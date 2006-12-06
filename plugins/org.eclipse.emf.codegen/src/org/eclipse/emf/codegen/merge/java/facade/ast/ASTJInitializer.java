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
 * $Id: ASTJInitializer.java,v 1.3 2006/12/06 03:48:43 marcelop Exp $
 */
package org.eclipse.emf.codegen.merge.java.facade.ast;

import org.eclipse.jdt.core.dom.ASTNode;
import org.eclipse.jdt.core.dom.Initializer;

import org.eclipse.emf.codegen.merge.java.facade.JInitializer;

/**
 * Wraps {@link Initializer} object.
 * 
 * @since 2.2.0
 */
public class ASTJInitializer extends ASTJMember<Initializer> implements JInitializer
{
  /**
   * @param initializer
   */
  public ASTJInitializer(Initializer initializer)
  {
    super(initializer);
  }
  
  /* (non-Javadoc)
   * @see org.eclipse.emf.codegen.merge.java.facade.JNode#getName()
   */
  public String getName()
  {
    return getName(this);
  }
  
  /**
   * This implementation does nothing. 
   * 
   * @see org.eclipse.emf.codegen.merge.java.facade.JNode#setName(java.lang.String)
   */
  public void setName(String name)
  {
    // no op
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
   * Returns the original body of the initializer.
   * 
   * @see org.eclipse.emf.codegen.merge.java.facade.JInitializer#getBody()
   */
  public String getBody()
  {
    return getFacadeHelper().toString(getASTNode().getBody());
  }

  /**
   * Sets the body of the initializer.
   * <p>
   * Note that <code>getBody()</code> will not return the new value.
   * 
   * @see org.eclipse.emf.codegen.merge.java.facade.JInitializer#setBody(java.lang.String)
   */
  public void setBody(String body)
  {
    setTrackedNodeProperty(getASTNode(), body, Initializer.BODY_PROPERTY, ASTNode.BLOCK);
  }
}
