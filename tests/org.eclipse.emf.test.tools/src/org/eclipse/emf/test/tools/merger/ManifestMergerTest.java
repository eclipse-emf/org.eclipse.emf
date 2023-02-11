/**
 * Copyright (c) 2023 Eclipse contributors and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 */
package org.eclipse.emf.test.tools.merger;


import static org.junit.Assert.assertEquals;

import org.eclipse.emf.codegen.ecore.generator.AbstractGeneratorAdapter;
import org.eclipse.emf.common.util.Diagnostic;
import org.eclipse.emf.common.util.Monitor;
import org.junit.Test;


public class ManifestMergerTest
{
  private String merge(String oldContents, String newContents)
  {
    String result = new AbstractGeneratorAdapter()
      {
        @Override
        public boolean canGenerate(Object object, Object projectType)
        {
          return false;
        }

        @Override
        protected Diagnostic doGenerate(Object object, Object projectType, Monitor monitor) throws Exception
        {
          return null;
        }

        @Override
        public String mergeManifest(String oldContents, String newContents)
        {
          return super.mergeManifest(oldContents, newContents);
        }
      }.mergeManifest(oldContents, newContents);
    return result;
  }

  private String join(String... strings)
  {
    StringBuilder result = new StringBuilder();
    for (String string : strings)
    {
      result.append(string);
      result.append('\n');
    }
    return result.toString();
  }

  @Test
  public void testEmpty()
  {
    assertEquals(
      join(//
        "Manifest-Version: 1.0"),
      merge(
        join(//
          "Manifest-Version: 1.0"),
        join(//
          "Manifest-Version: 1.0")));
  }

  @Test
  public void testBREE()
  {
    assertEquals(
      join(//
        "Manifest-Version: 1.0",
        "Bundle-RequiredExecutionEnvironment: JavaSE-1.8"),
      merge(
        join(//
          "Manifest-Version: 1.0",
          "Bundle-RequiredExecutionEnvironment: JavaSE-1.5"),
        join(//
          "Manifest-Version: 1.0",
          "Bundle-RequiredExecutionEnvironment: JavaSE-1.8")));
  }

  @Test
  public void testEscape()
  {
    assertEquals(
      join(//
        "Manifest-Version: 1.0",
        "Provide-Capability: cap;value=\"\\\\\\\"\",",
        " org.eclipse.emf.ecore.generated_package;uri=\"https:///test\";class=org.example.test.TestXPackage;genModel=\"model/Test.genmodel\""),
      merge(
        join(//
          "Manifest-Version: 1.0",
          "Provide-Capability: cap;value= \"\\\\\\\"\" "),
        join(//
          "Manifest-Version: 1.0",
          "Provide-Capability: org.eclipse.emf.ecore.generated_package;uri=\"https:///test\";class=org.example.test.TestXPackage;genModel=\"model/Test.genmodel\"")));
  }

  @Test
  public void testChangeClass()
  {
    assertEquals(
      join(//
        "Manifest-Version: 1.0",
        "Provide-Capability: org.eclipse.emf.ecore.generated_package;uri=\"https:///test\";class=org.example.test.TestXPackage;genModel=\"model/Test.genmodel\""),
      merge(
        join(//
          "Manifest-Version: 1.0",
          "Provide-Capability: org.eclipse.emf.ecore.generated_package;uri=\"https:///test\";class=org.example.test.TestPackage;genModel=\"model/Test.genmodel\""),
        join(//
          "Manifest-Version: 1.0",
          "Provide-Capability: org.eclipse.emf.ecore.generated_package;uri=\"https:///test\";class=org.example.test.TestXPackage;genModel=\"model/Test.genmodel\"")));
  }

