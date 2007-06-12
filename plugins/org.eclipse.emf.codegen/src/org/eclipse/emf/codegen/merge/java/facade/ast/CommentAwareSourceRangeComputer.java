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
 * $Id: CommentAwareSourceRangeComputer.java,v 1.7 2007/06/12 20:56:05 emerks Exp $
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
import org.eclipse.jdt.core.dom.Annotation;
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
   * Node types that this range computer changes range for.
   */
  protected static final Set<Integer> NODE_TYPES_WITH_SPECIAL_RANGE;
  static
  {
    NODE_TYPES_WITH_SPECIAL_RANGE = new HashSet<Integer>();

    NODE_TYPES_WITH_SPECIAL_RANGE.add(ASTNode.ANNOTATION_TYPE_DECLARATION);
    NODE_TYPES_WITH_SPECIAL_RANGE.add(ASTNode.ANNOTATION_TYPE_MEMBER_DECLARATION);

    NODE_TYPES_WITH_SPECIAL_RANGE.add(ASTNode.TYPE_DECLARATION);
    NODE_TYPES_WITH_SPECIAL_RANGE.add(ASTNode.ENUM_DECLARATION);
    NODE_TYPES_WITH_SPECIAL_RANGE.add(ASTNode.ENUM_CONSTANT_DECLARATION);

    // this could be method body, for example
    NODE_TYPES_WITH_SPECIAL_RANGE.add(ASTNode.BLOCK);  
    
    NODE_TYPES_WITH_SPECIAL_RANGE.add(ASTNode.SINGLE_MEMBER_ANNOTATION);
    NODE_TYPES_WITH_SPECIAL_RANGE.add(ASTNode.NORMAL_ANNOTATION);
    NODE_TYPES_WITH_SPECIAL_RANGE.add(ASTNode.MARKER_ANNOTATION);
    
    NODE_TYPES_WITH_SPECIAL_RANGE.add(ASTNode.METHOD_DECLARATION);
    NODE_TYPES_WITH_SPECIAL_RANGE.add(ASTNode.FIELD_DECLARATION);
    
    NODE_TYPES_WITH_SPECIAL_RANGE.add(ASTNode.IMPORT_DECLARATION);
  }
  
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
  protected Map<ASTNode, Comment> includeTrailingCommentMapper = new HashMap<ASTNode, Comment>();

  /**
   * Set of nodes that must have default range returned for them 
   */
  protected Set<ASTNode> nodesWithDefaultRange = new HashSet<ASTNode>();

  /**
   * Original source used to create compilation unit
   */
  protected String source = null;
  
  /**
   * @param compilationUnit to use to get a list of comments and node positions
   * @param source original source used to create compilation unit
   */
  public CommentAwareSourceRangeComputer(CompilationUnit compilationUnit, String source)
  {
    this.compilationUnit = compilationUnit;
    this.source = source;

    List<?> commentList = compilationUnit.getCommentList();
    if (commentList != null)
    {
      this.commentArray = (Comment[])commentList.toArray();
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
    else
    {
      this.commentArray = new Comment [0];
      this.commentStartPositions = new int [0];
      this.commentEndPositions = new int [0];
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

    int commentIndex = findLastCommentInRangeIndex(rangeStartPos, rangeEndPos);
    return commentIndex == -1 ? null : commentArray[commentIndex];
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
   * @see #computeDefaultSourceRange(ASTNode)
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
      SourceRange range = computeDefaultSourceRange(previousNode);
      rangeStartPos = range.getStartPosition() + range.getLength();//previousNode.getStartPosition() + previousNode.getLength();
    }

    int rangeEndPos = compilationUnit.getExtendedStartPosition(node);

    int commentIndex = findFirstCommentInRangeIndex(rangeStartPos, rangeEndPos);
    return commentIndex == -1 ? null : commentArray[commentIndex];
  }

  /**
   * Finds index of the last comment in the given range. 
   * Start and end position of the resulting comment must be between <code>rangeStartPos</code>
   * and <code>rangeEndPos</code> inclusively.
   * 
   * @param rangeStartPos
   * @param rangeEndPos
   * @return comment index, <code>-1</code> if not found
   */
  final protected int findLastCommentInRangeIndex(int rangeStartPos, int rangeEndPos)
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
          return commentIndex;
        }
      }
    }
    // else - there is a comment that ends exactly at rangeEndPos    
    else
    {
      if (commentStartPositions[commentIndex] >= rangeStartPos)
      {
        return commentIndex;
      }
    }

    return -1;
  }

  /**
   * Finds index of the first comment that is completely in the given range. 
   * Start and end position of the resulting comment must be between <code>rangeStartPos</code>
   * and <code>rangeEndPos</code> inclusively.
   * 
   * @param rangeStartPos
   * @param rangeEndPos
   * @return comment index, <code>-1</code> if not found
   */
  final protected int findFirstCommentInRangeIndex(int rangeStartPos, int rangeEndPos)
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
          return commentIndex;
        }
      }
    }
    // else - there is a comment that starts exactly at rangeStartPos
    else
    {
      if (commentEndPositions[commentIndex] <= rangeEndPos)
      {
        return commentIndex;
      }
    }

    return -1;
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
   * Finds the start position of preceding comments of the given node.
   * <p>
   * Uses source to check if there is only whitespace between comments.
   * <p>
   * This method uses extended start position of the node as a starting position
   * to look for comments.
   * 
   * @param node
   * @return start position of the first preceding comment, or extended start position if there are no comments
   */
  protected int computeStartOfPrecedingComments(ASTNode node)
  {
    int nodeStartPosition = compilationUnit.getExtendedStartPosition(node);
    
    if (nodeStartPosition >= 0)
    {
      // find start position of furthest preceding comment
      ASTNode prevNode = getPreviousNode(node);
      int minStartPosition = prevNode == null ? 0 : compilationUnit.getExtendedStartPosition(prevNode) + compilationUnit.getExtendedLength(prevNode);
      int commentIndex = findLastCommentInRangeIndex(minStartPosition, nodeStartPosition);
      while(commentIndex >= 0)
      {
        int commentStartPosition = commentArray[commentIndex].getStartPosition();
        int commentEndPosition = commentStartPosition + commentArray[commentIndex].getLength();
        if (commentStartPosition >= minStartPosition && isWhitespace(commentEndPosition, nodeStartPosition))
        {
          nodeStartPosition = commentStartPosition;
          commentIndex--;
        }
        else
        {
          break;
        }
      }
    }
    return nodeStartPosition;
  }
  
  /**
   * Finds the end position of trailing comments of the given node.
   * <p>
   * Uses source to check if there is only whitespace between comments.
   * <p>
   * This method uses extended end position of the node as a starting position
   * to look for comments.
   * 
   * @param node
   * @return end position of the last trailing comment, or extended end position if there are no comments
   */
  protected int computeEndOfTrailingComments(ASTNode node)
  {
    int nodeEndPosition = compilationUnit.getExtendedStartPosition(node) + compilationUnit.getExtendedLength(node);
    
    if (nodeEndPosition >= 0)
    {
      // find start position of furthest preceding comment
      ASTNode nextNode = getNextNode(node);
      int maxEndPosition = nextNode == null ? source.length() : compilationUnit.getExtendedStartPosition(nextNode);
      int commentIndex = findFirstCommentInRangeIndex(nodeEndPosition, maxEndPosition);
      while(commentIndex >= 0 && commentIndex < commentArray.length)
      {
        int commentStartPosition = commentArray[commentIndex].getStartPosition();
        int commentEndPosition = commentStartPosition + commentArray[commentIndex].getLength();
        if (commentEndPosition <= maxEndPosition && isWhitespace(nodeEndPosition, commentStartPosition))
        {
          nodeEndPosition = commentEndPosition;
          commentIndex++;
        }
        else
        {
          break;
        }
      }
    }
    return nodeEndPosition;
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
      List<?> siblings = (List<?>)parent.getStructuralProperty(locationInParent);
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
      List<?> siblings = (List<?>)parent.getStructuralProperty(locationInParent);
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
   * @see #computeDefaultSourceRange(ASTNode)
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
   * Calculate the end of the line comment that is at the same line as <code>position</code>,
   * and with only whitespace between <code>position</code> and the start of the line comment.
   * <p>
   * Uses source contents and comment arrays.
   * <p>
   * 
   * @param position 
   * @return original position if no such comment exist
   */
  protected int determineEndPositionOfLineComment(int position)
  {
    if (position >= 0)
    {
      // extend to include the comment at the same line as the position 
      // if there is nothing between the position and the comment
      int firstTrailingCommentIndex = findFirstCommentInRangeIndex(position, Integer.MAX_VALUE);
      if (firstTrailingCommentIndex >= 0 && commentArray[firstTrailingCommentIndex].isLineComment())
      {
        int commentStartPos = commentArray[firstTrailingCommentIndex].getStartPosition();
        int commentEndPos = commentStartPos + commentArray[firstTrailingCommentIndex].getLength();
        if (compilationUnit.getLineNumber(commentStartPos) == compilationUnit.getLineNumber(position))
        {
          // check if there is just whitespace between position and start of the comment
          if (isWhitespace(position, commentStartPos))
          {
            return commentEndPos;
          }
        }
      }
    }
    return position;
  }
  
  /**
   * Calculate the end of the line comment that is at the same line as <code>nodeEnd</code>,
   * and ends before <code>nodeExtendedEnd</code>.
   * <p>
   * Does not use source contents.
   * <p>
   * 
   * @param nodeEnd
   * @param nodeExtendedEnd line comment must end before this position
   * @return original position if no such comment exist
   */
  protected int determineEndPositionOfLineComment(int nodeEnd, int nodeExtendedEnd)
  {
    if (nodeEnd < 0)
    {
      return nodeEnd;
    }
    if (nodeExtendedEnd < 0)
    {
      return nodeExtendedEnd;
    }
    
    // extend to include the comment at the same line as the position 
    int firstTrailingCommentIndex = findFirstCommentInRangeIndex(nodeEnd, nodeExtendedEnd);
    if (firstTrailingCommentIndex >= 0 && commentArray[firstTrailingCommentIndex].isLineComment())
    {
      int commentStartPos = commentArray[firstTrailingCommentIndex].getStartPosition();
      int commentEndPos = commentStartPos + commentArray[firstTrailingCommentIndex].getLength();
      if (compilationUnit.getLineNumber(commentStartPos) == compilationUnit.getLineNumber(nodeEnd))
      {
        return commentEndPos;
      }
    }
    return nodeEnd;
  }  
  
  /**
   * @param startPosition
   * @param endPosition
   * @return <code>true</code> if there is only whitespace between start and end position
   */
  protected boolean isWhitespace(int startPosition, int endPosition)
  {
    return source != null && startPosition >= 0 && endPosition < source.length()
      && source.substring(startPosition, endPosition).matches("\\s*");
  }
  
  /**
   * Specific method for enum constants. Returns
   * range that includes preceding comments, extended range,
   * trailing comments, and whitespace following the comments.
   * <p>
   * Using extended range as defined above is important to keep all the 
   * comments when enum constants are moved (e.g. removed and then inserted). 
   * <p>
   * Such extended range also allows keeping right separating new line characters between constants,
   * e.g. including trailing whitespace prevents putting constants at the end of line comments
   * on the same line (https://bugs.eclipse.org/bugs/show_bug.cgi?id=165703).
   * 
   * @param node
   * @return new extended range
   */
  protected SourceRange getEnumConstantSourceRange(ASTNode node)
  {
    // extend range backward
    int extendedStartPosition = computeStartOfPrecedingComments(node);
    
    // extend range forward
    int extendedEndPosition = computeEndOfTrailingComments(node);

    // add trailing whitespace
    extendedEndPosition = addWhitespaceAfterPosition(extendedEndPosition);

    return new SourceRange(extendedStartPosition, extendedEndPosition - extendedStartPosition);
  }
  
  /**
   * If possible, extends given position to include any whitespace following the position.
   * @param position
   */
  protected int addWhitespaceAfterPosition(int position)
  {
    if (source != null && position >= 0)
    {
      while(position < source.length() && Character.isWhitespace(source.charAt(position++)))
      {
        // increments the position to add the white spaces
      }
      return position - 1;
    }
    else
    {
      return position;
    }
  }  
  
  /**
   * Calculates the default range for the node.
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
  public SourceRange computeDefaultSourceRange(ASTNode node)
  {
    // for all nodes but some use default extended range
    if (!NODE_TYPES_WITH_SPECIAL_RANGE.contains(node.getNodeType()))
    {
      return super.computeSourceRange(node);
    }

    // for enum constants use special range
    if (node.getNodeType() == ASTNode.ENUM_CONSTANT_DECLARATION)
    {
      return getEnumConstantSourceRange(node);
    }
    
    int nodeStartPos = node.getStartPosition();
    int nodeEndPos = nodeStartPos + node.getLength();

    int extendedStartPos = compilationUnit.getExtendedStartPosition(node);
    int extendedEndPos = extendedStartPos + compilationUnit.getExtendedLength(node);

    // we don't want to include the line comments immediately before an import in the 
    // returned range
    if (node.getNodeType() == ASTNode.IMPORT_DECLARATION)
    {
      extendedStartPos = nodeStartPos;
    }
    
    // line comments at the end of annotations are not a part of the range of annotation
    // TODO is not including line comments at the end of annotations a bug?
    extendedEndPos = node instanceof Annotation ?
      determineEndPositionOfLineComment(nodeEndPos) :
      determineEndPositionOfLineComment(nodeEndPos, extendedEndPos);
    
    // include line comments following end of the nodes into the range
    int extendedLength = extendedEndPos - extendedStartPos;
    
    return new SourceRange(extendedStartPos, extendedLength);
  }

  /**
   * Checks if the range should be extended for the given node.
   * <p>
   * Range should be extended for all nodes in {@link #nodesWithDefaultRange} except
   * for enum constants and for nodes that should use the default range (i.e. removed nodes). 
   * 
   * @param node
   * @return <code>true</code> if range should be extended, <code>false</code> otherwise
   */
  protected boolean shouldHaveExtendedRange(ASTNode node)
  {
    // since enum constants are separated by commas, they should always have the default range
    // we can not extend their range or reduce it
    return node.getNodeType() != ASTNode.ENUM_CONSTANT_DECLARATION && !nodesWithDefaultRange.contains(node);
  }
  
  /**
   * Calculates the range of a node as follows:
   * <p>
   * For any node the range is at least the range returned by {@link #computeDefaultSourceRange(ASTNode)}.
   * <p>
   * If the node should have an extended range (as defined by {@link #shouldHaveExtendedRange(ASTNode)}),
   * the range is extended to include all leading comments up to the previous node, and all hanging trailing comments
   * if the next node has been removed.
   * 
   * @see #computeDefaultSourceRange(ASTNode)
   * @see #shouldHaveExtendedRange(ASTNode)
   * @see org.eclipse.jdt.core.dom.rewrite.TargetSourceRangeComputer#computeSourceRange(org.eclipse.jdt.core.dom.ASTNode)
   */
  @Override
  public SourceRange computeSourceRange(ASTNode node)
  {
    // for all nodes but some use default extended range
    if (!NODE_TYPES_WITH_SPECIAL_RANGE.contains(node.getNodeType()))
    {
      return super.computeSourceRange(node);
    }    
    
    SourceRange sourceRange = computeDefaultSourceRange(node);

    // check if given node should use the default range (i.e. node removed),
    // or extended range (i.e. surrounding nodes removed)
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
