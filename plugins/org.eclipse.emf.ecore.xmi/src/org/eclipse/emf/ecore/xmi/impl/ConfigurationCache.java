/**
 * <copyright>
 *
 * Copyright (c) 2005-2006 IBM Corporation and others.
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
 * $Id: ConfigurationCache.java,v 1.4 2006/12/05 20:23:28 emerks Exp $
 */
package org.eclipse.emf.ecore.xmi.impl;


import java.util.HashMap;
import java.util.Map;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.xmi.impl.XMLSaveImpl.Escape;


/**
 * This class represents a configuration that can be used to enable caching and therefore improves performance 
 * of EMF serialization. 
 * This class is considered an INTERNAL API and might change in the future.
 * @since 2.1.0
 */
public class ConfigurationCache
{
  public static final ConfigurationCache INSTANCE = new ConfigurationCache();

  protected static final int SIZE = 100;
  
  protected Map<EPackage, EObject> documentRoots = new HashMap<EPackage, EObject>();

  protected XMLString printers[] = new XMLString [SIZE];

  protected Escape escapes[] = new Escape [SIZE];

  protected int freePrinterIndex = -1;

  protected int freeEscapeIndex = -1;

  protected int currentSize = SIZE;

  protected ConfigurationCache()
  {
    super();
  }

  protected synchronized XMLString getPrinter()
  {
    if (freePrinterIndex < 0)
    {
      return new XMLString();
    }
    XMLString printer = printers[freePrinterIndex];
    printers[freePrinterIndex--] = null;
    return printer;
  }

  protected synchronized void releasePrinter(XMLString printer)
  {
    ++freePrinterIndex;
    if (printers.length == freePrinterIndex)
    {
      currentSize += SIZE;
      XMLString newarray[] = new XMLString [currentSize];
      System.arraycopy(printers, 0, newarray, 0, printers.length);
      printers = newarray;
    }
    printers[freePrinterIndex] = printer;
  }

  protected synchronized Escape getEscape()
  {
    if (freeEscapeIndex < 0)
    {
      return new Escape();
    }
    Escape escape = escapes[freeEscapeIndex];
    escapes[freeEscapeIndex--] = null;
    return escape;
  }

  protected synchronized void releaseEscape(Escape escape)
  {
    ++freeEscapeIndex;
    if (escapes.length == freeEscapeIndex)
    {
      currentSize += SIZE;
      Escape newarray[] = new Escape [currentSize];
      System.arraycopy(escapes, 0, newarray, 0, escapes.length);
      escapes = newarray;
    }
    escapes[freeEscapeIndex] = escape;
  }

  public synchronized void release()
  {
    freeEscapeIndex = -1;
    freePrinterIndex = -1;
    for (int i = 0; i < printers.length; i++)
    {
      printers[i] = null;
    }
    for (int i = 0; i < escapes.length; i++)
    {
      escapes[i] = null;
    }
  }
  
  public synchronized EClass getDocumentRoot(EPackage ePackage)
  {
    return (EClass)documentRoots.get(ePackage);
  }
  
  public synchronized void putDocumentRoot(EPackage ePackage, EClass documentRoot)
  {
    documentRoots.put(ePackage, documentRoot);
  }

}
