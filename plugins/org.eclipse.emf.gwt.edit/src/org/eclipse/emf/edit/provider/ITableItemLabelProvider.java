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
 * $Id: ITableItemLabelProvider.java,v 1.1 2010/04/28 14:48:40 emerks Exp $
 */
package org.eclipse.emf.edit.provider;



/**
 * This is the interface needed to provide labels for items in a TableViewer.
 * This interface is similar to {@link IItemLabelProvider}, but this will pass additional information, 
 * namely the column index.
 */
public interface ITableItemLabelProvider
{
  /**
   * This does the same thing as ITableLabelProvider.getColumnText.
   */
  public String getColumnText(Object object, int columnIndex);

  /**
   * This does the same thing as ITableLabelProvider.getColumnImage.
   */
  public Object getColumnImage(Object object, int columnIndex);
}
