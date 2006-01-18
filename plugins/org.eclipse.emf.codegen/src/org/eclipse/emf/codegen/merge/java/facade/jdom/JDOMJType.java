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
 * $Id: JDOMJType.java,v 1.1 2006/01/18 20:42:15 marcelop Exp $
 */
package org.eclipse.emf.codegen.merge.java.facade.jdom;


import org.eclipse.jdt.core.jdom.IDOMType;

import org.eclipse.emf.codegen.merge.java.facade.JNode;
import org.eclipse.emf.codegen.merge.java.facade.JPackage;
import org.eclipse.emf.codegen.merge.java.facade.JType;


/**
 * @since 2.2.0
 */
public class JDOMJType extends JDOMJMember implements JType
{
  protected JPackage jPackage;

  /**
   * @param member
   */
  public JDOMJType(IDOMType type)
  {
    super(type);
  }

  protected IDOMType getIDOMType()
  {
    return (IDOMType)getIDOMNode();
  }

  public String getSuperclass()
  {
    return getIDOMType().getSuperclass();
  }
  
  public void setSuperclass(String superclassName)
  {
    getIDOMType().setSuperclass(superclassName);
  }

  public String[] getSuperInterfaces()
  {
    String[] ret = getIDOMType().getSuperInterfaces();
    return ret == null ? EMPTY_STRING_ARRAY : ret;
  }
  
  public void setSuperInterfaces(String[] interfaceNames)
  {
    getIDOMType().setSuperInterfaces(interfaceNames);
  }
  
  public void addSuperInterface(String interfaceName)
  {
    getIDOMType().addSuperInterface(interfaceName);
  }

  public String[] getTypeParameters()
  {
    String[] ret = getIDOMType().getTypeParameters();
    return ret == null ? EMPTY_STRING_ARRAY : ret;
  }

  protected String computeQualifiedName()
  {
    return computeQualifiedName(this);
  }
}
