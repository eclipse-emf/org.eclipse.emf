/**
 * <copyright>
 *
 * Copyright (c) 2002-2005 IBM Corporation and others.
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
 * $Id: EcoreUtil.java,v 1.34 2005/11/19 11:58:02 emerks Exp $
 */
package org.eclipse.emf.ecore.util;


import java.io.PrintStream;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.common.notify.Notifier;
import org.eclipse.emf.common.util.AbstractTreeIterator;
import org.eclipse.emf.common.util.ECollections;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EAnnotation;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EModelElement;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.EcoreFactory;
import org.eclipse.emf.ecore.EcorePackage;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.EPackageImpl;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;


/**
 * This class contains convenient static methods for working with EMF objects.
 */
public class EcoreUtil
{
  //   // Suppress default constructor for noninstantiability.
  //   private EcoreUtil()
  //   {
  //   }

  /**
   * Returns the specified notifier's exisiting adapter of the specified type.
   * @param notifier the adapted object.
   * @param type the type of adapter.
   * @return an adapter associated with the specified notifier or null.
   */
  public static Adapter getExistingAdapter(Notifier notifier, Object type)
  {
    return getAdapter(notifier.eAdapters(), type);
  }

  /**
   * Returns the specified eObject's adapter of the specified type. If none exists, create and
   * add a new adapter using a registered adapter factory if one exists for the specified type.
   * @param eObject the adapted object.
   * @param type the type of adapter.
   * @return an adapter associated with the specified eObject or null.
   */
  public static Adapter getRegisteredAdapter(EObject eObject, Object type)
  {
    Adapter result = getExistingAdapter(eObject, type);
    if (result == null)
    {
      Resource resource = eObject.eResource();
      if (resource != null)
      {
        ResourceSet resourceSet = resource.getResourceSet();
        if (resourceSet != null)
        {
          AdapterFactory factory = getAdapterFactory(resourceSet.getAdapterFactories(), type);
          if (factory != null)
          {
            result = factory.adaptNew(eObject, type);
          }
        }
      }
    }
    return result;
  }

  /**
   * Returns the specified resource's adapter of the specified type. If none exists, create and
   * add a new adapter using a registered adapter factory if one exists for the specified type.
   * @param resource the adapted resource.
   * @param type the type of adapter.
   * @return an adapter associated with the specified eObject or null.
   */
  public static Adapter getRegisteredAdapter(Resource resource, Object type)
  {
    Adapter result = getExistingAdapter(resource, type);
    if (result == null)
    {
      ResourceSet resourceSet = resource.getResourceSet();
      if (resourceSet != null)
      {
        AdapterFactory factory = getAdapterFactory(resourceSet.getAdapterFactories(), type);
        if (factory != null)
        {
          result = factory.adaptNew(resource, type);
        }
      }
    }
    return result;
  }

  /**
   * Returns the adapter of the specified type.
   * @param adapters list of adapters to search.
   * @param type the type of adapter.
   * @return an adapter from the list or null.
   */
  public static Adapter getAdapter(List adapters, Object type)
  {
    for (int i = 0, size = adapters.size(); i < size; ++i)
    {
      Adapter adapter = (Adapter)adapters.get(i);
      if (adapter.isAdapterForType(type))
      {
        return adapter;
      }
    }
    return null;
  }

  /**
   * Returns the adapter factory for the specified adapter type.
   * @param adapterFactories list of adapter factories to search.
   * @param type the type of adapter.
   * @return an adapter factory from the list or null.
   */
  public static AdapterFactory getAdapterFactory(List adapterFactories, Object type)
  {
    for (int i = 0, size = adapterFactories.size(); i < size; ++i)
    {
      AdapterFactory factory = (AdapterFactory)adapterFactories.get(i);
      if (factory.isFactoryForType(type))
      {
        return factory;
      }
    }
    return null;
  }

  /**
   * Returns the resolved object represented by proxy. Proxy chains are followed.
   * If resourceSet is null, the global package registry is consulted to obtain a
   * package registered against the proxy URI, less its fragment, in the same manner
   * as the default resource set implementation's fallback behaviour.
   * @param proxy the proxy to be resolved.
   * @param resourceSet the resource set in which to resolve.
   * @return the resolved object, or the proxy if unable to resolve. 
   */
  public static EObject resolve(EObject proxy, ResourceSet resourceSet)
  {
    URI proxyURI = ((InternalEObject)proxy).eProxyURI();
    if (proxyURI != null)
    {
      try
      {
        EObject resolvedObject = null;

        if (resourceSet != null)
        {
          resolvedObject = resourceSet.getEObject(proxyURI, true);
        }
        else
        {
          EPackage ePackage = EPackage.Registry.INSTANCE.getEPackage(proxyURI.trimFragment().toString());
          if (ePackage != null)
          {
            Resource resource = ePackage.eResource();
            if (resource != null)
            {
              resolvedObject = resource.getEObject(proxyURI.fragment().toString());
            }
          }
        }

        if (resolvedObject != null && resolvedObject != proxy)
        {
          return resolve(resolvedObject, resourceSet);
        }
      }
      catch (RuntimeException exception)
      {
        // Failure to resolve is ignored.
      }
    }
    return proxy;
  }

  /**
   * Returns the resolved object represented by proxy. Proxy chains are followed.
   * If resourceContext is null or not in a resource set, the global package registry is
   * consulted to obtain a package registered against the proxy URI, less its fragment,
   * in the same manner as the default resource set implementation's fallback behaviour.
   * @param proxy the proxy to be resolved.
   * @param resourceContext a context resource whose resource set is used for the resolve.
   * @return the resolved object, or the proxy if unable to resolve.
   */
  public static EObject resolve(EObject proxy, Resource resourceContext)
  {
    return resolve(proxy, resourceContext != null ? resourceContext.getResourceSet() : null);
  }

  /**
   * Returns the resolved object represented by proxy. Proxy chains are followed.
   * If objectContext is null or not in a resource that is in a resource set, the
   * global package registry is consulted to obtain a package registered against
   * the proxy URI, less its fragment, in the same manner as the default resource
   * set implementation's fallback behaviour.
   * @param proxy the proxy to be resolved.
   * @param objectContext a context object whose resource set is used for the resolve.
   * @return the resolved object, or the proxy if unable to resolve.
   */
  public static EObject resolve(EObject proxy, EObject objectContext)
  {
    Resource resourceContext = objectContext != null ? objectContext.eResource() : null;
    return resolve(proxy, resourceContext != null ? resourceContext.getResourceSet() : null);
  }

  /**
   * Visits all proxies in the resource set and tries to resolve them.
   * @param resourceSet the objects to visit.
   */
  public static void resolveAll(ResourceSet resourceSet)
  {
    List resources = resourceSet.getResources();
    for (int i = 0; i < resources.size(); ++i)
    {
      resolveAll((Resource)resources.get(i));
    }
  }

  /**
   * Visits all proxies in the resource and tries to resolve them.
   * @param resource the objects to visit.
   */
  public static void resolveAll(Resource resource)
  {
    for (Iterator i = resource.getAllContents();  i.hasNext(); )
    {
      EObject eObject = (EObject)i.next();
      for (Iterator j =  eObject.eCrossReferences().iterator();  j.hasNext(); j.next())
      {
      }
    }
  }

  /**
   * Visits all proxies referenced by the object or recursively any of it's contained object.
   * @param eObject the object to visist.
   */
  public static void resolveAll(EObject eObject)
  {
    for (Iterator i = eObject.eAllContents(); i.hasNext(); )
    {
      EObject childEObject = (EObject)i.next();
      for (Iterator j =  childEObject.eCrossReferences().iterator();  j.hasNext(); j.next())
      {
      }
    }
  }

  /**
   * Returns the first collection member that {@link EClassifier#isInstance is an instance} of the type.
   * @param objects a collection of objects to check.
   * @param type the type of object to find.
   * @return the first object of the specified type.
   */
  public static Object getObjectByType(Collection objects, EClassifier type)
  {
    for (Iterator i = objects.iterator(); i.hasNext();)
    {
      Object object = i.next();
      if (type.isInstance(object))
      {
        return object;
      }
    }
    return null;
  }

  /**
   * Returns a collection containing each collection member that {@link EClassifier#isInstance is an instance} of the type.
   * @param objects a collection of objects to check.
   * @param type the type of object to find.
   * @return a collection of objects of the specified type.
   */
  public static Collection getObjectsByType(Collection objects, EClassifier type)
  {
    Collection result = new ArrayList();
    for (Iterator i = objects.iterator(); i.hasNext();)
    {
      Object object = i.next();
      if (type.isInstance(object))
      {
        result.add(object);
      }
    }
    return result;
  }

  /**
   * Returns a self-contained copy of the eObject.
   * @param eObject the object to copy.
   * @return the copy.
   * @see Copier
   */
  public static EObject copy(EObject eObject)
  {
    Copier copier = new Copier();
    EObject result = copier.copy(eObject);
    copier.copyReferences();
    return result;
  }

  /**
   * Returns a collection of the self-contained copies of each {@link EObject} in eObjects.
   * @param eObjects the collection of objects to copy.
   * @return the collection of copies.
   * @see Copier
   */
  public static Collection copyAll(Collection eObjects)
  {
    Copier copier = new Copier();
    Collection result = copier.copyAll(eObjects);
    copier.copyReferences();
    return result;
  }

  /**
   * A mapping building traverser of a collection of {@link EObject#eAllContents content trees};
   * the map is from {@link EObject} to <code>EObject</code>, i.e., from original to copy;
   * use {@link EcoreUtil#copy EcoreUtil.copy} or {@link EcoreUtil#copyAll EcoreUtil.copyAll} to do routine copies.
   * Since this implementation extends a Map implementation, it acts as the result of the over all copy.
   * The client can call {@link #copy copy} and {@link #copyAll copyAll} repeatedly.
   * When all the objects have been copied, 
   * the client should call {@link #copyReferences copyReferences} 
   * to copy the {@link #copyReference appropriate} {@link EObject#eCrossReferences cross references}.
   *<pre>
   *  Copier copier = new Copier();
   *  EObject result = copier.copy(eObject);
   *  Collection results = copier.copyAll(eObjects);
   *  copier.copyReferences();
   *</pre>
   * The copier delegates to {@link #copyContainment copyContainment}, {@link #copyAttribute copyAttribute} during the copy phase
   * and to {@link #copyReference copyReference}, during the cross reference phase.
   * This allows tailored handling through derivation.
   */
  public static class Copier extends HashMap
  {
    /**
     * Whether proxies should be resolved during copying.
     */
    protected boolean resolveProxies = true;
    
    /**
     * Creates an instance.
     */
    public Copier()
    {
    }
    
    /**
     * Creates an instance that resolves proxies or not as specified.
     * @param resolveProxies whether proxies should be resolved while copying.
     */
    public Copier(boolean resolveProxies)
    {
      this.resolveProxies = resolveProxies;
    }
     
    /**
     * Returns a collection containing a copy of each EObject in the given collection.
     * @param eObjects the collection of objects to copy.
     * @return the collection of copies.
     */
    public Collection copyAll(Collection eObjects)
    {
      Collection result = new ArrayList(eObjects.size());
      for (Iterator i = eObjects.iterator(); i.hasNext();)
      {
        result.add(copy((EObject)i.next()));
      }
      return result;
    }

    /**
     * Returns a copy of the given eObject.
     * @param eObject the object to copy.
     * @return the copy.
     */
    public EObject copy(EObject eObject)
    {
      EObject copyEObject = createCopy(eObject);
      put(eObject, copyEObject);
      EClass eClass = eObject.eClass();
      for (int i = 0, size = eClass.getFeatureCount(); i < size; ++i)
      {
        EStructuralFeature eStructuralFeature = eClass.getEStructuralFeature(i);
        if (eStructuralFeature.isChangeable() && !eStructuralFeature.isDerived())
        {
          if (eStructuralFeature instanceof EAttribute)
          {
            copyAttribute((EAttribute)eStructuralFeature, eObject, copyEObject);
          }
          else
          {
            EReference eReference = (EReference)eStructuralFeature;
            if (eReference.isContainment())
            {
              copyContainment(eReference, eObject, copyEObject);
            }
          }
        }
      }

      return copyEObject;
    }

    /**
     * Returns a new instance of the object's target class.
     * @param eObject the object to copy.
     * @return a new instance of the target class.
     * @see #getTarget(EClass)
     * @see EcoreUtil#create(EClass)
     */
    protected EObject createCopy(EObject eObject)
    {
      return create(getTarget(eObject.eClass()));
    }

