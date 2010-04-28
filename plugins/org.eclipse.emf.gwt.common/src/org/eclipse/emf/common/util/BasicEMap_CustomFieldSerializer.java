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
 * $Id: BasicEMap_CustomFieldSerializer.java,v 1.1 2010/04/28 14:49:30 emerks Exp $
 */
package org.eclipse.emf.common.util;

import org.eclipse.emf.common.util.BasicEMap.Entry;

import com.google.gwt.user.client.rpc.SerializationException;
import com.google.gwt.user.client.rpc.SerializationStreamReader;
import com.google.gwt.user.client.rpc.SerializationStreamWriter;

public final class BasicEMap_CustomFieldSerializer
{
  @SuppressWarnings("unchecked")
  public static <K, V> void deserialize(SerializationStreamReader reader, BasicEMap<K, V> eMap) throws SerializationException
  {
    reader.readObject();

    eMap.size = reader.readInt();
    eMap.initializeDelegateEList();
  
    // Restore the capacity, if there was any.
    //
    int capacity = reader.readInt();
    if (capacity > 0)
    {
        eMap.entryData = eMap.newEntryData(capacity);
    
      // Read all size number of entryData.
      //
      for (int i = 0; i < eMap.size; ++i) 
      {
        eMap.put((K)reader.readObject(), (V)reader.readObject());
      }
    }
  }

  public static <K, V> void serialize(SerializationStreamWriter writer, BasicEMap<K, V> eMap) throws SerializationException
  {
    writer.writeObject(eMap);

    writer.writeInt(eMap.size);
    if (eMap.entryData == null)
    {
      writer.writeInt(0);
    }
    else
    {
      // Write the capacity.
      //
      writer.writeInt(eMap.entryData.length);
  
      // Write all the entryData; there will be size of them.
      //
      for (int i = 0; i < eMap.entryData.length; ++i)
      {
        BasicEList<Entry<K, V>> eList = eMap.entryData[i];
        if (eList != null)
        {
          Object [] entries = eList.data;
          int size = eList.size;
          for (int j = 0; j < size; ++j)
          {
            @SuppressWarnings("unchecked")
            Entry<Object, Object> entry = (Entry<Object, Object>)entries[j];
            writer.writeObject(entry.getKey());
            writer.writeObject(entry.getValue());
          }
        }
      }
    }
  }
}
