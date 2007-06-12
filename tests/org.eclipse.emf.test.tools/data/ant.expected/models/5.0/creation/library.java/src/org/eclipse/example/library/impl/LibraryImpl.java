/**
 * This is my code.
 *
 * $Id: LibraryImpl.java,v 1.6 2007/06/12 00:20:35 marcelop Exp $
 */
package org.eclipse.example.library.impl;

import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.EMap;
import org.eclipse.emf.common.util.URI;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.EObjectImpl;

import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.EObjectResolvingEList;
import org.eclipse.emf.ecore.util.EcoreEMap;
import org.eclipse.emf.ecore.util.InternalEList;

import org.eclipse.example.library.Book;
import org.eclipse.example.library.Library;
import org.eclipse.example.library.LibraryPackage;
import org.eclipse.example.library.Writer;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Library</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link org.eclipse.example.library.impl.LibraryImpl#getName <em>Name</em>}</li>
 *   <li>{@link org.eclipse.example.library.impl.LibraryImpl#getWriters <em>Writers</em>}</li>
 *   <li>{@link org.eclipse.example.library.impl.LibraryImpl#getBooks <em>Books</em>}</li>
 *   <li>{@link org.eclipse.example.library.impl.LibraryImpl#getSpecialBooks <em>Special Books</em>}</li>
 *   <li>{@link org.eclipse.example.library.impl.LibraryImpl#getBookByTitleMap <em>Book By Title Map</em>}</li>
 *   <li>{@link org.eclipse.example.library.impl.LibraryImpl#getWriterByNameMap <em>Writer By Name Map</em>}</li>
 *   <li>{@link org.eclipse.example.library.impl.LibraryImpl#getWriterByIDMap <em>Writer By ID Map</em>}</li>
 *   <li>{@link org.eclipse.example.library.impl.LibraryImpl#getOptions <em>Options</em>}</li>
 *   <li>{@link org.eclipse.example.library.impl.LibraryImpl#getMap1 <em>Map1</em>}</li>
 *   <li>{@link org.eclipse.example.library.impl.LibraryImpl#getURIs_1 <em>UR Is 1</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class LibraryImpl extends EObjectImpl implements Library
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
   * The cached value of the '{@link #getWriters() <em>Writers</em>}' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getWriters()
   * @generated
   * @ordered
   */
  protected EList<Writer> writers;

  /**
   * The cached value of the '{@link #getBooks() <em>Books</em>}' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getBooks()
   * @generated
   * @ordered
   */
  protected EList<Book> books;

  /**
   * The cached value of the '{@link #getSpecialBooks() <em>Special Books</em>}' reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getSpecialBooks()
   * @generated
   * @ordered
   */
  protected EList<Book> specialBooks;

  /**
   * The cached value of the '{@link #getBookByTitleMap() <em>Book By Title Map</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getBookByTitleMap()
   * @generated
   * @ordered
   */
  protected Map<String, Book> bookByTitleMap;

  /**
   * The cached value of the '{@link #getWriterByNameMap() <em>Writer By Name Map</em>}' map.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getWriterByNameMap()
   * @generated
   * @ordered
   */
  protected EMap<String, Writer> writerByNameMap;

  /**
   * The cached value of the '{@link #getWriterByIDMap() <em>Writer By ID Map</em>}' map.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getWriterByIDMap()
   * @generated
   * @ordered
   */
  protected EMap<Integer, String> writerByIDMap;

  /**
   * The cached value of the '{@link #getOptions() <em>Options</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getOptions()
   * @generated
   * @ordered
   */
  protected Map<String, String> options;

  /**
   * The cached value of the '{@link #getMap1() <em>Map1</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getMap1()
   * @generated
   * @ordered
   */
  protected Map<EObject, List<URI>> map1;

  /**
   * The cached value of the '{@link #getURIs_1() <em>UR Is 1</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getURIs_1()
   * @generated
   * @ordered
   */
  protected List<URI> uRIs_1;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected LibraryImpl()
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
    return LibraryPackage.Literals.LIBRARY;
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
      eNotify(new ENotificationImpl(this, Notification.SET, LibraryPackage.LIBRARY__NAME, oldName, name));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EList<Writer> getWriters()
  {
    if (writers == null)
    {
      writers = new EObjectContainmentEList<Writer>(Writer.class, this, LibraryPackage.LIBRARY__WRITERS);
    }
    return writers;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EList<Book> getBooks()
  {
    if (books == null)
    {
      books = new EObjectContainmentEList<Book>(Book.class, this, LibraryPackage.LIBRARY__BOOKS);
    }
    return books;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EList<Book> getSpecialBooks()
  {
    if (specialBooks == null)
    {
      specialBooks = new EObjectResolvingEList<Book>(Book.class, this, LibraryPackage.LIBRARY__SPECIAL_BOOKS);
    }
    return specialBooks;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public Map<String, Book> getBookByTitleMap()
  {
    return bookByTitleMap;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setBookByTitleMap(Map<String, Book> newBookByTitleMap)
  {
    Map<String, Book> oldBookByTitleMap = bookByTitleMap;
    bookByTitleMap = newBookByTitleMap;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, LibraryPackage.LIBRARY__BOOK_BY_TITLE_MAP, oldBookByTitleMap, bookByTitleMap));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EMap<String, Writer> getWriterByNameMap()
  {
    if (writerByNameMap == null)
    {
      writerByNameMap = new EcoreEMap<String,Writer>(LibraryPackage.Literals.WRITER_NAME_MAP, WriterNameMapImpl.class, this, LibraryPackage.LIBRARY__WRITER_BY_NAME_MAP);
    }
    return writerByNameMap;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EMap<Integer, String> getWriterByIDMap()
  {
    if (writerByIDMap == null)
    {
      writerByIDMap = new EcoreEMap<Integer,String>(LibraryPackage.Literals.MAP_OF_DATA_TYPES, MapOfDataTypesImpl.class, this, LibraryPackage.LIBRARY__WRITER_BY_ID_MAP);
    }
    return writerByIDMap;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public Map<String, String> getOptions()
  {
    return options;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setOptions(Map<String, String> newOptions)
  {
    Map<String, String> oldOptions = options;
    options = newOptions;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, LibraryPackage.LIBRARY__OPTIONS, oldOptions, options));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public Map<EObject, List<URI>> getMap1()
  {
    return map1;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setMap1(Map<EObject, List<URI>> newMap1)
  {
    Map<EObject, List<URI>> oldMap1 = map1;
    map1 = newMap1;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, LibraryPackage.LIBRARY__MAP1, oldMap1, map1));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public List<URI> getURIs_1()
  {
    return uRIs_1;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setURIs_1(List<URI> newURIs_1)
  {
    List<URI> oldURIs_1 = uRIs_1;
    uRIs_1 = newURIs_1;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, LibraryPackage.LIBRARY__UR_IS_1, oldURIs_1, uRIs_1));
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
      case LibraryPackage.LIBRARY__WRITERS:
        return ((InternalEList<?>)getWriters()).basicRemove(otherEnd, msgs);
      case LibraryPackage.LIBRARY__BOOKS:
        return ((InternalEList<?>)getBooks()).basicRemove(otherEnd, msgs);
      case LibraryPackage.LIBRARY__WRITER_BY_NAME_MAP:
        return ((InternalEList<?>)getWriterByNameMap()).basicRemove(otherEnd, msgs);
      case LibraryPackage.LIBRARY__WRITER_BY_ID_MAP:
        return ((InternalEList<?>)getWriterByIDMap()).basicRemove(otherEnd, msgs);
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
      case LibraryPackage.LIBRARY__NAME:
        return getName();
      case LibraryPackage.LIBRARY__WRITERS:
        return getWriters();
      case LibraryPackage.LIBRARY__BOOKS:
        return getBooks();
      case LibraryPackage.LIBRARY__SPECIAL_BOOKS:
        return getSpecialBooks();
      case LibraryPackage.LIBRARY__BOOK_BY_TITLE_MAP:
        return getBookByTitleMap();
      case LibraryPackage.LIBRARY__WRITER_BY_NAME_MAP:
        if (coreType) return getWriterByNameMap();
        else return getWriterByNameMap().map();
      case LibraryPackage.LIBRARY__WRITER_BY_ID_MAP:
        if (coreType) return getWriterByIDMap();
        else return getWriterByIDMap().map();
      case LibraryPackage.LIBRARY__OPTIONS:
        return getOptions();
      case LibraryPackage.LIBRARY__MAP1:
        return getMap1();
      case LibraryPackage.LIBRARY__UR_IS_1:
        return getURIs_1();
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
      case LibraryPackage.LIBRARY__NAME:
        setName((String)newValue);
        return;
      case LibraryPackage.LIBRARY__WRITERS:
        getWriters().clear();
        getWriters().addAll((Collection<? extends Writer>)newValue);
        return;
      case LibraryPackage.LIBRARY__BOOKS:
        getBooks().clear();
        getBooks().addAll((Collection<? extends Book>)newValue);
        return;
      case LibraryPackage.LIBRARY__SPECIAL_BOOKS:
        getSpecialBooks().clear();
        getSpecialBooks().addAll((Collection<? extends Book>)newValue);
        return;
      case LibraryPackage.LIBRARY__BOOK_BY_TITLE_MAP:
        setBookByTitleMap((Map<String, Book>)newValue);
        return;
      case LibraryPackage.LIBRARY__WRITER_BY_NAME_MAP:
        ((EStructuralFeature.Setting)getWriterByNameMap()).set(newValue);
        return;
      case LibraryPackage.LIBRARY__WRITER_BY_ID_MAP:
        ((EStructuralFeature.Setting)getWriterByIDMap()).set(newValue);
        return;
      case LibraryPackage.LIBRARY__OPTIONS:
        setOptions((Map<String, String>)newValue);
        return;
      case LibraryPackage.LIBRARY__MAP1:
        setMap1((Map<EObject, List<URI>>)newValue);
        return;
      case LibraryPackage.LIBRARY__UR_IS_1:
        setURIs_1((List<URI>)newValue);
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
      case LibraryPackage.LIBRARY__NAME:
        setName(NAME_EDEFAULT);
        return;
      case LibraryPackage.LIBRARY__WRITERS:
        getWriters().clear();
        return;
      case LibraryPackage.LIBRARY__BOOKS:
        getBooks().clear();
        return;
      case LibraryPackage.LIBRARY__SPECIAL_BOOKS:
        getSpecialBooks().clear();
        return;
      case LibraryPackage.LIBRARY__BOOK_BY_TITLE_MAP:
        setBookByTitleMap((Map<String, Book>)null);
        return;
      case LibraryPackage.LIBRARY__WRITER_BY_NAME_MAP:
        getWriterByNameMap().clear();
        return;
      case LibraryPackage.LIBRARY__WRITER_BY_ID_MAP:
        getWriterByIDMap().clear();
        return;
      case LibraryPackage.LIBRARY__OPTIONS:
        setOptions((Map<String, String>)null);
        return;
      case LibraryPackage.LIBRARY__MAP1:
        setMap1((Map<EObject, List<URI>>)null);
        return;
      case LibraryPackage.LIBRARY__UR_IS_1:
        setURIs_1((List<URI>)null);
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
      case LibraryPackage.LIBRARY__NAME:
        return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
      case LibraryPackage.LIBRARY__WRITERS:
        return writers != null && !writers.isEmpty();
      case LibraryPackage.LIBRARY__BOOKS:
        return books != null && !books.isEmpty();
      case LibraryPackage.LIBRARY__SPECIAL_BOOKS:
        return specialBooks != null && !specialBooks.isEmpty();
      case LibraryPackage.LIBRARY__BOOK_BY_TITLE_MAP:
        return bookByTitleMap != null;
      case LibraryPackage.LIBRARY__WRITER_BY_NAME_MAP:
        return writerByNameMap != null && !writerByNameMap.isEmpty();
      case LibraryPackage.LIBRARY__WRITER_BY_ID_MAP:
        return writerByIDMap != null && !writerByIDMap.isEmpty();
      case LibraryPackage.LIBRARY__OPTIONS:
        return options != null;
      case LibraryPackage.LIBRARY__MAP1:
        return map1 != null;
      case LibraryPackage.LIBRARY__UR_IS_1:
        return uRIs_1 != null;
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
    result.append(", bookByTitleMap: ");
    result.append(bookByTitleMap);
    result.append(", options: ");
    result.append(options);
    result.append(", map1: ");
    result.append(map1);
    result.append(", uRIs_1: ");
    result.append(uRIs_1);
    result.append(')');
    return result.toString();
  }

} //LibraryImpl
