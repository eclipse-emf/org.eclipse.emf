/**
 * <copyright>
 * </copyright>
 *
 * $Id: NameTypeImpl.java,v 1.1 2005/06/01 22:28:12 elena Exp $
 */
package org.eclipse.emf.test.models.personal.impl;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EStructuralFeature;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

import org.eclipse.emf.ecore.sdo.impl.EDataObjectImpl;

import org.eclipse.emf.test.models.personal.NameType;
import org.eclipse.emf.test.models.personal.PersonalPackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Name Type</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.emf.test.models.personal.impl.NameTypeImpl#getFamily <em>Family</em>}</li>
 *   <li>{@link org.eclipse.emf.test.models.personal.impl.NameTypeImpl#getGiven <em>Given</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class NameTypeImpl extends EDataObjectImpl implements NameType
{
  /**
   * The default value of the '{@link #getFamily() <em>Family</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getFamily()
   * @generated
   * @ordered
   */
  protected static final String FAMILY_EDEFAULT = null;

  /**
   * The cached value of the '{@link #getFamily() <em>Family</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getFamily()
   * @generated
   * @ordered
   */
  protected String family = FAMILY_EDEFAULT;

  /**
   * The default value of the '{@link #getGiven() <em>Given</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getGiven()
   * @generated
   * @ordered
   */
  protected static final String GIVEN_EDEFAULT = null;

  /**
   * The cached value of the '{@link #getGiven() <em>Given</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getGiven()
   * @generated
   * @ordered
   */
  protected String given = GIVEN_EDEFAULT;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected NameTypeImpl()
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
    return PersonalPackage.eINSTANCE.getNameType();
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public String getFamily()
  {
    return family;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setFamily(String newFamily)
  {
    String oldFamily = family;
    family = newFamily;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, PersonalPackage.NAME_TYPE__FAMILY, oldFamily, family));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public String getGiven()
  {
    return given;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setGiven(String newGiven)
  {
    String oldGiven = given;
    given = newGiven;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, PersonalPackage.NAME_TYPE__GIVEN, oldGiven, given));
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
      case PersonalPackage.NAME_TYPE__FAMILY:
        return getFamily();
      case PersonalPackage.NAME_TYPE__GIVEN:
        return getGiven();
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
      case PersonalPackage.NAME_TYPE__FAMILY:
        setFamily((String)newValue);
        return;
      case PersonalPackage.NAME_TYPE__GIVEN:
        setGiven((String)newValue);
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
      case PersonalPackage.NAME_TYPE__FAMILY:
        setFamily(FAMILY_EDEFAULT);
        return;
      case PersonalPackage.NAME_TYPE__GIVEN:
        setGiven(GIVEN_EDEFAULT);
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
      case PersonalPackage.NAME_TYPE__FAMILY:
        return FAMILY_EDEFAULT == null ? family != null : !FAMILY_EDEFAULT.equals(family);
      case PersonalPackage.NAME_TYPE__GIVEN:
        return GIVEN_EDEFAULT == null ? given != null : !GIVEN_EDEFAULT.equals(given);
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
    result.append(" (family: ");
    result.append(family);
    result.append(", given: ");
    result.append(given);
    result.append(')');
    return result.toString();
  }

} //NameTypeImpl
