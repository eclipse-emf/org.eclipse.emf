/**
 * <copyright>
 * </copyright>
 *
 * $Id: ResourceChange.java,v 1.3 2004/12/16 21:51:46 emerks Exp $
 */
package org.eclipse.emf.ecore.change;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Resource Change</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.emf.ecore.change.ResourceChange#getResourceURI <em>Resource URI</em>}</li>
 *   <li>{@link org.eclipse.emf.ecore.change.ResourceChange#getResource <em>Resource</em>}</li>
 *   <li>{@link org.eclipse.emf.ecore.change.ResourceChange#getValue <em>Value</em>}</li>
 *   <li>{@link org.eclipse.emf.ecore.change.ResourceChange#getListChanges <em>List Changes</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.emf.ecore.change.ChangePackage#getResourceChange()
 * @model
 * @generated
 */
public interface ResourceChange extends EObject
{
  /**
   * Returns the value of the '<em><b>Resource URI</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Resource URI</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Resource URI</em>' attribute.
   * @see #setResourceURI(String)
   * @see org.eclipse.emf.ecore.change.ChangePackage#getResourceChange_ResourceURI()
   * @model
   * @generated
   */
  String getResourceURI();

  /**
   * Sets the value of the '{@link org.eclipse.emf.ecore.change.ResourceChange#getResourceURI <em>Resource URI</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Resource URI</em>' attribute.
   * @see #getResourceURI()
   * @generated
   */
  void setResourceURI(String value);

  /**
   * Returns the value of the '<em><b>Resource</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Resource</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Resource</em>' attribute.
   * @see #setResource(Resource)
   * @see org.eclipse.emf.ecore.change.ChangePackage#getResourceChange_Resource()
   * @model transient="true" derived="true"
   * @generated
   */
  Resource getResource();

  /**
   * Sets the value of the '{@link org.eclipse.emf.ecore.change.ResourceChange#getResource <em>Resource</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Resource</em>' attribute.
   * @see #getResource()
   * @generated
   */
  void setResource(Resource value);

  /**
   * Returns the value of the '<em><b>Value</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Value</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Value</em>' attribute.
   * @see org.eclipse.emf.ecore.change.ChangePackage#getResourceChange_Value()
   * @model many="false" transient="true" changeable="false" volatile="true" derived="true"
   * @generated
   */
  EList getValue();

  /**
   * Returns the value of the '<em><b>List Changes</b></em>' containment reference list.
   * The list contents are of type {@link org.eclipse.emf.ecore.change.ListChange}.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>List Changes</em>' containment reference list isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>List Changes</em>' containment reference list.
   * @see org.eclipse.emf.ecore.change.ChangePackage#getResourceChange_ListChanges()
   * @model type="org.eclipse.emf.ecore.change.ListChange" containment="true"
   * @generated
   */
  EList getListChanges();

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @model
   * @generated
   */
  void apply();

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @model
   * @generated
   */
  void applyAndReverse();

} // ResourceChange
