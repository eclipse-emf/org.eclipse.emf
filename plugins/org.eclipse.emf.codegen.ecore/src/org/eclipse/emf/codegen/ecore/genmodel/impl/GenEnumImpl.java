/**
 * <copyright> 
 *
 * Copyright (c) 2002-2004 IBM Corporation and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Common Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/cpl-v10.html
 * 
 * Contributors: 
 *   IBM - Initial API and implementation
 *
 * </copyright>
 *
 * $Id: GenEnumImpl.java,v 1.4 2005/04/04 19:23:46 emerks Exp $
 */
package org.eclipse.emf.codegen.ecore.genmodel.impl;


import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.SubProgressMonitor;

import org.eclipse.emf.codegen.ecore.CodeGenEcorePlugin;
import org.eclipse.emf.codegen.ecore.Generator;
import org.eclipse.emf.codegen.ecore.genmodel.GenEnum;
import org.eclipse.emf.codegen.ecore.genmodel.GenEnumLiteral;
import org.eclipse.emf.codegen.ecore.genmodel.GenModelPackage;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EEnum;
import org.eclipse.emf.ecore.EEnumLiteral;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.util.EObjectContainmentWithInverseEList;
import org.eclipse.emf.ecore.util.InternalEList;


/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Gen Enum</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.emf.codegen.ecore.genmodel.impl.GenEnumImpl#getEcoreEnum <em>Ecore Enum</em>}</li>
 *   <li>{@link org.eclipse.emf.codegen.ecore.genmodel.impl.GenEnumImpl#getGenEnumLiterals <em>Gen Enum Literals</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class GenEnumImpl extends GenDataTypeImpl implements GenEnum
{
  /**
   * The cached value of the '{@link #getEcoreEnum() <em>Ecore Enum</em>}' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getEcoreEnum()
   * @generated
   * @ordered
   */
  protected EEnum ecoreEnum = null;

  /**
   * The cached value of the '{@link #getGenEnumLiterals() <em>Gen Enum Literals</em>}' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getGenEnumLiterals()
   * @generated
   * @ordered
   */
  protected EList genEnumLiterals = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated modifiable
   */
  protected GenEnumImpl() 
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
    return GenModelPackage.eINSTANCE.getGenEnum();
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EEnum getEcoreEnum()
  {
    if (ecoreEnum != null && ecoreEnum.eIsProxy())
    {
      EEnum oldEcoreEnum = ecoreEnum;
      ecoreEnum = (EEnum)eResolveProxy((InternalEObject)ecoreEnum);
      if (ecoreEnum != oldEcoreEnum)
      {
        if (eNotificationRequired())
          eNotify(new ENotificationImpl(this, Notification.RESOLVE, GenModelPackage.GEN_ENUM__ECORE_ENUM, oldEcoreEnum, ecoreEnum));
      }
    }
    return ecoreEnum;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EEnum basicGetEcoreEnum()
  {
    return ecoreEnum;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setEcoreEnum(EEnum newEcoreEnum)
  {
    EEnum oldEcoreEnum = ecoreEnum;
    ecoreEnum = newEcoreEnum;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, GenModelPackage.GEN_ENUM__ECORE_ENUM, oldEcoreEnum, ecoreEnum));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EList getGenEnumLiterals()
  {
    if (genEnumLiterals == null)
    {
      genEnumLiterals = new EObjectContainmentWithInverseEList(GenEnumLiteral.class, this, GenModelPackage.GEN_ENUM__GEN_ENUM_LITERALS, GenModelPackage.GEN_ENUM_LITERAL__GEN_ENUM);
    }
    return genEnumLiterals;
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
        case GenModelPackage.GEN_ENUM__GEN_ENUM_LITERALS:
          return ((InternalEList)getGenEnumLiterals()).basicAdd(otherEnd, msgs);
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
        case GenModelPackage.GEN_ENUM__GEN_ENUM_LITERALS:
          return ((InternalEList)getGenEnumLiterals()).basicRemove(otherEnd, msgs);
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
      case GenModelPackage.GEN_ENUM__GEN_PACKAGE:
        return getGenPackage();
      case GenModelPackage.GEN_ENUM__ECORE_DATA_TYPE:
        if (resolve) return getEcoreDataType();
        return basicGetEcoreDataType();
      case GenModelPackage.GEN_ENUM__ECORE_ENUM:
        if (resolve) return getEcoreEnum();
        return basicGetEcoreEnum();
      case GenModelPackage.GEN_ENUM__GEN_ENUM_LITERALS:
        return getGenEnumLiterals();
    }
    return eDynamicGet(eFeature, resolve);
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
      case GenModelPackage.GEN_ENUM__GEN_PACKAGE:
        return getGenPackage() != null;
      case GenModelPackage.GEN_ENUM__ECORE_DATA_TYPE:
        return ecoreDataType != null;
      case GenModelPackage.GEN_ENUM__ECORE_ENUM:
        return ecoreEnum != null;
      case GenModelPackage.GEN_ENUM__GEN_ENUM_LITERALS:
        return genEnumLiterals != null && !genEnumLiterals.isEmpty();
    }
    return eDynamicIsSet(eFeature);
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
      case GenModelPackage.GEN_ENUM__ECORE_DATA_TYPE:
        setEcoreDataType((EDataType)newValue);
        return;
      case GenModelPackage.GEN_ENUM__ECORE_ENUM:
        setEcoreEnum((EEnum)newValue);
        return;
      case GenModelPackage.GEN_ENUM__GEN_ENUM_LITERALS:
        getGenEnumLiterals().clear();
        getGenEnumLiterals().addAll((Collection)newValue);
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
      case GenModelPackage.GEN_ENUM__ECORE_DATA_TYPE:
        setEcoreDataType((EDataType)null);
        return;
      case GenModelPackage.GEN_ENUM__ECORE_ENUM:
        setEcoreEnum((EEnum)null);
        return;
      case GenModelPackage.GEN_ENUM__GEN_ENUM_LITERALS:
        getGenEnumLiterals().clear();
        return;
    }
    eDynamicUnset(eFeature);
  }

  public EClassifier getEcoreClassifier()
  {
    return getEcoreEnum();
  }

  public EDataType getEcoreDataType()
  {
    return getEcoreEnum();
  }

  public String getImportedMetaType()
  {
    return getGenModel().getImportedName("org.eclipse.emf.ecore.EEnum");
  }

  public String getQualifiedName()
  {
    return getGenPackage().getInterfacePackageName() + "." + getName();
  }

  public String getImportedName()
  {
    return getGenModel().getImportedName(getQualifiedName());
  }

