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
 * Utility class for decoding modifier flags in Java elements.
 * <p>
 * This class provides static methods only; it is not intended to be
 * instantiated or subclassed by clients.
 * </p>
 *
 * @see JNode#getFlags()
 * @since 2.2.0
 */
public final class FacadeFlags
{
  /**
   * Constant representing the absence of any flag
   */
  public static final int DEFAULT = 0;

  /**
   * Public access flag. See The Java Virtual Machine Specification for more details.
   */
  public static final int PUBLIC = 0x0001;

  /**
   * Private access flag. See The Java Virtual Machine Specification for more details.
   */
  public static final int PRIVATE = 0x0002;

  /**
   * Protected access flag. See The Java Virtual Machine Specification for more details.
   */
  public static final int PROTECTED = 0x0004;

  /**
   * Static access flag. See The Java Virtual Machine Specification for more details.
   */
  public static final int STATIC = 0x0008;

  /**
   * Final access flag. See The Java Virtual Machine Specification for more details.
   */
  public static final int FINAL = 0x0010;

  /**
   * Synchronized access flag. See The Java Virtual Machine Specification for more details.
   */
  public static final int SYNCHRONIZED = 0x0020;

  /**
   * Volatile property flag. See The Java Virtual Machine Specification for more details.
   */
  public static final int VOLATILE = 0x0040;

  /**
   * Transient property flag. See The Java Virtual Machine Specification for more details.
   */
  public static final int TRANSIENT = 0x0080;

  /**
   * Native property flag. See The Java Virtual Machine Specification for more details.
   */
  public static final int NATIVE = 0x0100;

  /**
   * Interface property flag. See The Java Virtual Machine Specification for more details.
   */
  public static final int INTERFACE = 0x0200;

  /**
   * Abstract property flag. See The Java Virtual Machine Specification for more details.
   */
  public static final int ABSTRACT = 0x0400;

  /**
   * Strictfp property flag. See The Java Virtual Machine Specification for more details.
   */
  public static final int STRICTFP = 0x0800;

  /**
   * Super property flag. See The Java Virtual Machine Specification for more details.
   */
  public static final int SUPER = 0x0020;

  /**
   * Synthetic property flag. See The Java Virtual Machine Specification for more details.
   */
  public static final int SYNTHETIC = 0x1000;

  /**
   * Deprecated property flag. See The Java Virtual Machine Specification for more details.
   */
  public static final int DEPRECATED = 0x100000;

  /**
   * Bridge method property flag (added in J2SE 1.5). Used to flag a compiler-generated
   * bridge methods.
   * See The Java Virtual Machine Specification for more details.
   */
  public static final int BRIDGE = 0x0040;

  /**
   * Varargs method property flag (added in J2SE 1.5).
   * Used to flag variable arity method declarations.
   * See The Java Virtual Machine Specification for more details.
   */
  public static final int VARARGS = 0x0080;

  /**
   * Enum property flag (added in J2SE 1.5).
   * See The Java Virtual Machine Specification for more details.
   */
  public static final int ENUM = 0x4000;

  /**
   * Annotation property flag (added in J2SE 1.5).
   * See The Java Virtual Machine Specification for more details.
   */
  public static final int ANNOTATION = 0x2000;

  /**
   * Not instantiable.
   */
  private FacadeFlags()
  {
    // Not instantiable
  }

  /**
   * Returns whether the given integer includes the <code>abstract</code> modifier.
   *
   * @param flags the flags
   * @return <code>true</code> if the <code>abstract</code> modifier is included
   */
  public static boolean isAbstract(int flags)
  {
    return (flags & ABSTRACT) != 0;
  }

  /**
   * Returns whether the given integer includes the indication that the 
   * element is deprecated (<code>@deprecated</code> tag in Javadoc comment).
   *
   * @param flags the flags
   * @return <code>true</code> if the element is marked as deprecated
   */
  public static boolean isDeprecated(int flags)
  {
    return (flags & DEPRECATED) != 0;
  }

  /**
   * Returns whether the given integer includes the <code>final</code> modifier.
   *
   * @param flags the flags
   * @return <code>true</code> if the <code>final</code> modifier is included
   */
  public static boolean isFinal(int flags)
  {
    return (flags & FINAL) != 0;
  }

  /**
   * Returns whether the given integer includes the <code>interface</code> modifier.
   *
   * @param flags the flags
   * @return <code>true</code> if the <code>interface</code> modifier is included
   */
  public static boolean isInterface(int flags)
  {
    return (flags & INTERFACE) != 0;
  }

  /**
   * Returns whether the given integer includes the <code>native</code> modifier.
   *
   * @param flags the flags
   * @return <code>true</code> if the <code>native</code> modifier is included
   */
  public static boolean isNative(int flags)
  {
    return (flags & NATIVE) != 0;
  }

  /**
   * Returns whether the given integer includes the <code>private</code> modifier.
   *
   * @param flags the flags
   * @return <code>true</code> if the <code>private</code> modifier is included
   */
  public static boolean isPrivate(int flags)
  {
    return (flags & PRIVATE) != 0;
  }

  /**
   * Returns whether the given integer includes the <code>protected</code> modifier.
   *
   * @param flags the flags
   * @return <code>true</code> if the <code>protected</code> modifier is included
   */
  public static boolean isProtected(int flags)
  {
    return (flags & PROTECTED) != 0;
  }

  /**
   * Returns whether the given integer includes the <code>public</code> modifier.
   *
   * @param flags the flags
   * @return <code>true</code> if the <code>public</code> modifier is included
   */
  public static boolean isPublic(int flags)
  {
    return (flags & PUBLIC) != 0;
  }

