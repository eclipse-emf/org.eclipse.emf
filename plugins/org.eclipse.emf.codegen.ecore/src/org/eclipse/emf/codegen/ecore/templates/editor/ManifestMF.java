package org.eclipse.emf.codegen.ecore.templates.editor;

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
  protected final String TEXT_7 = NL + "Bundle-Activator: ";
  protected final String TEXT_8 = "$Implementation";
  protected final String TEXT_9 = NL + "Bundle-Vendor: ";
  protected final String TEXT_10 = NL + "Bundle-Localization: ";
  protected final String TEXT_11 = NL + "Bundle-RequiredExecutionEnvironment: ";
  protected final String TEXT_12 = NL + "Export-Package: ";
  protected final String TEXT_13 = ",";
  protected final String TEXT_14 = NL + " ";
  protected final String TEXT_15 = NL + "Require-Bundle: ";
  protected final String TEXT_16 = ";visibility:=reexport";
  protected final String TEXT_17 = NL + "Import-Package: org.eclipse.emf.common.ui," + NL + " org.eclipse.emf.common.ui.action," + NL + " org.eclipse.emf.common.ui.dialogs," + NL + " org.eclipse.emf.common.ui.editor," + NL + " org.eclipse.emf.common.ui.viewer," + NL + " org.eclipse.emf.edit.ui," + NL + " org.eclipse.emf.edit.ui.action," + NL + " org.eclipse.emf.edit.ui.celleditor," + NL + " org.eclipse.emf.edit.ui.dnd," + NL + " org.eclipse.emf.edit.ui.provider," + NL + " org.eclipse.emf.edit.ui.util," + NL + " org.eclipse.emf.edit.ui.view," + NL + " org.eclipse.jface.action," + NL + " org.eclipse.jface.dialogs," + NL + " org.eclipse.jface.operation," + NL + " org.eclipse.jface.text," + NL + " org.eclipse.jface.util," + NL + " org.eclipse.jface.viewers," + NL + " org.eclipse.jface.window," + NL + " org.eclipse.jface.wizard," + NL + " org.eclipse.swt," + NL + " org.eclipse.swt.custom," + NL + " org.eclipse.swt.dnd," + NL + " org.eclipse.swt.events," + NL + " org.eclipse.swt.graphics," + NL + " org.eclipse.swt.layout," + NL + " org.eclipse.swt.widgets," + NL + " org.eclipse.ui;ui.workbench=\"split\"," + NL + " org.eclipse.ui.actions;ui.workbench=\"split\"," + NL + " org.eclipse.ui.application," + NL + " org.eclipse.ui.part;ui.workbench=\"split\"," + NL + " org.eclipse.ui.plugin," + NL + " org.eclipse.ui.views," + NL + " org.eclipse.ui.views.contentoutline," + NL + " org.eclipse.ui.views.properties;ui.views=\"split\"";
  protected final String TEXT_18 = NL + "Eclipse-LazyStart: true";
  protected final String TEXT_19 = NL + "Bundle-ActivationPolicy: lazy";
  protected final String TEXT_20 = NL;

  public String generate(Object argument)
  {
    final StringBuffer stringBuffer = new StringBuffer();
    
/**
 * Copyright (c) 2005-2010 IBM Corporation and others.
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
    stringBuffer.append(genModel.getEditorBundleNameKey());
    stringBuffer.append(TEXT_2);
    stringBuffer.append(genModel.getEditorPluginID());
    stringBuffer.append(TEXT_3);
    stringBuffer.append(genModel.getEditorPluginID());
    stringBuffer.append(TEXT_4);
    if (genModel.isRuntimeJar()) {
    stringBuffer.append(genModel.getEditorPluginID());
    stringBuffer.append(TEXT_5);
    }else{
    stringBuffer.append(TEXT_6);
    }
    if (genModel.getRuntimePlatform() != GenRuntimePlatform.GWT) {
    stringBuffer.append(TEXT_7);
    stringBuffer.append(genModel.getQualifiedEditorPluginClassName());
    stringBuffer.append(TEXT_8);
    }
    stringBuffer.append(TEXT_9);
    stringBuffer.append(genModel.getEditorBundleVendorKey());
    stringBuffer.append(TEXT_10);
    stringBuffer.append(genModel.getEditorBundleLocalization());
    if (genModel.getComplianceLevel().ordinal() >= GenJDKLevel.JDK50_LITERAL.ordinal()) {
    stringBuffer.append(TEXT_11);
    stringBuffer.append(genModel.getComplianceLevel().getExecutionEnvironment());
    }
    Iterator<String> packagesIterator = genModel.getEditorQualifiedPackageNames().iterator(); if (packagesIterator.hasNext()) { String pack = packagesIterator.next();
    stringBuffer.append(TEXT_12);
    stringBuffer.append(pack);
    while(packagesIterator.hasNext()) { pack = packagesIterator.next();
    stringBuffer.append(TEXT_13);
    stringBuffer.append(TEXT_14);
    stringBuffer.append(pack);
    }
    }
    Iterator<String> requiredPluginIterator = genModel.getEditorRequiredPlugins().iterator(); if (requiredPluginIterator.hasNext()) { String pluginID = requiredPluginIterator.next();
    stringBuffer.append(TEXT_15);
    stringBuffer.append(pluginID);
    if (!pluginID.startsWith("org.eclipse.core.runtime")){
    stringBuffer.append(TEXT_16);
    } while(requiredPluginIterator.hasNext()) { pluginID = requiredPluginIterator.next();
    stringBuffer.append(TEXT_13);
    stringBuffer.append(TEXT_14);
    stringBuffer.append(pluginID);
    if (!pluginID.startsWith("org.eclipse.core.runtime") && !pluginID.equals("org.eclipse.jface.text") && !pluginID.equals("org.eclipse.xtext.xbase.lib") && !pluginID.equals("org.eclipse.emf.ecore.xcore.lib")) {
    stringBuffer.append(TEXT_16);
    }}
    }
    if (genModel.isRichAjaxPlatform()) {
    stringBuffer.append(TEXT_17);
    }
    if (genModel.getRuntimeVersion() == GenRuntimeVersion.EMF22 || genModel.getRuntimeVersion() == GenRuntimeVersion.EMF23) {
    stringBuffer.append(TEXT_18);
    }
    stringBuffer.append(TEXT_19);
    stringBuffer.append(TEXT_20);
    return stringBuffer.toString();
  }
}
