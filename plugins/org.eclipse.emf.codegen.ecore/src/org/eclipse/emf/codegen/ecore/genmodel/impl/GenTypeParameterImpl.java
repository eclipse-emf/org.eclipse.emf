/**
 * Copyright (c) 2006 IBM Corporation and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 * 
 * Contributors: 
 *   IBM - Initial API and implementation
 */
package org.eclipse.emf.codegen.ecore.genmodel.impl;

import java.util.Iterator;

import org.eclipse.emf.codegen.ecore.genmodel.GenModelPackage;
import org.eclipse.emf.codegen.ecore.genmodel.GenTypeParameter;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EGenericType;
import org.eclipse.emf.ecore.EModelElement;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.ETypeParameter;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Gen Type Parameter</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.emf.codegen.ecore.genmodel.impl.GenTypeParameterImpl#getEcoreTypeParameter <em>Ecore Type Parameter</em>}</li>
 *   <li>{@link org.eclipse.emf.codegen.ecore.genmodel.impl.GenTypeParameterImpl#getDocumentation <em>Documentation</em>}</li>
 * </ul>
 *
 * @generated
 */
public class GenTypeParameterImpl extends GenBaseImpl implements GenTypeParameter
{
  /**
   * The cached value of the '{@link #getEcoreTypeParameter() <em>Ecore Type Parameter</em>}' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getEcoreTypeParameter()
   * @generated
   * @ordered
   */
  protected ETypeParameter ecoreTypeParameter;
  /**
   * The default value of the '{@link #getDocumentation() <em>Documentation</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getDocumentation()
   * @since 2.14
   * @generated
   * @ordered
   */
  protected static final String DOCUMENTATION_EDEFAULT = null;
  /**
   * The cached value of the '{@link #getDocumentation() <em>Documentation</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getDocumentation()
   * @since 2.14
   * @generated
   * @ordered
   */
  protected String documentation = DOCUMENTATION_EDEFAULT;
  /**
   * This is true if the Documentation attribute has been set.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @since 2.14
   * @generated
   * @ordered
   */
  protected boolean documentationESet;
  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected GenTypeParameterImpl()
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
    return GenModelPackage.Literals.GEN_TYPE_PARAMETER;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public ETypeParameter getEcoreTypeParameter()
  {
    if (ecoreTypeParameter != null && ecoreTypeParameter.eIsProxy())
    {
      InternalEObject oldEcoreTypeParameter = (InternalEObject)ecoreTypeParameter;
      ecoreTypeParameter = (ETypeParameter)eResolveProxy(oldEcoreTypeParameter);
      if (ecoreTypeParameter != oldEcoreTypeParameter)
      {
        if (eNotificationRequired())
          eNotify(new ENotificationImpl(this, Notification.RESOLVE, GenModelPackage.GEN_TYPE_PARAMETER__ECORE_TYPE_PARAMETER, oldEcoreTypeParameter, ecoreTypeParameter));
      }
    }
    return ecoreTypeParameter;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public ETypeParameter basicGetEcoreTypeParameter()
  {
    return ecoreTypeParameter;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setEcoreTypeParameter(ETypeParameter newEcoreTypeParameter)
  {
    ETypeParameter oldEcoreTypeParameter = ecoreTypeParameter;
    ecoreTypeParameter = newEcoreTypeParameter;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, GenModelPackage.GEN_TYPE_PARAMETER__ECORE_TYPE_PARAMETER, oldEcoreTypeParameter, ecoreTypeParameter));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @since 2.14
   * @generated
   */
  public String getDocumentationGen()
  {
    return documentation;
  }

  @Override
  public String getDocumentation()
  {
    return isSetDocumentation() ? getDocumentationGen() : super.getDocumentation();
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @since 2.14
   * @generated
   */
  public void setDocumentation(String newDocumentation)
  {
    String oldDocumentation = documentation;
    documentation = newDocumentation;
    boolean oldDocumentationESet = documentationESet;
    documentationESet = true;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, GenModelPackage.GEN_TYPE_PARAMETER__DOCUMENTATION, oldDocumentation, documentation, !oldDocumentationESet));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @since 2.14
   * @generated
   */
  public void unsetDocumentation()
  {
    String oldDocumentation = documentation;
    boolean oldDocumentationESet = documentationESet;
    documentation = DOCUMENTATION_EDEFAULT;
    documentationESet = false;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.UNSET, GenModelPackage.GEN_TYPE_PARAMETER__DOCUMENTATION, oldDocumentation, DOCUMENTATION_EDEFAULT, oldDocumentationESet));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @since 2.14
   * @generated
   */
  public boolean isSetDocumentation()
  {
    return documentationESet;
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
      case GenModelPackage.GEN_TYPE_PARAMETER__ECORE_TYPE_PARAMETER:
        if (resolve) return getEcoreTypeParameter();
        return basicGetEcoreTypeParameter();
      case GenModelPackage.GEN_TYPE_PARAMETER__DOCUMENTATION:
        return getDocumentation();
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
      case GenModelPackage.GEN_TYPE_PARAMETER__ECORE_TYPE_PARAMETER:
        setEcoreTypeParameter((ETypeParameter)newValue);
        return;
      case GenModelPackage.GEN_TYPE_PARAMETER__DOCUMENTATION:
        setDocumentation((String)newValue);
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
      case GenModelPackage.GEN_TYPE_PARAMETER__ECORE_TYPE_PARAMETER:
        setEcoreTypeParameter((ETypeParameter)null);
        return;
      case GenModelPackage.GEN_TYPE_PARAMETER__DOCUMENTATION:
        unsetDocumentation();
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
      case GenModelPackage.GEN_TYPE_PARAMETER__ECORE_TYPE_PARAMETER:
        return ecoreTypeParameter != null;
      case GenModelPackage.GEN_TYPE_PARAMETER__DOCUMENTATION:
        return isSetDocumentation();
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
    result.append(" (documentation: ");
    if (documentationESet) result.append(documentation); else result.append("<unset>");
    result.append(')');
    return result.toString();
  }

  @Override
  public String getName()
  {
    ETypeParameter ecoreTypeParameter = getEcoreTypeParameter();
	return ecoreTypeParameter == null || ecoreTypeParameter.getName() == null ? "" : ecoreTypeParameter.getName();
  }

  public void initialize(ETypeParameter eTypeParameter)
  {
    if (eTypeParameter != getEcoreTypeParameter())
    {
      setEcoreTypeParameter(eTypeParameter);
    }
  }

  public boolean reconcile(GenTypeParameter oldGenTypeParameterVersion)
  {
    reconcileSettings(oldGenTypeParameterVersion);
    return true;
  }

  protected void reconcileSettings(GenTypeParameter oldGenTypeParameterVersion)
  {
    reconcileGenAnnotations(oldGenTypeParameterVersion);
    if (oldGenTypeParameterVersion.eIsSet(GenModelPackage.Literals.GEN_TYPE_PARAMETER__DOCUMENTATION))
    {
      setDocumentation(oldGenTypeParameterVersion.getDocumentation());
    }
  }

  public boolean reconcile()
  {
    ETypeParameter eTypeParameter = getEcoreTypeParameter();
    if (eTypeParameter == null || eTypeParameter.eIsProxy()  || eTypeParameter.eResource() == null)
    {
      return false;
    }
    else
    {
      return true;
    }
  }

  public boolean isUsed()
  {
    for (Iterator<EObject> i = getEcoreTypeParameter().eContainer().eAllContents(); i.hasNext(); )
    {
      EObject eObject = i.next();
      if (eObject instanceof EGenericType && ((EGenericType)eObject).getETypeParameter() == ecoreTypeParameter)
      {
        return true;
      }
    }
    return false;
  }
  
  public String getQualifiedModelInfo()
  {
    return getModelInfo(true);
  }

  protected String getModelInfo(boolean qualified)
  {
    StringBuffer result = new StringBuffer();
    ETypeParameter eTypeParameter = getEcoreTypeParameter();
    if (hasReferenceToClassifierWithInstanceTypeName(eTypeParameter.getEBounds()))
    {
      StringBuilder type = new StringBuilder();
      for (EGenericType eGenericType : eTypeParameter.getEBounds())
      {
        type.append(getEcoreType(eGenericType));
        type.append(' ');
      }
      appendModelSetting(result, getName(), "bounds", type.toString().trim());
    }
    return result.toString().trim();
  }
  
  @Override
  public EModelElement getEcoreModelElement()
  {
    return getEcoreTypeParameter();
  }

  @Override
  protected EModelElement basicGetEcoreModelElement()
  {
    return ecoreTypeParameter;
  }

} //GenTypeParameterImpl
