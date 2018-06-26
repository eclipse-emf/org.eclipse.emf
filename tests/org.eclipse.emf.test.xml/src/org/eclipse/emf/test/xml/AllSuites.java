/**
 * Copyright (c) 2002-2007 IBM Corporation and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
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
import org.eclipse.emf.test.xml.xmi.XMIExtendedMetadataTest;
import org.eclipse.emf.test.xml.xmi.XMIResourceURIFragmentsTest;
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
    DTDTest.class,
    ProcessingInstructionTest.class,
    XMLStringTest.class,

    UnicodeEncodingTest.class,
    XMLHeaderTest.class,

    RSSTests.class,

    CrossResourceReferencesTest.class,
    ElementHandlerTest.class,
    LaxFeatureNamespaceMatchingTest.class,
    LineWidthTest.class,
    MultipleDocumentRootTest.class,
    NamespaceTest.class,
    NullNamespaceTest.class,
    OrderTest.class,
    QNameTest.class,
    UnloadXMIResourceTest.class,
    URIHandlerTest.class,
    XMIExtendedMetadataTest.class,
    XMIResourceURIFragmentsTest.class,

    XSDFeatureTypeTest.class,

    Ecore2XSDTest.class,
    XSD2EcoreTest.class,
    XSDEcoreBuilderTests.class,
    XSDTests.class,
    XSDValidateTest.class,
  })
public class AllSuites
{
  public static final String PLUGIN_ID = "org.eclipse.emf.test.xml";
}