package org.eclipse.emf.codegen.ecore.templates.model;

import java.util.Iterator;

import org.eclipse.emf.codegen.ecore.genmodel.GenClass;
import org.eclipse.emf.codegen.ecore.genmodel.GenDataType;
import org.eclipse.emf.codegen.ecore.genmodel.GenEnum;
import org.eclipse.emf.codegen.ecore.genmodel.GenModel;
import org.eclipse.emf.codegen.ecore.genmodel.GenPackage;

public class FactoryClass
{
  protected final String NL = System.getProperties().getProperty("line.separator");
  protected final String TEXT_1 = "";
  protected final String TEXT_2 = "/**" + NL + " * <copyright>" + NL + " * </copyright>" + NL + " *" + NL + " * ";
  protected final String TEXT_3 = "W%" + NL + " * @version ";
  protected final String TEXT_4 = "I% ";
  protected final String TEXT_5 = "H%" + NL + " */" + NL + "package ";
  protected final String TEXT_6 = ";" + NL;
  protected final String TEXT_7 = NL + NL + "/**" + NL + " * <!-- begin-user-doc -->" + NL + " * An implementation of the model <b>Factory</b>." + NL + " * <!-- end-user-doc -->" + NL + " * @generated" + NL + " */" + NL + "public class ";
  protected final String TEXT_8 = " extends ";
  protected final String TEXT_9 = " implements ";
  protected final String TEXT_10 = NL + "{";
  protected final String TEXT_11 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */" + NL + "\tpublic static final ";
  protected final String TEXT_12 = " copyright = \"";
  protected final String TEXT_13 = "\";";
  protected final String TEXT_14 = NL;
  protected final String TEXT_15 = NL + "\t/**" + NL + "\t * Creates and instance of the factory." + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */" + NL + "\tpublic ";
  protected final String TEXT_16 = "()" + NL + "\t{" + NL + "\t\tsuper();" + NL + "\t}" + NL + "" + NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */" + NL + "\tpublic EObject create(EClass eClass)" + NL + "\t{" + NL + "\t\tswitch (eClass.getClassifierID())" + NL + "\t\t{";
  protected final String TEXT_17 = NL + "\t\t\tcase ";
  protected final String TEXT_18 = ".";
  protected final String TEXT_19 = ": return ";
  protected final String TEXT_20 = "create";
  protected final String TEXT_21 = "();";
  protected final String TEXT_22 = NL + "\t\t\tdefault:" + NL + "\t\t\t\tthrow new IllegalArgumentException(\"The class '\" + eClass.getName() + \"' is not a valid classifier\");";
  protected final String TEXT_23 = NL + "\t\t}" + NL + "\t}";
  protected final String TEXT_24 = NL + NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */" + NL + "\tpublic Object createFromString(";
  protected final String TEXT_25 = " eDataType, String initialValue)" + NL + "\t{" + NL + "\t\tswitch (eDataType.getClassifierID())" + NL + "\t\t{";
  protected final String TEXT_26 = NL + "\t\t\tcase ";
  protected final String TEXT_27 = ".";
  protected final String TEXT_28 = ":";
  protected final String TEXT_29 = NL + "\t\t\t\treturn ";
  protected final String TEXT_30 = ".get(initialValue);";
  protected final String TEXT_31 = NL + "\t\t\t\treturn create";
  protected final String TEXT_32 = "FromString(eDataType, initialValue);";
  protected final String TEXT_33 = NL + "\t\t\tdefault:" + NL + "\t\t\t\tthrow new IllegalArgumentException(\"The datatype '\" + eDataType.getName() + \"' is not a valid classifier\");";
  protected final String TEXT_34 = NL + "\t\t}" + NL + "\t}" + NL + "" + NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */" + NL + "\tpublic String convertToString(";
  protected final String TEXT_35 = " eDataType, Object instanceValue)" + NL + "\t{" + NL + "\t\tswitch (eDataType.getClassifierID())" + NL + "\t\t{";
  protected final String TEXT_36 = NL + "\t\t\tcase ";
  protected final String TEXT_37 = ".";
  protected final String TEXT_38 = ":";
  protected final String TEXT_39 = NL + "\t\t\t\treturn instanceValue == null ? null : instanceValue.toString();";
  protected final String TEXT_40 = NL + "\t\t\t\treturn convert";
  protected final String TEXT_41 = "ToString(eDataType, instanceValue);";
  protected final String TEXT_42 = NL + "\t\t\tdefault:" + NL + "\t\t\t\tthrow new IllegalArgumentException(\"The datatype '\" + eDataType.getName() + \"' is not a valid classifier\");";
  protected final String TEXT_43 = NL + "\t\t}" + NL + "\t}";
  protected final String TEXT_44 = NL + NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */" + NL + "\tpublic ";
  protected final String TEXT_45 = " create";
  protected final String TEXT_46 = "()" + NL + "\t{" + NL + "\t\t";
  protected final String TEXT_47 = " ";
  protected final String TEXT_48 = " = new ";
  protected final String TEXT_49 = "();" + NL + "\t\treturn ";
  protected final String TEXT_50 = ";" + NL + "\t}";
  protected final String TEXT_51 = NL + NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */" + NL + "\tpublic ";
  protected final String TEXT_52 = " create";
  protected final String TEXT_53 = "FromString(";
  protected final String TEXT_54 = " eDataType, String initialValue)" + NL + "\t{";
  protected final String TEXT_55 = NL + "\t\t// TODO: implement this method" + NL + "\t\t// Ensure that you remove @generated or mark it @generated NOT" + NL + "\t\tthrow new ";
  protected final String TEXT_56 = "();";
  protected final String TEXT_57 = NL + "\t\treturn (";
  protected final String TEXT_58 = ")";
  protected final String TEXT_59 = ".eINSTANCE.createFromString(";
  protected final String TEXT_60 = ".eINSTANCE.get";
  protected final String TEXT_61 = "(), initialValue);";
  protected final String TEXT_62 = NL + "\t\t";
  protected final String TEXT_63 = " result = new ";
  protected final String TEXT_64 = "();" + NL + "\t\tfor (";
  protected final String TEXT_65 = " stringTokenizer = new ";
  protected final String TEXT_66 = "(initialValue); stringTokenizer.hasMoreTokens(); )" + NL + "\t\t{" + NL + "\t\t\tString item = stringTokenizer.nextToken();" + NL + "\t\t\tresult.add(";
  protected final String TEXT_67 = ".eINSTANCE.createFromString(";
  protected final String TEXT_68 = ".eINSTANCE.get";
  protected final String TEXT_69 = "(), item));" + NL + "\t\t}" + NL + "\t\treturn result;";
  protected final String TEXT_70 = NL + "\t\ttry" + NL + "\t\t{" + NL + "\t\t\tObject result = (";
  protected final String TEXT_71 = ")";
  protected final String TEXT_72 = ".eINSTANCE.createFromString(";
  protected final String TEXT_73 = ".eINSTANCE.get";
  protected final String TEXT_74 = "(), initialValue);" + NL + "\t\t\tif (result != null)" + NL + "\t\t\t{" + NL + "\t\t\t\treturn result;" + NL + "\t\t\t}" + NL + "\t\t}" + NL + "\t\tcatch (RuntimeException exception)" + NL + "\t\t{" + NL + "\t\t}";
  protected final String TEXT_75 = NL + "\t\treturn null;";
  protected final String TEXT_76 = NL + "\t\treturn (";
  protected final String TEXT_77 = ")super.createFromString(eDataType, initialValue);";
  protected final String TEXT_78 = NL + "\t}" + NL + "" + NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */" + NL + "\tpublic String convert";
  protected final String TEXT_79 = "ToString(";
  protected final String TEXT_80 = " eDataType, Object instanceValue)" + NL + "\t{";
  protected final String TEXT_81 = NL + "\t\t// TODO: implement this method" + NL + "\t\t// Ensure that you remove @generated or mark it @generated NOT" + NL + "\t\tthrow new ";
  protected final String TEXT_82 = "();";
  protected final String TEXT_83 = NL + "\t\treturn ";
  protected final String TEXT_84 = ".eINSTANCE.convertToString(";
  protected final String TEXT_85 = ".eINSTANCE.get";
  protected final String TEXT_86 = "(), instanceValue);";
  protected final String TEXT_87 = NL + "\t\t";
  protected final String TEXT_88 = " list = (";
  protected final String TEXT_89 = ")instanceValue;" + NL + "\t\tif (list.isEmpty()) return \"\";" + NL + "\t\t";
  protected final String TEXT_90 = " result = new ";
  protected final String TEXT_91 = "();" + NL + "\t\tfor (";
  protected final String TEXT_92 = " i = list.iterator(); i.hasNext(); )" + NL + "\t\t{" + NL + "\t\t\tresult.append(";
  protected final String TEXT_93 = ".eINSTANCE.convertToString(";
  protected final String TEXT_94 = ".eINSTANCE.get";
  protected final String TEXT_95 = "(), i.next()));" + NL + "\t\t\tresult.append(' ');" + NL + "\t\t}" + NL + "\t\treturn result.substring(0, result.length() - 1);";
  protected final String TEXT_96 = NL + "\t\tif (";
  protected final String TEXT_97 = ".eINSTANCE.get";
  protected final String TEXT_98 = "().isInstance(instanceValue))" + NL + "\t\t{" + NL + "\t\t\treturn ";
  protected final String TEXT_99 = ".eINSTANCE.convertToString(";
  protected final String TEXT_100 = ".eINSTANCE.get";
  protected final String TEXT_101 = "(), instanceValue);" + NL + "\t\t}";
  protected final String TEXT_102 = NL + "\t\treturn null;";
  protected final String TEXT_103 = NL + "\t\treturn super.convertToString(eDataType, instanceValue);";
  protected final String TEXT_104 = NL + "\t}";
  protected final String TEXT_105 = NL + NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */" + NL + "\tpublic ";
  protected final String TEXT_106 = " get";
  protected final String TEXT_107 = "()" + NL + "\t{" + NL + "\t\treturn (";
  protected final String TEXT_108 = ")getEPackage();" + NL + "\t}" + NL + "" + NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @deprecated" + NL + "\t * @generated" + NL + "\t */" + NL + "\tpublic static ";
  protected final String TEXT_109 = " getPackage()" + NL + "\t{" + NL + "\t\treturn ";
  protected final String TEXT_110 = ".eINSTANCE;" + NL + "\t}" + NL + "} //";
  protected final String TEXT_111 = NL;

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
 *
 * $Id: FactoryClass.java,v 1.1 2004/03/06 17:31:31 marcelop Exp $
 */

    GenPackage genPackage = (GenPackage)argument; GenModel genModel=genPackage.getGenModel();
    stringBuffer.append(TEXT_1);
    stringBuffer.append(TEXT_2);
    stringBuffer.append("%");
    stringBuffer.append(TEXT_3);
    stringBuffer.append("%");
    stringBuffer.append(TEXT_4);
    stringBuffer.append("%");
    stringBuffer.append(TEXT_5);
    stringBuffer.append(genPackage.getClassPackageName());
    stringBuffer.append(TEXT_6);
    genModel.addImport("org.eclipse.emf.ecore.EClass");
    genModel.addImport("org.eclipse.emf.ecore.EObject");
    if (!genPackage.hasJavaLangConflict() && !genPackage.hasInterfaceImplConflict()) genModel.addImport(genPackage.getInterfacePackageName() + ".*");
    genModel.markImportLocation(stringBuffer);
    stringBuffer.append(TEXT_7);
    stringBuffer.append(genPackage.getFactoryClassName());
    stringBuffer.append(TEXT_8);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.impl.EFactoryImpl"));
    stringBuffer.append(TEXT_9);
    stringBuffer.append(genPackage.getImportedFactoryInterfaceName());
    stringBuffer.append(TEXT_10);
    if (genModel.getCopyrightText() != null) {
    stringBuffer.append(TEXT_11);
    stringBuffer.append(genModel.getImportedName("java.lang.String"));
    stringBuffer.append(TEXT_12);
    stringBuffer.append(genModel.getCopyrightText());
    stringBuffer.append(TEXT_13);
    stringBuffer.append(genModel.getNonNLS());
    stringBuffer.append(TEXT_14);
    }
    stringBuffer.append(TEXT_15);
    stringBuffer.append(genPackage.getFactoryClassName());
    stringBuffer.append(TEXT_16);
    for (Iterator i=genPackage.getGenClasses().iterator(); i.hasNext();) { GenClass genClass = (GenClass)i.next();
    if (!genClass.isAbstract()) {
    stringBuffer.append(TEXT_17);
    stringBuffer.append(genPackage.getImportedPackageInterfaceName());
    stringBuffer.append(TEXT_18);
    stringBuffer.append(genClass.getClassifierID());
    stringBuffer.append(TEXT_19);
    stringBuffer.append(!genClass.isEObjectExtension() ? "(EObject)" : "" );
    stringBuffer.append(TEXT_20);
    stringBuffer.append(genClass.getName());
    stringBuffer.append(TEXT_21);
    }
    }
    stringBuffer.append(TEXT_22);
    stringBuffer.append(genModel.getNonNLS());
    stringBuffer.append(genModel.getNonNLS(2));
    stringBuffer.append(TEXT_23);
    if (!genPackage.getAllGenDataTypes().isEmpty()) {
    stringBuffer.append(TEXT_24);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.EDataType"));
    stringBuffer.append(TEXT_25);
    for (Iterator i=genPackage.getAllGenDataTypes().iterator(); i.hasNext();) { GenDataType genDataType = (GenDataType)i.next();
    if (genDataType.isSerializable()) {
    stringBuffer.append(TEXT_26);
    stringBuffer.append(genPackage.getImportedPackageInterfaceName());
    stringBuffer.append(TEXT_27);
    stringBuffer.append(genDataType.getClassifierID());
    stringBuffer.append(TEXT_28);
    if (genDataType instanceof GenEnum) {
    stringBuffer.append(TEXT_29);
    stringBuffer.append(((GenEnum)genDataType).getImportedName());
    stringBuffer.append(TEXT_30);
    } else {
    stringBuffer.append(TEXT_31);
    stringBuffer.append(genDataType.getName());
    stringBuffer.append(TEXT_32);
    }
    }
    }
    stringBuffer.append(TEXT_33);
    stringBuffer.append(genModel.getNonNLS());
    stringBuffer.append(genModel.getNonNLS(2));
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
    if (genDataType instanceof GenEnum) {
    stringBuffer.append(TEXT_39);
    } else {
    stringBuffer.append(TEXT_40);
    stringBuffer.append(genDataType.getName());
    stringBuffer.append(TEXT_41);
    }
    }
    }
    stringBuffer.append(TEXT_42);
    stringBuffer.append(genModel.getNonNLS());
    stringBuffer.append(genModel.getNonNLS(2));
    stringBuffer.append(TEXT_43);
    }
    for (Iterator i=genPackage.getGenClasses().iterator(); i.hasNext();) { GenClass genClass = (GenClass)i.next();
    if (!genClass.isAbstract()) {
    stringBuffer.append(TEXT_44);
    stringBuffer.append(genClass.getImportedInterfaceName());
    stringBuffer.append(TEXT_45);
    stringBuffer.append(genClass.getName());
    stringBuffer.append(TEXT_46);
    stringBuffer.append(genClass.getImportedClassName());
    stringBuffer.append(TEXT_47);
    stringBuffer.append(genClass.getSafeUncapName());
    stringBuffer.append(TEXT_48);
    stringBuffer.append(genClass.getImportedClassName());
    stringBuffer.append(TEXT_49);
    stringBuffer.append(genClass.getSafeUncapName());
    stringBuffer.append(TEXT_50);
    }
    }
    for (Iterator i=genPackage.getAllGenDataTypes().iterator(); i.hasNext();) { GenDataType genDataType = (GenDataType)i.next();
    if (!(genDataType instanceof GenEnum) && genDataType.isSerializable()) {
    stringBuffer.append(TEXT_51);
    stringBuffer.append(genDataType.getObjectInstanceClassName());
    stringBuffer.append(TEXT_52);
    stringBuffer.append(genDataType.getName());
    stringBuffer.append(TEXT_53);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.EDataType"));
    stringBuffer.append(TEXT_54);
    if (genDataType.isArrayType()) {
    stringBuffer.append(TEXT_55);
    stringBuffer.append(genModel.getImportedName("java.lang.UnsupportedOperationException"));
    stringBuffer.append(TEXT_56);
    } else if (genDataType.getBaseType() != null) { GenDataType genBaseType = genDataType.getBaseType(); 
    stringBuffer.append(TEXT_57);
    stringBuffer.append(genDataType.getObjectInstanceClassName());
    stringBuffer.append(TEXT_58);
    stringBuffer.append(genBaseType.getGenPackage().getImportedFactoryInterfaceName());
    stringBuffer.append(TEXT_59);
    stringBuffer.append(genBaseType.getGenPackage().getImportedPackageInterfaceName());
    stringBuffer.append(TEXT_60);
    stringBuffer.append(genBaseType.getClassifierAccessorName());
    stringBuffer.append(TEXT_61);
    } else if (genDataType.getItemType() != null) { GenDataType genItemType = genDataType.getItemType(); 
    stringBuffer.append(TEXT_62);
    stringBuffer.append(genModel.getImportedName("java.util.List"));
    stringBuffer.append(TEXT_63);
    stringBuffer.append(genModel.getImportedName("java.util.ArrayList"));
    stringBuffer.append(TEXT_64);
    stringBuffer.append(genModel.getImportedName("java.util.StringTokenizer"));
    stringBuffer.append(TEXT_65);
    stringBuffer.append(genModel.getImportedName("java.util.StringTokenizer"));
    stringBuffer.append(TEXT_66);
    stringBuffer.append(genItemType.getGenPackage().getImportedFactoryInterfaceName());
    stringBuffer.append(TEXT_67);
    stringBuffer.append(genItemType.getGenPackage().getImportedPackageInterfaceName());
    stringBuffer.append(TEXT_68);
    stringBuffer.append(genItemType.getClassifierAccessorName());
    stringBuffer.append(TEXT_69);
    } else if (!genDataType.getMemberTypes().isEmpty()) {
    for (Iterator j = genDataType.getMemberTypes().iterator(); j.hasNext(); ) { GenDataType genMemberType = (GenDataType)j.next();
    stringBuffer.append(TEXT_70);
    stringBuffer.append(genDataType.getObjectInstanceClassName());
    stringBuffer.append(TEXT_71);
    stringBuffer.append(genMemberType.getGenPackage().getImportedFactoryInterfaceName());
    stringBuffer.append(TEXT_72);
    stringBuffer.append(genMemberType.getGenPackage().getImportedPackageInterfaceName());
    stringBuffer.append(TEXT_73);
    stringBuffer.append(genMemberType.getClassifierAccessorName());
    stringBuffer.append(TEXT_74);
    }
    stringBuffer.append(TEXT_75);
    } else {
    stringBuffer.append(TEXT_76);
    stringBuffer.append(genDataType.getObjectInstanceClassName());
    stringBuffer.append(TEXT_77);
    }
    stringBuffer.append(TEXT_78);
    stringBuffer.append(genDataType.getName());
    stringBuffer.append(TEXT_79);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.EDataType"));
    stringBuffer.append(TEXT_80);
    if (genDataType.isArrayType()) {
    stringBuffer.append(TEXT_81);
    stringBuffer.append(genModel.getImportedName("java.lang.UnsupportedOperationException"));
    stringBuffer.append(TEXT_82);
    } else if (genDataType.getBaseType() != null) { GenDataType genBaseType = genDataType.getBaseType(); 
    stringBuffer.append(TEXT_83);
    stringBuffer.append(genBaseType.getGenPackage().getImportedFactoryInterfaceName());
    stringBuffer.append(TEXT_84);
    stringBuffer.append(genBaseType.getGenPackage().getImportedPackageInterfaceName());
    stringBuffer.append(TEXT_85);
    stringBuffer.append(genBaseType.getClassifierAccessorName());
    stringBuffer.append(TEXT_86);
    } else if (genDataType.getItemType() != null) { GenDataType genItemType = genDataType.getItemType(); 
    stringBuffer.append(TEXT_87);
    stringBuffer.append(genModel.getImportedName("java.util.List"));
    stringBuffer.append(TEXT_88);
    stringBuffer.append(genModel.getImportedName("java.util.List"));
    stringBuffer.append(TEXT_89);
    stringBuffer.append(genModel.getImportedName("java.lang.StringBuffer"));
    stringBuffer.append(TEXT_90);
    stringBuffer.append(genModel.getImportedName("java.lang.StringBuffer"));
    stringBuffer.append(TEXT_91);
    stringBuffer.append(genModel.getImportedName("java.util.Iterator"));
    stringBuffer.append(TEXT_92);
    stringBuffer.append(genItemType.getGenPackage().getImportedFactoryInterfaceName());
    stringBuffer.append(TEXT_93);
    stringBuffer.append(genItemType.getGenPackage().getImportedPackageInterfaceName());
    stringBuffer.append(TEXT_94);
    stringBuffer.append(genItemType.getClassifierAccessorName());
    stringBuffer.append(TEXT_95);
    } else if (!genDataType.getMemberTypes().isEmpty()) {
    for (Iterator j = genDataType.getMemberTypes().iterator(); j.hasNext(); ) { GenDataType genMemberType = (GenDataType)j.next();
    stringBuffer.append(TEXT_96);
    stringBuffer.append(genMemberType.getGenPackage().getImportedPackageInterfaceName());
    stringBuffer.append(TEXT_97);
    stringBuffer.append(genMemberType.getClassifierAccessorName());
    stringBuffer.append(TEXT_98);
    stringBuffer.append(genMemberType.getGenPackage().getImportedFactoryInterfaceName());
    stringBuffer.append(TEXT_99);
    stringBuffer.append(genMemberType.getGenPackage().getImportedPackageInterfaceName());
    stringBuffer.append(TEXT_100);
    stringBuffer.append(genMemberType.getClassifierAccessorName());
    stringBuffer.append(TEXT_101);
    }
    stringBuffer.append(TEXT_102);
    } else {
    stringBuffer.append(TEXT_103);
    }
    stringBuffer.append(TEXT_104);
    }
    }
    stringBuffer.append(TEXT_105);
    stringBuffer.append(genPackage.getImportedPackageInterfaceName());
    stringBuffer.append(TEXT_106);
    stringBuffer.append(genPackage.getPackageInterfaceName());
    stringBuffer.append(TEXT_107);
    stringBuffer.append(genPackage.getImportedPackageInterfaceName());
    stringBuffer.append(TEXT_108);
    stringBuffer.append(genPackage.getImportedPackageInterfaceName());
    stringBuffer.append(TEXT_109);
    stringBuffer.append(genPackage.getImportedPackageInterfaceName());
    stringBuffer.append(TEXT_110);
    stringBuffer.append(genPackage.getFactoryClassName());
    genModel.emitSortedImports();
    stringBuffer.append(TEXT_111);
    return stringBuffer.toString();
  }
}
