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
  protected final String TEXT_4 = NL + " */" + NL + "package ";
  protected final String TEXT_5 = ";" + NL;
  protected final String TEXT_6 = NL + NL + "/**" + NL + " * <!-- begin-user-doc -->" + NL + " * An implementation of the model <b>Factory</b>." + NL + " * <!-- end-user-doc -->" + NL + " * @generated" + NL + " */" + NL + "public class ";
  protected final String TEXT_7 = " extends ";
  protected final String TEXT_8 = " implements ";
  protected final String TEXT_9 = NL + "{";
  protected final String TEXT_10 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */" + NL + "\tpublic static final ";
  protected final String TEXT_11 = " copyright = \"";
  protected final String TEXT_12 = "\";";
  protected final String TEXT_13 = NL;
  protected final String TEXT_14 = NL + "\t/**" + NL + "\t * Creates the default factory implementation." + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */" + NL + "\tpublic static ";
  protected final String TEXT_15 = " init()" + NL + "\t{" + NL + "\t\ttry" + NL + "\t\t{" + NL + "\t\t\t";
  protected final String TEXT_16 = " the";
  protected final String TEXT_17 = " = (";
  protected final String TEXT_18 = ")";
  protected final String TEXT_19 = ".Registry.INSTANCE.getEFactory(\"";
  protected final String TEXT_20 = "\");";
  protected final String TEXT_21 = " " + NL + "\t\t\tif (the";
  protected final String TEXT_22 = " != null)" + NL + "\t\t\t{" + NL + "\t\t\t\treturn the";
  protected final String TEXT_23 = ";" + NL + "\t\t\t}" + NL + "\t\t}" + NL + "\t\tcatch (Exception exception)" + NL + "\t\t{" + NL + "\t\t\t";
  protected final String TEXT_24 = ".INSTANCE.log(exception);" + NL + "\t\t}" + NL + "\t\treturn new ";
  protected final String TEXT_25 = "();" + NL + "\t}" + NL + "" + NL + "\t/**" + NL + "\t * Creates an instance of the factory." + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */" + NL + "\tpublic ";
  protected final String TEXT_26 = "()" + NL + "\t{" + NL + "\t\tsuper();" + NL + "\t}" + NL + "" + NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */" + NL + "\tpublic EObject create(EClass eClass)" + NL + "\t{" + NL + "\t\tswitch (eClass.getClassifierID())" + NL + "\t\t{";
  protected final String TEXT_27 = NL + "\t\t\tcase ";
  protected final String TEXT_28 = ".";
  protected final String TEXT_29 = ": return ";
  protected final String TEXT_30 = "create";
  protected final String TEXT_31 = "();";
  protected final String TEXT_32 = NL + "\t\t\tdefault:" + NL + "\t\t\t\tthrow new IllegalArgumentException(\"The class '\" + eClass.getName() + \"' is not a valid classifier\");";
  protected final String TEXT_33 = NL + "\t\t}" + NL + "\t}";
  protected final String TEXT_34 = NL + NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */" + NL + "\tpublic Object createFromString(";
  protected final String TEXT_35 = " eDataType, String initialValue)" + NL + "\t{" + NL + "\t\tswitch (eDataType.getClassifierID())" + NL + "\t\t{";
  protected final String TEXT_36 = NL + "\t\t\tcase ";
  protected final String TEXT_37 = ".";
  protected final String TEXT_38 = ":" + NL + "\t\t\t\treturn create";
  protected final String TEXT_39 = "FromString(eDataType, initialValue);";
  protected final String TEXT_40 = NL + "\t\t\tdefault:" + NL + "\t\t\t\tthrow new IllegalArgumentException(\"The datatype '\" + eDataType.getName() + \"' is not a valid classifier\");";
  protected final String TEXT_41 = NL + "\t\t}" + NL + "\t}" + NL + "" + NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */" + NL + "\tpublic String convertToString(";
  protected final String TEXT_42 = " eDataType, Object instanceValue)" + NL + "\t{" + NL + "\t\tswitch (eDataType.getClassifierID())" + NL + "\t\t{";
  protected final String TEXT_43 = NL + "\t\t\tcase ";
  protected final String TEXT_44 = ".";
  protected final String TEXT_45 = ":" + NL + "\t\t\t\treturn convert";
  protected final String TEXT_46 = "ToString(eDataType, instanceValue);";
  protected final String TEXT_47 = NL + "\t\t\tdefault:" + NL + "\t\t\t\tthrow new IllegalArgumentException(\"The datatype '\" + eDataType.getName() + \"' is not a valid classifier\");";
  protected final String TEXT_48 = NL + "\t\t}" + NL + "\t}";
  protected final String TEXT_49 = NL + NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */" + NL + "\tpublic ";
  protected final String TEXT_50 = " create";
  protected final String TEXT_51 = "()" + NL + "\t{" + NL + "\t\t";
  protected final String TEXT_52 = " ";
  protected final String TEXT_53 = " = new ";
  protected final String TEXT_54 = "();" + NL + "\t\treturn ";
  protected final String TEXT_55 = ";" + NL + "\t}";
  protected final String TEXT_56 = NL + NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */" + NL + "\tpublic ";
  protected final String TEXT_57 = " create";
  protected final String TEXT_58 = "FromString(";
  protected final String TEXT_59 = " eDataType, String initialValue)" + NL + "\t{";
  protected final String TEXT_60 = NL + "\t\t";
  protected final String TEXT_61 = " result = ";
  protected final String TEXT_62 = ".get(initialValue);" + NL + "\t\tif (result == null) throw new IllegalArgumentException(\"The value '\" + initialValue + \"' is not a valid enumerator of '\" + eDataType.getName() + \"'\");";
  protected final String TEXT_63 = NL + "\t\treturn result;";
  protected final String TEXT_64 = NL + "\t\treturn (";
  protected final String TEXT_65 = ")create";
  protected final String TEXT_66 = "FromString(";
  protected final String TEXT_67 = ".eINSTANCE.get";
  protected final String TEXT_68 = "(), initialValue);";
  protected final String TEXT_69 = NL + "\t\treturn (";
  protected final String TEXT_70 = ")";
  protected final String TEXT_71 = ".eINSTANCE.createFromString(";
  protected final String TEXT_72 = ".eINSTANCE.get";
  protected final String TEXT_73 = "(), initialValue);";
  protected final String TEXT_74 = NL + "\t\tif (initialValue == null) return null;" + NL + "\t\t";
  protected final String TEXT_75 = " result = new ";
  protected final String TEXT_76 = "();" + NL + "\t\tfor (";
  protected final String TEXT_77 = " stringTokenizer = new ";
  protected final String TEXT_78 = "(initialValue); stringTokenizer.hasMoreTokens(); )" + NL + "\t\t{" + NL + "\t\t\tString item = stringTokenizer.nextToken();";
  protected final String TEXT_79 = NL + "\t\t\tresult.add(create";
  protected final String TEXT_80 = "FromString(";
  protected final String TEXT_81 = ".eINSTANCE.get";
  protected final String TEXT_82 = "(), item));";
  protected final String TEXT_83 = NL + "\t\t\tresult.add(";
  protected final String TEXT_84 = ".eINSTANCE.createFromString(";
  protected final String TEXT_85 = ".eINSTANCE.get";
  protected final String TEXT_86 = "(), item));";
  protected final String TEXT_87 = NL + "\t\t}" + NL + "\t\treturn result;";
  protected final String TEXT_88 = NL + "\t\tif (initialValue == null) return null;" + NL + "\t\t";
  protected final String TEXT_89 = " result = null;" + NL + "\t\tRuntimeException exception = null;";
  protected final String TEXT_90 = NL + "\t\ttry" + NL + "\t\t{";
  protected final String TEXT_91 = NL + "\t\t\tresult = (";
  protected final String TEXT_92 = ")create";
  protected final String TEXT_93 = "FromString(";
  protected final String TEXT_94 = ".eINSTANCE.get";
  protected final String TEXT_95 = "(), initialValue);";
  protected final String TEXT_96 = NL + "\t\t\tresult = (";
  protected final String TEXT_97 = ")";
  protected final String TEXT_98 = ".eINSTANCE.createFromString(";
  protected final String TEXT_99 = ".eINSTANCE.get";
  protected final String TEXT_100 = "(), initialValue);";
  protected final String TEXT_101 = NL + "\t\t\tif (result != null && ";
  protected final String TEXT_102 = ".INSTANCE.validate(eDataType, result, null, null))" + NL + "\t\t\t{" + NL + "\t\t\t\treturn result;" + NL + "\t\t\t}" + NL + "\t\t}" + NL + "\t\tcatch (RuntimeException e)" + NL + "\t\t{" + NL + "\t\t\texception = e;" + NL + "\t\t}";
  protected final String TEXT_103 = NL + "\t\tif (result != null || exception == null) return result;" + NL + "    " + NL + "\t\tthrow exception;";
  protected final String TEXT_104 = NL + "\t\t// TODO: implement this method" + NL + "\t\t// Ensure that you remove @generated or mark it @generated NOT" + NL + "\t\tthrow new ";
  protected final String TEXT_105 = "();";
  protected final String TEXT_106 = NL + "\t\treturn (";
  protected final String TEXT_107 = ")super.createFromString(eDataType, initialValue);";
  protected final String TEXT_108 = NL + "\t}" + NL + "" + NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */" + NL + "\tpublic String convert";
  protected final String TEXT_109 = "ToString(";
  protected final String TEXT_110 = " eDataType, Object instanceValue)" + NL + "\t{";
  protected final String TEXT_111 = NL + "\t\treturn instanceValue == null ? null : instanceValue.toString();";
  protected final String TEXT_112 = NL + "\t\treturn convert";
  protected final String TEXT_113 = "ToString(";
  protected final String TEXT_114 = ".eINSTANCE.get";
  protected final String TEXT_115 = "(), instanceValue);";
  protected final String TEXT_116 = NL + "\t\treturn ";
  protected final String TEXT_117 = ".eINSTANCE.convertToString(";
  protected final String TEXT_118 = ".eINSTANCE.get";
  protected final String TEXT_119 = "(), instanceValue);";
  protected final String TEXT_120 = NL + "\t\tif (instanceValue == null) return null;" + NL + "\t\t";
  protected final String TEXT_121 = " list = (";
  protected final String TEXT_122 = ")instanceValue;" + NL + "\t\tif (list.isEmpty()) return \"\";" + NL + "\t\t";
  protected final String TEXT_123 = " result = new ";
  protected final String TEXT_124 = "();" + NL + "\t\tfor (";
  protected final String TEXT_125 = " i = list.iterator(); i.hasNext(); )" + NL + "\t\t{";
  protected final String TEXT_126 = NL + "\t\t\tresult.append(convert";
  protected final String TEXT_127 = "ToString(";
  protected final String TEXT_128 = ".eINSTANCE.get";
  protected final String TEXT_129 = "(), i.next()));";
  protected final String TEXT_130 = NL + "\t\t\tresult.append(";
  protected final String TEXT_131 = ".eINSTANCE.convertToString(";
  protected final String TEXT_132 = ".eINSTANCE.get";
  protected final String TEXT_133 = "(), i.next()));";
  protected final String TEXT_134 = NL + "\t\t\tresult.append(' ');" + NL + "\t\t}" + NL + "\t\treturn result.substring(0, result.length() - 1);";
  protected final String TEXT_135 = NL + "\t\tif (instanceValue == null) return null;";
  protected final String TEXT_136 = NL + "\t\tif (";
  protected final String TEXT_137 = ".eINSTANCE.get";
  protected final String TEXT_138 = "().isInstance(instanceValue))" + NL + "\t\t{" + NL + "\t\t\ttry" + NL + "\t\t\t{";
  protected final String TEXT_139 = NL + "\t\t\t\tString value = convert";
  protected final String TEXT_140 = "ToString(";
  protected final String TEXT_141 = ".eINSTANCE.get";
  protected final String TEXT_142 = "(), instanceValue);";
  protected final String TEXT_143 = NL + "\t\t\t\tString value = ";
  protected final String TEXT_144 = ".eINSTANCE.convertToString(";
  protected final String TEXT_145 = ".eINSTANCE.get";
  protected final String TEXT_146 = "(), instanceValue);";
  protected final String TEXT_147 = NL + "\t\t\t\tif (value != null) return value;" + NL + "\t\t\t}" + NL + "\t\t\tcatch (Exception e)" + NL + "\t\t\t{" + NL + "\t\t\t}" + NL + "\t\t}";
  protected final String TEXT_148 = NL + "\t\tthrow new IllegalArgumentException(\"Invalid value: '\"+instanceValue+\"' for datatype :\"+eDataType.getName());";
  protected final String TEXT_149 = NL + "\t\t// TODO: implement this method" + NL + "\t\t// Ensure that you remove @generated or mark it @generated NOT" + NL + "\t\tthrow new ";
  protected final String TEXT_150 = "();";
  protected final String TEXT_151 = NL + "\t\treturn super.convertToString(eDataType, instanceValue);";
  protected final String TEXT_152 = NL + "\t}";
  protected final String TEXT_153 = NL + NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */" + NL + "\tpublic ";
  protected final String TEXT_154 = " get";
  protected final String TEXT_155 = "()" + NL + "\t{" + NL + "\t\treturn (";
  protected final String TEXT_156 = ")getEPackage();" + NL + "\t}" + NL + "" + NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @deprecated" + NL + "\t * @generated" + NL + "\t */" + NL + "\tpublic static ";
  protected final String TEXT_157 = " getPackage()" + NL + "\t{" + NL + "\t\treturn ";
  protected final String TEXT_158 = ".eINSTANCE;" + NL + "\t}" + NL + "" + NL + "} //";
  protected final String TEXT_159 = NL;

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

    GenPackage genPackage = (GenPackage)argument; GenModel genModel=genPackage.getGenModel();
    stringBuffer.append(TEXT_1);
    stringBuffer.append(TEXT_2);
    stringBuffer.append("$");
    stringBuffer.append(TEXT_3);
    stringBuffer.append("$");
    stringBuffer.append(TEXT_4);
    stringBuffer.append(genPackage.getClassPackageName());
    stringBuffer.append(TEXT_5);
    genModel.addImport("org.eclipse.emf.ecore.EClass");
    genModel.addImport("org.eclipse.emf.ecore.EObject");
    if (!genPackage.hasJavaLangConflict() && !genPackage.hasInterfaceImplConflict() && !genPackage.getClassPackageName().equals(genPackage.getInterfacePackageName())) genModel.addImport(genPackage.getInterfacePackageName() + ".*");
    genModel.markImportLocation(stringBuffer);
    stringBuffer.append(TEXT_6);
    stringBuffer.append(genPackage.getFactoryClassName());
    stringBuffer.append(TEXT_7);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.impl.EFactoryImpl"));
    stringBuffer.append(TEXT_8);
    stringBuffer.append(genPackage.getImportedFactoryInterfaceName());
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
    stringBuffer.append(TEXT_14);
    stringBuffer.append(genPackage.getImportedFactoryInterfaceName());
    stringBuffer.append(TEXT_15);
    stringBuffer.append(genPackage.getImportedFactoryInterfaceName());
    stringBuffer.append(TEXT_16);
    stringBuffer.append(genPackage.getFactoryInterfaceName());
    stringBuffer.append(TEXT_17);
    stringBuffer.append(genPackage.getImportedFactoryInterfaceName());
    stringBuffer.append(TEXT_18);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.EPackage"));
    stringBuffer.append(TEXT_19);
    stringBuffer.append(genPackage.getNSURI());
    stringBuffer.append(TEXT_20);
    stringBuffer.append(genModel.getNonNLS());
    stringBuffer.append(TEXT_21);
    stringBuffer.append(genPackage.getFactoryInterfaceName());
    stringBuffer.append(TEXT_22);
    stringBuffer.append(genPackage.getFactoryInterfaceName());
    stringBuffer.append(TEXT_23);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.plugin.EcorePlugin"));
    stringBuffer.append(TEXT_24);
    stringBuffer.append(genPackage.getFactoryClassName());
    stringBuffer.append(TEXT_25);
    stringBuffer.append(genPackage.getFactoryClassName());
    stringBuffer.append(TEXT_26);
    for (Iterator i=genPackage.getGenClasses().iterator(); i.hasNext();) { GenClass genClass = (GenClass)i.next();
    if (!genClass.isAbstract()) {
    stringBuffer.append(TEXT_27);
    stringBuffer.append(genPackage.getImportedPackageInterfaceName());
    stringBuffer.append(TEXT_28);
    stringBuffer.append(genClass.getClassifierID());
    stringBuffer.append(TEXT_29);
    stringBuffer.append(!genClass.isEObjectExtension() ? "(EObject)" : "" );
    stringBuffer.append(TEXT_30);
    stringBuffer.append(genClass.getName());
    stringBuffer.append(TEXT_31);
    }
    }
    stringBuffer.append(TEXT_32);
    stringBuffer.append(genModel.getNonNLS());
    stringBuffer.append(genModel.getNonNLS(2));
    stringBuffer.append(TEXT_33);
    if (!genPackage.getAllGenDataTypes().isEmpty()) {
    stringBuffer.append(TEXT_34);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.EDataType"));
    stringBuffer.append(TEXT_35);
    for (Iterator i=genPackage.getAllGenDataTypes().iterator(); i.hasNext();) { GenDataType genDataType = (GenDataType)i.next();
    if (genDataType.isSerializable()) {
    stringBuffer.append(TEXT_36);
    stringBuffer.append(genPackage.getImportedPackageInterfaceName());
    stringBuffer.append(TEXT_37);
    stringBuffer.append(genDataType.getClassifierID());
    stringBuffer.append(TEXT_38);
    stringBuffer.append(genDataType.getName());
    stringBuffer.append(TEXT_39);
    }
    }
    stringBuffer.append(TEXT_40);
    stringBuffer.append(genModel.getNonNLS());
    stringBuffer.append(genModel.getNonNLS(2));
    stringBuffer.append(TEXT_41);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.EDataType"));
    stringBuffer.append(TEXT_42);
    for (Iterator i=genPackage.getAllGenDataTypes().iterator(); i.hasNext();) { GenDataType genDataType = (GenDataType)i.next();
    if (genDataType.isSerializable()) {
    stringBuffer.append(TEXT_43);
    stringBuffer.append(genPackage.getImportedPackageInterfaceName());
    stringBuffer.append(TEXT_44);
    stringBuffer.append(genDataType.getClassifierID());
    stringBuffer.append(TEXT_45);
    stringBuffer.append(genDataType.getName());
    stringBuffer.append(TEXT_46);
    }
    }
    stringBuffer.append(TEXT_47);
    stringBuffer.append(genModel.getNonNLS());
    stringBuffer.append(genModel.getNonNLS(2));
    stringBuffer.append(TEXT_48);
    }
    for (Iterator i=genPackage.getGenClasses().iterator(); i.hasNext();) { GenClass genClass = (GenClass)i.next();
    if (!genClass.isAbstract()) {
    stringBuffer.append(TEXT_49);
    stringBuffer.append(genClass.getImportedInterfaceName());
    stringBuffer.append(TEXT_50);
    stringBuffer.append(genClass.getName());
    stringBuffer.append(TEXT_51);
    stringBuffer.append(genClass.getImportedClassName());
    stringBuffer.append(TEXT_52);
    stringBuffer.append(genClass.getSafeUncapName());
    stringBuffer.append(TEXT_53);
    stringBuffer.append(genClass.getImportedClassName());
    stringBuffer.append(TEXT_54);
    stringBuffer.append(genClass.getSafeUncapName());
    stringBuffer.append(TEXT_55);
    }
    }
    for (Iterator i=genPackage.getAllGenDataTypes().iterator(); i.hasNext();) { GenDataType genDataType = (GenDataType)i.next();
    if (genDataType.isSerializable()) {
    stringBuffer.append(TEXT_56);
    stringBuffer.append(genDataType.getObjectInstanceClassName());
    stringBuffer.append(TEXT_57);
    stringBuffer.append(genDataType.getName());
    stringBuffer.append(TEXT_58);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.EDataType"));
    stringBuffer.append(TEXT_59);
    if (genDataType instanceof GenEnum) {
    stringBuffer.append(TEXT_60);
    stringBuffer.append(((GenEnum)genDataType).getImportedName());
    stringBuffer.append(TEXT_61);
    stringBuffer.append(((GenEnum)genDataType).getImportedName());
    stringBuffer.append(TEXT_62);
    stringBuffer.append(genModel.getNonNLS());
    stringBuffer.append(genModel.getNonNLS(2));
    stringBuffer.append(genModel.getNonNLS(3));
    stringBuffer.append(TEXT_63);
    } else if (genDataType.getBaseType() != null) { GenDataType genBaseType = genDataType.getBaseType(); 
    if (genBaseType.getGenPackage() == genPackage) {
    stringBuffer.append(TEXT_64);
    stringBuffer.append(genDataType.getObjectInstanceClassName());
    stringBuffer.append(TEXT_65);
    stringBuffer.append(genBaseType.getName());
    stringBuffer.append(TEXT_66);
    stringBuffer.append(genBaseType.getGenPackage().getImportedPackageInterfaceName());
    stringBuffer.append(TEXT_67);
    stringBuffer.append(genBaseType.getClassifierAccessorName());
    stringBuffer.append(TEXT_68);
    } else {
    stringBuffer.append(TEXT_69);
    stringBuffer.append(genDataType.getObjectInstanceClassName());
    stringBuffer.append(TEXT_70);
    stringBuffer.append(genBaseType.getGenPackage().getImportedFactoryInterfaceName());
    stringBuffer.append(TEXT_71);
    stringBuffer.append(genBaseType.getGenPackage().getImportedPackageInterfaceName());
    stringBuffer.append(TEXT_72);
    stringBuffer.append(genBaseType.getClassifierAccessorName());
    stringBuffer.append(TEXT_73);
    }
    } else if (genDataType.getItemType() != null) { GenDataType genItemType = genDataType.getItemType(); 
    stringBuffer.append(TEXT_74);
    stringBuffer.append(genModel.getImportedName("java.util.List"));
    stringBuffer.append(TEXT_75);
    stringBuffer.append(genModel.getImportedName("java.util.ArrayList"));
    stringBuffer.append(TEXT_76);
    stringBuffer.append(genModel.getImportedName("java.util.StringTokenizer"));
    stringBuffer.append(TEXT_77);
    stringBuffer.append(genModel.getImportedName("java.util.StringTokenizer"));
    stringBuffer.append(TEXT_78);
    if (genItemType.getGenPackage() == genPackage) {
    stringBuffer.append(TEXT_79);
    stringBuffer.append(genItemType.getName());
    stringBuffer.append(TEXT_80);
    stringBuffer.append(genItemType.getGenPackage().getImportedPackageInterfaceName());
    stringBuffer.append(TEXT_81);
    stringBuffer.append(genItemType.getClassifierAccessorName());
    stringBuffer.append(TEXT_82);
    } else {
    stringBuffer.append(TEXT_83);
    stringBuffer.append(genItemType.getGenPackage().getImportedFactoryInterfaceName());
    stringBuffer.append(TEXT_84);
    stringBuffer.append(genItemType.getGenPackage().getImportedPackageInterfaceName());
    stringBuffer.append(TEXT_85);
    stringBuffer.append(genItemType.getClassifierAccessorName());
    stringBuffer.append(TEXT_86);
    }
    stringBuffer.append(TEXT_87);
    } else if (!genDataType.getMemberTypes().isEmpty()) {
    stringBuffer.append(TEXT_88);
    stringBuffer.append(genDataType.getObjectInstanceClassName());
    stringBuffer.append(TEXT_89);
    for (Iterator j = genDataType.getMemberTypes().iterator(); j.hasNext(); ) { GenDataType genMemberType = (GenDataType)j.next();
    stringBuffer.append(TEXT_90);
    if (genMemberType.getGenPackage() == genPackage) {
    stringBuffer.append(TEXT_91);
    stringBuffer.append(genDataType.getObjectInstanceClassName());
    stringBuffer.append(TEXT_92);
    stringBuffer.append(genMemberType.getName());
    stringBuffer.append(TEXT_93);
    stringBuffer.append(genMemberType.getGenPackage().getImportedPackageInterfaceName());
    stringBuffer.append(TEXT_94);
    stringBuffer.append(genMemberType.getClassifierAccessorName());
    stringBuffer.append(TEXT_95);
    } else {
    stringBuffer.append(TEXT_96);
    stringBuffer.append(genDataType.getObjectInstanceClassName());
    stringBuffer.append(TEXT_97);
    stringBuffer.append(genMemberType.getGenPackage().getImportedFactoryInterfaceName());
    stringBuffer.append(TEXT_98);
    stringBuffer.append(genMemberType.getGenPackage().getImportedPackageInterfaceName());
    stringBuffer.append(TEXT_99);
    stringBuffer.append(genMemberType.getClassifierAccessorName());
    stringBuffer.append(TEXT_100);
    }
    stringBuffer.append(TEXT_101);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.Diagnostician"));
    stringBuffer.append(TEXT_102);
    }
    stringBuffer.append(TEXT_103);
    } else if (genDataType.isArrayType()) {
    stringBuffer.append(TEXT_104);
    stringBuffer.append(genModel.getImportedName("java.lang.UnsupportedOperationException"));
    stringBuffer.append(TEXT_105);
    } else {
    stringBuffer.append(TEXT_106);
    stringBuffer.append(genDataType.getObjectInstanceClassName());
    stringBuffer.append(TEXT_107);
    }
    stringBuffer.append(TEXT_108);
    stringBuffer.append(genDataType.getName());
    stringBuffer.append(TEXT_109);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.EDataType"));
    stringBuffer.append(TEXT_110);
    if (genDataType instanceof GenEnum) {
    stringBuffer.append(TEXT_111);
    } else if (genDataType.getBaseType() != null) { GenDataType genBaseType = genDataType.getBaseType(); 
    if (genBaseType.getGenPackage() == genPackage) {
    stringBuffer.append(TEXT_112);
    stringBuffer.append(genBaseType.getName());
    stringBuffer.append(TEXT_113);
    stringBuffer.append(genBaseType.getGenPackage().getImportedPackageInterfaceName());
    stringBuffer.append(TEXT_114);
    stringBuffer.append(genBaseType.getClassifierAccessorName());
    stringBuffer.append(TEXT_115);
    } else {
    stringBuffer.append(TEXT_116);
    stringBuffer.append(genBaseType.getGenPackage().getImportedFactoryInterfaceName());
    stringBuffer.append(TEXT_117);
    stringBuffer.append(genBaseType.getGenPackage().getImportedPackageInterfaceName());
    stringBuffer.append(TEXT_118);
    stringBuffer.append(genBaseType.getClassifierAccessorName());
    stringBuffer.append(TEXT_119);
    }
    } else if (genDataType.getItemType() != null) { GenDataType genItemType = genDataType.getItemType(); 
    stringBuffer.append(TEXT_120);
    stringBuffer.append(genModel.getImportedName("java.util.List"));
    stringBuffer.append(TEXT_121);
    stringBuffer.append(genModel.getImportedName("java.util.List"));
    stringBuffer.append(TEXT_122);
    stringBuffer.append(genModel.getImportedName("java.lang.StringBuffer"));
    stringBuffer.append(TEXT_123);
    stringBuffer.append(genModel.getImportedName("java.lang.StringBuffer"));
    stringBuffer.append(TEXT_124);
    stringBuffer.append(genModel.getImportedName("java.util.Iterator"));
    stringBuffer.append(TEXT_125);
    if (genItemType.getGenPackage() == genPackage) {
    stringBuffer.append(TEXT_126);
    stringBuffer.append(genItemType.getName());
    stringBuffer.append(TEXT_127);
    stringBuffer.append(genItemType.getGenPackage().getImportedPackageInterfaceName());
    stringBuffer.append(TEXT_128);
    stringBuffer.append(genItemType.getClassifierAccessorName());
    stringBuffer.append(TEXT_129);
    } else {
    stringBuffer.append(TEXT_130);
    stringBuffer.append(genItemType.getGenPackage().getImportedFactoryInterfaceName());
    stringBuffer.append(TEXT_131);
    stringBuffer.append(genItemType.getGenPackage().getImportedPackageInterfaceName());
    stringBuffer.append(TEXT_132);
    stringBuffer.append(genItemType.getClassifierAccessorName());
    stringBuffer.append(TEXT_133);
    }
    stringBuffer.append(TEXT_134);
    } else if (!genDataType.getMemberTypes().isEmpty()) {
    stringBuffer.append(TEXT_135);
    for (Iterator j = genDataType.getMemberTypes().iterator(); j.hasNext(); ) { GenDataType genMemberType = (GenDataType)j.next();
    stringBuffer.append(TEXT_136);
    stringBuffer.append(genMemberType.getGenPackage().getImportedPackageInterfaceName());
    stringBuffer.append(TEXT_137);
    stringBuffer.append(genMemberType.getClassifierAccessorName());
    stringBuffer.append(TEXT_138);
    if (genMemberType.getGenPackage() == genPackage) {
    stringBuffer.append(TEXT_139);
    stringBuffer.append(genMemberType.getName());
    stringBuffer.append(TEXT_140);
    stringBuffer.append(genMemberType.getGenPackage().getImportedPackageInterfaceName());
    stringBuffer.append(TEXT_141);
    stringBuffer.append(genMemberType.getClassifierAccessorName());
    stringBuffer.append(TEXT_142);
    } else {
    stringBuffer.append(TEXT_143);
    stringBuffer.append(genMemberType.getGenPackage().getImportedFactoryInterfaceName());
    stringBuffer.append(TEXT_144);
    stringBuffer.append(genMemberType.getGenPackage().getImportedPackageInterfaceName());
    stringBuffer.append(TEXT_145);
    stringBuffer.append(genMemberType.getClassifierAccessorName());
    stringBuffer.append(TEXT_146);
    }
    stringBuffer.append(TEXT_147);
    }
    stringBuffer.append(TEXT_148);
    } else if (genDataType.isArrayType()) {
    stringBuffer.append(TEXT_149);
    stringBuffer.append(genModel.getImportedName("java.lang.UnsupportedOperationException"));
    stringBuffer.append(TEXT_150);
    } else {
    stringBuffer.append(TEXT_151);
    }
    stringBuffer.append(TEXT_152);
    }
    }
    stringBuffer.append(TEXT_153);
    stringBuffer.append(genPackage.getImportedPackageInterfaceName());
    stringBuffer.append(TEXT_154);
    stringBuffer.append(genPackage.getPackageInterfaceName());
    stringBuffer.append(TEXT_155);
    stringBuffer.append(genPackage.getImportedPackageInterfaceName());
    stringBuffer.append(TEXT_156);
    stringBuffer.append(genPackage.getImportedPackageInterfaceName());
    stringBuffer.append(TEXT_157);
    stringBuffer.append(genPackage.getImportedPackageInterfaceName());
    stringBuffer.append(TEXT_158);
    stringBuffer.append(genPackage.getFactoryClassName());
    genModel.emitSortedImports();
    stringBuffer.append(TEXT_159);
    return stringBuffer.toString();
  }
}
