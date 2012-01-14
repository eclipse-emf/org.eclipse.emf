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
import org.eclipse.xsd.XSDComplexTypeDefinition;
import org.eclipse.xsd.XSDContentTypeCategory;
import org.eclipse.xsd.XSDDerivationMethod;
import org.eclipse.xsd.XSDTypeDefinition;
import org.eclipse.xsd.util.XSDConstants;


/**
 * This is the item provider adpater for a {@link org.eclipse.xsd.XSDComplexTypeDefinition} object.
 */
public class XSDComplexTypeDefinitionItemProvider
  extends XSDTypeDefinitionItemProvider
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
  public XSDComplexTypeDefinitionItemProvider(AdapterFactory adapterFactory)
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

      // This is for the derivationMethod feature.
      //
      itemPropertyDescriptors.add
        (new ItemPropertyDescriptor
          (((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
           XSDEditPlugin.INSTANCE.getString("_UI_DerivationMethod_label"),
           XSDEditPlugin.INSTANCE.getString("_UI_DerivationMethod_description"),
           xsdPackage.getXSDComplexTypeDefinition_DerivationMethod(),
           true,
           ItemPropertyDescriptor.TEXT_VALUE_IMAGE));

/*
      // This is for the ffinal feature.
      //
      itemPropertyDescriptors.add
        (new ItemPropertyDescriptor
          (((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
           XSDEditPlugin.INSTANCE.getString("_UI_Final_label"),
           XSDEditPlugin.INSTANCE.getString("_UI_FinalOfType_description"),
           xsdPackage.getXSDComplexTypeDefinition_Final(),
           true,
           ItemPropertyDescriptor.TEXT_VALUE_IMAGE));
*/

      // This is for the fabstract feature.
      //
      itemPropertyDescriptors.add
        (new ItemPropertyDescriptor
          (((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
           XSDEditPlugin.INSTANCE.getString("_UI_Abstract_label"),
           XSDEditPlugin.INSTANCE.getString("_UI_AbstractOfType_description"),
           xsdPackage.getXSDComplexTypeDefinition_Abstract(),
           true,
           ItemPropertyDescriptor.BOOLEAN_VALUE_IMAGE));

      // This is for the mixed feature.
      //
      itemPropertyDescriptors.add
        (new ItemPropertyDescriptor
          (((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
           XSDEditPlugin.INSTANCE.getString("_UI_Mixed_label"),
           XSDEditPlugin.INSTANCE.getString("_UI_Mixed_description"),
           xsdPackage.getXSDComplexTypeDefinition_Mixed(),
           true,
           ItemPropertyDescriptor.BOOLEAN_VALUE_IMAGE));

      // This is for the contentTypeCategory feature.
      //
      itemPropertyDescriptors.add
        (new ItemPropertyDescriptor
          (((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
           XSDEditPlugin.INSTANCE.getString("_UI_ContentTypeCategory_label"),
           XSDEditPlugin.INSTANCE.getString("_UI_ContentTypeCategory_description"),
           xsdPackage.getXSDComplexTypeDefinition_ContentTypeCategory(),
           true,
           ItemPropertyDescriptor.TEXT_VALUE_IMAGE));

      // This is for the contentType feature.
      //
      itemPropertyDescriptors.add
        (new ItemPropertyDescriptor
          (((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
           XSDEditPlugin.INSTANCE.getString("_UI_ContentType_label"),
           XSDEditPlugin.INSTANCE.getString("_UI_ContentType_description"),
           xsdPackage.getXSDComplexTypeDefinition_ContentType(),
           false));

      // This is for the prohibitedSubstitutions feature.
      //
      itemPropertyDescriptors.add
        (new ItemPropertyDescriptor
          (((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
           XSDEditPlugin.INSTANCE.getString("_UI_ProhibitedSubstitutions_label"),
           XSDEditPlugin.INSTANCE.getString("_UI_ProhibitedSubstitutions_description"),
           xsdPackage.getXSDComplexTypeDefinition_ProhibitedSubstitutions(),
           false,
           ItemPropertyDescriptor.TEXT_VALUE_IMAGE)
         {
           @Override
          public Object getPropertyValue(Object o)
           {
             return ((XSDComplexTypeDefinition)o).getStringProhibitedSubstitutions();
           }
         });

      // This is for the attributeWildcardContent feature.
      //
      itemPropertyDescriptors.add
        (new ItemPropertyDescriptor
          (((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
           XSDEditPlugin.INSTANCE.getString("_UI_AttributeWildcard_label"),
           XSDEditPlugin.INSTANCE.getString("_UI_AttributeWildcardOfType_description"),
           xsdPackage.getXSDComplexTypeDefinition_AttributeWildcardContent(), 
           false));

      // This is for the annotation feature.
      //
      itemPropertyDescriptors.add
        (new ItemPropertyDescriptor
          (((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
           XSDEditPlugin.INSTANCE.getString("_UI_Annotation_label"),
           XSDEditPlugin.INSTANCE.getString("_UI_AnnotationOfType_description"),
           xsdPackage.getXSDTypeDefinition_Annotation(), 
           false));

      // This is for the baseTypeDefinition feature.
      //
      itemPropertyDescriptors.add
        (new ItemPropertyDescriptor
          (((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
           XSDEditPlugin.INSTANCE.getString("_UI_BaseTypeDefinition_label"),
           XSDEditPlugin.INSTANCE.getString("_UI_BaseTypeDefinition_description"),
           xsdPackage.getXSDComplexTypeDefinition_BaseTypeDefinition(),
           false));

      createLexicalFinalPropertyDescriptor(object);
      createFinalPropertyDescriptor(object);
      createBlockPropertyDescriptor(object);
    }
    return itemPropertyDescriptors;
  }

  protected void createLexicalFinalPropertyDescriptor(Object object)
  {

    // This is for the final feature.
    //
    itemPropertyDescriptors.add
      (new ItemPropertyDescriptorWithDefault
        (((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
         XSDEditPlugin.INSTANCE.getString("_UI_LexicalFinal_label"),
         XSDEditPlugin.INSTANCE.getString("_UI_LexicalFinal_description"),
         xsdPackage.getXSDComplexTypeDefinition_LexicalFinal(),
         true,
         ItemPropertyDescriptor.TEXT_VALUE_IMAGE)
       {
         @Override
        public Object getPropertyValue(Object o)
         {
           return ((XSDComplexTypeDefinition)o).getStringLexicalFinal();
         }
         @Override
        public void setPropertyValue(Object o, Object value)
         {
           ((XSDComplexTypeDefinition)o).setStringLexicalFinal((String)value);
         }
         @Override
        public Collection<?> getChoiceOfValues(Object o)
         {
           Collection<Object> result = new ArrayList<Object>();
           result.add("");
           result.add("#all");
           result.add("extension");
           result.add("restriction");
           result.add("extension restriction");
           return result;
         }
       });
  }

  protected void createFinalPropertyDescriptor(Object object)
  {

    // This is for the final feature.
    //
    itemPropertyDescriptors.add
      (new ItemPropertyDescriptorWithDefault
        (((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
         XSDEditPlugin.INSTANCE.getString("_UI_Final_label"),
         XSDEditPlugin.INSTANCE.getString("_UI_Final_description"),
         xsdPackage.getXSDComplexTypeDefinition_Final(),
         false,
         ItemPropertyDescriptor.TEXT_VALUE_IMAGE)
       {
         @Override
        public Object getPropertyValue(Object o)
         {
           return ((XSDComplexTypeDefinition)o).getStringFinal();
         }
       });
  }

  protected void createBlockPropertyDescriptor(Object object)
  {

    // This is for the final feature.
    //
    itemPropertyDescriptors.add
      (new ItemPropertyDescriptorWithDefault
        (((ComposeableAdapterFactory)adapterFactory).getRootAdapterFactory(),
         XSDEditPlugin.INSTANCE.getString("_UI_Block_label"),
         XSDEditPlugin.INSTANCE.getString("_UI_BlockOfType_description"),
         xsdPackage.getXSDComplexTypeDefinition_Block(),
         true,
         ItemPropertyDescriptor.TEXT_VALUE_IMAGE)
       {
         @Override
        public Object getPropertyValue(Object o)
         {
           return ((XSDComplexTypeDefinition)o).getStringBlock();
         }
         @Override
        public void setPropertyValue(Object o, Object value)
         {
           ((XSDComplexTypeDefinition)o).setStringBlock((String)value);
         }
         @Override
        public Collection<?> getChoiceOfValues(Object o)
         {
           Collection<Object> result = new ArrayList<Object>();
           result.add("");
           result.add("#all");
           result.add("extension");
           result.add("restriction");
           result.add("substitution");
           result.add("extension restriction");
           result.add("extension substitution");
           result.add("extension restriction substitution");
           result.add("restriction substitution");
           return result;
         }
       });
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
      childrenFeatures.add(xsdPackage.getXSDTypeDefinition_Annotation());
      childrenFeatures.add(xsdPackage.getXSDComplexTypeDefinition_ContentAnnotation());
      childrenFeatures.add(xsdPackage.getXSDTypeDefinition_DerivationAnnotation());
      childrenFeatures.add(xsdPackage.getXSDComplexTypeDefinition_Content());
      childrenFeatures.add(xsdPackage.getXSDComplexTypeDefinition_AttributeContents());
      childrenFeatures.add(xsdPackage.getXSDComplexTypeDefinition_AttributeWildcardContent());
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
      return xsdPackage.getXSDComplexTypeDefinition_AttributeContents();
    }
    return super.getChildFeature(object, child);
  }

  /**
   * This returns XSDComplexTypeDefinition.gif.
   */
  @Override
  public Object getImage(Object object)
  {
    XSDComplexTypeDefinition xsdComplexTypeDefinition = ((XSDComplexTypeDefinition)object);
    return 
      XSDEditPlugin.INSTANCE.getImage
        (xsdComplexTypeDefinition.getContainer() == null ?
          "full/obj16/XSDComplexTypeDefinitionUnresolved" :
          "full/obj16/XSDComplexTypeDefinition");
  }

  @Override
  public String getText(Object object)
  {
    return getText(object, true);
  }

  public String getText(Object object, boolean showType)
  {
    XSDComplexTypeDefinition xsdComplexTypeDefinition = ((XSDComplexTypeDefinition)object);

    StringBuffer result = new StringBuffer();

    result.append
     (xsdComplexTypeDefinition.getName() == null ? 
        xsdComplexTypeDefinition.getAliasName() : 
        xsdComplexTypeDefinition.getName());

    if (showType)
    {
      XSDTypeDefinition baseTypeDefinition = xsdComplexTypeDefinition.getBaseTypeDefinition();
      if (baseTypeDefinition != null && 
            baseTypeDefinition != xsdComplexTypeDefinition.getContent() &&
            baseTypeDefinition.getName() != null &&
            !XSDConstants.isURType(baseTypeDefinition))
      {
        result.append(" : ");
        result.append(baseTypeDefinition.getQName(xsdComplexTypeDefinition));
      }
    }

    return result.toString();
  }

  /**
   * This handles notification by calling {@link #fireNotifyChanged fireNotifyChanged}.
   */
  @Override
  public void notifyChanged(Notification msg) 
  {
    if (msg.getFeature() == xsdPackage.getXSDComplexTypeDefinition_DerivationMethod() || 
          msg.getFeature() == xsdPackage.getXSDComplexTypeDefinition_Final() || 
          msg.getFeature() == xsdPackage.getXSDComplexTypeDefinition_LexicalFinal() || 
          msg.getFeature() == xsdPackage.getXSDComplexTypeDefinition_BaseTypeDefinition() || 
          msg.getFeature() == xsdPackage.getXSDComplexTypeDefinition_Abstract() || 
          msg.getFeature() == xsdPackage.getXSDComplexTypeDefinition_ContentTypeCategory() || 
          msg.getFeature() == xsdPackage.getXSDComplexTypeDefinition_ProhibitedSubstitutions() || 
          msg.getFeature() == xsdPackage.getXSDComplexTypeDefinition_AttributeContents() || 
          msg.getFeature() == xsdPackage.getXSDComplexTypeDefinition_AttributeWildcardContent() || 
          msg.getFeature() == xsdPackage.getXSDComplexTypeDefinition_Content() || 
          msg.getFeature() == xsdPackage.getXSDComplexTypeDefinition_AttributeUses() || 
          msg.getFeature() == xsdPackage.getXSDTypeDefinition_Annotations() || 
          msg.getFeature() == xsdPackage.getXSDTypeDefinition_Annotation())
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
    XSDComplexTypeDefinition ctd = (XSDComplexTypeDefinition) object;

    // type annotation
    newChildDescriptors.add(createChildParameter(xsdPackage.getXSDTypeDefinition_Annotation(), xsdFactory.createXSDAnnotation()));

    XSDComplexTypeDefinition anyTypeDefinition = ctd.getSchema().getSchemaForSchema().resolveComplexTypeDefinition("anyType");
    XSDContentTypeCategory contentType = ctd.getContentTypeCategory();

    // content annotation on simple type definition content (simple type
    // definition already contains its own derivation annotation)...
    if (contentType == XSDContentTypeCategory.SIMPLE_LITERAL)
    {
      // content annotation
      newChildDescriptors.add(createChildParameter(xsdPackage.getXSDComplexTypeDefinition_ContentAnnotation(), xsdFactory.createXSDAnnotation()));
    }

    // content and derivation annotations on complex content, if not
    // restriction of anyTypeDefinition (for which short form is used)...
    else if ((contentType == XSDContentTypeCategory.ELEMENT_ONLY_LITERAL ||
              contentType == XSDContentTypeCategory.MIXED_LITERAL) &&
             !(ctd.getBaseTypeDefinition() == anyTypeDefinition &&
               ctd.getDerivationMethod() == XSDDerivationMethod.RESTRICTION_LITERAL))
    {
      // content annotation
      newChildDescriptors.add(createChildParameter(xsdPackage.getXSDComplexTypeDefinition_ContentAnnotation(), xsdFactory.createXSDAnnotation()));

      // derivation annotation
      newChildDescriptors.add(createChildParameter(xsdPackage.getXSDTypeDefinition_DerivationAnnotation(), xsdFactory.createXSDAnnotation()));
    }

    // atomic simple type definition content
    addSimpleTypeDefinitionChildParameters(newChildDescriptors, ctd, xsdPackage.getXSDComplexTypeDefinition_Content(), true, false, false);

    // all, choice, and sequence model groups particle content
    addModelGroupChildParameters(newChildDescriptors, xsdPackage.getXSDComplexTypeDefinition_Content(), true, true);

    // model group definition reference particle content
    newChildDescriptors.add(createChildParameter(xsdPackage.getXSDComplexTypeDefinition_Content(), createParticle(ctd.resolveModelGroupDefinition(""), true)));

    // attribute declaration under attribute use
    XSDAttributeDeclaration ad = createAttributeDeclaration(ctd);
    newChildDescriptors.add(createChildParameter(xsdPackage.getXSDComplexTypeDefinition_AttributeContents(), createAttributeUse(ad, false)));

    // attribute declaration reference under attribute use
    newChildDescriptors.add(createChildParameter(xsdPackage.getXSDComplexTypeDefinition_AttributeContents(), createAttributeUse(ctd.resolveAttributeDeclaration(""), true)));

    // attribute group definition reference
    newChildDescriptors.add(createChildParameter(xsdPackage.getXSDComplexTypeDefinition_AttributeContents(), createAttributeGroupDefinitionReference(ctd.resolveAttributeGroupDefinition(""))));

    // attribute wildcard
    newChildDescriptors.add(createChildParameter(xsdPackage.getXSDComplexTypeDefinition_AttributeWildcardContent(), xsdFactory.createXSDWildcard()));
  }
}
