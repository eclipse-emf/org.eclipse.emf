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
 * $Id: CommentAwareSourceRangeComputer.java,v 1.1 2006/11/01 21:31:43 marcelop Exp $
 */
package org.eclipse.emf.codegen.merge.java.facade.ast;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.eclipse.jdt.core.dom.ASTNode;
import org.eclipse.jdt.core.dom.AbstractTypeDeclaration;
import org.eclipse.jdt.core.dom.BodyDeclaration;
import org.eclipse.jdt.core.dom.Comment;
import org.eclipse.jdt.core.dom.CompilationUnit;
import org.eclipse.jdt.core.dom.StructuralPropertyDescriptor;
import org.eclipse.jdt.core.dom.rewrite.ASTRewrite;
import org.eclipse.jdt.core.dom.rewrite.TargetSourceRangeComputer;


/**
 * Class that computes the ranges for the nodes for use by {@link ASTRewrite}. The ranges for the nodes will include
 * the comments between the nodes in order to preserve as many comments as possible when
 * nodes are moved or removed.
 * <p>
 * The default range for any node will include the comments immediately preceding the node and
 * the line comment at the last line of the node.
 * <p>
 * If the node and the previous node have not been marked for removal,
 * the range will also include all leading comments up to the previous node.
 * <p>
 * If the node is marked for removal, the range for node itself will be the default range. 
 * The leading comments between the node for removal and previous node will be in the range for
 * the previous node.
 * <p>
 * When the node is going to be removed, {@link #markNodeForRemoval(ASTNode)} must be called.
 * To undo the removal (in a situation where the removed node is inserted into the tree, in other words,
 * node is moved), {@link #unmarkNodeForRemoval(ASTNode)} must be called.
 * All nodes must be marked as removed before {@link ASTRewrite#rewriteAST()} or {@link ASTRewrite#rewriteAST(org.eclipse.jface.text.IDocument, Map)} is called.
 * <p>
 * Note that {@link ASTRewrite} removes all content after the removed node up to the start of the next node.
 * Hence, if two consecutive nodes are removed, all comments between them are lost. 
 *  
 * @see ASTRewrite#setTargetSourceRangeComputer(TargetSourceRangeComputer)
 * @see TargetSourceRangeComputer
 */
public class CommentAwareSourceRangeComputer extends TargetSourceRangeComputer
{
  /**
   * Array of comments from compilation unit
   */
  protected Comment[] commentArray = null;

  /**
   * Start positions of all comments
   */
  protected int[] commentStartPositions = null;

  /**
   * End positions of all comments
   */
  protected int[] commentEndPositions = null;

  /**
   * Compilation unit used to get extended ranges of the nodes
   */
  protected CompilationUnit compilationUnit = null;

  /**
   * Map of nodes to the trailing <code>Comment</code> nodes that must be included
   * in the range for the node
   */
  private Map<ASTNode, Comment> includeTrailingCommentMapper = new HashMap<ASTNode, Comment>();

  /**
   * Set of nodes that must have default range returned for them 
   */
  private Set<ASTNode> nodesWithDefaultRange = new HashSet<ASTNode>();

  /**
   * @param compilationUnit to use to get a list of comments and node positions
   */
  public CommentAwareSourceRangeComputer(CompilationUnit compilationUnit)
  {
    this.compilationUnit = compilationUnit;

    List<?> commentList = compilationUnit.getCommentList();
    if (commentList != null)
    {
      this.commentArray = (Comment[])commentList.toArray();
    }
    else
    {
      this.commentArray = new Comment [0];
    }

    this.commentStartPositions = new int [commentList.size()];
    this.commentEndPositions = new int [commentList.size()];
    int i = 0;
    for (Iterator<?> iter = commentList.iterator(); iter.hasNext();)
    {
      Comment comment = (Comment)iter.next();
      commentStartPositions[i] = comment.getStartPosition();
      commentEndPositions[i] = commentStartPositions[i] + comment.getLength();
      i++;
    }
  }

