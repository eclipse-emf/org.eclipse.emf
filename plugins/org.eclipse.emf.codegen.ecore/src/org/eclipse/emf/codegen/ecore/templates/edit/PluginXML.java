package org.eclipse.emf.codegen.ecore.templates.edit;

import java.util.Iterator;

import org.eclipse.emf.codegen.ecore.genmodel.GenModel;
import org.eclipse.emf.codegen.ecore.genmodel.GenPackage;
import org.eclipse.emf.codegen.ecore.genmodel.GenResourceKind;

public class PluginXML
{
  protected final String NL = System.getProperties().getProperty("line.separator");
  protected final String TEXT_1 = "<?xml version=\"1.0\"?>" + NL + "" + NL + "<!--" + NL;
  protected final String TEXT_2 = "/**" + NL + " * <copyright>" + NL + " * </copyright>" + NL + " *" + NL + " * ";
  protected final String TEXT_3 = "W%" + NL + " * @version ";
  protected final String TEXT_4 = "I% ";
  protected final String TEXT_5 = "H%" + NL + " */" + NL + "-->" + NL + "" + NL + "<plugin" + NL + "    name = \"%pluginName\"" + NL + "    id = \"";
  protected final String TEXT_6 = "\"" + NL + "    version = \"1.0.0\"" + NL + "    provider-name = \"%providerName\"" + NL + "    class = \"";
  protected final String TEXT_7 = "$Implementation\">" + NL + "" + NL + "  <requires>";
  protected final String TEXT_8 = NL + "    <import plugin=\"";
  protected final String TEXT_9 = "\" export=\"true\"/>";
  protected final String TEXT_10 = NL + "  </requires>" + NL + "" + NL + "  <runtime>";
  protected final String TEXT_11 = NL + "    <library name=\"runtime/";
  protected final String TEXT_12 = ".jar\">";
  protected final String TEXT_13 = NL + "    <library name=\"runtime/\">";
  protected final String TEXT_14 = NL + "      <export name=\"*\"/>" + NL + "    </library>" + NL + "  </runtime>";
  protected final String TEXT_15 = NL + NL + "  <extension point=\"org.eclipse.emf.ecore.generated_package\">" + NL + "    <package" + NL + "       uri = \"";
  protected final String TEXT_16 = "\"" + NL + "       class = \"";
  protected final String TEXT_17 = "\" />" + NL + "  </extension>";
  protected final String TEXT_18 = NL + NL + "  <extension point=\"org.eclipse.emf.ecore.extension_parser\">" + NL + "    <parser" + NL + "       type=\"";
  protected final String TEXT_19 = "\"" + NL + "       class=\"";
  protected final String TEXT_20 = "\" />" + NL + "  </extension>";
  protected final String TEXT_21 = NL + NL + "</plugin>" + NL;
  protected final String TEXT_22 = NL;

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
 *
 * $Id: PluginXML.java,v 1.1 2004/03/06 17:31:31 marcelop Exp $
 */

    GenModel genModel = (GenModel)argument;
    stringBuffer.append(TEXT_1);
    stringBuffer.append(TEXT_2);
    stringBuffer.append("%");
    stringBuffer.append(TEXT_3);
    stringBuffer.append("%");
    stringBuffer.append(TEXT_4);
    stringBuffer.append("%");
    stringBuffer.append(TEXT_5);
    stringBuffer.append(genModel.getEditPluginID());
    stringBuffer.append(TEXT_6);
    stringBuffer.append(genModel.getQualifiedEditPluginClassName());
    stringBuffer.append(TEXT_7);
    for (Iterator j=genModel.getEditRequiredPlugins().iterator(); j.hasNext();) {
    stringBuffer.append(TEXT_8);
    stringBuffer.append((String)j.next());
    stringBuffer.append(TEXT_9);
    }
    stringBuffer.append(TEXT_10);
    if (genModel.isRuntimeJar()) {
    stringBuffer.append(TEXT_11);
    stringBuffer.append(genModel.getEditPluginID());
    stringBuffer.append(TEXT_12);
    } else {
    stringBuffer.append(TEXT_13);
    }
    stringBuffer.append(TEXT_14);
    if (genModel.sameModelEditProject()) {
     for (Iterator i = genModel.getAllGenPackagesWithClassifiers().iterator(); i.hasNext(); ) { GenPackage genPackage = (GenPackage)i.next(); 
    stringBuffer.append(TEXT_15);
    stringBuffer.append(genPackage.getNSURI());
    stringBuffer.append(TEXT_16);
    stringBuffer.append(genPackage.getQualifiedPackageInterfaceName());
    stringBuffer.append(TEXT_17);
    if (genPackage.getResource() != GenResourceKind.NONE_LITERAL) {
    stringBuffer.append(TEXT_18);
    stringBuffer.append(genPackage.getPrefix().toLowerCase());
    stringBuffer.append(TEXT_19);
    stringBuffer.append(genPackage.getQualifiedResourceFactoryClassName());
    stringBuffer.append(TEXT_20);
    }
    }
    }
    stringBuffer.append(TEXT_21);
    stringBuffer.append(TEXT_22);
    return stringBuffer.toString();
  }
}
