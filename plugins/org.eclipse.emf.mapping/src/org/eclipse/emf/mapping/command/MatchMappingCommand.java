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
 * $Id: MatchMappingCommand.java,v 1.1 2004/03/06 17:31:32 marcelop Exp $
 */
package org.eclipse.emf.mapping.command;


import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.command.CompoundCommand;
import org.eclipse.emf.mapping.Mapping;
import org.eclipse.emf.mapping.MappingPlugin;
import org.eclipse.emf.mapping.MappingRoot;
import org.eclipse.emf.mapping.domain.MappingDomain;


public abstract class MatchMappingCommand extends CompoundCommand
{
  /**
   * This keeps track of the mapping domain in which the command operates.
   */
  protected MappingDomain domain;

  /**
   * This is the mapping that is being recursively matched.
   */
  protected Mapping mapping;

  /**
   * This is the collection of inputs that have been matched by this command
   */
  protected Collection mappedInputs;

  /**
   * This caches the label.
   */
  protected static final String LABEL = MappingPlugin.getPlugin().getString("_UI_MatchMappingCommand_label");

  /**
   * This cachaes the description.
   */
  protected static final String DESCRIPTION = MappingPlugin.getPlugin().getString("_UI_MatchMappingCommand_description");

  public MatchMappingCommand(MappingDomain domain, Mapping mapping)
  {
    super(LABEL, DESCRIPTION);

    this.domain = domain;
    this.mapping = mapping;
  }

  protected boolean prepare() 
  {
    if (domain != null && mapping != null)
    {
      Collection inputChildren = new ArrayList(); 
      for (Iterator inputs = mapping.getSenders().iterator(); inputs.hasNext(); )
      {
        inputChildren.addAll(domain.getChildren(inputs.next()));
      }

      Collection outputChildren = new ArrayList(); 
      for (Iterator outputs = mapping.getReceivers().iterator(); outputs.hasNext(); )
      {
        outputChildren.addAll(domain.getChildren(outputs.next()));
      }

      matchChildren(inputChildren, outputChildren);
    }

    // We have done our preparation. Now ask the super to validate.
    //
    boolean result = super.prepare();
    return result;
  }

  protected void matchChildren(Collection inputChildren, Collection outputChildren)
  {
    mappedInputs = new ArrayList();
    MappingRoot mappingRoot = domain.getMappingRoot();
    boolean multipleMatchesAllowed = (domain.getMappingEnablementFlags() & MappingDomain.ENABLE_MULTIPLE_INPUT_MAPPINGS) != 0;

    for (Iterator childOutputs = outputChildren.iterator(); childOutputs.hasNext(); )
    {
      Object childOutput = childOutputs.next();
      if (mappingRoot.getMappings(childOutput).isEmpty())
      {
        Collection mappedObjects = new ArrayList();

        for (Iterator childInputs = inputChildren.iterator(); childInputs.hasNext(); )
        {
          Object childInput = childInputs.next();
          boolean canCreateMapping = 
            multipleMatchesAllowed || (!mappedInputs.contains(childInput) && mappingRoot.getMappings(childInput).isEmpty());
          if (canCreateMapping && match(childInput, childOutput, mappedObjects))
          {
            break;
          }
        }

        if (!mappedObjects.isEmpty())
        {
          mappedInputs.addAll(mappedObjects);
          mappedObjects.add(childOutput);

          Command mapCommand = CreateMappingCommand.create(domain, mappedObjects);
          appendIfCanExecute(mapCommand);
        }
      }
    }
  }

  protected abstract boolean match(Object inputObject, Object outputObject, Collection mappedObjects);

  /**
   * This gives an abbreviated name using this object's own class' name, without package qualification,
   * followed by a space separated list of <tt>field:value</tt> pairs.
   */
  public String toString()
  {
    StringBuffer result = new StringBuffer(super.toString());
    result.append(" (domain: " + domain + ")");
    result.append(" (mapping: " + mapping + ")");

    return result.toString();
  }
}
