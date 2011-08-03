/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.eclipse.emf.ecore.xcore.impl;

import java.util.Collection;
import org.eclipse.emf.codegen.ecore.genmodel.GenFeature;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

import org.eclipse.emf.ecore.util.EObjectResolvingEList;
import org.eclipse.emf.ecore.xcore.XReference;
import org.eclipse.emf.ecore.xcore.XcorePackage;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>XReference</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.emf.ecore.xcore.impl.XReferenceImpl#isContainer <em>Container</em>}</li>
 *   <li>{@link org.eclipse.emf.ecore.xcore.impl.XReferenceImpl#isContainment <em>Containment</em>}</li>
 *   <li>{@link org.eclipse.emf.ecore.xcore.impl.XReferenceImpl#isResolveProxies <em>Resolve Proxies</em>}</li>
 *   <li>{@link org.eclipse.emf.ecore.xcore.impl.XReferenceImpl#isLocal <em>Local</em>}</li>
 *   <li>{@link org.eclipse.emf.ecore.xcore.impl.XReferenceImpl#getOpposite <em>Opposite</em>}</li>
 *   <li>{@link org.eclipse.emf.ecore.xcore.impl.XReferenceImpl#getKeys <em>Keys</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class XReferenceImpl extends XStructuralFeatureImpl implements XReference
{
  /**
   * The default value of the '{@link #isContainer() <em>Container</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #isContainer()
   * @generated
   * @ordered
   */
  protected static final boolean CONTAINER_EDEFAULT = false;

  /**
   * The cached value of the '{@link #isContainer() <em>Container</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #isContainer()
   * @generated
   * @ordered
   */
  protected boolean container = CONTAINER_EDEFAULT;

  /**
   * The default value of the '{@link #isContainment() <em>Containment</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #isContainment()
   * @generated
   * @ordered
   */
  protected static final boolean CONTAINMENT_EDEFAULT = false;

  /**
   * The cached value of the '{@link #isContainment() <em>Containment</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #isContainment()
   * @generated
   * @ordered
   */
  protected boolean containment = CONTAINMENT_EDEFAULT;

  /**
   * The default value of the '{@link #isResolveProxies() <em>Resolve Proxies</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #isResolveProxies()
   * @generated
   * @ordered
   */
  protected static final boolean RESOLVE_PROXIES_EDEFAULT = false;

  /**
   * The cached value of the '{@link #isResolveProxies() <em>Resolve Proxies</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #isResolveProxies()
   * @generated
   * @ordered
   */
  protected boolean resolveProxies = RESOLVE_PROXIES_EDEFAULT;

  /**
   * The default value of the '{@link #isLocal() <em>Local</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #isLocal()
   * @generated
   * @ordered
   */
  protected static final boolean LOCAL_EDEFAULT = false;

  /**
   * The cached value of the '{@link #isLocal() <em>Local</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #isLocal()
   * @generated
   * @ordered
   */
  protected boolean local = LOCAL_EDEFAULT;

  /**
   * The cached value of the '{@link #getOpposite() <em>Opposite</em>}' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getOpposite()
   * @generated
   * @ordered
   */
  protected GenFeature opposite;

  /**
   * The cached value of the '{@link #getKeys() <em>Keys</em>}' reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getKeys()
   * @generated
   * @ordered
   */
  protected EList<GenFeature> keys;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected XReferenceImpl()
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
    return XcorePackage.Literals.XREFERENCE;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public boolean isContainer()
  {
    return container;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setContainer(boolean newContainer)
  {
    boolean oldContainer = container;
    container = newContainer;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, XcorePackage.XREFERENCE__CONTAINER, oldContainer, container));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public boolean isContainment()
  {
    return containment;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setContainment(boolean newContainment)
  {
    boolean oldContainment = containment;
    containment = newContainment;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, XcorePackage.XREFERENCE__CONTAINMENT, oldContainment, containment));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public boolean isResolveProxies()
  {
    return resolveProxies;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setResolveProxies(boolean newResolveProxies)
  {
    boolean oldResolveProxies = resolveProxies;
    resolveProxies = newResolveProxies;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, XcorePackage.XREFERENCE__RESOLVE_PROXIES, oldResolveProxies, resolveProxies));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public boolean isLocal()
  {
    return local;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setLocal(boolean newLocal)
  {
    boolean oldLocal = local;
    local = newLocal;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, XcorePackage.XREFERENCE__LOCAL, oldLocal, local));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public GenFeature getOpposite()
  {
    if (opposite != null && opposite.eIsProxy())
    {
      InternalEObject oldOpposite = (InternalEObject)opposite;
      opposite = (GenFeature)eResolveProxy(oldOpposite);
      if (opposite != oldOpposite)
      {
        if (eNotificationRequired())
          eNotify(new ENotificationImpl(this, Notification.RESOLVE, XcorePackage.XREFERENCE__OPPOSITE, oldOpposite, opposite));
      }
    }
    return opposite;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public GenFeature basicGetOpposite()
  {
    return opposite;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setOpposite(GenFeature newOpposite)
  {
    GenFeature oldOpposite = opposite;
    opposite = newOpposite;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, XcorePackage.XREFERENCE__OPPOSITE, oldOpposite, opposite));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EList<GenFeature> getKeys()
  {
    if (keys == null)
    {
      keys = new EObjectResolvingEList<GenFeature>(GenFeature.class, this, XcorePackage.XREFERENCE__KEYS);
    }
    return keys;
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
      case XcorePackage.XREFERENCE__CONTAINER:
        return isContainer();
      case XcorePackage.XREFERENCE__CONTAINMENT:
        return isContainment();
      case XcorePackage.XREFERENCE__RESOLVE_PROXIES:
        return isResolveProxies();
      case XcorePackage.XREFERENCE__LOCAL:
        return isLocal();
      case XcorePackage.XREFERENCE__OPPOSITE:
        if (resolve) return getOpposite();
        return basicGetOpposite();
      case XcorePackage.XREFERENCE__KEYS:
        return getKeys();
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
      case XcorePackage.XREFERENCE__CONTAINER:
        setContainer((Boolean)newValue);
        return;
      case XcorePackage.XREFERENCE__CONTAINMENT:
        setContainment((Boolean)newValue);
        return;
      case XcorePackage.XREFERENCE__RESOLVE_PROXIES:
        setResolveProxies((Boolean)newValue);
        return;
      case XcorePackage.XREFERENCE__LOCAL:
        setLocal((Boolean)newValue);
        return;
      case XcorePackage.XREFERENCE__OPPOSITE:
        setOpposite((GenFeature)newValue);
        return;
      case XcorePackage.XREFERENCE__KEYS:
        getKeys().clear();
        getKeys().addAll((Collection<? extends GenFeature>)newValue);
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
      case XcorePackage.XREFERENCE__CONTAINER:
        setContainer(CONTAINER_EDEFAULT);
        return;
      case XcorePackage.XREFERENCE__CONTAINMENT:
        setContainment(CONTAINMENT_EDEFAULT);
        return;
      case XcorePackage.XREFERENCE__RESOLVE_PROXIES:
        setResolveProxies(RESOLVE_PROXIES_EDEFAULT);
        return;
      case XcorePackage.XREFERENCE__LOCAL:
        setLocal(LOCAL_EDEFAULT);
        return;
      case XcorePackage.XREFERENCE__OPPOSITE:
        setOpposite((GenFeature)null);
        return;
      case XcorePackage.XREFERENCE__KEYS:
        getKeys().clear();
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
      case XcorePackage.XREFERENCE__CONTAINER:
        return container != CONTAINER_EDEFAULT;
      case XcorePackage.XREFERENCE__CONTAINMENT:
        return containment != CONTAINMENT_EDEFAULT;
      case XcorePackage.XREFERENCE__RESOLVE_PROXIES:
        return resolveProxies != RESOLVE_PROXIES_EDEFAULT;
      case XcorePackage.XREFERENCE__LOCAL:
        return local != LOCAL_EDEFAULT;
      case XcorePackage.XREFERENCE__OPPOSITE:
        return opposite != null;
      case XcorePackage.XREFERENCE__KEYS:
        return keys != null && !keys.isEmpty();
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
    result.append(" (container: ");
    result.append(container);
    result.append(", containment: ");
    result.append(containment);
    result.append(", resolveProxies: ");
    result.append(resolveProxies);
    result.append(", local: ");
    result.append(local);
    result.append(')');
    return result.toString();
  }

} //XReferenceImpl
