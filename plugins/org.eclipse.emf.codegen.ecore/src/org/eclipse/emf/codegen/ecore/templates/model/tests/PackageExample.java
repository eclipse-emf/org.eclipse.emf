package org.eclipse.emf.codegen.ecore.templates.model.tests;

import org.eclipse.emf.codegen.ecore.genmodel.*;

public class PackageExample
{
  protected static String nl;
  public static synchronized PackageExample create(String lineSeparator)
  {
    nl = lineSeparator;
    PackageExample result = new PackageExample();
    nl = null;
    return result;
  }

  protected final String NL = nl == null ? (System.getProperties().getProperty("line.separator")) : nl;
  protected final String TEXT_1 = "";
  protected final String TEXT_2 = "/**" + NL + " * <copyright>" + NL + " * </copyright>" + NL + " *" + NL + " * ";
  protected final String TEXT_3 = "Id";
  protected final String TEXT_4 = NL + " */" + NL + "package ";
  protected final String TEXT_5 = ";" + NL;
  protected final String TEXT_6 = NL + NL + "/**" + NL + " * <!-- begin-user-doc -->" + NL + " * A sample utility for the '<em><b>";
  protected final String TEXT_7 = "</b></em>' package." + NL + " * <!-- end-user-doc -->" + NL + " * @generated" + NL + " */" + NL + "public class ";
  protected final String TEXT_8 = NL + "{";
  protected final String TEXT_9 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */" + NL + "\tpublic static final ";
  protected final String TEXT_10 = " copyright = \"";
  protected final String TEXT_11 = "\";";
  protected final String TEXT_12 = NL + "\t";
  protected final String TEXT_13 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * Load all the argument file paths or URIs as instances of the model." + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @param args the file paths or URIs." + NL + "\t * @generated" + NL + "\t */" + NL + "\tpublic static void main(String[] args)" + NL + "\t{" + NL + "\t\t// Create a resource set to hold the resources." + NL + "\t\t//" + NL + "\t\t";
  protected final String TEXT_14 = " resourceSet = new ";
  protected final String TEXT_15 = "();" + NL + "\t\t" + NL + "\t\t// Register the appropriate resource factory to handle all file extentions." + NL + "\t\t//" + NL + "\t\tresourceSet.getResourceFactoryRegistry().getExtensionToFactoryMap().put" + NL + "\t\t\t(Resource.Factory.Registry.DEFAULT_EXTENSION, " + NL + "\t\t\t new ";
  protected final String TEXT_16 = "());" + NL + "" + NL + "\t\t// Register the package to ensure it is available during loading." + NL + "\t\t//" + NL + "\t\tresourceSet.getPackageRegistry().put" + NL + "\t\t\t(";
  protected final String TEXT_17 = ".eNS_URI, " + NL + "\t\t\t ";
  protected final String TEXT_18 = ".eINSTANCE);" + NL + "        " + NL + "\t\t// If there are no arguments, emit an appropriate usage message." + NL + "\t\t//" + NL + "\t\tif (args.length == 0)" + NL + "\t\t{";
  protected final String TEXT_19 = NL + "\t\t\tSystem.out.println(\"Enter a list of file paths or URIs that have content like this:\");" + NL + "\t\t\ttry" + NL + "\t\t\t{" + NL + "\t\t\t\t";
  protected final String TEXT_20 = " resource = resourceSet.createResource(";
  protected final String TEXT_21 = ".createURI(\"http:///My.";
  protected final String TEXT_22 = "\"));";
  protected final String TEXT_23 = NL + "\t\t\t\t";
  protected final String TEXT_24 = " documentRoot = ";
  protected final String TEXT_25 = ".eINSTANCE.create";
  protected final String TEXT_26 = "();" + NL + "\t\t\t\t";
  protected final String TEXT_27 = " root = ";
  protected final String TEXT_28 = ".eINSTANCE.create";
  protected final String TEXT_29 = "();" + NL + "\t\t\t\tdocumentRoot.set";
  protected final String TEXT_30 = "(root);" + NL + "\t\t\t\tresource.getContents().add(documentRoot);";
  protected final String TEXT_31 = NL + "\t\t\t\t";
  protected final String TEXT_32 = " root = ";
  protected final String TEXT_33 = ".eINSTANCE.create";
  protected final String TEXT_34 = "();" + NL + "\t\t\t\tresource.getContents().add(root);";
  protected final String TEXT_35 = NL + "\t\t\t\tresource.save(";
  protected final String TEXT_36 = ".out, null);" + NL + "\t\t\t}" + NL + "\t\t\tcatch (";
  protected final String TEXT_37 = " exception) " + NL + "\t\t\t{" + NL + "\t\t\t\texception.printStackTrace();" + NL + "\t\t\t}";
  protected final String TEXT_38 = NL + "\t\t\tSystem.out.println(\"Enter a list of file paths or URIs\");";
  protected final String TEXT_39 = NL + "\t\t}" + NL + "\t\telse" + NL + "\t\t{" + NL + "\t\t\t// Iterate over all the arguments." + NL + "\t\t\t//" + NL + "\t\t\tfor (int i = 0; i < args.length; ++i)" + NL + "\t\t\t{" + NL + "\t\t\t\t// Construct the URI for the instance file." + NL + "\t\t\t\t// The argument is treated as a file path only if it denotes an existing file." + NL + "\t\t\t\t// Otherwise, it's directly treated as a URL." + NL + "\t\t\t\t//" + NL + "\t\t\t\t";
  protected final String TEXT_40 = " file = new ";
  protected final String TEXT_41 = "(args[0]);" + NL + "\t\t\t\t";
  protected final String TEXT_42 = " uri = file.isFile() ? ";
  protected final String TEXT_43 = ".createFileURI(file.getAbsolutePath()): URI.createURI(args[0]);" + NL + "" + NL + "\t\t\t\ttry" + NL + "\t\t\t\t{" + NL + "\t\t\t\t\t// Demand load resource for this file." + NL + "\t\t\t\t\t//" + NL + "\t\t\t\t\t";
  protected final String TEXT_44 = " resource = resourceSet.getResource(uri, true);" + NL + "\t\t\t\t\tSystem.out.println(\"Loaded \" + uri);" + NL + "" + NL + "\t\t\t\t\t// Validate the contents of the loaded resource." + NL + "\t\t\t\t\t//" + NL + "\t\t\t\t\tfor (";
  protected final String TEXT_45 = " j = resource.getContents().iterator(); j.hasNext(); )" + NL + "\t\t\t\t\t{" + NL + "\t\t\t\t\t\t";
  protected final String TEXT_46 = " eObject = (";
  protected final String TEXT_47 = ")j.next();" + NL + "\t\t\t\t\t\t";
  protected final String TEXT_48 = " diagnostic = ";
  protected final String TEXT_49 = ".INSTANCE.validate(eObject);" + NL + "\t\t\t\t\t\tif (diagnostic.getSeverity() != Diagnostic.OK)" + NL + "\t\t\t\t\t\t{" + NL + "\t\t\t\t\t\t\tprintDiagnostic(diagnostic, \"\");" + NL + "\t\t\t\t\t\t}" + NL + "\t\t\t\t\t}" + NL + "\t\t\t\t}" + NL + "\t\t\t\tcatch (";
  protected final String TEXT_50 = " exception) " + NL + "\t\t\t\t{" + NL + "\t\t\t\t\tSystem.out.println(\"Problem loading \" + uri);" + NL + "\t\t\t\t\texception.printStackTrace();" + NL + "\t\t\t\t}" + NL + "\t\t\t}" + NL + "\t\t}" + NL + "\t}" + NL + "\t" + NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * Prints diagnostics with indentation." + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @param diagnostic the diagnostic to print." + NL + "\t * @param indent the indentation for printing." + NL + "\t * @generated" + NL + "\t */" + NL + "\tprotected static void printDiagnostic(Diagnostic diagnostic, String indent)" + NL + "\t{" + NL + "\t\tSystem.out.print(indent);" + NL + "\t\tSystem.out.println(diagnostic.getMessage());" + NL + "\t\tfor (Iterator i = diagnostic.getChildren().iterator(); i.hasNext(); )" + NL + "\t\t{" + NL + "\t\t\tprintDiagnostic((Diagnostic)i.next(), indent + \"  \");" + NL + "\t\t}" + NL + "\t}" + NL + "" + NL + "} //";
  protected final String TEXT_51 = NL;

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

