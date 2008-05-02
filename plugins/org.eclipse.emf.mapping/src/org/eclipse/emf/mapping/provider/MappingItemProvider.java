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
 * $Id: MappingItemProvider.java,v 1.11 2008/05/02 11:27:40 emerks Exp $
 */
package org.eclipse.emf.mapping.provider;


import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.command.CommandWrapper;
import org.eclipse.emf.common.command.CompoundCommand;
import org.eclipse.emf.common.command.StrictCompoundCommand;
import org.eclipse.emf.common.command.UnexecutableCommand;
import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.util.ResourceLocator;
import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.edit.command.CommandParameter;
import org.eclipse.emf.edit.command.CopyCommand;
import org.eclipse.emf.edit.command.RemoveCommand;
import org.eclipse.emf.edit.command.SetCommand;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.emf.edit.provider.AdapterFactoryItemDelegator;
import org.eclipse.emf.edit.provider.ComposeableAdapterFactory;
import org.eclipse.emf.edit.provider.IEditingDomainItemProvider;
import org.eclipse.emf.edit.provider.IItemLabelProvider;
import org.eclipse.emf.edit.provider.IItemPropertyDescriptor;
import org.eclipse.emf.edit.provider.IItemPropertySource;
import org.eclipse.emf.edit.provider.IStructuredItemContentProvider;
import org.eclipse.emf.edit.provider.ITreeItemContentProvider;
//import org.eclipse.emf.edit.provider.ViewerNotification;

import org.eclipse.emf.edit.provider.ItemPropertyDescriptor;
import org.eclipse.emf.edit.provider.ItemPropertyDescriptorDecorator;
import org.eclipse.emf.mapping.Mapping;
import org.eclipse.emf.mapping.MappingHelper;
import org.eclipse.emf.mapping.MappingPackage;
import org.eclipse.emf.mapping.MappingPlugin;
import org.eclipse.emf.mapping.MappingRoot;
import org.eclipse.emf.mapping.command.NameMatchMappingCommand;
import org.eclipse.emf.mapping.command.RemoveMappingCommand;
import org.eclipse.emf.mapping.command.TypeMatchMappingCommand;
import org.eclipse.emf.mapping.domain.MappingDomain;

// import org.eclipse.emf.edit.provider.ItemProviderAdapter;


/**
 * This is the item provider adpater for a {@link org.eclipse.emf.mapping.Mapping} object.
 */
