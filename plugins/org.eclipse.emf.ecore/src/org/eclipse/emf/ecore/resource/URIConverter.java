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
 * $Id: URIConverter.java,v 1.2 2004/07/29 13:33:22 marcelop Exp $
 */
package org.eclipse.emf.ecore.resource;


import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Map;

import org.eclipse.emf.common.util.URI;


/**
 * A converter to normalize a URI or to produce an input or output stream for a URI.
 * <p>
 * A resource set provides {@link ResourceSet#getURIConverter one} of these
 * for use by it's {@link ResourceSet#getResources resources}
 * when they are {@link Resource#save(java.util.Map) serialized} and {@link Resource#load(java.util.Map) deserialized}.
 * A resource set also uses this directly when it {@link ResourceSet#getResource looks up} a resource:
 * a resource is considered a match if {@link Resource#getURI it's URI}, 
 * and the URI being looked up, 
 * {@link #normalize normalize} to {@link URI#equals(Object) equal} URIs.
 * </p>
 */
public interface URIConverter
{
  /**
   * Returns the normalized form of the URI.
   * <p>
   * This may, in theory, do absolutly anything.
   * Default behaviour includes 
   * applying URI {@link URIConverter#getURIMap mapping},
   * assuming <code>"file:"</code> protocol 
   * for a {@link URI#isRelative relative} URI with a {@link URI#hasRelativePath relative path}:
   *<pre>
   *  ./WhateverDirectory/Whatever.file 
   *    -> 
   *  file:./WhateverDirectory/Whatever.file
   *</pre>
   * and assuming <code>"platform:/resource"</code> protocol 
   * for a relative URI with an {@link URI#hasAbsolutePath absolute path}:
   *<pre>
   *  /WhateverRelocatableProject/Whatever.file 
   *    -> 
   *  platform:/resource/WhateverRelocatableProject/Whatever.file
   *</pre>
   * </p>
   * <p>
   * It is important to emphasize that normalization can result it loss of information.
   * The normalized URI should generally be used only for comparison and for access to input or output streams.
   * </p>
   * @param uri the URI to normalize.
   * @return the normalized form.
   * @see org.eclipse.emf.ecore.plugin.EcorePlugin#getPlatformResourceMap
   */
  URI normalize(URI uri);

  /**
   * Returns the map used for remapping a logical URI to a physical URI when {@link #normalize normalizing}.
   * <p>
   * An implementation will typically also delegate to the {@link URIConverter#URI_MAP global} map,
   * so registrations made in this map are <em>local</em> to this URI converter,
   * i.e., they augment or override those of the global map.
   * </p>
   * <p>
   * The map generally specifies instance to instance mapping,
   * except for the case that both the key URI and the value URI end with "/", 
   * which specifies a folder to folder mapping.
   * A folder mapping will remap any URI that has the key as its {@link URI#replacePrefix prefix}, 
   * e.g., if the map contains:
   *<pre>
   *  http://www.example.com/ -> platform:/resource/example/
   *</pre>
   * then the URI
   *<pre>
   *  http://www.example.com/a/b/c.d
   *</pre>
   * will map to 
   *<pre>
   *  platform:/resource/example/a/b/c.d
   *</pre>
   * A matching instance mapping is considered first.
   * If there isn't one, the folder mappings are considered starting with the {@link URI#segmentCount() longest} prefix. 
   * </p>
   * @see #normalize(URI)
   * @see #URI_MAP
   * @return the map used for remapping a logical URI to a physical URI.
   */
  Map getURIMap();

  /**
   * The global static URI map.
   * Registrations made in this instance will (typically) be available
   * for {@link URIConverter#normalize use} by any URI converter.
   * It is populated by URI mappings registered via
   * {@link org.eclipse.emf.ecore.plugin.EcorePlugin.Implementation#startup plugin registration}.
   * @see #normalize(URI)
   */
  Map URI_MAP = org.eclipse.emf.ecore.resource.impl.URIMappingRegistryImpl.INSTANCE.map();

  /**
   * Creates an input stream for the URI and returns it.
   * <p>
   * It {@link #normalize normalizes} the URI and uses that as the basis for further processing.
   * Special requirements, such as an Eclipse file refresh, 
   * are handled by the {@link org.eclipse.emf.ecore.resource.impl.URIConverterImpl default implementation}.
   * </p>
   * @return an open input stream.
   * @exception IOException if there is a problem obtaining an open input stream.
   */
  InputStream createInputStream(URI uri) throws IOException;

  /**
   * Creates an output stream for the URI and returns it.
   * <p>
   * It {@link #normalize normalizes} the URI and uses that as the basis for further processing.
   * Special requirements, such as an Eclipse file refresh and automatic subdirectory creation, 
   * are handled by the {@link org.eclipse.emf.ecore.resource.impl.URIConverterImpl default implementation}.
   * </p>
   * @return an open output stream.
   * @exception IOException if there is a problem obtaining an open output stream.
   */
  OutputStream createOutputStream(URI uri) throws IOException;
}
