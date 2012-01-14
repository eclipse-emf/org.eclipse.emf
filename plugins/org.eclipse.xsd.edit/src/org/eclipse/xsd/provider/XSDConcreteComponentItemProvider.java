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
package org.eclipse.xsd.provider;


import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.edit.command.CopyCommand;
import org.eclipse.emf.edit.command.InitializeCopyCommand;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.emf.edit.provider.ComposeableAdapterFactory;
import org.eclipse.emf.edit.provider.IEditingDomainItemProvider;
import org.eclipse.emf.edit.provider.IItemLabelProvider;
import org.eclipse.emf.edit.provider.IItemPropertyDescriptor;
import org.eclipse.emf.edit.provider.IItemPropertySource;
import org.eclipse.emf.edit.provider.IStructuredItemContentProvider;
import org.eclipse.emf.edit.provider.ITreeItemContentProvider;
import org.eclipse.emf.edit.provider.ItemPropertyDescriptor;

import org.eclipse.xsd.XSDConcreteComponent;


/**
 * This is the item provider adpater for a {@link org.eclipse.xsd.XSDConcreteComponent} object.
 */
public class XSDConcreteComponentItemProvider
  extends XSDItemProviderAdapter
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
  public XSDConcreteComponentItemProvider(AdapterFactory adapterFactory)
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

      createElementPropertyDescriptor(object);
    }
    return itemPropertyDescriptors;
  }

  protected void createElementPropertyDescriptor(Object object)
  {
    // This is for the element feature.
    //
    itemPropertyDescriptors.add
      (new ItemPropertyDescriptor
        (((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
         XSDEditPlugin.INSTANCE.getString("_UI_Element_label"),
         XSDEditPlugin.INSTANCE.getString("_UI_Element_description"),
         xsdPackage.getXSDConcreteComponent_Element(),
         false,
         ItemPropertyDescriptor.GENERIC_VALUE_IMAGE)
       {
         @Override
        public Object getPropertyValue(Object o)
         {
           XSDConcreteComponent concreteComponent = ((XSDConcreteComponent)o);
           if (concreteComponent.getElement() == null)
           {
             return "";
           }
           else
           {
             return concreteComponent.getElement().getTagName();
           }
         }
       });
  }

  /**
   * We don't want to copy any references other than containment references.
   */
  @Override
  protected Command createInitializeCopyCommand(EditingDomain domain, EObject owner, CopyCommand.Helper helper)
  {
    return 
      new InitializeCopyCommand(domain, owner, helper)
      {
        @Override
        protected Collection<? extends EAttribute> getAttributesToCopy()
        {
          Collection<EAttribute> result = new ArrayList<EAttribute>(this.owner.eClass().getEAllAttributes());
          result.remove(xsdPackage.getXSDConcreteComponent_Element());
          return result;
        }
      };
  }

  /**
   * This handles notification by calling {@link #fireNotifyChanged fireNotifyChanged}.
   */
  @Override
  public void notifyChanged(Notification msg) 
  {
    if (msg.getFeature() == xsdPackage.getXSDConcreteComponent_Element())
    {
      fireNotifyChanged(msg);
      return;
    }
    super.notifyChanged(msg);
  }
}
