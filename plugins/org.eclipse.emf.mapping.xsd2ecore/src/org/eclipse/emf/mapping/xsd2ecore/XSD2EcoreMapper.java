/**
 * Copyright (c) 2002-2006 IBM Corporation and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *   IBM - Initial API and implementation
 */
package org.eclipse.emf.mapping.xsd2ecore;


import java.util.Collection;
import java.util.Iterator;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.provider.EcoreItemProviderAdapterFactory;
import org.eclipse.emf.edit.provider.ComposedAdapterFactory;
import org.eclipse.emf.edit.provider.resource.ResourceItemProviderAdapterFactory;
import org.eclipse.emf.mapping.Mapping;
import org.eclipse.emf.mapping.MappingRoot;
import org.eclipse.emf.mapping.domain.PluginAdapterFactoryMappingDomain;
import org.eclipse.emf.mapping.provider.MappingItemProviderAdapterFactory;

import org.eclipse.xsd.ecore.MapBuilder.Mapper;
import org.eclipse.xsd.provider.XSDItemProviderAdapterFactory;


public class XSD2EcoreMapper implements Mapper
{
  protected MappingRoot mappingRoot;
  protected boolean topToBottom = true;

  public XSD2EcoreMapper()
  {
    super();
  }

  public EObject getRoot()
  {
    return getMappingRoot();
  }

  public MappingRoot getMappingRoot()
  {
    if (mappingRoot == null)
    {
      mappingRoot = XSD2EcoreFactory.eINSTANCE.createXSD2EcoreMappingRoot();
      AdapterFactory xsdAdapterFactory = new XSDItemProviderAdapterFactory();
      AdapterFactory ecoreAdapterFactory = new EcoreItemProviderAdapterFactory();
      AdapterFactory composedAdapterFactory =
        new ComposedAdapterFactory
          (new AdapterFactory[]
           {
             new ResourceItemProviderAdapterFactory(),
             new MappingItemProviderAdapterFactory(),
             new XSDItemProviderAdapterFactory(),
             new EcoreItemProviderAdapterFactory()
           });

      mappingRoot.setDomain
       (new PluginAdapterFactoryMappingDomain
         (composedAdapterFactory,
          xsdAdapterFactory,
          ecoreAdapterFactory,
          null,
          null));

      mappingRoot.setTopToBottom(topToBottom);
    }

    return mappingRoot;
  }

  public void map(Collection<? extends EObject> inputs, Collection<? extends EObject> outputs)
  {
    Mapping mapping = 
      topToBottom ? 
        getMappingRoot().createMapping(inputs, outputs) :
        getMappingRoot().createMapping(outputs, inputs);
    Mapping parent = mappingRoot.getParentMapping(mapping.getMappedObjects());
    parent.getNested().add(mapping);
    for (Iterator<Mapping> i = parent.getNested().iterator(); i.hasNext(); )
    {
      Mapping otherMapping = i.next();
      if (otherMapping != mapping && mappingRoot.getParentMapping(otherMapping.getMappedObjects()) == mapping)
      {
        i.remove();
        mapping.getNested().add(otherMapping);
      }
    }
  }

  public void addInput(EObject input)
  {
    if (input instanceof EPackage)
    {
      topToBottom = false;
    }

    getMappingRoot().getInputs().add(input);
  }

  public void addOutput(EObject output)
  {
    getMappingRoot().getOutputs().add(output);
  }
}
