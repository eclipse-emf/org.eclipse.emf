/**
 * Copyright (c) 2004 IBM Corporation and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: 
 *   IBM - Initial API and implementation
 */
package org.eclipse.emf.test.tools.merger;

import target.ClassA;
import target.ClassB;

/**
 * Target javadoc 1
 * Target javadoc 2
 * <!-- begin-user-doc -->
 * Target user javadoc 3
 * Target user javadoc 4
 * <!-- end-user-doc -->
 * 
 * @author EMF Team (target)
 * @generated
 */
@ClassAnnotation
(
  comment = "target annotation, to be overwritten"
) // target line comment after @ClassAnnotation
@ClassAnnotationToBeSwept // line comment
(
  "target argument"
) // line comment after @ClassAnnotationToBeSwept
@LastClassAnnotation
public class MergerExample
{
  /**
   * Target comment for bb
   * <!-- begin-user-doc -->
   * Target user comment for bb
   * <!-- end-user-doc -->
   */
  @Generated
  @FieldAnnotation(comment = "target annotation for bb")
  @AnotherFieldAnnotation("target")
  int bb;
  
  String s1 = "source"; //$NON-NLS-1$
  
  /**
   * This method uses Generated annotation.
   * 
   * Target javadoc 1
   * Target javadoc 2
   * <!-- begin-user-doc -->
   * Target user javadoc 3
   * Target user javadoc 4
   * <!-- end-user-doc -->
   */
  @Generated
  @MethodAnnotation (comment = "target annotation method1")
  @MethodAnnotationToBeSwept
  public void method1(int[][] atarget, long btarget)
  {
    // begin-user-code
    System.out.println("Target user code 1");
    // end-user-code    
    System.out.println("Target code 2");
    return id == "target";
  }
  
  /**
   * This method uses Javadoc Generated comment.
   * 
   * Target javadoc 4
   * Target javadoc 5
   * <!-- begin-user-doc -->
   * Target user javadoc 6
   * Target user javadoc 7
   * <!-- end-user-doc -->
   * @generated
   */
  @MethodAnnotation(comment = "target annotation method2")  
  @TargetAnnotation
  public <T> void method2(List<T> l, final HashMap<T, S> h[])
  {
    // begin-user-code
    System.out.println("Target user code 2");
    // end-user-code    
    System.out.println("Target code 3");
    return id == "target";
  }
  
  /**
   * This method is not generated.
   * 
   * Target javadoc 8
   * Target javadoc 9
   * <!-- begin-user-doc -->
   * Target user javadoc 10
   * Target user javadoc 11
   * <!-- end-user-doc -->
   */
  public void method3()
  {
    // begin-user-code
    System.out.println("Target user code 5");
    // end-user-code    
    System.out.println("Target code 6");
    return id == "target";
  }      
  
  @GeneratedNot
  public enum Operation
  {
    @Generated 
    PLUS,
    
    @Generated 
    @AnnotationToBeSwept
    MINUS,
    
    TARGET_ENUM_CONST,
    
    @Generated 
    TIMES,
    
    @Generated 
    DIVIDE;
    
    String s1 = "source"; //$NON-NLS-1$
    
    /**
     * 
     * @param x
     * @param y
     * @return
     */
    @Generated
    double eval(double x, double y)
    {
      switch (this)
      {
        case PLUS:
          return x + y;
        case MINUS:
          return x - y;
        case TIMES:
          return x * y;
        case DIVIDE:
          return x / y;
      }
      throw new AssertionError("Unknown op: " + this);
    }
    
    /**
     * Target javadoc a,b
     * Target javadoc a,b
     * <!-- begin-user-doc -->
     * Target user javadoc a,b
     * Target user javadoc a,b
     * <!-- end-user-doc -->  
     * @generated
     */
    @AnnotationAB (
      description = "target for a,b"
        + " next line"
      /* block 
       * target comment
       */
    )
    private int a, b; // line comment
    
    @Generated
    @MyAnnotation (
      a = 
        1,
        // line comment
      b = 
        3
    )
    protected static int c = 
      "c"
      // target line comment in initializer for c
      + "c", d = "d" +
      // target line comment in initializer for d
      "d", e; // line comment
  }  
  
  /**
   * Target javadoc 1
   * Target javadoc 2
   * <!-- begin-user-doc -->
   * Target user javadoc 3
   * Target user javadoc 4
   * <!-- end-user-doc -->
   * 
   * Describes the Request-For-Enhancement(RFE) that led
   * to the presence of the annotated API element.
   */
  public @interface RequestForEnhancement {
      long    id();
      /**
       * @generated
       */
      String synopsis() default "[target - unassigned]";
      @Generated
      String engineer() default "[target - default]"; 
      String date()    default "[target - unimplemented - not generated]"; 
  }  
  
  /**
   * Generated, must be swept
   * 
   * target javadoc
   */
  @Generated
  private int methodA()
  {
    
  }  
}
