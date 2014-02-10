/**
 * Copyright (c) 2011-2012 Eclipse contributors and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package org.eclipse.emf.test.ecore.xcore.smoketest;

import com.google.inject.Inject;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.xcore.XcoreInjectorProvider;
import org.eclipse.xtend2.lib.StringConcatenation;
import org.eclipse.xtext.junit4.InjectWith;
import org.eclipse.xtext.junit4.XtextRunner;
import org.eclipse.xtext.junit4.smoketest.AbstractSmokeTest;
import org.eclipse.xtext.junit4.util.ParseHelper;
import org.eclipse.xtext.junit4.validation.ValidationTestHelper;
import org.eclipse.xtext.linking.lazy.LazyLinkingResource;
import org.eclipse.xtext.xbase.lib.CollectionLiterals;
import org.eclipse.xtext.xbase.lib.Exceptions;
import org.eclipse.xtext.xbase.lib.Extension;
import org.junit.runner.RunWith;

@RunWith(XtextRunner.class)
@InjectWith(XcoreInjectorProvider.class)
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
    String _string = _builder.toString();
    return CollectionLiterals.<String>newArrayList(_string);
  }
  
  public void processModel(final String model) {
    try {
      EObject _parse = this.parser.parse(model);
      this.validationTestHelper.validate(_parse);
    } catch (Throwable _e) {
      throw Exceptions.sneakyThrow(_e);
    }
  }
  
  public void processModelWithoutResourceSet(final String model) {
  }
  
  public LazyLinkingResource createResource(final String string) {
    return null;
  }
}
