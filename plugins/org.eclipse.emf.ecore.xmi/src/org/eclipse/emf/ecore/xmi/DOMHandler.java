/**
 * <copyright>
 *
 * Copyright (c) 2004 IBM Corporation and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Common Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/cpl-v10.html
 *
 * Contributors:
 *   IBM - Initial API and implementation
 *
 * </copyright>
 *
 * $Id: DOMHandler.java,v 1.1 2004/12/23 19:32:59 elena Exp $
 */
package org.eclipse.emf.ecore.xmi;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.w3c.dom.Node;

/**
 * This interface is a handler that is called by {@link XMLSave} while converting to DOM, to allow application
 * to record EMF values that were used to create a DOM node {@link DOMHandler#recordEObject(Node, EObject, EObject, EStructuralFeature)}.
 * This interface also provides methods which given a DOM node return corresponding EMF objects.
 * @since 2.1.0
 */
public interface DOMHandler
{
  /**
   * Record the DOM node and its corresponding EMF values.
   * @param node the DOM node
   * @param object the {@link org.eclipse.emf.ecore.EObject} which the node was created from, or null
   * @param container the container of the value from which DOM node was created
   * @param feature the feature for DOM node value
   */
  void recordEObject(Node node, EObject object, EObject container, EStructuralFeature feature);
  
  /**
   * @param node the DOM node
   * @return the corresponding {@link org.eclipse.emf.ecore.EObject} or null
   */
  EObject getEObject(Node node);
  
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

}
