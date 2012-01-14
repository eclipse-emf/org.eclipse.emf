/**
 * Copyright (c) 2005-2007 IBM Corporation and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: 
 *   IBM - Initial API and implementation
 */
package org.eclipse.emf.importer.rose.ui;

import org.eclipse.jface.viewers.ColumnWeightData;
import org.eclipse.jface.viewers.TableLayout;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.DirectoryDialog;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.widgets.Text;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.common.notify.impl.AdapterFactoryImpl;
import org.eclipse.emf.common.ui.celleditor.SingleColumnTableEditor;
import org.eclipse.emf.common.util.BasicDiagnostic;
import org.eclipse.emf.common.util.BasicMonitor;
import org.eclipse.emf.common.util.Diagnostic;
import org.eclipse.emf.edit.provider.ItemProvider;
import org.eclipse.emf.edit.ui.provider.AdapterFactoryContentProvider;
import org.eclipse.emf.edit.ui.provider.AdapterFactoryLabelProvider;
import org.eclipse.emf.importer.ModelImporter;
import org.eclipse.emf.importer.rose.RoseImporter;
import org.eclipse.emf.importer.rose.RoseImporterPlugin;
import org.eclipse.emf.importer.ui.contribution.base.ModelImporterDetailPage;
import org.eclipse.emf.converter.ConverterPlugin;
import org.eclipse.emf.converter.util.ConverterUtil;


/**
 * @since 2.1.0
 */
public class RoseDetailPage extends ModelImporterDetailPage
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

  @Override
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
  
  @Override
  protected boolean supportMultipleURIs()
  {
    return false;
  }  

  public RoseImporter getRoseImporter()
  {
    return (RoseImporter)getModelImporter();
  }

  @Override
  protected void addDetailControl(Composite parent)
  {
    Group pathMapGroup = new Group(parent, SWT.SHADOW_ETCHED_IN);
    {
      FormLayout layout = new FormLayout();
      layout.marginTop = 10;
      layout.spacing = 10;
      pathMapGroup.setLayout(layout);
    }
    pathMapGroup.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 2, 1));
    pathMapGroup.setText(RoseImporterPlugin.INSTANCE.getString("_UI_PathMap_label"));

    pathMapTable = new Table(pathMapGroup, SWT.BORDER | SWT.FULL_SELECTION);
    
    Composite buttonComposite = new Composite(pathMapGroup, SWT.NONE);
    {
      FormData data = new FormData();
      data.top = new FormAttachment(0, -10);
      data.right = new FormAttachment(100, -5);
      buttonComposite.setLayoutData(data);      
       
      GridLayout layout = new GridLayout(2, true);
      buttonComposite.setLayout(layout);
   }
     
    {
      FormData data = new FormData();
      data.top = new FormAttachment(buttonComposite, 0, SWT.BOTTOM);
      data.bottom = new FormAttachment(100, -10);
      data.left = new FormAttachment(0, 10);
      data.right = new FormAttachment(100, -10);
      pathMapTable.setLayoutData(data);
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

    new SingleColumnTableEditor(pathMapTable)
    {
      @Override
      protected Control createEditor(TableItem item, int column)
      {
        isCellEditing = true;
        setPageComplete(false);
        return createTextEditor(item, column);
      }

      @Override
      protected void endEditing(TableItem item, int column, Control editor, boolean accept)
      {
        super.endEditing(item, column, editor, accept);

        isCellEditing = false;
        setErrorMessage(null);
        setMessage(null);
        setPageComplete(isPageComplete());
      }

      @Override
      protected void update(TableItem item, int column, Control editor)
      {
        item.setText(column, ((Text)editor).getText());

        String key = item.getText(0);
        String value = item.getText(1);
        if ("".equals(value))
        {
          value = null;
        }
        getRoseImporter().getPathMap().put(key, value);
      }
    };

    AdapterFactory adapterFactory = new AdapterFactoryImpl();
    pathMapTableViewer.setColumnProperties(new String []{ "a", "b" });
    pathMapTableViewer.setContentProvider(new AdapterFactoryContentProvider(adapterFactory));
    pathMapTableViewer.setLabelProvider(new AdapterFactoryLabelProvider(adapterFactory)
      {
        @Override
        public Image getColumnImage(Object o, int columnIndex)
        {
          switch (columnIndex)
          {
            default: {
              return null;
            }
          }
        }

        @Override
        public String getColumnText(Object o, int columnIndex)
        {
          switch (columnIndex)
          {
            case 0: {
              return (String)o;
            }
            default:
            case 1: {
              String result = getRoseImporter().getPathMap().get(o);
              return result == null ? "" : result;
            }
          }
        }
      });
  }

  @Override
  protected void doHandleEvent(Event event)
  {
    if (event.type == SWT.Selection && event.widget == loadPathMapSymbolsButton)
    {
      if (uriText.getText().trim().length() > 0)
      {
        getControl().getDisplay().syncExec(new Runnable()
          {
            public void run()
            {
              loadPathMapSymbols();
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
  
  @Override
  public boolean isPageComplete()
  {
    return !isCellEditing && super.isPageComplete();
  }
  
  @Override
  protected void adjustLoadButton()
  {
    super.adjustLoadButton();
    loadPathMapSymbolsButton.setEnabled(loadButton.isEnabled()); 
  }
  
  @Override
  protected void refreshModel()
  {
    super.refreshModel();
    pathMapTableViewer.setInput(new ItemProvider(getRoseImporter().getPathMap().keySet()));
  }
  
  protected void loadPathMapSymbols()
  {
    Diagnostic diagnostic = null;
    try
    {
      diagnostic = getRoseImporter().loadPathMap(new BasicMonitor());
    }
    catch (Exception exception)
    {
      diagnostic = ConverterUtil.createErrorDiagnostic(exception, false);       
    }
    
    internalSetGenModelFileName(diagnostic.getSeverity() == Diagnostic.OK ? 
      getRoseImporter().getGenModelFileName() : 
      getRoseImporter().computeDefaultGenModelFileName());
    
    Diagnostic nameDiagnostic = getRoseImporter().checkGenModelFileName();
    if (nameDiagnostic.getSeverity() != Diagnostic.OK)
    {
      if (diagnostic.getSeverity() == Diagnostic.OK)
      {
        diagnostic = nameDiagnostic;
      }
      else
      {
        diagnostic = ConverterUtil.mergeDiagnostic(diagnostic, nameDiagnostic);
      }
    }
    
    if (diagnostic.getSeverity() == Diagnostic.OK && getRoseImporter().getPathMap().isEmpty())
    {
      diagnostic = new BasicDiagnostic(Diagnostic.INFO, ConverterPlugin.ID, 
        ConverterUtil.ACTION_DEFAULT, RoseImporterPlugin.INSTANCE.getString("_UI_NoPathMap_message"), null);
    }
    
    pathMapTableViewer.setInput(new ItemProvider(getRoseImporter().getPathMap().keySet()));
    handleDiagnostic(diagnostic, null, RoseImporterPlugin.INSTANCE.getString("_UI_LoadProblem_title"), RoseImporterPlugin.INSTANCE.getString("_UI_RoseLoadFailed_message"));
  }
}