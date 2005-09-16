/**
 * <copyright>
 * </copyright>
 *
 * $Id: GenTypedElement.java,v 1.3 2005/09/16 22:01:51 davidms Exp $
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
  String getTypeClassifier();

  String getType(); // this returns the feature type, either primitive (e.g. "int") or qualified class name (e.g. "java.lang.String", "org.eclipse.emf.common.util.EList", or "org.sample.company.Company")
  String getImportedType(); // this returns the feature type, either primitive (e.g. "int") or imported class name (e.g. "String", "EList", or "Company")
  String getObjectType(); // this returns the imported name of the feature type or, if primitive, of the wrapper class (e.g. "Integer")
  String getImportedInternalType(); // this returns the real imported feature type used internally, regardless of the value of GenModel.isSuppressEMFValues()

  boolean isFeatureMapType();
  boolean isWrappedFeatureMapType();

  /**
   * @deprecated As of EMF 2.1, replaced by {@link #isWrappedFeatureMapType}. 
   */
  boolean isFeatureMapWrapped();

  boolean isEffectiveSuppressEMFTypes();

  String getImportedEffectiveFeatureMapWrapperInternalInterface();
  String getImportedEffectiveFeatureMapWrapperClass();

  boolean isListType();
  String getListItemType(); // this returns the imported name of the feature type/wrapper class, or if a multi-valued feature, the type of the EList members
  String getQualifiedListItemType(); // this returns the qualified name of the feature type/wrapper class, or if a multi-valued feature, the type of the EList members

  boolean isMapType();
  GenClass getMapEntryTypeGenClass(); 
  String getImportedMapEntryType(); 

  /**
   * @deprecated As of EMF 2.1, replaced by {@link #getMapEntryTypeGenClass}.
   */
  GenClass getMapGenClass(); 

  /**
   * @deprecated As of EMF 2.1, replaced by {@link #getImportedMapEntryType}.
   */
  String getMapItemType(); 

  String getEObjectCast(); // this returns either "(EObject)", "(org.eclipse.emf.ecore.EObject)", or "" as needed for the reference type.
  String getInternalTypeCast(); // this returns either "(EObject)", "(org.eclipse.emf.ecore.EObject)", or "" as needed for the reference type.
  String getNonEObjectInternalTypeCast(); // this returns either a cast to the internal type, or "" if it is EObject.

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

  /**
   * @deprecated As of EMF 2.1, replaced by {@link #getTypeGenEnum}.
   */
  GenEnum getGenEnumType();

  /**
   * @deprecated As of EMF 2.1, replaced by {@link #getTypeGenDataType}.
   */
  GenDataType getGenDataTypeType();

  /**
   * @deprecated As of EMF 2.1, replaced by {@link #getTypeGenClass}.
   */
  GenClass getGenClassType();

  String getLowerBound();
  String getUpperBound();

  boolean isUnique();
  String getUniqueFlag();
  String getOrderedFlag();

} // GenTypedElement
