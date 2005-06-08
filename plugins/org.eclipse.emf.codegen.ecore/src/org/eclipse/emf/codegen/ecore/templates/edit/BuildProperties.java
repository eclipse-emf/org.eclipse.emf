package org.eclipse.emf.codegen.ecore.templates.edit;

import org.eclipse.emf.codegen.ecore.genmodel.*;

public class BuildProperties
{
  protected final String NL = System.getProperties().getProperty("line.separator");
  protected final String TEXT_1 = "<!--" + NL;
  protected final String TEXT_2 = "/**" + NL + " * <copyright>" + NL + " * </copyright>" + NL + " *" + NL + " * ";
  protected final String TEXT_3 = "Id";
  protected final String TEXT_4 = NL + " */" + NL + "-->";
  protected final String TEXT_5 = NL + "bin.includes =\tplugin.xml,\\" + NL + "\t\t\t\t";
  protected final String TEXT_6 = ",\\" + NL + "\t\t\t\tplugin.properties,\\" + NL + "\t\t\t\ticons/" + NL + "jars.compile.order = ";
  protected final String TEXT_7 = NL + "source.";
  protected final String TEXT_8 = " = src/" + NL + "output.";
  protected final String TEXT_9 = " = bin/";
  protected final String TEXT_10 = NL + "bin.includes =\tplugin.xml,\\" + NL + "\t\t\t\tplugin.properties,\\" + NL + "\t\t\t\ticons/";

  public String generate(Object argument)
  {
    StringBuffer stringBuffer = new StringBuffer();
    
/**
 * <copyright>
 *
 * Copyright (c) 2002-2004 IBM Corporation and others.
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

    GenModel genModel = (GenModel)argument;
    stringBuffer.append(TEXT_1);
    stringBuffer.append(TEXT_2);
    stringBuffer.append("$");
    stringBuffer.append(TEXT_3);
    stringBuffer.append("$");
    stringBuffer.append(TEXT_4);
    if (genModel.isRuntimeJar()) {
    String jarFile = "runtime/"+genModel.getEditPluginID()+".jar";
    stringBuffer.append(TEXT_5);
    stringBuffer.append(jarFile);
    stringBuffer.append(TEXT_6);
    stringBuffer.append(jarFile);
    stringBuffer.append(TEXT_7);
    stringBuffer.append(jarFile);
    stringBuffer.append(TEXT_8);
    stringBuffer.append(jarFile);
    stringBuffer.append(TEXT_9);
    } else {
    stringBuffer.append(TEXT_10);
    }
    return stringBuffer.toString();
  }
}
