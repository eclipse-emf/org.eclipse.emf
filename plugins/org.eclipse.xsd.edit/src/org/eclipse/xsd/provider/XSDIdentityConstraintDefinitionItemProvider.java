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
 * $Id: XSDIdentityConstraintDefinitionItemProvider.java,v 1.2 2004/04/07 22:12:58 davidms Exp $
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

import org.eclipse.xsd.XSDIdentityConstraintCategory;
import org.eclipse.xsd.XSDIdentityConstraintDefinition;
import org.eclipse.xsd.XSDXPathDefinition;
import org.eclipse.xsd.XSDXPathVariety;


/**
 * This is the item provider adpater for a {@link org.eclipse.xsd.XSDIdentityConstraintDefinition} object.
 */
public class XSDIdentityConstraintDefinitionItemProvider
  extends XSDNamedComponentItemProvider
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
  public XSDIdentityConstraintDefinitionItemProvider(AdapterFactory adapterFactory)
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

      XSDIdentityConstraintDefinition xsdIdentityConstraintDefinition = ((XSDIdentityConstraintDefinition)object);

      // This is for the identityConstraintCategory feature.
      //
      itemPropertyDescriptors.add
        (new ItemPropertyDescriptor
          (((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
           XSDEditPlugin.getString("_UI_IdentityConstraintCategory_label"),
           XSDEditPlugin.getString("_UI_IdentityConstraintCategory_description"),
           xsdPackage.getXSDIdentityConstraintDefinition_IdentityConstraintCategory(),
           true,
           ItemPropertyDescriptor.TEXT_VALUE_IMAGE));

/*
      // This is for the selector feature.
      //
      itemPropertyDescriptors.add
        (new ItemPropertyDescriptor
          (((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
           XSDEditPlugin.getString("_UI_Selector_label"),
           XSDEditPlugin.getString("_UI_Selector_description"),
           xsdPackage.getXSDIdentityConstraintDefinition_Selector(),
           true,
           ItemPropertyDescriptor.TEXT_VALUE_IMAGE));
*/

      // This is for the referencedKey feature.
      //
      itemPropertyDescriptors.add
        (new ItemPropertyDescriptor
          (((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
           XSDEditPlugin.getString("_UI_ReferencedKey_label"),
           XSDEditPlugin.getString("_UI_ReferencedKey_description"),
           xsdPackage.getXSDIdentityConstraintDefinition_ReferencedKey(), 
           false));

      // This is for the annotation feature.
      //
      itemPropertyDescriptors.add
        (new ItemPropertyDescriptor
          (((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
           XSDEditPlugin.getString("_UI_Annotation_label"),
           XSDEditPlugin.getString("_UI_AnnotationOfIdentityConstraint_description"),
           xsdPackage.getXSDIdentityConstraintDefinition_Annotation(), 
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
      XSDIdentityConstraintDefinition xsdIdentityConstraintDefinition = ((XSDIdentityConstraintDefinition)object);
      childrenFeatures.add(xsdPackage.getXSDIdentityConstraintDefinition_Annotation());
      childrenFeatures.add(xsdPackage.getXSDIdentityConstraintDefinition_Selector());
      childrenFeatures.add(xsdPackage.getXSDIdentityConstraintDefinition_Fields());
    }
    return childrenFeatures;
  }

  /**
   * This returns XSDIdentityConstraintDefinition.gif.
   */
  public Object getImage(Object object)
  {
    XSDIdentityConstraintDefinition xsdIdentityConstraintDefinition = ((XSDIdentityConstraintDefinition)object);
    return 
      XSDEditPlugin.getImage
        (XSDIdentityConstraintCategory.KEY_LITERAL ==  xsdIdentityConstraintDefinition.getIdentityConstraintCategory() ?  
          "full/obj16/XSDIdentityConstraintDefinitionKey" :
          XSDIdentityConstraintCategory.KEYREF_LITERAL == xsdIdentityConstraintDefinition.getIdentityConstraintCategory() ?  
            "full/obj16/XSDIdentityConstraintDefinitionKeyReference" :
            "full/obj16/XSDIdentityConstraintDefinitionUnique");
  }

  public String getText(Object object)
  {
    XSDIdentityConstraintDefinition xsdIdentityConstraintDefinition = ((XSDIdentityConstraintDefinition)object);
    String result = xsdIdentityConstraintDefinition.getName();
    return result == null ? "" : result;
  }

  /**
   * This handles notification by calling {@link #fireNotifyChanged fireNotifyChanged}.
   */
  public void notifyChanged(Notification msg) 
  {
    if (
         msg.getFeature() == xsdPackage.getXSDIdentityConstraintDefinition_IdentityConstraintCategory() || 
         msg.getFeature() == xsdPackage.getXSDIdentityConstraintDefinition_Selector() || 
         msg.getFeature() == xsdPackage.getXSDIdentityConstraintDefinition_Fields() || 
         msg.getFeature() == xsdPackage.getXSDIdentityConstraintDefinition_ReferencedKey() || 
         msg.getFeature() == xsdPackage.getXSDIdentityConstraintDefinition_Annotation()
       )
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
    XSDIdentityConstraintDefinition icd = 
      (XSDIdentityConstraintDefinition) object;

    // annotation
    newChildDescriptors.add(createChildParameter(xsdPackage.getXSDIdentityConstraintDefinition_Annotation(), xsdFactory.createXSDAnnotation()));

    // selector xpath
    XSDXPathDefinition xpd = xsdFactory.createXSDXPathDefinition();
    xpd.setVariety(XSDXPathVariety.SELECTOR_LITERAL);
    xpd.setValue("");
    newChildDescriptors.add(createChildParameter(xsdPackage.getXSDIdentityConstraintDefinition_Selector(), xpd));

    // field xpath
    xpd = xsdFactory.createXSDXPathDefinition();
    xpd.setVariety(XSDXPathVariety.FIELD_LITERAL);
    xpd.setValue("");
    newChildDescriptors.add(createChildParameter(xsdPackage.getXSDIdentityConstraintDefinition_Fields(), xpd));
  }
}
