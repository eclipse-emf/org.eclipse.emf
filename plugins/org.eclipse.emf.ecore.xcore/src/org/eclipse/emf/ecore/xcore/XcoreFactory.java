/**
 *
 * $Id$
 */
package org.eclipse.emf.ecore.xcore;

import org.eclipse.emf.ecore.EFactory;

/**
 * <!-- begin-user-doc -->
 * The <b>Factory</b> for the model.
 * It provides a create method for each non-abstract class of the model.
 * <!-- end-user-doc -->
 * @see org.eclipse.emf.ecore.xcore.XcorePackage
 * @generated
 */
public interface XcoreFactory extends EFactory
{
  /**
   * The singleton instance of the factory.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  XcoreFactory eINSTANCE = org.eclipse.emf.ecore.xcore.impl.XcoreFactoryImpl.init();

  /**
   * Returns a new object of class '<em>XAnnotation</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>XAnnotation</em>'.
   * @generated
   */
  XAnnotation createXAnnotation();

  /**
   * Returns a new object of class '<em>XAnnotation Directive</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>XAnnotation Directive</em>'.
   * @generated
   */
  XAnnotationDirective createXAnnotationDirective();

  /**
   * Returns a new object of class '<em>XAttribute</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>XAttribute</em>'.
   * @generated
   */
  XAttribute createXAttribute();

  /**
   * Returns a new object of class '<em>XClass</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>XClass</em>'.
   * @generated
   */
  XClass createXClass();

  /**
   * Returns a new object of class '<em>XData Type</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>XData Type</em>'.
   * @generated
   */
  XDataType createXDataType();

  /**
   * Returns a new object of class '<em>XEnum</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>XEnum</em>'.
   * @generated
   */
  XEnum createXEnum();

  /**
   * Returns a new object of class '<em>XEnum Literal</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>XEnum Literal</em>'.
   * @generated
   */
  XEnumLiteral createXEnumLiteral();

  /**
   * Returns a new object of class '<em>XOperation</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>XOperation</em>'.
   * @generated
   */
  XOperation createXOperation();

  /**
   * Returns a new object of class '<em>XPackage</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>XPackage</em>'.
   * @generated
   */
  XPackage createXPackage();

  /**
   * Returns a new object of class '<em>XParameter</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>XParameter</em>'.
   * @generated
   */
  XParameter createXParameter();

  /**
   * Returns a new object of class '<em>XReference</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>XReference</em>'.
   * @generated
   */
  XReference createXReference();

  /**
   * Returns a new object of class '<em>XGeneric Type</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>XGeneric Type</em>'.
   * @generated
   */
  XGenericType createXGenericType();

  /**
   * Returns a new object of class '<em>XImport Directive</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>XImport Directive</em>'.
   * @generated
   */
  XImportDirective createXImportDirective();

  /**
   * Returns a new object of class '<em>XType Parameter</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>XType Parameter</em>'.
   * @generated
   */
  XTypeParameter createXTypeParameter();

  /**
   * Returns the package supported by this factory.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the package supported by this factory.
   * @generated
   */
  XcorePackage getXcorePackage();

} //XcoreFactory
