/**
 * Copyright (c) 2008-2012 IBM Corporation and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *   IBM - Initial API and implementation
 */
package org.eclipse.emf.codegen.ecore.genmodel.impl;

import java.util.Collection;

import org.eclipse.emf.codegen.ecore.genmodel.GenAnnotation;
import org.eclipse.emf.codegen.ecore.genmodel.GenBase;
import org.eclipse.emf.codegen.ecore.genmodel.GenModelPackage;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.EMap;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.EcorePackage;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EStringToStringMapEntryImpl;
import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.EObjectResolvingEList;
import org.eclipse.emf.ecore.util.EcoreEMap;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.util.InternalEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Gen Annotation</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.emf.codegen.ecore.genmodel.impl.GenAnnotationImpl#getSource <em>Source</em>}</li>
 *   <li>{@link org.eclipse.emf.codegen.ecore.genmodel.impl.GenAnnotationImpl#getDetails <em>Details</em>}</li>
 *   <li>{@link org.eclipse.emf.codegen.ecore.genmodel.impl.GenAnnotationImpl#getGenBase <em>Gen Base</em>}</li>
 *   <li>{@link org.eclipse.emf.codegen.ecore.genmodel.impl.GenAnnotationImpl#getReferences <em>References</em>}</li>
 *   <li>{@link org.eclipse.emf.codegen.ecore.genmodel.impl.GenAnnotationImpl#getContents <em>Contents</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class GenAnnotationImpl extends GenBaseImpl implements GenAnnotation
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
  protected EMap<String, String> details;

  /**
   * The cached value of the '{@link #getReferences() <em>References</em>}' reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getReferences()
   * @generated
   * @ordered
   */
  protected EList<EObject> references;

  /**
   * The cached value of the '{@link #getContents() <em>Contents</em>}' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getContents()
   * @generated
   * @ordered
   */
  protected EList<EObject> contents;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected GenAnnotationImpl()
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
    return GenModelPackage.Literals.GEN_ANNOTATION;
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
      eNotify(new ENotificationImpl(this, Notification.SET, GenModelPackage.GEN_ANNOTATION__SOURCE, oldSource, source));
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
      details = new EcoreEMap<String,String>(EcorePackage.Literals.ESTRING_TO_STRING_MAP_ENTRY, EStringToStringMapEntryImpl.class, this, GenModelPackage.GEN_ANNOTATION__DETAILS);
    }
    return details;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public GenBase getGenBase()
  {
    if (eContainerFeatureID() != GenModelPackage.GEN_ANNOTATION__GEN_BASE) return null;
    return (GenBase)eInternalContainer();
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetGenBase(GenBase newGenBase, NotificationChain msgs)
  {
    msgs = eBasicSetContainer((InternalEObject)newGenBase, GenModelPackage.GEN_ANNOTATION__GEN_BASE, msgs);
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setGenBase(GenBase newGenBase)
  {
    if (newGenBase != eInternalContainer() || (eContainerFeatureID() != GenModelPackage.GEN_ANNOTATION__GEN_BASE && newGenBase != null))
    {
      if (EcoreUtil.isAncestor(this, newGenBase))
        throw new IllegalArgumentException("Recursive containment not allowed for " + toString());
      NotificationChain msgs = null;
      if (eInternalContainer() != null)
        msgs = eBasicRemoveFromContainer(msgs);
      if (newGenBase != null)
        msgs = ((InternalEObject)newGenBase).eInverseAdd(this, GenModelPackage.GEN_BASE__GEN_ANNOTATIONS, GenBase.class, msgs);
      msgs = basicSetGenBase(newGenBase, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, GenModelPackage.GEN_ANNOTATION__GEN_BASE, newGenBase, newGenBase));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EList<EObject> getReferences()
  {
    if (references == null)
    {
      references = new EObjectResolvingEList<EObject>(EObject.class, this, GenModelPackage.GEN_ANNOTATION__REFERENCES);
    }
    return references;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EList<EObject> getContents()
  {
    if (contents == null)
    {
      contents = new EObjectContainmentEList<EObject>(EObject.class, this, GenModelPackage.GEN_ANNOTATION__CONTENTS);
    }
    return contents;
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
      case GenModelPackage.GEN_ANNOTATION__GEN_BASE:
        if (eInternalContainer() != null)
          msgs = eBasicRemoveFromContainer(msgs);
        return basicSetGenBase((GenBase)otherEnd, msgs);
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
      case GenModelPackage.GEN_ANNOTATION__DETAILS:
        return ((InternalEList<?>)getDetails()).basicRemove(otherEnd, msgs);
      case GenModelPackage.GEN_ANNOTATION__GEN_BASE:
        return basicSetGenBase(null, msgs);
      case GenModelPackage.GEN_ANNOTATION__CONTENTS:
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
  public NotificationChain eBasicRemoveFromContainerFeature(NotificationChain msgs)
  {
    switch (eContainerFeatureID())
    {
      case GenModelPackage.GEN_ANNOTATION__GEN_BASE:
        return eInternalContainer().eInverseRemove(this, GenModelPackage.GEN_BASE__GEN_ANNOTATIONS, GenBase.class, msgs);
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
      case GenModelPackage.GEN_ANNOTATION__SOURCE:
        return getSource();
      case GenModelPackage.GEN_ANNOTATION__DETAILS:
        if (coreType) return getDetails();
        else return getDetails().map();
      case GenModelPackage.GEN_ANNOTATION__GEN_BASE:
        return getGenBase();
      case GenModelPackage.GEN_ANNOTATION__REFERENCES:
        return getReferences();
      case GenModelPackage.GEN_ANNOTATION__CONTENTS:
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
      case GenModelPackage.GEN_ANNOTATION__SOURCE:
        setSource((String)newValue);
        return;
      case GenModelPackage.GEN_ANNOTATION__DETAILS:
        ((EStructuralFeature.Setting)getDetails()).set(newValue);
        return;
      case GenModelPackage.GEN_ANNOTATION__GEN_BASE:
        setGenBase((GenBase)newValue);
        return;
      case GenModelPackage.GEN_ANNOTATION__REFERENCES:
        getReferences().clear();
        getReferences().addAll((Collection<? extends EObject>)newValue);
        return;
      case GenModelPackage.GEN_ANNOTATION__CONTENTS:
        getContents().clear();
        getContents().addAll((Collection<? extends EObject>)newValue);
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
      case GenModelPackage.GEN_ANNOTATION__SOURCE:
        setSource(SOURCE_EDEFAULT);
        return;
      case GenModelPackage.GEN_ANNOTATION__DETAILS:
        getDetails().clear();
        return;
      case GenModelPackage.GEN_ANNOTATION__GEN_BASE:
        setGenBase((GenBase)null);
        return;
      case GenModelPackage.GEN_ANNOTATION__REFERENCES:
        getReferences().clear();
        return;
      case GenModelPackage.GEN_ANNOTATION__CONTENTS:
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
      case GenModelPackage.GEN_ANNOTATION__SOURCE:
        return SOURCE_EDEFAULT == null ? source != null : !SOURCE_EDEFAULT.equals(source);
      case GenModelPackage.GEN_ANNOTATION__DETAILS:
        return details != null && !details.isEmpty();
      case GenModelPackage.GEN_ANNOTATION__GEN_BASE:
        return getGenBase() != null;
      case GenModelPackage.GEN_ANNOTATION__REFERENCES:
        return references != null && !references.isEmpty();
      case GenModelPackage.GEN_ANNOTATION__CONTENTS:
        return contents != null && !contents.isEmpty();
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
    result.append(" (source: ");
    result.append(source);
    result.append(')');
    return result.toString();
  }

  public boolean reconcile()
  {
    return true;
  }

  @Override
  public String getName()
  {
    return getSource();
  }  
} //GenAnnotationImpl
