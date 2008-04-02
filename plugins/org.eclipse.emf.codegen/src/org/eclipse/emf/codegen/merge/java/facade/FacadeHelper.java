/**
 * <copyright>
 *
 * Copyright (c) 2006-2008 IBM Corporation and others.
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
 * $Id: FacadeHelper.java,v 1.11 2008/04/02 19:07:28 marcelop Exp $
 */
package org.eclipse.emf.codegen.merge.java.facade;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.emf.codegen.merge.java.JControlModel;
import org.eclipse.emf.codegen.util.CodeGenUtil;

public abstract class FacadeHelper
{
  protected static final String CLASS_PREFIX = "org.eclipse.emf.codegen.merge.java.facade.J";
  
  protected JControlModel controlModel;
  protected Map<Object, JNode> objectToNodeMap;
  
  public void reset()
  {
    if (objectToNodeMap != null)
    {
      for (JNode node : objectToNodeMap.values())
      {
        disposeNode(node);
      }
      objectToNodeMap.clear();
    }   
  }
  
  /**
   * @return whether this facade helper can be used on a merge operation. Typically
   * subclasses would return <code>false</code> depending on some environment requirement.
   * @since 2.4
   */
  public boolean canMerge()
  {
    return true;
  }
  
  protected Map<Object, JNode> getObjectToNodeMap()
  {
    if (objectToNodeMap == null)
    {
      objectToNodeMap = new HashMap<Object, JNode>();
    }
    return objectToNodeMap;
  }
  
  public String getClassPrefix()
  {
    return CLASS_PREFIX;
  }
  
  /**
   * <p>Disposes the node and its children.  If the node is null,
   * this method doesn't do anything.</p>
   * <p>After being disposed, the node and its children should not be
   * reused.</p>
   * @param node
   */
  public void dispose(JNode node)
  {
    for (JNode child : node.getChildren())
    {
      dispose(child);
    }
    disposeNode(node);      
  }
  
  /**
   * Disposes a single node.
   * @param node
   */
  protected void disposeNode(JNode node)
  {
    if (node instanceof AbstractJNode)
    {
      ((AbstractJNode)node).dispose();
    }
  }
  
