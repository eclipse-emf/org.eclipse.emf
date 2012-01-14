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
package org.eclipse.emf.mapping.domain;


import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.command.CommandStack;
import org.eclipse.emf.common.command.UnexecutableCommand;
import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationWrapper;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.ENamedElement;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.ETypedElement;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.edit.command.AddCommand;
import org.eclipse.emf.edit.command.CommandParameter;
import org.eclipse.emf.edit.command.CopyToClipboardCommand;
import org.eclipse.emf.edit.command.CreateCopyCommand;
import org.eclipse.emf.edit.command.DragAndDropCommand;
import org.eclipse.emf.edit.command.InitializeCopyCommand;
import org.eclipse.emf.edit.command.MoveCommand;
import org.eclipse.emf.edit.command.OverrideableCommand;
import org.eclipse.emf.edit.command.PasteFromClipboardCommand;
import org.eclipse.emf.edit.command.RemoveCommand;
import org.eclipse.emf.edit.command.ReplaceCommand;
import org.eclipse.emf.edit.command.SetCommand;
import org.eclipse.emf.edit.domain.AdapterFactoryEditingDomain;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.emf.edit.domain.IEditingDomainProvider;
import org.eclipse.emf.edit.provider.ComposedAdapterFactory;
import org.eclipse.emf.edit.provider.IItemLabelProvider;
import org.eclipse.emf.edit.provider.ITreeItemContentProvider;
import org.eclipse.emf.mapping.Mapping;
import org.eclipse.emf.mapping.MappingPlugin;
import org.eclipse.emf.mapping.MappingRoot;
import org.eclipse.emf.mapping.command.AddOverrideCommand;
import org.eclipse.emf.mapping.command.CopyToClipboardOverrideCommand;
import org.eclipse.emf.mapping.command.CreateCopyOverrideCommand;
import org.eclipse.emf.mapping.command.CreateMappingCommand;
import org.eclipse.emf.mapping.command.DragAndDropOverrideCommand;
import org.eclipse.emf.mapping.command.InitializeCopyOverrideCommand;
import org.eclipse.emf.mapping.command.PasteFromClipboardOverrideCommand;
import org.eclipse.emf.mapping.command.PersistentCommandStack;
import org.eclipse.emf.mapping.command.RemoveOverrideCommand;
import org.eclipse.emf.mapping.command.SetOverrideCommand;
import org.eclipse.emf.mapping.provider.MappedObjectItemProvider;


/**
 * This class implements {@link MappingDomain} interface using two strategies. 
 * The primary strategy is to delegate to an adapter that is produced by a {@link ComposedAdapterFactory} that comprises 
 * an adapter factory for the mapping model, 
 * an adapter factory for the top domain,
 * and an adapter factory for the bottom domain (which may be the same as the one for the top domain).
 * The secondary strategy is to use table lookup, 
 * which provides an easy way to supply the input/ouput type correspondence information from a plugin configuration file, 
 * when the top and bottom domains are of different type.
 */
public class AdapterFactoryMappingDomain extends AdapterFactoryEditingDomain implements MappingDomain
{
  public static final int LABEL_UPPER = 1;
  public static final int LABEL_LOWER = 2;
  public static final int LABEL_MIXED = 3;
  public static final char LABEL_NO_SEPARATOR = '\0';

  protected HashMap<String, String> topToBottomTypeTable = new HashMap<String, String>();
  protected HashMap<String, String> bottomToTopTypeTable = new HashMap<String, String>();

  protected char topLabelSeparator = LABEL_NO_SEPARATOR;
  protected char bottomLabelSeparator = LABEL_NO_SEPARATOR;

  protected int topLabelCase = LABEL_MIXED;
  protected int bottomLabelCase = LABEL_MIXED;

  protected List<String> topLabelLongForms = new ArrayList<String>();
  protected List<String> bottomLabelLongForms = new ArrayList<String>();

  protected List<String> topLabelShortForms = new ArrayList<String>();
  protected List<String> bottomLabelShortForms = new ArrayList<String>();

  protected char packageTypeSeparator = '|';

  protected MappingRoot mappingRoot;
  protected boolean isSameEditingDomainAdapterFactory;
  protected PersistentCommandStack persistentCommandStack;