    /**
     * Returns the target class used to create a copy instance for objects of the given source class.
     * @param eClass the source class.
     * @return the target class used to create a copy instance.
     * @see #getTarget(EStructuralFeature)
     */
    protected EClass getTarget(EClass eClass)
    {
      return eClass;
    }

    /**
     * Returns the target feature used to populate a copy instance from the given source feature.
     * @param eStructuralFeature the source feature.
     * @return the target feature used to populate a copy instance.
     * @see #getTarget(EClass)
     */
    protected EStructuralFeature getTarget(EStructuralFeature eStructuralFeature)
    {
      return eStructuralFeature;
    }

    /**
     * Called to handle the copying of a containment feature;
     * this adds a list of copies or sets a single copy as appropriate for the multiplicity.
     * @param eReference the feature to copy.
     * @param eObject the object from which to copy.
     * @param copyEObject the object to copy to.
     */
    protected void copyContainment(EReference eReference, EObject eObject, EObject copyEObject)
    {
      if (eObject.eIsSet(eReference))
      {
        if (eReference.isMany())
        {
          List source = (List)eObject.eGet(eReference);
          List target = (List)copyEObject.eGet(getTarget(eReference));
          if (source.isEmpty())
          {
            target.clear();
          }
          else
          {
            target.addAll(copyAll(source));
          }
        }
        else
        {
          EObject childEObject = (EObject)eObject.eGet(eReference);
          copyEObject.eSet(getTarget(eReference), childEObject == null ? null : copy(childEObject));
        }
      }
    }

    /**
     * Called to handle the copying of an attribute;
     * this adds a list of values or sets a single value as appropriate for the multiplicity.
     * @param eAttribute the attribute to copy.
     * @param eObject the object from which to copy.
     * @param copyEObject the object to copy to.
     */
    protected void copyAttribute(EAttribute eAttribute, EObject eObject, EObject copyEObject)
    {
      if (eObject.eIsSet(eAttribute))
      {
        if (FeatureMapUtil.isFeatureMap(eAttribute))
        {
          FeatureMap featureMap = (FeatureMap)eObject.eGet(eAttribute);
          for (int i = 0, size = featureMap.size(); i < size; ++i)
          {
            EStructuralFeature feature = featureMap.getEStructuralFeature(i);
            if (feature instanceof EReference && ((EReference)feature).isContainment())
            {
              Object value = featureMap.getValue(i);
              if (value != null)
              {
                copy((EObject)value);
              }
            }
          }
        }
        else if (eAttribute.isMany())
        {
          List source = (List)eObject.eGet(eAttribute);
          List target = (List)copyEObject.eGet(getTarget(eAttribute));
          if (source.isEmpty())
          {
            target.clear();
          }
          else
          {
            target.addAll(source);
          }
        }
        else
        {
          copyEObject.eSet(getTarget(eAttribute), eObject.eGet(eAttribute));
        }
      }
    }

    /**
     * Hooks up cross references; it delegates to {@link #copyReference copyReference}.
     */
    public void copyReferences()
    {
      for (Iterator i = entrySet().iterator(); i.hasNext();)
      {
        Map.Entry entry = (Map.Entry)i.next();
        EObject eObject = (EObject)entry.getKey();
        EObject copyEObject = (EObject)entry.getValue();
        EClass eClass = eObject.eClass();
        for (int j = 0, size = eClass.getFeatureCount(); j < size; ++j)
        {
          EStructuralFeature eStructuralFeature = eClass.getEStructuralFeature(j);
          if (eStructuralFeature.isChangeable() && !eStructuralFeature.isDerived())
          {
            if (eStructuralFeature instanceof EReference)
            {
              EReference eReference = (EReference)eStructuralFeature;
              if (!eReference.isContainment() && !eReference.isContainer())
              {
                copyReference(eReference, eObject, copyEObject);
              }
            }
            else if (FeatureMapUtil.isFeatureMap(eStructuralFeature))
            {
              FeatureMap featureMap = (FeatureMap)eObject.eGet(eStructuralFeature);
              FeatureMap copyFeatureMap = (FeatureMap)copyEObject.eGet(getTarget(eStructuralFeature));
              for (int k = 0, featureMapSize = featureMap.size(); k < featureMapSize; ++k)
              {
                EStructuralFeature feature = featureMap.getEStructuralFeature(k);
                if (feature instanceof EReference)
                {
                  Object referencedEObject = featureMap.getValue(k);
                  Object copyReferencedEObject = get(referencedEObject);
                  copyFeatureMap.add(feature, copyReferencedEObject == null ? referencedEObject : copyReferencedEObject);
                }
                else
                {
                  copyFeatureMap.add(featureMap.get(k));
                }
              }
            }
          }
        }
      }
    }

    /**
     * Called to handle the copying of a cross reference;
     * this adds values or sets a single value as appropriate for the multiplicity
     * while omitting any bidirectional reference that isn't in the copy map.
     * @param eReference the reference to copy.
     * @param eObject the object from which to copy.
     * @param copyEObject the object to copy to.
     */
    protected void copyReference(EReference eReference, EObject eObject, EObject copyEObject)
    {
      if (eObject.eIsSet(eReference))
      {
        if (eReference.isMany())
        {
          InternalEList source = (InternalEList)eObject.eGet(eReference);
          InternalEList target = (InternalEList)copyEObject.eGet(getTarget(eReference));
          if (source.isEmpty())
          {
            target.clear();
          }
          else
          {
            boolean isBidirectional = eReference.getEOpposite() != null;
            int index = 0;
            for (Iterator k = resolveProxies ? source.iterator() : source.basicIterator(); k.hasNext();)
            {
              Object referencedEObject = k.next();
              Object copyReferencedEObject = get(referencedEObject);
              if (copyReferencedEObject == null)
              {
                if (!isBidirectional)
                {
                  target.addUnique(index, referencedEObject);
                  ++index;
                }
              }
              else
              {
                if (isBidirectional)
                {
                  int position = target.indexOf(copyReferencedEObject);
                  if (position == -1)
                  {
                    target.addUnique(index, copyReferencedEObject);
                  }
                  else if (index != position)
                  {
                    target.move(index, copyReferencedEObject);
                  }
                }
                else
                {
                  target.addUnique(index, copyReferencedEObject);
                }
                ++index;
              }
            }
          }
        }
        else
        {
          Object referencedEObject = eObject.eGet(eReference, resolveProxies);
          if (referencedEObject == null)
          {
            copyEObject.eSet(getTarget(eReference), null);
          }
          else
          {
            Object copyReferencedEObject = get(referencedEObject);
            if (copyReferencedEObject == null)
            {
              if (eReference.getEOpposite() == null)
              {
                copyEObject.eSet(getTarget(eReference), referencedEObject);
              }
            }
            else
            {
              copyEObject.eSet(getTarget(eReference), copyReferencedEObject);
            }
          }
        }
      }
    }
  }

  /**
   * Returns the root container;
   * it may be this object itself
   * and it will have a <code>null</code> {@link EObject#eContainer container}.
   * <p>
   * The root container must be {@link Resource#getContents directly contained} in a resource
   * for its {@link EObject#eAllContents tree} to be {@link Resource#save(java.util.Map) serializable}.
   * </p>
   * @param eObject the object to get the root container for.
   * @return the root container.
   * @see #getRootContainer(EObject, boolean)
   * @see EObject#eResource()
   * @see EObject#eContainer()
   */
  public static EObject getRootContainer(EObject eObject)
  {
    EObject result = eObject;
    for (EObject parent = eObject; parent != null; parent = parent.eContainer())
    {
      result = parent;
    }
    return result;
  }
  
  /**
   * Returns the root container;
   * it may be this object itself
   * and it will have a <code>null</code> {@link EObject#eContainer container}.
   * Container proxies are resolved or not as specified
   * <p>
   * The root container must be {@link Resource#getContents directly contained} in a resource
   * for its {@link EObject#eAllContents tree} to be {@link Resource#save(java.util.Map) serializable}.
   * </p>
   * @param eObject the object to get the root container for.
   * @param resolve whether to resolve container proxies.
   * @return the root container.
   * @see #getRootContainer(EObject)
   * @see EObject#eResource()
   * @see InternalEObject#eInternalContainer()
   */
  public static EObject getRootContainer(EObject eObject, boolean resolve)
  {
    if (resolve)
    {
      return getRootContainer(eObject);
    }
    else
    {
      EObject result = eObject;
      for (InternalEObject parent = (InternalEObject)eObject; parent != null; parent = parent.eInternalContainer())
      {
        result = parent;
      }
      return result;
    }
  }

  /**
   * Returns whether the second object is directly or indirectly contained by the first object,
   * i.e., whether the second object is in the {@link EObject#eContents content tree} of the first.
   * Container proxies are not resolved.
   * @param ancestorEObject the ancestor object in question.
   * @param eObject the object to test.
   * @return whether the first object is an ancestor of the second object.
   * @see EObject#eContainer
   */
  public static boolean isAncestor(EObject ancestorEObject, EObject eObject)
  {
    if (eObject != null)
    {
      if (eObject == ancestorEObject)
      {
        return true;
      }
      
      InternalEObject eContainer = ((InternalEObject)eObject).eInternalContainer();
      while (eContainer != null)
      {
        if (eContainer == ancestorEObject)
        {
          return true;
        }
        eContainer = eContainer.eInternalContainer();
      }
    }
    return false;
  }

  /**
   * Returns whether the given resource is that of the object, 
   * i.e., whether the object is in the {@link Resource#getContents content tree} of the resource.
   * @param ancestorResource the ancestor resource in question.
   * @param eObject the object to test.
   * @return whether the resource is an ancestor of the object.
   * @see EObject#eContainer
   * @see EObject#eResource
   */
  public static boolean isAncestor(Resource ancestorResource, EObject eObject)
  {
    for (InternalEObject parent = (InternalEObject)eObject; parent != null; parent = parent.eInternalContainer())
    {
      if (parent.eDirectResource() == ancestorResource)
      {
        return true;
      }
    }
    return false;
  }

  /**
   * Returns whether the given resource set is that of the object, 
   * i.e., whether the object is in the {@link Resource#getContents content tree} of the resource set.
   * @param ancestorResourceSet the ancestor resource set in question.
   * @param eObject the object to test.
   * @return whether the resource set is an ancestor of the object.
   * @see EObject#eContainer
   * @see EObject#eResource
   * @see Resource#getResourceSet
   */
  public static boolean isAncestor(ResourceSet ancestorResourceSet, EObject eObject)
  {
    for (InternalEObject parent = (InternalEObject)eObject; parent != null; parent = parent.eInternalContainer())
    {
      Resource resource = parent.eDirectResource();
      if (resource != null && resource.getResourceSet() == ancestorResourceSet)
      {
        return true;
      }
    }
    return false;
  }

  /**
   * Returns whether any EObject, Resource, or ResourceSet in the collection is an ancestor of the EObject.
   * @param ancestorEMFObjects the collection of ancestor objects in question.
   * @param eObject the object to test.
   * @return whether any object in the collection is an ancestor of the object.
   */
  public static boolean isAncestor(Collection ancestorEMFObjects, EObject eObject)
  {
    for (InternalEObject parent = (InternalEObject)eObject; parent != null; parent = parent.eInternalContainer())
    {
      if (ancestorEMFObjects.contains(parent))
      {
        return true;
      }
      Resource resource = parent.eDirectResource();
      if (resource != null && (ancestorEMFObjects.contains(resource) || ancestorEMFObjects.contains(resource.getResourceSet())))
      {
        return true;
      }
    }
    return false;
  }

  /**
   * Returns a tree iterator over the content trees 
   * recursively defined by 
   * {@link ResourceSet#getResources()},
   * {@link Resource#getContents()},
   * and {@link EObject#eContents()}.
   * It uses a special iterator for ResourceSet.getResources 
   * that is tolerant of growth in the underlying collection 
   * which result from demand loaded resources;
   * the iterator will walk these additional resources.
   * @param emfObjects the collection of objects to iterate over.
   * @return a tree iterator over the objects and their contents.
   * @see ContentTreeIterator
   */
  public static TreeIterator getAllContents(Collection emfObjects)
  {
    return new ContentTreeIterator(emfObjects);
  }
  
  /**
   * Returns a tree iterator over the content trees 
   * recursively defined by 
   * {@link ResourceSet#getResources()},
   * {@link Resource#getContents()},
   * and {@link EObject#eContents()}.
   * It uses a special iterator for ResourceSet.getResources 
   * that is tolerant of growth in the underlying collection 
   * which result from demand loaded resources;
   * the iterator will walk these additional resources.
   * Contained proxies are resolved or not as specified.
   * @param emfObjects the collection of objects to iterate over.
   * @param resolve whether proxies should be resolved.
   * @return a tree iterator over the objects and their contents.
   * @see ContentTreeIterator
   */
  public static TreeIterator getAllContents(Collection emfObjects, boolean resolve)
  {
    return  new ContentTreeIterator(emfObjects, resolve);
  }
  
