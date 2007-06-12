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
 * $Id: JEnum.java,v 1.2 2007/06/12 20:56:05 emerks Exp $
 */
package org.eclipse.emf.codegen.merge.java.facade;

/**
 * Enumeration declaration as described in JLS3 8.9.
 * 
 * <blockquote><pre><em>
 * EnumDeclaration:
 * &nbsp;&nbsp;ClassModifiers<sub>opt</sub></em> enum<em> Identifier Interfaces<sub>opt</sub> EnumBody
 * 
 * EnumBody:
 * &nbsp;&nbsp;{ EnumConstants<sub>opt ,opt</sub> EnumBodyDeclarations<sub>opt</sub> }</em>
 * </pre></blockquote>
 * 
 * @see <a href="http://java.sun.com/docs/books/jls/third_edition/html/classes.html">JLS3 Section 8.9</a>
 * @since 2.3.0
 */
public interface JEnum extends JAbstractType
{
  /**
   * Returns ordered array of super interfaces as declared in source
   * in "implements" clause.
   * @return array of interfaces, empty array if none
   */
  String[] getSuperInterfaces();
  
  /**
   * Sets ordered super interfaces array of super interfaces to the given
   * array.
   * @param superInterfaces
   */
  void setSuperInterfaces(String[] superInterfaces);
  
  /**
   * Adds an interface to the list of interfaces.
   * @param superInterface
   */
  void addSuperInterface(String superInterface);
}
