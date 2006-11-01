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
 * $Id: ASTJInitializer.java,v 1.2 2006/11/01 21:31:43 marcelop Exp $
 */
package org.eclipse.emf.codegen.merge.java.facade.ast;

import org.eclipse.emf.codegen.merge.java.facade.JInitializer;
import org.eclipse.jdt.core.dom.ASTNode;
import org.eclipse.jdt.core.dom.Initializer;

/**
 * Wraps {@link Initializer} object.
 * 
 * @since 2.2.0
 */
public class ASTJInitializer extends ASTJMember implements JInitializer
{
  /**
   * @param initializer
   */
  public ASTJInitializer(Initializer initializer)
  {
    super(initializer);
  }
  
  /**
   * @return initializer wrapped by this node
   */
  protected Initializer getASTInitializer()
  {
    return (Initializer)getASTBodyDeclaration();
  }

  /* (non-Javadoc)
   * @see org.eclipse.emf.codegen.merge.java.facade.JNode#getName()
   */
  public String getName()
  {
    return getName(this);
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
    return facadeHelper.toString(getASTInitializer().getBody());
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
    setTrackedNodeProperty(getASTInitializer(), body, Initializer.BODY_PROPERTY, ASTNode.BLOCK);
  }
}
