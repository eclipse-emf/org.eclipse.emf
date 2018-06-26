/**
 * Copyright (c) 2018 Eclipse contributors and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 */
package org.eclipse.emf.edit.provider;


import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.eclipse.emf.common.util.BasicDiagnostic;
import org.eclipse.emf.common.util.DiagnosticChain;
import org.eclipse.emf.common.util.ResourceLocator;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EModelElement;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.edit.EMFEditPlugin;


/**
 * The primary implementation of  property editor factory.
 * All implementations of {@link IPropertyEditorFactory} must extend this abstract class.
 * This class provides utility methods that are generally useful in all implementations,
 * as well as default implementations of all interface methods
 * except {@link #createEditor(Object, IItemPropertyDescriptor, Object)}.
 * For development-time purposes, a factory may support {@link #validate(EModelElement, URI, DiagnosticChain, Map)} validation.
 */
public abstract class PropertyEditorFactory implements IPropertyEditorFactory
{
  /**
   * This property editor factory's base URI.
   */
  protected final URI propertyEditorFactoryURI;

  /**
   * Create an instance with the given property editor factory URI.
   * 
   * @param propertyEditorFactoryURI the base URI of this property editor factory.
   */
  public PropertyEditorFactory(URI propertyEditorFactoryURI)
  {
    this.propertyEditorFactoryURI = propertyEditorFactoryURI;
  }

  /**
   * Returns the URI of this property editor factory.
   * @return the URI of this property editor factory.
   */
  public URI getPropertyEditorFactoryURI()
  {
    return propertyEditorFactoryURI;
  }

  /**
   * {@inheritDoc}
   */
  public IItemLabelProvider createLabelProvider(Object object, IItemPropertyDescriptor propertyDescriptor)
  {
    return null;
  }

  /**
   * Validates whether this factory, in the context of the given model element, supports the given URI for specifying the particular details of the editor to be created.
   * @param eModelElement the model element for which the given property editor specification is to be applied.
   * @param propertyEditorSpecification the URI specifying details about exactly which editor is to be created.
   * @param diagnostics a place to accumulate diagnostics; if it's {@code null}, no diagnostics should be produced.
   * @param context a place to cache information.
   * @return whether the property editor specification is valid for the given model element.
   */
  public boolean validate(EModelElement eModelElement, URI propertyEditorSpecification, DiagnosticChain diagnostics, Map<Object, Object> context)
  {
    return true;
  }

  /**
   * Returns the structural feature of the property descriptor, if it has one.
   * @param object the object being adapted.
   * @param propertyDescriptor that object's property descriptor.
   * @return the structural feature of the property descriptor or {@code null}.
   */
  protected EStructuralFeature getEStructuralFeature(Object object, IItemPropertyDescriptor propertyDescriptor)
  {
    Object feature = propertyDescriptor.getFeature(object);
    if (feature instanceof EStructuralFeature)
    {
      return (EStructuralFeature)feature;
    }
    else
    {
      return null;
    }
  }

  /**
   * Returns the property editor URI of the property descriptor 
   * if it is a {@link IPropertyEditorFactory.Provider provider} 
   * that {@link IPropertyEditorFactory.Provider#getEditorFactory(Object) yields} a URI.
   * @param object the object being adapted.
   * @param propertyDescriptor its property descriptor.
   * @return the property editor URI of the property descriptor .
   */
  protected URI getPropertyEditorURI(Object object, IItemPropertyDescriptor propertyDescriptor)
  {
    if (propertyDescriptor instanceof IPropertyEditorFactory.Provider)
    {
      Object editorFactory = ((IPropertyEditorFactory.Provider)propertyDescriptor).getEditorFactory(object);
      if (editorFactory instanceof URI)
      {
        return (URI)editorFactory;
      }
    }
    return null;
  }

  /**
   * Returns the bit-wise | of the {@link #getStyleElements(String) style elements}.
   * @param style a |-separated sequence of style elements.
   * @param styles the style bit value of the known styles.
   * @return the bit-wise | of the style elements.
   */
  protected int getStyle(String style, Map<String, Integer> styles)
  {
    int result = 0;
    for (String styleElement : getStyleElements(style))
    {
      Integer value = styles.get(styleElement);
      if (value != null)
      {
        result |= value;
      }
    }
    return result;
  }

  /**
   * Splits a style sequence of |-separated elements into a list of style elements.
   * @param style a |-separated sequence of style elements.
   * @return the list of style elements.
   */
  protected List<String> getStyleElements(String style)
  {
    String[] styleElements = style.split("\\|");
    return Arrays.asList(styleElements);
  }

  /**
   * Returns the URI suffixes that will be appended to the property editor factory's registered URI as suggestions (choices} for values.
   * If this returns an empty set, the property editor factory's URI will not be suggested at all.
   * Specialize this to offer useful examples for how to configure the property editor factory's behavior.
   * @param eModelElement the context where the URIs will be used.
   * @return a set of URI suffixes.
   */
  public Set<String> getChoices(EModelElement eModelElement)
  {
    Set<String> result = new LinkedHashSet<String>();
    result.add("");
    return result;
  }

  /**
   * Creates a diagnostic using the given parameters.
   * @param severity the severity.
   * @param code the diagnostic code.
   * @param message the message.
   * @param data the data associated with the diagnostic.
   * @return a diagnostic.
   */
  protected BasicDiagnostic createDiagnostic(int severity, int code, String message, Object... data)
  {
    return new BasicDiagnostic(severity, getPropertyEditorFactoryURI().authority(), code, message, data);
  }

  /**
   * Fetches a translated string from the resource locator using the message key and the give substitutions, if any.
   * @param key the key to look up.
   * @param substitutions the substitutions for the message.
   * @return the translated string for the message key.
   */
  protected String getString(String key, Object... substitutions)
  {
    return getString(getResourceLocator(), key, substitutions);
  }
  
  /**
   * Fetches a translated string from the resource locator using the message key and the give substitutions, if any.
   * @param resourceLocator the resource locator to use for lookup.
   * @param key the key to look up.
   * @param substitutions the substitutions for the message.
   * @return the translated string for the message key.
   */
  protected String getString(ResourceLocator resourceLocator, String key, Object... substitutions)
  {
    return substitutions == null ? resourceLocator.getString(key) : resourceLocator.getString(key, substitutions);
  }

  /**
   * Returns the resource locator for fetching implementation-specific validation messages.
   * @return the resource locator for fetching model-specific validation messages.
   */
  protected ResourceLocator getResourceLocator()
  {
    return getEMFEditResourceLocator();
  }

  /**
   * Returns the resource locator for fetching messages supported directly by the base implementation.
   * @return the resource locator for fetching messages supported directly by the base implementation.
   */
  protected ResourceLocator getEMFEditResourceLocator()
  {
    return EMFEditPlugin.INSTANCE;
  }
}
