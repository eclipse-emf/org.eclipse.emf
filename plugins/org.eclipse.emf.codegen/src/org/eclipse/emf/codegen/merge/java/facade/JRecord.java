/**
 * Copyright (c) 2006-2025 Eclipse contributors and others..
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 */
package org.eclipse.emf.codegen.merge.java.facade;


/**
 * Represents a record type in a compilation unit, either as a top-level type or a member type.
 * The corresponding syntactic unit is RecordDeclaration (JLS2 16).
 * @since 2.27.0
 */
public interface JRecord extends JAbstractType
{

  /**
   * Returns the names of interfaces that this record implements,
   * in the order in which they are listed in the source, or an empty array
   * if no super interfaces are present. The syntax for interface names is
   * defined by Interfaces in ClassDeclaration (JLS2 8.1). Type names appear
   * as they would in source code. For example: <code>"Cloneable"</code>,
   * or <code>"java.io.Serializable"</code>.
   * As of J2SE 1.5, super interfaces may also include parameterized
   * types like <code>"List&lt;String&gt;"</code>.
   * <p>
   * This method returns the interfaces that this record implements.
   * </p>
   *
   * @return the list of interface names, or an empty array if no interfaces
   * are declared
   */
  String[] getSuperInterfaces();

  /**
   * Sets the names of interfaces that this record implements,
   * in the order in which they are to be listed in the source. An empty array
   * parameter indicates that no super interfaces are present. The syntax for
   * interface names is defined by Interfaces in ClassDeclaration (JLS2 8.1).
   * Type names appear as they would in source code. For example:
   * <code>"Cloneable"</code>, or <code>"java.io.Serializable"</code>.
   * As of J2SE 1.5, super interfaces may also include parameterized
   * types like <code>"List&lt;String&gt;"</code>.
   * <p>
   * This method sets the interfaces that this record implements.
   * </p>
   *
   * @param interfaceNames the list of interface names
   */
  void setSuperInterfaces(String[] interfaceNames);

  /**
   * Adds the given interface name to the names of interfaces that this record implements
   * (the name will be added after the existing interface names). This is a convenience method.
   *
   * This represents the interfaces that this record implements.
   * The name may or may not be fully qualified.
   *
   * @param interfaceName the syntax for an interface name is defined by
   *  Interfaces in ClassDeclaration (JLS2 8.1). Type names must be specified as they would
   *  appear in source code. For example: "Cloneable", "java.io.Serializable".
   */
  void addSuperInterface(String interfaceName);

  /**
   * Returns the formal type parameters for this record.
   * Returns an empty array if this method has no formal type parameters.
   * <p>Formal type parameters are as they appear in the source
   * code; for example:
   * <code>"X extends List&lt;String&gt; & Serializable"</code>.
   * </p>
   *
   * @return the formal type parameters of this record,
   * in the order declared in the source, or an empty array if no type parameters
   * are declared
   */
  String[] getTypeParameters();

  /**
   * Sets the formal type parameters for this record.
   * <p>Formal type parameters are given as they appear in the source
   * code; for example:
   * <code>"X extends List&lt;String&gt; & Serializable"</code>.
   * </p>
   *
   * @param typeParameters the formal type parameters of this record,
   * in the order to appear in the source, an empty array if none
   */
  void setTypeParameters(String[] typeParameters);

  /**
   * Returns the names of record components in the order they are declared, or an empty
   * array if no record components are declared. The record component names are identifiers as defined by Formal
   * Parameters (JLS2 and JLS3 8.4.1). Record components names do not include extra dimensions.
   *
   * @return the list of record component names, or an empty array if no record components are declared
   */
  String[] getRecordComponentNames();

  /**
   * Returns the erased type names for the components of this record in the order they are
   * declared, or an empty array if no record components are declared. The syntax for type names is
   * defined by Formal Parameters (JLS2 8.4.1). Type names must be specified as they would appear
   * in source code. For example: <code>"File"</code>, <code>"java.io.File"</code>, or
   * <code>"int[]"</code>.
   *
   * @return the list of the erased types of the record components, or an empty array if no record components
   *             are declared
   */
  String[] getRecordComponentTypes();

  /**
   * Returns the full types for the components of this record in the order they are declared, or
   * an empty array if no record components are declared. The syntax for type names is defined by Formal
   * Parameters (JLS2 8.4.1). Type names must be specified as they would appear in source code.
   * For example: <code>"File"</code>, <code>"java.io.File"</code>, or <code>"int[]"</code>.
   *
   * @return the list of the full types of the record components, or an empty array if no record components are
   *             declared
   */
  String[] getFullRecordComponentTypes();

  /**
   * Sets the names of components in this record in the order they are to be declared. The
   * record component names are identifiers as defined by Formal Parameters (JLS2 and JLS3 8.4.1).
   * Record components names do not include extra dimensions.
   *
   * @param names the list of record components
   */
  void setRecordComponentNames(String[] names) throws IllegalArgumentException;

  /**
   * Returns the components of this record in the order they are declared, or empty array if
   * no record components are declared. The syntax for the components is defined by Formal
   * Parameters (JLS2 and JLS3 8.4.1). Types and record component names must be specified as
   * they would appear in source code. For example: <code>"File file"</code>,
   * <code>"java.io.File file"</code>, or <code>"int[][] n[]"</code>.
   *
   * @return the list of record components, or or an empty array if no record components are
   *             declared
   */
  String[] getRecordComponents();

  /**
   * Sets the components in this record in the order they are to be declared. The syntax for
   * the components is defined by Formal Parameters (JLS2 and JLS3 8.4.1). Types and
   * record component names must be specified as they would appear in source code. For example:
   * <code>"File file"</code>, <code>"java.io.File file"</code>, or <code>"int[][] n[]"</code>.
   *
   * @param recordComponents the list of record components, or or an empty array if no
   *            record components are declared
   */
  void setRecordComponents(String[] recordComponents);
}
