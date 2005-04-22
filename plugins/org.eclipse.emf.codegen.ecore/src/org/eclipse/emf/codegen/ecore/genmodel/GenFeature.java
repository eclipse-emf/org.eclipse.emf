/**
 * <copyright> 
 *
 * Copyright (c) 2002-2005 IBM Corporation and others.
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
 * $Id: GenFeature.java,v 1.10 2005/04/22 19:46:37 khussey Exp $
 */
package org.eclipse.emf.codegen.ecore.genmodel;


import org.eclipse.emf.common.util.EList;

import java.util.List;

import org.eclipse.emf.ecore.EStructuralFeature;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Gen Feature</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.emf.codegen.ecore.genmodel.GenFeature#getProperty <em>Property</em>}</li>
 *   <li>{@link org.eclipse.emf.codegen.ecore.genmodel.GenFeature#isNotify <em>Notify</em>}</li>
 *   <li>{@link org.eclipse.emf.codegen.ecore.genmodel.GenFeature#isChildren <em>Children</em>}</li>
 *   <li>{@link org.eclipse.emf.codegen.ecore.genmodel.GenFeature#isCreateChild <em>Create Child</em>}</li>
 *   <li>{@link org.eclipse.emf.codegen.ecore.genmodel.GenFeature#getCategory <em>Category</em>}</li>
 *   <li>{@link org.eclipse.emf.codegen.ecore.genmodel.GenFeature#getFilterFlags <em>Filter Flags</em>}</li>
 *   <li>{@link org.eclipse.emf.codegen.ecore.genmodel.GenFeature#getGenClass <em>Gen Class</em>}</li>
 *   <li>{@link org.eclipse.emf.codegen.ecore.genmodel.GenFeature#getEcoreFeature <em>Ecore Feature</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.emf.codegen.ecore.genmodel.GenModelPackage#getGenFeature()
 * @model
 * @generated
 */
public interface GenFeature extends GenBase{
  /**
   * Returns the value of the '<em><b>Property</b></em>' attribute.
   * The literals are from the enumeration {@link org.eclipse.emf.codegen.ecore.genmodel.GenPropertyKind}.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Property</em>' attribute isn't clear, 
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Property</em>' attribute.
   * @see org.eclipse.emf.codegen.ecore.genmodel.GenPropertyKind
   * @see #setProperty(GenPropertyKind)
   * @see org.eclipse.emf.codegen.ecore.genmodel.GenModelPackage#getGenFeature_Property()
   * @model
   * @generated
   */
  GenPropertyKind getProperty();

  /**
   * Sets the value of the '{@link org.eclipse.emf.codegen.ecore.genmodel.GenFeature#getProperty <em>Property</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Property</em>' attribute.
   * @see org.eclipse.emf.codegen.ecore.genmodel.GenPropertyKind
   * @see #getProperty()
   * @generated
   */
  void setProperty(GenPropertyKind value);

  /**
   * Returns the value of the '<em><b>Notify</b></em>' attribute.
   * The default value is <code>"true"</code>.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the value of the '<em>Notify</em>' attribute.
   * @see #setNotify(boolean)
   * @see org.eclipse.emf.codegen.ecore.genmodel.GenModelPackage#getGenFeature_Notify()
   * @model default="true"
   * @generated
   */
  boolean isNotify();

  /**
   * Sets the value of the '{@link org.eclipse.emf.codegen.ecore.genmodel.GenFeature#isNotify <em>Notify</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Notify</em>' attribute.
   * @see #isNotify()
   * @generated
   */
  void setNotify(boolean value);

  /**
   * Returns the value of the '<em><b>Children</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the value of the '<em>Children</em>' attribute.
   * @see #setChildren(boolean)
   * @see org.eclipse.emf.codegen.ecore.genmodel.GenModelPackage#getGenFeature_Children()
   * @model
   * @generated
   */
  boolean isChildren();

  /**
   * Sets the value of the '{@link org.eclipse.emf.codegen.ecore.genmodel.GenFeature#isChildren <em>Children</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Children</em>' attribute.
   * @see #isChildren()
   * @generated
   */
  void setChildren(boolean value);

  /**
   * Returns the value of the '<em><b>Create Child</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <p>
   * This attribute determines whether child objects can be created via this feature. It is temporarily unsettable to
   * ease migration from pre-2.0 versions of EMF, in which the {@link #isChildren children} feature did this as a
   * secondary role.
   * </p>
   * <p>
   * If the feature has not yet been set, it will be set based on the value of the {@link #isChildren children}
   * feature, before its value is returned. This ensures that pre-2.0 models will upgrade themselves, and continue to
   * work as before.
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Create Child</em>' attribute.
   * @see #isSetCreateChild()
   * @see #unsetCreateChild()
   * @see #setCreateChild(boolean)
   * @see org.eclipse.emf.codegen.ecore.genmodel.GenModelPackage#getGenFeature_CreateChild()
   * @model unsettable="true"
   * @generated
   */
  boolean isCreateChild();

  /**
   * Sets the value of the '{@link org.eclipse.emf.codegen.ecore.genmodel.GenFeature#isCreateChild <em>Create Child</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Create Child</em>' attribute.
   * @see #isSetCreateChild()
   * @see #unsetCreateChild()
   * @see #isCreateChild()
   * @generated
   */
  void setCreateChild(boolean value);

  /**
   * Unsets the value of the '{@link org.eclipse.emf.codegen.ecore.genmodel.GenFeature#isCreateChild <em>Create Child</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #isSetCreateChild()
   * @see #isCreateChild()
   * @see #setCreateChild(boolean)
   * @generated
   */
  void unsetCreateChild();

  /**
   * Returns whether the value of the '{@link org.eclipse.emf.codegen.ecore.genmodel.GenFeature#isCreateChild <em>Create Child</em>}' attribute is set.
   * <!-- begin-user-doc -->
   * <p>
   * This always returns true.  If the feature is unset, it will first be set based on the value of {@link #isChildren
   * children}. This ensures that pre-2.0 models will upgrade themselves, and continue to behave as before
   * </p>
   * <!-- end-user-doc -->
   * @return whether the value of the '<em>Create Child</em>' attribute is set.
   * @see #unsetCreateChild()
   * @see #isCreateChild()
   * @see #setCreateChild(boolean)
   * @generated
   */
  boolean isSetCreateChild();

  /**
   * Returns the value of the '<em><b>Category</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Category</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Category</em>' attribute.
   * @see #setCategory(String)
   * @see org.eclipse.emf.codegen.ecore.genmodel.GenModelPackage#getGenFeature_Category()
   * @model
   * @generated
   */
  String getCategory();

  /**
   * Sets the value of the '{@link org.eclipse.emf.codegen.ecore.genmodel.GenFeature#getCategory <em>Category</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Category</em>' attribute.
   * @see #getCategory()
   * @generated
   */
  void setCategory(String value);

  /**
   * Returns the value of the '<em><b>Filter Flags</b></em>' attribute list.
   * The list contents are of type {@link java.lang.String}.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Filter Flags</em>' attribute list isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Filter Flags</em>' attribute list.
   * @see org.eclipse.emf.codegen.ecore.genmodel.GenModelPackage#getGenFeature_FilterFlags()
   * @model type="java.lang.String"
   * @generated
   */
  EList getFilterFlags();

  /**
   * Returns the value of the '<em><b>Gen Class</b></em>' container reference.
   * It is bidirectional and its opposite is '{@link org.eclipse.emf.codegen.ecore.genmodel.GenClass#getGenFeatures <em>Gen Features</em>}'.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Gen Class</em>' reference isn't clear, 
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Gen Class</em>' container reference.
   * @see #setGenClass(GenClass)
   * @see org.eclipse.emf.codegen.ecore.genmodel.GenModelPackage#getGenFeature_GenClass()
   * @see org.eclipse.emf.codegen.ecore.genmodel.GenClass#getGenFeatures
   * @model opposite="genFeatures" required="true"
   * @generated
   */
  GenClass getGenClass();

  /**
   * Sets the value of the '{@link org.eclipse.emf.codegen.ecore.genmodel.GenFeature#getGenClass <em>Gen Class</em>}' container reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Gen Class</em>' container reference.
   * @see #getGenClass()
   * @generated
   */
  void setGenClass(GenClass value);

  /**
   * Returns the value of the '<em><b>Ecore Feature</b></em>' reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Ecore Feature</em>' reference isn't clear, 
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Ecore Feature</em>' reference.
   * @see #setEcoreFeature(EStructuralFeature)
   * @see org.eclipse.emf.codegen.ecore.genmodel.GenModelPackage#getGenFeature_EcoreFeature()
   * @model required="true"
   * @generated
   */
  EStructuralFeature getEcoreFeature();

  /**
   * Sets the value of the '{@link org.eclipse.emf.codegen.ecore.genmodel.GenFeature#getEcoreFeature <em>Ecore Feature</em>}' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Ecore Feature</em>' reference.
   * @see #getEcoreFeature()
   * @generated
   */
  void setEcoreFeature(EStructuralFeature value);

  String getName(); // callers that plan to use this name without appending a suffix should call f.safeName(f.getName()) to make sure its not a keyword
  String getSafeName(); // this is a safe guaranteed uncapitalized form of the name
  String getSafeNameAsEObject(); // the safe name and cast to EObject, if necessary.
  String getCapName();
  String getUncapName();
  String getUpperName();
  String getFormattedName(); // This returns a name like employeeNumber formatted like 'Employee Number'
  String getIsName(); // This returns the same as getCapName unless it starts with "is" in which case the "is" is stripped 
  String getAccessorName(); // This returns the name to be used in accessor method names; it's the same as getCapName, unless this is the key or value of a map entry, for which "Typed" is prepended
  String getGetAccessor(); // This returns the name of the get accessor method for the feature, i.e., either "is" or "get" is prefixed to getAccessorName

  GenPackage getGenPackage(); // returns the package in which this feature is defined
  String getFeatureAccessorName(); // returns the name of the feature get method in the package interface (e.g. Company_Name)
  String getQualifiedFeatureAccessorName(); // returns the name of the feature get method in the package interface (e.g. Xyz.eINSTANCE.getCompany_Name)

  String getMetaType(); // this returns either "EAttribute" or "EReference"
  String getImportedMetaType();
  String getFeatureKind(); // this returns either "attribute", "attribute list", "reference", or "reference list"
  boolean isReferenceType();
  String getEObjectCast(); // this returns either "(EObject)", "(org.eclipse.emf.ecore.EObject)", or "" as needed for the reference type.
  String getInternalTypeCast(); // this returns either "(EObject)", "(org.eclipse.emf.ecore.EObject)", or "" as needed for the reference type.
  boolean isEffectiveSuppressEMFTypes();
  boolean isFlag();
  boolean isESetFlag();
  
  String getTypeClassifier();
  GenPackage getTypeGenPackage();

  String getType(); // this returns the feature type, either primitive (e.g. "int") or qualified class name (e.g. "java.lang.String", "org.eclipse.emf.common.util.EList", or "org.sample.company.Company")

  String getImportedType(); // this returns the feature type, either primitive (e.g. "int") or imported class name (e.g. "String", "EList", or "Company")
  String getObjectType(); // this returns the imported name of the feature type or, if primitive, of the wrapper class (e.g. "Integer")
  String getImportedInternalType(); // this returns the real imported feature type used internally, regardless of the value of GenModel.isSuppressEMFValues()
  String getQualifiedListItemType(); // this returns the qualified name of the feature type/wrapper class, or if a multi-valued feature, the type of the EList members
  String getListItemType(); // this returns the imported name of the feature type/wrapper class, or if a multi-valued feature, the type of the EList members
  GenClass getMapGenClass(); 
  String getMapItemType(); 
  String getImportedEffectiveFeatureMapWrapperClass();
  String getImportedEffectiveFeatureMapWrapperInternalInterface();

  boolean isSetDefaultValue();
  String getDefaultValue();
  String getStaticDefaultValue();

  boolean isEnumType();
  boolean isEnumBasedType();
  GenEnum getGenEnumType();
  GenDataType getGenDataTypeType();
  boolean isBooleanType();
  boolean isStringType();
  boolean isStringBasedType();
  boolean isListType();
  boolean isMapType();
  boolean isFeatureMapType();
  boolean isFeatureMapWrapped();

  boolean isContainer();
  boolean isContains();
  boolean isBidirectional();
  GenFeature getReverse();

  boolean isPrimitiveType();
  String getPrimitiveValueFunction();

  String getLowerBound();
  String getUpperBound();

  String getContainerClass();

  String getOrderedFlag();
  String getDerivedFlag();
  String getTransientFlag();
  String getVolatileFlag();
  String getChangeableFlag();
  String getUnsettableFlag();
  String getUniqueFlag();
  String getIDFlag();
  String getContainmentFlag();
  String getResolveProxiesFlag();

  boolean isVolatile();
  boolean isChangeable();
  boolean isUnsettable();
  boolean isID();
  boolean isUnique();
  boolean isDerived();
  boolean isResolveProxies();

  boolean hasDelegateFeature();
  GenFeature getDelegateFeature();

  List/*of GenFeature*/ getDelegatedFeatures();

  String getCreateChildValueLiteral();

  boolean isSuppressedGetVisibility();
  boolean isSuppressedSetVisibility();
  boolean isSuppressedIsSetVisibility();
  boolean isSuppressedUnsetVisibility();
  
  void initialize(EStructuralFeature eFeature);

  //
  // EMFEdit generation
  //

  String getPropertyImageName();
  String getModelInfo();
  String getQualifiedModelInfo();
  boolean isProperty();

  boolean reconcile(GenFeature oldGenFeatureVersion);
  
  boolean isField();
  boolean isESetField();
  boolean isGet();
  boolean isBasicGet();
  boolean isBasicSet();
  boolean isSet();
  boolean isBasicUnset();
  boolean isUnset();
  boolean isIsSet();

}
