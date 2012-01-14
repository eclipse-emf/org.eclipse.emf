/**
 * Copyright (c) 2006 IBM Corporation and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: 
 *   IBM - Initial API and implementation
 */
package org.eclipse.emf.codegen.merge.java.facade.ast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.eclipse.jdt.core.dom.ASTNode;
import org.eclipse.jdt.core.dom.CompilationUnit;
import org.eclipse.jdt.core.dom.EnumConstantDeclaration;
import org.eclipse.jdt.core.dom.EnumDeclaration;
import org.eclipse.jdt.core.dom.PackageDeclaration;
import org.eclipse.jdt.core.dom.rewrite.ASTRewrite;
import org.eclipse.jdt.core.dom.rewrite.ITrackedNodePosition;
import org.eclipse.jdt.core.dom.rewrite.ListRewrite;
import org.eclipse.jdt.core.formatter.IndentManipulation;
import org.eclipse.jface.text.BadLocationException;
import org.eclipse.jface.text.Document;
import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.IRegion;
import org.eclipse.text.edits.InsertEdit;
import org.eclipse.text.edits.MalformedTreeException;
import org.eclipse.text.edits.MultiTextEdit;
import org.eclipse.text.edits.ReplaceEdit;
import org.eclipse.text.edits.TextEdit;

import org.eclipse.emf.codegen.merge.java.facade.FacadeHelper;
import org.eclipse.emf.codegen.merge.java.facade.JCompilationUnit;
import org.eclipse.emf.codegen.merge.java.facade.JNode;
import org.eclipse.emf.codegen.merge.java.facade.JType;

/**
 * Wraps {@link CompilationUnit} object.
 * 
 * @since 2.2.0
 */
public class ASTJCompilationUnit extends ASTJNode<CompilationUnit> implements JCompilationUnit
{
  /**
   * Name for the name property of the compilation unit.
   */
  public static final String NAME_PROPERTY = "ASTJCompilationUnit.name";

  /**
   * Pattern to extract and replace header.
   * Header is any number of block or line comments, spaces or newline characters
   * before any Java code (package, import or type declaration).
   */
  protected final static Pattern HEADER_PATTERN = Pattern.compile("^(?:(?:/\\*(?:.|[\\n\\r])*?\\*/)|(?://.*(?:[\\n\\r])+)|(?:\\s+))+");  

  /**
   * Map of nodes (<code>ASTNode</code>) to contents of the node (<code>String</code>).
   * This map is used during rewrite to track the nodes positions and set the exact contents of them.
   */
  private Map<ASTNode, String> allTrackedContentsMap = new HashMap<ASTNode, String>();
  
  /**
   * Set of all nodes that have been commented out
   */
  private Set<ASTNode> commentedOutNodes = null;  
  
  /**
   * Header of the compilation unit to be set during rewrite.
   * If it is null, the header will not be modified.
   */
  protected String headerString = null;
  
  /**
   * Original contents of the compilation unit
   */
  protected char[] originalContents;
  
  /**
   * @param compilationUnit
   */
  public ASTJCompilationUnit(CompilationUnit compilationUnit)
  {
    super(compilationUnit);
  }
  
  @Override
  public void dispose()
  {
    allTrackedContentsMap.clear();
    headerString = null;
    originalContents = null;
    if (commentedOutNodes != null)
    {
      commentedOutNodes.clear();
      commentedOutNodes = null;
    }
    
    super.dispose();
  }
  
  /**
   * Sets original contents of the compilation unit to be used for converting nodes
   * to strings.
   * 
   * @param originalContents
   */
  public void setOriginalContents(char[] originalContents)
  {
    this.originalContents = originalContents;
  }  

  /**
   * @return original contents of the compilation unit
   */
  public char[] getOriginalContents()
  {
    return originalContents;
  }
  
  /**
   * Returns the name of the main type suffixed with java extension.
   * <p>
   * In the absence of type defaults to original name that compilation unit was created with.
   * <p>
   * Created for compatibility with JDOM implementation of <code>getName()</code> for {@link JCompilationUnit}.
   * 
   * @return name of the main type suffixed with java extension
   * 
   * @see org.eclipse.emf.codegen.merge.java.facade.JNode#getName()
   */
  public String getName()
  {
    JType type = getFacadeHelper().getMainType(this);
    return type != null ? type.getName() + ".java" : (String)getWrappedObject().getProperty(NAME_PROPERTY);
  }
  
  /**
   * Sets the name of the main type of compilation unit.
   * 
   * @see FacadeHelper#getMainType(JCompilationUnit)
   * @see org.eclipse.emf.codegen.merge.java.facade.JNode#setName(java.lang.String)
   * @see org.eclipse.emf.codegen.merge.java.facade.JNode#getQualifiedName()
   */    
  public void setName(String name)
  {
    JType type = getFacadeHelper().getMainType(this);
    if (type != null)
    {
      type.setName(name);
    }
    else
    {
      getASTNode().setProperty(NAME_PROPERTY, name);
    }
  }  
  
  /**
   * Same as the <code>getName()</code>.
   * 
   * @see org.eclipse.emf.codegen.merge.java.facade.AbstractJNode#computeQualifiedName()
   */
  @Override
  protected String computeQualifiedName()
  {
    return getName();
  }    

