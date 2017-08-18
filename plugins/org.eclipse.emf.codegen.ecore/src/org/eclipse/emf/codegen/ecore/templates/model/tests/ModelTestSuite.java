package org.eclipse.emf.codegen.ecore.templates.model.tests;

import java.util.*;
import org.eclipse.emf.codegen.ecore.genmodel.*;

public class ModelTestSuite
{
  protected static String nl;
  public static synchronized ModelTestSuite create(String lineSeparator)
  {
    nl = lineSeparator;
    ModelTestSuite result = new ModelTestSuite();
    nl = null;
    return result;
  }

  public final String NL = nl == null ? (System.getProperties().getProperty("line.separator")) : nl;
  protected final String TEXT_1 = "";
  protected final String TEXT_2 = "/**";
  protected final String TEXT_3 = NL + " * ";
  protected final String TEXT_4 = NL + " */" + NL + "package ";
  protected final String TEXT_5 = ";" + NL;
  protected final String TEXT_6 = NL + NL + "/**" + NL + " * <!-- begin-user-doc -->" + NL + " * A test suite for the '<em><b>";
  protected final String TEXT_7 = "</b></em>' model." + NL + " * <!-- end-user-doc -->";
  protected final String TEXT_8 = NL + " * ";
  protected final String TEXT_9 = NL + " * @generated" + NL + " */";
  protected final String TEXT_10 = NL + "@Deprecated";
  protected final String TEXT_11 = NL + "@SuppressWarnings(\"deprecation\")";
  protected final String TEXT_12 = NL + "public class ";
  protected final String TEXT_13 = " extends ";
  protected final String TEXT_14 = NL + "{";
  protected final String TEXT_15 = NL + NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */" + NL + "\tpublic static final ";
  protected final String TEXT_16 = " copyright = ";
  protected final String TEXT_17 = ";";
  protected final String TEXT_18 = NL + NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */" + NL + "\tpublic static void main(String[] args)" + NL + "\t{" + NL + "\t\t";
  protected final String TEXT_19 = ".run(suite());" + NL + "\t}" + NL + "" + NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */" + NL + "\tpublic static ";
  protected final String TEXT_20 = " suite()" + NL + "\t{" + NL + "\t\t";
  protected final String TEXT_21 = " suite = new ";
  protected final String TEXT_22 = "(\"";
  protected final String TEXT_23 = " Tests\");";
  protected final String TEXT_24 = NL + "\t\tsuite.addTest(";
  protected final String TEXT_25 = ".suite());";
  protected final String TEXT_26 = NL + "\t\treturn suite;" + NL + "\t}" + NL + "" + NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */" + NL + "\tpublic ";
  protected final String TEXT_27 = "(String name)" + NL + "\t{" + NL + "\t\tsuper(name);" + NL + "\t}" + NL + "" + NL + "} //";
  protected final String TEXT_28 = NL;

  public String generate(Object argument)
  {
    final StringBuffer stringBuffer = new StringBuffer();
    
/**
 * Copyright (c) 2005 IBM Corporation and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *   IBM - Initial API and implementation
 */

    GenModel genModel = (GenModel)argument; /* Trick to import java.util.* without warnings */Iterator.class.getName();
    final boolean isJDK50 = genModel.getComplianceLevel().getValue() >= GenJDKLevel.JDK50;
    stringBuffer.append(TEXT_1);
    stringBuffer.append(TEXT_2);
    {GenBase copyrightHolder = argument instanceof GenBase ? (GenBase)argument : argument instanceof Object[] && ((Object[])argument)[0] instanceof GenBase ? (GenBase)((Object[])argument)[0] : null;
    if (copyrightHolder != null && copyrightHolder.hasCopyright()) {
    stringBuffer.append(TEXT_3);
    stringBuffer.append(copyrightHolder.getCopyright(copyrightHolder.getGenModel().getIndentation(stringBuffer)));
    }}
    stringBuffer.append(TEXT_4);
    stringBuffer.append(genModel.getTestSuitePackageName());
    stringBuffer.append(TEXT_5);
    genModel.markImportLocation(stringBuffer);
    stringBuffer.append(TEXT_6);
    stringBuffer.append(genModel.getModelName());
    stringBuffer.append(TEXT_7);
    if (genModel.hasAPITags()) {
    stringBuffer.append(TEXT_8);
    stringBuffer.append(genModel.getAPITags(genModel.getIndentation(stringBuffer)));
    }
    stringBuffer.append(TEXT_9);
    if (isJDK50 && genModel.hasAPIDeprecatedTag()) {
    stringBuffer.append(TEXT_10);
    } else if (isJDK50) {
  boolean needsSuppressDeprecation = false;
  LOOP: for (GenPackage genPackage : genModel.getAllGenPackagesWithClassifiers()) { if (genPackage.hasTests()) { for (GenClass genClass : genPackage.getGenClasses()) { if (!genClass.isExternalInterface() && !genClass.isAbstract() && genClass.hasTests() && genClass.hasImplicitAPIDeprecatedTag()) { needsSuppressDeprecation = true; break LOOP; }}}}
  if (needsSuppressDeprecation) {
    stringBuffer.append(TEXT_11);
    }}
    stringBuffer.append(TEXT_12);
    stringBuffer.append(genModel.getTestSuiteClassName());
    stringBuffer.append(TEXT_13);
    stringBuffer.append(genModel.getImportedName("junit.framework.TestSuite"));
    stringBuffer.append(TEXT_14);
    if (genModel.hasCopyrightField()) {
    stringBuffer.append(TEXT_15);
    stringBuffer.append(genModel.getImportedName("java.lang.String"));
    stringBuffer.append(TEXT_16);
    stringBuffer.append(genModel.getCopyrightFieldLiteral());
    stringBuffer.append(TEXT_17);
    stringBuffer.append(genModel.getNonNLS());
    }
    stringBuffer.append(TEXT_18);
    stringBuffer.append(genModel.getImportedName("junit.textui.TestRunner"));
    stringBuffer.append(TEXT_19);
    stringBuffer.append(genModel.getImportedName("junit.framework.Test"));
    stringBuffer.append(TEXT_20);
    stringBuffer.append(genModel.getImportedName("junit.framework.TestSuite"));
    stringBuffer.append(TEXT_21);
    stringBuffer.append(genModel.getTestSuiteClassName());
    stringBuffer.append(TEXT_22);
    stringBuffer.append(genModel.getModelName());
    stringBuffer.append(TEXT_23);
    stringBuffer.append(genModel.getNonNLS());
    for (GenPackage genPackage : genModel.getAllGenPackagesWithClassifiers()) {
    if (genPackage.hasTests()) {
    stringBuffer.append(TEXT_24);
    stringBuffer.append(genModel.getImportedName(genPackage.getImportedTestSuiteClassName()));
    stringBuffer.append(TEXT_25);
    }
    }
    stringBuffer.append(TEXT_26);
    stringBuffer.append(genModel.getTestSuiteClassName());
    stringBuffer.append(TEXT_27);
    stringBuffer.append(genModel.getTestSuiteClassName());
    genModel.emitSortedImports();
    stringBuffer.append(TEXT_28);
    return stringBuffer.toString();
  }
}
