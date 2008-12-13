/**
 * <copyright> 
 *
 * Copyright (c) 2002-2006 IBM Corporation and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: 
 *   IBM - Initial API and implementation
 *
 * </copyright>
 *
 * $Id: CommandParameter.java,v 1.5 2008/12/13 15:56:01 emerks Exp $
 */
package org.eclipse.emf.edit.command;


import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;


/**
 * This is a convenient common base class for all the command parameters need by the various types of commands.
 * It provides particular support for the encodings need by the basic EMF-based command implementations.
 */
public class CommandParameter 
{
  /**
   * This value is used to indicate that the optional positional index indicator is unspecified.
   */
  public static final int NO_INDEX = -1;

  /**
   * This is the object that is the target or subject of the command.
   */
  public Object owner;

  /**
   * This is the aspect of the owner that will be affected.
   */
  public Object feature;

  /**
   * This is the collection of values involved in the command.
   */
  public Collection<?> collection;

  /**
   * This is the single value involved in the command.
   */
  public Object value;

  /**
   * This the index (usually the position indicator) of the command.
   */
  public int index;

  /**
   * This creates an instance specifying only an owner.
   */
  public CommandParameter(Object owner)
  {
    this.owner = owner;
  }

  /**
   * This creates an instance specifying an owner, a feature, and a value.
   */
  public CommandParameter(Object owner, Object feature, Object value)
  {
    this.owner = owner;
    this.feature = feature;
    this.value = value;
    this.index = NO_INDEX;
  }

  /**
   * This creates an instance specifying an owner, a feature, a value, and an index.
   */
  public CommandParameter(Object owner, Object feature, Object value, int index)
  {
    this.owner = owner;
    this.feature = feature;
    this.value = value;
    this.index = index;
  }

  /**
   * This creates an instance specifying an owner, a feature, and a collection of values.
   */
  public CommandParameter(Object owner, Object feature, Collection<?> collection)
  {
    this.owner = owner;
    this.feature = feature;
    this.collection = collection;
    this.index = NO_INDEX;
  }

  /**
   * This creates an instance specifying an owner, a feature, a collection of values, and an index.
   */
  public CommandParameter(Object owner, Object feature, Collection<?> collection, int index)
  {
    this.owner = owner;
    this.feature = feature;
    this.collection = collection;
    this.index = index;
  }

  /**
   * This creates an instance specifying an owner, a feature, and a value, and a collection.
   */
  public CommandParameter(Object owner, Object feature, Object value, Collection<?> collection)
  {
    this.owner = owner;
    this.feature = feature;
    this.value = value;
    this.collection = collection;
    this.index = NO_INDEX;
  }

  /**
   * This creates an instance specifying an owner, a feature, a value, a collection, and an index.
   */
  public CommandParameter(Object owner, Object feature, Object value, Collection<?> collection, int index)
  {
    this.owner = owner;
    this.feature = feature;
    this.value = value;
    this.collection = collection;
    this.index = index;
  }

  /**
   * This returns the specified owner.
   */
  public Object getOwner()
  {
    return owner;
  }

  /**
   * This returns the specified owner as a EObject, if it is one.
   */
  public EObject getEOwner()
  {
    return owner instanceof EObject ? (EObject)owner : null;
  }

  /**
   * This sets the owner to the specified value.
   */
  public void setOwner(Object owner)
  {
    this.owner = owner;
  }

  /**
   * This returns the specified feature.
   */
  public Object getFeature()
  {
    return feature;
  }

  /**
   * This returns the specified feature as a EStructuralFeature, if it is one.
   */
  public EStructuralFeature getEStructuralFeature()
  {
    return feature instanceof EStructuralFeature ? (EStructuralFeature)feature : null;
  }

  /**
   * This returns the specified feature as a EReference, if it is one.
   */
  public EReference getEReference()
  {
    return feature instanceof EReference ? (EReference)feature : null;
  }

  /**
   * This returns the specified feature as a EReference, if it is one.
   */
  public EAttribute getEAttribute()
  {
    return feature instanceof EAttribute ? (EAttribute)feature : null;
  }

  /**
   * This is a safe way to get the list affected by the parameters for an add or remove specification.
   * It can return either the {@link org.eclipse.emf.common.util.EList}, if the owner is one,
   * or it tries to get the EList specified by the feature of the owner.
   *
   * <p>
   * It works as an excellent guard for poorly formed parameters.
   */
  public EList<?> getOwnerList()
  {
    if (owner instanceof EObject)
    {
      EObject eOwner = (EObject)owner;
      if (eOwner.eClass().getEAllStructuralFeatures().contains(feature))
      {
        EStructuralFeature eStructuralFeature = (EStructuralFeature)feature;
        if (eStructuralFeature.isMany())
        {
          return (EList<?>)eOwner.eGet(eStructuralFeature);
        }
      }
    }
    else if (owner instanceof EList<?>)
    {
      return (EList<?>)owner;
    }

    return null;
  }

  /**
   * This returns the specified collection.
   */
  public Collection<?> getCollection()
  {
    return collection;
  }

  /**
   * This returns the specified collection as a list.
   * If the collection isn't a list, a new copy is created.
   */
  public List<?> getList()
  {
    return 
      collection == null ? 
        null : 
        collection instanceof List<?> ? 
          (List<?>)collection : 
          new ArrayList<Object>(collection);
  }

  /**
   * This returns the specified value.
   */
  public Object getValue()
  {
    return value;
  }

  /**
   * This returns the specified value as a EObject, if it is one.
   */
  public EObject getEValue()
  {
    return value instanceof EObject ? (EObject)value : null;
  }

  /**
   * This returns the specified index.
   */
  public int getIndex()
  {
    return index;
  }

  /**
   * This yields an encoding of the owner-child relation.
   */
  public Collection<String> getParameters() 
  {
    Collection<String> parameters = new ArrayList<String>();

    EObject eObject = getEOwner();
    EStructuralFeature eStructuralFeature = getEStructuralFeature();
    if (eObject != null && eStructuralFeature != null)
    {
      parameters.add(eObject.eClass().getName());
      parameters.add(eStructuralFeature.getEType().getName());
    }

    return parameters;
  }
 
  public static String collectionToString(Collection<?> collection)
  {
    if (collection == null)
    {
      return "null";
    }
    else
    {
      StringBuffer result = new StringBuffer();

      result.append("{ ");

      for (Iterator<?> objects = collection.iterator(); objects.hasNext(); )
      {
        result.append(objects.next());
        if (objects.hasNext())
        {
          result.append(", ");
        }
      }

      result.append(" }");

      return result.toString();
    }
  }

  @Override
  public String toString()
  {
    StringBuffer result = new StringBuffer();

    result.append("CommandParameter");

    result.append("\n  owner        = ");
    result.append(owner);

    result.append("\n  feature      = ");
    result.append(feature);

    if (getOwnerList() != null)
    {
      result.append("\n  ownerList  = ");
      result.append(collectionToString(getOwnerList()));
    }

    if (collection != null)
    {
      result.append("\n  collection   = ");
      result.append(collectionToString(collection));
    }

    if (value != null)
    {
      result.append("\n  value        = ");
      result.append(value);
    }

    if (index != NO_INDEX)
    {
      result.append("\n  index        = ");
      result.append(index);
    }

    return result.toString();
  }
}
