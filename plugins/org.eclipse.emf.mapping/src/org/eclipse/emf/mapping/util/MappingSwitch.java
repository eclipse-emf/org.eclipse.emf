/**
 * <copyright>
 *
 * Copyright (c) 2002-2004 IBM Corporation and others.
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
 * $Id: MappingSwitch.java,v 1.5 2005/06/08 06:21:43 nickb Exp $
 */
package org.eclipse.emf.mapping.util;

import java.util.List;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.mapping.*;


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
 * @see org.eclipse.emf.mapping.MappingPackage
 * @generated
 */
public class MappingSwitch 
{
  /**
   * The cached model package
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected static MappingPackage modelPackage;

  /**
   * Creates an instance of the switch.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public MappingSwitch()
  {
    if (modelPackage == null)
    {
      modelPackage = MappingPackage.eINSTANCE;
    }
  }

  /**
   * Calls <code>caseXXX</code> for each class of the model until one returns a non null result; it yields that result.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the first non-null result returned by a <code>caseXXX</code> call.
   * @generated
   */
  public Object doSwitch(EObject theEObject)
  {
    return doSwitch(theEObject.eClass(), theEObject);
  }

  /**
   * Calls <code>caseXXX</code> for each class of the model until one returns a non null result; it yields that result.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the first non-null result returned by a <code>caseXXX</code> call.
   * @generated
   */
  protected Object doSwitch(EClass theEClass, EObject theEObject)
  {
    if (theEClass.eContainer() == modelPackage)
    {
      return doSwitch(theEClass.getClassifierID(), theEObject);
    }
    else
    {
      List eSuperTypes = theEClass.getESuperTypes();
      return
        eSuperTypes.isEmpty() ?
          defaultCase(theEObject) :
          doSwitch((EClass)eSuperTypes.get(0), theEObject);
    }
  }

  /**
   * Calls <code>caseXXX</code> for each class of the model until one returns a non null result; it yields that result.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the first non-null result returned by a <code>caseXXX</code> call.
   * @generated
   */
  protected Object doSwitch(int classifierID, EObject theEObject)
  {
    switch (classifierID)
    {
      case MappingPackage.MAPPING_HELPER:
      {
        MappingHelper mappingHelper = (MappingHelper)theEObject;
        Object result = caseMappingHelper(mappingHelper);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case MappingPackage.MAPPING:
      {
        Mapping mapping = (Mapping)theEObject;
        Object result = caseMapping(mapping);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case MappingPackage.TYPE_CONVERTER:
      {
        TypeConverter typeConverter = (TypeConverter)theEObject;
        Object result = caseTypeConverter(typeConverter);
        if (result == null) result = caseMappingHelper(typeConverter);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case MappingPackage.FUNCTION_PAIR:
      {
        FunctionPair functionPair = (FunctionPair)theEObject;
        Object result = caseFunctionPair(functionPair);
        if (result == null) result = caseTypeConverter(functionPair);
        if (result == null) result = caseMappingHelper(functionPair);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case MappingPackage.FUNCTION_NAME_PAIR:
      {
        FunctionNamePair functionNamePair = (FunctionNamePair)theEObject;
        Object result = caseFunctionNamePair(functionNamePair);
        if (result == null) result = caseTypeConverter(functionNamePair);
        if (result == null) result = caseMappingHelper(functionNamePair);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case MappingPackage.MAPPING_STRATEGY:
      {
        MappingStrategy mappingStrategy = (MappingStrategy)theEObject;
        Object result = caseMappingStrategy(mappingStrategy);
        if (result == null) result = caseMappingHelper(mappingStrategy);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case MappingPackage.MAPPING_ROOT:
      {
        MappingRoot mappingRoot = (MappingRoot)theEObject;
        Object result = caseMappingRoot(mappingRoot);
        if (result == null) result = caseMapping(mappingRoot);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case MappingPackage.COMPLEX_TYPE_CONVERTER:
      {
        ComplexTypeConverter complexTypeConverter = (ComplexTypeConverter)theEObject;
        Object result = caseComplexTypeConverter(complexTypeConverter);
        if (result == null) result = caseTypeConverter(complexTypeConverter);
        if (result == null) result = caseMappingHelper(complexTypeConverter);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      default: return defaultCase(theEObject);
    }
  }

  /**
   * Returns the result of interpretting the object as an instance of '<em>Helper</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpretting the object as an instance of '<em>Helper</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public Object caseMappingHelper(MappingHelper object)
  {
    return null;
  }

  /**
   * Returns the result of interpretting the object as an instance of '<em>Mapping</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpretting the object as an instance of '<em>Mapping</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public Object caseMapping(Mapping object)
  {
    return null;
  }

  /**
   * Returns the result of interpretting the object as an instance of '<em>Type Converter</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpretting the object as an instance of '<em>Type Converter</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public Object caseTypeConverter(TypeConverter object)
  {
    return null;
  }

  /**
   * Returns the result of interpretting the object as an instance of '<em>Function Pair</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpretting the object as an instance of '<em>Function Pair</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public Object caseFunctionPair(FunctionPair object)
  {
    return null;
  }

  /**
   * Returns the result of interpretting the object as an instance of '<em>Function Name Pair</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpretting the object as an instance of '<em>Function Name Pair</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public Object caseFunctionNamePair(FunctionNamePair object)
  {
    return null;
  }

  /**
   * Returns the result of interpretting the object as an instance of '<em>Strategy</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpretting the object as an instance of '<em>Strategy</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public Object caseMappingStrategy(MappingStrategy object)
  {
    return null;
  }

  /**
   * Returns the result of interpretting the object as an instance of '<em>Root</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpretting the object as an instance of '<em>Root</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public Object caseMappingRoot(MappingRoot object)
  {
    return null;
  }

  /**
   * Returns the result of interpretting the object as an instance of '<em>Complex Type Converter</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpretting the object as an instance of '<em>Complex Type Converter</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public Object caseComplexTypeConverter(ComplexTypeConverter object)
  {
    return null;
  }

  /**
   * Returns the result of interpretting the object as an instance of '<em>EObject</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch, but this is the last case anyway.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpretting the object as an instance of '<em>EObject</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject)
   * @generated
   */
  public Object defaultCase(EObject object)
  {
    return null;
  }

} //MappingSwitch
