/**
 * <copyright>
 *
 * Copyright (c) 2002-2004 IBM Corporation and others.
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
 * $Id: EClass.java,v 1.1 2004/03/06 17:31:31 marcelop Exp $
 */
package org.eclipse.emf.ecore;


import org.eclipse.emf.common.util.EList;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>EClass</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.emf.ecore.EClass#isAbstract <em>Abstract</em>}</li>
 *   <li>{@link org.eclipse.emf.ecore.EClass#isInterface <em>Interface</em>}</li>
 *   <li>{@link org.eclipse.emf.ecore.EClass#getESuperTypes <em>ESuper Types</em>}</li>
 *   <li>{@link org.eclipse.emf.ecore.EClass#getEOperations <em>EOperations</em>}</li>
 *   <li>{@link org.eclipse.emf.ecore.EClass#getEAllAttributes <em>EAll Attributes</em>}</li>
 *   <li>{@link org.eclipse.emf.ecore.EClass#getEAllReferences <em>EAll References</em>}</li>
 *   <li>{@link org.eclipse.emf.ecore.EClass#getEReferences <em>EReferences</em>}</li>
 *   <li>{@link org.eclipse.emf.ecore.EClass#getEAttributes <em>EAttributes</em>}</li>
 *   <li>{@link org.eclipse.emf.ecore.EClass#getEAllContainments <em>EAll Containments</em>}</li>
 *   <li>{@link org.eclipse.emf.ecore.EClass#getEAllOperations <em>EAll Operations</em>}</li>
 *   <li>{@link org.eclipse.emf.ecore.EClass#getEAllStructuralFeatures <em>EAll Structural Features</em>}</li>
 *   <li>{@link org.eclipse.emf.ecore.EClass#getEAllSuperTypes <em>EAll Super Types</em>}</li>
 *   <li>{@link org.eclipse.emf.ecore.EClass#getEIDAttribute <em>EID Attribute</em>}</li>
 *   <li>{@link org.eclipse.emf.ecore.EClass#getEStructuralFeatures <em>EStructural Features</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.emf.ecore.EcorePackage#getEClass()
 * @model 
 * @generated
 */
public interface EClass extends EClassifier
{
  /**
   * Returns the value of the '<em><b>Abstract</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <p>
   * It represents whether the class is abstract in the Java sense.
   * For an abstract class,
   * the generated implementation class will be abstract,
   * and the generated factory will not provide support for creating an instance.
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Abstract</em>' attribute.
   * @see #setAbstract(boolean)
   * @see org.eclipse.emf.ecore.EcorePackage#getEClass_Abstract()
   * @model 
   * @generated
   */
  boolean isAbstract();

  /**
   * Sets the value of the '{@link org.eclipse.emf.ecore.EClass#isAbstract <em>Abstract</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Abstract</em>' attribute.
   * @see #isAbstract()
   * @generated
   */
  void setAbstract(boolean value);

  /**
   * Returns the value of the '<em><b>Interface</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <p>
   * It represents whether the class is an interface in the Java sense.
   * For an interface class,
   * there will be no generated implementation class.
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Interface</em>' attribute.
   * @see #setInterface(boolean)
   * @see org.eclipse.emf.ecore.EcorePackage#getEClass_Interface()
   * @model 
   * @generated
   */
  boolean isInterface();

  /**
   * Sets the value of the '{@link org.eclipse.emf.ecore.EClass#isInterface <em>Interface</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Interface</em>' attribute.
   * @see #isInterface()
   * @generated
   */
  void setInterface(boolean value);

  /**
   * Returns the value of the '<em><b>ESuper Types</b></em>' reference list.
   * The list contents are of type {@link org.eclipse.emf.ecore.EClass}.
   * <!-- begin-user-doc -->
   * <p>
   * It represents the super types in the Java sense, i.e., the super class and the direct implemented interfaces.
   * </p>
   * @see #getEAllSuperTypes()
   * @ignore
   * <!-- end-user-doc -->
   * @return the value of the '<em>ESuper Types</em>' reference list.
   * @see org.eclipse.emf.ecore.EcorePackage#getEClass_ESuperTypes()
   * @model type="org.eclipse.emf.ecore.EClass"
   * @generated
   */
  EList getESuperTypes();

  /**
   * Returns the value of the '<em><b>EAll Super Types</b></em>' reference list.
   * The list contents are of type {@link org.eclipse.emf.ecore.EClass}.
   * <!-- begin-user-doc -->
   * <p>
   * It represents the closure of all inherited super types.
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>EAll Super Types</em>' reference list.
   * @see org.eclipse.emf.ecore.EcorePackage#getEClass_EAllSuperTypes()
   * @model type="org.eclipse.emf.ecore.EClass" transient="true" changeable="false" volatile="true" derived="true"
   * @generated
   */
  EList getEAllSuperTypes();

  /**
   * Returns the value of the '<em><b>EID Attribute</b></em>' reference.
   * <!-- begin-user-doc -->
   * <p>
   * It represents the attribute that will be used as the ID of an instace.
   * @see org.eclipse.emf.ecore.EAttribute#isID()
   * @ignore
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>EID Attribute</em>' reference.
   * @see org.eclipse.emf.ecore.EcorePackage#getEClass_EIDAttribute()
   * @model resolveProxies="false" transient="true" changeable="false" volatile="true" derived="true"
   * @generated
   */
  EAttribute getEIDAttribute();

  /**
   * Returns the value of the '<em><b>EStructural Features</b></em>' containment reference list.
   * The list contents are of type {@link org.eclipse.emf.ecore.EStructuralFeature}.
   * It is bidirectional and its opposite is '{@link org.eclipse.emf.ecore.EStructuralFeature#getEContainingClass <em>EContaining Class</em>}'.
   * <!-- begin-user-doc -->
   * <p>
   * It represents the modeled features local to this class.
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>EStructural Features</em>' containment reference list.
   * @see org.eclipse.emf.ecore.EcorePackage#getEClass_EStructuralFeatures()
   * @see org.eclipse.emf.ecore.EStructuralFeature#getEContainingClass
   * @model type="org.eclipse.emf.ecore.EStructuralFeature" opposite="eContainingClass" containment="true"
   * @generated
   */
  EList getEStructuralFeatures();

  /**
   * Returns the value of the '<em><b>EAttributes</b></em>' reference list.
   * The list contents are of type {@link org.eclipse.emf.ecore.EAttribute}.
   * <!-- begin-user-doc -->
   * <p>
   * It represents the modeled attributes local to this class.
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>EAttributes</em>' reference list.
   * @see org.eclipse.emf.ecore.EcorePackage#getEClass_EAttributes()
   * @model type="org.eclipse.emf.ecore.EAttribute" transient="true" changeable="false" volatile="true" derived="true"
   * @generated
   */
  EList getEAttributes();

  /**
   * Returns the value of the '<em><b>EAll Attributes</b></em>' reference list.
   * The list contents are of type {@link org.eclipse.emf.ecore.EAttribute}.
   * <!-- begin-user-doc -->
   * <p>
   * It represents the closure of all attributes, inherited and local.
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>EAll Attributes</em>' reference list.
   * @see org.eclipse.emf.ecore.EcorePackage#getEClass_EAllAttributes()
   * @model type="org.eclipse.emf.ecore.EAttribute" transient="true" changeable="false" volatile="true" derived="true"
   * @generated
   */
  EList getEAllAttributes();

  /**
   * Returns the value of the '<em><b>EReferences</b></em>' reference list.
   * The list contents are of type {@link org.eclipse.emf.ecore.EReference}.
   * <!-- begin-user-doc -->
   * <p>
   * It represents the modeled references local to this class.
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>EReferences</em>' reference list.
   * @see org.eclipse.emf.ecore.EcorePackage#getEClass_EReferences()
   * @model type="org.eclipse.emf.ecore.EReference" transient="true" changeable="false" volatile="true" derived="true"
   * @generated
   */
  EList getEReferences();

  /**
   * Returns the value of the '<em><b>EAll References</b></em>' reference list.
   * The list contents are of type {@link org.eclipse.emf.ecore.EReference}.
   * <!-- begin-user-doc -->
   * <p>
   * It represents the closure of all references, inherited and local.
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>EAll References</em>' reference list.
   * @see org.eclipse.emf.ecore.EcorePackage#getEClass_EAllReferences()
   * @model type="org.eclipse.emf.ecore.EReference" transient="true" changeable="false" volatile="true" derived="true"
   * @generated
   */
  EList getEAllReferences();

  /**
   * Returns the value of the '<em><b>EAll Containments</b></em>' reference list.
   * The list contents are of type {@link org.eclipse.emf.ecore.EReference}.
   * <!-- begin-user-doc -->
   * <p>
   * It represents the closure of all references, inherited and local, 
   * that are {@link EReference#isContainment containments}.
   * These features will determine the {@link EObject#eContents} of an instance.
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>EAll Containments</em>' reference list.
   * @see org.eclipse.emf.ecore.EcorePackage#getEClass_EAllContainments()
   * @model type="org.eclipse.emf.ecore.EReference" transient="true" changeable="false" volatile="true" derived="true"
   * @generated
   */
  EList getEAllContainments();

  /**
   * Returns the value of the '<em><b>EAll Structural Features</b></em>' reference list.
   * The list contents are of type {@link org.eclipse.emf.ecore.EStructuralFeature}.
   * <!-- begin-user-doc -->
   * <p>
   * It represents the closure of all attributes and references, inherited and local.
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>EAll Structural Features</em>' reference list.
   * @see org.eclipse.emf.ecore.EcorePackage#getEClass_EAllStructuralFeatures()
   * @model type="org.eclipse.emf.ecore.EStructuralFeature" transient="true" changeable="false" volatile="true" derived="true"
   * @generated
   */
  EList getEAllStructuralFeatures();

  /**
   * Returns the value of the '<em><b>EOperations</b></em>' containment reference list.
   * The list contents are of type {@link org.eclipse.emf.ecore.EOperation}.
   * It is bidirectional and its opposite is '{@link org.eclipse.emf.ecore.EOperation#getEContainingClass <em>EContaining Class</em>}'.
   * <!-- begin-user-doc -->
   * <p>
   * It represents the modeled operations local to this class.
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>EOperations</em>' containment reference list.
   * @see org.eclipse.emf.ecore.EcorePackage#getEClass_EOperations()
   * @see org.eclipse.emf.ecore.EOperation#getEContainingClass
   * @model type="org.eclipse.emf.ecore.EOperation" opposite="eContainingClass" containment="true"
   * @generated
   */
  EList getEOperations();

  /**
   * Returns the value of the '<em><b>EAll Operations</b></em>' reference list.
   * The list contents are of type {@link org.eclipse.emf.ecore.EOperation}.
   * <!-- begin-user-doc -->
   * <p>
   * It represents the closure of all operations, inherited and local.
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>EAll Operations</em>' reference list.
   * @see org.eclipse.emf.ecore.EcorePackage#getEClass_EAllOperations()
   * @model type="org.eclipse.emf.ecore.EOperation" transient="true" changeable="false" volatile="true" derived="true"
   * @generated
   */
  EList getEAllOperations();

  /**
   * <!-- begin-user-doc -->
   * Returns whether this class is the same as, or a super type of, some other class.
   * @param somClass some other class.
   * @return whether this class is the same as, or a super type of, some other class.
   * @see Class#isAssignableFrom
   * @ignore
   * <!-- end-user-doc -->
   * @model 
   * @generated
   */
  boolean isSuperTypeOf(EClass someClass);

  /**
   * <!-- begin-user-doc -->
   * Returns the feature with this ID.
   * @return the feature with this ID.
   * <!-- end-user-doc -->
   * @model 
   * @generated
   */
  EStructuralFeature getEStructuralFeature(int featureID);

  /**
   * <!-- begin-user-doc -->
   * Returns the feature with this name.
   * @return the feature with this name.
   * <!-- end-user-doc -->
   * @model 
   * @generated
   */
  EStructuralFeature getEStructuralFeature(String featureName);

}
