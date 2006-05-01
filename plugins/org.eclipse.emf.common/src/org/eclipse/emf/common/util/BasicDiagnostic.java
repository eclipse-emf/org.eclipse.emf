/**
 * <copyright>
 *
 * Copyright (c) 2004-2006 IBM Corporation and others.
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
 * $Id: BasicDiagnostic.java,v 1.10 2006/05/01 21:09:48 davidms Exp $
 */
package org.eclipse.emf.common.util;

import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import org.eclipse.core.runtime.IStatus;


/**
 * A basic implementation of a diagostic that that also acts as a chain.
 */
public class BasicDiagnostic implements Diagnostic, DiagnosticChain
{
  /**
   * The severity.
   * @see #getSeverity
   */
  protected int severity;

  /**
   * The message.
   * @see #getMessage
   */
  protected String message;

  /**
   * The message.
   * @see #getMessage
   */
  protected List children;

  /**
   * The data.
   * @see #getData
   */
  protected List data;

  /**
   * The source.
   * @see #getSource
   */
  protected String source;

  /**
   * The code.
   * @see #getCode
   */
  protected int code;
  
  /**
   * Default Constructor (no initialization for local parameters)
   */
  public BasicDiagnostic()
  {
  }

  public BasicDiagnostic(String source, int code, String message, Object[] data) 
  {
    this.source = source;
    this.code = code;
    this.message = message;
    this.data = dataAsList(data);
  }

  public BasicDiagnostic(int severity, String source, int code, String message, Object[] data)
  {
    this(source, code, message, data);
    this.severity = severity;
  }

  public BasicDiagnostic(String source, int code, List children, String message, Object[] data)
  {
    this(source, code, message, data);
    if (children != null)
    {
      for (Iterator i = children.iterator(); i.hasNext(); )
      {
        add((Diagnostic)i.next());
      }
    }
  }

  protected List dataAsList(Object [] data)
  {
    if (data == null)
    {
      return Collections.EMPTY_LIST;
    }
    else
    {
      Object [] copy = new Object [data.length];
      System.arraycopy(data, 0, copy, 0, data.length);
      return new BasicEList.UnmodifiableEList(copy.length, copy);
    }
  }
  
  protected void setSeverity(int severity)
  {
    this.severity = severity;
  }

  public int getSeverity()
  {
    return severity;
  }

  public String getMessage()
  {
    return message;
  }

  public List getData()
  {
    return data;
  }

  public List getChildren()
  {
    return 
      children == null ?
        Collections.EMPTY_LIST :
        Collections.unmodifiableList(children);
  }

  protected void setSource(String source)
  {
    this.source = source;
  }

  public String getSource()
  {
    return source;
  }
  
  protected void setCode(int code)
  {
    this.code = code;
  }

  public int getCode()
  {
    return code;
  }

  public void add(Diagnostic diagnostic)
  {
    if (children == null)
    {
      children = new BasicEList();
    }

    children.add(diagnostic);
    int childSeverity = diagnostic.getSeverity();
    if (childSeverity > getSeverity())
    {
      severity = childSeverity;
    }
  }

  public void addAll(Diagnostic diagnostic)
  {
    for (Iterator i = diagnostic.getChildren().iterator(); i.hasNext(); )
    {
      add((Diagnostic)i.next());
    }
  }

  public void merge(Diagnostic diagnostic)
  {
    if (diagnostic.getChildren().isEmpty())
    {
      add(diagnostic);
    }
    else
    {
      addAll(diagnostic);
    }
  }

  public int recomputeSeverity()
  {
    if (children != null)
    {
      severity = OK;
      for (Iterator i = children.iterator(); i.hasNext(); )
      {
        Diagnostic child = (Diagnostic)i.next();
        int childSeverity = child instanceof BasicDiagnostic ? ((BasicDiagnostic)child).recomputeSeverity() : child.getSeverity();
        if (childSeverity > severity)
        {
          severity = childSeverity;
        }
      }
    }

    return severity;
  }
  
  /**
   * Returns the first throwable object available in the {@link #data} list, 
   * which is set when this diagnostic is instantiated.
   */
  public Throwable getException()
  {
    List data = getData();
    if (data != null)
    {
      for (Iterator i = data.iterator(); i.hasNext(); )
      {
        Object d = i.next();
        if (d instanceof Throwable)
        {
          return (Throwable)d;
        }
      }
    }
    return null;
  }

  private static class Wrapper implements IStatus
  {
    protected static final IStatus [] EMPTY_CHILDREN = new IStatus [0];

