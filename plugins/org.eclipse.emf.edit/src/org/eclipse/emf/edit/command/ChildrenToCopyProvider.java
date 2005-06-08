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
 * $Id: ChildrenToCopyProvider.java,v 1.1.2.1 2005/06/08 18:27:45 nickb Exp $
 */
package org.eclipse.emf.edit.command;


import java.util.Collection;


/**
 * This interface is implemented by commands that need to support specialized copying of children.
 */
public interface ChildrenToCopyProvider
{
  Collection getChildrenToCopy();
}
