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
 * $Id: EMFTestSDOPlugin.java,v 1.5 2005/06/08 06:17:25 nickb Exp $
 */
package org.eclipse.emf.test.sdo;

import org.eclipse.core.runtime.Plugin;

public class EMFTestSDOPlugin 
extends Plugin
{
    private static EMFTestSDOPlugin instance;
    
    public EMFTestSDOPlugin()
    {
        super();
        instance = this;
    }

    public static EMFTestSDOPlugin getPlugin()
    {
        return instance;
    }
}