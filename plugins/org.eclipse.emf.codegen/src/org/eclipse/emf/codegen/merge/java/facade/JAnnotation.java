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
 * Annotation as defined by JLS3 9.7.
 * 
 * @see <a href="http://java.sun.com/docs/books/jls/third_edition/html/interfaces.html">JLS3 Section 9.7</a> 
 * @since 2.3.0
 */
public interface JAnnotation extends JNode
{
  /**
   * Overwrites the contents of the annotation with the given contents.
   * @param contents
   */
  void setContents(String contents);
}
