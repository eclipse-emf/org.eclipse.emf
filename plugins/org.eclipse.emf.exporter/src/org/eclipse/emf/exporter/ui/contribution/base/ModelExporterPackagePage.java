/**
 * Copyright (c) 2005-2006 IBM Corporation and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: 
 *   IBM - Initial API and implementa
 *   tion
 */

package org.eclipse.emf.exporter.ui.contribution.base;


import java.util.Iterator;
import java.util.List;

import org.eclipse.jface.viewers.CheckStateChangedEvent;
import org.eclipse.jface.viewers.CheckboxTableViewer;
import org.eclipse.jface.viewers.CheckboxTreeViewer;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CCombo;
import org.eclipse.swt.custom.TreeEditor;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.swt.widgets.TreeColumn;
import org.eclipse.swt.widgets.TreeItem;
import org.eclipse.swt.widgets.Widget;

import org.eclipse.emf.codegen.ecore.genmodel.GenPackage;
import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.common.util.Diagnostic;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.converter.ui.contribution.base.ModelConverterPackagePage;
import org.eclipse.emf.converter.util.ConverterUIUtil;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.edit.ui.provider.AdapterFactoryLabelProvider;
import org.eclipse.emf.exporter.ExporterPlugin;
import org.eclipse.emf.exporter.ModelExporter;
import org.eclipse.emf.exporter.ModelExporter.ReferencedGenPackageExportInfo;
import org.eclipse.emf.exporter.ui.contribution.ModelExporterDescriptor;
import org.eclipse.emf.exporter.ui.contribution.ModelExporterManager;
import org.eclipse.emf.exporter.util.ExporterUIUtil;


/**
 * @since 2.2.0
 */
public class ModelExporterPackagePage extends ModelConverterPackagePage implements IModelExporterPage
{      
  public class ReferencedGenPackageTreeEditorHelper extends ExporterUIUtil.TreeEditorHelper
  {
    public ReferencedGenPackageTreeEditorHelper(Tree tree)
    {
      super(tree);
    }
    
    @Override
    public void handleEvent(Event event)
    {
      if (event.widget instanceof CCombo)
      {
        comboSelected(event);
      }
      else
      {
        super.handleEvent(event);
      }
    }
    
    protected void comboSelected(Event event)
    {
      CCombo combo = (CCombo)event.widget;
      TreeItem treeItem = ((TreeEditor)compositeEditor).getItem();
      String text = combo.getItem(combo.getSelectionIndex());
      treeItem.setText(getColumn(), text);
      
      boolean validate = true;
      Object[] itemData = (Object[])combo.getData(text);
      if (itemData != null)
      {
        String exporterID = (String)itemData[0];
        URI artifactURI = (URI)itemData[1];

        GenPackage genPackage = (GenPackage)combo.getData();
        ModelExporter.ReferencedGenPackageExportInfo genPackageInfo = getModelExporter().getReferencedGenPackageExportInfo(genPackage);
        genPackageInfo.setModelExporterID(exporterID);
        genPackageInfo.setArtifactURI(artifactURI);
        if (treeItem.getChecked() != (artifactURI != null))
        {
          referencedGenModelsCheckboxTreeViewerCheckStateChanged(null);
          validate = false;
        }
      }
      
      if (validate)
      {
        validate();
        getContainer().updateButtons();
      }
    }
    
    @Override
    protected Control createEditorControl(Widget item)
    {
      TreeItem treeItem = (TreeItem)item;
      if (treeItem != null)
      {
        Object data = treeItem.getData();
        if (data instanceof GenPackage)
        {
          GenPackage genPackage = (GenPackage)data;
          ModelExporter.ReferencedGenPackageExportInfo genPackageInfo = getModelExporter().getReferencedGenPackageExportInfo(genPackage);
          if (genPackageInfo.isValidReference())
          {
            CCombo combo = new CCombo(treeItem.getParent(), SWT.READ_ONLY | SWT.FLAT);
            combo.addListener(SWT.Selection, this);
            initializeCombo(combo, genPackage);
            return combo;
          }
        }
      }
      
      return null;
    }
    
