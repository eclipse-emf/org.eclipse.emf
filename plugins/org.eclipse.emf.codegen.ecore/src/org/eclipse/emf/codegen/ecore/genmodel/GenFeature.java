/**
 * Copyright (c) 2002-2009 IBM Corporation and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 * 
 * Contributors: 
 *   IBM - Initial API and implementation
 */
package org.eclipse.emf.codegen.ecore.genmodel;


import java.util.List;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EStructuralFeature;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Gen Feature</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.emf.codegen.ecore.genmodel.GenFeature#getProperty <em>Property</em>}</li>
 *   <li>{@link org.eclipse.emf.codegen.ecore.genmodel.GenFeature#isNotify <em>Notify</em>}</li>
 *   <li>{@link org.eclipse.emf.codegen.ecore.genmodel.GenFeature#isChildren <em>Children</em>}</li>
 *   <li>{@link org.eclipse.emf.codegen.ecore.genmodel.GenFeature#isCreateChild <em>Create Child</em>}</li>
 *   <li>{@link org.eclipse.emf.codegen.ecore.genmodel.GenFeature#getPropertyCategory <em>Property Category</em>}</li>
 *   <li>{@link org.eclipse.emf.codegen.ecore.genmodel.GenFeature#getPropertyFilterFlags <em>Property Filter Flags</em>}</li>
 *   <li>{@link org.eclipse.emf.codegen.ecore.genmodel.GenFeature#getPropertyDescription <em>Property Description</em>}</li>
 *   <li>{@link org.eclipse.emf.codegen.ecore.genmodel.GenFeature#isPropertyMultiLine <em>Property Multi Line</em>}</li>
 *   <li>{@link org.eclipse.emf.codegen.ecore.genmodel.GenFeature#isPropertySortChoices <em>Property Sort Choices</em>}</li>
 *   <li>{@link org.eclipse.emf.codegen.ecore.genmodel.GenFeature#getGenClass <em>Gen Class</em>}</li>
 *   <li>{@link org.eclipse.emf.codegen.ecore.genmodel.GenFeature#getEcoreFeature <em>Ecore Feature</em>}</li>
 *   <li>{@link org.eclipse.emf.codegen.ecore.genmodel.GenFeature#isSuppressedGetVisibility <em>Suppressed Get Visibility</em>}</li>
 *   <li>{@link org.eclipse.emf.codegen.ecore.genmodel.GenFeature#isSuppressedSetVisibility <em>Suppressed Set Visibility</em>}</li>
 *   <li>{@link org.eclipse.emf.codegen.ecore.genmodel.GenFeature#isSuppressedIsSetVisibility <em>Suppressed Is Set Visibility</em>}</li>
 *   <li>{@link org.eclipse.emf.codegen.ecore.genmodel.GenFeature#isSuppressedUnsetVisibility <em>Suppressed Unset Visibility</em>}</li>
 *   <li>{@link org.eclipse.emf.codegen.ecore.genmodel.GenFeature#getPropertyEditorFactory <em>Property Editor Factory</em>}</li>
 * </ul>
 *
 * @see org.eclipse.emf.codegen.ecore.genmodel.GenModelPackage#getGenFeature()
 * @model features="get" 
 *        getUnsettable="true" getDataType="org.eclipse.emf.ecore.EString" getSuppressedGetVisibility="true" getSuppressedSetVisibility="true" getSuppressedIsSetVisibility="true" getSuppressedUnsetVisibility="true"
 * @generated
 */
public interface GenFeature extends GenTypedElement
{
  /**
   * Returns the value of the '<em><b>Property</b></em>' attribute.
   * The literals are from the enumeration {@link org.eclipse.emf.codegen.ecore.genmodel.GenPropertyKind}.
   * <!-- begin-user-doc -->
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
   * Returns the value of the '<em><b>Property Category</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the value of the '<em>Property Category</em>' attribute.
   * @see #setPropertyCategory(String)
   * @see org.eclipse.emf.codegen.ecore.genmodel.GenModelPackage#getGenFeature_PropertyCategory()
   * @model
   * @generated
   */
  String getPropertyCategory();

  /**
   * Sets the value of the '{@link org.eclipse.emf.codegen.ecore.genmodel.GenFeature#getPropertyCategory <em>Property Category</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Property Category</em>' attribute.
   * @see #getPropertyCategory()
   * @generated
   */
  void setPropertyCategory(String value);

  /**
   * Returns the value of the '<em><b>Property Filter Flags</b></em>' attribute list.
   * The list contents are of type {@link java.lang.String}.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the value of the '<em>Property Filter Flags</em>' attribute list.
   * @see org.eclipse.emf.codegen.ecore.genmodel.GenModelPackage#getGenFeature_PropertyFilterFlags()
   * @model
   * @generated
   */
  EList<String> getPropertyFilterFlags();

  /**
   * Returns the value of the '<em><b>Property Description</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the value of the '<em>Property Description</em>' attribute.
   * @see #setPropertyDescription(String)
   * @see org.eclipse.emf.codegen.ecore.genmodel.GenModelPackage#getGenFeature_PropertyDescription()
   * @model
   * @generated
   */
  String getPropertyDescription();

  /**
   * Sets the value of the '{@link org.eclipse.emf.codegen.ecore.genmodel.GenFeature#getPropertyDescription <em>Property Description</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Property Description</em>' attribute.
   * @see #getPropertyDescription()
   * @generated
   */
  void setPropertyDescription(String value);

  /**
   * Returns the value of the '<em><b>Property Multi Line</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the value of the '<em>Property Multi Line</em>' attribute.
   * @see #setPropertyMultiLine(boolean)
   * @see org.eclipse.emf.codegen.ecore.genmodel.GenModelPackage#getGenFeature_PropertyMultiLine()
   * @model
   * @generated
   */
  boolean isPropertyMultiLine();

  /**
   * Sets the value of the '{@link org.eclipse.emf.codegen.ecore.genmodel.GenFeature#isPropertyMultiLine <em>Property Multi Line</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Property Multi Line</em>' attribute.
   * @see #isPropertyMultiLine()
   * @generated
   */
  void setPropertyMultiLine(boolean value);

  /**
   * Returns the value of the '<em><b>Property Sort Choices</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the value of the '<em>Property Sort Choices</em>' attribute.
   * @see #setPropertySortChoices(boolean)
   * @see org.eclipse.emf.codegen.ecore.genmodel.GenModelPackage#getGenFeature_PropertySortChoices()
   * @model
   * @generated
   */
  boolean isPropertySortChoices();

  /**
   * Sets the value of the '{@link org.eclipse.emf.codegen.ecore.genmodel.GenFeature#isPropertySortChoices <em>Property Sort Choices</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Property Sort Choices</em>' attribute.
   * @see #isPropertySortChoices()
   * @generated
   */
  void setPropertySortChoices(boolean value);

  /**
   * Returns the value of the '<em><b>Gen Class</b></em>' container reference.
   * It is bidirectional and its opposite is '{@link org.eclipse.emf.codegen.ecore.genmodel.GenClass#getGenFeatures <em>Gen Features</em>}'.
   * <!-- begin-user-doc -->
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

  /**
   * The expression is assumed to be of the type of this feature.  
   * This will return that expression cast to InternalEObject, if necessary, i.e., if interfaces are not suppressed,
   * and with additional parentheses around the cast of the expression.
   * @since 2.17
   */
  String getAsInternalEObject(String expression, boolean parenthesize); 

  String getCapName();
  String getUncapName();
  String getUpperName();
  String getFormattedName(); // This returns a name like employeeNumber formatted like 'Employee Number'
  String getIsName(); // This returns the same as getCapName unless it starts with "is" in which case the "is" is stripped 
  String getAccessorName(); // This returns the name to be used in accessor method names; it's the same as getCapName, unless this is the key or value of a map entry, for which "Typed" is prepended
  String getGetArrayAccessor(); // This returns the name of the get array-based accessor method for the list feature.
  String getGetAccessor(); // This returns the name of the get accessor method for the feature, i.e., either "is" or "get" is prefixed to getAccessorName

  GenPackage getGenPackage(); // returns the package in which this feature is defined
  String getFeatureAccessorName(); // returns the name of the feature get method in the package interface (e.g. Company_Name)
  String getQualifiedFeatureAccessorName(); // returns the name of the feature get method in the package interface (e.g. Xyz.eINSTANCE.getCompany_Name)
  String getQualifiedFeatureAccessor();
  
  String getMetaType(); // this returns either "EAttribute" or "EReference"
  String getImportedMetaType();
  String getFeatureKind(); // this returns either "attribute", "attribute list", "reference", or "reference list"
  boolean isReferenceType();

  /**
   * This method isn't terribly useful now that an enum attribute could be considered a flag, but should only be
   * generated as such if {@link GenModel#isPackedEnums packed enums} is enabled for the model. This method still
   * returns false in that case.
   * @deprecated use {@link GenClass#isFlag} to correctly determine whether a feature is a flag
   */
  @Deprecated boolean isFlag();
  boolean isESetFlag();

  boolean isSetDefaultValue();
  String getDefaultValue();
  String getStaticDefaultValue();

  /**
   * @since 2.37
   */
  boolean isStaticDefaultValueUncheckedCast();

  boolean isContainer();
  boolean isContains();
  boolean isEffectiveContains();
  boolean isBidirectional();
  GenFeature getReverse();
  
  List<GenFeature> getKeys();

  String getContainerClass();

  String getDerivedFlag();
  String getTransientFlag();
  String getVolatileFlag();
  String getChangeableFlag();
  String getUnsettableFlag();
  String getIDFlag();
  String getContainmentFlag();
  String getResolveProxiesFlag();

  boolean isVolatile();
  boolean isChangeable();
  boolean isUnsettable();
  boolean isID();
  boolean isDerived();
  boolean isResolveProxies();

  boolean hasDelegateFeature();
  GenFeature getDelegateFeature();

  List<GenFeature> getDelegatedFeatures();

  /**
   * @since 2.4
   */
  List<GenFeature> getDelegatedFeatures(GenModel genModel);

  String getCreateChildValueLiteral();

  boolean isSuppressedGetVisibility();

  /**
   * Sets the value of the '{@link org.eclipse.emf.codegen.ecore.genmodel.GenFeature#isSuppressedGetVisibility <em>Suppressed Get Visibility</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Suppressed Get Visibility</em>' attribute.
   * @see #isSuppressedGetVisibility()
   * @since 2.14
   * @generated
   */
  void setSuppressedGetVisibility(boolean value);

  boolean isSuppressedSetVisibility();

  /**
   * Sets the value of the '{@link org.eclipse.emf.codegen.ecore.genmodel.GenFeature#isSuppressedSetVisibility <em>Suppressed Set Visibility</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Suppressed Set Visibility</em>' attribute.
   * @see #isSuppressedSetVisibility()
   * @since 2.14
   * @generated
   */
  void setSuppressedSetVisibility(boolean value);

  boolean isSuppressedIsSetVisibility();

  /**
   * Sets the value of the '{@link org.eclipse.emf.codegen.ecore.genmodel.GenFeature#isSuppressedIsSetVisibility <em>Suppressed Is Set Visibility</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Suppressed Is Set Visibility</em>' attribute.
   * @see #isSuppressedIsSetVisibility()
   * @since 2.14
   * @generated
   */
  void setSuppressedIsSetVisibility(boolean value);

  boolean isSuppressedUnsetVisibility();

  /**
   * Sets the value of the '{@link org.eclipse.emf.codegen.ecore.genmodel.GenFeature#isSuppressedUnsetVisibility <em>Suppressed Unset Visibility</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Suppressed Unset Visibility</em>' attribute.
   * @see #isSuppressedUnsetVisibility()
   * @since 2.14
   * @generated
   */
  void setSuppressedUnsetVisibility(boolean value);

  /**
   * Returns the value of the '<em><b>Property Editor Factory</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * <!-- begin-model-doc -->
   * @since 2.14
   * <!-- end-model-doc -->
   * @return the value of the '<em>Property Editor Factory</em>' attribute.
   * @see #setPropertyEditorFactory(String)
   * @see org.eclipse.emf.codegen.ecore.genmodel.GenModelPackage#getGenFeature_PropertyEditorFactory()
   * @model unsettable="true" dataType="org.eclipse.emf.codegen.ecore.genmodel.PropertyEditorFactory" suppressedIsSetVisibility="true" suppressedUnsetVisibility="true"
   * @generated
   */
  String getPropertyEditorFactory();

  /**
   * Sets the value of the '{@link org.eclipse.emf.codegen.ecore.genmodel.GenFeature#getPropertyEditorFactory <em>Property Editor Factory</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Property Editor Factory</em>' attribute.
   * @see #getPropertyEditorFactory()
   * @since 2.14
   * @generated
   */
  void setPropertyEditorFactory(String value);

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
  
  /**
   * @since 2.3
   */
  boolean hasEDefault();

  /**
   * @since 2.3
   */
  String getEDefault();

  /**
   * Returns whether the test case will test this feature.
   * @since 2.3
   */
  boolean isTested();
  
  /**
   * Returns whether this feature is implemented with a registered setting delegate.
   * @since 2.6
   */
  boolean hasSettingDelegate();

  /**
   * @since 2.8
   */
  boolean hasGetterBody();

  /**
   * @since 2.8
   */
  String getGetterBody(String indentation);
}
