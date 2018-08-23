/**
 * Copyright (c) 2002-2006 IBM Corporation and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 *
 * Contributors:
 *   IBM - Initial API and implementation
 */
package org.eclipse.emf.ecore.provider;


import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.ResourceLocator;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EAnnotation;
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
import org.eclipse.emf.edit.provider.AdapterFactoryItemDelegator;
import org.eclipse.emf.edit.provider.IEditingDomainItemProvider;
import org.eclipse.emf.edit.provider.IItemLabelProvider;
import org.eclipse.emf.edit.provider.IItemPropertyDescriptor;
import org.eclipse.emf.edit.provider.IItemPropertySource;
import org.eclipse.emf.edit.provider.IStructuredItemContentProvider;
import org.eclipse.emf.edit.provider.ITreeItemContentProvider;
import org.eclipse.emf.edit.provider.ItemProviderAdapter;
import org.eclipse.emf.edit.provider.ItemPropertyDescriptor;

import org.eclipse.emf.edit.provider.ViewerNotification;

/**
 * This is the item provider adapter for a {@link org.eclipse.emf.ecore.EModelElement} object.
 * <!-- begin-user-doc -->
 * <!-- end-user-doc -->
 * @generated
 */
public class EModelElementItemProvider
  extends ItemProviderAdapter
  implements
    IEditingDomainItemProvider, IStructuredItemContentProvider, ITreeItemContentProvider, IItemLabelProvider, IItemPropertySource
{
  /**
   * This is used to store all the non-generic children features.
   * @see #getChildrenFeatures()
   * @since 2.15
   */
  protected List<EStructuralFeature> nonGenericChildrenFeatures;

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
   * @generated NOT
   */
  @Override
  public Collection<? extends EStructuralFeature> getChildrenFeatures(Object object)
  {
    List<EStructuralFeature> childrenFeatures = getChildrenFeatures();
    if (childrenFeatures == null)
    {
      if (isShowGenerics())
      {
        childrenFeatures = this.childrenFeatures = new ArrayList<EStructuralFeature>();
      }
      else
      {
        childrenFeatures = nonGenericChildrenFeatures = new ArrayList<EStructuralFeature>();
      }
      childrenFeatures.add(EcorePackage.Literals.EMODEL_ELEMENT__EANNOTATIONS);
    }
    return childrenFeatures;
  }

  /**
   * Returns whether the adapter factory {@link EcoreItemProviderAdapterFactory#isShowGenerics() supports showing generics}.
   * @since 2.14
   */
  protected boolean isShowGenerics()
  {
    return !(adapterFactory instanceof EcoreItemProviderAdapterFactory) || ((EcoreItemProviderAdapterFactory)adapterFactory).isShowGenerics();
  }

  /**
   * Returns either {@link #childrenFeatures} or {@link #nonGenericChildrenFeatures} depending on {@link #isShowGenerics()}.
   * @since 2.15
   */
  protected List<EStructuralFeature> getChildrenFeatures()
  {
    return isShowGenerics() ? childrenFeatures : nonGenericChildrenFeatures;
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
   * @generated NOT
   */
  @Override
  protected void collectNewChildDescriptors(Collection<Object> newChildDescriptors, Object object)
  {
    super.collectNewChildDescriptors(newChildDescriptors, object);

    EModelElement eModelElement = (EModelElement)object;

    // Collect the sources we will use for creating annotations.
    // It will always include null which will always be first.
    //
    Collection<String> sources = new ArrayList<String>();
    sources.add(null);

    if (eModelElement.eClass().getEPackage() == EcorePackage.eINSTANCE)
    {
      // Create an annotation that we will temporarily add as a child.
      // We will disable notifications because no adapter should see this happen.
      //
      EList<EAnnotation> eAnnotations = eModelElement.getEAnnotations();
      EAnnotation eAnnotation = EcoreFactory.eINSTANCE.createEAnnotation();
      eModelElement.eSetDeliver(false);
      try
      {
        // Add the annotation
        eAnnotations.add(eAnnotation);
        IItemPropertyDescriptor propertyDescriptor = new AdapterFactoryItemDelegator(getRootAdapterFactory()).getPropertyDescriptor(
          eAnnotation,
          EcorePackage.Literals.EANNOTATION__SOURCE);
        @SuppressWarnings("unchecked")
        Collection<String> choiceOfValues = (Collection<String>)propertyDescriptor.getChoiceOfValues(eAnnotation);
        sources.addAll(choiceOfValues);
      }
      finally
      {
        // No matter what might go wrong, we will remove the annotation, re-enable notification, and clear any adapters added to the annotation.
        eAnnotations.remove(eAnnotation);
        eModelElement.eSetDeliver(true);
        eAnnotation.eAdapters().clear();
      }
    }

    // Create a child descriptor for each source.
    //
    for (String source : sources)
    {
      EAnnotation eAnnotation = EcoreFactory.eINSTANCE.createEAnnotation();
      eAnnotation.setSource(source);
      newChildDescriptors.add(createChildParameter(EcorePackage.Literals.EMODEL_ELEMENT__EANNOTATIONS, eAnnotation));
    }
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
    return EcoreEditPlugin.INSTANCE;
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
