/**
 * Copyright (c) 2010 Ed Merks and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: 
 *   Ed Merks - Initial API and implementation
 */
package org.eclipse.emf.ecore.impl;

import java.util.List;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EStructuralFeature;

import com.google.gwt.user.client.rpc.SerializationException;
import com.google.gwt.user.client.rpc.SerializationStreamReader;
import com.google.gwt.user.client.rpc.SerializationStreamWriter;

public class BasicEObjectImpl_CustomFieldSerializer
{
  public static void serialize(SerializationStreamWriter streamWriter, BasicEObjectImpl myObject) throws SerializationException 
  {
    streamWriter.writeString(myObject.eIsProxy() ? myObject.eProxyURI().toString() : null);
    EClass eClass = myObject.eDynamicClass();
    streamWriter.writeObject(eClass);
    if (eClass == null)
    {
      eClass = myObject.eClass();
    }
    short featureID = 0;
    for (EStructuralFeature eStructuralFeature : eClass.getEAllStructuralFeatures())
    {
      if (!eStructuralFeature.isTransient() && myObject.eIsSet(eStructuralFeature))
      {
        Object value = myObject.eGet(eStructuralFeature);
        if (eStructuralFeature.isMany())
        {
          streamWriter.writeShort(featureID);
          List<?> list = (List<?>)value;
          streamWriter.writeInt(list.size());
          for (Object element : list)
          {
            streamWriter.writeObject(element);
          }
        }
        else
        {
          streamWriter.writeShort((short)(-1 - featureID));
          streamWriter.writeObject(value);
        }
      }
      ++featureID; 
    }
    streamWriter.writeShort(Short.MAX_VALUE);
  }

  public static void deserialize(SerializationStreamReader streamReader, BasicEObjectImpl myObject) throws SerializationException 
  {
    String proxyURI = streamReader.readString();
    if (proxyURI != null)
    {
      myObject.eSetProxyURI(URI.createURI(proxyURI));
    }
    EClass eClass = (EClass)streamReader.readObject();
    if (eClass != null)
    {
      myObject.eSetClass(eClass);
    }
    for (short featureID = streamReader.readShort(); featureID != Short.MAX_VALUE; featureID = streamReader.readShort())
    {
      if (featureID >= 0)
      {
        @SuppressWarnings("unchecked")
        List<Object> list = (List<Object>)myObject.eGet(featureID, false, true);
        for (int i = 0, size = streamReader.readInt(); i < size; ++i)
        {
          list.add(streamReader.readObject());
        }
      }
      else
      {
        myObject.eSet(-1 - featureID, streamReader.readObject());
      }
    }
  }
}
