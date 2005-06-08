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
 * $Id: GenClassifierImpl.java,v 1.5 2005/06/08 06:18:44 nickb Exp $
 */
package org.eclipse.emf.codegen.ecore.genmodel.impl;


import java.util.List;

import org.eclipse.emf.codegen.ecore.genmodel.GenClassifier;
import org.eclipse.emf.codegen.ecore.genmodel.GenModelPackage;
import org.eclipse.emf.codegen.ecore.genmodel.GenPackage;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EModelElement;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.util.EcoreUtil;


/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Gen Meta Object</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.emf.codegen.ecore.genmodel.impl.GenClassifierImpl#getGenPackage <em>Gen Package</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public abstract class GenClassifierImpl extends GenBaseImpl implements GenClassifier
{
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
  protected EClass eStaticClass()
  {
    return GenModelPackage.eINSTANCE.getGenClassifier();
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
  public Object eGet(EStructuralFeature eFeature, boolean resolve)
  {
    switch (eDerivedStructuralFeatureID(eFeature))
    {
      case GenModelPackage.GEN_CLASSIFIER__GEN_PACKAGE:
        return getGenPackage();
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
      case GenModelPackage.GEN_CLASSIFIER__GEN_PACKAGE:
        return getGenPackage() != null;
    }
    return eDynamicIsSet(eFeature);
  }

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

  public String getFormattedName()
  {
    return format(getName(), ' ', getGenPackage().getPrefix(), false);
  }

  public String getClassifierInstanceName()
  {
    return uncapPrefixedName(getName()) + getMetaType();
  }

  public String getClassifierID()
  {
    String name = getName();
    String prefix = getGenPackage().getPrefix();
    return format(name, '_', prefix, true).toUpperCase();
  }

  public List /* of String */ getGenConstraints()
  {
    return EcoreUtil.getConstraints(getEcoreClassifier());
  }

  public List /* of String */ getAllGenConstraints()
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
} 
