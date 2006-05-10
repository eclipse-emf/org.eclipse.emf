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
 * $Id: ASTFacadeHelper.java,v 1.1 2006/05/10 20:33:16 marcelop Exp $
 */
package org.eclipse.emf.codegen.merge.java.facade.ast;

import java.util.Iterator;
import java.util.List;

import org.eclipse.jdt.core.dom.AST;
import org.eclipse.jdt.core.dom.ASTNode;
import org.eclipse.jdt.core.dom.ASTParser;
import org.eclipse.jdt.core.dom.ArrayCreation;
import org.eclipse.jdt.core.dom.ArrayType;
import org.eclipse.jdt.core.dom.CompilationUnit;
import org.eclipse.jdt.core.dom.Expression;
import org.eclipse.jdt.core.dom.FieldDeclaration;
import org.eclipse.jdt.core.dom.ImportDeclaration;
import org.eclipse.jdt.core.dom.InfixExpression;
import org.eclipse.jdt.core.dom.Initializer;
import org.eclipse.jdt.core.dom.Javadoc;
import org.eclipse.jdt.core.dom.MethodDeclaration;
import org.eclipse.jdt.core.dom.Name;
import org.eclipse.jdt.core.dom.NumberLiteral;
import org.eclipse.jdt.core.dom.PackageDeclaration;
import org.eclipse.jdt.core.dom.PrimitiveType;
import org.eclipse.jdt.core.dom.SimpleType;
import org.eclipse.jdt.core.dom.StringLiteral;
import org.eclipse.jdt.core.dom.TagElement;
import org.eclipse.jdt.core.dom.TextElement;
import org.eclipse.jdt.core.dom.Type;
import org.eclipse.jdt.core.dom.TypeDeclaration;

import org.eclipse.emf.codegen.merge.java.facade.FacadeHelper;
import org.eclipse.emf.codegen.merge.java.facade.JCompilationUnit;
import org.eclipse.emf.codegen.merge.java.facade.JNode;

/**
 * @since 2.2.0
 */
public class ASTFacadeHelper extends FacadeHelper
{
  public static ASTNode getASTNode(JNode node)
  {
    return node instanceof ASTJNode ? ((ASTJNode)node).getASTNode() : null;
  }
  
  public static String toString(Name name)
  {
    return name == null ? null : name.getFullyQualifiedName();
  }    
  
  public static String toString(Javadoc javadoc)
  {
    if (javadoc != null)
    {
      StringBuffer sb = new StringBuffer();
      
      String nl = System.getProperties().getProperty("line.separator");
      List tags = javadoc.tags();
      for (Iterator i = tags.iterator(); i.hasNext();)
      {
        TagElement tagElement = (TagElement)i.next();
        for (Iterator j = tagElement.fragments().iterator(); j.hasNext();)
        {
          TextElement element = (TextElement)j.next();
          sb.append(nl).append(element.getText());
        }      
      }
      sb.delete(0, nl.length());
      
      return sb.toString();
    }
    else
    {
      return null;
    }
  }    

  public static String toString(Type type)
  {
    if (type != null)
    {
      String string = null;
      String dimensions = "";
      
      if (type.isArrayType())
      {
        for (int i=((ArrayType)type).getDimensions(); i>0; i--)
        {
          dimensions += "[]";
        }
        while (type.isArrayType())
        {
          type = ((ArrayType)type).getComponentType();
        }
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
  
  public static String toString(Expression expression)
  {
    if (expression instanceof ArrayCreation)
    {
      ArrayCreation arrayCreation = (ArrayCreation)expression;
      arrayCreation.toString();
      String initializer = "new " + ASTFacadeHelper.toString(arrayCreation.getType());
      for (Iterator i = arrayCreation.dimensions().iterator(); i.hasNext();)
      {
        NumberLiteral numberLiteral = (NumberLiteral)i.next();
        initializer = initializer.replaceFirst("\\[\\]", "[" + numberLiteral.getToken() + "]");
      }
      return initializer;
    }
    
    if (expression instanceof NumberLiteral)
    {
      return ((NumberLiteral)expression).getToken();
    }
    else if (expression instanceof StringLiteral)
    {
      return ((StringLiteral)expression).getEscapedValue();
    }      
    else if (expression instanceof InfixExpression)
    {
      InfixExpression infixExpression = (InfixExpression)expression;
      StringBuffer sb = new StringBuffer();
      sb.append(toString(infixExpression.getLeftOperand()));
      sb.append(infixExpression.getOperator());
      sb.append(toString(infixExpression.getRightOperand()));
      return sb.toString();
    }
    return null;
  }

  protected ASTParser astParser;

  public void reset()
  {
    astParser = null;
    super.reset();
  }
  
  protected ASTParser getASTParser()
  {
    if (astParser == null)
    {
      astParser = ASTParser.newParser(AST.JLS3);
    }
    return astParser;
  }  
  
  public JCompilationUnit createCompilationUnit(String name, String content)
  {
    char[] contentAsCharArray = content.toCharArray();
    getASTParser().setSource(contentAsCharArray);
    CompilationUnit astCompilationUnit = (CompilationUnit)astParser.createAST(null);
    astCompilationUnit.setProperty(ASTJCompilationUnit.PROPERY_NAME, name);
    JCompilationUnit compilationUnit = (JCompilationUnit)convertToNode(astCompilationUnit);
    ((ASTJCompilationUnit)compilationUnit).setOriginalContents(contentAsCharArray);
    return (JCompilationUnit)compilationUnit;
  }
  
  public Object getContext(JNode node)
  {
    return getASTNode(node).getAST();
  }  

  public JNode cloneNode(Object context, JNode node)
  {
    ASTNode astNode = null;
    if (node instanceof JCompilationUnit)
    {
      String content = applyFormatRules(node.getContents());
      return createCompilationUnit(node.getName(), content);
    }
    else
    {
      ASTNode originalASTNode = getASTNode(node);
      if (originalASTNode != null)
      {
        astNode = ASTNode.copySubtree((AST)context, originalASTNode);
      }
    }
    
    return astNode != null ? convertToNode(astNode) : null;
  }

  protected JNode doConvertToNode(Object object)
  {
    ASTJNode node = null;
    if (object instanceof CompilationUnit)
    {
      node = new ASTJCompilationUnit((CompilationUnit)object);
    }
    if (object instanceof FieldDeclaration)
    {
      node = new ASTJField((FieldDeclaration)object);
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
    }
    return node;
  }
  
  public boolean remove(JNode node)
  {
    ASTNode astNode = getASTNode(node);
    ASTNode parent = astNode.getParent();
    if (parent != null)
    {
      astNode.delete();
      return true;
    }
    return false;
  }  
}
