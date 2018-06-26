/**
 * Copyright (c) 2011-2012 Eclipse contributors and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 */
package org.eclipse.emf.test.ecore.xcore.generator;

import com.google.inject.Inject;
import org.eclipse.emf.ecore.xcore.XPackage;
import org.eclipse.emf.ecore.xcore.generator.XcoreGenerator;
import org.eclipse.emf.test.ecore.xcore.XcoreStandaloneInjectorProvider;
import org.eclipse.xtend2.lib.StringConcatenation;
import org.eclipse.xtext.generator.IFileSystemAccess;
import org.eclipse.xtext.generator.InMemoryFileSystemAccess;
import org.eclipse.xtext.testing.InjectWith;
import org.eclipse.xtext.testing.XtextRunner;
import org.eclipse.xtext.testing.util.ParseHelper;
import org.eclipse.xtext.xbase.lib.Exceptions;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(XtextRunner.class)
@InjectWith(XcoreStandaloneInjectorProvider.class)
@SuppressWarnings("all")
public class GeneratorTest {
  @Inject
  private ParseHelper<XPackage> parser;
  
  @Inject
  private XcoreGenerator xcoreGenerator;
  
  @Test
  public void testGenerator() {
    try {
      StringConcatenation _builder = new StringConcatenation();
      _builder.append("@GenModel(modelDirectory=\"Nowhere\")");
      _builder.newLine();
      _builder.append("package test");
      _builder.newLine();
      _builder.append("class X {}");
      _builder.newLine();
      final XPackage xPackage = this.parser.parse(_builder);
      final InMemoryFileSystemAccess inmemFsa = new InMemoryFileSystemAccess();
      this.xcoreGenerator.doGenerate(xPackage.eResource(), inmemFsa);
      Assert.assertEquals(inmemFsa.getAllFiles().keySet().toString(), 8, inmemFsa.getAllFiles().size());
      Assert.assertNotNull(inmemFsa.getAllFiles().get((IFileSystemAccess.DEFAULT_OUTPUT + "/test/util/TestSwitch.java")));
    } catch (Throwable _e) {
      throw Exceptions.sneakyThrow(_e);
    }
  }
}
