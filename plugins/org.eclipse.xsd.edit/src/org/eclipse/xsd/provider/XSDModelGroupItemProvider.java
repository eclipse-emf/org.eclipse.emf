/**
 * <copyright>
 *
 * Copyright (c) 2002-2004 IBM Corporation and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Common Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/cpl-v10.html
 * 
 * Contributors: 
 *   IBM - Initial API and implementation
 *
 * </copyright>
 *
 * $Id: XSDModelGroupItemProvider.java,v 1.2 2004/04/07 22:12:58 davidms Exp $
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
  public List getPropertyDescriptors(Object object)
  {
    if (itemPropertyDescriptors == null)
    {
      super.getPropertyDescriptors(object);

      XSDModelGroup xsdModelGroup = ((XSDModelGroup)object);

      // This is for the compositor feature.
      //
      itemPropertyDescriptors.add
        (new ItemPropertyDescriptor
          (((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
           XSDEditPlugin.getString("_UI_Compositor_label"),
           XSDEditPlugin.getString("_UI_Compositor_description"),
           xsdPackage.getXSDModelGroup_Compositor(),
           true,
           ItemPropertyDescriptor.TEXT_VALUE_IMAGE));

      // This is for the annotation feature.
      //
      itemPropertyDescriptors.add
        (new ItemPropertyDescriptor
          (((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
           XSDEditPlugin.getString("_UI_Annotation_label"),
           XSDEditPlugin.getString("_UI_AnnotationOfModelGroup_description"),
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
  public Collection getChildrenFeatures(Object object)
  {
    if (childrenFeatures == null)
    {
      super.getChildrenFeatures(object);
      XSDModelGroup xsdModelGroup = ((XSDModelGroup)object);
      childrenFeatures.add(xsdPackage.getXSDModelGroup_Annotation());
      childrenFeatures.add(xsdPackage.getXSDModelGroup_Contents());
    }
    return childrenFeatures;
  }

  /**
   * This returns XSDModelGroup.gif.
   */
  public Object getImage(Object object)
  {
    XSDModelGroup xsdModelGroup = ((XSDModelGroup)object);
    if (XSDCompositor.CHOICE_LITERAL == xsdModelGroup.getCompositor())
    {
      return XSDEditPlugin.getImage("full/obj16/XSDModelGroupChoice");
    }
    else if (XSDCompositor.ALL_LITERAL == xsdModelGroup.getCompositor())
    {
      return XSDEditPlugin.getImage("full/obj16/XSDModelGroupAll");
    }
    else 
    {
      return XSDEditPlugin.getImage("full/obj16/XSDModelGroupSequence");
    }
  }

  public String getText(Object object)
  {
    XSDModelGroup xsdModelGroup = ((XSDModelGroup)object);
    return xsdModelGroup.getCompositor().getName();
  }

  /**
   * This handles notification by calling {@link #fireNotifyChanged fireNotifyChanged}.
   */
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
   * This adds to the collection of {@link org.eclipse.emf.edit.command.CommandParameter}s 
   * describing all of the children that can be created under this object.
   */
  protected void collectNewChildDescriptors(Collection newChildDescriptors, Object object)
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
