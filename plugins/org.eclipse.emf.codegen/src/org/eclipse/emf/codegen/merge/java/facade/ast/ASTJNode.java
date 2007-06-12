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
 * $Id: ASTJNode.java,v 1.9 2007/06/12 20:56:05 emerks Exp $
 */
package org.eclipse.emf.codegen.merge.java.facade.ast;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.jdt.core.dom.ASTNode;
import org.eclipse.jdt.core.dom.ChildListPropertyDescriptor;
import org.eclipse.jdt.core.dom.ChildPropertyDescriptor;
import org.eclipse.jdt.core.dom.SimplePropertyDescriptor;
import org.eclipse.jdt.core.dom.StructuralPropertyDescriptor;
import org.eclipse.jdt.core.dom.rewrite.ASTRewrite;
import org.eclipse.jdt.core.dom.rewrite.ListRewrite;
import org.eclipse.jdt.core.dom.rewrite.TargetSourceRangeComputer;

import org.eclipse.emf.codegen.merge.java.facade.AbstractJNode;
import org.eclipse.emf.codegen.merge.java.facade.FacadeHelper;
import org.eclipse.emf.codegen.merge.java.facade.JNode;


/**
 * Each subclass of <code>ASTJNode</code> wraps subclass of {@link ASTNode}.
 * <p>
 * <code>ASTJNode</code> may have reference to multiple <code>ASTNode</code> objects.
 * <p>
 * Wrapped <code>ASTNode</code> (returned by {@link #getWrappedObject()}) is always the node in the rewritten tree. Each object of <code>ASTJNode</code>
 * wraps a unique <code>ASTNode</code>. After nodes are removed, wrapped <code>ASTNode</code> has reference to the move target node that allows
 * the node to be inserted after, i.e. perform moving of the node.
 * <p>
 * Original ASTNode (returned by {@link #getASTNode()}) is used to make modifications to the node and its children. This node must
 * be used by get and set methods. Using this node allows to make modifications to the nodes after
 * they are moved.
 * <p>
 * Some subclasses (e.g. <code>ASTJAnnotation</code> and <code>ASTJField</code>) may need a different <code>ASTNode</code> to be used
 * by set methods. These subclasses use <code>getASTNode()</code> only for get methods.
 * <p>
 * Removed ASTNode (returned by {@link #getRemovedASTNode()}) is used to keep reference to the node
 * that was removed. This allows to notify the range computer when the removed node is inserted again.
 * <p>
 * 
 * @see #getWrappedObject()
 * @see #getASTNode()
 * @see #getRemovedASTNode()
 *
 * @param <T> wrapped AST node type
 * 
 * @since 2.2.0
 */
public abstract class ASTJNode<T extends ASTNode> extends AbstractJNode
{
  /**
   * Default value used as default value of fields of subclasses. 
   * Indicates that fields have not been initialized.
   * This value should never be returned by any get...() methods.
   */
  protected static final String UNITIALIZED_STRING = "UNITIALIZED_STRING";
  
  /**
   * Name of the node.
   * 
   * @see JNode#getName()
   * @see JNode#setName(String)
   */
  protected String name = UNITIALIZED_STRING;
    
  /**
   * <code>true</code> if the node is commented out, <code>false</code> otherwise
   * @see #commentOut()
   */
  protected boolean isCommentedOut = false;
  
  private ASTFacadeHelper facadeHelper;

  /**
   * AST node used to read contents and make modifications to the nodes.
   */
  private T astNode = null;
  
  private ASTNode wrappedObject;

  /**
   * Flag that determines if parent property has been set 
   * (if not, <code>ASTNode.getParent()</code> will be used to retrieve the parent).
   */
  private boolean isParentSet = false;

  /**
   * Current parent of ASTJNode.
   * <p>
   * This attribute should used instead of ASTNode parent attribute. In some situations
   * (i.e. moving nodes by removing and inserting) ASTNode parent attribute
   * might not be updated. In addition, for the cloned nodes ASTNode parent attribute might
   * not be set either.
   * <p> 
   * When ASTJNode is deleted, parent is set to <code>null</code>.<p>
   */
  private ASTJNode<?> parent = null;

