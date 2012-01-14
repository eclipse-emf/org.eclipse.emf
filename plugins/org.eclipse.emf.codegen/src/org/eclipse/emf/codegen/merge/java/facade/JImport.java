/**
 * Copyright (c) 2006 IBM Corporation and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: 
 *   IBM - Initial API and implementation
 */
package org.eclipse.emf.codegen.merge.java.facade;


/**
 * Represents an import declaration in Java compilation unit.
 * @since 2.2.0
 */
public interface JImport extends JNode
{
  /**
   * Returns whether this import declaration ends with <code>".*"</code>.
   * @return <code>true</code> if this in an on-demand import
   */
  boolean isOnDemand();  
}
