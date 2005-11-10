/**
 * <copyright>
 *
 * Copyright (c) 2005 IBM Corporation and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: 
 *   IBM - Initial API and implementation
 *
 * </copyright>
 *
 * $Id: EXTLibraryPackage.java,v 1.1 2005/11/10 18:55:52 marcelop Exp $
 */
package org.eclipse.emf.examples.extlibrary;


import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EEnum;
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
 * @see org.eclipse.emf.examples.extlibrary.EXTLibraryFactory
 * @model kind="package"
 * @generated
 */
public interface EXTLibraryPackage extends EPackage{
  /**
   * The package name.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  String eNAME = "extlibrary"; //$NON-NLS-1$

  /**
   * The package namespace URI.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  String eNS_URI = "http:///org/eclipse/emf/examples/library/extlibrary.ecore/1.0.0"; //$NON-NLS-1$

  /**
   * The package namespace name.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  String eNS_PREFIX = "extlib"; //$NON-NLS-1$

  /**
   * The singleton instance of the package.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  EXTLibraryPackage eINSTANCE = org.eclipse.emf.examples.extlibrary.impl.EXTLibraryPackageImpl.init();

  /**
   * The meta object id for the '{@link org.eclipse.emf.examples.extlibrary.impl.ItemImpl <em>Item</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.eclipse.emf.examples.extlibrary.impl.ItemImpl
   * @see org.eclipse.emf.examples.extlibrary.impl.EXTLibraryPackageImpl#getItem()
   * @generated
   */
  int ITEM = 3;

  /**
   * The feature id for the '<em><b>Publication Date</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ITEM__PUBLICATION_DATE = 0;

  /**
   * The number of structural features of the '<em>Item</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ITEM_FEATURE_COUNT = 1;

  /**
   * The meta object id for the '{@link org.eclipse.emf.examples.extlibrary.impl.CirculatingItemImpl <em>Circulating Item</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.eclipse.emf.examples.extlibrary.impl.CirculatingItemImpl
   * @see org.eclipse.emf.examples.extlibrary.impl.EXTLibraryPackageImpl#getCirculatingItem()
   * @generated
   */
  int CIRCULATING_ITEM = 5;

  /**
   * The feature id for the '<em><b>Publication Date</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int CIRCULATING_ITEM__PUBLICATION_DATE = ITEM__PUBLICATION_DATE;

  /**
   * The feature id for the '<em><b>Copies</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int CIRCULATING_ITEM__COPIES = ITEM_FEATURE_COUNT + 0;

  /**
   * The feature id for the '<em><b>Borrowers</b></em>' reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int CIRCULATING_ITEM__BORROWERS = ITEM_FEATURE_COUNT + 1;

  /**
   * The number of structural features of the '<em>Circulating Item</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int CIRCULATING_ITEM_FEATURE_COUNT = ITEM_FEATURE_COUNT + 2;

  /**
   * The meta object id for the '{@link org.eclipse.emf.examples.extlibrary.impl.BookImpl <em>Book</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.eclipse.emf.examples.extlibrary.impl.BookImpl
   * @see org.eclipse.emf.examples.extlibrary.impl.EXTLibraryPackageImpl#getBook()
   * @generated
   */
  int BOOK = 0;

  /**
   * The feature id for the '<em><b>Publication Date</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int BOOK__PUBLICATION_DATE = CIRCULATING_ITEM__PUBLICATION_DATE;

  /**
   * The feature id for the '<em><b>Copies</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int BOOK__COPIES = CIRCULATING_ITEM__COPIES;

  /**
   * The feature id for the '<em><b>Borrowers</b></em>' reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int BOOK__BORROWERS = CIRCULATING_ITEM__BORROWERS;

  /**
   * The feature id for the '<em><b>Title</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int BOOK__TITLE = CIRCULATING_ITEM_FEATURE_COUNT + 0;

  /**
   * The feature id for the '<em><b>Pages</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int BOOK__PAGES = CIRCULATING_ITEM_FEATURE_COUNT + 1;

  /**
   * The feature id for the '<em><b>Category</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int BOOK__CATEGORY = CIRCULATING_ITEM_FEATURE_COUNT + 2;

  /**
   * The feature id for the '<em><b>Author</b></em>' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int BOOK__AUTHOR = CIRCULATING_ITEM_FEATURE_COUNT + 3;

  /**
   * The number of structural features of the '<em>Book</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int BOOK_FEATURE_COUNT = CIRCULATING_ITEM_FEATURE_COUNT + 4;

  /**
   * The meta object id for the '{@link org.eclipse.emf.examples.extlibrary.Addressable <em>Addressable</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.eclipse.emf.examples.extlibrary.Addressable
   * @see org.eclipse.emf.examples.extlibrary.impl.EXTLibraryPackageImpl#getAddressable()
   * @generated
   */
  int ADDRESSABLE = 13;

