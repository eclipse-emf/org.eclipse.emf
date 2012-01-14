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


import org.eclipse.emf.common.util.EList;

import org.eclipse.core.runtime.IProgressMonitor;

import org.eclipse.emf.common.util.Monitor;
import org.eclipse.emf.ecore.EModelElement;
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
public interface GenBase extends EObject
{
  /**
   * Returns the value of the '<em><b>Gen Annotations</b></em>' containment reference list.
   * The list contents are of type {@link org.eclipse.emf.codegen.ecore.genmodel.GenAnnotation}.
   * It is bidirectional and its opposite is '{@link org.eclipse.emf.codegen.ecore.genmodel.GenAnnotation#getGenBase <em>Gen Base</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the value of the '<em>Gen Annotations</em>' containment reference list.
   * @see org.eclipse.emf.codegen.ecore.genmodel.GenModelPackage#getGenBase_GenAnnotations()
   * @see org.eclipse.emf.codegen.ecore.genmodel.GenAnnotation#getGenBase
   * @model opposite="genBase" containment="true"
   * @generated
   */
  EList<GenAnnotation> getGenAnnotations();

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
  @Deprecated
  String format(String name, char separator, String prefix, boolean includePrefix);

  boolean canGenerate();

  /**
   * @deprecated In EMF 2.2, a {@link org.eclipse.emf.codegen.ecore.generator.Generator Generator} should be used to generate code.
   * This method will be removed after 2.2.
   */
  @Deprecated
  void generate(Monitor progressMonitor);

  /**
   * @deprecated In EMF 2.2, a {@link org.eclipse.emf.codegen.ecore.generator.Generator Generator} should be used to generate code.
   * This method will be removed after 2.2.
   */
  @Deprecated
  void gen(Monitor progressMonitor);

  /**
   * @deprecated In EMF 2.2, a {@link org.eclipse.emf.codegen.ecore.generator.Generator Generator} should be used to generate code.
   * This method will be removed after 2.2.
   */
  @Deprecated
  void generate(IProgressMonitor progressMonitor);

  //
  // EMFEdit generation
  //
  boolean canGenerateEdit();

  /**
   * @deprecated In EMF 2.2, a {@link org.eclipse.emf.codegen.ecore.generator.Generator Generator} should be used to generate code.
   * This method will be removed after 2.2.
   */
  @Deprecated
  void generateEdit(Monitor progressMonitor);

  /**
   * @deprecated In EMF 2.2, a {@link org.eclipse.emf.codegen.ecore.generator.Generator Generator} should be used to generate code.
   * This method will be removed after 2.2.
   */
  @Deprecated
  void genEdit(Monitor progressMonitor);

  /**
   * @deprecated In EMF 2.2, a {@link org.eclipse.emf.codegen.ecore.generator.Generator Generator} should be used to generate code.
   * This method will be removed after 2.2.
   */
  @Deprecated
  void generateEdit(IProgressMonitor progressMonitor);

  boolean canGenerateEditor();

  /**
   * @deprecated In EMF 2.2, a {@link org.eclipse.emf.codegen.ecore.generator.Generator Generator} should be used to generate code.
   * This method will be removed after 2.2.
   */
  @Deprecated
  void generateEditor(Monitor progressMonitor);

  /**
   * @deprecated In EMF 2.2, a {@link org.eclipse.emf.codegen.ecore.generator.Generator Generator} should be used to generate code.
   * This method will be removed after 2.2.
   */
  @Deprecated
  void genEditor(Monitor progressMonitor);

  /**
   * @deprecated In EMF 2.2, a {@link org.eclipse.emf.codegen.ecore.generator.Generator Generator} should be used to generate code.
   * This method will be removed after 2.2.
   */
  @Deprecated
  void generateEditor(IProgressMonitor progressMonitor);

  /**
   * @deprecated In EMF 2.2, schema generation is properly done via a model exporter. This method will be removed after 2.2.
   */
  @Deprecated
  boolean canGenerateSchema();

  /**
   * @deprecated In EMF 2.2, schema generation is properly done via a model exporter. This method will be removed after 2.2.
   */
  @Deprecated
  void generateSchema(Monitor progressMonitor);

  /**
   * @deprecated In EMF 2.2, schema generation is properly done via a model exporter. This method will be removed after 2.2.
   */
  @Deprecated
  void genSchema(Monitor progressMonitor);

  /**
   * @deprecated In EMF 2.2, schema generation is properly done via a model exporter. This method will be removed after 2.2.
   */
  @Deprecated
  void generateSchema(IProgressMonitor progressMonitor);

  boolean canGenerateTests();

  /**
   * @deprecated In EMF 2.2, a {@link org.eclipse.emf.codegen.ecore.generator.Generator Generator} should be used to generate code.
   * This method will be removed after 2.2.
   */
  @Deprecated
  void generateTests(Monitor progressMonitor);

  /**
   * @deprecated In EMF 2.2, a {@link org.eclipse.emf.codegen.ecore.generator.Generator Generator} should be used to generate code.
   * This method will be removed after 2.2.
   */
  @Deprecated
  void genTests(Monitor progressMonitor);

  /**
   * @deprecated In EMF 2.2, a {@link org.eclipse.emf.codegen.ecore.generator.Generator Generator} should be used to generate code.
   * This method will be removed after 2.2.
   */
  @Deprecated
  void generateTests(IProgressMonitor progressMonitor);

  boolean reconcile();

  boolean hasDocumentation();
  String getDocumentation(String indentation);
  
  EModelElement getEcoreModelElement();

  /**
   * @since 2.3
   */
  boolean hasCopyright();

  /**
   * @since 2.3
   */
  String getCopyright(String indentation);
}
