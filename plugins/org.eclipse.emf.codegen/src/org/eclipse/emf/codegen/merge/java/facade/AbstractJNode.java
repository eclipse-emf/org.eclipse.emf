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
 * $Id: AbstractJNode.java,v 1.1 2006/01/18 20:42:16 marcelop Exp $
 */
package org.eclipse.emf.codegen.merge.java.facade;


/**
 * @since 2.2.0
 */
public abstract class AbstractJNode implements JNode
{
  protected static final String[] EMPTY_STRING_ARRAY = new String[0];
  
  protected FacadeHelper facadeHelper;
  protected Object wrappedObject;
  protected String qualifiedName;
  
  protected AbstractJNode(Object wrappedObject)
  {
    this.wrappedObject = wrappedObject;
  }
  
  public void dispose()
  {
    wrappedObject = null;
    facadeHelper = null;
  }
  
  protected Object getWrappedObject()
  {
    return wrappedObject;
  }
  
  public void setFacadeHelper(FacadeHelper facadeHelper)
  {
    this.facadeHelper = facadeHelper;
  }

  public FacadeHelper getFacadeHelper()
  {
    return facadeHelper;
  }
  
  public String getQualifiedName()
  {
    if (qualifiedName == null)
    {
      qualifiedName = computeQualifiedName();
    }
    return qualifiedName;
  }

  protected String computeQualifiedName()
  {
    JNode parent = getParent();
    return parent == null || parent instanceof JCompilationUnit ?
      getName() :
      parent.getQualifiedName() + "." + getName();
  }
  
  protected String getName(JInitializer initializer)
  {
    JNode parent = initializer.getParent();
    if (parent != null)
    {
      int index = getFacadeHelper().getChildren(parent, JInitializer.class).indexOf(initializer);
      return parent.getName() + "." +  index;
    }
    return null;    
  }
  
  protected String computeQualifiedName(JInitializer initializer)
  {
    JNode parent = initializer.getParent();
    if (parent != null)
    {
      int index = getFacadeHelper().getChildren(parent, JInitializer.class).indexOf(initializer);
      return parent.getQualifiedName() + "." +  index;
    }
    return null;
  }  
  
  protected String computeQualifiedName(JType type)
  {
    JNode parent = type.getParent();
    if (parent instanceof JType)
    {
      return parent.getQualifiedName() + "." + type.getName();
    }
      
    JPackage jPackage = getFacadeHelper().getPackage(this);
    return jPackage != null ? jPackage.getName() + "." + type.getName() : type.getName();
  }
}
