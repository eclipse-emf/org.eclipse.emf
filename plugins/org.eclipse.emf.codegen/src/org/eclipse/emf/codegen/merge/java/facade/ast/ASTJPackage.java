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
 * $Id: ASTJPackage.java,v 1.5 2006/11/02 20:34:48 marcelop Exp $
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
  
  @Override
  public String getContents()
  {
    String content = super.getContents();
    return fixPackageContent(content);
  }
  
  protected String fixPackageContent(String content)
  {
    String header = getFacadeHelper().getCompilationUnit(this).getHeader();
    if (header != null && content.startsWith(header))
    {
      content = content.substring(header.length());
    }
    else
    {
      String javadoc = getFacadeHelper().toString(getASTPackageDeclaration().getJavadoc());
      if (javadoc != null && content.startsWith(javadoc))
      {
        content = content.substring(javadoc.length());
      }
    }
    return content.trim();
  }
}
