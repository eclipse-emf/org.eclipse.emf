package org.eclipse.emf.codegen.ecore.templates.editor;

import java.util.*;
import org.eclipse.emf.codegen.ecore.genmodel.*;

public class Plugin
{
  protected final String NL = System.getProperties().getProperty("line.separator");
  protected final String TEXT_1 = "";
  protected final String TEXT_2 = "/**" + NL + " * <copyright>" + NL + " * </copyright>" + NL + " *" + NL + " * ";
  protected final String TEXT_3 = "Id";
  protected final String TEXT_4 = NL + " */" + NL + "package ";
  protected final String TEXT_5 = ";" + NL;
  protected final String TEXT_6 = NL + NL + "/**" + NL + " * This is the central singleton for the ";
  protected final String TEXT_7 = " editor plugin." + NL + " * <!-- begin-user-doc -->" + NL + " * <!-- end-user-doc -->" + NL + " * @generated" + NL + " */" + NL + "public final class ";
  protected final String TEXT_8 = " extends EMFPlugin" + NL + "{";
  protected final String TEXT_9 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */" + NL + "\tpublic static final ";
  protected final String TEXT_10 = " copyright = \"";
  protected final String TEXT_11 = "\";";
  protected final String TEXT_12 = NL;
  protected final String TEXT_13 = NL + "\t/**" + NL + "\t * Keep track of the singleton." + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */" + NL + "\tpublic static final ";
  protected final String TEXT_14 = " INSTANCE = new ";
  protected final String TEXT_15 = "();" + NL + "" + NL + "\t/**" + NL + "\t * Keep track of the singleton." + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */" + NL + "\tprivate static Implementation plugin;" + NL + "" + NL + "\t/**" + NL + "\t * Create the instance." + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */" + NL + "\tpublic ";
  protected final String TEXT_16 = "()" + NL + "\t{" + NL + "\t\tsuper" + NL + "\t\t  (new ResourceLocator [] " + NL + "\t\t   {";
  protected final String TEXT_17 = NL + "\t\t     ";
  protected final String TEXT_18 = ".INSTANCE,";
  protected final String TEXT_19 = NL + "\t\t   });" + NL + "\t}" + NL + "" + NL + "\t/**" + NL + "\t * Returns the singleton instance of the Eclipse plugin." + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @return the singleton instance." + NL + "\t * @generated" + NL + "\t */" + NL + "\tpublic ResourceLocator getPluginResourceLocator()" + NL + "\t{" + NL + "\t\treturn plugin;" + NL + "\t}" + NL + "" + NL + "\t/**" + NL + "\t * Returns the singleton instance of the Eclipse plugin." + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @return the singleton instance." + NL + "\t * @generated" + NL + "\t */" + NL + "\tpublic static Implementation getPlugin()" + NL + "\t{" + NL + "\t\treturn plugin;" + NL + "\t}" + NL + "" + NL + "\t/**" + NL + "\t * The actual implementation of the Eclipse <b>Plugin</b>." + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */" + NL + "\tpublic static class Implementation extends EclipsePlugin" + NL + "\t{" + NL + "\t\t/**" + NL + "\t\t * Creates an instance." + NL + "\t\t * <!-- begin-user-doc -->" + NL + "\t\t * <!-- end-user-doc -->";
  protected final String TEXT_20 = NL + "\t\t * @param descriptor the description of the plugin.";
  protected final String TEXT_21 = NL + "\t\t * @generated" + NL + "\t\t */" + NL + "\t\tpublic Implementation(";
  protected final String TEXT_22 = " descriptor";
  protected final String TEXT_23 = ")" + NL + "\t\t{" + NL + "\t\t\tsuper(";
  protected final String TEXT_24 = "descriptor";
  protected final String TEXT_25 = ");" + NL + "" + NL + "\t\t\t// Remember the static instance." + NL + "\t\t\t//" + NL + "\t\t\tplugin = this;" + NL + "\t\t}" + NL + "\t}" + NL + "" + NL + "}";
  protected final String TEXT_26 = NL;

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
    stringBuffer.append("$");
    stringBuffer.append(TEXT_3);
    stringBuffer.append("$");
    stringBuffer.append(TEXT_4);
    stringBuffer.append(genModel.getEditorPluginPackageName());
    stringBuffer.append(TEXT_5);
    genModel.addImport("org.eclipse.emf.common.EMFPlugin");
    genModel.addImport("org.eclipse.emf.common.util.ResourceLocator");
    genModel.markImportLocation(stringBuffer);
    stringBuffer.append(TEXT_6);
    stringBuffer.append(genModel.getModelName());
    stringBuffer.append(TEXT_7);
    stringBuffer.append(genModel.getEditorPluginClassName());
    stringBuffer.append(TEXT_8);
    if (genModel.getCopyrightText() != null) {
    stringBuffer.append(TEXT_9);
    stringBuffer.append(genModel.getImportedName("java.lang.String"));
    stringBuffer.append(TEXT_10);
    stringBuffer.append(genModel.getCopyrightText());
    stringBuffer.append(TEXT_11);
    stringBuffer.append(genModel.getNonNLS());
    stringBuffer.append(TEXT_12);
    }
    stringBuffer.append(TEXT_13);
    stringBuffer.append(genModel.getEditorPluginClassName());
    stringBuffer.append(TEXT_14);
    stringBuffer.append(genModel.getEditorPluginClassName());
    stringBuffer.append(TEXT_15);
    stringBuffer.append(genModel.getEditorPluginClassName());
    stringBuffer.append(TEXT_16);
    for (Iterator i = genModel.getEditResourceDelegateImportedPluginClassNames().iterator(); i.hasNext(); ) { String pluginClassName = (String)i.next(); 
    stringBuffer.append(TEXT_17);
    stringBuffer.append(pluginClassName);
    stringBuffer.append(TEXT_18);
    }
    stringBuffer.append(TEXT_19);
    if (genModel.needsRuntimeCompatibility()) {
    stringBuffer.append(TEXT_20);
    }
    stringBuffer.append(TEXT_21);
    if (genModel.needsRuntimeCompatibility()) {
    stringBuffer.append(genModel.getImportedName("org.eclipse.core.runtime.IPluginDescriptor"));
    stringBuffer.append(TEXT_22);
    }
    stringBuffer.append(TEXT_23);
    if (genModel.needsRuntimeCompatibility()) {
    stringBuffer.append(TEXT_24);
    }
    stringBuffer.append(TEXT_25);
    genModel.emitSortedImports();
    stringBuffer.append(TEXT_26);
    return stringBuffer.toString();
  }
}
