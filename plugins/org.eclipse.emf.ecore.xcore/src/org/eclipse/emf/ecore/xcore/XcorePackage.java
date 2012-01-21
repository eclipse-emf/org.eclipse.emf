/**
 * Copyright (c) 2011-2012 Eclipse contributors and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package org.eclipse.emf.ecore.xcore;


import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;


/**
 * <!-- begin-user-doc -->
 * The <b>Package</b> for the model.
 * It contains accessors for the meta objects to represent
 * <ul>
 *   <li>each class,</li>
 *   <li>each feature of each class,</li>
 *   <li>each enum,</li>
 *   <li>and each data type</li>
 * </ul>
 * <!-- end-user-doc -->
 * @see org.eclipse.emf.ecore.xcore.XcoreFactory
 * @model kind="package"
 * @generated
 */
public interface XcorePackage extends EPackage
{
  /**
   * The package name.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  String eNAME = "xcore";

  /**
   * The package namespace URI.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  String eNS_URI = "http://www.eclipse.org/emf/2011/Xcore";

  /**
   * The package namespace name.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  String eNS_PREFIX = "xcore";

  /**
   * The singleton instance of the package.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  XcorePackage eINSTANCE = org.eclipse.emf.ecore.xcore.impl.XcorePackageImpl.init();

  /**
   * The meta object id for the '{@link org.eclipse.emf.ecore.xcore.impl.XModelElementImpl <em>XModel Element</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.eclipse.emf.ecore.xcore.impl.XModelElementImpl
   * @see org.eclipse.emf.ecore.xcore.impl.XcorePackageImpl#getXModelElement()
   * @generated
   */
  int XMODEL_ELEMENT = 11;

  /**
   * The feature id for the '<em><b>Annotations</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int XMODEL_ELEMENT__ANNOTATIONS = 0;

  /**
   * The number of structural features of the '<em>XModel Element</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int XMODEL_ELEMENT_FEATURE_COUNT = 1;

  /**
   * The meta object id for the '{@link org.eclipse.emf.ecore.xcore.impl.XAnnotationImpl <em>XAnnotation</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.eclipse.emf.ecore.xcore.impl.XAnnotationImpl
   * @see org.eclipse.emf.ecore.xcore.impl.XcorePackageImpl#getXAnnotation()
   * @generated
   */
  int XANNOTATION = 0;

  /**
   * The feature id for the '<em><b>Annotations</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int XANNOTATION__ANNOTATIONS = XMODEL_ELEMENT__ANNOTATIONS;

  /**
   * The feature id for the '<em><b>Source</b></em>' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int XANNOTATION__SOURCE = XMODEL_ELEMENT_FEATURE_COUNT + 0;

  /**
   * The feature id for the '<em><b>Details</b></em>' map.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int XANNOTATION__DETAILS = XMODEL_ELEMENT_FEATURE_COUNT + 1;

  /**
   * The feature id for the '<em><b>Model Element</b></em>' container reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int XANNOTATION__MODEL_ELEMENT = XMODEL_ELEMENT_FEATURE_COUNT + 2;

  /**
   * The number of structural features of the '<em>XAnnotation</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int XANNOTATION_FEATURE_COUNT = XMODEL_ELEMENT_FEATURE_COUNT + 3;

  /**
   * The meta object id for the '{@link org.eclipse.emf.ecore.xcore.impl.XNamedElementImpl <em>XNamed Element</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.eclipse.emf.ecore.xcore.impl.XNamedElementImpl
   * @see org.eclipse.emf.ecore.xcore.impl.XcorePackageImpl#getXNamedElement()
   * @generated
   */
  int XNAMED_ELEMENT = 12;

  /**
   * The feature id for the '<em><b>Annotations</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int XNAMED_ELEMENT__ANNOTATIONS = XMODEL_ELEMENT__ANNOTATIONS;

  /**
   * The feature id for the '<em><b>Name</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int XNAMED_ELEMENT__NAME = XMODEL_ELEMENT_FEATURE_COUNT + 0;

  /**
   * The number of structural features of the '<em>XNamed Element</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int XNAMED_ELEMENT_FEATURE_COUNT = XMODEL_ELEMENT_FEATURE_COUNT + 1;

  /**
   * The meta object id for the '{@link org.eclipse.emf.ecore.xcore.impl.XAnnotationDirectiveImpl <em>XAnnotation Directive</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.eclipse.emf.ecore.xcore.impl.XAnnotationDirectiveImpl
   * @see org.eclipse.emf.ecore.xcore.impl.XcorePackageImpl#getXAnnotationDirective()
   * @generated
   */
  int XANNOTATION_DIRECTIVE = 1;

  /**
   * The feature id for the '<em><b>Annotations</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int XANNOTATION_DIRECTIVE__ANNOTATIONS = XNAMED_ELEMENT__ANNOTATIONS;

  /**
   * The feature id for the '<em><b>Name</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int XANNOTATION_DIRECTIVE__NAME = XNAMED_ELEMENT__NAME;

  /**
   * The feature id for the '<em><b>Source URI</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int XANNOTATION_DIRECTIVE__SOURCE_URI = XNAMED_ELEMENT_FEATURE_COUNT + 0;

  /**
   * The feature id for the '<em><b>Package</b></em>' container reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int XANNOTATION_DIRECTIVE__PACKAGE = XNAMED_ELEMENT_FEATURE_COUNT + 1;

  /**
   * The number of structural features of the '<em>XAnnotation Directive</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int XANNOTATION_DIRECTIVE_FEATURE_COUNT = XNAMED_ELEMENT_FEATURE_COUNT + 2;

  /**
   * The meta object id for the '{@link org.eclipse.emf.ecore.xcore.impl.XTypedElementImpl <em>XTyped Element</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.eclipse.emf.ecore.xcore.impl.XTypedElementImpl
   * @see org.eclipse.emf.ecore.xcore.impl.XcorePackageImpl#getXTypedElement()
   * @generated
   */
  int XTYPED_ELEMENT = 19;

  /**
   * The feature id for the '<em><b>Annotations</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int XTYPED_ELEMENT__ANNOTATIONS = XNAMED_ELEMENT__ANNOTATIONS;

  /**
   * The feature id for the '<em><b>Name</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int XTYPED_ELEMENT__NAME = XNAMED_ELEMENT__NAME;

  /**
   * The feature id for the '<em><b>Unordered</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int XTYPED_ELEMENT__UNORDERED = XNAMED_ELEMENT_FEATURE_COUNT + 0;

  /**
   * The feature id for the '<em><b>Unique</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int XTYPED_ELEMENT__UNIQUE = XNAMED_ELEMENT_FEATURE_COUNT + 1;

  /**
   * The feature id for the '<em><b>Type</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int XTYPED_ELEMENT__TYPE = XNAMED_ELEMENT_FEATURE_COUNT + 2;

  /**
   * The feature id for the '<em><b>Multiplicity</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int XTYPED_ELEMENT__MULTIPLICITY = XNAMED_ELEMENT_FEATURE_COUNT + 3;

  /**
   * The number of structural features of the '<em>XTyped Element</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int XTYPED_ELEMENT_FEATURE_COUNT = XNAMED_ELEMENT_FEATURE_COUNT + 4;

  /**
   * The meta object id for the '{@link org.eclipse.emf.ecore.xcore.impl.XMemberImpl <em>XMember</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.eclipse.emf.ecore.xcore.impl.XMemberImpl
   * @see org.eclipse.emf.ecore.xcore.impl.XcorePackageImpl#getXMember()
   * @generated
   */
  int XMEMBER = 10;

  /**
   * The feature id for the '<em><b>Annotations</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int XMEMBER__ANNOTATIONS = XTYPED_ELEMENT__ANNOTATIONS;

  /**
   * The feature id for the '<em><b>Name</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int XMEMBER__NAME = XTYPED_ELEMENT__NAME;

  /**
   * The feature id for the '<em><b>Unordered</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int XMEMBER__UNORDERED = XTYPED_ELEMENT__UNORDERED;

  /**
   * The feature id for the '<em><b>Unique</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int XMEMBER__UNIQUE = XTYPED_ELEMENT__UNIQUE;

  /**
   * The feature id for the '<em><b>Type</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int XMEMBER__TYPE = XTYPED_ELEMENT__TYPE;

  /**
   * The feature id for the '<em><b>Multiplicity</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int XMEMBER__MULTIPLICITY = XTYPED_ELEMENT__MULTIPLICITY;

  /**
   * The feature id for the '<em><b>Containing Class</b></em>' container reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int XMEMBER__CONTAINING_CLASS = XTYPED_ELEMENT_FEATURE_COUNT + 0;

  /**
   * The number of structural features of the '<em>XMember</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int XMEMBER_FEATURE_COUNT = XTYPED_ELEMENT_FEATURE_COUNT + 1;

  /**
   * The meta object id for the '{@link org.eclipse.emf.ecore.xcore.impl.XStructuralFeatureImpl <em>XStructural Feature</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.eclipse.emf.ecore.xcore.impl.XStructuralFeatureImpl
   * @see org.eclipse.emf.ecore.xcore.impl.XcorePackageImpl#getXStructuralFeature()
   * @generated
   */
  int XSTRUCTURAL_FEATURE = 18;

  /**
   * The feature id for the '<em><b>Annotations</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int XSTRUCTURAL_FEATURE__ANNOTATIONS = XMEMBER__ANNOTATIONS;

  /**
   * The feature id for the '<em><b>Name</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int XSTRUCTURAL_FEATURE__NAME = XMEMBER__NAME;

  /**
   * The feature id for the '<em><b>Unordered</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int XSTRUCTURAL_FEATURE__UNORDERED = XMEMBER__UNORDERED;

  /**
   * The feature id for the '<em><b>Unique</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int XSTRUCTURAL_FEATURE__UNIQUE = XMEMBER__UNIQUE;

  /**
   * The feature id for the '<em><b>Type</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int XSTRUCTURAL_FEATURE__TYPE = XMEMBER__TYPE;

  /**
   * The feature id for the '<em><b>Multiplicity</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int XSTRUCTURAL_FEATURE__MULTIPLICITY = XMEMBER__MULTIPLICITY;

  /**
   * The feature id for the '<em><b>Containing Class</b></em>' container reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int XSTRUCTURAL_FEATURE__CONTAINING_CLASS = XMEMBER__CONTAINING_CLASS;

  /**
   * The feature id for the '<em><b>Readonly</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int XSTRUCTURAL_FEATURE__READONLY = XMEMBER_FEATURE_COUNT + 0;

  /**
   * The feature id for the '<em><b>Volatile</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int XSTRUCTURAL_FEATURE__VOLATILE = XMEMBER_FEATURE_COUNT + 1;

  /**
   * The feature id for the '<em><b>Transient</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int XSTRUCTURAL_FEATURE__TRANSIENT = XMEMBER_FEATURE_COUNT + 2;

  /**
   * The feature id for the '<em><b>Unsettable</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int XSTRUCTURAL_FEATURE__UNSETTABLE = XMEMBER_FEATURE_COUNT + 3;

  /**
   * The feature id for the '<em><b>Derived</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int XSTRUCTURAL_FEATURE__DERIVED = XMEMBER_FEATURE_COUNT + 4;

  /**
   * The feature id for the '<em><b>Get Body</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int XSTRUCTURAL_FEATURE__GET_BODY = XMEMBER_FEATURE_COUNT + 5;

  /**
   * The feature id for the '<em><b>Set Body</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int XSTRUCTURAL_FEATURE__SET_BODY = XMEMBER_FEATURE_COUNT + 6;

  /**
   * The feature id for the '<em><b>Is Set Body</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int XSTRUCTURAL_FEATURE__IS_SET_BODY = XMEMBER_FEATURE_COUNT + 7;

  /**
   * The feature id for the '<em><b>Unset Body</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int XSTRUCTURAL_FEATURE__UNSET_BODY = XMEMBER_FEATURE_COUNT + 8;

  /**
   * The number of structural features of the '<em>XStructural Feature</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int XSTRUCTURAL_FEATURE_FEATURE_COUNT = XMEMBER_FEATURE_COUNT + 9;

  /**
   * The meta object id for the '{@link org.eclipse.emf.ecore.xcore.impl.XAttributeImpl <em>XAttribute</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.eclipse.emf.ecore.xcore.impl.XAttributeImpl
   * @see org.eclipse.emf.ecore.xcore.impl.XcorePackageImpl#getXAttribute()
   * @generated
   */
  int XATTRIBUTE = 2;

