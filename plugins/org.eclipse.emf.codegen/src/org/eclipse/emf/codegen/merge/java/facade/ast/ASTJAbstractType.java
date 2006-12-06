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
 * $Id: ASTJAbstractType.java,v 1.1 2006/12/06 03:48:44 marcelop Exp $
 */
package org.eclipse.emf.codegen.merge.java.facade.ast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.eclipse.jdt.core.dom.ASTNode;
import org.eclipse.jdt.core.dom.AbstractTypeDeclaration;
import org.eclipse.jdt.core.dom.BodyDeclaration;
import org.eclipse.jdt.core.dom.ChildListPropertyDescriptor;
import org.eclipse.jdt.core.dom.FieldDeclaration;
import org.eclipse.jdt.core.dom.rewrite.ListRewrite;

import org.eclipse.emf.codegen.merge.java.facade.JAbstractType;
import org.eclipse.emf.codegen.merge.java.facade.JNode;


/**
 *
 * @param <T> wrapped AST AbstractType
 */
public abstract class ASTJAbstractType<T extends AbstractTypeDeclaration> extends ASTJMember<T> implements JAbstractType
{
  protected ASTJAbstractType(T abstractTypeDeclaration)
  {
    super(abstractTypeDeclaration);
  }

  /* (non-Javadoc)
   * @see org.eclipse.emf.codegen.merge.java.facade.JNode#getName()
   */
  public String getName()
  {
    return ASTFacadeHelper.toString(getASTNode().getName());
  }
  
  /**
   * In this implementation, new name will not be returned by {@link #getName()}.
   * 
   * @see org.eclipse.emf.codegen.merge.java.facade.JNode#setName(java.lang.String)
   * @see org.eclipse.emf.codegen.merge.java.facade.JNode#getQualifiedName()
   */
  public void setName(String name)
  {
    setNodeProperty(getASTNode(), name, getASTNode().getNameProperty(), ASTNode.SIMPLE_NAME);
  }

  /* (non-Javadoc)
   * @see org.eclipse.emf.codegen.merge.java.facade.AbstractJNode#computeQualifiedName()
   */
  @Override
  protected String computeQualifiedName()
  {
    return computeQualifiedName(this);
  }

  /**
   * Returns the list of children.
   * 
   * @see ASTFacadeHelper#convertToNode(Object)
   * @see org.eclipse.emf.codegen.merge.java.facade.AbstractJNode#getChildren()
   */
  @Override
  public List<JNode> getChildren()
  {
    List<JNode> children = new ArrayList<JNode>();
    children.addAll(getAnnotationList());
    children.addAll(getMembers());
    return children.isEmpty() ? Collections.<JNode> emptyList() : Collections.unmodifiableList(children);
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
    
    if (child instanceof ASTJMember)
    {
      insertLast(child, getASTNode().getBodyDeclarationsProperty());
      return true;
    }

    return super.addChild(child);
  }

  /* (non-Javadoc)
   * @see org.eclipse.emf.codegen.merge.java.facade.ast.ASTJNode#insertSibling(org.eclipse.emf.codegen.merge.java.facade.JNode, org.eclipse.emf.codegen.merge.java.facade.JNode, boolean)
   */
  @Override
  public boolean insertSibling(ASTJNode<?> node, ASTJNode<?> newSibling, boolean before)
  {
    if (newSibling.getParent() != null || node.getParent() != this)
    {
      return false;
    }    
    
    if (newSibling instanceof ASTJMember)
    {
      ChildListPropertyDescriptor property = getASTNode().getBodyDeclarationsProperty();
      if (node instanceof ASTJMember)
      {
        insert(newSibling, property, node, before);
      }
      else if (node instanceof ASTJAnnotation)
      {
        insertFirst(newSibling, property);
      }
      else
      {
        insertLast(newSibling, property);
      }
      return true;
    }

    return super.insertSibling(node, newSibling, before);
  }

  /**
   * Removes the node. Node must be a child of ASTJType.
   * <p>
   * This implementation supports moving the nodes by calling {@link #remove(JNode)} and 
   * then {@link #insertSibling(JNode, JNode, boolean)} or {@link #addChild(JNode)}.
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
    
    if (node instanceof ASTJMember)
    {
      remove(node, getASTNode().getBodyDeclarationsProperty());
      return true;
    }

    return super.remove(node);
  }

  protected List<JNode> getMembers()
  {
    List<JNode> members = new ArrayList<JNode>();    
    ListRewrite listRewrite = rewriter.getListRewrite(getASTNode(), getASTNode().getBodyDeclarationsProperty());
    
    @SuppressWarnings("unchecked")
    List<BodyDeclaration> bodyDeclarations = listRewrite.getRewrittenList();
    
    for (BodyDeclaration declaration : bodyDeclarations)
    {
      // for field declarations use variable declaration fragments instead
      if (declaration instanceof FieldDeclaration)
      {
        FieldDeclaration fieldDeclaration = (FieldDeclaration)declaration;
        ListRewrite fragmentslistRewrite = rewriter.getListRewrite(fieldDeclaration, FieldDeclaration.FRAGMENTS_PROPERTY);
        List<?> fragments = fragmentslistRewrite.getRewrittenList();
        for (Object fragment : fragments)
        {
          JNode node = getFacadeHelper().convertToNode(fragment);
          if (node != null)
          {
            members.add(node);
          }
        }
      }
      else
      {
        JNode node = getFacadeHelper().convertToNode(declaration);
        if (node != null)
        {
          members.add(node);
        }
      }
    }
    return members;
  }
}