  /**
   * Returns the list of children in order: package declaration (<code>JPackage</code>), imports (<code>JImport</code>), and types (<code>JType</code>).
   * 
   * @see org.eclipse.emf.codegen.merge.java.facade.AbstractJNode#getChildren()
   */
  @Override
  public List<JNode> getChildren()
  {
    if (!isDisposed())
    {
      CompilationUnit astCompilationUnit = getASTNode();
      List<JNode> children = new ArrayList<JNode>();
      PackageDeclaration astPackage = astCompilationUnit.getPackage();
      if (astPackage != null)
      {
        JNode child = getFacadeHelper().convertToNode(astPackage);
        if (child != null)
        {
          children.add(child);
        }
      }
      
      ListRewrite importsListRewrite = rewriter.getListRewrite(astCompilationUnit, CompilationUnit.IMPORTS_PROPERTY);
      for (Object importDeclaration : importsListRewrite.getRewrittenList())
      {
        JNode child = getFacadeHelper().convertToNode(importDeclaration);
        if (child != null)
        {
          children.add(child);
        }
      }
      
      ListRewrite typesListRewrite = rewriter.getListRewrite(astCompilationUnit, CompilationUnit.TYPES_PROPERTY);
      for (Object type : typesListRewrite.getRewrittenList())
      {
        JNode child = getFacadeHelper().convertToNode(type);
        if (child != null)
        {
          children.add(child);
        }
      }
      if (!children.isEmpty())
      {
        return Collections.unmodifiableList(children);
      }
    }
    return Collections.emptyList();
  }

  public String getHeader()
  {
    if (headerString == null)
    {
      Matcher matcher = HEADER_PATTERN.matcher(new String(originalContents));
      if (matcher.find())
      {
        String headerString = matcher.group();
        if (ASTFacadeHelper.DEBUG)
        {
          getFacadeHelper().logInfo("Got header <" + headerString + ">");
        }
        return headerString;
      }
      else
      {
        return "";
      }
    }
    return headerString;
  }
  
  /**
   * This implementation remembers the header string, and does replacement of the header in the final
   * document after any other changes.
   * <p>
   * For the files with no package declaration, 
   * the new header might be inserted at the beginning of the file instead of being replaced.
   * 
   * @see org.eclipse.emf.codegen.merge.java.facade.JCompilationUnit#setHeader(java.lang.String)
   */
  public void setHeader(String header)
  {
    if (header != null)
    {
      this.headerString = header;
    }    
  }
  
  /**
   * Method to replace the header in the given document.
   * 
   * @param targetDoc
   */
  protected void setHeader(IDocument targetDoc)
  {
    String targetDocString = targetDoc.get();
    Matcher matcher = HEADER_PATTERN.matcher(targetDocString);
    // replace or append
    if (matcher.find())
    {
      targetDocString = headerString.concat(targetDocString.substring(matcher.end()));
    }
    else 
    {
      targetDocString = headerString.concat(targetDocString);
    }
    targetDoc.set(targetDocString);
  }    

  /**
   * Returns the rewritten text after applying all the changes made to JNode tree.
   * 
   * @see org.eclipse.emf.codegen.merge.java.facade.ast.ASTJNode#getContents()
   */
  @Override
  public String getContents()
  {
    // enable tracking for nodes that have string content
    AbstractRewriter contentsReplacer = null;
    if (allTrackedContentsMap != null && !allTrackedContentsMap.isEmpty())
    {
      contentsReplacer = new NodeContentsReplacer();
    }
    
    // enable tracking for commented out nodes
    AbstractRewriter commenter = null;
    if (commentedOutNodes != null && !commentedOutNodes.isEmpty())
    {
      commenter = new NodeCommenter();
    }

    String contents = new String(originalContents);    
    IDocument targetDoc = new Document(contents);
    TextEdit edits = rewriter.rewriteAST(targetDoc, getFacadeHelper().getJavaCoreOptions());
    
    if (edits.getChildrenSize() != 0 || edits.getLength() != 0 || commenter != null || contentsReplacer != null || headerString != null)
    {
      // apply changes using ASTRewrite
      //
      try
      {
        edits.apply(targetDoc);
        if (ASTFacadeHelper.DEBUG)
        {
          getFacadeHelper().logInfo("Document after ASTRewrite:\n<" + targetDoc.get() + ">\nEnd of document.");
        }
      }
      catch (MalformedTreeException e)
      {
        if (ASTFacadeHelper.DEBUG)
        {
          getFacadeHelper().logError("Error applying edits: ", e);
        }
      }
      catch (BadLocationException e)
      {
        if (ASTFacadeHelper.DEBUG)
        {
          getFacadeHelper().logError("Error applying edits: ", e);
        }
      }
  
      // apply additional edits like replacing or commenting out nodes
      //
      try 
      {
        TextEdit additionalEdits = null;
        
        if (contentsReplacer != null)
        {
          additionalEdits = contentsReplacer.createEdits(additionalEdits, targetDoc);
        }
        
        if (commenter != null)
        {
          additionalEdits = commenter.createEdits(additionalEdits, targetDoc);
        }    
        
        if (additionalEdits != null)
        {
          additionalEdits.apply(targetDoc);
        }
      }
      catch (Exception e) 
      {
        if (ASTFacadeHelper.DEBUG)
        {
          getFacadeHelper().logError("Error creating and applying replace edits: ", e);
        }
      }
      
      // apply header
      if (headerString != null)
      {
        setHeader(targetDoc);
      }      
      
      contents = targetDoc.get();
    }
    return contents;
  }
  
