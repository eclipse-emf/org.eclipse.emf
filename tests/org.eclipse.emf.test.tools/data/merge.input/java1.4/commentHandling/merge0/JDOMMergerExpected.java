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
* This is the target header
*/
/*
 * target comment before import that will removed
 */
import b;
import a  ;
import org.eclipse.emf.ecore.util.EObjectCompositeEListNew;

/**
 * Test of comments handling when nodes are moved or removed.
 * 
 * @implements Comparable 
 *
 */
protected abstract class EchoSoapBindingImpl implements echo.Echo, Comparable
{
  // after a
  
  // between a and b
  
  // before b
  /**
   * javadoc b
   */
  int b;// after b on the same line
  // hanging before a
  
  /*
   * hanging block before a
   * 
   */
  
  // before a
  /**
   * javadoc a
   */
  int a; // after a on the same line
  // after b
  
  // between b and c
  
  // before c
  /**
   * javadoc c
   */
  int c;// after c on the same line
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

  // after e
  
  // between e and f
  
  // before f
  /**
   * javadoc f
   */
  int f;// after f on the same line
  // line after c 
  
  /*
   * hanging block after c
   */  

  // before d
  /**
   * javadoc d
   */
  int d; // after d on the same line
  // line comment after c()
  
  // hanging comment between c() and d()
  
  // line comment before d()  
  /**
   * javadoc for d()
   */
  public void d()
  {
    
  } // line comment after d() on the same line
  // line comment after e()
  
  // hanging comment between e() and f()
  
  // line comment before f()  
  /**
   * javadoc for f()
   */
  public void f()
  {
    
  } // line comment after f() on the same line
  // line comment after f()
  
  // hanging comment after f() 
}
