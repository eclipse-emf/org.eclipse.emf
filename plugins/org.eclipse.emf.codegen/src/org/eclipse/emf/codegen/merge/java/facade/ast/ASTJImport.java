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
 * $Id: ASTJImport.java,v 1.2 2006/11/01 21:31:43 marcelop Exp $
 */
package org.eclipse.emf.codegen.merge.java.facade.ast;

import org.eclipse.emf.codegen.merge.java.facade.FacadeFlags;
import org.eclipse.emf.codegen.merge.java.facade.JImport;
import org.eclipse.jdt.core.dom.ImportDeclaration;

/**
 * Wraps {@link ImportDeclaration} object.
 * 
 * @since 2.2.0
 */
public class ASTJImport extends ASTJNode implements JImport
{
  /**
   * @param importDeclaration
   */
  public ASTJImport(ImportDeclaration importDeclaration)
  {
    super(importDeclaration);
  }
  
  /**
   * @return import declaration wrapped by {@link ASTJImport}
   */
  protected ImportDeclaration getASTImportDeclaration()
  {
    return (ImportDeclaration)getASTNode();
  }
  
  /* (non-Javadoc)
   * @see org.eclipse.emf.codegen.merge.java.facade.JImport#isOnDemand()
   */
  public boolean isOnDemand()
  {
    return getASTImportDeclaration().isOnDemand();
  }

  /* (non-Javadoc)
   * @see org.eclipse.emf.codegen.merge.java.facade.JNode#getName()
   */
  public String getName()
  {
    String name = ASTFacadeHelper.toString(getASTImportDeclaration().getName());
    if (isOnDemand() && name.lastIndexOf('*') < 0)
    {
      name = name + ".*";
    }
    return name;
  }
  
  /**
   * Returns original flags.
   * <p>
   * For import, only {@link FacadeFlags#STATIC} flag is set.
   * 
   * @see org.eclipse.emf.codegen.merge.java.facade.AbstractJNode#getFlags()
   */
  @Override
  public int getFlags()
  {
    return getASTImportDeclaration().isStatic() ? FacadeFlags.STATIC : super.getFlags();
  }
  
  /**
   * Sets {@link FacadeFlags#STATIC} flag.
   * <p>
   * Note that <code>getFlags()</code> will not return the new value.
   * 
   * @see org.eclipse.emf.codegen.merge.java.facade.ast.ASTJNode#setFlags(int)
   */
  @Override
  public void setFlags(int flags)
  {
    // if not changed, return
    if (flags == getFlags())
    {
      return;
    }
    
    ImportDeclaration importDeclaration = getASTImportDeclaration();
    setNodeProperty(importDeclaration, flags & FacadeFlags.STATIC, ImportDeclaration.STATIC_PROPERTY);
  }
}
