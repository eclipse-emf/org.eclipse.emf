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
import org.eclipse.emf.ecore.EReference;
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
  protected EList<XSDAnnotation> annotations;

  /**
   * The cached value of the '{@link #getContents() <em>Contents</em>}' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getContents()
   * @generated
   * @ordered
   */
  protected EList<XSDRedefineContent> contents;

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

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected XSDRedefineImpl()
  {
    super();
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  protected EClass eStaticClass()
  {
    return XSDPackage.Literals.XSD_REDEFINE;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EList<XSDAnnotation> getAnnotations()
  {
    if (annotations == null)
    {
      annotations = new EObjectEList<XSDAnnotation>(XSDAnnotation.class, this, XSDPackage.XSD_REDEFINE__ANNOTATIONS);
    }
    return annotations;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EList<XSDRedefineContent> getContents()
  {
    if (contents == null)
    {
      contents = new EObjectContainmentEList<XSDRedefineContent>(XSDRedefineContent.class, this, XSDPackage.XSD_REDEFINE__CONTENTS);
    }
    return contents;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs)
  {
    switch (featureID)
    {
      case XSDPackage.XSD_REDEFINE__CONTENTS:
        return ((InternalEList<?>)getContents()).basicRemove(otherEnd, msgs);
    }
    return super.eInverseRemove(otherEnd, featureID, msgs);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public Object eGet(int featureID, boolean resolve, boolean coreType)
  {
    switch (featureID)
    {
      case XSDPackage.XSD_REDEFINE__ANNOTATIONS:
        return getAnnotations();
      case XSDPackage.XSD_REDEFINE__CONTENTS:
        return getContents();
    }
    return super.eGet(featureID, resolve, coreType);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @SuppressWarnings("unchecked")
  @Override
  public void eSet(int featureID, Object newValue)
  {
    switch (featureID)
    {
      case XSDPackage.XSD_REDEFINE__ANNOTATIONS:
        getAnnotations().clear();
        getAnnotations().addAll((Collection<? extends XSDAnnotation>)newValue);
        return;
      case XSDPackage.XSD_REDEFINE__CONTENTS:
        getContents().clear();
        getContents().addAll((Collection<? extends XSDRedefineContent>)newValue);
        return;
    }
    super.eSet(featureID, newValue);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public void eUnset(int featureID)
  {
    switch (featureID)
    {
      case XSDPackage.XSD_REDEFINE__ANNOTATIONS:
        getAnnotations().clear();
        return;
      case XSDPackage.XSD_REDEFINE__CONTENTS:
        getContents().clear();
        return;
    }
    super.eUnset(featureID);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public boolean eIsSet(int featureID)
  {
    switch (featureID)
    {
      case XSDPackage.XSD_REDEFINE__ANNOTATIONS:
        return annotations != null && !annotations.isEmpty();
      case XSDPackage.XSD_REDEFINE__CONTENTS:
        return contents != null && !contents.isEmpty();
    }
    return super.eIsSet(featureID);
  }

  @Override
  public Element createElement()
  {
    Element newElement = createElement(XSDConstants.REDEFINE_ELEMENT);
    setElement(newElement);

    for (XSDRedefineContent xsdRedefineContent : getContents())
    {
      Element contentElement = ((XSDConcreteComponentImpl)xsdRedefineContent).createElement();
      newElement.appendChild(contentElement);
    }
    return newElement;
  }

  @Override
  protected void patch()
  {
    if (getSchemaLocation() != null && getSchemaLocation().length() > 0)
    {
      XSDSchemaImpl containingSchema = (XSDSchemaImpl)getContainer();
      String oldPendingSchemaLocation = containingSchema.pendingSchemaLocation;
      if (containingSchema.getSchemaLocation() != null)
      {
        containingSchema.pendingSchemaLocation = containingSchema.getSchemaLocation();
      }
      resolve("", getSchemaLocation());
      containingSchema.pendingSchemaLocation = oldPendingSchemaLocation;
      super.patch();
      if (oldPendingSchemaLocation == null)
      {
        List<XSDSchemaImpl> redefinitions = containingSchema.getSchemasToRedefine();
        containingSchema.schemasToRedefine = null;
        for (XSDSchemaImpl schemaToRedefine : redefinitions)
        {
          if (schemaToRedefine != containingSchema)
          {
            schemaToRedefine.forceResolve = true;
            schemaToRedefine.setSchemaLocation(schemaToRedefine.pendingSchemaLocation);
            schemaToRedefine.forceResolve = false;
            schemaToRedefine.pendingSchemaLocation = null;
          }
        }
      }
    }
    else
    {
      super.patch();
    }
  }

  @Override
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

  @Override
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
      for (XSDRedefineContent content : getContents())
      {
        if (!(content instanceof XSDAnnotation))
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
          for (XSDRedefineContent xsdRedefineContent : getContents())
          {
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
              Collection<XSDModelGroupDefinition> selfReferences = new ArrayList<XSDModelGroupDefinition>();
              for (Iterator<?> j = xsdModelGroupDefinition.eAllContents(); j.hasNext(); )
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
                  (XSDModelGroupDefinition)((XSDSchemaImpl)getContainer()).getRedefinitionMap().get(xsdModelGroupDefinition);
                if (otherXSDModelGroupDefinition == null || otherXSDModelGroupDefinition.getContainer() == null)
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
                Iterator<XSDModelGroupDefinition> j = selfReferences.iterator();
                XSDModelGroupDefinition otherXSDModelGroupDefinition = j.next();
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
              Collection<XSDAttributeGroupDefinition> selfReferences = new ArrayList<XSDAttributeGroupDefinition>();
              for (Iterator<?> j = xsdAttributeGroupDefinition.eAllContents(); j.hasNext(); )
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
                  (XSDAttributeGroupDefinition)((XSDSchemaImpl)getContainer()).getRedefinitionMap().get(xsdAttributeGroupDefinition);
                if (otherXSDAttributeGroupDefinition == null || otherXSDAttributeGroupDefinition.getContainer() == null)
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
                Iterator<?> j = selfReferences.iterator();
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

  @Override
  protected void handleUnreconciledElement(Element child, List<XSDConcreteComponent> newContents, List<XSDConcreteComponent> remainingContents)
  {
    XSDRedefineContent xsdRedefineContent = XSDRedefineContentImpl.createRedefineContent(child);
    if (xsdRedefineContent != null)
    {
      newContents.add(xsdRedefineContent);
    }
  }

  @Override
  protected void handleReconciliation(List<XSDConcreteComponent> newContents, List<XSDConcreteComponent> remainingContents)
  {
    if (!remainingContents.isEmpty())
    {
      getContents().removeAll(remainingContents);
    }

    @SuppressWarnings("unchecked") List<XSDRedefineContent> list = (List<XSDRedefineContent>)(List<?>)newContents;
    setListContentAndOrder(getContents(), list);
  }

  @Override
  protected void adoptContent(EReference eReference, XSDConcreteComponent xsdConcreteComponent)
  {
    super.adoptContent(eReference, xsdConcreteComponent);
    if (xsdConcreteComponent instanceof XSDAnnotation)
    {
      getAnnotations().add((XSDAnnotation)xsdConcreteComponent);
    }
  }

  @Override
  protected void orphanContent(EReference eReference, XSDConcreteComponent xsdConcreteComponent)
  {
    super.orphanContent(eReference, xsdConcreteComponent);
    if (xsdConcreteComponent instanceof XSDAnnotation)
    {
      getAnnotations().remove(xsdConcreteComponent);
    }
  }

  @Override
  public XSDConcreteComponent cloneConcreteComponent(boolean deep, boolean shareDOM)
  {
    XSDRedefineImpl clonedRedefine =
      (XSDRedefineImpl)getXSDFactory().createXSDRedefine();
    clonedRedefine.isReconciling = true;

    clonedRedefine.setSchemaLocation(getSchemaLocation());
    
    if (deep)
    {
      if (!getContents().isEmpty())
      {
        clonedRedefine.getContents().addAll(cloneConcreteComponents(getContents(), true, shareDOM));
      }
    }
    
    if (shareDOM && getElement() != null)
    {
      clonedRedefine.setElement(getElement());
    }

    clonedRedefine.isReconciling = shareDOM;
    return clonedRedefine;
  }
} 
