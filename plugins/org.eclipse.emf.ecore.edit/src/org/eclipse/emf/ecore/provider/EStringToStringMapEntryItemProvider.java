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


import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.util.ResourceLocator;
import org.eclipse.emf.common.util.UniqueEList;
import org.eclipse.emf.ecore.EAnnotation;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EModelElement;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.EcorePackage;
import org.eclipse.emf.ecore.provider.annotation.EAnnotationItemProviderAdapterFactory;
import org.eclipse.emf.ecore.util.BasicEAnnotationValidator;
import org.eclipse.emf.edit.domain.AdapterFactoryEditingDomain;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.emf.edit.provider.ComposeableAdapterFactory;
import org.eclipse.emf.edit.provider.IEditingDomainItemProvider;
import org.eclipse.emf.edit.provider.IItemLabelProvider;
import org.eclipse.emf.edit.provider.IItemPropertyDescriptor;
import org.eclipse.emf.edit.provider.IItemPropertySource;
import org.eclipse.emf.edit.provider.IStructuredItemContentProvider;
import org.eclipse.emf.edit.provider.ITreeItemContentProvider;
import org.eclipse.emf.edit.provider.ItemPropertyDescriptor;
import org.eclipse.emf.edit.provider.ItemPropertyDescriptorDecorator;
import org.eclipse.emf.edit.provider.ItemProviderAdapter;
import org.eclipse.emf.edit.provider.ViewerNotification;


/**
 * This is the item provider adapter for a {@link java.util.Map.Entry} object.
 * <!-- begin-user-doc -->
 * This implementation is {@link EAnnotationItemProviderAdapterFactory}-aware using {@link #getEAnnotationItemProviderAdapterFactory()} to drive specialized behavior.
 * I.e., it behaves differently when created by {@link EcoreItemProviderAdapterFactory} versus when created by {@link EAnnotationItemProviderAdapterFactory}.
 * <!-- end-user-doc -->
 * @generated
 */
