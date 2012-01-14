/**
 * Copyright (c) 2006 IBM Corporation and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *   Peter Nehrer IBM - Initial API and implementation
 */
package org.eclipse.emf.test.xml;

import java.io.StringReader;
import java.util.HashMap;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.util.FeatureMapUtil;
import org.eclipse.emf.ecore.xmi.XMLOptions;
import org.eclipse.emf.ecore.xmi.XMLResource;
import org.eclipse.emf.ecore.xmi.impl.XMLOptionsImpl;
import org.eclipse.emf.ecore.xmi.impl.XMLResourceFactoryImpl;
import org.eclipse.emf.ecore.xmi.util.XMLProcessor;
import org.eclipse.emf.ecore.xml.type.AnyType;
import org.eclipse.emf.ecore.xml.type.ProcessingInstruction;
import org.eclipse.emf.ecore.xml.type.XMLTypeDocumentRoot;
import org.eclipse.emf.ecore.xml.type.XMLTypeFactory;
import org.eclipse.emf.ecore.xml.type.XMLTypePackage;
import org.xml.sax.InputSource;

public class ProcessingInstructionTest extends TestCase
{
  
  static final String LF = System.getProperty("line.separator");

  static final String XML = "<?pi1 prologue?>" + LF +
        "<root>" + LF +
        "\t<?pi2 nested?>" + LF +
        "</root>" + LF +
        "<?pi3 epilogue?>";

  private XMLProcessor processor;
  
  private HashMap<String, Object> options;
  
  private InputSource input;
  
  public ProcessingInstructionTest(String name)
  {
    super(name);
  }

  public static Test suite()
  {
    TestSuite ts = new TestSuite("ProcesingInstructionTest");
    ts.addTestSuite(ProcessingInstructionTest.class);
    return ts;
  }

  @Override
  protected void setUp() throws Exception
  {
    super.setUp();
    processor = new XMLProcessor();
    options = new HashMap<String, Object>();
    XMLOptions xmlOptions = new XMLOptionsImpl();
    xmlOptions.setProcessAnyXML(true);
    options.put(XMLResource.OPTION_XML_OPTIONS, xmlOptions);
    options.put(XMLResource.OPTION_DECLARE_XML, Boolean.FALSE);
    options.put(XMLResource.OPTION_USE_LEXICAL_HANDLER, Boolean.TRUE);
    input = new InputSource(new StringReader(XML));
  }

  public void testLoadPI() throws Exception
  {
    Resource res = processor.load(input, options);
    assertFalse(res.getContents().isEmpty());
    assertTrue(res.getContents().get(0) instanceof XMLTypeDocumentRoot);
    XMLTypeDocumentRoot doc = (XMLTypeDocumentRoot) res.getContents().get(0);
    assertEquals(3, doc.getMixed().size());
    
    assertSame(XMLTypePackage.Literals.XML_TYPE_DOCUMENT_ROOT__PROCESSING_INSTRUCTION, doc.getMixed().getEStructuralFeature(0));
    assertTrue(doc.getMixed().getValue(0) instanceof ProcessingInstruction);
    ProcessingInstruction pi = (ProcessingInstruction) doc.getMixed().getValue(0);
    assertEquals("pi1", pi.getTarget());
    assertEquals("prologue", pi.getData());

    assertSame(XMLTypePackage.Literals.XML_TYPE_DOCUMENT_ROOT__PROCESSING_INSTRUCTION, doc.getMixed().getEStructuralFeature(2));
    assertTrue(doc.getMixed().getValue(2) instanceof ProcessingInstruction);
    pi = (ProcessingInstruction) doc.getMixed().getValue(2);
    assertEquals("pi3", pi.getTarget());
    assertEquals("epilogue", pi.getData());
    
    assertTrue(doc.getMixed().getValue(1) instanceof AnyType);
    AnyType root = (AnyType) doc.getMixed().getValue(1);
    assertEquals(3, root.getMixed().size());
    assertSame(XMLTypePackage.Literals.XML_TYPE_DOCUMENT_ROOT__PROCESSING_INSTRUCTION, root.getMixed().getEStructuralFeature(1));
    assertTrue(root.getMixed().getValue(1) instanceof ProcessingInstruction);
    pi = (ProcessingInstruction) root.getMixed().getValue(1);
    assertEquals("pi2", pi.getTarget());
    assertEquals("nested", pi.getData());
  }
  
  public void testSavePI() throws Exception
  {
    EPackage pkg = processor.getExtendedMetaData().demandPackage(null);
    XMLTypeDocumentRoot doc = (XMLTypeDocumentRoot) EcoreUtil.create(processor.getExtendedMetaData().getDocumentRoot(pkg));
    FeatureMapUtil.addProcessingInstruction(doc.getMixed(), "pi1", "prologue");
    AnyType root = XMLTypeFactory.eINSTANCE.createAnyType();
    doc.getMixed().add(processor.getExtendedMetaData().demandFeature(null, "root", true), root);
    FeatureMapUtil.addText(root.getMixed(), "\n\t");
    FeatureMapUtil.addProcessingInstruction(root.getMixed(), "pi2", "nested");
    FeatureMapUtil.addText(root.getMixed(), "\n");
    FeatureMapUtil.addProcessingInstruction(doc.getMixed(), "pi3", "epilogue");
    
    ResourceSet resourceSet = new ResourceSetImpl();
    resourceSet.getResourceFactoryRegistry().getExtensionToFactoryMap().put(Resource.Factory.Registry.DEFAULT_EXTENSION, new XMLResourceFactoryImpl());
    Resource res = resourceSet.createResource(URI.createURI("test.xml"));
    res.getContents().add(doc);
    String xml = processor.saveToString(res, options);

    assertEquals(XML, xml);
  }
}
