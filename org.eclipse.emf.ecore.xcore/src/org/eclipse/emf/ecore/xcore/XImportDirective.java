/**
 * <copyright>
 * </copyright>
 *
 * $Id$
 */
package org.eclipse.emf.ecore.xcore;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>XImport Directive</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.emf.ecore.xcore.XImportDirective#getImportedNamespace <em>Imported Namespace</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.emf.ecore.xcore.XcorePackage#getXImportDirective()
 * @model
 * @generated
 */
public interface XImportDirective extends XModelElement
{
  /**
   * Returns the value of the '<em><b>Imported Namespace</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Imported Namespace</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Imported Namespace</em>' attribute.
   * @see #setImportedNamespace(String)
   * @see org.eclipse.emf.ecore.xcore.XcorePackage#getXImportDirective_ImportedNamespace()
   * @model required="true"
   * @generated
   */
  String getImportedNamespace();

  /**
   * Sets the value of the '{@link org.eclipse.emf.ecore.xcore.XImportDirective#getImportedNamespace <em>Imported Namespace</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Imported Namespace</em>' attribute.
   * @see #getImportedNamespace()
   * @generated
   */
  void setImportedNamespace(String value);

} // XImportDirective
