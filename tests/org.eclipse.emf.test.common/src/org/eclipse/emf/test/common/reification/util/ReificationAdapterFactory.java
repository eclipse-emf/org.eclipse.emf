/**
 * Copyright (c) 2013 Eclipse contributors and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package org.eclipse.emf.test.common.reification.util;

import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notifier;

import org.eclipse.emf.common.notify.impl.AdapterFactoryImpl;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

import org.eclipse.emf.test.common.reification.*;

/**
 * <!-- begin-user-doc -->
 * The <b>Adapter Factory</b> for the model.
 * It provides an adapter <code>createXXX</code> method for each class of the model.
 * <!-- end-user-doc -->
 * @see org.eclipse.emf.test.common.reification.ReificationPackage
 * @generated
 */
public class ReificationAdapterFactory extends AdapterFactoryImpl
{
  /**
   * The cached model package.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected static ReificationPackage modelPackage;

  /**
   * Creates an instance of the adapter factory.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public ReificationAdapterFactory()
  {
    if (modelPackage == null)
    {
      modelPackage = ReificationPackage.eINSTANCE;
    }
  }

  /**
   * Returns whether this factory is applicable for the type of the object.
   * <!-- begin-user-doc -->
   * This implementation returns <code>true</code> if the object is either the model's package or is an instance object of the model.
   * <!-- end-user-doc -->
   * @return whether this factory is applicable for the type of the object.
   * @generated
   */
  @Override
  public boolean isFactoryForType(Object object)
  {
    if (object == modelPackage)
    {
      return true;
    }
    if (object instanceof EObject)
    {
      return ((EObject)object).eClass().getEPackage() == modelPackage;
    }
    return false;
  }

  /**
   * The switch that delegates to the <code>createXXX</code> methods.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected ReificationSwitch<Adapter> modelSwitch =
    new ReificationSwitch<Adapter>()
    {
      @Override
      public Adapter caseRoot(Root object)
      {
        return createRootAdapter();
      }
      @Override
      public Adapter caseVeryLow(VeryLow object)
      {
        return createVeryLowAdapter();
      }
      @Override
      public Adapter caseLow(Low object)
      {
        return createLowAdapter();
      }
      @Override
      public Adapter caseMedium(Medium object)
      {
        return createMediumAdapter();
      }
      @Override
      public Adapter caseHigh(High object)
      {
        return createHighAdapter();
      }
      @Override
      public Adapter caseVeryHigh(VeryHigh object)
      {
        return createVeryHighAdapter();
      }
      @Override
      public Adapter caseContainer(Container object)
      {
        return createContainerAdapter();
      }
      @Override
      public <T> Adapter caseUnboundedGenericContainer(UnboundedGenericContainer<T> object)
      {
        return createUnboundedGenericContainerAdapter();
      }
      @Override
      public Adapter caseRawUnboundedContainer(RawUnboundedContainer object)
      {
        return createRawUnboundedContainerAdapter();
      }
      @Override
      public Adapter caseMediumUnboundedContainer(MediumUnboundedContainer object)
      {
        return createMediumUnboundedContainerAdapter();
      }
      @Override
      public <T extends Medium> Adapter caseBoundedGenericContainer(BoundedGenericContainer<T> object)
      {
        return createBoundedGenericContainerAdapter();
      }
      @Override
      public Adapter caseHighBoundedContainer(HighBoundedContainer object)
      {
        return createHighBoundedContainerAdapter();
      }
      @Override
      public <K extends EList<?>, V extends KeyValuePair<K, V>> Adapter caseKeyValuePair(KeyValuePair<K, V> object)
      {
        return createKeyValuePairAdapter();
      }
      @Override
      public Adapter caseRawKeyValuePair(RawKeyValuePair object)
      {
        return createRawKeyValuePairAdapter();
      }
      @Override
      public Adapter caseStringListKeyValuePair(StringListKeyValuePair object)
      {
        return createStringListKeyValuePairAdapter();
      }
      @Override
      public <T> Adapter caseTListKeyValuePair(TListKeyValuePair<T> object)
      {
        return createTListKeyValuePairAdapter();
      }
      @Override
      public <T extends Medium> Adapter caseBoundedTListKeyValuePair(BoundedTListKeyValuePair<T> object)
      {
        return createBoundedTListKeyValuePairAdapter();
      }
      @Override
      public Adapter caseHighListKeyValuePair(HighListKeyValuePair object)
      {
        return createHighListKeyValuePairAdapter();
      }
      @Override
      public Adapter caseIntegerListKeyValuePair(IntegerListKeyValuePair object)
      {
        return createIntegerListKeyValuePairAdapter();
      }
      @Override
      public <K extends EList<?>, V extends KeyValuePair<K, V>> Adapter caseKeyValuePairContainer(KeyValuePairContainer<K, V> object)
      {
        return createKeyValuePairContainerAdapter();
      }
      @Override
      public Adapter caseStringListKeyValuePairContainer(StringListKeyValuePairContainer object)
      {
        return createStringListKeyValuePairContainerAdapter();
      }
      @Override
      public <T extends Bidirectional<T>> Adapter caseBidirectional(Bidirectional<T> object)
      {
        return createBidirectionalAdapter();
      }
      @Override
      public Adapter caseLink(Link object)
      {
        return createLinkAdapter();
      }
      @Override
      public Adapter caseRawLink(RawLink object)
      {
        return createRawLinkAdapter();
      }
      @Override
      public Adapter caseLinkItem(LinkItem object)
      {
        return createLinkItemAdapter();
      }
      @Override
      public <T extends MediumBidirectional<T> & Medium> Adapter caseMediumBidirectional(MediumBidirectional<T> object)
      {
        return createMediumBidirectionalAdapter();
      }
      @Override
      public Adapter caseMediumLink(MediumLink object)
      {
        return createMediumLinkAdapter();
      }
      @Override
      public Adapter defaultCase(EObject object)
      {
        return createEObjectAdapter();
      }
    };

  /**
   * Creates an adapter for the <code>target</code>.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param target the object to adapt.
   * @return the adapter for the <code>target</code>.
   * @generated
   */
  @Override
  public Adapter createAdapter(Notifier target)
  {
    return modelSwitch.doSwitch((EObject)target);
  }


