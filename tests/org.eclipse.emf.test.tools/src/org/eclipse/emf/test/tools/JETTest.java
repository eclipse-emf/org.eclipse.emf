/**
 * Copyright (c) 2004-2007 IBM Corporation and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: 
 *   IBM - Initial API and implementation
 */
package org.eclipse.emf.test.tools;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.Writer;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.Path;

import org.eclipse.emf.codegen.jet.JETEmitter;
import org.eclipse.emf.codegen.jet.JETException;
import org.eclipse.emf.common.util.DiagnosticException;
import org.eclipse.emf.test.common.TestUtil;

public class JETTest extends TestCase
{
  protected static final File TEMPLATE_FILE = new File(TestUtil.getPluginDirectory(AllSuites.PLUGIN_ID), "aTemplate.javajet");
  
  /**
   * @param name
   */
  public JETTest(String name)
  {
    super(name);
  }

  public static Test suite()
  {
    TestSuite ts = new TestSuite("JETTest");
    ts.addTest(new JETTest("testEmmiter"));
    ts.addTest(new JETTest("testCRLF"));
    ts.addTest(new JETTest("testLF"));
    return ts;
  }
  
  public void testEmmiter() throws Exception
  {
    String nonOSLineSeparator = "\r\n".equals(System.getProperties().getProperty("line.separator")) ? "\n" : "\r\n";
    String text = createTemplateText(nonOSLineSeparator);
    saveFile(TEMPLATE_FILE, templateHeader(nonOSLineSeparator).append(text).toString());
    
    JETEmitter emitter = new JETEmitter(TEMPLATE_FILE.getAbsolutePath());
    String generatedText = emitter.generate(new NullProgressMonitor(), new Object[]{""});
    
    text = text.replaceAll(nonOSLineSeparator, System.getProperties().getProperty("line.separator"));
    assertEquals(text, generatedText);
    TEMPLATE_FILE.delete();
  }
  
  public void testCRLF() throws Exception
  {
    assertLineSeparator("\r\n");
  }

  public void testLF() throws Exception
  {
    assertLineSeparator("\n");
  }
  
  /*
   * This test:
   * 1. Creates a javajet template using the specified lineSeparator
   * 2. Generates a class by invoking the Emitter against the template above
   * 3. Reads the content of the Gen class
   * 4. Checks if the Gen class has the appropriate text.
   * 
   * Given this text:
   * 	
   * xyz<NL>yy1
   * 
   * where NL is the line separator, the "appropriate" text in the Gen class is
   * 
   * "xyz" + NL + "yy1"
   */
  protected void assertLineSeparator(String lineSeparator) throws IOException, CoreException
  {
    String text = createTemplateText(lineSeparator);
    saveFile(TEMPLATE_FILE, templateHeader(lineSeparator).append(text).toString());
    
    JETEmitter emitter = new JETEmitter(TEMPLATE_FILE.getAbsolutePath());
    try
    {
      emitter.generate(new NullProgressMonitor(), new Object[]{""});
    }
    catch (JETException exception)
    {
      throw DiagnosticException.toCoreException(exception);
    }
    
    IProject project = ResourcesPlugin.getWorkspace().getRoot().getProject(emitter.getProjectName());
    IFile generatedJavaFile = project.getFile(new Path("src/jetTest/ATemplateGen.java"));
    assertTrue(generatedJavaFile.exists());
    String generatedGen = readInputStream(generatedJavaFile.getContents());
    
    StringBuffer expectedText = new StringBuffer(text);
    while(expectedText.charAt(0) == '\r' | expectedText.charAt(0) == '\n')
    {
      expectedText.deleteCharAt(0);
    }
    for(int i=expectedText.length()-1; expectedText.charAt(i) == '\r' | expectedText.charAt(i) == '\n'; i--)
    {
      expectedText.deleteCharAt(i);
    }
    text = "\"" + expectedText.toString().replaceAll(lineSeparator, "\" + NL + \"").replaceAll("\t", "\\\\t") + "\"";

    assertTrue(generatedGen.indexOf(text) >= 0);
    TEMPLATE_FILE.delete();
  }
  
  protected StringBuffer templateHeader(String lineSeparator)
  {
    StringBuffer text = new StringBuffer();
    text.append("<%@ jet package=\"jetTest\" imports=\"\" class=\"ATemplateGen\"%>").append(lineSeparator);
    
    text.append("<%").append(lineSeparator);
    text.append("/**").append(lineSeparator);
    text.append(" * <copyright>").append(lineSeparator);
    text.append(" */").append(lineSeparator);
    text.append("%>").append(lineSeparator);
    return text;
  }
  
  protected String createTemplateText(String lineSeparator)
  {
    StringBuffer text = new StringBuffer();
    text.append(lineSeparator);
    text.append("public class ATemplateGen").append(lineSeparator);
    text.append("{").append(lineSeparator);
    text.append("\tprivate String name;").append(lineSeparator);
    text.append("\tprivate int value;").append(lineSeparator);
    text.append("}").append(lineSeparator);
    text.append(lineSeparator);

    return text.toString();
  }
  
  protected void saveFile(File file, String text) throws IOException
  {
    Writer writer = null;
    try
    {
	    writer = new BufferedWriter(new FileWriter(file));
	    writer.write(text);
    }
    finally
    {
      if(writer != null)
      {
        writer.close();
      }
    }
  }
  
  protected String readInputStream(InputStream inputStream) throws IOException
  {
    Reader reader = null;
    try
    {
	    reader = new InputStreamReader(inputStream);
	    StringBuffer stringBuffer = new StringBuffer();
	    for(int c=reader.read(); c > -1; c=reader.read())
	    {
	      stringBuffer.append((char)c);
	    }
	    return stringBuffer.toString();
    }
    finally
    {
      if(reader != null)
      {
        reader.close();
      }
    }
  }  
}
