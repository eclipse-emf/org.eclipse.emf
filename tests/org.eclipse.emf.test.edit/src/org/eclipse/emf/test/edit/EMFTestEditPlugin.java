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
 * $Id: EMFTestEditPlugin.java,v 1.3 2005/02/22 16:03:44 marcelop Exp $
 */
package org.eclipse.emf.test.edit;

import org.eclipse.core.runtime.Plugin;

public class EMFTestEditPlugin 
extends Plugin
{
    private static EMFTestEditPlugin instance;
    
    public EMFTestEditPlugin()
    {
        super();
        instance = this;
    }

    public static EMFTestEditPlugin getPlugin()
    {
        return instance;
    }
}