public class MappingItemProvider
  extends MappingItemProviderAdapter
  implements 
    IEditingDomainItemProvider,
    IStructuredItemContentProvider, 
    ITreeItemContentProvider, 
    IItemLabelProvider, 
    IItemPropertySource
{
  /**
   * This constructs an instance from a factory and a notifier.
   */
  public MappingItemProvider(AdapterFactory adapterFactory)
  {
    super(adapterFactory);
  }

  static public class TypeMappingHelperWrapper extends ItemPropertyDescriptor.PropertyValueWrapper
  {
    protected Mapping mapping;
    protected MappingHelper typeMappingHelper;

    public TypeMappingHelperWrapper(AdapterFactory adapterFactory, Mapping mapping, MappingHelper typeMappingHelper)
    {
      super(adapterFactory, mapping, typeMappingHelper, typeMappingHelper);
      this.mapping = mapping;
      this.typeMappingHelper = typeMappingHelper;
    }

    @Override
    protected IItemPropertyDescriptor createPropertyDescriptorDecorator(Object object, IItemPropertyDescriptor decoratedDescriptor)
    {
      return 
        new ItemPropertyDescriptorDecorator(object, decoratedDescriptor)
        {
          @Override
          public void setPropertyValue(Object thisObject, final Object value)
          {
            final MappingDomain domain = mapping.getMappingRoot().getDomain();
            final Object feature = itemPropertyDescriptor.getFeature(value);
            CompoundCommand compound = new StrictCompoundCommand();
            final Command copyCommand = CopyCommand.create(domain, typeMappingHelper);
            compound.append(copyCommand);
            compound.append
              (new CommandWrapper()
               {
                 @Override
                protected Command createCommand()
                 {
                   return SetCommand.create(domain, copyCommand.getResult().iterator().next(), feature, value);
                 }
               });
            compound.append
              (new CommandWrapper()
               {
                 @Override
                protected Command createCommand()
                 {
                   return SetCommand.create(domain, mapping, MappingPackage.eINSTANCE.getMapping_Helper(), copyCommand.getResult().iterator().next());
                 }
               });
            domain.getCommandStack().execute(compound);
          }

          @Override
          public void resetPropertyValue(Object thisObject)
          {
            //FB ????? 
          }
        };
    }
  }

  public void getPropertyDescriptorsForHelper(Mapping mapping)
  {
     // This is for the helper feature.
     //
     if (mapping.getEffectiveHelper() != null)
     {
       itemPropertyDescriptors.add
         (new ItemPropertyDescriptor
            (((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
             MappingPlugin.getPlugin().getString("_UI_Transformation_label"),
             MappingPlugin.getPlugin().getString("_UI_Transformation_description"),
             MappingPackage.eINSTANCE.getMapping_Helper(),
             false)
          {
            @Override
            public Object getPropertyValue(Object thisObject)
            {
              Mapping thisMapping = (Mapping)thisObject;
              MappingHelper helper = thisMapping.getHelper();
              if (helper != null)
                return helper;
              else
                return new TypeMappingHelperWrapper(this.adapterFactory, thisMapping, thisMapping.getEffectiveHelper());
            }
          });
     }
  }

  /**
   * This returns the property descriptors for the adapted class.
   */
  @Override
  public List<IItemPropertyDescriptor> getPropertyDescriptors(Object object)
  {
    if (itemPropertyDescriptors == null)
    {
      Mapping mapping = (Mapping)object;
      MappingRoot mappingRoot = mapping.getMappingRoot();
      boolean isTypeMapping = mappingRoot != null && mappingRoot.getDomain() == null;
      MappingPackage ePackage = MappingPackage.eINSTANCE;

      itemPropertyDescriptors = new ArrayList<IItemPropertyDescriptor>();
      if (isTypeMapping)
      {
        int count = 1;
        for (final Mapping childMapping : mapping.getNested())
        {
          IItemPropertyDescriptor childMappingItemPropertyDescriptor =
            new ItemPropertyDescriptor
               (((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
                count + ". " + MappingPlugin.getPlugin().getString("_UI_Type_mapping_label") + " ",
                MappingPlugin.getPlugin().getString("_UI_Type_mapping_helpers_description"),
                ePackage.getMapping_TypeMapping())
            {
              @Override
              public String getId(Object object)
              {
                return displayName;
              }
            };

          itemPropertyDescriptors.add
            (new ItemPropertyDescriptorDecorator(childMapping, childMappingItemPropertyDescriptor)
             {
               @Override
               public Object getPropertyValue(Object o)
               {
                 super.getPropertyValue(o);
                 return this.object;
               }
               @Override
               public Collection<?> getChoiceOfValues(Object o)
               {
                 return null;
               }
             });
          ++count;
        }
      }
      else
      {
        getPropertyDescriptorsForHelper(mapping);

        // This is for the typeMapping feature.
        //
        if (mapping.getTypeMapping() != null)
        {
          itemPropertyDescriptors.add
            (new ItemPropertyDescriptor
               (((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
                MappingPlugin.getPlugin().getString("_UI_Type_mapping_label"),
                MappingPlugin.getPlugin().getString("_UI_Type_mapping_description"),
                ePackage.getMapping_TypeMapping())
             {
               @Override
              public Object createPropertyValueWrapper(Object o, Object v)
               {
                 return v;
               }
               @Override
              public Collection<?> getChoiceOfValues(Object o)
               {
                 return null;
               }
             });
        }
      }
    }
    return itemPropertyDescriptors;
  }

  /**
   * This specifies how to implement {@link #getChildren} and is used to deduce an appropriate feature for an
   * {@link org.eclipse.emf.edit.command.AddCommand}, {@link org.eclipse.emf.edit.command.RemoveCommand} or
   * {@link org.eclipse.emf.edit.command.MoveCommand} in {@link #createCommand}.
   */
  @Override
  public Collection<? extends EStructuralFeature> getChildrenFeatures(Object object)
  {
    Mapping mapping = (Mapping)object;
    MappingRoot mappingRoot = mapping.getMappingRoot();
    Collection<EStructuralFeature> result = new ArrayList<EStructuralFeature>();
    if (mappingRoot == null || mappingRoot.isTopToBottom())
    {
      result.add(MappingPackage.eINSTANCE.getMapping_Inputs());
      result.add(MappingPackage.eINSTANCE.getMapping_Outputs());
    }
    else
    {
      result.add(MappingPackage.eINSTANCE.getMapping_Outputs());
      result.add(MappingPackage.eINSTANCE.getMapping_Inputs());
    }

    result.add(MappingPackage.eINSTANCE.getMapping_Nested());

    return result;
  }

  @Override
  protected EStructuralFeature getChildFeature(Object object, Object child)
  {
    Mapping mapping = (Mapping)object;
    MappingRoot mappingRoot = mapping.getMappingRoot();
    EObject refObject = (EObject)child;
    if (refObject instanceof Mapping)
    {
      return MappingPackage.eINSTANCE.getMapping_Nested();
    }
    else if (mapping.getInputs().contains(child))
    {
      return MappingPackage.eINSTANCE.getMapping_Inputs();
    }
    else if (mapping.getOutputs().contains(child))
    {
      return MappingPackage.eINSTANCE.getMapping_Outputs();
    }
    else if (mappingRoot != null && mappingRoot.isInputObject(child))
    {
      return MappingPackage.eINSTANCE.getMapping_Inputs();
    }
    else if (mappingRoot != null && mappingRoot.isOutputObject(child))
    {
      if (mappingRoot.getDomain() != null && mappingRoot.getMappedObjects().contains(mappingRoot.getDomain().getRoot(child)))
      {
        return MappingPackage.eINSTANCE.getMapping_Outputs();
      }
    }

    return super.getChildFeature(object, child);
  }

  @Override
  public EStructuralFeature getSetFeature(Object object, Object value)
  {
    Mapping mapping = (Mapping)object;
    MappingRoot mappingRoot = mapping.getMappingRoot();
    if (mappingRoot != null && mapping != mappingRoot)
    {
      if (mappingRoot.isInputObject(value) && !mapping.getInputs().contains(value))
      {
        return MappingPackage.eINSTANCE.getMapping_Inputs();
      }
      else if (mappingRoot.isOutputObject(value) && !mapping.getOutputs().contains(value))
      {
        return MappingPackage.eINSTANCE.getMapping_Outputs();
      }
    }

    return super.getSetFeature(object, value);
  }


  protected ArrayList<MappedObjectItemProvider> mappedObjectItemProviderList = new ArrayList<MappedObjectItemProvider>();

  protected MappedObjectItemProvider getMappedObjectItemProvider(Mapping mapping, Object mappedObject)
  {
    for (MappedObjectItemProvider mappedObjectItemProvider : mappedObjectItemProviderList)
    {
      if (mappedObjectItemProvider.getMappedObject() == mappedObject)
      {
        return mappedObjectItemProvider;
      }
    }

    MappedObjectItemProvider result = new MappedObjectItemProvider(adapterFactory, (EObject)mappedObject, mapping);
    mappedObjectItemProviderList.add(result);
    return result;
  }

  protected Object substituteMappedObjectItemProvider(Mapping mapping, Object object)
  {
    getChildren(mapping);
    for (MappedObjectItemProvider mappedObjectItemProvider : mappedObjectItemProviderList)
    {
      if (mappedObjectItemProvider.getMappedObject() == object)
      {
        return mappedObjectItemProvider;
      }
    }

    if (object instanceof Collection)
    {
      Collection<Object> result = new ArrayList<Object>();
      LOOP: 
      for (Object o : (Collection<?>)object)
      {
        for (MappedObjectItemProvider mappedObjectItemProvider : mappedObjectItemProviderList)
        {
          if (mappedObjectItemProvider.getMappedObject() == o)
          {
            result.add(mappedObjectItemProvider);
            continue LOOP;
          }
        }
        result.add(o);
      }

      return result;
    }

    return object;
  }

  protected Object substituteMappedObject(Mapping mapping, Object object)
  {
    if (object instanceof MappedObjectItemProvider)
    {
      return ((MappedObjectItemProvider)object).getMappedObject();
    }
    else if (object instanceof Collection)
    {
      Collection<Object> result = new ArrayList<Object>();
      for (Object o : (Collection<?>)object)
      {
        if (o instanceof MappedObjectItemProvider)
        {
          result.add(((MappedObjectItemProvider)o).getMappedObject());
        }
        else
        {
          result.add(o);
        }
      }
      return result;
    }

    return object;
  }

  public static Iterator<?> createValueIterator(Object object)
  {
    if (object == null)
    {
      return Collections.EMPTY_LIST.iterator();
    }
    else if (object instanceof Collection)
    {
      return ((Collection<?>)object).iterator();
    }
    else
    {
      return Collections.singleton(object).iterator();
    }
  }

  @Override
  public Collection<?> getChildren(Object object)
  {
    final Mapping mapping = (Mapping)object;

    Collection<Object> result = new ArrayList<Object>();

   
    for (Object input : mapping.getInputs())
    {
      result.add(getMappedObjectItemProvider(mapping, input));
    }

    for (Object output : mapping.getOutputs())
    {
      result.add(getMappedObjectItemProvider(mapping, output));
    }

    result.addAll(mapping.getNested());

    return result;
  }

  @Override
  public boolean hasChildren(Object object)
  {
    return true;
  }

  /**
   * This returns the nestedIn of the Mapping.
   */
  @Override
  public Object getParent(Object object)
  {
    return ((Mapping)object).getNestedIn();
  }

  public static Object getImage(MappingRoot mappingRoot, String prefix, Collection<?> collection)
  {
    return getImage(mappingRoot, prefix, collection, false);
  }

  public static Object getImage(MappingRoot mappingRoot, String prefix, Collection<?> collection, boolean supportNone)
  {
    int topsSize = 0;
    int bottomsSize = 0;
    for (Object object : collection)
    {
      if (mappingRoot.isTopObject(object))
      {
        ++topsSize;
      }
      else if (mappingRoot.isBottomObject(object))
      {
        ++bottomsSize;
      }
    } 

    return getImage(prefix, topsSize, bottomsSize, supportNone);
  }

  public static Object getImage(String prefix, Collection<?> tops, Collection<?> bottoms)
  {
    return getImage(prefix, tops, bottoms, false);
  }

  public static Object getImage(String prefix, Collection<?> tops, Collection<?> bottoms, boolean supportNone)
  {
    return getImage(prefix, tops.size(), bottoms.size(), supportNone);
  }

  public static Object getImage(String prefix, int topsSize, int bottomsSize, boolean supportNone)
  {
    if (topsSize == 0)
    {
      if (bottomsSize == 0)
      {
        return MappingPlugin.getPlugin().getImage(prefix + (supportNone ? "NoneToNoneMapping" : "OneToOneMapping"));
      }
      else if (bottomsSize == 1)
      {
        return MappingPlugin.getPlugin().getImage(prefix + (supportNone ? "NoneToOneMapping" : "OneToOneMapping"));
      }
      else
      {
        return MappingPlugin.getPlugin().getImage(prefix + (supportNone ? "NoneToManyMapping" : "OneToManyMapping"));
      }
    }
    else if (topsSize == 1)
    {
      if (bottomsSize == 0)
      {
        return MappingPlugin.getPlugin().getImage(prefix + (supportNone ? "OneToNoneMapping" : "OneToOneMapping"));
      }
      if (bottomsSize <= 1)
      {
        return MappingPlugin.getPlugin().getImage(prefix + "OneToOneMapping");
      }
      else
      {
        return MappingPlugin.getPlugin().getImage(prefix + "OneToManyMapping");
      }
    }
    else
    {
      if (bottomsSize == 0)
      {
        return MappingPlugin.getPlugin().getImage(prefix + (supportNone ? "ManyToNoneMapping" : "ManyToOneMapping"));
      }
      else if (bottomsSize == 1)
      {
        return MappingPlugin.getPlugin().getImage(prefix + "ManyToOneMapping");
      }
      else
      {
        return MappingPlugin.getPlugin().getImage(prefix + "ManyToManyMapping");
      }
    }
  }

  /**
   * This returns Mapping.gif.
   */
  @Override
  public Object getImage(Object object)
  {
    Mapping mapping = (Mapping)object;
    MappingRoot mappingRoot = mapping.getMappingRoot();
    boolean isTypeMapping = mappingRoot != null && mappingRoot.getDomain() == null;

    return 
      getImage
        (isTypeMapping ? "full/obj16/Type" : "full/obj16/", 
         mapping.getTops(), 
         mapping.getBottoms(),
         !isTypeMapping);
  }

  public static String getText(MappingRoot mappingRoot, AdapterFactory adapterFactory, Collection<?> collection)
  {
    return getText(mappingRoot, adapterFactory, collection, (String)null);
  }

  protected static final String DIVIDER = " " + MappingPlugin.getPlugin().getString("_UI_Mapping_label_divider") + " ";
  protected static final String SEPARATOR = MappingPlugin.getPlugin().getString("_UI_Mapping_label_separator") + " ";

  public static String getText(MappingRoot mappingRoot, AdapterFactory adapterFactory, Collection<?> collection, String pathSeparator)
  {
    return getText(mappingRoot, adapterFactory, collection, pathSeparator, SEPARATOR, DIVIDER);
  }

  public static String getText
    (MappingRoot mappingRoot, 
     AdapterFactory adapterFactory, 
     Collection<?> collection, 
     String pathSeparator, 
     String objectSeparator,
     String divider)
  {
    AdapterFactoryItemDelegator labelProvider = new AdapterFactoryItemDelegator(adapterFactory);
    boolean needsComma = false;
    StringBuffer result = new StringBuffer();

    for (Object object : collection)
    {
      EObject input = (EObject)object;
      if (mappingRoot.isTopObject(input))
      {
        if (needsComma)
        {
          result.append(objectSeparator);
        }
        else
        {
          needsComma = true;
        }
        int index = result.length();
        result.append(labelProvider.getText(input));
        if (pathSeparator != null)
        {
          for (Object parent = mappingRoot.getDomain().getParent(input); 
               parent instanceof EObject;
               parent = mappingRoot.getDomain().getParent(parent))
          {
            result.insert(index, labelProvider.getText(parent) + pathSeparator);
          }
        }
      }
    }

    result.append(divider);

    needsComma = false;
    for (Object object : collection)
    {
      EObject output = (EObject)object;
      if (mappingRoot.isBottomObject(output))
      {
        if (needsComma)
        {
          result.append(objectSeparator);
        }
        else
        {
          needsComma = true;
        }
        int index = result.length();
        result.append(labelProvider.getText(output));
        if (pathSeparator != null)
        {
          for (Object parent = mappingRoot.getDomain().getParent(output); 
               parent != null;
               parent = mappingRoot.getDomain().getParent(parent))
          {
            result.insert(index, labelProvider.getText(parent) + pathSeparator);
          }
        }
      }
    }

    return result.toString();
  }

  public static String getText(MappingRoot mappingRoot, AdapterFactory adapterFactory, Collection<?> inputs, Collection<?> outputs)
  {
    return getText(mappingRoot, adapterFactory, inputs, outputs, SEPARATOR, DIVIDER);
  }

  public static String getText
    (MappingRoot mappingRoot, AdapterFactory adapterFactory, Collection<?> inputs, Collection<?> outputs, String objectSeparator, String divider)
  {
    AdapterFactoryItemDelegator labelProvider = new AdapterFactoryItemDelegator(adapterFactory);
    boolean needsComma = false;
    StringBuffer result = new StringBuffer();

    for (Object input : inputs)
    {
      if (needsComma)
      {
        result.append(objectSeparator);
      }
      else
      {
        needsComma = true;
      }
      result.append(labelProvider.getText(input));
    }

    result.append(divider);

    needsComma = false;
    for (Object output : outputs)
    {
      if (needsComma)
      {
        result.append(objectSeparator);
      }
      else
      {
        needsComma = true;
      }
      result.append(labelProvider.getText(output));
    }

    return result.toString();
  }

  @Override
  public String getText(Object object)
  {
    Mapping mapping = (Mapping)object;
    return 
      getText
        (mapping.getMappingRoot(), 
         ((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(), 
         mapping.getTops(), 
         mapping.getBottoms());
  }

  /**
   * This handles notification by delegating to {@link #fireNotifyChanged fireNotifyChanged}.
   */
  @Override
  public void notifyChanged(Notification msg) 
  {
    MappingPackage ePackage = MappingPackage.eINSTANCE;
    if (msg.getFeature() == ePackage.getMapping_Inputs() || msg.getFeature() == ePackage.getMapping_Outputs())
    {
      fireNotifyChanged(msg);
      //hgd::fireNotifyChanged(msg.getNotifier(), msg.getEventType(), msg.getFeature(), substituteOldValue, substituteNewValue, msg.getPosition());

      ENotificationImpl note = 
        new ENotificationImpl
         ((InternalEObject)msg.getNotifier(),  //FIX:::
          Notification.SET, 
          null, 
          null, 
          null, 
          -1);
      fireNotifyChanged(note);
    }
    else if (msg.getFeature() == ePackage.getMapping_Helper() || msg.getFeature() == ePackage.getMapping_TypeMapping())
    {
      itemPropertyDescriptors = null;
      fireNotifyChanged(msg);
      //hgd::fireNotifyChanged(msg.getNotifier(), msg.getEventType(), msg.getFeature(), msg.getOldValue(), msg.getNewValue(), msg.getPosition());
    }
    else if (msg.getFeature() == ePackage.getMapping_Nested() || msg.getFeature() == ePackage.getMapping_NestedIn())
    {
      fireNotifyChanged(msg);
      //fireNotifyChanged(msg.getNotifier(), msg.getEventType(), msg.getFeature(), msg.getOldValue(), msg.getNewValue(), msg.getPosition());
    }
    else
    {
      super.notifyChanged(msg);
    }
  }

  @Override
  public Command createCommand(Object object, EditingDomain editingDomain, Class<? extends Command> commandClass, CommandParameter commandParameter)
  {
    if (editingDomain instanceof MappingDomain)
    {
      MappingDomain mappingDomain = (MappingDomain)editingDomain;
      if (commandClass == NameMatchMappingCommand.class)
      {
        return createNameMatchMappingCommand(mappingDomain, (Mapping)commandParameter.getOwner());
      }
      else if (commandClass == TypeMatchMappingCommand.class)
      {
        return createTypeMatchMappingCommand(mappingDomain, (Mapping)commandParameter.getOwner());
      }
    }

    // This ensures that we are dealing with actual MOF objects.
    //
    commandParameter.collection = (Collection<?>)substituteMappedObject((Mapping)object, commandParameter.getCollection());
    commandParameter.value = substituteMappedObject((Mapping)object, commandParameter.getValue());

    return super.createCommand(object, editingDomain, commandClass, commandParameter);
  }

  /**
   * This creates a primitive {@link org.eclipse.emf.mapping.command.NameMatchMappingCommand}.
   */
  protected Command createNameMatchMappingCommand(MappingDomain domain, Mapping mapping)
  {
    return new NameMatchMappingCommand(domain, mapping);
  }

  /**
   * This creates a primitive {@link org.eclipse.emf.mapping.command.TypeMatchMappingCommand}.
   */
  protected Command createTypeMatchMappingCommand(MappingDomain domain, Mapping mapping)
  {
    return new TypeMatchMappingCommand(domain, mapping);
  }

  @Override
  protected Command createRemoveCommand(final EditingDomain domain, EObject owner, final EStructuralFeature feature, Collection<?> collection)
  {
    final Mapping mappingOwner = (Mapping)owner;
    final MappingRoot mappingRoot = mappingOwner.getMappingRoot();

    if (feature == MappingPackage.eINSTANCE.getMapping_Nested())
    {
      final Collection<?> mappingCollection = collection;
      return 
        new CommandWrapper(super.createRemoveCommand(domain, owner, feature, collection))
        {
          protected void register()
          {
            for (Object object : mappingCollection)
            {
              Mapping mapping = (Mapping)object;
              for (TreeIterator<Mapping> mappings = mapping.treeIterator(); mappings.hasNext(); )
              {
                Mapping childMapping = mappings.next();
                mappingRoot.register(childMapping);
              }
            }
          }

          protected void deregister()
          {
            for (Object object : mappingCollection)
            {
              Mapping mapping = (Mapping)object;
              for (TreeIterator<Mapping> mappings = mapping.treeIterator(); mappings.hasNext(); )
              {
                Mapping childMapping = mappings.next();
                mappingRoot.deregister(childMapping);
              }
            }
          }

          @Override
          public void execute()
          {
            deregister();
            super.execute();
          }

          @Override
          public void undo()
          {
            super.undo();
            register();
          }

          @Override
          public void redo()
          {
            deregister();
            super.redo();
          }
        };
    }
    else if (feature == MappingPackage.eINSTANCE.getMapping_Inputs() || 
               feature == MappingPackage.eINSTANCE.getMapping_Outputs())
    {
      final Collection<?> mappedObjectsCollection = collection;
      final boolean removingInputs = (feature == MappingPackage.eINSTANCE.getMapping_Inputs());
      return 
        new CommandWrapper(super.createRemoveCommand(domain, owner, feature, collection))
        {
          protected CompoundCommand commands; 

          @Override
          protected boolean prepare()
          {
            boolean result = true;
            Collection<?> inputs = mappingOwner.getInputs();
            Collection<?> outputs = mappingOwner.getOutputs();
            if (removingInputs)
            {
              inputs = new ArrayList<Object>(inputs);
              inputs.removeAll(mappedObjectsCollection);
            }
            else
            {
              outputs = new ArrayList<Object>(outputs);
              outputs.removeAll(mappedObjectsCollection);
            }
            if (!mappingRoot.canCreateMapping(inputs, outputs, mappingOwner))
            {
              result = false;
            }
            return result;
          }

          protected void register()
          {
            for (Object object : mappedObjectsCollection)
            {
              mappingRoot.getMappedObjectState(object).getMappings().add(mappingOwner);
            }
          }

          protected void deregister()
          {
            for (Object object : mappedObjectsCollection)
            {
              mappingRoot.getMappedObjectState(object).getMappings().remove(mappingOwner);
            }
          }

          @Override
          public void execute()
          {
            if (mappingOwner == mappingRoot) 
            {
              commands = new CompoundCommand();
              Collection<Object> collectionOfDescendants = new HashSet<Object>();
              for (Object object : mappedObjectsCollection)
              {
                for (TreeIterator<?> descendants = domain.treeIterator(object); descendants.hasNext(); )
                {
                  Object descendant = descendants.next();
                  collectionOfDescendants.add(descendant);
                }
              }

              for (TreeIterator<Mapping> mappings = mappingRoot.treeIterator(false); mappings.hasNext(); )
              {
                Mapping mapping = mappings.next();
                if (!mapping.getInputs().isEmpty() && collectionOfDescendants.containsAll(mapping.getInputs()) || 
                      !mapping.getOutputs().isEmpty() && collectionOfDescendants.containsAll(mapping.getOutputs()))
                {
                  Command command = RemoveMappingCommand.create((MappingDomain)domain, mapping);
                  commands.appendIfCanExecute(command);
                }
                else
                {
                  if (feature == MappingPackage.eINSTANCE.getMapping_Inputs())
                  {
                    Collection<?> inputsToRemove = new ArrayList<Object>(mapping.getInputs());
                    inputsToRemove.retainAll(collectionOfDescendants);
                    if (!inputsToRemove.isEmpty())
                    {
                      Command removeInputsCommand = 
                        RemoveCommand.create(domain, mapping, MappingPackage.eINSTANCE.getMapping_Inputs(), inputsToRemove);
                      commands.appendIfCanExecute(removeInputsCommand);
                    }
                  }
                  else
                  {
                    Collection<?> outputsToRemove = new ArrayList<Object>(mapping.getOutputs());
                    outputsToRemove.retainAll(collectionOfDescendants);
                    if (!outputsToRemove.isEmpty())
                    {
                      Command removeOutputsCommand = 
                        RemoveCommand.create(domain, mapping, MappingPackage.eINSTANCE.getMapping_Outputs(), outputsToRemove);
                      commands.appendIfCanExecute(removeOutputsCommand);
                    }
                  }
                }
              }

              commands.execute();
            }
            super.execute();
            deregister();
          }

          @Override
          public void undo()
          {
            super.undo();
            if (commands != null)
            {
              commands.undo();
            }
            register();
          }

          @Override
          public void redo()
          {
            if (commands != null)
            {
              commands.redo();
            }
            super.redo();
            deregister();
          }

          @Override
          public void dispose()
          {
            super.dispose();
            if (commands != null)
            {
              command.dispose();
            }
          }
        };
    }
    else
    {
      return super.createRemoveCommand(domain, owner, feature, collection);
    }
  }

  @Override
  protected Command factorAddCommand(EditingDomain domain, CommandParameter commandParameter)
  {
    return UnexecutableCommand.INSTANCE;
  }

  @Override
  protected Command createAddCommand(EditingDomain domain, EObject owner, EStructuralFeature feature, Collection<?> collection, int index)
  {
    final Mapping mappingOwner = (Mapping)owner;
    final MappingRoot mappingRoot = mappingOwner.getMappingRoot();

    if (feature == MappingPackage.eINSTANCE.getMapping_Inputs() || 
          feature == MappingPackage.eINSTANCE.getMapping_Outputs())
    {
      final Collection<?> mappedObjectsCollection = collection;
      final boolean addingInputs = (feature == MappingPackage.eINSTANCE.getMapping_Inputs());
      return 
        new CommandWrapper(super.createAddCommand(domain, owner, feature, collection, index))
        {
          @Override
          protected boolean prepare()
          {
            boolean result = true;
            Collection<EObject> inputs = mappingOwner.getInputs();
            Collection<EObject> outputs = mappingOwner.getOutputs();
            if (addingInputs)
            {
              inputs = new ArrayList<EObject>(inputs);
              @SuppressWarnings("unchecked")
              Collection<EObject> eObjects = (Collection<EObject>)mappedObjectsCollection;
              inputs.addAll(eObjects);
            }
            else
            {
              outputs = new ArrayList<EObject>(outputs);
              @SuppressWarnings("unchecked")
              Collection<EObject> eObjects = (Collection<EObject>)mappedObjectsCollection;
              outputs.addAll(eObjects);
            }
            if (!mappingRoot.canCreateMapping(inputs, outputs, mappingOwner))
            {
              result = false;
            }
            return result;
          }
          protected void register()
          {
            for (Object object : mappedObjectsCollection)
            {
              mappingRoot.getMappedObjectState(object).getMappings().add(mappingOwner);
            }
          }

          protected void deregister()
          {
            for (Object object : mappedObjectsCollection)
            {
              mappingRoot.getMappedObjectState(object).getMappings().remove(mappingOwner);
            }
          }

          @Override
          public void execute()
          {
            super.execute();
            register();
          }

          @Override
          public void undo()
          {
            deregister();
            super.undo();
          }

          @Override
          public void redo()
          {
            super.redo();
            register();
          }

          @Override
          public Collection<?> getAffectedObjects()
          {
            return (Collection<?>)substituteMappedObjectItemProvider(mappingOwner, super.getAffectedObjects());
          }

          @Override
          public Collection<?> getResult()
          {
            return (Collection<?>)substituteMappedObjectItemProvider(mappingOwner, super.getResult());
          }
        };
    }
    else
    {
      return super.createAddCommand(domain, owner, feature, collection, index);
    }
  }

  /**
   * This creates a primitive {@link org.eclipse.emf.edit.command.MoveCommand}.
   */
  protected Command createMoveCommand(EditingDomain domain, EObject owner, EStructuralFeature feature, EObject value, int index)
  {
    final Mapping mappingOwner = (Mapping)owner;
    if (feature == MappingPackage.eINSTANCE.getMapping_Inputs() || 
          feature == MappingPackage.eINSTANCE.getMapping_Outputs())
    {
      return 
        new CommandWrapper(super.createMoveCommand(domain, owner, feature, value, index))
        {
          @Override
          public Collection<?> getAffectedObjects()
          {
            return (Collection<?>)substituteMappedObjectItemProvider(mappingOwner, super.getAffectedObjects());
          }

          @Override
          public Collection<?> getResult()
          {
            return (Collection<?>)substituteMappedObjectItemProvider(mappingOwner, super.getResult());
          }
        };
    }
    else
    {
      return super.createMoveCommand(domain, owner, feature, value, index);
    }
  }

  /**
   * This creates a primitive {@link SetCommand}.
   */
  @Override
  protected Command createSetCommand(EditingDomain domain, EObject owner, EStructuralFeature feature, Object value)
  {
    if (feature == MappingPackage.eINSTANCE.getMapping_Inputs() || 
          feature == MappingPackage.eINSTANCE.getMapping_Outputs())
    {
      return createAddCommand(domain, owner, feature, Collections.singleton(value), Notification.NO_INDEX);
    }
    else
    {
      return super.createSetCommand(domain, owner, feature, value);
    }
  }

  @Override
  public void dispose()
  {
    for (MappedObjectItemProvider mappedObjectItemProvider : mappedObjectItemProviderList)
    {
      mappedObjectItemProvider.dispose();
    }
    super.dispose();
  }

  /**
   * Return the resource locator for this item provider's resources.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public ResourceLocator getResourceLocator()
  {
    return MappingPlugin.INSTANCE;
  }

}
