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
 * $Id: ASTJCompilationUnit.java,v 1.2 2006/11/01 21:31:43 marcelop Exp $
 */
package org.eclipse.emf.codegen.merge.java.facade.ast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.eclipse.emf.codegen.merge.java.facade.JCompilationUnit;
import org.eclipse.emf.codegen.merge.java.facade.JNode;
import org.eclipse.emf.codegen.merge.java.facade.JType;
import org.eclipse.jdt.core.dom.ASTNode;
import org.eclipse.jdt.core.dom.AbstractTypeDeclaration;
import org.eclipse.jdt.core.dom.CompilationUnit;
import org.eclipse.jdt.core.dom.ImportDeclaration;
import org.eclipse.jdt.core.dom.PackageDeclaration;
import org.eclipse.jdt.core.dom.TypeDeclaration;
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

/**
 * Wraps {@link CompilationUnit} object.
 * 
 * @since 2.2.0
 */
public class ASTJCompilationUnit extends ASTJNode implements JCompilationUnit
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
  private Map<ASTNode, String> trackedContentsMap = new HashMap<ASTNode, String>();
  
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
   * @return compilation unit wrapped by ASTJCompilationUnit
   */
  protected CompilationUnit getASTCompilationUnit()
  {
    return (CompilationUnit)getASTNode();
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
    JType type = facadeHelper.getMainType(this);
    return type != null ? type.getName() + ".java" : (String)getASTCompilationUnit().getProperty(NAME_PROPERTY);
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
    CompilationUnit astCompilationUnit = getASTCompilationUnit();
    PackageDeclaration astPackage = astCompilationUnit.getPackage();
    if (astPackage != null)
    {
      children.add(facadeHelper.convertToNode(astPackage));
    }
    ListRewrite importsListRewrite = rewriter.getListRewrite(astCompilationUnit, CompilationUnit.IMPORTS_PROPERTY);
    for (Iterator<?> i = importsListRewrite.getRewrittenList().iterator(); i.hasNext();)
    {
      children.add(facadeHelper.convertToNode(i.next()));
    }
    ListRewrite typesListRewrite = rewriter.getListRewrite(astCompilationUnit, CompilationUnit.TYPES_PROPERTY);
    for (Iterator<?> i = typesListRewrite.getRewrittenList().iterator(); i.hasNext();)
    {
      AbstractTypeDeclaration abstractASTType = (AbstractTypeDeclaration)i.next();
      // TODO add Java 5 Enum and Annotation
      if (abstractASTType instanceof TypeDeclaration)
      {
        children.add(facadeHelper.convertToNode(abstractASTType));
      }
    }
    return children.isEmpty() ? Collections.<JNode>emptyList() : Collections.<JNode>unmodifiableList(children);
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
        ((ASTFacadeHelper)facadeHelper).logInfo("Got header <" + headerString + ">");
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

    // apply changes using ASTRewrite
    IDocument targetDoc = new Document(new String(originalContents));
    TextEdit edits = rewriter.rewriteAST(targetDoc, ((ASTFacadeHelper) facadeHelper).getJavaCoreOptions());
    try
    {
      edits.apply(targetDoc);
      if (ASTFacadeHelper.DEBUG)
      {
        ((ASTFacadeHelper)facadeHelper).logInfo("Document after ASTRewrite:\n<" + targetDoc.get() + ">\nEnd of document.");
      }
    }
    catch (MalformedTreeException e)
    {
      if (ASTFacadeHelper.DEBUG)
      {
        ((ASTFacadeHelper)facadeHelper).logError("Error applying edits: ", e);
      }
    }
    catch (BadLocationException e)
    {
      if (ASTFacadeHelper.DEBUG)
      {
        ((ASTFacadeHelper)facadeHelper).logError("Error applying edits: ", e);
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
        ((ASTFacadeHelper)facadeHelper).logError("Error creating and applying replace edits: ", e);
      }
    }
    
    // apply header
    if (headerString != null)
    {
      setHeader(targetDoc);
    }      
    
    return targetDoc.get();
  }
  
  /* (non-Javadoc)
   * @see org.eclipse.emf.codegen.merge.java.facade.ast.ASTJNode#addChild(org.eclipse.emf.codegen.merge.java.facade.JNode)
   */
  @Override
  public boolean addChild(JNode child)
  {
    if (child.getParent() != null)
    {
      return false;
    }
    
    CompilationUnit compilationUnit = getASTCompilationUnit();
    ASTNode astNode = ASTFacadeHelper.getASTNode(child);
    if (astNode instanceof ImportDeclaration)
    {
      addValueToListProperty(compilationUnit, astNode, CompilationUnit.IMPORTS_PROPERTY);
    }
    else if (astNode instanceof AbstractTypeDeclaration)
    {
      addValueToListProperty(compilationUnit, astNode, CompilationUnit.TYPES_PROPERTY);
    }
    else if (astNode instanceof PackageDeclaration)
    {
      setNodeProperty(compilationUnit, astNode, CompilationUnit.PACKAGE_PROPERTY);
    }
    else
    {
      return false;
    }
    ((ASTJNode)child).setParent(this);    
    return true;
  }
  
  /* (non-Javadoc)
   * @see org.eclipse.emf.codegen.merge.java.facade.ast.ASTJNode#insertSibling(org.eclipse.emf.codegen.merge.java.facade.JNode, org.eclipse.emf.codegen.merge.java.facade.JNode, boolean)
   */
  @Override
  public boolean insertSibling(JNode node, JNode newSibling, boolean before)
  {
    if (newSibling.getParent() != null)
    {
      return false;
    }
    
    CompilationUnit compilationUnit = getASTCompilationUnit();
    ASTNode astNode = ASTFacadeHelper.getASTNode(node);
    ASTNode newASTNode = ASTFacadeHelper.getASTNode(newSibling);
    
    if (newASTNode instanceof ImportDeclaration)
    {
      if (astNode instanceof ImportDeclaration)
      {
        insert(compilationUnit, CompilationUnit.IMPORTS_PROPERTY, newASTNode, astNode, before);
      }
      else if (astNode instanceof PackageDeclaration)
      {
        // always insert import after package
        insertFirst(compilationUnit, CompilationUnit.IMPORTS_PROPERTY, newASTNode);
      }
      else if (astNode instanceof AbstractTypeDeclaration)
      {
        // always insert import before types
        insertLast(compilationUnit, CompilationUnit.IMPORTS_PROPERTY, newASTNode);
      }
      else 
      {
        return false;
      }
    }
    else if (newASTNode instanceof AbstractTypeDeclaration)
    {
      if (astNode instanceof AbstractTypeDeclaration)
      {
        insert(compilationUnit, CompilationUnit.TYPES_PROPERTY, newASTNode, astNode, before);
      }      
      if (astNode instanceof PackageDeclaration)
      {
        // always insert type after package
        insertFirst(compilationUnit, CompilationUnit.TYPES_PROPERTY, newASTNode);
      }
      else if (astNode instanceof ImportDeclaration)
      {
        // always insert type after import
        insertFirst(compilationUnit, CompilationUnit.TYPES_PROPERTY, newASTNode);        
      }
      else 
      {
        return false;
      }
    }
    else if (newASTNode instanceof PackageDeclaration)
    {
      setNodeProperty(compilationUnit, newASTNode, CompilationUnit.PACKAGE_PROPERTY);
    }
    else
    {
      return false;
    }
    ((ASTJNode)newSibling).setParent(this);    
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
  public boolean remove(JNode node)
  {
    if (node.getParent() == null)
    {
      return false;
    }
    
    ASTNode astNode = ASTFacadeHelper.getASTNode(node);
    CompilationUnit compilationUnit = getASTCompilationUnit();    
    if (astNode instanceof ImportDeclaration)
    {
      removeNodeFromListProperty(compilationUnit, astNode, CompilationUnit.IMPORTS_PROPERTY);
    }
    else if (astNode instanceof AbstractTypeDeclaration)
    {
      removeNodeFromListProperty(compilationUnit, astNode, CompilationUnit.TYPES_PROPERTY);
    }
    else if (astNode instanceof PackageDeclaration)
    {
      setNodeProperty(compilationUnit, null, CompilationUnit.PACKAGE_PROPERTY);
    }    
    else
    {
      return false;
    }
    ((ASTJNode)node).setParent(null);    
    return true;
  }

  /**
   * @return map of nodes (<code>ASTNode</code>) to contents of the node (<code>String</code>).
   * This map will be used during rewrite to track the nodes and set the exact contents of them.
   */
  protected Map<ASTNode, String> getTrackedContentsMap()
  {
    return trackedContentsMap;
  }

  /**
   * Enables tracking for all nodes in tracked contents map ({@link #getTrackedContentsMap()}).
   * <p>
   * This method must be called before call to {@link ASTRewrite#rewriteAST()} or {@link ASTRewrite#rewriteAST(IDocument, Map)}.
   * 
   * @return map of {@link ITrackedNodePosition} objects to <code>String</code> content
   * 
   * @see ASTRewrite#track(ASTNode)
   */
  protected Map<ITrackedNodePosition, String> trackNodePositions()
  {
    Map<ITrackedNodePosition, String> trackedNodePositionsMap = new HashMap<ITrackedNodePosition, String>(trackedContentsMap.keySet().size());
    for (ASTNode node : trackedContentsMap.keySet())
    {
      try
      {
        ITrackedNodePosition trackedNodePosition = rewriter.track(node);
        trackedNodePositionsMap.put(trackedNodePosition, trackedContentsMap.get(node));
      }
      catch (Exception e)
      {
        if (ASTFacadeHelper.DEBUG)
        {
          ((ASTFacadeHelper)facadeHelper).logError("Enabling tracking in " + getName(), e);
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
    for (ITrackedNodePosition position : trackedNodePositions.keySet())
    {
      String contents = trackedNodePositions.get(position);
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
          ((ASTFacadeHelper)facadeHelper).logError("Creating ReplaceEdit in " + getName(), e);
        }
      }
    }
    return replaceContentsEdits;
  }  
}
