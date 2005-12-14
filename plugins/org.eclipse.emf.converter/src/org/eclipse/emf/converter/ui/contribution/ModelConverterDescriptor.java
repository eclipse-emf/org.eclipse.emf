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
 * $Id: ModelConverterDescriptor.java,v 1.1 2005/12/14 07:45:42 marcelop Exp $
 */
package org.eclipse.emf.converter.ui.contribution;

import org.eclipse.swt.graphics.Image;


/**
 * <p>Basic interface to describe the objects responsible to import and export
 * the Genmodel and Ecore models.  Usually these models are contributed throught
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
