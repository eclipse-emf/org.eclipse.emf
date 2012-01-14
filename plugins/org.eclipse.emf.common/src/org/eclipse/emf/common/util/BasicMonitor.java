/**
 * Copyright (c) 2005-2006 IBM Corporation and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *   IBM - Initial API and implementation
 */
package org.eclipse.emf.common.util;

import java.io.PrintStream;

import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IProgressMonitorWithBlocking;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.SubProgressMonitor;


/**
 * The most basic implementation of a task monitor.
 */
public class BasicMonitor implements Monitor 
{
  private boolean isCanceled;
  
  private Diagnostic blockedReason;
  
  public BasicMonitor()
  {
    super();
  }

  public boolean isCanceled()
  {
    return isCanceled;
  }

  public void setCanceled(boolean isCanceled)
  {
    this.isCanceled = isCanceled;
  }
  
  /**
   * Returns the current reason for task being blocked, or <code>null</code>.
   */
  public Diagnostic getBlockedReason()
  {
    return blockedReason;
  }
  
  public void setBlocked(Diagnostic reason)
  {
    this.blockedReason = reason;
  }

  public void clearBlocked()
  {
    this.blockedReason = null;
  }
 
  public void beginTask(String name, int totalWork)
  {
    // Do nothing.
  }
  
  public void setTaskName(String name)
  {
    // Do nothing.
  }

  public void subTask(String name)
  {
    // Do nothing.
  }

  public void worked(int work)
  {
    // Do nothing.
  }
  
  public void internalWorked(double work)
  {
    // Do nothing.
  }
    
  public void done()
  {
    // Do nothing.
  }
  
  /**
   * A simple monitor that delegates to another monitor.
   */
  public static class Delegating
  {
    protected Monitor monitor;
    
    public Delegating(Monitor monitor)
    {
      this.monitor = monitor; 
    }
    
    public boolean isCanceled()
    {
      return monitor.isCanceled();
    }

    public void setCanceled(boolean value)
    {
      monitor.setCanceled(value);
    }

    public void setBlocked(Diagnostic reason)
    {
      monitor.setBlocked(reason);
    }
    
    public void clearBlocked()
    {
      monitor.clearBlocked();
    }

    public void beginTask(String name, int totalWork)
    {
      monitor.beginTask(name, totalWork);
    }

    public void setTaskName(String name)
    {
      monitor.setTaskName(name);
    }

    public void subTask(String name)
    {
      monitor.subTask(name);
    }

    public void worked(int work)
    {
      monitor.worked(work);
    }
    
    public void internalWorked(double work)
    {
      monitor.internalWorked(work);
    }

    public void done()
    {
      monitor.done();
    }
    
    /**
     * A simple monitor that delegates to another monitor, and implements the Eclipse API
     */
    private static class Eclipse extends Delegating implements IProgressMonitorWithBlocking
    {
      public Eclipse(Monitor monitor)
      {
        super(monitor);
      }
      
      public void setBlocked(IStatus reason)
      {
        setBlocked
          (new BasicDiagnostic
              (reason.getSeverity(), 
               reason.getPlugin(), 
               reason.getCode(), 
               reason.getMessage(), 
               null));
      }
  
      public static IProgressMonitorWithBlocking createIProgressMonitorWithBlocking(Monitor monitor)
      {
        if (monitor instanceof IProgressMonitorWithBlocking)
        {
          return (IProgressMonitorWithBlocking)monitor;
        }
        else
        {
          return new Eclipse(monitor);
        }
      }
      
      public static IProgressMonitor createIProgressMonitor(Monitor monitor)
      {
        if (monitor instanceof IProgressMonitor)
        {
          return (IProgressMonitor)monitor;
        }
        else
        {
          return new Eclipse(monitor);
        }
      }
    }
  }
  
  /**
   * Creates a delegating wrapper that allows the monitor to be used 
   * in a context requiring an instance implementing the Eclipse API.
   */
  public static IProgressMonitor toIProgressMonitor(Monitor monitor)
  {
    return Delegating.Eclipse.createIProgressMonitor(monitor);
  }
  
  /**
   * Creates a delegating wrapper that allows the monitor to be used 
   * in a context requiring an instance implementing the Eclipse API.
   */
  public static IProgressMonitorWithBlocking toIProgressMonitorWithBlocking(Monitor monitor)
  {
    return Delegating.Eclipse.createIProgressMonitorWithBlocking(monitor);
  }
  
