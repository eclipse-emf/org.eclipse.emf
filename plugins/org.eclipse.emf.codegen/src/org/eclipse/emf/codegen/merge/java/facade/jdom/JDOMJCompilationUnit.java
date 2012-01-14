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
package org.eclipse.emf.codegen.merge.java.facade.jdom;


import org.eclipse.jdt.core.jdom.IDOMCompilationUnit;

import org.eclipse.emf.codegen.merge.java.facade.JCompilationUnit;


/**
 * @since 2.2.0
 */
@SuppressWarnings({"deprecation", "unchecked"})
public class JDOMJCompilationUnit extends JDOMJNode implements JCompilationUnit
{
  protected String originalContent;
  
  public JDOMJCompilationUnit(IDOMCompilationUnit compilationUnit)
  {
    super(compilationUnit);
  }

  @Override
  protected IDOMCompilationUnit getWrappedObject()
  {
    return (IDOMCompilationUnit)super.getWrappedObject();
  }
  
  public String getHeader()
  {
    return getWrappedObject().getHeader();
  }
  
  public void setHeader(String header)
  {
    getWrappedObject().setHeader(header);
  }
  
  public void setOriginalContent(String originalContent)
  {
    this.originalContent = originalContent;
  }
  
  public String getOriginalContent()
  {
    return originalContent;
  }
}
