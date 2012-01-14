/**
 * Copyright (c) 2002-2007 IBM Corporation and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: 
 *   IBM - Initial API and implementation
 */
package org.eclipse.xsd.provider;


import java.util.Collection;
import java.util.List;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.edit.provider.ComposeableAdapterFactory;
import org.eclipse.emf.edit.provider.IEditingDomainItemProvider;
import org.eclipse.emf.edit.provider.IItemLabelProvider;
import org.eclipse.emf.edit.provider.IItemPropertyDescriptor;
import org.eclipse.emf.edit.provider.IItemPropertySource;
import org.eclipse.emf.edit.provider.IStructuredItemContentProvider;
import org.eclipse.emf.edit.provider.ITreeItemContentProvider;
import org.eclipse.emf.edit.provider.ItemPropertyDescriptor;
import org.eclipse.emf.edit.provider.ItemProviderAdapter;

import org.eclipse.xsd.XSDCompositor;
import org.eclipse.xsd.XSDConcreteComponent;
import org.eclipse.xsd.XSDElementDeclaration;
import org.eclipse.xsd.XSDModelGroup;
import org.eclipse.xsd.XSDParticle;


/**
 * This is the item provider adpater for a {@link org.eclipse.xsd.XSDModelGroup} object.
 */
public class XSDModelGroupItemProvider
  extends XSDTermItemProvider
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
  public XSDModelGroupItemProvider(AdapterFactory adapterFactory)
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

      // This is for the compositor feature.
      //
      itemPropertyDescriptors.add
        (new ItemPropertyDescriptor
          (((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
           XSDEditPlugin.INSTANCE.getString("_UI_Compositor_label"),
           XSDEditPlugin.INSTANCE.getString("_UI_Compositor_description"),
           xsdPackage.getXSDModelGroup_Compositor(),
           true,
           ItemPropertyDescriptor.TEXT_VALUE_IMAGE));

      // This is for the annotation feature.
      //
      itemPropertyDescriptors.add
        (new ItemPropertyDescriptor
          (((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
           XSDEditPlugin.INSTANCE.getString("_UI_Annotation_label"),
           XSDEditPlugin.INSTANCE.getString("_UI_AnnotationOfModelGroup_description"),
           xsdPackage.getXSDModelGroup_Annotation(), 
           false));

    }
    return itemPropertyDescriptors;
  }

  /**
   * This specifies how to implement {@link #getChildren} and is used to deduce an appropriate feature for an
   * {@link org.eclipse.emf.edit.command.AddCommand}, {@link org.eclipse.emf.edit.command.RemoveCommand} or
   * {@link org.eclipse.emf.edit.command.MoveCommand} in {@link #createCommand}.
   */
  @Override
  public Collection<? extends EStructuralFeature> getChildrenFeatures(Object object)
  {
    if (childrenFeatures == null)
    {
      super.getChildrenFeatures(object);
      childrenFeatures.add(xsdPackage.getXSDModelGroup_Annotation());
      childrenFeatures.add(xsdPackage.getXSDModelGroup_Contents());
    }
    return childrenFeatures;
  }

  /**
   * This returns XSDModelGroup.gif.
   */
  @Override
  public Object getImage(Object object)
  {
    XSDModelGroup xsdModelGroup = ((XSDModelGroup)object);
    if (XSDCompositor.CHOICE_LITERAL == xsdModelGroup.getCompositor())
    {
      return XSDEditPlugin.INSTANCE.getImage("full/obj16/XSDModelGroupChoice");
    }
    else if (XSDCompositor.ALL_LITERAL == xsdModelGroup.getCompositor())
    {
      return XSDEditPlugin.INSTANCE.getImage("full/obj16/XSDModelGroupAll");
    }
    else 
    {
      return XSDEditPlugin.INSTANCE.getImage("full/obj16/XSDModelGroupSequence");
    }
  }

  @Override
  public String getText(Object object)
  {
    XSDModelGroup xsdModelGroup = ((XSDModelGroup)object);
    return xsdModelGroup.getCompositor().getName();
  }

  /**
   * This handles notification by calling {@link #fireNotifyChanged fireNotifyChanged}.
   */
  @Override
  public void notifyChanged(Notification msg) 
  {
    XSDModelGroup xsdModelGroup = (XSDModelGroup)msg.getNotifier();
    if (msg.getFeature() == xsdPackage.getXSDModelGroup_Compositor() || 
          msg.getFeature() == xsdPackage.getXSDModelGroup_Contents() || 
          msg.getFeature() == xsdPackage.getXSDModelGroup_Annotation())
    {
      fireNotifyChanged(msg);

      XSDConcreteComponent container = xsdModelGroup.getContainer();
      if (container instanceof XSDParticle)
      {
        ((ItemProviderAdapter)adapterFactory.adapt(container, IItemLabelProvider.class)).fireNotifyChanged
          (new ENotificationImpl
            ((InternalEObject)container, 
             msg.getEventType(), 
             (EStructuralFeature)msg.getFeature(), 
             msg.getOldValue(), 
             msg.getNewValue(), 
             msg.getPosition()));
      }
      return;
    }
    super.notifyChanged(msg);
  }

  /**
   * This adds {@link org.eclipse.emf.edit.command.CommandParameter}s describing the children
   * that can be created under this object.
   */
  @Override
  protected void collectNewChildDescriptors(Collection<Object> newChildDescriptors, Object object)
  {
    super.collectNewChildDescriptors(newChildDescriptors, object);
    XSDModelGroup mg = (XSDModelGroup) object;

    // annotation
    newChildDescriptors.add(createChildParameter(xsdPackage.getXSDModelGroup_Annotation(), xsdFactory.createXSDAnnotation()));

    // element declaration under particle
    XSDElementDeclaration ed = createElementDeclaration(mg);
    newChildDescriptors.add(createChildParameter(xsdPackage.getXSDModelGroup_Contents(), createParticle(ed, false)));

    // element declaration reference under particle
    newChildDescriptors.add(createChildParameter(xsdPackage.getXSDModelGroup_Contents(), createParticle(mg.resolveElementDeclaration(""), true)));

    // for choice and sequence model groups only...
    if (mg.getCompositor() != XSDCompositor.ALL_LITERAL)
    {
      // choice and sequence model groups under particles
      addModelGroupChildParameters(newChildDescriptors, xsdPackage.getXSDModelGroup_Contents(), false, true);

      // model group definition reference under particle
      newChildDescriptors.add(createChildParameter(xsdPackage.getXSDModelGroup_Contents(), createParticle(mg.resolveModelGroupDefinition(""), true)));

      // element wildcard under particle
      newChildDescriptors.add(createChildParameter(xsdPackage.getXSDModelGroup_Contents(), createParticle(xsdFactory.createXSDWildcard(), false)));
    }
  }
}
