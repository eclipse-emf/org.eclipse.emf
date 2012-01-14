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
 * Enum constant as described in JLS3 8.9.
 * 
 * <blockquote><pre><em>
 * EnumConstants:
 * &nbsp;&nbsp;EnumConstant
 * &nbsp;&nbsp;EnumConstants , EnumConstant
 * 
 * EnumConstant:
 * &nbsp;&nbsp;Annotations Identifier Arguments<sub>opt</sub> ClassBody<sub>opt</sub>
 * 
 * Arguments:
 * &nbsp;&nbsp;( ArgumentList<sub>opt</sub> )
 * 
 * EnumBodyDeclarations:
 * &nbsp;&nbsp;; ClassBodyDeclarations<sub>opt</sub>
 * </em>
 * </pre></blockquote>
 * 
 * @see <a href="http://java.sun.com/docs/books/jls/third_edition/html/classes.html">JLS3 Section 8.9</a>
 * @since 2.3.0
 *
 */
public interface JEnumConstant extends JMember
{
  /**
   * Returns an ordered list of arguments as declared in source.
   * @return arguments, or empty array if none
   */
  String[] getArguments();
  
  /**
   * @param arguments
   */
  void setArguments(String[] arguments);
  
  /**
   * Returns body of the enum constant.
   * @return body, empty string if none
   */
  String getBody();
  
  /**
   * @param body
   */
  void setBody(String body);
}
