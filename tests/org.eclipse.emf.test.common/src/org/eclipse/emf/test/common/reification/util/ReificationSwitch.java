/**
 * Copyright (c) 2013 Eclipse contributors and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package org.eclipse.emf.test.common.reification.util;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;

import org.eclipse.emf.ecore.util.Switch;

import org.eclipse.emf.test.common.reification.*;

/**
 * <!-- begin-user-doc -->
 * The <b>Switch</b> for the model's inheritance hierarchy.
 * It supports the call {@link #doSwitch(EObject) doSwitch(object)}
 * to invoke the <code>caseXXX</code> method for each class of the model,
 * starting with the actual class of the object
 * and proceeding up the inheritance hierarchy
 * until a non-null result is returned,
 * which is the result of the switch.
 * <!-- end-user-doc -->
 * @see org.eclipse.emf.test.common.reification.ReificationPackage
 * @generated
 */
public class ReificationSwitch<T1> extends Switch<T1>
{
  /**
   * The cached model package
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected static ReificationPackage modelPackage;

  /**
   * Creates an instance of the switch.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public ReificationSwitch()
  {
    if (modelPackage == null)
    {
      modelPackage = ReificationPackage.eINSTANCE;
    }
  }

  /**
   * Checks whether this is a switch for the given package.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @parameter ePackage the package in question.
   * @return whether this is a switch for the given package.
   * @generated
   */
  @Override
  protected boolean isSwitchFor(EPackage ePackage)
  {
    return ePackage == modelPackage;
  }