  /**
   * Returns a tree iterator that iterates over all the {@link EObject#eContents direct contents} and indirect contents of the object.
   * @param eObject the object to iterate over.
   * @param resolve whether proxies should be resolved.
   * @return a tree iterator that iterates over all contents.
   * @see EObject#eAllContents
   * @see Resource#getAllContents
   */
  public static TreeIterator getAllContents(EObject eObject, boolean resolve)
  {
    return  
      new ContentTreeIterator(eObject, resolve)
      {
        public Iterator getChildren(Object object)
        {
          return getEObjectChildren((EObject)object);
        }
      };
  }
  
  /**
   * Returns a tree iterator that iterates over all the {@link Resource#getContents direct contents} and indirect contents of the resource.
   * @param resource the resource to iterate over.
   * @param resolve whether proxies should be resolved.
   * @return a tree iterator that iterates over all contents.
   * @see EObject#eAllContents
   * @see ResourceSet#getAllContents
   */
  public static TreeIterator getAllContents(Resource resource, boolean resolve)
  {
    return  
      new ContentTreeIterator(resource.getContents(), resolve)
      {
        public Iterator getChildren(Object object)
        {
          return object == this.object ? ((List)this.object).iterator() : getEObjectChildren((EObject)object);
        }
      };
  }
  
  /**
   * Returns a tree iterator that iterates over all the {@link ResourceSet#getResources direct resources} in the resource set
   * and over the content {@link Resource#getAllContents tree} of each. 
   * @param resourceSet the resource set to iterate over.
   * @param resolve whether proxies should be resolved.
   * @return a tree iterator that iterates over all contents.
   * @see EObject#eAllContents
   * @see Resource#getAllContents
   * @see org.eclipse.emf.ecore.util.EcoreUtil#getAllContents
   */
  public static TreeIterator getAllContents(ResourceSet resourceSet, boolean resolve)
  {
    return new ContentTreeIterator(resourceSet, resolve);
  }
  
  /**
   * Returns a tree iterator over the content trees 
   * recursively defined by 
   * {@link ResourceSet#getResources()},
   * {@link Resource#getContents()},
   * and {@link EObject#eContents()},
   * skipping over any child object that's in a different resource from its parent.
   * It uses a special iterator for ResourceSet.getResources 
   * that is tolerant of growth in the underlying collection 
   * which result from demand loaded resources;
   * the iterator will walk these additional resources.
   * Contained proxies are resolved or not as specified.
   * @param emfObjects the collection of objects to iterate over.
   * @param resolve whether proxies should be resolved.
   * @return a tree iterator over the objects and their contents.
   * @see ContentTreeIterator
   */
  public static TreeIterator getAllProperContents(Collection emfObjects, boolean resolve)
  {
    return  
      new ContentTreeIterator(emfObjects, resolve)
      {
        public Iterator getEObjectChildren(EObject eObject)
        {
          return new ProperContentIterator(eObject, isResolveProxies());
        }
      };
  }
  
  /**
   * Returns a tree iterator that iterates over all the {@link EObject#eContents direct contents} and indirect contents of the object,
   * skipping over any child object that's in a different resource from its parent.
   * @param eObject the object to iterate over.
   * @param resolve whether proxies should be resolved.
   * @return a tree iterator that iterates over all contents.
   * @see EObject#eAllContents
   * @see Resource#getAllContents
   */
  public static TreeIterator getAllProperContents(EObject eObject, boolean resolve)
  {
    return  
      new ContentTreeIterator(eObject, resolve)
      {
        public Iterator getChildren(Object object)
        {
          return new ProperContentIterator(((EObject)object), isResolveProxies());
        }
      };
  }
  
  /**
   * Returns a tree iterator that iterates over all the {@link Resource#getContents direct contents} and indirect contents of the resource,
   * skipping over any child object that's in a different resource from its parent.
   * @param resource the resource to iterate over.
   * @param resolve whether proxies should be resolved.
   * @return a tree iterator that iterates over all contents.
   * @see EObject#eAllContents
   * @see ResourceSet#getAllContents
   */
  public static TreeIterator getAllProperContents(Resource resource, boolean resolve)
  {
    return  
      new ContentTreeIterator(resource.getContents(), resolve)
      {
        public Iterator getChildren(Object object)
        {
          if (object == this.object)
          {
            return ((Collection)object).iterator();
          }
          else
          {
            return new ProperContentIterator(((EObject)object), isResolveProxies());
          }
        }
      };
  }
  
  /**
   * Returns a tree iterator that iterates over all the {@link ResourceSet#getResources direct resources} in the resource set
   * and over the content {@link Resource#getAllContents tree} of each,
   * skipping over any child object that's in a different resource from its parent.
   * @param resourceSet the resource set to iterate over.
   * @param resolve whether proxies should be resolved.
   * @return a tree iterator that iterates over all contents.
   * @see EObject#eAllContents
   * @see Resource#getAllContents
   * @see org.eclipse.emf.ecore.util.EcoreUtil#getAllContents
   */
  public static TreeIterator getAllProperContents(ResourceSet resourceSet, boolean resolve)
  {
    return  
      new ContentTreeIterator(resourceSet, resolve)
      {
        public Iterator getEObjectChildren(EObject eObject)
        {
          return new ProperContentIterator(((EObject)object), isResolveProxies());
        }
      };
  }
  
  /**
   * An iterator that can skip over items in a list.
   */
  public static class ProperContentIterator implements Iterator
  {
    protected Iterator iterator;
    protected Object preparedResult;
                 
    public ProperContentIterator(List contents)
    {
      iterator = contents.iterator();
    }
    
    public ProperContentIterator(InternalEList basicContents)
    {
      iterator = basicContents.basicIterator();
    }
    
    public ProperContentIterator(EObject eObject)
    {
      this(eObject, false);
    }
    
    public ProperContentIterator(EObject eObject, boolean isResolveProxies)
    {
      EList contents = eObject.eContents();
      iterator = 
        !isResolveProxies && contents instanceof InternalEList ?
          ((InternalEList)contents).basicIterator() :
            contents.iterator();
    }
    
    public boolean hasNext()
    {
      if (preparedResult == null)
      {
        while (iterator.hasNext())
        { 
          preparedResult = iterator.next();
          if (((InternalEObject)preparedResult).eDirectResource() == null)
          {
            return true;
          }
        }
        preparedResult = null;
        return false;
      }
      else
      {
        return true;
      }
    }
    
    public Object next()
    {
      hasNext();
      Object result = preparedResult;
      preparedResult = null;
      return result;
    }
 
    public void remove()
    {
      iterator.remove();
    }
  }

  /**
   * An iterator over the tree contents of a collection of EObjects, Resources, and ResourceSets;
   * use {@link EcoreUtil#getAllContents getAllContents} to create a new instance.
   * It provides a special iterator for ResourceSet.getResources 
   * that is tolerant of growth in the underlying collection 
   * which result from demand loaded resources;
   * the iterator will walk these additional resources.
   */
  public static class ContentTreeIterator extends AbstractTreeIterator
  {
    /**
     *  Whether proxies should be resolved.
     */
    protected boolean isResolveProxies;
    
    /**
     * The most recently created iterator over a resource set's resources.
     * This iterator needs special handling because it returns <code>true</code> for <code>hasNext()</code> 
     * even when the list is exhausted.
     * This ensures that any growth in the resources list will not be overlooked.
     */
    protected ResourcesIterator resourceSetIterator;

    /**
     * Creates an instance for the given collection of objects.
     * @param emfObjects the collection of objects to iterate over.
     */
    protected ContentTreeIterator(Collection emfObjects)
    {
      super(emfObjects, false);
    }

    /**
     * Creates an instance for the given collection of objects.
     * @param emfObjects the collection of objects to iterate over.
     */
    protected ContentTreeIterator(Object object, boolean isResolveProxies)
    {
      super(object, false);
      this.isResolveProxies = isResolveProxies;
    }
    
    /**
     * Returns an iterator over the children of the given parent object.
     * @param object the parent object.
     * @return the children iterator.
     */
    public Iterator getChildren(Object object)
    {
      if (object instanceof EObject)
      {
        return getEObjectChildren((EObject)object);
      }
      else if (object instanceof Resource)
      {
        return getResourceChildren((Resource)object);
      }
      else if (object instanceof ResourceSet)
      {
        return getResourceSetChildren((ResourceSet)object);
      }
      else if (object == this.object)
      {
        return ((Collection)object).iterator();
      }
      else
      {
        return getObjectChildren(object);
      }
    }

    /**
     * Returns an iterator over the {@link EObject#eContents() children} of the given parent EObject.
     * @param eObject the parent object.
     * @return the children iterator.
     */
    protected Iterator getEObjectChildren(EObject eObject)
    {
      return isResolveProxies() ? eObject.eContents().iterator() : ((InternalEList)eObject.eContents()).basicIterator();
    }
    
    /**
     * Returns whether proxies should resolve.
     * @return whether proxies should resolve.
     */
    protected boolean isResolveProxies()
    {
      return isResolveProxies;
    }

    /**
     * Returns an iterator over the {@link Resource#getContents() children} of the given parent resource.
     * @param resource the parent resource.
     * @return the children iterator.
     */
    protected Iterator getResourceChildren(Resource resource)
    {
      return resource.getContents().iterator();
    }

    /**
     * Returns the next object and advances the iterator.
     * This override handles the {@link #resourceSetIterator} in a special way,
     * i.e., it skips the null object that iterator yields as the last fake item of the list.
     * @return the next object.
     */
    public boolean hasNext()
    {
      Iterator iterator;
      if (!includeRoot && data == null)
      {
        nextPruneIterator = getChildren(object);
        add(nextPruneIterator);
        iterator = nextPruneIterator;
      }
      else
      {
        // We don't create an iterator stack until the root mapping itself has been returned by next once.
        // After that the stack should be non-empty and the top iterator should yield true for hasNext.
        //
        if (data == null)
        {
          return true;
        }
        else if (isEmpty())
        {
          return false;
        }
        else
        {
          iterator = (Iterator)data[size - 1];
        }
      }

      // If we are on the special resource set iterator, and there isn't really a next object at this point...
      //
      if (iterator == resourceSetIterator && !resourceSetIterator.reallyHasNext())
      {
        // Skip the dummy null object and test again.
        //
        next();
        return hasNext();
      }
      else
      {
        return iterator.hasNext();
      }
    }

    /**
     * A special iterator that's tolerant of growth in the list of resources 
     * which can result from demand loading when traversing the tree of contents.
     */
    protected static class ResourcesIterator implements Iterator
    {
      /**
       * The resources to iterator over.
       */
      protected List resources;

      /**
       * The current index of the iterator.
       */
      protected int index = 0;

      /**
       * Constructs an instance.
       * @param resources the list of resources.
       */
      public ResourcesIterator(List resources)
      {
        this.resources = resources;
      }

      /**
       * Returns whether there really are any resources left.
       * @return whether there really are any resources left.
       */
      public boolean reallyHasNext()
      {
        return index < resources.size();
      }

      /**
       * Returns whether there might be resources left by the time we next check.
       * This returns <code>true</code> when the index is equal to the size, 
       * because the tree iterator will still be set to yield the last of the contents of the resource, 
       * and accessing that may cause another resource to be loaded.
       * @return whether there might be resources left by the time we next check.
       */
      public boolean hasNext()
      {
        return index <= resources.size();
      }

      /**
       * Returns the next item, or <code>null</code> if there isn't one.
       * @return the next item, or <code>null</code> if there isn't one.
       */
      public Object next()
      {
        if (index >= resources.size())
        {
          ++index;
          return null;
        }
        else
        {
          return resources.get(index++);
        }
      }

      /**
       * @throws UnsupportedOperationException always.
       */
      public void remove()
      {
        throw new UnsupportedOperationException();
      }
    }

    /**
     * Returns an iterator over the {@link ResourceSet#getResources() children} of the given parent resource set.
     * It also records the result in {@link #resourceSetIterator} for special handling in {@link #hasNext()}.
     * @param resourceSet the parent resource set.
     * @return the children iterator.
     */
    protected Iterator getResourceSetChildren(ResourceSet resourceSet)
    {
      return resourceSetIterator = new ResourcesIterator(resourceSet.getResources());
    }

