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
 * $Id: JCompilationUnit.java,v 1.1 2006/01/18 20:42:16 marcelop Exp $
 */
package org.eclipse.emf.codegen.merge.java.facade;


/**
 * Represents an entire Java compilation unit (<code>.java</code> source file).
 * Compilation unit elements need to be opened before they can be navigated.
 * The children are of type {@link JPackage}, {@link JImport}, 
 * and {@link JType}, and appear in the order in which they are declared in the source.
 * If a <code>.java</code> file cannot be parsed, its structure remains unknown.
 * Use {@link JNode#isStructureKnown} to determine whether this is 
 * the case.
 *  
 * @since 2.2.0
 */
public interface JCompilationUnit extends JNode
{
  /**
   * Returns the header comment for this compilation unit. The header comment
   * appears before the first declaration in a compilation unit.
   *
   * @return the header comment for this compilation unit, or <code>null</code> if
   *   no header comment is present
   */
  String getHeader();
}