  protected int mappingEnablementFlags = 0;

  public AdapterFactoryMappingDomain
    (AdapterFactory mappingDomainAdapterFactory,
     AdapterFactory editingDomainAdapterFactory,
     CommandStack commandStack) 
  {
    this(mappingDomainAdapterFactory, editingDomainAdapterFactory, editingDomainAdapterFactory, commandStack);
  }

  public AdapterFactoryMappingDomain
    (AdapterFactory mappingDomainAdapterFactory,
     AdapterFactory editingDomainAdapterFactory,
     CommandStack commandStack,
     ResourceSet resourceSet) 
  {
    this(mappingDomainAdapterFactory, editingDomainAdapterFactory, editingDomainAdapterFactory, commandStack, resourceSet);
  }

  public AdapterFactoryMappingDomain
    (AdapterFactory mappingDomainAdapterFactory, 
     AdapterFactory topDomainAdapterFactory, 
     AdapterFactory bottomDomainAdapterFactory,
     CommandStack commandStack)
  {
    super(null, commandStack);

    adapterFactory = createComposedAdapterFactory(mappingDomainAdapterFactory, topDomainAdapterFactory, bottomDomainAdapterFactory);

    isSameEditingDomainAdapterFactory = (topDomainAdapterFactory == bottomDomainAdapterFactory);

    if (commandStack instanceof PersistentCommandStack)
    {
      persistentCommandStack = (PersistentCommandStack)commandStack;
    }
  }

  public AdapterFactoryMappingDomain
    (AdapterFactory mappingDomainAdapterFactory, 
     AdapterFactory topDomainAdapterFactory, 
     AdapterFactory bottomDomainAdapterFactory,
     CommandStack commandStack,
     ResourceSet resourceSet)
  {
    super(null, commandStack, resourceSet);

    adapterFactory = createComposedAdapterFactory(mappingDomainAdapterFactory, topDomainAdapterFactory, bottomDomainAdapterFactory);

    isSameEditingDomainAdapterFactory = (topDomainAdapterFactory == bottomDomainAdapterFactory);

    if (commandStack instanceof PersistentCommandStack)
    {
      persistentCommandStack = (PersistentCommandStack)commandStack;
    }
  }

  protected class LabelUpdatePropagatingComposedAdapterFactory extends ComposedAdapterFactory
  {
    public LabelUpdatePropagatingComposedAdapterFactory(AdapterFactory adapterFactory)
    {
      super(adapterFactory);
    }

    //public void fireNotifyChanged(Object object, int eventType, Object feature, Object oldValue, Object newValue, int index)
    @Override
    public void fireNotifyChanged(Notification note)
    {
      super.fireNotifyChanged(note);
      // if (!viewerUpdate.isStructuralChange())
      {
        if (parentAdapterFactory != null && getMappingRoot() != null && note.getNotifier() != null)
        {
          for (Mapping mapping : getMappingRoot().getMappings(note.getNotifier()))
          {
            parentAdapterFactory.fireNotifyChanged(new NotificationWrapper(mapping, note));

            //FB Won't compile with VAJ or NO_JIKES 
            //FB Object adapter = adapterFactory.adapt(mapping);
            ITreeItemContentProvider treeItemContentProvider = 
              (ITreeItemContentProvider)getAdapterFactory().adapt(mapping, ITreeItemContentProvider.class);

            if (treeItemContentProvider != null)
            {
              for (Iterator<?> children = treeItemContentProvider.getChildren(mapping).iterator(); children.hasNext(); )
              {
                Object child = children.next();
                if (child instanceof MappedObjectItemProvider &&
                      ((MappedObjectItemProvider)child).getMappedObject() == note.getNotifier())
                {
                  parentAdapterFactory.fireNotifyChanged(new NotificationWrapper(child, note));
                }
              }
            }
          }
        }
      }
    }
  }

  public static class EditingDomainProvidingComposedAdapterFactory extends ComposedAdapterFactory implements IEditingDomainProvider
  {
    protected EditingDomain editingDomain;

    /**
     */
    public EditingDomainProvidingComposedAdapterFactory(AdapterFactory adapterFactory, EditingDomain editingDomain)
    {
      super(adapterFactory);
      this.editingDomain = editingDomain;
      addAdapterFactory(adapterFactory);
    }