  /* (non-Javadoc)
   * @see org.eclipse.emf.codegen.merge.java.facade.ast.ASTJNode#addChild(org.eclipse.emf.codegen.merge.java.facade.JNode)
   */
  @Override
  public boolean addChild(ASTJNode<?> child)
  {
    if (child.getParent() != null)
    {
      return false;
    }

    if (child instanceof ASTJImport)
    {
      insertLast(child, CompilationUnit.IMPORTS_PROPERTY);
    }
    else if (child instanceof ASTJAbstractType<?>)
    {
      insertLast(child, CompilationUnit.TYPES_PROPERTY);
    }
    else if (child instanceof ASTJPackage)
    {
      setNodeProperty(getASTNode(), child.getASTNode(), CompilationUnit.PACKAGE_PROPERTY);
    }
    else
    {
      return false;
    }
    child.setParent(this);
    return true;
  }
  
  /* (non-Javadoc)
   * @see org.eclipse.emf.codegen.merge.java.facade.ast.ASTJNode#insertSibling(org.eclipse.emf.codegen.merge.java.facade.JNode, org.eclipse.emf.codegen.merge.java.facade.JNode, boolean)
   */
  @Override
  public boolean insertSibling(ASTJNode<?> node, ASTJNode<?> newSibling, boolean before)
  {
    if (newSibling.getParent() != null)
    {
      return false;
    }

    if (newSibling instanceof ASTJImport)
    {
      if (node instanceof ASTJImport)
      {
        insert(newSibling, CompilationUnit.IMPORTS_PROPERTY, node, before);
      }
      else if (node instanceof ASTJPackage)
      {
        insertFirst(newSibling, CompilationUnit.IMPORTS_PROPERTY);
      }
      else if (node instanceof ASTJAbstractType<?>)
      {
        insertLast(newSibling, CompilationUnit.IMPORTS_PROPERTY);
      }
      else
      {
        return false;
      }
    }
    else if (newSibling instanceof ASTJAbstractType<?>)
    {
      if (node instanceof ASTJAbstractType<?>)
      {
        insert(newSibling, CompilationUnit.TYPES_PROPERTY, node, before);
      }
      else if (node instanceof ASTJImport)
      {
        insertFirst(newSibling, CompilationUnit.TYPES_PROPERTY);
      }
      else if (node instanceof ASTJPackage)
      {
        insertFirst(newSibling, CompilationUnit.TYPES_PROPERTY);
      }
      else
      {
        return false;
      }
    }
    else if (newSibling instanceof ASTJPackage)
    {
      setNodeProperty(getASTNode(), newSibling.getASTNode(), CompilationUnit.PACKAGE_PROPERTY);
    }
    else
    {
      return false;
    }
    newSibling.setParent(this);
    return true;
  }
  
  /**
   * Removes the given node.
   * <p>
   * This implementation will not perform moving of the node if the node is inserted after being removed.
   * Hence, the removed node must not be inserted again.
   * 
   * @see org.eclipse.emf.codegen.merge.java.facade.ast.ASTJNode#remove(ASTJNode)
   */
  @Override
  public boolean remove(ASTJNode<?> node)
  {
    if (node.getParent() != this)
    {
      return false;
    }

    if (node instanceof ASTJImport)
    {
      remove(node, CompilationUnit.IMPORTS_PROPERTY);
    }
    else if (node instanceof ASTJAbstractType<?>)
    {
      remove(node, CompilationUnit.TYPES_PROPERTY);
    }
    else if (node instanceof ASTJPackage)
    {
      setNodeProperty(getASTNode(), null, CompilationUnit.PACKAGE_PROPERTY);
    }
    else
    {
      return false;
    }
    node.setParent(null);
    return true;
  }

  /**
   * @return map of all nodes of the compilation unit and its children (<code>ASTNode</code> to <code>String</code> contents of the node).
   * <p>
   * This map will be used during rewrite to track the nodes and set the exact contents of them.
   */
  protected Map<ASTNode, String> getAllTrackedContentsMap()
  {
    return allTrackedContentsMap;
  }
  
  /**
   * @return set of nodes that have been commented out
   */
  protected Set<ASTNode> getCommentedOutNodes()
  {
    if (commentedOutNodes == null)
    {
      commentedOutNodes = new HashSet<ASTNode>();
    }
    return commentedOutNodes;
  }  
  
  /**
   * Base class for additional rewriters used during rewrite process.
   */
  protected abstract class AbstractRewriter
  {
    /**
     * Creates additional edits to be applied on the document
     * 
     * @param existingEdits existing edits on  
     * @param doc document after call to {@link ASTRewrite#rewriteAST()} or {@link ASTRewrite#rewriteAST(IDocument, Map)}.
     * @return existingEdits with new edits added
     */
    public TextEdit createEdits(TextEdit existingEdits, IDocument doc)
    {
      if (existingEdits == null)
      {
        existingEdits = new MultiTextEdit();
      }
      return addEdits(existingEdits, doc);
    }

