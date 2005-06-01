/**
 * <copyright>
 * </copyright>
 *
 * $Id: NameTypeImpl.java,v 1.1 2005/06/01 22:28:12 elena Exp $
 */
package org.eclipse.emf.test.models.personal.mixed.impl;

import commonj.sdo.Sequence;

import java.util.Collection;

import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.sdo.impl.EDataObjectImpl;

import org.eclipse.emf.ecore.sdo.util.BasicESequence;
import org.eclipse.emf.ecore.sdo.util.ESequence;

import org.eclipse.emf.ecore.util.BasicFeatureMap;
import org.eclipse.emf.ecore.util.FeatureMap;
import org.eclipse.emf.ecore.util.InternalEList;

import org.eclipse.emf.test.models.personal.mixed.MixedPackage;
import org.eclipse.emf.test.models.personal.mixed.NameType;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Name Type</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.emf.test.models.personal.mixed.impl.NameTypeImpl#getMixed <em>Mixed</em>}</li>
 *   <li>{@link org.eclipse.emf.test.models.personal.mixed.impl.NameTypeImpl#getFamily <em>Family</em>}</li>
 *   <li>{@link org.eclipse.emf.test.models.personal.mixed.impl.NameTypeImpl#getGiven <em>Given</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class NameTypeImpl extends EDataObjectImpl implements NameType
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
   * The default value of the '{@link #getFamily() <em>Family</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getFamily()
   * @generated
   * @ordered
   */
  protected static final String FAMILY_EDEFAULT = null;

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
    return MixedPackage.eINSTANCE.getNameType();
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
      mixed = new BasicESequence(new BasicFeatureMap(this, MixedPackage.NAME_TYPE__MIXED));
    }
    return mixed;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public String getFamily()
  {
    return (String)((ESequence)getMixed()).featureMap().get(MixedPackage.eINSTANCE.getNameType_Family(), true);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setFamily(String newFamily)
  {
    ((FeatureMap.Internal)((ESequence)getMixed()).featureMap()).set(MixedPackage.eINSTANCE.getNameType_Family(), newFamily);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public String getGiven()
  {
    return (String)((ESequence)getMixed()).featureMap().get(MixedPackage.eINSTANCE.getNameType_Given(), true);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setGiven(String newGiven)
  {
    ((FeatureMap.Internal)((ESequence)getMixed()).featureMap()).set(MixedPackage.eINSTANCE.getNameType_Given(), newGiven);
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
        case MixedPackage.NAME_TYPE__MIXED:
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
      case MixedPackage.NAME_TYPE__MIXED:
        return ((ESequence)getMixed()).featureMap();
      case MixedPackage.NAME_TYPE__FAMILY:
        return getFamily();
      case MixedPackage.NAME_TYPE__GIVEN:
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
      case MixedPackage.NAME_TYPE__MIXED:
        ((ESequence)getMixed()).featureMap().clear();
        ((ESequence)getMixed()).featureMap().addAll((Collection)newValue);
        return;
      case MixedPackage.NAME_TYPE__FAMILY:
        setFamily((String)newValue);
        return;
      case MixedPackage.NAME_TYPE__GIVEN:
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
      case MixedPackage.NAME_TYPE__MIXED:
        ((ESequence)getMixed()).featureMap().clear();
        return;
      case MixedPackage.NAME_TYPE__FAMILY:
        setFamily(FAMILY_EDEFAULT);
        return;
      case MixedPackage.NAME_TYPE__GIVEN:
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
      case MixedPackage.NAME_TYPE__MIXED:
        return mixed != null && !mixed.featureMap().isEmpty();
      case MixedPackage.NAME_TYPE__FAMILY:
        return FAMILY_EDEFAULT == null ? getFamily() != null : !FAMILY_EDEFAULT.equals(getFamily());
      case MixedPackage.NAME_TYPE__GIVEN:
        return GIVEN_EDEFAULT == null ? getGiven() != null : !GIVEN_EDEFAULT.equals(getGiven());
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
    result.append(')');
    return result.toString();
  }

} //NameTypeImpl
