/**
 * <copyright>
 *
 * Copyright (c) 2002-2004 IBM Corporation and others.
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
 * $Id: XSDUtil.java,v 1.6 2006/12/15 18:59:56 emerks Exp $
 */
package org.eclipse.xsd.util;


import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.util.EcoreUtil;

import org.eclipse.xsd.XSDNamedComponent;
import org.eclipse.xsd.XSDSchema;
import org.eclipse.xsd.impl.XSDNamedComponentImpl;
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
    private static final long serialVersionUID = 1L;

    protected Collection<?> eObjectsOfInterest;

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

    protected UsageCrossReferencer(Collection<?> emfObjects)
    {
      super(emfObjects);
    }

    @Override
    protected boolean crossReference(EObject eObject, EReference eReference, EObject crossReferencedEObject)
    {
      return
         !eReference.isVolatile() &&
           eReference.isChangeable() &&
           eObjectsOfInterest.contains(crossReferencedEObject);
    }

    @Override
    protected Collection<EStructuralFeature.Setting> findUsage(EObject eObject)
    {
      eObjectsOfInterest = Collections.singleton(eObject);
      crossReference();
      this.eObjectsOfInterest = null;
      done();
      return getCollection(eObject);
    }

    @Override
    protected Map<EObject, Collection<EStructuralFeature.Setting>> findAllUsage(Collection<?> eObjectsOfInterest)
    {
      this.eObjectsOfInterest = eObjectsOfInterest;
      crossReference();
      this.eObjectsOfInterest = null;
      done();
      return this;
    }

    public static Collection<EStructuralFeature.Setting> find(EObject eObjectOfInterest, EObject eObject)
    {
      return new UsageCrossReferencer(eObject).findUsage(eObjectOfInterest);
    }

    public static Collection<EStructuralFeature.Setting> find(EObject eObjectOfInterest, Resource resource)
    {
      return new UsageCrossReferencer(resource).findUsage(eObjectOfInterest);
    }

    public static Collection<EStructuralFeature.Setting> find(EObject eObjectOfInterest, ResourceSet resourceSet)
    {
      return new UsageCrossReferencer(resourceSet).findUsage(eObjectOfInterest);
    }

    public static Collection<EStructuralFeature.Setting> find(EObject eObjectOfInterest, Collection<?> emfObjectsToSearch)
    {
      return new UsageCrossReferencer(emfObjectsToSearch).findUsage(eObjectOfInterest);
    }
  
    public static Map<EObject, Collection<EStructuralFeature.Setting>> findAll(Collection<?> eObjectsOfInterest, EObject eObject)
    {
      return new UsageCrossReferencer(eObject).findAllUsage(eObjectsOfInterest);
    }

    public static Map<EObject, Collection<EStructuralFeature.Setting>> findAll(Collection<?> eObjectsOfInterest, Resource resource)
    {
      return new UsageCrossReferencer(resource).findAllUsage(eObjectsOfInterest);
    }

    public static Map<EObject, Collection<EStructuralFeature.Setting>> findAll(Collection<?> eObjectsOfInterest, ResourceSet resourceSet)
    {
      return new UsageCrossReferencer(resourceSet).findAllUsage(eObjectsOfInterest);
    }

    public static Map<EObject, Collection<EStructuralFeature.Setting>> findAll(Collection<?> eObjectsOfInterest, Collection<?> emfObjectsToSearch)
    {
      return new UsageCrossReferencer(emfObjectsToSearch).findAllUsage(eObjectsOfInterest);
    }
  }

  /**
   * A cross referencer that finds all XSDNamedComponents and each usage of an XSDNamedComponent.
   */
  public static class XSDNamedComponentCrossReferencer extends EcoreUtil.CrossReferencer 
  {
    private static final long serialVersionUID = 1L;

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

    protected XSDNamedComponentCrossReferencer(Collection<?> emfObjects)
    {
      super(emfObjects);
    }

    @Override
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
   
    @Override
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
    public static Map<EObject, Collection<EStructuralFeature.Setting>> find(EObject eObject)
    {
      XSDNamedComponentCrossReferencer result = new XSDNamedComponentCrossReferencer(eObject);
      result.crossReference();
      result.done();
      return result;
    }

    /**
     * Returns a map of all XSDNamedComponent cross references in the content tree.
     */
    public static Map<EObject, Collection<EStructuralFeature.Setting>> find(Resource resource)
    {
      XSDNamedComponentCrossReferencer result = new XSDNamedComponentCrossReferencer(resource);
      result.crossReference();
      result.done();
      return result;
    }

    /**
     * Returns a map of all XSDNamedComponent cross references in the content tree.
     */
    public static Map<EObject, Collection<EStructuralFeature.Setting>> find(ResourceSet resourceSet)
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
    private static final long serialVersionUID = 1L;

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

    protected URICrossReferencer(Collection<?> emfObjects)
    {
      super(emfObjects);
    }

    @Override
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
   
    @Override
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

    protected Map<EObject, Collection<EStructuralFeature.Setting>> findURI(String uri)
    {
      uris = new String [] { uri };
      crossReference();
      uris = null;
      done();
      return this;
    }

    protected Map<EObject, Collection<EStructuralFeature.Setting>> findAllURI(Collection<String> uris)
    {
      this.uris = uris.toArray(new String [uris.size()]);
      crossReference();
      uris = null;
      done();
      return this;
    }

    public static Map<EObject, Collection<EStructuralFeature.Setting>> find(String uri, EObject eObject)
    {
      return new URICrossReferencer(eObject).findURI(uri);
    }

    public static Map<EObject, Collection<EStructuralFeature.Setting>> find(String uri, Resource resource)
    {
      return new URICrossReferencer(resource).findURI(uri);
    }

    public static Map<EObject, Collection<EStructuralFeature.Setting>> find(String uri, ResourceSet resourceSet)
    {
      return new URICrossReferencer(resourceSet).findURI(uri);
    }

    public static Map<EObject, Collection<EStructuralFeature.Setting>> find(String uri, Collection<?> emfObjectsToSearch)
    {
      return new URICrossReferencer(emfObjectsToSearch).findURI(uri);
    }
  
    public static Map<EObject, Collection<EStructuralFeature.Setting>> findAll(Collection<String> uris, EObject eObject)
    {
      return new URICrossReferencer(eObject).findAllURI(uris);
    }

    public static Map<EObject, Collection<EStructuralFeature.Setting>> findAll(Collection<String> uris, Resource resource)
    {
      return new URICrossReferencer(resource).findAllURI(uris);
    }

    public static Map<EObject, Collection<EStructuralFeature.Setting>> findAll(Collection<String> uris, ResourceSet resourceSet)
    {
      return new URICrossReferencer(resourceSet).findAllURI(uris);
    }

    public static Map<EObject, Collection<EStructuralFeature.Setting>> findAll(Collection<String> uris, Collection<?> emfObjectsToSearch)
    {
      return new URICrossReferencer(emfObjectsToSearch).findAllURI(uris);
    }
  }

  /**
   * This is a wrapper type for representing values of type hexBin or base64Binary.
   */
  public static interface ByteSequence
  {
    byte[] getBytes();
  }

  /**
   * Returns the matching named component in the sorted list of named components, 
   * or <code>null</null> if there isn't one.
   * This list is expected to be one of a {@link XSDSchema schema's} list of named components.
   * @param xsdNamedComponents a sorted lists of named components.
   * @param namespace the namespace to search
   * @param name the name to search
   * @return the matching component, if any.
   */
  public static XSDNamedComponent findInSortedList(List<? extends XSDNamedComponent> xsdNamedComponents, String namespace, String name)
  {
    return XSDNamedComponentImpl.findInSortedList(xsdNamedComponents, namespace, name);
  }
}