  /**
   * Creates a new adapter for an object of class '{@link org.eclipse.emf.test.common.reification.Root <em>Root</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.eclipse.emf.test.common.reification.Root
   * @generated
   */
  public Adapter createRootAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.eclipse.emf.test.common.reification.VeryLow <em>Very Low</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.eclipse.emf.test.common.reification.VeryLow
   * @generated
   */
  public Adapter createVeryLowAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.eclipse.emf.test.common.reification.Low <em>Low</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.eclipse.emf.test.common.reification.Low
   * @generated
   */
  public Adapter createLowAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.eclipse.emf.test.common.reification.Medium <em>Medium</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.eclipse.emf.test.common.reification.Medium
   * @generated
   */
  public Adapter createMediumAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.eclipse.emf.test.common.reification.High <em>High</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.eclipse.emf.test.common.reification.High
   * @generated
   */
  public Adapter createHighAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.eclipse.emf.test.common.reification.VeryHigh <em>Very High</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.eclipse.emf.test.common.reification.VeryHigh
   * @generated
   */
  public Adapter createVeryHighAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.eclipse.emf.test.common.reification.Container <em>Container</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.eclipse.emf.test.common.reification.Container
   * @generated
   */
  public Adapter createContainerAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.eclipse.emf.test.common.reification.UnboundedGenericContainer <em>Unbounded Generic Container</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.eclipse.emf.test.common.reification.UnboundedGenericContainer
   * @generated
   */
  public Adapter createUnboundedGenericContainerAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.eclipse.emf.test.common.reification.RawUnboundedContainer <em>Raw Unbounded Container</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.eclipse.emf.test.common.reification.RawUnboundedContainer
   * @generated
   */
  public Adapter createRawUnboundedContainerAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.eclipse.emf.test.common.reification.MediumUnboundedContainer <em>Medium Unbounded Container</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.eclipse.emf.test.common.reification.MediumUnboundedContainer
   * @generated
   */
  public Adapter createMediumUnboundedContainerAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.eclipse.emf.test.common.reification.BoundedGenericContainer <em>Bounded Generic Container</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.eclipse.emf.test.common.reification.BoundedGenericContainer
   * @generated
   */
  public Adapter createBoundedGenericContainerAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.eclipse.emf.test.common.reification.HighBoundedContainer <em>High Bounded Container</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.eclipse.emf.test.common.reification.HighBoundedContainer
   * @generated
   */
  public Adapter createHighBoundedContainerAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.eclipse.emf.test.common.reification.KeyValuePair <em>Key Value Pair</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.eclipse.emf.test.common.reification.KeyValuePair
   * @generated
   */
  public Adapter createKeyValuePairAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.eclipse.emf.test.common.reification.RawKeyValuePair <em>Raw Key Value Pair</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.eclipse.emf.test.common.reification.RawKeyValuePair
   * @generated
   */
  public Adapter createRawKeyValuePairAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.eclipse.emf.test.common.reification.StringListKeyValuePair <em>String List Key Value Pair</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.eclipse.emf.test.common.reification.StringListKeyValuePair
   * @generated
   */
  public Adapter createStringListKeyValuePairAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.eclipse.emf.test.common.reification.TListKeyValuePair <em>TList Key Value Pair</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.eclipse.emf.test.common.reification.TListKeyValuePair
   * @generated
   */
  public Adapter createTListKeyValuePairAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.eclipse.emf.test.common.reification.BoundedTListKeyValuePair <em>Bounded TList Key Value Pair</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.eclipse.emf.test.common.reification.BoundedTListKeyValuePair
   * @generated
   */
  public Adapter createBoundedTListKeyValuePairAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.eclipse.emf.test.common.reification.HighListKeyValuePair <em>High List Key Value Pair</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.eclipse.emf.test.common.reification.HighListKeyValuePair
   * @generated
   */
  public Adapter createHighListKeyValuePairAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.eclipse.emf.test.common.reification.IntegerListKeyValuePair <em>Integer List Key Value Pair</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.eclipse.emf.test.common.reification.IntegerListKeyValuePair
   * @generated
   */
  public Adapter createIntegerListKeyValuePairAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.eclipse.emf.test.common.reification.KeyValuePairContainer <em>Key Value Pair Container</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.eclipse.emf.test.common.reification.KeyValuePairContainer
   * @generated
   */
  public Adapter createKeyValuePairContainerAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.eclipse.emf.test.common.reification.StringListKeyValuePairContainer <em>String List Key Value Pair Container</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.eclipse.emf.test.common.reification.StringListKeyValuePairContainer
   * @generated
   */
  public Adapter createStringListKeyValuePairContainerAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.eclipse.emf.test.common.reification.Bidirectional <em>Bidirectional</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.eclipse.emf.test.common.reification.Bidirectional
   * @generated
   */
  public Adapter createBidirectionalAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.eclipse.emf.test.common.reification.Link <em>Link</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.eclipse.emf.test.common.reification.Link
   * @generated
   */
  public Adapter createLinkAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.eclipse.emf.test.common.reification.RawLink <em>Raw Link</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.eclipse.emf.test.common.reification.RawLink
   * @generated
   */
  public Adapter createRawLinkAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.eclipse.emf.test.common.reification.LinkItem <em>Link Item</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.eclipse.emf.test.common.reification.LinkItem
   * @generated
   */
  public Adapter createLinkItemAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.eclipse.emf.test.common.reification.MediumBidirectional <em>Medium Bidirectional</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.eclipse.emf.test.common.reification.MediumBidirectional
   * @generated
   */
  public Adapter createMediumBidirectionalAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.eclipse.emf.test.common.reification.MediumLink <em>Medium Link</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.eclipse.emf.test.common.reification.MediumLink
   * @generated
   */
  public Adapter createMediumLinkAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for the default case.
   * <!-- begin-user-doc -->
   * This default implementation returns null.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @generated
   */
  public Adapter createEObjectAdapter()
  {
    return null;
  }

} //ReificationAdapterFactory
