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
 * $Id: ItemProviderAdapter.java,v 1.25.2.2 2007/05/18 18:57:57 marcelop Exp $
 */
package org.eclipse.emf.edit.provider;


import java.net.URL;
import java.util.AbstractList;
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
import org.eclipse.emf.common.notify.Notifier;
import org.eclipse.emf.common.notify.impl.AdapterImpl;
import org.eclipse.emf.common.util.BasicEList;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.ResourceLocator;
import org.eclipse.emf.common.util.UniqueEList;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.util.FeatureMap;
import org.eclipse.emf.ecore.util.FeatureMapUtil;
import org.eclipse.emf.edit.EMFEditPlugin;
import org.eclipse.emf.edit.command.AddCommand;
import org.eclipse.emf.edit.command.CommandActionDelegate;
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
import org.eclipse.emf.edit.domain.AdapterFactoryEditingDomain;
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
   * This is used to store all the children features. Derived classes should add features to this vector.
   */
  protected List childrenFeatures;

  /**
   * This stored the children references, but attributes may now contribute children, too. It is still present to
   * support existing subclasses.
   * @deprecated As of EMF 2.0, replaced by {@link #childrenFeatures}.
   */
  protected List childrenReferences;

  /**
   * This is used to implement {@link IChangeNotifier}.
   */
  protected IChangeNotifier changeNotifier;

  /**
   * This keeps track of all the targets to which this adapter is set.
   */
  protected List targets;

  /**
   * When {@link ChildrenStore}s are to be used to cache children (typically to hold wrappers for non-EObject
   * children), this maps adapted objects to their corresponding stores. Stores should be accessed and created
   * via {@link #getChildrenStore getChildrenStore} and {@link #createChildrenStore createChildrenStore}.
   */
  protected Map childrenStoreMap;

  /**
   * This holds children wrappers that are {@link #wrap created} by this item provider, so that they can be {@link
   * #dispose disposed} with it.
   */
  protected Disposable wrappers;

  /**
   * This caches the result returned by {@link #isWrappingNeeded isWrappingNeeded} so that it need not be recomputed
   * each time.
   */
  protected Boolean wrappingNeeded;

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
    return getPropertyDescriptor(object, property).getPropertyValue(object);
  }

  /**
   * This implements PropertySource by delegating to the descriptor,
   * which is assumed to support the IItemPropertyDescriptor interface
   */
  public boolean isPropertySet(Object object, String property) 
  {
    return getPropertyDescriptor(object, property).isPropertySet(object);
  }

  /**
   * This implements PropertySource.resetPropertyValue by delegating to the descriptor,
   * which is assumed to support the IItemPropertyDescriptor interface
   */
  public void resetPropertyValue(Object object, String property) 
  {
    getPropertyDescriptor(object, property).resetPropertyValue(object);
  }

  /**
   * This implements PropertySource by delegating to the descriptor,
   * which is assumed to support the IItemPropertyDescriptor interface
   */
  public void setPropertyValue(Object object, String property, Object value) 
  {
    getPropertyDescriptor(object, property).setPropertyValue(object, value);
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
   * This implements {@link ITreeItemContentProvider#getChildren ITreeItemContentProvider.getChildren}. If children are
   * already cached in a {@link ChildrenStore}, they are returned. Otherwise, children are collected from the features
   * returned by {@link #getChildrenFeatures getChildrenFeatures}.  The collected children may or may not be cached,
   * depending on the result of {@link #createChildrenStore createChildrenStore}; by default, no store is returned if
   * {@link #getChildrenFeatures getChildrenFeatures} returns only containment references. All children are optionally
   * {@link #wrap wrapped} before being cached and returned. Subclasses may override {@link #createWrapper
   * createWrapper} to specify when and with what to wrap children.
   */
  public Collection getChildren(Object object)
  {
    ChildrenStore store = getChildrenStore(object);
    if (store != null)
    {
      return store.getChildren();
    }

    store = createChildrenStore(object);
    List result = store != null ? null : new ArrayList();
    EObject eObject = (EObject)object;

    for (Iterator i = getAnyChildrenFeatures(object).iterator(); i.hasNext();)
    {
      EStructuralFeature feature = (EStructuralFeature)i.next();
      if (feature.isMany())
      {
        List children = (List)eObject.eGet(feature);
        int index = 0;
        for (Iterator ci = children.iterator(); ci.hasNext(); index++)
        {
          Object child = wrap(eObject, feature, ci.next(), index);
          if (store != null)
          {
            store.getList(feature).add(child);
          }
          else
          {
            result.add(child);
          }
        }
      }
      else
      {
        Object child = eObject.eGet(feature);
        if (child != null)
        {
          child = wrap(eObject, feature, child, CommandParameter.NO_INDEX);
          if (store != null)
          {
            store.setValue(feature, child);
          }
          else
          {
            result.add(child);
          }
        }
      }
    }
    return store != null ? store.getChildren() : result;
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
   * If this is defined to be something other than an empty list, it is used to implement {@link #getChildren
   * getChildren}, including in determining whether to cache children and, if so, in setting up the store. It is also
   * used to deduce the appropriate feature for an <code>AddCommand</code>, <code>RemoveCommand</code> or
   * <code>MoveCommand</code> in {@link #createCommand createCommand}. If you override those methods, then you don't
   * need to implement this.
   */
  protected Collection getChildrenFeatures(Object object)
  {
    if (childrenFeatures == null)
    {
      childrenFeatures = new ArrayList();
    }
    return childrenFeatures;
  }

  /**
   * This returned the children references, but it has been replaced since attributes may now contribute children,
   * too. If the replacement method is not overridden to return a non-empty list, this method will still be called to
   * provide backwards compatibility.
   * @deprecated As of EMF 2.0, replaced by {@link #getChildrenFeatures getChildrenFeatures}.
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
   * This is a temporary way to get the structural features that contribute children. It first calls the new
   * {link #getChildrenFeatues getChildrenFeatures} method and then, if the result is empty, tries the deprecated
   * {@link #getChildrenReferences getChildrenReferences} method. It is used, instead of just the new method,
   * throughout this class.
   */
  private Collection getAnyChildrenFeatures(Object object)
  {
    Collection result = getChildrenFeatures(object);
    return result.isEmpty() ? getChildrenReferences(object) : result;
  }

  /**
   * This method is called by {@link #factorRemoveCommand factorRemoveCommand} to retrieve the children objects of the
   * features returned from {@link #getChildrenFeatures getChildrenFeatures}. For references, this default
   * implementation calls the deprecated {@link #getReferenceValue getReferenceValue} to provide backwards
   * compatibility.
   */
  protected Object getFeatureValue(EObject object, EStructuralFeature feature)
  {
    // Use an existing getReferenceValue() override.
    //
    if (feature instanceof EReference)
    {
      return getReferenceValue(object, (EReference)feature);
    }

    return object.eGet(feature);
  }

  /**
   * This method returned the children objects of the given reference, but it has been replaced since attributes may
   * now contribute children, too.  The replacement method still calls this method for references, to provide backwards
   * compatibility.
   * @deprecated As of EMF 2.0, replaced by {@link #getFeatureValue getFeatureValue}.
   */
  protected Object getReferenceValue(EObject object, EReference reference)
  {
    return object.eGet(reference);
  }

  /**
   * This returns the most appropriate feature of the object into which the given child could be added.
   * This default implementation first calls the deprecated {@link #getChildReference getChildReference}, for backwards
   * compatibility. If that does not yield a non-null result, it returns the first feature returned by {@link
   * #getChildrenFeatures getChildrenFeatures} that has a type compatible with the child. You can override this to
   * return a better result or to compute it more efficiently.
   */
  protected EStructuralFeature getChildFeature(Object object, Object child)
  {
    // First, try an existing implementation of getChildReference().  This provides backwards compatibility if that
    // method, now deprecated, was overridden.
    //
    EStructuralFeature oldFeature = getChildReference(object, child);
    if (oldFeature != null) return oldFeature;
    
    for (Iterator features = getAnyChildrenFeatures(object).iterator(); features.hasNext(); )
    {
      EStructuralFeature feature = (EStructuralFeature)features.next();
      if (feature.getEType().isInstance(child))
      {
        return feature;
      }
    }
    return null;
  }

  /**
   * This returned the most appropriate reference for a given child, but has been replaced since attributes may now
   * contribute children, too. The replacement first tries calling this method, for backwards compatibility.
   * @deprecated As of EMF 2.0, replaced by {@link #getChildFeature getChildFeature}.
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
        EReference eReference = (EReference)childrenReferences.next();
        EClassifier eType = eReference.getEType();

        // If this object is compatible with this reference...
        //
        if (eType.isInstance(eChild))
        {
          return eReference;
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
   * ITreeItemContentProvider.getParent} by returning the EMF object's
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
   * This adds an overlay to the given image if the object is controlled.
   */
  protected Object overlayImage(Object object, Object image)
  {
    if (AdapterFactoryEditingDomain.isControlled(object))
    {
      List images = new ArrayList(2);
      images.add(image);
      images.add(EMFEditPlugin.INSTANCE.getImage("full/ovr16/ControlledObject"));
      image = new ComposedImage(images);
    }
    return image;
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
   * This crops the given text to exclude any control characters. The first such character and all following it are replaced by "..."
   * @since 2.2.0
   */
  public String crop(String text)
  {
    if (text != null)
    {
      char[] chars = text.toCharArray();
      for (int i = 0; i < chars.length; i++)
      {
        if (Character.isISOControl(chars[i]))
        {
          return text.substring(0, i) + "...";
        }
      }
    }
    return text;
  }

  /**
   * This implements {@link IEditingDomainItemProvider#getNewChildDescriptors
   * IEditingDomainItemProvider.getNewChildDescriptors}, returning {@link
   * org.eclipse.emf.edit.command.CommandParameter}s to describe all the possible children that can be added to the
   * specified <code>object</code>.
   * 
   * <p>This implementation invokes {@link #collectNewChildDescriptors collectNewChildDescriptors}, which should be
   * overridden by derived classes, to build this collection.
   *
   * <p>If <code>sibling</code> is non-null, an <code>index</code> is added to each <code>CommandParameter</code> with
   * a multi-valued <code>feature</code>, to ensure that the new child object gets added in the right position.
   */
  public Collection getNewChildDescriptors(Object object, EditingDomain editingDomain, Object sibling)
  {
    EObject eObject = (EObject)object;

    // Build the collection of new child descriptors.
    //
    Collection newChildDescriptors = new ArrayList();
    collectNewChildDescriptors(newChildDescriptors, object);

    // If a sibling has been specified, add the best index possible to each CommandParameter.
    //
    if (sibling != null)
    {
      sibling = unwrap(sibling);

      // Find the index of a feature containing the sibling, or an equivalent value, in the collection of children
      // features.
      //
      Collection childrenFeatures = getAnyChildrenFeatures(object);
      int siblingFeatureIndex = -1;
      int i = 0;

      FEATURES_LOOP:
      for (Iterator features = childrenFeatures.iterator(); features.hasNext(); i++)
      {
        EStructuralFeature feature = (EStructuralFeature)features.next();
        Object featureValue = eObject.eGet(feature);
        if (feature.isMany())
        {
          for (Iterator values = ((Collection)featureValue).iterator(); values.hasNext(); )
          {
            Object value = values.next();
            if (isEquivalentValue(sibling, value))
            {
              siblingFeatureIndex = i;
              break FEATURES_LOOP;
            }
          }
        }
        else if (isEquivalentValue(sibling, featureValue))
        {
          siblingFeatureIndex = i;
          break FEATURES_LOOP;
        }
      }

      // For each CommandParameter with a non-null, multi-valued structural feature...
      //
      DESCRIPTORS_LOOP:
      for (Iterator descriptors = newChildDescriptors.iterator(); descriptors.hasNext(); )
      {
        Object d = descriptors.next();
        if (d instanceof CommandParameter)
        {
          CommandParameter parameter = (CommandParameter)d;
          EStructuralFeature childFeature = parameter.getEStructuralFeature();
          if (childFeature == null || !childFeature.isMany())
          {
            continue DESCRIPTORS_LOOP;
          }

          // Look for the sibling value or an equivalent in the new child's feature. If it is found, the child should
          // immediately follow it.
          //
          i = 0;
          for (Iterator values = ((Collection)eObject.eGet(childFeature)).iterator(); values.hasNext(); i++)
          {
            Object v = values.next();
            if (isEquivalentValue(sibling, v))
            {
              parameter.index = i + 1;
              continue DESCRIPTORS_LOOP;
            }
          }

          // Otherwise, if a sibling feature was found, iterate through the children features to find the index of
          // the child feature... 
          //
          if (siblingFeatureIndex != -1)
          {
            i = 0;
            for (Iterator features = childrenFeatures.iterator(); features.hasNext(); i++)
            {
              EStructuralFeature feature = (EStructuralFeature)features.next();
            
              if (feature == childFeature)
              {
                // If the child feature follows the sibling feature, the child should be first in its feature.
                //
                if (i > siblingFeatureIndex)
                {
                  parameter.index = 0;
                }
                continue DESCRIPTORS_LOOP;
              }
            }
          }
        }
      }
    }
    return newChildDescriptors;
  }

  /**
   * Returns whether the given value is to be considered equivalent to the given reference value.
   * This is true if it is the reference value or if it is a feature map entry whose value is the reference value.
   */
  protected boolean isEquivalentValue(Object value, Object referenceValue)
  {
    if (value == referenceValue)
    {
      return true;
    }
      
    if (value instanceof FeatureMap.Entry)
    {
      Object entryValue = ((FeatureMap.Entry)value).getValue();
      if (entryValue == referenceValue)
      {
        return true;
      }
    }

    return false;
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
  public Command createCommand(Object object, EditingDomain domain, Class commandClass, CommandParameter commandParameter)
  {
    // Commands should operate on the values, not their wrappers.  If the command's values needed to be unwrapped,
    // we'll back get a new CommandParameter.
    //
    CommandParameter oldCommandParameter = commandParameter;
    commandParameter = unwrapCommandValues(commandParameter, commandClass);

    Command result = UnexecutableCommand.INSTANCE;

    if (commandClass == SetCommand.class)
    {
      result =
        createSetCommand
          (domain, 
           commandParameter.getEOwner(), 
           commandParameter.getEStructuralFeature() != null ?
             commandParameter.getEStructuralFeature() :
             getSetFeature(commandParameter.getEOwner(), commandParameter.getValue()),
           commandParameter.getValue(),
           commandParameter.getIndex());
    }
    else if (commandClass == CopyCommand.class)
    {
      result = createCopyCommand(domain, commandParameter.getEOwner(), (CopyCommand.Helper)commandParameter.getValue());
    }
    else if (commandClass == CreateCopyCommand.class)
    {
      result = createCreateCopyCommand(domain, commandParameter.getEOwner(), (CopyCommand.Helper)commandParameter.getValue());
    }
    else if (commandClass == InitializeCopyCommand.class)
    {
      result = createInitializeCopyCommand(domain, commandParameter.getEOwner(), (CopyCommand.Helper)commandParameter.getValue());
    }
    else if (commandClass == RemoveCommand.class)
    {
      if (commandParameter.getEStructuralFeature() != null)
      {
        result = createRemoveCommand(domain, commandParameter.getEOwner(), commandParameter.getEStructuralFeature(), commandParameter.getCollection());
      }
      else
      {
        result = factorRemoveCommand(domain, commandParameter);
      }
    }
    else if (commandClass == AddCommand.class)
    {
      if (commandParameter.getEStructuralFeature() != null)
      {
        result = 
          createAddCommand
            (domain, 
             commandParameter.getEOwner(), 
             commandParameter.getEStructuralFeature(), 
             commandParameter.getCollection(),
             commandParameter.getIndex());
      }
      else
      {
        result = factorAddCommand(domain, commandParameter);
      }
    }
    else if (commandClass == MoveCommand.class)
    {
      if (commandParameter.getEStructuralFeature() != null)
      {
        result = 
          createMoveCommand
            (domain, 
             commandParameter.getEOwner(), 
             commandParameter.getEStructuralFeature(), 
             commandParameter.getValue(), 
             commandParameter.getIndex());
      }
      else
      {
        result = factorMoveCommand(domain, commandParameter);
      }
    }
    else if (commandClass == ReplaceCommand.class)
    {
      result = 
        createReplaceCommand
          (domain, commandParameter.getEOwner(), commandParameter.getEStructuralFeature(), (EObject)commandParameter.getValue(), commandParameter.getCollection());
    }
    else if (commandClass == DragAndDropCommand.class)
    {
      DragAndDropCommand.Detail detail = (DragAndDropCommand.Detail)commandParameter.getFeature();
      result = 
        createDragAndDropCommand
          (domain, commandParameter.getOwner(), detail.location, detail.operations, detail.operation, commandParameter.getCollection());
    }
    else if (commandClass == CreateChildCommand.class)
    {
      CommandParameter newChildParameter = (CommandParameter)commandParameter.getValue();
      result = 
        createCreateChildCommand
          (domain,
           commandParameter.getEOwner(), 
           newChildParameter.getEStructuralFeature(), 
           newChildParameter.getValue(),
           newChildParameter.getIndex(),
           commandParameter.getCollection());      
    }

    // If necessary, get a command that replaces unwrapped values by their wrappers in the result and affected objects.
    //
    return wrapCommand(result, object, commandClass, commandParameter, oldCommandParameter);
  }

  /**
   * This creates a primitive {@link org.eclipse.emf.edit.command.SetCommand}.
   */
  protected Command createSetCommand(EditingDomain domain, EObject owner, EStructuralFeature feature, Object value, int index) 
  {
    if (index == CommandParameter.NO_INDEX)
    {
      return createSetCommand(domain, owner, feature, value);
    }
    return new SetCommand(domain, owner, feature, value, index);
  }

  /**
   * This returned a primitive {@link org.eclipse.emf.edit.command.SetCommand}, but it has been replaced, since this
   * command can now take an index.  The replacement method still calls this method when invoked with {@link
   * CommandParameter#NO_INDEX no index}, to provide backwards compatibility.
   * 
   * <p>This method will soon be deprecated.  New code should use or override the {@link
   * #createSetCommand(EditingDomain, EObject, EStructuralFeature, Object, int) new form}, instead.
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
  protected Command createRemoveCommand(EditingDomain domain, EObject owner, EStructuralFeature feature, Collection collection) 
  {
    if (feature instanceof EReference)
    {
      return createRemoveCommand(domain, owner, (EReference)feature, collection);
    }
    return new RemoveCommand(domain, owner, feature, collection);
  }

  /**
   * This returned a primitive {@link org.eclipse.emf.edit.command.RemoveCommand}, but it has been replaced since
   * this command is now used on attributes, too. The replacement method still calls this method for references, to
   * provide backwards compatibility.
   * @deprecated As of EMF 2.0, replaced by {@link #createRemoveCommand(EditingDomain, EObject, EStructuralFeature, Collection)
   * createRemoveCommand}.
   */
  protected Command createRemoveCommand(EditingDomain domain, EObject owner, EReference feature, Collection collection) 
  {
    return new RemoveCommand(domain, owner, feature, collection);
  }

  /**
   * This creates a primitive {@link org.eclipse.emf.edit.command.ReplaceCommand}.
   */
  protected Command createReplaceCommand(EditingDomain domain, EObject owner, EStructuralFeature feature, EObject value, Collection collection) 
  {
    if (feature instanceof EReference)
    {
      return createReplaceCommand(domain, owner, (EReference)feature, value, collection);
    }
    return new ReplaceCommand(domain, owner, feature, value, collection);
  }

  /**
   * This returned a primitive {@link org.eclipse.emf.edit.command.ReplaceCommand}, but it has been replaced since
   * this command is now used on attributes, too. The replacement method still calls this method for references, to
   * provide backwards compatibility.
   * @deprecated As of EMF 2.0, replaced by {@link #createReplaceCommand(EditingDomain, EObject, EStructuralFeature, EObject, Collection)
   * createReplaceCommand}.
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
    if (feature instanceof EReference)
    {
      return createAddCommand(domain, owner, (EReference)feature, collection, index);
    }
    return new AddCommand(domain, owner, feature, collection, index);
  }

  /**
   * This returned a primitive {@link org.eclipse.emf.edit.command.AddCommand}, but it has been replaced since
   * this command is now used on attributes, too. The replacement method still calls this method for references, to
   * provide backwards compatibility.
   * @deprecated As of EMF 2.0, replaced by {@link #createAddCommand(EditingDomain, EObject, EStructuralFeature, Collection, int)
   * createAddCommand}.
   */
  protected Command createAddCommand(EditingDomain domain, EObject owner, EReference feature, Collection collection, int index) 
  {
    return new AddCommand(domain, owner, feature, collection, index);
  }

  /**
   * This creates a primitive {@link org.eclipse.emf.edit.command.MoveCommand}.
   */
  protected Command createMoveCommand(EditingDomain domain, EObject owner, EStructuralFeature feature, Object value, int index) 
  {
    if (feature instanceof EReference && value instanceof EObject)
    {
      return createMoveCommand(domain, owner, (EReference)feature, (EObject)value, index);
    }
    return new MoveCommand(domain, owner, feature, value, index);
  }

  /**
   * This returned a primitive {@link org.eclipse.emf.edit.command.MoveCommand}, but it has been replaced since
   * this command is now used on attributes, too. The replacement method still calls this method for references, to
   * provide backwards compatibility.
   * @deprecated As of EMF 2.0, replaced by {@link #createMoveCommand(EditingDomain, EObject, EStructuralFeature, Object, int)
   * createMoveCommand}.
   */
  protected Command createMoveCommand(EditingDomain domain, EObject owner, EReference feature, EObject value, int index) 
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
  protected Command createCreateChildCommand(EditingDomain domain, EObject owner, EStructuralFeature feature, Object value, int index, Collection collection)
  {
    if (feature instanceof EReference && value instanceof EObject)
    {
      return createCreateChildCommand(domain, owner, (EReference)feature, (EObject)value, index, collection);
    }
    return new CreateChildCommand(domain, owner, feature, value, index, collection, this);
  }

  /**
   * This returned a primitive {@link org.eclipse.emf.edit.command.CreateChildCommand}, but it has been replaced since
   * this command is now used on attributes, too. The replacement method still calls this method for references, to
   * provide backwards compatibility.
   * @deprecated As of EMF 2.0, replaced by {@link #createCreateChildCommand(EditingDomain, EObject, EStructuralFeature, Object, int, Collection)
   * createCreateChildCommand}.
   */
  protected Command createCreateChildCommand(EditingDomain domain, EObject owner, EReference feature, EObject value, int index, Collection collection)
  {
    return new CreateChildCommand(domain, owner, feature, value, index, collection, this);
  }

  /**
   * This method factors a {@link org.eclipse.emf.edit.command.RemoveCommand} for a collection of objects 
   * into one or more primitive remove commands, 
   * i.e., one per unique feature.
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
    for (Iterator childrenFeatures = getAnyChildrenFeatures(eObject).iterator(); childrenFeatures.hasNext(); )
    {
      EStructuralFeature feature = (EStructuralFeature)childrenFeatures.next();

      // If it is a list type value...
      // 
      if (feature.isMany())
      {
        List value = (List)getFeatureValue(eObject, feature);

        // These will be the children beloging to this feature.
        //
        Collection childrenOfThisFeature = new ArrayList();
        for (ListIterator objects = list.listIterator(); objects.hasNext(); )
        {
          Object o = objects.next();

          // Is this object in this feature...
          //
          if (value.contains(o))
          {
            // Add it to the list and remove it from the other list.
            //
            childrenOfThisFeature.add(o);
            objects.remove();
          }
        }

        // If we have children to remove for this feature, create a command for it.
        //
        if (!childrenOfThisFeature.isEmpty())
        {
          removeCommand.append(createRemoveCommand(domain, eObject, feature, childrenOfThisFeature));
        }
      }
      else 
      {
        // It's just a single value
        //
        final Object value = getFeatureValue(eObject, feature);
        for (ListIterator objects = list.listIterator(); objects.hasNext(); )
        {
          Object o = objects.next();

          // Is this object in this feature...
          //
          if (o == value)
          {
            // Create a command to set this to null and remove the object from the other list.
            //
            Command setCommand = createSetCommand(domain, eObject, feature, null);
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
   * i.e., one per unique feature.
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
      EStructuralFeature childFeature = getChildFeature(eObject, firstChild);

      if (childFeature == null)
      {
        break;
      }
      // If it is a list type value...
      // 
      else if (childFeature.isMany())
      {
        // Correct the index, if necessary.
        //
        if (index != CommandParameter.NO_INDEX)
        {
          for (Iterator childrenFeatures = getAnyChildrenFeatures(eObject).iterator(); childrenFeatures.hasNext(); )
          {
            EStructuralFeature feature = (EStructuralFeature)childrenFeatures.next();
            if (feature == childFeature)
            {
              break;
            } 

            if (feature.isMany())
            {
              index -= ((List)(eObject).eGet(feature)).size();
            }
            else if (eObject.eGet(feature) != null)
            {
              index -= 1;
            }
          }
          if (index < 0)
          {
            break;
          }
        }

        // These will be the children belonging to this feature.
        //
        Collection childrenOfThisFeature = new ArrayList();
        childrenOfThisFeature.add(firstChild);
        children.remove();

        // Consume the rest of the appropriate children.
        //
        while (children.hasNext())
        {
          Object child = children.next();
          
          // Is this child in this feature...
          //
          if (getChildFeature(eObject, child) == childFeature)
          {
            // Add it to the list and remove it from the other list.
            //
            childrenOfThisFeature.add(child);
            children.remove();
          }
        }

        // Create a command for this feature, 
        //
        addCommand.append(createAddCommand(domain, eObject, childFeature, childrenOfThisFeature, index));

        if (index >= childrenOfThisFeature.size())
        {
          index -= childrenOfThisFeature.size();
        }
        else
        {
          index = CommandParameter.NO_INDEX;
        }
      }
      else if (eObject.eGet(childFeature) == null)
      {
        Command setCommand = createSetCommand(domain, eObject, childFeature, firstChild);
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
    final Object value = commandParameter.getValue();
    int index = commandParameter.getIndex();

    EStructuralFeature childFeature = getChildFeature(eObject, value);

    if (childFeature != null && childFeature.isMany())
    {
      // Compute the relative index as best as possible.
      //
      for (Iterator i = getAnyChildrenFeatures(eObject).iterator(); i.hasNext(); )
      {
        EStructuralFeature feature = (EStructuralFeature)i.next();
        if (feature == childFeature)
        {
          break;
        } 

        if (feature.isMany())
        {
          index -= ((List)(eObject).eGet(feature)).size();
        }
        else if (eObject.eGet(feature) != null)
        {
          index -= 1;
        }
      }

      // Create a command for this feature,
      //
      return createMoveCommand(domain, eObject, childFeature, value, index);
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

  public void unsetTarget(Notifier target)
  {
    if (target == this.target)
    {
      if (targets == null || targets.isEmpty())
      {
        super.setTarget(null);
      }
      else
      {
        super.setTarget((Notifier)targets.remove(targets.size() - 1));
      }
    }
    else if (targets != null)
    {
      targets.remove(target);
    }

    if (childrenStoreMap != null)
    {
      ChildrenStore store = (ChildrenStore)childrenStoreMap.remove(target);
      if (store != null && wrappers != null)
      {
        for (Iterator i = store.getChildren().iterator(); i.hasNext(); )
        {
          Object child = i.next();
          if (wrappers.remove(child))
          {
            ((IDisposable)child).dispose();
          }
        }
      }
    }
  }

  /**
   * This will remove this adapter from all its the targets and dispose any
   * remainging children wrappers in the children store.
   */
  public void dispose()
  {
    Notifier oldTarget = target;
    target = null;

    List oldTargets = targets;
    targets = null;

    if (oldTarget != null)
    {
      oldTarget.eAdapters().remove(this);
    }

    if (oldTargets != null)
    {
      for (Iterator i = oldTargets.iterator(); i.hasNext(); )
      {
        Notifier otherTarget = (Notifier)i.next();
        otherTarget.eAdapters().remove(this);
      }
    }

    // Dispose the child wrappers.
    //
    if (wrappers != null)
    {
      wrappers.dispose();
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
    if (feature instanceof EStructuralFeature && FeatureMapUtil.isFeatureMap((EStructuralFeature)feature))
    {
      FeatureMap.Entry entry = (FeatureMap.Entry)child;
      feature = entry.getEStructuralFeature();
      child = entry.getValue();
    }

    String childType = feature instanceof EAttribute ? getTypeText((EAttribute)feature) : getTypeText(child);

    // The try/catch provides backwards compatibility with the old resource
    // key, "_UI_CreateChild_label", and should be removed at the next
    // release, when we can expect users to do a regen.
    //
    try
    {
      return 
        getResourceLocator().getString
          (feature instanceof EAttribute ? "_UI_CreateChild_text3" : "_UI_CreateChild_text",
           new Object[] { childType, getFeatureText(feature), getTypeText(owner) });
    }
    catch (MissingResourceException e)
    {
      return 
        getResourceLocator().getString
          ("_UI_CreateChild_label",
           new Object[] { childType, getFeatureText(feature), getTypeText(owner) });
    }
  }

  /**
   * This returns the description for {@link CreateChildCommand}.
   */
  public String getCreateChildDescription(Object owner, Object feature, Object child, Collection selection)
  {
    if (feature instanceof EStructuralFeature && FeatureMapUtil.isFeatureMap((EStructuralFeature)feature))
    {
      FeatureMap.Entry entry = (FeatureMap.Entry)child;
      feature = entry.getEStructuralFeature();
      child = entry.getValue();
    }

    String childType = feature instanceof EAttribute ? getTypeText((EAttribute)feature) : getTypeText(child);
    Object selectionObject = selection == null || selection.isEmpty() ?  null : selection.iterator().next();

    if (selectionObject == owner)
    {
      return 
        getResourceLocator().getString
          ("_UI_CreateChild_description", 
           new Object[] { childType, getFeatureText(feature), getTypeText(owner) });
    }

    Object sibling = selectionObject;
    Object siblingFeature = getChildFeature(owner, sibling);

    if (siblingFeature instanceof EStructuralFeature && FeatureMapUtil.isFeatureMap((EStructuralFeature)siblingFeature))
    {
      FeatureMap.Entry entry = (FeatureMap.Entry)sibling;
      siblingFeature = entry.getEStructuralFeature();
      sibling = entry.getValue();
    }

    String siblingType = siblingFeature instanceof EAttribute ? getTypeText((EAttribute)siblingFeature) : getTypeText(sibling);
    return 
      getResourceLocator().getString
        ("_UI_CreateSibling_description", 
         new Object[] { childType, getFeatureText(feature), siblingType });
  }

  /**
   * This returns the tool tip text for {@link CreateChildCommand}.
   */
  public String getCreateChildToolTipText(Object owner, Object feature, Object child, Collection selection)
  {
    if (feature instanceof EStructuralFeature && FeatureMapUtil.isFeatureMap((EStructuralFeature)feature))
    {
      FeatureMap.Entry entry = (FeatureMap.Entry)child;
      feature = entry.getEStructuralFeature();
      child = entry.getValue();
    }

    String childType = feature instanceof EAttribute ? getTypeText((EAttribute)feature) : getTypeText(child);
    return 
      getResourceLocator().getString
        ("_UI_CreateChild_tooltip",
         new Object[] {childType , getFeatureText(feature), getTypeText(owner) });
  }

  /**
   * This returns the icon image for {@link org.eclipse.emf.edit.command.CreateChildCommand}.
   */
  public Object getCreateChildImage(Object owner, Object feature, Object child, Collection selection)
  {
    if (feature instanceof EStructuralFeature && FeatureMapUtil.isFeatureMap((EStructuralFeature)feature))
    {
      FeatureMap.Entry entry = (FeatureMap.Entry)child;
      feature = entry.getEStructuralFeature();
      child = entry.getValue();        
    }

    if (feature instanceof EReference)
    {
      EStructuralFeature eFeature = (EStructuralFeature)feature;
      String name = "full/ctool16/Create" + eFeature.getEContainingClass().getName() + "_" + eFeature.getName();

      if (child instanceof EObject)
      {
        name += "_" + ((EObject)child).eClass().getName();
      }

      try
      {
        return getResourceLocator().getImage(name);
      }
      catch (Exception e)
      {
        List images = new ArrayList();
        IItemLabelProvider itemLabelProvider = 
          (IItemLabelProvider)((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory().adapt(child, IItemLabelProvider.class);
        images.add(itemLabelProvider.getImage(child));
        images.add(EMFEditPlugin.INSTANCE.getImage("full/ovr16/CreateChild"));
        return 
          new ComposedImage(images)
          {
            public List getDrawPoints(Size size)
            {
              List result = super.getDrawPoints(size);
              ((Point)result.get(1)).x = size.width - 7;
              return result;
            }
          };
      }
    }

    return EMFEditPlugin.INSTANCE.getImage("full/ctool16/CreateChild");
  }

  /**
   * This looks up the name of the type of the specified object.
   */
  protected String getTypeText(Object object)
  {
    if (object instanceof EObject)
    {
      EObject eObject = (EObject)object;
      String typeKey = eObject.eClass().getName();
      List originalAdapters = new ArrayList(eObject.eAdapters());
      try
      {
        return getResourceLocator(object).getString("_UI_" + typeKey + "_type");
      }
      catch (MissingResourceException e)
      {
        return typeKey;
      }
      finally
      {
        eObject.eAdapters().retainAll(originalAdapters);
      }      
    }
    return getString("_UI_Unknown_type");
  }

  /**
   * This looks up the name of the type of the specified attribute.
   */
  protected String getTypeText(EAttribute attribute)
  {
    String typeKey = attribute.getEAttributeType().getName();
    try
    {
      return getString("_UI_" + typeKey + "_datatype");
    }
    catch (MissingResourceException e)
    {
    }
    return getString("_UI_Unknown_datatype");
  }

  /**
   * This looks up the name of the specified feature.
   */
  protected String getFeatureText(Object feature)
  {
    String featureKey;
    if (feature instanceof EStructuralFeature)
    {
      EStructuralFeature eFeature = (EStructuralFeature)feature;
      featureKey = eFeature.getEContainingClass().getName() + "_" + eFeature.getName();
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
   * i.e., it may not the type object for which this adapter is applicable.
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
   * Indicates whether strings should be translated by default.
   * 
   * @return <code>true</code> if strings should be translated by default; <code>false</code> otherwise.
   */
  protected boolean shouldTranslate()
  {
    return true;
  }

  /**
   * Get a translated string from the resource locator.
   */
  public String getString(String key)
  {
    return getString(key, shouldTranslate());
  }
  
  /**
   * Get a translated string from the resource locator.
   */
  public String getString(String key, boolean translate)
  {
    return getResourceLocator().getString(key, translate);
  }

  /**
   * Get a translated string from the resource locator, with substitutions.
   */
  public String getString(String key, Object [] substitutions)
  {
    return getString(key, substitutions, shouldTranslate());
  }
  
  /**
   * Get a translated string from the resource locator, with substitutions.
   */
  public String getString(String key, Object [] substitutions, boolean translate)
  {
    return getResourceLocator().getString(key, substitutions, translate);
  }

  /**
   * Get a translated string from the resource locator, substituting another
   * such translated string.
   */
  protected String getString(String key, String s0)
  {
    return getString(key, s0, shouldTranslate());
  }
  
  /**
   * Get a translated string from the resource locator, substituting another
   * such translated string.
   */
  protected String getString(String key, String s0, boolean translate)
  {
    ResourceLocator resourceLocator = getResourceLocator();
    return resourceLocator.getString(key, new Object[] { resourceLocator.getString(s0, translate) }, translate);
  }

  /**
   * Get a translated string from the resource locator, substituting two
   * other such translated strings.
   */
  protected String getString(String key, String s0, String s1)
  {
    return getString(key, s0, s1, shouldTranslate());
  }
  
  /**
   * Get a translated string from the resource locator, substituting two
   * other such translated strings.
   */
  protected String getString(String key, String s0, String s1, boolean translate)
  {
    ResourceLocator resourceLocator = getResourceLocator();
    return resourceLocator.getString(key, new Object[] { resourceLocator.getString(s0, translate), resourceLocator.getString(s1, translate) }, translate);
  }

  /**
   * Returns whether this item provider may need to use wrappers for some or all of the values it returns as {@link
   * #getChildren children}. This is used to determine whether to use a store to keep track of children and whether to
   * use command wrappers that re-wrap results and affected objects. The default implementation of {@link
   * #createWrapper createWrapper} also tests this method and will not create any wrappers if it returns
   * <code>false</code>.
   * 
   * <p>This implementation consults {@link #getChildrenFeatures getChildrenFeatures}, returning true if any feature
   * map or simple attributes contribute children. This provides backwards compatibility with pre-2.0 subclasses
   * and enables the more useful new default behaviour for attributes, which were previously not allowed. Subclasses
   * may override this to enable wrapping of cross-referenced model objects, or to immediately return <code>true</code> 
   * or <code>false</code>, as desired. This is a convenient way to disable all of the new wrapping features in 2.0.
   */
  protected boolean isWrappingNeeded(Object object)
  {
    if (wrappingNeeded == null)
    {
      wrappingNeeded = Boolean.FALSE;
      
      for (Iterator i = getAnyChildrenFeatures(object).iterator(); i.hasNext(); )
      {
        EStructuralFeature f = (EStructuralFeature)i.next();
        if (f instanceof EAttribute)
        {
          wrappingNeeded = Boolean.TRUE;
        }
      }
    }
    return wrappingNeeded.booleanValue();
  }

  /**
   * Returns the store for the children of the given object, or null if no such store is being maintained.
   */
  protected ChildrenStore getChildrenStore(Object object)
  {
    return childrenStoreMap == null ? null : (ChildrenStore)childrenStoreMap.get(object);
  }

  /**
   * Consults {@link #isWrappingNeeded isWrappingNeeded} to decide whether to create a store for the children of the
   * given object. If so, the new store is created, added to the collection being maintained, and returned. If not,
   * null is returned.
   */
  protected ChildrenStore createChildrenStore(Object object)
  {
    ChildrenStore store = null;

    if (isWrappingNeeded(object))
    {
      if (childrenStoreMap == null)
      {
        childrenStoreMap = new HashMap();
      }
      store = new ChildrenStore(getAnyChildrenFeatures(object));
      childrenStoreMap.put(object, store);
    }
    return store;
  }

  /**
   * A <code>ChildrenStore</code> stores a number of objects that are to be presented as the children of an object.
   * Each of a set of {@link org.eclipse.emf.ecore.EStructuralFeature feature}s may have either one or any number
   * of children objects associated with it, depending on its multiplicity. The objects associated with a
   * multiplicity-many feature are accessed and manipulated as an {@link org.eclipse.emf.common.util.EList}, 
   * typically mirroring the actual values of that feature, with some or all in wrappers, as necessary. The object
   * associated with a multiplicity-1 feature is typically accessed and set directly, although a modifiable, singleton
   * list view is available. This class provides a number of convenient methods for access by feature, as well as
   * a method, {@link #getChildren getChildren} that returns the complete collection of children from all features as
   * an unmodifiable list.
   */
  protected static class ChildrenStore
  {
    /**
     * An <code>Entry</code> represents the children from a feature.
     */
    protected static class Entry
    {
      public EStructuralFeature feature;
      public EList list;

      public Entry(EStructuralFeature feature)
      {
        this.feature = feature;
      }
    }

    protected Entry[] entries;

    /**
     * Creates a store for the children of the given features.
     */
    public ChildrenStore(Collection features)
    {
      entries = new Entry[features.size()];
      int i = 0;
      for (Iterator iter = features.iterator(); iter.hasNext(); )
      {
        entries[i++] = new Entry((EStructuralFeature)iter.next());
      }
    }

    protected Entry getEntry(EStructuralFeature feature)
    {
      for (int i = 0; i < entries.length; i++)
      {
        if (entries[i].feature == feature) return entries[i];
      }
      return null;
    }

    /**
     * Returns a list, either a full list implementation or a fixed-sized modifiable singleton list, depending
     * on the multiplicity of the feature. Before accessing an entry's list, the store should always check if it is
     * null and if so, allocate it using this method.
     */
    protected EList createList(EStructuralFeature feature)
    {
      return feature.isMany() ?
        (EList)(new BasicEList()) :
        (EList)(new ModifiableSingletonEList());
    }

    /**
     * Returns whether the store handles children of the specified feature.
     */
    public boolean contains(EStructuralFeature feature)
    {
      return getEntry(feature) != null;
    }

    /**
     * Returns the list view of the specified feature, or null if the store does not handle the feature.
     */
    public EList getList(EStructuralFeature feature)
    {
      Entry entry = getEntry(feature);
      if (entry == null) return null;

      if (entry.list == null)
      {        
        entry.list = createList(feature);
      }
      return entry.list;
    }

    /**
     * Returns the value view of the specified feature. For a single-valued feature, this is the value itself, which
     * may be null. For a multi-valued feature, it is just the whole list.
     */
    public Object getValue(EStructuralFeature feature)
    {
      Entry entry = getEntry(feature);
      if (entry == null) return null;
      Object result = null;

      if (feature.isMany())
      {
        if (entry.list == null)
        {
          entry.list = createList(feature);
        }
        result = entry.list;
      }
      else if (entry.list != null)
      {
        result = entry.list.get(0);
      }
      return result;
    }

    /**
     * Sets the value of the specified feature. For a multi-valued feature, the specified value is treated as a
     * {@link java.util.Collection}, and all of its elements are added to the emptied list.
     */
    public boolean setValue(EStructuralFeature feature, Object value)
    {
      Entry entry = getEntry(feature);
      if (entry == null) return false;

      if (entry.list == null && value != null)
      {
        entry.list = createList(feature);
      }

      if (entry.list != null)
      {
        if (feature.isMany())
        {
          entry.list.clear();
          if (value != null) entry.list.addAll((Collection)value);
        }
        else
        {
          entry.list.set(0, value);
        }
      }
      return true;
    }

    /**
     * Returns either a single element of the specified feature or, if index is -1, the value view.
     */
    public Object get(EStructuralFeature feature, int index)
    {
      if (index == -1) return getValue(feature);
      EList list = getList(feature);
      return list != null ? list.get(index) : null;
    }

    /**
     * Sets either a single element of the specified feature or, if the index is -1, the value of the feature.
     */
    public boolean set(EStructuralFeature feature, int index, Object object)
    {
      if (index == -1) return setValue(feature, object);
      EList list = getList(feature);

      if (list != null)
      {
        list.set(index, object);
        return true;
      }
      return false;
    }

    /**
     * Returns a list containing all children of all features in the store. Null, single-valued features are excluded.
     * The list can be freely modified without affecting the store.
     */
    public List getChildren()
    {
      int size = 0;
      for (int i = 0; i < entries.length; i++)
      {
        if (entries[i].list != null)
        {
          size += entries[i].feature.isMany() ?
            entries[i].list.size() :
            entries[i].list.get(0) != null ? 1 : 0;
        }
      }
      List result = new ArrayList(size);
      for (int i = 0; i < entries.length; i++)
      {
        if (entries[i].list != null)
        {
          if (entries[i].feature.isMany())
          {
            result.addAll(entries[i].list);
          }
          else if (entries[i].list.get(0) != null)
          {
            result.add(entries[i].list.get(0));
          }
        }
      }
      return result;
    }

    /**
     * Returns the offset in the list returned by {@link #getChildren getChildren} of given feature's children.
     */
    public int getOffset(EStructuralFeature feature)
    {
      for (int i = 0, offset = 0; i < entries.length; i++)
      {
        if (entries[i].feature == feature) return offset;
        if (entries[i].list != null)
        {
          offset += entries[i].feature.isMany() ?
            entries[i].list.size() :
            entries[i].list.get(0) != null ? 1 : 0;
        }
      }
      return -1;
    }

    /**
     * Clears the children of all features in the store. Multi-valued features are cleared, and single-valued features
     * are set to null.
     */
    public void clear()
    {
      for (int i = 0; i < entries.length; i++)
      {
        if (entries[i].list != null)
        {
          if (entries[i].feature.isMany())
          {
            entries[i].list.clear();
          }
          else
          {
            entries[i].list.set(0, null);
          }
        }
      }
    }
  }

  /**
   * A single-element implementation of {@link org.eclipse.emf.common.util.EList}. The element can be modified, but
   * the size of the list may not be changed.
   */
  protected static class ModifiableSingletonEList extends AbstractList
  implements EList
  {
    private Object singleElement;

    ModifiableSingletonEList()
    {
      singleElement = null;
    }

    ModifiableSingletonEList(Object element)
    {
      singleElement = element;
    }

    public int size()
    {
      return 1;
    }

    public Object get(int index)
    {
      if (index != 0)
      {
        throw new IndexOutOfBoundsException("index=" + index + ", size=1");
      }
      return singleElement;
    }

    public Object set(int index, Object element)
    {
      if (index != 0)
      {
        throw new IndexOutOfBoundsException("index=" + index + ", size=1");
      }
      Object oldElement = singleElement;
      singleElement = element;
      return oldElement;
    }

    public boolean contains(Object o)
    {
      return o == null ? singleElement == null : o.equals(singleElement);
    }

    public void move(int index, Object o)
    {
      if (index != 0 || !contains(o))
      {
        throw new IndexOutOfBoundsException("index=" + index + ", size=1");
      }
    }

    public Object move(int targetIndex, int sourceIndex)
    {
      if (targetIndex != 0)
      {
        throw new IndexOutOfBoundsException("targetIndex=" + targetIndex + ", size=1");
      }
      if (sourceIndex != 0)
      {
        throw new IndexOutOfBoundsException("sourceIndex=" + sourceIndex + ", size=1");
      }
      return singleElement;
    }
  }

  /**
   * Wraps a value, if needed, and keeps the wrapper for disposal along with the item provider. This method actually
   * calls {@link #createWrapper createWrapper} to determine if the given value, at the given index in the given
   * feature of the given object, should be wrapped and to obtain the wrapper. If a wrapper is obtained, it is recorded
   * and returned. Otherwise, the orginal value is returned. Subclasses may override {@link #createWrapper
   * createWrapper} to specify when and with what to wrap values.
   */
  protected Object wrap(EObject object, EStructuralFeature feature, Object value, int index)
  {
    if (!feature.isMany() && index != CommandParameter.NO_INDEX)
    {
      System.out.println("Bad wrap index.");
      System.out.println("  object: " + object);
      System.out.println("  feature: " + feature);
      System.out.println("  value: " + value);
      System.out.println("  index: " + index);
      (new IllegalArgumentException("Bad wrap index.")).printStackTrace();
    }

    Object wrapper = createWrapper(object, feature, value, index);
    if (wrapper == null)
    {
      wrapper = value;
    }
    else if (wrapper != value)
    {
      if (wrappers == null)
      {
        wrappers = new Disposable();
      }
      wrappers.add(wrapper);
    }
    return wrapper;
  }

  /**
   * Creates and returns a wrapper for the given value, at the given index in the given feature of the given object
   * if such a wrapper is needed; otherwise, returns the original value. This implementation consults {@link
   * #isWrappingNeeded isWrappingNeeded} and, if it is <code>true</code>, creates different wrappers that implement
   * {@link IWrapperItemProvider} for feature maps, simple attributes, and cross references.
   * 
   * By default, {@link #isWrappingNeeded isWrappingNeeded} does not return <code>true</code> unless there is at
   * least one feature map or simple attribute that contributes children, in order to maintain backwards compatibility.
   * As a result, it may be necessary to override that method in order to wrap cross-referenced model objects here.
   * Subclasses may also override this method, in order to create their own specialized wrappers.
   */
  protected Object createWrapper(EObject object, EStructuralFeature feature, Object value, int index)
  {
    if (!isWrappingNeeded(object)) return value;

    if (FeatureMapUtil.isFeatureMap(feature))
    {
      value = new FeatureMapEntryWrapperItemProvider((FeatureMap.Entry)value, object, (EAttribute)feature, index, adapterFactory, getResourceLocator());
    }
    else if (feature instanceof EAttribute)
    {
      value = new AttributeValueWrapperItemProvider(value, object, (EAttribute)feature, index, adapterFactory, getResourceLocator());
    }
    else if (!((EReference)feature).isContainment())
    {
      value = new DelegatingWrapperItemProvider(value, object, feature, index, adapterFactory);
    }

    return value;
  }

  /**
   * If the given object implements {@link IWrapperItemProvider}, it is unwrapped by obtaining a value from {@link
   * IWrapperItemProvider#getValue getValue}. The unwrapping continues until a non-wrapper value is returned. This
   * iterative unwrapping is required because values may be repeatedly wrapped, as children of a delegating wrapper.
   */
  protected Object unwrap(Object object)
  {
    while (object instanceof IWrapperItemProvider)
    {
      object = ((IWrapperItemProvider)object).getValue();
    }
    return object;
  }

  /**
   * If the given object implements {@link IWrapperItemProvider}, it is disposed by calling {@link
   * IWrapperItemProvider#dispose dispose}. It is also removed from {@link #wrappers}, as it will longer need to be
   * disposed along with this item provider.
   */
  protected void disposeWrapper(Object object)
  {
    if (object instanceof IWrapperItemProvider)
    {
      ((IWrapperItemProvider)object).dispose();
      if (wrappers != null)
      {
        wrappers.remove(object);
      }
    }
  }

  /**
   * Each element of the given list that implements {@link IWrapperItemProvider} is disposed by calling
   * IWrapperItemProvider#dispose dispose} and is removed from {@link #wrappers}.
   */
  protected void disposeWrappers(List objects)
  {
    for (Iterator i = objects.iterator(); i.hasNext(); )
    {
      disposeWrapper(i.next());
    }
  }

  /**
   * If the given object implements {@link IWrapperItemProvider} and specifies an index, that index is adjusted by the
   * given increment.
   */
  protected void adjustWrapperIndex(Object object, int increment)
  {
    if (object instanceof IWrapperItemProvider)
    {
      IWrapperItemProvider wrapper = (IWrapperItemProvider)object;
      int index = wrapper.getIndex();

      if (index != CommandParameter.NO_INDEX)
      {
        wrapper.setIndex(index + increment);
      }
    }
  }

  /**
   * For each element of the given list, starting at <code>from</code>, that implements {@link IWrapperItemProvider}
   * and specifies an index, that index is adjusted by the given increment.
   */
  protected void adjustWrapperIndices(List objects, int from, int increment)
  {
    for (Iterator i = objects.listIterator(from); i.hasNext(); )
    {
      adjustWrapperIndex(i.next(), increment);
    }
  }

  /**
   * For each element of the given list, between <code>from</code> and <code>to</code>, that implements {@link
   * IWrapperItemProvider} and specifies an index, that index is adjusted by the given increment.
   */
  protected void adjustWrapperIndices(List objects, int from, int to, int increment)
  {
    for (Iterator i = objects.listIterator(from); from < to && i.hasNext(); from++)
    {
      adjustWrapperIndex(i.next(), increment);
    }
  }

  /**
   * Updates any cached children based on the given notification. If a {@link ChildrenStore} exists for its notifier,
   * then the children of the specified feature are updated.
   *
   * <p>Existing children in the store that correspond to any set, removed or unset values are {@link
   * #disposeWrapper disposed} before being removed from the store. When children are added to, removed from, or moved
   * within a feature, the indices of any others affected are {@link #adjustWrapperIndex adjusted}. Since this method
   * is typically called from {@link #notifyChanged notifyChanged}, which, in subclasses, is often invoked repeatedly
   * up the inheritance chain, it can be safely called repeatedly for a single notification, and only the first such
   * call will have an effect. Such repeated calls may not, however, safely be interleaved with calls for another
   * notification.
   */
  protected void updateChildren(Notification notification)
  {
    EObject object = (EObject)notification.getNotifier();
    ChildrenStore childrenStore = getChildrenStore(object);

    if (childrenStore != null)
    {
      EStructuralFeature feature = (EStructuralFeature)notification.getFeature();
      EList children = childrenStore.getList(feature);
      if (children != null)
      {
        int index = notification.getPosition();

        switch (notification.getEventType())
        {
          case Notification.UNSET:
          {
            // Ignore the unset notification for an isMany feature; the value is boolean in this case.
            //
            if (feature.isMany())
            {
              break;
            }
            
            // continue to next case
          }
          case Notification.SET:
          {
            Object oldChild = childrenStore.get(feature, index);
            Object newValue = notification.getNewValue();

            if (unwrap(oldChild) != newValue)
            {
              if (feature.isMany() && index == Notification.NO_INDEX)
              {
                disposeWrappers((List)oldChild);
              }
              else
              {
                disposeWrapper(oldChild);
              }
              Object newChild = newValue == null && index == Notification.NO_INDEX ?
                  null : wrap(object, feature, newValue, index);
              childrenStore.set(feature, index, newChild);
            }
            break;
          }
          case Notification.ADD:
          {
            EList values = (EList)object.eGet(feature);

            if (children.size() != values.size())
            {
              Object newValue = notification.getNewValue();
              adjustWrapperIndices(children, index, 1);
              children.add(index, wrap(object, feature, newValue, index));
            }
            break;
          }
          case Notification.REMOVE:
          {
            EList values = (EList)object.eGet(feature);

            if (children.size() != values.size())
            {
              disposeWrapper(children.remove(index));
              adjustWrapperIndices(children, index, -1);
            }
            break;
          }
          case Notification.ADD_MANY:
          {
            EList values = (EList)object.eGet(feature);

            if (children.size() != values.size())
            {
              if (notification.getOldValue() != null)
              {
                throw new IllegalArgumentException("No old value expected");
              }
              List newValues = (List)notification.getNewValue();
              List newChildren = new ArrayList(newValues.size());
              int offset = 0;
              for (Iterator i = newValues.iterator(); i.hasNext(); offset++)
              {
                newChildren.add(wrap(object, feature, i.next(), index+offset));
              }
              adjustWrapperIndices(children, index, offset);
              children.addAll(index, newChildren);
            }
            break;
          }
          case Notification.REMOVE_MANY:
          {
            // No index specified when removing all elements.
            //
            if (index == Notification.NO_INDEX) index = 0;
            EList values = (EList)object.eGet(feature);

            if (children.size() != values.size())
            {
              if (notification.getNewValue() instanceof int[])
              {
                int[] indices = (int[])notification.getNewValue();
                for (int i = indices.length - 1; i >= 0; i--)
                {
                  disposeWrapper(children.remove(indices[i]));
                  adjustWrapperIndices(children, indices[i], -1);
                }
              }
              else
              {
                int len = ((List)notification.getOldValue()).size();
                List sl = children.subList(index, index + len);
                disposeWrappers(sl);
                sl.clear();
                adjustWrapperIndices(children, index, -len);
              }
            }
            break;
          }
          case Notification.MOVE:
          {
            int oldIndex = ((Integer)notification.getOldValue()).intValue();
            EList values = (EList)object.eGet(feature);
            boolean didMove = true;

            for (int i = Math.min(oldIndex, index), end = Math.max(oldIndex, index); didMove && i <= end; i++)
            {
              didMove = unwrap(children.get(i)) == values.get(i);
            }
            
            if (!didMove)
            {
              int delta = index - oldIndex;
              if (delta < 0)
              {
                adjustWrapperIndices(children, index, oldIndex, 1);
              }
              children.move(index, oldIndex);
              adjustWrapperIndex(children.get(index), delta);
              if (delta > 0)
              {
                adjustWrapperIndices(children, oldIndex, index, -1);
              }
            }
            break;
          }
        }
      }
    }
  }

  /**
   * If the given command parameter contains wrapped values that need to be unwrapped for the given command class to
   * operate on, a new command parameter will be returned, with those values unwrapped; otherwise, the original one is
   * returned. For most commands, any objects in the {@link org.eclipse.emf.edit.command.CommandParameter#getCollection
   * collection} or in the {@link org.eclipse.emf.edit.command.CommandParameter#getValue value} that implement {@link
   * IWrapperItemProvider} will be {@link #unwrap unwrapped}. {@link org.eclipse.emf.edit.command.DragAndDropCommand}
   * is never unwrapped. 
   */
  protected CommandParameter unwrapCommandValues(CommandParameter commandParameter, Class commandClass)
  {
    // We need the wrapper, not the item provider, to handle a DragAndDropCommand; the move, add, remove, etc. commands
    // that implement it will have their values unwrapped as usual.
    //
    if (commandClass == DragAndDropCommand.class)
    {
      return commandParameter;
    }
    
    ArrayList newCollection = null;
    Collection oldCollection = commandParameter.getCollection();
    
    // Unwrap collection.
    //
    if (oldCollection != null)
    {
      for (Iterator i = oldCollection.iterator(); i.hasNext(); )
      {
        Object oldValue = i.next();
        Object newValue = unwrap(oldValue);

        // If the first wrapped value is found...
        //
        if (newValue != oldValue && newCollection == null)
        {
          // Allocate the new collection, and populate it up to this point.
          //
          newCollection = new ArrayList(oldCollection.size());
          for (Iterator j = oldCollection.iterator(); j.hasNext(); )
          {
            Object o = j.next();
            if (o == oldValue) break;
            newCollection.add(o);
          }
        }

        // If a new collection was allocated, continue to populate it.
        //
        if (newCollection != null)
        {
          newCollection.add(newValue);
        }
      }
    }

    // Unwrap value.
    //
    Object oldValue = commandParameter.getValue();
    Object newValue = unwrap(oldValue);

    if (newCollection != null || newValue != oldValue)
    {
      commandParameter = new CommandParameter(
        commandParameter.owner,
        commandParameter.feature,
        newValue,
        newCollection,
        commandParameter.index);
    }

    return commandParameter;
  }

  /**
   * Returns a version of the given command that automatically re-wraps values that have been unwrapped when returning
   * them as the command's result or affected objects. This is only done if {@link #isWrappingNeeded isWrappingNeeded}
   * returns <code>true</code>, and never for a {@link org.eclipse.emf.edit.command.DragAndDropCommand}.
   */
  protected Command wrapCommand(Command command, Object object, Class commandClass, CommandParameter commandParameter, CommandParameter oldCommandParameter) 
  {
    if (isWrappingNeeded(object) && commandClass != DragAndDropCommand.class)
    {
      // Wrappers from the old command parameter must be considered in order for cut and paste to work.
      //
      Collection oldWrappers = commandParameter != oldCommandParameter ?
        getWrappedValues(oldCommandParameter) : Collections.EMPTY_LIST;

      command = command instanceof CommandActionDelegate ?
        new ResultAndAffectedObjectsWrappingCommandActionDelegate((CommandActionDelegate)command, oldWrappers) :
        new ResultAndAffectedObjectsWrappingCommand(command, oldWrappers);
    }
    return command;
  }
  
  /**
   * Returns a collection of any objects in the given command parameter's {@link
   * org.eclipse.emf.edit.command.CommandParameter#getCollection collection} and {@link
   * org.eclipse.emf.edit.command.CommandParameter#getValue value}, that implement {@link IWrapperItemProvider}.
   */
  protected Collection getWrappedValues(CommandParameter commandParameter)
  {
    Collection collection = commandParameter.getCollection();
    Object value = commandParameter.getValue();

    if (collection != null)
    {
      List result = new ArrayList(collection.size() + 1);
      for (Iterator i = collection.iterator(); i.hasNext(); )
      {
        Object o = i.next();
        if (o instanceof IWrapperItemProvider)
        {
          result.add(o);
        }
      }

      if (value instanceof IWrapperItemProvider)
      {
        result.add(value);
      }

      return result;
    } 
    else if (value instanceof IWrapperItemProvider)
    {
      return Collections.singletonList(value);
    }
    return Collections.EMPTY_LIST;
  }

  /**
   * This creates an item provider descriptor that uses a resource locator; specifies a static image, a category, and filter flags;
   * and determines the cell editor from the type of the structural feature. 
   * <p>
   * Newly regenerated code will no longer use this method. You'll need to override {@link
   * #createItemPropertyDescriptor(AdapterFactory, ResourceLocator, String, String, EStructuralFeature, boolean, boolean, Object, String, String[])
   * this form} instead.
   */
  protected ItemPropertyDescriptor createItemPropertyDescriptor(
    AdapterFactory adapterFactory,
    ResourceLocator resourceLocator,
    String displayName,
    String description,
    EStructuralFeature feature,
    boolean isSettable,
    Object staticImage,
    String category,
    String[] filterFlags)
  {
    return createItemPropertyDescriptor(
      adapterFactory,
      resourceLocator,
      displayName,
      description,
      feature,
      isSettable,
      false,
      false,
      staticImage,
      category,
      filterFlags);
  }

  /**
   * Creates an instance that uses a resource locator; indicates whether to be mutli-line and to sort choices; specifies
   * a  static image, a category, and filter flags; and determines the cell editor from the type of the structural feature. 
   * @since 2.2.0
   */
  protected ItemPropertyDescriptor createItemPropertyDescriptor(
    AdapterFactory adapterFactory,
    ResourceLocator resourceLocator,
    String displayName,
    String description,
    EStructuralFeature feature,
    boolean isSettable,
    boolean multiLine,
    boolean sortChoices,
    Object staticImage,
    String category,
    String[] filterFlags)
  {
    return new ItemPropertyDescriptor(
      adapterFactory,
      resourceLocator,
      displayName,
      description,
      feature,
      isSettable,
      multiLine,
      sortChoices,
      staticImage,
      category,
      filterFlags);
  }
  
  /**
   * A <code>ResultAndAffectedObjectsWrappingCommand</code> wraps another command to substitute {@link
   * IWrapperItemProvider}s for their values in the command's result and affected objects. This is needed
   * as the values have been unwrapped for the command to operate on properly.
   * <p>
   * A list of wrappers from which to substitute is formed by calling {@link #getChildren getChildren} on the command's
   * owner(s). Additional wrappers to be considered for the result can by specified in the two-argument constructor.
   * The first wrapper whose {@link IWrapperItemProvider#getValue value} matches a given value in the result or
   * affected objects is substitued for it.
   */
  public class ResultAndAffectedObjectsWrappingCommand extends CommandWrapper
  {
    protected List owners;
    protected Collection additionalWrappers;

    public ResultAndAffectedObjectsWrappingCommand(Command command)
    {
      super(command);
    }

    public ResultAndAffectedObjectsWrappingCommand(Command command, Collection additionalResultWrappers)
    {
      super(command);
      additionalWrappers = additionalResultWrappers;
    }

    public Collection getResult()
    {
      return wrapValues(super.getResult(), true);
    }

    public Collection getAffectedObjects()
    {
      return wrapValues(super.getAffectedObjects(), false);
    }

    protected Collection wrapValues(Collection unwrappedValues, boolean useAdditionalWrappers)
    {
      List result = new ArrayList(unwrappedValues);
      List wrappers = new ArrayList();

      // If the adapter factory is composeable, we'll adapt using the root.
      //
      AdapterFactory af = adapterFactory instanceof ComposeableAdapterFactory ?
        ((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory() :
        adapterFactory;

      // Build list of wrapped children from the appropriate adapters.
      //
      for (Iterator i = getOwners().iterator(); i.hasNext(); )
      {
        Object owner = i.next();
        Collection children = Collections.EMPTY_LIST;

        // Either the IEditingDomainItemProvider or ITreeItemContentProvider item provider interface can give us
        // the children.
        //
        Object adapter = af.adapt(owner, IEditingDomainItemProvider.class);
        if (adapter instanceof IEditingDomainItemProvider)
        {
          children = ((IEditingDomainItemProvider)adapter).getChildren(owner);
        }
        else
        {
          adapter = af.adapt(owner, ITreeItemContentProvider.class);
          if (adapter instanceof ITreeItemContentProvider)
          {
            children = ((ITreeItemContentProvider)adapter).getChildren(owner);
          }
        }

        for (Iterator j = children.iterator(); j.hasNext(); )
        {
          Object child = j.next();
          if (child instanceof IWrapperItemProvider)
          {
            wrappers.add(child);
          }
        }
      }

      // Add in additional wrappers to search.
      //
      if (useAdditionalWrappers && additionalWrappers != null)
      {
        wrappers.addAll(additionalWrappers);
      }

      // Look for each unwrapped object as a value of a wrapper, replacing it with the first one found.
      //
      for (ListIterator i = result.listIterator(); i.hasNext(); )
      {
        Object resultObject = i.next();

        Iterator j = wrappers.iterator();
        while (j.hasNext())
        {
          IWrapperItemProvider wrapper = (IWrapperItemProvider)j.next();

          if (isEquivalentValue(unwrap(wrapper), resultObject))
          {
            i.set(wrapper);
            break;
          }
        }
      }
      return result;
    }

    /**
     * Returns any owners from the wrapped command. If it is a compound command, or a wrapped compound command, it may
     * have multiple owners. This returns and caches a list of them.
     */
    public List getOwners()
    {
      if (owners == null)
      {
        owners = new UniqueEList();
        addOwners(getCommand());
      }
      return owners;
    }

    /**
     * Helper method that builds the list of owners, recursively for command wrappers and/or compound commands.
     */
    protected void addOwners(Command command)
    {
      if (command instanceof CommandWrapper)
      {
        addOwners(((CommandWrapper)command).getCommand());
      }        
      else if (command instanceof CompoundCommand)
      {
        CompoundCommand compoundCommand = (CompoundCommand)command;
        List commandList = compoundCommand.getCommandList();
        int resultIndex = compoundCommand.getResultIndex();

        if (resultIndex == CompoundCommand.MERGE_COMMAND_ALL)
        {
          for (Iterator i = commandList.iterator(); i.hasNext(); )
          {
            addOwners((Command)i.next());
          }
        }
        else
        {
          if (resultIndex == CompoundCommand.LAST_COMMAND_ALL)
          {
            resultIndex = commandList.size() - 1;
          }
        
          if (resultIndex >= 0)
          {
            addOwners((Command)commandList.get(resultIndex));
          }
        }
      }
      else if (command instanceof AddCommand) 
      {
        owners.add(((AddCommand)command).getOwner());
      }
      else if (command instanceof CreateCopyCommand)
      {
        owners.add(((CreateCopyCommand)command).getOwner());
      }
      else if (command instanceof InitializeCopyCommand)
      {
        owners.add(((InitializeCopyCommand)command).getOwner());
      }
      else if (command instanceof MoveCommand)
      {
        owners.add(((MoveCommand)command).getOwner());
      }
      else if (command instanceof RemoveCommand)
      {
        owners.add(((RemoveCommand)command).getOwner());
      }
      else if (command instanceof ReplaceCommand)
      {
        owners.add(((ReplaceCommand)command).getOwner());
      }
      else if (command instanceof SetCommand)
      {
        owners.add(((SetCommand)command).getOwner());
      }
    }
  }

  /**
   * A <code>ResultAndAffectedObjectsWrappingCommandActionDelegate</code> wraps another command that also implements
   * <code>CommandActionDelegate</code>, to substitute {@link IWrapperItemProvider}s for its values, which have been
   * unwrapped for the command to operate on properly. This substitution is performed exactly as by a
   * <code>ResultAndAffectedObjectsWrappingComand</code>, and action delegate methods are delegated directly to the
   * wrapped command.
   */
  public class ResultAndAffectedObjectsWrappingCommandActionDelegate extends ResultAndAffectedObjectsWrappingCommand
    implements CommandActionDelegate
  {
    CommandActionDelegate commandActionDelegate;
    
    /**
     * Returns a new <code>ResultAndAffectedObjectsWrappingCommandActionDelegate</code> for the given command.
     * @exception ClassCastException If the specified command does not implement {@link org.eclipse.emf.common.command.Command}.
     */
    public ResultAndAffectedObjectsWrappingCommandActionDelegate(CommandActionDelegate command)
    {
      super((Command)command);
      commandActionDelegate = command;
    }

    /**
     * Returns a new <code>ResultAndAffectedObjectsWrappingCommandActionDelegate</code> for the given command and list
     * of additional wrappers.
     * @exception ClassCastException If the specified command does not implement {@link org.eclipse.emf.common.command.Command}.
     */
    public ResultAndAffectedObjectsWrappingCommandActionDelegate(CommandActionDelegate command, Collection additionalWrappers)
    {
      super((Command)command, additionalWrappers);
      commandActionDelegate = command;
    }

    public Object getImage()
    {
      return commandActionDelegate.getImage();
    }

    public String getText()
    {
      return commandActionDelegate.getText();
    }

    public String getDescription()
    {
      return commandActionDelegate.getDescription();
    }

    public String getToolTipText()
    {
      return commandActionDelegate.getToolTipText();
    }
  }
}
