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
 * $Id: PackageNotFoundException.java,v 1.1 2004/03/06 17:31:32 marcelop Exp $
 */
package org.eclipse.emf.ecore.xmi;

public class PackageNotFoundException extends XMIException {
    protected String uri;

	/**
	 * Constructor for PackageNotFoundException.
	 * @param uri
	 * @param location
	 * @param line
	 * @param column
	 */
	public PackageNotFoundException(
		String uri,
		String location,
		int line,
		int column) {
		super("Package with uri '" + uri + "' not found.", location, line, column);
		this.uri = uri;
	}
	
	public String uri() {
		return uri;
	}	
}
