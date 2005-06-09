/**
 * <copyright>
 *
 * Copyright (c) 2005 IBM Corporation and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: 
 *   IBM - Initial API and implementation
 *
 * </copyright>
 *
 * $Id: RoseDetailPage.java,v 1.10 2005/06/09 14:01:04 davidms Exp $
 */
package org.eclipse.emf.importer.rose.ui;

import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.Status;
import org.eclipse.jface.viewers.ColumnWeightData;
import org.eclipse.jface.viewers.TableLayout;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.FocusAdapter;
import org.eclipse.swt.events.FocusEvent;
import org.eclipse.swt.events.KeyAdapter;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.DirectoryDialog;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.widgets.Text;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.common.notify.impl.AdapterFactoryImpl;
import org.eclipse.emf.common.ui.celleditor.ExtendedTableEditor;
import org.eclipse.emf.edit.provider.ItemProvider;
import org.eclipse.emf.edit.ui.provider.AdapterFactoryContentProvider;
import org.eclipse.emf.edit.ui.provider.AdapterFactoryLabelProvider;
import org.eclipse.emf.importer.ImporterPlugin;
import org.eclipse.emf.importer.ModelImporter;
import org.eclipse.emf.importer.rose.RoseImporter;
import org.eclipse.emf.importer.rose.RoseImporterPlugin;
import org.eclipse.emf.importer.ui.contribution.base.ModelDetailPage;
import org.eclipse.emf.importer.util.ImporterUtil;


/**
 * @since 2.1.0
 */
public class RoseDetailPage extends ModelDetailPage
{
  protected Button loadPathMapSymbolsButton;
  protected Button browsePathMapLocationButton;
  protected Table pathMapTable;
  protected TableViewer pathMapTableViewer;
  
  protected boolean isCellEditing = false;

  public RoseDetailPage(ModelImporter modelImporter, String pageName)
  {
    super(modelImporter, pageName);

    setTitle(RoseImporterPlugin.INSTANCE.getString("_UI_RoseModelImport_title"));
    setDescription(RoseImporterPlugin.INSTANCE.getString(showGenModel() ?
      "_UI_RoseModelImportNewProject_description" : "_UI_RoseModelImportFile_description"));
  }

  public void dispose()
  {
    if (loadPathMapSymbolsButton != null)
    {
      loadPathMapSymbolsButton.removeListener(SWT.Selection, this);
      loadPathMapSymbolsButton = null;
    }
    if (browsePathMapLocationButton != null)
    {
      browsePathMapLocationButton.removeListener(SWT.Selection, this);
      browsePathMapLocationButton = null;
    }
    if (pathMapTable != null)
    {
      pathMapTable.removeListener(SWT.Selection, this);
      pathMapTable = null;
    }
    if (pathMapTableViewer != null)
    {
      pathMapTableViewer = null;
    }

    super.dispose();
  }
  
  protected boolean supportMultipleModelLocation()
  {
    return false;
  }  

  public RoseImporter getRoseImporter()
  {
    return (RoseImporter)getModelImporter();
  }

