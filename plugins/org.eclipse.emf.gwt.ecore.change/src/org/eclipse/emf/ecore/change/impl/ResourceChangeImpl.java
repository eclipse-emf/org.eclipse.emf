/**
 * <copyright>
 *
 * Copyright (c) 2006-2007 IBM Corporation and others.
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
 * $Id: ResourceChangeImpl.java,v 1.1 2010/04/28 14:46:51 emerks Exp $
 */
package org.eclipse.emf.ecore.change.impl;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.BasicEList;
import org.eclipse.emf.common.util.ECollections;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.change.ChangePackage;
import org.eclipse.emf.ecore.change.ListChange;
import org.eclipse.emf.ecore.change.ResourceChange;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Resource Change</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.emf.ecore.change.impl.ResourceChangeImpl#getResourceURI <em>Resource URI</em>}</li>
 *   <li>{@link org.eclipse.emf.ecore.change.impl.ResourceChangeImpl#getResource <em>Resource</em>}</li>
 *   <li>{@link org.eclipse.emf.ecore.change.impl.ResourceChangeImpl#getValue <em>Value</em>}</li>
 *   <li>{@link org.eclipse.emf.ecore.change.impl.ResourceChangeImpl#getListChanges <em>List Changes</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class ResourceChangeImpl extends EObjectImpl implements ResourceChange
{
  /**
   * The default value of the '{@link #getResourceURI() <em>Resource URI</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getResourceURI()
   * @generated
   * @ordered
   */
  protected static final String RESOURCE_URI_EDEFAULT = null;

  /**
   * The cached value of the '{@link #getResourceURI() <em>Resource URI</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getResourceURI()
   * @generated
   * @ordered
   */
  protected String resourceURI = RESOURCE_URI_EDEFAULT;

  /**
   * The default value of the '{@link #getResource() <em>Resource</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getResource()
   * @generated
   * @ordered
   */
  protected static final Resource RESOURCE_EDEFAULT = null;

  /**
   * The cached value of the '{@link #getResource() <em>Resource</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getResource()
   * @generated
   * @ordered
   */
  protected Resource resource = RESOURCE_EDEFAULT;

  /**
   * The cached value of the '{@link #getListChanges() <em>List Changes</em>}' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getListChanges()
   * @generated
   * @ordered
   */
  protected EList<ListChange> listChanges;

  protected EList<Object> valueField = null;
  protected EList<Object> newValue = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected ResourceChangeImpl()
  {
    super();
  }

  protected ResourceChangeImpl(Resource resource, EList<Object> value)
  {
    this();
    setResource(resource);
    setValue(value);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  protected EClass eStaticClass()
  {
    return ChangePackage.Literals.RESOURCE_CHANGE;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public String getResourceURI()
  {
    return resourceURI;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setResourceURI(String newResourceURI)
  {
    String oldResourceURI = resourceURI;
    resourceURI = newResourceURI;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, ChangePackage.RESOURCE_CHANGE__RESOURCE_URI, oldResourceURI, resourceURI));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated NOT
   */
  public Resource getResource()
  {
    if (resource == null)
    {
      if (getResourceURI() != null)
      {
        Resource changeResource = eResource();
        if (changeResource != null)
        {
          ResourceSet resourceSet = changeResource.getResourceSet();
          if (resourceSet != null)
          {
            resource = resourceSet.getResource(URI.createURI(getResourceURI()), false);
          }
        }
      }
    }
    return resource;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setResourceGen(Resource newResource)
  {
    Resource oldResource = resource;
    resource = newResource;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, ChangePackage.RESOURCE_CHANGE__RESOURCE, oldResource, resource));
  }

  public void setResource(Resource newResource)
  {
    setResourceGen(newResource);
    setResourceURI(newResource.getURI() == null ? null : newResource.getURI().toString());
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated NOT
   */
  public EList<Object> getValue()
  {
    if (valueField == null)
    {
      Resource resource = getResource();
      if (resource != null)
      {
        EList<Object> changedList = new BasicEList<Object>(resource.getContents());
        apply(changedList);
        valueField = changedList; // cache result
        return changedList;
      }
      return ECollections.emptyEList();
    }
    return valueField;
  }

  protected void setValue(EList<Object> value)
  {
    valueField = value;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EList<ListChange> getListChanges()
  {
    if (listChanges == null)
    {
      listChanges = new EObjectContainmentEList<ListChange>(ListChange.class, this, ChangePackage.RESOURCE_CHANGE__LIST_CHANGES);
    }
    return listChanges;
  }

  public void preApply(boolean reverse)
  {
    if (resource != null)
    {
      if (reverse)
      {
        newValue = new BasicEList<Object>(resource.getContents());
      }
    }
  }
  
  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated NOT
   */
  public void apply()
  {    
    apply(false);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated NOT
   */
  public void applyAndReverse()
  {
    apply(true);
  }

  protected void apply(boolean reverse)
  {
    Resource resource = getResource();
    if (resource != null && listChanges != null)
    {
      EList<Object> value = getValue();
      
      @SuppressWarnings({"unchecked", "rawtypes"}) EList<Object> result = (EList)resource.getContents();
      ECollections.setEList(result, value);
      
      if (reverse)
      {
        ECollections.reverse(getListChanges());
        setValue(newValue);
      }
    }
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs)
  {
    switch (featureID)
    {
      case ChangePackage.RESOURCE_CHANGE__LIST_CHANGES:
        return ((InternalEList<?>)getListChanges()).basicRemove(otherEnd, msgs);
    }
    return super.eInverseRemove(otherEnd, featureID, msgs);
  }

  protected void apply(EList<Object> toList)
  {
    for (ListChange listChange : getListChanges())
    {
      listChange.apply(toList);
    }
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public Object eGet(int featureID, boolean resolve, boolean coreType)
  {
    switch (featureID)
    {
      case ChangePackage.RESOURCE_CHANGE__RESOURCE_URI:
        return getResourceURI();
      case ChangePackage.RESOURCE_CHANGE__RESOURCE:
        return getResource();
      case ChangePackage.RESOURCE_CHANGE__VALUE:
        return getValue();
      case ChangePackage.RESOURCE_CHANGE__LIST_CHANGES:
        return getListChanges();
    }
    return super.eGet(featureID, resolve, coreType);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @SuppressWarnings("unchecked")
  @Override
  public void eSet(int featureID, Object newValue)
  {
    switch (featureID)
    {
      case ChangePackage.RESOURCE_CHANGE__RESOURCE_URI:
        setResourceURI((String)newValue);
        return;
      case ChangePackage.RESOURCE_CHANGE__RESOURCE:
        setResource((Resource)newValue);
        return;
      case ChangePackage.RESOURCE_CHANGE__LIST_CHANGES:
        getListChanges().clear();
        getListChanges().addAll((Collection<? extends ListChange>)newValue);
        return;
    }
    super.eSet(featureID, newValue);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public void eUnset(int featureID)
  {
    switch (featureID)
    {
      case ChangePackage.RESOURCE_CHANGE__RESOURCE_URI:
        setResourceURI(RESOURCE_URI_EDEFAULT);
        return;
      case ChangePackage.RESOURCE_CHANGE__RESOURCE:
        setResource(RESOURCE_EDEFAULT);
        return;
      case ChangePackage.RESOURCE_CHANGE__LIST_CHANGES:
        getListChanges().clear();
        return;
    }
    super.eUnset(featureID);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public boolean eIsSet(int featureID)
  {
    switch (featureID)
    {
      case ChangePackage.RESOURCE_CHANGE__RESOURCE_URI:
        return RESOURCE_URI_EDEFAULT == null ? resourceURI != null : !RESOURCE_URI_EDEFAULT.equals(resourceURI);
      case ChangePackage.RESOURCE_CHANGE__RESOURCE:
        return RESOURCE_EDEFAULT == null ? resource != null : !RESOURCE_EDEFAULT.equals(resource);
      case ChangePackage.RESOURCE_CHANGE__VALUE:
        return getValue() != null;
      case ChangePackage.RESOURCE_CHANGE__LIST_CHANGES:
        return listChanges != null && !listChanges.isEmpty();
    }
    return super.eIsSet(featureID);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public String toString()
  {
    if (eIsProxy()) return super.toString();

    StringBuffer result = new StringBuffer(super.toString());
    result.append(" (resourceURI: ");
    result.append(resourceURI);
    result.append(", resource: ");
    result.append(resource);
    result.append(')');
    return result.toString();
  }

} //ResourceChangeImpl
