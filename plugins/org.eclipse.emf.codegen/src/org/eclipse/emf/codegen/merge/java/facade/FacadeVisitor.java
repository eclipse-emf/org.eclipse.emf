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
    // Subclasses may override this method
  }

  protected void afterVisit(JNode node)
  {    
    // Subclasses may override this method
  }
  
  protected void afterVisitChildren(JNode node)
  {
    // Subclasses may override this method
  }
  
  protected boolean dispatch(JNode node)
  {
    if (node instanceof JAnnotation)
    {
      return visit((JAnnotation)node);
    }
    else if (node instanceof JMethod)
    {
      return visit((JMethod)node);
    }
    else if (node instanceof JField)
    {
      return visit((JField)node);
    }
    else if (node instanceof JImport)
    {
      return visit((JImport)node);
    }
    else if (node instanceof JEnumConstant)
    {
      return visit((JEnumConstant)node);
    }
    else if (node instanceof JPackage)
    {
      return visit((JPackage)node);
    }
    else if (node instanceof JCompilationUnit)
    {
      return visit((JCompilationUnit)node);
    }
    else if (node instanceof JAbstractType)
    {
      return visit((JAbstractType)node);
    }
    else if (node instanceof JAnnotationTypeMember)
    {
      return visit((JAnnotationTypeMember)node);
    }   
    else if (node instanceof JInitializer)
    {
      return visit((JInitializer)node);
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
  
  protected boolean basicVisit(JNode node)
  {
    return true;
  }

  protected boolean visit(JCompilationUnit compilationUnit)
  {
    return basicVisit(compilationUnit);
  }
  
  protected boolean visit(JField field)
  {
    return basicVisit(field);
  }  

  protected boolean visit(JImport jImport)
  {
    return basicVisit(jImport);
  }
  
  protected boolean visit(JInitializer initializer)
  {
    return basicVisit(initializer);
  }  

  protected boolean visit(JMethod method)
  {
    return basicVisit(method);
  }
  
  protected boolean visit(JPackage jPackage)
  {
    return basicVisit(jPackage);
  }  

  protected boolean visit(JAbstractType abstractType)
  {
    return basicVisit(abstractType);
  }
  
  protected boolean visit(JAnnotation annotation)
  {
    return basicVisit(annotation);
  }  
  
  protected boolean visit(JAnnotationTypeMember annotationTypeMember)
  {
    return basicVisit(annotationTypeMember);
  }   

  protected boolean visit(JEnumConstant enumConstant)
  {
    return basicVisit(enumConstant);
  }    
}
