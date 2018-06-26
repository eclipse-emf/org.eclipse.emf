/**
 * Copyright (c) 2002-2004 IBM Corporation and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 * 
 * Contributors: 
 *   IBM - Initial API and implementation
 */
package org.eclipse.emf.ecore;

import java.util.Map;
import java.util.Set;

import org.eclipse.emf.common.CommonPlugin;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>EData Type</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.emf.ecore.EDataType#isSerializable <em>Serializable</em>}</li>
 * </ul>
 *
 * @see org.eclipse.emf.ecore.EcorePackage#getEDataType()
 * @model
 * @generated
 */
public interface EDataType extends EClassifier
{
  /**
   * Returns the value of the '<em><b>Serializable</b></em>' attribute.
   * The default value is <code>"true"</code>.
   * <!-- begin-user-doc -->
   * <p>
   * It represents whether values of this type will be {@link org.eclipse.emf.ecore.resource.Resource#save(java.util.Map) serialized}.
   * For a serializable data type,
   * there will be factory methods of the form:
   *<pre>
   *  String convertXyzToString(EDataType metaObject, Object instanceValue)
   *  Object createXyzFromString(EDataType metaObject, String initialValue)
   *</pre>
   * in the generated factory implementation.
   * Clients will typically need to hand write the bodies of these generated methods.
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Serializable</em>' attribute.
   * @see #setSerializable(boolean)
   * @see org.eclipse.emf.ecore.EcorePackage#getEDataType_Serializable()
   * @model default="true"
   * @generated
   */
  boolean isSerializable();

  /**
   * Sets the value of the '{@link org.eclipse.emf.ecore.EDataType#isSerializable <em>Serializable</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Serializable</em>' attribute.
   * @see #isSerializable()
   * @generated
   */
  void setSerializable(boolean value);

  /**
   * Internal API implemented by all data types.
   * @since 2.8
   */
  interface Internal extends EDataType, InternalEObject
  {
    /**
     * An API for converting values of the data type to and from a string representation.
     */
    interface ConversionDelegate
    {
      /**
       * A factory for creating conversion delegates.
       */
      interface Factory
      {
        /**
         * Creates a conversion delegate for the given EDataType.
         * @param eDataType the EDataType for which a conversion delegate is to be created.
         * @return a new conversion delegate for the given EDataType.
         */
        ConversionDelegate createConversionDelegate(EDataType eDataType);

        /**
         * A <code>Factory</code> wrapper that is used by the {@link Factory.Registry}.
         */
        interface Descriptor
        {
          Factory getFactory();
        }

        /**
         * A registry of factories for creating conversion delegates.
         * @noimplement Do not implement this interface directly; instead extend {@link Impl}.
         */
        interface Registry extends Map<String, Object>
        {
          Registry INSTANCE = new Impl();

          Factory getFactory(String uri);

          /**
           * Returns the factories registered in the target platform when running with the Eclipse Plug-in Development environment,
           * a copy of the {@link #keySet()} otherwise.
           * @since 2.14
           */
          Set<String> getTargetPlatformFactories();

          class Impl extends CommonPlugin.SimpleTargetPlatformRegistryImpl<String, Object> implements Registry
          {
            private static final long serialVersionUID = 1L;

            public Set<String> getTargetPlatformFactories()
            {
              return getTargetPlatformValues("org.eclipse.emf.ecore.conversion_delegate", "uri");
            }

            @Override
            protected String createKey(String attribute)
            {
              return attribute;
            }

            @Override
            public Object get(Object key)
            {
              Object factory = super.get(key);
              if (factory instanceof Descriptor)
              {
                Descriptor factoryDescriptor = (Descriptor)factory;
                factory = factoryDescriptor.getFactory();
                put((String)key, factory);
                return factory;
              }
              else
              {
                return factory;
              }
            }

            public Factory getFactory(String uri)
            {
              return (Factory)get(uri);
            }
          }
        }
      }

      /**
       * Converts a value of the data type to a string literal.
       * @param value the value to be converted.
       * @return the literal representation of the value.
       */
      String convertToString(Object value);

      /**
       * Creates a value of the data type from a string literal.
       * @param literal the string literal to be converted.
       * @return the value of the literal representation.
       */
      Object createFromString(String literal);
    }

    /**
     * Returns the delegate for this data type.
     * A default delegate implementation need not be available,
     * so this might <code>null</code>.
     * @return the delegate for this feature.
     */
    ConversionDelegate getConversionDelegate();

    /**
     * Sets the specialized delegate for this data type.
     * @param conversionDelegate the specialized delegate.
     */
    void setConversionDelegate(ConversionDelegate conversionDelegate);
  }

} //EDataType
