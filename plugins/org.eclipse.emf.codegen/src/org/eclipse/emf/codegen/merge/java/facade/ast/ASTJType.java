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
 * $Id: ASTJType.java,v 1.2 2006/11/01 21:31:43 marcelop Exp $
 */
package org.eclipse.emf.codegen.merge.java.facade.ast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import org.eclipse.emf.codegen.merge.java.facade.FacadeFlags;
import org.eclipse.emf.codegen.merge.java.facade.JNode;
import org.eclipse.emf.codegen.merge.java.facade.JType;
import org.eclipse.jdt.core.dom.ASTNode;
import org.eclipse.jdt.core.dom.BodyDeclaration;
import org.eclipse.jdt.core.dom.FieldDeclaration;
import org.eclipse.jdt.core.dom.Type;
import org.eclipse.jdt.core.dom.TypeDeclaration;
import org.eclipse.jdt.core.dom.rewrite.ListRewrite;

/**
 * Wraps {@link TypeDeclaration} object.
 * 
 * @since 2.2.0
 */
public class ASTJType extends ASTJMember implements JType
{
  /**
   * @param typeDeclaration
   */
  public ASTJType(TypeDeclaration typeDeclaration)
  {
    super(typeDeclaration);
  }
  
  /**
   * @return type declaration wrapped by this node
   */
  protected TypeDeclaration getASTTypeDeclaration()
  {
    return (TypeDeclaration)getASTNode();
  }

  /**
   * Returns the original superclass.
   * 
   * @see org.eclipse.emf.codegen.merge.java.facade.JType#getSuperclass()
   */
  public String getSuperclass()
  {
    return ASTFacadeHelper.toString(getASTTypeDeclaration().getSuperclassType());
  }

  /**
   * Sets the superclass.
   * <p>
   * Note that <code>getSuperclass()</code> will not return the new value.
   * 
   * @see org.eclipse.emf.codegen.merge.java.facade.JType#setSuperclass(java.lang.String)
   */
  public void setSuperclass(String superclassName)
  {
    setNodeProperty(getASTTypeDeclaration(), superclassName, TypeDeclaration.SUPERCLASS_TYPE_PROPERTY, ASTNode.SIMPLE_TYPE);
  }

  /**
   * Returns the array of interfaces in <code>implements</code> clause.
   * 
   * @see org.eclipse.emf.codegen.merge.java.facade.JType#getSuperInterfaces()
   */
  public String[] getSuperInterfaces()
  {
    List<?> interfaces = getASTTypeDeclaration().superInterfaceTypes();
    String[] ret = new String [interfaces.size()];
    int j = 0;
    for (Iterator<?> i = interfaces.iterator(); i.hasNext();)
    {
      Type type = (Type)i.next();
      ret[j++] = facadeHelper.toString(type);
    }
    return ret;
  }

  /**
   * Sets the list of interfaces in the <code>implements</code> list to the given array.
   * <p>
   * Note that <code>getSuperInterfaces()</code> will not return the new value.
   * 
   * @see org.eclipse.emf.codegen.merge.java.facade.JType#setSuperInterfaces(java.lang.String[])
   */
  public void setSuperInterfaces(String[] interfaceNames)
  {
    setListNodeProperty(getASTTypeDeclaration(), interfaceNames, TypeDeclaration.SUPER_INTERFACE_TYPES_PROPERTY, ASTNode.SIMPLE_TYPE);
  }

  /**
   * Adds the interface to the <code>implements</code> list.
   * <p>
   * Note that <code>getSuperInterfaces()</code> will not return the new value.
   * 
   * @see org.eclipse.emf.codegen.merge.java.facade.JType#addSuperInterface(java.lang.String)
   */
  public void addSuperInterface(String superInterface)
  {
    addValueToListProperty(getASTTypeDeclaration(), superInterface, TypeDeclaration.SUPER_INTERFACE_TYPES_PROPERTY, ASTNode.SIMPLE_TYPE);
  }

  /* (non-Javadoc)
   * @see org.eclipse.emf.codegen.merge.java.facade.JType#getTypeParameters()
   */
  public String[] getTypeParameters()
  {
    // TODO implement (Java 5)
    return EMPTY_STRING_ARRAY;
  }

