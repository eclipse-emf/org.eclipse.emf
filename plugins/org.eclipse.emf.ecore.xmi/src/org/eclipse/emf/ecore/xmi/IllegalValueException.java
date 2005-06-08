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
 * $Id: IllegalValueException.java,v 1.4 2005/06/08 06:16:07 nickb Exp $
 */
package org.eclipse.emf.ecore.xmi;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;

public class IllegalValueException extends XMIException 
{
  protected transient EObject object;
  protected transient EStructuralFeature feature;
  protected transient Object value;
  
  public IllegalValueException
    (EObject object, EStructuralFeature feature, Object value, Exception emfException, String location, int line, int column) 
  {
    super("Value '" + value + "' is not legal.", emfException, location, line, column);
    this.object  = object;
    this.feature = feature;
    this.value   = value;
  }
  
  public EObject getObject() 
  {
    return object;
  }
  
  public EStructuralFeature getFeature() 
  {
    return feature;
  }
  
  public Object getValue() 
  {
    return value;
  }
}
