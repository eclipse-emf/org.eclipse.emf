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
 * $Id: XMLSave.java,v 1.3 2004/12/23 19:32:59 elena Exp $
 */
package org.eclipse.emf.ecore.xmi;

import java.io.IOException;
import java.io.OutputStream;
import java.util.Map;

import org.w3c.dom.Document;


/**
 * This defines the methods for the interface that XMLResourceImpl
 * uses to save the resource.
 */
public interface XMLSave 
{
  void save(XMLResource resource, OutputStream outputStream, Map options) throws IOException;

  /**
   * @param resource a resource 
   * @param document a {@link org.w3c.dom.Document} (must not be null)
   * @param handler a {@link DOMHandler} (must not be null)
   * @param options options
   * @return the document
   * @since 2.1.0
   */
  Document toDOM(XMLResource resource, Document document, DOMHandler handler, Map options);
}