  /**
   * The feature id for the '<em><b>Annotations</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int XATTRIBUTE__ANNOTATIONS = XSTRUCTURAL_FEATURE__ANNOTATIONS;

  /**
   * The feature id for the '<em><b>Name</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int XATTRIBUTE__NAME = XSTRUCTURAL_FEATURE__NAME;

  /**
   * The feature id for the '<em><b>Unordered</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int XATTRIBUTE__UNORDERED = XSTRUCTURAL_FEATURE__UNORDERED;

  /**
   * The feature id for the '<em><b>Unique</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int XATTRIBUTE__UNIQUE = XSTRUCTURAL_FEATURE__UNIQUE;

  /**
   * The feature id for the '<em><b>Type</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int XATTRIBUTE__TYPE = XSTRUCTURAL_FEATURE__TYPE;

  /**
   * The feature id for the '<em><b>Multiplicity</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int XATTRIBUTE__MULTIPLICITY = XSTRUCTURAL_FEATURE__MULTIPLICITY;

  /**
   * The feature id for the '<em><b>Containing Class</b></em>' container reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int XATTRIBUTE__CONTAINING_CLASS = XSTRUCTURAL_FEATURE__CONTAINING_CLASS;

  /**
   * The feature id for the '<em><b>Readonly</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int XATTRIBUTE__READONLY = XSTRUCTURAL_FEATURE__READONLY;

  /**
   * The feature id for the '<em><b>Volatile</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int XATTRIBUTE__VOLATILE = XSTRUCTURAL_FEATURE__VOLATILE;

  /**
   * The feature id for the '<em><b>Transient</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int XATTRIBUTE__TRANSIENT = XSTRUCTURAL_FEATURE__TRANSIENT;

  /**
   * The feature id for the '<em><b>Unsettable</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int XATTRIBUTE__UNSETTABLE = XSTRUCTURAL_FEATURE__UNSETTABLE;

  /**
   * The feature id for the '<em><b>Derived</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int XATTRIBUTE__DERIVED = XSTRUCTURAL_FEATURE__DERIVED;

  /**
   * The feature id for the '<em><b>Get Body</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int XATTRIBUTE__GET_BODY = XSTRUCTURAL_FEATURE__GET_BODY;

  /**
   * The feature id for the '<em><b>Set Body</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int XATTRIBUTE__SET_BODY = XSTRUCTURAL_FEATURE__SET_BODY;

  /**
   * The feature id for the '<em><b>Is Set Body</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int XATTRIBUTE__IS_SET_BODY = XSTRUCTURAL_FEATURE__IS_SET_BODY;

  /**
   * The feature id for the '<em><b>Unset Body</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int XATTRIBUTE__UNSET_BODY = XSTRUCTURAL_FEATURE__UNSET_BODY;

  /**
   * The feature id for the '<em><b>Default Value Literal</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int XATTRIBUTE__DEFAULT_VALUE_LITERAL = XSTRUCTURAL_FEATURE_FEATURE_COUNT + 0;

  /**
   * The feature id for the '<em><b>ID</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int XATTRIBUTE__ID = XSTRUCTURAL_FEATURE_FEATURE_COUNT + 1;

  /**
   * The number of structural features of the '<em>XAttribute</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int XATTRIBUTE_FEATURE_COUNT = XSTRUCTURAL_FEATURE_FEATURE_COUNT + 2;

  /**
   * The meta object id for the '{@link org.eclipse.emf.ecore.xcore.impl.XClassifierImpl <em>XClassifier</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.eclipse.emf.ecore.xcore.impl.XClassifierImpl
   * @see org.eclipse.emf.ecore.xcore.impl.XcorePackageImpl#getXClassifier()
   * @generated
   */
  int XCLASSIFIER = 4;

  /**
   * The feature id for the '<em><b>Annotations</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int XCLASSIFIER__ANNOTATIONS = XNAMED_ELEMENT__ANNOTATIONS;

  /**
   * The feature id for the '<em><b>Name</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int XCLASSIFIER__NAME = XNAMED_ELEMENT__NAME;

  /**
   * The feature id for the '<em><b>Instance Type</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int XCLASSIFIER__INSTANCE_TYPE = XNAMED_ELEMENT_FEATURE_COUNT + 0;

  /**
   * The feature id for the '<em><b>Package</b></em>' container reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int XCLASSIFIER__PACKAGE = XNAMED_ELEMENT_FEATURE_COUNT + 1;

  /**
   * The feature id for the '<em><b>Type Parameters</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int XCLASSIFIER__TYPE_PARAMETERS = XNAMED_ELEMENT_FEATURE_COUNT + 2;

  /**
   * The number of structural features of the '<em>XClassifier</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int XCLASSIFIER_FEATURE_COUNT = XNAMED_ELEMENT_FEATURE_COUNT + 3;

  /**
   * The meta object id for the '{@link org.eclipse.emf.ecore.xcore.impl.XClassImpl <em>XClass</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.eclipse.emf.ecore.xcore.impl.XClassImpl
   * @see org.eclipse.emf.ecore.xcore.impl.XcorePackageImpl#getXClass()
   * @generated
   */
  int XCLASS = 3;

  /**
   * The feature id for the '<em><b>Annotations</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int XCLASS__ANNOTATIONS = XCLASSIFIER__ANNOTATIONS;

  /**
   * The feature id for the '<em><b>Name</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int XCLASS__NAME = XCLASSIFIER__NAME;

  /**
   * The feature id for the '<em><b>Instance Type</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int XCLASS__INSTANCE_TYPE = XCLASSIFIER__INSTANCE_TYPE;

  /**
   * The feature id for the '<em><b>Package</b></em>' container reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int XCLASS__PACKAGE = XCLASSIFIER__PACKAGE;

  /**
   * The feature id for the '<em><b>Type Parameters</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int XCLASS__TYPE_PARAMETERS = XCLASSIFIER__TYPE_PARAMETERS;

  /**
   * The feature id for the '<em><b>Abstract</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int XCLASS__ABSTRACT = XCLASSIFIER_FEATURE_COUNT + 0;

  /**
   * The feature id for the '<em><b>Interface</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int XCLASS__INTERFACE = XCLASSIFIER_FEATURE_COUNT + 1;

  /**
   * The feature id for the '<em><b>Members</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int XCLASS__MEMBERS = XCLASSIFIER_FEATURE_COUNT + 2;

  /**
   * The feature id for the '<em><b>Super Types</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int XCLASS__SUPER_TYPES = XCLASSIFIER_FEATURE_COUNT + 3;

  /**
   * The number of structural features of the '<em>XClass</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int XCLASS_FEATURE_COUNT = XCLASSIFIER_FEATURE_COUNT + 4;

  /**
   * The meta object id for the '{@link org.eclipse.emf.ecore.xcore.impl.XDataTypeImpl <em>XData Type</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.eclipse.emf.ecore.xcore.impl.XDataTypeImpl
   * @see org.eclipse.emf.ecore.xcore.impl.XcorePackageImpl#getXDataType()
   * @generated
   */
  int XDATA_TYPE = 5;

  /**
   * The feature id for the '<em><b>Annotations</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int XDATA_TYPE__ANNOTATIONS = XCLASSIFIER__ANNOTATIONS;

  /**
   * The feature id for the '<em><b>Name</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int XDATA_TYPE__NAME = XCLASSIFIER__NAME;

  /**
   * The feature id for the '<em><b>Instance Type</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int XDATA_TYPE__INSTANCE_TYPE = XCLASSIFIER__INSTANCE_TYPE;

  /**
   * The feature id for the '<em><b>Package</b></em>' container reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int XDATA_TYPE__PACKAGE = XCLASSIFIER__PACKAGE;

  /**
   * The feature id for the '<em><b>Type Parameters</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int XDATA_TYPE__TYPE_PARAMETERS = XCLASSIFIER__TYPE_PARAMETERS;

  /**
   * The feature id for the '<em><b>Serializable</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int XDATA_TYPE__SERIALIZABLE = XCLASSIFIER_FEATURE_COUNT + 0;

  /**
   * The feature id for the '<em><b>Create Body</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int XDATA_TYPE__CREATE_BODY = XCLASSIFIER_FEATURE_COUNT + 1;

  /**
   * The feature id for the '<em><b>Convert Body</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int XDATA_TYPE__CONVERT_BODY = XCLASSIFIER_FEATURE_COUNT + 2;

  /**
   * The number of structural features of the '<em>XData Type</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int XDATA_TYPE_FEATURE_COUNT = XCLASSIFIER_FEATURE_COUNT + 3;

  /**
   * The meta object id for the '{@link org.eclipse.emf.ecore.xcore.impl.XEnumImpl <em>XEnum</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.eclipse.emf.ecore.xcore.impl.XEnumImpl
   * @see org.eclipse.emf.ecore.xcore.impl.XcorePackageImpl#getXEnum()
   * @generated
   */
  int XENUM = 6;

