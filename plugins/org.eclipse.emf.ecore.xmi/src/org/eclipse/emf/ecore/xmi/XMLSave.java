/**
 * <copyright>
 *
 * Copyright (c) 2002-2004 IBM Corporation and others.
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
 * $Id: XMLSave.java,v 1.2.2.1 2005/06/08 18:27:44 nickb Exp $
 */
package org.eclipse.emf.ecore.xmi;

import java.io.IOException;
import java.io.OutputStream;
import java.util.Map;

/**
 * This defines the methods for the interface that XMLResourceImpl
 * uses to save the resource.
 */
public interface XMLSave 
{
  void save(XMLResource resource, OutputStream outputStream, Map options) throws IOException;
}
