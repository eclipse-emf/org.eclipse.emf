/**
 * <copyright>
 * </copyright>
 *
 * $Id: SimplePackage.java,v 1.1 2004/06/30 21:11:29 marcelop Exp $
 */
package com.example.simple;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;

import org.eclipse.emf.ecore.EReference;

/**
 * <!-- begin-user-doc -->
 * The <b>Package</b> for the model.
 * It contains accessors for the meta objects to represent
 * <ul>
 *   <li>each class,</li>
 *   <li>each feature of each class,</li>
 *   <li>each enum,</li>
 *   <li>and each data type</li>
 * </ul>
 * <!-- end-user-doc -->
 * @see com.example.simple.SimpleFactory
 * @generated
 */
public interface SimplePackage extends EPackage{
  /**
   * The package name.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  String eNAME = "simple";

  /**
   * The package namespace URI.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  String eNS_URI = "http://www.example.com/simple";

  /**
   * The package namespace name.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  String eNS_PREFIX = "simple";

  /**
   * The singleton instance of the package.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  SimplePackage eINSTANCE = com.example.simple.impl.SimplePackageImpl.init();

  /**
   * The meta object id for the '{@link com.example.simple.impl.QuoteImpl <em>Quote</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see com.example.simple.impl.QuoteImpl
   * @see com.example.simple.impl.SimplePackageImpl#getQuote()
   * @generated
   */
  int QUOTE = 0;

  /**
   * The feature id for the '<em><b>Symbol</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int QUOTE__SYMBOL = 0;

  /**
   * The feature id for the '<em><b>Company Name</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int QUOTE__COMPANY_NAME = 1;

  /**
   * The feature id for the '<em><b>Price</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int QUOTE__PRICE = 2;

  /**
   * The feature id for the '<em><b>Open1</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int QUOTE__OPEN1 = 3;

  /**
   * The feature id for the '<em><b>High</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int QUOTE__HIGH = 4;

  /**
   * The feature id for the '<em><b>Low</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int QUOTE__LOW = 5;

  /**
   * The feature id for the '<em><b>Volume</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int QUOTE__VOLUME = 6;

  /**
   * The feature id for the '<em><b>Change1</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int QUOTE__CHANGE1 = 7;

  /**
   * The feature id for the '<em><b>Quotes</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int QUOTE__QUOTES = 8;

  /**
   * The number of structural features of the the '<em>Quote</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int QUOTE_FEATURE_COUNT = 9;


  /**
   * Returns the meta object for class '{@link com.example.simple.Quote <em>Quote</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Quote</em>'.
   * @see com.example.simple.Quote
   * @generated
   */
  EClass getQuote();

  /**
   * Returns the meta object for the attribute '{@link com.example.simple.Quote#getSymbol <em>Symbol</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Symbol</em>'.
   * @see com.example.simple.Quote#getSymbol()
   * @see #getQuote()
   * @generated
   */
  EAttribute getQuote_Symbol();

  /**
   * Returns the meta object for the attribute '{@link com.example.simple.Quote#getCompanyName <em>Company Name</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Company Name</em>'.
   * @see com.example.simple.Quote#getCompanyName()
   * @see #getQuote()
   * @generated
   */
  EAttribute getQuote_CompanyName();

  /**
   * Returns the meta object for the attribute '{@link com.example.simple.Quote#getPrice <em>Price</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Price</em>'.
   * @see com.example.simple.Quote#getPrice()
   * @see #getQuote()
   * @generated
   */
  EAttribute getQuote_Price();

  /**
   * Returns the meta object for the attribute '{@link com.example.simple.Quote#getOpen1 <em>Open1</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Open1</em>'.
   * @see com.example.simple.Quote#getOpen1()
   * @see #getQuote()
   * @generated
   */
  EAttribute getQuote_Open1();

  /**
   * Returns the meta object for the attribute '{@link com.example.simple.Quote#getHigh <em>High</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>High</em>'.
   * @see com.example.simple.Quote#getHigh()
   * @see #getQuote()
   * @generated
   */
  EAttribute getQuote_High();

  /**
   * Returns the meta object for the attribute '{@link com.example.simple.Quote#getLow <em>Low</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Low</em>'.
   * @see com.example.simple.Quote#getLow()
   * @see #getQuote()
   * @generated
   */
  EAttribute getQuote_Low();

  /**
   * Returns the meta object for the attribute '{@link com.example.simple.Quote#getVolume <em>Volume</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Volume</em>'.
   * @see com.example.simple.Quote#getVolume()
   * @see #getQuote()
   * @generated
   */
  EAttribute getQuote_Volume();

  /**
   * Returns the meta object for the attribute '{@link com.example.simple.Quote#getChange1 <em>Change1</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Change1</em>'.
   * @see com.example.simple.Quote#getChange1()
   * @see #getQuote()
   * @generated
   */
  EAttribute getQuote_Change1();

  /**
   * Returns the meta object for the containment reference list '{@link com.example.simple.Quote#getQuotes <em>Quotes</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Quotes</em>'.
   * @see com.example.simple.Quote#getQuotes()
   * @see #getQuote()
   * @generated
   */
  EReference getQuote_Quotes();

  /**
   * Returns the factory that creates the instances of the model.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the factory that creates the instances of the model.
   * @generated
   */
  SimpleFactory getSimpleFactory();

} //SimplePackage
