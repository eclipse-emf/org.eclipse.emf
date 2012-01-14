/**
 * Copyright (c) 2002-2006 IBM Corporation and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *   IBM - Initial API and implementation
 */
package org.eclipse.emf.mapping.command;


import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

import org.eclipse.emf.common.command.AbstractCommand;
import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.edit.command.AddCommand;
import org.eclipse.emf.edit.command.CopyCommand;
import org.eclipse.emf.edit.command.InitializeCopyCommand;
import org.eclipse.emf.mapping.domain.MappingDomain;


/**
 * This command overrides the InitializeCopyCommand for cross-domain copies.
 * This implementation is a no-op unless the objects are suitable ECore classes,
 * in which case it copies the name and possibly the eType.
 * Application specific subclasses should override execute() to complete the initialization, if necessary.
 */
public class InitializeCopyOverrideCommand extends AbstractCommand
{
  /**
   * This keeps track of the mapping domain in which the command operates.
   */
  protected MappingDomain mappingDomain;

  /**
   * This is command we're overriding
   */
  protected InitializeCopyCommand initializeCommand;

  /**
   * This creates a command instance that initializes a copied object
   */
  public InitializeCopyOverrideCommand(MappingDomain domain, InitializeCopyCommand initializeCommand)
  {
    super(initializeCommand.doGetLabel(), initializeCommand.doGetDescription());

    this.mappingDomain = domain;
    this.initializeCommand = initializeCommand;
  }

  @Override
  protected boolean prepare()
  {
    return true;
  }

  public void execute()
  {
    EObject owner = initializeCommand.getOwner();
    EObject copy = initializeCommand.getCopy();
    CopyCommand.Helper copyHelper = initializeCommand.getCopyHelper();

    /*
    if (owner.refMetaObject() == copy.refMetaObject())
    {
      initializeCommand.doExecute();
      return;
    }
    */

    // Copy the children references 
    //
    Collection<Object> copyChildren = new ArrayList<Object>();
    for (Object copyChildObject : mappingDomain.getChildren(owner))
    {
      if (copyChildObject instanceof EObject)
      {
        EObject copyChild = copyHelper.getCopy((EObject)copyChildObject);
        if (copyChild != null)
        {
          copyChildren.add(copyChild);
        }
      }
    }   
    if (!copyChildren.isEmpty()) {
      Command addCommand = AddCommand.create(mappingDomain, copy, null, copyChildren);
      if (addCommand.canExecute())
      {
        addCommand.execute(); // this will create the mapping as well
      }
      else
      {
        addCommand.dispose();
        return;
      }
    }

    // Copy the name
    //
    String ownerName = mappingDomain.getName(owner);
    if (ownerName != null)
    {
      mappingDomain.setName(copy, mappingDomain.getOutputName(ownerName));
    }

    // Copy the type
    //
    Object ownerType = mappingDomain.getTypeClassifier(owner);
    if (ownerType != null)
    {
      Object copyType = mappingDomain.getOutputTypeClassifier(ownerType);
      if (copyType != null)
      {
        mappingDomain.setTypeClassifier(copy, copyType);
      }
    }
  }

  @Override
  public void undo()
  {
    // no-op
  }

  public void redo()
  {
    // no-op
  }

  @Override
  public Collection<?> getResult()
  {
    return Collections.singleton(initializeCommand.getCopy());
  }

  @Override
  public Collection<?> getAffectedObjects()
  {
    return Collections.singleton(initializeCommand.getCopy());
  }

  /**
   * This gives an abbreviated name using this object's own class' name, without package qualification,
   * followed by a space separated list of <tt>field:value</tt> pairs.
   */
  @Override
  public String toString()
  {
    StringBuffer result = new StringBuffer(super.toString());
    result.append(" (mappingDomain: " + mappingDomain + ")");
    result.append(" (owner: " + initializeCommand.getOwner() + ")");
    result.append(" (copy: " + initializeCommand.getCopy() + ")");

    return result.toString();
  }
}
