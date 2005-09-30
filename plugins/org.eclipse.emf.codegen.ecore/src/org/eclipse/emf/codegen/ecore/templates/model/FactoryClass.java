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
  protected final String TEXT_14 = NL + "\t/**" + NL + "\t * Creates an instance of the factory." + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */" + NL + "\tpublic ";
  protected final String TEXT_15 = "()" + NL + "\t{" + NL + "\t\tsuper();" + NL + "\t}" + NL + "" + NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */" + NL + "\tpublic EObject create(EClass eClass)" + NL + "\t{" + NL + "\t\tswitch (eClass.getClassifierID())" + NL + "\t\t{";
  protected final String TEXT_16 = NL + "\t\t\tcase ";
  protected final String TEXT_17 = ".";
  protected final String TEXT_18 = ": return ";
  protected final String TEXT_19 = "create";
  protected final String TEXT_20 = "();";
  protected final String TEXT_21 = NL + "\t\t\tdefault:" + NL + "\t\t\t\tthrow new IllegalArgumentException(\"The class '\" + eClass.getName() + \"' is not a valid classifier\");";
  protected final String TEXT_22 = NL + "\t\t}" + NL + "\t}";
  protected final String TEXT_23 = NL + NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */" + NL + "\tpublic Object createFromString(";
  protected final String TEXT_24 = " eDataType, String initialValue)" + NL + "\t{" + NL + "\t\tswitch (eDataType.getClassifierID())" + NL + "\t\t{";
  protected final String TEXT_25 = NL + "\t\t\tcase ";
  protected final String TEXT_26 = ".";
  protected final String TEXT_27 = ":" + NL + "\t\t\t\treturn create";
  protected final String TEXT_28 = "FromString(eDataType, initialValue);";
  protected final String TEXT_29 = NL + "\t\t\tdefault:" + NL + "\t\t\t\tthrow new IllegalArgumentException(\"The datatype '\" + eDataType.getName() + \"' is not a valid classifier\");";
  protected final String TEXT_30 = NL + "\t\t}" + NL + "\t}" + NL + "" + NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */" + NL + "\tpublic String convertToString(";
  protected final String TEXT_31 = " eDataType, Object instanceValue)" + NL + "\t{" + NL + "\t\tswitch (eDataType.getClassifierID())" + NL + "\t\t{";
  protected final String TEXT_32 = NL + "\t\t\tcase ";
  protected final String TEXT_33 = ".";
  protected final String TEXT_34 = ":" + NL + "\t\t\t\treturn convert";
  protected final String TEXT_35 = "ToString(eDataType, instanceValue);";
  protected final String TEXT_36 = NL + "\t\t\tdefault:" + NL + "\t\t\t\tthrow new IllegalArgumentException(\"The datatype '\" + eDataType.getName() + \"' is not a valid classifier\");";
  protected final String TEXT_37 = NL + "\t\t}" + NL + "\t}";
  protected final String TEXT_38 = NL + NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */" + NL + "\tpublic ";
  protected final String TEXT_39 = " create";
  protected final String TEXT_40 = "()" + NL + "\t{" + NL + "\t\t";
  protected final String TEXT_41 = " ";
  protected final String TEXT_42 = " = new ";
  protected final String TEXT_43 = "();" + NL + "\t\treturn ";
  protected final String TEXT_44 = ";" + NL + "\t}";
  protected final String TEXT_45 = NL + NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */" + NL + "\tpublic ";
  protected final String TEXT_46 = " create";
  protected final String TEXT_47 = "FromString(";
  protected final String TEXT_48 = " eDataType, String initialValue)" + NL + "\t{";
  protected final String TEXT_49 = NL + "\t\t";
  protected final String TEXT_50 = " result = ";
  protected final String TEXT_51 = ".get(initialValue);" + NL + "\t\tif (result == null) throw new IllegalArgumentException(\"The value '\" + initialValue + \"' is not a valid enumerator of '\" + eDataType.getName() + \"'\");";
  protected final String TEXT_52 = NL + "\t\treturn result;";
  protected final String TEXT_53 = NL + "\t\treturn (";
  protected final String TEXT_54 = ")create";
  protected final String TEXT_55 = "FromString(";
  protected final String TEXT_56 = ".eINSTANCE.get";
  protected final String TEXT_57 = "(), initialValue);";
  protected final String TEXT_58 = NL + "\t\treturn (";
  protected final String TEXT_59 = ")";
  protected final String TEXT_60 = ".eINSTANCE.createFromString(";
  protected final String TEXT_61 = ".eINSTANCE.get";
  protected final String TEXT_62 = "(), initialValue);";
  protected final String TEXT_63 = NL + "\t\tif (initialValue == null) return null;" + NL + "\t\t";
  protected final String TEXT_64 = " result = new ";
  protected final String TEXT_65 = "();" + NL + "\t\tfor (";
  protected final String TEXT_66 = " stringTokenizer = new ";
  protected final String TEXT_67 = "(initialValue); stringTokenizer.hasMoreTokens(); )" + NL + "\t\t{" + NL + "\t\t\tString item = stringTokenizer.nextToken();";
  protected final String TEXT_68 = NL + "\t\t\tresult.add(create";
  protected final String TEXT_69 = "FromString(";
  protected final String TEXT_70 = ".eINSTANCE.get";
  protected final String TEXT_71 = "(), item));";
  protected final String TEXT_72 = NL + "\t\t\tresult.add(";
  protected final String TEXT_73 = ".eINSTANCE.createFromString(";
  protected final String TEXT_74 = ".eINSTANCE.get";
  protected final String TEXT_75 = "(), item));";
  protected final String TEXT_76 = NL + "\t\t}" + NL + "\t\treturn result;";
  protected final String TEXT_77 = NL + "\t\t";
  protected final String TEXT_78 = " result = null;" + NL + "\t\tRuntimeException exception = null;";
  protected final String TEXT_79 = NL + "\t\ttry" + NL + "\t\t{";
  protected final String TEXT_80 = NL + "\t\t\tresult = (";
  protected final String TEXT_81 = ")create";
  protected final String TEXT_82 = "FromString(";
  protected final String TEXT_83 = ".eINSTANCE.get";
  protected final String TEXT_84 = "(), initialValue);";
  protected final String TEXT_85 = NL + "\t\t\tresult = (";
  protected final String TEXT_86 = ")";
  protected final String TEXT_87 = ".eINSTANCE.createFromString(";
  protected final String TEXT_88 = ".eINSTANCE.get";
  protected final String TEXT_89 = "(), initialValue);";
  protected final String TEXT_90 = NL + "\t\t\tif (result != null && ";
  protected final String TEXT_91 = ".INSTANCE.validate(eDataType, result, null, null))" + NL + "\t\t\t{" + NL + "\t\t\t\treturn result;" + NL + "\t\t\t}" + NL + "\t\t}" + NL + "\t\tcatch (RuntimeException e)" + NL + "\t\t{" + NL + "\t\t\texception = e;" + NL + "\t\t}";
  protected final String TEXT_92 = NL + "\t\tif (result != null || exception == null) return result;" + NL + "    " + NL + "\t\tthrow exception;";
  protected final String TEXT_93 = NL + "\t\t// TODO: implement this method" + NL + "\t\t// Ensure that you remove @generated or mark it @generated NOT" + NL + "\t\tthrow new ";
  protected final String TEXT_94 = "();";
  protected final String TEXT_95 = NL + "\t\treturn (";
  protected final String TEXT_96 = ")super.createFromString(eDataType, initialValue);";
  protected final String TEXT_97 = NL + "\t}" + NL + "" + NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */" + NL + "\tpublic String convert";
  protected final String TEXT_98 = "ToString(";
  protected final String TEXT_99 = " eDataType, Object instanceValue)" + NL + "\t{";
  protected final String TEXT_100 = NL + "\t\treturn instanceValue == null ? null : instanceValue.toString();";
  protected final String TEXT_101 = NL + "\t\treturn convert";
  protected final String TEXT_102 = "ToString(";
  protected final String TEXT_103 = ".eINSTANCE.get";
  protected final String TEXT_104 = "(), instanceValue);";
  protected final String TEXT_105 = NL + "\t\treturn ";
  protected final String TEXT_106 = ".eINSTANCE.convertToString(";
  protected final String TEXT_107 = ".eINSTANCE.get";
  protected final String TEXT_108 = "(), instanceValue);";
  protected final String TEXT_109 = NL + "\t\tif (instanceValue == null) return null;" + NL + "\t\t";
  protected final String TEXT_110 = " list = (";
  protected final String TEXT_111 = ")instanceValue;" + NL + "\t\tif (list.isEmpty()) return \"\";" + NL + "\t\t";
  protected final String TEXT_112 = " result = new ";
  protected final String TEXT_113 = "();" + NL + "\t\tfor (";
  protected final String TEXT_114 = " i = list.iterator(); i.hasNext(); )" + NL + "\t\t{";
  protected final String TEXT_115 = NL + "\t\t\tresult.append(convert";
  protected final String TEXT_116 = "ToString(";
  protected final String TEXT_117 = ".eINSTANCE.get";
  protected final String TEXT_118 = "(), i.next()));";
  protected final String TEXT_119 = NL + "\t\t\tresult.append(";
  protected final String TEXT_120 = ".eINSTANCE.convertToString(";
  protected final String TEXT_121 = ".eINSTANCE.get";
  protected final String TEXT_122 = "(), i.next()));";
  protected final String TEXT_123 = NL + "\t\t\tresult.append(' ');" + NL + "\t\t}" + NL + "\t\treturn result.substring(0, result.length() - 1);";
  protected final String TEXT_124 = NL + "\t\tif (";
  protected final String TEXT_125 = ".eINSTANCE.get";
  protected final String TEXT_126 = "().isInstance(instanceValue))" + NL + "\t\t{" + NL + "\t\t\ttry" + NL + "\t\t\t{";
  protected final String TEXT_127 = NL + "\t\t\t\tString value = convert";
  protected final String TEXT_128 = "ToString(";
  protected final String TEXT_129 = ".eINSTANCE.get";
  protected final String TEXT_130 = "(), instanceValue);";
  protected final String TEXT_131 = NL + "\t\t\t\tString value = ";
  protected final String TEXT_132 = ".eINSTANCE.convertToString(";
  protected final String TEXT_133 = ".eINSTANCE.get";
  protected final String TEXT_134 = "(), instanceValue);";
  protected final String TEXT_135 = NL + "\t\t\t\tif (value != null) return value;" + NL + "\t\t\t}" + NL + "\t\t\tcatch (Exception e)" + NL + "\t\t\t{" + NL + "\t\t\t}" + NL + "\t\t}";
  protected final String TEXT_136 = NL + "\t\tthrow new IllegalArgumentException(\"Invalid value: '\"+instanceValue+\"' for datatype :\"+eDataType.getName());";
  protected final String TEXT_137 = NL + "\t\t// TODO: implement this method" + NL + "\t\t// Ensure that you remove @generated or mark it @generated NOT" + NL + "\t\tthrow new ";
  protected final String TEXT_138 = "();";
  protected final String TEXT_139 = NL + "\t\treturn super.convertToString(eDataType, instanceValue);";
  protected final String TEXT_140 = NL + "\t}";
  protected final String TEXT_141 = NL + NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */" + NL + "\tpublic ";
  protected final String TEXT_142 = " get";
  protected final String TEXT_143 = "()" + NL + "\t{" + NL + "\t\treturn (";
  protected final String TEXT_144 = ")getEPackage();" + NL + "\t}" + NL + "" + NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @deprecated" + NL + "\t * @generated" + NL + "\t */" + NL + "\tpublic static ";
  protected final String TEXT_145 = " getPackage()" + NL + "\t{" + NL + "\t\treturn ";
  protected final String TEXT_146 = ".eINSTANCE;" + NL + "\t}" + NL + "" + NL + "} //";
  protected final String TEXT_147 = NL;

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
    stringBuffer.append(genPackage.getFactoryClassName());
    stringBuffer.append(TEXT_15);
    for (Iterator i=genPackage.getGenClasses().iterator(); i.hasNext();) { GenClass genClass = (GenClass)i.next();
    if (!genClass.isAbstract()) {
    stringBuffer.append(TEXT_16);
    stringBuffer.append(genPackage.getImportedPackageInterfaceName());
    stringBuffer.append(TEXT_17);
    stringBuffer.append(genClass.getClassifierID());
    stringBuffer.append(TEXT_18);
    stringBuffer.append(!genClass.isEObjectExtension() ? "(EObject)" : "" );
    stringBuffer.append(TEXT_19);
    stringBuffer.append(genClass.getName());
    stringBuffer.append(TEXT_20);
    }
    }
    stringBuffer.append(TEXT_21);
    stringBuffer.append(genModel.getNonNLS());
    stringBuffer.append(genModel.getNonNLS(2));
    stringBuffer.append(TEXT_22);
    if (!genPackage.getAllGenDataTypes().isEmpty()) {
    stringBuffer.append(TEXT_23);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.EDataType"));
    stringBuffer.append(TEXT_24);
    for (Iterator i=genPackage.getAllGenDataTypes().iterator(); i.hasNext();) { GenDataType genDataType = (GenDataType)i.next();
    if (genDataType.isSerializable()) {
    stringBuffer.append(TEXT_25);
    stringBuffer.append(genPackage.getImportedPackageInterfaceName());
    stringBuffer.append(TEXT_26);
    stringBuffer.append(genDataType.getClassifierID());
    stringBuffer.append(TEXT_27);
    stringBuffer.append(genDataType.getName());
    stringBuffer.append(TEXT_28);
    }
    }
    stringBuffer.append(TEXT_29);
    stringBuffer.append(genModel.getNonNLS());
    stringBuffer.append(genModel.getNonNLS(2));
    stringBuffer.append(TEXT_30);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.EDataType"));
    stringBuffer.append(TEXT_31);
    for (Iterator i=genPackage.getAllGenDataTypes().iterator(); i.hasNext();) { GenDataType genDataType = (GenDataType)i.next();
    if (genDataType.isSerializable()) {
    stringBuffer.append(TEXT_32);
    stringBuffer.append(genPackage.getImportedPackageInterfaceName());
    stringBuffer.append(TEXT_33);
    stringBuffer.append(genDataType.getClassifierID());
    stringBuffer.append(TEXT_34);
    stringBuffer.append(genDataType.getName());
    stringBuffer.append(TEXT_35);
    }
    }
    stringBuffer.append(TEXT_36);
    stringBuffer.append(genModel.getNonNLS());
    stringBuffer.append(genModel.getNonNLS(2));
    stringBuffer.append(TEXT_37);
    }
    for (Iterator i=genPackage.getGenClasses().iterator(); i.hasNext();) { GenClass genClass = (GenClass)i.next();
    if (!genClass.isAbstract()) {
    stringBuffer.append(TEXT_38);
    stringBuffer.append(genClass.getImportedInterfaceName());
    stringBuffer.append(TEXT_39);
    stringBuffer.append(genClass.getName());
    stringBuffer.append(TEXT_40);
    stringBuffer.append(genClass.getImportedClassName());
    stringBuffer.append(TEXT_41);
    stringBuffer.append(genClass.getSafeUncapName());
    stringBuffer.append(TEXT_42);
    stringBuffer.append(genClass.getImportedClassName());
    stringBuffer.append(TEXT_43);
    stringBuffer.append(genClass.getSafeUncapName());
    stringBuffer.append(TEXT_44);
    }
    }
    for (Iterator i=genPackage.getAllGenDataTypes().iterator(); i.hasNext();) { GenDataType genDataType = (GenDataType)i.next();
    if (genDataType.isSerializable()) {
    stringBuffer.append(TEXT_45);
    stringBuffer.append(genDataType.getObjectInstanceClassName());
    stringBuffer.append(TEXT_46);
    stringBuffer.append(genDataType.getName());
    stringBuffer.append(TEXT_47);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.EDataType"));
    stringBuffer.append(TEXT_48);
    if (genDataType instanceof GenEnum) {
    stringBuffer.append(TEXT_49);
    stringBuffer.append(((GenEnum)genDataType).getImportedName());
    stringBuffer.append(TEXT_50);
    stringBuffer.append(((GenEnum)genDataType).getImportedName());
    stringBuffer.append(TEXT_51);
    stringBuffer.append(genModel.getNonNLS());
    stringBuffer.append(genModel.getNonNLS(2));
    stringBuffer.append(genModel.getNonNLS(3));
    stringBuffer.append(TEXT_52);
    } else if (genDataType.getBaseType() != null) { GenDataType genBaseType = genDataType.getBaseType(); 
    if (genBaseType.getGenPackage() == genPackage) {
    stringBuffer.append(TEXT_53);
    stringBuffer.append(genDataType.getObjectInstanceClassName());
    stringBuffer.append(TEXT_54);
    stringBuffer.append(genBaseType.getName());
    stringBuffer.append(TEXT_55);
    stringBuffer.append(genBaseType.getGenPackage().getImportedPackageInterfaceName());
    stringBuffer.append(TEXT_56);
    stringBuffer.append(genBaseType.getClassifierAccessorName());
    stringBuffer.append(TEXT_57);
    } else {
    stringBuffer.append(TEXT_58);
    stringBuffer.append(genDataType.getObjectInstanceClassName());
    stringBuffer.append(TEXT_59);
    stringBuffer.append(genBaseType.getGenPackage().getImportedFactoryInterfaceName());
    stringBuffer.append(TEXT_60);
    stringBuffer.append(genBaseType.getGenPackage().getImportedPackageInterfaceName());
    stringBuffer.append(TEXT_61);
    stringBuffer.append(genBaseType.getClassifierAccessorName());
    stringBuffer.append(TEXT_62);
    }
    } else if (genDataType.getItemType() != null) { GenDataType genItemType = genDataType.getItemType(); 
    stringBuffer.append(TEXT_63);
    stringBuffer.append(genModel.getImportedName("java.util.List"));
    stringBuffer.append(TEXT_64);
    stringBuffer.append(genModel.getImportedName("java.util.ArrayList"));
    stringBuffer.append(TEXT_65);
    stringBuffer.append(genModel.getImportedName("java.util.StringTokenizer"));
    stringBuffer.append(TEXT_66);
    stringBuffer.append(genModel.getImportedName("java.util.StringTokenizer"));
    stringBuffer.append(TEXT_67);
    if (genItemType.getGenPackage() == genPackage) {
    stringBuffer.append(TEXT_68);
    stringBuffer.append(genItemType.getName());
    stringBuffer.append(TEXT_69);
    stringBuffer.append(genItemType.getGenPackage().getImportedPackageInterfaceName());
    stringBuffer.append(TEXT_70);
    stringBuffer.append(genItemType.getClassifierAccessorName());
    stringBuffer.append(TEXT_71);
    } else {
    stringBuffer.append(TEXT_72);
    stringBuffer.append(genItemType.getGenPackage().getImportedFactoryInterfaceName());
    stringBuffer.append(TEXT_73);
    stringBuffer.append(genItemType.getGenPackage().getImportedPackageInterfaceName());
    stringBuffer.append(TEXT_74);
    stringBuffer.append(genItemType.getClassifierAccessorName());
    stringBuffer.append(TEXT_75);
    }
    stringBuffer.append(TEXT_76);
    } else if (!genDataType.getMemberTypes().isEmpty()) {
    stringBuffer.append(TEXT_77);
    stringBuffer.append(genDataType.getObjectInstanceClassName());
    stringBuffer.append(TEXT_78);
    for (Iterator j = genDataType.getMemberTypes().iterator(); j.hasNext(); ) { GenDataType genMemberType = (GenDataType)j.next();
    stringBuffer.append(TEXT_79);
    if (genMemberType.getGenPackage() == genPackage) {
    stringBuffer.append(TEXT_80);
    stringBuffer.append(genDataType.getObjectInstanceClassName());
    stringBuffer.append(TEXT_81);
    stringBuffer.append(genMemberType.getName());
    stringBuffer.append(TEXT_82);
    stringBuffer.append(genMemberType.getGenPackage().getImportedPackageInterfaceName());
    stringBuffer.append(TEXT_83);
    stringBuffer.append(genMemberType.getClassifierAccessorName());
    stringBuffer.append(TEXT_84);
    } else {
    stringBuffer.append(TEXT_85);
    stringBuffer.append(genDataType.getObjectInstanceClassName());
    stringBuffer.append(TEXT_86);
    stringBuffer.append(genMemberType.getGenPackage().getImportedFactoryInterfaceName());
    stringBuffer.append(TEXT_87);
    stringBuffer.append(genMemberType.getGenPackage().getImportedPackageInterfaceName());
    stringBuffer.append(TEXT_88);
    stringBuffer.append(genMemberType.getClassifierAccessorName());
    stringBuffer.append(TEXT_89);
    }
    stringBuffer.append(TEXT_90);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.Diagnostician"));
    stringBuffer.append(TEXT_91);
    }
    stringBuffer.append(TEXT_92);
    } else if (genDataType.isArrayType()) {
    stringBuffer.append(TEXT_93);
    stringBuffer.append(genModel.getImportedName("java.lang.UnsupportedOperationException"));
    stringBuffer.append(TEXT_94);
    } else {
    stringBuffer.append(TEXT_95);
    stringBuffer.append(genDataType.getObjectInstanceClassName());
    stringBuffer.append(TEXT_96);
    }
    stringBuffer.append(TEXT_97);
    stringBuffer.append(genDataType.getName());
    stringBuffer.append(TEXT_98);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.EDataType"));
    stringBuffer.append(TEXT_99);
    if (genDataType instanceof GenEnum) {
    stringBuffer.append(TEXT_100);
    } else if (genDataType.getBaseType() != null) { GenDataType genBaseType = genDataType.getBaseType(); 
    if (genBaseType.getGenPackage() == genPackage) {
    stringBuffer.append(TEXT_101);
    stringBuffer.append(genBaseType.getName());
    stringBuffer.append(TEXT_102);
    stringBuffer.append(genBaseType.getGenPackage().getImportedPackageInterfaceName());
    stringBuffer.append(TEXT_103);
    stringBuffer.append(genBaseType.getClassifierAccessorName());
    stringBuffer.append(TEXT_104);
    } else {
    stringBuffer.append(TEXT_105);
    stringBuffer.append(genBaseType.getGenPackage().getImportedFactoryInterfaceName());
    stringBuffer.append(TEXT_106);
    stringBuffer.append(genBaseType.getGenPackage().getImportedPackageInterfaceName());
    stringBuffer.append(TEXT_107);
    stringBuffer.append(genBaseType.getClassifierAccessorName());
    stringBuffer.append(TEXT_108);
    }
    } else if (genDataType.getItemType() != null) { GenDataType genItemType = genDataType.getItemType(); 
    stringBuffer.append(TEXT_109);
    stringBuffer.append(genModel.getImportedName("java.util.List"));
    stringBuffer.append(TEXT_110);
    stringBuffer.append(genModel.getImportedName("java.util.List"));
    stringBuffer.append(TEXT_111);
    stringBuffer.append(genModel.getImportedName("java.lang.StringBuffer"));
    stringBuffer.append(TEXT_112);
    stringBuffer.append(genModel.getImportedName("java.lang.StringBuffer"));
    stringBuffer.append(TEXT_113);
    stringBuffer.append(genModel.getImportedName("java.util.Iterator"));
    stringBuffer.append(TEXT_114);
    if (genItemType.getGenPackage() == genPackage) {
    stringBuffer.append(TEXT_115);
    stringBuffer.append(genItemType.getName());
    stringBuffer.append(TEXT_116);
    stringBuffer.append(genItemType.getGenPackage().getImportedPackageInterfaceName());
    stringBuffer.append(TEXT_117);
    stringBuffer.append(genItemType.getClassifierAccessorName());
    stringBuffer.append(TEXT_118);
    } else {
    stringBuffer.append(TEXT_119);
    stringBuffer.append(genItemType.getGenPackage().getImportedFactoryInterfaceName());
    stringBuffer.append(TEXT_120);
    stringBuffer.append(genItemType.getGenPackage().getImportedPackageInterfaceName());
    stringBuffer.append(TEXT_121);
    stringBuffer.append(genItemType.getClassifierAccessorName());
    stringBuffer.append(TEXT_122);
    }
    stringBuffer.append(TEXT_123);
    } else if (!genDataType.getMemberTypes().isEmpty()) {
    for (Iterator j = genDataType.getMemberTypes().iterator(); j.hasNext(); ) { GenDataType genMemberType = (GenDataType)j.next();
    stringBuffer.append(TEXT_124);
    stringBuffer.append(genMemberType.getGenPackage().getImportedPackageInterfaceName());
    stringBuffer.append(TEXT_125);
    stringBuffer.append(genMemberType.getClassifierAccessorName());
    stringBuffer.append(TEXT_126);
    if (genMemberType.getGenPackage() == genPackage) {
    stringBuffer.append(TEXT_127);
    stringBuffer.append(genMemberType.getName());
    stringBuffer.append(TEXT_128);
    stringBuffer.append(genMemberType.getGenPackage().getImportedPackageInterfaceName());
    stringBuffer.append(TEXT_129);
    stringBuffer.append(genMemberType.getClassifierAccessorName());
    stringBuffer.append(TEXT_130);
    } else {
    stringBuffer.append(TEXT_131);
    stringBuffer.append(genMemberType.getGenPackage().getImportedFactoryInterfaceName());
    stringBuffer.append(TEXT_132);
    stringBuffer.append(genMemberType.getGenPackage().getImportedPackageInterfaceName());
    stringBuffer.append(TEXT_133);
    stringBuffer.append(genMemberType.getClassifierAccessorName());
    stringBuffer.append(TEXT_134);
    }
    stringBuffer.append(TEXT_135);
    }
    stringBuffer.append(TEXT_136);
    } else if (genDataType.isArrayType()) {
    stringBuffer.append(TEXT_137);
    stringBuffer.append(genModel.getImportedName("java.lang.UnsupportedOperationException"));
    stringBuffer.append(TEXT_138);
    } else {
    stringBuffer.append(TEXT_139);
    }
    stringBuffer.append(TEXT_140);
    }
    }
    stringBuffer.append(TEXT_141);
    stringBuffer.append(genPackage.getImportedPackageInterfaceName());
    stringBuffer.append(TEXT_142);
    stringBuffer.append(genPackage.getPackageInterfaceName());
    stringBuffer.append(TEXT_143);
    stringBuffer.append(genPackage.getImportedPackageInterfaceName());
    stringBuffer.append(TEXT_144);
    stringBuffer.append(genPackage.getImportedPackageInterfaceName());
    stringBuffer.append(TEXT_145);
    stringBuffer.append(genPackage.getImportedPackageInterfaceName());
    stringBuffer.append(TEXT_146);
    stringBuffer.append(genPackage.getFactoryClassName());
    genModel.emitSortedImports();
    stringBuffer.append(TEXT_147);
    return stringBuffer.toString();
  }
}
