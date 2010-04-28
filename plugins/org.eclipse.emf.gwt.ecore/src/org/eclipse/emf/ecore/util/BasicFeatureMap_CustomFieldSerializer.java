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
