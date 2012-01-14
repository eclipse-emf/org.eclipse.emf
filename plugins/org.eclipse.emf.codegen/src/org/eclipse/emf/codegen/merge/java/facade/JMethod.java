/**
 * Copyright (c) 2006-2007 IBM Corporation and others.
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
 * Represents a method declaration. 
 * @since 2.2.0
 */
public interface JMethod extends JMember
{
  /**
   * Returns whether this method is a constructor.
   * @return <code>true</code> for constructors, and <code>false</code> for methods
   */
  boolean isConstructor();
  
  /**
   * Returns the return type name, or <code>null</code>. 
   * Returns <code>null</code> for constructors.
   * The syntax for return type name corresponds to ReturnType in 
   * MethodDeclaration (JLS2 8.4). Names are returned as they appear in the source
   * code; for example: <code>"File"</code>, <code>"java.io.File"</code>,
   * <code>"int[]"</code>, or <code>"void"</code>.
   *
   * @return the return type
   */
  String getReturnType();
  
  /**
   * Sets the return type name. This has no effect on constructors.
   * The syntax for return type name corresponds to ReturnType in 
   * MethodDeclaration (JLS2 8.4). Type names are specified as they appear in the 
   * source code; for example: <code>"File"</code>, <code>"java.io.File"</code>,
   * <code>"int[]"</code>, or <code>"void"</code>.
   *
   * @param type the return type
   */
  void setReturnType(String type);
  
  /**
   * Returns the formal type parameters for this method.
   * Returns an empty array if this method has no formal type parameters.
   * <p>Formal type parameters are as they appear in the source
   * code; for example: 
   * <code>"X extends List&lt;String&gt; & Serializable"</code>.
   * </p>
   *
   * @return the formal type parameters of this method,
   * in the order declared in the source, or an empty array if no type parameters
   * are declared
   */
  String[] getTypeParameters();  
  
  /**
   * Sets the formal type parameters for this method.
   * @param typeParameters in the order declared in the source, or an empty array if no type parameters
   * are declared
   * @since 2.3.0
   */
  void setTypeParameters(String[] typeParameters);
  
  /**
   * Returns the names of parameters in this method in the order they are declared,
   * or an empty array if no parameters are declared.
   * The parameter names are identifiers as defined by Formal Parameters (JLS2 and JLS3 8.4.1).
   * Parameter names do not include extra dimensions.
   * @return the list of parameter names, or an empty array if no parameters
   * are declared
   */
  String[] getParameterNames();
  
  /**
   * Returns the erased type names for the parameters of this method in the order 
   * they are declared, or an empty array if no parameters are declared.
   * The syntax for type names is defined by Formal Parameters (JLS2 8.4.1). 
   * Type names must be specified as they would appear in source code. For
   * example: <code>"File"</code>, <code>"java.io.File"</code>, or 
   * <code>"int[]"</code>.
   * 
   * @return the list of the erased types of the parameters, or an empty array if no 
   * parameters are declared
   */
  String[] getParameterTypes();

  /**
   * Returns the full types for the parameters of this method in the order 
   * they are declared, or an empty array if no parameters are declared.
   * The syntax for type names is defined by Formal Parameters (JLS2 8.4.1). 
   * Type names must be specified as they would appear in source code. For
   * example: <code>"File"</code>, <code>"java.io.File"</code>, or 
   * <code>"int[]"</code>.
   *
   * @return the list of the full types of the parameters, or an empty array if no 
   * parameters are declared
   * @since 2.4
   */
  String[] getFullParameterTypes();
  
  /**
   * Sets the names of parameters in this method in the order they are
   * to be declared.
   * The parameter names are identifiers as defined by Formal Parameters (JLS2 and JLS3 8.4.1).
   * Parameter names do not include extra dimensions.
   * @param names the list of parameter names
   */
  void setParameterNames(String[] names) throws IllegalArgumentException;

  /**
   * Returns the parameters of this method in the order they are declared,
   * or empty array if no parameters are declared.
   * The syntax for the parameters is defined by Formal Parameters (JLS2 and JLS3 8.4.1). 
   * Types and parameter names must be specified as they would appear in source code. For
   * example: <code>"File file"</code>, <code>"java.io.File file"</code>, or 
   * <code>"int[][] n[]"</code>, or <code>final @Annotation int[]... n</code>.
   * 
   * @return the list of parameters, or or an empty array if no parameters
   * are declared
   * @since 2.3.0
   */
  String[] getParameters();
  
  /**
   * Sets the parameters in this method in the order they are
   * to be declared. 
   * The syntax for the parameters is defined by Formal Parameters (JLS2 and JLS3 8.4.1). 
   * Types and parameter names must be specified as they would appear in source code. For
   * example: <code>"File file"</code>, <code>"java.io.File file"</code>, or 
   * <code>"int[][] n[]"</code>, or <code>final @Annotation int[]... n</code>.
   * @param parameters the list of parameters, or or an empty array if no parameters
   * are declared
   * @since 2.3.0
   */
  void setParameters(String[] parameters);
  
  /**
   * Returns the type signatures of the exceptions this method throws,
   * in the order declared in the source. Returns an empty array
   * if this method throws no exceptions.
   * <p>
   * For example, a source method declaring <code>"throws IOException"</code>,
   * would return the array <code>{"QIOException;"}</code>.
   * </p>
   * <p>
   * The type signatures may be either unresolved (for source types)
   * or resolved (for binary types), and either basic (for basic types)
   * or rich (for parameterized types).
   * </p>
   *
   * @return the list of the exceptions, or an empty array if no exceptions
   * are declared
   */
  String[] getExceptions();
  
  /**
   * Sets the names of the exception types this method throws,
   * in the order in which they are declared in the source. An empty array
   * indicates this method declares no exception types.
   * The syntax for an exception type name is defined by Method Throws (JLS2 8.4.4).
   * Type names must be specified as they would appear in source code. For 
   * example: <code>"IOException"</code> or <code>"java.io.IOException"</code>.
   *
   * @param exceptionTypes the list of exception types
   */
  void setExceptions(String[] exceptionTypes);
  
  /**
   * Adds the given exception to the end of the list of exceptions this method
   * is declared to throw.
   * The syntax for an exception type name is defined by Method Throws (JLS2 8.4.4).
   * Type names must be specified as they would appear in source code. For 
   * example: <code>"IOException"</code> or <code>"java.io.IOException"</code>.
   * This is a convenience method for <code>setExceptions</code>.
   *
   * @param exceptionType the exception type
   * @see #setExceptions(String[])
   */
  void addException(String exceptionType);
  
  /**
   * Returns the body of this method. The method body includes all code following
   * the method declaration, including the enclosing braces. 
   *
   * @return the body, or <code>null</code> if the method has no body (for
   *   example, for an abstract or native method)
   */
  String getBody(); 
  
  /**
   * Sets the body of this method. The method body includes all code following
   * the method declaration, including the enclosing braces. No formatting or
   * syntax checking is performed on the body.
   *
   * @param body the body, or <code>null</code> indicating the method has no body (for
   *   example, for an abstract or native method)
   */
  void setBody(String body);  
}