    protected void initializeCombo(CCombo combo, GenPackage genPackage)
    {
      combo.setData(genPackage);
      String exporterID = getModelExporter().getID();
      for (URI uri : getModelExporter().getArtifactURIs(genPackage))
      {
        String item = getArtifactText(exporterID, uri);
        combo.setData(item, new Object[]{exporterID, uri});
        combo.add(item);
      }

      ModelExporter.ReferencedGenPackageExportInfo genPackageInfo = getModelExporter().getReferencedGenPackageExportInfo(genPackage);
      if (genPackageInfo.getArtifactURI() != null)
      {
        combo.setText(getArtifactText(genPackageInfo.getModelExporterID(), genPackageInfo.getArtifactURI()));
      }
    }
  }
  
  public ModelExporterPackagePage(ModelExporter modelExporter, String pageName)
  {
    super(modelExporter, pageName);
    setDescription(ExporterPlugin.INSTANCE.getString("_UI_PackageSelection_description"));
  }
  
  public ModelExporter getModelExporter()
  {
    return (ModelExporter)getModelConverter();
  }

  @Override
  protected void adjustEPackagesTableViewer(CheckboxTableViewer ePackagesTableViewer)
  {
    super.adjustEPackagesTableViewer(ePackagesTableViewer);
    createEPackagesTableEditor();
  }

  @Override
  protected boolean validateEPackageData(EPackage ePackage, String data)
  {
    Diagnostic diagnostic = getModelExporter().checkEPackageArtifactLocation(data, getLabel(ePackage));
    handleDiagnostic(diagnostic);
    return diagnostic.getSeverity() == Diagnostic.OK;    
  }
  
  @Override
  protected void setEPackageData(EPackage ePackage, String data)
  {
    ModelExporter.EPackageExportInfo ePackageInfo = getModelExporter().getEPackageExportInfo(ePackage);
    ePackageInfo.setArtifactLocation(data);
  }
  
  @Override
  protected String getEPackageData(EPackage ePackage)
  {
    return getModelExporter().getEPackageExportInfo(ePackage).getArtifactLocation();
  }
  
  @Override
  protected String getEPackageDataColumnLabel()
  {
    return ExporterPlugin.INSTANCE.getString("_UI_ArtifactName_label");
  }

  @Override
  protected void addReferencedGenModelsTreeColumns(CheckboxTreeViewer referencedGenModelsTreeViewer)
  {
    Tree tree = referencedGenModelsTreeViewer.getTree();
    tree.setHeaderVisible(true);

    TreeColumn genObjectColumn = new TreeColumn(tree, SWT.NONE);
    genObjectColumn.setText(ExporterPlugin.INSTANCE.getString("_UI_GenObjectsColumn_title"));
    genObjectColumn.setResizable(true);
    genObjectColumn.setWidth(250);

    TreeColumn artifactColumn = new TreeColumn(tree, SWT.NONE);
    artifactColumn.setText(ExporterPlugin.INSTANCE.getString("_UI_ExporterAndArtifactColumn_title"));
    artifactColumn.setResizable(true);
    artifactColumn.setWidth(200);
    
    ReferencedGenPackageTreeEditorHelper treeEditorHelper = new ReferencedGenPackageTreeEditorHelper(tree);
    treeEditorHelper.setColumn(1);
  }
  
  @Override
  protected void addReferencedGenModelsTreeCheckStateManager(Tree tree)
  {
    new ConverterUIUtil.TreeCheckStateManager(tree)
    {
      @Override
      protected void setCheck(TreeItem item, boolean check)
      {
        Object data = item.getData();
        if (!(data instanceof GenPackage) ||
            getModelExporter().getReferencedGenPackageExportInfo((GenPackage)data).isValidReference())
        {
          super.setCheck(item, check);
        }
      }
    };
  }