    protected abstract TextEdit addEdits(TextEdit existingEdits, IDocument doc);
  }

  protected class NodeContentsReplacer extends AbstractRewriter
  {
    protected Map<ITrackedNodePosition, String> trackedNodePositionsMap;

    /**
     * Enables tracking for all nodes in tracked contents map ({@link #getAllTrackedContentsMap()}).
     * <p>
     * This constructor must be called before call to {@link ASTRewrite#rewriteAST()} or {@link ASTRewrite#rewriteAST(IDocument, Map)}.
     * 
     * @see ASTRewrite#track(ASTNode)
     */
    public NodeContentsReplacer()
    {
      Map<ASTNode, String> allTrackedContentsMap = getAllTrackedContentsMap();
      trackedNodePositionsMap = new HashMap<ITrackedNodePosition, String>(Math.max((int)(allTrackedContentsMap.keySet().size() / .75f) + 1, 16));
      for (ASTNode node : allTrackedContentsMap.keySet())
      {
        try
        {
          ITrackedNodePosition trackedNodePosition = rewriter.track(node);
          trackedNodePositionsMap.put(trackedNodePosition, allTrackedContentsMap.get(node));
        }
        catch (Exception e)
        {
          if (ASTFacadeHelper.DEBUG)
          {
            getFacadeHelper().logError("Enabling tracking in " + getName(), e);
          }
        }
      }
    }

    /**
     * Creates a {@link TextEdit} that replaces contents of all tracked nodes.
     * 
     * @param existingEdits existing edits on  
     * @param doc document after call to {@link ASTRewrite#rewriteAST()} or {@link ASTRewrite#rewriteAST(IDocument, Map)}.
     * @return existingEdits with new edits added
     * 
     * @see #trackedNodePositionsMap
     */
    @Override
    protected TextEdit addEdits(TextEdit existingEdits, IDocument doc)
    {
      for (Map.Entry<ITrackedNodePosition, String> entry : trackedNodePositionsMap.entrySet())
      {
        ITrackedNodePosition position = entry.getKey();
        String contents = entry.getValue();
        try
        {
          ReplaceEdit replaceEdit = new ReplaceEdit(position.getStartPosition(), position.getLength(), contents);
          existingEdits.addChild(replaceEdit);
        }
        // this should never happen
        catch (Exception e)
        {
          // log the error, ignore the change and continue
          if (ASTFacadeHelper.DEBUG)
          {
            getFacadeHelper().logError("Creating ReplaceEdit for <" + contents + "> in " + getName(), e);
          }
        }
      }
      return existingEdits;
    }
  }

  protected class NodeCommenter extends AbstractRewriter
  {
    /**
     * String to be inserted at the beginning of lines to indicate the line comment
     */
    protected static final String LINE_COMMENT_STRING = "//";

    protected static final String EMPTY_STRING = "";

    /**
     * Map of commented out nodes to their tracked positions
     */
    protected Map<ASTNode, ITrackedNodePosition> commentedOutPositions = new HashMap<ASTNode, ITrackedNodePosition>();

    /**
     * Responsible for inserting line breaks at the beginning and the end of the commented out nodes
     */
    protected LineBreakInserter lineBreakInserter;
    
    /**
     * Map of insert offsets to InsertEdit objects created.
     * Used to prevent adding different InsertEdit objects at the same offset.
     */
    protected Map<Integer, InsertEdit> addedInsertEdits = new HashMap<Integer, InsertEdit>();
    
    /**
     * List of currently added text edits that have to be reverted (removed) in case of an exception.
     */
    protected List<TextEdit> textEditsToRevert = new ArrayList<TextEdit>();
    
    /**
     * Document after call to {@link ASTRewrite#rewriteAST()} or {@link ASTRewrite#rewriteAST(IDocument, Map)}.
     */
    protected IDocument doc;
    
    /**
     * Enables tracking for all commented out nodes.
     * <p>
     * This constructor must be called before call to {@link ASTRewrite#rewriteAST()} or {@link ASTRewrite#rewriteAST(IDocument, Map)}.
     * 
     * @see ASTRewrite#track(ASTNode)
     */    
    public NodeCommenter()
    {
      for (ASTNode node : getCommentedOutNodes())
      {
        try
        {
          commentedOutPositions.put(node, rewriter.track(node));
        }
        catch (Exception e)
        {
          if (ASTFacadeHelper.DEBUG)
          {
            getFacadeHelper().logError("Enabling tracking in " + getName(), e);
          }
        }
      }
    }