  /**
   * Finds the furthest trailing comment of <code>node</code> before the next node. 
   * If <code>includeHangingCommentsOnly</code> is
   * set to true, only comments up to the extended range of the next node will be considered.
   * If <code>includeHangingCommentsOnly</code> is
   * set to false, the first comment after the default range of the previous node is returned.
   * 
   * @param node
   * @param nextNode
   * @param includeHangingCommentsOnly
   * @return comment, <code>null</code> if not found
   */
  protected Comment findTrailingComments(ASTNode node, ASTNode nextNode, boolean includeHangingCommentsOnly)
  {
    if (node == null || nextNode == null)
    {
      return null;
    }

    // range to look for comments
    int rangeStartPos = compilationUnit.getExtendedStartPosition(node) + compilationUnit.getExtendedLength(node);
    int rangeEndPos;
    if (includeHangingCommentsOnly)
    {
      rangeEndPos = compilationUnit.getExtendedStartPosition(nextNode);
    }
    else
    {
      rangeEndPos = nextNode.getStartPosition();
    }

    return findLastCommentInRange(rangeStartPos, rangeEndPos);
  }

  /**
   * Finds the furthest leading comment of <code>node</code> after the previous node. 
   * <p>
   * If <code>includeHangingCommentsOnly</code> is
   * set to true, only comments up to the extended range of the previous node will be considered.
   * If <code>includeHangingCommentsOnly</code> is
   * set to false, the first comment after the default source range of the previous node is returned.
   * 
   * @param node
   * @param previousNode the previous node
   * @param includeHangingCommentsOnly
   * @return comment, <code>null</code> if not found
   * 
   * @see #getDefaultSourceRange(ASTNode)
   */
  protected Comment findLeadingComment(ASTNode node, ASTNode previousNode, boolean includeHangingCommentsOnly)
  {
    if (node == null || previousNode == null)
    {
      return null;
    }

    // range to look for comments
    int rangeStartPos;
    if (includeHangingCommentsOnly)
    {
      rangeStartPos = compilationUnit.getExtendedStartPosition(previousNode) + compilationUnit.getExtendedLength(previousNode);
    }
    else
    {
      // get the end position of the range for the previous node
      SourceRange range = getDefaultSourceRange(previousNode);
      rangeStartPos = range.getStartPosition() + range.getLength();//previousNode.getStartPosition() + previousNode.getLength();
    }

    int rangeEndPos = compilationUnit.getExtendedStartPosition(node);

    return findFirstCommentInRange(rangeStartPos, rangeEndPos);
  }

  /**
   * Finds last comment in the given range. 
   * Start and end position of the resulting comment must be between <code>rangeStartPos</code>
   * and <code>rangeEndPos</code> inclusively.
   * 
   * @param rangeStartPos
   * @param rangeEndPos
   * @return comment, <code>null</code> if not found
   */
  final protected Comment findLastCommentInRange(int rangeStartPos, int rangeEndPos)
  {
    int commentIndex = Arrays.binarySearch(commentEndPositions, rangeEndPos);

    if (commentIndex < 0)
    {
      // find insertion point from returned result (-(insertion point) - 1)
      // commentIndex is the index of the first element with end position > rangeEndPos
      commentIndex = -(commentIndex) - 1;

      // if there is an element with end position <= rangeEndPos
      if (commentIndex > 0)
      {
        // the previous element must have end position <= rangeEndPos
        commentIndex--;
        if (commentStartPositions[commentIndex] >= rangeStartPos)
        {
          return commentArray[commentIndex];
        }
      }
    }
    // else - there is a comment that ends exactly at rangeEndPos    
    else
    {
      if (commentStartPositions[commentIndex] >= rangeStartPos)
      {
        return commentArray[commentIndex];
      }
    }

    return null;
  }

  /**
   * Finds first comment that is completely in the given range. 
   * Start and end position of the resulting comment must be between <code>rangeStartPos</code>
   * and <code>rangeEndPos</code> inclusively.
   * 
   * @param rangeStartPos
   * @param rangeEndPos
   * @return comment, <code>null</code> if not found
   */
  final protected Comment findFirstCommentInRange(int rangeStartPos, int rangeEndPos)
  {
    int commentIndex = Arrays.binarySearch(commentStartPositions, rangeStartPos);

    if (commentIndex < 0)
    {
      // find insertion point from returned result (-(insertion point) - 1)
      // commentIndex is an index of the first element that has start position > rangeStartPos 
      commentIndex = -(commentIndex) - 1;

      // if there is a comment with start position > rangeStartPos
      if (commentIndex < commentArray.length)
      {
        if (commentEndPositions[commentIndex] <= rangeEndPos)
        {
          return commentArray[commentIndex];
        }
      }
    }
    // else - there is a comment that starts exactly at rangeStartPos
    else
    {
      if (commentEndPositions[commentIndex] <= rangeEndPos)
      {
        return commentArray[commentIndex];
      }
    }

    return null;
  }

