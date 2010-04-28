/**
 * <copyright> 
 *
 * Copyright (c) 2002-2010 IBM Corporation and others.
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
 * $Id: CreateCopyCommand.java,v 1.2 2010/04/28 20:38:34 khussey Exp $
 */
package org.eclipse.emf.edit.command;


import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;

import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EFactory;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.edit.EMFEditPlugin;
import org.eclipse.emf.edit.domain.EditingDomain;


/**
 * The create copy command is used to create an uninitialized object of the same type
 * as owner which will later be initialized using {@link InitializeCopyCommand}.
 *
 * <p>
 * A create copy command is an {@link OverrideableCommand}.
 */
public class CreateCopyCommand extends AbstractOverrideableCommand implements ChildrenToCopyProvider
{
  /**
   * This creates a command that will create and object for copying the given object
   */
  public static Command create(EditingDomain domain, Object owner, CopyCommand.Helper copyHelper) 
  {
    return domain.createCommand(CreateCopyCommand.class, new CommandParameter(owner, null, copyHelper));
  }

  /**
   * This caches the label.
   */
  protected static final String LABEL = EMFEditPlugin.INSTANCE.getString("_UI_CreateCopyCommand_label");

  /**
   * This caches the description.
   */
  protected static final String DESCRIPTION = EMFEditPlugin.INSTANCE.getString("_UI_CreateCopyCommand_description");

  /**
   * This is the object being copied.
   */
  protected EObject owner;

  /**
   * This is the copy.
   */
  protected EObject copy;

  /**
   * This is a map of objects to their copies
   */
  protected CopyCommand.Helper copyHelper;

  /**
   * This constructs a command that will create an object that is a copy of the given object.
   */
  public CreateCopyCommand(EditingDomain domain, EObject owner, CopyCommand.Helper copyHelper)
  {
    super(domain, LABEL, DESCRIPTION);

    this.owner = owner;
    this.copyHelper = copyHelper;
  }

  /**
   * This is the object being copied.
   */
  public EObject getOwner()
  {
    return owner;
  }

  /**
   * This is the map of objects to their copies.
   */
  public CopyCommand.Helper getCopyHelper()
  {
    return copyHelper;
  }

  @Override
  protected boolean prepare()
  {
    return true;
  }

  @Override
  public void doExecute() 
  {
    // Create the copy
    //
    EClass metaObject = owner.eClass();
    EPackage ePackage = metaObject.getEPackage();
    EFactory eFactory = ePackage.getEFactoryInstance();
    copy = eFactory.create(metaObject);
    copyHelper.put(owner, copy);
  }

  @Override
  public void doUndo() 
  {
    copyHelper.remove(owner);
  }

  @Override
  public void doRedo()
  {
    copyHelper.put(owner, copy);
  }

  @Override
  public Collection<?> doGetResult()
  {
    return Collections.singleton(copy);
  }

  @Override
  public Collection<?> doGetChildrenToCopy()
  {
    // Create commands to create copies of the children.
    //
    HashSet<Object> result = new HashSet<Object>(owner.eContents());
    return result;
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
    result.append(" (copyHelper: " + copyHelper + ")");

    return result.toString();
  }
}
