/**
 * <copyright>
 * </copyright>
 *
 * $Id: LinkTypeImpl.java,v 1.1 2005/06/01 22:28:12 elena Exp $
 */
package org.eclipse.emf.test.models.personal.impl;

import java.util.List;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EStructuralFeature;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

import org.eclipse.emf.ecore.sdo.impl.EDataObjectImpl;

import org.eclipse.emf.test.models.personal.LinkType;
import org.eclipse.emf.test.models.personal.PersonalPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Link Type</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.emf.test.models.personal.impl.LinkTypeImpl#getManager <em>Manager</em>}</li>
 *   <li>{@link org.eclipse.emf.test.models.personal.impl.LinkTypeImpl#getSubordinates <em>Subordinates</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class LinkTypeImpl extends EDataObjectImpl implements LinkType
{
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
    return PersonalPackage.eINSTANCE.getLinkType();
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
      eNotify(new ENotificationImpl(this, Notification.SET, PersonalPackage.LINK_TYPE__MANAGER, oldManager, manager));
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
      eNotify(new ENotificationImpl(this, Notification.SET, PersonalPackage.LINK_TYPE__SUBORDINATES, oldSubordinates, subordinates));
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
      case PersonalPackage.LINK_TYPE__MANAGER:
        return getManager();
      case PersonalPackage.LINK_TYPE__SUBORDINATES:
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
      case PersonalPackage.LINK_TYPE__MANAGER:
        setManager((String)newValue);
        return;
      case PersonalPackage.LINK_TYPE__SUBORDINATES:
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
      case PersonalPackage.LINK_TYPE__MANAGER:
        setManager(MANAGER_EDEFAULT);
        return;
      case PersonalPackage.LINK_TYPE__SUBORDINATES:
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
      case PersonalPackage.LINK_TYPE__MANAGER:
        return MANAGER_EDEFAULT == null ? manager != null : !MANAGER_EDEFAULT.equals(manager);
      case PersonalPackage.LINK_TYPE__SUBORDINATES:
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
    result.append(" (manager: ");
    result.append(manager);
    result.append(", subordinates: ");
    result.append(subordinates);
    result.append(')');
    return result.toString();
  }

} //LinkTypeImpl
