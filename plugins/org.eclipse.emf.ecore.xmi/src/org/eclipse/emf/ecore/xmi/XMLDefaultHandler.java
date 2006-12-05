/**
 * <copyright>
 *
 * Copyright (c) 2002-2006 IBM Corporation and others.
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
 * $Id$
 */
package org.eclipse.emf.ecore.xmi;

import java.util.Map;

import org.xml.sax.ContentHandler;
import org.xml.sax.DTDHandler;
import org.xml.sax.EntityResolver;
import org.xml.sax.ErrorHandler;
import org.xml.sax.ext.LexicalHandler;

/**
 * The XML handler interface. 
 *
 */
public interface XMLDefaultHandler extends ContentHandler, EntityResolver, DTDHandler, ErrorHandler, LexicalHandler
{
  void reset();
  void prepare(XMLResource resource, XMLHelper helper, Map<?, ?> options);
}
