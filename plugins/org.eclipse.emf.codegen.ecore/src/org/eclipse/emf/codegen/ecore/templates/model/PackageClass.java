package org.eclipse.emf.codegen.ecore.templates.model;

import java.util.*;
import org.eclipse.emf.ecore.*;
import org.eclipse.emf.codegen.ecore.genmodel.*;
import org.eclipse.emf.codegen.ecore.genmodel.impl.Literals;

public class PackageClass
{
  protected final String NL = System.getProperties().getProperty("line.separator");
  protected final String TEXT_1 = "";
  protected final String TEXT_2 = "/**" + NL + " * <copyright>" + NL + " * </copyright>" + NL + " *" + NL + " * ";
  protected final String TEXT_3 = "Id";
  protected final String TEXT_4 = NL + " */" + NL + "package ";
  protected final String TEXT_5 = ";" + NL;
  protected final String TEXT_6 = NL + NL + "/**" + NL + " * <!-- begin-user-doc -->" + NL + " * An implementation of the model <b>Package</b>." + NL + " * <!-- end-user-doc -->" + NL + " * @generated" + NL + " */" + NL + "public class ";
  protected final String TEXT_7 = " extends ";
  protected final String TEXT_8 = " implements ";
  protected final String TEXT_9 = NL + "{";
  protected final String TEXT_10 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */" + NL + "\tpublic static final ";
  protected final String TEXT_11 = " copyright = \"";
  protected final String TEXT_12 = "\";";
  protected final String TEXT_13 = NL;
  protected final String TEXT_14 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */" + NL + "\tprivate ";
  protected final String TEXT_15 = " ";
  protected final String TEXT_16 = " = null;" + NL;
  protected final String TEXT_17 = NL + "\t/**" + NL + "\t * Creates an instance of the model <b>Package</b>, registered with" + NL + "\t * {@link org.eclipse.emf.ecore.EPackage.Registry EPackage.Registry} by the package" + NL + "\t * package URI value." + NL + "\t * <p>Note: the correct way to create the package is via the static" + NL + "\t * factory method {@link #init init()}, which also performs" + NL + "\t * initialization of the package, or returns the registered package," + NL + "\t * if one already exists." + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @see org.eclipse.emf.ecore.EPackage.Registry" + NL + "\t * @see ";
  protected final String TEXT_18 = "#eNS_URI" + NL + "\t * @see #init()" + NL + "\t * @generated" + NL + "\t */" + NL + "\tprivate ";
  protected final String TEXT_19 = "()" + NL + "\t{" + NL + "\t\tsuper(eNS_URI, ";
  protected final String TEXT_20 = ".eINSTANCE);" + NL + "\t}" + NL + "" + NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */" + NL + "\tprivate static boolean isInited = false;" + NL + "" + NL + "\t/**" + NL + "\t * Creates, registers, and initializes the <b>Package</b> for this" + NL + "\t * model, and for any others upon which it depends.  Simple" + NL + "\t * dependencies are satisfied by calling this method on all" + NL + "\t * dependent packages before doing anything else.  This method drives" + NL + "\t * initialization for interdependent packages directly, in parallel" + NL + "\t * with this package, itself." + NL + "\t * <p>Of this package and its interdependencies, all packages which" + NL + "\t * have not yet been registered by their URI values are first created" + NL + "\t * and registered.  The packages are then initialized in two steps:" + NL + "\t * meta-model objects for all of the packages are created before any" + NL + "\t * are initialized, since one package's meta-model objects may refer to" + NL + "\t * those of another." + NL + "\t * <p>Invocation of this method will not affect any packages that have" + NL + "\t * already been initialized." + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @see #eNS_URI" + NL + "\t * @see #createPackageContents()" + NL + "\t * @see #initializePackageContents()" + NL + "\t * @generated" + NL + "\t */" + NL + "\tpublic static ";
  protected final String TEXT_21 = " init()" + NL + "\t{" + NL + "\t\tif (isInited) return (";
  protected final String TEXT_22 = ")";
  protected final String TEXT_23 = ".Registry.INSTANCE.get(";
  protected final String TEXT_24 = ".eNS_URI);" + NL + "" + NL + "\t\t// Obtain or create and register package." + NL + "\t\t";
  protected final String TEXT_25 = " the";
  protected final String TEXT_26 = " = (";
  protected final String TEXT_27 = ")(";
  protected final String TEXT_28 = ".Registry.INSTANCE.get(eNS_URI) instanceof ";
  protected final String TEXT_29 = " ? ";
  protected final String TEXT_30 = ".Registry.INSTANCE.get(eNS_URI) : new ";
  protected final String TEXT_31 = "());" + NL + "" + NL + "\t\tisInited = true;" + NL;
  protected final String TEXT_32 = NL + "\t\t// Initialize simple dependencies";
  protected final String TEXT_33 = NL + "\t\t";
  protected final String TEXT_34 = ".init();";
  protected final String TEXT_35 = NL;
  protected final String TEXT_36 = NL + "\t\t// Obtain or create and register interdependencies";
  protected final String TEXT_37 = NL + "\t\t";
  protected final String TEXT_38 = " ";
  protected final String TEXT_39 = " = (";
  protected final String TEXT_40 = ")(";
  protected final String TEXT_41 = ".Registry.INSTANCE.get(";
  protected final String TEXT_42 = ".eNS_URI) instanceof ";
  protected final String TEXT_43 = " ? ";
  protected final String TEXT_44 = ".Registry.INSTANCE.get(";
  protected final String TEXT_45 = ".eNS_URI) : ";
  protected final String TEXT_46 = ".eINSTANCE);";
  protected final String TEXT_47 = NL + NL + "\t\t// Step 1: create meta-model objects" + NL + "\t\tthe";
  protected final String TEXT_48 = ".createPackageContents();";
  protected final String TEXT_49 = NL + "\t\t";
  protected final String TEXT_50 = ".createPackageContents();";
  protected final String TEXT_51 = NL + NL + "\t\t// Step 2: complete initialization" + NL + "\t\tthe";
  protected final String TEXT_52 = ".initializePackageContents();";
  protected final String TEXT_53 = NL + "\t\t";
  protected final String TEXT_54 = ".initializePackageContents();";
  protected final String TEXT_55 = NL;
  protected final String TEXT_56 = NL + "\t\t";
  protected final String TEXT_57 = ".Registry.INSTANCE.put(the";
  protected final String TEXT_58 = ", ";
  protected final String TEXT_59 = ".INSTANCE);" + NL;
  protected final String TEXT_60 = NL + "\t\treturn the";
  protected final String TEXT_61 = ";" + NL + "\t}" + NL;
  protected final String TEXT_62 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */" + NL + "\tpublic ";
  protected final String TEXT_63 = " get";
  protected final String TEXT_64 = "()" + NL + "\t{" + NL + "\t\treturn ";
  protected final String TEXT_65 = ";" + NL + "\t}" + NL;
  protected final String TEXT_66 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */" + NL + "\tpublic ";
  protected final String TEXT_67 = " get";
  protected final String TEXT_68 = "()" + NL + "\t{" + NL + "\t\treturn (";
  protected final String TEXT_69 = ")";
  protected final String TEXT_70 = ".getEStructuralFeatures().get(";
  protected final String TEXT_71 = ");" + NL + "\t}" + NL;
  protected final String TEXT_72 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */" + NL + "\tpublic ";
  protected final String TEXT_73 = " get";
  protected final String TEXT_74 = "()" + NL + "\t{" + NL + "\t\treturn (";
  protected final String TEXT_75 = ")getEFactoryInstance();" + NL + "\t}" + NL + "" + NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */" + NL + "\tprivate boolean isCreated = false;" + NL + "" + NL + "\t/**" + NL + "\t * Creates the meta-model objects for the package.  This method is" + NL + "\t * guarded to have no affect on any invocation but its first." + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */" + NL + "\tpublic void createPackageContents()" + NL + "\t{" + NL + "\t\tif (isCreated) return;" + NL + "\t\tisCreated = true;";
  protected final String TEXT_76 = NL + NL + "\t\t// Create classes and their features";
  protected final String TEXT_77 = NL + "\t\t";
  protected final String TEXT_78 = " = create";
  protected final String TEXT_79 = "(";
  protected final String TEXT_80 = ");";
  protected final String TEXT_81 = NL + "\t\tcreate";
  protected final String TEXT_82 = "(";
  protected final String TEXT_83 = ", ";
  protected final String TEXT_84 = ");";
  protected final String TEXT_85 = NL;
  protected final String TEXT_86 = NL + NL + "\t\t// Create enums";
  protected final String TEXT_87 = NL + "\t\t";
  protected final String TEXT_88 = " = createEEnum(";
  protected final String TEXT_89 = ");";
  protected final String TEXT_90 = NL + NL + "\t\t// Create data types";
  protected final String TEXT_91 = NL + "\t\t";
  protected final String TEXT_92 = " = createEDataType(";
  protected final String TEXT_93 = ");";
  protected final String TEXT_94 = NL + "\t}" + NL + "" + NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */" + NL + "\tprivate boolean isInitialized = false;" + NL + "" + NL + "\t/**" + NL + "\t * Complete the initialization of the package and its meta-model.  This" + NL + "\t * method is guarded to have no affect on any invocation but its first." + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */" + NL + "\tpublic void initializePackageContents()" + NL + "\t{" + NL + "\t\tif (isInitialized) return;" + NL + "\t\tisInitialized = true;" + NL + "" + NL + "\t\t// Initialize package" + NL + "\t\tsetName(eNAME);" + NL + "\t\tsetNsPrefix(eNS_PREFIX);" + NL + "\t\tsetNsURI(eNS_URI);";
  protected final String TEXT_95 = NL + NL + "\t\t// Obtain other dependent packages";
  protected final String TEXT_96 = NL + "\t\t";
  protected final String TEXT_97 = " ";
  protected final String TEXT_98 = " = (";
  protected final String TEXT_99 = ")";
  protected final String TEXT_100 = ".Registry.INSTANCE.getEPackage(";
  protected final String TEXT_101 = ".eNS_URI);";
  protected final String TEXT_102 = NL + NL + "\t\t// Add subpackages";
  protected final String TEXT_103 = NL + "\t\tgetESubpackages().add(";
  protected final String TEXT_104 = ");";
  protected final String TEXT_105 = NL + NL + "\t\t// Add supertypes to classes";
  protected final String TEXT_106 = NL + "\t\t";
  protected final String TEXT_107 = ".getESuperTypes().add(";
  protected final String TEXT_108 = ".get";
  protected final String TEXT_109 = "());";
  protected final String TEXT_110 = NL + NL + "\t\t// Initialize classes and features; add operations and parameters";
  protected final String TEXT_111 = NL + "\t\tinitEClass(";
  protected final String TEXT_112 = ", ";
  protected final String TEXT_113 = ".class, \"";
  protected final String TEXT_114 = "\", ";
  protected final String TEXT_115 = ", ";
  protected final String TEXT_116 = ");";
  protected final String TEXT_117 = NL + "\t\tinitEReference(get";
  protected final String TEXT_118 = "(), ";
  protected final String TEXT_119 = ".get";
  protected final String TEXT_120 = "(), ";
  protected final String TEXT_121 = ", \"";
  protected final String TEXT_122 = "\", ";
  protected final String TEXT_123 = ", ";
  protected final String TEXT_124 = ", ";
  protected final String TEXT_125 = ", ";
  protected final String TEXT_126 = ", ";
  protected final String TEXT_127 = ", ";
  protected final String TEXT_128 = ", ";
  protected final String TEXT_129 = ", ";
  protected final String TEXT_130 = ", ";
  protected final String TEXT_131 = ", ";
  protected final String TEXT_132 = ", ";
  protected final String TEXT_133 = ", ";
  protected final String TEXT_134 = ");";
  protected final String TEXT_135 = NL + "\t\tinitEAttribute(get";
  protected final String TEXT_136 = "(), ";
  protected final String TEXT_137 = ".get";
  protected final String TEXT_138 = "(), \"";
  protected final String TEXT_139 = "\", ";
  protected final String TEXT_140 = ", ";
  protected final String TEXT_141 = ", ";
  protected final String TEXT_142 = ", ";
  protected final String TEXT_143 = ", ";
  protected final String TEXT_144 = ", ";
  protected final String TEXT_145 = ", ";
  protected final String TEXT_146 = ", ";
  protected final String TEXT_147 = ", ";
  protected final String TEXT_148 = ", ";
  protected final String TEXT_149 = ", ";
  protected final String TEXT_150 = ");";
  protected final String TEXT_151 = NL;
  protected final String TEXT_152 = NL + "\t\t";
  protected final String TEXT_153 = "addEOperation(";
  protected final String TEXT_154 = ", ";
  protected final String TEXT_155 = ".get";
  protected final String TEXT_156 = "(), \"";
  protected final String TEXT_157 = "\");";
  protected final String TEXT_158 = NL + "\t\t";
  protected final String TEXT_159 = "addEOperation(";
  protected final String TEXT_160 = ", null, \"";
  protected final String TEXT_161 = "\");";
  protected final String TEXT_162 = NL + "\t\taddEParameter(op, ";
  protected final String TEXT_163 = ".get";
  protected final String TEXT_164 = "(), \"";
  protected final String TEXT_165 = "\");";
  protected final String TEXT_166 = NL + "\t\taddEException(op, ";
  protected final String TEXT_167 = ".get";
  protected final String TEXT_168 = "());";
  protected final String TEXT_169 = NL;
  protected final String TEXT_170 = NL + NL + "\t\t// Initialize enums and add enum literals";
  protected final String TEXT_171 = NL + "\t\tinitEEnum(";
  protected final String TEXT_172 = ", ";
  protected final String TEXT_173 = ".class, \"";
  protected final String TEXT_174 = "\");";
  protected final String TEXT_175 = NL + "\t\taddEEnumLiteral(";
  protected final String TEXT_176 = ", ";
  protected final String TEXT_177 = ".";
  protected final String TEXT_178 = "_LITERAL);";
  protected final String TEXT_179 = NL;
  protected final String TEXT_180 = NL + NL + "\t\t// Initialize data types";
  protected final String TEXT_181 = NL + "\t\tinitEDataType(";
  protected final String TEXT_182 = ", ";
  protected final String TEXT_183 = ".class, \"";
  protected final String TEXT_184 = "\", ";
  protected final String TEXT_185 = ");";
  protected final String TEXT_186 = NL + NL + "\t\t// Create resource" + NL + "\t\tcreateResource(eNS_URI);";
  protected final String TEXT_187 = NL + NL + "\t\t// Create annotations";
  protected final String TEXT_188 = NL + "\t\t// ";
  protected final String TEXT_189 = NL + "\t\tcreate";
  protected final String TEXT_190 = "Annotations();";
  protected final String TEXT_191 = NL + "\t}" + NL;
  protected final String TEXT_192 = NL + "\t/**" + NL + "\t * Initializes the annotations for <b>";
  protected final String TEXT_193 = "</b>." + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */" + NL + "\tprotected void create";
  protected final String TEXT_194 = "Annotations()" + NL + "\t{" + NL + "\t\tString source = ";
  protected final String TEXT_195 = "null;";
  protected final String TEXT_196 = "\"";
  protected final String TEXT_197 = "\";";
  protected final String TEXT_198 = "\t";
  protected final String TEXT_199 = "\t" + NL + "\t\taddAnnotation" + NL + "\t\t  (";
  protected final String TEXT_200 = ", " + NL + "\t\t   source, " + NL + "\t\t   new String[] " + NL + "\t\t   {";
  protected final String TEXT_201 = NL + "\t\t\t ";
  protected final String TEXT_202 = ", ";
  protected final String TEXT_203 = NL + "\t\t   });";
  protected final String TEXT_204 = NL + "\t}" + NL;
  protected final String TEXT_205 = NL + "} //";
  protected final String TEXT_206 = NL;

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
    genModel.markImportLocation(stringBuffer);
    genModel.addPseudoImport("org.eclipse.emf.ecore.EPackage.Registry");
    genModel.addPseudoImport("org.eclipse.emf.ecore.EPackage.Descriptor");
    stringBuffer.append(TEXT_6);
    stringBuffer.append(genPackage.getPackageClassName());
    stringBuffer.append(TEXT_7);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.impl.EPackageImpl"));
    stringBuffer.append(TEXT_8);
    stringBuffer.append(genPackage.getImportedPackageInterfaceName());
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
    for (Iterator i=genPackage.getGenClassifiers().iterator(); i.hasNext();) { GenClassifier genClassifier = (GenClassifier)i.next();
    stringBuffer.append(TEXT_14);
    stringBuffer.append(genClassifier.getImportedMetaType());
    stringBuffer.append(TEXT_15);
    stringBuffer.append(genClassifier.getClassifierInstanceName());
    stringBuffer.append(TEXT_16);
    }
    stringBuffer.append(TEXT_17);
    stringBuffer.append(genPackage.getQualifiedPackageInterfaceName());
    stringBuffer.append(TEXT_18);
    stringBuffer.append(genPackage.getPackageClassName());
    stringBuffer.append(TEXT_19);
    stringBuffer.append(genPackage.getImportedFactoryInterfaceName());
    stringBuffer.append(TEXT_20);
    stringBuffer.append(genPackage.getImportedPackageInterfaceName());
    stringBuffer.append(TEXT_21);
    stringBuffer.append(genPackage.getImportedPackageInterfaceName());
    stringBuffer.append(TEXT_22);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.EPackage"));
    stringBuffer.append(TEXT_23);
    stringBuffer.append(genPackage.getImportedPackageInterfaceName());
    stringBuffer.append(TEXT_24);
    stringBuffer.append(genPackage.getPackageClassName());
    stringBuffer.append(TEXT_25);
    stringBuffer.append(genPackage.getPackageInterfaceName());
    stringBuffer.append(TEXT_26);
    stringBuffer.append(genPackage.getPackageClassName());
    stringBuffer.append(TEXT_27);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.EPackage"));
    stringBuffer.append(TEXT_28);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.EPackage"));
    stringBuffer.append(TEXT_29);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.EPackage"));
    stringBuffer.append(TEXT_30);
    stringBuffer.append(genPackage.getPackageClassName());
    stringBuffer.append(TEXT_31);
    if (!genPackage.getPackageSimpleDependencies().isEmpty()) {
    stringBuffer.append(TEXT_32);
    for (Iterator p=genPackage.getPackageSimpleDependencies().iterator(); p.hasNext();) { GenPackage dep = (GenPackage)p.next();
    stringBuffer.append(TEXT_33);
    stringBuffer.append(dep.getImportedPackageClassName());
    stringBuffer.append(TEXT_34);
    }
    stringBuffer.append(TEXT_35);
    }
    stringBuffer.append(TEXT_36);
    for (Iterator p=genPackage.getPackageInterDependencies().iterator(); p.hasNext();) { GenPackage interdep = (GenPackage)p.next();
    stringBuffer.append(TEXT_37);
    stringBuffer.append(interdep.getImportedPackageClassName());
    stringBuffer.append(TEXT_38);
    stringBuffer.append(genPackage.getPackageInstanceVariable(interdep));
    stringBuffer.append(TEXT_39);
    stringBuffer.append(interdep.getImportedPackageClassName());
    stringBuffer.append(TEXT_40);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.EPackage"));
    stringBuffer.append(TEXT_41);
    stringBuffer.append(interdep.getImportedPackageInterfaceName());
    stringBuffer.append(TEXT_42);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.EPackage"));
    stringBuffer.append(TEXT_43);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.EPackage"));
    stringBuffer.append(TEXT_44);
    stringBuffer.append(interdep.getImportedPackageInterfaceName());
    stringBuffer.append(TEXT_45);
    stringBuffer.append(interdep.getImportedPackageClassName());
    stringBuffer.append(TEXT_46);
    }
    stringBuffer.append(TEXT_47);
    stringBuffer.append(genPackage.getPackageInterfaceName());
    stringBuffer.append(TEXT_48);
    for (Iterator p=genPackage.getPackageInterDependencies().iterator(); p.hasNext();) { GenPackage interdep = (GenPackage)p.next();
    stringBuffer.append(TEXT_49);
    stringBuffer.append(genPackage.getPackageInstanceVariable(interdep));
    stringBuffer.append(TEXT_50);
    }
    stringBuffer.append(TEXT_51);
    stringBuffer.append(genPackage.getPackageInterfaceName());
    stringBuffer.append(TEXT_52);
    for (Iterator p=genPackage.getPackageInterDependencies().iterator(); p.hasNext();) { GenPackage interdep = (GenPackage)p.next();
    stringBuffer.append(TEXT_53);
    stringBuffer.append(genPackage.getPackageInstanceVariable(interdep));
    stringBuffer.append(TEXT_54);
    }
    stringBuffer.append(TEXT_55);
    if (genPackage.hasConstraints()) {
    stringBuffer.append(TEXT_56);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.EValidator"));
    stringBuffer.append(TEXT_57);
    stringBuffer.append(genPackage.getPackageInterfaceName());
    stringBuffer.append(TEXT_58);
    stringBuffer.append(genPackage.getImportedValidatorClassName());
    stringBuffer.append(TEXT_59);
    }
    stringBuffer.append(TEXT_60);
    stringBuffer.append(genPackage.getPackageInterfaceName());
    stringBuffer.append(TEXT_61);
    for (Iterator m=genPackage.getGenClassifiers().iterator(); m.hasNext();) { GenClassifier genClassifier = (GenClassifier)m.next();
    stringBuffer.append(TEXT_62);
    stringBuffer.append(genClassifier.getImportedMetaType());
    stringBuffer.append(TEXT_63);
    stringBuffer.append(genClassifier.getClassifierAccessorName());
    stringBuffer.append(TEXT_64);
    stringBuffer.append(genClassifier.getClassifierInstanceName());
    stringBuffer.append(TEXT_65);
    if (genClassifier instanceof GenClass) { GenClass genClass = (GenClass)genClassifier;
    for (Iterator f=genClass.getGenFeatures().iterator(); f.hasNext();) { GenFeature genFeature = (GenFeature)f.next();
    stringBuffer.append(TEXT_66);
    stringBuffer.append(genFeature.getImportedMetaType());
    stringBuffer.append(TEXT_67);
    stringBuffer.append(genFeature.getFeatureAccessorName());
    stringBuffer.append(TEXT_68);
    stringBuffer.append(genFeature.getImportedMetaType());
    stringBuffer.append(TEXT_69);
    stringBuffer.append(genClass.getClassifierInstanceName());
    stringBuffer.append(TEXT_70);
    stringBuffer.append(genClass.getLocalFeatureIndex(genFeature));
    stringBuffer.append(TEXT_71);
    }
    }
    }
    stringBuffer.append(TEXT_72);
    stringBuffer.append(genPackage.getImportedFactoryInterfaceName());
    stringBuffer.append(TEXT_73);
    stringBuffer.append(genPackage.getFactoryInterfaceName());
    stringBuffer.append(TEXT_74);
    stringBuffer.append(genPackage.getImportedFactoryInterfaceName());
    stringBuffer.append(TEXT_75);
    if (!genPackage.getGenClasses().isEmpty()) {
    stringBuffer.append(TEXT_76);
    for (Iterator c=genPackage.getGenClasses().iterator(); c.hasNext();) { GenClass genClass = (GenClass)c.next();
    stringBuffer.append(TEXT_77);
    stringBuffer.append(genClass.getClassifierInstanceName());
    stringBuffer.append(TEXT_78);
    stringBuffer.append(genClass.getMetaType());
    stringBuffer.append(TEXT_79);
    stringBuffer.append(genClass.getClassifierID());
    stringBuffer.append(TEXT_80);
    for (Iterator f=genClass.getGenFeatures().iterator(); f.hasNext();) { GenFeature genFeature = (GenFeature)f.next();
    stringBuffer.append(TEXT_81);
    stringBuffer.append(genFeature.getMetaType());
    stringBuffer.append(TEXT_82);
    stringBuffer.append(genClass.getClassifierInstanceName());
    stringBuffer.append(TEXT_83);
    stringBuffer.append(genClass.getFeatureID(genFeature));
    stringBuffer.append(TEXT_84);
    }
    if (c.hasNext()) {
    stringBuffer.append(TEXT_85);
    }
    }
    }
    if (!genPackage.getGenEnums().isEmpty()) {
    stringBuffer.append(TEXT_86);
    for (Iterator e=genPackage.getGenEnums().iterator(); e.hasNext();) { GenEnum genEnum = (GenEnum)e.next();
    stringBuffer.append(TEXT_87);
    stringBuffer.append(genEnum.getClassifierInstanceName());
    stringBuffer.append(TEXT_88);
    stringBuffer.append(genEnum.getClassifierID());
    stringBuffer.append(TEXT_89);
    }
    }
    if (!genPackage.getGenDataTypes().isEmpty()) {
    stringBuffer.append(TEXT_90);
    for (Iterator d=genPackage.getGenDataTypes().iterator(); d.hasNext();) { GenDataType genDataType = (GenDataType)d.next();
    stringBuffer.append(TEXT_91);
    stringBuffer.append(genDataType.getClassifierInstanceName());
    stringBuffer.append(TEXT_92);
    stringBuffer.append(genDataType.getClassifierID());
    stringBuffer.append(TEXT_93);
    }
    }
    stringBuffer.append(TEXT_94);
    if (!genPackage.getPackageInitializationDependencies().isEmpty()) {
    stringBuffer.append(TEXT_95);
    for (Iterator p=genPackage.getPackageInitializationDependencies().iterator(); p.hasNext();) { GenPackage dep = (GenPackage)p.next();
    stringBuffer.append(TEXT_96);
    stringBuffer.append(dep.getImportedPackageClassName());
    stringBuffer.append(TEXT_97);
    stringBuffer.append(genPackage.getPackageInstanceVariable(dep));
    stringBuffer.append(TEXT_98);
    stringBuffer.append(dep.getImportedPackageClassName());
    stringBuffer.append(TEXT_99);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.EPackage"));
    stringBuffer.append(TEXT_100);
    stringBuffer.append(dep.getImportedPackageInterfaceName());
    stringBuffer.append(TEXT_101);
    }
    }
    if (!genPackage.getSubGenPackages().isEmpty()) {
    stringBuffer.append(TEXT_102);
    for (Iterator p=genPackage.getSubGenPackages().iterator(); p.hasNext();) { GenPackage sub = (GenPackage)p.next();
    stringBuffer.append(TEXT_103);
    stringBuffer.append(genPackage.getPackageInstanceVariable(sub));
    stringBuffer.append(TEXT_104);
    }
    }
    if (!genPackage.getGenClasses().isEmpty()) { boolean firstOperationAssignment = true; 
    stringBuffer.append(TEXT_105);
    for (Iterator c=genPackage.getGenClasses().iterator(); c.hasNext();) { GenClass genClass = (GenClass)c.next();
    for (Iterator b=genClass.getBaseGenClasses().iterator(); b.hasNext();) { GenClass baseGenClass = (GenClass)b.next();
    stringBuffer.append(TEXT_106);
    stringBuffer.append(genClass.getClassifierInstanceName());
    stringBuffer.append(TEXT_107);
    stringBuffer.append(genPackage.getPackageInstanceVariable(baseGenClass.getGenPackage()));
    stringBuffer.append(TEXT_108);
    stringBuffer.append(baseGenClass.getClassifierAccessorName());
    stringBuffer.append(TEXT_109);
    }
    }
    stringBuffer.append(TEXT_110);
    for (Iterator c=genPackage.getGenClasses().iterator(); c.hasNext();) { GenClass genClass = (GenClass)c.next();
    stringBuffer.append(TEXT_111);
    stringBuffer.append(genClass.getClassifierInstanceName());
    stringBuffer.append(TEXT_112);
    stringBuffer.append(genClass.getImportedInterfaceName());
    stringBuffer.append(TEXT_113);
    stringBuffer.append(genClass.getName());
    stringBuffer.append(TEXT_114);
    stringBuffer.append(genClass.getAbstractFlag());
    stringBuffer.append(TEXT_115);
    stringBuffer.append(genClass.getInterfaceFlag());
    stringBuffer.append(TEXT_116);
    stringBuffer.append(genModel.getNonNLS());
    for (Iterator f=genClass.getGenFeatures().iterator(); f.hasNext();) { GenFeature genFeature = (GenFeature)f.next();
    if (genFeature.isReferenceType()) { GenFeature reverseGenFeature = genFeature.getReverse();
    String reverse = reverseGenFeature == null ? "null" : genPackage.getPackageInstanceVariable(reverseGenFeature.getGenPackage()) + ".get" + reverseGenFeature.getFeatureAccessorName() + "()";
    stringBuffer.append(TEXT_117);
    stringBuffer.append(genFeature.getFeatureAccessorName());
    stringBuffer.append(TEXT_118);
    stringBuffer.append(genPackage.getPackageInstanceVariable(genFeature.getTypeGenPackage()));
    stringBuffer.append(TEXT_119);
    stringBuffer.append(genFeature.getTypeClassifier());
    stringBuffer.append(TEXT_120);
    stringBuffer.append(reverse);
    stringBuffer.append(TEXT_121);
    stringBuffer.append(genFeature.getName());
    stringBuffer.append(TEXT_122);
    stringBuffer.append(genFeature.getDefaultValue());
    stringBuffer.append(TEXT_123);
    stringBuffer.append(genFeature.getLowerBound());
    stringBuffer.append(TEXT_124);
    stringBuffer.append(genFeature.getUpperBound());
    stringBuffer.append(TEXT_125);
    stringBuffer.append(genFeature.getTransientFlag());
    stringBuffer.append(TEXT_126);
    stringBuffer.append(genFeature.getVolatileFlag());
    stringBuffer.append(TEXT_127);
    stringBuffer.append(genFeature.getChangeableFlag());
    stringBuffer.append(TEXT_128);
    stringBuffer.append(genFeature.getContainmentFlag());
    stringBuffer.append(TEXT_129);
    stringBuffer.append(genFeature.getResolveProxiesFlag());
    stringBuffer.append(TEXT_130);
    stringBuffer.append(genFeature.getUnsettableFlag());
    stringBuffer.append(TEXT_131);
    stringBuffer.append(genFeature.getUniqueFlag());
    stringBuffer.append(TEXT_132);
    stringBuffer.append(genFeature.getDerivedFlag());
    stringBuffer.append(TEXT_133);
    stringBuffer.append(genFeature.getOrderedFlag());
    stringBuffer.append(TEXT_134);
    stringBuffer.append(genModel.getNonNLS());
    stringBuffer.append(genModel.getNonNLS(genFeature.getDefaultValue(), 2));
    } else {
    stringBuffer.append(TEXT_135);
    stringBuffer.append(genFeature.getFeatureAccessorName());
    stringBuffer.append(TEXT_136);
    stringBuffer.append(genPackage.getPackageInstanceVariable(genFeature.getTypeGenPackage()));
    stringBuffer.append(TEXT_137);
    stringBuffer.append(genFeature.getTypeClassifier());
    stringBuffer.append(TEXT_138);
    stringBuffer.append(genFeature.getName());
    stringBuffer.append(TEXT_139);
    stringBuffer.append(genFeature.getDefaultValue());
    stringBuffer.append(TEXT_140);
    stringBuffer.append(genFeature.getLowerBound());
    stringBuffer.append(TEXT_141);
    stringBuffer.append(genFeature.getUpperBound());
    stringBuffer.append(TEXT_142);
    stringBuffer.append(genFeature.getTransientFlag());
    stringBuffer.append(TEXT_143);
    stringBuffer.append(genFeature.getVolatileFlag());
    stringBuffer.append(TEXT_144);
    stringBuffer.append(genFeature.getChangeableFlag());
    stringBuffer.append(TEXT_145);
    stringBuffer.append(genFeature.getUnsettableFlag());
    stringBuffer.append(TEXT_146);
    stringBuffer.append(genFeature.getIDFlag());
    stringBuffer.append(TEXT_147);
    stringBuffer.append(genFeature.getUniqueFlag());
    stringBuffer.append(TEXT_148);
    stringBuffer.append(genFeature.getDerivedFlag());
    stringBuffer.append(TEXT_149);
    stringBuffer.append(genFeature.getOrderedFlag());
    stringBuffer.append(TEXT_150);
    stringBuffer.append(genModel.getNonNLS());
    stringBuffer.append(genModel.getNonNLS(genFeature.getDefaultValue(), 2));
    }
    }
    for (Iterator o=genClass.getGenOperations().iterator(); o.hasNext();) { GenOperation genOperation = (GenOperation)o.next(); String prefix = ""; if (!genOperation.getGenParameters().isEmpty() || !genOperation.getGenExceptions().isEmpty()) { if (firstOperationAssignment) { firstOperationAssignment = false; prefix = genModel.getImportedName("org.eclipse.emf.ecore.EOperation") + " op = "; } else { prefix = "op = "; }} 
    stringBuffer.append(TEXT_151);
    if (!genOperation.isVoid()) {
    stringBuffer.append(TEXT_152);
    stringBuffer.append(prefix);
    stringBuffer.append(TEXT_153);
    stringBuffer.append(genClass.getClassifierInstanceName());
    stringBuffer.append(TEXT_154);
    stringBuffer.append(genPackage.getPackageInstanceVariable(genOperation.getReturnTypeGenPackage()));
    stringBuffer.append(TEXT_155);
    stringBuffer.append(genOperation.getReturnTypeClassifier());
    stringBuffer.append(TEXT_156);
    stringBuffer.append(genOperation.getName());
    stringBuffer.append(TEXT_157);
    stringBuffer.append(genModel.getNonNLS());
    } else {
    stringBuffer.append(TEXT_158);
    stringBuffer.append(prefix);
    stringBuffer.append(TEXT_159);
    stringBuffer.append(genClass.getClassifierInstanceName());
    stringBuffer.append(TEXT_160);
    stringBuffer.append(genOperation.getName());
    stringBuffer.append(TEXT_161);
    stringBuffer.append(genModel.getNonNLS());
    }
    for (Iterator p=genOperation.getGenParameters().iterator(); p.hasNext();) { GenParameter genParameter = (GenParameter)p.next();
    stringBuffer.append(TEXT_162);
    stringBuffer.append(genPackage.getPackageInstanceVariable(genParameter.getTypeGenPackage()));
    stringBuffer.append(TEXT_163);
    stringBuffer.append(genParameter.getTypeClassifier());
    stringBuffer.append(TEXT_164);
    stringBuffer.append(genParameter.getName());
    stringBuffer.append(TEXT_165);
    stringBuffer.append(genModel.getNonNLS());
    }
    for (Iterator p=genOperation.getGenExceptions().iterator(); p.hasNext();) { GenClassifier genException = (GenClassifier)p.next();
    stringBuffer.append(TEXT_166);
    stringBuffer.append(genPackage.getPackageInstanceVariable(genException.getGenPackage()));
    stringBuffer.append(TEXT_167);
    stringBuffer.append(genException.getClassifierAccessorName());
    stringBuffer.append(TEXT_168);
    }
    }
    if (c.hasNext()) {
    stringBuffer.append(TEXT_169);
    }
    }
    }
    if (!genPackage.getGenEnums().isEmpty()) {
    stringBuffer.append(TEXT_170);
    for (Iterator e=genPackage.getGenEnums().iterator(); e.hasNext();) { GenEnum genEnum = (GenEnum)e.next();
    stringBuffer.append(TEXT_171);
    stringBuffer.append(genEnum.getClassifierInstanceName());
    stringBuffer.append(TEXT_172);
    stringBuffer.append(genEnum.getImportedName());
    stringBuffer.append(TEXT_173);
    stringBuffer.append(genEnum.getName());
    stringBuffer.append(TEXT_174);
    stringBuffer.append(genModel.getNonNLS());
    for (Iterator l=genEnum.getGenEnumLiterals().iterator(); l.hasNext();) { GenEnumLiteral genEnumLiteral = (GenEnumLiteral)l.next();
    stringBuffer.append(TEXT_175);
    stringBuffer.append(genEnum.getClassifierInstanceName());
    stringBuffer.append(TEXT_176);
    stringBuffer.append(genEnum.getImportedName().equals(genEnum.getClassifierID()) ? genEnum.getQualifiedName() : genEnum.getImportedName());
    stringBuffer.append(TEXT_177);
    stringBuffer.append(genEnumLiteral.getEnumLiteralID());
    stringBuffer.append(TEXT_178);
    }
    if (e.hasNext()) {
    stringBuffer.append(TEXT_179);
    }
    }
    }
    if (!genPackage.getGenDataTypes().isEmpty()) {
    stringBuffer.append(TEXT_180);
    for (Iterator d=genPackage.getGenDataTypes().iterator(); d.hasNext();) { GenDataType genDataType = (GenDataType)d.next();
    stringBuffer.append(TEXT_181);
    stringBuffer.append(genDataType.getClassifierInstanceName());
    stringBuffer.append(TEXT_182);
    stringBuffer.append(genDataType.getImportedInstanceClassName());
    stringBuffer.append(TEXT_183);
    stringBuffer.append(genDataType.getName());
    stringBuffer.append(TEXT_184);
    stringBuffer.append(genDataType.getSerializableFlag());
    stringBuffer.append(TEXT_185);
    stringBuffer.append(genModel.getNonNLS());
    }
    }
    if (genPackage.getSuperGenPackage() == null) {
    stringBuffer.append(TEXT_186);
    }
    if (!genPackage.getAnnotationSources().isEmpty()) {
    stringBuffer.append(TEXT_187);
    for (Iterator i = genPackage.getAnnotationSources().iterator(); i.hasNext();) { String annotationSource = (String)i.next();
    stringBuffer.append(TEXT_188);
    stringBuffer.append(annotationSource);
    stringBuffer.append(TEXT_189);
    stringBuffer.append(genPackage.getAnnotationSourceIdentifier(annotationSource));
    stringBuffer.append(TEXT_190);
    }
    }
    stringBuffer.append(TEXT_191);
    for (Iterator i = genPackage.getAnnotationSources().iterator(); i.hasNext();) { String annotationSource = (String)i.next();
    stringBuffer.append(TEXT_192);
    stringBuffer.append(annotationSource);
    stringBuffer.append(TEXT_193);
    stringBuffer.append(genPackage.getAnnotationSourceIdentifier(annotationSource));
    stringBuffer.append(TEXT_194);
    if (annotationSource == null) {
    stringBuffer.append(TEXT_195);
    } else {
    stringBuffer.append(TEXT_196);
    stringBuffer.append(annotationSource);
    stringBuffer.append(TEXT_197);
    stringBuffer.append(genModel.getNonNLS());
    }
    for (Iterator j = genPackage.getAllAnnotations().iterator(); j.hasNext();) { EAnnotation eAnnotation = (EAnnotation)j.next();
    stringBuffer.append(TEXT_198);
    if (annotationSource == null ? eAnnotation.getSource() == null : annotationSource.equals(eAnnotation.getSource())) {
    stringBuffer.append(TEXT_199);
    stringBuffer.append(genPackage.getAnnotatedModelElementAccessor(eAnnotation));
    stringBuffer.append(TEXT_200);
    for (Iterator k = eAnnotation.getDetails().iterator(); k.hasNext();) { Map.Entry detail = (Map.Entry)k.next(); String key = Literals.toStringLiteral((String)detail.getKey()); String value = Literals.toStringLiteral((String)detail.getValue());
    stringBuffer.append(TEXT_201);
    stringBuffer.append(key);
    stringBuffer.append(TEXT_202);
    stringBuffer.append(value);
    stringBuffer.append(k.hasNext() ? "," : "");
    stringBuffer.append(genModel.getNonNLS(key + value));
    }
    stringBuffer.append(TEXT_203);
    }
    }
    stringBuffer.append(TEXT_204);
    }
    stringBuffer.append(TEXT_205);
    stringBuffer.append(genPackage.getPackageClassName());
    genModel.emitSortedImports();
    stringBuffer.append(TEXT_206);
    return stringBuffer.toString();
  }
}
