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
 * $Id: XSDRedefineImpl.java,v 1.1 2004/03/06 18:00:11 marcelop Exp $
 */
package org.eclipse.xsd.impl;


import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import org.w3c.dom.Element;
import org.w3c.dom.Node;

import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.EObjectEList;
import org.eclipse.emf.ecore.util.InternalEList;

import org.eclipse.xsd.XSDAnnotation;
import org.eclipse.xsd.XSDAttributeGroupDefinition;
import org.eclipse.xsd.XSDComplexTypeDefinition;
import org.eclipse.xsd.XSDConcreteComponent;
import org.eclipse.xsd.XSDDiagnosticSeverity;
import org.eclipse.xsd.XSDFactory;
import org.eclipse.xsd.XSDModelGroupDefinition;
import org.eclipse.xsd.XSDPackage;
import org.eclipse.xsd.XSDParticle;
import org.eclipse.xsd.XSDRedefine;
import org.eclipse.xsd.XSDRedefineContent;
import org.eclipse.xsd.XSDSchema;
import org.eclipse.xsd.XSDTypeDefinition;
import org.eclipse.xsd.util.XSDConstants;


/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Redefine</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.xsd.impl.XSDRedefineImpl#getAnnotations <em>Annotations</em>}</li>
 *   <li>{@link org.eclipse.xsd.impl.XSDRedefineImpl#getContents <em>Contents</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class XSDRedefineImpl 
  extends XSDSchemaCompositorImpl 
  implements XSDRedefine
{
  /**
   * The cached value of the '{@link #getAnnotations() <em>Annotations</em>}' reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getAnnotations()
   * @generated
   * @ordered
   */
  protected EList annotations = null;

  /**
   * The cached value of the '{@link #getContents() <em>Contents</em>}' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getContents()
   * @generated
   * @ordered
   */
  protected EList contents = null;

  public static XSDRedefine createRedefine(Node node)
  {
    if (XSDConstants.nodeType(node) == XSDConstants.REDEFINE_ELEMENT)
    {
      XSDRedefine xsdRedefine = XSDFactory.eINSTANCE.createXSDRedefine();
      xsdRedefine.setElement((Element)node);
      return xsdRedefine;
    }

    return null;
  }

  protected XSDRedefineImpl() 
  {
    super();
  }


  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected EClass eStaticClass()
  {
    return XSDPackage.eINSTANCE.getXSDRedefine();
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EList getAnnotations()
  {
    if (annotations == null)
    {
      annotations = new EObjectEList(XSDAnnotation.class, this, XSDPackage.XSD_REDEFINE__ANNOTATIONS);
    }
    return annotations;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EList getContents()
  {
    if (contents == null)
    {
      contents = new EObjectContainmentEList(XSDRedefineContent.class, this, XSDPackage.XSD_REDEFINE__CONTENTS);
    }
    return contents;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, Class baseClass, NotificationChain msgs)
  {
    if (featureID >= 0)
    {
      switch (eDerivedStructuralFeatureID(featureID, baseClass))
      {
        case XSDPackage.XSD_REDEFINE__DIAGNOSTICS:
          return ((InternalEList)getDiagnostics()).basicRemove(otherEnd, msgs);
        case XSDPackage.XSD_REDEFINE__CONTENTS:
          return ((InternalEList)getContents()).basicRemove(otherEnd, msgs);
        default:
          return eDynamicInverseRemove(otherEnd, featureID, baseClass, msgs);
      }
    }
    return eBasicSetContainer(null, featureID, msgs);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public Object eGet(EStructuralFeature eFeature, boolean resolve)
  {
    switch (eDerivedStructuralFeatureID(eFeature))
    {
      case XSDPackage.XSD_REDEFINE__ELEMENT:
        return getElement();
      case XSDPackage.XSD_REDEFINE__CONTAINER:
        return getContainer();
      case XSDPackage.XSD_REDEFINE__ROOT_CONTAINER:
        return getRootContainer();
      case XSDPackage.XSD_REDEFINE__SCHEMA:
        return getSchema();
      case XSDPackage.XSD_REDEFINE__DIAGNOSTICS:
        return getDiagnostics();
      case XSDPackage.XSD_REDEFINE__SCHEMA_LOCATION:
        return getSchemaLocation();
      case XSDPackage.XSD_REDEFINE__RESOLVED_SCHEMA:
        return getResolvedSchema();
      case XSDPackage.XSD_REDEFINE__INCORPORATED_SCHEMA:
        return getIncorporatedSchema();
      case XSDPackage.XSD_REDEFINE__ANNOTATIONS:
        return getAnnotations();
      case XSDPackage.XSD_REDEFINE__CONTENTS:
        return getContents();
    }
    return eDynamicGet(eFeature, resolve);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void eSet(EStructuralFeature eFeature, Object newValue)
  {
    switch (eDerivedStructuralFeatureID(eFeature))
    {
      case XSDPackage.XSD_REDEFINE__ELEMENT:
        setElement((Element)newValue);
        return;
      case XSDPackage.XSD_REDEFINE__DIAGNOSTICS:
        getDiagnostics().clear();
        getDiagnostics().addAll((Collection)newValue);
        return;
      case XSDPackage.XSD_REDEFINE__SCHEMA_LOCATION:
        setSchemaLocation((String)newValue);
        return;
      case XSDPackage.XSD_REDEFINE__RESOLVED_SCHEMA:
        setResolvedSchema((XSDSchema)newValue);
        return;
      case XSDPackage.XSD_REDEFINE__INCORPORATED_SCHEMA:
        setIncorporatedSchema((XSDSchema)newValue);
        return;
      case XSDPackage.XSD_REDEFINE__ANNOTATIONS:
        getAnnotations().clear();
        getAnnotations().addAll((Collection)newValue);
        return;
      case XSDPackage.XSD_REDEFINE__CONTENTS:
        getContents().clear();
        getContents().addAll((Collection)newValue);
        return;
    }
    eDynamicSet(eFeature, newValue);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void eUnset(EStructuralFeature eFeature)
  {
    switch (eDerivedStructuralFeatureID(eFeature))
    {
      case XSDPackage.XSD_REDEFINE__ELEMENT:
        setElement(ELEMENT_EDEFAULT);
        return;
      case XSDPackage.XSD_REDEFINE__DIAGNOSTICS:
        getDiagnostics().clear();
        return;
      case XSDPackage.XSD_REDEFINE__SCHEMA_LOCATION:
        setSchemaLocation(SCHEMA_LOCATION_EDEFAULT);
        return;
      case XSDPackage.XSD_REDEFINE__RESOLVED_SCHEMA:
        setResolvedSchema((XSDSchema)null);
        return;
      case XSDPackage.XSD_REDEFINE__INCORPORATED_SCHEMA:
        setIncorporatedSchema((XSDSchema)null);
        return;
      case XSDPackage.XSD_REDEFINE__ANNOTATIONS:
        getAnnotations().clear();
        return;
      case XSDPackage.XSD_REDEFINE__CONTENTS:
        getContents().clear();
        return;
    }
    eDynamicUnset(eFeature);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public boolean eIsSet(EStructuralFeature eFeature)
  {
    switch (eDerivedStructuralFeatureID(eFeature))
    {
      case XSDPackage.XSD_REDEFINE__ELEMENT:
        return ELEMENT_EDEFAULT == null ? element != null : !ELEMENT_EDEFAULT.equals(element);
      case XSDPackage.XSD_REDEFINE__CONTAINER:
        return getContainer() != null;
      case XSDPackage.XSD_REDEFINE__ROOT_CONTAINER:
        return getRootContainer() != null;
      case XSDPackage.XSD_REDEFINE__SCHEMA:
        return getSchema() != null;
      case XSDPackage.XSD_REDEFINE__DIAGNOSTICS:
        return diagnostics != null && !diagnostics.isEmpty();
      case XSDPackage.XSD_REDEFINE__SCHEMA_LOCATION:
        return SCHEMA_LOCATION_EDEFAULT == null ? schemaLocation != null : !SCHEMA_LOCATION_EDEFAULT.equals(schemaLocation);
      case XSDPackage.XSD_REDEFINE__RESOLVED_SCHEMA:
        return resolvedSchema != null;
      case XSDPackage.XSD_REDEFINE__INCORPORATED_SCHEMA:
        return incorporatedSchema != null;
      case XSDPackage.XSD_REDEFINE__ANNOTATIONS:
        return annotations != null && !annotations.isEmpty();
      case XSDPackage.XSD_REDEFINE__CONTENTS:
        return contents != null && !contents.isEmpty();
    }
    return eDynamicIsSet(eFeature);
  }

  public Element createElement()
  {
    Element newElement = createElement(XSDConstants.REDEFINE_ELEMENT);
    setElement(newElement);

    for (Iterator contents = getContents().iterator(); contents.hasNext(); )
    {
      XSDRedefineContent xsdRedefineContent = (XSDRedefineContent)contents.next();
      Element contentElement = ((XSDConcreteComponentImpl)xsdRedefineContent).createElement();
      newElement.appendChild(contentElement);
    }
    return newElement;
  }

  protected void patch()
  {
    resolve(null, getSchemaLocation());
    super.patch();
  }

  protected void handleResolvedSchema(XSDSchema xsdSchema)
  {
    if (xsdSchema == null)
    {
      setResolvedSchema(null);
      setIncorporatedSchema(null);
    }
    else
    {
      ((XSDSchemaImpl)xsdSchema).redefined(this);
    }
  }

  public void validate()
  {
    super.validate();

    Element theElement = getElement();
    if (theElement != null)
    {
      checkAttributes
        (XSDConstants.PART1,
         "element-redefine",
         theElement,
         new String []
         {
           XSDConstants.ID_ATTRIBUTE,
           XSDConstants.SCHEMALOCATION_ATTRIBUTE,
         });

      XSDComplexTypeDefinition xsdComplexTypeDefinition =
        (XSDComplexTypeDefinition)getSchema().getSchemaForSchema().resolveElementDeclaration("redefine").getTypeDefinition();

      checkComplexContent(xsdComplexTypeDefinition, XSDConstants.PART1, "element-redefine", theElement);

      checkBuiltInTypeConstraint
        ("ID",
         null,
         XSDConstants.PART1,
         "element-redefine",
         theElement,
         XSDConstants.ID_ATTRIBUTE,
         false);
    }

    checkBuiltInTypeConstraint
      ("anyURI",
       getSchemaLocation(),
       XSDConstants.PART1,
       "element-redefine",
       theElement,
       XSDConstants.SCHEMALOCATION_ATTRIBUTE,
       true);

    XSDSchema theResolvedSchema = getResolvedSchema();
    if (theResolvedSchema == null)
    {
      boolean hasRedefinitions = false;
      for (Iterator i = getContents().iterator(); i.hasNext(); )
      {
        if (!(i.next() instanceof XSDAnnotation))
        {
          hasRedefinitions = true;
          break;
        }
      }
      if (hasRedefinitions)
      {
        createDiagnostic
          (XSDDiagnosticSeverity.ERROR_LITERAL,
           "src-redefine.1",
           getSchemaLocation() == null ? "" : getSchemaLocation());
      }
      else
      {
        createDiagnostic
          (XSDDiagnosticSeverity.WARNING_LITERAL,
           "src-redefine.0",
           getSchemaLocation() == null ? "" : getSchemaLocation());
      }
    }
    else
    {
      if (theResolvedSchema.eResource() == null ||
            theResolvedSchema.getElement() == null ||
            XSDConstants.nodeType(theResolvedSchema.getElement()) != XSDConstants.SCHEMA_ELEMENT)
      {
        createDiagnostic
          (XSDDiagnosticSeverity.ERROR_LITERAL,
           "src-redefine.2",
           getSchemaLocation() == null ? "" : getSchemaLocation());
      }
      else
      {
        XSDSchema theSchema = getSchema();
        if (theSchema.getTargetNamespace() == null ?
              theResolvedSchema.getTargetNamespace() != null :
               !theSchema.getTargetNamespace().equals(theResolvedSchema.getTargetNamespace()) &&
                 theResolvedSchema.getTargetNamespace() != null)
        {
          createDiagnostic
            (XSDDiagnosticSeverity.ERROR_LITERAL,
             "src-redefine.3",
             theResolvedSchema.getTargetNamespace() == null ? "" : theResolvedSchema.getTargetNamespace(),
             theSchema.getTargetNamespace() == null ? "" : theSchema.getTargetNamespace());
        }
        else
        {
          for (Iterator i = getContents().iterator(); i.hasNext(); )
          {
            XSDRedefineContent xsdRedefineContent = (XSDRedefineContent)i.next();
            if (xsdRedefineContent instanceof XSDTypeDefinition)
            {
              XSDTypeDefinition xsdTypeDefinition = (XSDTypeDefinition)xsdRedefineContent;
              XSDTypeDefinition baseType = xsdTypeDefinition.getBaseType();
              if (baseType == null || 
                    !baseType.hasSameNameAndTargetNamespace(xsdTypeDefinition) ||
                    resolveTypeDefinition(xsdTypeDefinition.getName()).getContainer() == null)
              {
                getDiagnosticTarget(xsdTypeDefinition).createDiagnostic
                  (XSDDiagnosticSeverity.ERROR_LITERAL,
                   "src-redefine.5",
                   xsdTypeDefinition.getName());
              }
            }
            else if (xsdRedefineContent instanceof XSDModelGroupDefinition)
            {
              XSDModelGroupDefinition xsdModelGroupDefinition = (XSDModelGroupDefinition)xsdRedefineContent;
              Collection selfReferences = new ArrayList();
              for (Iterator j = xsdModelGroupDefinition.eAllContents(); j.hasNext(); )
              {
                Object component = j.next();
                if (component instanceof XSDModelGroupDefinition)
                {
                  XSDModelGroupDefinition otherXSDModelGroupDefinition = (XSDModelGroupDefinition)component;
                  if (xsdModelGroupDefinition.hasSameNameAndTargetNamespace
                        (otherXSDModelGroupDefinition.getResolvedModelGroupDefinition()))
                  {
                    selfReferences.add(otherXSDModelGroupDefinition);
                  }
                }
              }

              if (selfReferences.isEmpty())
              {
                XSDModelGroupDefinition otherXSDModelGroupDefinition = 
                  theResolvedSchema.resolveModelGroupDefinition(xsdModelGroupDefinition.getName());
                if (otherXSDModelGroupDefinition.getContainer() == null)
                {
                  ((XSDConcreteComponentImpl)xsdModelGroupDefinition).createDiagnostic
                    (XSDDiagnosticSeverity.ERROR_LITERAL,
                     "src-redefine.6.2.1",
                     xsdModelGroupDefinition.getName());
                }
                else 
                {
                  XSDParticle xsdParticle = XSDFactory.eINSTANCE.createXSDParticle();
                  xsdParticle.setTerm(xsdModelGroupDefinition.getModelGroup());
                  XSDParticle otherXSDParticle = XSDFactory.eINSTANCE.createXSDParticle();
                  otherXSDParticle.setTerm(otherXSDModelGroupDefinition.getModelGroup());
                  // EATM This doesn't really work for retargeted schemas.
                  //
                  if (!((XSDParticleImpl)xsdParticle).isSubset(otherXSDParticle, true))
                  {
                    ((XSDConcreteComponentImpl)xsdModelGroupDefinition).createDiagnostic
                      (XSDDiagnosticSeverity.ERROR_LITERAL,
                       "src-redefine.6.2.2");
                  }
                }
              }
              else
              {
                Iterator j = selfReferences.iterator();
                XSDModelGroupDefinition otherXSDModelGroupDefinition = (XSDModelGroupDefinition)j.next();
                XSDParticle xsdParticle = (XSDParticle)otherXSDModelGroupDefinition.getContainer();
                if (xsdParticle.getMinOccurs() != 1 || xsdParticle.getMaxOccurs() != 1)
                {
                  ((XSDConcreteComponentImpl)xsdParticle).createDiagnostic
                    (XSDDiagnosticSeverity.ERROR_LITERAL,
                     "src-redefine.6.1.2");
                }

                while (j.hasNext())
                {
                  XSDConcreteComponentImpl xsdConcreteComponent = (XSDConcreteComponentImpl)j.next();
                  xsdConcreteComponent.createDiagnostic
                    (XSDDiagnosticSeverity.ERROR_LITERAL,
                     "src-redefine.6.1.1");
                }
              }
            }
            else if (xsdRedefineContent instanceof XSDAttributeGroupDefinition)
            {
              XSDAttributeGroupDefinition xsdAttributeGroupDefinition = (XSDAttributeGroupDefinition)xsdRedefineContent;
              Collection selfReferences = new ArrayList();
              for (Iterator j = xsdAttributeGroupDefinition.eAllContents(); j.hasNext(); )
              {
                Object component = j.next();
                if (component instanceof XSDAttributeGroupDefinition)
                {
                  XSDAttributeGroupDefinition otherXSDAttributeGroupDefinition = (XSDAttributeGroupDefinition)component;
                  if (xsdAttributeGroupDefinition.hasSameNameAndTargetNamespace
                        (otherXSDAttributeGroupDefinition.getResolvedAttributeGroupDefinition()))
                  {
                    selfReferences.add(otherXSDAttributeGroupDefinition);
                  }
                }
              }

              if (selfReferences.isEmpty())
              {
                XSDAttributeGroupDefinition otherXSDAttributeGroupDefinition = 
                  theResolvedSchema.resolveAttributeGroupDefinition(xsdAttributeGroupDefinition.getName());
                if (otherXSDAttributeGroupDefinition.getContainer() == null)
                {
                  ((XSDConcreteComponentImpl)xsdAttributeGroupDefinition).createDiagnostic
                    (XSDDiagnosticSeverity.ERROR_LITERAL,
                     "src-redefine.7.2.1",
                     xsdAttributeGroupDefinition.getName());
                }
                else 
                {
                  // EATM This doesn't really work for retargeted schemas.
                  //
                  XSDComplexTypeDefinitionImpl.validateAttributeGroup
                    ((XSDConcreteComponentImpl)xsdAttributeGroupDefinition,
                     xsdAttributeGroupDefinition.getAttributeWildcardContent(),
                     otherXSDAttributeGroupDefinition.getAttributeUses(),
                     xsdAttributeGroupDefinition.getAttributeUses(),
                     otherXSDAttributeGroupDefinition.getAttributeWildcard(),
                     xsdAttributeGroupDefinition.getAttributeWildcard());
                }
              }
              else
              {
                Iterator j = selfReferences.iterator();
                j.next();
                while (j.hasNext())
                {
                  XSDConcreteComponentImpl xsdConcreteComponent = (XSDConcreteComponentImpl)j.next();
                  xsdConcreteComponent.createDiagnostic
                    (XSDDiagnosticSeverity.ERROR_LITERAL,
                     "src-redefine.7.1");
                }
              }
            }
          }
        }
      }
    }
  }

  protected void handleUnreconciledElement(Element child, List newContents, List remainingContents)
  {
    XSDRedefineContent xsdRedefineContent = XSDRedefineContentImpl.createRedefineContent(child);
    if (xsdRedefineContent != null)
    {
      newContents.add(xsdRedefineContent);
    }
  }

  protected void handleReconciliation(List newContents, List remainingContents)
  {
    if (!remainingContents.isEmpty())
    {
      getContents().removeAll(remainingContents);
    }

    setListContentAndOrder(getContents(), newContents);
  }

  public XSDConcreteComponent cloneConcreteComponent(boolean deep, boolean shareDOM)
  {
    XSDRedefineImpl clonedRedefine =
      (XSDRedefineImpl)getXSDFactory().createXSDRedefine();
    clonedRedefine.isReconciling = true;

    clonedRedefine.setSchemaLocation(getSchemaLocation());

    if (shareDOM && getElement() != null)
    {
      clonedRedefine.setElement(getElement());
    }

    clonedRedefine.isReconciling = shareDOM;
    return clonedRedefine;
  }
} 
