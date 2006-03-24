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
 * $Id: EReferenceItemProvider.java,v 1.11 2006/03/24 18:06:15 davidms Exp $
 */
package org.eclipse.emf.ecore.provider;


import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.command.CompoundCommand;
import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.util.ResourceLocator;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EcorePackage;
import org.eclipse.emf.edit.command.InitializeCopyCommand;
import org.eclipse.emf.edit.command.SetCommand;
import org.eclipse.emf.edit.command.CopyCommand.Helper;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.emf.edit.provider.ComposeableAdapterFactory;
import org.eclipse.emf.edit.provider.IEditingDomainItemProvider;
import org.eclipse.emf.edit.provider.IItemLabelProvider;
import org.eclipse.emf.edit.provider.IItemPropertySource;
import org.eclipse.emf.edit.provider.IStructuredItemContentProvider;
import org.eclipse.emf.edit.provider.ITreeItemContentProvider;
import org.eclipse.emf.edit.provider.ItemPropertyDescriptor;


import org.eclipse.emf.edit.provider.ViewerNotification;

/**
 * This is the item provider adapter for a {@link org.eclipse.emf.ecore.EReference} object.
 * <!-- begin-user-doc -->
 * <!-- end-user-doc -->
 * @generated
 */
public class EReferenceItemProvider
  extends EStructuralFeatureItemProvider
  implements
    IEditingDomainItemProvider, IStructuredItemContentProvider, ITreeItemContentProvider, IItemLabelProvider, IItemPropertySource
{
  /**
   * This constructs an instance from a factory and a notifier.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EReferenceItemProvider(AdapterFactory adapterFactory)
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

      addContainmentPropertyDescriptor(object);
      addContainerPropertyDescriptor(object);
      addResolveProxiesPropertyDescriptor(object);
      addEOppositePropertyDescriptor(object);
      addEReferenceTypePropertyDescriptor(object);
    }
    return itemPropertyDescriptors;
  }

  /**
   * This adds a property descriptor for the Containment feature.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected void addContainmentPropertyDescriptor(Object object)
  {
    itemPropertyDescriptors.add
      (createItemPropertyDescriptor
        (((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
         getResourceLocator(),
         getString("_UI_EReference_containment_feature"),
         getString("_UI_PropertyDescriptor_description", "_UI_EReference_containment_feature", "_UI_EReference_type"),
         EcorePackage.Literals.EREFERENCE__CONTAINMENT,
         true,
         ItemPropertyDescriptor.BOOLEAN_VALUE_IMAGE,
         null,
         null));
  }

  /**
   * This adds a property descriptor for the Container feature.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected void addContainerPropertyDescriptor(Object object)
  {
    itemPropertyDescriptors.add
      (createItemPropertyDescriptor
        (((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
         getResourceLocator(),
         getString("_UI_EReference_container_feature"),
         getString("_UI_PropertyDescriptor_description", "_UI_EReference_container_feature", "_UI_EReference_type"),
         EcorePackage.Literals.EREFERENCE__CONTAINER,
         false,
         ItemPropertyDescriptor.BOOLEAN_VALUE_IMAGE,
         null,
         null));
  }

  /**
   * This adds a property descriptor for the Resolve Proxies feature.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected void addResolveProxiesPropertyDescriptor(Object object)
  {
    itemPropertyDescriptors.add
      (createItemPropertyDescriptor
        (((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
         getResourceLocator(),
         getString("_UI_EReference_resolveProxies_feature"),
         getString("_UI_PropertyDescriptor_description", "_UI_EReference_resolveProxies_feature", "_UI_EReference_type"),
         EcorePackage.Literals.EREFERENCE__RESOLVE_PROXIES,
         true,
         ItemPropertyDescriptor.BOOLEAN_VALUE_IMAGE,
         null,
         null));
  }

  /**
   * This adds a property descriptor for the EOpposite feature.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated NOT
   */
  protected void addEOppositePropertyDescriptor(Object object)
  {
    itemPropertyDescriptors.add
      (new ItemPropertyDescriptor
        (((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
         getResourceLocator(),
         getString("_UI_EReference_eOpposite_feature"),
         getString("_UI_PropertyDescriptor_description", "_UI_EReference_eOpposite_feature", "_UI_EReference_type"),
         EcorePackage.eINSTANCE.getEReference_EOpposite(),
         true)
        {
          public Collection getChoiceOfValues(Object object)
          {
            EReference eReference = (EReference)object;
            EClass eContainingClass = eReference.getEContainingClass();
            EClass eReferenceType = eReference.getEReferenceType();
            if (eContainingClass == null || eReferenceType == null)
            {
              return Collections.EMPTY_LIST;
            }
            Collection result = new ArrayList(super.getChoiceOfValues(object));
            for (Iterator i = result.iterator(); i.hasNext(); )
            {
              EReference eOpposite = (EReference)i.next();
              if (eOpposite != null)
              {
                if (eOpposite == eReference)
                {
                  i.remove();
                }
                else
                {
                  EClass eOppositeContainingClass = eOpposite.getEContainingClass();
                  EClass eOppositeReferenceType = eOpposite.getEReferenceType();
                  if (eOppositeContainingClass == null || 
                        !eOppositeContainingClass.isSuperTypeOf(eReferenceType)  || 
                        !eContainingClass.isSuperTypeOf(eOppositeReferenceType))
                  {
                    i.remove();
                  }
                }
              }
            }
            return result;
          }
          
          public void setPropertyValue(Object object, Object value)
          {
            EReference eReference = (EReference)object;
            EReference eOpposite = (EReference)value;
            EditingDomain editingDomain = getEditingDomain(eReference);
            if (editingDomain == null)
            {
              EReference oldReferenceOpposite = eReference.getEOpposite();
              if (oldReferenceOpposite != null)
              {
                oldReferenceOpposite.setEOpposite(null);
              }
              if (eOpposite != null)
              {
                EReference oldOppositeOpposite = eOpposite.getEOpposite();
                if (oldOppositeOpposite != null)
                {
                  oldOppositeOpposite.setEOpposite(null);
                }
                eOpposite.setEOpposite(eReference);
              }
              eReference.setEOpposite(eOpposite);
            }
            else
            {
              CompoundCommand compoundCommand = new CompoundCommand(CompoundCommand.LAST_COMMAND_ALL);
              EReference oldReferenceOpposite = eReference.getEOpposite();
              if (oldReferenceOpposite != null)
              {
                compoundCommand.append(SetCommand.create(editingDomain, getCommandOwner(oldReferenceOpposite), feature, null));
              }
              if (eOpposite != null)
              {
                EReference oldOppositeOpposite = eOpposite.getEOpposite();
                if (oldOppositeOpposite != null)
                {
                  compoundCommand.append(SetCommand.create(editingDomain, getCommandOwner(oldOppositeOpposite), feature, null));
                }
                compoundCommand.append(SetCommand.create(editingDomain, getCommandOwner(eOpposite), feature, eReference));
              }
              compoundCommand.append(SetCommand.create(editingDomain, getCommandOwner(eReference), feature, eOpposite));
              editingDomain.getCommandStack().execute(compoundCommand);
            }
          }
        });
  }

  /**
   * This adds a property descriptor for the EReference Type feature.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected void addEReferenceTypePropertyDescriptor(Object object)
  {
    itemPropertyDescriptors.add
      (createItemPropertyDescriptor
        (((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
         getResourceLocator(),
         getString("_UI_EReference_eReferenceType_feature"),
         getString("_UI_PropertyDescriptor_description", "_UI_EReference_eReferenceType_feature", "_UI_EReference_type"),
         EcorePackage.Literals.EREFERENCE__EREFERENCE_TYPE,
         false,
         null,
         null,
         null));
  }

  /**
   * This returns EReference.gif.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated NOT
   */
  public Object getImage(Object object)
  {
    return overlayImage(object, getComposedImage(object, getResourceLocator().getImage("full/obj16/EReference")));
  }

  /**
   * This returns the label text for the adapted class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated NOT
   */
  public String getText(Object object)
  {
    EReference eReference = (EReference)object;
    StringBuffer result = new StringBuffer();
    result.append(eReference.getName());
    if (eReference.getEType() != null)
    {
      result.append(" : ");
      result.append(eReference.getEType().getName());
    }
    return result.toString();
  }

  /**
   * This creates a custom initialize copy command that specially handles the eOpposite like it is
   * bidirectional, itself.
   */
  protected Command createInitializeCopyCommand(EditingDomain domain, EObject owner, Helper helper)
  {
    return new InitializeCopyCommand(domain, owner, helper)
    {
      // Don't use the normal reference copying for eOpposite.
      //
      protected Collection getReferencesToCopy()
      {
        List result = new ArrayList(super.getReferencesToCopy());
        result.remove(EcorePackage.Literals.EREFERENCE__EOPPOSITE);
        return result;
      }

      // Handle eOpposite specially.
      //
      protected void copyReferences()
      {
        super.copyReferences();

        EReference reference = EcorePackage.Literals.EREFERENCE__EOPPOSITE;
        EObject value = (EObject)owner.eGet(reference);
        if (value != null)
        {
          EObject target = copyHelper.getCopyTarget(value, true);
          if (target != null)
          {
            copy.eSet(reference, target);
          }
        }
      }
    };
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

    switch (notification.getFeatureID(EReference.class))
    {
      case EcorePackage.EREFERENCE__CONTAINMENT:
      case EcorePackage.EREFERENCE__CONTAINER:
      case EcorePackage.EREFERENCE__RESOLVE_PROXIES:
      case EcorePackage.EREFERENCE__EOPPOSITE:
      case EcorePackage.EREFERENCE__EREFERENCE_TYPE:
        fireNotifyChanged(new ViewerNotification(notification, notification.getNotifier(), false, true));
        return;
    }
    super.notifyChanged(notification);
  }

  /**
   * This adds to the collection of {@link org.eclipse.emf.edit.command.CommandParameter}s
   * describing all of the children that can be created under this object.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected void collectNewChildDescriptors(Collection newChildDescriptors, Object object)
  {
    super.collectNewChildDescriptors(newChildDescriptors, object);
  }

  /**
   * Return the resource locator for this item provider's resources.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public ResourceLocator getResourceLocator()
  {
    return EcoreEditPlugin.INSTANCE;
  }

}
