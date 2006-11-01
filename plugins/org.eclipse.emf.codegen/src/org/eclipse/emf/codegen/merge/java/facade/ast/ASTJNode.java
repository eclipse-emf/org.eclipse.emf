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
 * $Id: ASTJNode.java,v 1.2 2006/11/01 21:31:43 marcelop Exp $
 */
package org.eclipse.emf.codegen.merge.java.facade.ast;

import java.util.Iterator;
import java.util.List;

import org.eclipse.emf.codegen.merge.java.facade.AbstractJNode;
import org.eclipse.emf.codegen.merge.java.facade.FacadeHelper;
import org.eclipse.emf.codegen.merge.java.facade.JNode;
import org.eclipse.jdt.core.dom.ASTNode;
import org.eclipse.jdt.core.dom.ChildListPropertyDescriptor;
import org.eclipse.jdt.core.dom.ChildPropertyDescriptor;
import org.eclipse.jdt.core.dom.SimplePropertyDescriptor;
import org.eclipse.jdt.core.dom.StructuralPropertyDescriptor;
import org.eclipse.jdt.core.dom.rewrite.ASTRewrite;
import org.eclipse.jdt.core.dom.rewrite.ListRewrite;
import org.eclipse.jdt.core.dom.rewrite.TargetSourceRangeComputer;

/**
 * @since 2.2.0
 */
public abstract class ASTJNode extends AbstractJNode
{
  /**
   * ASTRewrite object used to keep track of all modifications
   */
  protected ASTRewrite rewriter = null;
  
  /**
   * @return the rewriter
   */
  public ASTRewrite getRewriter()
  {
    return rewriter;
  }

  /**
   * @param rewriter the rewriter to set
   */
  public void setRewriter(ASTRewrite rewriter)
  {
    this.rewriter = rewriter;
  }    
  
  /**
   * Current parent of ASTJNode.
   * <p>
   * This attribute should used instead of ASTNode parent attribute. In some situations
   * (i.e. moving nodes by removing and inserting) ASTNode parent attribute
   * might not be updated. In addition, for the cloned nodes ASTNode parent attribute might
   * not be set either.
   * <p> 
   * When ASTJNode is deleted, parent is set to null.<p>
   */
  private ASTJNode parent = null;
  
  /**
   * Flag that determines if parent property has been set 
   * (if not, <code>ASTNode.getParent()</code> will be used to retrieve the parent).
   */
  private boolean isParentSet = false; 
  
  /**
   * @return the parent of this ASTJNode, or <code>null</code> if this node has been created or removed
   */
  public ASTJNode getParent()
  {
    if (parent == null && !isParentSet)
    {
      setParent((ASTJNode)findParent());
    }
    return parent;
  }

  /**
   * Sets the parent of this node.
   * 
   * @param parent the parent to set, <code>null</code> if the node does not have a parent
   */
  public void setParent(ASTJNode parent)
  {
    isParentSet = true;
    this.parent = parent;
  }

  /**
   * @see AbstractJNode#AbstractJNode(Object)
   * @param astNode to be used as wrapped object
   */
  protected ASTJNode(ASTNode astNode)
  {
    super(astNode);
  }
  
  /**
   * @return ASTNode wrapped by this node
   */
  protected ASTNode getASTNode()
  {
    return (ASTNode)getWrappedObject();
  }
  
  /**
   * Sets the wrapped object to the given <code>ASTNode</code>.
   * <p>
   * Must be used with caution and only by subclasses.
   * 
   * @param node
   */
  protected void setASTNode(ASTNode node)
  {
    wrappedObject = node;
  }  
  
  /**
   * Default implementation does nothing.
   * 
   * @see org.eclipse.emf.codegen.merge.java.facade.JNode#setFlags(int)
   */
  public void setFlags(int flags)
  {
  }
  
  /**
   * Finds the parent node based on the parent of wrapped AST node.
   * 
   * @return parent {@link JNode}
   */
  private JNode findParent()
  {
    FacadeHelper facadeHelper = getFacadeHelper();
    ASTNode node = getASTNode();
    JNode parent;
    // skip nodes in hierarchy that can not be converted to JNode (i.e. VariableDeclarationFragment)
    do
    {
      node = node.getParent();
      parent = facadeHelper.convertToNode(node);      
    } 
    while (parent == null && node != null);
    return parent;
  }
    
