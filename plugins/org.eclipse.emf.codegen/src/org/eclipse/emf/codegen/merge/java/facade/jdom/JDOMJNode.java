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
 * $Id: JDOMJNode.java,v 1.1 2006/01/18 20:42:15 marcelop Exp $
 */

package org.eclipse.emf.codegen.merge.java.facade.jdom;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;
import java.util.List;

import org.eclipse.jdt.core.jdom.IDOMInitializer;
import org.eclipse.jdt.core.jdom.IDOMNode;

import org.eclipse.emf.codegen.merge.java.facade.AbstractJNode;
import org.eclipse.emf.codegen.merge.java.facade.FacadeFlags;
import org.eclipse.emf.codegen.merge.java.facade.JCompilationUnit;
import org.eclipse.emf.codegen.merge.java.facade.JNode;

/**
 * @since 2.2.0
 */
public abstract class JDOMJNode extends AbstractJNode
{
  protected JDOMJNode(IDOMNode idomNode)
  {
    super(idomNode);
  }

  protected IDOMNode getIDOMNode()
  {
    return (IDOMNode)getWrappedObject();
  }
  
  public String getName()
  {
    return getIDOMNode().getName();
  }
  
  public int getFlags()
  {
    return FacadeFlags.DEFAULT;
  }
  
  public void setFlags(int flags)
  {
    // No op
  }

  public String getContents()
  {
    return getIDOMNode().getContents();
  }

  public JNode getParent()
  {
    return getFacadeHelper().convertToNode(getIDOMNode().getParent());
  }

  public List getChildren()
  {
    List children = new ArrayList();
    for (Enumeration e = getIDOMNode().getChildren(); e.hasMoreElements();)
    {
      IDOMNode node = (IDOMNode)e.nextElement();
      JNode jNode = getFacadeHelper().convertToNode(node);
      children.add(jNode);
    }
    return Collections.unmodifiableList(children);
  }
}
