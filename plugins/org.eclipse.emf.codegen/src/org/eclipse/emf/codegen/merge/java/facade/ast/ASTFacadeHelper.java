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
 * $Id: ASTFacadeHelper.java,v 1.4 2006/11/15 17:59:06 marcelop Exp $
 */
package org.eclipse.emf.codegen.merge.java.facade.ast;

import java.util.HashMap;
import java.util.Map;

import org.eclipse.jdt.core.JavaCore;
import org.eclipse.jdt.core.compiler.IProblem;
import org.eclipse.jdt.core.dom.AST;
import org.eclipse.jdt.core.dom.ASTNode;
import org.eclipse.jdt.core.dom.ASTParser;
import org.eclipse.jdt.core.dom.ArrayType;
import org.eclipse.jdt.core.dom.CompilationUnit;
import org.eclipse.jdt.core.dom.FieldDeclaration;
import org.eclipse.jdt.core.dom.ImportDeclaration;
import org.eclipse.jdt.core.dom.Initializer;
import org.eclipse.jdt.core.dom.Javadoc;
import org.eclipse.jdt.core.dom.MethodDeclaration;
import org.eclipse.jdt.core.dom.Name;
import org.eclipse.jdt.core.dom.PackageDeclaration;
import org.eclipse.jdt.core.dom.PrimitiveType;
import org.eclipse.jdt.core.dom.SimpleType;
import org.eclipse.jdt.core.dom.Type;
import org.eclipse.jdt.core.dom.TypeDeclaration;
import org.eclipse.jdt.core.dom.VariableDeclarationFragment;
import org.eclipse.jdt.core.dom.rewrite.ASTRewrite;
import org.eclipse.jdt.core.dom.rewrite.TargetSourceRangeComputer;
import org.eclipse.jdt.core.dom.rewrite.TargetSourceRangeComputer.SourceRange;
import org.eclipse.jdt.core.formatter.DefaultCodeFormatterConstants;

import org.eclipse.emf.codegen.CodeGenPlugin;
import org.eclipse.emf.codegen.merge.java.facade.FacadeHelper;
import org.eclipse.emf.codegen.merge.java.facade.JCompilationUnit;
import org.eclipse.emf.codegen.merge.java.facade.JNode;

/**
 * @since 2.2.0
 */
public class ASTFacadeHelper extends FacadeHelper
{
  /**
   * Debug output setting
   */
  protected final static boolean DEBUG = false;
  
  /**
   * Returns the wrapped <code>ASTNode</code> object by given {@link JNode}.
   * 
   * @param node
   * @return wrapped <code>ASTNode</code>, or <code>null</code> if <code>JNode</code> is not instance of <code>ASTJNode</code>
   */
  public static ASTNode getASTNode(JNode node)
  {
    return node instanceof ASTJNode ? ((ASTJNode)node).getASTNode() : null;
  }  
  
  /**
   * Converts {@link Name} to string representation.
   * 
   * @param name
   * @return fully qualified name or <code>null</code> if name parameter is <code>null</code>
   */
  public static String toString(Name name)
  {
    return name == null ? null : name.getFullyQualifiedName();
  }    

  /**
   * Converts {@link Type} to string representation.
   * 
   * @param type
   * @return string representing the type
   */
  public static String toString(Type type)
  {
    if (type != null)
    {
      String string = null;
      String dimensions = "";

      if (type.isArrayType())
      {
        for (int i = ((ArrayType)type).getDimensions(); i > 0; i--)
        {
          dimensions += "[]";
        }
        type = ((ArrayType)type).getElementType();
      }

      if (type.isSimpleType())
      {
        string = ASTFacadeHelper.toString(((SimpleType)type).getName());
      }
      else if (type.isPrimitiveType())
      {
        string = ((PrimitiveType)type).getPrimitiveTypeCode().toString();
      }

      if (dimensions.length() != 0)
      {
        string = string + dimensions;
      }
      return string;
    }
    return null;
  }

