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

import source.ClassA;
import source.ClassB;
import target.ClassA;
import target.ClassB;

/**
 * Source javadoc 1
 * Source javadoc 2
 * <!-- begin-user-doc -->
 * Target user javadoc 3
 * Target user javadoc 4
 * <!-- end-user-doc -->
 * 
 * @author EMF Team (source)
 * @generated
 */
@ClassAnnotation
(
  comment = "target annotation, to be overwritten"
) // target line comment after @ClassAnnotation
@SourceAnnotationToPushWithNoBody
@ClassAnnotationToBeSwept // line comment
(
  "target argument"
) // line comment after @ClassAnnotationToBeSwept
@LastClassAnnotation
public class MergerExample<T> extends ArrayList<A, B> implements Comparable
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
  
  /**
   * Source comment for aa, bb, cc
   * <!-- begin-user-doc -->
   * Source user comment for aa, bb, cc
   * <!-- end-user-doc -->
   */
  @Generated
  @FieldAnnotation(comment = "source annotation for aa,bb,cc")
  @SourceFieldAnnotation(comment = "source sourceannotation for aa,bb,cc")
  int cc;

  String s1 = "source"; //$NON-NLS-1$
  
  String s2 = "source"; //$NON-NLS-1$

  /**
   * Source comment for aa, bb, cc
   * <!-- begin-user-doc -->
   * Source user comment for aa, bb, cc
   * <!-- end-user-doc -->
   */
  @Generated
  @FieldAnnotation(comment = "source annotation for aa,bb,cc")
  @SourceFieldAnnotation(comment = "source sourceannotation for aa,bb,cc")
  int aa;

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
   * Source javadoc 4
   * Source javadoc 5
   * <!-- begin-user-doc -->
   * Target user javadoc 6
   * Target user javadoc 7
   * <!-- end-user-doc -->
   * @generated
   */
  @MethodAnnotation(comment = "target annotation method2")  
  @MethodAnnotationToPush
  @TargetAnnotation
  public void method2(List list, HashMap [ ] hashMap)
  {
    // begin-user-code
    System.out.println("Source user code 3");
    // end-user-code    
    System.out.println("Source code 4");
    return id == "source";
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
    
    POWER_SOURCE, @Generated 
    DIVIDE;
    
    String s1 = "source"; //$NON-NLS-1$
    
    String s2 = "source"; //$NON-NLS-1$    

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
     * Source Javadoc
     * Description of the operation
     */
    @Generated
    String description = null;

    /**
     * Source javadoc a
     * Source javadoc a
     * <!-- begin-user-doc -->
     * Target user javadoc a,b
     * Target user javadoc a,b
     * <!-- end-user-doc -->  
     * @generated
     */
    @AnnotationA
    @AnnotationAB (
      description = "target for a,b"
        + " next line"
      /* block 
       * target comment
       */
    )
    public int a;

    @Generated
    private int sourceBetweenAandB = 
      // line comment
      a;

    /**
     * Source javadoc b
     * Source javadoc b
     * <!-- begin-user-doc -->
     * Target user javadoc a,b
     * Target user javadoc a,b
     * <!-- end-user-doc -->  
     * @generated
     */
    @AnnotationB
    @AnnotationAB (
      description = "target for a,b"
        + " next line"
      /* block 
       * target comment
       */
    ) int b; // line comment
    
    /**
     * Source Javadoc
     * Default constructor
     */
    @Generated
    Operation()
    {
    }

    /**
     * Source Javadoc
     * @param description
     */
    @Generated
    Operation(String description)
    {
      this.description = description;
    }

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
      String synopsis();
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
