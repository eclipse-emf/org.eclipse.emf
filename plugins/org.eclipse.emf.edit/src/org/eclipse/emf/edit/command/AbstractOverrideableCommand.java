/**
 * <copyright> 
 *
 * Copyright (c) 2002-2004 IBM Corporation and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Common Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/cpl-v10.html
 * 
 * Contributors: 
 *   IBM - Initial API and implementation
 *
 * </copyright>
 *
 * $Id: AbstractOverrideableCommand.java,v 1.1 2004/03/06 17:31:32 marcelop Exp $
 */
package org.eclipse.emf.edit.command;


import java.util.Collection;
import java.util.Collections;

import org.eclipse.emf.common.command.AbstractCommand;
import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.edit.domain.EditingDomain;


/**
 * This is a convenient base class for classes that implement {@link OverrideableCommand}.
 * Subclasses of AbstractOverrideableCommand should provide implementations of the doXxx methods
 * (e.g., doExecute) from OverrideableCommand instead of the base command methods (e.g.,
 * execute), which have final implementations here.
 */
public abstract class AbstractOverrideableCommand extends AbstractCommand implements OverrideableCommand
{
  /**
   * This is the editing doman in which this command operates.
   */
  protected EditingDomain domain;

  /**
   * This is the command that overrides this command.
   */
  protected Command overrideCommand;

  /** 
   * This constructs an instance in this editing domain. 
   */
  protected AbstractOverrideableCommand(EditingDomain domain) 
  {
    this(domain, null, null);
  }

  /** 
   * This constructs an instance with the given label and in this editing domain. 
   */
  protected AbstractOverrideableCommand(EditingDomain domain, String label) 
  {
    this(domain, label, null);
  }

  /** 
   * This constructs an instance with the given label and description, in this editing domain. 
   */
  protected AbstractOverrideableCommand(EditingDomain domain, String label, String description) 
  {
    super(label, description);

    this.domain = domain;
  }

  /**
   * This returns the editing domain that contains this.
   */
  public EditingDomain getDomain()
  {
    return domain;
  }

  /**
   * This returns the command that overrides this command.
   */
  public Command getOverride()
  {
    return overrideCommand;
  }

  /**
   * This sets the command that overrides this command.
   */
  public void setOverride(Command overrideCommand)
  {
    this.overrideCommand = overrideCommand;
  }

  public final boolean canExecute() 
  {
    if (domain != null && !isPrepared)
    {
      Command newOverrideCommand = domain.createOverrideCommand(this);
      setOverride(newOverrideCommand);
    }

    boolean result = 
      overrideCommand != null ?
        overrideCommand.canExecute() :
        doCanExecute();
    
    return result;
  }

  public boolean doCanExecute() 
  {
    return super.canExecute();
  }

  public final void execute() 
  {
    if (overrideCommand != null)
    {
      overrideCommand.execute();
    }
    else
    {
      doExecute();
    }
  }

  public abstract void doExecute();

  public final boolean canUndo() 
  {
    boolean result = 
      overrideCommand != null ?
        overrideCommand.canUndo() :
        doCanUndo();

    return result;
  }

  public boolean doCanUndo()
  {
    return super.canUndo();
  }

  public final void undo() 
  {
    if (overrideCommand != null)
    {
      overrideCommand.undo();
    }
    else
    {
      doUndo();
    }
  }

  public abstract void doUndo();

  public final void redo()
  {
    if (overrideCommand != null)
    {
      overrideCommand.redo();
    }
    else
    {
      doRedo();
    }
  }

  public abstract void doRedo();

  public final Collection getResult()
  {
    return 
      overrideCommand != null ?
        overrideCommand.getResult() :
        doGetResult();
  }

  public Collection doGetResult()
  {
    return super.getResult();
  }

  public final Collection getAffectedObjects()
  {
    return 
      overrideCommand != null ?
        overrideCommand.getAffectedObjects() :
        doGetAffectedObjects();
  }

  public Collection doGetAffectedObjects()
  {
    return super.getAffectedObjects();
  }

  public final String getLabel()
  {
    return 
      overrideCommand != null ?
        overrideCommand.getLabel() :
        doGetLabel();
  }

  public String doGetLabel()
  {
    return super.getLabel();
  }

  public final String getDescription()
  {
    return 
      overrideCommand != null ?
        overrideCommand.getDescription() :
        doGetDescription();
  }

  public String doGetDescription()
  {
    return super.getDescription();
  }

  public final void dispose()
  {
    if (overrideCommand != null)
    {
      overrideCommand.dispose();
    }
    else
    {
      doDispose();
    }
  }

  public void doDispose()
  {
    super.dispose();
  }

  public final Collection getChildrenToCopy()
  {
    Collection result =
      overrideCommand instanceof ChildrenToCopyProvider ?
        ((ChildrenToCopyProvider)overrideCommand).getChildrenToCopy() :
        doGetChildrenToCopy();

    return result;
  }

  public Collection doGetChildrenToCopy()
  {
    return Collections.EMPTY_LIST;
  }

  public static EList getOwnerList(EObject owner, EStructuralFeature feature)
  {
    return 
      owner.eClass().getEAllStructuralFeatures().contains(feature) && feature.isMany() ?
        (EList)owner.eGet(feature) :
        null;
  }

  /**
   * This gives an abbreviated name using this object's own class' name, without package qualification,
   * followed by a space separated list of <tt>field:value</tt> pairs.
   */
  public String toString()
  {
    StringBuffer result = new StringBuffer(super.toString());
    result.append(" (domain: " + domain + ")");
    result.append(" (overrideCommand: " + overrideCommand + ")");
    return result.toString();
  }
}