  /**
   * Returns whether the given integer includes the <code>static</code> modifier.
   *
   * @param flags the flags
   * @return <code>true</code> if the <code>static</code> modifier is included
   */
  public static boolean isStatic(int flags)
  {
    return (flags & STATIC) != 0;
  }

  /**
   * Returns whether the given integer includes the <code>strictfp</code> modifier.
   *
   * @param flags the flags
   * @return <code>true</code> if the <code>strictfp</code> modifier is included
   */
  public static boolean isStrictfp(int flags)
  {
    return (flags & STRICTFP) != 0;
  }

  /**
   * Returns whether the given integer includes the <code>synchronized</code> modifier.
   *
   * @param flags the flags
   * @return <code>true</code> if the <code>synchronized</code> modifier is included
   */
  public static boolean isSynchronized(int flags)
  {
    return (flags & SYNCHRONIZED) != 0;
  }

  /**
   * Returns whether the given integer includes the indication that the 
   * element is synthetic.
   *
   * @param flags the flags
   * @return <code>true</code> if the element is marked synthetic
   */
  public static boolean isSynthetic(int flags)
  {
    return (flags & SYNTHETIC) != 0;
  }

  /**
   * Returns whether the given integer includes the <code>transient</code> modifier.
   *
   * @param flags the flags
   * @return <code>true</code> if the <code>transient</code> modifier is included
   */
  public static boolean isTransient(int flags)
  {
    return (flags & TRANSIENT) != 0;
  }

  /**
   * Returns whether the given integer includes the <code>volatile</code> modifier.
   *
   * @param flags the flags
   * @return <code>true</code> if the <code>volatile</code> modifier is included
   */
  public static boolean isVolatile(int flags)
  {
    return (flags & VOLATILE) != 0;
  }

  /**
   * Returns whether the given integer has the <code>AccBridge</code>
   * bit set.
   *
   * @param flags the flags
   * @return <code>true</code> if the <code>AccBridge</code> flag is included
   * @see #BRIDGE
   */
  public static boolean isBridge(int flags)
  {
    return (flags & BRIDGE) != 0;
  }

  /**
   * Returns whether the given integer has the <code>AccVarargs</code>
   * bit set.
   *
   * @param flags the flags
   * @return <code>true</code> if the <code>AccVarargs</code> flag is included
   * @see #VARARGS
   */
  public static boolean isVarargs(int flags)
  {
    return (flags & VARARGS) != 0;
  }

  /**
   * Returns whether the given integer has the <code>AccEnum</code>
   * bit set.
   *
   * @param flags the flags
   * @return <code>true</code> if the <code>AccEnum</code> flag is included
   * @see #ENUM
   */
  public static boolean isEnum(int flags)
  {
    return (flags & ENUM) != 0;
  }

  /**
   * Returns whether the given integer has the <code>AccAnnotation</code>
   * bit set.
   *
   * @param flags the flags
   * @return <code>true</code> if the <code>AccAnnotation</code> flag is included
   * @see #ANNOTATION
   */
  public static boolean isAnnotation(int flags)
  {
    return (flags & ANNOTATION) != 0;
  }

  /**
   * Returns a standard string describing the given modifier flags.
   * Only modifier flags are included in the output; deprecated,
   * synthetic, bridge, etc. flags are ignored.
   * <p>
   * The flags are output in the following order:
   * <pre>
   *   <code>public</code> <code>protected</code> <code>private</code> 
   *   <code>static</code> 
   *   <code>abstract</code> <code>final</code> <code>native</code> <code>synchronized</code> <code>transient</code> <code>volatile</code> <code>strictfp</code>
   * </pre>
   * This is a compromise between the orders specified in sections 8.1.1,
   * 8.3.1, 8.4.3, 8.8.3, 9.1.1, and 9.3 of <em>The Java Language 
   * Specification, Second Edition</em> (JLS2).
   * </p> 
   * <p>
   * Note that the flags of a method can include the AccVarargs flag that has no standard description. Since the AccVarargs flag has the same value as
   * the AccTransient flag (valid for fields only), attempting to get the description of method modifiers with the AccVarargs flag set would result in an
   * unexpected description. Clients should ensure that the AccVarargs is not included in the flags of a method as follows:
   * <pre>
   * IMethod method = ...
   * int flags = method.getFlags() & ~Flags.AccVarargs;
   * return Flags.toString(flags);
   * </pre>
   * </p>
   * <p>
   * Examples results:
   * <pre>
   *    <code>"public static final"</code>
   *    <code>"private native"</code>
   * </pre>
   * </p>
   *
   * @param flags the flags
   * @return the standard string representation of the given flags
   */
  public static String toString(int flags)
  {
    StringBuilder sb = new StringBuilder();

    if (isPublic(flags))
      sb.append("public "); //$NON-NLS-1$
    if (isProtected(flags))
      sb.append("protected "); //$NON-NLS-1$
    if (isPrivate(flags))
      sb.append("private "); //$NON-NLS-1$
    if (isStatic(flags))
      sb.append("static "); //$NON-NLS-1$
    if (isAbstract(flags))
      sb.append("abstract "); //$NON-NLS-1$
    if (isFinal(flags))
      sb.append("final "); //$NON-NLS-1$
    if (isNative(flags))
      sb.append("native "); //$NON-NLS-1$
    if (isSynchronized(flags))
      sb.append("synchronized "); //$NON-NLS-1$
    if (isTransient(flags))
      sb.append("transient "); //$NON-NLS-1$
    if (isVolatile(flags))
      sb.append("volatile "); //$NON-NLS-1$
    if (isStrictfp(flags))
      sb.append("strictfp "); //$NON-NLS-1$

    int len = sb.length();
    if (len == 0)
      return ""; //$NON-NLS-1$
    sb.setLength(len - 1);
    return sb.toString();
  }
}