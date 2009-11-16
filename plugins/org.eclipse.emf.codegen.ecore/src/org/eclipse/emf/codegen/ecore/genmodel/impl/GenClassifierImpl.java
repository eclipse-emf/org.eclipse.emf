/**
 * <copyright> 
 *
 * Copyright (c) 2002-2009 IBM Corporation and others.
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
 * $Id: GenClassifierImpl.java,v 1.16 2009/11/16 19:26:46 khussey Exp $
 */
package org.eclipse.emf.codegen.ecore.genmodel.impl;


import java.util.Collection;
import java.util.List;

import org.eclipse.emf.codegen.ecore.genmodel.GenClassifier;
import org.eclipse.emf.codegen.ecore.genmodel.GenJDKLevel;
import org.eclipse.emf.codegen.ecore.genmodel.GenModelPackage;
import org.eclipse.emf.codegen.ecore.genmodel.GenPackage;
import org.eclipse.emf.codegen.ecore.genmodel.GenRuntimeVersion;
import org.eclipse.emf.codegen.ecore.genmodel.GenTypeParameter;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EModelElement;
import org.eclipse.emf.ecore.util.EcoreUtil;


/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Gen Meta Object</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.emf.codegen.ecore.genmodel.impl.GenClassifierImpl#getGenPackage <em>Gen Package</em>}</li>
 *   <li>{@link org.eclipse.emf.codegen.ecore.genmodel.impl.GenClassifierImpl#getGenTypeParameters <em>Gen Type Parameters</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public abstract class GenClassifierImpl extends GenBaseImpl implements GenClassifier
{
  /**
   * The cached value of the '{@link #getGenTypeParameters() <em>Gen Type Parameters</em>}' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getGenTypeParameters()
   * @generated
   * @ordered
   */
  protected EList<GenTypeParameter> genTypeParameters;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated modifiable
   */
  protected GenClassifierImpl() 
  {
    super();
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  protected EClass eStaticClass()
  {
    return GenModelPackage.Literals.GEN_CLASSIFIER;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated modifiable
   */
  public GenPackage getGenPackage()
  {
    return (GenPackage)eContainer();
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EList<GenTypeParameter> getGenTypeParameters()
  {
    if (genTypeParameters == null)
    {
      genTypeParameters = new EObjectContainmentEList<GenTypeParameter>(GenTypeParameter.class, this, GenModelPackage.GEN_CLASSIFIER__GEN_TYPE_PARAMETERS);
    }
    return genTypeParameters;
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
      case GenModelPackage.GEN_CLASSIFIER__GEN_TYPE_PARAMETERS:
        return ((InternalEList<?>)getGenTypeParameters()).basicRemove(otherEnd, msgs);
    }
    return super.eInverseRemove(otherEnd, featureID, msgs);
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
      case GenModelPackage.GEN_CLASSIFIER__GEN_PACKAGE:
        return getGenPackage();
      case GenModelPackage.GEN_CLASSIFIER__GEN_TYPE_PARAMETERS:
        return getGenTypeParameters();
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
      case GenModelPackage.GEN_CLASSIFIER__GEN_TYPE_PARAMETERS:
        getGenTypeParameters().clear();
        getGenTypeParameters().addAll((Collection<? extends GenTypeParameter>)newValue);
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
      case GenModelPackage.GEN_CLASSIFIER__GEN_TYPE_PARAMETERS:
        getGenTypeParameters().clear();
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
      case GenModelPackage.GEN_CLASSIFIER__GEN_PACKAGE:
        return getGenPackage() != null;
      case GenModelPackage.GEN_CLASSIFIER__GEN_TYPE_PARAMETERS:
        return genTypeParameters != null && !genTypeParameters.isEmpty();
    }
    return super.eIsSet(featureID);
  }

  @Override
  public EModelElement getEcoreModelElement()
  {
    return getEcoreClassifier();
  }

  public abstract EClassifier getEcoreClassifier();

  public abstract String getImportedMetaType();

  public String getMetaType()
  {
    String importedName = getImportedMetaType();
    return importedName.substring(importedName.lastIndexOf(".") + 1);
  }

  @Override
  public String getName()
  {
    return getEcoreClassifier().getName();
  }

  public String getUncapName()
  {
    return uncapPrefixedName(getName());
  }

  public String getSafeUncapName()
  {
    return safeName(getUncapName());
  }

  public String getClassifierAccessorName()
  {
    String result = getEcoreClassifier().getName();
    if ("Class".equals(result) || "Name".equals(result))
    {
      result += "_";
    }
    return result;
  }

  public String getQualifiedClassifierAccessor()
  {
    return getGenPackage().isLiteralsInterface()
      ? getGenPackage().getImportedPackageInterfaceName() + ".Literals." + getClassifierID()
      : getGenPackage().getImportedPackageInterfaceName() + ".eINSTANCE.get" + getClassifierAccessorName() + "()";
  }

  public String getFormattedName()
  {
    return format(getName(), ' ', getGenPackage().getPrefix(), false, false);
  }

  public String getClassifierInstanceName()
  {
    return uncapPrefixedName(getName()) + getMetaType();
  }

  public String getClassifierID()
  {
    String name = getName();
    String prefix = getGenPackage().getPrefix();
    return format(name, '_', prefix, true, true).toUpperCase(getGenModel().getLocale());
  }

  public List<String> getGenConstraints()
  {
    return EcoreUtil.getConstraints(getEcoreClassifier());
  }

  public List<String> getAllGenConstraints()
  {
    return getGenConstraints();
  }

  public GenClassifier getConstraintImplementor(String constraint)
  {
    return this;
  }

  public boolean hasOnlyDefaultConstraints()
  {
    return false;
  }

  public boolean isUncheckedCast()
  {
    return 
      getEffectiveComplianceLevel().getValue() >= GenJDKLevel.JDK50 && 
        getEcoreClassifier().getInstanceTypeName() != null && 
        getEcoreClassifier().getInstanceTypeName().indexOf('<') != -1;
  }

  public String getImportedParameterizedInstanceClassName()
  {
    return getImportedInstanceClassName();
  }

  public String getImportedWildcardInstanceClassName()
  {
    return getImportedInstanceClassName();
  }

  public String getImportedBoundedWildcardInstanceClassName()
  {
    return getImportedInstanceClassName();
  }

  protected String getConstraintExpression(String constraint)
  {
    for (String validationDelegate : EcoreUtil.getValidationDelegates(getGenPackage().getEcorePackage()))
    {
      String expression = EcoreUtil.getAnnotation(getEcoreClassifier(), validationDelegate, constraint);
      if (expression != null)
      {
        return expression;
      }
    }
    return null;
  }

  public boolean hasConstraintExpression(String constraint)
  {
    return getGenModel().getRuntimeVersion().getValue() >= GenRuntimeVersion.EMF26_VALUE && getConstraintExpression(constraint) != null;
  }

  public String getConstraintExpression(String constraint, String indentation)
  {
    return indent(getConstraintExpression(constraint), indentation);
  }

  public String getValidationDelegate(String constraint)
  {
    for (String validationDelegate : EcoreUtil.getValidationDelegates(getGenPackage().getEcorePackage()))
    {
      String expression = EcoreUtil.getAnnotation(getEcoreClassifier(), validationDelegate, constraint);
      if (expression != null)
      {
        return validationDelegate;
      }
    }
    return null;
  }
}
