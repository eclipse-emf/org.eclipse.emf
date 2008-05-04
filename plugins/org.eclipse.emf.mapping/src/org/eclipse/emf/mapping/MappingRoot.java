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
 * $Id: MappingRoot.java,v 1.5 2008/05/04 17:03:16 emerks Exp $
 */
package org.eclipse.emf.mapping;


import java.util.Collection;

import org.eclipse.emf.mapping.domain.MappingDomain;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Root</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.emf.mapping.MappingRoot#isOutputReadOnly <em>Output Read Only</em>}</li>
 *   <li>{@link org.eclipse.emf.mapping.MappingRoot#isTopToBottom <em>Top To Bottom</em>}</li>
 *   <li>{@link org.eclipse.emf.mapping.MappingRoot#getCommandStack <em>Command Stack</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.emf.mapping.MappingPackage#getMappingRoot()
 * @model
 * @generated
 */
public interface MappingRoot extends Mapping
{
  /**
   * Returns the value of the '<em><b>Output Read Only</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the value of the '<em>Output Read Only</em>' attribute.
   * @see #setOutputReadOnly(boolean)
   * @see org.eclipse.emf.mapping.MappingPackage#getMappingRoot_OutputReadOnly()
   * @model
   * @generated
   */
  boolean isOutputReadOnly();

  /**
   * Sets the value of the '{@link org.eclipse.emf.mapping.MappingRoot#isOutputReadOnly <em>Output Read Only</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Output Read Only</em>' attribute.
   * @see #isOutputReadOnly()
   * @generated
   */
  void setOutputReadOnly(boolean value);

  /**
   * Returns the value of the '<em><b>Top To Bottom</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the value of the '<em>Top To Bottom</em>' attribute.
   * @see #setTopToBottom(boolean)
   * @see org.eclipse.emf.mapping.MappingPackage#getMappingRoot_TopToBottom()
   * @model
   * @generated
   */
  boolean isTopToBottom();

  /**
   * Sets the value of the '{@link org.eclipse.emf.mapping.MappingRoot#isTopToBottom <em>Top To Bottom</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Top To Bottom</em>' attribute.
   * @see #isTopToBottom()
   * @generated
   */
  void setTopToBottom(boolean value);

  /**
   * Returns the value of the '<em><b>Command Stack</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the value of the '<em>Command Stack</em>' attribute.
   * @see #setCommandStack(String)
   * @see org.eclipse.emf.mapping.MappingPackage#getMappingRoot_CommandStack()
   * @model
   * @generated
   */
  String getCommandStack();

  /**
   * Sets the value of the '{@link org.eclipse.emf.mapping.MappingRoot#getCommandStack <em>Command Stack</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Command Stack</em>' attribute.
   * @see #getCommandStack()
   * @generated
   */
  void setCommandStack(String value);

  /**
   * This returns the associated mapping domain.
   */
  MappingDomain getDomain();

  /**
   * This sets the domain of this mapping root.
   */
  void setDomain(MappingDomain domain);

  /**
   * This refreshes the mapped objects states of all mappings rooted at the specified mapping subtree.
   */
  void refreshMappedObjectStates(Mapping subtree);

  /**
   * This returns a collection of the mappings that refer to the given object.
   */
  Collection<? extends Mapping> getMappings(Object object);

  /**
   * This returns the <bf>intersection</bf> of the result of {@link #getMappings(Object) getMappings(Object)} 
   * for each object of the collection.
   */
  Collection<? extends Mapping> getAllMappings(Collection<?> collection);

  /**
   * This returns the subset of mappings returned by {@link #getAllMappings} 
   * such that have each mapping has <bf>exactly</bf> the collection as its mapped objects,
   * i.e., as return by {@link org.eclipse.emf.mapping.Mapping#getMappedObjects() Mapping#getMappedObjects()}.
   */
  Collection<? extends Mapping> getExactMappings(Collection<?> collection);

  /**
   * This returns the mapping in the tree that would be the parent of a mapping that has the given collection as its mapped objects.
   */
  Mapping getParentMapping(Collection<?> collection);

  /**
   * If mapping is null, this checks if a mapping can be created with the given inputs and outputs. If mapping is
   * not null, it checks if the specified mapping can be changed to the given inputs and outputs.
   */
  boolean canCreateMapping(Collection<?> inputs, Collection<?> outputs, Mapping mapping);

  /**
   * This checks if the given mapping can be removed.
   */
  boolean canRemoveMapping(Mapping mapping);

  /**
   * This creates a new, appropriately-parented, mapping object with the given collections of input and output objects.
   */
  Mapping createMapping(Collection<?> inputs, Collection<?> outputs);

  /**
   * This method resets the mapping and output dirty flags.
   */
  void resetDirty();

  /**
   * This method returns true if the mapping tree needs to be saved.
   */
  boolean isDirty();

  /**
   * This method returns true if the mapping output needs to be saved.
   */
  boolean isOutputDirty();

  /**
   * This method sets the output dirty flag.
   */
  void setOutputDirty(boolean dirty);

  /**
   * This returns whether the given object is an input object in the domain.
   */
  boolean isInputObject(Object object);

  /**
   * This returns whether the given object is an output object in the domain.
   */
  boolean isOutputObject(Object object);

  /**
   * This returns whether the given object is a top domain object.
   */
  boolean isTopObject(Object object);

  /**
   * This returns whether the given object is a bottom domain object.
   */
  boolean isBottomObject(Object object);

  /**
   * This returns whether the given object descends from one of the root's input or output objects.
   */
  boolean isAttachedObject(Object object);

  /**
   * This adds cached information about this mapping.
   */
  void register(Mapping mapping);

  /**
   * This removes cached information about this mapping.
   */
  void deregister(Mapping mapping);

  /**
   * This returns the mapping state, if any, of the given object.
   */
  MappedObjectState getMappedObjectState(Object object);

  /**
   * This returns the root of the type mapping model.
   */
  MappingRoot getTypeMappingRoot();

  /**
   * This disposes all the adapters used to record the mapped object state.
   */
  void dispose();

} // MappingRoot

