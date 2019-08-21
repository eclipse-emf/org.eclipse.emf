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
public class JETSubItem extends JETItem
{
  private JETItem parent;

  JETSubItem(JETMark start, JETMark stop)
  {
    super(start, stop);
  }

  @Override
  protected JETSubItem asSubItem()
  {
    return this;
  }

  protected void setParent(JETItem parent)
  {
    this.parent = parent;
  }

  @Override
  public JETItem getParent()
  {
    return parent;
  }

  @Override
  public JETItem getRoot()
  {
    return parent == null ? this : parent.getRoot();
  }
}