  /**
   * The feature id for the '<em><b>Address</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ADDRESSABLE__ADDRESS = 0;

  /**
   * The number of structural features of the '<em>Addressable</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ADDRESSABLE_FEATURE_COUNT = 1;

  /**
   * The meta object id for the '{@link org.eclipse.emf.examples.extlibrary.impl.LibraryImpl <em>Library</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.eclipse.emf.examples.extlibrary.impl.LibraryImpl
   * @see org.eclipse.emf.examples.extlibrary.impl.EXTLibraryPackageImpl#getLibrary()
   * @generated
   */
  int LIBRARY = 1;

  /**
   * The feature id for the '<em><b>Address</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int LIBRARY__ADDRESS = ADDRESSABLE__ADDRESS;

  /**
   * The feature id for the '<em><b>Name</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int LIBRARY__NAME = ADDRESSABLE_FEATURE_COUNT + 0;

  /**
   * The feature id for the '<em><b>Writers</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int LIBRARY__WRITERS = ADDRESSABLE_FEATURE_COUNT + 1;

  /**
   * The feature id for the '<em><b>Employees</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int LIBRARY__EMPLOYEES = ADDRESSABLE_FEATURE_COUNT + 2;

  /**
   * The feature id for the '<em><b>Borrowers</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int LIBRARY__BORROWERS = ADDRESSABLE_FEATURE_COUNT + 3;

  /**
   * The feature id for the '<em><b>Stock</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int LIBRARY__STOCK = ADDRESSABLE_FEATURE_COUNT + 4;

  /**
   * The feature id for the '<em><b>Books</b></em>' reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int LIBRARY__BOOKS = ADDRESSABLE_FEATURE_COUNT + 5;

  /**
   * The feature id for the '<em><b>Branches</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int LIBRARY__BRANCHES = ADDRESSABLE_FEATURE_COUNT + 6;

  /**
   * The feature id for the '<em><b>Parent Branch</b></em>' container reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int LIBRARY__PARENT_BRANCH = ADDRESSABLE_FEATURE_COUNT + 7;

  /**
   * The feature id for the '<em><b>People</b></em>' attribute list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int LIBRARY__PEOPLE = ADDRESSABLE_FEATURE_COUNT + 8;

  /**
   * The number of structural features of the '<em>Library</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int LIBRARY_FEATURE_COUNT = ADDRESSABLE_FEATURE_COUNT + 9;

  /**
   * The meta object id for the '{@link org.eclipse.emf.examples.extlibrary.impl.PersonImpl <em>Person</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.eclipse.emf.examples.extlibrary.impl.PersonImpl
   * @see org.eclipse.emf.examples.extlibrary.impl.EXTLibraryPackageImpl#getPerson()
   * @generated
   */
  int PERSON = 11;

  /**
   * The feature id for the '<em><b>Address</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int PERSON__ADDRESS = ADDRESSABLE__ADDRESS;

  /**
   * The feature id for the '<em><b>First Name</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int PERSON__FIRST_NAME = ADDRESSABLE_FEATURE_COUNT + 0;

  /**
   * The feature id for the '<em><b>Last Name</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int PERSON__LAST_NAME = ADDRESSABLE_FEATURE_COUNT + 1;

  /**
   * The number of structural features of the '<em>Person</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int PERSON_FEATURE_COUNT = ADDRESSABLE_FEATURE_COUNT + 2;

  /**
   * The meta object id for the '{@link org.eclipse.emf.examples.extlibrary.impl.WriterImpl <em>Writer</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.eclipse.emf.examples.extlibrary.impl.WriterImpl
   * @see org.eclipse.emf.examples.extlibrary.impl.EXTLibraryPackageImpl#getWriter()
   * @generated
   */
  int WRITER = 2;

