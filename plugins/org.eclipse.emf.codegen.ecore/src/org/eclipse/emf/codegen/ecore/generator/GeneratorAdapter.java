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
 * $Id: GeneratorAdapter.java,v 1.5 2007/06/12 20:56:34 emerks Exp $
 */
package org.eclipse.emf.codegen.ecore.generator;

import java.util.Collection;

import org.eclipse.emf.common.util.Diagnostic;
import org.eclipse.emf.common.util.Monitor;

/**
 * An adapter that performs code generation for a {@link Generator}.
 * 
 * <p>A <code>GeneratorAdapter</code> implementation performs code generation for a single type of model object. It is
 * created by a {@link GeneratorAdapterFactory} and used by its containing {@link Generator}. Several different 
 * generator adapters can be associated with a single object, with each one contributing functionality to the code
 * generation for that object. A singleton pattern is usually used for generator adapters, where a single instance of an
 * adapter type is shared by all instances of the same object type. 
 * 
 * <p>A generator adapter is primarily responsible for two aspects of two tasks. The tasks are:
 * <ul>
 * <li>Determining whether code generation is possible for an object.
 * <li>Generating code for an object.
 * </ul>
 * 
 * <p>The two aspects of these tasks are:
 * <ul>
 * <li>Assembling the tree of relevant objects for the task.
 * <li>Performing the task for each object.
 * </ul>
 * 
 * <p>In all cases, a project type can be used to identify a subset of the code generation for the given object. This
 * is an arbitrary object that should be meaningful to the particular generator adapter. 
 * 
 * <p>See {@link Generator#canGenerate(Object, Object)} and {@link Generator#generate(Object, Object, String, Monitor)}
 * for detailed descriptions of the protocol by which generator adapters are actually used to complete the two tasks.
 * 
 * @see Generator#canGenerate(Object, Object)
 * @see Generator#generate(Object, Object, String, Monitor)
 * @since 2.2.0
 */
public interface GeneratorAdapter
{
  /**
   * Returns the adapter factory that created this adapter.
   * 
   * @see #setAdapterFactory(GeneratorAdapterFactory)
   */
  GeneratorAdapterFactory getAdapterFactory();

  /**
   * Sets the adapter factory that created this adapter. If the constructor for the adapter does not set the adapter
   * factory, this method can be called immediately after the adapter is created.
   * 
   * @see #getAdapterFactory()
   */
  void setAdapterFactory(GeneratorAdapterFactory adapterFactory);

  /**
   * Returns any children of the specified object that are relevant to determining whether code, of the given project
   * type, can be generated for the specified object.
   */
  Collection<?> getCanGenerateChildren(Object object, Object projectType);

  /**
   * Returns the parent of the specified object if it is relevant to determining whether code, of the given project
   * type, can be generated for the specified object.
   */
  Object getCanGenerateParent(Object object, Object projectType);

  /**
   * Returns whether code of the given object type can be generated for the specified object.
   */
  boolean canGenerate(Object object, Object projectType);

  /**
   * Returns any children of the specified object for which code of the given project type may be generated.
   */  
  Collection<?> getGenerateChildren(Object object, Object projectType);

  /**
   * Returns the parent of the specified object if code of the given project type may be generated for it.
   */  
  Object getGenerateParent(Object object, Object projectType);

  /**
   * Called before any code is generated for the object and/or any of its parents and/or children, in order to give
   * the adapter a chance to perform setup for the code generation.
   */
  Diagnostic preGenerate(Object object, Object projectType);

  /**
   * Generates code for the given object and project type. Because this is a long-running operation, the monitor is
   * used to report progress.
   */
  Diagnostic generate(Object object, Object projectType, Monitor monitor);

  /**
   * Called after all code is generated for the object and/or any of its parents and/or children, in order to give
   * the adapter a chance to perform cleanup from the code generation.
   */
  Diagnostic postGenerate(Object object, Object projectType);

  /**
   * Removes the adapter from its {@link org.eclipse.emf.common.notify.Notifier Notifier}s and performs any other needed
   * disposal.
   */
  void dispose();
}
