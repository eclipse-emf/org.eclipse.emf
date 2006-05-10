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
 * $Id: ASTJMember.java,v 1.1 2006/05/10 20:33:16 marcelop Exp $
 */
package org.eclipse.emf.codegen.merge.java.facade.ast;

import org.eclipse.jdt.core.dom.BodyDeclaration;

import org.eclipse.emf.codegen.merge.java.facade.JMember;

/**
 * @since 2.2.0
 */
public abstract class ASTJMember extends ASTJNode implements JMember
{
  public ASTJMember(BodyDeclaration bodyDeclaration)
  {
    super(bodyDeclaration);
  }
  
  protected BodyDeclaration getASTBodyDeclaration()
  {
    return (BodyDeclaration)getASTNode();
  }  
  
  public int getFlags()
  {
    return getASTBodyDeclaration().getModifiers();
  }

  public String getComment()
  {
    return toString(getASTBodyDeclaration().getJavadoc());
  }

  public void setComment(String comment)
  {
  }
}
