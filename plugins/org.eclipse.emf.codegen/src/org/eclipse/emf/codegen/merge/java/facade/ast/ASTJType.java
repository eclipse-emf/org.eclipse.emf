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

import java.util.ArrayList;
import java.util.List;

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
   * Cached super class name.
   * @see #getSuperclass()
   * @see #setSuperclass(String)
   */
  protected String superclassName = UNITIALIZED_STRING;
 
  /**
   * Array of cached super interfaces. All currently set super interfaces is a combination of
   * this array and {@link #addedSuperInterfaces}.
   */
  protected String[] superInterfaces = EMPTY_STRING_ARRAY;
  
  /**
   * List of added interfaces by calling {@link #addSuperInterface(String)}.
   * This list does not include existing interfaces, nor interfaces set by {@link #setSuperInterfaces(String[])}
   */
  protected List<String> addedSuperInterfaces = null;  
  
  /**
   * Cached type parameters
   * @see #getTypeParameters()
   * @see #setTypeParameters(String[])
   */
  protected String[] typeParameters = EMPTY_STRING_ARRAY;
  
  /**
   * @param abstractTypeDeclaration
   */
  protected ASTJType(TypeDeclaration abstractTypeDeclaration)
  {
    super(abstractTypeDeclaration);
  }
  
  @Override
  public void dispose()
  {
    superclassName = null;
    superInterfaces = null;
    typeParameters = null;
    if (addedSuperInterfaces != null)
    {
      addedSuperInterfaces.clear();
      addedSuperInterfaces = null;
    }
    
    super.dispose();
  }

  public String getSuperclass()
  {
    if (superclassName == UNITIALIZED_STRING)
    {
      superclassName = getFacadeHelper().toString(getASTNode().getSuperclassType());
    }
    return superclassName;
  }

  public void setSuperclass(String superclassName)
  {
    this.superclassName = superclassName;
    setNodeProperty(getASTNode(), superclassName, TypeDeclaration.SUPERCLASS_TYPE_PROPERTY, ASTNode.SIMPLE_TYPE);
  }

  @SuppressWarnings("unchecked")
  public String[] getSuperInterfaces()
  {
    if (superInterfaces == EMPTY_STRING_ARRAY)
    {
      superInterfaces = convertASTNodeListToStringArray(getASTNode().superInterfaceTypes());
    }
    
    // add added super interfaces to the array
    superInterfaces = combineArrayAndList(superInterfaces, addedSuperInterfaces);    
    return superInterfaces;
  }

  public void setSuperInterfaces(String[] interfaceNames)
  {
    this.superInterfaces = interfaceNames;
    this.addedSuperInterfaces = null;    
    setListNodeProperty(getASTNode(), interfaceNames, TypeDeclaration.SUPER_INTERFACE_TYPES_PROPERTY, ASTNode.SIMPLE_TYPE);
  }

  public void addSuperInterface(String superInterface)
  {
    if (addedSuperInterfaces == null)
    {
      addedSuperInterfaces = new ArrayList<String>();
    }
    addedSuperInterfaces.add(superInterface);    
    addValueToListProperty(getASTNode(), superInterface, TypeDeclaration.SUPER_INTERFACE_TYPES_PROPERTY, ASTNode.SIMPLE_TYPE);
  }

  @SuppressWarnings("unchecked")
  public String[] getTypeParameters()
  {
    if (typeParameters == EMPTY_STRING_ARRAY)
    {
      typeParameters = convertASTNodeListToStringArray(getASTNode().typeParameters());
    }
    return typeParameters;
  }
  
  public void setTypeParameters(String[] typeParameters)
  {
    this.typeParameters = typeParameters;
    setListNodeProperty(getASTNode(), typeParameters, TypeDeclaration.TYPE_PARAMETERS_PROPERTY, ASTNode.SIMPLE_TYPE);
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
}
