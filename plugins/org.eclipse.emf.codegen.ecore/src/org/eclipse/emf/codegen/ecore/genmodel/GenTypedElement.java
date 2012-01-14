/**
 * Copyright (c) 2002-2006 IBM Corporation and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: 
 *   IBM - Initial API and implementation
 */
package org.eclipse.emf.codegen.ecore.genmodel;

import org.eclipse.emf.ecore.EModelElement;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Gen Typed Element</b></em>'.
 * <!-- end-user-doc -->
 *
 *
 * @see org.eclipse.emf.codegen.ecore.genmodel.GenModelPackage#getGenTypedElement()
 * @model abstract="true"
 * @generated
 */
public interface GenTypedElement extends GenBase
{
  EModelElement getEcoreModelElement();

  GenPackage getTypeGenPackage();
  String getTypeClassifierAccessorName();

  /**
   * @deprecated As of EMF 2.1, replaced by {@link #getTypeClassifierAccessorName}. 
   */
  @Deprecated
  String getTypeClassifier();

  /**
   * @since 2.3
   */
  boolean hasGenericType();

  /**
   * @since 2.3
   */
  String getRawType();

  /**
   * This returns the feature type as for getRawType, except for the case that the type is a type parameter, in which case it returns the actual bounding type.
   * @since 2.3
   */
  String getRawBoundType(); 

  /**
   * This returns the feature type, either primitive (e.g. "int") or qualified class name (e.g. "java.lang.String", "org.eclipse.emf.common.util.EList", or "org.sample.company.Company")
   * @since 2.3
   */
  String getType(GenClass context); 

  /**
   * Use {@link #getType(GenClass)} with either <code>null</code> for erasing type parameter references 
   * or a {@link GenClass} context representing potential type substitutions for type parameter references.
   * By default, this will just do <code>getType(getGenClass())</code>.
   * @see #getType(GenClass)
   * @deprecated
   */
  @Deprecated
  String getType(); 

  /**
   * @since 2.3
   */
  String getRawImportedType();

  /**
   * This returns the feature type as for getRawImportedType, except for the case that the type is a type parameter, in which case it returns the actual bounding type.
   * @since 2.3
   */
  String getRawImportedBoundType(); 

  /**
   * This returns the feature type, either primitive (e.g. "int") or imported class name (e.g. "String", "EList", or "Company").
   * @since 2.3
   */
  String getImportedType(GenClass context); 

  /**
   * Use {@link #getImportedType(GenClass)} with either <code>null</code> for erasing type parameter references 
   * or a {@link GenClass} context representing potential type substitutions for type parameter references.
   * By default, this will just do <code>getImportedType(getGenClass())</code>.
   * @see #getImportedType(GenClass)
   * @deprecated
   */
  @Deprecated
  String getImportedType();

  /**
   * This returns the imported name of the feature type or, if primitive, of the wrapper class (e.g. "Integer").
   * @since 2.3
   */
  String getObjectType(GenClass context); 

  /**
   * Use {@link #getObjectType(GenClass)} with either <code>null</code> for erasing type parameter references 
   * or a {@link GenClass} context representing potential type substitutions for type parameter references.
   * By default, this will just do <code>getObjectType(getGenClass())</code>.
   * @see #getObjectType(GenClass)
   * @deprecated
   */
  @Deprecated 
  String getObjectType(); 

  /**
   * This returns the real imported feature type used internally, regardless of the value of GenModel.isSuppressEMFValues().
   * @since 2.3
   */
  String getImportedInternalType(GenClass context); 

  /**
   * Use {@link #getImportedInternalType(GenClass)} with either <code>null</code> for erasing type parameter references 
   * or a {@link GenClass} context representing potential type substitutions for type parameter references.
   * By default, this will just do <code>getImportedInternalType(getGenClass())</code>.
   * @see #getImportedInternalType(GenClass)
   * @deprecated
   */
  @Deprecated 
  String getImportedInternalType();

  boolean isFeatureMapType();
  boolean isWrappedFeatureMapType();

  /**
   * @deprecated As of EMF 2.1, replaced by {@link #isWrappedFeatureMapType}. 
   */
  @Deprecated
  boolean isFeatureMapWrapped();

  boolean isEffectiveSuppressEMFTypes();
  /**
   * @since 2.3
   */
  GenJDKLevel getEffectiveComplianceLevel();

  String getImportedEffectiveFeatureMapWrapperInternalInterface();
  String getImportedEffectiveFeatureMapWrapperClass();

  boolean isListType();

  /**
   * @since 2.3
   */
  boolean isListDataType();

