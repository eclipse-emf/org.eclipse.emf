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

/**
 * Source javadoc 1
 * Source javadoc 2
 * <!-- begin-user-doc -->
 * Source user javadoc 3
 * Source user javadoc 4
 * <!-- end-user-doc -->
 * 
 * @author EMF Team (source)
 * @generated
 */
@ClassAnnotation(comment = "source annotation") // source line comment
@SourceAnnotationToPushWithNoBody
@LastClassAnnotation
public class MergerExample<T> extends ArrayList<A, B> implements Comparable
{
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
  int aa, bb, cc;
  
  /**
   * This method uses Generated annotation.
   * 
   * Source javadoc 1
   * Source javadoc 2
   * <!-- begin-user-doc -->
   * Source user javadoc 3
   * Source user javadoc 4
   * <!-- end-user-doc -->
   */
  @Generated
  @MethodAnnotationToPush
  @MethodAnnotation(comment = "source annotation method1")
  public <S> void method1( final int[] a[] , @ParameterAnnoation  final  long b)
  {
    // begin-user-code
    System.out.println("Source user code 1");
    // end-user-code    
    System.out.println("Source code 2");
    return id == "source";
  }
  
  /**
   * This method uses Javadoc Generated comment.
   * 
   * Source javadoc 4
   * Source javadoc 5
   * <!-- begin-user-doc -->
   * Source user javadoc 6
   * Source user javadoc 7
   * <!-- end-user-doc -->
   * @generated
   */
  @MethodAnnotation(comment = "source annotation method2"
      // source line comment inside annotation
  )  
  @MethodAnnotationToPush
  public void method2(List list, HashMap [ ] hashMap)
  {
    // begin-user-code
    System.out.println("Source user code 3");
    // end-user-code    
    System.out.println("Source code 4");
    return id == "source";
  }
  
  /**
   * This method is not generated in target.
   * 
   * Source javadoc 8
   * Source javadoc 9
   * <!-- begin-user-doc -->
   * Source user javadoc 10
   * Source user javadoc 11
   * <!-- end-user-doc -->
   */
  @Generated
  @SourceAnnotationNotToPush
  public void method3()
  {
    // begin-user-code
    System.out.println("Source user code 5");
    // end-user-code    
    System.out.println("Source code 6");
    return id == "source";
  }  
  
  @Generated
  public enum Operation {
    PLUS ("source - plus"), 
    MINUS, 
    TIMES ("source - multiply"), 
    POWER_SOURCE, 
    DIVIDE ("source - divide") 
    {
      /* source divide const body */
    };

    String s1 = "source"; //$NON-NLS-1$
    String s2 = "source"; //$NON-NLS-1$    
    
    /**
     * Source Javadoc
     * Perform operation
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
        case POWER_SOURCE:
          return Math.pow(x, y);
      }
      throw new AssertionError("Unknown source op: " + this);
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
     * Source user javadoc a
     * Source user javadoc a
     * <!-- end-user-doc -->  
     * @generated
     */
    public @AnnotationA
    @AnnotationAB(
      description = "source"
      // source line comment
    )
    int a;

    @Generated
    private int sourceBetweenAandB = 
      // line comment
      a;
    
    /**
     * Source javadoc b
     * Source javadoc b
     * <!-- begin-user-doc -->
     * Source user javadoc b
     * Source user javadoc b
     * <!-- end-user-doc -->  
     * @generated
     */
    @AnnotationB
    int b;
    
    @Generated
    @SourceAnnotationForCAndD(
      // source comment
        // source comment
      "string value"
    )
    private int c = "source " +
        "string for c" /* comment for initializer of c */, d;
    
    
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
  }  
  
  /**
   * Source javadoc 1
   * Source javadoc 2
   * <!-- begin-user-doc -->
   * Source user javadoc 3
   * Source user javadoc 4
   * <!-- end-user-doc -->
   * 
   * Describes the Request-For-Enhancement(RFE) that led
   * to the presence of the annotated API element.
   */
  @Generated
  private @interface RequestForEnhancement {
      int    id();
      String synopsis();
      String engineer() default "[unassigned - source]"; 
      String date()    default "[unimplemented - source]"; 
  }
}
