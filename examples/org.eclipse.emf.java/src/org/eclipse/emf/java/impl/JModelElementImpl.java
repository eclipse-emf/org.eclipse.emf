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
 * $Id: JModelElementImpl.java,v 1.2 2005/06/08 06:21:07 nickb Exp $
 */
package org.eclipse.emf.java.impl;


import java.util.Collection;
import java.util.Iterator;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;
import org.eclipse.emf.java.JModelElement;
import org.eclipse.emf.java.JavaPackage;


/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>JModel IDOMNode</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.emf.java.impl.JModelElementImpl#getName <em>Name</em>}</li>
 *   <li>{@link org.eclipse.emf.java.impl.JModelElementImpl#getJNode <em>JNode</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public abstract class JModelElementImpl extends EObjectImpl implements JModelElement
{
  /**
   * The default value of the '{@link #getName() <em>Name</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getName()
   * @generated
   * @ordered
   */
  protected static final String NAME_EDEFAULT = null;

  /**
   * The cached value of the '{@link #getName() <em>Name</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getName()
   * @generated
   * @ordered
   */
  protected String name = NAME_EDEFAULT;

  protected boolean isReconciling;

  /**
   * The default value of the '{@link #getJNode() <em>JNode</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getJNode()
   * @generated
   * @ordered
   */
  protected static final Object JNODE_EDEFAULT = null;

  /**
   * The cached value of the '{@link #getJNode() <em>JNode</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getJNode()
   * @generated
   * @ordered
   */
  protected Object jNode = JNODE_EDEFAULT;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected JModelElementImpl()
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
    return JavaPackage.eINSTANCE.getJModelElement();
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public String getName()
  {
    return name;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setName(String newName)
  {
    String oldName = name;
    name = newName;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, JavaPackage.JMODEL_ELEMENT__NAME, oldName, name));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public Object getJNode()
  {
    return jNode;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setJNodeGen(Object newJNode)
  {
    Object oldJNode = jNode;
    jNode = newJNode;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, JavaPackage.JMODEL_ELEMENT__JNODE, oldJNode, jNode));
  }

  /**
   * EATM 
   */
  public void setJNode(Object newJNode)
  {
    if (newJNode == null)
    {
      isReconciling = true;
      setJNodeGen(null);
      for (Iterator contents = eContents().iterator(); contents.hasNext(); )
      {
        JModelElement content = (JModelElement)contents.next();
        content.setJNode(null);
      }
      isReconciling = false;
    }
    else
    {
      setJNodeGen(newJNode);
    }
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
      case JavaPackage.JMODEL_ELEMENT__NAME:
        return getName();
      case JavaPackage.JMODEL_ELEMENT__JNODE:
        return getJNode();
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
      case JavaPackage.JMODEL_ELEMENT__NAME:
        setName((String)newValue);
        return;
      case JavaPackage.JMODEL_ELEMENT__JNODE:
        setJNode((Object)newValue);
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
      case JavaPackage.JMODEL_ELEMENT__NAME:
        setName(NAME_EDEFAULT);
        return;
      case JavaPackage.JMODEL_ELEMENT__JNODE:
        setJNode(JNODE_EDEFAULT);
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
      case JavaPackage.JMODEL_ELEMENT__NAME:
        return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
      case JavaPackage.JMODEL_ELEMENT__JNODE:
        return JNODE_EDEFAULT == null ? jNode != null : !JNODE_EDEFAULT.equals(jNode);
    }
    return eDynamicIsSet(eFeature);
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
    result.append(" (name: ");
    result.append(name);
    result.append(", jNode: ");
    result.append(jNode);
    result.append(')');
    return result.toString();
  }

  /**
   * EATM
   */
  public boolean eNotificationRequired()
  {
    return true;
  }

  public void eNotify(Notification notification)
  {
    int eventType = notification.getEventType();
    Object feature = notification.getFeature();
    if (eClass().getEAllReferences().contains(feature))
    {
      EReference eReference = (EReference)feature;
      if (eReference.isContainment() && !eReference.isTransient())
      {
        Object oldValue = notification.getOldValue();
        Object newValue = notification.getNewValue();
        switch (eventType)
        {
          case Notification.ADD:
          {
            adoptContent(eReference, (JModelElement)newValue);
            break;
          }
          case Notification.ADD_MANY:
          {
            for (Iterator newValues = ((Collection)newValue).iterator();  newValues.hasNext(); )
            {
              JModelElement jModelElement = (JModelElement)newValues.next();
              adoptContent(eReference, jModelElement);
            }
            break;
          }
          case Notification.REMOVE:
          {
            if (oldValue != null)
            {
              orphanContent(eReference, (JModelElement)oldValue);
            }
            break;
          }
          case Notification.REMOVE_MANY:
          {
            for (Iterator oldValues = ((Collection)oldValue).iterator();  oldValues.hasNext(); )
            {
              JModelElement jModelElement = (JModelElement)oldValues.next();
              orphanContent(eReference, jModelElement);
            }
            break;
          }
          case Notification.MOVE:
          {
            moveContent(eReference, (JModelElement)newValue);
            break;
          }
          case Notification.SET:
          case Notification.UNSET:
          {
            if (oldValue != null)
            {
              orphanContent(eReference, (JModelElement)oldValue);
            }
            if (newValue != null)
            {
              adoptContent(eReference, (JModelElement)newValue);
            }
            break;
          }
        }
      }
      else
      {
        switch (eventType)
        {
          case Notification.ADD:
          case Notification.ADD_MANY:
          case Notification.REMOVE:
          case Notification.REMOVE_MANY:
          case Notification.MOVE:
          case Notification.SET:
          case Notification.UNSET:
          {
            changeReference(notification);
            break;
          }
        }
      }
    }
    else if (eClass().getEAllAttributes().contains(feature))
    {
      EAttribute eAttribute = (EAttribute)feature;
      switch (eventType)
      {
        case Notification.ADD:
        case Notification.ADD_MANY:
        case Notification.REMOVE:
        case Notification.REMOVE_MANY:
        case Notification.MOVE:
        case Notification.SET:
        case Notification.UNSET:
        {
          changeAttribute(notification);
          break;
        }
      }
    }

    super.eNotify(notification);
  }

  protected void resolveIdentifiers()
  {
  }

  protected void changeAttribute(Notification notification)
  {
  }

  protected void changeReference(Notification notification)
  {
  }

  protected void adoptContent(EReference eReference, JModelElement jModelElement)
  {
  }

  protected void orphanContent(EReference eReference, JModelElement jModelElement)
  {
  }

  public void moveContent(EReference eReference, JModelElement jModelElement)
  {
  }

  public String getQualifiedName()
  {
    if (eContainer() instanceof JModelElement)
    {
      String containerQualifiedName = ((JModelElement)eContainer).getQualifiedName();
      if (containerQualifiedName.length() > 0)
      {
        return containerQualifiedName + "." + getName();
      }
    }

    return getName();
  }
} //JModelElementImpl