  /**
   * Extends the given range to include the range of <code>nodeToAdd</code>.
   * <p>
   * Range of <code>nodeToAdd</code> must be outside and after the given <code>range</code>.
   * Otherwise, given <code>range</code> is returned.
   * 
   * @param nodeToAdd
   * @param range
   * @return new range
   */
  protected SourceRange extendRangeForward(ASTNode nodeToAdd, SourceRange range)
  {
    if (nodeToAdd != null && nodeToAdd.getStartPosition() > range.getStartPosition() + range.getLength())
    {
      return new SourceRange(range.getStartPosition(), nodeToAdd.getStartPosition() + nodeToAdd.getLength() - range.getStartPosition());
    }
    return range;
  }

  /**
   * Extends the given range to include the range of <code>nodeToAdd</code>.
   * <p>
   * Range of <code>nodeToAdd</code> must be outside and before the given <code>range</code>. 
   * Otherwise, given <code>range</code> is returned. 
   * 
   * @param nodeToAdd
   * @param range
   * @return new range
   */
  protected SourceRange extendRangeBackward(ASTNode nodeToAdd, SourceRange range)
  {
    if (nodeToAdd != null)
    {
      int nodeEndPos = nodeToAdd.getStartPosition() + nodeToAdd.getLength();
      if (nodeEndPos >= 0 && nodeEndPos < range.getStartPosition())
      {
        return new SourceRange(nodeToAdd.getStartPosition(), range.getStartPosition() + range.getLength() - nodeToAdd.getStartPosition());
      }
    }
    return range;
  }

  /**
   * Finds the node that follows the given node.
   * 
   * @param node
   * @return next node, or <code>null</code> if not found
   */
  protected ASTNode getNextNode(ASTNode node)
  {
    if (node == null)
    {
      return null;
    }
    StructuralPropertyDescriptor locationInParent = node.getLocationInParent();
    if (locationInParent != null && locationInParent.isChildListProperty())
    {
      ASTNode parent = node.getParent();
      List<?> siblings = (List)parent.getStructuralProperty(locationInParent);
      int index = siblings.indexOf(node);
      if (index >= 0 && index < siblings.size() - 1)
      {
        return (ASTNode)siblings.get(index + 1);
      }
    }
    return null;
  }

  /**
   * Finds the node that precedes the given node.
   * 
   * @param node
   * @return previous node, or <code>null</code> if not found
   */
  protected ASTNode getPreviousNode(ASTNode node)
  {
    if (node == null)
    {
      return null;
    }
    StructuralPropertyDescriptor locationInParent = node.getLocationInParent();
    if (locationInParent != null && locationInParent.isChildListProperty())
    {
      ASTNode parent = node.getParent();
      List<?> siblings = (List)parent.getStructuralProperty(locationInParent);
      int index = siblings.indexOf(node);
      if (index > 0 && index < siblings.size())
      {
        return (ASTNode)siblings.get(index - 1);
      }
    }
    return null;
  }

  /**
   * Mark this node as the node to be deleted.
   * <p>
   * The node marked for removal will use the default range.
   * <p>
   * The leading hanging comments will be added to the range of the previous node.
   * 
   * @param node
   * 
   * @see #getDefaultSourceRange(ASTNode)
   * @see #unmarkNodeForRemoval(ASTNode)
   */
  public void markNodeForRemoval(ASTNode node)
  {
    nodesWithDefaultRange.add(node);

    // add leading hanging comments to the previous node,
    // hence, only comments immediately preceding the node will be in the range for removal
    ASTNode keyNode = getPreviousNode(node);
    if (keyNode != null)
    {
      Comment trailingComment = findTrailingComments(keyNode, node, true);
      if (trailingComment != null)
      {
        includeTrailingCommentMapper.put(keyNode, trailingComment);
      }
    }
  }

