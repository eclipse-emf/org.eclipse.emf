package org.eclipse.emf.codegen.ecore.templates.model;

import java.util.Iterator;
import java.util.Map;

import org.eclipse.emf.codegen.ecore.genmodel.GenClass;
import org.eclipse.emf.codegen.ecore.genmodel.GenClassifier;
import org.eclipse.emf.codegen.ecore.genmodel.GenDataType;
import org.eclipse.emf.codegen.ecore.genmodel.GenEnum;
import org.eclipse.emf.codegen.ecore.genmodel.GenEnumLiteral;
import org.eclipse.emf.codegen.ecore.genmodel.GenFeature;
import org.eclipse.emf.codegen.ecore.genmodel.GenModel;
import org.eclipse.emf.codegen.ecore.genmodel.GenOperation;
import org.eclipse.emf.codegen.ecore.genmodel.GenPackage;
import org.eclipse.emf.codegen.ecore.genmodel.GenParameter;
import org.eclipse.emf.codegen.ecore.genmodel.impl.Literals;
import org.eclipse.emf.ecore.EAnnotation;

public class PackageClass
{
  protected final String NL = System.getProperties().getProperty("line.separator");
  protected final String TEXT_1 = "";
  protected final String TEXT_2 = "/**" + NL + " * <copyright>" + NL + " * </copyright>" + NL + " *" + NL + " * ";
  protected final String TEXT_3 = "W%" + NL + " * @version ";
  protected final String TEXT_4 = "I% ";
  protected final String TEXT_5 = "H%" + NL + " */" + NL + "package ";
  protected final String TEXT_6 = ";" + NL;
  protected final String TEXT_7 = NL + NL + "/**" + NL + " * <!-- begin-user-doc -->" + NL + " * An implementation of the model <b>Package</b>." + NL + " * <!-- end-user-doc -->" + NL + " * @generated" + NL + " */" + NL + "public class ";
  protected final String TEXT_8 = " extends ";
  protected final String TEXT_9 = " implements ";
  protected final String TEXT_10 = NL + "{";
  protected final String TEXT_11 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */" + NL + "\tpublic static final ";
  protected final String TEXT_12 = " copyright = \"";
  protected final String TEXT_13 = "\";";
  protected final String TEXT_14 = NL;
  protected final String TEXT_15 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */" + NL + "\tprivate ";
  protected final String TEXT_16 = " ";
  protected final String TEXT_17 = " = null;" + NL;
  protected final String TEXT_18 = NL + "\t/**" + NL + "\t * Creates an instance of the model <b>Package</b>, registered with" + NL + "\t * {@link org.eclipse.emf.ecore.EPackage.Registry EPackage.Registry} by the package" + NL + "\t * package URI value." + NL + "\t * <p>Note: the correct way to create the package is via the static" + NL + "\t * factory method {@link #init init()}, which also performs" + NL + "\t * initialization of the package, or returns the registered package," + NL + "\t * if one already exists." + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @see org.eclipse.emf.ecore.EPackage.Registry" + NL + "\t * @see ";
  protected final String TEXT_19 = "#eNS_URI" + NL + "\t * @see #init()" + NL + "\t * @generated" + NL + "\t */" + NL + "\tprivate ";
  protected final String TEXT_20 = "()" + NL + "\t{" + NL + "\t\tsuper(eNS_URI, ";
  protected final String TEXT_21 = ".eINSTANCE);" + NL + "\t}" + NL + "" + NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */" + NL + "\tprivate static boolean isInited = false;" + NL + "" + NL + "\t/**" + NL + "\t * Creates, registers, and initializes the <b>Package</b> for this" + NL + "\t * model, and for any others upon which it depends.  Simple" + NL + "\t * dependencies are satisfied by calling this method on all" + NL + "\t * dependent packages before doing anything else.  This method drives" + NL + "\t * initialization for interdependent packages directly, in parallel" + NL + "\t * with this package, itself." + NL + "\t * <p>Of this package and its interdependencies, all packages which" + NL + "\t * have not yet been registered by their URI values are first created" + NL + "\t * and registered.  The packages are then initialized in two steps:" + NL + "\t * meta-model objects for all of the packages are created before any" + NL + "\t * are initialized, since one package's meta-model objects may refer to" + NL + "\t * those of another." + NL + "\t * <p>Invocation of this method will not affect any packages that have" + NL + "\t * already been initialized." + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @see #eNS_URI" + NL + "\t * @see #createPackageContents()" + NL + "\t * @see #initializePackageContents()" + NL + "\t * @generated" + NL + "\t */" + NL + "\tpublic static ";
  protected final String TEXT_22 = " init()" + NL + "\t{" + NL + "\t\tif (isInited) return (";
  protected final String TEXT_23 = ")";
  protected final String TEXT_24 = ".Registry.INSTANCE.get(";
  protected final String TEXT_25 = ".eNS_URI);" + NL + "" + NL + "\t\t// Obtain or create and register package." + NL + "\t\t";
  protected final String TEXT_26 = " the";
  protected final String TEXT_27 = " = (";
  protected final String TEXT_28 = ")(";
  protected final String TEXT_29 = ".Registry.INSTANCE.get(eNS_URI) instanceof ";
  protected final String TEXT_30 = " ? ";
  protected final String TEXT_31 = ".Registry.INSTANCE.get(eNS_URI) : new ";
  protected final String TEXT_32 = "());" + NL + "" + NL + "\t\tisInited = true;" + NL;
  protected final String TEXT_33 = NL + "\t\t// Initialize simple dependencies";
  protected final String TEXT_34 = NL + "\t\t";
  protected final String TEXT_35 = ".init();";
  protected final String TEXT_36 = NL;
  protected final String TEXT_37 = NL + "\t\t// Obtain or create and register interdependencies";
  protected final String TEXT_38 = NL + "\t\t";
  protected final String TEXT_39 = " ";
  protected final String TEXT_40 = " = (";
  protected final String TEXT_41 = ")(";
  protected final String TEXT_42 = ".Registry.INSTANCE.get(";
  protected final String TEXT_43 = ".eNS_URI) instanceof ";
  protected final String TEXT_44 = " ? ";
  protected final String TEXT_45 = ".Registry.INSTANCE.get(";
  protected final String TEXT_46 = ".eNS_URI) : ";
  protected final String TEXT_47 = ".eINSTANCE);";
  protected final String TEXT_48 = NL + NL + "\t\t// Step 1: create meta-model objects" + NL + "\t\tthe";
  protected final String TEXT_49 = ".createPackageContents();";
  protected final String TEXT_50 = NL + "\t\t";
  protected final String TEXT_51 = ".createPackageContents();";
  protected final String TEXT_52 = NL + NL + "\t\t// Step 2: complete initialization" + NL + "\t\tthe";
  protected final String TEXT_53 = ".initializePackageContents();";
  protected final String TEXT_54 = NL + "\t\t";
  protected final String TEXT_55 = ".initializePackageContents();";
  protected final String TEXT_56 = NL + NL + "\t\treturn the";
  protected final String TEXT_57 = ";" + NL + "\t}" + NL;
  protected final String TEXT_58 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */" + NL + "\tpublic ";
  protected final String TEXT_59 = " get";
  protected final String TEXT_60 = "()" + NL + "\t{" + NL + "\t\treturn ";
  protected final String TEXT_61 = ";" + NL + "\t}" + NL;
  protected final String TEXT_62 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */" + NL + "\tpublic ";
  protected final String TEXT_63 = " get";
  protected final String TEXT_64 = "()" + NL + "\t{" + NL + "\t\treturn (";
  protected final String TEXT_65 = ")";
  protected final String TEXT_66 = ".getEStructuralFeatures().get(";
  protected final String TEXT_67 = ");" + NL + "\t}" + NL;
  protected final String TEXT_68 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */" + NL + "\tpublic ";
  protected final String TEXT_69 = " get";
  protected final String TEXT_70 = "()" + NL + "\t{" + NL + "\t\treturn (";
  protected final String TEXT_71 = ")getEFactoryInstance();" + NL + "\t}" + NL + "" + NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */" + NL + "\tprivate boolean isCreated = false;" + NL + "" + NL + "\t/**" + NL + "\t * Creates the meta-model objects for the package.  This method is" + NL + "\t * guarded to have no affect on any invocation but its first." + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */" + NL + "\tpublic void createPackageContents()" + NL + "\t{" + NL + "\t\tif (isCreated) return;" + NL + "\t\tisCreated = true;";
  protected final String TEXT_72 = NL + NL + "\t\t// Create classes and their features";
  protected final String TEXT_73 = NL + "\t\t";
  protected final String TEXT_74 = " = create";
  protected final String TEXT_75 = "(";
  protected final String TEXT_76 = ");";
  protected final String TEXT_77 = NL + "\t\tcreate";
  protected final String TEXT_78 = "(";
  protected final String TEXT_79 = ", ";
  protected final String TEXT_80 = ");";
  protected final String TEXT_81 = NL;
  protected final String TEXT_82 = NL + NL + "\t\t// Create enums";
  protected final String TEXT_83 = NL + "\t\t";
  protected final String TEXT_84 = " = createEEnum(";
  protected final String TEXT_85 = ");";
  protected final String TEXT_86 = NL + NL + "\t\t// Create data types";
  protected final String TEXT_87 = NL + "\t\t";
  protected final String TEXT_88 = " = createEDataType(";
  protected final String TEXT_89 = ");";
  protected final String TEXT_90 = NL + "\t}" + NL + "" + NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */" + NL + "\tprivate boolean isInitialized = false;" + NL + "" + NL + "\t/**" + NL + "\t * Complete the initialization of the package and its meta-model.  This" + NL + "\t * method is guarded to have no affect on any invocation but its first." + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */" + NL + "\tpublic void initializePackageContents()" + NL + "\t{" + NL + "\t\tif (isInitialized) return;" + NL + "\t\tisInitialized = true;" + NL + "" + NL + "\t\t// Initialize package" + NL + "\t\tsetName(eNAME);" + NL + "\t\tsetNsPrefix(eNS_PREFIX);" + NL + "\t\tsetNsURI(eNS_URI);";
  protected final String TEXT_91 = NL + NL + "\t\t// Obtain other dependent packages";
  protected final String TEXT_92 = NL + "\t\t";
  protected final String TEXT_93 = " ";
  protected final String TEXT_94 = " = (";
  protected final String TEXT_95 = ")";
  protected final String TEXT_96 = ".Registry.INSTANCE.getEPackage(";
  protected final String TEXT_97 = ".eNS_URI);";
  protected final String TEXT_98 = NL + NL + "\t\t// Add subpackages";
  protected final String TEXT_99 = NL + "\t\tgetESubpackages().add(";
  protected final String TEXT_100 = ");";
  protected final String TEXT_101 = NL + NL + "\t\t// Add supertypes to classes";
  protected final String TEXT_102 = NL + "\t\t";
  protected final String TEXT_103 = ".getESuperTypes().add(";
  protected final String TEXT_104 = ".get";
  protected final String TEXT_105 = "());";
  protected final String TEXT_106 = NL + NL + "\t\t// Initialize classes and features; add operations and parameters";
  protected final String TEXT_107 = NL + "\t\tinitEClass(";
  protected final String TEXT_108 = ", ";
  protected final String TEXT_109 = ".class, \"";
  protected final String TEXT_110 = "\", ";
  protected final String TEXT_111 = ", ";
  protected final String TEXT_112 = ");";
  protected final String TEXT_113 = NL + "\t\tinitEReference(get";
  protected final String TEXT_114 = "(), ";
  protected final String TEXT_115 = ".get";
  protected final String TEXT_116 = "(), ";
  protected final String TEXT_117 = ", \"";
  protected final String TEXT_118 = "\", ";
  protected final String TEXT_119 = ", ";
  protected final String TEXT_120 = ", ";
  protected final String TEXT_121 = ", ";
  protected final String TEXT_122 = ", ";
  protected final String TEXT_123 = ", ";
  protected final String TEXT_124 = ", ";
  protected final String TEXT_125 = ", ";
  protected final String TEXT_126 = ", ";
  protected final String TEXT_127 = ", ";
  protected final String TEXT_128 = ", ";
  protected final String TEXT_129 = ");";
  protected final String TEXT_130 = NL + "\t\tinitEAttribute(get";
  protected final String TEXT_131 = "(), ";
  protected final String TEXT_132 = ".get";
  protected final String TEXT_133 = "(), \"";
  protected final String TEXT_134 = "\", ";
  protected final String TEXT_135 = ", ";
  protected final String TEXT_136 = ", ";
  protected final String TEXT_137 = ", ";
  protected final String TEXT_138 = ", ";
  protected final String TEXT_139 = ", ";
  protected final String TEXT_140 = ", ";
  protected final String TEXT_141 = ", ";
  protected final String TEXT_142 = ", ";
  protected final String TEXT_143 = ", ";
  protected final String TEXT_144 = ");";
  protected final String TEXT_145 = NL;
  protected final String TEXT_146 = NL + "\t\t";
  protected final String TEXT_147 = "addEOperation(";
  protected final String TEXT_148 = ", ";
  protected final String TEXT_149 = ".get";
  protected final String TEXT_150 = "(), \"";
  protected final String TEXT_151 = "\");";
  protected final String TEXT_152 = NL + "\t\t";
  protected final String TEXT_153 = "addEOperation(";
  protected final String TEXT_154 = ", null, \"";
  protected final String TEXT_155 = "\");";
  protected final String TEXT_156 = NL + "\t\taddEParameter(op, ";
  protected final String TEXT_157 = ".get";
  protected final String TEXT_158 = "(), \"";
  protected final String TEXT_159 = "\");";
  protected final String TEXT_160 = NL + "\t\taddEException(op, ";
  protected final String TEXT_161 = ".get";
  protected final String TEXT_162 = "());";
  protected final String TEXT_163 = NL;
  protected final String TEXT_164 = NL + NL + "\t\t// Initialize enums and add enum literals";
  protected final String TEXT_165 = NL + "\t\tinitEEnum(";
  protected final String TEXT_166 = ", ";
  protected final String TEXT_167 = ".class, \"";
  protected final String TEXT_168 = "\");";
  protected final String TEXT_169 = NL + "\t\taddEEnumLiteral(";
  protected final String TEXT_170 = ", ";
  protected final String TEXT_171 = ".";
  protected final String TEXT_172 = "_LITERAL);";
  protected final String TEXT_173 = NL;
  protected final String TEXT_174 = NL + NL + "\t\t// Initialize data types";
  protected final String TEXT_175 = NL + "\t\tinitEDataType(";
  protected final String TEXT_176 = ", ";
  protected final String TEXT_177 = ".class, \"";
  protected final String TEXT_178 = "\", ";
  protected final String TEXT_179 = ");";
  protected final String TEXT_180 = NL + NL + "\t\t// Create resource" + NL + "\t\tcreateResource(eNS_URI);";
  protected final String TEXT_181 = NL + NL + "\t\t// Create annotations";
  protected final String TEXT_182 = NL + "\t\t// ";
  protected final String TEXT_183 = NL + "\t\tcreate";
  protected final String TEXT_184 = "Annotations();";
  protected final String TEXT_185 = NL + "\t}";
  protected final String TEXT_186 = NL + NL + "\t/**" + NL + "\t * Initializes the annotations for <b>";
  protected final String TEXT_187 = "</b>." + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */" + NL + "\tprotected void create";
  protected final String TEXT_188 = "Annotations()" + NL + "\t{" + NL + "\t\tString source = ";
  protected final String TEXT_189 = "null;";
  protected final String TEXT_190 = "\"";
  protected final String TEXT_191 = "\";";
  protected final String TEXT_192 = "\t";
  protected final String TEXT_193 = "\t" + NL + "\t\taddAnnotation" + NL + "\t\t  (";
  protected final String TEXT_194 = ", " + NL + "\t\t   source, " + NL + "\t\t   new String[] " + NL + "\t\t   {";
  protected final String TEXT_195 = NL + "\t\t\t ";
  protected final String TEXT_196 = ", ";
  protected final String TEXT_197 = NL + "\t\t   });";
  protected final String TEXT_198 = NL + "\t}";
  protected final String TEXT_199 = NL + "} //";
  protected final String TEXT_200 = NL;

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
 * $Id: PackageClass.java,v 1.1 2004/03/06 17:31:31 marcelop Exp $
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
    genModel.markImportLocation(stringBuffer);
    stringBuffer.append(TEXT_7);
    stringBuffer.append(genPackage.getPackageClassName());
    stringBuffer.append(TEXT_8);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.impl.EPackageImpl"));
    stringBuffer.append(TEXT_9);
    stringBuffer.append(genPackage.getImportedPackageInterfaceName());
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
    for (Iterator i=genPackage.getGenClassifiers().iterator(); i.hasNext();) { GenClassifier genClassifier = (GenClassifier)i.next();
    stringBuffer.append(TEXT_15);
    stringBuffer.append(genClassifier.getImportedMetaType());
    stringBuffer.append(TEXT_16);
    stringBuffer.append(genClassifier.getClassifierInstanceName());
    stringBuffer.append(TEXT_17);
    }
    stringBuffer.append(TEXT_18);
    stringBuffer.append(genPackage.getQualifiedPackageInterfaceName());
    stringBuffer.append(TEXT_19);
    stringBuffer.append(genPackage.getPackageClassName());
    stringBuffer.append(TEXT_20);
    stringBuffer.append(genPackage.getImportedFactoryInterfaceName());
    stringBuffer.append(TEXT_21);
    stringBuffer.append(genPackage.getImportedPackageInterfaceName());
    stringBuffer.append(TEXT_22);
    stringBuffer.append(genPackage.getImportedPackageInterfaceName());
    stringBuffer.append(TEXT_23);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.EPackage"));
    stringBuffer.append(TEXT_24);
    stringBuffer.append(genPackage.getImportedPackageInterfaceName());
    stringBuffer.append(TEXT_25);
    stringBuffer.append(genPackage.getPackageClassName());
    stringBuffer.append(TEXT_26);
    stringBuffer.append(genPackage.getPackageInterfaceName());
    stringBuffer.append(TEXT_27);
    stringBuffer.append(genPackage.getPackageClassName());
    stringBuffer.append(TEXT_28);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.EPackage"));
    stringBuffer.append(TEXT_29);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.EPackage"));
    stringBuffer.append(TEXT_30);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.EPackage"));
    stringBuffer.append(TEXT_31);
    stringBuffer.append(genPackage.getPackageClassName());
    stringBuffer.append(TEXT_32);
    if (!genPackage.getPackageSimpleDependencies().isEmpty()) {
    stringBuffer.append(TEXT_33);
    for (Iterator p=genPackage.getPackageSimpleDependencies().iterator(); p.hasNext();) { GenPackage dep = (GenPackage)p.next();
    stringBuffer.append(TEXT_34);
    stringBuffer.append(dep.getImportedPackageClassName());
    stringBuffer.append(TEXT_35);
    }
    stringBuffer.append(TEXT_36);
    }
    stringBuffer.append(TEXT_37);
    for (Iterator p=genPackage.getPackageInterDependencies().iterator(); p.hasNext();) { GenPackage interdep = (GenPackage)p.next();
    stringBuffer.append(TEXT_38);
    stringBuffer.append(interdep.getImportedPackageClassName());
    stringBuffer.append(TEXT_39);
    stringBuffer.append(genPackage.getPackageInstanceVariable(interdep));
    stringBuffer.append(TEXT_40);
    stringBuffer.append(interdep.getImportedPackageClassName());
    stringBuffer.append(TEXT_41);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.EPackage"));
    stringBuffer.append(TEXT_42);
    stringBuffer.append(interdep.getImportedPackageInterfaceName());
    stringBuffer.append(TEXT_43);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.EPackage"));
    stringBuffer.append(TEXT_44);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.EPackage"));
    stringBuffer.append(TEXT_45);
    stringBuffer.append(interdep.getImportedPackageInterfaceName());
    stringBuffer.append(TEXT_46);
    stringBuffer.append(interdep.getImportedPackageClassName());
    stringBuffer.append(TEXT_47);
    }
    stringBuffer.append(TEXT_48);
    stringBuffer.append(genPackage.getPackageInterfaceName());
    stringBuffer.append(TEXT_49);
    for (Iterator p=genPackage.getPackageInterDependencies().iterator(); p.hasNext();) { GenPackage interdep = (GenPackage)p.next();
    stringBuffer.append(TEXT_50);
    stringBuffer.append(genPackage.getPackageInstanceVariable(interdep));
    stringBuffer.append(TEXT_51);
    }
    stringBuffer.append(TEXT_52);
    stringBuffer.append(genPackage.getPackageInterfaceName());
    stringBuffer.append(TEXT_53);
    for (Iterator p=genPackage.getPackageInterDependencies().iterator(); p.hasNext();) { GenPackage interdep = (GenPackage)p.next();
    stringBuffer.append(TEXT_54);
    stringBuffer.append(genPackage.getPackageInstanceVariable(interdep));
    stringBuffer.append(TEXT_55);
    }
    stringBuffer.append(TEXT_56);
    stringBuffer.append(genPackage.getPackageInterfaceName());
    stringBuffer.append(TEXT_57);
    for (Iterator m=genPackage.getGenClassifiers().iterator(); m.hasNext();) { GenClassifier genClassifier = (GenClassifier)m.next();
    stringBuffer.append(TEXT_58);
    stringBuffer.append(genClassifier.getImportedMetaType());
    stringBuffer.append(TEXT_59);
    stringBuffer.append(genClassifier.getClassifierAccessorName());
    stringBuffer.append(TEXT_60);
    stringBuffer.append(genClassifier.getClassifierInstanceName());
    stringBuffer.append(TEXT_61);
    if (genClassifier instanceof GenClass) { GenClass genClass = (GenClass)genClassifier;
    for (Iterator f=genClass.getGenFeatures().iterator(); f.hasNext();) { GenFeature genFeature = (GenFeature)f.next();
    stringBuffer.append(TEXT_62);
    stringBuffer.append(genFeature.getImportedMetaType());
    stringBuffer.append(TEXT_63);
    stringBuffer.append(genFeature.getFeatureAccessorName());
    stringBuffer.append(TEXT_64);
    stringBuffer.append(genFeature.getImportedMetaType());
    stringBuffer.append(TEXT_65);
    stringBuffer.append(genClass.getClassifierInstanceName());
    stringBuffer.append(TEXT_66);
    stringBuffer.append(genClass.getLocalFeatureIndex(genFeature));
    stringBuffer.append(TEXT_67);
    }
    }
    }
    stringBuffer.append(TEXT_68);
    stringBuffer.append(genPackage.getImportedFactoryInterfaceName());
    stringBuffer.append(TEXT_69);
    stringBuffer.append(genPackage.getFactoryInterfaceName());
    stringBuffer.append(TEXT_70);
    stringBuffer.append(genPackage.getImportedFactoryInterfaceName());
    stringBuffer.append(TEXT_71);
    if (!genPackage.getGenClasses().isEmpty()) {
    stringBuffer.append(TEXT_72);
    for (Iterator c=genPackage.getGenClasses().iterator(); c.hasNext();) { GenClass genClass = (GenClass)c.next();
    stringBuffer.append(TEXT_73);
    stringBuffer.append(genClass.getClassifierInstanceName());
    stringBuffer.append(TEXT_74);
    stringBuffer.append(genClass.getMetaType());
    stringBuffer.append(TEXT_75);
    stringBuffer.append(genClass.getClassifierID());
    stringBuffer.append(TEXT_76);
    for (Iterator f=genClass.getGenFeatures().iterator(); f.hasNext();) { GenFeature genFeature = (GenFeature)f.next();
    stringBuffer.append(TEXT_77);
    stringBuffer.append(genFeature.getMetaType());
    stringBuffer.append(TEXT_78);
    stringBuffer.append(genClass.getClassifierInstanceName());
    stringBuffer.append(TEXT_79);
    stringBuffer.append(genClass.getFeatureID(genFeature));
    stringBuffer.append(TEXT_80);
    }
    if (c.hasNext()) {
    stringBuffer.append(TEXT_81);
    }
    }
    }
    if (!genPackage.getGenEnums().isEmpty()) {
    stringBuffer.append(TEXT_82);
    for (Iterator e=genPackage.getGenEnums().iterator(); e.hasNext();) { GenEnum genEnum = (GenEnum)e.next();
    stringBuffer.append(TEXT_83);
    stringBuffer.append(genEnum.getClassifierInstanceName());
    stringBuffer.append(TEXT_84);
    stringBuffer.append(genEnum.getClassifierID());
    stringBuffer.append(TEXT_85);
    }
    }
    if (!genPackage.getGenDataTypes().isEmpty()) {
    stringBuffer.append(TEXT_86);
    for (Iterator d=genPackage.getGenDataTypes().iterator(); d.hasNext();) { GenDataType genDataType = (GenDataType)d.next();
    stringBuffer.append(TEXT_87);
    stringBuffer.append(genDataType.getClassifierInstanceName());
    stringBuffer.append(TEXT_88);
    stringBuffer.append(genDataType.getClassifierID());
    stringBuffer.append(TEXT_89);
    }
    }
    stringBuffer.append(TEXT_90);
    if (!genPackage.getPackageInitializationDependencies().isEmpty()) {
    stringBuffer.append(TEXT_91);
    for (Iterator p=genPackage.getPackageInitializationDependencies().iterator(); p.hasNext();) { GenPackage dep = (GenPackage)p.next();
    stringBuffer.append(TEXT_92);
    stringBuffer.append(dep.getImportedPackageClassName());
    stringBuffer.append(TEXT_93);
    stringBuffer.append(genPackage.getPackageInstanceVariable(dep));
    stringBuffer.append(TEXT_94);
    stringBuffer.append(dep.getImportedPackageClassName());
    stringBuffer.append(TEXT_95);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.EPackage"));
    stringBuffer.append(TEXT_96);
    stringBuffer.append(dep.getImportedPackageInterfaceName());
    stringBuffer.append(TEXT_97);
    }
    }
    if (!genPackage.getSubGenPackages().isEmpty()) {
    stringBuffer.append(TEXT_98);
    for (Iterator p=genPackage.getSubGenPackages().iterator(); p.hasNext();) { GenPackage sub = (GenPackage)p.next();
    stringBuffer.append(TEXT_99);
    stringBuffer.append(genPackage.getPackageInstanceVariable(sub));
    stringBuffer.append(TEXT_100);
    }
    }
    if (!genPackage.getGenClasses().isEmpty()) { boolean firstOperationAssignment = true; 
    stringBuffer.append(TEXT_101);
    for (Iterator c=genPackage.getGenClasses().iterator(); c.hasNext();) { GenClass genClass = (GenClass)c.next();
    for (Iterator b=genClass.getBaseGenClasses().iterator(); b.hasNext();) { GenClass baseGenClass = (GenClass)b.next();
    stringBuffer.append(TEXT_102);
    stringBuffer.append(genClass.getClassifierInstanceName());
    stringBuffer.append(TEXT_103);
    stringBuffer.append(genPackage.getPackageInstanceVariable(baseGenClass.getGenPackage()));
    stringBuffer.append(TEXT_104);
    stringBuffer.append(baseGenClass.getClassifierAccessorName());
    stringBuffer.append(TEXT_105);
    }
    }
    stringBuffer.append(TEXT_106);
    for (Iterator c=genPackage.getGenClasses().iterator(); c.hasNext();) { GenClass genClass = (GenClass)c.next();
    stringBuffer.append(TEXT_107);
    stringBuffer.append(genClass.getClassifierInstanceName());
    stringBuffer.append(TEXT_108);
    stringBuffer.append(genClass.getImportedInterfaceName());
    stringBuffer.append(TEXT_109);
    stringBuffer.append(genClass.getName());
    stringBuffer.append(TEXT_110);
    stringBuffer.append(genClass.getAbstractFlag());
    stringBuffer.append(TEXT_111);
    stringBuffer.append(genClass.getInterfaceFlag());
    stringBuffer.append(TEXT_112);
    stringBuffer.append(genModel.getNonNLS());
    for (Iterator f=genClass.getGenFeatures().iterator(); f.hasNext();) { GenFeature genFeature = (GenFeature)f.next();
    if (genFeature.isReferenceType()) { GenFeature reverseGenFeature = genFeature.getReverse();
    String reverse = reverseGenFeature == null ? "null" : genPackage.getPackageInstanceVariable(reverseGenFeature.getGenPackage()) + ".get" + reverseGenFeature.getFeatureAccessorName() + "()";
    stringBuffer.append(TEXT_113);
    stringBuffer.append(genFeature.getFeatureAccessorName());
    stringBuffer.append(TEXT_114);
    stringBuffer.append(genPackage.getPackageInstanceVariable(genFeature.getTypeGenPackage()));
    stringBuffer.append(TEXT_115);
    stringBuffer.append(genFeature.getTypeClassifier());
    stringBuffer.append(TEXT_116);
    stringBuffer.append(reverse);
    stringBuffer.append(TEXT_117);
    stringBuffer.append(genFeature.getName());
    stringBuffer.append(TEXT_118);
    stringBuffer.append(genFeature.getDefaultValue());
    stringBuffer.append(TEXT_119);
    stringBuffer.append(genFeature.getLowerBound());
    stringBuffer.append(TEXT_120);
    stringBuffer.append(genFeature.getUpperBound());
    stringBuffer.append(TEXT_121);
    stringBuffer.append(genFeature.getTransientFlag());
    stringBuffer.append(TEXT_122);
    stringBuffer.append(genFeature.getVolatileFlag());
    stringBuffer.append(TEXT_123);
    stringBuffer.append(genFeature.getChangeableFlag());
    stringBuffer.append(TEXT_124);
    stringBuffer.append(genFeature.getContainmentFlag());
    stringBuffer.append(TEXT_125);
    stringBuffer.append(genFeature.getResolveProxiesFlag());
    stringBuffer.append(TEXT_126);
    stringBuffer.append(genFeature.getUnsettableFlag());
    stringBuffer.append(TEXT_127);
    stringBuffer.append(genFeature.getUniqueFlag());
    stringBuffer.append(TEXT_128);
    stringBuffer.append(genFeature.getDerivedFlag());
    stringBuffer.append(TEXT_129);
    stringBuffer.append(genModel.getNonNLS());
    stringBuffer.append(genModel.getNonNLS(genFeature.getDefaultValue(), 2));
    } else {
    stringBuffer.append(TEXT_130);
    stringBuffer.append(genFeature.getFeatureAccessorName());
    stringBuffer.append(TEXT_131);
    stringBuffer.append(genPackage.getPackageInstanceVariable(genFeature.getTypeGenPackage()));
    stringBuffer.append(TEXT_132);
    stringBuffer.append(genFeature.getTypeClassifier());
    stringBuffer.append(TEXT_133);
    stringBuffer.append(genFeature.getName());
    stringBuffer.append(TEXT_134);
    stringBuffer.append(genFeature.getDefaultValue());
    stringBuffer.append(TEXT_135);
    stringBuffer.append(genFeature.getLowerBound());
    stringBuffer.append(TEXT_136);
    stringBuffer.append(genFeature.getUpperBound());
    stringBuffer.append(TEXT_137);
    stringBuffer.append(genFeature.getTransientFlag());
    stringBuffer.append(TEXT_138);
    stringBuffer.append(genFeature.getVolatileFlag());
    stringBuffer.append(TEXT_139);
    stringBuffer.append(genFeature.getChangeableFlag());
    stringBuffer.append(TEXT_140);
    stringBuffer.append(genFeature.getUnsettableFlag());
    stringBuffer.append(TEXT_141);
    stringBuffer.append(genFeature.getIDFlag());
    stringBuffer.append(TEXT_142);
    stringBuffer.append(genFeature.getUniqueFlag());
    stringBuffer.append(TEXT_143);
    stringBuffer.append(genFeature.getDerivedFlag());
    stringBuffer.append(TEXT_144);
    stringBuffer.append(genModel.getNonNLS());
    stringBuffer.append(genModel.getNonNLS(genFeature.getDefaultValue(), 2));
    }
    }
    for (Iterator o=genClass.getGenOperations().iterator(); o.hasNext();) { GenOperation genOperation = (GenOperation)o.next(); String prefix = ""; if (!genOperation.getGenParameters().isEmpty() || !genOperation.getGenExceptions().isEmpty()) { if (firstOperationAssignment) { firstOperationAssignment = false; prefix = genModel.getImportedName("org.eclipse.emf.ecore.EOperation") + " op = "; } else { prefix = "op = "; }} 
    stringBuffer.append(TEXT_145);
    if (!genOperation.isVoid()) {
    stringBuffer.append(TEXT_146);
    stringBuffer.append(prefix);
    stringBuffer.append(TEXT_147);
    stringBuffer.append(genClass.getClassifierInstanceName());
    stringBuffer.append(TEXT_148);
    stringBuffer.append(genPackage.getPackageInstanceVariable(genOperation.getReturnTypeGenPackage()));
    stringBuffer.append(TEXT_149);
    stringBuffer.append(genOperation.getReturnTypeClassifier());
    stringBuffer.append(TEXT_150);
    stringBuffer.append(genOperation.getName());
    stringBuffer.append(TEXT_151);
    stringBuffer.append(genModel.getNonNLS());
    } else {
    stringBuffer.append(TEXT_152);
    stringBuffer.append(prefix);
    stringBuffer.append(TEXT_153);
    stringBuffer.append(genClass.getClassifierInstanceName());
    stringBuffer.append(TEXT_154);
    stringBuffer.append(genOperation.getName());
    stringBuffer.append(TEXT_155);
    stringBuffer.append(genModel.getNonNLS());
    }
    for (Iterator p=genOperation.getGenParameters().iterator(); p.hasNext();) { GenParameter genParameter = (GenParameter)p.next();
    stringBuffer.append(TEXT_156);
    stringBuffer.append(genPackage.getPackageInstanceVariable(genParameter.getTypeGenPackage()));
    stringBuffer.append(TEXT_157);
    stringBuffer.append(genParameter.getTypeClassifier());
    stringBuffer.append(TEXT_158);
    stringBuffer.append(genParameter.getName());
    stringBuffer.append(TEXT_159);
    stringBuffer.append(genModel.getNonNLS());
    }
    for (Iterator p=genOperation.getGenExceptions().iterator(); p.hasNext();) { GenClassifier genException = (GenClassifier)p.next();
    stringBuffer.append(TEXT_160);
    stringBuffer.append(genPackage.getPackageInstanceVariable(genException.getGenPackage()));
    stringBuffer.append(TEXT_161);
    stringBuffer.append(genException.getClassifierAccessorName());
    stringBuffer.append(TEXT_162);
    }
    }
    if (c.hasNext()) {
    stringBuffer.append(TEXT_163);
    }
    }
    }
    if (!genPackage.getGenEnums().isEmpty()) {
    stringBuffer.append(TEXT_164);
    for (Iterator e=genPackage.getGenEnums().iterator(); e.hasNext();) { GenEnum genEnum = (GenEnum)e.next();
    stringBuffer.append(TEXT_165);
    stringBuffer.append(genEnum.getClassifierInstanceName());
    stringBuffer.append(TEXT_166);
    stringBuffer.append(genEnum.getImportedName());
    stringBuffer.append(TEXT_167);
    stringBuffer.append(genEnum.getName());
    stringBuffer.append(TEXT_168);
    stringBuffer.append(genModel.getNonNLS());
    for (Iterator l=genEnum.getGenEnumLiterals().iterator(); l.hasNext();) { GenEnumLiteral genEnumLiteral = (GenEnumLiteral)l.next();
    stringBuffer.append(TEXT_169);
    stringBuffer.append(genEnum.getClassifierInstanceName());
    stringBuffer.append(TEXT_170);
    stringBuffer.append(genEnum.getImportedName().equals(genEnum.getClassifierID()) ? genEnum.getQualifiedName() : genEnum.getImportedName());
    stringBuffer.append(TEXT_171);
    stringBuffer.append(genEnumLiteral.getEnumLiteralID());
    stringBuffer.append(TEXT_172);
    }
    if (e.hasNext()) {
    stringBuffer.append(TEXT_173);
    }
    }
    }
    if (!genPackage.getGenDataTypes().isEmpty()) {
    stringBuffer.append(TEXT_174);
    for (Iterator d=genPackage.getGenDataTypes().iterator(); d.hasNext();) { GenDataType genDataType = (GenDataType)d.next();
    stringBuffer.append(TEXT_175);
    stringBuffer.append(genDataType.getClassifierInstanceName());
    stringBuffer.append(TEXT_176);
    stringBuffer.append(genDataType.getImportedInstanceClassName());
    stringBuffer.append(TEXT_177);
    stringBuffer.append(genDataType.getName());
    stringBuffer.append(TEXT_178);
    stringBuffer.append(genDataType.getSerializableFlag());
    stringBuffer.append(TEXT_179);
    stringBuffer.append(genModel.getNonNLS());
    }
    }
    if (genPackage.getSuperGenPackage() == null) {
    stringBuffer.append(TEXT_180);
    }
    if (!genPackage.getAnnotationSources().isEmpty()) {
    stringBuffer.append(TEXT_181);
    for (Iterator i = genPackage.getAnnotationSources().iterator(); i.hasNext();) { String annotationSource = (String)i.next();
    stringBuffer.append(TEXT_182);
    stringBuffer.append(annotationSource);
    stringBuffer.append(TEXT_183);
    stringBuffer.append(genPackage.getAnnotationSourceIdentifier(annotationSource));
    stringBuffer.append(TEXT_184);
    }
    }
    stringBuffer.append(TEXT_185);
    for (Iterator i = genPackage.getAnnotationSources().iterator(); i.hasNext();) { String annotationSource = (String)i.next();
    stringBuffer.append(TEXT_186);
    stringBuffer.append(annotationSource);
    stringBuffer.append(TEXT_187);
    stringBuffer.append(genPackage.getAnnotationSourceIdentifier(annotationSource));
    stringBuffer.append(TEXT_188);
    if (annotationSource == null) {
    stringBuffer.append(TEXT_189);
    } else {
    stringBuffer.append(TEXT_190);
    stringBuffer.append(annotationSource);
    stringBuffer.append(TEXT_191);
    stringBuffer.append(genModel.getNonNLS());
    }
    for (Iterator j = genPackage.getAllAnnotations().iterator(); j.hasNext();) { EAnnotation eAnnotation = (EAnnotation)j.next();
    stringBuffer.append(TEXT_192);
    if (annotationSource == null ? eAnnotation.getSource() == null : annotationSource.equals(eAnnotation.getSource())) {
    stringBuffer.append(TEXT_193);
    stringBuffer.append(genPackage.getAnnotatedModelElementAccessor(eAnnotation));
    stringBuffer.append(TEXT_194);
    for (Iterator k = eAnnotation.getDetails().iterator(); k.hasNext();) { Map.Entry detail = (Map.Entry)k.next(); String key = Literals.toStringLiteral((String)detail.getKey()); String value = Literals.toStringLiteral((String)detail.getValue());
    stringBuffer.append(TEXT_195);
    stringBuffer.append(key);
    stringBuffer.append(TEXT_196);
    stringBuffer.append(value);
    stringBuffer.append(k.hasNext() ? "," : "");
    stringBuffer.append(genModel.getNonNLS(key + value));
    }
    stringBuffer.append(TEXT_197);
    }
    }
    stringBuffer.append(TEXT_198);
    }
    stringBuffer.append(TEXT_199);
    stringBuffer.append(genPackage.getPackageClassName());
    genModel.emitSortedImports();
    stringBuffer.append(TEXT_200);
    return stringBuffer.toString();
  }
}
