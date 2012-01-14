/**
 * Copyright (c) 2006 IBM Corporation and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: 
 *   IBM - Initial API and implementation
 */
package org.eclipse.emf.codegen.merge.java.facade.ast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.eclipse.jdt.core.dom.ASTNode;
import org.eclipse.jdt.core.dom.BodyDeclaration;
import org.eclipse.jdt.core.dom.IExtendedModifier;
import org.eclipse.jdt.core.dom.Modifier;
import org.eclipse.jdt.core.dom.rewrite.ListRewrite;

import org.eclipse.emf.codegen.merge.java.facade.JMember;
import org.eclipse.emf.codegen.merge.java.facade.JNode;

/**
 * Each <code>ASTJMember</code> has a reference to {@link BodyDeclaration} as a wrapped object.
 * 
 * @param <T> wrapped body declaration type
 * 
 * @since 2.2.0
 */
public abstract class ASTJMember<T extends BodyDeclaration> extends ASTJNode<T> implements JMember
{
  /**
   * Cached value of member comment.
   * @see #getComment()
   * @see #setComment(String)
   */
  protected String comment = UNITIALIZED_STRING;

  /**
   * @param bodyDeclaration
   */
  public ASTJMember(T bodyDeclaration)
  {
    super(bodyDeclaration);
  }
  
  @Override
  public void dispose()
  {
    comment = null;
    super.dispose();
  }
  
  /**
   * Returns original flags of the member.
   * 
   * @see org.eclipse.emf.codegen.merge.java.facade.AbstractJNode#getFlags()
   */
  @Override
  public int getFlags()
  {
    return getASTNode().getModifiers();
  }
  
  /**
   * Sets the flags of the member.
   * <p>
   * Note that <code>getFlags()</code> will not return the new value.
   * 
   * @see org.eclipse.emf.codegen.merge.java.facade.ast.ASTJNode#setFlags(int)
   */
  @Override
  public void setFlags(int flags)
  {
    BodyDeclaration bodyDeclaration = getASTNode();
    ListRewrite listRewrite = rewriter.getListRewrite(bodyDeclaration, bodyDeclaration.getModifiersProperty());

    // remove all existing modifiers
    @SuppressWarnings("unchecked") 
    List<IExtendedModifier> existingModifiers = listRewrite.getRewrittenList();
    for (IExtendedModifier modifier : existingModifiers)
    {
      if (modifier.isModifier())
      {
        listRewrite.remove((ASTNode)modifier, null);
      }
    }
    
    // create new modifiers and add to rewrite
    @SuppressWarnings("unchecked")    
    List<Modifier> newModifiers = bodyDeclaration.getAST().newModifiers(flags);
    for (Modifier modifier : newModifiers)
    {
      listRewrite.insertLast(modifier, null);
    }    
  }
  
  public String getComment()
  {
    if (comment == UNITIALIZED_STRING)
    {
      comment = getFacadeHelper().toString(getASTNode().getJavadoc());
    }
    return comment;
  }

  public void setComment(String comment)
  {
    this.comment = comment;
    setTrackedNodeProperty(getASTNode(), comment, getASTNode().getJavadocProperty(), ASTNode.JAVADOC);
  }
  
  /* (non-Javadoc)
   * @see org.eclipse.emf.codegen.merge.java.facade.ast.ASTJNode#remove(org.eclipse.emf.codegen.merge.java.facade.ast.ASTJNode)
   */
  @Override
  public boolean remove(ASTJNode<?> node)
  {
    if (node.getParent() != this)
    {
      return false;
    }
    
    if (node instanceof ASTJAnnotation)
    {
      remove(node, getASTNode().getModifiersProperty());
      return true;
    }
    
    return false;
  }
  
  /* (non-Javadoc)
   * @see org.eclipse.emf.codegen.merge.java.facade.ast.ASTJNode#insertSibling(org.eclipse.emf.codegen.merge.java.facade.ast.ASTJNode, org.eclipse.emf.codegen.merge.java.facade.ast.ASTJNode, boolean)
   */
  @Override
  public boolean insertSibling(ASTJNode<?> node, ASTJNode<?> newSibling, boolean before)
  {
    if (newSibling.getParent() != null || node.getParent() != this)
    {
      return false;
    }
    
    if (newSibling instanceof ASTJAnnotation)
    {
      if (node instanceof ASTJAnnotation)
      {
        insert(newSibling, getASTNode().getModifiersProperty(), node, before);
      }
      else
      {
        insertLastAnnotation((ASTJAnnotation)newSibling);
      }
      return true;
    }
    
    return false;
  }
  
  /* (non-Javadoc)
   * @see org.eclipse.emf.codegen.merge.java.facade.ast.ASTJNode#addChild(org.eclipse.emf.codegen.merge.java.facade.ast.ASTJNode)
   */
  @Override
  public boolean addChild(ASTJNode<?> child)
  {
    if (child.getParent() != null)
    {
      return false;
    }
    
    if (child instanceof ASTJAnnotation)
    {
      insertLastAnnotation((ASTJAnnotation)child);
      return true;
    }
    return false;
  }
  
  /* (non-Javadoc)
   * @see org.eclipse.emf.codegen.merge.java.facade.AbstractJNode#getChildren()
   */
  @Override
  public List<JNode> getChildren()
  {
    if (!isDisposed())
    {
      List<JNode> children = new ArrayList<JNode>();
      children.addAll(getAnnotationList());
      if (!children.isEmpty())
      {
        return Collections.unmodifiableList(children);
      }
    }
    return Collections.emptyList();
  }

  protected List<JNode> getAnnotationList()
  {
    List<JNode> annotations = new ArrayList<JNode>();
    ListRewrite listRewrite = rewriter.getListRewrite(getASTNode(), getASTNode().getModifiersProperty());
    
    @SuppressWarnings("unchecked")
    List<IExtendedModifier> modifiers = listRewrite.getRewrittenList();
    
    for (IExtendedModifier extendedModifier : modifiers)
    {
      if (extendedModifier.isAnnotation())
      {
        JNode annotation = getFacadeHelper().convertToNode(extendedModifier);
        // specify the exact parent of annotations
        ((ASTJNode<?>)annotation).setParent(this);
        annotations.add(annotation);
      }
    }
    return annotations;    
  }
  
  /**
   * Insert annotation after the last annotation of the given body declaration.
   * <p>
   * Note that modifiers and annotations can alternate in the modifiers list.
   * This method inserts the new annotation after the last existing annotation.
   * 
   * @param annotation
   */
  protected void insertLastAnnotation(ASTJAnnotation annotation)
  {
    List<JNode> annotations = getAnnotationList();
    if (annotations.size() > 0)
    {
      ASTJNode<?> lastAnnotation = (ASTJNode<?>)annotations.get(annotations.size() - 1);
      insert(annotation, getASTNode().getModifiersProperty(), lastAnnotation, false);
    }
    else
    {
      insertFirst(annotation, getASTNode().getModifiersProperty());
    }
  }
}
