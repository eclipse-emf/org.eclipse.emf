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
 * $Id: FeatureNotFoundException.java,v 1.1 2004/03/06 17:31:32 marcelop Exp $
 */
package org.eclipse.emf.ecore.xmi;

import org.eclipse.emf.ecore.EObject;

public class FeatureNotFoundException extends XMIException {
    protected String featureName;
    protected EObject object;

	/**
	 * Constructor for FeatureNotFoundException.
	 * @param name
	 * @param object
	 * @param location
	 * @param line
	 * @param column
	 */
	public FeatureNotFoundException(
		String name,
		EObject object,
		String location,
		int line,
		int column) {
		super("Feature '" + name + "' not found.", location, line, column);
		featureName = name;
		this.object = object;
	}
	
	public String getName() {
		return featureName;
	}	
	
	public EObject getObject() {
		return object;
	}	
}
