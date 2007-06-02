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
 * $Id: JClass.java,v 1.5 2007/06/02 19:34:28 emerks Exp $
 */
package org.eclipse.emf.java;


import org.eclipse.emf.common.util.EList;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>JClass</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.emf.java.JClass#isAbstract <em>Abstract</em>}</li>
 *   <li>{@link org.eclipse.emf.java.JClass#isFinal <em>Final</em>}</li>
 *   <li>{@link org.eclipse.emf.java.JClass#isInterface <em>Interface</em>}</li>
 *   <li>{@link org.eclipse.emf.java.JClass#isThrowable <em>Throwable</em>}</li>
 *   <li>{@link org.eclipse.emf.java.JClass#getJavaClass <em>Java Class</em>}</li>
 *   <li>{@link org.eclipse.emf.java.JClass#getFields <em>Fields</em>}</li>
 *   <li>{@link org.eclipse.emf.java.JClass#getMethods <em>Methods</em>}</li>
 *   <li>{@link org.eclipse.emf.java.JClass#getSuperTypes <em>Super Types</em>}</li>
 *   <li>{@link org.eclipse.emf.java.JClass#getAllSuperTypes <em>All Super Types</em>}</li>
 *   <li>{@link org.eclipse.emf.java.JClass#getMembers <em>Members</em>}</li>
 *   <li>{@link org.eclipse.emf.java.JClass#getComponentType <em>Component Type</em>}</li>
 *   <li>{@link org.eclipse.emf.java.JClass#getArrayType <em>Array Type</em>}</li>
 *   <li>{@link org.eclipse.emf.java.JClass#getUnit <em>Unit</em>}</li>
 *   <li>{@link org.eclipse.emf.java.JClass#getAllMethods <em>All Methods</em>}</li>
 *   <li>{@link org.eclipse.emf.java.JClass#getAllFields <em>All Fields</em>}</li>
 *   <li>{@link org.eclipse.emf.java.JClass#getPackage <em>Package</em>}</li>
 *   <li>{@link org.eclipse.emf.java.JClass#getTypes <em>Types</em>}</li>
 *   <li>{@link org.eclipse.emf.java.JClass#getAllTypes <em>All Types</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.emf.java.JavaPackage#getJClass()
 * @model
 * @generated
 */
public interface JClass extends JMember
{
  /**
   * Returns the value of the '<em><b>Abstract</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Abstract</em>' attribute isn't clear, 
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Abstract</em>' attribute.
   * @see #setAbstract(boolean)
   * @see org.eclipse.emf.java.JavaPackage#getJClass_Abstract()
   * @model
   * @generated
   */
  boolean isAbstract();

  /**
   * Sets the value of the '{@link org.eclipse.emf.java.JClass#isAbstract <em>Abstract</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Abstract</em>' attribute.
   * @see #isAbstract()
   * @generated
   */
  void setAbstract(boolean value);

  /**
   * Returns the value of the '<em><b>Final</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Final</em>' attribute isn't clear, 
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Final</em>' attribute.
   * @see #setFinal(boolean)
   * @see org.eclipse.emf.java.JavaPackage#getJClass_Final()
   * @model
   * @generated
   */
  boolean isFinal();

  /**
   * Sets the value of the '{@link org.eclipse.emf.java.JClass#isFinal <em>Final</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Final</em>' attribute.
   * @see #isFinal()
   * @generated
   */
  void setFinal(boolean value);

  /**
   * Returns the value of the '<em><b>Interface</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Interface</em>' attribute isn't clear, 
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Interface</em>' attribute.
   * @see #setInterface(boolean)
   * @see org.eclipse.emf.java.JavaPackage#getJClass_Interface()
   * @model
   * @generated
   */
  boolean isInterface();

  /**
   * Sets the value of the '{@link org.eclipse.emf.java.JClass#isInterface <em>Interface</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Interface</em>' attribute.
   * @see #isInterface()
   * @generated
   */
  void setInterface(boolean value);

  /**
   * Returns the value of the '<em><b>Throwable</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Throwable</em>' attribute isn't clear, 
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Throwable</em>' attribute.
   * @see #setThrowable(boolean)
   * @see org.eclipse.emf.java.JavaPackage#getJClass_Throwable()
   * @model
   * @generated
   */
  boolean isThrowable();

  /**
   * Sets the value of the '{@link org.eclipse.emf.java.JClass#isThrowable <em>Throwable</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Throwable</em>' attribute.
   * @see #isThrowable()
   * @generated
   */
  void setThrowable(boolean value);

  /**
   * Returns the value of the '<em><b>Java Class</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Java Class</em>' attribute isn't clear, 
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Java Class</em>' attribute.
   * @see #setJavaClass(Class)
   * @see org.eclipse.emf.java.JavaPackage#getJClass_JavaClass()
   * @model dataType="org.eclipse.emf.java.JavaClass" transient="true"
   * @generated
   */
  Class<?> getJavaClass();

  /**
   * Sets the value of the '{@link org.eclipse.emf.java.JClass#getJavaClass <em>Java Class</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Java Class</em>' attribute.
   * @see #getJavaClass()
   * @generated
   */
  void setJavaClass(Class<?> value);

  /**
   * Returns the value of the '<em><b>Fields</b></em>' reference list.
   * The list contents are of type {@link org.eclipse.emf.java.JField}.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Fields</em>' reference list isn't clear, 
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Fields</em>' reference list.
   * @see org.eclipse.emf.java.JavaPackage#getJClass_Fields()
   * @model type="org.eclipse.emf.java.JField" transient="true" volatile="true"
   * @generated
   */
  EList<JField> getFields();

  /**
   * Returns the value of the '<em><b>Methods</b></em>' reference list.
   * The list contents are of type {@link org.eclipse.emf.java.JMethod}.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Methods</em>' reference list isn't clear, 
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Methods</em>' reference list.
   * @see org.eclipse.emf.java.JavaPackage#getJClass_Methods()
   * @model type="org.eclipse.emf.java.JMethod" transient="true" volatile="true"
   * @generated
   */
  EList<JMethod> getMethods();

  /**
   * Returns the value of the '<em><b>Super Types</b></em>' reference list.
   * The list contents are of type {@link org.eclipse.emf.java.JClass}.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Super Types</em>' reference list isn't clear, 
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Super Types</em>' reference list.
   * @see org.eclipse.emf.java.JavaPackage#getJClass_SuperTypes()
   * @model type="org.eclipse.emf.java.JClass"
   * @generated
   */
  EList<JClass> getSuperTypes();

  /**
   * Returns the value of the '<em><b>All Super Types</b></em>' reference list.
   * The list contents are of type {@link org.eclipse.emf.java.JClass}.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>All Super Types</em>' reference list isn't clear, 
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>All Super Types</em>' reference list.
   * @see org.eclipse.emf.java.JavaPackage#getJClass_AllSuperTypes()
   * @model type="org.eclipse.emf.java.JClass" transient="true" volatile="true"
   * @generated
   */
  EList<JClass> getAllSuperTypes();

  /**
   * Returns the value of the '<em><b>Members</b></em>' containment reference list.
   * The list contents are of type {@link org.eclipse.emf.java.JMember}.
   * It is bidirectional and its opposite is '{@link org.eclipse.emf.java.JMember#getContainingType <em>Containing Type</em>}'.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Members</em>' containment reference list isn't clear, 
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Members</em>' containment reference list.
   * @see org.eclipse.emf.java.JavaPackage#getJClass_Members()
   * @see org.eclipse.emf.java.JMember#getContainingType
   * @model type="org.eclipse.emf.java.JMember" opposite="containingType" containment="true"
   * @generated
   */
  EList<JMember> getMembers();

  /**
   * Returns the value of the '<em><b>Component Type</b></em>' container reference.
   * It is bidirectional and its opposite is '{@link org.eclipse.emf.java.JClass#getArrayType <em>Array Type</em>}'.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Component Type</em>' reference isn't clear, 
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Component Type</em>' container reference.
   * @see #setComponentType(JClass)
   * @see org.eclipse.emf.java.JavaPackage#getJClass_ComponentType()
   * @see org.eclipse.emf.java.JClass#getArrayType
   * @model opposite="arrayType"
   * @generated
   */
  JClass getComponentType();

  /**
   * Sets the value of the '{@link org.eclipse.emf.java.JClass#getComponentType <em>Component Type</em>}' container reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Component Type</em>' container reference.
   * @see #getComponentType()
   * @generated
   */
  void setComponentType(JClass value);

  /**
   * Returns the value of the '<em><b>Array Type</b></em>' containment reference.
   * It is bidirectional and its opposite is '{@link org.eclipse.emf.java.JClass#getComponentType <em>Component Type</em>}'.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Array Type</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Array Type</em>' containment reference.
   * @see #setArrayType(JClass)
   * @see org.eclipse.emf.java.JavaPackage#getJClass_ArrayType()
   * @see org.eclipse.emf.java.JClass#getComponentType
   * @model opposite="componentType" containment="true" transient="true"
   * @generated
   */
  JClass getArrayType();

  /**
   * Sets the value of the '{@link org.eclipse.emf.java.JClass#getArrayType <em>Array Type</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Array Type</em>' containment reference.
   * @see #getArrayType()
   * @generated
   */
  void setArrayType(JClass value);

  /**
   * Returns the value of the '<em><b>Unit</b></em>' container reference.
   * It is bidirectional and its opposite is '{@link org.eclipse.emf.java.JCompilationUnit#getTypes <em>Types</em>}'.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Unit</em>' container reference isn't clear, 
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Unit</em>' container reference.
   * @see #setUnit(JCompilationUnit)
   * @see org.eclipse.emf.java.JavaPackage#getJClass_Unit()
   * @see org.eclipse.emf.java.JCompilationUnit#getTypes
   * @model opposite="types"
   * @generated
   */
  JCompilationUnit getUnit();

  /**
   * Sets the value of the '{@link org.eclipse.emf.java.JClass#getUnit <em>Unit</em>}' container reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Unit</em>' container reference.
   * @see #getUnit()
   * @generated
   */
  void setUnit(JCompilationUnit value);

  /**
   * Returns the value of the '<em><b>All Methods</b></em>' reference list.
   * The list contents are of type {@link org.eclipse.emf.java.JMethod}.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>All Methods</em>' reference list isn't clear, 
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>All Methods</em>' reference list.
   * @see org.eclipse.emf.java.JavaPackage#getJClass_AllMethods()
   * @model type="org.eclipse.emf.java.JMethod" transient="true" volatile="true"
   * @generated
   */
  EList<JMethod> getAllMethods();

  /**
   * Returns the value of the '<em><b>All Fields</b></em>' reference list.
   * The list contents are of type {@link org.eclipse.emf.java.JField}.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>All Fields</em>' reference list isn't clear, 
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>All Fields</em>' reference list.
   * @see org.eclipse.emf.java.JavaPackage#getJClass_AllFields()
   * @model type="org.eclipse.emf.java.JField" transient="true" volatile="true"
   * @generated
   */
  EList<JField> getAllFields();

  /**
   * Returns the value of the '<em><b>Package</b></em>' reference.
   * It is bidirectional and its opposite is '{@link org.eclipse.emf.java.JPackage#getTypes <em>Types</em>}'.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Package</em>' reference isn't clear, 
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Package</em>' reference.
   * @see #setPackage(JPackage)
   * @see org.eclipse.emf.java.JavaPackage#getJClass_Package()
   * @see org.eclipse.emf.java.JPackage#getTypes
   * @model opposite="types"
   * @generated
   */
  JPackage getPackage();

  /**
   * Sets the value of the '{@link org.eclipse.emf.java.JClass#getPackage <em>Package</em>}' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Package</em>' reference.
   * @see #getPackage()
   * @generated
   */
  void setPackage(JPackage value);

  /**
   * Returns the value of the '<em><b>Types</b></em>' reference list.
   * The list contents are of type {@link org.eclipse.emf.java.JClass}.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Types</em>' reference list isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Types</em>' reference list.
   * @see org.eclipse.emf.java.JavaPackage#getJClass_Types()
   * @model type="org.eclipse.emf.java.JClass"
   * @generated
   */
  EList<JClass> getTypes();

  /**
   * Returns the value of the '<em><b>All Types</b></em>' reference list.
   * The list contents are of type {@link org.eclipse.emf.java.JClass}.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>All Types</em>' reference list isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>All Types</em>' reference list.
   * @see org.eclipse.emf.java.JavaPackage#getJClass_AllTypes()
   * @model type="org.eclipse.emf.java.JClass"
   * @generated
   */
  EList<JClass> getAllTypes();

  JClass resolveJClass(String qualifiedName);

} // JClass