  /**
   * Reference to the original AST node that was removed from the tree.
   * <p>
   * This reference is used to notify the source range computer when the
   * removed node is inserted again (i.e. node is moved). 
   */
  private ASTNode removedASTNode = null;

  /**
   * ASTRewrite object used to keep track of all modifications
   */
  protected ASTRewrite rewriter = null;

  /**
   * Map of nodes to their contents. This map may only contain wrapped node and/or its children.
   * This map is used to preserve the contents when node is removed and then inserted.
   * 
   * @see ASTJCompilationUnit#getAllTrackedContentsMap()
   */
  private Map<ASTNode, String> trackedContentsMap = new HashMap<ASTNode, String>(4);

  /**
   * @see AbstractJNode#AbstractJNode()
   * @param astNode to be used as wrapped object
   */
  protected ASTJNode(T astNode)
  {
    this.astNode = astNode;
    wrappedObject = astNode;
  }

  @Override
  public void dispose()
  {
    if (rewriter != null)
    {
      ((ASTFacadeHelper.ASTRewriteWithRemove)rewriter).dispose();
      rewriter = null;
    }
    trackedContentsMap.clear();
    wrappedObject = null;
    removedASTNode = null;
    astNode = null;
    facadeHelper = null;
    name = null;
  }
  
  @Override
  public boolean isDisposed()
  {
    return rewriter == null;
  }

  @Override
  public ASTFacadeHelper getFacadeHelper()
  {
    return facadeHelper;
  }

  @Override
  public void setFacadeHelper(FacadeHelper facadeHelper)
  {
    this.facadeHelper = (ASTFacadeHelper)facadeHelper;
  }

  /**
   * Returns AST node used to read contents and make modifications to the nodes.
   * <p>
   * When the node is removed, the wrapped node returned by {@link #getWrappedObject()} will be
   * a place-holder node, while this method will return the original node that can be modified by
   * <code>set...</code> methods.
   * 
   * @return AST node
   */
  protected T getASTNode()
  {
    return astNode;
  }

  /**
   * @param astNode the astNode to set
   */
  protected void setASTNode(T astNode)
  {
    this.astNode = astNode;
  }

  /**
   * Returns wrapped AST node that is currently in the rewritten tree, or to be inserted into
   * the tree. This node can be a move target node or any other temporary node. 
   * Methods such as <code>set...</code> and <code>get...</code> should not use this node. 
   * 
   * @return wrapped AST node 
   * @see #getASTNode()
   * @see #getRemovedASTNode()
   */
  @Override
  protected ASTNode getWrappedObject()
  {
    return wrappedObject;
  }

  /**
   * Sets the wrapped object to the given <code>ASTNode</code>. Must be 
   * used with caution and only by subclasses.
   * @param node
   * @see #getWrappedObject()
   */
  protected void setWrappedObject(ASTNode node)
  {
    wrappedObject = node;
  }

  /**
   * @return the parent of this ASTJNode, or <code>null</code> if this node has been created or removed
   */
  public ASTJNode<?> getParent()
  {
    if (parent == null && !isParentSet)
    {
      setParent(facadeHelper.findParent(getWrappedObject()));
    }
    return parent;
  }

  /**
   * Sets the parent of this node.
   * 
   * @param parent the parent to set, <code>null</code> if the node does not have a parent
   */
  public void setParent(ASTJNode<?> parent)
  {
    isParentSet = true;
    this.parent = parent;
  }

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
   * Adds a child to this node.
   * <p>
   * Default implementation does nothing and returns <code>false</code>.
   * 
   * @param child to add
   * @return <code>true</code> if operation successful, <code>false</code> otherwise 
   */
  public boolean addChild(ASTJNode<?> child)
  {
    return false;
  }  
  
