package org.eclipse.emf.codegen.ecore.templates.model;

import java.util.*;
import org.eclipse.emf.codegen.ecore.genmodel.*;

public class PackageInterface
{
  protected final String NL = System.getProperties().getProperty("line.separator");
  protected final String TEXT_1 = "";
  protected final String TEXT_2 = "/**" + NL + " * <copyright>" + NL + " * </copyright>" + NL + " *" + NL + " * ";
  protected final String TEXT_3 = "Id";
  protected final String TEXT_4 = NL + " */" + NL + "package ";
  protected final String TEXT_5 = ";" + NL;
  protected final String TEXT_6 = NL + NL + "/**" + NL + " * <!-- begin-user-doc -->" + NL + " * The <b>Package</b> for the model." + NL + " * It contains accessors for the meta objects to represent" + NL + " * <ul>" + NL + " *   <li>each class,</li>" + NL + " *   <li>each feature of each class,</li>" + NL + " *   <li>each enum,</li>" + NL + " *   <li>and each data type</li>" + NL + " * </ul>" + NL + " * <!-- end-user-doc -->";
  protected final String TEXT_7 = NL + " * <!-- begin-model-doc -->" + NL + " * ";
  protected final String TEXT_8 = NL + " * <!-- end-model-doc -->";
  protected final String TEXT_9 = NL + " * @see ";
  protected final String TEXT_10 = NL + " * @generated" + NL + " */" + NL + "public interface ";
  protected final String TEXT_11 = " extends ";
  protected final String TEXT_12 = NL + "{";
  protected final String TEXT_13 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */" + NL + "\t";
  protected final String TEXT_14 = " copyright = \"";
  protected final String TEXT_15 = "\";";
  protected final String TEXT_16 = NL;
  protected final String TEXT_17 = NL + "\t/**" + NL + "\t * The package name." + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */" + NL + "\t";
  protected final String TEXT_18 = " eNAME = \"";
  protected final String TEXT_19 = "\";";
  protected final String TEXT_20 = NL + NL + "\t/**" + NL + "\t * The package namespace URI." + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */" + NL + "\t";
  protected final String TEXT_21 = " eNS_URI = \"";
  protected final String TEXT_22 = "\";";
  protected final String TEXT_23 = NL + NL + "\t/**" + NL + "\t * The package namespace name." + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */" + NL + "\t";
  protected final String TEXT_24 = " eNS_PREFIX = \"";
  protected final String TEXT_25 = "\";";
  protected final String TEXT_26 = NL + NL + "\t/**" + NL + "\t * The singleton instance of the package." + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */" + NL + "\t";
  protected final String TEXT_27 = " eINSTANCE = ";
  protected final String TEXT_28 = ".init();" + NL;
  protected final String TEXT_29 = NL + "\t/**";
  protected final String TEXT_30 = NL + "\t * The meta object id for the '{@link ";
  protected final String TEXT_31 = " <em>";
  protected final String TEXT_32 = "</em>}' class." + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @see ";
  protected final String TEXT_33 = NL + "\t * The meta object id for the '{@link ";
  protected final String TEXT_34 = " <em>";
  protected final String TEXT_35 = "</em>}' class." + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @see ";
  protected final String TEXT_36 = NL + "\t * The meta object id for the '{@link ";
  protected final String TEXT_37 = " <em>";
  protected final String TEXT_38 = "</em>}' enum." + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @see ";
  protected final String TEXT_39 = NL + "\t * The meta object id for the '<em>";
  protected final String TEXT_40 = "</em>' data type." + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->";
  protected final String TEXT_41 = NL + "\t * @see ";
  protected final String TEXT_42 = NL + "\t * @see ";
  protected final String TEXT_43 = "#get";
  protected final String TEXT_44 = "()" + NL + "\t * @generated" + NL + "\t */" + NL + "\tint ";
  protected final String TEXT_45 = " = ";
  protected final String TEXT_46 = ";" + NL;
  protected final String TEXT_47 = NL + "\t/**" + NL + "\t * The feature id for the '<em><b>";
  protected final String TEXT_48 = "</b></em>' ";
  protected final String TEXT_49 = "." + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t * @ordered" + NL + "\t */" + NL + "\tint ";
  protected final String TEXT_50 = " = ";
  protected final String TEXT_51 = ";" + NL;
  protected final String TEXT_52 = NL + "\t/**" + NL + "\t * The number of structural features of the the '<em>";
  protected final String TEXT_53 = "</em>' class." + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t * @ordered" + NL + "\t */" + NL + "\tint ";
  protected final String TEXT_54 = " = ";
  protected final String TEXT_55 = ";" + NL;
  protected final String TEXT_56 = NL;
  protected final String TEXT_57 = NL + "\t/**";
  protected final String TEXT_58 = NL + "\t * Returns the meta object for class '{@link ";
  protected final String TEXT_59 = " <em>";
  protected final String TEXT_60 = "</em>}'." + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @return the meta object for class '<em>";
  protected final String TEXT_61 = "</em>'." + NL + "\t * @see ";
  protected final String TEXT_62 = NL + "\t * @model ";
  protected final String TEXT_63 = NL + "\t * Returns the meta object for enum '{@link ";
  protected final String TEXT_64 = " <em>";
  protected final String TEXT_65 = "</em>}'." + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @return the meta object for enum '<em>";
  protected final String TEXT_66 = "</em>'." + NL + "\t * @see ";
  protected final String TEXT_67 = NL + "\t * Returns the meta object for data type '<em>";
  protected final String TEXT_68 = "</em>'.";
  protected final String TEXT_69 = NL + "\t * Returns the meta object for data type '{@link ";
  protected final String TEXT_70 = " <em>";
  protected final String TEXT_71 = "</em>}'.";
  protected final String TEXT_72 = NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @return the meta object for data type '<em>";
  protected final String TEXT_73 = "</em>'.";
  protected final String TEXT_74 = NL + "\t * @see ";
  protected final String TEXT_75 = NL + "\t * @model ";
  protected final String TEXT_76 = NL + "\t * @generated" + NL + "\t */" + NL + "\t";
  protected final String TEXT_77 = " get";
  protected final String TEXT_78 = "();" + NL;
  protected final String TEXT_79 = NL + "\t/**" + NL + "\t * Returns the meta object for the ";
  protected final String TEXT_80 = " '{@link ";
  protected final String TEXT_81 = "#";
  protected final String TEXT_82 = " <em>";
  protected final String TEXT_83 = "</em>}'." + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @return the meta object for the ";
  protected final String TEXT_84 = " '<em>";
  protected final String TEXT_85 = "</em>'." + NL + "\t * @see ";
  protected final String TEXT_86 = "#";
  protected final String TEXT_87 = "()";
  protected final String TEXT_88 = NL + "\t * @see #get";
  protected final String TEXT_89 = "()" + NL + "\t * @generated" + NL + "\t */" + NL + "\t";
  protected final String TEXT_90 = " get";
  protected final String TEXT_91 = "();" + NL;
  protected final String TEXT_92 = NL + "\t/**" + NL + "\t * Returns the factory that creates the instances of the model." + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @return the factory that creates the instances of the model." + NL + "\t * @generated" + NL + "\t */" + NL + "\t";
  protected final String TEXT_93 = " get";
  protected final String TEXT_94 = "();" + NL + "" + NL + "} //";
  protected final String TEXT_95 = NL;

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
    stringBuffer.append(genPackage.getInterfacePackageName());
    stringBuffer.append(TEXT_5);
    genModel.markImportLocation(stringBuffer, genPackage);
    stringBuffer.append(TEXT_6);
    if (genPackage.hasDocumentation()) {
    stringBuffer.append(TEXT_7);
    stringBuffer.append(genPackage.getDocumentation(genModel.getIndentation(stringBuffer)));
    stringBuffer.append(TEXT_8);
    }
    stringBuffer.append(TEXT_9);
    stringBuffer.append(genPackage.getQualifiedFactoryInterfaceName());
    stringBuffer.append(TEXT_10);
    stringBuffer.append(genPackage.getPackageInterfaceName());
    stringBuffer.append(TEXT_11);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.EPackage"));
    stringBuffer.append(TEXT_12);
    if (genModel.getCopyrightText() != null) {
    stringBuffer.append(TEXT_13);
    stringBuffer.append(genModel.getImportedName("java.lang.String"));
    stringBuffer.append(TEXT_14);
    stringBuffer.append(genModel.getCopyrightText());
    stringBuffer.append(TEXT_15);
    stringBuffer.append(genModel.getNonNLS());
    stringBuffer.append(TEXT_16);
    }
    stringBuffer.append(TEXT_17);
    stringBuffer.append(genModel.getImportedName("java.lang.String"));
    stringBuffer.append(TEXT_18);
    stringBuffer.append(genPackage.getPackageName());
    stringBuffer.append(TEXT_19);
    stringBuffer.append(genModel.getNonNLS());
    stringBuffer.append(TEXT_20);
    stringBuffer.append(genModel.getImportedName("java.lang.String"));
    stringBuffer.append(TEXT_21);
    stringBuffer.append(genPackage.getNSURI());
    stringBuffer.append(TEXT_22);
    stringBuffer.append(genModel.getNonNLS());
    stringBuffer.append(TEXT_23);
    stringBuffer.append(genModel.getImportedName("java.lang.String"));
    stringBuffer.append(TEXT_24);
    stringBuffer.append(genPackage.getNSName());
    stringBuffer.append(TEXT_25);
    stringBuffer.append(genModel.getNonNLS());
    stringBuffer.append(TEXT_26);
    stringBuffer.append(genPackage.getPackageInterfaceName());
    stringBuffer.append(TEXT_27);
    stringBuffer.append(genPackage.getQualifiedPackageClassName());
    stringBuffer.append(TEXT_28);
    for (Iterator i=genPackage.getOrderedGenClassifiers().iterator(); i.hasNext();) { GenClassifier genClassifier = (GenClassifier)i.next();
    stringBuffer.append(TEXT_29);
    if (genClassifier instanceof GenClass) { GenClass genClass = (GenClass)genClassifier;
    if (!genClass.isInterface()) {
    stringBuffer.append(TEXT_30);
    stringBuffer.append(genClass.getQualifiedClassName());
    stringBuffer.append(TEXT_31);
    stringBuffer.append(genClass.getFormattedName());
    stringBuffer.append(TEXT_32);
    stringBuffer.append(genClass.getQualifiedClassName());
    } else {
    stringBuffer.append(TEXT_33);
    stringBuffer.append(genClass.getQualifiedInterfaceName());
    stringBuffer.append(TEXT_34);
    stringBuffer.append(genClass.getFormattedName());
    stringBuffer.append(TEXT_35);
    stringBuffer.append(genClass.getQualifiedInterfaceName());
    }
    } else if (genClassifier instanceof GenEnum) { GenEnum genEnum = (GenEnum)genClassifier;
    stringBuffer.append(TEXT_36);
    stringBuffer.append(genEnum.getQualifiedName());
    stringBuffer.append(TEXT_37);
    stringBuffer.append(genEnum.getFormattedName());
    stringBuffer.append(TEXT_38);
    stringBuffer.append(genEnum.getQualifiedName());
    } else if (genClassifier instanceof GenDataType) { GenDataType genDataType = (GenDataType)genClassifier;
    stringBuffer.append(TEXT_39);
    stringBuffer.append(genDataType.getFormattedName());
    stringBuffer.append(TEXT_40);
    if (!genDataType.isPrimitiveType() && !genDataType.isArrayType()) {
    stringBuffer.append(TEXT_41);
    stringBuffer.append(genDataType.getQualifiedInstanceClassName());
    }
    }
    stringBuffer.append(TEXT_42);
    stringBuffer.append(genPackage.getQualifiedPackageClassName());
    stringBuffer.append(TEXT_43);
    stringBuffer.append(genClassifier.getClassifierAccessorName());
    stringBuffer.append(TEXT_44);
    stringBuffer.append(genPackage.getClassifierID(genClassifier));
    stringBuffer.append(TEXT_45);
    stringBuffer.append(genPackage.getClassifierValue(genClassifier));
    stringBuffer.append(TEXT_46);
    if (genClassifier instanceof GenClass) { GenClass genClass = (GenClass)genClassifier;
    for (Iterator f=genClass.getAllGenFeatures().iterator(); f.hasNext();) { GenFeature genFeature = (GenFeature)f.next();
    stringBuffer.append(TEXT_47);
    stringBuffer.append(genFeature.getFormattedName());
    stringBuffer.append(TEXT_48);
    stringBuffer.append(genFeature.getFeatureKind());
    stringBuffer.append(TEXT_49);
    stringBuffer.append(genClass.getFeatureID(genFeature));
    stringBuffer.append(TEXT_50);
    stringBuffer.append(genClass.getFeatureValue(genFeature));
    stringBuffer.append(TEXT_51);
    }
    stringBuffer.append(TEXT_52);
    stringBuffer.append(genClass.getFormattedName());
    stringBuffer.append(TEXT_53);
    stringBuffer.append(genClass.getFeatureCountID());
    stringBuffer.append(TEXT_54);
    stringBuffer.append(genClass.getFeatureCountValue());
    stringBuffer.append(TEXT_55);
    }
    }
    stringBuffer.append(TEXT_56);
    for (Iterator i=genPackage.getGenClassifiers().iterator(); i.hasNext();) { GenClassifier genClassifier = (GenClassifier)i.next();
    stringBuffer.append(TEXT_57);
    if (genClassifier instanceof GenClass) { GenClass genClass = (GenClass)genClassifier;
    stringBuffer.append(TEXT_58);
    stringBuffer.append(genClass.getQualifiedInterfaceName());
    stringBuffer.append(TEXT_59);
    stringBuffer.append(genClass.getFormattedName());
    stringBuffer.append(TEXT_60);
    stringBuffer.append(genClass.getFormattedName());
    stringBuffer.append(TEXT_61);
    stringBuffer.append(genClass.getQualifiedInterfaceName());
    if (genClass.isExternalInterface()) {
    stringBuffer.append(TEXT_62);
    stringBuffer.append(genClass.getModelInfo());
    }
    } else if (genClassifier instanceof GenEnum) { GenEnum genEnum = (GenEnum)genClassifier;
    stringBuffer.append(TEXT_63);
    stringBuffer.append(genEnum.getQualifiedName());
    stringBuffer.append(TEXT_64);
    stringBuffer.append(genEnum.getFormattedName());
    stringBuffer.append(TEXT_65);
    stringBuffer.append(genEnum.getFormattedName());
    stringBuffer.append(TEXT_66);
    stringBuffer.append(genEnum.getQualifiedName());
    } else if (genClassifier instanceof GenDataType) { GenDataType genDataType = (GenDataType)genClassifier;
    if (genDataType.isPrimitiveType() || genDataType.isArrayType()) {
    stringBuffer.append(TEXT_67);
    stringBuffer.append(genDataType.getFormattedName());
    stringBuffer.append(TEXT_68);
    } else {
    stringBuffer.append(TEXT_69);
    stringBuffer.append(genDataType.getQualifiedInstanceClassName());
    stringBuffer.append(TEXT_70);
    stringBuffer.append(genDataType.getFormattedName());
    stringBuffer.append(TEXT_71);
    }
    stringBuffer.append(TEXT_72);
    stringBuffer.append(genDataType.getFormattedName());
    stringBuffer.append(TEXT_73);
    if (!genDataType.isPrimitiveType() && !genDataType.isArrayType()) {
    stringBuffer.append(TEXT_74);
    stringBuffer.append(genDataType.getQualifiedInstanceClassName());
    }
    stringBuffer.append(TEXT_75);
    stringBuffer.append(genDataType.getModelInfo());
    }
    stringBuffer.append(TEXT_76);
    stringBuffer.append(genClassifier.getImportedMetaType());
    stringBuffer.append(TEXT_77);
    stringBuffer.append(genClassifier.getClassifierAccessorName());
    stringBuffer.append(TEXT_78);
    if (genClassifier instanceof GenClass) { GenClass genClass = (GenClass)genClassifier;
    for (Iterator f=genClass.getGenFeatures().iterator(); f.hasNext();) { GenFeature genFeature = (GenFeature)f.next();
    stringBuffer.append(TEXT_79);
    stringBuffer.append(genFeature.getFeatureKind());
    stringBuffer.append(TEXT_80);
    stringBuffer.append(genClass.getQualifiedInterfaceName());
    if (!genClass.isMapEntry()) {
    stringBuffer.append(TEXT_81);
    stringBuffer.append(genFeature.getGetAccessor());
    }
    stringBuffer.append(TEXT_82);
    stringBuffer.append(genFeature.getFormattedName());
    stringBuffer.append(TEXT_83);
    stringBuffer.append(genFeature.getFeatureKind());
    stringBuffer.append(TEXT_84);
    stringBuffer.append(genFeature.getFormattedName());
    stringBuffer.append(TEXT_85);
    stringBuffer.append(genClass.getQualifiedInterfaceName());
    if (!genClass.isMapEntry()) {
    stringBuffer.append(TEXT_86);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_87);
    }
    stringBuffer.append(TEXT_88);
    stringBuffer.append(genClass.getClassifierAccessorName());
    stringBuffer.append(TEXT_89);
    stringBuffer.append(genFeature.getImportedMetaType());
    stringBuffer.append(TEXT_90);
    stringBuffer.append(genFeature.getFeatureAccessorName());
    stringBuffer.append(TEXT_91);
    }
    }
    }
    stringBuffer.append(TEXT_92);
    stringBuffer.append(genPackage.getFactoryInterfaceName());
    stringBuffer.append(TEXT_93);
    stringBuffer.append(genPackage.getFactoryInterfaceName());
    stringBuffer.append(TEXT_94);
    stringBuffer.append(genPackage.getPackageInterfaceName());
    genModel.emitSortedImports();
    stringBuffer.append(TEXT_95);
    return stringBuffer.toString();
  }
}
