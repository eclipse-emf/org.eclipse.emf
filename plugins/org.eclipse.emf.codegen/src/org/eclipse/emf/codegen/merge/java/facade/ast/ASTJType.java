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
 * $Id: ASTJType.java,v 1.1 2006/05/10 20:33:16 marcelop Exp $
 */
package org.eclipse.emf.codegen.merge.java.facade.ast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import org.eclipse.jdt.core.dom.BodyDeclaration;
import org.eclipse.jdt.core.dom.SimpleType;
import org.eclipse.jdt.core.dom.Type;
import org.eclipse.jdt.core.dom.TypeDeclaration;

import org.eclipse.emf.codegen.merge.java.facade.FacadeFlags;
import org.eclipse.emf.codegen.merge.java.facade.JType;

/**
 * @since 2.2.0
 */
public class ASTJType extends ASTJMember implements JType
{
  public ASTJType(TypeDeclaration typeDeclaration)
  {
    super(typeDeclaration);
  }
  
  protected TypeDeclaration getASTTypeDeclaration()
  {
    return (TypeDeclaration)getASTNode();
  }

  public String getSuperclass()
  {
    return getName(getASTTypeDeclaration().getSuperclassType());
  }

  public void setSuperclass(String superclassName)
  {
  }

  public String[] getSuperInterfaces()
  {
    List names = new ArrayList();
    List types = getASTTypeDeclaration().superInterfaceTypes();
    for (Iterator i = types.iterator(); i.hasNext();)
    {
      Type type = (Type)i.next();
      String name = getName(type);
      if (name == null)
      {
        throw new RuntimeException("Invalid super interface type");
      }
      names.add(name);
    }
    return (String[])names.toArray(new String [names.size()]);
  }
  
  protected String getName(Type type)
  {
    return type instanceof SimpleType ? ((SimpleType)type).getName().getFullyQualifiedName() : null;
  }

  public void setSuperInterfaces(String[] interfaceNames)
  {
  }

  public void addSuperInterface(String interfaceName)
  {
  }

  public String[] getTypeParameters()
  {
    return new String[0];
  }

  public String getName()
  {
    return ASTFacadeHelper.toString(getASTTypeDeclaration().getName());
  }
  
  protected String computeQualifiedName()
  {
    return computeQualifiedName(this);
  }
  
  public int getFlags()
  {
    return getASTTypeDeclaration().isInterface() ? 
      super.getFlags() | FacadeFlags.INTERFACE :
      super.getFlags();
  }

  public List getChildren()
  {
    List children = new ArrayList();
    for (Iterator i = getASTTypeDeclaration().bodyDeclarations().iterator(); i.hasNext();)
    {
      BodyDeclaration bodyDeclaration = (BodyDeclaration)i.next();
      children.add(getFacadeHelper().convertToNode(bodyDeclaration));
    }
    return children.isEmpty() ? Collections.EMPTY_LIST : Collections.unmodifiableList(children);
  }
}
