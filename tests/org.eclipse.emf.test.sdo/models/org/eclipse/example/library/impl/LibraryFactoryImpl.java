/**
 * <copyright>
 * </copyright>
 *
 * $Id: LibraryFactoryImpl.java,v 1.1 2004/12/19 04:01:08 marcelop Exp $
 */
package org.eclipse.example.library.impl;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EDataType;
import org.eclipse.emf.ecore.EObject;

import org.eclipse.emf.ecore.impl.EFactoryImpl;

import org.eclipse.example.library.*;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Factory</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class LibraryFactoryImpl extends EFactoryImpl implements LibraryFactory {
	/**
	 * Creates and instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public LibraryFactoryImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EObject create(EClass eClass) {
		switch (eClass.getClassifierID()) {
			case LibraryPackage.BOOK: return (EObject)createBook();
			case LibraryPackage.LIBRARY: return (EObject)createLibrary();
			case LibraryPackage.WRITER: return (EObject)createWriter();
			default:
				throw new IllegalArgumentException("The class '" + eClass.getName() + "' is not a valid classifier");
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Object createFromString(EDataType eDataType, String initialValue) {
		switch (eDataType.getClassifierID()) {
			case LibraryPackage.BOOK_CATEGORY: {
				BookCategory result = BookCategory.get(initialValue);
				if (result == null) throw new IllegalArgumentException("The value '" + initialValue + "' is not a valid enumerator of '" + eDataType.getName() + "'");
				return result;
			}
			default:
				throw new IllegalArgumentException("The datatype '" + eDataType.getName() + "' is not a valid classifier");
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String convertToString(EDataType eDataType, Object instanceValue) {
		switch (eDataType.getClassifierID()) {
			case LibraryPackage.BOOK_CATEGORY:
				return instanceValue == null ? null : instanceValue.toString();
			default:
				throw new IllegalArgumentException("The datatype '" + eDataType.getName() + "' is not a valid classifier");
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Book createBook() {
		BookImpl book = new BookImpl();
		return book;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Library createLibrary() {
		LibraryImpl library = new LibraryImpl();
		return library;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Writer createWriter() {
		WriterImpl writer = new WriterImpl();
		return writer;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public LibraryPackage getLibraryPackage() {
		return (LibraryPackage)getEPackage();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @deprecated
	 * @generated
	 */
	public static LibraryPackage getPackage() {
		return LibraryPackage.eINSTANCE;
	}

} //LibraryFactoryImpl
