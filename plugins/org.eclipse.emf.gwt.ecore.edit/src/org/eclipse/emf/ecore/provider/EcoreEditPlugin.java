/**
 * Copyright (c) 2002-2010 IBM Corporation and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *   IBM - Initial API and implementation
 */
package org.eclipse.emf.ecore.provider;


import com.google.gwt.core.client.GWT;
import org.eclipse.emf.common.EMFPlugin;
import org.eclipse.emf.common.util.ResourceLocator;


/**
 * This is the central singleton for the Ecore edit plugin.
 * <!-- begin-user-doc -->
 * <!-- end-user-doc -->
 * @generated
 */
public final class EcoreEditPlugin extends EMFPlugin
{
  /**
   * Keep track of the singleton.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public static final EcoreEditPlugin INSTANCE = new EcoreEditPlugin();

  /**
   * Create the instance.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EcoreEditPlugin()
  {
    super
      (new ResourceLocator [] 
       {
       });
  }

  /**
   * Returns the singleton instance of the Eclipse plugin.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the singleton instance.
   * @generated
   */
  @Override
  public ResourceLocator getPluginResourceLocator()
  {
    return null;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected static final EcoreEditPluginProperties PROPERTIES = GWT.create(EcoreEditPluginProperties.class);

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public String getString(String key, boolean translate)
  {
    if ("_UI_EAttribute_type".equals(key)) return PROPERTIES.eAttributeType();
    else if ("_UI_EAnnotation_type".equals(key)) return PROPERTIES.eAnnotationType();
    else if ("_UI_EClass_type".equals(key)) return PROPERTIES.eClassType();
    else if ("_UI_EClassifier_type".equals(key)) return PROPERTIES.eClassifierType();
    else if ("_UI_EDataType_type".equals(key)) return PROPERTIES.eDataTypeType();
    else if ("_UI_EEnum_type".equals(key)) return PROPERTIES.eEnumType();
    else if ("_UI_EEnumLiteral_type".equals(key)) return PROPERTIES.eEnumLiteralType();
    else if ("_UI_EFactory_type".equals(key)) return PROPERTIES.eFactoryType();
    else if ("_UI_EModelElement_type".equals(key)) return PROPERTIES.eModelElementType();
    else if ("_UI_ENamedElement_type".equals(key)) return PROPERTIES.eNamedElementType();
    else if ("_UI_EObject_type".equals(key)) return PROPERTIES.eObjectType();
    else if ("_UI_EOperation_type".equals(key)) return PROPERTIES.eOperationType();
    else if ("_UI_EPackage_type".equals(key)) return PROPERTIES.ePackageType();
    else if ("_UI_EParameter_type".equals(key)) return PROPERTIES.eParameterType();
    else if ("_UI_EReference_type".equals(key)) return PROPERTIES.eReferenceType();
    else if ("_UI_EStructuralFeature_type".equals(key)) return PROPERTIES.eStructuralFeatureType();
    else if ("_UI_ETypedElement_type".equals(key)) return PROPERTIES.eTypedElementType();
    else if ("_UI_EStringToStringMapEntry_type".equals(key)) return PROPERTIES.eStringToStringMapEntryType();
    else if ("_UI_EGenericType_type".equals(key)) return PROPERTIES.eGenericTypeType();
    else if ("_UI_ETypeParameter_type".equals(key)) return PROPERTIES.eTypeParameterType();
    else  if ("_UI_Unknown_type".equals(key)) return PROPERTIES.unknownType();
    else if ("_UI_Unknown_datatype".equals(key)) return PROPERTIES.unknownDatatype();
    else if ("_UI_EAttribute_iD_feature".equals(key)) return PROPERTIES.eAttribute_IDFeature();
    else if ("_UI_EAttribute_iD_description".equals(key)) return PROPERTIES.eAttribute_IDDescription();
    else if ("_UI_EAttribute_eAttributeType_feature".equals(key)) return PROPERTIES.eAttribute_EAttributeTypeFeature();
    else if ("_UI_EAttribute_eAttributeType_description".equals(key)) return PROPERTIES.eAttribute_EAttributeTypeDescription();
    else if ("_UI_EAnnotation_source_feature".equals(key)) return PROPERTIES.eAnnotation_SourceFeature();
    else if ("_UI_EAnnotation_source_description".equals(key)) return PROPERTIES.eAnnotation_SourceDescription();
    else if ("_UI_EAnnotation_details_feature".equals(key)) return PROPERTIES.eAnnotation_DetailsFeature();
    else if ("_UI_EAnnotation_eModelElement_feature".equals(key)) return PROPERTIES.eAnnotation_EModelElementFeature();
    else if ("_UI_EAnnotation_contents_feature".equals(key)) return PROPERTIES.eAnnotation_ContentsFeature();
    else if ("_UI_EAnnotation_references_feature".equals(key)) return PROPERTIES.eAnnotation_ReferencesFeature();
    else if ("_UI_EAnnotation_references_description".equals(key)) return PROPERTIES.eAnnotation_ReferencesDescription();
    else if ("_UI_EClass_abstract_feature".equals(key)) return PROPERTIES.eClass_AbstractFeature();
    else if ("_UI_EClass_abstract_description".equals(key)) return PROPERTIES.eClass_AbstractDescription();
    else if ("_UI_EClass_interface_feature".equals(key)) return PROPERTIES.eClass_InterfaceFeature();
    else if ("_UI_EClass_interface_description".equals(key)) return PROPERTIES.eClass_InterfaceDescription();
    else if ("_UI_EClass_eSuperTypes_feature".equals(key)) return PROPERTIES.eClass_ESuperTypesFeature();
    else if ("_UI_EClass_eSuperTypes_description".equals(key)) return PROPERTIES.eClass_ESuperTypesDescription();
    else if ("_UI_EClass_eOperations_feature".equals(key)) return PROPERTIES.eClass_EOperationsFeature();
    else if ("_UI_EClass_eOperations_description".equals(key)) return PROPERTIES.eClass_EOperationsDescription();
    else if ("_UI_EClass_eAllAttributes_feature".equals(key)) return PROPERTIES.eClass_EAllAttributesFeature();
    else if ("_UI_EClass_eAllReferences_feature".equals(key)) return PROPERTIES.eClass_EAllReferencesFeature();
    else if ("_UI_EClass_eReferences_feature".equals(key)) return PROPERTIES.eClass_EReferencesFeature();
    else if ("_UI_EClass_eAttributes_feature".equals(key)) return PROPERTIES.eClass_EAttributesFeature();
    else if ("_UI_EClass_eAllContainments_feature".equals(key)) return PROPERTIES.eClass_EAllContainmentsFeature();
    else if ("_UI_EClass_eAllOperations_feature".equals(key)) return PROPERTIES.eClass_EAllOperationsFeature();
    else if ("_UI_EClass_eAllStructuralFeatures_feature".equals(key)) return PROPERTIES.eClass_EAllStructuralFeaturesFeature();
    else if ("_UI_EClass_eAllSuperTypes_feature".equals(key)) return PROPERTIES.eClass_EAllSuperTypesFeature();
    else if ("_UI_EClass_eIDAttribute_feature".equals(key)) return PROPERTIES.eClass_EIDAttributeFeature();
    else if ("_UI_EClass_eStructuralFeatures_feature".equals(key)) return PROPERTIES.eClass_EStructuralFeaturesFeature();
    else if ("_UI_EClass_eGenericSuperTypes_feature".equals(key)) return PROPERTIES.eClass_EGenericSuperTypesFeature();
    else if ("_UI_EClass_eAllGenericSuperTypes_feature".equals(key)) return PROPERTIES.eClass_EAllGenericSuperTypesFeature();
    else if ("_UI_EClassifier_instanceClassName_feature".equals(key)) return PROPERTIES.eClassifier_InstanceClassNameFeature();
    else if ("_UI_EClassifier_instanceClassName_description".equals(key)) return PROPERTIES.eClassifier_InstanceClassNameDescription();
    else if ("_UI_EClassifier_instanceClass_feature".equals(key)) return PROPERTIES.eClassifier_InstanceClassFeature();
    else if ("_UI_EClassifier_defaultValue_feature".equals(key)) return PROPERTIES.eClassifier_DefaultValueFeature();
    else if ("_UI_EClassifier_defaultValue_description".equals(key)) return PROPERTIES.eClassifier_DefaultValueDescription();
    else if ("_UI_EClassifier_instanceTypeName_feature".equals(key)) return PROPERTIES.eClassifier_InstanceTypeNameFeature();
    else if ("_UI_EClassifier_instanceTypeName_description".equals(key)) return PROPERTIES.eClassifier_InstanceTypeNameDescription();
    else if ("_UI_EClassifier_ePackage_feature".equals(key)) return PROPERTIES.eClassifier_EPackageFeature();
    else if ("_UI_EClassifier_eTypeParameters_feature".equals(key)) return PROPERTIES.eClassifier_ETypeParametersFeature();
    else if ("_UI_EDataType_serializable_feature".equals(key)) return PROPERTIES.eDataType_SerializableFeature();
    else if ("_UI_EDataType_serializable_description".equals(key)) return PROPERTIES.eDataType_SerializableDescription();
    else if ("_UI_EEnum_eLiterals_feature".equals(key)) return PROPERTIES.eEnum_ELiteralsFeature();
    else if ("_UI_EEnumLiteral_value_feature".equals(key)) return PROPERTIES.eEnumLiteral_ValueFeature();
    else if ("_UI_EEnumLiteral_value_description".equals(key)) return PROPERTIES.eEnumLiteral_ValueDescription();
    else if ("_UI_EEnumLiteral_instance_feature".equals(key)) return PROPERTIES.eEnumLiteral_InstanceFeature();
    else if ("_UI_EEnumLiteral_literal_feature".equals(key)) return PROPERTIES.eEnumLiteral_LiteralFeature();
    else if ("_UI_EEnumLiteral_literal_description".equals(key)) return PROPERTIES.eEnumLiteral_LiteralDescription();
    else if ("_UI_EEnumLiteral_eEnum_feature".equals(key)) return PROPERTIES.eEnumLiteral_EEnumFeature();
    else if ("_UI_EFactory_ePackage_feature".equals(key)) return PROPERTIES.eFactory_EPackageFeature();
    else if ("_UI_EFactory_ePackage_description".equals(key)) return PROPERTIES.eFactory_EPackageDescription();
    else if ("_UI_EModelElement_eAnnotations_feature".equals(key)) return PROPERTIES.eModelElement_EAnnotationsFeature();
    else if ("_UI_ENamedElement_name_feature".equals(key)) return PROPERTIES.eNamedElement_NameFeature();
    else if ("_UI_ENamedElement_name_description".equals(key)) return PROPERTIES.eNamedElement_NameDescription();
    else if ("_UI_EOperation_eContainingClass_feature".equals(key)) return PROPERTIES.eOperation_EContainingClassFeature();
    else if ("_UI_EOperation_eTypeParameters_feature".equals(key)) return PROPERTIES.eOperation_ETypeParametersFeature();
    else if ("_UI_EOperation_eParameters_feature".equals(key)) return PROPERTIES.eOperation_EParametersFeature();
    else if ("_UI_EOperation_eExceptions_feature".equals(key)) return PROPERTIES.eOperation_EExceptionsFeature();
    else if ("_UI_EOperation_eExceptions_description".equals(key)) return PROPERTIES.eOperation_EExceptionsDescription();
    else if ("_UI_EOperation_eGenericExceptions_feature".equals(key)) return PROPERTIES.eOperation_EGenericExceptionsFeature();
    else if ("_UI_EPackage_nsURI_feature".equals(key)) return PROPERTIES.ePackage_NsURIFeature();
    else if ("_UI_EPackage_nsURI_description".equals(key)) return PROPERTIES.ePackage_NsURIDescription();
    else if ("_UI_EPackage_nsPrefix_feature".equals(key)) return PROPERTIES.ePackage_NsPrefixFeature();
    else if ("_UI_EPackage_nsPrefix_description".equals(key)) return PROPERTIES.ePackage_NsPrefixDescription();
    else if ("_UI_EPackage_eFactoryInstance_feature".equals(key)) return PROPERTIES.ePackage_EFactoryInstanceFeature();
    else if ("_UI_EPackage_eFactoryInstance_description".equals(key)) return PROPERTIES.ePackage_EFactoryInstanceDescription();
    else if ("_UI_EPackage_eClassifiers_feature".equals(key)) return PROPERTIES.ePackage_EClassifiersFeature();
    else if ("_UI_EPackage_eSubpackages_feature".equals(key)) return PROPERTIES.ePackage_ESubpackagesFeature();
    else if ("_UI_EPackage_eSuperPackage_feature".equals(key)) return PROPERTIES.ePackage_ESuperPackageFeature();
    else if ("_UI_EParameter_eOperation_feature".equals(key)) return PROPERTIES.eParameter_EOperationFeature();
    else if ("_UI_EReference_containment_feature".equals(key)) return PROPERTIES.eReference_ContainmentFeature();
    else if ("_UI_EReference_containment_description".equals(key)) return PROPERTIES.eReference_ContainmentDescription();
    else if ("_UI_EReference_container_feature".equals(key)) return PROPERTIES.eReference_ContainerFeature();
    else if ("_UI_EReference_container_description".equals(key)) return PROPERTIES.eReference_ContainerDescription();
    else if ("_UI_EReference_resolveProxies_feature".equals(key)) return PROPERTIES.eReference_ResolveProxiesFeature();
    else if ("_UI_EReference_resolveProxies_description".equals(key)) return PROPERTIES.eReference_ResolveProxiesDescription();
    else if ("_UI_EReference_eOpposite_feature".equals(key)) return PROPERTIES.eReference_EOppositeFeature();
    else if ("_UI_EReference_eOpposite_description".equals(key)) return PROPERTIES.eReference_EOppositeDescription();
    else if ("_UI_EReference_eReferenceType_feature".equals(key)) return PROPERTIES.eReference_EReferenceTypeFeature();
    else if ("_UI_EReference_eReferenceType_description".equals(key)) return PROPERTIES.eReference_EReferenceTypeDescription();
    else if ("_UI_EReference_eKeys_feature".equals(key)) return PROPERTIES.eReference_EKeysFeature();
    else if ("_UI_EReference_eKeys_description".equals(key)) return PROPERTIES.eReference_EKeysDescription();
    else if ("_UI_EStructuralFeature_changeable_feature".equals(key)) return PROPERTIES.eStructuralFeature_ChangeableFeature();
    else if ("_UI_EStructuralFeature_changeable_description".equals(key)) return PROPERTIES.eStructuralFeature_ChangeableDescription();
    else if ("_UI_EStructuralFeature_volatile_feature".equals(key)) return PROPERTIES.eStructuralFeature_VolatileFeature();
    else if ("_UI_EStructuralFeature_volatile_description".equals(key)) return PROPERTIES.eStructuralFeature_VolatileDescription();
    else if ("_UI_EStructuralFeature_transient_feature".equals(key)) return PROPERTIES.eStructuralFeature_TransientFeature();
    else if ("_UI_EStructuralFeature_transient_description".equals(key)) return PROPERTIES.eStructuralFeature_TransientDescription();
    else if ("_UI_EStructuralFeature_defaultValueLiteral_feature".equals(key)) return PROPERTIES.eStructuralFeature_DefaultValueLiteralFeature();
    else if ("_UI_EStructuralFeature_defaultValueLiteral_description".equals(key)) return PROPERTIES.eStructuralFeature_DefaultValueLiteralDescription();
    else if ("_UI_EStructuralFeature_defaultValue_feature".equals(key)) return PROPERTIES.eStructuralFeature_DefaultValueFeature();
    else if ("_UI_EStructuralFeature_defaultValue_description".equals(key)) return PROPERTIES.eStructuralFeature_DefaultValueDescription();
    else if ("_UI_EStructuralFeature_unsettable_feature".equals(key)) return PROPERTIES.eStructuralFeature_UnsettableFeature();
    else if ("_UI_EStructuralFeature_unsettable_description".equals(key)) return PROPERTIES.eStructuralFeature_UnsettableDescription();
    else if ("_UI_EStructuralFeature_derived_feature".equals(key)) return PROPERTIES.eStructuralFeature_DerivedFeature();
    else if ("_UI_EStructuralFeature_derived_description".equals(key)) return PROPERTIES.eStructuralFeature_DerivedDescription();
    else if ("_UI_EStructuralFeature_eContainingClass_feature".equals(key)) return PROPERTIES.eStructuralFeature_EContainingClassFeature();
    else if ("_UI_EStructuralFeature_eContainingClass_description".equals(key)) return PROPERTIES.eStructuralFeature_EContainingClassDescription();
    else if ("_UI_ETypedElement_ordered_feature".equals(key)) return PROPERTIES.eTypedElement_OrderedFeature();
    else if ("_UI_ETypedElement_ordered_description".equals(key)) return PROPERTIES.eTypedElement_OrderedDescription();
    else if ("_UI_ETypedElement_unique_feature".equals(key)) return PROPERTIES.eTypedElement_UniqueFeature();
    else if ("_UI_ETypedElement_unique_description".equals(key)) return PROPERTIES.eTypedElement_UniqueDescription();
    else if ("_UI_ETypedElement_lowerBound_feature".equals(key)) return PROPERTIES.eTypedElement_LowerBoundFeature();
    else if ("_UI_ETypedElement_lowerBound_description".equals(key)) return PROPERTIES.eTypedElement_LowerBoundDescription();
    else if ("_UI_ETypedElement_upperBound_feature".equals(key)) return PROPERTIES.eTypedElement_UpperBoundFeature();
    else if ("_UI_ETypedElement_upperBound_description".equals(key)) return PROPERTIES.eTypedElement_UpperBoundDescription();
    else if ("_UI_ETypedElement_many_feature".equals(key)) return PROPERTIES.eTypedElement_ManyFeature();
    else if ("_UI_ETypedElement_many_description".equals(key)) return PROPERTIES.eTypedElement_ManyDescription();
    else if ("_UI_ETypedElement_required_feature".equals(key)) return PROPERTIES.eTypedElement_RequiredFeature();
    else if ("_UI_ETypedElement_required_description".equals(key)) return PROPERTIES.eTypedElement_RequiredDescription();
    else if ("_UI_ETypedElement_eType_feature".equals(key)) return PROPERTIES.eTypedElement_ETypeFeature();
    else if ("_UI_ETypedElement_eType_description".equals(key)) return PROPERTIES.eTypedElement_ETypeDescription();
    else if ("_UI_ETypedElement_eGenericType_feature".equals(key)) return PROPERTIES.eTypedElement_EGenericTypeFeature();
    else if ("_UI_EStringToStringMapEntry_key_feature".equals(key)) return PROPERTIES.eStringToStringMapEntry_KeyFeature();
    else if ("_UI_EStringToStringMapEntry_key_description".equals(key)) return PROPERTIES.eStringToStringMapEntry_KeyDescription();
    else if ("_UI_EStringToStringMapEntry_value_feature".equals(key)) return PROPERTIES.eStringToStringMapEntry_ValueFeature();
    else if ("_UI_EStringToStringMapEntry_value_description".equals(key)) return PROPERTIES.eStringToStringMapEntry_ValueDescription();
    else if ("_UI_EGenericType_eUpperBound_feature".equals(key)) return PROPERTIES.eGenericType_EUpperBoundFeature();
    else if ("_UI_EGenericType_eTypeArguments_feature".equals(key)) return PROPERTIES.eGenericType_ETypeArgumentsFeature();
    else if ("_UI_EGenericType_eRawType_feature".equals(key)) return PROPERTIES.eGenericType_ERawTypeFeature();
    else if ("_UI_EGenericType_eRawType_description".equals(key)) return PROPERTIES.eGenericType_ERawTypeDescription();
    else if ("_UI_EGenericType_eLowerBound_feature".equals(key)) return PROPERTIES.eGenericType_ELowerBoundFeature();
    else if ("_UI_EGenericType_eTypeParameter_feature".equals(key)) return PROPERTIES.eGenericType_ETypeParameterFeature();
    else if ("_UI_EGenericType_eTypeParameter_description".equals(key)) return PROPERTIES.eGenericType_ETypeParameterDescription();
    else if ("_UI_EGenericType_eClassifier_feature".equals(key)) return PROPERTIES.eGenericType_EClassifierFeature();
    else if ("_UI_EGenericType_eClassifier_description".equals(key)) return PROPERTIES.eGenericType_EClassifierDescription();
    else if ("_UI_ETypeParameter_eBounds_feature".equals(key)) return PROPERTIES.eTypeParameter_EBoundsFeature();
    else if ("_UI_Unknown_feature".equals(key)) return PROPERTIES.unknownFeature();
    else return key;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public String getString(String key, Object [] substitutions, boolean translate)
  {
    if ("_UI_CreateChild_text".equals(key)) return PROPERTIES.createChildText(substitutions[0]);
    else if ("_UI_CreateChild_text2".equals(key)) return PROPERTIES.createChildText2(substitutions[0], substitutions[1]);
    else if ("_UI_CreateChild_text3".equals(key)) return PROPERTIES.createChildText3(substitutions[1]);
    else if ("_UI_CreateChild_tooltip".equals(key)) return PROPERTIES.createChildTooltip(substitutions[0], substitutions[1]);
    else if ("_UI_CreateChild_description".equals(key)) return PROPERTIES.createChildDescripition(substitutions[0], substitutions[1], substitutions[2]);
    else if ("_UI_CreateSibling_description".equals(key)) return PROPERTIES.createSiblingDescription(substitutions[0], substitutions[1], substitutions[2]);
    if ("_UI_PropertyDescriptor_description".equals(key)) return PROPERTIES.propertyDescriptorDescription(substitutions[0], substitutions[1]);
    else return key;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected static final EcoreEditPluginImages IMAGES = GWT.create(EcoreEditPluginImages.class);

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public Object getImageGen(String key)
  {
    if ("full/obj16/EAttribute".equals(key)) return IMAGES.eAttribute();
    else if ("full/obj16/EAnnotation".equals(key)) return IMAGES.eAnnotation();
    else if ("full/obj16/EClass".equals(key)) return IMAGES.eClass();
    else if ("full/obj16/EDataType".equals(key)) return IMAGES.eDataType();
    else if ("full/obj16/EEnum".equals(key)) return IMAGES.eEnum();
    else if ("full/obj16/EEnumLiteral".equals(key)) return IMAGES.eEnumLiteral();
    else if ("full/obj16/EFactory".equals(key)) return IMAGES.eFactory();
    else if ("full/obj16/EObject".equals(key)) return IMAGES.eObject();
    else if ("full/obj16/EOperation".equals(key)) return IMAGES.eOperation();
    else if ("full/obj16/EPackage".equals(key)) return IMAGES.ePackage();
    else if ("full/obj16/EParameter".equals(key)) return IMAGES.eParameter();
    else if ("full/obj16/EReference".equals(key)) return IMAGES.eReference();
    else if ("full/obj16/EStringToStringMapEntry".equals(key)) return IMAGES.eStringToStringMapEntry();
    else if ("full/obj16/EGenericType".equals(key)) return IMAGES.eGenericType();
    else if ("full/obj16/ETypeParameter".equals(key)) return IMAGES.eTypeParameter();
    else return key;
  }

  @Override
  public Object getImage(String key)
  {
    if ("full/obj16/EGenericSuperType".equals(key)) return IMAGES.eGenericSuperType();
    else if ("full/obj16/EGenericElementType".equals(key)) return IMAGES.eGenericElementType();
    else if ("full/obj16/EGenericException".equals(key)) return IMAGES.eGenericException();
    else if ("full/obj16/EOccurrenceN".equals(key)) return IMAGES.eOccurrenceN();
    else if ("full/obj16/EOccurrenceNToM".equals(key)) return IMAGES.eOccurrenceNToM();
    else if ("full/obj16/EOccurrenceNToUnbounded".equals(key)) return IMAGES.eOccurrenceNToUnbounded();
    else if ("full/obj16/EOccurrenceNToUnspecified".equals(key)) return IMAGES.eOccurrenceNToUnspecified();
    else if ("full/obj16/EOccurrenceOne".equals(key)) return IMAGES.eOccurrenceOne();
    else if ("full/obj16/EOccurrenceOneToN".equals(key)) return IMAGES.eOccurrenceOneToN();
    else if ("full/obj16/EOccurrenceOneToUnbounded".equals(key)) return IMAGES.eOccurrenceOneToUnbounded();
    else if ("full/obj16/EOccurrenceOneToUnspecified".equals(key)) return IMAGES.eOccurrenceOneToUnspecified();
    else if ("full/obj16/EOccurrenceZero".equals(key)) return IMAGES.eOccurrenceZero();
    else if ("full/obj16/EOccurrenceZeroToN".equals(key)) return IMAGES.eOccurrenceZeroToN();
    else if ("full/obj16/EOccurrenceZeroToOne".equals(key)) return IMAGES.eOccurrenceZeroToOne();
    else if ("full/obj16/EOccurrenceZeroToUnbounded".equals(key)) return IMAGES.eOccurrenceZeroToUnbounded();
    else if ("full/obj16/EOccurrenceZeroToUnspecified".equals(key)) return IMAGES.eOccurrenceZeroToUnspecified();
    else return getImageGen(key);
  }
}
