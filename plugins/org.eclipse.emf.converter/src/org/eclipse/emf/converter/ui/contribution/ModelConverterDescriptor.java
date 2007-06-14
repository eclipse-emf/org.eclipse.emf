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
 * $Id: ModelConverterDescriptor.java,v 1.3 2007/06/14 18:32:49 emerks Exp $
 */
package org.eclipse.emf.converter.ui.contribution;

import org.eclipse.swt.graphics.Image;


/**
 * <p>Basic interface to describe the objects responsible to import and export
 * the Genmodel and Ecore models.  Usually these models are contributed through
 * extension points.</p>
 * <p>It is highly recommended not to implement this interface.  Implementations
 * are provided in the importer and exporter plugins.</p>
 * 
 * @since 2.2.0
 */
public interface ModelConverterDescriptor
{
  String getID();

  String getName();

  Image getIcon();
  
  String getDescription();
}