  /**
   * Map of options set by default from <code>JavaCore.getOptions()</code>
   */
  protected Map javaCoreOptions = null;
  
  /**
   * Map of nodes to node contents. Used for caching only.
   */
  protected Map<ASTNode, String> nodeContents = new HashMap<ASTNode, String>();

  /**
   * Creates and returns <code>ASTParser</code>.
   * 
   * @return new ASTParser object
   */
  protected ASTParser createASTParser()
  {
    // caching parser does not parse 2nd file in the same way (javadoc of package for example)
    // hence, new parser is created every time this method is called
    ASTParser astParser = ASTParser.newParser(AST.JLS3);
    astParser.setCompilerOptions(getJavaCoreOptions());
    return astParser;
  }
  
  /* (non-Javadoc)
   * @see org.eclipse.emf.codegen.merge.java.facade.FacadeHelper#createCompilationUnit(java.lang.String, java.lang.String)
   */
  @Override
  public JCompilationUnit createCompilationUnit(String name, String content)
  {
    // set source
    char[] contentAsCharArray = content.toCharArray();
    ASTParser astParser = createASTParser();
    astParser.setSource(contentAsCharArray);
    
    // parse
    CompilationUnit astCompilationUnit = (CompilationUnit)astParser.createAST(null);

    // display errors
    if (DEBUG && astCompilationUnit.getProblems().length > 0)
    {
      logCompilationErrors(astCompilationUnit.getProblems());
    }
    
    // create rewriter to record changes
    ASTRewrite rewriter = ASTRewrite.create(astCompilationUnit.getAST());

    // keep comments between nodes when removing or moving nodes
    rewriter.setTargetSourceRangeComputer(new CommentAwareSourceRangeComputer(astCompilationUnit));
    
    // set properties
    astCompilationUnit.setProperty(ASTJCompilationUnit.NAME_PROPERTY, name);
    
    // create JNode and set properties
    ASTJCompilationUnit compilationUnit = (ASTJCompilationUnit)convertToNode(astCompilationUnit);
    compilationUnit.setOriginalContents(contentAsCharArray);
    compilationUnit.setRewriter(rewriter);

    return compilationUnit;
  }
  
  /**
   * Logs all compilation errors
   * <br>Currently for debugging only.
   * 
   * @param problems to display
   */
  private void logCompilationErrors(IProblem[] problems)
  {
    for (int i = 0; i < problems.length; i++)
    {
      logError("Compiler Problem: " + problems[i]);
    }
  }

  /**
   * Accessor for options to be used during parsing and rewriting.
   * <p>
   * If options are <code>null</code>, uses options provided by
   * <code>getDefaultJavaCoreOptions()</code>.
   * 
   * @return map of options
   * 
   * @see #getDefaultJavaCoreOptions()
   */
  public Map getJavaCoreOptions()
  {
    if (javaCoreOptions == null)
    {
      javaCoreOptions = getDefaultJavaCoreOptions();
    }
    return javaCoreOptions;
  }
  
  /**
   * Sets the Java Core Options to use for parsing and rewriting.
   * 
   * @param javaCoreOptions the Java Core options to set
   * 
   * @see ASTRewrite#rewriteAST(org.eclipse.jface.text.IDocument, Map)
   * @see ASTParser#setCompilerOptions(Map)
   */
  public void setJavaCoreOptions(Map javaCoreOptions)
  {
    this.javaCoreOptions = javaCoreOptions;
  }

