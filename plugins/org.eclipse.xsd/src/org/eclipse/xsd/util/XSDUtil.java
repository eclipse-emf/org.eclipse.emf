/**
 * <copyright>
 *
 * Copyright (c) 2002-2004 IBM Corporation and others.
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
 * $Id: XSDUtil.java,v 1.1 2004/03/06 18:00:11 marcelop Exp $
 */
package org.eclipse.xsd.util;


import java.util.Collection;
import java.util.Collections;
import java.util.Map;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.util.EcoreUtil;

import org.eclipse.xsd.XSDNamedComponent;
import org.eclipse.xsd.XSDSchema;
import org.eclipse.xsd.impl.XSDSchemaImpl;


/**
 */
public final class XSDUtil extends XSDConstants
{
  /**
   * Returns the singleton instance of the schema for schemas.
   */
  public static XSDSchema getSchemaForSchema(String schemaForSchemaURI)
  {
    return XSDSchemaImpl.getSchemaForSchema(schemaForSchemaURI);
  }

  /**
   * A cross referencer that finds each usage of an EObject or collection of EObjects,
   * excluding unintesting derived references.
   */
  public static class UsageCrossReferencer extends EcoreUtil.UsageCrossReferencer 
  {
    protected Collection eObjectsOfInterest;

    protected UsageCrossReferencer(EObject eObject)
    {
      super(eObject);
    }

    protected UsageCrossReferencer(Resource resource)
    {
      super(resource);
    }

    protected UsageCrossReferencer(ResourceSet resourceSet)
    {
      super(resourceSet);
    }

    protected UsageCrossReferencer(Collection emfObjects)
    {
      super(emfObjects);
    }

    protected boolean crossReference(EObject eObject, EReference eReference, EObject crossReferencedEObject)
    {
      return
         !eReference.isVolatile() &&
           eReference.isChangeable() &&
           eObjectsOfInterest.contains(crossReferencedEObject);
    }

    protected Collection findUsage(EObject eObject)
    {
      eObjectsOfInterest = Collections.singleton(eObject);
      crossReference();
      this.eObjectsOfInterest = null;
      done();
      return getCollection(eObject);
    }

    protected Map findAllUsage(Collection eObjectsOfInterest)
    {
      this.eObjectsOfInterest = eObjectsOfInterest;
      crossReference();
      this.eObjectsOfInterest = null;
      done();
      return this;
    }

    public static Collection find(EObject eObjectOfInterest, EObject eObject)
    {
      return new UsageCrossReferencer(eObject).findUsage(eObjectOfInterest);
    }

    public static Collection find(EObject eObjectOfInterest, Resource resource)
    {
      return new UsageCrossReferencer(resource).findUsage(eObjectOfInterest);
    }

    public static Collection find(EObject eObjectOfInterest, ResourceSet resourceSet)
    {
      return new UsageCrossReferencer(resourceSet).findUsage(eObjectOfInterest);
    }

    public static Collection find(EObject eObjectOfInterest, Collection emfObjectsToSearch)
    {
      return new UsageCrossReferencer(emfObjectsToSearch).findUsage(eObjectOfInterest);
    }
  
    public static Map findAll(Collection eObjectsOfInterest, EObject eObject)
    {
      return new UsageCrossReferencer(eObject).findAllUsage(eObjectsOfInterest);
    }

    public static Map findAll(Collection eObjectsOfInterest, Resource resource)
    {
      return new UsageCrossReferencer(resource).findAllUsage(eObjectsOfInterest);
    }

    public static Map findAll(Collection eObjectsOfInterest, ResourceSet resourceSet)
    {
      return new UsageCrossReferencer(resourceSet).findAllUsage(eObjectsOfInterest);
    }

    public static Map findAll(Collection eObjectsOfInterest, Collection emfObjectsToSearch)
    {
      return new UsageCrossReferencer(emfObjectsToSearch).findAllUsage(eObjectsOfInterest);
    }
  }

  /**
   * A cross referencer that finds all XSDNamedComponents and each usage of an XSDNamedComponent.
   */
  public static class XSDNamedComponentCrossReferencer extends EcoreUtil.CrossReferencer 
  {
    protected XSDNamedComponentCrossReferencer(EObject eObject)
    {
      super(eObject);
    }

    protected XSDNamedComponentCrossReferencer(Resource resource)
    {
      super(resource);
    }

    protected XSDNamedComponentCrossReferencer(ResourceSet resourceSet)
    {
      super(resourceSet);
    }

    protected XSDNamedComponentCrossReferencer(Collection emfObjects)
    {
      super(emfObjects);
    }

