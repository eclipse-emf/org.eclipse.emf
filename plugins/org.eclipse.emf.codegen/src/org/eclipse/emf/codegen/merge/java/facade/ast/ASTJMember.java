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
 * $Id: ASTJMember.java,v 1.2 2006/11/01 21:31:43 marcelop Exp $
 */
package org.eclipse.emf.codegen.merge.java.facade.ast;

import java.util.Iterator;
import java.util.List;

import org.eclipse.emf.codegen.merge.java.facade.JMember;
import org.eclipse.jdt.core.dom.ASTNode;
import org.eclipse.jdt.core.dom.BodyDeclaration;
import org.eclipse.jdt.core.dom.IExtendedModifier;
import org.eclipse.jdt.core.dom.rewrite.ListRewrite;

/**
 * Each <code>ASTJMember</code> has a reference to {@link BodyDeclaration} as a wrapped object.
 * 
 * @since 2.2.0
 */
public abstract class ASTJMember extends ASTJNode implements JMember
{
  /**
   * Used to keep reference to original AST node when node is removed and then inserted
   */
  private ASTNode originalASTNode = null;
  
  /**
   * @return original ASTNode before removal
   */
  protected ASTNode getOriginalASTNode()
  {
    return originalASTNode;
  }

  /**
   * Sets original AST node to the given node.
   * 
   * @param originalASTNode
   */
  protected void setOriginalASTNode(ASTNode originalASTNode)
  {
    this.originalASTNode = originalASTNode;
  }

  /**
   * @param bodyDeclaration
   */
  public ASTJMember(BodyDeclaration bodyDeclaration)
  {
    super(bodyDeclaration);
  }
  
  /**
   * @return body declaration wrapped by {@link ASTJNode}
   */
  protected BodyDeclaration getASTBodyDeclaration()
  {
    return (BodyDeclaration)getASTNode();
  }  
  
  /**
   * Returns original flags of the member.
   * 
   * @see org.eclipse.emf.codegen.merge.java.facade.AbstractJNode#getFlags()
   */
  @Override
  public int getFlags()
  {
    return getASTBodyDeclaration().getModifiers();
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
    // check if changed
    if (getFlags() == flags)
    {
      return;
    }
    
    ListRewrite listRewrite = rewriter.getListRewrite(getASTBodyDeclaration(), getASTBodyDeclaration().getModifiersProperty());

    // TODO check the order of ExtendedModifiers and Modifiers 
    
    // remove all existing modifiers
    for (Iterator<?> iter = listRewrite.getRewrittenList().iterator(); iter.hasNext();)
    {
      IExtendedModifier modifier = (IExtendedModifier)iter.next();
      if (modifier.isModifier())
      {
        listRewrite.remove((ASTNode)modifier, null);
      }
    }
    
    // create new modifiers and add to rewrite
    List<?> newModifiersList = getASTBodyDeclaration().getAST().newModifiers(flags);
    for (Iterator<?> iter = newModifiersList.iterator(); iter.hasNext();)
    {
      listRewrite.insertLast((ASTNode)iter.next(), null);
    }
  }
  
  /**
   * Returns string contents of original Javadoc of the member.
   * 
   * @see org.eclipse.emf.codegen.merge.java.facade.JMember#getComment()
   */
  public String getComment()
  {
    return facadeHelper.toString(getASTBodyDeclaration().getJavadoc());
  }

  /**
   * Sets the Javadoc to the given string.
   * <p>
   * Note that <code>getComment()</code> will not return the new value.
   * 
   * @see org.eclipse.emf.codegen.merge.java.facade.JMember#setComment(java.lang.String)
   */
  public void setComment(String comment)
  {
    setTrackedNodeProperty(getASTBodyDeclaration(), comment, getASTBodyDeclaration().getJavadocProperty(), ASTNode.JAVADOC);
  }
}
