/**
 * <copyright>
 *
 * Copyright (c) 2005 IBM Corporation and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Common Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/cpl-v10.html
 * 
 * Contributors: 
 *   IBM - Initial API and implementation
 *
 * </copyright>
 *
 * $Id: RoseDetailPage.java,v 1.1 2005/05/10 17:40:33 davidms Exp $
 */
package org.eclipse.emf.importer.rose.ui;

import java.util.Iterator;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.jface.dialogs.ErrorDialog;
import org.eclipse.jface.viewers.ColumnWeightData;
import org.eclipse.jface.viewers.TableLayout;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.FocusAdapter;
import org.eclipse.swt.events.FocusEvent;
import org.eclipse.swt.events.KeyAdapter;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.DirectoryDialog;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.actions.WorkspaceModifyOperation;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.common.notify.impl.AdapterFactoryImpl;
import org.eclipse.emf.common.ui.celleditor.ExtendedTableEditor;
import org.eclipse.emf.edit.provider.ItemProvider;
import org.eclipse.emf.edit.ui.provider.AdapterFactoryContentProvider;
import org.eclipse.emf.edit.ui.provider.AdapterFactoryLabelProvider;
import org.eclipse.emf.importer.ModelImporter;
import org.eclipse.emf.importer.rose.RoseImporter;
import org.eclipse.emf.importer.rose.RoseImporterPlugin;
import org.eclipse.emf.importer.ui.wizard.base.ModelDetailPage;


/**
 * @since 2.1.0
 */
public class RoseDetailPage extends ModelDetailPage
{
  protected Button loadButton;
  protected TableViewer pathMapTableViewer;

  protected boolean isCellEditing;
  protected boolean hasToCalculateEPackages = false;

  public RoseDetailPage(ModelImporter modelImporter, String pageName)
  {
    super(modelImporter, pageName);

    setTitle(RoseImporterPlugin.INSTANCE.getString("_UI_RoseModelImport_title"));
    setDescription(RoseImporterPlugin.INSTANCE.getString(showGenModel()
      ? "_UI_RoseModelImportFile_description" : "_UI_RoseModelImportNewProject_description"));
  }

  public void dispose()
  {
    if (loadButton != null)
    {
      loadButton.removeListener(SWT.Selection, this);
      loadButton = null;
    }

    super.dispose();
  }

  public RoseImporter getRoseImporter()
  {
    return (RoseImporter)getModelImporter();
  }

  protected void pageDeactivated(boolean performFinish)
  {
    if (isPageComplete() && hasToCalculateEPackages)
    {
      getControl().getDisplay().asyncExec(new Runnable()
        {
          public void run()
          {
            computeEPackages();
          }
        });
    }

    super.pageDeactivated(performFinish);
  }

  protected void addControl(Composite parent)
  {
    Label pathMapLabel = new Label(parent, SWT.LEFT);
    {
      pathMapLabel.setText(RoseImporterPlugin.INSTANCE.getString("_UI_PathMap_label"));

      GridData data = new GridData();
      data.horizontalAlignment = GridData.FILL;
      pathMapLabel.setLayoutData(data);
    }

    Composite buttonComposite = new Composite(parent, SWT.NONE);
    {
      GridData data = new GridData();
      data.horizontalAlignment = GridData.END;
      buttonComposite.setLayoutData(data);

      RowLayout layout = new RowLayout();
      layout.justify = true;
      layout.pack = true;
      layout.spacing = 15;
      buttonComposite.setLayout(layout);
    }

    loadButton = new Button(buttonComposite, SWT.PUSH);
    loadButton.setText(RoseImporterPlugin.INSTANCE.getString("_UI_Load_label"));
    loadButton.addListener(SWT.Selection, this);

    final Button pathMapTableBrowseButton = new Button(buttonComposite, SWT.PUSH);
    pathMapTableBrowseButton.setEnabled(false);
    pathMapTableBrowseButton.setText(RoseImporterPlugin.INSTANCE.getString("_UI_Browse_label"));

    final Table pathMapTable = new Table(parent, SWT.BORDER);

    pathMapTable.addSelectionListener(new SelectionAdapter()
      {
        public void widgetSelected(SelectionEvent event)
        {
          pathMapTableBrowseButton.setEnabled(pathMapTable.getSelectionIndex() != -1);
        }
      });

    pathMapTableBrowseButton.addSelectionListener(new SelectionAdapter()
      {
        public void widgetSelected(SelectionEvent event)
        {
          DirectoryDialog directoryDialog = new DirectoryDialog(getShell());
          String path = directoryDialog.open();
          if (path != null && path.length() > 0)
          {
            int index = pathMapTable.getSelectionIndex();
            getRoseImporter().getPathMap().put(pathMapTable.getItem(index).getText(), path);
            setPageComplete(false);
            pathMapTableViewer.refresh();
            if (++index < pathMapTable.getItemCount())
            {
              pathMapTable.select(index);
            }
          }
        }
      });

    pathMapTableViewer = new TableViewer(pathMapTable);
    {
      GridData data = new GridData();
      data.verticalAlignment = GridData.FILL;
      data.grabExcessVerticalSpace = true;
      data.horizontalAlignment = GridData.FILL;
      data.horizontalSpan = 2;
      pathMapTable.setLayoutData(data);
    }

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
    if (event.type == SWT.Modify && event.widget == modelLocationText)
    {
      setErrorMessage(null);
      getRoseImporter().setModelLocation(null);
    }
    else if (event.type == SWT.Selection && event.widget == loadButton)
    {
      setErrorMessage(null);
      refreshModel();
    }
    else
    {
      super.doHandleEvent(event);
    }
    setPageComplete(isPageComplete());
  }