    /**
     * Returns an empty iterator; subclasses may override this method.
     * @param object the parent object.
     * @return the children iterator.
     */
    protected Iterator getObjectChildren(Object object)
    {
      return ECollections.EMPTY_ELIST.iterator();
    }
  }

  /**
   * A mapping building traverser of a collection of {@link EcoreUtil#getAllContents content trees};
   * the map is from target {@link EObject object} to a collection of {@link org.eclipse.emf.ecore.EStructuralFeature.Setting}.
   * Since this implementation extends a Map implementation, it can yield itself as the result for most operations.
   * The traverser considers each EObject in the {@link EObject#eCrossReferences} of each EObject in the content tree,
   * and creates a setting for each positive match.
   * This default implementation {@link #find creates} a map of all cross references.
   */
  public static class CrossReferencer extends HashMap
  {
    /**
     * The collection of objects being cross referenced.
     */
    protected Collection emfObjects;

    /**
     * Creates an instance for the given object.
     * @param eObject the object to cross reference.
     */
    protected CrossReferencer(EObject eObject)
    {
      this.emfObjects = Collections.singleton(eObject);
    }

    /**
     * Creates an instance for the given resource.
     * @param resource the resource to cross reference.
     */
    protected CrossReferencer(Resource resource)
    {
      this.emfObjects = Collections.singleton(resource);
    }

    /**
     * Creates an instance for the given resource set.
     * @param resourceSet the resource set to cross reference.
     */
    protected CrossReferencer(ResourceSet resourceSet)
    {
      this.emfObjects = Collections.singleton(resourceSet);
    }

    /**
     * Creates an instance for the given collection of objects.
     * @param emfObjects the collection of objects to cross reference.
     */
    protected CrossReferencer(Collection emfObjects)
    {
      this.emfObjects = emfObjects;
    }

    /**
     * Return true if the cross referencer should include references from children of the specified object.
     * @param eObject an object in the cross referencer's content tree.
     * @return if the cross referencer should include references from children of the object.
     */
    protected boolean containment(EObject eObject)
    {
      return true;
    }

    /**
     * Return true if the specified eReference from eObject to crossReferencedEObject should be
     * considiered a cross reference by this cross referencer.
     * @param eObject an object in the cross referencer's content tree.
     * @param eReference a reference from the object.
     * @param crossReferencedEObject the target of the specified reference.
     * @return if the cross referencer should consider the specified reference a cross reference.
     */
    protected boolean crossReference(EObject eObject, EReference eReference, EObject crossReferencedEObject)
    {
      return true;
    }

    /**
     * Return true if cross references that are proxies should be resolved.
     * @return if the cross referencer should resolve proxies.
     */
    protected boolean resolve()
    {
      return true;
    }

    /**
     * Return a collection to use for storing {@link org.eclipse.emf.ecore.EStructuralFeature.Setting settings}.
     * @return a collection for settings.
     */
    protected Collection newCollection()
    {
      return new ArrayList();
    }

    /**
     * Return the collection of cross reference {@link org.eclipse.emf.ecore.EStructuralFeature.Setting settings}
     * for the specified key (target object).
     * @param key the key for the cross referencer's map.
     * @return the collection of settings.
     */
    protected Collection getCollection(Object key)
    {
      Collection result = (Collection)get(key);
      if (result == null)
      {
        put(key, result = newCollection());
      }
      return result;
    }

    /**
     * Return a tree iterator over the content trees of this cross referencer's objects.
     * @return a tree iterator over content trees.
     */
    protected TreeIterator newContentsIterator()
    {
      return new ContentTreeIterator(emfObjects);
    }

    /**
     * Compute the map of cross references.
     */
    protected void crossReference()
    {
      for (TreeIterator contents = newContentsIterator(); contents.hasNext();)
      {
        Object content = contents.next();
        if (content instanceof EObject)
        {
          EObject eObject = (EObject)content;
          if (containment(eObject))
          {
            handleCrossReference(eObject);
          }
          else
          {
            contents.prune();
          }
        }
      }
    }

    protected EContentsEList.FeatureIterator getCrossReferences(EObject eObject)
    {
      return 
        (EContentsEList.FeatureIterator)
          (resolve() ? 
            eObject.eCrossReferences().iterator() : 
            ((InternalEList)eObject.eCrossReferences()).basicIterator()); 
    }

    protected void handleCrossReference(EObject eObject)
    {
      InternalEObject internalEObject = (InternalEObject)eObject;
      for (EContentsEList.FeatureIterator crossReferences = getCrossReferences(internalEObject); crossReferences.hasNext();)
      {
        EObject crossReferencedEObject = (EObject)crossReferences.next();
        if (crossReferencedEObject != null)
        {
          EReference eReference = (EReference)crossReferences.feature();
          if (crossReference(internalEObject, eReference, crossReferencedEObject))
          {
            getCollection(crossReferencedEObject).add(internalEObject.eSetting(eReference));
          }
        }
      }
    }

    /**
     * Reset this cross referencer's object set.
     */
    protected void done()
    {
      emfObjects = null;
    }

    /**
     * Returns a map of all cross references in the content tree.
     * @param emfObjects a collection of objects whose combined content trees should be considered.
     * @return a map of cross references.
     */
    public static Map find(Collection emfObjects)
    {
      CrossReferencer result = new CrossReferencer(emfObjects);
      result.crossReference();
      result.done();
      return result;
    }

    /**
     * Returns a string representation of this cross referencer.
     * @return the string representation.
     */
    public String toString()
    {
      StringBuffer result = new StringBuffer("{"); // }

      for (Iterator i = entrySet().iterator(); i.hasNext();)
      {
        Map.Entry entry = (Map.Entry)i.next();
        EObject eObject = (EObject)entry.getKey();
        result.append(getIdentification(eObject));
        result.append("=[");
        Collection collection = (Collection)entry.getValue();
        for (Iterator j = collection.iterator(); j.hasNext();)
        {
          EStructuralFeature.Setting setting = (EStructuralFeature.Setting)j.next();
          EStructuralFeature eStructuralFeature = setting.getEStructuralFeature();
          result.append(eStructuralFeature.getName());
          result.append("<-");
          result.append(getIdentification(setting.getEObject()));
          if (j.hasNext())
          {
            result.append(", ");
          }
        }
        result.append(']');
      }

      // {
      result.append('}');
      return result.toString();
    }

    /**
     * Print the specified cross reference map to the specified stream.
     * @param out the stream to print to.
     * @param crossReferenceMap a map (cross referencer) to print.
     */
    public static void print(PrintStream out, Map crossReferenceMap)
    {
      out.println('{'); // }

      for (Iterator i = crossReferenceMap.entrySet().iterator(); i.hasNext();)
      {
        Map.Entry entry = (Map.Entry)i.next();
        EObject eObject = (EObject)entry.getKey();
        out.print(" ");
        out.print(getIdentification(eObject));
        Collection collection = (Collection)entry.getValue();
        if (collection.isEmpty())
        {
          out.println(" =[]");
        }
        else
        {
          out.println(" =[");
          for (Iterator j = collection.iterator(); j.hasNext();)
          {
            EStructuralFeature.Setting setting = (EStructuralFeature.Setting)j.next();
            EStructuralFeature eStructuralFeature = setting.getEStructuralFeature();
            out.print("   ");
            out.print(eStructuralFeature.getName());
            out.print("<-");
            out.print(getIdentification(setting.getEObject()));
            if (j.hasNext())
            {
              out.println(",");
            }
          }
          out.println(']');
        }
      }

      // {
      out.println('}');
    }

    /**
     * Print the specified collection of {@link org.eclipse.emf.ecore.EStructuralFeature.Setting settings}
     * to the specified stream.
     * @param out the stream to print to.
     * @param settings a collection of settings.
     */
    public static void print(PrintStream out, Collection settings)
    {
      if (settings.isEmpty())
      {
        out.println("[]");
      }
      else
      {
        out.println("[");
        for (Iterator j = settings.iterator(); j.hasNext();)
        {
          EStructuralFeature.Setting setting = (EStructuralFeature.Setting)j.next();
          EStructuralFeature eStructuralFeature = setting.getEStructuralFeature();
          out.print(" ");
          out.print(eStructuralFeature.getName());
          out.print("<-");
          out.print(getIdentification(setting.getEObject()));
          if (j.hasNext())
          {
            out.println(",");
          }
        }
        out.println(']');
      }
    }
  }

  /**
   * A cross referencer that finds all references that are not contained within the content trees.
   */
  public static class ExternalCrossReferencer extends CrossReferencer
  {
    /**
     * Creates an instance for the given collection of objects.
     * @param emfObjects the collection of objects to cross reference.
     */
    protected ExternalCrossReferencer(Collection emfObjects)
    {
      super(emfObjects);
    }

    /**
     * Creates an instance for the given object.
     * @param eObject the object to cross reference.
     */
    protected ExternalCrossReferencer(EObject eObject)
    {
      super(eObject);
    }

    /**
     * Creates an instance for the given resource.
     * @param resource the resource to cross reference.
     */
    protected ExternalCrossReferencer(Resource resource)
    {
      super(Collections.singleton(resource));
    }

    /**
     * Creates an instance for the given resource set.
     * @param resourceSet the resource set to cross reference.
     */
    protected ExternalCrossReferencer(ResourceSet resourceSet)
    {
      super(Collections.singleton(resourceSet));
    }

    /**
     * Return true if the specified eReference from eObject to crossReferencedEObject should be
     * considiered a cross reference by this cross referencer.
     * @param eObject an object in the cross referencer's content tree.
     * @param eReference a reference from the object.
     * @param crossReferencedEObject the target of the specified reference.
     * @return if the cross referencer should consider the specified reference a cross reference.
     */
    protected boolean crossReference(EObject eObject, EReference eReference, EObject crossReferencedEObject)
    {
      return !isAncestor(emfObjects, crossReferencedEObject);
    }

    /**
     * Returns the map of external cross references for this cross referencer.
     * @return a map of cross references.
     */
    protected Map findExternalCrossReferences()
    {
      crossReference();
      done();
      return this;
    }

    /**
     * Returns a map of all external cross references from the specified content tree.
     * @param eObject an object whose content trees should be considered.
     * @return a map of cross references.
     */
    public static Map find(EObject eObject)
    {
      return new ExternalCrossReferencer(eObject).findExternalCrossReferences();
    }

    /**
     * Returns a map of all external cross references from the specified content tree.
     * @param resource a resource whose content tree should be considered.
     * @return a map of cross references.
     */
    public static Map find(Resource resource)
    {
      return new ExternalCrossReferencer(resource).findExternalCrossReferences();
    }

    /**
     * Returns a map of all external cross references from the specified content tree.
     * @param resourceSet a resourceSet whose content tree should be considered.
     * @return a map of cross references.
     */
    public static Map find(ResourceSet resourceSet)
    {
      return new ExternalCrossReferencer(resourceSet).findExternalCrossReferences();
    }

    /**
     * Returns a map of all external cross references from the combined content trees of the specified collection of objects.
     * @param emfObjectsToSearch a collection of objects whose combined content trees should be considered.
     * @return a map of cross references.
     */
    public static Map find(Collection emfObjectsToSearch)
    {
      return new ExternalCrossReferencer(emfObjectsToSearch).findExternalCrossReferences();
    }
  }

  /**
   * Returns <code>true</code> if <code>eObject1</code> and <code>eObject2</code> are {@link EqualityHelper equal}, 
   * <code>false</code> otherwise.
   * @return whether <code>eObject1</code> and <code>eObject2</code> are equal.
   * @see EqualityHelper
   * @since 2.1.0
   */
  public static boolean equals(EObject eObject1, EObject eObject2)
  {
    EqualityHelper equalityHelper = new EqualityHelper();
    return equalityHelper.equals(eObject1, eObject2);
  }

