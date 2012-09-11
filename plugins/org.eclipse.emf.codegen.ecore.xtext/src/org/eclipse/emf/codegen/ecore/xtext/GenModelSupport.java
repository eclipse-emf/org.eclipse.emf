/**
 * Copyright (c) 2010 itemis AG (http://www.itemis.eu) and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package org.eclipse.emf.codegen.ecore.xtext;


import org.eclipse.xtext.resource.generic.AbstractGenericResourceSupport;

import com.google.inject.Module;


/**
 *
 * This class is intended to be used from an MWE workflow.
 *
 * It instantiates and registers the GenModel support for Xtext, which allows for referencing GenModels from any Xtext
 * language.
 *
 * Usage:
 *
 * <pre>
 *    component = org.eclipse.emf.codegen.ecore.xtext.GenModelSupport{}
 * </pre>
 *
 * If you want to provide a different guice guiceModule than the default one ({@link GenModelRuntimeModule}) in order to
 * change any implementation classes, you can make use of the property guiceModule. E.g. :
 *
 * <pre>
 *    component = org.eclipse.emf.codegen.ecore.xtext.GenModelSupport{
 *       guiceModule = my.special.CustomizedGenModelRuntimeModule {}
 *    }
 * </pre>
 *
 * @author Sven Efftinge - Initial contribution and API
 */
public class GenModelSupport extends AbstractGenericResourceSupport
{
  @Override
  protected Module createGuiceModule()
  {
    return new GenModelRuntimeModule();
  }
}