  /**
   * The feature id for the '<em><b>Address</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int WRITER__ADDRESS = PERSON__ADDRESS;

  /**
   * The feature id for the '<em><b>First Name</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int WRITER__FIRST_NAME = PERSON__FIRST_NAME;

  /**
   * The feature id for the '<em><b>Last Name</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int WRITER__LAST_NAME = PERSON__LAST_NAME;

  /**
   * The feature id for the '<em><b>Name</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int WRITER__NAME = PERSON_FEATURE_COUNT + 0;

  /**
   * The feature id for the '<em><b>Books</b></em>' reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int WRITER__BOOKS = PERSON_FEATURE_COUNT + 1;

  /**
   * The number of structural features of the '<em>Writer</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int WRITER_FEATURE_COUNT = PERSON_FEATURE_COUNT + 2;

  /**
   * The meta object id for the '{@link org.eclipse.emf.examples.extlibrary.Lendable <em>Lendable</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.eclipse.emf.examples.extlibrary.Lendable
   * @see org.eclipse.emf.examples.extlibrary.impl.EXTLibraryPackageImpl#getLendable()
   * @generated
   */
  int LENDABLE = 4;

  /**
   * The feature id for the '<em><b>Copies</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int LENDABLE__COPIES = 0;

  /**
   * The feature id for the '<em><b>Borrowers</b></em>' reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int LENDABLE__BORROWERS = 1;

  /**
   * The number of structural features of the '<em>Lendable</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int LENDABLE_FEATURE_COUNT = 2;

  /**
   * The meta object id for the '{@link org.eclipse.emf.examples.extlibrary.impl.PeriodicalImpl <em>Periodical</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.eclipse.emf.examples.extlibrary.impl.PeriodicalImpl
   * @see org.eclipse.emf.examples.extlibrary.impl.EXTLibraryPackageImpl#getPeriodical()
   * @generated
   */
  int PERIODICAL = 6;

  /**
   * The feature id for the '<em><b>Publication Date</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int PERIODICAL__PUBLICATION_DATE = ITEM__PUBLICATION_DATE;

  /**
   * The feature id for the '<em><b>Title</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int PERIODICAL__TITLE = ITEM_FEATURE_COUNT + 0;

  /**
   * The feature id for the '<em><b>Issues Per Year</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int PERIODICAL__ISSUES_PER_YEAR = ITEM_FEATURE_COUNT + 1;

  /**
   * The number of structural features of the '<em>Periodical</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int PERIODICAL_FEATURE_COUNT = ITEM_FEATURE_COUNT + 2;

  /**
   * The meta object id for the '{@link org.eclipse.emf.examples.extlibrary.impl.AudoVisualItemImpl <em>Audo Visual Item</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.eclipse.emf.examples.extlibrary.impl.AudoVisualItemImpl
   * @see org.eclipse.emf.examples.extlibrary.impl.EXTLibraryPackageImpl#getAudoVisualItem()
   * @generated
   */
  int AUDO_VISUAL_ITEM = 7;

  /**
   * The feature id for the '<em><b>Publication Date</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int AUDO_VISUAL_ITEM__PUBLICATION_DATE = CIRCULATING_ITEM__PUBLICATION_DATE;

  /**
   * The feature id for the '<em><b>Copies</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int AUDO_VISUAL_ITEM__COPIES = CIRCULATING_ITEM__COPIES;

  /**
   * The feature id for the '<em><b>Borrowers</b></em>' reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int AUDO_VISUAL_ITEM__BORROWERS = CIRCULATING_ITEM__BORROWERS;

  /**
   * The feature id for the '<em><b>Title</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int AUDO_VISUAL_ITEM__TITLE = CIRCULATING_ITEM_FEATURE_COUNT + 0;

  /**
   * The feature id for the '<em><b>Minutes Length</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int AUDO_VISUAL_ITEM__MINUTES_LENGTH = CIRCULATING_ITEM_FEATURE_COUNT + 1;

  /**
   * The feature id for the '<em><b>Damaged</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int AUDO_VISUAL_ITEM__DAMAGED = CIRCULATING_ITEM_FEATURE_COUNT + 2;

  /**
   * The number of structural features of the '<em>Audo Visual Item</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int AUDO_VISUAL_ITEM_FEATURE_COUNT = CIRCULATING_ITEM_FEATURE_COUNT + 3;

  /**
   * The meta object id for the '{@link org.eclipse.emf.examples.extlibrary.impl.BookOnTapeImpl <em>Book On Tape</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.eclipse.emf.examples.extlibrary.impl.BookOnTapeImpl
   * @see org.eclipse.emf.examples.extlibrary.impl.EXTLibraryPackageImpl#getBookOnTape()
   * @generated
   */
  int BOOK_ON_TAPE = 8;

