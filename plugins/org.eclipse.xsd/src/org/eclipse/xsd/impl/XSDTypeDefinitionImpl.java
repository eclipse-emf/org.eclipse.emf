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
 * $Id: XSDTypeDefinitionImpl.java,v 1.1 2004/03/06 18:00:11 marcelop Exp $
 */
package org.eclipse.xsd.impl;


import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import org.w3c.dom.Element;
import org.w3c.dom.Node;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.util.EObjectEList;
import org.eclipse.emf.ecore.util.InternalEList;

import org.eclipse.xsd.XSDAnnotation;
import org.eclipse.xsd.XSDComplexTypeDefinition;
import org.eclipse.xsd.XSDPackage;
import org.eclipse.xsd.XSDParticle;
import org.eclipse.xsd.XSDSimpleTypeDefinition;
import org.eclipse.xsd.XSDTypeDefinition;


/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Type Definition</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.xsd.impl.XSDTypeDefinitionImpl#getAnnotation <em>Annotation</em>}</li>
 *   <li>{@link org.eclipse.xsd.impl.XSDTypeDefinitionImpl#getDerivationAnnotation <em>Derivation Annotation</em>}</li>
 *   <li>{@link org.eclipse.xsd.impl.XSDTypeDefinitionImpl#getAnnotations <em>Annotations</em>}</li>
 *   <li>{@link org.eclipse.xsd.impl.XSDTypeDefinitionImpl#getRootType <em>Root Type</em>}</li>
 *   <li>{@link org.eclipse.xsd.impl.XSDTypeDefinitionImpl#getBaseType <em>Base Type</em>}</li>
 *   <li>{@link org.eclipse.xsd.impl.XSDTypeDefinitionImpl#getSimpleType <em>Simple Type</em>}</li>
 *   <li>{@link org.eclipse.xsd.impl.XSDTypeDefinitionImpl#getComplexType <em>Complex Type</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public abstract class XSDTypeDefinitionImpl 
  extends XSDRedefinableComponentImpl 
  implements XSDTypeDefinition
{
  /**
   * The cached value of the '{@link #getAnnotation() <em>Annotation</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getAnnotation()
   * @generated
   * @ordered
   */
  protected XSDAnnotation annotation = null;

  /**
   * The cached value of the '{@link #getDerivationAnnotation() <em>Derivation Annotation</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getDerivationAnnotation()
   * @generated
   * @ordered
   */
  protected XSDAnnotation derivationAnnotation = null;

  /**
   * The cached value of the '{@link #getAnnotations() <em>Annotations</em>}' reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getAnnotations()
   * @generated
   * @ordered
   */
  protected EList annotations = null;

  public static XSDTypeDefinition createTypeDefinition(Node node)
  {
    XSDSimpleTypeDefinition xsdSimpleTypeDefinition = XSDSimpleTypeDefinitionImpl.createSimpleTypeDefinition(node);
    if (xsdSimpleTypeDefinition != null)
    {
      return xsdSimpleTypeDefinition;
    }
    XSDComplexTypeDefinition xsdComplexTypeDefinition = XSDComplexTypeDefinitionImpl.createComplexTypeDefinition(node);
    if (xsdComplexTypeDefinition != null)
    {
      return xsdComplexTypeDefinition;
    }
    return null;
  }

  protected int analysisState;

  protected XSDTypeDefinitionImpl() 
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
    return XSDPackage.eINSTANCE.getXSDTypeDefinition();
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
        msgs = ((InternalEObject)annotation).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - XSDPackage.XSD_TYPE_DEFINITION__ANNOTATION, null, msgs);
      if (newAnnotation != null)
        msgs = ((InternalEObject)newAnnotation).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - XSDPackage.XSD_TYPE_DEFINITION__ANNOTATION, null, msgs);
      msgs = basicSetAnnotation(newAnnotation, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, XSDPackage.XSD_TYPE_DEFINITION__ANNOTATION, newAnnotation, newAnnotation));
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
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, XSDPackage.XSD_TYPE_DEFINITION__ANNOTATION, oldAnnotation, newAnnotation);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public XSDAnnotation getDerivationAnnotation()
  {
    return derivationAnnotation;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setDerivationAnnotation(XSDAnnotation newDerivationAnnotation)
  {
    if (newDerivationAnnotation != derivationAnnotation)
    {
      NotificationChain msgs = null;
      if (derivationAnnotation != null)
        msgs = ((InternalEObject)derivationAnnotation).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - XSDPackage.XSD_TYPE_DEFINITION__DERIVATION_ANNOTATION, null, msgs);
      if (newDerivationAnnotation != null)
        msgs = ((InternalEObject)newDerivationAnnotation).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - XSDPackage.XSD_TYPE_DEFINITION__DERIVATION_ANNOTATION, null, msgs);
      msgs = basicSetDerivationAnnotation(newDerivationAnnotation, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, XSDPackage.XSD_TYPE_DEFINITION__DERIVATION_ANNOTATION, newDerivationAnnotation, newDerivationAnnotation));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetDerivationAnnotation(XSDAnnotation newDerivationAnnotation, NotificationChain msgs)
  {
    XSDAnnotation oldDerivationAnnotation = derivationAnnotation;
    derivationAnnotation = newDerivationAnnotation;
    if (eNotificationRequired())
    {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, XSDPackage.XSD_TYPE_DEFINITION__DERIVATION_ANNOTATION, oldDerivationAnnotation, newDerivationAnnotation);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }
    return msgs;
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
      annotations = new EObjectEList(XSDAnnotation.class, this, XSDPackage.XSD_TYPE_DEFINITION__ANNOTATIONS);
    }
    return annotations;
  }

  protected void patch()
  {
    analysisState = UNANALYZED;
    super.patch();
  }

  protected boolean analyze()
  {
    switch (analysisState)
    {
      case UNANALYZED:
      {
        analysisState = ANALYZING;

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
    super.analyze();
  }

  public static XSDTypeDefinition getLowestCommonAncestor(Collection xsdTypeDefinitions)
  {
    XSDTypeDefinition result = null;

    if (!xsdTypeDefinitions.isEmpty())
    {
      List listOfLists = new ArrayList();
      for (Iterator typeDefinitions = xsdTypeDefinitions.iterator(); typeDefinitions.hasNext(); )
      {
        List list = new ArrayList();
        listOfLists.add(list);

        for (XSDTypeDefinition xsdTypeDefinition = (XSDTypeDefinition)typeDefinitions.next();  
             xsdTypeDefinition != null; 
             xsdTypeDefinition = xsdTypeDefinition.getBaseType())
        {
          if (list.contains(xsdTypeDefinition))
          {
            break;
          }
          list.add(0, xsdTypeDefinition);
        }
/*
System.out.println("========");
for (Iterator i = list.iterator(); i.hasNext(); )
{
  XSDTypeDefinition t = (XSDTypeDefinition)i.next();
  if (t.getName() != null)
  {
    System.out.println("  : " + t.getName());
  }
  else 
  {
    for (org.eclipse.xsd.XSDConcreteComponent xsdConcreteComponent = t.getContainer(); 
         xsdConcreteComponent != null; 
         xsdConcreteComponent = xsdConcreteComponent.getContainer())
    {
      if (xsdConcreteComponent instanceof XSDNamedComponent)
      {
        System.out.println("  * " + ((XSDNamedComponent)xsdConcreteComponent).getName());
        break;
      }
    }
  }
  if (t.getSchema() == null)
  {
    System.out.println("!!!!");
  }
}
*/
      }
  
      List firstList = (List)listOfLists.get(0);
      LOOP: for (int index = 0, size = firstList.size(); index < size; ++index)
      {
        Object candidate = firstList.get(index);
        for (Iterator lists = listOfLists.listIterator(1); lists.hasNext(); )
        {
          List list = (List)lists.next();
          if (list.size() <= index || list.get(index) != candidate)
          {
            break LOOP;
          }
        }
        result = (XSDTypeDefinition)candidate;
      }
    }

    return result;
  }

  public XSDTypeDefinition getBaseType()
  {
    return null;
  }

  public XSDTypeDefinition getRootType()
  {
    return null;
  }

  public XSDSimpleTypeDefinition getSimpleType()
  {
    return null;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated modifiable
   */
  public XSDParticle getComplexType() 
  {
    return null;
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
        case XSDPackage.XSD_TYPE_DEFINITION__DIAGNOSTICS:
          return ((InternalEList)getDiagnostics()).basicRemove(otherEnd, msgs);
        case XSDPackage.XSD_TYPE_DEFINITION__ANNOTATION:
          return basicSetAnnotation(null, msgs);
        case XSDPackage.XSD_TYPE_DEFINITION__DERIVATION_ANNOTATION:
          return basicSetDerivationAnnotation(null, msgs);
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
      case XSDPackage.XSD_TYPE_DEFINITION__ELEMENT:
        return getElement();
      case XSDPackage.XSD_TYPE_DEFINITION__CONTAINER:
        return getContainer();
      case XSDPackage.XSD_TYPE_DEFINITION__ROOT_CONTAINER:
        return getRootContainer();
      case XSDPackage.XSD_TYPE_DEFINITION__SCHEMA:
        return getSchema();
      case XSDPackage.XSD_TYPE_DEFINITION__DIAGNOSTICS:
        return getDiagnostics();
      case XSDPackage.XSD_TYPE_DEFINITION__NAME:
        return getName();
      case XSDPackage.XSD_TYPE_DEFINITION__TARGET_NAMESPACE:
        return getTargetNamespace();
      case XSDPackage.XSD_TYPE_DEFINITION__ALIAS_NAME:
        return getAliasName();
      case XSDPackage.XSD_TYPE_DEFINITION__URI:
        return getURI();
      case XSDPackage.XSD_TYPE_DEFINITION__ALIAS_URI:
        return getAliasURI();
      case XSDPackage.XSD_TYPE_DEFINITION__QNAME:
        return getQName();
      case XSDPackage.XSD_TYPE_DEFINITION__CIRCULAR:
        return isCircular() ? Boolean.TRUE : Boolean.FALSE;
      case XSDPackage.XSD_TYPE_DEFINITION__ANNOTATION:
        return getAnnotation();
      case XSDPackage.XSD_TYPE_DEFINITION__DERIVATION_ANNOTATION:
        return getDerivationAnnotation();
      case XSDPackage.XSD_TYPE_DEFINITION__ANNOTATIONS:
        return getAnnotations();
      case XSDPackage.XSD_TYPE_DEFINITION__ROOT_TYPE:
        return getRootType();
      case XSDPackage.XSD_TYPE_DEFINITION__BASE_TYPE:
        return getBaseType();
      case XSDPackage.XSD_TYPE_DEFINITION__SIMPLE_TYPE:
        return getSimpleType();
      case XSDPackage.XSD_TYPE_DEFINITION__COMPLEX_TYPE:
        return getComplexType();
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
      case XSDPackage.XSD_TYPE_DEFINITION__ELEMENT:
        setElement((Element)newValue);
        return;
      case XSDPackage.XSD_TYPE_DEFINITION__DIAGNOSTICS:
        getDiagnostics().clear();
        getDiagnostics().addAll((Collection)newValue);
        return;
      case XSDPackage.XSD_TYPE_DEFINITION__NAME:
        setName((String)newValue);
        return;
      case XSDPackage.XSD_TYPE_DEFINITION__TARGET_NAMESPACE:
        setTargetNamespace((String)newValue);
        return;
      case XSDPackage.XSD_TYPE_DEFINITION__ANNOTATION:
        setAnnotation((XSDAnnotation)newValue);
        return;
      case XSDPackage.XSD_TYPE_DEFINITION__DERIVATION_ANNOTATION:
        setDerivationAnnotation((XSDAnnotation)newValue);
        return;
      case XSDPackage.XSD_TYPE_DEFINITION__ANNOTATIONS:
        getAnnotations().clear();
        getAnnotations().addAll((Collection)newValue);
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
      case XSDPackage.XSD_TYPE_DEFINITION__ELEMENT:
        setElement(ELEMENT_EDEFAULT);
        return;
      case XSDPackage.XSD_TYPE_DEFINITION__DIAGNOSTICS:
        getDiagnostics().clear();
        return;
      case XSDPackage.XSD_TYPE_DEFINITION__NAME:
        setName(NAME_EDEFAULT);
        return;
      case XSDPackage.XSD_TYPE_DEFINITION__TARGET_NAMESPACE:
        setTargetNamespace(TARGET_NAMESPACE_EDEFAULT);
        return;
      case XSDPackage.XSD_TYPE_DEFINITION__ANNOTATION:
        setAnnotation((XSDAnnotation)null);
        return;
      case XSDPackage.XSD_TYPE_DEFINITION__DERIVATION_ANNOTATION:
        setDerivationAnnotation((XSDAnnotation)null);
        return;
      case XSDPackage.XSD_TYPE_DEFINITION__ANNOTATIONS:
        getAnnotations().clear();
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
      case XSDPackage.XSD_TYPE_DEFINITION__ELEMENT:
        return ELEMENT_EDEFAULT == null ? element != null : !ELEMENT_EDEFAULT.equals(element);
      case XSDPackage.XSD_TYPE_DEFINITION__CONTAINER:
        return getContainer() != null;
      case XSDPackage.XSD_TYPE_DEFINITION__ROOT_CONTAINER:
        return getRootContainer() != null;
      case XSDPackage.XSD_TYPE_DEFINITION__SCHEMA:
        return getSchema() != null;
      case XSDPackage.XSD_TYPE_DEFINITION__DIAGNOSTICS:
        return diagnostics != null && !diagnostics.isEmpty();
      case XSDPackage.XSD_TYPE_DEFINITION__NAME:
        return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
      case XSDPackage.XSD_TYPE_DEFINITION__TARGET_NAMESPACE:
        return TARGET_NAMESPACE_EDEFAULT == null ? targetNamespace != null : !TARGET_NAMESPACE_EDEFAULT.equals(targetNamespace);
      case XSDPackage.XSD_TYPE_DEFINITION__ALIAS_NAME:
        return getAliasName() != null;
      case XSDPackage.XSD_TYPE_DEFINITION__URI:
        return getURI() != null;
      case XSDPackage.XSD_TYPE_DEFINITION__ALIAS_URI:
        return getAliasURI() != null;
      case XSDPackage.XSD_TYPE_DEFINITION__QNAME:
        return getQName() != null;
      case XSDPackage.XSD_TYPE_DEFINITION__CIRCULAR:
        return isCircular() != false;
      case XSDPackage.XSD_TYPE_DEFINITION__ANNOTATION:
        return annotation != null;
      case XSDPackage.XSD_TYPE_DEFINITION__DERIVATION_ANNOTATION:
        return derivationAnnotation != null;
      case XSDPackage.XSD_TYPE_DEFINITION__ANNOTATIONS:
        return annotations != null && !annotations.isEmpty();
      case XSDPackage.XSD_TYPE_DEFINITION__ROOT_TYPE:
        return getRootType() != null;
      case XSDPackage.XSD_TYPE_DEFINITION__BASE_TYPE:
        return getBaseType() != null;
      case XSDPackage.XSD_TYPE_DEFINITION__SIMPLE_TYPE:
        return getSimpleType() != null;
      case XSDPackage.XSD_TYPE_DEFINITION__COMPLEX_TYPE:
        return getComplexType() != null;
    }
    return eDynamicIsSet(eFeature);
  }

  public boolean isCircular()
  {
    return analysisState == CIRCULAR;
  }

  public XSDTypeDefinition getBadTypeDerivation(XSDTypeDefinition xsdTypeDefinition, boolean extension, boolean restriction)
  {
    throw new UnsupportedOperationException();
  }
}
