package org.eclipse.emf.codegen.ecore.templates.model.tests;

import java.util.*;
import org.eclipse.emf.codegen.ecore.genmodel.*;

public class TestCase
{
  protected static String nl;
  public static synchronized TestCase create(String lineSeparator)
  {
    nl = lineSeparator;
    TestCase result = new TestCase();
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
  protected final String TEXT_7 = ";" + NL;
  protected final String TEXT_8 = NL + NL + "/**" + NL + " * <!-- begin-user-doc -->" + NL + " * A test case for the model object '<em><b>";
  protected final String TEXT_9 = "</b></em>'." + NL + " * <!-- end-user-doc -->";
  protected final String TEXT_10 = NL + " * <p>" + NL + " * The following features are tested:" + NL + " * <ul>";
  protected final String TEXT_11 = NL + " *   <li>{@link ";
  protected final String TEXT_12 = "#";
  protected final String TEXT_13 = "() <em>";
  protected final String TEXT_14 = "</em>}</li>";
  protected final String TEXT_15 = NL + " * </ul>" + NL + " * </p>";
  protected final String TEXT_16 = NL + " * <p>" + NL + " * The following operations are tested:" + NL + " * <ul>";
  protected final String TEXT_17 = NL + " *   <li>{@link ";
  protected final String TEXT_18 = "#";
  protected final String TEXT_19 = "(";
  protected final String TEXT_20 = ") <em>";
  protected final String TEXT_21 = "</em>}</li>";
  protected final String TEXT_22 = NL + " * </ul>" + NL + " * </p>";
  protected final String TEXT_23 = NL + " * @generated" + NL + " */" + NL + "public";
  protected final String TEXT_24 = " abstract";
  protected final String TEXT_25 = " class ";
  protected final String TEXT_26 = " extends ";
  protected final String TEXT_27 = NL + "{";
  protected final String TEXT_28 = NL + NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */" + NL + "\tpublic static final ";
  protected final String TEXT_29 = " copyright = ";
  protected final String TEXT_30 = ";";
  protected final String TEXT_31 = NL + NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */" + NL + "\tpublic static final ";
  protected final String TEXT_32 = " mofDriverNumber = \"";
  protected final String TEXT_33 = "\";";
  protected final String TEXT_34 = NL + NL + "\t/**" + NL + "\t * The fixture for this ";
  protected final String TEXT_35 = " test case." + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */" + NL + "\tprotected ";
  protected final String TEXT_36 = " fixture = null;";
  protected final String TEXT_37 = NL + NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */" + NL + "\tpublic static void main(String[] args)" + NL + "\t{" + NL + "\t\t";
  protected final String TEXT_38 = ".run(";
  protected final String TEXT_39 = ".class);" + NL + "\t}";
  protected final String TEXT_40 = NL + NL + "\t/**" + NL + "\t * Constructs a new ";
  protected final String TEXT_41 = " test case with the given name." + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */" + NL + "\tpublic ";
  protected final String TEXT_42 = "(String name)" + NL + "\t{" + NL + "\t\tsuper(name);" + NL + "\t}";
  protected final String TEXT_43 = NL + NL + "\t/**" + NL + "\t * Sets the fixture for this ";
  protected final String TEXT_44 = " test case." + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */" + NL + "\tprotected void setFixture(";
  protected final String TEXT_45 = " fixture)" + NL + "\t{" + NL + "\t\tthis.fixture = fixture;" + NL + "\t}";
  protected final String TEXT_46 = NL + NL + "\t/**" + NL + "\t * Returns the fixture for this ";
  protected final String TEXT_47 = " test case." + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */";
  protected final String TEXT_48 = NL + "\t@Override";
  protected final String TEXT_49 = NL + "\t";
  protected final String TEXT_50 = "protected";
  protected final String TEXT_51 = "private";
  protected final String TEXT_52 = " ";
  protected final String TEXT_53 = " getFixture()" + NL + "\t{";
  protected final String TEXT_54 = NL + "\t\treturn fixture;";
  protected final String TEXT_55 = NL + "\t\treturn (";
  protected final String TEXT_56 = ")fixture;";
  protected final String TEXT_57 = NL + "\t}";
  protected final String TEXT_58 = NL + NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @see junit.framework.TestCase#setUp()" + NL + "\t * @generated" + NL + "\t */";
  protected final String TEXT_59 = NL + "\t@Override";
  protected final String TEXT_60 = NL + "\t@SuppressWarnings(\"unchecked\")";
  protected final String TEXT_61 = NL + "\tprotected void setUp() throws Exception" + NL + "\t{";
  protected final String TEXT_62 = NL + "\t\tsetFixture((";
  protected final String TEXT_63 = ")";
  protected final String TEXT_64 = ".create(";
  protected final String TEXT_65 = "));";
  protected final String TEXT_66 = NL + "\t\tsetFixture(";
  protected final String TEXT_67 = ".create";
  protected final String TEXT_68 = "());";
  protected final String TEXT_69 = NL + "\t}" + NL + "" + NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @see junit.framework.TestCase#tearDown()" + NL + "\t * @generated" + NL + "\t */";
  protected final String TEXT_70 = NL + "\t@Override";
  protected final String TEXT_71 = NL + "\tprotected void tearDown() throws Exception" + NL + "\t{" + NL + "\t\tsetFixture(null);" + NL + "\t}";
  protected final String TEXT_72 = NL + NL + "\t/**" + NL + "\t * Tests the '{@link ";
  protected final String TEXT_73 = "#";
  protected final String TEXT_74 = "() <em>";
  protected final String TEXT_75 = "</em>}' feature getter." + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @see ";
  protected final String TEXT_76 = "#";
  protected final String TEXT_77 = "()" + NL + "\t * @generated" + NL + "\t */";
  protected final String TEXT_78 = NL + "\tpublic void test";
  protected final String TEXT_79 = "()" + NL + "\t{";
  protected final String TEXT_80 = NL + "\t\t// TODO: implement this feature getter test method" + NL + "\t\t// Ensure that you remove @generated or mark it @generated NOT" + NL + "\t\tfail();";
  protected final String TEXT_81 = NL + "\t}";
  protected final String TEXT_82 = NL + NL + "\t/**" + NL + "\t * Tests the '{@link ";
  protected final String TEXT_83 = "#set";
  protected final String TEXT_84 = "(";
  protected final String TEXT_85 = ") <em>";
  protected final String TEXT_86 = "</em>}' feature setter." + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @see ";
  protected final String TEXT_87 = "#set";
  protected final String TEXT_88 = "(";
  protected final String TEXT_89 = ")" + NL + "\t * @generated" + NL + "\t */";
  protected final String TEXT_90 = NL + "\tpublic void testSet";
  protected final String TEXT_91 = "()" + NL + "\t{";
  protected final String TEXT_92 = NL + "\t\t// TODO: implement this feature setter test method" + NL + "\t\t// Ensure that you remove @generated or mark it @generated NOT" + NL + "\t\tfail();";
  protected final String TEXT_93 = NL + "\t}";
  protected final String TEXT_94 = NL + NL + "\t/**" + NL + "\t * Tests the '{@link ";
  protected final String TEXT_95 = "#unset";
  protected final String TEXT_96 = "() <em>unset";
  protected final String TEXT_97 = "()</em>}' method." + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @see ";
  protected final String TEXT_98 = "#unset";
  protected final String TEXT_99 = "()" + NL + "\t * @generated" + NL + "\t */";
  protected final String TEXT_100 = NL + "\tpublic void testUnset";
  protected final String TEXT_101 = "()" + NL + "\t{";
  protected final String TEXT_102 = NL + "\t\t// TODO: implement this test method" + NL + "\t\t// Ensure that you remove @generated or mark it @generated NOT" + NL + "\t\tfail();";
  protected final String TEXT_103 = NL + "\t}";
  protected final String TEXT_104 = NL + NL + "\t/**" + NL + "\t * Tests the '{@link ";
  protected final String TEXT_105 = "#isSet";
  protected final String TEXT_106 = "() <em>isSet";
  protected final String TEXT_107 = "()</em>}' method." + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @see ";
  protected final String TEXT_108 = "#isSet";
  protected final String TEXT_109 = "()" + NL + "\t * @generated" + NL + "\t */";
  protected final String TEXT_110 = NL + "\tpublic void testIsSet";
  protected final String TEXT_111 = "()" + NL + "\t{";
  protected final String TEXT_112 = NL + "\t\t// TODO: implement this test method" + NL + "\t\t// Ensure that you remove @generated or mark it @generated NOT" + NL + "\t\tfail();";
  protected final String TEXT_113 = NL + "\t}";
  protected final String TEXT_114 = NL + NL + "\t/**" + NL + "\t * Tests the '{@link ";
  protected final String TEXT_115 = "#";
  protected final String TEXT_116 = "(";
  protected final String TEXT_117 = ") <em>";
  protected final String TEXT_118 = "</em>}' operation." + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @see ";
  protected final String TEXT_119 = "#";
  protected final String TEXT_120 = "(";
  protected final String TEXT_121 = ")" + NL + "\t * @generated" + NL + "\t */";
  protected final String TEXT_122 = NL + "\tpublic void test";
  protected final String TEXT_123 = "()" + NL + "\t{";
  protected final String TEXT_124 = NL + "\t\t// TODO: implement this operation test method" + NL + "\t\t// Ensure that you remove @generated or mark it @generated NOT" + NL + "\t\tfail();";
  protected final String TEXT_125 = NL + "\t}";
  protected final String TEXT_126 = NL;
  protected final String TEXT_127 = NL + "} //";
  protected final String TEXT_128 = NL;

  public String generate(Object argument)
  {
    final StringBuffer stringBuffer = new StringBuffer();
    
/**
 * <copyright>
 *
 * Copyright (c) 2005, 2007 IBM Corporation and others.
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

    GenClass genClass = (GenClass)argument; GenPackage genPackage = genClass.getGenPackage(); GenModel genModel=genPackage.getGenModel(); /* Trick to import java.util.* without warnings */Iterator.class.getName();
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
    stringBuffer.append(genPackage.getTestsPackageName());
    stringBuffer.append(TEXT_7);
    genModel.markImportLocation(stringBuffer);
    stringBuffer.append(TEXT_8);
    stringBuffer.append(genClass.getFormattedName());
    stringBuffer.append(TEXT_9);
    if (!genClass.getImplementedGenFeatures().isEmpty()) { boolean first = true;
    for (GenFeature genFeature : genClass.getImplementedGenFeatures()) {
    if (genFeature.isTested() && !genFeature.isSuppressedGetVisibility()) {
    if (first) { first = false;
    stringBuffer.append(TEXT_10);
    }
    stringBuffer.append(TEXT_11);
    stringBuffer.append(genFeature.getGenClass().getQualifiedInterfaceName());
    stringBuffer.append(TEXT_12);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_13);
    stringBuffer.append(genFeature.getFormattedName());
    stringBuffer.append(TEXT_14);
    }
    }
     if (!first) {
    stringBuffer.append(TEXT_15);
    }
    }
    if (!genClass.getImplementedGenOperations().isEmpty()) { boolean first = true;
    for (GenOperation genOperation : genClass.getImplementedGenOperations()) {
    if (first) { first = false;
    stringBuffer.append(TEXT_16);
    }
    stringBuffer.append(TEXT_17);
    stringBuffer.append(genOperation.getGenClass().getQualifiedInterfaceName());
    stringBuffer.append(TEXT_18);
    stringBuffer.append(genOperation.getName());
    stringBuffer.append(TEXT_19);
    stringBuffer.append(genOperation.getParameterTypes(", "));
    stringBuffer.append(TEXT_20);
    stringBuffer.append(genOperation.getFormattedName());
    stringBuffer.append(TEXT_21);
    }
     if (!first) {
    stringBuffer.append(TEXT_22);
    }
    }
    stringBuffer.append(TEXT_23);
    if (genClass.isAbstract()) {
    stringBuffer.append(TEXT_24);
    }
    stringBuffer.append(TEXT_25);
    stringBuffer.append(genClass.getTestCaseClassName());
    stringBuffer.append(TEXT_26);
    stringBuffer.append(genClass.isModelRoot() ? genModel.getImportedName("junit.framework.TestCase") : genClass.getClassExtendsGenClass().getImportedTestCaseClassName());
    stringBuffer.append(TEXT_27);
    if (genModel.hasCopyrightField()) {
    stringBuffer.append(TEXT_28);
    stringBuffer.append(genModel.getImportedName("java.lang.String"));
    stringBuffer.append(TEXT_29);
    stringBuffer.append(genModel.getCopyrightFieldLiteral());
    stringBuffer.append(TEXT_30);
    stringBuffer.append(genModel.getNonNLS());
    }
    if (genModel.getDriverNumber() != null) {
    stringBuffer.append(TEXT_31);
    stringBuffer.append(genModel.getImportedName("java.lang.String"));
    stringBuffer.append(TEXT_32);
    stringBuffer.append(genModel.getDriverNumber());
    stringBuffer.append(TEXT_33);
    stringBuffer.append(genModel.getNonNLS());
    }
    if (genClass.isModelRoot()) {
    stringBuffer.append(TEXT_34);
    stringBuffer.append(genClass.getFormattedName());
    stringBuffer.append(TEXT_35);
    stringBuffer.append(genClass.getImportedInterfaceName());
    stringBuffer.append(genClass.getInterfaceWildTypeArguments());
    stringBuffer.append(TEXT_36);
    }
    if (!genClass.isAbstract()) {
    stringBuffer.append(TEXT_37);
    stringBuffer.append(genModel.getImportedName("junit.textui.TestRunner"));
    stringBuffer.append(TEXT_38);
    stringBuffer.append(genClass.getTestCaseClassName());
    stringBuffer.append(TEXT_39);
    }
    stringBuffer.append(TEXT_40);
    stringBuffer.append(genClass.getFormattedName());
    stringBuffer.append(TEXT_41);
    stringBuffer.append(genClass.getTestCaseClassName());
    stringBuffer.append(TEXT_42);
    if (genClass.isModelRoot()) {
    stringBuffer.append(TEXT_43);
    stringBuffer.append(genClass.getFormattedName());
    stringBuffer.append(TEXT_44);
    stringBuffer.append(genClass.getImportedInterfaceName());
    stringBuffer.append(genClass.getInterfaceWildTypeArguments());
    stringBuffer.append(TEXT_45);
    }
    stringBuffer.append(TEXT_46);
    stringBuffer.append(genClass.getFormattedName());
    stringBuffer.append(TEXT_47);
    if (!genClass.isModelRoot() && genModel.useClassOverrideAnnotation()) {
    stringBuffer.append(TEXT_48);
    }
    stringBuffer.append(TEXT_49);
    if (genModel.useGenerics()) {
    stringBuffer.append(TEXT_50);
    } else {
    stringBuffer.append(TEXT_51);
    }
    stringBuffer.append(TEXT_52);
    stringBuffer.append(genClass.getImportedInterfaceName());
    stringBuffer.append(genClass.getInterfaceWildTypeArguments());
    stringBuffer.append(TEXT_53);
    if (genClass.isModelRoot()) {
    stringBuffer.append(TEXT_54);
    } else {
    stringBuffer.append(TEXT_55);
    stringBuffer.append(genClass.getImportedInterfaceName());
    stringBuffer.append(genClass.getInterfaceWildTypeArguments());
    stringBuffer.append(TEXT_56);
    }
    stringBuffer.append(TEXT_57);
    if (!genClass.isAbstract()) {
    stringBuffer.append(TEXT_58);
    if (genModel.useClassOverrideAnnotation()) {
    stringBuffer.append(TEXT_59);
    }
    if (genModel.useGenerics() && genClass.isMapEntry()) {
    stringBuffer.append(TEXT_60);
    }
    stringBuffer.append(TEXT_61);
    if (genClass.isMapEntry()) {
    stringBuffer.append(TEXT_62);
    stringBuffer.append(genClass.getImportedInterfaceName());
    stringBuffer.append(genClass.getInterfaceWildTypeArguments());
    stringBuffer.append(TEXT_63);
    stringBuffer.append(genPackage.getQualifiedEFactoryInternalInstanceAccessor());
    stringBuffer.append(TEXT_64);
    stringBuffer.append(genClass.getQualifiedClassifierAccessor());
    stringBuffer.append(TEXT_65);
    } else {
    stringBuffer.append(TEXT_66);
    stringBuffer.append(genPackage.getQualifiedEFactoryInternalInstanceAccessor());
    stringBuffer.append(TEXT_67);
    stringBuffer.append(genClass.getName());
    stringBuffer.append(TEXT_68);
    }
    stringBuffer.append(TEXT_69);
    if (genModel.useClassOverrideAnnotation()) {
    stringBuffer.append(TEXT_70);
    }
    stringBuffer.append(TEXT_71);
    }
    for (GenFeature genFeature : genClass.getImplementedGenFeatures()) {
    if (genFeature.isTested()) {
    if (genFeature.isGet() && !genFeature.isSuppressedGetVisibility()) {
    stringBuffer.append(TEXT_72);
    stringBuffer.append(genFeature.getGenClass().getQualifiedInterfaceName());
    stringBuffer.append(TEXT_73);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_74);
    stringBuffer.append(genFeature.getFormattedName());
    stringBuffer.append(TEXT_75);
    stringBuffer.append(genFeature.getGenClass().getQualifiedInterfaceName());
    stringBuffer.append(TEXT_76);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_77);
    if (genModel.getComplianceLevel().getValue() >= GenJDKLevel.JDK50) { //TestCase/getGenFeature.annotations.insert.javajetinc
    }
    stringBuffer.append(TEXT_78);
    stringBuffer.append(genModel.capName(genFeature.getGetAccessor()));
    stringBuffer.append(TEXT_79);
    stringBuffer.append(TEXT_80);
    //TestCase/getGenFeature.todo.override.javajetinc
    stringBuffer.append(TEXT_81);
    //TestCase/getGenFeature.override.javajetinc
    }
    if (genFeature.isSet() && !genFeature.isSuppressedSetVisibility()) {
    stringBuffer.append(TEXT_82);
    stringBuffer.append(genFeature.getGenClass().getQualifiedInterfaceName());
    stringBuffer.append(TEXT_83);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_84);
    stringBuffer.append(genFeature.getRawBoundType());
    stringBuffer.append(TEXT_85);
    stringBuffer.append(genFeature.getFormattedName());
    stringBuffer.append(TEXT_86);
    stringBuffer.append(genFeature.getGenClass().getQualifiedInterfaceName());
    stringBuffer.append(TEXT_87);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_88);
    stringBuffer.append(genFeature.getRawBoundType());
    stringBuffer.append(TEXT_89);
    if (genModel.getComplianceLevel().getValue() >= GenJDKLevel.JDK50) { //TestCase/setGenFeature.annotations.insert.javajetinc
    }
    stringBuffer.append(TEXT_90);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_91);
    stringBuffer.append(TEXT_92);
    //TestCase/setGenFeature.todo.override.javajetinc
    stringBuffer.append(TEXT_93);
    //TestCase/setGenFeature.override.javajetinc
    }
    if (genFeature.isUnset() && !genFeature.isSuppressedUnsetVisibility()) {
    stringBuffer.append(TEXT_94);
    stringBuffer.append(genFeature.getGenClass().getQualifiedInterfaceName());
    stringBuffer.append(TEXT_95);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_96);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_97);
    stringBuffer.append(genFeature.getGenClass().getQualifiedInterfaceName());
    stringBuffer.append(TEXT_98);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_99);
    if (genModel.getComplianceLevel().getValue() >= GenJDKLevel.JDK50) { //TestCase/unsetGenFeature.annotations.insert.javajetinc
    }
    stringBuffer.append(TEXT_100);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_101);
    stringBuffer.append(TEXT_102);
    //TestCase/unsetGenFeature.todo.override.javajetinc
    stringBuffer.append(TEXT_103);
    //TestCase/unsetGenFeature.override.javajetinc
    }
    if (genFeature.isIsSet() && !genFeature.isSuppressedIsSetVisibility()) {
    stringBuffer.append(TEXT_104);
    stringBuffer.append(genFeature.getGenClass().getQualifiedInterfaceName());
    stringBuffer.append(TEXT_105);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_106);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_107);
    stringBuffer.append(genFeature.getGenClass().getQualifiedInterfaceName());
    stringBuffer.append(TEXT_108);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_109);
    if (genModel.getComplianceLevel().getValue() >= GenJDKLevel.JDK50) { //TestCase/isSetGenFeature.annotations.insert.javajetinc
    }
    stringBuffer.append(TEXT_110);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_111);
    stringBuffer.append(TEXT_112);
    //TestCase/isSetGenFeature.todo.override.javajetinc
    stringBuffer.append(TEXT_113);
    //TestCase/isSetGenFeature.override.javajetinc
    }
    }
    //TestCase/implementedGenFeature.override.javajetinc
    }
    for (GenOperation genOperation : genClass.getImplementedGenOperations()) {
    stringBuffer.append(TEXT_114);
    stringBuffer.append(genOperation.getGenClass().getQualifiedInterfaceName());
    stringBuffer.append(TEXT_115);
    stringBuffer.append(genOperation.getName());
    stringBuffer.append(TEXT_116);
    stringBuffer.append(genOperation.getParameterTypes(", "));
    stringBuffer.append(TEXT_117);
    stringBuffer.append(genOperation.getFormattedName());
    stringBuffer.append(TEXT_118);
    stringBuffer.append(genOperation.getGenClass().getQualifiedInterfaceName());
    stringBuffer.append(TEXT_119);
    stringBuffer.append(genOperation.getName());
    stringBuffer.append(TEXT_120);
    stringBuffer.append(genOperation.getParameterTypes(", "));
    stringBuffer.append(TEXT_121);
    if (genModel.getComplianceLevel().getValue() >= GenJDKLevel.JDK50) { //TestCase/genOperation.annotations.insert.javajetinc
    }
    stringBuffer.append(TEXT_122);
    stringBuffer.append(genClass.getUniqueName(genOperation));
    stringBuffer.append(TEXT_123);
    stringBuffer.append(TEXT_124);
    //TestCase/implementedGenOperation.todo.override.javajetinc
    stringBuffer.append(TEXT_125);
    //TestCase/implementedGenOperation.override.javajetinc
    }
    stringBuffer.append(TEXT_126);
    stringBuffer.append(TEXT_127);
    stringBuffer.append(genClass.getTestCaseClassName());
    genModel.emitSortedImports();
    stringBuffer.append(TEXT_128);
    return stringBuffer.toString();
  }
}
