package org.eclipse.emf.codegen.ecore.templates.model;

import org.eclipse.emf.codegen.ecore.genmodel.*;

public class BuildProperties
{
  protected final String NL = System.getProperties().getProperty("line.separator");
  protected final String TEXT_1 = "<!--";
  protected final String TEXT_2 = NL;
  protected final String TEXT_3 = "/**" + NL + " * <copyright>" + NL + " * </copyright>" + NL + " *" + NL + " * ";
  protected final String TEXT_4 = "Id";
  protected final String TEXT_5 = NL + " */" + NL + "-->";
  protected final String TEXT_6 = NL + "bin.includes =\tplugin.xml,\\" + NL + "\t\t\t\t";
  protected final String TEXT_7 = ",\\";
  protected final String TEXT_8 = NL + "\t\t\t\ticons/,\\";
  protected final String TEXT_9 = NL + "\t\t\t\tplugin.properties" + NL + "jars.compile.order = ";
  protected final String TEXT_10 = NL + "source.";
  protected final String TEXT_11 = " = src/" + NL + "output.";
  protected final String TEXT_12 = " = bin/";
  protected final String TEXT_13 = NL + "bin.includes =\tplugin.xml,\\" + NL + "\t\t\t\tplugin.properties";

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
    String jarFile = "runtime/"+genModel.getModelPluginID()+".jar";
    stringBuffer.append(TEXT_6);
    stringBuffer.append(jarFile);
    stringBuffer.append(TEXT_7);
    if (genModel.sameModelEditProject() || genModel.sameModelEditorProject()) {
    stringBuffer.append(TEXT_8);
    }
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
    return stringBuffer.toString();
  }
}
