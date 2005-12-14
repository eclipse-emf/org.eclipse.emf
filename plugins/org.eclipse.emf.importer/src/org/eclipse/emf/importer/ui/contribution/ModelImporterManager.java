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
 * $Id: ModelImporterManager.java,v 1.1 2005/12/14 07:48:48 marcelop Exp $
 */
package org.eclipse.emf.importer.ui.contribution;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.jface.wizard.IWizard;

import org.eclipse.emf.importer.ImporterPlugin;
import org.eclipse.emf.converter.ui.contribution.ModelConverterDescriptor;
import org.eclipse.emf.converter.ui.contribution.ModelConverterManager;

/**
 * @since 2.2.0
 */
public class ModelImporterManager extends ModelConverterManager
{
  public final static ModelImporterManager INSTANCE = new ModelImporterManager();
  
  public static class ModelImporterDescriptorImpl extends ModelConverterDescriptorImpl implements ModelImporterDescriptor
  {
    protected List extensions;
    protected int types = ModelImporterDescriptor.TYPE_DEFAULT;
    
    public IModelImporterWizard createWizard()
    {
      Object object = createExecutableExtension("wizard");
      return object instanceof IModelImporterWizard ? (IModelImporterWizard)object : null;
    }
    
    public int getTypes()
    {
      return types;
    }

    public void setTypes(int types)
    {
      this.types = types;
    }

    public List getExtensions()
    {
      if (extensions == null)
      {
        extensions = new ArrayList();
      }
      return extensions;
    }    
  }
  
  public static class ModelImporterDescriptorWizardNode extends ModelConverterDescriptorWizardNode
  {
    public ModelImporterDescriptorWizardNode(ModelImporterDescriptor descriptor)
    {
      super(descriptor);
    }
    
    protected IWizard createWizard()
    {
      return ((ModelImporterDescriptor)descriptor).createWizard();
    }
  }
  
  protected String getPluginId()
  {
    return ImporterPlugin.ID;
  }
  
  protected String getExtensionPointId()
  {
    return "modelImporterDescriptors";
  }

  protected String getElementName()
  {
    return "modelImporterDescriptor";
  }

  protected ModelConverterDescriptorImpl createModelConverterDescriptorImpl()
  {
    return new ModelImporterDescriptorImpl();
  }

  protected ModelConverterDescriptorWizardNode createModelConverterDescriptorWizardNode(ModelConverterDescriptor descriptor)
  {
    return new ModelImporterDescriptorWizardNode((ModelImporterDescriptor)descriptor);
  }

  public ModelImporterDescriptor getModelImporterDescriptor(String id)
  {
    return (ModelImporterDescriptor)getModelConverterDescriptor(id);
  }
  
  public ModelConverterDescriptor createFromContribution(IConfigurationElement configurationElement)
  {
    ModelImporterDescriptorImpl descriptorImpl = (ModelImporterDescriptorImpl)super.createFromContribution(configurationElement);
    if (descriptorImpl != null)
    {
      String extensions = configurationElement.getAttribute("extensions");
      if (extensions != null)
      {
        String[] exts = extensions.split(",");
        for (int i = 0; i < exts.length; i++)
        {
          String ext = exts[i].trim();
          if (ext.length() > 0)
          {
            descriptorImpl.getExtensions().add(ext);
          }
        }
      }

      String typesAttribute = configurationElement.getAttribute("types");
      if (typesAttribute != null)
      {
        descriptorImpl.setTypes(0);
        String[] types = typesAttribute.split(",");
        for (int i = 0; i < types.length; i++)
        {
          String type = types[i].trim();
          if ("project".equalsIgnoreCase(type))
          {
            descriptorImpl.setTypes(descriptorImpl.getTypes() | ModelImporterDescriptor.TYPE_PROJECT);
          }
          else if ("file".equalsIgnoreCase(type))
          {
            descriptorImpl.setTypes(descriptorImpl.getTypes() | ModelImporterDescriptor.TYPE_FILE);
          }
        }
      }      
    }
    return descriptorImpl;
  }
  
  public List filterModelImporterDescriptors(int type)
  {
    List descriptors = new ArrayList();
    for (Iterator i = getModelConverterDescriptors().iterator(); i.hasNext();)
    {
      ModelImporterDescriptor descriptor = (ModelImporterDescriptor)i.next();
      if ((descriptor.getTypes() & type) == type)
      {
        descriptors.add(descriptor);
      }
    }
    return descriptors;
  }

  public List filterModelImporterDescriptors(String extension)
  {
    List descriptors = new ArrayList();
    for (Iterator i = getModelConverterDescriptors().iterator(); i.hasNext();)
    {
      ModelImporterDescriptor descriptor = (ModelImporterDescriptor)i.next();
      if (descriptor.getExtensions().contains(extension))
      {
        descriptors.add(descriptor);
      }
    }
    return descriptors;
  }  
}
