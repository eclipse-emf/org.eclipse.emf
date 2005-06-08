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
 * $Id: EValidator.java,v 1.5 2005/06/08 06:20:10 nickb Exp $
 */
package org.eclipse.emf.ecore;


import java.util.Map;

import org.eclipse.emf.common.util.DiagnosticChain;


/**
 * A validity checker.
 */
public interface EValidator
{
  /**
   * This is the ID used for Eclipse markers which are based on diagnostics.
   */
  String MARKER = "org.eclipse.emf.ecore.diagnostic";

  /**
   * This is the name of the marker attribute to hold the String reprsentation of the 
   * {@link org.eclipse.emf.ecore.util.EcoreUtil#getURI URI} of the object that is the target of the marker.
   * @see org.eclipse.emf.ecore.util.EcoreUtil#getURI
   */
  String URI_ATTRIBUTE = "uri";

  /**
   * An <code>EValidator</code> wrapper that is used by the {@link EValidator.Registry}.
   */
  public interface Descriptor
  {
    /**
     * Returns the validator.
     * @return the validator.
     */
    EValidator getEValidator();
  }

  /**
   * A map from {@link org.eclipse.emf.ecore.EPackage EPackage} to {@link EValidator}.
   */
  interface Registry extends Map
  {
    /**
     * Looks up the package in the map.
     */
    EValidator getEValidator(EPackage ePackage);

    /**
     * The global instance of a validator registry.
     */
    Registry INSTANCE = new org.eclipse.emf.ecore.impl.EValidatorRegistryImpl();
  }

  /**
   * An interface for providing labels used within message substitutions.
   */
  interface SubstitutionLabelProvider
  {
    /**
     * Returns the label to identify an object.
     */
    String getObjectLabel(EObject eObject);

    /**
     * Returns the label used to identify a feature.
     */
    String getFeatureLabel(EStructuralFeature eStructuralFeature);

    /**
     * Returns the label to identify a value of some data type.
     */
    String getValueLabel(EDataType eDataType, Object value);
  }

  /**
   * An common interface for pattern-based constraints.
   */
  interface PatternMatcher
  {
    /**
     * Returns whether the string value matches the pattern.
     */
    boolean matches(String value);
  }

  /**
   * Validates the object in the given context, optionally producing diagnostics.
   * @param diagnostics a place to accumulate diagnostics; if it's <code>null</code>, no diagnostics should be produced.
   * @param context a place to cache information, if it's <code>null</code>, no cache is supported.
   * @return whether the object is valid.
   */
  boolean validate(EObject eObject, DiagnosticChain diagnostics, Map context);

  boolean validate(EClass eClass, EObject eObject, DiagnosticChain diagnostics, Map context);

  boolean validate(EDataType eDataType, Object value, DiagnosticChain diagnostics, Map context);
}