    protected boolean containment(EObject eObject)
    {
      // Create an empty setting collection for any named component.
      //
      if (eObject instanceof XSDNamedComponent)
      {
        getCollection(eObject);
      }
      return true;
    }
   
    protected boolean crossReference(EObject eObject, EReference eReference, EObject crossReferencedEObject)
    {
      // Add a setting for any named component in an interesting reference.
      //
      return
         !eReference.isVolatile() &&
           eReference.isChangeable() &&
           crossReferencedEObject instanceof XSDNamedComponent;
    }

    /**
     * Returns a map of all XSDNamedComponent cross references in the content tree.
     */
    public static Map find(EObject eObject)
    {
      XSDNamedComponentCrossReferencer result = new XSDNamedComponentCrossReferencer(eObject);
      result.crossReference();
      result.done();
      return result;
    }

    /**
     * Returns a map of all XSDNamedComponent cross references in the content tree.
     */
    public static Map find(Resource resource)
    {
      XSDNamedComponentCrossReferencer result = new XSDNamedComponentCrossReferencer(resource);
      result.crossReference();
      result.done();
      return result;
    }

    /**
     * Returns a map of all XSDNamedComponent cross references in the content tree.
     */
    public static Map find(ResourceSet resourceSet)
    {
      XSDNamedComponentCrossReferencer result = new XSDNamedComponentCrossReferencer(resourceSet);
      result.crossReference();
      result.done();
      return result;
    }
  }

  /**
   * A cross referencer that finds each usage of URI or collection of URIs.
   */
  public static class URICrossReferencer extends EcoreUtil.CrossReferencer
  {
    protected String [] uris;

    protected URICrossReferencer(EObject eObject)
    {
      super(eObject);
    }

    protected URICrossReferencer(Resource resource)
    {
      super(resource);
    }

    protected URICrossReferencer(ResourceSet resourceSet)
    {
      super(resourceSet);
    }

    protected URICrossReferencer(Collection emfObjects)
    {
      super(emfObjects);
    }

    protected boolean containment(EObject eObject)
    {
      // Create an empty setting collection for any named component.
      //
      if (eObject instanceof XSDNamedComponent)
      {
        XSDNamedComponent xsdNamedComponent = (XSDNamedComponent)eObject;
        for (int i = 0; i < uris.length; ++i)
        {
          String uri = uris[i];
          if (xsdNamedComponent.hasURI(uri))
          {
            getCollection(eObject);
            break;
          }
        }
      }
      return true;
    }
   
    protected boolean crossReference(EObject eObject, EReference eReference, EObject crossReferencedEObject)
    {
      if (crossReferencedEObject instanceof XSDNamedComponent && 
            !eReference.isVolatile() && 
            eReference.isChangeable())
      {
        XSDNamedComponent xsdNamedComponent = (XSDNamedComponent)crossReferencedEObject;
        for (int i = 0; i < uris.length; ++i)
        {
          String uri = uris[i];
          if (xsdNamedComponent.hasURI(uri))
          {
            return true;
          }
        }
      }

      return false;
    }

    protected Map findURI(String uri)
    {
      uris = new String [] { uri };
      crossReference();
      uris = null;
      done();
      return this;
    }

    protected Map findAllURI(Collection uris)
    {
      this.uris = (String [])uris.toArray(new String [uris.size()]);
      crossReference();
      uris = null;
      done();
      return this;
    }

    public static Map find(String uri, EObject eObject)
    {
      return new URICrossReferencer(eObject).findURI(uri);
    }

    public static Map find(String uri, Resource resource)
    {
      return new URICrossReferencer(resource).findURI(uri);
    }

    public static Map find(String uri, ResourceSet resourceSet)
    {
      return new URICrossReferencer(resourceSet).findURI(uri);
    }

    public static Map find(String uri, Collection emfObjectsToSearch)
    {
      return new URICrossReferencer(emfObjectsToSearch).findURI(uri);
    }
  
    public static Map findAll(Collection uris, EObject eObject)
    {
      return new URICrossReferencer(eObject).findAllURI(uris);
    }

    public static Map findAll(Collection uris, Resource resource)
    {
      return new URICrossReferencer(resource).findAllURI(uris);
    }

    public static Map findAll(Collection uris, ResourceSet resourceSet)
    {
      return new URICrossReferencer(resourceSet).findAllURI(uris);
    }

    public static Map findAll(Collection uris, Collection emfObjectsToSearch)
    {
      return new URICrossReferencer(emfObjectsToSearch).findAllURI(uris);
    }
  }
}
