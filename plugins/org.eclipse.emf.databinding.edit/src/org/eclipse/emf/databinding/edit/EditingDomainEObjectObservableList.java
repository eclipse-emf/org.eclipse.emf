/**
 * <copyright>
 *
 * Copyright (c) 2007 IBM Corporation and others.
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
 * $Id: EditingDomainEObjectObservableList.java,v 1.3 2010/02/04 20:56:05 emerks Exp $
 */
package org.eclipse.emf.databinding.edit;

import java.util.ArrayList;
import java.util.Collection;

import org.eclipse.core.databinding.observable.Realm;
import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.databinding.EObjectObservableList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.edit.command.AddCommand;
import org.eclipse.emf.edit.command.MoveCommand;
import org.eclipse.emf.edit.command.RemoveCommand;
import org.eclipse.emf.edit.command.SetCommand;
import org.eclipse.emf.edit.domain.EditingDomain;


/**
 * <p><b>PROVISIONAL:</b> This API is subject to arbitrary change, including renaming or removal.</p>
 */
public class EditingDomainEObjectObservableList extends EObjectObservableList
{
  /**
   * The editing domain
   */
  protected EditingDomain domain;

  /**
   * Observe a feature of the instance using the default realm
   * @param domain
   *            the editing domain
   * @param eObject
   *            the object
   * @param eStructuralFeature
   *            the feature
   */
  public EditingDomainEObjectObservableList(EditingDomain domain, EObject eObject, EStructuralFeature eStructuralFeature)
  {
    this(Realm.getDefault(), domain, eObject, eStructuralFeature);
  }

  /**
   * Observe a feature of the instance using the realm
   * @param domain
   *            the editing domain
   * @param realm
   * 
   * @param eObject
   *            the object
   * @param eStructuralFeature
   *            the feature
   */
  public EditingDomainEObjectObservableList(Realm realm, EditingDomain domain, EObject eObject, EStructuralFeature eStructuralFeature)
  {
    super(realm, eObject, eStructuralFeature);
    this.domain = domain;
  }

  @Override
  public synchronized void dispose()
  {
    domain = null;
    super.dispose();
  }

  /**
   * Execute a command
   * @param command the command to execute
   * @return <code>true</code> if execute else <code>false</code>
   */
  protected boolean execute(Command command)
  {
    if (command.canExecute())
    {
      domain.getCommandStack().execute(command);
      return true;
    }
    else
    {
      return false;
    }
  }

  @Override
  public boolean add(Object object)
  {
    checkRealm();
    return execute(AddCommand.create(domain, eObject, eStructuralFeature, object));
  }

  @Override
  public void add(int index, Object object)
  {
    checkRealm();
    execute(AddCommand.create(domain, eObject, eStructuralFeature, object, index));
  }

  @SuppressWarnings("rawtypes")
  @Override
  public boolean addAll(Collection collection)
  {
    checkRealm();
    return execute(AddCommand.create(domain, eObject, eStructuralFeature, collection));
  }

  @SuppressWarnings("rawtypes")
  @Override
  public boolean addAll(int index, Collection collection)
  {
    checkRealm();
    return execute(AddCommand.create(domain, eObject, eStructuralFeature, collection, index));
  }

  @Override
  public Object set(int index, Object element)
  {
    checkRealm();
    Object oldElement = wrappedList.get(index);
    execute(SetCommand.create(domain, eObject, eStructuralFeature, element, index));
    return oldElement;
  }

  @Override
  public Object remove(int index)
  {
    checkRealm();
    Object element = wrappedList.get(index);
    execute(RemoveCommand.create(domain, eObject, eStructuralFeature, element));
    return element;
  }

  @Override
  public boolean remove(Object element)
  {
    checkRealm();
    return execute(RemoveCommand.create(domain, eObject, eStructuralFeature, element));
  }

  @SuppressWarnings("rawtypes")
  @Override
  public boolean removeAll(Collection collection)
  {
    checkRealm();
    return execute(RemoveCommand.create(domain, eObject, eStructuralFeature, collection));
  }

  @SuppressWarnings("rawtypes")
  @Override
  public boolean retainAll(Collection collection)
  {
    checkRealm();
    Collection<Object> objectsToRemove = new ArrayList<Object>(wrappedList());
    objectsToRemove.removeAll(collection);
    return execute(RemoveCommand.create(domain, eObject, eStructuralFeature, objectsToRemove));
  }

  @Override
  public void clear()
  {
    checkRealm();
    execute(RemoveCommand.create(domain, eObject, eStructuralFeature, new ArrayList<Object>(wrappedList())));
  }

  @Override
  public Object move(int newPosition, int oldPosition)
  {
    Object result = wrappedList.get(oldPosition);
    move(newPosition, result);
    return result;
  }

  @Override
  public void move(int newPosition, Object object)
  {
    checkRealm();
    execute(MoveCommand.create(domain, eObject, eStructuralFeature, object, newPosition));
  }
}