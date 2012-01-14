/**
 * Copyright (c) 2002-2006 IBM Corporation and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: 
 *   IBM - Initial API and implementation
 */
package org.eclipse.emf.ecore.xmi;


import org.eclipse.emf.ecore.resource.Resource;


public class XMIException extends Exception implements Resource.Diagnostic
{
  private static final long serialVersionUID = 1L;

  protected String location;
  protected int line;
  protected int column;

  public XMIException(String message)
  {
    super(message);
  }

  public XMIException(Exception exception)
  {
    super(exception);
  }

  public XMIException(String message, Exception exception)
  {
    super(message, exception);
  }

  public XMIException(String message, String location, int line, int column)
  {
    super(message);
    this.location = location;
    this.line = line;
    this.column = column;
  }

  public XMIException(String message, Exception exception, String location, int line, int column)
  {
    super(message, exception);
    this.location = location;
    this.line = line;
    this.column = column;
  }

  public XMIException(Exception exception, String location, int line, int column)
  {
    super(exception);
    this.location = location;
    this.line = line;
    this.column = column;
  }

  @Override
  public String getMessage()
  {
    String result = super.getMessage();
    if (line != 0)
    {
      result +=  " (" + location + ", " + line + ", " + column + ")";
    }
    return result;
  }

  public String getLocation()
  {
    return location;
  }

  public int getLine()
  {
    return line;
  }

  public int getColumn()
  {
    return column;
  }

  /**
   * @deprecated in 2.2.  Use {@link #getCause()} instead.  Given that 
   * the constructors of this class take {@link Exception}s as arguments, it is 
   * save to do this cast <pre>(Exception)getCause()</pre>. 
   */
  @Deprecated
  public Exception getWrappedException()
  {
    return (Exception)getCause();
  }
}