    /**
     * Adds a {@link TextEdit} that comments out required nodes.
     * 
     * @param existingEdits existing edits on  
     * @param doc document after call to {@link ASTRewrite#rewriteAST()} or {@link ASTRewrite#rewriteAST(IDocument, Map)}.
     * @return existingEdits with new edits added
     * 
     * @see NodeContentsReplacer#trackedNodePositionsMap
     */    
    @Override
    protected TextEdit addEdits(TextEdit existingEdits, IDocument doc)
    {
      this.doc = doc;
      lineBreakInserter = new LineBreakInserter();      

      // loop for all commented out nodes
      for (Map.Entry<ASTNode, ITrackedNodePosition> entry : commentedOutPositions.entrySet())
      {
        ASTNode node = entry.getKey();
        ITrackedNodePosition nodePosition = entry.getValue();
        try
        {
          // insert line break and comment out the first line if needed
          // note that first line might move backward beyond node start (e.g. if a comma of the previous enum constant has been commented out)
          int firstLine = addLineBreakBeforeNode(existingEdits, nodePosition, node);

          // comment out all lines of the node itself, from firstLine to last line
          int lastLine = doc.getLineOfOffset(nodePosition.getStartPosition() + nodePosition.getLength());
          commentOutLines(existingEdits, firstLine, lastLine);

          // if the node is less than 1 line long, but its contents is replaced by multiple lines,
          // comment out each line in it
          if (firstLine == lastLine && getAllTrackedContentsMap().containsKey(node))
          {
            findAndCommentOutReplaceEdit(existingEdits, nodePosition);
          }
          
          // if there is anything after the node on the same line, insert line break
          addLineBreakAfterNode(existingEdits, nodePosition, node);
          
          // reset text edits; since this node is processed successfully, there is no need to revert these changes
          textEditsToRevert.clear();
        }
        catch (Exception e)
        {
          // revert all current edits for this node
          for (TextEdit edit : textEditsToRevert)
          {
            existingEdits.removeChild(edit);
          }
          textEditsToRevert.clear();

          // log the error, ignore the change and continue
          if (ASTFacadeHelper.DEBUG)
          {
            try
            {
              getFacadeHelper().logError(
                "Unable to comment out <" + doc.get().substring(nodePosition.getStartPosition(), nodePosition.getLength()) + "> in "
                  + getName() + " : " + e.toString() + ". There should be no tracked changes to commented out nodes.");
            }
            catch (Exception innerException)
            {
              getFacadeHelper().logError(
                "Unable to comment out node in " + getName() + " : " + e.toString() + ". Unable to get contents of the node either : "
                  + innerException.toString() + ". There should be no tracked changes to commented out nodes.");
            }
          }
        }
      }
      return existingEdits;
    }

    /**
     * Add line break before the node if needed. If there was a line break added at this position (e.g. after the previous node), replace it.
     * Added line break will contain the marker to comment out the first line of the node.
     * If <code>InsertEdit</code> has been created, the returned line number is the line after the line break, i.e. the next
     * line that has to be commented out.
     * If no changes are made, returned line number is the first line of the node.
     * 
     * @param existingEdits
     * @param nodePosition
     * @param node
     * @return line number of the next line that has to be commented out
     * @throws BadLocationException
     * 
     * @see LineBreakInserter#createLineBreakBeforeNode(ITrackedNodePosition, ASTNode)
     */
    protected int addLineBreakBeforeNode(TextEdit existingEdits, ITrackedNodePosition nodePosition, ASTNode node) throws BadLocationException
    {
      // insert line break at the first line if there is something before the node start and the beginning of the line
      InsertEdit lineBreakEdit = lineBreakInserter.createLineBreakBeforeNode(nodePosition, node);
      if (lineBreakEdit != null)
      {
        // replace existing InsertEdit at this position
        // (case when previous node ends at the same position that current node starts at)
        InsertEdit existingEdit = addedInsertEdits.get(lineBreakEdit.getOffset());
        if (existingEdit != null)
        {
          existingEdits.removeChild(existingEdit);
          addedInsertEdits.remove(existingEdit);
        }
        existingEdits.addChild(lineBreakEdit);
        textEditsToRevert.add(lineBreakEdit);
        addedInsertEdits.put(lineBreakEdit.getOffset(), lineBreakEdit);
        
        // this line has been commented out, return next line number
        return doc.getLineOfOffset(lineBreakEdit.getOffset()) + 1;
      }
      else
      {
        // return the line number of the start of the node
        return doc.getLineOfOffset(nodePosition.getStartPosition());
      }
    }
    
    /**
     * Creates and adds <code>InsertEdit</code>s that comment out all lines between <code>firstLine</code> and
     * <code>lastLine</code> inclusively.
     * <p>
     * If there is a ReplaceEdit that covers positions where <code>InsertEdit</code>s are inserted,
     * then ReplaceEdit is replaced by another ReplaceEdit with modified text with all lines commented out.
     * <p>
     * If there is any other problem adding new <code>InsertEdit</code>s to existing edits, original exception
     * is re-thrown. 
     * 
     * @param existingEdits
     * @param firstLine
     * @param lastLine
     * @throws BadLocationException
     */
    protected void commentOutLines(TextEdit existingEdits, int firstLine, int lastLine) throws BadLocationException
    {
      for (int i = firstLine; i <= lastLine; i++)
      {
        InsertEdit edit = new InsertEdit(doc.getLineOffset(i), LINE_COMMENT_STRING);
        try 
        {
          existingEdits.addChild(edit);
          textEditsToRevert.add(edit);
          addedInsertEdits.put(edit.getOffset(), edit);          
        }
        catch (MalformedTreeException e)
        {
          // handle the case when there is a replace edit that covers these lines
          TextEdit causeEdit = e.getChild();
          if (causeEdit instanceof ReplaceEdit)
          {
            ReplaceEdit newReplaceEdit = commentOutReplaceEdit((ReplaceEdit)causeEdit);
            // skip all lines that replace edit covers
            i = doc.getLineOfOffset(newReplaceEdit.getOffset() + newReplaceEdit.getLength());
          }
          else
          {
            // should not happen, re-throw exception
            throw e;
          }
        }
      }
    }

