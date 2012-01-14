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
package org.eclipse.xsd.ecore;


import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.eclipse.emf.ecore.EModelElement;
import org.eclipse.emf.ecore.EObject;

import org.eclipse.xsd.XSDComponent;


public class MapBuilder extends NameMangler
{
  public interface Mapper
  {
    EObject getRoot();

    void addInput(EObject eObject);

    void addOutput(EObject eObject);

    void map(Collection<? extends EObject> inputs, Collection<? extends EObject> outputs);
  }

  protected Mapper mapper;
  protected Map<XSDComponent, EModelElement> xsdComponentToEModelElementMap = new HashMap<XSDComponent, EModelElement>();

  public MapBuilder()
  {
    super();
  }

  public void setMapper(Mapper mapper)
  {
    this.mapper = mapper;
  }

  protected void map(XSDComponent xsdComponent, EModelElement eModelElement)
  {
    if (mapper != null)
    {
      mapper.map(Collections.singleton(xsdComponent), Collections.singleton(eModelElement));
    }

    xsdComponentToEModelElementMap.put(xsdComponent, eModelElement);
  }

  protected void addInput(EObject eObject)
  {
    if (mapper != null)
    {
      mapper.addInput(eObject);
    }
  }

  protected void addOutput(EObject eObject)
  {
    if (mapper != null)
    {
      mapper.addOutput(eObject);
    }
  }
}
