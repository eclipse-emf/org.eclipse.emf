/**
 * <copyright>
 *
 * Copyright (c) 2002-2004 IBM Corporation and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Common Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/cpl-v10.html
 *
 * Contributors:
 *   IBM - Initial API and implementation
 *
 * </copyright>
 *
 * $Id: XMLResourceImpl.java,v 1.3 2004/04/18 23:15:22 emerks Exp $
 */
package org.eclipse.emf.ecore.xmi.impl;


import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;
import java.util.WeakHashMap;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.impl.ResourceImpl;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.xmi.XMLHelper;
import org.eclipse.emf.ecore.xmi.XMLLoad;
import org.eclipse.emf.ecore.xmi.XMLResource;
import org.eclipse.emf.ecore.xmi.XMLSave;


/**
 * This class implements the XMLResource interface. It overloads the
 * doLoad method to invoke the XML deserializer rather than using the
 * default XMI loader.
 */
public class XMLResourceImpl extends ResourceImpl implements XMLResource
{
  /**
   * The map from {@link #getID ID} to {@link EObject}.
   * It is used to store IDs during a load or if the user
   * sets the ID of an object.
   */
  protected Map idToEObjectMap;

  /**
   * The map from {@link EObject} to {@link #getID ID}.
   * It is used to store IDs during a load or if the user
   * sets the ID of an object.
   */
  protected Map eObjectToIDMap;

  protected Map eObjectToExtensionMap;

  protected String encoding;
  protected boolean useZip;
  protected String publicId;
  protected String systemId;

  /**
   * The map from {@link EObject} to {@link #getID ID}. It is used to store
   * IDs for objects that have been detached.
   */
  protected static final Map DETACHED_EOBJECT_TO_ID_MAP = Collections.synchronizedMap(new WeakHashMap());

  /**
   * Constructor for XMLResourceImpl.
   */
  public XMLResourceImpl()
  {
    super();
    init();
  }

  /**
   * Constructor for XMLResourceImpl.
   * @param uri
   */
  public XMLResourceImpl(URI uri)
  {
    super(uri);
    init();
  }

  protected void init()
  {
    encoding = "ASCII";
  }

  protected boolean useIDs()
  {
    return true;
  }

  protected boolean useIDAttributes()
  {
    return true;
  }

  protected boolean useUUIDs()
  {
    return false;
  }

  public Map getDefaultSaveOptions()
  {
    if (defaultSaveOptions == null)
    {
      defaultSaveOptions = new HashMap();
    }
    return defaultSaveOptions;
  }

  public Map getDefaultLoadOptions()
  {
    if (defaultLoadOptions == null)
    {
      defaultLoadOptions = new HashMap();
    }
    return defaultLoadOptions;
  }

  protected XMLHelper createXMLHelper()
  {
    return new XMLHelperImpl(this);
  }

  protected XMLLoad createXMLLoad()
  {
    return new XMLLoadImpl(createXMLHelper());
  }

  protected XMLSave createXMLSave()
  {
    return new XMLSaveImpl(createXMLHelper());
  }

  public void doLoad(InputStream inputStream, Map options) throws IOException
  {
    XMLLoad xmlLoad = createXMLLoad();

    if (options == null)
    {
      options = Collections.EMPTY_MAP;
    }

    xmlLoad.load(this, inputStream, options);
  }

  public void doSave(OutputStream outputStream, Map options) throws IOException
  {
    XMLSave xmlSave = createXMLSave();

    if (options == null)
    {
      options = Collections.EMPTY_MAP;
    }

    xmlSave.save(this, outputStream, options);
  }

  public boolean useZip()
  {
    return useZip;
  }

  public void setUseZip(boolean useZip)
  {
    this.useZip = useZip;
  }
  
  public String getPublicId()
  {
    return publicId;
  }
  public String getSystemId()
  {
    return systemId;
  }
  public void setDoctypeInfo(String publicId, String systemId)
  {
    this.publicId = publicId;
    this.systemId = systemId;
  }

  public String getEncoding()
  {
    return encoding;
  }

  public void setEncoding(String encoding)
  {
    this.encoding = encoding;
  }

  public Map getIDToEObjectMap()
  {
    if (idToEObjectMap == null)
    {
      idToEObjectMap = new HashMap();
    }

    return idToEObjectMap;
  }

  public Map getEObjectToIDMap()
  {
    if (eObjectToIDMap == null)
    {
      eObjectToIDMap = new HashMap();
    }

    return eObjectToIDMap;
  }

  public Map getEObjectToExtensionMap()
  {
    if (eObjectToExtensionMap == null)
    {
      eObjectToExtensionMap = new HashMap();
    }
    return eObjectToExtensionMap;
  }

