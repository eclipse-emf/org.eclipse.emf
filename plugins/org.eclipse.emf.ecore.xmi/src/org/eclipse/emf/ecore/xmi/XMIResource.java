/**
 * Copyright (c) 2002-2011 IBM Corporation, CEA, and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 *
 * Contributors:
 *   IBM - Initial API and implementation
 *   Kenn Hussey (CEA) - 351783
 */
package org.eclipse.emf.ecore.xmi;

import org.eclipse.emf.ecore.util.ExtendedMetaData;



/**
 * A resource that serializes to XMI. The serialization format is based
 * on the XMI 2.0 specification from the OMG. The various save and load
 * options in this interface enable you to tailor the XMI files that are
 * produced.
 * <p>
 * You may specify the XML encoding to be used when saving the resource
 * by using the {@link XMLResource#setEncoding setEncoding} method.
 * </p>
 * <p>
 * An XMIResource is capable of handling XMI files that contain IDs as
 * well as XMI files that use URI fragments rather than IDs. The IDs from
 * an XMI file are stored in the XMIResource in the {@link XMLResource#getIDToEObjectMap
 * idToEObjectMap} and the {@link XMLResource#getEObjectToIDMap eObjectToIDMap}.
 * </p>
 * <p>
 * When saving an XMIResource, the ID of an object is saved if an object has
 * one. If a referenced object has an ID, it is used rather than a URI fragment.
 * You may clear the Maps that contain IDs if you wish to use URI fragments
 * rather than IDs.
 * </p>
 * <p>
 * An XMIResource does not create IDs for you automatically; you can set IDs
 * for objects yourself. However, we recommend that you use URI fragments
 * instead because their use reduces the size of XMI files and memory
 * consumption as well.
 * </p>
 * @see org.eclipse.emf.ecore.resource.Resource
 * @see org.eclipse.emf.ecore.resource.impl.ResourceImpl
 * @see org.eclipse.emf.ecore.xmi.impl.XMIResourceImpl
 */
public interface XMIResource extends XMLResource
{
  /**
   * Write the type of an element as "xmi:type" instead of "xsi:type"
   * which is useful for models with multiple inheritance that may
   * conflict with XML schema types
   */
  String OPTION_USE_XMI_TYPE = "USE_XMI_TYPE";

  /**
   * A save or load option that when set to true, instructs the resource to produce regular XML, not XMI, or to consume, regular XML, not XMI.
   * @since 2.7
   */
  String OPTION_SUPPRESS_XMI = "SUPPRESS_XMI";

  String VERSION_NAME = "version";

  String VERSION_VALUE = "2.0";

  String VERSION_2_1_VALUE = "2.1";

  String XMI_NAMESPACE_PREFIX = "http://schema.omg.org/spec/XMI/";

  String XMI_2_4_NAMESPACE_PREFIX = "http://www.omg.org/spec/XMI/";

  String XMI_NS = "xmi";

  String XMI_ID = "id";

  String XMI_TAG_NAME = "XMI";

  String XMI_URI = ExtendedMetaData.XMI_URI;

  String XMI_2_1_URI = XMI_NAMESPACE_PREFIX + VERSION_2_1_VALUE;

  /**
   * @return XMI version
   */
  String getXMIVersion();

  /**
   * Sets XMI version and automatically assigns corresponding namespace
   */
  void setXMIVersion(String version);

  /**
   * @return XMI namespace
   */ 
  String getXMINamespace();

  /**
   * Sets XMI namespace and automatically assigns corresponding version
   */
  void setXMINamespace(String namespace);
}