  public boolean isPageComplete()
  {
    return super.isPageComplete() && !isCellEditing && (hasToCalculateEPackages || !getRoseImporter().getEPackages().isEmpty())
      && !getRoseImporter().getModelLocationURIs().isEmpty();
  }

  protected String[] getFilterExtensions()
  {
    return new String []{ "*.mdl" };
  }

  protected void refreshModel(IProgressMonitor progressMonitor) throws Exception
  {
    IStatus status = getRoseImporter().loadPathMap(progressMonitor);
    pathMapTableViewer.setInput(new ItemProvider(getRoseImporter().getPathMap().keySet()));
    hasToCalculateEPackages = true;
    if (processStatus(status))
    {
      internalSetGenModelFileName(getRoseImporter().getGenModelFileName());
    }
    else
    {
      internalSetGenModelFileName(getRoseImporter().computeDefaultGenModelFileName());
    }
  }

  protected void computeEPackages()
  {
    WorkspaceModifyOperation initializeOperation = new WorkspaceModifyOperation()
      {
        protected void execute(IProgressMonitor progressMonitor) throws CoreException
        {
          try
          {
            computeEPackages(progressMonitor);
          }
          catch (Exception exception)
          {
            RoseImporterPlugin.INSTANCE.log(exception);
          }
          finally
          {
            progressMonitor.done();
          }
        }
      };

    try
    {
      getContainer().run(false, false, initializeOperation);
    }
    catch (Exception exception)
    {
      RoseImporterPlugin.INSTANCE.log(exception);
    }
  }

  protected void computeEPackages(IProgressMonitor progressMonitor) throws Exception
  {
    processStatus(getRoseImporter().computeEPackages(progressMonitor));
    hasToCalculateEPackages = false;
  }

  protected boolean processStatus(IStatus status)
  {
    if (status.isOK())
    {
      setErrorMessage(null);
      return true;
    }
    else
    {
      setErrorMessage(status.getMessage());
      if (status.getChildren().length > 0)
      {
        boolean showErrorDialog = true;
        if (status.getSeverity() == IStatus.INFO)
        {
          if (getRoseImporter().getPathMap().isEmpty())
          {
            showErrorDialog = false;
          }
          else
          {
            for (Iterator i = getRoseImporter().getPathMap().values().iterator(); i.hasNext();)
            {
              String value = (String)i.next();
              if (value == null || value.trim().length() == 0)
              {
                showErrorDialog = false;
                break;
              }
            }
          }
        }

        if (showErrorDialog)
        {
          ErrorDialog dialog = new ErrorDialog(getShell(),
            RoseImporterPlugin.INSTANCE.getString("_UI_LoadProblem_title"),
            RoseImporterPlugin.INSTANCE.getString("_UI_RoseLoadFailed_message"),
            status.getChildren()[0], IStatus.INFO | IStatus.WARNING | IStatus.ERROR)
            {
              protected Image getImage()
              {
                return getErrorImage();
              }
            };
          dialog.open();
        }
      }
      
      return false;
    }
  }
}