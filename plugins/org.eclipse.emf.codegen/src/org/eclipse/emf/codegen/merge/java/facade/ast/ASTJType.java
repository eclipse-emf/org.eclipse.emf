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
 * $Id: ASTJType.java,v 1.4 2006/12/06 03:48:44 marcelop Exp $
 */
package org.eclipse.emf.codegen.merge.java.facade.ast;

import org.eclipse.jdt.core.dom.ASTNode;
import org.eclipse.jdt.core.dom.TypeDeclaration;

import org.eclipse.emf.codegen.merge.java.facade.FacadeFlags;
import org.eclipse.emf.codegen.merge.java.facade.JType;

/**
 * Wraps {@link TypeDeclaration} object.
 * 
 * @since 2.2.0
 */
public class ASTJType extends ASTJAbstractType<TypeDeclaration> implements JType
{
  /**
   * @param abstractTypeDeclaration
   */
  protected ASTJType(TypeDeclaration abstractTypeDeclaration)
  {
    super(abstractTypeDeclaration);
  }

  /**
   * Returns the original superclass.
   * 
   * @see org.eclipse.emf.codegen.merge.java.facade.JType#getSuperclass()
   */
  public String getSuperclass()
  {
    return getFacadeHelper().toString(getASTNode().getSuperclassType());
  }

  /**
   * Sets the superclass.
   * <p>
   * Note that <code>getSuperclass()</code> will not return the new value.
   * 
   * @see org.eclipse.emf.codegen.merge.java.facade.JType#setSuperclass(java.lang.String)
   */
  public void setSuperclass(String superclassName)
  {
    setNodeProperty(getASTNode(), superclassName, TypeDeclaration.SUPERCLASS_TYPE_PROPERTY, ASTNode.SIMPLE_TYPE);
  }

  /**
   * Returns the array of interfaces in <code>implements</code> clause.
   * 
   * @see org.eclipse.emf.codegen.merge.java.facade.JType#getSuperInterfaces()
   */
  @SuppressWarnings("unchecked")
  public String[] getSuperInterfaces()
  {
    return convertASTNodeListToStringArray(getASTNode().superInterfaceTypes());
  }

  /**
   * Sets the list of interfaces in the <code>implements</code> list to the given array.
   * <p>
   * Note that <code>getSuperInterfaces()</code> will not return the new value.
   * 
   * @see org.eclipse.emf.codegen.merge.java.facade.JType#setSuperInterfaces(java.lang.String[])
   */
  public void setSuperInterfaces(String[] interfaceNames)
  {
    setListNodeProperty(getASTNode(), interfaceNames, TypeDeclaration.SUPER_INTERFACE_TYPES_PROPERTY, ASTNode.SIMPLE_TYPE);
  }

  /**
   * Adds the interface to the <code>implements</code> list.
   * <p>
   * Note that <code>getSuperInterfaces()</code> will not return the new value.
   * 
   * @see org.eclipse.emf.codegen.merge.java.facade.JType#addSuperInterface(java.lang.String)
   */
  public void addSuperInterface(String superInterface)
  {
    addValueToListProperty(getASTNode(), superInterface, TypeDeclaration.SUPER_INTERFACE_TYPES_PROPERTY, ASTNode.SIMPLE_TYPE);
  }

  /* (non-Javadoc)
   * @see org.eclipse.emf.codegen.merge.java.facade.JType#getTypeParameters()
   */
  @SuppressWarnings("unchecked")
  public String[] getTypeParameters()
  {
    return convertASTNodeListToStringArray(getASTNode().typeParameters());
  }
  
  /**
   * Returns the original flags of the type, including the {@link FacadeFlags#INTERFACE} flag.
   * <p>
   * Note that the {@link FacadeFlags#INTERFACE} flag can not be set.
   * 
   * @see org.eclipse.emf.codegen.merge.java.facade.ast.ASTJMember#getFlags()
   */
  @Override
  public int getFlags()
  {
    return getASTNode().isInterface() ? 
      super.getFlags() | FacadeFlags.INTERFACE :
      super.getFlags();
  }

  /* (non-Javadoc)
   * @see org.eclipse.emf.codegen.merge.java.facade.JType#setTypeParameters(java.lang.String[])
   */
  public void setTypeParameters(String[] typeParameters)
  {
    setListNodeProperty(getASTNode(), typeParameters, TypeDeclaration.TYPE_PARAMETERS_PROPERTY, ASTNode.SIMPLE_TYPE);
  }
}
