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
 * $Id: DiagnosticException.java,v 1.2 2005/11/22 20:26:13 marcelop Exp $
 */
package org.eclipse.emf.common.util;

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
    super(diagnostic.getMessage(), diagnostic.getException());
    this.diagnostic = diagnostic;
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
