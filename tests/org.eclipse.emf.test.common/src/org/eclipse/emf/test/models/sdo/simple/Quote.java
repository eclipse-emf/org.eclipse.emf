/**
 * <copyright>
 *
 * Copyright (c) 2007 IBM Corporation and others.
 * All rights reserved.  This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *   IBM - Initial API and implementation
 *
 * </copyright>
 *
 * $Id: Quote.java,v 1.3 2007/06/12 15:08:10 emerks Exp $
 */
package org.eclipse.emf.test.models.sdo.simple;

import java.math.BigDecimal;

import java.util.List;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Quote</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.emf.test.models.sdo.simple.Quote#getSymbol <em>Symbol</em>}</li>
 *   <li>{@link org.eclipse.emf.test.models.sdo.simple.Quote#getCompanyName <em>Company Name</em>}</li>
 *   <li>{@link org.eclipse.emf.test.models.sdo.simple.Quote#getPrice <em>Price</em>}</li>
 *   <li>{@link org.eclipse.emf.test.models.sdo.simple.Quote#getOpen1 <em>Open1</em>}</li>
 *   <li>{@link org.eclipse.emf.test.models.sdo.simple.Quote#getHigh <em>High</em>}</li>
 *   <li>{@link org.eclipse.emf.test.models.sdo.simple.Quote#getLow <em>Low</em>}</li>
 *   <li>{@link org.eclipse.emf.test.models.sdo.simple.Quote#getVolume <em>Volume</em>}</li>
 *   <li>{@link org.eclipse.emf.test.models.sdo.simple.Quote#getChange1 <em>Change1</em>}</li>
 *   <li>{@link org.eclipse.emf.test.models.sdo.simple.Quote#getQuotes <em>Quotes</em>}</li>
 * </ul>
 * </p>
 *
 * @model extendedMetaData="name='Quote' kind='elementOnly'"
 * @generated
 */
public interface Quote
{
  /**
   * Returns the value of the '<em><b>Symbol</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Symbol</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Symbol</em>' attribute.
   * @see #setSymbol(String)
   * @model unique="false" dataType="org.eclipse.emf.ecore.xml.type.String" required="true"
   *        extendedMetaData="kind='element' name='symbol'"
   * @generated
   */
  String getSymbol();

  /**
   * Sets the value of the '{@link org.eclipse.emf.test.models.sdo.simple.Quote#getSymbol <em>Symbol</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Symbol</em>' attribute.
   * @see #getSymbol()
   * @generated
   */
  void setSymbol(String value);

  /**
   * Returns the value of the '<em><b>Company Name</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Company Name</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Company Name</em>' attribute.
   * @see #setCompanyName(String)
   * @model unique="false" dataType="org.eclipse.emf.ecore.xml.type.String" required="true"
   *        extendedMetaData="kind='element' name='companyName'"
   * @generated
   */
  String getCompanyName();

  /**
   * Sets the value of the '{@link org.eclipse.emf.test.models.sdo.simple.Quote#getCompanyName <em>Company Name</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Company Name</em>' attribute.
   * @see #getCompanyName()
   * @generated
   */
  void setCompanyName(String value);

  /**
   * Returns the value of the '<em><b>Price</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Price</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Price</em>' attribute.
   * @see #setPrice(BigDecimal)
   * @model unique="false" dataType="org.eclipse.emf.ecore.xml.type.Decimal" required="true"
   *        extendedMetaData="kind='element' name='price'"
   * @generated
   */
  BigDecimal getPrice();

  /**
   * Sets the value of the '{@link org.eclipse.emf.test.models.sdo.simple.Quote#getPrice <em>Price</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Price</em>' attribute.
   * @see #getPrice()
   * @generated
   */
  void setPrice(BigDecimal value);

  /**
   * Returns the value of the '<em><b>Open1</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Open1</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Open1</em>' attribute.
   * @see #setOpen1(BigDecimal)
   * @model unique="false" dataType="org.eclipse.emf.ecore.xml.type.Decimal" required="true"
   *        extendedMetaData="kind='element' name='open1'"
   * @generated
   */
  BigDecimal getOpen1();

  /**
   * Sets the value of the '{@link org.eclipse.emf.test.models.sdo.simple.Quote#getOpen1 <em>Open1</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Open1</em>' attribute.
   * @see #getOpen1()
   * @generated
   */
  void setOpen1(BigDecimal value);

  /**
   * Returns the value of the '<em><b>High</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>High</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>High</em>' attribute.
   * @see #setHigh(BigDecimal)
   * @model unique="false" dataType="org.eclipse.emf.ecore.xml.type.Decimal" required="true"
   *        extendedMetaData="kind='element' name='high'"
   * @generated
   */
  BigDecimal getHigh();

  /**
   * Sets the value of the '{@link org.eclipse.emf.test.models.sdo.simple.Quote#getHigh <em>High</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>High</em>' attribute.
   * @see #getHigh()
   * @generated
   */
  void setHigh(BigDecimal value);

  /**
   * Returns the value of the '<em><b>Low</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Low</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Low</em>' attribute.
   * @see #setLow(BigDecimal)
   * @model unique="false" dataType="org.eclipse.emf.ecore.xml.type.Decimal" required="true"
   *        extendedMetaData="kind='element' name='low'"
   * @generated
   */
  BigDecimal getLow();

  /**
   * Sets the value of the '{@link org.eclipse.emf.test.models.sdo.simple.Quote#getLow <em>Low</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Low</em>' attribute.
   * @see #getLow()
   * @generated
   */
  void setLow(BigDecimal value);

  /**
   * Returns the value of the '<em><b>Volume</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Volume</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Volume</em>' attribute.
   * @see #isSetVolume()
   * @see #unsetVolume()
   * @see #setVolume(double)
   * @model unique="false" unsettable="true" dataType="org.eclipse.emf.ecore.xml.type.Double" required="true"
   *        extendedMetaData="kind='element' name='volume'"
   * @generated
   */
  double getVolume();

  /**
   * Sets the value of the '{@link org.eclipse.emf.test.models.sdo.simple.Quote#getVolume <em>Volume</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Volume</em>' attribute.
   * @see #isSetVolume()
   * @see #unsetVolume()
   * @see #getVolume()
   * @generated
   */
  void setVolume(double value);

  /**
   * Unsets the value of the '{@link org.eclipse.emf.test.models.sdo.simple.Quote#getVolume <em>Volume</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #isSetVolume()
   * @see #getVolume()
   * @see #setVolume(double)
   * @generated
   */
  void unsetVolume();

  /**
   * Returns whether the value of the '{@link org.eclipse.emf.test.models.sdo.simple.Quote#getVolume <em>Volume</em>}' attribute is set.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return whether the value of the '<em>Volume</em>' attribute is set.
   * @see #unsetVolume()
   * @see #getVolume()
   * @see #setVolume(double)
   * @generated
   */
  boolean isSetVolume();

  /**
   * Returns the value of the '<em><b>Change1</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Change1</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Change1</em>' attribute.
   * @see #isSetChange1()
   * @see #unsetChange1()
   * @see #setChange1(double)
   * @model unique="false" unsettable="true" dataType="org.eclipse.emf.ecore.xml.type.Double" required="true"
   *        extendedMetaData="kind='element' name='change1'"
   * @generated
   */
  double getChange1();

  /**
   * Sets the value of the '{@link org.eclipse.emf.test.models.sdo.simple.Quote#getChange1 <em>Change1</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Change1</em>' attribute.
   * @see #isSetChange1()
   * @see #unsetChange1()
   * @see #getChange1()
   * @generated
   */
  void setChange1(double value);

  /**
   * Unsets the value of the '{@link org.eclipse.emf.test.models.sdo.simple.Quote#getChange1 <em>Change1</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #isSetChange1()
   * @see #getChange1()
   * @see #setChange1(double)
   * @generated
   */
  void unsetChange1();

  /**
   * Returns whether the value of the '{@link org.eclipse.emf.test.models.sdo.simple.Quote#getChange1 <em>Change1</em>}' attribute is set.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return whether the value of the '<em>Change1</em>' attribute is set.
   * @see #unsetChange1()
   * @see #getChange1()
   * @see #setChange1(double)
   * @generated
   */
  boolean isSetChange1();

  /**
   * Returns the value of the '<em><b>Quotes</b></em>' containment reference list.
   * The list contents are of type {@link org.eclipse.emf.test.models.sdo.simple.Quote}.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Quotes</em>' containment reference list isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Quotes</em>' containment reference list.
   * @model containment="true"
   *        extendedMetaData="kind='element' name='quotes'"
   * @generated
   */
  List<Quote> getQuotes();

} // Quote
