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
 * $Id: DOMHandler.java,v 1.2 2005/01/25 18:45:01 elena Exp $
 */
package org.eclipse.emf.ecore.xmi;


import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.w3c.dom.Node;


/**
 * This interface is a handler that is called by {@link XMLSave} while converting the contents of an XMLResource to DOM, 
 * to allow application to record EMF values that were used to create a DOM node.
 * @since 2.1.0
 */
public interface DOMHandler
{
  /**
   * Record the DOM node and its corresponding EMF values.
   * @param node the DOM node
   * @param container the {@link org.eclipse.emf.ecore.EObject} which is the node's container
   * @param feature the feature for DOM node value
   * @param object the EMF value which is obtained by querying the value of the specified feature on the container 
   *               {@link  org.eclipse.emf.ecore.EObject#eGet(EStructuralFeature}; 
   *               this value is used to create the <emf>node</emf>
   */
  void recordValues(Node node, EObject container, EStructuralFeature feature, Object value);

  DOMHelper getDOMHelper();
}
