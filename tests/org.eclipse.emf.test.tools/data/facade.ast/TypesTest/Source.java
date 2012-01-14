/**
 * Copyright (c) 2004-2006 IBM Corporation and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: 
 *   IBM - Initial API and implementation
 */

  // line comment1 

/**
 * A javadoc
 */

  
package org.eclipse.emf.test.tools.merger;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.eclipse.emf.common.*;
import org.eclipse.emf.common.notify.Notification;
// This is importing the EObjectImpl
import org.eclipse.emf.ecore.impl.EObjectImpl;

/**
 * 
 * AnotherClass javadoc
 * Second line of javadoc
 */
@Version("1.1"
// line comment
// line comment
  )
@Deprecated
class AnotherClass
{
  /**
   * Javadoc for field
   * Another line of javadoc
   */
  int field;
  
  // line comment
  // line comment
  /**
   * Javadoc for methodA
   */
  void methodA()
  {
    
  }
} // line comment at the end of the class

/**
 * This is an example of a fairly complete Java file.
 * Its content is not really important
 * 
 * @author EMF team
 * @generated
 * @generated NOT
 */
public class Example1 extends EObjectImpl
{
  // A public inner class.  It is indented with TABs
  public abstract class InnerClass implements Notification, org.eclipse.emf.common.notify.Notifier 
  {
  
  }
  
  // An initializer.  It is indented with TABs
  {
  System.out.println("A initializer with Comments");
  }
  
  /**
   * public String constant.
   */
  public static final String STR_CONST = "something is ; different \"//; /*;*/" /*inte;res;ting*/ + " !!;;" ;  // = "original text";
    
  /*
   * A private static inner interface
   */
  private static interface InnerInterface extends Notification
  {
    
  }
  
  /**
   * protected static long field.
   * This is a multiline comment.
   */
  protected static long longStatic=1l; //A field
  
  /*
   * package protected boolean field.
   */
  Boolean booleanInstance;
  
  private Map.Entry myEntry;
      
  /**
   * An static initializer
   */
  static
  {
    System.out.println("A initializer with JavaDoc - line1");
    System.out.println("A initializer with JavaDoc - line2");
  }

  /**
   * This is a contructor
   */
  public Example1()
  {
    super();
  }
  
  private Example1(String aString, boolean bol)
  {
    super();
  }

  /**
   * Sets the boolean instance.
   * @param b
   * @generated
   */
  public void setBooleanInstance(Boolean b)
  {
    if (b != null)
    {
      booleanInstance = b;
    }
    else
    {
      booleanInstance = Boolean.FALSE;
    }
  }

  void setBooleanInstance(int a)
  {
    setBooleanInstance(a > 0 ? Boolean.TRUE : Boolean.FALSE);
  }
  
  private int[][] myMatrix =new int[4][5];

  /**
   * Gets the boolean instance.  Now I will ask you to see 
   * something {@link EObjectImpl#eAdapters()}.  
   * @param b
   * @generated NOT
   */
  public Boolean getBooleanInstance() throws Exception
  {
    return booleanInstance == null ? Boolean.FALSE : booleanInstance;
  }
  
  /*
   * This method returns an empty list.
   */
  protected List aMethodWithComments() throws RuntimeException, IllegalAccessError, java.lang.NullPointerException
  {
    return Collections.EMPTY_LIST;
  }
  // This is a simple comment

  //This is another simple comment
  private static long[][] aMethodWithNoComments(int[] a)
  {
     System.out.println("I don't do anything");
     return null;
  }
  
  /**
   * Another initializer with 2 lines
   * of javadoc.
   */
  {
    System.out.println("Another initializer with JavaDoc");
  }  

  public int a = 1, b = 2, c = 3;
  float[][] floatArray[][];
}