  protected void addControl(Composite parent)
  {
    Group pathMapGroup = new Group(parent, SWT.SHADOW_ETCHED_IN);
    {
      GridLayout layout = new GridLayout();
      layout.numColumns = 2;
      layout.verticalSpacing = 12;
      pathMapGroup.setLayout(layout);      
    }
    pathMapGroup.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 2, 1));
    pathMapGroup.setText(RoseImporterPlugin.INSTANCE.getString("_UI_PathMap_label"));

    Composite buttonComposite = new Composite(pathMapGroup, SWT.NONE);
     {
       GridData data = new GridData(GridData.HORIZONTAL_ALIGN_END);
       data.horizontalSpan = 2;
       buttonComposite.setLayoutData(data);       
       
       GridLayout layout = new GridLayout(2, true);
       layout.marginLeft = -5; 
       layout.marginRight = -5;
       layout.marginTop = -5;
       layout.marginBottom = -5;
       buttonComposite.setLayout(layout);
    }
    
    loadPathMapSymbolsButton = new Button(buttonComposite, SWT.PUSH);
    loadPathMapSymbolsButton.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
    loadPathMapSymbolsButton.setText(RoseImporterPlugin.INSTANCE.getString("_UI_LoadSymbols_label"));
    loadPathMapSymbolsButton.addListener(SWT.Selection, this);

    browsePathMapLocationButton = new Button(buttonComposite, SWT.PUSH);
    browsePathMapLocationButton.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
    browsePathMapLocationButton.setText(RoseImporterPlugin.INSTANCE.getString("_UI_Browse_label"));
    browsePathMapLocationButton.setEnabled(false);
    browsePathMapLocationButton.addListener(SWT.Selection, this);

    pathMapTable = new Table(pathMapGroup, SWT.BORDER);
    pathMapTable.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 2, 1));
    pathMapTableViewer = new TableViewer(pathMapTable);

    pathMapTable.setHeaderVisible(true);
    pathMapTable.setLinesVisible(true);
    {
      TableLayout layout = new TableLayout();

      TableColumn variableNameColumn = new TableColumn(pathMapTable, SWT.NONE);
      layout.addColumnData(new ColumnWeightData(2, true));
      variableNameColumn.setText(RoseImporterPlugin.INSTANCE.getString("_UI_SymbolName_label"));
      variableNameColumn.setResizable(true);

      TableColumn locationColumn = new TableColumn(pathMapTable, SWT.NONE);
      layout.addColumnData(new ColumnWeightData(3, true));
      locationColumn.setText(RoseImporterPlugin.INSTANCE.getString("_UI_ActualLocation_label"));
      locationColumn.setResizable(true);

      pathMapTable.setLayout(layout);
    }
    pathMapTable.addListener(SWT.Selection, this);

    ExtendedTableEditor extendedTableEditor = new ExtendedTableEditor(pathMapTable)
      {
        protected void editItem(final TableItem tableItem, final int column)
        {
          switch (column)
          {
            case 1: {
              final String string = tableItem.getText(column);
              horizontalAlignment = SWT.LEFT;
              minimumWidth = Math.max(50, tableItem.getBounds(column).width);

              final Text text = new Text(table, SWT.NONE);
              setEditor(text, tableItem, column);
              text.setFocus();
              text.setText(string);
              text.setSelection(0, string.length());

              text.addFocusListener(new FocusAdapter()
                {
                  public void focusLost(FocusEvent event)
                  {
                    modify(tableItem, column, text);
                  }
                });

              text.addKeyListener(new KeyAdapter()
                {
                  public void keyPressed(KeyEvent event)
                  {
                    if (event.character == '\r' || event.character == '\n')
                    {
                      modify(tableItem, column, text);
                      setEditor(null);
                      text.dispose();
                    }
                    else if (event.character == '\033')
                    {
                      setEditor(null);
                      text.dispose();
                    }
                  }
                });

              isCellEditing = true;
              setPageComplete(false);
              break;
            }
          }
        }

        protected void modify(TableItem tableItem, int column, Text text)
        {
          tableItem.setText(column, text.getText());
          String key = tableItem.getText();
          String value = tableItem.getText(column);
          text.setVisible(false);
          if ("".equals(value))
          {
            value = null;
          }
          getRoseImporter().getPathMap().put(key, value);

          isCellEditing = false;
          setErrorMessage(null);
          setMessage(null);
          setPageComplete(isPageComplete());
        }
      };

    AdapterFactory adapterFactory = new AdapterFactoryImpl();
    pathMapTableViewer.setColumnProperties(new String []{ "a", "b" });
    pathMapTableViewer.setContentProvider(new AdapterFactoryContentProvider(adapterFactory));
    pathMapTableViewer.setLabelProvider(new AdapterFactoryLabelProvider(adapterFactory)
      {
        public Image getColumnImage(Object o, int columnIndex)
        {
          switch (columnIndex)
          {
            default: {
              return null;
            }
          }
        }

        public String getColumnText(Object o, int columnIndex)
        {
          switch (columnIndex)
          {
            case 0: {
              return (String)o;
            }
            default:
            case 1: {
              String result = (String)getRoseImporter().getPathMap().get(o);
              return result == null ? "" : result;
            }
          }
        }
      });
  }

  protected void doHandleEvent(Event event)
  {
    if (event.type == SWT.Selection && event.widget == loadPathMapSymbolsButton)
    {
      if (modelLocationText.getText().trim().length() > 0)
      {
        getControl().getDisplay().syncExec(new Runnable()
          {
            public void run()
            {
              loadPathMapSymbols();;
            }
          });
      }
    }
    else if (event.type == SWT.Selection && event.widget == pathMapTable)
    {
      browsePathMapLocationButton.setEnabled(pathMapTable.getSelectionIndex() != -1);
    }
    else if (event.type == SWT.Selection && event.widget == browsePathMapLocationButton)
    {
      int index = pathMapTable.getSelectionIndex();
      String symbol = pathMapTable.getItem(index).getText();
      DirectoryDialog directoryDialog = new DirectoryDialog(getShell());
      directoryDialog.setMessage(RoseImporterPlugin.INSTANCE.getString("_UI_PathMapDirectoryDialog_message", new Object[] { symbol }));
      String path = directoryDialog.open();

      if (path != null && path.length() > 0)
      {
        getRoseImporter().getPathMap().put(symbol, path);
        setPageComplete(false);
        pathMapTableViewer.refresh();
        if (++index < pathMapTable.getItemCount())
        {
          pathMapTable.select(index);
        }
      }
    }
    else
    {
      super.doHandleEvent(event);
    }
    getContainer().updateButtons();
  }
  
  public boolean isPageComplete()
  {
    return !isCellEditing && super.isPageComplete();
  }
  
  protected void adjustLoadButton()
  {
    super.adjustLoadButton();
    loadPathMapSymbolsButton.setEnabled(loadButton.isEnabled()); 
  }
  
  protected void refreshModel()
  {
    super.refreshModel();
    pathMapTableViewer.setInput(new ItemProvider(getRoseImporter().getPathMap().keySet()));
  }
  
  protected void loadPathMapSymbols()
  {
    IStatus status = null;
    try
    {
      status = getRoseImporter().loadPathMap(new NullProgressMonitor());
    }
    catch (Exception exception)
    {
      status = ImporterUtil.createErrorStatus(exception, false);       
    }
    
    internalSetGenModelFileName(status.isOK() ? 
      getRoseImporter().getGenModelFileName() : 
      getRoseImporter().computeDefaultGenModelFileName());
    
    IStatus nameStatus = getRoseImporter().checkGenModelFileName();
    if (!nameStatus.isOK())
    {
      if (status.isOK())
      {
        status = nameStatus;
      }
      else
      {
        status = ImporterUtil.mergeStatus(status, nameStatus);
      }
    }
    
    if (status.isOK() && getRoseImporter().getPathMap().isEmpty())
    {
      status = new Status(IStatus.INFO, ImporterPlugin.ID, 
        ImporterUtil.ACTION_DEFAULT, RoseImporterPlugin.INSTANCE.getString("_UI_NoPathMap_message"), null);
    }
    
    pathMapTableViewer.setInput(new ItemProvider(getRoseImporter().getPathMap().keySet()));
    handleStatus(status, null, RoseImporterPlugin.INSTANCE.getString("_UI_LoadProblem_title"), RoseImporterPlugin.INSTANCE.getString("_UI_RoseLoadFailed_message"));
  }
}