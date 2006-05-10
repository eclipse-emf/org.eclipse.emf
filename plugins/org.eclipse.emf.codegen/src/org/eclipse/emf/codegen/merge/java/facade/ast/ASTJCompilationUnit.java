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
 * $Id: ASTJCompilationUnit.java,v 1.1 2006/05/10 20:33:16 marcelop Exp $
 */
package org.eclipse.emf.codegen.merge.java.facade.ast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import org.eclipse.jdt.core.dom.CompilationUnit;
import org.eclipse.jdt.core.dom.ImportDeclaration;
import org.eclipse.jdt.core.dom.Javadoc;
import org.eclipse.jdt.core.dom.PackageDeclaration;
import org.eclipse.jdt.core.dom.TypeDeclaration;

import org.eclipse.emf.codegen.merge.java.facade.JCompilationUnit;
import org.eclipse.emf.codegen.merge.java.facade.JType;

/**
 * @since 2.2.0
 */
public class ASTJCompilationUnit extends ASTJNode implements JCompilationUnit
{
  public static final String PROPERY_NAME = "ASTJCompilationUnit.name";
  
  protected char[] originalContents;
  
  public ASTJCompilationUnit(CompilationUnit compilationUnit)
  {
    super(compilationUnit);
  }
  
  protected CompilationUnit getASTCompilationUnit()
  {
    return (CompilationUnit)getASTNode();
  }
  
  public void setOriginalContents(char[] originalContents)
  {
    this.originalContents = originalContents;
  }  

  public char[] getOriginalContents()
  {
    return originalContents;
  }
  
  public String getName()
  {
    JType type = getFacadeHelper().getMainType(this);
    return type != null ? type.getName() + ".java" : (String)getASTCompilationUnit().getProperty(PROPERY_NAME);
  }

  public List getChildren()
  {
    List children = new ArrayList();
    CompilationUnit astCompilationUnit = getASTCompilationUnit();
    PackageDeclaration astPackage = astCompilationUnit.getPackage(); 
    if (astPackage != null)
    {
      children.add(getFacadeHelper().convertToNode(astPackage));
    }
    for (Iterator i = astCompilationUnit.imports().iterator(); i.hasNext();)
    {
      ImportDeclaration astImport = (ImportDeclaration)i.next();
      children.add(getFacadeHelper().convertToNode(astImport));
    }
    for (Iterator i = astCompilationUnit.types().iterator(); i.hasNext(); )
    {
      TypeDeclaration astType = (TypeDeclaration)i.next();
      children.add(getFacadeHelper().convertToNode(astType));
    }
    return children.isEmpty() ? Collections.EMPTY_LIST : Collections.unmodifiableList(children);
  }
  
  protected String computeQualifiedName()
  {
    return getName();
  }  

  public String getHeader()
  {
    return toString((Javadoc)getASTCompilationUnit().getCommentList().get(0));
  }
  
  public void setHeader(String header)
  {
  }
}
