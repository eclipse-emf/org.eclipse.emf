/**
 * <copyright>
 *
 * Copyright (c) 2002-2004 IBM Corporation and others.
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
 * $Id: EPackageImpl.java,v 1.25 2006/01/27 20:41:35 emerks Exp $
 */
package org.eclipse.emf.ecore.impl;


import java.lang.reflect.Method;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.EMap;
import org.eclipse.emf.common.util.Enumerator;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EAnnotation;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EEnum;
import org.eclipse.emf.ecore.EEnumLiteral;
import org.eclipse.emf.ecore.EFactory;
import org.eclipse.emf.ecore.ENamedElement;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EOperation;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EParameter;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.EcoreFactory;
import org.eclipse.emf.ecore.EcorePackage;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.impl.ResourceImpl;
import org.eclipse.emf.ecore.util.BasicExtendedMetaData;
import org.eclipse.emf.ecore.util.ExtendedMetaData;
import org.eclipse.emf.ecore.util.EObjectContainmentWithInverseEList;
import org.eclipse.emf.ecore.util.InternalEList;


/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>EPackage</b></em>'.
 * @extends BasicExtendedMetaData.EPackageExtendedMetaData.Holder
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.emf.ecore.impl.EPackageImpl#getNsURI <em>Ns URI</em>}</li>
 *   <li>{@link org.eclipse.emf.ecore.impl.EPackageImpl#getNsPrefix <em>Ns Prefix</em>}</li>
 *   <li>{@link org.eclipse.emf.ecore.impl.EPackageImpl#getEFactoryInstance <em>EFactory Instance</em>}</li>
 *   <li>{@link org.eclipse.emf.ecore.impl.EPackageImpl#getEClassifiers <em>EClassifiers</em>}</li>
 *   <li>{@link org.eclipse.emf.ecore.impl.EPackageImpl#getESubpackages <em>ESubpackages</em>}</li>
 *   <li>{@link org.eclipse.emf.ecore.impl.EPackageImpl#getESuperPackage <em>ESuper Package</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class EPackageImpl extends ENamedElementImpl implements EPackage, BasicExtendedMetaData.EPackageExtendedMetaData.Holder
{
  /**
   * The default value of the '{@link #getNsURI() <em>Ns URI</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getNsURI()
   * @generated
   * @ordered
   */
  protected static final String NS_URI_EDEFAULT = null;

  /**
   * The Ecore factory.
   */
  protected EcoreFactory ecoreFactory = null;

  /**
   * The Ecore factory.
   */
  protected EcorePackage ecorePackage = null;

  /**
   * The map from name to 
   */
  protected Map eNameToEClassifierMap; 

  /**
   * <!-- begin-user-doc -->
   * Creates an instance.
   * <!-- end-user-doc -->
   * @generated NOT
   */
  protected EPackageImpl()
  {
    super();

    setEFactoryInstance(new EFactoryImpl());

    ecorePackage = EcorePackage.eINSTANCE;
    ecoreFactory = EcoreFactory.eINSTANCE;
  }

  /**
   * Creates an instance with a factory.
   * @param eFactory the factory of the new package.
   */
  protected EPackageImpl(EFactory eFactory)
  {
    super();

    setEFactoryInstance(eFactory);

    ecorePackage = EcorePackage.eINSTANCE;
    ecoreFactory = EcoreFactory.eINSTANCE;
  }

  /**
   * Creates a {@link org.eclipse.emf.ecore.EPackage.Registry#INSTANCE registered} instance that has a default factory.
   * @param packageURI the registered {@link #getNsURI namespace URI} of the new package.
   */
  protected EPackageImpl(String packageURI)
  {
    this(packageURI, new EFactoryImpl());
  }

  /**
   * Creates a {@link org.eclipse.emf.ecore.EPackage.Registry#INSTANCE registered} instance with a factory.
   * @param packageURI the registered {@link #getNsURI namespace URI} of the new package.
   * @param factory the factory of the new package.
   */
  protected EPackageImpl(String packageURI, EFactory factory)
  {
    super();

    Registry.INSTANCE.put(packageURI, this);

    setEFactoryInstance(factory);

    if (factory == EcoreFactory.eINSTANCE)
    {
      ecorePackage = (EcorePackage)this;
      ecoreFactory = (EcoreFactory)factory;
    }
    else
    {
      ecorePackage = EcorePackage.eINSTANCE;
      ecoreFactory = EcoreFactory.eINSTANCE;
    }
  }

  public void freeze()
  {
    super.freeze();
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected EClass eStaticClass()
  {
    return EcorePackage.Literals.EPACKAGE;
  }

  /**
   * @generated modifiable
   */
  public void setNamespaceURI(String nsURI)
  {
  }

  /**
   * The cached value of the '{@link #getNsURI() <em>Ns URI</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getNsURI()
   * @generated
   * @ordered
   */
  protected String nsURI = NS_URI_EDEFAULT;

  /**
   * The default value of the '{@link #getNsPrefix() <em>Ns Prefix</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getNsPrefix()
   * @generated
   * @ordered
   */
  protected static final String NS_PREFIX_EDEFAULT = null;

  /**
   * The cached value of the '{@link #getNsPrefix() <em>Ns Prefix</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getNsPrefix()
   * @generated
   * @ordered
   */
  protected String nsPrefix = NS_PREFIX_EDEFAULT;

  /**
   * The cached value of the '{@link #getEFactoryInstance() <em>EFactory Instance</em>}' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getEFactoryInstance()
   * @generated
   * @ordered
   */
  protected EFactory eFactoryInstance = null;

  /**
   * The cached value of the '{@link #getEClassifiers() <em>EClassifiers</em>}' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getEClassifiers()
   * @generated
   * @ordered
   */
  protected EList eClassifiers = null;

  /**
   * The cached value of the '{@link #getESubpackages() <em>ESubpackages</em>}' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getESubpackages()
   * @generated
   * @ordered
   */
  protected EList eSubpackages = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public String getNsURI()
  {
    return nsURI;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setNsURI(String newNsURI)
  {
    String oldNsURI = nsURI;
    nsURI = newNsURI;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, EcorePackage.EPACKAGE__NS_URI, oldNsURI, nsURI));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public String getNsPrefix()
  {
    return nsPrefix;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setNsPrefix(String newNsPrefix)
  {
    String oldNsPrefix = nsPrefix;
    nsPrefix = newNsPrefix;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, EcorePackage.EPACKAGE__NS_PREFIX, oldNsPrefix, nsPrefix));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EFactory getEFactoryInstance()
  {
    return eFactoryInstance;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setEFactoryInstance(EFactory newEFactoryInstance)
  {
    if (newEFactoryInstance != eFactoryInstance)
    {
      NotificationChain msgs = null;
      if (eFactoryInstance != null)
        msgs = ((InternalEObject)eFactoryInstance).eInverseRemove(this, EcorePackage.EFACTORY__EPACKAGE, EFactory.class, msgs);
      if (newEFactoryInstance != null)
        msgs = ((InternalEObject)newEFactoryInstance).eInverseAdd(this, EcorePackage.EFACTORY__EPACKAGE, EFactory.class, msgs);
      msgs = basicSetEFactoryInstance(newEFactoryInstance, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, EcorePackage.EPACKAGE__EFACTORY_INSTANCE, newEFactoryInstance, newEFactoryInstance));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetEFactoryInstance(EFactory newEFactoryInstance, NotificationChain msgs)
  {
    EFactory oldEFactoryInstance = eFactoryInstance;
    eFactoryInstance = newEFactoryInstance;
    if (eNotificationRequired())
    {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, EcorePackage.EPACKAGE__EFACTORY_INSTANCE, oldEFactoryInstance, newEFactoryInstance);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated NOT
   */
  public EList getEClassifiers()
  {
    if (eClassifiers == null)
    {
      eClassifiers = 
        new EObjectContainmentWithInverseEList.Resolving
          (EClassifier.class, this, EcorePackage.EPACKAGE__ECLASSIFIERS, EcorePackage.ECLASSIFIER__EPACKAGE)
        {
          protected void didChange()
          {
            eNameToEClassifierMap = null;
          }
        };
    }
    return eClassifiers;
  }

  /**
   * @generated modifiable
   */
  public EClassifier getEClassifier(String name)
  {
    if (eNameToEClassifierMap == null)
    {
      List eClassifiers = getEClassifiers();
      Map result = new HashMap(eClassifiers.size());
      for (Iterator i = eClassifiers.iterator(); i.hasNext(); )
      {
        EClassifier eClassifier = (EClassifier)i.next();
        result.put(eClassifier.getName(), eClassifier);
      }
      eNameToEClassifierMap = result;
    }

    return (EClassifier)eNameToEClassifierMap.get(name);
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
      case EcorePackage.EPACKAGE__EANNOTATIONS:
        return ((InternalEList)getEAnnotations()).basicAdd(otherEnd, msgs);
      case EcorePackage.EPACKAGE__EFACTORY_INSTANCE:
        if (eFactoryInstance != null)
          msgs = ((InternalEObject)eFactoryInstance).eInverseRemove(this, EcorePackage.EFACTORY__EPACKAGE, EFactory.class, msgs);
        return basicSetEFactoryInstance((EFactory)otherEnd, msgs);
      case EcorePackage.EPACKAGE__ECLASSIFIERS:
        return ((InternalEList)getEClassifiers()).basicAdd(otherEnd, msgs);
      case EcorePackage.EPACKAGE__ESUBPACKAGES:
        return ((InternalEList)getESubpackages()).basicAdd(otherEnd, msgs);
      case EcorePackage.EPACKAGE__ESUPER_PACKAGE:
        if (eInternalContainer() != null)
          msgs = eBasicRemoveFromContainer(msgs);
        return eBasicSetContainer(otherEnd, EcorePackage.EPACKAGE__ESUPER_PACKAGE, msgs);
    }
    return eDynamicInverseAdd(otherEnd, featureID, msgs);
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
      case EcorePackage.EPACKAGE__EANNOTATIONS:
        return ((InternalEList)getEAnnotations()).basicRemove(otherEnd, msgs);
      case EcorePackage.EPACKAGE__EFACTORY_INSTANCE:
        return basicSetEFactoryInstance(null, msgs);
      case EcorePackage.EPACKAGE__ECLASSIFIERS:
        return ((InternalEList)getEClassifiers()).basicRemove(otherEnd, msgs);
      case EcorePackage.EPACKAGE__ESUBPACKAGES:
        return ((InternalEList)getESubpackages()).basicRemove(otherEnd, msgs);
      case EcorePackage.EPACKAGE__ESUPER_PACKAGE:
        return eBasicSetContainer(null, EcorePackage.EPACKAGE__ESUPER_PACKAGE, msgs);
    }
    return eDynamicInverseRemove(otherEnd, featureID, msgs);
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
      case EcorePackage.EPACKAGE__ESUPER_PACKAGE:
        return eInternalContainer().eInverseRemove(this, EcorePackage.EPACKAGE__ESUBPACKAGES, EPackage.class, msgs);
    }
    return eDynamicBasicRemoveFromContainer(msgs);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EList getESubpackages()
  {
    if (eSubpackages == null)
    {
      eSubpackages = new EObjectContainmentWithInverseEList.Resolving(EPackage.class, this, EcorePackage.EPACKAGE__ESUBPACKAGES, EcorePackage.EPACKAGE__ESUPER_PACKAGE);
    }
    return eSubpackages;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated modifiable
   */
  public EPackage getESuperPackage()
  {
    return (eContainerFeatureID == EcorePackage.EPACKAGE__ESUPER_PACKAGE) ? (EPackage)eContainer : null;
  }


  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EPackage basicGetESuperPackage()
  {
    if (eContainerFeatureID != EcorePackage.EPACKAGE__ESUPER_PACKAGE) return null;
    return (EPackage)eInternalContainer();
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
      case EcorePackage.EPACKAGE__EANNOTATIONS:
        return getEAnnotations();
      case EcorePackage.EPACKAGE__NAME:
        return getName();
      case EcorePackage.EPACKAGE__NS_URI:
        return getNsURI();
      case EcorePackage.EPACKAGE__NS_PREFIX:
        return getNsPrefix();
      case EcorePackage.EPACKAGE__EFACTORY_INSTANCE:
        return getEFactoryInstance();
      case EcorePackage.EPACKAGE__ECLASSIFIERS:
        return getEClassifiers();
      case EcorePackage.EPACKAGE__ESUBPACKAGES:
        return getESubpackages();
      case EcorePackage.EPACKAGE__ESUPER_PACKAGE:
        if (resolve) return getESuperPackage();
        return basicGetESuperPackage();
    }
    return eDynamicGet(featureID, resolve, coreType);
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
      case EcorePackage.EPACKAGE__EANNOTATIONS:
        getEAnnotations().clear();
        getEAnnotations().addAll((Collection)newValue);
        return;
      case EcorePackage.EPACKAGE__NAME:
        setName((String)newValue);
        return;
      case EcorePackage.EPACKAGE__NS_URI:
        setNsURI((String)newValue);
        return;
      case EcorePackage.EPACKAGE__NS_PREFIX:
        setNsPrefix((String)newValue);
        return;
      case EcorePackage.EPACKAGE__EFACTORY_INSTANCE:
        setEFactoryInstance((EFactory)newValue);
        return;
      case EcorePackage.EPACKAGE__ECLASSIFIERS:
        getEClassifiers().clear();
        getEClassifiers().addAll((Collection)newValue);
        return;
      case EcorePackage.EPACKAGE__ESUBPACKAGES:
        getESubpackages().clear();
        getESubpackages().addAll((Collection)newValue);
        return;
    }
    eDynamicSet(featureID, newValue);
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
      case EcorePackage.EPACKAGE__EANNOTATIONS:
        getEAnnotations().clear();
        return;
      case EcorePackage.EPACKAGE__NAME:
        setName(NAME_EDEFAULT);
        return;
      case EcorePackage.EPACKAGE__NS_URI:
        setNsURI(NS_URI_EDEFAULT);
        return;
      case EcorePackage.EPACKAGE__NS_PREFIX:
        setNsPrefix(NS_PREFIX_EDEFAULT);
        return;
      case EcorePackage.EPACKAGE__EFACTORY_INSTANCE:
        setEFactoryInstance((EFactory)null);
        return;
      case EcorePackage.EPACKAGE__ECLASSIFIERS:
        getEClassifiers().clear();
        return;
      case EcorePackage.EPACKAGE__ESUBPACKAGES:
        getESubpackages().clear();
        return;
    }
    eDynamicUnset(featureID);
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
      case EcorePackage.EPACKAGE__EANNOTATIONS:
        return eAnnotations != null && !eAnnotations.isEmpty();
      case EcorePackage.EPACKAGE__NAME:
        return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
      case EcorePackage.EPACKAGE__NS_URI:
        return NS_URI_EDEFAULT == null ? nsURI != null : !NS_URI_EDEFAULT.equals(nsURI);
      case EcorePackage.EPACKAGE__NS_PREFIX:
        return NS_PREFIX_EDEFAULT == null ? nsPrefix != null : !NS_PREFIX_EDEFAULT.equals(nsPrefix);
      case EcorePackage.EPACKAGE__EFACTORY_INSTANCE:
        return eFactoryInstance != null;
      case EcorePackage.EPACKAGE__ECLASSIFIERS:
        return eClassifiers != null && !eClassifiers.isEmpty();
      case EcorePackage.EPACKAGE__ESUBPACKAGES:
        return eSubpackages != null && !eSubpackages.isEmpty();
      case EcorePackage.EPACKAGE__ESUPER_PACKAGE:
        return basicGetESuperPackage() != null;
    }
    return eDynamicIsSet(featureID);
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
    result.append(" (nsURI: ");
    result.append(nsURI);
    result.append(", nsPrefix: ");
    result.append(nsPrefix);
    result.append(')');
    return result.toString();
  }

  protected Resource createResource(String uri)
  {
    Resource resource = eResource();
    if (resource == null) 
    {
      URI actualURI = URI.createURI(uri);
      resource =  new ResourceImpl(actualURI);
      resource.getContents().add(this);
    }
    return resource;
  }

  protected EClass createEClass(int id)
  {
    EClassImpl c = (EClassImpl)ecoreFactory.createEClass();
    c.setClassifierID(id);
    getEClassifiers().add(c);
    return c;
  }

  protected EEnum createEEnum(int id)
  {
    EEnumImpl e = (EEnumImpl)ecoreFactory.createEEnum();
    e.setClassifierID(id);
    getEClassifiers().add(e);
    return e;
  }

  protected EDataType createEDataType(int id)
  {
    EDataTypeImpl d = (EDataTypeImpl)ecoreFactory.createEDataType();
    d.setClassifierID(id);
    getEClassifiers().add(d);
    return d;
  }

  protected void createEAttribute(EClass owner, int id)
  {
    EAttributeImpl a = (EAttributeImpl)ecoreFactory.createEAttribute();
    a.setFeatureID(id);
    owner.getEStructuralFeatures().add(a);
  }

  protected void createEReference(EClass owner, int id)
  {
    EReferenceImpl r = (EReferenceImpl)ecoreFactory.createEReference();
    r.setFeatureID(id);
    owner.getEStructuralFeatures().add(r);
  }

  final static protected boolean IS_ABSTRACT = true;
  final static protected boolean IS_INTERFACE = true;
  final static protected boolean IS_GENERATED_INSTANCE_CLASS = true;

  protected EClass initEClass(EClass c, Class instanceClass, String name, boolean isAbstract, boolean isInterface)
  {
    initEClassifier(c, ecorePackage.getEClass(), instanceClass, name);
    c.setAbstract(isAbstract);
    c.setInterface(isInterface);
    return c;
  }

  protected EClass initEClass(EClass c, Class instanceClass, String name, boolean isAbstract, boolean isInterface, boolean isGenerated)
  {
    initEClassifier(c, ecorePackage.getEClass(), instanceClass, name, isGenerated);
    c.setAbstract(isAbstract);
    c.setInterface(isInterface);
    return c;
  }

  protected EEnum initEEnum(EEnum e, Class instanceClass, String name)
  {
    initEClassifier(e, ecorePackage.getEEnum(), instanceClass, name, true);
    return e;
  }

  final static protected boolean IS_SERIALIZABLE = true;

  protected EDataType initEDataType(EDataType d, Class instanceClass, String name, boolean isSerializable)
  {
    initEClassifier(d, ecorePackage.getEDataType(), instanceClass, name, false);
    d.setSerializable(isSerializable);
    return d;
  }

  protected EDataType initEDataType(EDataType d, Class instanceClass, String name, boolean isSerializable, boolean isGenerated)
  {
    initEClassifier(d, ecorePackage.getEDataType(), instanceClass, name, isGenerated);
    d.setSerializable(isSerializable);
    return d;
  }

  private void initEClassifier(EClassifier o, EClass metaObject, Class instanceClass, String name)
  {
    o.setName(name);
    if (instanceClass != null)
    {
      o.setInstanceClass(instanceClass);
    }
  }

  private void initEClassifier(EClassifier o, EClass metaObject, Class instanceClass, String name, boolean isGenerated)
  {
    o.setName(name);
    if (instanceClass != null)
    {
      o.setInstanceClass(instanceClass);
    }
    if (isGenerated)
    {
      setGeneratedClassName(o);
    }
  }

  protected void setGeneratedClassName(EClassifier eClassifier)
  {
    ((EClassifierImpl)eClassifier).setGeneratedInstanceClass(true);
  }

  protected static final boolean IS_DERIVED = true;
  protected static final boolean IS_TRANSIENT = true;
  protected static final boolean IS_VOLATILE = true;
  protected static final boolean IS_CHANGEABLE = true;
  protected static final boolean IS_UNSETTABLE = true;
  protected static final boolean IS_UNIQUE = true;
  protected static final boolean IS_ID = true;
  protected static final boolean IS_ORDERED = true;

  /**
   * @deprecated
   */
  protected EAttribute initEAttribute
    (EAttribute a, 
     EClassifier type,
     String name, 
     String defaultValue,
     int lowerBound, 
     int upperBound, 
     boolean isTransient, 
     boolean isVolatile, 
     boolean isChangeable, 
     boolean isUnsettable)
  {
    return 
      initEAttribute
        (a, type, name, defaultValue, lowerBound, upperBound, isTransient, isVolatile, isChangeable, isUnsettable, false, true);
  }

  /**
   * @deprecated
   */
  protected EAttribute initEAttribute
    (EAttribute a, 
     EClassifier type,
     String name, 
     String defaultValue,
     int lowerBound, 
     int upperBound, 
     boolean isTransient, 
     boolean isVolatile, 
     boolean isChangeable, 
     boolean isUnsettable,
     boolean isID)
  {
    return 
      initEAttribute
        (a, type, name, defaultValue, lowerBound, upperBound, isTransient, isVolatile, isChangeable, isUnsettable, isID, true);
  }

  /**
   * @deprecated
   */
  protected EAttribute initEAttribute
    (EAttribute a, 
     EClassifier type,
     String name, 
     String defaultValue,
     int lowerBound, 
     int upperBound, 
     boolean isTransient, 
     boolean isVolatile, 
     boolean isChangeable, 
     boolean isUnsettable,
     boolean isID,
     boolean isUnique)
  {
    return 
      initEAttribute
        (a, type, name, defaultValue, lowerBound, upperBound, isTransient, isVolatile, isChangeable, isUnsettable, isID, isUnique, false);
  }

  protected EAttribute initEAttribute
    (EAttribute a, 
     EClassifier type,
     String name, 
     String defaultValue,
     int lowerBound, 
     int upperBound, 
     boolean isTransient, 
     boolean isVolatile, 
     boolean isChangeable, 
     boolean isUnsettable,
     boolean isID,
     boolean isUnique,
     boolean isDerived)
  {
    return 
      initEAttribute
        (a, 
         type, 
         name, 
         defaultValue, 
         lowerBound, 
         upperBound, 
         isTransient, 
         isVolatile, 
         isChangeable, 
         isUnsettable, 
         isID, 
         isUnique, 
         isDerived, 
         true);
  }

  protected EAttribute initEAttribute
    (EAttribute a, 
     EClassifier type,
     String name, 
     String defaultValue,
     int lowerBound, 
     int upperBound, 
     boolean isTransient, 
     boolean isVolatile, 
     boolean isChangeable, 
     boolean isUnsettable,
     boolean isID,
     boolean isUnique,
     boolean isDerived,
     boolean isOrdered)
  {
    initEAttribute
      (a, 
       type, 
       name, 
       defaultValue, 
       lowerBound, 
       upperBound, 
       ((EClassifier)a.eContainer()).getInstanceClass(),
       isTransient, 
       isVolatile, 
       isChangeable, 
       isUnsettable, 
       isID,
       isUnique, 
       isDerived, 
       isOrdered);
    return a;
  }

  protected EAttribute initEAttribute
    (EAttribute a, 
     EClassifier type,
     String name, 
     String defaultValue,
     int lowerBound, 
     int upperBound, 
     Class containerClass,
     boolean isTransient, 
     boolean isVolatile, 
     boolean isChangeable, 
     boolean isUnsettable,
     boolean isID,
     boolean isUnique,
     boolean isDerived,
     boolean isOrdered)
  {
    initEStructuralFeature
      (a, 
       type, 
       name, 
       defaultValue, 
       lowerBound, 
       upperBound, 
       containerClass,
       isTransient, 
       isVolatile, 
       isChangeable, 
       isUnsettable, 
       isUnique, 
       isDerived, 
       isOrdered);
    a.setID(isID);
    return a;
  }

  final static protected boolean IS_COMPOSITE = true;
  final static protected boolean IS_RESOLVE_PROXIES = true;
  final static protected boolean IS_RESOLVABLE = true;

  /**
   * @deprecated
   */
  protected EReference initEReference
    (EReference r, 
     EClassifier type, 
     EReference otherEnd,
     String name, 
     String defaultValue,
     int lowerBound, 
     int upperBound, 
     boolean isTransient, 
     boolean isVolatile, 
     boolean isChangeable, 
     boolean isContainment, 
     boolean isResolveProxies)
  {
    initEReference
      (r, 
       type, 
       otherEnd, 
       name, 
       defaultValue, 
       lowerBound, 
       upperBound, 
       isTransient, 
       isVolatile, 
       isChangeable, 
       isContainment, 
       isResolveProxies, 
       false, 
       true);
    return r;
  }

  /**
   * @deprecated
   */
  protected EReference initEReference
    (EReference r, 
     EClassifier type, 
     EReference otherEnd,
     String name, 
     String defaultValue,
     int lowerBound, 
     int upperBound, 
     boolean isTransient, 
     boolean isVolatile, 
     boolean isChangeable, 
     boolean isContainment, 
     boolean isResolveProxies,
     boolean isUnsettable)
  {
    initEReference
      (r, 
       type, 
       otherEnd, 
       name, 
       defaultValue, 
       lowerBound, 
       upperBound, 
       isTransient, 
       isVolatile, 
       isChangeable, 
       isContainment, 
       isResolveProxies, 
       isUnsettable, 
       true);
    return r;
  }

  /**
   * @deprecated
   */
  protected EReference initEReference
    (EReference r, 
     EClassifier type, 
     EReference otherEnd,
     String name, 
     String defaultValue,
     int lowerBound, 
     int upperBound, 
     boolean isTransient, 
     boolean isVolatile, 
     boolean isChangeable, 
     boolean isContainment, 
     boolean isResolveProxies,
     boolean isUnsettable,
     boolean isUnique)
  {
    initEReference
      (r, 
       type, 
       otherEnd, 
       name, 
       defaultValue, 
       lowerBound, 
       upperBound, 
       isTransient, 
       isVolatile, 
       isChangeable, 
       isContainment, 
       isResolveProxies, 
       isUnsettable, 
       isUnique,
       false);
    return r;
  }

  protected EReference initEReference
    (EReference r, 
     EClassifier type, 
     EReference otherEnd,
     String name, 
     String defaultValue,
     int lowerBound, 
     int upperBound, 
     boolean isTransient, 
     boolean isVolatile, 
     boolean isChangeable, 
     boolean isContainment, 
     boolean isResolveProxies,
     boolean isUnsettable,
     boolean isUnique,
     boolean isDerived)
  {
    initEReference
      (r, 
       type, 
       otherEnd, 
       name, 
       defaultValue, 
       lowerBound, 
       upperBound, 
       isTransient, 
       isVolatile, 
       isChangeable, 
       isContainment, 
       isResolveProxies, 
       isUnsettable, 
       isUnique,
       isDerived,
       true);
    return r;
  }

  protected EReference initEReference
    (EReference r, 
     EClassifier type, 
     EReference otherEnd,
     String name, 
     String defaultValue,
     int lowerBound, 
     int upperBound, 
     boolean isTransient, 
     boolean isVolatile, 
     boolean isChangeable, 
     boolean isContainment, 
     boolean isResolveProxies,
     boolean isUnsettable,
     boolean isUnique,
     boolean isDerived,
     boolean isOrdered)
  {
    initEReference
      (r, 
       type, 
       otherEnd,
       name, 
       defaultValue, 
       lowerBound, 
       upperBound, 
       ((EClassifier)r.eContainer()).getInstanceClass(),
       isTransient, 
       isVolatile, 
       isChangeable, 
       isContainment,
       isResolveProxies,
       isUnsettable, 
       isUnique, 
       isDerived, 
       isOrdered);
    return r;
  }

  protected EReference initEReference
    (EReference r, 
     EClassifier type, 
     EReference otherEnd,
     String name, 
     String defaultValue,
     int lowerBound, 
     int upperBound, 
     Class containerClass,
     boolean isTransient, 
     boolean isVolatile, 
     boolean isChangeable, 
     boolean isContainment, 
     boolean isResolveProxies,
     boolean isUnsettable,
     boolean isUnique,
     boolean isDerived,
     boolean isOrdered)
  {
    initEStructuralFeature
      (r, 
       type, 
       name, 
       defaultValue, 
       lowerBound, 
       upperBound, 
       containerClass,
       isTransient, 
       isVolatile, 
       isChangeable, 
       isUnsettable, 
       isUnique, 
       isDerived, 
       isOrdered);
    r.setContainment(isContainment);
    if (otherEnd != null)
    {
      r.setEOpposite(otherEnd);
    }
    r.setResolveProxies(isResolveProxies);
    return r;
  }

  private void initEStructuralFeature
    (EStructuralFeature s, 
     EClassifier type,
     String name, 
     String defaultValue,
     int lowerBound, 
     int upperBound, 
     Class containerClass,
     boolean isTransient, 
     boolean isVolatile, 
     boolean isChangeable,
     boolean isUnsettable,
     boolean isUnique,
     boolean isDerived,
     boolean isOrdered)
  {
    s.setName(name);
    ((EStructuralFeatureImpl)s).setContainerClass(containerClass);
    s.setTransient(isTransient);
    s.setVolatile(isVolatile);
    s.setChangeable(isChangeable);
    s.setUnsettable(isUnsettable);
    s.setUnique(isUnique);
    s.setDerived(isDerived);
    s.setOrdered(isOrdered);
    s.setLowerBound(lowerBound);
    s.setUpperBound(upperBound);
    s.setEType(type);
    if (defaultValue != null)
    {
      s.setDefaultValueLiteral(defaultValue);
    }
  }

  protected EOperation addEOperation(EClass owner, EClassifier type, String name)
  {
    EOperation o = ecoreFactory.createEOperation();
    o.setEType(type);
    o.setName(name);
    owner.getEOperations().add(o);
    return o;
  }

  protected EOperation addEOperation(EClass owner, EClassifier type, String name, int lowerBound, int UpperBound)
  {
    EOperation o = addEOperation(owner, type, name);
    o.setLowerBound(lowerBound);
    o.setUpperBound(UpperBound);
    return o;
  }

  private EParameter internalAddEParameter(EOperation owner, EClassifier type, String name)
  {
    EParameter p = ecoreFactory.createEParameter();
    p.setEType(type);
    p.setName(name);
    owner.getEParameters().add(p);
    return p;
  }

  protected void addEParameter(EOperation owner, EClassifier type, String name)
  {
    internalAddEParameter(owner, type, name);
  }

  protected void addEParameter(EOperation owner, EClassifier type, String name, int lowerBound, int UpperBound)
  {
    EParameter p = internalAddEParameter(owner, type, name);
    p.setLowerBound(lowerBound);
    p.setUpperBound(UpperBound);
  }

  protected void addEException(EOperation owner, EClassifier exception)
  {
    owner.getEExceptions().add(exception);
  }

  protected void addEEnumLiteral(EEnum owner, Enumerator e)
  {
    EEnumLiteral l = ecoreFactory.createEEnumLiteral();
    l.setInstance(e);
    owner.getELiterals().add(l);
  }

  protected void addAnnotation(ENamedElement eNamedElement, String source, String [] details)
  {
    EAnnotation eAnnotation = ecoreFactory.createEAnnotation();
    eAnnotation.setSource(source);
    EMap theDetails = eAnnotation.getDetails();
    for (int i = 1; i < details.length; i += 2)
    {
      theDetails.put(details[i - 1], details[i]);
    }
    eNamedElement.getEAnnotations().add(eAnnotation);
  }

  protected void addAnnotation(ENamedElement eNamedElement, int depth, String source, String [] details)
  {
    EAnnotation eAnnotation = ecoreFactory.createEAnnotation();
    eAnnotation.setSource(source);
    EMap theDetails = eAnnotation.getDetails();
    for (int i = 1; i < details.length; i += 2)
    {
      theDetails.put(details[i - 1], details[i]);
    }
    List annotations = eNamedElement.getEAnnotations();
    for (int i = 0; i < depth; ++i)
    {
      annotations = ((EAnnotation)annotations.get(annotations.size() - 1)).getContents();
    }
    annotations.add(eAnnotation);
  }

  protected void initializeFromLoadedEPackage(EPackage target, EPackage source)
  {
    target.setName(source.getName());
    target.setNsPrefix(source.getNsPrefix());
    target.setNsURI(source.getNsURI());

    target.getEClassifiers().addAll(source.getEClassifiers());
    target.getEAnnotations().addAll(source.getEAnnotations());

    for (Iterator i = source.getESubpackages().iterator(); i.hasNext(); )
    {
      EPackage sourceSubpackage = (EPackage)i.next();
      EPackage targetSubpackage = EPackage.Registry.INSTANCE.getEPackage(sourceSubpackage.getNsURI());
      initializeFromLoadedEPackage(targetSubpackage, sourceSubpackage);
      target.getESubpackages().add(targetSubpackage);
    }
  }
  
  protected void fixEClassifiers()
  {
    int id = 0;
    
    for (Iterator i = getEClassifiers().iterator(); i.hasNext(); )
    {
      EClassifierImpl eClassifier = (EClassifierImpl)i.next();
      if (eClassifier instanceof EClass)
      {
        eClassifier.setClassifierID(id++);
        fixInstanceClass(eClassifier);
        fixEStructuralFeatures((EClass)eClassifier);
      }
    }
    
    for (Iterator i = getEClassifiers().iterator(); i.hasNext(); )
    {
      EClassifierImpl eClassifier = (EClassifierImpl)i.next();
      if (eClassifier.getClassifierID() == -1 && eClassifier instanceof EEnum)
      {
        eClassifier.setClassifierID(id++);
        fixInstanceClass(eClassifier);
        fixEEnumLiterals((EEnum)eClassifier);
      }
    }

    for (Iterator i = getEClassifiers().iterator(); i.hasNext(); )
    {
      EClassifierImpl eClassifier = (EClassifierImpl)i.next();
      if (eClassifier.getClassifierID() == -1 && eClassifier instanceof EDataType)
      {
        eClassifier.setClassifierID(id++);
        if (eClassifier.getInstanceClassName() == "org.eclipse.emf.common.util.AbstractEnumerator")
        {
          EDataType baseType = ExtendedMetaData.INSTANCE.getBaseType((EDataType)eClassifier);
          if (baseType instanceof EEnum)
          {
            eClassifier.setInstanceClass(baseType.getInstanceClass());
            setGeneratedClassName(eClassifier);
          }
        }
      }
    }
  }

  protected void fixInstanceClass(EClassifier eClassifier)
  {
    if (eClassifier.getInstanceClassName() == null)
    {
      String className = getClass().getName();
      int i = className.lastIndexOf('.', className.lastIndexOf('.') - 1);
      className = i == -1 ? eClassifier.getName() : className.substring(0, i + 1) + eClassifier.getName();
      eClassifier.setInstanceClassName(className);
      setGeneratedClassName(eClassifier);
    }
  }

  protected void fixEStructuralFeatures(EClass eClass)
  {
    List features = eClass.getEStructuralFeatures();
    if (!features.isEmpty())
    {
      // The container class must be null for the open content features of the document root
      // to ensure that they are looked up in the actual eClass() 
      // rather than assumed to be a feature with a feature ID relative to the actual class.
      // Otherwise, it's good to have this optimization.
      //
      Class containerClass = ExtendedMetaData.INSTANCE.getDocumentRoot(this) == eClass ? null : eClass.getInstanceClass();

      int id = eClass.getFeatureID((EStructuralFeature)features.get(0));
      
      for (Iterator i = features.iterator(); i.hasNext(); )
      {
        EStructuralFeatureImpl eStructuralFeature = (EStructuralFeatureImpl)i.next();
        eStructuralFeature.setFeatureID(id++);
        eStructuralFeature.setContainerClass(containerClass);
      }
    }
  }

  protected void fixEEnumLiterals(EEnum eEnum)
  {
    Class enumClass = eEnum.getInstanceClass();
    
    try
    {
      Method getter = enumClass.getMethod("get", new Class[] { Integer.TYPE });

      for (Iterator i = eEnum.getELiterals().iterator(); i.hasNext(); )
      {
        EEnumLiteral eEnumLiteral = (EEnumLiteral)i.next();
        Enumerator instance = (Enumerator)getter.invoke(null, new Object[] { new Integer(eEnumLiteral.getValue()) });
        eEnumLiteral.setInstance(instance);
      }
    }
    catch (Exception e)
    {
      // Do nothing
    }
  }

  protected BasicExtendedMetaData.EPackageExtendedMetaData ePackageExtendedMetaData;

  public BasicExtendedMetaData.EPackageExtendedMetaData getExtendedMetaData()
  {
    return ePackageExtendedMetaData;
  }

  public void setExtendedMetaData(BasicExtendedMetaData.EPackageExtendedMetaData ePackageExtendedMetaData)
  {
    this.ePackageExtendedMetaData = ePackageExtendedMetaData;
  }

  public EObject eObjectForURIFragmentSegment(String uriFragmentSegment)
  {
    EObject result = getEClassifier(uriFragmentSegment);
    return result != null ? result : super.eObjectForURIFragmentSegment(uriFragmentSegment);
  }
}
