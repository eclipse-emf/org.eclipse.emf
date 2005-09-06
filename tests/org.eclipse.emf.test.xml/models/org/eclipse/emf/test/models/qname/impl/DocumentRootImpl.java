/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.eclipse.emf.test.models.qname.impl;

import java.util.Collection;
import java.util.List;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EMap;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.EcorePackage;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;
import org.eclipse.emf.ecore.impl.EStringToStringMapEntryImpl;

import org.eclipse.emf.ecore.util.BasicFeatureMap;
import org.eclipse.emf.ecore.util.EcoreEMap;
import org.eclipse.emf.ecore.util.FeatureMap;
import org.eclipse.emf.ecore.util.InternalEList;

import org.eclipse.emf.test.models.qname.DocumentRoot;
import org.eclipse.emf.test.models.qname.QnamePackage;
import org.eclipse.emf.test.models.qname.ResourceType;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Document Root</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.emf.test.models.qname.impl.DocumentRootImpl#getMixed <em>Mixed</em>}</li>
 *   <li>{@link org.eclipse.emf.test.models.qname.impl.DocumentRootImpl#getXMLNSPrefixMap <em>XMLNS Prefix Map</em>}</li>
 *   <li>{@link org.eclipse.emf.test.models.qname.impl.DocumentRootImpl#getXSISchemaLocation <em>XSI Schema Location</em>}</li>
 *   <li>{@link org.eclipse.emf.test.models.qname.impl.DocumentRootImpl#getAnyE <em>Any E</em>}</li>
 *   <li>{@link org.eclipse.emf.test.models.qname.impl.DocumentRootImpl#getAnyEU <em>Any EU</em>}</li>
 *   <li>{@link org.eclipse.emf.test.models.qname.impl.DocumentRootImpl#getResource <em>Resource</em>}</li>
 *   <li>{@link org.eclipse.emf.test.models.qname.impl.DocumentRootImpl#getAInt <em>AInt</em>}</li>
 *   <li>{@link org.eclipse.emf.test.models.qname.impl.DocumentRootImpl#getAQname <em>AQname</em>}</li>
 *   <li>{@link org.eclipse.emf.test.models.qname.impl.DocumentRootImpl#getAUnion <em>AUnion</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class DocumentRootImpl extends EObjectImpl implements DocumentRoot
{
  /**
   * The cached value of the '{@link #getMixed() <em>Mixed</em>}' attribute list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getMixed()
   * @generated
   * @ordered
   */
  protected FeatureMap mixed = null;

  /**
   * The cached value of the '{@link #getXMLNSPrefixMap() <em>XMLNS Prefix Map</em>}' map.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getXMLNSPrefixMap()
   * @generated
   * @ordered
   */
  protected EMap xMLNSPrefixMap = null;

  /**
   * The cached value of the '{@link #getXSISchemaLocation() <em>XSI Schema Location</em>}' map.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getXSISchemaLocation()
   * @generated
   * @ordered
   */
  protected EMap xSISchemaLocation = null;

  /**
   * The default value of the '{@link #getAInt() <em>AInt</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getAInt()
   * @generated
   * @ordered
   */
  protected static final int AINT_EDEFAULT = 0;

  /**
   * The cached value of the '{@link #getAInt() <em>AInt</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getAInt()
   * @generated
   * @ordered
   */
  protected int aInt = AINT_EDEFAULT;

  /**
   * This is true if the AInt attribute has been set.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  protected boolean aIntESet = false;

  /**
   * The default value of the '{@link #getAQname() <em>AQname</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getAQname()
   * @generated
   * @ordered
   */
  protected static final Object AQNAME_EDEFAULT = null;

  /**
   * The cached value of the '{@link #getAQname() <em>AQname</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getAQname()
   * @generated
   * @ordered
   */
  protected Object aQname = AQNAME_EDEFAULT;

  /**
   * The default value of the '{@link #getAUnion() <em>AUnion</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getAUnion()
   * @generated
   * @ordered
   */
  protected static final List AUNION_EDEFAULT = null;

  /**
   * The cached value of the '{@link #getAUnion() <em>AUnion</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getAUnion()
   * @generated
   * @ordered
   */
  protected List aUnion = AUNION_EDEFAULT;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected DocumentRootImpl()
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
    return QnamePackage.eINSTANCE.getDocumentRoot();
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public FeatureMap getMixed()
  {
    if (mixed == null)
    {
      mixed = new BasicFeatureMap(this, QnamePackage.DOCUMENT_ROOT__MIXED);
    }
    return mixed;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EMap getXMLNSPrefixMap()
  {
    if (xMLNSPrefixMap == null)
    {
      xMLNSPrefixMap = new EcoreEMap(EcorePackage.eINSTANCE.getEStringToStringMapEntry(), EStringToStringMapEntryImpl.class, this, QnamePackage.DOCUMENT_ROOT__XMLNS_PREFIX_MAP);
    }
    return xMLNSPrefixMap;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EMap getXSISchemaLocation()
  {
    if (xSISchemaLocation == null)
    {
      xSISchemaLocation = new EcoreEMap(EcorePackage.eINSTANCE.getEStringToStringMapEntry(), EStringToStringMapEntryImpl.class, this, QnamePackage.DOCUMENT_ROOT__XSI_SCHEMA_LOCATION);
    }
    return xSISchemaLocation;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public Object getAnyE()
  {
    return (Object)getMixed().get(QnamePackage.eINSTANCE.getDocumentRoot_AnyE(), true);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setAnyE(Object newAnyE)
  {
    ((FeatureMap.Internal)getMixed()).set(QnamePackage.eINSTANCE.getDocumentRoot_AnyE(), newAnyE);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public List getAnyEU()
  {
    return (List)getMixed().get(QnamePackage.eINSTANCE.getDocumentRoot_AnyEU(), true);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setAnyEU(List newAnyEU)
  {
    ((FeatureMap.Internal)getMixed()).set(QnamePackage.eINSTANCE.getDocumentRoot_AnyEU(), newAnyEU);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public ResourceType getResource()
  {
    return (ResourceType)getMixed().get(QnamePackage.eINSTANCE.getDocumentRoot_Resource(), true);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetResource(ResourceType newResource, NotificationChain msgs)
  {
    return ((FeatureMap.Internal)getMixed()).basicAdd(QnamePackage.eINSTANCE.getDocumentRoot_Resource(), newResource, null);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setResource(ResourceType newResource)
  {
    ((FeatureMap.Internal)getMixed()).set(QnamePackage.eINSTANCE.getDocumentRoot_Resource(), newResource);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public int getAInt()
  {
    return aInt;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setAInt(int newAInt)
  {
    int oldAInt = aInt;
    aInt = newAInt;
    boolean oldAIntESet = aIntESet;
    aIntESet = true;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, QnamePackage.DOCUMENT_ROOT__AINT, oldAInt, aInt, !oldAIntESet));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void unsetAInt()
  {
    int oldAInt = aInt;
    boolean oldAIntESet = aIntESet;
    aInt = AINT_EDEFAULT;
    aIntESet = false;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.UNSET, QnamePackage.DOCUMENT_ROOT__AINT, oldAInt, AINT_EDEFAULT, oldAIntESet));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public boolean isSetAInt()
  {
    return aIntESet;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public Object getAQname()
  {
    return aQname;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setAQname(Object newAQname)
  {
    Object oldAQname = aQname;
    aQname = newAQname;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, QnamePackage.DOCUMENT_ROOT__AQNAME, oldAQname, aQname));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public List getAUnion()
  {
    return aUnion;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setAUnion(List newAUnion)
  {
    List oldAUnion = aUnion;
    aUnion = newAUnion;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, QnamePackage.DOCUMENT_ROOT__AUNION, oldAUnion, aUnion));
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
        case QnamePackage.DOCUMENT_ROOT__MIXED:
          return ((InternalEList)getMixed()).basicRemove(otherEnd, msgs);
        case QnamePackage.DOCUMENT_ROOT__XMLNS_PREFIX_MAP:
          return ((InternalEList)getXMLNSPrefixMap()).basicRemove(otherEnd, msgs);
        case QnamePackage.DOCUMENT_ROOT__XSI_SCHEMA_LOCATION:
          return ((InternalEList)getXSISchemaLocation()).basicRemove(otherEnd, msgs);
        case QnamePackage.DOCUMENT_ROOT__RESOURCE:
          return basicSetResource(null, msgs);
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
      case QnamePackage.DOCUMENT_ROOT__MIXED:
        return getMixed();
      case QnamePackage.DOCUMENT_ROOT__XMLNS_PREFIX_MAP:
        return getXMLNSPrefixMap();
      case QnamePackage.DOCUMENT_ROOT__XSI_SCHEMA_LOCATION:
        return getXSISchemaLocation();
      case QnamePackage.DOCUMENT_ROOT__ANY_E:
        return getAnyE();
      case QnamePackage.DOCUMENT_ROOT__ANY_EU:
        return getAnyEU();
      case QnamePackage.DOCUMENT_ROOT__RESOURCE:
        return getResource();
      case QnamePackage.DOCUMENT_ROOT__AINT:
        return new Integer(getAInt());
      case QnamePackage.DOCUMENT_ROOT__AQNAME:
        return getAQname();
      case QnamePackage.DOCUMENT_ROOT__AUNION:
        return getAUnion();
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
      case QnamePackage.DOCUMENT_ROOT__MIXED:
        getMixed().clear();
        getMixed().addAll((Collection)newValue);
        return;
      case QnamePackage.DOCUMENT_ROOT__XMLNS_PREFIX_MAP:
        getXMLNSPrefixMap().clear();
        getXMLNSPrefixMap().addAll((Collection)newValue);
        return;
      case QnamePackage.DOCUMENT_ROOT__XSI_SCHEMA_LOCATION:
        getXSISchemaLocation().clear();
        getXSISchemaLocation().addAll((Collection)newValue);
        return;
      case QnamePackage.DOCUMENT_ROOT__ANY_E:
        setAnyE((Object)newValue);
        return;
      case QnamePackage.DOCUMENT_ROOT__ANY_EU:
        setAnyEU((List)newValue);
        return;
      case QnamePackage.DOCUMENT_ROOT__RESOURCE:
        setResource((ResourceType)newValue);
        return;
      case QnamePackage.DOCUMENT_ROOT__AINT:
        setAInt(((Integer)newValue).intValue());
        return;
      case QnamePackage.DOCUMENT_ROOT__AQNAME:
        setAQname((Object)newValue);
        return;
      case QnamePackage.DOCUMENT_ROOT__AUNION:
        setAUnion((List)newValue);
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
      case QnamePackage.DOCUMENT_ROOT__MIXED:
        getMixed().clear();
        return;
      case QnamePackage.DOCUMENT_ROOT__XMLNS_PREFIX_MAP:
        getXMLNSPrefixMap().clear();
        return;
      case QnamePackage.DOCUMENT_ROOT__XSI_SCHEMA_LOCATION:
        getXSISchemaLocation().clear();
        return;
      case QnamePackage.DOCUMENT_ROOT__ANY_E:
        setAnyE((Object)null);
        return;
      case QnamePackage.DOCUMENT_ROOT__ANY_EU:
        setAnyEU((List)null);
        return;
      case QnamePackage.DOCUMENT_ROOT__RESOURCE:
        setResource((ResourceType)null);
        return;
      case QnamePackage.DOCUMENT_ROOT__AINT:
        unsetAInt();
        return;
      case QnamePackage.DOCUMENT_ROOT__AQNAME:
        setAQname(AQNAME_EDEFAULT);
        return;
      case QnamePackage.DOCUMENT_ROOT__AUNION:
        setAUnion(AUNION_EDEFAULT);
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
      case QnamePackage.DOCUMENT_ROOT__MIXED:
        return mixed != null && !mixed.isEmpty();
      case QnamePackage.DOCUMENT_ROOT__XMLNS_PREFIX_MAP:
        return xMLNSPrefixMap != null && !xMLNSPrefixMap.isEmpty();
      case QnamePackage.DOCUMENT_ROOT__XSI_SCHEMA_LOCATION:
        return xSISchemaLocation != null && !xSISchemaLocation.isEmpty();
      case QnamePackage.DOCUMENT_ROOT__ANY_E:
        return getAnyE() != null;
      case QnamePackage.DOCUMENT_ROOT__ANY_EU:
        return getAnyEU() != null;
      case QnamePackage.DOCUMENT_ROOT__RESOURCE:
        return getResource() != null;
      case QnamePackage.DOCUMENT_ROOT__AINT:
        return isSetAInt();
      case QnamePackage.DOCUMENT_ROOT__AQNAME:
        return AQNAME_EDEFAULT == null ? aQname != null : !AQNAME_EDEFAULT.equals(aQname);
      case QnamePackage.DOCUMENT_ROOT__AUNION:
        return AUNION_EDEFAULT == null ? aUnion != null : !AUNION_EDEFAULT.equals(aUnion);
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
    result.append(" (mixed: ");
    result.append(mixed);
    result.append(", aInt: ");
    if (aIntESet) result.append(aInt); else result.append("<unset>");
    result.append(", aQname: ");
    result.append(aQname);
    result.append(", aUnion: ");
    result.append(aUnion);
    result.append(')');
    return result.toString();
  }

} //DocumentRootImpl
