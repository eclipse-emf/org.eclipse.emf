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
public abstract class JETJavaItem extends JETRootItem
{
  private int javaOffset;

  private int javaLength;

  JETJavaItem(JETMark start, JETMark stop)
  {
    super(start, stop);
  }

  public int getJavaOffset()
  {
    return javaOffset;
  }

  void setJavaOffset(int javaOffset)
  {
    this.javaOffset = javaOffset;
  }

  public int getJavaLength()
  {
    return javaLength;
  }

  public void setJavaLength(int javaLength)
  {
    this.javaLength = javaLength;
  }
}
