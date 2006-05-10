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
 * $Id: ASTJInitializer.java,v 1.1 2006/05/10 20:33:16 marcelop Exp $
 */
package org.eclipse.emf.codegen.merge.java.facade.ast;

import org.eclipse.jdt.core.dom.Initializer;

import org.eclipse.emf.codegen.merge.java.facade.JInitializer;

/**
 * @since 2.2.0
 */
public class ASTJInitializer extends ASTJMember implements JInitializer
{
  public ASTJInitializer(Initializer initializer)
  {
    super(initializer);
  }
  
  protected Initializer getASTInitializer()
  {
    return (Initializer)getASTBodyDeclaration();
  }

  public String getName()
  {
    return getName(this);
  }
    
  protected String computeQualifiedName()
  {
    return computeQualifiedName(this);
  }
  
  public String getBody()
  {
    return null;
  }

  public void setBody(String body)
  {
  }
}
