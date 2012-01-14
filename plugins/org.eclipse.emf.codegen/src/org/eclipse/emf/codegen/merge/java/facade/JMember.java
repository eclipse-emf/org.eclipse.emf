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
 * An <code>JMember</code> defines functionality common to nodes, which
 * can be members of types.
 * @see JType
 * @see JMethod
 * @see JField
 * @see JInitializer
 * @since 2.2.0
 */
public interface JMember extends JNode
{
  /**
   * Returns the comment associated with this member (including comment delimiters).
   *
   * @return the comment, or <code>null</code> if this member has no associated
   *   comment
   */
  String getComment();
  
  /**
   * Sets the comment associated with this member. The comment will appear
   * before the member in the source. The comment must be properly formatted, including
   * delimiters. A <code>null</code> comment indicates no comment.
   *
   * @param comment the comment, including comment delimiters, or 
   *   <code>null</code> indicating this member should have no associated comment
   */
  void setComment(String comment);  
}
