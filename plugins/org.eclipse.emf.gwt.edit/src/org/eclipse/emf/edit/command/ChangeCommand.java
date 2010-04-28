/**
 * <copyright> 
 *
 * Copyright (c) 2006-2010 IBM Corporation and others.
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
 * $Id: ChangeCommand.java,v 1.2 2010/04/28 20:38:33 khussey Exp $
 */
package org.eclipse.emf.edit.command;


import java.util.Collection;
import java.util.Collections;

import org.eclipse.emf.common.command.AbstractCommand;
import org.eclipse.emf.common.notify.Notifier;
import org.eclipse.emf.ecore.change.ChangeDescription;
import org.eclipse.emf.ecore.change.util.ChangeRecorder;

/**
 * <p>This command uses the Change Model facilities to enable a group of modifications
 * to be executed as one single command.  The undo operation reverts all the 
 * modifications made when the command was executed</p>
 * <p>In order to use this class, the <tt>org.eclipse.emf.ecore.change</tt> plugin has to
 * be available in your Eclipse configuration.</p>
 * 
 * @since 2.2.0
 */
abstract public class ChangeCommand extends AbstractCommand
{  
  protected ChangeDescription changeDescription;

  // Possible scope for the ChangeRecorder
  protected Notifier notifier;
  protected Collection<Notifier> notifiers;
  protected ChangeRecorder changeRecorder;
  
  protected ChangeCommand(ChangeRecorder changeRecorder)
  {
    if (changeRecorder.isRecording())
    {
      throw new IllegalStateException("The changeRecorder cannot be currently recording.");
    }
    this.changeRecorder = changeRecorder;
  }

  public ChangeCommand(ChangeRecorder changeRecorder, Notifier notifier)
  {
    this(changeRecorder);
    this.notifier = notifier;
  }
  
  public ChangeCommand(Notifier notifier)
  {
    this.notifier = notifier;
  }

  public ChangeCommand(ChangeRecorder changeRecorder, Collection<Notifier> notifiers)
  {
    this(changeRecorder);    
    this.notifiers = notifiers;
  }

  public ChangeCommand(Collection<Notifier> notifiers)
  {
    this.notifiers = notifiers;
  }
  
  @Override
  public void dispose()
  {
    changeRecorder = null;
    notifier = null;
    notifiers = null;

    changeDescription = null;
    
    super.dispose();
  }
    
  /**
   * Returns the {@link ChangeRecorder} specified in the
   * constructor if any.
   * @return {@link ChangeRecorder}
   */
  public ChangeRecorder getChangeRecorder()
  {
    return changeRecorder;
  }
    
  protected ChangeRecorder createChangeRecorder()
  {
    return new ChangeRecorder();
  }

  protected ChangeDescription getChangeDescription()
  {
    return changeDescription;
  }
  
  protected void setChangeDescription(ChangeDescription changeDescription)
  {
    this.changeDescription = changeDescription;
  }
      
  @Override
  protected boolean prepare()
  {
    return getChangeDescription() == null;
  }
  
  @Override
  public boolean canUndo()
  {
    return getChangeDescription() != null;
  }
  
  /**
   * Executes the command.  Subclasses are <b>not</b> expected to overwrite
   * this method, and implement their changes on {@link #doExecute()}.
   */
  public void execute()
  { 
    ChangeRecorder changeRecorder = getChangeRecorder();
    if (changeRecorder == null)
    {
      changeRecorder = createChangeRecorder();
    }
    
    changeRecorder.beginRecording(notifier != null ? Collections.singleton(notifier) : notifiers);

    try
    {
      doExecute();
    }
    finally
    {
      setChangeDescription(changeRecorder.endRecording());
      disposeChangeRecorder(changeRecorder);
    }    
  }
  
  /**
   * Subclasses should override this to perform modifications to resources and objects.
   * If no modification occurs, an {@link org.eclipse.emf.common.command.AbortExecutionException}
   * may be thrown to avoid placing the command on the command stack.
   */
  abstract protected void doExecute();
  
  /**
   * Disposes the {@link ChangeRecorder} created by {@link #execute()}.  The default 
   * implementation doesn't dispose the change recorder if it was passed in 
   * through one of the constructor methods.
   * @param changeRecorder
   */
  protected void disposeChangeRecorder(ChangeRecorder changeRecorder)
  {
    if (changeRecorder != getChangeRecorder())
    {
      changeRecorder.dispose();
    }
  }
  
  @Override
  public void undo()
  {    
    getChangeDescription().applyAndReverse();
  }
  
  public void redo()
  {    
    getChangeDescription().applyAndReverse();
  }  
}
