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
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.edit.provider.ComposeableAdapterFactory;
import org.eclipse.emf.edit.provider.IEditingDomainItemProvider;
import org.eclipse.emf.edit.provider.IItemLabelProvider;
import org.eclipse.emf.edit.provider.IItemPropertyDescriptor;
import org.eclipse.emf.edit.provider.IItemPropertySource;
import org.eclipse.emf.edit.provider.IStructuredItemContentProvider;
import org.eclipse.emf.edit.provider.ITreeItemContentProvider;
import org.eclipse.emf.edit.provider.ItemPropertyDescriptor;

import org.eclipse.xsd.XSDAttributeDeclaration;
import org.eclipse.xsd.XSDAttributeGroupDefinition;


/**
 * This is the item provider adpater for a {@link org.eclipse.xsd.XSDAttributeGroupDefinition} object.
 */
public class XSDAttributeGroupDefinitionItemProvider
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
  public XSDAttributeGroupDefinitionItemProvider(AdapterFactory adapterFactory)
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

      // This is for the attributeWildcard feature.
      //
      itemPropertyDescriptors.add
        (new ItemPropertyDescriptor
          (((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
           XSDEditPlugin.INSTANCE.getString("_UI_AttributeWildcard_label"),
           XSDEditPlugin.INSTANCE.getString("_UI_AttributeWildcardOfAttributeGroup_description"),
           xsdPackage.getXSDAttributeGroupDefinition_AttributeWildcardContent(), 
           false));

      // This is for the annotation feature.
      //
      itemPropertyDescriptors.add
        (new ItemPropertyDescriptor
          (((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
           XSDEditPlugin.INSTANCE.getString("_UI_Annotation_label"),
           XSDEditPlugin.INSTANCE.getString("_UI_AnnotationOfAttributeGroup_description"),
           xsdPackage.getXSDAttributeGroupDefinition_Annotation(), 
           false));

      // This is for the resolvedAttributeGroupDefinition feature.
      //
      itemPropertyDescriptors.add
        (new ItemPropertyDescriptor
          (((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
           XSDEditPlugin.INSTANCE.getString("_UI_ResolvedAttributeGroupDefinition_label"),
           XSDEditPlugin.INSTANCE.getString("_UI_ResolvedAttributeGroupDefinition_description"),
           xsdPackage.getXSDAttributeGroupDefinition_ResolvedAttributeGroupDefinition(), 
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
      childrenFeatures.add(xsdPackage.getXSDAttributeGroupDefinition_Annotation());
      childrenFeatures.add(xsdPackage.getXSDAttributeGroupDefinition_Contents());
      childrenFeatures.add(xsdPackage.getXSDAttributeGroupDefinition_AttributeWildcardContent());
    }
    return childrenFeatures;
  }

  @Override
  protected EStructuralFeature getChildFeature(Object object, Object child)
  {
    EObject refObject = (EObject)child;
    if (refObject instanceof XSDAttributeDeclaration)
    {
      // TODO: check that this is what you want.
      return xsdPackage.getXSDAttributeGroupDefinition_Contents();
    }
    return super.getChildFeature(object, child);
  }

  /**
   * This returns XSDAttributeGroupDefinition.gif.
   */
  @Override
  public Object getImage(Object object)
  {
    XSDAttributeGroupDefinition xsdAttributeGroupDefinition = ((XSDAttributeGroupDefinition)object);
    XSDAttributeGroupDefinition resolvedAttributeGroupDefinition = xsdAttributeGroupDefinition.getResolvedAttributeGroupDefinition();
    return 
      XSDEditPlugin.INSTANCE.getImage
        (resolvedAttributeGroupDefinition.getContainer() == null ?
          "full/obj16/XSDAttributeGroupUnresolved" :
          xsdAttributeGroupDefinition.getResolvedAttributeGroupDefinition() == xsdAttributeGroupDefinition ?
            "full/obj16/XSDAttributeGroupDefinition" :
            "full/obj16/XSDAttributeGroupUse");
  }

  @Override
  public String getText(Object object)
  {
    XSDAttributeGroupDefinition xsdAttributeGroupDefinition = ((XSDAttributeGroupDefinition)object);
    String result =  
      xsdAttributeGroupDefinition.isAttributeGroupDefinitionReference() ?
        xsdAttributeGroupDefinition.getQName() :
        xsdAttributeGroupDefinition.getName();
    return result == null ? XSDEditPlugin.INSTANCE.getString("_UI_Absent_label") : result;
  }

  /**
   * This handles notification by calling {@link #fireNotifyChanged fireNotifyChanged}.
   */
  @Override
  public void notifyChanged(Notification msg) 
  {
    if (
         msg.getFeature() == xsdPackage.getXSDAttributeGroupDefinition_Contents() || 
         msg.getFeature() == xsdPackage.getXSDAttributeGroupDefinition_AttributeUses() || 
         msg.getFeature() == xsdPackage.getXSDAttributeGroupDefinition_AttributeWildcardContent() || 
         msg.getFeature() == xsdPackage.getXSDAttributeGroupDefinition_Annotation()
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
    XSDAttributeGroupDefinition xsdAttributeGroupDefinition = (XSDAttributeGroupDefinition) object;

    if (!xsdAttributeGroupDefinition.isAttributeGroupDefinitionReference())
    {
      // annotation
      //
      newChildDescriptors.add
        (createChildParameter
          (xsdPackage.getXSDAttributeGroupDefinition_Annotation(), 
           xsdFactory.createXSDAnnotation()));

      // attribute declaration under attribute use
      //
      XSDAttributeDeclaration xsdAttributeDeclaration = createAttributeDeclaration(xsdAttributeGroupDefinition);
      newChildDescriptors.add
        (createChildParameter
          (xsdPackage.getXSDAttributeGroupDefinition_Contents(), 
           createAttributeUse(xsdAttributeDeclaration, false)));

      // attribute declaration reference under attribute use
      //
      newChildDescriptors.add
        (createChildParameter
          (xsdPackage.getXSDAttributeGroupDefinition_Contents(), 
           createAttributeUse(xsdAttributeGroupDefinition.resolveAttributeDeclaration(""), true)));

      // attribute group definition reference
      //
      newChildDescriptors.add
        (createChildParameter
          (xsdPackage.getXSDAttributeGroupDefinition_Contents(), 
          createAttributeGroupDefinitionReference(xsdAttributeGroupDefinition.resolveAttributeGroupDefinition(""))));

      // attribute wildcard
      //
      newChildDescriptors.add
        (createChildParameter
          (xsdPackage.getXSDAttributeGroupDefinition_AttributeWildcardContent(), 
           xsdFactory.createXSDWildcard()));
    }
  }
}
