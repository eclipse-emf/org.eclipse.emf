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
 * $Id: ASTJAnnotation.java,v 1.3 2006/12/29 20:56:54 marcelop Exp $
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
   * Annotation node to be used by set methods.
   */
  private Annotation rewrittenASTNode = null;

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
   * 
   * @see org.eclipse.emf.codegen.merge.java.facade.JNode#getName()
   */
  public String getName()
  {
    // adding ampersand allows distinguish annotations from other members
    // (i.e. inner class vs. annotation of a class) 
    return name == UNITIALIZED_STRING ? name = "@" + ASTFacadeHelper.toString(getASTNode().getTypeName()) : name;
  }
  
  /**
   * Sets the name of annotation type. If the name starts with &#64; sign, &#64; sign is removed.
   * 
   * @param name name to set 
   * 
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

  /* (non-Javadoc)
   * @see org.eclipse.emf.codegen.merge.java.facade.JAnnotation#setContents(java.lang.String)
   */
  public void setContents(String contents)
  {
    if (getParent() != null)
    {
      getParent().childToBeChanged(this);
    }

    trackAndReplace(getRewrittenASTNode(), contents);
  }
}
