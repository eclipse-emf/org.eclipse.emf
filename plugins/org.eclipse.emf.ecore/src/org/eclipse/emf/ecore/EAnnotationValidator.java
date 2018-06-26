/**
 * Copyright (c) 2017 Eclipse contributors and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 */
package org.eclipse.emf.ecore;


import java.util.HashMap;
import java.util.Map;

import org.eclipse.emf.common.util.DiagnosticChain;
import org.eclipse.emf.ecore.util.BasicEAnnotationValidator;
import org.eclipse.emf.ecore.util.EObjectValidator;


/**
 * An annotation validity checker for a specific {@link EAnnotation#getSource() annotation source}.
 * <p>
 * Implementations of EAnnotationValidator should extend {@link org.eclipse.emf.ecore.util.BasicEAnnotationValidator BasicEAnnotationValidator}
 * or one of its derived classes
 * because methods may be added to this API.
 * </p>
 *
 * @since 2.14
 * @see BasicEAnnotationValidator
 *
 * @noimplement Do not implement this interface directly; instead extend {@link BasicEAnnotationValidator} or one of its subclasses.
 */
public interface EAnnotationValidator
{
  /**
   * An <code>EAnnotationValidator</code> wrapper used by the {@link EAnnotationValidator.Registry}.
   */
  interface Descriptor
  {
    /**
     * Returns the annotation validator.
     * @return the annotation validator.
     */
    EAnnotationValidator getEAnnotationValidator();
  }

  /**
   * A map from {@link EAnnotation#getSource() annotation source} to {@link EAnnotationValidator} or to {@link Descriptor}.
   */
  interface Registry extends Map<String, Object>
  {
    /**
     * Looks up the annotation source in the map.
     */
    EAnnotationValidator getEAnnotationValidator(String annotationSource);

    /**
     * The global instance of an annotation validator registry.
     */
    Registry INSTANCE = new Impl();

    class Impl extends HashMap<String, Object> implements Registry
    {
      private static final long serialVersionUID = 1L;

      @Override
      public Object get(Object key)
      {
        Object eAnnotationValidator = super.get(key);
        if (eAnnotationValidator instanceof EAnnotationValidator.Descriptor)
        {
          EAnnotationValidator.Descriptor eAnnotationValidatorDescriptor = (EAnnotationValidator.Descriptor)eAnnotationValidator;
          eAnnotationValidator = eAnnotationValidatorDescriptor.getEAnnotationValidator();
          put((String)key, eAnnotationValidator);
          return eAnnotationValidator;
        }
        else
        {
          return eAnnotationValidator;
        }
      }

      public EAnnotationValidator getEAnnotationValidator(String annotationSource)
      {
        return (EAnnotationValidator)get(annotationSource);
      }
    }
  }

  /**
   * Returns the {@link EAnnotation#getSource() annotation source} of the annotations validated by this annotation validator.
   * @return the annotation source.
   */
  String getAnnotationSource();

  /**
   * Returns whether this annotation with this annotation validator's {@link EAnnotation#getSource() annotation source} is valid at its {@link EAnnotation#getEModelElement() current location}.
   * @param eAnnotation the annotation in question.
   * @return whether this annotation with this annotation validator's annotation source is valid at its current location.
   */
  boolean isValidLocation(EAnnotation eAnnotation);

  /**
   * Returns whether this annotation is valid.
   * @param eAnnotation the annotation in question.
   * @param diagnostics a place to accumulate diagnostics; if it's <code>null</code>, no diagnostics should be produced.
   * @param context a place to cache information, if it's <code>null</code>, no cache is supported.
   * @return whether this annotation is valid.
   *
   * @see EObjectValidator#validate(EObject, DiagnosticChain, Map)
   */
  boolean validate(EAnnotation eAnnotation, DiagnosticChain diagnostics, Map<Object, Object> context);
}