  /**
   * The feature id for the '<em><b>Publication Date</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int BOOK_ON_TAPE__PUBLICATION_DATE = AUDO_VISUAL_ITEM__PUBLICATION_DATE;

  /**
   * The feature id for the '<em><b>Copies</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int BOOK_ON_TAPE__COPIES = AUDO_VISUAL_ITEM__COPIES;

  /**
   * The feature id for the '<em><b>Borrowers</b></em>' reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int BOOK_ON_TAPE__BORROWERS = AUDO_VISUAL_ITEM__BORROWERS;

  /**
   * The feature id for the '<em><b>Title</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int BOOK_ON_TAPE__TITLE = AUDO_VISUAL_ITEM__TITLE;

  /**
   * The feature id for the '<em><b>Minutes Length</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int BOOK_ON_TAPE__MINUTES_LENGTH = AUDO_VISUAL_ITEM__MINUTES_LENGTH;

  /**
   * The feature id for the '<em><b>Damaged</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int BOOK_ON_TAPE__DAMAGED = AUDO_VISUAL_ITEM__DAMAGED;

  /**
   * The feature id for the '<em><b>Reader</b></em>' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int BOOK_ON_TAPE__READER = AUDO_VISUAL_ITEM_FEATURE_COUNT + 0;

  /**
   * The feature id for the '<em><b>Author</b></em>' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int BOOK_ON_TAPE__AUTHOR = AUDO_VISUAL_ITEM_FEATURE_COUNT + 1;

  /**
   * The number of structural features of the '<em>Book On Tape</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int BOOK_ON_TAPE_FEATURE_COUNT = AUDO_VISUAL_ITEM_FEATURE_COUNT + 2;

  /**
   * The meta object id for the '{@link org.eclipse.emf.examples.extlibrary.impl.VideoCassetteImpl <em>Video Cassette</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.eclipse.emf.examples.extlibrary.impl.VideoCassetteImpl
   * @see org.eclipse.emf.examples.extlibrary.impl.EXTLibraryPackageImpl#getVideoCassette()
   * @generated
   */
  int VIDEO_CASSETTE = 9;

  /**
   * The feature id for the '<em><b>Publication Date</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int VIDEO_CASSETTE__PUBLICATION_DATE = AUDO_VISUAL_ITEM__PUBLICATION_DATE;

  /**
   * The feature id for the '<em><b>Copies</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int VIDEO_CASSETTE__COPIES = AUDO_VISUAL_ITEM__COPIES;

  /**
   * The feature id for the '<em><b>Borrowers</b></em>' reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int VIDEO_CASSETTE__BORROWERS = AUDO_VISUAL_ITEM__BORROWERS;

  /**
   * The feature id for the '<em><b>Title</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int VIDEO_CASSETTE__TITLE = AUDO_VISUAL_ITEM__TITLE;

  /**
   * The feature id for the '<em><b>Minutes Length</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int VIDEO_CASSETTE__MINUTES_LENGTH = AUDO_VISUAL_ITEM__MINUTES_LENGTH;

  /**
   * The feature id for the '<em><b>Damaged</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int VIDEO_CASSETTE__DAMAGED = AUDO_VISUAL_ITEM__DAMAGED;

  /**
   * The feature id for the '<em><b>Cast</b></em>' reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int VIDEO_CASSETTE__CAST = AUDO_VISUAL_ITEM_FEATURE_COUNT + 0;

  /**
   * The number of structural features of the '<em>Video Cassette</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int VIDEO_CASSETTE_FEATURE_COUNT = AUDO_VISUAL_ITEM_FEATURE_COUNT + 1;

  /**
   * The meta object id for the '{@link org.eclipse.emf.examples.extlibrary.impl.BorrowerImpl <em>Borrower</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.eclipse.emf.examples.extlibrary.impl.BorrowerImpl
   * @see org.eclipse.emf.examples.extlibrary.impl.EXTLibraryPackageImpl#getBorrower()
   * @generated
   */
  int BORROWER = 10;