    GenPackage genPackage = (GenPackage)argument; GenModel genModel = genPackage.getGenModel();
    
String _System = genModel.getImportedName("java.lang.System");
String _String = genModel.getImportedName("java.lang.String");
String _RuntimeException = genModel.getImportedName("java.lang.RuntimeException");
String _File = genModel.getImportedName("java.io.File");
String _Iterator = genModel.getImportedName("java.util.Iterator");
String _Diagnostic = genModel.getImportedName("org.eclipse.emf.common.util.Diagnostic");
String _URI = genModel.getImportedName("org.eclipse.emf.common.util.URI");
String _EObject = genModel.getImportedName("org.eclipse.emf.ecore.EObject");
String _Diagnostician = genModel.getImportedName("org.eclipse.emf.ecore.util.Diagnostician");
String _Resource = genModel.getImportedName("org.eclipse.emf.ecore.resource.Resource");
String _ResourceSet = genModel.getImportedName("org.eclipse.emf.ecore.resource.ResourceSet");
String _ResourceSetImpl = genModel.getImportedName("org.eclipse.emf.ecore.resource.impl.ResourceSetImpl");

    stringBuffer.append(TEXT_1);
    stringBuffer.append(TEXT_2);
    stringBuffer.append("$");
    stringBuffer.append(TEXT_3);
    stringBuffer.append("$");
    stringBuffer.append(TEXT_4);
    stringBuffer.append(genPackage.getTestsPackageName());
    stringBuffer.append(TEXT_5);
    genModel.markImportLocation(stringBuffer);
    stringBuffer.append(TEXT_6);
    stringBuffer.append(genPackage.getPackageName());
    stringBuffer.append(TEXT_7);
    stringBuffer.append(genPackage.getExampleClassName());
    stringBuffer.append(TEXT_8);
    if (genModel.getCopyrightText() != null) {
    stringBuffer.append(TEXT_9);
    stringBuffer.append(_String);
    stringBuffer.append(TEXT_10);
    stringBuffer.append(genModel.getCopyrightText());
    stringBuffer.append(TEXT_11);
    stringBuffer.append(genModel.getNonNLS());
    stringBuffer.append(TEXT_12);
    }
    stringBuffer.append(TEXT_13);
    stringBuffer.append(_ResourceSet);
    stringBuffer.append(TEXT_14);
    stringBuffer.append(_ResourceSetImpl);
    stringBuffer.append(TEXT_15);
    stringBuffer.append(genPackage.getResource() == GenResourceKind.NONE_LITERAL ? genModel.getImportedName("org.eclipse.emf.ecore.xmi.impl.XMIResourceFactoryImpl") : genPackage.getImportedResourceFactoryClassName());
    stringBuffer.append(TEXT_16);
    stringBuffer.append(genPackage.getImportedPackageInterfaceName());
    stringBuffer.append(TEXT_17);
    stringBuffer.append(genPackage.getImportedPackageInterfaceName());
    stringBuffer.append(TEXT_18);
    if (genPackage.getRootClass() != null) { GenClass rootClass = genPackage.getRootClass();
    stringBuffer.append(TEXT_19);
    stringBuffer.append(_Resource);
    stringBuffer.append(TEXT_20);
    stringBuffer.append(_URI);
    stringBuffer.append(TEXT_21);
    stringBuffer.append(genPackage.getPrefix().toLowerCase());
    stringBuffer.append(TEXT_22);
    if (genPackage.getRootFeature() != null) { GenFeature rootFeature = rootFeature = genPackage.getRootFeature(); GenClass documentRoot = rootFeature.getGenClass();
    stringBuffer.append(TEXT_23);
    stringBuffer.append(documentRoot.getImportedInterfaceName());
    stringBuffer.append(TEXT_24);
    stringBuffer.append(genPackage.getImportedFactoryInterfaceName());
    stringBuffer.append(TEXT_25);
    stringBuffer.append(documentRoot.getName());
    stringBuffer.append(TEXT_26);
    stringBuffer.append(rootClass.getImportedInterfaceName());
    stringBuffer.append(TEXT_27);
    stringBuffer.append(genPackage.getImportedFactoryInterfaceName());
    stringBuffer.append(TEXT_28);
    stringBuffer.append(rootClass.getName());
    stringBuffer.append(TEXT_29);
    stringBuffer.append(rootFeature.getCapName());
    stringBuffer.append(TEXT_30);
    } else {
    stringBuffer.append(TEXT_31);
    stringBuffer.append(rootClass.getImportedInterfaceName());
    stringBuffer.append(TEXT_32);
    stringBuffer.append(genPackage.getImportedFactoryInterfaceName());
    stringBuffer.append(TEXT_33);
    stringBuffer.append(rootClass.getName());
    stringBuffer.append(TEXT_34);
    }
    stringBuffer.append(TEXT_35);
    stringBuffer.append(_System);
    stringBuffer.append(TEXT_36);
    stringBuffer.append(genModel.getImportedName("java.io.IOException"));
    stringBuffer.append(TEXT_37);
    } else {
    stringBuffer.append(TEXT_38);
    }
    stringBuffer.append(TEXT_39);
    stringBuffer.append(_File);
    stringBuffer.append(TEXT_40);
    stringBuffer.append(_File);
    stringBuffer.append(TEXT_41);
    stringBuffer.append(_URI);
    stringBuffer.append(TEXT_42);
    stringBuffer.append(_URI);
    stringBuffer.append(TEXT_43);
    stringBuffer.append(_Resource);
    stringBuffer.append(TEXT_44);
    stringBuffer.append(_Iterator);
    stringBuffer.append(TEXT_45);
    stringBuffer.append(_EObject);
    stringBuffer.append(TEXT_46);
    stringBuffer.append(_EObject);
    stringBuffer.append(TEXT_47);
    stringBuffer.append(_Diagnostic);
    stringBuffer.append(TEXT_48);
    stringBuffer.append(_Diagnostician);
    stringBuffer.append(TEXT_49);
    stringBuffer.append(_RuntimeException);
    stringBuffer.append(TEXT_50);
    stringBuffer.append(genPackage.getExampleClassName());
    genModel.emitSortedImports();
    stringBuffer.append(TEXT_51);
    return stringBuffer.toString();
  }
}
