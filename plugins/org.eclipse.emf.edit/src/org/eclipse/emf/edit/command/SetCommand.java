/**
 * <copyright> 
 *
 * Copyright (c) 2002-2004 IBM Corporation and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Common Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/cpl-v10.html
 * 
 * Contributors: 
 *   IBM - Initial API and implementation
 *
 * </copyright>
 *
 * $Id: SetCommand.java,v 1.1 2004/03/06 17:31:32 marcelop Exp $
 */
package org.eclipse.emf.edit.command;


import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.command.CommandWrapper;
import org.eclipse.emf.common.command.CompoundCommand;
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
 * The set command logically acts upon an owner object to set a particular feature to a specified value.
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
 * if the value is null and the feature is an attribute.
 * <p>
 * Setting a feature that is a bi-directional reference with a multiplicity-many reverse or with a
 * multiplicity-1 reverse that is already set (on value), is not undoable.
 * In this case, the SetCommand static create function will not return an instance of this class, but
 * instead will return a compound command (e.g., a {@link RemoveCommand} followed by an {@link AddCommand}
 * for the other end of the relation) which can be undone.
 * <p>
 * A set command is an {@link OverrideableCommand}.
 */
public class SetCommand extends AbstractOverrideableCommand
{
  /**
   * This creates a command to set the owner's feature to the specified value.
   */
  public static Command create(EditingDomain domain, final Object owner, Object feature, Object value) 
  {
    // If the feature is a bi-directional reference with a multiplicity-many reverse, a composite reverse,
    // or a multiplicity-1 reverse that is already set (on value), then we'll switch things around and 
    // execute this command a little differently, because otherwise it's not undoable.
    //
    if (owner instanceof EObject && ((EObject)owner).eClass().getEAllReferences().contains(feature))
    {
      EReference eReference = (EReference)feature;
      if (eReference.isMany())
      {
        CompoundCommand compound = 
          new CompoundCommand(CompoundCommand.LAST_COMMAND_ALL, LABEL, DESCRIPTION)
          {
            public Collection getAffectedObjects()
            {
              return Collections.singleton(owner);
            }
          };

        List oldValues = (List)((EObject)owner).eGet(eReference);
        if (!oldValues.isEmpty())
        {
          compound.append(RemoveCommand.create(domain, owner, feature, new BasicEList(oldValues)));
        }
        List values = (List)value;
        if (!values.isEmpty())
        {
          compound.append(AddCommand.create(domain, owner, feature, values));
        }
        return compound;
      }
      else if (eReference.getEOpposite() != null)
      {
        EReference eOtherEnd = eReference.getEOpposite();
        if (eOtherEnd.isMany())
        {
          Object oldValue = ((EObject)owner).eGet(eReference);
          if (value == null) 
          {
            if (oldValue == null) 
            { // (value == null) && (oldValue == null)
              return domain.createCommand(SetCommand.class, new CommandParameter(owner, eReference, value));
            }
            else 
            {  // (value == null) && (oldValue != null)
              return RemoveCommand.create(domain, oldValue, eOtherEnd, Collections.singleton(owner));
            }
          }
          else 
          { // ((value != null) 
            Command addCommand = 
              new CommandWrapper(AddCommand.create(domain, value, eOtherEnd, Collections.singleton(owner)))
              {
                public Collection getAffectedObjects()
                {
                  return Collections.singleton(owner);
                }
              };

            if (oldValue == null) 
            { // (value != null) && (oldValue == null)
              return addCommand;
            }
            else 
            { // ((value != null) && (oldValue != null))
              CompoundCommand compound = new CompoundCommand(CompoundCommand.LAST_COMMAND_ALL, LABEL, DESCRIPTION);
              compound.append(RemoveCommand.create(domain, oldValue, eOtherEnd, Collections.singleton(owner)));
              compound.append(addCommand);
              return compound;
            }
          }
        }
        else if (eOtherEnd.isContainment())
        {
          if (value != null)
          {
            // For consistency, we always set container relations from the container end.
            //
            return 
              new CommandWrapper(SetCommand.create(domain, value, eOtherEnd, owner))
              {
                public Collection getResult()
                {
                  return Collections.singleton(owner);
                }
                public Collection getAffectedObjects()
                {
                  return Collections.singleton(owner);
                }
              };
          }
        }
        else
        {
          if (value instanceof EObject && ((EObject)value).eGet(eOtherEnd) != null)
          {
            CompoundCommand compound = 
              new CompoundCommand(CompoundCommand.LAST_COMMAND_ALL)
              {
                public boolean canUndo()
                {
                  return true;
                }
              };
            compound.append(domain.createCommand(SetCommand.class, new CommandParameter(value, eOtherEnd, null)));
            compound.append(domain.createCommand(SetCommand.class, new CommandParameter(owner, eReference, value)));
            return compound;
          }
        }
      }
    }
    return domain.createCommand(SetCommand.class, new CommandParameter(owner, feature, value));
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
   * This is the value of to be set.
   */
  protected Object value;

  /**
   * This is the old value of the feature which must be restored during undo.
   */
  protected Object oldValue;

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
   * This returns the value of to be set.
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

  protected boolean prepare() 
  {
    boolean result = false;

    // If there is an owner.
    //
    if (owner != null)
    {
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

        if (eAttribute.isMany())
        {
          oldValue = new BasicEList((EList)owner.eGet(feature));

          if (value == null)
          {
            result = true;
          }
          else if (value instanceof EList)
          {
            EList list = (EList)value;
            result = true;
            for (Iterator objects = list.iterator(); objects.hasNext(); )
            {
              if (!eType.isInstance(objects.next()))
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

          result = value == null || eType.isInstance(value);
        }
      }
      // Is the feature an reference of the owner...
      //
      else if (eMetaObject.getEAllReferences().contains(feature)) 
      {
        // It must be of this type.
        //
        EReference eReference = (EReference)feature;

        // Make sure it's a single-valued relation (multi-valued is not supported).
        //
        if (!eReference.isMany())
        {
          // This just turns into the regular case, just like setting a String or Integer.
          //
          oldValue = owner.eGet(feature);

          // We want to make sure the object is of a type compatible with the type of the reference.
          //
          if (value == null || eReference.getEType().isInstance(value))
          {
            result = true;

            // Check to see if the container is being put into a contained object
            //
            if (eReference.isContainment())
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

            if (result)
            {
              // Setting a bi-direction relation may not be undoable.
              //
              if (eReference.getEOpposite() != null)
              {
                EReference eOtherEnd = eReference.getEOpposite();
                if (eOtherEnd.isMany())
                {
                  canUndo = (oldValue == null);
                }
                else
                {
                  canUndo = (value == null || ((EObject)value).eGet(eOtherEnd) == null);
                }
              }
            }
          }
        }
      }
    }

    return result;
  }

  public void doExecute() 
  {
    // Either set or unset the feature.
    //
    if (value == null)
    {
      owner.eUnset(feature);
    }
    else
    {
      owner.eSet(feature, value);
    }
  }

  public boolean doCanUndo()
  {
    return canUndo;
  }

  public void doUndo() 
  {
    // Either set or unset the old value.
    //
    if (oldValue == null)
    {
      owner.eUnset(feature);
    }
    else
    {
      owner.eSet(feature, oldValue);
    }
  }

  public void doRedo() 
  {
    // Either set or unset the old value.
    //
    if (value == null)
    {
      owner.eUnset(feature);
    }
    else
    {
      owner.eSet(feature, value);
    }
  }

  public Collection doGetResult()
  {
    return Collections.singleton(owner);
  }

  public Collection doGetAffectedObjects()
  {
    return Collections.singleton(owner);
  }

  /**
   * This gives an abbreviated name using this object's own class' name, without package qualification,
   * followed by a space separated list of <tt>field:value</tt> pairs.
   */
  public String toString()
  {
    StringBuffer result = new StringBuffer(super.toString());
    result.append(" (owner: " + owner + ")");
    result.append(" (feature: " + feature + ")");
    result.append(" (value: " + value + ")");
    result.append(" (oldValue: " + oldValue + ")");

    return result.toString();
  }
}