  /**
   * The feature id for the '<em><b>Address</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int BORROWER__ADDRESS = PERSON__ADDRESS;

  /**
   * The feature id for the '<em><b>First Name</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int BORROWER__FIRST_NAME = PERSON__FIRST_NAME;

  /**
   * The feature id for the '<em><b>Last Name</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int BORROWER__LAST_NAME = PERSON__LAST_NAME;

  /**
   * The feature id for the '<em><b>Borrowed</b></em>' reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int BORROWER__BORROWED = PERSON_FEATURE_COUNT + 0;

  /**
   * The number of structural features of the '<em>Borrower</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int BORROWER_FEATURE_COUNT = PERSON_FEATURE_COUNT + 1;

  /**
   * The meta object id for the '{@link org.eclipse.emf.examples.extlibrary.impl.EmployeeImpl <em>Employee</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.eclipse.emf.examples.extlibrary.impl.EmployeeImpl
   * @see org.eclipse.emf.examples.extlibrary.impl.EXTLibraryPackageImpl#getEmployee()
   * @generated
   */
  int EMPLOYEE = 12;

  /**
   * The feature id for the '<em><b>Address</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int EMPLOYEE__ADDRESS = PERSON__ADDRESS;

  /**
   * The feature id for the '<em><b>First Name</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int EMPLOYEE__FIRST_NAME = PERSON__FIRST_NAME;

  /**
   * The feature id for the '<em><b>Last Name</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int EMPLOYEE__LAST_NAME = PERSON__LAST_NAME;

  /**
   * The feature id for the '<em><b>Manager</b></em>' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int EMPLOYEE__MANAGER = PERSON_FEATURE_COUNT + 0;

  /**
   * The number of structural features of the '<em>Employee</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int EMPLOYEE_FEATURE_COUNT = PERSON_FEATURE_COUNT + 1;

  /**
   * The meta object id for the '{@link org.eclipse.emf.examples.extlibrary.BookCategory <em>Book Category</em>}' enum.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see org.eclipse.emf.examples.extlibrary.BookCategory
   * @see org.eclipse.emf.examples.extlibrary.impl.EXTLibraryPackageImpl#getBookCategory()
   * @generated
   */
  int BOOK_CATEGORY = 14;

  /**
   * Returns the meta object for class '{@link org.eclipse.emf.examples.extlibrary.Book <em>Book</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Book</em>'.
   * @see org.eclipse.emf.examples.extlibrary.Book
   * @generated
   */
  EClass getBook();

  /**
   * Returns the meta object for the attribute '{@link org.eclipse.emf.examples.extlibrary.Book#getTitle <em>Title</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Title</em>'.
   * @see org.eclipse.emf.examples.extlibrary.Book#getTitle()
   * @see #getBook()
   * @generated
   */
  EAttribute getBook_Title();

  /**
   * Returns the meta object for the attribute '{@link org.eclipse.emf.examples.extlibrary.Book#getPages <em>Pages</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Pages</em>'.
   * @see org.eclipse.emf.examples.extlibrary.Book#getPages()
   * @see #getBook()
   * @generated
   */
  EAttribute getBook_Pages();

  /**
   * Returns the meta object for the attribute '{@link org.eclipse.emf.examples.extlibrary.Book#getCategory <em>Category</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Category</em>'.
   * @see org.eclipse.emf.examples.extlibrary.Book#getCategory()
   * @see #getBook()
   * @generated
   */
  EAttribute getBook_Category();

  /**
   * Returns the meta object for the reference '{@link org.eclipse.emf.examples.extlibrary.Book#getAuthor <em>Author</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the reference '<em>Author</em>'.
   * @see org.eclipse.emf.examples.extlibrary.Book#getAuthor()
   * @see #getBook()
   * @generated
   */
  EReference getBook_Author();

  /**
   * Returns the meta object for class '{@link org.eclipse.emf.examples.extlibrary.Library <em>Library</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Library</em>'.
   * @see org.eclipse.emf.examples.extlibrary.Library
   * @generated
   */
  EClass getLibrary();

  /**
   * Returns the meta object for the attribute '{@link org.eclipse.emf.examples.extlibrary.Library#getName <em>Name</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Name</em>'.
   * @see org.eclipse.emf.examples.extlibrary.Library#getName()
   * @see #getLibrary()
   * @generated
   */
  EAttribute getLibrary_Name();

