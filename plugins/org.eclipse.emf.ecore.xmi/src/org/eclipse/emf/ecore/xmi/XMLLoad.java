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
 * $Id: XMLLoad.java,v 1.2 2004/04/05 20:06:48 emerks Exp $
 */
package org.eclipse.emf.ecore.xmi;

import java.io.IOException;
import java.io.InputStream;
import java.util.Map;

/**
 * This defines the methods for the interface that XMLResourceImpl
 * uses to load the resource.
 */
public interface XMLLoad 
{
  void load(XMLResource resource, InputStream inputStream, Map options) throws IOException;
}
