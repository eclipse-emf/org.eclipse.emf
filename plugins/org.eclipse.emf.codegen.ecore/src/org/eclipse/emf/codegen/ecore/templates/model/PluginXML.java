package org.eclipse.emf.codegen.ecore.templates.model;

import java.util.*;
import org.eclipse.emf.codegen.ecore.genmodel.*;

public class PluginXML
{
  protected final String NL = System.getProperties().getProperty("line.separator");
  protected final String TEXT_1 = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>" + NL + "<?eclipse version=\"3.0\"?>" + NL + "" + NL + "<!--";
  protected final String TEXT_2 = NL;
  protected final String TEXT_3 = "/**" + NL + " * <copyright>" + NL + " * </copyright>" + NL + " *" + NL + " * ";
  protected final String TEXT_4 = "Id";
  protected final String TEXT_5 = NL + " */" + NL + "-->" + NL + "" + NL + "<plugin" + NL + "    name = \"%pluginName\"" + NL + "    id = \"";
  protected final String TEXT_6 = "\"" + NL + "    version = \"1.0.0\"";
  protected final String TEXT_7 = NL + "    provider-name = \"%providerName\"" + NL + "    class = \"";
  protected final String TEXT_8 = "$Implementation\">";
  protected final String TEXT_9 = NL + "    provider-name = \"%providerName\">";
  protected final String TEXT_10 = NL + NL + "  <requires>";
  protected final String TEXT_11 = NL + "    <import plugin=\"";
  protected final String TEXT_12 = "\" ";
  protected final String TEXT_13 = "export=\"true\"";
  protected final String TEXT_14 = "/>";
  protected final String TEXT_15 = NL + "  </requires>" + NL + "" + NL + "  <runtime>";
  protected final String TEXT_16 = NL + "    <library name=\"runtime/";
  protected final String TEXT_17 = ".jar\">";
  protected final String TEXT_18 = NL + "    <library name=\"runtime/\">";
  protected final String TEXT_19 = NL + "      <export name=\"*\"/>" + NL + "    </library>" + NL + "  </runtime>";
  protected final String TEXT_20 = NL + NL + "  <extension point=\"org.eclipse.emf.ecore.generated_package\">" + NL + "    <package " + NL + "       uri = \"";
  protected final String TEXT_21 = "\" " + NL + "       class = \"";
  protected final String TEXT_22 = "\" />" + NL + "  </extension>";
  protected final String TEXT_23 = NL + NL + "  <extension point=\"org.eclipse.emf.ecore.extension_parser\">" + NL + "    <parser " + NL + "       type=\"";
  protected final String TEXT_24 = "\" " + NL + "       class=\"";
  protected final String TEXT_25 = "\" />" + NL + "  </extension>";
  protected final String TEXT_26 = NL + NL + "</plugin>" + NL;
  protected final String TEXT_27 = NL;

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
    stringBuffer.append(genModel.getModelPluginID());
    stringBuffer.append(TEXT_6);
    if (genModel.hasModelPluginClass()) { 
    stringBuffer.append(TEXT_7);
    stringBuffer.append(genModel.getQualifiedModelPluginClassName());
    stringBuffer.append(TEXT_8);
    } else {
    stringBuffer.append(TEXT_9);
    }
    stringBuffer.append(TEXT_10);
    for (Iterator j=genModel.getModelRequiredPlugins().iterator(); j.hasNext();) { String pluginID = (String)j.next();
    stringBuffer.append(TEXT_11);
    stringBuffer.append(pluginID);
    stringBuffer.append(TEXT_12);
    if (!pluginID.startsWith("org.eclipse.core.runtime")) {
    stringBuffer.append(TEXT_13);
    }
    stringBuffer.append(TEXT_14);
    }
    stringBuffer.append(TEXT_15);
    if (genModel.isRuntimeJar()) {
    stringBuffer.append(TEXT_16);
    stringBuffer.append(genModel.getModelPluginID());
    stringBuffer.append(TEXT_17);
    } else {
    stringBuffer.append(TEXT_18);
    }
    stringBuffer.append(TEXT_19);
    for (Iterator i = genModel.getAllGenPackagesWithClassifiers().iterator(); i.hasNext(); ) { GenPackage genPackage = (GenPackage)i.next(); 
    stringBuffer.append(TEXT_20);
    stringBuffer.append(genPackage.getNSURI());
    stringBuffer.append(TEXT_21);
    stringBuffer.append(genPackage.getQualifiedPackageInterfaceName());
    stringBuffer.append(TEXT_22);
    if (genPackage.getResource() != GenResourceKind.NONE_LITERAL) {
    stringBuffer.append(TEXT_23);
    stringBuffer.append(genPackage.getPrefix().toLowerCase());
    stringBuffer.append(TEXT_24);
    stringBuffer.append(genPackage.getQualifiedResourceFactoryClassName());
    stringBuffer.append(TEXT_25);
    }
    }
    stringBuffer.append(TEXT_26);
    stringBuffer.append(TEXT_27);
    return stringBuffer.toString();
  }
}
