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
 * $Id: SetCommand.java,v 1.12 2007/06/14 18:32:42 emerks Exp $
 */
package org.eclipse.emf.edit.command;


import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.ListIterator;

import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.command.CommandWrapper;
import org.eclipse.emf.common.command.CompoundCommand;
import org.eclipse.emf.common.command.StrictCompoundCommand;
import org.eclipse.emf.common.util.BasicEList;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.EcorePackage;
import org.eclipse.emf.edit.EMFEditPlugin;
import org.eclipse.emf.edit.domain.EditingDomain;


/**
 * The set command logically acts upon an owner object to set a particular feature to a specified value or to unset a feature.
 * The static create methods delegate command creation to {@link EditingDomain#createCommand EditingDomain.createCommand},
 * which may or may not result in the actual creation of an instance of this class.
 *
 * <p>
 * The implementation of this class is low-level and EMF specific;
 * it allows a value to be set to a single-valued feature of an owner,
 * i.e., it is equivalent of the call
 * <pre>
 *   ((EObject)object).eSet((EStructuralFeature)feature, value);
 * </pre>
 * or to 
 * <pre>
 *   ((EObject)object).eUnset((EStructuralFeature)feature);
 * </pre>
 * if the value is {@link #UNSET_VALUE}.
 * <p>
 * Setting a feature that is a bidirectional reference with a multiplicity-many reverse or with a
 * multiplicity-1 reverse that is already set (on value), is not undoable.
 * In this case, the SetCommand static create function will not return an instance of this class, but
 * instead will return a compound command (e.g., a {@link RemoveCommand} followed by an {@link AddCommand}
 * for the other end of the relation) which could not be undone.
 * <p>
 * The exception to the above is when an empty list is being set to empty or unset. Such commands are undoable
 * and represent the only way to toggle whether the feature is set.
 * <p>
 * When setting a containment (or container) feature, we always assume that the object that will be
 * contained is not already in a container, but take no action in this class to ensure this is the case. 
 * <p>
 * A set command is an {@link OverrideableCommand}.
 */
public class SetCommand extends AbstractOverrideableCommand
{
  /**
   * Specify this as the value in order to unset a feature.  Note that this value can be specified for
   * a multiplicity-1 feature or for a multiplicity-many feature with no index given.  Unsetting a single
   * value within a list is not possible. 
   */
  public static final Object UNSET_VALUE = new Object();
  
  /**
   * This creates a command to set the owner's feature to the specified value.
   */
  public static Command create(EditingDomain domain, final Object owner, Object feature, Object value) 
  {
    return create(domain, owner, feature, value, CommandParameter.NO_INDEX);
  }

