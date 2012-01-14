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
 * Converts one node to another node type.
 */
public interface NodeConverter
{
  /**
   * Converts the specified abstract type to an instance of the given class and returns the converted node.
   * Original node must be removed from the tree, and returned node inserted in its place.
   * All children of the removed node may be moved to the converted node if needed.
   * 
   * @param abstractType type to convert
   * @param cls class of the converted node
   * @return converted node
   */
  JAbstractType convert(JAbstractType abstractType, Class<? extends JAbstractType> cls);
}  

