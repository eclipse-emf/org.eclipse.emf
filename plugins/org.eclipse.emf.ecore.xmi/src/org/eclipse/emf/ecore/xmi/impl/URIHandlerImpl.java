/**
 * Copyright (c) 2006-2012 IBM Corporation and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *   IBM - Initial API and implementation
 */

package org.eclipse.emf.ecore.xmi.impl;


import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.xmi.XMLResource;


/**
 * This is the basic implementation of the {@link XMLResource.URIHandler URIHandler} interface. 
 * Implementations should extend this class to implement a specialized URI handler.
 */
public class URIHandlerImpl implements XMLResource.URIHandler
{
  /**
   * A URI handler that will avoid creating relative references between platform:/resource and platform:/plugin.
   */
  public static class PlatformSchemeAware extends URIHandlerImpl
  {
    @Override
    public URI deresolve(URI uri)
    {
      return !uri.isPlatform() || (uri.segmentCount() > 0 && baseURI.segmentCount() > 0 && uri.segment(0).equals(baseURI.segment(0))) ?
        super.deresolve(uri) : uri;
    }
  }

  /**
   * A URI handler that will avoid creating relative references between different platform:/resource/project-name or platform/plugin/bundle-name,
   * i.e., it will produce relative references only within projects or bundles and will use absolute URIs for references outside of that project or bundle.
   * @since 2.9
   */
  public static class AbsoluteCrossBundleAware extends URIHandlerImpl
  {
    @Override
    public URI deresolve(URI uri)
    {
      return !uri.isPlatform() || (uri.segmentCount() > 1 && baseURI.segmentCount() > 1 && uri.segment(0).equals(baseURI.segment(0)) && uri.segment(1).equals(baseURI.segment(1))) ?
        super.deresolve(uri) : uri;
    }
  }

  protected URI baseURI;
  protected boolean resolve;

  public void setBaseURI(URI uri)
  {
    baseURI = uri;
    resolve = uri != null && uri.isHierarchical() && !uri.isRelative();
  }

  public URI resolve(URI uri)
  {
    return resolve && uri.isRelative() && uri.hasRelativePath() ? uri.resolve(baseURI) : uri;
  }

  public URI deresolve(URI uri)
  {
    if (resolve && !uri.isRelative())
    {
      URI deresolvedURI = uri.deresolve(baseURI, true, true, false);
      if (deresolvedURI.hasRelativePath())
      {
        uri = deresolvedURI;
      }
    }
    return uri;
  }
}
