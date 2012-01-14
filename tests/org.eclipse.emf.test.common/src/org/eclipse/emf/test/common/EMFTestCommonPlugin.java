/**
 * Copyright (c) 2007 IBM Corporation and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *   IBM - Initial API and implementation
 */
package org.eclipse.emf.test.common;

import org.eclipse.core.runtime.Plugin;

public class EMFTestCommonPlugin 
extends Plugin
{
    private static EMFTestCommonPlugin instance;
    
    public EMFTestCommonPlugin()
    {
        super();
        instance = this;
    }

    public static EMFTestCommonPlugin getPlugin()
    {
        return instance;
    }
}
