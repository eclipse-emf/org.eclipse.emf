/**
 * <copyright>
 * </copyright>
 *
 * $Id: Quote.java,v 1.1 2004/12/19 04:02:21 marcelop Exp $
 */
package com.example.simple;

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
 *   <li>{@link com.example.simple.Quote#getSymbol <em>Symbol</em>}</li>
 *   <li>{@link com.example.simple.Quote#getCompanyName <em>Company Name</em>}</li>
 *   <li>{@link com.example.simple.Quote#getPrice <em>Price</em>}</li>
 *   <li>{@link com.example.simple.Quote#getOpen1 <em>Open1</em>}</li>
 *   <li>{@link com.example.simple.Quote#getHigh <em>High</em>}</li>
 *   <li>{@link com.example.simple.Quote#getLow <em>Low</em>}</li>
 *   <li>{@link com.example.simple.Quote#getVolume <em>Volume</em>}</li>
 *   <li>{@link com.example.simple.Quote#getChange1 <em>Change1</em>}</li>
 *   <li>{@link com.example.simple.Quote#getQuotes <em>Quotes</em>}</li>
 * </ul>
 * </p>
 *
 * @see com.example.simple.SimplePackage#getQuote()
 * @model 
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
   * @see com.example.simple.SimplePackage#getQuote_Symbol()
   * @model unique="false" dataType="org.eclipse.emf.ecore.xml.type.String" required="true"
   * @generated
   */
  String getSymbol();

  /**
   * Sets the value of the '{@link com.example.simple.Quote#getSymbol <em>Symbol</em>}' attribute.
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
   * @see com.example.simple.SimplePackage#getQuote_CompanyName()
   * @model unique="false" dataType="org.eclipse.emf.ecore.xml.type.String" required="true"
   * @generated
   */
  String getCompanyName();

  /**
   * Sets the value of the '{@link com.example.simple.Quote#getCompanyName <em>Company Name</em>}' attribute.
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
   * @see com.example.simple.SimplePackage#getQuote_Price()
   * @model unique="false" dataType="org.eclipse.emf.ecore.xml.type.Decimal" required="true"
   * @generated
   */
  BigDecimal getPrice();

  /**
   * Sets the value of the '{@link com.example.simple.Quote#getPrice <em>Price</em>}' attribute.
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
   * @see com.example.simple.SimplePackage#getQuote_Open1()
   * @model unique="false" dataType="org.eclipse.emf.ecore.xml.type.Decimal" required="true"
   * @generated
   */
  BigDecimal getOpen1();

  /**
   * Sets the value of the '{@link com.example.simple.Quote#getOpen1 <em>Open1</em>}' attribute.
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
   * @see com.example.simple.SimplePackage#getQuote_High()
   * @model unique="false" dataType="org.eclipse.emf.ecore.xml.type.Decimal" required="true"
   * @generated
   */
  BigDecimal getHigh();

  /**
   * Sets the value of the '{@link com.example.simple.Quote#getHigh <em>High</em>}' attribute.
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
   * @see com.example.simple.SimplePackage#getQuote_Low()
   * @model unique="false" dataType="org.eclipse.emf.ecore.xml.type.Decimal" required="true"
   * @generated
   */
  BigDecimal getLow();

  /**
   * Sets the value of the '{@link com.example.simple.Quote#getLow <em>Low</em>}' attribute.
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
   * @see com.example.simple.SimplePackage#getQuote_Volume()
   * @model unique="false" unsettable="true" dataType="org.eclipse.emf.ecore.xml.type.Double" required="true"
   * @generated
   */
  double getVolume();

  /**
   * Sets the value of the '{@link com.example.simple.Quote#getVolume <em>Volume</em>}' attribute.
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
   * Unsets the value of the '{@link com.example.simple.Quote#getVolume <em>Volume</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #isSetVolume()
   * @see #getVolume()
   * @see #setVolume(double)
   * @generated
   */
  void unsetVolume();

  /**
   * Returns whether the value of the '{@link com.example.simple.Quote#getVolume <em>Volume</em>}' attribute is set.
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
   * @see com.example.simple.SimplePackage#getQuote_Change1()
   * @model unique="false" unsettable="true" dataType="org.eclipse.emf.ecore.xml.type.Double" required="true"
   * @generated
   */
  double getChange1();

  /**
   * Sets the value of the '{@link com.example.simple.Quote#getChange1 <em>Change1</em>}' attribute.
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
   * Unsets the value of the '{@link com.example.simple.Quote#getChange1 <em>Change1</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #isSetChange1()
   * @see #getChange1()
   * @see #setChange1(double)
   * @generated
   */
  void unsetChange1();

  /**
   * Returns whether the value of the '{@link com.example.simple.Quote#getChange1 <em>Change1</em>}' attribute is set.
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
   * The list contents are of type {@link com.example.simple.Quote}.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Quotes</em>' containment reference list isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Quotes</em>' containment reference list.
   * @see com.example.simple.SimplePackage#getQuote_Quotes()
   * @model type="com.example.simple.Quote" containment="true"
   * @generated
   */
  List getQuotes();

} // Quote
