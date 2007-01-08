/**
 * <copyright>
 *
 * Copyright (c) 2007 IBM Corporation and others.
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
 * $Id: EMFTestExamplesPlugin.java,v 1.1 2007/01/08 00:22:46 marcelop Exp $
 */
package org.eclipse.emf.test.examples;

import org.eclipse.core.runtime.Plugin;


public class EMFTestExamplesPlugin 
extends Plugin
{
    private static EMFTestExamplesPlugin instance;
    
    public EMFTestExamplesPlugin()
    {
        super();
        instance = this;
    }

    public static EMFTestExamplesPlugin getPlugin()
    {
        return instance;
    }
}
