package org.eclipse.emf.codegen.ecore.templates.model.tests;

import java.util.*;
import org.eclipse.emf.codegen.ecore.genmodel.*;

public class TestCase
{
  protected final String NL = System.getProperties().getProperty("line.separator");
  protected final String TEXT_1 = "";
  protected final String TEXT_2 = "/**" + NL + " * <copyright>" + NL + " * </copyright>" + NL + " *" + NL + " * ";
  protected final String TEXT_3 = "Id";
  protected final String TEXT_4 = NL + " */" + NL + "package ";
  protected final String TEXT_5 = ";" + NL;
  protected final String TEXT_6 = NL + NL + "/**" + NL + " * <!-- begin-user-doc -->" + NL + " * A test case for the model object '<em><b>";
  protected final String TEXT_7 = "</b></em>'." + NL + " * <!-- end-user-doc -->";
  protected final String TEXT_8 = NL + " * <p>" + NL + " * The following features are tested:" + NL + " * <ul>";
  protected final String TEXT_9 = NL + " *   <li>{@link ";
  protected final String TEXT_10 = "#";
  protected final String TEXT_11 = "() <em>";
  protected final String TEXT_12 = "</em>}</li>";
  protected final String TEXT_13 = NL + " * </ul>" + NL + " * </p>";
  protected final String TEXT_14 = NL + " * <p>" + NL + " * The following operations are tested:" + NL + " * <ul>";
  protected final String TEXT_15 = NL + " *   <li>{@link ";
  protected final String TEXT_16 = "#";
  protected final String TEXT_17 = "(";
  protected final String TEXT_18 = ") <em>";
  protected final String TEXT_19 = "</em>}</li>";
  protected final String TEXT_20 = NL + " * </ul>" + NL + " * </p>";
  protected final String TEXT_21 = NL + " * @generated" + NL + " */" + NL + "public";
  protected final String TEXT_22 = " abstract";
  protected final String TEXT_23 = " class ";
  protected final String TEXT_24 = " extends ";
  protected final String TEXT_25 = NL + "{";
  protected final String TEXT_26 = NL + NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */" + NL + "\tpublic static final ";
  protected final String TEXT_27 = " copyright = \"";
  protected final String TEXT_28 = "\";";
  protected final String TEXT_29 = NL + NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */" + NL + "\tpublic static final ";
  protected final String TEXT_30 = " mofDriverNumber = \"";
  protected final String TEXT_31 = "\";";
  protected final String TEXT_32 = NL + NL + "\t/**" + NL + "\t * The fixture for this ";
  protected final String TEXT_33 = " test case." + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */" + NL + "\tprotected ";
  protected final String TEXT_34 = " fixture = null;";
  protected final String TEXT_35 = NL + NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */" + NL + "\tpublic static void main(String[] args)" + NL + "\t{" + NL + "\t\t";
  protected final String TEXT_36 = ".run(";
  protected final String TEXT_37 = "Test.class);" + NL + "\t}";
  protected final String TEXT_38 = NL + NL + "\t/**" + NL + "\t * Constructs a new ";
  protected final String TEXT_39 = " test case with the given name." + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */" + NL + "\tpublic ";
  protected final String TEXT_40 = "(String name)" + NL + "\t{" + NL + "\t\tsuper(name);" + NL + "\t}";
  protected final String TEXT_41 = NL + NL + "\t/**" + NL + "\t * Sets the fixture for this ";
  protected final String TEXT_42 = " test case." + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */" + NL + "\tprotected void setFixture(";
  protected final String TEXT_43 = " fixture)" + NL + "\t{" + NL + "\t\tthis.fixture = fixture;" + NL + "\t}";
  protected final String TEXT_44 = NL + NL + "\t/**" + NL + "\t * Returns the fixture for this ";
  protected final String TEXT_45 = " test case." + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */" + NL + "\tprivate ";
  protected final String TEXT_46 = " getFixture()" + NL + "\t{";
  protected final String TEXT_47 = NL + "\t\treturn fixture;";
  protected final String TEXT_48 = NL + "\t\treturn (";
  protected final String TEXT_49 = ")fixture;";
  protected final String TEXT_50 = NL + "\t}";
  protected final String TEXT_51 = NL + NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @see junit.framework.TestCase#setUp()" + NL + "\t * @generated" + NL + "\t */" + NL + "\tprotected void setUp() throws Exception" + NL + "\t{" + NL + "\t\tsetFixture(";
  protected final String TEXT_52 = ".eINSTANCE.create";
  protected final String TEXT_53 = "());" + NL + "\t}" + NL + "" + NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @see junit.framework.TestCase#tearDown()" + NL + "\t * @generated" + NL + "\t */" + NL + "\tprotected void tearDown() throws Exception" + NL + "\t{" + NL + "\t\tsetFixture(null);" + NL + "\t}";
  protected final String TEXT_54 = NL + NL + "\t/**" + NL + "\t * Tests the '{@link ";
  protected final String TEXT_55 = "#";
  protected final String TEXT_56 = "() <em>";
  protected final String TEXT_57 = "</em>}' feature getter." + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @see ";
  protected final String TEXT_58 = "#";
  protected final String TEXT_59 = "()" + NL + "\t * @generated" + NL + "\t */" + NL + "\tpublic void test";
  protected final String TEXT_60 = "()" + NL + "\t{" + NL + "\t\t// TODO: implement this feature getter test method" + NL + "\t\t// Ensure that you remove @generated or mark it @generated NOT" + NL + "\t\tfail();" + NL + "\t}";
  protected final String TEXT_61 = NL + NL + "\t/**" + NL + "\t * Tests the '{@link ";
  protected final String TEXT_62 = "#set";
  protected final String TEXT_63 = "(";
  protected final String TEXT_64 = ") <em>";
  protected final String TEXT_65 = "</em>}' feature setter." + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @see ";
  protected final String TEXT_66 = "#set";
  protected final String TEXT_67 = "(";
  protected final String TEXT_68 = ")" + NL + "\t * @generated" + NL + "\t */" + NL + "\tpublic void testSet";
  protected final String TEXT_69 = "()" + NL + "\t{" + NL + "\t\t// TODO: implement this feature setter test method" + NL + "\t\t// Ensure that you remove @generated or mark it @generated NOT" + NL + "\t\tfail();" + NL + "\t}";
  protected final String TEXT_70 = NL + NL + "\t/**" + NL + "\t * Tests the '{@link ";
  protected final String TEXT_71 = "#unset";
  protected final String TEXT_72 = "() <em>unset";
  protected final String TEXT_73 = "()</em>}' method." + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @see ";
  protected final String TEXT_74 = "#unset";
  protected final String TEXT_75 = "()" + NL + "\t * @generated" + NL + "\t */" + NL + "\tpublic void testUnset";
  protected final String TEXT_76 = "()" + NL + "\t{" + NL + "\t\t// TODO: implement this test method" + NL + "\t\t// Ensure that you remove @generated or mark it @generated NOT" + NL + "\t\tfail();" + NL + "\t}";
  protected final String TEXT_77 = NL + NL + "\t/**" + NL + "\t * Tests the '{@link ";
  protected final String TEXT_78 = "#isSet";
  protected final String TEXT_79 = "() <em>isSet";
  protected final String TEXT_80 = "()</em>}' method." + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @see ";
  protected final String TEXT_81 = "#isSet";
  protected final String TEXT_82 = "()" + NL + "\t * @generated" + NL + "\t */" + NL + "\tpublic void testIsSet";
  protected final String TEXT_83 = "()" + NL + "\t{" + NL + "\t\t// TODO: implement this test method" + NL + "\t\t// Ensure that you remove @generated or mark it @generated NOT" + NL + "\t\tfail();" + NL + "\t}";
  protected final String TEXT_84 = NL + NL + "\t/**" + NL + "\t * Tests the '{@link ";
  protected final String TEXT_85 = "#";
  protected final String TEXT_86 = "(";
  protected final String TEXT_87 = ") <em>";
  protected final String TEXT_88 = "</em>}' operation." + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @see ";
  protected final String TEXT_89 = "#";
  protected final String TEXT_90 = "(";
  protected final String TEXT_91 = ")" + NL + "\t * @generated" + NL + "\t */" + NL + "\tpublic void test";
  protected final String TEXT_92 = "()" + NL + "\t{" + NL + "\t\t// TODO: implement this operation test method" + NL + "\t\t// Ensure that you remove @generated or mark it @generated NOT" + NL + "\t\tfail();" + NL + "\t}";
  protected final String TEXT_93 = NL + NL + "} //";
  protected final String TEXT_94 = NL;

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

    GenClass genClass = (GenClass)argument; GenPackage genPackage = genClass.getGenPackage(); GenModel genModel=genPackage.getGenModel();
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
    stringBuffer.append(genClass.getFormattedName());
    stringBuffer.append(TEXT_7);
    if (!genClass.getImplementedGenFeatures().isEmpty()) { boolean first = true;
    for (Iterator genFeatures = genClass.getImplementedGenFeatures().iterator(); genFeatures.hasNext();) { GenFeature genFeature = (GenFeature)genFeatures.next();
    if ((genFeature.isVolatile() || genFeature.isDerived()) && !genFeature.isSuppressedGetVisibility()) {
    if (first) { first = false;
    stringBuffer.append(TEXT_8);
    }
    stringBuffer.append(TEXT_9);
    stringBuffer.append(genFeature.getGenClass().getQualifiedInterfaceName());
    stringBuffer.append(TEXT_10);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_11);
    stringBuffer.append(genFeature.getFormattedName());
    stringBuffer.append(TEXT_12);
    }
    }
     if (!first) {
    stringBuffer.append(TEXT_13);
    }
    }
    if (!genClass.getImplementedGenOperations().isEmpty()) { boolean first = true;
    for (Iterator genOperations = genClass.getImplementedGenOperations().iterator(); genOperations.hasNext();) { GenOperation genOperation = (GenOperation)genOperations.next();
    if (first) { first = false;
    stringBuffer.append(TEXT_14);
    }
    stringBuffer.append(TEXT_15);
    stringBuffer.append(genOperation.getGenClass().getQualifiedInterfaceName());
    stringBuffer.append(TEXT_16);
    stringBuffer.append(genOperation.getName());
    stringBuffer.append(TEXT_17);
    stringBuffer.append(genOperation.getParameterTypes(", "));
    stringBuffer.append(TEXT_18);
    stringBuffer.append(genOperation.getFormattedName());
    stringBuffer.append(TEXT_19);
    }
     if (!first) {
    stringBuffer.append(TEXT_20);
    }
    }
    stringBuffer.append(TEXT_21);
    if (genClass.isAbstract()) {
    stringBuffer.append(TEXT_22);
    }
    stringBuffer.append(TEXT_23);
    stringBuffer.append(genClass.getTestCaseClassName());
    stringBuffer.append(TEXT_24);
    stringBuffer.append(genClass.getClassExtendsGenClass() == null || genClass.getClassExtendsGenClass().getGenModel() != genModel ? genModel.getImportedName("junit.framework.TestCase") : genClass.getClassExtendsGenClass().getImportedTestCaseClassName());
    stringBuffer.append(TEXT_25);
    if (genModel.getCopyrightText() != null) {
    stringBuffer.append(TEXT_26);
    stringBuffer.append(genModel.getImportedName("java.lang.String"));
    stringBuffer.append(TEXT_27);
    stringBuffer.append(genModel.getCopyrightText());
    stringBuffer.append(TEXT_28);
    stringBuffer.append(genModel.getNonNLS());
    }
    if (genModel.getDriverNumber() != null) {
    stringBuffer.append(TEXT_29);
    stringBuffer.append(genModel.getImportedName("java.lang.String"));
    stringBuffer.append(TEXT_30);
    stringBuffer.append(genModel.getDriverNumber());
    stringBuffer.append(TEXT_31);
    stringBuffer.append(genModel.getNonNLS());
    }
    if (genClass.getClassExtendsGenClass() == null || genClass.getClassExtendsGenClass().getGenModel() != genModel) {
    stringBuffer.append(TEXT_32);
    stringBuffer.append(genClass.getFormattedName());
    stringBuffer.append(TEXT_33);
    stringBuffer.append(genClass.getImportedInterfaceName());
    stringBuffer.append(TEXT_34);
    }
    if (!genClass.isAbstract()) {
    stringBuffer.append(TEXT_35);
    stringBuffer.append(genModel.getImportedName("junit.textui.TestRunner"));
    stringBuffer.append(TEXT_36);
    stringBuffer.append(genClass.getInterfaceName());
    stringBuffer.append(TEXT_37);
    }
    stringBuffer.append(TEXT_38);
    stringBuffer.append(genClass.getFormattedName());
    stringBuffer.append(TEXT_39);
    stringBuffer.append(genClass.getTestCaseClassName());
    stringBuffer.append(TEXT_40);
    if (genClass.getClassExtendsGenClass() == null || genClass.getClassExtendsGenClass().getGenModel() != genModel) {
    stringBuffer.append(TEXT_41);
    stringBuffer.append(genClass.getFormattedName());
    stringBuffer.append(TEXT_42);
    stringBuffer.append(genClass.getImportedInterfaceName());
    stringBuffer.append(TEXT_43);
    }
    stringBuffer.append(TEXT_44);
    stringBuffer.append(genClass.getFormattedName());
    stringBuffer.append(TEXT_45);
    stringBuffer.append(genClass.getImportedInterfaceName());
    stringBuffer.append(TEXT_46);
    if (genClass.getClassExtendsGenClass() == null || genClass.getClassExtendsGenClass().getGenModel() != genModel) {
    stringBuffer.append(TEXT_47);
    } else {
    stringBuffer.append(TEXT_48);
    stringBuffer.append(genClass.getImportedInterfaceName());
    stringBuffer.append(TEXT_49);
    }
    stringBuffer.append(TEXT_50);
    if (!genClass.isAbstract()) {
    stringBuffer.append(TEXT_51);
    stringBuffer.append(genPackage.getImportedFactoryInterfaceName());
    stringBuffer.append(TEXT_52);
    stringBuffer.append(genClass.getName());
    stringBuffer.append(TEXT_53);
    }
    for (Iterator genFeatures=genClass.getImplementedGenFeatures().iterator(); genFeatures.hasNext();) { GenFeature genFeature = (GenFeature)genFeatures.next();
    if (genFeature.isVolatile() || genFeature.isDerived()) {
    if (!genFeature.isSuppressedGetVisibility()) {
    stringBuffer.append(TEXT_54);
    stringBuffer.append(genFeature.getGenClass().getQualifiedInterfaceName());
    stringBuffer.append(TEXT_55);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_56);
    stringBuffer.append(genFeature.getFormattedName());
    stringBuffer.append(TEXT_57);
    stringBuffer.append(genFeature.getGenClass().getQualifiedInterfaceName());
    stringBuffer.append(TEXT_58);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_59);
    stringBuffer.append(genModel.capName(genFeature.getGetAccessor()));
    stringBuffer.append(TEXT_60);
    }
    if (!genFeature.isListType() && genFeature.isChangeable() && !genFeature.isSuppressedSetVisibility()) {
    stringBuffer.append(TEXT_61);
    stringBuffer.append(genFeature.getGenClass().getQualifiedInterfaceName());
    stringBuffer.append(TEXT_62);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_63);
    stringBuffer.append(genFeature.getImportedType());
    stringBuffer.append(TEXT_64);
    stringBuffer.append(genFeature.getFormattedName());
    stringBuffer.append(TEXT_65);
    stringBuffer.append(genFeature.getGenClass().getQualifiedInterfaceName());
    stringBuffer.append(TEXT_66);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_67);
    stringBuffer.append(genFeature.getImportedType());
    stringBuffer.append(TEXT_68);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_69);
    }
    if (genFeature.isUnsettable()) {
    if (genFeature.isChangeable() && !genFeature.isSuppressedUnsetVisibility()) {
    stringBuffer.append(TEXT_70);
    stringBuffer.append(genFeature.getGenClass().getQualifiedInterfaceName());
    stringBuffer.append(TEXT_71);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_72);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_73);
    stringBuffer.append(genFeature.getGenClass().getQualifiedInterfaceName());
    stringBuffer.append(TEXT_74);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_75);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_76);
    }
    if (!genFeature.isSuppressedIsSetVisibility()) {
    stringBuffer.append(TEXT_77);
    stringBuffer.append(genFeature.getGenClass().getQualifiedInterfaceName());
    stringBuffer.append(TEXT_78);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_79);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_80);
    stringBuffer.append(genFeature.getGenClass().getQualifiedInterfaceName());
    stringBuffer.append(TEXT_81);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_82);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_83);
    }
    }
    }
    }
    for (Iterator genOperations = genClass.getImplementedGenOperations().iterator(); genOperations.hasNext();) { GenOperation genOperation = (GenOperation)genOperations.next();
    stringBuffer.append(TEXT_84);
    stringBuffer.append(genOperation.getGenClass().getQualifiedInterfaceName());
    stringBuffer.append(TEXT_85);
    stringBuffer.append(genOperation.getName());
    stringBuffer.append(TEXT_86);
    stringBuffer.append(genOperation.getParameterTypes(", "));
    stringBuffer.append(TEXT_87);
    stringBuffer.append(genOperation.getFormattedName());
    stringBuffer.append(TEXT_88);
    stringBuffer.append(genOperation.getGenClass().getQualifiedInterfaceName());
    stringBuffer.append(TEXT_89);
    stringBuffer.append(genOperation.getName());
    stringBuffer.append(TEXT_90);
    stringBuffer.append(genOperation.getParameterTypes(", "));
    stringBuffer.append(TEXT_91);
    stringBuffer.append(genModel.capName(genOperation.getName()));
    stringBuffer.append(genOperation.getGenParameters().size() == 0 ? "" : "__" + genOperation.getParameterTypes("_"));
    stringBuffer.append(TEXT_92);
    }
    stringBuffer.append(TEXT_93);
    stringBuffer.append(genClass.getTestCaseClassName());
    genModel.emitSortedImports();
    stringBuffer.append(TEXT_94);
    return stringBuffer.toString();
  }
}
