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
 * $Id: EcoreSwitch.java,v 1.7 2005/04/08 12:00:16 emerks Exp $
 */
package org.eclipse.emf.ecore.util;


import java.util.List;
import java.util.Map;

import org.eclipse.emf.ecore.*;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;


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
 * @see org.eclipse.emf.ecore.EcorePackage
 * @generated
 */
public class EcoreSwitch
{
  /**
   * The cached model package
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected static EcorePackage modelPackage;

  /**
   * Creates an instance of the switch.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EcoreSwitch()
  {
    if (modelPackage == null)
    {
      modelPackage = EcorePackage.eINSTANCE;
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
      case EcorePackage.EATTRIBUTE:
      {
        EAttribute eAttribute = (EAttribute)theEObject;
        Object result = caseEAttribute(eAttribute);
        if (result == null) result = caseEStructuralFeature(eAttribute);
        if (result == null) result = caseETypedElement(eAttribute);
        if (result == null) result = caseENamedElement(eAttribute);
        if (result == null) result = caseEModelElement(eAttribute);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case EcorePackage.EANNOTATION:
      {
        EAnnotation eAnnotation = (EAnnotation)theEObject;
        Object result = caseEAnnotation(eAnnotation);
        if (result == null) result = caseEModelElement(eAnnotation);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case EcorePackage.ECLASS:
      {
        EClass eClass = (EClass)theEObject;
        Object result = caseEClass(eClass);
        if (result == null) result = caseEClassifier(eClass);
        if (result == null) result = caseENamedElement(eClass);
        if (result == null) result = caseEModelElement(eClass);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case EcorePackage.ECLASSIFIER:
      {
        EClassifier eClassifier = (EClassifier)theEObject;
        Object result = caseEClassifier(eClassifier);
        if (result == null) result = caseENamedElement(eClassifier);
        if (result == null) result = caseEModelElement(eClassifier);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case EcorePackage.EDATA_TYPE:
      {
        EDataType eDataType = (EDataType)theEObject;
        Object result = caseEDataType(eDataType);
        if (result == null) result = caseEClassifier(eDataType);
        if (result == null) result = caseENamedElement(eDataType);
        if (result == null) result = caseEModelElement(eDataType);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case EcorePackage.EENUM:
      {
        EEnum eEnum = (EEnum)theEObject;
        Object result = caseEEnum(eEnum);
        if (result == null) result = caseEDataType(eEnum);
        if (result == null) result = caseEClassifier(eEnum);
        if (result == null) result = caseENamedElement(eEnum);
        if (result == null) result = caseEModelElement(eEnum);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case EcorePackage.EENUM_LITERAL:
      {
        EEnumLiteral eEnumLiteral = (EEnumLiteral)theEObject;
        Object result = caseEEnumLiteral(eEnumLiteral);
        if (result == null) result = caseENamedElement(eEnumLiteral);
        if (result == null) result = caseEModelElement(eEnumLiteral);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case EcorePackage.EFACTORY:
      {
        EFactory eFactory = (EFactory)theEObject;
        Object result = caseEFactory(eFactory);
        if (result == null) result = caseEModelElement(eFactory);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case EcorePackage.EMODEL_ELEMENT:
      {
        EModelElement eModelElement = (EModelElement)theEObject;
        Object result = caseEModelElement(eModelElement);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case EcorePackage.ENAMED_ELEMENT:
      {
        ENamedElement eNamedElement = (ENamedElement)theEObject;
        Object result = caseENamedElement(eNamedElement);
        if (result == null) result = caseEModelElement(eNamedElement);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case EcorePackage.EOPERATION:
      {
        EOperation eOperation = (EOperation)theEObject;
        Object result = caseEOperation(eOperation);
        if (result == null) result = caseETypedElement(eOperation);
        if (result == null) result = caseENamedElement(eOperation);
        if (result == null) result = caseEModelElement(eOperation);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case EcorePackage.EPACKAGE:
      {
        EPackage ePackage = (EPackage)theEObject;
        Object result = caseEPackage(ePackage);
        if (result == null) result = caseENamedElement(ePackage);
        if (result == null) result = caseEModelElement(ePackage);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case EcorePackage.EPARAMETER:
      {
        EParameter eParameter = (EParameter)theEObject;
        Object result = caseEParameter(eParameter);
        if (result == null) result = caseETypedElement(eParameter);
        if (result == null) result = caseENamedElement(eParameter);
        if (result == null) result = caseEModelElement(eParameter);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case EcorePackage.EREFERENCE:
      {
        EReference eReference = (EReference)theEObject;
        Object result = caseEReference(eReference);
        if (result == null) result = caseEStructuralFeature(eReference);
        if (result == null) result = caseETypedElement(eReference);
        if (result == null) result = caseENamedElement(eReference);
        if (result == null) result = caseEModelElement(eReference);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case EcorePackage.ESTRUCTURAL_FEATURE:
      {
        EStructuralFeature eStructuralFeature = (EStructuralFeature)theEObject;
        Object result = caseEStructuralFeature(eStructuralFeature);
        if (result == null) result = caseETypedElement(eStructuralFeature);
        if (result == null) result = caseENamedElement(eStructuralFeature);
        if (result == null) result = caseEModelElement(eStructuralFeature);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case EcorePackage.ETYPED_ELEMENT:
      {
        ETypedElement eTypedElement = (ETypedElement)theEObject;
        Object result = caseETypedElement(eTypedElement);
        if (result == null) result = caseENamedElement(eTypedElement);
        if (result == null) result = caseEModelElement(eTypedElement);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case EcorePackage.ESTRING_TO_STRING_MAP_ENTRY:
      {
        Map.Entry eStringToStringMapEntry = (Map.Entry)theEObject;
        Object result = caseEStringToStringMapEntry(eStringToStringMapEntry);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      default: return defaultCase(theEObject);
    }
  }

  /**
   * Returns the result of interpretting the object as an instance of '<em>EModel Element</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null; 
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpretting the object as an instance of '<em>EModel Element</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public Object caseEModelElement(EModelElement object)
  {
    return null;
  }

  /**
   * Returns the result of interpretting the object as an instance of '<em>EStructural Feature</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null; 
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpretting the object as an instance of '<em>EStructural Feature</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public Object caseEStructuralFeature(EStructuralFeature object)
  {
    return null;
  }

  /**
   * Returns the result of interpretting the object as an instance of '<em>EAnnotation</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpretting the object as an instance of '<em>EAnnotation</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public Object caseEAnnotation(EAnnotation object)
  {
    return null;
  }

  /**
   * Returns the result of interpretting the object as an instance of '<em>EAttribute</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null; 
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpretting the object as an instance of '<em>EAttribute</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public Object caseEAttribute(EAttribute object)
  {
    return null;
  }

  /**
   * Returns the result of interpretting the object as an instance of '<em>EClass</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null; 
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpretting the object as an instance of '<em>EClass</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public Object caseEClass(EClass object)
  {
    return null;
  }

  /**
   * Returns the result of interpretting the object as an instance of '<em>EData Type</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null; 
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpretting the object as an instance of '<em>EData Type</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public Object caseEDataType(EDataType object)
  {
    return null;
  }

  /**
   * Returns the result of interpretting the object as an instance of '<em>EClassifier</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null; 
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpretting the object as an instance of '<em>EClassifier</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public Object caseEClassifier(EClassifier object)
  {
    return null;
  }

  /**
   * Returns the result of interpretting the object as an instance of '<em>ENamed Element</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null; 
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpretting the object as an instance of '<em>ENamed Element</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public Object caseENamedElement(ENamedElement object)
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

  /**
   * Returns the result of interpretting the object as an instance of '<em>ETyped Element</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null; 
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpretting the object as an instance of '<em>ETyped Element</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public Object caseETypedElement(ETypedElement object)
  {
    return null;
  }

  /**
   * Returns the result of interpretting the object as an instance of '<em>EParameter</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null; 
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpretting the object as an instance of '<em>EParameter</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public Object caseEParameter(EParameter object)
  {
    return null;
  }

  /**
   * Returns the result of interpretting the object as an instance of '<em>EOperation</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null; 
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpretting the object as an instance of '<em>EOperation</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public Object caseEOperation(EOperation object)
  {
    return null;
  }

  /**
   * Returns the result of interpretting the object as an instance of '<em>EPackage</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null; 
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpretting the object as an instance of '<em>EPackage</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public Object caseEPackage(EPackage object)
  {
    return null;
  }

  /**
   * Returns the result of interpretting the object as an instance of '<em>EFactory</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null; 
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpretting the object as an instance of '<em>EFactory</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public Object caseEFactory(EFactory object)
  {
    return null;
  }

  /**
   * Returns the result of interpretting the object as an instance of '<em>EEnum Literal</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null; 
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpretting the object as an instance of '<em>EEnum Literal</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public Object caseEEnumLiteral(EEnumLiteral object)
  {
    return null;
  }

  /**
   * Returns the result of interpretting the object as an instance of '<em>EEnum</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null; 
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpretting the object as an instance of '<em>EEnum</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public Object caseEEnum(EEnum object)
  {
    return null;
  }

  /**
   * Returns the result of interpretting the object as an instance of '<em>EReference</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null; 
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpretting the object as an instance of '<em>EReference</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public Object caseEReference(EReference object)
  {
    return null;
  }

  /**
   * Returns the result of interpretting the object as an instance of '<em>EString To String Map Entry</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpretting the object as an instance of '<em>EString To String Map Entry</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public Object caseEStringToStringMapEntry(Map.Entry object)
  {
    return null;
  }

} //EcoreSwitch
