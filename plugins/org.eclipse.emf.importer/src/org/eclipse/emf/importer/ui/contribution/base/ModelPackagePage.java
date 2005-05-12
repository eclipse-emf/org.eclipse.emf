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
 * ModelPackagePage.java,v 1.1 2005/05/12 17:10:24 marcelop Exp
 */
package org.eclipse.emf.importer.ui.contribution.base;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.jface.viewers.AbstractTreeViewer;
import org.eclipse.jface.viewers.CheckStateChangedEvent;
import org.eclipse.jface.viewers.CheckboxTableViewer;
import org.eclipse.jface.viewers.CheckboxTreeViewer;
import org.eclipse.jface.viewers.ColumnWeightData;
import org.eclipse.jface.viewers.ICheckStateListener;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.TableLayout;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.FocusAdapter;
import org.eclipse.swt.events.FocusEvent;
import org.eclipse.swt.events.KeyAdapter;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.ui.dialogs.ResourceSelectionDialog;

import org.eclipse.emf.codegen.ecore.genmodel.GenPackage;
import org.eclipse.emf.codegen.ecore.genmodel.provider.GenBaseItemProvider;
import org.eclipse.emf.codegen.ecore.genmodel.provider.GenModelEditPlugin;
import org.eclipse.emf.codegen.ecore.genmodel.provider.GenModelItemProviderAdapterFactory;
import org.eclipse.emf.codegen.ecore.genmodel.provider.GenPackageItemProvider;
import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.common.notify.impl.AdapterFactoryImpl;
import org.eclipse.emf.common.ui.celleditor.ExtendedTableEditor;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.common.util.UniqueEList;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.edit.provider.ItemProvider;
import org.eclipse.emf.edit.ui.provider.AdapterFactoryContentProvider;
import org.eclipse.emf.edit.ui.provider.AdapterFactoryLabelProvider;
import org.eclipse.emf.edit.ui.provider.ExtendedImageRegistry;
import org.eclipse.emf.importer.ImporterPlugin;
import org.eclipse.emf.importer.ModelImporter;


/**
 * @since 2.1.0
 */
public class ModelPackagePage extends ModelImporterPage
{
  public static final int ECORE_FILE_COLUMN = 0;

  protected CheckboxTableViewer packagesCheckboxTableViewer;
  protected CheckboxTreeViewer referencedGenModelsCheckboxTreeViewer;

  protected List filteredEPackages = new ModelImporter.EPackageList();

  protected boolean isCellEditing = false;
  protected boolean showReferencedGenModels = false;

  public ModelPackagePage(ModelImporter modelImporter, String pageName)
  {
    super(modelImporter, pageName);

    setTitle(ImporterPlugin.INSTANCE.getString("_UI_PackageSelection_title"));
    setDescription(ImporterPlugin.INSTANCE.getString("_UI_PackageSelection_description"));
  }

  public void dispose()
  {
    packagesCheckboxTableViewer = null;
    referencedGenModelsCheckboxTreeViewer = null;

    if (filteredEPackages != null)
    {
      filteredEPackages.clear();
      filteredEPackages = null;
    }

    super.dispose();
  }

  public void setShowReferencedGenModels(boolean showReferencedGenModels)
  {
    this.showReferencedGenModels = showReferencedGenModels;
  }

  public boolean showReferencedGenModels()
  {
    return showReferencedGenModels;
  }

  protected void pageActivated(final boolean firstTime, int cause)
  {
    getControl().getDisplay().asyncExec(new Runnable()
      {
        public void run()
        {
          filterPackagesTable(true);
          getContainer().updateButtons();
        }
      });
  }

  public boolean isPageComplete()
  {
    return super.isPageComplete() 
      && !getModelImporter().getEPackages().isEmpty()
      && packagesCheckboxTableViewer != null 
      && packagesCheckboxTableViewer.getCheckedElements().length > 0;
  }

