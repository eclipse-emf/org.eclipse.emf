/**
 * <copyright> 
 *
 * Copyright (c) 2005-2009 IBM Corporation and others.
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
 * $Id: DeleteCommand.java,v 1.9 2009/07/30 18:16:00 davidms Exp $
 */
package org.eclipse.emf.edit.command;


import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Map;

import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.command.CompoundCommand;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.util.EcoreUtil.UsageCrossReferencer;
import org.eclipse.emf.edit.EMFEditPlugin;
import org.eclipse.emf.edit.domain.AdapterFactoryEditingDomain;
import org.eclipse.emf.edit.domain.EditingDomain;


/**
 * This uses one or more {@link RemoveCommand}s to remove an object from its parent container and to delete all other
 * references to it from within the editing domain.
 * @since 2.2
 */
public class DeleteCommand extends CompoundCommand
{
  /**
   * This creates a command that deletes the given object.
   */
  public static Command create(EditingDomain domain, Object object) 
  {
    return create(domain, Collections.singleton(object));
  }

  /**
   * This creates a command that deletes the objects in the given collection.
   */
  public static Command create(EditingDomain domain, final Collection<?> collection)
  {
    return domain.createCommand(DeleteCommand.class, new CommandParameter(null, null, collection));
  }

  /**
   * This caches the label.
   */
  protected static final String LABEL = EMFEditPlugin.INSTANCE.getString("_UI_DeleteCommand_label");

  /**
   * This caches the description.
   */
  protected static final String DESCRIPTION = EMFEditPlugin.INSTANCE.getString("_UI_DeleteCommand_description");

  /**
   * This constructs a command that deletes the objects in the given collection.
   */
  public DeleteCommand(EditingDomain domain, Collection<?> collection)
  {
    super(0, LABEL, DESCRIPTION);
    this.domain = domain;
    this.collection = collection;
  }

  /**
   * This is the editing doman in which this command operates.
   */
  protected EditingDomain domain;

  /**
   * This is the collection of objects to be deleted.
   */
  protected Collection<?> collection;

  /**
   * This returns the collection of objects to be deleted.
   */
  public Collection<?> getCollection()
  {
    return collection;
  }

  @Override
  protected boolean prepare()
  {
    prepareCommand();
    return super.prepare();
  }
  
  protected void prepareCommand()
  {
    append(RemoveCommand.create(domain, collection));
  }

  @Override
  public void execute()
  {
    Collection<EObject> eObjects = new LinkedHashSet<EObject>();
    for (Object wrappedObject : collection)
    {
      Object object = AdapterFactoryEditingDomain.unwrap(wrappedObject);
      if (object instanceof EObject)
      {
        eObjects.add((EObject)object);
        for (Iterator<EObject> j = ((EObject)object).eAllContents(); j.hasNext(); )
        {
          eObjects.add(j.next());
        }
      }
      else if (object instanceof Resource)
      {
        for (Iterator<EObject> j = ((Resource)object).getAllContents(); j.hasNext(); )
        {
          eObjects.add(j.next());
        }
      }
    }
    
    Map<EObject, Collection<EStructuralFeature.Setting>> usages = findReferences(eObjects);
    
    super.execute();

    for (Map.Entry<EObject, Collection<EStructuralFeature.Setting>> entry : usages.entrySet())
    {
      EObject eObject = entry.getKey();
      Collection<EStructuralFeature.Setting> settings = entry.getValue();
      for (EStructuralFeature.Setting setting : settings)
      {
        EObject referencingEObject = setting.getEObject();
        if (!eObjects.contains(referencingEObject))
        {
          EStructuralFeature eStructuralFeature = setting.getEStructuralFeature();
          if (eStructuralFeature.isChangeable())
          {
            if (eStructuralFeature.isMany())
            {
              appendAndExecute(RemoveCommand.create(domain, referencingEObject, eStructuralFeature, eObject));
            }
            else
            {
              appendAndExecute(SetCommand.create(domain, referencingEObject, eStructuralFeature, SetCommand.UNSET_VALUE));
            }
          }
        }
      }
    }
  }

  /**
   * Returns the references to the objects in the given collection that are to be cleared.
   * The default implementation uses {@link UsageCrossReferencer} to find all incoming cross references.
   * @see UsageCrossReferencer
   * @since 2.6
   */
  protected Map<EObject, Collection<EStructuralFeature.Setting>> findReferences(Collection<EObject> eObjects)
  {
    return EcoreUtil.UsageCrossReferencer.findAll(eObjects, domain.getResourceSet());
  }
}
