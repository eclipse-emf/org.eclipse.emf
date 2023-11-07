/**
 * Copyright (c) 2017 Eclipse contributors and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 */
package org.eclipse.emf.ecore.util;


import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.MissingResourceException;

import org.eclipse.emf.common.util.BasicDiagnostic;
import org.eclipse.emf.common.util.Diagnostic;
import org.eclipse.emf.common.util.DiagnosticChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.ResourceLocator;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EAnnotation;
import org.eclipse.emf.ecore.EAnnotationValidator;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EModelElement;
import org.eclipse.emf.ecore.ENamedElement;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.ETypedElement;
import org.eclipse.emf.ecore.EValidator;
import org.eclipse.emf.ecore.EcoreFactory;
import org.eclipse.emf.ecore.EcorePackage;
import org.eclipse.emf.ecore.impl.EPackageImpl;
import org.eclipse.emf.ecore.plugin.EcorePlugin;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.xml.type.XMLTypeFactory;


/**
 * A basic implementation of an {@link EAnnotationValidator annotation validator}.
 * <p>
 * An implementation must specialize the {@link #getResourceLocator()} method in order for the {@link #getValidLocationDescription()} method to function correctly.
 * The most straight-forward way to implement an annotation validator is to model the supported keys,
 * specializing {@link #getPropertyClasses(EModelElement)} with one or more {@link EClass classes} that
 * can be {@link #createInstance(EClass, EAnnotation) instantiated} to represent the information in the annotation.
 * These classes are used to induce {@link #getProperties(EModelElement) a mapping} of keys onto the underlying annotation model's features.
 * If the annotation model includes references,
 * {@link #validateReferenceDetailValueLiteral(EAnnotation, EModelElement, Map.Entry, EReference, String, List, DiagnosticChain, Map) validateReferenceDetailValueLiteral}
 * and {@link #convertPropertyReferenceValueToLiteralItem(EObject, EReference, Object)} must also be specialized.
 * Alternatively an implementation can specialize {@link #validateDetail(EAnnotation, EModelElement, Map.Entry, DiagnosticChain, Map)} without providing a modeled representation.
 * The annotation validator's {@link #getAssistant() assistant} is especially useful for inducing a user interface based on the modeled annotation representation.
 * </p>
 *
 * @see EAnnotationValidator
 * @see Assistant
 * @since 2.14
 */
public abstract class BasicEAnnotationValidator implements EAnnotationValidator
{
  /**
   * @see #reportInvalidLocation(EAnnotation, DiagnosticChain, Map)
   */
  public static final int INVALID_LOCATION = 1;

  /**
   * @see #reportDuplicate(EAnnotation, EAnnotation, EModelElement, DiagnosticChain, Map)
   */
  public static final int INVALID_DUPLICATE = 2;

  /**
   * @see #reportInvalidReferenceLiteral(EAnnotation, EModelElement, Map.Entry, EReference, String, DiagnosticChain, Map)
   */
  public static final int INVALID_REFERENCE_LITERAL = 3;

  /**
   * @see #createValueDiagnostic(EAnnotation, EModelElement, Map.Entry, EStructuralFeature)
   */
  public static final int INVALID_DETAIL_VALUE = 4;

  /**
   * @see #reportInvalidValueLiteral(EAnnotation, EModelElement, Map.Entry, EAttribute, String, EDataType, DiagnosticChain, RuntimeException, Map)
   */
  public static final int INVALID_VALUE_LITERAL = 5;

  /**
   * @see #reportIgnoredAnnotations(EAnnotation, EModelElement, Collection, DiagnosticChain, Map)
   */
  public static final int IGNORED_ANNOTATIONS = 6;

  /**
   * @see #reportIgnoredContents(EAnnotation, EModelElement, Collection, DiagnosticChain, Map)
   */
  public static final int IGNORED_CONTENTS = 7;

  /**
   * @see #reportIgnoredReferences(EAnnotation, EModelElement, Collection, DiagnosticChain, Map)
   */
  public static final int IGNORED_REFERENCES = 8;

  /**
   * @see #reportInvalidReference(EAnnotation, EModelElement, EObject, DiagnosticChain, Map)
   */
  public static final int INVALID_REFERENCE = 9;

  /**
   * @see #reportInvalidAnnotation(EAnnotation, EModelElement, EAnnotation, DiagnosticChain, Map)
   */
  public static final int INVALID_ANNOTATION = 10;

  /**
   * @see #reportInvalidContent(EAnnotation, EModelElement, EObject, DiagnosticChain, Map)
   */
  public static final int INVALID_CONTENT = 11;

  /**
   * @see #reportIgnoredEntry(EAnnotation, EModelElement, Map.Entry, DiagnosticChain, Map)
   */
  public static final int IGNORED_ENTRY = 12;

  /**
   * @see #reportMissingEntry(EAnnotation, EModelElement, String, EStructuralFeature, DiagnosticChain, Map)
   */
  public static final int MISSING_ENTRY = 13;

  /**
   * @see #reportMissingRequiredEntryValue(EAnnotation, EModelElement, EStructuralFeature, List, DiagnosticChain, Map)
   */
  public static final int MISSING_REQUIRED_ENTRY_VALUE = 14;

  /**
   * @see #reportTooFewValues(EAnnotation, EModelElement, Map.Entry, EStructuralFeature, List, int, int, DiagnosticChain, Map)
   */
  public static final int TOO_FEW_VALUES = 15;

  /**
   * @see #reportTooManyValues(EAnnotation, EModelElement, Map.Entry, EStructuralFeature, List, int, int, DiagnosticChain, Map)
   */
  public static final int TOO_MANY_VALUES = 16;

  /**
   * The {@link EAnnotation#getSource() annotation source} validated by this annotation validator.
   */
  protected final String annotationSource;

  /**
   * The name used in messages for this validator's annotations.
   */
  protected final String annotationName;

  /**
   * The {@link Diagnostic#getSource() source} used in this validator's diagnostics.
   */
  protected final String diagnosticSource;

  /**
   * The {@link Assistant assistant} used by the framework to induce a user interface.
   */
  protected final Assistant assistant;

  /**
   * Creates an instance for the given {@link EAnnotation#getSource() annotation source} validated by this annotation validator.
   *
   * @param annotationSource the annotation source validated by this annotation validator.
   * @param annotationName the name used in this validator's diagnostics
   * @param diagnosticSource the {@link Diagnostic#getSource() diagnostic source} used in this validator's diagnostics.
   */
  public BasicEAnnotationValidator(String annotationSource, String annotationName, String diagnosticSource)
  {
    this.annotationSource = annotationSource;
    this.annotationName = annotationName;
    this.diagnosticSource = diagnosticSource;
    this.assistant = createAssistant();
  }

  /**
   * {@inheritDoc}
   */
  public String getAnnotationSource()
  {
    return annotationSource;
  }

  /**
   * Returns the resource locator for fetching implementation-specific messages.
   * @return the resource locator for fetching model-specific messages.
   */
  protected abstract ResourceLocator getResourceLocator();

  /**
   * Returns the model classes used to represent annotations for the given model element.
   * <p>
   * Typically an annotation validator implementation will return a single class.
   * An induced user interface will generally require the ability to {@link #createInstance(EClass, EAnnotation) create instances} of the classes returned by this method.
   * The annotation validator implementation itself does not require that ability.
   * </p>
   * @param eModelElement the model element in question.
   * @return the model classes used to represent annotations for the given model element.
   */
  protected abstract List<EClass> getPropertyClasses(EModelElement eModelElement);

  /**
   * Returns the assistant provided by this annotation validator
   * which is generally useful to provide access to protected methods that are needed primarily for inducing a user interface that represents the annotations in a more structured form.
   * @return the assistant provided by this annotation validator.
   */
  public Assistant getAssistant()
  {
    return assistant;
  }

  /**
   * Creates the assistant.
   * <p>
   * Generally derived classes will not need to specialize this method because all methods of the assistant delegate back to the annotation validator.
   * </p>
   * @return the assistant.
   */
  protected Assistant createAssistant()
  {
    return new Assistant(this)
      {
      };
  }

  /**
   * {@inheritDoc}
   * <p>
   * This implementation returns <code>false</code> if the containing {@link EAnnotation#getEModelElement()} is <code>null</code>,
   * if the {@link #isValidLocation(EAnnotation, EModelElement)} returns <code>false</code> for the containing model element,
   * or if this is not the first annotation with this annotation source in the model element and {@link #isDuplicateValid(EModelElement, EAnnotation, EAnnotation) isDuplicateValue} returns <code>false</code>.
   * </p>
   */
  public boolean isValidLocation(EAnnotation eAnnotation)
  {
    EModelElement eModelElement = eAnnotation.getEModelElement();
    if (eModelElement == null || !isValidLocation(eAnnotation, eModelElement))
    {
      return false;
    }
    else
    {
      EAnnotation otherEAnnotation = eModelElement.getEAnnotation(annotationSource);
      return otherEAnnotation == null || otherEAnnotation == eAnnotation || isDuplicateValid(eModelElement, otherEAnnotation, eAnnotation);
    }
  }

  /**
   * Returns whether this annotation {@link EAnnotation#getEModelElement() contained} by this model element is valid at this location.
   * <p>
   * This implementation returns <code>false</code> if the element is not a {@link ENamedElement named element}.
   * It's typically the case that annotations on annotations aren't meaningful and valid.
   * </p>
   * @param eAnnotation the annotation in question.
   * @param eModelElement the annotation's {@link EAnnotation#getEModelElement() containing} model element.
   * @return whether this annotation contained by this model element is valid at this location.
   *
   * @see #isValidLocation(EAnnotation)
   * @see #validate(EAnnotation, DiagnosticChain, Map)
   */
  protected boolean isValidLocation(EAnnotation eAnnotation, EModelElement eModelElement)
  {
    return eModelElement instanceof ENamedElement;
  }

  /**
   * Returns whether the given two annotations, both with the annotation validator's annotation source, both {@link EModelElement#getEAnnotation(String) contained} by the given model element, are valid.
   * <p>
   * This implementation returns <code>false</code> because it's typically the case that only the primary annotation is meaningful and valid.
   * </p>
   * @param eModelElement the model element that contains both annotations in its {@link EModelElement#getEAnnotations() annotations} feature.
   * @param primaryEAnnotation the first annotation in the model element's details.
   * @param secondaryEAnnotation a subsequent annotation in the model element's details.
   * @return whether these two annotations, both of which have this annotation validator's annotation source, are valid.
   * @see #isValidLocation(EAnnotation)
   * @see #validate(EAnnotation, DiagnosticChain, Map)
   */
  protected boolean isDuplicateValid(EModelElement eModelElement, EAnnotation primaryEAnnotation, EAnnotation secondaryEAnnotation)
  {
    return false;
  }

