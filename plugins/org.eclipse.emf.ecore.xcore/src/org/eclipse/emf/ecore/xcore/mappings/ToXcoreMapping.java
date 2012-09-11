/**
 * Copyright (c) 2011-2012 Eclipse contributors and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package org.eclipse.emf.ecore.xcore.mappings;


import org.eclipse.emf.ecore.xcore.XNamedElement;


public class ToXcoreMapping extends AbstractMapping
{
  private XNamedElement xcoreElement;

  public XNamedElement getXcoreElement()
  {
    return xcoreElement;
  }

  public void setXcoreElement(XNamedElement xcoreElement)
  {
    this.xcoreElement = xcoreElement;
  }

  @Override
  public String toString()
  {
    return "[" + target + "->" + xcoreElement + "]";
  }
}