/*
  public String getImportedInstanceClassName()
  {
    return getGenModel().getImportedName("org.eclipse.emf.ecore.EEnumLiteral"); 
  }
*/

  public String getEnumLiteralID(GenEnumLiteral genEnumLiteral)
  {
    return getClassifierID() + "__" + genEnumLiteral.getEnumLiteralID();
  }

  public int getEnumLiteralValue(GenEnumLiteral genEnumLiteral)
  {
    return getGenEnumLiterals().indexOf(genEnumLiteral);
  }

  public GenEnumLiteral getGenEnumLiteral(String literalName)
  {
    for (Iterator iter = getGenEnumLiterals().iterator(); iter.hasNext(); )
    {
      GenEnumLiteral genEnumLiteral = (GenEnumLiteral)iter.next();
      if (genEnumLiteral.getName().equals(literalName))
      {
        return genEnumLiteral;
      }
    }
    //FB TBD if (literalName != null || getGenEnumLiterals().isEmpty()) report error?
    return getGenEnumLiterals().isEmpty() ? null : (GenEnumLiteral)getGenEnumLiterals().get(0);
  }

  public List getUniqueValuedGenEnumLiterals()
  {
    List result = new ArrayList();
    Set resultSet = new HashSet();

    for (Iterator iter = getGenEnumLiterals().iterator(); iter.hasNext(); )
    {
      GenEnumLiteral genEnumLiteral = (GenEnumLiteral)iter.next();
      if (resultSet.add(new Integer(genEnumLiteral.getValue())))
      {
        result.add(genEnumLiteral);
      }      
    }
    return result;
  }

  public boolean isSerializable()
  {
    return true;
  }

  public void initialize(EEnum eEnum)
  {
    setEcoreEnum(eEnum);

    LOOP:
    for (Iterator iter = eEnum.getELiterals().iterator(); iter.hasNext(); )
    {
      EEnumLiteral eEnumLiteral = (EEnumLiteral)iter.next();

      for (Iterator j = getGenEnumLiterals().iterator(); j.hasNext(); )
      {
        GenEnumLiteral genEnumLiteral = (GenEnumLiteral)j.next();
        if (genEnumLiteral.getEcoreEnumLiteral() == eEnumLiteral)
        {
          genEnumLiteral.initialize(eEnumLiteral);
          continue LOOP;
        }
      }

      GenEnumLiteral genEnumLiteral = getGenModel().createGenEnumLiteral();
      getGenEnumLiterals().add(genEnumLiteral);
      genEnumLiteral.initialize(eEnumLiteral);
    }
  }

  protected boolean hasModelContribution()
  {
    return true;
  }

  public void generate(IProgressMonitor progressMonitor)
  {
    try
    {
      if (!canGenerate()) return;

      progressMonitor.beginTask("", 1);
      progressMonitor.subTask
        (CodeGenEcorePlugin.INSTANCE.getString
           ("_UI_Generating_message", new Object [] { getFormattedName() }));
      progressMonitor.subTask
        (CodeGenEcorePlugin.INSTANCE.getString
           ("_UI_GeneratingJavaClass_message", new Object [] { getGenPackage().getInterfacePackageName() + "." + getName() }));
      generate
        (new SubProgressMonitor(progressMonitor, 1), 
         Generator.EMF_MODEL_PROJECT_STYLE, 
         getGenModel().getEffectiveModelPluginVariables(),
         getGenModel().getModelDirectory(), 
         getGenPackage().getInterfacePackageName(), 
         getName(), 
         getGenModel().getEnumClassEmitter());
    }
    finally
    {
      progressMonitor.done();
    }
  }

  public boolean canGenerateEdit()
  {
    return false;
  }

  public boolean canGenerateEditor()
  {
    return false;
  }

  public boolean reconcile(GenEnum oldGenEnumVersion)
  {
    if (getEcoreEnum().getName().equals(oldGenEnumVersion.getEcoreEnum().getName()))
    {
      for (Iterator i = getGenEnumLiterals().iterator(); i.hasNext(); )
      {
        GenEnumLiteral genEnumLiteral = (GenEnumLiteral)i.next();
        for (Iterator j = oldGenEnumVersion.getGenEnumLiterals().iterator(); j.hasNext(); )
        {
          GenEnumLiteral oldGenEnumLiteralVersion = (GenEnumLiteral)j.next();
          if (genEnumLiteral.reconcile(oldGenEnumLiteralVersion))
          {
            break;
          }
        }
      }
      reconcileSettings(oldGenEnumVersion);
      return true;
    }
    else
    {
      return false;
    }
  }

  protected void reconcileSettings(GenEnum oldGenEnumVersion)
  {
  }

  public boolean reconcile()
  {
    EEnum eEnum = getEcoreEnum();
    if (eEnum == null || eEnum.eIsProxy() || eEnum.eResource() == null)
    {
      return false;
    }
    else
    {
      for (Iterator i = getGenEnumLiterals().iterator(); i.hasNext(); )
      {
        GenEnumLiteral genEnumLiteral = (GenEnumLiteral)i.next();
        if (!genEnumLiteral.reconcile())
        {
          i.remove();
        }
      }

      return true;
    }
  }

  public String getStaticValue(String literal)
  {
    GenEnumLiteral genEnumLiteral = getGenEnumLiteral(literal);
    return genEnumLiteral == null ? "null" : getImportedName() + "." + genEnumLiteral.getEnumLiteralID() + "_LITERAL";
  }

} //GenEnumImpl