  /**
   * {@inheritDoc}
   * <p>
   * This implementation checks if the {@link #isValidLocation(EAnnotation, EModelElement) location is valid}
   * and {@link #reportInvalidLocation(EAnnotation, DiagnosticChain, Map) reports an invalid location} if it is not.
   * Then checks for {@link #isDuplicateValid(EModelElement, EAnnotation, EAnnotation) invalid duplicates}
   * and {@link #reportDuplicate(EAnnotation, EAnnotation, EModelElement, DiagnosticChain, Map) reports a duplicate} if it is an invalid duplicate.
   * Then it {@link #validateReferences(EAnnotation, EModelElement, DiagnosticChain, Map) validates the references} and {@link #validateDetails(EAnnotation, EModelElement, DiagnosticChain, Map) validates the details}.
   * </p>
   */
  public boolean validate(EAnnotation eAnnotation, DiagnosticChain diagnostics, Map<Object, Object> context)
  {
    boolean result;
    EModelElement eModelElement = eAnnotation.getEModelElement();
    if (eModelElement != null && isValidLocation(eAnnotation, eModelElement))
    {
      EAnnotation otherEAnnotation = eModelElement.getEAnnotation(annotationSource);
      if (otherEAnnotation == eAnnotation || isDuplicateValid(eModelElement, otherEAnnotation, eAnnotation))
      {
        result = validateReferences(eAnnotation, eModelElement, diagnostics, context);
        if (result || diagnostics != null)
        {
          result &= validateContents(eAnnotation, eModelElement, diagnostics, context);
        }
        if (result || diagnostics != null)
        {
          result &= validateAnnotations(eAnnotation, eModelElement, diagnostics, context);
        }
        if (result || diagnostics != null)
        {
          result &= validateDetails(eAnnotation, eModelElement, diagnostics, context);
        }
      }
      else
      {
        result = false;
        if (diagnostics != null)
        {
          reportDuplicate(otherEAnnotation, eAnnotation, eModelElement, diagnostics, context);
        }
      }
    }
    else
    {
      result = false;
      if (diagnostics != null)
      {
        reportInvalidLocation(eAnnotation, diagnostics, context);
      }
    }
    return result;
  }

  /**
   * Returns whether this annotation's {@link EAnnotation#getReferences() references} are valid.
   * <p>
   * This implementation checks whether {@link #isReferencesSupported(EAnnotation, EModelElement) references are supported}.
   * If not, it checks whether the references are empty and if not {@link #reportIgnoredReferences(EAnnotation, EModelElement, Collection, DiagnosticChain, Map) reports ignored references}.
   * If references are supported, then for each reference, it tests whether that reference is among the {@link #getValidReferences(EAnnotation, EModelElement, Collection) valid references},
   * passing in this annotation's references to determine the valid references,
   * and {@link #reportInvalidReference(EAnnotation, EModelElement, EObject, DiagnosticChain, Map) reports an invalid reference} for each not present in the valid references.
   * </p>
   * <p>
   * It's typically the case that annotations ignore references.
   * If that's not the case, specialize {@link #isReferencesSupported(EAnnotation, EModelElement)}
   * and {@link #getValidReferences(EAnnotation, EModelElement, Collection)}.
   * An implementation may override this method to report missing required references.
   * </p>
   * @param eAnnotation the annotation in question.
   * @param eModelElement the annotation's {@link EAnnotation#getEModelElement() containing} model element.
   * @param diagnostics a place to accumulate diagnostics; if it's <code>null</code>, no diagnostics should be produced.
   * @param context a place to cache information, if it's <code>null</code>, no cache is supported.
   * @return whether this annotation's references are valid.
   * @see #isReferencesSupported(EAnnotation, EModelElement)
   */
  protected boolean validateReferences(EAnnotation eAnnotation, EModelElement eModelElement, DiagnosticChain diagnostics, Map<Object, Object> context)
  {
    EList<EObject> references = eAnnotation.getReferences();
    if (!isReferencesSupported(eAnnotation, eModelElement))
    {
      boolean result = references.isEmpty();
      if (!result && diagnostics != null)
      {
        reportIgnoredReferences(eAnnotation, eModelElement, references, diagnostics, context);
      }
      return result;
    }
    else
    {
      boolean result = true;
      Collection<?> validReferences = getValidReferences(eAnnotation, eModelElement, references);
      for (EObject reference : references)
      {
        if (!validReferences.contains(reference))
        {
          result = false;
          if (diagnostics == null)
          {
            break;
          }
          else
          {
            reportInvalidReference(eAnnotation, eModelElement, reference, diagnostics, context);
          }
        }
      }
      return result;
    }
  }

  /**
   * Returns whether {@link EAnnotation#getReferences() references} are meaningful for this annotation.
   * <p>
   * This method used to determine how references should be {@link #validateReferences(EAnnotation, EModelElement, DiagnosticChain, Map) validated}.
   * Also, an induced user interface should avoid providing the ability to specify references when this returns <code>false</code>.
   * Implementations that override this to ever return <code>true</code> should also override {@link #getValidReferences(EAnnotation, EModelElement, Collection)} to control the valid choices.
   * </p>
   * @param eAnnotation the annotation in question.
   * @param eModelElement the annotation's {@link EAnnotation#getEModelElement() containing} model element.
   * @return whether references are meaningful for this annotation.
   * @see Assistant#isReferencesSupported(EAnnotation)
   * @see #validateReferences(EAnnotation, EModelElement, DiagnosticChain, Map)
   */
  protected boolean isReferencesSupported(EAnnotation eAnnotation, EModelElement eModelElement)
  {
    return false;
  }

  /**
   * Returns the filtered collection of references that are valid for this annotation.
   * <p>
   * An induced user interface should provide the ability to specify only the references returned by this method.
   * The references argument may contain all reachable objects, some subset there of, or none at all;
   * an implementation may choose to filter from this collection or to provide its own result, including objects not in this collection.
   * This implementation returns the references argument if {@link #isReferencesSupported(EAnnotation, EModelElement) references are supported}, or an empty list otherwise.
   * It is also used to {@link #validateReferences(EAnnotation, EModelElement, DiagnosticChain, Map) determine} which references are valid.
   * An implementation that overrides this should also override {@link #isReferencesSupported(EAnnotation, EModelElement)}.
   * </p>
   * @param eAnnotation the annotation in question.
   * @param eModelElement the annotation's {@link EAnnotation#getEModelElement() containing} model element.
   * @param references all reachable objects, some subset there of, or none at all.
   * @return the objects that are valid as references for this annotation.
   * @see Assistant#getValidReferences(EAnnotation, Collection)
   */
  protected Collection<?> getValidReferences(EAnnotation eAnnotation, EModelElement eModelElement, Collection<?> references)
  {
    return isReferencesSupported(eAnnotation, eModelElement) ? references : Collections.emptyList();
  }

  /**
   * Returns whether this annotation's {@link EAnnotation#getContents() contents} are valid.
   * <p>
   * This implementation checks whether {@link #isContentsSupported(EAnnotation, EModelElement) contents are supported}.
   * If not, it checks whether the contents are empty and if not {@link #reportIgnoredContents(EAnnotation, EModelElement, Collection, DiagnosticChain, Map) reports ignored contents}.
   * If contents are supported, then for each content, it tests whether that content is among the {@link #getValidContents(EAnnotation, EModelElement, Collection) valid contents},
   * passing in this annotation's contents to determine the valid contents,
   * and {@link #reportInvalidContent(EAnnotation, EModelElement, EObject, DiagnosticChain, Map) reports an invalid content} for each not present in the valid contents.
   * </p>
   * <p>
   * It's typically the case that annotations ignore contents.
   * If that's not the case, specialize {@link #isContentsSupported(EAnnotation, EModelElement)}
   * and {@link #getValidContents(EAnnotation, EModelElement, Collection)}.
   * An implementation may override this method to report missing required contents.
   * </p>
   * @param eAnnotation the annotation in question.
   * @param eModelElement the annotation's {@link EAnnotation#getEModelElement() containing} model element.
   * @param diagnostics a place to accumulate diagnostics; if it's <code>null</code>, no diagnostics should be produced.
   * @param context a place to cache information, if it's <code>null</code>, no cache is supported.
   * @return whether this annotation's contents are valid.
   * @see #isContentsSupported(EAnnotation, EModelElement)
   * @see #getValidContents(EAnnotation, EModelElement, Collection)
   */
  protected boolean validateContents(EAnnotation eAnnotation, EModelElement eModelElement, DiagnosticChain diagnostics, Map<Object, Object> context)
  {
    EList<EObject> contents = eAnnotation.getContents();
    if (!isContentsSupported(eAnnotation, eModelElement))
    {
      boolean result = contents.isEmpty();
      if (!result && diagnostics != null)
      {
        reportIgnoredContents(eAnnotation, eModelElement, contents, diagnostics, context);
      }
      return result;
    }
    else
    {
      boolean result = true;
      Collection<?> validContents = getValidContents(eAnnotation, eModelElement, contents);
      for (EObject content : contents)
      {
        if (!validContents.contains(content))
        {
          result = false;
          if (diagnostics == null)
          {
            break;
          }
          else
          {
            reportInvalidContent(eAnnotation, eModelElement, content, diagnostics, context);
          }
        }
      }
      return result;
    }
  }

  /**
   * Returns whether {@link EAnnotation#getContents() contents} are meaningful for this annotation.
   * <p>
   * This method used to determine how contents should be {@link #validateContents(EAnnotation, EModelElement, DiagnosticChain, Map) validated}.
   * Also, an induced user interface should avoid providing the ability to specify contents when this returns <code>false</code>.
   * Implementations that override this to ever return <code>true</code> should also override {@link #getValidContents(EAnnotation, EModelElement, Collection)} to control the valid choices.
   * </p>
   * @param eAnnotation the annotation in question.
   * @param eModelElement the annotation's {@link EAnnotation#getEModelElement() containing} model element.
   * @return whether contents are meaningful for this annotation.
   * @see Assistant#isContentsSupported(EAnnotation)
   * @see #validateContents(EAnnotation, EModelElement, DiagnosticChain, Map)
   */
  protected boolean isContentsSupported(EAnnotation eAnnotation, EModelElement eModelElement)
  {
    return false;
  }

  /**
   * Returns the filtered collection of contents that are valid for this annotation.
   * <p>
   * An induced user interface should provide the ability to specify only the contents returned by this method.
   * The contents argument may contain nothing at all, or the {@link EAnnotation#getContents() current contents} of the annotation;
   * an implementation may choose to filter from this collection or to provide its own result, including objects not in this collection
   * but it should not remove objects currently contained by the annotation that are valid.
   * This implementation returns the contents argument if {@link #isContentsSupported(EAnnotation, EModelElement) contents are supported}, or an empty list otherwise.
   * It is also used to {@link #validateContents(EAnnotation, EModelElement, DiagnosticChain, Map) determine} which contents are valid
   * and should therefore <b>not</b> remove values from the provided contents argument if they are valid.
   * An implementation that overrides this should also override {@link #isContentsSupported(EAnnotation, EModelElement)}.
   * </p>
   * @param eAnnotation the annotation in question.
   * @param eModelElement the annotation's {@link EAnnotation#getEModelElement() containing} model element.
   * @param contents nothing at all, or the {@link EAnnotation#getContents() current or potential contents} of the annotation.
   * @return the objects that are valid as contents for this annotation.
   * @see Assistant#getValidContents(EAnnotation, Collection)
   */
  protected Collection<? extends EObject> getValidContents(EAnnotation eAnnotation, EModelElement eModelElement, Collection<? extends EObject> contents)
  {
    return isContentsSupported(eAnnotation, eModelElement) ? contents : Collections.<EObject> emptyList();
  }

