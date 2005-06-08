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
 * $Id: XSD2EcoreMappingRootImpl.java,v 1.3 2005/06/08 06:18:57 nickb Exp $
 */
package org.eclipse.emf.mapping.xsd2ecore.impl;


import java.util.Collection;

import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.util.InternalEList;
import org.eclipse.emf.mapping.Mapping;
import org.eclipse.emf.mapping.MappingHelper;
import org.eclipse.emf.mapping.MappingPackage;
import org.eclipse.emf.mapping.impl.MappingRootImpl;
import org.eclipse.emf.mapping.xsd2ecore.XSD2EcoreMappingRoot;
import org.eclipse.emf.mapping.xsd2ecore.XSD2EcorePackage;


/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Mapping Root</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * </p>
 *
 * @generated
 */
public class XSD2EcoreMappingRootImpl extends MappingRootImpl implements XSD2EcoreMappingRoot
{
  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected XSD2EcoreMappingRootImpl()
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
    return XSD2EcorePackage.eINSTANCE.getXSD2EcoreMappingRoot();
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, Class baseClass, NotificationChain msgs)
  {
    if (featureID >= 0)
    {
      switch (eDerivedStructuralFeatureID(featureID, baseClass))
      {
        case XSD2EcorePackage.XSD2_ECORE_MAPPING_ROOT__HELPER:
          if (helper != null)
            msgs = ((InternalEObject)helper).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - XSD2EcorePackage.XSD2_ECORE_MAPPING_ROOT__HELPER, null, msgs);
          return basicSetHelper((MappingHelper)otherEnd, msgs);
        case XSD2EcorePackage.XSD2_ECORE_MAPPING_ROOT__NESTED:
          return ((InternalEList)getNested()).basicAdd(otherEnd, msgs);
        case XSD2EcorePackage.XSD2_ECORE_MAPPING_ROOT__NESTED_IN:
          if (eContainer != null)
            msgs = eBasicRemoveFromContainer(msgs);
          return eBasicSetContainer(otherEnd, XSD2EcorePackage.XSD2_ECORE_MAPPING_ROOT__NESTED_IN, msgs);
        default:
          return eDynamicInverseAdd(otherEnd, featureID, baseClass, msgs);
      }
    }
    if (eContainer != null)
      msgs = eBasicRemoveFromContainer(msgs);
    return eBasicSetContainer(otherEnd, featureID, msgs);
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
        case XSD2EcorePackage.XSD2_ECORE_MAPPING_ROOT__HELPER:
          return basicSetHelper(null, msgs);
        case XSD2EcorePackage.XSD2_ECORE_MAPPING_ROOT__NESTED:
          return ((InternalEList)getNested()).basicRemove(otherEnd, msgs);
        case XSD2EcorePackage.XSD2_ECORE_MAPPING_ROOT__NESTED_IN:
          return eBasicSetContainer(null, XSD2EcorePackage.XSD2_ECORE_MAPPING_ROOT__NESTED_IN, msgs);
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
  public NotificationChain eBasicRemoveFromContainer(NotificationChain msgs)
  {
    if (eContainerFeatureID >= 0)
    {
      switch (eContainerFeatureID)
      {
        case XSD2EcorePackage.XSD2_ECORE_MAPPING_ROOT__NESTED_IN:
          return eContainer.eInverseRemove(this, MappingPackage.MAPPING__NESTED, Mapping.class, msgs);
        default:
          return eDynamicBasicRemoveFromContainer(msgs);
      }
    }
    return eContainer.eInverseRemove(this, EOPPOSITE_FEATURE_BASE - eContainerFeatureID, null, msgs);
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
      case XSD2EcorePackage.XSD2_ECORE_MAPPING_ROOT__HELPER:
        return getHelper();
      case XSD2EcorePackage.XSD2_ECORE_MAPPING_ROOT__NESTED:
        return getNested();
      case XSD2EcorePackage.XSD2_ECORE_MAPPING_ROOT__NESTED_IN:
        return getNestedIn();
      case XSD2EcorePackage.XSD2_ECORE_MAPPING_ROOT__INPUTS:
        return getInputs();
      case XSD2EcorePackage.XSD2_ECORE_MAPPING_ROOT__OUTPUTS:
        return getOutputs();
      case XSD2EcorePackage.XSD2_ECORE_MAPPING_ROOT__TYPE_MAPPING:
        if (resolve) return getTypeMapping();
        return basicGetTypeMapping();
      case XSD2EcorePackage.XSD2_ECORE_MAPPING_ROOT__OUTPUT_READ_ONLY:
        return isOutputReadOnly() ? Boolean.TRUE : Boolean.FALSE;
      case XSD2EcorePackage.XSD2_ECORE_MAPPING_ROOT__TOP_TO_BOTTOM:
        return isTopToBottom() ? Boolean.TRUE : Boolean.FALSE;
      case XSD2EcorePackage.XSD2_ECORE_MAPPING_ROOT__COMMAND_STACK:
        return getCommandStack();
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
      case XSD2EcorePackage.XSD2_ECORE_MAPPING_ROOT__HELPER:
        setHelper((MappingHelper)newValue);
        return;
      case XSD2EcorePackage.XSD2_ECORE_MAPPING_ROOT__NESTED:
        getNested().clear();
        getNested().addAll((Collection)newValue);
        return;
      case XSD2EcorePackage.XSD2_ECORE_MAPPING_ROOT__NESTED_IN:
        setNestedIn((Mapping)newValue);
        return;
      case XSD2EcorePackage.XSD2_ECORE_MAPPING_ROOT__INPUTS:
        getInputs().clear();
        getInputs().addAll((Collection)newValue);
        return;
      case XSD2EcorePackage.XSD2_ECORE_MAPPING_ROOT__OUTPUTS:
        getOutputs().clear();
        getOutputs().addAll((Collection)newValue);
        return;
      case XSD2EcorePackage.XSD2_ECORE_MAPPING_ROOT__TYPE_MAPPING:
        setTypeMapping((Mapping)newValue);
        return;
      case XSD2EcorePackage.XSD2_ECORE_MAPPING_ROOT__OUTPUT_READ_ONLY:
        setOutputReadOnly(((Boolean)newValue).booleanValue());
        return;
      case XSD2EcorePackage.XSD2_ECORE_MAPPING_ROOT__TOP_TO_BOTTOM:
        setTopToBottom(((Boolean)newValue).booleanValue());
        return;
      case XSD2EcorePackage.XSD2_ECORE_MAPPING_ROOT__COMMAND_STACK:
        setCommandStack((String)newValue);
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
      case XSD2EcorePackage.XSD2_ECORE_MAPPING_ROOT__HELPER:
        setHelper((MappingHelper)null);
        return;
      case XSD2EcorePackage.XSD2_ECORE_MAPPING_ROOT__NESTED:
        getNested().clear();
        return;
      case XSD2EcorePackage.XSD2_ECORE_MAPPING_ROOT__NESTED_IN:
        setNestedIn((Mapping)null);
        return;
      case XSD2EcorePackage.XSD2_ECORE_MAPPING_ROOT__INPUTS:
        getInputs().clear();
        return;
      case XSD2EcorePackage.XSD2_ECORE_MAPPING_ROOT__OUTPUTS:
        getOutputs().clear();
        return;
      case XSD2EcorePackage.XSD2_ECORE_MAPPING_ROOT__TYPE_MAPPING:
        setTypeMapping((Mapping)null);
        return;
      case XSD2EcorePackage.XSD2_ECORE_MAPPING_ROOT__OUTPUT_READ_ONLY:
        setOutputReadOnly(OUTPUT_READ_ONLY_EDEFAULT);
        return;
      case XSD2EcorePackage.XSD2_ECORE_MAPPING_ROOT__TOP_TO_BOTTOM:
        setTopToBottom(TOP_TO_BOTTOM_EDEFAULT);
        return;
      case XSD2EcorePackage.XSD2_ECORE_MAPPING_ROOT__COMMAND_STACK:
        setCommandStack(COMMAND_STACK_EDEFAULT);
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
      case XSD2EcorePackage.XSD2_ECORE_MAPPING_ROOT__HELPER:
        return helper != null;
      case XSD2EcorePackage.XSD2_ECORE_MAPPING_ROOT__NESTED:
        return nested != null && !nested.isEmpty();
      case XSD2EcorePackage.XSD2_ECORE_MAPPING_ROOT__NESTED_IN:
        return getNestedIn() != null;
      case XSD2EcorePackage.XSD2_ECORE_MAPPING_ROOT__INPUTS:
        return inputs != null && !inputs.isEmpty();
      case XSD2EcorePackage.XSD2_ECORE_MAPPING_ROOT__OUTPUTS:
        return outputs != null && !outputs.isEmpty();
      case XSD2EcorePackage.XSD2_ECORE_MAPPING_ROOT__TYPE_MAPPING:
        return typeMapping != null;
      case XSD2EcorePackage.XSD2_ECORE_MAPPING_ROOT__OUTPUT_READ_ONLY:
        return outputReadOnly != OUTPUT_READ_ONLY_EDEFAULT;
      case XSD2EcorePackage.XSD2_ECORE_MAPPING_ROOT__TOP_TO_BOTTOM:
        return topToBottom != TOP_TO_BOTTOM_EDEFAULT;
      case XSD2EcorePackage.XSD2_ECORE_MAPPING_ROOT__COMMAND_STACK:
        return COMMAND_STACK_EDEFAULT == null ? commandStack != null : !COMMAND_STACK_EDEFAULT.equals(commandStack);
    }
    return eDynamicIsSet(eFeature);
  }

} //XSD2EcoreMappingRootImpl
