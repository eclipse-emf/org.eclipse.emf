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
package org.eclipse.emf.importer.ui.contribution;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.jface.wizard.IWizard;
import org.eclipse.emf.converter.ui.contribution.ModelConverterManager;
import org.eclipse.emf.importer.ImporterPlugin;

/**
 * @since 2.2.0
 */
public class ModelImporterManager extends ModelConverterManager<ModelImporterDescriptor>
{
  public final static ModelImporterManager INSTANCE = new ModelImporterManager();
  
  public static class ModelImporterDescriptorImpl extends ModelConverterDescriptorImpl implements ModelImporterDescriptor
  {
    protected List<String> extensions;
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

    public List<String> getExtensions()
    {
      if (extensions == null)
      {
        extensions = new ArrayList<String>();
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
    
    @Override
    protected IWizard createWizard()
    {
      return ((ModelImporterDescriptor)descriptor).createWizard();
    }
  }
  
  @Override
  protected String getPluginId()
  {
    return ImporterPlugin.ID;
  }
  
  @Override
  protected String getExtensionPointId()
  {
    return "modelImporterDescriptors";
  }

  @Override
  protected String getElementName()
  {
    return "modelImporterDescriptor";
  }

  @Override
  protected ModelImporterDescriptorImpl createModelConverterDescriptorImpl()
  {
    return new ModelImporterDescriptorImpl();
  }

  @Override
  protected ModelImporterDescriptorWizardNode createModelConverterDescriptorWizardNode(ModelImporterDescriptor descriptor)
  {
    return new ModelImporterDescriptorWizardNode(descriptor);
  }

  public ModelImporterDescriptor getModelImporterDescriptor(String id)
  {
    return getModelConverterDescriptor(id);
  }
  
  @Override
  public ModelImporterDescriptor createFromContribution(IConfigurationElement configurationElement)
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
  
  public List<ModelImporterDescriptor> filterModelImporterDescriptors(int type)
  {
    List<ModelImporterDescriptor> descriptors = new ArrayList<ModelImporterDescriptor>();
    for (ModelImporterDescriptor descriptor : getModelConverterDescriptors())
    {
      if ((descriptor.getTypes() & type) == type)
      {
        descriptors.add(descriptor);
      }
    }
    return descriptors;
  }

  private static List<String> DEFAULT_IMPORTER_IDS =
    Arrays.asList
      (new String[] 
       { 
        "org.eclipse.emf.importer.ecore",
        "org.eclipse.emf.importer.java",
        "org.eclipse.emf.importer.rose",
        "org.eclipse.xsd.ecore.importer",
        "org.eclipse.emf.ecore.xcoreimporter"
       });
  
  public List<ModelImporterDescriptor> filterModelImporterDescriptors(String extension)
  {
    List<ModelImporterDescriptor> descriptors = new ArrayList<ModelImporterDescriptor>();
    for (ModelImporterDescriptor descriptor : getModelConverterDescriptors())
    {
      if (descriptor.getExtensions().contains(extension))
      {
        if (DEFAULT_IMPORTER_IDS.contains(descriptor.getID()))
        {
          descriptors.add(0, descriptor);
        }
        else
        {
          descriptors.add(descriptor);
        }
      }
    }
    return descriptors;
  }  
}
