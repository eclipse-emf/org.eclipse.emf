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
 * $Id: GenBase.java,v 1.7 2005/11/18 12:07:27 emerks Exp $
 */
package org.eclipse.emf.codegen.ecore.genmodel;


import org.eclipse.core.runtime.IProgressMonitor;

import org.eclipse.emf.common.util.Monitor;
import org.eclipse.emf.ecore.EObject;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Gen Base</b></em>'.
 * <!-- end-user-doc -->
 *
 *
 * @see org.eclipse.emf.codegen.ecore.genmodel.GenModelPackage#getGenBase()
 * @model abstract="true"
 * @generated
 */
public interface GenBase extends EObject
{
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
