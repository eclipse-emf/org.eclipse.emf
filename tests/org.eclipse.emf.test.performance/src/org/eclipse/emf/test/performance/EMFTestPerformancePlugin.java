/**
 * <copyright>
 *
 * Copyright (c) 2002-2005 IBM Corporation and others.
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
 * $Id: EMFTestPerformancePlugin.java,v 1.9 2005/02/17 18:12:27 marcelop Exp $
 */
package org.eclipse.emf.test.performance;

import java.util.Iterator;
import java.util.Map;

import org.eclipse.core.runtime.Plugin;

public class EMFTestPerformancePlugin 
extends Plugin
{
    private static EMFTestPerformancePlugin instance;
    
    public EMFTestPerformancePlugin()
    {
        super();
        instance = this;
        
        try
        {
          if (Class.forName("org.apache.derby.drda.NetworkServerControl") != null)
          {
            System.out.println("*** Derby is in the classpath.");
            System.setProperty("test.target", "performance");
            System.setProperty("eclipse.perf.dbloc", "net://localhost:1527/;dbuser=app;dbpasswd=app");
            System.setProperty("eclipse.perf.config" ,"build=fromJava");
          }
        }
        catch (ClassNotFoundException e)
        {
        }

        System.out.println("EMFTestPerformancePlugin() - debug start");
        Map properties = System.getProperties(); 
        for (Iterator i = properties.entrySet().iterator(); i.hasNext();)
        {
          Map.Entry entry = (Map.Entry)i.next();
          System.out.println("key:'" + entry.getKey() + "' - value:'" + entry.getValue() + "'");
        }
        System.out.println("EMFTestPerformancePlugin() - debug end");  
    }

    public static EMFTestPerformancePlugin getPlugin()
    {
        return instance;
    }
}
