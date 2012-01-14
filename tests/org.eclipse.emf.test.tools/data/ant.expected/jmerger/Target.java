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
public class MergerExample
{
  /**
   * Target javadoc 5
   * @generated
   */
  private int a1 = 1;
  
  /**
   * Target javadoc 6
   * @generated
   */
  int a2 = 2;

  /**
   * Target javadoc 7
   * @generated
   */
  protected int a3 = 3;

  /**
   * Target javadoc 8
   * <!-- begin-user-doc -->
   * Target user javadoc 9 
   * <!-- end-user-doc -->
   * @generated
   */
  public int a4 = 4;

  /**
   * Source javadoc 9
   * <!-- begin-user-doc -->
   * Target user javadoc 11
   * <!-- end-user-doc -->
   * @generated
   */
  protected int a5 = 5;
  
  
  
  
  
  /**
   * Source javadoc 11
   * <!-- begin-user-doc -->
   * Target user javadoc 13
   * <!-- end-user-doc -->
   * @generated
   */
  protected String id = "source";

  /**
   * Source javadoc 12
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected boolean newAttribute = true;

/**
   * Source javadoc 13
   * <!-- begin-user-doc -->
   * Target user javadoc 16
   * Target user javadoc 17
   * <!-- end-user-doc -->
   * Source javadoc 15
   * @return source
   * @generated
   */
  public boolean isID()
  {
    // begin-user-code
    System.out.println("Target user code 2");
    // end-user-code
    System.out.println("Source code 2");
    return id == "source";
  }

  /**
   * Target javadoc 19
   * <!-- begin-user-doc -->
   * Target user javadoc 20
   * Target user javadoc 21
   * <!-- end-user-doc -->
   * Target javadoc 22
   * @param target
   * @generated NOT
   */
  public void setID(boolean value)
  {
     id = value ? "target" : "source";
     System.out.println("Target code 4");
  }
  
  /**
   * Target javadoc 23
   * <!-- begin-user-doc -->
   * Target user javadoc 24
   * <!-- end-user-doc -->
   * Target javadoc 25
   * @param target
   */  
  protected Object sourceRequired()
  {
    System.out.println("Source code 4");
  }    
  
  /**
   * Target javadoc 26
   * @generated
   */
  private void methodWithTargetWithoutUserSections()
  {
    System.out.println("Target code 6");
  }
  
  /**
   * Target javadoc 27
   * <!-- begin-user-doc -->
   * Target user javadoc 28
   * <!-- end-user-doc -->
   * Target javadoc 29
   * @generated
   */
  private void methodWithSourceWithoutUserSections()
  {
    // begin-user-code
    System.out.println("Target user code 7");
    // end-user-code        
    System.out.println("Target code 8");
  }
  
  /**
   * Target javadoc 30
   * @generated
   */
  private void methodWithoutUserSections()
  {
    System.out.println("Target code 9");
  }
}