    public EditingDomainProvidingComposedAdapterFactory(AdapterFactory [] adapterFactories, EditingDomain editingDomain)
    {
      super(adapterFactories);
      this.editingDomain = editingDomain;
    }

    public EditingDomainProvidingComposedAdapterFactory(Collection<? extends AdapterFactory> adapterFactories, EditingDomain editingDomain)
    {
      super(adapterFactories);
      this.editingDomain = editingDomain;
    }

    public EditingDomain getEditingDomain()
    {
      return editingDomain;
    }
  }

  protected ComposedAdapterFactory createComposedAdapterFactory
    (AdapterFactory mappingDomainAdapterFactory,
     AdapterFactory topDomainAdapterFactory,
     AdapterFactory bottomDomainAdapterFactory)
  {
    AdapterFactory[] adapterFactories;

    if (topDomainAdapterFactory == bottomDomainAdapterFactory)
    {
      adapterFactories = 
        new AdapterFactory[] 
        {
          mappingDomainAdapterFactory, 
          new LabelUpdatePropagatingComposedAdapterFactory(topDomainAdapterFactory)
        };
    }
    else
    {
      adapterFactories = 
        new AdapterFactory[] 
        {
          mappingDomainAdapterFactory, 
          new LabelUpdatePropagatingComposedAdapterFactory(topDomainAdapterFactory), 
          new LabelUpdatePropagatingComposedAdapterFactory(bottomDomainAdapterFactory)
        };
    }

    return createComposedAdapterFactory(adapterFactories);
  }

  protected ComposedAdapterFactory createComposedAdapterFactory(AdapterFactory[] adapterFactories)
  {
    return new EditingDomainProvidingComposedAdapterFactory(adapterFactories, this);
  }
  
  public String getOutputName(String inputName) 
  {
    if (isSameEditingDomainAdapterFactory)
    {
      return inputName;
    }
    else if (mappingRoot.isTopToBottom())
    {
      return 
        convertName
          (inputName, 
           topLabelSeparator, 
           topLabelCase, 
           topLabelShortForms, 
           topLabelLongForms,
           bottomLabelSeparator, 
           bottomLabelCase, 
           bottomLabelShortForms, 
           bottomLabelLongForms);
    }
    else
    {
      return 
        convertName
          (inputName, 
           bottomLabelSeparator, 
           bottomLabelCase, 
           bottomLabelShortForms, 
           bottomLabelLongForms,
           topLabelSeparator, 
           topLabelCase, 
           topLabelShortForms, 
           topLabelLongForms);
    }
  }

  public List<String> parseOutputName(String outputName) 
  {
    if (mappingRoot.isTopToBottom())
    {
      return parseName(outputName, bottomLabelSeparator);
    }
    else
    {
      return parseName(outputName, topLabelSeparator);
    }
  }

  public List<String> parseInputName(String inputName) 
  {
    if (mappingRoot.isTopToBottom())
    {
      return parseName(inputName, topLabelSeparator);
    }
    else
    {
      return parseName(inputName, bottomLabelSeparator);
    }
  }

  public String getName(Object object)
  {
    // If eCore, return the ENamedElement name
    //
    if (object instanceof ENamedElement)
    {
      return ((ENamedElement)object).getName();
    }

    // If there is an adapter of the correct type, return the item label
    //
    IItemLabelProvider itemLabelProvider = (IItemLabelProvider)adapterFactory.adapt(object, IItemLabelProvider.class);
    return
      itemLabelProvider != null ?
       itemLabelProvider.getText(object) :
       null;
  }

  public void setName(Object object, String name)
  {
    // If eCore, set the ENamedElement name
    //
    if (object instanceof ENamedElement)
    {
      ((ENamedElement)object).setName(name);
    }
  }

  public EObject getOutputMetaObject(EObject inputMetaObject) 
  {
    if (isSameEditingDomainAdapterFactory)
    {
      return inputMetaObject;
    }
    else
    {
      return getCorrespondingType(inputMetaObject, mappingRoot.isTopToBottom() ? topToBottomTypeTable : bottomToTopTypeTable);
    }
  } 

  public MappingRoot getMappingRoot() 
  {
    return mappingRoot;
  }      
   
