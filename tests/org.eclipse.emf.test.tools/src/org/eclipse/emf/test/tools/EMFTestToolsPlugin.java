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
 * $Id: EMFTestToolsPlugin.java,v 1.3.2.2 2005/06/08 18:27:44 nickb Exp $
 */
package org.eclipse.emf.test.tools;

import org.eclipse.core.runtime.Plugin;

public class EMFTestToolsPlugin 
extends Plugin
{
    private static EMFTestToolsPlugin instance;
    
    public EMFTestToolsPlugin()
    {
        super();
        instance = this;
    }

    public static EMFTestToolsPlugin getPlugin()
    {
        return instance;
    }
}
