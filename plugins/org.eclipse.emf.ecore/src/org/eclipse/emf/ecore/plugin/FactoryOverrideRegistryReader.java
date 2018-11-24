/**
 * Copyright (c) 2005-2006 IBM Corporation and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 *
 * Contributors:
 *   IBM - Initial API and implementation
 */
package org.eclipse.emf.ecore.plugin;


import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.StringTokenizer;

import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.RegistryFactory;

import org.eclipse.emf.ecore.EPackage;


/**
 * A plugin extension reader that populates the
 * {@link org.eclipse.emf.ecore.EPackage.Registry#INSTANCE global} package registry.
 * Clients are not expected to use this class directly.
 */
class FactoryOverrideRegistryReader extends RegistryReader
{
  static final String TAG_FACTORY = "factory";
  static final String ATT_URI = "uri";
  static final String ATT_CLASS = "class";
  static final String ATT_PREDECESSOR = "predecessor";

  private static final Comparator<IConfigurationElement> CONFIGURATION_ELEMENT_COMPARATOR = new Comparator<IConfigurationElement>()
  {
    public int compare(IConfigurationElement element1, IConfigurationElement element2)
    {
      String class1 = element1.getAttribute(ATT_CLASS);
      String class2 = element2.getAttribute(ATT_CLASS);
      boolean element1Succeeds = getPredecessors(element1).contains(class2);
      boolean element2Succeeds = getPredecessors(element2).contains(class1);
      if (element1Succeeds == element2Succeeds)
      {
        return 0;
      }

      if (element1Succeeds)
      {
        return -1;
      }

      return 1;
    }
  };

  public FactoryOverrideRegistryReader()
  {
    super
      (RegistryFactory.getRegistry(),
       EcorePlugin.INSTANCE.getSymbolicName(),
       EcorePlugin.FACTORY_OVERRIDE_PPID);
  }

  /**
   * Removes all elements with a 'class' attribute that is specified in the 'predecessor' list of any other element.
   * @since 2.16
   */
  @Override
  protected IConfigurationElement[] getFilteredConfigurationElements(IConfigurationElement[] elements)
  {
    List<IConfigurationElement> result = new ArrayList<IConfigurationElement>();
    Map<String, List<IConfigurationElement>> uriToElements = new HashMap<String, List<IConfigurationElement>>();

    for (IConfigurationElement element : elements)
    {
      if (element.getName().equals(TAG_FACTORY))
      {
        String packageURI = element.getAttribute(ATT_URI);
        if (packageURI == null || element.getAttribute(ATT_CLASS) == null)
        {
          // Elements without a 'uri' or 'class' attribute will be handled later in readElement().
          result.add(element);
        }
        else
        {
          List<IConfigurationElement> list = uriToElements.get(packageURI);
          if (list == null)
          {
            list = new ArrayList<IConfigurationElement>();
            uriToElements.put(packageURI, list);
          }

          list.add(element);
        }
      }
    }

    for (Map.Entry<String, List<IConfigurationElement>> entry : uriToElements.entrySet())
    {
      List<IConfigurationElement> list = entry.getValue();
      Collections.sort(list, CONFIGURATION_ELEMENT_COMPARATOR);

      IConfigurationElement firstElement = list.get(0);
      for (int i = 1, size = list.size(); i < size; i++)
      {
        IConfigurationElement element = list.get(i);
        if (CONFIGURATION_ELEMENT_COMPARATOR.compare(firstElement, element) == 0)
        {
          EcorePlugin.INSTANCE.log
            ("Both '" + firstElement.getContributor().getName() + "' and '" + element.getContributor().getName() + "' register a factory override for '" + entry.getKey() + "'");
        }
        else
        {
          break;
        }
      }

      result.add(firstElement);
    }

    return result.toArray(new IConfigurationElement[result.size()]);
  }

  @Override
  protected boolean readElement(IConfigurationElement element, boolean add)
  {
    if (element.getName().equals(TAG_FACTORY))
    {
      String packageURI = element.getAttribute(ATT_URI);
      if (packageURI == null)
      {
        logMissingAttribute(element, ATT_URI);
      }
      else if (element.getAttribute(ATT_CLASS) == null)
      {
        logMissingAttribute(element, ATT_CLASS);
      }
      else if (add)
      {
        Object ePackageDescriptor = EPackage.Registry.INSTANCE.get(packageURI);
        if (ePackageDescriptor instanceof EPackage.Descriptor)
        {
          EPackage.Registry.INSTANCE.put(packageURI, new EFactoryDescriptor(element, ATT_CLASS, (EPackage.Descriptor)ePackageDescriptor));
          if (ePackageDescriptor instanceof EFactoryDescriptor)
          {
            EFactoryDescriptor descriptor = (EFactoryDescriptor)ePackageDescriptor;
            EcorePlugin.INSTANCE.log
              ("Both '" + descriptor.element.getContributor().getName() + "' and '" + element.getContributor().getName() + "' register a factory override for '" + packageURI + "'");
          }
        }
        return true;
      }
      else
      {
        Object ePackageDescriptor = EPackage.Registry.INSTANCE.get(packageURI);
        if (ePackageDescriptor instanceof EFactoryDescriptor)
        {
          EPackage.Registry.INSTANCE.put(packageURI, ((EFactoryDescriptor)ePackageDescriptor).getOverridenDescriptor());
        }
        return true;
      }
    }

    return false;
  }

  private static Set<String> getPredecessors(IConfigurationElement element)
  {
    String value = element.getAttribute(ATT_PREDECESSOR);
    if (value == null)
    {
      return Collections.emptySet();
    }

    Set<String> predecessors = new HashSet<String>();
    StringTokenizer tokenizer = new StringTokenizer(value, " ");

    while (tokenizer.hasMoreTokens())
    {
      String predecessor = tokenizer.nextToken().trim();
      if (predecessor.length() != 0)
      {
        predecessors.add(predecessor);
      }
    }

    return predecessors;
  }
}