  /**
   * Returns whether this annotation's {@link EAnnotation#getEAnnotations() nested annotations} are valid.
   * <p>
   * This implementation iterates over the nested annotations, and if there is at least one for which there is no {@link org.eclipse.emf.ecore.EAnnotationValidator.Registry#getEAnnotationValidator(String) registered annotation validator}
   * or for which the registered annotation validator does not consider this nested annotation {@link #isValidLocation(EAnnotation) valid at this location},
   * it {@link #reportIgnoredAnnotations(EAnnotation, EModelElement, Collection, DiagnosticChain, Map) reports ignored annotations}.
   * It's typically the case that annotations ignore nested annotations.
   * If that's not the case, you should override this method and specialize {@link #isAnnotationsSupported(EAnnotation, EModelElement)}
   * and consider specializing {@link #getValidAnnotations(EAnnotation, EModelElement, Collection)}
   * </p>
   * <p>
   * This implementation checks whether {@link #isAnnotationsSupported(EAnnotation, EModelElement) nested annotations are supported}.
   * If not, it checks whether the {@link #getValidAnnotations(EAnnotation, EModelElement, Collection) valid annotations},
   * passing in this annotation's nested annotations to determine the valid annotations,
   * contain all the nested annotations; if not it {@link #reportIgnoredAnnotations(EAnnotation, EModelElement, Collection, DiagnosticChain, Map) reports ignored annotations}.
   * If nested annotations are supported, then for each nested annotation, it tests whether that annotation is among the {@link #getValidAnnotations(EAnnotation, EModelElement, Collection) valid annotations}
   * and {@link #reportInvalidAnnotation(EAnnotation, EModelElement, EAnnotation, DiagnosticChain, Map) reports an invalid annotation} for each not present in the valid annotations.
   * </p>
   * <p>
   * It's typically the case that annotations ignore nested annotations.
   * If that's not the case, specialize {@link #isAnnotationsSupported(EAnnotation, EModelElement)}
   * and {@link #getValidAnnotations(EAnnotation, EModelElement, Collection)}.
   * An implementation may override this method to report missing required nested annotations.
   * </p>
   * @param eAnnotation the annotation in question.
   * @param eModelElement the annotation's {@link EAnnotation#getEModelElement() containing} model element.
   * @param diagnostics a place to accumulate diagnostics; if it's <code>null</code>, no diagnostics should be produced.
   * @param context a place to cache information, if it's <code>null</code>, no cache is supported.
   * @return whether this annotation's nested annotations are valid.
   * @see #isAnnotationsSupported(EAnnotation, EModelElement)
   */
  protected boolean validateAnnotations(EAnnotation eAnnotation, EModelElement eModelElement, DiagnosticChain diagnostics, Map<Object, Object> context)
  {
    EList<EAnnotation> annotations = eAnnotation.getEAnnotations();
    if (!annotations.isEmpty())
    {
      Collection<? extends EAnnotation> validAnnotations = getValidAnnotations(eAnnotation, eModelElement, annotations);
      if (!isAnnotationsSupported(eAnnotation, eModelElement))
      {
        if (!validAnnotations.containsAll(annotations))
        {
          if (diagnostics != null)
          {
            List<EAnnotation> ignoredAnnotations = new ArrayList<EAnnotation>(annotations);
            ignoredAnnotations.removeAll(validAnnotations);
            reportIgnoredAnnotations(eAnnotation, eModelElement, ignoredAnnotations, diagnostics, context);
          }
          return false;
        }
        else
        {
          return true;
        }
      }
      else
      {
        boolean result = true;
        for (EAnnotation nestedEAnnotation : annotations)
        {
          if (!validAnnotations.contains(nestedEAnnotation))
          {
            result = false;
            if (diagnostics == null)
            {
              break;
            }
            else
            {
              reportInvalidAnnotation(eAnnotation, eModelElement, nestedEAnnotation, diagnostics, context);
            }
          }
        }
        return result;
      }
    }
    else
    {
      return true;
    }
  }

  /**
   * Returns whether {@link EAnnotation#getEAnnotations() nested annotations} are meaningful for this annotation.
   * <p>
   * This method used to determine how nested annotations should be {@link #validateAnnotations(EAnnotation, EModelElement, DiagnosticChain, Map) validated}.
   * Also, an induced user interface should avoid providing the ability to specify nested annotations when this returns <code>false</code>.
   * Implementations that override this to ever return <code>true</code> should also override {@link #getValidAnnotations(EAnnotation, EModelElement, Collection)} to control the valid choices.
   * </p>
   * @param eAnnotation the annotation in question.
   * @param eModelElement the annotation's {@link EAnnotation#getEModelElement() containing} model element.
   * @return whether nested annotations are meaningful for this annotation.
   * @see Assistant#isAnnotationsSupported(EAnnotation)
   * @see #validateAnnotations(EAnnotation, EModelElement, DiagnosticChain, Map)
   */
  protected boolean isAnnotationsSupported(EAnnotation eAnnotation, EModelElement eModelElement)
  {
    return false;
  }

  /**
   * Returns the filtered collection of nested annotations that are valid for this annotation.
   * <p>
   * The annotations argument typically contains the {@link EModelElement#getEAnnotations() current nested annotations} of the specified annotation;
   * an implementation may choose to filter from this collection,
   * but it should <b>not</b> remove nested annotations currently contained by the annotation that are valid.
   * This implementation takes into account the fact that annotations may be specifically designed to annotate other annotations,
   * i.e., that the nested annotation source might correspond to a {@link org.eclipse.emf.ecore.EAnnotationValidator.Registry#getEAnnotationValidator(String) registered annotation validator}
   * that considers its annotations {@link BasicEAnnotationValidator#isValidLocation(EAnnotation, EModelElement) valid} when contained by the specified annotation.
   * As such, this implementation does not remove nested annotations for which there is a registered validator that considers its annotation valid in the specified annotation.
   * Note that this method is used to {@link #validateAnnotations(EAnnotation, EModelElement, DiagnosticChain, Map) determine} which nested annotations are valid
   * and that is why it should <b>not</b> remove values from the provided annotations argument if they are valid.
   * </p>
   * @param eAnnotation the annotation in question.
   * @param eModelElement the annotation's {@link EAnnotation#getEModelElement() containing} model element.
   * @param annotations typically the {@link EModelElement#getEAnnotations() current nested annotations} of the annotation.
   * @return the nested annotations that are valid as annotations for this annotation.
   * @see Assistant#getValidAnnotations(EAnnotation, Collection)
   */
  protected Collection<? extends EAnnotation> getValidAnnotations(EAnnotation eAnnotation, EModelElement eModelElement, Collection<? extends EAnnotation> annotations)
  {
    List<EAnnotation> result = new ArrayList<EAnnotation>(annotations);
    for (EAnnotation nestedEAnnotation : annotations)
    {
      EAnnotationValidator eAnnotationValidator = EAnnotationValidator.Registry.INSTANCE.getEAnnotationValidator(nestedEAnnotation.getSource());
      if (!(eAnnotationValidator instanceof BasicEAnnotationValidator) || !((BasicEAnnotationValidator)eAnnotationValidator).isValidLocation(nestedEAnnotation, eAnnotation))
      {
        result.remove(nestedEAnnotation);
      }
    }
    return result;
  }

  /**
   * Returns the filtered collection of nested annotations that are valid for this annotation.
   * <p>
   * An induced user interface should provide the ability to specify only the nested annotations returned by this method.
   * The annotations argument may contain nothing at all, or the {@link EModelElement#getEAnnotations() current nested annotations} of the specified annotation;
   * an implementation may choose to filter from this collection or to provide its own result, including objects not in this collection,
   * but it should <b>not</b> remove nested annotations currently contained by the annotation that are valid.
   * This implementation takes into account the fact that annotations may be specifically designed to annotate other annotations,
   * i.e., that the nested annotation source might correspond to a {@link org.eclipse.emf.ecore.EAnnotationValidator.Registry#getEAnnotationValidator(String) registered annotation validator}
   * that considers its annotations {@link BasicEAnnotationValidator#isValidLocation(EAnnotation, EModelElement) valid} when contained by the specified annotation.
   * As such, this implementation does not remove nested annotations for which there is a registered validator that considers its annotation valid in the specified annotation.
   * Also, this implementation's result will include an additional annotation for each registered annotation validator that considers its annotations valid when nested in this annotation.
   * In fact, an override should <b>only</b> add values to those returned by this implementation.
   * An implementation that overrides this method should also override {@link #isAnnotationsSupported(EAnnotation, EModelElement)}.
   * </p>
   * @param eAnnotation the annotation in question.
   * @param eModelElement the annotation's {@link EAnnotation#getEModelElement() containing} model element.
   * @param annotations nothing at all, or the {@link EModelElement#getEAnnotations() current or potential nested annotations} of the annotation.
   * @return the nested annotations that are valid as annotations for this annotation.
   * @see Assistant#getValidAnnotations(EAnnotation, Collection)
   */
  protected Collection<? extends EAnnotation> getAllValidAnnotations(EAnnotation eAnnotation, EModelElement eModelElement, Collection<? extends EAnnotation> annotations)
  {
    List<EAnnotation> result = new ArrayList<EAnnotation>(getValidAnnotations(eAnnotation, eModelElement, annotations));
    for (String annotationSource : EAnnotationValidator.Registry.INSTANCE.keySet())
    {
      EAnnotationValidator eAnnotationValidator;
      try
      {
        eAnnotationValidator = EAnnotationValidator.Registry.INSTANCE.getEAnnotationValidator(annotationSource);
      }
      catch (RuntimeException exception)
      {
        eAnnotationValidator = null;
        EcorePlugin.INSTANCE.log(exception);
      }

      if (eAnnotationValidator instanceof BasicEAnnotationValidator)
      {
        BasicEAnnotationValidator basicEAnnotationValidator = (BasicEAnnotationValidator)eAnnotationValidator;
        EAnnotation nestedEAnnotation = EcoreFactory.eINSTANCE.createEAnnotation();
        nestedEAnnotation.setSource(annotationSource);
        EAnnotation otherEAnnotation = eAnnotation.getEAnnotation(annotationSource);
        if ((otherEAnnotation == null || basicEAnnotationValidator.isDuplicateValid(eAnnotation, otherEAnnotation, nestedEAnnotation))
          && ((BasicEAnnotationValidator)eAnnotationValidator).isValidLocation(nestedEAnnotation, eAnnotation))
        {
          result.add(nestedEAnnotation);
        }
      }
    }
    return result;
  }

  /**
   * Returns whether this annotation's {@link EAnnotation#getDetails() details} are valid.
   * <p>
   * This implementation uses the {@link #getProperties(EModelElement) properties of the model element}.
   * For each detail, it determines whether there is a corresponding feature in the properties.
   * If not, it validates the detail {@link #validateDetail(EAnnotation, EModelElement, java.util.Map.Entry, DiagnosticChain, Map) without a property feature}.
   * If so, it validates the detail {@link #validateFeatureDetail(EAnnotation, EModelElement, java.util.Map.Entry, EStructuralFeature, DiagnosticChain, Map) with the property feature}.
   * If all the details are valid,
   * it will check whether any {@link EStructuralFeature#isRequired() required} property feature is absent from the details
   * and {@link #reportMissingEntry(EAnnotation, EModelElement, String, EStructuralFeature, DiagnosticChain, Map) reports it missing}.
   * </p>
   * @param eAnnotation the annotation in question.
   * @param eModelElement the annotation's {@link EAnnotation#getEModelElement() containing} model element.
   * @param diagnostics a place to accumulate diagnostics; if it's <code>null</code>, no diagnostics should be produced.
   * @param context a place to cache information, if it's <code>null</code>, no cache is supported.
   * @return whether this annotation's details are valid.
   */
  protected boolean validateDetails(EAnnotation eAnnotation, EModelElement eModelElement, DiagnosticChain diagnostics, Map<Object, Object> context)
  {
    boolean result = true;
    Map<String, EStructuralFeature> properties = new LinkedHashMap<String, EStructuralFeature>(getProperties(eModelElement));
    for (Map.Entry<String, String> entry : eAnnotation.getDetails())
    {
      String key = entry.getKey();
      EStructuralFeature feature = properties.remove(key);
      if (feature == null)
      {
        result &= validateDetail(eAnnotation, eModelElement, entry, diagnostics, context);
      }
      else
      {
        result &= validateFeatureDetail(eAnnotation, eModelElement, entry, feature, diagnostics, context);
      }
      if (!result && diagnostics == null)
      {
        return false;
      }
    }

    if (result)
    {
      for (Map.Entry<String, EStructuralFeature> entry : properties.entrySet())
      {
        EStructuralFeature feature = entry.getValue();
        if (feature.isRequired())
        {
          result = false;
          if (diagnostics == null)
          {
            break;
          }
          else
          {
            reportMissingEntry(eAnnotation, eModelElement, entry.getKey(), feature, diagnostics, context);
          }
        }
      }
    }

    return result;
  }

