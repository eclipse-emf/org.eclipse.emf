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
  protected final String TEXT_1 = "Manifest-Version: 1.0" + NL + "Bundle-ManifestVersion: 2" + NL + "Bundle-Name: %pluginName" + NL + "Bundle-SymbolicName: ";
  protected final String TEXT_2 = ";singleton:=true" + NL + "Bundle-Version: 1.0.0.qualifier" + NL + "Bundle-ClassPath: ";
  protected final String TEXT_3 = ".jar";
  protected final String TEXT_4 = ".";
  protected final String TEXT_5 = NL + "Bundle-Activator: ";
  protected final String TEXT_6 = "$Implementation";
  protected final String TEXT_7 = NL + "Bundle-Vendor: %providerName" + NL + "Bundle-Localization: plugin";
  protected final String TEXT_8 = NL + "Bundle-RequiredExecutionEnvironment: J2SE-1.5";
  protected final String TEXT_9 = NL + "Bundle-RequiredExecutionEnvironment: JavaSE-1.6";
  protected final String TEXT_10 = NL + "Export-Package: ";
  protected final String TEXT_11 = ",";
  protected final String TEXT_12 = NL + " ";
  protected final String TEXT_13 = NL + "Require-Bundle: ";
  protected final String TEXT_14 = ";visibility:=reexport";
  protected final String TEXT_15 = ",";
  protected final String TEXT_16 = NL + " ";
  protected final String TEXT_17 = ";visibility:=reexport";
  protected final String TEXT_18 = NL + "Import-Package: org.eclipse.emf.common.ui," + NL + " org.eclipse.emf.common.ui.action," + NL + " org.eclipse.emf.common.ui.dialogs," + NL + " org.eclipse.emf.common.ui.editor," + NL + " org.eclipse.emf.common.ui.viewer," + NL + " org.eclipse.emf.edit.ui," + NL + " org.eclipse.emf.edit.ui.action," + NL + " org.eclipse.emf.edit.ui.celleditor," + NL + " org.eclipse.emf.edit.ui.dnd," + NL + " org.eclipse.emf.edit.ui.provider," + NL + " org.eclipse.emf.edit.ui.util," + NL + " org.eclipse.emf.edit.ui.view," + NL + " org.eclipse.jface.action," + NL + " org.eclipse.jface.dialogs," + NL + " org.eclipse.jface.operation," + NL + " org.eclipse.jface.viewers," + NL + " org.eclipse.jface.window," + NL + " org.eclipse.jface.wizard," + NL + " org.eclipse.swt," + NL + " org.eclipse.swt.custom," + NL + " org.eclipse.swt.dnd," + NL + " org.eclipse.swt.events," + NL + " org.eclipse.swt.graphics," + NL + " org.eclipse.swt.layout," + NL + " org.eclipse.swt.widgets," + NL + " org.eclipse.ui;ui.workbench=\"split\"," + NL + " org.eclipse.ui.actions;ui.workbench=\"split\"," + NL + " org.eclipse.ui.application," + NL + " org.eclipse.ui.part;ui.workbench=\"split\"," + NL + " org.eclipse.ui.views," + NL + " org.eclipse.ui.views.contentoutline," + NL + " org.eclipse.ui.views.properties;ui.views=\"split\"";
  protected final String TEXT_19 = NL + "Eclipse-LazyStart: true";
  protected final String TEXT_20 = NL + "Bundle-ActivationPolicy: lazy";
  protected final String TEXT_21 = NL;

  public String generate(Object argument)
  {
    final StringBuffer stringBuffer = new StringBuffer();
    
/**
 * <copyright>
 *
 * Copyright (c) 2005-2010 IBM Corporation and others.
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
    stringBuffer.append(genModel.getEditorPluginID());
    stringBuffer.append(TEXT_2);
    if (genModel.isRuntimeJar()) {
    stringBuffer.append(genModel.getEditorPluginID());
    stringBuffer.append(TEXT_3);
    }else{
    stringBuffer.append(TEXT_4);
    }
    if (genModel.getRuntimePlatform() != GenRuntimePlatform.GWT) {
    stringBuffer.append(TEXT_5);
    stringBuffer.append(genModel.getQualifiedEditorPluginClassName());
    stringBuffer.append(TEXT_6);
    }
    stringBuffer.append(TEXT_7);
    if (genModel.getComplianceLevel() == GenJDKLevel.JDK50_LITERAL) {
    stringBuffer.append(TEXT_8);
    } else if (genModel.getComplianceLevel() == GenJDKLevel.JDK60_LITERAL) {
    stringBuffer.append(TEXT_9);
    }
    Iterator<String> packagesIterator = genModel.getEditorQualifiedPackageNames().iterator(); if (packagesIterator.hasNext()) { String pack = packagesIterator.next();
    stringBuffer.append(TEXT_10);
    stringBuffer.append(pack);
    while(packagesIterator.hasNext()) { pack = packagesIterator.next();
    stringBuffer.append(TEXT_11);
    stringBuffer.append(TEXT_12);
    stringBuffer.append(pack);
    }
    }
    Iterator<String> requiredPluginIterator = genModel.getEditorRequiredPlugins().iterator(); if (requiredPluginIterator.hasNext()) { String pluginID = requiredPluginIterator.next();
    stringBuffer.append(TEXT_13);
    stringBuffer.append(pluginID);
    if (!pluginID.startsWith("org.eclipse.core.runtime")){
    stringBuffer.append(TEXT_14);
    } while(requiredPluginIterator.hasNext()) { pluginID = requiredPluginIterator.next();
    stringBuffer.append(TEXT_15);
    stringBuffer.append(TEXT_16);
    stringBuffer.append(pluginID);
    if (!pluginID.startsWith("org.eclipse.core.runtime")){
    stringBuffer.append(TEXT_17);
    }}
    }
    if (genModel.isRichAjaxPlatform()) {
    stringBuffer.append(TEXT_18);
    }
    if (genModel.getRuntimeVersion() == GenRuntimeVersion.EMF22 || genModel.getRuntimeVersion() == GenRuntimeVersion.EMF23) {
    stringBuffer.append(TEXT_19);
    }
    stringBuffer.append(TEXT_20);
    stringBuffer.append(TEXT_21);
    return stringBuffer.toString();
  }
}
