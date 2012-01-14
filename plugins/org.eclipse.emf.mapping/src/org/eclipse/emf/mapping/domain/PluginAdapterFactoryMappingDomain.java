/**
 * Copyright (c) 2002-2004 IBM Corporation and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *   IBM - Initial API and implementation
 */
package org.eclipse.emf.mapping.domain;


import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExtension;
import org.eclipse.core.runtime.IExtensionPoint;
import org.eclipse.core.runtime.IExtensionRegistry;
import org.eclipse.core.runtime.Platform;

import org.eclipse.emf.common.command.CommandStack;
import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.mapping.MappingPlugin;


/**
 * This class extends the AdapterFactoryMappingDomain by providing initialization
 * using config information specified in the VABase plugin.xml.
 *
 * <p>
 * A sample configuration is like this:
 * <pre>
 *   &lt;extension point = "org.eclipse.emf.mapping">
 *     &lt;mapping-domain>
 *       &lt;type-mappings id="DTD2DTD">
 *         &lt;type-mapping top="java.xmi:ContainerManagedEntity" bottom="rdbschema.xmi:Table"/>
 *         &lt;type-mapping top="java.xmi:Field" bottom="rdbschema.xmi:Column"/>
 *       &lt;/type-mappings>
 *
 *       &lt;top-label-separator value=""/>
 *       &lt;bottom-label-separator value="_"/>
 *
 *       &lt;top-label-case value="mixed"/>
 *       &lt;bottom-label-case value="upper"/>
 *
 *       &lt;top-label-forms>
 *         &lt;label-form long="Table" short="Tab"/>
 *         &lt;label-form long="Integer" short="Int"/>
 *       &lt;/top-label-forms>
 *
 *       &lt;bottom-label-forms>
 *         &lt;label-form long="TABLE" short="TBL"/>
 *         &lt;label-form long="INTEGER" short="INT"/>
 *       &lt;/bottom-label-forms>
 *     &lt;/mapping-domain>
 *   &lt;/extension>
 * </pre>
 */
public class PluginAdapterFactoryMappingDomain extends AdapterFactoryMappingDomain 
{
  public final String MAPPING_EXTENSION_POINT_ID = "configuration";

  public PluginAdapterFactoryMappingDomain
    (AdapterFactory mappingDomainAdapterFactory, 
     AdapterFactory editingDomainAdapterFactory, 
     CommandStack commandStack,
     String mappingDomainKey) 
  {
    super(mappingDomainAdapterFactory, editingDomainAdapterFactory, commandStack);
    initializeFromPlugin(mappingDomainKey);
  }

  public PluginAdapterFactoryMappingDomain
    (AdapterFactory mappingDomainAdapterFactory, 
     AdapterFactory editingDomainAdapterFactory, 
     CommandStack commandStack,
     ResourceSet resourceSet,
     String mappingDomainKey) 
  {
 
    super(mappingDomainAdapterFactory, editingDomainAdapterFactory, commandStack, resourceSet);
    initializeFromPlugin(mappingDomainKey);
  }

  public PluginAdapterFactoryMappingDomain
    (AdapterFactory mappingDomainAdapterFactory,
     AdapterFactory topDomainAdapterFactory,
     AdapterFactory bottomDomainAdapterFactory,
     CommandStack commandStack,
     String mappingDomainKey)
  {
    super(mappingDomainAdapterFactory, topDomainAdapterFactory, bottomDomainAdapterFactory, commandStack);
    initializeFromPlugin(mappingDomainKey);
  }

  public PluginAdapterFactoryMappingDomain
    (AdapterFactory mappingDomainAdapterFactory,
     AdapterFactory topDomainAdapterFactory,
     AdapterFactory bottomDomainAdapterFactory,
     CommandStack commandStack,
     ResourceSet resourceSet,
     String mappingDomainKey)
  {
    super(mappingDomainAdapterFactory, topDomainAdapterFactory, bottomDomainAdapterFactory, commandStack, resourceSet);
    initializeFromPlugin(mappingDomainKey);
  }

