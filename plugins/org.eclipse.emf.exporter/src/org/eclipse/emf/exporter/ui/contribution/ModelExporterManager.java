/**
 * Copyright (c) 2005-2006 IBM Corporation and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: 
 *   IBM - Initial API and implementation
 */
package org.eclipse.emf.exporter.ui.contribution;

import org.eclipse.jface.wizard.IWizard;
import org.eclipse.ui.IWorkbenchWizard;

import org.eclipse.emf.converter.ui.contribution.ModelConverterManager;
import org.eclipse.emf.exporter.ExporterPlugin;

/**
 * @since 2.2.0
 */
public class ModelExporterManager extends ModelConverterManager<ModelExporterDescriptor>
{
  public final static ModelExporterManager INSTANCE = new ModelExporterManager();
  
  public static class ModelExporterDescriptorImpl extends ModelConverterDescriptorImpl implements ModelExporterDescriptor
  {
    public IWorkbenchWizard createWizard()
    {
      Object object = createExecutableExtension("wizard");
      return object instanceof IWorkbenchWizard ? (IWorkbenchWizard)object : null;
    }
  }
  
  public static class ModelExporterDescriptorWizardNode extends ModelConverterDescriptorWizardNode
  {
    public ModelExporterDescriptorWizardNode(ModelExporterDescriptor descriptor)
    {
      super(descriptor);
    }
    
    @Override
    protected IWizard createWizard()
    {
      return ((ModelExporterDescriptor)descriptor).createWizard();
    }
  }
  
  @Override
  protected String getPluginId()
  {
    return ExporterPlugin.ID;
  }
  
  @Override
  protected String getExtensionPointId()
  {
    return "modelExporterDescriptors";
  }

  @Override
  protected String getElementName()
  {
    return "modelExporterDescriptor";
  }

  @Override
  protected ModelConverterDescriptorImpl createModelConverterDescriptorImpl()
  {
    return new ModelExporterDescriptorImpl();
  }

  @Override
  protected ModelConverterDescriptorWizardNode createModelConverterDescriptorWizardNode(ModelExporterDescriptor descriptor)
  {
    return new ModelExporterDescriptorWizardNode(descriptor);
  }
  
  public ModelExporterDescriptor getModelExporterDescriptor(String id)
  {
    return getModelConverterDescriptor(id);
  }  
}
