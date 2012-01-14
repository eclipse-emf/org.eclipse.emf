/**
 * Copyright (c) 2002-2004 IBM Corporation and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: 
 *   IBM - Initial API and implementation
 */
package org.eclipse.emf.codegen.jet;



/**
 * The core elements we recognize; 
 * these are stateless abstractions that represent the parsing action for a core tag.
 */
public interface JETCoreElement 
{
  /**
   * Return true if the input contained the sequence that matched
   * the action corresponding to this core tag.
   */
  boolean accept(JETParseEventListener listener, JETReader reader, JETParser parser) throws JETException;
}
