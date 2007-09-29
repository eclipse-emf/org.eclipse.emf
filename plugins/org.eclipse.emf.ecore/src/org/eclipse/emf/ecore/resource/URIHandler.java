/**
 * <copyright>
 *
 * Copyright (c) 2007 IBM Corporation and others.
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
 * $Id: URIHandler.java,v 1.1 2007/09/29 16:41:42 emerks Exp $
 */
package org.eclipse.emf.ecore.resource;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.impl.ArchiveURIHandlerImpl;
import org.eclipse.emf.ecore.resource.impl.EFSURIHandlerImpl;
import org.eclipse.emf.ecore.resource.impl.FileURIHandlerImpl;
import org.eclipse.emf.ecore.resource.impl.PlatformResourceURIHandlerImpl;
import org.eclipse.emf.ecore.resource.impl.URIHandlerImpl;

/**
 * A handler for determining information about URI.
 * <p>
 * A URI handler is used primarily by a {@link URIConverter URI converter} 
 * which provides support for accessing and querying the contents of a URI
 * by virtue of having a {@link URIConverter#getURIHandlers() list} of URI handlers
 * that it consults to determine whether the  handler {@link #canHandle(URI) can handle} the given URI and if so
 * that it uses as a delegate.
 * </p>
 * @see URIConverter
 * @see ContentHandler
 * @since 2.4
 */
public interface URIHandler
{
  /**
   * The global default read only list of URI handlers.
   */
  List<URIHandler> DEFAULT_HANDLERS = 
    Collections.unmodifiableList
      (Arrays.asList
         (new URIHandler [] 
          { 
            new PlatformResourceURIHandlerImpl(), 
            new FileURIHandlerImpl(), 
            new EFSURIHandlerImpl(), 
            new ArchiveURIHandlerImpl(), 
            new URIHandlerImpl()
          }));

  /**
   * Returns whether this handler is appropriate for the given URI.
   * @param uri the URI to consider.
   * @return whether this handler is appropriate for the given URI.
   */
  boolean canHandle(URI uri);

  /**
   * Creates an input stream for the URI and returns it.
   * @param uri the URI for which to create the input stream.
   * @param options a map of options to influence the kind of stream that is returned; unrecognized options are ignored and <code>null</code> is permitted.
   * @return an open input stream.
   * @exception IOException if there is a problem obtaining an open input stream.
   * @see URIConverter#createInputStream(URI, Map)
   */
  InputStream createInputStream(URI uri, Map<?, ?> options) throws IOException;

  /**
   * Creates an output stream for the URI and returns it.
   * @param uri the URI for which to create the output stream.
   * @param options a map of options to influence the kind of stream that is returned; unrecognized options are ignored and <code>null</code> is permitted.
   * @return an open output stream.
   * @exception IOException if there is a problem obtaining an open output stream.
   * @see URIConverter#createOutputStream(URI, Map)
   */
  OutputStream createOutputStream(URI uri, Map<?, ?> options) throws IOException;

  /**
   * Deletes the contents of the given URI. 
   * @param uri the URI to consider.
   * @param options options to influence how the contents are deleted, or <code>null</code> if there are no options.
   * @throws IOException if there is a problem deleting the contents.
   * @see URIConverter#delete(URI, Map) 
   */
  void delete(URI uri, Map<?, ?> options) throws IOException;

  /**
   * Returns a map from String properties to their corresponding values representing a description the given URI's contents.
   * See the {@link ContentHandler#contentDescription(URI, InputStream, Map, Map) content handler} for more details.
   * @param uri the URI to consider.
   * @param options options to influence how the content description is determined, or <code>null</code> if there are no options.
   * @return a map from String properties to their corresponding values representing a description the given URI's contents.
   * @throws IOException if there is a problem accessing the contents.
   * @see URIConverter#contentDescription(URI, Map)
   * @see ContentHandler#contentDescription(URI, InputStream, Map, Map)
   */
  Map<String, ?> contentDescription(URI uri, Map<?, ?> options) throws IOException;

  /**
   * Returns whether the given URI has contents.
   * If the URI {@link #exists(URI, Map) exists}
   * it will be possible to {@link #createOutputStream(URI, Map) create} an input stream.
   * @param uri the URI to consider.
   * @param options options to influence how the existence determined, or <code>null</code> if there are no options.
   * @return whether the given URI has contents.
   * @see URIConverter#exists(URI, Map)
   */
  boolean exists(URI uri, Map<?, ?> options);

  /**
   * Returns whether the contents of the given URI can be modified.
   * If the URI's contents {@link #exists(URI, Map) exist} and is read only, 
   * it will not be possible to {@link #createOutputStream(URI, Map) create} an output stream.
   * @param uri the URI to consider.
   * @param options options to influence how the read only state is determined, or <code>null</code> if there are no options.
   * @return whether the contents of the given URI can be modified.
   * @see URIConverter#isReadOnly(URI, Map)
   */
  boolean isReadOnly(URI uri, Map<?, ?> options);

  /**
   * Returns the time stamp representing the last time the contents of the given URI was modified.
   * The value is represented as the number of milliseconds 
   * since the epoch 00:00:00 GMT, January 1, 1970.
   * @param uri the URI to consider.
   * @param options options to influence how the time stamp is determined, or <code>null</code> if there are no options.
   * @return the time stamp representing the last time the contents of the given URI was modified.
   * @see URIConverter#timeStamp(URI, Map)
   */
  long timeStamp(URI uri, Map<?, ?> options);
}
