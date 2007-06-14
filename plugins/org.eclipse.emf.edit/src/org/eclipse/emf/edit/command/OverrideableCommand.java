/**
 * <copyright> 
 *
 * Copyright (c) 2002-2006 IBM Corporation and others.
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
 * $Id: OverrideableCommand.java,v 1.4 2007/06/14 18:32:42 emerks Exp $
 */
package org.eclipse.emf.edit.command;


import java.util.Collection;

import org.eclipse.emf.common.command.Command;


/**
 * This represents a command that can be overridden by another command.
 * The intended use of this is that an overrideable command should call 
 * {@link org.eclipse.emf.edit.domain.EditingDomain#createOverrideCommand EditingDomain.createOverrideCommand}
 * in its constructor to set up the override command.
 * All its {@link Command} methods should then be guarded as follows:
 * <pre>
 *   public void execute()
 *   {
 *     if (getOverride() != null)
 *     {
 *       getOverride().execute();
 *     }
 *     else
 *     {
 *       doExecute();
 *     }
 *   }
 * </pre>
 * The contract with the overriding command is that the overrideable command will implement all its
 * methods in corresponding doXxx methods, e.g., execute() is implemented in doExecute(), so that the
 * overriding command can call back to the overrideable command's doXxx methods if it wants to extend
 * rather than replace the original implementation.
 * {@link AbstractOverrideableCommand} provides a convenient base implementation for overrideable commands.
 */
public interface OverrideableCommand extends Command
{
  /**
   * This returns the command that overrides this command.
   */
  Command getOverride();

  /**
   * This sets the command that overrides this command.
   */
  void setOverride(Command overrideCommand);

  /**
   * This is overrideable command's implementation of canExecute.
   */
  boolean doCanExecute();

  /**
   * This is overrideable command's implementation of execute.
   */
  void doExecute();

  /**
   * This is overrideable command's implementation of canUndo.
   */
  boolean doCanUndo();

  /**
   * This is overrideable command's implementation of undo.
   */
  void doUndo();

  /**
   * This is overrideable command's implementation of redo.
   */
  void doRedo();

  /**
   * This is overrideable command's implementation of getResult.
   */
  Collection<?> doGetResult();

  /**
   * This is overrideable command's implementation of getAffectedObjects.
   */
  Collection<?> doGetAffectedObjects();

  /**
   * This is overrideable command's implementation of getLabel.
   */
  String doGetLabel();

  /**
   * This is overrideable command's implementation of getDescription.
   */
  String doGetDescription();

  /**
   * This is overrideable command's implementation of dispose.
   */
  void doDispose();
}