  /**
   * Un-mark the node as the node for removal.
   * @param node
   * 
   * @see #markNodeForRemoval(ASTNode)
   */
  public void unmarkNodeForRemoval(ASTNode node)
  {
    nodesWithDefaultRange.remove(node);
    includeTrailingCommentMapper.remove(getPreviousNode(node));
  }

  /**
   * Returns the default range for the node.
   * <p>
   * The default range starts at an extended start position and ends at the non-extended end of the node.
   * If the node has a line comment on the last line of the node, this comment is included in the default range.
   * 
   * @param node
   * @return range for the node
   * 
   * @see CompilationUnit#getExtendedStartPosition(ASTNode)
   * @see ASTNode#getStartPosition() 
   */
  protected SourceRange getDefaultSourceRange(ASTNode node)
  {
    int extendedStartPos = compilationUnit.getExtendedStartPosition(node);
    int extendedEndPos = extendedStartPos + compilationUnit.getExtendedLength(node);

    int nodeStartPos = node.getStartPosition();
    int nodeEndPos = nodeStartPos + node.getLength();

    // check for the line comment at the last line of a node
    Comment firstTrailingComment = findFirstCommentInRange(nodeEndPos, extendedEndPos);
    if (firstTrailingComment != null && firstTrailingComment.isLineComment())
    {
      int commentStartPos = firstTrailingComment.getStartPosition();
      int commentEndPos = commentStartPos + firstTrailingComment.getLength();
      if (compilationUnit.getLineNumber(commentStartPos) == compilationUnit.getLineNumber(nodeEndPos))
      {
        return new SourceRange(extendedStartPos, commentEndPos - extendedStartPos);
      }
    }

    // return default range
    return new SourceRange(extendedStartPos, nodeEndPos - extendedStartPos);
  }

  /**
   * Checks if the range should be extended for the given node.
   * <p>
   * Range should be extended for a node of type {@link BodyDeclaration} or {@link AbstractTypeDeclaration}
   * unless the node should use the default range (i.e. node removed). 
   * 
   * @param node
   * @return true if range should be extended, false otherwise
   */
  protected boolean shouldHaveExtendedRange(ASTNode node)
  {
    return (node instanceof BodyDeclaration || node instanceof AbstractTypeDeclaration) && !nodesWithDefaultRange.contains(node);
  }

  /**
   * Calculates the range of a node as follows:
   * <p>
   * For any node the range is at least the range returned by {@link #getDefaultSourceRange(ASTNode)}.
   * <p>
   * If the node should have an extended range (as defined by {@link #shouldHaveExtendedRange(ASTNode)}),
   * the range is extended to include all leading comments up to the previous node, and hanging trailing comments
   * if the next node has been removed.
   * 
   * @see #getDefaultSourceRange(ASTNode)
   * @see #shouldHaveExtendedRange(ASTNode)
   * @see org.eclipse.jdt.core.dom.rewrite.TargetSourceRangeComputer#computeSourceRange(org.eclipse.jdt.core.dom.ASTNode)
   */
  @Override
  public SourceRange computeSourceRange(ASTNode node)
  {
    SourceRange sourceRange = getDefaultSourceRange(node);

    // check if given node should use the default range (i.e. node removed), or extended
    if (shouldHaveExtendedRange(node))
    {
      // add leading comments
      ASTNode prevNode = getPreviousNode(node);
      if (prevNode != null)
      {
        sourceRange = extendRangeBackward(findLeadingComment(node, prevNode, false), sourceRange);
      }

      // add trailing comments if necessary
      sourceRange = extendRangeForward(includeTrailingCommentMapper.get(node), sourceRange);
    }
    return sourceRange;
  }

  //  /**
  //   * Simpler implementation that sets the range of any node to be the node and all preceding comments
  //   */
  //  public SourceRange computeSourceRange(ASTNode node)
  //  {
  //    SourceRange range = new SourceRange(node.getStartPosition(), node.getLength());
  //
  //    range = extendRangeBackward(findLeadingComment(node, getPreviousNode(node), false), range);
  //
  //    return range;
  //  }
}
