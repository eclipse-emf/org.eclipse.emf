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
 * $Id: MappingHelperItemProvider.java,v 1.6 2005/06/12 13:38:46 emerks Exp $
 */
package org.eclipse.emf.mapping.provider;


import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.util.ResourceLocator;
import org.eclipse.emf.edit.provider.ComposeableAdapterFactory;
import org.eclipse.emf.edit.provider.IEditingDomainItemProvider;
import org.eclipse.emf.edit.provider.IItemLabelProvider;
import org.eclipse.emf.edit.provider.IItemPropertyDescriptor;
import org.eclipse.emf.edit.provider.IItemPropertySource;
import org.eclipse.emf.edit.provider.IStructuredItemContentProvider;
import org.eclipse.emf.edit.provider.ITreeItemContentProvider;
//import org.eclipse.emf.edit.provider.ViewerNotification;

import org.eclipse.emf.edit.provider.ItemPropertyDescriptor;
import org.eclipse.emf.edit.provider.ItemPropertyDescriptorDecorator;
import org.eclipse.emf.mapping.Mapping;
import org.eclipse.emf.mapping.MappingHelper;
import org.eclipse.emf.mapping.MappingPackage;
import org.eclipse.emf.mapping.MappingPlugin;

// import org.eclipse.emf.edit.provider.ItemProviderAdapter;


/**
 * This is the item provider adpater for a {@link org.eclipse.emf.mapping.MappingHelper} object.
 */
public class MappingHelperItemProvider
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
  public MappingHelperItemProvider(AdapterFactory adapterFactory)
  {
    super(adapterFactory);
  }

  /**
   * This returns the property descriptors for the adapted class.
   */
  public List getPropertyDescriptors(Object object)
  {
    if (itemPropertyDescriptors == null)
    {
      super.getPropertyDescriptors(object);

      MappingHelper mappingHelper = (MappingHelper)object;

      Mapping mapping = mappingHelper.getMapper();
      
      MappingPackage ePackage = MappingPackage.eINSTANCE;
      if (!mappingHelper.getNested().isEmpty())
      {
        int count = 1;
        for (Iterator helpers = mappingHelper.getNested().iterator(); helpers.hasNext(); ++count)
        {
          final MappingHelper childHelper = (MappingHelper)helpers.next();

          String additionalLabel = 
            mapping.getInputs().contains(childHelper.getHelpedObject()) ? MappingPlugin.INSTANCE.getString("_UI_Input_label") :
            mapping.getOutputs().contains(childHelper.getHelpedObject()) ? MappingPlugin.INSTANCE.getString("_UI_Output_label") :
            "";

          IItemPropertyDescriptor childHelperItemPropertyDescriptor = 
            (new ItemPropertyDescriptor
               (((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
                count + ". " + additionalLabel + MappingPlugin.INSTANCE.getString("_UI_Helper_label"),
                MappingPlugin.INSTANCE.getString("_UI_Helper_description"), 
                ePackage.getMappingHelper_Nested()));

          itemPropertyDescriptors.add
            (new ItemPropertyDescriptorDecorator(childHelper, childHelperItemPropertyDescriptor)
             {
               protected Object createPropertyValueWrapper(Object object, Object propertyValue)
               {
                 return 
                   new ItemPropertyDescriptor.PropertyValueWrapper
                     (((ComposeableAdapterFactory)getAdapterFactory()).getRootAdapterFactory(), object, propertyValue, object);
               }
               public Object getPropertyValue(Object o)
               {
                 return createPropertyValueWrapper(childHelper, childHelper.getHelpedObject());
               }
               public Collection getChoiceOfValues(Object object)
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
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated NOT
   */
  public Collection getChildrenFeatures(Object object)
  {
    return super.getChildrenFeatures(object);
  }

  public Collection getChildren(Object object)
  {
    return ((MappingHelper)object).getNested();
  }

  public boolean hasChildren(Object object)
  {
    return !((MappingHelper)object).getNested().isEmpty();
  }

  /**
   * This returns the mapper of the MappingHelper.
   */
  public Object getParent(Object object)
  {
    MappingHelper mappingHelper = (MappingHelper)object;
    if (mappingHelper.getNestedIn() != null)
    {
      return mappingHelper.getNestedIn();
    }
    else
    {
      return mappingHelper.getMapper();
    }
  }

  /**
   * This returns MappingHelper.gif.
   */
  public Object getImage(Object object)
  {
    return MappingPlugin.getPlugin().getImage("full/obj16/MappingHelper");
  }

  public String getText(Object object)
  {
    return "MappingHelper";
  }

  /**
   * This handles notification by delegating to {@link #fireNotifyChanged fireNotifyChanged}.
   */
  public void notifyChanged(Notification msg) 
  {
    if (msg.getFeature() == MappingPackage.eINSTANCE.getMappingHelper_Mapper())
    {
      fireNotifyChanged(msg);
      return;
    }
    super.notifyChanged(msg);
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
