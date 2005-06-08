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
 * $Id: RoseComponent.java,v 1.2 2005/06/08 06:20:36 nickb Exp $
 */
package org.eclipse.emf.importer.rose.parser;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


/**
 * A common class for property change event producers.
 */
public class RoseComponent
{
  protected List listeners = new ArrayList();

  public void addPropertyChangeListener(PropertyChangeListener l)
  {
    listeners.add(l);
  }

  public void firePropertyChange(String propertyName, int oldValue, int newValue)
  {
    for (Iterator i = listeners.iterator(); i.hasNext();)
    {
      PropertyChangeListener propertyChangeListener = (PropertyChangeListener)i.next();
      Integer oldInt = new Integer(oldValue);
      Integer newInt = new Integer(newValue);
      PropertyChangeEvent propertyChangeEvent = new PropertyChangeEvent(this, propertyName, oldInt, newInt);
      propertyChangeListener.propertyChange(propertyChangeEvent);
    }
  }
}