    /**
     * Replaces given {@link ReplaceEdit} by new ReplaceEdit with each line commented out.
     * <p>
     * New ReplaceEdit has the same offset and length as the given ReplaceEdit. Text of new ReplaceEdit
     * has each line but the first one commented out. Given ReplaceEdit is removed from its parent,
     * and new ReplaceEdit is inserted in its place.
     * @param replaceEdit
     * @return new ReplaceEdit
     */
    protected ReplaceEdit commentOutReplaceEdit(ReplaceEdit replaceEdit)
    {
      TextEdit parent = replaceEdit.getParent();
      String newText = commentOutEachLine(replaceEdit.getText());
      ReplaceEdit newEdit = new ReplaceEdit(replaceEdit.getOffset(), replaceEdit.getLength(), newText);
      parent.removeChild(replaceEdit);
      parent.addChild(newEdit);
      return newEdit;
    }

    /**
     * Finds first ReplaceEdit in existing edits that covers node position range, and comments out
     * each line in it.
     * <p> 
     * This method is used in the case when existing replace edit covers only a part of one line,
     * but the contents that it replaces is longer than 1 line. In this case, such replace edit
     * will be found and its contents changed by this method.
     * 
     * @param existingEdits
     * @param nodePosition range of existing node that has a corresponding ReplaceEdit for node's range
     * @see #commentOutReplaceEdit(ReplaceEdit)
     */
    protected void findAndCommentOutReplaceEdit(TextEdit existingEdits, ITrackedNodePosition nodePosition)
    {
      // create and try to add dummy edit to find the ReplaceEdit
      // this should be faster than lookup since underneath of addChild() binary search is used
      ReplaceEdit dummyEdit = new ReplaceEdit(nodePosition.getStartPosition() + 1, 0, "");
      try
      {
        existingEdits.addChild(dummyEdit);
      }
      catch (MalformedTreeException e)
      {
        TextEdit causeEdit = e.getChild();
        if (causeEdit instanceof ReplaceEdit)
        {
          commentOutReplaceEdit((ReplaceEdit)causeEdit);
        }
        else if (ASTFacadeHelper.DEBUG)
        {
          // this should never happen
          getFacadeHelper().logError("Unable to find ReplaceEdit for node in " + getName() + " : " + e.toString());
        }
      }
      finally
      {
        // make sure that dummy edit is not in the tree
        try
        {
          existingEdits.removeChild(dummyEdit);
        }
        catch (Exception e)
        {
          // Ignore
        }
      }
    }

    /**
     * Comments out each line but the first one in the given text, and returns resulting new text.
     * @param text
     * @return new text with each line but the first one commented out
     */
    protected String commentOutEachLine(String text)
    {
      // assume length will grow by 10% (average line length is 20 characters)
      StringBuilder sb = new StringBuilder(text.length() + text.length() / 10);
      char[] textContent = text.toCharArray();
      int lastPos = 0, currentPos = 0;
      
      for (int i = 0; i < textContent.length; i++)
      {
        if (textContent[i] == '\n')
        {
          currentPos = i;
        }
        else if (textContent[i] == '\r')
        {
          if (i + 1 < textContent.length && textContent[i + 1] == '\n')
          {
            // next position is checked as well
            currentPos = ++i;
          }
          else
          {
            currentPos = i;
          }
        }
        
        if (lastPos != currentPos)
        {
          // char at currentPos is copied as well
          sb.append(textContent, lastPos, currentPos - lastPos + 1);
          sb.append(LINE_COMMENT_STRING);
          // lastPos, currentPos points at the next chars that will be copied later
          lastPos = ++currentPos;
        }
      }
      
      // copy last piece if any
      if (currentPos < textContent.length)
      {
        sb.append(textContent, currentPos, textContent.length - currentPos);
      }      
      
      return sb.toString();
    }    
    
    /**
     * If there is anything after the node, inserts the line break to prevent commenting
     * out extra content.
     * 
     * @param existingEdits
     * @param nodePosition
     * @param node
     * @throws BadLocationException
     * 
     * @see LineBreakInserter#createLineBreakAfterNode(ITrackedNodePosition, ASTNode)
     */
    protected void addLineBreakAfterNode(TextEdit existingEdits, ITrackedNodePosition nodePosition, ASTNode node) throws BadLocationException
    {
      InsertEdit lineBreakEdit = lineBreakInserter.createLineBreakAfterNode(nodePosition, node);
      if (lineBreakEdit != null)
      {
        // do not add a new line if there is one
        InsertEdit existingEdit = addedInsertEdits.get(lineBreakEdit.getOffset());
        if (existingEdit == null)
        {
          existingEdits.addChild(lineBreakEdit);
          textEditsToRevert.add(lineBreakEdit);
          addedInsertEdits.put(lineBreakEdit.getOffset(), lineBreakEdit);
        }
      }
    }    
    
