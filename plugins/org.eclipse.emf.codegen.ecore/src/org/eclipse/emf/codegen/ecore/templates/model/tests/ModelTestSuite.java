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

  protected final String NL = nl == null ? (System.getProperties().getProperty("line.separator")) : nl;
  protected final String TEXT_1 = "";
  protected final String TEXT_2 = "/**" + NL + " * <copyright>" + NL + " * </copyright>" + NL + " *" + NL + " * ";
  protected final String TEXT_3 = "Id";
  protected final String TEXT_4 = NL + " */" + NL + "package ";
  protected final String TEXT_5 = ";" + NL;
  protected final String TEXT_6 = NL + NL + "/**" + NL + " * <!-- begin-user-doc -->" + NL + " * A test suite for the '<em><b>";
  protected final String TEXT_7 = "</b></em>' model." + NL + " * <!-- end-user-doc -->" + NL + " * @generated" + NL + " */" + NL + "public class ";
  protected final String TEXT_8 = " extends ";
  protected final String TEXT_9 = NL + "{";
  protected final String TEXT_10 = NL + NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */" + NL + "\tpublic static final ";
  protected final String TEXT_11 = " copyright = \"";
  protected final String TEXT_12 = "\";";
  protected final String TEXT_13 = NL + NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */" + NL + "\tpublic static void main(String[] args)" + NL + "\t{" + NL + "\t\t";
  protected final String TEXT_14 = ".run(suite());" + NL + "\t}" + NL + "" + NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */" + NL + "\tpublic static ";
  protected final String TEXT_15 = " suite()" + NL + "\t{" + NL + "\t\t";
  protected final String TEXT_16 = " suite = new ";
  protected final String TEXT_17 = "(\"";
  protected final String TEXT_18 = " Tests\");";
  protected final String TEXT_19 = NL + "\t\tsuite.addTest(";
  protected final String TEXT_20 = ".suite());";
  protected final String TEXT_21 = NL + "\t\treturn suite;" + NL + "\t}" + NL + "" + NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */" + NL + "\tpublic ";
  protected final String TEXT_22 = "(String name)" + NL + "\t{" + NL + "\t\tsuper(name);" + NL + "\t}" + NL + "" + NL + "} //";
  protected final String TEXT_23 = NL;

  public String generate(Object argument)
  {
    StringBuffer stringBuffer = new StringBuffer();
    
/**
 * <copyright>
 *
 * Copyright (c) 2005 IBM Corporation and others.
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
    stringBuffer.append(genModel.getTestSuitePackageName());
    stringBuffer.append(TEXT_5);
    genModel.markImportLocation(stringBuffer);
    stringBuffer.append(TEXT_6);
    stringBuffer.append(genModel.getModelName());
    stringBuffer.append(TEXT_7);
    stringBuffer.append(genModel.getTestSuiteClassName());
    stringBuffer.append(TEXT_8);
    stringBuffer.append(genModel.getImportedName("junit.framework.TestSuite"));
    stringBuffer.append(TEXT_9);
    if (genModel.getCopyrightText() != null) {
    stringBuffer.append(TEXT_10);
    stringBuffer.append(genModel.getImportedName("java.lang.String"));
    stringBuffer.append(TEXT_11);
    stringBuffer.append(genModel.getCopyrightText());
    stringBuffer.append(TEXT_12);
    stringBuffer.append(genModel.getNonNLS());
    }
    stringBuffer.append(TEXT_13);
    stringBuffer.append(genModel.getImportedName("junit.textui.TestRunner"));
    stringBuffer.append(TEXT_14);
    stringBuffer.append(genModel.getImportedName("junit.framework.Test"));
    stringBuffer.append(TEXT_15);
    stringBuffer.append(genModel.getImportedName("junit.framework.TestSuite"));
    stringBuffer.append(TEXT_16);
    stringBuffer.append(genModel.getTestSuiteClassName());
    stringBuffer.append(TEXT_17);
    stringBuffer.append(genModel.getModelName());
    stringBuffer.append(TEXT_18);
    stringBuffer.append(genModel.getNonNLS());
    for (Iterator genPackages = genModel.getAllGenPackagesWithClassifiers().iterator(); genPackages.hasNext();) { GenPackage genPackage = (GenPackage)genPackages.next();
    stringBuffer.append(TEXT_19);
    stringBuffer.append(genModel.getImportedName(genPackage.getImportedTestSuiteClassName()));
    stringBuffer.append(TEXT_20);
    }
    stringBuffer.append(TEXT_21);
    stringBuffer.append(genModel.getTestSuiteClassName());
    stringBuffer.append(TEXT_22);
    stringBuffer.append(genModel.getTestSuiteClassName());
    genModel.emitSortedImports();
    stringBuffer.append(TEXT_23);
    return stringBuffer.toString();
  }
}
