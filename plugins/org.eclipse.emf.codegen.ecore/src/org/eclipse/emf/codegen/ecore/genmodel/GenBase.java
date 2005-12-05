/**
 * <copyright> 
 *
 * Copyright (c) 2002-2005 IBM Corporation and others.
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
 * $Id: GenBase.java,v 1.8 2005/12/05 20:11:14 marcelop Exp $
 */
package org.eclipse.emf.codegen.ecore.genmodel;


import org.eclipse.emf.common.util.EList;

import org.eclipse.core.runtime.IProgressMonitor;

import org.eclipse.emf.common.util.Monitor;
import org.eclipse.emf.ecore.EObject;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Gen Base</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.emf.codegen.ecore.genmodel.GenBase#getGenAnnotations <em>Gen Annotations</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.emf.codegen.ecore.genmodel.GenModelPackage#getGenBase()
 * @model abstract="true"
 * @generated
 */
public interface GenBase extends EObject{
  /**
   * Returns the value of the '<em><b>Gen Annotations</b></em>' containment reference list.
   * The list contents are of type {@link org.eclipse.emf.codegen.ecore.genmodel.GenAnnotation}.
   * It is bidirectional and its opposite is '{@link org.eclipse.emf.codegen.ecore.genmodel.GenAnnotation#getGenBase <em>Gen Base</em>}'.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Gen Annotations</em>' containment reference list isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Gen Annotations</em>' containment reference list.
   * @see org.eclipse.emf.codegen.ecore.genmodel.GenModelPackage#getGenBase_GenAnnotations()
   * @see org.eclipse.emf.codegen.ecore.genmodel.GenAnnotation#getGenBase
   * @model type="org.eclipse.emf.codegen.ecore.genmodel.GenAnnotation" opposite="genBase" containment="true"
   * @generated
   */
  EList getGenAnnotations();

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @model
   * @generated
   */
  GenAnnotation getGenAnnotation(String source);

  GenModel getGenModel();

  String capName(String name);
  String uncapName(String name);
  String uncapPrefixedName(String in);
  String safeName(String name);

  /**
   * @deprecated in 2.2. Please use {@link org.eclipse.emf.codegen.util.CodeGenUtil#format(String, char, String, boolean, boolean)} instead.
   */
  String format(String name, char separator, String prefix, boolean includePrefix);

  boolean canGenerate();
  void generate(Monitor progressMonitor);
  void gen(Monitor progressMonitor);
  void generate(IProgressMonitor progressMonitor);

  //
  // EMFEdit generation
  //
  boolean canGenerateEdit();
  void generateEdit(Monitor progressMonitor);
  void genEdit(Monitor progressMonitor);
  void generateEdit(IProgressMonitor progressMonitor);

  boolean canGenerateEditor();
  void generateEditor(Monitor progressMonitor);
  void genEditor(Monitor progressMonitor);
  void generateEditor(IProgressMonitor progressMonitor);

  boolean canGenerateSchema();
  void generateSchema(Monitor progressMonitor);
  void genSchema(Monitor progressMonitor);
  void generateSchema(IProgressMonitor progressMonitor);

  boolean canGenerateTests();
  void generateTests(Monitor progressMonitor);
  void genTests(Monitor progressMonitor);
  void generateTests(IProgressMonitor progressMonitor);

  boolean reconcile();

  boolean hasDocumentation();
  String getDocumentation(String indentation);
}