  /**
   * Adds <code>value</code> to the property of the node.
   * 
   * @param node
   * @param value
   * @param property
   */
  protected void addValueToListProperty(ASTNode node, ASTNode value, ChildListPropertyDescriptor property)
  {
    if (value == null)
    {
      return;
    }

    if (ASTFacadeHelper.DEBUG)
    {
      facadeHelper.logInfo("Adding value to list property <" + value + ">");
    }

    ListRewrite listRewrite = rewriter.getListRewrite(node, property);
    listRewrite.insertLast(value, null);
  }

  /**
   * Adds <code>value</code> as a new string place-holder to the <code>property</code> of the <code>node</code>.
   * <p>
   * If <code>value</code> is <code>null</code>, no changes are made.
   * 
   * @param node
   * @param value
   * @param property
   * @param nodeType of the place-holder
   */
  protected void addValueToListProperty(ASTNode node, String value, ChildListPropertyDescriptor property, int nodeType)
  {
    if (value == null)
    {
      return;
    }

    if (ASTFacadeHelper.DEBUG)
    {
      facadeHelper.logInfo("Adding value to list property <" + value + ">");
    }

    ListRewrite listRewrite = rewriter.getListRewrite(node, property);
    listRewrite.insertLast(rewriter.createStringPlaceholder(value, nodeType), null);
  }
  
  /**
   * Notifies the node that one of ancestors in the hierarchy or this node itself was inserted.
   * This means that this node was inserted as well.
   * <p>
   * This implementation enables tracked contents for this node and calls itself on all of the children.
   */
  protected void ancestorInserted()
  {
    enableTrackAndReplace();

    for (JNode node : getChildren())
    {
      ((ASTJNode<?>)node).ancestorInserted();
    }
  }

  /**
   * Notifies the node that one of ancestors in the hierarchy or this node itself will be removed.
   * This means that this node will be removed as well.
   * <p>
   * This implementation disables tracked contents for this node and calls itself on all of the children.
   */
  protected void ancestorToBeRemoved()
  {
    disableTrackAndReplace();

    for (JNode node : getChildren())
    {
      ((ASTJNode<?>)node).ancestorToBeRemoved();
    }
  }

  /**
   * Notifies the node that the child will be changed.
   * <p>
   * Parents that are interested in changes to children must override this method.
   * Children that need to notify the parents about changes have to call this method.
   * <p>
   * Default implementation does nothing.
   * 
   * @param child that will be changed
   */
  protected void childToBeChanged(ASTJNode<?> child)
  {
    // default implementation does nothing
  }

  /**
   * Convert a given list of nodes to an array of strings.
   * <p>
   * All nodes in the given array must have valid source range and belong to the original tree.
   * 
   * @param nodes
   * @return string array
   * 
   * @see ASTFacadeHelper#toString(ASTNode)
   */
  protected String[] convertASTNodeListToStringArray(List< ? extends ASTNode> nodes)
  {
    if (nodes == null)
    {
      return EMPTY_STRING_ARRAY;
    }

    String[] strings = new String [nodes.size()];
    int i = 0;
    for (ASTNode node : nodes)
    {
      strings[i++] = facadeHelper.toString(node);
    }
    return strings;
  }

  /**
   * Temporarily disables tracking for all AST nodes that are tracked by this <code>ASTJNode</code>.
   * <p>
   * Used when modified <code>ASTJNode</code>s are removed from the tree, and then possibly inserted again.
   * 
   * @see #enableTrackAndReplace() 
   * @see #trackAndReplace(ASTNode, String)
   */
  protected void disableTrackAndReplace()
  {
    ASTJCompilationUnit compilationUnit = (ASTJCompilationUnit)facadeHelper.getCompilationUnit(this);

    if (compilationUnit != null)
    {
      for (ASTNode node : trackedContentsMap.keySet())
      {
        compilationUnit.getAllTrackedContentsMap().remove(node);
      }

      // disable commenting out
      if (isCommentedOut)
      {
        compilationUnit.getCommentedOutNodes().remove(getASTNode());
      }
    }
  }

