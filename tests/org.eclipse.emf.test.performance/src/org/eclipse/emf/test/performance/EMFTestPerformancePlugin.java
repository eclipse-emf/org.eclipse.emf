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
 * $Id: EMFTestPerformancePlugin.java,v 1.12 2005/02/18 06:47:12 marcelop Exp $
 */
package org.eclipse.emf.test.performance;

import java.lang.reflect.Method;

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
          final Class derbyClass = Class.forName("org.apache.derby.drda.NetworkServerControl");
          if (derbyClass != null)
          {
            System.out.println("*** Derby is in the classpath.");
            
            Thread thread = new Thread()
            {
              public void run()
              {
                try
                {
                  System.setProperty("derby.system.home", "/home/www-data/derby/derbyroot");
                  Method mainMethod = derbyClass.getDeclaredMethod("main", new Class[]{String[].class});
                  mainMethod.invoke(null, new Object[]{new String[]{"start","-h","0.0.0.0"}});
                }
                catch (Exception e)
                {
                  e.printStackTrace();
                }
              }
            };
            thread.start();
            
            System.setProperty("test.target", "performance");
            System.setProperty("eclipse.perf.dbloc", "net://localhost/;dbuser=app;dbpasswd=app");
            System.setProperty("eclipse.perf.config" ,"build=fromJava");            
          }
        }
        catch (Exception e)
        {
        }
    }

    public static EMFTestPerformancePlugin getPlugin()
    {
        return instance;
    }
}
