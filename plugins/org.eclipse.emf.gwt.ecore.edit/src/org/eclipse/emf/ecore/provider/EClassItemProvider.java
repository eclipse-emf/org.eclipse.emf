/**
 * Copyright (c) 2002-2012 IBM Corporation and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *   IBM - Initial API and implementation
 */
package org.eclipse.emf.ecore.provider;


import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import org.eclipse.emf.common.command.CompoundCommand;
import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EGenericType;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.ETypeParameter;
import org.eclipse.emf.ecore.EcoreFactory;
import org.eclipse.emf.ecore.EcorePackage;
import org.eclipse.emf.edit.command.SetCommand;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.emf.edit.provider.ComposeableAdapterFactory;
import org.eclipse.emf.edit.provider.IEditingDomainItemProvider;
import org.eclipse.emf.edit.provider.IItemLabelProvider;
import org.eclipse.emf.edit.provider.IItemPropertyDescriptor;
import org.eclipse.emf.edit.provider.IItemPropertySource;
import org.eclipse.emf.edit.provider.IStructuredItemContentProvider;
import org.eclipse.emf.edit.provider.ITreeItemContentProvider;
import org.eclipse.emf.edit.provider.ItemPropertyDescriptor;


import org.eclipse.emf.edit.provider.ViewerNotification;

/**
 * This is the item provider adapter for a {@link org.eclipse.emf.ecore.EClass} object.
 * <!-- begin-user-doc -->
 * <!-- end-user-doc -->
 * @generated
 */
