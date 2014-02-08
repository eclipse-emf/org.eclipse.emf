/**
 * Copyright (c) 2011-2012 Eclipse contributors and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package org.eclipse.emf.ecore.xcore.util;


import java.util.Collection;

import org.eclipse.emf.codegen.ecore.genmodel.GenModel;
import org.eclipse.emf.common.util.EList;


public class XcoreGenModelInitializer
{
  private static final String XBASE_LIB = "org.eclipse.xtext.xbase.lib";
  private static final String XCORE_LIB = "org.eclipse.emf.ecore.xcore.lib";

  public Collection<? extends Runnable> initialize(GenModel genModel, boolean handleAnnotations)
  {
    // Disable all generation exception for the model sources by default.
    // This can be overridden by annotations which are processed by the call to initialize.
    //
    genModel.setEditDirectory("");
    genModel.setEditorDirectory("");
    genModel.setTestsDirectory("");

    // Ensure that the Xbase library is on the classpath.
    //
    EList<String> modelPluginVariables = genModel.getModelPluginVariables();
    if (!modelPluginVariables.contains(XBASE_LIB))
    {
      modelPluginVariables.add(XBASE_LIB);
    }

    // Ensure that the Xcore library is on the classpath.
    //
    if (!modelPluginVariables.contains(XCORE_LIB))
    {
      modelPluginVariables.add(XCORE_LIB);
    }

    // Because containment references will be proxy resolving only when explicitly declared that way in the Xcore source, we can default to always support containment proxies.
    //
    genModel.setContainmentProxies(true);

    return genModel.prelinkInitialize(handleAnnotations);
  }
}