/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.eclipse.emf.ecore.xcore.impl;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

import org.eclipse.emf.ecore.xcore.XGenericType;
import org.eclipse.emf.ecore.xcore.XTypedElement;
import org.eclipse.emf.ecore.xcore.XcorePackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>XTyped Element</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.emf.ecore.xcore.impl.XTypedElementImpl#isUnordered <em>Unordered</em>}</li>
 *   <li>{@link org.eclipse.emf.ecore.xcore.impl.XTypedElementImpl#isUnique <em>Unique</em>}</li>
 *   <li>{@link org.eclipse.emf.ecore.xcore.impl.XTypedElementImpl#getType <em>Type</em>}</li>
 *   <li>{@link org.eclipse.emf.ecore.xcore.impl.XTypedElementImpl#getMultiplicity <em>Multiplicity</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public abstract class XTypedElementImpl extends XNamedElementImpl implements XTypedElement
{
  /**
   * The default value of the '{@link #isUnordered() <em>Unordered</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #isUnordered()
   * @generated
   * @ordered
   */
  protected static final boolean UNORDERED_EDEFAULT = false;

  /**
   * The cached value of the '{@link #isUnordered() <em>Unordered</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #isUnordered()
   * @generated
   * @ordered
   */
  protected boolean unordered = UNORDERED_EDEFAULT;

  /**
   * The default value of the '{@link #isUnique() <em>Unique</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #isUnique()
   * @generated
   * @ordered
   */
  protected static final boolean UNIQUE_EDEFAULT = false;

  /**
   * The cached value of the '{@link #isUnique() <em>Unique</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #isUnique()
   * @generated
   * @ordered
   */
  protected boolean unique = UNIQUE_EDEFAULT;

  /**
   * The cached value of the '{@link #getType() <em>Type</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getType()
   * @generated
   * @ordered
   */
  protected XGenericType type;

  /**
   * This is true if the Type containment reference has been set.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  protected boolean typeESet;

  /**
   * The default value of the '{@link #getMultiplicity() <em>Multiplicity</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getMultiplicity()
   * @generated
   * @ordered
   */
  protected static final int[] MULTIPLICITY_EDEFAULT = null;

  /**
   * The cached value of the '{@link #getMultiplicity() <em>Multiplicity</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getMultiplicity()
   * @generated
   * @ordered
   */
  protected int[] multiplicity = MULTIPLICITY_EDEFAULT;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected XTypedElementImpl()
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
    return XcorePackage.Literals.XTYPED_ELEMENT;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public boolean isUnordered()
  {
    return unordered;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setUnordered(boolean newUnordered)
  {
    boolean oldUnordered = unordered;
    unordered = newUnordered;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, XcorePackage.XTYPED_ELEMENT__UNORDERED, oldUnordered, unordered));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public boolean isUnique()
  {
    return unique;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setUnique(boolean newUnique)
  {
    boolean oldUnique = unique;
    unique = newUnique;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, XcorePackage.XTYPED_ELEMENT__UNIQUE, oldUnique, unique));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public XGenericType getType()
  {
    return type;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetType(XGenericType newType, NotificationChain msgs)
  {
    XGenericType oldType = type;
    type = newType;
    boolean oldTypeESet = typeESet;
    typeESet = true;
    if (eNotificationRequired())
    {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, XcorePackage.XTYPED_ELEMENT__TYPE, oldType, newType, !oldTypeESet);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setType(XGenericType newType)
  {
    if (newType != type)
    {
      NotificationChain msgs = null;
      if (type != null)
        msgs = ((InternalEObject)type).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - XcorePackage.XTYPED_ELEMENT__TYPE, null, msgs);
      if (newType != null)
        msgs = ((InternalEObject)newType).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - XcorePackage.XTYPED_ELEMENT__TYPE, null, msgs);
      msgs = basicSetType(newType, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else
    {
      boolean oldTypeESet = typeESet;
      typeESet = true;
      if (eNotificationRequired())
        eNotify(new ENotificationImpl(this, Notification.SET, XcorePackage.XTYPED_ELEMENT__TYPE, newType, newType, !oldTypeESet));
    }
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicUnsetType(NotificationChain msgs)
  {
    XGenericType oldType = type;
    type = null;
    boolean oldTypeESet = typeESet;
    typeESet = false;
    if (eNotificationRequired())
    {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.UNSET, XcorePackage.XTYPED_ELEMENT__TYPE, oldType, null, oldTypeESet);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void unsetType()
  {
    if (type != null)
    {
      NotificationChain msgs = null;
      msgs = ((InternalEObject)type).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - XcorePackage.XTYPED_ELEMENT__TYPE, null, msgs);
      msgs = basicUnsetType(msgs);
      if (msgs != null) msgs.dispatch();
    }
    else
    {
      boolean oldTypeESet = typeESet;
      typeESet = false;
      if (eNotificationRequired())
        eNotify(new ENotificationImpl(this, Notification.UNSET, XcorePackage.XTYPED_ELEMENT__TYPE, null, null, oldTypeESet));
    }
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public boolean isSetType()
  {
    return typeESet;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public int[] getMultiplicity()
  {
    return multiplicity;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setMultiplicity(int[] newMultiplicity)
  {
    int[] oldMultiplicity = multiplicity;
    multiplicity = newMultiplicity;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, XcorePackage.XTYPED_ELEMENT__MULTIPLICITY, oldMultiplicity, multiplicity));
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
      case XcorePackage.XTYPED_ELEMENT__TYPE:
        return basicUnsetType(msgs);
    }
    return super.eInverseRemove(otherEnd, featureID, msgs);
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
      case XcorePackage.XTYPED_ELEMENT__UNORDERED:
        return isUnordered();
      case XcorePackage.XTYPED_ELEMENT__UNIQUE:
        return isUnique();
      case XcorePackage.XTYPED_ELEMENT__TYPE:
        return getType();
      case XcorePackage.XTYPED_ELEMENT__MULTIPLICITY:
        return getMultiplicity();
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
      case XcorePackage.XTYPED_ELEMENT__UNORDERED:
        setUnordered((Boolean)newValue);
        return;
      case XcorePackage.XTYPED_ELEMENT__UNIQUE:
        setUnique((Boolean)newValue);
        return;
      case XcorePackage.XTYPED_ELEMENT__TYPE:
        setType((XGenericType)newValue);
        return;
      case XcorePackage.XTYPED_ELEMENT__MULTIPLICITY:
        setMultiplicity((int[])newValue);
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
      case XcorePackage.XTYPED_ELEMENT__UNORDERED:
        setUnordered(UNORDERED_EDEFAULT);
        return;
      case XcorePackage.XTYPED_ELEMENT__UNIQUE:
        setUnique(UNIQUE_EDEFAULT);
        return;
      case XcorePackage.XTYPED_ELEMENT__TYPE:
        unsetType();
        return;
      case XcorePackage.XTYPED_ELEMENT__MULTIPLICITY:
        setMultiplicity(MULTIPLICITY_EDEFAULT);
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
      case XcorePackage.XTYPED_ELEMENT__UNORDERED:
        return unordered != UNORDERED_EDEFAULT;
      case XcorePackage.XTYPED_ELEMENT__UNIQUE:
        return unique != UNIQUE_EDEFAULT;
      case XcorePackage.XTYPED_ELEMENT__TYPE:
        return isSetType();
      case XcorePackage.XTYPED_ELEMENT__MULTIPLICITY:
        return MULTIPLICITY_EDEFAULT == null ? multiplicity != null : !MULTIPLICITY_EDEFAULT.equals(multiplicity);
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
    result.append(" (unordered: ");
    result.append(unordered);
    result.append(", unique: ");
    result.append(unique);
    result.append(", multiplicity: ");
    result.append(multiplicity);
    result.append(')');
    return result.toString();
  }

} //XTypedElementImpl