  /*
   * Javadoc copied from interface
   */
  public String getID(EObject eObject)
  {
    if (eObjectToIDMap == null)
    {
      return null;
    }
    else
    {
      return (String)eObjectToIDMap.get(eObject);
    }
  }

  /**
   * Sets the ID of the object.
   * This default implementation will update the {@link #eObjectToIDMap}.
   * Clients may override it to set the ID as an actual attribute object the object.
   * @param eObject the object.
   * @param id the object's ID.
   */
  public void setID(EObject eObject, String id)
  {
    Object oldID = getEObjectToIDMap().put(eObject, id);
    if (oldID != null)
    {
      getIDToEObjectMap().remove(oldID);
    }
    getIDToEObjectMap().put(id, eObject);
  }

  /*
   * Javadoc copied from interface.
   */
  public String getURIFragment(EObject eObject)
  {
    String id = getID(eObject);

    if (id != null)
    {
      return id;
    }
    else
    {
      return super.getURIFragment(eObject);
    }
  }

  protected EObject getEObjectByID(String id)
  {
    if (idToEObjectMap != null)
    {
      EObject eObject = (EObject) idToEObjectMap.get(id);
      if (eObject != null)
      {
        return eObject;
      }
    }

   return useIDAttributes() ? super.getEObjectByID(id) : null;
  }

  protected boolean isPath(String uriFragment)
  {
    return uriFragment.startsWith("/");
  }

  /*
   * Javadoc copied from interface.
   */
  public void attached(EObject eObject)
  {
    if (useIDs())
    {
      attachedHelper(eObject);
      for (Iterator tree = eObject.eAllContents(); tree.hasNext(); )
      {
        EObject child = (EObject)tree.next();
        attachedHelper(child);
      }
    }
    else
    {
      super.attached(eObject);
    }
  }

  protected void attachedHelper(EObject eObject)
  {
    if (modificationTrackingAdapter != null)
    {
      eObject.eAdapters().add(modificationTrackingAdapter);
    }

    String id = getID(eObject);
    if (useUUIDs() && id == null)
    {
      id = (String)DETACHED_EOBJECT_TO_ID_MAP.remove(eObject);
      if (id == null)
      {
        id = EcoreUtil.generateUUID();
      }
      setID(eObject, id);
    }
    else if (id != null)
    {
      getIDToEObjectMap().put(id, eObject);
    }
  }

  /*
   * Javadoc copied from interface.
   */
  public void detached(EObject eObject)
  {
    if (useIDs())
    {
      detachedHelper(eObject);
      for (Iterator tree = eObject.eAllContents(); tree.hasNext(); )
      {
        EObject child = (EObject)tree.next();
        detachedHelper(child);
      }
    }
    else
    {
      super.detached(eObject);
    }
  }

  protected void detachedHelper(EObject eObject)
  {
    if (modificationTrackingAdapter != null)
    {
      eObject.eAdapters().remove(modificationTrackingAdapter);
    }

    if (useUUIDs())
    {
      DETACHED_EOBJECT_TO_ID_MAP.put(eObject, getID(eObject));
    }

    if (idToEObjectMap != null && eObjectToIDMap != null)
    {
      idToEObjectMap.remove(eObjectToIDMap.remove(eObject));
    }
  }

  /**
   * Does all the work of unloading the resource. It calls doUnload in
   * ResourceImpl, then it clears {@link #idToEObjectMap} and {@link #eObjectToIDMap} as necessary.
   */
  protected void doUnload()
  {
    super.doUnload();

    if (idToEObjectMap != null)
    {
      idToEObjectMap.clear();
    }

    if (eObjectToIDMap != null)
    {
      eObjectToIDMap.clear();
    }

    if (eObjectToExtensionMap != null)
    {
      eObjectToExtensionMap.clear();
    }
  }

  /**
   * Returns a string representation of the {@link #idToEObjectMap ID} map.
   * @return a string representation of the ID map.
   */
  public String toKeyString()
  {
    StringBuffer result = new StringBuffer("Key type: ");
    result.append(getClass().toString());
    if (idToEObjectMap != null)
    {
      TreeMap tree = new TreeMap();
      for (Iterator i = idToEObjectMap.keySet().iterator(); i.hasNext(); )
      {
        Object key = i.next();
        if (key != null)
        {
          tree.put(key.toString(), key);
        }
      }

      // add the key/value pairs to the output string
      for (Iterator i = tree.values().iterator(); i.hasNext(); )
      {
        Object key = i.next();
        Object value = idToEObjectMap.get(key);
        result.append("\r\n\t[Key=" + key + ", Value=" + value + "]");
      }
    }
    return result.toString();
  }
}
