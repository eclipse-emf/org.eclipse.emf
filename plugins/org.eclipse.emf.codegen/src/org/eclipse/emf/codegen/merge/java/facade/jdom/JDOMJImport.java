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
 * $Id: JDOMJImport.java,v 1.1 2006/01/18 20:42:15 marcelop Exp $
 */
package org.eclipse.emf.codegen.merge.java.facade.jdom;

import org.eclipse.jdt.core.jdom.IDOMImport;

import org.eclipse.emf.codegen.merge.java.facade.JImport;


/**
 * @since 2.2.0
 */
public class JDOMJImport extends JDOMJNode implements JImport
{
  /**
   * @param node
   */
  public JDOMJImport(IDOMImport imp)
  {
    super(imp);
  }

  protected IDOMImport getIDOMImport()
  {
    return (IDOMImport)getIDOMNode();
  }

  public boolean isOnDemand()
  {
    return getIDOMImport().isOnDemand();
  }

  public int getFlags()
  {
    return getIDOMImport().getFlags();
  }
  
  public void setFlags(int flags)
  {
    getIDOMImport().setFlags(flags);
  }
}
