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
 * $Id: ModelConverterPackagePage.java,v 1.6 2006/04/18 17:01:33 marcelop Exp $
 */
package org.eclipse.emf.converter.ui.contribution.base;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;
import org.eclipse.jface.viewers.AbstractTreeViewer;
import org.eclipse.jface.viewers.CheckStateChangedEvent;
import org.eclipse.jface.viewers.CheckboxTableViewer;
import org.eclipse.jface.viewers.CheckboxTreeViewer;
import org.eclipse.jface.viewers.ColumnWeightData;
import org.eclipse.jface.viewers.ICheckStateListener;
import org.eclipse.jface.viewers.IContentProvider;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.TableLayout;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerFilter;
import org.eclipse.jface.viewers.ViewerSorter;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.SashForm;
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
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Tree;

import org.eclipse.emf.codegen.ecore.genmodel.GenModel;
import org.eclipse.emf.codegen.ecore.genmodel.GenModelPackage;
import org.eclipse.emf.codegen.ecore.genmodel.GenPackage;
import org.eclipse.emf.codegen.ecore.genmodel.provider.GenBaseItemProvider;
import org.eclipse.emf.codegen.ecore.genmodel.provider.GenModelEditPlugin;
import org.eclipse.emf.codegen.ecore.genmodel.provider.GenModelItemProvider;
import org.eclipse.emf.codegen.ecore.genmodel.provider.GenModelItemProviderAdapterFactory;
import org.eclipse.emf.codegen.ecore.genmodel.provider.GenPackageItemProvider;
import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.common.notify.impl.AdapterFactoryImpl;
import org.eclipse.emf.common.ui.celleditor.ExtendedTableEditor;
import org.eclipse.emf.common.ui.dialogs.WorkspaceResourceDialog;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.common.util.UniqueEList;
import org.eclipse.emf.converter.ConverterPlugin;
import org.eclipse.emf.converter.ModelConverter;
import org.eclipse.emf.converter.util.ConverterUIUtil;
import org.eclipse.emf.converter.util.ConverterUtil;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.edit.provider.ItemProvider;
import org.eclipse.emf.edit.ui.provider.AdapterFactoryContentProvider;
import org.eclipse.emf.edit.ui.provider.AdapterFactoryLabelProvider;
import org.eclipse.emf.edit.ui.provider.ExtendedImageRegistry;


/**
 * @since 2.2.0
 */
public class ModelConverterPackagePage extends ModelConverterPage
{  
  protected CheckboxTableViewer ePackagesCheckboxTableViewer;
  protected int ePackageDataTableColumn = 1;
  protected CheckboxTreeViewer referencedGenModelsCheckboxTreeViewer;
  protected List filteredEPackages = new ConverterUtil.EPackageList();
  protected boolean showReferencedGenModels = false;
  protected boolean isCellEditing = false;

  protected ModelConverterPackagePage(ModelConverter modelConverter, String pageName)
  {
    super(modelConverter, pageName);

    setTitle(ConverterPlugin.INSTANCE.getString("_UI_PackageSelection_title"));
  }

