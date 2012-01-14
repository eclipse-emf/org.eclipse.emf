/**
 * Copyright (c) 2007 IBM Corporation and others.
 * All rights reserved.  This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *   IBM - Initial API and implementation
 */
package org.eclipse.emf.test.models.dbitem;

import org.eclipse.emf.ecore.EObject;

import org.eclipse.emf.test.models.dbprice.PenType;
import org.eclipse.emf.test.models.dbprice.PencilType;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Db Type</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link org.eclipse.emf.test.models.dbitem.DbType#getRedPen <em>Red Pen</em>}</li>
 *   <li>{@link org.eclipse.emf.test.models.dbitem.DbType#getBluePen <em>Blue Pen</em>}</li>
 *   <li>{@link org.eclipse.emf.test.models.dbitem.DbType#getRedPencil <em>Red Pencil</em>}</li>
 *   <li>{@link org.eclipse.emf.test.models.dbitem.DbType#getBluePencil <em>Blue Pencil</em>}</li>
 * </ul>
 * </p>
 *
 * @see org.eclipse.emf.test.models.dbitem.DBItemPackage#getDbType()
 * @model extendedMetaData="name='dbType' kind='elementOnly'"
 * @generated
 */
public interface DbType extends EObject
{
  /**
   * Returns the value of the '<em><b>Red Pen</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Red Pen</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Red Pen</em>' containment reference.
   * @see #setRedPen(PenType)
   * @see org.eclipse.emf.test.models.dbitem.DBItemPackage#getDbType_RedPen()
   * @model containment="true" required="true"
   *        extendedMetaData="kind='element' name='red_pen' namespace='##targetNamespace'"
   * @generated
   */
  PenType getRedPen();

  /**
   * Sets the value of the '{@link org.eclipse.emf.test.models.dbitem.DbType#getRedPen <em>Red Pen</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Red Pen</em>' containment reference.
   * @see #getRedPen()
   * @generated
   */
  void setRedPen(PenType value);

  /**
   * Returns the value of the '<em><b>Blue Pen</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Blue Pen</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Blue Pen</em>' containment reference.
   * @see #setBluePen(PenType)
   * @see org.eclipse.emf.test.models.dbitem.DBItemPackage#getDbType_BluePen()
   * @model containment="true" required="true"
   *        extendedMetaData="kind='element' name='blue_pen'"
   * @generated
   */
  PenType getBluePen();

  /**
   * Sets the value of the '{@link org.eclipse.emf.test.models.dbitem.DbType#getBluePen <em>Blue Pen</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Blue Pen</em>' containment reference.
   * @see #getBluePen()
   * @generated
   */
  void setBluePen(PenType value);

  /**
   * Returns the value of the '<em><b>Red Pencil</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Red Pencil</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Red Pencil</em>' containment reference.
   * @see #setRedPencil(PencilType)
   * @see org.eclipse.emf.test.models.dbitem.DBItemPackage#getDbType_RedPencil()
   * @model containment="true" required="true"
   *        extendedMetaData="kind='element' name='red_pencil' namespace='##targetNamespace'"
   * @generated
   */
  PencilType getRedPencil();

  /**
   * Sets the value of the '{@link org.eclipse.emf.test.models.dbitem.DbType#getRedPencil <em>Red Pencil</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Red Pencil</em>' containment reference.
   * @see #getRedPencil()
   * @generated
   */
  void setRedPencil(PencilType value);

  /**
   * Returns the value of the '<em><b>Blue Pencil</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Blue Pencil</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Blue Pencil</em>' containment reference.
   * @see #setBluePencil(PencilType)
   * @see org.eclipse.emf.test.models.dbitem.DBItemPackage#getDbType_BluePencil()
   * @model containment="true" required="true"
   *        extendedMetaData="kind='element' name='blue_pencil'"
   * @generated
   */
  PencilType getBluePencil();

  /**
   * Sets the value of the '{@link org.eclipse.emf.test.models.dbitem.DbType#getBluePencil <em>Blue Pencil</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Blue Pencil</em>' containment reference.
   * @see #getBluePencil()
   * @generated
   */
  void setBluePencil(PencilType value);

} // DbType