  /**
   * <p>
   * A helper for determining whether two {@link EObject}s are <em>structurally equal</em>.
   * Two EObjects, <code>eObject1</code> and <code>eObject2</code>, are structurally equal 
   * if their {@link EObject.eClass() classes} are the same,
   * and if, for each {@link EStructuralFeature#isDerived non-derived} {@link EStructuralFeature feature} of the class, 
   * the {@link EObject.eIsSet(EStructuralFeature) isSet} states are the same
   * and the corresponding {@link EObject#eGet(EStructuralFeature) values} are structurally equal 
   * as appropriate for the type of feature.
   * For {@link FeatureMapUtil.isFeatureMap() feature map} features, 
   * the positionally corresponding {@link FeatureMap.Entry entries}
   * must have the same {@link FeatureMap.Entry#getEStructuralFeature() entry feature}s
   * and must have structurally equal {@link FeatureMap.Entry#getValue() value}s
   * as appropriate for the type of entry's feature.
   * For {@link EReference reference} features, 
   * the corresponding values must recursively be structurally equal according to this definition.
   * For {@link EAttribute attribute} features, 
   * the corresponding values must be equal according to Java equality.
   * Note that container references are derived and hence are ignored.
   * </p>
   * <p>
   * During the recursive process of determining {@link EcoreUtil#equals(EObject,EObject) equality},
   * the helper instance is populated as a two way map 
   * such that a given <code>eObject1</code> is considered to be equal to at most one other <code>eObject2</code>,
   * i.e., <code>get(eObject1) == eObject2 && get(eObject2) == eObject1</code>.
   * While their features are being compared, the two objects are assumed to be equal:
   *<pre> 
   *  put(eObject1, eObject2); 
   *  put(eObject2, eObject1);
   *</pre>
   * Once that correspondence is established, an <code>eObject1</code> considered equal to a different <code>eObject2</code>
   * will not even be considered equal to itself.
   * This ensures that two objects are structurally equal only if the graphs formed by all their referenced objects 
   * have the same topology.
   * </p> 
   * @see EcoreUtil#equals(EObject, EObject)
   * @see EqualityHelper#equals(EObject, EObject)
   */
  public static class EqualityHelper extends HashMap
  {
    /**
     * Returns whether <code>eObject1</code> and <code>eObject2</code> are {@link EqualityHelper equal}
     * in the context of this helper instance.
     * @return whether <code>eObject1</code> and <code>eObject2</code> are equal.
     * @since 2.1.0
     */
    public boolean equals(EObject eObject1, EObject eObject2)
    {
      // If the first object is null, the second object must be null.
      //
      if (eObject1 == null)
      {
        return eObject2 == null;
      }
      
      // We know the first object isn't null, so if the second one is, it can't be equal.
      //
      if (eObject2 == null)
      {
        return false;
      }

      // Both eObject1 and eObject2 are not null.
      // If eObject1 has been compared already...
      //
      Object eObject1MappedValue = get(eObject1);
      if (eObject1MappedValue != null)
      {
        // Then eObject2 must be that previous match.
        //
        return eObject1MappedValue == eObject2;
      }

      // If eObject2 has been compared already...
      //
      Object eObject2MappedValue = get(eObject2);
      if (eObject2MappedValue != null)
      {
        // Then eObject1 must be that match.
        //
        return eObject2MappedValue == eObject1;
      }

      // Neither eObject1 nor eObject2 have been compared yet.

      // If eObject1 and eObject2 are the same instance...
      //
      if (eObject1 == eObject2)
      { 
        // Match them and return true.
        //
        put(eObject1, eObject2);
        put(eObject2, eObject1);
        return true;
      }

      // If they don't have the same class, they can't be equal.
      //
      EClass eClass = eObject1.eClass();
      if (eClass != eObject2.eClass())
      {
        return false;
      }

      // Assume from now on that they match.
      //
      put(eObject1, eObject2);
      put(eObject2, eObject1);

      
      // Check all the values.
      //
      for (int i = 0, size = eClass.getFeatureCount(); i < size; ++i)
      {
        // Ignore derived features.
        //
        EStructuralFeature feature = eClass.getEStructuralFeature(i);
        if (!feature.isDerived())
        {
          if (!haveEqualFeature(eObject1, eObject2, feature))
          {
            return false;
          }
        }
      }
      
      // There's no reason they aren't equal, so they are.
      //
      return true;
    }
    
    /**
     * Returns whether <code>list1</code> and <code>list2</code> contain 
     * {@link #equals(EObject, EObject) equal} {@link EObject}s at the same index.
     * It is assumed that list1 and list2 only contain EObjects.
     * @return whether <code>list1</code> and <code>list2</code> contain equal objects.
     * @since 2.1.0
     */
    public boolean equals(List list1, List list2)
    {
      int size = list1.size();
      if (size != list2.size())
      {
        return false;
      }

      for (int i = 0; i < size; i++)
      {
        EObject eObject1 = (EObject)list1.get(i);
        EObject eObject2 = (EObject)list2.get(i);
        if (!equals(eObject1, eObject2))
        {
          return false;
        }
      }

      return true;
    }

    /**
     * Returns whether the two objects have {@link EqualityHelper equal} 
     * {@link EObject#eIsSet(EStructuralFeature) isSet} states and {@link EObject#eGet(EStructuralFeature) value}s for the feature.
     * @return whether the two objects have equal isSet states and values for the feature.
     * @since 2.2.0
     * @see #equals(EObject, EObject)
     * @see #equals(List, List)
     */
    protected boolean haveEqualFeature(EObject eObject1, EObject eObject2, EStructuralFeature feature)
    {
      // If the set states are the same, and the values of the feature are the structurally equal, they are equal.
      //
      return 
        eObject1.eIsSet(feature) == eObject2.eIsSet(feature)&& 
          (feature instanceof EReference ?
             haveEqualReference(eObject1, eObject2, (EReference)feature) :
             haveEqualAttribute(eObject1, eObject2, (EAttribute)feature));
    }

    /**
     * Returns whether the two objects have {@link EqualityHelper equal} {@link EObject#eGet(EStructuralFeature) value}s for the reference.
     * @return whether the two objects have equal values for the reference.
     * @since 2.1.0
     * @see #equals(EObject, EObject)
     * @see #equals(List, List)
     */
    protected boolean haveEqualReference(EObject eObject1, EObject eObject2, EReference reference)
    {
      Object value1 = eObject1.eGet(reference);
      Object value2 = eObject2.eGet(reference);

      return 
        reference.isMany() ?
          equals((List)value1, (List)value2) :
          equals((EObject)value1, (EObject)value2);
    }


    /**
     * Returns whether the two objects have {@link EqualityHelper equal} {@link EObject#eGet(EStructuralFeature) value}s for the attribute.
     * @return whether the two objects have equal values for the attribute.
     * @since 2.1.0
     * @see #equalFeatureMaps(FeatureMap, FeatureMap)
     */
    protected boolean haveEqualAttribute(EObject eObject1, EObject eObject2, EAttribute attribute)
    {
      Object value1 = eObject1.eGet(attribute);
      Object value2 = eObject2.eGet(attribute);

      // If the first value is null, the second value must be null.
      //
      if (value1 == null)
      {
        return value2 == null;
      }
      
      // Since the first value isn't null, if the second one is, they aren't equal.
      //
      if (value2 == null)
      {
        return false;
      }

      // If this is a feature map...
      //
      if (FeatureMapUtil.isFeatureMap(attribute))
      {
        // The feature maps must be equal.
        //
        FeatureMap featureMap1 = (FeatureMap)value1;
        FeatureMap featureMap2 = (FeatureMap)value2;
        return equalFeatureMaps(featureMap1, featureMap2);
      }
      else
      {
        // The values must be Java equal.
        //
        return value1.equals(value2);
      }
    }

    /**
     * Returns whether the two feature maps are {@link EqualityHelper equal}.
     * @return whether the two feature maps are equal.
     * @since 2.1.0
     */
    protected boolean equalFeatureMaps(FeatureMap featureMap1, FeatureMap featureMap2)
    {
      // If they don't have the same size, the feature maps aren't equal.
      //
      int size = featureMap1.size();
      if (size != featureMap2.size())
      {
        return false;
      }

      // Compare entries in order.
      //
      for (int i = 0; i < size; i++)
      {
        // If entries don't have the same feature, the feature maps aren't equal.
        //
        EStructuralFeature feature = featureMap1.getEStructuralFeature(i);
        if (feature != featureMap2.getEStructuralFeature(i))
        {
          return false;
        }

        Object value1 = featureMap1.getValue(i);
        Object value2 = featureMap2.getValue(i);

        if (!equalFeatureMapValues(value1, value2, feature))
        {
          return false;
        }
      }

      // There is no reason they aren't equals.
      //
      return true;
    }
    
    /**
     * Returns whether the two values of a feature map are {@link EqualityHelper equal}.
     * @return whether the two values of a feature map are equal.
     * @since 2.2.0
     */
    protected boolean equalFeatureMapValues(Object value1, Object value2, EStructuralFeature feature)
    {
      if (feature instanceof EReference)
      {
        // If the referenced EObjects aren't equal, the feature maps aren't equal.
        //
        return equals((EObject)value1, (EObject)value2);
      }
      else
      {
        // If the values aren't Java equal, the feature maps aren't equal.
        //
        return value1 == null ? value2 == null : value1.equals(value2);
      }
    }
    
  } // EqualityHelper

  /**
   * A cross referencer that finds each usage of an EObject or collection of EObjects.
   */
  public static class UsageCrossReferencer extends CrossReferencer
  {
    /**
     * The collection of usage target objects.
     */
    protected Collection eObjectsOfInterest;

    /**
     * Creates an instance for the given object.
     * @param eObject the object to cross reference.
     */
    protected UsageCrossReferencer(EObject eObject)
    {
      super(eObject);
    }

    /**
     * Creates an instance for the given resource.
     * @param resource the resource to cross reference.
     */
    protected UsageCrossReferencer(Resource resource)
    {
      super(resource);
    }

    /**
     * Creates an instance for the given resource set.
     * @param resourceSet the resource set to cross reference.
     */
    protected UsageCrossReferencer(ResourceSet resourceSet)
    {
      super(resourceSet);
    }

    /**
     * Creates an instance for the given collection of objects.
     * @param emfObjects the collection of objects to cross reference.
     */
    protected UsageCrossReferencer(Collection emfObjects)
    {
      super(emfObjects);
    }

    /**
     * Return true if the specified eReference from eObject to crossReferencedEObject should be
     * considiered a cross reference by this cross referencer.
     * @param eObject an object in the cross referencer's content tree.
     * @param eReference a reference from the object.
     * @param crossReferencedEObject the target of the specified reference.
     * @return if the cross referencer should consider the specified reference a cross reference.
     */
    protected boolean crossReference(EObject eObject, EReference eReference, EObject crossReferencedEObject)
    {
      return eObjectsOfInterest.contains(crossReferencedEObject);
    }

    /**
     * Returns the collection of usage references to the specified object.
     * @param eObject the usage target.
     * @return a collection of cross references.
     */
    protected Collection findUsage(EObject eObject)
    {
      eObjectsOfInterest = Collections.singleton(eObject);
      crossReference();
      this.eObjectsOfInterest = null;
      done();
      return getCollection(eObject);
    }

    /**
     * Returns the map of usage references to objects in the specified collection.
     * @param eObjectsOfInterest a collection of usage targets.
     * @return a map of cross references.
     */
    protected Map findAllUsage(Collection eObjectsOfInterest)
    {
      this.eObjectsOfInterest = eObjectsOfInterest;
      crossReference();
      this.eObjectsOfInterest = null;
      done();
      return this;
    }

    /**
     * Returns a collection of usage references from the specified content tree.
     * @param eObjectOfInterest the usage target.
     * @param eObject an object whose content trees should be considered.
     * @return a collection of cross references.
     */
    public static Collection find(EObject eObjectOfInterest, EObject eObject)
    {
      return new UsageCrossReferencer(eObject).findUsage(eObjectOfInterest);
    }

    /**
     * Returns a collection of usage references from the specified content tree.
     * @param eObjectOfInterest the usage target.
     * @param resource a resource whose content tree should be considered.
     * @return a collection of cross references.
     */
    public static Collection find(EObject eObjectOfInterest, Resource resource)
    {
      return new UsageCrossReferencer(resource).findUsage(eObjectOfInterest);
    }

    /**
     * Returns a collection of usage references from the specified content tree.
     * @param eObjectOfInterest the usage target.
     * @param resourceSet a resource set whose content tree should be considered.
     * @return a collection of cross references.
     */
    public static Collection find(EObject eObjectOfInterest, ResourceSet resourceSet)
    {
      return new UsageCrossReferencer(resourceSet).findUsage(eObjectOfInterest);
    }

    /**
     * Returns a collection of usage references from the combined content trees of the specified collection of objects.
     * @param eObjectOfInterest the usage target.
     * @param emfObjectsToSearch a collection of objects whose combined content trees should be considered.
     * @return a collection of cross references.
     */
    public static Collection find(EObject eObjectOfInterest, Collection emfObjectsToSearch)
    {
      return new UsageCrossReferencer(emfObjectsToSearch).findUsage(eObjectOfInterest);
    }

    /**
     * Returns a map of usage references from the specified content tree.
     * @param eObjectsOfInterest a collection of usage targets.
     * @param eObject an object whose content trees should be considered.
     * @return a map of cross references.
     */
    public static Map findAll(Collection eObjectsOfInterest, EObject eObject)
    {
      return new UsageCrossReferencer(eObject).findAllUsage(eObjectsOfInterest);
    }

