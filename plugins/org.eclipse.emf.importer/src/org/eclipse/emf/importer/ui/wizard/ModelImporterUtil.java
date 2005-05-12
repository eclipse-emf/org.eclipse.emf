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
 * $Id: ModelImporterUtil.java,v 1.2 2005/05/12 14:08:44 marcelop Exp $
 */
package org.eclipse.emf.importer.ui.wizard;

import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.osgi.framework.Bundle;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExtensionPoint;
import org.eclipse.core.runtime.Platform;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.wizard.IWizard;
import org.eclipse.jface.wizard.IWizardNode;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.Point;

import org.eclipse.emf.importer.ImporterPlugin;


/**
 * @since 2.1.0
 */
public class ModelImporterUtil
{
  public static class ModelImporterDescriptorImpl implements ModelImporterDescriptor
  {
    protected String id;
    protected String name;
    protected List extensions;
    protected int types = ModelImporterDescriptor.TYPE_DEFAULT;
    protected Image icon;
    protected String description;
    
    protected IModelImporterWizard modelImporterWizard;
    protected IConfigurationElement configurationElement;

    public String getId()
    {
      return id;
    }

    public void setId(String id)
    {
      this.id = id;
    }

    public Image getIcon()
    {
      return icon;
    }

    public void setIcon(Image icon)
    {
      this.icon = icon;
    }

    public String getName()
    {
      return name;
    }

    public void setName(String name)
    {
      this.name = name;
    }

    public int getTypes()
    {
      return types;
    }

    public void setTypes(int types)
    {
      this.types = types;
    }

    public String getDescription()
    {
      return description;
    }

    public void setDescription(String description)
    {
      this.description = description;
    }
    
    public List getExtensions()
    {
      if (extensions == null)
      {
        extensions = new ArrayList();
      }
      return extensions;
    }

    public IModelImporterWizard getWizard()
    {
      if (modelImporterWizard == null)
      {
        if (configurationElement != null)
        {
          try
          {
            Object object = configurationElement.createExecutableExtension("wizard");
            if (object instanceof IModelImporterWizard)
            {
              modelImporterWizard = (IModelImporterWizard)object;
            }
          }
          catch (CoreException e)
          {
          }
        }
      }
      return modelImporterWizard;
    }
  }

  public static class ModelImporterDescriptorLabelProvider extends LabelProvider
  {
    public Image getImage(Object element)
    {
      if (element instanceof ModelImporterDescriptor)
      {
        return ((ModelImporterDescriptor)element).getIcon();
      }
      else
      {
        return super.getImage(element);
      }
    }

    public String getText(Object element)
    {
      if (element instanceof ModelImporterDescriptor)
      {
        return ((ModelImporterDescriptor)element).getName();
      }
      else
      {
        return super.getText(element);
      }
    }
  }

  public static class ModelImporterDescriptorWizardNode implements IWizardNode
  {
    protected boolean contentCreated = false;
    protected ModelImporterDescriptor descriptor;
    protected Point point;

    public ModelImporterDescriptorWizardNode(ModelImporterDescriptor descriptor)
    {
      this.descriptor = descriptor;
    }

    public void dispose()
    {
      if (descriptor != null)
      {
        if (descriptor instanceof ModelImporterDescriptorImpl)
        {
          ((ModelImporterDescriptorImpl)descriptor).modelImporterWizard = null;
        }
        descriptor = null;
      }
      point = null;
    }

    public IWizard getWizard()
    {
      return descriptor.getWizard();
    }

    public boolean isContentCreated()
    {
      return contentCreated;
    }
    
    public void setContentCreated(boolean contentCreated)
    {
      this.contentCreated = contentCreated;
    }

    public Point getExtent()
    {
      if (point == null)
      {
        point = new Point(-1, -1);
      }
      return point;
    }
  }

  public final static String MODEL_IMPORTER_DESCRIPTORS_EXTENSION_POINT = "modelImporterDescriptors";

  private static List descriptors;
  private static Map wizardNodeByDescriptor;

  public static void dispose()
  {
    if (descriptors != null)
    {
      descriptors.clear();
      descriptors = null;
    }

    clearWizardNodesCache();
  }

  public static void clearWizardNodesCache()
  {
    if (wizardNodeByDescriptor != null)
    {
      wizardNodeByDescriptor.clear();
      wizardNodeByDescriptor = null;
    }
  }

  public static IWizardNode getWizardNode(ModelImporterDescriptor descriptor)
  {
    if (wizardNodeByDescriptor == null)
    {
      wizardNodeByDescriptor = new HashMap();
    }

    ModelImporterDescriptorWizardNode descriptorNode = (ModelImporterDescriptorWizardNode)wizardNodeByDescriptor.get(descriptor);
    if (descriptorNode == null)
    {
      descriptorNode = new ModelImporterDescriptorWizardNode(descriptor);
      wizardNodeByDescriptor.put(descriptor, descriptorNode);
    }

    if (descriptorNode.descriptor == null)
    {
      descriptorNode.descriptor = descriptor;
    }

    return descriptorNode;
  }

