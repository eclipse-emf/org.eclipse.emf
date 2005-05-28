package org.eclipse.emf.codegen.ecore.templates.edit;

import org.eclipse.emf.codegen.ecore.genmodel.*;

public class BuildProperties
{
  protected static String nl;
  public static synchronized BuildProperties create(String lineSeparator)
  {
    nl = lineSeparator;
    BuildProperties result = new BuildProperties();
    nl = null;
    return result;
  }

  protected final String NL = nl == null ? (System.getProperties().getProperty("line.separator")) : nl;
  protected final String TEXT_1 = "<!--";
  protected final String TEXT_2 = NL;
  protected final String TEXT_3 = "/**" + NL + " * <copyright>" + NL + " * </copyright>" + NL + " *" + NL + " * ";
  protected final String TEXT_4 = "Id";
  protected final String TEXT_5 = NL + " */" + NL + "-->";
  protected final String TEXT_6 = NL + "bin.includes = plugin.xml,\\";
  protected final String TEXT_7 = NL + "               ";
  protected final String TEXT_8 = ",\\" + NL + "               plugin.properties,\\" + NL + "               icons/" + NL + "jars.compile.order = ";
  protected final String TEXT_9 = NL + "source.";
  protected final String TEXT_10 = " = src/" + NL + "output.";
  protected final String TEXT_11 = " = bin/" + NL + "exclude.";
  protected final String TEXT_12 = " = **/doc-files/**";
  protected final String TEXT_13 = NL + "bin.includes = plugin.xml,\\" + NL + "               plugin.properties,\\" + NL + "               icons/";
  protected final String TEXT_14 = NL;

  public String generate(Object argument)
  {
    StringBuffer stringBuffer = new StringBuffer();
    
/**
 * <copyright>
 *
 * Copyright (c) 2002-2004 IBM Corporation and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Common Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/cpl-v10.html
 * 
 * Contributors: 
 *   IBM - Initial API and implementation
 *
 * </copyright>
 */

    GenModel genModel = (GenModel)argument;
    stringBuffer.append(TEXT_1);
    stringBuffer.append(TEXT_2);
    stringBuffer.append(TEXT_3);
    stringBuffer.append("$");
    stringBuffer.append(TEXT_4);
    stringBuffer.append("$");
    stringBuffer.append(TEXT_5);
    if (genModel.isRuntimeJar()) {
    String jarFile = genModel.getEditPluginID()+".jar";
    stringBuffer.append(TEXT_6);
    stringBuffer.append(TEXT_7);
    stringBuffer.append(jarFile);
    stringBuffer.append(TEXT_8);
    stringBuffer.append(jarFile);
    stringBuffer.append(TEXT_9);
    stringBuffer.append(jarFile);
    stringBuffer.append(TEXT_10);
    stringBuffer.append(jarFile);
    stringBuffer.append(TEXT_11);
    stringBuffer.append(jarFile);
    stringBuffer.append(TEXT_12);
    } else {
    stringBuffer.append(TEXT_13);
    }
    stringBuffer.append(TEXT_14);
    return stringBuffer.toString();
  }
}
