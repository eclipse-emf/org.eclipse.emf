/**
 * Copyright (c) 2013 Eclipse contributors and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package org.eclipse.emf.test.common.reification.impl;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;

import org.eclipse.emf.test.common.reification.BoundedGenericContainer;
import org.eclipse.emf.test.common.reification.Container;
import org.eclipse.emf.test.common.reification.Medium;
import org.eclipse.emf.test.common.reification.ReificationPackage;
import org.eclipse.emf.test.common.reification.Root;
import org.eclipse.emf.test.common.reification.UnboundedGenericContainer;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Root</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.emf.test.common.reification.impl.RootImpl#getName <em>Name</em>}</li>
 *   <li>{@link org.eclipse.emf.test.common.reification.impl.RootImpl#getContainer <em>Container</em>}</li>
 *   <li>{@link org.eclipse.emf.test.common.reification.impl.RootImpl#getUnboundedGenericContainerWithRawType <em>Unbounded Generic Container With Raw Type</em>}</li>
 *   <li>{@link org.eclipse.emf.test.common.reification.impl.RootImpl#getUnboundedGenericContainerWithWildcard <em>Unbounded Generic Container With Wildcard</em>}</li>
 *   <li>{@link org.eclipse.emf.test.common.reification.impl.RootImpl#getUnboundedGenericContainerWithSuper <em>Unbounded Generic Container With Super</em>}</li>
 *   <li>{@link org.eclipse.emf.test.common.reification.impl.RootImpl#getUnboundedGenericContainerWithExtends <em>Unbounded Generic Container With Extends</em>}</li>
 *   <li>{@link org.eclipse.emf.test.common.reification.impl.RootImpl#getBoundedGenericContainerWithRawType <em>Bounded Generic Container With Raw Type</em>}</li>
 *   <li>{@link org.eclipse.emf.test.common.reification.impl.RootImpl#getBoundedGenericContainerWithWildcard <em>Bounded Generic Container With Wildcard</em>}</li>
 *   <li>{@link org.eclipse.emf.test.common.reification.impl.RootImpl#getBoundedGenericContainerWithSuper <em>Bounded Generic Container With Super</em>}</li>
 *   <li>{@link org.eclipse.emf.test.common.reification.impl.RootImpl#getBoundedGenericContainerWithExtends <em>Bounded Generic Container With Extends</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
@SuppressWarnings("rawtypes")
public class RootImpl extends EObjectImpl implements Root
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
   * The cached value of the '{@link #getContainer() <em>Container</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getContainer()
   * @generated
   * @ordered
   */
  protected Container container;

  /**
   * The cached value of the '{@link #getUnboundedGenericContainerWithRawType() <em>Unbounded Generic Container With Raw Type</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getUnboundedGenericContainerWithRawType()
   * @generated
   * @ordered
   */
  protected UnboundedGenericContainer unboundedGenericContainerWithRawType;

  /**
   * The cached value of the '{@link #getUnboundedGenericContainerWithWildcard() <em>Unbounded Generic Container With Wildcard</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getUnboundedGenericContainerWithWildcard()
   * @generated
   * @ordered
   */
  protected UnboundedGenericContainer<?> unboundedGenericContainerWithWildcard;

  /**
   * The cached value of the '{@link #getUnboundedGenericContainerWithSuper() <em>Unbounded Generic Container With Super</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getUnboundedGenericContainerWithSuper()
   * @generated
   * @ordered
   */
  protected UnboundedGenericContainer<? super Medium> unboundedGenericContainerWithSuper;

  /**
   * The cached value of the '{@link #getUnboundedGenericContainerWithExtends() <em>Unbounded Generic Container With Extends</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getUnboundedGenericContainerWithExtends()
   * @generated
   * @ordered
   */
  protected UnboundedGenericContainer<? extends Medium> unboundedGenericContainerWithExtends;

  /**
   * The cached value of the '{@link #getBoundedGenericContainerWithRawType() <em>Bounded Generic Container With Raw Type</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getBoundedGenericContainerWithRawType()
   * @generated
   * @ordered
   */
  protected BoundedGenericContainer boundedGenericContainerWithRawType;

  /**
   * The cached value of the '{@link #getBoundedGenericContainerWithWildcard() <em>Bounded Generic Container With Wildcard</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getBoundedGenericContainerWithWildcard()
   * @generated
   * @ordered
   */
  protected BoundedGenericContainer<?> boundedGenericContainerWithWildcard;

  /**
   * The cached value of the '{@link #getBoundedGenericContainerWithSuper() <em>Bounded Generic Container With Super</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getBoundedGenericContainerWithSuper()
   * @generated
   * @ordered
   */
  protected BoundedGenericContainer<? super Medium> boundedGenericContainerWithSuper;

  /**
   * The cached value of the '{@link #getBoundedGenericContainerWithExtends() <em>Bounded Generic Container With Extends</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getBoundedGenericContainerWithExtends()
   * @generated
   * @ordered
   */
  protected BoundedGenericContainer<? extends Medium> boundedGenericContainerWithExtends;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected RootImpl()
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
    return ReificationPackage.Literals.ROOT;
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
      eNotify(new ENotificationImpl(this, Notification.SET, ReificationPackage.ROOT__NAME, oldName, name));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public Container getContainer()
  {
    return container;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetContainer(Container newContainer, NotificationChain msgs)
  {
    Container oldContainer = container;
    container = newContainer;
    if (eNotificationRequired())
    {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, ReificationPackage.ROOT__CONTAINER, oldContainer, newContainer);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setContainer(Container newContainer)
  {
    if (newContainer != container)
    {
      NotificationChain msgs = null;
      if (container != null)
        msgs = ((InternalEObject)container).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - ReificationPackage.ROOT__CONTAINER, null, msgs);
      if (newContainer != null)
        msgs = ((InternalEObject)newContainer).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - ReificationPackage.ROOT__CONTAINER, null, msgs);
      msgs = basicSetContainer(newContainer, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, ReificationPackage.ROOT__CONTAINER, newContainer, newContainer));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public UnboundedGenericContainer getUnboundedGenericContainerWithRawType()
  {
    return unboundedGenericContainerWithRawType;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetUnboundedGenericContainerWithRawType(UnboundedGenericContainer newUnboundedGenericContainerWithRawType, NotificationChain msgs)
  {
    UnboundedGenericContainer oldUnboundedGenericContainerWithRawType = unboundedGenericContainerWithRawType;
    unboundedGenericContainerWithRawType = newUnboundedGenericContainerWithRawType;
    if (eNotificationRequired())
    {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, ReificationPackage.ROOT__UNBOUNDED_GENERIC_CONTAINER_WITH_RAW_TYPE, oldUnboundedGenericContainerWithRawType, newUnboundedGenericContainerWithRawType);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setUnboundedGenericContainerWithRawType(UnboundedGenericContainer newUnboundedGenericContainerWithRawType)
  {
    if (newUnboundedGenericContainerWithRawType != unboundedGenericContainerWithRawType)
    {
      NotificationChain msgs = null;
      if (unboundedGenericContainerWithRawType != null)
        msgs = ((InternalEObject)unboundedGenericContainerWithRawType).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - ReificationPackage.ROOT__UNBOUNDED_GENERIC_CONTAINER_WITH_RAW_TYPE, null, msgs);
      if (newUnboundedGenericContainerWithRawType != null)
        msgs = ((InternalEObject)newUnboundedGenericContainerWithRawType).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - ReificationPackage.ROOT__UNBOUNDED_GENERIC_CONTAINER_WITH_RAW_TYPE, null, msgs);
      msgs = basicSetUnboundedGenericContainerWithRawType(newUnboundedGenericContainerWithRawType, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, ReificationPackage.ROOT__UNBOUNDED_GENERIC_CONTAINER_WITH_RAW_TYPE, newUnboundedGenericContainerWithRawType, newUnboundedGenericContainerWithRawType));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public UnboundedGenericContainer<?> getUnboundedGenericContainerWithWildcard()
  {
    return unboundedGenericContainerWithWildcard;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetUnboundedGenericContainerWithWildcard(UnboundedGenericContainer<?> newUnboundedGenericContainerWithWildcard, NotificationChain msgs)
  {
    UnboundedGenericContainer<?> oldUnboundedGenericContainerWithWildcard = unboundedGenericContainerWithWildcard;
    unboundedGenericContainerWithWildcard = newUnboundedGenericContainerWithWildcard;
    if (eNotificationRequired())
    {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, ReificationPackage.ROOT__UNBOUNDED_GENERIC_CONTAINER_WITH_WILDCARD, oldUnboundedGenericContainerWithWildcard, newUnboundedGenericContainerWithWildcard);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setUnboundedGenericContainerWithWildcard(UnboundedGenericContainer<?> newUnboundedGenericContainerWithWildcard)
  {
    if (newUnboundedGenericContainerWithWildcard != unboundedGenericContainerWithWildcard)
    {
      NotificationChain msgs = null;
      if (unboundedGenericContainerWithWildcard != null)
        msgs = ((InternalEObject)unboundedGenericContainerWithWildcard).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - ReificationPackage.ROOT__UNBOUNDED_GENERIC_CONTAINER_WITH_WILDCARD, null, msgs);
      if (newUnboundedGenericContainerWithWildcard != null)
        msgs = ((InternalEObject)newUnboundedGenericContainerWithWildcard).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - ReificationPackage.ROOT__UNBOUNDED_GENERIC_CONTAINER_WITH_WILDCARD, null, msgs);
      msgs = basicSetUnboundedGenericContainerWithWildcard(newUnboundedGenericContainerWithWildcard, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, ReificationPackage.ROOT__UNBOUNDED_GENERIC_CONTAINER_WITH_WILDCARD, newUnboundedGenericContainerWithWildcard, newUnboundedGenericContainerWithWildcard));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public UnboundedGenericContainer<? super Medium> getUnboundedGenericContainerWithSuper()
  {
    return unboundedGenericContainerWithSuper;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetUnboundedGenericContainerWithSuper(UnboundedGenericContainer<? super Medium> newUnboundedGenericContainerWithSuper, NotificationChain msgs)
  {
    UnboundedGenericContainer<? super Medium> oldUnboundedGenericContainerWithSuper = unboundedGenericContainerWithSuper;
    unboundedGenericContainerWithSuper = newUnboundedGenericContainerWithSuper;
    if (eNotificationRequired())
    {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, ReificationPackage.ROOT__UNBOUNDED_GENERIC_CONTAINER_WITH_SUPER, oldUnboundedGenericContainerWithSuper, newUnboundedGenericContainerWithSuper);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setUnboundedGenericContainerWithSuper(UnboundedGenericContainer<? super Medium> newUnboundedGenericContainerWithSuper)
  {
    if (newUnboundedGenericContainerWithSuper != unboundedGenericContainerWithSuper)
    {
      NotificationChain msgs = null;
      if (unboundedGenericContainerWithSuper != null)
        msgs = ((InternalEObject)unboundedGenericContainerWithSuper).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - ReificationPackage.ROOT__UNBOUNDED_GENERIC_CONTAINER_WITH_SUPER, null, msgs);
      if (newUnboundedGenericContainerWithSuper != null)
        msgs = ((InternalEObject)newUnboundedGenericContainerWithSuper).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - ReificationPackage.ROOT__UNBOUNDED_GENERIC_CONTAINER_WITH_SUPER, null, msgs);
      msgs = basicSetUnboundedGenericContainerWithSuper(newUnboundedGenericContainerWithSuper, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, ReificationPackage.ROOT__UNBOUNDED_GENERIC_CONTAINER_WITH_SUPER, newUnboundedGenericContainerWithSuper, newUnboundedGenericContainerWithSuper));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public UnboundedGenericContainer<? extends Medium> getUnboundedGenericContainerWithExtends()
  {
    return unboundedGenericContainerWithExtends;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetUnboundedGenericContainerWithExtends(UnboundedGenericContainer<? extends Medium> newUnboundedGenericContainerWithExtends, NotificationChain msgs)
  {
    UnboundedGenericContainer<? extends Medium> oldUnboundedGenericContainerWithExtends = unboundedGenericContainerWithExtends;
    unboundedGenericContainerWithExtends = newUnboundedGenericContainerWithExtends;
    if (eNotificationRequired())
    {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, ReificationPackage.ROOT__UNBOUNDED_GENERIC_CONTAINER_WITH_EXTENDS, oldUnboundedGenericContainerWithExtends, newUnboundedGenericContainerWithExtends);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setUnboundedGenericContainerWithExtends(UnboundedGenericContainer<? extends Medium> newUnboundedGenericContainerWithExtends)
  {
    if (newUnboundedGenericContainerWithExtends != unboundedGenericContainerWithExtends)
    {
      NotificationChain msgs = null;
      if (unboundedGenericContainerWithExtends != null)
        msgs = ((InternalEObject)unboundedGenericContainerWithExtends).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - ReificationPackage.ROOT__UNBOUNDED_GENERIC_CONTAINER_WITH_EXTENDS, null, msgs);
      if (newUnboundedGenericContainerWithExtends != null)
        msgs = ((InternalEObject)newUnboundedGenericContainerWithExtends).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - ReificationPackage.ROOT__UNBOUNDED_GENERIC_CONTAINER_WITH_EXTENDS, null, msgs);
      msgs = basicSetUnboundedGenericContainerWithExtends(newUnboundedGenericContainerWithExtends, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, ReificationPackage.ROOT__UNBOUNDED_GENERIC_CONTAINER_WITH_EXTENDS, newUnboundedGenericContainerWithExtends, newUnboundedGenericContainerWithExtends));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public BoundedGenericContainer getBoundedGenericContainerWithRawType()
  {
    return boundedGenericContainerWithRawType;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetBoundedGenericContainerWithRawType(BoundedGenericContainer newBoundedGenericContainerWithRawType, NotificationChain msgs)
  {
    BoundedGenericContainer oldBoundedGenericContainerWithRawType = boundedGenericContainerWithRawType;
    boundedGenericContainerWithRawType = newBoundedGenericContainerWithRawType;
    if (eNotificationRequired())
    {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, ReificationPackage.ROOT__BOUNDED_GENERIC_CONTAINER_WITH_RAW_TYPE, oldBoundedGenericContainerWithRawType, newBoundedGenericContainerWithRawType);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setBoundedGenericContainerWithRawType(BoundedGenericContainer newBoundedGenericContainerWithRawType)
  {
    if (newBoundedGenericContainerWithRawType != boundedGenericContainerWithRawType)
    {
      NotificationChain msgs = null;
      if (boundedGenericContainerWithRawType != null)
        msgs = ((InternalEObject)boundedGenericContainerWithRawType).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - ReificationPackage.ROOT__BOUNDED_GENERIC_CONTAINER_WITH_RAW_TYPE, null, msgs);
      if (newBoundedGenericContainerWithRawType != null)
        msgs = ((InternalEObject)newBoundedGenericContainerWithRawType).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - ReificationPackage.ROOT__BOUNDED_GENERIC_CONTAINER_WITH_RAW_TYPE, null, msgs);
      msgs = basicSetBoundedGenericContainerWithRawType(newBoundedGenericContainerWithRawType, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, ReificationPackage.ROOT__BOUNDED_GENERIC_CONTAINER_WITH_RAW_TYPE, newBoundedGenericContainerWithRawType, newBoundedGenericContainerWithRawType));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public BoundedGenericContainer<?> getBoundedGenericContainerWithWildcard()
  {
    return boundedGenericContainerWithWildcard;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetBoundedGenericContainerWithWildcard(BoundedGenericContainer<?> newBoundedGenericContainerWithWildcard, NotificationChain msgs)
  {
    BoundedGenericContainer<?> oldBoundedGenericContainerWithWildcard = boundedGenericContainerWithWildcard;
    boundedGenericContainerWithWildcard = newBoundedGenericContainerWithWildcard;
    if (eNotificationRequired())
    {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, ReificationPackage.ROOT__BOUNDED_GENERIC_CONTAINER_WITH_WILDCARD, oldBoundedGenericContainerWithWildcard, newBoundedGenericContainerWithWildcard);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setBoundedGenericContainerWithWildcard(BoundedGenericContainer<?> newBoundedGenericContainerWithWildcard)
  {
    if (newBoundedGenericContainerWithWildcard != boundedGenericContainerWithWildcard)
    {
      NotificationChain msgs = null;
      if (boundedGenericContainerWithWildcard != null)
        msgs = ((InternalEObject)boundedGenericContainerWithWildcard).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - ReificationPackage.ROOT__BOUNDED_GENERIC_CONTAINER_WITH_WILDCARD, null, msgs);
      if (newBoundedGenericContainerWithWildcard != null)
        msgs = ((InternalEObject)newBoundedGenericContainerWithWildcard).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - ReificationPackage.ROOT__BOUNDED_GENERIC_CONTAINER_WITH_WILDCARD, null, msgs);
      msgs = basicSetBoundedGenericContainerWithWildcard(newBoundedGenericContainerWithWildcard, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, ReificationPackage.ROOT__BOUNDED_GENERIC_CONTAINER_WITH_WILDCARD, newBoundedGenericContainerWithWildcard, newBoundedGenericContainerWithWildcard));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public BoundedGenericContainer<? super Medium> getBoundedGenericContainerWithSuper()
  {
    return boundedGenericContainerWithSuper;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetBoundedGenericContainerWithSuper(BoundedGenericContainer<? super Medium> newBoundedGenericContainerWithSuper, NotificationChain msgs)
  {
    BoundedGenericContainer<? super Medium> oldBoundedGenericContainerWithSuper = boundedGenericContainerWithSuper;
    boundedGenericContainerWithSuper = newBoundedGenericContainerWithSuper;
    if (eNotificationRequired())
    {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, ReificationPackage.ROOT__BOUNDED_GENERIC_CONTAINER_WITH_SUPER, oldBoundedGenericContainerWithSuper, newBoundedGenericContainerWithSuper);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setBoundedGenericContainerWithSuper(BoundedGenericContainer<? super Medium> newBoundedGenericContainerWithSuper)
  {
    if (newBoundedGenericContainerWithSuper != boundedGenericContainerWithSuper)
    {
      NotificationChain msgs = null;
      if (boundedGenericContainerWithSuper != null)
        msgs = ((InternalEObject)boundedGenericContainerWithSuper).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - ReificationPackage.ROOT__BOUNDED_GENERIC_CONTAINER_WITH_SUPER, null, msgs);
      if (newBoundedGenericContainerWithSuper != null)
        msgs = ((InternalEObject)newBoundedGenericContainerWithSuper).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - ReificationPackage.ROOT__BOUNDED_GENERIC_CONTAINER_WITH_SUPER, null, msgs);
      msgs = basicSetBoundedGenericContainerWithSuper(newBoundedGenericContainerWithSuper, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, ReificationPackage.ROOT__BOUNDED_GENERIC_CONTAINER_WITH_SUPER, newBoundedGenericContainerWithSuper, newBoundedGenericContainerWithSuper));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public BoundedGenericContainer<? extends Medium> getBoundedGenericContainerWithExtends()
  {
    return boundedGenericContainerWithExtends;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetBoundedGenericContainerWithExtends(BoundedGenericContainer<? extends Medium> newBoundedGenericContainerWithExtends, NotificationChain msgs)
  {
    BoundedGenericContainer<? extends Medium> oldBoundedGenericContainerWithExtends = boundedGenericContainerWithExtends;
    boundedGenericContainerWithExtends = newBoundedGenericContainerWithExtends;
    if (eNotificationRequired())
    {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, ReificationPackage.ROOT__BOUNDED_GENERIC_CONTAINER_WITH_EXTENDS, oldBoundedGenericContainerWithExtends, newBoundedGenericContainerWithExtends);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setBoundedGenericContainerWithExtends(BoundedGenericContainer<? extends Medium> newBoundedGenericContainerWithExtends)
  {
    if (newBoundedGenericContainerWithExtends != boundedGenericContainerWithExtends)
    {
      NotificationChain msgs = null;
      if (boundedGenericContainerWithExtends != null)
        msgs = ((InternalEObject)boundedGenericContainerWithExtends).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - ReificationPackage.ROOT__BOUNDED_GENERIC_CONTAINER_WITH_EXTENDS, null, msgs);
      if (newBoundedGenericContainerWithExtends != null)
        msgs = ((InternalEObject)newBoundedGenericContainerWithExtends).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - ReificationPackage.ROOT__BOUNDED_GENERIC_CONTAINER_WITH_EXTENDS, null, msgs);
      msgs = basicSetBoundedGenericContainerWithExtends(newBoundedGenericContainerWithExtends, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, ReificationPackage.ROOT__BOUNDED_GENERIC_CONTAINER_WITH_EXTENDS, newBoundedGenericContainerWithExtends, newBoundedGenericContainerWithExtends));
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
      case ReificationPackage.ROOT__CONTAINER:
        return basicSetContainer(null, msgs);
      case ReificationPackage.ROOT__UNBOUNDED_GENERIC_CONTAINER_WITH_RAW_TYPE:
        return basicSetUnboundedGenericContainerWithRawType(null, msgs);
      case ReificationPackage.ROOT__UNBOUNDED_GENERIC_CONTAINER_WITH_WILDCARD:
        return basicSetUnboundedGenericContainerWithWildcard(null, msgs);
      case ReificationPackage.ROOT__UNBOUNDED_GENERIC_CONTAINER_WITH_SUPER:
        return basicSetUnboundedGenericContainerWithSuper(null, msgs);
      case ReificationPackage.ROOT__UNBOUNDED_GENERIC_CONTAINER_WITH_EXTENDS:
        return basicSetUnboundedGenericContainerWithExtends(null, msgs);
      case ReificationPackage.ROOT__BOUNDED_GENERIC_CONTAINER_WITH_RAW_TYPE:
        return basicSetBoundedGenericContainerWithRawType(null, msgs);
      case ReificationPackage.ROOT__BOUNDED_GENERIC_CONTAINER_WITH_WILDCARD:
        return basicSetBoundedGenericContainerWithWildcard(null, msgs);
      case ReificationPackage.ROOT__BOUNDED_GENERIC_CONTAINER_WITH_SUPER:
        return basicSetBoundedGenericContainerWithSuper(null, msgs);
      case ReificationPackage.ROOT__BOUNDED_GENERIC_CONTAINER_WITH_EXTENDS:
        return basicSetBoundedGenericContainerWithExtends(null, msgs);
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
      case ReificationPackage.ROOT__NAME:
        return getName();
      case ReificationPackage.ROOT__CONTAINER:
        return getContainer();
      case ReificationPackage.ROOT__UNBOUNDED_GENERIC_CONTAINER_WITH_RAW_TYPE:
        return getUnboundedGenericContainerWithRawType();
      case ReificationPackage.ROOT__UNBOUNDED_GENERIC_CONTAINER_WITH_WILDCARD:
        return getUnboundedGenericContainerWithWildcard();
      case ReificationPackage.ROOT__UNBOUNDED_GENERIC_CONTAINER_WITH_SUPER:
        return getUnboundedGenericContainerWithSuper();
      case ReificationPackage.ROOT__UNBOUNDED_GENERIC_CONTAINER_WITH_EXTENDS:
        return getUnboundedGenericContainerWithExtends();
      case ReificationPackage.ROOT__BOUNDED_GENERIC_CONTAINER_WITH_RAW_TYPE:
        return getBoundedGenericContainerWithRawType();
      case ReificationPackage.ROOT__BOUNDED_GENERIC_CONTAINER_WITH_WILDCARD:
        return getBoundedGenericContainerWithWildcard();
      case ReificationPackage.ROOT__BOUNDED_GENERIC_CONTAINER_WITH_SUPER:
        return getBoundedGenericContainerWithSuper();
      case ReificationPackage.ROOT__BOUNDED_GENERIC_CONTAINER_WITH_EXTENDS:
        return getBoundedGenericContainerWithExtends();
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
      case ReificationPackage.ROOT__NAME:
        setName((String)newValue);
        return;
      case ReificationPackage.ROOT__CONTAINER:
        setContainer((Container)newValue);
        return;
      case ReificationPackage.ROOT__UNBOUNDED_GENERIC_CONTAINER_WITH_RAW_TYPE:
        setUnboundedGenericContainerWithRawType((UnboundedGenericContainer)newValue);
        return;
      case ReificationPackage.ROOT__UNBOUNDED_GENERIC_CONTAINER_WITH_WILDCARD:
        setUnboundedGenericContainerWithWildcard((UnboundedGenericContainer<?>)newValue);
        return;
      case ReificationPackage.ROOT__UNBOUNDED_GENERIC_CONTAINER_WITH_SUPER:
        setUnboundedGenericContainerWithSuper((UnboundedGenericContainer<? super Medium>)newValue);
        return;
      case ReificationPackage.ROOT__UNBOUNDED_GENERIC_CONTAINER_WITH_EXTENDS:
        setUnboundedGenericContainerWithExtends((UnboundedGenericContainer<? extends Medium>)newValue);
        return;
      case ReificationPackage.ROOT__BOUNDED_GENERIC_CONTAINER_WITH_RAW_TYPE:
        setBoundedGenericContainerWithRawType((BoundedGenericContainer)newValue);
        return;
      case ReificationPackage.ROOT__BOUNDED_GENERIC_CONTAINER_WITH_WILDCARD:
        setBoundedGenericContainerWithWildcard((BoundedGenericContainer<?>)newValue);
        return;
      case ReificationPackage.ROOT__BOUNDED_GENERIC_CONTAINER_WITH_SUPER:
        setBoundedGenericContainerWithSuper((BoundedGenericContainer<? super Medium>)newValue);
        return;
      case ReificationPackage.ROOT__BOUNDED_GENERIC_CONTAINER_WITH_EXTENDS:
        setBoundedGenericContainerWithExtends((BoundedGenericContainer<? extends Medium>)newValue);
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
      case ReificationPackage.ROOT__NAME:
        setName(NAME_EDEFAULT);
        return;
      case ReificationPackage.ROOT__CONTAINER:
        setContainer((Container)null);
        return;
      case ReificationPackage.ROOT__UNBOUNDED_GENERIC_CONTAINER_WITH_RAW_TYPE:
        setUnboundedGenericContainerWithRawType((UnboundedGenericContainer)null);
        return;
      case ReificationPackage.ROOT__UNBOUNDED_GENERIC_CONTAINER_WITH_WILDCARD:
        setUnboundedGenericContainerWithWildcard((UnboundedGenericContainer<?>)null);
        return;
      case ReificationPackage.ROOT__UNBOUNDED_GENERIC_CONTAINER_WITH_SUPER:
        setUnboundedGenericContainerWithSuper((UnboundedGenericContainer<? super Medium>)null);
        return;
      case ReificationPackage.ROOT__UNBOUNDED_GENERIC_CONTAINER_WITH_EXTENDS:
        setUnboundedGenericContainerWithExtends((UnboundedGenericContainer<? extends Medium>)null);
        return;
      case ReificationPackage.ROOT__BOUNDED_GENERIC_CONTAINER_WITH_RAW_TYPE:
        setBoundedGenericContainerWithRawType((BoundedGenericContainer)null);
        return;
      case ReificationPackage.ROOT__BOUNDED_GENERIC_CONTAINER_WITH_WILDCARD:
        setBoundedGenericContainerWithWildcard((BoundedGenericContainer<?>)null);
        return;
      case ReificationPackage.ROOT__BOUNDED_GENERIC_CONTAINER_WITH_SUPER:
        setBoundedGenericContainerWithSuper((BoundedGenericContainer<? super Medium>)null);
        return;
      case ReificationPackage.ROOT__BOUNDED_GENERIC_CONTAINER_WITH_EXTENDS:
        setBoundedGenericContainerWithExtends((BoundedGenericContainer<? extends Medium>)null);
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
      case ReificationPackage.ROOT__NAME:
        return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
      case ReificationPackage.ROOT__CONTAINER:
        return container != null;
      case ReificationPackage.ROOT__UNBOUNDED_GENERIC_CONTAINER_WITH_RAW_TYPE:
        return unboundedGenericContainerWithRawType != null;
      case ReificationPackage.ROOT__UNBOUNDED_GENERIC_CONTAINER_WITH_WILDCARD:
        return unboundedGenericContainerWithWildcard != null;
      case ReificationPackage.ROOT__UNBOUNDED_GENERIC_CONTAINER_WITH_SUPER:
        return unboundedGenericContainerWithSuper != null;
      case ReificationPackage.ROOT__UNBOUNDED_GENERIC_CONTAINER_WITH_EXTENDS:
        return unboundedGenericContainerWithExtends != null;
      case ReificationPackage.ROOT__BOUNDED_GENERIC_CONTAINER_WITH_RAW_TYPE:
        return boundedGenericContainerWithRawType != null;
      case ReificationPackage.ROOT__BOUNDED_GENERIC_CONTAINER_WITH_WILDCARD:
        return boundedGenericContainerWithWildcard != null;
      case ReificationPackage.ROOT__BOUNDED_GENERIC_CONTAINER_WITH_SUPER:
        return boundedGenericContainerWithSuper != null;
      case ReificationPackage.ROOT__BOUNDED_GENERIC_CONTAINER_WITH_EXTENDS:
        return boundedGenericContainerWithExtends != null;
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
    result.append(" (name: ");
    result.append(name);
    result.append(')');
    return result.toString();
  }

} //RootImpl