  /**
   * Returns <code>true</code> if a node is
   * disposed.
   * @param node
   * @return boolean
   * @see #dispose(JNode)
   */
  public boolean isDisposed(JNode node)
  {
    return node instanceof AbstractJNode &&
      ((AbstractJNode)node).isDisposed();
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
    JNode node = getObjectToNodeMap().get(object);
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
   * an object that would be used to create a child or sibling of the node.
   * @return the context of a node.
   */  
  public abstract Object getContext(JNode node);
  
  public abstract JCompilationUnit createCompilationUnit(String name, String contents);
  protected abstract JNode doConvertToNode(Object object);

  /**
   * Returns the original, unmodified, content of the compilation unit.
   * Not all facade implementation may be able to provide this information.
   * @param compilationUnit
   * @return the original contents or null
   */
  public String getOriginalContents(JCompilationUnit compilationUnit)
  {
    return null;
  }
  
  /**
   * @return converter that can be used to convert nodes, or <code>null</code> if none
   */
  public NodeConverter getNodeConverter()
  {
    return null;
  }
  
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
      for (JNode child : compilationUnit.getChildren())
      {
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
   * @return the first public type of a compilation unit
   */
  public JType getMainType(JCompilationUnit compilationUnit)
  {
    for (JType type : getChildren(compilationUnit, JType.class))
    {
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
   * @param cls class of the child
   * @return the list of children of a {@link JNode}
   */
  public <T extends JNode> List<T> getChildren(JNode node, Class<T> cls)
  {
    if (node != null && cls != null)
    {
      List<JNode> allChildren = node.getChildren();
      List<T> children = new ArrayList<T>(allChildren.size());
      for (JNode child : allChildren)
      {
        if (cls.isInstance(child))
        {
          children.add(cls.cast(child));
        }
      }
      
      if (!children.isEmpty())
      {
        return Collections.unmodifiableList(children); 
      }
    }
    return Collections.<T>emptyList(); 
  }

  /**
   * Returns the first child of the specified node.
   * Children appear in the order in which they exist in the source code.
   * @param node
   * @return the first child, or <code>null</code> if this node has no children
   * @see #getChildren(JNode, Class)
   */  
  public JNode getFirstChild(JNode node)
  {
    if (node != null)
    {
      List<JNode> children = node.getChildren();
      if (!children.isEmpty())
      {
        return children.get(0);
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
   * Returns the sibling of the specified node that is located 
   * in a specific position relative to the node.
   * @param node
   * @param pos position of the sibling, relative to the node (can be a negative number)
   * @return the sibling, or <code>null</code> if this node has no children
   * @see #getChildren(JNode, Class)
   */    
  protected JNode getSibiling(JNode node, int pos)
  {
    if (node != null && node.getParent() != null)
    {
      List<JNode> children = node.getParent().getChildren();
      int index = children.indexOf(node) + pos;
      if (index >= 0 && index < children.size())
      {
        return children.get(index);
      }
    }
    return null;
  }
  
  /**
   * Adds the given orphan node (document fragment) as the last child of the
   * specified node.
   * @param node the parent of the child to be added
   * @param child the new child node
   * @return whether the operation was successful.
   * @see #insertSibling(JNode, JNode, boolean)
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
        // Ignore
      }
    }
    return false;
  }
  
  /**
   * Inserts the given orphan node as a sibling of the specified node, immediately 
   * before or after it.
   * @param node the node that will be after the new sibling
   * @param newSibling the new sibling node
   * @param before whether the sibling should be added before the node
   * @see #addChild(JNode, JNode)
   * @see #remove(JNode)
   */
  public boolean insertSibling(JNode node, JNode newSibling, boolean before)
  {
     if (node != null && newSibling != null)
     {
       JNode parent = node.getParent();
       if (parent != null)
       {
         List<JNode> children = parent.getChildren();
         int index = children.indexOf(node);
         if (!before)
         {
           index++;
         }

         try
         {
           if (index == children.size())
           {
             return children.add(newSibling);
           }
           else
           {
             children.add(index, newSibling);
             return children.get(index) == newSibling;             
           }
         }
         catch(UnsupportedOperationException e)
         {
           // Ignore
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
   * @return whether the operation was successful.
   * @see #addChild(JNode, JNode)
   * @see #insertSibling(JNode, JNode, boolean)
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
          // Ignore
        }
      }
    }
    return false;
  }
 
  /**
   * Comment out the node and its children.  The node may still be returned by 
   * {@link JNode#getChildren()}
   * @param node
   */
  public void commentOut(JNode node)
  {
    throw new UnsupportedOperationException("This facade implementation cannot comment out nodes.");
  }
  
  /**
   * Formats the specified string using the 
   * {@link CodeGenUtil#convertFormat(String, boolean, String)} method. 
   * @param value
   * @return the formatted String.
   */
  public String applyFormatRules(String value)
  {
    // do not crash when control model is not set
    return getControlModel() == null ?
      value :
      CodeGenUtil.convertFormat(getControlModel().getLeadingTabReplacement(), getControlModel().convertToStandardBraceStyle(), value);
  }
  
  /**
   * @param object
   */
  public String toString(Object object)
  {
    return object == null ? null : object.toString();  
  }

  public boolean fixInterfaceBrace()
  {
    return false;
  }
  
  /**
   * Returns whether this facade implementation may return the
   * wrong Javadoc for a node when there are 2 or more javadoc blocks preceding 
   * the node.
   * @return boolean
   */
  public boolean canYieldWrongJavadoc()
  {
    return false;
  }
  
  /**
   * Returns whether using the {@link #getPrevious(JNode)} and 
   * {@link #getNext(JNode)} methods is slower than using {@link JNode#getChildren()}
   * directly.
   * @return boolean
   */
  public boolean isSibilingTraversalExpensive()
  {
    return true;
  }
}
