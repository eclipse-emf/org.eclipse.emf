/**
 * <copyright> 
 *
 * Copyright (c) 2005-2006 IBM Corporation and others.
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
 * $Id: DeleteCommand.java,v 1.4 2006/05/29 13:56:33 emerks Exp $
 */
package org.eclipse.emf.edit.command;


import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.Map;

import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.command.CompoundCommand;
import org.eclipse.emf.common.util.UniqueEList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.util.EcoreUtil;
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
  public static Command create(EditingDomain domain, final Collection collection)
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
  public DeleteCommand(EditingDomain domain, Collection collection)
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
  protected Collection collection;

  /**
   * This returns the collection of objects to be deleted.
   */
  public Collection getCollection()
  {
    return collection;
  }

  protected boolean prepare()
  {
    append(RemoveCommand.create(domain, collection));
    return super.prepare();
  }

  public void execute()
  {
    Collection eObjects = new UniqueEList();
    for (Iterator i = collection.iterator(); i.hasNext(); )
    {
      Object object = AdapterFactoryEditingDomain.unwrap(i.next());
      if (object instanceof EObject)
      {
        eObjects.add(object);
        for (Iterator j = ((EObject)object).eAllContents(); j.hasNext(); )
        {
          eObjects.add(j.next());
        }
      }
      else if (object instanceof Resource)
      {
        for (Iterator j = ((Resource)object).getAllContents(); j.hasNext(); )
        {
          eObjects.add(j.next());
        }
      }
    }
    
    Map usages = EcoreUtil.UsageCrossReferencer.findAll(eObjects, domain.getResourceSet());
    
    super.execute();

    for (Iterator i = usages.entrySet().iterator(); i.hasNext(); )
    {
      Map.Entry entry = (Map.Entry)i.next();
      EObject eObject = (EObject)entry.getKey();
      Collection settings = (Collection)entry.getValue();
      for (Iterator j = settings.iterator(); j.hasNext(); )
      {
        EStructuralFeature.Setting setting = (EStructuralFeature.Setting)j.next();
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
              appendAndExecute(SetCommand.create(domain, referencingEObject, eStructuralFeature, null));
            }
          }
        }
      }
    }
  }
}
