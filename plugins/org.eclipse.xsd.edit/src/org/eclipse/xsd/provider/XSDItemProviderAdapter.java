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
import java.util.HashSet;
import java.util.List;

import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.edit.command.CommandParameter;
import org.eclipse.emf.edit.command.CreateChildCommand;
import org.eclipse.emf.edit.provider.AdapterFactoryItemDelegator;
import org.eclipse.emf.edit.provider.IItemPropertyDescriptor;
import org.eclipse.emf.edit.provider.ItemPropertyDescriptor;
import org.eclipse.emf.edit.provider.ItemProviderAdapter;

import org.eclipse.xsd.XSDAnnotation;
import org.eclipse.xsd.XSDAttributeDeclaration;
import org.eclipse.xsd.XSDAttributeGroupDefinition;
import org.eclipse.xsd.XSDAttributeUse;
import org.eclipse.xsd.XSDComplexTypeDefinition;
import org.eclipse.xsd.XSDCompositor;
import org.eclipse.xsd.XSDConcreteComponent;
import org.eclipse.xsd.XSDElementDeclaration;
import org.eclipse.xsd.XSDFactory;
import org.eclipse.xsd.XSDForm;
import org.eclipse.xsd.XSDIdentityConstraintDefinition;
import org.eclipse.xsd.XSDModelGroup;
import org.eclipse.xsd.XSDModelGroupDefinition;
import org.eclipse.xsd.XSDNotationDeclaration;
import org.eclipse.xsd.XSDPackage;
import org.eclipse.xsd.XSDParticle;
import org.eclipse.xsd.XSDParticleContent;
import org.eclipse.xsd.XSDRedefine;
import org.eclipse.xsd.XSDSchema;
import org.eclipse.xsd.XSDSimpleTypeDefinition;
import org.eclipse.xsd.XSDTypeDefinition;
import org.eclipse.xsd.XSDVariety;
import org.eclipse.xsd.XSDWildcard;
import org.eclipse.xsd.XSDXPathDefinition;


/**
 * This is the base for all adapters.
 * It takes handles the {@link #getParent getParent()} implementation that
 * is need for {@link org.eclipse.xsd.provider.XSDParticleItemProvider} 
 * and {@link org.eclipse.xsd.provider.XSDAttributeUseItemProvider} 
 * to skip up to a particle or attribute use.
 * It also supports {@link CreateChildCommand} by supplying text and icons,
 * and provides methods that assist the derived adapters in overriding
 * {@link ItemProviderAdapter#collectNewChildDescriptors}.
 */
