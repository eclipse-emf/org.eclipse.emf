/**
 * <copyright>
 *
 * Copyright (c) 2002-2004 IBM Corporation and others.
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
 * $Id: JETGenerator.java,v 1.1.2.1 2005/06/08 18:27:42 nickb Exp $
 */
package org.eclipse.emf.codegen.jet;



/**
 * The interface that all generators implement.
 *
 */
public interface JETGenerator 
{
  String generate();
}
