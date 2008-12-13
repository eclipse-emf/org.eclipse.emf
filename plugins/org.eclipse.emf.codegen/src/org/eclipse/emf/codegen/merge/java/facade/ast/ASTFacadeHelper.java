/**
 * <copyright>
 *
 * Copyright (c) 2006-2007 IBM Corporation and others.
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
 * $Id: ASTFacadeHelper.java,v 1.15 2008/12/13 15:50:44 emerks Exp $
 */
package org.eclipse.emf.codegen.merge.java.facade.ast;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.eclipse.jdt.core.JavaCore;
import org.eclipse.jdt.core.compiler.IProblem;
import org.eclipse.jdt.core.dom.AST;
import org.eclipse.jdt.core.dom.ASTNode;
import org.eclipse.jdt.core.dom.ASTParser;
import org.eclipse.jdt.core.dom.Annotation;
import org.eclipse.jdt.core.dom.AnnotationTypeDeclaration;
import org.eclipse.jdt.core.dom.AnnotationTypeMemberDeclaration;
import org.eclipse.jdt.core.dom.ArrayType;
import org.eclipse.jdt.core.dom.ChildListPropertyDescriptor;
import org.eclipse.jdt.core.dom.CompilationUnit;
import org.eclipse.jdt.core.dom.EnumConstantDeclaration;
import org.eclipse.jdt.core.dom.EnumDeclaration;
import org.eclipse.jdt.core.dom.FieldDeclaration;
import org.eclipse.jdt.core.dom.ImportDeclaration;
import org.eclipse.jdt.core.dom.Initializer;
import org.eclipse.jdt.core.dom.MethodDeclaration;
import org.eclipse.jdt.core.dom.Name;
import org.eclipse.jdt.core.dom.PackageDeclaration;
import org.eclipse.jdt.core.dom.ParameterizedType;
import org.eclipse.jdt.core.dom.PrimitiveType;
import org.eclipse.jdt.core.dom.QualifiedType;
import org.eclipse.jdt.core.dom.SimpleType;
import org.eclipse.jdt.core.dom.Type;
import org.eclipse.jdt.core.dom.TypeDeclaration;
import org.eclipse.jdt.core.dom.VariableDeclarationFragment;
import org.eclipse.jdt.core.dom.rewrite.ASTRewrite;
import org.eclipse.jdt.core.dom.rewrite.ListRewrite;
import org.eclipse.jdt.core.dom.rewrite.TargetSourceRangeComputer;
import org.eclipse.jdt.core.dom.rewrite.TargetSourceRangeComputer.SourceRange;
import org.eclipse.jdt.core.formatter.DefaultCodeFormatterConstants;

import org.eclipse.emf.codegen.CodeGenPlugin;
import org.eclipse.emf.codegen.merge.java.JControlModel;
import org.eclipse.emf.codegen.merge.java.JMerger;
import org.eclipse.emf.codegen.merge.java.facade.FacadeHelper;
import org.eclipse.emf.codegen.merge.java.facade.JCompilationUnit;
import org.eclipse.emf.codegen.merge.java.facade.JNode;
import org.eclipse.emf.common.util.BasicDiagnostic;
import org.eclipse.emf.common.util.Diagnostic;
import org.eclipse.emf.common.util.DiagnosticException;
import org.eclipse.emf.common.util.WrappedException;


/**
 * @since 2.2.0
 */
public class ASTFacadeHelper extends FacadeHelper
{
  public static class ASTRewriteWithRemove extends ASTRewrite
  {
    protected ASTRewriteWithRemove(AST ast)
    {
      super(ast);
    }
    
    /**
     * Disposes this ASTRewriteWithRemove
     */
    @SuppressWarnings("restriction")
    public void dispose()
    {
      getRewriteEventStore().clear();
      getNodeStore().clear();
      setTargetSourceRangeComputer(null);
    }
    
