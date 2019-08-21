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
public class JETAttributeListItem extends JETSubItem
{
  private final List<JETAttributeItem> attributes;

  JETAttributeListItem(JETMark start, JETMark stop, List<JETAttributeItem> attributes)
  {
    super(start, stop);
    this.attributes = attributes;
    for (JETAttributeItem jetAttributeItem : attributes)
    {
      jetAttributeItem.setParent(this);
    }
  }

  public final List<JETAttributeItem> getAttributes()
  {
    return Collections.unmodifiableList(attributes);
  }

  public final JETAttributeItem getAttribute(String attributeName)
  {
    for (JETAttributeItem jetAttribute : attributes)
    {
      JETTokenItem nameToken = jetAttribute.getNameToken();
      if (attributeName.equals(nameToken.getToken()))
      {
        return jetAttribute;
      }
    }
    return null;
  }

  @Override
  @SuppressWarnings("unchecked")
  public final List<JETSubItem> getChildren()
  {
    return (List<JETSubItem>)(List<?>)getAttributes();
  }
}