    /**
     * Class that inserts extra line breaks between nodes when nodes are being commented out.
     * 
     * @see #createLineBreakBeforeNode(ITrackedNodePosition, ASTNode)
     * @see #createLineBreakAfterNode(ITrackedNodePosition, ASTNode)
     */
    protected class LineBreakInserter
    {
      protected char[] charContent;
    
      protected LineBreakInserter()
      {
        this.charContent = getDocument().get().toCharArray();
      }
    
      /**
       * @return document to be used to lookup line numbers and line information
       */
      protected IDocument getDocument()
      {
        return doc;
      }
      
      /**
       * Creates line break at the beginning of the node when there is another node declared at the same line.
       * The returned edit also contains "//" after the line break - the first line of the node becomes already commented out.
       * @param nodePosition
       * @param node
       * 
       * @return <code>InsertEdit</code> or <code>null</code> if none required
       * @throws BadLocationException
       */
      protected InsertEdit createLineBreakBeforeNode(ITrackedNodePosition nodePosition, ASTNode node) throws BadLocationException
      {
        int startPos = nodePosition.getStartPosition();
        IRegion lineInfo = getDocument().getLineInformationOfOffset(startPos);
        
        // if needed, comment out a comma of the previous enum constant
        if (node.getNodeType() == ASTNode.ENUM_CONSTANT_DECLARATION)
        {
          InsertEdit insertEdit = commentOutEnumConstantSeparator((EnumConstantDeclaration)node, lineInfo, nodePosition);
          if (insertEdit != null)
          {
            return insertEdit;
          }
        }
        
        // if there is anything before the node on the same line, create line break and comment out the first line of the node
        if (!isWhitespace(lineInfo.getOffset(), startPos))
        {
          return new InsertEdit(startPos, createLineBreakString(lineInfo.getOffset(), true));
        }
        else
        {
          // there is only whitespace on this line - no need for line break
          return null;
        }
      }
    
      /**
       * Creates line break at the end of the node when there is another node declared at the same line.
       * @param nodePosition
       * @param node
       * 
       * @return <code>InsertEdit</code> or <code>null</code> if none required
       * @throws BadLocationException
       */
      protected InsertEdit createLineBreakAfterNode(ITrackedNodePosition nodePosition, ASTNode node) throws BadLocationException
      {
        int endPos = nodePosition.getStartPosition() + nodePosition.getLength();        
        IRegion lineInfo = getDocument().getLineInformationOfOffset(endPos);
    
        // for enum constants, insert line break after the comma if there is anything after the comma
        if (node.getNodeType() == ASTNode.ENUM_CONSTANT_DECLARATION)
        {
          return createLineBreakAfterEnumConstant(node, lineInfo, nodePosition);
        }
        
        // if there is any content after the node on the same line, insert line break (to prevent commenting out extra content)
        else if (!isWhitespace(endPos, lineInfo.getOffset() + lineInfo.getLength()))
        {
          return new InsertEdit(endPos, createLineBreakString(lineInfo.getOffset(), false));
        }
        else
        {
          // there is only whitespace on this line - no need for line break
          return null;
        }
      }
      
      /**
       * @param lineStart the first character of the line excluding <code>CR</code> or <code>LF</code> characters.
       * @return indent of the line starting at <code>lineStart</code>, empty string if there is no indent or <code>lineStart</code> is invalid position
       * @see IndentManipulation#isIndentChar(char)
       */
      protected String getIndent(int lineStart)
      {
        if (lineStart >= 0 && lineStart < charContent.length)
        {
          int i = lineStart;
          while (i < charContent.length && IndentManipulation.isIndentChar(charContent[i]))
          {
            i++;
          }
          return new String(charContent, lineStart, i - lineStart);
        }
        return EMPTY_STRING;
      }
    
      /**
       * Determines if there are only whitespace characters in the given range in char array.
       * @param start
       * @param end
       * @return <code>true</code> if only whitespace characters are between <code>start</code> and <code>end</code>
       * 
       * @see Character#isWhitespace(char)
       */
      protected boolean isWhitespace(int start, int end)
      {
        if (start >= 0 && end < charContent.length && start <= end)
        {
          for (int i = start; i < end; i++)
          {
            if (!Character.isWhitespace(charContent[i]))
            {
              return false;
            }
          }
        }
        return true;
      }
      
      /**
       * Creates line break string containing line delimiter, line comment string if <code>isCommentedOut</code> is <code>true</code>,
       * and same indent string as the line that break is inserted at. 
       * @param startOfLineOffset
       * @param isCommentedOut
       * @return line break string
       * @throws BadLocationException
       */
      protected String createLineBreakString(int startOfLineOffset, boolean isCommentedOut) throws BadLocationException
      {
        return createLineBreakString(getDocument().getLineDelimiter(getDocument().getLineOfOffset(startOfLineOffset)), startOfLineOffset, isCommentedOut);
      }
      
      /**
       * Creates line break string containing line delimiter, line comment string if <code>isCommentedOut</code> is <code>true</code>,
       * and indent string that is the same as of the line that break is inserted at. 
       * 
       * @param lineDelimiter
       * @param startOfLineOffset
       * @param isCommentedOut
       * @return line break string
       */
      protected String createLineBreakString(String lineDelimiter, int startOfLineOffset, boolean isCommentedOut)
      {
        String indent = getIndent(startOfLineOffset);
        StringBuilder sb = new StringBuilder(indent.length() + 10);
        sb.append(lineDelimiter);
        if (isCommentedOut)
        {
          sb.append(LINE_COMMENT_STRING);
        }
        sb.append(indent);
        return sb.toString();
      }