  public void setMappingRoot(MappingRoot mappingRoot) 
  {
    if (this.mappingRoot != mappingRoot)
    {
      this.mappingRoot = mappingRoot;
    }
  } 

  /**
   * This default implementation returns null or the {@link ETypedElement} if the object is an {@link ETypedElement}.
   */
  public Object getTypeClassifier(Object mappedObject)
  {
    if (mappedObject instanceof ETypedElement)
    {
      return ((ETypedElement)mappedObject).getEType();
    }
    return null;
  }

  /**
   * This default implementation sets the {@link ETypedElement} if the object is an {@link ETypedElement}.
   */
  public void setTypeClassifier(Object mappedObject, Object typeClassifier)
  {
    if (mappedObject instanceof ETypedElement)
    {
      ((ETypedElement)mappedObject).setEType((EClassifier)typeClassifier);
    }
  }

  public Object getOutputTypeClassifier(Object inputType)
  {
    Object outputType = null;
    MappingRoot typeMappingRoot = mappingRoot.getTypeMappingRoot();
    if (typeMappingRoot != null)
    {
      Collection<? extends Mapping> mappings = typeMappingRoot.getMappings(inputType);
      if (mappings.size() == 1)
      {
        Mapping typeMapping = mappings.iterator().next();
        outputType = typeMapping.getOutputs().iterator().next();
      }
    }
    return outputType;
  }

  public int getMappingEnablementFlags()
  {
    return mappingEnablementFlags;
  }

  public void setMappingEnablementFlags(int mappingEnablementFlags)
  {
    this.mappingEnablementFlags = mappingEnablementFlags;
  }

  /**
   * This is called for every command created by the domain.
   */
  public void handleCreateCommand(Class<? extends Command> commandClass, CommandParameter commandParameter, Command command)
  {
    if (persistentCommandStack != null)
    {
      persistentCommandStack.handleCreateCommand(commandClass, commandParameter, command);
    }
  }

  @Override
  public Command createCommand(Class<? extends Command> commandClass, CommandParameter commandParameter)
  {
    // This turns a featureless set command into an operation to create a mapping.
    // This is invoked during drag and drop.
    //
    if (commandClass == SetCommand.class && 
          !(commandParameter.getOwner() instanceof Mapping) &&
          !(commandParameter.getOwner() instanceof MappedObjectItemProvider) &&
          commandParameter.getFeature() == null && 
          commandParameter.getValue() instanceof Collection<?>)
    {
      boolean inputToOutput = mappingRoot.isOutputObject(commandParameter.getOwner());
      boolean okay = true;
      for (Object object : (Collection<?>)commandParameter.getValue())
      {
        if (mappingRoot.isInputObject(object) != inputToOutput)
        {
          okay = false;
        }
      }
      if (okay)
      {
        Collection<Object> mappedObjects = new ArrayList<Object>((Collection<?>)commandParameter.getValue());
        mappedObjects.add(commandParameter.getOwner());
        return CreateMappingCommand.create(this, mappedObjects);
      }
    }  
    else if (commandClass == RemoveCommand.class && commandParameter.getOwner() == null)
    {
      // If this is a RemoveCommand for a root input object, direct it to the MappingRoot.
      //
      Collection<?> collection = commandParameter.getCollection();
      if (mappingRoot.getMappedObjects().containsAll(collection))
      {
        commandParameter.setOwner(mappingRoot);
      }
    }

    if ((mappingRoot.isInputObject(commandParameter.getOwner()) ||
                mappingRoot.isOutputReadOnly() && mappingRoot.isOutputObject(commandParameter.getOwner())) && 
               (commandClass == AddCommand.class ||
                  commandClass == MoveCommand.class ||
                  commandClass == RemoveCommand.class ||
                  commandClass == ReplaceCommand.class ||
                  commandClass == SetCommand.class))
    {
      return UnexecutableCommand.INSTANCE;
    }
    else
    {
      Command result = super.createCommand(commandClass, commandParameter);
      handleCreateCommand(commandClass, commandParameter, result);
      return result;
    }
  }

