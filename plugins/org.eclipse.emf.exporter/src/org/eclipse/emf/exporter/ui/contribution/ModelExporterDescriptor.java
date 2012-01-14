/**
 * Copyright (c) 2005 IBM Corporation and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: 
 *   IBM - Initial API and implementation
 */
package org.eclipse.emf.exporter.ui.contribution;

import org.eclipse.ui.IWorkbenchWizard;

import org.eclipse.emf.converter.ui.contribution.ModelConverterDescriptor;


/**
 * It is highly recommended not to implement this interface.  If you need to create
 * instances of a <tt>ModelExporterDescriptor</tt>, use 
 * org.eclipse.emf.exporter.ui.contribution.ModelExporterDescriptorImpl.
 * 
 * @since 2.2.0
 */
public interface ModelExporterDescriptor extends ModelConverterDescriptor
{
  IWorkbenchWizard createWizard();
}
