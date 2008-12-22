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
 * $Id: PersistentCommandStack.java,v 1.7 2008/12/22 14:26:20 emerks Exp $
 */
package org.eclipse.emf.mapping.command;


import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

import org.eclipse.emf.common.command.BasicCommandStack;
import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.edit.command.CommandParameter;
import org.eclipse.emf.edit.command.DragAndDropCommand;
import org.eclipse.emf.mapping.Mapping;
import org.eclipse.emf.mapping.MappingRoot;
import org.eclipse.emf.mapping.domain.MappingDomain;


/**
 * This implementation of a command stack records the command class and command parameter used to create each command.
 * This information can be used to create and execute all the commands again in a different session.
 */
public class PersistentCommandStack extends BasicCommandStack
{
  /**
   * This keeps track of the mapping between commands and their {@link CommandCreationRecord}s.
   */
  protected HashMap<Command, CommandCreationRecord> commandCreationMap = new HashMap<Command, CommandCreationRecord>();

  protected MappingDomain domain; 
  protected ClassLoader classLoader;
  protected String encoding;

  /**
   * This constructs and instance of a command stack that records commands using a persistent encoding.
   */
  public PersistentCommandStack(ClassLoader classLoader)
  {
    super();
    this.classLoader = classLoader;
  }

  /**
   * This is called by the mapping domain whenever a command (that may subsequently be executed) is created.
   */
  public void handleCreateCommand(Class<? extends Command> commandClass, CommandParameter commandParameter, Command command)
  {
    // Just remember it; it's encoded later during execution, which is more efficient.
    //
    CommandCreationRecord commandCreationRecord = new CommandCreationRecord(commandClass, commandParameter);
    commandCreationMap.put(command, commandCreationRecord);
  }

  /**
   * You can overide this to create your own type of encoder.
   */
  protected Encoder createEncoder()
  {
    return new Encoder();
  }

  /**
   * You can overide this to create your own type of decoder.
   */
  protected Decoder createDecoder(MappingRoot mappingRoot, ResourceSet resourceSet, ClassLoader classLoader)
  {
    return new Decoder(mappingRoot, resourceSet, classLoader);
  }

  /**
   * This override of execute calls {@link CommandCreationRecord#encode} just before normal execution by super.
   */
  @Override
  public void execute(Command command)
  {
    CommandCreationRecord commandCreationRecord = commandCreationMap.get(command);
    if (commandCreationRecord != null)
    {
      // Do the encoding.
      //
      commandCreationRecord.encode(createEncoder());
      System.out.println("Executing Encoded Command: " + commandCreationRecord.getEncoding());
    }
    else
    {
      System.out.println("Executing Unregistered Command: " + command);
      Thread.dumpStack();
    }

    super.execute(command);

    if (encoding != null)
    {
      executeEncoding();
    }
  }

  public String getEncoding()
  { 
    // Record the records for the executed commands on the stack.
    //
    Collection<CommandCreationRecord> commandCreationRecordList = new ArrayList<CommandCreationRecord>();

    for (int i = 0; i <= top; ++i)
    {
      CommandCreationRecord commandCreationRecord = commandCreationMap.get(commandList.get(i));
      if (commandCreationRecord == null)
      {
        System.out.println("UnregisteredCommand:" + commandList.get(i));
        break;
      }
      else if (commandCreationRecord.getCommandClass() != RestoreInitialStateCommand.class)
      {
        commandCreationRecordList.add(commandCreationRecord);
      }
    }

    Encoder encoder = createEncoder();
    encoder.encode(commandCreationRecordList);
    return encoder.toString();
  }

  public void setEncoding(MappingDomain domain, String encoding)
  {
    this.domain = domain;
    this.encoding = encoding;
  }

  protected void executeEncoding() 
  {
    Decoder decoder = createDecoder(domain.getMappingRoot(), domain.getResourceSet(), classLoader);
    decoder.setEncoding(encoding);
    encoding = null;
    @SuppressWarnings("unchecked")
    Collection<CommandCreationRecord> commandCreationRecordList = (Collection<CommandCreationRecord>)decoder.decode();
    if (commandCreationRecordList != null)
    {
      boolean failure = false;
      for (CommandCreationRecord commandCreationRecord : commandCreationRecordList)
      {
        commandCreationRecord.decode(decoder);

        Command command =domain.createCommand(commandCreationRecord.getCommandClass(), commandCreationRecord.getCommandParameter());
        if (command.canExecute())
        {
          System.out.println("Re-executed Command: " + command);
          execute(command);
        }
        else
        {
          System.out.println("Not! Executing Command: " + command);
          command.dispose();
          failure = true;
          break;
        }
      }

      if (!failure)
      {
        saveIsDone();
      }
    }
  }

