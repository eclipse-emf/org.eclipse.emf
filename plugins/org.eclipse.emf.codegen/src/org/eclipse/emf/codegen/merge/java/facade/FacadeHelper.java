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
 * $Id: FacadeHelper.java,v 1.1 2006/01/18 20:42:16 marcelop Exp $
 */
package org.eclipse.emf.codegen.merge.java.facade;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.eclipse.emf.codegen.merge.java.JControlModel;
import org.eclipse.emf.codegen.util.CodeGenUtil;

public abstract class FacadeHelper
{
  protected static final String CLASS_PREFIX = "org.eclipse.emf.codegen.merge.java.facade.J";
  
  protected JControlModel controlModel;
  protected Map objectToNodeMap;
  
  public void reset()
  {
    if (objectToNodeMap != null)
    {
      objectToNodeMap.clear();
    }   
  }
  
  protected Map getObjectToNodeMap()
  {
    if (objectToNodeMap == null)
    {
      objectToNodeMap = new HashMap();
    }
    return objectToNodeMap;
  }
  
  public String getClassPrefix()
  {
    return CLASS_PREFIX;
  }
  
  /**
   * Sets this facade helper's control model.
   * @param controlModel
   * @throws IllegalArgumentException if the control model's facade helper is
   * different than this facade helper. 
   */
  public void setControlModel(JControlModel controlModel) throws IllegalArgumentException
  {
    if (controlModel != null && controlModel.getFacadeHelper() != this)
    {
      throw new IllegalArgumentException("Invalid control model");
    }
    
    if (this.controlModel != null)
    {
      reset();
    }
    this.controlModel = controlModel;
  }
  
  public JControlModel getControlModel()
  {
    return controlModel;
  }
  
  public JNode convertToNode(Object object)
  {
    JNode node = (JNode)getObjectToNodeMap().get(object);
    if (node == null)
    {
      node = doConvertToNode(object);
      if (node != null)
      {
        getObjectToNodeMap().put(object, node);
      }
    }
    return node;    
  }
  
  /**
   * Clones the specified node, returning an object that is related to
   * the given context.  On some implementations the context may be 
   * <code>null</code>.
   * @param context
   * @param node
   * @return a cloned version of the specified node 
   */
  public abstract JNode cloneNode(Object context, JNode node);

  /**
   * Returns the context of a node.  The context is usually
   * an object that would be used to create a child or sibiling of the node.
   * @return the context of a node.
   */  
  public abstract Object getContext(JNode node);
  
  public abstract JCompilationUnit createCompilationUnit(String name, String content);
  protected abstract JNode doConvertToNode(Object object);

  /**
   * Returns the compilation unit of the specified node.
   * @param node
   * @return the {@link JCompilationUnit} of a {@link JNode} or 
   * <code>null</code>.
   */
  public JCompilationUnit getCompilationUnit(JNode node)
  {
    while(node != null)
    {
      if (node instanceof JCompilationUnit)
      {
        return (JCompilationUnit)node;
      }
      else
      {
        node = node.getParent();
      }
    }
    return null;
  }
  
  /**
   * Returns the package of the specified node.
   * @param node
   * @return the {@link JPackage} of a {@link JNode} or 
   * <code>null</code>
   */
  public JPackage getPackage(JNode node)
  {
    JCompilationUnit compilationUnit = getCompilationUnit(node);
    if (compilationUnit != null)
    {
      for (Iterator i = compilationUnit.getChildren().iterator(); i.hasNext();)
      {
        JNode child = (JNode)i.next();
        if (child instanceof JPackage)
        {
          return (JPackage)child;
        }
      }
    }
    return null;
  }
  
  /**
   * Returns the first public type in of a compilation unit. 
   * @param compilationUnit
   * @return the first public type of a compilation unti
   */
  public JType getMainType(JCompilationUnit compilationUnit)
  {
    for (Iterator i = getChildren(compilationUnit, JType.class).iterator(); i.hasNext();)
    {
      JType type = (JType)i.next();
      if (FacadeFlags.isPublic(type.getFlags()))
      {
       return type; 
      }
    }
    return null;
  }
  
