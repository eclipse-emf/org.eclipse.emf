/**
 * <copyright>
 *
 * Copyright (c) 2002-2004 IBM Corporation and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Common Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/cpl-v10.html
 * 
 * Contributors: 
 *   IBM - Initial API and implementation
 *
 * </copyright>
 *
 * $Id: GenModelSwitch.java,v 1.1 2004/03/06 17:31:31 marcelop Exp $
 */
package org.eclipse.emf.codegen.ecore.genmodel.util;


import org.eclipse.emf.codegen.ecore.genmodel.GenBase;
import org.eclipse.emf.codegen.ecore.genmodel.GenClass;
import org.eclipse.emf.codegen.ecore.genmodel.GenClassifier;
import org.eclipse.emf.codegen.ecore.genmodel.GenDataType;
import org.eclipse.emf.codegen.ecore.genmodel.GenEnum;
import org.eclipse.emf.codegen.ecore.genmodel.GenEnumLiteral;
import org.eclipse.emf.codegen.ecore.genmodel.GenFeature;
import org.eclipse.emf.codegen.ecore.genmodel.GenModel;
import org.eclipse.emf.codegen.ecore.genmodel.GenModelPackage;
import org.eclipse.emf.codegen.ecore.genmodel.GenOperation;
import org.eclipse.emf.codegen.ecore.genmodel.GenPackage;
import org.eclipse.emf.codegen.ecore.genmodel.GenParameter;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;


/**
 * <!-- begin-user-doc -->
 * The <b>Switch</b> for the model's inheritance hierarchy.
 * It supports the call {@link #doSwitch doSwitch(object)} 
 * to invoke the <code>caseXXX</code> method for each class of the model,
 * starting with the actual class of the object 
 * and proceeding up the inheritance hierarchy
 * until a non-null result is returned, 
 * which is the result of the switch.
 * <!-- end-user-doc -->
 * @see org.eclipse.emf.codegen.ecore.genmodel.GenModelPackage
 * @generated
 */
