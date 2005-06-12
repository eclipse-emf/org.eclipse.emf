/**
 * <copyright>
 *
 * Copyright (c) 2002-2004 IBM Corporation and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: 
 *   IBM - Initial API and implementation
 *
 * </copyright>
 *
 * $Id: XSDSemanticItemProviderAdapterFactory.java,v 1.4 2005/06/12 12:34:45 emerks Exp $
 */
package org.eclipse.xsd.provider;


import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.impl.NotificationImpl;
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

  public Adapter createXSDSchemaAdapter()
  {
    // if (xsdSchemaItemProvider == null)
    {
      xsdSchemaItemProvider = 
        new XSDSchemaItemProvider(this)
        {
          protected List children;

          public Collection getChildrenFeatures(Object object)
          {
            return Collections.EMPTY_LIST;
          }

          public Collection getChildren(Object object)
          {
            if (children == null)
            {
              final XSDSchema xsdSchema = (XSDSchema)object;
              class ChildItemProvider extends ItemProvider
              {
                Collection children;
                public ChildItemProvider(String label, Object image, Collection children)
                {
                  super(label, image, xsdSchema);
                  this.children = children;
                }
                public boolean hasChildren(Object o)
                {
                  return !children.isEmpty();
                }
                public Collection getChildren(Object o)
                {
                  return children;
                }
              }

              children = new ArrayList();
              children.add
                (new ChildItemProvider
                  (XSDEditPlugin.getString("_UI_Elements_label"), 
                    XSDEditPlugin.getImage("full/obj16/XSDElementDeclaration"),
                    xsdSchema.getElementDeclarations()));
              children.add
                (new ChildItemProvider
                  (XSDEditPlugin.getString("_UI_Attributes_label"),
                    XSDEditPlugin.getImage("full/obj16/XSDAttributeDeclaration"),
                    xsdSchema.getAttributeDeclarations()));
              children.add
                (new ChildItemProvider
                  (XSDEditPlugin.getString("_UI_AttributeGroups_label"), 
                    XSDEditPlugin.getImage("full/obj16/XSDAttributeGroupDefinition"),
                    xsdSchema.getAttributeGroupDefinitions()));
              children.add
                (new ChildItemProvider
                  (XSDEditPlugin.getString("_UI_Types_label"), 
                    XSDEditPlugin.getImage("full/obj16/XSDTypeDefinition"),
                    xsdSchema.getTypeDefinitions()));
              children.add
                (new ChildItemProvider
                  (XSDEditPlugin.getString("_UI_ModelGroups_label"), 
                    XSDEditPlugin.getImage("full/obj16/XSDModelGroupDefinition"),
                    xsdSchema.getModelGroupDefinitions()));
              children.add
                (new ChildItemProvider
                  (XSDEditPlugin.getString("_UI_Notations_label"), 
                    XSDEditPlugin.getImage("full/obj16/XSDNotationDeclaration"),
                    xsdSchema.getNotationDeclarations()));
              children.add
                (new ChildItemProvider
                  (XSDEditPlugin.getString("_UI_IdentityConstraints_label"), 
                    XSDEditPlugin.getImage("full/obj16/XSDIdentityConstraintDefinitionKey"),
                    xsdSchema.getIdentityConstraintDefinitions()));
              children.add
                (new ChildItemProvider
                  (XSDEditPlugin.getString("_UI_Annotations_label"), 
                    XSDEditPlugin.getImage("full/obj16/XSDAnnotation"),
                    xsdSchema.getAnnotations()));
            }
            return children;
          }

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

              public Object getNotifier()
              {
                return category;
              }
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

  public Adapter createXSDElementDeclarationAdapter()
  {
    if (xsdElementDeclarationItemProvider == null)
    {
      xsdElementDeclarationItemProvider = 
        new XSDElementDeclarationItemProvider(this)
        {
          public Collection getChildrenFeatures(Object object)
          {
            if (childrenFeatures == null)
            {
              super.getChildrenFeatures(object);
              childrenFeatures.remove(xsdPackage.getXSDElementDeclaration_AnonymousTypeDefinition());
              childrenFeatures.add(1, xsdPackage.getXSDElementDeclaration_TypeDefinition());
            }
            return childrenFeatures;
          }

          public Object getParent(Object object)
          {
            Object result = super.getParent(object);
            if (result instanceof XSDSchema)
            {
              return ((List)new AdapterFactoryItemDelegator(XSDSemanticItemProviderAdapterFactory.this).getChildren(result)).get(0);
            }
            else
            {
              return result;
            }
          }

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

  public Adapter createXSDAttributeDeclarationAdapter()
  {
    if (xsdAttributeDeclarationItemProvider == null)
    {
      xsdAttributeDeclarationItemProvider = 
        new XSDAttributeDeclarationItemProvider(this)
        {
          public Collection getChildrenFeatures(Object object)
          {
            if (childrenFeatures == null)
            {
              super.getChildrenFeatures(object);
              childrenFeatures.remove(xsdPackage.getXSDAttributeDeclaration_AnonymousTypeDefinition());
              childrenFeatures.add(1, xsdPackage.getXSDAttributeDeclaration_TypeDefinition());
            }
            return childrenFeatures;
          }
          public Object getParent(Object object)
          {
            Object result = super.getParent(object);
            if (result instanceof XSDSchema)
            {
              return ((List)new AdapterFactoryItemDelegator(XSDSemanticItemProviderAdapterFactory.this).getChildren(result)).get(1);
            }

            return result;
          }

          public String getText(Object object)
          {
            XSDAttributeDeclaration xsdAttributeDeclaration = ((XSDAttributeDeclaration)object);
            XSDAttributeDeclaration resolvedXSDAttributeDeclaration = xsdAttributeDeclaration.getResolvedAttributeDeclaration();
            String result = resolvedXSDAttributeDeclaration.getName();
            return result == null ? XSDEditPlugin.getString("_UI_Absent_label") : result;
          }
        };
    }

    return xsdAttributeDeclarationItemProvider;
  }

  public Adapter createXSDAttributeGroupDefinitionAdapter()
  {
    if (xsdAttributeGroupDefinitionItemProvider == null)
    {
      xsdAttributeGroupDefinitionItemProvider = 
        new XSDAttributeGroupDefinitionItemProvider(this)
        {
          public Collection getChildrenFeatures(Object object)
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
          public Object getParent(Object object)
          {
            Object result = super.getParent(object);
            if (result instanceof XSDSchema)
            {
              return ((List)new AdapterFactoryItemDelegator(XSDSemanticItemProviderAdapterFactory.this).getChildren(result)).get(2);
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

  public Adapter createXSDComplexTypeDefinitionAdapter()
  {
    if (xsdComplexTypeDefinitionItemProvider == null)
    {
      xsdComplexTypeDefinitionItemProvider = 
        new XSDComplexTypeDefinitionItemProvider(this)
        {
          public Collection getChildrenFeatures(Object object)
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
          public Object getParent(Object object)
          {
            Object result = super.getParent(object);
            if (result instanceof XSDSchema)
            {
              return ((List)new AdapterFactoryItemDelegator(XSDSemanticItemProviderAdapterFactory.this).getChildren(result)).get(3);
            }
            else
            {
              return result;
            }
          }
          public String getText(Object object)
          {
            return getText(object, false);
          }
        };
    }

    return xsdComplexTypeDefinitionItemProvider;
  }

  public Adapter createXSDSimpleTypeDefinitionAdapter()
  {
    if (xsdSimpleTypeDefinitionItemProvider == null)
    {
      xsdSimpleTypeDefinitionItemProvider = 
        new XSDSimpleTypeDefinitionItemProvider(this)
        {
          public Collection getChildrenFeatures(Object object)
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

          public Object getParent(Object object)
          {
            Object result = super.getParent(object);
            if (result instanceof XSDSchema)
            {
              return ((List)new AdapterFactoryItemDelegator(XSDSemanticItemProviderAdapterFactory.this).getChildren(result)).get(3);
            }
            else
            {
              return result;
            }
          }
          public String getText(Object object)
          {
            return getText(object, false);
          }
        };
    }

    return xsdSimpleTypeDefinitionItemProvider;
  }

  public Adapter createXSDModelGroupDefinitionAdapter()
  {
    if (xsdModelGroupDefinitionItemProvider == null)
    {
      xsdModelGroupDefinitionItemProvider = 
        new XSDModelGroupDefinitionItemProvider(this)
        {
          public Object getParent(Object object)
          {
            Object result = super.getParent(object);
            if (result instanceof XSDSchema)
            {
              return ((List)new AdapterFactoryItemDelegator(XSDSemanticItemProviderAdapterFactory.this).getChildren(result)).get(4);
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

  public Adapter createXSDNotationDeclarationAdapter()
  {
    if (xsdNotationDeclarationItemProvider == null)
    {
      xsdNotationDeclarationItemProvider = 
        new XSDNotationDeclarationItemProvider(this)
        {
          public Object getParent(Object object)
          {
            Object result = super.getParent(object);
            if (result instanceof XSDSchema)
            {
              return ((List)new AdapterFactoryItemDelegator(XSDSemanticItemProviderAdapterFactory.this).getChildren(result)).get(5);
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

  public Adapter createXSDAnnotationAdapter()
  {
    if (xsdAnnotationItemProvider == null)
    {
      xsdAnnotationItemProvider = 
        new XSDAnnotationItemProvider(this)
        {
          public Object getParent(Object object)
          {
            Object result = super.getParent(object);
            if (result instanceof XSDSchema)
            {
              return ((List)new AdapterFactoryItemDelegator(XSDSemanticItemProviderAdapterFactory.this).getChildren(result)).get(7);
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

  public Adapter createXSDParticleAdapter()
  {
    if (xsdParticleItemProvider == null)
    {
      xsdParticleItemProvider = 
        new XSDParticleItemProvider(this)
        {
          protected XSDParticleContent getDelegate(XSDParticle xsdParticle)
          {
            return xsdParticle.getTerm();
          }
        };
    }

    return xsdParticleItemProvider;
  }

  public Adapter createXSDModelGroupAdapter()
  {
    if (xsdModelGroupItemProvider == null)
    {
      xsdModelGroupItemProvider = 
        new XSDModelGroupItemProvider(this)
        {
          public Collection getChildrenFeatures(Object object)
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



  public Adapter createXSDCardinalityFacetAdapter()
  {
    if (xsdCardinalityFacetItemProvider == null)
    {
      xsdCardinalityFacetItemProvider = new XSDCardinalityFacetItemProvider(this);
    }

    return xsdCardinalityFacetItemProvider;
  }


  public Adapter createXSDNumericFacetAdapter()
  {
    if (xsdNumericFacetItemProvider == null)
    {
      xsdNumericFacetItemProvider = new XSDNumericFacetItemProvider(this);
    }

    return xsdNumericFacetItemProvider;
  }


  public Adapter createXSDOrderedFacetAdapter()
  {
    if (xsdOrderedFacetItemProvider == null)
    {
      xsdOrderedFacetItemProvider = new XSDOrderedFacetItemProvider(this);
    }

    return xsdOrderedFacetItemProvider;
  }

  public Adapter createXSDFractionDigitsFacetAdapter()
  {
    if (xsdFractionDigitsFacetItemProvider == null)
    {
      xsdFractionDigitsFacetItemProvider = new XSDFractionDigitsFacetItemProvider(this);
    }

    return xsdFractionDigitsFacetItemProvider;
  }


  public Adapter createXSDLengthFacetAdapter()
  {
    if (xsdLengthFacetItemProvider == null)
    {
      xsdLengthFacetItemProvider = new XSDLengthFacetItemProvider(this);
    }

    return xsdLengthFacetItemProvider;
  }


  public Adapter createXSDMaxExclusiveFacetAdapter()
  {
    if (xsdMaxExclusiveFacetItemProvider == null)
    {
      xsdMaxExclusiveFacetItemProvider = new XSDMaxExclusiveFacetItemProvider(this);
    }

    return xsdMaxExclusiveFacetItemProvider;
  }


  public Adapter createXSDMaxInclusiveFacetAdapter()
  {
    if (xsdMaxInclusiveFacetItemProvider == null)
    {
      xsdMaxInclusiveFacetItemProvider = new XSDMaxInclusiveFacetItemProvider(this);
    }

    return xsdMaxInclusiveFacetItemProvider;
  }


  public Adapter createXSDMaxLengthFacetAdapter()
  {
    if (xsdMaxLengthFacetItemProvider == null)
    {
      xsdMaxLengthFacetItemProvider = new XSDMaxLengthFacetItemProvider(this);
    }

    return xsdMaxLengthFacetItemProvider;
  }


  public Adapter createXSDMinExclusiveFacetAdapter()
  {
    if (xsdMinExclusiveFacetItemProvider == null)
    {
      xsdMinExclusiveFacetItemProvider = new XSDMinExclusiveFacetItemProvider(this);
    }

    return xsdMinExclusiveFacetItemProvider;
  }


  public Adapter createXSDMinInclusiveFacetAdapter()
  {
    if (xsdMinInclusiveFacetItemProvider == null)
    {
      xsdMinInclusiveFacetItemProvider = new XSDMinInclusiveFacetItemProvider(this);
    }

    return xsdMinInclusiveFacetItemProvider;
  }


  public Adapter createXSDMinLengthFacetAdapter()
  {
    if (xsdMinLengthFacetItemProvider == null)
    {
      xsdMinLengthFacetItemProvider = new XSDMinLengthFacetItemProvider(this);
    }

    return xsdMinLengthFacetItemProvider;
  }


  public Adapter createXSDTotalDigitsFacetAdapter()
  {
    if (xsdTotalDigitsFacetItemProvider == null)
    {
      xsdTotalDigitsFacetItemProvider = new XSDTotalDigitsFacetItemProvider(this);
    }

    return xsdTotalDigitsFacetItemProvider;
  }


  public Adapter createXSDWhiteSpaceFacetAdapter()
  {
    if (xsdWhiteSpaceFacetItemProvider == null)
    {
      xsdWhiteSpaceFacetItemProvider = new XSDWhiteSpaceFacetItemProvider(this);
    }

    return xsdWhiteSpaceFacetItemProvider;
  }


  public Adapter createXSDRepeatableFacetAdapter()
  {
    if (xsdRepeatableFacetItemProvider == null)
    {
      xsdRepeatableFacetItemProvider = new XSDRepeatableFacetItemProvider(this);
    }

    return xsdRepeatableFacetItemProvider;
  }


  public Adapter createXSDEnumerationFacetAdapter()
  {
    if (xsdEnumerationFacetItemProvider == null)
    {
      xsdEnumerationFacetItemProvider = 
        new XSDEnumerationFacetItemProvider(this)
        {
          public Collection getChildrenFeatures(Object object)
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


  public Adapter createXSDPatternFacetAdapter()
  {
    if (xsdPatternFacetItemProvider == null)
    {
      xsdPatternFacetItemProvider = 
        new XSDPatternFacetItemProvider(this)
        {
          public Collection getChildrenFeatures(Object object)
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

  public Adapter createXSDAttributeUseAdapter()
  {
    if (xsdAttributeUseItemProvider == null)
    {
      xsdAttributeUseItemProvider = 
        new XSDAttributeUseItemProvider(this)
        {
          public XSDAttributeDeclaration getDelegate(XSDAttributeUse xsdAttributeUse)
          {
            return xsdAttributeUse.getAttributeDeclaration();
          }
        };
    }

    return xsdAttributeUseItemProvider;
  }

  public Adapter createXSDWildcardAdapter()
  {
    if (xsdWildcardItemProvider == null)
    {
      xsdWildcardItemProvider = 
        new XSDWildcardItemProvider(this)
        {
          public Collection getChildrenFeatures(Object object)
          {
            if (childrenFeatures == null)
            {
              super.getChildrenFeatures(object);
              childrenFeatures.remove(xsdPackage.getXSDWildcard_Annotation());
              childrenFeatures.add(xsdPackage.getXSDWildcard_Annotations());
            }
            return childrenFeatures;
          }
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

  public Adapter createXSDIdentityConstraintDefinitionAdapter()
  {
    if (xsdIdentityConstraintDefinitionItemProvider == null)
    {
      xsdIdentityConstraintDefinitionItemProvider = new XSDIdentityConstraintDefinitionItemProvider(this);
    }

    return xsdIdentityConstraintDefinitionItemProvider;
  }

  public Adapter createXSDXPathDefinitionAdapter()
  {
    if (xsdxPathDefinitionItemProvider == null)
    {
      xsdxPathDefinitionItemProvider = new XSDXPathDefinitionItemProvider(this);
    }

    return xsdxPathDefinitionItemProvider;
  }

  public Adapter createXSDRedefineAdapter()
  {
    if (xsdRedefineItemProvider == null)
    {
      xsdRedefineItemProvider = new XSDRedefineItemProvider(this);
    }

    return xsdRedefineItemProvider;
  }

  public Adapter createXSDImportAdapter()
  {
    if (xsdImportItemProvider == null)
    {
      xsdImportItemProvider = new XSDImportItemProvider(this);
    }

    return xsdImportItemProvider;
  }

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
  public Adapter createXSDMaxFacetAdapter()
  {
    if (xsdMaxFacetItemProvider == null)
    {
      xsdMaxFacetItemProvider = new XSDMaxFacetItemProvider(this);
    }

    return xsdMaxFacetItemProvider;
  }
}