  /**
   * This creates a command to set the owner's feature to the specified value at the specified index.
   */
  public static Command create(EditingDomain domain, final Object owner, Object feature, Object value, int index) 
  {
    // If the feature is a bidirectional reference with a multiplicity-many reverse, a composite reverse,
    // or a multiplicity-1 reverse that is already set (on value), then we'll switch things around and 
    // execute this command a little differently, because otherwise it's not undoable.
    //
    if (owner instanceof EObject && ((EObject)owner).eClass().getEAllReferences().contains(feature))
    {
      EReference eReference = (EReference)feature;
      if (eReference.isMany() && index == CommandParameter.NO_INDEX)
      {
        // We never directly set a multiplicity-many feature to a list directly.  Instead, we remove the old values
        // values, move the values that remain, and insert the new values.  If all old values are removed, we'll still
        // set it to an empty list, or unset it, as appropriate. 
        //
        List<?> values = value == UNSET_VALUE ? Collections.EMPTY_LIST : (List<?>)value;
        List<?> oldValues = (List<?>)((EObject)owner).eGet(eReference);

        // If we're unsetting the list or setting to be empty, and it is currently not empty, we'll need a strict
        // compound command so that the list is empty when the final set command tests for executability. 
        //
        CompoundCommand compound = null;
        if (!oldValues.isEmpty() && values.isEmpty())
        {
          compound =
            new PessimisticStrictCompoundCommand(LABEL, DESCRIPTION)
            {
              @Override
              public Collection<?> getAffectedObjects()
              {
                return Collections.singleton(owner);
              }         
            };
        }
        else
        {
          compound = 
            new CompoundCommand(CompoundCommand.LAST_COMMAND_ALL, LABEL, DESCRIPTION)
            {
              @Override
              public Collection<?> getAffectedObjects()
              {
                return Collections.singleton(owner);
              }
            };
        }

        if (!oldValues.isEmpty())
        {
          if (!values.isEmpty())
          {
            List<Object> removedValues = new BasicEList.FastCompare<Object>(oldValues);
            removedValues.removeAll(values);
            
            // If we aren't simply removing all the old values...
            //
            if (!removedValues.equals(oldValues))
            {
              // If there are values to remove, append a command for them.
              //
              if (!removedValues.isEmpty())
              {
                compound.append(RemoveCommand.create(domain, owner, feature, new BasicEList<Object>(removedValues)));
              }
              
              // Determine the values that will remain and move them into the right order, if necessary.
              //
              List<Object> remainingValues = new BasicEList.FastCompare<Object>(oldValues);
              remainingValues.removeAll(removedValues);
              int count = -1;
              for (Object object : values)
              {
                int position = remainingValues.indexOf(object);
                if (position != -1 && position != ++count)
                {
                  compound.append(MoveCommand.create(domain, owner, feature, object, count));
                }
              }
              
              // Determine the values to be added and add them at the right position.
              //
              List<Object> addedValues = new BasicEList.FastCompare<Object>(values);
              addedValues.removeAll(remainingValues);
              for (ListIterator<?> i = values.listIterator(); i.hasNext(); )
              {
                Object object = i.next();
                if (addedValues.contains(object))
                {
                  int addIndex = i.previousIndex();
                  if (addIndex > oldValues.size())
                  {
                    addIndex = -1;
                  }
                  compound.append(AddCommand.create(domain, owner, feature, object, addIndex));
                }
              }
              return compound;
            }
          }

          compound.append(RemoveCommand.create(domain, owner, feature, new BasicEList<Object>(oldValues)));
        }

        if (!values.isEmpty())
        {
          compound.append(AddCommand.create(domain, owner, feature, values));
        }
        else
        {
          compound.append(domain.createCommand(SetCommand.class, new CommandParameter(owner, feature, value)));
        }
        return compound;
      } // end setting whole list
      else if (eReference.getEOpposite() != null)
      {
        EReference eOtherEnd = eReference.getEOpposite();
        if (eOtherEnd.isMany())
        {
          if (eReference.isMany())
          {
            // For a many-to-many association, the command can only be undoable if the value or owner is last in its
            // respective list, since the undo will include an inverse add.  So, if the value is last, but the owner is
            // not, we create an undoable compound command that removes from the opposite end and then inserts the new
            // value.
            //
            EList<?> list = (EList<?>)((EObject)owner).eGet(eReference);
            if (index == list.size() - 1)
            {
              EObject oldValue = (EObject)list.get(index);
              EList<?> oppositeList = (EList<?>)oldValue.eGet(eOtherEnd);
              if (oppositeList.get(oppositeList.size() - 1) != owner)
              {
                CompoundCommand compound = 
                  new CompoundCommand(CompoundCommand.LAST_COMMAND_ALL, LABEL, DESCRIPTION)
                  {
                    @Override
                    public Collection<?> getAffectedObjects()
                    {
                      return Collections.singleton(owner);
                    }
                  };
                compound.append(RemoveCommand.create(domain, oldValue, eOtherEnd, owner));
                compound.append(AddCommand.create(domain, owner, feature, value));
                return compound;
              }
            }
          }
          else
          {
            // For a 1-to-many association, doing the set as a remove and add from the other end will make it undoable.
            // In particular, if there is an existing non-null value, we first need to remove it from the other end, so
            // that it will be reinserted at the correct index on undo. 
            //
            Object oldValue = ((EObject)owner).eGet(eReference);

            if (value == null || value == UNSET_VALUE) 
            {
              if (oldValue == null) 
              { // (value == null) && (oldValue == null)
                // A simple set/unset will suffice.
                //
                return domain.createCommand(SetCommand.class, new CommandParameter(owner, eReference, value));
              }
              else 
              { // (value == null) && (oldValue != null)
                // Remove owner from the old value and unset if necessary.
                //
                Command removeCommand = RemoveCommand.create(domain, oldValue, eOtherEnd, Collections.singleton(owner));

                if (value != UNSET_VALUE)
                {
                  return removeCommand;
                }
                else
                {
                  CompoundCommand compound = new PessimisticStrictCompoundCommand(LABEL, DESCRIPTION);
                  compound.append(removeCommand);
                  compound.append(domain.createCommand(SetCommand.class, new CommandParameter(owner, eReference, value)));
                  return compound;
                }
              }
            }
            else 
            { // ((value != null) 
              Command addCommand = 
                new CommandWrapper(AddCommand.create(domain, value, eOtherEnd, Collections.singleton(owner)))
                {
                  @Override
                  public Collection<?> getAffectedObjects()
                  {
                    return Collections.singleton(owner);
                  }
                };
  
              if (oldValue == null) 
              { // (value != null) && (oldValue == null)
                // Add owner to new value.
                //
                return addCommand;
              }
              else 
              { // ((value != null) && (oldValue != null))
                // Need a compound command to remove owner from old value and add it to new value.
                //
                CompoundCommand compound = new CompoundCommand(CompoundCommand.LAST_COMMAND_ALL, LABEL, DESCRIPTION);
                compound.append(RemoveCommand.create(domain, oldValue, eOtherEnd, Collections.singleton(owner)));
                compound.append(addCommand);
                return compound;
              }
            }
          }
        }
        else if (eOtherEnd.isContainment())
        {
          if (value != null && value != UNSET_VALUE)
          {
            // For consistency, we always set 1-1 container relations from the container end.
            //
            return 
              new CommandWrapper(SetCommand.create(domain, value, eOtherEnd, owner))
              {
                @Override
                public Collection<?> getResult()
                {
                  return Collections.singleton(owner);
                }
                @Override
                public Collection<?> getAffectedObjects()
                {
                  return Collections.singleton(owner);
                }
              };
          }
        }
        else
        {
          // For a many-to-1 or 1-to-1 association, if the opposite reference on the new value is already set to
          // something, we need a compound command that first explicitly removes that reference, so that it will be
          // restored in the undo.
          //
          if (value instanceof EObject && ((EObject)value).eGet(eOtherEnd) != null)
          {
            CompoundCommand compound = 
              new CompoundCommand(CompoundCommand.LAST_COMMAND_ALL)
              {
                @Override
                public boolean canUndo()
                {
                  return true;
                }
              };
            if (eReference.isMany())
            {
              // For a many-to-1, we use SetCommand.create() to create the command to remove the opposite reference;
              // a RemoveCommand on its opposite will actually result.
              //
              compound.append(SetCommand.create(domain, value, eOtherEnd, null));
            }
            else
            {
              // For a 1-to-1, we can directly create a SetCommand.
              //
              compound.append(domain.createCommand(SetCommand.class, new CommandParameter(value, eOtherEnd, null)));
            }
            compound.append(domain.createCommand(SetCommand.class, new CommandParameter(owner, eReference, value, index)));
            return compound;
          }
        }
      }
    }
    return domain.createCommand(SetCommand.class, new CommandParameter(owner, feature, value, index));
  }