  /* (non-Javadoc)
   * @see org.eclipse.emf.codegen.merge.java.facade.JNode#getName()
   */
  public String getName()
  {
    return ASTFacadeHelper.toString(getASTTypeDeclaration().getName());
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
   * Returns the original flags of the type, including the {@link FacadeFlags#INTERFACE} flag.
   * <p>
   * Note that the {@link FacadeFlags#INTERFACE} flag can not be set.
   * 
   * @see org.eclipse.emf.codegen.merge.java.facade.ast.ASTJMember#getFlags()
   */
  @Override
  public int getFlags()
  {
    return getASTTypeDeclaration().isInterface() ? 
      super.getFlags() | FacadeFlags.INTERFACE :
      super.getFlags();
  }

  /**
   * Returns the list of children as a list of body declarations with each element wrapped by <code>ASTJNode</code>.
   * 
   * @see ASTFacadeHelper#convertToNode(Object)
   * @see org.eclipse.emf.codegen.merge.java.facade.AbstractJNode#getChildren()
   */
  @Override
  public List<JNode> getChildren()
  {
    List<JNode> resultChildren = new ArrayList<JNode>();
    ListRewrite listRewrite = rewriter.getListRewrite(getASTTypeDeclaration(), TypeDeclaration.BODY_DECLARATIONS_PROPERTY);
    List<?> allChildren = listRewrite.getRewrittenList();
    for (Iterator<?> i = allChildren.iterator(); i.hasNext();)
    {
      BodyDeclaration bodyDeclaration = (BodyDeclaration)i.next();
      
      // for field declarations use variable declaration fragment instead
      if (bodyDeclaration instanceof FieldDeclaration)
      {
        for (Iterator<?> fragmentsIt = ((FieldDeclaration)bodyDeclaration).fragments().iterator(); fragmentsIt.hasNext();)
        {
          resultChildren.add(getFacadeHelper().convertToNode(fragmentsIt.next()));
        }
      }
      else
      {
        resultChildren.add(getFacadeHelper().convertToNode(bodyDeclaration));
      }
    }
    return resultChildren.isEmpty() ? Collections.<JNode>emptyList() : Collections.<JNode>unmodifiableList(resultChildren);
  }
  
  /* (non-Javadoc)
   * @see org.eclipse.emf.codegen.merge.java.facade.ast.ASTJNode#addChild(org.eclipse.emf.codegen.merge.java.facade.JNode)
   */
  @Override
  public boolean addChild(JNode child)
  {
    if (!(child instanceof ASTJMember) || child.getParent() != null)
    {
      return false;
    }
    BodyDeclaration astNode = ((ASTJMember)child).getASTBodyDeclaration();    

    if (astNode != null)
    {
      insertLast(getASTTypeDeclaration(), TypeDeclaration.BODY_DECLARATIONS_PROPERTY, astNode);
      
      // mark the node as moved if original node is set      
      ASTNode originalASTNode = ((ASTJMember)child).getOriginalASTNode();
      if (originalASTNode != null)
      {
        nodeToBeMoved(originalASTNode);
      }
      
      ((ASTJMember) child).setParent(this);
      
      return true;
    }
    return false;
  }
  
  /* (non-Javadoc)
   * @see org.eclipse.emf.codegen.merge.java.facade.ast.ASTJNode#insertSibling(org.eclipse.emf.codegen.merge.java.facade.JNode, org.eclipse.emf.codegen.merge.java.facade.JNode, boolean)
   */
  @Override
  public boolean insertSibling(JNode node, JNode newSibling, boolean before)
  {
    if (!(node instanceof ASTJMember && newSibling instanceof ASTJMember) || newSibling.getParent() != null)
    {
      return false;
    }
    ASTJMember memberNode = (ASTJMember)node;
    ASTJMember newMemberSibling = (ASTJMember)newSibling;
    BodyDeclaration astNode = memberNode.getASTBodyDeclaration();
    BodyDeclaration newASTSibling = newMemberSibling.getASTBodyDeclaration();

    if (astNode != null && newASTSibling != null)
    {
      insert(getASTTypeDeclaration(), TypeDeclaration.BODY_DECLARATIONS_PROPERTY, newASTSibling, astNode, before);
      
      // mark the node as moved if original node is set
      ASTNode originalASTNode = newMemberSibling.getOriginalASTNode();
      if (originalASTNode != null)
      {
        nodeToBeMoved(originalASTNode);
      }
      
      newMemberSibling.setParent(this);
      
      return true;
    }
    return false;
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
  public boolean remove(JNode node)
  {
    if (node instanceof ASTJMember && node.getParent() != null)
    {
      ASTJMember memberNode = (ASTJMember) node;
      BodyDeclaration astNode = memberNode.getASTBodyDeclaration();
      if (astNode != null)
      {
        // mark the node to be removed
        nodeToBeRemoved(astNode);
        
        // assume that the node is moved (to allow insertion after)
        memberNode.setOriginalASTNode(astNode);
        
        ASTNode moveTargetASTNode = rewriter.createMoveTarget(astNode);
        memberNode.setASTNode(moveTargetASTNode);
  
        // remove the node
        removeNodeFromListProperty(getASTNode(), astNode, TypeDeclaration.BODY_DECLARATIONS_PROPERTY);
        memberNode.setParent(null);
        
        return true;
      }
    }
    return false;
  }
}
