/**
 * Copyright (c) 2018 Eclipse contributors and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 */
package org.eclipse.emf.edit.provider;


import java.util.Map;
import java.util.Set;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.edit.EMFEditPlugin;


/**
 * A factory for creating a property editor for an object's property descriptor in a specific context.
 * A factory optionally supports {@link #createLabelProvider(Object, IItemPropertyDescriptor) creating} a specialized label provider for the value of the property.
 * <p>
 * Factories are typically {@link Registry registered} for a specific URI of the form {@code editor://<authority>/},
 * i.e., a {@link URI#isHierarchical() hierarchical} URI with an {@link URI#authority() authority} and a {@link URI#isPrefix() trailing} '/'.
 * </p>
 * <p>
 * {@link IPropertyEditorFactory.Provider Providers} may use a URI's 
 * {@link URI#segments() segments}, {@link URI#query() query}, and {@link URI#fragment() fragment} 
 * to act as a way of passing parameters to the factory when creating an editor.
 * </p> 
 * 
 * @noimplement This interface is not intended to be directly implemented by clients. Instead extend the abstract {@link PropertyEditorFactory} class.
 * @since 2.14
 */
public interface IPropertyEditorFactory
{
  /**
   * Creates a new property editor in the given context for the given object's property descriptor.
   * The return type and the context type are platform-neutral.
   * The implementation may return {@code null} if it is unable to create a property editor.
   * If the property descriptor is a {@link Provider} that {@link Provider#getEditorFactory(Object) yields} a URI, 
   * that URI may be used to more fully specify the details of the editor to be created,
   * i.e., may be used to pass additional parameters for property editor creation.
   * 
   * @param object the object being edited.
   * @param propertyDescriptor its {@link IItemPropertySource#getPropertyDescriptors(Object) property descriptor}.
   * @param context the context in which to create the editor.
   * @return a new property editor or {@code null}.
   */
  public Object createEditor(Object object, IItemPropertyDescriptor propertyDescriptor, Object context);

  /**
   * Creates a label provider for the given object's property descriptor.
   * This will be used to produce the label for this property descriptor's value.
   * @param object the object being edited.
   * @param propertyDescriptor its {@link IItemPropertySource#getPropertyDescriptors(Object) property descriptor}.
   * @return a label provider or {@code null}.
   */
  public IItemLabelProvider createLabelProvider(Object object, IItemPropertyDescriptor propertyDescriptor);

  /**
   * A provider of an property editor factory.
   * This is typically implemented by classes that also implement {@link IItemPropertyDescriptor}.
   * The two primary implementation classes {@link ItemPropertyDescriptor} and {@link ItemPropertyDescriptorDecorator} both implement this interface.
   */
  public interface Provider
  {
    /**
     * Returns the editor factory for the object being adapted.
     * The result can be mapped to a {@link IPropertyEditorFactory} instance using {@code IPropertyEditorFactory.Registry.INSTANCE.}{@link Registry#getPropertyEditorFactory(Object)}.
     * @param object the adapted object.
     * @return a {@link URI} that specifies the property editor factory, a {@link IPropertyEditorFactory} implementation, or {@code null}.
     */
    public Object getEditorFactory(Object object);
  }

  /**
   * A descriptor used to {@link Registry register} property editor factories lazily.
   */
  public interface Descriptor
  {
    /**
     * Returns the property editor factory specified by the descriptor.
     * @return the property editor factory specified by the descriptor.
     */
    public IPropertyEditorFactory getPropertyEditorFactory();
  }

  /**
   * A registry for mapping a property editor specification URI to a {@link IPropertyEditorFactory property editor factory} instance.
   * It is a global singleton generally referenced as {@code IPropertyEditorFactory.Registry.INSTANCE}.
   * @noimplement Do not implement this interface directly; instead extend {@link org.eclipse.emf.edit.EMFEditPlugin.PropertyEditorFactoryRegistryImpl}.
   */
  public interface Registry extends Map<URI, Object>
  {
    /**
     * The global singleton instance of the registry.
     */
    public Registry INSTANCE = EMFEditPlugin.getPropertyEditorFactoryRegistry();

    /**
     * Returns the property editor factory associated with the given property editor specification URI.
     * The general lookup strategy is to trim the fragment and the query, and then successively trim segments until an exact match is found in the registry.
     * As such, each property editor factory should generally be registered with a URI of the form {@code editor://<authority>/},
     * i.e., a hierarchical URI with an authority and a trailing '/'.
     * The segments, query, and fragment of the URI act as a way of passing parameters to the factory when creating an editor.
     * 
     * @param propertyEditorSpecification the URI specifying the details of the property editor to be created.
     * @return the associated property editor factory, or {@code null}.
     */
    public IPropertyEditorFactory getPropertyEditorFactory(URI propertyEditorSpecification);

    /**
     * A convenience method for mapping a {@link URI}, or an {@link IPropertyEditorFactory} instance, to the associated instance or to the instance itself. 
     * In all other cases, it returns {@code null}.
     * @param propertyEditorSpecification a URI or an IPropertyEditorFactory instance.
     * @return the associated property editor factory.
     */
    public IPropertyEditorFactory getPropertyEditorFactory(Object propertyEditorSpecification);

    /**
     * Returns the factories registered in the target platform when running with the Eclipse Plug-in Development environment,
     * a copy of the {@link #keySet()} otherwise.
     * @since 2.14
     */
    public Set<URI> getTargetPlatformFactories();
  }
}
