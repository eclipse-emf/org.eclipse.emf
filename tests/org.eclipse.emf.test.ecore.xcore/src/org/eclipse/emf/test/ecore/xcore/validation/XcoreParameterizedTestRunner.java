/**
 * Copyright (c) 2013 Eclipse contributors and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package org.eclipse.emf.test.ecore.xcore.validation;


import java.util.List;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.test.ecore.xcore.legacy_xpect_runner.ParameterizedXtextRunner;
import org.eclipse.xtext.junit4.IInjectorProvider;
import org.eclipse.xtext.resource.XtextResource;
import org.eclipse.xtext.resource.XtextResourceFactory;
import org.junit.runners.model.InitializationError;
import org.junit.runners.model.TestClass;

import com.google.common.collect.Lists;
import com.google.inject.Injector;


public class XcoreParameterizedTestRunner extends ParameterizedXtextRunner
{

  public XcoreParameterizedTestRunner(Class<?> testClass) throws InitializationError
  {
    super(testClass);
  }

  @Override
  protected List<ResourceRunner> getChildren()
  {
    if (children == null)
    {
      children = Lists.newArrayList();
      IInjectorProvider injectorProvider = getOrCreateInjectorProvider();
      Injector injector = injectorProvider.getInjector();
      final XtextResourceFactory factory = injector.getInstance(XtextResourceFactory.class);
      for (URI uri : getURIs())
      {
        ResourceRunner child =
          new ResourceRunner()
          {
            @Override
            public void init(TestClass clazz, IInjectorProvider injector, URI uri)
            {
              this.clazz = clazz;
              this.injectorProvider = injector;
              this.resourceSet = injectorProvider.getInjector().getInstance(ResourceSet.class);
              this.resourceSet.getResourceFactoryRegistry().getExtensionToFactoryMap().put("xcore_test", factory);
              this.resource = (XtextResource)resourceSet.getResource(uri, true);
              collectParameters();
              setIndex();
            }
          };
        child.init(getTestClass(), injectorProvider, uri);
        children.add(child);
      }
    }
    return children;
  }
}
