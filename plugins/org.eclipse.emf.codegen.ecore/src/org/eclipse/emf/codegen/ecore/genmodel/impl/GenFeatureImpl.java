/**
 * <copyright> 
 *
 * Copyright (c) 2002-2004 IBM Corporation and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Common Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/cpl-v10.html
 * 
 * Contributors: 
 *   IBM - Initial API and implementation
 *
 * </copyright>
 *
 * $Id: GenFeatureImpl.java,v 1.2 2004/03/31 16:19:31 davidms Exp $
 */
package org.eclipse.emf.codegen.ecore.genmodel.impl;


import java.util.Iterator;

import org.eclipse.emf.codegen.ecore.genmodel.GenClass;
import org.eclipse.emf.codegen.ecore.genmodel.GenDataType;
import org.eclipse.emf.codegen.ecore.genmodel.GenEnum;
import org.eclipse.emf.codegen.ecore.genmodel.GenEnumLiteral;
import org.eclipse.emf.codegen.ecore.genmodel.GenFeature;
import org.eclipse.emf.codegen.ecore.genmodel.GenModelPackage;
import org.eclipse.emf.codegen.ecore.genmodel.GenPackage;
import org.eclipse.emf.codegen.ecore.genmodel.GenPropertyKind;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EEnum;
import org.eclipse.emf.ecore.EModelElement;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.EcoreFactory;
import org.eclipse.emf.ecore.EcorePackage;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.util.ExtendedMetaData;


