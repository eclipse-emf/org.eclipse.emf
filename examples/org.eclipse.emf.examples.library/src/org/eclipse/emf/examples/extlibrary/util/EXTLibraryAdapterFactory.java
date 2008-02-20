/**
 * <copyright>
 *
 * Copyright (c) 2005-2006 IBM Corporation and others.
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
 * $Id: EXTLibraryAdapterFactory.java,v 1.4 2008/02/20 22:12:51 emerks Exp $
 */
package org.eclipse.emf.examples.extlibrary.util;


import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notifier;

import org.eclipse.emf.common.notify.impl.AdapterFactoryImpl;

import org.eclipse.emf.ecore.EObject;

import org.eclipse.emf.examples.extlibrary.*;


/**
 * <!-- begin-user-doc -->
 * The <b>Adapter Factory</b> for the model.
 * It provides an adapter <code>createXXX</code> method for each class of the model.
 * <!-- end-user-doc -->
 * @see org.eclipse.emf.examples.extlibrary.EXTLibraryPackage
 * @generated
 */
public class EXTLibraryAdapterFactory extends AdapterFactoryImpl
{
  /**
   * The cached model package.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected static EXTLibraryPackage modelPackage;

  /**
   * Creates an instance of the adapter factory.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EXTLibraryAdapterFactory()
  {
    if (modelPackage == null)
    {
      modelPackage = EXTLibraryPackage.eINSTANCE;
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
  protected EXTLibrarySwitch<Adapter> modelSwitch =
    new EXTLibrarySwitch<Adapter>()
    {
      @Override
      public Adapter caseBook(Book object)
      {
        return createBookAdapter();
      }
      @Override
      public Adapter caseLibrary(Library object)
      {
        return createLibraryAdapter();
      }
      @Override
      public Adapter caseWriter(Writer object)
      {
        return createWriterAdapter();
      }
      @Override
      public Adapter caseItem(Item object)
      {
        return createItemAdapter();
      }
      @Override
      public Adapter caseLendable(Lendable object)
      {
        return createLendableAdapter();
      }
      @Override
      public Adapter caseCirculatingItem(CirculatingItem object)
      {
        return createCirculatingItemAdapter();
      }
      @Override
      public Adapter casePeriodical(Periodical object)
      {
        return createPeriodicalAdapter();
      }
      @Override
      public Adapter caseAudioVisualItem(AudioVisualItem object)
      {
        return createAudioVisualItemAdapter();
      }
      @Override
      public Adapter caseBookOnTape(BookOnTape object)
      {
        return createBookOnTapeAdapter();
      }
      @Override
      public Adapter caseVideoCassette(VideoCassette object)
      {
        return createVideoCassetteAdapter();
      }
      @Override
      public Adapter caseBorrower(Borrower object)
      {
        return createBorrowerAdapter();
      }
      @Override
      public Adapter casePerson(Person object)
      {
        return createPersonAdapter();
      }
      @Override
      public Adapter caseEmployee(Employee object)
      {
        return createEmployeeAdapter();
      }
      @Override
      public Adapter caseAddressable(Addressable object)
      {
        return createAddressableAdapter();
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
   * Creates a new adapter for an object of class '{@link org.eclipse.emf.examples.extlibrary.Book <em>Book</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.eclipse.emf.examples.extlibrary.Book
   * @generated
   */
  public Adapter createBookAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.eclipse.emf.examples.extlibrary.Library <em>Library</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.eclipse.emf.examples.extlibrary.Library
   * @generated
   */
  public Adapter createLibraryAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.eclipse.emf.examples.extlibrary.Writer <em>Writer</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.eclipse.emf.examples.extlibrary.Writer
   * @generated
   */
  public Adapter createWriterAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.eclipse.emf.examples.extlibrary.Item <em>Item</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.eclipse.emf.examples.extlibrary.Item
   * @generated
   */
  public Adapter createItemAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.eclipse.emf.examples.extlibrary.Lendable <em>Lendable</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.eclipse.emf.examples.extlibrary.Lendable
   * @generated
   */
  public Adapter createLendableAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.eclipse.emf.examples.extlibrary.CirculatingItem <em>Circulating Item</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.eclipse.emf.examples.extlibrary.CirculatingItem
   * @generated
   */
  public Adapter createCirculatingItemAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.eclipse.emf.examples.extlibrary.Periodical <em>Periodical</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.eclipse.emf.examples.extlibrary.Periodical
   * @generated
   */
  public Adapter createPeriodicalAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.eclipse.emf.examples.extlibrary.AudioVisualItem <em>Audio Visual Item</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.eclipse.emf.examples.extlibrary.AudioVisualItem
   * @generated
   */
  public Adapter createAudioVisualItemAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.eclipse.emf.examples.extlibrary.BookOnTape <em>Book On Tape</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.eclipse.emf.examples.extlibrary.BookOnTape
   * @generated
   */
  public Adapter createBookOnTapeAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.eclipse.emf.examples.extlibrary.VideoCassette <em>Video Cassette</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.eclipse.emf.examples.extlibrary.VideoCassette
   * @generated
   */
  public Adapter createVideoCassetteAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.eclipse.emf.examples.extlibrary.Borrower <em>Borrower</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.eclipse.emf.examples.extlibrary.Borrower
   * @generated
   */
  public Adapter createBorrowerAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.eclipse.emf.examples.extlibrary.Person <em>Person</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.eclipse.emf.examples.extlibrary.Person
   * @generated
   */
  public Adapter createPersonAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.eclipse.emf.examples.extlibrary.Employee <em>Employee</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.eclipse.emf.examples.extlibrary.Employee
   * @generated
   */
  public Adapter createEmployeeAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.eclipse.emf.examples.extlibrary.Addressable <em>Addressable</em>}'.
   * <!-- begin-user-doc -->
   * This default implementation returns null so that we can easily ignore cases;
   * it's useful to ignore a case when inheritance will catch all the cases anyway.
   * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.eclipse.emf.examples.extlibrary.Addressable
   * @generated
   */
  public Adapter createAddressableAdapter()
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

} //EXTLibraryAdapterFactory