  @Override
  public Command createOverrideCommand(OverrideableCommand command)
  {
    if (command instanceof AddCommand)
    {
      AddCommand addCommand = (AddCommand)command;
      return createAddOverrideCommand(addCommand);
    }
    else if (command instanceof RemoveCommand)
    {
      RemoveCommand removeCommand = (RemoveCommand)command;
      return createRemoveOverrideCommand(removeCommand);
    }
    else if (command instanceof SetCommand)
    {
      SetCommand setCommand = (SetCommand)command;
      return createSetOverrideCommand(setCommand);
    }
    else if (command instanceof ReplaceCommand)
    {
      ReplaceCommand replaceCommand = (ReplaceCommand)command;
      return createReplaceOverrideCommand(replaceCommand);
    }
    else if (command instanceof MoveCommand)
    {
      MoveCommand moveCommand = (MoveCommand)command;
      return createMoveOverrideCommand(moveCommand);
    }
    else if (command instanceof CreateCopyCommand)
    {
      CreateCopyCommand createCopyCommand = (CreateCopyCommand)command;
      return createCreateCopyOverrideCommand(createCopyCommand);
    }
    else if (command instanceof InitializeCopyCommand)
    {
      InitializeCopyCommand initializeCopyCommand = (InitializeCopyCommand)command;
      return createInitializeCopyOverrideCommand(initializeCopyCommand);
    }
    else if (command instanceof CopyToClipboardCommand)
    {
      CopyToClipboardCommand copyToClipboardCommand = (CopyToClipboardCommand)command;
      return createCopyToClipboardOverrideCommand(copyToClipboardCommand);
    }
    else if (command instanceof PasteFromClipboardCommand)
    {
      PasteFromClipboardCommand pasteFromClipboardCommand = (PasteFromClipboardCommand)command;
      return createPasteFromClipboardOverrideCommand(pasteFromClipboardCommand);
    }
    else if (command instanceof DragAndDropCommand)
    {
      DragAndDropCommand dragAndDropCommand = (DragAndDropCommand)command;
      return createDragAndDropOverrideCommand(dragAndDropCommand);
    }
    else
    {
      return null;
    }
  }

  protected Command createAddOverrideCommand(AddCommand addCommand)
  {
    if (!(addCommand.getOwner() instanceof Mapping))
    {
      return new AddOverrideCommand(this, addCommand);
    }

    return null;
  }

  protected Command createRemoveOverrideCommand(RemoveCommand removeCommand)
  {
    if (!(removeCommand.getOwner() instanceof Mapping))
    {
      return new RemoveOverrideCommand(this, removeCommand);
    }

    return null;
  }

  protected Command createSetOverrideCommand(SetCommand setCommand)
  {
    EStructuralFeature feature = setCommand.getFeature();
    return 
      feature instanceof EReference && ((EReference)feature).isContainment() && mappingRoot.isOutputObject(setCommand.getOwner()) ?
        new SetOverrideCommand(this, setCommand) :
        null;
  }

  protected Command createMoveOverrideCommand(MoveCommand moveCommand)
  {
    return null;
  }

  protected Command createReplaceOverrideCommand(ReplaceCommand replaceCommand)
  {
    return null;
  }

  protected Command createCreateCopyOverrideCommand(CreateCopyCommand createCopyCommand)
  {
    if (mappingRoot.isInputObject(createCopyCommand.getOwner()))
    {
      return new CreateCopyOverrideCommand(this, createCopyCommand);
    }

    return null;
  }

  protected Command createInitializeCopyOverrideCommand(InitializeCopyCommand initializeCopyCommand)
  {
    // If the owner and the copy are of the same type, we can proceed with the command, i.e. no override.
    // If not, we will skip this command by returning a do-nothing override.
    //
    if (!isSameEditingDomainAdapterFactory)
    {
      return new InitializeCopyOverrideCommand(this, initializeCopyCommand);
    } 

    return null;
  }

  protected Command createCopyToClipboardOverrideCommand(CopyToClipboardCommand copyToClipboardCommand)
  {
    if (!(copyToClipboardCommand instanceof CopyToClipboardOverrideCommand))
    {
      Collection<Object> inputObjects = new ArrayList<Object>();
      Collection<Object> nonInputObjects = new ArrayList<Object>();

      for (Object object : copyToClipboardCommand.getSourceObjects())
      {
        if (mappingRoot.isInputObject(object))
        {
          inputObjects.add(object);
        }
        else
        {
          nonInputObjects.add(object);
        }
      }

      if (inputObjects.size() > 0)
      {
        return new CopyToClipboardOverrideCommand(this, nonInputObjects, inputObjects);
      }
    }

    return null;
  }
      
