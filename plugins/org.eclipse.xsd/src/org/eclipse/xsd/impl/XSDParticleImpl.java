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
 * $Id: XSDParticleImpl.java,v 1.19 2007/11/26 12:20:55 emerks Exp $
 */
package org.eclipse.xsd.impl;


import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.w3c.dom.Element;
import org.w3c.dom.Node;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.UniqueEList;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.xsd.XSDComponent;
import org.eclipse.xsd.XSDCompositor;
import org.eclipse.xsd.XSDConcreteComponent;
import org.eclipse.xsd.XSDConstraint;
import org.eclipse.xsd.XSDDiagnostic;
import org.eclipse.xsd.XSDDiagnosticSeverity;
import org.eclipse.xsd.XSDElementDeclaration;
import org.eclipse.xsd.XSDFactory;
import org.eclipse.xsd.XSDModelGroup;
import org.eclipse.xsd.XSDModelGroupDefinition;
import org.eclipse.xsd.XSDPackage;
import org.eclipse.xsd.XSDParticle;
import org.eclipse.xsd.XSDParticleContent;
import org.eclipse.xsd.XSDPlugin;
import org.eclipse.xsd.XSDSimpleTypeDefinition;
import org.eclipse.xsd.XSDTerm;
import org.eclipse.xsd.XSDTypeDefinition;
import org.eclipse.xsd.XSDWildcard;
import org.eclipse.xsd.util.XSDConstants;
import org.eclipse.xsd.util.XSDSwitch;


