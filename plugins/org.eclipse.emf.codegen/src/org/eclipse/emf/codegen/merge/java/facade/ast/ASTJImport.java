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
 * $Id: ASTJImport.java,v 1.1 2006/05/10 20:33:16 marcelop Exp $
 */
package org.eclipse.emf.codegen.merge.java.facade.ast;

import org.eclipse.jdt.core.dom.ImportDeclaration;

import org.eclipse.emf.codegen.merge.java.facade.FacadeFlags;
import org.eclipse.emf.codegen.merge.java.facade.JImport;

/**
 * @since 2.2.0
 */
public class ASTJImport extends ASTJNode implements JImport
{
  public ASTJImport(ImportDeclaration importDeclaration)
  {
    super(importDeclaration);
  }
  
  protected ImportDeclaration getASTImportDeclaration()
  {
    return (ImportDeclaration)getASTNode();
  }
  
  public boolean isOnDemand()
  {
    return getASTImportDeclaration().isOnDemand();
  }

  public String getName()
  {
    String name = ASTFacadeHelper.toString(getASTImportDeclaration().getName());
    if (isOnDemand() && name.lastIndexOf('*') < 0)
    {
      name = name + ".*";
    }
    return name;
  }
  
  public int getFlags()
  {
    return getASTImportDeclaration().isStatic() ? FacadeFlags.STATIC : super.getFlags();
  }
}
