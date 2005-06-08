/**
 * <copyright>
 *
 * Copyright (c) 2005 IBM Corporation and others.
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
 * $Id: DOMHelper.java,v 1.2 2005/06/08 06:16:07 nickb Exp $
 */
package org.eclipse.emf.ecore.xmi;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.w3c.dom.Node;

/**
 * This interface provides methods which given a DOM node return corresponding EMF objects.
 * @since 2.1.0
 */
public interface DOMHelper
{
  /**
   * @param node the DOM node
   * @return the {@link org.eclipse.emf.ecore.EObject} container that holds the DOM node.
   */
  EObject getContainer(Node node);
  
  /**
   * @param node the DOM node
   * @return the feature for the DOM node value.
   */
  EStructuralFeature getEStructuralFeature(Node node);
  
  /**
   * @param node the attribute {@link org.w3c.dom.Attr} or element {@link org.w3c.dom.Element} node
   * @return the corresponding EMF value, one of:
   *         {@link org.eclipse.emf.ecore.EObject} 
   *         {@link org.eclipse.emf.ecore.util.FeatureMap.Entry}
   *         {@link java.util.List}
   *         or Java type (e.g. {@link java.lang.String}
   */
  Object getValue(Node node);
  
}
