/**
 * Copyright (c) 2018 Eclipse contributorsCorporation and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 */
package org.eclipse.emf.edit.ui.provider;


import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.edit.provider.IItemPropertyDescriptor;
import org.eclipse.emf.edit.provider.IItemPropertySource;
import org.eclipse.emf.edit.provider.PropertyEditorFactory;
import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.swt.widgets.Composite;


/**
 * A property editor factory that specialized to create {@link CellEditor cell editors}.
 */
public abstract class EMFEditUIPropertyEditorFactory extends PropertyEditorFactory
{
  /**
   * Create an instance with the given property editor factory URI.
   * 
   * @param propertyEditorFactoryURI the base URI of this property editor factory.
   */
  public EMFEditUIPropertyEditorFactory(URI propertyEditorFactoryURI)
  {
    super(propertyEditorFactoryURI);
  }

  /**
   * {@inheritDoc}
   * <p>
   * This implementation returns a {@link CellEditor} and delegates to {@link #createDefaultEditor(Object, IItemPropertyDescriptor, Composite)} if the context is a composite.
   * You probably don't need to override this method.
   * </p>
   */
  public CellEditor createEditor(Object object, IItemPropertyDescriptor propertyDescriptor, Object context)
  {
    if (context instanceof Composite)
    {
      Composite composite = (Composite)context;
      return createEditor(object, propertyDescriptor, composite);
    }
    else
    {
      return null;
    }
  }

  /**
   * Creates a new cell editor editor on the given composite for the given the object's property descriptor.
   * The implementation may return {@code null} if it is unable to create a cell editor.
   * If the property descriptor is a {@link org.eclipse.emf.edit.provider.IPropertyEditorFactory.Provider} 
   * that {@link org.eclipse.emf.edit.provider.IPropertyEditorFactory.Provider#getEditorFactory(Object) yields} a URI, 
   * that URI may be used to more fully specify the details of the editor to be created,
   * i.e., may be used to pass additional parameters for property editor creation.
   * 
   * @param object the object being edited.
   * @param propertyDescriptor its {@link IItemPropertySource#getPropertyDescriptors(Object) property descriptor}.
   * @param composite the parent of the cell editor.
   * @return a new cell editor or {@code null}.
   */
  public abstract CellEditor createEditor(Object object, IItemPropertyDescriptor propertyDescriptor, Composite composite);

  /**
   * Creates the default cell editor editor on the given composite for the given the object's property descriptor,
   * i.e., the cell editor that would be created if there were to specialized property editor factory.
   * 
   * @param object the object being edited.
   * @param propertyDescriptor its {@link IItemPropertySource#getPropertyDescriptors(Object) property descriptor}.
   * @param composite the parent of the cell editor.
   * @return a new property editor or {@code null}.
   */
  protected CellEditor createDefaultEditor(Object object, IItemPropertyDescriptor propertyDescriptor, Composite composite)
  {
    return new PropertyDescriptor(object, propertyDescriptor)
      {
        @Override
        public CellEditor createPropertyEditorFromFactory(Composite composite)
        {
          return null;
        }
      }.createPropertyEditor(composite);
  }
}
