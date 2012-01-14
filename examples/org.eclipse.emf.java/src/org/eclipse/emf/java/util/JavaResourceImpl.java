/**
 * Copyright (c) 2002-2006 IBM Corporation and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *   IBM - Initial API and implementation
 */
package org.eclipse.emf.java.util;


import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Map;

import org.eclipse.emf.codegen.merge.java.JMerger;
import org.eclipse.emf.codegen.merge.java.facade.FacadeHelper;
import org.eclipse.emf.codegen.util.CodeGenUtil;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.impl.ResourceImpl;
import org.eclipse.emf.java.JCompilationUnit;
import org.eclipse.emf.java.JavaFactory;


public class JavaResourceImpl extends ResourceImpl 
{
  public JavaResourceImpl(URI uri)
  {
    super(uri);
  }

  @Override
  protected void doLoad(InputStream inputStream, Map<?, ?> options) throws IOException
  {
    try
    {
      BufferedInputStream bufferedInputStream = new BufferedInputStream(inputStream);
      byte [] input = new byte [bufferedInputStream.available()];
      bufferedInputStream.read(input);
      bufferedInputStream.close();
      FacadeHelper facadeHelper = CodeGenUtil.instantiateFacadeHelper(JMerger.DEFAULT_FACADE_HELPER_CLASS);
      org.eclipse.emf.codegen.merge.java.facade.JCompilationUnit jCompilationUnit = facadeHelper.createCompilationUnit(uri.lastSegment(), new String(input));
      JCompilationUnit compilationUnit = JavaFactory.eINSTANCE.createJCompilationUnit();
      getContents().add(compilationUnit);
      compilationUnit.setJNode(jCompilationUnit);
    }
    catch (IOException exception)
    {
      exception.printStackTrace();
    }
  }

  @Override
  protected void doSave(OutputStream outputStream, Map<?, ?> options) throws IOException
  {
    throw new UnsupportedOperationException();
  }
}
