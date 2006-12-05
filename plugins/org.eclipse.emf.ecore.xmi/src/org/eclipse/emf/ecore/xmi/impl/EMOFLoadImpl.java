/**
 * <copyright>
 *
 * Copyright (c) 2003-2006 IBM Corporation and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *   IBM - Initial API and implementation
 *
 * </copyright>
 *
 * $Id: EMOFLoadImpl.java,v 1.4 2006/12/05 20:23:28 emerks Exp $
 */
package org.eclipse.emf.ecore.xmi.impl;

import org.xml.sax.helpers.DefaultHandler;


public class EMOFLoadImpl extends XMILoadImpl
{
  public EMOFLoadImpl(EMOFHandler.Helper helper)
  {
    super(helper);
  }

  @Override
  protected DefaultHandler makeDefaultHandler()
  {
    return new EMOFHandler(resource, (EMOFHandler.Helper)helper, options);
  }
}