  public static class Encoder
  {
    protected StringBuffer buffer;

    public Encoder()
    {
      this.buffer = new StringBuffer();
    }

    public Encoder(StringBuffer buffer)
    {
      this.buffer = buffer;
    }

    public void setBuffer(StringBuffer buffer)
    {
      this.buffer = buffer;
    }

    public void encode(int value)
    {
      buffer.append("<int value=\"" + value + "\"/>");
    }

    public void encode(float value)
    {
      buffer.append("<float value=\"" + value + "\"/>");
    }

    public void encode(Object object)
    {
      if (object == null)
      {
        buffer.append("<null/>");
       }
      else if (object instanceof Class<?>)
      {
        @SuppressWarnings("unchecked")
        Class<? extends Command> theClass = (Class<? extends Command>)object;
        buffer.append("<class name=\"" + theClass.getName() + "\"/>");
      }
      else if (object instanceof CommandParameter)
      {
        CommandParameter commandParameter = (CommandParameter)object;
        buffer.append("<command-parameter>");
        encode(commandParameter.getOwner());
        encode(commandParameter.getFeature());
        encode(commandParameter.getCollection());
        encode(commandParameter.getValue());
        encode(commandParameter.getIndex());
        buffer.append("</command-parameter>");
      }
      else if (object instanceof EObject)
      {
        if (object instanceof Mapping)
        {
          Mapping mapping = (Mapping)object;
          MappingRoot mappingRoot = mapping.getMappingRoot();

          if (mappingRoot != null)
          {
            Collection<?> mappedObjects = mapping.getMappedObjects();
            Collection<?> collection = mappingRoot.getExactMappings(mappedObjects);

            // If there is more than one exact match, we must get an index number;
            //
            int index = 0;
            if (collection.size() > 1)
            {
              // Iterate over the whole tree to do this.
              //
              for (TreeIterator<?> mappings = mappingRoot.treeIterator(); mappings.hasNext(); )
              {
                Object otherMapping = mappings.next();
                if (otherMapping == mapping)
                {
                  break;
                }
                else if (collection.contains(otherMapping))
                {
                  ++index;
                }
              }
            }
            buffer.append("<mapping>");
            encode(mappedObjects);
            encode(index);
            buffer.append("</mapping>");
          }
          else
          {
            buffer.append("<null/>");
          }
        }
        else
        {
          EObject refObject = (EObject)object;
          //if (resource != null)
          {
            //Resource resource = EcoreUtil.getURI(refObject).; FIX:::
            String href = EcoreUtil.getURI(refObject).toString();
            buffer.append("<ref-object href=\"" + href + "\"/>");
          }
          /*else if (refObject.refPackage() != null && refObject instanceof EStructuralFeature)
          {
            EPackage refPackage = refObject.refPackage();
            buffer.append("<ref_structural-feature ");
            buffer.append("package-name=\"" + refPackage.refNamespaceURI() + "\" ");
            buffer.append("meta-object-name=\"" + refObject.refContainer().refName() + "\" ");
            buffer.append("meta-feature-name=\"" + refObject.refName() + "\"");
            buffer.append("/>");
          }
          else
          {
            buffer.append("<null/>");
          }*/
        }
      }
      else if (object instanceof Collection<?>)
      {
        Collection<?> collection = (Collection<?>)object;
        buffer.append("<collection>");
        for (Object member : collection)
        {
          encode(member);
        }
        buffer.append("</collection>");
      }
      else if (object instanceof String)
      {
        buffer.append("<string value=\"" + (String)object + "\"/>");
      }
      else if (object instanceof DragAndDropCommand.Detail)
      {
        DragAndDropCommand.Detail detail = (DragAndDropCommand.Detail)object;
        buffer.append("<drag-and-drop-detail>");
        encode(detail.location);
        encode(detail.operations);
        encode(detail.operation);
        buffer.append("</drag-and-drop-detail>");
      }
      else if (object instanceof CommandCreationRecord)
      {
        CommandCreationRecord commandCreationRecord = (CommandCreationRecord)object;
        buffer.append("<command-creation-record>");
        buffer.append(commandCreationRecord.getEncoding());
        buffer.append("</command-creation-record>");
      }
      else 
      {
        buffer.append("<unknown>" + object.toString() + "</unknown>");
      }
    }