  protected Command createPasteFromClipboardOverrideCommand(PasteFromClipboardCommand pasteFromClipboardCommand)
  {
    if (!isSameEditingDomainAdapterFactory && optimizeCopy && !(pasteFromClipboardCommand instanceof PasteFromClipboardOverrideCommand))
    {
      return new PasteFromClipboardOverrideCommand(this, pasteFromClipboardCommand);
    }
    return null;
  }

  protected Command createDragAndDropOverrideCommand(DragAndDropCommand dragAndDropCommand)
  {
    if (!isSameEditingDomainAdapterFactory && optimizeCopy && !(dragAndDropCommand instanceof DragAndDropOverrideCommand))
    {
      return new DragAndDropOverrideCommand(this, dragAndDropCommand);
    }
    return null;
  }

  protected EObject getCorrespondingType(EObject sourceType, HashMap<String, String> typeTable)
  {
    EObject result = null;
    EClassifier sourceClassifier = (EClassifier)sourceType;
    String sourceTypeName = sourceClassifier.getEPackage().getNsURI() + packageTypeSeparator + sourceClassifier.getName();
    String targetTypeName = typeTable.get(sourceTypeName);
        
    if (targetTypeName != null) 
    {
      int pos = targetTypeName.indexOf(packageTypeSeparator);
      if (pos != -1) 
      {
        String pkgName = targetTypeName.substring(0, pos);
        EPackage targetPkg = EPackage.Registry.INSTANCE.getEPackage(pkgName);
        targetTypeName = targetTypeName.substring(pos + 1, targetTypeName.length());
        result = targetPkg.getEClassifier(targetTypeName);
      } 
      else
      {
        throw 
          new RuntimeException
            (MappingPlugin.INSTANCE.getString
              ("_EXC_AdapterFactoryMappingDomain_getCorrespondingType_failed", 
               new Object [] { targetTypeName, String.valueOf(packageTypeSeparator) }));
      }
    }
        
    return result;
  } 

  protected List<String> parseName(String sourceName, char sourceSeparator)
  {
    List<String> result = new ArrayList<String>();

    StringBuffer currentWord = new StringBuffer();
    int length = sourceName.length();
    boolean lastIsLower = false;

    for (int index=0; index<length; index++)
    {
      char curChar = sourceName.charAt(index);
      if (Character.isUpperCase(curChar) || curChar == sourceSeparator)
      {
        if (lastIsLower || curChar == sourceSeparator)
        {
          result.add(currentWord.toString());
          currentWord = new StringBuffer();
        }
        lastIsLower = false;
      }
      else
      {
        if (!lastIsLower)
        {
          int currentWordLength = currentWord.length();
          if (currentWordLength > 1)
          {
            char lastChar = currentWord.charAt(--currentWordLength);
            currentWord.setLength(currentWordLength);
            result.add(currentWord.toString());
            currentWord = new StringBuffer();
            currentWord.append(lastChar);
          }
        }
        lastIsLower = true;
      }
      if (curChar != sourceSeparator)
      {
        currentWord.append(curChar);
      }
    }

    result.add(currentWord.toString());
    return result;
  }

  protected String convertName
    (String sourceName, 
     char sourceSeparator, 
     int sourceCase, 
     List<String> sourceShortForms, 
     List<String> sourceLongForms,
     char targetSeparator, 
     int targetCase, 
     List<String> targetShortForms, 
     List<String> targetLongForms)
  {
    String result = convertNameForm(sourceName, sourceShortForms, sourceLongForms);

    if (targetSeparator == LABEL_NO_SEPARATOR) 
    {
      result = convertCase(result, sourceCase, targetCase, sourceSeparator);
      result = convertSeparator(result, sourceSeparator, targetSeparator, sourceCase);
    }
    else 
    {
      result = convertSeparator(result, sourceSeparator, targetSeparator, sourceCase);
      result = convertCase(result, sourceCase, targetCase, sourceSeparator);
    }

    result = convertNameForm(result, targetLongForms, targetShortForms);

    return result;
  }
   
