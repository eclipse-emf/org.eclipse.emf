/**
 * Copyright (c) 2006 IBM Corporation and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: 
 *   IBM - Initial API and implementation
 */
package org.eclipse.emf.codegen.merge.java.facade;


/**
 * Represents a field declaration. 
 * @since 2.2.0
 */
public interface JField extends JMember
{
  /**
   * Returns the initializer expression for this field.
   * The syntax for an initializer corresponds to VariableInitializer (JLS2 8.3). 
   * <p>
   * Note: The expression does not include a "<code>=</code>".
   * </p>
   *
   * @return the initializer expression, or <code>null</code> if this field does
   *    not have an initializer
   */
  String getInitializer();
  
  /**
   * Sets the initializer expression for this field.
   * The syntax for an initializer corresponds to VariableInitializer (JLS2 8.3). 
   * <p>
   * Note: The expression does not include a "<code>=</code>".
   * </p>
   *
   * @param initializer the initializer expression, or <code>null</code> indicating
   *   the field does not have an initializer
   */
  void setInitializer(String initializer);
    
  /**
   * Returns the type name of this field. The syntax for a type name of a field
   * corresponds to Type in Field Declaration (JLS2 8.3).
   *
   * @return the name of the type
   */
  String getType();  
  
  /**
   * Sets the type name of this field. The syntax for a type name of a field
   * corresponds to Type in Field Declaration (JLS2 8.3). Type names must be 
   * specified as they should appear in source code. For example: 
   * <code>"String"</code>, <code>"int[]"</code>, or <code>"java.io.File"</code>.
   *
   * @param typeName the type name
   */
  void setType(String typeName);  
}
