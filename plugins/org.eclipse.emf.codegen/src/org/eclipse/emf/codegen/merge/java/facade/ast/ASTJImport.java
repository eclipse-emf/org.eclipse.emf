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
import org.eclipse.jdt.core.dom.ImportDeclaration;

import org.eclipse.emf.codegen.merge.java.facade.FacadeFlags;
import org.eclipse.emf.codegen.merge.java.facade.JImport;

/**
 * Wraps {@link ImportDeclaration} object.
 * 
 * @since 2.2.0
 */
public class ASTJImport extends ASTJNode<ImportDeclaration> implements JImport
{
  /**
   * @param importDeclaration
   */
  public ASTJImport(ImportDeclaration importDeclaration)
  {
    super(importDeclaration);
  }
  
  /**
   * Return <code>true</code> if original import was an "on demand" import.
   * 
   * @see org.eclipse.emf.codegen.merge.java.facade.JImport#isOnDemand()
   */
  public boolean isOnDemand()
  {
    // always returns original value
    return getASTNode().isOnDemand();
  }

  /**
   * Returns the name of the import ending with ".*" for on demand imports.
   * @see org.eclipse.emf.codegen.merge.java.facade.JNode#getName()
   */
  public String getName()
  {
    if (name == UNITIALIZED_STRING)
    {
      name = ASTFacadeHelper.toString(getASTNode().getName());
      if (isOnDemand() && name.lastIndexOf('*') < 0)
      {
        name = name + ".*";
      }
    }
    return name;
  }
  
  /**
   * Sets the name of the import to the given name and sets "on demand" property to false.
   * 
   * @see org.eclipse.emf.codegen.merge.java.facade.JNode#setName(java.lang.String)
   * @see org.eclipse.emf.codegen.merge.java.facade.JNode#getQualifiedName()
   */    
  public void setName(String name)
  {
    this.name = name;
    setNodeProperty(getASTNode(), name, ImportDeclaration.NAME_PROPERTY, ASTNode.SIMPLE_NAME);
    // name already contains ".*" - unset on demand property
    setNodeProperty(getASTNode(), false, ImportDeclaration.ON_DEMAND_PROPERTY);
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
    return getASTNode().isStatic() ? FacadeFlags.STATIC : super.getFlags();
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
    if (flags != getFlags())
    {
      setNodeProperty(getASTNode(), (flags & FacadeFlags.STATIC) == 0 ? Boolean.FALSE : Boolean.TRUE, ImportDeclaration.STATIC_PROPERTY);
    }    
  }
}
