/**
 * Copyright (c) 2019 Eclipse contributors and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 */
package org.eclipse.emf.codegen.jet;


import java.util.Arrays;
import java.util.List;


/**
 * @since 2.19
 */
public class JETAttributeItem extends JETSubItem
{
  private final JETTokenItem nameToken;

  private final JETTokenItem valueToken;

  JETAttributeItem(JETMark start, JETMark stop, JETTokenItem nameToken, JETTokenItem valueToken)
  {
    super(start, stop);
    this.nameToken = nameToken;
    this.valueToken = valueToken;
    nameToken.setParent(this);
    valueToken.setParent(this);
  }

  public final JETTokenItem getNameToken()
  {
    return nameToken;
  }

  public final JETTokenItem getValueToken()
  {
    return valueToken;
  }

  @Override
  public final List<JETSubItem> getChildren()
  {
    return Arrays.<JETSubItem> asList(nameToken, valueToken);
  }
}
