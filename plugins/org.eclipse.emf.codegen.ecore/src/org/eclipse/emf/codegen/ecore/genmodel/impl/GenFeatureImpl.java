/**
 * Copyright (c) 2002-2010 IBM Corporation and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 * 
 * Contributors: 
 *   IBM - Initial API and implementation
 */
package org.eclipse.emf.codegen.ecore.genmodel.impl;


import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.eclipse.emf.codegen.ecore.genmodel.GenClass;
import org.eclipse.emf.codegen.ecore.genmodel.GenDataType;
import org.eclipse.emf.codegen.ecore.genmodel.GenFeature;
import org.eclipse.emf.codegen.ecore.genmodel.GenJDKLevel;
import org.eclipse.emf.codegen.ecore.genmodel.GenModel;
import org.eclipse.emf.codegen.ecore.genmodel.GenModelPackage;
import org.eclipse.emf.codegen.ecore.genmodel.GenOperation;
import org.eclipse.emf.codegen.ecore.genmodel.GenPackage;
import org.eclipse.emf.codegen.ecore.genmodel.GenPropertyKind;
import org.eclipse.emf.codegen.util.CodeGenUtil;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EAnnotation;
import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EGenericType;
import org.eclipse.emf.ecore.EModelElement;
import org.eclipse.emf.ecore.EReference;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.ETypeParameter;
import org.eclipse.emf.ecore.ETypedElement;
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
 * </p>
 * <ul>
 *   <li>{@link org.eclipse.emf.codegen.ecore.genmodel.impl.GenFeatureImpl#getProperty <em>Property</em>}</li>
 *   <li>{@link org.eclipse.emf.codegen.ecore.genmodel.impl.GenFeatureImpl#isNotify <em>Notify</em>}</li>
 *   <li>{@link org.eclipse.emf.codegen.ecore.genmodel.impl.GenFeatureImpl#isChildren <em>Children</em>}</li>
 *   <li>{@link org.eclipse.emf.codegen.ecore.genmodel.impl.GenFeatureImpl#isCreateChild <em>Create Child</em>}</li>
 *   <li>{@link org.eclipse.emf.codegen.ecore.genmodel.impl.GenFeatureImpl#getPropertyCategory <em>Property Category</em>}</li>
 *   <li>{@link org.eclipse.emf.codegen.ecore.genmodel.impl.GenFeatureImpl#getPropertyFilterFlags <em>Property Filter Flags</em>}</li>
 *   <li>{@link org.eclipse.emf.codegen.ecore.genmodel.impl.GenFeatureImpl#getPropertyDescription <em>Property Description</em>}</li>
 *   <li>{@link org.eclipse.emf.codegen.ecore.genmodel.impl.GenFeatureImpl#isPropertyMultiLine <em>Property Multi Line</em>}</li>
 *   <li>{@link org.eclipse.emf.codegen.ecore.genmodel.impl.GenFeatureImpl#isPropertySortChoices <em>Property Sort Choices</em>}</li>
 *   <li>{@link org.eclipse.emf.codegen.ecore.genmodel.impl.GenFeatureImpl#getGenClass <em>Gen Class</em>}</li>
 *   <li>{@link org.eclipse.emf.codegen.ecore.genmodel.impl.GenFeatureImpl#getEcoreFeature <em>Ecore Feature</em>}</li>
 *   <li>{@link org.eclipse.emf.codegen.ecore.genmodel.impl.GenFeatureImpl#isSuppressedGetVisibility <em>Suppressed Get Visibility</em>}</li>
 *   <li>{@link org.eclipse.emf.codegen.ecore.genmodel.impl.GenFeatureImpl#isSuppressedSetVisibility <em>Suppressed Set Visibility</em>}</li>
 *   <li>{@link org.eclipse.emf.codegen.ecore.genmodel.impl.GenFeatureImpl#isSuppressedIsSetVisibility <em>Suppressed Is Set Visibility</em>}</li>
 *   <li>{@link org.eclipse.emf.codegen.ecore.genmodel.impl.GenFeatureImpl#isSuppressedUnsetVisibility <em>Suppressed Unset Visibility</em>}</li>
 *   <li>{@link org.eclipse.emf.codegen.ecore.genmodel.impl.GenFeatureImpl#getGet <em>Get</em>}</li>
 *   <li>{@link org.eclipse.emf.codegen.ecore.genmodel.impl.GenFeatureImpl#getPropertyEditorFactory <em>Property Editor Factory</em>}</li>
 * </ul>
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
  protected boolean createChildESet;

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
  protected EList<String> propertyFilterFlags;

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
   * The default value of the '{@link #isPropertyMultiLine() <em>Property Multi Line</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #isPropertyMultiLine()
   * @generated
   * @ordered
   */
  protected static final boolean PROPERTY_MULTI_LINE_EDEFAULT = false;

  /**
   * The cached value of the '{@link #isPropertyMultiLine() <em>Property Multi Line</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #isPropertyMultiLine()
   * @generated
   * @ordered
   */
  protected boolean propertyMultiLine = PROPERTY_MULTI_LINE_EDEFAULT;

  /**
   * The default value of the '{@link #isPropertySortChoices() <em>Property Sort Choices</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #isPropertySortChoices()
   * @generated
   * @ordered
   */
  protected static final boolean PROPERTY_SORT_CHOICES_EDEFAULT = false;

  /**
   * The cached value of the '{@link #isPropertySortChoices() <em>Property Sort Choices</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #isPropertySortChoices()
   * @generated
   * @ordered
   */
  protected boolean propertySortChoices = PROPERTY_SORT_CHOICES_EDEFAULT;

  /**
   * The cached value of the '{@link #getEcoreFeature() <em>Ecore Feature</em>}' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getEcoreFeature()
   * @generated
   * @ordered
   */
  protected EStructuralFeature ecoreFeature;

  /**
   * The default value of the '{@link #isSuppressedGetVisibility() <em>Suppressed Get Visibility</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #isSuppressedGetVisibility()
   * @since 2.14
   * @generated
   * @ordered
   */
  protected static final boolean SUPPRESSED_GET_VISIBILITY_EDEFAULT = false;

  /**
   * The cached value of the '{@link #isSuppressedGetVisibility() <em>Suppressed Get Visibility</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #isSuppressedGetVisibility()
   * @since 2.14
   * @generated
   * @ordered
   */
  protected boolean suppressedGetVisibility = SUPPRESSED_GET_VISIBILITY_EDEFAULT;

  /**
   * This is true if the Suppressed Get Visibility attribute has been set.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @since 2.14
   * @generated
   * @ordered
   */
  protected boolean suppressedGetVisibilityESet;

  /**
   * The default value of the '{@link #isSuppressedSetVisibility() <em>Suppressed Set Visibility</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #isSuppressedSetVisibility()
   * @since 2.14
   * @generated
   * @ordered
   */
  protected static final boolean SUPPRESSED_SET_VISIBILITY_EDEFAULT = false;

  /**
   * The cached value of the '{@link #isSuppressedSetVisibility() <em>Suppressed Set Visibility</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #isSuppressedSetVisibility()
   * @since 2.14
   * @generated
   * @ordered
   */
  protected boolean suppressedSetVisibility = SUPPRESSED_SET_VISIBILITY_EDEFAULT;

  /**
   * This is true if the Suppressed Set Visibility attribute has been set.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @since 2.14
   * @generated
   * @ordered
   */
  protected boolean suppressedSetVisibilityESet;

  /**
   * The default value of the '{@link #isSuppressedIsSetVisibility() <em>Suppressed Is Set Visibility</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #isSuppressedIsSetVisibility()
   * @since 2.14
   * @generated
   * @ordered
   */
  protected static final boolean SUPPRESSED_IS_SET_VISIBILITY_EDEFAULT = false;

  /**
   * The cached value of the '{@link #isSuppressedIsSetVisibility() <em>Suppressed Is Set Visibility</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #isSuppressedIsSetVisibility()
   * @since 2.14
   * @generated
   * @ordered
   */
  protected boolean suppressedIsSetVisibility = SUPPRESSED_IS_SET_VISIBILITY_EDEFAULT;

  /**
   * This is true if the Suppressed Is Set Visibility attribute has been set.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @since 2.14
   * @generated
   * @ordered
   */
  protected boolean suppressedIsSetVisibilityESet;

  /**
   * The default value of the '{@link #isSuppressedUnsetVisibility() <em>Suppressed Unset Visibility</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #isSuppressedUnsetVisibility()
   * @since 2.14
   * @generated
   * @ordered
   */
  protected static final boolean SUPPRESSED_UNSET_VISIBILITY_EDEFAULT = false;

  /**
   * The cached value of the '{@link #isSuppressedUnsetVisibility() <em>Suppressed Unset Visibility</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #isSuppressedUnsetVisibility()
   * @since 2.14
   * @generated
   * @ordered
   */
  protected boolean suppressedUnsetVisibility = SUPPRESSED_UNSET_VISIBILITY_EDEFAULT;

  /**
   * This is true if the Suppressed Unset Visibility attribute has been set.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @since 2.14
   * @generated
   * @ordered
   */
  protected boolean suppressedUnsetVisibilityESet;

  /**
   * The default value of the '{@link #getGet() <em>Get</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getGet()
   * @since 2.14
   * @generated
   * @ordered
   */
  protected static final String GET_EDEFAULT = null;

  /**
   * The cached value of the '{@link #getGet() <em>Get</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getGet()
   * @since 2.14
   * @generated
   * @ordered
   */
  protected String get = GET_EDEFAULT;

  /**
   * This is true if the Get attribute has been set.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @since 2.14
   * @generated
   * @ordered
   */
  protected boolean getESet;

  /**
   * The default value of the '{@link #getPropertyEditorFactory() <em>Property Editor Factory</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getPropertyEditorFactory()
   * @since 2.14
   * @generated
   * @ordered
   */
  protected static final String PROPERTY_EDITOR_FACTORY_EDEFAULT = null;

  /**
   * The cached value of the '{@link #getPropertyEditorFactory() <em>Property Editor Factory</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getPropertyEditorFactory()
   * @since 2.14
   * @generated
   * @ordered
   */
  protected String propertyEditorFactory = PROPERTY_EDITOR_FACTORY_EDEFAULT;

  /**
   * This is true if the Property Editor Factory attribute has been set.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @since 2.14
   * @generated
   * @ordered
   */
  protected boolean propertyEditorFactoryESet;

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
  @Override
  protected EClass eStaticClass()
  {
    return GenModelPackage.Literals.GEN_FEATURE;
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
  public EList<String> getPropertyFilterFlags()
  {
    if (propertyFilterFlags == null)
    {
      propertyFilterFlags = new EDataTypeUniqueEList<String>(String.class, this, GenModelPackage.GEN_FEATURE__PROPERTY_FILTER_FLAGS);
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

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public boolean isPropertyMultiLine()
  {
    return propertyMultiLine;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setPropertyMultiLine(boolean newPropertyMultiLine)
  {
    boolean oldPropertyMultiLine = propertyMultiLine;
    propertyMultiLine = newPropertyMultiLine;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, GenModelPackage.GEN_FEATURE__PROPERTY_MULTI_LINE, oldPropertyMultiLine, propertyMultiLine));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public boolean isPropertySortChoices()
  {
    return propertySortChoices;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setPropertySortChoices(boolean newPropertySortChoices)
  {
    boolean oldPropertySortChoices = propertySortChoices;
    propertySortChoices = newPropertySortChoices;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, GenModelPackage.GEN_FEATURE__PROPERTY_SORT_CHOICES, oldPropertySortChoices, propertySortChoices));
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
    if (eContainerFeatureID() != GenModelPackage.GEN_FEATURE__GEN_CLASS) return null;
    return (GenClass)eInternalContainer();
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetGenClass(GenClass newGenClass, NotificationChain msgs)
  {
    msgs = eBasicSetContainer((InternalEObject)newGenClass, GenModelPackage.GEN_FEATURE__GEN_CLASS, msgs);
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setGenClass(GenClass newGenClass)
  {
    if (newGenClass != eInternalContainer() || (eContainerFeatureID() != GenModelPackage.GEN_FEATURE__GEN_CLASS && newGenClass != null))
    {
      if (EcoreUtil.isAncestor(this, newGenClass))
        throw new IllegalArgumentException("Recursive containment not allowed for " + toString());
      NotificationChain msgs = null;
      if (eInternalContainer() != null)
        msgs = eBasicRemoveFromContainer(msgs);
      if (newGenClass != null)
        msgs = ((InternalEObject)newGenClass).eInverseAdd(this, GenModelPackage.GEN_CLASS__GEN_FEATURES, GenClass.class, msgs);
      msgs = basicSetGenClass(newGenClass, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, GenModelPackage.GEN_FEATURE__GEN_CLASS, newGenClass, newGenClass));
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
      InternalEObject oldEcoreFeature = (InternalEObject)ecoreFeature;
      ecoreFeature = (EStructuralFeature)eResolveProxy(oldEcoreFeature);
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
  @Override
  public NotificationChain eInverseAdd(InternalEObject otherEnd, int featureID, NotificationChain msgs)
  {
    switch (featureID)
    {
      case GenModelPackage.GEN_FEATURE__GEN_CLASS:
        if (eInternalContainer() != null)
          msgs = eBasicRemoveFromContainer(msgs);
        return basicSetGenClass((GenClass)otherEnd, msgs);
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
      case GenModelPackage.GEN_FEATURE__GEN_CLASS:
        return basicSetGenClass(null, msgs);
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
      case GenModelPackage.GEN_FEATURE__GEN_CLASS:
        return eInternalContainer().eInverseRemove(this, GenModelPackage.GEN_CLASS__GEN_FEATURES, GenClass.class, msgs);
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
      case GenModelPackage.GEN_FEATURE__PROPERTY:
        return getProperty();
      case GenModelPackage.GEN_FEATURE__NOTIFY:
        return isNotify();
      case GenModelPackage.GEN_FEATURE__CHILDREN:
        return isChildren();
      case GenModelPackage.GEN_FEATURE__CREATE_CHILD:
        return isCreateChild();
      case GenModelPackage.GEN_FEATURE__PROPERTY_CATEGORY:
        return getPropertyCategory();
      case GenModelPackage.GEN_FEATURE__PROPERTY_FILTER_FLAGS:
        return getPropertyFilterFlags();
      case GenModelPackage.GEN_FEATURE__PROPERTY_DESCRIPTION:
        return getPropertyDescription();
      case GenModelPackage.GEN_FEATURE__PROPERTY_MULTI_LINE:
        return isPropertyMultiLine();
      case GenModelPackage.GEN_FEATURE__PROPERTY_SORT_CHOICES:
        return isPropertySortChoices();
      case GenModelPackage.GEN_FEATURE__GEN_CLASS:
        return getGenClass();
      case GenModelPackage.GEN_FEATURE__ECORE_FEATURE:
        if (resolve) return getEcoreFeature();
        return basicGetEcoreFeature();
      case GenModelPackage.GEN_FEATURE__SUPPRESSED_GET_VISIBILITY:
        return isSuppressedGetVisibility();
      case GenModelPackage.GEN_FEATURE__SUPPRESSED_SET_VISIBILITY:
        return isSuppressedSetVisibility();
      case GenModelPackage.GEN_FEATURE__SUPPRESSED_IS_SET_VISIBILITY:
        return isSuppressedIsSetVisibility();
      case GenModelPackage.GEN_FEATURE__SUPPRESSED_UNSET_VISIBILITY:
        return isSuppressedUnsetVisibility();
      case GenModelPackage.GEN_FEATURE__GET:
        return getGet();
      case GenModelPackage.GEN_FEATURE__PROPERTY_EDITOR_FACTORY:
        return getPropertyEditorFactory();
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
      case GenModelPackage.GEN_FEATURE__PROPERTY:
        setProperty((GenPropertyKind)newValue);
        return;
      case GenModelPackage.GEN_FEATURE__NOTIFY:
        setNotify((Boolean)newValue);
        return;
      case GenModelPackage.GEN_FEATURE__CHILDREN:
        setChildren((Boolean)newValue);
        return;
      case GenModelPackage.GEN_FEATURE__CREATE_CHILD:
        setCreateChild((Boolean)newValue);
        return;
      case GenModelPackage.GEN_FEATURE__PROPERTY_CATEGORY:
        setPropertyCategory((String)newValue);
        return;
      case GenModelPackage.GEN_FEATURE__PROPERTY_FILTER_FLAGS:
        getPropertyFilterFlags().clear();
        getPropertyFilterFlags().addAll((Collection<? extends String>)newValue);
        return;
      case GenModelPackage.GEN_FEATURE__PROPERTY_DESCRIPTION:
        setPropertyDescription((String)newValue);
        return;
      case GenModelPackage.GEN_FEATURE__PROPERTY_MULTI_LINE:
        setPropertyMultiLine((Boolean)newValue);
        return;
      case GenModelPackage.GEN_FEATURE__PROPERTY_SORT_CHOICES:
        setPropertySortChoices((Boolean)newValue);
        return;
      case GenModelPackage.GEN_FEATURE__GEN_CLASS:
        setGenClass((GenClass)newValue);
        return;
      case GenModelPackage.GEN_FEATURE__ECORE_FEATURE:
        setEcoreFeature((EStructuralFeature)newValue);
        return;
      case GenModelPackage.GEN_FEATURE__SUPPRESSED_GET_VISIBILITY:
        setSuppressedGetVisibility((Boolean)newValue);
        return;
      case GenModelPackage.GEN_FEATURE__SUPPRESSED_SET_VISIBILITY:
        setSuppressedSetVisibility((Boolean)newValue);
        return;
      case GenModelPackage.GEN_FEATURE__SUPPRESSED_IS_SET_VISIBILITY:
        setSuppressedIsSetVisibility((Boolean)newValue);
        return;
      case GenModelPackage.GEN_FEATURE__SUPPRESSED_UNSET_VISIBILITY:
        setSuppressedUnsetVisibility((Boolean)newValue);
        return;
      case GenModelPackage.GEN_FEATURE__GET:
        setGet((String)newValue);
        return;
      case GenModelPackage.GEN_FEATURE__PROPERTY_EDITOR_FACTORY:
        setPropertyEditorFactory((String)newValue);
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
      case GenModelPackage.GEN_FEATURE__PROPERTY_MULTI_LINE:
        setPropertyMultiLine(PROPERTY_MULTI_LINE_EDEFAULT);
        return;
      case GenModelPackage.GEN_FEATURE__PROPERTY_SORT_CHOICES:
        setPropertySortChoices(PROPERTY_SORT_CHOICES_EDEFAULT);
        return;
      case GenModelPackage.GEN_FEATURE__GEN_CLASS:
        setGenClass((GenClass)null);
        return;
      case GenModelPackage.GEN_FEATURE__ECORE_FEATURE:
        setEcoreFeature((EStructuralFeature)null);
        return;
      case GenModelPackage.GEN_FEATURE__SUPPRESSED_GET_VISIBILITY:
        unsetSuppressedGetVisibility();
        return;
      case GenModelPackage.GEN_FEATURE__SUPPRESSED_SET_VISIBILITY:
        unsetSuppressedSetVisibility();
        return;
      case GenModelPackage.GEN_FEATURE__SUPPRESSED_IS_SET_VISIBILITY:
        unsetSuppressedIsSetVisibility();
        return;
      case GenModelPackage.GEN_FEATURE__SUPPRESSED_UNSET_VISIBILITY:
        unsetSuppressedUnsetVisibility();
        return;
      case GenModelPackage.GEN_FEATURE__GET:
        unsetGet();
        return;
      case GenModelPackage.GEN_FEATURE__PROPERTY_EDITOR_FACTORY:
        unsetPropertyEditorFactory();
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
      case GenModelPackage.GEN_FEATURE__PROPERTY_MULTI_LINE:
        return propertyMultiLine != PROPERTY_MULTI_LINE_EDEFAULT;
      case GenModelPackage.GEN_FEATURE__PROPERTY_SORT_CHOICES:
        return propertySortChoices != PROPERTY_SORT_CHOICES_EDEFAULT;
      case GenModelPackage.GEN_FEATURE__GEN_CLASS:
        return getGenClass() != null;
      case GenModelPackage.GEN_FEATURE__ECORE_FEATURE:
        return ecoreFeature != null;
      case GenModelPackage.GEN_FEATURE__SUPPRESSED_GET_VISIBILITY:
        return isSetSuppressedGetVisibility();
      case GenModelPackage.GEN_FEATURE__SUPPRESSED_SET_VISIBILITY:
        return isSetSuppressedSetVisibility();
      case GenModelPackage.GEN_FEATURE__SUPPRESSED_IS_SET_VISIBILITY:
        return isSetSuppressedIsSetVisibility();
      case GenModelPackage.GEN_FEATURE__SUPPRESSED_UNSET_VISIBILITY:
        return isSetSuppressedUnsetVisibility();
      case GenModelPackage.GEN_FEATURE__GET:
        return isSetGet();
      case GenModelPackage.GEN_FEATURE__PROPERTY_EDITOR_FACTORY:
        return isSetPropertyEditorFactory();
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
    result.append(", propertyMultiLine: ");
    result.append(propertyMultiLine);
    result.append(", propertySortChoices: ");
    result.append(propertySortChoices);
    result.append(", suppressedGetVisibility: ");
    if (suppressedGetVisibilityESet) result.append(suppressedGetVisibility); else result.append("<unset>");
    result.append(", suppressedSetVisibility: ");
    if (suppressedSetVisibilityESet) result.append(suppressedSetVisibility); else result.append("<unset>");
    result.append(", suppressedIsSetVisibility: ");
    if (suppressedIsSetVisibilityESet) result.append(suppressedIsSetVisibility); else result.append("<unset>");
    result.append(", suppressedUnsetVisibility: ");
    if (suppressedUnsetVisibilityESet) result.append(suppressedUnsetVisibility); else result.append("<unset>");
    result.append(", get: ");
    if (getESet) result.append(get); else result.append("<unset>");
    result.append(", propertyEditorFactory: ");
    if (propertyEditorFactoryESet) result.append(propertyEditorFactory); else result.append("<unset>");
    result.append(')');
    return result.toString();
  }

  @Override
  public ETypedElement getEcoreTypedElement()
  {
    return getEcoreFeature();
  }

  @Override
  protected EModelElement basicGetEcoreModelElement()
  {
    return ecoreFeature;
  }

  @Override
  public String getName()
  {
    EStructuralFeature ecoreFeature = getEcoreFeature();
    return ecoreFeature == null || ecoreFeature.getName() == null ? "" : ecoreFeature.getName();
  }

  public String getCapName()
  {
    return capName(getName());
  }

  public String getAccessorName()
  {
    return isMapEntryFeature() ? "Typed" + getCapName() : getCapName();
  }

  public String getGetArrayAccessor()
  {
    String result = getGetAccessor();
    if (isListType() && !isFeatureMapType() && !isMapType() && getGenModel().isArrayAccessors())
    {
      result = result.substring(0, result.length() - 4);
    }
    return result;
  }

  private String getAccessor;

  public String getGetAccessor()
  {
    if (getAccessor == null)
    {
      String capName = getCapName();
      if (isMapEntryFeature())
      {
        getAccessor = "getTyped" + capName;
      }
      else
      {
        String result = isBooleanType() ? "is" + capName : "get" + ("Class".equals(capName) ? "Class_" : capName);
        if (isListType() && !isFeatureMapType() && !isMapType() && getGenModel().isArrayAccessors())
        {
          result += "List";
        }

        GenClass rootImplementsInterface = getGenModel().getRootImplementsInterfaceGenClass();
        GenClass context = getContext();
        if (rootImplementsInterface != null && !rootImplementsInterface.isEObject())
        {
          for (GenOperation genOperation : rootImplementsInterface.getAllGenOperations())
          {
            if (genOperation.getGenParameters().isEmpty() &&
                  genOperation.getName().equals(result) &&
                  !genOperation.getType(context).equals(getType(context)))
            {
              result = result + "_";
              break;
            }
          }
        }

        getAccessor = result;
      }
    }
    return getAccessor;
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

  public String getAsInternalEObject(String expression, boolean parenthesize)
  {
    GenClass typeGenClass = getTypeGenClass();
    if (typeGenClass != null && !typeGenClass.getGenModel().isSuppressInterfaces())
    {
      String cast = getGenModel().getImportedName("org.eclipse.emf.ecore.InternalEObject");
      StringBuilder result = new StringBuilder();
      if (parenthesize)
      {
        result.append('(');
      }
      result.append('(');
      result.append(cast);
      result.append(')');
      result.append(expression);
      if (parenthesize)
      {
        result.append(')');
      }
      return result.toString();
    }
    return expression;
  }

  public String getFormattedName()
  {
    return format(getCapName(), ' ', null, false, false);
  }

  public String getUpperName()
  {
    return CodeGenUtil.upperName(getName(), getGenModel().getLocale());
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

  public String getQualifiedFeatureAccessor()
  {
    return getGenPackage().isLiteralsInterface() ? getGenPackage().getImportedPackageInterfaceName() + ".Literals."
      + getGenClass().getFeatureID(this) : getQualifiedFeatureAccessorName() + "()";
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

  @Override
  protected boolean isEObjectExtensionType()
  {
    return isReferenceType() && super.isEObjectExtensionType();
  }

  @Override
  protected boolean isEObjectType()
  {
    return isReferenceType() && super.isEObjectType();
  }

  @Override
  public boolean isMapType()
  {
    return isContains() && super.isMapType();
  }

  public boolean isFlag()
  {
    return isBooleanType() && !isVolatile();
  }

  public boolean isESetFlag()
  {
    return isUnsettable() && !isListType() && !isVolatile();
  }

  public boolean isSetDefaultValue()
  {
    return getEcoreFeature().getDefaultValueLiteral() != null;
  }

  public String getDefaultValue()
  {
    if (!isSetDefaultValue()) return null;
    return Literals.toStringLiteral(getEcoreFeature().getDefaultValueLiteral(), getGenModel());
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

  protected boolean isMapEntryFeature()
  {
    return getGenClass().isMapEntry() && ("key".equals(getName()) || "value".equals(getName()));
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
    if (isBidirectional())
    {
      EReference eReverseFeature = ((EReference)getEcoreFeature()).getEOpposite();
      if (eReverseFeature != null)
      {
        EClass eReverseClass = (EClass)eReverseFeature.eContainer();
        GenClass genClass = findGenClass(eReverseClass);
        if (genClass != null)
        {
          for (GenFeature genFeature : genClass.getGenFeatures())
          {
            if (genFeature.getEcoreFeature() == eReverseFeature)
            {
              return genFeature;
            }
          }
        }
      }
    }
    return null;
  }

  public List<GenFeature> getKeys()
  {
    List<GenFeature> result = new ArrayList<GenFeature>();
    for (EAttribute eAttribute : ((EReference)getEcoreFeature()).getEKeys())
    {
      GenClass genClass = getTypeGenClass();
      for (GenFeature genFeature : genClass.getAllGenFeatures())
      {
        if (genFeature.getEcoreFeature() == eAttribute)
        {
          result.add(genFeature);
        }
      }
    }
    return result;
  }

  public String getContainerClass()
  {
    GenClass genClass = getGenClass();
    return genClass.isDocumentRoot() || genClass.isDynamic() ? "null" : genClass.getImportedInterfaceName() + ".class";
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
    return (!isContainer() && !isContains() || getGenModel().isContainmentProxies())&& 
           eStructuralFeature instanceof EReference && ((EReference)eStructuralFeature).isResolveProxies();
  }

  public boolean isVolatile()
  {
    // We treat the feature as volatile if it is volatile itself or if it is 
    // a reference whose opposite end is volatile, 
    // unless that opposite volatile reference delegates to a feature map,
    // in which case, a full implementation of the references can be generated.
    //
    EReference eReverseFeature = isReferenceType() ?
      ((EReference)getEcoreFeature()).getEOpposite() : null;

    return (getEcoreFeature().isVolatile() ||
            (eReverseFeature != null && eReverseFeature.isVolatile() && !getReverse().hasDelegateFeature()));
  }

  public boolean isChangeable()
  {
    return getEcoreFeature().isChangeable();
  }

  public boolean isUnsettable()
  {
    if (getGenModel().isSuppressUnsettable())
    {
      return false;
    }
    else
    {
      EStructuralFeature eStructuralFeature = getEcoreFeature();
      return eStructuralFeature.isUnsettable() && !isContainer();
    }
  }

  public boolean isID()
  {
    EStructuralFeature eStructuralFeature = getEcoreFeature();
    return eStructuralFeature instanceof EAttribute && ((EAttribute)eStructuralFeature).isID();
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
      (mixedFeature != null && mixedFeature != ecoreFeature && isPotentialDelegatingFeatureKind(getExtendedMetaData().getFeatureKind(ecoreFeature))) ||
      getExtendedMetaData().getGroup(ecoreFeature) != null;
  }
  
  private boolean isPotentialDelegatingFeatureKind(int featureKind)
  {
    switch (featureKind)
    {
      case ExtendedMetaData.SIMPLE_FEATURE:
      case ExtendedMetaData.ELEMENT_FEATURE:
      case ExtendedMetaData.ELEMENT_WILDCARD_FEATURE:
      case ExtendedMetaData.GROUP_FEATURE:
      {
        return true;
      }
      default:
      {
        return false;
      }
    }
  }

  public GenFeature getDelegateFeature()
  {
    EStructuralFeature ecoreFeature = getEcoreFeature();
    EClass ecoreClass = ecoreFeature.getEContainingClass();
    EStructuralFeature eStructuralFeature = getExtendedMetaData().getGroup(ecoreFeature);
    if (eStructuralFeature == null && isPotentialDelegatingFeatureKind(getExtendedMetaData().getFeatureKind(ecoreFeature)))
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
   * It considers mixed types, model groups, substitution groups and wildcards.
   */
  public List<GenFeature> getDelegatedFeatures()
  {
    return getDelegatedFeatures(getGenModel());
  }

  public List<GenFeature> getDelegatedFeatures(GenModel genModel)
  {
    if (!isFeatureMapType()) return Collections.emptyList();

    GenClass genClass = getGenClass();
    List<GenFeature> delegated = new ArrayList<GenFeature>();

    ExtendedMetaData extendedMetaData = genModel.getExtendedMetaData();
    if (genClass.getMixedGenFeature() == this)
    {
       delegated.add(findGenFeature(XMLTypePackage.Literals.XML_TYPE_DOCUMENT_ROOT__COMMENT));
       delegated.add(findGenFeature(XMLTypePackage.Literals.XML_TYPE_DOCUMENT_ROOT__TEXT));
       delegated.add(findGenFeature(XMLTypePackage.Literals.XML_TYPE_DOCUMENT_ROOT__PROCESSING_INSTRUCTION));

       if (!genClass.isDocumentRoot())
       {
         delegated.add(findGenFeature(XMLTypePackage.Literals.XML_TYPE_DOCUMENT_ROOT__CDATA));
       }

       for (GenFeature otherFeature : genClass.getGenFeatures())
       {
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
          Set<EStructuralFeature> allDelegated = new HashSet<EStructuralFeature>();
          Set<String> qNames = new HashSet<String>();
          for (GenFeature otherFeature : genClass.getGenFeatures())
          {
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
          
          for (GenPackage genPackage : genModel.getAllGenAndUsedGenPackagesWithClassifiers())
          {
            if (genPackage.hasDocumentRoot())
            {
              GenClass documentRoot = genPackage.getDocumentRoot();
              for (GenFeature otherFeature : documentRoot.getGenFeatures())
              {
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
          for (GenPackage genPackage : genModel.getAllGenAndUsedGenPackagesWithClassifiers())
          {
            if (genPackage.hasDocumentRoot())
            {
              GenClass documentRoot = genPackage.getDocumentRoot();
              for (GenFeature otherFeature : documentRoot.getGenFeatures())
              {
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

    List<GenFeature> result = new ArrayList<GenFeature>();
    for (GenFeature feature : delegated)
    {
      if (feature.isFeatureMapType())
      {
        result.addAll(feature.getDelegatedFeatures(genModel));
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
      Class<?> c = getEcoreFeature().getEType().getInstanceClass();

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

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @since 2.14
   * @generated
   */
  public boolean isSuppressedGetVisibilityGen()
  {
    return suppressedGetVisibility;
  }

  public boolean isSuppressedGetVisibility()
  {
    return isSetSuppressedGetVisibility() ? isSuppressedGetVisibilityGen() : EcoreUtil.isSuppressedVisibility(getEcoreFeature(), EcoreUtil.GET);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @since 2.14
   * @generated
   */
  public void setSuppressedGetVisibility(boolean newSuppressedGetVisibility)
  {
    boolean oldSuppressedGetVisibility = suppressedGetVisibility;
    suppressedGetVisibility = newSuppressedGetVisibility;
    boolean oldSuppressedGetVisibilityESet = suppressedGetVisibilityESet;
    suppressedGetVisibilityESet = true;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, GenModelPackage.GEN_FEATURE__SUPPRESSED_GET_VISIBILITY, oldSuppressedGetVisibility, suppressedGetVisibility, !oldSuppressedGetVisibilityESet));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @since 2.14
   * @generated
   */
  public boolean isSetSuppressedGetVisibility()
  {
    return suppressedGetVisibilityESet;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @since 2.14
   * @generated
   */
  public void unsetSuppressedGetVisibility()
  {
    boolean oldSuppressedGetVisibility = suppressedGetVisibility;
    boolean oldSuppressedGetVisibilityESet = suppressedGetVisibilityESet;
    suppressedGetVisibility = SUPPRESSED_GET_VISIBILITY_EDEFAULT;
    suppressedGetVisibilityESet = false;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.UNSET, GenModelPackage.GEN_FEATURE__SUPPRESSED_GET_VISIBILITY, oldSuppressedGetVisibility, SUPPRESSED_GET_VISIBILITY_EDEFAULT, oldSuppressedGetVisibilityESet));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @since 2.14
   * @generated
   */
  public boolean isSuppressedSetVisibilityGen()
  {
    return suppressedSetVisibility;
  }

  public boolean isSuppressedSetVisibility()
  {
    return isSetSuppressedSetVisibility() ?  isSuppressedSetVisibilityGen() : EcoreUtil.isSuppressedVisibility(getEcoreFeature(), EcoreUtil.SET);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @since 2.14
   * @generated
   */
  public void setSuppressedSetVisibility(boolean newSuppressedSetVisibility)
  {
    boolean oldSuppressedSetVisibility = suppressedSetVisibility;
    suppressedSetVisibility = newSuppressedSetVisibility;
    boolean oldSuppressedSetVisibilityESet = suppressedSetVisibilityESet;
    suppressedSetVisibilityESet = true;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, GenModelPackage.GEN_FEATURE__SUPPRESSED_SET_VISIBILITY, oldSuppressedSetVisibility, suppressedSetVisibility, !oldSuppressedSetVisibilityESet));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @since 2.14
   * @generated
   */
  public boolean isSetSuppressedSetVisibility()
  {
    return suppressedSetVisibilityESet;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @since 2.14
   * @generated
   */
  public void unsetSuppressedSetVisibility()
  {
    boolean oldSuppressedSetVisibility = suppressedSetVisibility;
    boolean oldSuppressedSetVisibilityESet = suppressedSetVisibilityESet;
    suppressedSetVisibility = SUPPRESSED_SET_VISIBILITY_EDEFAULT;
    suppressedSetVisibilityESet = false;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.UNSET, GenModelPackage.GEN_FEATURE__SUPPRESSED_SET_VISIBILITY, oldSuppressedSetVisibility, SUPPRESSED_SET_VISIBILITY_EDEFAULT, oldSuppressedSetVisibilityESet));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @since 2.14
   * @generated
   */
  public boolean isSuppressedIsSetVisibilityGen()
  {
    return suppressedIsSetVisibility;
  }

  public boolean isSuppressedIsSetVisibility()
  {
    return isSetSuppressedIsSetVisibility() ? isSuppressedIsSetVisibilityGen() : EcoreUtil.isSuppressedVisibility(getEcoreFeature(), EcoreUtil.IS_SET);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @since 2.14
   * @generated
   */
  public void setSuppressedIsSetVisibility(boolean newSuppressedIsSetVisibility)
  {
    boolean oldSuppressedIsSetVisibility = suppressedIsSetVisibility;
    suppressedIsSetVisibility = newSuppressedIsSetVisibility;
    boolean oldSuppressedIsSetVisibilityESet = suppressedIsSetVisibilityESet;
    suppressedIsSetVisibilityESet = true;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, GenModelPackage.GEN_FEATURE__SUPPRESSED_IS_SET_VISIBILITY, oldSuppressedIsSetVisibility, suppressedIsSetVisibility, !oldSuppressedIsSetVisibilityESet));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @since 2.14
   * @generated
   */
  public boolean isSetSuppressedIsSetVisibility()
  {
    return suppressedIsSetVisibilityESet;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @since 2.14
   * @generated
   */
  public void unsetSuppressedIsSetVisibility()
  {
    boolean oldSuppressedIsSetVisibility = suppressedIsSetVisibility;
    boolean oldSuppressedIsSetVisibilityESet = suppressedIsSetVisibilityESet;
    suppressedIsSetVisibility = SUPPRESSED_IS_SET_VISIBILITY_EDEFAULT;
    suppressedIsSetVisibilityESet = false;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.UNSET, GenModelPackage.GEN_FEATURE__SUPPRESSED_IS_SET_VISIBILITY, oldSuppressedIsSetVisibility, SUPPRESSED_IS_SET_VISIBILITY_EDEFAULT, oldSuppressedIsSetVisibilityESet));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @since 2.14
   * @generated
   */
  public boolean isSuppressedUnsetVisibilityGen()
  {
    return suppressedUnsetVisibility;
  }

  public boolean isSuppressedUnsetVisibility()
  {
    return isSetSuppressedUnsetVisibility() ? isSuppressedUnsetVisibilityGen() : EcoreUtil.isSuppressedVisibility(getEcoreFeature(), EcoreUtil.UNSET);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @since 2.14
   * @generated
   */
  public void setSuppressedUnsetVisibility(boolean newSuppressedUnsetVisibility)
  {
    boolean oldSuppressedUnsetVisibility = suppressedUnsetVisibility;
    suppressedUnsetVisibility = newSuppressedUnsetVisibility;
    boolean oldSuppressedUnsetVisibilityESet = suppressedUnsetVisibilityESet;
    suppressedUnsetVisibilityESet = true;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, GenModelPackage.GEN_FEATURE__SUPPRESSED_UNSET_VISIBILITY, oldSuppressedUnsetVisibility, suppressedUnsetVisibility, !oldSuppressedUnsetVisibilityESet));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @since 2.14
   * @generated
   */
  public boolean isSetSuppressedUnsetVisibility()
  {
    return suppressedUnsetVisibilityESet;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @since 2.14
   * @generated
   */
  public String getGet()
  {
    return get;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @since 2.14
   * @generated
   */
  public void setGet(String newGet)
  {
    String oldGet = get;
    get = newGet;
    boolean oldGetESet = getESet;
    getESet = true;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, GenModelPackage.GEN_FEATURE__GET, oldGet, get, !oldGetESet));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @since 2.14
   * @generated
   */
  public void unsetGet()
  {
    String oldGet = get;
    boolean oldGetESet = getESet;
    get = GET_EDEFAULT;
    getESet = false;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.UNSET, GenModelPackage.GEN_FEATURE__GET, oldGet, GET_EDEFAULT, oldGetESet));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @since 2.14
   * @generated
   */
  public boolean isSetGet()
  {
    return getESet;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @since 2.14
   * @generated
   */
  public String getPropertyEditorFactoryGen()
  {
    return propertyEditorFactory;
  }

  public String getPropertyEditorFactory()
  {
    if (isSetPropertyEditorFactory())
    {
      return getPropertyEditorFactoryGen();
    }
    else
    {
      EStructuralFeature ecoreFeature = getEcoreFeature();
      if (ecoreFeature != null)
      {
        String result = EcoreUtil.getAnnotation(ecoreFeature, GenModelPackage.eNS_URI, "propertyEditorFactory");
        if (result == null)
        {
          if (getGenModel() != null)
          {
            GenDataType genDataType = getTypeGenDataType();
            if (genDataType != null)
            {
              result = genDataType.getPropertyEditorFactory();
            }
          }

          EClassifier eType = ecoreFeature.getEType();
          if (eType != null)
          {
            result = EcoreUtil.getAnnotation(eType, GenModelPackage.eNS_URI, "propertyEditorFactory");
          }
        }
        return result;
      }
      else
      {
        return null;
      }
    }
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @since 2.14
   * @generated
   */
  public void setPropertyEditorFactory(String newPropertyEditorFactory)
  {
    String oldPropertyEditorFactory = propertyEditorFactory;
    propertyEditorFactory = newPropertyEditorFactory;
    boolean oldPropertyEditorFactoryESet = propertyEditorFactoryESet;
    propertyEditorFactoryESet = true;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, GenModelPackage.GEN_FEATURE__PROPERTY_EDITOR_FACTORY, oldPropertyEditorFactory, propertyEditorFactory, !oldPropertyEditorFactoryESet));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @since 2.14
   * @generated
   */
  public void unsetPropertyEditorFactory()
  {
    String oldPropertyEditorFactory = propertyEditorFactory;
    boolean oldPropertyEditorFactoryESet = propertyEditorFactoryESet;
    propertyEditorFactory = PROPERTY_EDITOR_FACTORY_EDEFAULT;
    propertyEditorFactoryESet = false;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.UNSET, GenModelPackage.GEN_FEATURE__PROPERTY_EDITOR_FACTORY, oldPropertyEditorFactory, PROPERTY_EDITOR_FACTORY_EDEFAULT, oldPropertyEditorFactoryESet));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @since 2.14
   * @generated
   */
  public boolean isSetPropertyEditorFactory()
  {
    return propertyEditorFactoryESet;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @since 2.14
   * @generated
   */
  public void unsetSuppressedUnsetVisibility()
  {
    boolean oldSuppressedUnsetVisibility = suppressedUnsetVisibility;
    boolean oldSuppressedUnsetVisibilityESet = suppressedUnsetVisibilityESet;
    suppressedUnsetVisibility = SUPPRESSED_UNSET_VISIBILITY_EDEFAULT;
    suppressedUnsetVisibilityESet = false;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.UNSET, GenModelPackage.GEN_FEATURE__SUPPRESSED_UNSET_VISIBILITY, oldSuppressedUnsetVisibility, SUPPRESSED_UNSET_VISIBILITY_EDEFAULT, oldSuppressedUnsetVisibilityESet));
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
        setChildren
          (eReference.isContainment() && 
             (getGenClass().isDocumentRoot() ? 
                getExtendedMetaData().getFeatureKind(eFeature) == ExtendedMetaData.ELEMENT_FEATURE : 
                !hasDelegateFeature()));
        setCreateChild(isChildren() && isChangeable());
        setNotify(isChildren());
      }
      else if (isFeatureMapType())
      {
        setProperty(GenPropertyKind.NONE_LITERAL);
        setChildren(!getGenClass().isDocumentRoot() && !hasDelegateFeature());
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

      if (getProperty() == GenPropertyKind.EDITABLE_LITERAL && isReferenceType())
      {
        setPropertySortChoices(true);
      }
    }
  }

  @Override
  public String getModelInfo()
  {
    return getModelInfo(false);
  }

  public String getQualifiedModelInfo()
  {
    return getModelInfo(true);
  }

  /**
   * @since 2.18
   */
  protected AnnotationFilter getFeatureAnnotationFilter()
  {
    return new AnnotationFilterImpl()
    {
      private final AnnotationFilter delegate = getAnnotationFilter();

      @Override
      public boolean accept(EModelElement eModelElement, String source, String key, String value)
      {
        return
          delegate.accept(eModelElement, source, key, value) &&
            !(GenModelPackage.eNS_URI.equals(source) &&
                ("suppressedSetVisibility".equals(key) ||
                   "suppressedGetVisibility".equals(key) ||
                   "suppressedIsSetVisibility".equals(key) ||
                   "suppressedUnsetVisibility".equals(key)));
      }
    };
  }

  public String getModelInfo(boolean qualified)
  {
    EStructuralFeature eStructuralFeature = getEcoreFeature();
    StringBuffer result = new StringBuffer();
    boolean defaultTransient = false;

    if (eStructuralFeature instanceof EReference)
    {
      EGenericType eGenericType = eStructuralFeature.getEGenericType();
      ETypeParameter eTypeParameter = eGenericType.getETypeParameter();
      if (eTypeParameter != null)
      {
        boolean needsKindAnnotation = true;
        for (EGenericType eBound : eTypeParameter.getEBounds())
        {
          if (eBound.getERawType() instanceof EClass)
          {
            needsKindAnnotation = false;
            break;
          }
        }
        if (needsKindAnnotation)
        {
          appendModelSetting(result, qualified, "kind", "reference");
        }
      }
    }

    // We don't want keyType and valueType on a map type specification in a package interface.
    // But, we also use qualified model information when defining a feature with suppressed get accessor
    // on the interface, and we do want to include these properties in that case.
    //
    String mapModelInfo = getMapModelInfo(qualified, (!qualified || isSuppressedGetVisibility()) && !isContainer());
    if (mapModelInfo != null)
    {
      result.append(mapModelInfo);
    }
    else
    {
      if (eStructuralFeature instanceof EReference)
      {
        if (qualified ||
              eStructuralFeature.isMany() && getEffectiveComplianceLevel().getValue() < GenJDKLevel.JDK50 || 
              hasReferenceToClassifierWithInstanceTypeName(eStructuralFeature.getEGenericType()))
        {
          appendModelSetting(result, qualified, "type", getEcoreType(eStructuralFeature.getEGenericType()));
        }

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
          if (reference.isResolveProxies() && getGenModel().isContainmentProxies())
          {
            appendModelSetting(result, qualified, "resolveProxies", "true");
          }
        }
        else if (!reference.isResolveProxies())
        {
          appendModelSetting(result, qualified, "resolveProxies", "false");
        }
        if (reference.isUnsettable())
        {
          appendModelSetting(result, qualified, "unsettable", "true");
        }

        if (!reference.getEKeys().isEmpty())
        {
          StringBuilder keys = new StringBuilder();
          for (EAttribute eKey : reference.getEKeys())
          {
            keys.append(eKey.getName());
            keys.append(' ');
          }
          appendModelSetting(result, qualified, "keys", keys.toString().trim());
        }
      }
      else if (eStructuralFeature instanceof EAttribute)
      {
        EAttribute attribute = (EAttribute) eStructuralFeature;
        if (eStructuralFeature.getDefaultValueLiteral() != null)
        {
          String literal = Literals.toStringLiteral(eStructuralFeature.getDefaultValueLiteral(), getGenModel());
          appendModelSetting(result, qualified, "default", literal.substring(1, literal.length() - 1));
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
        if (qualified || 
              eStructuralFeature.isMany() && getEffectiveComplianceLevel().getValue() < GenJDKLevel.JDK50 || 
              hasReferenceToClassifierWithInstanceTypeName(eStructuralFeature.getEGenericType()))
        {
          GenPackage genPackage = findGenPackage(eDataType.getEPackage());
          if (genPackage != null && (isFeatureMapType() || !genPackage.isEcorePackage() || qualified))
          {
            appendModelSetting(result, qualified, "dataType", getEcoreType(eStructuralFeature.getEGenericType()));
          }
        }
      }

      result.append(getMultiplicityModelInfo(qualified));
    }

    if (eStructuralFeature.isTransient() && !defaultTransient)
    {
      appendModelSetting(result, qualified, "transient", "true");
    }
    else if (!eStructuralFeature.isTransient() && defaultTransient)
    {
      appendModelSetting(result, qualified, "transient", "false");
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

    appendAnnotationInfo(result, qualified, eStructuralFeature, getFeatureAnnotationFilter());
    return result.toString().trim();
  }

  //
  // EMFEdit generation
  //

  public String getPropertyImageName()
  {
    EClassifier eType = getEcoreFeature().getEType();
    if (isPrimitiveType(eType))
    {
      Class<?> instanceClass = eType.getInstanceClass();
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
    setPropertyMultiLine(oldGenFeatureVersion.isPropertyMultiLine());
    setPropertySortChoices(oldGenFeatureVersion.isPropertySortChoices());

    getPropertyFilterFlags().addAll(oldGenFeatureVersion.getPropertyFilterFlags());
    reconcileGenAnnotations(oldGenFeatureVersion);

    if (oldGenFeatureVersion.eIsSet(GenModelPackage.Literals.GEN_TYPED_ELEMENT__DOCUMENTATION))
    {
      setDocumentation(oldGenFeatureVersion.getDocumentation());
    }

    if (oldGenFeatureVersion.eIsSet(GenModelPackage.Literals.GEN_FEATURE__SUPPRESSED_GET_VISIBILITY))
    {
      setSuppressedGetVisibility(oldGenFeatureVersion.isSuppressedGetVisibility());
    }

    if (oldGenFeatureVersion.eIsSet(GenModelPackage.Literals.GEN_FEATURE__SUPPRESSED_SET_VISIBILITY))
    {
      setSuppressedSetVisibility(oldGenFeatureVersion.isSuppressedSetVisibility());
    }

    if (oldGenFeatureVersion.eIsSet(GenModelPackage.Literals.GEN_FEATURE__SUPPRESSED_IS_SET_VISIBILITY))
    {
      setSuppressedIsSetVisibility(oldGenFeatureVersion.isSuppressedIsSetVisibility());
    }

    if (oldGenFeatureVersion.eIsSet(GenModelPackage.Literals.GEN_FEATURE__SUPPRESSED_UNSET_VISIBILITY))
    {
      setSuppressedUnsetVisibility(oldGenFeatureVersion.isSuppressedUnsetVisibility());
    }

    if (oldGenFeatureVersion.eIsSet(GenModelPackage.Literals.GEN_FEATURE__GET))
    {
      setGet((String)oldGenFeatureVersion.eGet(GenModelPackage.Literals.GEN_FEATURE__GET));
    }

    if (oldGenFeatureVersion.eIsSet(GenModelPackage.Literals.GEN_FEATURE__PROPERTY_EDITOR_FACTORY))
    {
      setPropertyEditorFactory(oldGenFeatureVersion.getPropertyEditorFactory());
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

  public boolean isField()
  {
    return !isContainer() && !isVolatile() && !hasSettingDelegate();
  }

  public boolean isESetField()
  {
    return !isContainer() && !isListType() && isUnsettable() && !isVolatile() && !hasSettingDelegate();
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
    return !getGenModel().isReflectiveDelegation() && !isListType() && (isChangeable() || !isContainer()) 
      && (isBidirectional() && !isVolatile() || isEffectiveContains());
  }

  public boolean isSet()
  {
    return !isListType() && isChangeable();
  }

  public boolean isBasicUnset()
  {
    return isUnsettable() && isChangeable() && !isListType() && isReferenceType() && (isBidirectional() || isEffectiveContains());
  }

  public boolean isUnset()
  {
    return isUnsettable() && isChangeable();
  }

  public boolean isIsSet()
  {
    return isUnsettable();
  }
  
  public boolean isEffectiveContains()
  {
    return isContains() && !getGenModel().isSuppressContainment();
  }

  public boolean hasEDefault()
  {
    return 
      getEcoreFeature() instanceof EAttribute && 
        (getEffectiveComplianceLevel().getValue() < GenJDKLevel.JDK50 ||
           (getEcoreFeature().getEType() != null &&
               getEcoreFeature().getEType().getETypeParameters().isEmpty() && 
               getEcoreFeature().getEGenericType().getETypeParameter() == null &&
               getEcoreFeature().getEType().getInstanceTypeName() == getEcoreFeature().getEType().getInstanceClassName()));
  }

  public String getEDefault()
  {
    return hasEDefault() ? getUpperName() + "_" + "EDEFAULT" :  "null";
  }

  public boolean isTested()
  {
    return isVolatile() || isDerived();
  }

  public boolean hasSettingDelegate()
  {
    EStructuralFeature ecoreFeature = getEcoreFeature();
    for (String settingDelegate : EcoreUtil.getSettingDelegates(getGenPackage().getEcorePackage()))
    {
      if (ecoreFeature.getEAnnotation(settingDelegate) != null)
        return true;
    }
    return false;
  }

  protected String getGetterBody()
  {
    if (isSetGet())
    {
      return getGet();
    }
    else
    {
      EStructuralFeature eStructuralFeature = getEcoreFeature();
      EAnnotation eAnnotation = eStructuralFeature.getEAnnotation(GenModelPackage.eNS_URI);
      return eAnnotation == null ? null : (String)eAnnotation.getDetails().get("get");
    }
  }

  public boolean hasGetterBody()
  {
    return getGetterBody() != null;
  }

  public String getGetterBody(String indentation)
  {
    return indentAndImport(getGetterBody(), indentation);
  }

  @Override
  protected String getLink()
  {
    GenClass genClass = getGenClass();
    return genClass.getRawQualifiedInterfaceName() + (!genClass.isMapEntry() && !isSuppressedGetVisibility() ? "#" + getGetAccessor() + "()" : "");
  }

  @Override
  public void clearCache()
  {
    super.clearCache();
    getAccessor = null;
  }
} //GenFeatureImpl