    /**
     * Returns a map of usage references from the specified content tree.
     * @param eObjectsOfInterest a collection of usage targets.
     * @param resource a resource whose content tree should be considered.
     * @return a map of cross references.
     */
    public static Map findAll(Collection eObjectsOfInterest, Resource resource)
    {
      return new UsageCrossReferencer(resource).findAllUsage(eObjectsOfInterest);
    }

    /**
     * Returns a map of usage references from the specified content tree.
     * @param eObjectsOfInterest a collection of usage targets.
     * @param resourceSet a resource set whose content tree should be considered.
     * @return a map of cross references.
     */
    public static Map findAll(Collection eObjectsOfInterest, ResourceSet resourceSet)
    {
      return new UsageCrossReferencer(resourceSet).findAllUsage(eObjectsOfInterest);
    }

    /**
     * Returns a map of usage references from the combined content trees of the specified collection of objects.
     * @param eObjectsOfInterest a collection of usage targets.
     * @param emfObjectsToSearch a collection of objects whose combined content trees should be considered.
     * @return a map of cross references.
     */
    public static Map findAll(Collection eObjectsOfInterest, Collection emfObjectsToSearch)
    {
      return new UsageCrossReferencer(emfObjectsToSearch).findAllUsage(eObjectsOfInterest);
    }
  }

  /**
   * A cross referencer that finds proxies; the cross referencer will not cause proxies to be resolved.
   */
  public static class ProxyCrossReferencer extends CrossReferencer
  {
    /**
     * Creates an instance for the given object.
     * @param eObject the object to cross reference.
     */
    protected ProxyCrossReferencer(EObject eObject)
    {
      super(eObject);
    }

    /**
     * Creates an instance for the given resource.
     * @param resource the resource to cross reference.
     */
    protected ProxyCrossReferencer(Resource resource)
    {
      super(Collections.singleton(resource));
    }

    /**
     * Creates an instance for the given resource set.
     * @param resourceSet the resource set to cross reference.
     */
    protected ProxyCrossReferencer(ResourceSet resourceSet)
    {
      super(Collections.singleton(resourceSet));
    }

    /**
     * Creates an instance for the given collection of objects.
     * @param emfObjects the collection of objects to cross reference.
     */
    protected ProxyCrossReferencer(Collection emfObjects)
    {
      super(emfObjects);
    }

    /**
     * Return true if potential cross references that are proxies should be resolved.
     * @return if the cross referencer should resolve proxies.
     */
    protected boolean resolve()
    {
      return false;
    }

    /**
     * Return true if the specified eReference from eObject to crossReferencedEObject should be
     * considiered a cross reference by this cross referencer.
     * @param eObject an object in the cross referencer's content tree.
     * @param eReference a reference from the object.
     * @param crossReferencedEObject the target of the specified reference.
     * @return if the cross referencer should consider the specified reference a cross reference.
     */
    protected boolean crossReference(EObject eObject, EReference eReference, EObject crossReferencedEObject)
    {
      return crossReferencedEObject.eIsProxy();
    }

    /**
     * Returns the map of proxy references for this cross referencer.
     * @return a map of cross references.
     */
    protected Map findProxyCrossReferences()
    {
      crossReference();
      done();
      return this;
    }

    /**
     * Returns a map of all proxy references from the specified content tree.
     * @param eObject an object whose content trees should be considered.
     * @return a map of cross references.
     */
    public static Map find(EObject eObject)
    {
      return new ProxyCrossReferencer(eObject).findProxyCrossReferences();
    }

    /**
     * Returns a map of all proxy references from the specified content tree.
     * @param resource a resource whose content tree should be considered.
     * @return a map of cross references.
     */
    public static Map find(Resource resource)
    {
      return new ProxyCrossReferencer(resource).findProxyCrossReferences();
    }

    /**
     * Returns a map of all proxy references from the specified content tree.
     * @param resourceSet a resourceSet whose content tree should be considered.
     * @return a map of cross references.
     */
    public static Map find(ResourceSet resourceSet)
    {
      return new ProxyCrossReferencer(resourceSet).findProxyCrossReferences();
    }

    /**
     * Returns a map of all proxy references from the specified content tree.
     * @param emfObjects a collection of objects whose combined content trees should be considered.
     * @return a map of cross references.
     */
    public static Map find(Collection emfObjects)
    {
      return new ProxyCrossReferencer(emfObjects).findProxyCrossReferences();
    }
  }

  /**
   * A cross referencer that finds proxies that cannot be resolved.
   */
  public static class UnresolvedProxyCrossReferencer extends CrossReferencer
  {
    /**
     * Creates an instance for the given object.
     * @param eObject the object to cross reference.
     */
    protected UnresolvedProxyCrossReferencer(EObject eObject)
    {
      super(eObject);
    }

    /**
     * Creates an instance for the given resource.
     * @param resource the resource to cross reference.
     */
    protected UnresolvedProxyCrossReferencer(Resource resource)
    {
      super(Collections.singleton(resource));
    }

    /**
     * Creates an instance for the given resource set.
     * @param resourceSet the resource set to cross reference.
     */
    protected UnresolvedProxyCrossReferencer(ResourceSet resourceSet)
    {
      super(Collections.singleton(resourceSet));
    }

    /**
     * Creates an instance for the given collection of objects.
     * @param emfObjects the collection of objects to cross reference.
     */
    protected UnresolvedProxyCrossReferencer(Collection emfObjects)
    {
      super(emfObjects);
    }

    /**
     * Return true if the specified eReference from eObject to crossReferencedEObject should be
     * considiered a cross reference by this cross referencer.
     * @param eObject an object in the cross referencer's content tree.
     * @param eReference a reference from the object.
     * @param crossReferencedEObject the target of the specified reference.
     * @return if the cross referencer should consider the specified reference a cross reference.
     */
    protected boolean crossReference(EObject eObject, EReference eReference, EObject crossReferencedEObject)
    {
      return crossReferencedEObject.eIsProxy();
    }

    /**
     * Returns the map of unresolvable proxies for this cross referencer.
     * @return a map of cross references.
     */
    protected Map findUnresolvedProxyCrossReferences()
    {
      crossReference();
      done();
      return this;
    }

    /**
     * Returns a map of all unresolvable proxies from the specified content tree.
     * @param eObject an object whose content trees should be considered.
     * @return a map of cross references.
     */
    public static Map find(EObject eObject)
    {
      return new UnresolvedProxyCrossReferencer(eObject).findUnresolvedProxyCrossReferences();
    }

    /**
     * Returns a map of all unresolvable proxies from the specified content tree.
     * @param resource a resource whose content tree should be considered.
     * @return a map of cross references.
     */
    public static Map find(Resource resource)
    {
      return new UnresolvedProxyCrossReferencer(resource).findUnresolvedProxyCrossReferences();
    }

    /**
     * Returns a map of all unresolvable proxies from the specified content tree.
     * @param resourceSet a resourceSet whose content tree should be considered.
     * @return a map of cross references.
     */
    public static Map find(ResourceSet resourceSet)
    {
      return new UnresolvedProxyCrossReferencer(resourceSet).findUnresolvedProxyCrossReferences();
    }

    /**
     * Returns a map of all unresolvable proxies from the specified content tree.
     * @param emfObjects a collection of objects whose combined content trees should be considered.
     * @return a map of cross references.
     */
    public static Map find(Collection emfObjects)
    {
      return new UnresolvedProxyCrossReferencer(emfObjects).findUnresolvedProxyCrossReferences();
    }
  }

  /**
   * Returns a unique string identification of the eObject;
   * it is structured as follows:
   *<pre>
   *  &lt;java-class-name>[/&lt;dynamic-eclass-name>]@&lt;java-hex-hash-code>{&lt;uri-of-eobject>}
   *</pre>
   * @param eObject the object for which to get an identification.
   * @return the identification string for the object.
   */
  public static String getIdentification(EObject eObject)
  {
    StringBuffer result = new StringBuffer(eObject.getClass().getName());
    EClass eClass = eObject.eClass();
    if (eClass.getInstanceClassName() == null)
    {
      result.append('/');
      result.append(eClass.getEPackage().getNsURI());
      result.append('#');
      result.append(eClass.getName());
    }
    result.append('@');
    result.append(Integer.toHexString(eObject.hashCode()));

    result.append('{');
    result.append(getURI(eObject));
    result.append('}');

    return result.toString();
  }

  /**
   * Returns a URI for the eObject, 
   * i.e., either 
   * the eProxyURI,
   * the URI of the eResource with the fragment produced by the eResource,
   * or the URI consisting of just the fragment that would be produced by a default Resource 
   * with the eObject as its only contents.
   * @param eObject the object for which to get the URI.
   * @return the URI for the object.
   */
  public static URI getURI(EObject eObject)
  {
    // If it's a proxy, use that.
    //
    URI proxyURI = ((InternalEObject)eObject).eProxyURI();
    if (proxyURI != null)
    {
      return proxyURI;
    }
    else
    {
      // If it is in a resource, form the URI relative to that resource.
      //
      Resource resource = eObject.eResource();
      if (resource != null)
      {
        return resource.getURI().appendFragment(resource.getURIFragment(eObject));
      }
      else
      {
        String id = EcoreUtil.getID(eObject);
        if (id != null)
        {
          return URI.createURI("#" + id);
        }
        else
        {
          InternalEObject internalEObject = (InternalEObject)eObject;
          List uriFragmentPath = new ArrayList();
          for (InternalEObject container = internalEObject.eInternalContainer(); container != null; container = internalEObject.eInternalContainer())
          {
            uriFragmentPath.add(container.eURIFragmentSegment(internalEObject.eContainingFeature(), internalEObject));
            internalEObject = container;
          }
      
          StringBuffer result = new StringBuffer("#//");
      
          for (int i = uriFragmentPath.size() - 1; i >= 0; --i)
          {
            result.append('/');
            result.append(uriFragmentPath.get(i));
          }
          return URI.createURI(result.toString());
        }
      }
    }
  }

  /**
   * Searches for the first occurence of the given argument in list starting from
   * a specified index.  The equality is tested using the operator <tt>==<tt> and
   * the <tt>equals</tt> method. 
   * @param list
   * @param o an object (can be null)
   * @param fromIndex 
   * @return the index of the first occurrence of the argument in this
   *         list (where index>=fromIndex); returns <tt>-1</tt> if the 
   * 				 object is not found.
   * @deprecated replaced by {@link ECollections#indexOf(List, Object, int)} in 2.1.0
   */
  public static int indexOf(List list, Object o, int fromIndex)
  {
    return ECollections.indexOf(list, o, fromIndex);
  }

  /** 
   * Sets the <code>eList</code>'s contents and order to be exactly that of the <code>prototype</code> collection.
   * This implementation mimimizes the number of notifications the operation will produce.
   * Objects already in the list will be moved, missing objects will be added, and extra objects will be removed.
   * If <code>eList</code>'s contents and order are already exactly that of the <code>prototype</code> collection,
   * no change will be made.
   * @param eList the list to set.
   * @param prototypeCollection the collection representing the desired content and order.
   * @deprecated replaced by {@link ECollections#setEList(EList, List)} in 2.1.0
   */
  public static void setEList(EList eList, Collection prototypeCollection)
  {
    ECollections.setEList(eList, new ArrayList(prototypeCollection));
  }

  /** 
   * Sets the <code>eList</code>'s contents and order to be exactly that of the <code>prototype</code> list.
   * This implementation mimimizes the number of notifications the operation will produce.
   * Objects already in the list will be moved, missing objects will be added, and extra objects will be removed.
   * If <code>eList</code>'s contents and order are already exactly that of the <code>prototype</code> list,
   * no change will be made.
   * @param eList the list to set.
   * @param prototypeList the list representing the desired content and order.
   * @deprecated replaced by {@link ECollections#setEList(EList, List)} in 2.1.0
   */
  public static void setEList(EList eList, List prototypeList)
  {
    ECollections.setEList(eList, prototypeList);
  }

  /**
   * Removes the value from the setting.
   * @param setting the setting holding the value.
   * @param value the value to remove.
   */
  public static void remove(EStructuralFeature.Setting setting, Object value)
  {
    if (setting.getEStructuralFeature().isMany())
    {
      ((List)setting.get(false)).remove(value);
    }
    else
    {
      setting.unset();
    }
  }

  /**
   * Replaces the old value in the setting with the new value.
   * @param setting the setting holding the values.
   * @param oldValue the value to replace.
   * @param newValue the replacement value.
   */
  public static void replace(EStructuralFeature.Setting setting, Object oldValue, Object newValue)
  {
    if (setting.getEStructuralFeature().isMany())
    {
      List list = (List)setting.get(false);
      list.set(list.indexOf(oldValue), newValue);
    }
    else
    {
      setting.set(newValue);
    }
  }

