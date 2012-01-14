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
import java.util.Collections;

import org.eclipse.emf.common.command.AbstractCommand;
import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.command.StrictCompoundCommand;
import org.eclipse.emf.edit.command.CommandParameter;
import org.eclipse.emf.mapping.Mapping;
import org.eclipse.emf.mapping.MappingPlugin;
import org.eclipse.emf.mapping.domain.MappingDomain;


/**
 * The create mapping command creates a new mapping in a {@link MappingDomain} 
 * from a set of the domain's input and output objects.
 */
public class CreateMappingCommand extends AbstractCommand 
{
  /**
   * @deprecated - use MappingDomain.ENABLE_MULTIPLE_INPUTS
   */
  @Deprecated
  public static final int ENABLE_MULTIPLE_INPUTS = 0x0001;
  /**
   * @deprecated - use MappingDomain.ENABLE_MULTIPLE_OUTPUTS
   */
  @Deprecated
  public static final int ENABLE_MULTIPLE_OUTPUTS = 0x0002;
  /**
   * @deprecated - use MappingDomain.ENABLE_MULTIPLE_INPUT_MAPPINGS
   */
  @Deprecated
  public static final int ENABLE_MAPPED_INPUTS = 0x0004;
  /**
   * @deprecated - use MappingDomain.ENABLE_MULTIPLE_OUTPUT_MAPPINGS
   */
  @Deprecated
  public static final int ENABLE_MAPPED_OUTPUTS = 0x0008;
  /**
   * @deprecated - use MappingDomain.ENABLE_INCOMPATIBLE_METAOBJECTS
   */
  @Deprecated
  public static final int ENABLE_INCOMPATIBLE_METAOBJECTS = 0x0010;
  /**
   * @deprecated - use MappingDomain.ENABLE_INCOMPATIBLE_TYPE_CLASSIFIERS
   */
  @Deprecated
  public static final int ENABLE_INCOMPATIBLE_TYPE_CLASSIFIERS = 0x0020;
  /**
   * @deprecated - use MappingDomain.ENABLE_EMPTY_INPUTS
   */
  @Deprecated
  public static final int ENABLE_EMPTY_INPUTS = 0x0040;
  /**
   * @deprecated - use MappingDomain.ENABLE_EMPTY_OUTPUTS
   */
  @Deprecated
  public static final int ENABLE_EMPTY_OUTPUTS = 0x0080;
  /**
   * @deprecated - use MappingDomain.ENABLE_UNMAPPED_PARENTS
   */
  @Deprecated
  public static final int ENABLE_UNMAPPED_PARENTS = 0x0100;
  /**
   * @deprecated - use MappingDomain.ENABLE_ALL
   */
  @Deprecated
  public static final int ENABLE_ALL = 0xFFFF;

  /**
   * This creates a command that creates a new mapping involving the given domain's collection of input and output objects.
   */
  public static Command create(MappingDomain domain, Collection<?> collection)
  {
    return 
      domain.createCommand
        (CreateMappingCommand.class, 
         new CommandParameter(domain.getMappingRoot(), null, collection));
  }

  /**
   * This creates a command that creates a new mapping between the given input and output.
   */
  public static Command create(MappingDomain domain, Object input, Object output)
  {
    Collection<Object> collection = new ArrayList<Object>();
    collection.add(input);
    collection.add(output);
    return create(domain, collection);
  }
 
  /**
   * This creates a command that creates a new mapping with the given collections of inputs and outputs.
   */
  public static Command create(MappingDomain domain, Collection<?> inputs, Collection<?> outputs)
  {
    Collection<Object> collection = new ArrayList<Object>();
    collection.addAll(inputs);
    collection.addAll(outputs);
    return create(domain, collection);
  }

  /**
   * This creates a command that creates a new mapping with the given collection of inputs and output.
   */
  public static Command create(MappingDomain domain, Collection<?> inputs, Object output)
  {
    Collection<Object> collection = new ArrayList<Object>();
    collection.addAll(inputs);
    collection.add(output);
    return create(domain, collection);
  }

