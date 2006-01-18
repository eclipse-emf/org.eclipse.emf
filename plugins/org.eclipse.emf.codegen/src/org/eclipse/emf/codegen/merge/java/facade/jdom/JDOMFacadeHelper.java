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
 * $Id: JDOMFacadeHelper.java,v 1.1 2006/01/18 20:42:15 marcelop Exp $
 */
package org.eclipse.emf.codegen.merge.java.facade.jdom;


import org.eclipse.jdt.core.jdom.DOMFactory;
import org.eclipse.jdt.core.jdom.IDOMCompilationUnit;
import org.eclipse.jdt.core.jdom.IDOMField;
import org.eclipse.jdt.core.jdom.IDOMImport;
import org.eclipse.jdt.core.jdom.IDOMInitializer;
import org.eclipse.jdt.core.jdom.IDOMMethod;
import org.eclipse.jdt.core.jdom.IDOMNode;
import org.eclipse.jdt.core.jdom.IDOMPackage;
import org.eclipse.jdt.core.jdom.IDOMType;

import org.eclipse.emf.codegen.merge.java.facade.FacadeHelper;
import org.eclipse.emf.codegen.merge.java.facade.JCompilationUnit;
import org.eclipse.emf.codegen.merge.java.facade.JField;
import org.eclipse.emf.codegen.merge.java.facade.JImport;
import org.eclipse.emf.codegen.merge.java.facade.JInitializer;
import org.eclipse.emf.codegen.merge.java.facade.JMethod;
import org.eclipse.emf.codegen.merge.java.facade.JNode;
import org.eclipse.emf.codegen.merge.java.facade.JPackage;
import org.eclipse.emf.codegen.merge.java.facade.JType;


public class JDOMFacadeHelper extends FacadeHelper
{
  public static IDOMNode getIDOMNode(JNode node)
  {
    return node instanceof JDOMJNode ? ((JDOMJNode)node).getIDOMNode() : null;
  }

  protected DOMFactory jdomFactory;

  public void reset()
  {
    jdomFactory = null;
    super.reset();
  }
  
  protected DOMFactory getJDOMFactory()
  {
    if (jdomFactory == null)
    {
      jdomFactory = new DOMFactory();
    }
    return jdomFactory;
  }

  public JCompilationUnit createCompilationUnit(String name, String content)
  {
    return (JCompilationUnit)convertToNode(getJDOMFactory().createCompilationUnit(content, name));
  }

  protected JNode doConvertToNode(Object object)
  {
    JDOMJNode node = null;
    if (object instanceof IDOMCompilationUnit)
    {
      node = new JDOMJCompilationUnit((IDOMCompilationUnit)object);
    }
    else if (object instanceof IDOMField)
    {
      node = new JDOMJField((IDOMField)object);
    }
    else if (object instanceof IDOMImport)
    {
      node = new JDOMJImport((IDOMImport)object);
    }
    else if (object instanceof IDOMInitializer)
    {
      node = new JDOMJInitializer((IDOMInitializer)object);
    }
    else if (object instanceof IDOMMethod)
    {
      node = new JDOMJMethod((IDOMMethod)object);
    }
    else if (object instanceof IDOMPackage)
    {
      node = new JDOMJPackage((IDOMPackage)object);
    }
    else if (object instanceof IDOMType)
    {
      node = new JDOMJType((IDOMType)object);
    }
    
    if (node != null)
    {
      node.setFacadeHelper(this);
    }
    return node;
  }
  
  public Object getContext(JNode node)
  {
    return null;
  }

  public JNode cloneNode(Object context, JNode node)
  {
    String content = applyFormatRules(node.getContents());
    IDOMNode idomNode = null;
    if (node instanceof JCompilationUnit)
    {
      idomNode = getIDOMNode(createCompilationUnit(node.getName(), content));
    }
    else if (node instanceof JField)
    {
      idomNode = getJDOMFactory().createField(content);
    }
    else if (node instanceof JImport)
    {
      idomNode = getJDOMFactory().createImport(content);
    }
    else if (node instanceof JInitializer)
    {
      idomNode = getJDOMFactory().createInitializer(content);
    }
    else if (node instanceof JMethod)
    {
      idomNode = getJDOMFactory().createMethod(content);
    }
    else if (node instanceof JPackage)
    {
      idomNode = getJDOMFactory().createPackage(content);
    }
    else if (node instanceof JType)
    {
      idomNode = getJDOMFactory().createType(content);
    }
    else
    {
      IDOMNode originalIDOMNode = JDOMFacadeHelper.getIDOMNode(node);
      if (originalIDOMNode != null)
      {
        idomNode = (IDOMNode)originalIDOMNode.clone();
      }
    }

    return idomNode != null ? convertToNode(idomNode) : null;
  }
  
  public boolean addChild(JNode node, JNode child)
  {
    IDOMNode idomNode = getIDOMNode(node);
    IDOMNode idomChild = getIDOMNode(child);
    idomNode.addChild(idomChild);
    return true;
  }
  
  public boolean insertSibling(JNode node, JNode sibiling, boolean before)
  {
    IDOMNode idomNode = getIDOMNode(node);
    if (!before)
    {
      idomNode = idomNode.getNextNode();
    }
    IDOMNode idomSibiling = getIDOMNode(sibiling);
    idomNode.insertSibling(idomSibiling);
    return true;
  }
  
  public boolean remove(JNode node)
  {    
    IDOMNode idomNode = getIDOMNode(node);
    IDOMNode parent = idomNode.getParent();
    if (parent != null)
    {
      idomNode.remove();
      return true;
    }
    return false; 
  }
  
  public JNode getFirstChild(JNode node)
  {
    IDOMNode idomNode = getIDOMNode(node);
    return convertToNode(idomNode.getFirstChild());
  }
  
  public JNode getPrevious(JNode node)
  {
    IDOMNode idomNode = getIDOMNode(node);
    return convertToNode(idomNode.getPreviousNode());
  }
  
  public JNode getNext(JNode node)
  {
    IDOMNode idomNode = getIDOMNode(node);
    return convertToNode(idomNode.getNextNode());
  }  
}