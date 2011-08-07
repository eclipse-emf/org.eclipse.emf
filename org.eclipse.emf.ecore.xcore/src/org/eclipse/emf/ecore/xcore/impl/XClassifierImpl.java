/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.eclipse.emf.ecore.xcore.impl;

import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;

import org.eclipse.emf.ecore.xcore.XClassifier;
import org.eclipse.emf.ecore.xcore.XPackage;
import org.eclipse.emf.ecore.xcore.XTypeParameter;
import org.eclipse.emf.ecore.xcore.XcorePackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>XClassifier</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.emf.ecore.xcore.impl.XClassifierImpl#getInstanceTypeName <em>Instance Type Name</em>}</li>
 *   <li>{@link org.eclipse.emf.ecore.xcore.impl.XClassifierImpl#getPackage <em>Package</em>}</li>
 *   <li>{@link org.eclipse.emf.ecore.xcore.impl.XClassifierImpl#getTypeParameters <em>Type Parameters</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public abstract class XClassifierImpl extends XNamedElementImpl implements XClassifier
{
  /**
   * The default value of the '{@link #getInstanceTypeName() <em>Instance Type Name</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getInstanceTypeName()
   * @generated
   * @ordered
   */
  protected static final String INSTANCE_TYPE_NAME_EDEFAULT = null;

  /**
   * The cached value of the '{@link #getInstanceTypeName() <em>Instance Type Name</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getInstanceTypeName()
   * @generated
   * @ordered
   */
  protected String instanceTypeName = INSTANCE_TYPE_NAME_EDEFAULT;

  /**
   * This is true if the Instance Type Name attribute has been set.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  protected boolean instanceTypeNameESet;

  /**
   * The cached value of the '{@link #getTypeParameters() <em>Type Parameters</em>}' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getTypeParameters()
   * @generated
   * @ordered
   */
  protected EList<XTypeParameter> typeParameters;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected XClassifierImpl()
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
    return XcorePackage.Literals.XCLASSIFIER;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public String getInstanceTypeName()
  {
    return instanceTypeName;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setInstanceTypeName(String newInstanceTypeName)
  {
    String oldInstanceTypeName = instanceTypeName;
    instanceTypeName = newInstanceTypeName;
    boolean oldInstanceTypeNameESet = instanceTypeNameESet;
    instanceTypeNameESet = true;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, XcorePackage.XCLASSIFIER__INSTANCE_TYPE_NAME, oldInstanceTypeName, instanceTypeName, !oldInstanceTypeNameESet));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void unsetInstanceTypeName()
  {
    String oldInstanceTypeName = instanceTypeName;
    boolean oldInstanceTypeNameESet = instanceTypeNameESet;
    instanceTypeName = INSTANCE_TYPE_NAME_EDEFAULT;
    instanceTypeNameESet = false;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.UNSET, XcorePackage.XCLASSIFIER__INSTANCE_TYPE_NAME, oldInstanceTypeName, INSTANCE_TYPE_NAME_EDEFAULT, oldInstanceTypeNameESet));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public boolean isSetInstanceTypeName()
  {
    return instanceTypeNameESet;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public XPackage getPackage()
  {
    if (eContainerFeatureID() != XcorePackage.XCLASSIFIER__PACKAGE) return null;
    return (XPackage)eContainer();
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EList<XTypeParameter> getTypeParameters()
  {
    if (typeParameters == null)
    {
      typeParameters = new EObjectContainmentEList<XTypeParameter>(XTypeParameter.class, this, XcorePackage.XCLASSIFIER__TYPE_PARAMETERS);
    }
    return typeParameters;
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
      case XcorePackage.XCLASSIFIER__PACKAGE:
        if (eInternalContainer() != null)
          msgs = eBasicRemoveFromContainer(msgs);
        return eBasicSetContainer(otherEnd, XcorePackage.XCLASSIFIER__PACKAGE, msgs);
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
      case XcorePackage.XCLASSIFIER__PACKAGE:
        return eBasicSetContainer(null, XcorePackage.XCLASSIFIER__PACKAGE, msgs);
      case XcorePackage.XCLASSIFIER__TYPE_PARAMETERS:
        return ((InternalEList<?>)getTypeParameters()).basicRemove(otherEnd, msgs);
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
      case XcorePackage.XCLASSIFIER__PACKAGE:
        return eInternalContainer().eInverseRemove(this, XcorePackage.XPACKAGE__CLASSIFIERS, XPackage.class, msgs);
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
      case XcorePackage.XCLASSIFIER__INSTANCE_TYPE_NAME:
        return getInstanceTypeName();
      case XcorePackage.XCLASSIFIER__PACKAGE:
        return getPackage();
      case XcorePackage.XCLASSIFIER__TYPE_PARAMETERS:
        return getTypeParameters();
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
      case XcorePackage.XCLASSIFIER__INSTANCE_TYPE_NAME:
        setInstanceTypeName((String)newValue);
        return;
      case XcorePackage.XCLASSIFIER__TYPE_PARAMETERS:
        getTypeParameters().clear();
        getTypeParameters().addAll((Collection<? extends XTypeParameter>)newValue);
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
      case XcorePackage.XCLASSIFIER__INSTANCE_TYPE_NAME:
        unsetInstanceTypeName();
        return;
      case XcorePackage.XCLASSIFIER__TYPE_PARAMETERS:
        getTypeParameters().clear();
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
      case XcorePackage.XCLASSIFIER__INSTANCE_TYPE_NAME:
        return isSetInstanceTypeName();
      case XcorePackage.XCLASSIFIER__PACKAGE:
        return getPackage() != null;
      case XcorePackage.XCLASSIFIER__TYPE_PARAMETERS:
        return typeParameters != null && !typeParameters.isEmpty();
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
    result.append(" (instanceTypeName: ");
    if (instanceTypeNameESet) result.append(instanceTypeName); else result.append("<unset>");
    result.append(')');
    return result.toString();
  }

} //XClassifierImpl
