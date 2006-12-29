/**
 * <copyright>
 *
 * Copyright (c) 2005-2006 IBM Corporation and others.
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
 * $Id: XMIExporterWizard.java,v 1.3 2006/12/29 18:18:32 marcelop Exp $
 */
package org.eclipse.xsd.ecore.exporter.ui;

import org.eclipse.emf.converter.ModelConverter;
import org.eclipse.xsd.ecore.exporter.XMIExporter;

/**
 * @since 2.2.0
 */
public class XMIExporterWizard extends XSDExporterWizard
{
  public XMIExporterWizard()
  {
    super();
  }
  
  @Override
  protected ModelConverter createModelConverter()
  {
    return new XMIExporter();
  }
}
