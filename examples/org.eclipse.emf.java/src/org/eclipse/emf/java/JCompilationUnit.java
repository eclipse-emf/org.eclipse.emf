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
package org.eclipse.emf.java;


import org.eclipse.emf.common.util.EList;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>JCompilation Unit</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.emf.java.JCompilationUnit#getImports <em>Imports</em>}</li>
 *   <li>{@link org.eclipse.emf.java.JCompilationUnit#getComment <em>Comment</em>}</li>
 *   <li>{@link org.eclipse.emf.java.JCompilationUnit#getTypes <em>Types</em>}</li>
 *   <li>{@link org.eclipse.emf.java.JCompilationUnit#getImportedPackages <em>Imported Packages</em>}</li>
 *   <li>{@link org.eclipse.emf.java.JCompilationUnit#getImportedTypes <em>Imported Types</em>}</li>
 *   <li>{@link org.eclipse.emf.java.JCompilationUnit#getPackage <em>Package</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.emf.java.JavaPackage#getJCompilationUnit()
 * @model
 * @generated
 */
public interface JCompilationUnit extends JModelElement
{
  /**
   * Returns the value of the '<em><b>Package</b></em>' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the value of the '<em>Package</em>' reference.
   * @see #setPackage(JPackage)
   * @see org.eclipse.emf.java.JavaPackage#getJCompilationUnit_Package()
   * @model
   * @generated
   */
  JPackage getPackage();

  /**
   * Sets the value of the '{@link org.eclipse.emf.java.JCompilationUnit#getPackage <em>Package</em>}' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Package</em>' reference.
   * @see #getPackage()
   * @generated
   */
  void setPackage(JPackage value);

  /**
   * Returns the value of the '<em><b>Imports</b></em>' attribute list.
   * The list contents are of type {@link java.lang.String}.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the value of the '<em>Imports</em>' attribute list.
   * @see org.eclipse.emf.java.JavaPackage#getJCompilationUnit_Imports()
   * @model
   * @generated
   */
  EList<String> getImports();

  /**
   * Returns the value of the '<em><b>Comment</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the value of the '<em>Comment</em>' attribute.
   * @see #setComment(String)
   * @see org.eclipse.emf.java.JavaPackage#getJCompilationUnit_Comment()
   * @model
   * @generated
   */
  String getComment();

  /**
   * Sets the value of the '{@link org.eclipse.emf.java.JCompilationUnit#getComment <em>Comment</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Comment</em>' attribute.
   * @see #getComment()
   * @generated
   */
  void setComment(String value);

  /**
   * Returns the value of the '<em><b>Types</b></em>' containment reference list.
   * The list contents are of type {@link org.eclipse.emf.java.JClass}.
   * It is bidirectional and its opposite is '{@link org.eclipse.emf.java.JClass#getUnit <em>Unit</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the value of the '<em>Types</em>' containment reference list.
   * @see org.eclipse.emf.java.JavaPackage#getJCompilationUnit_Types()
   * @see org.eclipse.emf.java.JClass#getUnit
   * @model opposite="unit" containment="true"
   * @generated
   */
  EList<JClass> getTypes();

  /**
   * Returns the value of the '<em><b>Imported Packages</b></em>' reference list.
   * The list contents are of type {@link org.eclipse.emf.java.JPackage}.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the value of the '<em>Imported Packages</em>' reference list.
   * @see org.eclipse.emf.java.JavaPackage#getJCompilationUnit_ImportedPackages()
   * @model
   * @generated
   */
  EList<JPackage> getImportedPackages();

  /**
   * Returns the value of the '<em><b>Imported Types</b></em>' reference list.
   * The list contents are of type {@link org.eclipse.emf.java.JClass}.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the value of the '<em>Imported Types</em>' reference list.
   * @see org.eclipse.emf.java.JavaPackage#getJCompilationUnit_ImportedTypes()
   * @model
   * @generated
   */
  EList<JClass> getImportedTypes();

  /**
   * Resolves all package and class references based on the source; 
   * the JNode must be present.
   * EATM
   */
  void resolveIdentifiers();

  JClass resolveJClass(String qualifiedName);

} // JCompilationUnit
