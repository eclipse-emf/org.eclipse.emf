package org.eclipse.emf.codegen.ecore.templates.model.tests;

import java.util.*;
import org.eclipse.emf.codegen.ecore.genmodel.*;

public class ManifestMF
{
  protected static String nl;
  public static synchronized ManifestMF create(String lineSeparator)
  {
    nl = lineSeparator;
    ManifestMF result = new ManifestMF();
    nl = null;
    return result;
  }

  protected final String NL = nl == null ? (System.getProperties().getProperty("line.separator")) : nl;
  protected final String TEXT_1 = "Manifest-Version: 1.0" + NL + "Bundle-ManifestVersion: 2" + NL + "Bundle-Name: %pluginName" + NL + "Bundle-SymbolicName: ";
  protected final String TEXT_2 = "; singleton:=true" + NL + "Bundle-Version: 1.0.0" + NL + "Bundle-ClassPath: ";
  protected final String TEXT_3 = ".jar";
  protected final String TEXT_4 = ".";
  protected final String TEXT_5 = NL + "Bundle-Vendor: %providerName" + NL + "Bundle-Localization: plugin";
  protected final String TEXT_6 = NL + "Export-Package: ";
  protected final String TEXT_7 = ",";
  protected final String TEXT_8 = NL + " ";
  protected final String TEXT_9 = NL + "Require-Bundle: ";
  protected final String TEXT_10 = ";visibility:=reexport";
  protected final String TEXT_11 = ",";
  protected final String TEXT_12 = NL + " ";
  protected final String TEXT_13 = ";visibility:=reexport";
  protected final String TEXT_14 = NL + "Eclipse-LazyStart: true";
  protected final String TEXT_15 = NL;

  public String generate(Object argument)
  {
    StringBuffer stringBuffer = new StringBuffer();
    
/**
 * <copyright>
 *
 * Copyright (c) 2005 IBM Corporation and others.
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
    stringBuffer.append(genModel.getTestsPluginID());
    stringBuffer.append(TEXT_2);
    if (genModel.isRuntimeJar()) {
    stringBuffer.append(genModel.getTestsPluginID());
    stringBuffer.append(TEXT_3);
    }else{
    stringBuffer.append(TEXT_4);
    }
    stringBuffer.append(TEXT_5);
    Iterator packagesIterator = genModel.getTestsQualifiedPackageNames().iterator(); if (packagesIterator.hasNext()) { String pack = (String)packagesIterator.next();
    stringBuffer.append(TEXT_6);
    stringBuffer.append(pack);
    while(packagesIterator.hasNext()) { pack = (String)packagesIterator.next();
    stringBuffer.append(TEXT_7);
    stringBuffer.append(TEXT_8);
    stringBuffer.append(pack);
    }
    }
    Iterator requiredPluginIterator = genModel.getTestsRequiredPlugins().iterator(); if (requiredPluginIterator.hasNext()) { String pluginID = (String)requiredPluginIterator.next();
    stringBuffer.append(TEXT_9);
    stringBuffer.append(pluginID);
    if (!pluginID.startsWith("org.eclipse.core.runtime")){
    stringBuffer.append(TEXT_10);
    } while(requiredPluginIterator.hasNext()) { pluginID = (String)requiredPluginIterator.next();
    stringBuffer.append(TEXT_11);
    stringBuffer.append(TEXT_12);
    stringBuffer.append(pluginID);
    if (!pluginID.startsWith("org.eclipse.core.runtime")){
    stringBuffer.append(TEXT_13);
    }}
    }
    stringBuffer.append(TEXT_14);
    stringBuffer.append(TEXT_15);
    return stringBuffer.toString();
  }
}