public class GenModelSwitch 
{
  /**
   * The cached model package
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected static GenModelPackage modelPackage;

  /**
   * Creates an instance of the switch.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public GenModelSwitch()
  {
    if (modelPackage == null)
    {
      modelPackage = GenModelPackage.eINSTANCE;
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
    EClass theEClass = theEObject.eClass();
    if (theEClass.eContainer() == modelPackage)
    {
      switch (theEClass.getClassifierID())
      {
        case GenModelPackage.GEN_MODEL:
        {
          GenModel genModel = (GenModel)theEObject;
          Object result = caseGenModel(genModel);
          if (result == null) result = caseGenBase(genModel);
          if (result == null) result = defaultCase(theEObject);
          return result;
        }
        case GenModelPackage.GEN_PACKAGE:
        {
          GenPackage genPackage = (GenPackage)theEObject;
          Object result = caseGenPackage(genPackage);
          if (result == null) result = caseGenBase(genPackage);
          if (result == null) result = defaultCase(theEObject);
          return result;
        }
        case GenModelPackage.GEN_CLASS:
        {
          GenClass genClass = (GenClass)theEObject;
          Object result = caseGenClass(genClass);
          if (result == null) result = caseGenClassifier(genClass);
          if (result == null) result = caseGenBase(genClass);
          if (result == null) result = defaultCase(theEObject);
          return result;
        }
        case GenModelPackage.GEN_FEATURE:
        {
          GenFeature genFeature = (GenFeature)theEObject;
          Object result = caseGenFeature(genFeature);
          if (result == null) result = caseGenBase(genFeature);
          if (result == null) result = defaultCase(theEObject);
          return result;
        }
        case GenModelPackage.GEN_ENUM:
        {
          GenEnum genEnum = (GenEnum)theEObject;
          Object result = caseGenEnum(genEnum);
          if (result == null) result = caseGenDataType(genEnum);
          if (result == null) result = caseGenClassifier(genEnum);
          if (result == null) result = caseGenBase(genEnum);
          if (result == null) result = defaultCase(theEObject);
          return result;
        }
        case GenModelPackage.GEN_ENUM_LITERAL:
        {
          GenEnumLiteral genEnumLiteral = (GenEnumLiteral)theEObject;
          Object result = caseGenEnumLiteral(genEnumLiteral);
          if (result == null) result = caseGenBase(genEnumLiteral);
          if (result == null) result = defaultCase(theEObject);
          return result;
        }
        case GenModelPackage.GEN_DATA_TYPE:
        {
          GenDataType genDataType = (GenDataType)theEObject;
          Object result = caseGenDataType(genDataType);
          if (result == null) result = caseGenClassifier(genDataType);
          if (result == null) result = caseGenBase(genDataType);
          if (result == null) result = defaultCase(theEObject);
          return result;
        }
        case GenModelPackage.GEN_OPERATION:
        {
          GenOperation genOperation = (GenOperation)theEObject;
          Object result = caseGenOperation(genOperation);
          if (result == null) result = caseGenBase(genOperation);
          if (result == null) result = defaultCase(theEObject);
          return result;
        }
        case GenModelPackage.GEN_PARAMETER:
        {
          GenParameter genParameter = (GenParameter)theEObject;
          Object result = caseGenParameter(genParameter);
          if (result == null) result = caseGenBase(genParameter);
          if (result == null) result = defaultCase(theEObject);
          return result;
        }
        default: return defaultCase(theEObject);
      }
    }
    return defaultCase(theEObject);
  }

  /**
   * Returns the result of interpretting the object as an instance of '<em>GenModel</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null; 
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpretting the object as an instance of '<em>GenModel</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public Object caseGenModel(GenModel object)
  {
    return null;
  }

  /**
   * Returns the result of interpretting the object as an instance of '<em>Gen Package</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null; 
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpretting the object as an instance of '<em>Gen Package</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public Object caseGenPackage(GenPackage object)
  {
    return null;
  }

  /**
   * Returns the result of interpretting the object as an instance of '<em>Gen Class</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null; 
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpretting the object as an instance of '<em>Gen Class</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public Object caseGenClass(GenClass object)
  {
    return null;
  }

  /**
   * Returns the result of interpretting the object as an instance of '<em>Gen Feature</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null; 
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpretting the object as an instance of '<em>Gen Feature</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public Object caseGenFeature(GenFeature object)
  {
    return null;
  }

  /**
   * Returns the result of interpretting the object as an instance of '<em>Gen Base</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null; 
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpretting the object as an instance of '<em>Gen Base</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public Object caseGenBase(GenBase object)
  {
    return null;
  }

  /**
   * Returns the result of interpretting the object as an instance of '<em>Gen Enum</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null; 
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpretting the object as an instance of '<em>Gen Enum</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public Object caseGenEnum(GenEnum object)
  {
    return null;
  }

  /**
   * Returns the result of interpretting the object as an instance of '<em>Gen Enum Literal</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null; 
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpretting the object as an instance of '<em>Gen Enum Literal</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public Object caseGenEnumLiteral(GenEnumLiteral object)
  {
    return null;
  }

  /**
   * Returns the result of interpretting the object as an instance of '<em>Gen Classifier</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null; 
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpretting the object as an instance of '<em>Gen Classifier</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public Object caseGenClassifier(GenClassifier object)
  {
    return null;
  }

  /**
   * Returns the result of interpretting the object as an instance of '<em>Gen Data Type</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null; 
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpretting the object as an instance of '<em>Gen Data Type</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public Object caseGenDataType(GenDataType object)
  {
    return null;
  }

  /**
   * Returns the result of interpretting the object as an instance of '<em>Gen Operation</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null; 
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpretting the object as an instance of '<em>Gen Operation</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public Object caseGenOperation(GenOperation object)
  {
    return null;
  }

  /**
   * Returns the result of interpretting the object as an instance of '<em>Gen Parameter</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null; 
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpretting the object as an instance of '<em>Gen Parameter</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public Object caseGenParameter(GenParameter object)
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

} //GenModelSwitch
