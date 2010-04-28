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
 * $Id: CopyCommand.java,v 1.1 2010/04/28 14:48:44 emerks Exp $
 */
package org.eclipse.emf.edit.command;


import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;

import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.command.CompoundCommand;
import org.eclipse.emf.common.command.IdentityCommand;
import org.eclipse.emf.common.command.StrictCompoundCommand;
import org.eclipse.emf.common.command.UnexecutableCommand;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.edit.EMFEditPlugin;
import org.eclipse.emf.edit.domain.EditingDomain;


/**
 * The copy command logically acts upon an owner object or collection or owner objects 
 * and copies the tree structured implied by the MOF containment hierarchy.
 * The static create methods delegate command creation to {@link EditingDomain#createCommand EditingDomain.createCommand}.
 *
 * <p>
 * The copy implementation is, at each level, delegated to {@link CreateCopyCommand} and 
 * {@link InitializeCopyCommand} which can be overridden to control the copy's object creation
 * and initialization respectively.
 */
public class CopyCommand extends StrictCompoundCommand
{
  /**
   * This creates a command that copies the given object.
   */
  public static Command create(EditingDomain domain, Object owner) 
  {
    return domain.createCommand(CopyCommand.class, new CommandParameter(owner, null, new Helper()));
  }

  /**
   * This creates a command that copies the given collection of objects. If the collection contains more than one object,
   * then a compound command will be created containing individual copy commands for each object.
   */
  public static Command create(final EditingDomain domain, final Collection<?> collection)
  {
    if (collection == null || collection.isEmpty()) 
    {
      return UnexecutableCommand.INSTANCE;
    }

    Helper copyHelper = new Helper();
    CompoundCommand copyCommand = new CompoundCommand(CompoundCommand.MERGE_COMMAND_ALL);
    for (Object object : collection)
    {
      copyCommand.append(domain.createCommand(CopyCommand.class, new CommandParameter(object, null, copyHelper)));
    }

    return copyCommand.unwrap();
  }

  /**
   * This caches the label.
   */
  protected static final String LABEL = EMFEditPlugin.INSTANCE.getString("_UI_CopyCommand_label");

  /**
   * This caches the description.
   */
  protected static final String DESCRIPTION = EMFEditPlugin.INSTANCE.getString("_UI_CopyCommand_description");

  /**
   * This keeps track of the domain in which this command is created.
   */
  protected EditingDomain domain;

  /**
   * This keeps track of the owner in the command parameter from the constructor.
   */
  protected EObject owner;

  /**
   * This is a map of objects to their copies
   */
  protected Helper copyHelper;

  /**
   * This controls whether or not to optimize the canExecute (prepare)
   */
  protected boolean optimize;

  /**
   * This creates and instance in the given domain and for the given owner
   */
  public CopyCommand(EditingDomain domain, EObject owner, Helper copyHelper)
  {
    this(domain, owner, copyHelper, true);
  }

  /**
   * This creates and instance in the given domain and for the given owner
   */
  public CopyCommand(EditingDomain domain, EObject owner, Helper copyHelper, boolean optimize)
  {
    super(LABEL, DESCRIPTION);

    this.resultIndex = 0;
    this.domain = domain;
    this.owner = owner;
    this.copyHelper = copyHelper;
    this.optimize = optimize;

    copyHelper.incrementDeferredInitializationCount();
  }

  @Override
  protected boolean prepare()
  {
    if (owner == null)
    {
      return false;
    }
    
    // Create commands to create the copy object(s).
    //
    CompoundCommand createCommand = new CompoundCommand(0);

    addCreateCopyCommands(createCommand, owner);
    append(createCommand.unwrap());

    // Create an initialize copy command for each of the created objects.
    //
    if (copyHelper.decrementDeferredInitializationCount() == 0)
    {
      Command initializeCommand = 
        new CompoundCommand()
        {
          @Override
          public boolean prepare()
          {
            for (Iterator<EObject> copiedObjects = copyHelper.initializationIterator(); copiedObjects.hasNext(); )
            {
              EObject object = copiedObjects.next();
              Command initializeCopyCommand = InitializeCopyCommand.create(domain, object, copyHelper);
  
              // Record it for execution.
              //
              if (!this.appendIfCanExecute(initializeCopyCommand))
              {
                return false;
              }
  
              copiedObjects.remove();
            }
  
            return true;
          }
        };
      append(initializeCommand);
    }
    else if (!optimize)
    {
      // This ensures that the CreateCopyCommand will be executed by StrictCompoundCommand.prepare().
      //
      append(IdentityCommand.INSTANCE);
    }

    // This will execute the CreateCopyCommand's and then call canExecute on the InitializeCopyCommand's.
    //
    boolean result = super.prepare();

    return result;
  }

  @Override
  public boolean canExecute()
  {
    boolean result;
    if (optimize)
    {
      // We'll assume that all the CreateCopyCommand's and InitializeCopyCommand's can execute.
      //
      result = true;
    }
    else
    {
      // This will call prepare() which will partially execute the command.
      //
      result = super.canExecute();
    }

    return result;
  }

  @Override
  public void execute()
  {
    // We need to check canExecute() in case optimize is true.
    //
    if (super.canExecute())
    {
      super.execute();
    }
    else
    {
      // Thread.dumpStack();
    }
  }

  protected void addCreateCopyCommands(CompoundCommand compoundCommand, EObject object)
  {
    // Create a command to create a copy of the object.
    //
    Command createCopyCommand = CreateCopyCommand.create(domain, object, copyHelper);
    compoundCommand.append(createCopyCommand);

    if (createCopyCommand instanceof ChildrenToCopyProvider && createCopyCommand.canExecute())
    {
      for (Object child :  ((ChildrenToCopyProvider)createCopyCommand).getChildrenToCopy())
      {
        addCreateCopyCommands(compoundCommand, (EObject)child);
      }
    }
    else
    {
      // Create commands to create copies of the children.
      //
      for (EObject child : object.eContents())
      {
        addCreateCopyCommands(compoundCommand, child);
      }
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
    result.append(" (owner: " + owner + ")");

    return result.toString();
  }

  /**
   * This helper class is used to keep track of copied objects and their associated copies.
   */
  public static class Helper extends HashMap<EObject, EObject>
  {
    private static final long serialVersionUID = 1L;

    protected ArrayList<EObject> initializationList = new ArrayList<EObject>();
    protected int deferredInitializationCount;

    /**
     * Return the copy of the specified object if it has one.
     */
    public EObject getCopy(EObject object)
    {
      return get(object);
    }

    /**
     * Return the copy of the specified object or the object itself if it has no copy.
     */
    public EObject getCopyTarget(EObject target, boolean copyRequired)
    {
      EObject copied = getCopy(target);
      if (copied == null)
      {
        copied = copyRequired ? null : target;
      }
      return copied;
    }

    @Override
    public EObject put(EObject key, EObject value)
    {
      initializationList.add(key);
      return super.put(key, value);
    }

    @Override
    public EObject remove(Object key)
    {
      initializationList.remove(key);
      return super.remove(key);
    }

    public Iterator<EObject> initializationIterator() 
    {
      return initializationList.iterator();
    }

    public void incrementDeferredInitializationCount()
    {
      ++deferredInitializationCount;
    }

    public int decrementDeferredInitializationCount()
    {
      return --deferredInitializationCount;
    }
  }
}
