/**
 * Copyright (c) 2019 Eclipse contributors and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 */
package org.eclipse.emf.codegen.jet;


/**
 * @since 2.19
 */
public class JETValueElementItem extends JETSubItem
{
  private String value;

  JETValueElementItem(JETMark start, JETMark stop, String value)
  {
    super(start, stop);
    this.value = value;
  }

  public String getValue()
  {
    return value;
  }
}