    @Override
    public String toString()
    {
      return buffer.toString();
    }
  }
  
  public static class Decoder
  {
    protected String string;
    protected int index = 0;
    protected ResourceSet resourceSet;
    protected ClassLoader classLoader;
    protected MappingRoot mappingRoot;

    public Decoder(MappingRoot mappingRoot, ResourceSet resourceSet, ClassLoader classLoader)
    {
      this.mappingRoot = mappingRoot;
      this.resourceSet = resourceSet;
      this.classLoader = classLoader;
    }

    public void setEncoding(String encoding)
    {
      string = encoding;
      index = 0;
    }

    protected void skipWhitespace()
    {
      while (index < string.length() && Character.isWhitespace(string.charAt(index))) 
      { 
        ++index; 
      }
    }

    public Object decode()
    {
      Object result = null;

      skipWhitespace();
      if (index < string.length() && string.charAt(index) == '<')
      {
        ++index;
        skipWhitespace();
        int keyStartIndex = index;
        while (index < string.length() && 
                 !Character.isWhitespace(string.charAt(index)) && 
                 string.charAt(index) != '/' && 
                 string.charAt(index) != '>')
        {
          ++index;
        }
        String key = string.substring(keyStartIndex, index);

        if (key.equals("null"))
        {
          index = string.indexOf(">", index) + 1;
        }
        else if (key.equals("class"))
        {
          index = string.indexOf("\"", index);
          int classIndex = ++index;
          index = string.indexOf("\"", index);

          String className = string.substring(classIndex, index);
          index = string.indexOf(">", index) + 1;

          try
          {
            result = classLoader.loadClass(className);
          }
          catch (Exception exception)
          {
            exception.printStackTrace();
          }
        }
        else if (key.equals("drag-and-drop-detail"))
        {
          index = string.indexOf(">", index) + 1;

          float location = ((Float)decode()).floatValue();
          int operations = (Integer)decode();
          int operation = (Integer)decode();

          index = string.indexOf(">", index) + 1;

          result = new DragAndDropCommand.Detail(location, operations, operation);
        }
        else if (key.equals("command-parameter"))
        {
          index = string.indexOf(">", index) + 1;

          Object owner = decode();
          Object feature = decode();
          Collection<?> collection = (Collection<?>)decode();
          Object value = decode();
          int theIndex = (Integer)decode();

          index = string.indexOf(">", index) + 1;

          if (collection == null)
          {
            result = new CommandParameter(owner, feature, value, theIndex);
          }
          else
          {
            result = new CommandParameter(owner, feature, collection, theIndex);
          }
        }
        else if (key.equals("command-creation-record"))
        {
          index = string.indexOf(">", index) + 1;

          int startIndex = index;

          index = string.indexOf("</command-creation-record>", index);
          index = string.indexOf(">", index) + 1;

          result = new CommandCreationRecord(string.substring(startIndex, index));
        }
        else if (key.equals("ref-object"))
        {
          index = string.indexOf("\"", index);
          int hrefStartIndex = ++index;
          index = string.indexOf("\"", index);

          String href = string.substring(hrefStartIndex, index);

          index = string.indexOf(">", index) + 1;

          result = resourceSet.getEObject(URI.createURI(href), true);
        }
        else if (key.equals("ref_structural-feature"))
        {
          index = string.indexOf("\"", index);
          int startIndex = ++index;
          index = string.indexOf("\"", index);
          String packageURI = string.substring(startIndex, index);

          index = string.indexOf("\"", index + 1);
          startIndex = ++index;
          index = string.indexOf("\"", index);
          String metaObjectName = string.substring(startIndex, index);

          index = string.indexOf("\"", index + 1);
          startIndex = ++index;
          index = string.indexOf("\"", index);
          String metaFeatureName = string.substring(startIndex, index);

          index = string.indexOf(">", index) + 1;

          EPackage refPackage = EPackage.Registry.INSTANCE.getEPackage(packageURI);
          EClass metaObject = (EClass)refPackage.getEClassifier(metaObjectName);
          EObject metaFeature = metaObject.getEStructuralFeature(metaFeatureName);
          result = metaFeature;
        }
        else if (key.equals("mapping"))
        {
          index = string.indexOf(">", index) + 1;
          Collection<?> mappedObjects = (Collection<?>)decode();
          int value = (Integer)decode();
          index = string.indexOf(">", index) + 1;

          Collection<?> collection = mappingRoot.getExactMappings(mappedObjects);
          if (collection.size() > 1)
          {
            // Iterate over the whole tree to do this.
            //
            for (TreeIterator<?> mappings = mappingRoot.treeIterator(); mappings.hasNext(); )
            {
              Object mapping = mappings.next();
              if (collection.contains(mapping))
              {
                if (value == 0)
                {
                  result = mapping;
                  break;
                }
                else
                {
                  --value;
                }
              }
            }
          }

          if (result == null && !collection.isEmpty())
          {
            result = collection.iterator().next();
          }
        }
        else if (key.equals("collection"))
        {
          Collection<Object> collection = new ArrayList<Object>();

          index = string.indexOf(">", index) + 1;

          while (index < string.length() && Character.isWhitespace(string.charAt(index)))
          {
            ++index;
          }
  
          while (index < string.length() && string.indexOf("</collection>", index) != index)
          {
            Object object = decode();
            collection.add(object);
            while (index < string.length() && Character.isWhitespace(string.charAt(index)))
            {
              ++index;
            }
          }

          if (index < string.length())
          {
            index += "</collection>".length();
          }

          result = collection;
        }
        else if (key.equals("string"))
        {
          index = string.indexOf("\"", index);
          int startIndex = ++index;
          index = string.indexOf("\"", index);
          String value = string.substring(startIndex, index);

          index = string.indexOf(">", index) + 1;

          result = value;
        }
        else if (key.equals("int"))
        {
          index = string.indexOf("\"", index);
          int startIndex = ++index;
          index = string.indexOf("\"", index);
          String value = string.substring(startIndex, index);

          index = string.indexOf(">", index) + 1;

          result = Integer.valueOf(value);
        }
        else if (key.equals("float"))
        {
          index = string.indexOf("\"", index);
          int startIndex = ++index;
          index = string.indexOf("\"", index);
          String value = string.substring(startIndex, index);

          index = string.indexOf(">", index) + 1;

          result = Float.valueOf(value);
        }
        else 
        {
          index = string.indexOf("</unknown>", index) + "</unknown>".length();
        }

        // System.out.println("Decoded key= '" + key + "':'" + string.substring(resultStartIndex, index) + "'");

        skipWhitespace();
      }

      return result;
    }

