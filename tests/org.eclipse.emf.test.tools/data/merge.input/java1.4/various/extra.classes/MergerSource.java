/**
* Copyright (c) 2006 IBM Corporation and others.
* All rights reserved.   This program and the accompanying materials
* are made available under the terms of the Eclipse Public License v1.0
* which accompanies this distribution, and is available at
* http://www.eclipse.org/legal/epl-v10.html
* 
* Contributors: 
*   IBM - Initial API and implementation
*
* This is the source header
*/
import b;
import a  ;
import org.eclipse.emf.ecore.util.EObjectCompositeEListNew;

/**
 * Test similar to merge5 and 6. There are extra inner and top-level classes that are brought
 * from source to target.
 */
protected abstract class EchoSoapBindingImpl implements echo.Echo
{
  /**
   * source comment for newMethodA()
   */
  public static void newMethodA()
  {
    // first line comment
    int a;
    if (true)
    {
      a = 0;
    }
  }
  
  // sort the three fields
  /**
   * @ordered
   */
  int b;

  /**
   * source javadoc 
   * for InnerClass1
   *
   */
  public class InnerClass1
  {
    /**
     * 
     * source javadoc line 1
     * source javadoc line 2
     * @ordered
     */
    int b   = 2;
    /**
     * @ordered
     */
    int a 
    = 1;    
  }   
  
  /**
   * @ordered
   */  
  int a;
  /**
   * @ordered
   */  
  int c;

  // remove e
  // move f to be before d
  /**
   * @ordered
   */  
  int f;
  /**
   * @ordered
   */  
  int d;
  
  // remove b()
  // remove c()
  // add a()
  /**
   * source comment for a()
   * this method should be added to target
   */
  void a() 
  {
    // source line comment inside a()
  }

  // remove e()
  void d() {}  
  void f() {}
  
  /**
   * source javadoc 
   * for InnerClass2
   *
   */
  public class InnerClass2
  {
    int a 
      = 1;
    /**
     * 
     * source javadoc line 1
     * source javadoc line 2
     */
    int b   = 2;
  }  
  
  /**
   * source comment for newMethodB()
   */
  public static void newMethodB()
  {
    // first line comment
    int a;
    if (true)
    {
      a = 0;
    }
  }
}

/**
 * source javadoc 
 * for AnotherClass 
 *
 */
public class AnotherClass
{
  int a 
    = 1;
  /**
   * 
   * source javadoc line 1
   * source javadoc line 2
   */
  int b   = 2;
}

/**
 * source comment for TopLevelClass2
 */
class TopLevelClass2
{
}
