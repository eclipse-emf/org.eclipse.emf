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
 * $Id: ASTJAnnotation.java,v 1.5 2007/06/12 20:56:05 emerks Exp $
 */
package org.eclipse.emf.codegen.merge.java.facade.ast;

import org.eclipse.jdt.core.dom.ASTNode;
import org.eclipse.jdt.core.dom.Annotation;

import org.eclipse.emf.codegen.merge.java.facade.JAnnotation;


/**
 * Wraps {@link Annotation} object. ASTJAnnotation nodes are 
 * children of {@link ASTJMember}.
 */
public class ASTJAnnotation extends ASTJNode<Annotation> implements JAnnotation
{
  /**
   * Annotation node to be used by set methods
   */
  private Annotation rewrittenASTNode = null;
  
  /**
   * Cached contents of this annotation
   * @see #getContents()
   * @see #setContents(String)
   */
  protected String contents = UNITIALIZED_STRING;

  /**
   * @param annotation
   */
  protected ASTJAnnotation(Annotation annotation)
  {
    super(annotation);
    rewrittenASTNode = annotation;
  }
  
  @Override
  public void dispose()
  {
    rewrittenASTNode = null;
    contents = null;
    super.dispose();
  }

  /**
   * @return the rewritten annotation
   */
  protected Annotation getRewrittenASTNode()
  {
    return rewrittenASTNode;
  }

  /**
   * @param rewrittenAnnotation the annotation to use in set methods
   */
  protected void setRewrittenASTNode(Annotation rewrittenAnnotation)
  {
    this.rewrittenASTNode = rewrittenAnnotation;
  }

  /**
   * Returns name of annotation type with preceding &#64; sign.
   * @see org.eclipse.emf.codegen.merge.java.facade.JNode#getName()
   */
  public String getName()
  {
    if (name == UNITIALIZED_STRING)
    {
      // adding 'at' allows distinguish annotations from other members
      // (i.e. inner class verses annotation of a class)
      //
      name = "@" + ASTFacadeHelper.toString(getASTNode().getTypeName());
    }
    return name;
  }
  
  /**
   * Sets the name of annotation type. If the name starts with &#64; sign, &#64; sign is removed.
   * @param name name to set 
   * @see org.eclipse.emf.codegen.merge.java.facade.JNode#setName(java.lang.String)
   */
  public void setName(String name)
  {
    if (name != null)
    {
      name = name.trim();
      if (name.startsWith("@"))
      {
        this.name = name;
        name = name.substring(1);
      }
      else
      {
        this.name = "@" + name;
      }
    }
    else
    {
      this.name = name;
    }
    setNodeProperty(getASTNode(), name, getASTNode().getTypeNameProperty(), ASTNode.SIMPLE_NAME);
  }

  @Override
  public String getContents()
  {
    if (contents == UNITIALIZED_STRING)
    {
      contents = super.getContents();
    }
    return contents;
  }

  public void setContents(String contents)
  {
    if (getParent() != null)
    {
      getParent().childToBeChanged(this);
    }
    this.contents = contents;
    trackAndReplace(getRewrittenASTNode(), contents);
  }  
}