    @Override
    public String toString()
    {
      return index < string.length() ? string.substring(index) : "";
    }
  }

  /**
   * This records and encodes the command class and command parameter.
   */
  public static class CommandCreationRecord
  {
    Class<? extends Command> commandClass;
    CommandParameter commandParameter;
    String encoding;

    public CommandCreationRecord(Class<? extends Command> commandClass, CommandParameter commandParameter)
    {
      this.commandClass = commandClass;
      this.commandParameter = commandParameter;
      this.encoding = null;
    }

    public CommandCreationRecord(String encoding)
    {
      this.encoding = encoding;
    }

    public CommandParameter getCommandParameter()
    {
      return commandParameter;
    }

    public String getEncoding()
    {
      return encoding;
    }

    public Class<? extends Command> getCommandClass()
    {
      return commandClass;
    }

    public void encode(Encoder encoder)
    {
      encoder.encode(commandClass);
      encoder.encode(commandParameter);
      encoding = encoder.toString();
    }

    @SuppressWarnings("unchecked")
    public void decode(Decoder decoder)
    {
      decoder.setEncoding(encoding);
      commandClass = (Class<? extends Command>)decoder.decode();
      commandParameter = (CommandParameter)decoder.decode();
    }
    
    @Override
    public String toString()
    {
      return 
        "CommandCreationRecord { commandClass=" + 
          (commandClass == null ? "null" : commandClass.getName()) + 
          ", commandParameter=" + commandParameter + " }";
    }
  }
}