    protected Throwable throwable;
    protected Diagnostic diagnostic;
    protected IStatus [] wrappedChildren;

    public Wrapper(Diagnostic diagnostic)
    {
      this.diagnostic = diagnostic;
    }
    
    public Wrapper(DiagnosticException diagnosticException)
    {
      throwable = diagnosticException;
      diagnostic = diagnosticException.getDiagnostic();
    }

    public IStatus[] getChildren()
    {
      if (wrappedChildren == null)
      {
        List children = diagnostic.getChildren();
        if (children.isEmpty())
        {
          wrappedChildren = EMPTY_CHILDREN;
        }
        else
        {
          wrappedChildren = new IStatus [children.size()];
          for (int i = 0; i < wrappedChildren.length; ++i)
          {
            wrappedChildren[i] = toIStatus((Diagnostic)children.get(i));
          }
        }
      }
      return wrappedChildren;
    }
    
    public int getCode()
    {
      return diagnostic.getCode();
    }
    
    public Throwable getException()
    {
      return throwable != null ? throwable : diagnostic.getException();
    }
    
    public String getMessage()
    {
      return diagnostic.getMessage();
    }
    
    public String getPlugin()
    {
      return diagnostic.getSource();
    }
    
    public int getSeverity()
    {
      return diagnostic.getSeverity();
    }
    
    public boolean isMultiStatus()
    {
      return !diagnostic.getChildren().isEmpty();
    }
    
    public boolean isOK()
    {
      return diagnostic.getSeverity() == OK;
    }
    
    public boolean matches(int severityMask)
    {
      return (diagnostic.getSeverity() & severityMask ) != 0;
    }

    public String toString()
    {
      return diagnostic.toString();
    }

    public static IStatus create(Diagnostic diagnostic)
    {
      return new Wrapper(diagnostic);
    }
    
    public static IStatus create(DiagnosticException diagnosticException)
    {
      return new Wrapper(diagnosticException);
    }
  }

  /**
   * Returns the diagnostic viewed as an {@link IStatus}.
   */
  public static IStatus toIStatus(Diagnostic diagnostic)
  {
    return Wrapper.create(diagnostic);
  }
  
  /**
   * Returns the diagnostic exception viewed as an {@link IStatus}.
   */
  public static IStatus toIStatus(DiagnosticException diagnosticException)
  {
    return Wrapper.create(diagnosticException);
  }
  
  /**
   * Returns the throwable viewed as a {@link Diagnostic}.
   * 
   * @param throwable
   * @return {@link Diagnostic}
   */
  public static Diagnostic toDiagnostic(Throwable throwable)
  {
    if (throwable instanceof DiagnosticException)
    {
      return ((DiagnosticException)throwable).getDiagnostic();
    }
    else if (throwable instanceof WrappedException)
    {
      return toDiagnostic(throwable.getCause());
    }
    
    String message = throwable.getClass().getName();
    int index = message.lastIndexOf('.');
    if (index >= 0)
    {
      message = message.substring(index + 1);
    }
    if (throwable.getLocalizedMessage() != null)
    {
      message = message + ": " + throwable.getLocalizedMessage();
    }
    
    BasicDiagnostic basicDiagnostic = 
      new BasicDiagnostic
        (Diagnostic.ERROR,
         "org.eclipse.emf.common", 
         0, 
         message,
         new Object[] { throwable });
    
    while (throwable.getCause() != null && throwable.getCause() != throwable)
    {
      throwable = throwable.getCause();
      basicDiagnostic.add(toDiagnostic(throwable));
    }
    
    return basicDiagnostic;
  }  

  public String toString()
  {
    StringBuffer result = new StringBuffer();
    result.append("Diagnostic ");
    switch (severity)
    {
      case OK: 
      {
        result.append("OK"); 
        break;
      }
      case INFO: 
      {
        result.append("INFO"); 
        break;
      }
      case WARNING: 
      {
        result.append("WARNING"); 
        break;
      }
      case ERROR: 
      {
        result.append("ERROR"); 
        break;
      }
      case CANCEL: 
      {
        result.append("CANCEL"); 
        break;
      }
      default:
      {
        result.append(Integer.toHexString(severity));
        break;
      }
    }

    result.append(" source="); 
    result.append(source);

    result.append(" code="); 
    result.append(code);

    result.append(' ');
    result.append(message);

    if (data != null)
    {
      result.append(" data=");
      result.append(data);
    }
    if (children != null)
    {
      result.append(' ');
      result.append(children);
    }

    return result.toString();
  }
}