  public void createControl(Composite parent)
  {
    Composite composite = new Composite(parent, SWT.NONE);
    {
      GridLayout layout = new GridLayout();
      layout.numColumns = 2;
      layout.verticalSpacing = 12;
      composite.setLayout(layout);

      GridData data = new GridData();
      data.verticalAlignment = GridData.FILL;
      data.grabExcessVerticalSpace = true;
      data.horizontalAlignment = GridData.FILL;
      composite.setLayoutData(data);
    }

    createPackageControl(composite);
    if (showReferencedGenModels())
    {
      createReferencedGenModelControl(composite);
    }

    setControl(composite);
  }

  protected void createPackageControl(Composite parent)
  {
    Label packagesLabel = new Label(parent, SWT.LEFT);
    packagesLabel.setText(getPackagesLabel());
    {
      GridData data = new GridData();
      data.horizontalAlignment = GridData.FILL;
      packagesLabel.setLayoutData(data);
    }

    Composite selectionComposite = new Composite(parent, SWT.NONE);
    {
      GridData data = new GridData();
      data.horizontalAlignment = GridData.END;
      selectionComposite.setLayoutData(data);

      RowLayout layout = new RowLayout();
      layout.justify = true;
      layout.pack = true;
      layout.spacing = 15;
      selectionComposite.setLayout(layout);
    }

    Button selectAllButton = new Button(selectionComposite, SWT.PUSH);
    selectAllButton.setText(getSelectAllLabel());
    selectAllButton.addSelectionListener(new SelectionAdapter()
      {
        public void widgetSelected(SelectionEvent event)
        {
          packagesCheckboxTableViewer.setCheckedElements(getModelImporter().getEPackages().toArray());
          ePackageCheckStateChanged();
        }
      });

    Button deselectAllButton = new Button(selectionComposite, SWT.PUSH);
    deselectAllButton.setText(getDeselectAllLabel());
    deselectAllButton.addSelectionListener(new SelectionAdapter()
      {
        public void widgetSelected(SelectionEvent event)
        {
          packagesCheckboxTableViewer.setCheckedElements(new Object [0]);
          ePackageCheckStateChanged();
        }
      });

    Table packagesTable = new Table(parent, SWT.CHECK | SWT.BORDER);
    packagesCheckboxTableViewer = new CheckboxTableViewer(packagesTable);
    {
      GridData data = new GridData();
      data.verticalAlignment = GridData.FILL;
      data.grabExcessHorizontalSpace = true;
      data.grabExcessVerticalSpace = true;
      data.horizontalAlignment = GridData.FILL;
      data.horizontalSpan = 2;
      data.heightHint = 90;
      packagesTable.setLayoutData(data);
    }

    packagesTable.setHeaderVisible(true);
    packagesTable.setLinesVisible(true);
    {
      TableLayout layout = new TableLayout();

      TableColumn packageColumn = new TableColumn(packagesTable, SWT.NONE);
      layout.addColumnData(new ColumnWeightData(1, true));
      packageColumn.setText(getPackageColumnLabel());
      packageColumn.setResizable(true);

      TableColumn ecoreFileNameColumn = new TableColumn(packagesTable, SWT.NONE);
      layout.addColumnData(new ColumnWeightData(1, true));
      ecoreFileNameColumn.setText(getEcoreNameColumnLabel());
      ecoreFileNameColumn.setResizable(true);

      packagesTable.setLayout(layout);
    }

    ExtendedTableEditor extendedTableEditor = new ExtendedTableEditor(packagesTable)
      {
        protected void editItem(final TableItem tableItem, final int column)
        {
          switch (column)
          {
            case 1:
            case 2: {
              final String string = tableItem.getText(column);
              horizontalAlignment = SWT.LEFT;
              minimumWidth = Math.max(50, tableItem.getBounds(column).width);

              final Text text = new Text(table, SWT.NONE);
              setEditor(text, tableItem, column);
              text.setFocus();
              text.setText(string);
              text.setSelection(0, string.length());
              validateEcoreModelFileName(string, null);

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

              text.addModifyListener(new ModifyListener()
                {
                  public void modifyText(ModifyEvent event)
                  {
                    if (column == 1)
                    {
                      validateEcoreModelFileName(text.getText(), null);
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
          String value = tableItem.getText(column);
          text.setVisible(false);

          StringBuffer ecoreFileName = getModelImporter().getEPackageInfo((EPackage)tableItem.getData()).getEcoreFileName();
          if (ecoreFileName != null)
          {
            ecoreFileName.replace(0, ecoreFileName.length(), value);
          }
          isCellEditing = false;
          validate();
          setPageComplete(isPageComplete());
        }
      };

    AdapterFactory adapterFactory = new AdapterFactoryImpl();
    packagesCheckboxTableViewer.setColumnProperties(new String []{ "a", "b" });
    packagesCheckboxTableViewer.setContentProvider(new AdapterFactoryContentProvider(adapterFactory));
    packagesCheckboxTableViewer.setLabelProvider(new AdapterFactoryLabelProvider(adapterFactory)
      {
        public Image getColumnImage(Object o, int columnIndex)
        {
          switch (columnIndex)
          {
            case 0: {
              return getEPackageImage();
            }
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
              return ((EPackage)o).getName();
            }
            case 1:
            case 2: {
              return getModelImporter().getEPackageInfo((EPackage)o).getEcoreFileName().toString();
            }
            default: {
              return "";
            }
          }
        }
      });

    packagesCheckboxTableViewer.addCheckStateListener(new ICheckStateListener()
      {
        public void checkStateChanged(CheckStateChangedEvent event)
        {
          ePackageCheckStateChanged();
        }
      });
  }

  protected void ePackageCheckStateChanged()
  {
    validate();
    getContainer().updateButtons();

    Set checkedElements = new HashSet(Arrays.asList(packagesCheckboxTableViewer.getCheckedElements()));
    for (Iterator i = getModelImporter().getEPackages().iterator(); i.hasNext();)
    {
      EPackage ePackage = (EPackage)i.next();
      getModelImporter().getEPackageInfo(ePackage).setGenerate(checkedElements.contains(ePackage));
    }
  }

  public List getCheckedEPackages()
  {
    if (packagesCheckboxTableViewer != null)
    {
      return Arrays.asList(packagesCheckboxTableViewer.getCheckedElements());
    }
    else
    {
      return Collections.EMPTY_LIST;
    }
  }

  protected String getEcoreNameColumnLabel()
  {
    return ImporterPlugin.INSTANCE.getString("_UI_EcoreModelName_label");
  }

  protected String getPackageColumnLabel()
  {
    return ImporterPlugin.INSTANCE.getString("_UI_Package_label");
  }

  protected String getDeselectAllLabel()
  {
    return ImporterPlugin.INSTANCE.getString("_UI_DeselectAll_label");
  }

  protected String getSelectAllLabel()
  {
    return ImporterPlugin.INSTANCE.getString("_UI_SelectAll_label");
  }

  protected String getPackagesLabel()
  {
    return ImporterPlugin.INSTANCE.getString("_UI_RootPackages_label");
  }

  protected Image getEPackageImage()
  {
    return ExtendedImageRegistry.INSTANCE.getImage(new GenBaseItemProvider.UnderlayedImage(
      GenModelEditPlugin.INSTANCE.getImage("full/obj16/EPackage")));
  }

  protected void createReferencedGenModelControl(Composite parent)
  {
    Label referencedGenModelsLabel = new Label(parent, SWT.LEFT);
    {
      referencedGenModelsLabel.setText(getReferencedGenModelLabel());

      GridData data = new GridData();
      data.horizontalAlignment = GridData.FILL;
      referencedGenModelsLabel.setLayoutData(data);
    }

    Button referencedGenModelsTreeBrowseButton = new Button(parent, SWT.PUSH);
    referencedGenModelsTreeBrowseButton.setText(getBrowseButtonLabel());
    {
      GridData data = new GridData();
      data.horizontalAlignment = GridData.END;
      referencedGenModelsTreeBrowseButton.setLayoutData(data);
    }

    referencedGenModelsTreeBrowseButton.addSelectionListener(new SelectionAdapter()
      {
        public void widgetSelected(SelectionEvent event)
        {
          Collection genModelResources = new ArrayList();
          ResourceSelectionDialog resourceSelectionDialog = new ResourceSelectionDialog(
            getShell(),
            ResourcesPlugin.getWorkspace().getRoot(),
            getSelectGenModelDialogTitle());

          resourceSelectionDialog.setInitialSelections(genModelResources.toArray());
          Object[] result = resourceSelectionDialog.open() == ResourceSelectionDialog.OK ? 
            resourceSelectionDialog.getResult() : null;
          if (result != null)
          {
            ResourceSet referencedGenModels = getModelImporter().createResourceSet();
            List genModels = new UniqueEList.FastCompare(getModelImporter().getExternalGenModels());
            for (int i = 0; i < result.length; ++i)
            {
              IResource resource = (IResource)result[i];
              if (resource.getType() == IResource.FILE && !isInJavaOutput(resource)
                && "genmodel".equals(resource.getFullPath().getFileExtension()))
              {
                URI genModelURI = URI.createPlatformResourceURI(resource.getFullPath().toString(), true);
                Resource genModelResource = referencedGenModels.getResource(genModelURI, true);
                genModels.add(genModelResource.getContents().get(0));
              }
            }

            referencedGenModelsCheckboxTreeViewer.getTree().deselectAll();
            referencedGenModelsCheckboxTreeViewer.setInput(new ItemProvider(genModels));
            for (Iterator i = genModels.iterator(); i.hasNext();)
            {
              referencedGenModelsCheckboxTreeViewer.expandToLevel(i.next(), AbstractTreeViewer.ALL_LEVELS);
            }            
            referencedGenModelsCheckboxTreeViewerCheckStateChanged();
          }
        }
      });

    final Tree referencedGenModelsTree = new Tree(parent, SWT.CHECK | SWT.BORDER | SWT.MULTI);
    referencedGenModelsCheckboxTreeViewer = new CheckboxTreeViewer(referencedGenModelsTree);
    {
      GridData data = new GridData();
      data.verticalAlignment = GridData.FILL;
      data.grabExcessHorizontalSpace = true;
      data.horizontalAlignment = GridData.FILL;
      data.horizontalSpan = 2;
      data.heightHint = 60;
      referencedGenModelsTree.setLayoutData(data);
    }
    GenModelItemProviderAdapterFactory genModelItemProviderAdapterFactory = new GenModelItemProviderAdapterFactory()
      {
        public Adapter createGenPackageAdapter()
        {
          if (genPackageItemProvider == null)
          {
            genPackageItemProvider = new GenPackageItemProvider(this)
              {
                public Collection getChildrenFeatures(Object object)
                {
                  return Collections.EMPTY_LIST;
                }
              };
          }

          return genPackageItemProvider;
        }
      };
    referencedGenModelsCheckboxTreeViewer.setContentProvider(new AdapterFactoryContentProvider(genModelItemProviderAdapterFactory));
    referencedGenModelsCheckboxTreeViewer.setLabelProvider(new AdapterFactoryLabelProvider(genModelItemProviderAdapterFactory));

    referencedGenModelsCheckboxTreeViewer.addCheckStateListener(new ICheckStateListener()
      {
        public void checkStateChanged(CheckStateChangedEvent event)
        {
          referencedGenModelsCheckboxTreeViewerCheckStateChanged();
        }
      });
  }
  
  protected void referencedGenModelsCheckboxTreeViewerCheckStateChanged()
  {
    filterPackagesTable(false);
    validate();
    setPageComplete(isPageComplete());    
  }

  protected String getBrowseButtonLabel()
  {
    return ImporterPlugin.INSTANCE.getString("_UI_Browse_label");
  }

  protected String getReferencedGenModelLabel()
  {
    return ImporterPlugin.INSTANCE.getString("_UI_ReferencedGeneratorModels_label");
  }

  protected String getSelectGenModelDialogTitle()
  {
    return ImporterPlugin.INSTANCE.getString("_UI_SelectAllGeneratorModels_description");
  }

  protected void validate()
  {
    List tableCheckedEPackages = getCheckedEPackages();

    List referencedEPackages = new ModelImporter.EPackageList();
    for (Iterator i = getModelImporter().getEPackages().iterator(); i.hasNext();)
    {
      EPackage ePackage = (EPackage)i.next();
      if (tableCheckedEPackages.contains(ePackage) || !filteredEPackages.contains(ePackage))
      {
        for (Iterator j = ePackage.eAllContents(); j.hasNext();)
        {
          EObject eObject = (EObject)j.next();
          for (Iterator k = eObject.eCrossReferences().iterator(); k.hasNext();)
          {
            Object o = k.next();
            if (o instanceof EClassifier)
            {
              EClassifier eClassifier = (EClassifier)o;
              referencedEPackages.add(eClassifier.getEPackage());
            }
          }
        }
      }
    }

    String errorMessage = null;
    for (Iterator i = referencedEPackages.iterator(); i.hasNext();)
    {
      EPackage ePackage = (EPackage)i.next();
      if (!tableCheckedEPackages.contains(ePackage) && filteredEPackages.contains(ePackage))
      {
        errorMessage = ImporterPlugin.INSTANCE.getString(
          "_UI_PackageIsUsedBySelectedPackage_message",
          new Object []{ ePackage.getName() });
      }
    }

    if (errorMessage != null)
    {
      setErrorMessage(errorMessage);
    }
    else
    {
      boolean allNamesAreValid = true;
      for (Iterator i = tableCheckedEPackages.iterator(); i.hasNext();)
      {
        EPackage ePackage = (EPackage)i.next();
        String fileName = getModelImporter().getEPackageInfo(ePackage).getEcoreFileName().toString();
        allNamesAreValid = validateEcoreModelFileName(fileName, ePackage.getName());
        if (!allNamesAreValid)
        {
          break;
        }
      }
      if (allNamesAreValid)
      {
        setErrorMessage(null);
      }
    }
  }

  protected boolean validateEcoreModelFileName(String fileName, String packageName)
  {
    IStatus status = getModelImporter().checkEcoreModelFileName(fileName, packageName);
    if (status.isOK())
    {
      setErrorMessage(null);
      return true;
    }
    else
    {
      setErrorMessage(status.getMessage());
      return false;
    }
  }

  protected void filterPackagesTable(boolean reloadReferencedGenPackagesTable)
  {
    if (reloadReferencedGenPackagesTable)
    {
      if (!getModelImporter().getReferencedGenPackages().isEmpty() || !getModelImporter().getExternalGenModels().isEmpty())
      {
        GenPackage[] referencedGenPackages = (GenPackage[])getModelImporter().getReferencedGenPackages().toArray(new GenPackage [getModelImporter().getReferencedGenPackages().size()]);
        Set genModels = new HashSet();
        for (int i = 0; i < referencedGenPackages.length; i++)
        {
          genModels.add(referencedGenPackages[i].getGenModel());
        }
        genModels.addAll(getModelImporter().getExternalGenModels());
        referencedGenModelsCheckboxTreeViewer.setInput(new ItemProvider(genModels));
        referencedGenModelsCheckboxTreeViewer.expandAll();
        referencedGenModelsCheckboxTreeViewer.setCheckedElements(referencedGenPackages);
        referencedGenModelsCheckboxTreeViewer.setSelection(new StructuredSelection(referencedGenPackages), true);
      }
    }
    else
    {
      getModelImporter().getReferencedGenPackages().clear();
      Object[] checkedElements = referencedGenModelsCheckboxTreeViewer.getCheckedElements();
      for (int i = 0; i < checkedElements.length; i++)
      {
        if (checkedElements[i] instanceof GenPackage)
        {
          getModelImporter().getReferencedGenPackages().add(checkedElements[i]);
        }
      }
    }

    filteredEPackages = getModelImporter().filterReferencedEPackages(getModelImporter().getEPackages());
    List checkedEPackages = new ArrayList();
    for (Iterator i = getModelImporter().getEPackages().iterator(); i.hasNext();)
    {
      EPackage ePackage = (EPackage)i.next();
      ModelImporter.EPackageInfo ePackageInfo = getModelImporter().getEPackageInfo(ePackage);
      if (filteredEPackages.contains(ePackage) && ePackageInfo.isGenerate())
      {
        checkedEPackages.add(ePackage);
      }
      else
      {
        ePackageInfo.setGenerate(false);
      }
    }
    packagesCheckboxTableViewer.setInput(new ItemProvider(filteredEPackages));
    packagesCheckboxTableViewer.setCheckedElements(checkedEPackages.toArray());
  }
}
