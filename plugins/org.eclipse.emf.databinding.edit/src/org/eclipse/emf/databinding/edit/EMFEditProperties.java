/**
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
 */
package org.eclipse.emf.databinding.edit;

import org.eclipse.core.databinding.property.list.IListProperty;
import org.eclipse.core.databinding.property.map.IMapProperty;
import org.eclipse.core.databinding.property.set.ISetProperty;
import org.eclipse.core.databinding.property.value.IValueProperty;
import org.eclipse.emf.databinding.FeaturePath;
import org.eclipse.emf.databinding.edit.internal.EMFEditListProperty;
import org.eclipse.emf.databinding.edit.internal.EMFEditListPropertyDecorator;
import org.eclipse.emf.databinding.edit.internal.EMFEditMapProperty;
import org.eclipse.emf.databinding.edit.internal.EMFEditMapPropertyDecorator;
import org.eclipse.emf.databinding.edit.internal.EMFEditMultiListProperty;
import org.eclipse.emf.databinding.edit.internal.EMFEditResourceContentProperty;
import org.eclipse.emf.databinding.edit.internal.EMFEditSetProperty;
import org.eclipse.emf.databinding.edit.internal.EMFEditSetPropertyDecorator;
import org.eclipse.emf.databinding.edit.internal.EMFEditValueProperty;
import org.eclipse.emf.databinding.edit.internal.EMFEditValuePropertyDecorator;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.edit.domain.EditingDomain;


/**
 * <p><b>PROVISIONAL:</b> This API is subject to arbitrary change, including renaming or removal.</p>
 *
 * A factory to create property bound attributes for {@link EObject} which use an {@link EditingDomain} to make changes to the {@link EObject}
 *
 * @since 2.5
 */
public class EMFEditProperties
{
  /**
   * Turn on debug logging
   */
  public static final boolean DEBUG = false;

  /**
   * Returns a value property for the given {@link EStructuralFeature}
   *
   * @param editingDomain the editing domain
   * @param feature
   *            the feature instance the property is created for
   * @return a value property for the given {@link EStructuralFeature}
   */
  public static IEMFEditValueProperty value(EditingDomain editingDomain, EStructuralFeature feature)
  {
    return value(editingDomain, FeaturePath.fromList(feature));
  }

