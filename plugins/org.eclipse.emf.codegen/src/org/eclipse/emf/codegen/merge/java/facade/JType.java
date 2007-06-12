/**
 * <copyright>
 *
 * Copyright (c) 2006 IBM Corporation and others.
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
 * $Id: JType.java,v 1.3 2007/06/12 20:56:05 emerks Exp $
 */
package org.eclipse.emf.codegen.merge.java.facade;


/**
 * Represents a source type in a compilation unit, either as a top-level type or a member type.
 * The corresponding syntactic units are ClassDeclaration (JLS2 8.1) and InterfaceDeclaration (JLS2 9.1).
 * Enumeration types and annotation types, added in J2SE 1.5, are represented as
 * classes and interfaces, respectively.  
 * @since 2.2.0
 */
public interface JType extends JAbstractType
{  
  /**
   * Returns the name of this type's superclass. The syntax for a superclass name
   * is specified by Super in ClassDeclaration (JLS2 8.1). Type names must be
   * specified as they would appear in source code. For example: 
   * <code>"Object"</code>, or <code>"java.io.File"</code>.
   * As of J2SE 1.5, the superclass may also include parameterized
   * types like <code>"ArrayList&lt;String&gt;"</code>.
   *
   * @return the superclass name, or <code>null</code> if this type represents
   *   an interface or if no superclass has been assigned to this class
   */
  String getSuperclass();
  
  /**
   * Sets the name of this type's superclass. Has no effect if this type
   * represents an interface. A <code>null</code> name indicates that no 
   * superclass name (extends clause) should appear in the source code.
   * The syntax for a superclass name is specified by Super in ClassDeclaration
   * (JLS2 8.1). Type names must be specified as they would appear in source code.
   * For example: <code>"Object"</code>, or <code>"java.io.File"</code>.
   * As of J2SE 1.5, the superclass may also include parameterized
   * types like <code>"ArrayList&lt;String&gt;"</code>.
   *
   * @param superclassName the superclass name, or <code>null</code> if this type
   *   should have to no explicitly specified superclass
   */
  void setSuperclass(String superclassName);
  
  /**
   * Returns the names of interfaces that this type implements or extends,
   * in the order in which they are listed in the source, or an empty array
   * if no super interfaces are present. The syntax for interface names is
   * defined by Interfaces in ClassDeclaration (JLS2 8.1). Type names appear
   * as they would in source code. For example: <code>"Cloneable"</code>,
   * or <code>"java.io.Serializable"</code>.
   * As of J2SE 1.5, super interfaces may also include parameterized
   * types like <code>"List&lt;String&gt;"</code>.
   * <p>
   * For classes, this method returns the interfaces that this class implements.
   * For interfaces, this method returns the interfaces that this interface extends.
   * </p>
   * 
   * @return the list of interface names, or an empty array if no interfaces
   * are declared
   */
  String[] getSuperInterfaces();  
  
  /**
   * Sets the names of interfaces that this type implements or extends,
   * in the order in which they are to be listed in the source. An empty array
   * parameter indicates that no super interfaces are present. The syntax for
   * interface names is defined by Interfaces in ClassDeclaration (JLS2 8.1).
   * Type names appear as they would in source code. For example: 
   * <code>"Cloneable"</code>, or <code>"java.io.Serializable"</code>.
   * As of J2SE 1.5, super interfaces may also include parameterized
   * types like <code>"List&lt;String&gt;"</code>.
   * <p>
   * For classes, this method sets the interfaces that this class implements.
   * For interfaces, this method sets the interfaces that this interface extends.
   * </p>
   * 
   * @param interfaceNames the list of interface names
   */
  void setSuperInterfaces(String[] interfaceNames);
  
  /**
   * Adds the given interface name to the names of interfaces that this type implements or extends
   * (the name will be added after the existing interface names). This is a convenience method.
   *
   * For classes, this represents the interfaces that this class implements.
   * For interfaces, this represents the interfaces that this interface extends.
   * The name may or may not be fully qualified.
   *
   * @param interfaceName the syntax for an interface name is defined by
   *  Interfaces in ClassDeclaration (JLS2 8.1). Type names must be specified as they would
   *  appear in source code. For example: "Cloneable", "java.io.Serializable".
   */
  void addSuperInterface(String interfaceName);
  
  /**
   * Returns the formal type parameters for this type.
   * Returns an empty array if this method has no formal type parameters.
   * <p>Formal type parameters are as they appear in the source
   * code; for example: 
   * <code>"X extends List&lt;String&gt; & Serializable"</code>.
   * </p>
   *
   * @return the formal type parameters of this type,
   * in the order declared in the source, or an empty array if no type parameters
   * are declared
   */
  String[] getTypeParameters();  
  
  /**
   * Sets the formal type parameters for this type.
   * <p>Formal type parameters are given as they appear in the source
   * code; for example: 
   * <code>"X extends List&lt;String&gt; & Serializable"</code>.
   * </p>
   *
   * @param typeParameters the formal type parameters of this type,
   * in the order to appear in the source, an empty array if none
   * @since 2.3.0
   */
  void setTypeParameters(String[] typeParameters);
}
