package org.eclipse.emf.codegen.ecore.templates.editor;

import java.util.*;
import org.eclipse.emf.codegen.ecore.genmodel.*;

public class EntryPoint
{
  protected static String nl;
  public static synchronized EntryPoint create(String lineSeparator)
  {
    nl = lineSeparator;
    EntryPoint result = new EntryPoint();
    nl = null;
    return result;
  }

  public final String NL = nl == null ? (System.getProperties().getProperty("line.separator")) : nl;
  protected final String TEXT_1 = "";
  protected final String TEXT_2 = "/**";
  protected final String TEXT_3 = NL + " * ";
  protected final String TEXT_4 = NL + " * <copyright>" + NL + " * </copyright>" + NL + " *" + NL + " * ";
  protected final String TEXT_5 = "Id";
  protected final String TEXT_6 = NL + " */" + NL + "package ";
  protected final String TEXT_7 = ";" + NL + "" + NL + "import org.eclipse.emf.edit.ui.EditorEntryPoint;";
  protected final String TEXT_8 = NL + NL + "/**" + NL + " * This is the entry point." + NL + " * <!-- begin-user-doc -->" + NL + " * <!-- end-user-doc -->" + NL + " * @generated" + NL + " */" + NL + "public class ";
  protected final String TEXT_9 = " extends EditorEntryPoint" + NL + "{";
  protected final String TEXT_10 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */" + NL + "\tpublic static final ";
  protected final String TEXT_11 = " copyright = ";
  protected final String TEXT_12 = ";";
  protected final String TEXT_13 = NL + "\t";
  protected final String TEXT_14 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */" + NL + "\t@Override" + NL + "\tpublic void onModuleLoad()" + NL + "\t{" + NL + "\t\tsuper.onModuleLoad();" + NL + "\t}" + NL + "" + NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */" + NL + "\t@Override" + NL + "\tprotected void registerPackages(";
  protected final String TEXT_15 = ".Registry packageRegistry)" + NL + "\t{";
  protected final String TEXT_16 = NL + "\t\tpackageRegistry.put(";
  protected final String TEXT_17 = ".eNS_URI, ";
  protected final String TEXT_18 = ".eINSTANCE);";
  protected final String TEXT_19 = NL + "\t}" + NL + "" + NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */" + NL + "\t@Override" + NL + "\tprotected void configureItemProviderAdapterFactories(";
  protected final String TEXT_20 = " adapterFactory)" + NL + "\t{";
  protected final String TEXT_21 = NL + "\t\tadapterFactory.addAdapterFactory(new ";
  protected final String TEXT_22 = "());";
  protected final String TEXT_23 = NL + "\t}" + NL + "" + NL + "}";
  protected final String TEXT_24 = NL;

  public String generate(Object argument)
  {
    final StringBuffer stringBuffer = new StringBuffer();
    
/**
 * <copyright>
 *
 * Copyright (c) 20010 Ed Merks and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *   Ed Merks - Initial API and implementation
 *
 * </copyright>
 */

    GenModel genModel = (GenModel)argument; /* Trick to import java.util.* without warnings */Iterator.class.getName();
    stringBuffer.append(TEXT_1);
    stringBuffer.append(TEXT_2);
    {GenBase copyrightHolder = argument instanceof GenBase ? (GenBase)argument : argument instanceof Object[] && ((Object[])argument)[0] instanceof GenBase ? (GenBase)((Object[])argument)[0] : null;
    if (copyrightHolder != null && copyrightHolder.hasCopyright()) {
    stringBuffer.append(TEXT_3);
    stringBuffer.append(copyrightHolder.getCopyright(copyrightHolder.getGenModel().getIndentation(stringBuffer)));
    } else {
    stringBuffer.append(TEXT_4);
    stringBuffer.append("$");
    stringBuffer.append(TEXT_5);
    stringBuffer.append("$");
    }}
    stringBuffer.append(TEXT_6);
    stringBuffer.append(genModel.getEditorPluginPackageName());
    stringBuffer.append(TEXT_7);
    genModel.markImportLocation(stringBuffer);
    stringBuffer.append(TEXT_8);
    stringBuffer.append(genModel.getEditorEntryPointClassName());
    stringBuffer.append(TEXT_9);
    if (genModel.hasCopyrightField()) {
    stringBuffer.append(TEXT_10);
    stringBuffer.append(genModel.getImportedName("java.lang.String"));
    stringBuffer.append(TEXT_11);
    stringBuffer.append(genModel.getCopyrightFieldLiteral());
    stringBuffer.append(TEXT_12);
    stringBuffer.append(genModel.getNonNLS());
    stringBuffer.append(TEXT_13);
    }
    stringBuffer.append(TEXT_14);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.EPackage"));
    stringBuffer.append(TEXT_15);
    for (GenPackage genPackage : genModel.getAllGenAndUsedGenPackagesWithClassifiers()) {
    stringBuffer.append(TEXT_16);
    stringBuffer.append(genPackage.getImportedPackageInterfaceName());
    stringBuffer.append(TEXT_17);
    stringBuffer.append(genPackage.getImportedPackageInterfaceName());
    stringBuffer.append(TEXT_18);
    }
    stringBuffer.append(TEXT_19);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.edit.provider.ComposedAdapterFactory"));
    stringBuffer.append(TEXT_20);
    for (GenPackage genPackage : genModel.getAllGenAndUsedGenPackagesWithClassifiers()) {
    stringBuffer.append(TEXT_21);
    stringBuffer.append(genPackage.getImportedItemProviderAdapterFactoryClassName());
    stringBuffer.append(TEXT_22);
    }
    stringBuffer.append(TEXT_23);
    genModel.emitSortedImports();
    stringBuffer.append(TEXT_24);
    return stringBuffer.toString();
  }
}
