/**
 * <copyright>
 *
 * Copyright (c) 2002-2004 IBM Corporation and others.
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
 * $Id: JMethodItemProvider.java,v 1.5 2005/06/13 14:26:57 emerks Exp $
 */
package org.eclipse.emf.java.provider;


import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.util.ResourceLocator;
import org.eclipse.emf.edit.provider.ComposeableAdapterFactory;
import org.eclipse.emf.edit.provider.IEditingDomainItemProvider;
import org.eclipse.emf.edit.provider.IItemLabelProvider;
import org.eclipse.emf.edit.provider.IItemPropertySource;
import org.eclipse.emf.edit.provider.IStructuredItemContentProvider;
import org.eclipse.emf.edit.provider.ITreeItemContentProvider;
import org.eclipse.emf.edit.provider.ItemPropertyDescriptor;
import org.eclipse.emf.edit.provider.ViewerNotification;
import org.eclipse.emf.java.JMethod;
import org.eclipse.emf.java.JParameter;
import org.eclipse.emf.java.JavaPackage;


/**
 * This is the item provider adapter for a {@link org.eclipse.emf.java.JMethod} object.
 * <!-- begin-user-doc -->
 * <!-- end-user-doc -->
 * @generated
 */
public class JMethodItemProvider
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
  public JMethodItemProvider(AdapterFactory adapterFactory)
  {
    super(adapterFactory);
  }

  /**
   * This returns the property descriptors for the adapted class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public List getPropertyDescriptors(Object object)
  {
    if (itemPropertyDescriptors == null)
    {
      super.getPropertyDescriptors(object);

      addAbstractPropertyDescriptor(object);
      addFinalPropertyDescriptor(object);
      addNativePropertyDescriptor(object);
      addSynchronizedPropertyDescriptor(object);
      addJavaMethodPropertyDescriptor(object);
      addConstructorPropertyDescriptor(object);
      addJavaConstructorPropertyDescriptor(object);
      addBodyPropertyDescriptor(object);
      addParametersPropertyDescriptor(object);
      addExceptionsPropertyDescriptor(object);
      addReturnTypePropertyDescriptor(object);
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
         getString("_UI_JMethod_abstract_feature"),
         getString("_UI_PropertyDescriptor_description", "_UI_JMethod_abstract_feature", "_UI_JMethod_type"),
         JavaPackage.eINSTANCE.getJMethod_Abstract(),
         true,
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
         getString("_UI_JMethod_final_feature"),
         getString("_UI_PropertyDescriptor_description", "_UI_JMethod_final_feature", "_UI_JMethod_type"),
         JavaPackage.eINSTANCE.getJMethod_Final(),
         true,
         ItemPropertyDescriptor.BOOLEAN_VALUE_IMAGE,
         null,
         null));
  }

  /**
   * This adds a property descriptor for the Native feature.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected void addNativePropertyDescriptor(Object object)
  {
    itemPropertyDescriptors.add
      (createItemPropertyDescriptor
        (((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
         getResourceLocator(),
         getString("_UI_JMethod_native_feature"),
         getString("_UI_PropertyDescriptor_description", "_UI_JMethod_native_feature", "_UI_JMethod_type"),
         JavaPackage.eINSTANCE.getJMethod_Native(),
         true,
         ItemPropertyDescriptor.BOOLEAN_VALUE_IMAGE,
         null,
         null));
  }

  /**
   * This adds a property descriptor for the Synchronized feature.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected void addSynchronizedPropertyDescriptor(Object object)
  {
    itemPropertyDescriptors.add
      (createItemPropertyDescriptor
        (((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
         getResourceLocator(),
         getString("_UI_JMethod_synchronized_feature"),
         getString("_UI_PropertyDescriptor_description", "_UI_JMethod_synchronized_feature", "_UI_JMethod_type"),
         JavaPackage.eINSTANCE.getJMethod_Synchronized(),
         true,
         ItemPropertyDescriptor.BOOLEAN_VALUE_IMAGE,
         null,
         null));
  }

  /**
   * This adds a property descriptor for the Java Method feature.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected void addJavaMethodPropertyDescriptor(Object object)
  {
    itemPropertyDescriptors.add
      (createItemPropertyDescriptor
        (((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
         getResourceLocator(),
         getString("_UI_JMethod_javaMethod_feature"),
         getString("_UI_PropertyDescriptor_description", "_UI_JMethod_javaMethod_feature", "_UI_JMethod_type"),
         JavaPackage.eINSTANCE.getJMethod_JavaMethod(),
         true,
         ItemPropertyDescriptor.GENERIC_VALUE_IMAGE,
         null,
         null));
  }

  /**
   * This adds a property descriptor for the Constructor feature.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected void addConstructorPropertyDescriptor(Object object)
  {
    itemPropertyDescriptors.add
      (createItemPropertyDescriptor
        (((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
         getResourceLocator(),
         getString("_UI_JMethod_constructor_feature"),
         getString("_UI_PropertyDescriptor_description", "_UI_JMethod_constructor_feature", "_UI_JMethod_type"),
         JavaPackage.eINSTANCE.getJMethod_Constructor(),
         true,
         ItemPropertyDescriptor.BOOLEAN_VALUE_IMAGE,
         null,
         null));
  }

  /**
   * This adds a property descriptor for the Java Constructor feature.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected void addJavaConstructorPropertyDescriptor(Object object)
  {
    itemPropertyDescriptors.add
      (createItemPropertyDescriptor
        (((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
         getResourceLocator(),
         getString("_UI_JMethod_javaConstructor_feature"),
         getString("_UI_PropertyDescriptor_description", "_UI_JMethod_javaConstructor_feature", "_UI_JMethod_type"),
         JavaPackage.eINSTANCE.getJMethod_JavaConstructor(),
         true,
         ItemPropertyDescriptor.GENERIC_VALUE_IMAGE,
         null,
         null));
  }

  /**
   * This adds a property descriptor for the Body feature.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected void addBodyPropertyDescriptor(Object object)
  {
    itemPropertyDescriptors.add
      (createItemPropertyDescriptor
        (((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
         getResourceLocator(),
         getString("_UI_JMethod_body_feature"),
         getString("_UI_PropertyDescriptor_description", "_UI_JMethod_body_feature", "_UI_JMethod_type"),
         JavaPackage.eINSTANCE.getJMethod_Body(),
         true,
         ItemPropertyDescriptor.GENERIC_VALUE_IMAGE,
         null,
         null));
  }

  /**
   * This adds a property descriptor for the Parameters feature.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected void addParametersPropertyDescriptor(Object object)
  {
    itemPropertyDescriptors.add
      (createItemPropertyDescriptor
        (((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
         getResourceLocator(),
         getString("_UI_JMethod_parameters_feature"),
         getString("_UI_PropertyDescriptor_description", "_UI_JMethod_parameters_feature", "_UI_JMethod_type"),
         JavaPackage.eINSTANCE.getJMethod_Parameters(),
         true,
         null,
         null,
         null));
  }

  /**
   * This adds a property descriptor for the Exceptions feature.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected void addExceptionsPropertyDescriptor(Object object)
  {
    itemPropertyDescriptors.add
      (createItemPropertyDescriptor
        (((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
         getResourceLocator(),
         getString("_UI_JMethod_exceptions_feature"),
         getString("_UI_PropertyDescriptor_description", "_UI_JMethod_exceptions_feature", "_UI_JMethod_type"),
         JavaPackage.eINSTANCE.getJMethod_Exceptions(),
         true,
         null,
         null,
         null));
  }

  /**
   * This adds a property descriptor for the Return Type feature.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected void addReturnTypePropertyDescriptor(Object object)
  {
    itemPropertyDescriptors.add
      (createItemPropertyDescriptor
        (((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
         getResourceLocator(),
         getString("_UI_JMethod_returnType_feature"),
         getString("_UI_PropertyDescriptor_description", "_UI_JMethod_returnType_feature", "_UI_JMethod_type"),
         JavaPackage.eINSTANCE.getJMethod_ReturnType(),
         true,
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
  public Collection getChildrenFeatures(Object object)
  {
    if (childrenFeatures == null)
    {
      super.getChildrenFeatures(object);
      childrenFeatures.add(JavaPackage.eINSTANCE.getJMethod_Parameters());
    }
    return childrenFeatures;
  }

  /**
   * This returns JMethod.gif.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated EATM
   */
  public Object getImage(Object object)
  {
    JMethod jMethod = (JMethod)object;
    return getResourceLocator().getImage("full/obj16/JMethod" + getVisibility(jMethod));
  }

  /**
   * This returns the label text for the adapted class.
   * @generated EATM
   */
  public String getText(Object object)
  {
    JMethod jMethod = (JMethod)object;
    StringBuffer result = new StringBuffer();
    result.append(jMethod.getName());
    result.append('('); // )
    for (Iterator i = jMethod.getParameters().iterator(); i.hasNext(); )
    {
      JParameter jParameter = (JParameter)i.next();
      if (jParameter.getType() == null)
      {
        result.append('-');
      }
      else
      {
        result.append(jParameter.getType().getName());
      }
      if (i.hasNext())
      {
        result.append(',');
      }
    }
    // (
    result.append(")"); 
    if (!jMethod.isConstructor() && jMethod.getReturnType() != null)
    {
      result.append(" : ");
      result.append(jMethod.getReturnType().getName());
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
  public void notifyChanged(Notification notification)
  {
    updateChildren(notification);

    switch (notification.getFeatureID(JMethod.class))
    {
      case JavaPackage.JMETHOD__ABSTRACT:
      case JavaPackage.JMETHOD__FINAL:
      case JavaPackage.JMETHOD__NATIVE:
      case JavaPackage.JMETHOD__SYNCHRONIZED:
      case JavaPackage.JMETHOD__JAVA_METHOD:
      case JavaPackage.JMETHOD__CONSTRUCTOR:
      case JavaPackage.JMETHOD__JAVA_CONSTRUCTOR:
      case JavaPackage.JMETHOD__BODY:
      case JavaPackage.JMETHOD__RETURN_TYPE:
        fireNotifyChanged(new ViewerNotification(notification, notification.getNotifier(), false, true));
        return;
      case JavaPackage.JMETHOD__PARAMETERS:
        fireNotifyChanged(new ViewerNotification(notification, notification.getNotifier(), true, false));
        return;
    }
    super.notifyChanged(notification);
  }

  /**
   * Return the resource locator for this item provider's resources.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public ResourceLocator getResourceLocator()
  {
    return JavaEditPlugin.INSTANCE;
  }

}
