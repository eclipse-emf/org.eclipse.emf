/**
 * <copyright> 
 *
 * Copyright (c) 2002-2004 IBM Corporation and others.
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
 * $Id: RoseWalker.java,v 1.1 2004/03/06 17:31:31 marcelop Exp $
 */
package org.eclipse.emf.codegen.ecore.rose2ecore;


import java.util.List;

import org.eclipse.emf.codegen.ecore.rose2ecore.parser.RoseNode;


/**
 * Traverses the Rose file a second time to create mapping object.
 */
public class RoseWalker
{
  private RoseNode roseNode;
  private RoseVisitor visitor;

  public RoseWalker(RoseNode roseNode)
  {
    this.roseNode = roseNode;
  }

  public void traverse(RoseVisitor visitor)
  {
    this.visitor = visitor;
    traverseObject(roseNode); 
  }

  private void traverseObject(RoseNode roseNode)
  {
    visitor.visitObject(roseNode);
    traverse(roseNode);
  }

  private void traverseList(RoseNode roseNode)
  {
    visitor.visitList(roseNode);
    traverse(roseNode);
  }

  private void traverse(RoseNode roseNode)
  {
    List nodes = roseNode.getNodes();
    for (int i = 0; i < nodes.size(); i++) 
    {
      RoseNode node = (RoseNode) nodes.get(i);
      if (node.getRoseNodeType() == RoseNode.OBJECT)
      {
        traverseObject(node);
      }
      else if (node.getRoseNodeType() == RoseNode.LIST)
      {
        traverseList(node);
      }
    }
  }
}