  /**
   * Returns the meta object for the containment reference list '{@link org.eclipse.emf.examples.extlibrary.Library#getWriters <em>Writers</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Writers</em>'.
   * @see org.eclipse.emf.examples.extlibrary.Library#getWriters()
   * @see #getLibrary()
   * @generated
   */
  EReference getLibrary_Writers();

  /**
   * Returns the meta object for the containment reference list '{@link org.eclipse.emf.examples.extlibrary.Library#getEmployees <em>Employees</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Employees</em>'.
   * @see org.eclipse.emf.examples.extlibrary.Library#getEmployees()
   * @see #getLibrary()
   * @generated
   */
  EReference getLibrary_Employees();

  /**
   * Returns the meta object for the containment reference list '{@link org.eclipse.emf.examples.extlibrary.Library#getBorrowers <em>Borrowers</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Borrowers</em>'.
   * @see org.eclipse.emf.examples.extlibrary.Library#getBorrowers()
   * @see #getLibrary()
   * @generated
   */
  EReference getLibrary_Borrowers();

  /**
   * Returns the meta object for the containment reference list '{@link org.eclipse.emf.examples.extlibrary.Library#getStock <em>Stock</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Stock</em>'.
   * @see org.eclipse.emf.examples.extlibrary.Library#getStock()
   * @see #getLibrary()
   * @generated
   */
  EReference getLibrary_Stock();

  /**
   * Returns the meta object for the reference list '{@link org.eclipse.emf.examples.extlibrary.Library#getBooks <em>Books</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the reference list '<em>Books</em>'.
   * @see org.eclipse.emf.examples.extlibrary.Library#getBooks()
   * @see #getLibrary()
   * @generated
   */
  EReference getLibrary_Books();

  /**
   * Returns the meta object for the containment reference list '{@link org.eclipse.emf.examples.extlibrary.Library#getBranches <em>Branches</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Branches</em>'.
   * @see org.eclipse.emf.examples.extlibrary.Library#getBranches()
   * @see #getLibrary()
   * @generated
   */
  EReference getLibrary_Branches();

  /**
   * Returns the meta object for the container reference '{@link org.eclipse.emf.examples.extlibrary.Library#getParentBranch <em>Parent Branch</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the container reference '<em>Parent Branch</em>'.
   * @see org.eclipse.emf.examples.extlibrary.Library#getParentBranch()
   * @see #getLibrary()
   * @generated
   */
  EReference getLibrary_ParentBranch();

  /**
   * Returns the meta object for the attribute list '{@link org.eclipse.emf.examples.extlibrary.Library#getPeople <em>People</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute list '<em>People</em>'.
   * @see org.eclipse.emf.examples.extlibrary.Library#getPeople()
   * @see #getLibrary()
   * @generated
   */
  EAttribute getLibrary_People();

  /**
   * Returns the meta object for class '{@link org.eclipse.emf.examples.extlibrary.Writer <em>Writer</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Writer</em>'.
   * @see org.eclipse.emf.examples.extlibrary.Writer
   * @generated
   */
  EClass getWriter();

  /**
   * Returns the meta object for the attribute '{@link org.eclipse.emf.examples.extlibrary.Writer#getName <em>Name</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Name</em>'.
   * @see org.eclipse.emf.examples.extlibrary.Writer#getName()
   * @see #getWriter()
   * @generated
   */
  EAttribute getWriter_Name();

  /**
   * Returns the meta object for the reference list '{@link org.eclipse.emf.examples.extlibrary.Writer#getBooks <em>Books</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the reference list '<em>Books</em>'.
   * @see org.eclipse.emf.examples.extlibrary.Writer#getBooks()
   * @see #getWriter()
   * @generated
   */
  EReference getWriter_Books();

  /**
   * Returns the meta object for class '{@link org.eclipse.emf.examples.extlibrary.Item <em>Item</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Item</em>'.
   * @see org.eclipse.emf.examples.extlibrary.Item
   * @generated
   */
  EClass getItem();

  /**
   * Returns the meta object for the attribute '{@link org.eclipse.emf.examples.extlibrary.Item#getPublicationDate <em>Publication Date</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Publication Date</em>'.
   * @see org.eclipse.emf.examples.extlibrary.Item#getPublicationDate()
   * @see #getItem()
   * @generated
   */
  EAttribute getItem_PublicationDate();

  /**
   * Returns the meta object for class '{@link org.eclipse.emf.examples.extlibrary.Lendable <em>Lendable</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Lendable</em>'.
   * @see org.eclipse.emf.examples.extlibrary.Lendable
   * @generated
   */
  EClass getLendable();

