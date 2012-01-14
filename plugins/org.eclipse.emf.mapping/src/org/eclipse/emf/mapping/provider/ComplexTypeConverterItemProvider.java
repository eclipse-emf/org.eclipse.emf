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


import java.util.List;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.edit.provider.IEditingDomainItemProvider;
import org.eclipse.emf.edit.provider.IItemLabelProvider;
import org.eclipse.emf.edit.provider.IItemPropertyDescriptor;
import org.eclipse.emf.edit.provider.IItemPropertySource;
import org.eclipse.emf.edit.provider.IStructuredItemContentProvider;
import org.eclipse.emf.edit.provider.ITreeItemContentProvider;
//import org.eclipse.emf.edit.provider.ViewerNotification;

import org.eclipse.emf.edit.provider.ItemPropertyDescriptor;
import org.eclipse.emf.mapping.MappingPackage;
import org.eclipse.emf.mapping.MappingPlugin;

// import org.eclipse.emf.mapping.ComplexTypeConverter;


/**
 * This is the item provider adpater for a {@link org.eclipse.emf.mapping.ComplexTypeConverter} object.
 */
public class ComplexTypeConverterItemProvider
  extends TypeConverterItemProvider
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
  public ComplexTypeConverterItemProvider(AdapterFactory adapterFactory)
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

      //MappingPackage ePackage = ((ComplexTypeConverter)object).ePackageMapping();
      MappingPackage ePackage = MappingPackage.eINSTANCE;

      // This is for the in2out feature.
      //
      itemPropertyDescriptors.add
        (new ItemPropertyDescriptor
          (adapterFactory,
           MappingPlugin.getPlugin().getString("_UI_In2out_property_label"),
           MappingPlugin.getPlugin().getString("_UI_In2out_property_description"),
           ePackage.getComplexTypeConverter_In2out()));

      // This is for the out2in feature.
      //
      itemPropertyDescriptors.add
        (new ItemPropertyDescriptor
          (adapterFactory,
           MappingPlugin.getPlugin().getString("_UI_Out2in_property_label"),
           MappingPlugin.getPlugin().getString("_UI_Out2in_property_description"),
           ePackage.getComplexTypeConverter_Out2in()));

    }
    return itemPropertyDescriptors;
  }

  /**
   * This returns ComplexTypeConverter.gif.
   */
  @Override
  public Object getImage(Object object)
  {
    return MappingPlugin.getPlugin().getImage("full/obj16/ComplexTypeConverter");
  }

  @Override
  public String getText(Object object)
  {
    return "ComplexTypeConverter";
  }

  /**
   * This handles notification by delegating to {@link #fireNotifyChanged fireNotifyChanged}.
   */
  @Override
  public void notifyChanged(Notification msg) 
  {
    MappingPackage ePackage = MappingPackage.eINSTANCE;
    //MappingPackage ePackage = ((ComplexTypeConverter)msg.getNotifier()).ePackageMapping();
    if (msg.getFeature() == ePackage.getComplexTypeConverter_In2out() || msg.getFeature() == ePackage.getComplexTypeConverter_Out2in())
    {
      this.fireNotifyChanged(msg);
      // HGD::fireNotifyChanged(msg.getNotifier(), msg.getEventType(), msg.getFeature(), msg.getOldValue(), msg.getNewValue(), msg.getPosition());
      return;
    }
    super.notifyChanged(msg);
  }

}
