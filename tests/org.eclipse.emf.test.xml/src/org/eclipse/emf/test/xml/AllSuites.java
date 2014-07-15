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
package org.eclipse.emf.test.xml;


import org.eclipse.emf.test.xml.encoding.UnicodeEncodingTest;
import org.eclipse.emf.test.xml.encoding.XMLHeaderTest;
import org.eclipse.emf.test.xml.rss.RSSTests;
import org.eclipse.emf.test.xml.xmi.CrossResourceReferencesTest;
import org.eclipse.emf.test.xml.xmi.ElementHandlerTest;
import org.eclipse.emf.test.xml.xmi.LaxFeatureNamespaceMatchingTest;
import org.eclipse.emf.test.xml.xmi.LineWidthTest;
import org.eclipse.emf.test.xml.xmi.MultipleDocumentRootTest;
import org.eclipse.emf.test.xml.xmi.NamespaceTest;
import org.eclipse.emf.test.xml.xmi.NullNamespaceTest;
import org.eclipse.emf.test.xml.xmi.OrderTest;
import org.eclipse.emf.test.xml.xmi.QNameTest;
import org.eclipse.emf.test.xml.xmi.URIHandlerTest;
import org.eclipse.emf.test.xml.xmi.UnloadXMIResourceTest;
import org.eclipse.emf.test.xml.xsd.XSDFeatureTypeTest;
import org.eclipse.emf.test.xml.xsdecore.Ecore2XSDTest;
import org.eclipse.emf.test.xml.xsdecore.XSD2EcoreTest;
import org.eclipse.emf.test.xml.xsdecore.XSDEcoreBuilderTests;
import org.eclipse.emf.test.xml.xsdecore.XSDTests;
import org.eclipse.emf.test.xml.xsdecore.XSDValidateTest;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;


@RunWith(Suite.class)
@Suite.SuiteClasses
  ({
    LineWidthTest.class,
    ElementHandlerTest.class,
    NullNamespaceTest.class,
    NamespaceTest.class,
    OrderTest.class,
    QNameTest.class,
    CrossResourceReferencesTest.class,
    UnloadXMIResourceTest.class,
    MultipleDocumentRootTest.class,
    UnicodeEncodingTest.class,
    XMLHeaderTest.class,
    Ecore2XSDTest.class,
    XSDEcoreBuilderTests.class,
    XSD2EcoreTest.class,
    XSDValidateTest.class,
    XSDTests.class,
    RSSTests.class,
    ProcessingInstructionTest.class,
    DTDTest.class,
    URIHandlerTest.class,
    LaxFeatureNamespaceMatchingTest.class,
    XSDFeatureTypeTest.class
  })
public class AllSuites
{
  public static final String PLUGIN_ID = "org.eclipse.emf.test.xml";
}