/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Particle</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.xsd.impl.XSDParticleImpl#getMinOccurs <em>Min Occurs</em>}</li>
 *   <li>{@link org.eclipse.xsd.impl.XSDParticleImpl#getMaxOccurs <em>Max Occurs</em>}</li>
 *   <li>{@link org.eclipse.xsd.impl.XSDParticleImpl#getContent <em>Content</em>}</li>
 *   <li>{@link org.eclipse.xsd.impl.XSDParticleImpl#getTerm <em>Term</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class XSDParticleImpl 
  extends XSDComplexTypeContentImpl 
  implements XSDParticle
{
  protected static final int MAXIMUM_STATES = 10000;

  /**
   * The default value of the '{@link #getMinOccurs() <em>Min Occurs</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getMinOccurs()
   * @generated
   * @ordered
   */
  protected static final int MIN_OCCURS_EDEFAULT = 1;

  /**
   * The cached value of the '{@link #getMinOccurs() <em>Min Occurs</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getMinOccurs()
   * @generated
   * @ordered
   */
  protected int minOccurs = MIN_OCCURS_EDEFAULT;

  /**
   * The flag representing whether the Min Occurs attribute has been set.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  protected static final int MIN_OCCURS_ESETFLAG = 1 << 8;

  /**
   * The default value of the '{@link #getMaxOccurs() <em>Max Occurs</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getMaxOccurs()
   * @generated
   * @ordered
   */
  protected static final int MAX_OCCURS_EDEFAULT = 1;

  /**
   * The cached value of the '{@link #getMaxOccurs() <em>Max Occurs</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getMaxOccurs()
   * @generated
   * @ordered
   */
  protected int maxOccurs = MAX_OCCURS_EDEFAULT;

  /**
   * The flag representing whether the Max Occurs attribute has been set.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  protected static final int MAX_OCCURS_ESETFLAG = 1 << 9;

  /**
   * The cached value of the '{@link #getContent() <em>Content</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getContent()
   * @generated
   * @ordered
   */
  protected XSDParticleContent content;

  /**
   * The cached value of the '{@link #getTerm() <em>Term</em>}' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getTerm()
   * @generated
   * @ordered
   */
  protected XSDTerm term;

  public static XSDParticle createParticle(Node node)
  {
    XSDParticleContent xsdParticleContent = XSDParticleContentImpl.createParticleContent(node);
    if (xsdParticleContent != null)
    {
      XSDParticle xsdParticle = XSDFactory.eINSTANCE.createXSDParticle();
      xsdParticle.setElement((Element)node);
      xsdParticle.setContent(xsdParticleContent);
      return xsdParticle;
    }

    return null;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected XSDParticleImpl()
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
    return XSDPackage.Literals.XSD_PARTICLE;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public int getMinOccurs()
  {
    return minOccurs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setMinOccurs(int newMinOccurs)
  {
    int oldMinOccurs = minOccurs;
    minOccurs = newMinOccurs;
    boolean oldMinOccursESet = (eFlags & MIN_OCCURS_ESETFLAG) != 0;
    eFlags |= MIN_OCCURS_ESETFLAG;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, XSDPackage.XSD_PARTICLE__MIN_OCCURS, oldMinOccurs, minOccurs, !oldMinOccursESet));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void unsetMinOccurs()
  {
    int oldMinOccurs = minOccurs;
    boolean oldMinOccursESet = (eFlags & MIN_OCCURS_ESETFLAG) != 0;
    minOccurs = MIN_OCCURS_EDEFAULT;
    eFlags &= ~MIN_OCCURS_ESETFLAG;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.UNSET, XSDPackage.XSD_PARTICLE__MIN_OCCURS, oldMinOccurs, MIN_OCCURS_EDEFAULT, oldMinOccursESet));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public boolean isSetMinOccurs()
  {
    return (eFlags & MIN_OCCURS_ESETFLAG) != 0;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public int getMaxOccurs()
  {
    return maxOccurs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setMaxOccurs(int newMaxOccurs)
  {
    int oldMaxOccurs = maxOccurs;
    maxOccurs = newMaxOccurs;
    boolean oldMaxOccursESet = (eFlags & MAX_OCCURS_ESETFLAG) != 0;
    eFlags |= MAX_OCCURS_ESETFLAG;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, XSDPackage.XSD_PARTICLE__MAX_OCCURS, oldMaxOccurs, maxOccurs, !oldMaxOccursESet));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void unsetMaxOccurs()
  {
    int oldMaxOccurs = maxOccurs;
    boolean oldMaxOccursESet = (eFlags & MAX_OCCURS_ESETFLAG) != 0;
    maxOccurs = MAX_OCCURS_EDEFAULT;
    eFlags &= ~MAX_OCCURS_ESETFLAG;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.UNSET, XSDPackage.XSD_PARTICLE__MAX_OCCURS, oldMaxOccurs, MAX_OCCURS_EDEFAULT, oldMaxOccursESet));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public boolean isSetMaxOccurs()
  {
    return (eFlags & MAX_OCCURS_ESETFLAG) != 0;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public XSDParticleContent getContent()
  {
    return content;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setContent(XSDParticleContent newContent)
  {
    if (newContent != content)
    {
      NotificationChain msgs = null;
      if (content != null)
        msgs = ((InternalEObject)content).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - XSDPackage.XSD_PARTICLE__CONTENT, null, msgs);
      if (newContent != null)
        msgs = ((InternalEObject)newContent).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - XSDPackage.XSD_PARTICLE__CONTENT, null, msgs);
      msgs = basicSetContent(newContent, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, XSDPackage.XSD_PARTICLE__CONTENT, newContent, newContent));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetContent(XSDParticleContent newContent, NotificationChain msgs)
  {
    XSDParticleContent oldContent = content;
    content = newContent;
    if (eNotificationRequired())
    {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, XSDPackage.XSD_PARTICLE__CONTENT, oldContent, newContent);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public XSDTerm getTerm()
  {
    return term;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setTerm(XSDTerm newTerm)
  {
    XSDTerm oldTerm = term;
    term = newTerm;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, XSDPackage.XSD_PARTICLE__TERM, oldTerm, term));
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
      case XSDPackage.XSD_PARTICLE__CONTENT:
        return basicSetContent(null, msgs);
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
      case XSDPackage.XSD_PARTICLE__MIN_OCCURS:
        return new Integer(getMinOccurs());
      case XSDPackage.XSD_PARTICLE__MAX_OCCURS:
        return new Integer(getMaxOccurs());
      case XSDPackage.XSD_PARTICLE__CONTENT:
        return getContent();
      case XSDPackage.XSD_PARTICLE__TERM:
        return getTerm();
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
      case XSDPackage.XSD_PARTICLE__MIN_OCCURS:
        setMinOccurs(((Integer)newValue).intValue());
        return;
      case XSDPackage.XSD_PARTICLE__MAX_OCCURS:
        setMaxOccurs(((Integer)newValue).intValue());
        return;
      case XSDPackage.XSD_PARTICLE__CONTENT:
        setContent((XSDParticleContent)newValue);
        return;
      case XSDPackage.XSD_PARTICLE__TERM:
        setTerm((XSDTerm)newValue);
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
      case XSDPackage.XSD_PARTICLE__MIN_OCCURS:
        unsetMinOccurs();
        return;
      case XSDPackage.XSD_PARTICLE__MAX_OCCURS:
        unsetMaxOccurs();
        return;
      case XSDPackage.XSD_PARTICLE__CONTENT:
        setContent((XSDParticleContent)null);
        return;
      case XSDPackage.XSD_PARTICLE__TERM:
        setTerm((XSDTerm)null);
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
      case XSDPackage.XSD_PARTICLE__MIN_OCCURS:
        return isSetMinOccurs();
      case XSDPackage.XSD_PARTICLE__MAX_OCCURS:
        return isSetMaxOccurs();
      case XSDPackage.XSD_PARTICLE__CONTENT:
        return content != null;
      case XSDPackage.XSD_PARTICLE__TERM:
        return term != null;
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
    result.append(" (minOccurs: ");
    if ((eFlags & MIN_OCCURS_ESETFLAG) != 0) result.append(minOccurs); else result.append("<unset>");
    result.append(", maxOccurs: ");
    if ((eFlags & MAX_OCCURS_ESETFLAG) != 0) result.append(maxOccurs); else result.append("<unset>");
    result.append(')');
    return result.toString();
  }

  @Override
  public Element createElement()
  {
    Element newElement = null;
    XSDParticleContent theContent = getContent();
    if (theContent != null)
    {
      newElement = ((XSDConcreteComponentImpl)theContent).createElement();
      setElement(newElement);
    }
    return newElement;
  }

  @Override
  protected void patch()
  {
    super.patch();
    patchHelper();
  }

  protected void patchHelper()
  {
    XSDParticleContent theContent =  getContent();
    XSDTerm newTerm = null;
    if (theContent instanceof XSDModelGroupDefinition)
    {
      XSDModelGroupDefinition xsdModelGroupDefinition = (XSDModelGroupDefinition)theContent;
      newTerm = xsdModelGroupDefinition.getResolvedModelGroupDefinition().getModelGroup();
    }
    else if (theContent instanceof XSDElementDeclaration)
    {
      XSDElementDeclaration xsdElementDeclaration = (XSDElementDeclaration)theContent;
      newTerm = xsdElementDeclaration.getResolvedElementDeclaration();
    }
    else
    {
      newTerm = (XSDTerm)theContent;
    }

    if (getTerm() != newTerm)
    {
      setTerm(newTerm);
    }
  }

  @Override
  public boolean analyze()
  {
    xsdNFA = null;
    return super.analyze();
  }

  @Override
  public void validate()
  {
    super.validate();

    if (getMaxOccurs() != -1)
    {
      if (getMinOccurs() > getMaxOccurs())
      {
        createDiagnostic(XSDDiagnosticSeverity.ERROR_LITERAL, "coss-particle.2.1");
      }
    }
  }

  @Override
  protected Collection<Element> getContentNodes(Element changedElement)
  {
    return Collections.singleton(getElement());
  }

  @Override
  protected void reconcileAttributes(Element changedElement)
  {
    super.reconcileAttributes(changedElement);

    if (changedElement == getElement())
    {
      if (changedElement.hasAttributeNS(null, XSDConstants.MINOCCURS_ATTRIBUTE))
      {
        String newMinOccurs = changedElement.getAttributeNS(null, XSDConstants.MINOCCURS_ATTRIBUTE);
        try
        {
          int newMinOccursValue = Integer.parseInt(newMinOccurs);
          if (!isSetMinOccurs() || newMinOccursValue != getMinOccurs())
          {
            setMinOccurs(newMinOccursValue);
          }
        }
        catch (NumberFormatException exception)
        {
          // Ignore
        }
      }
      else if (isSetMinOccurs())
      {
        unsetMinOccurs();
      }

      if (changedElement.hasAttributeNS(null, XSDConstants.MAXOCCURS_ATTRIBUTE))
      {
        String newMaxOccurs = changedElement.getAttributeNS(null, XSDConstants.MAXOCCURS_ATTRIBUTE);
        try
        {
          int newMaxOccursValue = "unbounded".equals(newMaxOccurs) ?  -1 : Integer.parseInt(newMaxOccurs);
          if (!isSetMaxOccurs() || newMaxOccursValue != getMaxOccurs())
          {
            setMaxOccurs(newMaxOccursValue);
          }
        }
        catch (NumberFormatException exception)
        {
          // Ignore
        }
      }
      else if (isSetMaxOccurs())
      {
        unsetMaxOccurs();
      }

      XSDParticleContent theContent = getContent();
      if (theContent != null)
      {
        theContent.elementAttributesChanged(changedElement);
      }
    }
  }

  @Override
  protected void reconcileContents(Element changedElement)
  {
    super.reconcileContents(changedElement);
    XSDParticleContent theContent = getContent();
    if (theContent != null)
    {
      theContent.elementContentsChanged(changedElement);

      XSDTerm newTerm =
        (XSDTerm)
          new XSDSwitch<Object>()
          {
            @Override
            public Object caseXSDElementDeclaration(XSDElementDeclaration xsdElementDeclaration)
            {
              return xsdElementDeclaration.getResolvedElementDeclaration();
            }
            @Override
            public Object caseXSDModelGroupDefinition(XSDModelGroupDefinition xsdModelGroupDefinition)
            {
              return xsdModelGroupDefinition.getResolvedModelGroupDefinition().getModelGroup();
            }
            @Override
            public Object caseXSDWildcard(XSDWildcard xsdWildcard)
            {
              return xsdWildcard;
            }
            @Override
            public Object caseXSDModelGroup(XSDModelGroup xsdModelGroup)
            {
              return xsdModelGroup;
            }
          }.doSwitch(theContent);

      if (newTerm != getTerm())
      {
        setTerm(newTerm);
      }
    }
  }

  @Override
  protected void changeAttribute(EAttribute eAttribute)
  {
    if (isReconciling)
    {
      return;
    }

    super.changeAttribute(eAttribute);
    Element theElement = getElement();
    if (theElement != null)
    {
      if (eAttribute == null || eAttribute == XSDPackage.Literals.XSD_PARTICLE__MIN_OCCURS)
      {
        niceSetAttribute
          (theElement, 
           XSDConstants.MINOCCURS_ATTRIBUTE, 
           isSetMinOccurs() ? Integer.toString(getMinOccurs()) : null);
      }
      if (eAttribute == null || eAttribute == XSDPackage.Literals.XSD_PARTICLE__MAX_OCCURS)
      {
        niceSetAttribute
          (theElement, 
           XSDConstants.MAXOCCURS_ATTRIBUTE, 
           isSetMaxOccurs() ? getMaxOccurs() == -1 ? "unbounded" : Integer.toString(getMaxOccurs()) : null);
      }
    }
  }

  @Override
  protected void adoptContent(EReference eReference, XSDConcreteComponent xsdConcreteComponent)
  {
    super.adoptContent(eReference, xsdConcreteComponent);
    if (eReference == XSDPackage.Literals.XSD_PARTICLE__CONTENT)
    {
      patchHelper();
      traverseToRootForPatching();
    }
  }

  @Override
  protected void orphanContent(EReference eReference, XSDConcreteComponent xsdConcreteComponent)
  {
    super.orphanContent(eReference, xsdConcreteComponent);
    if (eReference == XSDPackage.Literals.XSD_PARTICLE__CONTENT)
    {
      patchHelper();
      traverseToRootForPatching();
    }
  }

  public static class XSDNFA implements XSDParticle.DFA
  {
    public static class StateImpl implements XSDParticle.DFA.State
    {
      protected UniqueEList<XSDParticle.DFA.Transition> transitions;
      protected boolean isAccepting;

      public StateImpl()
      {
        transitions = 
          new UniqueEList<XSDParticle.DFA.Transition>()
          {
            private static final long serialVersionUID = 1L;

            Set<XSDParticle.DFA.Transition> set = new HashSet<XSDParticle.DFA.Transition>();

            @Override
            protected void didAdd(int index, XSDParticle.DFA.Transition newObject)
            {
              set.add(newObject);
            }

            @Override
            protected void didRemove(int index, XSDParticle.DFA.Transition oldObject)
            {
              set.remove(oldObject);
            }

            @Override
            public boolean contains(Object object)
            {
              return set.contains(object);
            }
          };
      }

      public List<XSDParticle.DFA.Transition> getTransitions()
      {
        return transitions;
      }

      public boolean isAccepting()
      {
        return isAccepting;
      }

      public void setAccepting(boolean isAccepting)
      {
        this.isAccepting = isAccepting;
      }

      protected TransitionImpl createdTransition;

      public XSDParticle.DFA.Transition createTransition(XSDParticle xsdParticle, XSDParticle.DFA.State state)
      {
        if (createdTransition == null)
        {
          createdTransition = new TransitionImpl(xsdParticle, state);
        }
        else
        {
          createdTransition.setParticle(xsdParticle);
          createdTransition.setState(state);
        }

        if (transitions.add(createdTransition))
        {
          XSDParticle.DFA.Transition result = createdTransition;
          createdTransition = null;
          return result;
        }
        else
        {
          return null;
        }
      }

      public XSDParticle.DFA.Transition accept(String namespaceURI, String localName)
      {
        for (Transition transition : getTransitions())
        {
          XSDParticle xsdParticle = transition.getParticle();
          XSDTerm xsdTerm = xsdParticle.getTerm();
          if (xsdTerm instanceof XSDElementDeclaration)
          {
            XSDElementDeclaration xsdElementDeclaration = (XSDElementDeclaration)xsdTerm;
            if ((namespaceURI == null ? 
                   xsdElementDeclaration.getTargetNamespace() == null : 
                   namespaceURI.equals(xsdElementDeclaration.getTargetNamespace())) &&
                  localName.equals(xsdElementDeclaration.getName()))
            {
              return transition;
            }
          }
          else if (xsdTerm instanceof XSDWildcard)
          {
            XSDWildcard xsdWildcard = (XSDWildcard)xsdTerm;
            if (xsdWildcard.allows(namespaceURI))
            {
              return transition;
            }
          }
        }

        return null;
      }
    }

    public static class TransitionImpl implements XSDParticle.DFA.Transition
    {
      protected XSDParticle xsdParticle;
      protected XSDParticle.DFA.State state;

      public TransitionImpl(XSDParticle xsdParticle, XSDParticle.DFA.State state)
      {
        this.xsdParticle = xsdParticle;
        this.state = state;
      }

      public XSDParticle.DFA.State getState()
      {
        return state;
      }

      public void setState(XSDParticle.DFA.State state)
      {
        this.state = state;
      }

      public XSDParticle getParticle()
      {
        return xsdParticle;
      }

      public void setParticle(XSDParticle xsdParticle)
      {
        this.xsdParticle = xsdParticle;
      }

      @Override
      public int hashCode()
      {
        return 
          (xsdParticle == null ? 0 : xsdParticle.hashCode()) ^ (state == null ? 0 : state.hashCode());
      }

      @Override
      public boolean equals(Object that)
      {
        if (that instanceof TransitionImpl)
        {
          TransitionImpl otherTransition = (TransitionImpl)that;
          return 
            xsdParticle == otherTransition.xsdParticle &&
            state == otherTransition.state;
        }
        else
        {
          return false;
        }
      }
    }

    protected List<State> states = new ArrayList<State>();
    protected StateImpl initialState;
    protected StateImpl finalState;
    protected XSDParticle xsdParticle;
    protected StateImpl currentState;
    protected Set<XSDModelGroup> visitedModelGroups;
    protected Collection<XSDDiagnostic> diagnostics;
    protected boolean isApproximate;

    protected XSDNFA(boolean isApproximate)
    {
      this.isApproximate = isApproximate;
      currentState = initialState = createState(null);
      finalState = createState(null);
    }

    protected XSDNFA(Set<XSDModelGroup> visitedModelGroups, XSDParticle xsdParticle, XSDTerm xsdTerm, boolean isApproximate)
    {
      this.isApproximate = isApproximate;
      this.visitedModelGroups = visitedModelGroups;
      this.xsdParticle = xsdParticle;
      currentState = initialState = createState(null);
      finalState = createState(xsdTerm);
      if (!visitedModelGroups.contains(xsdTerm))
      {
        initialize(xsdTerm);
      }
    }

    public XSDNFA(Set<XSDModelGroup> visitedModelGroups, XSDParticle xsdParticle, boolean isApproximate)
    {
      this.isApproximate = isApproximate;
      this.visitedModelGroups = visitedModelGroups;
      this.xsdParticle = xsdParticle;
      currentState = initialState = createState(null);
      finalState = createState(xsdParticle);
      initialize(xsdParticle);
    }

    public XSDNFA(XSDParticle xsdParticle, boolean isApproximate)
    {
      this.isApproximate = isApproximate;
      this.visitedModelGroups = new HashSet<XSDModelGroup>();
      this.xsdParticle = xsdParticle;
      currentState = initialState = createState(null);
      finalState = createState(xsdParticle);
      initialize(xsdParticle);
    }

    protected void checkBadTransitions()
    {
      for (State state : getStates())
      {
        for (Transition transition : state.getTransitions())
        {
          if (!getStates().contains(transition.getState()))
          {
            Thread.dumpStack();
          }
        }
      }
    }

    protected XSDNFA(XSDNFA original, boolean isApproximate)
    {
      this.isApproximate = isApproximate;
      states = new ArrayList<State>();
      xsdParticle = original.getParticle();

      Map<State, State> stateCloneMap = new HashMap<State, State>();
      List<State> originalStates = original.getStates();
      for (int i = 0, size = originalStates.size(); i < size; ++i)
      {
        State originalState = originalStates.get(i); 
        StateImpl clonedState = createState(null);
        stateCloneMap.put(originalState, clonedState);
        clonedState.setAccepting(originalState.isAccepting());
      }

      currentState = initialState = (StateImpl)stateCloneMap.get(original.getInitialState());
      finalState = (StateImpl)stateCloneMap.get(original.getFinalState());

      for (int i = 0, size = originalStates.size(); i < size; ++i)
      {
        State originalState = originalStates.get(i); 
        List<Transition> originalTransitions = originalState.getTransitions();
        for (int j = 0, transitionSize = originalTransitions.size(); j < transitionSize; ++j)
        {
          Transition originalTransition = originalTransitions.get(j); 
          ((StateImpl)stateCloneMap.get(originalState)).createTransition
            (originalTransition.getParticle(), stateCloneMap.get(originalTransition.getState()));
        }
      }
    }

    protected StateImpl createState(XSDComponent associatedComponent)
    {
      StateImpl result = new StateImpl();
      states.add(result);
      return result;
    }

    protected void initialize(XSDComponent xsdComponent)
    {
      if (xsdComponent instanceof XSDElementDeclaration)
      {
        XSDElementDeclaration xsdElementDeclaration = (XSDElementDeclaration)xsdComponent;
        for (XSDElementDeclaration substititonGroupMember : xsdElementDeclaration.getSubstitutionGroup())
        {
          if (substititonGroupMember == xsdElementDeclaration)
          {
            initialState.createTransition(xsdParticle, finalState);
          }
          else
          {
            XSDParticle substititonGroupMemberParticle = ((XSDParticleImpl)xsdParticle).getXSDFactory().createXSDParticle();
            substititonGroupMemberParticle.setTerm(substititonGroupMember);
            initialState.createTransition(substititonGroupMemberParticle, finalState);
          }
        }
      }
      else if (xsdComponent instanceof XSDWildcard)
      {
        initialState.createTransition(xsdParticle, finalState);
      }
      else if (xsdComponent instanceof XSDModelGroup)
      {
        XSDModelGroup xsdModelGroup = (XSDModelGroup)xsdComponent;
        visitedModelGroups.add(xsdModelGroup);
        switch (xsdModelGroup.getCompositor().getValue())
        {
          case XSDCompositor.SEQUENCE:
          {
            StateImpl previousState = initialState;
            for (XSDParticle xsdParticle : xsdModelGroup.getParticles())
            {
              XSDNFA particleNFA = new XSDNFA(visitedModelGroups, xsdParticle, isApproximate);
              propagateStates(particleNFA);
              previousState.createTransition(null, particleNFA.getInitialState());
              previousState = (StateImpl)particleNFA.getFinalState();
              if (getStates().size() > MAXIMUM_STATES)
              {
                break;
              }
            }
            previousState.createTransition(null, finalState);
            break;
          }
          case XSDCompositor.CHOICE:
          {
            for (XSDParticle xsdParticle : xsdModelGroup.getParticles())
            {
              XSDNFA particleNFA = new XSDNFA(visitedModelGroups, xsdParticle, isApproximate);
              propagateStates(particleNFA);
              initialState.createTransition(null, particleNFA.getInitialState());
              ((StateImpl)particleNFA.getFinalState()).createTransition(null, finalState);
              if (getStates().size() > MAXIMUM_STATES)
              {
                break;
              }
            }
            break;
          }
          case XSDCompositor.ALL:
          default:
          {
            if (visitedModelGroups.size() > 1)
            {
              if (diagnostics == null)
              {
                diagnostics = new ArrayList<XSDDiagnostic>();
              }

              XSDDiagnostic xsdDiagnostic = XSDFactory.eINSTANCE.createXSDDiagnostic();
              xsdDiagnostic.setSeverity(XSDDiagnosticSeverity.ERROR_LITERAL);
              xsdDiagnostic.setMessage
                (XSDPlugin.INSTANCE.getString
                   ("_UI_XSDError_message", 
                       new Object [] 
                       {
                         populateDiagnostic(xsdDiagnostic, "cos-all-limited.1", null)
                       }));

              diagnostics.add(xsdDiagnostic);
            }

            if (xsdModelGroup.getParticles().size() == 0)
            {
              initialState.createTransition(null, finalState);
            }
            else
            {
              List<XSDNFA> particleNFAs = new ArrayList<XSDNFA>(xsdModelGroup.getParticles().size());
              for (XSDParticle xsdParticle : xsdModelGroup.getParticles())
              {
                XSDNFA newParticleNFA = new XSDNFA(visitedModelGroups, xsdParticle, isApproximate);
                particleNFAs.add(newParticleNFA);
                if (getStates().size() > MAXIMUM_STATES)
                {
                  break;
                }
              }

              if (xsdModelGroup.getParticles().size() > 4)
              {
                // diagnostics = new ArrayList();
                // XSDDiagnostic xsdDiagnostic = XSDFactory.eINSTANCE.createXSDDiagnostic();
                // xsdDiagnostic.setSeverity(XSDDiagnosticSeverity.ERROR_LITERAL);
                // xsdDiagnostic.setMessage
                //   (XSDPlugin.INSTANCE.getString
                //      ("_UI_XSDError_message", 
                //       new Object [] 
                //       {
                //         XSDPlugin.INSTANCE.getString
                //           ("coss-particle.0.2", new Object [] { new Integer(xsdModelGroup.getParticles().size()) })
                //       }));
                // diagnostics.add(xsdDiagnostic);

                isApproximate = true;

                // Treat it like a repeating choice.
                //
                for (XSDParticle xsdParticle : xsdModelGroup.getParticles())
                {
                  XSDNFA particleNFA = new XSDNFA(visitedModelGroups, xsdParticle, isApproximate);
                  propagateStates(particleNFA);
                  initialState.createTransition(null, particleNFA.getInitialState());
                  ((StateImpl)particleNFA.getFinalState()).createTransition(null, finalState);
                  ((StateImpl)particleNFA.getFinalState()).createTransition(null, initialState);
                  if (getStates().size() > MAXIMUM_STATES)
                  {
                    break;
                  }
                }
              }
              else
              {
                XSDNFA xsdNFA = createPermutations(xsdModelGroup.getParticles(), particleNFAs, new HashMap<List<XSDParticle>, XSDNFA>());

                propagateStates(xsdNFA);

                initialState.createTransition(null, xsdNFA.getInitialState());
                ((StateImpl)xsdNFA.getFinalState()).createTransition(null, finalState);
              }
            }
            break;
          }
        }
      }
      else if (xsdComponent instanceof XSDParticle)
      {
        XSDParticle xsdParticle = (XSDParticle)xsdComponent;
        int minOccurs = xsdParticle.getMinOccurs();
        int maxOccurs = xsdParticle.getMaxOccurs();
        int count = maxOccurs == -1 || isApproximate ? minOccurs : maxOccurs;
        if (minOccurs <= count)
        {
          List<XSDNFA> termNFAs = new ArrayList<XSDNFA>(count);
          XSDNFA firstTermNFA = new XSDNFA(visitedModelGroups, xsdParticle, xsdParticle.getTerm(), isApproximate);
          propagateStates(firstTermNFA);
          termNFAs.add(firstTermNFA);
          for (int i = 2; i <= count; ++i)
          {
            XSDNFA clonedTermNFA = new XSDNFA(firstTermNFA, isApproximate);
            propagateStates(clonedTermNFA);
            termNFAs.add(clonedTermNFA);
            if (getStates().size() > MAXIMUM_STATES)
            {
              break;
            }
          }

          //XSDNFA previousTermNFA = firstTermNFA;
          StateImpl previousState = initialState;
          int i;
          if (minOccurs == 0)
          {
            initialState.createTransition(null, firstTermNFA.getInitialState());
            initialState.createTransition(null, firstTermNFA.getFinalState());
            previousState = (StateImpl)firstTermNFA.getFinalState();
            i = 2;
          }
          else
          {
            for (i = 1; i <= minOccurs; ++i)
            {
              XSDNFA termNFA = termNFAs.get(i - 1);
              previousState.createTransition(null, termNFA.getInitialState());
              previousState = (StateImpl)termNFA.getFinalState();
              if (getStates().size() > MAXIMUM_STATES)
              {
                break;
              }
            }
          }

          if (maxOccurs == -1 || isApproximate)
          {
            previousState.createTransition(null, firstTermNFA.getInitialState());
          }
          else
          {
            for ( ; i <= maxOccurs; ++i)
            {
              XSDNFA termNFA = termNFAs.get(i - 1);
              previousState.createTransition(null, termNFA.getInitialState());
              ((StateImpl)termNFA.getInitialState()).createTransition(null, finalState);
              previousState = (StateImpl)termNFA.getFinalState();
              if (getStates().size() > MAXIMUM_STATES)
              {
                break;
              }
            }
          }
          previousState.createTransition(null, finalState);
        }
      }
    }

    protected XSDNFA createPermutations(List<XSDParticle> particles, List<XSDNFA> particleNFAs, Map<List<XSDParticle>, XSDNFA> particlesToNFAMap)
    {
      XSDNFA result = particlesToNFAMap.get(particles);
      if (result == null)
      {
        int size = particleNFAs.size();
        if (size == 1)
        {
          result = new XSDNFA(particleNFAs.get(0), isApproximate);
          particlesToNFAMap.put(particles, result);
        }
        else
        {
          result = new XSDNFA(isApproximate);
          List<XSDNFA> otherParticleNFAs = new ArrayList<XSDNFA>(size - 1);
          List<XSDParticle> otherParticles = new ArrayList<XSDParticle>(size - 1);
          for (int i = 0; i < size; ++i) 
          {
            XSDNFA particleNFA = particleNFAs.get(i);
            XSDParticle particle = particles.get(i);
            otherParticleNFAs.clear();
            otherParticles.clear();
            for (int j = 0; j < size; ++j) 
            {
              XSDParticle otherParticle = particles.get(j);
              if (particle != otherParticle)
              {
                XSDNFA otherParticleNFA = particleNFAs.get(j);
                otherParticleNFAs.add(otherParticleNFA);
                otherParticles.add(otherParticle);
              }
            }

            XSDNFA clonedParticleNFA = new XSDNFA(particleNFA, isApproximate);
            result.propagateStates(clonedParticleNFA);
            ((StateImpl)result.getInitialState()).createTransition(null, clonedParticleNFA.getInitialState());

            XSDNFA combinationXSDNFA = createPermutations
              (otherParticles, 
               otherParticleNFAs, 
               particlesToNFAMap);
            result.propagateStates(combinationXSDNFA);

            ((StateImpl)clonedParticleNFA.getFinalState()).createTransition(null, combinationXSDNFA.getInitialState());
            ((StateImpl)combinationXSDNFA.getFinalState()).createTransition(null, result.getFinalState());
          }

          if (result.getStates().size() > 50)
          {
            result.epsilonClosure();
            result.minimize();
            result.determinize();
            result.minimize();
            result.createFinalState();
          }
        }
        particlesToNFAMap.put(particles, result);
      }

      return result;
    }

    public boolean isApproximate()
    {
      return isApproximate;
    }

    public List<State> getStates()
    {
      return states;
    }

    public XSDParticle.DFA.State getInitialState()
    {
      return initialState;
    }

    public XSDParticle.DFA.State getFinalState()
    {
      return finalState;
    }

    public XSDParticle getParticle()
    {
      return xsdParticle;
    }

    public static String getComponentLabel(Collection<XSDComponent> xsdComponents)
    {
      StringBuffer result = new StringBuffer();
      result.append("{");
      for (Iterator<XSDComponent> i = xsdComponents.iterator(); i.hasNext(); )
      {
        XSDComponent xsdComponent = i.next();
        result.append(getComponentLabel(xsdComponent));
        if (i.hasNext())
        {
          result.append(" ");
        }
      }
      result.append("}");
      return result.toString();
    }

    public static String getComponentLabel(XSDComponent xsdComponent)
    {
      if (xsdComponent == null)
      {
        return "-";
      }
      else if (xsdComponent instanceof XSDElementDeclaration)
      {
        XSDElementDeclaration xsdElementDeclaration = (XSDElementDeclaration)xsdComponent;
        return xsdElementDeclaration.getName();
      }
      else if (xsdComponent instanceof XSDWildcard)
      {
        XSDWildcard xsdWildcard = (XSDWildcard)xsdComponent;
        return xsdWildcard.getStringNamespaceConstraint();
      }
      else if (xsdComponent instanceof XSDModelGroup)
      {
        XSDModelGroup xsdModelGroup = (XSDModelGroup)xsdComponent;
        switch (xsdModelGroup.getCompositor().getValue())
        {
          case XSDCompositor.SEQUENCE:
          {
            return "<sequence/>";
          }
          case XSDCompositor.CHOICE:
          {
            return "<choice/>";
          }
          case XSDCompositor.ALL:
          default:
          {
            return "<all/>";
          }
        }
      }
      else if (xsdComponent instanceof XSDParticle)
      {
        XSDParticle xsdParticle = (XSDParticle)xsdComponent;
        return getComponentLabel(xsdParticle.getTerm()) + "[" + xsdParticle.getMinOccurs() + "," + xsdParticle.getMaxOccurs() + "]";
      }
      else
      {
        return xsdComponent.toString();
      }
    }

    public void dump(PrintStream out)
    {
      List<State> stateList = new ArrayList<State>(getStates());
      stateList.remove(initialState);
      stateList.add(0, initialState);
      if (finalState != null)
      {
        stateList.remove(finalState);
        stateList.add(finalState);
      }

      out.println
        ("Component: " + getComponentLabel(xsdParticle) + 
           " [" + stateList.indexOf(initialState) + ", " + 
           stateList.indexOf(finalState) + "]"); 
      for (State state : stateList)
      {
        out.println
          ("    State: " + stateList.indexOf(state) + (state.isAccepting() ? " *" : ""));

        for (Transition transition : state.getTransitions())
        {
          out.println
            ("     --> : " + getComponentLabel(transition.getParticle()) +  " -> " + stateList.indexOf(transition.getState())); 
        }
      }
    }

    public void epsilonClosure()
    {
      finalState.setAccepting(true);
      boolean closed;
      do
      {
        closed = true;
        for (int i = 0, iSize = states.size(); i < iSize; ++i) 
        {
          StateImpl state = (StateImpl)states.get(i);
          List<Transition> stateTransitions = state.getTransitions();
          for (int j = 0, jSize = stateTransitions.size(); j < jSize; ++j)
          {
            Transition transition = stateTransitions.get(j);
            if (transition.getParticle() == null)
            {
              State otherState = transition.getState();
              if (state != otherState)
              {
                if (otherState.isAccepting())
                {
                  state.setAccepting(true);
                }
                List<Transition> otherStateTransitions = otherState.getTransitions();
                for (int k = 0, kSize = otherStateTransitions.size(); k < kSize; ++k)
                {
                  Transition t = otherStateTransitions.get(k);
                  if (t.getParticle() != null && stateTransitions.add(t))
                  {
                    closed = false;
                    ++jSize;
                  }
                }
              }
            }
          }
        }
      }
      while (!closed);

      for (int i = 0, iSize = states.size(); i < iSize; ++i) 
      {
        State  state = states.get(i);
        for (Iterator<Transition> j = state.getTransitions().iterator(); j.hasNext(); )
        {
          Transition transition = j.next();
          if (transition.getParticle() == null)
          {
            j.remove();
          }
        }
      }
    }

    public void createFinalState()
    {
      finalState = createState(null);
      for (int i = 0, iSize = states.size(); i < iSize; ++i) 
      {
        StateImpl state = (StateImpl)states.get(i);
        if (state.isAccepting())
        {
          state.setAccepting(false);
          state.createTransition(null, finalState);
        }
      }
    }

    protected TransitionImpl testTransition = new TransitionImpl(null, null);

    public boolean isEquivalent(XSDParticle.DFA.State s1, XSDParticle.DFA.State s2)
    {
      if (s1.isAccepting() != s2.isAccepting() || s1.getTransitions().size() != s2.getTransitions().size())
      {
        return false;
      }
      else
      {
        List<Transition> stateTransitions = s1.getTransitions();
        for (int i = 0, iSize = stateTransitions.size(); i < iSize; ++i)
        {
          Transition transition = stateTransitions.get(i);
          testTransition.setParticle(transition.getParticle());
          testTransition.setState(transition.getState());

          if (!s2.getTransitions().contains(testTransition))
          {
            return false;
          }
        }

        return true;
      }
    }

    public void minimize()
    {
      State [] theStates = states.toArray(new State [states.size()]);
      List<State> equivalentStates = new ArrayList<State>();
      boolean minimal;
      do
      {
        minimal = true;
        for (int i = 0; i < theStates.length; ++i)
        {
          State state = theStates[i];
          if (state != null)
          {
            for (int j = i + 1; j < theStates.length; ++j)
            {
              State otherState = theStates[j];
              if (otherState != null && isEquivalent(state, otherState))
              {
                equivalentStates.add(otherState);
                theStates[j] = null;
              }
            }
            if (!equivalentStates.isEmpty())
            {
              for (int j = 0; j < theStates.length; ++j)
              {
                State otherState = theStates[j];
                if (otherState != null)
                {
                  List<Transition> theTransitions = otherState.getTransitions();
                  if (equivalentStates.contains(otherState))
                  {
                    for (int k = 0, kSize = theTransitions.size(); k < kSize; ++k)
                    {
                      Transition transition = theTransitions.get(k);
                      State outgoingState = transition.getState();
                      if (equivalentStates.contains(outgoingState))
                      {
                        ((StateImpl)state).createTransition(transition.getParticle(), state);
                      }
                      else
                      {
                        ((StateImpl)state).createTransition(transition.getParticle(), outgoingState);
                      }
                    }
                  }
                  else 
                  {
                    for (int k = theTransitions.size(); --k >= 0; )
                    {
                      Transition transition = theTransitions.get(k);
                      State outgoingState = transition.getState();
                      if (equivalentStates.contains(outgoingState))
                      {
                        theTransitions.remove(k);
                        theTransitions.add(new TransitionImpl(transition.getParticle(), state));
                      }
                    }
                  }
                }
              }

              states.removeAll(equivalentStates);
              equivalentStates.clear();
              minimal = false;
            }
          }
        }
      }
      while (!minimal);
    }

    protected XSDElementDeclaration [] xsdElementDeclarations;

    public int checksum()
    {
      int result = 0;
      if (xsdElementDeclarations != null)
      {
        for (int i = 0, length = xsdElementDeclarations.length; i < length; ++i)
        {
          result += xsdElementDeclarations[i].getSubstitutionGroup().hashCode();
        }
      }
      else
      {
        List<XSDElementDeclaration> elements = new UniqueEList.FastCompare<XSDElementDeclaration>();
        for (int i = 0, iSize = states.size(); i < iSize; ++i)
        {
          State state = states.get(i);
          List<Transition> theTransitions = state.getTransitions();
          for (int j = 0, jSize = theTransitions.size(); j < jSize; ++j)
          {
            Transition transition = theTransitions.get(j);
            XSDTerm xsdTerm = transition.getParticle().getTerm();
            if (xsdTerm instanceof XSDElementDeclaration)
            {
              XSDElementDeclaration xsdElementDeclaration = (XSDElementDeclaration)xsdTerm;
              if (elements.add(xsdElementDeclaration))
              {
                result += xsdElementDeclaration.getSubstitutionGroup().hashCode();
              }
            }
          }
        }
        xsdElementDeclarations = new XSDElementDeclaration[elements.size()];
        elements.toArray(xsdElementDeclarations);
      }
      return result;
    }

    public void determinize()
    {
      Set<Set<State>> stateSubsets = new HashSet<Set<State>>();
      State originalInitialState = initialState;
      Set<State> initialStateSubset = Collections.<State>singleton(initialState);
      stateSubsets.add(initialStateSubset);

      states = new ArrayList<State>();
      currentState = initialState = createState(null);
      initialState.setAccepting(originalInitialState.isAccepting());
      finalState = null;
      xsdParticle = null;

      Map<Set<State>, State> stateSubsetToStateMap = new HashMap<Set<State>, State>();
      stateSubsetToStateMap.put(initialStateSubset, initialState);
      do
      {
        Iterator<Set<State>> s = stateSubsets.iterator();
        Set<State> stateSubset = s.next();
        s.remove();

        StateImpl newState = (StateImpl)stateSubsetToStateMap.get(stateSubset);

        Set<Transition> transitions = new HashSet<Transition>();
        for (State originalState : stateSubset)
        {
          transitions.addAll(originalState.getTransitions());
        }

        while (!transitions.isEmpty())
        {
          Set<State> newStateSubset = new HashSet<State>();
          Iterator<Transition> i = transitions.iterator(); 
          Transition originalTransition = i.next();
          XSDParticle particle = originalTransition.getParticle();
          i.remove();
          newStateSubset.add(originalTransition.getState());
          boolean isAccepting = false;
          if (originalTransition.getState().isAccepting())
          {
            isAccepting = true;
          }
          while (i.hasNext())
          {
            Transition otherOriginalTransition = i.next();
            if (otherOriginalTransition.getParticle() == particle)
            {
              i.remove();
              newStateSubset.add(otherOriginalTransition.getState());
              if (otherOriginalTransition.getState().isAccepting())
              {
                isAccepting = true;
              }
            }
          }
          StateImpl newNextState = (StateImpl)stateSubsetToStateMap.get(newStateSubset);
          if (newNextState == null)
          {
            newNextState = createState(null);
            if (isAccepting)
            {
              newNextState.setAccepting(true);
            }
            stateSubsetToStateMap.put(newStateSubset, newNextState);
            stateSubsets.add(newStateSubset);
          }
          else
          {
            if (newNextState.isAccepting() != isAccepting)
            {
              Thread.dumpStack();
            }
          }

          newState.createTransition(particle, newNextState);
        }
      }
      while (!stateSubsets.isEmpty());
    }

    public XSDDiagnostic checkOverlap(XSDTerm xsdComponent1, XSDTerm xsdComponent2)
    {
      if (xsdComponent1 instanceof XSDElementDeclaration)
      {
        XSDElementDeclaration xsdElementDeclaration1 = (XSDElementDeclaration)xsdComponent1;
        if (xsdComponent2 instanceof XSDElementDeclaration)
        {
          XSDElementDeclaration xsdElementDeclaration2 = (XSDElementDeclaration)xsdComponent2;
          if (xsdElementDeclaration1.hasSameNameAndTargetNamespace(xsdElementDeclaration2))
          {
            XSDDiagnostic xsdDiagnostic = XSDFactory.eINSTANCE.createXSDDiagnostic();
            xsdDiagnostic.setSeverity(XSDDiagnosticSeverity.ERROR_LITERAL);
            xsdDiagnostic.setMessage
              (XSDPlugin.INSTANCE.getString
                 ("_UI_XSDError_message", 
                  new Object [] 
                  {
                    populateDiagnostic
                      (xsdDiagnostic, "key-overlap.1", new Object [] { xsdElementDeclaration1.getURI() })
                  }));
            return xsdDiagnostic;
          }
        }
        else if (xsdComponent2 instanceof XSDWildcard)
        {
          XSDWildcard xsdWildcard2 = (XSDWildcard)xsdComponent2;
          if (xsdWildcard2.allows(xsdElementDeclaration1.getTargetNamespace()))
          {
            XSDDiagnostic xsdDiagnostic = XSDFactory.eINSTANCE.createXSDDiagnostic();
            xsdDiagnostic.setSeverity(XSDDiagnosticSeverity.ERROR_LITERAL);
            xsdDiagnostic.setMessage
              (XSDPlugin.INSTANCE.getString
                 ("_UI_XSDError_message", 
                  new Object [] 
                  { 
                    populateDiagnostic(xsdDiagnostic, "key-overlap.2", new Object [] { xsdElementDeclaration1.getURI() }) 
                  }));
            return xsdDiagnostic;
          }
        }
      }
      else if (xsdComponent1 instanceof XSDWildcard)
      {
        XSDWildcard xsdWildcard1 = (XSDWildcard)xsdComponent1;
        if (xsdComponent2 instanceof XSDElementDeclaration)
        {
          XSDElementDeclaration xsdElementDeclaration2 = (XSDElementDeclaration)xsdComponent2;
          if (xsdWildcard1.allows(xsdElementDeclaration2.getTargetNamespace()))
          {
            XSDDiagnostic xsdDiagnostic = XSDFactory.eINSTANCE.createXSDDiagnostic();
            xsdDiagnostic.setSeverity(XSDDiagnosticSeverity.ERROR_LITERAL);
            xsdDiagnostic.setMessage
              (XSDPlugin.INSTANCE.getString
                 ("_UI_XSDError_message", 
                  new Object [] 
                  { 
                    populateDiagnostic(xsdDiagnostic, "key-overlap.2", new Object [] { xsdElementDeclaration2.getURI() })
                  }));
            return xsdDiagnostic;
          }
        }
        else if (xsdComponent2 instanceof XSDWildcard)
        {
          XSDWildcard xsdWildcard2 = (XSDWildcard)xsdComponent2;
          XSDWildcard intersection = xsdWildcard1.attributeWildcardIntersection(xsdWildcard2);
          if (intersection != null && !intersection.getNamespaceConstraint().isEmpty())
          {
            XSDDiagnostic xsdDiagnostic = XSDFactory.eINSTANCE.createXSDDiagnostic();
            xsdDiagnostic.setSeverity(XSDDiagnosticSeverity.ERROR_LITERAL);
            xsdDiagnostic.setMessage
              (XSDPlugin.INSTANCE.getString
                 ("_UI_XSDError_message", 
                  new Object [] 
                  {
                    populateDiagnostic(xsdDiagnostic, "key-overlap.3", new Object [] { intersection.getNamespaceConstraint().get(0) })
                  }));
            return xsdDiagnostic;
          }
        }
      }

      return null;
    }

    public void propagateStates(XSDNFA xsdNFA)
    {
      states.addAll(xsdNFA.getStates());
      if (xsdNFA.diagnostics != null)
      {
        if (diagnostics == null)
        {
          diagnostics = new ArrayList<XSDDiagnostic>();
        }
        diagnostics.addAll(xsdNFA.diagnostics);
      }
    }

    public Collection<XSDDiagnostic> getDiagnostics()
    {
      if (diagnostics == null)
      {
        diagnostics = new ArrayList<XSDDiagnostic>();
        Map<String, XSDTypeDefinition> elementURIToTypeMap = new HashMap<String, XSDTypeDefinition>();
        if (getStates().size() > MAXIMUM_STATES)
        {
          XSDDiagnostic xsdDiagnostic = XSDFactory.eINSTANCE.createXSDDiagnostic();
          xsdDiagnostic.setSeverity(XSDDiagnosticSeverity.WARNING_LITERAL);
          xsdDiagnostic.setMessage
            (XSDPlugin.INSTANCE.getString
               ("_UI_XSDError_message", 
                new Object [] 
                { 
                  populateDiagnostic(xsdDiagnostic, "coss-particle.0.1", new Object [] { new Integer(getStates().size()) })
                }));
          diagnostics.add(xsdDiagnostic);
        }
        else
        {
          for (State state : getStates())
          {
            List<Transition> transitions = new ArrayList<Transition>(state.getTransitions());
            for (Iterator<Transition> j = transitions.iterator(); j.hasNext(); )
            {
              Transition transition = j.next();
              XSDTerm xsdTerm = transition.getParticle().getTerm();
              if (xsdTerm instanceof XSDElementDeclaration)
              {
                XSDElementDeclaration xsdElementDeclaration = (XSDElementDeclaration)xsdTerm;
                XSDTypeDefinition xsdTypeDefinition = xsdElementDeclaration.getTypeDefinition();
                Object otherXSDTypeDefinition = elementURIToTypeMap.put(xsdElementDeclaration.getURI(), xsdTypeDefinition);
                if (otherXSDTypeDefinition != null && otherXSDTypeDefinition != xsdTypeDefinition)
                {
                  XSDDiagnostic xsdDiagnostic = XSDFactory.eINSTANCE.createXSDDiagnostic();
                  xsdDiagnostic.setSeverity(XSDDiagnosticSeverity.ERROR_LITERAL);
                  xsdDiagnostic.setMessage
                    (XSDPlugin.INSTANCE.getString
                       ("_UI_XSDError_message", 
                        new Object [] 
                        {
                          populateDiagnostic
                            (xsdDiagnostic,
                             "cos-element-consistent", 
                             new Object [] 
                             { 
                               xsdElementDeclaration.getURI(), 
                               xsdTypeDefinition.getURI(), 
                               ((XSDTypeDefinition)otherXSDTypeDefinition).getURI()
                             })
                         }));
                  diagnostics.add(xsdDiagnostic);
                }
              }
              j.remove();
              for (Transition otherTransition : transitions)
              {
                XSDDiagnostic xsdDiagnostic = checkOverlap(xsdTerm, otherTransition.getParticle().getTerm());
                if (xsdDiagnostic != null)
                {
                  diagnostics.add(xsdDiagnostic);
                }
              }
            }
          }
        }
      }

      return diagnostics;
    }

    public XSDParticle.DFA.State getCurrentState()
    {
      return currentState;
    }

    public void reset()
    {
      currentState = initialState;
    }

    public XSDParticle.DFA cloneDFA()
    {
      return new XSDNFA(this, isApproximate);
    }
  }

  public static final boolean debug = false;

  protected XSDNFA xsdNFA;
  protected int xsdNFACheckSum;

  public XSDParticle.DFA getDFA()
  {
    if (xsdNFA == null || (!getSchema().getSchemaForSchemaNamespace().equals(getSchema().getTargetNamespace()) && xsdNFACheckSum != xsdNFA.checksum()))
    {
      XSDNFA localNFA = new XSDNFA(this, false);
      if (localNFA.getStates().size() > MAXIMUM_STATES)
      {
        localNFA = new XSDNFA(this, true);
      }
      if (localNFA.getStates().size() < MAXIMUM_STATES)
      {
        if (debug)
        {
          System.out.println("-- NFA initial --");
          localNFA.dump(System.out);
          System.out.println("-- NFA no-epsilon --");
        }
        localNFA.epsilonClosure();
        if (debug)
        {
          localNFA.dump(System.out);
          System.out.println("-- NFA minimal --");
        }
        localNFA.minimize();
        if (debug)
        {
          localNFA.dump(System.out);
          System.out.println("-- DFA --");
        }
        localNFA.determinize();
        if (debug)
        {
          localNFA.dump(System.out);
          System.out.println("-- DFA minimal--");
        }
        localNFA.minimize();
        if (debug)
        {
          localNFA.dump(System.out);
        }
      }
      else
      {
        // Clean up bad transitions.
        //
        for (DFA.State state : localNFA.getStates())
        {
          for (Iterator<DFA.Transition> j = state.getTransitions().iterator(); j.hasNext(); )
          {
            DFA.Transition transition = j.next();
            if (transition.getParticle() == null)
            {
              j.remove();
            }
          }
        }
      }
      xsdNFA = localNFA;
    }
    return xsdNFA;
  }

  public boolean isEmptiable()
  {
    return getDFA().getInitialState().isAccepting();
  }

  public boolean isSubset(XSDParticle otherParticle)
  {
    return isSubset(otherParticle, false);
  }

  public boolean isSubset(XSDParticle otherParticle, boolean diagnose)
  {
    XSDNFA subset = (XSDNFA)getDFA();
    XSDNFA superSet = (XSDNFA)otherParticle.getDFA();

    if (!subset.getDiagnostics().isEmpty() || !superSet.getDiagnostics().isEmpty())
    {
      return true;
    }

    final Set<List<DFA.State>> states = new HashSet<List<DFA.State>>();
    Map<DFA.State, Object> map = 
      new HashMap<DFA.State, Object>()
      {
        private static final long serialVersionUID = 1L;

        @Override
        public Object put(DFA.State key, Object value)
        {
          DFA.State s1 = key;
          DFA.State s2 = (DFA.State)value;
          if (s1.isAccepting() && !s2.isAccepting())
          {
            return null;
          }

          @SuppressWarnings("unchecked") Set<DFA.State> set = (Set<DFA.State>)super.get(key);
          if (set == null)
          {
            set = new HashSet<DFA.State>();
            super.put(key, set);
          }
          if (set.add(s2))
          {
            List<DFA.State> pair = new ArrayList<DFA.State>(2);
            pair.add(s1);
            pair.add(s2);
            states.add(pair);
          }
          return set;
        }
      };

    boolean result = false;
    if (map.put(subset.getInitialState(), superSet.getInitialState()) != null)
    {
      Map<XSDElementDeclaration, XSDElementDeclaration> elementMap = new HashMap<XSDElementDeclaration, XSDElementDeclaration>();

      result = true;
      LOOP: while (!states.isEmpty())
      {
        Iterator<List<DFA.State>> iterator = states.iterator();
        List<DFA.State> pair = iterator.next();
        iterator.remove();

        DFA.State substate = pair.get(0);
        DFA.State superState = pair.get(1);
        for (DFA.Transition subtransition : substate.getTransitions())
        {
          XSDTerm subXSDTerm = subtransition.getParticle().getTerm();
          boolean transitionResult = false;
          if (subXSDTerm instanceof XSDElementDeclaration)
          {
            XSDElementDeclaration subXSDElementDeclaration = (XSDElementDeclaration)subXSDTerm;
            for (DFA.Transition superTransition : superState.getTransitions())
            {
              XSDTerm superXSDTerm = superTransition.getParticle().getTerm();
              if (superXSDTerm instanceof XSDElementDeclaration)
              {
                XSDElementDeclaration superXSDElementDeclaration = (XSDElementDeclaration)superXSDTerm;
                if (superXSDElementDeclaration.hasSameNameAndTargetNamespace(subXSDElementDeclaration))
                {
                  if (map.put(subtransition.getState(), superTransition.getState()) != null)
                  {
                    elementMap.put(subXSDElementDeclaration, superXSDElementDeclaration);
                    transitionResult = true;
                    break;
                  }
                  else
                  {
                    result = false;
                    break LOOP;
                  }
                }
              }
              else
              {
                XSDWildcard superXSDWildcard = (XSDWildcard)superXSDTerm;
                if (superXSDWildcard.allows(subXSDElementDeclaration.getTargetNamespace()))
                {
                  if (map.put(subtransition.getState(), superTransition.getState()) != null)
                  {
                    transitionResult = true;
                    break;
                  }
                  else
                  {
                    result = false;
                    break LOOP;
                  }
                }
              }
            }
          }
          else
          {
            XSDWildcard subXSDWildcard = (XSDWildcard)subXSDTerm;
            for (DFA.Transition superTransition : superState.getTransitions())
            {
              XSDTerm superXSDTerm = superTransition.getParticle().getTerm();
              if (superXSDTerm instanceof XSDWildcard)
              {
                XSDWildcard superXSDWildcard = (XSDWildcard)superXSDTerm;
                if (subXSDWildcard.isWildcardSubset(superXSDWildcard))
                {
                  if (map.put(subtransition.getState(), superTransition.getState()) != null)
                  {
                    transitionResult = true;
                    break;
                  }
                  else
                  {
                    result = false;
                    break LOOP;
                  }
                }
              }
            }
          }

          if (!transitionResult)
          {
            result = false;
            break LOOP;
          }
        }
      }

      if (result)
      {
        for (Map.Entry<XSDElementDeclaration, XSDElementDeclaration> entry : elementMap.entrySet())
        {
          XSDElementDeclaration subXSDElementDeclaration = entry.getKey();
          XSDElementDeclaration superXSDElementDeclaration = entry.getValue();

          if (superXSDElementDeclaration.isNillable() && !subXSDElementDeclaration.isNillable())
          {
            result = false;
            if (diagnose)
            {
              getDiagnosticTarget(subXSDElementDeclaration).createDiagnostic
                (XSDDiagnosticSeverity.ERROR_LITERAL,
                 "rcase-NameAndTypeOK.2",
                 subXSDElementDeclaration.getURI());
            }
            else
            {
             break;
            }
          }

          if (superXSDElementDeclaration.getTypeDefinition() instanceof XSDSimpleTypeDefinition)
          {
            if (superXSDElementDeclaration.getConstraint() == XSDConstraint.FIXED_LITERAL &&
                  superXSDElementDeclaration.getLexicalValue() != null &&
                  (subXSDElementDeclaration.getConstraint() != XSDConstraint.FIXED_LITERAL || 
                    !((XSDSimpleTypeDefinition)superXSDElementDeclaration.getTypeDefinition()).equalLiterals
                       (superXSDElementDeclaration.getElement(), superXSDElementDeclaration.getLexicalValue(), subXSDElementDeclaration.getElement(), subXSDElementDeclaration.getLexicalValue())))
            {
              result = false;
              if (diagnose)
              {
                getDiagnosticTarget(subXSDElementDeclaration).createDiagnostic
                  (XSDDiagnosticSeverity.ERROR_LITERAL, 
                   "rcase-NameAndTypeOK.4", 
                   subXSDElementDeclaration.getLexicalValue(), 
                   subXSDElementDeclaration.getURI());
              }
              else
              {
                break;
              }
            }
          }
    
          if (!subXSDElementDeclaration.getDisallowedSubstitutions().containsAll
                 (superXSDElementDeclaration.getDisallowedSubstitutions()))
          {
            result = false;
            if (diagnose)
            {
              getDiagnosticTarget(subXSDElementDeclaration).createDiagnostic
                (XSDDiagnosticSeverity.ERROR_LITERAL, 
                 "rcase-NameAndTypeOK.6", 
                 subXSDElementDeclaration.getURI());
            }
            else
            {
              break;
            }
          }

          XSDTypeDefinition badType =
            subXSDElementDeclaration.getTypeDefinition().getBadTypeDerivation
              (superXSDElementDeclaration.getTypeDefinition(), false, true);
          if (badType != null)
          {
            result = false;
            if (diagnose)
            {
              getDiagnosticTarget(subXSDElementDeclaration).createDiagnostic
                (XSDDiagnosticSeverity.ERROR_LITERAL,
                 "rcase-NameAndTypeOK.7",
                 subXSDElementDeclaration.getURI(),
                 superXSDElementDeclaration.getTypeDefinition().getURI());
            }
            else
            {
              break;
            }
          }
        }
      }
    }

    // if (!result)
    // {
    //   printDFA(subset);
    //   printDFA(superSet);
    // }

    return result;
  }

  @Override
  public XSDConcreteComponent cloneConcreteComponent(boolean deep, boolean shareDOM)
  {
    XSDParticleImpl clonedParticle =
      (XSDParticleImpl)getXSDFactory().createXSDParticle();
    clonedParticle.isReconciling = true;

    if (isSetMinOccurs())
    {
      clonedParticle.setMinOccurs(getMinOccurs());
    }
    if (isSetMaxOccurs())
    {
      clonedParticle.setMaxOccurs(getMaxOccurs());
    }

    clonedParticle.setContent((XSDParticleContent)getContent().cloneConcreteComponent(deep, shareDOM));

    if (shareDOM && getElement() != null)
    {
      clonedParticle.setElement(getElement());
    }

    clonedParticle.isReconciling = shareDOM;
    return clonedParticle;
  }
} 
