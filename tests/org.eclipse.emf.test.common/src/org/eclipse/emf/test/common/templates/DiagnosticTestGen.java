package org.eclipse.emf.test.common.templates;

import java.util.*;
import org.eclipse.emf.common.util.*;

public class DiagnosticTestGen
{
  protected static String nl;
  public static synchronized DiagnosticTestGen create(String lineSeparator)
  {
    nl = lineSeparator;
    DiagnosticTestGen result = new DiagnosticTestGen();
    nl = null;
    return result;
  }

  protected final String NL = nl == null ? (System.getProperties().getProperty("line.separator")) : nl;
  protected final String TEXT_1 = NL + "import java.util.Iterator;" + NL + "" + NL + "import junit.framework.Test;" + NL + "import junit.framework.TestCase;" + NL + "import junit.framework.TestSuite;" + NL + "" + NL + "import org.eclipse.emf.common.util.AbstractTreeIterator;" + NL + "import org.eclipse.emf.common.util.Diagnostic;" + NL + "import org.eclipse.emf.common.util.TreeIterator;" + NL + "import org.eclipse.emf.common.util.URI;" + NL + "import org.eclipse.emf.ecore.EPackage;" + NL + "import org.eclipse.emf.ecore.resource.Resource;" + NL + "import org.eclipse.emf.ecore.util.Diagnostician;" + NL + "import org.eclipse.emf.ecore.xmi.impl.EcoreResourceFactoryImpl;" + NL + "import org.eclipse.emf.test.common.TestUtil;" + NL + "" + NL + "public class DiagnosticTest extends TestCase" + NL + "{" + NL + "\tprotected Diagnostic diagnostic;" + NL + "\t" + NL + "\tpublic DiagnosticTest(String name)" + NL + "\t{" + NL + "\t\tsuper(name);" + NL + "\t}" + NL + "\t" + NL + "\tpublic static Test suite()" + NL + "\t{" + NL + "\t\tTestSuite ts = new TestSuite(\"DianosticTest\");" + NL + "\t\tts.addTest(new DiagnosticTest(\"testDiagnostic\"));" + NL + "\t\treturn ts;" + NL + "\t}" + NL + "\t" + NL + "\t@Override" + NL + "\tprotected void setUp() throws Exception" + NL + "\t{" + NL + "\t\t//TODO:Instantiates the diagnostic here" + NL + "\t\t" + NL + "\t\tassertNotNull(diagnostic);" + NL + "\t}" + NL + "\t" + NL + "\tpublic void testDiagnostic() throws Exception" + NL + "\t{" + NL + "\t\tTreeIterator<Diagnostic> diagnosticIterator = new AbstractTreeIterator<Diagnostic>(diagnostic)" + NL + "\t\t{" + NL + "\t\t\tprivate static final long serialVersionUID = 1L;" + NL + "\t\t\t@Override" + NL + "\t\t\tprotected Iterator<? extends Diagnostic> getChildren(Object object)" + NL + "\t\t\t{" + NL + "\t\t\t\treturn ((Diagnostic)object).getChildren().iterator();" + NL + "\t\t\t}" + NL + "\t\t};" + NL + "" + NL + "\t";
  protected final String TEXT_2 = NL + "\t\tDiagnostic diagnostic";
  protected final String TEXT_3 = " = diagnosticIterator.next();" + NL + "\t\tassertEquals(";
  protected final String TEXT_4 = ", diagnostic";
  protected final String TEXT_5 = ".getSeverity());" + NL + "\t\tassertEquals(";
  protected final String TEXT_6 = ", diagnostic";
  protected final String TEXT_7 = ".getSource());" + NL + "\t\tassertEquals(";
  protected final String TEXT_8 = ", removeObjectHash(diagnostic";
  protected final String TEXT_9 = ".getMessage()));" + NL + "\t\tassertEquals(";
  protected final String TEXT_10 = ", diagnostic";
  protected final String TEXT_11 = ".getCode());" + NL + "\t\tassertEquals(";
  protected final String TEXT_12 = ", diagnostic";
  protected final String TEXT_13 = ".getChildren().size());" + NL + "\t\tassertEquals(";
  protected final String TEXT_14 = ", diagnostic";
  protected final String TEXT_15 = ".getData().size());" + NL + "\t\t";
  protected final String TEXT_16 = "assertEquals(";
  protected final String TEXT_17 = ", toString(diagnostic";
  protected final String TEXT_18 = ".getException()));" + NL + "\t\t";
  protected final String TEXT_19 = "assertNull(diagnostic";
  protected final String TEXT_20 = ".getException());";
  protected final String TEXT_21 = NL + "\t\t" + NL + "\t\tassertFalse(diagnosticIterator.hasNext());" + NL + "\t}" + NL + "\t" + NL + "\tprotected String toString(Throwable throwable)" + NL + "\t{" + NL + "\t\tStringBuilder sb = new StringBuilder();" + NL + "\t\tsb.append(throwable.getClass().getName());" + NL + "\t\tsb.append(\"#\").append(throwable.getMessage());" + NL + "" + NL + "\t\tThrowable cause = throwable.getCause();" + NL + "\t\tif (cause != null && cause != throwable)" + NL + "\t\t{" + NL + "\t\t\tsb.append(\"--\").append(toString(cause));" + NL + "\t\t}" + NL + "\t\treturn sb.toString();" + NL + "\t}" + NL + "\t" + NL + "\tprotected String removeObjectHash(String string)" + NL + "\t{" + NL + "\t\treturn string.replaceAll(\"@\\\\w+\", \"\");" + NL + "\t}" + NL + "}";

