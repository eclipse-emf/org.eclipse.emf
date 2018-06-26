/**
 * Copyright (c) 2011-2012 Eclipse contributors and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 */
package org.eclipse.emf.test.ecore.xcore.smoketest;

import com.google.inject.Inject;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.test.ecore.xcore.XcoreStandaloneInjectorProvider;
import org.eclipse.xtend2.lib.StringConcatenation;
import org.eclipse.xtext.linking.lazy.LazyLinkingResource;
import org.eclipse.xtext.testing.InjectWith;
import org.eclipse.xtext.testing.XtextRunner;
import org.eclipse.xtext.testing.smoketest.AbstractSmokeTest;
import org.eclipse.xtext.testing.util.ParseHelper;
import org.eclipse.xtext.testing.validation.ValidationTestHelper;
import org.eclipse.xtext.xbase.lib.CollectionLiterals;
import org.eclipse.xtext.xbase.lib.Exceptions;
import org.eclipse.xtext.xbase.lib.Extension;
import org.junit.runner.RunWith;

@RunWith(XtextRunner.class)
@InjectWith(XcoreStandaloneInjectorProvider.class)
@SuppressWarnings("all")
public class XcoreSmokeTest extends AbstractSmokeTest {
  @Inject
  @Extension
  private ParseHelper<EObject> parser;
  
  @Inject
  @Extension
  private ValidationTestHelper validationTestHelper;
  
  /**
   * The models don't neccessarily need to be proper Xcore models.
   */
  @Override
  public Iterable<String> getSmokeTestModels() {
    StringConcatenation _builder = new StringConcatenation();
    _builder.append("package foo ");
    _builder.newLine();
    _builder.append("import org.eclipse.emf.ecore.EObject");
    _builder.newLine();
    _builder.append("import org.eclipse.emf.ecore.EEList");
    _builder.newLine();
    _builder.append("class Stuff {");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("String x");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("contains OtherStuff otherStuff opposite parent keys x");
    _builder.newLine();
    _builder.append("}");
    _builder.newLine();
    _builder.append("class OtherStuff {");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("container Stuff parent opposite otherStuff");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("int x");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("transient EEList<Integer> ints");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("refers Stuff stuff ");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("refers EObject data");
    _builder.newLine();
    _builder.append("\t");
    _builder.append("op int bar() { x + 1 }");
    _builder.newLine();
    _builder.append("}");
    _builder.newLine();
    _builder.append("type ListOfStringArray wraps java.util.List<String[]>");
    _builder.newLine();
    return CollectionLiterals.<String>newArrayList(_builder.toString());
  }
  
  @Override
  public void processModel(final String model) {
    try {
      this.validationTestHelper.validate(this.parser.parse(model));
    } catch (Throwable _e) {
      throw Exceptions.sneakyThrow(_e);
    }
  }
  
  @Override
  public void processModelWithoutResourceSet(final String model) {
  }
  
  @Override
  public LazyLinkingResource createResource(final String string) {
    return null;
  }
}
