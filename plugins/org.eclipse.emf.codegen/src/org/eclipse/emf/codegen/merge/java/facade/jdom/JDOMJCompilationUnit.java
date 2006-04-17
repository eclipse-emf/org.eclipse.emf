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
 * $Id: JDOMJCompilationUnit.java,v 1.3 2006/04/17 21:33:50 marcelop Exp $
 */
package org.eclipse.emf.codegen.merge.java.facade.jdom;


import org.eclipse.jdt.core.jdom.IDOMCompilationUnit;

import org.eclipse.emf.codegen.merge.java.facade.JCompilationUnit;


/**
 * @since 2.2.0
 */
public class JDOMJCompilationUnit extends JDOMJNode implements JCompilationUnit
{
  /**
   * @param node
   */
  public JDOMJCompilationUnit(IDOMCompilationUnit compilationUnit)
  {
    super(compilationUnit);
  }

  protected IDOMCompilationUnit getIDOMCompilationUnit()
  {
    return (IDOMCompilationUnit)getIDOMNode();
  }
  
  public String getHeader()
  {
    return getIDOMCompilationUnit().getHeader();
  }
  
  public void setHeader(String header)
  {
    getIDOMCompilationUnit().setHeader(header);
  }
}