  /**
   * This caches the label.
   */
  protected static final String LABEL = EMFEditPlugin.INSTANCE.getString("_UI_SetCommand_label");

  /**
   * This caches the description.
   */
  protected static final String DESCRIPTION = EMFEditPlugin.INSTANCE.getString("_UI_SetCommand_description");

  /**
   * This is the owner object upon which the command will act.
   */
  protected EObject owner;

  /**
   * This is the feature of the owner object upon the command will act.
   */
  protected EStructuralFeature feature;

  /**
   * If non-null, this is the list in which the command will set a value.
   * If null, feature is single-valued or no index was specified.
   */
  protected EList<Object> ownerList;

  /**
   * This is the value to be set.
   */
  protected Object value;

  /**
   * This is the old value of the feature which must be restored during undo.
   */
  protected Object oldValue;

  /**
   * This is the position at which the object will be set.
   */
  protected int index;

  /**
   * This specified whether or not this command can be undone.
   */
  protected boolean canUndo = true;

  /**
   * This constructs a primitive command to set the owner's feature to the specified value.
   */
  public SetCommand(EditingDomain domain, EObject owner, EStructuralFeature feature, Object value)  
  {
    super(domain, LABEL, DESCRIPTION);

    // Initialize all the fields from the command parameter.
    //
    this.owner = owner;
    this.feature = feature;
    this.value = value;
    this.index = CommandParameter.NO_INDEX;
  }

