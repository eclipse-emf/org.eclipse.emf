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
public class JETValueItem extends JETSubItem
{
  private final String value;

  private final List<JETValueElementItem> elements;

  JETValueItem(JETMark start, JETMark stop, String value, List<JETValueElementItem> elements)
  {
    super(start, stop);
    this.value = value;
    this.elements = elements;
    for (JETSubItem child : elements)
    {
      child.setParent(this);
    }
  }

  public String getValue()
  {
    return value;
  }

  @Override
  @SuppressWarnings("unchecked")
  public List<JETSubItem> getChildren()
  {
    return (List<JETSubItem>)(List<?>)Collections.unmodifiableList(elements);
  }

  public List<JETValueElementItem> getElements()
  {
    return elements;
  }
}
