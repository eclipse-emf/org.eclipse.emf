package org.eclipse.emf.codegen.ecore.templates.model;

import java.util.*;
import org.eclipse.emf.ecore.*;
import org.eclipse.emf.codegen.ecore.genmodel.*;
import org.eclipse.emf.codegen.ecore.genmodel.impl.Literals;

public class PackageClass
{
  protected static String nl;
  public static synchronized PackageClass create(String lineSeparator)
  {
    nl = lineSeparator;
    PackageClass result = new PackageClass();
    nl = null;
    return result;
  }

  protected final String NL = nl == null ? (System.getProperties().getProperty("line.separator")) : nl;
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
  protected final String TEXT_14 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */" + NL + "\tprotected String packageFilename = \"";
  protected final String TEXT_15 = "\";";
  protected final String TEXT_16 = NL;
  protected final String TEXT_17 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */" + NL + "\tprivate ";
  protected final String TEXT_18 = " ";
  protected final String TEXT_19 = " = null;" + NL;
  protected final String TEXT_20 = NL + "\t/**" + NL + "\t * Creates an instance of the model <b>Package</b>, registered with" + NL + "\t * {@link org.eclipse.emf.ecore.EPackage.Registry EPackage.Registry} by the package" + NL + "\t * package URI value." + NL + "\t * <p>Note: the correct way to create the package is via the static" + NL + "\t * factory method {@link #init init()}, which also performs" + NL + "\t * initialization of the package, or returns the registered package," + NL + "\t * if one already exists." + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @see org.eclipse.emf.ecore.EPackage.Registry" + NL + "\t * @see ";
  protected final String TEXT_21 = "#eNS_URI" + NL + "\t * @see #init()" + NL + "\t * @generated" + NL + "\t */" + NL + "\tprivate ";
  protected final String TEXT_22 = "()" + NL + "\t{" + NL + "\t\tsuper(eNS_URI, ";
  protected final String TEXT_23 = ".eINSTANCE);" + NL + "\t}" + NL + "" + NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */" + NL + "\tprivate static boolean isInited = false;" + NL + "" + NL + "\t/**" + NL + "\t * Creates, registers, and initializes the <b>Package</b> for this" + NL + "\t * model, and for any others upon which it depends.  Simple" + NL + "\t * dependencies are satisfied by calling this method on all" + NL + "\t * dependent packages before doing anything else.  This method drives" + NL + "\t * initialization for interdependent packages directly, in parallel" + NL + "\t * with this package, itself." + NL + "\t * <p>Of this package and its interdependencies, all packages which" + NL + "\t * have not yet been registered by their URI values are first created" + NL + "\t * and registered.  The packages are then initialized in two steps:" + NL + "\t * meta-model objects for all of the packages are created before any" + NL + "\t * are initialized, since one package's meta-model objects may refer to" + NL + "\t * those of another." + NL + "\t * <p>Invocation of this method will not affect any packages that have" + NL + "\t * already been initialized." + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @see #eNS_URI";
  protected final String TEXT_24 = NL + "\t * @see #createPackageContents()" + NL + "\t * @see #initializePackageContents()";
  protected final String TEXT_25 = NL + "\t * @generated" + NL + "\t */" + NL + "\tpublic static ";
  protected final String TEXT_26 = " init()" + NL + "\t{" + NL + "\t\tif (isInited) return (";
  protected final String TEXT_27 = ")";
  protected final String TEXT_28 = ".Registry.INSTANCE.getEPackage(";
  protected final String TEXT_29 = ".eNS_URI);" + NL + "" + NL + "\t\t// Obtain or create and register package" + NL + "\t\t";
  protected final String TEXT_30 = " the";
  protected final String TEXT_31 = " = (";
  protected final String TEXT_32 = ")(";
  protected final String TEXT_33 = ".Registry.INSTANCE.getEPackage(eNS_URI) instanceof ";
  protected final String TEXT_34 = " ? ";
  protected final String TEXT_35 = ".Registry.INSTANCE.getEPackage(eNS_URI) : new ";
  protected final String TEXT_36 = "());" + NL + "" + NL + "\t\tisInited = true;" + NL;
  protected final String TEXT_37 = NL + "\t\t// Initialize simple dependencies";
  protected final String TEXT_38 = NL + "\t\t";
  protected final String TEXT_39 = ".init();";
  protected final String TEXT_40 = NL;
  protected final String TEXT_41 = NL + "\t\t// Obtain or create and register interdependencies";
  protected final String TEXT_42 = NL + "\t\t";
  protected final String TEXT_43 = " ";
  protected final String TEXT_44 = " = (";
  protected final String TEXT_45 = ")(";
  protected final String TEXT_46 = ".Registry.INSTANCE.getEPackage(";
  protected final String TEXT_47 = ".eNS_URI) instanceof ";
  protected final String TEXT_48 = " ? ";
  protected final String TEXT_49 = ".Registry.INSTANCE.getEPackage(";
  protected final String TEXT_50 = ".eNS_URI) : ";
  protected final String TEXT_51 = ".eINSTANCE);";
  protected final String TEXT_52 = NL;
  protected final String TEXT_53 = NL + "\t\t// Load packages";
  protected final String TEXT_54 = NL + "\t\tthe";
  protected final String TEXT_55 = ".loadPackage();";
  protected final String TEXT_56 = NL + "\t\t";
  protected final String TEXT_57 = ".loadPackage();";
  protected final String TEXT_58 = NL;
  protected final String TEXT_59 = NL + "\t\t// Create package meta-data objects";
  protected final String TEXT_60 = NL + "\t\tthe";
  protected final String TEXT_61 = ".createPackageContents();";
  protected final String TEXT_62 = NL + "\t\t";
  protected final String TEXT_63 = ".createPackageContents();";
  protected final String TEXT_64 = NL + NL + "\t\t// Initialize created meta-data";
  protected final String TEXT_65 = NL + "\t\tthe";
  protected final String TEXT_66 = ".initializePackageContents();";
  protected final String TEXT_67 = NL + "\t\t";
  protected final String TEXT_68 = ".initializePackageContents();";
  protected final String TEXT_69 = NL;
  protected final String TEXT_70 = NL + "\t\t// Fix loaded packages";
  protected final String TEXT_71 = NL + "\t\tthe";
  protected final String TEXT_72 = ".fixPackageContents();";
  protected final String TEXT_73 = NL + "\t\t";
  protected final String TEXT_74 = ".fixPackageContents();";
  protected final String TEXT_75 = NL;
  protected final String TEXT_76 = NL + "\t\t// Register package validator" + NL + "\t\t";
  protected final String TEXT_77 = ".Registry.INSTANCE.put" + NL + "\t\t\t(the";
  protected final String TEXT_78 = ", " + NL + "\t\t\t new ";
  protected final String TEXT_79 = ".Descriptor()" + NL + "\t\t\t {" + NL + "\t\t\t\t public ";
  protected final String TEXT_80 = " getEValidator()" + NL + "\t\t\t\t {" + NL + "\t\t\t\t\t return ";
  protected final String TEXT_81 = ".INSTANCE;" + NL + "\t\t\t\t }" + NL + "\t\t\t });" + NL;
  protected final String TEXT_82 = NL + "\t\t// Mark meta-data to indicate it can't be changed" + NL + "\t\tthe";
  protected final String TEXT_83 = ".freeze();" + NL;
  protected final String TEXT_84 = NL + "\t\treturn the";
  protected final String TEXT_85 = ";" + NL + "\t}" + NL;
  protected final String TEXT_86 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */" + NL + "\tpublic ";
  protected final String TEXT_87 = " get";
  protected final String TEXT_88 = "()" + NL + "\t{";
  protected final String TEXT_89 = NL + "\t\tif (";
  protected final String TEXT_90 = " == null)" + NL + "\t\t{" + NL + "\t\t\t";
  protected final String TEXT_91 = " = (";
  protected final String TEXT_92 = ")";
  protected final String TEXT_93 = ".Registry.INSTANCE.getEPackage(";
  protected final String TEXT_94 = ".eNS_URI).getEClassifiers().get(";
  protected final String TEXT_95 = ");" + NL + "\t\t}";
  protected final String TEXT_96 = NL + "\t\treturn ";
  protected final String TEXT_97 = ";" + NL + "\t}" + NL;
  protected final String TEXT_98 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */" + NL + "\tpublic ";
  protected final String TEXT_99 = " get";
  protected final String TEXT_100 = "()" + NL + "\t{";
  protected final String TEXT_101 = NL + "\t\treturn (";
  protected final String TEXT_102 = ")";
  protected final String TEXT_103 = ".getEStructuralFeatures().get(";
  protected final String TEXT_104 = ");";
  protected final String TEXT_105 = NL + "        return (";
  protected final String TEXT_106 = ")get";
  protected final String TEXT_107 = "().getEStructuralFeatures().get(";
  protected final String TEXT_108 = ");";
  protected final String TEXT_109 = NL + "\t}" + NL;
  protected final String TEXT_110 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */" + NL + "\tpublic ";
  protected final String TEXT_111 = " get";
  protected final String TEXT_112 = "()" + NL + "\t{" + NL + "\t\treturn (";
  protected final String TEXT_113 = ")getEFactoryInstance();" + NL + "\t}" + NL;
  protected final String TEXT_114 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */" + NL + "\tprivate boolean isCreated = false;" + NL + "" + NL + "\t/**" + NL + "\t * Creates the meta-model objects for the package.  This method is" + NL + "\t * guarded to have no affect on any invocation but its first." + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */" + NL + "\tpublic void createPackageContents()" + NL + "\t{" + NL + "\t\tif (isCreated) return;" + NL + "\t\tisCreated = true;";
  protected final String TEXT_115 = NL + NL + "\t\t// Create classes and their features";
  protected final String TEXT_116 = NL + "\t\t";
  protected final String TEXT_117 = " = create";
  protected final String TEXT_118 = "(";
  protected final String TEXT_119 = ");";
  protected final String TEXT_120 = NL + "\t\tcreate";
  protected final String TEXT_121 = "(";
  protected final String TEXT_122 = ", ";
  protected final String TEXT_123 = ");";
  protected final String TEXT_124 = NL;
  protected final String TEXT_125 = NL + NL + "\t\t// Create enums";
  protected final String TEXT_126 = NL + "\t\t";
  protected final String TEXT_127 = " = createEEnum(";
  protected final String TEXT_128 = ");";
  protected final String TEXT_129 = NL + NL + "\t\t// Create data types";
  protected final String TEXT_130 = NL + "\t\t";
  protected final String TEXT_131 = " = createEDataType(";
  protected final String TEXT_132 = ");";
  protected final String TEXT_133 = NL + "\t}" + NL + "" + NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */" + NL + "\tprivate boolean isInitialized = false;" + NL + "" + NL + "\t/**" + NL + "\t * Complete the initialization of the package and its meta-model.  This" + NL + "\t * method is guarded to have no affect on any invocation but its first." + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */" + NL + "\tpublic void initializePackageContents()" + NL + "\t{" + NL + "\t\tif (isInitialized) return;" + NL + "\t\tisInitialized = true;" + NL + "" + NL + "\t\t// Initialize package" + NL + "\t\tsetName(eNAME);" + NL + "\t\tsetNsPrefix(eNS_PREFIX);" + NL + "\t\tsetNsURI(eNS_URI);";
  protected final String TEXT_134 = NL + NL + "\t\t// Obtain other dependent packages";
  protected final String TEXT_135 = NL + "\t\t";
  protected final String TEXT_136 = " ";
  protected final String TEXT_137 = " = (";
  protected final String TEXT_138 = ")";
  protected final String TEXT_139 = ".Registry.INSTANCE.getEPackage(";
  protected final String TEXT_140 = ".eNS_URI);";
  protected final String TEXT_141 = NL + NL + "\t\t// Add subpackages";
  protected final String TEXT_142 = NL + "\t\tgetESubpackages().add(";
  protected final String TEXT_143 = ");";
  protected final String TEXT_144 = NL + NL + "\t\t// Add supertypes to classes";
  protected final String TEXT_145 = NL + "\t\t";
  protected final String TEXT_146 = ".getESuperTypes().add(";
  protected final String TEXT_147 = ".get";
  protected final String TEXT_148 = "());";
  protected final String TEXT_149 = NL + NL + "\t\t// Initialize classes and features; add operations and parameters";
  protected final String TEXT_150 = NL + "\t\tinitEClass(";
  protected final String TEXT_151 = ", ";
  protected final String TEXT_152 = ".class, \"";
  protected final String TEXT_153 = "\", ";
  protected final String TEXT_154 = ", ";
  protected final String TEXT_155 = ", ";
  protected final String TEXT_156 = ");";
  protected final String TEXT_157 = NL + "\t\tinitEReference(get";
  protected final String TEXT_158 = "(), ";
  protected final String TEXT_159 = ".get";
  protected final String TEXT_160 = "(), ";
  protected final String TEXT_161 = ", \"";
  protected final String TEXT_162 = "\", ";
  protected final String TEXT_163 = ", ";
  protected final String TEXT_164 = ", ";
  protected final String TEXT_165 = ", ";
  protected final String TEXT_166 = ", ";
  protected final String TEXT_167 = ", ";
  protected final String TEXT_168 = ", ";
  protected final String TEXT_169 = ", ";
  protected final String TEXT_170 = ", ";
  protected final String TEXT_171 = ", ";
  protected final String TEXT_172 = ", ";
  protected final String TEXT_173 = ", ";
  protected final String TEXT_174 = ", ";
  protected final String TEXT_175 = ");";
  protected final String TEXT_176 = NL + "\t\tinitEAttribute(get";
  protected final String TEXT_177 = "(), ";
  protected final String TEXT_178 = ".get";
  protected final String TEXT_179 = "(), \"";
  protected final String TEXT_180 = "\", ";
  protected final String TEXT_181 = ", ";
  protected final String TEXT_182 = ", ";
  protected final String TEXT_183 = ", ";
  protected final String TEXT_184 = ", ";
  protected final String TEXT_185 = ", ";
  protected final String TEXT_186 = ", ";
  protected final String TEXT_187 = ", ";
  protected final String TEXT_188 = ", ";
  protected final String TEXT_189 = ", ";
  protected final String TEXT_190 = ", ";
  protected final String TEXT_191 = ", ";
  protected final String TEXT_192 = ");";
  protected final String TEXT_193 = NL;
  protected final String TEXT_194 = NL + "\t\t";
  protected final String TEXT_195 = "addEOperation(";
  protected final String TEXT_196 = ", ";
  protected final String TEXT_197 = ".get";
  protected final String TEXT_198 = "(), \"";
  protected final String TEXT_199 = "\");";
  protected final String TEXT_200 = NL + "\t\t";
  protected final String TEXT_201 = "addEOperation(";
  protected final String TEXT_202 = ", null, \"";
  protected final String TEXT_203 = "\");";
  protected final String TEXT_204 = NL + "\t\taddEParameter(op, ";
  protected final String TEXT_205 = ".get";
  protected final String TEXT_206 = "(), \"";
  protected final String TEXT_207 = "\");";
  protected final String TEXT_208 = NL + "\t\taddEException(op, ";
  protected final String TEXT_209 = ".get";
  protected final String TEXT_210 = "());";
  protected final String TEXT_211 = NL;
  protected final String TEXT_212 = NL + NL + "\t\t// Initialize enums and add enum literals";
  protected final String TEXT_213 = NL + "\t\tinitEEnum(";
  protected final String TEXT_214 = ", ";
  protected final String TEXT_215 = ".class, \"";
  protected final String TEXT_216 = "\");";
  protected final String TEXT_217 = NL + "\t\taddEEnumLiteral(";
  protected final String TEXT_218 = ", ";
  protected final String TEXT_219 = ".";
  protected final String TEXT_220 = "_LITERAL);";
  protected final String TEXT_221 = NL;
  protected final String TEXT_222 = NL + NL + "\t\t// Initialize data types";
  protected final String TEXT_223 = NL + "\t\tinitEDataType(";
  protected final String TEXT_224 = ", ";
  protected final String TEXT_225 = ".class, \"";
  protected final String TEXT_226 = "\", ";
  protected final String TEXT_227 = ", ";
  protected final String TEXT_228 = ");";
  protected final String TEXT_229 = NL + NL + "\t\t// Create resource" + NL + "\t\tcreateResource(eNS_URI);";
  protected final String TEXT_230 = NL + NL + "\t\t// Create annotations";
  protected final String TEXT_231 = NL + "\t\t// ";
  protected final String TEXT_232 = NL + "\t\tcreate";
  protected final String TEXT_233 = "Annotations();";
  protected final String TEXT_234 = NL + "\t}" + NL;
  protected final String TEXT_235 = NL + "\t/**" + NL + "\t * Initializes the annotations for <b>";
  protected final String TEXT_236 = "</b>." + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */" + NL + "\tprotected void create";
  protected final String TEXT_237 = "Annotations()" + NL + "\t{" + NL + "\t\tString source = ";
  protected final String TEXT_238 = "null;";
  protected final String TEXT_239 = "\"";
  protected final String TEXT_240 = "\";";
  protected final String TEXT_241 = "\t";
  protected final String TEXT_242 = "\t" + NL + "\t\taddAnnotation" + NL + "\t\t  (";
  protected final String TEXT_243 = ", " + NL + "\t\t   source, " + NL + "\t\t   new String[] " + NL + "\t\t   {";
  protected final String TEXT_244 = NL + "\t\t\t ";
  protected final String TEXT_245 = ", ";
  protected final String TEXT_246 = NL + "\t\t   });";
  protected final String TEXT_247 = NL + "\t}" + NL;
  protected final String TEXT_248 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */" + NL + "\tprivate boolean isLoaded = false;" + NL + "" + NL + "\t/**" + NL + "\t * Laods the package and any sub-packages from their serialized form." + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */" + NL + "\tpublic void loadPackage()" + NL + "\t{" + NL + "\t\tif (isLoaded) return;" + NL + "\t\tisLoaded = true;" + NL + "" + NL + "\t\t";
  protected final String TEXT_249 = " url = getClass().getResource(packageFilename);" + NL + "\t\tif (url == null)" + NL + "\t\t{" + NL + "\t\t\tthrow new RuntimeException(\"Missing serialized package: \" + packageFilename);";
  protected final String TEXT_250 = NL + "\t\t}" + NL + "\t\t";
  protected final String TEXT_251 = " uri = ";
  protected final String TEXT_252 = ".createURI(url.toString());" + NL + "\t\t";
  protected final String TEXT_253 = " resource = new ";
  protected final String TEXT_254 = "().createResource(";
  protected final String TEXT_255 = ".createURI(url.toString()));" + NL + "\t\ttry" + NL + "\t\t{" + NL + "\t\t\tresource.load(null);" + NL + "\t\t}" + NL + "\t\tcatch (";
  protected final String TEXT_256 = " exception)" + NL + "\t\t{" + NL + "\t\t\tthrow new ";
  protected final String TEXT_257 = "(exception);" + NL + "\t\t}" + NL + "\t\tinitializeFromLoadedEPackage(this, (";
  protected final String TEXT_258 = ")resource.getContents().get(0));" + NL + "\t\tcreateResource(eNS_URI);" + NL + "\t}" + NL;
  protected final String TEXT_259 = NL + NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */" + NL + "\tprivate boolean isFixed = false;" + NL + "" + NL + "\t/**" + NL + "\t * Fixes up the loaded package, to make it appear as if it had been programmatically built." + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */" + NL + "\tpublic void fixPackageContents()" + NL + "\t{" + NL + "\t\tif (isFixed) return;" + NL + "\t\tisFixed = true;" + NL + "\t\tfixEClassifiers();" + NL + "\t}";
  protected final String TEXT_260 = NL + "} //";
  protected final String TEXT_261 = NL;

  public String generate(Object argument)
  {
    StringBuffer stringBuffer = new StringBuffer();
    
/**
 * <copyright>
 *
 * Copyright (c) 2002-2004 IBM Corporation and others.
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
    genModel.markImportLocation(stringBuffer);
    genModel.addPseudoImport("org.eclipse.emf.ecore.EPackage.Registry");
    genModel.addPseudoImport("org.eclipse.emf.ecore.EPackage.Descriptor");
    for (Iterator i=genPackage.getOrderedGenClassifiers().iterator(); i.hasNext();) genModel.addPseudoImport(genPackage.getQualifiedPackageInterfaceName() + "." + genPackage.getClassifierID((GenClassifier)i.next()));
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
    if (genPackage.isLoadingInitialization()) {
    stringBuffer.append(TEXT_14);
    stringBuffer.append(genPackage.getSerializedPackageFilename());
    stringBuffer.append(TEXT_15);
    stringBuffer.append(genModel.getNonNLS());
    stringBuffer.append(TEXT_16);
    }
    for (Iterator i=genPackage.getGenClassifiers().iterator(); i.hasNext();) { GenClassifier genClassifier = (GenClassifier)i.next();
    stringBuffer.append(TEXT_17);
    stringBuffer.append(genClassifier.getImportedMetaType());
    stringBuffer.append(TEXT_18);
    stringBuffer.append(genClassifier.getClassifierInstanceName());
    stringBuffer.append(TEXT_19);
    }
    stringBuffer.append(TEXT_20);
    stringBuffer.append(genPackage.getQualifiedPackageInterfaceName());
    stringBuffer.append(TEXT_21);
    stringBuffer.append(genPackage.getPackageClassName());
    stringBuffer.append(TEXT_22);
    stringBuffer.append(genPackage.getImportedFactoryInterfaceName());
    stringBuffer.append(TEXT_23);
    if (!genPackage.isLoadedInitialization()) {
    stringBuffer.append(TEXT_24);
    }
    stringBuffer.append(TEXT_25);
    stringBuffer.append(genPackage.getImportedPackageInterfaceName());
    stringBuffer.append(TEXT_26);
    stringBuffer.append(genPackage.getImportedPackageInterfaceName());
    stringBuffer.append(TEXT_27);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.EPackage"));
    stringBuffer.append(TEXT_28);
    stringBuffer.append(genPackage.getImportedPackageInterfaceName());
    stringBuffer.append(TEXT_29);
    stringBuffer.append(genPackage.getPackageClassName());
    stringBuffer.append(TEXT_30);
    stringBuffer.append(genPackage.getPackageInterfaceName());
    stringBuffer.append(TEXT_31);
    stringBuffer.append(genPackage.getPackageClassName());
    stringBuffer.append(TEXT_32);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.EPackage"));
    stringBuffer.append(TEXT_33);
    stringBuffer.append(genPackage.getPackageClassName());
    stringBuffer.append(TEXT_34);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.EPackage"));
    stringBuffer.append(TEXT_35);
    stringBuffer.append(genPackage.getPackageClassName());
    stringBuffer.append(TEXT_36);
    if (!genPackage.getPackageSimpleDependencies().isEmpty()) {
    stringBuffer.append(TEXT_37);
    for (Iterator p=genPackage.getPackageSimpleDependencies().iterator(); p.hasNext();) { GenPackage dep = (GenPackage)p.next();
    stringBuffer.append(TEXT_38);
    stringBuffer.append(dep.getImportedPackageClassName());
    stringBuffer.append(TEXT_39);
    }
    stringBuffer.append(TEXT_40);
    }
    if (!genPackage.getPackageInterDependencies().isEmpty()) {
    stringBuffer.append(TEXT_41);
    for (Iterator p=genPackage.getPackageInterDependencies().iterator(); p.hasNext();) { GenPackage interdep = (GenPackage)p.next();
    stringBuffer.append(TEXT_42);
    stringBuffer.append(interdep.getImportedPackageClassName());
    stringBuffer.append(TEXT_43);
    stringBuffer.append(genPackage.getPackageInstanceVariable(interdep));
    stringBuffer.append(TEXT_44);
    stringBuffer.append(interdep.getImportedPackageClassName());
    stringBuffer.append(TEXT_45);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.EPackage"));
    stringBuffer.append(TEXT_46);
    stringBuffer.append(interdep.getImportedPackageInterfaceName());
    stringBuffer.append(TEXT_47);
    stringBuffer.append(interdep.getImportedPackageClassName());
    stringBuffer.append(TEXT_48);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.EPackage"));
    stringBuffer.append(TEXT_49);
    stringBuffer.append(interdep.getImportedPackageInterfaceName());
    stringBuffer.append(TEXT_50);
    stringBuffer.append(interdep.getImportedPackageInterfaceName());
    stringBuffer.append(TEXT_51);
    }
    stringBuffer.append(TEXT_52);
    }
    if (genPackage.isLoadedInitialization() || !genPackage.getPackageLoadInterDependencies().isEmpty()) {
    stringBuffer.append(TEXT_53);
    if (genPackage.isLoadingInitialization()) {
    stringBuffer.append(TEXT_54);
    stringBuffer.append(genPackage.getPackageInterfaceName());
    stringBuffer.append(TEXT_55);
    }
    for (Iterator p=genPackage.getPackageLoadInterDependencies().iterator(); p.hasNext();) { GenPackage interdep = (GenPackage)p.next();
    if (interdep.isLoadingInitialization()) {
    stringBuffer.append(TEXT_56);
    stringBuffer.append(genPackage.getPackageInstanceVariable(interdep));
    stringBuffer.append(TEXT_57);
    }
    }
    stringBuffer.append(TEXT_58);
    }
    if (!genPackage.isLoadedInitialization() || !genPackage.getPackageBuildInterDependencies().isEmpty()) {
    stringBuffer.append(TEXT_59);
    if (!genPackage.isLoadedInitialization()) {
    stringBuffer.append(TEXT_60);
    stringBuffer.append(genPackage.getPackageInterfaceName());
    stringBuffer.append(TEXT_61);
    }
    for (Iterator p=genPackage.getPackageBuildInterDependencies().iterator(); p.hasNext();) { GenPackage interdep = (GenPackage)p.next();
    stringBuffer.append(TEXT_62);
    stringBuffer.append(genPackage.getPackageInstanceVariable(interdep));
    stringBuffer.append(TEXT_63);
    }
    stringBuffer.append(TEXT_64);
    if (!genPackage.isLoadedInitialization()) {
    stringBuffer.append(TEXT_65);
    stringBuffer.append(genPackage.getPackageInterfaceName());
    stringBuffer.append(TEXT_66);
    }
    for (Iterator p=genPackage.getPackageBuildInterDependencies().iterator(); p.hasNext();) { GenPackage interdep = (GenPackage)p.next();
    stringBuffer.append(TEXT_67);
    stringBuffer.append(genPackage.getPackageInstanceVariable(interdep));
    stringBuffer.append(TEXT_68);
    }
    stringBuffer.append(TEXT_69);
    }
    if (genPackage.isLoadedInitialization() || !genPackage.getPackageLoadInterDependencies().isEmpty()) {
    stringBuffer.append(TEXT_70);
    if (genPackage.isLoadedInitialization()) {
    stringBuffer.append(TEXT_71);
    stringBuffer.append(genPackage.getPackageInterfaceName());
    stringBuffer.append(TEXT_72);
    }
    for (Iterator p=genPackage.getPackageLoadInterDependencies().iterator(); p.hasNext();) { GenPackage interdep = (GenPackage)p.next();
    stringBuffer.append(TEXT_73);
    stringBuffer.append(genPackage.getPackageInstanceVariable(interdep));
    stringBuffer.append(TEXT_74);
    }
    stringBuffer.append(TEXT_75);
    }
    if (genPackage.hasConstraints()) {
    stringBuffer.append(TEXT_76);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.EValidator"));
    stringBuffer.append(TEXT_77);
    stringBuffer.append(genPackage.getPackageInterfaceName());
    stringBuffer.append(TEXT_78);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.EValidator"));
    stringBuffer.append(TEXT_79);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.EValidator"));
    stringBuffer.append(TEXT_80);
    stringBuffer.append(genPackage.getImportedValidatorClassName());
    stringBuffer.append(TEXT_81);
    }
    if (!genPackage.isEcorePackage()) {
    stringBuffer.append(TEXT_82);
    stringBuffer.append(genPackage.getPackageInterfaceName());
    stringBuffer.append(TEXT_83);
    }
    stringBuffer.append(TEXT_84);
    stringBuffer.append(genPackage.getPackageInterfaceName());
    stringBuffer.append(TEXT_85);
    for (Iterator m=genPackage.getGenClassifiers().iterator(); m.hasNext();) { GenClassifier genClassifier = (GenClassifier)m.next();
    stringBuffer.append(TEXT_86);
    stringBuffer.append(genClassifier.getImportedMetaType());
    stringBuffer.append(TEXT_87);
    stringBuffer.append(genClassifier.getClassifierAccessorName());
    stringBuffer.append(TEXT_88);
    if (genPackage.isLoadedInitialization()) {
    stringBuffer.append(TEXT_89);
    stringBuffer.append(genClassifier.getClassifierInstanceName());
    stringBuffer.append(TEXT_90);
    stringBuffer.append(genClassifier.getClassifierInstanceName());
    stringBuffer.append(TEXT_91);
    stringBuffer.append(genClassifier.getImportedMetaType());
    stringBuffer.append(TEXT_92);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.EPackage"));
    stringBuffer.append(TEXT_93);
    stringBuffer.append(genPackage.getImportedPackageInterfaceName());
    stringBuffer.append(TEXT_94);
    stringBuffer.append(genPackage.getLocalClassifierIndex(genClassifier));
    stringBuffer.append(TEXT_95);
    }
    stringBuffer.append(TEXT_96);
    stringBuffer.append(genClassifier.getClassifierInstanceName());
    stringBuffer.append(TEXT_97);
    if (genClassifier instanceof GenClass) { GenClass genClass = (GenClass)genClassifier;
    for (Iterator f=genClass.getGenFeatures().iterator(); f.hasNext();) { GenFeature genFeature = (GenFeature)f.next();
    stringBuffer.append(TEXT_98);
    stringBuffer.append(genFeature.getImportedMetaType());
    stringBuffer.append(TEXT_99);
    stringBuffer.append(genFeature.getFeatureAccessorName());
    stringBuffer.append(TEXT_100);
    if (!genPackage.isLoadedInitialization()) {
    stringBuffer.append(TEXT_101);
    stringBuffer.append(genFeature.getImportedMetaType());
    stringBuffer.append(TEXT_102);
    stringBuffer.append(genClass.getClassifierInstanceName());
    stringBuffer.append(TEXT_103);
    stringBuffer.append(genClass.getLocalFeatureIndex(genFeature));
    stringBuffer.append(TEXT_104);
    } else {
    stringBuffer.append(TEXT_105);
    stringBuffer.append(genFeature.getImportedMetaType());
    stringBuffer.append(TEXT_106);
    stringBuffer.append(genClassifier.getClassifierAccessorName());
    stringBuffer.append(TEXT_107);
    stringBuffer.append(genClass.getLocalFeatureIndex(genFeature));
    stringBuffer.append(TEXT_108);
    }
    stringBuffer.append(TEXT_109);
    }
    }
    }
    stringBuffer.append(TEXT_110);
    stringBuffer.append(genPackage.getImportedFactoryInterfaceName());
    stringBuffer.append(TEXT_111);
    stringBuffer.append(genPackage.getFactoryInterfaceName());
    stringBuffer.append(TEXT_112);
    stringBuffer.append(genPackage.getImportedFactoryInterfaceName());
    stringBuffer.append(TEXT_113);
    if (!genPackage.isLoadedInitialization()) {
    stringBuffer.append(TEXT_114);
    if (!genPackage.getGenClasses().isEmpty()) {
    stringBuffer.append(TEXT_115);
    for (Iterator c=genPackage.getGenClasses().iterator(); c.hasNext();) { GenClass genClass = (GenClass)c.next();
    stringBuffer.append(TEXT_116);
    stringBuffer.append(genClass.getClassifierInstanceName());
    stringBuffer.append(TEXT_117);
    stringBuffer.append(genClass.getMetaType());
    stringBuffer.append(TEXT_118);
    stringBuffer.append(genClass.getClassifierID());
    stringBuffer.append(TEXT_119);
    for (Iterator f=genClass.getGenFeatures().iterator(); f.hasNext();) { GenFeature genFeature = (GenFeature)f.next();
    stringBuffer.append(TEXT_120);
    stringBuffer.append(genFeature.getMetaType());
    stringBuffer.append(TEXT_121);
    stringBuffer.append(genClass.getClassifierInstanceName());
    stringBuffer.append(TEXT_122);
    stringBuffer.append(genClass.getFeatureID(genFeature));
    stringBuffer.append(TEXT_123);
    }
    if (c.hasNext()) {
    stringBuffer.append(TEXT_124);
    }
    }
    }
    if (!genPackage.getGenEnums().isEmpty()) {
    stringBuffer.append(TEXT_125);
    for (Iterator e=genPackage.getGenEnums().iterator(); e.hasNext();) { GenEnum genEnum = (GenEnum)e.next();
    stringBuffer.append(TEXT_126);
    stringBuffer.append(genEnum.getClassifierInstanceName());
    stringBuffer.append(TEXT_127);
    stringBuffer.append(genEnum.getClassifierID());
    stringBuffer.append(TEXT_128);
    }
    }
    if (!genPackage.getGenDataTypes().isEmpty()) {
    stringBuffer.append(TEXT_129);
    for (Iterator d=genPackage.getGenDataTypes().iterator(); d.hasNext();) { GenDataType genDataType = (GenDataType)d.next();
    stringBuffer.append(TEXT_130);
    stringBuffer.append(genDataType.getClassifierInstanceName());
    stringBuffer.append(TEXT_131);
    stringBuffer.append(genDataType.getClassifierID());
    stringBuffer.append(TEXT_132);
    }
    }
    stringBuffer.append(TEXT_133);
    if (!genPackage.getPackageInitializationDependencies().isEmpty()) {
    stringBuffer.append(TEXT_134);
    for (Iterator p=genPackage.getPackageInitializationDependencies().iterator(); p.hasNext();) { GenPackage dep = (GenPackage)p.next();
    stringBuffer.append(TEXT_135);
    stringBuffer.append(dep.getImportedPackageClassName());
    stringBuffer.append(TEXT_136);
    stringBuffer.append(genPackage.getPackageInstanceVariable(dep));
    stringBuffer.append(TEXT_137);
    stringBuffer.append(dep.getImportedPackageClassName());
    stringBuffer.append(TEXT_138);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.EPackage"));
    stringBuffer.append(TEXT_139);
    stringBuffer.append(dep.getImportedPackageInterfaceName());
    stringBuffer.append(TEXT_140);
    }
    }
    if (!genPackage.getSubGenPackages().isEmpty()) {
    stringBuffer.append(TEXT_141);
    for (Iterator p=genPackage.getSubGenPackages().iterator(); p.hasNext();) { GenPackage sub = (GenPackage)p.next();
    stringBuffer.append(TEXT_142);
    stringBuffer.append(genPackage.getPackageInstanceVariable(sub));
    stringBuffer.append(TEXT_143);
    }
    }
    if (!genPackage.getGenClasses().isEmpty()) { boolean firstOperationAssignment = true; 
    stringBuffer.append(TEXT_144);
    for (Iterator c=genPackage.getGenClasses().iterator(); c.hasNext();) { GenClass genClass = (GenClass)c.next();
    for (Iterator b=genClass.getBaseGenClasses().iterator(); b.hasNext();) { GenClass baseGenClass = (GenClass)b.next();
    stringBuffer.append(TEXT_145);
    stringBuffer.append(genClass.getClassifierInstanceName());
    stringBuffer.append(TEXT_146);
    stringBuffer.append(genPackage.getPackageInstanceVariable(baseGenClass.getGenPackage()));
    stringBuffer.append(TEXT_147);
    stringBuffer.append(baseGenClass.getClassifierAccessorName());
    stringBuffer.append(TEXT_148);
    }
    }
    stringBuffer.append(TEXT_149);
    for (Iterator c=genPackage.getGenClasses().iterator(); c.hasNext();) { GenClass genClass = (GenClass)c.next();
    stringBuffer.append(TEXT_150);
    stringBuffer.append(genClass.getClassifierInstanceName());
    stringBuffer.append(TEXT_151);
    stringBuffer.append(genClass.getImportedInterfaceName());
    stringBuffer.append(TEXT_152);
    stringBuffer.append(genClass.getName());
    stringBuffer.append(TEXT_153);
    stringBuffer.append(genClass.getAbstractFlag());
    stringBuffer.append(TEXT_154);
    stringBuffer.append(genClass.getInterfaceFlag());
    stringBuffer.append(TEXT_155);
    stringBuffer.append(genClass.getGeneratedInstanceClassFlag());
    stringBuffer.append(TEXT_156);
    stringBuffer.append(genModel.getNonNLS());
    for (Iterator f=genClass.getGenFeatures().iterator(); f.hasNext();) { GenFeature genFeature = (GenFeature)f.next();
    if (genFeature.isReferenceType()) { GenFeature reverseGenFeature = genFeature.getReverse();
    String reverse = reverseGenFeature == null ? "null" : genPackage.getPackageInstanceVariable(reverseGenFeature.getGenPackage()) + ".get" + reverseGenFeature.getFeatureAccessorName() + "()";
    stringBuffer.append(TEXT_157);
    stringBuffer.append(genFeature.getFeatureAccessorName());
    stringBuffer.append(TEXT_158);
    stringBuffer.append(genPackage.getPackageInstanceVariable(genFeature.getTypeGenPackage()));
    stringBuffer.append(TEXT_159);
    stringBuffer.append(genFeature.getTypeClassifierAccessorName());
    stringBuffer.append(TEXT_160);
    stringBuffer.append(reverse);
    stringBuffer.append(TEXT_161);
    stringBuffer.append(genFeature.getName());
    stringBuffer.append(TEXT_162);
    stringBuffer.append(genFeature.getDefaultValue());
    stringBuffer.append(TEXT_163);
    stringBuffer.append(genFeature.getLowerBound());
    stringBuffer.append(TEXT_164);
    stringBuffer.append(genFeature.getUpperBound());
    stringBuffer.append(TEXT_165);
    stringBuffer.append(genFeature.getContainerClass());
    stringBuffer.append(TEXT_166);
    stringBuffer.append(genFeature.getTransientFlag());
    stringBuffer.append(TEXT_167);
    stringBuffer.append(genFeature.getVolatileFlag());
    stringBuffer.append(TEXT_168);
    stringBuffer.append(genFeature.getChangeableFlag());
    stringBuffer.append(TEXT_169);
    stringBuffer.append(genFeature.getContainmentFlag());
    stringBuffer.append(TEXT_170);
    stringBuffer.append(genFeature.getResolveProxiesFlag());
    stringBuffer.append(TEXT_171);
    stringBuffer.append(genFeature.getUnsettableFlag());
    stringBuffer.append(TEXT_172);
    stringBuffer.append(genFeature.getUniqueFlag());
    stringBuffer.append(TEXT_173);
    stringBuffer.append(genFeature.getDerivedFlag());
    stringBuffer.append(TEXT_174);
    stringBuffer.append(genFeature.getOrderedFlag());
    stringBuffer.append(TEXT_175);
    stringBuffer.append(genModel.getNonNLS());
    stringBuffer.append(genModel.getNonNLS(genFeature.getDefaultValue(), 2));
    } else {
    stringBuffer.append(TEXT_176);
    stringBuffer.append(genFeature.getFeatureAccessorName());
    stringBuffer.append(TEXT_177);
    stringBuffer.append(genPackage.getPackageInstanceVariable(genFeature.getTypeGenPackage()));
    stringBuffer.append(TEXT_178);
    stringBuffer.append(genFeature.getTypeClassifierAccessorName());
    stringBuffer.append(TEXT_179);
    stringBuffer.append(genFeature.getName());
    stringBuffer.append(TEXT_180);
    stringBuffer.append(genFeature.getDefaultValue());
    stringBuffer.append(TEXT_181);
    stringBuffer.append(genFeature.getLowerBound());
    stringBuffer.append(TEXT_182);
    stringBuffer.append(genFeature.getUpperBound());
    stringBuffer.append(TEXT_183);
    stringBuffer.append(genFeature.getContainerClass());
    stringBuffer.append(TEXT_184);
    stringBuffer.append(genFeature.getTransientFlag());
    stringBuffer.append(TEXT_185);
    stringBuffer.append(genFeature.getVolatileFlag());
    stringBuffer.append(TEXT_186);
    stringBuffer.append(genFeature.getChangeableFlag());
    stringBuffer.append(TEXT_187);
    stringBuffer.append(genFeature.getUnsettableFlag());
    stringBuffer.append(TEXT_188);
    stringBuffer.append(genFeature.getIDFlag());
    stringBuffer.append(TEXT_189);
    stringBuffer.append(genFeature.getUniqueFlag());
    stringBuffer.append(TEXT_190);
    stringBuffer.append(genFeature.getDerivedFlag());
    stringBuffer.append(TEXT_191);
    stringBuffer.append(genFeature.getOrderedFlag());
    stringBuffer.append(TEXT_192);
    stringBuffer.append(genModel.getNonNLS());
    stringBuffer.append(genModel.getNonNLS(genFeature.getDefaultValue(), 2));
    }
    }
    for (Iterator o=genClass.getGenOperations().iterator(); o.hasNext();) { GenOperation genOperation = (GenOperation)o.next(); String prefix = ""; if (!genOperation.getGenParameters().isEmpty() || !genOperation.getGenExceptions().isEmpty()) { if (firstOperationAssignment) { firstOperationAssignment = false; prefix = genModel.getImportedName("org.eclipse.emf.ecore.EOperation") + " op = "; } else { prefix = "op = "; }} 
    stringBuffer.append(TEXT_193);
    if (!genOperation.isVoid()) {
    stringBuffer.append(TEXT_194);
    stringBuffer.append(prefix);
    stringBuffer.append(TEXT_195);
    stringBuffer.append(genClass.getClassifierInstanceName());
    stringBuffer.append(TEXT_196);
    stringBuffer.append(genPackage.getPackageInstanceVariable(genOperation.getTypeGenPackage()));
    stringBuffer.append(TEXT_197);
    stringBuffer.append(genOperation.getTypeClassifierAccessorName());
    stringBuffer.append(TEXT_198);
    stringBuffer.append(genOperation.getName());
    stringBuffer.append(TEXT_199);
    stringBuffer.append(genModel.getNonNLS());
    } else {
    stringBuffer.append(TEXT_200);
    stringBuffer.append(prefix);
    stringBuffer.append(TEXT_201);
    stringBuffer.append(genClass.getClassifierInstanceName());
    stringBuffer.append(TEXT_202);
    stringBuffer.append(genOperation.getName());
    stringBuffer.append(TEXT_203);
    stringBuffer.append(genModel.getNonNLS());
    }
    for (Iterator p=genOperation.getGenParameters().iterator(); p.hasNext();) { GenParameter genParameter = (GenParameter)p.next();
    stringBuffer.append(TEXT_204);
    stringBuffer.append(genPackage.getPackageInstanceVariable(genParameter.getTypeGenPackage()));
    stringBuffer.append(TEXT_205);
    stringBuffer.append(genParameter.getTypeClassifierAccessorName());
    stringBuffer.append(TEXT_206);
    stringBuffer.append(genParameter.getName());
    stringBuffer.append(TEXT_207);
    stringBuffer.append(genModel.getNonNLS());
    }
    for (Iterator p=genOperation.getGenExceptions().iterator(); p.hasNext();) { GenClassifier genException = (GenClassifier)p.next();
    stringBuffer.append(TEXT_208);
    stringBuffer.append(genPackage.getPackageInstanceVariable(genException.getGenPackage()));
    stringBuffer.append(TEXT_209);
    stringBuffer.append(genException.getClassifierAccessorName());
    stringBuffer.append(TEXT_210);
    }
    }
    if (c.hasNext()) {
    stringBuffer.append(TEXT_211);
    }
    }
    }
    if (!genPackage.getGenEnums().isEmpty()) {
    stringBuffer.append(TEXT_212);
    for (Iterator e=genPackage.getGenEnums().iterator(); e.hasNext();) { GenEnum genEnum = (GenEnum)e.next();
    stringBuffer.append(TEXT_213);
    stringBuffer.append(genEnum.getClassifierInstanceName());
    stringBuffer.append(TEXT_214);
    stringBuffer.append(genEnum.getImportedName());
    stringBuffer.append(TEXT_215);
    stringBuffer.append(genEnum.getName());
    stringBuffer.append(TEXT_216);
    stringBuffer.append(genModel.getNonNLS());
    for (Iterator l=genEnum.getGenEnumLiterals().iterator(); l.hasNext();) { GenEnumLiteral genEnumLiteral = (GenEnumLiteral)l.next();
    stringBuffer.append(TEXT_217);
    stringBuffer.append(genEnum.getClassifierInstanceName());
    stringBuffer.append(TEXT_218);
    stringBuffer.append(genEnum.getImportedName().equals(genEnum.getClassifierID()) ? genEnum.getQualifiedName() : genEnum.getImportedName());
    stringBuffer.append(TEXT_219);
    stringBuffer.append(genEnumLiteral.getEnumLiteralID());
    stringBuffer.append(TEXT_220);
    }
    if (e.hasNext()) {
    stringBuffer.append(TEXT_221);
    }
    }
    }
    if (!genPackage.getGenDataTypes().isEmpty()) {
    stringBuffer.append(TEXT_222);
    for (Iterator d=genPackage.getGenDataTypes().iterator(); d.hasNext();) { GenDataType genDataType = (GenDataType)d.next();
    stringBuffer.append(TEXT_223);
    stringBuffer.append(genDataType.getClassifierInstanceName());
    stringBuffer.append(TEXT_224);
    stringBuffer.append(genDataType.getImportedInstanceClassName());
    stringBuffer.append(TEXT_225);
    stringBuffer.append(genDataType.getName());
    stringBuffer.append(TEXT_226);
    stringBuffer.append(genDataType.getSerializableFlag());
    stringBuffer.append(TEXT_227);
    stringBuffer.append(genDataType.getGeneratedInstanceClassFlag());
    stringBuffer.append(TEXT_228);
    stringBuffer.append(genModel.getNonNLS());
    }
    }
    if (genPackage.getSuperGenPackage() == null) {
    stringBuffer.append(TEXT_229);
    }
    if (!genPackage.getAnnotationSources().isEmpty()) {
    stringBuffer.append(TEXT_230);
    for (Iterator i = genPackage.getAnnotationSources().iterator(); i.hasNext();) { String annotationSource = (String)i.next();
    stringBuffer.append(TEXT_231);
    stringBuffer.append(annotationSource);
    stringBuffer.append(TEXT_232);
    stringBuffer.append(genPackage.getAnnotationSourceIdentifier(annotationSource));
    stringBuffer.append(TEXT_233);
    }
    }
    stringBuffer.append(TEXT_234);
    for (Iterator i = genPackage.getAnnotationSources().iterator(); i.hasNext();) { String annotationSource = (String)i.next();
    stringBuffer.append(TEXT_235);
    stringBuffer.append(annotationSource);
    stringBuffer.append(TEXT_236);
    stringBuffer.append(genPackage.getAnnotationSourceIdentifier(annotationSource));
    stringBuffer.append(TEXT_237);
    if (annotationSource == null) {
    stringBuffer.append(TEXT_238);
    } else {
    stringBuffer.append(TEXT_239);
    stringBuffer.append(annotationSource);
    stringBuffer.append(TEXT_240);
    stringBuffer.append(genModel.getNonNLS());
    }
    for (Iterator j = genPackage.getAllAnnotations().iterator(); j.hasNext();) { EAnnotation eAnnotation = (EAnnotation)j.next();
    stringBuffer.append(TEXT_241);
    if (annotationSource == null ? eAnnotation.getSource() == null : annotationSource.equals(eAnnotation.getSource())) {
    stringBuffer.append(TEXT_242);
    stringBuffer.append(genPackage.getAnnotatedModelElementAccessor(eAnnotation));
    stringBuffer.append(TEXT_243);
    for (Iterator k = eAnnotation.getDetails().iterator(); k.hasNext();) { Map.Entry detail = (Map.Entry)k.next(); String key = Literals.toStringLiteral((String)detail.getKey(), genModel); String value = Literals.toStringLiteral((String)detail.getValue(), genModel);
    stringBuffer.append(TEXT_244);
    stringBuffer.append(key);
    stringBuffer.append(TEXT_245);
    stringBuffer.append(value);
    stringBuffer.append(k.hasNext() ? "," : "");
    stringBuffer.append(genModel.getNonNLS(key + value));
    }
    stringBuffer.append(TEXT_246);
    }
    }
    stringBuffer.append(TEXT_247);
    }
    } else {
    if (genPackage.isLoadingInitialization()) {
    stringBuffer.append(TEXT_248);
    stringBuffer.append(genModel.getImportedName("java.net.URL"));
    stringBuffer.append(TEXT_249);
    stringBuffer.append(genModel.getNonNLS());
    stringBuffer.append(TEXT_250);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.util.URI"));
    stringBuffer.append(TEXT_251);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.util.URI"));
    stringBuffer.append(TEXT_252);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.resource.Resource"));
    stringBuffer.append(TEXT_253);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.xmi.impl.EcoreResourceFactoryImpl"));
    stringBuffer.append(TEXT_254);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.util.URI"));
    stringBuffer.append(TEXT_255);
    stringBuffer.append(genModel.getImportedName("java.io.IOException"));
    stringBuffer.append(TEXT_256);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.util.WrappedException"));
    stringBuffer.append(TEXT_257);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.EPackage"));
    stringBuffer.append(TEXT_258);
    }
    stringBuffer.append(TEXT_259);
    }
    stringBuffer.append(TEXT_260);
    stringBuffer.append(genPackage.getPackageClassName());
    genModel.emitSortedImports();
    stringBuffer.append(TEXT_261);
    return stringBuffer.toString();
  }
}
