/**
 * <copyright>
 *
 * Copyright (c) 2004 IBM Corporation and others.
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
 * $Id: URIEditorInput.java,v 1.3 2005/06/08 06:24:33 nickb Exp $
 */
package org.eclipse.emf.common.ui;


import java.io.File;

import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IPersistableElement;

import org.eclipse.emf.common.util.URI;


/**
 * Very simple implementation of an {@link org.eclipse.ui.IEditorInput} to wrap
 * one {@link org.eclipse.emf.common.util.URI}. 
 */
public class URIEditorInput implements IEditorInput
{
  private URI uri;

  public URIEditorInput(URI uri)
  {
    this.uri = uri;
  }
  
  /**
   * @return the uri
   */
  public URI getURI()
  {
    return uri;
  }  

  /**
   * Returns <b>true</b> only if the URI represents a file and if 
   * this file exists. 
   * @see org.eclipse.ui.IEditorInput#exists()
   */
  public boolean exists()
  {
    if (getURI().isFile())
    {
      return new File(getURI().toFileString()).exists();
    }
    else
    {
      return false;
    }
  }

  /**
   * Returns the <i>toString</i> value of the associated URI. 
   * @see org.eclipse.ui.IEditorInput#getName()
   */
  public String getName()
  {
    return getURI().toString();
  }
  
  /*
   * @see org.eclipse.ui.IEditorInput#getToolTipText()
   */
  public String getToolTipText()
  {
      return getName();
  }

  /* (non-Javadoc)
   * @see org.eclipse.ui.IEditorInput#getImageDescriptor()
   */
  public ImageDescriptor getImageDescriptor()
  {
    return null;
  }

  /* (non-Javadoc)
   * @see org.eclipse.ui.IEditorInput#getPersistable()
   */
  public IPersistableElement getPersistable()
  {
    return null;
  }

  /* (non-Javadoc)
   * @see org.eclipse.core.runtime.IAdaptable#getAdapter(java.lang.Class)
   */
  public Object getAdapter(Class adapter)
  {
    return null;
  }
}