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
 * $Id: DanglingHREFException.java,v 1.1 2004/03/06 17:31:32 marcelop Exp $
 */
package org.eclipse.emf.ecore.xmi;


public class DanglingHREFException extends XMIException {

	/**
	 * Constructor for DanglingHREFException.
	 * @param message
	 * @param location
	 * @param line
	 * @param column
	 */
	public DanglingHREFException(
		String message,
		String location,
		int line,
		int column) {
		super(message, location, line, column);
	}
}