  protected void initializeFromPlugin(String mappingDomainKey)
  {
    if (mappingDomainKey == null) return;

    IExtensionRegistry registry = Platform.getExtensionRegistry();
    IExtensionPoint pct = 
      registry.getExtensionPoint(MappingPlugin.getPlugin().getBundle().getSymbolicName(), MAPPING_EXTENSION_POINT_ID);
                
    IExtension[] extension = pct.getExtensions();
    for (int l = 0; l < extension.length; ++l) 
    {
      IExtension config = extension[l];

      IConfigurationElement[] cElems = config.getConfigurationElements();
      for (int i=0; i < cElems.length; i++) 
      {
        IConfigurationElement d = cElems[i];
        if (d.getName().equals("mapping-domain") && mappingDomainKey.equals(d.getAttribute("key"))) 
        {
          // type-mappings
          IConfigurationElement typeMappings[] = d.getChildren("type-mappings");
          for (int j=0; j < typeMappings.length; j++) 
          {
            IConfigurationElement mapping[] = typeMappings[j].getChildren("type-mapping");
            for (int k=0; k < mapping.length; k++) 
            {
              addTypeMapping(mapping[k].getAttribute("top"), mapping[k].getAttribute("bottom"));
            }
          }
  
          // top-label-separator
          IConfigurationElement topLabelSeparatorAll[] = d.getChildren("top-label-separator");
          if (topLabelSeparatorAll.length > 0) 
          {
            String sep =  topLabelSeparatorAll[0].getAttribute("value");
            if (sep.length() > 0) 
            {
              topLabelSeparator = sep.charAt(0);
            }
          }
  
          // bottom-label-separator
          IConfigurationElement bottomLabelSeparatorAll[] = d.getChildren("bottom-label-separator");
          if (bottomLabelSeparatorAll.length > 0) 
          {
            String sep =  bottomLabelSeparatorAll[0].getAttribute("value");
            if (sep.length() > 0) 
            {
              bottomLabelSeparator = sep.charAt(0);
            }
          }
  
          // top-label-case
          IConfigurationElement topLabelCaseAll[] = d.getChildren("top-label-case");
          if (topLabelCaseAll.length > 0) 
          {
            String labelCase =  topLabelCaseAll[0].getAttribute("value");
            if (labelCase.equalsIgnoreCase("upper")) 
            {
              topLabelCase = LABEL_UPPER;
            }
            else if (labelCase.equalsIgnoreCase("lower")) 
            {
              topLabelCase = LABEL_LOWER;
            }
            else if (labelCase.equalsIgnoreCase("mixed")) 
            {
              topLabelCase = LABEL_MIXED;
            }
          }
  
          // bottom-label-case
          IConfigurationElement bottomLabelCaseAll[] = d.getChildren("bottom-label-case");
          if (bottomLabelCaseAll.length > 0) 
          {
            String labelCase =  bottomLabelCaseAll[0].getAttribute("value");
            if (labelCase.equalsIgnoreCase("upper")) 
            {
              bottomLabelCase = LABEL_UPPER;
            }
            else if (labelCase.equalsIgnoreCase("lower")) 
            {
              bottomLabelCase = LABEL_LOWER;
            }
            else if (labelCase.equalsIgnoreCase("mixed")) 
            {
              bottomLabelCase = LABEL_MIXED;
            }
          }
  
          // top-label-forms
          IConfigurationElement topLabelForms[] = d.getChildren("top-label-forms");
          for (int j=0; j < topLabelForms.length; j++) 
          {
            IConfigurationElement labelForm[] = topLabelForms[j].getChildren("label-form");
            for (int k=0; k < labelForm.length; k++) 
            {
              topLabelLongForms.add(labelForm[k].getAttribute("long"));
              topLabelShortForms.add(labelForm[k].getAttribute("short"));
            }
          }
  
          // bottom-label-forms
          IConfigurationElement bottomLabelForms[] = d.getChildren("bottom-label-forms");
          for (int j=0; j < bottomLabelForms.length; j++) 
          {
            IConfigurationElement labelForm[] = bottomLabelForms[j].getChildren("label-form");
            for (int k=0; k < labelForm.length; k++) 
            {
              bottomLabelLongForms.add(labelForm[k].getAttribute("long"));
              bottomLabelShortForms.add(labelForm[k].getAttribute("short"));
            }
          }

          break;
        } //if
      } // for
    } // for
  } 

  protected void addTypeMapping(String topType, String bottomType) 
  {
    /*
     * The type string must be qualified by its package uri, e.g. DTD.xmi:DTDElement.
     * The package uri is used to look up the package by calling RefRegister.getPackage(uri).
     */
    topToBottomTypeTable.put(topType, bottomType);
    bottomToTopTypeTable.put(bottomType, topType);
  } 
}
