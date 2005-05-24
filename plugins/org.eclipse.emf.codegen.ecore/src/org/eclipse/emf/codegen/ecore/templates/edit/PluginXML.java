package org.eclipse.emf.codegen.ecore.templates.edit;

import java.util.*;
import org.eclipse.emf.codegen.ecore.genmodel.*;

public class PluginXML
{
  protected static String nl;
  public static synchronized PluginXML create(String lineSeparator)
  {
    nl = lineSeparator;
    PluginXML result = new PluginXML();
    nl = null;
    return result;
  }

  protected final String NL = nl == null ? (System.getProperties().getProperty("line.separator")) : nl;
  protected final String TEXT_1 = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>" + NL + "<?eclipse version=\"3.0\"?>" + NL + "" + NL + "<!--";
  protected final String TEXT_2 = NL;
  protected final String TEXT_3 = "/**" + NL + " * <copyright>" + NL + " * </copyright>" + NL + " *" + NL + " * ";
  protected final String TEXT_4 = "Id";
  protected final String TEXT_5 = NL + " */" + NL + "-->" + NL + "" + NL + "<plugin" + NL + "    name = \"%pluginName\"" + NL + "    id = \"";
  protected final String TEXT_6 = "\"" + NL + "    version = \"1.0.0\"" + NL + "    provider-name = \"%providerName\"" + NL + "    class = \"";
  protected final String TEXT_7 = "$Implementation\">" + NL + "" + NL + "  <requires>";
  protected final String TEXT_8 = NL + "    <import plugin=\"";
  protected final String TEXT_9 = "\" ";
  protected final String TEXT_10 = "export=\"true\"";
  protected final String TEXT_11 = "/>";
  protected final String TEXT_12 = NL + "  </requires>" + NL + "" + NL + "  <runtime>";
  protected final String TEXT_13 = NL + "    <library name=\"";
  protected final String TEXT_14 = ".jar\">";
  protected final String TEXT_15 = NL + "    <library name=\".\">";
  protected final String TEXT_16 = NL + "      <export name=\"*\"/>" + NL + "    </library>" + NL + "  </runtime>";
  protected final String TEXT_17 = NL + "    " + NL + "  <extension point=\"org.eclipse.emf.edit.itemProviderAdapterFactories\">" + NL + "    <factory " + NL + "       uri = \"";
  protected final String TEXT_18 = "\" " + NL + "       class = \"";
  protected final String TEXT_19 = "\" " + NL + "       supportedTypes = ";
  protected final String TEXT_20 = NL + "         ";
  protected final String TEXT_21 = " />" + NL + "  </extension>";
  protected final String TEXT_22 = NL + NL + "  <extension point=\"org.eclipse.emf.ecore.generated_package\">" + NL + "    <package" + NL + "       uri = \"";
  protected final String TEXT_23 = "\"" + NL + "       class = \"";
  protected final String TEXT_24 = "\"";
  protected final String TEXT_25 = " />";
  protected final String TEXT_26 = NL + "       genModel = \"";
  protected final String TEXT_27 = "\" /> ";
  protected final String TEXT_28 = NL + "  </extension>";
  protected final String TEXT_29 = NL + NL + "  <extension point=\"org.eclipse.emf.ecore.extension_parser\">" + NL + "    <parser" + NL + "       type=\"";
  protected final String TEXT_30 = "\"" + NL + "       class=\"";
  protected final String TEXT_31 = "\" />" + NL + "  </extension>";
  protected final String TEXT_32 = NL + NL + "</plugin>" + NL;
  protected final String TEXT_33 = NL;

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
    stringBuffer.append(genModel.getEditPluginID());
    stringBuffer.append(TEXT_6);
    stringBuffer.append(genModel.getQualifiedEditPluginClassName());
    stringBuffer.append(TEXT_7);
    for (Iterator j=genModel.getEditRequiredPlugins().iterator(); j.hasNext();) { String pluginID = (String)j.next();
    stringBuffer.append(TEXT_8);
    stringBuffer.append(pluginID);
    stringBuffer.append(TEXT_9);
    if (!pluginID.startsWith("org.eclipse.core.runtime")) {
    stringBuffer.append(TEXT_10);
    }
    stringBuffer.append(TEXT_11);
    }
    stringBuffer.append(TEXT_12);
    if (genModel.isRuntimeJar()) {
    stringBuffer.append(TEXT_13);
    stringBuffer.append(genModel.getEditPluginID());
    stringBuffer.append(TEXT_14);
    } else {
    stringBuffer.append(TEXT_15);
    }
    stringBuffer.append(TEXT_16);
     for (Iterator i = genModel.getAllGenPackagesWithClassifiers().iterator(); i.hasNext(); ) { GenPackage genPackage = (GenPackage)i.next(); 
    stringBuffer.append(TEXT_17);
    stringBuffer.append(genPackage.getNSURI());
    stringBuffer.append(TEXT_18);
    stringBuffer.append(genPackage.getQualifiedItemProviderAdapterFactoryClassName());
    stringBuffer.append(TEXT_19);
    for (ListIterator j = genPackage.getProviderSupportedTypes().listIterator(); j.hasNext(); ) {
    stringBuffer.append(TEXT_20);
    stringBuffer.append((j.hasPrevious()? " " : "\"") + j.next() + (j.hasNext() ? "" : "\""));
    }
    stringBuffer.append(TEXT_21);
    }
    if (genModel.sameModelEditProject()) {
     for (Iterator i = genModel.getAllGenPackagesWithClassifiers().iterator(); i.hasNext(); ) { GenPackage genPackage = (GenPackage)i.next(); 
    stringBuffer.append(TEXT_22);
    stringBuffer.append(genPackage.getNSURI());
    stringBuffer.append(TEXT_23);
    stringBuffer.append(genPackage.getQualifiedPackageInterfaceName());
    stringBuffer.append(TEXT_24);
    if (!genModel.hasLocalGenModel()) {
    stringBuffer.append(TEXT_25);
    } else {
    stringBuffer.append(TEXT_26);
    stringBuffer.append(genModel.getRelativeGenModelLocation());
    stringBuffer.append(TEXT_27);
    }
    stringBuffer.append(TEXT_28);
    if (genPackage.getResource() != GenResourceKind.NONE_LITERAL) {
    stringBuffer.append(TEXT_29);
    stringBuffer.append(genPackage.getPrefix().toLowerCase());
    stringBuffer.append(TEXT_30);
    stringBuffer.append(genPackage.getQualifiedResourceFactoryClassName());
    stringBuffer.append(TEXT_31);
    }
    }
    }
    stringBuffer.append(TEXT_32);
    stringBuffer.append(TEXT_33);
    return stringBuffer.toString();
  }
}
