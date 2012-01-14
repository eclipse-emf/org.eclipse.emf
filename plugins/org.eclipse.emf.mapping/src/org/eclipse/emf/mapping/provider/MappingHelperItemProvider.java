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
package org.eclipse.emf.mapping.provider;


import java.util.Collection;
import java.util.List;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.util.ResourceLocator;
import org.eclipse.emf.ecore.EStructuralFeature;
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
  @Override
  public List<IItemPropertyDescriptor> getPropertyDescriptors(Object object)
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
        for (final MappingHelper childHelper : mappingHelper.getNested())
        {

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
               @Override
               public Object getPropertyValue(Object o)
               {
                 return createPropertyValueWrapper(childHelper, childHelper.getHelpedObject());
               }
               @Override
               public Collection<?> getChoiceOfValues(Object object)
               {
                 return null;
               }
             });
          ++count;
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
  @Override
  public Collection<? extends EStructuralFeature> getChildrenFeatures(Object object)
  {
    return super.getChildrenFeatures(object);
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
  public Collection<?> getChildren(Object object)
  {
    return ((MappingHelper)object).getNested();
  }

  @Override
  public boolean hasChildren(Object object)
  {
    return !((MappingHelper)object).getNested().isEmpty();
  }

  /**
   * This returns the mapper of the MappingHelper.
   */
  @Override
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
  @Override
  public Object getImage(Object object)
  {
    return MappingPlugin.getPlugin().getImage("full/obj16/MappingHelper");
  }

  @Override
  public String getText(Object object)
  {
    return "MappingHelper";
  }

  /**
   * This handles notification by delegating to {@link #fireNotifyChanged fireNotifyChanged}.
   */
  @Override
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
  @Override
  public ResourceLocator getResourceLocator()
  {
    return MappingPlugin.INSTANCE;
  }

}
