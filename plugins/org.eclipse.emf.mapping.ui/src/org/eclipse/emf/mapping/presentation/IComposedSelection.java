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
 * $Id: IComposedSelection.java,v 1.2 2005/06/08 06:23:57 nickb Exp $
 */
package org.eclipse.emf.mapping.presentation;


import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;


public interface IComposedSelection extends ISelection
{
  ISelection getSelection();
  ISelection [] getSelections();
  IStructuredSelection getCombinedSelection();
}
