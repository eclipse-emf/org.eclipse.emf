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
package org.eclipse.emf.java.provider;


import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.edit.provider.ComposeableAdapterFactory;
import org.eclipse.emf.edit.provider.IEditingDomainItemProvider;
import org.eclipse.emf.edit.provider.IItemLabelProvider;
import org.eclipse.emf.edit.provider.IItemPropertyDescriptor;
import org.eclipse.emf.edit.provider.IItemPropertySource;
import org.eclipse.emf.edit.provider.IStructuredItemContentProvider;
import org.eclipse.emf.edit.provider.ITreeItemContentProvider;
import org.eclipse.emf.edit.provider.ItemPropertyDescriptor;
import org.eclipse.emf.edit.provider.ViewerNotification;
import org.eclipse.emf.java.JClass;
import org.eclipse.emf.java.JavaPackage;


/**
 * This is the item provider adapter for a {@link org.eclipse.emf.java.JClass} object.
 * <!-- begin-user-doc -->
 * <!-- end-user-doc -->
 * @generated
 */
public class JClassItemProvider
  extends JMemberItemProvider
  implements 
    IEditingDomainItemProvider, IStructuredItemContentProvider, ITreeItemContentProvider, IItemLabelProvider, IItemPropertySource
{
  /**
   * This constructs an instance from a factory and a notifier.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public JClassItemProvider(AdapterFactory adapterFactory)
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
      addFinalPropertyDescriptor(object);
      addInterfacePropertyDescriptor(object);
      addThrowablePropertyDescriptor(object);
      addJavaClassPropertyDescriptor(object);
      addFieldsPropertyDescriptor(object);
      addMethodsPropertyDescriptor(object);
      addSuperTypesPropertyDescriptor(object);
      addAllSuperTypesPropertyDescriptor(object);
      addMembersPropertyDescriptor(object);
      addComponentTypePropertyDescriptor(object);
      addUnitPropertyDescriptor(object);
      addAllMethodsPropertyDescriptor(object);
      addAllFieldsPropertyDescriptor(object);
      addPackagePropertyDescriptor(object);
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
         getString("_UI_JClass_abstract_feature"),
         getString("_UI_PropertyDescriptor_description", "_UI_JClass_abstract_feature", "_UI_JClass_type"),
         JavaPackage.Literals.JCLASS__ABSTRACT,
         true,
         false,
         false,
         ItemPropertyDescriptor.BOOLEAN_VALUE_IMAGE,
         null,
         null));
  }

  /**
   * This adds a property descriptor for the Final feature.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected void addFinalPropertyDescriptor(Object object)
  {
    itemPropertyDescriptors.add
      (createItemPropertyDescriptor
        (((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
         getResourceLocator(),
         getString("_UI_JClass_final_feature"),
         getString("_UI_PropertyDescriptor_description", "_UI_JClass_final_feature", "_UI_JClass_type"),
         JavaPackage.Literals.JCLASS__FINAL,
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
   * @generated
   */
  protected void addInterfacePropertyDescriptor(Object object)
  {
    itemPropertyDescriptors.add
      (createItemPropertyDescriptor
        (((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
         getResourceLocator(),
         getString("_UI_JClass_interface_feature"),
         getString("_UI_PropertyDescriptor_description", "_UI_JClass_interface_feature", "_UI_JClass_type"),
         JavaPackage.Literals.JCLASS__INTERFACE,
         true,
         false,
         false,
         ItemPropertyDescriptor.BOOLEAN_VALUE_IMAGE,
         null,
         null));
  }

  /**
   * This adds a property descriptor for the Throwable feature.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected void addThrowablePropertyDescriptor(Object object)
  {
    itemPropertyDescriptors.add
      (createItemPropertyDescriptor
        (((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
         getResourceLocator(),
         getString("_UI_JClass_throwable_feature"),
         getString("_UI_PropertyDescriptor_description", "_UI_JClass_throwable_feature", "_UI_JClass_type"),
         JavaPackage.Literals.JCLASS__THROWABLE,
         true,
         false,
         false,
         ItemPropertyDescriptor.BOOLEAN_VALUE_IMAGE,
         null,
         null));
  }

  /**
   * This adds a property descriptor for the Java Class feature.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected void addJavaClassPropertyDescriptor(Object object)
  {
    itemPropertyDescriptors.add
      (createItemPropertyDescriptor
        (((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
         getResourceLocator(),
         getString("_UI_JClass_javaClass_feature"),
         getString("_UI_PropertyDescriptor_description", "_UI_JClass_javaClass_feature", "_UI_JClass_type"),
         JavaPackage.Literals.JCLASS__JAVA_CLASS,
         true,
         false,
         false,
         ItemPropertyDescriptor.GENERIC_VALUE_IMAGE,
         null,
         null));
  }

  /**
   * This adds a property descriptor for the Fields feature.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected void addFieldsPropertyDescriptor(Object object)
  {
    itemPropertyDescriptors.add
      (createItemPropertyDescriptor
        (((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
         getResourceLocator(),
         getString("_UI_JClass_fields_feature"),
         getString("_UI_PropertyDescriptor_description", "_UI_JClass_fields_feature", "_UI_JClass_type"),
         JavaPackage.Literals.JCLASS__FIELDS,
         true,
         false,
         false,
         null,
         null,
         null));
  }

  /**
   * This adds a property descriptor for the Methods feature.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected void addMethodsPropertyDescriptor(Object object)
  {
    itemPropertyDescriptors.add
      (createItemPropertyDescriptor
        (((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
         getResourceLocator(),
         getString("_UI_JClass_methods_feature"),
         getString("_UI_PropertyDescriptor_description", "_UI_JClass_methods_feature", "_UI_JClass_type"),
         JavaPackage.Literals.JCLASS__METHODS,
         true,
         false,
         false,
         null,
         null,
         null));
  }

  /**
   * This adds a property descriptor for the Super Types feature.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected void addSuperTypesPropertyDescriptor(Object object)
  {
    itemPropertyDescriptors.add
      (createItemPropertyDescriptor
        (((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
         getResourceLocator(),
         getString("_UI_JClass_superTypes_feature"),
         getString("_UI_PropertyDescriptor_description", "_UI_JClass_superTypes_feature", "_UI_JClass_type"),
         JavaPackage.Literals.JCLASS__SUPER_TYPES,
         true,
         false,
         false,
         null,
         null,
         null));
  }

  /**
   * This adds a property descriptor for the All Super Types feature.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected void addAllSuperTypesPropertyDescriptor(Object object)
  {
    itemPropertyDescriptors.add
      (createItemPropertyDescriptor
        (((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
         getResourceLocator(),
         getString("_UI_JClass_allSuperTypes_feature"),
         getString("_UI_PropertyDescriptor_description", "_UI_JClass_allSuperTypes_feature", "_UI_JClass_type"),
         JavaPackage.Literals.JCLASS__ALL_SUPER_TYPES,
         true,
         false,
         false,
         null,
         null,
         null));
  }

  /**
   * This adds a property descriptor for the Members feature.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected void addMembersPropertyDescriptor(Object object)
  {
    itemPropertyDescriptors.add
      (createItemPropertyDescriptor
        (((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
         getResourceLocator(),
         getString("_UI_JClass_members_feature"),
         getString("_UI_PropertyDescriptor_description", "_UI_JClass_members_feature", "_UI_JClass_type"),
         JavaPackage.Literals.JCLASS__MEMBERS,
         true,
         false,
         false,
         null,
         null,
         null));
  }

  /**
   * This adds a property descriptor for the Component Type feature.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected void addComponentTypePropertyDescriptor(Object object)
  {
    itemPropertyDescriptors.add
      (createItemPropertyDescriptor
        (((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
         getResourceLocator(),
         getString("_UI_JClass_componentType_feature"),
         getString("_UI_PropertyDescriptor_description", "_UI_JClass_componentType_feature", "_UI_JClass_type"),
         JavaPackage.Literals.JCLASS__COMPONENT_TYPE,
         true,
         false,
         false,
         null,
         null,
         null));
  }

  /**
   * This adds a property descriptor for the Unit feature.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected void addUnitPropertyDescriptor(Object object)
  {
    itemPropertyDescriptors.add
      (createItemPropertyDescriptor
        (((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
         getResourceLocator(),
         getString("_UI_JClass_unit_feature"),
         getString("_UI_PropertyDescriptor_description", "_UI_JClass_unit_feature", "_UI_JClass_type"),
         JavaPackage.Literals.JCLASS__UNIT,
         true,
         false,
         false,
         null,
         null,
         null));
  }

  /**
   * This adds a property descriptor for the All Methods feature.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected void addAllMethodsPropertyDescriptor(Object object)
  {
    itemPropertyDescriptors.add
      (createItemPropertyDescriptor
        (((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
         getResourceLocator(),
         getString("_UI_JClass_allMethods_feature"),
         getString("_UI_PropertyDescriptor_description", "_UI_JClass_allMethods_feature", "_UI_JClass_type"),
         JavaPackage.Literals.JCLASS__ALL_METHODS,
         true,
         false,
         false,
         null,
         null,
         null));
  }

  /**
   * This adds a property descriptor for the All Fields feature.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected void addAllFieldsPropertyDescriptor(Object object)
  {
    itemPropertyDescriptors.add
      (createItemPropertyDescriptor
        (((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
         getResourceLocator(),
         getString("_UI_JClass_allFields_feature"),
         getString("_UI_PropertyDescriptor_description", "_UI_JClass_allFields_feature", "_UI_JClass_type"),
         JavaPackage.Literals.JCLASS__ALL_FIELDS,
         true,
         false,
         false,
         null,
         null,
         null));
  }

  /**
   * This adds a property descriptor for the Package feature.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected void addPackagePropertyDescriptor(Object object)
  {
    itemPropertyDescriptors.add
      (createItemPropertyDescriptor
        (((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
         getResourceLocator(),
         getString("_UI_JClass_package_feature"),
         getString("_UI_PropertyDescriptor_description", "_UI_JClass_package_feature", "_UI_JClass_type"),
         JavaPackage.Literals.JCLASS__PACKAGE,
         true,
         false,
         false,
         null,
         null,
         null));
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
      childrenFeatures.add(JavaPackage.Literals.JCLASS__MEMBERS);
      childrenFeatures.add(JavaPackage.Literals.JCLASS__ARRAY_TYPE);
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
   * This returns JClass.gif.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated EATM
   */
  @Override
  public Object getImage(Object object)
  {
    JClass jClass = (JClass)object;
    return 
      getResourceLocator().getImage
        ("full/obj16/" + (jClass.isInterface() ? "JInterface" : "JClass") + getVisibility(jClass));
  }

  /**
   * This returns the label text for the adapted class.
   * @generated EATM
   */
  @Override
  public String getText(Object object)
  {
    JClass jClass = (JClass)object;
    StringBuffer result = new StringBuffer();
    if (jClass.isInterface())
    {
      result.append("interface ");
    }
    else
    {
      result.append("class ");
    }
    result.append(jClass.getName());

    if (jClass.getComponentType() == null)
    {
      Iterator<JClass> superTypes = jClass.getSuperTypes().iterator();
      if (superTypes.hasNext())
      {
        JClass superType = superTypes.next();
        if (!superType.isInterface())
        {
          result.append(" extends ");
          result.append(superType.getName());
          if (superTypes.hasNext())
          {
            result.append(" implements ");
          }
        }
        else
        {
          result.append(" extends ");
          result.append(superType.getName());
          if (superTypes.hasNext())
          {
            result.append(", ");
          }
        }
        while (superTypes.hasNext())
        {
          superType = superTypes.next();
          result.append(superType.getName());
          if (superTypes.hasNext())
          {
            result.append(", ");
          }
        }
      }
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

    switch (notification.getFeatureID(JClass.class))
    {
      case JavaPackage.JCLASS__ABSTRACT:
      case JavaPackage.JCLASS__FINAL:
      case JavaPackage.JCLASS__INTERFACE:
      case JavaPackage.JCLASS__THROWABLE:
      case JavaPackage.JCLASS__JAVA_CLASS:
      case JavaPackage.JCLASS__COMPONENT_TYPE:
      case JavaPackage.JCLASS__PACKAGE:
        fireNotifyChanged(new ViewerNotification(notification, notification.getNotifier(), false, true));
        return;
      case JavaPackage.JCLASS__MEMBERS:
      case JavaPackage.JCLASS__ARRAY_TYPE:
        fireNotifyChanged(new ViewerNotification(notification, notification.getNotifier(), true, false));
        return;
    }
    super.notifyChanged(notification);
  }

}
