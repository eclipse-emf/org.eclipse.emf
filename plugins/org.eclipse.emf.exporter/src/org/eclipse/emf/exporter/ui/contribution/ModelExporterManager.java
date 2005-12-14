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
 * $Id: ModelExporterManager.java,v 1.1 2005/12/14 08:06:32 marcelop Exp $
 */
package org.eclipse.emf.exporter.ui.contribution;

import org.eclipse.jface.wizard.IWizard;
import org.eclipse.ui.IWorkbenchWizard;

import org.eclipse.emf.converter.ui.contribution.ModelConverterDescriptor;
import org.eclipse.emf.converter.ui.contribution.ModelConverterManager;
import org.eclipse.emf.exporter.ExporterPlugin;

/**
 * @since 2.2.0
 */
public class ModelExporterManager extends ModelConverterManager
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
    
    protected IWizard createWizard()
    {
      return ((ModelExporterDescriptor)descriptor).createWizard();
    }
  }
  
  protected String getPluginId()
  {
    return ExporterPlugin.ID;
  }
  
  protected String getExtensionPointId()
  {
    return "modelExporterDescriptors";
  }

  protected String getElementName()
  {
    return "modelExporterDescriptor";
  }

  protected ModelConverterDescriptorImpl createModelConverterDescriptorImpl()
  {
    return new ModelExporterDescriptorImpl();
  }

  protected ModelConverterDescriptorWizardNode createModelConverterDescriptorWizardNode(ModelConverterDescriptor descriptor)
  {
    return new ModelExporterDescriptorWizardNode((ModelExporterDescriptor)descriptor);
  }
  
  public ModelExporterDescriptor getModelExporterDescriptor(String id)
  {
    return (ModelExporterDescriptor)getModelConverterDescriptor(id);
  }  
}
