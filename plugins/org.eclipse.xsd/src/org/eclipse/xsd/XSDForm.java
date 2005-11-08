/**
 * <copyright>
 *
 * Copyright (c) 2002-2004 IBM Corporation and others.
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
 * $Id: XSDForm.java,v 1.3 2005/11/08 13:52:50 emerks Exp $
 */
package org.eclipse.xsd;


import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.eclipse.emf.common.util.AbstractEnumerator;


/**
 * <!-- begin-user-doc -->
 * A representation of the literals of the enumeration '<em><b>Form</b></em>'.
 * @see org.eclipse.xsd.XSDSchema#getAttributeFormDefault()
 * @see org.eclipse.xsd.XSDSchema#getElementFormDefault()
 * @see org.eclipse.xsd.XSDFeature#getForm()
 * @<!-- end-user-doc -->
 * @see org.eclipse.xsd.XSDPackage#getXSDForm()
 * @model
 * @generated
 */
public final class XSDForm extends AbstractEnumerator
{
  /**
   * The '<em><b>Qualified</b></em>' literal value.
   * <!-- begin-user-doc --> 
   * <p>
   * This is used to indicate that the {@link org.eclipse.xsd.XSDFeature feature} is qualified,
   * i.e.,  the value of the '{@link org.eclipse.xsd.XSDNamedComponent <em>Target Namespace</em>}' attribute
   * will be that of the {@link org.eclipse.xsd.XSDSchema#getTargetNamespace() schema}.
   * </p>
   * <!-- end-user-doc --> 
   * @see #QUALIFIED_LITERAL
   * @model name="qualified"
   * @generated
   * @ordered
   */
  public static final int QUALIFIED = 0;

  /**
   * The '<em><b>Unqualified</b></em>' literal value.
   * <!-- begin-user-doc --> 
   * <p>
   * This is used to indicate that the {@link org.eclipse.xsd.XSDFeature feature} is unqualified,
   * i.e.,  the value of the '{@link org.eclipse.xsd.XSDNamedComponent <em>Target Namespace</em>}' attribute
   * will be <code>null</code>.
   * </p>
   * <!-- end-user-doc --> 
   * @see #UNQUALIFIED_LITERAL
   * @model name="unqualified"
   * @generated
   * @ordered
   */
  public static final int UNQUALIFIED = 1;
  /**
   * The '<em><b>Qualified</b></em>' literal object.
   * <!-- begin-user-doc --> 
   * <p>
   * </p>
   * <!-- end-user-doc --> 
   * @see #QUALIFIED
   * @generated
   * @ordered
   */
  public static final XSDForm QUALIFIED_LITERAL = new XSDForm(QUALIFIED, "qualified", "qualified");

  /**
   * The '<em><b>Unqualified</b></em>' literal object.
   * <!-- begin-user-doc --> 
   * <p>
   * </p>
   * <!-- end-user-doc --> 
   * @see #UNQUALIFIED
   * @generated
   * @ordered
   */
  public static final XSDForm UNQUALIFIED_LITERAL = new XSDForm(UNQUALIFIED, "unqualified", "unqualified");

  /**
   * An array of all the '<em><b>Form</b></em>' enumerators.
   * <!-- begin-user-doc --> 
   * <!-- end-user-doc --> 
   * @generated
   */
  private static final XSDForm[] VALUES_ARRAY =
    new XSDForm[]
    {
      QUALIFIED_LITERAL,
      UNQUALIFIED_LITERAL,
    };

  /**
   * A public read-only list of all the '<em><b>Form</b></em>' enumerators.
   * <!-- begin-user-doc --> 
   * <!-- end-user-doc --> 
   * @generated
   */
  public static final List VALUES = Collections.unmodifiableList(Arrays.asList(VALUES_ARRAY));

  /**
   * Returns the '<em><b>Form</b></em>' literal with the specified literal value.
   * <!-- begin-user-doc --> 
   * <!-- end-user-doc --> 
   * @generated
   */
  public static XSDForm get(String literal)
  {
    for (int i = 0; i < VALUES_ARRAY.length; ++i)
    {
      XSDForm result = VALUES_ARRAY[i];
      if (result.toString().equals(literal))
      {
        return result;
      }
    }
    return null;
  }

  /**
   * Returns the '<em><b>Form</b></em>' literal with the specified name.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public static XSDForm getByName(String name)
  {
    for (int i = 0; i < VALUES_ARRAY.length; ++i)
    {
      XSDForm result = VALUES_ARRAY[i];
      if (result.getName().equals(name))
      {
        return result;
      }
    }
    return null;
  }

  /**
   * Returns the '<em><b>Form</b></em>' literal with the specified integer value.
   * <!-- begin-user-doc --> 
   * <!-- end-user-doc --> 
   * @generated
   */
  public static XSDForm get(int value)
  {
    switch (value)
    {
      case QUALIFIED: return QUALIFIED_LITERAL;
      case UNQUALIFIED: return UNQUALIFIED_LITERAL;
    }
    return null;	
  }

  /**
   * Only this class can construct instances.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private XSDForm(int value, String name, String literal)
  {
    super(value, name, literal);
  }

} 
