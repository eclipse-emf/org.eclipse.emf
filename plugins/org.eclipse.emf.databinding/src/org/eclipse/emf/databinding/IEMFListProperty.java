/**
 * Copyright (c) 2008 Matthew Hall and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: 
 *   Matthew Hall - initial API and implementation (bug 195222)
 *   Tom Schindl <tom.schindl@bestsolution.at> - port to EMF in 262160
 */
package org.eclipse.emf.databinding;

import java.util.List;

import org.eclipse.core.databinding.property.list.IListProperty;
import org.eclipse.emf.ecore.EStructuralFeature;


/**
 * <p><b>PROVISIONAL:</b> This API is subject to arbitrary change, including renaming or removal.</p>
 * 
 * An {@link IListProperty} extension interface with convenience methods for
 * creating nested {@link EStructuralFeature}s
 * 
 * @since 2.5
 * @noextend This interface is not intended to be extended by clients.
 * @noimplement This interface is not intended to be implemented by clients.
 */
public interface IEMFListProperty extends IEMFProperty, IListProperty
{

  /**
   * <p><b>PROVISIONAL:</b> This API is subject to arbitrary change, including renaming or removal.</p>
   * <p><b>This API is highly experimental and if possible will get moved upwards to Core-Databinding</b></p>
   * 
   * Delegate used to read/write an element of an observed list
   * @param <O> 
   * 
   * @since 2.7
   */
  public abstract class ListElementAccess<O>
  {
    /**
     * <p><b>PROVISIONAL:</b> This API is subject to arbitrary change, including renaming or removal.</p>
     * <p><b>This API is highly experimental and if possible will get moved upwards to Core-Databinding</b></p>
     * 
     * Struct to hold write information
     * 
     * @since 2.7
     */
    public static class WriteData
    {
      /**
       * If the value should be appended
       */
      public static final int NO_INDEX = -1;

      /**
       * The index in the list the value is written to
       */
      public final int index;

      /**
       * <code>true</code> if the given value should be inserted at the given position
       */
      public final boolean insert;

      /**
       * Create new instance
       * @param index The index in the list the value is written to
       * @param insert <code>true</code> if the given value should be inserted at the given position
       */
      public WriteData(int index, boolean insert)
      {
        this.index = index;
        this.insert = insert;
      }
    }

    /**
     * The index in the list the value is read from or {@link WriteData#NO_INDEX} if not available
     * @param list the list to search in
     * @return the index or <code>{@link WriteData#NO_INDEX}</code> if not found
     */
    public abstract int getReadValueIndex(List< O > list);

    /**
     * The index in the list the value is written to or {@link WriteData#NO_INDEX} if appended
     * @param list the list the value will is written in
     * @return The index in the list the value is written to or {@link WriteData#NO_INDEX} if appended
     */
    public abstract int getWriteValueIndex(List< O > list);

    /**
     * The data used to add/replace when writing the value back to the list
     * @param list the list the value will is written in
     * @return the data
     */
    public WriteData getWriteValueData(List< O > list)
    {
      int idx = getWriteValueIndex(list);
      return new WriteData(idx, idx == WriteData.NO_INDEX);
    }
  }

  /**
   * Returns a master-detail combination of this property and the specified
   * value nested feature.
   * 
   * @param featurePath
   *            the nested feature to observe
   * @return a nested combination of this property and the specified nested
   *         feature.
   * @see #values(IEMFValueProperty)
   */
  public IEMFListProperty values(FeaturePath featurePath);

  /**
   * Returns a master-detail combination of this property and the specified
   * value property.
   * 
   * @param feature
   *            the feature
   * @return a nested combination of this property and the specified nested
   *         feature.
   */
  public IEMFListProperty values(EStructuralFeature feature);

  /**
   * Returns a master-detail combination of this property and the specified
   * value property. The returned property will observe the specified value
   * property for all elements observed by this list property.
   * <p>
   * Example:
   * 
   * <pre>
   * // Observes the list-typed &quot;children&quot; property of a Person object,
   * // where the elements are Person objects
   * IEMFListProperty children = EMFProperties
   * 		.list(MyPackage.Literals.PERSON_CHILDREN);
   * // Observes the string-typed &quot;name&quot; property of a Person object
   * IEMFValueProperty name = EMFProperties.value(MyPackage.Literals.PERSON_NAME);
   * // Observes the names of children of a Person object.
   * IEMFListProperty childrenNames = children.values(name);
   * </pre>
   * 
   * @param property
   *            the detail property to observe
   * @return a master-detail combination of this property and the specified
   *         value property.
   */
  public IEMFListProperty values(IEMFValueProperty property);

  /**
   * <p><b>This API is highly experimental and if possible will get moved upwards to Core-Databinding</b></p>
   * Observe one of the elements in the list
   * @param elementAccess the element access to use
   * @return a master-detail property
   * @since 2.7
   */
  public IEMFValueProperty value(ListElementAccess<?> elementAccess);
}
