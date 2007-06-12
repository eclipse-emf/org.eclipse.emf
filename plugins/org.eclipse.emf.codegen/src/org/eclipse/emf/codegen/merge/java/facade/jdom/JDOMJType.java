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
 * $Id: JDOMJType.java,v 1.4 2007/06/12 20:56:06 emerks Exp $
 */
package org.eclipse.emf.codegen.merge.java.facade.jdom;


import org.eclipse.jdt.core.jdom.IDOMType;

import org.eclipse.emf.codegen.merge.java.facade.JPackage;
import org.eclipse.emf.codegen.merge.java.facade.JType;


/**
 * @since 2.2.0
 */
@SuppressWarnings({"deprecation", "unchecked"})
public class JDOMJType extends JDOMJMember implements JType
{
  protected JPackage jPackage;

  public JDOMJType(IDOMType type)
  {
    super(type);
  }

  @Override
  protected IDOMType getWrappedObject()
  {
    return (IDOMType)super.getWrappedObject();
  }

  public String getSuperclass()
  {
    return getWrappedObject().getSuperclass();
  }
  
  public void setSuperclass(String superclassName)
  {
    getWrappedObject().setSuperclass(superclassName);
  }

  public String[] getSuperInterfaces()
  {
    String[] ret = getWrappedObject().getSuperInterfaces();
    return ret == null ? EMPTY_STRING_ARRAY : ret;
  }
  
  public void setSuperInterfaces(String[] interfaceNames)
  {
    getWrappedObject().setSuperInterfaces(interfaceNames);
  }
  
  public void addSuperInterface(String interfaceName)
  {
    getWrappedObject().addSuperInterface(interfaceName);
  }

  public String[] getTypeParameters()
  {
    String[] ret = getWrappedObject().getTypeParameters();
    return ret == null ? EMPTY_STRING_ARRAY : ret;
  }

  @Override
  protected String computeQualifiedName()
  {
    return computeQualifiedName(this);
  }

  public void setTypeParameters(String[] typeParameters)
  {
    // not supported in JDOM    
  }
}
