package org.eclipse.emf.codegen.ecore.templates.model;

import java.util.*;
import org.eclipse.emf.codegen.ecore.genmodel.*;

public class Interface
{
  protected final String NL = System.getProperties().getProperty("line.separator");
  protected final String TEXT_1 = "";
  protected final String TEXT_2 = "/**" + NL + " * <copyright>" + NL + " * </copyright>" + NL + " *" + NL + " * ";
  protected final String TEXT_3 = "Id";
  protected final String TEXT_4 = NL + " */" + NL + "package ";
  protected final String TEXT_5 = ";" + NL;
  protected final String TEXT_6 = NL + NL + "/**" + NL + " * <!-- begin-user-doc -->" + NL + " * A representation of the model object '<em><b>";
  protected final String TEXT_7 = "</b></em>'." + NL + " * <!-- end-user-doc -->";
  protected final String TEXT_8 = NL + " *" + NL + " * <!-- begin-model-doc -->" + NL + " * ";
  protected final String TEXT_9 = NL + " * <!-- end-model-doc -->";
  protected final String TEXT_10 = NL + " *";
  protected final String TEXT_11 = NL + " * <p>" + NL + " * The following features are supported:" + NL + " * <ul>";
  protected final String TEXT_12 = NL + " *   <li>{@link ";
  protected final String TEXT_13 = "#";
  protected final String TEXT_14 = " <em>";
  protected final String TEXT_15 = "</em>}</li>";
  protected final String TEXT_16 = NL + " * </ul>" + NL + " * </p>";
  protected final String TEXT_17 = NL + " *" + NL + " * @see ";
  protected final String TEXT_18 = "#get";
  protected final String TEXT_19 = "()" + NL + " * @model ";
  protected final String TEXT_20 = NL + " * @extends ";
  protected final String TEXT_21 = NL + " * @generated" + NL + " */" + NL + "public interface ";
  protected final String TEXT_22 = NL + "{";
  protected final String TEXT_23 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */" + NL + "\t";
  protected final String TEXT_24 = " copyright = \"";
  protected final String TEXT_25 = "\";";
  protected final String TEXT_26 = NL;
  protected final String TEXT_27 = NL + "\t/**" + NL + "\t * Returns the value of the '<em><b>";
  protected final String TEXT_28 = "</b></em>' ";
  protected final String TEXT_29 = ".";
  protected final String TEXT_30 = NL + "\t * The key is of type ";
  protected final String TEXT_31 = "list of {@link ";
  protected final String TEXT_32 = "}";
  protected final String TEXT_33 = "{@link ";
  protected final String TEXT_34 = "}";
  protected final String TEXT_35 = "," + NL + "\t * and the value is of type ";
  protected final String TEXT_36 = "list of {@link ";
  protected final String TEXT_37 = "}";
  protected final String TEXT_38 = "{@link ";
  protected final String TEXT_39 = "}";
  protected final String TEXT_40 = ",";
  protected final String TEXT_41 = NL + "\t * The list contents are of type {@link ";
  protected final String TEXT_42 = "}.";
  protected final String TEXT_43 = NL + "\t * The default value is <code>";
  protected final String TEXT_44 = "</code>.";
  protected final String TEXT_45 = NL + "\t * The literals are from the enumeration {@link ";
  protected final String TEXT_46 = "}.";
  protected final String TEXT_47 = NL + "\t * It is bidirectional and its opposite is '{@link ";
  protected final String TEXT_48 = "#";
  protected final String TEXT_49 = " <em>";
  protected final String TEXT_50 = "</em>}'.";
  protected final String TEXT_51 = NL + "\t * <!-- begin-user-doc -->";
  protected final String TEXT_52 = NL + "\t * <p>" + NL + "\t * If the meaning of the '<em>";
  protected final String TEXT_53 = "</em>' ";
  protected final String TEXT_54 = " isn't clear," + NL + "\t * there really should be more of a description here..." + NL + "\t * </p>";
  protected final String TEXT_55 = NL + "\t * <!-- end-user-doc -->";
  protected final String TEXT_56 = NL + "\t * <!-- begin-model-doc -->" + NL + "\t * ";
  protected final String TEXT_57 = NL + "\t * <!-- end-model-doc -->";
  protected final String TEXT_58 = NL + "\t * @return the value of the '<em>";
  protected final String TEXT_59 = "</em>' ";
  protected final String TEXT_60 = ".";
  protected final String TEXT_61 = NL + "\t * @see ";
  protected final String TEXT_62 = NL + "\t * @see #isSet";
  protected final String TEXT_63 = "()";
  protected final String TEXT_64 = NL + "\t * @see #unset";
  protected final String TEXT_65 = "()";
  protected final String TEXT_66 = NL + "\t * @see #set";
  protected final String TEXT_67 = "(";
  protected final String TEXT_68 = ")";
  protected final String TEXT_69 = NL + "\t * @see ";
  protected final String TEXT_70 = "#get";
  protected final String TEXT_71 = "()";
  protected final String TEXT_72 = NL + "\t * @see ";
  protected final String TEXT_73 = "#";
  protected final String TEXT_74 = NL + "\t * @model ";
  protected final String TEXT_75 = NL + "\t * @generated" + NL + "\t */" + NL + "\t";
  protected final String TEXT_76 = " ";
  protected final String TEXT_77 = "();" + NL;
  protected final String TEXT_78 = NL + "\t/**" + NL + "\t * Sets the value of the '{@link ";
  protected final String TEXT_79 = "#";
  protected final String TEXT_80 = " <em>";
  protected final String TEXT_81 = "</em>}' ";
  protected final String TEXT_82 = "." + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @param value the new value of the '<em>";
  protected final String TEXT_83 = "</em>' ";
  protected final String TEXT_84 = ".";
  protected final String TEXT_85 = NL + "\t * @see ";
  protected final String TEXT_86 = NL + "\t * @see #isSet";
  protected final String TEXT_87 = "()";
  protected final String TEXT_88 = NL + "\t * @see #unset";
  protected final String TEXT_89 = "()";
  protected final String TEXT_90 = NL + "\t * @see #";
  protected final String TEXT_91 = "()" + NL + "\t * @generated" + NL + "\t */" + NL + "\tvoid set";
  protected final String TEXT_92 = "(";
  protected final String TEXT_93 = " value);" + NL;
  protected final String TEXT_94 = NL + "\t/**" + NL + "\t * Unsets the value of the '{@link ";
  protected final String TEXT_95 = "#";
  protected final String TEXT_96 = " <em>";
  protected final String TEXT_97 = "</em>}' ";
  protected final String TEXT_98 = "." + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->";
  protected final String TEXT_99 = NL + "\t * @see #isSet";
  protected final String TEXT_100 = "()";
  protected final String TEXT_101 = NL + "\t * @see #";
  protected final String TEXT_102 = "()";
  protected final String TEXT_103 = NL + "\t * @see #set";
  protected final String TEXT_104 = "(";
  protected final String TEXT_105 = ")";
  protected final String TEXT_106 = NL + "\t * @generated" + NL + "\t */" + NL + "\tvoid unset";
  protected final String TEXT_107 = "();" + NL;
  protected final String TEXT_108 = NL + "\t/**" + NL + "\t * Returns whether the value of the '{@link ";
  protected final String TEXT_109 = "#";
  protected final String TEXT_110 = " <em>";
  protected final String TEXT_111 = "</em>}' ";
  protected final String TEXT_112 = " is set." + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @return whether the value of the '<em>";
  protected final String TEXT_113 = "</em>' ";
  protected final String TEXT_114 = " is set.";
  protected final String TEXT_115 = NL + "\t * @see #unset";
  protected final String TEXT_116 = "()";
  protected final String TEXT_117 = NL + "\t * @see #";
  protected final String TEXT_118 = "()";
  protected final String TEXT_119 = NL + "\t * @see #set";
  protected final String TEXT_120 = "(";
  protected final String TEXT_121 = ")";
  protected final String TEXT_122 = NL + "\t * @generated" + NL + "\t */" + NL + "\tboolean isSet";
  protected final String TEXT_123 = "();" + NL;
  protected final String TEXT_124 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->";
  protected final String TEXT_125 = NL + "\t * <!-- begin-model-doc -->" + NL + "\t * ";
  protected final String TEXT_126 = NL + "\t * <!-- end-model-doc -->";
  protected final String TEXT_127 = NL + "\t * @model ";
  protected final String TEXT_128 = NL + "\t * @generated" + NL + "\t */" + NL + "\t";
  protected final String TEXT_129 = " ";
  protected final String TEXT_130 = "(";
  protected final String TEXT_131 = ")";
  protected final String TEXT_132 = ";" + NL;
  protected final String TEXT_133 = NL + "} // ";
  protected final String TEXT_134 = NL;

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

