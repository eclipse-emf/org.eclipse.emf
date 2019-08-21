/**
 * Copyright (c) 2002-2004 IBM Corporation and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 * 
 * Contributors: 
 *   IBM - Initial API and implementation
 */
package org.eclipse.emf.codegen.jet;


/**
 * The interface that all generators implement.
 *
 */
public interface JETGenerator
{
  /**
   * An interface implemented by generators that depend on the name of the builder.
   * @since 2.19
   */
  interface BuilderSensitive
  {
    void setBuilderName(String builderName);
  }

  String generate();
}
