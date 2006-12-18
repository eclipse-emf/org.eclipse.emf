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
 * $Id: ASTJCompilationUnit.java,v 1.6 2006/12/18 21:15:01 marcelop Exp $
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
    List<JNode> children = new ArrayList<JNode>();
    CompilationUnit astCompilationUnit = getASTNode();
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
    return children.isEmpty() ? Collections.<JNode> emptyList() : Collections.<JNode> unmodifiableList(children);
  }

  /**
   * @return the original header
   * 
   * @see org.eclipse.emf.codegen.merge.java.facade.JCompilationUnit#getHeader()
   */
  public String getHeader()
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
  
  /**
   * This implementation remembers the header string, and does replacement of the header in the final
   * document after any other changes.
   * <p>
   * For the files with no package declaration, 
   * the new header might be inserted at the beginning of the file instead of being replaced.
   * <p>
   * Note that <code>getHeader()</code> will not return the new header.  
   * 
   * @see org.eclipse.emf.codegen.merge.java.facade.JCompilationUnit#setHeader(java.lang.String)
   */
  public void setHeader(String header)
  {
    if (header == null)
    {
      return;
    }
    
    this.headerString = header;
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
   * @see org.eclipse.emf.codegen.merge.java.facade.ast.ASTJNode#remove(org.eclipse.emf.codegen.merge.java.facade.JNode)
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
    Map<ITrackedNodePosition, String> trackedNodePositionsMap;

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
     * @see #trackNodePositions()
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
     * Enables tracking for all commented out nodes.
     * <p>
     * This constructor must be called before call to {@link ASTRewrite#rewriteAST()} or {@link ASTRewrite#rewriteAST(IDocument, Map)}.
     * 
     * @see ASTRewrite#track(ASTNode)
     */    
    protected NodeCommenter()
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
     * @see #trackNodePositions()
     */    
    @Override
    protected TextEdit addEdits(TextEdit existingEdits, IDocument doc)
    {
      LineBreakInserter lineBreakInserter = new LineBreakInserter(doc);
      
      // TODO reconsider having this map
      // map of positions of added InsertEdit objects
      // used to not create InsertEdit at the same position
      HashMap<Integer, InsertEdit> addedInsertEdits = new HashMap<Integer, InsertEdit>();
      
      for (Map.Entry<ASTNode, ITrackedNodePosition> entry : commentedOutPositions.entrySet())
      {
        ASTNode node = entry.getKey();
        ITrackedNodePosition nodePosition = entry.getValue();
        
        // used to revert changes made to this node in case of exception
        ArrayList<TextEdit> textEditsToRevert = new ArrayList<TextEdit>();
        
        int startPos = nodePosition.getStartPosition();
        int endPos = startPos + nodePosition.getLength();
        
        try
        {
          int firstLine = doc.getLineOfOffset(startPos);
          int lastLine = doc.getLineOfOffset(endPos);

          IRegion lineInfo = doc.getLineInformation(firstLine);

          // insert line break at the first line if there is something before the node start and the beginning of the line
          InsertEdit lineBreakEdit = lineBreakInserter.createFirstLineBreak(lineInfo, nodePosition, node);
          if (lineBreakEdit != null)
          {
            // replace existing InsertEdit at this position
            // (case when previous node ends at the same position that current starts at)
            InsertEdit existingEdit = addedInsertEdits.get(lineBreakEdit.getOffset());
            if (existingEdit != null)
            {
              existingEdits.removeChild(existingEdit);
              addedInsertEdits.remove(existingEdit);
            }
            existingEdits.addChild(lineBreakEdit);
            textEditsToRevert.add(lineBreakEdit);
            addedInsertEdits.put(lineBreakEdit.getOffset(), lineBreakEdit);
            // first line is processed
            firstLine++;
          }

          // add "//" on each line of the node
          for (int i = firstLine; i <= lastLine; i++)
          {
            lineInfo = doc.getLineInformation(i);
            InsertEdit edit = new InsertEdit(lineInfo.getOffset(), LINE_COMMENT_STRING);
            existingEdits.addChild(edit);
            textEditsToRevert.add(edit);
            addedInsertEdits.put(edit.getOffset(), edit);
          }

          // if there is anything after the node on the same line, insert line break
          lineBreakEdit = lineBreakInserter.createLastLineBreak(lineInfo, nodePosition, node);
          if (lineBreakEdit != null)
          {
            InsertEdit existingEdit = addedInsertEdits.get(lineBreakEdit.getOffset());
            if (existingEdit == null)
            {
              existingEdits.addChild(lineBreakEdit);
              textEditsToRevert.add(lineBreakEdit);
              addedInsertEdits.put(lineBreakEdit.getOffset(), lineBreakEdit);
            }
          }          
        }
        catch (Exception e)
        {
          // revert all current edits for this node
          for (TextEdit edit : textEditsToRevert)
          {
            existingEdits.removeChild(edit);
          }

          // log the error, ignore the change and continue
          if (ASTFacadeHelper.DEBUG)
          {
            getFacadeHelper().logError(
              "Unable to comment out <" + doc.get().substring(startPos, endPos - startPos) + "> in " + getName() + " : " + e.toString()
                + ". There should be no tracked changes to commented out nodes.");
          }
        }
      }
      return existingEdits;
    }

    /**
     * Class that inserts extra line breaks between nodes to separate them when nodes are being commented out.
     * 
     * @see #createFirstLineBreak(IRegion, ITrackedNodePosition, ASTNode)
     * @see #createLastLineBreak(IRegion, ITrackedNodePosition, ASTNode)
     */
    protected class LineBreakInserter
    {
      protected char[] charContent;
    
      protected IDocument doc;
    
      protected LineBreakInserter(IDocument doc)
      {
        this.charContent = doc.get().toCharArray();
        this.doc = doc;
      }
    
      /**
       * Creates line break at the beginning of the node when there is another node declared at the same line.
       * The returned edit also contains "//" after the line break - the first line of the node becomes already commented out.
       * 
       * @param lineInfo
       * @param nodePosition
       * @param node
       * @return
       * @throws BadLocationException
       */
      protected InsertEdit createFirstLineBreak(IRegion lineInfo, ITrackedNodePosition nodePosition, ASTNode node) throws BadLocationException
      {
        int startPos = nodePosition.getStartPosition();
        int firstLine = doc.getLineOfOffset(startPos);
        String indent = getIndent(charContent, lineInfo.getOffset());
        
        // TODO comment out a comma of the previous enum constant
        if (!isWhitespace(charContent, lineInfo.getOffset(), startPos))
        {
          return new InsertEdit(startPos, doc.getLineDelimiter(firstLine) + LINE_COMMENT_STRING + indent);
        }
        else
        {
          // there is only whitespace on this line - no need for line break
          return null;
        }
      }
    
      /**
       * Creates line break at the end of the node when there is another node declared at the same line.
       * 
       * @param lineInfo
       * @param nodePosition
       * @param node
       * @return
       * @throws BadLocationException
       */
      protected InsertEdit createLastLineBreak(IRegion lineInfo, ITrackedNodePosition nodePosition, ASTNode node) throws BadLocationException
      {
        int endPos = nodePosition.getStartPosition() + nodePosition.getLength();
    
        // for enum constants, insert line break after the comma if there is anything after the comma 
        if (node.getNodeType() == ASTNode.ENUM_CONSTANT_DECLARATION)
        {
          return createLineBreakAfterEnumConstant(node, lineInfo, nodePosition);
        }
        else if (!isWhitespace(charContent, endPos, lineInfo.getOffset() + lineInfo.getLength()))
        {
          String indent = getIndent(charContent, lineInfo.getOffset());
          return new InsertEdit(endPos, doc.getLineDelimiter(doc.getLineOfOffset(endPos)) + indent);
        }
        else
        {
          // there is only whitespace on this line - no need for line break
          return null;
        }
      }
      
      /**
       * @param content
       * @param lineStart the first character of the line excluding <code>CR</code> or <code>LF</code> characters.
       * @return indent of the line starting at <code>lineStart</code>, empty string if no indent, or <code>lineStart</code> is invalid position
       */
      protected String getIndent(char[] content, int lineStart)
      {
        if (lineStart >= 0 && lineStart < content.length)
        {
          int i = lineStart;
          while (i < content.length && IndentManipulation.isIndentChar(content[i]))
          {
            i++;
          }
          return new String(content, lineStart, i - lineStart);
        }
        return EMPTY_STRING;
      }
    
      /**
       * Determines if there are only whitespace characters in the given range in char array.
       * 
       * @param content
       * @param start
       * @param end
       * @return <code>true</code> if only whitespace characters are between <code>start</code> and <code>end</code>
       * 
       * @see Character#isWhitespace(char)
       */
      protected boolean isWhitespace(char[] content, int start, int end)
      {
        if (content != null && start >= 0 && end < content.length && start <= end)
        {
          for (int i = start; i < end; i++)
          {
            if (!Character.isWhitespace(content[i]))
            {
              return false;
            }
          }
        }
        return true;
      }

      /**
       * If needed, create a line break after enum constant.
       * 
       * @param node
       * @param lineInfo
       * @param nodePosition
       * @return
       * @throws BadLocationException
       */
      private InsertEdit createLineBreakAfterEnumConstant(ASTNode node, IRegion lineInfo, ITrackedNodePosition nodePosition) throws BadLocationException
      {
        int nodeEndPos = nodePosition.getStartPosition() + nodePosition.getLength();
        int endOfLine = lineInfo.getOffset() + lineInfo.getLength();
        int i = nodeEndPos;
        
        // skip whitespace until end of line
        while (i <= endOfLine && Character.isWhitespace(charContent[i]))
        {
          i++;
        }
        
        // insert line break if there is non-whitespace before end of the line
        if (i < endOfLine)
        {
          // if current char is comma, insert line break after it
          if (charContent[i] == ',')
          {
            // do not insert line break if there is only whitespace after comma
            if (!isWhitespace(charContent, i + 1, endOfLine))
            {
              return new InsertEdit(i + 1, doc.getLineDelimiter(doc.getLineOfOffset(nodeEndPos)) + getIndent(charContent, lineInfo.getOffset()));
            }
          }
          else
          {
            // there is anything else but comma after constant - 
            return new InsertEdit(i, doc.getLineDelimiter(doc.getLineOfOffset(nodeEndPos)) + getIndent(charContent, lineInfo.getOffset()));
          }
        }
        // there is only whitespace after enum constant on the same line - do not add line breaks
        return null;
      }
      
    }
  }
}
