/**
 * <copyright>
 * </copyright>
 *
 * $Id: CafeteriaImpl.java,v 1.1 2006/02/08 21:30:37 marcelop Exp $
 */
package lib.impl;

import lib.Cafeteria;
import lib.LibPackage;
import lib.Library;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;

import org.eclipse.emf.ecore.util.EcoreUtil;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Cafeteria</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link lib.impl.CafeteriaImpl#getName <em>Name</em>}</li>
 *   <li>{@link lib.impl.CafeteriaImpl#getLibrary <em>Library</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class CafeteriaImpl extends EObjectImpl implements Cafeteria
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
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected CafeteriaImpl()
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
    return LibPackage.Literals.CAFETERIA;
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
      eNotify(new ENotificationImpl(this, Notification.SET, LibPackage.CAFETERIA__NAME, oldName, name));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public Library getLibrary()
  {
    if (eContainerFeatureID != LibPackage.CAFETERIA__LIBRARY) return null;
    return (Library)eContainer();
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public Library basicGetLibrary()
  {
    if (eContainerFeatureID != LibPackage.CAFETERIA__LIBRARY) return null;
    return (Library)eInternalContainer();
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setLibrary(Library newLibrary)
  {
    if (newLibrary != eInternalContainer() || (eContainerFeatureID != LibPackage.CAFETERIA__LIBRARY && newLibrary != null))
    {
      if (EcoreUtil.isAncestor(this, newLibrary))
        throw new IllegalArgumentException("Recursive containment not allowed for " + toString());
      NotificationChain msgs = null;
      if (eInternalContainer() != null)
        msgs = eBasicRemoveFromContainer(msgs);
      if (newLibrary != null)
        msgs = ((InternalEObject)newLibrary).eInverseAdd(this, LibPackage.LIBRARY__CAFETERIA, Library.class, msgs);
      msgs = eBasicSetContainer((InternalEObject)newLibrary, LibPackage.CAFETERIA__LIBRARY, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, LibPackage.CAFETERIA__LIBRARY, newLibrary, newLibrary));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs)
  {
    switch (featureID)
    {
      case LibPackage.CAFETERIA__LIBRARY:
        if (eInternalContainer() != null)
          msgs = eBasicRemoveFromContainer(msgs);
        return eBasicSetContainer(otherEnd, LibPackage.CAFETERIA__LIBRARY, msgs);
    }
    return super.eInverseAdd(otherEnd, featureID, msgs);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs)
  {
    switch (featureID)
    {
      case LibPackage.CAFETERIA__LIBRARY:
        return eBasicSetContainer(null, LibPackage.CAFETERIA__LIBRARY, msgs);
    }
    return super.eInverseRemove(otherEnd, featureID, msgs);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain eBasicRemoveFromContainerFeature(NotificationChain msgs)
  {
    switch (eContainerFeatureID)
    {
      case LibPackage.CAFETERIA__LIBRARY:
        return eInternalContainer().eInverseRemove(this, LibPackage.LIBRARY__CAFETERIA, Library.class, msgs);
    }
    return super.eBasicRemoveFromContainerFeature(msgs);
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
      case LibPackage.CAFETERIA__NAME:
        return getName();
      case LibPackage.CAFETERIA__LIBRARY:
        if (resolve) return getLibrary();
        return basicGetLibrary();
    }
    return super.eGet(featureID, resolve, coreType);
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
      case LibPackage.CAFETERIA__NAME:
        setName((String)newValue);
        return;
      case LibPackage.CAFETERIA__LIBRARY:
        setLibrary((Library)newValue);
        return;
    }
    super.eSet(featureID, newValue);
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
      case LibPackage.CAFETERIA__NAME:
        setName(NAME_EDEFAULT);
        return;
      case LibPackage.CAFETERIA__LIBRARY:
        setLibrary((Library)null);
        return;
    }
    super.eUnset(featureID);
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
      case LibPackage.CAFETERIA__NAME:
        return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
      case LibPackage.CAFETERIA__LIBRARY:
        return basicGetLibrary() != null;
    }
    return super.eIsSet(featureID);
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
    result.append(')');
    return result.toString();
  }

} //CafeteriaImpl
