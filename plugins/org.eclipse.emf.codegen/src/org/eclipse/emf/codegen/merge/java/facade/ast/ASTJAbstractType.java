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
 * $Id: ASTJAbstractType.java,v 1.6 2008/12/13 15:50:44 emerks Exp $
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

  public String getName()
  {
    if (name == UNITIALIZED_STRING)
    {
      name = ASTFacadeHelper.toString(getASTNode().getName());
    }
    return name;
  }
  
  public void setName(String name)
  {
    this.name = name;
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
    if (!isDisposed())
    {
      List<JNode> children = new ArrayList<JNode>();
      children.addAll(getAnnotationList());
      children.addAll(getMembers());
      if (!children.isEmpty())
      {
        return Collections.unmodifiableList(children);
      }
    }
    return Collections.emptyList();
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
    
    if (child instanceof ASTJMember<?>)
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
    
    if (newSibling instanceof ASTJMember<?>)
    {
      ChildListPropertyDescriptor property = getASTNode().getBodyDeclarationsProperty();
      if (node instanceof ASTJMember<?>)
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
   * This implementation supports moving the nodes by calling {@link #remove(ASTJNode)} and 
   * then {@link #insertSibling(ASTJNode, ASTJNode, boolean)} or {@link #addChild(ASTJNode)}.
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
    
    if (node instanceof ASTJMember<?>)
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