    GenClass genClass = (GenClass)argument; GenPackage genPackage = genClass.getGenPackage(); GenModel genModel=genPackage.getGenModel();
    stringBuffer.append(TEXT_1);
    stringBuffer.append(TEXT_2);
    stringBuffer.append("$");
    stringBuffer.append(TEXT_3);
    stringBuffer.append("$");
    stringBuffer.append(TEXT_4);
    stringBuffer.append(genPackage.getInterfacePackageName());
    stringBuffer.append(TEXT_5);
    genModel.markImportLocation(stringBuffer, genPackage);
    stringBuffer.append(TEXT_6);
    stringBuffer.append(genClass.getFormattedName());
    stringBuffer.append(TEXT_7);
    if (genClass.hasDocumentation()) {
    stringBuffer.append(TEXT_8);
    stringBuffer.append(genClass.getDocumentation(genModel.getIndentation(stringBuffer)));
    stringBuffer.append(TEXT_9);
    }
    stringBuffer.append(TEXT_10);
    if (!genClass.getGenFeatures().isEmpty()) {
    stringBuffer.append(TEXT_11);
    for (Iterator i=genClass.getGenFeatures().iterator(); i.hasNext();) { GenFeature genFeature = (GenFeature)i.next();
    if (!genFeature.isSuppressedGetVisibility()) {
    stringBuffer.append(TEXT_12);
    stringBuffer.append(genClass.getQualifiedInterfaceName());
    stringBuffer.append(TEXT_13);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_14);
    stringBuffer.append(genFeature.getFormattedName());
    stringBuffer.append(TEXT_15);
    }
    }
    stringBuffer.append(TEXT_16);
    }
    stringBuffer.append(TEXT_17);
    stringBuffer.append(genPackage.getQualifiedPackageInterfaceName());
    stringBuffer.append(TEXT_18);
    stringBuffer.append(genClass.getClassifierAccessorName());
    stringBuffer.append(TEXT_19);
    stringBuffer.append(genClass.getModelInfo());
    if (genClass.needsRootExtendsInterfaceExtendsTag()) {
    stringBuffer.append(TEXT_20);
    stringBuffer.append(genModel.getImportedName(genModel.getRootExtendsInterface()));
    }
    stringBuffer.append(TEXT_21);
    stringBuffer.append(genClass.getInterfaceName());
    stringBuffer.append(genClass.getInterfaceExtends());
    stringBuffer.append(TEXT_22);
    if (genModel.getCopyrightText() != null) {
    stringBuffer.append(TEXT_23);
    stringBuffer.append(genModel.getImportedName("java.lang.String"));
    stringBuffer.append(TEXT_24);
    stringBuffer.append(genModel.getCopyrightText());
    stringBuffer.append(TEXT_25);
    stringBuffer.append(genModel.getNonNLS());
    stringBuffer.append(TEXT_26);
    }
    for (Iterator i=genClass.getGenFeatures().iterator(); i.hasNext();) { GenFeature genFeature = (GenFeature)i.next();
    if (!genFeature.isSuppressedGetVisibility()) {
    stringBuffer.append(TEXT_27);
    stringBuffer.append(genFeature.getFormattedName());
    stringBuffer.append(TEXT_28);
    stringBuffer.append(genFeature.getFeatureKind());
    stringBuffer.append(TEXT_29);
    if (genFeature.isListType()) {
    if (genFeature.isMapType()) { GenFeature keyFeature = genFeature.getMapGenClass().getMapEntryKeyFeature(); GenFeature valueFeature = genFeature.getMapGenClass().getMapEntryValueFeature(); 
    stringBuffer.append(TEXT_30);
    if (keyFeature.isListType()) {
    stringBuffer.append(TEXT_31);
    stringBuffer.append(keyFeature.getQualifiedListItemType());
    stringBuffer.append(TEXT_32);
    } else {
    stringBuffer.append(TEXT_33);
    stringBuffer.append(keyFeature.getType());
    stringBuffer.append(TEXT_34);
    }
    stringBuffer.append(TEXT_35);
    if (valueFeature.isListType()) {
    stringBuffer.append(TEXT_36);
    stringBuffer.append(valueFeature.getQualifiedListItemType());
    stringBuffer.append(TEXT_37);
    } else {
    stringBuffer.append(TEXT_38);
    stringBuffer.append(valueFeature.getType());
    stringBuffer.append(TEXT_39);
    }
    stringBuffer.append(TEXT_40);
    } else {
    stringBuffer.append(TEXT_41);
    stringBuffer.append(genFeature.getQualifiedListItemType());
    stringBuffer.append(TEXT_42);
    }
    } else if (genFeature.isSetDefaultValue()) {
    stringBuffer.append(TEXT_43);
    stringBuffer.append(genFeature.getDefaultValue());
    stringBuffer.append(TEXT_44);
    }
    if (genFeature.getGenEnumType() != null) {
    stringBuffer.append(TEXT_45);
    stringBuffer.append(genFeature.getGenEnumType().getQualifiedName());
    stringBuffer.append(TEXT_46);
    }
    if (genFeature.isBidirectional() && !genFeature.getReverse().getGenClass().isMapEntry()) { GenFeature reverseGenFeature = genFeature.getReverse(); 
    if (!reverseGenFeature.isSuppressedGetVisibility()) {
    stringBuffer.append(TEXT_47);
    stringBuffer.append(reverseGenFeature.getGenClass().getQualifiedInterfaceName());
    stringBuffer.append(TEXT_48);
    stringBuffer.append(reverseGenFeature.getGetAccessor());
    stringBuffer.append(TEXT_49);
    stringBuffer.append(reverseGenFeature.getFormattedName());
    stringBuffer.append(TEXT_50);
    }
    }
    stringBuffer.append(TEXT_51);
    if (!genFeature.hasDocumentation()) {
    stringBuffer.append(TEXT_52);
    stringBuffer.append(genFeature.getFormattedName());
    stringBuffer.append(TEXT_53);
    stringBuffer.append(genFeature.getFeatureKind());
    stringBuffer.append(TEXT_54);
    }
    stringBuffer.append(TEXT_55);
    if (genFeature.hasDocumentation()) {
    stringBuffer.append(TEXT_56);
    stringBuffer.append(genFeature.getDocumentation(genModel.getIndentation(stringBuffer)));
    stringBuffer.append(TEXT_57);
    }
    stringBuffer.append(TEXT_58);
    stringBuffer.append(genFeature.getFormattedName());
    stringBuffer.append(TEXT_59);
    stringBuffer.append(genFeature.getFeatureKind());
    stringBuffer.append(TEXT_60);
    if (genFeature.getGenEnumType() != null) {
    stringBuffer.append(TEXT_61);
    stringBuffer.append(genFeature.getGenEnumType().getQualifiedName());
    }
    if (genFeature.isUnsettable()) {
    if (!genFeature.isSuppressedIsSetVisibility()) {
    stringBuffer.append(TEXT_62);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_63);
    }
    if (genFeature.isChangeable() && !genFeature.isSuppressedUnsetVisibility()) {
    stringBuffer.append(TEXT_64);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_65);
    }
    }
    if (genFeature.isChangeable() && !genFeature.isListType() && !genFeature.isSuppressedSetVisibility()) {
    stringBuffer.append(TEXT_66);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_67);
    stringBuffer.append(genFeature.getImportedType());
    stringBuffer.append(TEXT_68);
    }
    stringBuffer.append(TEXT_69);
    stringBuffer.append(genPackage.getQualifiedPackageInterfaceName());
    stringBuffer.append(TEXT_70);
    stringBuffer.append(genFeature.getFeatureAccessorName());
    stringBuffer.append(TEXT_71);
    if (genFeature.isBidirectional() && !genFeature.getReverse().getGenClass().isMapEntry()) { GenFeature reverseGenFeature = genFeature.getReverse(); 
    if (!reverseGenFeature.isSuppressedGetVisibility()) {
    stringBuffer.append(TEXT_72);
    stringBuffer.append(reverseGenFeature.getGenClass().getQualifiedInterfaceName());
    stringBuffer.append(TEXT_73);
    stringBuffer.append(reverseGenFeature.getGetAccessor());
    }
    }
    stringBuffer.append(TEXT_74);
    stringBuffer.append(genFeature.getModelInfo());
    stringBuffer.append(TEXT_75);
    stringBuffer.append(genFeature.getImportedType());
    stringBuffer.append(TEXT_76);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_77);
    }
    if (genFeature.isChangeable() && !genFeature.isListType() && !genFeature.isSuppressedSetVisibility()) {
    stringBuffer.append(TEXT_78);
    stringBuffer.append(genClass.getQualifiedInterfaceName());
    stringBuffer.append(TEXT_79);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_80);
    stringBuffer.append(genFeature.getFormattedName());
    stringBuffer.append(TEXT_81);
    stringBuffer.append(genFeature.getFeatureKind());
    stringBuffer.append(TEXT_82);
    stringBuffer.append(genFeature.getFormattedName());
    stringBuffer.append(TEXT_83);
    stringBuffer.append(genFeature.getFeatureKind());
    stringBuffer.append(TEXT_84);
    if (genFeature.isEnumType()) {
    stringBuffer.append(TEXT_85);
    stringBuffer.append(genFeature.getGenEnumType().getQualifiedName());
    }
    if (genFeature.isUnsettable()) {
    if (!genFeature.isSuppressedIsSetVisibility()) {
    stringBuffer.append(TEXT_86);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_87);
    }
    if (!genFeature.isSuppressedUnsetVisibility()) {
    stringBuffer.append(TEXT_88);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_89);
    }
    }
    stringBuffer.append(TEXT_90);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_91);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_92);
    stringBuffer.append(genFeature.getImportedType());
    stringBuffer.append(TEXT_93);
    }
    if (genFeature.isUnsettable()) {
    if (genFeature.isChangeable() && !genFeature.isSuppressedUnsetVisibility()) {
    stringBuffer.append(TEXT_94);
    stringBuffer.append(genClass.getQualifiedInterfaceName());
    stringBuffer.append(TEXT_95);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_96);
    stringBuffer.append(genFeature.getFormattedName());
    stringBuffer.append(TEXT_97);
    stringBuffer.append(genFeature.getFeatureKind());
    stringBuffer.append(TEXT_98);
    if (!genFeature.isSuppressedIsSetVisibility()) {
    stringBuffer.append(TEXT_99);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_100);
    }
    stringBuffer.append(TEXT_101);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_102);
    if (!genFeature.isListType() && !genFeature.isSuppressedSetVisibility()) {
    stringBuffer.append(TEXT_103);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_104);
    stringBuffer.append(genFeature.getImportedType());
    stringBuffer.append(TEXT_105);
    }
    stringBuffer.append(TEXT_106);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_107);
    }
    if (!genFeature.isSuppressedIsSetVisibility()) {
    stringBuffer.append(TEXT_108);
    stringBuffer.append(genClass.getQualifiedInterfaceName());
    stringBuffer.append(TEXT_109);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_110);
    stringBuffer.append(genFeature.getFormattedName());
    stringBuffer.append(TEXT_111);
    stringBuffer.append(genFeature.getFeatureKind());
    stringBuffer.append(TEXT_112);
    stringBuffer.append(genFeature.getFormattedName());
    stringBuffer.append(TEXT_113);
    stringBuffer.append(genFeature.getFeatureKind());
    stringBuffer.append(TEXT_114);
    if (genFeature.isChangeable() && !genFeature.isSuppressedUnsetVisibility()) {
    stringBuffer.append(TEXT_115);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_116);
    }
    stringBuffer.append(TEXT_117);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_118);
    if (!genFeature.isListType() && genFeature.isChangeable() && !genFeature.isSuppressedSetVisibility()) {
    stringBuffer.append(TEXT_119);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_120);
    stringBuffer.append(genFeature.getImportedType());
    stringBuffer.append(TEXT_121);
    }
    stringBuffer.append(TEXT_122);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_123);
    }
    }
    }//for
    for (Iterator i=genClass.getGenOperations().iterator(); i.hasNext();) { GenOperation genOperation = (GenOperation)i.next();
    stringBuffer.append(TEXT_124);
    if (genOperation.hasDocumentation()) {
    stringBuffer.append(TEXT_125);
    stringBuffer.append(genOperation.getDocumentation(genModel.getIndentation(stringBuffer)));
    stringBuffer.append(TEXT_126);
    }
    stringBuffer.append(TEXT_127);
    stringBuffer.append(genOperation.getModelInfo());
    stringBuffer.append(TEXT_128);
    stringBuffer.append(genOperation.getImportedReturnType());
    stringBuffer.append(TEXT_129);
    stringBuffer.append(genOperation.getName());
    stringBuffer.append(TEXT_130);
    stringBuffer.append(genOperation.getParameters());
    stringBuffer.append(TEXT_131);
    stringBuffer.append(genOperation.getThrows());
    stringBuffer.append(TEXT_132);
    }//for
    stringBuffer.append(TEXT_133);
    stringBuffer.append(genClass.getInterfaceName());
    genModel.emitSortedImports();
    stringBuffer.append(TEXT_134);
    return stringBuffer.toString();
  }
}