  /**
   * Returns a map from key to {@link EStructuralFeature feature}.
   * These represents the keys that are considered valid and can be processed by this annotation validator.
   * <p>
   * This implementation uses {@link #getPropertyClasses(EModelElement)}, iterating over each class and each feature of each class, adding to the map each {@link #isIncludedProperty(EModelElement, EClass, EStructuralFeature) included} feature.
   * If that method returns an empty list, then implementation returns an empty map.
   * In that case, {@link #validateDetails(EAnnotation, EModelElement, DiagnosticChain, Map) validating the details} of any annotation will report all detail entries as being ignored.
   * An annotation validator implement must override either this method, the <code>getPropertyClasses</code> method or the <code>validateDetails</code> method.
   * </p>
   * @param eModelElement the model element that is being annotated.
   * @return a map from key to feature.
   * @see #validateDetails(EAnnotation, EModelElement, DiagnosticChain, Map)
   * @see #getPropertyClasses(EModelElement)
   */
  protected Map<String, EStructuralFeature> getProperties(EModelElement eModelElement)
  {
    Map<String, EStructuralFeature> properties = new LinkedHashMap<String, EStructuralFeature>();
    for (EClass eClass : getPropertyClasses(eModelElement))
    {
      for (EStructuralFeature eStructuralFeature : eClass.getEAllStructuralFeatures())
      {
        if (isIncludedProperty(eModelElement, eClass, eStructuralFeature))
        {
          properties.put(eStructuralFeature.getName(), eStructuralFeature);
        }
      }
    }
    return Collections.unmodifiableMap(properties);
  }

  /**
   * Returns whether the given structural feature of the given class for the given model element is {@link #getProperties(EModelElement) included as a property}.
   * @param eModelElement the model element.
   * @param eClass the class used to model the annotation for the model element.
   * @param eStructuralFeature a structural feature of the class.
   * @return whether the given structural feature of the given class for the given model element is included as a property.
   */
  protected boolean isIncludedProperty(EModelElement eModelElement, EClass eClass, EStructuralFeature eStructuralFeature)
  {
    return true;
  }

  /**
   * Creates an instance of the modeled representation for the given annotation.
   * <p>
   * This implementation {@link EcoreUtil#create(EClass) creates} an instance and {@link #initialize(EObject, EAnnotation) initializes} it.
   * </p>
   * @param eClass the class to be instantiated.
   * @param eAnnotation the annotation with the information that needs to be represented.
   * @return creates an instance of the modeled representation for the given annotation.
   */
  protected EObject createInstance(EClass eClass, EAnnotation eAnnotation)
  {
    EModelElement eModelElement = eAnnotation.getEModelElement();
    if (!getPropertyClasses(eModelElement).contains(eClass))
    {
      throw new IllegalArgumentException("The eClass is not a property class of the model element");
    }
    else
    {
      return initialize(EcoreUtil.create(eClass), eAnnotation);
    }
  }

  /**
   * Returns an initialized instance of the given object for the given annotation.
   * <p>
   * This implementation handles only the case of modeled attributes.
   * For each {@link EAnnotation#getDetails() detail entry},
   * it looks up the corresponding {@link #getProperties(EModelElement) property} via the key.
   * If it's not an {@link EAttribute} it will throw an {@link UnsupportedOperationException}.
   * If the attribute property is {@link EStructuralFeature#isMany() multi-valued},
   * it {@link #split(EAnnotation, EModelElement, Map.Entry, String, EStructuralFeature, DiagnosticChain, Map) splits} the detail entry value, and {@link EcoreUtil#convertToString(EDataType, Object) converts} each literal item into a value of the {@link EAttribute#getEAttributeType() attribute's data type}.
   * If it's single-valued, the literal value is directly converted to the attributes data type.
   * The resulting list value or single value is {@link EObject#eSet(EStructuralFeature, Object) reflectively set} into the instance.
   * If the model representation includes references,
   * an annotation validator implementation must specialize this method for the {@link Assistant#createInstance(EClass, EAnnotation) assistant} to function correctly.
   * </p>
   * @param eObject the object to initialize.
   * @param eAnnotation the annotation used to initialize the object.
   * @return an initialized instance.
   */
  protected EObject initialize(EObject eObject, EAnnotation eAnnotation)
  {
    EModelElement eModelElement = eAnnotation.getEModelElement();
    Map<String, EStructuralFeature> properties = getProperties(eModelElement);
    for (Map.Entry<String, String> entry : eAnnotation.getDetails())
    {
      String key = entry.getKey();
      EStructuralFeature eStructuralFeature = properties.get(key);
      if (eStructuralFeature instanceof EAttribute)
      {
        EDataType eDataType = (EDataType)eStructuralFeature.getEType();
        String literalValue = entry.getValue();
        if (eStructuralFeature.isMany())
        {
          List<String> literalValues = split(eAnnotation, eModelElement, entry, literalValue, eStructuralFeature, null, null);
          List<Object> value = new ArrayList<Object>();
          if (literalValues != null)
          {
            for (String itemLiteralValue : literalValues)
            {
              value.add(EcoreUtil.createFromString(eDataType, itemLiteralValue));
            }
          }
          eObject.eSet(eStructuralFeature, value);
        }
        else
        {
          eObject.eSet(eStructuralFeature, EcoreUtil.createFromString(eDataType, literalValue));
        }
      }
      else if (eStructuralFeature != null)
      {
        throw new UnsupportedOperationException("Initializing of references is not supported");
      }
    }
    return eObject;
  }

  /**
   * Returns the value of the feature of the modeled object converted to a literal representation as used in {@link EAnnotation#getDetails() detail entry}.
   * <p>
   * This implementation handles both {@link EAttribute} and {@link EReference}.
   * For a {@link EStructuralFeature#isMany() multi-valued} feature, it {@link #convertPropertyReferenceValueToLiteralItem(EObject, EReference, Object) converts} for each item in the list, and {@link #join(EObject, EStructuralFeature, List) joins} them into a single string.
   * For a single-valued feature, it returns the {@link #convertPropertyReferenceValueToLiteralItem(EObject, EReference, Object) converted} value.
   * This method is not used by the validator but is useful for specializing the {@link Assistant#convertPropertyValueToLiteral(EObject, EStructuralFeature, Object)}.
   * </p>
   * @param eObject the modeled object.
   * @param eStructuralFeature a feature of that object.
   * @param value the value to converted to a literal representation.
   * @return the value of the feature of the modeled object converted to a literal representation.
   * @see #convertPropertyValueToLiteralItem(EObject, EStructuralFeature, Object)
   */
  protected String convertPropertyValueToLiteral(EObject eObject, EStructuralFeature eStructuralFeature, Object value)
  {
    if (eStructuralFeature.isMany())
    {
      List<String> result = new ArrayList<String>();
      if (value != null)
      {
        @SuppressWarnings("unchecked")
        List<Object> values = (List<Object>)value;
        for (Object valueItem : values)
        {
          result.add(convertPropertyValueToLiteralItem(eObject, eStructuralFeature, valueItem));
        }
      }
      return join(eObject, eStructuralFeature, result);
    }
    else
    {
      return convertPropertyValueToLiteralItem(eObject, eStructuralFeature, value);
    }
  }

  /**
   * Returns the single value of the feature's {@link ETypedElement#getEType() type} for the modeled object converted to a literal representation as used in {@link EAnnotation#getDetails() detail entry}.
   * <p>
   * This implementation delegates to {@link #convertPropertyAttributeValueToLiteralItem(EObject, EAttribute, Object)} or {@link #convertPropertyReferenceValueToLiteralItem(EObject, EReference, Object)} as appropriate.
   * </p>
   * @param eObject the modeled object.
   * @param eStructuralFeature a feature of that object.
   * @param value the value of the feature's type to converted to a literal representation.
   * @return a value of the feature's type converted to a literal representation.
   * @see #convertPropertyValueToLiteral(EObject, EStructuralFeature, Object)
   */
  protected String convertPropertyValueToLiteralItem(EObject eObject, EStructuralFeature eStructuralFeature, Object value)
  {
    if (eStructuralFeature instanceof EAttribute)
    {
      return convertPropertyAttributeValueToLiteralItem(eObject, (EAttribute)eStructuralFeature, value);
    }
    else
    {
      return convertPropertyReferenceValueToLiteralItem(eObject, (EReference)eStructuralFeature, value);
    }
  }

  /**
   * Returns the single value of the attribute's {@link ETypedElement#getEType() type} for the modeled object converted to a literal representation as used in {@link EAnnotation#getDetails() detail entry}.
   * <p>
   * This implementation simple uses {@link EcoreUtil#convertToString(EDataType, Object)}.
   * </p>
   * @param eObject the modeled object.
   * @param eAttribute an attribute feature of that object.
   * @param value the value of the feature's type to converted to a literal representation.
   * @return a value of the feature's type converted to a literal representation.
   * @see #convertPropertyValueToLiteral(EObject, EStructuralFeature, Object)
   */
  protected String convertPropertyAttributeValueToLiteralItem(EObject eObject, EAttribute eAttribute, Object value)
  {
    return EcoreUtil.convertToString(eAttribute.getEAttributeType(), value);
  }

  /**
   * Returns the single value of the references's {@link ETypedElement#getEType() type} for the modeled object converted to a literal representation as used in {@link EAnnotation#getDetails() detail entry}.
   * <p>
   * This implementation is incomplete.
   * It can't generally be known how to represent a reference to an object.
   * This implementation looks for a feature called "name", {@link EObject#eGet(EStructuralFeature) gets} the value, and if it's a string returns it.
   * Failing that, it throws an {@link UnsupportedOperationException}.
   * </p>
   * @param eObject the modeled object.
   * @param eReference a reference feature of that object.
   * @param value the value of the reference's type to converted to a literal representation.
   * @return a value of the references's type converted to a literal representation.
   * @see #convertPropertyValueToLiteral(EObject, EStructuralFeature, Object)
   */
  protected String convertPropertyReferenceValueToLiteralItem(EObject eObject, EReference eReference, Object value)
  {
    if (value == null)
    {
      return null;
    }
    else if (value instanceof EObject)
    {
      EObject valueEObject = (EObject)value;
      EStructuralFeature eStructuralFeature = valueEObject.eClass().getEStructuralFeature("name");
      if (eStructuralFeature != null)
      {
        Object name = valueEObject.eGet(eStructuralFeature);
        if (name instanceof String)
        {
          return name.toString();
        }
      }
    }

    throw new UnsupportedOperationException("Unable to convert '" + value + "' to a literal value for feature " + eReference + " of " + eObject);
  }

