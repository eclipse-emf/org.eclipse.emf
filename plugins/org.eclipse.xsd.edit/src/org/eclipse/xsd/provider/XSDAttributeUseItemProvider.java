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

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.command.CommandWrapper;
import org.eclipse.emf.common.command.UnexecutableCommand;
import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.edit.command.CommandParameter;
import org.eclipse.emf.edit.command.CreateChildCommand;
import org.eclipse.emf.edit.command.RemoveCommand;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.emf.edit.provider.ComposeableAdapterFactory;
import org.eclipse.emf.edit.provider.ComposedImage;
import org.eclipse.emf.edit.provider.IEditingDomainItemProvider;
import org.eclipse.emf.edit.provider.IItemLabelProvider;
import org.eclipse.emf.edit.provider.IItemPropertyDescriptor;
import org.eclipse.emf.edit.provider.IItemPropertySource;
import org.eclipse.emf.edit.provider.IStructuredItemContentProvider;
import org.eclipse.emf.edit.provider.ITreeItemContentProvider;
import org.eclipse.emf.edit.provider.ItemPropertyDescriptor;
import org.eclipse.emf.edit.provider.ItemPropertyDescriptorDecorator;

import org.eclipse.xsd.XSDAttributeDeclaration;
import org.eclipse.xsd.XSDAttributeUse;
import org.eclipse.xsd.XSDAttributeUseCategory;
import org.eclipse.xsd.XSDPackage;


/**
 * This is the item provider adpater for a {@link org.eclipse.xsd.XSDAttributeUse} object.
 */