public class EClassItemProvider
  extends EClassifierItemProvider
  implements
    IEditingDomainItemProvider, IStructuredItemContentProvider, ITreeItemContentProvider, IItemLabelProvider, IItemPropertySource
{
  /**
   * This constructs an instance from a factory and a notifier.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EClassItemProvider(AdapterFactory adapterFactory)
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

      addAbstractPropertyDescriptor(object);
      addInterfacePropertyDescriptor(object);
      addESuperTypesPropertyDescriptor(object);
    }
    return itemPropertyDescriptors;
  }

  /**
   * This adds a property descriptor for the Abstract feature.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected void addAbstractPropertyDescriptor(Object object)
  {
    itemPropertyDescriptors.add
      (createItemPropertyDescriptor
        (((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
         getResourceLocator(),
         getString("_UI_EClass_abstract_feature"),
         getString("_UI_EClass_abstract_description"),
         EcorePackage.Literals.ECLASS__ABSTRACT,
         true,
         false,
         false,
         ItemPropertyDescriptor.BOOLEAN_VALUE_IMAGE,
         null,
         null));
  }

  /**
   * This adds a property descriptor for the Interface feature.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated NOT
   */
  protected void addInterfacePropertyDescriptor(Object object)
  {
    itemPropertyDescriptors.add
      (new ItemPropertyDescriptor
        (((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
         getResourceLocator(),
         getString("_UI_EClass_interface_feature"),
         getString("_UI_EClass_interface_description"),
         EcorePackage.Literals.ECLASS__INTERFACE,
         true,
         false,
         false,
         ItemPropertyDescriptor.BOOLEAN_VALUE_IMAGE,
         null,
         null)
      {
        @Override
        public void setPropertyValue(Object object, Object value)
        {
          EditingDomain editingDomain = getEditingDomain(object);
          if (editingDomain != null && Boolean.TRUE.equals(value))
          {
            CompoundCommand command = new CompoundCommand();
            command.append(SetCommand.create(editingDomain, object, EcorePackage.Literals.ECLASS__INTERFACE, Boolean.TRUE));
            command.append(SetCommand.create(editingDomain, object, EcorePackage.Literals.ECLASS__ABSTRACT, Boolean.TRUE));
            editingDomain.getCommandStack().execute(command);
          }
          else
          {
            super.setPropertyValue(object, value);
          }
        }
      });
  }

  /**
   * This adds a property descriptor for the ESuper Types feature.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated NOT
   */
  protected void addESuperTypesPropertyDescriptor(Object object)
  {
    itemPropertyDescriptors.add
      (new ItemPropertyDescriptorWithUniqueChoiceOfValueLabels
        (((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
         getResourceLocator(),
         getString("_UI_EClass_eSuperTypes_feature"),
         getString("_UI_EClass_eSuperTypes_description"),
         EcorePackage.Literals.ECLASS__ESUPER_TYPES,
         true,
         false,
         true,
         null,
         null,
         null)
       {
         @Override
         public Collection<?> getChoiceOfValues(Object object)
         {
           EClass eClass = (EClass)object;
           
           // Filter out classes that aren't permitted.
           //
           Collection<?> result = super.getChoiceOfValues(object);
           if (eClass.eResource() == null ||
                 eClass.eResource().getResourceSet() == null ||
                 !eClass.eResource().getResourceSet().getPackageRegistry().containsKey(EcorePackage.eNS_URI))
           {
             result.removeAll(EcorePackage.eINSTANCE.getEClassifiers());
           }
           for (Iterator<?> i = result.iterator(); i.hasNext(); )
           {
             EClass otherEClass = (EClass)i.next();
             if (otherEClass == eClass || otherEClass.getEAllSuperTypes().contains(eClass))
             {
               i.remove();
             }
           }

           uniqueNameMap = computeUniqueLabels(object, result);

           return result;
         }
         
         @Override
         public void setPropertyValue(Object object, Object value)
         {
           EditingDomain editingDomain = getEditingDomain(object);
           if (editingDomain == null)
           {
             super.setPropertyValue(object, value);
           }
           else 
           {
             EClass eClass = (EClass)object;
             List<EGenericType> eGenericTypes = new ArrayList<EGenericType>();
             @SuppressWarnings("unchecked")
             List<EClass> list = (List<EClass>)value;
             LOOP:
             for (EClass eSuperType : list)
             {
               for (EGenericType eGenericSuperType : eClass.getEGenericSuperTypes())
               {
                 if (eGenericSuperType.getEClassifier() == eSuperType)
                 {
                   eGenericTypes.add(eGenericSuperType);
                   continue LOOP;
                 }
               }
               EGenericType eGenericType = EcoreFactory.eINSTANCE.createEGenericType();
               eGenericType.setEClassifier(eSuperType);
               for (int i = 0, size = eSuperType.getETypeParameters().size(); i < size; ++i)
               {
                 eGenericType.getETypeArguments().add(EcoreFactory.eINSTANCE.createEGenericType());
               }
               eGenericTypes.add(eGenericType);
             }
             editingDomain.getCommandStack().execute
               (SetCommand.create(editingDomain, object, EcorePackage.Literals.ECLASS__EGENERIC_SUPER_TYPES, eGenericTypes));
           }
         }
       });
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
    if (childrenFeatures == null)
    {
      super.getChildrenFeatures(object);
      childrenFeatures.add(EcorePackage.Literals.ECLASS__EOPERATIONS);
      childrenFeatures.add(EcorePackage.Literals.ECLASS__ESTRUCTURAL_FEATURES);
      childrenFeatures.add(2, EcorePackage.Literals.ECLASS__EGENERIC_SUPER_TYPES);
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

  @Override
  public String getCreateChildText(Object owner, Object feature, Object child, Collection<?> selection)
  {
    return
      feature == EcorePackage.Literals.ECLASS__EGENERIC_SUPER_TYPES ?
        getString("_UI_EGenericSuperType_label") : 
        super.getCreateChildText(owner, feature, child, selection);
  }

  /**
   * This returns EClass.gif.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public Object getImage(Object object)
  {
    return overlayImage(object, getResourceLocator().getImage("full/obj16/EClass"));
  }

  /**
   * This returns the label text for the adapted class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated NOT
   */
  @Override
  public String getText(Object object)
  {
    EClass eClass = (EClass)object;
    StringBuffer result = new StringBuffer();
    if (eClass.getName() != null)
    {
      result.append(eClass.getName());
    }

    if (!eClass.getETypeParameters().isEmpty())
    {
      result.append("<");
      for (Iterator<ETypeParameter> i = eClass.getETypeParameters().iterator(); i.hasNext(); )
      {
        ETypeParameter eTypeParameter = i.next();
        result.append(ETypeParameterItemProvider.getText(eTypeParameter));
        if (i.hasNext())
        {
          result.append(", ");
        }
      }
      result.append(">");
    }

    if (!eClass.getEGenericSuperTypes().isEmpty())
    {
      result.append(" -> ");
      for (Iterator<EGenericType> i = eClass.getEGenericSuperTypes().iterator(); i.hasNext(); )
      {
        EGenericType eGenericSuperType = i.next();
        result.append(EGenericTypeItemProvider.getText(eGenericSuperType));
        if (i.hasNext())
        {
          result.append(", ");
        }
      }
    }
    if (eClass.getInstanceTypeName() != null)
    {
      result.append(" [");
      result.append(eClass.getInstanceTypeName());
      result.append("]");
    }

    return result.toString();
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

    switch (notification.getFeatureID(EClass.class))
    {
      case EcorePackage.ECLASS__ABSTRACT:
      case EcorePackage.ECLASS__INTERFACE:
      case EcorePackage.ECLASS__ESUPER_TYPES:
      case EcorePackage.ECLASS__EREFERENCES:
      case EcorePackage.ECLASS__EATTRIBUTES:
        fireNotifyChanged(new ViewerNotification(notification, notification.getNotifier(), false, true));
        return;
      case EcorePackage.ECLASS__EOPERATIONS:
      case EcorePackage.ECLASS__ESTRUCTURAL_FEATURES:
      case EcorePackage.ECLASS__EGENERIC_SUPER_TYPES:
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
        (EcorePackage.Literals.ECLASS__EOPERATIONS,
         EcoreFactory.eINSTANCE.createEOperation()));

    newChildDescriptors.add
      (createChildParameter
        (EcorePackage.Literals.ECLASS__ESTRUCTURAL_FEATURES,
         EcoreFactory.eINSTANCE.createEAttribute()));

    newChildDescriptors.add
      (createChildParameter
        (EcorePackage.Literals.ECLASS__ESTRUCTURAL_FEATURES,
         EcoreFactory.eINSTANCE.createEReference()));

    newChildDescriptors.add
      (createChildParameter
        (EcorePackage.Literals.ECLASS__EGENERIC_SUPER_TYPES,
         EcoreFactory.eINSTANCE.createEGenericType()));
  }

}
