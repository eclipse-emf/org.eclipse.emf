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
 * $Id: EMFTestXMLPlugin.java,v 1.2 2005/01/05 20:42:51 marcelop Exp $
 */
package org.eclipse.emf.test.xml;

import org.eclipse.core.runtime.Plugin;

public class EMFTestXMLPlugin 
extends Plugin
{
    private static EMFTestXMLPlugin instance;
    
    public EMFTestXMLPlugin()
    {
        super();
        instance = this;
    }

    public static EMFTestXMLPlugin getPlugin()
    {
        return instance;
    }
}
