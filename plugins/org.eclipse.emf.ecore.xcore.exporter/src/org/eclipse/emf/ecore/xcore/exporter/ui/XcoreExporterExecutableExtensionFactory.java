/**
 * Copyright (c) 2012 Eclipse contributors and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 */
package org.eclipse.emf.ecore.xcore.exporter.ui;

import org.eclipse.emf.ecore.xcore.exporter.XcoreExporterPlugin;
import org.eclipse.emf.ecore.xcore.ui.XcoreExecutableExtensionFactory;
import org.osgi.framework.Bundle;

public class XcoreExporterExecutableExtensionFactory extends XcoreExecutableExtensionFactory
{
  @Override
  protected Bundle getBundle()
  {
    return XcoreExporterPlugin.getPlugin().getBundle();
  }
}
