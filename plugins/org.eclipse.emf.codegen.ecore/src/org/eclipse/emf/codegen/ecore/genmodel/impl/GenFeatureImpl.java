/**
 * <copyright> 
 *
 * Copyright (c) 2002-2005 IBM Corporation and others.
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
 * $Id: GenFeatureImpl.java,v 1.21 2005/05/29 11:36:37 emerks Exp $
 */
package org.eclipse.emf.codegen.ecore.genmodel.impl;


import java.util.Collection;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.eclipse.emf.codegen.ecore.genmodel.GenClass;
import org.eclipse.emf.codegen.ecore.genmodel.GenDataType;
import org.eclipse.emf.codegen.ecore.genmodel.GenEnum;
import org.eclipse.emf.codegen.ecore.genmodel.GenFeature;
import org.eclipse.emf.codegen.ecore.genmodel.GenModelPackage;
import org.eclipse.emf.codegen.ecore.genmodel.GenOperation;
import org.eclipse.emf.codegen.ecore.genmodel.GenPackage;
import org.eclipse.emf.codegen.ecore.genmodel.GenPropertyKind;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EEnum;
import org.eclipse.emf.ecore.EModelElement;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.util.EDataTypeUniqueEList;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.util.ExtendedMetaData;
import org.eclipse.emf.ecore.xml.type.XMLTypePackage;


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
 *   <li>{@link org.eclipse.emf.codegen.ecore.genmodel.impl.GenFeatureImpl#getPropertyCategory <em>Property Category</em>}</li>
 *   <li>{@link org.eclipse.emf.codegen.ecore.genmodel.impl.GenFeatureImpl#getPropertyFilterFlags <em>Property Filter Flags</em>}</li>
 *   <li>{@link org.eclipse.emf.codegen.ecore.genmodel.impl.GenFeatureImpl#getPropertyDescription <em>Property Description</em>}</li>
 *   <li>{@link org.eclipse.emf.codegen.ecore.genmodel.impl.GenFeatureImpl#getGenClass <em>Gen Class</em>}</li>
 *   <li>{@link org.eclipse.emf.codegen.ecore.genmodel.impl.GenFeatureImpl#getEcoreFeature <em>Ecore Feature</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class GenFeatureImpl extends GenTypedElementImpl implements GenFeature
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
   * The default value of the '{@link #getPropertyCategory() <em>Property Category</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getPropertyCategory()
   * @generated
   * @ordered
   */
  protected static final String PROPERTY_CATEGORY_EDEFAULT = null;

  /**
   * The cached value of the '{@link #getPropertyCategory() <em>Property Category</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getPropertyCategory()
   * @generated
   * @ordered
   */
  protected String propertyCategory = PROPERTY_CATEGORY_EDEFAULT;

  /**
   * The cached value of the '{@link #getPropertyFilterFlags() <em>Property Filter Flags</em>}' attribute list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getPropertyFilterFlags()
   * @generated
   * @ordered
   */
  protected EList propertyFilterFlags = null;

  /**
   * The default value of the '{@link #getPropertyDescription() <em>Property Description</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getPropertyDescription()
   * @generated
   * @ordered
   */
  protected static final String PROPERTY_DESCRIPTION_EDEFAULT = null;

  /**
   * The cached value of the '{@link #getPropertyDescription() <em>Property Description</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getPropertyDescription()
   * @generated
   * @ordered
   */
  protected String propertyDescription = PROPERTY_DESCRIPTION_EDEFAULT;

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

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public String getPropertyCategory()
  {
    return propertyCategory;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setPropertyCategory(String newPropertyCategory)
  {
    String oldPropertyCategory = propertyCategory;
    propertyCategory = newPropertyCategory;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, GenModelPackage.GEN_FEATURE__PROPERTY_CATEGORY, oldPropertyCategory, propertyCategory));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EList getPropertyFilterFlags()
  {
    if (propertyFilterFlags == null)
    {
      propertyFilterFlags = new EDataTypeUniqueEList(String.class, this, GenModelPackage.GEN_FEATURE__PROPERTY_FILTER_FLAGS);
    }
    return propertyFilterFlags;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public String getPropertyDescription()
  {
    return propertyDescription;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setPropertyDescription(String newPropertyDescription)
  {
    String oldPropertyDescription = propertyDescription;
    propertyDescription = newPropertyDescription;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, GenModelPackage.GEN_FEATURE__PROPERTY_DESCRIPTION, oldPropertyDescription, propertyDescription));
  }

  protected void autoSetCreateChild()
  {
    if (!isSetCreateChildGen())
    {
      setCreateChild(isChildren());
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
          return eContainer.eInverseRemove(this, GenModelPackage.GEN_CLASS__GEN_FEATURES, GenClass.class, msgs);
        default:
          return eDynamicBasicRemoveFromContainer(msgs);
      }
    }
    return eContainer.eInverseRemove(this, EOPPOSITE_FEATURE_BASE - eContainerFeatureID, null, msgs);
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
      case GenModelPackage.GEN_FEATURE__PROPERTY_CATEGORY:
        return getPropertyCategory();
      case GenModelPackage.GEN_FEATURE__PROPERTY_FILTER_FLAGS:
        return getPropertyFilterFlags();
      case GenModelPackage.GEN_FEATURE__PROPERTY_DESCRIPTION:
        return getPropertyDescription();
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
      case GenModelPackage.GEN_FEATURE__PROPERTY_CATEGORY:
        return PROPERTY_CATEGORY_EDEFAULT == null ? propertyCategory != null : !PROPERTY_CATEGORY_EDEFAULT.equals(propertyCategory);
      case GenModelPackage.GEN_FEATURE__PROPERTY_FILTER_FLAGS:
        return propertyFilterFlags != null && !propertyFilterFlags.isEmpty();
      case GenModelPackage.GEN_FEATURE__PROPERTY_DESCRIPTION:
        return PROPERTY_DESCRIPTION_EDEFAULT == null ? propertyDescription != null : !PROPERTY_DESCRIPTION_EDEFAULT.equals(propertyDescription);
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
      case GenModelPackage.GEN_FEATURE__PROPERTY_CATEGORY:
        setPropertyCategory((String)newValue);
        return;
      case GenModelPackage.GEN_FEATURE__PROPERTY_FILTER_FLAGS:
        getPropertyFilterFlags().clear();
        getPropertyFilterFlags().addAll((Collection)newValue);
        return;
      case GenModelPackage.GEN_FEATURE__PROPERTY_DESCRIPTION:
        setPropertyDescription((String)newValue);
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
      case GenModelPackage.GEN_FEATURE__PROPERTY_CATEGORY:
        setPropertyCategory(PROPERTY_CATEGORY_EDEFAULT);
        return;
      case GenModelPackage.GEN_FEATURE__PROPERTY_FILTER_FLAGS:
        getPropertyFilterFlags().clear();
        return;
      case GenModelPackage.GEN_FEATURE__PROPERTY_DESCRIPTION:
        setPropertyDescription(PROPERTY_DESCRIPTION_EDEFAULT);
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
    result.append(", propertyCategory: ");
    result.append(propertyCategory);
    result.append(", propertyFilterFlags: ");
    result.append(propertyFilterFlags);
    result.append(", propertyDescription: ");
    result.append(propertyDescription);
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
    String result = isBooleanType() ? "is" + capName : "get" + ("Class".equals(capName) ? "Class_" : capName);

    GenClass rootImplementsInterface = getGenModel().getRootImplementsInterfaceGenClass();
    if (rootImplementsInterface != null && !rootImplementsInterface.isEObject())
    {
      for (Iterator i = rootImplementsInterface.getAllGenOperations().iterator(); i.hasNext(); )
      {
        GenOperation genOperation = (GenOperation)i.next();
        if (genOperation.getName().equals(result) && 
              genOperation.getGenParameters().isEmpty() && 
              !genOperation.getReturnType().equals(getType()))
        {
          result = result + "_";
          break;
        }
      }
    }

    return result;
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

  public boolean isFlag()
  {
    return isBooleanType() && !isVolatile();
  }

  public boolean isESetFlag()
  {
    return isUnsettable() && !isListType() && !isVolatile();
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
    if (eType instanceof EDataType)
    {
      GenDataType genDataType = (GenDataType)findGenClassifier(eType);
      return genDataType.getStaticValue(defaultString);
    }

    return "null";
  }

  public boolean isEnumType()
  {
    return !isListType() && getEcoreFeature().getEType() instanceof EEnum;
  }

  public boolean isEnumBasedType()
  {
    return getEcoreFeature().getEType() instanceof EEnum;
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

  public GenClass getGenClassType()
  {
    EClassifier eType = getEcoreFeature().getEType();
    return eType instanceof EClass ? findGenClass((EClass)eType) : null;
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

  // Like isStringType(), but still returns true even if multiplicity-many.
  //
  public boolean isStringBasedType()
  {
    return getInstanceClass(getEcoreFeature().getEType()) == java.lang.String.class;
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
    return getPrimitiveValueFunction(getEcoreFeature().getEType());
  }

  public String getLowerBound()
  {
    return String.valueOf(getEcoreFeature().getLowerBound());
  }

  public String getUpperBound()
  {
    return String.valueOf(getEcoreFeature().getUpperBound());
  }

  public String getContainerClass()
  {
    return getGenClass().isDocumentRoot() ? "null" : getGenClass().getImportedInterfaceName() + ".class";
  }

  public String getDerivedFlag()
  {
    String result = !getEcoreFeature().isDerived() ? "!" : "";
    return result + "IS_DERIVED";
  }

  public String getOrderedFlag()
  {
    String result = !getEcoreFeature().isOrdered() ? "!" : "";
    return result + "IS_ORDERED";
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

  public boolean isDerived()
  {
    return getEcoreFeature().isDerived();
  }

  public boolean hasDelegateFeature()
  {
    EStructuralFeature ecoreFeature = getEcoreFeature();
    EClass ecoreClass = ecoreFeature.getEContainingClass();
    EStructuralFeature mixedFeature = getExtendedMetaData().getMixedFeature(ecoreClass);
    return 
      (mixedFeature != null && mixedFeature != ecoreFeature) ||
      getExtendedMetaData().getGroup(ecoreFeature) != null;
  }

  public GenFeature getDelegateFeature()
  {
    EStructuralFeature ecoreFeature = getEcoreFeature();
    EClass ecoreClass = ecoreFeature.getEContainingClass();
    EStructuralFeature eStructuralFeature = getExtendedMetaData().getGroup(ecoreFeature);
    if (eStructuralFeature == null)
    {
      eStructuralFeature = getExtendedMetaData().getMixedFeature(ecoreClass);
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

  /**
   * It considers mixed types, model groups, subsitution groups and wildcards.
   */
  public List/*of GenFeature*/ getDelegatedFeatures()
  {
    if (!isFeatureMapType()) return Collections.EMPTY_LIST;

    GenClass genClass = getGenClass();
    List delegated = new ArrayList();

    ExtendedMetaData extendedMetaData = getExtendedMetaData();
    if (genClass.getMixedGenFeature() == this)
    {
       delegated.add(findGenFeature(XMLTypePackage.eINSTANCE.getXMLTypeDocumentRoot_Comment()));
       delegated.add(findGenFeature(XMLTypePackage.eINSTANCE.getXMLTypeDocumentRoot_Text()));

       if (!genClass.isDocumentRoot())
       {
         delegated.add(findGenFeature(XMLTypePackage.eINSTANCE.getXMLTypeDocumentRoot_CDATA()));
       }

       for (Iterator iter = genClass.getGenFeatures().iterator(); iter.hasNext(); )
       {
         GenFeature otherFeature = (GenFeature)iter.next();
         if (otherFeature != this && otherFeature.isDerived() &&
             extendedMetaData.getGroup(otherFeature.getEcoreFeature()) == null)
         {
           delegated.add(otherFeature);
         }
       }
    }
    else
    {
      switch (extendedMetaData.getFeatureKind(getEcoreFeature()))
      {
        case ExtendedMetaData.GROUP_FEATURE:
        {
          Set allDelegated = new HashSet();
          Set qNames = new HashSet();
          for (Iterator i = genClass.getGenFeatures().iterator(); i.hasNext(); )
          {
            GenFeature otherFeature = (GenFeature)i.next();
            if (otherFeature != this && otherFeature.isDerived() && 
                extendedMetaData.getGroup(otherFeature.getEcoreFeature()) == getEcoreFeature())
            { 
              if (otherFeature.isChangeable())
              {
                delegated.add(otherFeature);
                qNames.add
                  (extendedMetaData.getNamespace(otherFeature.getEcoreFeature()) + "#" + 
                     extendedMetaData.getName(otherFeature.getEcoreFeature()));
              }
              allDelegated.add(otherFeature.getEcoreFeature());
            }
          }
          
          for (Iterator i = getGenModel().getAllGenAndUsedGenPackagesWithClassifiers().iterator(); i.hasNext(); )
          {
            GenPackage genPackage = (GenPackage)i.next(); 
            if (genPackage.hasDocumentRoot())
            {
              GenClass documentRoot = findGenClass(extendedMetaData.getDocumentRoot(genPackage.getEcorePackage()));
              for (Iterator j = documentRoot.getGenFeatures().iterator(); j.hasNext(); )
              {
                GenFeature otherFeature = (GenFeature)j.next();
                if (otherFeature != this && 
                      otherFeature.isChangeable() &&
                      otherFeature.isDerived() &&
                      allDelegated.contains(extendedMetaData.getAffiliation(genClass.getEcoreClass(), otherFeature.getEcoreFeature())))
                {
                  if (qNames.add
                        (extendedMetaData.getNamespace(otherFeature.getEcoreFeature()) + "#" + 
                           extendedMetaData.getName(otherFeature.getEcoreFeature())))
                  {
                    delegated.add(otherFeature);
                  }
                }
              }
            }
          }
            
          break;
        }
        case ExtendedMetaData.ATTRIBUTE_WILDCARD_FEATURE:
        case ExtendedMetaData.ELEMENT_WILDCARD_FEATURE:
        {
          for (Iterator i = getGenModel().getAllGenAndUsedGenPackagesWithClassifiers().iterator(); i.hasNext(); )
          {
            GenPackage genPackage = (GenPackage)i.next(); 
            if (genPackage.hasDocumentRoot())
            {
              GenClass documentRoot = findGenClass(extendedMetaData.getDocumentRoot(genPackage.getEcorePackage()));
              for (Iterator j = documentRoot.getGenFeatures().iterator(); j.hasNext(); )
              {
                GenFeature otherFeature = (GenFeature)j.next();
                if (otherFeature != this && 
                      otherFeature.isChangeable() &&
                      otherFeature.isDerived() &&
                      extendedMetaData.getAffiliation(genClass.getEcoreClass(), otherFeature.getEcoreFeature()) == getEcoreFeature())
                {
                  delegated.add(otherFeature);
                }
              }
            }
          }
          break;
        }
      }
    }
   /* 
    else if (extendedMetaData.getFeatureKind(getEcoreFeature()) == ExtendedMetaData.GROUP_FEATURE)
    {
      for (Iterator iter = genClass.getGenFeatures().iterator(); iter.hasNext(); )
      {
        GenFeature otherFeature = (GenFeature)iter.next();
        if (otherFeature != this && otherFeature.isDerived() && otherFeature.isChangeable() &&
            extendedMetaData.getGroup(otherFeature.getEcoreFeature()) == getEcoreFeature())
        {
          delegated.add(otherFeature);
        }
      }
    }
    */

    List result = new ArrayList();
    for (Iterator iter = delegated.iterator(); iter.hasNext(); )
    {
      GenFeature feature = (GenFeature)iter.next();
      if (feature.isFeatureMapType())
      {
        result.addAll(feature.getDelegatedFeatures());
      }
      else
      {
        result.add(feature);
      }
    }
    return result;
  }

  public String getCreateChildValueLiteral()
  {
    String result = getDefaultValue();

    if (result == null)
    {
      Class c = getEcoreFeature().getEType().getInstanceClass();

      if (c == Boolean.TYPE || c == Boolean.class)
      {
        result = "\"false\"";
      }
      else if (c == String.class)
      {
        result = "\"\"";
      }
      else if (c == Character.class)
      {
        result = "\"0\"";
      }
      else if (c == Byte.TYPE || c == Short.TYPE || c == Integer.TYPE || c == Long.TYPE || c == Float.TYPE || c == Double.TYPE ||
               (c != null && Number.class.isAssignableFrom(c)))
      {
        result = "\"0\"";
      }
    }
    return result;
  }

  public boolean isSuppressedGetVisibility()
  {
    return EcoreUtil.isSuppressedVisibility(getEcoreFeature(), EcoreUtil.GET);
  }

  public boolean isSuppressedSetVisibility()
  {
    return EcoreUtil.isSuppressedVisibility(getEcoreFeature(), EcoreUtil.SET);
  }

  public boolean isSuppressedIsSetVisibility()
  {
    return EcoreUtil.isSuppressedVisibility(getEcoreFeature(), EcoreUtil.IS_SET);
  }

  public boolean isSuppressedUnsetVisibility()
  {
    return EcoreUtil.isSuppressedVisibility(getEcoreFeature(), EcoreUtil.UNSET);
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
        setCreateChild(isChildren() && isChangeable());
        setNotify(isChildren());
      }
      else if (isFeatureMapType())
      {
        setProperty(GenPropertyKind.NONE_LITERAL);
        setChildren(!hasDelegateFeature());
        setCreateChild(isChildren() && isChangeable());
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

  AnnotationFilter DEFAULT_GEN_FEATURE_ANNOTATION_FILTER = 
    new AnnotationFilterImpl()
    {
      public boolean accept(EModelElement eModelElement, String source, String key, String value)
      {
        return 
          super.accept(eModelElement, source, key, value) &&
            !(GenModelPackage.eNS_URI.equals(source) && 
                ("suppressedSetVisibility".equals(key) ||
                   "suppressedGetVisibility".equals(key) ||
                   "suppressedIsSetVisibility".equals(key) ||
                   "suppressedUnsetVisibility".equals(key)));
      }
    };

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

      // We don't want keyType and valueType on a map type specification in a package interface.
      // But, we also use qualified model information when defining a feature with suppressed get accessor
      // on the interface, and we do want to include these properties in that case.
      //
      if ((!qualified || isSuppressedGetVisibility()) && !eReference.isContainer())
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
      if (upperBound > 1 || upperBound < -1)
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

    if (!eStructuralFeature.isOrdered())
    {
      appendModelSetting(result, qualified, "ordered", "false");
    }

    if (isSuppressedGetVisibility())
    {
      appendModelSetting(result, qualified, "suppressedGetVisibility", "true");
    }
    if (isSuppressedSetVisibility())
    {
      appendModelSetting(result, qualified, "suppressedSetVisibility", "true");
    }
    if (isSuppressedIsSetVisibility())
    {
      appendModelSetting(result, qualified, "suppressedIsSetVisibility", "true");
    }
    if (isSuppressedUnsetVisibility())
    {
      appendModelSetting(result, qualified, "suppressedUnsetVisibility", "true");
    }

    appendAnnotationInfo(result, getEcoreFeature(), DEFAULT_GEN_FEATURE_ANNOTATION_FILTER);

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
    setChildren(oldGenFeatureVersion.isChildren());
    setCreateChild(oldGenFeatureVersion.isCreateChild());
    setPropertyCategory(oldGenFeatureVersion.getPropertyCategory());
    setPropertyDescription(oldGenFeatureVersion.getPropertyDescription());

    getPropertyFilterFlags().addAll(oldGenFeatureVersion.getPropertyFilterFlags());
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

  public boolean isField()
  {
    return !isContainer() && !isVolatile();
  }

  public boolean isESetField()
  {
    return !isContainer() && !isListType() && isUnsettable() && !isVolatile();
  }

  public boolean isGet()
  {
    return true;
  }

  public boolean isBasicGet()
  {
    return !getGenModel().isReflectiveDelegation() && isResolveProxies() && !isListType();
  }

  public boolean isBasicSet()
  {
    return !getGenModel().isReflectiveDelegation() && !isListType()
      && (isBidirectional() && !isContainer() && !isVolatile() || isContains());
  }

  public boolean isSet()
  {
    return !isListType() && isChangeable();
  }

  public boolean isBasicUnset()
  {
    return isUnsettable() && isChangeable() && !isListType() && isReferenceType() && (isBidirectional() || isContains());
  }

  public boolean isUnset()
  {
    return isUnsettable() && isChangeable();
  }

  public boolean isIsSet()
  {
    return isUnsettable();
  }

} //GenFeatureImpl
