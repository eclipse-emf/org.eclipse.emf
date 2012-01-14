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


import org.eclipse.emf.common.command.AbstractCommand;
import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.command.CommandStack;
import org.eclipse.emf.edit.command.CommandParameter;
import org.eclipse.emf.edit.command.RemoveCommand;
import org.eclipse.emf.mapping.MappingPackage;
import org.eclipse.emf.mapping.MappingPlugin;
import org.eclipse.emf.mapping.MappingRoot;
import org.eclipse.emf.mapping.domain.MappingDomain;


/**
 * The restore initial state command ensures that the mapping root is ready to begin a mapping session from a defined initial state.
 * This implementation just deletes all the mappings, which is good for pure meet-in-the-middle mapping.
 * For pure top down mapping, a derived implementation will want to reset the outputs.
 */
public class RestoreInitialStateCommand extends AbstractCommand 
{
  /**
   * This creates a command that removes the mapping from the mapping root.
   */
  public static Command create(MappingDomain domain)
  {
    return 
      domain.createCommand
        (RestoreInitialStateCommand.class, new CommandParameter(domain.getMappingRoot(), null, null));
  }

  /**
   * This caches the label.
   */
  protected static final String LABEL = MappingPlugin.getPlugin().getString("_UI_RestoreInitialStateCommand_label");

  /**
   * This cachaes the description.
   */
  protected static final String DESCRIPTION = MappingPlugin.getPlugin().getString("_UI_RestoreInitialStateCommand_description");

  /**
   * This keeps track of the mapping domain in which the command operates.
   */
  protected MappingDomain domain;

  /**
   * This keeps track of all the command used to implement removing all the mappings.
   */
  Command removeCommand;

  /**
   * This creates a command instance that removes the mappings in the collection from the mapping root.
   */
  public RestoreInitialStateCommand(MappingDomain domain)
  {
    super(LABEL, DESCRIPTION);

    this.domain = domain;
  }

  @Override
  protected boolean prepare() 
  {
    boolean result = true;

    CommandStack commandStack = domain.getCommandStack();
    if (domain == null || 
          !(commandStack instanceof PersistentCommandStack) ||
          commandStack.getMostRecentCommand() != null)
    {
      result = false;
    }

    return result;
  }

  public void execute()
  {
    // Remove all the mappings from the root.
    //
    MappingRoot mappingRoot = domain.getMappingRoot();
    //removeCommand = RemoveCommand.create(domain, mappingRoot, mappingRoot.ePackageMapping().getMapping_Nested(), mappingRoot.getNested());
  	 removeCommand = RemoveCommand.create(domain, mappingRoot, MappingPackage.eINSTANCE.getMapping_Nested(), mappingRoot.getNested());

    if (removeCommand.canExecute())
    {
      removeCommand.execute();
    }
    else
    {
      removeCommand.dispose();
      removeCommand = null;
    }

    // This is the tricky part where we set the encoding 
    // that we'd like to have decoded as a series of commands to execute after this command has completed.
    //
    PersistentCommandStack commandStack = (PersistentCommandStack)domain.getCommandStack();
    commandStack.setEncoding(domain, mappingRoot.getCommandStack());
  }

  @Override
  public void undo() 
  {
    if (removeCommand != null)
    {
      removeCommand.undo();
    }
  }

  public void redo()
  {
    if (removeCommand != null)
    {
      removeCommand.redo();
    }
  }

  @Override
  public void dispose()
  {
    if (removeCommand != null)
    {
      removeCommand.dispose();
    }

    super.dispose();
  }

  /**
   * This gives an abbreviated name using this object's own class' name, without package qualification,
   * followed by a space separated list of <tt>field:value</tt> pairs.
   */
  @Override
  public String toString()
  {
    StringBuffer result = new StringBuffer(super.toString());
    result.append(" (domain: " + domain + ")");
    result.append(" (removeCommand: " + removeCommand + ")");

    return result.toString();
  }
}
