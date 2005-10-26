package org.eclipse.emf.codegen.ecore.templates.model;

import org.eclipse.emf.codegen.ecore.genmodel.*;

public class ResourceFactoryClass
{
  protected static String nl;
  public static synchronized ResourceFactoryClass create(String lineSeparator)
  {
    nl = lineSeparator;
    ResourceFactoryClass result = new ResourceFactoryClass();
    nl = null;
    return result;
  }

  protected final String NL = nl == null ? (System.getProperties().getProperty("line.separator")) : nl;
  protected final String TEXT_1 = "";
  protected final String TEXT_2 = "/**" + NL + " * <copyright>" + NL + " * </copyright>" + NL + " *" + NL + " * ";
  protected final String TEXT_3 = "Id";
  protected final String TEXT_4 = NL + " */" + NL + "package ";
  protected final String TEXT_5 = ";" + NL;
  protected final String TEXT_6 = NL + NL + "/**" + NL + " * <!-- begin-user-doc -->" + NL + " * The <b>Resource Factory</b> associated with the package." + NL + " * <!-- end-user-doc -->" + NL + " * @see ";
  protected final String TEXT_7 = NL + " * @generated" + NL + " */" + NL + "public class ";
  protected final String TEXT_8 = " extends ";
  protected final String TEXT_9 = NL + "{";
  protected final String TEXT_10 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */" + NL + "\tpublic static final ";
  protected final String TEXT_11 = " copyright = \"";
  protected final String TEXT_12 = "\";";
  protected final String TEXT_13 = NL;
  protected final String TEXT_14 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */" + NL + "\tprotected ";
  protected final String TEXT_15 = " extendedMetaData;" + NL;
  protected final String TEXT_16 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */" + NL + "\tprotected ";
  protected final String TEXT_17 = " xmlMap = new ";
  protected final String TEXT_18 = "();" + NL;
  protected final String TEXT_19 = NL + "\t/**" + NL + "\t * Creates an instance of the resource factory." + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */" + NL + "\tpublic ";
  protected final String TEXT_20 = "()" + NL + "\t{" + NL + "\t\tsuper();";
  protected final String TEXT_21 = NL + "\t\textendedMetaData = new ";
  protected final String TEXT_22 = "(new ";
  protected final String TEXT_23 = "(";
  protected final String TEXT_24 = ".Registry.INSTANCE));" + NL + "\t\textendedMetaData.putPackage(null, ";
  protected final String TEXT_25 = ".eINSTANCE);";
  protected final String TEXT_26 = NL + "\t\txmlMap.setNoNamespacePackage(";
  protected final String TEXT_27 = ".eINSTANCE);";
  protected final String TEXT_28 = NL + "\t}" + NL + "" + NL + "\t/**" + NL + "\t * Creates an instance of the resource." + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */" + NL + "\tpublic Resource createResource(URI uri)" + NL + "\t{";
  protected final String TEXT_29 = NL + "\t\t";
  protected final String TEXT_30 = " result = new ";
  protected final String TEXT_31 = "(uri);" + NL + "\t\tresult.getDefaultSaveOptions().put(";
  protected final String TEXT_32 = ".OPTION_EXTENDED_META_DATA, ";
  protected final String TEXT_33 = "Boolean.TRUE";
  protected final String TEXT_34 = "extendedMetaData";
  protected final String TEXT_35 = ");" + NL + "\t\tresult.getDefaultLoadOptions().put(";
  protected final String TEXT_36 = ".OPTION_EXTENDED_META_DATA, ";
  protected final String TEXT_37 = "Boolean.TRUE";
  protected final String TEXT_38 = "extendedMetaData";
  protected final String TEXT_39 = ");" + NL + "" + NL + "\t\tresult.getDefaultSaveOptions().put(";
  protected final String TEXT_40 = ".OPTION_SCHEMA_LOCATION, Boolean.TRUE);" + NL + "" + NL + "\t\tresult.getDefaultLoadOptions().put(";
  protected final String TEXT_41 = ".OPTION_USE_ENCODED_ATTRIBUTE_STYLE, Boolean.TRUE);" + NL + "\t\tresult.getDefaultSaveOptions().put(";
  protected final String TEXT_42 = ".OPTION_USE_ENCODED_ATTRIBUTE_STYLE, Boolean.TRUE);" + NL + "" + NL + "\t\tresult.getDefaultLoadOptions().put(";
  protected final String TEXT_43 = ".OPTION_USE_LEXICAL_HANDLER, Boolean.TRUE);";
  protected final String TEXT_44 = NL + "\t\t";
  protected final String TEXT_45 = " result = new ";
  protected final String TEXT_46 = "(uri);" + NL + "\t\tresult.getDefaultSaveOptions().put(";
  protected final String TEXT_47 = ".OPTION_XML_MAP, xmlMap);" + NL + "\t\tresult.getDefaultLoadOptions().put(";
  protected final String TEXT_48 = ".OPTION_XML_MAP, xmlMap);";
  protected final String TEXT_49 = NL + "\t\tResource result = new ";
  protected final String TEXT_50 = "(uri);";
  protected final String TEXT_51 = NL + "\t\treturn result;";
  protected final String TEXT_52 = NL + "\t}" + NL + "" + NL + "} //";
  protected final String TEXT_53 = NL;

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

    GenPackage genPackage = (GenPackage)argument; GenModel genModel=genPackage.getGenModel();
    stringBuffer.append(TEXT_1);
    stringBuffer.append(TEXT_2);
    stringBuffer.append("$");
    stringBuffer.append(TEXT_3);
    stringBuffer.append("$");
    stringBuffer.append(TEXT_4);
    stringBuffer.append(genPackage.getUtilitiesPackageName());
    stringBuffer.append(TEXT_5);
    genModel.getImportedName("org.eclipse.emf.common.util.URI");
    genModel.getImportedName("org.eclipse.emf.ecore.resource.Resource");
    genModel.markImportLocation(stringBuffer);
    stringBuffer.append(TEXT_6);
    stringBuffer.append(genPackage.getQualifiedResourceClassName());
    stringBuffer.append(TEXT_7);
    stringBuffer.append(genPackage.getResourceFactoryClassName());
    stringBuffer.append(TEXT_8);
    stringBuffer.append(genPackage.getImportedResourceFactoryBaseClassName());
    stringBuffer.append(TEXT_9);
    if (genModel.getCopyrightText() != null) {
    stringBuffer.append(TEXT_10);
    stringBuffer.append(genModel.getImportedName("java.lang.String"));
    stringBuffer.append(TEXT_11);
    stringBuffer.append(genModel.getCopyrightText());
    stringBuffer.append(TEXT_12);
    stringBuffer.append(genModel.getNonNLS());
    stringBuffer.append(TEXT_13);
    }
    if (genPackage.hasExtendedMetaData() && !genPackage.hasTargetNamespace()) {
    stringBuffer.append(TEXT_14);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.ExtendedMetaData"));
    stringBuffer.append(TEXT_15);
    } else if (genPackage.hasXMLMap()) {
    stringBuffer.append(TEXT_16);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.xmi.XMLResource$XMLMap"));
    stringBuffer.append(TEXT_17);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.xmi.impl.XMLMapImpl"));
    stringBuffer.append(TEXT_18);
    }
    stringBuffer.append(TEXT_19);
    stringBuffer.append(genPackage.getResourceFactoryClassName());
    stringBuffer.append(TEXT_20);
    if (genPackage.hasExtendedMetaData() && !genPackage.hasTargetNamespace()) {
    stringBuffer.append(TEXT_21);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.BasicExtendedMetaData"));
    stringBuffer.append(TEXT_22);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.impl.EPackageRegistryImpl"));
    stringBuffer.append(TEXT_23);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.EPackage"));
    stringBuffer.append(TEXT_24);
    stringBuffer.append(genPackage.getImportedPackageInterfaceName());
    stringBuffer.append(TEXT_25);
    } else if (genPackage.hasXMLMap() && !genPackage.hasTargetNamespace()) {
    stringBuffer.append(TEXT_26);
    stringBuffer.append(genPackage.getImportedPackageInterfaceName());
    stringBuffer.append(TEXT_27);
    }
    stringBuffer.append(TEXT_28);
    if (genPackage.hasExtendedMetaData()) {
    stringBuffer.append(TEXT_29);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.xmi.XMLResource"));
    stringBuffer.append(TEXT_30);
    stringBuffer.append(genPackage.getResourceClassName());
    stringBuffer.append(TEXT_31);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.xmi.XMLResource"));
    stringBuffer.append(TEXT_32);
    if (genPackage.hasTargetNamespace()){
    stringBuffer.append(TEXT_33);
    }else{
    stringBuffer.append(TEXT_34);
    }
    stringBuffer.append(TEXT_35);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.xmi.XMLResource"));
    stringBuffer.append(TEXT_36);
    if (genPackage.hasTargetNamespace()){
    stringBuffer.append(TEXT_37);
    }else{
    stringBuffer.append(TEXT_38);
    }
    stringBuffer.append(TEXT_39);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.xmi.XMLResource"));
    stringBuffer.append(TEXT_40);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.xmi.XMLResource"));
    stringBuffer.append(TEXT_41);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.xmi.XMLResource"));
    stringBuffer.append(TEXT_42);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.xmi.XMLResource"));
    stringBuffer.append(TEXT_43);
    } else if (genPackage.hasXMLMap()) {
    stringBuffer.append(TEXT_44);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.xmi.XMLResource"));
    stringBuffer.append(TEXT_45);
    stringBuffer.append(genPackage.getResourceClassName());
    stringBuffer.append(TEXT_46);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.xmi.XMLResource"));
    stringBuffer.append(TEXT_47);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.xmi.XMLResource"));
    stringBuffer.append(TEXT_48);
    } else {
    stringBuffer.append(TEXT_49);
    stringBuffer.append(genPackage.getResourceClassName());
    stringBuffer.append(TEXT_50);
    }
    stringBuffer.append(TEXT_51);
    //ResourceFactoryClass/createResource.override.javajetinc
    stringBuffer.append(TEXT_52);
    stringBuffer.append(genPackage.getResourceFactoryClassName());
    genModel.emitSortedImports();
    stringBuffer.append(TEXT_53);
    return stringBuffer.toString();
  }
}
