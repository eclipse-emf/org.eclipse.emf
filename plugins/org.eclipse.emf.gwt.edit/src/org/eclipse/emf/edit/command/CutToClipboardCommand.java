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
 * $Id: CutToClipboardCommand.java,v 1.1 2010/04/28 14:48:43 emerks Exp $
 */
package org.eclipse.emf.edit.command;


import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.command.CommandWrapper;
import org.eclipse.emf.edit.EMFEditPlugin;
import org.eclipse.emf.edit.domain.EditingDomain;


/**
 * This works just like {@link RemoveCommand} but also sets the removed objects to the {@link EditingDomain}.
 * In fact, the implementation is just a proxy for remove command.
 */
public class CutToClipboardCommand extends CommandWrapper 
{
  /**
   * This creates a command to remove an object
   * and set it to the clipboard.
   */
  public static Command create(EditingDomain domain, Object value) 
  {
    if (domain == null)
    {
      return new CutToClipboardCommand(domain, RemoveCommand.create(domain, value));
    }
    else
    {
      return domain.createCommand(CutToClipboardCommand.class, new CommandParameter(null, null, Collections.singleton(value)));
    }
  }

  /**
   * This creates a command to remove a particular value from the specified feature of the owner
   * and set it to the clipboard.
   */
  public static Command create(EditingDomain domain, Object owner, Object feature, Object value) 
  {
    if (domain == null)
    {
      return new CutToClipboardCommand(domain, RemoveCommand.create(domain, owner, feature, value));
    }
    else
    {
      return domain.createCommand(CutToClipboardCommand.class, new CommandParameter(owner, feature, Collections.singleton(value)));
    }
  }

  /**
   * This creates a command to remove multiple objects
   * and set it to the clipboard.
   */
  public static Command create(EditingDomain domain, Collection<?> collection) 
  {
    if (domain == null)
    {
      return new CutToClipboardCommand(domain, RemoveCommand.create(domain, collection));
    }
    else
    {
      return domain.createCommand(CutToClipboardCommand.class, new CommandParameter(null, null, collection));
    }
  }

  /**
   * This creates a command to remove a collection of values from the specified feature of the owner
   * and set it to the clipboard.
   */
  public static Command create(EditingDomain domain, Object owner, Object feature, Collection<?> collection)
  {
    if (domain == null)
    {
      return new CutToClipboardCommand(domain, RemoveCommand.create(domain, owner, feature, collection));
    }
    else
    {
      return domain.createCommand(CutToClipboardCommand.class, new CommandParameter(owner, feature, collection));
    }
  }

  /**
   * This caches the label.
   */
  protected static final String LABEL = EMFEditPlugin.INSTANCE.getString("_UI_CutToClipboardCommand_label");

  /**
   * This caches the description.
   */
  protected static final String DESCRIPTION = EMFEditPlugin.INSTANCE.getString("_UI_CutToClipboardCommand_description");

  /**
   * This is the editing domain in which this command operates.
   */
  protected EditingDomain domain;

  /**
   * This is the original clipboard value before execute.
   */
  protected Collection<Object> oldClipboard;

  /**
   * This constructs an instance that yields the result of the given command as its clipboard.
   */
  public CutToClipboardCommand(EditingDomain domain, Command command)
  {
    super(LABEL, DESCRIPTION, command);

    this.domain = domain;
  }

  @Override
  public void execute()
  {
    super.execute();

    if (domain != null)
    {
      oldClipboard = domain.getClipboard();
      domain.setClipboard(new ArrayList<Object>(command.getResult()));
    }
  }

  @Override
  public void undo()
  {
    super.undo();

    if (domain != null)
    {
      domain.setClipboard(oldClipboard);
    }
  }

  @Override
  public void redo()
  {
    super.redo();

    if (domain != null)
    {
      oldClipboard = domain.getClipboard();
      domain.setClipboard(new ArrayList<Object>(command.getResult()));
    }
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
    result.append(" (oldClipboard: " + oldClipboard + ")");

    return result.toString();
  }
}
