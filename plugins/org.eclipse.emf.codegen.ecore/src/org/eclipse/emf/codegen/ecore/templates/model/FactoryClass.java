package org.eclipse.emf.codegen.ecore.templates.model;

import java.util.*;
import org.eclipse.emf.codegen.ecore.genmodel.*;

public class FactoryClass
{
  protected final String NL = System.getProperties().getProperty("line.separator");
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
  protected final String TEXT_14 = NL + "\t/**" + NL + "\t * Creates and instance of the factory." + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */" + NL + "\tpublic ";
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
  protected final String TEXT_27 = ":";
  protected final String TEXT_28 = NL + "\t\t\t\t";
  protected final String TEXT_29 = " result = ";
  protected final String TEXT_30 = ".get(initialValue);" + NL + "\t\t\t\tif (result == null) throw new IllegalArgumentException(\"The value '\" + initialValue + \"' is not a valid enumerator of '\" + eDataType.getName() + \"'\");";
  protected final String TEXT_31 = NL + "\t\t\t\treturn result;";
  protected final String TEXT_32 = NL + "\t\t\t\treturn create";
  protected final String TEXT_33 = "FromString(eDataType, initialValue);";
  protected final String TEXT_34 = NL + "\t\t\tdefault:" + NL + "\t\t\t\tthrow new IllegalArgumentException(\"The datatype '\" + eDataType.getName() + \"' is not a valid classifier\");";
  protected final String TEXT_35 = NL + "\t\t}" + NL + "\t}" + NL + "" + NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */" + NL + "\tpublic String convertToString(";
  protected final String TEXT_36 = " eDataType, Object instanceValue)" + NL + "\t{" + NL + "\t\tswitch (eDataType.getClassifierID())" + NL + "\t\t{";
  protected final String TEXT_37 = NL + "\t\t\tcase ";
  protected final String TEXT_38 = ".";
  protected final String TEXT_39 = ":";
  protected final String TEXT_40 = NL + "\t\t\t\treturn instanceValue == null ? null : instanceValue.toString();";
  protected final String TEXT_41 = NL + "\t\t\t\treturn convert";
  protected final String TEXT_42 = "ToString(eDataType, instanceValue);";
  protected final String TEXT_43 = NL + "\t\t\tdefault:" + NL + "\t\t\t\tthrow new IllegalArgumentException(\"The datatype '\" + eDataType.getName() + \"' is not a valid classifier\");";
  protected final String TEXT_44 = NL + "\t\t}" + NL + "\t}";
  protected final String TEXT_45 = NL + NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */" + NL + "\tpublic ";
  protected final String TEXT_46 = " create";
  protected final String TEXT_47 = "()" + NL + "\t{" + NL + "\t\t";
  protected final String TEXT_48 = " ";
  protected final String TEXT_49 = " = new ";
  protected final String TEXT_50 = "();" + NL + "\t\treturn ";
  protected final String TEXT_51 = ";" + NL + "\t}";
  protected final String TEXT_52 = NL + NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */" + NL + "\tpublic ";
  protected final String TEXT_53 = " create";
  protected final String TEXT_54 = "FromString(";
  protected final String TEXT_55 = " eDataType, String initialValue)" + NL + "\t{";
  protected final String TEXT_56 = NL + "\t\t// TODO: implement this method" + NL + "\t\t// Ensure that you remove @generated or mark it @generated NOT" + NL + "\t\tthrow new ";
  protected final String TEXT_57 = "();";
  protected final String TEXT_58 = NL + "\t\treturn (";
  protected final String TEXT_59 = ")";
  protected final String TEXT_60 = ".eINSTANCE.createFromString(";
  protected final String TEXT_61 = ".eINSTANCE.get";
  protected final String TEXT_62 = "(), initialValue);";
  protected final String TEXT_63 = NL + "\t\tif (initialValue == null) return null;" + NL + "\t\t";
  protected final String TEXT_64 = " result = new ";
  protected final String TEXT_65 = "();" + NL + "\t\tfor (";
  protected final String TEXT_66 = " stringTokenizer = new ";
  protected final String TEXT_67 = "(initialValue); stringTokenizer.hasMoreTokens(); )" + NL + "\t\t{" + NL + "\t\t\tString item = stringTokenizer.nextToken();" + NL + "\t\t\tresult.add(";
  protected final String TEXT_68 = ".eINSTANCE.createFromString(";
  protected final String TEXT_69 = ".eINSTANCE.get";
  protected final String TEXT_70 = "(), item));" + NL + "\t\t}" + NL + "\t\treturn result;";
  protected final String TEXT_71 = NL + "\t\ttry" + NL + "\t\t{" + NL + "\t\t\tObject result = (";
  protected final String TEXT_72 = ")";
  protected final String TEXT_73 = ".eINSTANCE.createFromString(";
  protected final String TEXT_74 = ".eINSTANCE.get";
  protected final String TEXT_75 = "(), initialValue);" + NL + "\t\t\tif (result != null)" + NL + "\t\t\t{" + NL + "\t\t\t\treturn result;" + NL + "\t\t\t}" + NL + "\t\t}" + NL + "\t\tcatch (RuntimeException exception)" + NL + "\t\t{" + NL + "\t\t}";
  protected final String TEXT_76 = NL + "\t\treturn null;";
  protected final String TEXT_77 = NL + "\t\treturn (";
  protected final String TEXT_78 = ")super.createFromString(eDataType, initialValue);";
  protected final String TEXT_79 = NL + "\t}" + NL + "" + NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */" + NL + "\tpublic String convert";
  protected final String TEXT_80 = "ToString(";
  protected final String TEXT_81 = " eDataType, Object instanceValue)" + NL + "\t{";
  protected final String TEXT_82 = NL + "\t\t// TODO: implement this method" + NL + "\t\t// Ensure that you remove @generated or mark it @generated NOT" + NL + "\t\tthrow new ";
  protected final String TEXT_83 = "();";
  protected final String TEXT_84 = NL + "\t\treturn ";
  protected final String TEXT_85 = ".eINSTANCE.convertToString(";
  protected final String TEXT_86 = ".eINSTANCE.get";
  protected final String TEXT_87 = "(), instanceValue);";
  protected final String TEXT_88 = NL + "\t\tif (instanceValue == null) return null;" + NL + "\t\t";
  protected final String TEXT_89 = " list = (";
  protected final String TEXT_90 = ")instanceValue;" + NL + "\t\tif (list.isEmpty()) return \"\";" + NL + "\t\t";
  protected final String TEXT_91 = " result = new ";
  protected final String TEXT_92 = "();" + NL + "\t\tfor (";
  protected final String TEXT_93 = " i = list.iterator(); i.hasNext(); )" + NL + "\t\t{" + NL + "\t\t\tresult.append(";
  protected final String TEXT_94 = ".eINSTANCE.convertToString(";
  protected final String TEXT_95 = ".eINSTANCE.get";
  protected final String TEXT_96 = "(), i.next()));" + NL + "\t\t\tresult.append(' ');" + NL + "\t\t}" + NL + "\t\treturn result.substring(0, result.length() - 1);";
  protected final String TEXT_97 = NL + "\t\tif (";
  protected final String TEXT_98 = ".eINSTANCE.get";
  protected final String TEXT_99 = "().isInstance(instanceValue))" + NL + "\t\t{" + NL + "\t\t\treturn ";
  protected final String TEXT_100 = ".eINSTANCE.convertToString(";
  protected final String TEXT_101 = ".eINSTANCE.get";
  protected final String TEXT_102 = "(), instanceValue);" + NL + "\t\t}";
  protected final String TEXT_103 = NL + "\t\treturn null;";
  protected final String TEXT_104 = NL + "\t\treturn super.convertToString(eDataType, instanceValue);";
  protected final String TEXT_105 = NL + "\t}";
  protected final String TEXT_106 = NL + NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */" + NL + "\tpublic ";
  protected final String TEXT_107 = " get";
  protected final String TEXT_108 = "()" + NL + "\t{" + NL + "\t\treturn (";
  protected final String TEXT_109 = ")getEPackage();" + NL + "\t}" + NL + "" + NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @deprecated" + NL + "\t * @generated" + NL + "\t */" + NL + "\tpublic static ";
  protected final String TEXT_110 = " getPackage()" + NL + "\t{" + NL + "\t\treturn ";
  protected final String TEXT_111 = ".eINSTANCE;" + NL + "\t}" + NL + "} //";
  protected final String TEXT_112 = NL;

  public String generate(Object argument)
  {
    StringBuffer stringBuffer = new StringBuffer();
    
/**
 * <copyright>
 *
 * Copyright (c) 2002-2004 IBM Corporation and others.
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
    if (!genPackage.hasJavaLangConflict() && !genPackage.hasInterfaceImplConflict()) genModel.addImport(genPackage.getInterfacePackageName() + ".*");
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
    if (genDataType instanceof GenEnum) {
    stringBuffer.append(TEXT_28);
    stringBuffer.append(((GenEnum)genDataType).getImportedName());
    stringBuffer.append(TEXT_29);
    stringBuffer.append(((GenEnum)genDataType).getImportedName());
    stringBuffer.append(TEXT_30);
    stringBuffer.append(genModel.getNonNLS());
    stringBuffer.append(genModel.getNonNLS(2));
    stringBuffer.append(genModel.getNonNLS(3));
    stringBuffer.append(TEXT_31);
    } else {
    stringBuffer.append(TEXT_32);
    stringBuffer.append(genDataType.getName());
    stringBuffer.append(TEXT_33);
    }
    }
    }
    stringBuffer.append(TEXT_34);
    stringBuffer.append(genModel.getNonNLS());
    stringBuffer.append(genModel.getNonNLS(2));
    stringBuffer.append(TEXT_35);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.EDataType"));
    stringBuffer.append(TEXT_36);
    for (Iterator i=genPackage.getAllGenDataTypes().iterator(); i.hasNext();) { GenDataType genDataType = (GenDataType)i.next();
    if (genDataType.isSerializable()) {
    stringBuffer.append(TEXT_37);
    stringBuffer.append(genPackage.getImportedPackageInterfaceName());
    stringBuffer.append(TEXT_38);
    stringBuffer.append(genDataType.getClassifierID());
    stringBuffer.append(TEXT_39);
    if (genDataType instanceof GenEnum) {
    stringBuffer.append(TEXT_40);
    } else {
    stringBuffer.append(TEXT_41);
    stringBuffer.append(genDataType.getName());
    stringBuffer.append(TEXT_42);
    }
    }
    }
    stringBuffer.append(TEXT_43);
    stringBuffer.append(genModel.getNonNLS());
    stringBuffer.append(genModel.getNonNLS(2));
    stringBuffer.append(TEXT_44);
    }
    for (Iterator i=genPackage.getGenClasses().iterator(); i.hasNext();) { GenClass genClass = (GenClass)i.next();
    if (!genClass.isAbstract()) {
    stringBuffer.append(TEXT_45);
    stringBuffer.append(genClass.getImportedInterfaceName());
    stringBuffer.append(TEXT_46);
    stringBuffer.append(genClass.getName());
    stringBuffer.append(TEXT_47);
    stringBuffer.append(genClass.getImportedClassName());
    stringBuffer.append(TEXT_48);
    stringBuffer.append(genClass.getSafeUncapName());
    stringBuffer.append(TEXT_49);
    stringBuffer.append(genClass.getImportedClassName());
    stringBuffer.append(TEXT_50);
    stringBuffer.append(genClass.getSafeUncapName());
    stringBuffer.append(TEXT_51);
    }
    }
    for (Iterator i=genPackage.getAllGenDataTypes().iterator(); i.hasNext();) { GenDataType genDataType = (GenDataType)i.next();
    if (!(genDataType instanceof GenEnum) && genDataType.isSerializable()) {
    stringBuffer.append(TEXT_52);
    stringBuffer.append(genDataType.getObjectInstanceClassName());
    stringBuffer.append(TEXT_53);
    stringBuffer.append(genDataType.getName());
    stringBuffer.append(TEXT_54);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.EDataType"));
    stringBuffer.append(TEXT_55);
    if (genDataType.isArrayType()) {
    stringBuffer.append(TEXT_56);
    stringBuffer.append(genModel.getImportedName("java.lang.UnsupportedOperationException"));
    stringBuffer.append(TEXT_57);
    } else if (genDataType.getBaseType() != null) { GenDataType genBaseType = genDataType.getBaseType(); 
    stringBuffer.append(TEXT_58);
    stringBuffer.append(genDataType.getObjectInstanceClassName());
    stringBuffer.append(TEXT_59);
    stringBuffer.append(genBaseType.getGenPackage().getImportedFactoryInterfaceName());
    stringBuffer.append(TEXT_60);
    stringBuffer.append(genBaseType.getGenPackage().getImportedPackageInterfaceName());
    stringBuffer.append(TEXT_61);
    stringBuffer.append(genBaseType.getClassifierAccessorName());
    stringBuffer.append(TEXT_62);
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
    stringBuffer.append(genItemType.getGenPackage().getImportedFactoryInterfaceName());
    stringBuffer.append(TEXT_68);
    stringBuffer.append(genItemType.getGenPackage().getImportedPackageInterfaceName());
    stringBuffer.append(TEXT_69);
    stringBuffer.append(genItemType.getClassifierAccessorName());
    stringBuffer.append(TEXT_70);
    } else if (!genDataType.getMemberTypes().isEmpty()) {
    for (Iterator j = genDataType.getMemberTypes().iterator(); j.hasNext(); ) { GenDataType genMemberType = (GenDataType)j.next();
    stringBuffer.append(TEXT_71);
    stringBuffer.append(genDataType.getObjectInstanceClassName());
    stringBuffer.append(TEXT_72);
    stringBuffer.append(genMemberType.getGenPackage().getImportedFactoryInterfaceName());
    stringBuffer.append(TEXT_73);
    stringBuffer.append(genMemberType.getGenPackage().getImportedPackageInterfaceName());
    stringBuffer.append(TEXT_74);
    stringBuffer.append(genMemberType.getClassifierAccessorName());
    stringBuffer.append(TEXT_75);
    }
    stringBuffer.append(TEXT_76);
    } else {
    stringBuffer.append(TEXT_77);
    stringBuffer.append(genDataType.getObjectInstanceClassName());
    stringBuffer.append(TEXT_78);
    }
    stringBuffer.append(TEXT_79);
    stringBuffer.append(genDataType.getName());
    stringBuffer.append(TEXT_80);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.EDataType"));
    stringBuffer.append(TEXT_81);
    if (genDataType.isArrayType()) {
    stringBuffer.append(TEXT_82);
    stringBuffer.append(genModel.getImportedName("java.lang.UnsupportedOperationException"));
    stringBuffer.append(TEXT_83);
    } else if (genDataType.getBaseType() != null) { GenDataType genBaseType = genDataType.getBaseType(); 
    stringBuffer.append(TEXT_84);
    stringBuffer.append(genBaseType.getGenPackage().getImportedFactoryInterfaceName());
    stringBuffer.append(TEXT_85);
    stringBuffer.append(genBaseType.getGenPackage().getImportedPackageInterfaceName());
    stringBuffer.append(TEXT_86);
    stringBuffer.append(genBaseType.getClassifierAccessorName());
    stringBuffer.append(TEXT_87);
    } else if (genDataType.getItemType() != null) { GenDataType genItemType = genDataType.getItemType(); 
    stringBuffer.append(TEXT_88);
    stringBuffer.append(genModel.getImportedName("java.util.List"));
    stringBuffer.append(TEXT_89);
    stringBuffer.append(genModel.getImportedName("java.util.List"));
    stringBuffer.append(TEXT_90);
    stringBuffer.append(genModel.getImportedName("java.lang.StringBuffer"));
    stringBuffer.append(TEXT_91);
    stringBuffer.append(genModel.getImportedName("java.lang.StringBuffer"));
    stringBuffer.append(TEXT_92);
    stringBuffer.append(genModel.getImportedName("java.util.Iterator"));
    stringBuffer.append(TEXT_93);
    stringBuffer.append(genItemType.getGenPackage().getImportedFactoryInterfaceName());
    stringBuffer.append(TEXT_94);
    stringBuffer.append(genItemType.getGenPackage().getImportedPackageInterfaceName());
    stringBuffer.append(TEXT_95);
    stringBuffer.append(genItemType.getClassifierAccessorName());
    stringBuffer.append(TEXT_96);
    } else if (!genDataType.getMemberTypes().isEmpty()) {
    for (Iterator j = genDataType.getMemberTypes().iterator(); j.hasNext(); ) { GenDataType genMemberType = (GenDataType)j.next();
    stringBuffer.append(TEXT_97);
    stringBuffer.append(genMemberType.getGenPackage().getImportedPackageInterfaceName());
    stringBuffer.append(TEXT_98);
    stringBuffer.append(genMemberType.getClassifierAccessorName());
    stringBuffer.append(TEXT_99);
    stringBuffer.append(genMemberType.getGenPackage().getImportedFactoryInterfaceName());
    stringBuffer.append(TEXT_100);
    stringBuffer.append(genMemberType.getGenPackage().getImportedPackageInterfaceName());
    stringBuffer.append(TEXT_101);
    stringBuffer.append(genMemberType.getClassifierAccessorName());
    stringBuffer.append(TEXT_102);
    }
    stringBuffer.append(TEXT_103);
    } else {
    stringBuffer.append(TEXT_104);
    }
    stringBuffer.append(TEXT_105);
    }
    }
    stringBuffer.append(TEXT_106);
    stringBuffer.append(genPackage.getImportedPackageInterfaceName());
    stringBuffer.append(TEXT_107);
    stringBuffer.append(genPackage.getPackageInterfaceName());
    stringBuffer.append(TEXT_108);
    stringBuffer.append(genPackage.getImportedPackageInterfaceName());
    stringBuffer.append(TEXT_109);
    stringBuffer.append(genPackage.getImportedPackageInterfaceName());
    stringBuffer.append(TEXT_110);
    stringBuffer.append(genPackage.getImportedPackageInterfaceName());
    stringBuffer.append(TEXT_111);
    stringBuffer.append(genPackage.getFactoryClassName());
    genModel.emitSortedImports();
    stringBuffer.append(TEXT_112);
    return stringBuffer.toString();
  }
}
