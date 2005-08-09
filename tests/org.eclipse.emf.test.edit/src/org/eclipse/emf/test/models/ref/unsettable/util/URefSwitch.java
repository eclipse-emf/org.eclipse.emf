/**
 * <copyright>
 * </copyright>
 *
 * $Id: URefSwitch.java,v 1.1 2005/08/09 04:43:09 davidms Exp $
 */
package org.eclipse.emf.test.models.ref.unsettable.util;

import java.util.List;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;

import org.eclipse.emf.test.models.ref.unsettable.*;

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
 * @see org.eclipse.emf.test.models.ref.unsettable.URefPackage
 * @generated
 */
public class URefSwitch
{
  /**
   * The cached model package
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected static URefPackage modelPackage;

  /**
   * Creates an instance of the switch.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public URefSwitch()
  {
    if (modelPackage == null)
    {
      modelPackage = URefPackage.eINSTANCE;
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
      case URefPackage.C1U:
      {
        C1U c1U = (C1U)theEObject;
        Object result = caseC1U(c1U);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case URefPackage.C2U:
      {
        C2U c2U = (C2U)theEObject;
        Object result = caseC2U(c2U);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case URefPackage.AU:
      {
        AU au = (AU)theEObject;
        Object result = caseAU(au);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case URefPackage.BU:
      {
        BU bu = (BU)theEObject;
        Object result = caseBU(bu);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case URefPackage.CU:
      {
        CU cu = (CU)theEObject;
        Object result = caseCU(cu);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case URefPackage.DU:
      {
        DU du = (DU)theEObject;
        Object result = caseDU(du);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case URefPackage.C4U:
      {
        C4U c4U = (C4U)theEObject;
        Object result = caseC4U(c4U);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case URefPackage.C3U:
      {
        C3U c3U = (C3U)theEObject;
        Object result = caseC3U(c3U);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      case URefPackage.EU:
      {
        EU eu = (EU)theEObject;
        Object result = caseEU(eu);
        if (result == null) result = defaultCase(theEObject);
        return result;
      }
      default: return defaultCase(theEObject);
    }
  }

  /**
   * Returns the result of interpretting the object as an instance of '<em>C1U</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpretting the object as an instance of '<em>C1U</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public Object caseC1U(C1U object)
  {
    return null;
  }

  /**
   * Returns the result of interpretting the object as an instance of '<em>C2U</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpretting the object as an instance of '<em>C2U</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public Object caseC2U(C2U object)
  {
    return null;
  }

  /**
   * Returns the result of interpretting the object as an instance of '<em>AU</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpretting the object as an instance of '<em>AU</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public Object caseAU(AU object)
  {
    return null;
  }

  /**
   * Returns the result of interpretting the object as an instance of '<em>BU</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpretting the object as an instance of '<em>BU</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public Object caseBU(BU object)
  {
    return null;
  }

  /**
   * Returns the result of interpretting the object as an instance of '<em>CU</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpretting the object as an instance of '<em>CU</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public Object caseCU(CU object)
  {
    return null;
  }

  /**
   * Returns the result of interpretting the object as an instance of '<em>DU</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpretting the object as an instance of '<em>DU</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public Object caseDU(DU object)
  {
    return null;
  }

  /**
   * Returns the result of interpretting the object as an instance of '<em>C4U</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpretting the object as an instance of '<em>C4U</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public Object caseC4U(C4U object)
  {
    return null;
  }

  /**
   * Returns the result of interpretting the object as an instance of '<em>C3U</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpretting the object as an instance of '<em>C3U</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public Object caseC3U(C3U object)
  {
    return null;
  }

  /**
   * Returns the result of interpretting the object as an instance of '<em>EU</em>'.
   * <!-- begin-user-doc -->
   * This implementation returns null;
   * returning a non-null result will terminate the switch.
   * <!-- end-user-doc -->
   * @param object the target of the switch.
   * @return the result of interpretting the object as an instance of '<em>EU</em>'.
   * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)
   * @generated
   */
  public Object caseEU(EU object)
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

} //URefSwitch
