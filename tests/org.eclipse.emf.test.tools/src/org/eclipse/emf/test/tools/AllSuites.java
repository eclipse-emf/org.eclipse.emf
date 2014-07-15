/**
 * Copyright (c) 2002-2007 IBM Corporation and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *   IBM - Initial API and implementation
 */
package org.eclipse.emf.test.tools;


import org.eclipse.emf.test.tools.ant.AntTest;
import org.eclipse.emf.test.tools.codegen.CodeGenUtilTest;
import org.eclipse.emf.test.tools.codegen.GenModelDefaultsTest;
import org.eclipse.emf.test.tools.codegen.GenModelTest;
import org.eclipse.emf.test.tools.codegen.LiteralsTest;
import org.eclipse.emf.test.tools.codegen.RemappedXMLTypesTest;
import org.eclipse.emf.test.tools.converter.ConverterUtilTest;
import org.eclipse.emf.test.tools.importer.ModelImporterTest;
import org.eclipse.emf.test.tools.merger.MergerAllSuites;
import org.eclipse.emf.test.tools.merger.facade.FacadeAllSuites;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;


@RunWith(Suite.class)
@Suite.SuiteClasses
  ({
    ImportManagerTest.class,
    JETTest.class,
    ToolsTest.class,
    AntTest.class,
    CodeGenUtilTest.class,
    GenModelDefaultsTest.class,
    GenModelTest.class,
    LiteralsTest.class,
    RemappedXMLTypesTest.class,
    ConverterUtilTest.class,
    ModelImporterTest.class,
    MergerAllSuites.class,
    FacadeAllSuites.class
  })
public class AllSuites
{
  public static final String PLUGIN_ID = "org.eclipse.emf.test.tools";
}