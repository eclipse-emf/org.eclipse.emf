/**
 * <copyright>
 * </copyright>
 *
 * $Id: ResourceChangeImpl.java,v 1.1 2004/03/06 17:31:32 marcelop Exp $
 */
package org.eclipse.emf.ecore.change.impl;

import java.util.Collection;
import java.util.Iterator;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.BasicEList;
import org.eclipse.emf.common.util.ECollections;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EStructuralFeature;
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
  protected EList listChanges = null;

  protected EList valueField = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected ResourceChangeImpl()
  {
    super();
  }

  protected ResourceChangeImpl(Resource resource, EList value)
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
  protected EClass eStaticClass()
  {
    return ChangePackage.eINSTANCE.getResourceChange();
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
    setResourceURI(newResource.getURI().toString());
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated NOT
   */
  public EList getValue()
  {
    if (valueField == null)
    {
      Resource resource = getResource();
      if (resource != null)
      {
        EList changedList = new BasicEList(resource.getContents());
        apply(changedList);
        valueField = changedList; // cache result
        return changedList;
      }
      return ECollections.EMPTY_ELIST;
    }
    return valueField;
  }

  protected void setValue(EList value)
  {
    valueField = value;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EList getListChanges()
  {
    if (listChanges == null)
    {
      listChanges = new EObjectContainmentEList(ListChange.class, this, ChangePackage.RESOURCE_CHANGE__LIST_CHANGES);
    }
    return listChanges;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated NOT
   */
  public void apply()
  {
    Resource resource = getResource();
    if (resource != null && listChanges != null)
    {
      apply(resource.getContents());
    }
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated NOT
   */
  public void applyAndReverse()
  {
    Resource resource = getResource();
    if (resource != null && listChanges != null)
    {
      EList applyToList = resource.getContents();
      for (Iterator iter = getListChanges().iterator(); iter.hasNext(); )
      {
        ListChange listChange = (ListChange)iter.next();
        listChange.applyAndReverse(applyToList);
      }
      ECollections.reverse(getListChanges());
      setValue(null);
    }
  }

  protected void apply(EList toList)
  {
    for (Iterator iter = getListChanges().iterator(); iter.hasNext(); )
    {
      ListChange listChange = (ListChange)iter.next();
      listChange.apply(toList);
    }
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, Class baseClass, NotificationChain msgs)
  {
    if (featureID >= 0)
    {
      switch (eDerivedStructuralFeatureID(featureID, baseClass))
      {
        case ChangePackage.RESOURCE_CHANGE__LIST_CHANGES:
          return ((InternalEList)getListChanges()).basicRemove(otherEnd, msgs);
        default:
          return eDynamicInverseRemove(otherEnd, featureID, baseClass, msgs);
      }
    }
    return eBasicSetContainer(null, featureID, msgs);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public Object eGet(EStructuralFeature eFeature, boolean resolve)
  {
    switch (eDerivedStructuralFeatureID(eFeature))
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
    return eDynamicGet(eFeature, resolve);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void eSet(EStructuralFeature eFeature, Object newValue)
  {
    switch (eDerivedStructuralFeatureID(eFeature))
    {
      case ChangePackage.RESOURCE_CHANGE__RESOURCE_URI:
        setResourceURI((String)newValue);
        return;
      case ChangePackage.RESOURCE_CHANGE__RESOURCE:
        setResource((Resource)newValue);
        return;
      case ChangePackage.RESOURCE_CHANGE__LIST_CHANGES:
        getListChanges().clear();
        getListChanges().addAll((Collection)newValue);
        return;
    }
    eDynamicSet(eFeature, newValue);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void eUnset(EStructuralFeature eFeature)
  {
    switch (eDerivedStructuralFeatureID(eFeature))
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
    eDynamicUnset(eFeature);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public boolean eIsSet(EStructuralFeature eFeature)
  {
    switch (eDerivedStructuralFeatureID(eFeature))
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
    return eDynamicIsSet(eFeature);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
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