  /**
   * The feature id for the '<em><b>Annotations</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int XENUM__ANNOTATIONS = XDATA_TYPE__ANNOTATIONS;

  /**
   * The feature id for the '<em><b>Name</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int XENUM__NAME = XDATA_TYPE__NAME;

  /**
   * The feature id for the '<em><b>Instance Type</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int XENUM__INSTANCE_TYPE = XDATA_TYPE__INSTANCE_TYPE;

  /**
   * The feature id for the '<em><b>Package</b></em>' container reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int XENUM__PACKAGE = XDATA_TYPE__PACKAGE;

  /**
   * The feature id for the '<em><b>Type Parameters</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int XENUM__TYPE_PARAMETERS = XDATA_TYPE__TYPE_PARAMETERS;

  /**
   * The feature id for the '<em><b>Serializable</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int XENUM__SERIALIZABLE = XDATA_TYPE__SERIALIZABLE;

  /**
   * The feature id for the '<em><b>Create Body</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int XENUM__CREATE_BODY = XDATA_TYPE__CREATE_BODY;

  /**
   * The feature id for the '<em><b>Convert Body</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int XENUM__CONVERT_BODY = XDATA_TYPE__CONVERT_BODY;

  /**
   * The feature id for the '<em><b>Literals</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int XENUM__LITERALS = XDATA_TYPE_FEATURE_COUNT + 0;

  /**
   * The number of structural features of the '<em>XEnum</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int XENUM_FEATURE_COUNT = XDATA_TYPE_FEATURE_COUNT + 1;

  /**
   * The meta object id for the '{@link org.eclipse.emf.ecore.xcore.impl.XEnumLiteralImpl <em>XEnum Literal</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.eclipse.emf.ecore.xcore.impl.XEnumLiteralImpl
   * @see org.eclipse.emf.ecore.xcore.impl.XcorePackageImpl#getXEnumLiteral()
   * @generated
   */
  int XENUM_LITERAL = 7;

  /**
   * The feature id for the '<em><b>Annotations</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int XENUM_LITERAL__ANNOTATIONS = XNAMED_ELEMENT__ANNOTATIONS;

  /**
   * The feature id for the '<em><b>Name</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int XENUM_LITERAL__NAME = XNAMED_ELEMENT__NAME;

  /**
   * The feature id for the '<em><b>Value</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int XENUM_LITERAL__VALUE = XNAMED_ELEMENT_FEATURE_COUNT + 0;

  /**
   * The feature id for the '<em><b>Literal</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int XENUM_LITERAL__LITERAL = XNAMED_ELEMENT_FEATURE_COUNT + 1;

  /**
   * The feature id for the '<em><b>Enum</b></em>' container reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int XENUM_LITERAL__ENUM = XNAMED_ELEMENT_FEATURE_COUNT + 2;

  /**
   * The number of structural features of the '<em>XEnum Literal</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int XENUM_LITERAL_FEATURE_COUNT = XNAMED_ELEMENT_FEATURE_COUNT + 3;

  /**
   * The meta object id for the '{@link org.eclipse.emf.ecore.xcore.impl.XGenericTypeImpl <em>XGeneric Type</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.eclipse.emf.ecore.xcore.impl.XGenericTypeImpl
   * @see org.eclipse.emf.ecore.xcore.impl.XcorePackageImpl#getXGenericType()
   * @generated
   */
  int XGENERIC_TYPE = 8;

  /**
   * The feature id for the '<em><b>Upper Bound</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int XGENERIC_TYPE__UPPER_BOUND = 0;

  /**
   * The feature id for the '<em><b>Type Arguments</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int XGENERIC_TYPE__TYPE_ARGUMENTS = 1;

  /**
   * The feature id for the '<em><b>Lower Bound</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int XGENERIC_TYPE__LOWER_BOUND = 2;

  /**
   * The feature id for the '<em><b>Type</b></em>' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int XGENERIC_TYPE__TYPE = 3;

  /**
   * The number of structural features of the '<em>XGeneric Type</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int XGENERIC_TYPE_FEATURE_COUNT = 4;

  /**
   * The meta object id for the '{@link org.eclipse.emf.ecore.xcore.impl.XImportDirectiveImpl <em>XImport Directive</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.eclipse.emf.ecore.xcore.impl.XImportDirectiveImpl
   * @see org.eclipse.emf.ecore.xcore.impl.XcorePackageImpl#getXImportDirective()
   * @generated
   */
  int XIMPORT_DIRECTIVE = 9;

  /**
   * The feature id for the '<em><b>Annotations</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int XIMPORT_DIRECTIVE__ANNOTATIONS = XMODEL_ELEMENT__ANNOTATIONS;

  /**
   * The feature id for the '<em><b>Imported Namespace</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int XIMPORT_DIRECTIVE__IMPORTED_NAMESPACE = XMODEL_ELEMENT_FEATURE_COUNT + 0;

  /**
   * The feature id for the '<em><b>Imported Object</b></em>' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int XIMPORT_DIRECTIVE__IMPORTED_OBJECT = XMODEL_ELEMENT_FEATURE_COUNT + 1;

  /**
   * The feature id for the '<em><b>Package</b></em>' container reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int XIMPORT_DIRECTIVE__PACKAGE = XMODEL_ELEMENT_FEATURE_COUNT + 2;

  /**
   * The number of structural features of the '<em>XImport Directive</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int XIMPORT_DIRECTIVE_FEATURE_COUNT = XMODEL_ELEMENT_FEATURE_COUNT + 3;

  /**
   * The meta object id for the '{@link org.eclipse.emf.ecore.xcore.impl.XOperationImpl <em>XOperation</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.eclipse.emf.ecore.xcore.impl.XOperationImpl
   * @see org.eclipse.emf.ecore.xcore.impl.XcorePackageImpl#getXOperation()
   * @generated
   */
  int XOPERATION = 13;

  /**
   * The feature id for the '<em><b>Annotations</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int XOPERATION__ANNOTATIONS = XMEMBER__ANNOTATIONS;

  /**
   * The feature id for the '<em><b>Name</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int XOPERATION__NAME = XMEMBER__NAME;

  /**
   * The feature id for the '<em><b>Unordered</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int XOPERATION__UNORDERED = XMEMBER__UNORDERED;

  /**
   * The feature id for the '<em><b>Unique</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int XOPERATION__UNIQUE = XMEMBER__UNIQUE;

  /**
   * The feature id for the '<em><b>Type</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int XOPERATION__TYPE = XMEMBER__TYPE;

  /**
   * The feature id for the '<em><b>Multiplicity</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int XOPERATION__MULTIPLICITY = XMEMBER__MULTIPLICITY;

  /**
   * The feature id for the '<em><b>Containing Class</b></em>' container reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int XOPERATION__CONTAINING_CLASS = XMEMBER__CONTAINING_CLASS;

  /**
   * The feature id for the '<em><b>Type Parameters</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int XOPERATION__TYPE_PARAMETERS = XMEMBER_FEATURE_COUNT + 0;

  /**
   * The feature id for the '<em><b>Parameters</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int XOPERATION__PARAMETERS = XMEMBER_FEATURE_COUNT + 1;

  /**
   * The feature id for the '<em><b>Exceptions</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int XOPERATION__EXCEPTIONS = XMEMBER_FEATURE_COUNT + 2;

  /**
   * The feature id for the '<em><b>Body</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int XOPERATION__BODY = XMEMBER_FEATURE_COUNT + 3;

  /**
   * The number of structural features of the '<em>XOperation</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int XOPERATION_FEATURE_COUNT = XMEMBER_FEATURE_COUNT + 4;

  /**
   * The meta object id for the '{@link org.eclipse.emf.ecore.xcore.impl.XPackageImpl <em>XPackage</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.eclipse.emf.ecore.xcore.impl.XPackageImpl
   * @see org.eclipse.emf.ecore.xcore.impl.XcorePackageImpl#getXPackage()
   * @generated
   */
  int XPACKAGE = 14;

  /**
   * The feature id for the '<em><b>Annotations</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int XPACKAGE__ANNOTATIONS = XNAMED_ELEMENT__ANNOTATIONS;

  /**
   * The feature id for the '<em><b>Name</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int XPACKAGE__NAME = XNAMED_ELEMENT__NAME;

  /**
   * The feature id for the '<em><b>Import Directives</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int XPACKAGE__IMPORT_DIRECTIVES = XNAMED_ELEMENT_FEATURE_COUNT + 0;

  /**
   * The feature id for the '<em><b>Annotation Directives</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int XPACKAGE__ANNOTATION_DIRECTIVES = XNAMED_ELEMENT_FEATURE_COUNT + 1;

  /**
   * The feature id for the '<em><b>Classifiers</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int XPACKAGE__CLASSIFIERS = XNAMED_ELEMENT_FEATURE_COUNT + 2;

  /**
   * The number of structural features of the '<em>XPackage</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int XPACKAGE_FEATURE_COUNT = XNAMED_ELEMENT_FEATURE_COUNT + 3;

  /**
   * The meta object id for the '{@link org.eclipse.emf.ecore.xcore.impl.XParameterImpl <em>XParameter</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.eclipse.emf.ecore.xcore.impl.XParameterImpl
   * @see org.eclipse.emf.ecore.xcore.impl.XcorePackageImpl#getXParameter()
   * @generated
   */
  int XPARAMETER = 15;

  /**
   * The feature id for the '<em><b>Annotations</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int XPARAMETER__ANNOTATIONS = XTYPED_ELEMENT__ANNOTATIONS;

  /**
   * The feature id for the '<em><b>Name</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int XPARAMETER__NAME = XTYPED_ELEMENT__NAME;

  /**
   * The feature id for the '<em><b>Unordered</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int XPARAMETER__UNORDERED = XTYPED_ELEMENT__UNORDERED;

  /**
   * The feature id for the '<em><b>Unique</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int XPARAMETER__UNIQUE = XTYPED_ELEMENT__UNIQUE;

  /**
   * The feature id for the '<em><b>Type</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int XPARAMETER__TYPE = XTYPED_ELEMENT__TYPE;

  /**
   * The feature id for the '<em><b>Multiplicity</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int XPARAMETER__MULTIPLICITY = XTYPED_ELEMENT__MULTIPLICITY;

  /**
   * The feature id for the '<em><b>Operation</b></em>' container reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int XPARAMETER__OPERATION = XTYPED_ELEMENT_FEATURE_COUNT + 0;

  /**
   * The number of structural features of the '<em>XParameter</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int XPARAMETER_FEATURE_COUNT = XTYPED_ELEMENT_FEATURE_COUNT + 1;

  /**
   * The meta object id for the '{@link org.eclipse.emf.ecore.xcore.impl.XReferenceImpl <em>XReference</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.eclipse.emf.ecore.xcore.impl.XReferenceImpl
   * @see org.eclipse.emf.ecore.xcore.impl.XcorePackageImpl#getXReference()
   * @generated
   */
  int XREFERENCE = 16;

