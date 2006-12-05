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
 * $Id: FeatureNotFoundException.java,v 1.5 2006/12/05 20:23:28 emerks Exp $
 */
package org.eclipse.emf.ecore.xmi;

import org.eclipse.emf.ecore.EObject;

public class FeatureNotFoundException extends XMIException 
{
  private static final long serialVersionUID = 1L;

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