  /**
   * Returns the meta object for the attribute '{@link org.eclipse.emf.examples.extlibrary.Lendable#getCopies <em>Copies</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Copies</em>'.
   * @see org.eclipse.emf.examples.extlibrary.Lendable#getCopies()
   * @see #getLendable()
   * @generated
   */
  EAttribute getLendable_Copies();

  /**
   * Returns the meta object for the reference list '{@link org.eclipse.emf.examples.extlibrary.Lendable#getBorrowers <em>Borrowers</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the reference list '<em>Borrowers</em>'.
   * @see org.eclipse.emf.examples.extlibrary.Lendable#getBorrowers()
   * @see #getLendable()
   * @generated
   */
  EReference getLendable_Borrowers();

  /**
   * Returns the meta object for class '{@link org.eclipse.emf.examples.extlibrary.CirculatingItem <em>Circulating Item</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Circulating Item</em>'.
   * @see org.eclipse.emf.examples.extlibrary.CirculatingItem
   * @generated
   */
  EClass getCirculatingItem();

  /**
   * Returns the meta object for class '{@link org.eclipse.emf.examples.extlibrary.Periodical <em>Periodical</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Periodical</em>'.
   * @see org.eclipse.emf.examples.extlibrary.Periodical
   * @generated
   */
  EClass getPeriodical();

  /**
   * Returns the meta object for the attribute '{@link org.eclipse.emf.examples.extlibrary.Periodical#getTitle <em>Title</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Title</em>'.
   * @see org.eclipse.emf.examples.extlibrary.Periodical#getTitle()
   * @see #getPeriodical()
   * @generated
   */
  EAttribute getPeriodical_Title();

  /**
   * Returns the meta object for the attribute '{@link org.eclipse.emf.examples.extlibrary.Periodical#getIssuesPerYear <em>Issues Per Year</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Issues Per Year</em>'.
   * @see org.eclipse.emf.examples.extlibrary.Periodical#getIssuesPerYear()
   * @see #getPeriodical()
   * @generated
   */
  EAttribute getPeriodical_IssuesPerYear();

  /**
   * Returns the meta object for class '{@link org.eclipse.emf.examples.extlibrary.AudoVisualItem <em>Audo Visual Item</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Audo Visual Item</em>'.
   * @see org.eclipse.emf.examples.extlibrary.AudoVisualItem
   * @generated
   */
  EClass getAudoVisualItem();

  /**
   * Returns the meta object for the attribute '{@link org.eclipse.emf.examples.extlibrary.AudoVisualItem#getTitle <em>Title</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Title</em>'.
   * @see org.eclipse.emf.examples.extlibrary.AudoVisualItem#getTitle()
   * @see #getAudoVisualItem()
   * @generated
   */
  EAttribute getAudoVisualItem_Title();

  /**
   * Returns the meta object for the attribute '{@link org.eclipse.emf.examples.extlibrary.AudoVisualItem#getMinutesLength <em>Minutes Length</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Minutes Length</em>'.
   * @see org.eclipse.emf.examples.extlibrary.AudoVisualItem#getMinutesLength()
   * @see #getAudoVisualItem()
   * @generated
   */
  EAttribute getAudoVisualItem_MinutesLength();

  /**
   * Returns the meta object for the attribute '{@link org.eclipse.emf.examples.extlibrary.AudoVisualItem#isDamaged <em>Damaged</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Damaged</em>'.
   * @see org.eclipse.emf.examples.extlibrary.AudoVisualItem#isDamaged()
   * @see #getAudoVisualItem()
   * @generated
   */
  EAttribute getAudoVisualItem_Damaged();

  /**
   * Returns the meta object for class '{@link org.eclipse.emf.examples.extlibrary.BookOnTape <em>Book On Tape</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Book On Tape</em>'.
   * @see org.eclipse.emf.examples.extlibrary.BookOnTape
   * @generated
   */
  EClass getBookOnTape();

  /**
   * Returns the meta object for the reference '{@link org.eclipse.emf.examples.extlibrary.BookOnTape#getReader <em>Reader</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the reference '<em>Reader</em>'.
   * @see org.eclipse.emf.examples.extlibrary.BookOnTape#getReader()
   * @see #getBookOnTape()
   * @generated
   */
  EReference getBookOnTape_Reader();

