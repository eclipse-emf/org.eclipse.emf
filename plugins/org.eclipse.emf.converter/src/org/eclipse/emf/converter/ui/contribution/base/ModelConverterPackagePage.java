/**
 * <copyright>
 *
 * Copyright (c) 2005-2007 IBM Corporation and others.
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
 * $Id: ModelConverterPackagePage.java,v 1.13 2007/05/29 20:28:45 marcelop Exp $
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
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormLayout;
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
import org.eclipse.emf.ecore.EStructuralFeature;
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
  protected List<EPackage> filteredEPackages = new ConverterUtil.EPackageList();
  protected boolean showReferencedGenModels = false;
  protected boolean isCellEditing = false;

  protected ModelConverterPackagePage(ModelConverter modelConverter, String pageName)
  {
    super(modelConverter, pageName);

    setTitle(ConverterPlugin.INSTANCE.getString("_UI_PackageSelection_title"));
  }

  @Override
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

  @Override
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

  @Override
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
    composite.setLayoutData(new GridData(GridData.FILL_BOTH));
    {
      FormLayout layout = new FormLayout();
      layout.marginTop = 10;
      layout.spacing = 10;
      composite.setLayout(layout);
    }

    Label packagesLabel = new Label(composite, SWT.LEFT);
    packagesLabel.setText(getPackagesLabel());
    {
      FormData data = new FormData();
      data.left = new FormAttachment(0);
      packagesLabel.setLayoutData(data);
    }    

    Table packagesTable = new Table(composite, SWT.CHECK | SWT.BORDER | SWT.SINGLE);
    ePackagesCheckboxTableViewer = new CheckboxTableViewer(packagesTable);
    {
      FormData data = new FormData();
      data.top = new FormAttachment(packagesLabel, 5);
      data.bottom = new FormAttachment(100);
      data.left = new FormAttachment(0);
      data.right = new FormAttachment(100);
      data.height = 90;
      packagesTable.setLayoutData(data);
    }
    
    Composite selectionComposite = new Composite(composite, SWT.NONE);
    {
      FormData data = new FormData();
      data.top = new FormAttachment(packagesLabel, 0, SWT.CENTER);
      data.right = new FormAttachment(100);
      selectionComposite.setLayoutData(data);      

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
        @Override
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
        @Override
        public void widgetSelected(SelectionEvent event)
        {
          ePackagesCheckboxTableViewer.setCheckedElements(new Object [0]);
          ePackageCheckStateChanged();
        }
      });

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
      @Override
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
              @Override
              public void focusLost(FocusEvent event)
              {
                modify(tableItem, index, text);
              }
            });

          text.addKeyListener(new KeyAdapter()
            {
              @Override
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
    // Subclasses may override
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
      @Override
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

      @Override
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

    Set<Object> checkedElements = new HashSet<Object>(Arrays.asList(ePackagesCheckboxTableViewer.getCheckedElements()));
    for (EPackage ePackage : getModelConverter().getEPackages())
    {
      getModelConverter().getEPackageConvertInfo(ePackage).setConvert(checkedElements.contains(ePackage));
    }
  }

  @SuppressWarnings("unchecked")
  public List<EPackage> getCheckedEPackages()
  {
    return ePackagesCheckboxTableViewer != null ?
      (List<EPackage>)(List)Arrays.asList(ePackagesCheckboxTableViewer.getCheckedElements())
      :  Collections.<EPackage>emptyList();
  }

  public List<GenPackage> getCheckedReferencedGenPackages()
  {
    if (referencedGenModelsCheckboxTreeViewer != null)
    {
      List<GenPackage> genPackages = new ConverterUtil.GenPackageList();
      Object[] checkedElements = referencedGenModelsCheckboxTreeViewer.getCheckedElements();
      for (int i = 0; i < checkedElements.length; i++)
      {
        if (checkedElements[i] instanceof GenPackage)
        {
          genPackages.add((GenPackage)checkedElements[i]);
        }
      }
      return genPackages;
    }
    else
    {
      return Collections.emptyList();
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
    composite.setLayoutData(new GridData(GridData.FILL_BOTH));
    {
      FormLayout layout = new FormLayout();
      layout.marginTop = 10;
      layout.spacing = 10;
      composite.setLayout(layout);
    }

    Label referencedGenModelsLabel = new Label(composite, SWT.LEFT);
    referencedGenModelsLabel.setText(getReferencedGenModelsLabel());
    {
      FormData data = new FormData();
      data.left = new FormAttachment(0);
      referencedGenModelsLabel.setLayoutData(data);      
    }    

    Tree referencedGenModelsTree = new Tree(composite, SWT.CHECK | SWT.BORDER | SWT.SINGLE);
    {
      FormData data = new FormData();
      data.top = new FormAttachment(referencedGenModelsLabel, 5);
      data.bottom = new FormAttachment(100);
      data.left = new FormAttachment(0);
      data.right = new FormAttachment(100);
      referencedGenModelsTree.setLayoutData(data);
    }
    
    Button referencedGenModelsAddButton = new Button(composite, SWT.PUSH);
    referencedGenModelsAddButton.setText(getBrowseButtonLabel());
    {
      FormData data = new FormData();
      data.top = new FormAttachment(referencedGenModelsLabel, 0, SWT.CENTER);
      data.right = new FormAttachment(100);
      referencedGenModelsAddButton.setLayoutData(data);
    }
    referencedGenModelsAddButton.addSelectionListener(new SelectionAdapter()
      {
        @Override
        public void widgetSelected(SelectionEvent event)
        {
          referencedGenModelsBrowseSelected(referencedGenModelsCheckboxTreeViewer);
        }
      });

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
    // Subclasses may override
  }
  
  protected boolean supportsNestedPackages()
  {
    return false;
  }

  protected void adjustReferencedGenModelsTreeViewer(CheckboxTreeViewer referencedGenModelsTreeViewer)
  {
    GenModelItemProviderAdapterFactory genModelItemProviderAdapterFactory = new GenModelItemProviderAdapterFactory()
      {
        @Override
        public Adapter createGenModelAdapter()
        {
          if (genModelItemProvider == null)
          {
            genModelItemProvider = new GenModelItemProvider(this)
              {
                @Override
                public Collection<? extends EStructuralFeature> getChildrenFeatures(Object object)
                {
                  return Collections.singleton(GenModelPackage.Literals.GEN_MODEL__GEN_PACKAGES);
                }
              };
          }
          return genModelItemProvider;
        }
        
        @Override
        public Adapter createGenPackageAdapter()
        {
          if (genPackageItemProvider == null)
          {
            genPackageItemProvider = new GenPackageItemProvider(this)
              {
                @Override
                public Collection<EStructuralFeature> getChildrenFeatures(Object object)
                {
                  return supportsNestedPackages() ?
                    Collections.<EStructuralFeature>singleton(GenModelPackage.Literals.GEN_PACKAGE__NESTED_GEN_PACKAGES) :
                    Collections.<EStructuralFeature>emptyList();
                }
              };
          }
          return genPackageItemProvider;
        }
      };

    // Sorting only the genModels
    referencedGenModelsTreeViewer.setSorter(new ViewerSorter()
      {
        @Override
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
      @Override
      public String getColumnText(Object object, int columnIndex)
      {
        return getLabel(object, super.getColumnText(object, 0));
      }
      
      @Override
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
        boolean plugin = uri.isPlatformPlugin();
        if (plugin || uri.isPlatformResource())
        {
          String segment = URI.decode(uri.segment(1));
          location = ConverterPlugin.INSTANCE.getString(plugin ? "_UI_PlatformPlugin_label" : "_UI_PlatformResource_label", new String[]{segment});
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
  
  /**
   * @deprecated in 2.3.0.  Use {@link #referencedGenModelsAddSelected(CheckboxTreeViewer)} 
   * instead.  This method will be remove in future versions of the code.
   */
  @Deprecated
  protected void referencedGenModelsBrowseSelected(CheckboxTreeViewer treeViewer)
  {
    referencedGenModelsAddSelected(treeViewer);
  }
  
  protected void referencedGenModelsAddSelected(CheckboxTreeViewer treeViewer)
  {
    IPath path = null;
    Resource resource = getModelConverter().getGenModel().eResource();
    if (resource != null)
    {
      if (resource.getURI().isPlatformResource())
      {
        path = new Path(resource.getURI().toPlatformString(true)).makeAbsolute();
      }
    }
    final IPath genModelPath = path;

    ResourceSet resourceSet = null;
    List<GenModel> genModels = new UniqueEList.FastCompare<GenModel>();
    final Set<URI> genModelURIs = new HashSet<URI>();
    Object input = treeViewer.getInput();
    if (input instanceof ItemProvider)
    {
      Collection<?> elements = ((ItemProvider)input).getChildren();
      for (Object element : elements)
      {
        if (element instanceof GenModel)
        {
          GenModel genModel = (GenModel)element;
          URI genModelURI = genModel.eResource().getURI();
          genModels.add(genModel);
          genModelURIs.add(genModelURI);
          if (resourceSet == null && genModelURI.isPlatformResource())
          {
            resourceSet = genModel.eResource().getResourceSet();
          }
        }
      }
    }
        
    ViewerFilter genModelFilter = new ViewerFilter()
    {
      @Override
      public boolean select(Viewer viewer, Object parentElement, Object element)
      {
        if (element instanceof IFile)
        {
          IFile file = (IFile)element;
          URI uri = URI.createPlatformResourceURI(file.getFullPath().toString(), true);
          return "genmodel".equals(file.getFileExtension())
            && !file.getFullPath().equals(genModelPath)
            && !genModelURIs.contains(uri);
        }
        return true;
      }
    };
    
    IFile[] files = WorkspaceResourceDialog.openFileSelection(getShell(), 
      null, ConverterPlugin.INSTANCE.getString("_UI_SelectGenModel_message"), 
      true, null, Collections.singletonList(genModelFilter)); 
         
    if (files.length > 0)
    {
      ResourceSet referencedGenModels = resourceSet != null ?
        resourceSet :
        getModelConverter().createResourceSet();
      
      for (int i = 0; i < files.length; ++i)
      {
        URI genModelURI = URI.createPlatformResourceURI(files[i].getFullPath().toString(), true);
        Resource genModelResource = referencedGenModels.getResource(genModelURI, true);
        GenModel genModel = (GenModel)genModelResource.getContents().get(0);
        genModels.add(genModel);
      }
      
      addExternalGenModels(genModels);
      addReferencedGenModels(genModels);

      List<GenPackage> genPackagesToCheck = new ArrayList<GenPackage>();
      Object[] checkedElements = treeViewer.getCheckedElements();
      LOOP:
      for (int i = 0; i < checkedElements.length; i++)
      {
        if (checkedElements[i] instanceof GenPackage)
        {
          GenPackage checkedGenPackage = (GenPackage)checkedElements[i];
          String nsURI = checkedGenPackage.getNSURI();
          for (GenModel genModel : genModels)
          {
            for (GenPackage genPackage : genModel.getGenPackages())
            {
              if (nsURI.equals(genPackage.getNSURI()))
              {
                genPackagesToCheck.add(genPackage);
                continue LOOP;
              }
            }
          }
        }
      }
      treeViewer.getTree().deselectAll();
      treeViewer.cancelEditing();
      treeViewer.setInput(new ItemProvider(genModels));
      for (Iterator<?> i = genModels.iterator(); i.hasNext();)
      {
        treeViewer.expandToLevel(i.next(), AbstractTreeViewer.ALL_LEVELS);
      }
      treeViewer.setCheckedElements(genPackagesToCheck.toArray());
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

  protected String getAddButtonLabel()
  {
    return ConverterPlugin.INSTANCE.getString("_UI_Add_label");
  }

  /**
   * @deprecated in 2.3.0.  Use {@link #getAddButtonLabel()} instead.  This
   * method will be remove in future versions of the code.
   */
  @Deprecated
  protected String getBrowseButtonLabel()
  {
    return getAddButtonLabel();
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

    Map<String, GenPackage> nsURIToGenPackage = new HashMap<String, GenPackage>();
    List<GenPackage> referencedGenPackages = getModelConverter().getReferencedGenPackages();
    for (GenPackage genPackage : referencedGenPackages)
    {
      GenPackage previousGenPackage = nsURIToGenPackage.put(genPackage.getNSURI(), genPackage);
      if (previousGenPackage != null)
      {
        message = ConverterPlugin.INSTANCE.getString("_UI_SameReferencedNSURI_error", new Object []{getLabel(previousGenPackage), getLabel(genPackage)});
        break;
      }
    }
    
    if (message == null)
    {
      List<EPackage> tableCheckedEPackages = getCheckedEPackages();
  
      List<EPackage> referencedEPackages = new ConverterUtil.EPackageList();
      for (EPackage ePackage : getModelConverter().getEPackages())
      {
        if (tableCheckedEPackages.contains(ePackage) || !filteredEPackages.contains(ePackage))
        {
          referencedEPackages.addAll(ConverterUtil.computeRequiredPackages(ePackage));
        }
      }
  
      for (EPackage ePackage : referencedEPackages)
      {
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
    List<GenPackage> genPackagesToCheck = null;
    if (referencedGenModelsCheckboxTreeViewer != null)
    {
      if (reloadReferencedGenPackagesTable)
      {
        if (!getModelConverter().getReferencedGenPackages().isEmpty() || !getModelConverter().getExternalGenModels().isEmpty())
        {
          List<GenModel> genModels = new UniqueEList.FastCompare<GenModel>();
          genPackagesToCheck = new ConverterUtil.GenPackageList();
          for (GenPackage genPackage : getModelConverter().getReferencedGenPackages())
          {
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
    
    genPackagesToCheck = getReferencedGenPackagesToCheck(genPackagesToCheck == null ? Collections.<GenPackage>emptyList() : genPackagesToCheck, reloadReferencedGenPackagesTable);
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

    List<EPackage> checkedEPackages = new ArrayList<EPackage>();
    for (EPackage ePackage : getModelConverter().getEPackages())
    {
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
  protected List<GenPackage> getReferencedGenPackagesToCheck(List<GenPackage> genPackages, boolean reloadReferencedGenPackagesTable)
  {
    return genPackages;
  }  
  
  protected void addExternalGenModels(List<GenModel> genModels)
  {    
    List<GenModel> externalGenModels = new ArrayList<GenModel>(getModelConverter().getExternalGenModels());
    if (!externalGenModels.isEmpty())
    {
      GenModel exporterGenModel = getModelConverter().getGenModel();
      boolean hasExporterGenModel = exporterGenModel != null && genModels.contains(exporterGenModel);
      if (!hasExporterGenModel)
      {
        genModels.add(exporterGenModel);
      }
      
      for (GenModel genModel : genModels)
      {
        for (Iterator<GenModel> j = externalGenModels.iterator(); j.hasNext();)
        {
          GenModel externalGenModel = j.next();
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

  protected void addReferencedGenModels(List<GenModel> genModels)
  {
    // Subclasses may override
  }
}
