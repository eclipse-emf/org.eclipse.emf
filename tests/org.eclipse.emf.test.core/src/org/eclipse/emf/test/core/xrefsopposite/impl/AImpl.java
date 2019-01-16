/**
 * Copyright (c) 2019 THALES GLOBAL SERVICES.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 * 
 * Contributors:
 *   Thales - initial API and implementation
 */
package org.eclipse.emf.test.core.xrefsopposite.impl;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

import org.eclipse.emf.ecore.util.EObjectResolvingEList;
import org.eclipse.emf.ecore.util.EObjectWithInverseResolvingEList;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.util.InternalEList;

import org.eclipse.emf.test.core.xrefsopposite.A;
import org.eclipse.emf.test.core.xrefsopposite.APkg;
import org.eclipse.emf.test.core.xrefsopposite.XRefsOppositePackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>A</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.emf.test.core.xrefsopposite.impl.AImpl#getName <em>Name</em>}</li>
 *   <li>{@link org.eclipse.emf.test.core.xrefsopposite.impl.AImpl#getRef1 <em>Ref1</em>}</li>
 *   <li>{@link org.eclipse.emf.test.core.xrefsopposite.impl.AImpl#getOref1 <em>Oref1</em>}</li>
 *   <li>{@link org.eclipse.emf.test.core.xrefsopposite.impl.AImpl#getRef2 <em>Ref2</em>}</li>
 *   <li>{@link org.eclipse.emf.test.core.xrefsopposite.impl.AImpl#getOref2 <em>Oref2</em>}</li>
 *   <li>{@link org.eclipse.emf.test.core.xrefsopposite.impl.AImpl#getRef3 <em>Ref3</em>}</li>
 *   <li>{@link org.eclipse.emf.test.core.xrefsopposite.impl.AImpl#getOwningAPkg <em>Owning APkg</em>}</li>
 * </ul>
 *
 * @generated
 */
public class AImpl extends AbstractAImpl implements A
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

  /**
   * The cached value of the '{@link #getRef1() <em>Ref1</em>}' reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getRef1()
   * @generated
   * @ordered
   */
  protected EList<A> ref1;

  /**
   * The cached value of the '{@link #getOref1() <em>Oref1</em>}' reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getOref1()
   * @generated
   * @ordered
   */
  protected EList<A> oref1;

  /**
   * The cached value of the '{@link #getRef2() <em>Ref2</em>}' reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getRef2()
   * @generated
   * @ordered
   */
  protected EList<A> ref2;

  /**
   * The cached value of the '{@link #getOref2() <em>Oref2</em>}' reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getOref2()
   * @generated
   * @ordered
   */
  protected EList<A> oref2;

  /**
   * The cached value of the '{@link #getRef3() <em>Ref3</em>}' reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getRef3()
   * @generated
   * @ordered
   */
  protected EList<A> ref3;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected AImpl()
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
    return XRefsOppositePackage.Literals.A;
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
      eNotify(new ENotificationImpl(this, Notification.SET, XRefsOppositePackage.A__NAME, oldName, name));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EList<A> getRef1()
  {
    if (ref1 == null)
    {
      ref1 = new EObjectWithInverseResolvingEList.ManyInverse<A>(A.class, this, XRefsOppositePackage.A__REF1, XRefsOppositePackage.A__OREF1);
    }
    return ref1;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EList<A> getOref1()
  {
    if (oref1 == null)
    {
      oref1 = new EObjectWithInverseResolvingEList.ManyInverse<A>(A.class, this, XRefsOppositePackage.A__OREF1, XRefsOppositePackage.A__REF1);
    }
    return oref1;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EList<A> getRef2()
  {
    if (ref2 == null)
    {
      ref2 = new EObjectWithInverseResolvingEList.ManyInverse<A>(A.class, this, XRefsOppositePackage.A__REF2, XRefsOppositePackage.A__OREF2);
    }
    return ref2;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EList<A> getOref2()
  {
    if (oref2 == null)
    {
      oref2 = new EObjectWithInverseResolvingEList.ManyInverse<A>(A.class, this, XRefsOppositePackage.A__OREF2, XRefsOppositePackage.A__REF2);
    }
    return oref2;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EList<A> getRef3()
  {
    if (ref3 == null)
    {
      ref3 = new EObjectResolvingEList<A>(A.class, this, XRefsOppositePackage.A__REF3);
    }
    return ref3;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public APkg getOwningAPkg()
  {
    if (eContainerFeatureID() != XRefsOppositePackage.A__OWNING_APKG) return null;
    return (APkg)eInternalContainer();
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetOwningAPkg(APkg newOwningAPkg, NotificationChain msgs)
  {
    msgs = eBasicSetContainer((InternalEObject)newOwningAPkg, XRefsOppositePackage.A__OWNING_APKG, msgs);
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setOwningAPkg(APkg newOwningAPkg)
  {
    if (newOwningAPkg != eInternalContainer() || (eContainerFeatureID() != XRefsOppositePackage.A__OWNING_APKG && newOwningAPkg != null))
    {
      if (EcoreUtil.isAncestor(this, newOwningAPkg))
        throw new IllegalArgumentException("Recursive containment not allowed for " + toString());
      NotificationChain msgs = null;
      if (eInternalContainer() != null)
        msgs = eBasicRemoveFromContainer(msgs);
      if (newOwningAPkg != null)
        msgs = ((InternalEObject)newOwningAPkg).eInverseAdd(this, XRefsOppositePackage.APKG__OWNED_AS, APkg.class, msgs);
      msgs = basicSetOwningAPkg(newOwningAPkg, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, XRefsOppositePackage.A__OWNING_APKG, newOwningAPkg, newOwningAPkg));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @SuppressWarnings("unchecked")
  @Override
  public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs)
  {
    switch (featureID)
    {
      case XRefsOppositePackage.A__REF1:
        return ((InternalEList<InternalEObject>)(InternalEList<?>)getRef1()).basicAdd(otherEnd, msgs);
      case XRefsOppositePackage.A__OREF1:
        return ((InternalEList<InternalEObject>)(InternalEList<?>)getOref1()).basicAdd(otherEnd, msgs);
      case XRefsOppositePackage.A__REF2:
        return ((InternalEList<InternalEObject>)(InternalEList<?>)getRef2()).basicAdd(otherEnd, msgs);
      case XRefsOppositePackage.A__OREF2:
        return ((InternalEList<InternalEObject>)(InternalEList<?>)getOref2()).basicAdd(otherEnd, msgs);
      case XRefsOppositePackage.A__OWNING_APKG:
        if (eInternalContainer() != null)
          msgs = eBasicRemoveFromContainer(msgs);
        return basicSetOwningAPkg((APkg)otherEnd, msgs);
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
      case XRefsOppositePackage.A__REF1:
        return ((InternalEList<?>)getRef1()).basicRemove(otherEnd, msgs);
      case XRefsOppositePackage.A__OREF1:
        return ((InternalEList<?>)getOref1()).basicRemove(otherEnd, msgs);
      case XRefsOppositePackage.A__REF2:
        return ((InternalEList<?>)getRef2()).basicRemove(otherEnd, msgs);
      case XRefsOppositePackage.A__OREF2:
        return ((InternalEList<?>)getOref2()).basicRemove(otherEnd, msgs);
      case XRefsOppositePackage.A__OWNING_APKG:
        return basicSetOwningAPkg(null, msgs);
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
      case XRefsOppositePackage.A__OWNING_APKG:
        return eInternalContainer().eInverseRemove(this, XRefsOppositePackage.APKG__OWNED_AS, APkg.class, msgs);
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
      case XRefsOppositePackage.A__NAME:
        return getName();
      case XRefsOppositePackage.A__REF1:
        return getRef1();
      case XRefsOppositePackage.A__OREF1:
        return getOref1();
      case XRefsOppositePackage.A__REF2:
        return getRef2();
      case XRefsOppositePackage.A__OREF2:
        return getOref2();
      case XRefsOppositePackage.A__REF3:
        return getRef3();
      case XRefsOppositePackage.A__OWNING_APKG:
        return getOwningAPkg();
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
      case XRefsOppositePackage.A__NAME:
        setName((String)newValue);
        return;
      case XRefsOppositePackage.A__REF1:
        getRef1().clear();
        getRef1().addAll((Collection<? extends A>)newValue);
        return;
      case XRefsOppositePackage.A__OREF1:
        getOref1().clear();
        getOref1().addAll((Collection<? extends A>)newValue);
        return;
      case XRefsOppositePackage.A__REF2:
        getRef2().clear();
        getRef2().addAll((Collection<? extends A>)newValue);
        return;
      case XRefsOppositePackage.A__OREF2:
        getOref2().clear();
        getOref2().addAll((Collection<? extends A>)newValue);
        return;
      case XRefsOppositePackage.A__REF3:
        getRef3().clear();
        getRef3().addAll((Collection<? extends A>)newValue);
        return;
      case XRefsOppositePackage.A__OWNING_APKG:
        setOwningAPkg((APkg)newValue);
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
      case XRefsOppositePackage.A__NAME:
        setName(NAME_EDEFAULT);
        return;
      case XRefsOppositePackage.A__REF1:
        getRef1().clear();
        return;
      case XRefsOppositePackage.A__OREF1:
        getOref1().clear();
        return;
      case XRefsOppositePackage.A__REF2:
        getRef2().clear();
        return;
      case XRefsOppositePackage.A__OREF2:
        getOref2().clear();
        return;
      case XRefsOppositePackage.A__REF3:
        getRef3().clear();
        return;
      case XRefsOppositePackage.A__OWNING_APKG:
        setOwningAPkg((APkg)null);
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
      case XRefsOppositePackage.A__NAME:
        return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
      case XRefsOppositePackage.A__REF1:
        return ref1 != null && !ref1.isEmpty();
      case XRefsOppositePackage.A__OREF1:
        return oref1 != null && !oref1.isEmpty();
      case XRefsOppositePackage.A__REF2:
        return ref2 != null && !ref2.isEmpty();
      case XRefsOppositePackage.A__OREF2:
        return oref2 != null && !oref2.isEmpty();
      case XRefsOppositePackage.A__REF3:
        return ref3 != null && !ref3.isEmpty();
      case XRefsOppositePackage.A__OWNING_APKG:
        return getOwningAPkg() != null;
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

    StringBuilder result = new StringBuilder(super.toString());
    result.append(" (name: ");
    result.append(name);
    result.append(')');
    return result.toString();
  }

} //AImpl
