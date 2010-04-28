/**
 * <copyright> 
 *
 * Copyright (c) 2002-2007 IBM Corporation and others.
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
 * $Id: AddCommand.java,v 1.1 2010/04/28 14:48:44 emerks Exp $
 */
package org.eclipse.emf.edit.command;


import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.util.ExtendedMetaData;
import org.eclipse.emf.ecore.util.FeatureMap;
import org.eclipse.emf.ecore.util.FeatureMapUtil;
import org.eclipse.emf.ecore.xml.type.XMLTypePackage;
import org.eclipse.emf.edit.EMFEditPlugin;
import org.eclipse.emf.edit.domain.EditingDomain;


/**
 * The add command logically acts upon an owner object that has a collection-based feature to which other objects can be added.
 * The static create methods delegate command creation to {@link EditingDomain#createCommand EditingDomain.createCommand},
 * which may or may not result in the actual creation of an instance of this class.
 * 
 * <p>
 * The implementation of this class is low-level and EMF specific;
 * it allows one or more objects to be added to a many-valued feature of an owner,
 * i.e., it is equivalent of the call
 * <pre>
 *   ((EList)((EObject)owner).eGet((EStructuralFeature)feature)).addAll(index, (Collection)collection);
 * </pre>
 *
 * <p>
 * It can also be used as an equivalent to the call
 * <pre>
 *   ((EList)extent).addAll(index, (Collection)collection);
 * </pre>
 * which is how root objects are added into the contents of a resource.
 * Like all the low-level commands in this package, the add command is undoable.
 *
 * <p>
 * An add command is an {@link OverrideableCommand}.
 */
public class AddCommand extends AbstractOverrideableCommand
{
  /**
   * This creates a command to add a particular value to the specified feature of the owner.
   * The feature will often be null because the domain will deduce it.
   */
  public static Command create(EditingDomain domain, Object owner, Object feature, Object value)
  {
    return create(domain, owner, feature, Collections.singleton(value), CommandParameter.NO_INDEX);
  }

  /**
   * This creates a command to insert particular value at a particular index in the specified feature of the owner.
   * The feature will often be null because the domain will deduce it.
   */
  public static Command create(EditingDomain domain, Object owner, Object feature, Object value, int index)
  {
    return create(domain, owner, feature, Collections.singleton(value), index);
  }

  /**
   * This creates a command to add a collection of values to the specified feature of the owner.
   * The feature will often be null because the domain will deduce it.
   */
  public static Command create(EditingDomain domain, Object owner, Object feature, Collection<?> collection)
  {
    return domain.createCommand(AddCommand.class, new CommandParameter(owner, feature, collection, CommandParameter.NO_INDEX));
  }

  /**
   * This creates a command to insert a collection of values at a particular index in the specified feature of the owner.
   * The feature will often be null because the domain will deduce it.
   */
  public static Command create(EditingDomain domain, Object owner, Object feature, Collection<?> collection, int index)
  {
    return domain.createCommand(AddCommand.class, new CommandParameter(owner, feature, collection, index));
  }

  /**
   * This caches the label.
   */
  protected static final String LABEL = EMFEditPlugin.INSTANCE.getString("_UI_AddCommand_label");

  /**
   * This caches the description.
   */
  protected static final String DESCRIPTION = EMFEditPlugin.INSTANCE.getString("_UI_AddCommand_description");

  /**
   * This caches the description for a list-based addition.
   */
  protected static final String DESCRIPTION_FOR_LIST = EMFEditPlugin.INSTANCE.getString("_UI_AddCommand_description_for_list");
    
  /**
   * This is the owner object upon which the command will act.
   * It could be null in the case that we are dealing with an {@link org.eclipse.emf.common.util.EList}.
   */
  protected EObject owner;

  /**
   * This is the feature of the owner object upon the command will act.
   * It could be null, in the case that we are dealing with an {@link org.eclipse.emf.common.util.EList}.
   */
  protected EStructuralFeature feature;

  /**
   * This is the list to which the command will add the collection.
   */
  protected EList<Object> ownerList;

  /**
   * This is the collection of objects being added to the owner list.
   */
  protected Collection<?> collection;

  /**
   * This is the position at which the objects will be inserted.
   */
  protected int index;

  /**
   * This is the value returned by {@link Command#getAffectedObjects}. 
   * The affected objects are different after an execute than after an undo, so we record it.
   */
  protected Collection<?> affectedObjects;

  /**
   * This constructs a primitive command to add a particular value to the specified many-valued feature of the owner.
   */
  public AddCommand(EditingDomain domain, EObject owner, EStructuralFeature feature, Object value)
  {
    this(domain, owner, feature, Collections.singleton(value), CommandParameter.NO_INDEX);
  }

