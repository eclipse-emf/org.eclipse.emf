/**
 * <copyright>
 *
 * Copyright (c) 2003-2004 IBM Corporation and others.
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
 * $Id: EMOFLoadImpl.java,v 1.1 2004/03/06 17:31:32 marcelop Exp $
 */
package org.eclipse.emf.ecore.xmi.impl;

import org.xml.sax.helpers.DefaultHandler;


public class EMOFLoadImpl extends XMILoadImpl
{
  public EMOFLoadImpl(EMOFHandler.Helper helper)
  {
    super(helper);
  }

  protected DefaultHandler makeDefaultHandler()
  {
    return new SAXWrapper(new EMOFHandler(resource, (EMOFHandler.Helper)helper, options));
  }
}
