/**
 * Copyright (c) 2008-2010 IBM Corporation and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: 
 *   IBM - Initial API and implementation
 */
package org.eclipse.emf.edit.provider;



/**
 * This is the interface needed to provide color for items in a TableViewer.
 * This interface is similar to {@link IItemFontProvider}, but this will pass additional information, 
 * namely the column index.
 */
public interface ITableItemFontProvider
{
  /**
   * This does the same thing as ITableFontProvider.getFont.
   */
  public Object getFont(Object object, int columnIndex);
}
