/**
 * <copyright>
 * </copyright>
 *
 * $Id: LibraryImpl.java,v 1.3 2007/05/11 10:04:20 marcelop Exp $
 */
package org.eclipse.example.library.impl;

import java.util.Collection;
import java.util.Map;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.EMap;

import org.eclipse.emf.ecore.EClass;
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
 *   <li>{@link org.eclipse.example.library.impl.LibraryImpl#getOptions <em>Options</em>}</li>
 *   <li>{@link org.eclipse.example.library.impl.LibraryImpl#getWriterByIDMap <em>Writer By ID Map</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class LibraryImpl extends EObjectImpl implements Library
{
  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public static final String copyright = "This is my code.";

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
  protected EList writers;

  /**
   * The cached value of the '{@link #getBooks() <em>Books</em>}' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getBooks()
   * @generated
   * @ordered
   */
  protected EList books;

  /**
   * The cached value of the '{@link #getSpecialBooks() <em>Special Books</em>}' reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getSpecialBooks()
   * @generated
   * @ordered
   */
  protected EList specialBooks;

  /**
   * The cached value of the '{@link #getBookByTitleMap() <em>Book By Title Map</em>}' map.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getBookByTitleMap()
   * @generated
   * @ordered
   */
  protected EMap bookByTitleMap;

  /**
   * The cached value of the '{@link #getWriterByNameMap() <em>Writer By Name Map</em>}' map.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getWriterByNameMap()
   * @generated
   * @ordered
   */
  protected EMap writerByNameMap;

  /**
   * The default value of the '{@link #getOptions() <em>Options</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getOptions()
   * @generated
   * @ordered
   */
  protected static final Map OPTIONS_EDEFAULT = null;

  /**
   * The cached value of the '{@link #getOptions() <em>Options</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getOptions()
   * @generated
   * @ordered
   */
  protected Map options = OPTIONS_EDEFAULT;

  /**
   * The default value of the '{@link #getWriterByIDMap() <em>Writer By ID Map</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getWriterByIDMap()
   * @generated
   * @ordered
   */
  protected static final Map WRITER_BY_ID_MAP_EDEFAULT = null;

  /**
   * The cached value of the '{@link #getWriterByIDMap() <em>Writer By ID Map</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getWriterByIDMap()
   * @generated
   * @ordered
   */
  protected Map writerByIDMap = WRITER_BY_ID_MAP_EDEFAULT;

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
  public EList getWriters()
  {
    if (writers == null)
    {
      writers = new EObjectContainmentEList(Writer.class, this, LibraryPackage.LIBRARY__WRITERS);
    }
    return writers;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EList getBooks()
  {
    if (books == null)
    {
      books = new EObjectContainmentEList(Book.class, this, LibraryPackage.LIBRARY__BOOKS);
    }
    return books;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EList getSpecialBooks()
  {
    if (specialBooks == null)
    {
      specialBooks = new EObjectResolvingEList(Book.class, this, LibraryPackage.LIBRARY__SPECIAL_BOOKS);
    }
    return specialBooks;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EMap getBookByTitleMap()
  {
    if (bookByTitleMap == null)
    {
      bookByTitleMap = new EcoreEMap(LibraryPackage.Literals.ESTRING_TO_BOOK_MAP_ENTRY, EStringToBookMapEntryImpl.class, this, LibraryPackage.LIBRARY__BOOK_BY_TITLE_MAP);
    }
    return bookByTitleMap;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EMap getWriterByNameMap()
  {
    if (writerByNameMap == null)
    {
      writerByNameMap = new EcoreEMap(LibraryPackage.Literals.ESTRING_TO_WRITER_MAP_ENTRY, EStringToWriterMapEntryImpl.class, this, LibraryPackage.LIBRARY__WRITER_BY_NAME_MAP);
    }
    return writerByNameMap;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public Map getOptions()
  {
    return options;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setOptions(Map newOptions)
  {
    Map oldOptions = options;
    options = newOptions;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, LibraryPackage.LIBRARY__OPTIONS, oldOptions, options));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public Map getWriterByIDMap()
  {
    return writerByIDMap;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setWriterByIDMap(Map newWriterByIDMap)
  {
    Map oldWriterByIDMap = writerByIDMap;
    writerByIDMap = newWriterByIDMap;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, LibraryPackage.LIBRARY__WRITER_BY_ID_MAP, oldWriterByIDMap, writerByIDMap));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs)
  {
    switch (featureID)
    {
      case LibraryPackage.LIBRARY__WRITERS:
        return ((InternalEList)getWriters()).basicRemove(otherEnd, msgs);
      case LibraryPackage.LIBRARY__BOOKS:
        return ((InternalEList)getBooks()).basicRemove(otherEnd, msgs);
      case LibraryPackage.LIBRARY__BOOK_BY_TITLE_MAP:
        return ((InternalEList)getBookByTitleMap()).basicRemove(otherEnd, msgs);
      case LibraryPackage.LIBRARY__WRITER_BY_NAME_MAP:
        return ((InternalEList)getWriterByNameMap()).basicRemove(otherEnd, msgs);
    }
    return super.eInverseRemove(otherEnd, featureID, msgs);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
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
        if (coreType) return getBookByTitleMap();
        else return getBookByTitleMap().map();
      case LibraryPackage.LIBRARY__WRITER_BY_NAME_MAP:
        if (coreType) return getWriterByNameMap();
        else return getWriterByNameMap().map();
      case LibraryPackage.LIBRARY__OPTIONS:
        return getOptions();
      case LibraryPackage.LIBRARY__WRITER_BY_ID_MAP:
        return getWriterByIDMap();
    }
    return super.eGet(featureID, resolve, coreType);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void eSet(int featureID, Object newValue)
  {
    switch (featureID)
    {
      case LibraryPackage.LIBRARY__NAME:
        setName((String)newValue);
        return;
      case LibraryPackage.LIBRARY__WRITERS:
        getWriters().clear();
        getWriters().addAll((Collection)newValue);
        return;
      case LibraryPackage.LIBRARY__BOOKS:
        getBooks().clear();
        getBooks().addAll((Collection)newValue);
        return;
      case LibraryPackage.LIBRARY__SPECIAL_BOOKS:
        getSpecialBooks().clear();
        getSpecialBooks().addAll((Collection)newValue);
        return;
      case LibraryPackage.LIBRARY__BOOK_BY_TITLE_MAP:
        ((EStructuralFeature.Setting)getBookByTitleMap()).set(newValue);
        return;
      case LibraryPackage.LIBRARY__WRITER_BY_NAME_MAP:
        ((EStructuralFeature.Setting)getWriterByNameMap()).set(newValue);
        return;
      case LibraryPackage.LIBRARY__OPTIONS:
        setOptions((Map)newValue);
        return;
      case LibraryPackage.LIBRARY__WRITER_BY_ID_MAP:
        setWriterByIDMap((Map)newValue);
        return;
    }
    super.eSet(featureID, newValue);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
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
        getBookByTitleMap().clear();
        return;
      case LibraryPackage.LIBRARY__WRITER_BY_NAME_MAP:
        getWriterByNameMap().clear();
        return;
      case LibraryPackage.LIBRARY__OPTIONS:
        setOptions(OPTIONS_EDEFAULT);
        return;
      case LibraryPackage.LIBRARY__WRITER_BY_ID_MAP:
        setWriterByIDMap(WRITER_BY_ID_MAP_EDEFAULT);
        return;
    }
    super.eUnset(featureID);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
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
        return bookByTitleMap != null && !bookByTitleMap.isEmpty();
      case LibraryPackage.LIBRARY__WRITER_BY_NAME_MAP:
        return writerByNameMap != null && !writerByNameMap.isEmpty();
      case LibraryPackage.LIBRARY__OPTIONS:
        return OPTIONS_EDEFAULT == null ? options != null : !OPTIONS_EDEFAULT.equals(options);
      case LibraryPackage.LIBRARY__WRITER_BY_ID_MAP:
        return WRITER_BY_ID_MAP_EDEFAULT == null ? writerByIDMap != null : !WRITER_BY_ID_MAP_EDEFAULT.equals(writerByIDMap);
    }
    return super.eIsSet(featureID);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public String toString()
  {
    if (eIsProxy()) return super.toString();

    StringBuffer result = new StringBuffer(super.toString());
    result.append(" (name: ");
    result.append(name);
    result.append(", options: ");
    result.append(options);
    result.append(", writerByIDMap: ");
    result.append(writerByIDMap);
    result.append(')');
    return result.toString();
  }

} //LibraryImpl
