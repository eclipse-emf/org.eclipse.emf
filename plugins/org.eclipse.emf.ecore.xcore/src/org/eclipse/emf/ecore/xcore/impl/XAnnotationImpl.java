/**
 * Copyright (c) 2011-2012 Eclipse contributors and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package org.eclipse.emf.ecore.xcore.impl;


import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EMap;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

import org.eclipse.emf.ecore.util.EcoreEMap;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.util.InternalEList;

import org.eclipse.emf.ecore.xcore.XAnnotation;
import org.eclipse.emf.ecore.xcore.XAnnotationDirective;
import org.eclipse.emf.ecore.xcore.XModelElement;
import org.eclipse.emf.ecore.xcore.XcorePackage;


/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>XAnnotation</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.emf.ecore.xcore.impl.XAnnotationImpl#getSource <em>Source</em>}</li>
 *   <li>{@link org.eclipse.emf.ecore.xcore.impl.XAnnotationImpl#getDetails <em>Details</em>}</li>
 *   <li>{@link org.eclipse.emf.ecore.xcore.impl.XAnnotationImpl#getModelElement <em>Model Element</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class XAnnotationImpl extends XModelElementImpl implements XAnnotation
{
  /**
   * The cached value of the '{@link #getSource() <em>Source</em>}' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getSource()
   * @generated
   * @ordered
   */
  protected XAnnotationDirective source;

  /**
   * The cached value of the '{@link #getDetails() <em>Details</em>}' map.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getDetails()
   * @generated
   * @ordered
   */
  protected EMap<String, String> details;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected XAnnotationImpl()
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
    return XcorePackage.Literals.XANNOTATION;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public XAnnotationDirective getSource()
  {
    return source;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setSource(XAnnotationDirective newSource)
  {
    XAnnotationDirective oldSource = source;
    source = newSource;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, XcorePackage.XANNOTATION__SOURCE, oldSource, source));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EMap<String, String> getDetails()
  {
    if (details == null)
    {
      details = new EcoreEMap<String, String>(
        XcorePackage.Literals.XSTRING_TO_STRING_MAP_ENTRY,
        XStringToStringMapEntryImpl.class,
        this,
        XcorePackage.XANNOTATION__DETAILS);
    }
    return details;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public XModelElement getModelElement()
  {
    if (eContainerFeatureID() != XcorePackage.XANNOTATION__MODEL_ELEMENT)
      return null;
    return (XModelElement)eContainer();
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetModelElement(XModelElement newModelElement, NotificationChain msgs)
  {
    msgs = eBasicSetContainer((InternalEObject)newModelElement, XcorePackage.XANNOTATION__MODEL_ELEMENT, msgs);
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setModelElement(XModelElement newModelElement)
  {
    if (newModelElement != eInternalContainer()
      || (eContainerFeatureID() != XcorePackage.XANNOTATION__MODEL_ELEMENT && newModelElement != null))
    {
      if (EcoreUtil.isAncestor(this, newModelElement))
        throw new IllegalArgumentException("Recursive containment not allowed for " + toString());
      NotificationChain msgs = null;
      if (eInternalContainer() != null)
        msgs = eBasicRemoveFromContainer(msgs);
      if (newModelElement != null)
        msgs = ((InternalEObject)newModelElement).eInverseAdd(this, XcorePackage.XMODEL_ELEMENT__ANNOTATIONS, XModelElement.class, msgs);
      msgs = basicSetModelElement(newModelElement, msgs);
      if (msgs != null)
        msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, XcorePackage.XANNOTATION__MODEL_ELEMENT, newModelElement, newModelElement));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs)
  {
    switch (featureID)
    {
      case XcorePackage.XANNOTATION__MODEL_ELEMENT:
      if (eInternalContainer() != null)
        msgs = eBasicRemoveFromContainer(msgs);
      return basicSetModelElement((XModelElement)otherEnd, msgs);
    }
    return super.eInverseAdd(otherEnd, featureID, msgs);
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
      case XcorePackage.XANNOTATION__DETAILS:
      return ((InternalEList<?>)getDetails()).basicRemove(otherEnd, msgs);
      case XcorePackage.XANNOTATION__MODEL_ELEMENT:
      return basicSetModelElement(null, msgs);
    }
    return super.eInverseRemove(otherEnd, featureID, msgs);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public NotificationChain eBasicRemoveFromContainerFeature(NotificationChain msgs)
  {
    switch (eContainerFeatureID())
    {
      case XcorePackage.XANNOTATION__MODEL_ELEMENT:
      return eInternalContainer().eInverseRemove(this, XcorePackage.XMODEL_ELEMENT__ANNOTATIONS, XModelElement.class, msgs);
    }
    return super.eBasicRemoveFromContainerFeature(msgs);
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
      case XcorePackage.XANNOTATION__SOURCE:
      return getSource();
      case XcorePackage.XANNOTATION__DETAILS:
      if (coreType)
        return getDetails();
      else
        return getDetails().map();
      case XcorePackage.XANNOTATION__MODEL_ELEMENT:
      return getModelElement();
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
      case XcorePackage.XANNOTATION__SOURCE:
      setSource((XAnnotationDirective)newValue);
      return;
      case XcorePackage.XANNOTATION__DETAILS:
      ((EStructuralFeature.Setting)getDetails()).set(newValue);
      return;
      case XcorePackage.XANNOTATION__MODEL_ELEMENT:
      setModelElement((XModelElement)newValue);
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
      case XcorePackage.XANNOTATION__SOURCE:
      setSource((XAnnotationDirective)null);
      return;
      case XcorePackage.XANNOTATION__DETAILS:
      getDetails().clear();
      return;
      case XcorePackage.XANNOTATION__MODEL_ELEMENT:
      setModelElement((XModelElement)null);
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
      case XcorePackage.XANNOTATION__SOURCE:
      return source != null;
      case XcorePackage.XANNOTATION__DETAILS:
      return details != null && !details.isEmpty();
      case XcorePackage.XANNOTATION__MODEL_ELEMENT:
      return getModelElement() != null;
    }
    return super.eIsSet(featureID);
  }

} //XAnnotationImpl
