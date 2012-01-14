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
import org.eclipse.emf.edit.provider.ComposeableAdapterFactory;
import org.eclipse.emf.edit.provider.IEditingDomainItemProvider;
import org.eclipse.emf.edit.provider.IItemLabelProvider;
import org.eclipse.emf.edit.provider.IItemPropertyDescriptor;
import org.eclipse.emf.edit.provider.IItemPropertySource;
import org.eclipse.emf.edit.provider.IStructuredItemContentProvider;
import org.eclipse.emf.edit.provider.ITreeItemContentProvider;
import org.eclipse.emf.edit.provider.ItemPropertyDescriptor;

import org.eclipse.xsd.XSDModelGroupDefinition;


/**
 * This is the item provider adpater for a {@link org.eclipse.xsd.XSDModelGroupDefinition} object.
 */
public class XSDModelGroupDefinitionItemProvider
  extends XSDRedefinableComponentItemProvider
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
  public XSDModelGroupDefinitionItemProvider(AdapterFactory adapterFactory)
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

      // This is for the annotation feature.
      //
      itemPropertyDescriptors.add
        (new ItemPropertyDescriptor
          (((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
           XSDEditPlugin.INSTANCE.getString("_UI_Annotation_label"),
           XSDEditPlugin.INSTANCE.getString("_UI_AnnotationOfModelGroupDefinition_description"),
           xsdPackage.getXSDModelGroupDefinition_Annotation(), 
           false));

      // This is for the resolvedModelGroupDefinition feature.
      //
      itemPropertyDescriptors.add
        (new ItemPropertyDescriptor
          (((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
           XSDEditPlugin.INSTANCE.getString("_UI_ResolvedModelGroupDefinition_label"),
           XSDEditPlugin.INSTANCE.getString("_UI_ResolvedModelGroupDefinition_description"),
           xsdPackage.getXSDModelGroupDefinition_ResolvedModelGroupDefinition(), 
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
      childrenFeatures.add(xsdPackage.getXSDModelGroupDefinition_Annotation());
      childrenFeatures.add(xsdPackage.getXSDModelGroupDefinition_ModelGroup());
    }
    return childrenFeatures;
  }

  /**
   * This returns XSDModelGroupDefinition.gif.
   */
  @Override
  public Object getImage(Object object)
  {
    XSDModelGroupDefinition xsdModelGroupDefinition = ((XSDModelGroupDefinition)object);
    XSDModelGroupDefinition resolvedModelGroupDefinition = xsdModelGroupDefinition.getResolvedModelGroupDefinition();
    return 
      XSDEditPlugin.INSTANCE.getImage
        (resolvedModelGroupDefinition.getContainer() == null ?
           "full/obj16/XSDModelGroupDefinition" :
           xsdModelGroupDefinition.getResolvedModelGroupDefinition() == xsdModelGroupDefinition ?
             "full/obj16/XSDModelGroupDefinition" :
             "full/obj16/XSDModelGroupUse");
  }

  @Override
  public String getText(Object object)
  {
    XSDModelGroupDefinition xsdModelGroupDefinition = ((XSDModelGroupDefinition)object);
    String result = 
      xsdModelGroupDefinition.isModelGroupDefinitionReference() ?
        xsdModelGroupDefinition.getQName() :
        xsdModelGroupDefinition.getName();
    return result == null ? XSDEditPlugin.INSTANCE.getString("_UI_Absent_label") : result;
  }

  /**
   * This handles notification by calling {@link #fireNotifyChanged fireNotifyChanged}.
   */
  @Override
  public void notifyChanged(Notification msg) 
  {
    if (msg.getFeature() == xsdPackage.getXSDModelGroupDefinition_ModelGroup() || 
         msg.getFeature() == xsdPackage.getXSDModelGroupDefinition_ResolvedModelGroupDefinition() || 
         msg.getFeature() == xsdPackage.getXSDModelGroupDefinition_Annotation())
    {
      fireNotifyChanged(msg);
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
    XSDModelGroupDefinition mgd = (XSDModelGroupDefinition) object;

    if (!mgd.isModelGroupDefinitionReference())
    {
      // annotation
      newChildDescriptors.add(createChildParameter(xsdPackage.getXSDModelGroupDefinition_Annotation(), xsdFactory.createXSDAnnotation()));

      // all, choice, and sequence model groups
      addModelGroupChildParameters(newChildDescriptors, xsdPackage.getXSDModelGroupDefinition_ModelGroup(), true, false);
    }
  }
}