  public static List getModelImporterDescriptors()
  {
    if (descriptors == null)
    {
      descriptors = retrieveContributedModelImporterDescriptors();
    }
    return descriptors;
  }

  public static ModelImporterDescriptor getModelImporterDescriptor(String id)
  {
    if (id != null)
    {
      for (Iterator i = getModelImporterDescriptors().iterator(); i.hasNext();)
      {
        ModelImporterDescriptor modelImporterDescriptor = (ModelImporterDescriptor)i.next();
        if (id.equals(modelImporterDescriptor.getId()))
        {
          return modelImporterDescriptor;
        }
      }
    }
    return null;
  }

  public static List filterModelImporterDescriptors(int type)
  {
    List descriptors = new ArrayList();
    for (Iterator i = getModelImporterDescriptors().iterator(); i.hasNext();)
    {
      ModelImporterDescriptor descriptor = (ModelImporterDescriptor)i.next();
      if ((descriptor.getTypes() & type) == type)
      {
        descriptors.add(descriptor);
      }
    }
    return descriptors;
  }

  public static List filterModelImporterDescriptors(String extension)
  {
    List descriptors = new ArrayList();
    for (Iterator i = getModelImporterDescriptors().iterator(); i.hasNext();)
    {
      ModelImporterDescriptor descriptor = (ModelImporterDescriptor)i.next();
      if (descriptor.getExtensions().contains(extension))
      {
        descriptors.add(descriptor);
      }
    }
    return descriptors;
  }

  public static List retrieveContributedModelImporterDescriptors()
  {
    List descriptors = new ArrayList();

    IExtensionPoint extensionPoint = Platform.getExtensionRegistry().getExtensionPoint(
      ImporterPlugin.getPlugin().getSymbolicName(),
      MODEL_IMPORTER_DESCRIPTORS_EXTENSION_POINT);
    IConfigurationElement[] configurationElements = extensionPoint.getConfigurationElements();
    for (int i = 0; i < configurationElements.length; i++)
    {
      ModelImporterDescriptor modelImporterDescriptor = createFromContribution(configurationElements[i]);
      if (modelImporterDescriptor != null)
      {
        descriptors.add(modelImporterDescriptor);
      }
    }

    return descriptors;
  }

  public static ModelImporterDescriptor createFromContribution(IConfigurationElement configurationElement)
  {
    if ("modelImporterDescriptor".equals(configurationElement.getName()))
    {
      String id = configurationElement.getAttribute("id");
      String name = configurationElement.getAttribute("name");
      String wizard = configurationElement.getAttribute("wizard");
      if (id != null && name != null && wizard != null)
      {
        ModelImporterDescriptorImpl descriptor = new ModelImporterDescriptorImpl();
        descriptor.setId(id);
        descriptor.setName(name);
        descriptor.setDescription(configurationElement.getAttribute("description"));
        descriptor.configurationElement = configurationElement;

        String imageKey = configurationElement.getAttribute("icon");
        if (imageKey != null)
        {
          Bundle pluginBundle = Platform.getBundle(configurationElement.getDeclaringExtension().getNamespace());
          URL path = pluginBundle.getEntry("/");
          URL fullPathString = null;
          try
          {
            fullPathString = new URL(path, imageKey);
            ImageDescriptor imageDescriptor = ImageDescriptor.createFromURL(fullPathString);
            descriptor.setIcon(imageDescriptor.createImage());
          }
          catch (Exception e)
          {
          }
        }

        String extensions = configurationElement.getAttribute("extensions");
        if (extensions != null)
        {
          String[] exts = extensions.split(",");
          for (int i = 0; i < exts.length; i++)
          {
            String ext = exts[i].trim();
            if (ext.length() > 0)
            {
              descriptor.getExtensions().add(ext);
            }
          }
        }

        String typesAttribute = configurationElement.getAttribute("types");
        if (typesAttribute != null)
        {
          descriptor.setTypes(0);
          String[] types = typesAttribute.split(",");
          for (int i = 0; i < types.length; i++)
          {
            String type = types[i].trim();
            if ("project".equalsIgnoreCase(type))
            {
              descriptor.setTypes(descriptor.getTypes() | ModelImporterDescriptor.TYPE_PROJECT);
            }
            else if ("file".equalsIgnoreCase(type))
            {
              descriptor.setTypes(descriptor.getTypes() | ModelImporterDescriptor.TYPE_FILE);
            }
          }
        }

        return descriptor;
      }
    }

    return null;
  }
}
