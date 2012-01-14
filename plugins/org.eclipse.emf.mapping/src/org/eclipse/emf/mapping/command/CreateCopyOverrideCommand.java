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


import java.util.Collection;
import java.util.Collections;

import org.eclipse.emf.common.command.AbstractCommand;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.edit.command.ChildrenToCopyProvider;
import org.eclipse.emf.edit.command.CopyCommand;
import org.eclipse.emf.edit.command.CreateCopyCommand;
import org.eclipse.emf.mapping.MappedObjectState;
import org.eclipse.emf.mapping.MappingRoot;
import org.eclipse.emf.mapping.domain.MappingDomain;


/**
 * This command overrides the CreateCopyCommand for cross-domain copies.
 */
public class CreateCopyOverrideCommand extends AbstractCommand implements ChildrenToCopyProvider
{
  /**
   * This keeps track of the mapping domain in which the command operates.
   */
  protected MappingDomain mappingDomain;

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
   * This creates a command instance that creates a copy of owner.
   */
  public CreateCopyOverrideCommand(MappingDomain domain, CreateCopyCommand createCommand)
  {
    super(createCommand.doGetLabel(), createCommand.doGetDescription());

    this.mappingDomain = domain;
    this.owner = createCommand.getOwner();
    this.copyHelper = createCommand.getCopyHelper();
  }

  @Override
  protected boolean prepare()
  {
    return true;
  }

  public void execute()
  {
    // Get the corresponding type.
    //
    
    EClass outputType = (EClass)mappingDomain.getOutputMetaObject(owner.eClass());

    if (outputType != null)
    {
      // Create the copy from this corresponding type.
      //
      copy = outputType.getEPackage().getEFactoryInstance().create(outputType);

      copyHelper.put(owner, copy);

      MappingRoot mappingRoot = mappingDomain.getMappingRoot();
      if (mappingRoot.isInputObject(owner))
      {
        // This is done to ensure that this new copy is treated as an output by the domain.
        // The CreateMappingCommand will rely on this setting.
        //
        MappedObjectState mappedObjectState = mappingRoot.getMappedObjectState(copy);
        if (mappedObjectState != null)
        {
          mappedObjectState.setOriginatingInput(owner);
        }
      }
    }
  }

  @Override
  public void undo()
  {
    copyHelper.remove(owner);
  }

  public void redo()
  {
    copyHelper.put(owner, copy);
  }

  @Override
  public Collection<?> getResult()
  {
    return Collections.singleton(copy);
  }

  public Collection<?> getChildrenToCopy()
  {
    return mappingDomain.getChildren(owner);
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
    result.append(" (owner: " + owner + ")");
    result.append(" (copy: " + copy + ")");
    result.append(" (copyHelper: " + copyHelper + ")");

    return result.toString();
  }
}