    /**
     * Workaround method that removes nodes similar to {@link #remove(ASTNode, org.eclipse.text.edits.TextEditGroup)}, but
     * it allows removal of newly created and inserted nodes that were not a part of original tree.
     * <p>
     * Note that {@link #remove(ASTNode, org.eclipse.text.edits.TextEditGroup)} does not remove newly created nodes that
     * have been inserted with {@link ListRewrite#insertFirst(ASTNode, org.eclipse.text.edits.TextEditGroup)} or
     * similar methods.
     * <p>
     * Workaround for <a href="https://bugs.eclipse.org/bugs/show_bug.cgi?id=164862">https://bugs.eclipse.org/bugs/show_bug.cgi?id=164862</a>
     * 
     * @param parent
     * @param childProperty
     * @param node
     */
    @SuppressWarnings("restriction")
    public void remove(ASTNode parent, ChildListPropertyDescriptor childProperty, ASTNode node)
    {
      ListRewrite lrw = getListRewrite(parent, childProperty);
      
      if (lrw.getRewrittenList().contains(node) && !lrw.getOriginalList().contains(node))
      {
        org.eclipse.jdt.internal.core.dom.rewrite.ListRewriteEvent listEvent = super.getRewriteEventStore().getListEvent(parent, childProperty, true);
        int index = listEvent.getIndex(node, org.eclipse.jdt.internal.core.dom.rewrite.ListRewriteEvent.NEW);
        if (index >= 0)
        {
          listEvent.revertChange((org.eclipse.jdt.internal.core.dom.rewrite.NodeRewriteEvent)listEvent.getChildren()[index]);
        }
        else
        {
          lrw.remove(node, null);
        }
      }
      else
      {
        lrw.remove(node, null);
      }
    }
  }
  
  
  /**
   * Debug output setting
   */
  protected final static boolean DEBUG = JMerger.DEBUG;

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

  public static String getTypeErasure(ArrayType arrayType)
  {
    StringBuilder sb = new StringBuilder(ASTFacadeHelper.getTypeErasure(arrayType.getElementType()));
    for (int i = arrayType.getDimensions(); i > 0; i--)
    {
      sb.append("[]");
    }
    return sb.toString();
  }

  public static String getTypeErasure(ParameterizedType parameterizedType)
  {
    return getTypeErasure(parameterizedType.getType());
  }

  public static String getTypeErasure(PrimitiveType primitiveType)
  {
    return primitiveType.getPrimitiveTypeCode().toString();
  }

  public static String getTypeErasure(SimpleType simpleType)
  {
    return ASTFacadeHelper.toString(simpleType.getName());
  }

  public static String getTypeErasure(QualifiedType qualifiedType)
  {
    StringBuilder sb = new StringBuilder(ASTFacadeHelper.getTypeErasure(qualifiedType.getQualifier()));
    sb.append(".");
    sb.append(ASTFacadeHelper.toString(qualifiedType.getName()));
    return sb.toString();
  }

  /**
   * Converts {@link Type} to string representation, erasing type parameters information.
   * <p>
   * This method is used to create a method signature, and match methods by signature.
   * 
   * @param type
   * @return string representing the type
   */
  public static String getTypeErasure(Type type)
  {
    if (type != null)
    {
      if (type.isArrayType())
      {
        return getTypeErasure((ArrayType)type);
      }
      else if (type.isParameterizedType())
      {
        return getTypeErasure((ParameterizedType)type);
      }
      else if (type.isPrimitiveType())
      {
        return getTypeErasure((PrimitiveType)type);
      }
      else if (type.isQualifiedType())
      {
        return getTypeErasure((QualifiedType)type);
      }
      else if (type.isSimpleType())
      {
        return getTypeErasure((SimpleType)type);
      }
      else if (type.isWildcardType())
      {
        return "";
      }
    }
    return "";
  }

  protected ASTNodeConverter nodeConverter = null;
  
  /**
   * Map of options set by default from <code>JavaCore.getOptions()</code>
   */
  @SuppressWarnings("unchecked")
  protected Map javaCoreOptions = null;

  /**
   * Map of nodes to node contents. Used for caching only.
   */
  protected Map<ASTNode, String> nodeContents = new HashMap<ASTNode, String>();

