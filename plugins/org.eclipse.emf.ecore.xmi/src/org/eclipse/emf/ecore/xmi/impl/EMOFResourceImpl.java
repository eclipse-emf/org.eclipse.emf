/**
 * <copyright>
 *
 * Copyright (c) 2003-2004 IBM Corporation and others.
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
 * $Id: EMOFResourceImpl.java,v 1.1.2.1 2005/06/08 18:27:42 nickb Exp $
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

  protected XMLHelper createXMLHelper()
  {
    return null;
  }

  protected XMLLoad createXMLLoad()
  {
    return new EMOFLoadImpl(new EMOFHelperImpl(this));
  }     
  
  protected XMLSave createXMLSave()
  {
    return new EMOFSaveImpl(new EMOFHelperImpl(this));
  }

  public String getID(EObject eObject)
  {
    String id = super.getID(eObject);
    if (id == null)
    {
      EClass eClass = eObject.eClass();
      if ((eClass != EcorePackage.eINSTANCE.getEAnnotation() || eObject.eContainer() == null) &&
          eClass != EcorePackage.eINSTANCE.getEStringToStringMapEntry())
      {
        id = makeID(eObject);
        getEObjectToIDMap().put(eObject, id);
      }
    }
    return id;
  }

  protected String makeID(EObject eObject)
  {
    List uriFragmentPath = new ArrayList();
    for (EObject container = eObject.eContainer(); container != null; container = eObject.eContainer())
    {
      uriFragmentPath.add(((InternalEObject)container).eURIFragmentSegment(eObject.eContainmentFeature(), eObject));
      eObject = container;
    }

    StringBuffer result = new StringBuffer(eObject instanceof ENamedElement ?
                                           ((ENamedElement)eObject).getName() :
                                           "_" + Integer.toString(getContents().indexOf(eObject)));
    for (ListIterator i = uriFragmentPath.listIterator(uriFragmentPath.size()); i.hasPrevious(); )
    {
      result.append('.');
      result.append((String)i.previous());
    }

    return result.toString();
  }
}
