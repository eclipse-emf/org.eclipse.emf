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

import org.eclipse.xsd.XSDConcreteComponent;
import org.eclipse.xsd.XSDParticle;
import org.eclipse.xsd.XSDWildcard;


/**
 * This is the item provider adpater for a {@link org.eclipse.xsd.XSDWildcard} object.
 */
public class XSDWildcardItemProvider
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
  public XSDWildcardItemProvider(AdapterFactory adapterFactory)
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

      // This is for the namespaceConstraint feature.
      //
      itemPropertyDescriptors.add
        (new ItemPropertyDescriptor
          (((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
           XSDEditPlugin.INSTANCE.getString("_UI_LexicalNamespaceConstraint_label"),
           XSDEditPlugin.INSTANCE.getString("_UI_LexicalNamespaceConstraint_description"),
           xsdPackage.getXSDWildcard_LexicalNamespaceConstraint(),
           true,
           ItemPropertyDescriptor.TEXT_VALUE_IMAGE)
         {
           @Override
          public Object getPropertyValue(Object o)
           {
             return ((XSDWildcard)o).getStringLexicalNamespaceConstraint();
           }
           @Override
          public void setPropertyValue(Object o, Object v)
           {
             XSDWildcard thisWildcard = (XSDWildcard)o;
             thisWildcard.setStringLexicalNamespaceConstraint((String)v);
/*
EATM TODO  
It looks like you can't set a multi-value anymore with the latest generated code.

             EditingDomain editingDomain = getEditingDomain(thisWildcard);
             if (editingDomain == null)
             {
               thisWildcard.setStringLexicalNamespaceConstraint((String)v);
             }
             else
             {
               //List newLexicalNamespaceConstraint = new ArrayList();
               List newLexicalNamespaceConstraint = new org.eclipse.emf.ecore.ref.impl.EListImpl();
               for (StringTokenizer stringTokenizer = new StringTokenizer((String)v); stringTokenizer.hasMoreTokens(); )
               {
                 String token = stringTokenizer.nextToken();
                 newLexicalNamespaceConstraint.add(token);
               }
               editingDomain.getCommandStack().execute
                 (SetCommand.create
                    (editingDomain,
                     thisWildcard,
                     xsdPackage.getXSDWildcard_LexicalNamespaceConstraint(),
                     newLexicalNamespaceConstraint));
             }
*/
           }
         });

      // This is for the namespaceConstraint feature.
      //
      itemPropertyDescriptors.add
        (new ItemPropertyDescriptor
          (((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
           XSDEditPlugin.INSTANCE.getString("_UI_NamespaceConstraint_label"),
           XSDEditPlugin.INSTANCE.getString("_UI_NamespaceConstraint_description"),
           xsdPackage.getXSDWildcard_NamespaceConstraint(),
           false,
           ItemPropertyDescriptor.TEXT_VALUE_IMAGE)
         {
           @Override
          public Object getPropertyValue(Object o)
           {
             return ((XSDWildcard)o).getStringNamespaceConstraint();
           }
         });

      // This is for the namespaceConstraintCategory feature.
      //
      itemPropertyDescriptors.add
        (new ItemPropertyDescriptor
          (((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
           XSDEditPlugin.INSTANCE.getString("_UI_NamespaceConstraintCategory_label"),
           XSDEditPlugin.INSTANCE.getString("_UI_NamespaceConstraintCategory_description"),
           xsdPackage.getXSDWildcard_NamespaceConstraintCategory(),
           false,
           ItemPropertyDescriptor.TEXT_VALUE_IMAGE));

      // This is for the processContents feature.
      //
      itemPropertyDescriptors.add
        (new ItemPropertyDescriptor
          (((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
           XSDEditPlugin.INSTANCE.getString("_UI_ProcessContents_label"),
           XSDEditPlugin.INSTANCE.getString("_UI_ProcessContents_description"),
           xsdPackage.getXSDWildcard_ProcessContents(),
           true,
           ItemPropertyDescriptor.TEXT_VALUE_IMAGE));

      // This is for the annotation feature.
      //
      itemPropertyDescriptors.add
        (new ItemPropertyDescriptor
          (((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
           XSDEditPlugin.INSTANCE.getString("_UI_Annotation_label"),
           XSDEditPlugin.INSTANCE.getString("_UI_AnnotationOfWildcard_description"),
           xsdPackage.getXSDWildcard_Annotation(), 
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
      childrenFeatures.add(xsdPackage.getXSDWildcard_Annotation());
    }
    return childrenFeatures;
  }

  /**
   * This returns XSDWildcard.gif.
   */
  @Override
  public Object getImage(Object object)
  {
    XSDWildcard xsdWildcard = ((XSDWildcard)object);
    return 
      XSDEditPlugin.INSTANCE.getImage
        (xsdWildcard.eContainer() instanceof XSDParticle ?
          "full/obj16/XSDWildcardElement" :
          "full/obj16/XSDWildcardAttribute");
  }

  @Override
  public String getText(Object object)
  {
    XSDWildcard xsdWildcard = ((XSDWildcard)object);
    String result = xsdWildcard.getStringLexicalNamespaceConstraint();
    return result == null ? "" : result;
  }

  /**
   * This handles notification by calling {@link #fireNotifyChanged fireNotifyChanged}.
   */
  @Override
  public void notifyChanged(Notification msg) 
  {
    XSDWildcard xsdWildcard = (XSDWildcard)msg.getNotifier();
    if (msg.getFeature() == xsdPackage.getXSDWildcard_LexicalNamespaceConstraint() ||
          msg.getFeature() == xsdPackage.getXSDWildcard_NamespaceConstraint())
    {
      fireNotifyChanged
        (new ENotificationImpl((InternalEObject)msg.getNotifier(), Notification.SET, null, null, null, Notification.NO_INDEX));

      XSDConcreteComponent container = xsdWildcard.getContainer();
      if (container instanceof XSDParticle)
      {
        ((ItemProviderAdapter)adapterFactory.adapt(container, IItemLabelProvider.class)).fireNotifyChanged
          (new ENotificationImpl((InternalEObject)container, Notification.SET, null, null, null, Notification.NO_INDEX));
      }
      return;
    }
    else if (msg.getFeature() == xsdPackage.getXSDWildcard_ProcessContents() || 
               msg.getFeature() == xsdPackage.getXSDWildcard_NamespaceConstraintCategory() ||
               msg.getFeature() == xsdPackage.getXSDWildcard_Annotation())
    {
      fireNotifyChanged(msg);
      XSDConcreteComponent container = xsdWildcard.getContainer();
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

    // annotation
    newChildDescriptors.add(createChildParameter(xsdPackage.getXSDWildcard_Annotation(), xsdFactory.createXSDAnnotation()));
  }
}
