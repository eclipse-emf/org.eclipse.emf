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
 * $Id: JAnnotationTypeMember.java,v 1.2 2007/06/12 20:56:05 emerks Exp $
 */
package org.eclipse.emf.codegen.merge.java.facade;

/**
 * Annotation type element in the following format (JLS3 9.6):
 * 
 * <blockquote><pre><em>
 * <code>AbstractMethodModifiers<sub>opt</sub> Type Identifier ( ) DefaultValue<sub>opt</sub> ;
 * </em>
 * </pre></blockquote>
 * 
 * @see <a href="http://java.sun.com/docs/books/jls/third_edition/html/interfaces.html">JLS3 Section 9.6</a>
 * @since 2.3.0
 */
public interface JAnnotationTypeMember extends JMember
{
  /**
   * Returns the string representation of the default value. If there is no default value,
   * returns empty string.
   * @return default value, empty string if none
   */
  String getDefaultValue();
  
  /**
   * Sets default value.
   * @param defaultValue default value to use, empty string or null if none
   */
  void setDefaultValue(String defaultValue);
  
  /**
   * Returns the string representation of the type.
   * @return type
   */
  String getType();
  
  /**
   * @param type to set
   */
  void setType(String type);
}
