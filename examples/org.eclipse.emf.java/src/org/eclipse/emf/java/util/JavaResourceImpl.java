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
 * $Id: JavaResourceImpl.java,v 1.1 2004/04/13 02:50:33 marcelop Exp $
 */
package org.eclipse.emf.java.util;


import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Map;

import org.eclipse.jdt.core.jdom.DOMFactory;
import org.eclipse.jdt.core.jdom.IDOMCompilationUnit;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.impl.ResourceImpl;
import org.eclipse.emf.java.JCompilationUnit;
import org.eclipse.emf.java.JavaFactory;


/**
 */
public class JavaResourceImpl extends ResourceImpl 
{
  /**
   * The factory used to create JDOM.
   */
  protected static DOMFactory jdomFactory = new DOMFactory();

  public JavaResourceImpl(URI uri)
  {
    super(uri);
  }

  protected void doLoad(InputStream inputStream, Map options) throws IOException
  {
    try
    {
      BufferedInputStream bufferedInputStream = new BufferedInputStream(inputStream);
      byte [] input = new byte [bufferedInputStream.available()];
      bufferedInputStream.read(input);
      bufferedInputStream.close();
      IDOMCompilationUnit jdomCompilationUnit = jdomFactory.createCompilationUnit(new String(input), uri.lastSegment());
      JCompilationUnit jCompilationUnit = JavaFactory.eINSTANCE.createJCompilationUnit();
      getContents().add(jCompilationUnit);
      jCompilationUnit.setJNode(jdomCompilationUnit);
      // jCompilationUnit.resolveIdentifiers();
    }
    catch (IOException exception)
    {
      exception.printStackTrace();
    }
  }

  protected void doSave(OutputStream outputStream, Map options) throws IOException
  {
    throw new UnsupportedOperationException();
  }
}
