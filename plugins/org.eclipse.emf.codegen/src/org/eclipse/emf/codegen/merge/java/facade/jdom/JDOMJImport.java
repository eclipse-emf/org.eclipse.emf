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
 * $Id: JDOMJImport.java,v 1.3 2007/06/12 20:56:06 emerks Exp $
 */
package org.eclipse.emf.codegen.merge.java.facade.jdom;

import org.eclipse.jdt.core.jdom.IDOMImport;

import org.eclipse.emf.codegen.merge.java.facade.JImport;


/**
 * @since 2.2.0
 */
@SuppressWarnings({"deprecation", "unchecked"})
public class JDOMJImport extends JDOMJNode implements JImport
{
  public JDOMJImport(IDOMImport imp)
  {
    super(imp);
  }

  @Override
  protected IDOMImport getWrappedObject()
  {
    return (IDOMImport)super.getWrappedObject();
  }

  public boolean isOnDemand()
  {
    return getWrappedObject().isOnDemand();
  }

  @Override
  public int getFlags()
  {
    return getWrappedObject().getFlags();
  }
  
  @Override
  public void setFlags(int flags)
  {
    getWrappedObject().setFlags(flags);
  }
}