  /**
   * The feature id for the '<em><b>Annotations</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int XREFERENCE__ANNOTATIONS = XSTRUCTURAL_FEATURE__ANNOTATIONS;

  /**
   * The feature id for the '<em><b>Name</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int XREFERENCE__NAME = XSTRUCTURAL_FEATURE__NAME;

  /**
   * The feature id for the '<em><b>Unordered</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int XREFERENCE__UNORDERED = XSTRUCTURAL_FEATURE__UNORDERED;

  /**
   * The feature id for the '<em><b>Unique</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int XREFERENCE__UNIQUE = XSTRUCTURAL_FEATURE__UNIQUE;

  /**
   * The feature id for the '<em><b>Type</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int XREFERENCE__TYPE = XSTRUCTURAL_FEATURE__TYPE;

  /**
   * The feature id for the '<em><b>Multiplicity</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int XREFERENCE__MULTIPLICITY = XSTRUCTURAL_FEATURE__MULTIPLICITY;

  /**
   * The feature id for the '<em><b>Containing Class</b></em>' container reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int XREFERENCE__CONTAINING_CLASS = XSTRUCTURAL_FEATURE__CONTAINING_CLASS;

  /**
   * The feature id for the '<em><b>Readonly</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int XREFERENCE__READONLY = XSTRUCTURAL_FEATURE__READONLY;

  /**
   * The feature id for the '<em><b>Volatile</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int XREFERENCE__VOLATILE = XSTRUCTURAL_FEATURE__VOLATILE;

  /**
   * The feature id for the '<em><b>Transient</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int XREFERENCE__TRANSIENT = XSTRUCTURAL_FEATURE__TRANSIENT;

  /**
   * The feature id for the '<em><b>Unsettable</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int XREFERENCE__UNSETTABLE = XSTRUCTURAL_FEATURE__UNSETTABLE;

  /**
   * The feature id for the '<em><b>Derived</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int XREFERENCE__DERIVED = XSTRUCTURAL_FEATURE__DERIVED;

  /**
   * The feature id for the '<em><b>Get Body</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int XREFERENCE__GET_BODY = XSTRUCTURAL_FEATURE__GET_BODY;

  /**
   * The feature id for the '<em><b>Set Body</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int XREFERENCE__SET_BODY = XSTRUCTURAL_FEATURE__SET_BODY;

  /**
   * The feature id for the '<em><b>Is Set Body</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int XREFERENCE__IS_SET_BODY = XSTRUCTURAL_FEATURE__IS_SET_BODY;

  /**
   * The feature id for the '<em><b>Unset Body</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int XREFERENCE__UNSET_BODY = XSTRUCTURAL_FEATURE__UNSET_BODY;

  /**
   * The feature id for the '<em><b>Container</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int XREFERENCE__CONTAINER = XSTRUCTURAL_FEATURE_FEATURE_COUNT + 0;

  /**
   * The feature id for the '<em><b>Containment</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int XREFERENCE__CONTAINMENT = XSTRUCTURAL_FEATURE_FEATURE_COUNT + 1;

  /**
   * The feature id for the '<em><b>Resolve Proxies</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int XREFERENCE__RESOLVE_PROXIES = XSTRUCTURAL_FEATURE_FEATURE_COUNT + 2;

  /**
   * The feature id for the '<em><b>Local</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int XREFERENCE__LOCAL = XSTRUCTURAL_FEATURE_FEATURE_COUNT + 3;

  /**
   * The feature id for the '<em><b>Opposite</b></em>' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int XREFERENCE__OPPOSITE = XSTRUCTURAL_FEATURE_FEATURE_COUNT + 4;

  /**
   * The feature id for the '<em><b>Keys</b></em>' reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int XREFERENCE__KEYS = XSTRUCTURAL_FEATURE_FEATURE_COUNT + 5;

  /**
   * The number of structural features of the '<em>XReference</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int XREFERENCE_FEATURE_COUNT = XSTRUCTURAL_FEATURE_FEATURE_COUNT + 6;

  /**
   * The meta object id for the '{@link org.eclipse.emf.ecore.xcore.impl.XStringToStringMapEntryImpl <em>XString To String Map Entry</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.eclipse.emf.ecore.xcore.impl.XStringToStringMapEntryImpl
   * @see org.eclipse.emf.ecore.xcore.impl.XcorePackageImpl#getXStringToStringMapEntry()
   * @generated
   */
  int XSTRING_TO_STRING_MAP_ENTRY = 17;

  /**
   * The feature id for the '<em><b>Key</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int XSTRING_TO_STRING_MAP_ENTRY__KEY = 0;

  /**
   * The feature id for the '<em><b>Value</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int XSTRING_TO_STRING_MAP_ENTRY__VALUE = 1;

  /**
   * The number of structural features of the '<em>XString To String Map Entry</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int XSTRING_TO_STRING_MAP_ENTRY_FEATURE_COUNT = 2;

  /**
   * The meta object id for the '{@link org.eclipse.emf.ecore.xcore.impl.XTypeParameterImpl <em>XType Parameter</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.eclipse.emf.ecore.xcore.impl.XTypeParameterImpl
   * @see org.eclipse.emf.ecore.xcore.impl.XcorePackageImpl#getXTypeParameter()
   * @generated
   */
  int XTYPE_PARAMETER = 20;

  /**
   * The feature id for the '<em><b>Annotations</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int XTYPE_PARAMETER__ANNOTATIONS = XNAMED_ELEMENT__ANNOTATIONS;

  /**
   * The feature id for the '<em><b>Name</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int XTYPE_PARAMETER__NAME = XNAMED_ELEMENT__NAME;

  /**
   * The feature id for the '<em><b>Bounds</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int XTYPE_PARAMETER__BOUNDS = XNAMED_ELEMENT_FEATURE_COUNT + 0;

  /**
   * The number of structural features of the '<em>XType Parameter</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int XTYPE_PARAMETER_FEATURE_COUNT = XNAMED_ELEMENT_FEATURE_COUNT + 1;

  /**
   * The meta object id for the '<em>XMultiplicity</em>' data type.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.eclipse.emf.ecore.xcore.impl.XcorePackageImpl#getXMultiplicity()
   * @generated
   */
  int XMULTIPLICITY = 21;

  /**
   * Returns the meta object for class '{@link org.eclipse.emf.ecore.xcore.XAnnotation <em>XAnnotation</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>XAnnotation</em>'.
   * @see org.eclipse.emf.ecore.xcore.XAnnotation
   * @generated
   */
  EClass getXAnnotation();

  /**
   * Returns the meta object for the reference '{@link org.eclipse.emf.ecore.xcore.XAnnotation#getSource <em>Source</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the reference '<em>Source</em>'.
   * @see org.eclipse.emf.ecore.xcore.XAnnotation#getSource()
   * @see #getXAnnotation()
   * @generated
   */
  EReference getXAnnotation_Source();

  /**
   * Returns the meta object for the map '{@link org.eclipse.emf.ecore.xcore.XAnnotation#getDetails <em>Details</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the map '<em>Details</em>'.
   * @see org.eclipse.emf.ecore.xcore.XAnnotation#getDetails()
   * @see #getXAnnotation()
   * @generated
   */
  EReference getXAnnotation_Details();

  /**
   * Returns the meta object for the container reference '{@link org.eclipse.emf.ecore.xcore.XAnnotation#getModelElement <em>Model Element</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the container reference '<em>Model Element</em>'.
   * @see org.eclipse.emf.ecore.xcore.XAnnotation#getModelElement()
   * @see #getXAnnotation()
   * @generated
   */
  EReference getXAnnotation_ModelElement();

  /**
   * Returns the meta object for class '{@link org.eclipse.emf.ecore.xcore.XAnnotationDirective <em>XAnnotation Directive</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>XAnnotation Directive</em>'.
   * @see org.eclipse.emf.ecore.xcore.XAnnotationDirective
   * @generated
   */
  EClass getXAnnotationDirective();

  /**
   * Returns the meta object for the attribute '{@link org.eclipse.emf.ecore.xcore.XAnnotationDirective#getSourceURI <em>Source URI</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Source URI</em>'.
   * @see org.eclipse.emf.ecore.xcore.XAnnotationDirective#getSourceURI()
   * @see #getXAnnotationDirective()
   * @generated
   */
  EAttribute getXAnnotationDirective_SourceURI();

  /**
   * Returns the meta object for the container reference '{@link org.eclipse.emf.ecore.xcore.XAnnotationDirective#getPackage <em>Package</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the container reference '<em>Package</em>'.
   * @see org.eclipse.emf.ecore.xcore.XAnnotationDirective#getPackage()
   * @see #getXAnnotationDirective()
   * @generated
   */
  EReference getXAnnotationDirective_Package();

  /**
   * Returns the meta object for class '{@link org.eclipse.emf.ecore.xcore.XAttribute <em>XAttribute</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>XAttribute</em>'.
   * @see org.eclipse.emf.ecore.xcore.XAttribute
   * @generated
   */
  EClass getXAttribute();

  /**
   * Returns the meta object for the attribute '{@link org.eclipse.emf.ecore.xcore.XAttribute#getDefaultValueLiteral <em>Default Value Literal</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Default Value Literal</em>'.
   * @see org.eclipse.emf.ecore.xcore.XAttribute#getDefaultValueLiteral()
   * @see #getXAttribute()
   * @generated
   */
  EAttribute getXAttribute_DefaultValueLiteral();

  /**
   * Returns the meta object for the attribute '{@link org.eclipse.emf.ecore.xcore.XAttribute#isID <em>ID</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>ID</em>'.
   * @see org.eclipse.emf.ecore.xcore.XAttribute#isID()
   * @see #getXAttribute()
   * @generated
   */
  EAttribute getXAttribute_ID();

  /**
   * Returns the meta object for class '{@link org.eclipse.emf.ecore.xcore.XClass <em>XClass</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>XClass</em>'.
   * @see org.eclipse.emf.ecore.xcore.XClass
   * @generated
   */
  EClass getXClass();

  /**
   * Returns the meta object for the attribute '{@link org.eclipse.emf.ecore.xcore.XClass#isAbstract <em>Abstract</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Abstract</em>'.
   * @see org.eclipse.emf.ecore.xcore.XClass#isAbstract()
   * @see #getXClass()
   * @generated
   */
  EAttribute getXClass_Abstract();

  /**
   * Returns the meta object for the attribute '{@link org.eclipse.emf.ecore.xcore.XClass#isInterface <em>Interface</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Interface</em>'.
   * @see org.eclipse.emf.ecore.xcore.XClass#isInterface()
   * @see #getXClass()
   * @generated
   */
  EAttribute getXClass_Interface();

  /**
   * Returns the meta object for the containment reference list '{@link org.eclipse.emf.ecore.xcore.XClass#getMembers <em>Members</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Members</em>'.
   * @see org.eclipse.emf.ecore.xcore.XClass#getMembers()
   * @see #getXClass()
   * @generated
   */
  EReference getXClass_Members();

  /**
   * Returns the meta object for the containment reference list '{@link org.eclipse.emf.ecore.xcore.XClass#getSuperTypes <em>Super Types</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Super Types</em>'.
   * @see org.eclipse.emf.ecore.xcore.XClass#getSuperTypes()
   * @see #getXClass()
   * @generated
   */
  EReference getXClass_SuperTypes();