  @Override
  public void reset()
  {
    nodeConverter = null;
    nodeContents.clear();
    super.reset();
  }
  
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
  public ASTJCompilationUnit createCompilationUnit(String name, String contents)
  {
    // set source
    char[] contentAsCharArray = contents.toCharArray();
    ASTParser astParser = createASTParser();
    astParser.setSource(contentAsCharArray);

    // parse
    CompilationUnit astCompilationUnit = (CompilationUnit)astParser.createAST(null);

    Diagnostic diagnostic = analyzeCompilationUnit(astCompilationUnit, contents);
    if (diagnostic != Diagnostic.OK_INSTANCE)
    { 
      StringBuilder message = new StringBuilder(diagnostic.getMessage());
      for (Diagnostic childDiagnostic : diagnostic.getChildren())
      {
        message.append("\n\t").append(childDiagnostic.getMessage());
      }
      message.append(contents);
      CodeGenPlugin.INSTANCE.log(message.toString());
      
      if (diagnostic.getSeverity() == Diagnostic.ERROR)
      {
        throw new WrappedException(new DiagnosticException(diagnostic));
      }
    }

    // create rewriter to record changes
    ASTRewrite rewriter = new ASTRewriteWithRemove(astCompilationUnit.getAST());

    // keep comments between nodes when removing or moving nodes
    rewriter.setTargetSourceRangeComputer(new CommentAwareSourceRangeComputer(astCompilationUnit, contents));

    // set properties
    astCompilationUnit.setProperty(ASTJCompilationUnit.NAME_PROPERTY, name);

    // create JNode and set properties
    ASTJCompilationUnit compilationUnit = (ASTJCompilationUnit)convertToNode(astCompilationUnit);
    compilationUnit.setOriginalContents(contentAsCharArray);
    compilationUnit.setRewriter(rewriter);

    return compilationUnit;
  }
  
  @Override
  public String getOriginalContents(JCompilationUnit compilationUnit)
  {
    return new String(((ASTJCompilationUnit)compilationUnit).getOriginalContents());
  }

  private Diagnostic analyzeCompilationUnit(CompilationUnit compilationUnit, String contents)
  {
    if (compilationUnit.getProblems().length == 0)
    {
      return Diagnostic.OK_INSTANCE;
    }
    else
    {
      BasicDiagnostic diagnostic = new BasicDiagnostic(
        CodeGenPlugin.ID,
        0,
        CodeGenPlugin.INSTANCE.getString("_UI_ParsingProblem_message"),
        contents == null ? null : new Object[] {new StringBuilder(contents)}
      );
      
      for (IProblem problem : compilationUnit.getProblems())
      {
        String message = problem.getMessage() != null ?
          CodeGenPlugin.INSTANCE.getString("_UI_LineNumberAndText_message", new Object[] {problem.getSourceLineNumber(), problem.getMessage()}) :
          CodeGenPlugin.INSTANCE.getString("_UI_LineNumber_message", new Object[] {problem.getSourceLineNumber()});
        
        BasicDiagnostic childDiagnostic = new BasicDiagnostic(
          problem.isWarning() ? Diagnostic.WARNING : Diagnostic.ERROR,
          CodeGenPlugin.ID,
          0,
          message.toString(),
          contents == null ? new Object[]{problem} : new Object[]{problem, new StringBuilder(contents)}
        );
        diagnostic.add(childDiagnostic);
      }
      
      return diagnostic;
    }
  }

  /**
   * Accessor for options to be used during parsing and rewriting.
   * <p>
   * If options are <code>null</code>, uses options provided by
   * <code>getDefaultJavaCoreOptions()</code>.
   * 
   * @return map of options
   * @see #getDefaultJavaCoreOptions()
   */
  @SuppressWarnings("unchecked")
  public Map getJavaCoreOptions()
  {
    if (javaCoreOptions == null)
    {
      javaCoreOptions = getDefaultJavaCoreOptions();
    }
    return javaCoreOptions;
  }

  /**
   * Gets JavaCore options from JavaCore and updates tab and indentation settings from ControlModel.
   * 
   * @return map of options
   * 
   * @see #getJavaCoreOptions()
   * @see JavaCore#getOptions()
   * @see JControlModel#getLeadingTabReplacement()
   */
  @SuppressWarnings("unchecked")
  private Map getDefaultJavaCoreOptions()
  {
    Map javaCoreOptions = JavaCore.getDefaultOptions();

    // Set of options that we want to copy from the current definition        
    useCurrentOption(javaCoreOptions, "org.eclipse.jdt.core.compiler.compliance");
    useCurrentOption(javaCoreOptions, "org.eclipse.jdt.core.compiler.source");
    useCurrentOption(javaCoreOptions, "org.eclipse.jdt.core.compiler.codegen.targetPlatform");
    useCurrentOption(javaCoreOptions, DefaultCodeFormatterConstants.FORMATTER_TAB_CHAR);
    useCurrentOption(javaCoreOptions, DefaultCodeFormatterConstants.FORMATTER_TAB_SIZE);
    useCurrentOption(javaCoreOptions, DefaultCodeFormatterConstants.FORMATTER_INDENTATION_SIZE);
    
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
      logInfo("Tab char: " + javaCoreOptions.get(DefaultCodeFormatterConstants.FORMATTER_TAB_CHAR) + ", Indent size: "
        + javaCoreOptions.get(DefaultCodeFormatterConstants.FORMATTER_TAB_SIZE) + ", Tab size: "
        + javaCoreOptions.get(DefaultCodeFormatterConstants.FORMATTER_INDENTATION_SIZE));
    }