public class EStringToStringMapEntryItemProvider extends ItemProviderAdapter
  implements
    IEditingDomainItemProvider,
    IStructuredItemContentProvider,
    ITreeItemContentProvider,
    IItemLabelProvider,
    IItemPropertySource
{
  /**
   * This constructs an instance from a factory and a notifier.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   */
  public EStringToStringMapEntryItemProvider(AdapterFactory adapterFactory)
  {
    super(adapterFactory);
  }

  /**
   * Returns the {@link EAnnotationItemProviderAdapterFactory} if the adapter factory is of that type, <code>null</code> otherwise.
   * @return the annotation item provider adapter factory or <code>null</code>
   * @since 2.14
   */
  protected EAnnotationItemProviderAdapterFactory getEAnnotationItemProviderAdapterFactory()
  {
    return adapterFactory instanceof EAnnotationItemProviderAdapterFactory ? (EAnnotationItemProviderAdapterFactory)adapterFactory : null;
  }

  /**
   * Returns the {@link EAnnotationItemProviderAdapterFactory#getAssistant() assistant} of the {@link #getEAnnotationItemProviderAdapterFactory() annotation item provider adapter factory}, if available.
   * @return the assistant or <code>null</code>.
   * @since 2.14
   */
  protected BasicEAnnotationValidator.Assistant getAssistant()
  {
    EAnnotationItemProviderAdapterFactory eAnnotationItemProviderAdapterFactory = getEAnnotationItemProviderAdapterFactory();
    return eAnnotationItemProviderAdapterFactory == null ? null : eAnnotationItemProviderAdapterFactory.getAssistant();
  }

  /**
   * Returns the containing annotation of the given annotation detail.
   *
   * @since 2.14
   */
  protected EAnnotation getEAnnotation(Object object)
  {
    EObject eContainer = ((EObject)object).eContainer();
    return eContainer instanceof EAnnotation ? (EAnnotation)eContainer : null;
  }

  /**
   * Returns the containing model element of the {@link #getEAnnotation(Object) containing annotation}.
   *
   * @since 2.14
   */
  protected EModelElement getEModelElement(Object object)
  {
    EAnnotation eAnnotation = getEAnnotation(object);
    return eAnnotation != null ? eAnnotation.getEModelElement() : null;
  }

  /**
   * This returns the property descriptors for the adapted class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected List<IItemPropertyDescriptor> getPropertyDescriptorsGen(Object object)
  {
    if (itemPropertyDescriptors == null)
    {
      super.getPropertyDescriptors(object);

      addKeyPropertyDescriptor(object);
      addValuePropertyDescriptor(object);
    }
    return itemPropertyDescriptors;
  }

  /**
   * {@inheritDoc}
   * <p>
   * This implementation is specialized to clear the cached {@link #itemPropertyDescriptors descriptors} if this adapter was created by an {@link EAnnotationItemProviderAdapterFactory}.
   * <p>
   */
  @Override
  public List<IItemPropertyDescriptor> getPropertyDescriptors(Object object)
  {
    if (getEAnnotationItemProviderAdapterFactory() != null)
    {
      itemPropertyDescriptors = null;
    }

    List<IItemPropertyDescriptor> propertyDescriptors = getPropertyDescriptorsGen(object);
    return propertyDescriptors;
  }

  /**
   * This adds a property descriptor for the Key feature.
   * <!-- begin-user-doc -->
   * This implementation is specialized to call {@link #createKeyPropertyDescriptor(Map.Entry)}.
   * <!-- end-user-doc -->
   * @generated NOT
   */
  protected void addKeyPropertyDescriptor(Object object)
  {
    @SuppressWarnings("unchecked")
    Map.Entry<String, String> entry = (Map.Entry<String, String>)object;
    itemPropertyDescriptors.add(createKeyPropertyDescriptor(entry));
  }

  /**
   * Creates the property descriptor for the entry's key.
   * <p>
   * This implementation creates specialized instance of property descriptor
   * that delegates {@link ItemPropertyDescriptor#getChoiceOfValues(Object)} to {@link #getKeyChoiceOfValues(Map.Entry)},
   * that delegates {@link ItemPropertyDescriptor#isSortChoices(Object)} to {@link #isKeySortChoices(Map.Entry)},
   * and returns <code>true</code> for {@link ItemPropertyDescriptor#isChoiceArbitrary(Object)}.
   * </p>
   * @param entry the detail entry.
   * @return the property descriptor for the entry's key.
   *
   * @since 2.14
   */
  protected IItemPropertyDescriptor createKeyPropertyDescriptor(Map.Entry<String, String> entry)
  {
    return new ValueHandlingPropertyDescriptor(
      ((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
      getResourceLocator(),
      getString("_UI_EStringToStringMapEntry_key_feature"),
      getString("_UI_EStringToStringMapEntry_key_description"),
      EcorePackage.Literals.ESTRING_TO_STRING_MAP_ENTRY__KEY,
      true,
      false,
      isKeySortChoices(entry),
      ItemPropertyDescriptor.GENERIC_VALUE_IMAGE,
      null,
      null)
      {
        @Override
        public Collection<?> getChoiceOfValues(Object object)
        {
          @SuppressWarnings("unchecked")
          Map.Entry<String, String> entry = (Map.Entry<String, String>)object;
          return getKeyChoiceOfValues(entry);
        }
      };
  }

  /**
   * Returns whether this entry supports {@link IItemPropertyDescriptor#isSortChoices(Object) sorting} choices for the property descriptor for the value.
   * <p>
   * This implementation always returns <code>true</code>
   * </p>
   * @param entry the entry to test.
   * @return whether this entry supports sorting choices for the property descriptor for the value.
   * @since 2.14
   */
  protected boolean isKeySortChoices(Map.Entry<String, String> entry)
  {
    return true;
  }

  /**
   * Returns the choices for the value feature.
   * <p>
   * This implementation uses the {@link #getEAnnotationItemProviderAdapterFactory()} {@link EAnnotationItemProviderAdapterFactory#getAssistant() assistant}
   * to {@link org.eclipse.emf.ecore.util.BasicEAnnotationValidator.Assistant#createInstance(EClass, EAnnotation) create} modeled objects and uses
   * the keys of their {@link org.eclipse.emf.ecore.util.BasicEAnnotationValidator.Assistant#getApplicableProperties(EObject, EAnnotation) applicable properties}
   * to build a set of valid keys, removing any keys already in the {@link EAnnotation#getDetails() details},
   * and then including the current value of the entry's key.
   * Otherwise it returns <code>null</code>
   * </p>
   * @param entry the entry.
   * @return the choices for the value feature.
   * @since 2.14
   */
  protected Collection<?> getKeyChoiceOfValues(Map.Entry<String, String> entry)
  {
    BasicEAnnotationValidator.Assistant assistant = getAssistant();
    if (assistant != null)
    {
      EModelElement eModelElement = getEModelElement(entry);
      if (eModelElement != null)
      {
        EAnnotation eAnnotation = getEAnnotation(entry);
        List<String> result = new UniqueEList<String>();
        for (EClass eClass : assistant.getPropertyClasses(eModelElement))
        {
          EObject instance = assistant.createInstance(eClass, eAnnotation);
          result.addAll(assistant.getApplicableProperties(instance, eAnnotation).keySet());
        }

        // Remove any property names already used within the annotation.
        result.removeAll(eAnnotation.getDetails().keySet());

        // Add the entry's key, if it's not null.
        String key = entry.getKey();
        result.add(key);

        return result;
      }
    }
    return null;
  }

  /**
   * This adds a property descriptor for the Value feature.
   * <!-- begin-user-doc -->
   * This implementation is specialized to delegate to {@link #createValuePropertyDescriptor(Map.Entry)}.
   * <!-- end-user-doc -->
   * @generated NOT
   */
  protected void addValuePropertyDescriptor(Object object)
  {
    @SuppressWarnings("unchecked")
    Map.Entry<String, String> entry = (Map.Entry<String, String>)object;
    itemPropertyDescriptors.add(createValuePropertyDescriptor(entry));
  }

  /**
   * Creates the property descriptor the entry's value.
   * <p>
   * This implementation creates specialized instance of property descriptor
   * that delegates {@link ItemPropertyDescriptor#getChoiceOfValues(Object)} to {@link #getValueChoiceOfValues(Map.Entry)},
   * that delegates {@link ItemPropertyDescriptor#isMultiLine(Object)} to {@link #isValueMultiLine(Map.Entry)},
   * that delegates {@link ItemPropertyDescriptor#isSortChoices(Object)} to {@link #isValueSortChoices(Map.Entry)},
   * and returns <code>true</code> for {@link ItemPropertyDescriptor#isChoiceArbitrary(Object)}.
   * </p>
   * <p>
   * <p>
   * This implementation uses the {@link #getEAnnotationItemProviderAdapterFactory()} {@link EAnnotationItemProviderAdapterFactory#getAssistant() assistant}
   * to {@link org.eclipse.emf.ecore.util.BasicEAnnotationValidator.Assistant#createInstance(EClass, EAnnotation) create} modeled objects, when available.
   * If the entry corresponds one of the modeled object's {@link org.eclipse.emf.ecore.util.BasicEAnnotationValidator.Assistant#getApplicableProperties(EObject, EAnnotation) applicable properties},
   * it {@link EAnnotationItemProviderAdapterFactory#getPropertyDescriptor(EObject, String, EStructuralFeature, EAnnotation, ResourceLocator) gets the property descriptor} for that object,
   * {@link EAnnotationItemProviderAdapterFactory#createPropertyDescriptorDecorator(IItemPropertyDescriptor, EObject, String, EStructuralFeature, EAnnotation, ResourceLocator, EditingDomain) creates a decorate} for descriptor,
   * and finally creates yet another decorator that that decorator
   * that makes the property look and behave like a regular value property descriptor, except that the description, value handling, and cell editor is that of the modeled object.
   * </p>
   * </p>
   * @param entry the detail entry.
   * @return the property descriptor for the entry's value.
   * @since 2.14
   */
  protected IItemPropertyDescriptor createValuePropertyDescriptor(final Map.Entry<String, String> entry)
  {
    final IItemPropertyDescriptor valuePropertyDescriptor = new ValueHandlingPropertyDescriptor(
      ((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
      getResourceLocator(),
      getString("_UI_EStringToStringMapEntry_value_feature"),
      getString("_UI_EStringToStringMapEntry_value_description"),
      EcorePackage.Literals.ESTRING_TO_STRING_MAP_ENTRY__VALUE,
      true,
      isValueMultiLine(entry),
      isValueSortChoices(entry),
      ItemPropertyDescriptor.GENERIC_VALUE_IMAGE,
      null,
      null)
      {
        @Override
        public Collection<?> getChoiceOfValues(Object object)
        {
          @SuppressWarnings("unchecked")
          Map.Entry<String, String> entry = (Map.Entry<String, String>)object;
          return getValueChoiceOfValues(entry);
        }
      };
    EAnnotationItemProviderAdapterFactory eAnnotationItemProviderAdapterFactory = getEAnnotationItemProviderAdapterFactory();
    BasicEAnnotationValidator.Assistant assistant = getAssistant();
    if (eAnnotationItemProviderAdapterFactory != null && assistant != null)
    {
      EModelElement eModelElement = getEModelElement(entry);
      if (eModelElement != null)
      {
        EAnnotation eAnnotation = getEAnnotation(entry);
        List<EClass> propertyClasses = assistant.getPropertyClasses(eModelElement);
        String key = entry.getKey();
        for (EClass eClass : propertyClasses)
        {
          EObject instance = assistant.createInstance(eClass, eAnnotation);
          Map<String, EStructuralFeature> applicableProperties = assistant.getApplicableProperties(instance, eAnnotation);
          final EStructuralFeature eStructuralFeature = applicableProperties.get(key);
          if (eClass.getEAllStructuralFeatures().contains(eStructuralFeature))
          {
            IItemPropertyDescriptor itemPropertyDescriptor = eAnnotationItemProviderAdapterFactory.getPropertyDescriptor(
              instance,
              key,
              eStructuralFeature,
              eAnnotation,
              getResourceLocator());

            EditingDomain domain = AdapterFactoryEditingDomain.getEditingDomainFor(eAnnotation);
            IItemPropertyDescriptor decoratedItemPropertyDescriptor = eAnnotationItemProviderAdapterFactory.createPropertyDescriptorDecorator(
              itemPropertyDescriptor,
              instance,
              key,
              eStructuralFeature,
              eAnnotation,
              getResourceLocator(),
              domain);

            return new ItemPropertyDescriptorDecorator(entry, decoratedItemPropertyDescriptor)
              {
                @Override
                public String getDisplayName(Object thisObject)
                {
                  return valuePropertyDescriptor.getDisplayName(entry);
                }

                @Override
                public Object getFeature(Object thisObject)
                {
                  return eStructuralFeature;
                }

                @Override
                public String getCategory(Object thisObject)
                {
                  return null;
                }

                @Override
                public String[] getFilterFlags(Object thisObject)
                {
                  return null;
                }

                @Override
                public boolean isPropertySet(Object thisObject)
                {
                  return valuePropertyDescriptor.isPropertySet(entry);
                }

                @Override
                public void resetPropertyValue(Object thisObject)
                {
                  valuePropertyDescriptor.resetPropertyValue(entry);
                }

                @Override
                public boolean isPropertyUnsettable(Object object)
                {
                  Object propertyValue = getPropertyValue(object);
                  return propertyValue != null;
                }
              };
          }
        }
      }
    }

    return valuePropertyDescriptor;
  }

  private static class ValueHandlingPropertyDescriptor extends ItemPropertyDescriptor
  {
    public ValueHandlingPropertyDescriptor(
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
      super(adapterFactory, resourceLocator, displayName, description, feature, isSettable, multiLine, sortChoices, staticImage, category, filterFlags);
    }

    @Override
    public boolean isChoiceArbitrary(Object object)
    {
      return true;
    }
  }

  /**
   * Returns whether this entry supports a {@link IItemPropertyDescriptor#isMultiLine(Object) multi-line} property descriptor for the value feature.
   * <p>
   * This implementation always returns <code>true</code>.
   * </p>
   * @param entry the entry in question.
   * @return whether this entry supported a multi-line property descriptor for the value.
   * @since 2.14
   */
  protected boolean isValueMultiLine(Map.Entry<String, String> entry)
  {
    return true;
  }

  /**
   * Returns whether this entry supports {@link IItemPropertyDescriptor#isSortChoices(Object) sorting} choices for the property descriptor for the value feature.
   * <p>
   * This implementation always returns <code>true</code>.
   * </p>
   * @param entry the entry to test.
   * @return whether this entry supports a sorting choices for the property descriptor for the value.
   * @since 2.14
   */
  protected boolean isValueSortChoices(Map.Entry<String, String> entry)
  {
    return true;
  }

  /**
   * Returns the choices for the value feature.
   * This implementation always returns <code>null</code>.
   * @param entry the entry.
   * @return the choices for the value feature.
   * @since 2.14
   */
  protected Collection<?> getValueChoiceOfValues(Map.Entry<String, String> entry)
  {
    return null;
  }

  /**
   * This returns EStringToStringMapEntry.gif.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public Object getImage(Object object)
  {
    return overlayImage(object, getResourceLocator().getImage("full/obj16/EStringToStringMapEntry"));
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
    Map.Entry<?, ?> eStringToStringMapEntry = (Map.Entry<?, ?>)object;
    String key = "" + eStringToStringMapEntry.getKey();
    String value = crop("" + eStringToStringMapEntry.getValue());
    return key + " -> " + value;
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

    switch (notification.getFeatureID(Map.Entry.class))
    {
      case EcorePackage.ESTRING_TO_STRING_MAP_ENTRY__KEY:
      case EcorePackage.ESTRING_TO_STRING_MAP_ENTRY__VALUE:
        fireNotifyChanged(new ViewerNotification(notification, notification.getNotifier(), false, true));
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
}
