/**
 * <copyright>
 *
 * Copyright (c) 2002-2006 IBM Corporation and others.
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
 * $Id: Mapping.java,v 1.5 2007/06/12 15:07:33 emerks Exp $
 */
package org.eclipse.emf.mapping;


import java.util.Collection;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.ecore.EObject;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Mapping</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.emf.mapping.Mapping#getHelper <em>Helper</em>}</li>
 *   <li>{@link org.eclipse.emf.mapping.Mapping#getNested <em>Nested</em>}</li>
 *   <li>{@link org.eclipse.emf.mapping.Mapping#getNestedIn <em>Nested In</em>}</li>
 *   <li>{@link org.eclipse.emf.mapping.Mapping#getInputs <em>Inputs</em>}</li>
 *   <li>{@link org.eclipse.emf.mapping.Mapping#getOutputs <em>Outputs</em>}</li>
 *   <li>{@link org.eclipse.emf.mapping.Mapping#getTypeMapping <em>Type Mapping</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.emf.mapping.MappingPackage#getMapping()
 * @model
 * @generated
 */
public interface Mapping extends EObject
{
  /**
   * Returns the value of the '<em><b>Helper</b></em>' containment reference.
   * It is bidirectional and its opposite is '{@link org.eclipse.emf.mapping.MappingHelper#getMapper <em>Mapper</em>}'.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Helper</em>' containment reference isn't clear, 
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Helper</em>' containment reference.
   * @see #setHelper(MappingHelper)
   * @see org.eclipse.emf.mapping.MappingPackage#getMapping_Helper()
   * @see org.eclipse.emf.mapping.MappingHelper#getMapper
   * @model opposite="mapper" containment="true"
   * @generated
   */
  MappingHelper getHelper();

  /**
   * Sets the value of the '{@link org.eclipse.emf.mapping.Mapping#getHelper <em>Helper</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Helper</em>' containment reference.
   * @see #getHelper()
   * @generated
   */
  void setHelper(MappingHelper value);

  /**
   * Returns the value of the '<em><b>Nested</b></em>' containment reference list.
   * The list contents are of type {@link org.eclipse.emf.mapping.Mapping}.
   * It is bidirectional and its opposite is '{@link org.eclipse.emf.mapping.Mapping#getNestedIn <em>Nested In</em>}'.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Nested</em>' containment reference list isn't clear, 
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Nested</em>' containment reference list.
   * @see org.eclipse.emf.mapping.MappingPackage#getMapping_Nested()
   * @see org.eclipse.emf.mapping.Mapping#getNestedIn
   * @model opposite="nestedIn" containment="true"
   * @generated
   */
  EList<Mapping> getNested();

  /**
   * Returns the value of the '<em><b>Nested In</b></em>' container reference.
   * It is bidirectional and its opposite is '{@link org.eclipse.emf.mapping.Mapping#getNested <em>Nested</em>}'.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Nested In</em>' container reference isn't clear, 
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Nested In</em>' container reference.
   * @see #setNestedIn(Mapping)
   * @see org.eclipse.emf.mapping.MappingPackage#getMapping_NestedIn()
   * @see org.eclipse.emf.mapping.Mapping#getNested
   * @model opposite="nested"
   * @generated
   */
  Mapping getNestedIn();

  /**
   * Sets the value of the '{@link org.eclipse.emf.mapping.Mapping#getNestedIn <em>Nested In</em>}' container reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Nested In</em>' container reference.
   * @see #getNestedIn()
   * @generated
   */
  void setNestedIn(Mapping value);

  /**
   * Returns the value of the '<em><b>Inputs</b></em>' reference list.
   * The list contents are of type {@link org.eclipse.emf.ecore.EObject}.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Inputs</em>' reference list isn't clear, 
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Inputs</em>' reference list.
   * @see org.eclipse.emf.mapping.MappingPackage#getMapping_Inputs()
   * @model
   * @generated
   */
  EList<EObject> getInputs();

  /**
   * Returns the value of the '<em><b>Outputs</b></em>' reference list.
   * The list contents are of type {@link org.eclipse.emf.ecore.EObject}.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Outputs</em>' reference list isn't clear, 
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Outputs</em>' reference list.
   * @see org.eclipse.emf.mapping.MappingPackage#getMapping_Outputs()
   * @model
   * @generated
   */
  EList<EObject> getOutputs();

  /**
   * Returns the value of the '<em><b>Type Mapping</b></em>' reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Type Mapping</em>' reference isn't clear, 
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Type Mapping</em>' reference.
   * @see #setTypeMapping(Mapping)
   * @see org.eclipse.emf.mapping.MappingPackage#getMapping_TypeMapping()
   * @model
   * @generated
   */
  Mapping getTypeMapping();

  /**
   * Sets the value of the '{@link org.eclipse.emf.mapping.Mapping#getTypeMapping <em>Type Mapping</em>}' reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Type Mapping</em>' reference.
   * @see #getTypeMapping()
   * @generated
   */
  void setTypeMapping(Mapping value);

  /**
   * This returns a set containing the results of {@link #getInputs} and {@link #getOutputs}.
   */
  public Collection<? extends EObject> getMappedObjects();

  /**
   * This returns the same as either {@link #getInputs} or {@link #getOutputs}
   * depending on the mapping root's isTopToBottom.
   */
  public EList<EObject> getTops();

  /**
   * This returns the same as either {@link #getInputs} or {@link #getOutputs}
   * depending on the mapping root's isTopToBottom.
   */
  public EList<EObject> getBottoms();

  /**
   * This returns either the containing mapping root or the object itself, if it is a mapping root.
   */
  public MappingRoot getMappingRoot();

  /**
   * This method is delegated to the containing mapping if one exists, or returns false otherwise.
   * Subclasses can override this method to return true when the logical direction of a mapping is
   * reversed (i.e., from outputs to inputs).
   */
  public boolean isReverse();

  /**
   * This returns the same as {@link #getInputs} if {@link #isReverse} returns false. 
   * Otherwise it returns the same as {@link #getOutputs}.
   */
  public EList<EObject> getSenders();

  /**
   * This returns the same as {@link #getOutputs} if {@link #isReverse} returns false. 
   * Otherwise it returns the same as {@link #getInputs}.
   */
  public EList<EObject> getReceivers();

  /**
   * This returns the mapping helper for this mapping, if it has one. Otherwise, if the mapping has an
   * associated type mapping it returns the type mapping's helper.
   */
  public MappingHelper getEffectiveHelper();

  /**
   * This returns a tree iterator that iterates over this mapping, 
   * all it's nested mappings, 
   * and their nested mappings, 
   * and so on.
   */
  public TreeIterator<Mapping> treeIterator();

  /**
   * This returns a tree iterator that iterates over this mapping (but only if includeRoot is true), 
   * all it's nested mappings, 
   * and their nested mappings, 
   * and so on.
   */
  public TreeIterator<Mapping> treeIterator(boolean includeRoot);

}