    // Set of options that we want to control
    javaCoreOptions.put("org.eclipse.jdt.core.incompleteClasspath", "warning");
    javaCoreOptions.put("org.eclipse.jdt.core.circularClasspath", "warning");
    //
    javaCoreOptions.put(DefaultCodeFormatterConstants.FORMATTER_BRACE_POSITION_FOR_ENUM_DECLARATION, DefaultCodeFormatterConstants.NEXT_LINE);
    javaCoreOptions.put(DefaultCodeFormatterConstants.FORMATTER_BRACE_POSITION_FOR_TYPE_DECLARATION, DefaultCodeFormatterConstants.NEXT_LINE);
    
    // separate fields with an empty line
    javaCoreOptions.put(DefaultCodeFormatterConstants.FORMATTER_BLANK_LINES_BEFORE_FIELD, "1");
    
    // make all enum constants to be on separate lines
    javaCoreOptions.put(DefaultCodeFormatterConstants.FORMATTER_ALIGNMENT_FOR_ENUM_CONSTANTS, DefaultCodeFormatterConstants.createAlignmentValue(true, DefaultCodeFormatterConstants.WRAP_ONE_PER_LINE, DefaultCodeFormatterConstants.INDENT_DEFAULT));
    
    return javaCoreOptions;
  }
  
  
  protected void useCurrentOption(Map<Object, String> options, String option)
  {
    options.put(option, JavaCore.getOption(option));
  }
  
  @Override
  public ASTNodeConverter getNodeConverter()
  {
    if (nodeConverter == null)
    {
      nodeConverter = new ASTNodeConverter(this);
    }
    return nodeConverter;
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
   * Creates a copy of the node to be inserted in the tree that context node belongs to.
   * <p>
   * Note that in this implementation the original and returned cloned node can not be modified.
   * Calls to <code>get...()</code> methods on the cloned node will not return the original content. 
   * The returned node can only be inserted in the same tree that context node belongs to.
   * 
   * @param context ASTJNode that belongs to the same tree that cloned node will be inserted to
   * @param node node that needs to be cloned
   * @return new node
   * 
   * @see org.eclipse.emf.codegen.merge.java.facade.FacadeHelper#cloneNode(java.lang.Object, org.eclipse.emf.codegen.merge.java.facade.JNode)
   */
  @Override
  public ASTJNode<?> cloneNode(Object context, JNode node)
  {
    if (node instanceof JCompilationUnit)
    {
      String content = applyFormatRules(toString(node.getContents()));
      return createCompilationUnit(node.getName(), content);
    }
    else
    {
      ASTJNode<?> newASTJNode = null;
      ASTNode originalASTNode = ((ASTJNode<?>)node).getASTNode();

      ASTJNode<?> contextNode = (ASTJNode<?>)context;
      ASTRewrite rewriter = contextNode.getRewriter();

      // handle field declarations separately
      if (node instanceof ASTJField)
      {
        newASTJNode = cloneField((ASTJField)node, contextNode);
      }
      else
      // create new node and replace it all by original contents
      {
        String contents = applyFormatRules(node.getContents());
        // note that string place holder adjusts indentation
        // to correct this trackAndReplace method is used below
        ASTNode newASTNode = rewriter.createStringPlaceholder(contents, originalASTNode.getNodeType());
        newASTJNode = (ASTJNode<?>)convertToNode(newASTNode);
        newASTJNode.trackAndReplace(newASTNode, contents);
      }

      // set rewriter on the new node
      newASTJNode.setRewriter(contextNode.getRewriter());

      return newASTJNode;
    }
  }

  /**
   * Copies the ASTJField node.
   * <p>
   * The copied field should <b>not</b> be modified (using set methods) nor read (using get methods), 
   * and can <b>only</b> be inserted into the same tree that context node belongs to.
   * <p>
   * If the source field has only 1 variable, returned field is replaced by contents of original field declaration. 
   * The returned field will have no internal structure.
   * <p>
   * If the source field has multiple variables declared in it, the returned field will
   * contain only 1 variable. Annotations, javadoc and initializer are replaced by original contents
   * with formatting preserved. Other parts of the declaration is not guaranteed to have formatting preserved.
   * The copied field will have source ranges for all nodes relative to the source file, hence, get methods might return
   * incorrect contents.
   * 
   * @param originalField
   * @param contextNode
   */
  protected ASTJField cloneField(ASTJField originalField, ASTJNode<?> contextNode)
  {
    ASTJField newField = null;
    FieldDeclaration originalFieldDeclaration = originalField.getOriginalFieldDeclaration();

    // if there are multiple variables in the same field declaration, create declaration with only 1 variable
    if (originalFieldDeclaration.fragments().size() > 1)
    {
      AST ast = contextNode.getWrappedObject().getAST();
      
      // note that the copied tree should not be modified by wrapped ASTJField
      //
      // the copied tree will have source ranges for all nodes in the source file,
      // hence, the get methods in the new ASTJField will not return the right contents
      FieldDeclaration newFieldDeclaration = 
        (FieldDeclaration)ASTNode.copySubtree(ast, originalFieldDeclaration);

      @SuppressWarnings("unchecked")
      List<Object> newFragmentsList = newFieldDeclaration.fragments();
      newFragmentsList.clear();
      ASTNode newASTNode = ASTNode.copySubtree(ast, originalField.getWrappedVariableDeclarationFragment());
      newFragmentsList.add(newASTNode);

      newField = (ASTJField)convertToNode(newASTNode);

      // rewriter is required for set methods
      newField.setRewriter(contextNode.getRewriter());

      // set comment and initializer as strings
      newField.setComment(originalField.getComment());
      newField.setInitializer(originalField.getInitializer());

      // set annotation contents
      Iterator<JNode> originalChildrenIterator = originalField.getChildren().iterator();
      Iterator<JNode> childrenIterator = newField.getChildren().iterator();
      for (; childrenIterator.hasNext() && originalChildrenIterator.hasNext();)
      {
        ASTJAnnotation originalAnnotation = (ASTJAnnotation)originalChildrenIterator.next();
        ASTJAnnotation annotation = (ASTJAnnotation)childrenIterator.next();
        annotation.setContents(applyFormatRules(originalAnnotation.getContents()));
      }
    }
    else
    // create new field and replace it all by original contents
    {
      String contents = applyFormatRules(originalField.getContents());
      FieldDeclaration fieldDeclaration = (FieldDeclaration)contextNode.getRewriter().createStringPlaceholder(
        contents,
        ASTNode.FIELD_DECLARATION);
      newField = (ASTJField)convertToNode(fieldDeclaration.fragments().get(0));
      newField.trackAndReplace(fieldDeclaration, contents);
    }
    return newField;
  }

  /**
   * Finds the parent node based on the parent of wrapped AST node.
   * 
   * @return parent {@link JNode}
   */
  public ASTJNode<?> findParent(ASTNode node)
  {
    JNode parent = null;
    // skip nodes in hierarchy that can not be converted to JNode (i.e. VariableDeclarationFragment)
    do
    {
      node = node.getParent();
      parent = convertToNode(node);
    }
    while (parent == null && node != null);
    return (ASTJNode<?>)parent;
  }

  /* (non-Javadoc)
   * @see org.eclipse.emf.codegen.merge.java.facade.FacadeHelper#doConvertToNode(java.lang.Object)
   */
  @Override
  protected ASTJNode<?> doConvertToNode(Object object)
  {
    ASTJNode<?> node = null;
    
    if (object instanceof ASTNode)
    {
      ASTNode astNode = (ASTNode)object;
      
      // get rewriter
      ASTRewrite rewriter = null;
      ASTJCompilationUnit compilationUnit = (ASTJCompilationUnit)getCompilationUnit(findParent((ASTNode)object));
      if (compilationUnit != null)
      {
        rewriter = compilationUnit.getRewriter();
      }
  
      boolean isCompilationUnit = false;
  
      switch (astNode.getNodeType())
      {
        case ASTNode.COMPILATION_UNIT:
          node = new ASTJCompilationUnit((CompilationUnit)object);
          isCompilationUnit = true;
          break;
        case ASTNode.VARIABLE_DECLARATION_FRAGMENT:
          node = new ASTJField((VariableDeclarationFragment)object, this, rewriter);
          break;
        case ASTNode.IMPORT_DECLARATION:
          node = new ASTJImport((ImportDeclaration)object);
          break;
        case ASTNode.INITIALIZER:
          node = new ASTJInitializer((Initializer)object);
          break;
        case ASTNode.METHOD_DECLARATION:
          node = new ASTJMethod((MethodDeclaration)object);
          break;
        case ASTNode.PACKAGE_DECLARATION:
          node = new ASTJPackage((PackageDeclaration)object);
          break;
        case ASTNode.TYPE_DECLARATION:
          node = new ASTJType((TypeDeclaration)object);
          break;
        case ASTNode.MARKER_ANNOTATION:
        case ASTNode.NORMAL_ANNOTATION:
        case ASTNode.SINGLE_MEMBER_ANNOTATION:
          node = new ASTJAnnotation((Annotation)object);
          break;
        case ASTNode.ANNOTATION_TYPE_DECLARATION:
          node = new ASTJAnnotationType((AnnotationTypeDeclaration)object);
          break;
        case ASTNode.ANNOTATION_TYPE_MEMBER_DECLARATION:
          node = new ASTJAnnotationTypeMember((AnnotationTypeMemberDeclaration)object);
          break;
        case ASTNode.ENUM_DECLARATION:
          node = new ASTJEnum((EnumDeclaration)object);
          break;
        case ASTNode.ENUM_CONSTANT_DECLARATION:
          node = new ASTJEnumConstant((EnumConstantDeclaration)object);
      }
      
      if (node != null)
      {
        node.setFacadeHelper(this);
        if (!isCompilationUnit)
        {
          node.setRewriter(rewriter);
        }
      }
    }
    return node;
  }
  
  /**
   * Updates wrapped objects to nodes map. This method must be called
   * when wrapped ASTNode of ASTJNode is changed.
   * 
   * @param node
   */
  public void updateObjectToNodeMap(ASTJNode<?> node)
  {
    objectToNodeMap.put(
      node instanceof ASTJField ? 
        ((ASTJField)node).getWrappedVariableDeclarationFragment() :
        node.getWrappedObject(),
      node);
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
    return ((ASTJNode<?>)node).addChild((ASTJNode<?>)child);
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
      ASTJNode<?> astjNode = (ASTJNode<?>)node;
      ASTJNode<?> parent = astjNode.getParent();
      if (parent != null)
      {
        // update the wrapped object map
        if (parent.remove(astjNode))
        {
          updateObjectToNodeMap(astjNode);
        }
        return true;
      }
    }
    return false;
  }
  
  @Override
  public void commentOut(JNode node)
  {
    ASTJNode<?> astjNode = (ASTJNode<?>)node;
    astjNode.commentOut();
  }

  /* (non-Javadoc)
   * @see org.eclipse.emf.codegen.merge.java.facade.FacadeHelper#insertSibling(org.eclipse.emf.codegen.merge.java.facade.JNode, org.eclipse.emf.codegen.merge.java.facade.JNode, boolean)
   */
  @Override
  public boolean insertSibling(JNode node, JNode newSibling, boolean before)
  {
    if (node != null && newSibling != null)
    {
      ASTJNode<?> astjNode = (ASTJNode<?>)node;
      ASTJNode<?> parent = astjNode.getParent();
      if (parent != null)
      {
        return parent.insertSibling(astjNode, (ASTJNode<?>)newSibling, before);
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
    if (object instanceof ASTJNode<?>)
    {
      return toString(((ASTJNode<?>)object).getASTNode());
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
    
    JNode root = convertToNode(node.getRoot());
    if (root == null || !(root instanceof ASTJCompilationUnit))
    {
      root = getCompilationUnit(convertToNode(node));
    }

    ASTJCompilationUnit compilationUnit = (ASTJCompilationUnit)root;
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
      String resultString = null;
      
      if (start >= 0 && start + length <= originalContents.length)
      {
        resultString = new String(originalContents, start, length);
      }
      
      nodeContents.put(node, resultString);
      return resultString;      
    }

    // this is a fall-back, however, this should never be called
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