  public void dispose()
  {
    ePackagesCheckboxTableViewer = null;
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
          filterEPackagesTable(true);
          validate();
          getContainer().updateButtons();
        }
      });
  }

  public boolean isPageComplete()
  {
    return super.isPageComplete() && !getModelConverter().getEPackages().isEmpty() && ePackagesCheckboxTableViewer != null
      && ePackagesCheckboxTableViewer.getCheckedElements().length > 0
      && !isCellEditing;
  }

  public void createControl(Composite parent)
  {
    Composite composite = null;
    if (showReferencedGenModels())
    {
      composite = new SashForm(parent, SWT.VERTICAL);
      composite.setLayoutData(new GridData(GridData.FILL_BOTH));
    }
    else
    {
      composite = new Composite(parent, SWT.NONE);
      GridLayout layout = new GridLayout();
      layout.verticalSpacing = 12;
      composite.setLayout(layout);
      composite.setLayoutData(new GridData(GridData.FILL_BOTH | GridData.GRAB_VERTICAL));
    }

    Composite packageComposite = new Composite(composite, SWT.NONE);
    {
      GridLayout layout = new GridLayout();
      layout.verticalSpacing = 12;
      packageComposite.setLayout(layout);
      packageComposite.setLayoutData(new GridData(GridData.FILL_BOTH | GridData.GRAB_VERTICAL));
    }
    createPackageControl(packageComposite);

    if (showReferencedGenModels())
    {
      Composite referencedGenModelComposite = new Composite(composite, SWT.NONE);
      {
        GridLayout layout = new GridLayout();
        layout.verticalSpacing = 12;
        referencedGenModelComposite.setLayout(layout);
        referencedGenModelComposite.setLayoutData(new GridData(GridData.FILL_BOTH | GridData.GRAB_VERTICAL));
      }
      createReferencedGenModelControl(referencedGenModelComposite);
      ((SashForm)composite).setWeights(new int []{ 60, 40 });
    }

    setControl(composite);
  }

  protected void createPackageControl(Composite parent)
  {
    Composite composite = new Composite(parent, SWT.NONE);
    composite.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
    {
      GridLayout layout = new GridLayout(2, false);
      layout.marginLeft = -5;
      layout.marginRight = -5;
      layout.marginTop = -5;
      layout.marginBottom = -5;
      composite.setLayout(layout);
    }

    Label packagesLabel = new Label(composite, SWT.LEFT);
    packagesLabel.setText(getPackagesLabel());
    packagesLabel.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));

    Composite selectionComposite = new Composite(composite, SWT.NONE);
    selectionComposite.setLayoutData(new GridData(GridData.HORIZONTAL_ALIGN_END));
    {
      GridLayout layout = new GridLayout(2, true);
      layout.marginLeft = -5;
      layout.marginRight = -5;
      layout.marginTop = -5;
      layout.marginBottom = -5;
      selectionComposite.setLayout(layout);
    }

    Button selectAllButton = new Button(selectionComposite, SWT.PUSH);
    selectAllButton.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
    selectAllButton.setText(getSelectAllLabel());
    selectAllButton.addSelectionListener(new SelectionAdapter()
      {
        public void widgetSelected(SelectionEvent event)
        {
          ePackagesCheckboxTableViewer.setCheckedElements(getModelConverter().getEPackages().toArray());
          ePackageCheckStateChanged();
        }
      });

    Button deselectAllButton = new Button(selectionComposite, SWT.PUSH);
    deselectAllButton.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
    deselectAllButton.setText(getDeselectAllLabel());
    deselectAllButton.addSelectionListener(new SelectionAdapter()
      {
        public void widgetSelected(SelectionEvent event)
        {
          ePackagesCheckboxTableViewer.setCheckedElements(new Object [0]);
          ePackageCheckStateChanged();
        }
      });

    Table packagesTable = new Table(parent, SWT.CHECK | SWT.BORDER | SWT.SINGLE);
    ePackagesCheckboxTableViewer = new CheckboxTableViewer(packagesTable);
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

    packagesTable.setLinesVisible(true);
    packagesTable.setLayout(new TableLayout());

    ePackagesCheckboxTableViewer.addCheckStateListener(new ICheckStateListener()
      {
        public void checkStateChanged(CheckStateChangedEvent event)
        {
          ePackageCheckStateChanged();
        }
      });

    adjustEPackagesTableViewer(ePackagesCheckboxTableViewer);
  }

  protected void addEPackagesTableColumns(CheckboxTableViewer ePackagesTableViewer)
  {
    ePackagesTableViewer.setColumnProperties(new String []{ "a", "b" });

    Table table = ePackagesTableViewer.getTable();
    table.setHeaderVisible(true);

    TableLayout tableLayout = (TableLayout)table.getLayout();

    TableColumn packageColumn = new TableColumn(table, SWT.NONE);
    tableLayout.addColumnData(new ColumnWeightData(1, true));
    packageColumn.setText(getEPackageColumnLabel());
    packageColumn.setResizable(true);

    TableColumn dataColumn = new TableColumn(table, SWT.NONE);
    tableLayout.addColumnData(new ColumnWeightData(1, true));
    dataColumn.setText(getEPackageDataColumnLabel());
    dataColumn.setResizable(true);
  }

  protected void adjustEPackagesTableViewer(CheckboxTableViewer ePackagesTableViewer)
  {
    AdapterFactory adapterFactory = new AdapterFactoryImpl();
    ePackagesTableViewer.setContentProvider(getEPackagesTableViewerContentProvider(adapterFactory));
    ePackagesTableViewer.setLabelProvider(getEPackagesTableViewerLabelProvider(adapterFactory));
    addEPackagesTableColumns(ePackagesTableViewer);
  }

  protected ExtendedTableEditor createEPackageDataColumnTableEditor()
  {
    return new ExtendedTableEditor(ePackagesCheckboxTableViewer.getTable())
    {
      protected void editItem(final TableItem tableItem, final int index)
      {
        if (index == ePackageDataTableColumn)
        {
          final String string = tableItem.getText(index);
          horizontalAlignment = SWT.LEFT;
          minimumWidth = Math.max(50, tableItem.getBounds(index).width);

          final Text text = new Text(table, SWT.NONE);
          setEditor(text, tableItem, index);
          text.setFocus();
          text.setText(string);
          text.setSelection(0, string.length());
          if (tableItem.getChecked()) validateEPackageData((EPackage)tableItem.getData(), string);

          text.addFocusListener(new FocusAdapter()
            {
              public void focusLost(FocusEvent event)
              {
                modify(tableItem, index, text);
              }
            });

          text.addKeyListener(new KeyAdapter()
            {
              public void keyPressed(KeyEvent event)
              {
                if (event.character == '\r' || event.character == '\n')
                {
                  modify(tableItem, index, text);
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
                if (index == 1)
                {
                  if (tableItem.getChecked()) validateEPackageData((EPackage)tableItem.getData(), text.getText());
                }
              }
            });

          isCellEditing = true;
          setPageComplete(false);
        }
      }

      protected void modify(TableItem tableItem, int column, Text text)
      {
        String value = text.getText();
        tableItem.setText(column, value);
        text.setVisible(false);
        
        setEPackageData((EPackage)tableItem.getData(), value);

        isCellEditing = false;
        validate();
        setPageComplete(isPageComplete());
      }
    };    
  }
  
  protected boolean validateEPackageData(EPackage ePackage, String data)
  {
    return true;
  }
  
  protected void setEPackageData(EPackage ePackage, String data)
  {
  }

  protected String getEPackageData(EPackage ePackage)
  {
    return null;
  }

  protected IContentProvider getEPackagesTableViewerContentProvider(AdapterFactory adapterFactory)
  {
    return new AdapterFactoryContentProvider(adapterFactory);
  }

  protected ILabelProvider getEPackagesTableViewerLabelProvider(AdapterFactory adapterFactory)
  {
    return new AdapterFactoryLabelProvider(adapterFactory)
    {
      public Image getColumnImage(Object o, int columnIndex)
      {
        switch (columnIndex)
        {
          case 0:
          {
            return getEPackageImage();
          }
          default:
          {
            return null;
          }
        }
      }

      public String getColumnText(Object o, int columnIndex)
      {
        EPackage ePackage = (EPackage)o;
        String text = columnIndex == 0 ?
          getLabel(ePackage) :
          columnIndex == ePackageDataTableColumn ?
            getEPackageData(ePackage) :
            null;
          
        return text == null ? "" : text;
      }
    };
  }
  
  protected String getLabel(EPackage ePackage)
  {
    GenPackage genPackage = getModelConverter().getGenModel().findGenPackage(ePackage);
    if (genPackage != null)
    {
      return getLabel(genPackage);
    }

    return supportsNestedPackages() ?
      ConverterUtil.getQualifiedName(ePackage) :
      ePackage.getName();
  }

  protected void ePackageCheckStateChanged()
  {
    validate();
    getContainer().updateButtons();

    Set checkedElements = new HashSet(Arrays.asList(ePackagesCheckboxTableViewer.getCheckedElements()));
    for (Iterator i = getModelConverter().getEPackages().iterator(); i.hasNext();)
    {
      EPackage ePackage = (EPackage)i.next();
      getModelConverter().getEPackageConvertInfo(ePackage).setConvert(checkedElements.contains(ePackage));
    }
  }

  public List getCheckedEPackages()
  {
    if (ePackagesCheckboxTableViewer != null)
    {
      return Arrays.asList(ePackagesCheckboxTableViewer.getCheckedElements());
    }
    else
    {
      return Collections.EMPTY_LIST;
    }
  }

  public List getCheckedReferencedGenPackages()
  {
    if (referencedGenModelsCheckboxTreeViewer != null)
    {
      List genPackages = new ConverterUtil.GenPackageList();
      Object[] checkedElements = referencedGenModelsCheckboxTreeViewer.getCheckedElements();
      for (int i = 0; i < checkedElements.length; i++)
      {
        if (checkedElements[i] instanceof GenPackage)
        {
          genPackages.add(checkedElements[i]);
        }
      }
      return genPackages;
    }
    else
    {
      return Collections.EMPTY_LIST;
    }
  }
  
  protected String getEPackageColumnLabel()
  {
    return ConverterPlugin.INSTANCE.getString("_UI_Package_label");
  }

  protected String getEPackageDataColumnLabel()
  {
    return ConverterPlugin.INSTANCE.getString("_UI_EPackageData_label");
  }

  protected String getDeselectAllLabel()
  {
    return ConverterPlugin.INSTANCE.getString("_UI_DeselectAll_label");
  }

  protected String getSelectAllLabel()
  {
    return ConverterPlugin.INSTANCE.getString("_UI_SelectAll_label");
  }

  protected String getPackagesLabel()
  {
    return ConverterPlugin.INSTANCE.getString("_UI_Packages_label");
  }

  protected Image getEPackageImage()
  {
    return ExtendedImageRegistry.INSTANCE.getImage(new GenBaseItemProvider.UnderlayedImage(
      GenModelEditPlugin.INSTANCE.getImage("full/obj16/EPackage")));
  }

  protected void createReferencedGenModelControl(Composite parent)
  {
    Composite composite = new Composite(parent, SWT.NONE);
    composite.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
    {
      GridLayout layout = new GridLayout(2, false);
      layout.marginLeft = -5;
      layout.marginRight = -5;
      layout.marginTop = -5;
      layout.marginBottom = -5;
      composite.setLayout(layout);
    }

    Label referencedGenModelsLabel = new Label(composite, SWT.LEFT);
    referencedGenModelsLabel.setText(getReferencedGenModelsLabel());
    referencedGenModelsLabel.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));

    Button referencedGenModelsBrowseButton = new Button(composite, SWT.PUSH);
    referencedGenModelsBrowseButton.setText(getBrowseButtonLabel());
    referencedGenModelsBrowseButton.setLayoutData(new GridData(GridData.HORIZONTAL_ALIGN_END));
    referencedGenModelsBrowseButton.addSelectionListener(new SelectionAdapter()
      {
        public void widgetSelected(SelectionEvent event)
        {
          referencedGenModelsBrowseSelected(referencedGenModelsCheckboxTreeViewer);
        }
      });

    Tree referencedGenModelsTree = new Tree(parent, SWT.CHECK | SWT.BORDER | SWT.SINGLE);
    referencedGenModelsTree.setLayoutData(new GridData(GridData.FILL_BOTH | GridData.GRAB_HORIZONTAL));
    referencedGenModelsTree.setLinesVisible(true);

    referencedGenModelsCheckboxTreeViewer = new CheckboxTreeViewer(referencedGenModelsTree);
    referencedGenModelsCheckboxTreeViewer.addCheckStateListener(new ICheckStateListener()
      {
        public void checkStateChanged(CheckStateChangedEvent event)
        {
          referencedGenModelsCheckboxTreeViewerCheckStateChanged(event);
        }
      });

    adjustReferencedGenModelsTreeViewer(referencedGenModelsCheckboxTreeViewer);
  }

  protected void addReferencedGenModelsTreeColumns(CheckboxTreeViewer referencedGenModelsTreeViewer)
  {
  }
  
  protected boolean supportsNestedPackages()
  {
    return false;
  }

  protected void adjustReferencedGenModelsTreeViewer(CheckboxTreeViewer referencedGenModelsTreeViewer)
  {
    GenModelItemProviderAdapterFactory genModelItemProviderAdapterFactory = new GenModelItemProviderAdapterFactory()
      {
        public Adapter createGenModelAdapter()
        {
          if (genModelItemProvider == null)
          {
            genModelItemProvider = new GenModelItemProvider(this)
              {
                public Collection getChildrenFeatures(Object object)
                {
                  return Collections.singleton(GenModelPackage.Literals.GEN_MODEL__GEN_PACKAGES);
                }
              };
          }
          return genModelItemProvider;
        }
        
        public Adapter createGenPackageAdapter()
        {
          if (genPackageItemProvider == null)
          {
            genPackageItemProvider = new GenPackageItemProvider(this)
              {
                public Collection getChildrenFeatures(Object object)
                {
                  return supportsNestedPackages() ?
                    Collections.singleton(GenModelPackage.Literals.GEN_PACKAGE__NESTED_GEN_PACKAGES) :
                    Collections.EMPTY_SET;
                }
              };
          }
          return genPackageItemProvider;
        }
      };

    // Sorting only the genModels
    referencedGenModelsTreeViewer.setSorter(new ViewerSorter()
      {
        public void sort(Viewer viewer, Object[] elements)
        {
          if (elements.length > 0 && elements[0] instanceof GenModel)
          {
            super.sort(viewer, elements);
          }
        }
      });

    referencedGenModelsTreeViewer.setContentProvider(getReferencedGenModelsTreeViewerContentProvider(genModelItemProviderAdapterFactory));
    referencedGenModelsTreeViewer.setLabelProvider(getReferencedGenModelsTreeViewerLabelProvider(genModelItemProviderAdapterFactory));

    addReferencedGenModelsTreeColumns(referencedGenModelsCheckboxTreeViewer);
    addReferencedGenModelsTreeCheckStateManager(referencedGenModelsCheckboxTreeViewer.getTree());
  }
  
  protected void addReferencedGenModelsTreeCheckStateManager(Tree tree)
  {
    new ConverterUIUtil.TreeCheckStateManager(tree);
  }

  protected IContentProvider getReferencedGenModelsTreeViewerContentProvider(AdapterFactory adapterFactory)
  {
    return new AdapterFactoryContentProvider(adapterFactory);
  }

  protected ILabelProvider getReferencedGenModelsTreeViewerLabelProvider(AdapterFactory adapterFactory)
  {
    return new AdapterFactoryLabelProvider(adapterFactory)
    {
      public String getText(Object element)
      {
        return getLabel(element, super.getText(element));
      }
    };
  }
  
  /**
   * Returns the label of a given object.
   */
  protected String getLabel(Object object, String defaultText)
  {
    if (object instanceof GenModel)
    {
      GenModel genModel = (GenModel)object;
      Resource resource = genModel.eResource();
      URI uri = resource != null ? resource.getURI() : null;
      if (uri != null)
      {
        String location = uri.toString();
  
        if ("platform".equals(uri.scheme()) && uri.segmentCount() > 1)
        {
          boolean plugin = "plugin".equals(uri.segment(0));
          String type = ConverterPlugin.INSTANCE.getString(plugin ? "_UI_PlatformPlugin_label" : "_UI_PlatformResource_label");
          location = ConverterPlugin.INSTANCE.getString("_UI_PlatformLocation_label", new Object [] { type, uri.segment(1) });
        }
        return ConverterPlugin.INSTANCE.getString("_UI_ReferencedGenModel_label", new Object [] { defaultText, location });
      }
    }
    else if (object instanceof GenPackage)
    {
      return getLabel((GenPackage)object);
    }
    return defaultText;
  }
  
  protected String getLabel(GenPackage genPackage)
  {
    return genPackage.getInterfacePackageName();
  }
  
  protected void referencedGenModelsBrowseSelected(CheckboxTreeViewer treeViewer)
  {
    IPath path = null;
    Resource resource = getModelConverter().getGenModel().eResource();
    if (resource != null)
    {
      String stringURI = resource.getURI().toString();
      if (stringURI.startsWith("platform:/resource/"));
      {
        path = new Path(stringURI.substring("platform:/resource/".length())).makeAbsolute();
      }
    }
    final IPath genModelPath = path;
    
    ViewerFilter genModelFilter = new ViewerFilter()
    {
      public boolean select(Viewer viewer, Object parentElement, Object element)
      {
        if (element instanceof IFile)
        {
          IFile file = (IFile)element;
          return "genmodel".equals(file.getFileExtension())
            && !file.getFullPath().equals(genModelPath);
        }
        return true;
      }
    };
    
    IFile[] files = WorkspaceResourceDialog.openFileSelection(getShell(), 
      null, ConverterPlugin.INSTANCE.getString("_UI_SelectGenModel_message"), 
      true, null, Collections.singletonList(genModelFilter)); 
         
    if (files.length > 0)
    {
      ResourceSet referencedGenModels = getModelConverter().createResourceSet();
      List genModels = new UniqueEList.FastCompare();
      for (int i = 0; i < files.length; ++i)
      {
        URI genModelURI = URI.createPlatformResourceURI(files[i].getFullPath().toString(), true);
        Resource genModelResource = referencedGenModels.getResource(genModelURI, true);
        GenModel genModel = (GenModel)genModelResource.getContents().get(0);
        genModels.add(genModel);
      }
      addExternalGenModels(genModels);

      treeViewer.getTree().deselectAll();
      treeViewer.setInput(new ItemProvider(genModels));
      for (Iterator i = genModels.iterator(); i.hasNext();)
      {
        treeViewer.expandToLevel(i.next(), AbstractTreeViewer.ALL_LEVELS);
      }
      referencedGenModelsCheckboxTreeViewerCheckStateChanged(null);
    }
  }

  /**
   * This method is invoked when one element in the referenced GenModels tree is
   * checked or unchecked.  The event argument can be <tt>null</tt>.
   */
  protected void referencedGenModelsCheckboxTreeViewerCheckStateChanged(CheckStateChangedEvent event)
  {
    if (event == null || event.getElement() instanceof GenPackage)
    {
      filterEPackagesTable(false);
      validate();
      getContainer().updateButtons();
    }
  }

  protected String getBrowseButtonLabel()
  {
    return ConverterPlugin.INSTANCE.getString("_UI_Browse_label");
  }

  protected String getReferencedGenModelsLabel()
  {
    return ConverterPlugin.INSTANCE.getString("_UI_ReferencedGeneratorModels_label");
  }

  protected String getSelectGenModelDialogMessage()
  {
    return ConverterPlugin.INSTANCE.getString("_UI_SelectAllGeneratorModels_description");
  }

  protected void validate()
  {
    String message = null;

    Map nsURIToGenPackage = new HashMap();
    List referencedGenPackages = getModelConverter().getReferencedGenPackages();
    for (Iterator i = referencedGenPackages.iterator(); i.hasNext();)
    {
      GenPackage genPackage = (GenPackage)i.next();
      GenPackage previousGenPackage = (GenPackage)nsURIToGenPackage.put(genPackage.getNSURI(), genPackage);
      if (previousGenPackage != null)
      {
        message = ConverterPlugin.INSTANCE.getString("_UI_SameReferencedNSURI_error", new Object []{getLabel(previousGenPackage), getLabel(genPackage)});
        break;
      }
    }
    
    if (message == null)
    {
      List tableCheckedEPackages = getCheckedEPackages();
  
      List referencedEPackages = new ConverterUtil.EPackageList();
      for (Iterator i = getModelConverter().getEPackages().iterator(); i.hasNext();)
      {
        EPackage ePackage = (EPackage)i.next();
        if (tableCheckedEPackages.contains(ePackage) || !filteredEPackages.contains(ePackage))
        {
          referencedEPackages.addAll(ConverterUtil.computeRequiredPackages(ePackage));
        }
      }
  
      for (Iterator i = referencedEPackages.iterator(); i.hasNext();)
      {
        EPackage ePackage = (EPackage)i.next();
        while (ePackage.getESuperPackage() != null)
        {
          ePackage = ePackage.getESuperPackage();
        }
        if (!tableCheckedEPackages.contains(ePackage) && filteredEPackages.contains(ePackage))
        {
          message = ConverterPlugin.INSTANCE.getString("_UI_PackageIsUsedBySelectedPackage_message", new Object []{getLabel(ePackage)});
          break;
        }
      }
    }
    
    setErrorMessage(message);
  }

  protected void filterEPackagesTable(boolean reloadReferencedGenPackagesTable)
  {
    List genPackagesToCheck = null;
    if (referencedGenModelsCheckboxTreeViewer != null)
    {
      if (reloadReferencedGenPackagesTable)
      {
        if (!getModelConverter().getReferencedGenPackages().isEmpty() || !getModelConverter().getExternalGenModels().isEmpty())
        {
          List genModels = new UniqueEList.FastCompare();
          genPackagesToCheck = new ConverterUtil.GenPackageList();
          for (Iterator i = getModelConverter().getReferencedGenPackages().iterator(); i.hasNext();)
          {
            GenPackage genPackage = (GenPackage)i.next();
            genModels.add(genPackage.getGenModel());

            ModelConverter.ReferencedGenPackageConvertInfo genPackageInfo = 
              getModelConverter().getReferenceGenPackageConvertInfo(genPackage);
            if (genPackageInfo.isValidReference())
            {
              genPackagesToCheck.add(genPackage);
            }
          }

          addExternalGenModels(genModels);
          referencedGenModelsCheckboxTreeViewer.setInput(new ItemProvider(genModels));
          referencedGenModelsCheckboxTreeViewer.expandAll();
        }
      }
      else
      {
        getModelConverter().getReferencedGenPackages().clear();
        Object[] checkedElements = referencedGenModelsCheckboxTreeViewer.getCheckedElements();
        for (int i = 0; i < checkedElements.length; i++)
        {
          if (checkedElements[i] instanceof GenPackage)
          {
            GenPackage genPackage = (GenPackage)checkedElements[i];
            getModelConverter().getReferencedGenPackages().add(genPackage);
            getModelConverter().getReferenceGenPackageConvertInfo(genPackage).setValidReference(true);
          }
        }
        referencedGenModelsCheckboxTreeViewer.refresh();
        genPackagesToCheck = getModelConverter().getReferencedGenPackages();
      }
    }
    
    genPackagesToCheck = getReferencedGenPackagesToCheck(genPackagesToCheck == null ? Collections.EMPTY_LIST : genPackagesToCheck, reloadReferencedGenPackagesTable);
    if (genPackagesToCheck.isEmpty())
    {
      filteredEPackages = getModelConverter().getEPackages();
    }
    else
    {
      if (reloadReferencedGenPackagesTable)
      {
        referencedGenModelsCheckboxTreeViewer.setCheckedElements(genPackagesToCheck.toArray());
        referencedGenModelsCheckboxTreeViewer.setSelection(new StructuredSelection(genPackagesToCheck.toArray()), true);
      }
      filteredEPackages = getModelConverter().filterReferencedEPackages(getModelConverter().getEPackages(), genPackagesToCheck);
    }

    List checkedEPackages = new ArrayList();
    for (Iterator i = getModelConverter().getEPackages().iterator(); i.hasNext();)
    {
      EPackage ePackage = (EPackage)i.next();
      ModelConverter.EPackageConvertInfo ePackageInfo = getModelConverter().getEPackageConvertInfo(ePackage);
      if (filteredEPackages.contains(ePackage) && ePackageInfo.isConvert())
      {
        checkedEPackages.add(ePackage);
      }
      else
      {
        ePackageInfo.setConvert(false);
      }
    }
    ePackagesCheckboxTableViewer.setInput(new ItemProvider(filteredEPackages));
    ePackagesCheckboxTableViewer.setCheckedElements(checkedEPackages.toArray());
  }
  
  /**
   * Returns a not null list with the GenPackages that should be selected 
   * in the Rereferenced Tree Viewer.  The list passed in the genPackage
   * is not null and contains all GenPackages that can be marked.  This list
   * can be both changed and returned. 
   */
  protected List getReferencedGenPackagesToCheck(List genPackages, boolean reloadReferencedGenPackagesTable)
  {
    return genPackages;
  }  
  
  protected void addExternalGenModels(List genModels)
  {    
    List externalGenModels = new ArrayList(getModelConverter().getExternalGenModels());
    if (!externalGenModels.isEmpty())
    {
      GenModel exporterGenModel = getModelConverter().getGenModel();
      boolean hasExporterGenModel = exporterGenModel != null && genModels.contains(exporterGenModel);
      if (!hasExporterGenModel)
      {
        genModels.add(exporterGenModel);
      }
      
      for (Iterator i = genModels.iterator(); i.hasNext();)
      {
        GenModel genModel = (GenModel)i.next();
        for (Iterator j = externalGenModels.iterator(); j.hasNext();)
        {
          GenModel externalGenModel = (GenModel)j.next();
          if (genModel == externalGenModel)
          {
            j.remove();
          }
          else
          {
            URI uri = genModel.eResource() != null ?
              genModel.eResource().getURI() :
              null;
              
            if (uri != null)
            {
              URI externalURI = externalGenModel.eResource() != null ?
                externalGenModel.eResource().getURI() :
                null;
                
              if (uri.equals(externalURI))
              {
                j.remove();
              }
            }
          }
        }
      }
      genModels.addAll(externalGenModels);
      
      if (!hasExporterGenModel)
      {
        genModels.remove(exporterGenModel);
      }
    }
  }
}
