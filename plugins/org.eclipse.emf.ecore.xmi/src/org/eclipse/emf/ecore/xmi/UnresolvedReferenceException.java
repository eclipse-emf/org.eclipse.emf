/**
 * Copyright (c) 2002-2006 IBM Corporation and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 * 
 * Contributors: 
 *   IBM - Initial API and implementation
 */
package org.eclipse.emf.ecore.xmi;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;

public class UnresolvedReferenceException extends XMIException 
{
  private static final long serialVersionUID = 1L;

  protected String reference;

  /**
   * @since 2.13
   */
  protected transient EObject eObject;

  /**
   * @since 2.13
   */
  protected transient EStructuralFeature eStructuralFeature;

  public UnresolvedReferenceException(String reference, String location, int line, int column) 
  {
    super("Unresolved reference '" + reference + "'.", location, line, column);
    this.reference = reference;
  }

  /**
   * @since 2.13
   */
  public UnresolvedReferenceException(EObject eObject, EStructuralFeature eReference, String reference, String location, int line, int column) 
  {
    super("Unresolved reference '" + reference + "'.", location, line, column);
    this.eObject = eObject;
    this.eStructuralFeature = eReference;
    this.reference = reference;
  }
  
  public String getReference() 
  {
    return reference;
  }

  /**
   * @since 2.13
   */
  public EObject getObject()
  {
    return eObject;
  }

  /**
   * @since 2.13
   */
  public EStructuralFeature getFeature()
  {
    return eStructuralFeature;
  }
}
