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
package org.eclipse.emf.edit.provider;


import java.util.Collection;


/**
 * This interface extends IPropertyDescriptor so that the methods of {@link IItemPropertySource} can be delegated to the descriptor.
 * This allows the implementing class to completely encapsulate the work associated with supporting a particular property sheet property.
 */
public interface IItemPropertyDescriptor
{
  /**
   * An interface that may be implemented by property descriptor to specialize the handling of entered literals and values.
   * 
   * @since 2.14
   */
  public interface ValueHandlerProvider
  {
    /**
     * Returns the value handler.
     * This must not be <code>null</code> if {@link #isChoiceArbitrary(Object)} return <code>true</code>.
     */
    public ValueHandler getValueHandler(Object object);

    /**
     * Returns whether this property descriptor allows arbitrary values
     * in addition to the explicit {@link IItemPropertyDescriptor#getChoiceOfValues(Object) choices} provided.
     */
    public boolean isChoiceArbitrary(Object object);

    /**
     * Returns whether this property descriptor supports the concept of an <em>unset</em> state,
     * i.e., whether the value state of the property includes one additional state, the unset state, that is distinct from the set of values the property can have.
     */
    public boolean isPropertyUnsettable(Object object);
  }

  /**
   * An interface used by by property descriptors to specialize the validation and handling of entered literals and values.
   * 
   * @since 2.14
   */
  public interface ValueHandler
  {
    /**
     * Converts a literal value to an instance value.
     * @param literal the literal textual value.
     * @return the instance value.
     * @see #toString(Object)
     */
    public Object toValue(String literal);

    /**
     * Converts an instance value to literal value.
     * @param instance the instance value.
     * @return the literal value.
     * @see #toString(Object)
     */
    public String toString(Object instance);

    /**
     * Validates the literal value, returning <code>null</code> if the literal is valid and a description of why it's invalid otherwise.
     * @param literal the literal value.
     * @return returns <code>null</code> if the literal is valid and a description of why it's invalid otherwise.
     */
    public String isValid(String literal);
  }

  /**
   * This fetches this descriptor's property from the object.
   */
  public Object getPropertyValue(Object object);

  /**
   * This determines whether this descriptor's property for the object is set.
   */
  public boolean isPropertySet(Object object);

  /**
   * This determines whether this descriptor's property for the object supports set (and reset).
   */
  public boolean canSetProperty(Object object);

  /**
   * This resets this descriptor's property for the object.
   */
  public void resetPropertyValue(Object object);

  /**
   * This sets this descriptor's property for the object to the given value.
   */
  public void setPropertyValue(Object object, Object value);

  /**
   * Returns the name of the category to which this property belongs.
   */
  String getCategory(Object object);

  /**
   * Returns a brief description of this property.
   */
  String getDescription(Object object);

  /**
   * Returns the display name for this property.
   */
  String getDisplayName(Object object);

  /**
   * Returns a list of filter types to which this property belongs.
   */
  String[] getFilterFlags(Object object);

  /*
   * Returns the help context id for this property.
   */
  Object getHelpContextIds(Object object);

  /**
   * Returns the id for this property.
   */
  String getId(Object object);

  /**
   * Returns the label provider for this property.
   */
  IItemLabelProvider getLabelProvider(Object object);

  /**
   * Returns whether this property descriptor and the given one are compatible.
   */
  boolean isCompatibleWith(Object object, Object anotherObject, IItemPropertyDescriptor anotherPropertyDescriptor);

  /**
   * Returns the feature.
   */
  public Object getFeature(Object object);

  /**
   * Returns whether this property represents multiple values. This may not be the same as the feature's getMany(), as
   * the property may allows editing only a single value of a multi-valued feature.
   */
  public boolean isMany(Object object);

  /**
   * Returns the choices of all the values that this property may take one.
   */
  public Collection<?> getChoiceOfValues(Object object);

  /**
   * Returns whether this property's value will consist of multi-line text.
   * @since 2.2.0
   */
  boolean isMultiLine(Object object);

  /**
   * Returns whether the choices for this property should be sorted for display.
   * @since 2.2.0
   */
  boolean isSortChoices(Object object);

  /**
   * This interface may be implemented by item property descriptors to allow an object to be provided as an override for
   * whatever would usually be the owner of any commands created to set the property's value.  This is typically used
   * when a wrapper is being displayed in place of a real model object, so that commands will be created by the wrapper.
   */
  public interface OverrideableCommandOwner
  {
    /**
     * Sets the object to use as the owner of commands created to set the property's value.
     */
    public void setCommandOwner(Object override);

    /**
     * Returns the override command owner set via {@link #setCommandOwner setCommandOwner}.
     */
    public Object getCommandOwner();
  }
}