  /**
   * Returns either "" or the getListItemType surrounded by <>, depending on the effective compliance level.
   * @since 2.3
   */
  String getListTemplateArguments(GenClass context); 

  /**
   * This returns the raw imported name of the feature type/wrapper class, or if a multi-valued feature, the type of the EList members.
   * @since 2.3
   */
  String getRawListItemType(); 

  /**
   * This returns the imported name of the feature type/wrapper class, or if a multi-valued feature, the type of the EList members
   * @since 2.3
   */
  String getListItemType(GenClass context); 

  /**
   * Use {@link #getListItemType(GenClass)} with either <code>null</code> for erasing type parameter references 
   * or a {@link GenClass} context representing potential type substitutions for type parameter references.
   * By default, this will just do <code>getListItemType(getGenClass())</code>.
   * @see #getListItemType(GenClass)
   * @deprecated
   */
  @Deprecated 
  String getListItemType();

  /**
   * This returns the qualified name of the feature type/wrapper class, or if a multi-valued feature, the type of the EList members.
   * @since 2.3
   */
  String getQualifiedListItemType(GenClass context); 
  
  /**
   * Use {@link #getQualifiedListItemType(GenClass)} with either <code>null</code> for erasing type parameter references 
   * or a {@link GenClass} context representing potential type substitutions for type parameter references.
   * By default, this will just do <code>getQualifiedListItemType(getGenClass())</code>.
   * @see #getQualifiedListItemType(GenClass)
   * @deprecated
   */
  @Deprecated 
  String getQualifiedListItemType();

  boolean isMapType();
  GenClass getMapEntryTypeGenClass(); 
  String getImportedMapEntryType(); 

  /**
   * @since 2.3
   */
  String getImportedMapKeyType(GenClass context);

  /**
   * @since 2.3
   */
  String getImportedMapValueType(GenClass context);

  /**
   * @since 2.3
   */
  String getImportedMapTemplateArguments(GenClass context);

  /**
   * @deprecated As of EMF 2.1, replaced by {@link #getMapEntryTypeGenClass}.
   */
  @Deprecated
  GenClass getMapGenClass(); 

  /**
   * @deprecated As of EMF 2.1, replaced by {@link #getImportedMapEntryType}.
   */
  @Deprecated
  String getMapItemType(); 

  /**
   * @since 2.3
   */
  boolean isUncheckedCast();

  /**
   * @since 2.4
   */
  boolean isUncheckedCast(GenClass context);

  /**
   * This returns either "(EObject)", "(org.eclipse.emf.ecore.EObject)", or "" as needed for the reference type.
   */
  String getEObjectCast(); 

  /**
   * This returns either "(EObject)", "(org.eclipse.emf.ecore.EObject)", or "" as needed for the reference type.
   */
  String getInternalTypeCast(); 

  /**
   * This returns either a cast to the internal type, or "" if it is EObject.
   * @since 2.3
   */
  String getNonEObjectInternalTypeCast(GenClass context); 

  /**
   * Use {@link #getNonEObjectInternalTypeCast(GenClass)} with either <code>null</code> for erasing type parameter references 
   * or a {@link GenClass} context representing potential type substitutions for type parameter references.
   * By default, this will just do <code>getNonEObjectInternalTypeCast(getGenClass())</code>.
   * @see #getNonEObjectInternalTypeCast(GenClass)
   * @deprecated
   */
  @Deprecated 
  String getNonEObjectInternalTypeCast();

  /**
   * This returns that case to the actual type if it's different from the raw type.
   * @since 2.3
   */
  String getRawTypeCast(); 

  boolean isPrimitiveType();
  String getPrimitiveValueFunction();

  boolean isBooleanType();
  boolean isStringType();
  boolean isStringBasedType();

  boolean isEnumType();
  boolean isEnumBasedType();
  GenEnum getTypeGenEnum();
  GenDataType getTypeGenDataType();
  GenClass getTypeGenClass();
  GenClassifier getTypeGenClassifier();

  /**
   * @deprecated As of EMF 2.1, replaced by {@link #getTypeGenEnum}.
   */
  @Deprecated
  GenEnum getGenEnumType();

  /**
   * @deprecated As of EMF 2.1, replaced by {@link #getTypeGenDataType}.
   */
  @Deprecated
  GenDataType getGenDataTypeType();

  /**
   * @deprecated As of EMF 2.1, replaced by {@link #getTypeGenClass}.
   */
  @Deprecated
  GenClass getGenClassType();

  String getLowerBound();
  String getUpperBound();

  boolean isUnique();
  String getUniqueFlag();
  String getOrderedFlag();

} // GenTypedElement
