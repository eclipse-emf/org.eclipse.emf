/**
 * <copyright>
 *
 * Copyright (c) 2004-2007 IBM Corporation and others.
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
 * $Id: URIEditorInput.java,v 1.1 2010/03/11 02:30:05 khussey Exp $
 */
package org.eclipse.emf.common.ui;


import java.io.File;
import java.lang.reflect.Constructor;

import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IMemento;
import org.eclipse.ui.IPersistableElement;

import org.eclipse.core.runtime.Platform;
import org.eclipse.emf.common.util.URI;
import org.osgi.framework.Bundle;


/**
 * An  implementation of an {@link org.eclipse.ui.IEditorInput} to wrap
 * a {@link org.eclipse.emf.common.util.URI}. 
 */
public class URIEditorInput implements IEditorInput, IPersistableElement
{
  private URI uri;
  private String name;

  public URIEditorInput(URI uri)
  {
    this.uri = uri;
  }

  public URIEditorInput(URI uri, String name)
  {
    this.uri = uri;
    this.name = name;
  }

  public URIEditorInput(IMemento memento)
  {
    loadState(memento); 
  }
  
  @Override
  public int hashCode()
  {
    return uri.hashCode();
  }

  @Override
  public boolean equals(Object o)
  {
    return this == o || o instanceof URIEditorInput && uri.equals(((URIEditorInput)o).getURI());
  }

  /**
   * @return the uri
   */
  public URI getURI()
  {
    return uri;
  }  

  /**
   * Returns <b>true</b> only if the URI represents a file and if this file exists. 
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
    if (name == null)
    {
      URI uri = getURI();
      return URI.decode(uri.isHierarchical() && uri.lastSegment() != null ? uri.lastSegment() : uri.toString());
    }
    else
    {
      return name;
    }
  }
  
  public String getToolTipText()
  {
      return getURI().toString();
  }

  public ImageDescriptor getImageDescriptor()
  {
    return null;
  }

  public IPersistableElement getPersistable()
  {
    return this;
  }

  @SuppressWarnings("rawtypes")
  public Object getAdapter(Class adapter)
  {
    return null;
  }

  public String getFactoryId()
  {
    return URIEditorInputFactory.ID;
  }
  
  protected String getBundleSymbolicName()
  {
    return CommonUIPlugin.getPlugin().getSymbolicName();
  }

  protected static final String BUNDLE_TAG = "bundle";
  protected static final String CLASS_TAG = "class";
  protected static final String URI_TAG = "uri";
  protected static final String NAME_TAG = "name";
  
  static URIEditorInput create(IMemento memento)
  {
    String bundleSymbolicName = memento.getString(BUNDLE_TAG);
    String className = memento.getString(CLASS_TAG);
    try
    {
      Bundle bundle = Platform.getBundle(bundleSymbolicName);
      Class<?> theClass = bundle.loadClass(className);
      Constructor<?> constructor = theClass.getConstructor(IMemento.class);
      return (URIEditorInput)constructor.newInstance(memento);
    }
    catch (Exception exception)
    {
      CommonUIPlugin.INSTANCE.log(exception);
      return new URIEditorInput(memento);
    }
  }

  public void saveState(IMemento memento)
  {
    memento.putString(BUNDLE_TAG, getBundleSymbolicName());
    memento.putString(CLASS_TAG, getClass().getName());
    memento.putString(URI_TAG, uri.toString());
    memento.putString(NAME_TAG, name);
  }

  protected void loadState(IMemento memento)
  {
    uri = URI.createURI(memento.getString(URI_TAG));
    name = memento.getString(NAME_TAG);
  }
}