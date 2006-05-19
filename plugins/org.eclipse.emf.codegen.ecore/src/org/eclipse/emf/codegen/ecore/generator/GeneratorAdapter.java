/**
 * <copyright>
 *
 * Copyright (c) 2006 IBM Corporation and others.
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
 * $Id: GeneratorAdapter.java,v 1.2 2006/05/19 22:31:12 davidms Exp $
 */
package org.eclipse.emf.codegen.ecore.generator;

import java.util.Collection;

import org.eclipse.emf.common.util.Diagnostic;
import org.eclipse.emf.common.util.Monitor;

/**
 * @since 2.2.0
 */
public interface GeneratorAdapter
{
  GeneratorAdapterFactory getAdapterFactory();
  void setAdapterFactory(GeneratorAdapterFactory adapterFactory);

  Collection getCanGenerateChildren(Object object, Object projectType);
  Object getCanGenerateParent(Object object, Object projectType);
  boolean canGenerate(Object object, Object projectType);
  
  Collection getGenerateChildren(Object object, Object projectType);
  Object getGenerateParent(Object object, Object projectType);
  Diagnostic preGenerate(Object object, Object projectType);
  Diagnostic generate(Object object, Object projectType, Monitor monitor);
  Diagnostic postGenerate(Object object, Object projectType);

  void dispose();
}
