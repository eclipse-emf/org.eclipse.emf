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
 * $Id: FacadeVisitor.java,v 1.2 2006/11/01 21:22:02 marcelop Exp $
 */
package org.eclipse.emf.codegen.merge.java.facade;


public class FacadeVisitor
{
  protected JNode rootNode;
  
  public void start(JNode node)
  {
    this.rootNode = node;
    visit(getRootNode());
  }
  
  protected JNode getRootNode()
  {
    return rootNode;
  }
  
  protected void visit(JNode node)
  {
    beforeVisit(node);
    boolean visitChildren = dispatch(node);
    afterVisit(node);
    if (visitChildren)
    {
      visitChildren(node);
      afterVisitChildren(node);
    }
  }
  
  protected void beforeVisit(JNode node)
  {    
  }

  protected void afterVisit(JNode node)
  {    
  }
  
  protected void afterVisitChildren(JNode node)
  {    
  }
  
  protected boolean defaultVisitReturn(JNode node)
  {
    return true;
  }
    
  protected boolean dispatch(JNode node)
  {
    if (node instanceof JCompilationUnit)
    {
      return visit((JCompilationUnit)node);
    }
    else if (node instanceof JField)
    {
      return visit((JField)node);
    }
    else if (node instanceof JImport)
    {
      return visit((JImport)node);
    }
    else if (node instanceof JInitializer)
    {
      return visit((JInitializer)node);
    }
    else if (node instanceof JMethod)
    {
      return visit((JMethod)node);
    }
    else if (node instanceof JPackage)
    {
      return visit((JPackage)node);
    }
    else if (node instanceof JType)
    {
      return visit((JType)node);
    }
    else
    {
      return false;
    }
  }
    
  protected void visitChildren(JNode node)
  {
    for (JNode child : node.getChildren())
    {
      visit(child);
    }
  }
  
  protected boolean visit(JCompilationUnit compilationUnit)
  {
    return defaultVisitReturn(compilationUnit);
  }
  
  protected boolean visit(JField field)
  {
    return defaultVisitReturn(field);
  }  

  protected boolean visit(JImport jImport)
  {
    return defaultVisitReturn(jImport);
  }
  
  protected boolean visit(JInitializer initializer)
  {
    return defaultVisitReturn(initializer);
  }  

  protected boolean visit(JMethod method)
  {
    return defaultVisitReturn(method);
  }
  
  protected boolean visit(JPackage jPackage)
  {
    return defaultVisitReturn(jPackage);
  }  

  protected boolean visit(JType type)
  {
    return defaultVisitReturn(type);
  }  
}