  @Override
  protected void referencedGenModelsCheckboxTreeViewerCheckStateChanged(CheckStateChangedEvent event)
  {
    if (event != null && event.getChecked())
    {
      Object element = event.getElement();
      if (element instanceof GenPackage)
      {
        GenPackage genPackage = (GenPackage)element;
        ReferencedGenPackageExportInfo genPackageInfo = getModelExporter().getReferencedGenPackageExportInfo(genPackage);
        if (!genPackageInfo.isValidReference())
        {
          ((CheckboxTreeViewer)event.getSource()).setChecked(genPackage, false);
          return;
        }
      }
    }
    super.referencedGenModelsCheckboxTreeViewerCheckStateChanged(event);
  }
  
  @Override
  protected boolean supportsNestedPackages()
  {
    return true;
  }

  @Override
  protected ILabelProvider getReferencedGenModelsTreeViewerLabelProvider(AdapterFactory adapterFactory)
  {
    return new AdapterFactoryLabelProvider(adapterFactory)
      {
        @Override
        public String getColumnText(Object object, int columnIndex)
        {
          if (columnIndex == 0)
          {
            return getLabel(object, super.getColumnText(object, 0));
          }
          else if (object instanceof GenPackage)
          {
            GenPackage genPackage = (GenPackage)object;
            ModelExporter.ReferencedGenPackageExportInfo genPackageInfo = getModelExporter().getReferencedGenPackageExportInfo(genPackage);
            if (genPackageInfo.isValidReference())
            {
              return getArtifactText(genPackageInfo.getModelExporterID(), genPackageInfo.getArtifactURI()); 
            }
            else
            {
              return ExporterPlugin.INSTANCE.getString("_UI_NotAvailable_text");
            }
          }
          else
          {
            return "";            
          }
        }

        @Override
        public Image getColumnImage(Object object, int columnIndex)
        {
          return columnIndex == 0 ? super.getColumnImage(object, columnIndex) : null;
        }
      };
  }

  protected String getArtifactText(String modelExporterId, URI artifactURI)
  {
    if (artifactURI != null)
    {
      String modelExporterLabel = artifactURI.toString();
      
      if (modelExporterId != null)
      {
        ModelExporterDescriptor modelExporterDescriptor = ModelExporterManager.INSTANCE.getModelExporterDescriptor(modelExporterId);
        modelExporterLabel = 
          (modelExporterDescriptor != null ? modelExporterDescriptor.getName() : modelExporterId) + 
          " - " + modelExporterLabel;      
      }
      return modelExporterLabel;
    }
    else
    {
      return "";
    }
  }
  
  @Override
  protected void validate()
  {
    super.validate();
    
    if (getErrorMessage() == null)
    {
      List<EPackage> tableCheckedEPackages = getCheckedEPackages();
      for (EPackage ePackage : tableCheckedEPackages)
      {
        if ( !validateEPackageData(ePackage, getModelExporter().getEPackageExportInfo(ePackage).getArtifactLocation()))
        {
          return;
        }
      }
            
      List<GenPackage> tableCheckedGenPackages = getCheckedReferencedGenPackages();
      for (GenPackage genPackage : tableCheckedGenPackages)
      {
        if (getModelExporter().getReferencedGenPackageExportInfo(genPackage).getArtifactURI() == null)
        {
          setErrorMessage(ExporterPlugin.INSTANCE.getString("_UI_ReferencedGenPackageNoArtifact_error"));
          return;
        }
      }      
    }
  }
  
  @Override
  protected List<GenPackage> getReferencedGenPackagesToCheck(List<GenPackage> genPackages, boolean reloadReferencedGenPackagesTable)
  {
    if (reloadReferencedGenPackagesTable)
    {
      for (Iterator<GenPackage> i = genPackages.iterator(); i.hasNext();)
      {
        GenPackage genPackage = i.next();
        if (getModelExporter().getReferencedGenPackageExportInfo(genPackage).getArtifactURI() == null)
        {
          i.remove();
        }
      }
    }
    return genPackages;
  }
}