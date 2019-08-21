/**
 * Copyright (c) 2019 Eclipse contributors and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 */
package org.eclipse.emf.codegen.jet;


import java.util.Collections;
import java.util.List;


/**
 * @since 2.19
 */
public class JETTokenItem extends JETSubItem
{
  private final String token;

  private final JETValueItem valueItem;

  JETTokenItem(JETMark start, JETMark stop, String token)
  {
    this(start, stop, token, null);
  }

  public JETTokenItem(JETMark start, JETMark stop, String token, JETValueItem valueItem)
  {
    super(start, stop);
    this.token = token;
    this.valueItem = valueItem;
    if (valueItem != null)
    {
      valueItem.setParent(this);
    }
  }

  public final String getToken()
  {
    return token;
  }

  public JETValueItem getValueItem()
  {
    return valueItem;
  }

  @Override
  public List<JETSubItem> getChildren()
  {
    return valueItem == null ? Collections.<JETSubItem> emptyList() : Collections.<JETSubItem> singletonList(valueItem);
  }
}