  /**
   * Enables disabled tracking for all AST nodes that are tracked by this <code>ASTJNode</code>.
   * <p>
   * Used when modified <code>ASTJNode</code>s are removed from the tree, and then inserted again.
   * 
   * @see #disableTrackAndReplace()
   * @see #trackAndReplace(ASTNode, String)
   */
  protected void enableTrackAndReplace()
  {
    ASTJCompilationUnit compilationUnit = (ASTJCompilationUnit)facadeHelper.getCompilationUnit(this);

    if (compilationUnit != null)
    {
      compilationUnit.getAllTrackedContentsMap().putAll(trackedContentsMap);
      
      // enable commenting out
      if (isCommentedOut)
      {
        compilationUnit.getCommentedOutNodes().add(getASTNode());
      }
    }
  }
  
  /**
   * Wraps up inserting new node. Notifies the children and the range computer that the node is inserted.
   * 
   * @param newNode
   */
  private void finishInsert(ASTJNode<?> newNode)
  {
    // mark the node as moved if node has been removed      
    ASTNode removedASTNode = newNode.getRemovedASTNode();
    if (removedASTNode != null)
    {
      nodeToBeMoved(removedASTNode);
    }

    newNode.setParent(this);
    newNode.ancestorInserted();
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
   * @return the removedASTNode
   */
  protected ASTNode getRemovedASTNode()
  {
    return removedASTNode;
  }

  /**
   * Inserts AST node wrapped by new node beside target node in the list defined by given property.
   * <p>
   * No checks are performed if the new node can be inserted nor if the target node exists. 
   * 
   * @param newNode
   * @param property 
   * @param targetNode
   * @param before
   */
  protected void insert(ASTJNode<?> newNode, ChildListPropertyDescriptor property, ASTJNode<?> targetNode, boolean before)
  {
    ListRewrite listRewrite = rewriter.getListRewrite(getASTNode(), property);
    if (before)
    {
      listRewrite.insertBefore(newNode.getWrappedObject(), targetNode.getWrappedObject(), null);
    }
    else
    {
      listRewrite.insertAfter(newNode.getWrappedObject(), targetNode.getWrappedObject(), null);
    }

    finishInsert(newNode);
  }

  /**
   * Inserts AST node wrapped by new node at the beginning of the list of nodes defined by given property.
   * <p>
   * No checks are performed if the new node can be inserted nor if the target node exists. 
   * 
   * @param newNode
   * @param property
   */
  protected void insertFirst(ASTJNode<?> newNode, ChildListPropertyDescriptor property)
  {
    ListRewrite listRewrite = rewriter.getListRewrite(getASTNode(), property);
    listRewrite.insertFirst(newNode.getWrappedObject(), null);

    finishInsert(newNode);
  }

  /**
   * Inserts AST node wrapped by new node at the end of the list of nodes defined by given property.
   * <p>
   * No checks are performed if the new node can be inserted nor if the target node exists. 
   * 
   * @param newNode
   * @param property
   */
  protected void insertLast(ASTJNode<?> newNode, ChildListPropertyDescriptor property)
  {
    ListRewrite listRewrite = rewriter.getListRewrite(getASTNode(), property);
    listRewrite.insertLast(newNode.getWrappedObject(), null);

    finishInsert(newNode);
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
  public boolean insertSibling(ASTJNode<?> node, ASTJNode<?> newSibling, boolean before)
  {
    return false;
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

  /**
   * Notifies the range computer of {@link ASTRewrite} that the node will be removed.
   * 
   * @param node to be removed
   * @see CommentAwareSourceRangeComputer#computeSourceRange(ASTNode) 
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
   * Removes a node.
   * <p>
   * Default implementation does nothing and returns <code>false</code>.
   * 
   * @param node must be a child of this node
   * @return <code>true</code> if operation successful, <code>false</code> otherwise 
   */
  public boolean remove(ASTJNode<?> node)
  {
    return false;
  }

  /**
   * Removes AST node wrapped by given <code>ASTJNode</code> from the given property.
   * <p>
   * This method notifies children and source range computer that the node is removed, then creates
   * a place-holder node that allows to insert the removed node later.
   * <p>
   * No checks are performed if the node is a child of this node. 
   * 
   * @param node
   * @param property
   */
  protected void remove(ASTJNode<?> node, ChildListPropertyDescriptor property)
  {
    node.ancestorToBeRemoved();

    ASTNode astNodeToBeRemoved = node.getWrappedObject();

    // if are dealing with original, not cloned node
    if (astNodeToBeRemoved.getParent() != null && astNodeToBeRemoved.getLocationInParent() != null)
    {
      // mark the node to be removed
      nodeToBeRemoved(astNodeToBeRemoved);

      // assume that the node is being moved (to allow insertion after)
      node.setRemovedASTNode(astNodeToBeRemoved);

      ASTNode moveTargetASTNode = rewriter.createMoveTarget(astNodeToBeRemoved);
      node.setWrappedObject(moveTargetASTNode);
      getFacadeHelper().updateObjectToNodeMap(node);
    }

    // remove the node
    removeNodeFromListProperty(getASTNode(), astNodeToBeRemoved, property);
    node.setParent(null);
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
      facadeHelper.logInfo("Removing node from list property <" + nodeToRemove + ">");
    }

    // Bugzilla 164862
    // listRewrite.remove(..) does not remove newly inserted nodes that were not a part of original tree (!!!)
    //    ListRewrite listRewrite = getRewriter().getListRewrite(parentNode, property);
    //    listRewrite.remove(nodeToRemove, null);

    // call workaround
    ((ASTFacadeHelper.ASTRewriteWithRemove)rewriter).remove(parentNode, property, nodeToRemove);
  }

  /**
   * Permanently disables tracking for the given AST node. 
   * <p>
   * The given <code>node</code> will be modified only by {@link ASTRewrite}.
   * 
   * @param node to disable tracking for 
   *
   * @see #disableTrackAndReplace()
   * @see #trackAndReplace(ASTNode, String)
   */
  protected void removeTrackAndReplace(ASTNode node)
  {
    trackedContentsMap.remove(node);

    ASTJCompilationUnit compilationUnit = (ASTJCompilationUnit)facadeHelper.getCompilationUnit(this);

    if (compilationUnit != null)
    {
      compilationUnit.getAllTrackedContentsMap().remove(node);
    }
    // TODO handle situation when ASTJNode is removed (compilationUnit is null)
  }

  /**
   * Default implementation does nothing.
   * 
   * @see org.eclipse.emf.codegen.merge.java.facade.JNode#setFlags(int)
   */
  public void setFlags(int flags)
  {
    // default implementation does nothing
  }

  /**
   * Sets the list property to the given array of values. For each string in the array,
   * a string place-holder node is created.
   * 
   * @param node
   * @param values
   * @param property
   * @param nodeType of the string place holders to create
   */
  protected void setListNodeProperty(ASTNode node, String[] values, ChildListPropertyDescriptor property, int nodeType)
  {
    ListRewrite listRewrite = rewriter.getListRewrite(node, property);
    
    @SuppressWarnings("unchecked")    
    List<ASTNode> oldValues = listRewrite.getRewrittenList();

    // clear old values
    for (ASTNode oldValue : oldValues)
    {
      // Bugzilla 164862
      // listRewrite.remove(..) does not remove newly inserted nodes that were not a part of original tree (!!!)
      //listRewrite.remove(oldValue, null);
      
      // call the workaround
      ((ASTFacadeHelper.ASTRewriteWithRemove)rewriter).remove(node, property, oldValue);
    }

    // insert new values
    for (String value : values)
    {
      listRewrite.insertLast(rewriter.createStringPlaceholder(value, nodeType), null);
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
      facadeHelper.logInfo("Setting node property <" + property + "> to <" + value + ">");
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
   * @param stringValue
   * @param property
   * @param nodeType
   */
  protected void setNodeProperty(ASTNode node, String stringValue, StructuralPropertyDescriptor property, int nodeType)
  {
    if (ASTFacadeHelper.DEBUG)
    {
      facadeHelper.logInfo("Setting node property to <" + stringValue + ">");
    }
    Object value = null;
    if (stringValue != null)
    {
      value = rewriter.createStringPlaceholder(stringValue, nodeType);
    }
    rewriter.set(node, property, value, null);
  }

  /**
   * Adds strings from strings list to existing array of strings, returns resulting
   * string array, and clears the list of strings.
   * 
   * @param strings can <b>not</b> be <code>null</code>
   * @param stringsList can be <code>null</code>
   */
  protected String[] combineArrayAndList(String[] strings, List<String> stringsList)
  {
    if (stringsList != null && stringsList.size() > 0)
    {
      if (strings.length > 0)
      {
        String[] newStringArray = new String[strings.length + stringsList.size()];
        for (int i = 0; i < strings.length; i++)
        {
          newStringArray[i] = strings[i];
        }
        for (int i = 0; i < stringsList.size(); i++)
        {
          newStringArray[strings.length + i] = stringsList.get(i);
        }
        strings = newStringArray;
      }
      else
      {
        strings = stringsList.toArray(EMPTY_STRING_ARRAY);
      }
      stringsList.clear();
    }
    return strings;
  }
  
  /**
   * @param removedASTNode the removedASTNode to set
   */
  protected void setRemovedASTNode(ASTNode removedASTNode)
  {
    this.removedASTNode = removedASTNode;
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
      facadeHelper.logInfo("Setting tracked node property to string <" + stringValue + ">");
    }

    ASTNode nodeValue = (ASTNode)rewriter.get(node, property);

    if (stringValue == null || "".equals(stringValue))
    {
      removeTrackAndReplace(nodeValue);
      rewriter.set(node, property, null, null);
    }
    else if (nodeValue != null)
    {
      trackAndReplace(nodeValue, stringValue);
    }
    else
    // stringValue not null, nodeValue is null
    {
      nodeValue = rewriter.createStringPlaceholder(stringValue, nodeType);
      rewriter.set(node, property, nodeValue, null);
      trackAndReplace(nodeValue, stringValue);
    }
  }

  /**
   * Records that the given <code>node</code> must have the given <code>contents</code>.
   * <p> 
   * During rewrite process, the position of the <code>node</code> will be tracked, and the node
   * will replaced by the given <code>contents</code>. No changes will be made to <code>contents</code>.
   * <p>
   * This method should be used to undo correction of indentation done by {@link ASTRewrite#rewriteAST(org.eclipse.jface.text.IDocument, Map)}
   * on string place-holder nodes ({@link ASTRewrite#createStringPlaceholder(String, int)})
   * during rewrite process.
   * <p>
   * Note that all the tracked nodes must exist in the rewritten tree. If some tracked nodes are removed from rewritten tree, wrong
   * contents may be replaced. Use {@link #disableTrackAndReplace()} when <code>ASTJNode</code>s are removed.
   * 
   * @param node
   * @param contents
   */
  protected void trackAndReplace(ASTNode node, String contents)
  {
    if (ASTFacadeHelper.DEBUG)
    {
      facadeHelper.logInfo("Track and replace <" + node + "> by <" + contents + ">");
    }

    trackedContentsMap.put(node, contents);

    ASTJCompilationUnit compilationUnit = ((ASTJCompilationUnit)facadeHelper.getCompilationUnit(this));
    if (compilationUnit != null)
    {
      compilationUnit.getAllTrackedContentsMap().put(node, contents);
    }
  }

  /**
   * Comments out this node. Node is not removed from the tree, and is still returned by getChildren().
   */
  public void commentOut()
  {
    isCommentedOut = true;
    ASTJCompilationUnit compilationUnit = ((ASTJCompilationUnit)facadeHelper.getCompilationUnit(this));
    if (compilationUnit != null)
    {
      compilationUnit.getCommentedOutNodes().add(this.getASTNode());
    }
  }
}
