/**
 * Copyright (c) 2006 IBM Corporation and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: 
 *   IBM - Initial API and implementation
 */
package org.eclipse.emf.codegen.merge.java.facade.ast;

import org.eclipse.jdt.core.dom.ASTNode;
import org.eclipse.jdt.core.dom.PackageDeclaration;

import org.eclipse.emf.codegen.merge.java.facade.JPackage;

/**
 * Wraps {@link PackageDeclaration} object.
 * 
 * @since 2.2.0
 */
public class ASTJPackage extends ASTJNode<PackageDeclaration> implements JPackage
{
  /**
   * @param packageDeclaration
   */
  public ASTJPackage(PackageDeclaration packageDeclaration)
  {
    super(packageDeclaration);
  }
  
  public String getName()
  {
    if (name == UNITIALIZED_STRING)
    {
      name = ASTFacadeHelper.toString(getASTNode().getName());
    }
    return name;
  }
  
  public void setName(String name)
  {
    this.name = name;
    setNodeProperty(getASTNode(), name, PackageDeclaration.NAME_PROPERTY, ASTNode.SIMPLE_NAME);
  }  
  
  /**
   * Returns original contents of the package declaration without the javadoc of the package.
   * @see org.eclipse.emf.codegen.merge.java.facade.ast.ASTJNode#getContents()
   */
  @Override
  public String getContents()
  {
    String content = super.getContents();
    return content != null ? fixPackageContent(content) : content;
  }
  
  /**
   * Fixes package contents to not include the header or the javadoc.
   * @param content
   * @return String
   * @see PackageDeclaration
   */
  protected String fixPackageContent(String content)
  {
    String header = getFacadeHelper().getCompilationUnit(this).getHeader();
    if (header != null && content.startsWith(header))
    {
      content = content.substring(header.length());
    }
    else
    {
      String javadoc = getFacadeHelper().toString(getASTNode().getJavadoc());
      if (javadoc != null && content.startsWith(javadoc))
      {
        content = content.substring(javadoc.length());
      }
    }
    return content.trim();
  }
}