public class XSDAttributeUseItemProvider
  extends XSDComponentItemProvider
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
  public XSDAttributeUseItemProvider(AdapterFactory adapterFactory)
  {
    super(adapterFactory);
  }

  protected XSDAttributeDeclaration getDelegate(XSDAttributeUse xsdAttributeUse)
  {
    return xsdAttributeUse.getContent();
  }

  /**
   * This returns the property descriptors for the adapted class.
   */
  @Override
  public List<IItemPropertyDescriptor> getPropertyDescriptors(Object object)
  {
    itemPropertyDescriptors = null;
    super.getPropertyDescriptors(object);

    XSDAttributeUse xsdAttributeUse = ((XSDAttributeUse)object);
    XSDAttributeDeclaration xsdAttributeDeclaration = getDelegate(xsdAttributeUse);
    if (xsdAttributeDeclaration != null)
    {
      for (IItemPropertyDescriptor delegatedItemPropertyDescriptor : itemDelegator.getPropertyDescriptors(xsdAttributeDeclaration))
      {
        Object feature = delegatedItemPropertyDescriptor.getFeature(object);
        if (feature != XSDPackage.eINSTANCE.getXSDFeature_Constraint() && 
              feature != XSDPackage.eINSTANCE.getXSDFeature_Value() &&
              feature != XSDPackage.eINSTANCE.getXSDFeature_LexicalValue())
        {
          itemPropertyDescriptors.add(new ItemPropertyDescriptorDecorator(xsdAttributeDeclaration, delegatedItemPropertyDescriptor));
        }
      }
    }

    // This is for the required feature.
    //
    itemPropertyDescriptors.add
      (new ItemPropertyDescriptor
        (((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
         XSDEditPlugin.INSTANCE.getString("_UI_Required_label"),
         XSDEditPlugin.INSTANCE.getString("_UI_Required_description"),
         xsdPackage.getXSDAttributeUse_Required(),
         true,
         ItemPropertyDescriptor.BOOLEAN_VALUE_IMAGE));

    // This is for the use feature.
    //
    itemPropertyDescriptors.add
      (new ItemPropertyDescriptor
        (((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
         XSDEditPlugin.INSTANCE.getString("_UI_Use_label"),
         XSDEditPlugin.INSTANCE.getString("_UI_Use_description"),
         xsdPackage.getXSDAttributeUse_Use(),
         true,
         ItemPropertyDescriptor.TEXT_VALUE_IMAGE));

    // This is for the value feature.
    //
    itemPropertyDescriptors.add
      (new ItemPropertyDescriptor
        (((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
         XSDEditPlugin.INSTANCE.getString("_UI_LexicalValue_label"),
         XSDEditPlugin.INSTANCE.getString("_UI_LexicalValueOfAttributeUse_description"),
         xsdPackage.getXSDAttributeUse_LexicalValue(),
         true,
         ItemPropertyDescriptor.TEXT_VALUE_IMAGE));

    // This is for the constraint feature.
    //
    itemPropertyDescriptors.add
      (new ItemPropertyDescriptor
        (((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
         XSDEditPlugin.INSTANCE.getString("_UI_Constraint_label"),
         XSDEditPlugin.INSTANCE.getString("_UI_ConstraintOfAttributeUse_description"),
         xsdPackage.getXSDAttributeUse_Constraint(),
         true,
         ItemPropertyDescriptor.TEXT_VALUE_IMAGE));

    // This is for the attributeDeclaration feature.
    //
    itemPropertyDescriptors.add
      (new ItemPropertyDescriptor
        (((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
         XSDEditPlugin.INSTANCE.getString("_UI_AttributeDeclaration_label"),
         XSDEditPlugin.INSTANCE.getString("_UI_AttributeDeclaration_description"),
         xsdPackage.getXSDAttributeUse_AttributeDeclaration(), 
         false));

    return itemPropertyDescriptors;
  }

  @Override
  public Collection<? extends EStructuralFeature> getChildrenFeatures(Object object)
  {
    if (childrenFeatures == null)
    {
      super.getChildrenFeatures(object);
      childrenFeatures.clear();
/*
      XSDAttributeUse xsdAttributeUse = ((XSDAttributeUse)object);
      childrenFeatures.add(xsdPackage.getXSDAttributeUse_Content());
*/
    }
    return childrenFeatures;
  }

  @Override
  public Collection<?> getChildren(Object object)
  {
    XSDAttributeUse xsdAttributeUse = (XSDAttributeUse)object;
    XSDAttributeDeclaration xsdAttributeDeclaration = getDelegate(xsdAttributeUse);
    if (xsdAttributeDeclaration != null)
    {
      return itemDelegator.getChildren(xsdAttributeDeclaration);
    }
    else
    {
      return Collections.EMPTY_LIST;
    }
  }

  @Override
  public boolean hasChildren(Object object)
  {
    XSDAttributeUse xsdAttributeUse = (XSDAttributeUse)object;
    XSDAttributeDeclaration xsdAttributeDeclaration = getDelegate(xsdAttributeUse);
    return
      xsdAttributeDeclaration != null && itemDelegator.hasChildren(xsdAttributeDeclaration);
  }

  /**
   * This returns XSDAttributeUse.gif.
   */
  @Override
  public Object getImage(Object object)
  {
    XSDAttributeUse xsdAttributeUse = ((XSDAttributeUse)object);
    XSDAttributeDeclaration xsdAttributeDeclaration = getDelegate(xsdAttributeUse);
    if (xsdAttributeDeclaration != null)
    {
      Collection<Object> images = new ArrayList<Object>();
      images.add(itemDelegator.getImage(xsdAttributeDeclaration));

      // Try this way, i.e., not showing the 1 case.
      //
      if (XSDAttributeUseCategory.REQUIRED_LITERAL != xsdAttributeUse.getUse())
      {
        String imageName = 
          "full/obj16/XSDOccurrence" +
             (XSDAttributeUseCategory.PROHIBITED_LITERAL == xsdAttributeUse.getUse() ?
               "Zero" : 
               XSDAttributeUseCategory.REQUIRED_LITERAL == xsdAttributeUse.getUse() ?
                 "One" : 
                 "ZeroToOne");
        images.add(XSDEditPlugin.INSTANCE.getImage(imageName));
      }

      return new ComposedImage(images);
    }
    else
    {
      return XSDEditPlugin.INSTANCE.getImage("full/obj16/XSDAttributeUse");
    }
  }

  @Override
  public String getText(Object object)
  {
    return getText(object, true);
  }

  public String getText(Object object, boolean showType)
  {
    XSDAttributeUse xsdAttributeUse = ((XSDAttributeUse)object);
    XSDAttributeDeclaration xsdAttributeDeclaration = getDelegate(xsdAttributeUse);
    StringBuffer result  = new StringBuffer();
    if (xsdAttributeDeclaration != null)
    {
      result.append(itemDelegator.getText(xsdAttributeDeclaration));
    }

    if (xsdAttributeUse.isSetConstraint())
    {
      if (result.length() != 0)
      {
        result.append("  ");
      }
      result.append('<');
      result.append(xsdAttributeUse.getConstraint());
      result.append("=\"");
      result.append(xsdAttributeUse.getLexicalValue());
      result.append("\">");
    }

    return result.toString();
  }

  /**
   * This handles notification by calling {@link #fireNotifyChanged fireNotifyChanged}.
   */
  @Override
  public void notifyChanged(Notification msg) 
  {
    if (msg.getFeature() == xsdPackage.getXSDAttributeUse_Required() || 
         msg.getFeature() == xsdPackage.getXSDAttributeUse_LexicalValue() || 
         msg.getFeature() == xsdPackage.getXSDAttributeUse_Constraint() || 
         msg.getFeature() == xsdPackage.getXSDAttributeUse_AttributeDeclaration() ||
         msg.getFeature() == xsdPackage.getXSDAttributeUse_Use())
    {
      fireNotifyChanged(msg);
      return;
    }
    super.notifyChanged(msg);
  }

  /**
   * This returns a list of child descriptors based on the attributeUse
   * content, not the attributeUse itself.
   */
  @Override
  public Collection<?> getNewChildDescriptors(Object object, EditingDomain domain, Object sibling)
  {
    Object content = ((XSDAttributeUse) object).getContent();
    return domain.getNewChildDescriptors(content, sibling);
  }

  /**
   * This returns Remove and CreateChild commands (at least) that are based
   * on the attributeUse content, not the attributeUse itself.
   */
  @Override
  public Command createCommand(final Object object, final EditingDomain domain, Class<? extends Command> commandClass, CommandParameter commandParameter)
  {
    if (commandClass == RemoveCommand.class ||
        commandClass == CreateChildCommand.class)
    {
      Object owner = ((XSDAttributeUse) object).getContent();
      if (owner == null)
      {
        return UnexecutableCommand.INSTANCE;
      }

      commandParameter.setOwner(owner);

      // RemoveCommand requires a wrapper that returns the correct affected
      // object after an execute or redo (the particle or attribute use
      // itself, instead of its content)
      if (commandClass == RemoveCommand.class)
      {
        return new CommandWrapper(
          domain.createCommand(commandClass, commandParameter))
        {
          private Collection<?> affectedObjects = Collections.EMPTY_SET;

          @Override
          public void execute()
          {
            super.execute();
            affectedObjects = Collections.singleton(object);
          }
            
          @Override
          public void undo()
          {
            super.undo();
            affectedObjects = super.getAffectedObjects();
          }

          @Override
          public void redo()
          {
            super.redo();
            affectedObjects = Collections.singleton(object);
          }

          @Override
          public Collection<?> getAffectedObjects()
          {
            return affectedObjects;
          }
        };
      }

      // CreateChildCommand handles its affected objects correctly
      return domain.createCommand(commandClass, commandParameter);
    }
    return super.createCommand(object, domain, commandClass, commandParameter);
  }
}
