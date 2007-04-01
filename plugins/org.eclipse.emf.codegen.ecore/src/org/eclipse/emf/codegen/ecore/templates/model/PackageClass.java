package org.eclipse.emf.codegen.ecore.templates.model;

import java.util.*;
import org.eclipse.emf.ecore.*;
import org.eclipse.emf.ecore.util.EcoreUtil;
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
  protected final String TEXT_241 = NL + "\t}" + NL + "" + NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */" + NL + "\tprivate boolean isInitialized = false;" + NL;
  protected final String TEXT_242 = NL + "\t/**" + NL + "\t * Complete the initialization of the package and its meta-model.  This" + NL + "\t * method is guarded to have no affect on any invocation but its first." + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */" + NL + "\tpublic void initializePackageContents()" + NL + "\t{" + NL + "\t\tif (isInitialized) return;" + NL + "\t\tisInitialized = true;" + NL + "" + NL + "\t\t// Initialize package" + NL + "\t\tsetName(eNAME);" + NL + "\t\tsetNsPrefix(eNS_PREFIX);" + NL + "\t\tsetNsURI(eNS_URI);";
  protected final String TEXT_243 = NL + NL + "\t\t// Obtain other dependent packages";
  protected final String TEXT_244 = NL + "\t\t";
  protected final String TEXT_245 = " ";
  protected final String TEXT_246 = " = (";
  protected final String TEXT_247 = ")";
  protected final String TEXT_248 = ".Registry.INSTANCE.getEPackage(";
  protected final String TEXT_249 = ".eNS_URI);";
  protected final String TEXT_250 = NL + NL + "\t\t// Add subpackages";
  protected final String TEXT_251 = NL + "\t\tgetESubpackages().add(";
  protected final String TEXT_252 = ");";
  protected final String TEXT_253 = NL + NL + "\t\t// Create type parameters";
  protected final String TEXT_254 = NL + "\t\t";
  protected final String TEXT_255 = " ";
  protected final String TEXT_256 = "_";
  protected final String TEXT_257 = " = addETypeParameter(";
  protected final String TEXT_258 = ", \"";
  protected final String TEXT_259 = "\");";
  protected final String TEXT_260 = NL + "\t\taddETypeParameter(";
  protected final String TEXT_261 = ", \"";
  protected final String TEXT_262 = "\");";
  protected final String TEXT_263 = NL + NL + "\t\t// Set bounds for type parameters";
  protected final String TEXT_264 = NL + "\t\t";
  protected final String TEXT_265 = "g";
  protected final String TEXT_266 = " = createEGenericType(";
  protected final String TEXT_267 = ");";
  protected final String TEXT_268 = NL + "\t\tg";
  protected final String TEXT_269 = ".";
  protected final String TEXT_270 = "(g";
  protected final String TEXT_271 = ");";
  protected final String TEXT_272 = NL + "\t\t";
  protected final String TEXT_273 = "_";
  protected final String TEXT_274 = ".getEBounds().add(g1);";
  protected final String TEXT_275 = NL + NL + "\t\t// Add supertypes to classes";
  protected final String TEXT_276 = NL + "\t\t";
  protected final String TEXT_277 = ".getESuperTypes().add(";
  protected final String TEXT_278 = ".get";
  protected final String TEXT_279 = "());";
  protected final String TEXT_280 = NL + "\t\t";
  protected final String TEXT_281 = "g";
  protected final String TEXT_282 = " = createEGenericType(";
  protected final String TEXT_283 = ");";
  protected final String TEXT_284 = NL + "\t\tg";
  protected final String TEXT_285 = ".";
  protected final String TEXT_286 = "(g";
  protected final String TEXT_287 = ");";
  protected final String TEXT_288 = NL + "\t\t";
  protected final String TEXT_289 = ".getEGenericSuperTypes().add(g1);";
  protected final String TEXT_290 = NL + NL + "\t\t// Initialize classes and features; add operations and parameters";
  protected final String TEXT_291 = NL + "\t\tinitEClass(";
  protected final String TEXT_292 = ", ";
  protected final String TEXT_293 = "null";
  protected final String TEXT_294 = ".class";
  protected final String TEXT_295 = ", \"";
  protected final String TEXT_296 = "\", ";
  protected final String TEXT_297 = ", ";
  protected final String TEXT_298 = ", ";
  protected final String TEXT_299 = ", \"";
  protected final String TEXT_300 = "\"";
  protected final String TEXT_301 = ");";
  protected final String TEXT_302 = NL + "\t\t";
  protected final String TEXT_303 = "g";
  protected final String TEXT_304 = " = createEGenericType(";
  protected final String TEXT_305 = ");";
  protected final String TEXT_306 = NL + "\t\tg";
  protected final String TEXT_307 = ".";
  protected final String TEXT_308 = "(g";
  protected final String TEXT_309 = ");";
  protected final String TEXT_310 = NL + "\t\tinitEReference(get";
  protected final String TEXT_311 = "(), ";
  protected final String TEXT_312 = "g1";
  protected final String TEXT_313 = ".get";
  protected final String TEXT_314 = "()";
  protected final String TEXT_315 = ", ";
  protected final String TEXT_316 = ", \"";
  protected final String TEXT_317 = "\", ";
  protected final String TEXT_318 = ", ";
  protected final String TEXT_319 = ", ";
  protected final String TEXT_320 = ", ";
  protected final String TEXT_321 = ", ";
  protected final String TEXT_322 = ", ";
  protected final String TEXT_323 = ", ";
  protected final String TEXT_324 = ", ";
  protected final String TEXT_325 = ", ";
  protected final String TEXT_326 = ", ";
  protected final String TEXT_327 = ", ";
  protected final String TEXT_328 = ", ";
  protected final String TEXT_329 = ", ";
  protected final String TEXT_330 = ");";
  protected final String TEXT_331 = NL + "\t\tget";
  protected final String TEXT_332 = "().getEKeys().add(";
  protected final String TEXT_333 = ".get";
  protected final String TEXT_334 = "());";
  protected final String TEXT_335 = NL + "\t\tinitEAttribute(get";
  protected final String TEXT_336 = "(), ";
  protected final String TEXT_337 = "g1";
  protected final String TEXT_338 = ".get";
  protected final String TEXT_339 = "()";
  protected final String TEXT_340 = ", \"";
  protected final String TEXT_341 = "\", ";
  protected final String TEXT_342 = ", ";
  protected final String TEXT_343 = ", ";
  protected final String TEXT_344 = ", ";
  protected final String TEXT_345 = ", ";
  protected final String TEXT_346 = ", ";
  protected final String TEXT_347 = ", ";
  protected final String TEXT_348 = ", ";
  protected final String TEXT_349 = ", ";
  protected final String TEXT_350 = ", ";
  protected final String TEXT_351 = ", ";
  protected final String TEXT_352 = ", ";
  protected final String TEXT_353 = ");";
  protected final String TEXT_354 = NL;
  protected final String TEXT_355 = NL + "\t\t";
  protected final String TEXT_356 = "addEOperation(";
  protected final String TEXT_357 = ", ";
  protected final String TEXT_358 = "null";
  protected final String TEXT_359 = ".get";
  protected final String TEXT_360 = "()";
  protected final String TEXT_361 = ", \"";
  protected final String TEXT_362 = "\", ";
  protected final String TEXT_363 = ", ";
  protected final String TEXT_364 = ");";
  protected final String TEXT_365 = NL + "\t\t";
  protected final String TEXT_366 = "addEOperation(";
  protected final String TEXT_367 = ", null, \"";
  protected final String TEXT_368 = "\");";
  protected final String TEXT_369 = NL + "\t\t";
  protected final String TEXT_370 = "addETypeParameter(op, \"";
  protected final String TEXT_371 = "\");";
  protected final String TEXT_372 = NL + "\t\t";
  protected final String TEXT_373 = "g";
  protected final String TEXT_374 = " = createEGenericType(";
  protected final String TEXT_375 = ");";
  protected final String TEXT_376 = NL + "\t\tg";
  protected final String TEXT_377 = ".";
  protected final String TEXT_378 = "(g";
  protected final String TEXT_379 = ");";
  protected final String TEXT_380 = NL + "\t\tt";
  protected final String TEXT_381 = ".getEBounds().add(g1);";
  protected final String TEXT_382 = NL + "\t\t";
  protected final String TEXT_383 = "g";
  protected final String TEXT_384 = " = createEGenericType(";
  protected final String TEXT_385 = ");";
  protected final String TEXT_386 = NL + "\t\tg";
  protected final String TEXT_387 = ".";
  protected final String TEXT_388 = "(g";
  protected final String TEXT_389 = ");";
  protected final String TEXT_390 = NL + "\t\taddEParameter(op, ";
  protected final String TEXT_391 = "g1";
  protected final String TEXT_392 = ".get";
  protected final String TEXT_393 = "()";
  protected final String TEXT_394 = ", \"";
  protected final String TEXT_395 = "\", ";
  protected final String TEXT_396 = ", ";
  protected final String TEXT_397 = ");";
  protected final String TEXT_398 = NL + "\t\t";
  protected final String TEXT_399 = "g";
  protected final String TEXT_400 = " = createEGenericType(";
  protected final String TEXT_401 = ");";
  protected final String TEXT_402 = NL + "\t\tg";
  protected final String TEXT_403 = ".";
  protected final String TEXT_404 = "(g";
  protected final String TEXT_405 = ");";
  protected final String TEXT_406 = NL + "\t\taddEException(op, g";
  protected final String TEXT_407 = ");";
  protected final String TEXT_408 = NL + "\t\taddEException(op, ";
  protected final String TEXT_409 = ".get";
  protected final String TEXT_410 = "());";
  protected final String TEXT_411 = NL + "\t\t";
  protected final String TEXT_412 = "g";
  protected final String TEXT_413 = " = createEGenericType(";
  protected final String TEXT_414 = ");";
  protected final String TEXT_415 = NL + "\t\tg";
  protected final String TEXT_416 = ".";
  protected final String TEXT_417 = "(g";
  protected final String TEXT_418 = ");";
  protected final String TEXT_419 = NL + "\t\tinitEOperation(op, g1);";
  protected final String TEXT_420 = NL;
  protected final String TEXT_421 = NL + NL + "\t\t// Initialize enums and add enum literals";
  protected final String TEXT_422 = NL + "\t\tinitEEnum(";
  protected final String TEXT_423 = ", ";
  protected final String TEXT_424 = ".class, \"";
  protected final String TEXT_425 = "\");";
  protected final String TEXT_426 = NL + "\t\taddEEnumLiteral(";
  protected final String TEXT_427 = ", ";
  protected final String TEXT_428 = ".";
  protected final String TEXT_429 = ");";
  protected final String TEXT_430 = NL;
  protected final String TEXT_431 = NL + NL + "\t\t// Initialize data types";
  protected final String TEXT_432 = NL + "\t\tinitEDataType(";
  protected final String TEXT_433 = ", ";
  protected final String TEXT_434 = ".class, \"";
  protected final String TEXT_435 = "\", ";
  protected final String TEXT_436 = ", ";
  protected final String TEXT_437 = ", \"";
  protected final String TEXT_438 = "\"";
  protected final String TEXT_439 = ");";
  protected final String TEXT_440 = NL + NL + "\t\t// Create resource" + NL + "\t\tcreateResource(eNS_URI);";
  protected final String TEXT_441 = NL + NL + "\t\t// Create annotations";
  protected final String TEXT_442 = NL + "\t\t// ";
  protected final String TEXT_443 = NL + "\t\tcreate";
  protected final String TEXT_444 = "Annotations();";
  protected final String TEXT_445 = NL + "\t}" + NL;
  protected final String TEXT_446 = NL + "\t/**" + NL + "\t * Initializes the annotations for <b>";
  protected final String TEXT_447 = "</b>." + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */" + NL + "\tprotected void create";
  protected final String TEXT_448 = "Annotations()" + NL + "\t{" + NL + "\t\tString source = ";
  protected final String TEXT_449 = "null;";
  protected final String TEXT_450 = "\"";
  protected final String TEXT_451 = "\";";
  protected final String TEXT_452 = "\t";
  protected final String TEXT_453 = "\t" + NL + "\t\taddAnnotation" + NL + "\t\t  (";
  protected final String TEXT_454 = ", " + NL + "\t\t   source, " + NL + "\t\t   new String[] " + NL + "\t\t   {";
  protected final String TEXT_455 = NL + "\t\t\t ";
  protected final String TEXT_456 = ", ";
  protected final String TEXT_457 = NL + "\t\t   });";
  protected final String TEXT_458 = NL + "\t\taddAnnotation" + NL + "\t\t  (";
  protected final String TEXT_459 = ", " + NL + "\t\t   ";
  protected final String TEXT_460 = "," + NL + "\t\t   ";
  protected final String TEXT_461 = "null,";
  protected final String TEXT_462 = "\"";
  protected final String TEXT_463 = "\",";
  protected final String TEXT_464 = NL + "\t\t   new String[] " + NL + "\t\t   {";
  protected final String TEXT_465 = NL + "\t\t    ";
  protected final String TEXT_466 = ", ";
  protected final String TEXT_467 = NL + "\t\t   });";
  protected final String TEXT_468 = NL + "\t\taddAnnotation" + NL + "\t\t  (";
  protected final String TEXT_469 = ", " + NL + "\t\t   ";
  protected final String TEXT_470 = "," + NL + "\t\t   ";
  protected final String TEXT_471 = "null,";
  protected final String TEXT_472 = "\"";
  protected final String TEXT_473 = "\",";
  protected final String TEXT_474 = NL + "\t\t   new String[] " + NL + "\t\t   {";
  protected final String TEXT_475 = NL + "\t\t    ";
  protected final String TEXT_476 = ", ";
  protected final String TEXT_477 = NL + "\t\t   });";
  protected final String TEXT_478 = NL + "\t}" + NL;
  protected final String TEXT_479 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */" + NL + "\tprivate boolean isLoaded = false;" + NL + "" + NL + "\t/**" + NL + "\t * Laods the package and any sub-packages from their serialized form." + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */" + NL + "\tpublic void loadPackage()" + NL + "\t{" + NL + "\t\tif (isLoaded) return;" + NL + "\t\tisLoaded = true;" + NL + "" + NL + "\t\t";
  protected final String TEXT_480 = " url = getClass().getResource(packageFilename);" + NL + "\t\tif (url == null)" + NL + "\t\t{" + NL + "\t\t\tthrow new RuntimeException(\"Missing serialized package: \" + packageFilename);";
  protected final String TEXT_481 = NL + "\t\t}" + NL + "\t\t";
  protected final String TEXT_482 = " uri = ";
  protected final String TEXT_483 = ".createURI(url.toString());" + NL + "\t\t";
  protected final String TEXT_484 = " resource = new ";
  protected final String TEXT_485 = "().createResource(uri);" + NL + "\t\ttry" + NL + "\t\t{" + NL + "\t\t\tresource.load(null);" + NL + "\t\t}" + NL + "\t\tcatch (";
  protected final String TEXT_486 = " exception)" + NL + "\t\t{" + NL + "\t\t\tthrow new ";
  protected final String TEXT_487 = "(exception);" + NL + "\t\t}" + NL + "\t\tinitializeFromLoadedEPackage(this, (";
  protected final String TEXT_488 = ")resource.getContents().get(0));" + NL + "\t\tcreateResource(eNS_URI);" + NL + "\t}" + NL;
  protected final String TEXT_489 = NL + NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */" + NL + "\tprivate boolean isFixed = false;" + NL + "" + NL + "\t/**" + NL + "\t * Fixes up the loaded package, to make it appear as if it had been programmatically built." + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */" + NL + "\tpublic void fixPackageContents()" + NL + "\t{" + NL + "\t\tif (isFixed) return;" + NL + "\t\tisFixed = true;" + NL + "\t\tfixEClassifiers();" + NL + "\t}" + NL + "" + NL + "\t/**" + NL + "\t * Sets the instance class on the given classifier." + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */";
  protected final String TEXT_490 = NL + "\t@Override";
  protected final String TEXT_491 = NL + "\tprotected void fixInstanceClass(";
  protected final String TEXT_492 = " eClassifier)" + NL + "\t{" + NL + "\t\tif (eClassifier.getInstanceClassName() == null)" + NL + "\t\t{";
  protected final String TEXT_493 = NL + "\t\t\teClassifier.setInstanceClassName(\"";
  protected final String TEXT_494 = ".\" + eClassifier.getName());";
  protected final String TEXT_495 = NL + "\t\t\tsetGeneratedClassName(eClassifier);";
  protected final String TEXT_496 = NL + "\t\t\tswitch (eClassifier.getClassifierID())" + NL + "\t\t\t{";
  protected final String TEXT_497 = NL + "\t\t\t\tcase ";
  protected final String TEXT_498 = ":";
  protected final String TEXT_499 = NL + "\t\t\t\t{" + NL + "\t\t\t\t\tbreak;" + NL + "\t\t\t\t}" + NL + "\t\t\t\tdefault:" + NL + "\t\t\t\t{" + NL + "\t\t\t\t\teClassifier.setInstanceClassName(\"";
  protected final String TEXT_500 = ".\" + eClassifier.getName());";
  protected final String TEXT_501 = NL + "\t\t\t\t\tsetGeneratedClassName(eClassifier);" + NL + "\t\t\t\t\tbreak;" + NL + "\t\t\t\t}" + NL + "\t\t\t}";
  protected final String TEXT_502 = NL + "\t\t}" + NL + "\t}" + NL;
  protected final String TEXT_503 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * Defines literals for the meta objects that represent" + NL + "\t * <ul>" + NL + "\t *   <li>each class,</li>" + NL + "\t *   <li>each feature of each class,</li>" + NL + "\t *   <li>each enum,</li>" + NL + "\t *   <li>and each data type</li>" + NL + "\t * </ul>" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */" + NL + "\t";
  protected final String TEXT_504 = "public ";
  protected final String TEXT_505 = "interface Literals" + NL + "\t{";
  protected final String TEXT_506 = NL + "\t\t/**";
  protected final String TEXT_507 = NL + "\t\t * The meta object literal for the '{@link ";
  protected final String TEXT_508 = " <em>";
  protected final String TEXT_509 = "</em>}' class." + NL + "\t\t * <!-- begin-user-doc -->" + NL + "\t\t * <!-- end-user-doc -->" + NL + "\t\t * @see ";
  protected final String TEXT_510 = NL + "\t\t * The meta object literal for the '{@link ";
  protected final String TEXT_511 = " <em>";
  protected final String TEXT_512 = "</em>}' class." + NL + "\t\t * <!-- begin-user-doc -->" + NL + "\t\t * <!-- end-user-doc -->" + NL + "\t\t * @see ";
  protected final String TEXT_513 = NL + "\t\t * The meta object literal for the '{@link ";
  protected final String TEXT_514 = " <em>";
  protected final String TEXT_515 = "</em>}' enum." + NL + "\t\t * <!-- begin-user-doc -->" + NL + "\t\t * <!-- end-user-doc -->" + NL + "\t\t * @see ";
  protected final String TEXT_516 = NL + "\t\t * The meta object literal for the '<em>";
  protected final String TEXT_517 = "</em>' data type." + NL + "\t\t * <!-- begin-user-doc -->" + NL + "\t\t * <!-- end-user-doc -->";
  protected final String TEXT_518 = NL + "\t\t * @see ";
  protected final String TEXT_519 = NL + "\t\t * @see ";
  protected final String TEXT_520 = "#get";
  protected final String TEXT_521 = "()" + NL + "\t\t * @generated" + NL + "\t\t */" + NL + "\t\t";
  protected final String TEXT_522 = " ";
  protected final String TEXT_523 = " = eINSTANCE.get";
  protected final String TEXT_524 = "();" + NL;
  protected final String TEXT_525 = NL + "\t\t/**" + NL + "\t\t * The meta object literal for the '<em><b>";
  protected final String TEXT_526 = "</b></em>' ";
  protected final String TEXT_527 = " feature." + NL + "\t\t * <!-- begin-user-doc -->" + NL + "\t\t * <!-- end-user-doc -->" + NL + "\t\t * @generated" + NL + "\t\t */" + NL + "\t\t";
  protected final String TEXT_528 = " ";
  protected final String TEXT_529 = " = eINSTANCE.get";
  protected final String TEXT_530 = "();" + NL;
  protected final String TEXT_531 = NL + "\t}" + NL;
  protected final String TEXT_532 = NL + "} //";
  protected final String TEXT_533 = NL;

  public String generate(Object argument)
  {
    final StringBuffer stringBuffer = new StringBuffer();
    
/**
 * <copyright>
 *
 * Copyright (c) 2002-2006 IBM Corporation and others.
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

    final GenPackage genPackage = (GenPackage)((Object[])argument)[0]; final GenModel genModel=genPackage.getGenModel();
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
    for (GenClassifier genClassifier : genPackage.getOrderedGenClassifiers()) genModel.addPseudoImport(genPackage.getQualifiedPackageInterfaceName() + "." + genPackage.getClassifierID(genClassifier));
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
    for (GenClassifier genClassifier : genPackage.getOrderedGenClassifiers()) {
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
    for (GenFeature genFeature : genClass.getAllGenFeatures()) {
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
    for (GenClassifier genClassifier : genPackage.getGenClassifiers()) {
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
    for (GenPackage dep : genPackage.getPackageSimpleDependencies()) {
    stringBuffer.append(TEXT_95);
    stringBuffer.append(dep.getImportedPackageInterfaceName());
    stringBuffer.append(TEXT_96);
    }
    stringBuffer.append(TEXT_97);
    }
    if (!genPackage.getPackageInterDependencies().isEmpty()) {
    stringBuffer.append(TEXT_98);
    for (GenPackage interdep : genPackage.getPackageInterDependencies()) {
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
    for (GenPackage interdep : genPackage.getPackageLoadInterDependencies()) {
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
    for (GenPackage interdep : genPackage.getPackageBuildInterDependencies()) {
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
    for (GenPackage interdep : genPackage.getPackageBuildInterDependencies()) {
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
    for (GenPackage interdep : genPackage.getPackageLoadInterDependencies()) {
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
    for (GenClassifier genClassifier : genPackage.getGenClassifiers()) {
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
    for (GenFeature genFeature : genClass.getGenFeatures()) {
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
    for (Iterator<GenClass> c=genPackage.getGenClasses().iterator(); c.hasNext();) { GenClass genClass = c.next();
    stringBuffer.append(TEXT_224);
    stringBuffer.append(genClass.getClassifierInstanceName());
    stringBuffer.append(TEXT_225);
    stringBuffer.append(genClass.getMetaType());
    stringBuffer.append(TEXT_226);
    stringBuffer.append(genClass.getClassifierID());
    stringBuffer.append(TEXT_227);
    for (GenFeature genFeature : genClass.getGenFeatures()) {
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
    for (GenEnum genEnum : genPackage.getGenEnums()) {
    stringBuffer.append(TEXT_234);
    stringBuffer.append(genEnum.getClassifierInstanceName());
    stringBuffer.append(TEXT_235);
    stringBuffer.append(genEnum.getClassifierID());
    stringBuffer.append(TEXT_236);
    }
    }
    if (!genPackage.getGenDataTypes().isEmpty()) {
    stringBuffer.append(TEXT_237);
    for (GenDataType genDataType : genPackage.getGenDataTypes()) {
    stringBuffer.append(TEXT_238);
    stringBuffer.append(genDataType.getClassifierInstanceName());
    stringBuffer.append(TEXT_239);
    stringBuffer.append(genDataType.getClassifierID());
    stringBuffer.append(TEXT_240);
    }
    }
    stringBuffer.append(TEXT_241);
    
///////////////////////
class Information
{
  @SuppressWarnings("unused")
  EGenericType eGenericType;
  int depth;
  String type;
  String accessor;
}

class InformationIterator
{
  Iterator<?> iterator;
  InformationIterator(EGenericType eGenericType)
  {
    iterator = EcoreUtil.getAllContents(Collections.singleton(eGenericType));
  }

  boolean hasNext()
  {
    return iterator.hasNext();
  }

  Information next()
  {
    Information information = new Information();
    EGenericType eGenericType = information.eGenericType = (EGenericType)iterator.next();
    for (EObject container = eGenericType.eContainer(); container instanceof EGenericType; container = container.eContainer())
    {
      ++information.depth;
    }
    if (eGenericType.getEClassifier() != null )
    {
      GenClassifier genClassifier = genModel.findGenClassifier(eGenericType.getEClassifier());
      information.type = genPackage.getPackageInstanceVariable(genClassifier.getGenPackage()) + ".get" + genClassifier.getClassifierAccessorName() + "()";
    }
    else if (eGenericType.getETypeParameter() != null)
    {
      ETypeParameter eTypeParameter = eGenericType.getETypeParameter();
      if (eTypeParameter.eContainer() instanceof EClass)
      {
        information.type = genModel.findGenClassifier((EClass)eTypeParameter.eContainer()).getClassifierInstanceName() + "_" + eGenericType.getETypeParameter().getName();
      }
      else
      {
        information.type = "t" + (((EOperation)eTypeParameter.eContainer()).getETypeParameters().indexOf(eTypeParameter) + 1);
      }
    }
    else
    {
      information.type ="";
    }
    if (information.depth > 0)
    {
      if (eGenericType.eContainmentFeature().isMany())
      {
        information.accessor = "getE" + eGenericType.eContainmentFeature().getName().substring(1) + "().add";
      }
      else
      {
        information.accessor = "setE" + eGenericType.eContainmentFeature().getName().substring(1);
      }
    }
    return information;
  }
}
///////////////////////
int maxGenericTypeAssignment = 0;

    stringBuffer.append(TEXT_242);
    if (!genPackage.getPackageInitializationDependencies().isEmpty()) {
    stringBuffer.append(TEXT_243);
    for (GenPackage dep : genPackage.getPackageInitializationDependencies()) {
    stringBuffer.append(TEXT_244);
    stringBuffer.append(dep.getImportedPackageInterfaceName());
    stringBuffer.append(TEXT_245);
    stringBuffer.append(genPackage.getPackageInstanceVariable(dep));
    stringBuffer.append(TEXT_246);
    stringBuffer.append(dep.getImportedPackageInterfaceName());
    stringBuffer.append(TEXT_247);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.EPackage"));
    stringBuffer.append(TEXT_248);
    stringBuffer.append(dep.getImportedPackageInterfaceName());
    stringBuffer.append(TEXT_249);
    }
    }
    if (!genPackage.getSubGenPackages().isEmpty()) {
    stringBuffer.append(TEXT_250);
    for (GenPackage sub : genPackage.getSubGenPackages()) {
    stringBuffer.append(TEXT_251);
    stringBuffer.append(genPackage.getPackageInstanceVariable(sub));
    stringBuffer.append(TEXT_252);
    }
    }
    if (!genPackage.getGenClasses().isEmpty()) { boolean firstOperationAssignment = true; int maxTypeParameterAssignment = 0;
    if (genModel.useGenerics()) {
    stringBuffer.append(TEXT_253);
    for (GenClassifier genClassifier : genPackage.getGenClassifiers()) {
    for (GenTypeParameter genTypeParameter : genClassifier.getGenTypeParameters()) {
    if (!genTypeParameter.getEcoreTypeParameter().getEBounds().isEmpty() || genTypeParameter.isUsed()) {
    stringBuffer.append(TEXT_254);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.ETypeParameter"));
    stringBuffer.append(TEXT_255);
    stringBuffer.append(genClassifier.getClassifierInstanceName());
    stringBuffer.append(TEXT_256);
    stringBuffer.append(genTypeParameter.getName());
    stringBuffer.append(TEXT_257);
    stringBuffer.append(genClassifier.getClassifierInstanceName());
    stringBuffer.append(TEXT_258);
    stringBuffer.append(genTypeParameter.getName());
    stringBuffer.append(TEXT_259);
    stringBuffer.append(genModel.getNonNLS());
    } else {
    stringBuffer.append(TEXT_260);
    stringBuffer.append(genClassifier.getClassifierInstanceName());
    stringBuffer.append(TEXT_261);
    stringBuffer.append(genTypeParameter.getName());
    stringBuffer.append(TEXT_262);
    stringBuffer.append(genModel.getNonNLS());
    }
    }
    }
    }
    if (genModel.useGenerics()) {
    stringBuffer.append(TEXT_263);
    for (GenClassifier genClassifier : genPackage.getGenClassifiers()) {
    for (GenTypeParameter genTypeParameter : genClassifier.getGenTypeParameters()) {
    for (EGenericType bound : genTypeParameter.getEcoreTypeParameter().getEBounds()) {
    for (InformationIterator i=new InformationIterator(bound); i.hasNext(); ) { Information info = i.next(); String prefix = ""; if (maxGenericTypeAssignment <= info.depth) { ++maxGenericTypeAssignment; prefix = genModel.getImportedName("org.eclipse.emf.ecore.EGenericType") + " "; }
    stringBuffer.append(TEXT_264);
    stringBuffer.append(prefix);
    stringBuffer.append(TEXT_265);
    stringBuffer.append(info.depth + 1);
    stringBuffer.append(TEXT_266);
    stringBuffer.append(info.type);
    stringBuffer.append(TEXT_267);
    if (info.depth > 0) {
    stringBuffer.append(TEXT_268);
    stringBuffer.append(info.depth);
    stringBuffer.append(TEXT_269);
    stringBuffer.append(info.accessor);
    stringBuffer.append(TEXT_270);
    stringBuffer.append(info.depth + 1);
    stringBuffer.append(TEXT_271);
    }
    }
    stringBuffer.append(TEXT_272);
    stringBuffer.append(genClassifier.getClassifierInstanceName());
    stringBuffer.append(TEXT_273);
    stringBuffer.append(genTypeParameter.getName());
    stringBuffer.append(TEXT_274);
    }
    }
    }
    }
    stringBuffer.append(TEXT_275);
    for (GenClass genClass : genPackage.getGenClasses()) {
    if (!genClass.hasGenericSuperTypes()) {
    for (GenClass baseGenClass : genClass.getBaseGenClasses()) {
    stringBuffer.append(TEXT_276);
    stringBuffer.append(genClass.getClassifierInstanceName());
    stringBuffer.append(TEXT_277);
    stringBuffer.append(genPackage.getPackageInstanceVariable(baseGenClass.getGenPackage()));
    stringBuffer.append(TEXT_278);
    stringBuffer.append(baseGenClass.getClassifierAccessorName());
    stringBuffer.append(TEXT_279);
    }
    } else {
    for (EGenericType superType : genClass.getEcoreClass().getEGenericSuperTypes()) {
    for (InformationIterator i=new InformationIterator(superType); i.hasNext(); ) { Information info = i.next(); String prefix = ""; if (maxGenericTypeAssignment <= info.depth) { ++maxGenericTypeAssignment; prefix = genModel.getImportedName("org.eclipse.emf.ecore.EGenericType") + " "; }
    stringBuffer.append(TEXT_280);
    stringBuffer.append(prefix);
    stringBuffer.append(TEXT_281);
    stringBuffer.append(info.depth + 1);
    stringBuffer.append(TEXT_282);
    stringBuffer.append(info.type);
    stringBuffer.append(TEXT_283);
    if (info.depth > 0) {
    stringBuffer.append(TEXT_284);
    stringBuffer.append(info.depth);
    stringBuffer.append(TEXT_285);
    stringBuffer.append(info.accessor);
    stringBuffer.append(TEXT_286);
    stringBuffer.append(info.depth + 1);
    stringBuffer.append(TEXT_287);
    }
    }
    stringBuffer.append(TEXT_288);
    stringBuffer.append(genClass.getClassifierInstanceName());
    stringBuffer.append(TEXT_289);
    }
    }
    }
    stringBuffer.append(TEXT_290);
    for (Iterator<GenClass> c=genPackage.getGenClasses().iterator(); c.hasNext();) { GenClass genClass = c.next(); boolean hasInstanceTypeName = genModel.useGenerics() && genClass.getEcoreClass().getInstanceTypeName() != null && genClass.getEcoreClass().getInstanceTypeName().contains("<");
    stringBuffer.append(TEXT_291);
    stringBuffer.append(genClass.getClassifierInstanceName());
    stringBuffer.append(TEXT_292);
    if (genClass.isDynamic()) {
    stringBuffer.append(TEXT_293);
    } else {
    stringBuffer.append(genClass.getRawImportedInterfaceName());
    stringBuffer.append(TEXT_294);
    }
    stringBuffer.append(TEXT_295);
    stringBuffer.append(genClass.getName());
    stringBuffer.append(TEXT_296);
    stringBuffer.append(genClass.getAbstractFlag());
    stringBuffer.append(TEXT_297);
    stringBuffer.append(genClass.getInterfaceFlag());
    stringBuffer.append(TEXT_298);
    stringBuffer.append(genClass.getGeneratedInstanceClassFlag());
    if (hasInstanceTypeName) {
    stringBuffer.append(TEXT_299);
    stringBuffer.append(genClass.getEcoreClass().getInstanceTypeName());
    stringBuffer.append(TEXT_300);
    }
    stringBuffer.append(TEXT_301);
    stringBuffer.append(genModel.getNonNLS());
    if (hasInstanceTypeName) {
    stringBuffer.append(genModel.getNonNLS(2));
    }
    for (GenFeature genFeature : genClass.getGenFeatures()) {
    if (genFeature.hasGenericType()) {
    for (InformationIterator i=new InformationIterator(genFeature.getEcoreFeature().getEGenericType()); i.hasNext(); ) { Information info = i.next(); String prefix = ""; if (maxGenericTypeAssignment <= info.depth) { ++maxGenericTypeAssignment; prefix = genModel.getImportedName("org.eclipse.emf.ecore.EGenericType") + " "; }
    stringBuffer.append(TEXT_302);
    stringBuffer.append(prefix);
    stringBuffer.append(TEXT_303);
    stringBuffer.append(info.depth + 1);
    stringBuffer.append(TEXT_304);
    stringBuffer.append(info.type);
    stringBuffer.append(TEXT_305);
    if (info.depth > 0) {
    stringBuffer.append(TEXT_306);
    stringBuffer.append(info.depth);
    stringBuffer.append(TEXT_307);
    stringBuffer.append(info.accessor);
    stringBuffer.append(TEXT_308);
    stringBuffer.append(info.depth + 1);
    stringBuffer.append(TEXT_309);
    }
    }
    }
    if (genFeature.isReferenceType()) { GenFeature reverseGenFeature = genFeature.getReverse();
    String reverse = reverseGenFeature == null ? "null" : genPackage.getPackageInstanceVariable(reverseGenFeature.getGenPackage()) + ".get" + reverseGenFeature.getFeatureAccessorName() + "()";
    stringBuffer.append(TEXT_310);
    stringBuffer.append(genFeature.getFeatureAccessorName());
    stringBuffer.append(TEXT_311);
    if (genFeature.hasGenericType()) {
    stringBuffer.append(TEXT_312);
    } else {
    stringBuffer.append(genPackage.getPackageInstanceVariable(genFeature.getTypeGenPackage()));
    stringBuffer.append(TEXT_313);
    stringBuffer.append(genFeature.getTypeClassifierAccessorName());
    stringBuffer.append(TEXT_314);
    }
    stringBuffer.append(TEXT_315);
    stringBuffer.append(reverse);
    stringBuffer.append(TEXT_316);
    stringBuffer.append(genFeature.getName());
    stringBuffer.append(TEXT_317);
    stringBuffer.append(genFeature.getDefaultValue());
    stringBuffer.append(TEXT_318);
    stringBuffer.append(genFeature.getLowerBound());
    stringBuffer.append(TEXT_319);
    stringBuffer.append(genFeature.getUpperBound());
    stringBuffer.append(TEXT_320);
    stringBuffer.append(genFeature.getContainerClass());
    stringBuffer.append(TEXT_321);
    stringBuffer.append(genFeature.getTransientFlag());
    stringBuffer.append(TEXT_322);
    stringBuffer.append(genFeature.getVolatileFlag());
    stringBuffer.append(TEXT_323);
    stringBuffer.append(genFeature.getChangeableFlag());
    stringBuffer.append(TEXT_324);
    stringBuffer.append(genFeature.getContainmentFlag());
    stringBuffer.append(TEXT_325);
    stringBuffer.append(genFeature.getResolveProxiesFlag());
    stringBuffer.append(TEXT_326);
    stringBuffer.append(genFeature.getUnsettableFlag());
    stringBuffer.append(TEXT_327);
    stringBuffer.append(genFeature.getUniqueFlag());
    stringBuffer.append(TEXT_328);
    stringBuffer.append(genFeature.getDerivedFlag());
    stringBuffer.append(TEXT_329);
    stringBuffer.append(genFeature.getOrderedFlag());
    stringBuffer.append(TEXT_330);
    stringBuffer.append(genModel.getNonNLS());
    stringBuffer.append(genModel.getNonNLS(genFeature.getDefaultValue(), 2));
    for (GenFeature keyFeature : genFeature.getKeys()) {
    stringBuffer.append(TEXT_331);
    stringBuffer.append(genFeature.getFeatureAccessorName());
    stringBuffer.append(TEXT_332);
    stringBuffer.append(genPackage.getPackageInstanceVariable(keyFeature.getGenPackage()));
    stringBuffer.append(TEXT_333);
    stringBuffer.append(keyFeature.getFeatureAccessorName());
    stringBuffer.append(TEXT_334);
    }
    } else {
    stringBuffer.append(TEXT_335);
    stringBuffer.append(genFeature.getFeatureAccessorName());
    stringBuffer.append(TEXT_336);
    if (genFeature.hasGenericType()) {
    stringBuffer.append(TEXT_337);
    } else {
    stringBuffer.append(genPackage.getPackageInstanceVariable(genFeature.getTypeGenPackage()));
    stringBuffer.append(TEXT_338);
    stringBuffer.append(genFeature.getTypeClassifierAccessorName());
    stringBuffer.append(TEXT_339);
    }
    stringBuffer.append(TEXT_340);
    stringBuffer.append(genFeature.getName());
    stringBuffer.append(TEXT_341);
    stringBuffer.append(genFeature.getDefaultValue());
    stringBuffer.append(TEXT_342);
    stringBuffer.append(genFeature.getLowerBound());
    stringBuffer.append(TEXT_343);
    stringBuffer.append(genFeature.getUpperBound());
    stringBuffer.append(TEXT_344);
    stringBuffer.append(genFeature.getContainerClass());
    stringBuffer.append(TEXT_345);
    stringBuffer.append(genFeature.getTransientFlag());
    stringBuffer.append(TEXT_346);
    stringBuffer.append(genFeature.getVolatileFlag());
    stringBuffer.append(TEXT_347);
    stringBuffer.append(genFeature.getChangeableFlag());
    stringBuffer.append(TEXT_348);
    stringBuffer.append(genFeature.getUnsettableFlag());
    stringBuffer.append(TEXT_349);
    stringBuffer.append(genFeature.getIDFlag());
    stringBuffer.append(TEXT_350);
    stringBuffer.append(genFeature.getUniqueFlag());
    stringBuffer.append(TEXT_351);
    stringBuffer.append(genFeature.getDerivedFlag());
    stringBuffer.append(TEXT_352);
    stringBuffer.append(genFeature.getOrderedFlag());
    stringBuffer.append(TEXT_353);
    stringBuffer.append(genModel.getNonNLS());
    stringBuffer.append(genModel.getNonNLS(genFeature.getDefaultValue(), 2));
    }
    }
    for (GenOperation genOperation : genClass.getGenOperations()) {String prefix = ""; if (genOperation.hasGenericType() || !genOperation.getGenParameters().isEmpty() || !genOperation.getGenExceptions().isEmpty() || !genOperation.getGenTypeParameters().isEmpty()) { if (firstOperationAssignment) { firstOperationAssignment = false; prefix = genModel.getImportedName("org.eclipse.emf.ecore.EOperation") + " op = "; } else { prefix = "op = "; }} 
    stringBuffer.append(TEXT_354);
    if (!genOperation.isVoid()) {
    stringBuffer.append(TEXT_355);
    stringBuffer.append(prefix);
    stringBuffer.append(TEXT_356);
    stringBuffer.append(genClass.getClassifierInstanceName());
    stringBuffer.append(TEXT_357);
    if (genOperation.hasGenericType()) {
    stringBuffer.append(TEXT_358);
    } else {
    stringBuffer.append(genPackage.getPackageInstanceVariable(genOperation.getTypeGenPackage()));
    stringBuffer.append(TEXT_359);
    stringBuffer.append(genOperation.getTypeClassifierAccessorName());
    stringBuffer.append(TEXT_360);
    }
    stringBuffer.append(TEXT_361);
    stringBuffer.append(genOperation.getName());
    stringBuffer.append(TEXT_362);
    stringBuffer.append(genOperation.getLowerBound());
    stringBuffer.append(TEXT_363);
    stringBuffer.append(genOperation.getUpperBound());
    stringBuffer.append(TEXT_364);
    stringBuffer.append(genModel.getNonNLS());
    } else {
    stringBuffer.append(TEXT_365);
    stringBuffer.append(prefix);
    stringBuffer.append(TEXT_366);
    stringBuffer.append(genClass.getClassifierInstanceName());
    stringBuffer.append(TEXT_367);
    stringBuffer.append(genOperation.getName());
    stringBuffer.append(TEXT_368);
    stringBuffer.append(genModel.getNonNLS());
    }
    if (genModel.useGenerics()) {
    for (ListIterator<GenTypeParameter> t=genOperation.getGenTypeParameters().listIterator(); t.hasNext(); ) { GenTypeParameter genTypeParameter = t.next(); String typeParameterVariable = ""; if (!genTypeParameter.getEcoreTypeParameter().getEBounds().isEmpty() || genTypeParameter.isUsed()) { if (maxTypeParameterAssignment <= t.previousIndex()) { ++maxTypeParameterAssignment; typeParameterVariable = genModel.getImportedName("org.eclipse.emf.ecore.ETypeParameter") + " t" + t.nextIndex() + " = "; } else { typeParameterVariable = "t" + t.nextIndex() + " = "; }} 
    stringBuffer.append(TEXT_369);
    stringBuffer.append(typeParameterVariable);
    stringBuffer.append(TEXT_370);
    stringBuffer.append(genTypeParameter.getName());
    stringBuffer.append(TEXT_371);
    stringBuffer.append(genModel.getNonNLS());
    for (EGenericType typeParameter : genTypeParameter.getEcoreTypeParameter().getEBounds()) {
    for (InformationIterator i=new InformationIterator(typeParameter); i.hasNext(); ) { Information info = i.next(); String typePrefix = ""; if (maxGenericTypeAssignment <= info.depth) { ++maxGenericTypeAssignment; typePrefix = genModel.getImportedName("org.eclipse.emf.ecore.EGenericType") + " "; }
    stringBuffer.append(TEXT_372);
    stringBuffer.append(typePrefix);
    stringBuffer.append(TEXT_373);
    stringBuffer.append(info.depth + 1);
    stringBuffer.append(TEXT_374);
    stringBuffer.append(info.type);
    stringBuffer.append(TEXT_375);
    if (info.depth > 0) {
    stringBuffer.append(TEXT_376);
    stringBuffer.append(info.depth);
    stringBuffer.append(TEXT_377);
    stringBuffer.append(info.accessor);
    stringBuffer.append(TEXT_378);
    stringBuffer.append(info.depth + 1);
    stringBuffer.append(TEXT_379);
    }
    }
    stringBuffer.append(TEXT_380);
    stringBuffer.append(t.nextIndex());
    stringBuffer.append(TEXT_381);
    }
    }
    }
    for (GenParameter genParameter : genOperation.getGenParameters()) {
    if (genParameter.hasGenericType()) {
    for (InformationIterator i=new InformationIterator(genParameter.getEcoreParameter().getEGenericType()); i.hasNext(); ) { Information info = i.next(); String typePrefix = ""; if (maxGenericTypeAssignment <= info.depth) { ++maxGenericTypeAssignment; typePrefix = genModel.getImportedName("org.eclipse.emf.ecore.EGenericType") + " "; }
    stringBuffer.append(TEXT_382);
    stringBuffer.append(typePrefix);
    stringBuffer.append(TEXT_383);
    stringBuffer.append(info.depth + 1);
    stringBuffer.append(TEXT_384);
    stringBuffer.append(info.type);
    stringBuffer.append(TEXT_385);
    if (info.depth > 0) {
    stringBuffer.append(TEXT_386);
    stringBuffer.append(info.depth);
    stringBuffer.append(TEXT_387);
    stringBuffer.append(info.accessor);
    stringBuffer.append(TEXT_388);
    stringBuffer.append(info.depth + 1);
    stringBuffer.append(TEXT_389);
    }
    }
    }
    stringBuffer.append(TEXT_390);
    if (genParameter.hasGenericType()){
    stringBuffer.append(TEXT_391);
    } else {
    stringBuffer.append(genPackage.getPackageInstanceVariable(genParameter.getTypeGenPackage()));
    stringBuffer.append(TEXT_392);
    stringBuffer.append(genParameter.getTypeClassifierAccessorName());
    stringBuffer.append(TEXT_393);
    }
    stringBuffer.append(TEXT_394);
    stringBuffer.append(genParameter.getName());
    stringBuffer.append(TEXT_395);
    stringBuffer.append(genParameter.getLowerBound());
    stringBuffer.append(TEXT_396);
    stringBuffer.append(genParameter.getUpperBound());
    stringBuffer.append(TEXT_397);
    stringBuffer.append(genModel.getNonNLS());
    }
    if (genOperation.hasGenericExceptions()) {
    for (EGenericType genericExceptions : genOperation.getEcoreOperation().getEGenericExceptions()) {
    for (InformationIterator i=new InformationIterator(genericExceptions); i.hasNext(); ) { Information info = i.next(); String typePrefix = ""; if (maxGenericTypeAssignment <= info.depth) { ++maxGenericTypeAssignment; typePrefix = genModel.getImportedName("org.eclipse.emf.ecore.EGenericType") + " "; }
    stringBuffer.append(TEXT_398);
    stringBuffer.append(typePrefix);
    stringBuffer.append(TEXT_399);
    stringBuffer.append(info.depth + 1);
    stringBuffer.append(TEXT_400);
    stringBuffer.append(info.type);
    stringBuffer.append(TEXT_401);
    if (info.depth > 0) {
    stringBuffer.append(TEXT_402);
    stringBuffer.append(info.depth);
    stringBuffer.append(TEXT_403);
    stringBuffer.append(info.accessor);
    stringBuffer.append(TEXT_404);
    stringBuffer.append(info.depth + 1);
    stringBuffer.append(TEXT_405);
    }
    stringBuffer.append(TEXT_406);
    stringBuffer.append(info.depth + 1);
    stringBuffer.append(TEXT_407);
    }
    }
    } else {
    for (GenClassifier genException : genOperation.getGenExceptions()) {
    stringBuffer.append(TEXT_408);
    stringBuffer.append(genPackage.getPackageInstanceVariable(genException.getGenPackage()));
    stringBuffer.append(TEXT_409);
    stringBuffer.append(genException.getClassifierAccessorName());
    stringBuffer.append(TEXT_410);
    }
    }
    if (!genOperation.isVoid() && genOperation.hasGenericType()) {
    for (InformationIterator i=new InformationIterator(genOperation.getEcoreOperation().getEGenericType()); i.hasNext(); ) { Information info = i.next(); String typePrefix = ""; if (maxGenericTypeAssignment <= info.depth) { ++maxGenericTypeAssignment; typePrefix = genModel.getImportedName("org.eclipse.emf.ecore.EGenericType") + " "; }
    stringBuffer.append(TEXT_411);
    stringBuffer.append(typePrefix);
    stringBuffer.append(TEXT_412);
    stringBuffer.append(info.depth + 1);
    stringBuffer.append(TEXT_413);
    stringBuffer.append(info.type);
    stringBuffer.append(TEXT_414);
    if (info.depth > 0) {
    stringBuffer.append(TEXT_415);
    stringBuffer.append(info.depth);
    stringBuffer.append(TEXT_416);
    stringBuffer.append(info.accessor);
    stringBuffer.append(TEXT_417);
    stringBuffer.append(info.depth + 1);
    stringBuffer.append(TEXT_418);
    }
    }
    stringBuffer.append(TEXT_419);
    }
    }
    if (c.hasNext()) {
    stringBuffer.append(TEXT_420);
    }
    }
    }
    if (!genPackage.getGenEnums().isEmpty()) {
    stringBuffer.append(TEXT_421);
    for (Iterator<GenEnum> e=genPackage.getGenEnums().iterator(); e.hasNext();) { GenEnum genEnum = e.next();
    stringBuffer.append(TEXT_422);
    stringBuffer.append(genEnum.getClassifierInstanceName());
    stringBuffer.append(TEXT_423);
    stringBuffer.append(genEnum.getImportedName());
    stringBuffer.append(TEXT_424);
    stringBuffer.append(genEnum.getName());
    stringBuffer.append(TEXT_425);
    stringBuffer.append(genModel.getNonNLS());
    for (GenEnumLiteral genEnumLiteral : genEnum.getGenEnumLiterals()) {
    stringBuffer.append(TEXT_426);
    stringBuffer.append(genEnum.getClassifierInstanceName());
    stringBuffer.append(TEXT_427);
    stringBuffer.append(genEnum.getImportedName().equals(genEnum.getClassifierID()) ? genEnum.getQualifiedName() : genEnum.getImportedName());
    stringBuffer.append(TEXT_428);
    stringBuffer.append(genEnumLiteral.getEnumLiteralInstanceConstantName());
    stringBuffer.append(TEXT_429);
    }
    if (e.hasNext()) {
    stringBuffer.append(TEXT_430);
    }
    }
    }
    if (!genPackage.getGenDataTypes().isEmpty()) {
    stringBuffer.append(TEXT_431);
    for (GenDataType genDataType : genPackage.getGenDataTypes()) {boolean hasInstanceTypeName = genModel.useGenerics() && genDataType.getEcoreDataType().getInstanceTypeName() != null && genDataType.getEcoreDataType().getInstanceTypeName().contains("<");
    stringBuffer.append(TEXT_432);
    stringBuffer.append(genDataType.getClassifierInstanceName());
    stringBuffer.append(TEXT_433);
    stringBuffer.append(genDataType.getRawImportedInstanceClassName());
    stringBuffer.append(TEXT_434);
    stringBuffer.append(genDataType.getName());
    stringBuffer.append(TEXT_435);
    stringBuffer.append(genDataType.getSerializableFlag());
    stringBuffer.append(TEXT_436);
    stringBuffer.append(genDataType.getGeneratedInstanceClassFlag());
    if (hasInstanceTypeName) {
    stringBuffer.append(TEXT_437);
    stringBuffer.append(genDataType.getEcoreDataType().getInstanceTypeName());
    stringBuffer.append(TEXT_438);
    }
    stringBuffer.append(TEXT_439);
    stringBuffer.append(genModel.getNonNLS());
    if (hasInstanceTypeName) {
    stringBuffer.append(genModel.getNonNLS(2));
    }
    }
    }
    if (genPackage.getSuperGenPackage() == null) {
    stringBuffer.append(TEXT_440);
    }
    if (!genPackage.isEcorePackage() && !genPackage.getAnnotationSources().isEmpty()) {
    stringBuffer.append(TEXT_441);
    for (String annotationSource : genPackage.getAnnotationSources()) {
    stringBuffer.append(TEXT_442);
    stringBuffer.append(annotationSource);
    stringBuffer.append(TEXT_443);
    stringBuffer.append(genPackage.getAnnotationSourceIdentifier(annotationSource));
    stringBuffer.append(TEXT_444);
    }
    }
    stringBuffer.append(TEXT_445);
    for (String annotationSource : genPackage.getAnnotationSources()) {
    stringBuffer.append(TEXT_446);
    stringBuffer.append(annotationSource);
    stringBuffer.append(TEXT_447);
    stringBuffer.append(genPackage.getAnnotationSourceIdentifier(annotationSource));
    stringBuffer.append(TEXT_448);
    if (annotationSource == null) {
    stringBuffer.append(TEXT_449);
    } else {
    stringBuffer.append(TEXT_450);
    stringBuffer.append(annotationSource);
    stringBuffer.append(TEXT_451);
    stringBuffer.append(genModel.getNonNLS());
    }
    for (EAnnotation eAnnotation : genPackage.getAllAnnotations()) {
    stringBuffer.append(TEXT_452);
    if (annotationSource == null ? eAnnotation.getSource() == null : annotationSource.equals(eAnnotation.getSource())) {
    stringBuffer.append(TEXT_453);
    stringBuffer.append(genPackage.getAnnotatedModelElementAccessor(eAnnotation));
    stringBuffer.append(TEXT_454);
    for (Iterator<Map.Entry<String, String>> k = eAnnotation.getDetails().iterator(); k.hasNext();) { Map.Entry<String, String> detail = k.next(); String key = Literals.toStringLiteral(detail.getKey(), genModel); String value = Literals.toStringLiteral(detail.getValue(), genModel);
    stringBuffer.append(TEXT_455);
    stringBuffer.append(key);
    stringBuffer.append(TEXT_456);
    stringBuffer.append(value);
    stringBuffer.append(k.hasNext() ? "," : "");
    stringBuffer.append(genModel.getNonNLS(key + value));
    }
    stringBuffer.append(TEXT_457);
    }
    for (EAnnotation nestedEAnnotation : genPackage.getAllNestedAnnotations(eAnnotation)) {String nestedAnnotationSource = nestedEAnnotation.getSource(); int depth = 1; for (EObject eContainer = nestedEAnnotation.eContainer(); eContainer != eAnnotation; eContainer = eContainer.eContainer()) { ++depth; }
    stringBuffer.append(TEXT_458);
    stringBuffer.append(genPackage.getAnnotatedModelElementAccessor(eAnnotation));
    stringBuffer.append(TEXT_459);
    stringBuffer.append(depth);
    stringBuffer.append(TEXT_460);
    if (nestedAnnotationSource == null) {
    stringBuffer.append(TEXT_461);
    } else {
    stringBuffer.append(TEXT_462);
    stringBuffer.append(nestedAnnotationSource);
    stringBuffer.append(TEXT_463);
    stringBuffer.append(genModel.getNonNLS());
    }
    stringBuffer.append(TEXT_464);
    for (Iterator<Map.Entry<String, String>> l = nestedEAnnotation.getDetails().iterator(); l.hasNext();) { Map.Entry<String, String> detail = l.next(); String key = Literals.toStringLiteral(detail.getKey(), genModel); String value = Literals.toStringLiteral(detail.getValue(), genModel);
    stringBuffer.append(TEXT_465);
    stringBuffer.append(key);
    stringBuffer.append(TEXT_466);
    stringBuffer.append(value);
    stringBuffer.append(l.hasNext() ? "," : "");
    stringBuffer.append(genModel.getNonNLS(key + value));
    }
    stringBuffer.append(TEXT_467);
    }
    for (EAnnotation nestedEAnnotation : genPackage.getAllNestedAnnotations(eAnnotation)) {String nestedAnnotationSource = nestedEAnnotation.getSource(); int depth = 1; for (EObject eContainer = nestedEAnnotation.eContainer(); eContainer != eAnnotation; eContainer = eContainer.eContainer()) { ++depth; }
    stringBuffer.append(TEXT_468);
    stringBuffer.append(genPackage.getAnnotatedModelElementAccessor(eAnnotation));
    stringBuffer.append(TEXT_469);
    stringBuffer.append(depth);
    stringBuffer.append(TEXT_470);
    if (nestedAnnotationSource == null) {
    stringBuffer.append(TEXT_471);
    } else {
    stringBuffer.append(TEXT_472);
    stringBuffer.append(nestedAnnotationSource);
    stringBuffer.append(TEXT_473);
    stringBuffer.append(genModel.getNonNLS());
    }
    stringBuffer.append(TEXT_474);
    for (Iterator<Map.Entry<String, String>> l = nestedEAnnotation.getDetails().iterator(); l.hasNext();) { Map.Entry<String, String> detail = l.next(); String key = Literals.toStringLiteral(detail.getKey(), genModel); String value = Literals.toStringLiteral(detail.getValue(), genModel);
    stringBuffer.append(TEXT_475);
    stringBuffer.append(key);
    stringBuffer.append(TEXT_476);
    stringBuffer.append(value);
    stringBuffer.append(l.hasNext() ? "," : "");
    stringBuffer.append(genModel.getNonNLS(key + value));
    }
    stringBuffer.append(TEXT_477);
    }
    }
    stringBuffer.append(TEXT_478);
    }
    } else {
    if (genPackage.isLoadingInitialization()) {
    stringBuffer.append(TEXT_479);
    stringBuffer.append(genModel.getImportedName("java.net.URL"));
    stringBuffer.append(TEXT_480);
    stringBuffer.append(genModel.getNonNLS());
    stringBuffer.append(TEXT_481);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.util.URI"));
    stringBuffer.append(TEXT_482);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.util.URI"));
    stringBuffer.append(TEXT_483);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.resource.Resource"));
    stringBuffer.append(TEXT_484);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.xmi.impl.EcoreResourceFactoryImpl"));
    stringBuffer.append(TEXT_485);
    stringBuffer.append(genModel.getImportedName("java.io.IOException"));
    stringBuffer.append(TEXT_486);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.util.WrappedException"));
    stringBuffer.append(TEXT_487);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.EPackage"));
    stringBuffer.append(TEXT_488);
    }
    stringBuffer.append(TEXT_489);
    if (genModel.useClassOverrideAnnotation()) {
    stringBuffer.append(TEXT_490);
    }
    stringBuffer.append(TEXT_491);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.EClassifier"));
    stringBuffer.append(TEXT_492);
    ArrayList<GenClass> dynamicGenClasses = new ArrayList<GenClass>(); for (GenClass genClass : genPackage.getGenClasses()) { if (genClass.isDynamic()) { dynamicGenClasses.add(genClass); } }
    if (dynamicGenClasses.isEmpty()) {
    stringBuffer.append(TEXT_493);
    stringBuffer.append(genPackage.getInterfacePackageName());
    stringBuffer.append(TEXT_494);
    stringBuffer.append(genModel.getNonNLS());
    stringBuffer.append(TEXT_495);
    } else {
    stringBuffer.append(TEXT_496);
    for (GenClass genClass : dynamicGenClasses) {
    if (genClass.isDynamic()) {
    stringBuffer.append(TEXT_497);
    stringBuffer.append(genPackage.getClassifierID(genClass));
    stringBuffer.append(TEXT_498);
    }
    }
    stringBuffer.append(TEXT_499);
    stringBuffer.append(genPackage.getInterfacePackageName());
    stringBuffer.append(TEXT_500);
    stringBuffer.append(genModel.getNonNLS());
    stringBuffer.append(TEXT_501);
    }
    stringBuffer.append(TEXT_502);
    }
    }
    if (isInterface && genPackage.isLiteralsInterface()) {
    stringBuffer.append(TEXT_503);
    if (isImplementation) {
    stringBuffer.append(TEXT_504);
    }
    stringBuffer.append(TEXT_505);
    for (GenClassifier genClassifier : genPackage.getGenClassifiers()) {
    stringBuffer.append(TEXT_506);
    if (genClassifier instanceof GenClass) { GenClass genClass = (GenClass)genClassifier;
    if (!genClass.isInterface()) {
    stringBuffer.append(TEXT_507);
    stringBuffer.append(genClass.getQualifiedClassName());
    stringBuffer.append(TEXT_508);
    stringBuffer.append(genClass.getFormattedName());
    stringBuffer.append(TEXT_509);
    stringBuffer.append(genClass.getQualifiedClassName());
    } else {
    stringBuffer.append(TEXT_510);
    stringBuffer.append(genClass.getQualifiedInterfaceName());
    stringBuffer.append(TEXT_511);
    stringBuffer.append(genClass.getFormattedName());
    stringBuffer.append(TEXT_512);
    stringBuffer.append(genClass.getQualifiedInterfaceName());
    }
    } else if (genClassifier instanceof GenEnum) { GenEnum genEnum = (GenEnum)genClassifier;
    stringBuffer.append(TEXT_513);
    stringBuffer.append(genEnum.getQualifiedName());
    stringBuffer.append(TEXT_514);
    stringBuffer.append(genEnum.getFormattedName());
    stringBuffer.append(TEXT_515);
    stringBuffer.append(genEnum.getQualifiedName());
    } else if (genClassifier instanceof GenDataType) { GenDataType genDataType = (GenDataType)genClassifier;
    stringBuffer.append(TEXT_516);
    stringBuffer.append(genDataType.getFormattedName());
    stringBuffer.append(TEXT_517);
    if (!genDataType.isPrimitiveType() && !genDataType.isArrayType()) {
    stringBuffer.append(TEXT_518);
    stringBuffer.append(genDataType.getQualifiedInstanceClassName());
    }
    }
    stringBuffer.append(TEXT_519);
    stringBuffer.append(genPackage.getQualifiedPackageClassName());
    stringBuffer.append(TEXT_520);
    stringBuffer.append(genClassifier.getClassifierAccessorName());
    stringBuffer.append(TEXT_521);
    stringBuffer.append(publicStaticFinalFlag);
    stringBuffer.append(genClassifier.getImportedMetaType());
    stringBuffer.append(TEXT_522);
    stringBuffer.append(genPackage.getClassifierID(genClassifier));
    stringBuffer.append(TEXT_523);
    stringBuffer.append(genClassifier.getClassifierAccessorName());
    stringBuffer.append(TEXT_524);
    if (genClassifier instanceof GenClass) { GenClass genClass = (GenClass)genClassifier;
    for (GenFeature genFeature : genClass.getGenFeatures()) {
    stringBuffer.append(TEXT_525);
    stringBuffer.append(genFeature.getFormattedName());
    stringBuffer.append(TEXT_526);
    stringBuffer.append(genFeature.getFeatureKind());
    stringBuffer.append(TEXT_527);
    stringBuffer.append(publicStaticFinalFlag);
    stringBuffer.append(genFeature.getImportedMetaType());
    stringBuffer.append(TEXT_528);
    stringBuffer.append(genClass.getFeatureID(genFeature));
    stringBuffer.append(TEXT_529);
    stringBuffer.append(genFeature.getFeatureAccessorName());
    stringBuffer.append(TEXT_530);
    }
    }
    }
    stringBuffer.append(TEXT_531);
    }
    stringBuffer.append(TEXT_532);
    stringBuffer.append(isInterface ? genPackage.getPackageInterfaceName() : genPackage.getPackageClassName());
    genModel.emitSortedImports();
    stringBuffer.append(TEXT_533);
    return stringBuffer.toString();
  }
}
