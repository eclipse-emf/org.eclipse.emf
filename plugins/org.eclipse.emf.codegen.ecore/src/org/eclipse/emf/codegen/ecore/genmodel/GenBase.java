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
 * $Id: GenBase.java,v 1.2 2004/07/05 03:16:50 marcelop Exp $
 */
package org.eclipse.emf.codegen.ecore.genmodel;


import org.eclipse.core.runtime.IProgressMonitor;

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
public interface GenBase extends EObject{
  GenModel getGenModel();

  String capName(String name);
  String uncapName(String name);
  String uncapPrefixedName(String in);
  String safeName(String name);
  String format(String name, char separator, String prefix, boolean includePrefix);

  boolean canGenerate();
  void generate(IProgressMonitor progressMonitor);

  GenModelPackage ePackageGenModel();

  //
  // EMFEdit generation
  //
  boolean canGenerateEdit();
  void generateEdit(IProgressMonitor progressMonitor);

  boolean canGenerateEditor();
  void generateEditor(IProgressMonitor progressMonitor);

  boolean canGenerateSchema();
  void generateSchema(IProgressMonitor progressMonitor);
  
  boolean reconcile();

  boolean hasDocumentation();
  String getDocumentation(String indentation);
}