  /**
   * Get the original contents of the node using the source.
   * <p>
   * The contents of the node in AST implementation includes only the node itself without
   * the leading or trailing whitespace.
   * If the node has a Javadoc comment, it is included in the contents. No other leading or trailing
   * comments are included in the node contents.
   * <p>
   * Note that this method returns the contents before any modifications. This method will not
   * return the correct contents if the node has been cloned, removed or moved.
   * 
   * @see org.eclipse.emf.codegen.merge.java.facade.JNode#getContents()
   */
  public String getContents()
  {
    return facadeHelper.toString(getASTNode());
  }
  
  /**
   * Inserts <code>newSibling</code> as a child of this node before or after the given <code>node</code>.
   * <p>
   * Default implementation does nothing and returns <code>false</code>.
   * 
   * @param node
   * @param newSibling to insert
   * @param before <code>true</code> if <code>newSibling</code> must be before <code>node</code>, <code>false</code> if after
   * @return <code>true</code> if operation successful, <code>false</code> otherwise 
   */
  public boolean insertSibling(JNode node, JNode newSibling, boolean before)
  {
    return false;
  }

  /**
   * Removes a node.
   * <p>
   * Default implementation does nothing and returns <code>false</code>.
   * 
   * @param node must be a child of this node
   * @return <code>true</code> if operation successful, <code>false</code> otherwise 
   */
  public boolean remove(JNode node)
  {
    return false;
  }

  /**
   * Adds a child to this node.
   * <p>
   * Default implementation does nothing and returns <code>false</code>.
   * 
   * @param child to add
   * @return <code>true</code> if operation successful, <code>false</code> otherwise 
   */
  public boolean addChild(JNode child)
  {
    return false;
  }  
  
  /**
   * Sets the property of the node. The position of the property value is tracked and value
   * is replaced by  <code>stringValue</code> during rewrite process.
   * 
   * @param node to set property of
   * @param stringValue
   * @param property must be {@link SimplePropertyDescriptor} or {@link ChildPropertyDescriptor}
   * @param nodeType to use if value is null, and new node has to be created
   */
  protected void setTrackedNodeProperty(ASTNode node, String stringValue, StructuralPropertyDescriptor property, int nodeType)
  {
    if (ASTFacadeHelper.DEBUG)
    {
      ((ASTFacadeHelper)facadeHelper).logInfo("Setting tracked node property to string <" + stringValue + ">");
    }
    
    ASTNode nodeValue = (ASTNode)rewriter.get(node, property);
    
    if (stringValue == null)
    {
      disableTrackAndReplace(nodeValue);
      rewriter.set(node, property, null, null);
    }
    else if (nodeValue != null)
    {
      trackAndReplace(nodeValue, stringValue);      
    } 
    else // stringValue not null, nodeValue is null
    {
      nodeValue = rewriter.createStringPlaceholder(stringValue, nodeType);
      rewriter.set(node, property, nodeValue, null);
      trackAndReplace(nodeValue, stringValue); 
    }
  }  
  
  /**
   * Sets the property of the node to the given value.
   * <p>
   * Note that the type <code>value</code> parameter will only be checked by 
   * {@link ASTRewrite#set(ASTNode, StructuralPropertyDescriptor, Object, org.eclipse.text.edits.TextEditGroup)}
   * <p>
   * 
   * @param node
   * @param value
   * @param property
   */
  protected void setNodeProperty(ASTNode node, Object value, StructuralPropertyDescriptor property)
  {
    if (ASTFacadeHelper.DEBUG)
    {
      ((ASTFacadeHelper)facadeHelper).logInfo("Setting node property to <" + value + ">");
    }
    rewriter.set(node, property, value, null);
  }

