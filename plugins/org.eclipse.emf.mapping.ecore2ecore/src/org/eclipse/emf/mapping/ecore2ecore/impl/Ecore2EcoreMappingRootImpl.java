/*
 * Copyright (c) 2004 IBM Corporation and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *   IBM - Initial API and implementation
 *
 * $Id: Ecore2EcoreMappingRootImpl.java,v 1.2.2.1 2005/06/08 18:27:47 nickb Exp $
 */
package org.eclipse.emf.mapping.ecore2ecore.impl;


import java.util.Collection;
import java.util.Iterator;

import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.util.InternalEList;

import org.eclipse.emf.mapping.Mapping;
import org.eclipse.emf.mapping.MappingHelper;
import org.eclipse.emf.mapping.MappingPackage;

import org.eclipse.emf.mapping.ecore2ecore.Ecore2EcoreMappingRoot;
import org.eclipse.emf.mapping.ecore2ecore.Ecore2EcorePackage;

import org.eclipse.emf.mapping.impl.MappingRootImpl;


/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Mapping Root</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * </p>
 *
 * @generated
 */
public class Ecore2EcoreMappingRootImpl extends MappingRootImpl implements Ecore2EcoreMappingRoot
{
  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected Ecore2EcoreMappingRootImpl()
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
    return Ecore2EcorePackage.eINSTANCE.getEcore2EcoreMappingRoot();
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
        case Ecore2EcorePackage.ECORE2_ECORE_MAPPING_ROOT__HELPER:
          if (helper != null)
            msgs = ((InternalEObject)helper).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - Ecore2EcorePackage.ECORE2_ECORE_MAPPING_ROOT__HELPER, null, msgs);
          return basicSetHelper((MappingHelper)otherEnd, msgs);
        case Ecore2EcorePackage.ECORE2_ECORE_MAPPING_ROOT__NESTED:
          return ((InternalEList)getNested()).basicAdd(otherEnd, msgs);
        case Ecore2EcorePackage.ECORE2_ECORE_MAPPING_ROOT__NESTED_IN:
          if (eContainer != null)
            msgs = eBasicRemoveFromContainer(msgs);
          return eBasicSetContainer(otherEnd, Ecore2EcorePackage.ECORE2_ECORE_MAPPING_ROOT__NESTED_IN, msgs);
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
        case Ecore2EcorePackage.ECORE2_ECORE_MAPPING_ROOT__HELPER:
          return basicSetHelper(null, msgs);
        case Ecore2EcorePackage.ECORE2_ECORE_MAPPING_ROOT__NESTED:
          return ((InternalEList)getNested()).basicRemove(otherEnd, msgs);
        case Ecore2EcorePackage.ECORE2_ECORE_MAPPING_ROOT__NESTED_IN:
          return eBasicSetContainer(null, Ecore2EcorePackage.ECORE2_ECORE_MAPPING_ROOT__NESTED_IN, msgs);
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
        case Ecore2EcorePackage.ECORE2_ECORE_MAPPING_ROOT__NESTED_IN:
          return ((InternalEObject)eContainer).eInverseRemove(this, MappingPackage.MAPPING__NESTED, Mapping.class, msgs);
        default:
          return eDynamicBasicRemoveFromContainer(msgs);
      }
    }
    return ((InternalEObject)eContainer).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - eContainerFeatureID, null, msgs);
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
      case Ecore2EcorePackage.ECORE2_ECORE_MAPPING_ROOT__HELPER:
        return getHelper();
      case Ecore2EcorePackage.ECORE2_ECORE_MAPPING_ROOT__NESTED:
        return getNested();
      case Ecore2EcorePackage.ECORE2_ECORE_MAPPING_ROOT__NESTED_IN:
        return getNestedIn();
      case Ecore2EcorePackage.ECORE2_ECORE_MAPPING_ROOT__INPUTS:
        return getInputs();
      case Ecore2EcorePackage.ECORE2_ECORE_MAPPING_ROOT__OUTPUTS:
        return getOutputs();
      case Ecore2EcorePackage.ECORE2_ECORE_MAPPING_ROOT__TYPE_MAPPING:
        if (resolve) return getTypeMapping();
        return basicGetTypeMapping();
      case Ecore2EcorePackage.ECORE2_ECORE_MAPPING_ROOT__OUTPUT_READ_ONLY:
        return isOutputReadOnly() ? Boolean.TRUE : Boolean.FALSE;
      case Ecore2EcorePackage.ECORE2_ECORE_MAPPING_ROOT__TOP_TO_BOTTOM:
        return isTopToBottom() ? Boolean.TRUE : Boolean.FALSE;
      case Ecore2EcorePackage.ECORE2_ECORE_MAPPING_ROOT__COMMAND_STACK:
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
      case Ecore2EcorePackage.ECORE2_ECORE_MAPPING_ROOT__HELPER:
        setHelper((MappingHelper)newValue);
        return;
      case Ecore2EcorePackage.ECORE2_ECORE_MAPPING_ROOT__NESTED:
        getNested().clear();
        getNested().addAll((Collection)newValue);
        return;
      case Ecore2EcorePackage.ECORE2_ECORE_MAPPING_ROOT__NESTED_IN:
        setNestedIn((Mapping)newValue);
        return;
      case Ecore2EcorePackage.ECORE2_ECORE_MAPPING_ROOT__INPUTS:
        getInputs().clear();
        getInputs().addAll((Collection)newValue);
        return;
      case Ecore2EcorePackage.ECORE2_ECORE_MAPPING_ROOT__OUTPUTS:
        getOutputs().clear();
        getOutputs().addAll((Collection)newValue);
        return;
      case Ecore2EcorePackage.ECORE2_ECORE_MAPPING_ROOT__TYPE_MAPPING:
        setTypeMapping((Mapping)newValue);
        return;
      case Ecore2EcorePackage.ECORE2_ECORE_MAPPING_ROOT__OUTPUT_READ_ONLY:
        setOutputReadOnly(((Boolean)newValue).booleanValue());
        return;
      case Ecore2EcorePackage.ECORE2_ECORE_MAPPING_ROOT__TOP_TO_BOTTOM:
        setTopToBottom(((Boolean)newValue).booleanValue());
        return;
      case Ecore2EcorePackage.ECORE2_ECORE_MAPPING_ROOT__COMMAND_STACK:
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
      case Ecore2EcorePackage.ECORE2_ECORE_MAPPING_ROOT__HELPER:
        setHelper((MappingHelper)null);
        return;
      case Ecore2EcorePackage.ECORE2_ECORE_MAPPING_ROOT__NESTED:
        getNested().clear();
        return;
      case Ecore2EcorePackage.ECORE2_ECORE_MAPPING_ROOT__NESTED_IN:
        setNestedIn((Mapping)null);
        return;
      case Ecore2EcorePackage.ECORE2_ECORE_MAPPING_ROOT__INPUTS:
        getInputs().clear();
        return;
      case Ecore2EcorePackage.ECORE2_ECORE_MAPPING_ROOT__OUTPUTS:
        getOutputs().clear();
        return;
      case Ecore2EcorePackage.ECORE2_ECORE_MAPPING_ROOT__TYPE_MAPPING:
        setTypeMapping((Mapping)null);
        return;
      case Ecore2EcorePackage.ECORE2_ECORE_MAPPING_ROOT__OUTPUT_READ_ONLY:
        setOutputReadOnly(OUTPUT_READ_ONLY_EDEFAULT);
        return;
      case Ecore2EcorePackage.ECORE2_ECORE_MAPPING_ROOT__TOP_TO_BOTTOM:
        setTopToBottom(TOP_TO_BOTTOM_EDEFAULT);
        return;
      case Ecore2EcorePackage.ECORE2_ECORE_MAPPING_ROOT__COMMAND_STACK:
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
      case Ecore2EcorePackage.ECORE2_ECORE_MAPPING_ROOT__HELPER:
        return helper != null;
      case Ecore2EcorePackage.ECORE2_ECORE_MAPPING_ROOT__NESTED:
        return nested != null && !nested.isEmpty();
      case Ecore2EcorePackage.ECORE2_ECORE_MAPPING_ROOT__NESTED_IN:
        return getNestedIn() != null;
      case Ecore2EcorePackage.ECORE2_ECORE_MAPPING_ROOT__INPUTS:
        return inputs != null && !inputs.isEmpty();
      case Ecore2EcorePackage.ECORE2_ECORE_MAPPING_ROOT__OUTPUTS:
        return outputs != null && !outputs.isEmpty();
      case Ecore2EcorePackage.ECORE2_ECORE_MAPPING_ROOT__TYPE_MAPPING:
        return typeMapping != null;
      case Ecore2EcorePackage.ECORE2_ECORE_MAPPING_ROOT__OUTPUT_READ_ONLY:
        return outputReadOnly != OUTPUT_READ_ONLY_EDEFAULT;
      case Ecore2EcorePackage.ECORE2_ECORE_MAPPING_ROOT__TOP_TO_BOTTOM:
        return topToBottom != TOP_TO_BOTTOM_EDEFAULT;
      case Ecore2EcorePackage.ECORE2_ECORE_MAPPING_ROOT__COMMAND_STACK:
        return COMMAND_STACK_EDEFAULT == null ? commandStack != null : !COMMAND_STACK_EDEFAULT.equals(commandStack);
    }
    return eDynamicIsSet(eFeature);
  }

  /* (non-Javadoc)
   * @see org.eclipse.emf.mapping.ecore2ecore.Ecore2EcoreMapping#getInputEPackage()
   */
  public EPackage getInputEPackage()
  {
    return getInputs().isEmpty() ? null : (EPackage)getInputs().get(0);
  }

  /* (non-Javadoc)
   * @see org.eclipse.emf.mapping.ecore2ecore.Ecore2EcoreMapping#getOutputEPackage()
   */
  public EPackage getOutputEPackage()
  {
    return getOutputs().isEmpty() ? null : (EPackage)getOutputs().get(0);
  }
  
  /* (non-Javadoc)
   * @see org.eclipse.emf.mapping.MappingRoot#canCreateMapping(java.util.Collection, java.util.Collection, org.eclipse.emf.mapping.Mapping)
   */
  public boolean canCreateMapping(Collection inputs, Collection outputs, Mapping mapping)
  {
    if (mapping == this)
    {
      for (Iterator i = inputs.iterator(); i.hasNext();)
      {
        if (!(i.next() instanceof EPackage))
        {
          return false;
        }
      }
      for (Iterator i = outputs.iterator(); i.hasNext();)
      {
        if (!(i.next() instanceof EPackage))
        {
          return false;
        }
      }
      return true;
    }
    else
    {
      return super.canCreateMapping(inputs, outputs, mapping);
    }
  }
} //Ecore2EcoreMappingRootImpl