  /**
   * This constructs a primitive command to set the owner's feature to the specified value at the given index.
   */
  public SetCommand(EditingDomain domain, EObject owner, EStructuralFeature feature, Object value, int index)  
  {
    super(domain, LABEL, DESCRIPTION);

    // Initialize all the fields from the command parameter.
    //
    this.owner = owner;
    this.feature = feature;
    this.value = value;
    this.index = index;

    if (index != CommandParameter.NO_INDEX)
    {
      ownerList = getOwnerList(owner, feature);
    }
  }

  /**
   * This returns the owner object upon which the command will act.
   */
  public EObject getOwner()
  {
    return owner;
  }

  /**
   * This returns the feature of the owner object upon the command will act.
   */
  public EStructuralFeature getFeature()
  {
    return feature;
  }

  /**
   * If the command will set a single value in a list, this returns the list in which it will set; null otherwise.
   */
  public EList<Object> getOwnerList()
  {
    return ownerList;
  }

  /**
   * This returns the position at which the objects will be added.
   */
  public int getIndex()
  {
    return index;
  }

  /**
   * This returns the value to be set.
   */
  public Object getValue()
  {
    return value;
  }

  /**
   * This returns the old value of the feature which must be restored during undo.
   */
  public Object getOldValue()
  {
    return oldValue;
  }

  protected static final EcorePackage ecorePackage = EcorePackage.eINSTANCE;