  /**
   * Returns the meta object for class '{@link org.eclipse.emf.ecore.xcore.XClassifier <em>XClassifier</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>XClassifier</em>'.
   * @see org.eclipse.emf.ecore.xcore.XClassifier
   * @generated
   */
  EClass getXClassifier();

  /**
   * Returns the meta object for the containment reference '{@link org.eclipse.emf.ecore.xcore.XClassifier#getInstanceType <em>Instance Type</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Instance Type</em>'.
   * @see org.eclipse.emf.ecore.xcore.XClassifier#getInstanceType()
   * @see #getXClassifier()
   * @generated
   */
  EReference getXClassifier_InstanceType();

  /**
   * Returns the meta object for the container reference '{@link org.eclipse.emf.ecore.xcore.XClassifier#getPackage <em>Package</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the container reference '<em>Package</em>'.
   * @see org.eclipse.emf.ecore.xcore.XClassifier#getPackage()
   * @see #getXClassifier()
   * @generated
   */
  EReference getXClassifier_Package();

  /**
   * Returns the meta object for the containment reference list '{@link org.eclipse.emf.ecore.xcore.XClassifier#getTypeParameters <em>Type Parameters</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Type Parameters</em>'.
   * @see org.eclipse.emf.ecore.xcore.XClassifier#getTypeParameters()
   * @see #getXClassifier()
   * @generated
   */
  EReference getXClassifier_TypeParameters();

  /**
   * Returns the meta object for class '{@link org.eclipse.emf.ecore.xcore.XDataType <em>XData Type</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>XData Type</em>'.
   * @see org.eclipse.emf.ecore.xcore.XDataType
   * @generated
   */
  EClass getXDataType();

  /**
   * Returns the meta object for the attribute '{@link org.eclipse.emf.ecore.xcore.XDataType#isSerializable <em>Serializable</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Serializable</em>'.
   * @see org.eclipse.emf.ecore.xcore.XDataType#isSerializable()
   * @see #getXDataType()
   * @generated
   */
  EAttribute getXDataType_Serializable();

  /**
   * Returns the meta object for the containment reference '{@link org.eclipse.emf.ecore.xcore.XDataType#getCreateBody <em>Create Body</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Create Body</em>'.
   * @see org.eclipse.emf.ecore.xcore.XDataType#getCreateBody()
   * @see #getXDataType()
   * @generated
   */
  EReference getXDataType_CreateBody();

  /**
   * Returns the meta object for the containment reference '{@link org.eclipse.emf.ecore.xcore.XDataType#getConvertBody <em>Convert Body</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Convert Body</em>'.
   * @see org.eclipse.emf.ecore.xcore.XDataType#getConvertBody()
   * @see #getXDataType()
   * @generated
   */
  EReference getXDataType_ConvertBody();

  /**
   * Returns the meta object for class '{@link org.eclipse.emf.ecore.xcore.XEnum <em>XEnum</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>XEnum</em>'.
   * @see org.eclipse.emf.ecore.xcore.XEnum
   * @generated
   */
  EClass getXEnum();

  /**
   * Returns the meta object for the containment reference list '{@link org.eclipse.emf.ecore.xcore.XEnum#getLiterals <em>Literals</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Literals</em>'.
   * @see org.eclipse.emf.ecore.xcore.XEnum#getLiterals()
   * @see #getXEnum()
   * @generated
   */
  EReference getXEnum_Literals();

  /**
   * Returns the meta object for class '{@link org.eclipse.emf.ecore.xcore.XEnumLiteral <em>XEnum Literal</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>XEnum Literal</em>'.
   * @see org.eclipse.emf.ecore.xcore.XEnumLiteral
   * @generated
   */
  EClass getXEnumLiteral();

  /**
   * Returns the meta object for the attribute '{@link org.eclipse.emf.ecore.xcore.XEnumLiteral#getValue <em>Value</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Value</em>'.
   * @see org.eclipse.emf.ecore.xcore.XEnumLiteral#getValue()
   * @see #getXEnumLiteral()
   * @generated
   */
  EAttribute getXEnumLiteral_Value();

  /**
   * Returns the meta object for the attribute '{@link org.eclipse.emf.ecore.xcore.XEnumLiteral#getLiteral <em>Literal</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Literal</em>'.
   * @see org.eclipse.emf.ecore.xcore.XEnumLiteral#getLiteral()
   * @see #getXEnumLiteral()
   * @generated
   */
  EAttribute getXEnumLiteral_Literal();

  /**
   * Returns the meta object for the container reference '{@link org.eclipse.emf.ecore.xcore.XEnumLiteral#getEnum <em>Enum</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the container reference '<em>Enum</em>'.
   * @see org.eclipse.emf.ecore.xcore.XEnumLiteral#getEnum()
   * @see #getXEnumLiteral()
   * @generated
   */
  EReference getXEnumLiteral_Enum();

  /**
   * Returns the meta object for class '{@link org.eclipse.emf.ecore.xcore.XGenericType <em>XGeneric Type</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>XGeneric Type</em>'.
   * @see org.eclipse.emf.ecore.xcore.XGenericType
   * @generated
   */
  EClass getXGenericType();

  /**
   * Returns the meta object for the containment reference '{@link org.eclipse.emf.ecore.xcore.XGenericType#getUpperBound <em>Upper Bound</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Upper Bound</em>'.
   * @see org.eclipse.emf.ecore.xcore.XGenericType#getUpperBound()
   * @see #getXGenericType()
   * @generated
   */
  EReference getXGenericType_UpperBound();

  /**
   * Returns the meta object for the containment reference list '{@link org.eclipse.emf.ecore.xcore.XGenericType#getTypeArguments <em>Type Arguments</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Type Arguments</em>'.
   * @see org.eclipse.emf.ecore.xcore.XGenericType#getTypeArguments()
   * @see #getXGenericType()
   * @generated
   */
  EReference getXGenericType_TypeArguments();

  /**
   * Returns the meta object for the containment reference '{@link org.eclipse.emf.ecore.xcore.XGenericType#getLowerBound <em>Lower Bound</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Lower Bound</em>'.
   * @see org.eclipse.emf.ecore.xcore.XGenericType#getLowerBound()
   * @see #getXGenericType()
   * @generated
   */
  EReference getXGenericType_LowerBound();

  /**
   * Returns the meta object for the reference '{@link org.eclipse.emf.ecore.xcore.XGenericType#getType <em>Type</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the reference '<em>Type</em>'.
   * @see org.eclipse.emf.ecore.xcore.XGenericType#getType()
   * @see #getXGenericType()
   * @generated
   */
  EReference getXGenericType_Type();

  /**
   * Returns the meta object for class '{@link org.eclipse.emf.ecore.xcore.XImportDirective <em>XImport Directive</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>XImport Directive</em>'.
   * @see org.eclipse.emf.ecore.xcore.XImportDirective
   * @generated
   */
  EClass getXImportDirective();

  /**
   * Returns the meta object for the attribute '{@link org.eclipse.emf.ecore.xcore.XImportDirective#getImportedNamespace <em>Imported Namespace</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Imported Namespace</em>'.
   * @see org.eclipse.emf.ecore.xcore.XImportDirective#getImportedNamespace()
   * @see #getXImportDirective()
   * @generated
   */
  EAttribute getXImportDirective_ImportedNamespace();

  /**
   * Returns the meta object for the reference '{@link org.eclipse.emf.ecore.xcore.XImportDirective#getImportedObject <em>Imported Object</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the reference '<em>Imported Object</em>'.
   * @see org.eclipse.emf.ecore.xcore.XImportDirective#getImportedObject()
   * @see #getXImportDirective()
   * @generated
   */
  EReference getXImportDirective_ImportedObject();

  /**
   * Returns the meta object for the container reference '{@link org.eclipse.emf.ecore.xcore.XImportDirective#getPackage <em>Package</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the container reference '<em>Package</em>'.
   * @see org.eclipse.emf.ecore.xcore.XImportDirective#getPackage()
   * @see #getXImportDirective()
   * @generated
   */
  EReference getXImportDirective_Package();

  /**
   * Returns the meta object for class '{@link org.eclipse.emf.ecore.xcore.XMember <em>XMember</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>XMember</em>'.
   * @see org.eclipse.emf.ecore.xcore.XMember
   * @generated
   */
  EClass getXMember();

  /**
   * Returns the meta object for the container reference '{@link org.eclipse.emf.ecore.xcore.XMember#getContainingClass <em>Containing Class</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the container reference '<em>Containing Class</em>'.
   * @see org.eclipse.emf.ecore.xcore.XMember#getContainingClass()
   * @see #getXMember()
   * @generated
   */
  EReference getXMember_ContainingClass();

  /**
   * Returns the meta object for class '{@link org.eclipse.emf.ecore.xcore.XModelElement <em>XModel Element</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>XModel Element</em>'.
   * @see org.eclipse.emf.ecore.xcore.XModelElement
   * @generated
   */
  EClass getXModelElement();

  /**
   * Returns the meta object for the containment reference list '{@link org.eclipse.emf.ecore.xcore.XModelElement#getAnnotations <em>Annotations</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Annotations</em>'.
   * @see org.eclipse.emf.ecore.xcore.XModelElement#getAnnotations()
   * @see #getXModelElement()
   * @generated
   */
  EReference getXModelElement_Annotations();

  /**
   * Returns the meta object for class '{@link org.eclipse.emf.ecore.xcore.XNamedElement <em>XNamed Element</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>XNamed Element</em>'.
   * @see org.eclipse.emf.ecore.xcore.XNamedElement
   * @generated
   */
  EClass getXNamedElement();

  /**
   * Returns the meta object for the attribute '{@link org.eclipse.emf.ecore.xcore.XNamedElement#getName <em>Name</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Name</em>'.
   * @see org.eclipse.emf.ecore.xcore.XNamedElement#getName()
   * @see #getXNamedElement()
   * @generated
   */
  EAttribute getXNamedElement_Name();

  /**
   * Returns the meta object for class '{@link org.eclipse.emf.ecore.xcore.XOperation <em>XOperation</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>XOperation</em>'.
   * @see org.eclipse.emf.ecore.xcore.XOperation
   * @generated
   */
  EClass getXOperation();

  /**
   * Returns the meta object for the containment reference list '{@link org.eclipse.emf.ecore.xcore.XOperation#getTypeParameters <em>Type Parameters</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Type Parameters</em>'.
   * @see org.eclipse.emf.ecore.xcore.XOperation#getTypeParameters()
   * @see #getXOperation()
   * @generated
   */
  EReference getXOperation_TypeParameters();

  /**
   * Returns the meta object for the containment reference list '{@link org.eclipse.emf.ecore.xcore.XOperation#getParameters <em>Parameters</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Parameters</em>'.
   * @see org.eclipse.emf.ecore.xcore.XOperation#getParameters()
   * @see #getXOperation()
   * @generated
   */
  EReference getXOperation_Parameters();