  /**
   * Sets the property of the node to the given string value.
   * <p>
   * Note that the type <code>value</code> parameter will only be checked by 
   * {@link ASTRewrite#set(ASTNode, StructuralPropertyDescriptor, Object, org.eclipse.text.edits.TextEditGroup)}
   * <p>
   * 
   * @param node
   * @param value
   * @param property
   * @param nodeType
   */
  protected void setNodeProperty(ASTNode node, String stringValue, StructuralPropertyDescriptor property, int nodeType)
  {
    if (ASTFacadeHelper.DEBUG)
    {
      ((ASTFacadeHelper)facadeHelper).logInfo("INFO Setting node property to <" + stringValue + ">");
    }
    rewriter.set(node, property, rewriter.createStringPlaceholder(stringValue, nodeType), null);
  }  
  
  /**
   * Sets the list property to the given array of values. For each string in the array,
   * a string placeholder node is created.
   * 
   * @param node
   * @param values
   * @param property
   * @param nodeType of the string place holders to create
   */
  protected void setListNodeProperty(ASTNode node, String[] values, ChildListPropertyDescriptor property, int nodeType)
  {
    ListRewrite listRewrite = rewriter.getListRewrite(node, property);
    List<?> oldValues = listRewrite.getRewrittenList();
    
    // clear old values
    for (Iterator<?> it = oldValues.iterator(); it.hasNext();)
    {
      listRewrite.remove((ASTNode)it.next(), null);
    }
    
    // insert new values
    for (int i = 0; i < values.length; i++)
    {
      listRewrite.insertLast(rewriter.createStringPlaceholder(values[i], nodeType), null);
    }      
  }
  
  /**
   * Adds <code>value</code> as a new string placeholder to the <code>property</code> of the <code>node</code>.
   * <p>
   * If <code>value</code> is <code>null</code>, no changes are made.
   * 
   * @param node
   * @param value
   * @param property
   * @param noteType of the placeholder
   */
  protected void addValueToListProperty(ASTNode node, String value, ChildListPropertyDescriptor property, int nodeType)
  {
    if (value == null)
    {
      return;
    }
    
    if (ASTFacadeHelper.DEBUG)
    {
      ((ASTFacadeHelper)facadeHelper).logInfo("Adding value to list property <" + value + ">");
    }
    
    ListRewrite listRewrite = rewriter.getListRewrite(node, property);
    listRewrite.insertLast(rewriter.createStringPlaceholder(value.toString(), nodeType), null);
  }  
  
  /**
   * Adds <code>value</code> to the property of the node.
   * 
   * @param node
   * @param value
   * @param property
   * @param noteType
   */
  protected void addValueToListProperty(ASTNode node, ASTNode value, ChildListPropertyDescriptor property)
  {
    if (value == null)
    {
      return;
    }
    
    if (ASTFacadeHelper.DEBUG)
    {
      ((ASTFacadeHelper)facadeHelper).logInfo("Adding value to list property <" + value + ">");
    }
    
    ListRewrite listRewrite = rewriter.getListRewrite(node, property);
    listRewrite.insertLast(value, null);
  }   
  
  /**
   * Removes value from list property.
   * 
   * @param parentNode
   * @param nodeToRemove
   * @param property
   */
  protected void removeNodeFromListProperty(ASTNode parentNode, ASTNode nodeToRemove, ChildListPropertyDescriptor property)
  {
    if (ASTFacadeHelper.DEBUG)
    {
      ((ASTFacadeHelper)facadeHelper).logInfo("Removing node from list property <" + nodeToRemove + ">");
    }
    
    ListRewrite listRewrite = getRewriter().getListRewrite(parentNode, property);
    listRewrite.remove(nodeToRemove, null);
  }    

  /**
   * Inserts new node before or after target node.
   * <p>
   * Target node must have a valid location in parent (must exist in the tree).
   * 
   * @param newNode node to insert
   * @param targetNode target node
   * @param before 
   */
  protected void insert(ASTNode newNode, ASTNode targetNode, boolean before)
  {
    StructuralPropertyDescriptor locationInParent = targetNode.getLocationInParent();
    if (locationInParent != null && locationInParent.isChildListProperty())
    {
      insert(targetNode.getParent(), (ChildListPropertyDescriptor) locationInParent, newNode, targetNode, before);
    } 
    else if (ASTFacadeHelper.DEBUG)
    {
      ((ASTFacadeHelper)facadeHelper).logInfo("Unable to insert, target node <" + targetNode + "> has invalid location in parent <" + locationInParent + ">");
    }
  }  
  
