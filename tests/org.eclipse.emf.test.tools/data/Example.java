/**
 * <copyright>
 *
 * Copyright (c) 2004 IBM Corporation and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Common Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/cpl-v10.html
 * 
 * Contributors: 
 *   IBM - Initial API and implementation
 *
 * </copyright>
 *
 * $Id: Example.java,v 1.3.2.1 2005/01/14 22:56:19 nickb Exp $
 */
package org.eclipse.emf.test.tools.merger;

import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.eclipse.emf.common.notify.Notification;
// This is importing the EObjectImpl
import org.eclipse.emf.ecore.impl.EObjectImpl;

class AnotherClass extends EObjectImpl
{
  
}

/**
 * This is an example of a fairly complete Java file.
 * Its content is not really important
 * 
 * @author EMF team
 * @generated
 * @generated NOT
 */
public class Example extends EObjectImpl
{
  // A public inner class.
  public abstract class InnerClass implements Notification, org.eclipse.emf.common.notify.Notifier 
  {
    
  }
  
  /*
   * A private static inner interface
   */
  private static interface InnerInterface extends Notification
  {
    
  }
  
  /**
   * public String constant.
   */
  public static final String STR_CONST = "something";
  
  /**
   * protected static long field.
   * This is a multiline comment.
   */
  protected static long longStatic = 1l;
  
  /*
   * package protected boolean field.
   */
  Boolean booleanInstance;
  
  private Map.Entry myEntry;
  
  private int[][] myMatrix = new int[4][5];
  
  
  /**
   * This is a contructor
   */
  public Example()
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
  
  /**
   * Gets the boolean instance.  Now I will ask you to see 
   * something {@link EObjectImpl#eAdapters()}.  
   * @param b
   * @generated NOT
   */
  public Boolean getBooleanInstance()
  {
    return booleanInstance == null ? Boolean.FALSE : booleanInstance;
  }
  
  /*
   * This method returns an empty list.
   */
  protected List aMethodWithComments()
  {
    return Collections.EMPTY_LIST;
  }

  private void aMethodWithNoComments(int[] a)
  {
     System.out.println("I don't do anything");
  }
}