  /**
   * This constructs a primitive command to insert particular value into the specified many-valued feature of the owner.
   */
  public AddCommand(EditingDomain domain, EObject owner, EStructuralFeature feature, Object value, int index)
  {
    this(domain, owner, feature, Collections.singleton(value), index);
  }

  /**
   * This constructs a primitive command to add a collection of values to the specified many-valued feature of the owner.
   */
  public AddCommand(EditingDomain domain, EObject owner, EStructuralFeature feature, Collection<?> collection)
  {
    this(domain, owner, feature, collection, CommandParameter.NO_INDEX);
  }

  /**
   * This constructs a primitive command to insert a collection of values into the specified many-valued feature of the owner.
   */
  public AddCommand(EditingDomain domain, EObject owner, EStructuralFeature feature, Collection<?> collection, int index)
  {
    super(domain, LABEL, DESCRIPTION);

    this.owner = owner;
    this.feature = feature;
    this.collection = collection;
    this.index = index;

    ownerList = getOwnerList(owner, feature);
  }

  /**
   * This constructs a primitive command to add a particular value into the specified extent.
   */
  public AddCommand(EditingDomain domain, EList<?> list, Object value)
  {
    this(domain, list, Collections.singleton(value), CommandParameter.NO_INDEX);
  }

  /**
   * This constructs a primitive command to insert particular value into the specified extent.
   */
  public AddCommand(EditingDomain domain, EList<?> list, Object value, int index)
  {
    this(domain, list, Collections.singleton(value), index);
  }

  /**
   * This constructs a primitive command to insert a collection of values into the specified extent.
   */
  public AddCommand(EditingDomain domain, EList<?> list, Collection<?> collection)
  {
    this(domain, list, collection, CommandParameter.NO_INDEX);
  }

  /**
   * This constructs a primitive command to insert a collection of values into the specified extent.
   */
  public AddCommand(EditingDomain domain, EList<?> list, Collection<?> collection, int index)
  {
    super(domain, LABEL, DESCRIPTION_FOR_LIST);

    this.collection = collection;
    this.index = index;

    @SuppressWarnings("unchecked")
    EList<Object> untypedList = (EList<Object>)list;
    ownerList = untypedList;
  }

  /**
   * This returns the owner object upon which the command will act.
   * It could be null in the case that we are dealing with an {@link org.eclipse.emf.common.util.EList}.
   */
  public EObject getOwner()
  {
    return owner;
  }

  /**
   * This returns the feature of the owner object upon the command will act.
   * It could be null, in the case that we are dealing with an {@link org.eclipse.emf.common.util.EList}.
   */
  public EStructuralFeature getFeature()
  {
    return feature;
  }

  /**
   * This returns the list to which the command will add.
   */
  public EList<?> getOwnerList()
  {
    return ownerList;
  }

  /**
   * This returns the collection of objects being added.
   */
  public Collection<?> getCollection()
  {
    return collection;
  }

  /**
   * This returns the position at which the objects will be added.
   */
  public int getIndex()
  {
    return index;
  }

  protected boolean isUserElement(EStructuralFeature entryFeature)
  {
    return
      entryFeature != XMLTypePackage.Literals.XML_TYPE_DOCUMENT_ROOT__TEXT &&
      entryFeature != XMLTypePackage.Literals.XML_TYPE_DOCUMENT_ROOT__CDATA &&
      entryFeature != XMLTypePackage.Literals.XML_TYPE_DOCUMENT_ROOT__COMMENT &&
      entryFeature != XMLTypePackage.Literals.XML_TYPE_DOCUMENT_ROOT__PROCESSING_INSTRUCTION;
  }

