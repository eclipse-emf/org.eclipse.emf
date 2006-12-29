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
 * $Id: ASTJEnum.java,v 1.2 2006/12/29 20:56:07 marcelop Exp $
 */
package org.eclipse.emf.codegen.merge.java.facade.ast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.eclipse.jdt.core.dom.ASTNode;
import org.eclipse.jdt.core.dom.EnumDeclaration;
import org.eclipse.jdt.core.dom.rewrite.ListRewrite;

import org.eclipse.emf.codegen.merge.java.facade.JEnum;
import org.eclipse.emf.codegen.merge.java.facade.JNode;


public class ASTJEnum extends ASTJAbstractType<EnumDeclaration> implements JEnum
{
  /**
   * @param enumDeclaration
   */
  public ASTJEnum(EnumDeclaration enumDeclaration)
  {
    super(enumDeclaration);
  }

  /* (non-Javadoc)
   * @see org.eclipse.emf.codegen.merge.java.facade.JEnum#addSuperInterface(java.lang.String)
   */
  public void addSuperInterface(String superInterface)
  {
    addValueToListProperty(getASTNode(), superInterface, EnumDeclaration.SUPER_INTERFACE_TYPES_PROPERTY, ASTNode.SIMPLE_TYPE);
  }

  /* (non-Javadoc)
   * @see org.eclipse.emf.codegen.merge.java.facade.JEnum#getSuperInterfaces()
   */
  @SuppressWarnings("unchecked")
  public String[] getSuperInterfaces()
  {
    return convertASTNodeListToStringArray(getASTNode().superInterfaceTypes());
  }

  /* (non-Javadoc)
   * @see org.eclipse.emf.codegen.merge.java.facade.JEnum#setSuperInterfaces(java.lang.String[])
   */
  public void setSuperInterfaces(String[] superInterfaces)
  {
    setListNodeProperty(getASTNode(), superInterfaces, EnumDeclaration.SUPER_INTERFACE_TYPES_PROPERTY, ASTNode.SIMPLE_TYPE);
  }

  /* (non-Javadoc)
   * @see org.eclipse.emf.codegen.merge.java.facade.ast.ASTJAbstractType#insertSibling(org.eclipse.emf.codegen.merge.java.facade.ast.ASTJNode, org.eclipse.emf.codegen.merge.java.facade.ast.ASTJNode, boolean)
   */
  @Override
  public boolean insertSibling(ASTJNode<?> node, ASTJNode<?> newSibling, boolean before)
  {
    if (newSibling.getParent() != null || node.getParent() != this)
    {
      return false;
    }

    if (newSibling instanceof ASTJEnumConstant)
    {
      if (node instanceof ASTJEnumConstant)
      {
        insert(newSibling, EnumDeclaration.ENUM_CONSTANTS_PROPERTY, node, before);
      }
      else if (node instanceof ASTJAnnotation)
      {
        insertFirst(newSibling, EnumDeclaration.ENUM_CONSTANTS_PROPERTY);
      }
      else
      {
        insertLast(newSibling, EnumDeclaration.ENUM_CONSTANTS_PROPERTY);
      }

      return true;
    }
    else if (node instanceof ASTJEnumConstant)
    {
      if (newSibling instanceof ASTJAnnotation)
      {
        insertLastAnnotation((ASTJAnnotation)newSibling);
        return true;
      }
      else if (newSibling instanceof ASTJMember)
      {
        insertFirst(newSibling, getASTNode().getBodyDeclarationsProperty());
        return true;
      }
    }

    return super.insertSibling(node, newSibling, before);
  }

  /* (non-Javadoc)
   * @see org.eclipse.emf.codegen.merge.java.facade.ast.ASTJAbstractType#addChild(org.eclipse.emf.codegen.merge.java.facade.ast.ASTJNode)
   */
  @Override
  public boolean addChild(ASTJNode<?> child)
  {
    if (child.getParent() != null)
    {
      return false;
    }

    if (child instanceof ASTJEnumConstant)
    {
      insertLast(child, EnumDeclaration.ENUM_CONSTANTS_PROPERTY);
      return true;
    }

    return super.addChild(child);
  }

  /* (non-Javadoc)
   * @see org.eclipse.emf.codegen.merge.java.facade.ast.ASTJAbstractType#remove(org.eclipse.emf.codegen.merge.java.facade.ast.ASTJNode)
   */
  @Override
  public boolean remove(ASTJNode<?> node)
  {
    if (node.getParent() != this)
    {
      return false;
    }

    if (node instanceof ASTJEnumConstant)
    {
      remove(node, EnumDeclaration.ENUM_CONSTANTS_PROPERTY);
      return true;
    }

    return super.remove(node);
  }

  /* (non-Javadoc)
   * @see org.eclipse.emf.codegen.merge.java.facade.ast.ASTJAbstractType#getChildren()
   */
  @Override
  public List<JNode> getChildren()
  {
    if (!isDisposed())
    {
      List<JNode> children = new ArrayList<JNode>();
      children.addAll(getAnnotationList());
      children.addAll(getEnumConstants());
      children.addAll(getMembers());
      if (!children.isEmpty())
      {
        return Collections.unmodifiableList(children);
      }
    }
    return Collections.emptyList();
  }

  protected List<JNode> getEnumConstants()
  {
    List<JNode> constants = new ArrayList<JNode>();
    ListRewrite listRewrite = rewriter.getListRewrite(getASTNode(), EnumDeclaration.ENUM_CONSTANTS_PROPERTY);
    for (Object enumConstant : listRewrite.getRewrittenList())
    {
      JNode node = getFacadeHelper().convertToNode(enumConstant);
      if (node != null)
      {
        constants.add(node);
      }
    }
    return constants;
  }
}