  /**
   * Returns a list with the children of the specified node that are instances of
   * the given class.  The list should be an unmodifiable list if it doesn't support 
   * changes.
   * @param node
   * @param the class of the child
   * @return the list of children of a {@link JNode}
   */
  public List getChildren(JNode node, Class cls)
  {
    if (node != null && cls != null)
    {
      List children = new ArrayList();
      for (Iterator i = node.getChildren().iterator(); i.hasNext();)
      {
        JNode child = (JNode)i.next();
        if (cls.isInstance(child))
        {
          children.add(child);
        }
      }
      
      if (!children.isEmpty())
      {
        return Collections.unmodifiableList(children); 
      }
    }
    return Collections.EMPTY_LIST; 
  }

  /**
   * Returns the first child of the specified node.
   * Children appear in the order in which they exist in the source code.
   * @param node
   * @return the first child, or <code>null</code> if this node has no children
   * @see #getChildren()
   */  
  public JNode getFirstChild(JNode node)
  {
    if (node != null)
    {
      List children = node.getChildren();
      if (!children.isEmpty())
      {
        return (JNode)children.get(0);
      }
    }
    return null;
  }
  
  /**
   * Returns the sibling node immediately preceding the specified node.
   * @param node
   * @return the previous node, or <code>null</code> if there is no preceding node
   */  
  public JNode getPrevious(JNode node)
  {
    return getSibiling(node, -1);
  }  

  /**
   * Returns the sibling node immediately following the specified node.
   * @param node
   * @return the next node, or <code>null</code> if there is no following node
   */  
  public JNode getNext(JNode node)
  {
    return getSibiling(node, 1);
  }  
  
  /**
   * Returns the sibiling of the specified node that is located 
   * in a specific position relative to the node.
   * @param node
   * @param index position of the sibiling, relative to the node (can be a negative number)
   * @return the sibiling, or <code>null</code> if this node has no children
   * @see #getChildren()
   */    
  public JNode getSibiling(JNode node, int pos)
  {
    if (node != null && node.getParent() != null)
    {
      List children = node.getParent().getChildren();
      int index = children.indexOf(node) + pos;
      if (index >= 0 && index < children.size())
      {
        return (JNode)children.get(index);
      }
    }
    return null;
  }
  
  /**
   * Adds the given un-parented node (document fragment) as the last child of the
   * specified node.
   * @param node the parent of the child to be added
   * @param child the new child node
   * @return whether the opearation was succesful.
   * @see #insertSibling(JNode, JNode)
   * @see #remove(JNode)
   */
  public boolean addChild(JNode node, JNode child)
  {
    if (node != null && child != null)
    {
      try
      {
        return node.getChildren().add(child);
      }
      catch(UnsupportedOperationException e)
      {
      }
    }
    return false;
  }
  
  /**
   * Inserts the given un-parented node as a sibling of the specofoed node, immediately 
   * before or after it.
   * @param node the node that will be after the new sibiling
   * @param sibling the new sibling node
   * @param before whether the sibiling should be added before the node
   * @see #addChild(JNode, JNode)
   * @see #remove(JNode)
   */
  public boolean insertSibling(JNode node, JNode sibiling, boolean before)
  {
     if (node != null && sibiling != null)
     {
       JNode parent = node.getParent();
       if (parent != null)
       {
         List children = parent.getChildren();
         int index = children.indexOf(node);
         if (!before)
         {
           index++;
         }

         try
         {
           if (index == children.size())
           {
             return children.add(sibiling);
           }
           else
           {
             children.add(index, sibiling);
             return children.get(index) == sibiling;             
           }
         }
         catch(UnsupportedOperationException e)
         {
         }
       }
     }
     return false;
  }
  
  /**
   * Separates the specified node from its parent and siblings, maintaining any ties 
   * that this node has to the underlying document fragment. A document fragment that 
   * is removed from its host document may still be dependent on that host document 
   * until it is inserted into a different document. Removing a root node has no effect.
   * @param node the node to be removed
   * @return whether the operation was succesful.
   * @see #addChild(JNode, JNode)
   * @see #insertSibling(JNode, JNode)
   */
  public boolean remove(JNode node)
  {
    if (node != null)
    {
      JNode parent = node.getParent();
      if (parent != null)
      {
        try
        {
          return parent.getChildren().remove(node);
        }
        catch(UnsupportedOperationException e)
        {          
        }
      }
    }
    return false;
  }
 
  /**
   * Formats the specified string using the 
   * {@link CodeGenUtil#convertFormat(String, boolean, String)} method. 
   * @param value
   * @return the formatted String.
   */
  public String applyFormatRules(String value)
  {
    return CodeGenUtil.convertFormat(getControlModel().getLeadingTabReplacement(), getControlModel().convertToStandardBraceStyle(), value);
  }   
}
