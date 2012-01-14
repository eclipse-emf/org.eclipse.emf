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
  // hanging before a
  
  /*
   * hanging block before a
   * 
   */
  
  // after a
  
  // between a and b
  
  // before b
  /**
   * javadoc b
   */
  int b;// after b on the same line
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
  // line after f 
  
  /*
   * hanging block after f
   */   
  
  // line comment before a()
  /**
   * javadoc for a()
   * this commented method should not be removed
   */
//  public void a()
//  {
//    int a = 1;
//  }
  // line comment after a()
  
  // hanging comment between a() and b()
  // line after c 
  
  /*
   * hanging block after c
   */  

  // before d
  /**
   * javadoc d
   */
  int d; // after d on the same line
  // after d
  
  // between d and e
  
  
  // line comment after b()
  
  // hanging comment between b() and c()
  // could be another method here, these comments will be removed
  
  // line comment after c()
  
  // hanging comment between c() and d()
  
  // line comment before d()  
  /**
   * javadoc for d()
   */
  public void d()
  {
    
  } // line comment after d() on the same line
  // line comment after d()
  
  // hanging comment between d() and e()
  
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