/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Gen Feature</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.emf.codegen.ecore.genmodel.impl.GenFeatureImpl#getProperty <em>Property</em>}</li>
 *   <li>{@link org.eclipse.emf.codegen.ecore.genmodel.impl.GenFeatureImpl#isNotify <em>Notify</em>}</li>
 *   <li>{@link org.eclipse.emf.codegen.ecore.genmodel.impl.GenFeatureImpl#isChildren <em>Children</em>}</li>
 *   <li>{@link org.eclipse.emf.codegen.ecore.genmodel.impl.GenFeatureImpl#isCreateChild <em>Create Child</em>}</li>
 *   <li>{@link org.eclipse.emf.codegen.ecore.genmodel.impl.GenFeatureImpl#getGenClass <em>Gen Class</em>}</li>
 *   <li>{@link org.eclipse.emf.codegen.ecore.genmodel.impl.GenFeatureImpl#getEcoreFeature <em>Ecore Feature</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class GenFeatureImpl extends GenBaseImpl implements GenFeature
{
  /**
   * The default value of the '{@link #getProperty() <em>Property</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getProperty()
   * @generated
   * @ordered
   */
  protected static final GenPropertyKind PROPERTY_EDEFAULT = GenPropertyKind.EDITABLE_LITERAL;

  /**
   * The cached value of the '{@link #getProperty() <em>Property</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getProperty()
   * @generated
   * @ordered
   */
  protected GenPropertyKind property = PROPERTY_EDEFAULT;

  /**
   * The default value of the '{@link #isNotify() <em>Notify</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #isNotify()
   * @generated
   * @ordered
   */
  protected static final boolean NOTIFY_EDEFAULT = true;

  /**
   * The cached value of the '{@link #isNotify() <em>Notify</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #isNotify()
   * @generated
   * @ordered
   */
  protected boolean notify = NOTIFY_EDEFAULT;

  /**
   * The default value of the '{@link #isChildren() <em>Children</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #isChildren()
   * @generated
   * @ordered
   */
  protected static final boolean CHILDREN_EDEFAULT = false;

  /**
   * The cached value of the '{@link #isChildren() <em>Children</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #isChildren()
   * @generated
   * @ordered
   */
  protected boolean children = CHILDREN_EDEFAULT;

  /**
   * The default value of the '{@link #isCreateChild() <em>Create Child</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #isCreateChild()
   * @generated
   * @ordered
   */
  protected static final boolean CREATE_CHILD_EDEFAULT = false;

  /**
   * The cached value of the '{@link #isCreateChild() <em>Create Child</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #isCreateChild()
   * @generated
   * @ordered
   */
  protected boolean createChild = CREATE_CHILD_EDEFAULT;

  /**
   * This is true if the Create Child attribute has been set.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  protected boolean createChildESet = false;

  /**
   * The cached value of the '{@link #getEcoreFeature() <em>Ecore Feature</em>}' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getEcoreFeature()
   * @generated
   * @ordered
   */
  protected EStructuralFeature ecoreFeature = null;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated modifiable
   */
  protected GenFeatureImpl() 
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
    return GenModelPackage.eINSTANCE.getGenFeature();
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public GenPropertyKind getProperty()
  {
    return property;
  }

  public boolean isProperty()
  {
    return property != GenPropertyKind.NONE_LITERAL;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setProperty(GenPropertyKind newProperty)
  {
    GenPropertyKind oldProperty = property;
    property = newProperty == null ? PROPERTY_EDEFAULT : newProperty;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, GenModelPackage.GEN_FEATURE__PROPERTY, oldProperty, property));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public boolean isNotify()
  {
    return notify;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setNotify(boolean newNotify)
  {
    boolean oldNotify = notify;
    notify = newNotify;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, GenModelPackage.GEN_FEATURE__NOTIFY, oldNotify, notify));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public boolean isChildren()
  {
    return children;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setChildren(boolean newChildren)
  {
    boolean oldChildren = children;
    children = newChildren;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, GenModelPackage.GEN_FEATURE__CHILDREN, oldChildren, children));
  }

  /*
   * Set from {@link #isChildren children} if unset, and return value.
   */
  public boolean isCreateChild()
  {
    autoSetCreateChild();
    return isCreateChildGen();
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public boolean isCreateChildGen()
  {
    return createChild;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setCreateChild(boolean newCreateChild)
  {
    boolean oldCreateChild = createChild;
    createChild = newCreateChild;
    boolean oldCreateChildESet = createChildESet;
    createChildESet = true;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, GenModelPackage.GEN_FEATURE__CREATE_CHILD, oldCreateChild, createChild, !oldCreateChildESet));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void unsetCreateChild()
  {
    boolean oldCreateChild = createChild;
    boolean oldCreateChildESet = createChildESet;
    createChild = CREATE_CHILD_EDEFAULT;
    createChildESet = false;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.UNSET, GenModelPackage.GEN_FEATURE__CREATE_CHILD, oldCreateChild, CREATE_CHILD_EDEFAULT, oldCreateChildESet));
  }

  /**
   * Set from {@link #isChildren children} if necessary, and return true.
   */
  public boolean isSetCreateChild()
  {
    autoSetCreateChild();
    return isSetCreateChildGen();
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public boolean isSetCreateChildGen()
  {
    return createChildESet;
  }

  protected void autoSetCreateChild()
  {
    if (!isSetCreateChildGen())
    {
      boolean value = (isChildren() || (!isChildren() && hasDelegateFeature())) && !isFeatureMapType();
      setCreateChild(value);
    }
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public GenClass getGenClass()
  {
    if (eContainerFeatureID != GenModelPackage.GEN_FEATURE__GEN_CLASS) return null;
    return (GenClass)eContainer;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setGenClass(GenClass newGenClass)
  {
    if (newGenClass != eContainer || (eContainerFeatureID != GenModelPackage.GEN_FEATURE__GEN_CLASS && newGenClass != null))
    {
      if (EcoreUtil.isAncestor(this, newGenClass))
        throw new IllegalArgumentException("Recursive containment not allowed for " + toString());
      NotificationChain msgs = null;
      if (eContainer != null)
        msgs = eBasicRemoveFromContainer(msgs);
      if (newGenClass != null)
        msgs = ((InternalEObject)newGenClass).eInverseAdd(this, GenModelPackage.GEN_CLASS__GEN_FEATURES, GenClass.class, msgs);
      msgs = eBasicSetContainer((InternalEObject)newGenClass, GenModelPackage.GEN_FEATURE__GEN_CLASS, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, GenModelPackage.GEN_FEATURE__GEN_CLASS, newGenClass, newGenClass));
  }

  public EModelElement getEcoreModelElement()
  {
    return getEcoreFeature();
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EStructuralFeature getEcoreFeature()
  {
    if (ecoreFeature != null && ecoreFeature.eIsProxy())
    {
      EStructuralFeature oldEcoreFeature = ecoreFeature;
      ecoreFeature = (EStructuralFeature)eResolveProxy((InternalEObject)ecoreFeature);
      if (ecoreFeature != oldEcoreFeature)
      {
        if (eNotificationRequired())
          eNotify(new ENotificationImpl(this, Notification.RESOLVE, GenModelPackage.GEN_FEATURE__ECORE_FEATURE, oldEcoreFeature, ecoreFeature));
      }
    }
    return ecoreFeature;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EStructuralFeature basicGetEcoreFeature()
  {
    return ecoreFeature;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setEcoreFeature(EStructuralFeature newEcoreFeature)
  {
    EStructuralFeature oldEcoreFeature = ecoreFeature;
    ecoreFeature = newEcoreFeature;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, GenModelPackage.GEN_FEATURE__ECORE_FEATURE, oldEcoreFeature, ecoreFeature));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, Class baseClass, NotificationChain msgs)
  {
    if (featureID >= 0)
    {
      switch (eDerivedStructuralFeatureID(featureID, baseClass))
      {
        case GenModelPackage.GEN_FEATURE__GEN_CLASS:
          if (eContainer != null)
            msgs = eBasicRemoveFromContainer(msgs);
          return eBasicSetContainer(otherEnd, GenModelPackage.GEN_FEATURE__GEN_CLASS, msgs);
        default:
          return eDynamicInverseAdd(otherEnd, featureID, baseClass, msgs);
      }
    }
    if (eContainer != null)
      msgs = eBasicRemoveFromContainer(msgs);
    return eBasicSetContainer(otherEnd, featureID, msgs);
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
        case GenModelPackage.GEN_FEATURE__GEN_CLASS:
          return eBasicSetContainer(null, GenModelPackage.GEN_FEATURE__GEN_CLASS, msgs);
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
  public NotificationChain eBasicRemoveFromContainer(NotificationChain msgs)
  {
    if (eContainerFeatureID >= 0)
    {
      switch (eContainerFeatureID)
      {
        case GenModelPackage.GEN_FEATURE__GEN_CLASS:
          return ((InternalEObject)eContainer).eInverseRemove(this, GenModelPackage.GEN_CLASS__GEN_FEATURES, GenClass.class, msgs);
        default:
          return eDynamicBasicRemoveFromContainer(msgs);
      }
    }
    return ((InternalEObject)eContainer).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - eContainerFeatureID, null, msgs);
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
      case GenModelPackage.GEN_FEATURE__PROPERTY:
        return getProperty();
      case GenModelPackage.GEN_FEATURE__NOTIFY:
        return isNotify() ? Boolean.TRUE : Boolean.FALSE;
      case GenModelPackage.GEN_FEATURE__CHILDREN:
        return isChildren() ? Boolean.TRUE : Boolean.FALSE;
      case GenModelPackage.GEN_FEATURE__CREATE_CHILD:
        return isCreateChild() ? Boolean.TRUE : Boolean.FALSE;
      case GenModelPackage.GEN_FEATURE__GEN_CLASS:
        return getGenClass();
      case GenModelPackage.GEN_FEATURE__ECORE_FEATURE:
        if (resolve) return getEcoreFeature();
        return basicGetEcoreFeature();
    }
    return eDynamicGet(eFeature, resolve);
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
      case GenModelPackage.GEN_FEATURE__PROPERTY:
        return property != PROPERTY_EDEFAULT;
      case GenModelPackage.GEN_FEATURE__NOTIFY:
        return notify != NOTIFY_EDEFAULT;
      case GenModelPackage.GEN_FEATURE__CHILDREN:
        return children != CHILDREN_EDEFAULT;
      case GenModelPackage.GEN_FEATURE__CREATE_CHILD:
        return isSetCreateChild();
      case GenModelPackage.GEN_FEATURE__GEN_CLASS:
        return getGenClass() != null;
      case GenModelPackage.GEN_FEATURE__ECORE_FEATURE:
        return ecoreFeature != null;
    }
    return eDynamicIsSet(eFeature);
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
      case GenModelPackage.GEN_FEATURE__PROPERTY:
        setProperty((GenPropertyKind)newValue);
        return;
      case GenModelPackage.GEN_FEATURE__NOTIFY:
        setNotify(((Boolean)newValue).booleanValue());
        return;
      case GenModelPackage.GEN_FEATURE__CHILDREN:
        setChildren(((Boolean)newValue).booleanValue());
        return;
      case GenModelPackage.GEN_FEATURE__CREATE_CHILD:
        setCreateChild(((Boolean)newValue).booleanValue());
        return;
      case GenModelPackage.GEN_FEATURE__GEN_CLASS:
        setGenClass((GenClass)newValue);
        return;
      case GenModelPackage.GEN_FEATURE__ECORE_FEATURE:
        setEcoreFeature((EStructuralFeature)newValue);
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
      case GenModelPackage.GEN_FEATURE__PROPERTY:
        setProperty(PROPERTY_EDEFAULT);
        return;
      case GenModelPackage.GEN_FEATURE__NOTIFY:
        setNotify(NOTIFY_EDEFAULT);
        return;
      case GenModelPackage.GEN_FEATURE__CHILDREN:
        setChildren(CHILDREN_EDEFAULT);
        return;
      case GenModelPackage.GEN_FEATURE__CREATE_CHILD:
        unsetCreateChild();
        return;
      case GenModelPackage.GEN_FEATURE__GEN_CLASS:
        setGenClass((GenClass)null);
        return;
      case GenModelPackage.GEN_FEATURE__ECORE_FEATURE:
        setEcoreFeature((EStructuralFeature)null);
        return;
    }
    eDynamicUnset(eFeature);
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
    result.append(" (property: ");
    result.append(property);
    result.append(", notify: ");
    result.append(notify);
    result.append(", children: ");
    result.append(children);
    result.append(", createChild: ");
    if (createChildESet) result.append(createChild); else result.append("<unset>");
    result.append(')');
    return result.toString();
  }

  public String getName()
  {
    return getEcoreFeature().getName();
  }

  public String getCapName()
  {
    return capName(getName());
  }

  public String getAccessorName()
  {
    return isMapEntryFeature() ? "Typed" + getCapName() : getCapName();
  }

  public String getGetAccessor()
  {
    String capName = getCapName();
    if (isMapEntryFeature()) return "getTyped" + capName;
    return isBooleanType() ? "is" + capName : "get" + ("Class".equals(capName) ? "Class_" : capName);
  }

  public String getSafeName()
  {
    return safeName(uncapPrefixedName(getName()));
  }

  public String getSafeNameAsEObject()
  {
    String result = getSafeName();
    if (!isEObjectExtensionType())
    {
      result = "((" + getGenModel().getImportedName("org.eclipse.emf.ecore.EObject") + ")" + result + ")";
    }
    return result;
  }

  public String getFormattedName()
  {
    return format(getCapName(), ' ', null, false);
  }

  public String getUpperName()
  {
    return format(getName(), '_', null, false).toUpperCase();
  }

  public String getUncapName()
  {
    return uncapPrefixedName(getName());
  }

  public String getIsName()
  {
    String name = getName();
    if (name.startsWith("is") && name.length() > 2 && Character.isUpperCase(name.charAt(2)))
    {
      return name.substring(2);
    }
    else
    {
      return capName(name);
    }
  }

  public GenPackage getGenPackage()
  {
    return getGenClass().getGenPackage();
  }

  public String getFeatureAccessorName()
  {
    return getGenClass().getName() + "_" + getCapName();
  }

  public String getQualifiedFeatureAccessorName()
  {
    return getGenPackage().getImportedPackageInterfaceName() + ".eINSTANCE.get" + getFeatureAccessorName();
  }

  public String getMetaType()
  {
    String importedName = getImportedMetaType();
    return importedName.substring(importedName.lastIndexOf(".") + 1);
  }

  public String getImportedMetaType()
  {
    if (getEcoreFeature() instanceof EReference)
      return getGenModel().getImportedName("org.eclipse.emf.ecore.EReference");
    else
      return getGenModel().getImportedName("org.eclipse.emf.ecore.EAttribute");
  }

  public String getFeatureKind()
  {
    String kind = 
      getEcoreFeature() instanceof EReference ? 
        isContains() ? 
          isJavaUtilMapEntry(getEcoreFeature().getEType().getInstanceClassName()) ? 
            "map" : 
            "containment reference" : 
          isContainer() ? "container reference" : "reference" :
        "attribute";
    return 
      !"map".equals(kind) && getEcoreFeature().isMany() ? 
        kind + " list" : 
        kind;
  }

  public boolean isReferenceType()
  {
    return getEcoreFeature() instanceof EReference;
  }

  public String getEObjectCast()
  {
    return 
      isEObjectExtensionType() && !isEffectiveSuppressEMFTypes() ? 
        "" : 
        "(" + getGenModel().getImportedName("org.eclipse.emf.ecore.EObject") + ")";
  }

  public String getInternalTypeCast()
  {
    return 
      isEObjectType() && isEffectiveSuppressEMFTypes() ? 
        "(" + getGenModel().getImportedName("org.eclipse.emf.ecore.EObject") + ")" :
        "";
  }

  public boolean isEObjectExtensionType()
  {
    return isReferenceType() && findGenClass((EClass)getEcoreFeature().getEType()).isEObjectExtension();
  }

  public boolean isEObjectType()
  {
    return isReferenceType() && findGenClass((EClass)getEcoreFeature().getEType()).isEObject();
  }

  public String getTypeClassifier()
  {
    return findGenClassifier(getEcoreFeature().getEType()).getClassifierAccessorName();
  }

  public GenPackage getTypeGenPackage()
  {
    return findGenPackage(getEcoreFeature().getEType().getEPackage());
  }

  public boolean isEffectiveSuppressEMFTypes()
  {
    return getGenModel().isSuppressEMFTypes();
  }

  public String getEffectiveMapType()
  {
    return isEffectiveSuppressEMFTypes() ?
      "java.util.Map" : "org.eclipse.emf.common.util.EMap";
  }

  public String getEffectiveListType()
  {
    return isEffectiveSuppressEMFTypes() ?
       "java.util.List" : "org.eclipse.emf.common.util.EList";
  }

  public String getEffectiveEObjectType()
  {
    return isEffectiveSuppressEMFTypes() ?
      "java.lang.Object" : "org.eclipse.emf.ecore.EObject";
  }

  public String getEffectiveFeatureMapWrapperInterface()
  {
    String result = getGenModel().getFeatureMapWrapperInterface();
    return isBlank(result) ? "org.eclipse.emf.ecore.util.FeatureMap" : result;
  }

  public String getImportedEffectiveFeatureMapWrapperInternalInterface()
  {
    String result = getGenModel().getFeatureMapWrapperInternalInterface();
    return getGenModel().getImportedName(isBlank(result) ? "org.eclipse.emf.ecore.util.FeatureMap" : result);
  }

  public String getImportedEffectiveFeatureMapWrapperClass()
  {
    String result = getGenModel().getFeatureMapWrapperClass();
    return getGenModel().getImportedName(isBlank(result) ? "org.eclipse.emf.ecore.util.FeatureMap" : result);
  }

  public String getType()
  {
    if (isFeatureMapType()) return getEffectiveFeatureMapWrapperInterface();
    if (isMapType()) return getEffectiveMapType();
    if (isListType()) return getEffectiveListType();
    if (isEObjectType()) return getEffectiveEObjectType();
    return getType(getEcoreFeature().getEType(), false);
  }

  public String getImportedType()
  {
    if (isFeatureMapType()) return getGenModel().getImportedName(getEffectiveFeatureMapWrapperInterface());
    if (isMapType()) return getGenModel().getImportedName(getEffectiveMapType());
    if (isListType()) return getGenModel().getImportedName(getEffectiveListType());
    if (isEObjectType()) return getGenModel().getImportedName(getEffectiveEObjectType());
    return getImportedType(getEcoreFeature().getEType(), false);
  }

  public String getObjectType()
  {
    if (isFeatureMapType()) return getGenModel().getImportedName(getEffectiveFeatureMapWrapperInterface());
    if (isMapType()) return getGenModel().getImportedName(getEffectiveMapType());
    if (isListType()) return getGenModel().getImportedName(getEffectiveListType());
    if (isEObjectType()) return getGenModel().getImportedName(getEffectiveEObjectType());
    return getImportedType(getEcoreFeature().getEType(), true);
  }

  public String getImportedInternalType()
  {
    if (isFeatureMapType()) return getImportedEffectiveFeatureMapWrapperInternalInterface();
    if (isMapType()) return getGenModel().getImportedName("org.eclipse.emf.common.util.EMap");
    if (isListType()) return getGenModel().getImportedName("org.eclipse.emf.common.util.EList");
    return getImportedType(getEcoreFeature().getEType(), false);
  }

  public String getQualifiedListItemType()
  {
    return getType(getEcoreFeature().getEType(), true).replace('$', '.');
  }

  public String getListItemType()
  {
    return getImportedType(getEcoreFeature().getEType(), true);
  }

  public GenClass getMapGenClass()
  {
    EClassifier eType = getEcoreFeature().getEType();
    if (eType instanceof EClass && isJavaUtilMapEntry(eType.getInstanceClassName()))
    {
      GenClass genClass = findGenClass((EClass)eType);
      if (genClass.isMapEntry())
      {
        return genClass;
      }
    }

    return null;
  }

  public String getMapItemType()
  {
    GenClass genClass = findGenClass((EClass)getEcoreFeature().getEType());
    return genClass.getImportedClassName();
  }

  public boolean isSetDefaultValue()
  {
    return getEcoreFeature().getDefaultValueLiteral() != null;
  }

  public String getDefaultValue()
  {
    if (!isSetDefaultValue()) return null;
    return "\"" + getEcoreFeature().getDefaultValueLiteral() + "\"";
  }

  public String getStaticDefaultValue()
  {
    String defaultString = getEcoreFeature().getDefaultValueLiteral();

    EClassifier eType = getEcoreFeature().getEType();
    if (eType instanceof EEnum)
    {
      GenEnum genEnum = findGenEnum((EEnum)eType);
      GenEnumLiteral genEnumLiteral = genEnum.getGenEnumLiteral(defaultString);
      return genEnum.getImportedName() + "." + genEnumLiteral.getEnumLiteralID() + "_LITERAL";
    }

    if (eType instanceof EDataType)
    {
      if (eType.getEPackage() != EcorePackage.eINSTANCE && defaultString != null)
      {
        boolean replaced = false;
        for (Iterator i = EcorePackage.eINSTANCE.getEClassifiers().iterator(); i.hasNext(); )
        {
          EClassifier eClassifier = (EClassifier)i.next();
          if (eClassifier instanceof EDataType && 
                eClassifier.getInstanceClassName().equals(eType.getInstanceClassName()) &&
                ((EDataType)eClassifier).isSerializable())
          {
            replaced = true;
            eType = eClassifier;
            break;
          }
        }
        if (!replaced)
        {
          return null;
        }
      }

      Object defaultObject = eType.getDefaultValue();
      if (defaultString != null)
      {
        try
        {
          defaultObject = EcoreFactory.eINSTANCE.createFromString((EDataType)eType, defaultString);
        }
        catch (Exception e)
        {
          return "";  // cause a syntax error
        }
      }
      if (defaultObject == null) return "null";
      String result = Literals.toLiteral(defaultObject);

      // import any class names
      if (defaultObject instanceof Float && result.startsWith("java.lang.Float"))
      {
        result = getGenModel().getImportedName("java.lang.Float") + result.substring(15);
      }
      else if (defaultObject instanceof Double && result.startsWith("java.lang.Double"))
      {
        result = getGenModel().getImportedName("java.lang.Double") + result.substring(16);
      }
      else if (defaultObject instanceof Class && result.endsWith(".class"))
      {
        result = getGenModel().getImportedName(result.substring(0, result.length() - 6)) + ".class";
      }

      // include wrapping for wrapped primitive types
      Class typeClass = getInstanceClass(eType);
      if (typeClass == Boolean.class || typeClass == Character.class ||
          typeClass == Byte.class    || typeClass == Short.class     ||
          typeClass == Integer.class || typeClass == Long.class      ||
          typeClass == Float.class   || typeClass == Double.class)
      {
        StringBuffer wrapped = new StringBuffer("new ");
        wrapped.append(getGenModel().getImportedName(eType.getInstanceClassName()));
        wrapped.append('(');
        if (typeClass == Byte.class)
        {
          wrapped.append("(byte)");
        }
        else if (typeClass == Short.class)
        {
          wrapped.append("(short)");
        }
        wrapped.append(result);
        wrapped.append(')');
        result = wrapped.toString();
      }
      return result;
    }

    return "null";
  }

  public boolean isEnumType()
  {
    return !isListType() && getEcoreFeature().getEType() instanceof EEnum;
  }

  public GenEnum getGenEnumType()
  {
    EClassifier eType = getEcoreFeature().getEType();
    return eType instanceof EEnum ? findGenEnum((EEnum)eType) : null;
  }

  public GenDataType getGenDataTypeType()
  {
    EClassifier eType = getEcoreFeature().getEType();
    return eType instanceof EDataType ? findGenDataType((EDataType)eType) : null;
  }

  public boolean isBooleanType()
  {
    return isPrimitiveType() &&
      getInstanceClass(getEcoreFeature().getEType()) == java.lang.Boolean.TYPE;
  }

  public boolean isStringType()
  {
    return !isListType() &&
      getInstanceClass(getEcoreFeature().getEType()) == java.lang.String.class;
  }

  public boolean isListType()
  {
    return getEcoreFeature().isMany() || isFeatureMapType();
  }

  public boolean isMapType()
  {
    return isListType() && !isContainer() && getMapGenClass() != null;
  }

  protected boolean isMapEntryFeature()
  {
    return getGenClass().isMapEntry() && ("key".equals(getName()) || "value".equals(getName()));
  }

  protected static boolean isFeatureMapEntry(String name)
  {
    return "org.eclipse.emf.ecore.util.FeatureMap.Entry".equals(name) || "org.eclipse.emf.ecore.util.FeatureMap$Entry".equals(name);
  }  

  public boolean isFeatureMapType()
  {
    return isFeatureMapEntry(getEcoreFeature().getEType().getInstanceClassName());
  }

  public boolean isFeatureMapWrapped()
  {
    return 
      isFeatureMapType() &&  
        !isBlank(getGenModel().getFeatureMapWrapperInterface()) &&
        !isBlank(getGenModel().getFeatureMapWrapperInternalInterface()) &&
        !isBlank(getGenModel().getFeatureMapWrapperClass());
  }

  public boolean isContainer()
  {
    if (isReferenceType())
    {
      EReference opposite = ((EReference)getEcoreFeature()).getEOpposite();
      return opposite != null && opposite.isContainment();
    }
    return false;
  }

  public boolean isContains()
  {
    return isReferenceType() && ((EReference)getEcoreFeature()).isContainment();
  }

  public boolean isBidirectional()
  {
    if (isReferenceType())
    {
      EReference eReverseFeature = ((EReference)getEcoreFeature()).getEOpposite();
      return eReverseFeature != null; //  && eReverseFeature.isNavigable();
    }
    return false;
  }

  public GenFeature getReverse()
  {
    EReference eReverseFeature = ((EReference)getEcoreFeature()).getEOpposite();
    if (eReverseFeature != null)
    {
      EClass eReverseClass = (EClass)eReverseFeature.eContainer();
      GenClass genClass = findGenClass(eReverseClass);
      if (genClass != null)
      {
        for (Iterator iter = genClass.getGenFeatures().iterator(); iter.hasNext(); )
        {
          GenFeature genFeature = (GenFeature)iter.next();
          if (genFeature.getEcoreFeature() == eReverseFeature)
          {
            return genFeature;
          }
        }
      }
    }
    return null;
  }

  public boolean isPrimitiveType()
  {
    return !isListType() && isPrimitiveType(getEcoreFeature().getEType());
  }

  public String getPrimitiveValueFunction()
  {
    Class instanceClass = getInstanceClass(getEcoreFeature().getEType());
    if (instanceClass == java.lang.Boolean.TYPE)
      return "booleanValue";
    if (instanceClass == java.lang.Byte.TYPE)
      return "byteValue";
    if (instanceClass == java.lang.Character.TYPE)
      return "charValue";
    if (instanceClass == java.lang.Double.TYPE)
      return "doubleValue";
    if (instanceClass == java.lang.Float.TYPE)
      return "floatValue";
    if (instanceClass == java.lang.Integer.TYPE)
      return "intValue";
    if (instanceClass == java.lang.Long.TYPE)
      return "longValue";
    if (instanceClass == java.lang.Short.TYPE)
      return "shortValue";
    return null;
  }

  public String getLowerBound()
  {
    return String.valueOf(getEcoreFeature().getLowerBound());
  }

  public String getUpperBound()
  {
    return String.valueOf(getEcoreFeature().getUpperBound());
  }

  public String getDerivedFlag()
  {
    String result = !getEcoreFeature().isDerived() ? "!" : "";
    return result + "IS_DERIVED";
  }

  public String getTransientFlag()
  {
    String result = !getEcoreFeature().isTransient() ? "!" : "";
    return result + "IS_TRANSIENT";
  }

  public String getVolatileFlag()
  {
    String result = !getEcoreFeature().isVolatile() ? "!" : "";
    return result + "IS_VOLATILE";
  }

  public String getChangeableFlag()
  {
    String result = !getEcoreFeature().isChangeable() ? "!" : "";
    return result + "IS_CHANGEABLE";
  }

  public String getUnsettableFlag()
  {
    String result = !isUnsettable() ? "!" : "";
    return result + "IS_UNSETTABLE";
  }

  public String getUniqueFlag()
  {
    String result = !isUnique() ? "!" : "";
    return result + "IS_UNIQUE";
  }

  public String getIDFlag()
  {
    String result = !isID() ? "!" : "";
    return result + "IS_ID";
  }

  public String getContainmentFlag()
  {
    String result = !((EReference)getEcoreFeature()).isContainment() ? "!" : "";
    return result + "IS_COMPOSITE";
  }

  public String getResolveProxiesFlag()
  {
    String result = !isResolveProxies() ? "!" : "";
    return result + "IS_RESOLVE_PROXIES";
  }

  public boolean isResolveProxies()
  {
    EStructuralFeature eStructuralFeature = getEcoreFeature();
    return !isContainer() && !isContains() && 
           eStructuralFeature instanceof EReference && ((EReference)eStructuralFeature).isResolveProxies();
  }

  public boolean isVolatile()
  {
    // We treat the feature as volatile if it is volatile itself or if it is
    // a reference whose opposite end is volatile.

    EReference eReverseFeature = isReferenceType() ?
      ((EReference)getEcoreFeature()).getEOpposite() : null;

    return (getEcoreFeature().isVolatile() ||
            (eReverseFeature != null && eReverseFeature.isVolatile()));
  }

  public boolean isChangeable()
  {
    return getEcoreFeature().isChangeable();
  }

  public boolean isUnsettable()
  {
    EStructuralFeature eStructuralFeature = getEcoreFeature();
    return eStructuralFeature.isUnsettable() && !isContainer();
  }

  public boolean isID()
  {
    EStructuralFeature eStructuralFeature = getEcoreFeature();
    return eStructuralFeature instanceof EAttribute && ((EAttribute)eStructuralFeature).isID();
  }

  public boolean isUnique()
  {
    return getEcoreFeature().isUnique();
  }

  public boolean hasDelegateFeature()
  {
    EStructuralFeature ecoreFeature = getEcoreFeature();
    EClass ecoreClass = ecoreFeature.getEContainingClass();
    EStructuralFeature mixedFeature = ExtendedMetaData.INSTANCE.getMixedFeature(ecoreClass);
    return 
      (mixedFeature != null && mixedFeature != ecoreFeature) ||
      ExtendedMetaData.INSTANCE.getGroup(ecoreFeature) != null;
  }

  public GenFeature getDelegateFeature()
  {
    EStructuralFeature ecoreFeature = getEcoreFeature();
    EClass ecoreClass = ecoreFeature.getEContainingClass();
    EStructuralFeature eStructuralFeature = ExtendedMetaData.INSTANCE.getGroup(ecoreFeature);
    if (eStructuralFeature == null)
    {
      eStructuralFeature = ExtendedMetaData.INSTANCE.getMixedFeature(ecoreClass);
    }
    if (eStructuralFeature != null && eStructuralFeature != ecoreFeature)
    {
      return findGenFeature(eStructuralFeature);
    }
    else
    {
      return null;
    }
  }

  public void initialize(EStructuralFeature eFeature)
  {
    if (eFeature != getEcoreFeature())
    {
      setEcoreFeature(eFeature);
  
      if (eFeature instanceof EReference)
      {
        EReference eReference = (EReference)eFeature;
        if (!eReference.isContainer() && !eReference.isContainment())
        {
          setProperty(eFeature.isChangeable() ? GenPropertyKind.EDITABLE_LITERAL : GenPropertyKind.READONLY_LITERAL);
        }
        else
        {
          setProperty(GenPropertyKind.NONE_LITERAL);
        }
        setChildren(eReference.isContainment() && !hasDelegateFeature());
        setCreateChild(eReference.isContainment());
        setNotify(isChildren());
      }
      else if (isFeatureMapType())
      {
        setProperty(GenPropertyKind.NONE_LITERAL);
        setChildren(!hasDelegateFeature());
        setCreateChild(false);
        setNotify(isChildren());
      }
      else
      {
        setProperty(eFeature.isChangeable() ? GenPropertyKind.EDITABLE_LITERAL : GenPropertyKind.READONLY_LITERAL);
        setChildren(false);
        setCreateChild(false);
        setNotify(true);
      }
    }
  }

  public String getModelInfo()
  {
    return getModelInfo(false);
  }

  public String getQualifiedModelInfo()
  {
    return getModelInfo(true);
  }

  public String getModelInfo(boolean qualified)
  {
    EStructuralFeature eStructuralFeature = getEcoreFeature();
    StringBuffer result = new StringBuffer();
    boolean defaultTransient = false;
    
    GenClass mapGenClass = getMapGenClass();
    if (mapGenClass != null)
    {
      appendModelSetting
        (result, 
         qualified, 
         "mapType", 
         mapGenClass.getGenPackage().getInterfacePackageName() + '.' + mapGenClass.getEcoreClass().getName());

      EReference eReference = (EReference) eStructuralFeature;
      if (!qualified && !eReference.isContainer())
      {
        GenFeature keyFeature = mapGenClass.getMapEntryKeyFeature();
        appendModelSetting(result, false, "keyType", getType(keyFeature.getEcoreFeature().getEType(), false));
  
        GenFeature valueFeature = mapGenClass.getMapEntryValueFeature();
        appendModelSetting(result, false, "valueType", getType(valueFeature.getEcoreFeature().getEType(), false));
      }
    }
    else
    {
      if (eStructuralFeature.isMany() && !isFeatureMapType() || qualified)
      {
        appendModelSetting(result, qualified, "type", getType(eStructuralFeature.getEType(), false));
      }

      if (eStructuralFeature instanceof EReference)
      {
        EReference reference = (EReference) eStructuralFeature;
        EReference opposite = reference.getEOpposite();
        if (opposite != null)
        {
          appendModelSetting(result, qualified, "opposite", opposite.getName());

          if (opposite.isContainment())
          {
            defaultTransient = true;
          }
        }
        if (reference.isContainment())
        {
          appendModelSetting(result, qualified, "containment", "true");
        }
        if (!reference.isResolveProxies())
        {
          appendModelSetting(result, qualified, "resolveProxies", "false");
        }
        if (reference.isUnsettable())
        {
          appendModelSetting(result, qualified, "unsettable", "true");
        }
      } 
      else if (eStructuralFeature instanceof EAttribute)
      {
        EAttribute attribute = (EAttribute) eStructuralFeature;
        if (eStructuralFeature.getDefaultValueLiteral() != null)
        {
          appendModelSetting(result, qualified, "default", eStructuralFeature.getDefaultValueLiteral());
        }
        if (!eStructuralFeature.isUnique())
        {
          appendModelSetting(result, qualified, "unique", "false");
        }
        if (attribute.isUnsettable())
        {
          appendModelSetting(result, qualified, "unsettable", "true");
        }
        if (attribute.isID())
        {
          appendModelSetting(result, qualified, "id", "true");
        }

        EDataType eDataType = attribute.getEAttributeType();
        if (!(eDataType instanceof EEnum))
        {
          GenPackage genPackage = findGenPackage(eDataType.getEPackage());
          if (genPackage != null && (isFeatureMapType() || !genPackage.isEcorePackage()))
          {
            appendModelSetting(result, qualified, "dataType", genPackage.getInterfacePackageName() + '.' + eDataType.getName());
          }
        }
      }
    
      int lowerBound = eStructuralFeature.getLowerBound();
      if (lowerBound == 1)
      {
        appendModelSetting(result, qualified, "required", "true");
      }
      else if (lowerBound > 1)
      {
        appendModelSetting(result, qualified, "lower", Integer.toString(lowerBound));
      }
      
      int upperBound = eStructuralFeature.getUpperBound();
      if (upperBound > 1)
      {
        appendModelSetting(result, qualified, "upper", Integer.toString(eStructuralFeature.getUpperBound()));
      }
      else if (upperBound == 1)
      {
        String typeName = getType(eStructuralFeature.getEType(), false);
        if ("org.eclipse.emf.common.util.EList".equals(typeName) || "java.util.List".equals(typeName)) 
        {
          appendModelSetting(result, qualified, "many", "false");
        }
      }
      else if ((qualified || isFeatureMapType()) && upperBound == -1)
      {
        appendModelSetting(result, qualified, "many", "true");
      }
    }

    if (eStructuralFeature.isTransient() && !defaultTransient)
    {
      appendModelSetting(result, qualified, "transient", "true");
    }

    if (!eStructuralFeature.isChangeable())
    {
      appendModelSetting(result, qualified, "changeable", "false");
    }

    if (eStructuralFeature.isVolatile())
    {
      appendModelSetting(result, qualified, "volatile", "true");
    }

    if (eStructuralFeature.isDerived())
    {
      appendModelSetting(result, qualified, "derived", "true");
    }

    return result.toString().trim();
  }

  protected void appendModelSetting(StringBuffer result, boolean qualified, String name, String value)
  {
    appendModelSetting(result, qualified ? getEcoreFeature().getName() : null, name, value);
  }

  //
  // EMFEdit generation
  //

  public String getPropertyImageName()
  {
    EClassifier eType = getEcoreFeature().getEType();
    if (isPrimitiveType(eType))
    {
      Class instanceClass = eType.getInstanceClass();
      if (instanceClass == Boolean.TYPE || instanceClass == Boolean.class)
      {
        return "BOOLEAN_VALUE_IMAGE";
      }
      else if (instanceClass == Byte.TYPE || instanceClass == Byte.class || 
               instanceClass == Integer.TYPE || instanceClass == Integer.class ||
               instanceClass == Long.TYPE || instanceClass == Long.class ||
               instanceClass == Short.TYPE || instanceClass == Short.class)
      {
        return "INTEGRAL_VALUE_IMAGE";
      }
      else if (instanceClass == Character.TYPE || instanceClass == Character.class ||
               instanceClass == String.class)
      {
        return "TEXT_VALUE_IMAGE";
      }
      else if (instanceClass == Double.TYPE ||  instanceClass == Double.class ||
               instanceClass == Float.TYPE || instanceClass == Float.class)
      {
        return "REAL_VALUE_IMAGE";
      }
    }

    return "GENERIC_VALUE_IMAGE";
  }

  public boolean reconcile(GenFeature oldGenFeatureVersion)
  {
    if (getEcoreFeature().getName().equals(oldGenFeatureVersion.getEcoreFeature().getName()))
    {
      reconcileSettings(oldGenFeatureVersion);
      return true;
    }
    else
    {
      return false;
    }
  }

  protected void reconcileSettings(GenFeature oldGenFeatureVersion)
  {
    setProperty(oldGenFeatureVersion.getProperty());
    setNotify(oldGenFeatureVersion.isNotify());

    if ((hasDelegateFeature() && oldGenFeatureVersion.hasDelegateFeature()) ||
        (isContains() && oldGenFeatureVersion.isContains()) ||
        (!hasDelegateFeature() && !isContains()))
    {
      setChildren(oldGenFeatureVersion.isChildren());
      setCreateChild(oldGenFeatureVersion.isCreateChild());
    }
  }

  public boolean reconcile()
  {
    EStructuralFeature eFeature = getEcoreFeature();
    if (eFeature == null || eFeature.eIsProxy() || eFeature.eResource() == null)
    {
      return false;
    }
    else
    {
      return true;
    }
  }
} //GenFeatureImpl
