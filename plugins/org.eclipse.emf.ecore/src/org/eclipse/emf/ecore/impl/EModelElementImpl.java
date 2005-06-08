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
 * $Id: EModelElementImpl.java,v 1.2.2.1 2005/06/08 18:27:43 nickb Exp $
 */
package org.eclipse.emf.ecore.impl;


import java.util.Collection;
import java.util.Iterator;

import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.WrappedException;
import org.eclipse.emf.ecore.EAnnotation;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EModelElement;
import org.eclipse.emf.ecore.ENamedElement;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.EcorePackage;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.util.EObjectContainmentWithInverseEList;
import org.eclipse.emf.ecore.util.InternalEList;


/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>EModel Element</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.emf.ecore.impl.EModelElementImpl#getEAnnotations <em>EAnnotations</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public abstract class EModelElementImpl extends EObjectImpl implements EModelElement
{
  /**
   * The cached value of the '{@link #getEAnnotations() <em>EAnnotations</em>}' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getEAnnotations()
   * @generated
   * @ordered
   */
  protected EList eAnnotations = null;

  protected EModelElementImpl()
  {
    super();
  }

  /**
   * The bit of {@link #eFlags} that is used to represent being {@link #freeze() frozen}.
   */
  protected static final int EFROZEN = ELAST_EOBJECT_FLAG << 1;

  /**
   * The last bit used by this class; derived classes may use bit values higher than this.
   */
  protected static final int ELAST_EMODEL_ELEMENT_FLAG = EFROZEN;

  protected void freeze()
  {
    eFlags |= EFROZEN;
    for (Iterator i = eContents().iterator(); i.hasNext(); )
    {
      Object child = i.next();
      if (child instanceof EModelElementImpl)
      {
        ((EModelElementImpl)child).freeze();
      }
    }
  }

  protected boolean isFrozen()
  {
    return (eFlags & EFROZEN) != 0;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected EClass eStaticClass()
  {
    return EcorePackage.eINSTANCE.getEModelElement();
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EList getEAnnotations()
  {
    if (eAnnotations == null)
    {
      eAnnotations = new EObjectContainmentWithInverseEList(EAnnotation.class, this, EcorePackage.EMODEL_ELEMENT__EANNOTATIONS, EcorePackage.EANNOTATION__EMODEL_ELEMENT);
    }
    return eAnnotations;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated NOT
   */
  public EAnnotation getEAnnotation(String source)
  {
    if (source == null)
    {
      for (Iterator i = getEAnnotations().iterator(); i.hasNext(); )
      {
        EAnnotation eAnnotation = (EAnnotation)i.next();
        if (eAnnotation.getSource() == null)
        {
          return eAnnotation;
        }
      }
    }
    else
    {
      for (Iterator i = getEAnnotations().iterator(); i.hasNext(); )
      {
        EAnnotation eAnnotation = (EAnnotation)i.next();
        if (source.equals(eAnnotation.getSource()))
        {
          return eAnnotation;
        }
      }
    }

    return null;
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
        case EcorePackage.EMODEL_ELEMENT__EANNOTATIONS:
          return ((InternalEList)getEAnnotations()).basicAdd(otherEnd, msgs);
        default:
          return eDynamicInverseAdd(otherEnd, featureID, baseClass, msgs);
      }
    }
    if (eContainer != null)
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
        case EcorePackage.EMODEL_ELEMENT__EANNOTATIONS:
          return ((InternalEList)getEAnnotations()).basicRemove(otherEnd, msgs);
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
      case EcorePackage.EMODEL_ELEMENT__EANNOTATIONS:
        return getEAnnotations();
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
      case EcorePackage.EMODEL_ELEMENT__EANNOTATIONS:
        getEAnnotations().clear();
        getEAnnotations().addAll((Collection)newValue);
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
      case EcorePackage.EMODEL_ELEMENT__EANNOTATIONS:
        getEAnnotations().clear();
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
      case EcorePackage.EMODEL_ELEMENT__EANNOTATIONS:
        return eAnnotations != null && !eAnnotations.isEmpty();
    }
    return eDynamicIsSet(eFeature);
  }

  public String eURIFragmentSegment(EStructuralFeature eStructuralFeature, EObject eObject)
  {
    if (eObject instanceof ENamedElement)
    {
      ENamedElement eNamedElement = (ENamedElement)eObject;
      String name = eNamedElement.getName();
      if (name != null)
      {
        int count = 0;
        for (Iterator i = eContents().iterator(); i.hasNext(); )
        {
          Object otherEObject = i.next();
          if (otherEObject == eObject)
          {
            break;
          }
          if (otherEObject instanceof ENamedElement)
          {
            ENamedElement otherENamedElement = (ENamedElement)otherEObject;
            if (name.equals(otherENamedElement.getName()))
            {
              ++count;
            }
          }
        }
        return 
          count > 0 ?
            name + "." + count : 
            name;
      }
    }
    return super.eURIFragmentSegment(eStructuralFeature, eObject);
  }

  public EObject eObjectForURIFragmentSegment(String uriFragmentSegment)
  {
    if (!uriFragmentSegment.startsWith("@"))
    {
      int index = uriFragmentSegment.indexOf(".");
      String name = index == -1 ? uriFragmentSegment : uriFragmentSegment.substring(0, index);
      int count = 0;
      if (index != -1)
      {
        try
        {
          count = Integer.parseInt(uriFragmentSegment.substring(index + 1));
        }
        catch (NumberFormatException exception)
        {
          throw new WrappedException(exception);
        }
      }

      for (Iterator i = eContents().iterator(); i.hasNext(); )
      {
        Object object = i.next();
        if (object instanceof ENamedElement)
        {
          ENamedElement eNamedElement = (ENamedElement)object;
          if (name.equals(eNamedElement.getName()) && count-- == 0)
          {
            return eNamedElement;
          }
        }
      }

      return null;
    }
    else
    {
      return super.eObjectForURIFragmentSegment(uriFragmentSegment);
    }
  }

}
