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
 * $Id: XSDModelGroupDefinitionItemProvider.java,v 1.1 2004/03/06 18:00:11 marcelop Exp $
 */
package org.eclipse.xsd.provider;


import java.util.Collection;
import java.util.List;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.edit.provider.ComposeableAdapterFactory;
import org.eclipse.emf.edit.provider.IEditingDomainItemProvider;
import org.eclipse.emf.edit.provider.IItemLabelProvider;
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
  public List getPropertyDescriptors(Object object)
  {
    if (itemPropertyDescriptors == null)
    {
      super.getPropertyDescriptors(object);

      XSDModelGroupDefinition xsdModelGroupDefinition = ((XSDModelGroupDefinition)object);

      // This is for the annotation feature.
      //
      itemPropertyDescriptors.add
        (new ItemPropertyDescriptor
          (((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
           XSDEditPlugin.getString("_UI_Annotation_label"),
           XSDEditPlugin.getString("_UI_AnnotationOfModelGroupDefinition_description"),
           xsdPackage.getXSDModelGroupDefinition_Annotation(), 
           false));

      // This is for the resolvedModelGroupDefinition feature.
      //
      itemPropertyDescriptors.add
        (new ItemPropertyDescriptor
          (((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
           XSDEditPlugin.getString("_UI_ResolvedModelGroupDefinition_label"),
           XSDEditPlugin.getString("_UI_ResolvedModelGroupDefinition_description"),
           xsdPackage.getXSDModelGroupDefinition_ResolvedModelGroupDefinition(), 
           false));

    }
    return itemPropertyDescriptors;
  }

  /**
   * This specifies how to implement {@link #getChildren} and 
   * {@link org.eclipse.emf.edit.command.AddCommand} and 
   * {@link org.eclipse.emf.edit.command.RemoveCommand} support in {@link #createCommand}.
   */
  public Collection getChildrenReferences(Object object)
  {
    if (childrenReferences == null)
    {
      super.getChildrenReferences(object);
      XSDModelGroupDefinition xsdModelGroupDefinition = ((XSDModelGroupDefinition)object);
      childrenReferences.add(xsdPackage.getXSDModelGroupDefinition_Annotation());
      childrenReferences.add(xsdPackage.getXSDModelGroupDefinition_ModelGroup());
    }
    return childrenReferences;
  }

  /**
   * This returns XSDModelGroupDefinition.gif.
   */
  public Object getImage(Object object)
  {
    XSDModelGroupDefinition xsdModelGroupDefinition = ((XSDModelGroupDefinition)object);
    XSDModelGroupDefinition resolvedModelGroupDefinition = xsdModelGroupDefinition.getResolvedModelGroupDefinition();
    return 
      XSDEditPlugin.getImage
        (resolvedModelGroupDefinition.getContainer() == null ?
           "full/obj16/XSDModelGroupDefinition" :
           xsdModelGroupDefinition.getResolvedModelGroupDefinition() == xsdModelGroupDefinition ?
             "full/obj16/XSDModelGroupDefinition" :
             "full/obj16/XSDModelGroupUse");
  }

  public String getText(Object object)
  {
    XSDModelGroupDefinition xsdModelGroupDefinition = ((XSDModelGroupDefinition)object);
    String result = 
      xsdModelGroupDefinition.isModelGroupDefinitionReference() ?
        xsdModelGroupDefinition.getQName() :
        xsdModelGroupDefinition.getName();
    return result == null ? XSDEditPlugin.getString("_UI_Absent_label") : result;
  }

  /**
   * This handles notification by calling {@link #fireNotifyChanged fireNotifyChanged}.
   */
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
   * This adds to the collection of {@link org.eclipse.emf.edit.command.CommandParameter}s 
   * describing all of the children that can be created under this object.
   */
  protected void collectNewChildDescriptors(Collection newChildDescriptors,
                                            Object object)
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
