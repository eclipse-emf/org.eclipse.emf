/**
 * Copyright (c) 2013 Eclipse contributors and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package org.eclipse.emf.test.common.reification.impl;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;

import org.eclipse.emf.ecore.impl.EFactoryImpl;

import org.eclipse.emf.ecore.plugin.EcorePlugin;

import org.eclipse.emf.test.common.reification.*;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Factory</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class ReificationFactoryImpl extends EFactoryImpl implements ReificationFactory
{
  /**
   * Creates the default factory implementation.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public static ReificationFactory init()
  {
    try
    {
      ReificationFactory theReificationFactory = (ReificationFactory)EPackage.Registry.INSTANCE.getEFactory("http://www.eclipse.org/emf/test/common/Reification"); 
      if (theReificationFactory != null)
      {
        return theReificationFactory;
      }
    }
    catch (Exception exception)
    {
      EcorePlugin.INSTANCE.log(exception);
    }
    return new ReificationFactoryImpl();
  }

  /**
   * Creates an instance of the factory.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public ReificationFactoryImpl()
  {
    super();
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public EObject create(EClass eClass)
  {
    switch (eClass.getClassifierID())
    {
      case ReificationPackage.ROOT: return createRoot();
      case ReificationPackage.VERY_LOW: return createVeryLow();
      case ReificationPackage.LOW: return createLow();
      case ReificationPackage.MEDIUM: return createMedium();
      case ReificationPackage.HIGH: return createHigh();
      case ReificationPackage.VERY_HIGH: return createVeryHigh();
      case ReificationPackage.CONTAINER: return createContainer();
      case ReificationPackage.UNBOUNDED_GENERIC_CONTAINER: return createUnboundedGenericContainer();
      case ReificationPackage.RAW_UNBOUNDED_CONTAINER: return createRawUnboundedContainer();
      case ReificationPackage.MEDIUM_UNBOUNDED_CONTAINER: return createMediumUnboundedContainer();
      case ReificationPackage.BOUNDED_GENERIC_CONTAINER: return createBoundedGenericContainer();
      case ReificationPackage.HIGH_BOUNDED_CONTAINER: return createHighBoundedContainer();
      case ReificationPackage.KEY_VALUE_PAIR: return createKeyValuePair();
      case ReificationPackage.RAW_KEY_VALUE_PAIR: return createRawKeyValuePair();
      case ReificationPackage.STRING_LIST_KEY_VALUE_PAIR: return createStringListKeyValuePair();
      case ReificationPackage.TLIST_KEY_VALUE_PAIR: return createTListKeyValuePair();
      case ReificationPackage.BOUNDED_TLIST_KEY_VALUE_PAIR: return createBoundedTListKeyValuePair();
      case ReificationPackage.HIGH_LIST_KEY_VALUE_PAIR: return createHighListKeyValuePair();
      case ReificationPackage.INTEGER_LIST_KEY_VALUE_PAIR: return createIntegerListKeyValuePair();
      case ReificationPackage.KEY_VALUE_PAIR_CONTAINER: return createKeyValuePairContainer();
      case ReificationPackage.STRING_LIST_KEY_VALUE_PAIR_CONTAINER: return createStringListKeyValuePairContainer();
      case ReificationPackage.BIDIRECTIONAL: return createBidirectional();
      case ReificationPackage.LINK: return createLink();
      case ReificationPackage.RAW_LINK: return createRawLink();
      case ReificationPackage.LINK_ITEM: return createLinkItem();
      case ReificationPackage.MEDIUM_BIDIRECTIONAL: return createMediumBidirectional();
      case ReificationPackage.MEDIUM_LINK: return createMediumLink();
      default:
        throw new IllegalArgumentException("The class '" + eClass.getName() + "' is not a valid classifier");
    }
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public Root createRoot()
  {
    RootImpl root = new RootImpl();
    return root;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public VeryLow createVeryLow()
  {
    VeryLowImpl veryLow = new VeryLowImpl();
    return veryLow;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public Low createLow()
  {
    LowImpl low = new LowImpl();
    return low;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public Medium createMedium()
  {
    MediumImpl medium = new MediumImpl();
    return medium;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public High createHigh()
  {
    HighImpl high = new HighImpl();
    return high;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public VeryHigh createVeryHigh()
  {
    VeryHighImpl veryHigh = new VeryHighImpl();
    return veryHigh;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public org.eclipse.emf.test.common.reification.Container createContainer()
  {
    ContainerImpl container = new ContainerImpl();
    return container;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public <T> UnboundedGenericContainer<T> createUnboundedGenericContainer()
  {
    UnboundedGenericContainerImpl<T> unboundedGenericContainer = new UnboundedGenericContainerImpl<T>();
    return unboundedGenericContainer;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public RawUnboundedContainer createRawUnboundedContainer()
  {
    RawUnboundedContainerImpl rawUnboundedContainer = new RawUnboundedContainerImpl();
    return rawUnboundedContainer;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public MediumUnboundedContainer createMediumUnboundedContainer()
  {
    MediumUnboundedContainerImpl mediumUnboundedContainer = new MediumUnboundedContainerImpl();
    return mediumUnboundedContainer;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public <T extends Medium> BoundedGenericContainer<T> createBoundedGenericContainer()
  {
    BoundedGenericContainerImpl<T> boundedGenericContainer = new BoundedGenericContainerImpl<T>();
    return boundedGenericContainer;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public HighBoundedContainer createHighBoundedContainer()
  {
    HighBoundedContainerImpl highBoundedContainer = new HighBoundedContainerImpl();
    return highBoundedContainer;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public <K extends EList<?>, V extends KeyValuePair<K, V>> KeyValuePair<K, V> createKeyValuePair()
  {
    KeyValuePairImpl<K, V> keyValuePair = new KeyValuePairImpl<K, V>();
    return keyValuePair;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public RawKeyValuePair createRawKeyValuePair()
  {
    RawKeyValuePairImpl rawKeyValuePair = new RawKeyValuePairImpl();
    return rawKeyValuePair;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public StringListKeyValuePair createStringListKeyValuePair()
  {
    StringListKeyValuePairImpl stringListKeyValuePair = new StringListKeyValuePairImpl();
    return stringListKeyValuePair;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public <T> TListKeyValuePair<T> createTListKeyValuePair()
  {
    TListKeyValuePairImpl<T> tListKeyValuePair = new TListKeyValuePairImpl<T>();
    return tListKeyValuePair;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public <T extends Medium> BoundedTListKeyValuePair<T> createBoundedTListKeyValuePair()
  {
    BoundedTListKeyValuePairImpl<T> boundedTListKeyValuePair = new BoundedTListKeyValuePairImpl<T>();
    return boundedTListKeyValuePair;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public HighListKeyValuePair createHighListKeyValuePair()
  {
    HighListKeyValuePairImpl highListKeyValuePair = new HighListKeyValuePairImpl();
    return highListKeyValuePair;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public IntegerListKeyValuePair createIntegerListKeyValuePair()
  {
    IntegerListKeyValuePairImpl integerListKeyValuePair = new IntegerListKeyValuePairImpl();
    return integerListKeyValuePair;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public <K extends EList<?>, V extends KeyValuePair<K, V>> KeyValuePairContainer<K, V> createKeyValuePairContainer()
  {
    KeyValuePairContainerImpl<K, V> keyValuePairContainer = new KeyValuePairContainerImpl<K, V>();
    return keyValuePairContainer;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public StringListKeyValuePairContainer createStringListKeyValuePairContainer()
  {
    StringListKeyValuePairContainerImpl stringListKeyValuePairContainer = new StringListKeyValuePairContainerImpl();
    return stringListKeyValuePairContainer;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public <T extends Bidirectional<T>> Bidirectional<T> createBidirectional()
  {
    BidirectionalImpl<T> bidirectional = new BidirectionalImpl<T>();
    return bidirectional;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public Link createLink()
  {
    LinkImpl link = new LinkImpl();
    return link;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public RawLink createRawLink()
  {
    RawLinkImpl rawLink = new RawLinkImpl();
    return rawLink;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public LinkItem createLinkItem()
  {
    LinkItemImpl linkItem = new LinkItemImpl();
    return linkItem;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public <T extends MediumBidirectional<T> & Medium> MediumBidirectional<T> createMediumBidirectional()
  {
    MediumBidirectionalImpl<T> mediumBidirectional = new MediumBidirectionalImpl<T>();
    return mediumBidirectional;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public MediumLink createMediumLink()
  {
    MediumLinkImpl mediumLink = new MediumLinkImpl();
    return mediumLink;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public ReificationPackage getReificationPackage()
  {
    return (ReificationPackage)getEPackage();
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @deprecated
   * @generated
   */
  @Deprecated
  public static ReificationPackage getPackage()
  {
    return ReificationPackage.eINSTANCE;
  }

} //ReificationFactoryImpl
