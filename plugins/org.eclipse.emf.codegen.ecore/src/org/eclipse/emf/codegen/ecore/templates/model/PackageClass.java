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
  protected final String TEXT_4 = NL + " */";
  protected final String TEXT_5 = NL + "package ";
  protected final String TEXT_6 = ";";
  protected final String TEXT_7 = NL + "package ";
  protected final String TEXT_8 = ";";
  protected final String TEXT_9 = NL;
  protected final String TEXT_10 = NL + NL + "/**" + NL + " * <!-- begin-user-doc -->" + NL + " * The <b>Package</b> for the model." + NL + " * It contains accessors for the meta objects to represent" + NL + " * <ul>" + NL + " *   <li>each class,</li>" + NL + " *   <li>each feature of each class,</li>" + NL + " *   <li>each enum,</li>" + NL + " *   <li>and each data type</li>" + NL + " * </ul>" + NL + " * <!-- end-user-doc -->";
  protected final String TEXT_11 = NL + " * <!-- begin-model-doc -->" + NL + " * ";
  protected final String TEXT_12 = NL + " * <!-- end-model-doc -->";
  protected final String TEXT_13 = NL + " * @see ";
  protected final String TEXT_14 = NL + " * @model ";
  protected final String TEXT_15 = NL + " *        ";
  protected final String TEXT_16 = NL + " * @model";
  protected final String TEXT_17 = NL + " * @generated" + NL + " */";
  protected final String TEXT_18 = NL + NL + "/**" + NL + " * <!-- begin-user-doc -->" + NL + " * An implementation of the model <b>Package</b>." + NL + " * <!-- end-user-doc -->" + NL + " * @generated" + NL + " */";
  protected final String TEXT_19 = NL + "public class ";
  protected final String TEXT_20 = " extends ";
  protected final String TEXT_21 = " implements ";
  protected final String TEXT_22 = NL + "public interface ";
  protected final String TEXT_23 = " extends ";
  protected final String TEXT_24 = NL + "{";
  protected final String TEXT_25 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */" + NL + "\t";
  protected final String TEXT_26 = " copyright = \"";
  protected final String TEXT_27 = "\";";
  protected final String TEXT_28 = NL;
  protected final String TEXT_29 = NL + "\t/**" + NL + "\t * The package name." + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */" + NL + "\t";
  protected final String TEXT_30 = " eNAME = \"";
  protected final String TEXT_31 = "\";";
  protected final String TEXT_32 = NL + NL + "\t/**" + NL + "\t * The package namespace URI." + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */" + NL + "\t";
  protected final String TEXT_33 = " eNS_URI = \"";
  protected final String TEXT_34 = "\";";
  protected final String TEXT_35 = NL + NL + "\t/**" + NL + "\t * The package namespace name." + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */" + NL + "\t";
  protected final String TEXT_36 = " eNS_PREFIX = \"";
  protected final String TEXT_37 = "\";";
  protected final String TEXT_38 = NL + NL + "\t/**" + NL + "\t * The singleton instance of the package." + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */" + NL + "\t";
  protected final String TEXT_39 = " eINSTANCE = ";
  protected final String TEXT_40 = ".init();" + NL;
  protected final String TEXT_41 = NL + "\t/**";
  protected final String TEXT_42 = NL + "\t * The meta object id for the '{@link ";
  protected final String TEXT_43 = " <em>";
  protected final String TEXT_44 = "</em>}' class." + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @see ";
  protected final String TEXT_45 = NL + "\t * The meta object id for the '{@link ";
  protected final String TEXT_46 = " <em>";
  protected final String TEXT_47 = "</em>}' class." + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @see ";
  protected final String TEXT_48 = NL + "\t * The meta object id for the '{@link ";
  protected final String TEXT_49 = " <em>";
  protected final String TEXT_50 = "</em>}' enum." + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @see ";
  protected final String TEXT_51 = NL + "\t * The meta object id for the '<em>";
  protected final String TEXT_52 = "</em>' data type." + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->";
  protected final String TEXT_53 = NL + "\t * @see ";
  protected final String TEXT_54 = NL + "\t * @see ";
  protected final String TEXT_55 = "#get";
  protected final String TEXT_56 = "()" + NL + "\t * @generated" + NL + "\t */" + NL + "\t";
  protected final String TEXT_57 = "int ";
  protected final String TEXT_58 = " = ";
  protected final String TEXT_59 = ";" + NL;
  protected final String TEXT_60 = NL + "\t/**" + NL + "\t * The feature id for the '<em><b>";
  protected final String TEXT_61 = "</b></em>' ";
  protected final String TEXT_62 = "." + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t * @ordered" + NL + "\t */" + NL + "\t";
  protected final String TEXT_63 = "int ";
  protected final String TEXT_64 = " = ";
  protected final String TEXT_65 = ";" + NL;
  protected final String TEXT_66 = NL + "\t/**" + NL + "\t * The number of structural features of the '<em>";
  protected final String TEXT_67 = "</em>' class." + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t * @ordered" + NL + "\t */" + NL + "\t";
  protected final String TEXT_68 = "int ";
  protected final String TEXT_69 = " = ";
  protected final String TEXT_70 = ";" + NL;
  protected final String TEXT_71 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */" + NL + "\tprotected String packageFilename = \"";
  protected final String TEXT_72 = "\";";
  protected final String TEXT_73 = NL;
  protected final String TEXT_74 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */" + NL + "\tprivate ";
  protected final String TEXT_75 = " ";
  protected final String TEXT_76 = " = null;" + NL;
  protected final String TEXT_77 = NL + "\t/**" + NL + "\t * Creates an instance of the model <b>Package</b>, registered with" + NL + "\t * {@link org.eclipse.emf.ecore.EPackage.Registry EPackage.Registry} by the package" + NL + "\t * package URI value." + NL + "\t * <p>Note: the correct way to create the package is via the static" + NL + "\t * factory method {@link #init init()}, which also performs" + NL + "\t * initialization of the package, or returns the registered package," + NL + "\t * if one already exists." + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @see org.eclipse.emf.ecore.EPackage.Registry" + NL + "\t * @see ";
  protected final String TEXT_78 = "#eNS_URI" + NL + "\t * @see #init()" + NL + "\t * @generated" + NL + "\t */" + NL + "\tprivate ";
  protected final String TEXT_79 = "()" + NL + "\t{" + NL + "\t\tsuper(eNS_URI, ";
  protected final String TEXT_80 = ");" + NL + "\t}" + NL + "" + NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */" + NL + "\tprivate static boolean isInited = false;" + NL + "" + NL + "\t/**" + NL + "\t * Creates, registers, and initializes the <b>Package</b> for this" + NL + "\t * model, and for any others upon which it depends.  Simple" + NL + "\t * dependencies are satisfied by calling this method on all" + NL + "\t * dependent packages before doing anything else.  This method drives" + NL + "\t * initialization for interdependent packages directly, in parallel" + NL + "\t * with this package, itself." + NL + "\t * <p>Of this package and its interdependencies, all packages which" + NL + "\t * have not yet been registered by their URI values are first created" + NL + "\t * and registered.  The packages are then initialized in two steps:" + NL + "\t * meta-model objects for all of the packages are created before any" + NL + "\t * are initialized, since one package's meta-model objects may refer to" + NL + "\t * those of another." + NL + "\t * <p>Invocation of this method will not affect any packages that have" + NL + "\t * already been initialized." + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @see #eNS_URI";
  protected final String TEXT_81 = NL + "\t * @see #createPackageContents()" + NL + "\t * @see #initializePackageContents()";
  protected final String TEXT_82 = NL + "\t * @generated" + NL + "\t */" + NL + "\tpublic static ";
  protected final String TEXT_83 = " init()" + NL + "\t{" + NL + "\t\tif (isInited) return (";
  protected final String TEXT_84 = ")";
  protected final String TEXT_85 = ".Registry.INSTANCE.getEPackage(";
  protected final String TEXT_86 = ".eNS_URI);" + NL + "" + NL + "\t\t// Obtain or create and register package" + NL + "\t\t";
  protected final String TEXT_87 = " the";
  protected final String TEXT_88 = " = (";
  protected final String TEXT_89 = ")(";
  protected final String TEXT_90 = ".Registry.INSTANCE.getEPackage(eNS_URI) instanceof ";
  protected final String TEXT_91 = " ? ";
  protected final String TEXT_92 = ".Registry.INSTANCE.getEPackage(eNS_URI) : new ";
  protected final String TEXT_93 = "());" + NL + "" + NL + "\t\tisInited = true;" + NL;
  protected final String TEXT_94 = NL + "\t\t// Initialize simple dependencies";
  protected final String TEXT_95 = NL + "\t\t";
  protected final String TEXT_96 = ".eINSTANCE.eClass();";
  protected final String TEXT_97 = NL;
  protected final String TEXT_98 = NL + "\t\t// Obtain or create and register interdependencies";
  protected final String TEXT_99 = NL + "\t\t";
  protected final String TEXT_100 = " ";
  protected final String TEXT_101 = " = (";
  protected final String TEXT_102 = ")(";
  protected final String TEXT_103 = ".Registry.INSTANCE.getEPackage(";
  protected final String TEXT_104 = ".eNS_URI) instanceof ";
  protected final String TEXT_105 = " ? ";
  protected final String TEXT_106 = ".Registry.INSTANCE.getEPackage(";
  protected final String TEXT_107 = ".eNS_URI) : ";
  protected final String TEXT_108 = ".eINSTANCE);";
  protected final String TEXT_109 = NL;
  protected final String TEXT_110 = NL + "\t\t// Load packages";
  protected final String TEXT_111 = NL + "\t\tthe";
  protected final String TEXT_112 = ".loadPackage();";
  protected final String TEXT_113 = NL + "\t\t";
  protected final String TEXT_114 = ".loadPackage();";
  protected final String TEXT_115 = NL;
  protected final String TEXT_116 = NL + "\t\t// Create package meta-data objects";
  protected final String TEXT_117 = NL + "\t\tthe";
  protected final String TEXT_118 = ".createPackageContents();";
  protected final String TEXT_119 = NL + "\t\t";
  protected final String TEXT_120 = ".createPackageContents();";
  protected final String TEXT_121 = NL + NL + "\t\t// Initialize created meta-data";
  protected final String TEXT_122 = NL + "\t\tthe";
  protected final String TEXT_123 = ".initializePackageContents();";
  protected final String TEXT_124 = NL + "\t\t";
  protected final String TEXT_125 = ".initializePackageContents();";
  protected final String TEXT_126 = NL;
  protected final String TEXT_127 = NL + "\t\t// Fix loaded packages";
  protected final String TEXT_128 = NL + "\t\tthe";
  protected final String TEXT_129 = ".fixPackageContents();";
  protected final String TEXT_130 = NL + "\t\t";
  protected final String TEXT_131 = ".fixPackageContents();";
  protected final String TEXT_132 = NL;
  protected final String TEXT_133 = NL + "\t\t// Register package validator" + NL + "\t\t";
  protected final String TEXT_134 = ".Registry.INSTANCE.put" + NL + "\t\t\t(the";
  protected final String TEXT_135 = ", " + NL + "\t\t\t new ";
  protected final String TEXT_136 = ".Descriptor()" + NL + "\t\t\t {" + NL + "\t\t\t\t public ";
  protected final String TEXT_137 = " getEValidator()" + NL + "\t\t\t\t {" + NL + "\t\t\t\t\t return ";
  protected final String TEXT_138 = ".INSTANCE;" + NL + "\t\t\t\t }" + NL + "\t\t\t });" + NL;
  protected final String TEXT_139 = NL + "\t\t// Mark meta-data to indicate it can't be changed" + NL + "\t\tthe";
  protected final String TEXT_140 = ".freeze();" + NL;
  protected final String TEXT_141 = NL + "\t\treturn the";
  protected final String TEXT_142 = ";" + NL + "\t}" + NL;
  protected final String TEXT_143 = NL;
  protected final String TEXT_144 = NL + "\t/**";
  protected final String TEXT_145 = NL + "\t * Returns the meta object for class '{@link ";
  protected final String TEXT_146 = " <em>";
  protected final String TEXT_147 = "</em>}'." + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @return the meta object for class '<em>";
  protected final String TEXT_148 = "</em>'." + NL + "\t * @see ";
  protected final String TEXT_149 = NL + "\t * @model ";
  protected final String TEXT_150 = NL + "\t *        ";
  protected final String TEXT_151 = NL + "\t * @model";
  protected final String TEXT_152 = NL + "\t * Returns the meta object for enum '{@link ";
  protected final String TEXT_153 = " <em>";
  protected final String TEXT_154 = "</em>}'." + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @return the meta object for enum '<em>";
  protected final String TEXT_155 = "</em>'." + NL + "\t * @see ";
  protected final String TEXT_156 = NL + "\t * Returns the meta object for data type '<em>";
  protected final String TEXT_157 = "</em>'.";
  protected final String TEXT_158 = NL + "\t * Returns the meta object for data type '{@link ";
  protected final String TEXT_159 = " <em>";
  protected final String TEXT_160 = "</em>}'.";
  protected final String TEXT_161 = NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @return the meta object for data type '<em>";
  protected final String TEXT_162 = "</em>'.";
  protected final String TEXT_163 = NL + "\t * @see ";
  protected final String TEXT_164 = NL + "\t * @model ";
  protected final String TEXT_165 = NL + "\t *        ";
  protected final String TEXT_166 = NL + "\t * @model";
  protected final String TEXT_167 = NL + "\t * @generated" + NL + "\t */";
  protected final String TEXT_168 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */";
  protected final String TEXT_169 = NL + "\tpublic ";
  protected final String TEXT_170 = " get";
  protected final String TEXT_171 = "()" + NL + "\t{";
  protected final String TEXT_172 = NL + "\t\tif (";
  protected final String TEXT_173 = " == null)" + NL + "\t\t{" + NL + "\t\t\t";
  protected final String TEXT_174 = " = (";
  protected final String TEXT_175 = ")";
  protected final String TEXT_176 = ".Registry.INSTANCE.getEPackage(";
  protected final String TEXT_177 = ".eNS_URI).getEClassifiers().get(";
  protected final String TEXT_178 = ");" + NL + "\t\t}";
  protected final String TEXT_179 = NL + "\t\treturn ";
  protected final String TEXT_180 = ";" + NL + "\t}" + NL;
  protected final String TEXT_181 = NL + "\t";
  protected final String TEXT_182 = " get";
  protected final String TEXT_183 = "();" + NL;
  protected final String TEXT_184 = NL + "\t/**" + NL + "\t * Returns the meta object for the ";
  protected final String TEXT_185 = " '{@link ";
  protected final String TEXT_186 = "#";
  protected final String TEXT_187 = " <em>";
  protected final String TEXT_188 = "</em>}'." + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @return the meta object for the ";
  protected final String TEXT_189 = " '<em>";
  protected final String TEXT_190 = "</em>'." + NL + "\t * @see ";
  protected final String TEXT_191 = "#";
  protected final String TEXT_192 = "()";
  protected final String TEXT_193 = NL + "\t * @see #get";
  protected final String TEXT_194 = "()" + NL + "\t * @generated" + NL + "\t */";
  protected final String TEXT_195 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */";
  protected final String TEXT_196 = NL + "\tpublic ";
  protected final String TEXT_197 = " get";
  protected final String TEXT_198 = "()" + NL + "\t{";
  protected final String TEXT_199 = NL + "\t\treturn (";
  protected final String TEXT_200 = ")";
  protected final String TEXT_201 = ".getEStructuralFeatures().get(";
  protected final String TEXT_202 = ");";
  protected final String TEXT_203 = NL + "        return (";
  protected final String TEXT_204 = ")get";
  protected final String TEXT_205 = "().getEStructuralFeatures().get(";
  protected final String TEXT_206 = ");";
  protected final String TEXT_207 = NL + "\t}";
  protected final String TEXT_208 = NL + "\t";
  protected final String TEXT_209 = " get";
  protected final String TEXT_210 = "();";
  protected final String TEXT_211 = NL;
  protected final String TEXT_212 = NL + "\t/**" + NL + "\t * Returns the factory that creates the instances of the model." + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @return the factory that creates the instances of the model." + NL + "\t * @generated" + NL + "\t */";
  protected final String TEXT_213 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */";
  protected final String TEXT_214 = NL + "\tpublic ";
  protected final String TEXT_215 = " get";
  protected final String TEXT_216 = "()" + NL + "\t{" + NL + "\t\treturn (";
  protected final String TEXT_217 = ")getEFactoryInstance();" + NL + "\t}";
  protected final String TEXT_218 = NL + "\t";
  protected final String TEXT_219 = " get";
  protected final String TEXT_220 = "();";
  protected final String TEXT_221 = NL;
  protected final String TEXT_222 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */" + NL + "\tprivate boolean isCreated = false;" + NL + "" + NL + "\t/**" + NL + "\t * Creates the meta-model objects for the package.  This method is" + NL + "\t * guarded to have no affect on any invocation but its first." + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */" + NL + "\tpublic void createPackageContents()" + NL + "\t{" + NL + "\t\tif (isCreated) return;" + NL + "\t\tisCreated = true;";
  protected final String TEXT_223 = NL + NL + "\t\t// Create classes and their features";
  protected final String TEXT_224 = NL + "\t\t";
  protected final String TEXT_225 = " = create";
  protected final String TEXT_226 = "(";
  protected final String TEXT_227 = ");";
  protected final String TEXT_228 = NL + "\t\tcreate";
  protected final String TEXT_229 = "(";
  protected final String TEXT_230 = ", ";
  protected final String TEXT_231 = ");";
  protected final String TEXT_232 = NL;
  protected final String TEXT_233 = NL + NL + "\t\t// Create enums";
  protected final String TEXT_234 = NL + "\t\t";
  protected final String TEXT_235 = " = createEEnum(";
  protected final String TEXT_236 = ");";
  protected final String TEXT_237 = NL + NL + "\t\t// Create data types";
  protected final String TEXT_238 = NL + "\t\t";
  protected final String TEXT_239 = " = createEDataType(";
  protected final String TEXT_240 = ");";
  protected final String TEXT_241 = NL + "\t}" + NL + "" + NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */" + NL + "\tprivate boolean isInitialized = false;" + NL + "" + NL + "\t/**" + NL + "\t * Complete the initialization of the package and its meta-model.  This" + NL + "\t * method is guarded to have no affect on any invocation but its first." + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */" + NL + "\tpublic void initializePackageContents()" + NL + "\t{" + NL + "\t\tif (isInitialized) return;" + NL + "\t\tisInitialized = true;" + NL + "" + NL + "\t\t// Initialize package" + NL + "\t\tsetName(eNAME);" + NL + "\t\tsetNsPrefix(eNS_PREFIX);" + NL + "\t\tsetNsURI(eNS_URI);";
  protected final String TEXT_242 = NL + NL + "\t\t// Obtain other dependent packages";
  protected final String TEXT_243 = NL + "\t\t";
  protected final String TEXT_244 = " ";
  protected final String TEXT_245 = " = (";
  protected final String TEXT_246 = ")";
  protected final String TEXT_247 = ".Registry.INSTANCE.getEPackage(";
  protected final String TEXT_248 = ".eNS_URI);";
  protected final String TEXT_249 = NL + NL + "\t\t// Add subpackages";
  protected final String TEXT_250 = NL + "\t\tgetESubpackages().add(";
  protected final String TEXT_251 = ");";
  protected final String TEXT_252 = NL + NL + "\t\t// Add supertypes to classes";
  protected final String TEXT_253 = NL + "\t\t";
  protected final String TEXT_254 = ".getESuperTypes().add(";
  protected final String TEXT_255 = ".get";
  protected final String TEXT_256 = "());";
  protected final String TEXT_257 = NL + NL + "\t\t// Initialize classes and features; add operations and parameters";
  protected final String TEXT_258 = NL + "\t\tinitEClass(";
  protected final String TEXT_259 = ", ";
  protected final String TEXT_260 = "null";
  protected final String TEXT_261 = ".class";
  protected final String TEXT_262 = ", \"";
  protected final String TEXT_263 = "\", ";
  protected final String TEXT_264 = ", ";
  protected final String TEXT_265 = ", ";
  protected final String TEXT_266 = ");";
  protected final String TEXT_267 = NL + "\t\tinitEReference(get";
  protected final String TEXT_268 = "(), ";
  protected final String TEXT_269 = ".get";
  protected final String TEXT_270 = "(), ";
  protected final String TEXT_271 = ", \"";
  protected final String TEXT_272 = "\", ";
  protected final String TEXT_273 = ", ";
  protected final String TEXT_274 = ", ";
  protected final String TEXT_275 = ", ";
  protected final String TEXT_276 = ", ";
  protected final String TEXT_277 = ", ";
  protected final String TEXT_278 = ", ";
  protected final String TEXT_279 = ", ";
  protected final String TEXT_280 = ", ";
  protected final String TEXT_281 = ", ";
  protected final String TEXT_282 = ", ";
  protected final String TEXT_283 = ", ";
  protected final String TEXT_284 = ", ";
  protected final String TEXT_285 = ");";
  protected final String TEXT_286 = NL + "\t\tinitEAttribute(get";
  protected final String TEXT_287 = "(), ";
  protected final String TEXT_288 = ".get";
  protected final String TEXT_289 = "(), \"";
  protected final String TEXT_290 = "\", ";
  protected final String TEXT_291 = ", ";
  protected final String TEXT_292 = ", ";
  protected final String TEXT_293 = ", ";
  protected final String TEXT_294 = ", ";
  protected final String TEXT_295 = ", ";
  protected final String TEXT_296 = ", ";
  protected final String TEXT_297 = ", ";
  protected final String TEXT_298 = ", ";
  protected final String TEXT_299 = ", ";
  protected final String TEXT_300 = ", ";
  protected final String TEXT_301 = ", ";
  protected final String TEXT_302 = ");";
  protected final String TEXT_303 = NL;
  protected final String TEXT_304 = NL + "\t\t";
  protected final String TEXT_305 = "addEOperation(";
  protected final String TEXT_306 = ", ";
  protected final String TEXT_307 = ".get";
  protected final String TEXT_308 = "(), \"";
  protected final String TEXT_309 = "\", ";
  protected final String TEXT_310 = ", ";
  protected final String TEXT_311 = ");";
  protected final String TEXT_312 = NL + "\t\t";
  protected final String TEXT_313 = "addEOperation(";
  protected final String TEXT_314 = ", null, \"";
  protected final String TEXT_315 = "\");";
  protected final String TEXT_316 = NL + "\t\taddEParameter(op, ";
  protected final String TEXT_317 = ".get";
  protected final String TEXT_318 = "(), \"";
  protected final String TEXT_319 = "\", ";
  protected final String TEXT_320 = ", ";
  protected final String TEXT_321 = ");";
  protected final String TEXT_322 = NL + "\t\taddEException(op, ";
  protected final String TEXT_323 = ".get";
  protected final String TEXT_324 = "());";
  protected final String TEXT_325 = NL;
  protected final String TEXT_326 = NL + NL + "\t\t// Initialize enums and add enum literals";
  protected final String TEXT_327 = NL + "\t\tinitEEnum(";
  protected final String TEXT_328 = ", ";
  protected final String TEXT_329 = ".class, \"";
  protected final String TEXT_330 = "\");";
  protected final String TEXT_331 = NL + "\t\taddEEnumLiteral(";
  protected final String TEXT_332 = ", ";
  protected final String TEXT_333 = ".";
  protected final String TEXT_334 = "_LITERAL);";
  protected final String TEXT_335 = NL;
  protected final String TEXT_336 = NL + NL + "\t\t// Initialize data types";
  protected final String TEXT_337 = NL + "\t\tinitEDataType(";
  protected final String TEXT_338 = ", ";
  protected final String TEXT_339 = ".class, \"";
  protected final String TEXT_340 = "\", ";
  protected final String TEXT_341 = ", ";
  protected final String TEXT_342 = ");";
  protected final String TEXT_343 = NL + NL + "\t\t// Create resource" + NL + "\t\tcreateResource(eNS_URI);";
  protected final String TEXT_344 = NL + NL + "\t\t// Create annotations";
  protected final String TEXT_345 = NL + "\t\t// ";
  protected final String TEXT_346 = NL + "\t\tcreate";
  protected final String TEXT_347 = "Annotations();";
  protected final String TEXT_348 = NL + "\t}" + NL;
  protected final String TEXT_349 = NL + "\t/**" + NL + "\t * Initializes the annotations for <b>";
  protected final String TEXT_350 = "</b>." + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */" + NL + "\tprotected void create";
  protected final String TEXT_351 = "Annotations()" + NL + "\t{" + NL + "\t\tString source = ";
  protected final String TEXT_352 = "null";
  protected final String TEXT_353 = "\"";
  protected final String TEXT_354 = "\"";
  protected final String TEXT_355 = ";";
  protected final String TEXT_356 = "\t";
  protected final String TEXT_357 = "\t" + NL + "\t\taddAnnotation" + NL + "\t\t  (";
  protected final String TEXT_358 = ", " + NL + "\t\t   source, " + NL + "\t\t   new String[] " + NL + "\t\t   {";
  protected final String TEXT_359 = NL + "\t\t\t ";
  protected final String TEXT_360 = ", ";
  protected final String TEXT_361 = NL + "\t\t   });";
  protected final String TEXT_362 = NL + "\t\taddAnnotation" + NL + "\t\t  (";
  protected final String TEXT_363 = ", " + NL + "\t\t   ";
  protected final String TEXT_364 = "," + NL + "\t\t   ";
  protected final String TEXT_365 = "null";
  protected final String TEXT_366 = "\"";
  protected final String TEXT_367 = "\"";
  protected final String TEXT_368 = "," + NL + "\t\t   new String[] " + NL + "\t\t   {";
  protected final String TEXT_369 = NL + "\t\t    ";
  protected final String TEXT_370 = ", ";
  protected final String TEXT_371 = NL + "\t\t   });";
  protected final String TEXT_372 = NL + "\t\taddAnnotation" + NL + "\t\t  (";
  protected final String TEXT_373 = ", " + NL + "\t\t   ";
  protected final String TEXT_374 = "," + NL + "\t\t   ";
  protected final String TEXT_375 = "null";
  protected final String TEXT_376 = "\"";
  protected final String TEXT_377 = "\"";
  protected final String TEXT_378 = "," + NL + "\t\t   new String[] " + NL + "\t\t   {";
  protected final String TEXT_379 = NL + "\t\t    ";
  protected final String TEXT_380 = ", ";
  protected final String TEXT_381 = NL + "\t\t   });";
  protected final String TEXT_382 = NL + "\t}" + NL;
  protected final String TEXT_383 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */" + NL + "\tprivate boolean isLoaded = false;" + NL + "" + NL + "\t/**" + NL + "\t * Laods the package and any sub-packages from their serialized form." + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */" + NL + "\tpublic void loadPackage()" + NL + "\t{" + NL + "\t\tif (isLoaded) return;" + NL + "\t\tisLoaded = true;" + NL + "" + NL + "\t\t";
  protected final String TEXT_384 = " url = getClass().getResource(packageFilename);" + NL + "\t\tif (url == null)" + NL + "\t\t{" + NL + "\t\t\tthrow new RuntimeException(\"Missing serialized package: \" + packageFilename);";
  protected final String TEXT_385 = NL + "\t\t}" + NL + "\t\t";
  protected final String TEXT_386 = " uri = ";
  protected final String TEXT_387 = ".createURI(url.toString());" + NL + "\t\t";
  protected final String TEXT_388 = " resource = new ";
  protected final String TEXT_389 = "().createResource(uri);" + NL + "\t\ttry" + NL + "\t\t{" + NL + "\t\t\tresource.load(null);" + NL + "\t\t}" + NL + "\t\tcatch (";
  protected final String TEXT_390 = " exception)" + NL + "\t\t{" + NL + "\t\t\tthrow new ";
  protected final String TEXT_391 = "(exception);" + NL + "\t\t}" + NL + "\t\tinitializeFromLoadedEPackage(this, (";
  protected final String TEXT_392 = ")resource.getContents().get(0));" + NL + "\t\tcreateResource(eNS_URI);" + NL + "\t}" + NL;
  protected final String TEXT_393 = NL + NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */" + NL + "\tprivate boolean isFixed = false;" + NL + "" + NL + "\t/**" + NL + "\t * Fixes up the loaded package, to make it appear as if it had been programmatically built." + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */" + NL + "\tpublic void fixPackageContents()" + NL + "\t{" + NL + "\t\tif (isFixed) return;" + NL + "\t\tisFixed = true;" + NL + "\t\tfixEClassifiers();" + NL + "\t}" + NL + "" + NL + "\t/**" + NL + "\t * Sets the instance class on the given classifier." + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */" + NL + "\tprotected void fixInstanceClass(";
  protected final String TEXT_394 = " eClassifier)" + NL + "\t{" + NL + "\t\tif (eClassifier.getInstanceClassName() == null)" + NL + "\t\t{" + NL + "\t\t\teClassifier.setInstanceClassName(\"";
  protected final String TEXT_395 = ".\" + eClassifier.getName());";
  protected final String TEXT_396 = NL + "\t\t\tsetGeneratedClassName(eClassifier);" + NL + "\t\t}" + NL + "\t}" + NL;
  protected final String TEXT_397 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * Defines literals for the meta objects that represent" + NL + "\t * <ul>" + NL + "\t *   <li>each class,</li>" + NL + "\t *   <li>each feature of each class,</li>" + NL + "\t *   <li>each enum,</li>" + NL + "\t *   <li>and each data type</li>" + NL + "\t * </ul>" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */" + NL + "\t";
  protected final String TEXT_398 = "public ";
  protected final String TEXT_399 = "interface Literals" + NL + "\t{";
  protected final String TEXT_400 = NL + "\t\t/**";
  protected final String TEXT_401 = NL + "\t\t * The meta object literal for the '{@link ";
  protected final String TEXT_402 = " <em>";
  protected final String TEXT_403 = "</em>}' class." + NL + "\t\t * <!-- begin-user-doc -->" + NL + "\t\t * <!-- end-user-doc -->" + NL + "\t\t * @see ";
  protected final String TEXT_404 = NL + "\t\t * The meta object literal for the '{@link ";
  protected final String TEXT_405 = " <em>";
  protected final String TEXT_406 = "</em>}' class." + NL + "\t\t * <!-- begin-user-doc -->" + NL + "\t\t * <!-- end-user-doc -->" + NL + "\t\t * @see ";
  protected final String TEXT_407 = NL + "\t\t * The meta object literal for the '{@link ";
  protected final String TEXT_408 = " <em>";
  protected final String TEXT_409 = "</em>}' enum." + NL + "\t\t * <!-- begin-user-doc -->" + NL + "\t\t * <!-- end-user-doc -->" + NL + "\t\t * @see ";
  protected final String TEXT_410 = NL + "\t\t * The meta object literal for the '<em>";
  protected final String TEXT_411 = "</em>' data type." + NL + "\t\t * <!-- begin-user-doc -->" + NL + "\t\t * <!-- end-user-doc -->";
  protected final String TEXT_412 = NL + "\t\t * @see ";
  protected final String TEXT_413 = NL + "\t\t * @see ";
  protected final String TEXT_414 = "#get";
  protected final String TEXT_415 = "()" + NL + "\t\t * @generated" + NL + "\t\t */" + NL + "\t\t";
  protected final String TEXT_416 = " ";
  protected final String TEXT_417 = " = eINSTANCE.get";
  protected final String TEXT_418 = "();" + NL;
  protected final String TEXT_419 = NL + "\t\t/**" + NL + "\t\t * The meta object literal for the '<em><b>";
  protected final String TEXT_420 = "</b></em>' ";
  protected final String TEXT_421 = " feature." + NL + "\t\t * <!-- begin-user-doc -->" + NL + "\t\t * <!-- end-user-doc -->" + NL + "\t\t * @generated" + NL + "\t\t */" + NL + "\t\t";
  protected final String TEXT_422 = " ";
  protected final String TEXT_423 = " = eINSTANCE.get";
  protected final String TEXT_424 = "();" + NL;
  protected final String TEXT_425 = NL + "\t}" + NL;
  protected final String TEXT_426 = NL + "} //";
  protected final String TEXT_427 = NL;

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
    if (isImplementation && !genModel.isSuppressInterfaces()) {
    stringBuffer.append(TEXT_5);
    stringBuffer.append(genPackage.getClassPackageName());
    stringBuffer.append(TEXT_6);
    } else {
    stringBuffer.append(TEXT_7);
    stringBuffer.append(genPackage.getReflectionPackageName());
    stringBuffer.append(TEXT_8);
    }
    stringBuffer.append(TEXT_9);
    genModel.markImportLocation(stringBuffer, genPackage);
    if (isImplementation) {
    genModel.addPseudoImport("org.eclipse.emf.ecore.EPackage.Registry");
    genModel.addPseudoImport("org.eclipse.emf.ecore.EPackage.Descriptor");
    if (genPackage.isLiteralsInterface()) {
    genModel.addPseudoImport(genPackage.getQualifiedPackageInterfaceName() + ".Literals");
    }
    for (Iterator i=genPackage.getOrderedGenClassifiers().iterator(); i.hasNext();) genModel.addPseudoImport(genPackage.getQualifiedPackageInterfaceName() + "." + genPackage.getClassifierID((GenClassifier)i.next()));
    }
    if (isInterface) {
    stringBuffer.append(TEXT_10);
    if (genPackage.hasDocumentation()) {
    stringBuffer.append(TEXT_11);
    stringBuffer.append(genPackage.getDocumentation(genModel.getIndentation(stringBuffer)));
    stringBuffer.append(TEXT_12);
    }
    stringBuffer.append(TEXT_13);
    stringBuffer.append(genPackage.getQualifiedFactoryInterfaceName());
    if (!genModel.isSuppressEMFModelTags()) { boolean first = true; for (StringTokenizer stringTokenizer = new StringTokenizer(genPackage.getModelInfo(), "\n\r"); stringTokenizer.hasMoreTokens(); ) { String modelInfo = stringTokenizer.nextToken(); if (first) { first = false;
    stringBuffer.append(TEXT_14);
    stringBuffer.append(modelInfo);
    } else {
    stringBuffer.append(TEXT_15);
    stringBuffer.append(modelInfo);
    }} if (first) {
    stringBuffer.append(TEXT_16);
    }}
    stringBuffer.append(TEXT_17);
    } else {
    stringBuffer.append(TEXT_18);
    }
    if (isImplementation) {
    stringBuffer.append(TEXT_19);
    stringBuffer.append(genPackage.getPackageClassName());
    stringBuffer.append(TEXT_20);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.impl.EPackageImpl"));
    if (!isInterface){
    stringBuffer.append(TEXT_21);
    stringBuffer.append(genPackage.getImportedPackageInterfaceName());
    }
    } else {
    stringBuffer.append(TEXT_22);
    stringBuffer.append(genPackage.getPackageInterfaceName());
    stringBuffer.append(TEXT_23);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.EPackage"));
    }
    stringBuffer.append(TEXT_24);
    if (genModel.getCopyrightText() != null) {
    stringBuffer.append(TEXT_25);
    stringBuffer.append(publicStaticFinalFlag);
    stringBuffer.append(genModel.getImportedName("java.lang.String"));
    stringBuffer.append(TEXT_26);
    stringBuffer.append(genModel.getCopyrightText());
    stringBuffer.append(TEXT_27);
    stringBuffer.append(genModel.getNonNLS());
    stringBuffer.append(TEXT_28);
    }
    if (isInterface) {
    stringBuffer.append(TEXT_29);
    stringBuffer.append(publicStaticFinalFlag);
    stringBuffer.append(genModel.getImportedName("java.lang.String"));
    stringBuffer.append(TEXT_30);
    stringBuffer.append(genPackage.getPackageName());
    stringBuffer.append(TEXT_31);
    stringBuffer.append(genModel.getNonNLS());
    stringBuffer.append(TEXT_32);
    stringBuffer.append(publicStaticFinalFlag);
    stringBuffer.append(genModel.getImportedName("java.lang.String"));
    stringBuffer.append(TEXT_33);
    stringBuffer.append(genPackage.getNSURI());
    stringBuffer.append(TEXT_34);
    stringBuffer.append(genModel.getNonNLS());
    stringBuffer.append(TEXT_35);
    stringBuffer.append(publicStaticFinalFlag);
    stringBuffer.append(genModel.getImportedName("java.lang.String"));
    stringBuffer.append(TEXT_36);
    stringBuffer.append(genPackage.getNSName());
    stringBuffer.append(TEXT_37);
    stringBuffer.append(genModel.getNonNLS());
    stringBuffer.append(TEXT_38);
    stringBuffer.append(publicStaticFinalFlag);
    stringBuffer.append(genPackage.getPackageInterfaceName());
    stringBuffer.append(TEXT_39);
    stringBuffer.append(genPackage.getQualifiedPackageClassName());
    stringBuffer.append(TEXT_40);
    for (Iterator i=genPackage.getOrderedGenClassifiers().iterator(); i.hasNext();) { GenClassifier genClassifier = (GenClassifier)i.next();
    stringBuffer.append(TEXT_41);
    if (genClassifier instanceof GenClass) { GenClass genClass = (GenClass)genClassifier;
    if (!genClass.isInterface()) {
    stringBuffer.append(TEXT_42);
    stringBuffer.append(genClass.getQualifiedClassName());
    stringBuffer.append(TEXT_43);
    stringBuffer.append(genClass.getFormattedName());
    stringBuffer.append(TEXT_44);
    stringBuffer.append(genClass.getQualifiedClassName());
    } else {
    stringBuffer.append(TEXT_45);
    stringBuffer.append(genClass.getQualifiedInterfaceName());
    stringBuffer.append(TEXT_46);
    stringBuffer.append(genClass.getFormattedName());
    stringBuffer.append(TEXT_47);
    stringBuffer.append(genClass.getQualifiedInterfaceName());
    }
    } else if (genClassifier instanceof GenEnum) { GenEnum genEnum = (GenEnum)genClassifier;
    stringBuffer.append(TEXT_48);
    stringBuffer.append(genEnum.getQualifiedName());
    stringBuffer.append(TEXT_49);
    stringBuffer.append(genEnum.getFormattedName());
    stringBuffer.append(TEXT_50);
    stringBuffer.append(genEnum.getQualifiedName());
    } else if (genClassifier instanceof GenDataType) { GenDataType genDataType = (GenDataType)genClassifier;
    stringBuffer.append(TEXT_51);
    stringBuffer.append(genDataType.getFormattedName());
    stringBuffer.append(TEXT_52);
    if (!genDataType.isPrimitiveType() && !genDataType.isArrayType()) {
    stringBuffer.append(TEXT_53);
    stringBuffer.append(genDataType.getQualifiedInstanceClassName());
    }
    }
    stringBuffer.append(TEXT_54);
    stringBuffer.append(genPackage.getQualifiedPackageClassName());
    stringBuffer.append(TEXT_55);
    stringBuffer.append(genClassifier.getClassifierAccessorName());
    stringBuffer.append(TEXT_56);
    stringBuffer.append(publicStaticFinalFlag);
    stringBuffer.append(TEXT_57);
    stringBuffer.append(genPackage.getClassifierID(genClassifier));
    stringBuffer.append(TEXT_58);
    stringBuffer.append(genPackage.getClassifierValue(genClassifier));
    stringBuffer.append(TEXT_59);
    if (genClassifier instanceof GenClass) { GenClass genClass = (GenClass)genClassifier;
    for (Iterator f=genClass.getAllGenFeatures().iterator(); f.hasNext();) { GenFeature genFeature = (GenFeature)f.next();
    stringBuffer.append(TEXT_60);
    stringBuffer.append(genFeature.getFormattedName());
    stringBuffer.append(TEXT_61);
    stringBuffer.append(genFeature.getFeatureKind());
    stringBuffer.append(TEXT_62);
    stringBuffer.append(publicStaticFinalFlag);
    stringBuffer.append(TEXT_63);
    stringBuffer.append(genClass.getFeatureID(genFeature));
    stringBuffer.append(TEXT_64);
    stringBuffer.append(genClass.getFeatureValue(genFeature));
    stringBuffer.append(TEXT_65);
    }
    stringBuffer.append(TEXT_66);
    stringBuffer.append(genClass.getFormattedName());
    stringBuffer.append(TEXT_67);
    stringBuffer.append(publicStaticFinalFlag);
    stringBuffer.append(TEXT_68);
    stringBuffer.append(genClass.getFeatureCountID());
    stringBuffer.append(TEXT_69);
    stringBuffer.append(genClass.getFeatureCountValue());
    stringBuffer.append(TEXT_70);
    }
    }
    }
    if (isImplementation) {
    if (genPackage.isLoadingInitialization()) {
    stringBuffer.append(TEXT_71);
    stringBuffer.append(genPackage.getSerializedPackageFilename());
    stringBuffer.append(TEXT_72);
    stringBuffer.append(genModel.getNonNLS());
    stringBuffer.append(TEXT_73);
    }
    for (Iterator i=genPackage.getGenClassifiers().iterator(); i.hasNext();) { GenClassifier genClassifier = (GenClassifier)i.next();
    stringBuffer.append(TEXT_74);
    stringBuffer.append(genClassifier.getImportedMetaType());
    stringBuffer.append(TEXT_75);
    stringBuffer.append(genClassifier.getClassifierInstanceName());
    stringBuffer.append(TEXT_76);
    }
    stringBuffer.append(TEXT_77);
    stringBuffer.append(genPackage.getQualifiedPackageInterfaceName());
    stringBuffer.append(TEXT_78);
    stringBuffer.append(genPackage.getPackageClassName());
    stringBuffer.append(TEXT_79);
    stringBuffer.append(genPackage.getQualifiedEFactoryInstanceAccessor());
    stringBuffer.append(TEXT_80);
    if (!genPackage.isLoadedInitialization()) {
    stringBuffer.append(TEXT_81);
    }
    stringBuffer.append(TEXT_82);
    stringBuffer.append(genPackage.getImportedPackageInterfaceName());
    stringBuffer.append(TEXT_83);
    stringBuffer.append(genPackage.getImportedPackageInterfaceName());
    stringBuffer.append(TEXT_84);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.EPackage"));
    stringBuffer.append(TEXT_85);
    stringBuffer.append(genPackage.getImportedPackageInterfaceName());
    stringBuffer.append(TEXT_86);
    stringBuffer.append(genPackage.getPackageClassName());
    stringBuffer.append(TEXT_87);
    stringBuffer.append(genPackage.getPackageInterfaceName());
    stringBuffer.append(TEXT_88);
    stringBuffer.append(genPackage.getPackageClassName());
    stringBuffer.append(TEXT_89);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.EPackage"));
    stringBuffer.append(TEXT_90);
    stringBuffer.append(genPackage.getPackageClassName());
    stringBuffer.append(TEXT_91);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.EPackage"));
    stringBuffer.append(TEXT_92);
    stringBuffer.append(genPackage.getPackageClassName());
    stringBuffer.append(TEXT_93);
    if (!genPackage.getPackageSimpleDependencies().isEmpty()) {
    stringBuffer.append(TEXT_94);
    for (Iterator p=genPackage.getPackageSimpleDependencies().iterator(); p.hasNext();) { GenPackage dep = (GenPackage)p.next();
    stringBuffer.append(TEXT_95);
    stringBuffer.append(dep.getImportedPackageInterfaceName());
    stringBuffer.append(TEXT_96);
    }
    stringBuffer.append(TEXT_97);
    }
    if (!genPackage.getPackageInterDependencies().isEmpty()) {
    stringBuffer.append(TEXT_98);
    for (Iterator p=genPackage.getPackageInterDependencies().iterator(); p.hasNext();) { GenPackage interdep = (GenPackage)p.next();
    stringBuffer.append(TEXT_99);
    stringBuffer.append(interdep.getImportedPackageClassName());
    stringBuffer.append(TEXT_100);
    stringBuffer.append(genPackage.getPackageInstanceVariable(interdep));
    stringBuffer.append(TEXT_101);
    stringBuffer.append(interdep.getImportedPackageClassName());
    stringBuffer.append(TEXT_102);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.EPackage"));
    stringBuffer.append(TEXT_103);
    stringBuffer.append(interdep.getImportedPackageInterfaceName());
    stringBuffer.append(TEXT_104);
    stringBuffer.append(interdep.getImportedPackageClassName());
    stringBuffer.append(TEXT_105);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.EPackage"));
    stringBuffer.append(TEXT_106);
    stringBuffer.append(interdep.getImportedPackageInterfaceName());
    stringBuffer.append(TEXT_107);
    stringBuffer.append(interdep.getImportedPackageInterfaceName());
    stringBuffer.append(TEXT_108);
    }
    stringBuffer.append(TEXT_109);
    }
    if (genPackage.isLoadedInitialization() || !genPackage.getPackageLoadInterDependencies().isEmpty()) {
    stringBuffer.append(TEXT_110);
    if (genPackage.isLoadingInitialization()) {
    stringBuffer.append(TEXT_111);
    stringBuffer.append(genPackage.getPackageInterfaceName());
    stringBuffer.append(TEXT_112);
    }
    for (Iterator p=genPackage.getPackageLoadInterDependencies().iterator(); p.hasNext();) { GenPackage interdep = (GenPackage)p.next();
    if (interdep.isLoadingInitialization()) {
    stringBuffer.append(TEXT_113);
    stringBuffer.append(genPackage.getPackageInstanceVariable(interdep));
    stringBuffer.append(TEXT_114);
    }
    }
    stringBuffer.append(TEXT_115);
    }
    if (!genPackage.isLoadedInitialization() || !genPackage.getPackageBuildInterDependencies().isEmpty()) {
    stringBuffer.append(TEXT_116);
    if (!genPackage.isLoadedInitialization()) {
    stringBuffer.append(TEXT_117);
    stringBuffer.append(genPackage.getPackageInterfaceName());
    stringBuffer.append(TEXT_118);
    }
    for (Iterator p=genPackage.getPackageBuildInterDependencies().iterator(); p.hasNext();) { GenPackage interdep = (GenPackage)p.next();
    stringBuffer.append(TEXT_119);
    stringBuffer.append(genPackage.getPackageInstanceVariable(interdep));
    stringBuffer.append(TEXT_120);
    }
    stringBuffer.append(TEXT_121);
    if (!genPackage.isLoadedInitialization()) {
    stringBuffer.append(TEXT_122);
    stringBuffer.append(genPackage.getPackageInterfaceName());
    stringBuffer.append(TEXT_123);
    }
    for (Iterator p=genPackage.getPackageBuildInterDependencies().iterator(); p.hasNext();) { GenPackage interdep = (GenPackage)p.next();
    stringBuffer.append(TEXT_124);
    stringBuffer.append(genPackage.getPackageInstanceVariable(interdep));
    stringBuffer.append(TEXT_125);
    }
    stringBuffer.append(TEXT_126);
    }
    if (genPackage.isLoadedInitialization() || !genPackage.getPackageLoadInterDependencies().isEmpty()) {
    stringBuffer.append(TEXT_127);
    if (genPackage.isLoadedInitialization()) {
    stringBuffer.append(TEXT_128);
    stringBuffer.append(genPackage.getPackageInterfaceName());
    stringBuffer.append(TEXT_129);
    }
    for (Iterator p=genPackage.getPackageLoadInterDependencies().iterator(); p.hasNext();) { GenPackage interdep = (GenPackage)p.next();
    stringBuffer.append(TEXT_130);
    stringBuffer.append(genPackage.getPackageInstanceVariable(interdep));
    stringBuffer.append(TEXT_131);
    }
    stringBuffer.append(TEXT_132);
    }
    if (genPackage.hasConstraints()) {
    stringBuffer.append(TEXT_133);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.EValidator"));
    stringBuffer.append(TEXT_134);
    stringBuffer.append(genPackage.getPackageInterfaceName());
    stringBuffer.append(TEXT_135);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.EValidator"));
    stringBuffer.append(TEXT_136);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.EValidator"));
    stringBuffer.append(TEXT_137);
    stringBuffer.append(genPackage.getImportedValidatorClassName());
    stringBuffer.append(TEXT_138);
    }
    if (!genPackage.isEcorePackage()) {
    stringBuffer.append(TEXT_139);
    stringBuffer.append(genPackage.getPackageInterfaceName());
    stringBuffer.append(TEXT_140);
    }
    stringBuffer.append(TEXT_141);
    stringBuffer.append(genPackage.getPackageInterfaceName());
    stringBuffer.append(TEXT_142);
    }
    if (isInterface) { // TODO REMOVE THIS BOGUS EMPTY LINE
    stringBuffer.append(TEXT_143);
    }
    for (Iterator i=genPackage.getGenClassifiers().iterator(); i.hasNext();) { GenClassifier genClassifier = (GenClassifier)i.next();
    if (isInterface) {
    stringBuffer.append(TEXT_144);
    if (genClassifier instanceof GenClass) { GenClass genClass = (GenClass)genClassifier;
    stringBuffer.append(TEXT_145);
    stringBuffer.append(genClass.getQualifiedInterfaceName());
    stringBuffer.append(TEXT_146);
    stringBuffer.append(genClass.getFormattedName());
    stringBuffer.append(TEXT_147);
    stringBuffer.append(genClass.getFormattedName());
    stringBuffer.append(TEXT_148);
    stringBuffer.append(genClass.getQualifiedInterfaceName());
    if (!genModel.isSuppressEMFModelTags() && (genClass.isExternalInterface() || genClass.isDynamic())) { boolean first = true; for (StringTokenizer stringTokenizer = new StringTokenizer(genClass.getModelInfo(), "\n\r"); stringTokenizer.hasMoreTokens(); ) { String modelInfo = stringTokenizer.nextToken(); if (first) { first = false;
    stringBuffer.append(TEXT_149);
    stringBuffer.append(modelInfo);
    } else {
    stringBuffer.append(TEXT_150);
    stringBuffer.append(modelInfo);
    }} if (first) {
    stringBuffer.append(TEXT_151);
    }}
    } else if (genClassifier instanceof GenEnum) { GenEnum genEnum = (GenEnum)genClassifier;
    stringBuffer.append(TEXT_152);
    stringBuffer.append(genEnum.getQualifiedName());
    stringBuffer.append(TEXT_153);
    stringBuffer.append(genEnum.getFormattedName());
    stringBuffer.append(TEXT_154);
    stringBuffer.append(genEnum.getFormattedName());
    stringBuffer.append(TEXT_155);
    stringBuffer.append(genEnum.getQualifiedName());
    } else if (genClassifier instanceof GenDataType) { GenDataType genDataType = (GenDataType)genClassifier;
    if (genDataType.isPrimitiveType() || genDataType.isArrayType()) {
    stringBuffer.append(TEXT_156);
    stringBuffer.append(genDataType.getFormattedName());
    stringBuffer.append(TEXT_157);
    } else {
    stringBuffer.append(TEXT_158);
    stringBuffer.append(genDataType.getQualifiedInstanceClassName());
    stringBuffer.append(TEXT_159);
    stringBuffer.append(genDataType.getFormattedName());
    stringBuffer.append(TEXT_160);
    }
    stringBuffer.append(TEXT_161);
    stringBuffer.append(genDataType.getFormattedName());
    stringBuffer.append(TEXT_162);
    if (!genDataType.isPrimitiveType() && !genDataType.isArrayType()) {
    stringBuffer.append(TEXT_163);
    stringBuffer.append(genDataType.getQualifiedInstanceClassName());
    }
    if (!genModel.isSuppressEMFModelTags()) {boolean first = true; for (StringTokenizer stringTokenizer = new StringTokenizer(genDataType.getModelInfo(), "\n\r"); stringTokenizer.hasMoreTokens(); ) { String modelInfo = stringTokenizer.nextToken(); if (first) { first = false;
    stringBuffer.append(TEXT_164);
    stringBuffer.append(modelInfo);
    } else {
    stringBuffer.append(TEXT_165);
    stringBuffer.append(modelInfo);
    }} if (first) {
    stringBuffer.append(TEXT_166);
    }}
    }
    stringBuffer.append(TEXT_167);
    } else {
    stringBuffer.append(TEXT_168);
    }
    if (isImplementation) {
    stringBuffer.append(TEXT_169);
    stringBuffer.append(genClassifier.getImportedMetaType());
    stringBuffer.append(TEXT_170);
    stringBuffer.append(genClassifier.getClassifierAccessorName());
    stringBuffer.append(TEXT_171);
    if (genPackage.isLoadedInitialization()) {
    stringBuffer.append(TEXT_172);
    stringBuffer.append(genClassifier.getClassifierInstanceName());
    stringBuffer.append(TEXT_173);
    stringBuffer.append(genClassifier.getClassifierInstanceName());
    stringBuffer.append(TEXT_174);
    stringBuffer.append(genClassifier.getImportedMetaType());
    stringBuffer.append(TEXT_175);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.EPackage"));
    stringBuffer.append(TEXT_176);
    stringBuffer.append(genPackage.getImportedPackageInterfaceName());
    stringBuffer.append(TEXT_177);
    stringBuffer.append(genPackage.getLocalClassifierIndex(genClassifier));
    stringBuffer.append(TEXT_178);
    }
    stringBuffer.append(TEXT_179);
    stringBuffer.append(genClassifier.getClassifierInstanceName());
    stringBuffer.append(TEXT_180);
    } else {
    stringBuffer.append(TEXT_181);
    stringBuffer.append(genClassifier.getImportedMetaType());
    stringBuffer.append(TEXT_182);
    stringBuffer.append(genClassifier.getClassifierAccessorName());
    stringBuffer.append(TEXT_183);
    }
    if (genClassifier instanceof GenClass) { GenClass genClass = (GenClass)genClassifier;
    for (Iterator f=genClass.getGenFeatures().iterator(); f.hasNext();) { GenFeature genFeature = (GenFeature)f.next();
    if (isInterface) {
    stringBuffer.append(TEXT_184);
    stringBuffer.append(genFeature.getFeatureKind());
    stringBuffer.append(TEXT_185);
    stringBuffer.append(genClass.getQualifiedInterfaceName());
    if (!genClass.isMapEntry() && !genFeature.isSuppressedGetVisibility()) {
    stringBuffer.append(TEXT_186);
    stringBuffer.append(genFeature.getGetAccessor());
    }
    stringBuffer.append(TEXT_187);
    stringBuffer.append(genFeature.getFormattedName());
    stringBuffer.append(TEXT_188);
    stringBuffer.append(genFeature.getFeatureKind());
    stringBuffer.append(TEXT_189);
    stringBuffer.append(genFeature.getFormattedName());
    stringBuffer.append(TEXT_190);
    stringBuffer.append(genClass.getQualifiedInterfaceName());
    if (!genClass.isMapEntry() && !genFeature.isSuppressedGetVisibility()) {
    stringBuffer.append(TEXT_191);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_192);
    }
    stringBuffer.append(TEXT_193);
    stringBuffer.append(genClass.getClassifierAccessorName());
    stringBuffer.append(TEXT_194);
    } else {
    stringBuffer.append(TEXT_195);
    }
    if (isImplementation) {
    stringBuffer.append(TEXT_196);
    stringBuffer.append(genFeature.getImportedMetaType());
    stringBuffer.append(TEXT_197);
    stringBuffer.append(genFeature.getFeatureAccessorName());
    stringBuffer.append(TEXT_198);
    if (!genPackage.isLoadedInitialization()) {
    stringBuffer.append(TEXT_199);
    stringBuffer.append(genFeature.getImportedMetaType());
    stringBuffer.append(TEXT_200);
    stringBuffer.append(genClass.getClassifierInstanceName());
    stringBuffer.append(TEXT_201);
    stringBuffer.append(genClass.getLocalFeatureIndex(genFeature));
    stringBuffer.append(TEXT_202);
    } else {
    stringBuffer.append(TEXT_203);
    stringBuffer.append(genFeature.getImportedMetaType());
    stringBuffer.append(TEXT_204);
    stringBuffer.append(genClassifier.getClassifierAccessorName());
    stringBuffer.append(TEXT_205);
    stringBuffer.append(genClass.getLocalFeatureIndex(genFeature));
    stringBuffer.append(TEXT_206);
    }
    stringBuffer.append(TEXT_207);
    } else {
    stringBuffer.append(TEXT_208);
    stringBuffer.append(genFeature.getImportedMetaType());
    stringBuffer.append(TEXT_209);
    stringBuffer.append(genFeature.getFeatureAccessorName());
    stringBuffer.append(TEXT_210);
    }
    stringBuffer.append(TEXT_211);
    }
    }
    }
    if (isInterface) {
    stringBuffer.append(TEXT_212);
    } else {
    stringBuffer.append(TEXT_213);
    }
    if (isImplementation) {
    stringBuffer.append(TEXT_214);
    stringBuffer.append(genPackage.getImportedFactoryInterfaceName());
    stringBuffer.append(TEXT_215);
    stringBuffer.append(genPackage.getFactoryInterfaceName());
    stringBuffer.append(TEXT_216);
    stringBuffer.append(genPackage.getImportedFactoryInterfaceName());
    stringBuffer.append(TEXT_217);
    } else {
    stringBuffer.append(TEXT_218);
    stringBuffer.append(genPackage.getFactoryInterfaceName());
    stringBuffer.append(TEXT_219);
    stringBuffer.append(genPackage.getFactoryInterfaceName());
    stringBuffer.append(TEXT_220);
    }
    stringBuffer.append(TEXT_221);
    if (isImplementation) {
    if (!genPackage.isLoadedInitialization()) {
    stringBuffer.append(TEXT_222);
    if (!genPackage.getGenClasses().isEmpty()) {
    stringBuffer.append(TEXT_223);
    for (Iterator c=genPackage.getGenClasses().iterator(); c.hasNext();) { GenClass genClass = (GenClass)c.next();
    stringBuffer.append(TEXT_224);
    stringBuffer.append(genClass.getClassifierInstanceName());
    stringBuffer.append(TEXT_225);
    stringBuffer.append(genClass.getMetaType());
    stringBuffer.append(TEXT_226);
    stringBuffer.append(genClass.getClassifierID());
    stringBuffer.append(TEXT_227);
    for (Iterator f=genClass.getGenFeatures().iterator(); f.hasNext();) { GenFeature genFeature = (GenFeature)f.next();
    stringBuffer.append(TEXT_228);
    stringBuffer.append(genFeature.getMetaType());
    stringBuffer.append(TEXT_229);
    stringBuffer.append(genClass.getClassifierInstanceName());
    stringBuffer.append(TEXT_230);
    stringBuffer.append(genClass.getFeatureID(genFeature));
    stringBuffer.append(TEXT_231);
    }
    if (c.hasNext()) {
    stringBuffer.append(TEXT_232);
    }
    }
    }
    if (!genPackage.getGenEnums().isEmpty()) {
    stringBuffer.append(TEXT_233);
    for (Iterator e=genPackage.getGenEnums().iterator(); e.hasNext();) { GenEnum genEnum = (GenEnum)e.next();
    stringBuffer.append(TEXT_234);
    stringBuffer.append(genEnum.getClassifierInstanceName());
    stringBuffer.append(TEXT_235);
    stringBuffer.append(genEnum.getClassifierID());
    stringBuffer.append(TEXT_236);
    }
    }
    if (!genPackage.getGenDataTypes().isEmpty()) {
    stringBuffer.append(TEXT_237);
    for (Iterator d=genPackage.getGenDataTypes().iterator(); d.hasNext();) { GenDataType genDataType = (GenDataType)d.next();
    stringBuffer.append(TEXT_238);
    stringBuffer.append(genDataType.getClassifierInstanceName());
    stringBuffer.append(TEXT_239);
    stringBuffer.append(genDataType.getClassifierID());
    stringBuffer.append(TEXT_240);
    }
    }
    stringBuffer.append(TEXT_241);
    if (!genPackage.getPackageInitializationDependencies().isEmpty()) {
    stringBuffer.append(TEXT_242);
    for (Iterator p=genPackage.getPackageInitializationDependencies().iterator(); p.hasNext();) { GenPackage dep = (GenPackage)p.next();
    stringBuffer.append(TEXT_243);
    stringBuffer.append(dep.getImportedPackageInterfaceName());
    stringBuffer.append(TEXT_244);
    stringBuffer.append(genPackage.getPackageInstanceVariable(dep));
    stringBuffer.append(TEXT_245);
    stringBuffer.append(dep.getImportedPackageInterfaceName());
    stringBuffer.append(TEXT_246);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.EPackage"));
    stringBuffer.append(TEXT_247);
    stringBuffer.append(dep.getImportedPackageInterfaceName());
    stringBuffer.append(TEXT_248);
    }
    }
    if (!genPackage.getSubGenPackages().isEmpty()) {
    stringBuffer.append(TEXT_249);
    for (Iterator p=genPackage.getSubGenPackages().iterator(); p.hasNext();) { GenPackage sub = (GenPackage)p.next();
    stringBuffer.append(TEXT_250);
    stringBuffer.append(genPackage.getPackageInstanceVariable(sub));
    stringBuffer.append(TEXT_251);
    }
    }
    if (!genPackage.getGenClasses().isEmpty()) { boolean firstOperationAssignment = true; 
    stringBuffer.append(TEXT_252);
    for (Iterator c=genPackage.getGenClasses().iterator(); c.hasNext();) { GenClass genClass = (GenClass)c.next();
    for (Iterator b=genClass.getBaseGenClasses().iterator(); b.hasNext();) { GenClass baseGenClass = (GenClass)b.next();
    stringBuffer.append(TEXT_253);
    stringBuffer.append(genClass.getClassifierInstanceName());
    stringBuffer.append(TEXT_254);
    stringBuffer.append(genPackage.getPackageInstanceVariable(baseGenClass.getGenPackage()));
    stringBuffer.append(TEXT_255);
    stringBuffer.append(baseGenClass.getClassifierAccessorName());
    stringBuffer.append(TEXT_256);
    }
    }
    stringBuffer.append(TEXT_257);
    for (Iterator c=genPackage.getGenClasses().iterator(); c.hasNext();) { GenClass genClass = (GenClass)c.next();
    stringBuffer.append(TEXT_258);
    stringBuffer.append(genClass.getClassifierInstanceName());
    stringBuffer.append(TEXT_259);
    if (genClass.isDynamic()) {
    stringBuffer.append(TEXT_260);
    } else {
    stringBuffer.append(genClass.getImportedInterfaceName());
    stringBuffer.append(TEXT_261);
    }
    stringBuffer.append(TEXT_262);
    stringBuffer.append(genClass.getName());
    stringBuffer.append(TEXT_263);
    stringBuffer.append(genClass.getAbstractFlag());
    stringBuffer.append(TEXT_264);
    stringBuffer.append(genClass.getInterfaceFlag());
    stringBuffer.append(TEXT_265);
    stringBuffer.append(genClass.getGeneratedInstanceClassFlag());
    stringBuffer.append(TEXT_266);
    stringBuffer.append(genModel.getNonNLS());
    for (Iterator f=genClass.getGenFeatures().iterator(); f.hasNext();) { GenFeature genFeature = (GenFeature)f.next();
    if (genFeature.isReferenceType()) { GenFeature reverseGenFeature = genFeature.getReverse();
    String reverse = reverseGenFeature == null ? "null" : genPackage.getPackageInstanceVariable(reverseGenFeature.getGenPackage()) + ".get" + reverseGenFeature.getFeatureAccessorName() + "()";
    stringBuffer.append(TEXT_267);
    stringBuffer.append(genFeature.getFeatureAccessorName());
    stringBuffer.append(TEXT_268);
    stringBuffer.append(genPackage.getPackageInstanceVariable(genFeature.getTypeGenPackage()));
    stringBuffer.append(TEXT_269);
    stringBuffer.append(genFeature.getTypeClassifierAccessorName());
    stringBuffer.append(TEXT_270);
    stringBuffer.append(reverse);
    stringBuffer.append(TEXT_271);
    stringBuffer.append(genFeature.getName());
    stringBuffer.append(TEXT_272);
    stringBuffer.append(genFeature.getDefaultValue());
    stringBuffer.append(TEXT_273);
    stringBuffer.append(genFeature.getLowerBound());
    stringBuffer.append(TEXT_274);
    stringBuffer.append(genFeature.getUpperBound());
    stringBuffer.append(TEXT_275);
    stringBuffer.append(genFeature.getContainerClass());
    stringBuffer.append(TEXT_276);
    stringBuffer.append(genFeature.getTransientFlag());
    stringBuffer.append(TEXT_277);
    stringBuffer.append(genFeature.getVolatileFlag());
    stringBuffer.append(TEXT_278);
    stringBuffer.append(genFeature.getChangeableFlag());
    stringBuffer.append(TEXT_279);
    stringBuffer.append(genFeature.getContainmentFlag());
    stringBuffer.append(TEXT_280);
    stringBuffer.append(genFeature.getResolveProxiesFlag());
    stringBuffer.append(TEXT_281);
    stringBuffer.append(genFeature.getUnsettableFlag());
    stringBuffer.append(TEXT_282);
    stringBuffer.append(genFeature.getUniqueFlag());
    stringBuffer.append(TEXT_283);
    stringBuffer.append(genFeature.getDerivedFlag());
    stringBuffer.append(TEXT_284);
    stringBuffer.append(genFeature.getOrderedFlag());
    stringBuffer.append(TEXT_285);
    stringBuffer.append(genModel.getNonNLS());
    stringBuffer.append(genModel.getNonNLS(genFeature.getDefaultValue(), 2));
    } else {
    stringBuffer.append(TEXT_286);
    stringBuffer.append(genFeature.getFeatureAccessorName());
    stringBuffer.append(TEXT_287);
    stringBuffer.append(genPackage.getPackageInstanceVariable(genFeature.getTypeGenPackage()));
    stringBuffer.append(TEXT_288);
    stringBuffer.append(genFeature.getTypeClassifierAccessorName());
    stringBuffer.append(TEXT_289);
    stringBuffer.append(genFeature.getName());
    stringBuffer.append(TEXT_290);
    stringBuffer.append(genFeature.getDefaultValue());
    stringBuffer.append(TEXT_291);
    stringBuffer.append(genFeature.getLowerBound());
    stringBuffer.append(TEXT_292);
    stringBuffer.append(genFeature.getUpperBound());
    stringBuffer.append(TEXT_293);
    stringBuffer.append(genFeature.getContainerClass());
    stringBuffer.append(TEXT_294);
    stringBuffer.append(genFeature.getTransientFlag());
    stringBuffer.append(TEXT_295);
    stringBuffer.append(genFeature.getVolatileFlag());
    stringBuffer.append(TEXT_296);
    stringBuffer.append(genFeature.getChangeableFlag());
    stringBuffer.append(TEXT_297);
    stringBuffer.append(genFeature.getUnsettableFlag());
    stringBuffer.append(TEXT_298);
    stringBuffer.append(genFeature.getIDFlag());
    stringBuffer.append(TEXT_299);
    stringBuffer.append(genFeature.getUniqueFlag());
    stringBuffer.append(TEXT_300);
    stringBuffer.append(genFeature.getDerivedFlag());
    stringBuffer.append(TEXT_301);
    stringBuffer.append(genFeature.getOrderedFlag());
    stringBuffer.append(TEXT_302);
    stringBuffer.append(genModel.getNonNLS());
    stringBuffer.append(genModel.getNonNLS(genFeature.getDefaultValue(), 2));
    }
    }
    for (Iterator o=genClass.getGenOperations().iterator(); o.hasNext();) { GenOperation genOperation = (GenOperation)o.next(); String prefix = ""; if (!genOperation.getGenParameters().isEmpty() || !genOperation.getGenExceptions().isEmpty()) { if (firstOperationAssignment) { firstOperationAssignment = false; prefix = genModel.getImportedName("org.eclipse.emf.ecore.EOperation") + " op = "; } else { prefix = "op = "; }} 
    stringBuffer.append(TEXT_303);
    if (!genOperation.isVoid()) {
    stringBuffer.append(TEXT_304);
    stringBuffer.append(prefix);
    stringBuffer.append(TEXT_305);
    stringBuffer.append(genClass.getClassifierInstanceName());
    stringBuffer.append(TEXT_306);
    stringBuffer.append(genPackage.getPackageInstanceVariable(genOperation.getTypeGenPackage()));
    stringBuffer.append(TEXT_307);
    stringBuffer.append(genOperation.getTypeClassifierAccessorName());
    stringBuffer.append(TEXT_308);
    stringBuffer.append(genOperation.getName());
    stringBuffer.append(TEXT_309);
    stringBuffer.append(genOperation.getLowerBound());
    stringBuffer.append(TEXT_310);
    stringBuffer.append(genOperation.getUpperBound());
    stringBuffer.append(TEXT_311);
    stringBuffer.append(genModel.getNonNLS());
    } else {
    stringBuffer.append(TEXT_312);
    stringBuffer.append(prefix);
    stringBuffer.append(TEXT_313);
    stringBuffer.append(genClass.getClassifierInstanceName());
    stringBuffer.append(TEXT_314);
    stringBuffer.append(genOperation.getName());
    stringBuffer.append(TEXT_315);
    stringBuffer.append(genModel.getNonNLS());
    }
    for (Iterator p=genOperation.getGenParameters().iterator(); p.hasNext();) { GenParameter genParameter = (GenParameter)p.next();
    stringBuffer.append(TEXT_316);
    stringBuffer.append(genPackage.getPackageInstanceVariable(genParameter.getTypeGenPackage()));
    stringBuffer.append(TEXT_317);
    stringBuffer.append(genParameter.getTypeClassifierAccessorName());
    stringBuffer.append(TEXT_318);
    stringBuffer.append(genParameter.getName());
    stringBuffer.append(TEXT_319);
    stringBuffer.append(genParameter.getLowerBound());
    stringBuffer.append(TEXT_320);
    stringBuffer.append(genParameter.getUpperBound());
    stringBuffer.append(TEXT_321);
    stringBuffer.append(genModel.getNonNLS());
    }
    for (Iterator p=genOperation.getGenExceptions().iterator(); p.hasNext();) { GenClassifier genException = (GenClassifier)p.next();
    stringBuffer.append(TEXT_322);
    stringBuffer.append(genPackage.getPackageInstanceVariable(genException.getGenPackage()));
    stringBuffer.append(TEXT_323);
    stringBuffer.append(genException.getClassifierAccessorName());
    stringBuffer.append(TEXT_324);
    }
    }
    if (c.hasNext()) {
    stringBuffer.append(TEXT_325);
    }
    }
    }
    if (!genPackage.getGenEnums().isEmpty()) {
    stringBuffer.append(TEXT_326);
    for (Iterator e=genPackage.getGenEnums().iterator(); e.hasNext();) { GenEnum genEnum = (GenEnum)e.next();
    stringBuffer.append(TEXT_327);
    stringBuffer.append(genEnum.getClassifierInstanceName());
    stringBuffer.append(TEXT_328);
    stringBuffer.append(genEnum.getImportedName());
    stringBuffer.append(TEXT_329);
    stringBuffer.append(genEnum.getName());
    stringBuffer.append(TEXT_330);
    stringBuffer.append(genModel.getNonNLS());
    for (Iterator l=genEnum.getGenEnumLiterals().iterator(); l.hasNext();) { GenEnumLiteral genEnumLiteral = (GenEnumLiteral)l.next();
    stringBuffer.append(TEXT_331);
    stringBuffer.append(genEnum.getClassifierInstanceName());
    stringBuffer.append(TEXT_332);
    stringBuffer.append(genEnum.getImportedName().equals(genEnum.getClassifierID()) ? genEnum.getQualifiedName() : genEnum.getImportedName());
    stringBuffer.append(TEXT_333);
    stringBuffer.append(genEnumLiteral.getEnumLiteralID());
    stringBuffer.append(TEXT_334);
    }
    if (e.hasNext()) {
    stringBuffer.append(TEXT_335);
    }
    }
    }
    if (!genPackage.getGenDataTypes().isEmpty()) {
    stringBuffer.append(TEXT_336);
    for (Iterator d=genPackage.getGenDataTypes().iterator(); d.hasNext();) { GenDataType genDataType = (GenDataType)d.next();
    stringBuffer.append(TEXT_337);
    stringBuffer.append(genDataType.getClassifierInstanceName());
    stringBuffer.append(TEXT_338);
    stringBuffer.append(genDataType.getImportedInstanceClassName());
    stringBuffer.append(TEXT_339);
    stringBuffer.append(genDataType.getName());
    stringBuffer.append(TEXT_340);
    stringBuffer.append(genDataType.getSerializableFlag());
    stringBuffer.append(TEXT_341);
    stringBuffer.append(genDataType.getGeneratedInstanceClassFlag());
    stringBuffer.append(TEXT_342);
    stringBuffer.append(genModel.getNonNLS());
    }
    }
    if (genPackage.getSuperGenPackage() == null) {
    stringBuffer.append(TEXT_343);
    }
    if (!genPackage.isEcorePackage() && !genPackage.getAnnotationSources().isEmpty()) {
    stringBuffer.append(TEXT_344);
    for (Iterator i = genPackage.getAnnotationSources().iterator(); i.hasNext();) { String annotationSource = (String)i.next();
    stringBuffer.append(TEXT_345);
    stringBuffer.append(annotationSource);
    stringBuffer.append(TEXT_346);
    stringBuffer.append(genPackage.getAnnotationSourceIdentifier(annotationSource));
    stringBuffer.append(TEXT_347);
    }
    }
    stringBuffer.append(TEXT_348);
    for (Iterator i = genPackage.getAnnotationSources().iterator(); i.hasNext();) { String annotationSource = (String)i.next();
    stringBuffer.append(TEXT_349);
    stringBuffer.append(annotationSource);
    stringBuffer.append(TEXT_350);
    stringBuffer.append(genPackage.getAnnotationSourceIdentifier(annotationSource));
    stringBuffer.append(TEXT_351);
    if (annotationSource == null) {
    stringBuffer.append(TEXT_352);
    } else {
    stringBuffer.append(TEXT_353);
    stringBuffer.append(annotationSource);
    stringBuffer.append(TEXT_354);
    stringBuffer.append(genModel.getNonNLS());
    }
    stringBuffer.append(TEXT_355);
    for (Iterator j = genPackage.getAllAnnotations().iterator(); j.hasNext();) { EAnnotation eAnnotation = (EAnnotation)j.next();
    stringBuffer.append(TEXT_356);
    if (annotationSource == null ? eAnnotation.getSource() == null : annotationSource.equals(eAnnotation.getSource())) {
    stringBuffer.append(TEXT_357);
    stringBuffer.append(genPackage.getAnnotatedModelElementAccessor(eAnnotation));
    stringBuffer.append(TEXT_358);
    for (Iterator k = eAnnotation.getDetails().iterator(); k.hasNext();) { Map.Entry detail = (Map.Entry)k.next(); String key = Literals.toStringLiteral((String)detail.getKey(), genModel); String value = Literals.toStringLiteral((String)detail.getValue(), genModel);
    stringBuffer.append(TEXT_359);
    stringBuffer.append(key);
    stringBuffer.append(TEXT_360);
    stringBuffer.append(value);
    stringBuffer.append(k.hasNext() ? "," : "");
    stringBuffer.append(genModel.getNonNLS(key + value));
    }
    stringBuffer.append(TEXT_361);
    }
    for (Iterator k = genPackage.getAllNestedAnnotations(eAnnotation).iterator(); k.hasNext(); ) { EAnnotation nestedEAnnotation = (EAnnotation)k.next(); String nestedAnnotationSource = nestedEAnnotation.getSource(); int depth = 1; for (EObject eContainer = nestedEAnnotation.eContainer(); eContainer != eAnnotation; eContainer = eContainer.eContainer()) { ++depth; }
    stringBuffer.append(TEXT_362);
    stringBuffer.append(genPackage.getAnnotatedModelElementAccessor(eAnnotation));
    stringBuffer.append(TEXT_363);
    stringBuffer.append(depth);
    stringBuffer.append(TEXT_364);
    if (nestedAnnotationSource == null) {
    stringBuffer.append(TEXT_365);
    } else {
    stringBuffer.append(TEXT_366);
    stringBuffer.append(nestedAnnotationSource);
    stringBuffer.append(TEXT_367);
    stringBuffer.append(genModel.getNonNLS());
    }
    stringBuffer.append(TEXT_368);
    for (Iterator l = nestedEAnnotation.getDetails().iterator(); l.hasNext();) { Map.Entry detail = (Map.Entry)l.next(); String key = Literals.toStringLiteral((String)detail.getKey(), genModel); String value = Literals.toStringLiteral((String)detail.getValue(), genModel);
    stringBuffer.append(TEXT_369);
    stringBuffer.append(key);
    stringBuffer.append(TEXT_370);
    stringBuffer.append(value);
    stringBuffer.append(l.hasNext() ? "," : "");
    stringBuffer.append(genModel.getNonNLS(key + value));
    }
    stringBuffer.append(TEXT_371);
    }
    for (Iterator k = genPackage.getAllNestedAnnotations(eAnnotation).iterator(); k.hasNext(); ) { EAnnotation nestedEAnnotation = (EAnnotation)k.next(); String nestedAnnotationSource = nestedEAnnotation.getSource(); int depth = 1; for (EObject eContainer = nestedEAnnotation.eContainer(); eContainer != eAnnotation; eContainer = eContainer.eContainer()) { ++depth; }
    stringBuffer.append(TEXT_372);
    stringBuffer.append(genPackage.getAnnotatedModelElementAccessor(eAnnotation));
    stringBuffer.append(TEXT_373);
    stringBuffer.append(depth);
    stringBuffer.append(TEXT_374);
    if (nestedAnnotationSource == null) {
    stringBuffer.append(TEXT_375);
    } else {
    stringBuffer.append(TEXT_376);
    stringBuffer.append(nestedAnnotationSource);
    stringBuffer.append(TEXT_377);
    stringBuffer.append(genModel.getNonNLS());
    }
    stringBuffer.append(TEXT_378);
    for (Iterator l = nestedEAnnotation.getDetails().iterator(); l.hasNext();) { Map.Entry detail = (Map.Entry)l.next(); String key = Literals.toStringLiteral((String)detail.getKey(), genModel); String value = Literals.toStringLiteral((String)detail.getValue(), genModel);
    stringBuffer.append(TEXT_379);
    stringBuffer.append(key);
    stringBuffer.append(TEXT_380);
    stringBuffer.append(value);
    stringBuffer.append(l.hasNext() ? "," : "");
    stringBuffer.append(genModel.getNonNLS(key + value));
    }
    stringBuffer.append(TEXT_381);
    }
    }
    stringBuffer.append(TEXT_382);
    }
    } else {
    if (genPackage.isLoadingInitialization()) {
    stringBuffer.append(TEXT_383);
    stringBuffer.append(genModel.getImportedName("java.net.URL"));
    stringBuffer.append(TEXT_384);
    stringBuffer.append(genModel.getNonNLS());
    stringBuffer.append(TEXT_385);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.util.URI"));
    stringBuffer.append(TEXT_386);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.util.URI"));
    stringBuffer.append(TEXT_387);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.resource.Resource"));
    stringBuffer.append(TEXT_388);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.xmi.impl.EcoreResourceFactoryImpl"));
    stringBuffer.append(TEXT_389);
    stringBuffer.append(genModel.getImportedName("java.io.IOException"));
    stringBuffer.append(TEXT_390);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.util.WrappedException"));
    stringBuffer.append(TEXT_391);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.EPackage"));
    stringBuffer.append(TEXT_392);
    }
    stringBuffer.append(TEXT_393);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.EClassifier"));
    stringBuffer.append(TEXT_394);
    stringBuffer.append(genPackage.getInterfacePackageName());
    stringBuffer.append(TEXT_395);
    stringBuffer.append(genModel.getNonNLS());
    stringBuffer.append(TEXT_396);
    }
    }
    if (isInterface && genPackage.isLiteralsInterface()) {
    stringBuffer.append(TEXT_397);
    if (isImplementation) {
    stringBuffer.append(TEXT_398);
    }
    stringBuffer.append(TEXT_399);
    for (Iterator i=genPackage.getGenClassifiers().iterator(); i.hasNext();) { GenClassifier genClassifier = (GenClassifier)i.next();
    stringBuffer.append(TEXT_400);
    if (genClassifier instanceof GenClass) { GenClass genClass = (GenClass)genClassifier;
    if (!genClass.isInterface()) {
    stringBuffer.append(TEXT_401);
    stringBuffer.append(genClass.getQualifiedClassName());
    stringBuffer.append(TEXT_402);
    stringBuffer.append(genClass.getFormattedName());
    stringBuffer.append(TEXT_403);
    stringBuffer.append(genClass.getQualifiedClassName());
    } else {
    stringBuffer.append(TEXT_404);
    stringBuffer.append(genClass.getQualifiedInterfaceName());
    stringBuffer.append(TEXT_405);
    stringBuffer.append(genClass.getFormattedName());
    stringBuffer.append(TEXT_406);
    stringBuffer.append(genClass.getQualifiedInterfaceName());
    }
    } else if (genClassifier instanceof GenEnum) { GenEnum genEnum = (GenEnum)genClassifier;
    stringBuffer.append(TEXT_407);
    stringBuffer.append(genEnum.getQualifiedName());
    stringBuffer.append(TEXT_408);
    stringBuffer.append(genEnum.getFormattedName());
    stringBuffer.append(TEXT_409);
    stringBuffer.append(genEnum.getQualifiedName());
    } else if (genClassifier instanceof GenDataType) { GenDataType genDataType = (GenDataType)genClassifier;
    stringBuffer.append(TEXT_410);
    stringBuffer.append(genDataType.getFormattedName());
    stringBuffer.append(TEXT_411);
    if (!genDataType.isPrimitiveType() && !genDataType.isArrayType()) {
    stringBuffer.append(TEXT_412);
    stringBuffer.append(genDataType.getQualifiedInstanceClassName());
    }
    }
    stringBuffer.append(TEXT_413);
    stringBuffer.append(genPackage.getQualifiedPackageClassName());
    stringBuffer.append(TEXT_414);
    stringBuffer.append(genClassifier.getClassifierAccessorName());
    stringBuffer.append(TEXT_415);
    stringBuffer.append(publicStaticFinalFlag);
    stringBuffer.append(genClassifier.getImportedMetaType());
    stringBuffer.append(TEXT_416);
    stringBuffer.append(genPackage.getClassifierID(genClassifier));
    stringBuffer.append(TEXT_417);
    stringBuffer.append(genClassifier.getClassifierAccessorName());
    stringBuffer.append(TEXT_418);
    if (genClassifier instanceof GenClass) { GenClass genClass = (GenClass)genClassifier;
    for (Iterator f=genClass.getGenFeatures().iterator(); f.hasNext();) { GenFeature genFeature = (GenFeature)f.next();
    stringBuffer.append(TEXT_419);
    stringBuffer.append(genFeature.getFormattedName());
    stringBuffer.append(TEXT_420);
    stringBuffer.append(genFeature.getFeatureKind());
    stringBuffer.append(TEXT_421);
    stringBuffer.append(publicStaticFinalFlag);
    stringBuffer.append(genFeature.getImportedMetaType());
    stringBuffer.append(TEXT_422);
    stringBuffer.append(genClass.getFeatureID(genFeature));
    stringBuffer.append(TEXT_423);
    stringBuffer.append(genFeature.getFeatureAccessorName());
    stringBuffer.append(TEXT_424);
    }
    }
    }
    stringBuffer.append(TEXT_425);
    }
    stringBuffer.append(TEXT_426);
    stringBuffer.append(isInterface ? genPackage.getPackageInterfaceName() : genPackage.getPackageClassName());
    genModel.emitSortedImports();
    stringBuffer.append(TEXT_427);
    return stringBuffer.toString();
  }
}