  /**
   * Gets JavaCore options from JavaCore and updates tab and indentation settings from ControlModel.
   * 
   * @return map of options
   * 
   * @see #getJavaCoreOptions()
   * @see JavaCore.getOptions()
   * @see JControlModel.getLeadingTabReplacement()
   */
  private Map getDefaultJavaCoreOptions()
  {
    Map javaCoreOptions = JavaCore.getOptions();

    // TODO add functionality to set source and compliance options
    //javaCoreOptions.put(JavaCore.COMPILER_SOURCE, "1.5");
    //javaCoreOptions.put(JavaCore.COMPILER_COMPLIANCE, "1.5");
    
    if (getControlModel() != null)
    {
      String indent = getControlModel().getLeadingTabReplacement();
      if (indent != null && indent.length() > 0)
      {
        String size = Integer.toString(indent.length());
        if (indent.charAt(0) == '\t') 
        {
          javaCoreOptions.put(DefaultCodeFormatterConstants.FORMATTER_TAB_CHAR, JavaCore.TAB);
          javaCoreOptions.put(DefaultCodeFormatterConstants.FORMATTER_TAB_SIZE, size);
          javaCoreOptions.put(DefaultCodeFormatterConstants.FORMATTER_INDENTATION_SIZE, size);                
        }
        else if (indent.charAt(0) == ' ')
        {
          javaCoreOptions.put(DefaultCodeFormatterConstants.FORMATTER_TAB_CHAR, JavaCore.SPACE);
          javaCoreOptions.put(DefaultCodeFormatterConstants.FORMATTER_TAB_SIZE, size);
          javaCoreOptions.put(DefaultCodeFormatterConstants.FORMATTER_INDENTATION_SIZE, size);                
        }
        else
        {
          if (DEBUG)
          {
            logInfo("Unable to recognize indent string, using java core options.");
          }
        }
      }
      else
      {
        if (DEBUG)
        {
          logInfo("Indent is not set, using java core options.");
        }
      }
    }
    
    if (DEBUG)
    {
      logInfo("Tab char: " + javaCoreOptions.get(DefaultCodeFormatterConstants.FORMATTER_TAB_CHAR));
      logInfo("Indent size: " + javaCoreOptions.get(DefaultCodeFormatterConstants.FORMATTER_TAB_SIZE));
      logInfo("Tab size: " + javaCoreOptions.get(DefaultCodeFormatterConstants.FORMATTER_INDENTATION_SIZE));
    }
    
    return javaCoreOptions;
  }  
  
  /* (non-Javadoc)
   * @see org.eclipse.emf.codegen.merge.java.facade.FacadeHelper#getContext(org.eclipse.emf.codegen.merge.java.facade.JNode)
   */
  @Override
  public Object getContext(JNode node)
  {
    return node;
  }  
  
  /**
   * Creates a copy of the node to be inserted in the context.
   * <p>
   * Note that in this implementation the original and returned cloned node can not be modified.
   * Calls to <code>get...()</code> methods on the cloned node will not return the original content. 
   * The returned node can only be inserted in the context.
   * 
   * @see org.eclipse.emf.codegen.merge.java.facade.FacadeHelper#cloneNode(java.lang.Object, org.eclipse.emf.codegen.merge.java.facade.JNode)
   */
  @Override
  public JNode cloneNode(Object context, JNode node)
  {
    if (node instanceof JCompilationUnit)
    {
      String content = applyFormatRules(toString(node.getContents()));
      return createCompilationUnit(node.getName(), content);
    }
    else
    {
      ASTNode originalASTNode = getASTNode(node);
      
      if (originalASTNode != null)
      {
        ASTJNode contextNode = (ASTJNode)context;        
        ASTRewrite rewriter = contextNode.getRewriter();        
        ASTNode newASTNode;
        ASTJNode astjNode;
        
        // create new AST node
        // handle variable declaration fragments separately
        if (originalASTNode instanceof VariableDeclarationFragment)
        {
          FieldDeclaration originalFieldDeclaration = (FieldDeclaration)originalASTNode.getParent();
          
          FieldDeclaration newFieldDeclaration = (FieldDeclaration) ASTNode.copySubtree(contextNode.getASTNode().getAST(), originalFieldDeclaration);
          
          // if there are multiple declarations, use only 1 in the resulting node
          // and copy only javadoc as string
          if (originalFieldDeclaration.fragments().size() > 1)
          {
            newFieldDeclaration.fragments().clear();
            newASTNode = ASTNode.copySubtree(contextNode.getASTNode().getAST(), originalASTNode);
            newFieldDeclaration.fragments().add(newASTNode);
            astjNode = (ASTJNode)convertToNode(newASTNode);
            
            // copy javadoc as string
            Javadoc originalJavadoc = originalFieldDeclaration.getJavadoc();
            if (originalJavadoc != null)
            {
              String javadocString = applyFormatRules(toString(originalFieldDeclaration.getJavadoc()));
              Javadoc javadoc = (Javadoc)rewriter.createStringPlaceholder(javadocString, originalFieldDeclaration.getJavadoc().getNodeType());
              newFieldDeclaration.setJavadoc(javadoc);
              contextNode.trackAndReplace(javadoc, javadocString);
            }
          }
          else // else - there is only 1 declaration, replace the whole node by original contents
          {
            newASTNode = (ASTNode)newFieldDeclaration.fragments().get(0);
            astjNode = (ASTJNode)convertToNode(newASTNode);
            contextNode.trackAndReplace(newFieldDeclaration, applyFormatRules(toString(originalFieldDeclaration)));
          }
        }
        else // any other node except variable declaration fragment - just copy content
        {
          String contents = applyFormatRules(toString(originalASTNode));
          newASTNode = rewriter.createStringPlaceholder(contents, originalASTNode.getNodeType());
          astjNode = (ASTJNode)convertToNode(newASTNode);
          contextNode.trackAndReplace(newASTNode, contents);
        }
        
        // set rewriter on the new node
        astjNode.setRewriter(rewriter);
        
        return astjNode;
      }
    }
    return null;
  }

