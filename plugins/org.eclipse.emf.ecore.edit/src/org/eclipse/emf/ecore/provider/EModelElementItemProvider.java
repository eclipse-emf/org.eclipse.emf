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
 * $Id: EModelElementItemProvider.java,v 1.16 2008/08/07 11:00:21 emerks Exp $
 */
package org.eclipse.emf.ecore.provider;


import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.util.ResourceLocator;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EModelElement;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.EcoreFactory;
import org.eclipse.emf.ecore.EcorePackage;
import org.eclipse.emf.ecore.EPackage.Registry;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.edit.provider.IEditingDomainItemProvider;
import org.eclipse.emf.edit.provider.IItemLabelProvider;
import org.eclipse.emf.edit.provider.IItemPropertyDescriptor;
import org.eclipse.emf.edit.provider.IItemPropertySource;
import org.eclipse.emf.edit.provider.IStructuredItemContentProvider;
import org.eclipse.emf.edit.provider.ITreeItemContentProvider;
import org.eclipse.emf.edit.provider.ItemPropertyDescriptor;

import org.eclipse.emf.edit.provider.ViewerNotification;

/**
 * This is the item provider adapter for a {@link org.eclipse.emf.ecore.EModelElement} object.
 * <!-- begin-user-doc -->
 * <!-- end-user-doc -->
 * @generated
 */
