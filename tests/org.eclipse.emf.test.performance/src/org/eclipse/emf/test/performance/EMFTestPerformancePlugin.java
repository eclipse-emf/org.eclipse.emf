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
 * $Id: EMFTestPerformancePlugin.java,v 1.15 2005/02/18 19:53:45 marcelop Exp $
 */
package org.eclipse.emf.test.performance;

import java.io.PrintWriter;
import java.lang.reflect.Method;

import org.eclipse.core.runtime.Plugin;


public class EMFTestPerformancePlugin extends Plugin
{
  private static class DerbyHelper
  {
    private String home;
    private String user;
    private String pass;
    private Class networkServerControlClass;
    private Object networkServerControl;

    public boolean isAvailable()
    {
      if (networkServerControl == null)
      {
        try
        {
          networkServerControlClass = Class.forName("org.apache.derby.drda.NetworkServerControl");
        }
        catch (Exception e)
        {
          return false;
        }
      }

      return true;
    }

    public boolean isRunning()
    {
      if (networkServerControl == null)
      {
        if (isAvailable())
        {
          try
          {
            networkServerControl = networkServerControlClass.newInstance();
          }
          catch (Exception e)
          {
          }
        }
      }

      if (networkServerControl != null)
      {
        try
        {
          Method sysInfoMethod = networkServerControlClass.getDeclaredMethod("getSysinfo", new Class [0]);
          return sysInfoMethod.invoke(networkServerControl, null) != null;
        }
        catch (Exception e)
        {
        }
      }

      return false;
    }

    public void setHome(String home)
    {
      this.home = home;
    }

    public void setUser(String user)
    {
      this.user = user;
    }

    public void setPass(String pass)
    {
      this.pass = pass;
    }

    //Returns true if the server is running
    public void startIfDown()
    {
      if (!isRunning() && networkServerControl != null)
      {
        Thread thread = new Thread()
          {
            public void run()
            {
              try
              {
                Method startMethod = networkServerControlClass.getDeclaredMethod("start", new Class []{ PrintWriter.class });
                startMethod.invoke(networkServerControl, new Object [1]);
              }
              catch (Exception e)
              {
                System.err.println("*** Unable to start Derby");
                e.printStackTrace();
              }
            }
          };
        thread.start();
      }
    }

    public void writeSystemProperties()
    {
      System.setProperty("derby.system.home", home);
      System.setProperty("test.target", "performance");
      System.setProperty("eclipse.perf.config", "build=" + hashCode());

      Class driverClass = null;
      try
      {
        driverClass = Class.forName("com.ibm.db2.jcc.DB2Driver");
      }
      catch (Throwable t)
      {
      }

      if (driverClass != null)
      {
        System.setProperty("eclipse.perf.dbloc", "net://localhost;dbuser=" + user + ";dbpasswd=" + pass);
      }
      else
      {
        System.setProperty("eclipse.perf.dbloc", home + ";dbuser=" + user + ";dbpasswd=" + pass);
      }
    }

    public void printSystemProperties()
    {
      System.out.println("*** Derby properties");
      System.out.println("derby.system.home: " + System.getProperty("derby.system.home"));
      System.out.println("test.target: " + System.getProperty("test.target"));
      System.out.println("eclipse.perf.config: " + System.getProperty("eclipse.perf.config"));
      System.out.println("eclipse.perf.dbloc: " + System.getProperty("eclipse.perf.dbloc"));
    }
  }

  private static EMFTestPerformancePlugin instance;

  public EMFTestPerformancePlugin()
  {
    super();
    instance = this;

    DerbyHelper derbyHelper = new DerbyHelper();
    if (derbyHelper.isAvailable())
    {
      derbyHelper.setHome("/home/www-data/derby/derbyroot");
      derbyHelper.setUser("app");
      derbyHelper.setPass("app");
      
      derbyHelper.startIfDown();
      derbyHelper.writeSystemProperties();
      derbyHelper.printSystemProperties();
    }
  }

  public static EMFTestPerformancePlugin getPlugin()
  {
    return instance;
  }
}
