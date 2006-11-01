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
 * $Id: ASTJPackage.java,v 1.2 2006/11/01 21:31:43 marcelop Exp $
 */
package org.eclipse.emf.codegen.merge.java.facade.ast;

import org.eclipse.jdt.core.dom.PackageDeclaration;

import org.eclipse.emf.codegen.merge.java.facade.JPackage;

/**
 * Wraps {@link PackageDeclaration} object.
 * 
 * @since 2.2.0
 */
public class ASTJPackage extends ASTJNode implements JPackage
{
  /**
   * @param packageDeclaration
   */
  public ASTJPackage(PackageDeclaration packageDeclaration)
  {
    super(packageDeclaration);
  }
  
  /**
   * @return package declaration wrapped by this node
   */
  protected PackageDeclaration getASTPackageDeclaration()
  {
    return (PackageDeclaration)getASTNode();
  }  
  
  /* (non-Javadoc)
   * @see org.eclipse.emf.codegen.merge.java.facade.JNode#getName()
   */
  public String getName()
  {
    return ASTFacadeHelper.toString(getASTPackageDeclaration().getName());
  }
}