  /**
   * Removes the value from the feature of the object.
   * @param eObject the object holding the value.
   * @param eStructuralFeature the feature of the object holding the value.
   * @param value the value to remove.
   */
  public static void remove(EObject eObject, EStructuralFeature eStructuralFeature, Object value)
  {
    if (eStructuralFeature.isMany())
    {
      ((List)eObject.eGet(eStructuralFeature)).remove(value);
    }
    else
    {
      eObject.eUnset(eStructuralFeature);
    }
  }

  /**
   * Replaces the old value in the object's feature with the new value.
   * @param eObject the object holding the values.
   * @param eStructuralFeature the feature of the object holding the values.
   * @param oldValue the value to replace.
   * @param newValue the replacement value.
   */
  public static void replace(EObject eObject, EStructuralFeature eStructuralFeature, Object oldValue, Object newValue)
  {
    if (eStructuralFeature.isMany())
    {
      List list = (List)eObject.eGet(eStructuralFeature);
      list.set(list.indexOf(oldValue), newValue);
    }
    else
    {
      eObject.eSet(eStructuralFeature, newValue);
    }
  }

  /**
   * Removes the object from its {@link EObject#eResource containing} resource 
   * or its {@link EObject#eContainer containing} object.
   * @param eObject the object to remove.
   */
  public static void remove(EObject eObject)
  {
    EObject container = ((InternalEObject)eObject).eInternalContainer();
    if (container != null)
    {
      EReference feature = eObject.eContainmentFeature();
      if (feature.isMany())
      {
        ((EList)container.eGet(feature)).remove(eObject);
      }
      else
      {
        container.eUnset(feature);
      }
    }
    else
    {
      Resource resource = eObject.eResource();
      if (resource != null)
      {
        resource.getContents().remove(eObject);
      }
    }
  }

  /**
   * Replace the object in its {@link EObject#eResource containing} resource 
   * or its {@link EObject#eContainer containing} object,
   * with the replacement object.
   * @param eObject the object to replace.
   * @param replacementEObject the replacement object.
   */
  public static void replace(EObject eObject, EObject replacementEObject)
  {
    EObject container = ((InternalEObject)eObject).eInternalContainer();
    if (container != null)
    {
      EReference feature = eObject.eContainmentFeature();
      if (feature.isMany())
      {
        List list = (List)container.eGet(feature);
        list.set(list.indexOf(eObject), replacementEObject);
      }
      else
      {
        container.eSet(feature, replacementEObject);
      }
    }
    else
    {
      Resource resource = eObject.eResource();
      if (resource != null)
      {
        List list = resource.getContents();
        list.set(list.indexOf(eObject), replacementEObject);
      }
    }
  }

  /**
   * Creates an instance of the class.
   * @param eClass the class to instantiate.
   * @return an instance of the class.
   */
  public static EObject create(EClass eClass)
  {
    return eClass.getEPackage().getEFactoryInstance().create(eClass);
  }

  /**
   * Creates an instance of the datatype.
   * @param eDataType the datatype to instantiate.
   * @param literal the string value of the datatype.
   * @return an instance of the datatype.
   * @see #convertToString(EDataType, Object)
   */
  public static Object createFromString(EDataType eDataType, String literal)
  {
    return eDataType.getEPackage().getEFactoryInstance().createFromString(eDataType, literal);
  }

  /**
   * Converts an instance of the datatype to a string literal representation.
   * @param eDataType the datatype to instantiate.
   * @param value a value of the datatype.
   * @return the string literal representation of the value.
   * @see #createFromString(EDataType, String)
   */
  public static String convertToString(EDataType eDataType, Object value)
  {
    return eDataType.getEPackage().getEFactoryInstance().convertToString(eDataType, value);
  }

  /**
   * Returns the value of the object's ID attribute as a String.
   * If the object has no ID attribute or the ID attribute is not set, it returns <code>null</code>.
   * @param eObject the object in question.
   * @return the value of the object's ID attribute as a String.
   * @see org.eclipse.emf.ecore.EAttribute#isID
   * @see org.eclipse.emf.ecore.EClass#getEIDAttribute
   * @see #setID(EObject, String)
   */
  public static String getID(EObject eObject)
  {
    EClass eClass = eObject.eClass();
    EAttribute eIDAttribute = eClass.getEIDAttribute();
    return eIDAttribute == null || !eObject.eIsSet(eIDAttribute) ? null : convertToString(
      eIDAttribute.getEAttributeType(),
      eObject.eGet(eIDAttribute));
  }

  /**
   * Sets the value of the object's ID attribute according to the value represented by the String.
   * A <code>null</code> ID will unset the attribute rather than setting it to <code>null</code>.
   * @param eObject the object in question.
   * @param id the String value of the new ID.
   * @return the value of the object's ID attribute as a String.
   * @throws IllegalArgumentException if the object has no ID attribute.
   * @see #getID(EObject)
   * @see org.eclipse.emf.ecore.EAttribute#isID
   * @see org.eclipse.emf.ecore.EClass#getEIDAttribute
   */
  public static void setID(EObject eObject, String id)
  {
    EClass eClass = eObject.eClass();
    EAttribute eIDAttribute = eClass.getEIDAttribute();
    if (eIDAttribute == null)
    {
      throw new IllegalArgumentException("The object doesn't have an ID feature.");
    }
    else if (id == null)
    {
      eObject.eUnset(eIDAttribute);
    }
    else
    {
      eObject.eSet(eIDAttribute, createFromString(eIDAttribute.getEAttributeType(), id));
    }
  }

  /**
   * Returns the wrapper class for the primitive class, or the original class, if it's not a primitive class.
   * @param javaClass 
   */
  public static Class wrapperClassFor(Class javaClass)
  {
    if (javaClass == null)
    {
      return null;
    }
    else if (javaClass.isPrimitive())
    {
      if (javaClass == Boolean.TYPE)
      {
        return Boolean.class;
      }
      else if (javaClass == Integer.TYPE)
      {
        return Integer.class;
      }
      else if (javaClass == Float.TYPE)
      {
        return Float.class;
      }
      else if (javaClass == Double.TYPE)
      {
        return Double.class;
      }
      else if (javaClass == Long.TYPE)
      {
        return Long.class;
      }
      else if (javaClass == Short.TYPE)
      {
        return Short.class;
      }
      else if (javaClass == Byte.TYPE)
      {
        return Byte.class;
      }
      else
      // if (javaClass == Character.TYPE)
      {
        return Character.class;
      }
    }
    else
    {
      return javaClass;
    }
  }

  protected static final String GEN_MODEL_PACKAGE_NS_URI = "http://www.eclipse.org/emf/2002/GenModel";

  public static String getDocumentation(EModelElement eModelElement)
  {
    EAnnotation eAnnotation = eModelElement.getEAnnotation(GEN_MODEL_PACKAGE_NS_URI);
    return eAnnotation == null ? null : (String)eAnnotation.getDetails().get("documentation");
  }

  public static void setDocumentation(EModelElement eModelElement, String documentation)
  {
    EAnnotation eAnnotation = eModelElement.getEAnnotation(GEN_MODEL_PACKAGE_NS_URI);
    if (documentation == null)
    {
      if (eAnnotation != null)
      {
        eAnnotation.getDetails().remove("documentation");
      }
    }
    else
    {
      if (eAnnotation == null)
      {
        eAnnotation = EcoreFactory.eINSTANCE.createEAnnotation();
        eAnnotation.setSource(GEN_MODEL_PACKAGE_NS_URI);
        eModelElement.getEAnnotations().add(eAnnotation);
      }
      eAnnotation.getDetails().put("documentation", documentation);
    }
  }

  public static List getConstraints(EModelElement eModelElement)
  {
    EAnnotation eAnnotation = eModelElement.getEAnnotation(EcorePackage.eNS_URI);
    if (eAnnotation != null)
    {
      String constraints = (String)eAnnotation.getDetails().get("constraints");
      if (constraints != null)
      {
        List result = new ArrayList();
        for (StringTokenizer stringTokenizer = new StringTokenizer(constraints); stringTokenizer.hasMoreTokens();)
        {
          String constraint = stringTokenizer.nextToken();
          result.add(constraint);
        }
        return result;
      }
    }
    return Collections.EMPTY_LIST;
  }

  public static void setConstraints(EModelElement eModelElement, List constraints)
  {
    EAnnotation eAnnotation = eModelElement.getEAnnotation(EcorePackage.eNS_URI);
    if (constraints == null || constraints.isEmpty())
    {
      if (eAnnotation != null)
      {
        eAnnotation.getDetails().remove("constraints");
      }
    }
    else
    {
      if (eAnnotation == null)
      {
        eAnnotation = EcoreFactory.eINSTANCE.createEAnnotation();
        eAnnotation.setSource(EcorePackage.eNS_URI);
        eModelElement.getEAnnotations().add(eAnnotation);
      }
      StringBuffer value = new StringBuffer();
      for (Iterator i = constraints.iterator(); i.hasNext();)
      {
        value.append(i.next());
        if (i.hasNext())
        {
          value.append(' ');
        }
      }
      eAnnotation.getDetails().put("constraints", value.toString());
    }
  }

  public static String getAnnotation(EModelElement eModelElement, String sourceURI, String key)
  {
    EAnnotation eAnnotation = eModelElement.getEAnnotation(sourceURI);
    return eAnnotation == null ? null : (String)eAnnotation.getDetails().get(key);
  }

  public static void setAnnotation(EModelElement eModelElement, String sourceURI, String key, String value)
  {
    EAnnotation eAnnotation = eModelElement.getEAnnotation(sourceURI);
    if (eAnnotation == null)
    {
      eAnnotation = EcoreFactory.eINSTANCE.createEAnnotation();
      eAnnotation.setSource(sourceURI);
      eModelElement.getEAnnotations().add(eAnnotation);
    }
    eAnnotation.getDetails().put(key, value);
  }

  /**
   * Identifier for the get accessor.
   * @see #isSuppressedVisibility
   * @see #setSuppressedVisibility
   * @since 2.1
   */
  public static final int GET = 0;

  /**
   * Identifier for the set accessor.
   * @see #isSuppressedVisibility
   * @see #setSuppressedVisibility
   * @since 2.1
   */
  public static final int SET = 1;

  /**
   * Identifier for the isSet accessor.
   * @see #isSuppressedVisibility
   * @see #setSuppressedVisibility
   * @since 2.1
   */
  public static final int IS_SET = 2;

  /**
   * Identifier for the unset accessor.
   * @see #isSuppressedVisibility
   * @see #setSuppressedVisibility
   * @since 2.1
   */
  public static final int UNSET = 3;

  // Keys that will be used to record visibility for the accessors.
  //
  static final String[] ACCESSOR_KEYS =
  {
    "suppressedGetVisibility",
    "suppressedSetVisibility",
    "suppressedIsSetVisibility",
    "suppressedUnsetVisibility"
  };

  // Value used to suppress visibility.
  //
  static final String TRUE = "true";

  /**
   * Tests whether the given structural feature has been annotated to prevent generation of accessor methods in its
   * interface.
   * @param eStructuralFeature the structural feature
   * @param accessor the type of accessor method, one of {@link #GET}, {@link #SET}, {@link #IS_SET}, or {@link #UNSET}
   * @return whether the specified accessor's visibility is suppressed
   * @since 2.1
   */
  public static boolean isSuppressedVisibility(EStructuralFeature eStructuralFeature, int accessor)
  {
    if (accessor < GET || accessor > UNSET) throw new IllegalArgumentException("Invalid accessor identifier: " + accessor);

    EAnnotation eAnnotation = eStructuralFeature.getEAnnotation(GEN_MODEL_PACKAGE_NS_URI);
    return eAnnotation == null ? false : TRUE.equalsIgnoreCase((String)eAnnotation.getDetails().get(ACCESSOR_KEYS[accessor]));
  }