  /**
   * Returns the joined list of values of this modeled object's feature.
   * <p>
   * This implementation simply separates the individual literal value items with a " ".
   * </p>
   * @param eObject the modeled object.
   * @param eStructuralFeature a feature of that object.
   * @param literalValues the literal value to join into a single value.
   */
  protected String join(EObject eObject, EStructuralFeature eStructuralFeature, List<String> literalValues)
  {
    return XMLTypeFactory.eINSTANCE.convertENTITIESBase(literalValues);
  }

  /**
   * Returns whether the given feature of the given modeled representation is meaningful for the current state of the model.
   * <p>
   * This method is used to induce the {@link Assistant#getApplicableProperties(EObject, EAnnotation) applicable properties} by the assistant.
   * It is not directly used by the annotation validator itself.
   * This implementation always returns <code>true</code>.
   * </p>
   * @param eObject the modeled object in question.
   * @param eStructuralFeature a feature of that object.
   * @return whether the given feature of the given modeled representation is meaningful for the current state of the model.
   */
  protected boolean isApplicable(EObject eObject, EStructuralFeature eStructuralFeature)
  {
    return true;
  }

  /**
   * Returns whether this detail entry is valid.
   * <p>
   * This method is only called when there is no {@link #getProperties(EModelElement) property} associated with this entry's {@link java.util.Map.Entry#getKey() key}.
   * This implementation always {@link #reportIgnoredEntry(EAnnotation, EModelElement, Map.Entry, DiagnosticChain, Map) reports an ignored entry}.
   * An annotation validator implementation may choose to support validation by specializing this method, rather than {@link #getPropertyClasses(EModelElement) providing a modeled representation},
   * but the {@link Assistant assistant} will not provide any useful support.
   * </p>
   * @param eAnnotation the annotation in question.
   * @param eModelElement the annotation's {@link EAnnotation#getEModelElement() containing} model element.
   * @param entry the annotation {@link EAnnotation#getDetails() detail} in question.
   * @param diagnostics a place to accumulate diagnostics; if it's <code>null</code>, no diagnostics should be produced.
   * @param context a place to cache information, if it's <code>null</code>, no cache is supported.
   * @return whether this detail entry is valid.
   */
  protected boolean validateDetail(EAnnotation eAnnotation, EModelElement eModelElement, Map.Entry<String, String> entry, DiagnosticChain diagnostics, Map<Object, Object> context)
  {
    if (diagnostics != null)
    {
      reportIgnoredEntry(eAnnotation, eModelElement, entry, diagnostics, context);
    }
    return false;
  }

  /**
   * Returns whether the value of this detail entry for the corresponding feature is valid.
   * <p>
   * This implementation delegates
   * to {@link #validateAttributeDetailLiteralValue(EAnnotation, EModelElement, java.util.Map.Entry, EAttribute, List, DiagnosticChain, Map)  validateAttributeDetail}
   * or to {@link #validateReferenceDetailLiteralValue(EAnnotation, EModelElement, Map.Entry, EReference, List, DiagnosticChain, Map)  validateReferenceDetail}
   * depending on whether the feature is an {@link EAttribute} or an {@link EReference}.
   * It {@link #createValueDiagnostic(EAnnotation, EModelElement, java.util.Map.Entry, EStructuralFeature) creates} a place holder diagnostic that it passed to those methods,
   * so all diagnostics gathered by those methods are grouped as {@link Diagnostic#getChildren() children} of the placeholder.
   * Both those methods build the corresponding modeled values as a side-effect.
   * If the detail entry is otherwise valid,
   * the modeled value {@link #validateFeatureDetailValue(EAnnotation, EModelElement, java.util.Map.Entry, EStructuralFeature, List, DiagnosticChain, Map) is validated}.
   * </p>
   * @param eAnnotation the annotation in question.
   * @param eModelElement the annotation's {@link EAnnotation#getEModelElement() containing} model element.
   * @param entry the annotation {@link EAnnotation#getDetails() detail} in question.
   * @param feature the {@link #getProperties(EModelElement) property} associated with entry.
   * @param diagnostics a place to accumulate diagnostics; if it's <code>null</code>, no diagnostics should be produced.
   * @param context a place to cache information, if it's <code>null</code>, no cache is supported.
   * @return whether the value of this detail entry for the corresponding feature is valid.
   */
  protected boolean validateFeatureDetail(
    EAnnotation eAnnotation,
    EModelElement eModelElement,
    Map.Entry<String, String> entry,
    EStructuralFeature feature,
    DiagnosticChain diagnostics,
    Map<Object, Object> context)
  {
    BasicDiagnostic badValueDiagnostic = diagnostics == null ? null : createValueDiagnostic(eAnnotation, eModelElement, entry, feature);
    List<Object> values = new ArrayList<Object>();
    boolean result = feature instanceof EAttribute
      ? validateAttributeDetailLiteralValue(eAnnotation, eModelElement, entry, (EAttribute)feature, values, badValueDiagnostic, context)
      : validateReferenceDetailLiteralValue(eAnnotation, eModelElement, entry, (EReference)feature, values, badValueDiagnostic, context);
    if (result)
    {
      result &= validateFeatureDetailValue(eAnnotation, eModelElement, entry, feature, values, badValueDiagnostic, context);
    }
    if (!result && diagnostics != null)
    {
      diagnostics.add(badValueDiagnostic);
    }
    return result;
  }

  /**
   * Returns whether the modeled values for this detail entry's corresponding feature are valid.
   * <p>
   * For a {@link EStructuralFeature#isMany() many-valued} feature, it validates that the {@link EStructuralFeature#getLowerBound() lower} and {@link EStructuralFeature#getUpperBound() upper} bounds are respected.
   * For a singled valued feature that is {@link EStructuralFeature#isRequired() required}, it validates that the value is present;
   * in the single-valued case, the values list should contain a single value.
   * </p>
   * @param eAnnotation the annotation in question.
   * @param eModelElement the annotation's {@link EAnnotation#getEModelElement() containing} model element.
   * @param entry the annotation {@link EAnnotation#getDetails() detail} in question.
   * @param feature the {@link #getProperties(EModelElement) property} associated with entry.
   * @param values the list of instance values for this entry.
   * @param diagnostics a place to accumulate diagnostics; if it's <code>null</code>, no diagnostics should be produced.
   * @param context a place to cache information, if it's <code>null</code>, no cache is supported.
   * @return whether the modeled values for this detail entry's corresponding feature are valid.
   */
  protected boolean validateFeatureDetailValue(
    EAnnotation eAnnotation,
    EModelElement eModelElement,
    Map.Entry<String, String> entry,
    EStructuralFeature feature,
    List<Object> values,
    DiagnosticChain diagnostics,
    Map<Object, Object> context)
  {
    boolean result = true;
    int size = values.size();
    if (feature.isMany())
    {
      int lowerBound = feature.getLowerBound();
      if (lowerBound > 0 && size < lowerBound)
      {
        if (diagnostics != null)
        {
          reportTooFewValues(eAnnotation, eModelElement, entry, feature, values, size, lowerBound, diagnostics, context);
        }
        result = false;
      }
      int upperBound = feature.getUpperBound();
      if (upperBound > 0 && size > upperBound)
      {
        if (diagnostics != null)
        {
          reportTooManyValues(eAnnotation, eModelElement, entry, feature, values, size, upperBound, diagnostics, context);
        }
        result = false;
      }
    }
    else
    {
      if (feature.isRequired())
      {
        if (size == 0 || values.get(0) == null)
        {
          result = false;
          if (diagnostics != null)
          {
            reportMissingRequiredEntryValue(eAnnotation, eModelElement, feature, values, diagnostics, context);
          }
        }
      }
    }
    return result;
  }

  /**
   * Returns whether the literal value of this detail entry for the corresponding attribute is valid.
   * <p>
   * This implementation,
   * for a {@link EStructuralFeature#isMany() many-valued} attribute,
   * {@link #split(EAnnotation, EModelElement, Map.Entry, String, EStructuralFeature, DiagnosticChain, Map) splits} the detail value, if present, into a list of literal values
   * and {@link #validateAttributeDetailValueLiteral(EAnnotation, EModelElement, Map.Entry, EAttribute, String, List, DiagnosticChain, Map) validates each literal value}.
   * For a single-valued attribute, it {@link #validateAttributeDetailValueLiteral(EAnnotation, EModelElement, Map.Entry, EAttribute, String, List, DiagnosticChain, Map) directly validates} the literal value.
   * As a side-effect, each literal value of a many-valued attribute, or the literal value of a single-valued attribute,
   * is converted to an instance of the attribute's {@link EAttribute#getEAttributeType() data type} and is added to the data values list.
   * </p>
   * @param eAnnotation the annotation in question.
   * @param eModelElement the annotation's {@link EAnnotation#getEModelElement() containing} model element.
   * @param entry the annotation {@link EAnnotation#getDetails() detail} in question.
   * @param attribute feature the {@link EAttribute attribute} {@link #getProperties(EModelElement) property} associated with entry.
   * @param dataValues the list in which to accumulate valid instance values.
   * @param diagnostics a place to accumulate diagnostics; if it's <code>null</code>, no diagnostics should be produced.
   * @param context a place to cache information, if it's <code>null</code>, no cache is supported.
   * @return whether the literal value of this detail entry for the corresponding attribute is valid.
   *
   * @see #validateAttributeDetailValueLiteral(EAnnotation, EModelElement, Map.Entry, EAttribute, String, List, DiagnosticChain, Map)
   */
  protected boolean validateAttributeDetailLiteralValue(
    EAnnotation eAnnotation,
    EModelElement eModelElement,
    Map.Entry<String, String> entry,
    EAttribute attribute,
    List<Object> dataValues,
    DiagnosticChain diagnostics,
    Map<Object, Object> context)
  {
    boolean result = true;
    String literalValue = entry.getValue();
    if (attribute.isMany())
    {
      List<String> literalValues = split(eAnnotation, eModelElement, entry, literalValue, attribute, diagnostics, context);
      if (literalValues != null)
      {
        for (String literalValueItem : literalValues)
        {
          result &= validateAttributeDetailValueLiteral(eAnnotation, eModelElement, entry, attribute, literalValueItem, dataValues, diagnostics, context);
          if (!result && diagnostics == null)
          {
            break;
          }
        }
      }
    }
    else
    {
      result = validateAttributeDetailValueLiteral(eAnnotation, eModelElement, entry, attribute, literalValue, dataValues, diagnostics, context);
    }
    return result;
  }