  /**
   * This creates a command that creates a new mapping with the given input and collection of outputs.
   */
  public static Command create(MappingDomain domain, Object input, Collection<?> outputs)
  {
    Collection<Object> collection = new ArrayList<Object>();
    collection.add(input);
    collection.addAll(outputs);
    return create(domain, collection);
  }

  /**
   * This caches the label.
   */
  protected static final String LABEL = MappingPlugin.getPlugin().getString("_UI_CreateMappingCommand_label");

  /**
   * This cachaes the description.
   */
  protected static final String DESCRIPTION = MappingPlugin.getPlugin().getString("_UI_CreateMappingCommand_description");

  /**
   * This keeps track of the mapping domain in which the command operates.
   */
  protected MappingDomain domain;

  /**
   * This keeps track of the input objects that are to be mapped.
   */
  protected Collection<?> inputs;

  /**
   * This keeps track of the output objects that are to be mapped.
   */
  protected Collection<?> outputs;

  /**
   * This is set during {@link #execute} to record the new mapping that is created.
   */
  protected Mapping newMapping;

  /**
   * This is set during {@link #execute} to record the command used to add the newly created mapping to the mapping root.
   */
  protected Command subcommand;

  /**
   * @deprecated
   */
  @Deprecated
  public CreateMappingCommand(MappingDomain domain, Collection<?> collection, int enablementFlags)
  {
    this(domain, collection);
  }

  /**
   * This creates a command that creates a new mapping involving the given domain's collection of input and output objects.
   */
  public CreateMappingCommand(MappingDomain domain, Collection<?> collection)
  {
    super(LABEL, DESCRIPTION);

    this.domain = domain;

    ArrayList<Object> newInputs = new ArrayList<Object>();
    ArrayList<Object> newOutputs = new ArrayList<Object>();
    inputs = newInputs;
    outputs = newOutputs;
    for (Object object : collection)
    {
      if (domain.getMappingRoot().isInputObject(object))
      {
        newInputs.add(object);
      }
      else if (domain.getMappingRoot().isOutputObject(object))
      {
        newOutputs.add(object);
      }
      else
      {
        inputs = outputs = null;
        break;
      }
    }
  }

  @Override
  protected boolean prepare() 
  {
    boolean result = 
      domain != null && 
      inputs != null &&
      outputs != null && 
      domain.getMappingRoot().canCreateMapping(inputs, outputs, null);

    return result;
  }

  public void execute()
  {
    newMapping = domain.getMappingRoot().createMapping(inputs, outputs);

    StrictCompoundCommand subcommands = new StrictCompoundCommand();
    subcommands.appendAndExecute(AddMappingCommand.create(domain, newMapping));
    subcommand = subcommands.unwrap();
  }

  @Override
  public void undo() 
  {
    //domain.getMappingRoot().removeMapping(newMapping);
    subcommand.undo();
  }

  public void redo()
  {
    subcommand.redo();
  }

  @Override
  public Collection<?> getResult() 
  {
    return Collections.singleton(newMapping);
  }

  @Override
  public Collection<?> getAffectedObjects()
  {
    return Collections.singleton(newMapping);
  }

  @Override
  public void dispose()
  {
    if (subcommand != null)
    {
      subcommand.dispose();
    }
    super.dispose();
  }

  @Override
  public String getLabel()
  {
    if (inputs == null  || inputs.isEmpty() || outputs == null || outputs.isEmpty())
    {
      return MappingPlugin.getPlugin().getString("_UI_CreateMappingCommand_onesided_label");
    }
    else
    {
      return super.getLabel();
    }
  }

  @Override
  public String getDescription()
  {
    if (inputs == null || inputs.isEmpty() || outputs == null || outputs.isEmpty())
    {
      return MappingPlugin.getPlugin().getString("_UI_CreateMappingCommand_onesided_description");
    }
    else
    {
      return super.getDescription();
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
    result.append(" (inputs: " + inputs + ")");
    result.append(" (outputs: " + outputs + ")");
    result.append(" (newMapping: " + newMapping + ")");
    result.append(" (subcommand: " + subcommand + ")");

    return result.toString();
  }
}