  /* (non-Javadoc)
   * @see org.eclipse.emf.codegen.merge.java.facade.FacadeHelper#doConvertToNode(java.lang.Object)
   */
  @Override
  protected JNode doConvertToNode(Object object)
  {
    ASTJNode node = null;
    boolean isCompilationUnit = false;
    if (object instanceof CompilationUnit)
    {
      node = new ASTJCompilationUnit((CompilationUnit)object);
      isCompilationUnit = true;
    }
    if (object instanceof VariableDeclarationFragment)
    {
      node = new ASTJField((VariableDeclarationFragment)object);
    }
    if (object instanceof ImportDeclaration)
    {
      node = new ASTJImport((ImportDeclaration)object);
    }
    if (object instanceof Initializer)
    {
      node = new ASTJInitializer((Initializer)object);
    }
    if (object instanceof MethodDeclaration)
    {
      node = new ASTJMethod((MethodDeclaration)object);
    }
    if (object instanceof PackageDeclaration)
    {
      node = new ASTJPackage((PackageDeclaration)object);
    }
    if (object instanceof TypeDeclaration)
    {
      node = new ASTJType((TypeDeclaration)object);
    }
    
    if (node != null)
    {
      node.setFacadeHelper(this);
      if (!isCompilationUnit)
      {
        JCompilationUnit jCompilationUnit = getCompilationUnit(node);
        if (jCompilationUnit != null)
        {
          node.setRewriter(((ASTJCompilationUnit)jCompilationUnit).getRewriter());
        }
      }
    }
    return node;
  }
  
  /* (non-Javadoc)
   * @see org.eclipse.emf.codegen.merge.java.facade.FacadeHelper#addChild(org.eclipse.emf.codegen.merge.java.facade.JNode, org.eclipse.emf.codegen.merge.java.facade.JNode)
   */
  @Override
  public boolean addChild(JNode node, JNode child)
  {
    if (node == null)
    {
      return false;
    }
    return ((ASTJNode)node).addChild(child);
  }
  
  /**
   * Removes the given node.
   * <p>
   * Most <code>get...()</code> operations on the removed node will not give the correct results
   * even after insertion of the node.
   * 
   * @see org.eclipse.emf.codegen.merge.java.facade.FacadeHelper#remove(org.eclipse.emf.codegen.merge.java.facade.JNode)
   */
  @Override
  public boolean remove(JNode node)
  {
    if (node != null)
    {
      ASTJNode parent = (ASTJNode)node.getParent();
      if (parent != null)
      {
        // update the wrapped object map
        if (parent.remove(node))
        {
          objectToNodeMap.put(getASTNode(node), node);
        }
        return true;
      }
    }
    return false;
  }
  
