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
 * $Id: BasicFeatureMap_CustomFieldSerializer.java,v 1.2 2010/04/28 20:39:41 khussey Exp $
 */
package org.eclipse.emf.ecore.util;

import com.google.gwt.user.client.rpc.SerializationException;
import com.google.gwt.user.client.rpc.SerializationStreamReader;
import com.google.gwt.user.client.rpc.SerializationStreamWriter;

public final class BasicFeatureMap_CustomFieldSerializer
{
  public static void serialize(SerializationStreamWriter streamWriter, BasicFeatureMap basicFeatureMap) throws SerializationException 
  {
    // TODO
    throw new UnsupportedOperationException();
  }
  
  public static BasicFeatureMap instantiate(SerializationStreamReader streamReader) throws SerializationException 
  {
    throw new UnsupportedOperationException();
  }

  public static void deserialize(SerializationStreamReader reader, BasicFeatureMap map) throws SerializationException
  {
    reader.readObject();

    map.featureMapValidator = FeatureMapUtil.getValidator(map.owner.eClass(), map.getEStructuralFeature());
  }

}
