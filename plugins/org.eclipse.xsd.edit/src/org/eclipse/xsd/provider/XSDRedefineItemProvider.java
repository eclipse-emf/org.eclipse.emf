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
 * $Id: XSDRedefineItemProvider.java,v 1.1 2004/03/06 18:00:11 marcelop Exp $
 */
package org.eclipse.xsd.provider;


import java.util.Collection;
import java.util.List;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.edit.provider.IEditingDomainItemProvider;
import org.eclipse.emf.edit.provider.IItemLabelProvider;
import org.eclipse.emf.edit.provider.IItemPropertySource;
import org.eclipse.emf.edit.provider.IStructuredItemContentProvider;
import org.eclipse.emf.edit.provider.ITreeItemContentProvider;

import org.eclipse.xsd.XSDRedefine;


/**
 * This is the item provider adpater for a {@link org.eclipse.xsd.XSDRedefine} object.
 */
public class XSDRedefineItemProvider
  extends XSDSchemaCompositorItemProvider
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
  public XSDRedefineItemProvider(AdapterFactory adapterFactory)
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

      XSDRedefine xsdRedefine = ((XSDRedefine)object);

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
      XSDRedefine xsdRedefine = ((XSDRedefine)object);
      childrenReferences.add(xsdPackage.getXSDRedefine_Contents());
    }
    return childrenReferences;
  }

  /**
   * This returns XSDRedefine.gif.
   */
  public Object getImage(Object object)
  {
    return XSDEditPlugin.getImage("full/obj16/XSDRedefine");
  }

  public String getText(Object object)
  {
    XSDRedefine xsdRedefine = ((XSDRedefine)object);
    String result = xsdRedefine.getSchemaLocation();
    return result == null ? "" : result;
  }

  /**
   * This handles notification by calling {@link #fireNotifyChanged fireNotifyChanged}.
   */
  public void notifyChanged(Notification msg) 
  {
    if (
         msg.getFeature() == xsdPackage.getXSDRedefine_Annotations() || 
         msg.getFeature() == xsdPackage.getXSDRedefine_Contents()
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
    XSDRedefine r = (XSDRedefine) object;

    // annotation
    newChildDescriptors.add(createChildParameter(xsdPackage.getXSDRedefine_Contents(), xsdFactory.createXSDAnnotation()));

    // model group definition
    newChildDescriptors.add(createChildParameter(xsdPackage.getXSDRedefine_Contents(), createModelGroupDefinition(r)));

    // attribute group definition
    newChildDescriptors.add(createChildParameter(xsdPackage.getXSDRedefine_Contents(), createAttributeGroupDefinition(r)));

    // atomic, list, and union simple type definitions
    addSimpleTypeDefinitionChildParameters(newChildDescriptors, r, xsdPackage.getXSDRedefine_Contents(), true, true, true);

    // complex type definition
    newChildDescriptors.add(createChildParameter(xsdPackage.getXSDRedefine_Contents(), createComplexTypeDefinition(r)));
  }
}