  /**
   * Returns whether the given literal value is valid with respect to this detail entry's corresponding attribute's {@link EAttribute#getEAttributeType() data type}.
   * As a side-effect, if the literal value can be converted to an instance value, the corresponding instance value is added to the list of data values.
   * <p>
   * This implementation first tries to {@link EcoreUtil#createFromString(EDataType, String) convert} the literal value to an instance value of the data type.
   * If that fails, it creates a diagnostic that includes the exception {@link Exception#getLocalizedMessage() message}.
   * Otherwise, it adds the instance value to the list of values
   * and {@link EValidator#validate(EDataType, Object, DiagnosticChain, Map) validates} the instance value.
   * </p>
   * @param eAnnotation the annotation in question.
   * @param eModelElement the annotation's {@link EAnnotation#getEModelElement() containing} model element.
   * @param entry the annotation {@link EAnnotation#getDetails() detail} in question.
   * @param attribute feature the {@link EAttribute attribute} {@link #getProperties(EModelElement) property} associated with entry.
   * @param literalValue the literal value of the data type.
   * @param dataValues the list in which to accumulate a valid instance value.
   * @param diagnostics a place to accumulate diagnostics; if it's <code>null</code>, no diagnostics should be produced.
   * @param context a place to cache information, if it's <code>null</code>, no cache is supported.
   * @return whether the given literal value is valid with respect to this detail entry's corresponding attribute's data type.
   */
  protected boolean validateAttributeDetailValueLiteral(
    EAnnotation eAnnotation,
    EModelElement eModelElement,
    Map.Entry<String, String> entry,
    EAttribute attribute,
    String literalValue,
    List<Object> dataValues,
    DiagnosticChain diagnostics,
    Map<Object, Object> context)
  {
    EDataType dataType = attribute.getEAttributeType();
    boolean result;
    try
    {
      Object value = EcoreUtil.createFromString(dataType, literalValue);
      dataValues.add(value);
      EValidator rootEValidator = getRootEValidator(context);
      ValidationContext validationContext = new ValidationContext(eAnnotation, eModelElement, entry, attribute);
      context.put(ValidationContext.CONTEXT_KEY, validationContext);
      try
      {
        result = rootEValidator == null || rootEValidator.validate(dataType, value, diagnostics, context);
      }
      finally
      {
        context.remove(ValidationContext.CONTEXT_KEY);
      }
    }
    catch (RuntimeException exception)
    {
      result = false;
      if (diagnostics != null)
      {
        reportInvalidValueLiteral(eAnnotation, eModelElement, entry, attribute, literalValue, dataType, diagnostics, exception, context);
      }
    }
    return result;
  }

  /**
   * Returns whether the literal value of this detail entry for the corresponding reference is valid.
   * <p>
   * This implementation,
   * for a {@link EStructuralFeature#isMany() many-valued} reference,
   * {@link #split(EAnnotation, EModelElement, Map.Entry, String, EStructuralFeature, DiagnosticChain, Map) splits} the detail value, if present, into a list of literal values
   * and {@link #validateReferenceDetailValueLiteral(EAnnotation, EModelElement, Map.Entry, EReference, String, List, DiagnosticChain, Map) validates each literal value}.
   * For a single-valued attribute, it {@link #validateReferenceDetailValueLiteral(EAnnotation, EModelElement, Map.Entry, EReference, String, List, DiagnosticChain, Map) directly validates} the literal value.
   * As a side-effect, each literal value of a many-valued reference, or the literal value of a single-valued reference,
   * is converted to an instance of the references's {@link EReference#getEReferenceType() class} and added to the reference values list.
   * </p>
   * @param eAnnotation the annotation in question.
   * @param eModelElement the annotation's {@link EAnnotation#getEModelElement() containing} model element.
   * @param entry the annotation {@link EAnnotation#getDetails() detail} in question.
   * @param reference the {@link EReference reference} {@link #getProperties(EModelElement) property} associated with entry.
   * @param referenceValues the list in which to accumulate valid instance values.
   * @param diagnostics a place to accumulate diagnostics; if it's <code>null</code>, no diagnostics should be produced.
   * @param context a place to cache information, if it's <code>null</code>, no cache is supported.
   * @return whether the literal value of this detail entry for the corresponding reference is valid.
   */
  protected boolean validateReferenceDetailLiteralValue(
    EAnnotation eAnnotation,
    EModelElement eModelElement,
    Map.Entry<String, String> entry,
    EReference reference,
    List<Object> referenceValues,
    DiagnosticChain diagnostics,
    Map<Object, Object> context)
  {
    boolean result = true;
    String literalValue = entry.getValue();
    if (reference.isMany())
    {
      List<String> literalValues = split(eAnnotation, eModelElement, entry, literalValue, reference, diagnostics, context);
      if (literalValues != null)
      {
        for (String literalValueItem : literalValues)
        {
          result &= validateReferenceDetailValueLiteral(eAnnotation, eModelElement, entry, reference, literalValueItem, referenceValues, diagnostics, context);
          if (!result && diagnostics == null)
          {
            break;
          }
        }
      }
    }
    else
    {
      result = validateReferenceDetailValueLiteral(eAnnotation, eModelElement, entry, reference, literalValue, referenceValues, diagnostics, context);
    }
    return result;
  }

  /**
   * Returns whether the given literal value is valid with respect to this detail entry's corresponding reference's {@link EReference#getEReferenceType() class}.
   * As a side-effect, if the literal value can be converted to an instance value, the corresponding instance value is added to the list of reference values.
   * <p>
   * This implementation always returns <code>false</code> and {@link #reportInvalidReferenceLiteral(EAnnotation, EModelElement, Map.Entry, EReference, String, DiagnosticChain, Map) reports an invalid reference literal}.
   * An annotation validator implementation that supports reference literals must specialize this method.
   * </p>
   * @param eAnnotation the annotation in question.
   * @param eModelElement the annotation's {@link EAnnotation#getEModelElement() containing} model element.
   * @param entry the annotation {@link EAnnotation#getDetails() detail} in question.
   * @param reference the {@link EReference reference} {@link #getProperties(EModelElement) property} associated with entry.
   * @param literalValue the literal value of the class.
   * @param referenceValues the list in which to accumulate valid instance values.
   * @param diagnostics a place to accumulate diagnostics; if it's <code>null</code>, no diagnostics should be produced.
   * @param context a place to cache information, if it's <code>null</code>, no cache is supported.
   * @return whether the given literal value is valid with respect to this detail entry's corresponding references's class.
   */
  protected boolean validateReferenceDetailValueLiteral(
    EAnnotation eAnnotation,
    EModelElement eModelElement,
    Map.Entry<String, String> entry,
    EReference reference,
    String literalValue,
    List<Object> referenceValues,
    DiagnosticChain diagnostics,
    Map<Object, Object> context)
  {
    if (diagnostics != null)
    {
      reportInvalidReferenceLiteral(eAnnotation, eModelElement, entry, reference, literalValue, diagnostics, context);
    }
    return false;
  }

  /**
   * Splits the literal value into a list of literal values as appropriate for this feature.
   * <p>
   * This implementation splits the values at whitespace boundaries for all features..
   * </p>
   * @param eAnnotation the annotation in question.
   * @param eModelElement the annotation's {@link EAnnotation#getEModelElement() containing} model element.
   * @param entry the annotation {@link EAnnotation#getDetails() detail} in question.
   * @param literalValue a literal value of this feature's {@link EStructuralFeature#getEType() type}.
   * @param feature the {@link #getProperties(EModelElement) property} associated with entry.
   * @param diagnostics a place to accumulate diagnostics; if it's <code>null</code>, no diagnostics should be produced.
   * @param context a place to cache information, if it's <code>null</code>, no cache is supported.
   * @return splits the literal value into a list of literal values as appropriate for this feature.
   *
   * @see #validateAttributeDetailValueLiteral(EAnnotation, EModelElement, Map.Entry, EAttribute, String, List, DiagnosticChain, Map)
   * @see #validateReferenceDetailValueLiteral(EAnnotation, EModelElement, Map.Entry, EReference, String, List, DiagnosticChain, Map)
   */
  protected List<String> split(
    EAnnotation eAnnotation,
    EModelElement eModelElement,
    Map.Entry<String, String> entry,
    String literalValue,
    EStructuralFeature feature,
    DiagnosticChain diagnostics,
    Map<Object, Object> context)
  {
    return XMLTypeFactory.eINSTANCE.createENTITIESBase(literalValue);
  }

  /**
   * @see #INVALID_REFERENCE_LITERAL
   */
  protected void reportInvalidReferenceLiteral(
    EAnnotation eAnnotation,
    EModelElement eModelElement,
    Map.Entry<String, String> entry,
    EReference reference,
    String literalValue,
    DiagnosticChain diagnostics,
    Map<Object, Object> context)
  {
    diagnostics.add(
      createDiagnostic(
        Diagnostic.WARNING,
        INVALID_REFERENCE_LITERAL,
        getString(
          getEcoreResourceLocator(),
          "_UI_InvalidValue_diagnostic",
          literalValue,
          getString(getEcoreResourceLocator(), "_UI_InvalidReferenceValue_substitution", reference.getEReferenceType().getName())),
        literalValue,
        reference.getEReferenceType()));
  }

  /**
   * @see #INVALID_VALUE_LITERAL
   */
  protected void reportInvalidValueLiteral(
    EAnnotation eAnnotation,
    EModelElement eModelElement,
    Map.Entry<String, String> entry,
    EAttribute attribute,
    String literalValue,
    EDataType dataType,
    DiagnosticChain diagnostics,
    RuntimeException exception,
    Map<Object, Object> context)
  {
    diagnostics.add(
      createDiagnostic(
        Diagnostic.WARNING,
        INVALID_VALUE_LITERAL,
        getString(getEcoreResourceLocator(), "_UI_InvalidValue_diagnostic", literalValue, exception.getLocalizedMessage()),
        literalValue,
        dataType));
  }

  /**
   * @see #MISSING_REQUIRED_ENTRY_VALUE
   */
  protected void reportMissingRequiredEntryValue(
    EAnnotation eAnnotation,
    EModelElement eModelElement,
    EStructuralFeature feature,
    List<Object> values,
    DiagnosticChain diagnostics,
    Map<Object, Object> context)
  {
    diagnostics.add(
      createDiagnostic(
        Diagnostic.WARNING,
        MISSING_REQUIRED_ENTRY_VALUE,
        getString(getEcoreResourceLocator(), "_UI_InvalidValueRequiredFeatureMustBeSet_diagnostic"),
        (Object)null));
  }

  /**
   * @see #TOO_FEW_VALUES
   */
  protected void reportTooFewValues(
    EAnnotation eAnnotation,
    EModelElement eModelElement,
    Map.Entry<String, String> entry,
    EStructuralFeature feature,
    List<Object> values,
    int size,
    int lowerBound,
    DiagnosticChain diagnostics,
    Map<Object, Object> context)
  {
    diagnostics.add(
      createDiagnostic(Diagnostic.WARNING, TOO_FEW_VALUES, getString(getEcoreResourceLocator(), "_UI_InvalidValueFeatureHasTooFewValues_diagnostic", size, lowerBound), values));
  }

  /**
   * @see #TOO_MANY_VALUES
   */
  protected void reportTooManyValues(
    EAnnotation eAnnotation,
    EModelElement eModelElement,
    Map.Entry<String, String> entry,
    EStructuralFeature feature,
    List<Object> values,
    int size,
    int upperBound,
    DiagnosticChain diagnostics,
    Map<Object, Object> context)
  {
    diagnostics.add(
      createDiagnostic(Diagnostic.WARNING, TOO_MANY_VALUES, getString(getEcoreResourceLocator(), "_UI_InvalidValueFeatureHasTooManyValues_diagnostic", size, upperBound), values));
  }

  /**
   * @see #INVALID_LOCATION
   */
  protected void reportInvalidLocation(EAnnotation eAnnotation, DiagnosticChain diagnostics, Map<Object, Object> context)
  {
    diagnostics.add(
      createDiagnostic(
        Diagnostic.WARNING,
        INVALID_LOCATION,
        getString(getEcoreResourceLocator(), "_UI_InvalidAnnotationLocation_diagnostic", annotationName, getValidLocationDescription()),
        eAnnotation,
        EcorePackage.Literals.EANNOTATION__SOURCE));
  }

