/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.eclipse.emf.ecore.xcore;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>XPackage</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.emf.ecore.xcore.XPackage#getNsURI <em>Ns URI</em>}</li>
 *   <li>{@link org.eclipse.emf.ecore.xcore.XPackage#getNsPrefix <em>Ns Prefix</em>}</li>
 *   <li>{@link org.eclipse.emf.ecore.xcore.XPackage#getImportDirectives <em>Import Directives</em>}</li>
 *   <li>{@link org.eclipse.emf.ecore.xcore.XPackage#getAnnotationDirectives <em>Annotation Directives</em>}</li>
 *   <li>{@link org.eclipse.emf.ecore.xcore.XPackage#getClassifiers <em>Classifiers</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.emf.ecore.xcore.XcorePackage#getXPackage()
 * @model
 * @generated
 */
public interface XPackage extends XNamedElement
{
  /**
   * Returns the value of the '<em><b>Ns URI</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Ns URI</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Ns URI</em>' attribute.
   * @see #setNsURI(String)
   * @see org.eclipse.emf.ecore.xcore.XcorePackage#getXPackage_NsURI()
   * @model
   * @generated
   */
  String getNsURI();

  /**
   * Sets the value of the '{@link org.eclipse.emf.ecore.xcore.XPackage#getNsURI <em>Ns URI</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Ns URI</em>' attribute.
   * @see #getNsURI()
   * @generated
   */
  void setNsURI(String value);

  /**
   * Returns the value of the '<em><b>Ns Prefix</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Ns Prefix</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Ns Prefix</em>' attribute.
   * @see #setNsPrefix(String)
   * @see org.eclipse.emf.ecore.xcore.XcorePackage#getXPackage_NsPrefix()
   * @model
   * @generated
   */
  String getNsPrefix();

  /**
   * Sets the value of the '{@link org.eclipse.emf.ecore.xcore.XPackage#getNsPrefix <em>Ns Prefix</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Ns Prefix</em>' attribute.
   * @see #getNsPrefix()
   * @generated
   */
  void setNsPrefix(String value);

  /**
   * Returns the value of the '<em><b>Import Directives</b></em>' containment reference list.
   * The list contents are of type {@link org.eclipse.emf.ecore.xcore.XImportDirective}.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Import Directives</em>' containment reference list isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Import Directives</em>' containment reference list.
   * @see org.eclipse.emf.ecore.xcore.XcorePackage#getXPackage_ImportDirectives()
   * @model containment="true"
   * @generated
   */
  EList<XImportDirective> getImportDirectives();

  /**
   * Returns the value of the '<em><b>Annotation Directives</b></em>' containment reference list.
   * The list contents are of type {@link org.eclipse.emf.ecore.xcore.XAnnotationDirective}.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Annotation Directives</em>' containment reference list isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Annotation Directives</em>' containment reference list.
   * @see org.eclipse.emf.ecore.xcore.XcorePackage#getXPackage_AnnotationDirectives()
   * @model containment="true"
   * @generated
   */
  EList<XAnnotationDirective> getAnnotationDirectives();

  /**
   * Returns the value of the '<em><b>Classifiers</b></em>' containment reference list.
   * The list contents are of type {@link org.eclipse.emf.ecore.xcore.XClassifier}.
   * It is bidirectional and its opposite is '{@link org.eclipse.emf.ecore.xcore.XClassifier#getPackage <em>Package</em>}'.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Classifiers</em>' containment reference list isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Classifiers</em>' containment reference list.
   * @see org.eclipse.emf.ecore.xcore.XcorePackage#getXPackage_Classifiers()
   * @see org.eclipse.emf.ecore.xcore.XClassifier#getPackage
   * @model opposite="package" containment="true"
   * @generated
   */
  EList<XClassifier> getClassifiers();

} // XPackage