  /**
   * Returns a value property for the given nested {@link EStructuralFeature}
   * feature like the <code>name</code> of a <code>person</code>
   *
   * @param editingDomain the editing domain
   * @param featurePath
   *            path to the feature
   * @return a value property for the given {@link FeaturePath}
   */
  public static IEMFEditValueProperty value(EditingDomain editingDomain, FeaturePath featurePath)
  {
    IValueProperty property;
    property = new EMFEditValueProperty(editingDomain, featurePath.getFeaturePath()[0]);

    IEMFEditValueProperty featureProperty = new EMFEditValuePropertyDecorator(editingDomain, property, featurePath.getFeaturePath()[0]);

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
   * @param editingDomain
   *            the editing domain
   * @param features
   *            the feature instances the properties are created for
   * @return an array of properties for the given {@link EStructuralFeature}s
   */
  public static IEMFEditValueProperty[] values(EditingDomain editingDomain, EStructuralFeature... features)
  {
    IEMFEditValueProperty[] properties = new IEMFEditValueProperty [features.length];
    for (int i = 0; i < properties.length; i++)
      properties[i] = value(editingDomain, features[i]);
    return properties;
  }

  /**
   * Returns multiple value property for the given nested
   * {@link EStructuralFeature} features like the <code>name</code> of a
   * <code>person</code>
   *
   * @param editingDomain
   *            the editing domain
   * @param featurePaths
   *            path to the feature
   * @return an array of properties for the given {@link FeaturePath}s
   */
  public static IEMFEditValueProperty[] values(EditingDomain editingDomain, FeaturePath... featurePaths)
  {
    IEMFEditValueProperty[] properties = new IEMFEditValueProperty [featurePaths.length];
    for (int i = 0; i < properties.length; i++)
      properties[i] = value(editingDomain, featurePaths[i]);
    return properties;
  }

  /**
   * Returns a set property for the given {@link EStructuralFeature}
   * @param editingDomain
   *            the editing domain
   * @param feature
   *            the feature instance the property is created for
   * @return a list property for the given {@link EStructuralFeature}
   */
  public static IEMFEditSetProperty set(EditingDomain editingDomain, EStructuralFeature feature)
  {
    ISetProperty property;
    property = new EMFEditSetProperty(editingDomain, feature);
    return new EMFEditSetPropertyDecorator(editingDomain, property, feature);
  }

  /**
   * Returns a set property for the given {@link FeaturePath}
   * @param editingDomain
   *            the editing domain
   * @param featurePath the feature path
   * @return a list property for the given {@link FeaturePath}
   */
  public static IEMFEditSetProperty set(EditingDomain editingDomain, FeaturePath featurePath)
  {
    int len = featurePath.getFeaturePath().length;
    if (len > 1)
    {
      IValueProperty property;
      property = new EMFEditValueProperty(editingDomain, featurePath.getFeaturePath()[0]);

      IEMFEditValueProperty featureProperty = new EMFEditValuePropertyDecorator(editingDomain, property, featurePath.getFeaturePath()[0]);

      for (int i = 1; i < featurePath.getFeaturePath().length - 1; i++)
      {
        featureProperty = featureProperty.value(featurePath.getFeaturePath()[i]);
      }

      return featureProperty.set(set(editingDomain, featurePath.getFeaturePath()[len - 1]));
    }
    else
    {
      return set(editingDomain, featurePath.getFeaturePath()[len - 1]);
    }
  }

  /**
   * Returns a list property for the given {@link EStructuralFeature}
   *
   * @param editingDomain
   *            the editing domain
   * @param feature
   *            the feature instance the property is created for
   * @return a list property for the given {@link EStructuralFeature}
   */
  public static IEMFEditListProperty list(EditingDomain editingDomain, EStructuralFeature feature)
  {
    IListProperty property;
    property = new EMFEditListProperty(editingDomain, feature);
    return new EMFEditListPropertyDecorator(editingDomain, property, feature);
  }

  /**
   * Returns a list property for the given {@link FeaturePath}
   * @param editingDomain
   * @param featurePath
   * @return a list property for the given {@link FeaturePath}
   */
  public static IEMFEditListProperty list(EditingDomain editingDomain, FeaturePath featurePath)
  {
    int len = featurePath.getFeaturePath().length;
    if (len > 1)
    {
      IValueProperty property;
      property = new EMFEditValueProperty(editingDomain, featurePath.getFeaturePath()[0]);

      IEMFEditValueProperty featureProperty = new EMFEditValuePropertyDecorator(editingDomain, property, featurePath.getFeaturePath()[0]);

      for (int i = 1; i < featurePath.getFeaturePath().length - 1; i++)
      {
        featureProperty = featureProperty.value(featurePath.getFeaturePath()[i]);
      }

      return featureProperty.list(list(editingDomain, featurePath.getFeaturePath()[len - 1]));
    }
    else
    {
      return list(editingDomain, featurePath.getFeaturePath()[len - 1]);
    }
  }

  /**
   * Combine multiple multi-value features into one observable list property
   * @param editingDomain the editing domain
   * @param features the features to add to the list
   * @return the list property
   */
  public static IEMFEditListProperty multiList(EditingDomain editingDomain, EStructuralFeature... features)
  {
    IEMFEditListProperty[] multi = new IEMFEditListProperty [features.length];
    int i = 0;
    for (EStructuralFeature feature : features)
    {
      multi[i++] = list(editingDomain, feature);
    }

    return multiList(editingDomain, multi);
  }

  /**
   * Combine multiple features below a common path into one observable list property
   * @param editingDomain the editing domain
   * @param rootPath the root path
   * @param features the features
   * @return the list property
   */
  public static IEMFEditListProperty multiList(EditingDomain editingDomain, FeaturePath rootPath, EStructuralFeature... features)
  {
    IEMFEditListProperty[] multi = new IEMFEditListProperty [features.length];
    int i = 0;
    int l = rootPath.getFeaturePath().length;
    for (EStructuralFeature f : features)
    {
      EStructuralFeature[] p = new EStructuralFeature [l + 1];
      System.arraycopy(rootPath.getFeaturePath(), 0, p, 0, l);
      p[l] = f;
      multi[i++] = list(editingDomain, FeaturePath.fromList(p));
    }
    return multiList(editingDomain, multi);
  }

  /**
   * Combine the features identified by the the path into one observable list property
   * @param editingDomain the editing domain
   * @param featurePaths the feature paths
   * @return the list property
   */
  public static IEMFEditListProperty multiList(EditingDomain editingDomain, FeaturePath... featurePaths)
  {
    IEMFEditListProperty[] multi = new IEMFEditListProperty [featurePaths.length];
    int i = 0;

    for (FeaturePath path : featurePaths)
    {
      multi[i++] = list(editingDomain, path);
    }

    return multiList(editingDomain, multi);
  }

  /**
   * Combine the given list properties into one observable list property
   * @param editingDomain the editing domain
   * @param properties the properties
   * @return the list property
   */
  public static IEMFEditListProperty multiList(EditingDomain editingDomain, IEMFEditListProperty... properties)
  {
    return new EMFEditMultiListProperty(editingDomain, properties);
  }

  /**
   * Returns a map property for the given {@link EStructuralFeature}. Objects lacking the named property are treated the same as if the
   * property always contains an empty map.
   *
   * @param editingDomain
   *            the editing domain
   * @param feature
   *            the feature the property is created for
   * @return a map property for the given {@link EStructuralFeature}
   */
  public static IEMFEditMapProperty map(EditingDomain editingDomain, EStructuralFeature feature)
  {
    IMapProperty property;
    property = new EMFEditMapProperty(editingDomain, feature);
    return new EMFEditMapPropertyDecorator(editingDomain, property, feature);
  }

  /**
   * Returns a property to observe a resource-content
   *
   * @param editingDomain the editing domain
   * @return the property
   * @since 2.6
   */
  public static IEMFEditListProperty resource(EditingDomain editingDomain)
  {
    IListProperty property;
    property = new EMFEditResourceContentProperty(editingDomain);
    return new EMFEditListPropertyDecorator(editingDomain, property, null);
  }
}
