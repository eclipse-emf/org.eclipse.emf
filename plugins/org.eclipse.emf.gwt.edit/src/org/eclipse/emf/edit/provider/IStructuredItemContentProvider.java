/**
 * <copyright> 
 *
 * Copyright (c) 2002-2006 IBM Corporation and others.
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
 * $Id: IStructuredItemContentProvider.java,v 1.1 2010/04/28 14:48:39 emerks Exp $
 */
package org.eclipse.emf.edit.provider;


import java.util.Collection;


/**
 * This is the interface needed to populate 
 * the top level items in a TreeViewer, 
 * the items of a ListViewer, 
 * or the rows of a TableViewer. 
 */
public interface IStructuredItemContentProvider
{
  /**
   * This does the same thing as IStructuredContentProvider.getElements.
   */
  public Collection<?> getElements(Object object);
}