  /**
   * Inserts new child node before or after the target target node.
   * 
   * @param parentNode parent node to insert a child for
   * @param property property of list of children
   * @param newNode node to insert
   * @param targetNode
   * @param before <code>true</code> if <code>newNode</code> must be inserted before <code>targetNode</code>, <code>false</code> otherwise
   */
  protected void insert(ASTNode parentNode, ChildListPropertyDescriptor property, ASTNode newNode, ASTNode targetNode, boolean before)
  {
    ListRewrite listRewrite = rewriter.getListRewrite(parentNode, property);
    if (before)
    {
      listRewrite.insertBefore(newNode, targetNode, null);
    }
    else
    {
      listRewrite.insertAfter(newNode, targetNode, null);
    }
  }
  
  /**
   * Insert new node at the beginning of the list of children.
   * 
   * @param parentNode parent node to insert a child for
   * @param property property of list of children
   * @param newNode node to insert
   */
  protected void insertFirst(ASTNode parentNode, ChildListPropertyDescriptor property, ASTNode newNode)
  {
    ListRewrite listRewrite = rewriter.getListRewrite(parentNode, property);    
    listRewrite.insertFirst(newNode, null);
  }  
  
  /**
   * Insert new node at the end of the list of children
   * 
   * @param parentNode parent node to insert a child for
   * @param property property of list of children
   * @param newNode node to insert
   */
  protected void insertLast(ASTNode parentNode, ChildListPropertyDescriptor property, ASTNode newNode)
  {
    ListRewrite listRewrite = rewriter.getListRewrite(parentNode, property);    
    listRewrite.insertLast(newNode, null);
  }
  
  /**
   * Records that the given <code>node</code> must have the given <code>contents</code>.
   * <p> 
   * During rewrite process, the position of the <code>node</code> will be tracked, and the node
   * will replaced by the given <code>contents</code>. No changes will be made to <code>contents</code>.
   * <p>
   * This method should be used when correction of indentation provided by {@link ASTRewrite#createStringPlaceholder(String, int)}
   * and other methods of {@link ASTRewrite} is not desired.
   * 
   * @param node
   * @param contents
   */
  protected void trackAndReplace(ASTNode node, String contents)
  {
    if (ASTFacadeHelper.DEBUG)
    {
      ((ASTFacadeHelper)facadeHelper).logInfo("Track and replace <" + node + "> by <" + contents + ">");
    }
    
    ((ASTJCompilationUnit)facadeHelper.getCompilationUnit(this)).getTrackedContentsMap().put(node, contents);
  }
  
  /**
   * Disables tracking for the node. 
   * <p>
   * The given <code>node</code> will be modified only by {@link ASTRewrite}.
   * 
   * @param node 
   * 
   * @see #trackAndReplace(ASTNode, String)
   */
  protected void disableTrackAndReplace(ASTNode node)
  {
    ((ASTJCompilationUnit)facadeHelper.getCompilationUnit(this)).getTrackedContentsMap().remove(node);
  }  
  
  /**
   * Notifies the range computer of {@link ASTRewrite} that the node will be removed.
   * 
   * @param node to be removed
   * 
   * @see CommentAwareSourceRangeComputer#computeSourceRange(ASTNode) 
   * @param node
   */
  protected void nodeToBeRemoved(ASTNode node)
  {
    TargetSourceRangeComputer sourceRangeComputer = rewriter.getExtendedSourceRangeComputer();
    if (sourceRangeComputer instanceof CommentAwareSourceRangeComputer)
    {
      ((CommentAwareSourceRangeComputer)sourceRangeComputer).markNodeForRemoval(node);
    }
  }

  /**
   * Notifies the range computer of {@link ASTRewrite} that the node will be moved.
   * 
   * @param node to be moved
   * 
   * @see CommentAwareSourceRangeComputer#computeSourceRange(ASTNode) 
   */
  protected void nodeToBeMoved(ASTNode node)
  {
    TargetSourceRangeComputer sourceRangeComputer = rewriter.getExtendedSourceRangeComputer();
    if (sourceRangeComputer instanceof CommentAwareSourceRangeComputer)
    {
      ((CommentAwareSourceRangeComputer)sourceRangeComputer).unmarkNodeForRemoval(node);
    }
  }
  
}
