/**
 * Copyright (c) 2003-2006 IBM Corporation and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *   IBM - Initial API and implementation
 */
package org.eclipse.emf.ecore.xmi.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.ENamedElement;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EcorePackage;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.xmi.XMIResource;
import org.eclipse.emf.ecore.xmi.XMLHelper;
import org.eclipse.emf.ecore.xmi.XMLLoad;
import org.eclipse.emf.ecore.xmi.XMLSave;


/**
 * This class represents an Ecore model serialized as an EMOF XMI file.
 */
public class EMOFResourceImpl extends XMIResourceImpl implements XMIResource
{
  public EMOFResourceImpl()
  {
    super();
  }

  public EMOFResourceImpl(URI uri)
  {
    super(uri);
  }

  @Override
  protected XMLHelper createXMLHelper()
  {
    return null;
  }

  @Override
  protected XMLLoad createXMLLoad()
  {
    return new EMOFLoadImpl(new EMOFHelperImpl(this));
  }     
  
  @Override
  protected XMLSave createXMLSave()
  {
    return new EMOFSaveImpl(new EMOFHelperImpl(this));
  }

  @Override
  public String getID(EObject eObject)
  {
    String id = super.getID(eObject);
    if (id == null)
    {
      EClass eClass = eObject.eClass();
      if ((eClass != EcorePackage.Literals.EANNOTATION || eObject.eContainer() == null) &&
          eClass != EcorePackage.Literals.ESTRING_TO_STRING_MAP_ENTRY)
      {
        id = makeID(eObject);
        getEObjectToIDMap().put(eObject, id);
      }
    }
    return id;
  }

  protected String makeID(EObject eObject)
  {
    List<String> uriFragmentPath = new ArrayList<String>();
    for (EObject container = eObject.eContainer(); container != null; container = eObject.eContainer())
    {
      uriFragmentPath.add(((InternalEObject)container).eURIFragmentSegment(eObject.eContainmentFeature(), eObject));
      eObject = container;
    }

    StringBuffer result = new StringBuffer(eObject instanceof ENamedElement ?
                                           ((ENamedElement)eObject).getName() :
                                           "_" + Integer.toString(getContents().indexOf(eObject)));
    for (ListIterator<String> i = uriFragmentPath.listIterator(uriFragmentPath.size()); i.hasPrevious(); )
    {
      result.append('.');
      result.append(i.previous());
    }

    return result.toString();
  }
}
