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

import org.eclipse.emf.common.command.IdentityCommand;
import org.eclipse.emf.edit.command.CopyCommand;
import org.eclipse.emf.edit.command.CopyToClipboardCommand;
import org.eclipse.emf.mapping.domain.MappingDomain;


/**
 * This command overrides the CopyToClipboardCommand for cross-domain copies.
 */
public class CopyToClipboardOverrideCommand extends CopyToClipboardCommand
{
  /**
   * This is the collection of read-only (input) objects that don't need to actually be copied.
   */
  protected Collection<?> inputObjects;

  /**
   * This constructs a command that copies the given collections of objects to the clipboard.
   */
  public CopyToClipboardOverrideCommand(MappingDomain domain, Collection<?> nonInputObjects, Collection<?> inputObjects)
  {
    super(domain, nonInputObjects);

    this.inputObjects = inputObjects;
  }

  @Override
  protected boolean prepare()
  {
    // Since input objects are read-only, we don't need to make a copy of them for the clipboard.
    // We can just put a pointer to the original object(s) on the clipboard.
    //
    copyCommand = new IdentityCommand(inputObjects);

    // Do we also have some non-input objects to copy?
    //
    if (sourceObjects.size() > 0)
    {
      copyCommand = copyCommand.chain(CopyCommand.create(domain, sourceObjects));
    }

    return copyCommand.canExecute();
  }

}
