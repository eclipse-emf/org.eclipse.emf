/**
 * <copyright>
 *
 * Copyright (c) 2002-2004 IBM Corporation and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: 
 *   IBM - Initial API and implementation
 *
 * </copyright>
 *
 * $Id: XMIException.java,v 1.1.2.1 2005/06/08 18:27:44 nickb Exp $
 */
package org.eclipse.emf.ecore.xmi;


import java.io.PrintStream;
import java.io.PrintWriter;

import org.eclipse.emf.ecore.resource.Resource;


public class XMIException extends Exception implements Resource.Diagnostic
{
  protected String location;
  protected int line;
  protected int column;
  protected Exception exception;

  public XMIException(String message)
  {
    super(message);
  }

  public XMIException(Exception exception)
  {
    super(exception.getMessage());
    this.exception = exception;
  }

  public XMIException(String message, Exception exception)
  {
    super(message);
    this.exception = exception;
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
    super(message);
    this.exception = exception;
    this.location = location;
    this.line = line;
    this.column = column;
  }

  public XMIException(Exception exception, String location, int line, int column)
  {
    super(exception.getMessage());
    this.exception = exception;
    this.location = location;
    this.line = line;
    this.column = column;
  }

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

  public Exception getWrappedException()
  {
    return exception;
  }

  public void printStackTrace()
  {
    if (exception != null)
    {
      exception.printStackTrace();
    }
    else
    {
      super.printStackTrace();
    }
  }

  public void printStackTrace(PrintStream printStream)
  {
    if (exception != null)
    {
      exception.printStackTrace(printStream);
    }
    else
    {
      super.printStackTrace(printStream);
    }
  }

  public void printStackTrace(PrintWriter printWriter)
  {
    if (exception != null)
    {
      exception.printStackTrace(printWriter);
    }
    else
    {
      super.printStackTrace(printWriter);
    }
  }
}