public class XSDItemProviderAdapter extends ItemProviderAdapter
  implements CreateChildCommand.Helper
{
  protected static final XSDPackage xsdPackage = XSDPackage.eINSTANCE;
  protected static final XSDFactory xsdFactory = XSDFactory.eINSTANCE;

  protected AdapterFactoryItemDelegator itemDelegator;

  /**
   * This creates an instance from an adapter factory.
   */
  protected XSDItemProviderAdapter(AdapterFactory adapterFactory)
  {
    super(adapterFactory);
    itemDelegator = new AdapterFactoryItemDelegator(adapterFactory);
  }

  /**
   * This returns the parent.
   */
  @Override
  public Object getParent(Object object)
  {
    EObject parent = ((EObject)object).eContainer();
    if (parent != null)
    {
      EObject grandParent = parent.eContainer();
      if (grandParent instanceof XSDParticle ||
          grandParent instanceof XSDAttributeUse)
      {
        return grandParent;
      }
    }
    return parent;
  }

  @Override
  public List<IItemPropertyDescriptor> getPropertyDescriptors(final Object object)
  {
    if (itemPropertyDescriptors == null)
    {
      itemPropertyDescriptors = 
        new ArrayList<IItemPropertyDescriptor>()
        {
          private static final long serialVersionUID = 1L;

          @Override
          public boolean add(IItemPropertyDescriptor o)
          {
            String id = o.getId(object);
            for (IItemPropertyDescriptor propertyDescriptor : this)
            {
              if (id.equals(propertyDescriptor.getId(object)))
              {
                return false;
              }
            }
            return super.add(o);
          }
        };

    }
    return itemPropertyDescriptors;
  }

  public static class ItemPropertyDescriptorWithDefault extends ItemPropertyDescriptor
  {
    public ItemPropertyDescriptorWithDefault
       (AdapterFactory adapterFactory,
        String displayName,
        String description,
        EStructuralFeature feature,
        boolean isSettable,
        Object staticImage)
    {
      super(adapterFactory, displayName, description, feature, isSettable, staticImage);
    }

    @Override
    public Object getPropertyValue(Object o)
    {
      if (feature instanceof EAttribute)
      {
        EObject refObject = (EObject)o;
        EAttribute attribute = (EAttribute)feature;
        if (!attribute.isMany() && !refObject.eIsSet(attribute))
        {
          return createPropertyValueWrapper(o, getPropertyDefaultValue(o));
        }
      }
      return super.getPropertyValue(o);
    }

    @Override
    public void setPropertyValue(Object o, Object value)
    {
      if (feature instanceof EAttribute)
      {
        EAttribute attribute = (EAttribute)feature;
        if (!attribute.isMany())
        {
          Object propertyDefaultValue = getPropertyDefaultValue(o);
          if (propertyDefaultValue.equals(value))
          {
            resetPropertyValue(o);
            return;
          }
        }
      }
      super.setPropertyValue(o, value);
    }

    @Override
    public Collection<?> getChoiceOfValues(Object o)
    {
      Collection<?> result = super.getChoiceOfValues(o);
      if (result != null && feature instanceof EAttribute)
      {
        EAttribute attribute = (EAttribute)feature;
        if (!attribute.isMany())
        {
          List<Object> newResult = new ArrayList<Object>(result);
          Object propertyDefaultValue = getPropertyDefaultValue(o);
          newResult.add(propertyDefaultValue);
          result = newResult;
        }
      }
      return result;
    }

    public Object getPropertyDefaultValue(Object o)
    {
      if (feature instanceof EAttribute)
      {
        EAttribute attribute = (EAttribute)feature;
        if (!attribute.isMany())
        {
          Object result = attribute.getDefaultValue();
          if (result == null)
          {
            result = getDefaultValue(attribute.getEType());
          }
          if (result == null)
          {
            return XSDEditPlugin.INSTANCE.getString("_UI_DefaultValue_label", new Object [] { "" });
          }
          else
          {
            return XSDEditPlugin.INSTANCE.getString("_UI_DefaultValue_label", new Object [] { this.itemDelegator.getText(result) });
          }
        }
      }
      return null;
    }
  }

  /**
   * If <code>object</code> is a particle or an attribute use, this returns
   * its content; otherwise, <code>object</code> itself.
   */
  protected Object getParticleOrAttributeUseContent(Object object)
  {
    if (object instanceof XSDParticle)
    {
      return ((XSDParticle) object).getContent();
    }
    else if (object instanceof XSDAttributeUse)
    {
      return ((XSDAttributeUse) object).getContent();
    }
    return object;
  }

  /**
   * This is a convenience method for creating <code>CommandParameter</code>s
   * for a given parent feature and child object.
   */
  protected CommandParameter createChildParameter(EReference feature,
                                                  XSDConcreteComponent child)
  {
    return new CommandParameter(null, feature, child);
  }

  /**
   * This is a convenience method for creating {@link CommandParameter}s
   * for model groups of two or all of the three compositor types
   * (choice, sequence, and optionally all, depending on the value of
   * <code>all</code>) and adding them to a <code>newChildDescriptors</code>
   * collection.  If <code>useParticle</code> is <code>true</code>, each
   * model group will be the content of a new particle.
   */
  protected void addModelGroupChildParameters(Collection<Object> newChildDescriptors,
                                              EReference feature,
                                              boolean all,
                                              boolean useParticle)
  {
    XSDCompositor[] compositor = { XSDCompositor.ALL_LITERAL, XSDCompositor.CHOICE_LITERAL, XSDCompositor.SEQUENCE_LITERAL };
    for (int i = all ? 0 : 1; i < compositor.length; i++)
    {
      XSDModelGroup mg = xsdFactory.createXSDModelGroup();
      mg.setCompositor(compositor[i]);
      XSDConcreteComponent child = mg;
      if (useParticle)
      {
        child = createParticle(mg, false);
      }
      newChildDescriptors.add(createChildParameter(feature, child));
    }
  }

  /**
   * This is a convenience method for creating {@link CommandParameter}s
   * for simple type definitions of zero, one, two, or all of three
   * varieties (atomic, list, and union), and adding them to a
   * <code>newChildDescriptors</code> collection.  The simple type
   * definition objects are to be added to the specified
   * <code>feature</code> of <code>parent</code>.  The varieties for which
   * to create simple type definitions and command parameters are specified
   * by the three boolean arguments, <code>atomic</code>, <code>list</code>,
   * and <code>union</code>.
   */
  protected void addSimpleTypeDefinitionChildParameters
    (Collection<Object> newChildDescriptors, 
     XSDConcreteComponent parent,
     EReference feature, 
     boolean atomic, 
     boolean list, 
     boolean union)
  {
    XSDSchema xsdSchema = parent.getSchema();
    if (xsdSchema != null)
    {
      XSDSimpleTypeDefinition baseType = parent.getSchema().getSchemaForSchema().resolveSimpleTypeDefinition("string");
  
      if (atomic)
      {
        XSDSimpleTypeDefinition xsdSimpleTypeDefinition = createSimpleTypeDefinition(parent);
        xsdSimpleTypeDefinition.setVariety(XSDVariety.ATOMIC_LITERAL);
        xsdSimpleTypeDefinition.setBaseTypeDefinition(baseType);
        newChildDescriptors.add(createChildParameter(feature, xsdSimpleTypeDefinition));
      }
  
      if (list)
      {
        XSDSimpleTypeDefinition xsdSimpleTypeDefinition = createSimpleTypeDefinition(parent);
        xsdSimpleTypeDefinition.setVariety(XSDVariety.LIST_LITERAL);
        xsdSimpleTypeDefinition.setItemTypeDefinition(baseType);
        newChildDescriptors.add(createChildParameter(feature, xsdSimpleTypeDefinition));
      }
  
      if (union)
      {
        XSDSimpleTypeDefinition xsdSimpleTypeDefinition = createSimpleTypeDefinition(parent);
        xsdSimpleTypeDefinition.setVariety(XSDVariety.UNION_LITERAL);
        xsdSimpleTypeDefinition.getMemberTypeDefinitions().add(baseType);
        newChildDescriptors.add(createChildParameter(feature, xsdSimpleTypeDefinition));
      }
    }
  }

  /**
   * This determines whether an object represents a global element,
   * based on the type of its parent.
   */
  protected boolean isGlobal(Object parent)
  {
    return (parent instanceof XSDSchema || parent instanceof XSDRedefine);
  }

  /**
   * This creates an object of type <code>XSDAttributeDeclaration</code>
   * with a name that is unique globally (compared to other global attribute
   * declarations) or locally (compared to sibling attribute declarations
   * and children of sibling attribute groups), depending on the type of the
   * specified <code>parent</code>.  The created object will be initialized
   * to resolve to itself and to have the <code>string</code> type
   * definition.
   * 
   * <p>Note: in determining local uniqueness, we do not consider any
   * referenced uses of the parent group or type elsewhere in the schema.
   */
  protected XSDAttributeDeclaration createAttributeDeclaration(XSDConcreteComponent parent)
  {
    XSDAttributeDeclaration child = null;
    String baseName = getNewObjectName(xsdPackage.getXSDAttributeDeclaration().getName());
    
    if (isGlobal(parent))
    {
      String name = null;
      int i = 0;
      do
      {
        name = baseName + ((i > 0) ? String.valueOf(i) : "");
        i = (i > 0) ? i + 1 : 1;
        child = parent.resolveAttributeDeclaration(name);
      } while (((EObject) child).eContainer() != null);
    }
    else
    {
      XSDSchema schema = parent.getSchema();

      // use the namespace from the schema in determining local uniqueness
      // only if new local declarations will be qualified by default
      String namespace = null;
      if (schema != null &&
          schema.getAttributeFormDefault() == XSDForm.QUALIFIED_LITERAL)
      {
        namespace = schema.getTargetNamespace();
      }
      Collection<? extends XSDAttributeUse> siblings = getAttributeSiblings(parent);
      String name = null;
      int i = 0;
      do
      {
        name = baseName + ((i > 0) ? String.valueOf(i) : "");
        i = (i > 0) ? i + 1 : 1;
      } while (!isUniqueAttributeDeclarationName(name, namespace, siblings));
      
      child = xsdFactory.createXSDAttributeDeclaration();
      child.setName(name); 
      if (namespace != null)
      {
        child.setTargetNamespace(namespace);
      }
      child.setResolvedAttributeDeclaration(child);
    }
    
    // initialize element type to be string
    child.setTypeDefinition(parent.getSchema().getSchemaForSchema().resolveSimpleTypeDefinition("string"));
    return child;
  }
  
  /**
   * This gathers and returns the siblings by stepping up through any
   * attribute group definitions to the top-most parent attribute definition
   * or complex type definition, and returning the attribute uses for it.
   * Returns <code>null</code> if there is no such parent.
   */
  protected Collection<? extends XSDAttributeUse> getAttributeSiblings(XSDConcreteComponent parent)
  {
    Collection<XSDAttributeUse> siblings = null;
    if (parent instanceof XSDAttributeGroupDefinition)
    {
      XSDAttributeGroupDefinition group = (XSDAttributeGroupDefinition)parent;
      while (group.eContainer() instanceof XSDAttributeGroupDefinition)
      {
        group = (XSDAttributeGroupDefinition) group.eContainer();
      }

      if (group.eContainer() instanceof XSDComplexTypeDefinition)
      {
        parent = (XSDComplexTypeDefinition) group.eContainer();
      }
      else
      {
        siblings = group.getAttributeUses();
      }
    }
    if (parent instanceof XSDComplexTypeDefinition)
    {
      siblings = ((XSDComplexTypeDefinition) parent).getAttributeUses();
    }
    return siblings;
  }

  /**
   * This tests whether the combination of given <code>localName</code> and
   * <code>targetNamespace</code> is shared by an attribute declaration of
   * an attribute use in the given collection.  Returns <code>false</code>
   * if so, <code>true</code> otherwise.  Note that this method is tolerant
   * of nulls: a result of <code>true</code> is returned if
   * <code>attributeUse</code> is <code>null</code>, and two null strings
   * are considered equal.
   */
  protected boolean isUniqueAttributeDeclarationName(String localName, String targetNamespace, Collection<? extends XSDAttributeUse> attributeUses)
  {
    if (attributeUses != null)
    {
      for (XSDAttributeUse attributeUse : attributeUses)
      {
        if (attributeUse.getAttributeDeclaration() != null)
        {
          XSDAttributeDeclaration other = 
            attributeUse.getAttributeDeclaration();
          if (other.hasNameAndTargetNamespace(localName, targetNamespace))
          {
            return false;
          }
        }
      }
    }
    return true;
  }

  /**
   * This creates an object of type <code>XSDElementDeclaration</code>
   * with a name that is unique globally or locally, depending on the type
   * of the specified <code>parent</code>.  The created object will be
   * initialized to resolve to itself and to have the <code>string</code>
   * type definition.
   *
   * <p>Note: in determining local uniqueness, we do not consider any
   * referenced uses of the parent group or type elsewhere in the schema.
   */
  protected XSDElementDeclaration createElementDeclaration(XSDConcreteComponent parent)
  {
    XSDElementDeclaration child = null;
    String baseName = getNewObjectName(xsdPackage.getXSDElementDeclaration().getName());

    if (isGlobal(parent))
    {
      String name = null;
      int i = 0;
      do
      {
        name = baseName + ((i > 0) ? String.valueOf(i) : "");
        i = (i > 0) ? i + 1 : 1;
        child = parent.resolveElementDeclaration(name);
      } while (((EObject) child).eContainer() != null);
    }
    else
    {
      XSDSchema schema = parent.getSchema();

      // use the namespace from the schema in determining local uniqueness
      // only if new local declarations will be qualified by default
      String namespace = null;
      if (schema != null &&
          schema.getElementFormDefault() == XSDForm.QUALIFIED_LITERAL)
      {
        namespace = schema.getTargetNamespace();
      }
      XSDModelGroup modelGroup = getTopModelGroup(parent);
      String name = null;
      int i = 0;
      do
      {
        name = baseName + ((i > 0) ? String.valueOf(i) : "");
        i = (i > 0) ? i + 1 : 1;
      } while (!isUniqueElementDeclarationName(name, namespace, modelGroup, null));
      child = xsdFactory.createXSDElementDeclaration();
      child.setName(name);
      if (namespace != null)
      {
        child.setTargetNamespace(namespace);
      }
      child.setResolvedElementDeclaration(child);
    }

    // initialize attribute type to be string
    child.setTypeDefinition(parent.getSchema().getSchemaForSchema().resolveSimpleTypeDefinition("string"));
    return child;
  }

  /**
   * If the specified parent is a model group, this finds and returns the
   * top-most model group above it (within the same complex type or model
   * group definition).  Otherwise, returns <code>null</code>.
   */
  protected XSDModelGroup getTopModelGroup(XSDConcreteComponent parent)
  {
    XSDModelGroup modelGroup = null;

    if (parent instanceof XSDModelGroup)
    {
      modelGroup = (XSDModelGroup) parent;
      while (parent.eContainer() instanceof XSDParticle ||
             parent.eContainer() instanceof XSDModelGroup)
      {
        parent = (XSDConcreteComponent) parent.eContainer();
        if (parent instanceof XSDModelGroup)
        {
          modelGroup = (XSDModelGroup) parent;
        }
      }
    }
    return modelGroup;
  }

  /**
   * This tests whether the combination of given <code>localName</code>
   * and <code>targetNamespace</code> is shared by an element declaration in
   * the given model group or a model group under it.  Returns
   * <code>false</code> if so, <code>true</code> otherwise.  Note that this
   * method is tolerant of nulls: a result of <code>true</code> is
   * returned if <code>modelGroup</code> is <code>null</code>, and two
   * null strings are considered equal.  Also, note that the
   * <code>visited</code> argument is used to avoid an infinite recursion
   * situation, and should be <code>null</code> when the method is initially
   * called.
   */
  protected boolean isUniqueElementDeclarationName(String localName, String targetNamespace, XSDModelGroup modelGroup, HashSet<XSDModelGroup> visited)
  {
    if (visited == null)
    {
      visited = new HashSet<XSDModelGroup>();
    }

    if (modelGroup != null && visited.add(modelGroup) && modelGroup.getParticles() != null)
    {
      for (XSDParticle particle : modelGroup.getParticles())
      {
        if (particle.getTerm() instanceof XSDElementDeclaration)
        {
          XSDElementDeclaration other = 
            (XSDElementDeclaration) particle.getTerm();
          if (other.hasNameAndTargetNamespace(localName, targetNamespace))
          {
            return false;
          }
        }
        else if (particle.getTerm() instanceof XSDModelGroup)
        {
          XSDModelGroup others = (XSDModelGroup) particle.getTerm();
          if (!isUniqueElementDeclarationName(localName, targetNamespace, others, visited))
          {
            return false;
          }
        }
      }
    }
    return true;
  }

  /**
   * This creates an object of type <code>XSDAttributeGroupDefinition</code>
   * with a name that is unique globally, and that resolves to
   * itself.  The created object is to be added under the specified
   * <code>parent</code>.
   */
  protected XSDAttributeGroupDefinition createAttributeGroupDefinition(XSDConcreteComponent parent)
  {
    XSDAttributeGroupDefinition child = null;
    String name = null;
    String baseName = getNewObjectName(xsdPackage.getXSDAttributeGroupDefinition().getName());
    int i = 0;
    do
    {
      name = baseName + ((i > 0) ? String.valueOf(i) : "");
      i = (i > 0) ? i + 1 : 1;
      child = parent.resolveAttributeGroupDefinition(name);
    } while (((EObject) child).eContainer() != null);

    return child;
  }

  /**
   * This creates an object of type <code>XSDModelGroupDefinition</code>
   * with a name that is unique globally, and that resolves to
   * itself.  The created object is to be added under the specified
   * <code>parent</code>.
   */
  protected XSDModelGroupDefinition createModelGroupDefinition(XSDConcreteComponent parent)
  {
    XSDModelGroupDefinition child = null;
    String name = null;
    String baseName = getNewObjectName(xsdPackage.getXSDModelGroupDefinition().getName());
    int i = 0;
    do
    {
      name = baseName + ((i > 0) ? String.valueOf(i) : "");
      i = (i > 0) ? i + 1 : 1;
      child = parent.resolveModelGroupDefinition(name);
    } while (((EObject) child).eContainer() != null);

    return child;
  }

  /** 
   * This creates an object of type <code>XSDComplexTypeDefinition</code>
   * with a name that is unique globally, if this is to be a global
   * definition; otherwise, with no name.  The created object is to be
   * added under the specified <code>parent</code>.
   */
  protected XSDComplexTypeDefinition createComplexTypeDefinition(XSDConcreteComponent parent)
  {
    XSDComplexTypeDefinition child = null;

    if (isGlobal(parent))
    {
      String name = null;
      String baseName = getNewObjectName(xsdPackage.getXSDComplexTypeDefinition().getName());
      int i = 0;
      do
      {
        name = baseName + ((i > 0) ? String.valueOf(i) : "");
        i = (i > 0) ? i + 1 : 1;
        child = parent.resolveComplexTypeDefinition(name);
      } while (((EObject) child).eContainer() != null);
    }
    else
    {
      child = xsdFactory.createXSDComplexTypeDefinition();
    }
    return child;
  }

  /**
   * This creates an object of type <code>XSDSimpleTypeDefinition</code>
   * with a name that is unique globally, if this is to be a global
   * definition; otherwise, with no name.  The created object is to be
   * added under the specified <code>parent</code>.
   */
  protected XSDSimpleTypeDefinition createSimpleTypeDefinition(XSDConcreteComponent parent)
  {
    XSDSimpleTypeDefinition child = null;

    if (isGlobal(parent))
    {
      String name = null;
      String baseName = getNewObjectName(xsdPackage.getXSDSimpleTypeDefinition().getName());
      int i = 0;
      do
      {
        name = baseName + ((i > 0) ? String.valueOf(i) : "");
        i = (i > 0) ? i + 1 : 1;
        child = parent.resolveSimpleTypeDefinition(name);
      } while (((EObject) child).eContainer() != null);
    }
    else
    {
      child = xsdFactory.createXSDSimpleTypeDefinition();
    }

    return child;
  }

  /**
   * This creates an object of type
   * <code>XSDIdentityConstraintDefinition</code> with a name that is unique
   * globally.  The created object is to be added under the specified
   * <code>parent</code>.
   */
  protected XSDIdentityConstraintDefinition createIdentityConstraintDefinition(XSDConcreteComponent parent)
  {
    XSDIdentityConstraintDefinition child = null;
    String name = null;
    String baseName = getNewObjectName(xsdPackage.getXSDIdentityConstraintDefinition().getName());
    int i = 0;
    do
    {
      name = baseName + ((i > 0) ? String.valueOf(i) : "");
      i = (i > 0) ? i + 1 : 1;
      child = parent.resolveIdentityConstraintDefinition(name);
    } while (((EObject) child).eContainer() != null);

    return child;
  }

  /**
   * This creates an object of type <code>XSDNotationDeclaration</code> with
   * a name that is unique globally.  The created object, to be added
   * under the specified <code>parent</code>, will have an empty string as
   * its public identifier.
   */
  protected XSDNotationDeclaration createNotationDeclaration(XSDConcreteComponent parent)
  {
    XSDNotationDeclaration child = null;
    String name = null;
    String baseName = getNewObjectName(xsdPackage.getXSDNotationDeclaration().getName());
    int i = 0;
    do
    {
      name = baseName + ((i > 0) ? String.valueOf(i) : "");
      i = (i > 0) ? i + 1 : 1;
      child = parent.resolveNotationDeclaration(name);
    } while (((EObject) child).eContainer() != null);

    child.setPublicIdentifier("");
    return child;
  }

  /**
   * This creates an object of type <code>XSDAttributeUse</code> containing
   * an object of type <code>XSDAttributeDeclaration</code> -- if
   * <code>isReference</code> is <code>true</code>, the attribute use
   * content will be a new attribute declaration that resolves to 
   * <code>attributeDeclaration</code>; otherwise, it will be simply
   * <code>attributeDeclaration</code> itself.
   */
  protected XSDAttributeUse createAttributeUse(XSDAttributeDeclaration attributeDeclaration, boolean isReference)
  {
    XSDAttributeUse au = xsdFactory.createXSDAttributeUse();
    if (isReference)
    {
      XSDAttributeDeclaration ref = xsdFactory.createXSDAttributeDeclaration();
      ref.setResolvedAttributeDeclaration(attributeDeclaration);
      au.setContent(ref);
    }
    else
    {
      au.setContent(attributeDeclaration);
    }
    return au;
  }

  /**
   * This creates an object of type <code>XSDAttributeGroupDefinition</code>
   * that resolves to <code>attributeGroupDefinition</code>.
   */
  protected XSDAttributeGroupDefinition createAttributeGroupDefinitionReference(XSDAttributeGroupDefinition attributeGroupDefinition)
  {
    XSDAttributeGroupDefinition ref =
      xsdFactory.createXSDAttributeGroupDefinition();
    ref.setResolvedAttributeGroupDefinition(attributeGroupDefinition);
    return ref;
  }

  /**
   * This creates an object of type <code>XSDParticle</code> containing
   * an object of type <code>XSDParticleContent</code> -- if either
   * <code>particleContent</code> is an element declaration and 
   * <code>isReference</code> is <code>true</code> or
   * <code>particleContent</code> is a model group definition, the particle
   * content will be a new element declaration that resolves to
   * <code>particleContent</code>; otherwise, it will be simply
   * <code>particleContent</code> itself.
   */
  protected XSDParticle createParticle(XSDParticleContent particleContent, boolean isReference)
  {
    XSDParticle p = xsdFactory.createXSDParticle();
    if (particleContent instanceof XSDModelGroupDefinition)
    {
      XSDModelGroupDefinition ref = xsdFactory.createXSDModelGroupDefinition();
      ref.setResolvedModelGroupDefinition((XSDModelGroupDefinition) particleContent);
      p.setContent(ref);
    }
    else if (particleContent instanceof XSDElementDeclaration && isReference)
    {
      XSDElementDeclaration ref = xsdFactory.createXSDElementDeclaration();
      ref.setResolvedElementDeclaration((XSDElementDeclaration) particleContent);
      p.setContent(ref);
    }
    else
    {
      p.setContent(particleContent);

    }
    return p;
  }

  /**
   * This returns the translated name for a new object with the specified
   * type name.
   */
  protected String getNewObjectName(String typeName)
  {
    return XSDEditPlugin.INSTANCE.getString("_UI_" + typeName + "_new_object");
  }

  /**
   * This returns the default result collection for {@link CreateChildCommand}.
   */
  @Override
  public Collection<?> getCreateChildResult(Object child)
  {
    Collection<Object> result = new ArrayList<Object>(1);
    result.add(child);
    return result;
  }

  /**
   * This returns the translated label for {@link CreateChildCommand}.
   */
  @Override
  public String getCreateChildText(Object parent, Object feature,
                                   Object child, Collection<?> selection)
  {
    child = getParticleOrAttributeUseContent(child);

    EReference refFeature = feature instanceof EReference ?
      (EReference) feature : null;
    String qualifier = getTypeTextQualifier(parent, refFeature, child);

    return getTypeText(child, qualifier);
  }

  /**
   * This returns the translated description for {@link CreateChildCommand}.
   */
  @Override
  public String getCreateChildDescription(Object parent, Object feature,
                                          Object child, Collection<?> selection)
  {
    child = getParticleOrAttributeUseContent(child);

    EReference refFeature = feature instanceof EReference ?
      (EReference) feature : null;
    String qualifier = getTypeTextQualifier(parent, refFeature, child);

    Object selectedObject = selection == null || selection.isEmpty() ?
      null : getParticleOrAttributeUseContent(selection.iterator().next());
    if (parent != selectedObject)
    {
      return 
        XSDEditPlugin.INSTANCE.getString
          ("_UI_CreateSibling_description", new Object [] { getTypeText(child, qualifier), getTypeText(selectedObject, null) });
    }
    else
    {
      return 
        XSDEditPlugin.INSTANCE.getString
          ("_UI_CreateChild_description", new Object [] { getTypeText(child, qualifier), getTypeText(parent, null) });
    }
  }

  /**
   * This returns the translated tool tip for {@link CreateChildCommand}.
   */
  @Override
  public String getCreateChildToolTipText(Object parent, Object feature,
                                          Object child, Collection<?> selection)
  {
    child = getParticleOrAttributeUseContent(child);

    EReference refFeature = feature instanceof EReference ?
      (EReference) feature : null;
    String qualifier = getTypeTextQualifier(parent, refFeature, child);

    Object selectedObject = selection == null || selection.isEmpty() ?
      null : getParticleOrAttributeUseContent(selection.iterator().next());
    if (parent != selectedObject)
    {
      return 
        XSDEditPlugin.INSTANCE.getString
          ("_UI_CreateSibling_tooltip", new Object [] { getTypeText(child, qualifier), getTypeText(selectedObject, null) });
    }
    else
    {
      return 
        XSDEditPlugin.INSTANCE.getString
          ("_UI_CreateChild_tooltip", new Object [] { getTypeText(child, qualifier), getTypeText(parent, null) });
    }
  }

  /**
   * This gets the translated string for <code>object</code>'s type,
   * qualified by <code>qualifier</code>, if it is non-null.
   */
  protected String getTypeText(Object object, String qualifier)
  {
    StringBuffer typeKey = new StringBuffer("_UI_");
    typeKey.append(object instanceof EObject ?
                   ((EObject) object).eClass().getName() : "Unknown");
    typeKey.append("_type");

    if (qualifier != null)
    {
      typeKey.append('_');
      typeKey.append(qualifier);
    }
    return XSDEditPlugin.INSTANCE.getString(typeKey.toString());
  }  

  /**
   * This returns the icon for {@link CreateChildCommand}.
   */
  @Override
  public Object getCreateChildImage(Object parent, Object feature,
                                    Object child, Collection<?> selection)
  {
    child = getParticleOrAttributeUseContent(child);
    Object image = null;

    if (child instanceof EObject)
    {
      StringBuffer imageName = new StringBuffer("full/obj16/");
      String typeName = ((EObject) child).eClass().getName();

      EReference refFeature = feature instanceof EReference ?
        (EReference) feature : null;
      String qualifier = getImageNameQualifier(parent, refFeature, child);

      // kludge: drop Declaration or Definition when qualifier is Use
      int i = "Use".equals(qualifier) ? typeName.lastIndexOf('D') : 0;
      if (i > 0)
      {
        typeName = typeName.substring(0, i);
      }
      imageName.append(typeName);

      if (qualifier != null)
      {
        imageName.append(qualifier);
      }
      image = XSDEditPlugin.INSTANCE.getImage(imageName.toString());    
    }
    return image;
  }

  /**
   * This generates and returns a qualifier string, for use in forming the
   * type key, based on the state of the child object, its parent, and the
   * feature under which it is to be added.
   */
  protected String getTypeTextQualifier(Object parent, EReference feature,
                                        Object child)
  {
    return getQualifier(parent, feature, child);
  }    

  /**
   * This generates and returns a qualifier string, for use in forming the
   * icon filename, based on the state of the child object, its parent, and
   * the feature under which is to be added.
   */
  protected String getImageNameQualifier(Object parent, EReference feature,
                                         Object child)
  {
    // kludge: there is only one annotation icon
    Object p = child instanceof XSDAnnotation ? null : parent;      
    String qualifier = getQualifier(p, feature, child);
      
    // a little translation
    if ("keyref".equals(qualifier))
    {
      qualifier = "KeyReference";
    }
    else if ("reference".equals(qualifier))
    {
      qualifier = "Use";
    }
    else if (qualifier != null && qualifier.length() > 0)
    {
      // capitalize
      StringBuffer buffer = new StringBuffer(qualifier);
      char c = buffer.charAt(0);
      buffer.setCharAt(0, Character.toUpperCase(c));
      qualifier = buffer.toString();
    }
    return qualifier;
  }

  /**
   * This does common stuff for getTypeTextQualifier() and
   * getImageNameQualifier().
   */
  private String getQualifier(Object parent, EReference feature, Object child)
  {
    String qualifier = null;

    // qualification for feature declaration or group definition reference
    if ((child instanceof XSDElementDeclaration &&
         ((XSDElementDeclaration) child).isElementDeclarationReference()) ||
        (child instanceof XSDAttributeDeclaration &&
         ((XSDAttributeDeclaration) child).isAttributeDeclarationReference()) ||
        (child instanceof XSDModelGroupDefinition &&
         ((XSDModelGroupDefinition) child).isModelGroupDefinitionReference()) ||
        (child instanceof XSDAttributeGroupDefinition &&
         ((XSDAttributeGroupDefinition) 
          child).isAttributeGroupDefinitionReference()))
    {    
      qualifier = "reference";
    }

    // qualification by compositor for model group
    else if (child instanceof XSDModelGroup)
    {
      qualifier = ((XSDModelGroup) child).getCompositor().getName();
    }

    // qualification by variety for simple type definition
    else if (child instanceof XSDSimpleTypeDefinition)
    {
      qualifier = ((XSDSimpleTypeDefinition) child).getVariety().getName();
    }

    // qualification by category for identity constraint definition
    else if (child instanceof XSDIdentityConstraintDefinition)
    {
      qualifier = ((XSDIdentityConstraintDefinition) child).getIdentityConstraintCategory().getName();
    }

    // qualification by variety for xpath definition
    else if (child instanceof XSDXPathDefinition)
    {
      qualifier = ((XSDXPathDefinition) child).getVariety().getName();
    }

    // qualification according to parent for wildcard
    else if (child instanceof XSDWildcard)
    {
      if (parent instanceof XSDAttributeGroupDefinition ||
          parent instanceof XSDComplexTypeDefinition)
      {
        qualifier = "attribute";
      }
      else if (parent instanceof XSDModelGroup)
      {
        qualifier = "element";
      }
    }

    // qualification by feature name for annotation under type definition
    else if (child instanceof XSDAnnotation &&
             parent instanceof XSDTypeDefinition &&
             feature != null)
    {
      qualifier = feature.getName();
    }
    return qualifier;
  }
}
