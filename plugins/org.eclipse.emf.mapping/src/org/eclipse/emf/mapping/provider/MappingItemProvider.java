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
 * $Id: MappingItemProvider.java,v 1.4 2004/04/06 22:53:50 davidms Exp $
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
import org.eclipse.emf.edit.provider.IDisposable;
import org.eclipse.emf.edit.provider.IEditingDomainItemProvider;
import org.eclipse.emf.edit.provider.IItemLabelProvider;
import org.eclipse.emf.edit.provider.IItemPropertyDescriptor;
import org.eclipse.emf.edit.provider.IItemPropertySource;
import org.eclipse.emf.edit.provider.IStructuredItemContentProvider;
import org.eclipse.emf.edit.provider.ITreeItemContentProvider;
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

    protected IItemPropertyDescriptor createPropertyDescriptorDecorator(Object object, IItemPropertyDescriptor decoratedDescriptor)
    {
      return 
        new ItemPropertyDescriptorDecorator(object, decoratedDescriptor)
        {
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
                 protected Command createCommand()
                 {
                   return SetCommand.create(domain, copyCommand.getResult().iterator().next(), feature, value);
                 }
               });
            compound.append
              (new CommandWrapper()
               {
                 protected Command createCommand()
                 {
                   return SetCommand.create(domain, mapping, MappingPackage.eINSTANCE.getMapping_Helper(), copyCommand.getResult().iterator().next());
                 }
               });
            domain.getCommandStack().execute(compound);
          }

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
            public Object getPropertyValue(Object thisObject)
            {
              Mapping thisMapping = (Mapping)thisObject;
              MappingHelper helper = (MappingHelper)thisMapping.getHelper();
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
  public List getPropertyDescriptors(Object object)
  {
    if (itemPropertyDescriptors == null)
    {
      Mapping mapping = (Mapping)object;
      MappingRoot mappingRoot = mapping.getMappingRoot();
      boolean isTypeMapping = mappingRoot != null && mappingRoot.getDomain() == null;
      MappingPackage ePackage = MappingPackage.eINSTANCE;

      itemPropertyDescriptors = new ArrayList();
      if (isTypeMapping)
      {
        int count = 1;
        for (Iterator mappings = mapping.getNested().iterator(); mappings.hasNext(); ++count)
        {
          final Mapping childMapping = (Mapping)mappings.next();
          IItemPropertyDescriptor childMappingItemPropertyDescriptor =
            new ItemPropertyDescriptor
               (((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
                count + ". " + MappingPlugin.getPlugin().getString("_UI_Type_mapping_label") + " ",
                MappingPlugin.getPlugin().getString("_UI_Type_mapping_helpers_description"),
                ePackage.getMapping_TypeMapping());

          itemPropertyDescriptors.add
            (new ItemPropertyDescriptorDecorator(childMapping, childMappingItemPropertyDescriptor)
             {
               public Object getPropertyValue(Object o)
               {
                 Object result = super.getPropertyValue(o);
                 return this.object;
               }
               public Collection getChoiceOfValues(Object o)
               {
                 return null;
               }
             });
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
               public Object createPropertyValueWrapper(Object o, Object v)
               {
                 return v;
               }
               public Collection getChoiceOfValues(Object o)
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
  public Collection getChildrenFeatures(Object object)
  {
    Mapping mapping = (Mapping)object;
    MappingRoot mappingRoot = mapping.getMappingRoot();
    Collection result = new ArrayList();
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


  protected ArrayList mappedObjectItemProviderList = new ArrayList();

  protected MappedObjectItemProvider getMappedObjectItemProvider(Mapping mapping, Object mappedObject)
  {
    for (Iterator mappedObjectItemProviders = mappedObjectItemProviderList.iterator(); mappedObjectItemProviders.hasNext(); )
    {
      MappedObjectItemProvider mappedObjectItemProvider = (MappedObjectItemProvider)mappedObjectItemProviders.next();
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
    for (Iterator mappedObjectItemProviders = mappedObjectItemProviderList.iterator(); mappedObjectItemProviders.hasNext(); )
    {
      MappedObjectItemProvider mappedObjectItemProvider = (MappedObjectItemProvider)mappedObjectItemProviders.next();
      if (mappedObjectItemProvider.getMappedObject() == object)
      {
        return mappedObjectItemProvider;
      }
    }

    if (object instanceof Collection)
    {
      Collection result = new ArrayList();
      LOOP: for (Iterator objects = ((Collection)object).iterator(); objects.hasNext(); )
      {
        Object o = objects.next();
        for (Iterator mappedObjectItemProviders = mappedObjectItemProviderList.iterator(); mappedObjectItemProviders.hasNext(); )
        {
          MappedObjectItemProvider mappedObjectItemProvider = (MappedObjectItemProvider)mappedObjectItemProviders.next();
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
      Collection result = new ArrayList();
      for (Iterator objects = ((Collection)object).iterator(); objects.hasNext(); )
      {
        Object o = objects.next();
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

  public static Iterator createValueIterator(Object object)
  {
    if (object == null)
    {
      return Collections.EMPTY_LIST.iterator();
    }
    else if (object instanceof Collection)
    {
      return ((Collection)object).iterator();
    }
    else
    {
      return Collections.singleton(object).iterator();
    }
  }

  public Collection getChildren(Object object)
  {
    final Mapping mapping = (Mapping)object;

    Collection result = new ArrayList();

   
    for (Iterator inputs = mapping.getInputs().iterator(); inputs.hasNext(); )
    {
      Object input = inputs.next();
      result.add(getMappedObjectItemProvider(mapping, input));
    }

    for (Iterator outputs = mapping.getOutputs().iterator(); outputs.hasNext(); )
    {
      Object output = outputs.next();
      result.add(getMappedObjectItemProvider(mapping, output));
    }

    result.addAll(mapping.getNested());

    return result;
  }

  public boolean hasChildren(Object object)
  {
    return true;
  }

  /**
   * This returns the nestedIn of the Mapping.
   */
  public Object getParent(Object object)
  {
    return ((Mapping)object).getNestedIn();
  }

  public static Object getImage(MappingRoot mappingRoot, String prefix, Collection collection)
  {
    return getImage(mappingRoot, prefix, collection, false);
  }

  public static Object getImage(MappingRoot mappingRoot, String prefix, Collection collection, boolean supportNone)
  {
    int topsSize = 0;
    int bottomsSize = 0;
    for (Iterator objects = collection.iterator(); objects.hasNext(); )
    {
      Object object = objects.next();
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

  public static Object getImage(String prefix, Collection tops, Collection bottoms)
  {
    return getImage(prefix, tops, bottoms, false);
  }

  public static Object getImage(String prefix, Collection tops, Collection bottoms, boolean supportNone)
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

  public static String getText(MappingRoot mappingRoot, AdapterFactory adapterFactory, Collection collection)
  {
    return getText(mappingRoot, adapterFactory, collection, (String)null);
  }

  protected static final String DIVIDER = " " + MappingPlugin.getPlugin().getString("_UI_Mapping_label_divider") + " ";
  protected static final String SEPARATOR = MappingPlugin.getPlugin().getString("_UI_Mapping_label_separator") + " ";

  public static String getText(MappingRoot mappingRoot, AdapterFactory adapterFactory, Collection collection, String pathSeparator)
  {
    return getText(mappingRoot, adapterFactory, collection, pathSeparator, SEPARATOR, DIVIDER);
  }

  public static String getText
    (MappingRoot mappingRoot, 
     AdapterFactory adapterFactory, 
     Collection collection, 
     String pathSeparator, 
     String objectSeparator,
     String divider)
  {
    AdapterFactoryItemDelegator labelProvider = new AdapterFactoryItemDelegator(adapterFactory);
    boolean needsComma = false;
    StringBuffer result = new StringBuffer();

    for (Iterator i = collection.iterator(); i.hasNext(); )
    {
      EObject input = (EObject)i.next();
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
    for (Iterator o = collection.iterator(); o.hasNext(); )
    {
      EObject output = (EObject)o.next();
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

  public static String getText(MappingRoot mappingRoot, AdapterFactory adapterFactory, Collection inputs, Collection outputs)
  {
    return getText(mappingRoot, adapterFactory, inputs, outputs, SEPARATOR, DIVIDER);
  }

  public static String getText
    (MappingRoot mappingRoot, AdapterFactory adapterFactory, Collection inputs, Collection outputs, String objectSeparator, String divider)
  {
    AdapterFactoryItemDelegator labelProvider = new AdapterFactoryItemDelegator(adapterFactory);
    boolean needsComma = false;
    StringBuffer result = new StringBuffer();

    for (Iterator i = inputs.iterator(); i.hasNext(); )
    {
      Object input = i.next();
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
    for (Iterator o = outputs.iterator(); o.hasNext(); )
    {
      Object output = o.next();
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
  public void notifyChanged(Notification msg) 
  {
    Mapping mapping = (Mapping)msg.getNotifier();
    //MappingPackage ePackage = mapping.ePackageMapping();
    MappingPackage ePackage = MappingPackage.eINSTANCE;
    Object feature = msg.getFeature();
    if (msg.getFeature() == ePackage.getMapping_Inputs() || msg.getFeature() == ePackage.getMapping_Outputs())
    {
      Object substituteOldValue = substituteMappedObjectItemProvider(mapping, msg.getOldValue());
      Object substituteNewValue = substituteMappedObjectItemProvider(mapping, msg.getNewValue());

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

  public Command createCommand(Object object, EditingDomain editingDomain, Class commandClass, CommandParameter commandParameter)
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
    commandParameter.collection = (Collection)substituteMappedObject((Mapping)object, commandParameter.getCollection());
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

  protected Command createRemoveCommand(final EditingDomain domain, EObject owner, final EStructuralFeature feature, Collection collection)
  {
    final Mapping mappingOwner = (Mapping)owner;
    final MappingRoot mappingRoot = mappingOwner.getMappingRoot();

    if (feature == MappingPackage.eINSTANCE.getMapping_Nested())
    {
      final Collection mappingCollection = collection;
      return 
        new CommandWrapper(super.createRemoveCommand(domain, owner, feature, collection))
        {
          protected void register()
          {
            for (Iterator objects = mappingCollection.iterator(); objects.hasNext(); )
            {
              Mapping mapping = (Mapping)objects.next();
              for (TreeIterator mappings = mapping.treeIterator(); mappings.hasNext(); )
              {
                Mapping childMapping = (Mapping)mappings.next();
                mappingRoot.register(childMapping);
              }
            }
          }

          protected void deregister()
          {
            for (Iterator objects = mappingCollection.iterator(); objects.hasNext(); )
            {
              Mapping mapping = (Mapping)objects.next();
              for (TreeIterator mappings = mapping.treeIterator(); mappings.hasNext(); )
              {
                Mapping childMapping = (Mapping)mappings.next();
                mappingRoot.deregister(childMapping);
              }
            }
          }

          public void execute()
          {
            deregister();
            super.execute();
          }

          public void undo()
          {
            super.undo();
            register();
          }

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
      final Collection mappedObjectsCollection = collection;
      final boolean removingInputs = (feature == MappingPackage.eINSTANCE.getMapping_Inputs());
      return 
        new CommandWrapper(super.createRemoveCommand(domain, owner, feature, collection))
        {
          protected CompoundCommand commands; 

          protected boolean prepare()
          {
            boolean result = true;
            Collection inputs = mappingOwner.getInputs();
            Collection outputs = mappingOwner.getOutputs();
            if (removingInputs)
            {
              inputs = new ArrayList(inputs);
              inputs.removeAll(mappedObjectsCollection);
            }
            else
            {
              outputs = new ArrayList(outputs);
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
            for (Iterator objects = mappedObjectsCollection.iterator(); objects.hasNext(); )
            {
              Object object = objects.next();
              mappingRoot.getMappedObjectState(object).getMappings().add(mappingOwner);
            }
          }

          protected void deregister()
          {
            for (Iterator objects = mappedObjectsCollection.iterator(); objects.hasNext(); )
            {
              Object object = objects.next();
              mappingRoot.getMappedObjectState(object).getMappings().remove(mappingOwner);
            }
          }

          public void execute()
          {
            if (mappingOwner == mappingRoot) 
            {
              commands = new CompoundCommand();
              Collection collectionOfDescendants = new HashSet();
              for (Iterator objects = mappedObjectsCollection.iterator(); objects.hasNext(); )
              {
                for (TreeIterator descendants = domain.treeIterator(objects.next()); descendants.hasNext(); )
                {
                  Object descendant = descendants.next();
                  collectionOfDescendants.add(descendant);
                }
              }

              Collection mappingsToRemove = new ArrayList();
              for (TreeIterator mappings = mappingRoot.treeIterator(false); mappings.hasNext(); )
              {
                Mapping mapping = (Mapping)mappings.next();
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
                    Collection inputsToRemove = new ArrayList(mapping.getInputs());
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
                    Collection outputsToRemove = new ArrayList(mapping.getOutputs());
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

          public void undo()
          {
            super.undo();
            if (commands != null)
            {
              commands.undo();
            }
            register();
          }

          public void redo()
          {
            if (commands != null)
            {
              commands.redo();
            }
            super.redo();
            deregister();
          }

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

  protected Command factorAddCommand(EditingDomain domain, CommandParameter commandParameter)
  {
    return UnexecutableCommand.INSTANCE;
  }

  protected Command createAddCommand(EditingDomain domain, EObject owner, EStructuralFeature feature, Collection collection, int index)
  {
    final Mapping mappingOwner = (Mapping)owner;
    final MappingRoot mappingRoot = mappingOwner.getMappingRoot();

    if (feature == MappingPackage.eINSTANCE.getMapping_Inputs() || 
          feature == MappingPackage.eINSTANCE.getMapping_Outputs())
    {
      final Collection mappedObjectsCollection = collection;
      final boolean addingInputs = (feature == MappingPackage.eINSTANCE.getMapping_Inputs());
      return 
        new CommandWrapper(super.createAddCommand(domain, owner, feature, collection, index))
        {
          protected boolean prepare()
          {
            boolean result = true;
            Collection inputs = mappingOwner.getInputs();
            Collection outputs = mappingOwner.getOutputs();
            if (addingInputs)
            {
              inputs = new ArrayList(inputs);
              inputs.addAll(mappedObjectsCollection);
            }
            else
            {
              outputs = new ArrayList(outputs);
              outputs.addAll(mappedObjectsCollection);
            }
            if (!mappingRoot.canCreateMapping(inputs, outputs, mappingOwner))
            {
              result = false;
            }
            return result;
          }
          protected void register()
          {
            for (Iterator objects = mappedObjectsCollection.iterator(); objects.hasNext(); )
            {
              Object object = objects.next();
              mappingRoot.getMappedObjectState(object).getMappings().add(mappingOwner);
            }
          }

          protected void deregister()
          {
            for (Iterator objects = mappedObjectsCollection.iterator(); objects.hasNext(); )
            {
              Object object = objects.next();
              mappingRoot.getMappedObjectState(object).getMappings().remove(mappingOwner);
            }
          }

          public void execute()
          {
            super.execute();
            register();
          }

          public void undo()
          {
            deregister();
            super.undo();
          }

          public void redo()
          {
            super.redo();
            register();
          }

          public Collection getAffectedObjects()
          {
            return (Collection)substituteMappedObjectItemProvider(mappingOwner, super.getAffectedObjects());
          }

          public Collection getResult()
          {
            return (Collection)substituteMappedObjectItemProvider(mappingOwner, super.getResult());
          }
        };
    }
    else
    {
      return super.createAddCommand(domain, owner, feature, collection, index);
    }
  }

  /**
   * This creates a primitive {@link com.ibm.etools.emf.edit.command.MoveCommand}.
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
          public Collection getAffectedObjects()
          {
            return (Collection)substituteMappedObjectItemProvider(mappingOwner, super.getAffectedObjects());
          }

          public Collection getResult()
          {
            return (Collection)substituteMappedObjectItemProvider(mappingOwner, super.getResult());
          }
        };
    }
    else
    {
      return super.createMoveCommand(domain, owner, feature, value, index);
    }
  }

  /**
   * This creates a primitive {@link com.ibm.etools.emf.edit.command.SetCommand}.
   */
  protected Command createSetCommand(EditingDomain domain, EObject owner, EStructuralFeature feature, Object value)
  {
    Mapping mapping = (Mapping)owner;

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

  public void dispose()
  {
    for (Iterator providers = mappedObjectItemProviderList.iterator(); providers.hasNext(); )
    {
      Object provider = providers.next();
      ((IDisposable)provider).dispose();
    }
    super.dispose();
  }

  /**
   * Return the resource locator for this item provider's resources.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public ResourceLocator getResourceLocator()
  {
    return MappingPlugin.INSTANCE;
  }
}
