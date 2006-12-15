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
 * $Id: ASTJCompilationUnit.java,v 1.5 2006/12/15 20:26:12 marcelop Exp $
 */
package org.eclipse.emf.codegen.merge.java.facade.ast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.eclipse.jdt.core.dom.ASTNode;
import org.eclipse.jdt.core.dom.CompilationUnit;
import org.eclipse.jdt.core.dom.PackageDeclaration;
import org.eclipse.jdt.core.dom.rewrite.ASTRewrite;
import org.eclipse.jdt.core.dom.rewrite.ITrackedNodePosition;
import org.eclipse.jdt.core.dom.rewrite.ListRewrite;
import org.eclipse.jface.text.BadLocationException;
import org.eclipse.jface.text.Document;
import org.eclipse.jface.text.IDocument;
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
    Map<ITrackedNodePosition, String> trackedNodePositions = trackNodePositions();

    String contents = new String(originalContents);    
    IDocument targetDoc = new Document(contents);
    TextEdit edits = rewriter.rewriteAST(targetDoc, getFacadeHelper().getJavaCoreOptions());
    
    if (edits.getChildrenSize() != 0 || edits.getLength() != 0 || !trackedNodePositions.isEmpty())
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
  
      // replace tracked nodes by string content
      try 
      {
        TextEdit replaceContentsEdits = createReplaceContentsEdit(trackedNodePositions);
        replaceContentsEdits.apply(targetDoc);
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
   * Enables tracking for all nodes in tracked contents map ({@link #getAllTrackedContentsMap()}).
   * <p>
   * This method must be called before call to {@link ASTRewrite#rewriteAST()} or {@link ASTRewrite#rewriteAST(IDocument, Map)}.
   * 
   * @return map of {@link ITrackedNodePosition} objects to <code>String</code> content
   * 
   * @see ASTRewrite#track(ASTNode)
   */
  protected Map<ITrackedNodePosition, String> trackNodePositions()
  {
    Map<ITrackedNodePosition, String> trackedNodePositionsMap = new HashMap<ITrackedNodePosition, String>(allTrackedContentsMap.keySet().size());
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
    return trackedNodePositionsMap;
  }
  
  /**
   * Creates a {@link TextEdit} that replaces all content in the given map.
   * <p>
   * The positions of the nodes in the map must match the document the edit will be applied on.
   * Therefore, this method must be called after {@link ASTRewrite#rewriteAST()} or {@link ASTRewrite#rewriteAST(IDocument, Map)}.
   * 
   * @param trackedNodePositions map of {@link ITrackedNodePosition} to <code>String</code> content 
   * @return
   * 
   * @see #trackNodePositions()
   */
  protected TextEdit createReplaceContentsEdit(Map<ITrackedNodePosition, String> trackedNodePositions)
  {
    MultiTextEdit replaceContentsEdits = new MultiTextEdit();
    for (Map.Entry<ITrackedNodePosition, String> entry : trackedNodePositions.entrySet())
    {
      ITrackedNodePosition position = entry.getKey();
      String contents = entry.getValue();
      try
      {
        ReplaceEdit replaceEdit = new ReplaceEdit(position.getStartPosition(), position.getLength(), contents);
        replaceContentsEdits.addChild(replaceEdit);
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
    return replaceContentsEdits;
  }  
}
