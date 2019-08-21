/**
 * Copyright (c) 2019 Eclipse contributors and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 */
package org.eclipse.emf.codegen.jet;


import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;


/**
 * @since 2.19
 */
public final class JETDirectiveItem extends JETRootItem
{
  public static final String RESOLVED_INCLUDE = "include";

  public static final String RESOLVED_INCLUDE_FILE_ID = "include-file-id";

  public static final String RESOLVED_INCLUDE_START = "include-start";

  public static final String SECTION_START = "section-start";

  public static final String SECTION_END = "section-end";

  public static final String SECTION_OTHER_END = "section-other-end";

  public static final String SECTION_OWNER = "section-owner";

  public static final String RESOLVED_INCLUDE_SUCCESS = "include-success";

  public static final String SKELETON = "skeleton";

  public static final String RESOLVED_SKELETON = "resolved-skeleton";

  public static final String DUPLICATE_JET_DIRECTIVE = "duplicate-jet-directive";

  public static final String MISSING_JET_DIRECTIVE = "missing-jet-directive";

  private final JETSubItem nameItem;

  private final JETAttributeListItem attributes;

  private Map<String, Object> data;

  JETDirectiveItem(JETMark start, JETMark stop, JETSubItem nameItem, JETAttributeListItem atributes)
  {
    super(start, stop);
    this.nameItem = nameItem;
    this.attributes = atributes;
    attributes.setParent(this);
    nameItem.setParent(this);
  }

  public final JETItem getNameItem()
  {
    return nameItem;
  }

  public final JETAttributeListItem getAttributes()
  {
    return attributes;
  }

  public final JETAttributeItem getAttribute(String attributeName)
  {
    return attributes.getAttribute(attributeName);
  }

  public final Object getData(String key)
  {
    return data == null ? null : data.get(key);
  }

  public final void setData(String key, Object value)
  {
    if (data == null)
    {
      data = new LinkedHashMap<String, Object>();
    }
    data.put(key, value);
  }

  @Override
  public final List<JETSubItem> getChildren()
  {
    return Arrays.asList(nameItem, attributes);
  }
}
