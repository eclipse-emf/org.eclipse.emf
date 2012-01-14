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


import java.util.ArrayList;
import java.util.Collection;

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
  protected Collection<?> mappedInputs;

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

  @Override
  protected boolean prepare() 
  {
    if (domain != null && mapping != null)
    {
      Collection<Object> inputChildren = new ArrayList<Object>(); 
      for (Object input : mapping.getSenders())
      {
        inputChildren.addAll(domain.getChildren(input));
      }

      Collection<Object> outputChildren = new ArrayList<Object>(); 
      for (Object output : mapping.getReceivers())
      {
        outputChildren.addAll(domain.getChildren(output));
      }

      matchChildren(inputChildren, outputChildren);
    }

    // We have done our preparation. Now ask the super to validate.
    //
    boolean result = super.prepare();
    return result;
  }

  protected void matchChildren(Collection<?> inputChildren, Collection<?> outputChildren)
  {
    ArrayList<Object> newMappedInputs = new ArrayList<Object>();
    mappedInputs = newMappedInputs;
    MappingRoot mappingRoot = domain.getMappingRoot();
    boolean multipleMatchesAllowed = (domain.getMappingEnablementFlags() & MappingDomain.ENABLE_MULTIPLE_INPUT_MAPPINGS) != 0;

    for (Object childOutput : outputChildren)
    {
      if (mappingRoot.getMappings(childOutput).isEmpty())
      {
        Collection<Object> mappedObjects = new ArrayList<Object>();

        for (Object childInput : inputChildren)
        {
          boolean canCreateMapping = 
            multipleMatchesAllowed || (!mappedInputs.contains(childInput) && mappingRoot.getMappings(childInput).isEmpty());
          if (canCreateMapping && match(childInput, childOutput, mappedObjects))
          {
            break;
          }
        }

        if (!mappedObjects.isEmpty())
        {
          newMappedInputs.addAll(mappedObjects);
          mappedObjects.add(childOutput);

          Command mapCommand = CreateMappingCommand.create(domain, mappedObjects);
          appendIfCanExecute(mapCommand);
        }
      }
    }
  }

  protected abstract boolean match(Object inputObject, Object outputObject, Collection<Object> mappedObjects);

  /**
   * This gives an abbreviated name using this object's own class' name, without package qualification,
   * followed by a space separated list of <tt>field:value</tt> pairs.
   */
  @Override
  public String toString()
  {
    StringBuffer result = new StringBuffer(super.toString());
    result.append(" (domain: " + domain + ")");
    result.append(" (mapping: " + mapping + ")");

    return result.toString();
  }
}
