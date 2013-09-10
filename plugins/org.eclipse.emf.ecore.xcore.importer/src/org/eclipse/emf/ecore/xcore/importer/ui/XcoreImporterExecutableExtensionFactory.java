/**
 * Copyright (c) 2013 Eclipse contributors and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package org.eclipse.emf.ecore.xcore.importer.ui;

import org.eclipse.emf.ecore.xcore.importer.XcoreImporterPlugin;
import org.eclipse.emf.ecore.xcore.ui.XcoreExecutableExtensionFactory;
import org.osgi.framework.Bundle;

public class XcoreImporterExecutableExtensionFactory extends XcoreExecutableExtensionFactory
{
  @Override
  protected Bundle getBundle()
  {
    return XcoreImporterPlugin.getPlugin().getBundle();
  }
}
