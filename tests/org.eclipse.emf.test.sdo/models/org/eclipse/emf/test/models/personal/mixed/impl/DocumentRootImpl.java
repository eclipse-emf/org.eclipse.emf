/**
 * <copyright>
 * </copyright>
 *
 * $Id: DocumentRootImpl.java,v 1.1 2005/06/01 22:28:12 elena Exp $
 */
package org.eclipse.emf.test.models.personal.mixed.impl;

import commonj.sdo.Sequence;

import java.util.Collection;
import java.util.Map;

import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EMap;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.EcorePackage;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.EStringToStringMapEntryImpl;

import org.eclipse.emf.ecore.sdo.impl.EDataObjectImpl;

import org.eclipse.emf.ecore.sdo.util.BasicESequence;
import org.eclipse.emf.ecore.sdo.util.ESequence;

import org.eclipse.emf.ecore.util.BasicFeatureMap;
import org.eclipse.emf.ecore.util.EcoreEMap;
import org.eclipse.emf.ecore.util.FeatureMap;
import org.eclipse.emf.ecore.util.InternalEList;

import org.eclipse.emf.test.models.personal.mixed.DocumentRoot;
import org.eclipse.emf.test.models.personal.mixed.LinkType;
import org.eclipse.emf.test.models.personal.mixed.MixedPackage;
import org.eclipse.emf.test.models.personal.mixed.NameType;
import org.eclipse.emf.test.models.personal.mixed.PersonType;
import org.eclipse.emf.test.models.personal.mixed.PersonnelType;
import org.eclipse.emf.test.models.personal.mixed.UrlType;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Document Root</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.emf.test.models.personal.mixed.impl.DocumentRootImpl#getMixed <em>Mixed</em>}</li>
 *   <li>{@link org.eclipse.emf.test.models.personal.mixed.impl.DocumentRootImpl#getXMLNSPrefixMap <em>XMLNS Prefix Map</em>}</li>
 *   <li>{@link org.eclipse.emf.test.models.personal.mixed.impl.DocumentRootImpl#getXSISchemaLocation <em>XSI Schema Location</em>}</li>
 *   <li>{@link org.eclipse.emf.test.models.personal.mixed.impl.DocumentRootImpl#getEmail <em>Email</em>}</li>
 *   <li>{@link org.eclipse.emf.test.models.personal.mixed.impl.DocumentRootImpl#getFamily <em>Family</em>}</li>
 *   <li>{@link org.eclipse.emf.test.models.personal.mixed.impl.DocumentRootImpl#getGiven <em>Given</em>}</li>
 *   <li>{@link org.eclipse.emf.test.models.personal.mixed.impl.DocumentRootImpl#getLink <em>Link</em>}</li>
 *   <li>{@link org.eclipse.emf.test.models.personal.mixed.impl.DocumentRootImpl#getName <em>Name</em>}</li>
 *   <li>{@link org.eclipse.emf.test.models.personal.mixed.impl.DocumentRootImpl#getPerson <em>Person</em>}</li>
 *   <li>{@link org.eclipse.emf.test.models.personal.mixed.impl.DocumentRootImpl#getPersonnel <em>Personnel</em>}</li>
 *   <li>{@link org.eclipse.emf.test.models.personal.mixed.impl.DocumentRootImpl#getUrl <em>Url</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class DocumentRootImpl extends EDataObjectImpl implements DocumentRoot
{
  /**
   * The cached value of the '{@link #getMixed() <em>Mixed</em>}' attribute list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getMixed()
   * @generated
   * @ordered
   */
  protected ESequence mixed = null;

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
   * The default value of the '{@link #getEmail() <em>Email</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getEmail()
   * @generated
   * @ordered
   */
  protected static final String EMAIL_EDEFAULT = null;

  /**
   * The default value of the '{@link #getFamily() <em>Family</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getFamily()
   * @generated
   * @ordered
   */
  protected static final String FAMILY_EDEFAULT = null;

  /**
   * The default value of the '{@link #getGiven() <em>Given</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getGiven()
   * @generated
   * @ordered
   */
  protected static final String GIVEN_EDEFAULT = null;

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
    return MixedPackage.eINSTANCE.getDocumentRoot();
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public Sequence getMixed()
  {
    if (mixed == null)
    {
      mixed = new BasicESequence(new BasicFeatureMap(this, MixedPackage.DOCUMENT_ROOT__MIXED));
    }
    return mixed;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public Map getXMLNSPrefixMap()
  {
    if (xMLNSPrefixMap == null)
    {
      xMLNSPrefixMap = new EcoreEMap(EcorePackage.eINSTANCE.getEStringToStringMapEntry(), EStringToStringMapEntryImpl.class, this, MixedPackage.DOCUMENT_ROOT__XMLNS_PREFIX_MAP);
    }
    return xMLNSPrefixMap.map();
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public Map getXSISchemaLocation()
  {
    if (xSISchemaLocation == null)
    {
      xSISchemaLocation = new EcoreEMap(EcorePackage.eINSTANCE.getEStringToStringMapEntry(), EStringToStringMapEntryImpl.class, this, MixedPackage.DOCUMENT_ROOT__XSI_SCHEMA_LOCATION);
    }
    return xSISchemaLocation.map();
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public String getEmail()
  {
    return (String)((ESequence)getMixed()).featureMap().get(MixedPackage.eINSTANCE.getDocumentRoot_Email(), true);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setEmail(String newEmail)
  {
    ((FeatureMap.Internal)((ESequence)getMixed()).featureMap()).set(MixedPackage.eINSTANCE.getDocumentRoot_Email(), newEmail);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public String getFamily()
  {
    return (String)((ESequence)getMixed()).featureMap().get(MixedPackage.eINSTANCE.getDocumentRoot_Family(), true);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setFamily(String newFamily)
  {
    ((FeatureMap.Internal)((ESequence)getMixed()).featureMap()).set(MixedPackage.eINSTANCE.getDocumentRoot_Family(), newFamily);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public String getGiven()
  {
    return (String)((ESequence)getMixed()).featureMap().get(MixedPackage.eINSTANCE.getDocumentRoot_Given(), true);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setGiven(String newGiven)
  {
    ((FeatureMap.Internal)((ESequence)getMixed()).featureMap()).set(MixedPackage.eINSTANCE.getDocumentRoot_Given(), newGiven);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public LinkType getLink()
  {
    return (LinkType)((ESequence)getMixed()).featureMap().get(MixedPackage.eINSTANCE.getDocumentRoot_Link(), true);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetLink(LinkType newLink, NotificationChain msgs)
  {
    return ((FeatureMap.Internal)((ESequence)getMixed()).featureMap()).basicAdd(MixedPackage.eINSTANCE.getDocumentRoot_Link(), newLink, msgs);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setLink(LinkType newLink)
  {
    ((FeatureMap.Internal)((ESequence)getMixed()).featureMap()).set(MixedPackage.eINSTANCE.getDocumentRoot_Link(), newLink);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NameType getName()
  {
    return (NameType)((ESequence)getMixed()).featureMap().get(MixedPackage.eINSTANCE.getDocumentRoot_Name(), true);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetName(NameType newName, NotificationChain msgs)
  {
    return ((FeatureMap.Internal)((ESequence)getMixed()).featureMap()).basicAdd(MixedPackage.eINSTANCE.getDocumentRoot_Name(), newName, msgs);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setName(NameType newName)
  {
    ((FeatureMap.Internal)((ESequence)getMixed()).featureMap()).set(MixedPackage.eINSTANCE.getDocumentRoot_Name(), newName);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public PersonType getPerson()
  {
    return (PersonType)((ESequence)getMixed()).featureMap().get(MixedPackage.eINSTANCE.getDocumentRoot_Person(), true);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetPerson(PersonType newPerson, NotificationChain msgs)
  {
    return ((FeatureMap.Internal)((ESequence)getMixed()).featureMap()).basicAdd(MixedPackage.eINSTANCE.getDocumentRoot_Person(), newPerson, msgs);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setPerson(PersonType newPerson)
  {
    ((FeatureMap.Internal)((ESequence)getMixed()).featureMap()).set(MixedPackage.eINSTANCE.getDocumentRoot_Person(), newPerson);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public PersonnelType getPersonnel()
  {
    return (PersonnelType)((ESequence)getMixed()).featureMap().get(MixedPackage.eINSTANCE.getDocumentRoot_Personnel(), true);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetPersonnel(PersonnelType newPersonnel, NotificationChain msgs)
  {
    return ((FeatureMap.Internal)((ESequence)getMixed()).featureMap()).basicAdd(MixedPackage.eINSTANCE.getDocumentRoot_Personnel(), newPersonnel, msgs);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setPersonnel(PersonnelType newPersonnel)
  {
    ((FeatureMap.Internal)((ESequence)getMixed()).featureMap()).set(MixedPackage.eINSTANCE.getDocumentRoot_Personnel(), newPersonnel);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public UrlType getUrl()
  {
    return (UrlType)((ESequence)getMixed()).featureMap().get(MixedPackage.eINSTANCE.getDocumentRoot_Url(), true);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetUrl(UrlType newUrl, NotificationChain msgs)
  {
    return ((FeatureMap.Internal)((ESequence)getMixed()).featureMap()).basicAdd(MixedPackage.eINSTANCE.getDocumentRoot_Url(), newUrl, msgs);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setUrl(UrlType newUrl)
  {
    ((FeatureMap.Internal)((ESequence)getMixed()).featureMap()).set(MixedPackage.eINSTANCE.getDocumentRoot_Url(), newUrl);
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
        case MixedPackage.DOCUMENT_ROOT__MIXED:
        return ((InternalEList)((ESequence)getMixed()).featureMap()).basicRemove(otherEnd, msgs);
        case MixedPackage.DOCUMENT_ROOT__XMLNS_PREFIX_MAP:
          return ((InternalEList)((EMap.InternalMapView)getXMLNSPrefixMap()).eMap()).basicRemove(otherEnd, msgs);
        case MixedPackage.DOCUMENT_ROOT__XSI_SCHEMA_LOCATION:
          return ((InternalEList)((EMap.InternalMapView)getXSISchemaLocation()).eMap()).basicRemove(otherEnd, msgs);
        case MixedPackage.DOCUMENT_ROOT__LINK:
          return basicSetLink(null, msgs);
        case MixedPackage.DOCUMENT_ROOT__NAME:
          return basicSetName(null, msgs);
        case MixedPackage.DOCUMENT_ROOT__PERSON:
          return basicSetPerson(null, msgs);
        case MixedPackage.DOCUMENT_ROOT__PERSONNEL:
          return basicSetPersonnel(null, msgs);
        case MixedPackage.DOCUMENT_ROOT__URL:
          return basicSetUrl(null, msgs);
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
      case MixedPackage.DOCUMENT_ROOT__MIXED:
        return ((ESequence)getMixed()).featureMap();
      case MixedPackage.DOCUMENT_ROOT__XMLNS_PREFIX_MAP:
        return ((EMap.InternalMapView)getXMLNSPrefixMap()).eMap();
      case MixedPackage.DOCUMENT_ROOT__XSI_SCHEMA_LOCATION:
        return ((EMap.InternalMapView)getXSISchemaLocation()).eMap();
      case MixedPackage.DOCUMENT_ROOT__EMAIL:
        return getEmail();
      case MixedPackage.DOCUMENT_ROOT__FAMILY:
        return getFamily();
      case MixedPackage.DOCUMENT_ROOT__GIVEN:
        return getGiven();
      case MixedPackage.DOCUMENT_ROOT__LINK:
        return getLink();
      case MixedPackage.DOCUMENT_ROOT__NAME:
        return getName();
      case MixedPackage.DOCUMENT_ROOT__PERSON:
        return getPerson();
      case MixedPackage.DOCUMENT_ROOT__PERSONNEL:
        return getPersonnel();
      case MixedPackage.DOCUMENT_ROOT__URL:
        return getUrl();
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
      case MixedPackage.DOCUMENT_ROOT__MIXED:
        ((ESequence)getMixed()).featureMap().clear();
        ((ESequence)getMixed()).featureMap().addAll((Collection)newValue);
        return;
      case MixedPackage.DOCUMENT_ROOT__XMLNS_PREFIX_MAP:
        getXMLNSPrefixMap().clear();
        ((EMap.InternalMapView)getXMLNSPrefixMap()).eMap().addAll((Collection)newValue);
        return;
      case MixedPackage.DOCUMENT_ROOT__XSI_SCHEMA_LOCATION:
        getXSISchemaLocation().clear();
        ((EMap.InternalMapView)getXSISchemaLocation()).eMap().addAll((Collection)newValue);
        return;
      case MixedPackage.DOCUMENT_ROOT__EMAIL:
        setEmail((String)newValue);
        return;
      case MixedPackage.DOCUMENT_ROOT__FAMILY:
        setFamily((String)newValue);
        return;
      case MixedPackage.DOCUMENT_ROOT__GIVEN:
        setGiven((String)newValue);
        return;
      case MixedPackage.DOCUMENT_ROOT__LINK:
        setLink((LinkType)newValue);
        return;
      case MixedPackage.DOCUMENT_ROOT__NAME:
        setName((NameType)newValue);
        return;
      case MixedPackage.DOCUMENT_ROOT__PERSON:
        setPerson((PersonType)newValue);
        return;
      case MixedPackage.DOCUMENT_ROOT__PERSONNEL:
        setPersonnel((PersonnelType)newValue);
        return;
      case MixedPackage.DOCUMENT_ROOT__URL:
        setUrl((UrlType)newValue);
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
      case MixedPackage.DOCUMENT_ROOT__MIXED:
        ((ESequence)getMixed()).featureMap().clear();
        return;
      case MixedPackage.DOCUMENT_ROOT__XMLNS_PREFIX_MAP:
        getXMLNSPrefixMap().clear();
        return;
      case MixedPackage.DOCUMENT_ROOT__XSI_SCHEMA_LOCATION:
        getXSISchemaLocation().clear();
        return;
      case MixedPackage.DOCUMENT_ROOT__EMAIL:
        setEmail(EMAIL_EDEFAULT);
        return;
      case MixedPackage.DOCUMENT_ROOT__FAMILY:
        setFamily(FAMILY_EDEFAULT);
        return;
      case MixedPackage.DOCUMENT_ROOT__GIVEN:
        setGiven(GIVEN_EDEFAULT);
        return;
      case MixedPackage.DOCUMENT_ROOT__LINK:
        setLink((LinkType)null);
        return;
      case MixedPackage.DOCUMENT_ROOT__NAME:
        setName((NameType)null);
        return;
      case MixedPackage.DOCUMENT_ROOT__PERSON:
        setPerson((PersonType)null);
        return;
      case MixedPackage.DOCUMENT_ROOT__PERSONNEL:
        setPersonnel((PersonnelType)null);
        return;
      case MixedPackage.DOCUMENT_ROOT__URL:
        setUrl((UrlType)null);
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
      case MixedPackage.DOCUMENT_ROOT__MIXED:
        return mixed != null && !mixed.featureMap().isEmpty();
      case MixedPackage.DOCUMENT_ROOT__XMLNS_PREFIX_MAP:
        return xMLNSPrefixMap != null && !xMLNSPrefixMap.isEmpty();
      case MixedPackage.DOCUMENT_ROOT__XSI_SCHEMA_LOCATION:
        return xSISchemaLocation != null && !xSISchemaLocation.isEmpty();
      case MixedPackage.DOCUMENT_ROOT__EMAIL:
        return EMAIL_EDEFAULT == null ? getEmail() != null : !EMAIL_EDEFAULT.equals(getEmail());
      case MixedPackage.DOCUMENT_ROOT__FAMILY:
        return FAMILY_EDEFAULT == null ? getFamily() != null : !FAMILY_EDEFAULT.equals(getFamily());
      case MixedPackage.DOCUMENT_ROOT__GIVEN:
        return GIVEN_EDEFAULT == null ? getGiven() != null : !GIVEN_EDEFAULT.equals(getGiven());
      case MixedPackage.DOCUMENT_ROOT__LINK:
        return getLink() != null;
      case MixedPackage.DOCUMENT_ROOT__NAME:
        return getName() != null;
      case MixedPackage.DOCUMENT_ROOT__PERSON:
        return getPerson() != null;
      case MixedPackage.DOCUMENT_ROOT__PERSONNEL:
        return getPersonnel() != null;
      case MixedPackage.DOCUMENT_ROOT__URL:
        return getUrl() != null;
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
    result.append(')');
    return result.toString();
  }

} //DocumentRootImpl