  /**
   * Returns the meta object for the containment reference list '{@link org.eclipse.emf.ecore.xcore.XOperation#getExceptions <em>Exceptions</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Exceptions</em>'.
   * @see org.eclipse.emf.ecore.xcore.XOperation#getExceptions()
   * @see #getXOperation()
   * @generated
   */
  EReference getXOperation_Exceptions();

  /**
   * Returns the meta object for the containment reference '{@link org.eclipse.emf.ecore.xcore.XOperation#getBody <em>Body</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Body</em>'.
   * @see org.eclipse.emf.ecore.xcore.XOperation#getBody()
   * @see #getXOperation()
   * @generated
   */
  EReference getXOperation_Body();

  /**
   * Returns the meta object for class '{@link org.eclipse.emf.ecore.xcore.XPackage <em>XPackage</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>XPackage</em>'.
   * @see org.eclipse.emf.ecore.xcore.XPackage
   * @generated
   */
  EClass getXPackage();

  /**
   * Returns the meta object for the containment reference list '{@link org.eclipse.emf.ecore.xcore.XPackage#getImportDirectives <em>Import Directives</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Import Directives</em>'.
   * @see org.eclipse.emf.ecore.xcore.XPackage#getImportDirectives()
   * @see #getXPackage()
   * @generated
   */
  EReference getXPackage_ImportDirectives();

  /**
   * Returns the meta object for the containment reference list '{@link org.eclipse.emf.ecore.xcore.XPackage#getAnnotationDirectives <em>Annotation Directives</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Annotation Directives</em>'.
   * @see org.eclipse.emf.ecore.xcore.XPackage#getAnnotationDirectives()
   * @see #getXPackage()
   * @generated
   */
  EReference getXPackage_AnnotationDirectives();

  /**
   * Returns the meta object for the containment reference list '{@link org.eclipse.emf.ecore.xcore.XPackage#getClassifiers <em>Classifiers</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Classifiers</em>'.
   * @see org.eclipse.emf.ecore.xcore.XPackage#getClassifiers()
   * @see #getXPackage()
   * @generated
   */
  EReference getXPackage_Classifiers();

  /**
   * Returns the meta object for class '{@link org.eclipse.emf.ecore.xcore.XParameter <em>XParameter</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>XParameter</em>'.
   * @see org.eclipse.emf.ecore.xcore.XParameter
   * @generated
   */
  EClass getXParameter();

  /**
   * Returns the meta object for the container reference '{@link org.eclipse.emf.ecore.xcore.XParameter#getOperation <em>Operation</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the container reference '<em>Operation</em>'.
   * @see org.eclipse.emf.ecore.xcore.XParameter#getOperation()
   * @see #getXParameter()
   * @generated
   */
  EReference getXParameter_Operation();

  /**
   * Returns the meta object for class '{@link org.eclipse.emf.ecore.xcore.XReference <em>XReference</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>XReference</em>'.
   * @see org.eclipse.emf.ecore.xcore.XReference
   * @generated
   */
  EClass getXReference();

  /**
   * Returns the meta object for the attribute '{@link org.eclipse.emf.ecore.xcore.XReference#isContainer <em>Container</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Container</em>'.
   * @see org.eclipse.emf.ecore.xcore.XReference#isContainer()
   * @see #getXReference()
   * @generated
   */
  EAttribute getXReference_Container();

  /**
   * Returns the meta object for the attribute '{@link org.eclipse.emf.ecore.xcore.XReference#isContainment <em>Containment</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Containment</em>'.
   * @see org.eclipse.emf.ecore.xcore.XReference#isContainment()
   * @see #getXReference()
   * @generated
   */
  EAttribute getXReference_Containment();

  /**
   * Returns the meta object for the attribute '{@link org.eclipse.emf.ecore.xcore.XReference#isResolveProxies <em>Resolve Proxies</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Resolve Proxies</em>'.
   * @see org.eclipse.emf.ecore.xcore.XReference#isResolveProxies()
   * @see #getXReference()
   * @generated
   */
  EAttribute getXReference_ResolveProxies();

  /**
   * Returns the meta object for the attribute '{@link org.eclipse.emf.ecore.xcore.XReference#isLocal <em>Local</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Local</em>'.
   * @see org.eclipse.emf.ecore.xcore.XReference#isLocal()
   * @see #getXReference()
   * @generated
   */
  EAttribute getXReference_Local();

  /**
   * Returns the meta object for the reference '{@link org.eclipse.emf.ecore.xcore.XReference#getOpposite <em>Opposite</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the reference '<em>Opposite</em>'.
   * @see org.eclipse.emf.ecore.xcore.XReference#getOpposite()
   * @see #getXReference()
   * @generated
   */
  EReference getXReference_Opposite();

  /**
   * Returns the meta object for the reference list '{@link org.eclipse.emf.ecore.xcore.XReference#getKeys <em>Keys</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the reference list '<em>Keys</em>'.
   * @see org.eclipse.emf.ecore.xcore.XReference#getKeys()
   * @see #getXReference()
   * @generated
   */
  EReference getXReference_Keys();

  /**
   * Returns the meta object for class '{@link java.util.Map.Entry <em>XString To String Map Entry</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>XString To String Map Entry</em>'.
   * @see java.util.Map.Entry
   * @model keyDataType="org.eclipse.emf.ecore.EString"
   *        valueDataType="org.eclipse.emf.ecore.EString"
   * @generated
   */
  EClass getXStringToStringMapEntry();

  /**
   * Returns the meta object for the attribute '{@link java.util.Map.Entry <em>Key</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Key</em>'.
   * @see java.util.Map.Entry
   * @see #getXStringToStringMapEntry()
   * @generated
   */
  EAttribute getXStringToStringMapEntry_Key();

  /**
   * Returns the meta object for the attribute '{@link java.util.Map.Entry <em>Value</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Value</em>'.
   * @see java.util.Map.Entry
   * @see #getXStringToStringMapEntry()
   * @generated
   */
  EAttribute getXStringToStringMapEntry_Value();

  /**
   * Returns the meta object for class '{@link org.eclipse.emf.ecore.xcore.XStructuralFeature <em>XStructural Feature</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>XStructural Feature</em>'.
   * @see org.eclipse.emf.ecore.xcore.XStructuralFeature
   * @generated
   */
  EClass getXStructuralFeature();

  /**
   * Returns the meta object for the attribute '{@link org.eclipse.emf.ecore.xcore.XStructuralFeature#isReadonly <em>Readonly</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Readonly</em>'.
   * @see org.eclipse.emf.ecore.xcore.XStructuralFeature#isReadonly()
   * @see #getXStructuralFeature()
   * @generated
   */
  EAttribute getXStructuralFeature_Readonly();

  /**
   * Returns the meta object for the attribute '{@link org.eclipse.emf.ecore.xcore.XStructuralFeature#isVolatile <em>Volatile</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Volatile</em>'.
   * @see org.eclipse.emf.ecore.xcore.XStructuralFeature#isVolatile()
   * @see #getXStructuralFeature()
   * @generated
   */
  EAttribute getXStructuralFeature_Volatile();

  /**
   * Returns the meta object for the attribute '{@link org.eclipse.emf.ecore.xcore.XStructuralFeature#isTransient <em>Transient</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Transient</em>'.
   * @see org.eclipse.emf.ecore.xcore.XStructuralFeature#isTransient()
   * @see #getXStructuralFeature()
   * @generated
   */
  EAttribute getXStructuralFeature_Transient();

  /**
   * Returns the meta object for the attribute '{@link org.eclipse.emf.ecore.xcore.XStructuralFeature#isUnsettable <em>Unsettable</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Unsettable</em>'.
   * @see org.eclipse.emf.ecore.xcore.XStructuralFeature#isUnsettable()
   * @see #getXStructuralFeature()
   * @generated
   */
  EAttribute getXStructuralFeature_Unsettable();

  /**
   * Returns the meta object for the attribute '{@link org.eclipse.emf.ecore.xcore.XStructuralFeature#isDerived <em>Derived</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Derived</em>'.
   * @see org.eclipse.emf.ecore.xcore.XStructuralFeature#isDerived()
   * @see #getXStructuralFeature()
   * @generated
   */
  EAttribute getXStructuralFeature_Derived();

  /**
   * Returns the meta object for the containment reference '{@link org.eclipse.emf.ecore.xcore.XStructuralFeature#getGetBody <em>Get Body</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Get Body</em>'.
   * @see org.eclipse.emf.ecore.xcore.XStructuralFeature#getGetBody()
   * @see #getXStructuralFeature()
   * @generated
   */
  EReference getXStructuralFeature_GetBody();

  /**
   * Returns the meta object for the containment reference '{@link org.eclipse.emf.ecore.xcore.XStructuralFeature#getSetBody <em>Set Body</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Set Body</em>'.
   * @see org.eclipse.emf.ecore.xcore.XStructuralFeature#getSetBody()
   * @see #getXStructuralFeature()
   * @generated
   */
  EReference getXStructuralFeature_SetBody();

  /**
   * Returns the meta object for the containment reference '{@link org.eclipse.emf.ecore.xcore.XStructuralFeature#getIsSetBody <em>Is Set Body</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Is Set Body</em>'.
   * @see org.eclipse.emf.ecore.xcore.XStructuralFeature#getIsSetBody()
   * @see #getXStructuralFeature()
   * @generated
   */
  EReference getXStructuralFeature_IsSetBody();

  /**
   * Returns the meta object for the containment reference '{@link org.eclipse.emf.ecore.xcore.XStructuralFeature#getUnsetBody <em>Unset Body</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Unset Body</em>'.
   * @see org.eclipse.emf.ecore.xcore.XStructuralFeature#getUnsetBody()
   * @see #getXStructuralFeature()
   * @generated
   */
  EReference getXStructuralFeature_UnsetBody();

  /**
   * Returns the meta object for class '{@link org.eclipse.emf.ecore.xcore.XTypedElement <em>XTyped Element</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>XTyped Element</em>'.
   * @see org.eclipse.emf.ecore.xcore.XTypedElement
   * @generated
   */
  EClass getXTypedElement();

  /**
   * Returns the meta object for the attribute '{@link org.eclipse.emf.ecore.xcore.XTypedElement#isUnordered <em>Unordered</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Unordered</em>'.
   * @see org.eclipse.emf.ecore.xcore.XTypedElement#isUnordered()
   * @see #getXTypedElement()
   * @generated
   */
  EAttribute getXTypedElement_Unordered();

  /**
   * Returns the meta object for the attribute '{@link org.eclipse.emf.ecore.xcore.XTypedElement#isUnique <em>Unique</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Unique</em>'.
   * @see org.eclipse.emf.ecore.xcore.XTypedElement#isUnique()
   * @see #getXTypedElement()
   * @generated
   */
  EAttribute getXTypedElement_Unique();

  /**
   * Returns the meta object for the containment reference '{@link org.eclipse.emf.ecore.xcore.XTypedElement#getType <em>Type</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Type</em>'.
   * @see org.eclipse.emf.ecore.xcore.XTypedElement#getType()
   * @see #getXTypedElement()
   * @generated
   */
  EReference getXTypedElement_Type();

