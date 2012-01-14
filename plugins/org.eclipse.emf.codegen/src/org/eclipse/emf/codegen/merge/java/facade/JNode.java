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

import java.util.List;


/**
 * Represents an entire Java compilation unit (<code>.java</code> source file).
 * Compilation unit elements need to be opened before they can be navigated.
 * The children are of type {@link JPackage}, {@link JImport}, 
 * and {@link JType}, and appear in the order in which they are declared in the source.
 * If a <code>.java</code> file cannot be parsed, its structure remains unknown.
 *  
 * @since 2.2.0
 */
public interface JNode
{
  /**
   * Returns the name of this node.
   * @return the name, or <code>null</code> if it has no name
   */
  String getName();
  
  /**
   * Sets the name of this node.  If the name is used to match the nodes 
   * to be merged, it is highly recommended not to use this method.
   * @param name
   * @see #getQualifiedName()
   * @since 2.3.0
   */
  void setName(String name);

  /**
   * Returns the qualified name of this node.  This value is not
   * expected to change as the node is modified.
   * @return the qualified name of this type
   */
  String getQualifiedName();  
  
  /**
   * Returns the current contents of this document fragment.
   * <p>
   * Note: To obtain complete source for the ".java" file, ask a compilation unit
   * node for its contents.
   * </p>
   *
   * @return the contents, or <code>null</code> if this node has no contents
   */
  String getContents();
  
  /**
   * Returns the modifier flags for this node. The flags can be examined using 
   * class {@link FacadeFlags}.
   */
  int getFlags(); 
  
  /**
   * Sets the flags for this member. The flags can be examined using the
   * {@link FacadeFlags} class.
   *
   * @param flags the flags
   */
  void setFlags(int flags);  
  
  /**
   * Returns the parent of this node.
   *
   * @return the parent node, or <code>null</code> if this node does not have a 
   *   parent
   */
  JNode getParent();  
  
  /**
   * Returns the children of this node. Returns an empty list
   * if this node has no children (including nodes that cannot have children). 
   * Children appear in the order in which they are declared in the source code.
   * <p>
   * The list must be unmodifiable if the implementation doesn't support direct manipulation.
   * </p>
   *
   * @return a list of the children
   */
  List<JNode> getChildren();  
}