public class EModelElementItemProvider
  extends EObjectItemProvider
  implements
    IEditingDomainItemProvider, IStructuredItemContentProvider, ITreeItemContentProvider, IItemLabelProvider, IItemPropertySource
{
  /**
   * This constructs an instance from a factory and a notifier.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EModelElementItemProvider(AdapterFactory adapterFactory)
  {
    super(adapterFactory);
  }

  /**
   * This returns the property descriptors for the adapted class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public List<IItemPropertyDescriptor> getPropertyDescriptors(Object object)
  {
    if (itemPropertyDescriptors == null)
    {
      super.getPropertyDescriptors(object);

    }
    return itemPropertyDescriptors;
  }

  /**
   * This specifies how to implement {@link #getChildren} and is used to deduce an appropriate feature for an
   * {@link org.eclipse.emf.edit.command.AddCommand}, {@link org.eclipse.emf.edit.command.RemoveCommand} or
   * {@link org.eclipse.emf.edit.command.MoveCommand} in {@link #createCommand}.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public Collection<? extends EStructuralFeature> getChildrenFeatures(Object object)
  {
    if (childrenFeatures == null)
    {
      super.getChildrenFeatures(object);
      childrenFeatures.add(EcorePackage.Literals.EMODEL_ELEMENT__EANNOTATIONS);
    }
    return childrenFeatures;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  protected EStructuralFeature getChildFeature(Object object, Object child)
  {
    // Check the type of the specified child object and return the proper feature to use for
    // adding (see {@link AddCommand}) it as a child.

    return super.getChildFeature(object, child);
  }

  /**
   * This returns the label text for the adapted class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public String getText(Object object)
  {
    return getString("_UI_EModelElement_type");
  }

  /**
   * This handles model notifications by calling {@link #updateChildren} to update any cached
   * children and by creating a viewer notification, which it passes to {@link #fireNotifyChanged}.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public void notifyChanged(Notification notification)
  {
    updateChildren(notification);

    switch (notification.getFeatureID(EModelElement.class))
    {
      case EcorePackage.EMODEL_ELEMENT__EANNOTATIONS:
        fireNotifyChanged(new ViewerNotification(notification, notification.getNotifier(), true, false));
        return;
    }
    super.notifyChanged(notification);
  }

  /**
   * This adds {@link org.eclipse.emf.edit.command.CommandParameter}s describing the children
   * that can be created under this object.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  protected void collectNewChildDescriptors(Collection<Object> newChildDescriptors, Object object)
  {
    super.collectNewChildDescriptors(newChildDescriptors, object);

    newChildDescriptors.add
      (createChildParameter
        (EcorePackage.Literals.EMODEL_ELEMENT__EANNOTATIONS,
         EcoreFactory.eINSTANCE.createEAnnotation()));
  }

  /** 
   * Strips whitespace and converts the empty string to null.
   * 
   * @param value Any string or null.
   * @return the trimmed value or null if it's an empty string.
   */
  public String stripToNull(String value)
  {
    if (value != null)
    {
      value = value.trim();
      if (value.length() == 0)
      {
        value = null;
      }
    }
    return value;
  }

  protected static class ItemPropertyDescriptorWithUniqueChoiceOfValueLabels extends ItemPropertyDescriptor
  {
    public ItemPropertyDescriptorWithUniqueChoiceOfValueLabels
     (AdapterFactory adapterFactory,
      ResourceLocator resourceLocator,
      String displayName,
      String description,
      EStructuralFeature feature, 
      boolean isSettable,
      boolean multiLine,
      boolean sortChoices,
      Object staticImage,
      String category,
      String [] filterFlags)
    {
      super(adapterFactory, resourceLocator, displayName, description, feature,  isSettable, multiLine, sortChoices, staticImage, category, filterFlags);
    }

    protected Map<Object, String> uniqueNameMap;

    @Override
    public IItemLabelProvider getLabelProvider(Object object)
    {
      if (uniqueNameMap != null)
      {
        final Map<Object, String> uniqueNameMap = this.uniqueNameMap;
        this.uniqueNameMap = null;
        return
          new ItemDelegator(adapterFactory, resourceLocator)
          {
            @Override
            public String getText(Object object)
            {
              String result = uniqueNameMap.get(object);
              return result != null ? result : super.getText(object);
            }
          };
      }
      else
      {
        return super.getLabelProvider(object);
      }
    }

    protected Map<Object, String> computeUniqueLabels(Object object, Collection<?> items)
    {
      Resource resource = ((EObject)object).eResource();
      URI base = resource == null ? URI.createURI("") : resource.getURI();
      Set<String> conflictingLabels = new HashSet<String>();
      Map<String, Object> labelToObjectMap = new HashMap<String, Object>();
      IItemLabelProvider labelProvider = getLabelProvider(object);
      for (Object item : items)
      {
        String label = labelProvider.getText(item);
        if ("".equals(label))
        {
          if (item != null)
          {
            labelToObjectMap.put("- " + getURI((EObject)item, base), item);
          }
        }
        else if (conflictingLabels.contains(label))
        {
          labelToObjectMap.put(label + " - " + getURI((EObject)item, base), item);
        }
        else
        {
          Object collision = labelToObjectMap.put(label, item);
          if (collision != null)
          {
            conflictingLabels.add(label);
            labelToObjectMap.remove(label);
            labelToObjectMap.put(label + " - " + getURI((EObject)item, base), item);
            labelToObjectMap.put(label + " - " + getURI((EObject)collision, base), collision);
          }
        }
      }

      Map<Object, String> result = new HashMap<Object, String>();
      for (Map.Entry<String, Object> entry : labelToObjectMap.entrySet())
      {
        result.put(entry.getValue(), entry.getKey());
      }
      return result;
    }

    private URI getURI(EObject eObject, URI base)
    {
      URI uri = EcoreUtil.getURI(eObject);
      return uri.deresolve(base);
    }

    @Override
    public Collection<?> getChoiceOfValues(Object object)
    {
      Collection<?> result = super.getChoiceOfValues(object);
      if (feature instanceof EReference && object instanceof EObject)
      {
        @SuppressWarnings("unchecked")
        List<EObject> eObjects = (List<EObject>)(List<?>)new LinkedList<Object>(result);
        Resource resource = ((EObject)object).eResource();
        if (resource != null)
        {
          ResourceSet resourceSet = resource.getResourceSet();
          if (resourceSet != null)
          {
            Collection<EObject> visited = new HashSet<EObject>(eObjects);
            Registry packageRegistry = resourceSet.getPackageRegistry();
            for (String nsURI : packageRegistry.keySet())
            {
              collectReachableObjectsOfType(visited, eObjects, packageRegistry.getEPackage(nsURI), feature.getEType());
            }
          }
        }
        result = eObjects;
      }
      return result;
    }
  }
}