      /**
       * Creates an InsertEdit that comments out separator after the previous enum constant.
       * <p>
       * Separator of the previous constant needs to be commented out when all following constants are commented out.
       * <p>
       * For example, if there are constants <code>C1, C2, C3;</code> and constants C2 and C3 are commented out, comma after C1 should be commented out as well.
       * When this method is called on the first line of C2, the returned edit will comment out a comma after C1. Calling this method on C3 will return <code>null</code> since C2 and C3 are both
       * commented out.
       * <p>
       * Returned edit (if any) may or may not contain a line break depending whether there is any content before the comma on the same line.
       * 
       * @param enumConstant
       * @param lineInfo
       * @param nodePosition
       * @return <code>InsertEdit</code> or <code>null</code> if none required
       * @throws BadLocationException
       */
      private InsertEdit commentOutEnumConstantSeparator(EnumConstantDeclaration enumConstant, IRegion lineInfo, ITrackedNodePosition nodePosition) throws BadLocationException
      {
        // if previous node is not commented out, but all the following nodes are, comment out a comma (constant separator)
        ASTJNode<?> astjNode = (ASTJNode<?>)getFacadeHelper().convertToNode(enumConstant);
        if (astjNode != null)
        {
          ASTNode parent = astjNode.getParent().getASTNode();
          List<?> enumConstants = rewriter.getListRewrite(parent, EnumDeclaration.ENUM_CONSTANTS_PROPERTY).getRewrittenList();
          int constantIndex = enumConstants.indexOf(enumConstant);
          if (constantIndex > 0 && constantIndex < enumConstants.size())
          {
            ASTNode previousNode = (ASTNode)enumConstants.get(constantIndex - 1);
            List<?> followingConstants = enumConstants.subList(constantIndex, enumConstants.size());
            // if previous node is not commented out, but all following are
            if (!commentedOutPositions.containsKey(previousNode) && commentedOutPositions.keySet().containsAll(followingConstants))
            {
              // insert new line at the end of previous constant
              int commaPosition = nodePosition.getStartPosition() - 1;
              while (commaPosition >=0 && Character.isWhitespace(charContent[commaPosition]))
              {
                commaPosition--;
              }
              
              // we should be able to find the comma because the range for enum constants includes all preceding comments up to the previous constant
              // if TargetSourceRangeComputer of ASTRewrite changes, this logic should change to skip comments
              // see org.eclipse.emf.codegen.merge.java.facade.ast.CommentAwareSourceRangeComputer#getEnumConstantSourceRange(ASTNode)
              if (commaPosition >=0 && charContent[commaPosition] == ',')
              {
                // if comma is on a line by itself, comment it out but do not insert the line break
                int line = getDocument().getLineOfOffset(commaPosition);
                int startOfLineOffset = getDocument().getLineOffset(line);
                if (isWhitespace(startOfLineOffset, commaPosition))
                {
                  return new InsertEdit(startOfLineOffset, LINE_COMMENT_STRING);
                }
                else
                {
                  return new InsertEdit(commaPosition, createLineBreakString(getDocument().getLineDelimiter(line), startOfLineOffset, true));
                }
              }
            }
          }
        }
        return null;
      }

      /**
       * If needed, creates a line break after enum constant.
       * 
       * @param node
       * @param lineInfo
       * @param nodePosition
       * @return <code>InsertEdit</code> or <code>null</code> if none required
       * @throws BadLocationException
       */
      private InsertEdit createLineBreakAfterEnumConstant(ASTNode node, IRegion lineInfo, ITrackedNodePosition nodePosition) throws BadLocationException
      {
        int nodeEndPos = nodePosition.getStartPosition() + nodePosition.getLength();
        int endOfLine = lineInfo.getOffset() + lineInfo.getLength();
        int i = nodeEndPos;

        // skip whitespace, up to the end of the line
        while (i <= endOfLine && Character.isWhitespace(charContent[i]))
        {
          i++;
        }

        // insert line break if there is non-whitespace before end of the line
        if (i < endOfLine)
        {
          // if current char is comma, insert line break after it
          //
          // we should be able to find the comma because the range for enum constants includes all trailing comments up to the separator
          // if TargetSourceRangeComputer of ASTRewrite changes, this logic should change to skip comments
          // see org.eclipse.emf.codegen.merge.java.facade.ast.CommentAwareSourceRangeComputer#getEnumConstantSourceRange(ASTNode)
          if (charContent[i] == ',')
          {
            // do not insert line break if there is only whitespace after comma
            if (!isWhitespace(i + 1, endOfLine))
            {
              return new InsertEdit(i + 1, createLineBreakString(lineInfo.getOffset(), false));
            }
          }
          else
          {
            // there is anything else but comma after constant - insert line break
            return new InsertEdit(i, createLineBreakString(lineInfo.getOffset(), false));
          }
        }
        // there is only whitespace after enum constant on the same line - do not add line breaks
        return null;
      }
      
    }
  }
}