  /**
   * A simple monitor that delegates to another Eclipse monitor.
   */
  private static class EclipseDelegating implements Monitor
  {
    protected IProgressMonitor progressMonitor;
    protected IProgressMonitorWithBlocking progressMonitorWithBlocking;
    
    public EclipseDelegating(IProgressMonitor progressMonitor)
    {
      this.progressMonitor = progressMonitor; 
      if (progressMonitor instanceof IProgressMonitorWithBlocking)
      {
        this.progressMonitorWithBlocking = (IProgressMonitorWithBlocking)progressMonitor;
      }
    }
    
    public EclipseDelegating(IProgressMonitorWithBlocking progressMonitorWithBlocking)
    {
      this.progressMonitor = progressMonitorWithBlocking; 
      this.progressMonitorWithBlocking = progressMonitorWithBlocking; 
    }
    
    public boolean isCanceled()
    {
      return progressMonitor.isCanceled();
    }

    public void setCanceled(boolean value)
    {
      progressMonitor.setCanceled(value);
    }

    public void setBlocked(Diagnostic reason)
    {
      if (progressMonitorWithBlocking != null)
      {
        progressMonitorWithBlocking.setBlocked(BasicDiagnostic.toIStatus(reason));
      }
    }

    public void clearBlocked()
    {
      if (progressMonitorWithBlocking != null)
      {
        progressMonitorWithBlocking.clearBlocked();
      }
    }

    public void beginTask(String name, int totalWork)
    {
      progressMonitor.beginTask(name, totalWork);
    }
    
    public void setTaskName(String name)
    {
      progressMonitor.setTaskName(name);
    }

    public void subTask(String name)
    {
      progressMonitor.subTask(name);
    }

    public void worked(int work)
    {
      progressMonitor.worked(work);
    }
    
    public void internalWorked(double work)
    {
      progressMonitor.internalWorked(work);
    }

    public void done()
    {
      progressMonitor.done();
    }
  }
  
  /**
   * Creates a delegating wrapper that allows the Eclipse progress monitor to be used 
   * in a context requiring an instance implementing the monitor API.
   */
  public static Monitor toMonitor(IProgressMonitorWithBlocking progressMonitor)
  {
    return new EclipseDelegating(progressMonitor);
  }
  
  /**
   * Creates a delegating wrapper that allows the Eclipse progress monitor to be used 
   * in a context requiring an instance implementing the monitor API.
   */
  public static Monitor toMonitor(IProgressMonitor progressMonitor)
  {
    return new EclipseDelegating(progressMonitor);
  }
  
  /**
   * An Eclipse subprogress monitor that directly implements the monitor API.
   */
  public static class EclipseSubProgress extends SubProgressMonitor implements Monitor
  {
    public EclipseSubProgress(IProgressMonitor monitor, int ticks)
    {
      super(monitor, ticks);
    }

    public EclipseSubProgress(IProgressMonitor monitor, int ticks, int style)
    {
      super(monitor, ticks, style);
    }

    public void setBlocked(Diagnostic reason)
    {
      super.setBlocked(BasicDiagnostic.toIStatus(reason));
    }
  }
  
  /**
   * A simple monitor that prints progress to a print stream.
   */
  public static class Printing extends BasicMonitor
  {
    protected PrintStream printStream;

    public Printing(PrintStream printStream)
    {
      this.printStream = printStream;
    }

    @Override
    public void beginTask(String name, int totalWork)
    {
      if (name != null && name.length() != 0)
      {
        printStream.println(">>> " + name);
      }
    }

    @Override
    public void setTaskName(String name)
    {
      if (name != null && name.length() != 0)
      {
        printStream.println("<>> " + name);
      }
    }

    @Override
    public void subTask(String name)
    {
      if (name != null && name.length() != 0)
      {
        printStream.println(">>  " + name);
      }
    }
    
    @Override
    public void setBlocked(Diagnostic reason)
    {
      super.setBlocked(reason);
      printStream.println("#>  " + reason.getMessage());
    }
  
    @Override
    public void clearBlocked()
    {
      printStream.println("=>  " + getBlockedReason().getMessage());
      super.clearBlocked();
    }
  }
}
