/**
 * Copyright (c) 2019 Eclipse contributors and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 */
package org.eclipse.emf.test.tools.codegen;


import static org.junit.Assert.assertEquals;

import org.eclipse.emf.codegen.ecore.genmodel.GenModel;
import org.eclipse.emf.codegen.ecore.genmodel.GenModelFactory;
import org.eclipse.emf.codegen.ecore.genmodel.impl.GenOperationImpl;
import org.eclipse.emf.codegen.util.ImportManager;
import org.junit.Before;
import org.junit.Test;


public class ImportSubstitutionTest
{
  private GenOperationImpl genOperation;

  private StringBuilder expectations;

  private StringBuilder results;

  private boolean failFast;

  @Before
  public void setUp() throws Exception
  {
    final GenModel genModel = GenModelFactory.eINSTANCE.createGenModel();
    genOperation = new GenOperationImpl()
      {
        @Override
        public GenModel getGenModel()
        {
          return genModel;
        }
      };
    genModel.setImportManager(new ImportManager("a.b.c", "Main"));
    expectations = new StringBuilder();
    results = new StringBuilder();
  }

  protected String process(String body)
  {
    genOperation.setBody(body);
    String result = genOperation.getBody("");
    if (Boolean.FALSE)
    {
      System.out.println(body);
      System.out.println(" -> " + result);
    }
    return result;
  }

  protected void generate(String body, String expected)
  {
    String actual = process(body);
    expectations.append(body).append("\t->\t").append(expected).append('\n');
    results.append(body).append("\t->\t").append(actual).append('\n');
    if (failFast)
    {
      assertEquals("Unexpected results generated for " + body, expectations.toString(), results.toString());
      expectations.setLength(0);
      results.setLength(0);
    }
  }

  public void test()
  {
    // Empty balanced content maps to the empty string.
    generate(
      "<%%>", // ->
      "");

    // A <%<%> is an escape for producing <.
    generate(
      "<%<%>", // ->
      "<");

    // A <%%%> is an escape for producing %
    generate(
      "<%%%>", // ->
      "%");

    // One way of escaping to produce <%.
    generate(
      "<%<%>%", // ->
      "<%");

    // Another way of escaping to produce <%.
    generate(
      "<%<%><%%%>", // ->
      "<%");

    // A wway of escaping to product <%%> literally.
    generate(
      "<%<%><%%%>%>", // ->
      "<%%>");

    // Leading unbalanced <% are ignored/literal.
    // 
    generate(
      "<%<%>%>", // ->
      "<%>");

    // Trailing unbalanced %> are ignored/literal.
    // 
    generate(
      "<%%>%>", // ->
      "%>");

    // Simple use case for importing a qualified name.
    //
    generate(
      "<%java.lang.Integer%>", // ->
      "Integer");

    // Multiple expressions may occur in a body of processed text.
    //
    generate(
      "<%java.lang.Integer%> <%java.lang.Integer%>", // ->
      "Integer Integer");

    // Since a.b.c.Main is imported, a conflicting importing will not be shortened.
    //
    generate(
      "<%x.y.z.Main%>", // ->
      "x.y.z.Main");

    // Using a nested expression to specify an annotation.
    //
    generate(
      "<%java.lang.<%@<%a.b.c.Foo %>%>Integer%>", // ->
      "@Foo Integer");

    // Using a nested expression to specify multiple annotations.
    //
    generate(
      "<%java.lang.<%@<%a.b.c.Foo%> @<%a.b.c.Goo %>%>Integer%>", // ->
      "@Foo @Goo Integer");

    // Using a nested expression to specify two annotations.
    //
    generate(
      "<%java.lang.<%@<%a.b.c.Foo%> @<%a.b.c.Goo %>%>Integer%>", // ->
      "@Foo @Goo Integer");

    // Using a nested expression to specify multiple annotations.
    //
    generate(
      "<%x.y.z.<%@<%x.b.P%> @<%x.b.Q%> @<%a.b.c.R$D%> %>T%>", // ->
      "@P @Q @R.D T");

    // There can be only one nested expression, so multiple expressions will produce seemingly unpredictable results.
    //
    generate(
      "<%x.y.z.<%@<%x.b.P%>%> <%@<%x.b.Q%>%> <%@<%a.b.c.R$D%>%> T%>", // ->
      "@P%> @x.b.Q <%@<%a.b.c.R$D%> T");

    // This is the use case example from OCl that motivated this support for nest import manager support for annotations.
    //
    generate(
      "<%org.eclipse.ocl.pivot.ids.<%@<%org.eclipse.jdt.annotation.NonNull%> %>IdResolver%>", // ->
      "@NonNull IdResolver");

    // Using a complex nested expression for specifying annotations even on template arguments.
    //
    generate(
      "<%java.util.Map$<%@<%org.eclipse.jdt.annotation.NonNull%> %>Entry%><<%java.lang.<%@<%org.eclipse.jdt.annotation.NonNull%>() %>String%>, <%java.lang.<%@<%org.eclipse.jdt.annotation.NonNull%>() %>Integer%>>", // ->
      "Map.@NonNull Entry<@NonNull() String, @NonNull() Integer>");

    // Using a complex nested expression for specifying annotations on nested class references and even on template arguments.
    //
    generate(
      "<%javax.util.Map$<%@<%org.eclipse.jdt.annotation.NonNull%> %>Entry%><<%java.lang.<%@<%org.eclipse.jdt.annotation.NonNull%>() %>String%>, <%java.lang.<%@<%org.eclipse.jdt.annotation.NonNull%>() %>Integer%>>", // ->
      "javax.util.Map.@NonNull Entry<@NonNull() String, @NonNull() Integer>");

    // Complex expressions properly handle the case where a nested class is directly imported.
    //
    generate(
      "<%javax.util.Map.<%@<%org.eclipse.jdt.annotation.NonNull%> %>Entry%><<%java.lang.String%>, <%java.lang.Integer%>>", // ->
      "@NonNull Entry<String, Integer>");

    // Complex expressions properly handle the case where a nested class is directly imported.
    //
    generate(
      "<%javax.utilx.Map.<%@<%org.eclipse.jdt.annotation.NonNull%> %>Entry%><<%java.lang.String%>, <%java.lang.Integer%>>", // ->
      "javax.utilx.Map.@NonNull Entry<String, Integer>");

    // Array types can be annotated as well.
    //
    generate(
      "<%java.lang.<%@<%foo.bar.Annotation%> %>Integer%>@<%foo.bar.Annotation%> []@<%foo.baz.Annotation%>[]", // ->
      "@Annotation Integer@Annotation []@foo.baz.Annotation[]");

    // Complex annotation initialization expressions are possible.
    //
    generate(
      "<%java.lang.<%@<%foo.bar.FancyAnnotation%>(fancy=<%a.b.c.AnnotationConstants%>.FANCEY_CONSTANT) %>Integer%>", // ->
      "@FancyAnnotation(fancy=AnnotationConstants.FANCEY_CONSTANT) Integer");

    if (!failFast)
    {
      assertEquals(expectations.toString(), results.toString());
    }
  }

  @Test
  public void testAll()
  {
    failFast = false;
    test();
  }

  @Test
  public void testEach()
  {
    failFast = true;
    test();
  }
}
