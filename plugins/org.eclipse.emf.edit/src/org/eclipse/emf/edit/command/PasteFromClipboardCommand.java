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
 * $Id: PasteFromClipboardCommand.java,v 1.4 2007/10/02 19:24:58 emerks Exp $
 */
package org.eclipse.emf.edit.command;


import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;

import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.command.CommandWrapper;
import org.eclipse.emf.common.command.StrictCompoundCommand;
import org.eclipse.emf.edit.EMFEditPlugin;
import org.eclipse.emf.edit.domain.EditingDomain;


/**
 * This works exactly like an {@link AddCommand} but the things to be added are copied from the {@link EditingDomain} clipboard.
 * If the copied clipboard instance is of the same type as the original clipboard instance, 
 * the clipboard instance is replaced by the copied instance and the original instance is used for the add.
 */
public class PasteFromClipboardCommand extends AbstractOverrideableCommand
{
  /**
   * This creates a command to add copies from the clipboard to the specified feature of the owner.
   */
  public static Command create(EditingDomain domain, Object owner, Object feature)
  {
    return create(domain, owner, feature, CommandParameter.NO_INDEX);
  }

  /**
   * This creates a command to add copies from the clipboard to the specified feature of the owner and at the given index.
   */
  public static Command create(EditingDomain domain, Object owner, Object feature, int index)
  {
    if (domain == null)
    {
      return new PasteFromClipboardCommand(domain, owner, feature, index, true);
    }
    else
    {
      Command command = 
        domain.createCommand(PasteFromClipboardCommand.class, new CommandParameter(owner, feature, Collections.emptyList(), index));
      return command;
    }
  }
    
  /**
   * This caches the label.
   */
  protected static final String LABEL = EMFEditPlugin.INSTANCE.getString("_UI_PasteFromClipboardCommand_label");

  /**
   * This caches the description.
   */
  protected static final String DESCRIPTION = EMFEditPlugin.INSTANCE.getString("_UI_PasteFromClipboardCommand_description");

  /**
   * This is the command that does the actual pasting.
   */
  protected StrictCompoundCommand command;

  /**
   * This is object where the clipboard copy is pasted.
   */
  protected Object owner;

  /**
   * This is feature of the owner where the clipboard copy is pasted.
   */
  protected Object feature;

  /**
   * This is index in the feature of the owner where the clipboard copy is pasted.
   */
  protected int index;

  /**
   * This controls whether or not to optimize the canExecute (prepare)
   */
  protected boolean optimize;

  /**
   * This constructs an instance from the domain, which provides access the clipboard collection 
   * via {@link EditingDomain#getCommandStack}.
   */
  public PasteFromClipboardCommand(EditingDomain domain, Object owner, Object feature, int index)
  {
    this(domain, owner, feature, index, true);
  }

  public PasteFromClipboardCommand(EditingDomain domain, Object owner, Object feature, int index, boolean optimize)
  {
    super(domain, LABEL, DESCRIPTION);

    this.owner = owner;
    this.feature = feature;
    this.index = index;
    this.optimize = optimize;
  }

  public Object getOwner()
  {
    return owner;
  }

  public Object getFeature()
  {
    return feature;
  }

  public int getIndex()
  {
    return index;
  }

  @Override
  protected boolean prepare()
  {
    // Create a strict compound command to do a copy and then add the result
    //
    command = new StrictCompoundCommand();

    // Create a command to copy the clipboard.
    //
    final Command copyCommand = CopyCommand.create(domain, domain.getClipboard());
    command.append(copyCommand);

    // Create a proxy that will create an add command.
    //
    command.append
      (new CommandWrapper()
       {
         protected Collection<Object> original;
         protected Collection<Object> copy;

         @Override
         protected Command createCommand()
         {
           original = domain.getClipboard();
           copy = new ArrayList<Object>(copyCommand.getResult());

           // Use the original to do the add, but only if it's of the same type as the copy.
           // This ensures that if there is conversion being done as part of the copy,
           // as would be the case for a cross domain copy in the mapping framework,
           // that we do actually use the converted instance.
           //
           if (original.size() == copy.size())
           {
             for (Iterator<Object> i = original.iterator(), j = copy.iterator(); i.hasNext(); )
             {
               Object originalObject = i.next();
               Object copyObject = j.next();
               if (originalObject.getClass() != copyObject.getClass())
               {
                 original = null;
                 break;
               }
             }
           }
           
           Command addCommand = AddCommand.create(domain, owner, feature, original == null ? copy : original, index);
           return addCommand;
         }

         @Override
         public void execute()
         {
           if (original != null)
           {
             domain.setClipboard(copy);
           }
           super.execute();
         }

         @Override
         public void undo()
         {
           super.undo();
           if (original != null)
           {
             domain.setClipboard(original);
           }
         }

         @Override
         public void redo()
         {
           if (original != null)
           {
             domain.setClipboard(copy);
           }
           super.redo();
         }
       });

    boolean result;
    if (optimize)
    {
      // This will determine canExecute as efficiently as possible.
      //
      result = optimizedCanExecute();
    }
    else
    {
      // This will actually execute the copy command in order to check if the add can execute.
      //
      result = command.canExecute();
    }

    return result;
  }

  protected boolean optimizedCanExecute()
  {
    // We'll assume that the copy command can execute and that adding a copy of the clipboard
    // is the same test as adding the clipboard contents itself.
    //
    Command addCommand = AddCommand.create(domain, owner, feature, domain.getClipboard());
    boolean result = addCommand.canExecute();
    addCommand.dispose();
    return result;
  }

  @Override
  public void doExecute()
  {
    // We need to check canExecute() here in case prepare() went down the "optimize" path.
    //
    if (command.canExecute())
    {
      command.execute();
    }  
    else
    {
      // Thread.dumpStack();
    }
  }

  @Override
  public void doUndo()
  {
    command.undo();
  }

  @Override
  public void doRedo()
  {
    command.redo();
  }

  @Override
  public Collection<?> doGetResult()
  {
    return command.getResult();
  }

  @Override
  public Collection<?> doGetAffectedObjects()
  {
    return command.getAffectedObjects();
  }

  @Override
  public void doDispose()
  {
    if (command != null) command.dispose();
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

    return result.toString();
  }
}
