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
 * $Id: EAnnotationImpl.java,v 1.4 2005/11/22 22:34:11 emerks Exp $
 */
package org.eclipse.emf.ecore.impl;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.EMap;
import org.eclipse.emf.ecore.EAnnotation;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EModelElement;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.EcorePackage;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.EObjectResolvingEList;
import org.eclipse.emf.ecore.util.EcoreEMap;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.util.InternalEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>EAnnotation</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.emf.ecore.impl.EAnnotationImpl#getSource <em>Source</em>}</li>
 *   <li>{@link org.eclipse.emf.ecore.impl.EAnnotationImpl#getDetails <em>Details</em>}</li>
 *   <li>{@link org.eclipse.emf.ecore.impl.EAnnotationImpl#getEModelElement <em>EModel Element</em>}</li>
 *   <li>{@link org.eclipse.emf.ecore.impl.EAnnotationImpl#getContents <em>Contents</em>}</li>
 *   <li>{@link org.eclipse.emf.ecore.impl.EAnnotationImpl#getReferences <em>References</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class EAnnotationImpl extends EModelElementImpl implements EAnnotation
{
  /**
   * The default value of the '{@link #getSource() <em>Source</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getSource()
   * @generated
   * @ordered
   */
  protected static final String SOURCE_EDEFAULT = null;

  /**
   * The cached value of the '{@link #getSource() <em>Source</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getSource()
   * @generated
   * @ordered
   */
  protected String source = SOURCE_EDEFAULT;

  /**
   * The cached value of the '{@link #getDetails() <em>Details</em>}' map.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getDetails()
   * @generated
   * @ordered
   */
  protected EMap details = null;

  /**
   * The cached value of the '{@link #getContents() <em>Contents</em>}' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getContents()
   * @generated
   * @ordered
   */
  protected EList contents = null;

  /**
   * The cached value of the '{@link #getReferences() <em>References</em>}' reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getReferences()
   * @generated
   * @ordered
   */
  protected EList references = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected EAnnotationImpl()
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
    return EcorePackage.eINSTANCE.getEAnnotation();
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public String getSource()
  {
    return source;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setSource(String newSource)
  {
    String oldSource = source;
    source = newSource;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, EcorePackage.EANNOTATION__SOURCE, oldSource, source));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EMap getDetails()
  {
    if (details == null)
    {
      details = new EcoreEMap(EcorePackage.eINSTANCE.getEStringToStringMapEntry(), EStringToStringMapEntryImpl.class, this, EcorePackage.EANNOTATION__DETAILS);
    }
    return details;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EModelElement getEModelElement()
  {
    if (eContainerFeatureID != EcorePackage.EANNOTATION__EMODEL_ELEMENT) return null;
    return (EModelElement)eContainer();
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setEModelElement(EModelElement newEModelElement)
  {
    if (newEModelElement != eInternalContainer() || (eContainerFeatureID != EcorePackage.EANNOTATION__EMODEL_ELEMENT && newEModelElement != null))
    {
      if (EcoreUtil.isAncestor(this, newEModelElement))
        throw new IllegalArgumentException("Recursive containment not allowed for " + toString());
      NotificationChain msgs = null;
      if (eInternalContainer() != null)
        msgs = eBasicRemoveFromContainer(msgs);
      if (newEModelElement != null)
        msgs = ((InternalEObject)newEModelElement).eInverseAdd(this, EcorePackage.EMODEL_ELEMENT__EANNOTATIONS, EModelElement.class, msgs);
      msgs = eBasicSetContainer((InternalEObject)newEModelElement, EcorePackage.EANNOTATION__EMODEL_ELEMENT, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, EcorePackage.EANNOTATION__EMODEL_ELEMENT, newEModelElement, newEModelElement));
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
      contents = new EObjectContainmentEList(EObject.class, this, EcorePackage.EANNOTATION__CONTENTS);
    }
    return contents;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EList getReferences()
  {
    if (references == null)
    {
      references = new EObjectResolvingEList(EObject.class, this, EcorePackage.EANNOTATION__REFERENCES);
    }
    return references;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, Class baseClass, NotificationChain msgs)
  {
    if (featureID >= 0)
    {
      switch (eDerivedStructuralFeatureID(featureID, baseClass))
      {
        case EcorePackage.EANNOTATION__EANNOTATIONS:
          return ((InternalEList)getEAnnotations()).basicAdd(otherEnd, msgs);
        case EcorePackage.EANNOTATION__EMODEL_ELEMENT:
          if (eInternalContainer() != null)
            msgs = eBasicRemoveFromContainer(msgs);
          return eBasicSetContainer(otherEnd, EcorePackage.EANNOTATION__EMODEL_ELEMENT, msgs);
        default:
          return eDynamicInverseAdd(otherEnd, featureID, baseClass, msgs);
      }
    }
    if (eInternalContainer() != null)
      msgs = eBasicRemoveFromContainer(msgs);
    return eBasicSetContainer(otherEnd, featureID, msgs);
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
        case EcorePackage.EANNOTATION__EANNOTATIONS:
          return ((InternalEList)getEAnnotations()).basicRemove(otherEnd, msgs);
        case EcorePackage.EANNOTATION__DETAILS:
          return ((InternalEList)getDetails()).basicRemove(otherEnd, msgs);
        case EcorePackage.EANNOTATION__EMODEL_ELEMENT:
          return eBasicSetContainer(null, EcorePackage.EANNOTATION__EMODEL_ELEMENT, msgs);
        case EcorePackage.EANNOTATION__CONTENTS:
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
  public NotificationChain eBasicRemoveFromContainer(NotificationChain msgs)
  {
    if (eContainerFeatureID >= 0)
    {
      switch (eContainerFeatureID)
      {
        case EcorePackage.EANNOTATION__EMODEL_ELEMENT:
          return eInternalContainer().eInverseRemove(this, EcorePackage.EMODEL_ELEMENT__EANNOTATIONS, EModelElement.class, msgs);
        default:
          return eDynamicBasicRemoveFromContainer(msgs);
      }
    }
    return eInternalContainer().eInverseRemove(this, EOPPOSITE_FEATURE_BASE - eContainerFeatureID, null, msgs);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public Object eGet(int featureID, boolean resolve, boolean coreType)
  {
    switch (featureID)
    {
      case EcorePackage.EANNOTATION__EANNOTATIONS:
        return getEAnnotations();
      case EcorePackage.EANNOTATION__SOURCE:
        return getSource();
      case EcorePackage.EANNOTATION__DETAILS:
        if (coreType) return getDetails();
        else return getDetails().map();
      case EcorePackage.EANNOTATION__EMODEL_ELEMENT:
        return getEModelElement();
      case EcorePackage.EANNOTATION__CONTENTS:
        return getContents();
      case EcorePackage.EANNOTATION__REFERENCES:
        return getReferences();
    }
    return eDynamicGet(featureID, resolve, coreType);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void eSet(int featureID, Object newValue)
  {
    switch (featureID)
    {
      case EcorePackage.EANNOTATION__EANNOTATIONS:
        getEAnnotations().clear();
        getEAnnotations().addAll((Collection)newValue);
        return;
      case EcorePackage.EANNOTATION__SOURCE:
        setSource((String)newValue);
        return;
      case EcorePackage.EANNOTATION__DETAILS:
        ((EStructuralFeature.Setting)getDetails()).set(newValue);
        return;
      case EcorePackage.EANNOTATION__EMODEL_ELEMENT:
        setEModelElement((EModelElement)newValue);
        return;
      case EcorePackage.EANNOTATION__CONTENTS:
        getContents().clear();
        getContents().addAll((Collection)newValue);
        return;
      case EcorePackage.EANNOTATION__REFERENCES:
        getReferences().clear();
        getReferences().addAll((Collection)newValue);
        return;
    }
    eDynamicSet(featureID, newValue);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void eUnset(int featureID)
  {
    switch (featureID)
    {
      case EcorePackage.EANNOTATION__EANNOTATIONS:
        getEAnnotations().clear();
        return;
      case EcorePackage.EANNOTATION__SOURCE:
        setSource(SOURCE_EDEFAULT);
        return;
      case EcorePackage.EANNOTATION__DETAILS:
        getDetails().clear();
        return;
      case EcorePackage.EANNOTATION__EMODEL_ELEMENT:
        setEModelElement((EModelElement)null);
        return;
      case EcorePackage.EANNOTATION__CONTENTS:
        getContents().clear();
        return;
      case EcorePackage.EANNOTATION__REFERENCES:
        getReferences().clear();
        return;
    }
    eDynamicUnset(featureID);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public boolean eIsSet(int featureID)
  {
    switch (featureID)
    {
      case EcorePackage.EANNOTATION__EANNOTATIONS:
        return eAnnotations != null && !eAnnotations.isEmpty();
      case EcorePackage.EANNOTATION__SOURCE:
        return SOURCE_EDEFAULT == null ? source != null : !SOURCE_EDEFAULT.equals(source);
      case EcorePackage.EANNOTATION__DETAILS:
        return details != null && !details.isEmpty();
      case EcorePackage.EANNOTATION__EMODEL_ELEMENT:
        return getEModelElement() != null;
      case EcorePackage.EANNOTATION__CONTENTS:
        return contents != null && !contents.isEmpty();
      case EcorePackage.EANNOTATION__REFERENCES:
        return references != null && !references.isEmpty();
    }
    return eDynamicIsSet(featureID);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public String toString()
  {
    if (eIsProxy()) return super.toString();

    StringBuffer result = new StringBuffer(super.toString());
    result.append(" (source: ");
    result.append(source);
    result.append(')');
    return result.toString();
  }

} //EAnnotationImpl

