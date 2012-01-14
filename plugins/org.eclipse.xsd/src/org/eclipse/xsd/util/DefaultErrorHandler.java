/**
 * Copyright (c) 2002-2005 IBM Corporation and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *   IBM - Initial API and implementation
 */
package org.eclipse.xsd.util;


import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.eclipse.xsd.XSDDiagnostic;
import org.eclipse.xsd.XSDDiagnosticSeverity;
import org.eclipse.xsd.XSDFactory;
import org.eclipse.xsd.XSDPlugin;
import org.xml.sax.ErrorHandler;
import org.xml.sax.SAXParseException;


/**
 * This helper class provides a default implementation for SAX ErrorHandler.
 *
 */
public class DefaultErrorHandler implements ErrorHandler
{

  protected List<XSDDiagnostic> xsdDiagnostics = new ArrayList<XSDDiagnostic>();

  protected XSDFactory xsdFactory = XSDFactory.eINSTANCE;

  public void fatalError(SAXParseException exception)
  {
    XSDDiagnostic xsdDiagnostic = xsdFactory.createXSDDiagnostic();
    xsdDiagnostic.setSeverity(XSDDiagnosticSeverity.FATAL_LITERAL);
    xsdDiagnostic.setMessage(XSDPlugin.INSTANCE.getString("_UI_ParserError_message", new Object []{ exception.getMessage() }));
    xsdDiagnostics.add(xsdDiagnostic);
  }

  public void error(SAXParseException exception)
  {
    XSDDiagnostic xsdDiagnostic = xsdFactory.createXSDDiagnostic();
    xsdDiagnostic.setSeverity(XSDDiagnosticSeverity.ERROR_LITERAL);
    xsdDiagnostic.setMessage("DOM:" + exception.getMessage());
    xsdDiagnostics.add(xsdDiagnostic);
  }

  public void warning(SAXParseException exception)
  {
    XSDDiagnostic xsdDiagnostic = xsdFactory.createXSDDiagnostic();
    xsdDiagnostic.setSeverity(XSDDiagnosticSeverity.WARNING_LITERAL);
    xsdDiagnostic.setMessage("DOM:" + exception.getMessage());
    xsdDiagnostics.add(xsdDiagnostic);
  }

  public Collection<XSDDiagnostic> getDiagnostics()
  {
    return xsdDiagnostics;
  }

}
