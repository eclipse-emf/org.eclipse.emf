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
package org.eclipse.emf.ecore.xmi;

import java.io.IOException;
import java.io.InputStream;
import java.util.Map;

import org.w3c.dom.Node;
import org.xml.sax.InputSource;

/**
 * This defines the methods for the interface that XMLResourceImpl
 * uses to load the resource.
 */
public interface XMLLoad 
{
  void load(XMLResource resource, InputStream inputStream, Map<?, ?> options) throws IOException;
  void load(XMLResource resource, InputSource inputSource, Map<?, ?> options) throws IOException;
  void load(XMLResource resource, Node node, Map<?, ?> options) throws IOException;
  XMLDefaultHandler createDefaultHandler();
}
