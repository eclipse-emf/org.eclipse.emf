/**
 * <copyright>
 *
 * Copyright (c) 2005 IBM Corporation and others.
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
 * $Id: DiagnosticException.java,v 1.1 2005/11/18 12:01:42 emerks Exp $
 */
package org.eclipse.emf.common.util;

import java.util.Iterator;

import org.eclipse.core.runtime.CoreException;

/**
 * A checked exception representing a diagnosed failure.
 * <p>
 * Diagnostic exceptions contain a diagnostic describing the cause of the exception.
 * </p>
 * @see Diagnostic
 */
public class DiagnosticException extends Exception 
{
  private Diagnostic diagnostic;

  public DiagnosticException(Diagnostic diagnostic) 
  {
    super(diagnostic.getMessage(), getException(diagnostic));
    this.diagnostic = diagnostic;
  }
  
  private static Throwable getException(Diagnostic diagnostic)
  {
    for (Iterator i = diagnostic.getData().iterator(); i.hasNext(); )
    {
      Object data = i.next();
      if (data instanceof Throwable)
      {
        return (Throwable)data;
      }
    }
    return null;
  }

  public final Diagnostic getDiagnostic() 
  {
    return diagnostic;
  }
  
  public static CoreException toCoreException(DiagnosticException exception)
  {
    return new CoreException(BasicDiagnostic.toIStatus(exception));
  }
}
