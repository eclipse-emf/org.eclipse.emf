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
 * $Id: EditingDomain.java,v 1.1 2010/04/28 14:48:43 emerks Exp $
 */
package org.eclipse.emf.edit.domain;


import java.util.Collection;
import java.util.List;

import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.command.CommandStack;
import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.edit.command.CommandParameter;
import org.eclipse.emf.edit.command.OverrideableCommand;


/**
 * An editing domain manages a self-contained set of interrelated EMF models and the {@link Command}s that modify them.
 * The models are maintained in the form of a {@link ResourceSet}.
 * Commands that modify the model are typically created through the domain and are executed using the {@link CommandStack}.
 * An optional feature of an editing domain, which is used to implement mapping domains,
 * is the ability to override primitive commands, see {@link OverrideableCommand}.
 * 
 * <p>
 * The domain imposes a hierarchical structure upon the models 
 * via the results of the {@link #getChildren getChildren} and {@link #getParent getParent} methods.
 * This is useful for implementing commands such as 
 * {@link org.eclipse.emf.edit.command.RemoveCommand}, which often needs to deduce the parent from which to remove a particular object,
 * and {@link org.eclipse.emf.edit.command.CopyCommand}, which often needs to deduce all the children to copy recursively.
 * This also meshes well with user interfaces, which often present a model hierarchically, i.e., as a tree.
 */
public interface EditingDomain
{
  /**
   * This creates the specified resource in this editing domain's resource set.
   */
  Resource createResource(String fileNameURI);

  /**
   * This loads the specified resource into this editing domain's resource set.
   */
  Resource loadResource(String fileNameURI);

  /** 
   * This returns the resource set within which all the created and loaded resources reside.
   */
  ResourceSet getResourceSet();

  /**
   * This creates a command of the type of the specified by the command class 
   * and acting upon the information specified in the given command parameter.
   */
  Command createCommand(Class<? extends Command> commandClass, CommandParameter commandParameter);

  /**
   * This creates an override for the given command.
   */
  Command createOverrideCommand(OverrideableCommand command);

  /**
   * This returns a command queue for executing commands.
   */
  CommandStack getCommandStack();

  /**
   * This returns the children of the object.
   */
  Collection<?> getChildren(Object object);

  /**
   * This returns the parent of the object.
   */
  Object getParent(Object object);

  /**
   * This returns the root of the object, i.e., .
   */
  Object getRoot(Object object);

  /**
   * This returns a collection of objects describing the different children
   * that can be added under the specified object.  If a sibling is
   * specified (non-null), the children should be as close to immediately
   * following that sibling as possible.
   */
  Collection<?> getNewChildDescriptors(Object object, Object sibling);

  /**
   * This returns a tree iterator that will yield the object, the children of the object, their children, and so on.
   */
  TreeIterator<?> treeIterator(Object object);

  /**
   * This returns a path from the root object to the given object in the tree.
   */
  List<?> getTreePath(Object object);


  /**
   * This returns the clipboard of the editing domain.
   */
  Collection<Object> getClipboard();

  /**
   * This sets the clipboard of the editing domain.
   */
  void setClipboard(Collection<Object> clipboard);

  /**
   * This returns whether or not copy command optimizations are safe in this domain.
   */
  boolean getOptimizeCopy();

  /**
   * This returns whether the resource is read only in editing domain.
   */
  boolean isReadOnly(Resource resource);

  /**
   * This returns whether or not the domain allows the given object to be moved to
   * a different resource from its container.
   */
  boolean isControllable(Object object);
}
