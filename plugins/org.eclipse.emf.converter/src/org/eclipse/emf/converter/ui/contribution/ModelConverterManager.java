/**
 * Copyright (c) 2005-2008 IBM Corporation and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: 
 *   IBM - Initial API and implementation
 */
package org.eclipse.emf.converter.ui.contribution;

import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExtensionPoint;
import org.eclipse.core.runtime.Platform;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.wizard.IWizard;
import org.eclipse.jface.wizard.IWizardNode;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.Point;

import org.eclipse.emf.common.util.URI;


/**
 * @since 2.2.0
 */
public abstract class ModelConverterManager<D extends ModelConverterDescriptor>
{
  public static class ModelConverterDescriptorImpl implements ModelConverterDescriptor
  {
    protected String id;
    protected String name;
    protected Image icon;
    protected String description;
    
    protected IConfigurationElement configurationElement;

    public String getID()
    {
      return id;
    }

    public void setID(String id)
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

    public String getDescription()
    {
      return description;
    }

    public void setDescription(String description)
    {
      this.description = description;
    }
    
    protected Object createExecutableExtension(String attribute)
    {
      if (configurationElement != null)
      {
        try
        {
          return configurationElement.createExecutableExtension(attribute);
        }
        catch (Exception e)
        {
          // Ignore
        }
      }
      return null;
    }
  }

  public static class ModelConverterDescriptorLabelProvider extends LabelProvider
  {
    @Override
    public Image getImage(Object element)
    {
      if (element instanceof ModelConverterDescriptor)
      {
        return ((ModelConverterDescriptor)element).getIcon();
      }
      else
      {
        return super.getImage(element);
      }
    }

    @Override
    public String getText(Object element)
    {
      if (element instanceof ModelConverterDescriptor)
      {
        return ((ModelConverterDescriptor)element).getName();
      }
      else
      {
        return super.getText(element);
      }
    }
  }

  public static abstract class ModelConverterDescriptorWizardNode implements IWizardNode
  {
    protected boolean contentCreated = false;
    protected ModelConverterDescriptor descriptor;
    protected IWizard wizard;
    protected Point point;

    public ModelConverterDescriptorWizardNode(ModelConverterDescriptor descriptor)
    {
      this.descriptor = descriptor;
    }

    public void dispose()
    {
      wizard = null;
      descriptor = null;
      point = null;
    }

    public IWizard getWizard()
    {
      if (wizard == null)
      {
        if (descriptor != null)
        {
          wizard = createWizard();
        }
      } 
      return wizard;
    }
    
    abstract protected IWizard createWizard();

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

  protected List<D> descriptors;

  public void dispose()
  {
    if (descriptors != null)
    {
      descriptors.clear();
      descriptors = null;
    }
  }
  
  abstract protected ModelConverterDescriptorWizardNode createModelConverterDescriptorWizardNode(D descriptor);

  /**
   * @return a map in which the key is a {@link ModelConverterDescriptor} and 
   * the value is a {@link ModelConverterDescriptorWizardNode}
   */
  public Map<D, ModelConverterDescriptorWizardNode> createModelConverterDescriptorWizardNodeMap()
  {
    if (descriptors != null)
    {
      Map<D, ModelConverterDescriptorWizardNode> map = new HashMap<D, ModelConverterDescriptorWizardNode>();
      for (D descriptor : descriptors)
      {
        ModelConverterDescriptorWizardNode wizardNode = createModelConverterDescriptorWizardNode(descriptor);
        map.put(descriptor, wizardNode);
      }
      return map;
    }
    else
    {
      return Collections.emptyMap();
    }
  }

  public List<D> getModelConverterDescriptors()
  {
    if (descriptors == null)
    {
      descriptors = retrieveContributedModelConverterDescriptors();
    }
    return descriptors;
  }

  public D getModelConverterDescriptor(String id)
  {
    if (id != null)
    {
      for (D descriptor : getModelConverterDescriptors())
      {
        if (id.equals(descriptor.getID()))
        {
          return descriptor;
        }
      }
    }
    return null;
  }
  
  abstract protected String getPluginId();
  abstract protected String getExtensionPointId();

  public List<D> retrieveContributedModelConverterDescriptors()
  {
    List<D> descriptors = new ArrayList<D>();

    IExtensionPoint extensionPoint = Platform.getExtensionRegistry().getExtensionPoint(getPluginId(), getExtensionPointId());
    IConfigurationElement[] configurationElements = extensionPoint.getConfigurationElements();
    for (int i = 0; i < configurationElements.length; i++)
    {
      D descriptor = createFromContribution(configurationElements[i]);
      if (descriptor != null)
      {
        descriptors.add(descriptor);
      }
    }

    return descriptors;
  }
  
  abstract protected String getElementName();
  abstract protected ModelConverterDescriptorImpl createModelConverterDescriptorImpl();

  protected D createFromContribution(IConfigurationElement configurationElement)
  {
    if (getElementName().equals(configurationElement.getName()))
    {
      String id = configurationElement.getAttribute("id");
      String name = configurationElement.getAttribute("name");
      String wizard = configurationElement.getAttribute("wizard");
      if (id != null && name != null && wizard != null)
      {
        ModelConverterDescriptorImpl descriptorImpl = createModelConverterDescriptorImpl();
        descriptorImpl.setID(id);
        descriptorImpl.setName(name);
        descriptorImpl.setDescription(configurationElement.getAttribute("description"));
        descriptorImpl.configurationElement = configurationElement;

        String iconValue = configurationElement.getAttribute("icon");
        if (iconValue != null)
        {
          URI iconURI = URI.createURI(iconValue);
          if (iconURI.isRelative())
          {
            URI pluginURI = URI.createPlatformPluginURI(configurationElement.getContributor().getName() + "/", true);
            iconURI = iconURI.resolve(pluginURI);
          }
          
          try
          {
            ImageDescriptor imageDescriptor = ImageDescriptor.createFromURL(new URL(iconURI.toString()));
            descriptorImpl.setIcon(imageDescriptor.createImage());
          }
          catch (Exception e)
          {
            // Ignore
          }
        }

        @SuppressWarnings("unchecked")
        D descriptor = (D)descriptorImpl;
        return descriptor;
      }
    }

    return null;
  }
}
