/**
 * <copyright>
 *
 * Copyright (c) 2002-2006 IBM Corporation and others.
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
 * $Id: XSDModelGroupDefinitionImpl.java,v 1.18 2008/12/22 14:25:48 emerks Exp $
 */
package org.eclipse.xsd.impl;


import java.util.List;
import java.util.Map;

import org.w3c.dom.Element;
import org.w3c.dom.Node;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.xsd.XSDAnnotation;
import org.eclipse.xsd.XSDComponent;
import org.eclipse.xsd.XSDConcreteComponent;
import org.eclipse.xsd.XSDDiagnosticSeverity;
import org.eclipse.xsd.XSDFactory;
import org.eclipse.xsd.XSDModelGroup;
import org.eclipse.xsd.XSDModelGroupDefinition;
import org.eclipse.xsd.XSDNamedComponent;
import org.eclipse.xsd.XSDPackage;
import org.eclipse.xsd.XSDParticle;
import org.eclipse.xsd.XSDRedefine;
import org.eclipse.xsd.XSDSchema;
import org.eclipse.xsd.util.XSDConstants;


/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Model Group Definition</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.xsd.impl.XSDModelGroupDefinitionImpl#isModelGroupDefinitionReference <em>Model Group Definition Reference</em>}</li>
 *   <li>{@link org.eclipse.xsd.impl.XSDModelGroupDefinitionImpl#getAnnotation <em>Annotation</em>}</li>
 *   <li>{@link org.eclipse.xsd.impl.XSDModelGroupDefinitionImpl#getModelGroup <em>Model Group</em>}</li>
 *   <li>{@link org.eclipse.xsd.impl.XSDModelGroupDefinitionImpl#getResolvedModelGroupDefinition <em>Resolved Model Group Definition</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class XSDModelGroupDefinitionImpl 
  extends XSDRedefinableComponentImpl 
  implements XSDModelGroupDefinition
{
  /**
   * The default value of the '{@link #isModelGroupDefinitionReference() <em>Model Group Definition Reference</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #isModelGroupDefinitionReference()
   * @generated
   * @ordered
   */
  protected static final boolean MODEL_GROUP_DEFINITION_REFERENCE_EDEFAULT = false;

  /**
   * The cached value of the '{@link #getAnnotation() <em>Annotation</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getAnnotation()
   * @generated
   * @ordered
   */
  protected XSDAnnotation annotation;

  /**
   * The cached value of the '{@link #getModelGroup() <em>Model Group</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getModelGroup()
   * @generated
   * @ordered
   */
  protected XSDModelGroup modelGroup;

  /**
   * The cached value of the '{@link #getResolvedModelGroupDefinition() <em>Resolved Model Group Definition</em>}' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getResolvedModelGroupDefinition()
   * @generated
   * @ordered
   */
  protected XSDModelGroupDefinition resolvedModelGroupDefinition;

  public static XSDModelGroupDefinition createModelGroupDefinition(Node node)
  {
    if (XSDConstants.nodeType(node) == XSDConstants.GROUP_ELEMENT)
    {
      XSDModelGroupDefinition xsdModelGroupDefinition = XSDFactory.eINSTANCE.createXSDModelGroupDefinition();
      xsdModelGroupDefinition.setElement((Element)node);
      return xsdModelGroupDefinition;
    }

    return null;
  }

  protected int analysisState;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated NOT
   */
  protected XSDModelGroupDefinitionImpl()
  {
    super();
    this.resolvedModelGroupDefinition = this;
    // this.setResolvedModelGroupDefinition = true;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  protected EClass eStaticClass()
  {
    return XSDPackage.Literals.XSD_MODEL_GROUP_DEFINITION;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated modifiable
   */
  public Boolean getModelGroupDefinitionReference() 
  {
    return isModelGroupDefinitionReference() ? Boolean.TRUE : Boolean.FALSE;
  }

  @Override
  protected boolean isUpdatingDOM()
  {
    return
      super.isUpdatingDOM() ||
        getContainer() instanceof XSDConcreteComponentImpl &&
          ((XSDConcreteComponentImpl)getContainer()).isUpdatingDOM();
  }

  @Override
  public Element createElement()
  {
    Element newElement = createElement(XSDConstants.GROUP_ELEMENT);
    setElement(newElement);

    XSDModelGroup theModelGroup = getModelGroup();
    if (theModelGroup != null)
    {
      Element modelGroupElement = ((XSDConcreteComponentImpl)theModelGroup).createElement();
      newElement.appendChild(modelGroupElement);
    }

    return newElement;
  }

  @Override
  protected void patch()
  {
    analysisState = UNANALYZED;
    super.patch();
    XSDModelGroupDefinition theResolvedModelGroupDefinition = getResolvedModelGroupDefinition();

    if (theResolvedModelGroupDefinition != this && (forceResolve || theResolvedModelGroupDefinition.getContainer() == null))
    {
      XSDModelGroupDefinition newResolvedModelGroupDefinition =
        resolveModelGroupDefinition(theResolvedModelGroupDefinition.getTargetNamespace(), theResolvedModelGroupDefinition.getName());
      if (forceResolve || newResolvedModelGroupDefinition.getContainer() != null)
      {
        handleNewResolvedModelGroupDefinition(newResolvedModelGroupDefinition);
      }
    }
  }

  @Override
  protected boolean analyze()
  {
    switch (analysisState)
    {
      case UNANALYZED:
      {
        analysisState = ANALYZING;
        super.analyze();
        handleAnalysis();
        if (analysisState == ANALYZING)
        {
          analysisState = ANALYZED;
          return true;
        }
        else
        {
          return false;
        }
      }
      case ANALYZED:
      {
        return true;
      }
      case ANALYZING:
      case CIRCULAR:
      default:
      {
        analysisState = CIRCULAR;
        return false;
      }
    }
  }

  protected void handleAnalysis()
  {
    XSDModelGroupDefinition theResolvedModelGroupDefinition = getResolvedModelGroupDefinition();
    if (theResolvedModelGroupDefinition != this && theResolvedModelGroupDefinition.getContainer() != null)
    {
      ((XSDConcreteComponentImpl)theResolvedModelGroupDefinition).analyze();
    }
  }

  @Override
  public void validate()
  {
    super.validate();

    Element theElement = getElement();
    if (theElement != null)
    {
      if (getContainer() instanceof XSDSchema || getContainer() instanceof XSDRedefine)
      {
        checkAttributes
          (XSDConstants.PART1,
           "element-group",
           theElement,
           new String []
           {
             XSDConstants.ID_ATTRIBUTE,
             XSDConstants.NAME_ATTRIBUTE
           });

        checkComplexContent("namedGroup", XSDConstants.PART1, "element-group", theElement);
      }
      else
      {
        checkAttributes
          (XSDConstants.PART1,
           "element-group",
           theElement,
           new String []
           {
             XSDConstants.ID_ATTRIBUTE,
             XSDConstants.MAXOCCURS_ATTRIBUTE,
             XSDConstants.MINOCCURS_ATTRIBUTE,
             XSDConstants.REF_ATTRIBUTE
           });

        checkComplexContent("groupRef", XSDConstants.PART1, "element-group", theElement);
      }

      checkBuiltInTypeConstraint
        ("ID",
         null,
         XSDConstants.PART1,
         "element-attributeGroup",
         theElement,
         XSDConstants.ID_ATTRIBUTE,
         false);
    }

    if (getContainer() instanceof XSDSchema || getContainer() instanceof XSDRedefine)
    {
      if (isModelGroupDefinitionReference())
      {
        if (theElement == null)
        {
          createDiagnostic(XSDDiagnosticSeverity.ERROR_LITERAL, "cvc-complex-type.3", "ref");
        }
      }
      else
      {
        checkBuiltInTypeConstraint
          ("NCName",
           getName(),
           XSDConstants.PART1,
           "element-group",
           theElement,
           XSDConstants.NAME_ATTRIBUTE,
           true);

        if (isCircular())
        {
          createDiagnostic(XSDDiagnosticSeverity.ERROR_LITERAL, "coss-modelGroup.2");
        }

        if (getModelGroup() != null)
        {
          ((XSDModelGroupImpl)getModelGroup()).validateRoot();
        }
      }
    }
    else
    {
      if (isModelGroupDefinitionReference())
      {
        XSDModelGroupDefinition theResolvedModelGroupDefinition = getResolvedModelGroupDefinition();
        if (theResolvedModelGroupDefinition.getContainer() == null)
        {
          createDiagnostic
            (XSDDiagnosticSeverity.ERROR_LITERAL, "_UI_UnresolvedModelGroupDefinition_message", theResolvedModelGroupDefinition.getURI());
        }
      }
      else
      {
        if (theElement == null)
        {
          createDiagnostic(XSDDiagnosticSeverity.ERROR_LITERAL, "cvc-complex-type.3", "name");
        }
      }
    }
  }

  @Override
  protected void patchTargetNamespaceAttribute(XSDSchema xsdSchema)
  {
    if (!isModelGroupDefinitionReference())
    {
      super.patchTargetNamespaceAttribute(xsdSchema);
    }
  }

  @Override
  protected void reconcileAttributes(Element changedElement)
  {
    super.reconcileAttributes(changedElement);

    XSDModelGroupDefinition newResolvedModelGroupDefinition = this;
    if (changedElement.hasAttributeNS(null, XSDConstants.REF_ATTRIBUTE))
    {
      newResolvedModelGroupDefinition =
        resolveModelGroupDefinitionURI(XSDConstants.lookupQNameForAttribute(changedElement, XSDConstants.REF_ATTRIBUTE));
    }

    handleNewResolvedModelGroupDefinition(newResolvedModelGroupDefinition);
  }

  protected void handleNewResolvedModelGroupDefinition(XSDModelGroupDefinition newResolvedModelGroupDefinition)
  {
    if (eContainer != null)
    {
      EObject parent = eContainer.eContainer();
      if (parent != null)
      {
        parent = parent.eContainer();
        if (parent != null)
        {
          parent = parent.eContainer();
          if (parent instanceof XSDRedefine)
          {
            XSDSchema redefinedSchema = ((XSDRedefine)parent).getIncorporatedSchema();
            if (redefinedSchema != null)
            {
              Map<XSDComponent, XSDComponent> redefinitionMap = ((XSDSchemaImpl)redefinedSchema).getRedefinitionMap();
              if (redefinitionMap.containsKey(newResolvedModelGroupDefinition))
              {
                XSDComponent replacement = redefinitionMap.get(newResolvedModelGroupDefinition);
                if (replacement != null)
                {
                  newResolvedModelGroupDefinition = (XSDModelGroupDefinition)replacement;
                }
              }
            }
          }
        }
      }
    }
    if (newResolvedModelGroupDefinition != getResolvedModelGroupDefinition())
    {
      setResolvedModelGroupDefinition(newResolvedModelGroupDefinition);
      if (getContainer() instanceof XSDParticle)
      {
        ((XSDParticle)getContainer()).setTerm(newResolvedModelGroupDefinition.getModelGroup());
      }
    }
  }

  @Override
  protected void handleUnreconciledElement(Element child, List<XSDConcreteComponent> newContents, List<XSDConcreteComponent> remainingContents)
  {
    XSDAnnotation xsdAnnotation = XSDAnnotationImpl.createAnnotation(child);
    if (xsdAnnotation != null)
    {
      if (newContents.isEmpty())
      {
        newContents.add(xsdAnnotation);
      }
    }
    else
    {
      XSDModelGroup xsdModelGroup = XSDModelGroupImpl.createModelGroup(child);
      if (xsdModelGroup != null)
      {
        newContents.add(xsdModelGroup);
      }
    }
  }

  @Override
  protected void handleReconciliation(List<XSDConcreteComponent> newContents, List<XSDConcreteComponent> remainingContents)
  {
    handleAnnotationReconciliation(XSDPackage.Literals.XSD_MODEL_GROUP_DEFINITION__ANNOTATION, newContents, remainingContents);

    if (remainingContents.remove(getModelGroup()))
    {
      setModelGroup(null);
    }

    if (!newContents.isEmpty())
    {
      setModelGroup((XSDModelGroup)newContents.get(0));
    }
  }

  @Override
  protected void changeReference(EReference eReference)
  {
    super.changeReference(eReference);
    Element theElement = getElement();
    if (theElement != null)
    {
      if (eReference == null || eReference == XSDPackage.Literals.XSD_MODEL_GROUP_DEFINITION__RESOLVED_MODEL_GROUP_DEFINITION)
      {
        XSDModelGroupDefinition theResolvedModelGroupDefinition = getResolvedModelGroupDefinition();
        if (theResolvedModelGroupDefinition != this)
        {
          niceSetAttributeURIValue(theElement, XSDConstants.REF_ATTRIBUTE, theResolvedModelGroupDefinition.getURI());

          if (eReference != null && getContainer() instanceof XSDParticle)
          {
            ((XSDParticle)getContainer()).setTerm(theResolvedModelGroupDefinition.getModelGroup());
          }
        }
      }
    }
  }

  @Override
  protected void adoptContent(EReference eReference, XSDConcreteComponent xsdConcreteComponent)
  {
    super.adoptContent(eReference, xsdConcreteComponent);
    if (eReference == XSDPackage.Literals.XSD_MODEL_GROUP_DEFINITION__MODEL_GROUP)
    {
      traverseToRootForPatching();
    }
  }

  @Override
  protected void orphanContent(EReference eReference, XSDConcreteComponent xsdConcreteComponent)
  {
    super.orphanContent(eReference, xsdConcreteComponent);
    if (eReference == XSDPackage.Literals.XSD_MODEL_GROUP_DEFINITION__MODEL_GROUP)
    {
      traverseToRootForPatching();
    }
  }

  public boolean isModelGroupDefinitionReference()
  {
    return this != getResolvedModelGroupDefinition();
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public XSDAnnotation getAnnotation()
  {
    return annotation;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setAnnotation(XSDAnnotation newAnnotation)
  {
    if (newAnnotation != annotation)
    {
      NotificationChain msgs = null;
      if (annotation != null)
        msgs = ((InternalEObject)annotation).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - XSDPackage.XSD_MODEL_GROUP_DEFINITION__ANNOTATION, null, msgs);
      if (newAnnotation != null)
        msgs = ((InternalEObject)newAnnotation).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - XSDPackage.XSD_MODEL_GROUP_DEFINITION__ANNOTATION, null, msgs);
      msgs = basicSetAnnotation(newAnnotation, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, XSDPackage.XSD_MODEL_GROUP_DEFINITION__ANNOTATION, newAnnotation, newAnnotation));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetAnnotation(XSDAnnotation newAnnotation, NotificationChain msgs)
  {
    XSDAnnotation oldAnnotation = annotation;
    annotation = newAnnotation;
    if (eNotificationRequired())
    {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, XSDPackage.XSD_MODEL_GROUP_DEFINITION__ANNOTATION, oldAnnotation, newAnnotation);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public XSDModelGroup getModelGroup()
  {
    return modelGroup;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setModelGroup(XSDModelGroup newModelGroup)
  {
    if (newModelGroup != modelGroup)
    {
      NotificationChain msgs = null;
      if (modelGroup != null)
        msgs = ((InternalEObject)modelGroup).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - XSDPackage.XSD_MODEL_GROUP_DEFINITION__MODEL_GROUP, null, msgs);
      if (newModelGroup != null)
        msgs = ((InternalEObject)newModelGroup).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - XSDPackage.XSD_MODEL_GROUP_DEFINITION__MODEL_GROUP, null, msgs);
      msgs = basicSetModelGroup(newModelGroup, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, XSDPackage.XSD_MODEL_GROUP_DEFINITION__MODEL_GROUP, newModelGroup, newModelGroup));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetModelGroup(XSDModelGroup newModelGroup, NotificationChain msgs)
  {
    XSDModelGroup oldModelGroup = modelGroup;
    modelGroup = newModelGroup;
    if (eNotificationRequired())
    {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, XSDPackage.XSD_MODEL_GROUP_DEFINITION__MODEL_GROUP, oldModelGroup, newModelGroup);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public XSDModelGroupDefinition getResolvedModelGroupDefinition()
  {
    return resolvedModelGroupDefinition;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setResolvedModelGroupDefinition(XSDModelGroupDefinition newResolvedModelGroupDefinition)
  {
    XSDModelGroupDefinition oldResolvedModelGroupDefinition = resolvedModelGroupDefinition;
    resolvedModelGroupDefinition = newResolvedModelGroupDefinition;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, XSDPackage.XSD_MODEL_GROUP_DEFINITION__RESOLVED_MODEL_GROUP_DEFINITION, oldResolvedModelGroupDefinition, resolvedModelGroupDefinition));
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
      case XSDPackage.XSD_MODEL_GROUP_DEFINITION__ANNOTATION:
        return basicSetAnnotation(null, msgs);
      case XSDPackage.XSD_MODEL_GROUP_DEFINITION__MODEL_GROUP:
        return basicSetModelGroup(null, msgs);
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
      case XSDPackage.XSD_MODEL_GROUP_DEFINITION__MODEL_GROUP_DEFINITION_REFERENCE:
        return isModelGroupDefinitionReference();
      case XSDPackage.XSD_MODEL_GROUP_DEFINITION__ANNOTATION:
        return getAnnotation();
      case XSDPackage.XSD_MODEL_GROUP_DEFINITION__MODEL_GROUP:
        return getModelGroup();
      case XSDPackage.XSD_MODEL_GROUP_DEFINITION__RESOLVED_MODEL_GROUP_DEFINITION:
        return getResolvedModelGroupDefinition();
    }
    return super.eGet(featureID, resolve, coreType);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public void eSet(int featureID, Object newValue)
  {
    switch (featureID)
    {
      case XSDPackage.XSD_MODEL_GROUP_DEFINITION__ANNOTATION:
        setAnnotation((XSDAnnotation)newValue);
        return;
      case XSDPackage.XSD_MODEL_GROUP_DEFINITION__MODEL_GROUP:
        setModelGroup((XSDModelGroup)newValue);
        return;
      case XSDPackage.XSD_MODEL_GROUP_DEFINITION__RESOLVED_MODEL_GROUP_DEFINITION:
        setResolvedModelGroupDefinition((XSDModelGroupDefinition)newValue);
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
      case XSDPackage.XSD_MODEL_GROUP_DEFINITION__ANNOTATION:
        setAnnotation((XSDAnnotation)null);
        return;
      case XSDPackage.XSD_MODEL_GROUP_DEFINITION__MODEL_GROUP:
        setModelGroup((XSDModelGroup)null);
        return;
      case XSDPackage.XSD_MODEL_GROUP_DEFINITION__RESOLVED_MODEL_GROUP_DEFINITION:
        setResolvedModelGroupDefinition((XSDModelGroupDefinition)null);
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
      case XSDPackage.XSD_MODEL_GROUP_DEFINITION__MODEL_GROUP_DEFINITION_REFERENCE:
        return isModelGroupDefinitionReference() != MODEL_GROUP_DEFINITION_REFERENCE_EDEFAULT;
      case XSDPackage.XSD_MODEL_GROUP_DEFINITION__ANNOTATION:
        return annotation != null;
      case XSDPackage.XSD_MODEL_GROUP_DEFINITION__MODEL_GROUP:
        return modelGroup != null;
      case XSDPackage.XSD_MODEL_GROUP_DEFINITION__RESOLVED_MODEL_GROUP_DEFINITION:
        return resolvedModelGroupDefinition != null;
    }
    return super.eIsSet(featureID);
  }

  @Override
  public String getQName()
  {
    XSDModelGroupDefinition resolvedModelGroupDefinition = getResolvedModelGroupDefinition();
    if (resolvedModelGroupDefinition == this)
    {
      return super.getQName();
    }
    else
    {
      return resolvedModelGroupDefinition.getQName(this);
    }
  }

  @Override
  public boolean isNamedComponentReference()
  {
    return isModelGroupDefinitionReference();
  }

  @Override
  public XSDNamedComponent getResolvedNamedComponent()
  {
    return getResolvedModelGroupDefinition();
  }

  @Override
  public boolean isCircular()
  {
    return analysisState == CIRCULAR;
  }

  @Override
  public XSDConcreteComponent cloneConcreteComponent(boolean deep, boolean shareDOM)
  {
    XSDModelGroupDefinitionImpl clonedModelGroupDefinition =
      (XSDModelGroupDefinitionImpl)getXSDFactory().createXSDModelGroupDefinition();
    clonedModelGroupDefinition.isReconciling = true;

    if (isModelGroupDefinitionReference())
    {
      XSDModelGroupDefinition theResolvedModelGroupDefinition = getResolvedModelGroupDefinition();
      clonedModelGroupDefinition.setResolvedModelGroupDefinition
        (createUnresolvedModelGroupDefinition
          (theResolvedModelGroupDefinition.getTargetNamespace(), theResolvedModelGroupDefinition.getName()));
    }
    else
    {
      if (getName() != null)
      {
        clonedModelGroupDefinition.setName(getName());
      }
    }

    if (deep)
    {
      if (getAnnotation() != null)
      {
        clonedModelGroupDefinition.setAnnotation((XSDAnnotation)getAnnotation().cloneConcreteComponent(deep, shareDOM));
      }

      if (getModelGroup() != null)
      {
        clonedModelGroupDefinition.setModelGroup((XSDModelGroup)getModelGroup().cloneConcreteComponent(deep, shareDOM));
      }
    }

    if (shareDOM && getElement() != null)
    {
      clonedModelGroupDefinition.setElement(getElement());
    }

    clonedModelGroupDefinition.isReconciling = shareDOM;
    return clonedModelGroupDefinition;
  }
} 

