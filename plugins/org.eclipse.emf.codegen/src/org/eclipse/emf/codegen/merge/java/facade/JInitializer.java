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
 * Represents an initializer. The corresponding syntactic
 * units are InstanceInitializer (JLS2 8.6) and StaticDeclaration (JLS2 8.7).
 * An initializer has no children and its parent is a type.
 * @since 2.2.0
 */
public interface JInitializer extends JMember
{
  /**
   * Returns the body of this initializer. The syntax for a body corresponds to
   * InstanceInitializer (JLS2 8.6) and StaticDeclaration (JLS2 8.7).
   *
   * @return an initializer body, including braces, or <code>null</code> if
   *   no body is present
   */
  String getBody();
  
  /**
   * Sets the body of this initializer. The syntax for a body corresponds to
   * InstanceInitializer (JLS2 8.6) and StaticDeclaration (JLS2 8.7). No formatting
   * or syntax checking is performed on the body. Braces <b>must</b> be included.
   *
   * @param body an initializer body, including braces, or <code>null</code> 
   *   indicating no body
   */
  void setBody(String body);  
}