  @Override
  protected boolean prepare()
  {
    // If there is no list to add to, no collection or an empty collection from which to add, or the index is out of range...
    //
    if (ownerList == null || 
          collection == null ||
          collection.size() == 0 ||
          index != CommandParameter.NO_INDEX && (index < 0 || index > ownerList.size()))
    {
      return false;
    }

    if (feature != null) 
    {
      // If it's a feature map, we'll need to validate the entry feature and enforce its multiplicity restraints.
      //
      FeatureMapUtil.Validator validator = null;
      boolean documentRoot = false;
      Set<EStructuralFeature> entryFeatures = Collections.emptySet();

      if (FeatureMapUtil.isFeatureMap(feature))
      {
        EClass eClass = owner.eClass();
        validator = FeatureMapUtil.getValidator(eClass, feature); 

        // Keep track of all the entry features that are already in the feature map and that will be added, excluding
        // XML text, CDATA, and comments (if we're in a mixed type).
        //
        documentRoot = ExtendedMetaData.INSTANCE.getDocumentRoot(eClass.getEPackage()) == eClass;
        boolean mixed = documentRoot || ExtendedMetaData.INSTANCE.getContentKind(eClass) == ExtendedMetaData.MIXED_CONTENT;
        entryFeatures = new HashSet<EStructuralFeature>();
        for (Object entry : ownerList)
        {
          EStructuralFeature entryFeature = ((FeatureMap.Entry)entry).getEStructuralFeature();
          if (!mixed || isUserElement(entryFeature))
          {
            entryFeatures.add(entryFeature);
          }
        }
      }

      // Check each object... 
      //
      for (Object object : collection)
      {
        boolean containment = false;

        // Check type of object.
        //
        if (!feature.getEType().isInstance(object))
        {
          return false;
        }

        // Check that the object isn't already in a unique list.
        //
        if (feature.isUnique() && ownerList.contains(object))
        {
          return false;
        }

        // For feature maps, test that the entry feature is a valid type, that the entry value is an instance of it,
        // that there is not already something in a document root, and that there is not already something in a
        // single-valued entry feature.
        //
        if (validator != null)
        {
          FeatureMap.Entry entry = (FeatureMap.Entry)object;
          EStructuralFeature entryFeature = entry.getEStructuralFeature();
          containment = entryFeature instanceof EReference && ((EReference)entryFeature).isContainment();
          
          if (!validator.isValid(entryFeature) || !entryFeature.getEType().isInstance(entry.getValue()))
          {
            return false;
          }

          if (documentRoot)
          {
            if (isUserElement(entryFeature))
            {
              if (!entryFeatures.isEmpty())
              {
                return false;
              }
              entryFeatures.add(entryFeature);
            }
          }
          else if (!entryFeatures.add(entryFeature) && !FeatureMapUtil.isMany(owner, entryFeature)) 
          {
            return false;
          }
        }

        // Check to see if a container is being put into a contained object.
        //
        containment |= feature instanceof EReference && ((EReference)feature).isContainment();
        if (containment)
        {
          for (EObject container = owner; container != null; container = container.eContainer()) 
          {
            if (object == container) 
            {
              return false;
            }
          }
        }
      }
    }

    if (owner != null && domain.isReadOnly(owner.eResource()))
    {
      return false;
    }

    return true;
  }

  @Override
  public void doExecute() 
  {
    // Simply add the collection to the list.
    //
    if (index == CommandParameter.NO_INDEX)
    {
      ownerList.addAll(collection);
    }
    else
    {
      ownerList.addAll(index, collection);
    }

    // Update the containing map, if necessary.
    //
    updateEMap(owner, feature);

    // We'd like the collection of things added to be selected after this command completes.
    //
    affectedObjects = collection;
  }

  @Override
  public void doUndo() 
  {
    // Remove the collection from the list by index.
    //
    int i = index != CommandParameter.NO_INDEX ? index : ownerList.size() - collection.size();
    ownerList.subList(i, i + collection.size()).clear();    
  
    // Update the containing map, if necessary.
    //
    updateEMap(owner, feature);

    // We'd like the owner selected after this undo completes.
    //
    affectedObjects = owner == null ? Collections.EMPTY_SET : Collections.singleton(owner);
  }

  @Override
  public void doRedo()
  {
    // Simply add the collection to the list.
    //
    if (index == CommandParameter.NO_INDEX)
    {
      ownerList.addAll(collection);
    }
    else
    {
      ownerList.addAll(index, collection);
    }

    // Update the containing map, if necessary.
    //
    updateEMap(owner, feature);

    // We'd like the collection of things added to be selected after this command completes.
    //
    affectedObjects = collection;
  }

  @Override
  public Collection<?> doGetResult()
  {
    return collection;
  }

  @Override
  public Collection<?> doGetAffectedObjects()
  {
    return affectedObjects;
  }

  /**
   * This gives an abbreviated name using this object's own class' name, without package qualification,
   * followed by a space separated list of <tt>field:value</tt> pairs.
   */
  @Override
  public String toString()
  {
    StringBuffer result = new StringBuffer(super.toString());
    result.append(" (owner: " + owner + ")");
    result.append(" (feature: " + feature + ")");
    result.append(" (ownerList: " + ownerList + ")");
    result.append(" (collection: " + collection + ")");
    result.append(" (index: " + index + ")");
    result.append(" (affectedObjects:" + affectedObjects + ")");

    return result.toString();
  }
}
