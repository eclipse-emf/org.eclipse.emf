/**
 * <copyright>
 *
 * Copyright (c) 2010 Ed Merks and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *   Ed Merks - Initial API and implementation
 *
 * </copyright>
 *
 * $Id: BasicEList_CustomFieldSerializer.java,v 1.1 2010/04/28 14:49:28 emerks Exp $
 */
package org.eclipse.emf.common.util;

import com.google.gwt.user.client.rpc.SerializationException;
import com.google.gwt.user.client.rpc.SerializationStreamReader;
import com.google.gwt.user.client.rpc.SerializationStreamWriter;


public final class BasicEList_CustomFieldSerializer
{
  @SuppressWarnings("unchecked")
  public static <E> void deserialize(SerializationStreamReader reader, BasicEList<E> eList) throws SerializationException
  {
    reader.readObject();

    int arrayLength = reader.readInt();
    if (arrayLength > 0)
    {
      try
      {
        eList.data = eList.newData(arrayLength);
      }
      catch (Throwable exception)
      {
        eList.data = new Object[arrayLength];
      }

      for (int i = 0; i < eList.size; ++i)
      {
        eList.didAdd(i, eList.assign(i, (E) reader.readObject()));
      }
    }
  }

  public static <E> void serialize(SerializationStreamWriter writer, BasicEList<E> eList) throws SerializationException
  {
    writer.writeObject(eList);

    if (eList.data == null)
    {
      writer.writeInt(0);
    }
    else
    {
      writer.writeInt(eList.data.length);
      for (int i = 0; i < eList.size; ++i)
      {
        writer.writeObject(eList.data[i]);
      }
    }
  }
}

