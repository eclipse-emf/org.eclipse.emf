/**
 * Copyright (c) 2002-2006 IBM Corporation and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *   IBM - Initial API and implementation
 */
package org.eclipse.emf.ecore.xmi.impl;

import org.xml.sax.helpers.DefaultHandler;

import org.eclipse.emf.ecore.xmi.XMLHelper;

/**
 * This class creates an XMIHandler rather than an
 * XMLHandler so an XMI file can be loaded.
 */
public class XMILoadImpl extends XMLLoadImpl {

  /**
   * Constructor for XMILoad.
   */
  public XMILoadImpl(XMLHelper helper) {
    super(helper);
  }

  @Override
  protected DefaultHandler makeDefaultHandler()
  {
    return new SAXXMIHandler(resource, helper, options);
  }

}
