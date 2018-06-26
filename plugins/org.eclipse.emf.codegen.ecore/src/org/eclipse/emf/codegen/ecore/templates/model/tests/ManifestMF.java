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

  public final String NL = nl == null ? (System.getProperties().getProperty("line.separator")) : nl;
  protected final String TEXT_1 = "Manifest-Version: 1.0" + NL + "Bundle-ManifestVersion: 2" + NL + "Bundle-Name: ";
  protected final String TEXT_2 = NL + "Bundle-SymbolicName: ";
  protected final String TEXT_3 = ";singleton:=true" + NL + "Automatic-Module-Name: ";
  protected final String TEXT_4 = NL + "Bundle-Version: 1.0.0.qualifier" + NL + "Bundle-ClassPath: ";
  protected final String TEXT_5 = ".jar";
  protected final String TEXT_6 = ".";
  protected final String TEXT_7 = NL + "Bundle-Vendor: ";
  protected final String TEXT_8 = NL + "Bundle-Localization: ";
  protected final String TEXT_9 = NL + "Bundle-RequiredExecutionEnvironment: J2SE-1.5";
  protected final String TEXT_10 = NL + "Bundle-RequiredExecutionEnvironment: JavaSE-1.6";
  protected final String TEXT_11 = NL + "Bundle-RequiredExecutionEnvironment: JavaSE-1.7";
  protected final String TEXT_12 = NL + "Bundle-RequiredExecutionEnvironment: JavaSE-1.8";
  protected final String TEXT_13 = NL + "Bundle-RequiredExecutionEnvironment: JavaSE-9";
  protected final String TEXT_14 = NL + "Bundle-RequiredExecutionEnvironment: JavaSE-10";
  protected final String TEXT_15 = NL + "Export-Package: ";
  protected final String TEXT_16 = ",";
  protected final String TEXT_17 = NL + " ";
  protected final String TEXT_18 = NL + "Require-Bundle: ";
  protected final String TEXT_19 = ";visibility:=reexport";
  protected final String TEXT_20 = NL + "Eclipse-LazyStart: true";
  protected final String TEXT_21 = NL + "Bundle-ActivationPolicy: lazy";
  protected final String TEXT_22 = NL;

  public String generate(Object argument)
  {
    final StringBuffer stringBuffer = new StringBuffer();
    
/**
 * Copyright (c) 2005 IBM Corporation and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 * 
 * Contributors: 
 *   IBM - Initial API and implementation
 */

    GenModel genModel = (GenModel)argument;
    stringBuffer.append(TEXT_1);
    stringBuffer.append(genModel.getTestsBundleNameKey());
    stringBuffer.append(TEXT_2);
    stringBuffer.append(genModel.getTestsPluginID());
    stringBuffer.append(TEXT_3);
    stringBuffer.append(genModel.getTestsPluginID());
    stringBuffer.append(TEXT_4);
    if (genModel.isRuntimeJar()) {
    stringBuffer.append(genModel.getTestsPluginID());
    stringBuffer.append(TEXT_5);
    }else{
    stringBuffer.append(TEXT_6);
    }
    stringBuffer.append(TEXT_7);
    stringBuffer.append(genModel.getTestsBundleVendorKey());
    stringBuffer.append(TEXT_8);
    stringBuffer.append(genModel.getTestsBundleLocalization());
    if (genModel.getComplianceLevel() == GenJDKLevel.JDK50_LITERAL) {
    stringBuffer.append(TEXT_9);
    } else if (genModel.getComplianceLevel() == GenJDKLevel.JDK60_LITERAL) {
    stringBuffer.append(TEXT_10);
    } else if (genModel.getComplianceLevel() == GenJDKLevel.JDK70_LITERAL) {
    stringBuffer.append(TEXT_11);
    } else if (genModel.getComplianceLevel() == GenJDKLevel.JDK80_LITERAL) {
    stringBuffer.append(TEXT_12);
    } else if (genModel.getComplianceLevel() == GenJDKLevel.JDK90_LITERAL) {
    stringBuffer.append(TEXT_13);
    } else if (genModel.getComplianceLevel() == GenJDKLevel.JDK100_LITERAL) {
    stringBuffer.append(TEXT_14);
    }
    Iterator<String> packagesIterator = genModel.getTestsQualifiedPackageNames().iterator(); if (packagesIterator.hasNext()) { String pack = packagesIterator.next();
    stringBuffer.append(TEXT_15);
    stringBuffer.append(pack);
    while(packagesIterator.hasNext()) { pack = packagesIterator.next();
    stringBuffer.append(TEXT_16);
    stringBuffer.append(TEXT_17);
    stringBuffer.append(pack);
    }
    }
    Iterator<String> requiredPluginIterator = genModel.getTestsRequiredPlugins().iterator(); if (requiredPluginIterator.hasNext()) { String pluginID = requiredPluginIterator.next();
    stringBuffer.append(TEXT_18);
    stringBuffer.append(pluginID);
    if (!pluginID.startsWith("org.eclipse.core.runtime")){
    stringBuffer.append(TEXT_19);
    } while(requiredPluginIterator.hasNext()) { pluginID = requiredPluginIterator.next();
    stringBuffer.append(TEXT_16);
    stringBuffer.append(TEXT_17);
    stringBuffer.append(pluginID);
    if (!pluginID.startsWith("org.eclipse.core.runtime") && !pluginID.equals("org.eclipse.xtext.xbase.lib") && !pluginID.equals("org.eclipse.emf.ecore.xcore.lib")) {
    stringBuffer.append(TEXT_19);
    }}
    }
    if (genModel.getRuntimeVersion() == GenRuntimeVersion.EMF22 || genModel.getRuntimeVersion() == GenRuntimeVersion.EMF23) {
    stringBuffer.append(TEXT_20);
    }
    stringBuffer.append(TEXT_21);
    stringBuffer.append(TEXT_22);
    return stringBuffer.toString();
  }
}
