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


import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.command.CompoundCommand;
import org.eclipse.emf.edit.command.AddCommand;
import org.eclipse.emf.edit.command.CopyCommand;
import org.eclipse.emf.edit.command.CreateCopyCommand;
import org.eclipse.emf.edit.command.DragAndDropCommand;
import org.eclipse.emf.mapping.domain.MappingDomain;


/**
 * This command overrides the PasteFromClipboardCommand for cross-domain (optimized) copies.
 */
public class DragAndDropOverrideCommand extends DragAndDropCommand
{
  public DragAndDropOverrideCommand(MappingDomain domain, DragAndDropCommand command)
  {
    super(domain, command.getOwner(), command.getLocation(), command.getOperations(), command.getOperation(), command.getCollection(), domain.getOptimizeCopy());
  }

  @Override
  protected boolean optimizedCanExecute()
  {
    if (collection == null)
    {
      return false;
    }

    // We'll try adding a shallow copy of the clipboard contents, instead of a full copy.
    // Note: we can't just try adding the clipboard contents itself, because the copy may be a
    //       different type then what's on the clipboard (e.g. EJB Field -> RDB Column).
    //
    CopyCommand.Helper copyHelper = new CopyCommand.Helper();
    CompoundCommand shallowCopyCommand = new CompoundCommand(CompoundCommand.MERGE_COMMAND_ALL);
    for (Object object : collection)
    {
      if (!shallowCopyCommand.appendAndExecute(CreateCopyCommand.create(domain, object, copyHelper)))
      {
        shallowCopyCommand.dispose();
        return false;
      }
    }

    Command addCommand = AddCommand.create(domain, owner, null, shallowCopyCommand.getResult());
    boolean result = addCommand.canExecute();

    shallowCopyCommand.dispose();
    addCommand.dispose();

    return result;
  }

}