  /**
   * @see #INVALID_DUPLICATE
   */
  protected void reportDuplicate(
    EAnnotation primaryEAnnotation,
    EAnnotation secondaryEAnnotation,
    EModelElement eModelElement,
    DiagnosticChain diagnostics,
    Map<Object, Object> context)
  {
    diagnostics.add(
      createDiagnostic(
        Diagnostic.WARNING,
        INVALID_DUPLICATE,
        getString(getEcoreResourceLocator(), "_UI_InvalidDuplicateAnnotation_diagnostic", annotationName, getValidLocationDescription()),
        secondaryEAnnotation,
        EcorePackage.Literals.EANNOTATION__SOURCE,
        primaryEAnnotation));
  }

  /**
   * @see #IGNORED_ENTRY
   */
  protected void reportIgnoredEntry(EAnnotation eAnnotation, EModelElement eModelElement, Map.Entry<String, String> entry, DiagnosticChain diagnostics, Map<Object, Object> context)
  {
    diagnostics.add(
      createDiagnostic(
        Diagnostic.WARNING,
        IGNORED_ENTRY,
        getString(getEcoreResourceLocator(), "_UI_InvalidAnnotationEntryKey_diagnostic", annotationName, entry.getKey()),
        entry,
        EcorePackage.Literals.ESTRING_TO_STRING_MAP_ENTRY__KEY));
  }

  /**
   * @see #MISSING_ENTRY
   */
  protected void reportMissingEntry(
    EAnnotation eAnnotation,
    EModelElement eModelElement,
    String key,
    EStructuralFeature property,
    DiagnosticChain diagnostics,
    Map<Object, Object> context)
  {
    diagnostics.add(
      createDiagnostic(
        Diagnostic.WARNING,
        MISSING_ENTRY,
        getString(getEcoreResourceLocator(), "_UI_MissingAnnotationEntryKey_diagnostic", key),
        eAnnotation,
        EcorePackage.Literals.EANNOTATION__DETAILS));
  }

  /**
   * @see #IGNORED_REFERENCES
   */
  protected void reportIgnoredReferences(
    EAnnotation eAnnotation,
    EModelElement eModelElement,
    Collection<? extends EObject> ignoredReferences,
    DiagnosticChain diagnostics,
    Map<Object, Object> context)
  {
    List<Object> data = new ArrayList<Object>();
    data.add(eAnnotation);
    data.add(EcorePackage.Literals.EANNOTATION__REFERENCES);
    data.addAll(ignoredReferences);
    diagnostics.add(
      createDiagnostic(Diagnostic.WARNING, IGNORED_REFERENCES, getString(getEcoreResourceLocator(), "_UI_IgnoredAnnotationReferences_diagnostic", annotationName), data.toArray()));
  }

  /**
   * @see #INVALID_REFERENCE
   */
  protected void reportInvalidReference(EAnnotation eAnnotation, EModelElement eModelElement, EObject reference, DiagnosticChain diagnostics, Map<Object, Object> context)
  {
    diagnostics.add(
      createDiagnostic(
        Diagnostic.WARNING,
        INVALID_REFERENCE,
        getString(getEcoreResourceLocator(), "_UI_InvalidAnnotationReference_diagnostic", annotationName, EObjectValidator.getObjectLabel(reference, context)),
        eAnnotation,
        EcorePackage.Literals.EANNOTATION__REFERENCES,
        reference));
  }

  /**
   * @see #INVALID_REFERENCE
   */
  protected void reportIgnoredContents(
    EAnnotation eAnnotation,
    EModelElement eModelElement,
    Collection<? extends EObject> ignoredContents,
    DiagnosticChain diagnostics,
    Map<Object, Object> context)
  {
    List<Object> data = new ArrayList<Object>();
    data.add(eAnnotation);
    data.add(EcorePackage.Literals.EANNOTATION__CONTENTS);
    data.addAll(ignoredContents);
    diagnostics.add(
      createDiagnostic(Diagnostic.WARNING, INVALID_REFERENCE, getString(getEcoreResourceLocator(), "_UI_IgnoredAnnotationContents_diagnostic", annotationName), data.toArray()));
  }

  /**
   * @see #INVALID_CONTENT
   */
  protected void reportInvalidContent(EAnnotation eAnnotation, EModelElement eModelElement, EObject content, DiagnosticChain diagnostics, Map<Object, Object> context)
  {
    diagnostics.add(
      createDiagnostic(
        Diagnostic.WARNING,
        INVALID_CONTENT,
        getString(getEcoreResourceLocator(), "_UI_InvalidAnnotationContent_diagnostic", annotationName, EObjectValidator.getObjectLabel(content, context)),
        eAnnotation,
        EcorePackage.Literals.EANNOTATION__CONTENTS,
        content));
  }

  /**
   * @see #IGNORED_ANNOTATIONS
   */
  protected void reportIgnoredAnnotations(
    EAnnotation eAnnotation,
    EModelElement eModelElement,
    Collection<? extends EAnnotation> ignoredAnnotations,
    DiagnosticChain diagnostics,
    Map<Object, Object> context)
  {
    List<Object> data = new ArrayList<Object>();
    data.add(eAnnotation);
    data.add(EcorePackage.Literals.EMODEL_ELEMENT__EANNOTATIONS);
    data.addAll(ignoredAnnotations);
    diagnostics.add(
      createDiagnostic(
        Diagnostic.WARNING,
        IGNORED_ANNOTATIONS,
        getString(getEcoreResourceLocator(), "_UI_IgnoredAnnotationAnnotations_diagnostic", annotationName),
        data.toArray()));
  }

  /**
   * @see #INVALID_ANNOTATION
   */
  protected void reportInvalidAnnotation(
    EAnnotation eAnnotation,
    EModelElement eModelElement,
    EAnnotation nestedEAnnotation,
    DiagnosticChain diagnostics,
    Map<Object, Object> context)
  {
    diagnostics.add(
      createDiagnostic(
        Diagnostic.WARNING,
        INVALID_ANNOTATION,
        getString(getEcoreResourceLocator(), "_UI_InvalidAnnotationAnnotation_diagnostic", annotationName, EObjectValidator.getObjectLabel(nestedEAnnotation, context)),
        eAnnotation,
        EcorePackage.Literals.EANNOTATION__CONTENTS,
        nestedEAnnotation));
  }

  /**
   * Returns a description of the valid locations supported for annotations of this annotation validator.
   * <p>
   * A annotation validator implementation must provide the key <code>"_UI_Valid" + {@link #annotationName annotationName} + "AnnotationLocation_substitution"</code> in its {@link #getResourceLocator() resource locator}
   * with a very short description of the valid locations of annotations.
   * </p>
   * @return a description of the valid locations supported for annotations of this annotation validator.
   * @see #reportInvalidLocation(EAnnotation, DiagnosticChain, Map)
   */
  protected String getValidLocationDescription()
  {
    String description;
    try
    {
      description = getString(getResourceLocator(), "_UI_Valid" + annotationName + "AnnotationLocation_substitution");
    }
    catch (MissingResourceException exception)
    {
      EcorePlugin.INSTANCE.log(exception);
      description = "unknown; Implementation error for " + getClass().getName() + ":" + exception.getLocalizedMessage();
    }

    return description;
  }

  /**
   * Creates the placeholder diagnostic used by {@link #validateFeatureDetail(EAnnotation, EModelElement, Map.Entry, EStructuralFeature, DiagnosticChain, Map) validateFeatureDetail}.
   * Diagnostics about problems with the value of a {@link EAnnotation#getDetails() detail entry} will be nested as {@link Diagnostic#getChildren() children} of this annotation.
   * @param eAnnotation the annotation.
   * @param eModelElement the model element of that annotation.
   * @param entry the entry.
   * @param feature the feature.
   * @return Creates a placeholder diagnostic.
   */
  protected BasicDiagnostic createValueDiagnostic(EAnnotation eAnnotation, EModelElement eModelElement, Map.Entry<String, String> entry, EStructuralFeature feature)
  {
    return createDiagnostic(
      Diagnostic.OK,
      INVALID_DETAIL_VALUE,
      getString(getEcoreResourceLocator(), "_UI_InvalidAnnotationEntryValue_diagnostic", annotationName, entry.getKey()),
      entry,
      EcorePackage.Literals.ESTRING_TO_STRING_MAP_ENTRY__VALUE,
      feature);
  }

  /**
   * Returns the resource locator for fetching messages supported directly by the base implementation.
   * @return the resource locator for fetching messages supported directly by the base implementation.
   */
  protected ResourceLocator getEcoreResourceLocator()
  {
    return EcorePlugin.INSTANCE;
  }

  /**
   * Creates a diagnostic using the given parameters and the {@link #annotationSource}.
   * @return a diagnostic.
   */
  protected BasicDiagnostic createDiagnostic(int severity, int code, String message, Object... data)
  {
    return new BasicDiagnostic(severity, diagnosticSource, code, message, data);
  }

  /**
   * Fetches a translated string from the resource locator using the message key and the give substitutions, if any.
   * @return the translated string for the message key.
   */
  protected String getString(ResourceLocator resourceLocator, String key, Object... substitutions)
  {
    return substitutions == null ? resourceLocator.getString(key) : resourceLocator.getString(key, substitutions);
  }

  /**
   * Returns the root validator of the context.
   * @param context the context.
   * @return the root validator
   */
  protected EValidator getRootEValidator(Map<Object, Object> context)
  {
    if (context != null)
    {
      EValidator result = (EValidator)context.get(EValidator.class);
      if (result != null)
      {
        return result;
      }
    }

    return Diagnostician.INSTANCE;
  }

  /**
   * Returns the package loaded from the location specified by the given URI.
   * If the resource loads successfully---the org.eclipse.ecore.xmi jar must be on the class---and it contains an {@link EPackage},
   * that package is registered in the {@link org.eclipse.emf.ecore.EPackage.Registry#INSTANCE}.
   * 
   * @param uri the location of a resource containing an EPackage.
   * @return the loaded package registered package.
   */
  protected static EPackage loadEPackage(String uri)
  {
    Resource resource = new EPackageImpl()
      {
        @Override
        public Resource createResource(String uri)
        {
          Resource resource = super.createResource(uri);
          resource.getContents().clear();
          resource.unload();
          return resource;
        }
      }.createResource(uri);

    try
    {
      resource.load(null);
      EPackage ePackage = (EPackage)EcoreUtil.getObjectByType(resource.getContents(), EcorePackage.Literals.EPACKAGE);
      if (ePackage != null)
      {
        String nsURI = ePackage.getNsURI();
        EPackage.Registry.INSTANCE.put(nsURI, ePackage);
        resource.setURI(URI.createURI(nsURI));
      }
      return ePackage;
    }
    catch (IOException e)
    {
      return null;
    }
  }

  /**
   * An assistant that is useful for inducing a user interface that represents the annotation information in a more structured way
   * using {@link #getPropertyClasses(EModelElement) modeled objects} that are created by {@link #createInstance(EClass, EAnnotation)}.
   * This implementation delegates to the {@link BasicEAnnotationValidator annotation validator} which in turn provides an {@link BasicEAnnotationValidator#getAssistant() accessor} for its to corresponding assistant.
   * This class generally does not need to be specialized nor instantiated because every annotation validator provides an assistant.
   * This class therefore is abstract though none of its methods are abstract.
   */
  public static abstract class Assistant
  {
    /**
     * The annotation validator to which this assistant delegates.
     */
    protected final BasicEAnnotationValidator eAnnotationValidator;

