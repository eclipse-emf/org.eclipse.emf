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

import org.eclipse.jdt.core.jdom.IDOMPackage;

import org.eclipse.emf.codegen.merge.java.facade.JPackage;


/**
 * @since 2.2.0
 */
@SuppressWarnings({"deprecation", "unchecked"})
public class JDOMJPackage extends JDOMJNode implements JPackage
{
  public JDOMJPackage(IDOMPackage pck)
  {
    super(pck);
  }

  @Override
  protected IDOMPackage getWrappedObject()
  {
    return (IDOMPackage)super.getWrappedObject();
  }
}
