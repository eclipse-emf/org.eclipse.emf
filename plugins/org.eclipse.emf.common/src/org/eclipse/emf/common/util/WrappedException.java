/**
 * <copyright>
 *
 * Copyright (c) 2002-2004 IBM Corporation and others.
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
 * $Id: WrappedException.java,v 1.1 2004/03/06 17:31:31 marcelop Exp $
 */
package org.eclipse.emf.common.util;

import java.io.PrintStream;
import java.io.PrintWriter;


/**
 * A runtime exception that wraps another exception.
 */
public class WrappedException extends RuntimeException
{
  /**
   * The exception being wrapped.
   */
  protected Exception wrappedException;

  /**
   * Creates an instance that wraps the exception.
   */
  public WrappedException(Exception exception)
  {
    super(exception.getLocalizedMessage());
    wrappedException = exception;
  }

  /**
   * Creates an instance with it's own message that wraps the exception.
   * @param message the message.
   * @param exception the exception to wrap.
   */
  public WrappedException(String message, Exception exception)
  {
    super(message);
    wrappedException = exception;
  }

  /**
   * Returns the wrapped exception.
   * @return the wrapped exception.
   */
  public Exception exception()
  {
    return wrappedException;
  }

  /**
   * Prints both wrapped exception's stack and this one's.
   */
  public void printStackTrace() 
  {
    System.err.println("Wrapped exception");
    wrappedException.printStackTrace();
    System.err.println("Wrapped by");
    super.printStackTrace();
  }

  /**
   * Prints both wrapped exception's stack and this one's.
   * @param printWriter the print target.
   */
  public void printStackTrace(PrintStream printStream) 
  {
    printStream.println("Wrapped exception");
    wrappedException.printStackTrace(printStream);
    printStream.println("Wrapped by");
    super.printStackTrace(printStream);
  }

  /**
   * Prints both wrapped exception's stack and this one's.
   * @param printWriter the print target.
   */
  public void printStackTrace(PrintWriter printWriter) 
  {
    printWriter.println("Wrapped exception");
    wrappedException.printStackTrace(printWriter);
    printWriter.println("Wrapped by");
    super.printStackTrace(printWriter);
  }
}
