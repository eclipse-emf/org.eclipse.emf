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
 * $Id: FeatureNotFoundException.java,v 1.3 2004/10/07 12:06:24 emerks Exp $
 */
package org.eclipse.emf.ecore.xmi;

import org.eclipse.emf.ecore.EObject;

public class FeatureNotFoundException extends XMIException 
{
  protected String featureName;
  protected transient EObject object;

  public FeatureNotFoundException(String name, EObject object, String location, int line, int column) 
  {
    super("Feature '" + name + "' not found.", location, line, column);
    featureName = name;
    this.object = object;
  }
  
  public String getName() 
  {
    return featureName;
  }  
  
  public EObject getObject() 
  {
    return object;
  }  
}