  @Test
  public void testChangeNsURI()
  {
    assertEquals(
      join(//
        "Manifest-Version: 1.0",
        "Provide-Capability: org.eclipse.emf.ecore.generated_package;uri=\"https:///testx\";class=org.example.test.TestPackage;genModel=\"model/TestX.genmodel\""),
      merge(
        join(//
          "Manifest-Version: 1.0",
          "Provide-Capability: org.eclipse.emf.ecore.generated_package;uri=\"https:///test\";class=org.example.test.TestPackage;genModel=\"model/Test.genmodel\""),
        join(//
          "Manifest-Version: 1.0",
          "Provide-Capability: org.eclipse.emf.ecore.generated_package;uri=\"https:///testx\";class=org.example.test.TestPackage;genModel=\"model/TestX.genmodel\"")));
  }

  @Test
  public void testAddNew()
  {
    assertEquals(
      join(//
        "Manifest-Version: 1.0",
        "Provide-Capability: org.eclipse.emf.ecore.generated_package;uri=\"https:///test\";class=org.example.test.TestPackage;genModel=\"model/Test.genmodel\""),
      merge(
        join(//
          "Manifest-Version: 1.0"),
        join(//
          "Manifest-Version: 1.0",
          "Provide-Capability: org.eclipse.emf.ecore.generated_package;uri=\"https:///test\";class=org.example.test.TestPackage;genModel=\"model/Test.genmodel\"")));
  }

  @Test
  public void testAdd()
  {
    assertEquals(
      join(//
        "Manifest-Version: 1.0",
        "Provide-Capability: org.eclipse.emf.ecore.generated_package;uri=\"https:///test\";class=org.example.test.TestPackage;genModel=\"model/Test.genmodel\",",
        " org.eclipse.emf.ecore.generated_package;uri=\"https:///test2\";class=org.example.test.Test2Package;genModel=\"model/Test2.genmodel\""),
      merge(
        join(//
          "Manifest-Version: 1.0",
          "Provide-Capability: org.eclipse.emf.ecore.generated_package;uri=\"https:///test\";class=org.example.test.TestPackage;genModel=\"model/Test.genmodel\""),
        join(//
          "Manifest-Version: 1.0",
          "Provide-Capability: org.eclipse.emf.ecore.generated_package;uri=\"https:///test\";class=org.example.test.TestPackage;genModel=\"model/Test.genmodel\",",
          " org.eclipse.emf.ecore.generated_package;uri=\"https:///test2\";class=org.example.test.Test2Package;genModel=\"model/Test2.genmodel\"")));
  }

  @Test
  public void testAddWithOther()
  {
    assertEquals(
      join(//
        "Manifest-Version: 1.0",
        "Provide-Capability: osgi.service;objectClass:List<String>=org.osgi.service.cm.ConfigurationAdmin;",
        "  uses:=\"org.osgi.service.cm\",",
        " osgi.implementation;osgi.implementation=osgi.cm;version:Version=\"1.6\";",
        "  uses:=\"org.osgi.service.cm\",",
        " org.eclipse.emf.ecore.generated_package;uri=\"https:///test\";class=org.example.test.TestPackage;genModel=\"model/Test.genmodel\",",
        " org.eclipse.emf.ecore.generated_package;uri=\"https:///test2\";class=org.example.test.Test2Package;genModel=\"model/Test2.genmodel\""),
      merge(
        join(//
          "Manifest-Version: 1.0",
          "Provide-Capability: ",
          " osgi.service;",
          "  objectClass:List<String>=\"org.osgi.service.cm.ConfigurationAdmin\";",
          "  uses:=\"org.osgi.service.cm\",",
          " osgi.implementation;",
          "  osgi.implementation=\"osgi.cm\";",
          "  uses:=\"org.osgi.service.cm\";",
          "  version:Version=\"1.6\""),
        join(//
          "Manifest-Version: 1.0",
          "Provide-Capability: org.eclipse.emf.ecore.generated_package;uri=\"https:///test\";class=org.example.test.TestPackage;genModel=\"model/Test.genmodel\",",
          " org.eclipse.emf.ecore.generated_package;uri=\"https:///test2\";class=org.example.test.Test2Package;genModel=\"model/Test2.genmodel\"")));
  }
}