  @Override
  protected boolean prepare() 
  {
    boolean result = false;

    // If there is an owner.
    //
    if (owner != null)
    {
      if (domain.isReadOnly(owner.eResource()))
      {
        return false;
      }

      // Get the owner's meta object.
      //
      EClass eMetaObject = owner.eClass();

      // Is the feature an attribute of the owner...
      //
      if (eMetaObject.getEAllAttributes().contains(feature)) 
      {
        // If must be of this type then.
        //
        EAttribute eAttribute = (EAttribute)feature;
        EClassifier eType = eAttribute.getEType();

        if (ownerList != null)
        {
          // Setting at an index. Make sure the index is valid, the type is valid, and the value isn't already in a
          // unique feature. Record the old value.
          //
          if (index >= 0 && index < ownerList.size() && eType.isInstance(value) &&
              (!eAttribute.isUnique() || !ownerList.contains(value)))
          {
            oldValue = ownerList.get(index);
            result = true;
          }
        }
        else if (eAttribute.isMany())
        {
          // If the attribute is set, record it's old value.
          //
          if (owner.eIsSet(eAttribute))
          {
            oldValue = new BasicEList<Object>((EList<?>)owner.eGet(feature));
          }
          else
          {
            oldValue = UNSET_VALUE;
          }

          if (value == UNSET_VALUE)
          {
            result = true;
          }
          else if (value instanceof Collection)
          {
            Collection<?> collection = (Collection<?>)value;
            result = true;
            for (Object object : collection)
            {
              if (!eType.isInstance(object))
              {
                result = false;
                break;
              }
            }
          }
        }
        else
        {
          // If the attribute is set, record it's old value.
          //
          if (owner.eIsSet(eAttribute))
          {
            oldValue = owner.eGet(feature);
          }
          else
          {
            oldValue = UNSET_VALUE;
          }

          result = value == null || value == UNSET_VALUE || eType.isInstance(value);
        }
      }
      // Is the feature an reference of the owner...
      //
      else if (eMetaObject.getEAllReferences().contains(feature)) 
      {
        // It must be of this type.
        //
        EReference eReference = (EReference)feature;

        // Make sure we're only setting a single value -- either a value at a specified index in a multi-valued
        // reference or just a single-valued reference is allowed.  Setting a whole multi-valued feature is not. 
        //
        if (ownerList != null)
        {
          if (index >= 0 && index < ownerList.size() && eReference.getEType().isInstance(value) &&
              (!eReference.isUnique() || !ownerList.contains(value)))
          {
            oldValue = ownerList.get(index);
            result = true;
          }
        }
        else if (eReference.isMany())
        {
          // If the list is unset or set to empty, and we can set it to empty or unset it.
          //
          if (value == UNSET_VALUE || (value instanceof Collection && ((List<?>)value).isEmpty()))
          {
            if (!owner.eIsSet(eReference))
            {
              result = true;
              oldValue = UNSET_VALUE;
            }
            else if (((EList<?>)owner.eGet(feature)).isEmpty())
            {
              result = true;
              oldValue = Collections.EMPTY_LIST;
            }
          }
        }
        else
        {
          if (owner.eIsSet(feature))
          {
            oldValue = owner.eGet(feature);
          }
          else
          {
            oldValue = UNSET_VALUE; 
          }

          if (value == null || value == UNSET_VALUE || eReference.getEType().isInstance(value))
          {
            result = true;
          }
        }

        // Make sure the container is not being put into a contained object.
        //
        if (result && eReference.isContainment())
        {
          for (EObject container = owner; container != null; container = container.eContainer())
          {
            if (value == container)
            {
              result = false;
              break;
            }
          }
        }

        // Check whether the command is undoable.
        //
        if (result && eReference.getEOpposite() != null && oldValue instanceof EObject)
        {
          EReference eOtherEnd = eReference.getEOpposite();
          if (eOtherEnd.isMany())
          {
            // If there is an existing value, the opposite reference is multi-valued, and the owner is not last in that
            // opposite reference, then executing the set will remove the owner from its position in the list, and
            // undoing would add it back at the end, failing to preserve the order.
            //
            if (oldValue != null)
            {
              EList<?> oppositeList = (EList<?>)((EObject)oldValue).eGet(eOtherEnd);
              canUndo = oppositeList.get(oppositeList.size() - 1) == owner;
            }
          }
          else
          {
            // If the new value is non-null and the opposite reference is single-valued, then the set will clear that
            // opposite reference, and undoing would not restore it.
            //
            canUndo = (value == null || value == UNSET_VALUE || ((EObject)value).eGet(eOtherEnd) == null);
          }

        }
      }
    }

    return result;
  }

  @Override
  public void doExecute() 
  {
    // Either set or unset the feature.
    //
    if (ownerList != null)
    {
      ownerList.set(index, value);
    }
    else if (value == UNSET_VALUE)
    {
      owner.eUnset(feature);
    }
    else
    {
      owner.eSet(feature, value);
    }
  }

  @Override
  public boolean doCanUndo()
  {
    return canUndo;
  }

  @Override
  public void doUndo() 
  {
    // Either set or unset the old value.
    //
    if (ownerList != null)
    {
      ownerList.set(index, oldValue);
    }
    else if (oldValue == UNSET_VALUE)
    {
      owner.eUnset(feature);
    }
    else
    {
      owner.eSet(feature, oldValue);
    }
  }

  @Override
  public void doRedo() 
  {
    // Either set or unset the feature.
    //
    if (ownerList != null)
    {
      ownerList.set(index, value);
    }
    else if (value == UNSET_VALUE)
    {
      owner.eUnset(feature);
    }
    else
    {
      owner.eSet(feature, value);
    }
  }

  @Override
  public Collection<?> doGetResult()
  {
    return Collections.singleton(owner);
  }

  @Override
  public Collection<?> doGetAffectedObjects()
  {
    return Collections.singleton(owner);
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
    if (ownerList != null)
    {
      result.append(" (ownerList: " + ownerList + ")");
      result.append(" (index: " + index + ")");
    }
    result.append(" (value: " + value + ")");
    result.append(" (oldValue: " + oldValue + ")");

    return result.toString();
  }

  protected static class PessimisticStrictCompoundCommand extends StrictCompoundCommand
  {
    public PessimisticStrictCompoundCommand(String label, String description)
    {
      super(label, description);
      isPessimistic = true;
    }
  }
}
