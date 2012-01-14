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
  @Override
  public List<IItemPropertyDescriptor> getPropertyDescriptors(Object object)
  {
    if (itemPropertyDescriptors == null)
    {
      super.getPropertyDescriptors(object);

      // This is for the identityConstraintCategory feature.
      //
      itemPropertyDescriptors.add
        (new ItemPropertyDescriptor
          (((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
           XSDEditPlugin.INSTANCE.getString("_UI_IdentityConstraintCategory_label"),
           XSDEditPlugin.INSTANCE.getString("_UI_IdentityConstraintCategory_description"),
           xsdPackage.getXSDIdentityConstraintDefinition_IdentityConstraintCategory(),
           true,
           ItemPropertyDescriptor.TEXT_VALUE_IMAGE));

/*
      // This is for the selector feature.
      //
      itemPropertyDescriptors.add
        (new ItemPropertyDescriptor
          (((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
           XSDEditPlugin.INSTANCE.getString("_UI_Selector_label"),
           XSDEditPlugin.INSTANCE.getString("_UI_Selector_description"),
           xsdPackage.getXSDIdentityConstraintDefinition_Selector(),
           true,
           ItemPropertyDescriptor.TEXT_VALUE_IMAGE));
*/

      // This is for the referencedKey feature.
      //
      itemPropertyDescriptors.add
        (new ItemPropertyDescriptor
          (((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
           XSDEditPlugin.INSTANCE.getString("_UI_ReferencedKey_label"),
           XSDEditPlugin.INSTANCE.getString("_UI_ReferencedKey_description"),
           xsdPackage.getXSDIdentityConstraintDefinition_ReferencedKey(), 
           false));

      // This is for the annotation feature.
      //
      itemPropertyDescriptors.add
        (new ItemPropertyDescriptor
          (((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
           XSDEditPlugin.INSTANCE.getString("_UI_Annotation_label"),
           XSDEditPlugin.INSTANCE.getString("_UI_AnnotationOfIdentityConstraint_description"),
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
  @Override
  public Collection<? extends EStructuralFeature> getChildrenFeatures(Object object)
  {
    if (childrenFeatures == null)
    {
      super.getChildrenFeatures(object);
      childrenFeatures.add(xsdPackage.getXSDIdentityConstraintDefinition_Annotation());
      childrenFeatures.add(xsdPackage.getXSDIdentityConstraintDefinition_Selector());
      childrenFeatures.add(xsdPackage.getXSDIdentityConstraintDefinition_Fields());
    }
    return childrenFeatures;
  }

  /**
   * This returns XSDIdentityConstraintDefinition.gif.
   */
  @Override
  public Object getImage(Object object)
  {
    XSDIdentityConstraintDefinition xsdIdentityConstraintDefinition = ((XSDIdentityConstraintDefinition)object);
    return 
      XSDEditPlugin.INSTANCE.getImage
        (XSDIdentityConstraintCategory.KEY_LITERAL ==  xsdIdentityConstraintDefinition.getIdentityConstraintCategory() ?  
          "full/obj16/XSDIdentityConstraintDefinitionKey" :
          XSDIdentityConstraintCategory.KEYREF_LITERAL == xsdIdentityConstraintDefinition.getIdentityConstraintCategory() ?  
            "full/obj16/XSDIdentityConstraintDefinitionKeyReference" :
            "full/obj16/XSDIdentityConstraintDefinitionUnique");
  }

  @Override
  public String getText(Object object)
  {
    XSDIdentityConstraintDefinition xsdIdentityConstraintDefinition = ((XSDIdentityConstraintDefinition)object);
    String result = xsdIdentityConstraintDefinition.getName();
    return result == null ? "" : result;
  }

  /**
   * This handles notification by calling {@link #fireNotifyChanged fireNotifyChanged}.
   */
  @Override
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
   * This adds {@link org.eclipse.emf.edit.command.CommandParameter}s describing the children
   * that can be created under this object.
   */
  @Override
  protected void collectNewChildDescriptors(Collection<Object> newChildDescriptors, Object object)
  {
    super.collectNewChildDescriptors(newChildDescriptors, object);

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
