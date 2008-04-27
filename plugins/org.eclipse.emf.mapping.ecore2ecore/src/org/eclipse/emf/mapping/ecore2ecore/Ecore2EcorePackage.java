/**
 * <copyright>
 * 
 * Copyright (c) 2004-2005 IBM Corporation and others.
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
 * $Id: Ecore2EcorePackage.java,v 1.7 2008/04/27 20:53:45 davidms Exp $
 */
package org.eclipse.emf.mapping.ecore2ecore;


import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;

import org.eclipse.emf.mapping.MappingPackage;


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
 * @see org.eclipse.emf.mapping.ecore2ecore.Ecore2EcoreFactory
 * @model kind="package"
 * @generated
 */
public interface Ecore2EcorePackage extends EPackage{
  /**
   * The package name.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  String eNAME = "ecore2ecore"; //$NON-NLS-1$

  /**
   * The package namespace URI.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  String eNS_URI = "http://www.eclipse.org/emf/2004/Ecore2Ecore"; //$NON-NLS-1$

  /**
   * The package namespace name.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  String eNS_PREFIX = "ecore2ecore"; //$NON-NLS-1$

  /**
   * The package content type ID.
   * <!-- begin-user-doc -->
   * @since 2.4
   * <!-- end-user-doc -->
   * @generated
   */
  String eCONTENT_TYPE = "org.eclipse.emf.mapping.ecore2ecore";

  /**
   * The singleton instance of the package.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  Ecore2EcorePackage eINSTANCE = org.eclipse.emf.mapping.ecore2ecore.impl.Ecore2EcorePackageImpl.init();

  /**
   * The meta object id for the '{@link org.eclipse.emf.mapping.ecore2ecore.impl.Ecore2EcoreMappingRootImpl <em>Mapping Root</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.eclipse.emf.mapping.ecore2ecore.impl.Ecore2EcoreMappingRootImpl
   * @see org.eclipse.emf.mapping.ecore2ecore.impl.Ecore2EcorePackageImpl#getEcore2EcoreMappingRoot()
   * @generated
   */
  int ECORE2_ECORE_MAPPING_ROOT = 0;

  /**
   * The feature id for the '<em><b>Helper</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ECORE2_ECORE_MAPPING_ROOT__HELPER = MappingPackage.MAPPING_ROOT__HELPER;

  /**
   * The feature id for the '<em><b>Nested</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ECORE2_ECORE_MAPPING_ROOT__NESTED = MappingPackage.MAPPING_ROOT__NESTED;

  /**
   * The feature id for the '<em><b>Nested In</b></em>' container reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ECORE2_ECORE_MAPPING_ROOT__NESTED_IN = MappingPackage.MAPPING_ROOT__NESTED_IN;

  /**
   * The feature id for the '<em><b>Inputs</b></em>' reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ECORE2_ECORE_MAPPING_ROOT__INPUTS = MappingPackage.MAPPING_ROOT__INPUTS;

  /**
   * The feature id for the '<em><b>Outputs</b></em>' reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ECORE2_ECORE_MAPPING_ROOT__OUTPUTS = MappingPackage.MAPPING_ROOT__OUTPUTS;

  /**
   * The feature id for the '<em><b>Type Mapping</b></em>' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ECORE2_ECORE_MAPPING_ROOT__TYPE_MAPPING = MappingPackage.MAPPING_ROOT__TYPE_MAPPING;

  /**
   * The feature id for the '<em><b>Output Read Only</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ECORE2_ECORE_MAPPING_ROOT__OUTPUT_READ_ONLY = MappingPackage.MAPPING_ROOT__OUTPUT_READ_ONLY;

  /**
   * The feature id for the '<em><b>Top To Bottom</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ECORE2_ECORE_MAPPING_ROOT__TOP_TO_BOTTOM = MappingPackage.MAPPING_ROOT__TOP_TO_BOTTOM;

  /**
   * The feature id for the '<em><b>Command Stack</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ECORE2_ECORE_MAPPING_ROOT__COMMAND_STACK = MappingPackage.MAPPING_ROOT__COMMAND_STACK;

  /**
   * The number of structural features of the '<em>Mapping Root</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ECORE2_ECORE_MAPPING_ROOT_FEATURE_COUNT = MappingPackage.MAPPING_ROOT_FEATURE_COUNT + 0;

  /**
   * Returns the meta object for class '{@link org.eclipse.emf.mapping.ecore2ecore.Ecore2EcoreMappingRoot <em>Mapping Root</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Mapping Root</em>'.
   * @see org.eclipse.emf.mapping.ecore2ecore.Ecore2EcoreMappingRoot
   * @generated
   */
  EClass getEcore2EcoreMappingRoot();

  /**
   * Returns the factory that creates the instances of the model.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the factory that creates the instances of the model.
   * @generated
   */
  Ecore2EcoreFactory getEcore2EcoreFactory();

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
     * The meta object literal for the '{@link org.eclipse.emf.mapping.ecore2ecore.impl.Ecore2EcoreMappingRootImpl <em>Mapping Root</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see org.eclipse.emf.mapping.ecore2ecore.impl.Ecore2EcoreMappingRootImpl
     * @see org.eclipse.emf.mapping.ecore2ecore.impl.Ecore2EcorePackageImpl#getEcore2EcoreMappingRoot()
     * @generated
     */
    EClass ECORE2_ECORE_MAPPING_ROOT = eINSTANCE.getEcore2EcoreMappingRoot();

  }

} //Ecore2EcorePackage
