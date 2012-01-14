/**
 * Copyright (c) 2006 IBM Corporation and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *   IBM - Initial API and implementation
 */
package org.eclipse.emf.mapping.ecore2xml.util;

import java.util.Map;

import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.Notifier;

import org.eclipse.emf.common.notify.impl.AdapterFactoryImpl;

import org.eclipse.emf.ecore.ENamedElement;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.mapping.ecore2xml.*;

/**
 * <!-- begin-user-doc -->
 * The <b>Adapter Factory</b> for the model.
 * It provides an adapter <code>createXXX</code> method for each class of the model.
 * <!-- end-user-doc -->
 * @see org.eclipse.emf.mapping.ecore2xml.Ecore2XMLPackage
 * @generated
 */
public class Ecore2XMLAdapterFactory extends AdapterFactoryImpl
{
  
  /**
   * The cached model package.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected static Ecore2XMLPackage modelPackage;
  
  /**
   * Creates an instance of the adapter factory.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public Ecore2XMLAdapterFactory()
  {
    if (modelPackage == null)
    {
      modelPackage = Ecore2XMLPackage.eINSTANCE;
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
  protected Ecore2XMLSwitch<Adapter> modelSwitch =
    new Ecore2XMLSwitch<Adapter>()
    {
      @Override
      public Adapter caseXMLInfo(XMLInfo object)
      {
        return createXMLInfoAdapter();
      }
      @Override
      public Adapter caseXMLMap(XMLMap object)
      {
        return createXMLMapAdapter();
      }
      @Override
      public Adapter caseENamedElementToXMLInfoMapEntry(Map.Entry<ENamedElement, XMLInfo> object)
      {
        return createENamedElementToXMLInfoMapEntryAdapter();
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
   * Creates a new adapter for an object of class '{@link org.eclipse.emf.mapping.ecore2xml.XMLInfo <em>XML Info</em>}'.
   * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.eclipse.emf.mapping.ecore2xml.XMLInfo
   * @generated
   */
    public Adapter createXMLInfoAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link org.eclipse.emf.mapping.ecore2xml.XMLMap <em>XML Map</em>}'.
   * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
   * @return the new adapter.
   * @see org.eclipse.emf.mapping.ecore2xml.XMLMap
   * @generated
   */
    public Adapter createXMLMapAdapter()
  {
    return null;
  }

  /**
   * Creates a new adapter for an object of class '{@link java.util.Map.Entry <em>ENamed Element To XML Info Map Entry</em>}'.
   * <!-- begin-user-doc -->
     * This default implementation returns null so that we can easily ignore cases;
     * it's useful to ignore a case when inheritance will catch all the cases anyway.
     * <!-- end-user-doc -->
   * @return the new adapter.
   * @see java.util.Map.Entry
   * @generated
   */
    public Adapter createENamedElementToXMLInfoMapEntryAdapter()
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

} //Ecore2XMLAdapterFactory
