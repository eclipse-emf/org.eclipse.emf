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
import java.util.List;
import java.util.ListIterator;

import org.w3c.dom.Element;
import org.w3c.dom.Node;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.EObjectEList;
import org.eclipse.emf.ecore.util.InternalEList;

import org.eclipse.xsd.XSDAnnotation;
import org.eclipse.xsd.XSDCompositor;
import org.eclipse.xsd.XSDConcreteComponent;
import org.eclipse.xsd.XSDDiagnostic;
import org.eclipse.xsd.XSDDiagnosticSeverity;
import org.eclipse.xsd.XSDFactory;
import org.eclipse.xsd.XSDModelGroup;
import org.eclipse.xsd.XSDModelGroupDefinition;
import org.eclipse.xsd.XSDPackage;
import org.eclipse.xsd.XSDParticle;
import org.eclipse.xsd.util.XSDConstants;


/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Model Group</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.xsd.impl.XSDModelGroupImpl#getCompositor <em>Compositor</em>}</li>
 *   <li>{@link org.eclipse.xsd.impl.XSDModelGroupImpl#getAnnotation <em>Annotation</em>}</li>
 *   <li>{@link org.eclipse.xsd.impl.XSDModelGroupImpl#getContents <em>Contents</em>}</li>
 *   <li>{@link org.eclipse.xsd.impl.XSDModelGroupImpl#getParticles <em>Particles</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class XSDModelGroupImpl 
  extends XSDTermImpl 
  implements XSDModelGroup
{
  /**
   * The default value of the '{@link #getCompositor() <em>Compositor</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getCompositor()
   * @generated
   * @ordered
   */
  protected static final XSDCompositor COMPOSITOR_EDEFAULT = XSDCompositor.ALL_LITERAL;

  /**
   * The offset of the flags representing the value of the '{@link #getCompositor() <em>Compositor</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  protected static final int COMPOSITOR_EFLAG_OFFSET = 8;

  /**
   * The flags representing the default value of the '{@link #getCompositor() <em>Compositor</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  protected static final int COMPOSITOR_EFLAG_DEFAULT = COMPOSITOR_EDEFAULT.ordinal() << COMPOSITOR_EFLAG_OFFSET;

  /**
   * The array of enumeration values for '{@link XSDCompositor Compositor}'
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  private static final XSDCompositor[] COMPOSITOR_EFLAG_VALUES = XSDCompositor.values();

  /**
   * The flags representing the value of the '{@link #getCompositor() <em>Compositor</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getCompositor()
   * @generated
   * @ordered
   */
  protected static final int COMPOSITOR_EFLAG = 0x3 << COMPOSITOR_EFLAG_OFFSET;

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
   * The cached value of the '{@link #getContents() <em>Contents</em>}' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getContents()
   * @generated
   * @ordered
   */
  protected EList<XSDParticle> contents;

  /**
   * The cached value of the '{@link #getParticles() <em>Particles</em>}' reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getParticles()
   * @generated
   * @ordered
   */
  protected EList<XSDParticle> particles;

  public static XSDModelGroup createModelGroup(Node node)
  {
    switch (XSDConstants.nodeType(node))
    {
      case XSDConstants.ALL_ELEMENT:
      {
        XSDModelGroup xsdModelGroup = XSDFactory.eINSTANCE.createXSDModelGroup();
        xsdModelGroup.setCompositor(XSDCompositor.ALL_LITERAL);
        xsdModelGroup.setElement((Element)node);
        return xsdModelGroup;
      }
      case XSDConstants.CHOICE_ELEMENT:
      {
        XSDModelGroup xsdModelGroup = XSDFactory.eINSTANCE.createXSDModelGroup();
        xsdModelGroup.setCompositor(XSDCompositor.CHOICE_LITERAL);
        xsdModelGroup.setElement((Element)node);
        return xsdModelGroup;
      }
      case XSDConstants.SEQUENCE_ELEMENT:
      {
        XSDModelGroup xsdModelGroup = XSDFactory.eINSTANCE.createXSDModelGroup();
        xsdModelGroup.setCompositor(XSDCompositor.SEQUENCE_LITERAL);
        xsdModelGroup.setElement((Element)node);
        return xsdModelGroup;
      }
    }

    return null;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected XSDModelGroupImpl()
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
    return XSDPackage.Literals.XSD_MODEL_GROUP;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public XSDCompositor getCompositor()
  {
    return COMPOSITOR_EFLAG_VALUES[(eFlags & COMPOSITOR_EFLAG) >>> COMPOSITOR_EFLAG_OFFSET];
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setCompositor(XSDCompositor newCompositor)
  {
    XSDCompositor oldCompositor = COMPOSITOR_EFLAG_VALUES[(eFlags & COMPOSITOR_EFLAG) >>> COMPOSITOR_EFLAG_OFFSET];
    if (newCompositor == null) newCompositor = COMPOSITOR_EDEFAULT;
    eFlags = eFlags & ~COMPOSITOR_EFLAG | newCompositor.ordinal() << COMPOSITOR_EFLAG_OFFSET;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, XSDPackage.XSD_MODEL_GROUP__COMPOSITOR, oldCompositor, newCompositor));
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
        msgs = ((InternalEObject)annotation).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - XSDPackage.XSD_MODEL_GROUP__ANNOTATION, null, msgs);
      if (newAnnotation != null)
        msgs = ((InternalEObject)newAnnotation).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - XSDPackage.XSD_MODEL_GROUP__ANNOTATION, null, msgs);
      msgs = basicSetAnnotation(newAnnotation, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, XSDPackage.XSD_MODEL_GROUP__ANNOTATION, newAnnotation, newAnnotation));
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
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, XSDPackage.XSD_MODEL_GROUP__ANNOTATION, oldAnnotation, newAnnotation);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EList<XSDParticle> getContents()
  {
    if (contents == null)
    {
      contents = new EObjectContainmentEList<XSDParticle>(XSDParticle.class, this, XSDPackage.XSD_MODEL_GROUP__CONTENTS);
    }
    return contents;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EList<XSDParticle> getParticles()
  {
    if (particles == null)
    {
      particles = new EObjectEList<XSDParticle>(XSDParticle.class, this, XSDPackage.XSD_MODEL_GROUP__PARTICLES);
    }
    return particles;
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
      case XSDPackage.XSD_MODEL_GROUP__ANNOTATION:
        return basicSetAnnotation(null, msgs);
      case XSDPackage.XSD_MODEL_GROUP__CONTENTS:
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
      case XSDPackage.XSD_MODEL_GROUP__COMPOSITOR:
        return getCompositor();
      case XSDPackage.XSD_MODEL_GROUP__ANNOTATION:
        return getAnnotation();
      case XSDPackage.XSD_MODEL_GROUP__CONTENTS:
        return getContents();
      case XSDPackage.XSD_MODEL_GROUP__PARTICLES:
        return getParticles();
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
      case XSDPackage.XSD_MODEL_GROUP__COMPOSITOR:
        setCompositor((XSDCompositor)newValue);
        return;
      case XSDPackage.XSD_MODEL_GROUP__ANNOTATION:
        setAnnotation((XSDAnnotation)newValue);
        return;
      case XSDPackage.XSD_MODEL_GROUP__CONTENTS:
        getContents().clear();
        getContents().addAll((Collection<? extends XSDParticle>)newValue);
        return;
      case XSDPackage.XSD_MODEL_GROUP__PARTICLES:
        getParticles().clear();
        getParticles().addAll((Collection<? extends XSDParticle>)newValue);
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
      case XSDPackage.XSD_MODEL_GROUP__COMPOSITOR:
        setCompositor(COMPOSITOR_EDEFAULT);
        return;
      case XSDPackage.XSD_MODEL_GROUP__ANNOTATION:
        setAnnotation((XSDAnnotation)null);
        return;
      case XSDPackage.XSD_MODEL_GROUP__CONTENTS:
        getContents().clear();
        return;
      case XSDPackage.XSD_MODEL_GROUP__PARTICLES:
        getParticles().clear();
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
      case XSDPackage.XSD_MODEL_GROUP__COMPOSITOR:
        return (eFlags & COMPOSITOR_EFLAG) != COMPOSITOR_EFLAG_DEFAULT;
      case XSDPackage.XSD_MODEL_GROUP__ANNOTATION:
        return annotation != null;
      case XSDPackage.XSD_MODEL_GROUP__CONTENTS:
        return contents != null && !contents.isEmpty();
      case XSDPackage.XSD_MODEL_GROUP__PARTICLES:
        return particles != null && !particles.isEmpty();
    }
    return super.eIsSet(featureID);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public String toString()
  {
    if (eIsProxy()) return super.toString();

    StringBuffer result = new StringBuffer(super.toString());
    result.append(" (compositor: ");
    result.append(COMPOSITOR_EFLAG_VALUES[(eFlags & COMPOSITOR_EFLAG) >>> COMPOSITOR_EFLAG_OFFSET]);
    result.append(')');
    return result.toString();
  }

  @Override
  public Element createElement()
  {
    XSDCompositor theCompositor = getCompositor();
    Element newElement = 
      createElement
        (XSDCompositor.ALL_LITERAL == theCompositor ?
           XSDConstants.ALL_ELEMENT :
           XSDCompositor.CHOICE_LITERAL == theCompositor ?
             XSDConstants.CHOICE_ELEMENT :
             XSDConstants.SEQUENCE_ELEMENT);

    setElement(newElement);
    for (XSDParticle xsdParticle : getContents())
    {
      Element newParticleElement = ((XSDConcreteComponentImpl)xsdParticle).createElement();
      newElement.appendChild(newParticleElement);
    }
    return newElement;
  }

  @Override
  protected void patch()
  {
    super.patch();

    List<XSDParticle> newParticles = new ArrayList<XSDParticle>(getContents());
    Collection<XSDParticle> remainingParticles = new ArrayList<XSDParticle>(getParticles());
    for (ListIterator<XSDParticle> particles = newParticles.listIterator(); particles.hasNext(); )
    {
      XSDParticle xsdParticle = particles.next();
      if (xsdParticle.getMaxOccurs() == 0)
      {
        particles.remove();
        remainingParticles.add(xsdParticle);
      }
      else
      {
        remainingParticles.remove(xsdParticle);
      }
    }

    if (!remainingParticles.isEmpty())
    {
      getParticles().removeAll(remainingParticles);
    }

    setListContentAndOrder(getParticles(), newParticles);
  }

  @Override
  public void validate()
  {
    super.validate();

    boolean hasBounds = !(getContainer() instanceof XSDModelGroupDefinition);
    String anchor = null;
    String contentType = null;
    switch (getCompositor().getValue())
    {
      case XSDCompositor.ALL:
      {
        anchor = "element-all";
        contentType = "all";

        for (XSDParticle xsdParticle : getContents())
        {
          switch (xsdParticle.getMaxOccurs())
          {
            case 0:
            case 1:
            {
              break;
            }
            default:
            {
              createDiagnostic(XSDDiagnosticSeverity.ERROR_LITERAL, "cos-all-limited.2");
            }
          }
        }
        break;
      }
      case XSDCompositor.SEQUENCE:
      {
        anchor = "element-sequence";
        contentType = "explicitGroup";
        break;
      }
      case XSDCompositor.CHOICE:
      {
        anchor = "element-choice";
        contentType = "explicitGroup";
        break;
      }
    }

    Element theElement = getElement();
    if (theElement != null)
    {
      String [] attributes =
        hasBounds ?
          new String []
          {
            XSDConstants.ID_ATTRIBUTE,
            XSDConstants.MAXOCCURS_ATTRIBUTE,
            XSDConstants.MINOCCURS_ATTRIBUTE
          } :
          new String []
          {
            XSDConstants.ID_ATTRIBUTE,
          };

      checkAttributes(XSDConstants.PART1, anchor, theElement, attributes);
      checkBuiltInTypeConstraint("ID", null, XSDConstants.PART1, anchor, theElement, XSDConstants.ID_ATTRIBUTE, false);

      if (hasBounds)
      {
        checkAttributeTypeConstraint
          (contentType,
           "maxOccurs",
           null,
           XSDConstants.PART1,
           anchor,
           theElement,
           XSDConstants.MAXOCCURS_ATTRIBUTE,
           false);

        checkAttributeTypeConstraint
          (contentType,
           "minOccurs",
           null,
           XSDConstants.PART1,
           anchor,
           theElement,
           XSDConstants.MINOCCURS_ATTRIBUTE,
           false);
      }

      checkComplexContent(contentType, XSDConstants.PART1, anchor, theElement);
    }
    else
    {
      // EATM validate synthesized models too
    }
  }

  public void validateRoot()
  {
    XSDConcreteComponent diagnosticTarget = this;
    XSDParticle xsdParticle;
    if (getContainer() instanceof XSDParticle)
    {
      xsdParticle = (XSDParticle)getContainer();
      if (xsdParticle.eContainmentFeature() == XSDPackage.Literals.XSD_COMPLEX_TYPE_DEFINITION__SYNTHETIC_PARTICLE)
      {
        diagnosticTarget = xsdParticle.getContainer();
      }
    }
    else
    {
      xsdParticle = XSDFactory.eINSTANCE.createXSDParticle();
      xsdParticle.setTerm(this);
    }

    XSDParticleImpl.XSDNFA dfa = (XSDParticleImpl.XSDNFA)xsdParticle.getDFA();
    Collection<XSDDiagnostic> diagnostics = dfa.getDiagnostics();
    if (!diagnostics.isEmpty())
    {
      for (XSDDiagnostic xsdDiagnostic : diagnostics)
      {
        xsdDiagnostic.setPrimaryComponent(diagnosticTarget);
        xsdDiagnostic.setNode(diagnosticTarget.getElement());
      }
      getDiagnostics().addAll(diagnostics);
    }
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
  protected void handleUnreconciledElement(Element child, List<XSDConcreteComponent> newContents, List<XSDConcreteComponent> remainingContents)
  {
    XSDParticle xsdParticle = XSDParticleImpl.createParticle(child);
    if (xsdParticle != null)
    {
      newContents.add(xsdParticle);
    }
    else
    {
      XSDAnnotation xsdAnnotation = XSDAnnotationImpl.createAnnotation(child);
      if (xsdAnnotation != null)
      {
        if (newContents.isEmpty())
        {
          newContents.add(xsdAnnotation);
        }
      }
    }
  }

  @Override
  protected void handleReconciliation(List<XSDConcreteComponent> newContents, List<XSDConcreteComponent> remainingContents)
  {
    handleAnnotationReconciliation(XSDPackage.Literals.XSD_MODEL_GROUP__ANNOTATION, newContents, remainingContents);

    if (!remainingContents.isEmpty())
    {
      getContents().removeAll(remainingContents);
    }

    if (!newContents.isEmpty())
    {
      @SuppressWarnings("unchecked") List<XSDParticle> list = (List<XSDParticle>)(List<?>)newContents;
      setListContentAndOrder(getContents(), list);
    }
  }

  @Override
  protected void changeAttribute(EAttribute eAttribute)
  {
    super.changeAttribute(eAttribute);
    Element theElement = getElement();
    if (eAttribute == null || eAttribute == XSDPackage.Literals.XSD_MODEL_GROUP__COMPOSITOR)
    {
      if (theElement != null && eAttribute != null && !isReconciling)
      {
        XSDCompositor theCompositor = getCompositor();
        Element newElement =
          createElement
            (XSDCompositor.ALL_LITERAL == theCompositor ?
               XSDConstants.ALL_ELEMENT :
               XSDCompositor.CHOICE_LITERAL == theCompositor ?
                 XSDConstants.CHOICE_ELEMENT :
                 XSDConstants.SEQUENCE_ELEMENT);

        forceReplace(newElement, theElement);
        setElement(newElement);

        XSDConcreteComponentImpl container = (XSDConcreteComponentImpl)getContainer();
        if (container instanceof XSDParticle)
        {
          ((XSDParticle)container).setElement(newElement);
          container.changeAttribute(null);
        }
      }
    }
  }

  @Override
  protected void adoptContent(EReference eReference, XSDConcreteComponent xsdConcreteComponent)
  {
    super.adoptContent(eReference, xsdConcreteComponent);
    if (eReference == XSDPackage.Literals.XSD_MODEL_GROUP__CONTENTS)
    {
      traverseToRootForPatching();
    }
  }

  @Override
  protected void orphanContent(EReference eReference, XSDConcreteComponent xsdConcreteComponent)
  {
    super.orphanContent(eReference, xsdConcreteComponent);
    if (eReference == XSDPackage.Literals.XSD_MODEL_GROUP__CONTENTS)
    {
      traverseToRootForPatching();
    }
  }

  @Override
  public void moveContent(EReference eReference, XSDConcreteComponent xsdConcreteComponent)
  {
    super.moveContent(eReference, xsdConcreteComponent);
    if (eReference == XSDPackage.Literals.XSD_MODEL_GROUP__CONTENTS)
    {
      traverseToRootForPatching();
    }
  }

  @Override
  public XSDConcreteComponent cloneConcreteComponent(boolean deep, boolean shareDOM)
  {
    XSDModelGroupImpl clonedModelGroup =
      (XSDModelGroupImpl)getXSDFactory().createXSDModelGroup();
    clonedModelGroup.isReconciling = true;

    clonedModelGroup.setCompositor(getCompositor());

    if (deep)
    {
      if (getAnnotation() != null)
      {
        clonedModelGroup.setAnnotation((XSDAnnotation)getAnnotation().cloneConcreteComponent(deep, shareDOM));
      }
      if (!getContents().isEmpty())
      {
        clonedModelGroup.getContents().addAll(cloneConcreteComponents(getContents(), true, shareDOM));
      }
    }

    if (shareDOM && getElement() != null)
    {
      clonedModelGroup.setElement(getElement());
    }

    clonedModelGroup.isReconciling = shareDOM;
    return clonedModelGroup;
  }
}
