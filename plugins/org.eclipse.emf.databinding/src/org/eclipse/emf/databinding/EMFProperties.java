/**
 * <copyright>
 *
 * Copyright (c) 2008 Matthew Hall and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *   Matthew Hall - initial API and implementation (bug 194734)
 *   Matthew Hall - bug 195222, 247997, 261843, 264307
 *   Hasan Ceylan  - patch in bug 262160
 *   Tom Schindl <tom.schindl@bestsolution.at> - port to EMF in 262160
 * </copyright>
 *
 * $Id: EMFProperties.java,v 1.4 2010/04/14 15:44:50 tschindl Exp $
 */
package org.eclipse.emf.databinding;

import org.eclipse.core.databinding.property.list.IListProperty;
import org.eclipse.core.databinding.property.map.IMapProperty;
import org.eclipse.core.databinding.property.set.ISetProperty;
import org.eclipse.core.databinding.property.value.IValueProperty;
import org.eclipse.emf.databinding.internal.EMFMultiListProperty;
import org.eclipse.emf.databinding.internal.EMFListProperty;
import org.eclipse.emf.databinding.internal.EMFListPropertyDecorator;
import org.eclipse.emf.databinding.internal.EMFMapProperty;
import org.eclipse.emf.databinding.internal.EMFMapPropertyDecorator;
import org.eclipse.emf.databinding.internal.EMFResourceContentProperty;
import org.eclipse.emf.databinding.internal.EMFSetProperty;
import org.eclipse.emf.databinding.internal.EMFSetPropertyDecorator;
import org.eclipse.emf.databinding.internal.EMFValueProperty;
import org.eclipse.emf.databinding.internal.EMFValuePropertyDecorator;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;


/**
 * <p><b>PROVISIONAL:</b> This API is subject to arbitrary change, including renaming or removal.</p>
 *
 * A factory to create property bound attributes for {@link EObject}
 *
 * @since 2.5
 */
public class EMFProperties
{
  /**
   * Debug constant to turn on/off debugging
   */
  public static final boolean DEBUG = false;

  /**
   * Returns a value property for the given {@link EStructuralFeature}
   *
   * @param feature
   *            the feature instance the property is created for
   * @return a value property for the given {@link EStructuralFeature}
   */
  public static IEMFValueProperty value(EStructuralFeature feature)
  {
    return value(FeaturePath.fromList(feature));
  }

  /**
   * Returns a value property for the given nested {@link EStructuralFeature}
   * feature like the <code>name</code> of a <code>person</code>
   *
   * @param featurePath
   *            path to the feature
   * @return a value property for the given {@link FeaturePath}
   */
  public static IEMFValueProperty value(FeaturePath featurePath)
  {
    IValueProperty property;
    property = new EMFValueProperty(featurePath.getFeaturePath()[0]);

    IEMFValueProperty featureProperty = new EMFValuePropertyDecorator(property, featurePath.getFeaturePath()[0]);

    for (int i = 1; i < featurePath.getFeaturePath().length; i++)
    {
      featureProperty = featureProperty.value(featurePath.getFeaturePath()[i]);
    }

    return featureProperty;
  }

  /**
   * Returns multiple value properties for the given
   * {@link EStructuralFeature}s
   *
   * @param features
   *            the feature instances the properties are created for
   * @return an array of properties for the given {@link EStructuralFeature}s
   */
  public static IEMFValueProperty[] values(EStructuralFeature... features)
  {
    IEMFValueProperty[] properties = new IEMFValueProperty [features.length];
    for (int i = 0; i < properties.length; i++)
      properties[i] = value(features[i]);
    return properties;
  }

  /**
   * Returns multiple value property for the given nested
   * {@link EStructuralFeature} features like the <code>name</code> of a
   * <code>person</code>
   *
   * @param featurePaths
   *            path to the feature
   * @return an array of properties for the given {@link FeaturePath}s
   */
  public static IEMFValueProperty[] values(FeaturePath... featurePaths)
  {
    IEMFValueProperty[] properties = new IEMFValueProperty [featurePaths.length];
    for (int i = 0; i < properties.length; i++)
      properties[i] = value(featurePaths[i]);
    return properties;
  }

  /**
   * Returns a list property for the given {@link EStructuralFeature}
   *
   * @param feature
   *            the feature instance the property is created for
   * @return a list property for the given {@link EStructuralFeature}
   */
  public static IEMFListProperty list(EStructuralFeature feature)
  {
    IListProperty property;
    property = new EMFListProperty(feature);
    return new EMFListPropertyDecorator(property, feature);
  }