  protected String convertCase(String sourceName, int sourceCase, int targetCase, char sourceSeparator) 
  {
    if (targetCase == sourceCase)
    {
      return sourceName;
    }

    switch (targetCase) 
    {
      case LABEL_UPPER:
      {
        return sourceName.toUpperCase();
      }
      case LABEL_LOWER:
      {
        return sourceName.toLowerCase();
      }
      case LABEL_MIXED:
      {
        StringBuffer newString = new StringBuffer();
        int lastIndex = 0;
        int newIndex = 0;
        for(;;) 
        {
          newIndex = sourceName.indexOf(sourceSeparator, lastIndex);
          if (newIndex != -1 && ++newIndex < sourceName.length()) 
          {
            newString.append(sourceName.substring(lastIndex, newIndex).toLowerCase());
            newString.append(Character.toUpperCase(sourceName.charAt(newIndex)));
            lastIndex = newIndex + 1;
          }
          else 
          {
            newString.append(sourceName.substring(lastIndex).toLowerCase());
            break;
          }
        }
        return newString.toString();
      }
      default:
      {
        return null;
      }
    }
  }    

  protected String convertSeparator(String sourceName, char sourceSeparator, char targetSeparator, int sourceCase) 
  {
    if (targetSeparator == sourceSeparator)
    {
      return sourceName;
    }

    if (sourceSeparator != LABEL_NO_SEPARATOR && targetSeparator != LABEL_NO_SEPARATOR)
    {
      return sourceName.replace(sourceSeparator, targetSeparator);
    }

    if (targetSeparator == LABEL_NO_SEPARATOR) 
    {
      // remove all sourceSeparator characters
      StringBuffer newString = new StringBuffer();
      int lastIndex = 0;
      int newIndex = 0;
      for(;;) 
      {
        newIndex = sourceName.indexOf(sourceSeparator, lastIndex);
        if (newIndex != -1) 
        {
          newString.append(sourceName.substring(lastIndex, newIndex));
          lastIndex = newIndex + 1;
        }
        else 
        {
          newString.append(sourceName.substring(lastIndex));
          break;
        }
      }
      return newString.toString();
    }
    else 
    { 
      // sourceSeparator == LABEL_NO_SEPARATOR
      if (sourceCase == LABEL_MIXED) 
      {
        // add a targetSeparator in front of every upper case character
        StringBuffer newString = new StringBuffer();
        int length = sourceName.length();
        for (int index=0; index<length; index++) 
        {
          char curChar = sourceName.charAt(index);
          if (Character.isUpperCase(curChar) && index != 0)
            newString.append(targetSeparator);
          newString.append(curChar);
        }
        return newString.toString();
      }
    }

    return sourceName;
  } 

  protected String convertNameForm(String name, List<String> fromStrings, List<String> toStrings) 
  {
    String newName = name;

    for (int i=0; i<fromStrings.size(); i++) 
    {
      String fromString = fromStrings.get(i);
      if (name.indexOf(fromString) != -1) 
      {
        String toString = toStrings.get(i);
        newName = change(newName, fromString, toString);
      }
    }

    return newName;
  }    

  //FB The following method is copied from com.ibm.etools.b2b.util.StringUtility
  //FB because the mapping framework cannot have a dependency on b2b. What we really
  //FB need to do is get methods like this moved to vabase so that everyone can
  //FB use them.
  // change all occurrences of oldPat to newPat
  protected String change(String in, String oldPat, String newPat)
  {
    if (oldPat.length() == 0)
      return in;
    if (oldPat.length() == 1 && newPat.length() == 1)
      return in.replace(oldPat.charAt(0), newPat.charAt(0));

    int lastIndex = 0;
    int newIndex = 0;
    StringBuffer newString = new StringBuffer();
    for(;;)
    {
      newIndex = in.indexOf(oldPat, lastIndex);
      if (newIndex != -1)
      {
        newString.append(in.substring(lastIndex, newIndex) + newPat);
        lastIndex = newIndex + oldPat.length();
      }
      else
      {
        newString.append(in.substring(lastIndex));
        break;
      }
    }
    return newString.toString();
  }    
}
