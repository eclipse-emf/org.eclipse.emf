/**
 * Copyright (c) 2014 Eclipse contributors and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package org.eclipse.emf.ecore.xcore.scoping;

import org.eclipse.xtext.resource.XtextResource;
import org.eclipse.xtext.xbase.imports.DefaultImportsConfiguration;
import org.eclipse.xtext.xtype.XImportSection;

public class XcoreImportsConfiguration extends DefaultImportsConfiguration
{
  @Override
  public XImportSection getImportSection(XtextResource resource)
  {
    return null;
  }
  
  @Override
  public int getImportSectionOffset(XtextResource resource)
  {
    return 0;
  }
}