  /**
   * Sets or removes annotations on the given structural feature to prevent generation of accessor methods in its interface.
   * @param eStructuralFeature the structural feature
   * @param accessor the type of accessor method, one of {@link #GET}, {@link #SET}, {@link #IS_SET}, or {@link #UNSET}
   * @param suppress whether the specified accessor's visibility should be suppressed
   * @since 2.1
   */
  public static void setSuppressedVisibility(EStructuralFeature eStructuralFeature, int accessor, boolean suppress)
  {
    if (accessor < GET || accessor > UNSET) throw new IllegalArgumentException("Invalid accessor identifier: " + accessor);

    EAnnotation eAnnotation = eStructuralFeature.getEAnnotation(GEN_MODEL_PACKAGE_NS_URI);
    if (!suppress)
    {
      if (eAnnotation != null)
      {
        eAnnotation.getDetails().removeKey(ACCESSOR_KEYS[accessor]);
      }
    }
    else
    {
      if (eAnnotation == null)
      {
        eAnnotation = EcoreFactory.eINSTANCE.createEAnnotation();
        eAnnotation.setSource(GEN_MODEL_PACKAGE_NS_URI);
        eStructuralFeature.getEAnnotations().add(eAnnotation);
      }
      eAnnotation.getDetails().put(ACCESSOR_KEYS[accessor], TRUE);
    }
  }

  /**
   * Generates a universally unique identifier, 
   * i.e., a <a href="ftp://ietf.org/internet-drafts/draft-mealling-uuid-urn-02.txt">UUID</a>.
   * It encodes the 128 bit UUID in <a href="http://www.ietf.org/rfc/rfc2045.txt">base 64</a>,
   * but rather than padding the encoding with two "=" characters, 
   * it prefixes the encoding with a single "_" character,
   * to ensure that the result is a valid <a href="http://www.w3.org/TR/xmlschema-2/#ID">ID</a>,
   * i.e., an <a href="http://www.w3.org/TR/1999/REC-xml-names-19990114/#NT-NCName">NCName</a>
   * @return a universally unique identifier.
   */
  public static String generateUUID()
  {
    return UUID.generate();
  }

  private static final class UUID
  {
    public synchronized static String generate()
    {
      updateCurrentTime();

      // Do a base 64 conversion by turning every 3 bytes into 4 base 64 characters
      //
      for (int i = 0; i < 5; ++i)
      {
        buffer[4 * i + 1] = BASE64_DIGITS[(uuid[i * 3] >> 2) & 0x3F];
        buffer[4 * i + 2] = BASE64_DIGITS[((uuid[i * 3] << 4) & 0x30) | ((uuid[i * 3 + 1] >> 4) & 0xF)];
        buffer[4 * i + 3] = BASE64_DIGITS[((uuid[i * 3 + 1] << 2) & 0x3C) | ((uuid[i * 3 + 2] >> 6) & 0x3)];
        buffer[4 * i + 4] = BASE64_DIGITS[uuid[i * 3 + 2] & 0x3F];
      }

      // Handle the last byte at the end.
      //
      buffer[21] = BASE64_DIGITS[(uuid[15] >> 2) & 0x3F];
      buffer[22] = BASE64_DIGITS[(uuid[15] << 4) & 0x30];

      return new String(buffer);
    }

    private UUID()
    {
    }

    private static final char[] BASE64_DIGITS = {
      'A',
      'B',
      'C',
      'D',
      'E',
      'F',
      'G',
      'H',
      'I',
      'J',
      'K',
      'L',
      'M',
      'N',
      'O',
      'P',
      'Q',
      'R',
      'S',
      'T',
      'U',
      'V',
      'W',
      'X',
      'Y',
      'Z',
      'a',
      'b',
      'c',
      'd',
      'e',
      'f',
      'g',
      'h',
      'i',
      'j',
      'k',
      'l',
      'm',
      'n',
      'o',
      'p',
      'q',
      'r',
      's',
      't',
      'u',
      'v',
      'w',
      'x',
      'y',
      'z',
      '0',
      '1',
      '2',
      '3',
      '4',
      '5',
      '6',
      '7',
      '8',
      '9',
      '-',
      '_' };

    /**
     * An adjustment to convert the Java epoch of Jan 1, 1970 00:00:00 to
     * the epoch required by the IETF specification, Oct 15, 1582 00:00:00.
     */
    private static final long EPOCH_ADJUSTMENT = new GregorianCalendar(1970, 0, 1, 0, 0, 0).getTime().getTime()
      - new GregorianCalendar(1582, 9, 15, 0, 0, 0).getTime().getTime();

    private static long lastTime = System.currentTimeMillis() + EPOCH_ADJUSTMENT;

    private static short clockSequence;

    private static short timeAdjustment;
    
    private static int sleepTime = 1;

    /**
     * A cached array of bytes representing the UUID. The second 8 bytes
     * will be kept the same unless the clock sequence has changed.
     */
    private static final byte[] uuid = new byte [16];

    private static final char[] buffer = new char [23];

    static
    {
      SecureRandom random = new SecureRandom();

      clockSequence = (short)random.nextInt(16384);
      updateClockSequence();

      // Generate a 48 bit node identifier; 
      // This is an alternative to the IEEE 802 host address, which is not available in Java.
      //
      byte[] nodeAddress = new byte [6];

      random.nextBytes(nodeAddress);

      // Set the most significant bit of the first octet to 1 so as to distinguish it from IEEE node addresses
      //
      nodeAddress[0] |= (byte)0x80;

      // The node identifier is already in network byte order, 
      // so there is no need to do any byte order reversing.
      //
      for (int i = 0; i < 6; ++i)
      {
        uuid[i + 10] = nodeAddress[i];
      }

      buffer[0] = '_';
    }

    /**
     * Updates the clock sequence portion of the UUID. The clock sequence
     * portion may seem odd, but in the specification, the high order byte
     * comes before the low order byte. The variant is multiplexed into the
     * high order octet of clockseq_hi.
     */
    private static void updateClockSequence()
    {
      // clockseq_hi
      uuid[8] = (byte)(((clockSequence >> 8) & 0x3F) | 0x80);
      // clockseq_low
      uuid[9] = (byte)(clockSequence & 0xFF);
    }

    /**
     * Updates the UUID with the current time, compensating for the fact
     * that the clock resolution may be less than 100 ns. The byte array
     * will have its first eight bytes populated with the time in the
     * correct sequence of bytes, as per the specification.
     */
    private static void updateCurrentTime()
    {
      // Get the current time in milliseconds since the epoch 
      // and adjust it to match the epoch required by the specification.
      //
      long currentTime = System.currentTimeMillis() + EPOCH_ADJUSTMENT;

      if (lastTime > currentTime)
      {
        // The system clock has been rewound so the clock sequence must be incremented 
        // to ensure that a duplicate UUID is not generated.
        //
        ++clockSequence;

        if (16384 == clockSequence)
        {
          clockSequence = 0;
        }

        updateClockSequence();
      }
      else if (lastTime == currentTime)
      {
        // The system time hasn't changed so add some increment of 100s of nanoseconds to guarantee uniqueness.
        //
        ++timeAdjustment;

        if (timeAdjustment > 9999)
        {
          // Wait so that the clock can catch up and the time adjustment won't overflow.
          try
          {
            Thread.sleep(sleepTime);
          }
          catch (InterruptedException exception)
          {
          }

          timeAdjustment = 0;
          currentTime = System.currentTimeMillis() + EPOCH_ADJUSTMENT;

          while (lastTime == currentTime)
          {
            try
            {
              ++sleepTime;
              Thread.sleep(1);
            }
            catch (InterruptedException exception)
            {
            }
            currentTime = System.currentTimeMillis() + EPOCH_ADJUSTMENT;
          }
        }
      }
      else
      {
        timeAdjustment = 0;
      }

      lastTime = currentTime;

      // Since the granularity of time in Java is only milliseconds, 
      // add an adjustment so that the time is represented in 100s of nanoseconds.
      // The version number (1) is multiplexed into the most significant hex digit.
      //
      currentTime *= 10000;
      currentTime += timeAdjustment;
      currentTime |= 0x1000000000000000L;

      // Place the time into the byte array in network byte order.
      //
      for (int i = 0; i < 4; ++i)
      {
        // time_low
        //
        uuid[i] = (byte)((currentTime >> 8 * (3 - i)) & 0xFFL);
      }

      for (int i = 0; i < 2; ++i)
      {
        // time_mid
        //
        uuid[i + 4] = (byte)((currentTime >> 8 * (1 - i) + 32) & 0xFFL);
      }

      for (int i = 0; i < 2; ++i)
      {
        // time_hi
        //
        uuid[i + 6] = (byte)((currentTime >> 8 * (1 - i) + 48) & 0xFFL);
      }
    }
  }

  /**
   * Marks the package to indicate that it and everything it contains or that its contents depend on can no longer be changed.
   * This helps to improve the performance of the model but has no other effect.
   */
  public static void freeze(EPackage ePackage)
  {
    try
    {
      ((EPackageImpl)ePackage).freeze();
    }
    catch (ClassCastException exception)
    {
    }
  }

  /*
   static 
   {
   System.err.println("UUID");
   for (int loop = 0; loop < 5; ++loop)
   {
   long before = System.currentTimeMillis(); 
   long count = 500000;
   for (int i = 0; i < count; ++i)
   {
   generateUUID();
   }
   long after = System.currentTimeMillis();
   System.err.println("Elapsed " + (after - before));
   System.err.println("Time " + 1000 * ((float)(after - before))/((float)count));
   }

   final EPackage ecorePackage = EPackage.Registry.INSTANCE.getEPackage("ecore.xmi");
   final Resource ecorePackageResource = ecorePackage.eResource();
   final EPackage genModelPackage = EPackage.Registry.INSTANCE.getEPackage("genmodel.xmi");
   final Resource genModelPackageResource = genModelPackage.eResource();

   // Proxy finder.
   //
   {
   // Create a proxy and stuff it into the eSuperTypes.
   // This is a really very nasty thing to do.
   //
   EClass eClass = org.eclipse.emf.ecore.EcoreFactory.eINSTANCE.createEClass();
   ((InternalEObject)eClass).eSetProxyURI(URI.createURI("Yes!"));
   ((EClass)genModelPackage.getEClassifier("GenClass")).getESuperTypes().add(eClass);

   System.err.println("=========================================");
   System.err.println("All proxy references in the GenModel EPackage");
   Map proxyCrossReferences = ProxyCrossReferencer.find(genModelPackage);
   CrossReferencer.print(System.err, proxyCrossReferences);

   // Clean up the prox.
   //
   ((EClass)genModelPackage.getEClassifier("GenClass")).getESuperTypes().remove(eClass);
   }

   // External cross reference finder.
   //
   {
   System.err.println("=========================================");
   System.err.println("All cross document references in the GenModel EPackage");
   Map externalCrossReferences = ExternalCrossReferencer.find(genModelPackage);
   CrossReferencer.print(System.err, externalCrossReferences);
   }

   {
   // Find uses for object of interest.
   //
   EObject objectOfInterest = ecorePackage.getEClassifier("EDataType");
   System.err.println("=========================================");
   System.err.println("Uses of: " + getIdentification(objectOfInterest));

   // Put the models in a resource set temporarily.
   //
   ResourceSet resourceSet = new org.eclipse.emf.ecore.resource.impl.ResourceSetImpl();
   resourceSet.getResources().add(ecorePackageResource);
   resourceSet.getResources().add(genModelPackageResource);

   // Search the whole resource set.
   //
   Collection result = new UsageCrossReferencer(resourceSet).findUsage(objectOfInterest);
   for (Iterator i = result.iterator(); i.hasNext(); )
   {
   // Show the settings that reference the objectOfInterest.
   //
   EStructuralFeature.Setting setting = (EStructuralFeature.Setting)i.next();
   EObject eObject = setting.getEObject();
   EStructuralFeature eStructuralFeature = (EStructuralFeature)setting.getEStructuralFeature();
   System.err.println
   (">   " + eStructuralFeature.getEContainingClass().getName() + "." + eStructuralFeature.getName() + 
   " <- " + getIdentification(eObject));
   }

   // Cleanup.
   //
   resourceSet.getResources().clear();
   }

   List list = org.eclipse.emf.ecore.EcorePackage.eINSTANCE.getEReference().getEAllStructuralFeatures();

   {
   Object object = org.eclipse.emf.ecore.EcorePackage.eINSTANCE.getEReference_EReferenceType();
   
   for (int i = 0; i < 100; ++i)
   {
   list.indexOf(object);
   }
   long before = System.currentTimeMillis(); 
   for (int i = 0; i < 500000; ++i)
   {
   list.indexOf(object);
   }
   long after = System.currentTimeMillis();
   System.err.println("Elapsed " + (after - before));
   }

   {
   Object object = org.eclipse.emf.ecore.EcorePackage.eINSTANCE.getENamedElement_Name();
   
   for (int i = 0; i < 100; ++i)
   {
   list.indexOf(object);
   }
   long before = System.currentTimeMillis(); 
   for (int i = 0; i < 500000; ++i)
   {
   list.indexOf(object);
   }
   long after = System.currentTimeMillis();
   System.err.println("Elapsed " + (after - before));
   }
   }
   */
}

