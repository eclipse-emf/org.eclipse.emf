/**
 * <copyright>
 *
 * Copyright (c) 2004 IBM Corporation and others.
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
 * $Id: IViewerProvider.java,v 1.1 2010/03/11 02:30:05 khussey Exp $
 */
package org.eclipse.emf.common.ui.viewer;

import org.eclipse.jface.viewers.Viewer;

/**
 * Interface common to all objects that provide a viewer.
 */
public interface IViewerProvider
{
  Viewer getViewer();
}
