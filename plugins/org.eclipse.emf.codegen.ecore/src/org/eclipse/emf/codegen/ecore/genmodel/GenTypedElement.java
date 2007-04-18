/**
 * <copyright> 
 *
 * Copyright (c) 2002-2006 IBM Corporation and others.
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
 * $Id: GenTypedElement.java,v 1.8 2007/04/18 20:24:34 emerks Exp $
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
   * @since 2.3
   */
  String getRawBoundType(); // this returns the feature type as for getRawType, except for the case that the type is a type parameter, in which case it returns the actual bounding type.
  String getType(); // this returns the feature type, either primitive (e.g. "int") or qualified class name (e.g. "java.lang.String", "org.eclipse.emf.common.util.EList", or "org.sample.company.Company")

  /**
   * @since 2.3
   */
  String getRawImportedType();
  /**
   * @since 2.3
   */
  String getRawImportedBoundType(); // this returns the feature type as for getRawImportedType, except for the case that the type is a type parameter, in which case it returns the actual bounding type.
  String getImportedType(); // this returns the feature type, either primitive (e.g. "int") or imported class name (e.g. "String", "EList", or "Company")
  String getObjectType(); // this returns the imported name of the feature type or, if primitive, of the wrapper class (e.g. "Integer")
  String getImportedInternalType(); // this returns the real imported feature type used internally, regardless of the value of GenModel.isSuppressEMFValues()

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
   * @since 2.3
   */
  String getListTemplateArguments(); // returns either "" or the getListItemType surrounded by <>, depending on the effective compliance level.
  /**
   * @since 2.3
   */
  String getRawListItemType(); // this returns the raw imported name of the feature type/wrapper class, or if a multi-valued feature, the type of the EList members
  String getListItemType(); // this returns the imported name of the feature type/wrapper class, or if a multi-valued feature, the type of the EList members
  String getQualifiedListItemType(); // this returns the qualified name of the feature type/wrapper class, or if a multi-valued feature, the type of the EList members

  boolean isMapType();
  GenClass getMapEntryTypeGenClass(); 
  String getImportedMapEntryType(); 
  /**
   * @since 2.3
   */
  String getImportedMapKeyType();
  /**
   * @since 2.3
   */
  String getImportedMapValueType();
  /**
   * @since 2.3
   */
  String getImportedMapTemplateArguments();

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

  String getEObjectCast(); // this returns either "(EObject)", "(org.eclipse.emf.ecore.EObject)", or "" as needed for the reference type.
  String getInternalTypeCast(); // this returns either "(EObject)", "(org.eclipse.emf.ecore.EObject)", or "" as needed for the reference type.
  String getNonEObjectInternalTypeCast(); // this returns either a cast to the internal type, or "" if it is EObject.
  /**
   * @since 2.3
   */
  String getRawTypeCast(); // this returns that case to the actual type if it's different from the raw type.

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
