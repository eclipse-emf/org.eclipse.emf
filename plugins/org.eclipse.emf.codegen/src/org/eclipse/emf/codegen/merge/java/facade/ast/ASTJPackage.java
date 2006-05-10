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
 * $Id: ASTJPackage.java,v 1.1 2006/05/10 20:33:16 marcelop Exp $
 */
package org.eclipse.emf.codegen.merge.java.facade.ast;

import org.eclipse.jdt.core.dom.PackageDeclaration;

import org.eclipse.emf.codegen.merge.java.facade.JPackage;

/**
 * @since 2.2.0
 */
public class ASTJPackage extends ASTJNode implements JPackage
{
  public ASTJPackage(PackageDeclaration packageDeclaration)
  {
    super(packageDeclaration);
  }
  
  protected PackageDeclaration getASTPackageDeclaration()
  {
    return (PackageDeclaration)getASTNode();
  }  
  
  public String getName()
  {
    return ASTFacadeHelper.toString(getASTPackageDeclaration().getName());
  }
}