    /**
     * Creates an instance that delegates to the give annotation validator.
     */
    public Assistant(BasicEAnnotationValidator eAnnotationValidator)
    {
      this.eAnnotationValidator = eAnnotationValidator;
    }

    /**
     * Returns whether this annotation with this annotation validator's {@link EAnnotation#getSource() annotation source} is valid at its {@link EAnnotation#getEModelElement() current location}.
     * <p>
     * The implementation delegates to {@link BasicEAnnotationValidator#isValidLocation(EAnnotation)}.
     * An induced user interface can use this to determine if it can/should use this assistant's information for representing modeled annotation information.
     * </p>
     * @param eAnnotation the annotation in question.
     * @return whether this annotation with this annotation validator's annotation source is valid at its current location.
     */
    public boolean isValidLocation(EAnnotation eAnnotation)
    {
      return eAnnotationValidator.isValidLocation(eAnnotation);
    }

    /**
     * Returns a map from key to {@link EStructuralFeature feature}.
     * These represents the keys that are considered valid and can be processed by this annotation validator.
     * <p>
     * The implementation delegates to {@link BasicEAnnotationValidator#getProperties(EModelElement)}.
     * An induced user interface can use this method to determine which properties to display.
     * </p>
     * @param eModelElement the model element that is being annotated.
     * @return a map from key to feature.
     * @see #getApplicableProperties(EObject, EAnnotation)
     */
    public Map<String, EStructuralFeature> getProperties(EModelElement eModelElement)
    {
      return eAnnotationValidator.getProperties(eModelElement);
    }

    /**
     * Returns the model classes used to represent annotations for the given model element.
     * <p>
     * The implementation delegates to {@link BasicEAnnotationValidator#getPropertyClasses(EModelElement)}.
     * An induced user interface can use this method to determine which instances to create for display purposes.
     * </p>
     * @param eModelElement the model element in question.
     * @return the model classes used to represent annotations for the given model element.
     */
    public List<EClass> getPropertyClasses(EModelElement eModelElement)
    {
      return eAnnotationValidator.getPropertyClasses(eModelElement);
    }

    /**
     * Creates an initialized instance of the modeled representation for the given annotation.
     * <p>
     * The implementation delegates to {@link BasicEAnnotationValidator#createInstance(EClass, EAnnotation)}.
     * An induced user interface can use this method to create instances for display purposes.
     * </p>
     * @param eClass the class to be instantiated.
     * @param eAnnotation the annotation with the information that needs to be represented.
     * @return creates an initialized instance of the modeled representation for the given annotation.
     */
    public EObject createInstance(EClass eClass, EAnnotation eAnnotation)
    {
      return eAnnotationValidator.createInstance(eClass, eAnnotation);
    }

    public String convertPropertyValueToLiteral(EObject eObject, EStructuralFeature eStructuralFeature, Object value)
    {
      return eAnnotationValidator.convertPropertyValueToLiteral(eObject, eStructuralFeature, value);
    }

    /**
     * Returns the {@link #getProperties(EModelElement) subset of properties} that are applicable for the current state of the modeled annotation instance.
     * <p>
     * This subset includes only those properties of the annotation's {@link EAnnotation#getEModelElement() containing} model element
     * for which {@link BasicEAnnotationValidator#isApplicable(EObject, EStructuralFeature)} returns <code>true</code>.
     * An induced user interface should avoid displaying properties that are not applicable for the current state of the modeled annotation instance.
     * </p>
     * @param eObject the modeled instance in question.
     * @param eAnnotation the corresponding annotation of that modeled instance.
     * @return the subset of properties that are applicable for the current state of the modeled annotation instance.
     */
    public Map<String, EStructuralFeature> getApplicableProperties(EObject eObject, EAnnotation eAnnotation)
    {
      EModelElement eModelElement = eAnnotation.getEModelElement();
      Map<String, EStructuralFeature> properties = getProperties(eModelElement);
      Map<String, EStructuralFeature> result = new LinkedHashMap<String, EStructuralFeature>();
      for (Map.Entry<String, EStructuralFeature> entry : properties.entrySet())
      {
        EStructuralFeature eStructuralFeature = entry.getValue();
        if (eAnnotationValidator.isApplicable(eObject, eStructuralFeature))
        {
          result.put(entry.getKey(), eStructuralFeature);
        }
      }
      return Collections.unmodifiableMap(result);
    }

    /**
     * Returns whether {@link EAnnotation#getReferences() references} are meaningful for this annotation.
     * <p>
     * The implementation delegates to {@link BasicEAnnotationValidator#isReferencesSupported(EAnnotation, EModelElement)},
     * passing in the {@link EAnnotation#getEModelElement() containing} model element.
     * An induced user interface should avoid providing the ability to specify references when this returns <code>false</code>.
     * </p>
     * @param eAnnotation the annotation in question.
     * @return whether references are meaningful for this annotation.
     */
    public boolean isReferencesSupported(EAnnotation eAnnotation)
    {
      return eAnnotationValidator.isReferencesSupported(eAnnotation, eAnnotation.getEModelElement());
    }

    /**
     * Returns the filtered collection of references that are valid for this annotation.
     * <p>
     * The implementation delegates to {@link BasicEAnnotationValidator#getValidReferences(EAnnotation, EModelElement, Collection)}, passing in the {@link EAnnotation#getEModelElement() containing model element}.
     * An induced user interface should provide the ability to specify only the references returned by this method.
     * The references argument may contain all reachable objects, some subset there of, or none at all;
     * an implementation may choose to filter from this collection or to provide its own result, including objects not in this collection.
     * </p>
     * @param eAnnotation the annotation in question.
     * @param references all reachable objects, some subset there of, or none at all.
     * @return the objects that are valid as references for this annotation.
     */
    public Collection<?> getValidReferences(EAnnotation eAnnotation, Collection<?> references)
    {
      return eAnnotationValidator.getValidReferences(eAnnotation, eAnnotation.getEModelElement(), references);
    }

    /**
     * Returns whether {@link EAnnotation#getContents() contents} are meaningful for this annotation.
     * <p>
     * The implementation delegates to {@link BasicEAnnotationValidator#isContentsSupported(EAnnotation, EModelElement)},
     * passing in the {@link EAnnotation#getEModelElement() containing} model element and an empty list.
     * An induced user interface should avoid providing the ability to specify contents when this returns <code>false</code>.
     * </p>
     * @param eAnnotation the annotation in question.
     * @return whether contents are meaningful for this annotation.
     */
    public boolean isContentsSupported(EAnnotation eAnnotation)
    {
      return eAnnotationValidator.isContentsSupported(eAnnotation, eAnnotation.getEModelElement());
    }

    /**
     * Returns the filtered collection of contents that are valid for this annotation.
     * <p>
     * The implementation delegates to {@link BasicEAnnotationValidator#getValidContents(EAnnotation, EModelElement, Collection)}
     * passing in the {@link EAnnotation#getEModelElement() containing} model element.
     * An induced user interface should provide the ability to specify only the contents returned by this method.
     * </p>
     * @param eAnnotation the annotation in question.
     * @param contents nothing at all, or the {@link EAnnotation#getContents() potential contents} of the annotation.
     * @return the objects that are valid as contents for this annotation.
     */
    public Collection<? extends EObject> getValidContents(EAnnotation eAnnotation, Collection<? extends EObject> contents)
    {
      return eAnnotationValidator.getValidContents(eAnnotation, eAnnotation.getEModelElement(), contents);
    }

    /**
     * Returns whether {@link EAnnotation#getEAnnotations() nested annotations} are meaningful for this annotation.
     * <p>
     * The implementation delegates to {@link BasicEAnnotationValidator#isAnnotationsSupported(EAnnotation, EModelElement)}, passing in the {@link EAnnotation#getEModelElement() containing model element}.
     * An induced user interface should avoid providing the ability to specify nested annotations when this returns <code>false</code>.
     * </p>
     * @param eAnnotation the annotation in question.
     * @return whether annotations are meaningful for this annotation.
     */
    public boolean isAnnotationsSupported(EAnnotation eAnnotation)
    {
      return eAnnotationValidator.isAnnotationsSupported(eAnnotation, eAnnotation.getEModelElement());
    }

    /**
     * Returns the filtered collection of nested annotations that are valid for this annotation.
     * <p>
     * The implementation delegates to {@link BasicEAnnotationValidator#getAllValidAnnotations(EAnnotation, EModelElement, Collection)}
     * passing in the {@link EAnnotation#getEModelElement() containing} model element.
     * An induced user interface should provide the ability to specify only the nested annotations returned by this method.
     * </p>
     * @param eAnnotation the annotation in question.
     * @param annotations nothing at all, or the {@link EModelElement#getEAnnotations() current or potential nested annotations} of the annotation.
     * @return the annotations that are valid as nested annotations for this annotation.
     */
    public Collection<? extends EAnnotation> getValidAnnotations(EAnnotation eAnnotation, Collection<? extends EAnnotation> annotations)
    {
      return eAnnotationValidator.getAllValidAnnotations(eAnnotation, eAnnotation.getEModelElement(), annotations);
    }
  }

  /**
   * Context data used by {@link BasicEAnnotationValidator#validateAttributeDetailValueLiteral(EAnnotation, EModelElement, Entry, EAttribute, String, List, DiagnosticChain, Map) validateAttributeDetailValueLiteral} 
   * to pass contextual information that can be used when a data type's value is {@link EValidator#validate(EDataType, Object, DiagnosticChain, Map) validated}.
   */
  public static class ValidationContext
  {
    /**
     * The key used in the context map.
     */
    public static final String CONTEXT_KEY = "EANNOTATION_VALIDATION_CONTEXT";

    /**
     * The annotation being validated.
     */
    private final EAnnotation eAnnotation;

    /**
     * The model element containing that annotation.
     */
    private final EModelElement eModelElement;

    /**
     * The detail entry being validated.
     */
    private final Entry<String, String> entry;

    /**
     * The attribute of the data type.
     */
    private final EAttribute eAttribute;

    /**
     * Creates an instance.
     * @param eAnnotation the annotation being validated.
     * @param eModelElement the model element containing that annotation.
     * @param entry the detail entry being validated.
     * @param eAttribute the structural feature of the data type.
     */
    public ValidationContext(EAnnotation eAnnotation, EModelElement eModelElement, Entry<String, String> entry, EAttribute eAttribute)
    {
      this.eAnnotation = eAnnotation;
      this.eModelElement = eModelElement;
      this.entry = entry;
      this.eAttribute = eAttribute;
    }

    /**
     * Returns the annotation being validated.
     * @return the annotation being validated.
     */
    public EAnnotation getEAnnotation()
    {
      return eAnnotation;
    }

    /**
     * The containing model elements of the annotation being validated.
     * @return the containing model elements of the annotation being validated.
     */
    public EModelElement getEModelElement()
    {
      return eModelElement;
    }

    /**
     * The {@link EAnnotation#getDetails() detail entry} being validated.
     * @return the detail entry being validated.
     */
    public Entry<String, String> getEntry()
    {
      return entry;
    }

    /**
     * The attribute of the data type being validated.
     * @return the attribute of the data type being validated.
     */
    public EAttribute getEAttribute()
    {
      return eAttribute;
    }
  }
}