  /**
   * Returns the meta object for the reference '{@link org.eclipse.emf.examples.extlibrary.BookOnTape#getAuthor <em>Author</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the reference '<em>Author</em>'.
   * @see org.eclipse.emf.examples.extlibrary.BookOnTape#getAuthor()
   * @see #getBookOnTape()
   * @generated
   */
  EReference getBookOnTape_Author();

  /**
   * Returns the meta object for class '{@link org.eclipse.emf.examples.extlibrary.VideoCassette <em>Video Cassette</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Video Cassette</em>'.
   * @see org.eclipse.emf.examples.extlibrary.VideoCassette
   * @generated
   */
  EClass getVideoCassette();

  /**
   * Returns the meta object for the reference list '{@link org.eclipse.emf.examples.extlibrary.VideoCassette#getCast <em>Cast</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the reference list '<em>Cast</em>'.
   * @see org.eclipse.emf.examples.extlibrary.VideoCassette#getCast()
   * @see #getVideoCassette()
   * @generated
   */
  EReference getVideoCassette_Cast();

  /**
   * Returns the meta object for class '{@link org.eclipse.emf.examples.extlibrary.Borrower <em>Borrower</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Borrower</em>'.
   * @see org.eclipse.emf.examples.extlibrary.Borrower
   * @generated
   */
  EClass getBorrower();

  /**
   * Returns the meta object for the reference list '{@link org.eclipse.emf.examples.extlibrary.Borrower#getBorrowed <em>Borrowed</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the reference list '<em>Borrowed</em>'.
   * @see org.eclipse.emf.examples.extlibrary.Borrower#getBorrowed()
   * @see #getBorrower()
   * @generated
   */
  EReference getBorrower_Borrowed();

  /**
   * Returns the meta object for class '{@link org.eclipse.emf.examples.extlibrary.Person <em>Person</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Person</em>'.
   * @see org.eclipse.emf.examples.extlibrary.Person
   * @generated
   */
  EClass getPerson();

  /**
   * Returns the meta object for the attribute '{@link org.eclipse.emf.examples.extlibrary.Person#getFirstName <em>First Name</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>First Name</em>'.
   * @see org.eclipse.emf.examples.extlibrary.Person#getFirstName()
   * @see #getPerson()
   * @generated
   */
  EAttribute getPerson_FirstName();

  /**
   * Returns the meta object for the attribute '{@link org.eclipse.emf.examples.extlibrary.Person#getLastName <em>Last Name</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Last Name</em>'.
   * @see org.eclipse.emf.examples.extlibrary.Person#getLastName()
   * @see #getPerson()
   * @generated
   */
  EAttribute getPerson_LastName();

  /**
   * Returns the meta object for class '{@link org.eclipse.emf.examples.extlibrary.Employee <em>Employee</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Employee</em>'.
   * @see org.eclipse.emf.examples.extlibrary.Employee
   * @generated
   */
  EClass getEmployee();

  /**
   * Returns the meta object for the reference '{@link org.eclipse.emf.examples.extlibrary.Employee#getManager <em>Manager</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the reference '<em>Manager</em>'.
   * @see org.eclipse.emf.examples.extlibrary.Employee#getManager()
   * @see #getEmployee()
   * @generated
   */
  EReference getEmployee_Manager();

  /**
   * Returns the meta object for class '{@link org.eclipse.emf.examples.extlibrary.Addressable <em>Addressable</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Addressable</em>'.
   * @see org.eclipse.emf.examples.extlibrary.Addressable
   * @generated
   */
  EClass getAddressable();

  /**
   * Returns the meta object for the attribute '{@link org.eclipse.emf.examples.extlibrary.Addressable#getAddress <em>Address</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Address</em>'.
   * @see org.eclipse.emf.examples.extlibrary.Addressable#getAddress()
   * @see #getAddressable()
   * @generated
   */
  EAttribute getAddressable_Address();

  /**
   * Returns the meta object for enum '{@link org.eclipse.emf.examples.extlibrary.BookCategory <em>Book Category</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for enum '<em>Book Category</em>'.
   * @see org.eclipse.emf.examples.extlibrary.BookCategory
   * @generated
   */
  EEnum getBookCategory();

  /**
   * Returns the factory that creates the instances of the model.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the factory that creates the instances of the model.
   * @generated
   */
  EXTLibraryFactory getEXTLibraryFactory();

} //EXTLibraryPackage
