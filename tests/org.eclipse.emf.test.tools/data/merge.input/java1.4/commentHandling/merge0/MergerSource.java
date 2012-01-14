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
 * Test of comments handling when nodes are moved or removed.
 * 
 */
protected abstract class EchoSoapBindingImpl implements echo.Echo
{
  // sort the three fields
  /**
   * @ordered
   */
  int b;
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
}
