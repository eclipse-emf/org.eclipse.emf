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
 * $Id: ItemProviderAdapter.java,v 1.1 2004/03/06 17:31:32 marcelop Exp $
 */
package org.eclipse.emf.edit.provider;


import java.net.URL;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.MissingResourceException;

import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.command.CommandWrapper;
import org.eclipse.emf.common.command.CompoundCommand;
import org.eclipse.emf.common.command.UnexecutableCommand;
import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationWrapper;
import org.eclipse.emf.common.notify.Notifier;
import org.eclipse.emf.common.notify.impl.AdapterImpl;
import org.eclipse.emf.common.util.ResourceLocator;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.util.ExtendedMetaData;
import org.eclipse.emf.ecore.util.FeatureMap;
import org.eclipse.emf.ecore.xml.type.XMLTypePackage;
import org.eclipse.emf.edit.EMFEditPlugin;
import org.eclipse.emf.edit.command.AddCommand;
import org.eclipse.emf.edit.command.CommandParameter;
import org.eclipse.emf.edit.command.CopyCommand;
import org.eclipse.emf.edit.command.CreateChildCommand;
import org.eclipse.emf.edit.command.CreateCopyCommand;
import org.eclipse.emf.edit.command.DragAndDropCommand;
import org.eclipse.emf.edit.command.InitializeCopyCommand;
import org.eclipse.emf.edit.command.MoveCommand;
import org.eclipse.emf.edit.command.RemoveCommand;
import org.eclipse.emf.edit.command.ReplaceCommand;
import org.eclipse.emf.edit.command.SetCommand;
import org.eclipse.emf.edit.domain.EditingDomain;


/**
 * This adapter implementation provides a convenient reusable base for
 * adapters that will be used as item providers.
 * Default implementations for the following interfaces are provided: 
 * {@link IItemLabelProvider}, {@link IItemPropertySource},
 * {@link IStructuredItemContentProvider},
 * {@link ITreeItemContentProvider}, and {@link IEditingDomainItemProvider}.  
 * Also, {@link IUpdateableItemText#getUpdateableText} is implemented to delegate to {@link #getText}; 
 * often the editable text will be just the text, in which case this is a good default implementation.
 */
