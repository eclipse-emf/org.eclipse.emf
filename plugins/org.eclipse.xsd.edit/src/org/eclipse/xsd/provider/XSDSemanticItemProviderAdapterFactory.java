/**
 * Copyright (c) 2002-2006 IBM Corporation and others.
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

import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.impl.NotificationImpl;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.edit.provider.AdapterFactoryItemDelegator;
import org.eclipse.emf.edit.provider.ItemProvider;

import org.eclipse.xsd.XSDAttributeDeclaration;
import org.eclipse.xsd.XSDAttributeUse;
import org.eclipse.xsd.XSDElementDeclaration;
import org.eclipse.xsd.XSDNamespaceConstraintCategory;
import org.eclipse.xsd.XSDParticle;
import org.eclipse.xsd.XSDParticleContent;
import org.eclipse.xsd.XSDSchema;
import org.eclipse.xsd.XSDWildcard;


public class XSDSemanticItemProviderAdapterFactory extends XSDItemProviderAdapterFactory 
{
  public XSDSemanticItemProviderAdapterFactory()
  {
    super();
  }

  @Override
  public Adapter createXSDSchemaAdapter()
  {
    // if (xsdSchemaItemProvider == null)
    {
      xsdSchemaItemProvider = 
        new XSDSchemaItemProvider(this)
        {
          protected List<Object> children;

          @Override
          public Collection<? extends EStructuralFeature> getChildrenFeatures(Object object)
          {
            return Collections.emptyList();
          }

          @Override
          public Collection<?> getChildren(Object object)
          {
            if (children == null)
            {
              final XSDSchema xsdSchema = (XSDSchema)object;
              class ChildItemProvider extends ItemProvider
              {
                Collection<?> children;
                public ChildItemProvider(String label, Object image, Collection<?> children)
                {
                  super(label, image, xsdSchema);
                  this.children = children;
                }
                @Override
                public boolean hasChildren(Object o)
                {
                  return !children.isEmpty();
                }
                @Override
                public Collection<?> getChildren(Object o)
                {
                  return children;
                }
              }

              children = new ArrayList<Object>();
              children.add
                (new ChildItemProvider
                  (XSDEditPlugin.INSTANCE.getString("_UI_Elements_label"), 
                    XSDEditPlugin.INSTANCE.getImage("full/obj16/XSDElementDeclaration"),
                    xsdSchema.getElementDeclarations()));
              children.add
                (new ChildItemProvider
                  (XSDEditPlugin.INSTANCE.getString("_UI_Attributes_label"),
                    XSDEditPlugin.INSTANCE.getImage("full/obj16/XSDAttributeDeclaration"),
                    xsdSchema.getAttributeDeclarations()));
              children.add
                (new ChildItemProvider
                  (XSDEditPlugin.INSTANCE.getString("_UI_AttributeGroups_label"), 
                    XSDEditPlugin.INSTANCE.getImage("full/obj16/XSDAttributeGroupDefinition"),
                    xsdSchema.getAttributeGroupDefinitions()));
              children.add
                (new ChildItemProvider
                  (XSDEditPlugin.INSTANCE.getString("_UI_Types_label"), 
                    XSDEditPlugin.INSTANCE.getImage("full/obj16/XSDTypeDefinition"),
                    xsdSchema.getTypeDefinitions()));
              children.add
                (new ChildItemProvider
                  (XSDEditPlugin.INSTANCE.getString("_UI_ModelGroups_label"), 
                    XSDEditPlugin.INSTANCE.getImage("full/obj16/XSDModelGroupDefinition"),
                    xsdSchema.getModelGroupDefinitions()));
              children.add
                (new ChildItemProvider
                  (XSDEditPlugin.INSTANCE.getString("_UI_Notations_label"), 
                    XSDEditPlugin.INSTANCE.getImage("full/obj16/XSDNotationDeclaration"),
                    xsdSchema.getNotationDeclarations()));
              children.add
                (new ChildItemProvider
                  (XSDEditPlugin.INSTANCE.getString("_UI_IdentityConstraints_label"), 
                    XSDEditPlugin.INSTANCE.getImage("full/obj16/XSDIdentityConstraintDefinitionKey"),
                    xsdSchema.getIdentityConstraintDefinitions()));
              children.add
                (new ChildItemProvider
                  (XSDEditPlugin.INSTANCE.getString("_UI_Annotations_label"), 
                    XSDEditPlugin.INSTANCE.getImage("full/obj16/XSDAnnotation"),
                    xsdSchema.getAnnotations()));
            }
            return children;
          }

          @Override
          public void notifyChanged(final Notification msg)
          {
            class CagetoryNotification extends NotificationImpl
            {
              protected Object category;
              public CagetoryNotification(Object category)
              {
                super(msg.getEventType(), msg.getOldValue(), msg.getNewValue(), msg.getPosition());
                this.category = category;
              }

              @Override
              public Object getNotifier()
              {
                return category;
              }
              @Override
              public Object getFeature()
              {
                return msg.getFeature();
              }
            }

            if (msg.getFeature() == xsdPackage.getXSDSchema_ElementDeclarations())
            {
              this.fireNotifyChanged(new CagetoryNotification(children.get(0)));
              return;
            }
            else if (msg.getFeature() == xsdPackage.getXSDSchema_AttributeDeclarations())
            {
              this.fireNotifyChanged(new CagetoryNotification(children.get(1)));
              return;
            }
            else if (msg.getFeature() == xsdPackage.getXSDSchema_AttributeGroupDefinitions())
            {
              this.fireNotifyChanged(new CagetoryNotification(children.get(2)));
              return;
            }
            else if (msg.getFeature() == xsdPackage.getXSDSchema_TypeDefinitions())
            {
              this.fireNotifyChanged(new CagetoryNotification(children.get(3)));
              return;
            }
            else if (msg.getFeature() == xsdPackage.getXSDSchema_ModelGroupDefinitions())
            {
              this.fireNotifyChanged(new CagetoryNotification(children.get(4)));
              return;
            }
            else if (msg.getFeature() == xsdPackage.getXSDSchema_NotationDeclarations())
            {
              this.fireNotifyChanged(new CagetoryNotification(children.get(5)));
              return;
            }
            else if (msg.getFeature() == xsdPackage.getXSDSchema_IdentityConstraintDefinitions())
            {
              this.fireNotifyChanged(new CagetoryNotification(children.get(6)));
              return;
            }
            else if (msg.getFeature() == xsdPackage.getXSDSchema_Annotations())
            {
              this.fireNotifyChanged(new CagetoryNotification(children.get(7)));
              return;
            }
            else if (msg.getFeature() == xsdPackage.getXSDSchema_SchemaLocation())
            {
              this.fireNotifyChanged(msg);
              return;
            }

            super.notifyChanged(msg);
          }
        };
    }

    return xsdSchemaItemProvider;
  }

  @Override
  public Adapter createXSDElementDeclarationAdapter()
  {
    if (xsdElementDeclarationItemProvider == null)
    {
      xsdElementDeclarationItemProvider = 
        new XSDElementDeclarationItemProvider(this)
        {
          @Override
          public Collection<? extends EStructuralFeature> getChildrenFeatures(Object object)
          {
            if (childrenFeatures == null)
            {
              super.getChildrenFeatures(object);
              childrenFeatures.remove(xsdPackage.getXSDElementDeclaration_AnonymousTypeDefinition());
              childrenFeatures.add(1, xsdPackage.getXSDElementDeclaration_TypeDefinition());
            }
            return childrenFeatures;
          }

          @Override
          public Object getParent(Object object)
          {
            Object result = super.getParent(object);
            if (result instanceof XSDSchema)
            {
              return ((List<?>)new AdapterFactoryItemDelegator(XSDSemanticItemProviderAdapterFactory.this).getChildren(result)).get(0);
            }
            else
            {
              return result;
            }
          }

          @Override
          public String getText(Object object)
          {
            XSDElementDeclaration xsdElementDeclaration = ((XSDElementDeclaration)object);
            XSDElementDeclaration resolvedXSDElementDeclaration = xsdElementDeclaration.getResolvedElementDeclaration();
            return resolvedXSDElementDeclaration.getName();
          }
        };
    }

    return xsdElementDeclarationItemProvider;
  }

  @Override
  public Adapter createXSDAttributeDeclarationAdapter()
  {
    if (xsdAttributeDeclarationItemProvider == null)
    {
      xsdAttributeDeclarationItemProvider = 
        new XSDAttributeDeclarationItemProvider(this)
        {
          @Override
          public Collection<? extends EStructuralFeature> getChildrenFeatures(Object object)
          {
            if (childrenFeatures == null)
            {
              super.getChildrenFeatures(object);
              childrenFeatures.remove(xsdPackage.getXSDAttributeDeclaration_AnonymousTypeDefinition());
              childrenFeatures.add(1, xsdPackage.getXSDAttributeDeclaration_TypeDefinition());
            }
            return childrenFeatures;
          }
          @Override
          public Object getParent(Object object)
          {
            Object result = super.getParent(object);
            if (result instanceof XSDSchema)
            {
              return ((List<?>)new AdapterFactoryItemDelegator(XSDSemanticItemProviderAdapterFactory.this).getChildren(result)).get(1);
            }

            return result;
          }

          @Override
          public String getText(Object object)
          {
            XSDAttributeDeclaration xsdAttributeDeclaration = ((XSDAttributeDeclaration)object);
            XSDAttributeDeclaration resolvedXSDAttributeDeclaration = xsdAttributeDeclaration.getResolvedAttributeDeclaration();
            String result = resolvedXSDAttributeDeclaration.getName();
            return result == null ? XSDEditPlugin.INSTANCE.getString("_UI_Absent_label") : result;
          }
        };
    }

    return xsdAttributeDeclarationItemProvider;
  }

  @Override
  public Adapter createXSDAttributeGroupDefinitionAdapter()
  {
    if (xsdAttributeGroupDefinitionItemProvider == null)
    {
      xsdAttributeGroupDefinitionItemProvider = 
        new XSDAttributeGroupDefinitionItemProvider(this)
        {
          @Override
          public Collection<? extends EStructuralFeature> getChildrenFeatures(Object object)
          {
            if (childrenFeatures == null)
            {
              super.getChildrenFeatures(object);
              childrenFeatures.remove(xsdPackage.getXSDAttributeGroupDefinition_Contents());
              childrenFeatures.add(1, xsdPackage.getXSDAttributeGroupDefinition_AttributeUses());
              childrenFeatures.remove(xsdPackage.getXSDAttributeGroupDefinition_AttributeWildcardContent());
              childrenFeatures.add(xsdPackage.getXSDAttributeGroupDefinition_AttributeWildcard());
            }
            return childrenFeatures;
          }
          @Override
          public Object getParent(Object object)
          {
            Object result = super.getParent(object);
            if (result instanceof XSDSchema)
            {
              return ((List<?>)new AdapterFactoryItemDelegator(XSDSemanticItemProviderAdapterFactory.this).getChildren(result)).get(2);
            }
            else
            {
              return result;
            }
          }
        };
    }

    return xsdAttributeGroupDefinitionItemProvider;
  }

  @Override
  public Adapter createXSDComplexTypeDefinitionAdapter()
  {
    if (xsdComplexTypeDefinitionItemProvider == null)
    {
      xsdComplexTypeDefinitionItemProvider = 
        new XSDComplexTypeDefinitionItemProvider(this)
        {
          @Override
          public Collection<? extends EStructuralFeature> getChildrenFeatures(Object object)
          {
            if (childrenFeatures == null)
            {
              super.getChildrenFeatures(object);
              childrenFeatures.remove(xsdPackage.getXSDComplexTypeDefinition_AttributeContents());
              childrenFeatures.remove(xsdPackage.getXSDComplexTypeDefinition_Content());
              childrenFeatures.remove(xsdPackage.getXSDComplexTypeDefinition_AttributeWildcardContent());
              childrenFeatures.add(1, xsdPackage.getXSDComplexTypeDefinition_ContentType());
              childrenFeatures.add(1, xsdPackage.getXSDComplexTypeDefinition_BaseTypeDefinition());
              childrenFeatures.add(xsdPackage.getXSDComplexTypeDefinition_AttributeUses());
              childrenFeatures.add(xsdPackage.getXSDComplexTypeDefinition_AttributeWildcard());
            }

            return childrenFeatures;
          }
          @Override
          public Object getParent(Object object)
          {
            Object result = super.getParent(object);
            if (result instanceof XSDSchema)
            {
              return ((List<?>)new AdapterFactoryItemDelegator(XSDSemanticItemProviderAdapterFactory.this).getChildren(result)).get(3);
            }
            else
            {
              return result;
            }
          }
          @Override
          public String getText(Object object)
          {
            return getText(object, false);
          }
        };
    }

    return xsdComplexTypeDefinitionItemProvider;
  }

  @Override
  public Adapter createXSDSimpleTypeDefinitionAdapter()
  {
    if (xsdSimpleTypeDefinitionItemProvider == null)
    {
      xsdSimpleTypeDefinitionItemProvider = 
        new XSDSimpleTypeDefinitionItemProvider(this)
        {
          @Override
          public Collection<? extends EStructuralFeature> getChildrenFeatures(Object object)
          {
            if (childrenFeatures == null)
            {
              super.getChildrenFeatures(object);
              childrenFeatures.remove(xsdPackage.getXSDSimpleTypeDefinition_Contents());
              childrenFeatures.remove(xsdPackage.getXSDSimpleTypeDefinition_FacetContents());
              childrenFeatures.add(1, xsdPackage.getXSDSimpleTypeDefinition_MemberTypeDefinitions());
              childrenFeatures.add(1, xsdPackage.getXSDSimpleTypeDefinition_ItemTypeDefinition());
              childrenFeatures.add(1, xsdPackage.getXSDSimpleTypeDefinition_BaseTypeDefinition());
              childrenFeatures.add(xsdPackage.getXSDSimpleTypeDefinition_Facets());
            }
            return childrenFeatures;
          }

          @Override
          public Object getParent(Object object)
          {
            Object result = super.getParent(object);
            if (result instanceof XSDSchema)
            {
              return ((List<?>)new AdapterFactoryItemDelegator(XSDSemanticItemProviderAdapterFactory.this).getChildren(result)).get(3);
            }
            else
            {
              return result;
            }
          }
          @Override
          public String getText(Object object)
          {
            return getText(object, false);
          }
        };
    }

    return xsdSimpleTypeDefinitionItemProvider;
  }

  @Override
  public Adapter createXSDModelGroupDefinitionAdapter()
  {
    if (xsdModelGroupDefinitionItemProvider == null)
    {
      xsdModelGroupDefinitionItemProvider = 
        new XSDModelGroupDefinitionItemProvider(this)
        {
          @Override
          public Object getParent(Object object)
          {
            Object result = super.getParent(object);
            if (result instanceof XSDSchema)
            {
              return ((List<?>)new AdapterFactoryItemDelegator(XSDSemanticItemProviderAdapterFactory.this).getChildren(result)).get(4);
            }
            else
            {
              return result;
            }
          }
        };
    }

    return xsdModelGroupDefinitionItemProvider;
  }

  @Override
  public Adapter createXSDNotationDeclarationAdapter()
  {
    if (xsdNotationDeclarationItemProvider == null)
    {
      xsdNotationDeclarationItemProvider = 
        new XSDNotationDeclarationItemProvider(this)
        {
          @Override
          public Object getParent(Object object)
          {
            Object result = super.getParent(object);
            if (result instanceof XSDSchema)
            {
              return ((List<?>)new AdapterFactoryItemDelegator(XSDSemanticItemProviderAdapterFactory.this).getChildren(result)).get(5);
            }
            else
            {
              return result;
            }
          }
        };
    }

    return xsdNotationDeclarationItemProvider;
  }

  @Override
  public Adapter createXSDAnnotationAdapter()
  {
    if (xsdAnnotationItemProvider == null)
    {
      xsdAnnotationItemProvider = 
        new XSDAnnotationItemProvider(this)
        {
          @Override
          public Object getParent(Object object)
          {
            Object result = super.getParent(object);
            if (result instanceof XSDSchema)
            {
              return ((List<?>)new AdapterFactoryItemDelegator(XSDSemanticItemProviderAdapterFactory.this).getChildren(result)).get(7);
            }
            else
            {
              return result;
            }
          }
        };
    }

    return xsdAnnotationItemProvider;
  }

  @Override
  public Adapter createXSDParticleAdapter()
  {
    if (xsdParticleItemProvider == null)
    {
      xsdParticleItemProvider = 
        new XSDParticleItemProvider(this)
        {
          @Override
          protected XSDParticleContent getDelegate(XSDParticle xsdParticle)
          {
            return xsdParticle.getTerm();
          }
        };
    }

    return xsdParticleItemProvider;
  }

  @Override
  public Adapter createXSDModelGroupAdapter()
  {
    if (xsdModelGroupItemProvider == null)
    {
      xsdModelGroupItemProvider = 
        new XSDModelGroupItemProvider(this)
        {
          @Override
          public Collection<? extends EStructuralFeature> getChildrenFeatures(Object object)
          {
            if (childrenFeatures == null)
            {
              super.getChildrenFeatures(object);
              childrenFeatures.remove(xsdPackage.getXSDModelGroup_Contents());
              childrenFeatures.add(1, xsdPackage.getXSDModelGroup_Particles());
            }
            return childrenFeatures;
          }
        };
    }

    return xsdModelGroupItemProvider;
  }



  @Override
  public Adapter createXSDCardinalityFacetAdapter()
  {
    if (xsdCardinalityFacetItemProvider == null)
    {
      xsdCardinalityFacetItemProvider = new XSDCardinalityFacetItemProvider(this);
    }

    return xsdCardinalityFacetItemProvider;
  }


  @Override
  public Adapter createXSDNumericFacetAdapter()
  {
    if (xsdNumericFacetItemProvider == null)
    {
      xsdNumericFacetItemProvider = new XSDNumericFacetItemProvider(this);
    }

    return xsdNumericFacetItemProvider;
  }


  @Override
  public Adapter createXSDOrderedFacetAdapter()
  {
    if (xsdOrderedFacetItemProvider == null)
    {
      xsdOrderedFacetItemProvider = new XSDOrderedFacetItemProvider(this);
    }

    return xsdOrderedFacetItemProvider;
  }

  @Override
  public Adapter createXSDFractionDigitsFacetAdapter()
  {
    if (xsdFractionDigitsFacetItemProvider == null)
    {
      xsdFractionDigitsFacetItemProvider = new XSDFractionDigitsFacetItemProvider(this);
    }

    return xsdFractionDigitsFacetItemProvider;
  }


  @Override
  public Adapter createXSDLengthFacetAdapter()
  {
    if (xsdLengthFacetItemProvider == null)
    {
      xsdLengthFacetItemProvider = new XSDLengthFacetItemProvider(this);
    }

    return xsdLengthFacetItemProvider;
  }


  @Override
  public Adapter createXSDMaxExclusiveFacetAdapter()
  {
    if (xsdMaxExclusiveFacetItemProvider == null)
    {
      xsdMaxExclusiveFacetItemProvider = new XSDMaxExclusiveFacetItemProvider(this);
    }

    return xsdMaxExclusiveFacetItemProvider;
  }


  @Override
  public Adapter createXSDMaxInclusiveFacetAdapter()
  {
    if (xsdMaxInclusiveFacetItemProvider == null)
    {
      xsdMaxInclusiveFacetItemProvider = new XSDMaxInclusiveFacetItemProvider(this);
    }

    return xsdMaxInclusiveFacetItemProvider;
  }


  @Override
  public Adapter createXSDMaxLengthFacetAdapter()
  {
    if (xsdMaxLengthFacetItemProvider == null)
    {
      xsdMaxLengthFacetItemProvider = new XSDMaxLengthFacetItemProvider(this);
    }

    return xsdMaxLengthFacetItemProvider;
  }


  @Override
  public Adapter createXSDMinExclusiveFacetAdapter()
  {
    if (xsdMinExclusiveFacetItemProvider == null)
    {
      xsdMinExclusiveFacetItemProvider = new XSDMinExclusiveFacetItemProvider(this);
    }

    return xsdMinExclusiveFacetItemProvider;
  }


  @Override
  public Adapter createXSDMinInclusiveFacetAdapter()
  {
    if (xsdMinInclusiveFacetItemProvider == null)
    {
      xsdMinInclusiveFacetItemProvider = new XSDMinInclusiveFacetItemProvider(this);
    }

    return xsdMinInclusiveFacetItemProvider;
  }


  @Override
  public Adapter createXSDMinLengthFacetAdapter()
  {
    if (xsdMinLengthFacetItemProvider == null)
    {
      xsdMinLengthFacetItemProvider = new XSDMinLengthFacetItemProvider(this);
    }

    return xsdMinLengthFacetItemProvider;
  }


  @Override
  public Adapter createXSDTotalDigitsFacetAdapter()
  {
    if (xsdTotalDigitsFacetItemProvider == null)
    {
      xsdTotalDigitsFacetItemProvider = new XSDTotalDigitsFacetItemProvider(this);
    }

    return xsdTotalDigitsFacetItemProvider;
  }


  @Override
  public Adapter createXSDWhiteSpaceFacetAdapter()
  {
    if (xsdWhiteSpaceFacetItemProvider == null)
    {
      xsdWhiteSpaceFacetItemProvider = new XSDWhiteSpaceFacetItemProvider(this);
    }

    return xsdWhiteSpaceFacetItemProvider;
  }


  @Override
  public Adapter createXSDRepeatableFacetAdapter()
  {
    if (xsdRepeatableFacetItemProvider == null)
    {
      xsdRepeatableFacetItemProvider = new XSDRepeatableFacetItemProvider(this);
    }

    return xsdRepeatableFacetItemProvider;
  }


  @Override
  public Adapter createXSDEnumerationFacetAdapter()
  {
    if (xsdEnumerationFacetItemProvider == null)
    {
      xsdEnumerationFacetItemProvider = 
        new XSDEnumerationFacetItemProvider(this)
        {
          @Override
          public Collection<? extends EStructuralFeature> getChildrenFeatures(Object object)
          {
            if (childrenFeatures == null)
            {
              super.getChildrenFeatures(object);
              childrenFeatures.remove(xsdPackage.getXSDFacet_Annotation());
              childrenFeatures.add(xsdPackage.getXSDRepeatableFacet_Annotations());
            }
            return childrenFeatures;
          }
        };
    }

    return xsdEnumerationFacetItemProvider;
  }


  @Override
  public Adapter createXSDPatternFacetAdapter()
  {
    if (xsdPatternFacetItemProvider == null)
    {
      xsdPatternFacetItemProvider = 
        new XSDPatternFacetItemProvider(this)
        {
          @Override
          public Collection<? extends EStructuralFeature> getChildrenFeatures(Object object)
          {
            if (childrenFeatures == null)
            {
              super.getChildrenFeatures(object);
              childrenFeatures.remove(xsdPackage.getXSDFacet_Annotation());
              childrenFeatures.add(xsdPackage.getXSDRepeatableFacet_Annotations());
            }
            return childrenFeatures;
          }
        };
    } 
    return xsdPatternFacetItemProvider;
  }

  @Override
  public Adapter createXSDAttributeUseAdapter()
  {
    if (xsdAttributeUseItemProvider == null)
    {
      xsdAttributeUseItemProvider = 
        new XSDAttributeUseItemProvider(this)
        {
          @Override
          public XSDAttributeDeclaration getDelegate(XSDAttributeUse xsdAttributeUse)
          {
            return xsdAttributeUse.getAttributeDeclaration();
          }
        };
    }

    return xsdAttributeUseItemProvider;
  }

  @Override
  public Adapter createXSDWildcardAdapter()
  {
    if (xsdWildcardItemProvider == null)
    {
      xsdWildcardItemProvider = 
        new XSDWildcardItemProvider(this)
        {
          @Override
          public Collection<? extends EStructuralFeature> getChildrenFeatures(Object object)
          {
            if (childrenFeatures == null)
            {
              super.getChildrenFeatures(object);
              childrenFeatures.remove(xsdPackage.getXSDWildcard_Annotation());
              childrenFeatures.add(xsdPackage.getXSDWildcard_Annotations());
            }
            return childrenFeatures;
          }
          @Override
          public String getText(Object object)
          {
            XSDWildcard xsdWildcard = (XSDWildcard)object;
            if (XSDNamespaceConstraintCategory.ANY_LITERAL == xsdWildcard.getNamespaceConstraintCategory())
            {
              return "any";
            }
            else if (XSDNamespaceConstraintCategory.NOT_LITERAL == xsdWildcard.getNamespaceConstraintCategory())
            {
              return "not " + xsdWildcard.getStringNamespaceConstraint();
            }
            else
            {
              return xsdWildcard.getStringNamespaceConstraint();
            }
          }
        };
    }

    return xsdWildcardItemProvider;
  }

  @Override
  public Adapter createXSDIdentityConstraintDefinitionAdapter()
  {
    if (xsdIdentityConstraintDefinitionItemProvider == null)
    {
      xsdIdentityConstraintDefinitionItemProvider = new XSDIdentityConstraintDefinitionItemProvider(this);
    }

    return xsdIdentityConstraintDefinitionItemProvider;
  }

  @Override
  public Adapter createXSDXPathDefinitionAdapter()
  {
    if (xsdxPathDefinitionItemProvider == null)
    {
      xsdxPathDefinitionItemProvider = new XSDXPathDefinitionItemProvider(this);
    }

    return xsdxPathDefinitionItemProvider;
  }

  @Override
  public Adapter createXSDRedefineAdapter()
  {
    if (xsdRedefineItemProvider == null)
    {
      xsdRedefineItemProvider = new XSDRedefineItemProvider(this);
    }

    return xsdRedefineItemProvider;
  }

  @Override
  public Adapter createXSDImportAdapter()
  {
    if (xsdImportItemProvider == null)
    {
      xsdImportItemProvider = new XSDImportItemProvider(this);
    }

    return xsdImportItemProvider;
  }

  @Override
  public Adapter createXSDIncludeAdapter()
  {
    if (xsdIncludeItemProvider == null)
    {
      xsdIncludeItemProvider = new XSDIncludeItemProvider(this);
    }

    return xsdIncludeItemProvider;
  }

  /**
   * This creates an adapter for a {@link org.eclipse.xsd.XSDDiagnostic}.
   */
  @Override
  public Adapter createXSDDiagnosticAdapter()
  {
    if (xsdDiagnosticItemProvider == null)
    {
      xsdDiagnosticItemProvider = new XSDDiagnosticItemProvider(this);
    }

    return xsdDiagnosticItemProvider;
  }

  /**
   * This creates an adapter for a {@link org.eclipse.xsd.XSDMinFacet}.
   */
  @Override
  public Adapter createXSDMinFacetAdapter()
  {
    if (xsdMinFacetItemProvider == null)
    {
      xsdMinFacetItemProvider = new XSDMinFacetItemProvider(this);
    }

    return xsdMinFacetItemProvider;
  }

  /**
   * This creates an adapter for a {@link org.eclipse.xsd.XSDMaxFacet}.
   */
  @Override
  public Adapter createXSDMaxFacetAdapter()
  {
    if (xsdMaxFacetItemProvider == null)
    {
      xsdMaxFacetItemProvider = new XSDMaxFacetItemProvider(this);
    }

    return xsdMaxFacetItemProvider;
  }
}