  /**
   * Returns a list property for the given {@link FeaturePath}
   * @param featurePath the feature path
   * @return a list property for the given {@link FeaturePath}
   */
  public static IEMFListProperty list(FeaturePath featurePath)
  {
    int len = featurePath.getFeaturePath().length;
    if (len > 1)
    {
      IValueProperty property;
      property = new EMFValueProperty(featurePath.getFeaturePath()[0]);

      IEMFValueProperty featureProperty = new EMFValuePropertyDecorator(property, featurePath.getFeaturePath()[0]);

      for (int i = 1; i < featurePath.getFeaturePath().length - 1; i++)
      {
        featureProperty = featureProperty.value(featurePath.getFeaturePath()[i]);
      }

      return featureProperty.list(list(featurePath.getFeaturePath()[len - 1]));
    }
    else
    {
      return list(featurePath.getFeaturePath()[len - 1]);
    }
  }

  /**
   * Returns a set property for the given {@link EStructuralFeature}
   *
   * @param feature
   *            the feature instance the property is created for
   * @return a list property for the given {@link EStructuralFeature}
   */
  public static IEMFSetProperty set(EStructuralFeature feature)
  {
    ISetProperty property;
    property = new EMFSetProperty(feature);
    return new EMFSetPropertyDecorator(property, feature);
  }

  /**
   * Returns a set property for the given {@link FeaturePath}
   * @param featurePath the feature path
   * @return a list property for the given {@link FeaturePath}
   */
  public static IEMFSetProperty set(FeaturePath featurePath)
  {
    int len = featurePath.getFeaturePath().length;
    if (len > 1)
    {
      IValueProperty property;
      property = new EMFValueProperty(featurePath.getFeaturePath()[0]);

      IEMFValueProperty featureProperty = new EMFValuePropertyDecorator(property, featurePath.getFeaturePath()[0]);

      for (int i = 1; i < featurePath.getFeaturePath().length - 1; i++)
      {
        featureProperty = featureProperty.value(featurePath.getFeaturePath()[i]);
      }

      return featureProperty.set(set(featurePath.getFeaturePath()[len - 1]));
    }
    else
    {
      return set(featurePath.getFeaturePath()[len - 1]);
    }
  }

  /**
   * Combine multiple multi-value features into one observable list property
   * @param features the features to add to the list
   * @return the list property
   */
  public static IEMFListProperty multiList(EStructuralFeature... features)
  {
    IEMFListProperty[] multi = new IEMFListProperty [features.length];
    int i = 0;
    for (EStructuralFeature feature : features)
    {
      multi[i++] = list(feature);
    }

    return multiList(multi);
  }

  /**
   * Combine multiple features below a common path into one observable list property
   * @param rootPath the root path
   * @param features the features
   * @return the list property
   */
  public static IEMFListProperty multiList(FeaturePath rootPath, EStructuralFeature... features)
  {
    IEMFListProperty[] multi = new IEMFListProperty [features.length];
    int i = 0;
    int l = rootPath.getFeaturePath().length;
    for (EStructuralFeature f : features)
    {
      EStructuralFeature[] p = new EStructuralFeature [l + 1];
      System.arraycopy(rootPath.getFeaturePath(), 0, p, 0, l);
      p[l] = f;
      multi[i++] = list(FeaturePath.fromList(p));
    }
    return multiList(multi);
  }

  /**
   * Combine the features identified by the the path into one observable list property
   * @param featurePaths the feature paths
   * @return the list property
   */
  public static IEMFListProperty multiList(FeaturePath... featurePaths)
  {
    IEMFListProperty[] multi = new IEMFListProperty [featurePaths.length];
    int i = 0;

    for (FeaturePath path : featurePaths)
    {
      multi[i++] = list(path);
    }

    return multiList(multi);
  }

  /**
   * Combine the given list properties into one observable list property
   * @param properties the properties
   * @return the list property
   */
  public static IEMFListProperty multiList(IEMFListProperty... properties)
  {
    return new EMFMultiListProperty(properties);
  }

  /**
   * Returns a map property for the given {@link EStructuralFeature}. Objects lacking the named property are treated the same as if the
   * property always contains an empty map.
   *
   * @param feature
   *            the feature the property is created for
   * @return a map property for the given {@link EStructuralFeature}
   */
  public static IEMFMapProperty map(EStructuralFeature feature)
  {
    IMapProperty property;
    property = new EMFMapProperty(feature);
    return new EMFMapPropertyDecorator(property, feature);
  }

  /**
   * Returns a property to observe a resource-content
   * @return the property
   * @since 2.6
   */
  public static IEMFListProperty resource()
  {
    IListProperty property;
    property = new EMFResourceContentProperty();
    return new EMFListPropertyDecorator(property, null);
  }
}
