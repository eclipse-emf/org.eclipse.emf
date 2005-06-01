/**
 * <copyright>
 * </copyright>
 *
 * $Id: LinkTypeImpl.java,v 1.1 2005/06/01 22:28:12 elena Exp $
 */
package org.eclipse.emf.test.models.personal.mixed.impl;

import commonj.sdo.Sequence;

import java.util.Collection;
import java.util.List;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

import org.eclipse.emf.ecore.sdo.impl.EDataObjectImpl;

import org.eclipse.emf.ecore.sdo.util.BasicESequence;
import org.eclipse.emf.ecore.sdo.util.ESequence;

import org.eclipse.emf.ecore.util.BasicFeatureMap;
import org.eclipse.emf.ecore.util.InternalEList;

import org.eclipse.emf.test.models.personal.mixed.LinkType;
import org.eclipse.emf.test.models.personal.mixed.MixedPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Link Type</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.emf.test.models.personal.mixed.impl.LinkTypeImpl#getMixed <em>Mixed</em>}</li>
 *   <li>{@link org.eclipse.emf.test.models.personal.mixed.impl.LinkTypeImpl#getManager <em>Manager</em>}</li>
 *   <li>{@link org.eclipse.emf.test.models.personal.mixed.impl.LinkTypeImpl#getSubordinates <em>Subordinates</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class LinkTypeImpl extends EDataObjectImpl implements LinkType
{
  /**
   * The cached value of the '{@link #getMixed() <em>Mixed</em>}' attribute list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getMixed()
   * @generated
   * @ordered
   */
  protected ESequence mixed = null;

  /**
   * The default value of the '{@link #getManager() <em>Manager</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getManager()
   * @generated
   * @ordered
   */
  protected static final String MANAGER_EDEFAULT = null;

  /**
   * The cached value of the '{@link #getManager() <em>Manager</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getManager()
   * @generated
   * @ordered
   */
  protected String manager = MANAGER_EDEFAULT;

  /**
   * The default value of the '{@link #getSubordinates() <em>Subordinates</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getSubordinates()
   * @generated
   * @ordered
   */
  protected static final List SUBORDINATES_EDEFAULT = null;

  /**
   * The cached value of the '{@link #getSubordinates() <em>Subordinates</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getSubordinates()
   * @generated
   * @ordered
   */
  protected List subordinates = SUBORDINATES_EDEFAULT;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected LinkTypeImpl()
  {
    super();
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected EClass eStaticClass()
  {
    return MixedPackage.eINSTANCE.getLinkType();
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public Sequence getMixed()
  {
    if (mixed == null)
    {
      mixed = new BasicESequence(new BasicFeatureMap(this, MixedPackage.LINK_TYPE__MIXED));
    }
    return mixed;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public String getManager()
  {
    return manager;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setManager(String newManager)
  {
    String oldManager = manager;
    manager = newManager;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, MixedPackage.LINK_TYPE__MANAGER, oldManager, manager));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public List getSubordinates()
  {
    return subordinates;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setSubordinates(List newSubordinates)
  {
    List oldSubordinates = subordinates;
    subordinates = newSubordinates;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, MixedPackage.LINK_TYPE__SUBORDINATES, oldSubordinates, subordinates));
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
        case MixedPackage.LINK_TYPE__MIXED:
        return ((InternalEList)((ESequence)getMixed()).featureMap()).basicRemove(otherEnd, msgs);
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
      case MixedPackage.LINK_TYPE__MIXED:
        return ((ESequence)getMixed()).featureMap();
      case MixedPackage.LINK_TYPE__MANAGER:
        return getManager();
      case MixedPackage.LINK_TYPE__SUBORDINATES:
        return getSubordinates();
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
      case MixedPackage.LINK_TYPE__MIXED:
        ((ESequence)getMixed()).featureMap().clear();
        ((ESequence)getMixed()).featureMap().addAll((Collection)newValue);
        return;
      case MixedPackage.LINK_TYPE__MANAGER:
        setManager((String)newValue);
        return;
      case MixedPackage.LINK_TYPE__SUBORDINATES:
        setSubordinates((List)newValue);
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
      case MixedPackage.LINK_TYPE__MIXED:
        ((ESequence)getMixed()).featureMap().clear();
        return;
      case MixedPackage.LINK_TYPE__MANAGER:
        setManager(MANAGER_EDEFAULT);
        return;
      case MixedPackage.LINK_TYPE__SUBORDINATES:
        setSubordinates(SUBORDINATES_EDEFAULT);
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
      case MixedPackage.LINK_TYPE__MIXED:
        return mixed != null && !mixed.featureMap().isEmpty();
      case MixedPackage.LINK_TYPE__MANAGER:
        return MANAGER_EDEFAULT == null ? manager != null : !MANAGER_EDEFAULT.equals(manager);
      case MixedPackage.LINK_TYPE__SUBORDINATES:
        return SUBORDINATES_EDEFAULT == null ? subordinates != null : !SUBORDINATES_EDEFAULT.equals(subordinates);
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
    result.append(" (mixed: ");
    result.append(mixed);
    result.append(", manager: ");
    result.append(manager);
    result.append(", subordinates: ");
    result.append(subordinates);
    result.append(')');
    return result.toString();
  }

} //LinkTypeImpl
