/**
 * Copyright (c) 2011-2012 Eclipse contributors and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package org.eclipse.emf.ecore.xcore.util;


import java.util.Map;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;

import org.eclipse.emf.ecore.util.Switch;

import org.eclipse.emf.ecore.xcore.*;


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
 * @see org.eclipse.emf.ecore.xcore.XcorePackage
 * @generated
 */
public class XcoreSwitch<T> extends Switch<T>
{
  /**
   * The cached model package
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected static XcorePackage modelPackage;

  /**
   * Creates an instance of the switch.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public XcoreSwitch()
  {
    if (modelPackage == null)
    {
      modelPackage = XcorePackage.eINSTANCE;
    }
  }

  /**
   * Checks whether this is a switch for the given package.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @parameter ePackage the package in question.
   * @return whether this is a switch for the given package.
   * @generated
   */
  @Override
  protected boolean isSwitchFor(EPackage ePackage)
  {
    return ePackage == modelPackage;
  }

  /**
   * Calls <code>caseXXX</code> for each class of the model until one returns a non null result; it yields that result.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the first non-null result returned by a <code>caseXXX</code> call.
   * @generated
   */
  @Override
  protected T doSwitch(int classifierID, EObject theEObject)
  {
    switch (classifierID)
    {
      case XcorePackage.XANNOTATION:
      {
        XAnnotation xAnnotation = (XAnnotation)theEObject;
        T result = caseXAnnotation(xAnnotation);
        if (result == null) result = caseXModelElement(xAnnotation);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case XcorePackage.XANNOTATION_DIRECTIVE:
      {
        XAnnotationDirective xAnnotationDirective = (XAnnotationDirective)theEObject;
        T result = caseXAnnotationDirective(xAnnotationDirective);
        if (result == null) result = caseXNamedElement(xAnnotationDirective);
        if (result == null) result = caseXModelElement(xAnnotationDirective);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case XcorePackage.XATTRIBUTE:
      {
        XAttribute xAttribute = (XAttribute)theEObject;
        T result = caseXAttribute(xAttribute);
        if (result == null) result = caseXStructuralFeature(xAttribute);
        if (result == null) result = caseXMember(xAttribute);
        if (result == null) result = caseXTypedElement(xAttribute);
        if (result == null) result = caseXNamedElement(xAttribute);
        if (result == null) result = caseXModelElement(xAttribute);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case XcorePackage.XCLASS:
      {
        XClass xClass = (XClass)theEObject;
        T result = caseXClass(xClass);
        if (result == null) result = caseXClassifier(xClass);
        if (result == null) result = caseXNamedElement(xClass);
        if (result == null) result = caseXModelElement(xClass);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case XcorePackage.XCLASSIFIER:
      {
        XClassifier xClassifier = (XClassifier)theEObject;
        T result = caseXClassifier(xClassifier);
        if (result == null) result = caseXNamedElement(xClassifier);
        if (result == null) result = caseXModelElement(xClassifier);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case XcorePackage.XDATA_TYPE:
      {
        XDataType xDataType = (XDataType)theEObject;
        T result = caseXDataType(xDataType);
        if (result == null) result = caseXClassifier(xDataType);
        if (result == null) result = caseXNamedElement(xDataType);
        if (result == null) result = caseXModelElement(xDataType);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case XcorePackage.XENUM:
      {
        XEnum xEnum = (XEnum)theEObject;
        T result = caseXEnum(xEnum);
        if (result == null) result = caseXDataType(xEnum);
        if (result == null) result = caseXClassifier(xEnum);
        if (result == null) result = caseXNamedElement(xEnum);
        if (result == null) result = caseXModelElement(xEnum);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case XcorePackage.XENUM_LITERAL:
      {
        XEnumLiteral xEnumLiteral = (XEnumLiteral)theEObject;
        T result = caseXEnumLiteral(xEnumLiteral);
        if (result == null) result = caseXNamedElement(xEnumLiteral);
        if (result == null) result = caseXModelElement(xEnumLiteral);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case XcorePackage.XGENERIC_TYPE:
      {
        XGenericType xGenericType = (XGenericType)theEObject;
        T result = caseXGenericType(xGenericType);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case XcorePackage.XIMPORT_DIRECTIVE:
      {
        XImportDirective xImportDirective = (XImportDirective)theEObject;
        T result = caseXImportDirective(xImportDirective);
        if (result == null) result = caseXModelElement(xImportDirective);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case XcorePackage.XMEMBER:
      {
        XMember xMember = (XMember)theEObject;
        T result = caseXMember(xMember);
        if (result == null) result = caseXTypedElement(xMember);
        if (result == null) result = caseXNamedElement(xMember);
        if (result == null) result = caseXModelElement(xMember);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case XcorePackage.XMODEL_ELEMENT:
      {
        XModelElement xModelElement = (XModelElement)theEObject;
        T result = caseXModelElement(xModelElement);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case XcorePackage.XNAMED_ELEMENT:
      {
        XNamedElement xNamedElement = (XNamedElement)theEObject;
        T result = caseXNamedElement(xNamedElement);
        if (result == null) result = caseXModelElement(xNamedElement);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case XcorePackage.XOPERATION:
      {
        XOperation xOperation = (XOperation)theEObject;
        T result = caseXOperation(xOperation);
        if (result == null) result = caseXMember(xOperation);
        if (result == null) result = caseXTypedElement(xOperation);
        if (result == null) result = caseXNamedElement(xOperation);
        if (result == null) result = caseXModelElement(xOperation);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case XcorePackage.XPACKAGE:
      {
        XPackage xPackage = (XPackage)theEObject;
        T result = caseXPackage(xPackage);
        if (result == null) result = caseXNamedElement(xPackage);
        if (result == null) result = caseXModelElement(xPackage);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case XcorePackage.XPARAMETER:
      {
        XParameter xParameter = (XParameter)theEObject;
        T result = caseXParameter(xParameter);
        if (result == null) result = caseXTypedElement(xParameter);
        if (result == null) result = caseXNamedElement(xParameter);
        if (result == null) result = caseXModelElement(xParameter);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case XcorePackage.XREFERENCE:
      {
        XReference xReference = (XReference)theEObject;
        T result = caseXReference(xReference);
        if (result == null) result = caseXStructuralFeature(xReference);
        if (result == null) result = caseXMember(xReference);
        if (result == null) result = caseXTypedElement(xReference);
        if (result == null) result = caseXNamedElement(xReference);
        if (result == null) result = caseXModelElement(xReference);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case XcorePackage.XSTRING_TO_STRING_MAP_ENTRY:
      {
        @SuppressWarnings("unchecked") Map.Entry<String, String> xStringToStringMapEntry = (Map.Entry<String, String>)theEObject;
        T result = caseXStringToStringMapEntry(xStringToStringMapEntry);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case XcorePackage.XSTRUCTURAL_FEATURE:
      {
        XStructuralFeature xStructuralFeature = (XStructuralFeature)theEObject;
        T result = caseXStructuralFeature(xStructuralFeature);
        if (result == null) result = caseXMember(xStructuralFeature);
        if (result == null) result = caseXTypedElement(xStructuralFeature);
        if (result == null) result = caseXNamedElement(xStructuralFeature);
        if (result == null) result = caseXModelElement(xStructuralFeature);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case XcorePackage.XTYPED_ELEMENT:
      {
        XTypedElement xTypedElement = (XTypedElement)theEObject;
        T result = caseXTypedElement(xTypedElement);
        if (result == null) result = caseXNamedElement(xTypedElement);
        if (result == null) result = caseXModelElement(xTypedElement);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case XcorePackage.XTYPE_PARAMETER:
      {
        XTypeParameter xTypeParameter = (XTypeParameter)theEObject;
        T result = caseXTypeParameter(xTypeParameter);
        if (result == null) result = caseXNamedElement(xTypeParameter);
        if (result == null) result = caseXModelElement(xTypeParameter);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      default: return defaultCase(theEObject);
    }
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>XAnnotation</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>XAnnotation</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseXAnnotation(XAnnotation object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>XAnnotation Directive</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>XAnnotation Directive</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseXAnnotationDirective(XAnnotationDirective object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>XAttribute</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>XAttribute</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseXAttribute(XAttribute object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>XClass</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>XClass</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseXClass(XClass object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>XClassifier</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>XClassifier</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseXClassifier(XClassifier object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>XData Type</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>XData Type</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseXDataType(XDataType object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>XEnum</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>XEnum</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseXEnum(XEnum object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>XEnum Literal</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>XEnum Literal</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseXEnumLiteral(XEnumLiteral object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>XModel Element</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>XModel Element</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseXModelElement(XModelElement object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>XNamed Element</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>XNamed Element</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseXNamedElement(XNamedElement object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>XOperation</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>XOperation</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseXOperation(XOperation object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>XPackage</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>XPackage</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseXPackage(XPackage object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>XParameter</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>XParameter</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseXParameter(XParameter object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>XReference</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>XReference</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseXReference(XReference object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>XStructural Feature</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>XStructural Feature</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseXStructuralFeature(XStructuralFeature object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>XTyped Element</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>XTyped Element</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseXTypedElement(XTypedElement object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>XString To String Map Entry</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>XString To String Map Entry</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseXStringToStringMapEntry(Map.Entry<String, String> object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>XGeneric Type</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>XGeneric Type</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseXGenericType(XGenericType object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>XImport Directive</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>XImport Directive</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseXImportDirective(XImportDirective object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>XMember</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>XMember</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseXMember(XMember object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>XType Parameter</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>XType Parameter</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public T caseXTypeParameter(XTypeParameter object)
  {
    return null;
  }

  /**
   * Returns the result of interpreting the object as an instance of '<em>EObject</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch, but this is the last case anyway.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpreting the object as an instance of '<em>EObject</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject)
   * @generated
   */
  @Override
  public T defaultCase(EObject object)
  {
    return null;
  }

} //XcoreSwitch