  public String generate(Object argument)
  {
    final StringBuffer stringBuffer = new StringBuffer();
    
/**
 * <copyright>
 *
 * Copyright (c) 2006-2007 IBM Corporation and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: 
 *   IBM - Initial API and implementation
 *
 * </copyright>
 */

    
  Diagnostic rootDiagnostic = (Diagnostic)argument;
  TreeIterator<Diagnostic> diagnosticIterator = new AbstractTreeIterator<Diagnostic>(rootDiagnostic)
  {
    private static final long serialVersionUID = 1L;
    @Override
    protected Iterator<? extends Diagnostic> getChildren(Object object)
    {
      return ((Diagnostic)object).getChildren().iterator();
    }
  };
    
  class Helper
  {
    public String toString(Throwable throwable)
    {
      StringBuilder sb = new StringBuilder();
      sb.append(throwable.getClass().getName());
      sb.append("#").append(throwable.getMessage());

      Throwable cause = throwable.getCause();
      if (cause != null && cause != throwable)
      {
          sb.append("--").append(toString(cause));
      }
      return sb.toString();
    }
    
    protected String removeObjectHash(String string)
    {
      return string.replaceAll("@\\w+", "");
    }
    
    
    public String toCodeString(String string)
    {
      return "\"" + string.replaceAll("\\\\", "\\\\\\\\").replaceAll("\\\"", "\\\\\"") + "\"";
    }
    
    public String getDiagnosticSeverity(int value)
    {
      switch(value)
      {
        case Diagnostic.CANCEL:
          return "Diagnostic.CANCEL";
        case Diagnostic.OK:
          return "Diagnostic.OK";
        case Diagnostic.INFO:
          return "Diagnostic.INFO";
        case Diagnostic.ERROR:
          return "Diagnostic.ERROR";
        case Diagnostic.WARNING:
          return "Diagnostic.WARNING";
        default:
          return Integer.toString(value);
      }
    }
  }
  Helper helper = new Helper();

    stringBuffer.append(TEXT_1);
    int count=0; while(diagnosticIterator.hasNext()){Diagnostic diagnostic = diagnosticIterator.next(); count++;
    stringBuffer.append(TEXT_2);
    stringBuffer.append(count);
    stringBuffer.append(TEXT_3);
    stringBuffer.append(helper.getDiagnosticSeverity(diagnostic.getSeverity()));
    stringBuffer.append(TEXT_4);
    stringBuffer.append(count);
    stringBuffer.append(TEXT_5);
    stringBuffer.append(helper.toCodeString(diagnostic.getSource()));
    stringBuffer.append(TEXT_6);
    stringBuffer.append(count);
    stringBuffer.append(TEXT_7);
    stringBuffer.append(helper.removeObjectHash(helper.toCodeString(diagnostic.getMessage())));
    stringBuffer.append(TEXT_8);
    stringBuffer.append(count);
    stringBuffer.append(TEXT_9);
    stringBuffer.append(diagnostic.getCode());
    stringBuffer.append(TEXT_10);
    stringBuffer.append(count);
    stringBuffer.append(TEXT_11);
    stringBuffer.append(diagnostic.getChildren().size());
    stringBuffer.append(TEXT_12);
    stringBuffer.append(count);
    stringBuffer.append(TEXT_13);
    stringBuffer.append(diagnostic.getData().size());
    stringBuffer.append(TEXT_14);
    stringBuffer.append(count);
    stringBuffer.append(TEXT_15);
    Throwable throwable = diagnostic.getException(); if (throwable != null) {
    stringBuffer.append(TEXT_16);
    stringBuffer.append(helper.toCodeString(helper.toString(diagnostic.getException())));
    stringBuffer.append(TEXT_17);
    stringBuffer.append(count);
    stringBuffer.append(TEXT_18);
    } else {
    stringBuffer.append(TEXT_19);
    stringBuffer.append(count);
    stringBuffer.append(TEXT_20);
    } if (diagnosticIterator.hasNext()) {stringBuffer.append(NL);}}
    stringBuffer.append(TEXT_21);
    return stringBuffer.toString();
  }
}
