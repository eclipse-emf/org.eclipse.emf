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
 * $Id: ASTJInitializer.java,v 1.6 2007/06/12 20:56:05 emerks Exp $
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
   * Cached body of initializer
   * @see #getBody()
   * @see #setBody(String)
   */
  protected String body = UNITIALIZED_STRING;
  
  /**
   * @param initializer
   */
  public ASTJInitializer(Initializer initializer)
  {
    super(initializer);
  }
  
  @Override
  public void dispose()
  {
    body = null;
    super.dispose();
  }
  
  /**
   * Returns value from {@link #getName(JInitializer)}
   * 
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
    // Ignore.
  }  
    
  /* (non-Javadoc)
   * @see org.eclipse.emf.codegen.merge.java.facade.AbstractJNode#computeQualifiedName()
   */
  @Override
  protected String computeQualifiedName()
  {
    return computeQualifiedName(this);
  }
  
  public String getBody()
  {
    if (body == UNITIALIZED_STRING)
    {
      body = getFacadeHelper().toString(getASTNode().getBody());
    }
    return body;
  }

  public void setBody(String body)
  {
    this.body = body;
    setTrackedNodeProperty(getASTNode(), body, Initializer.BODY_PROPERTY, ASTNode.BLOCK);
  }
}
