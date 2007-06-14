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
 * ModelImporterDescriptor.java,v 1.5 2005/12/14 07:48:48 marcelop Exp
 */
package org.eclipse.emf.importer.ui.contribution;

import java.util.List;

import org.eclipse.emf.converter.ui.contribution.ModelConverterDescriptor;


/**
 * It is highly recommended not to implement this interface.  If you need to create
 * instances of a <tt>ModelImporterDescriptor</tt>, use 
 * org.eclipse.emf.importer.ui.contribution.ModelImporterDescriptorImpl.
 * 
 * @since 2.1.0
 */
public interface ModelImporterDescriptor extends ModelConverterDescriptor
{
  int TYPE_PROJECT = 1;
  int TYPE_FILE = 2;
  int TYPE_DEFAULT = TYPE_PROJECT | TYPE_FILE;

  List<String> getExtensions();

  int getTypes();

  IModelImporterWizard createWizard();
}
