package org.eclipse.emf.codegen.ecore.templates.model;

import java.util.*;
import org.eclipse.emf.codegen.ecore.genmodel.*;

public class FactoryClass
{
  protected static String nl;
  public static synchronized FactoryClass create(String lineSeparator)
  {
    nl = lineSeparator;
    FactoryClass result = new FactoryClass();
    nl = null;
    return result;
  }

  protected final String NL = nl == null ? (System.getProperties().getProperty("line.separator")) : nl;
  protected final String TEXT_1 = "";
  protected final String TEXT_2 = "/**" + NL + " * <copyright>" + NL + " * </copyright>" + NL + " *" + NL + " * ";
  protected final String TEXT_3 = "Id";
  protected final String TEXT_4 = NL + " */";
  protected final String TEXT_5 = NL + "package ";
  protected final String TEXT_6 = ";";
  protected final String TEXT_7 = NL + "package ";
  protected final String TEXT_8 = ";";
  protected final String TEXT_9 = NL;
  protected final String TEXT_10 = NL;
  protected final String TEXT_11 = NL + "/**" + NL + " * <!-- begin-user-doc -->" + NL + " * The <b>Factory</b> for the model." + NL + " * It provides a create method for each non-abstract class of the model." + NL + " * <!-- end-user-doc -->";
  protected final String TEXT_12 = NL + " * @see ";
  protected final String TEXT_13 = NL + " * @generated" + NL + " */";
  protected final String TEXT_14 = NL + "/**" + NL + " * <!-- begin-user-doc -->" + NL + " * An implementation of the model <b>Factory</b>." + NL + " * <!-- end-user-doc -->" + NL + " * @generated" + NL + " */";
  protected final String TEXT_15 = NL + "public class ";
  protected final String TEXT_16 = " extends ";
  protected final String TEXT_17 = " implements ";
  protected final String TEXT_18 = NL + "public interface ";
  protected final String TEXT_19 = " extends ";
  protected final String TEXT_20 = NL + "{";
  protected final String TEXT_21 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */" + NL + "\t";
  protected final String TEXT_22 = " copyright = \"";
  protected final String TEXT_23 = "\";";
  protected final String TEXT_24 = NL;
  protected final String TEXT_25 = NL + "\t/**" + NL + "\t * The singleton instance of the factory." + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */" + NL + "\t";
  protected final String TEXT_26 = " eINSTANCE = init();" + NL;
  protected final String TEXT_27 = NL + "\t/**" + NL + "\t * The singleton instance of the factory." + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */" + NL + "\t";
  protected final String TEXT_28 = " INSTANCE = ";
  protected final String TEXT_29 = ".eINSTANCE;" + NL;
  protected final String TEXT_30 = NL + "\t/**" + NL + "\t * The singleton instance of the factory." + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */" + NL + "\t";
  protected final String TEXT_31 = " eINSTANCE = ";
  protected final String TEXT_32 = ".init();" + NL;
  protected final String TEXT_33 = NL + "\t/**" + NL + "\t * Creates the default factory implementation." + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */";
  protected final String TEXT_34 = NL + "\tpublic static ";
  protected final String TEXT_35 = " init()" + NL + "\t{" + NL + "\t\ttry" + NL + "\t\t{" + NL + "\t\t\t";
  protected final String TEXT_36 = " the";
  protected final String TEXT_37 = " = (";
  protected final String TEXT_38 = ")";
  protected final String TEXT_39 = ".Registry.INSTANCE.getEFactory(\"";
  protected final String TEXT_40 = "\");";
  protected final String TEXT_41 = " " + NL + "\t\t\tif (the";
  protected final String TEXT_42 = " != null)" + NL + "\t\t\t{" + NL + "\t\t\t\treturn the";
  protected final String TEXT_43 = ";" + NL + "\t\t\t}" + NL + "\t\t}" + NL + "\t\tcatch (Exception exception)" + NL + "\t\t{" + NL + "\t\t\t";
  protected final String TEXT_44 = ".INSTANCE.log(exception);" + NL + "\t\t}" + NL + "\t\treturn new ";
  protected final String TEXT_45 = "();" + NL + "\t}" + NL + "" + NL + "\t/**" + NL + "\t * Creates an instance of the factory." + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */" + NL + "\tpublic ";
  protected final String TEXT_46 = "()" + NL + "\t{" + NL + "\t\tsuper();" + NL + "\t}" + NL + "" + NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */" + NL + "\tpublic EObject create(EClass eClass)" + NL + "\t{" + NL + "\t\tswitch (eClass.getClassifierID())" + NL + "\t\t{";
  protected final String TEXT_47 = NL + "\t\t\tcase ";
  protected final String TEXT_48 = ".";
  protected final String TEXT_49 = ": return ";
  protected final String TEXT_50 = "create";
  protected final String TEXT_51 = "();";
  protected final String TEXT_52 = NL + "\t\t\tdefault:" + NL + "\t\t\t\tthrow new IllegalArgumentException(\"The class '\" + eClass.getName() + \"' is not a valid classifier\");";
  protected final String TEXT_53 = NL + "\t\t}" + NL + "\t}" + NL;
  protected final String TEXT_54 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */" + NL + "\tpublic Object createFromString(";
  protected final String TEXT_55 = " eDataType, String initialValue)" + NL + "\t{" + NL + "\t\tswitch (eDataType.getClassifierID())" + NL + "\t\t{";
  protected final String TEXT_56 = NL + "\t\t\tcase ";
  protected final String TEXT_57 = ".";
  protected final String TEXT_58 = ":" + NL + "\t\t\t\treturn create";
  protected final String TEXT_59 = "FromString(eDataType, initialValue);";
  protected final String TEXT_60 = NL + "\t\t\tdefault:" + NL + "\t\t\t\tthrow new IllegalArgumentException(\"The datatype '\" + eDataType.getName() + \"' is not a valid classifier\");";
  protected final String TEXT_61 = NL + "\t\t}" + NL + "\t}" + NL + "" + NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */" + NL + "\tpublic String convertToString(";
  protected final String TEXT_62 = " eDataType, Object instanceValue)" + NL + "\t{" + NL + "\t\tswitch (eDataType.getClassifierID())" + NL + "\t\t{";
  protected final String TEXT_63 = NL + "\t\t\tcase ";
  protected final String TEXT_64 = ".";
  protected final String TEXT_65 = ":" + NL + "\t\t\t\treturn convert";
  protected final String TEXT_66 = "ToString(eDataType, instanceValue);";
  protected final String TEXT_67 = NL + "\t\t\tdefault:" + NL + "\t\t\t\tthrow new IllegalArgumentException(\"The datatype '\" + eDataType.getName() + \"' is not a valid classifier\");";
  protected final String TEXT_68 = NL + "\t\t}" + NL + "\t}" + NL;
  protected final String TEXT_69 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */" + NL + "\tpublic ";
  protected final String TEXT_70 = " create";
  protected final String TEXT_71 = "()" + NL + "\t{";
  protected final String TEXT_72 = NL + "\t\t";
  protected final String TEXT_73 = " ";
  protected final String TEXT_74 = " = ";
  protected final String TEXT_75 = "super.create(";
  protected final String TEXT_76 = ");";
  protected final String TEXT_77 = NL + "\t\t";
  protected final String TEXT_78 = " ";
  protected final String TEXT_79 = " = new ";
  protected final String TEXT_80 = "()";
  protected final String TEXT_81 = "{}";
  protected final String TEXT_82 = ";";
  protected final String TEXT_83 = NL + "\t\treturn ";
  protected final String TEXT_84 = ";" + NL + "\t}" + NL;
  protected final String TEXT_85 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */" + NL + "\tpublic ";
  protected final String TEXT_86 = " create";
  protected final String TEXT_87 = "FromString(";
  protected final String TEXT_88 = " eDataType, String initialValue)" + NL + "\t{";
  protected final String TEXT_89 = NL + "\t\t";
  protected final String TEXT_90 = " result = ";
  protected final String TEXT_91 = ".get(initialValue);" + NL + "\t\tif (result == null) throw new IllegalArgumentException(\"The value '\" + initialValue + \"' is not a valid enumerator of '\" + eDataType.getName() + \"'\");";
  protected final String TEXT_92 = NL + "\t\treturn result;";
  protected final String TEXT_93 = NL + "\t\treturn (";
  protected final String TEXT_94 = ")create";
  protected final String TEXT_95 = "FromString(";
  protected final String TEXT_96 = ", initialValue);";
  protected final String TEXT_97 = NL + "\t\treturn (";
  protected final String TEXT_98 = ")";
  protected final String TEXT_99 = ".createFromString(";
  protected final String TEXT_100 = ", initialValue);";
  protected final String TEXT_101 = NL + "\t\tif (initialValue == null) return null;" + NL + "\t\t";
  protected final String TEXT_102 = " result = new ";
  protected final String TEXT_103 = "();" + NL + "\t\tfor (";
  protected final String TEXT_104 = " stringTokenizer = new ";
  protected final String TEXT_105 = "(initialValue); stringTokenizer.hasMoreTokens(); )" + NL + "\t\t{" + NL + "\t\t\tString item = stringTokenizer.nextToken();";
  protected final String TEXT_106 = NL + "\t\t\tresult.add(create";
  protected final String TEXT_107 = "FromString(";
  protected final String TEXT_108 = ", item));";
  protected final String TEXT_109 = NL + "\t\t\tresult.add(";
  protected final String TEXT_110 = ".createFromString(";
  protected final String TEXT_111 = ", item));";
  protected final String TEXT_112 = NL + "\t\t}" + NL + "\t\treturn result;";
  protected final String TEXT_113 = NL + "\t\tif (initialValue == null) return null;" + NL + "\t\t";
  protected final String TEXT_114 = " result = null;" + NL + "\t\tRuntimeException exception = null;";
  protected final String TEXT_115 = NL + "\t\ttry" + NL + "\t\t{";
  protected final String TEXT_116 = NL + "\t\t\tresult = (";
  protected final String TEXT_117 = ")create";
  protected final String TEXT_118 = "FromString(";
  protected final String TEXT_119 = ", initialValue);";
  protected final String TEXT_120 = NL + "\t\t\tresult = (";
  protected final String TEXT_121 = ")";
  protected final String TEXT_122 = ".createFromString(";
  protected final String TEXT_123 = ", initialValue);";
  protected final String TEXT_124 = NL + "\t\t\tif (result != null && ";
  protected final String TEXT_125 = ".INSTANCE.validate(eDataType, result, null, null))" + NL + "\t\t\t{" + NL + "\t\t\t\treturn result;" + NL + "\t\t\t}" + NL + "\t\t}" + NL + "\t\tcatch (RuntimeException e)" + NL + "\t\t{" + NL + "\t\t\texception = e;" + NL + "\t\t}";
  protected final String TEXT_126 = NL + "\t\tif (result != null || exception == null) return result;" + NL + "    " + NL + "\t\tthrow exception;";
  protected final String TEXT_127 = NL + "\t\t// TODO: implement this method" + NL + "\t\t// Ensure that you remove @generated or mark it @generated NOT" + NL + "\t\tthrow new ";
  protected final String TEXT_128 = "();";
  protected final String TEXT_129 = NL + "\t\treturn (";
  protected final String TEXT_130 = ")super.createFromString(eDataType, initialValue);";
  protected final String TEXT_131 = NL + "\t}" + NL + "" + NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */" + NL + "\tpublic String convert";
  protected final String TEXT_132 = "ToString(";
  protected final String TEXT_133 = " eDataType, Object instanceValue)" + NL + "\t{";
  protected final String TEXT_134 = NL + "\t\treturn instanceValue == null ? null : instanceValue.toString();";
  protected final String TEXT_135 = NL + "\t\treturn convert";
  protected final String TEXT_136 = "ToString(";
  protected final String TEXT_137 = ", instanceValue);";
  protected final String TEXT_138 = NL + "\t\treturn ";
  protected final String TEXT_139 = ".convertToString(";
  protected final String TEXT_140 = ", instanceValue);";
  protected final String TEXT_141 = NL + "\t\tif (instanceValue == null) return null;" + NL + "\t\t";
  protected final String TEXT_142 = " list = (";
  protected final String TEXT_143 = ")instanceValue;" + NL + "\t\tif (list.isEmpty()) return \"\";" + NL + "\t\t";
  protected final String TEXT_144 = " result = new ";
  protected final String TEXT_145 = "();" + NL + "\t\tfor (";
  protected final String TEXT_146 = " i = list.iterator(); i.hasNext(); )" + NL + "\t\t{";
  protected final String TEXT_147 = NL + "\t\t\tresult.append(convert";
  protected final String TEXT_148 = "ToString(";
  protected final String TEXT_149 = ", i.next()));";
  protected final String TEXT_150 = NL + "\t\t\tresult.append(";
  protected final String TEXT_151 = ".convertToString(";
  protected final String TEXT_152 = ", i.next()));";
  protected final String TEXT_153 = NL + "\t\t\tresult.append(' ');" + NL + "\t\t}" + NL + "\t\treturn result.substring(0, result.length() - 1);";
  protected final String TEXT_154 = NL + "\t\tif (instanceValue == null) return null;";
  protected final String TEXT_155 = NL + "\t\tif (";
  protected final String TEXT_156 = ".isInstance(instanceValue))" + NL + "\t\t{" + NL + "\t\t\ttry" + NL + "\t\t\t{";
  protected final String TEXT_157 = NL + "\t\t\t\tString value = convert";
  protected final String TEXT_158 = "ToString(";
  protected final String TEXT_159 = ", instanceValue);";
  protected final String TEXT_160 = NL + "\t\t\t\tString value = ";
  protected final String TEXT_161 = ".convertToString(";
  protected final String TEXT_162 = ", instanceValue);";
  protected final String TEXT_163 = NL + "\t\t\t\tif (value != null) return value;" + NL + "\t\t\t}" + NL + "\t\t\tcatch (Exception e)" + NL + "\t\t\t{" + NL + "\t\t\t}" + NL + "\t\t}";
  protected final String TEXT_164 = NL + "\t\tthrow new IllegalArgumentException(\"Invalid value: '\"+instanceValue+\"' for datatype :\"+eDataType.getName());";
  protected final String TEXT_165 = NL + "\t\t// TODO: implement this method" + NL + "\t\t// Ensure that you remove @generated or mark it @generated NOT" + NL + "\t\tthrow new ";
  protected final String TEXT_166 = "();";
  protected final String TEXT_167 = NL + "\t\treturn super.convertToString(eDataType, instanceValue);";
  protected final String TEXT_168 = NL + "\t}" + NL;
  protected final String TEXT_169 = NL + "\t/**" + NL + "\t * Returns a new object of class '<em>";
  protected final String TEXT_170 = "</em>'." + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @return a new object of class '<em>";
  protected final String TEXT_171 = "</em>'." + NL + "\t * @generated" + NL + "\t */" + NL + "\t";
  protected final String TEXT_172 = " create";
  protected final String TEXT_173 = "();" + NL;
  protected final String TEXT_174 = NL + "\t/**" + NL + "\t * Returns the package supported by this factory." + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @return the package supported by this factory." + NL + "\t * @generated" + NL + "\t */" + NL + "\t";
  protected final String TEXT_175 = " get";
  protected final String TEXT_176 = "();" + NL;
  protected final String TEXT_177 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */" + NL + "\tpublic ";
  protected final String TEXT_178 = " get";
  protected final String TEXT_179 = "()" + NL + "\t{" + NL + "\t\treturn (";
  protected final String TEXT_180 = ")getEPackage();" + NL + "\t}" + NL + "" + NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @deprecated" + NL + "\t * @generated" + NL + "\t */" + NL + "\tpublic static ";
  protected final String TEXT_181 = " getPackage()" + NL + "\t{" + NL + "\t\treturn ";
  protected final String TEXT_182 = ".eINSTANCE;" + NL + "\t}" + NL;
  protected final String TEXT_183 = NL + "} //";
  protected final String TEXT_184 = NL;

  public String generate(Object argument)
  {
    StringBuffer stringBuffer = new StringBuffer();
    
/**
 * <copyright>
 *
 * Copyright (c) 2002-2005 IBM Corporation and others.
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

    GenPackage genPackage = (GenPackage)((Object[])argument)[0]; GenModel genModel=genPackage.getGenModel();
    boolean isInterface = Boolean.TRUE.equals(((Object[])argument)[1]); boolean isImplementation = Boolean.TRUE.equals(((Object[])argument)[2]);
    String publicStaticFinalFlag = isImplementation ? "public static final " : "";
    stringBuffer.append(TEXT_1);
    stringBuffer.append(TEXT_2);
    stringBuffer.append("$");
    stringBuffer.append(TEXT_3);
    stringBuffer.append("$");
    stringBuffer.append(TEXT_4);
    if (isInterface || genModel.isSuppressInterfaces()) {
    stringBuffer.append(TEXT_5);
    stringBuffer.append(genPackage.getReflectionPackageName());
    stringBuffer.append(TEXT_6);
    } else {
    stringBuffer.append(TEXT_7);
    stringBuffer.append(genPackage.getClassPackageName());
    stringBuffer.append(TEXT_8);
    }
    stringBuffer.append(TEXT_9);
    if (isImplementation) {
    genModel.addImport("org.eclipse.emf.ecore.EClass");
    genModel.addImport("org.eclipse.emf.ecore.EObject");
    if (!genPackage.hasJavaLangConflict() && !genPackage.hasInterfaceImplConflict() && !genPackage.getClassPackageName().equals(genPackage.getInterfacePackageName())) genModel.addImport(genPackage.getInterfacePackageName() + ".*");
    }
    genModel.markImportLocation(stringBuffer);
    stringBuffer.append(TEXT_10);
    if (isInterface) {
    stringBuffer.append(TEXT_11);
    if (!genModel.isSuppressEMFMetaData()) {
    stringBuffer.append(TEXT_12);
    stringBuffer.append(genPackage.getQualifiedPackageInterfaceName());
    }
    stringBuffer.append(TEXT_13);
    } else {
    stringBuffer.append(TEXT_14);
    }
    if (isImplementation) {
    stringBuffer.append(TEXT_15);
    stringBuffer.append(genPackage.getFactoryClassName());
    stringBuffer.append(TEXT_16);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.impl.EFactoryImpl"));
    if (!genModel.isSuppressInterfaces()) {
    stringBuffer.append(TEXT_17);
    stringBuffer.append(genPackage.getImportedFactoryInterfaceName());
    }
    } else {
    stringBuffer.append(TEXT_18);
    stringBuffer.append(genPackage.getFactoryInterfaceName());
    if (!genModel.isSuppressEMFMetaData()) {
    stringBuffer.append(TEXT_19);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.EFactory"));
    }
    }
    stringBuffer.append(TEXT_20);
    if (genModel.getCopyrightText() != null) {
    stringBuffer.append(TEXT_21);
    stringBuffer.append(publicStaticFinalFlag);
    stringBuffer.append(genModel.getImportedName("java.lang.String"));
    stringBuffer.append(TEXT_22);
    stringBuffer.append(genModel.getCopyrightText());
    stringBuffer.append(TEXT_23);
    stringBuffer.append(genModel.getNonNLS());
    stringBuffer.append(TEXT_24);
    }
    if (isImplementation && (genModel.isSuppressEMFMetaData() || genModel.isSuppressInterfaces())) {
    stringBuffer.append(TEXT_25);
    stringBuffer.append(publicStaticFinalFlag);
    stringBuffer.append(genPackage.getFactoryClassName());
    stringBuffer.append(TEXT_26);
    }
    if (isInterface && genModel.isSuppressEMFMetaData()) {
    stringBuffer.append(TEXT_27);
    stringBuffer.append(publicStaticFinalFlag);
    stringBuffer.append(genPackage.getFactoryInterfaceName());
    stringBuffer.append(TEXT_28);
    stringBuffer.append(genPackage.getQualifiedFactoryClassName());
    stringBuffer.append(TEXT_29);
    } else if (isInterface && !genModel.isSuppressInterfaces()) {
    stringBuffer.append(TEXT_30);
    stringBuffer.append(publicStaticFinalFlag);
    stringBuffer.append(genPackage.getFactoryInterfaceName());
    stringBuffer.append(TEXT_31);
    stringBuffer.append(genPackage.getQualifiedFactoryClassName());
    stringBuffer.append(TEXT_32);
    }
    if (isImplementation) {
    stringBuffer.append(TEXT_33);
    String factoryType = genModel.isSuppressEMFMetaData() ? genPackage.getFactoryClassName() : genPackage.getImportedFactoryInterfaceName();
    stringBuffer.append(TEXT_34);
    stringBuffer.append(factoryType);
    stringBuffer.append(TEXT_35);
    stringBuffer.append(factoryType);
    stringBuffer.append(TEXT_36);
    stringBuffer.append(genPackage.getFactoryInterfaceName());
    stringBuffer.append(TEXT_37);
    stringBuffer.append(factoryType);
    stringBuffer.append(TEXT_38);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.EPackage"));
    stringBuffer.append(TEXT_39);
    stringBuffer.append(genPackage.getNSURI());
    stringBuffer.append(TEXT_40);
    stringBuffer.append(genModel.getNonNLS());
    stringBuffer.append(TEXT_41);
    stringBuffer.append(genPackage.getFactoryInterfaceName());
    stringBuffer.append(TEXT_42);
    stringBuffer.append(genPackage.getFactoryInterfaceName());
    stringBuffer.append(TEXT_43);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.plugin.EcorePlugin"));
    stringBuffer.append(TEXT_44);
    stringBuffer.append(genPackage.getFactoryClassName());
    stringBuffer.append(TEXT_45);
    stringBuffer.append(genPackage.getFactoryClassName());
    stringBuffer.append(TEXT_46);
    for (Iterator i=genPackage.getGenClasses().iterator(); i.hasNext();) { GenClass genClass = (GenClass)i.next();
    if (!genClass.isAbstract()) {
    stringBuffer.append(TEXT_47);
    stringBuffer.append(genPackage.getImportedPackageInterfaceName());
    stringBuffer.append(TEXT_48);
    stringBuffer.append(genClass.getClassifierID());
    stringBuffer.append(TEXT_49);
    stringBuffer.append(!genClass.isEObjectExtension() ? "(EObject)" : "" );
    stringBuffer.append(TEXT_50);
    stringBuffer.append(genClass.getName());
    stringBuffer.append(TEXT_51);
    }
    }
    stringBuffer.append(TEXT_52);
    stringBuffer.append(genModel.getNonNLS());
    stringBuffer.append(genModel.getNonNLS(2));
    stringBuffer.append(TEXT_53);
    if (!genPackage.getAllGenDataTypes().isEmpty()) {
    stringBuffer.append(TEXT_54);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.EDataType"));
    stringBuffer.append(TEXT_55);
    for (Iterator i=genPackage.getAllGenDataTypes().iterator(); i.hasNext();) { GenDataType genDataType = (GenDataType)i.next();
    if (genDataType.isSerializable()) {
    stringBuffer.append(TEXT_56);
    stringBuffer.append(genPackage.getImportedPackageInterfaceName());
    stringBuffer.append(TEXT_57);
    stringBuffer.append(genDataType.getClassifierID());
    stringBuffer.append(TEXT_58);
    stringBuffer.append(genDataType.getName());
    stringBuffer.append(TEXT_59);
    }
    }
    stringBuffer.append(TEXT_60);
    stringBuffer.append(genModel.getNonNLS());
    stringBuffer.append(genModel.getNonNLS(2));
    stringBuffer.append(TEXT_61);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.EDataType"));
    stringBuffer.append(TEXT_62);
    for (Iterator i=genPackage.getAllGenDataTypes().iterator(); i.hasNext();) { GenDataType genDataType = (GenDataType)i.next();
    if (genDataType.isSerializable()) {
    stringBuffer.append(TEXT_63);
    stringBuffer.append(genPackage.getImportedPackageInterfaceName());
    stringBuffer.append(TEXT_64);
    stringBuffer.append(genDataType.getClassifierID());
    stringBuffer.append(TEXT_65);
    stringBuffer.append(genDataType.getName());
    stringBuffer.append(TEXT_66);
    }
    }
    stringBuffer.append(TEXT_67);
    stringBuffer.append(genModel.getNonNLS());
    stringBuffer.append(genModel.getNonNLS(2));
    stringBuffer.append(TEXT_68);
    }
    for (Iterator i=genPackage.getGenClasses().iterator(); i.hasNext();) { GenClass genClass = (GenClass)i.next();
    if (!genClass.isAbstract()) {
    stringBuffer.append(TEXT_69);
    stringBuffer.append(genClass.getImportedInterfaceName());
    stringBuffer.append(TEXT_70);
    stringBuffer.append(genClass.getName());
    stringBuffer.append(TEXT_71);
    if (genClass.isDynamic()) {
    stringBuffer.append(TEXT_72);
    stringBuffer.append(genClass.getImportedInterfaceName());
    stringBuffer.append(TEXT_73);
    stringBuffer.append(genClass.getSafeUncapName());
    stringBuffer.append(TEXT_74);
    stringBuffer.append(genClass.getCastFromEObject());
    stringBuffer.append(TEXT_75);
    stringBuffer.append(genClass.getQualifiedClassifierAccessor());
    stringBuffer.append(TEXT_76);
    } else {
    stringBuffer.append(TEXT_77);
    stringBuffer.append(genClass.getImportedClassName());
    stringBuffer.append(TEXT_78);
    stringBuffer.append(genClass.getSafeUncapName());
    stringBuffer.append(TEXT_79);
    stringBuffer.append(genClass.getImportedClassName());
    stringBuffer.append(TEXT_80);
    if (genModel.isSuppressInterfaces() && !genPackage.getReflectionPackageName().equals(genPackage.getInterfacePackageName())) {
    stringBuffer.append(TEXT_81);
    }
    stringBuffer.append(TEXT_82);
    }
    stringBuffer.append(TEXT_83);
    stringBuffer.append(genClass.getSafeUncapName());
    stringBuffer.append(TEXT_84);
    }
    }
    for (Iterator i=genPackage.getAllGenDataTypes().iterator(); i.hasNext();) { GenDataType genDataType = (GenDataType)i.next();
    if (genDataType.isSerializable()) {
    stringBuffer.append(TEXT_85);
    stringBuffer.append(genDataType.getObjectInstanceClassName());
    stringBuffer.append(TEXT_86);
    stringBuffer.append(genDataType.getName());
    stringBuffer.append(TEXT_87);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.EDataType"));
    stringBuffer.append(TEXT_88);
    if (genDataType instanceof GenEnum) {
    stringBuffer.append(TEXT_89);
    stringBuffer.append(((GenEnum)genDataType).getImportedName());
    stringBuffer.append(TEXT_90);
    stringBuffer.append(((GenEnum)genDataType).getImportedName());
    stringBuffer.append(TEXT_91);
    stringBuffer.append(genModel.getNonNLS());
    stringBuffer.append(genModel.getNonNLS(2));
    stringBuffer.append(genModel.getNonNLS(3));
    stringBuffer.append(TEXT_92);
    } else if (genDataType.getBaseType() != null) { GenDataType genBaseType = genDataType.getBaseType(); 
    if (genBaseType.getGenPackage() == genPackage) {
    stringBuffer.append(TEXT_93);
    stringBuffer.append(genDataType.getObjectInstanceClassName());
    stringBuffer.append(TEXT_94);
    stringBuffer.append(genBaseType.getName());
    stringBuffer.append(TEXT_95);
    stringBuffer.append(genBaseType.getQualifiedClassifierAccessor());
    stringBuffer.append(TEXT_96);
    } else {
    stringBuffer.append(TEXT_97);
    stringBuffer.append(genDataType.getObjectInstanceClassName());
    stringBuffer.append(TEXT_98);
    stringBuffer.append(genBaseType.getGenPackage().getQualifiedEFactoryInternalInstanceAccessor());
    stringBuffer.append(TEXT_99);
    stringBuffer.append(genBaseType.getQualifiedClassifierAccessor());
    stringBuffer.append(TEXT_100);
    }
    } else if (genDataType.getItemType() != null) { GenDataType genItemType = genDataType.getItemType(); 
    stringBuffer.append(TEXT_101);
    stringBuffer.append(genModel.getImportedName("java.util.List"));
    stringBuffer.append(TEXT_102);
    stringBuffer.append(genModel.getImportedName("java.util.ArrayList"));
    stringBuffer.append(TEXT_103);
    stringBuffer.append(genModel.getImportedName("java.util.StringTokenizer"));
    stringBuffer.append(TEXT_104);
    stringBuffer.append(genModel.getImportedName("java.util.StringTokenizer"));
    stringBuffer.append(TEXT_105);
    if (genItemType.getGenPackage() == genPackage) {
    stringBuffer.append(TEXT_106);
    stringBuffer.append(genItemType.getName());
    stringBuffer.append(TEXT_107);
    stringBuffer.append(genItemType.getQualifiedClassifierAccessor());
    stringBuffer.append(TEXT_108);
    } else {
    stringBuffer.append(TEXT_109);
    stringBuffer.append(genItemType.getGenPackage().getQualifiedEFactoryInternalInstanceAccessor());
    stringBuffer.append(TEXT_110);
    stringBuffer.append(genItemType.getQualifiedClassifierAccessor());
    stringBuffer.append(TEXT_111);
    }
    stringBuffer.append(TEXT_112);
    } else if (!genDataType.getMemberTypes().isEmpty()) {
    stringBuffer.append(TEXT_113);
    stringBuffer.append(genDataType.getObjectInstanceClassName());
    stringBuffer.append(TEXT_114);
    for (Iterator j = genDataType.getMemberTypes().iterator(); j.hasNext(); ) { GenDataType genMemberType = (GenDataType)j.next();
    stringBuffer.append(TEXT_115);
    if (genMemberType.getGenPackage() == genPackage) {
    stringBuffer.append(TEXT_116);
    stringBuffer.append(genDataType.getObjectInstanceClassName());
    stringBuffer.append(TEXT_117);
    stringBuffer.append(genMemberType.getName());
    stringBuffer.append(TEXT_118);
    stringBuffer.append(genMemberType.getQualifiedClassifierAccessor());
    stringBuffer.append(TEXT_119);
    } else {
    stringBuffer.append(TEXT_120);
    stringBuffer.append(genDataType.getObjectInstanceClassName());
    stringBuffer.append(TEXT_121);
    stringBuffer.append(genMemberType.getGenPackage().getQualifiedEFactoryInternalInstanceAccessor());
    stringBuffer.append(TEXT_122);
    stringBuffer.append(genMemberType.getQualifiedClassifierAccessor());
    stringBuffer.append(TEXT_123);
    }
    stringBuffer.append(TEXT_124);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.Diagnostician"));
    stringBuffer.append(TEXT_125);
    }
    stringBuffer.append(TEXT_126);
    } else if (genDataType.isArrayType()) {
    stringBuffer.append(TEXT_127);
    stringBuffer.append(genModel.getImportedName("java.lang.UnsupportedOperationException"));
    stringBuffer.append(TEXT_128);
    } else {
    stringBuffer.append(TEXT_129);
    stringBuffer.append(genDataType.getObjectInstanceClassName());
    stringBuffer.append(TEXT_130);
    }
    stringBuffer.append(TEXT_131);
    stringBuffer.append(genDataType.getName());
    stringBuffer.append(TEXT_132);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.EDataType"));
    stringBuffer.append(TEXT_133);
    if (genDataType instanceof GenEnum) {
    stringBuffer.append(TEXT_134);
    } else if (genDataType.getBaseType() != null) { GenDataType genBaseType = genDataType.getBaseType(); 
    if (genBaseType.getGenPackage() == genPackage) {
    stringBuffer.append(TEXT_135);
    stringBuffer.append(genBaseType.getName());
    stringBuffer.append(TEXT_136);
    stringBuffer.append(genBaseType.getQualifiedClassifierAccessor());
    stringBuffer.append(TEXT_137);
    } else {
    stringBuffer.append(TEXT_138);
    stringBuffer.append(genBaseType.getGenPackage().getQualifiedEFactoryInternalInstanceAccessor());
    stringBuffer.append(TEXT_139);
    stringBuffer.append(genBaseType.getQualifiedClassifierAccessor());
    stringBuffer.append(TEXT_140);
    }
    } else if (genDataType.getItemType() != null) { GenDataType genItemType = genDataType.getItemType(); 
    stringBuffer.append(TEXT_141);
    stringBuffer.append(genModel.getImportedName("java.util.List"));
    stringBuffer.append(TEXT_142);
    stringBuffer.append(genModel.getImportedName("java.util.List"));
    stringBuffer.append(TEXT_143);
    stringBuffer.append(genModel.getImportedName("java.lang.StringBuffer"));
    stringBuffer.append(TEXT_144);
    stringBuffer.append(genModel.getImportedName("java.lang.StringBuffer"));
    stringBuffer.append(TEXT_145);
    stringBuffer.append(genModel.getImportedName("java.util.Iterator"));
    stringBuffer.append(TEXT_146);
    if (genItemType.getGenPackage() == genPackage) {
    stringBuffer.append(TEXT_147);
    stringBuffer.append(genItemType.getName());
    stringBuffer.append(TEXT_148);
    stringBuffer.append(genItemType.getQualifiedClassifierAccessor());
    stringBuffer.append(TEXT_149);
    } else {
    stringBuffer.append(TEXT_150);
    stringBuffer.append(genItemType.getGenPackage().getQualifiedEFactoryInternalInstanceAccessor());
    stringBuffer.append(TEXT_151);
    stringBuffer.append(genItemType.getQualifiedClassifierAccessor());
    stringBuffer.append(TEXT_152);
    }
    stringBuffer.append(TEXT_153);
    } else if (!genDataType.getMemberTypes().isEmpty()) {
    stringBuffer.append(TEXT_154);
    for (Iterator j = genDataType.getMemberTypes().iterator(); j.hasNext(); ) { GenDataType genMemberType = (GenDataType)j.next();
    stringBuffer.append(TEXT_155);
    stringBuffer.append(genMemberType.getQualifiedClassifierAccessor());
    stringBuffer.append(TEXT_156);
    if (genMemberType.getGenPackage() == genPackage) {
    stringBuffer.append(TEXT_157);
    stringBuffer.append(genMemberType.getName());
    stringBuffer.append(TEXT_158);
    stringBuffer.append(genMemberType.getQualifiedClassifierAccessor());
    stringBuffer.append(TEXT_159);
    } else {
    stringBuffer.append(TEXT_160);
    stringBuffer.append(genMemberType.getGenPackage().getQualifiedEFactoryInternalInstanceAccessor());
    stringBuffer.append(TEXT_161);
    stringBuffer.append(genMemberType.getQualifiedClassifierAccessor());
    stringBuffer.append(TEXT_162);
    }
    stringBuffer.append(TEXT_163);
    }
    stringBuffer.append(TEXT_164);
    } else if (genDataType.isArrayType()) {
    stringBuffer.append(TEXT_165);
    stringBuffer.append(genModel.getImportedName("java.lang.UnsupportedOperationException"));
    stringBuffer.append(TEXT_166);
    } else {
    stringBuffer.append(TEXT_167);
    }
    stringBuffer.append(TEXT_168);
    }
    }
    } else {
    for (Iterator i=genPackage.getGenClasses().iterator(); i.hasNext();) { GenClass genClass = (GenClass)i.next();
    if (genClass.hasFactoryInterfaceCreateMethod()) {
    stringBuffer.append(TEXT_169);
    stringBuffer.append(genClass.getFormattedName());
    stringBuffer.append(TEXT_170);
    stringBuffer.append(genClass.getFormattedName());
    stringBuffer.append(TEXT_171);
    stringBuffer.append(genClass.getImportedInterfaceName());
    stringBuffer.append(TEXT_172);
    stringBuffer.append(genClass.getName());
    stringBuffer.append(TEXT_173);
    }
    }
    }
    if (!isImplementation && !genModel.isSuppressEMFMetaData()) {
    stringBuffer.append(TEXT_174);
    stringBuffer.append(genPackage.getPackageInterfaceName());
    stringBuffer.append(TEXT_175);
    stringBuffer.append(genPackage.getPackageInterfaceName());
    stringBuffer.append(TEXT_176);
    } else if (isImplementation) {
    stringBuffer.append(TEXT_177);
    stringBuffer.append(genPackage.getImportedPackageInterfaceName());
    stringBuffer.append(TEXT_178);
    stringBuffer.append(genPackage.getPackageInterfaceName());
    stringBuffer.append(TEXT_179);
    stringBuffer.append(genPackage.getImportedPackageInterfaceName());
    stringBuffer.append(TEXT_180);
    stringBuffer.append(genPackage.getImportedPackageInterfaceName());
    stringBuffer.append(TEXT_181);
    stringBuffer.append(genPackage.getImportedPackageInterfaceName());
    stringBuffer.append(TEXT_182);
    }
    stringBuffer.append(TEXT_183);
    stringBuffer.append(isInterface ? genPackage.getFactoryInterfaceName() : genPackage.getFactoryClassName());
    genModel.emitSortedImports();
    stringBuffer.append(TEXT_184);
    return stringBuffer.toString();
  }
}