  /* (non-Javadoc)
   * @see org.eclipse.emf.codegen.merge.java.facade.FacadeHelper#insertSibling(org.eclipse.emf.codegen.merge.java.facade.JNode, org.eclipse.emf.codegen.merge.java.facade.JNode, boolean)
   */
  @Override
  public boolean insertSibling(JNode node, JNode newSibling, boolean before)
  {
    if (node != null && newSibling != null)
    {
      ASTJNode parent = (ASTJNode)node.getParent();
      if (parent != null)
      {
        return parent.insertSibling(node, newSibling, before);
      }
    }
    return false;
  }  
  
  /* (non-Javadoc)
   * @see org.eclipse.emf.codegen.merge.java.facade.FacadeHelper#toString(java.lang.Object)
   */
  @Override
  public String toString(Object object)
  {
    if (object instanceof JNode)
    {
      object = getASTNode((JNode)object);
    }
    if (object instanceof ASTNode)
    {
      return toString((ASTNode)object);
    }
    else
    {
      return super.toString(object);
    }
  }
  
  /**
   * Gets the original contents of the node.
   * <p> 
   * The contents of the node in AST implementation includes only the node itself without
   * the leading or trailing whitespace.
   * If the node has a Javadoc comment, it is included in the contents. No other leading or trailing
   * comments are included in the node contents.
   * <p>
   * The given node must be one of the nodes of the AST tree created by {@link #createCompilationUnit(String, String)}.
   * <p>
   * Note that contents is cached on the first access. Therefore, returned contents will not reflect any changes to the nodes.
   * 
   * @param node node to get contents for
   * @return original contents of the node
   */
  public String toString(ASTNode node)
  {
    if (node == null)
    {
      return null;
    }
    
    if (nodeContents.containsKey(node))
    {
      return nodeContents.get(node);
    }
    
    ASTJCompilationUnit compilationUnit = (ASTJCompilationUnit)convertToNode(node.getRoot());
    if (compilationUnit != null)
    {
      char[] originalContents = compilationUnit.getOriginalContents();
      //String resultString = new String(originalContents, compilationUnit.getASTCompilationUnit().getExtendedStartPosition(node), compilationUnit.getASTCompilationUnit().getExtendedLength(node));

      int start = node.getStartPosition();
      int length = node.getLength();
      TargetSourceRangeComputer rangeComputer = compilationUnit.getRewriter().getExtendedSourceRangeComputer();
      if (rangeComputer instanceof CommentAwareSourceRangeComputer)
      {
        SourceRange sourceRange = ((CommentAwareSourceRangeComputer)rangeComputer).computeDefaultSourceRange(node);
        length = (sourceRange.getStartPosition() + sourceRange.getLength()) - start;
      }
      String resultString = new String(originalContents, start, length);      
      
      nodeContents.put(node, resultString);
      return resultString;      
    }

    // this is a fallback, however, this should never be called
    return node.toString();
  }
  
  /**
   * Adds a new entry to the log using INFO level.
   * 
   * @param string to add to the log
   */
  public void logInfo(String string)
  {
    CodeGenPlugin.INSTANCE.log("INFO " + string);
  }    
  
  /**
   * Adds a new entry to the log using ERROR level.
   * 
   * @param string to add to the log
   */
  public void logError(String string)
  {
    CodeGenPlugin.INSTANCE.log("ERROR " + string);
  }  
  
  /**
   * Adds a new entry to the log using ERROR level.
   * 
   * @param string to add to the log
   * @param e exception to record
   */
  public void logError(String string, Exception e)
  {
    CodeGenPlugin.INSTANCE.log("ERROR " + string);
    CodeGenPlugin.INSTANCE.log(e);
  }    
}