  /**
   * Returns the meta object for the attribute '{@link org.eclipse.emf.ecore.xcore.XTypedElement#getMultiplicity <em>Multiplicity</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Multiplicity</em>'.
   * @see org.eclipse.emf.ecore.xcore.XTypedElement#getMultiplicity()
   * @see #getXTypedElement()
   * @generated
   */
  EAttribute getXTypedElement_Multiplicity();

  /**
   * Returns the meta object for class '{@link org.eclipse.emf.ecore.xcore.XTypeParameter <em>XType Parameter</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>XType Parameter</em>'.
   * @see org.eclipse.emf.ecore.xcore.XTypeParameter
   * @generated
   */
  EClass getXTypeParameter();

  /**
   * Returns the meta object for the containment reference list '{@link org.eclipse.emf.ecore.xcore.XTypeParameter#getBounds <em>Bounds</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Bounds</em>'.
   * @see org.eclipse.emf.ecore.xcore.XTypeParameter#getBounds()
   * @see #getXTypeParameter()
   * @generated
   */
  EReference getXTypeParameter_Bounds();

  /**
   * Returns the meta object for data type '<em>XMultiplicity</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for data type '<em>XMultiplicity</em>'.
   * @model instanceClass="int[]"
   * @generated
   */
  EDataType getXMultiplicity();

  /**
   * Returns the factory that creates the instances of the model.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the factory that creates the instances of the model.
   * @generated
   */
  XcoreFactory getXcoreFactory();

  /**
   * <!-- begin-user-doc -->
   * Defines literals for the meta objects that represent
   * <ul>
   *   <li>each class,</li>
   *   <li>each feature of each class,</li>
   *   <li>each enum,</li>
   *   <li>and each data type</li>
   * </ul>
   * <!-- end-user-doc -->
   * @generated
   */
  interface Literals
  {
    /**
     * The meta object literal for the '{@link org.eclipse.emf.ecore.xcore.impl.XAnnotationImpl <em>XAnnotation</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.eclipse.emf.ecore.xcore.impl.XAnnotationImpl
     * @see org.eclipse.emf.ecore.xcore.impl.XcorePackageImpl#getXAnnotation()
     * @generated
     */
    EClass XANNOTATION = eINSTANCE.getXAnnotation();

    /**
     * The meta object literal for the '<em><b>Source</b></em>' reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference XANNOTATION__SOURCE = eINSTANCE.getXAnnotation_Source();

    /**
     * The meta object literal for the '<em><b>Details</b></em>' map feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference XANNOTATION__DETAILS = eINSTANCE.getXAnnotation_Details();

    /**
     * The meta object literal for the '<em><b>Model Element</b></em>' container reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference XANNOTATION__MODEL_ELEMENT = eINSTANCE.getXAnnotation_ModelElement();

    /**
     * The meta object literal for the '{@link org.eclipse.emf.ecore.xcore.impl.XAnnotationDirectiveImpl <em>XAnnotation Directive</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.eclipse.emf.ecore.xcore.impl.XAnnotationDirectiveImpl
     * @see org.eclipse.emf.ecore.xcore.impl.XcorePackageImpl#getXAnnotationDirective()
     * @generated
     */
    EClass XANNOTATION_DIRECTIVE = eINSTANCE.getXAnnotationDirective();

    /**
     * The meta object literal for the '<em><b>Source URI</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute XANNOTATION_DIRECTIVE__SOURCE_URI = eINSTANCE.getXAnnotationDirective_SourceURI();

    /**
     * The meta object literal for the '<em><b>Package</b></em>' container reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference XANNOTATION_DIRECTIVE__PACKAGE = eINSTANCE.getXAnnotationDirective_Package();

    /**
     * The meta object literal for the '{@link org.eclipse.emf.ecore.xcore.impl.XAttributeImpl <em>XAttribute</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.eclipse.emf.ecore.xcore.impl.XAttributeImpl
     * @see org.eclipse.emf.ecore.xcore.impl.XcorePackageImpl#getXAttribute()
     * @generated
     */
    EClass XATTRIBUTE = eINSTANCE.getXAttribute();

    /**
     * The meta object literal for the '<em><b>Default Value Literal</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute XATTRIBUTE__DEFAULT_VALUE_LITERAL = eINSTANCE.getXAttribute_DefaultValueLiteral();

    /**
     * The meta object literal for the '<em><b>ID</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute XATTRIBUTE__ID = eINSTANCE.getXAttribute_ID();

    /**
     * The meta object literal for the '{@link org.eclipse.emf.ecore.xcore.impl.XClassImpl <em>XClass</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.eclipse.emf.ecore.xcore.impl.XClassImpl
     * @see org.eclipse.emf.ecore.xcore.impl.XcorePackageImpl#getXClass()
     * @generated
     */
    EClass XCLASS = eINSTANCE.getXClass();

    /**
     * The meta object literal for the '<em><b>Abstract</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute XCLASS__ABSTRACT = eINSTANCE.getXClass_Abstract();

    /**
     * The meta object literal for the '<em><b>Interface</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute XCLASS__INTERFACE = eINSTANCE.getXClass_Interface();

    /**
     * The meta object literal for the '<em><b>Members</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference XCLASS__MEMBERS = eINSTANCE.getXClass_Members();

    /**
     * The meta object literal for the '<em><b>Super Types</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference XCLASS__SUPER_TYPES = eINSTANCE.getXClass_SuperTypes();

    /**
     * The meta object literal for the '{@link org.eclipse.emf.ecore.xcore.impl.XClassifierImpl <em>XClassifier</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.eclipse.emf.ecore.xcore.impl.XClassifierImpl
     * @see org.eclipse.emf.ecore.xcore.impl.XcorePackageImpl#getXClassifier()
     * @generated
     */
    EClass XCLASSIFIER = eINSTANCE.getXClassifier();

    /**
     * The meta object literal for the '<em><b>Instance Type</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference XCLASSIFIER__INSTANCE_TYPE = eINSTANCE.getXClassifier_InstanceType();

    /**
     * The meta object literal for the '<em><b>Package</b></em>' container reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference XCLASSIFIER__PACKAGE = eINSTANCE.getXClassifier_Package();

    /**
     * The meta object literal for the '<em><b>Type Parameters</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference XCLASSIFIER__TYPE_PARAMETERS = eINSTANCE.getXClassifier_TypeParameters();

    /**
     * The meta object literal for the '{@link org.eclipse.emf.ecore.xcore.impl.XDataTypeImpl <em>XData Type</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.eclipse.emf.ecore.xcore.impl.XDataTypeImpl
     * @see org.eclipse.emf.ecore.xcore.impl.XcorePackageImpl#getXDataType()
     * @generated
     */
    EClass XDATA_TYPE = eINSTANCE.getXDataType();

    /**
     * The meta object literal for the '<em><b>Serializable</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute XDATA_TYPE__SERIALIZABLE = eINSTANCE.getXDataType_Serializable();

    /**
     * The meta object literal for the '<em><b>Create Body</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference XDATA_TYPE__CREATE_BODY = eINSTANCE.getXDataType_CreateBody();

    /**
     * The meta object literal for the '<em><b>Convert Body</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference XDATA_TYPE__CONVERT_BODY = eINSTANCE.getXDataType_ConvertBody();

    /**
     * The meta object literal for the '{@link org.eclipse.emf.ecore.xcore.impl.XEnumImpl <em>XEnum</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.eclipse.emf.ecore.xcore.impl.XEnumImpl
     * @see org.eclipse.emf.ecore.xcore.impl.XcorePackageImpl#getXEnum()
     * @generated
     */
    EClass XENUM = eINSTANCE.getXEnum();

    /**
     * The meta object literal for the '<em><b>Literals</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference XENUM__LITERALS = eINSTANCE.getXEnum_Literals();

    /**
     * The meta object literal for the '{@link org.eclipse.emf.ecore.xcore.impl.XEnumLiteralImpl <em>XEnum Literal</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.eclipse.emf.ecore.xcore.impl.XEnumLiteralImpl
     * @see org.eclipse.emf.ecore.xcore.impl.XcorePackageImpl#getXEnumLiteral()
     * @generated
     */
    EClass XENUM_LITERAL = eINSTANCE.getXEnumLiteral();

    /**
     * The meta object literal for the '<em><b>Value</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute XENUM_LITERAL__VALUE = eINSTANCE.getXEnumLiteral_Value();

    /**
     * The meta object literal for the '<em><b>Literal</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute XENUM_LITERAL__LITERAL = eINSTANCE.getXEnumLiteral_Literal();

    /**
     * The meta object literal for the '<em><b>Enum</b></em>' container reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference XENUM_LITERAL__ENUM = eINSTANCE.getXEnumLiteral_Enum();

    /**
     * The meta object literal for the '{@link org.eclipse.emf.ecore.xcore.impl.XGenericTypeImpl <em>XGeneric Type</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.eclipse.emf.ecore.xcore.impl.XGenericTypeImpl
     * @see org.eclipse.emf.ecore.xcore.impl.XcorePackageImpl#getXGenericType()
     * @generated
     */
    EClass XGENERIC_TYPE = eINSTANCE.getXGenericType();

    /**
     * The meta object literal for the '<em><b>Upper Bound</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference XGENERIC_TYPE__UPPER_BOUND = eINSTANCE.getXGenericType_UpperBound();

    /**
     * The meta object literal for the '<em><b>Type Arguments</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference XGENERIC_TYPE__TYPE_ARGUMENTS = eINSTANCE.getXGenericType_TypeArguments();

    /**
     * The meta object literal for the '<em><b>Lower Bound</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference XGENERIC_TYPE__LOWER_BOUND = eINSTANCE.getXGenericType_LowerBound();

    /**
     * The meta object literal for the '<em><b>Type</b></em>' reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference XGENERIC_TYPE__TYPE = eINSTANCE.getXGenericType_Type();

    /**
     * The meta object literal for the '{@link org.eclipse.emf.ecore.xcore.impl.XImportDirectiveImpl <em>XImport Directive</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.eclipse.emf.ecore.xcore.impl.XImportDirectiveImpl
     * @see org.eclipse.emf.ecore.xcore.impl.XcorePackageImpl#getXImportDirective()
     * @generated
     */
    EClass XIMPORT_DIRECTIVE = eINSTANCE.getXImportDirective();