  /**
   * Calls <code>caseXXX</code> for each class of the model until one returns a non null result; it yields that result.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the first non-null result returned by a <code>caseXXX</code> call.
   * @generated
   */
  @SuppressWarnings("unchecked")
  @Override
  protected T1 doSwitch(int classifierID, EObject theEObject)
  {
    switch (classifierID)
    {
      case ReificationPackage.ROOT:
      {
        Root root = (Root)theEObject;
        T1 result = caseRoot(root);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case ReificationPackage.VERY_LOW:
      {
        VeryLow veryLow = (VeryLow)theEObject;
        T1 result = caseVeryLow(veryLow);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case ReificationPackage.LOW:
      {
        Low low = (Low)theEObject;
        T1 result = caseLow(low);
        if (result == null) result = caseVeryLow(low);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case ReificationPackage.MEDIUM:
      {
        Medium medium = (Medium)theEObject;
        T1 result = caseMedium(medium);
        if (result == null) result = caseLow(medium);
        if (result == null) result = caseVeryLow(medium);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case ReificationPackage.HIGH:
      {
        High high = (High)theEObject;
        T1 result = caseHigh(high);
        if (result == null) result = caseMedium(high);
        if (result == null) result = caseLow(high);
        if (result == null) result = caseVeryLow(high);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case ReificationPackage.VERY_HIGH:
      {
        VeryHigh veryHigh = (VeryHigh)theEObject;
        T1 result = caseVeryHigh(veryHigh);
        if (result == null) result = caseHigh(veryHigh);
        if (result == null) result = caseMedium(veryHigh);
        if (result == null) result = caseLow(veryHigh);
        if (result == null) result = caseVeryLow(veryHigh);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case ReificationPackage.CONTAINER:
      {
        Container container = (Container)theEObject;
        T1 result = caseContainer(container);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case ReificationPackage.UNBOUNDED_GENERIC_CONTAINER:
      {
        UnboundedGenericContainer<?> unboundedGenericContainer = (UnboundedGenericContainer<?>)theEObject;
        T1 result = caseUnboundedGenericContainer(unboundedGenericContainer);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case ReificationPackage.RAW_UNBOUNDED_CONTAINER:
      {
        RawUnboundedContainer rawUnboundedContainer = (RawUnboundedContainer)theEObject;
        T1 result = caseRawUnboundedContainer(rawUnboundedContainer);
        if (result == null) result = (T1)caseUnboundedGenericContainer(rawUnboundedContainer);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case ReificationPackage.MEDIUM_UNBOUNDED_CONTAINER:
      {
        MediumUnboundedContainer mediumUnboundedContainer = (MediumUnboundedContainer)theEObject;
        T1 result = caseMediumUnboundedContainer(mediumUnboundedContainer);
        if (result == null) result = caseUnboundedGenericContainer(mediumUnboundedContainer);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case ReificationPackage.BOUNDED_GENERIC_CONTAINER:
      {
        BoundedGenericContainer<?> boundedGenericContainer = (BoundedGenericContainer<?>)theEObject;
        T1 result = caseBoundedGenericContainer(boundedGenericContainer);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case ReificationPackage.HIGH_BOUNDED_CONTAINER:
      {
        HighBoundedContainer highBoundedContainer = (HighBoundedContainer)theEObject;
        T1 result = caseHighBoundedContainer(highBoundedContainer);
        if (result == null) result = caseBoundedGenericContainer(highBoundedContainer);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case ReificationPackage.KEY_VALUE_PAIR:
      {
        KeyValuePair<?, ?> keyValuePair = (KeyValuePair<?, ?>)theEObject;
        T1 result = caseKeyValuePair(keyValuePair);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case ReificationPackage.RAW_KEY_VALUE_PAIR:
      {
        RawKeyValuePair rawKeyValuePair = (RawKeyValuePair)theEObject;
        T1 result = caseRawKeyValuePair(rawKeyValuePair);
        if (result == null) result = (T1)caseKeyValuePair(rawKeyValuePair);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case ReificationPackage.STRING_LIST_KEY_VALUE_PAIR:
      {
        StringListKeyValuePair stringListKeyValuePair = (StringListKeyValuePair)theEObject;
        T1 result = caseStringListKeyValuePair(stringListKeyValuePair);
        if (result == null) result = caseKeyValuePair(stringListKeyValuePair);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case ReificationPackage.TLIST_KEY_VALUE_PAIR:
      {
        TListKeyValuePair<?> tListKeyValuePair = (TListKeyValuePair<?>)theEObject;
        T1 result = caseTListKeyValuePair(tListKeyValuePair);
        if (result == null) result = caseKeyValuePair(tListKeyValuePair);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case ReificationPackage.BOUNDED_TLIST_KEY_VALUE_PAIR:
      {
        BoundedTListKeyValuePair<?> boundedTListKeyValuePair = (BoundedTListKeyValuePair<?>)theEObject;
        T1 result = caseBoundedTListKeyValuePair(boundedTListKeyValuePair);
        if (result == null) result = caseTListKeyValuePair(boundedTListKeyValuePair);
        if (result == null) result = caseKeyValuePair(boundedTListKeyValuePair);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case ReificationPackage.HIGH_LIST_KEY_VALUE_PAIR:
      {
        HighListKeyValuePair highListKeyValuePair = (HighListKeyValuePair)theEObject;
        T1 result = caseHighListKeyValuePair(highListKeyValuePair);
        if (result == null) result = caseBoundedTListKeyValuePair(highListKeyValuePair);
        if (result == null) result = caseTListKeyValuePair(highListKeyValuePair);
        if (result == null) result = caseKeyValuePair(highListKeyValuePair);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case ReificationPackage.INTEGER_LIST_KEY_VALUE_PAIR:
      {
        IntegerListKeyValuePair integerListKeyValuePair = (IntegerListKeyValuePair)theEObject;
        T1 result = caseIntegerListKeyValuePair(integerListKeyValuePair);
        if (result == null) result = caseTListKeyValuePair(integerListKeyValuePair);
        if (result == null) result = caseKeyValuePair(integerListKeyValuePair);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case ReificationPackage.KEY_VALUE_PAIR_CONTAINER:
      {
        KeyValuePairContainer<?, ?> keyValuePairContainer = (KeyValuePairContainer<?, ?>)theEObject;
        T1 result = caseKeyValuePairContainer(keyValuePairContainer);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case ReificationPackage.STRING_LIST_KEY_VALUE_PAIR_CONTAINER:
      {
        StringListKeyValuePairContainer stringListKeyValuePairContainer = (StringListKeyValuePairContainer)theEObject;
        T1 result = caseStringListKeyValuePairContainer(stringListKeyValuePairContainer);
        if (result == null) result = caseKeyValuePairContainer(stringListKeyValuePairContainer);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case ReificationPackage.BIDIRECTIONAL:
      {
        Bidirectional<?> bidirectional = (Bidirectional<?>)theEObject;
        T1 result = caseBidirectional(bidirectional);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case ReificationPackage.LINK:
      {
        Link link = (Link)theEObject;
        T1 result = caseLink(link);
        if (result == null) result = caseBidirectional(link);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case ReificationPackage.RAW_LINK:
      {
        RawLink rawLink = (RawLink)theEObject;
        T1 result = caseRawLink(rawLink);
        if (result == null) result = (T1)caseBidirectional(rawLink);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case ReificationPackage.LINK_ITEM:
      {
        LinkItem linkItem = (LinkItem)theEObject;
        T1 result = caseLinkItem(linkItem);
        if (result == null) result = caseBidirectional(linkItem);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case ReificationPackage.MEDIUM_BIDIRECTIONAL:
      {
        MediumBidirectional<?> mediumBidirectional = (MediumBidirectional<?>)theEObject;
        T1 result = caseMediumBidirectional(mediumBidirectional);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case ReificationPackage.MEDIUM_LINK:
      {
        MediumLink mediumLink = (MediumLink)theEObject;
        T1 result = caseMediumLink(mediumLink);
        if (result == null) result = caseMediumBidirectional(mediumLink);
        if (result == null) result = caseMedium(mediumLink);
        if (result == null) result = caseLow(mediumLink);
        if (result == null) result = caseVeryLow(mediumLink);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      default: return defaultCase(theEObject);
    }
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Root</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Root</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T1 caseRoot(Root object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Very Low</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Very Low</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T1 caseVeryLow(VeryLow object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Low</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Low</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T1 caseLow(Low object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Medium</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Medium</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T1 caseMedium(Medium object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>High</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>High</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T1 caseHigh(High object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Very High</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Very High</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T1 caseVeryHigh(VeryHigh object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Container</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Container</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T1 caseContainer(Container object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Unbounded Generic Container</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Unbounded Generic Container</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public <T> T1 caseUnboundedGenericContainer(UnboundedGenericContainer<T> object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Raw Unbounded Container</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Raw Unbounded Container</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T1 caseRawUnboundedContainer(RawUnboundedContainer object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Medium Unbounded Container</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Medium Unbounded Container</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T1 caseMediumUnboundedContainer(MediumUnboundedContainer object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Bounded Generic Container</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Bounded Generic Container</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public <T extends Medium> T1 caseBoundedGenericContainer(BoundedGenericContainer<T> object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>High Bounded Container</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>High Bounded Container</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T1 caseHighBoundedContainer(HighBoundedContainer object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Key Value Pair</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Key Value Pair</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public <K extends EList<?>, V extends KeyValuePair<K, V>> T1 caseKeyValuePair(KeyValuePair<K, V> object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Raw Key Value Pair</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Raw Key Value Pair</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T1 caseRawKeyValuePair(RawKeyValuePair object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>String List Key Value Pair</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>String List Key Value Pair</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T1 caseStringListKeyValuePair(StringListKeyValuePair object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>TList Key Value Pair</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>TList Key Value Pair</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public <T> T1 caseTListKeyValuePair(TListKeyValuePair<T> object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Bounded TList Key Value Pair</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Bounded TList Key Value Pair</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public <T extends Medium> T1 caseBoundedTListKeyValuePair(BoundedTListKeyValuePair<T> object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>High List Key Value Pair</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>High List Key Value Pair</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T1 caseHighListKeyValuePair(HighListKeyValuePair object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Integer List Key Value Pair</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Integer List Key Value Pair</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T1 caseIntegerListKeyValuePair(IntegerListKeyValuePair object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Key Value Pair Container</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Key Value Pair Container</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public <K extends EList<?>, V extends KeyValuePair<K, V>> T1 caseKeyValuePairContainer(KeyValuePairContainer<K, V> object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>String List Key Value Pair Container</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>String List Key Value Pair Container</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T1 caseStringListKeyValuePairContainer(StringListKeyValuePairContainer object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Bidirectional</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Bidirectional</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public <T extends Bidirectional<T>> T1 caseBidirectional(Bidirectional<T> object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Link</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Link</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T1 caseLink(Link object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Raw Link</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Raw Link</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T1 caseRawLink(RawLink object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Link Item</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Link Item</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T1 caseLinkItem(LinkItem object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Medium Bidirectional</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Medium Bidirectional</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public <T extends MediumBidirectional<T> & Medium> T1 caseMediumBidirectional(MediumBidirectional<T> object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>Medium Link</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>Medium Link</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T1 caseMediumLink(MediumLink object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>EObject</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch, but this is the last case anyway.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>EObject</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject)
   * @generated
   */
  @Override
  public T1 defaultCase(EObject object)
  {
    return null;
  }

} //ReificationSwitch
