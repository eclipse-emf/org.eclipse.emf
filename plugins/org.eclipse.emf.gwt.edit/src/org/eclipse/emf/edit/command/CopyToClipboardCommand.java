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
 * $Id: CopyToClipboardCommand.java,v 1.1 2010/04/28 14:48:44 emerks Exp $
 */
package org.eclipse.emf.edit.command;


import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.command.AbstractCommand;
import org.eclipse.emf.edit.EMFEditPlugin;
import org.eclipse.emf.edit.domain.EditingDomain;


/**
 * This works exactly like a {@link CopyCommand} but set the copy result to the {@link EditingDomain}.
 * In fact, the implementation is just a proxy for copy command.
 */
public class CopyToClipboardCommand extends AbstractOverrideableCommand implements AbstractCommand.NonDirtying
{
  /**
   * This creates a command that copies the given collection of objects to the clipboard.
   */
  public static Command create(EditingDomain domain, final Collection<?> collection)
  {
    if (domain == null)
    {
      CopyToClipboardCommand command = new CopyToClipboardCommand(domain, collection);
      return command;
    }
    else
    {
      Command command = domain.createCommand(CopyToClipboardCommand.class, new CommandParameter(null, null, collection));
      return command;
    }
  }

  /**
   * This caches the label.
   */
  protected static final String LABEL = EMFEditPlugin.INSTANCE.getString("_UI_CopyToClipboardCommand_label");

  /**
   * This caches the description.
   */
  protected static final String DESCRIPTION = EMFEditPlugin.INSTANCE.getString("_UI_CopyToClipboardCommand_description");

  /**
   * This constructs a command that copies the given collection of objects to the clipboard.
   */
  public CopyToClipboardCommand(EditingDomain domain, Collection<?> collection)
  {
    super (domain, LABEL, DESCRIPTION);

    this.sourceObjects = collection;
  }

  /**
   * This is the collection of objects to be copied to the clipboard.
   */
  protected Collection<?> sourceObjects;

  /**
   * This is the original clipboard value before execute.
   */
  protected Collection<Object> oldClipboard;

  /**
   * This is the command that does the actual copying.
   */
  protected Command copyCommand;

  /**
   * This creates a command that copies the given object to the clipboard.
   */
  public static Command create(EditingDomain domain, Object owner) 
  {
    return create(domain, Collections.singleton(owner));
  }

  /**
   * This returns the collection of objects to be copied to the clipboard.
   */
  public Collection<?> getSourceObjects()
  {
    return sourceObjects;
  }

  @Override
  protected boolean prepare()
  {
    copyCommand = CopyCommand.create(domain, sourceObjects);
    return copyCommand.canExecute();
  }

  @Override
  public void doExecute()
  {
    copyCommand.execute();

    oldClipboard = domain.getClipboard();
    domain.setClipboard(new ArrayList<Object>(copyCommand.getResult()));
  }

  @Override
  public void doUndo()
  {
    copyCommand.undo();

    domain.setClipboard(oldClipboard);
  }

  @Override
  public void doRedo()
  {
    copyCommand.redo();

    oldClipboard = domain.getClipboard();
    domain.setClipboard(new ArrayList<Object>(copyCommand.getResult()));
  }

  @Override
  public Collection<?> doGetResult()
  {
    return copyCommand.getResult();
  }

  @Override
  public Collection<?> doGetAffectedObjects()
  {
    return copyCommand.getAffectedObjects();
  }

  @Override
  public void doDispose()
  {
    if (copyCommand != null) copyCommand.dispose();
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
    result.append(" (sourceObjects: " + sourceObjects + ")");
    result.append(" (oldClipboard: " + oldClipboard + ")");

    return result.toString();
  }
}