    /**
     * The meta object literal for the '<em><b>Imported Namespace</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute XIMPORT_DIRECTIVE__IMPORTED_NAMESPACE = eINSTANCE.getXImportDirective_ImportedNamespace();

    /**
     * The meta object literal for the '<em><b>Imported Object</b></em>' reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference XIMPORT_DIRECTIVE__IMPORTED_OBJECT = eINSTANCE.getXImportDirective_ImportedObject();

    /**
     * The meta object literal for the '<em><b>Package</b></em>' container reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference XIMPORT_DIRECTIVE__PACKAGE = eINSTANCE.getXImportDirective_Package();

    /**
     * The meta object literal for the '{@link org.eclipse.emf.ecore.xcore.impl.XMemberImpl <em>XMember</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.eclipse.emf.ecore.xcore.impl.XMemberImpl
     * @see org.eclipse.emf.ecore.xcore.impl.XcorePackageImpl#getXMember()
     * @generated
     */
    EClass XMEMBER = eINSTANCE.getXMember();

    /**
     * The meta object literal for the '<em><b>Containing Class</b></em>' container reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference XMEMBER__CONTAINING_CLASS = eINSTANCE.getXMember_ContainingClass();

    /**
     * The meta object literal for the '{@link org.eclipse.emf.ecore.xcore.impl.XModelElementImpl <em>XModel Element</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.eclipse.emf.ecore.xcore.impl.XModelElementImpl
     * @see org.eclipse.emf.ecore.xcore.impl.XcorePackageImpl#getXModelElement()
     * @generated
     */
    EClass XMODEL_ELEMENT = eINSTANCE.getXModelElement();

    /**
     * The meta object literal for the '<em><b>Annotations</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference XMODEL_ELEMENT__ANNOTATIONS = eINSTANCE.getXModelElement_Annotations();

    /**
     * The meta object literal for the '{@link org.eclipse.emf.ecore.xcore.impl.XNamedElementImpl <em>XNamed Element</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.eclipse.emf.ecore.xcore.impl.XNamedElementImpl
     * @see org.eclipse.emf.ecore.xcore.impl.XcorePackageImpl#getXNamedElement()
     * @generated
     */
    EClass XNAMED_ELEMENT = eINSTANCE.getXNamedElement();

    /**
     * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute XNAMED_ELEMENT__NAME = eINSTANCE.getXNamedElement_Name();

    /**
     * The meta object literal for the '{@link org.eclipse.emf.ecore.xcore.impl.XOperationImpl <em>XOperation</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.eclipse.emf.ecore.xcore.impl.XOperationImpl
     * @see org.eclipse.emf.ecore.xcore.impl.XcorePackageImpl#getXOperation()
     * @generated
     */
    EClass XOPERATION = eINSTANCE.getXOperation();

    /**
     * The meta object literal for the '<em><b>Type Parameters</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference XOPERATION__TYPE_PARAMETERS = eINSTANCE.getXOperation_TypeParameters();

    /**
     * The meta object literal for the '<em><b>Parameters</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference XOPERATION__PARAMETERS = eINSTANCE.getXOperation_Parameters();

    /**
     * The meta object literal for the '<em><b>Exceptions</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference XOPERATION__EXCEPTIONS = eINSTANCE.getXOperation_Exceptions();

    /**
     * The meta object literal for the '<em><b>Body</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference XOPERATION__BODY = eINSTANCE.getXOperation_Body();

    /**
     * The meta object literal for the '{@link org.eclipse.emf.ecore.xcore.impl.XPackageImpl <em>XPackage</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.eclipse.emf.ecore.xcore.impl.XPackageImpl
     * @see org.eclipse.emf.ecore.xcore.impl.XcorePackageImpl#getXPackage()
     * @generated
     */
    EClass XPACKAGE = eINSTANCE.getXPackage();

    /**
     * The meta object literal for the '<em><b>Import Directives</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference XPACKAGE__IMPORT_DIRECTIVES = eINSTANCE.getXPackage_ImportDirectives();

    /**
     * The meta object literal for the '<em><b>Annotation Directives</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference XPACKAGE__ANNOTATION_DIRECTIVES = eINSTANCE.getXPackage_AnnotationDirectives();

    /**
     * The meta object literal for the '<em><b>Classifiers</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference XPACKAGE__CLASSIFIERS = eINSTANCE.getXPackage_Classifiers();

    /**
     * The meta object literal for the '{@link org.eclipse.emf.ecore.xcore.impl.XParameterImpl <em>XParameter</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.eclipse.emf.ecore.xcore.impl.XParameterImpl
     * @see org.eclipse.emf.ecore.xcore.impl.XcorePackageImpl#getXParameter()
     * @generated
     */
    EClass XPARAMETER = eINSTANCE.getXParameter();

    /**
     * The meta object literal for the '<em><b>Operation</b></em>' container reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference XPARAMETER__OPERATION = eINSTANCE.getXParameter_Operation();

    /**
     * The meta object literal for the '{@link org.eclipse.emf.ecore.xcore.impl.XReferenceImpl <em>XReference</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.eclipse.emf.ecore.xcore.impl.XReferenceImpl
     * @see org.eclipse.emf.ecore.xcore.impl.XcorePackageImpl#getXReference()
     * @generated
     */
    EClass XREFERENCE = eINSTANCE.getXReference();

    /**
     * The meta object literal for the '<em><b>Container</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute XREFERENCE__CONTAINER = eINSTANCE.getXReference_Container();

    /**
     * The meta object literal for the '<em><b>Containment</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute XREFERENCE__CONTAINMENT = eINSTANCE.getXReference_Containment();

    /**
     * The meta object literal for the '<em><b>Resolve Proxies</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute XREFERENCE__RESOLVE_PROXIES = eINSTANCE.getXReference_ResolveProxies();

    /**
     * The meta object literal for the '<em><b>Local</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute XREFERENCE__LOCAL = eINSTANCE.getXReference_Local();

    /**
     * The meta object literal for the '<em><b>Opposite</b></em>' reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference XREFERENCE__OPPOSITE = eINSTANCE.getXReference_Opposite();

    /**
     * The meta object literal for the '<em><b>Keys</b></em>' reference list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference XREFERENCE__KEYS = eINSTANCE.getXReference_Keys();

    /**
     * The meta object literal for the '{@link org.eclipse.emf.ecore.xcore.impl.XStringToStringMapEntryImpl <em>XString To String Map Entry</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.eclipse.emf.ecore.xcore.impl.XStringToStringMapEntryImpl
     * @see org.eclipse.emf.ecore.xcore.impl.XcorePackageImpl#getXStringToStringMapEntry()
     * @generated
     */
    EClass XSTRING_TO_STRING_MAP_ENTRY = eINSTANCE.getXStringToStringMapEntry();

    /**
     * The meta object literal for the '<em><b>Key</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute XSTRING_TO_STRING_MAP_ENTRY__KEY = eINSTANCE.getXStringToStringMapEntry_Key();

    /**
     * The meta object literal for the '<em><b>Value</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute XSTRING_TO_STRING_MAP_ENTRY__VALUE = eINSTANCE.getXStringToStringMapEntry_Value();

    /**
     * The meta object literal for the '{@link org.eclipse.emf.ecore.xcore.impl.XStructuralFeatureImpl <em>XStructural Feature</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.eclipse.emf.ecore.xcore.impl.XStructuralFeatureImpl
     * @see org.eclipse.emf.ecore.xcore.impl.XcorePackageImpl#getXStructuralFeature()
     * @generated
     */
    EClass XSTRUCTURAL_FEATURE = eINSTANCE.getXStructuralFeature();

    /**
     * The meta object literal for the '<em><b>Readonly</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute XSTRUCTURAL_FEATURE__READONLY = eINSTANCE.getXStructuralFeature_Readonly();

    /**
     * The meta object literal for the '<em><b>Volatile</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute XSTRUCTURAL_FEATURE__VOLATILE = eINSTANCE.getXStructuralFeature_Volatile();

    /**
     * The meta object literal for the '<em><b>Transient</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute XSTRUCTURAL_FEATURE__TRANSIENT = eINSTANCE.getXStructuralFeature_Transient();

    /**
     * The meta object literal for the '<em><b>Unsettable</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute XSTRUCTURAL_FEATURE__UNSETTABLE = eINSTANCE.getXStructuralFeature_Unsettable();

    /**
     * The meta object literal for the '<em><b>Derived</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute XSTRUCTURAL_FEATURE__DERIVED = eINSTANCE.getXStructuralFeature_Derived();

    /**
     * The meta object literal for the '<em><b>Get Body</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference XSTRUCTURAL_FEATURE__GET_BODY = eINSTANCE.getXStructuralFeature_GetBody();

    /**
     * The meta object literal for the '<em><b>Set Body</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference XSTRUCTURAL_FEATURE__SET_BODY = eINSTANCE.getXStructuralFeature_SetBody();

    /**
     * The meta object literal for the '<em><b>Is Set Body</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference XSTRUCTURAL_FEATURE__IS_SET_BODY = eINSTANCE.getXStructuralFeature_IsSetBody();

    /**
     * The meta object literal for the '<em><b>Unset Body</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference XSTRUCTURAL_FEATURE__UNSET_BODY = eINSTANCE.getXStructuralFeature_UnsetBody();

    /**
     * The meta object literal for the '{@link org.eclipse.emf.ecore.xcore.impl.XTypedElementImpl <em>XTyped Element</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.eclipse.emf.ecore.xcore.impl.XTypedElementImpl
     * @see org.eclipse.emf.ecore.xcore.impl.XcorePackageImpl#getXTypedElement()
     * @generated
     */
    EClass XTYPED_ELEMENT = eINSTANCE.getXTypedElement();

    /**
     * The meta object literal for the '<em><b>Unordered</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute XTYPED_ELEMENT__UNORDERED = eINSTANCE.getXTypedElement_Unordered();

    /**
     * The meta object literal for the '<em><b>Unique</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute XTYPED_ELEMENT__UNIQUE = eINSTANCE.getXTypedElement_Unique();

    /**
     * The meta object literal for the '<em><b>Type</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference XTYPED_ELEMENT__TYPE = eINSTANCE.getXTypedElement_Type();

    /**
     * The meta object literal for the '<em><b>Multiplicity</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute XTYPED_ELEMENT__MULTIPLICITY = eINSTANCE.getXTypedElement_Multiplicity();

    /**
     * The meta object literal for the '{@link org.eclipse.emf.ecore.xcore.impl.XTypeParameterImpl <em>XType Parameter</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.eclipse.emf.ecore.xcore.impl.XTypeParameterImpl
     * @see org.eclipse.emf.ecore.xcore.impl.XcorePackageImpl#getXTypeParameter()
     * @generated
     */
    EClass XTYPE_PARAMETER = eINSTANCE.getXTypeParameter();

    /**
     * The meta object literal for the '<em><b>Bounds</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference XTYPE_PARAMETER__BOUNDS = eINSTANCE.getXTypeParameter_Bounds();

    /**
     * The meta object literal for the '<em>XMultiplicity</em>' data type.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.eclipse.emf.ecore.xcore.impl.XcorePackageImpl#getXMultiplicity()
     * @generated
     */
    EDataType XMULTIPLICITY = eINSTANCE.getXMultiplicity();

  }

} //XcorePackage