public class ItemProviderAdapter 
  extends AdapterImpl 
  implements 
    IChangeNotifier,
    IDisposable,
    CreateChildCommand.Helper,
    ResourceLocator
{
  /**
   * This keeps track of the adapter factory that created this adaptor.
   * It is also used as the key/type for this adapter.
   */
  protected AdapterFactory adapterFactory;

  /**
   * This is used to store all the property descriptors.
   * Derived classes should add descriptors to this vector.
   */
  protected List itemPropertyDescriptors;

  /**
   * This is used to store all the children references.
   * Derived classes should add references to this vector.
   */
  protected List childrenReferences;

  /**
   * This is used to implement {@link IChangeNotifier}.
   */
  protected IChangeNotifier changeNotifier;

  /**
   * This keeps track of all the targets to which this adapter is set.
   */
  protected Collection targets;

  /**
   * An instance is created from an adapter factory.
   * The factory is used as a key so that we always know which factory created this adapter.
   */
  public ItemProviderAdapter(AdapterFactory adapterFactory)
  {
    this.adapterFactory = adapterFactory;
  }

  /**
   * The adapter factory is used as the type key.
   * This returns true, only if this adapter was created by the given factory.
   */
  public boolean isAdapterForType(Object type)
  {
    return type == adapterFactory;
  }

  /**
   * This provides access to the factory.
   */
  public AdapterFactory getAdapterFactory()
  {
    return adapterFactory;
  }

  public void addListener(INotifyChangedListener listener)
  {
    if (changeNotifier == null)
    {
      changeNotifier = new ChangeNotifier();
    }
    changeNotifier.addListener(listener);
  }

  public void removeListener(INotifyChangedListener listener)
  {
    if (changeNotifier != null)
    {
      changeNotifier.removeListener(listener);
    }
  }

  /**
   * This convenience method converts the arguments into an appropriate update call on the viewer.
   * The event type is a value from the static constants in {@link org.eclipse.emf.common.notify.Notifier}.
   */
  public void fireNotifyChanged(Notification notification)
  {
/*
    System.out.println("ItemProviderAdapterFactory.fireNotifyChanged");
    System.out.println("  object    = " + object);
    System.out.println("  eventType = " + eventType);
    if (oldValue instanceof Collection)
    {
      System.out.println("  oldValue  = " + CommandParameter.collectionToString((Collection)oldValue));
    }
    else
    {
      System.out.println("  oldValue  = " + oldValue);
    }
    if (newValue instanceof Collection)
    {
      System.out.println("  newValue  = " + CommandParameter.collectionToString((Collection)newValue));
    }
    else
    {
      System.out.println("  newValue  = " + newValue);
    }
*/

    if (changeNotifier != null)
    {
      changeNotifier.fireNotifyChanged(notification);
    }

    if (adapterFactory instanceof IChangeNotifier)
    {
      IChangeNotifier changeNotifier = (IChangeNotifier)adapterFactory;
      changeNotifier.fireNotifyChanged(notification);
    }
  }

  /**
   * This implements {@link IItemPropertySource#getEditableValue IItemPropertySource.getEditableValue} 
   * by simply returning the object itself.
   */
  public Object getEditableValue(Object object) 
  {
    return object;
  }

  /**
   * This implements {@link IItemPropertySource#getPropertyDescriptors IItemPropertySource.getPropertyDescriptors} 
   * by returning the locally stored vector of descriptors.
   * This vector could be populated in the constructor of a derived class
   * but it's probably more efficient to create them only on demand by overriding this method.
   * You'll probably want to call super.getPropertyDescriptors if you do this, 
   * since you may have one adapter derive from another.
   */
  public List getPropertyDescriptors(Object object) 
  {
    if (itemPropertyDescriptors == null)
    {
      itemPropertyDescriptors = new ArrayList();
    }
    return itemPropertyDescriptors;
  }

  /**
   * This convenience method finds a particular descriptor given its property name.
   */
  public IItemPropertyDescriptor getPropertyDescriptor(Object object, Object propertyId)
  {
    for (Iterator i = getPropertyDescriptors(object).iterator(); i.hasNext(); )
    {
      IItemPropertyDescriptor itemPropertyDescriptor = (IItemPropertyDescriptor)i.next();
      if (propertyId.equals(itemPropertyDescriptor.getId(object)))
      {
        return itemPropertyDescriptor;
      }
    }

    return null;
  }

  /**
   * This implements a PropertySource by delegating to the descriptor,
   * which is assumed to support the IItemPropertyDescriptor interface
   */
  public Object getPropertyValue(Object object, String property) 
  {
    return ((IItemPropertyDescriptor)getPropertyDescriptor(object, property)).getPropertyValue(object);
  }

  /**
   * This implements PropertySource by delegating to the descriptor,
   * which is assumed to support the IItemPropertyDescriptor interface
   */
  public boolean isPropertySet(Object object, String property) 
  {
    return ((IItemPropertyDescriptor)getPropertyDescriptor(object, property)).isPropertySet(object);
  }

  /**
   * This implements PropertySource.resetPropertyValue by delegating to the descriptor,
   * which is assumed to support the IItemPropertyDescriptor interface
   */
  public void resetPropertyValue(Object object, String property) 
  {
    ((IItemPropertyDescriptor)getPropertyDescriptor(object, property)).resetPropertyValue(object);
  }

  /**
   * This implements PropertySource by delegating to the descriptor,
   * which is assumed to support the IItemPropertyDescriptor interface
   */
  public void setPropertyValue(Object object, String property, Object value) 
  {
    ((IItemPropertyDescriptor)getPropertyDescriptor(object, property)).setPropertyValue(object, value);
  }
  
  /**
   * This implements {@link IStructuredItemContentProvider#getElements IStructuredItemContentProvider.getElements} 
   * by forwarding the call to {@link #getChildren getChildren}.
   * It seems that you almost always want getElements and getChildren to return the same thing, so this makes that easy.
   */
  public Collection getElements(Object object)
  {
    return getChildren(object);
  }

  /**
   * This implements {@link ITreeItemContentProvider#getChildren ITreeItemContentProvider.getChildren} 
   * by calling {@link #getChildrenReferences getChildrenReferences} and using those to collect the children.
   */
  public Collection getChildren(Object object)
  {
    Collection result = new ArrayList();
    for (Iterator childrenReferences = getChildrenReferences(object).iterator(); childrenReferences.hasNext(); )
    {
      EStructuralFeature childrenReference = (EStructuralFeature)childrenReferences.next();
      if (childrenReference.getEType().getInstanceClass() == FeatureMap.Entry.class) 
      {
        result.addAll(wrapFeatureMapChildren(object, childrenReference, (List)((EObject)object).eGet(childrenReference)));
      }
      else if (childrenReference.isMany())
      {
        result.addAll((List)((EObject)object).eGet(childrenReference));
      }
      else 
      {
        Object child = ((EObject)object).eGet(childrenReference);
        if (child != null)
        {
          result.add(child);
        }
      }
    }
    return result;
  }

  protected String getEncodedString(String stringValue)
  {
    StringBuffer encodedStringValue = new StringBuffer(stringValue.length());
    for (int j = 0; j < stringValue.length(); ++j)
    {
      char character = stringValue.charAt(j);
      switch (character)
      {
        case '\\':
        {
          encodedStringValue.append("\\\\");
          break;
        }
        case '\n':
        {
          encodedStringValue.append("\\n");
          break;
        }
        case '\r':
        {
          encodedStringValue.append("\\r");
          break;
        }
        default:
        {
          if (character < ' ')
          {
            encodedStringValue.append("\\" + (int)character);
          }
          else
          {
            encodedStringValue.append(character);
          }
          break;
        }
      }
    }
    return encodedStringValue.toString();
  }

  protected Map wrappers;
  protected Object getWrapper(Object object, EStructuralFeature eStructuralFeature, EStructuralFeature entryFeature, Object value)
  {
    if (wrappers == null)
    {
      wrappers = new HashMap();
    }

    ArrayList tuple = new ArrayList();
    tuple.ensureCapacity(4);
    tuple.add(object);
    tuple.add(eStructuralFeature);
    tuple.add(entryFeature);
    tuple.add(value);
    
    Object result = wrappers.get(tuple);
    if (result == null)
    {
      if (entryFeature instanceof EAttribute)
      {
        if (entryFeature == XMLTypePackage.eINSTANCE.getXMLTypeDocumentRoot_Text())
        {
          result =
            new ItemProvider
              (getEncodedString(value.toString()),
               EMFEditPlugin.INSTANCE.getImage("full/obj16/TextValue"), 
               object);
        }
        else if (entryFeature == XMLTypePackage.eINSTANCE.getXMLTypeDocumentRoot_CDATA())
        {
          result =
            new ItemProvider
              ("<![CDATA[" + getEncodedString(value.toString()) + "]]>",
               EMFEditPlugin.INSTANCE.getImage("full/obj16/TextValue"), 
               object);
        }
        else if (entryFeature == XMLTypePackage.eINSTANCE.getXMLTypeDocumentRoot_Comment())
        {
          result =
            new ItemProvider
              ("<!--" + getEncodedString(value.toString()) + "-->",
               EMFEditPlugin.INSTANCE.getImage("full/obj16/TextValue"), 
               object);
        }
        else if (ExtendedMetaData.INSTANCE.getFeatureKind(eStructuralFeature) == ExtendedMetaData.ATTRIBUTE_WILDCARD_FEATURE)
        {
          result =
            new ItemProvider
              (entryFeature.getName() + "='" + EcoreUtil.convertToString((EDataType)entryFeature.getEType(), value) + "'",
               EMFEditPlugin.INSTANCE.getImage("full/obj16/GenericValue"), 
               object);
        }
        else
        {
          if (value == null)
          {
            result =
              new ItemProvider
                ("<" + entryFeature.getName() + " xsi:nil=\"true\"/>",
                 EMFEditPlugin.INSTANCE.getImage("full/obj16/GenericValue"), 
                 object);
          }
          else
          {
            result =
              new ItemProvider
                ("<" + entryFeature.getName() + ">" + 
                   getEncodedString(EcoreUtil.convertToString((EDataType)entryFeature.getEType(), value)) + 
                   "</" + entryFeature.getName() + ">",
                 EMFEditPlugin.INSTANCE.getImage("full/obj16/GenericValue"), 
                 object);
          }
        }
      }
      else if (value == null)
      {
        result =
          new ItemProvider
            ("<" + entryFeature.getName() + " xsi:nil=\"true\"/>",
             EMFEditPlugin.INSTANCE.getImage("full/obj16/GenericValue"),
             object);
      }
      else
      {
        IChangeNotifier decoratedItemProvider = 
          (IChangeNotifier)getRootAdapterFactory().adapt((EObject)value, IStructuredItemContentProvider.class);
        class Wrapper 
          extends ItemProviderDecorator 
          implements 
            ITreeItemContentProvider, 
            IItemPropertySource, 
            IEditingDomainItemProvider, 
            IItemLabelProvider
        {
          protected Object parent;
          protected EStructuralFeature feature;
          protected EObject eObject;

          public Wrapper(Object parent, AdapterFactory adapterFactory, EStructuralFeature feature, EObject eObject)
          {
            super(adapterFactory);
            this.parent = parent;
            this.feature = feature;
            this.eObject = eObject;
          }

          public Object getParent(Object object)
          {
            return parent;
          }

          public String getText(Object object)
          {
            return "<" + feature.getName() + "> " + super.getText(eObject);
          }

          public Object getImage(Object object)
          {
            return super.getImage(eObject);
          }

          public boolean hasChildren(Object object)
          {
            return super.hasChildren(eObject);
          }

          public Collection getChildren(Object object)
          {
            return super.getChildren(eObject);
          }

          public Collection getElements(Object object)
          {
            return super.getElements(eObject);
          }

          public List getPropertyDescriptors(Object object)
          {
            List result = new ArrayList();
            for (Iterator i = super.getPropertyDescriptors(eObject).iterator(); i.hasNext(); )
            {
              IItemPropertyDescriptor itemPropertyDescriptor = (IItemPropertyDescriptor)i.next();
              result.add(new ItemPropertyDescriptorDecorator(eObject, itemPropertyDescriptor));
            }
            return result;
          }

          public IItemPropertyDescriptor getPropertyDescriptor(Object object, Object propertyId)
          {
            IItemPropertyDescriptor itemPropertyDescriptor = super.getPropertyDescriptor(eObject, propertyId);
            return new ItemPropertyDescriptorDecorator(eObject, itemPropertyDescriptor);
          }

          public Object getEditableValue(Object object) 
          {
            return eObject;
          }

          public Collection getNewChildDescriptors(Object object, EditingDomain editingDomain, Object sibling)
          {
            return super.getNewChildDescriptors(eObject, editingDomain, sibling);
          }

          public Command createCommand(Object object, EditingDomain editingDomain, Class commandClass, CommandParameter commandParameter)
          {
           commandParameter.setOwner(eObject);
           Command result = super.createCommand(eObject, editingDomain, commandClass, commandParameter);
           return result;
          }

          public void fireNotifyChanged(Notification notification)
          {
            super.fireNotifyChanged(new NotificationWrapper(this, notification));
          }
        }

        Wrapper wrapper = new Wrapper(object, getRootAdapterFactory(), entryFeature, (EObject)value);
        wrapper.setDecoratedItemProvider(decoratedItemProvider);
        result = wrapper;
      }

      wrappers.put(tuple, result);
    }
    return result;
  }

  protected Collection wrapFeatureMapChildren(Object object, EStructuralFeature eStructuralFeature, List children)
  {
    List result = new ArrayList();
    for (Iterator i = children.iterator(); i.hasNext(); )
    {
      FeatureMap.Entry entry = (FeatureMap.Entry)i.next();
      EStructuralFeature entryFeature = entry.getEStructuralFeature();
      Object value = entry.getValue();
      result.add(getWrapper(object, eStructuralFeature, entryFeature, value));
    }
    return result;
  }

  /**
   * This implements {@link ITreeItemContentProvider#hasChildren ITreeItemContentProvider.hasChildren} 
   * by simply testing whether {@link #getChildren getChildren} returns any children.
   * This implementation will always be right, however, for efficiency you may want to override it to return false 
   * or to compute the result in some other efficient way.
   */
  public boolean hasChildren(Object object)
  {
    return !getChildren(object).isEmpty();
  }

  /**
   * If this is defined to be something other than an empty list, it is used to implement {@link #getChildren getChildren} 
   * and to deduce the EMF feature in the AddCommand and RemoveCommand in {@link #createCommand createCommand}.
   * If you override those, then you don't need to implement this.
   */
  protected Collection getChildrenReferences(Object object)
  {
    if (childrenReferences == null)
    {
      childrenReferences = new ArrayList();
    }
    return childrenReferences;
  }

  /**
   * This method is called by {@link #factorRemoveCommand factorRemoveCommand} to retrieve the children objects
   * of the references returned from {@link #getChildrenReferences getChildrenReferences}.
   */
  protected Object getReferenceValue(EObject object, EStructuralFeature reference)
  {
    return object.eGet(reference);
  }

  /**
   * This returns the most appropriate feature of the object into which the given child could be added.
   * This default implementation returns the first reference returned by {@link #getChildrenReferences getChildrenReferences} 
   * that has a type compatible with the child.
   * You can override this to return a better result or to compute it more efficiently.
   */
  protected EReference getChildReference(Object object, Object child)
  {
    if (child instanceof EObject)
    {
      EObject eChild = (EObject)child;

      // Iterate over all the child references to factor each child to the right reference.
      //
      for (Iterator childrenReferences = getChildrenReferences(object).iterator(); childrenReferences.hasNext(); )
      {
        EStructuralFeature eStructuralFeature = (EStructuralFeature)childrenReferences.next();
        EClassifier eType = eStructuralFeature.getEType();

        // If this object is compatible with this reference...
        //
        if (eType.isInstance(eChild))
        {
          return (EReference)eStructuralFeature;
        }
      }
    }

    return null;
  }

  /**
   * If this is defined to be something other than an empty list, it is used to implement {@link #getSetFeature getSetFeature} 
   * and to deduce the EMF feature in the SetCommand {@link #createCommand createCommand}.
   * If you override those, then you don't need to implement this.
   */
  protected Collection getSetFeatures(Object object)
  {
    return Collections.EMPTY_LIST;
  }

  /**
   * This returns the most appropriate feature of the object into which the value be set.
   * This default implementation returns the first feature returned by {@link #getSetFeatures getSetFeatures} 
   * that has a type compatible with the value.
   * You can override this to return a better result or to compute it more efficiently.
   */
  protected EStructuralFeature getSetFeature(Object object, Object value)
  {
    // Iterate over all the set feature to factor each child to the right reference.
    //
    for (Iterator setFeatures = getSetFeatures(object).iterator(); setFeatures.hasNext(); )
    {
      EStructuralFeature eStructuralFeature = (EStructuralFeature)setFeatures.next();
      EClassifier eType = eStructuralFeature.getEType();
      if (eType.isInstance(value))
      {
        return eStructuralFeature;
      }
    }

    return null;
  }

  /**
   * This implements {@link ITreeItemContentProvider#getParent
   * ITreeItemContentProvider.getParent} by returning the MOF object's
   * container.  This is used by certain commands to find an owner, where
   * none is specified, and by the viewers, when trying to locate an
   * arbitrary object within the view (i.e. during select and reveal
   * operation).
   */
  public Object getParent(Object object)
  {
    EObject eObject = (EObject)object;
    Object result  = eObject.eContainer();
    if (result == null)
    {
      result = eObject.eResource();
    }
    return result;
  }

  /**
   * This implements {@link IItemLabelProvider#getImage IItemLabelProvider.getImage} by returning null.
   * Most things really should have an icon, but not having one is technically correct too.
   */
  public Object getImage(Object object)
  {
    return null;
  }

  /**
   * This implements {@link IItemLabelProvider#getText IItemLabelProvider.getText} by simply calling toString on the argument.
   * This will often be correct as is.
   */
  public String getText(Object object)
  {
    return object.toString();
  }

  /**
   * This implements {@link IUpdateableItemText#getUpdateableText IUpdateableItemText.getUpdateableText} 
   * by simply calling {@link #getText}.
   * This will often be correct as is.
   */
  public String getUpdateableText(Object object)
  {
    return getText(object);
  }

  /**
   * This implements {@link IEditingDomainItemProvider#getNewChildDescriptors
   * IEditingDomainItemProvider.getNewChildDescriptors}, returning
   * {@link org.eclipse.emf.edit.command.CommandParameter}s to
   * describe all the possible children that can be added to the specified
   * <code>object</code>.
   * 
   * <p>This implementation invokes {@link #collectNewChildDescriptors},
   * which should be overridden by derived classes, to build this
   * collection.
   *
   * <p>If <code>sibling</code> is non-null, an <code>index</code> is added
   * to each <code>CommandParameter</code> with a multi-valued
   * <code>reference</code>, to ensure that the new child object gets
   * added in the right position.
   */
  public Collection getNewChildDescriptors(Object object, EditingDomain editingDomain, Object sibling)
  {
    EObject eObject = (EObject) object;

    // build the collection of new child descriptors
    Collection newChildDescriptors = new ArrayList();
    collectNewChildDescriptors(newChildDescriptors, object);

    // if a sibling has been specified, add the best index possible to
    // each CommandParameter
    if (sibling != null)
    {
      // find the reference that contains sibling, its "index" in the
      // collection, and, if it is multi-valued, the index of the sibling
      // within it
      EStructuralFeature siblingReference = null;
      int siblingReferenceIndex = 0;
      int siblingIndex = -1;

      for (Iterator references = getChildrenReferences(object).iterator();
           references.hasNext(); siblingReferenceIndex++)
      {
        EStructuralFeature eStructuralFeature = (EStructuralFeature) references.next();
        Object o = eObject.eGet(eStructuralFeature);
        if (eStructuralFeature.isMany())
        {
          siblingIndex = ((List) o).indexOf(sibling);
          if (siblingIndex > -1)
          {
            siblingReference = eStructuralFeature;
            break;
          }
        }
        else if (o == sibling)
        {
          siblingReference = eStructuralFeature;
          break;
        }
      }
      
      // for each CommandParameter with a non-null, multi-valued
      // reference...
      for (Iterator parameters = newChildDescriptors.iterator();
           siblingReference != null && parameters.hasNext(); )
      {
        Object p = parameters.next();
        if (p instanceof CommandParameter)
        {
          CommandParameter parameter = (CommandParameter)p;
          EReference childReference = parameter.getEReference();
          if (childReference == null || !childReference.isMany())
          {
            continue;
          }

          // find the index of the new child's reference, and compare it to
          // that of the sibling...
          int childReferenceIndex = 0;
          for (Iterator references = getChildrenReferences(object).iterator();
               references.hasNext(); childReferenceIndex++)
          {
            EStructuralFeature eStructuralFeature = (EStructuralFeature)references.next();
            if (eStructuralFeature == childReference)
            {
              if (childReferenceIndex < siblingReferenceIndex)
              {
                // make child last in previous reference
                parameter.index = ((List)eObject.eGet(eStructuralFeature)).size();
              }
              else if (childReferenceIndex > siblingReferenceIndex)
              {
                // make child first in following reference
                parameter.index = 0;
              }
              else
              {
                // make child immediately following in same reference
                parameter.index = siblingIndex + 1;
              }
            }
          }
        }
      }
    }
    return newChildDescriptors;
  }

  /**
   * This adds to <code>newChildDescriptors</code>, a collection of {@link
   * org.eclipse.emf.edit.command.CommandParameter}s, parameters for
   * possible children of the specified <code>object</code>, when viewed as
   * an instance of the type for which this is an item provider.  This
   * implementation adds nothing to the collection, but derived classes
   * should override this method, invoking the superclass implementation and
   * then adding to the collection.
   */
  protected void collectNewChildDescriptors(Collection newChildDescriptors, Object object)    
  {
    return;
  }

  /**
   * This implements delegated command creation for the given object.
   */
  public Command createCommand(final Object object, final EditingDomain domain, Class commandClass, CommandParameter commandParameter)
  {
    if (commandClass == SetCommand.class)
    {
      return 
        createSetCommand
          (domain, 
           commandParameter.getEOwner(), 
           commandParameter.getEStructuralFeature() != null ?
             commandParameter.getEStructuralFeature() :
             getSetFeature(commandParameter.getEOwner(), commandParameter.getValue()),
           commandParameter.getValue());
    }
    else if (commandClass == CopyCommand.class)
    {
      return createCopyCommand(domain, commandParameter.getEOwner(), (CopyCommand.Helper)commandParameter.getValue());
    }
    else if (commandClass == CreateCopyCommand.class)
    {
      return createCreateCopyCommand(domain, commandParameter.getEOwner(), (CopyCommand.Helper)commandParameter.getValue());
    }
    else if (commandClass == InitializeCopyCommand.class)
    {
      return createInitializeCopyCommand(domain, commandParameter.getEOwner(), (CopyCommand.Helper)commandParameter.getValue());
    }
    else if (commandClass == RemoveCommand.class)
    {
      if (commandParameter.getEReference() != null)
      {
        return createRemoveCommand(domain, commandParameter.getEOwner(), commandParameter.getEReference(), commandParameter.getCollection());
      }
      else
      {
        return factorRemoveCommand(domain, commandParameter);
      }
    }
    else if (commandClass == AddCommand.class)
    {
      if (commandParameter.getEReference() != null)
      {
        return 
          createAddCommand
            (domain, 
             commandParameter.getEOwner(), 
             commandParameter.getEReference(), 
             commandParameter.getCollection(),
             commandParameter.getIndex());
      }
      else
      {
        return factorAddCommand(domain, commandParameter);
      }
    }
    else if (commandClass == MoveCommand.class)
    {
      if (commandParameter.getEReference() != null)
      {
        return 
          createMoveCommand
            (domain, 
             commandParameter.getEOwner(), 
             commandParameter.getEReference(), 
             commandParameter.getEValue(), 
             commandParameter.getIndex());
      }
      else
      {
        return factorMoveCommand(domain, commandParameter);
      }
    }
    else if (commandClass == ReplaceCommand.class)
    {
      return 
        createReplaceCommand
          (domain, commandParameter.getEOwner(), commandParameter.getEReference(), (EObject)commandParameter.getValue(), commandParameter.getCollection());
    }
    else if (commandClass == DragAndDropCommand.class)
    {
      DragAndDropCommand.Detail detail = (DragAndDropCommand.Detail)commandParameter.getFeature();
      return 
        createDragAndDropCommand
          (domain, commandParameter.getOwner(), detail.location, detail.operations, detail.operation, commandParameter.getCollection());
    }
    else if (commandClass == CreateChildCommand.class)
    {
      CommandParameter newChildParameter = (CommandParameter) commandParameter.getValue();
      return 
        createCreateChildCommand
          (domain,
           commandParameter.getEOwner(), 
           newChildParameter.getEReference(), 
           newChildParameter.getEValue(),
           newChildParameter.getIndex(),
           commandParameter.getCollection());      
    }
    else
    {
      return UnexecutableCommand.INSTANCE;
    }
  }


  /**
   * This creates a primitive {@link org.eclipse.emf.edit.command.SetCommand}.
   */
  protected Command createSetCommand(EditingDomain domain, EObject owner, EStructuralFeature feature, Object value) 
  {
    return new SetCommand(domain, owner, feature, value);
  }

  /**
   * This creates a primitive {@link org.eclipse.emf.edit.command.CopyCommand}.
   */
  protected Command createCopyCommand(EditingDomain domain, EObject owner, CopyCommand.Helper helper)
  {
    return new CopyCommand(domain, owner, helper, domain.getOptimizeCopy());
  }

  /**
   * This creates a primitive {@link org.eclipse.emf.edit.command.CreateCopyCommand}.
   */
  protected Command createCreateCopyCommand(EditingDomain domain, EObject owner, CopyCommand.Helper helper) 
  {
    return new CreateCopyCommand(domain, owner, helper);
  }

  /**
   * This creates a primitive {@link org.eclipse.emf.edit.command.InitializeCopyCommand}.
   */
  protected Command createInitializeCopyCommand(EditingDomain domain, EObject owner, CopyCommand.Helper helper) 
  {
    return new InitializeCopyCommand(domain, owner, helper);
  }

  /**
   * This creates a primitive {@link org.eclipse.emf.edit.command.RemoveCommand}.
   */
  protected Command createRemoveCommand(EditingDomain domain, EObject owner, EReference feature, Collection collection) 
  {
    return new RemoveCommand(domain, owner, feature, collection);
  }

  protected Command createRemoveCommand(EditingDomain domain, EObject owner, EStructuralFeature feature, Collection collection) 
  {
    return new RemoveCommand(domain, owner, feature, collection);
  }

  /**
   * This creates a primitive {@link org.eclipse.emf.edit.command.ReplaceCommand}.
   */
  protected Command createReplaceCommand(EditingDomain domain, EObject owner, EReference feature, EObject value, Collection collection) 
  {
    return new ReplaceCommand(domain, owner, feature, value, collection);
  }

  /**
   * This creates a primitive {@link org.eclipse.emf.edit.command.AddCommand}.
   */
  protected Command createAddCommand(EditingDomain domain, EObject owner, EStructuralFeature feature, Collection collection, int index) 
  {
    return new AddCommand(domain, owner, feature, collection, index);
  }

  /**
   * This creates a primitive {@link org.eclipse.emf.edit.command.MoveCommand}.
   */
  protected Command createMoveCommand(EditingDomain domain, EObject owner, EReference feature, EObject value, int index) 
  {
    return new MoveCommand(domain, owner, feature, value, index);
  }

  protected Command createMoveCommand(EditingDomain domain, EObject owner, EStructuralFeature feature, EObject value, int index) 
  {
    return new MoveCommand(domain, owner, feature, value, index);
  }

  /**
   * This creates a primitive {@link org.eclipse.emf.edit.command.DragAndDropCommand}.
   */
  protected Command createDragAndDropCommand
    (EditingDomain domain, Object owner, float location, int operations, int operation, Collection collection)
  {
    return new DragAndDropCommand(domain, owner, location, operations, operation, collection);
  }

  /**
   * This creates a primitive {@link org.eclipse.emf.edit.command.CreateChildCommand}.
   */
  protected Command createCreateChildCommand(EditingDomain domain, EObject owner, EReference feature, EObject value, int index, Collection collection)
  {
    return new CreateChildCommand(domain, owner, feature, value, index, collection, this);
  }

  /**
   * This method factors a {@link org.eclipse.emf.edit.command.RemoveCommand} for a collection of objects 
   * into one or more primitive remove commands, 
   * i.e., one per unique reference.
   */
  protected Command factorRemoveCommand(EditingDomain domain, CommandParameter commandParameter)
  {
    if (commandParameter.getCollection() == null || commandParameter.getCollection().isEmpty())
    {
      return UnexecutableCommand.INSTANCE;
    }

    final EObject eObject = commandParameter.getEOwner();
    final List list = new ArrayList(commandParameter.getCollection());

    CompoundCommand removeCommand = new CompoundCommand(CompoundCommand.MERGE_COMMAND_ALL);
      
    // Iterator over all the child references to factor each child to the right reference.
    //
    for (Iterator childrenReferences = getChildrenReferences(eObject).iterator(); childrenReferences.hasNext(); )
    {
      EStructuralFeature eStructuralFeature = (EStructuralFeature)childrenReferences.next();

      // If it is a list type value...
      // 
      if (eStructuralFeature.isMany())
      {
        List value = (List)getReferenceValue(eObject, eStructuralFeature);

        // These will be the children beloging to this reference.
        //
        Collection childrenOfThisReference = new ArrayList();
        for (ListIterator objects = list.listIterator(); objects.hasNext(); )
        {
          Object o = objects.next();

          // Is this object in this reference...
          //
          if (value.contains(o))
          {
            // Add it to the list and remove it from the other list.
            //
            childrenOfThisReference.add(o);
            objects.remove();
          }
        }

        // If we have children to remove for this reference, create a command for it.
        //
        if (!childrenOfThisReference.isEmpty())
        {
          removeCommand.append(createRemoveCommand(domain, eObject, eStructuralFeature, childrenOfThisReference));
        }
      }
      else 
      {
        // It's just a single value
        //
        final Object value = getReferenceValue(eObject, eStructuralFeature);
        for (ListIterator objects = list.listIterator(); objects.hasNext(); )
        {
          Object o = objects.next();

          // Is this object in this reference...
          //
          if (o == value)
          {
            // Create a command to set this to null and remove the object from the other list.
            //
            Command setCommand = createSetCommand(domain, eObject, eStructuralFeature, null);
            removeCommand.append
              (new CommandWrapper(setCommand)
               {
                 protected Collection affected;

                 public void execute()
                 {
                   super.execute();
                   affected = Collections.singleton(eObject);
                 }

                 public void undo()
                 {
                   super.undo();
                   affected = Collections.singleton(value);
                 }

                 public void redo()
                 {
                   super.redo();
                   affected = Collections.singleton(eObject);
                 }

                 public Collection getResult()
                 {
                   return Collections.singleton(value);
                 }

                 public Collection getAffectedObjects()
                 {
                   return affected;
                 }
               });
            objects.remove();
            break;
          }
        } 
      }
    } 

    // If all the objects are used up by the above, then we can't do the command.
    //
    if (list.isEmpty())
    {
      return removeCommand.unwrap();
    }
    else
    {
      removeCommand.dispose();
      return UnexecutableCommand.INSTANCE;
    }
  }

  /**
   * This method factors an {@link org.eclipse.emf.edit.command.AddCommand} for a collection of objects
   * into one or more primitive add command, 
   * i.e., one per unique reference.
   */
  protected Command factorAddCommand(EditingDomain domain, CommandParameter commandParameter)
  {
    if (commandParameter.getCollection() == null || commandParameter.getCollection().isEmpty())
    {
      return UnexecutableCommand.INSTANCE;
    }

    final EObject eObject = commandParameter.getEOwner();
    final List list = new ArrayList(commandParameter.getCollection());
    int index = commandParameter.getIndex();

    CompoundCommand addCommand = new CompoundCommand(CompoundCommand.MERGE_COMMAND_ALL);
      
    while (!list.isEmpty())
    {
      Iterator children = list.listIterator();
      final Object firstChild = children.next();
      EStructuralFeature childReference = getChildReference(eObject, firstChild);

      if (childReference == null)
      {
        break;
      }
      // If it is a list type value...
      // 
      else if (childReference.isMany())
      {
        // These will be the children belonging to this reference.
        //
        Collection childrenOfThisReference = new ArrayList();
        childrenOfThisReference.add(firstChild);
        children.remove();

        // Consume the rest of the appropriate children.
        //
        while (children.hasNext())
        {
          Object child = children.next();
          
          // Is this child in this reference...
          //
          if (getChildReference(eObject, child) == childReference)
          {
            // Add it to the list and remove it from the other list.
            //
            childrenOfThisReference.add(child);
            children.remove();
          }
        }

        // Create a command for this reference, 
        //
        addCommand.append(createAddCommand(domain, eObject, childReference, childrenOfThisReference, index));

        if (index >= childrenOfThisReference.size())
        {
          index -= childrenOfThisReference.size();
        }
        else
        {
          index = CommandParameter.NO_INDEX;
        }
      }
      else if (eObject.eGet(childReference) == null)
      {
        Command setCommand = createSetCommand(domain, eObject, childReference, firstChild);
        addCommand.append
          (new CommandWrapper(setCommand)
           {
             protected Collection affected;

             public void execute()
             {
               super.execute();
               affected = Collections.singleton(firstChild);
             }

             public void undo()
             {
               super.undo();
               affected = Collections.singleton(eObject);
             }

             public void redo()
             {
               super.redo();
               affected = Collections.singleton(firstChild);
             }

             public Collection getResult()
             {
               return Collections.singleton(firstChild);
             }

             public Collection getAffectedObjects()
             {
               return affected;
             }
           });
        children.remove();
      }
      else 
      {
        break;
      }
    }

    // If all the objects aren't used up by the above, then we can't do the command.
    //
    if (list.isEmpty())
    {
      return addCommand.unwrap();
    }
    else
    {
      addCommand.dispose();
      return UnexecutableCommand.INSTANCE;
    }
  }

  /**
   * This method factors a {@link org.eclipse.emf.edit.command.MoveCommand} to determine the feature.
   */
  protected Command factorMoveCommand(EditingDomain domain, CommandParameter commandParameter)
  {
    final EObject eObject = commandParameter.getEOwner();
    final EObject value = commandParameter.getEValue();
    int index = commandParameter.getIndex();

    EStructuralFeature childReference = getChildReference(eObject, value);

    if (childReference != null && childReference.isMany())
    {
      // Compute the relative index as best as possible.
      //
      Collection result = new ArrayList();
      for (Iterator childrenReferences = getChildrenReferences(eObject).iterator(); childrenReferences.hasNext(); )
      {
        EReference childrenReference = (EReference)childrenReferences.next();
        if (childrenReference == childReference)
        {
          break;
        } 

        if (childrenReference.isMany())
        {
          index -= ((List)(eObject).eGet(childrenReference)).size();
        }
        else if (eObject.eGet(childrenReference) != null)
        {
          index -= 1;
        }
      }

      // Create a command for this reference, 
      //
      return createMoveCommand(domain, eObject, childReference, value, index);
    }
    else
    {
      return UnexecutableCommand.INSTANCE;
    }
  }

  public void setTarget(Notifier target)
  {
    // All this logic is so that we only create a list if the adapter is set to more than one target.
    //
    if (this.target != null)
    {
      if (this.target != target)
      {
        if (targets == null)
        {
          targets = new ArrayList();
        }
        targets.add(this.target);
        super.setTarget(target);
      }
    }
    else
    {
      super.setTarget(target);
    }
  }

  /**
   * This will remove this adapter from all its the targets.
   */
  public void dispose()
  {
    if (target != null)
    {
      target.eAdapters().remove(this);
      if (targets != null)
      {
        for (Iterator i = targets.iterator(); i.hasNext(); )
        {
          Notifier otherTarget = (Notifier)i.next();
          otherTarget.eAdapters().remove(this);
        }
      }
    }
  }

  /**
   * This is a convenience method that creates a <code>CommandParameter</code>
   * for a given parent feature and child object.
   */
  protected CommandParameter createChildParameter(Object feature, Object child)
  {
    return new CommandParameter(null, feature, child);
  }

  /**
   * This returns the result collection for {@link CreateChildCommand}.
   */
  public Collection getCreateChildResult(Object child)
  {
    return Collections.singletonList(child);
  }

  /**
   * This returns the label for {@link CreateChildCommand}.
   */
  public String getCreateChildText(Object owner, Object feature, Object child, Collection selection)
  {
    // The try/catch provides backwards compatibility with the old resource
    // key, "_UI_CreateChild_label", and should be removed at the next
    // release, when we can expect users to do a regen.
    //
    try
    {
      return getResourceLocator().getString(
        "_UI_CreateChild_text",
        new Object[] { 
          getTypeText(child),
          getFeatureText(feature),
          getTypeText(owner)
        });
    }
    catch (MissingResourceException e)
    {
      return getResourceLocator().getString(
        "_UI_CreateChild_label",
        new Object[] { 
          getTypeText(child),
          getFeatureText(feature),
          getTypeText(owner)
        });
    }
  }

  /**
   * This returns the description for {@link CreateChildCommand}.
   */
  public String getCreateChildDescription(Object owner, Object feature, Object child, Collection selection)
  {
    Object selectionObject = selection == null || selection.isEmpty() ?  null : selection.iterator().next();
    if (owner != selectionObject)
    {
      return 
        getResourceLocator().getString
          ("_UI_CreateSibling_description", 
           new Object[] { getTypeText(child), getFeatureText(feature), getTypeText(selectionObject) });
    }

    return 
      getResourceLocator().getString
        ("_UI_CreateChild_description", 
         new Object[] { getTypeText(child), getFeatureText(feature), getTypeText(owner) });
  }

  /**
   * This returns the tool tip text for {@link CreateChildCommand}.
   */
  public String getCreateChildToolTipText(Object owner, Object feature, Object child, Collection selection)
  {
    return 
      getResourceLocator().getString
        ("_UI_CreateChild_tooltip",
         new Object[] { getTypeText(child), getFeatureText(feature), getTypeText(owner) });
  }

  /**
   * This returns the icon image for {@link CreateChildCommand}.
   */
  public Object getCreateChildImage(Object owner, Object feature, Object child, Collection selection)
  {
    String name = "full/ctool16/CreateChild";
    
    if (feature instanceof EReference && child instanceof EObject)
    {
      EReference reference = (EReference)feature;
      EClass parentClass = reference.getEContainingClass();
      EClass childClass = ((EObject)child).eClass();
      name = "full/ctool16/Create" + parentClass.getName() + "_" + reference.getName() + "_" + childClass.getName();
    }
    return getResourceLocator().getImage(name);
  }

  /**
   * This looks up the name of the type of the specified object.
   */
  protected String getTypeText(Object object)
  {
    if (object instanceof EObject)
    {
      String typeKey = ((EObject)object).eClass().getName();
      try
      {
        return getResourceLocator(object).getString("_UI_" + typeKey + "_type");
      }
      catch (MissingResourceException e)
      {
        return typeKey;
      }
    }
    return getString("_UI_Unknown_type");
  }

  /**
   * This looks up the name of the specified feature.
   */
  protected String getFeatureText(Object feature)
  {
    String featureKey;
    if (feature instanceof EReference)
    {
      EReference reference = (EReference)feature;
      featureKey = reference.getEContainingClass().getName() + "_" + reference.getName();
    }
    else
    {
      featureKey = "Unknown";
    }
    return getResourceLocator().getString("_UI_" + featureKey + "_feature");
  }

  /**
   * Get the resource locator for this adapter's resources.
   */
  protected ResourceLocator getResourceLocator()
  {
    return EMFEditPlugin.INSTANCE;
  }

  /**
   * Get the resource locator from the adapter of the object, if possible.
   * it can be any object, 
   * i.e., it may be not the type object for which this adapter is applicable.
   */
  protected ResourceLocator getResourceLocator(Object anyObject)
  {
    if (adapterFactory instanceof ComposeableAdapterFactory)
    {
      Object adapter = ((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory().adapt(anyObject, IItemLabelProvider.class);
      if (adapter instanceof ResourceLocator)
      {
        return (ResourceLocator)adapter;
      }
    }

    return getResourceLocator();
  }

  /**
   * Gets the root factory if this local adapter factory is composed, 
   * otherwise just the local one.
   */
  protected AdapterFactory getRootAdapterFactory()
  {
    if (adapterFactory instanceof ComposeableAdapterFactory)
    {
      return ((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory();
    }

    return adapterFactory;
  }

  /**
   * Get the base URL from the resource locator.
   */
  public URL getBaseURL()
  {
    return getResourceLocator().getBaseURL();
  }

  /**
   * Get an image from the resource locator.
   */
  public Object getImage(String key)
  {
    return getResourceLocator().getImage(key);
  }

  /**
   * Get a translated string from the resource locator.
   */
  public String getString(String key)
  {
    return getResourceLocator().getString(key);
  }

  /**
   * Get a translated string from the resource locator, with substitutions.
   */
  public String getString(String key, Object [] substitutions)
  {
    return getResourceLocator().getString(key, substitutions);
  }

  /**
   * Get a translated string from the resource locator, substituting another
   * such translated string.
   */
  protected String getString(String key, String s0)
  {
    ResourceLocator resourceLocator = getResourceLocator();
    return resourceLocator.getString
      (key, new Object[] { resourceLocator.getString(s0) });
  }

  /**
   * Get a translated string from the resource locator, substituting two
   * other such translated strings.
   */
  protected String getString(String key, String s0, String s1)
  {
    ResourceLocator resourceLocator = getResourceLocator();
    return resourceLocator.getString
      (key, new Object[] { resourceLocator.getString(s0), resourceLocator.getString(s1) });
  }
}
