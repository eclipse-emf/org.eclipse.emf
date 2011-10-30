/**
 * <copyright> 
 *
 * Copyright (c) 2002-2009 IBM Corporation and others.
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
 * $Id: GenEnumLiteralImpl.java,v 1.23 2011/10/30 10:12:38 emerks Exp $
 */
package org.eclipse.emf.codegen.ecore.genmodel.impl;


import org.eclipse.emf.codegen.ecore.genmodel.GenEnum;
import org.eclipse.emf.codegen.ecore.genmodel.GenEnumLiteral;
import org.eclipse.emf.codegen.ecore.genmodel.GenJDKLevel;
import org.eclipse.emf.codegen.ecore.genmodel.GenModelPackage;
import org.eclipse.emf.codegen.ecore.genmodel.GenPackage;
import org.eclipse.emf.codegen.util.CodeGenUtil;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EEnumLiteral;
import org.eclipse.emf.ecore.EModelElement;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.util.EcoreUtil;


/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Gen Enum Literal</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.emf.codegen.ecore.genmodel.impl.GenEnumLiteralImpl#getGenEnum <em>Gen Enum</em>}</li>
 *   <li>{@link org.eclipse.emf.codegen.ecore.genmodel.impl.GenEnumLiteralImpl#getEcoreEnumLiteral <em>Ecore Enum Literal</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class GenEnumLiteralImpl extends GenBaseImpl implements GenEnumLiteral
{
  /**
   * The cached value of the '{@link #getEcoreEnumLiteral() <em>Ecore Enum Literal</em>}' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getEcoreEnumLiteral()
   * @generated
   * @ordered
   */
  protected EEnumLiteral ecoreEnumLiteral;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated modifiable
   */
  protected GenEnumLiteralImpl() 
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
    return GenModelPackage.Literals.GEN_ENUM_LITERAL;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public GenEnum getGenEnum()
  {
    if (eContainerFeatureID() != GenModelPackage.GEN_ENUM_LITERAL__GEN_ENUM) return null;
    return (GenEnum)eContainer();
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetGenEnum(GenEnum newGenEnum, NotificationChain msgs)
  {
    msgs = eBasicSetContainer((InternalEObject)newGenEnum, GenModelPackage.GEN_ENUM_LITERAL__GEN_ENUM, msgs);
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setGenEnum(GenEnum newGenEnum)
  {
    if (newGenEnum != eInternalContainer() || (eContainerFeatureID() != GenModelPackage.GEN_ENUM_LITERAL__GEN_ENUM && newGenEnum != null))
    {
      if (EcoreUtil.isAncestor(this, newGenEnum))
        throw new IllegalArgumentException("Recursive containment not allowed for " + toString());
      NotificationChain msgs = null;
      if (eInternalContainer() != null)
        msgs = eBasicRemoveFromContainer(msgs);
      if (newGenEnum != null)
        msgs = ((InternalEObject)newGenEnum).eInverseAdd(this, GenModelPackage.GEN_ENUM__GEN_ENUM_LITERALS, GenEnum.class, msgs);
      msgs = basicSetGenEnum(newGenEnum, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, GenModelPackage.GEN_ENUM_LITERAL__GEN_ENUM, newGenEnum, newGenEnum));
  }

  @Override
  public EModelElement getEcoreModelElement()
  {
    return getEcoreEnumLiteral();
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EEnumLiteral getEcoreEnumLiteral()
  {
    if (ecoreEnumLiteral != null && ecoreEnumLiteral.eIsProxy())
    {
      InternalEObject oldEcoreEnumLiteral = (InternalEObject)ecoreEnumLiteral;
      ecoreEnumLiteral = (EEnumLiteral)eResolveProxy(oldEcoreEnumLiteral);
      if (ecoreEnumLiteral != oldEcoreEnumLiteral)
      {
        if (eNotificationRequired())
          eNotify(new ENotificationImpl(this, Notification.RESOLVE, GenModelPackage.GEN_ENUM_LITERAL__ECORE_ENUM_LITERAL, oldEcoreEnumLiteral, ecoreEnumLiteral));
      }
    }
    return ecoreEnumLiteral;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EEnumLiteral basicGetEcoreEnumLiteral()
  {
    return ecoreEnumLiteral;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setEcoreEnumLiteral(EEnumLiteral newEcoreEnumLiteral)
  {
    EEnumLiteral oldEcoreEnumLiteral = ecoreEnumLiteral;
    ecoreEnumLiteral = newEcoreEnumLiteral;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, GenModelPackage.GEN_ENUM_LITERAL__ECORE_ENUM_LITERAL, oldEcoreEnumLiteral, ecoreEnumLiteral));
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
      case GenModelPackage.GEN_ENUM_LITERAL__GEN_ENUM:
        if (eInternalContainer() != null)
          msgs = eBasicRemoveFromContainer(msgs);
        return basicSetGenEnum((GenEnum)otherEnd, msgs);
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
      case GenModelPackage.GEN_ENUM_LITERAL__GEN_ENUM:
        return basicSetGenEnum(null, msgs);
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
      case GenModelPackage.GEN_ENUM_LITERAL__GEN_ENUM:
        return eInternalContainer().eInverseRemove(this, GenModelPackage.GEN_ENUM__GEN_ENUM_LITERALS, GenEnum.class, msgs);
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
      case GenModelPackage.GEN_ENUM_LITERAL__GEN_ENUM:
        return getGenEnum();
      case GenModelPackage.GEN_ENUM_LITERAL__ECORE_ENUM_LITERAL:
        if (resolve) return getEcoreEnumLiteral();
        return basicGetEcoreEnumLiteral();
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
      case GenModelPackage.GEN_ENUM_LITERAL__GEN_ENUM:
        setGenEnum((GenEnum)newValue);
        return;
      case GenModelPackage.GEN_ENUM_LITERAL__ECORE_ENUM_LITERAL:
        setEcoreEnumLiteral((EEnumLiteral)newValue);
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
      case GenModelPackage.GEN_ENUM_LITERAL__GEN_ENUM:
        setGenEnum((GenEnum)null);
        return;
      case GenModelPackage.GEN_ENUM_LITERAL__ECORE_ENUM_LITERAL:
        setEcoreEnumLiteral((EEnumLiteral)null);
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
      case GenModelPackage.GEN_ENUM_LITERAL__GEN_ENUM:
        return getGenEnum() != null;
      case GenModelPackage.GEN_ENUM_LITERAL__ECORE_ENUM_LITERAL:
        return ecoreEnumLiteral != null;
    }
    return super.eIsSet(featureID);
  }

  @Override
  public String getName()
  {
    return getEcoreEnumLiteral().getName();
  }

  public String getCapName()
  {
    return capName(getName());
  }
  
  public String getLiteral()
  {
    return getEcoreEnumLiteral().getLiteral();
  }

  public String getFormattedName()
  {
    return format(getCapName(), ' ', null, false, false);
  }

  public int getValue()
  {
    return getEcoreEnumLiteral().getValue();
  }

  public String getEnumLiteralID()
  {
    String name = getName();
    return name == null ? null : CodeGenUtil.upperName(name, getGenModel().getLocale());
  }
  
  public String getEnumLiteralInstanceConstantName()
  {
    String enumLiteralID = getEnumLiteralID();
    return 
      enumLiteralID != null && (getEffectiveComplianceLevel().getValue() < GenJDKLevel.JDK50 || getGenEnum().isTypeSafeEnumCompatible()) ? 
        ensureUniqueness(enumLiteralID + "_LITERAL") :
        enumLiteralID;
  }
  
  protected String ensureUniqueness(String string)
  {
    for (GenEnumLiteral genEnumLiteral : getGenEnum().getGenEnumLiterals())
    {
      if (string.equals(genEnumLiteral.getEnumLiteralID()))
      {
        return string + "_";
      }
    }
    return string;
  }

  public String getEnumLiteralValueConstantName()
  {
    return 
      getEffectiveComplianceLevel().getValue() < GenJDKLevel.JDK50 || getGenEnum().isTypeSafeEnumCompatible() ? 
        getEnumLiteralID() :
        ensureUniqueness(getEnumLiteralID() + "_VALUE");
  }

  public GenPackage getGenPackage()
  {
    return getGenEnum().getGenPackage();
  }

  public String getEnumLiteralAccessorName()
  {
    return getGenEnum().getName() + "_" + getCapName();
  }

  public void initialize(EEnumLiteral eEnumLiteral)
  {
    setEcoreEnumLiteral(eEnumLiteral);
  }

  @Override
  public String getModelInfo()
  {
    StringBuffer result = new StringBuffer();
    if (!getEnumLiteralID().equals(getName()))
    {
      appendModelSetting(result, "name", getName());
    }
    if (!getName().equals(getLiteral()))
    {
      appendModelSetting(result, "literal", escapeString(getLiteral(), ""));
    }
    appendAnnotationInfo(result, getEcoreEnumLiteral());
    return result.toString().trim();
  }

  public boolean reconcile(GenEnumLiteral oldGenEnumLiteralVersion)
  {
    if (getEcoreEnumLiteral().getName().equals(oldGenEnumLiteralVersion.getEcoreEnumLiteral().getName()))
    {
      reconcileSettings(oldGenEnumLiteralVersion);
      return true;
    }
    else
    {
      return false;
    }
  }

  protected void reconcileSettings(GenEnumLiteral oldGenEnumLiteralVersion)
  {
    reconcileGenAnnotations(oldGenEnumLiteralVersion);
  }

  public boolean reconcile()
  {
    EEnumLiteral eEnumLiteral = getEcoreEnumLiteral();
    if (eEnumLiteral == null || eEnumLiteral.eIsProxy() || eEnumLiteral.eResource() == null)
    {
      return false;
    }
    else
    {
      return true;
    }
  }
}
