/**
 * Copyright (c) 2002-2010 IBM Corporation and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: 
 *   IBM - Initial API and implementation
 */
package org.eclipse.emf.edit.provider;


import java.util.Collection;

import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.edit.command.CommandParameter;
import org.eclipse.emf.edit.domain.EditingDomain;


/**
 * This is the interface used by {@link EditingDomain} 
 * to impose a hierarchical relation on the model objects,
 * and to map operations on those objects onto an underlying EMF model {@link Command}s.
 * See {@link EditingDomain} for more details about how this is used.
 */
public interface IEditingDomainItemProvider
{
  /**
   * This does the same thing as {@link org.eclipse.emf.edit.domain.EditingDomain#getChildren EditingDomain.getChildren},
   * i.e., it imposes a hierarchical relation on a domain's model objects.
   */
  public Collection<?> getChildren(Object object);

  /**
   * This does the same thing as {@link org.eclipse.emf.edit.domain.EditingDomain#getParent EditingDomain.getParent},
   * i.e., it imposes a hierarchical relation on a domain's model objects.
   */
  public Object getParent(Object object);

  /**
   * This does the same thing as {@link
   * org.eclipse.emf.edit.domain.EditingDomain#getNewChildDescriptors
   * EditingDomain.getNewChildDescriptors}, i.e., it returns a collection of
   * objects describing the children that can be added under an object in
   * the editing domain.
   */
  public Collection<?> getNewChildDescriptors(Object object, EditingDomain editingDomain, Object sibling);

  /**
   * This does the same thing as {@link org.eclipse.emf.edit.domain.EditingDomain#createCommand EditingDomain.createCommand},
   * i.e., it creates commands for a domain's model objects.
   */
  public Command createCommand(Object object, EditingDomain editingDomain, Class<? extends Command> commandClass, CommandParameter commandParameter);
}
