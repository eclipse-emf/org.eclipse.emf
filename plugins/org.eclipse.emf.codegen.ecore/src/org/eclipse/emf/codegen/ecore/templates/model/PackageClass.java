package org.eclipse.emf.codegen.ecore.templates.model;

import java.util.*;
import org.eclipse.emf.ecore.*;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.codegen.ecore.genmodel.*;
import org.eclipse.emf.codegen.ecore.genmodel.impl.Literals;
import org.eclipse.emf.codegen.ecore.genmodel.util.GenModelUtil;

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

  public final String NL = nl == null ? (System.getProperties().getProperty("line.separator")) : nl;
  protected final String TEXT_1 = "";
  protected final String TEXT_2 = "/**";
  protected final String TEXT_3 = NL + " * ";
  protected final String TEXT_4 = NL + " */";
  protected final String TEXT_5 = NL + "package ";
  protected final String TEXT_6 = ";";
  protected final String TEXT_7 = NL + "package ";
  protected final String TEXT_8 = ";";
  protected final String TEXT_9 = NL;
  protected final String TEXT_10 = NL + NL + "/**" + NL + " * <!-- begin-user-doc -->" + NL + " * The <b>Package</b> for the model." + NL + " * It contains accessors for the meta objects to represent" + NL + " * <ul>" + NL + " *   <li>each class,</li>" + NL + " *   <li>each feature of each class,</li>";
  protected final String TEXT_11 = NL + " *   <li>each operation of each class,</li>";
  protected final String TEXT_12 = NL + " *   <li>each enum,</li>" + NL + " *   <li>and each data type</li>" + NL + " * </ul>" + NL + " * <!-- end-user-doc -->";
  protected final String TEXT_13 = NL + " * <!-- begin-model-doc -->" + NL + " * ";
  protected final String TEXT_14 = NL + " * <!-- end-model-doc -->";
  protected final String TEXT_15 = NL + " * @see ";
  protected final String TEXT_16 = NL + " * @model ";
  protected final String TEXT_17 = NL + " *        ";
  protected final String TEXT_18 = NL + " * @model";
  protected final String TEXT_19 = NL + " * @generated" + NL + " */";
  protected final String TEXT_20 = NL + NL + "/**" + NL + " * <!-- begin-user-doc -->" + NL + " * An implementation of the model <b>Package</b>." + NL + " * <!-- end-user-doc -->";
  protected final String TEXT_21 = NL + " * ";
  protected final String TEXT_22 = NL + " * @generated" + NL + " */";
  protected final String TEXT_23 = NL + "@Deprecated";
  protected final String TEXT_24 = NL + "@SuppressWarnings(\"deprecation\")";
  protected final String TEXT_25 = NL + "public class ";
  protected final String TEXT_26 = " extends ";
  protected final String TEXT_27 = " implements ";
  protected final String TEXT_28 = NL + "public interface ";
  protected final String TEXT_29 = " extends ";
  protected final String TEXT_30 = NL + "{";
  protected final String TEXT_31 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */" + NL + "\t";
  protected final String TEXT_32 = " copyright = ";
  protected final String TEXT_33 = ";";
  protected final String TEXT_34 = NL;
  protected final String TEXT_35 = NL + "\t/**" + NL + "\t * The package name." + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */" + NL + "\t";
  protected final String TEXT_36 = " eNAME = \"";
  protected final String TEXT_37 = "\";";
  protected final String TEXT_38 = NL + NL + "\t/**" + NL + "\t * The package namespace URI." + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */" + NL + "\t";
  protected final String TEXT_39 = " eNS_URI = \"";
  protected final String TEXT_40 = "\";";
  protected final String TEXT_41 = NL + NL + "\t/**" + NL + "\t * The package namespace name." + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */" + NL + "\t";
  protected final String TEXT_42 = " eNS_PREFIX = \"";
  protected final String TEXT_43 = "\";";
  protected final String TEXT_44 = NL + NL + "\t/**" + NL + "\t * The package content type ID." + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */" + NL + "\t";
  protected final String TEXT_45 = " eCONTENT_TYPE = \"";
  protected final String TEXT_46 = "\";";
  protected final String TEXT_47 = NL + NL + "\t/**" + NL + "\t * The singleton instance of the package." + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */" + NL + "\t";
  protected final String TEXT_48 = " eINSTANCE = ";
  protected final String TEXT_49 = ".init();" + NL;
  protected final String TEXT_50 = NL + "\t/**";
  protected final String TEXT_51 = NL + "\t * The meta object id for the '{@link ";
  protected final String TEXT_52 = " <em>";
  protected final String TEXT_53 = "</em>}' class." + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @see ";
  protected final String TEXT_54 = NL + "\t * The meta object id for the '{@link ";
  protected final String TEXT_55 = " <em>";
  protected final String TEXT_56 = "</em>}' class." + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @see ";
  protected final String TEXT_57 = NL + "\t * The meta object id for the '{@link ";
  protected final String TEXT_58 = " <em>";
  protected final String TEXT_59 = "</em>}' enum." + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @see ";
  protected final String TEXT_60 = NL + "\t * The meta object id for the '<em>";
  protected final String TEXT_61 = "</em>' data type." + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->";
  protected final String TEXT_62 = NL + "\t * @see ";
  protected final String TEXT_63 = NL + "\t * @see ";
  protected final String TEXT_64 = "#get";
  protected final String TEXT_65 = "()";
  protected final String TEXT_66 = NL + "\t * ";
  protected final String TEXT_67 = NL + "\t * @generated" + NL + "\t */";
  protected final String TEXT_68 = NL + "\t@Deprecated";
  protected final String TEXT_69 = NL + "\t";
  protected final String TEXT_70 = "int ";
  protected final String TEXT_71 = " = ";
  protected final String TEXT_72 = ";" + NL;
  protected final String TEXT_73 = NL + "\t/**" + NL + "\t * The feature id for the '<em><b>";
  protected final String TEXT_74 = "</b></em>' ";
  protected final String TEXT_75 = "." + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->";
  protected final String TEXT_76 = NL + "\t * ";
  protected final String TEXT_77 = NL + "\t * @generated" + NL + "\t * @ordered" + NL + "\t */";
  protected final String TEXT_78 = NL + "\t@Deprecated";
  protected final String TEXT_79 = NL + "\t";
  protected final String TEXT_80 = "int ";
  protected final String TEXT_81 = " = ";
  protected final String TEXT_82 = ";" + NL;
  protected final String TEXT_83 = NL + "\t/**" + NL + "\t * The number of structural features of the '<em>";
  protected final String TEXT_84 = "</em>' class." + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->";
  protected final String TEXT_85 = NL + "\t * ";
  protected final String TEXT_86 = NL + "\t * @generated" + NL + "\t * @ordered" + NL + "\t */";
  protected final String TEXT_87 = NL + "\t@Deprecated";
  protected final String TEXT_88 = NL + "\t";
  protected final String TEXT_89 = "int ";
  protected final String TEXT_90 = " = ";
  protected final String TEXT_91 = ";" + NL;
  protected final String TEXT_92 = NL + "\t/**" + NL + "\t * The operation id for the '<em>";
  protected final String TEXT_93 = "</em>' operation." + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->";
  protected final String TEXT_94 = NL + "\t * ";
  protected final String TEXT_95 = NL + "\t * @generated" + NL + "\t * @ordered" + NL + "\t */";
  protected final String TEXT_96 = NL + "\t@Deprecated";
  protected final String TEXT_97 = NL + "\t";
  protected final String TEXT_98 = "int ";
  protected final String TEXT_99 = " = ";
  protected final String TEXT_100 = ";" + NL;
  protected final String TEXT_101 = NL + "\t/**" + NL + "\t * The number of operations of the '<em>";
  protected final String TEXT_102 = "</em>' class." + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->";
  protected final String TEXT_103 = NL + "\t * ";
  protected final String TEXT_104 = NL + "\t * @generated" + NL + "\t * @ordered" + NL + "\t */";
  protected final String TEXT_105 = NL + "\t@Deprecated";
  protected final String TEXT_106 = NL + "\t";
  protected final String TEXT_107 = "int ";
  protected final String TEXT_108 = " = ";
  protected final String TEXT_109 = ";" + NL;
  protected final String TEXT_110 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */" + NL + "\tprotected String packageFilename = \"";
  protected final String TEXT_111 = "\";";
  protected final String TEXT_112 = NL;
  protected final String TEXT_113 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->";
  protected final String TEXT_114 = NL + "\t * ";
  protected final String TEXT_115 = NL + "\t * @generated" + NL + "\t */";
  protected final String TEXT_116 = NL + "\t@Deprecated";
  protected final String TEXT_117 = NL + "\tprivate ";
  protected final String TEXT_118 = " ";
  protected final String TEXT_119 = " = null;" + NL;
  protected final String TEXT_120 = NL + "\t/**" + NL + "\t * Creates an instance of the model <b>Package</b>, registered with" + NL + "\t * {@link org.eclipse.emf.ecore.EPackage.Registry EPackage.Registry} by the package" + NL + "\t * package URI value." + NL + "\t * <p>Note: the correct way to create the package is via the static" + NL + "\t * factory method {@link #init init()}, which also performs" + NL + "\t * initialization of the package, or returns the registered package," + NL + "\t * if one already exists." + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @see org.eclipse.emf.ecore.EPackage.Registry" + NL + "\t * @see ";
  protected final String TEXT_121 = "#eNS_URI" + NL + "\t * @see #init()" + NL + "\t * @generated" + NL + "\t */" + NL + "\tprivate ";
  protected final String TEXT_122 = "()" + NL + "\t{" + NL + "\t\tsuper(eNS_URI, ";
  protected final String TEXT_123 = ");" + NL + "\t}" + NL + "" + NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */" + NL + "\tprivate static boolean isInited = false;" + NL + "" + NL + "\t/**" + NL + "\t * Creates, registers, and initializes the <b>Package</b> for this model, and for any others upon which it depends." + NL + "\t * " + NL + "\t * <p>This method is used to initialize {@link ";
  protected final String TEXT_124 = "#eINSTANCE} when that field is accessed." + NL + "\t * Clients should not invoke it directly. Instead, they should simply access that field to obtain the package." + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @see #eNS_URI";
  protected final String TEXT_125 = NL + "\t * @see #createPackageContents()" + NL + "\t * @see #initializePackageContents()";
  protected final String TEXT_126 = NL + "\t * @generated" + NL + "\t */" + NL + "\tpublic static ";
  protected final String TEXT_127 = " init()" + NL + "\t{" + NL + "\t\tif (isInited) return (";
  protected final String TEXT_128 = ")";
  protected final String TEXT_129 = ".Registry.INSTANCE.getEPackage(";
  protected final String TEXT_130 = ".eNS_URI);" + NL;
  protected final String TEXT_131 = NL + "\t\tinitializeRegistryHelpers();" + NL;
  protected final String TEXT_132 = NL + "\t\t// Obtain or create and register package" + NL + "\t\t";
  protected final String TEXT_133 = " the";
  protected final String TEXT_134 = " = (";
  protected final String TEXT_135 = ")(";
  protected final String TEXT_136 = ".Registry.INSTANCE.get(eNS_URI) instanceof ";
  protected final String TEXT_137 = " ? ";
  protected final String TEXT_138 = ".Registry.INSTANCE.get(eNS_URI) : new ";
  protected final String TEXT_139 = "());" + NL + "" + NL + "\t\tisInited = true;" + NL;
  protected final String TEXT_140 = NL + "\t\t// Initialize simple dependencies";
  protected final String TEXT_141 = NL + "\t\t";
  protected final String TEXT_142 = ".eINSTANCE.eClass();";
  protected final String TEXT_143 = NL;
  protected final String TEXT_144 = NL + "\t\t// Obtain or create and register interdependencies";
  protected final String TEXT_145 = NL + "\t\t";
  protected final String TEXT_146 = " ";
  protected final String TEXT_147 = " = (";
  protected final String TEXT_148 = ")(";
  protected final String TEXT_149 = ".Registry.INSTANCE.getEPackage(";
  protected final String TEXT_150 = ".eNS_URI) instanceof ";
  protected final String TEXT_151 = " ? ";
  protected final String TEXT_152 = ".Registry.INSTANCE.getEPackage(";
  protected final String TEXT_153 = ".eNS_URI) : ";
  protected final String TEXT_154 = ".eINSTANCE);";
  protected final String TEXT_155 = NL;
  protected final String TEXT_156 = NL + "\t\t// Load packages";
  protected final String TEXT_157 = NL + "\t\tthe";
  protected final String TEXT_158 = ".loadPackage();";
  protected final String TEXT_159 = NL + "\t\t";
  protected final String TEXT_160 = ".loadPackage();";
  protected final String TEXT_161 = NL;
  protected final String TEXT_162 = NL + "\t\t// Create package meta-data objects";
  protected final String TEXT_163 = NL + "\t\tthe";
  protected final String TEXT_164 = ".createPackageContents();";
  protected final String TEXT_165 = NL + "\t\t";
  protected final String TEXT_166 = ".createPackageContents();";
  protected final String TEXT_167 = NL + NL + "\t\t// Initialize created meta-data";
  protected final String TEXT_168 = NL + "\t\tthe";
  protected final String TEXT_169 = ".initializePackageContents();";
  protected final String TEXT_170 = NL + "\t\t";
  protected final String TEXT_171 = ".initializePackageContents();";
  protected final String TEXT_172 = NL;
  protected final String TEXT_173 = NL + "\t\t// Fix loaded packages";
  protected final String TEXT_174 = NL + "\t\tthe";
  protected final String TEXT_175 = ".fixPackageContents();";
  protected final String TEXT_176 = NL + "\t\t";
  protected final String TEXT_177 = ".fixPackageContents();";
  protected final String TEXT_178 = NL;
  protected final String TEXT_179 = NL + "\t\t// Register package validator" + NL + "\t\t";
  protected final String TEXT_180 = ".Registry.INSTANCE.put" + NL + "\t\t\t(the";
  protected final String TEXT_181 = ", " + NL + "\t\t\t new ";
  protected final String TEXT_182 = ".Descriptor()" + NL + "\t\t\t {" + NL + "\t\t\t\t public ";
  protected final String TEXT_183 = " getEValidator()" + NL + "\t\t\t\t {" + NL + "\t\t\t\t\t return ";
  protected final String TEXT_184 = ".INSTANCE;" + NL + "\t\t\t\t }" + NL + "\t\t\t });" + NL;
  protected final String TEXT_185 = NL + "\t\t// Mark meta-data to indicate it can't be changed" + NL + "\t\tthe";
  protected final String TEXT_186 = ".freeze();" + NL;
  protected final String TEXT_187 = NL + "  " + NL + "\t\t// Update the registry and return the package" + NL + "\t\t";
  protected final String TEXT_188 = ".Registry.INSTANCE.put(";
  protected final String TEXT_189 = ".eNS_URI, the";
  protected final String TEXT_190 = ");" + NL + "\t\treturn the";
  protected final String TEXT_191 = ";" + NL + "\t}";
  protected final String TEXT_192 = NL + NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */" + NL + "\tpublic static void initializeRegistryHelpers()" + NL + "\t{";
  protected final String TEXT_193 = NL + "\t\t";
  protected final String TEXT_194 = ".register" + NL + "\t\t\t(";
  protected final String TEXT_195 = ".class, " + NL + "\t\t\t new ";
  protected final String TEXT_196 = ".Helper() " + NL + "\t\t\t {" + NL + "\t\t\t\t public boolean isInstance(Object instance)" + NL + "\t\t\t\t {" + NL + "\t\t\t\t\t return instance instanceof ";
  protected final String TEXT_197 = ";" + NL + "\t\t\t\t }" + NL + "" + NL + "\t\t\t\t public Object newArrayInstance(int size)" + NL + "\t\t\t\t {" + NL + "\t\t\t\t\t return new ";
  protected final String TEXT_198 = "[size];" + NL + "\t\t\t\t }" + NL + "\t\t\t });";
  protected final String TEXT_199 = NL + "\t\t";
  protected final String TEXT_200 = ".register" + NL + "\t\t\t(";
  protected final String TEXT_201 = ".class, " + NL + "\t\t\t new ";
  protected final String TEXT_202 = ".Helper() " + NL + "\t\t\t {" + NL + "\t\t\t\t public boolean isInstance(Object instance)" + NL + "\t\t\t\t {" + NL + "\t\t\t\t\t return instance instanceof ";
  protected final String TEXT_203 = ";" + NL + "\t\t\t\t }" + NL + "" + NL + "\t\t\t\t public Object newArrayInstance(int size)" + NL + "\t\t\t\t {";
  protected final String TEXT_204 = NL + "\t\t\t\t\t return new ";
  protected final String TEXT_205 = "[size]";
  protected final String TEXT_206 = ";";
  protected final String TEXT_207 = NL + "\t\t\t\t\t return new ";
  protected final String TEXT_208 = "[size];";
  protected final String TEXT_209 = NL + "\t\t\t\t }" + NL + "\t\t});";
  protected final String TEXT_210 = NL + "\t}" + NL + "" + NL + "" + NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */" + NL + "\tpublic static class WhiteList implements ";
  protected final String TEXT_211 = ", EBasicWhiteList" + NL + "\t{";
  protected final String TEXT_212 = NL + "\t\t/**" + NL + "\t\t * <!-- begin-user-doc -->" + NL + "\t\t * <!-- end-user-doc -->" + NL + "\t\t * @generated" + NL + "\t\t */" + NL + "\t\tprotected ";
  protected final String TEXT_213 = " ";
  protected final String TEXT_214 = ";" + NL;
  protected final String TEXT_215 = NL + "\t\t/**" + NL + "\t\t * <!-- begin-user-doc -->" + NL + "\t\t * <!-- end-user-doc -->" + NL + "\t\t * @generated" + NL + "\t\t */" + NL + "\t\tprotected ";
  protected final String TEXT_216 = " ";
  protected final String TEXT_217 = ";" + NL;
  protected final String TEXT_218 = NL + "\t}";
  protected final String TEXT_219 = NL;
  protected final String TEXT_220 = NL;
  protected final String TEXT_221 = NL + "\t/**";
  protected final String TEXT_222 = NL + "\t * Returns the meta object for class '{@link ";
  protected final String TEXT_223 = " <em>";
  protected final String TEXT_224 = "</em>}'." + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @return the meta object for class '<em>";
  protected final String TEXT_225 = "</em>'." + NL + "\t * @see ";
  protected final String TEXT_226 = NL + "\t * @model ";
  protected final String TEXT_227 = NL + "\t *        ";
  protected final String TEXT_228 = NL + "\t * @model";
  protected final String TEXT_229 = NL + "\t * Returns the meta object for enum '{@link ";
  protected final String TEXT_230 = " <em>";
  protected final String TEXT_231 = "</em>}'." + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @return the meta object for enum '<em>";
  protected final String TEXT_232 = "</em>'." + NL + "\t * @see ";
  protected final String TEXT_233 = NL + "\t * Returns the meta object for data type '<em>";
  protected final String TEXT_234 = "</em>'.";
  protected final String TEXT_235 = NL + "\t * Returns the meta object for data type '{@link ";
  protected final String TEXT_236 = " <em>";
  protected final String TEXT_237 = "</em>}'.";
  protected final String TEXT_238 = NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->";
  protected final String TEXT_239 = NL + "     * <!-- begin-model-doc -->" + NL + "     * ";
  protected final String TEXT_240 = NL + "     * <!-- end-model-doc -->";
  protected final String TEXT_241 = NL + "\t * @return the meta object for data type '<em>";
  protected final String TEXT_242 = "</em>'.";
  protected final String TEXT_243 = NL + "\t * @see ";
  protected final String TEXT_244 = NL + "\t * @model ";
  protected final String TEXT_245 = NL + "\t *        ";
  protected final String TEXT_246 = NL + "\t * @model";
  protected final String TEXT_247 = NL + "\t * ";
  protected final String TEXT_248 = NL + "\t * @generated" + NL + "\t */";
  protected final String TEXT_249 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->";
  protected final String TEXT_250 = NL + "\t * ";
  protected final String TEXT_251 = NL + "\t * @generated" + NL + "\t */";
  protected final String TEXT_252 = NL + "\t@Deprecated";
  protected final String TEXT_253 = NL + "\tpublic ";
  protected final String TEXT_254 = " get";
  protected final String TEXT_255 = "()" + NL + "\t{";
  protected final String TEXT_256 = NL + "\t\tif (";
  protected final String TEXT_257 = " == null)" + NL + "\t\t{" + NL + "\t\t\t";
  protected final String TEXT_258 = " = (";
  protected final String TEXT_259 = ")";
  protected final String TEXT_260 = ".Registry.INSTANCE.getEPackage(";
  protected final String TEXT_261 = ".eNS_URI).getEClassifiers().get(";
  protected final String TEXT_262 = ");" + NL + "\t\t}";
  protected final String TEXT_263 = NL + "\t\treturn ";
  protected final String TEXT_264 = ";" + NL + "\t}" + NL;
  protected final String TEXT_265 = NL + "\t";
  protected final String TEXT_266 = " get";
  protected final String TEXT_267 = "();" + NL;
  protected final String TEXT_268 = NL + "\t/**" + NL + "\t * Returns the meta object for the ";
  protected final String TEXT_269 = " '{@link ";
  protected final String TEXT_270 = "#";
  protected final String TEXT_271 = " <em>";
  protected final String TEXT_272 = "</em>}'." + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @return the meta object for the ";
  protected final String TEXT_273 = " '<em>";
  protected final String TEXT_274 = "</em>'." + NL + "\t * @see ";
  protected final String TEXT_275 = "#";
  protected final String TEXT_276 = "()";
  protected final String TEXT_277 = NL + "\t * @see #get";
  protected final String TEXT_278 = "()";
  protected final String TEXT_279 = NL + "\t * ";
  protected final String TEXT_280 = NL + "\t * @generated" + NL + "\t */";
  protected final String TEXT_281 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->";
  protected final String TEXT_282 = NL + "\t * ";
  protected final String TEXT_283 = NL + "\t * @generated" + NL + "\t */";
  protected final String TEXT_284 = NL + "\t@Deprecated";
  protected final String TEXT_285 = NL + "\tpublic ";
  protected final String TEXT_286 = " get";
  protected final String TEXT_287 = "()" + NL + "\t{";
  protected final String TEXT_288 = NL + "\t\treturn (";
  protected final String TEXT_289 = ")";
  protected final String TEXT_290 = ".getEStructuralFeatures().get(";
  protected final String TEXT_291 = ");";
  protected final String TEXT_292 = NL + "        return (";
  protected final String TEXT_293 = ")get";
  protected final String TEXT_294 = "().getEStructuralFeatures().get(";
  protected final String TEXT_295 = ");";
  protected final String TEXT_296 = NL + "\t}";
  protected final String TEXT_297 = NL + "\t";
  protected final String TEXT_298 = " get";
  protected final String TEXT_299 = "();";
  protected final String TEXT_300 = NL;
  protected final String TEXT_301 = NL + "\t/**" + NL + "\t * Returns the meta object for the '{@link ";
  protected final String TEXT_302 = "#";
  protected final String TEXT_303 = "(";
  protected final String TEXT_304 = ") <em>";
  protected final String TEXT_305 = "</em>}' operation." + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @return the meta object for the '<em>";
  protected final String TEXT_306 = "</em>' operation." + NL + "\t * @see ";
  protected final String TEXT_307 = "#";
  protected final String TEXT_308 = "(";
  protected final String TEXT_309 = ")";
  protected final String TEXT_310 = NL + "\t * ";
  protected final String TEXT_311 = NL + "\t * @generated" + NL + "\t */";
  protected final String TEXT_312 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->";
  protected final String TEXT_313 = NL + "\t * ";
  protected final String TEXT_314 = NL + "\t * @generated" + NL + "\t */";
  protected final String TEXT_315 = NL + "\t@Deprecated";
  protected final String TEXT_316 = NL + "\tpublic ";
  protected final String TEXT_317 = " get";
  protected final String TEXT_318 = "()" + NL + "\t{";
  protected final String TEXT_319 = NL + "\t\treturn ";
  protected final String TEXT_320 = ".getEOperations().get(";
  protected final String TEXT_321 = ");";
  protected final String TEXT_322 = NL + "        return get";
  protected final String TEXT_323 = "().getEOperations().get(";
  protected final String TEXT_324 = ");";
  protected final String TEXT_325 = NL + "\t}";
  protected final String TEXT_326 = NL + "\t";
  protected final String TEXT_327 = " get";
  protected final String TEXT_328 = "();";
  protected final String TEXT_329 = NL;
  protected final String TEXT_330 = NL + "\t/**" + NL + "\t * Returns the factory that creates the instances of the model." + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @return the factory that creates the instances of the model." + NL + "\t * @generated" + NL + "\t */";
  protected final String TEXT_331 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */";
  protected final String TEXT_332 = NL + "\tpublic ";
  protected final String TEXT_333 = " get";
  protected final String TEXT_334 = "()" + NL + "\t{" + NL + "\t\treturn (";
  protected final String TEXT_335 = ")getEFactoryInstance();" + NL + "\t}";
  protected final String TEXT_336 = NL + "\t";
  protected final String TEXT_337 = " get";
  protected final String TEXT_338 = "();";
  protected final String TEXT_339 = NL;
  protected final String TEXT_340 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */" + NL + "\tprivate boolean isCreated = false;" + NL + "" + NL + "\t/**" + NL + "\t * Creates the meta-model objects for the package.  This method is" + NL + "\t * guarded to have no affect on any invocation but its first." + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */";
  protected final String TEXT_341 = NL + "\t@SuppressWarnings(\"deprecation\")";
  protected final String TEXT_342 = NL + "\tpublic void createPackageContents()" + NL + "\t{" + NL + "\t\tif (isCreated) return;" + NL + "\t\tisCreated = true;";
  protected final String TEXT_343 = NL + NL + "\t\t// Create classes and their features";
  protected final String TEXT_344 = NL + "\t\t";
  protected final String TEXT_345 = " = create";
  protected final String TEXT_346 = "(";
  protected final String TEXT_347 = ");";
  protected final String TEXT_348 = NL + "\t\tcreate";
  protected final String TEXT_349 = "(";
  protected final String TEXT_350 = ", ";
  protected final String TEXT_351 = ");";
  protected final String TEXT_352 = NL + "\t\tcreateEOperation(";
  protected final String TEXT_353 = ", ";
  protected final String TEXT_354 = ");";
  protected final String TEXT_355 = NL;
  protected final String TEXT_356 = NL + NL + "\t\t// Create enums";
  protected final String TEXT_357 = NL + "\t\t";
  protected final String TEXT_358 = " = createEEnum(";
  protected final String TEXT_359 = ");";
  protected final String TEXT_360 = NL + NL + "\t\t// Create data types";
  protected final String TEXT_361 = NL + "\t\t";
  protected final String TEXT_362 = " = createEDataType(";
  protected final String TEXT_363 = ");";
  protected final String TEXT_364 = NL + "\t}" + NL + "" + NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */" + NL + "\tprivate boolean isInitialized = false;" + NL;
  protected final String TEXT_365 = NL + "\t/**" + NL + "\t * Complete the initialization of the package and its meta-model.  This" + NL + "\t * method is guarded to have no affect on any invocation but its first." + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */";
  protected final String TEXT_366 = NL + "\t@SuppressWarnings(\"deprecation\")";
  protected final String TEXT_367 = NL + "\tpublic void initializePackageContents()" + NL + "\t{" + NL + "\t\tif (isInitialized) return;" + NL + "\t\tisInitialized = true;" + NL + "" + NL + "\t\t// Initialize package" + NL + "\t\tsetName(eNAME);" + NL + "\t\tsetNsPrefix(eNS_PREFIX);" + NL + "\t\tsetNsURI(eNS_URI);";
  protected final String TEXT_368 = NL + NL + "\t\t// Obtain other dependent packages";
  protected final String TEXT_369 = NL + "\t\t";
  protected final String TEXT_370 = " ";
  protected final String TEXT_371 = " = (";
  protected final String TEXT_372 = ")";
  protected final String TEXT_373 = ".Registry.INSTANCE.getEPackage(";
  protected final String TEXT_374 = ".eNS_URI);";
  protected final String TEXT_375 = NL + NL + "\t\t// Add subpackages";
  protected final String TEXT_376 = NL + "\t\tgetESubpackages().add(";
  protected final String TEXT_377 = ");";
  protected final String TEXT_378 = NL + NL + "\t\t// Create type parameters";
  protected final String TEXT_379 = NL + "\t\t";
  protected final String TEXT_380 = " ";
  protected final String TEXT_381 = "_";
  protected final String TEXT_382 = " = addETypeParameter(";
  protected final String TEXT_383 = ", \"";
  protected final String TEXT_384 = "\");";
  protected final String TEXT_385 = NL + "\t\taddETypeParameter(";
  protected final String TEXT_386 = ", \"";
  protected final String TEXT_387 = "\");";
  protected final String TEXT_388 = NL + NL + "\t\t// Set bounds for type parameters";
  protected final String TEXT_389 = NL + "\t\t";
  protected final String TEXT_390 = "g";
  protected final String TEXT_391 = " = createEGenericType(";
  protected final String TEXT_392 = ");";
  protected final String TEXT_393 = NL + "\t\tg";
  protected final String TEXT_394 = ".";
  protected final String TEXT_395 = "(g";
  protected final String TEXT_396 = ");";
  protected final String TEXT_397 = NL + "\t\t";
  protected final String TEXT_398 = "_";
  protected final String TEXT_399 = ".getEBounds().add(g1);";
  protected final String TEXT_400 = NL + NL + "\t\t// Add supertypes to classes";
  protected final String TEXT_401 = NL + "\t\t";
  protected final String TEXT_402 = ".getESuperTypes().add(";
  protected final String TEXT_403 = ".get";
  protected final String TEXT_404 = "());";
  protected final String TEXT_405 = NL + "\t\t";
  protected final String TEXT_406 = "g";
  protected final String TEXT_407 = " = createEGenericType(";
  protected final String TEXT_408 = ");";
  protected final String TEXT_409 = NL + "\t\tg";
  protected final String TEXT_410 = ".";
  protected final String TEXT_411 = "(g";
  protected final String TEXT_412 = ");";
  protected final String TEXT_413 = NL + "\t\t";
  protected final String TEXT_414 = ".getEGenericSuperTypes().add(g1);";
  protected final String TEXT_415 = NL + NL + "\t\t// Initialize classes";
  protected final String TEXT_416 = ", features, and operations; add parameters";
  protected final String TEXT_417 = " and features; add operations and parameters";
  protected final String TEXT_418 = NL + "\t\tinitEClass(";
  protected final String TEXT_419 = ", ";
  protected final String TEXT_420 = "null";
  protected final String TEXT_421 = ".class";
  protected final String TEXT_422 = ", \"";
  protected final String TEXT_423 = "\", ";
  protected final String TEXT_424 = ", ";
  protected final String TEXT_425 = ", ";
  protected final String TEXT_426 = ", \"";
  protected final String TEXT_427 = "\"";
  protected final String TEXT_428 = ");";
  protected final String TEXT_429 = NL + "\t\t";
  protected final String TEXT_430 = "g";
  protected final String TEXT_431 = " = createEGenericType(";
  protected final String TEXT_432 = ");";
  protected final String TEXT_433 = NL + "\t\tg";
  protected final String TEXT_434 = ".";
  protected final String TEXT_435 = "(g";
  protected final String TEXT_436 = ");";
  protected final String TEXT_437 = NL + "\t\tinitEReference(get";
  protected final String TEXT_438 = "(), ";
  protected final String TEXT_439 = "g1";
  protected final String TEXT_440 = ".get";
  protected final String TEXT_441 = "()";
  protected final String TEXT_442 = ", ";
  protected final String TEXT_443 = ", \"";
  protected final String TEXT_444 = "\", ";
  protected final String TEXT_445 = ", ";
  protected final String TEXT_446 = ", ";
  protected final String TEXT_447 = ", ";
  protected final String TEXT_448 = ", ";
  protected final String TEXT_449 = ", ";
  protected final String TEXT_450 = ", ";
  protected final String TEXT_451 = ", ";
  protected final String TEXT_452 = ", ";
  protected final String TEXT_453 = ", ";
  protected final String TEXT_454 = ", ";
  protected final String TEXT_455 = ", ";
  protected final String TEXT_456 = ", ";
  protected final String TEXT_457 = ");";
  protected final String TEXT_458 = NL + "\t\tget";
  protected final String TEXT_459 = "().getEKeys().add(";
  protected final String TEXT_460 = ".get";
  protected final String TEXT_461 = "());";
  protected final String TEXT_462 = NL + "\t\tinitEAttribute(get";
  protected final String TEXT_463 = "(), ";
  protected final String TEXT_464 = "g1";
  protected final String TEXT_465 = ".get";
  protected final String TEXT_466 = "()";
  protected final String TEXT_467 = ", \"";
  protected final String TEXT_468 = "\", ";
  protected final String TEXT_469 = ", ";
  protected final String TEXT_470 = ", ";
  protected final String TEXT_471 = ", ";
  protected final String TEXT_472 = ", ";
  protected final String TEXT_473 = ", ";
  protected final String TEXT_474 = ", ";
  protected final String TEXT_475 = ", ";
  protected final String TEXT_476 = ", ";
  protected final String TEXT_477 = ", ";
  protected final String TEXT_478 = ", ";
  protected final String TEXT_479 = ", ";
  protected final String TEXT_480 = ");";
  protected final String TEXT_481 = NL;
  protected final String TEXT_482 = NL + "\t\t";
  protected final String TEXT_483 = "initEOperation(get";
  protected final String TEXT_484 = "()";
  protected final String TEXT_485 = "addEOperation(";
  protected final String TEXT_486 = ", ";
  protected final String TEXT_487 = "null";
  protected final String TEXT_488 = ".get";
  protected final String TEXT_489 = "()";
  protected final String TEXT_490 = ", \"";
  protected final String TEXT_491 = "\", ";
  protected final String TEXT_492 = ", ";
  protected final String TEXT_493 = ", ";
  protected final String TEXT_494 = ", ";
  protected final String TEXT_495 = ");";
  protected final String TEXT_496 = NL + "\t\t";
  protected final String TEXT_497 = "initEOperation(get";
  protected final String TEXT_498 = "()";
  protected final String TEXT_499 = "addEOperation(";
  protected final String TEXT_500 = ", ";
  protected final String TEXT_501 = ".get";
  protected final String TEXT_502 = "(), \"";
  protected final String TEXT_503 = "\", ";
  protected final String TEXT_504 = ", ";
  protected final String TEXT_505 = ", ";
  protected final String TEXT_506 = ", ";
  protected final String TEXT_507 = ");";
  protected final String TEXT_508 = NL + "\t\t";
  protected final String TEXT_509 = "initEOperation(get";
  protected final String TEXT_510 = "()";
  protected final String TEXT_511 = "addEOperation(";
  protected final String TEXT_512 = ", ";
  protected final String TEXT_513 = ".get";
  protected final String TEXT_514 = "(), \"";
  protected final String TEXT_515 = "\", ";
  protected final String TEXT_516 = ", ";
  protected final String TEXT_517 = ");";
  protected final String TEXT_518 = NL + "\t\t";
  protected final String TEXT_519 = "initEOperation(get";
  protected final String TEXT_520 = "()";
  protected final String TEXT_521 = "addEOperation(";
  protected final String TEXT_522 = ", null, \"";
  protected final String TEXT_523 = "\");";
  protected final String TEXT_524 = NL + "\t\t";
  protected final String TEXT_525 = "addETypeParameter(op, \"";
  protected final String TEXT_526 = "\");";
  protected final String TEXT_527 = NL + "\t\t";
  protected final String TEXT_528 = "g";
  protected final String TEXT_529 = " = createEGenericType(";
  protected final String TEXT_530 = ");";
  protected final String TEXT_531 = NL + "\t\tg";
  protected final String TEXT_532 = ".";
  protected final String TEXT_533 = "(g";
  protected final String TEXT_534 = ");";
  protected final String TEXT_535 = NL + "\t\tt";
  protected final String TEXT_536 = ".getEBounds().add(g1);";
  protected final String TEXT_537 = NL + "\t\t";
  protected final String TEXT_538 = "g";
  protected final String TEXT_539 = " = createEGenericType(";
  protected final String TEXT_540 = ");";
  protected final String TEXT_541 = NL + "\t\tg";
  protected final String TEXT_542 = ".";
  protected final String TEXT_543 = "(g";
  protected final String TEXT_544 = ");";
  protected final String TEXT_545 = NL + "\t\taddEParameter(op, ";
  protected final String TEXT_546 = "g1";
  protected final String TEXT_547 = ".get";
  protected final String TEXT_548 = "()";
  protected final String TEXT_549 = ", \"";
  protected final String TEXT_550 = "\", ";
  protected final String TEXT_551 = ", ";
  protected final String TEXT_552 = ", ";
  protected final String TEXT_553 = ", ";
  protected final String TEXT_554 = ");";
  protected final String TEXT_555 = NL + "\t\taddEParameter(op, ";
  protected final String TEXT_556 = "g1";
  protected final String TEXT_557 = ".get";
  protected final String TEXT_558 = "()";
  protected final String TEXT_559 = ", \"";
  protected final String TEXT_560 = "\", ";
  protected final String TEXT_561 = ", ";
  protected final String TEXT_562 = ", ";
  protected final String TEXT_563 = ", ";
  protected final String TEXT_564 = ");";
  protected final String TEXT_565 = NL + "\t\taddEParameter(op, ";
  protected final String TEXT_566 = "g1";
  protected final String TEXT_567 = ".get";
  protected final String TEXT_568 = "()";
  protected final String TEXT_569 = ", \"";
  protected final String TEXT_570 = "\", ";
  protected final String TEXT_571 = ", ";
  protected final String TEXT_572 = ");";
  protected final String TEXT_573 = NL + "\t\t";
  protected final String TEXT_574 = "g";
  protected final String TEXT_575 = " = createEGenericType(";
  protected final String TEXT_576 = ");";
  protected final String TEXT_577 = NL + "\t\tg";
  protected final String TEXT_578 = ".";
  protected final String TEXT_579 = "(g";
  protected final String TEXT_580 = ");";
  protected final String TEXT_581 = NL + "\t\taddEException(op, g";
  protected final String TEXT_582 = ");";
  protected final String TEXT_583 = NL + "\t\taddEException(op, ";
  protected final String TEXT_584 = ".get";
  protected final String TEXT_585 = "());";
  protected final String TEXT_586 = NL + "\t\t";
  protected final String TEXT_587 = "g";
  protected final String TEXT_588 = " = createEGenericType(";
  protected final String TEXT_589 = ");";
  protected final String TEXT_590 = NL + "\t\tg";
  protected final String TEXT_591 = ".";
  protected final String TEXT_592 = "(g";
  protected final String TEXT_593 = ");";
  protected final String TEXT_594 = NL + "\t\tinitEOperation(op, g1);";
  protected final String TEXT_595 = NL;
  protected final String TEXT_596 = NL + NL + "\t\t// Initialize enums and add enum literals";
  protected final String TEXT_597 = NL + "\t\tinitEEnum(";
  protected final String TEXT_598 = ", ";
  protected final String TEXT_599 = ".class, \"";
  protected final String TEXT_600 = "\");";
  protected final String TEXT_601 = NL + "\t\taddEEnumLiteral(";
  protected final String TEXT_602 = ", ";
  protected final String TEXT_603 = ".";
  protected final String TEXT_604 = ");";
  protected final String TEXT_605 = NL;
  protected final String TEXT_606 = NL + NL + "\t\t// Initialize data types";
  protected final String TEXT_607 = NL + "\t\tinitEDataType(";
  protected final String TEXT_608 = ", ";
  protected final String TEXT_609 = ".class, \"";
  protected final String TEXT_610 = "\", ";
  protected final String TEXT_611 = ", ";
  protected final String TEXT_612 = ", \"";
  protected final String TEXT_613 = "\"";
  protected final String TEXT_614 = ");";
  protected final String TEXT_615 = NL + NL + "\t\t// Create resource" + NL + "\t\tcreateResource(";
  protected final String TEXT_616 = ");";
  protected final String TEXT_617 = NL + NL + "\t\t// Create annotations";
  protected final String TEXT_618 = NL + "\t\t// ";
  protected final String TEXT_619 = NL + "\t\tcreate";
  protected final String TEXT_620 = "Annotations();";
  protected final String TEXT_621 = NL + "\t}" + NL;
  protected final String TEXT_622 = NL + "\t/**" + NL + "\t * Initializes the annotations for <b>";
  protected final String TEXT_623 = "</b>." + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */" + NL + "\tprotected void create";
  protected final String TEXT_624 = "Annotations()" + NL + "\t{" + NL + "\t\tString source = ";
  protected final String TEXT_625 = "null;";
  protected final String TEXT_626 = "\"";
  protected final String TEXT_627 = "\";";
  protected final String TEXT_628 = "\t" + NL + "\t\taddAnnotation" + NL + "\t\t  (";
  protected final String TEXT_629 = ", " + NL + "\t\t   source, " + NL + "\t\t   new String[] " + NL + "\t\t   {";
  protected final String TEXT_630 = NL + "\t\t\t ";
  protected final String TEXT_631 = ", ";
  protected final String TEXT_632 = NL + "\t\t   }";
  protected final String TEXT_633 = ");";
  protected final String TEXT_634 = ",";
  protected final String TEXT_635 = NL + "\t\t   new ";
  protected final String TEXT_636 = "[] " + NL + "\t\t   {";
  protected final String TEXT_637 = NL + "\t\t\t ";
  protected final String TEXT_638 = ".createURI(";
  protected final String TEXT_639 = ".";
  protected final String TEXT_640 = "eNS_URI).appendFragment(\"";
  protected final String TEXT_641 = "\")";
  protected final String TEXT_642 = ",";
  protected final String TEXT_643 = NL + "\t\t   });";
  protected final String TEXT_644 = NL + "\t\taddAnnotation" + NL + "\t\t  (";
  protected final String TEXT_645 = ", " + NL + "\t\t   ";
  protected final String TEXT_646 = "new boolean[] { ";
  protected final String TEXT_647 = " }";
  protected final String TEXT_648 = "," + NL + "\t\t   ";
  protected final String TEXT_649 = "null,";
  protected final String TEXT_650 = "\"";
  protected final String TEXT_651 = "\",";
  protected final String TEXT_652 = NL + "\t\t   new String[] " + NL + "\t\t   {";
  protected final String TEXT_653 = NL + "\t\t\t ";
  protected final String TEXT_654 = ", ";
  protected final String TEXT_655 = NL + "\t\t   }";
  protected final String TEXT_656 = ");";
  protected final String TEXT_657 = ",";
  protected final String TEXT_658 = NL + "\t\t   new ";
  protected final String TEXT_659 = "[] " + NL + "\t\t   {";
  protected final String TEXT_660 = NL + "\t\t\t ";
  protected final String TEXT_661 = ".createURI(";
  protected final String TEXT_662 = ".";
  protected final String TEXT_663 = "eNS_URI).appendFragment(\"";
  protected final String TEXT_664 = "\")";
  protected final String TEXT_665 = ",";
  protected final String TEXT_666 = NL + "\t\t   });";
  protected final String TEXT_667 = NL + "\t}" + NL;
  protected final String TEXT_668 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */" + NL + "\tprivate boolean isLoaded = false;" + NL + "" + NL + "\t/**" + NL + "\t * Laods the package and any sub-packages from their serialized form." + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */" + NL + "\tpublic void loadPackage()" + NL + "\t{" + NL + "\t\tif (isLoaded) return;" + NL + "\t\tisLoaded = true;" + NL + "" + NL + "\t\t";
  protected final String TEXT_669 = " url = getClass().getResource(packageFilename);" + NL + "\t\tif (url == null)" + NL + "\t\t{" + NL + "\t\t\tthrow new RuntimeException(\"Missing serialized package: \" + packageFilename);";
  protected final String TEXT_670 = NL + "\t\t}" + NL + "\t\t";
  protected final String TEXT_671 = " uri = ";
  protected final String TEXT_672 = ".createURI(url.toString());" + NL + "\t\t";
  protected final String TEXT_673 = " resource = new ";
  protected final String TEXT_674 = "().createResource(uri);" + NL + "\t\ttry" + NL + "\t\t{" + NL + "\t\t\tresource.load(null);" + NL + "\t\t}" + NL + "\t\tcatch (";
  protected final String TEXT_675 = " exception)" + NL + "\t\t{" + NL + "\t\t\tthrow new ";
  protected final String TEXT_676 = "(exception);" + NL + "\t\t}" + NL + "\t\tinitializeFromLoadedEPackage(this, (";
  protected final String TEXT_677 = ")resource.getContents().get(0));" + NL + "\t\tcreateResource(eNS_URI);" + NL + "\t}" + NL;
  protected final String TEXT_678 = NL + NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */" + NL + "\tprivate boolean isFixed = false;" + NL + "" + NL + "\t/**" + NL + "\t * Fixes up the loaded package, to make it appear as if it had been programmatically built." + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */" + NL + "\tpublic void fixPackageContents()" + NL + "\t{" + NL + "\t\tif (isFixed) return;" + NL + "\t\tisFixed = true;" + NL + "\t\tfixEClassifiers();" + NL + "\t}" + NL + "" + NL + "\t/**" + NL + "\t * Sets the instance class on the given classifier." + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */";
  protected final String TEXT_679 = NL + "\t@Override";
  protected final String TEXT_680 = NL + "\tprotected void fixInstanceClass(";
  protected final String TEXT_681 = " eClassifier)" + NL + "\t{" + NL + "\t\tif (eClassifier.getInstanceClassName() == null)" + NL + "\t\t{";
  protected final String TEXT_682 = NL + "\t\t\teClassifier.setInstanceClassName(\"";
  protected final String TEXT_683 = ".\" + eClassifier.getName());";
  protected final String TEXT_684 = NL + "\t\t\tsetGeneratedClassName(eClassifier);";
  protected final String TEXT_685 = NL + "\t\t\tswitch (eClassifier.getClassifierID())" + NL + "\t\t\t{";
  protected final String TEXT_686 = NL + "\t\t\t\tcase ";
  protected final String TEXT_687 = ":";
  protected final String TEXT_688 = NL + "\t\t\t\t{" + NL + "\t\t\t\t\tbreak;" + NL + "\t\t\t\t}" + NL + "\t\t\t\tdefault:" + NL + "\t\t\t\t{" + NL + "\t\t\t\t\teClassifier.setInstanceClassName(\"";
  protected final String TEXT_689 = ".\" + eClassifier.getName());";
  protected final String TEXT_690 = NL + "\t\t\t\t\tsetGeneratedClassName(eClassifier);" + NL + "\t\t\t\t\tbreak;" + NL + "\t\t\t\t}" + NL + "\t\t\t}";
  protected final String TEXT_691 = NL + "\t\t}" + NL + "\t}" + NL;
  protected final String TEXT_692 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */" + NL + "\tprotected ";
  protected final String TEXT_693 = " addEOperation(";
  protected final String TEXT_694 = " owner, ";
  protected final String TEXT_695 = " type, String name, int lowerBound, int upperBound, boolean isUnique, boolean isOrdered)" + NL + "\t{" + NL + "\t\t";
  protected final String TEXT_696 = " o = addEOperation(owner, type, name, lowerBound, upperBound);" + NL + "\t\to.setUnique(isUnique);" + NL + "\t\to.setOrdered(isOrdered);" + NL + "\t\treturn o;" + NL + "\t}" + NL + "\t";
  protected final String TEXT_697 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */" + NL + "\tprotected ";
  protected final String TEXT_698 = " addEParameter(";
  protected final String TEXT_699 = " owner, ";
  protected final String TEXT_700 = " type, String name, int lowerBound, int upperBound, boolean isUnique, boolean isOrdered)" + NL + "\t{" + NL + "\t\t";
  protected final String TEXT_701 = " p = ecoreFactory.createEParameter();" + NL + "\t\tp.setEType(type);" + NL + "\t\tp.setName(name);" + NL + "\t\tp.setLowerBound(lowerBound);" + NL + "\t\tp.setUpperBound(upperBound);" + NL + "\t\tp.setUnique(isUnique);" + NL + "\t\tp.setOrdered(isOrdered);" + NL + "\t\towner.getEParameters().add(p);" + NL + "\t\treturn p;" + NL + "\t}" + NL + "\t";
  protected final String TEXT_702 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * Defines literals for the meta objects that represent" + NL + "\t * <ul>" + NL + "\t *   <li>each class,</li>" + NL + "\t *   <li>each feature of each class,</li>";
  protected final String TEXT_703 = NL + "\t *   <li>each operation of each class,</li>";
  protected final String TEXT_704 = NL + "\t *   <li>each enum,</li>" + NL + "\t *   <li>and each data type</li>" + NL + "\t * </ul>" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */" + NL + "\t";
  protected final String TEXT_705 = "public ";
  protected final String TEXT_706 = "interface Literals" + NL + "\t{";
  protected final String TEXT_707 = NL + "\t\t/**";
  protected final String TEXT_708 = NL + "\t\t * The meta object literal for the '{@link ";
  protected final String TEXT_709 = " <em>";
  protected final String TEXT_710 = "</em>}' class." + NL + "\t\t * <!-- begin-user-doc -->" + NL + "\t\t * <!-- end-user-doc -->" + NL + "\t\t * @see ";
  protected final String TEXT_711 = NL + "\t\t * The meta object literal for the '{@link ";
  protected final String TEXT_712 = " <em>";
  protected final String TEXT_713 = "</em>}' class." + NL + "\t\t * <!-- begin-user-doc -->" + NL + "\t\t * <!-- end-user-doc -->" + NL + "\t\t * @see ";
  protected final String TEXT_714 = NL + "\t\t * The meta object literal for the '{@link ";
  protected final String TEXT_715 = " <em>";
  protected final String TEXT_716 = "</em>}' enum." + NL + "\t\t * <!-- begin-user-doc -->" + NL + "\t\t * <!-- end-user-doc -->" + NL + "\t\t * @see ";
  protected final String TEXT_717 = NL + "\t\t * The meta object literal for the '<em>";
  protected final String TEXT_718 = "</em>' data type." + NL + "\t\t * <!-- begin-user-doc -->" + NL + "\t\t * <!-- end-user-doc -->";
  protected final String TEXT_719 = NL + "\t\t * @see ";
  protected final String TEXT_720 = NL + "\t\t * @see ";
  protected final String TEXT_721 = "#get";
  protected final String TEXT_722 = "()";
  protected final String TEXT_723 = NL + "\t\t * ";
  protected final String TEXT_724 = NL + "\t\t * @generated" + NL + "\t\t */";
  protected final String TEXT_725 = NL + "\t\t@Deprecated";
  protected final String TEXT_726 = NL + "\t\t";
  protected final String TEXT_727 = " ";
  protected final String TEXT_728 = " = eINSTANCE.get";
  protected final String TEXT_729 = "();" + NL;
  protected final String TEXT_730 = NL + "\t\t/**" + NL + "\t\t * The meta object literal for the '<em><b>";
  protected final String TEXT_731 = "</b></em>' ";
  protected final String TEXT_732 = " feature." + NL + "\t\t * <!-- begin-user-doc -->" + NL + "\t\t * <!-- end-user-doc -->";
  protected final String TEXT_733 = NL + "\t\t * ";
  protected final String TEXT_734 = NL + "\t\t * @generated" + NL + "\t\t */";
  protected final String TEXT_735 = NL + "\t\t@Deprecated";
  protected final String TEXT_736 = NL + "\t\t";
  protected final String TEXT_737 = " ";
  protected final String TEXT_738 = " = eINSTANCE.get";
  protected final String TEXT_739 = "();" + NL;
  protected final String TEXT_740 = NL + "\t\t/**" + NL + "\t\t * The meta object literal for the '<em><b>";
  protected final String TEXT_741 = "</b></em>' operation." + NL + "\t\t * <!-- begin-user-doc -->" + NL + "\t\t * <!-- end-user-doc -->";
  protected final String TEXT_742 = NL + "\t\t * ";
  protected final String TEXT_743 = NL + "\t\t * @generated" + NL + "\t\t */";
  protected final String TEXT_744 = NL + "\t\t@Deprecated";
  protected final String TEXT_745 = NL + "\t\t";
  protected final String TEXT_746 = " ";
  protected final String TEXT_747 = " = eINSTANCE.get";
  protected final String TEXT_748 = "();" + NL;
  protected final String TEXT_749 = NL + "\t}" + NL;
  protected final String TEXT_750 = NL + "} //";
  protected final String TEXT_751 = NL;

  public String generate(Object argument)
  {
    final StringBuffer stringBuffer = new StringBuffer();
    
/**
 * Copyright (c) 2002-2010 IBM Corporation and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *   IBM - Initial API and implementation
 */

    final GenPackage genPackage = (GenPackage)((Object[])argument)[0]; final GenModel genModel=genPackage.getGenModel();
    final boolean isJDK50 = genModel.getComplianceLevel().getValue() >= GenJDKLevel.JDK50;
    boolean isInterface = Boolean.TRUE.equals(((Object[])argument)[1]); boolean isImplementation = Boolean.TRUE.equals(((Object[])argument)[2]);
    boolean packageNeedsSuppressDeprecation = isJDK50 && GenModelUtil.hasAPIDeprecatedTag(genPackage.getOrderedGenClassifiers()) && !genPackage.hasAPIDeprecatedTag();
    String publicStaticFinalFlag = isImplementation ? "public static final " : "";
    boolean needsAddEOperation = false;
    boolean needsAddEParameter = false;
    stringBuffer.append(TEXT_1);
    stringBuffer.append(TEXT_2);
    {GenBase copyrightHolder = argument instanceof GenBase ? (GenBase)argument : argument instanceof Object[] && ((Object[])argument)[0] instanceof GenBase ? (GenBase)((Object[])argument)[0] : null;
    if (copyrightHolder != null && copyrightHolder.hasCopyright()) {
    stringBuffer.append(TEXT_3);
    stringBuffer.append(copyrightHolder.getCopyright(copyrightHolder.getGenModel().getIndentation(stringBuffer)));
    }}
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
    genModel.addPseudoImport("org.eclipse.emf.ecore.impl.EPackageImpl.EBasicWhiteList");
    genModel.addPseudoImport("org.eclipse.emf.ecore.impl.MinimalEObjectImpl.Container");
    genModel.addPseudoImport("org.eclipse.emf.ecore.impl.MinimalEObjectImpl.Container.Dynamic");
    if (genPackage.isLiteralsInterface()) {
    genModel.addPseudoImport(genPackage.getQualifiedPackageInterfaceName() + ".Literals");
    }
    for (GenClassifier genClassifier : genPackage.getOrderedGenClassifiers()) genModel.addPseudoImport(genPackage.getQualifiedPackageInterfaceName() + "." + genPackage.getClassifierID(genClassifier));
    }
    if (isInterface) {
    stringBuffer.append(TEXT_10);
    if (genModel.isOperationReflection()) {
    stringBuffer.append(TEXT_11);
    }
    stringBuffer.append(TEXT_12);
    if (genPackage.hasDocumentation()) {
    stringBuffer.append(TEXT_13);
    stringBuffer.append(genPackage.getDocumentation(genModel.getIndentation(stringBuffer)));
    stringBuffer.append(TEXT_14);
    }
    stringBuffer.append(TEXT_15);
    stringBuffer.append(genPackage.getQualifiedFactoryInterfaceName());
    if (!genModel.isSuppressEMFModelTags()) { boolean first = true; for (StringTokenizer stringTokenizer = new StringTokenizer(genPackage.getModelInfo(), "\n\r"); stringTokenizer.hasMoreTokens(); ) { String modelInfo = stringTokenizer.nextToken(); if (first) { first = false;
    stringBuffer.append(TEXT_16);
    stringBuffer.append(modelInfo);
    } else {
    stringBuffer.append(TEXT_17);
    stringBuffer.append(modelInfo);
    }} if (first) {
    stringBuffer.append(TEXT_18);
    }}
    stringBuffer.append(TEXT_19);
    } else {
    stringBuffer.append(TEXT_20);
    if (genPackage.hasAPITags()) {
    stringBuffer.append(TEXT_21);
    stringBuffer.append(genPackage.getAPITags(genModel.getIndentation(stringBuffer)));
    }
    stringBuffer.append(TEXT_22);
    }
    if (isJDK50 && genPackage.hasAPIDeprecatedTag()) {
    stringBuffer.append(TEXT_23);
    }
    if (isImplementation) {
    if (packageNeedsSuppressDeprecation) {
    stringBuffer.append(TEXT_24);
    }
    stringBuffer.append(TEXT_25);
    stringBuffer.append(genPackage.getPackageClassName());
    stringBuffer.append(TEXT_26);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.impl.EPackageImpl"));
    if (!isInterface){
    stringBuffer.append(TEXT_27);
    stringBuffer.append(genPackage.getImportedPackageInterfaceName());
    }
    } else {
    stringBuffer.append(TEXT_28);
    stringBuffer.append(genPackage.getPackageInterfaceName());
    stringBuffer.append(TEXT_29);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.EPackage"));
    }
    stringBuffer.append(TEXT_30);
    if (genModel.hasCopyrightField()) {
    stringBuffer.append(TEXT_31);
    stringBuffer.append(publicStaticFinalFlag);
    stringBuffer.append(genModel.getImportedName("java.lang.String"));
    stringBuffer.append(TEXT_32);
    stringBuffer.append(genModel.getCopyrightFieldLiteral());
    stringBuffer.append(TEXT_33);
    stringBuffer.append(genModel.getNonNLS());
    stringBuffer.append(TEXT_34);
    }
    if (isInterface) {
    stringBuffer.append(TEXT_35);
    stringBuffer.append(publicStaticFinalFlag);
    stringBuffer.append(genModel.getImportedName("java.lang.String"));
    stringBuffer.append(TEXT_36);
    stringBuffer.append(genPackage.getPackageName());
    stringBuffer.append(TEXT_37);
    stringBuffer.append(genModel.getNonNLS());
    stringBuffer.append(TEXT_38);
    stringBuffer.append(publicStaticFinalFlag);
    stringBuffer.append(genModel.getImportedName("java.lang.String"));
    stringBuffer.append(TEXT_39);
    stringBuffer.append(genPackage.getNSURI());
    stringBuffer.append(TEXT_40);
    stringBuffer.append(genModel.getNonNLS());
    stringBuffer.append(TEXT_41);
    stringBuffer.append(publicStaticFinalFlag);
    stringBuffer.append(genModel.getImportedName("java.lang.String"));
    stringBuffer.append(TEXT_42);
    stringBuffer.append(genPackage.getNSName());
    stringBuffer.append(TEXT_43);
    stringBuffer.append(genModel.getNonNLS());
    if (genPackage.isContentType()) {
    stringBuffer.append(TEXT_44);
    stringBuffer.append(publicStaticFinalFlag);
    stringBuffer.append(genModel.getImportedName("java.lang.String"));
    stringBuffer.append(TEXT_45);
    stringBuffer.append(genPackage.getContentTypeIdentifier());
    stringBuffer.append(TEXT_46);
    stringBuffer.append(genModel.getNonNLS());
    }
    stringBuffer.append(TEXT_47);
    stringBuffer.append(publicStaticFinalFlag);
    stringBuffer.append(genPackage.getPackageInterfaceName());
    stringBuffer.append(TEXT_48);
    stringBuffer.append(genPackage.getQualifiedPackageClassName());
    stringBuffer.append(TEXT_49);
    for (GenClassifier genClassifier : genPackage.getOrderedGenClassifiers()) {
    stringBuffer.append(TEXT_50);
    if (genClassifier instanceof GenClass) { GenClass genClass = (GenClass)genClassifier;
    if (!genClass.isInterface()) {
    stringBuffer.append(TEXT_51);
    stringBuffer.append(genClass.getQualifiedClassName());
    stringBuffer.append(TEXT_52);
    stringBuffer.append(genClass.getFormattedName());
    stringBuffer.append(TEXT_53);
    stringBuffer.append(genClass.getQualifiedClassName());
    } else {
    stringBuffer.append(TEXT_54);
    stringBuffer.append(genClass.getQualifiedInterfaceName());
    stringBuffer.append(TEXT_55);
    stringBuffer.append(genClass.getFormattedName());
    stringBuffer.append(TEXT_56);
    stringBuffer.append(genClass.getQualifiedInterfaceName());
    }
    } else if (genClassifier instanceof GenEnum) { GenEnum genEnum = (GenEnum)genClassifier;
    stringBuffer.append(TEXT_57);
    stringBuffer.append(genEnum.getQualifiedName());
    stringBuffer.append(TEXT_58);
    stringBuffer.append(genEnum.getFormattedName());
    stringBuffer.append(TEXT_59);
    stringBuffer.append(genEnum.getQualifiedName());
    } else if (genClassifier instanceof GenDataType) { GenDataType genDataType = (GenDataType)genClassifier;
    stringBuffer.append(TEXT_60);
    stringBuffer.append(genDataType.getFormattedName());
    stringBuffer.append(TEXT_61);
    if (!genDataType.isPrimitiveType() && !genDataType.isArrayType()) {
    stringBuffer.append(TEXT_62);
    stringBuffer.append(genDataType.getRawInstanceClassName());
    }
    }
    stringBuffer.append(TEXT_63);
    stringBuffer.append(genPackage.getQualifiedPackageClassName());
    stringBuffer.append(TEXT_64);
    stringBuffer.append(genClassifier.getClassifierAccessorName());
    stringBuffer.append(TEXT_65);
    if (genClassifier.hasAPITags()) {
    stringBuffer.append(TEXT_66);
    stringBuffer.append(genClassifier.getAPITags(genModel.getIndentation(stringBuffer)));
    }
    stringBuffer.append(TEXT_67);
    if (isJDK50 && genClassifier.hasAPIDeprecatedTag()) {
    stringBuffer.append(TEXT_68);
    }
    stringBuffer.append(TEXT_69);
    stringBuffer.append(publicStaticFinalFlag);
    stringBuffer.append(TEXT_70);
    stringBuffer.append(genPackage.getClassifierID(genClassifier));
    stringBuffer.append(TEXT_71);
    stringBuffer.append(genPackage.getClassifierValue(genClassifier));
    stringBuffer.append(TEXT_72);
    if (genClassifier instanceof GenClass) { GenClass genClass = (GenClass)genClassifier;
    for (GenFeature genFeature : genClass.getAllGenFeatures()) {
    stringBuffer.append(TEXT_73);
    stringBuffer.append(genFeature.getFormattedName());
    stringBuffer.append(TEXT_74);
    stringBuffer.append(genFeature.getFeatureKind());
    stringBuffer.append(TEXT_75);
    if (genFeature.hasImplicitAPITags()) {
    stringBuffer.append(TEXT_76);
    stringBuffer.append(genFeature.getImplicitAPITags(genModel.getIndentation(stringBuffer)));
    }
    stringBuffer.append(TEXT_77);
    if (isJDK50 && genFeature.hasImplicitAPIDeprecatedTag()) {
    stringBuffer.append(TEXT_78);
    }
    stringBuffer.append(TEXT_79);
    stringBuffer.append(publicStaticFinalFlag);
    stringBuffer.append(TEXT_80);
    stringBuffer.append(genClass.getFeatureID(genFeature));
    stringBuffer.append(TEXT_81);
    stringBuffer.append(genClass.getFeatureValue(genFeature));
    stringBuffer.append(TEXT_82);
    }
    stringBuffer.append(TEXT_83);
    stringBuffer.append(genClass.getFormattedName());
    stringBuffer.append(TEXT_84);
    if (genClass.hasAPITags()) {
    stringBuffer.append(TEXT_85);
    stringBuffer.append(genClass.getAPITags(genModel.getIndentation(stringBuffer)));
    }
    stringBuffer.append(TEXT_86);
    if (isJDK50 && genClass.hasAPIDeprecatedTag()) {
    stringBuffer.append(TEXT_87);
    }
    stringBuffer.append(TEXT_88);
    stringBuffer.append(publicStaticFinalFlag);
    stringBuffer.append(TEXT_89);
    stringBuffer.append(genClass.getFeatureCountID());
    stringBuffer.append(TEXT_90);
    stringBuffer.append(genClass.getFeatureCountValue());
    stringBuffer.append(TEXT_91);
    if (genModel.isOperationReflection()) {
    for (GenOperation genOperation : genClass.getAllGenOperations(false)) {
    if (genClass.getOverrideGenOperation(genOperation) == null) {
    stringBuffer.append(TEXT_92);
    stringBuffer.append(genOperation.getFormattedName());
    stringBuffer.append(TEXT_93);
    if (genOperation.hasImplicitAPITags()) {
    stringBuffer.append(TEXT_94);
    stringBuffer.append(genOperation.getImplicitAPITags(genModel.getIndentation(stringBuffer)));
    }
    stringBuffer.append(TEXT_95);
    if (isJDK50 && genOperation.hasImplicitAPIDeprecatedTag()) {
    stringBuffer.append(TEXT_96);
    }
    stringBuffer.append(TEXT_97);
    stringBuffer.append(publicStaticFinalFlag);
    stringBuffer.append(TEXT_98);
    stringBuffer.append(genClass.getOperationID(genOperation, false));
    stringBuffer.append(TEXT_99);
    stringBuffer.append(genClass.getOperationValue(genOperation));
    stringBuffer.append(TEXT_100);
    }
    }
    stringBuffer.append(TEXT_101);
    stringBuffer.append(genClass.getFormattedName());
    stringBuffer.append(TEXT_102);
    if (genClass.hasAPITags()) {
    stringBuffer.append(TEXT_103);
    stringBuffer.append(genClass.getAPITags(genModel.getIndentation(stringBuffer)));
    }
    stringBuffer.append(TEXT_104);
    if (isJDK50 && genClass.hasAPIDeprecatedTag()) {
    stringBuffer.append(TEXT_105);
    }
    stringBuffer.append(TEXT_106);
    stringBuffer.append(publicStaticFinalFlag);
    stringBuffer.append(TEXT_107);
    stringBuffer.append(genClass.getOperationCountID());
    stringBuffer.append(TEXT_108);
    stringBuffer.append(genClass.getOperationCountValue());
    stringBuffer.append(TEXT_109);
    }
    }
    }
    }
    if (isImplementation) {
    if (genPackage.isLoadingInitialization()) {
    stringBuffer.append(TEXT_110);
    stringBuffer.append(genPackage.getSerializedPackageFilename());
    stringBuffer.append(TEXT_111);
    stringBuffer.append(genModel.getNonNLS());
    stringBuffer.append(TEXT_112);
    }
    for (GenClassifier genClassifier : genPackage.getGenClassifiers()) {
    stringBuffer.append(TEXT_113);
    if (genClassifier.hasAPITags()) {
    stringBuffer.append(TEXT_114);
    stringBuffer.append(genClassifier.getAPITags(genModel.getIndentation(stringBuffer)));
    }
    stringBuffer.append(TEXT_115);
    if (isJDK50 && genClassifier.hasAPIDeprecatedTag()) {
    stringBuffer.append(TEXT_116);
    }
    stringBuffer.append(TEXT_117);
    stringBuffer.append(genClassifier.getImportedMetaType());
    stringBuffer.append(TEXT_118);
    stringBuffer.append(genClassifier.getClassifierInstanceName());
    stringBuffer.append(TEXT_119);
    }
    stringBuffer.append(TEXT_120);
    stringBuffer.append(genPackage.getQualifiedPackageInterfaceName());
    stringBuffer.append(TEXT_121);
    stringBuffer.append(genPackage.getPackageClassName());
    stringBuffer.append(TEXT_122);
    stringBuffer.append(genPackage.getQualifiedEFactoryInstanceAccessor());
    stringBuffer.append(TEXT_123);
    stringBuffer.append(genPackage.getImportedPackageInterfaceName());
    stringBuffer.append(TEXT_124);
    if (!genPackage.isLoadedInitialization()) {
    stringBuffer.append(TEXT_125);
    }
    stringBuffer.append(TEXT_126);
    stringBuffer.append(genPackage.getImportedPackageInterfaceName());
    stringBuffer.append(TEXT_127);
    stringBuffer.append(genPackage.getImportedPackageInterfaceName());
    stringBuffer.append(TEXT_128);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.EPackage"));
    stringBuffer.append(TEXT_129);
    stringBuffer.append(genPackage.getImportedPackageInterfaceName());
    stringBuffer.append(TEXT_130);
    if (genModel.getRuntimePlatform() == GenRuntimePlatform.GWT) {
    stringBuffer.append(TEXT_131);
    }
    stringBuffer.append(TEXT_132);
    stringBuffer.append(genPackage.getPackageClassName());
    stringBuffer.append(TEXT_133);
    stringBuffer.append(genPackage.getBasicPackageName());
    stringBuffer.append(TEXT_134);
    stringBuffer.append(genPackage.getPackageClassName());
    stringBuffer.append(TEXT_135);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.EPackage"));
    stringBuffer.append(TEXT_136);
    stringBuffer.append(genPackage.getPackageClassName());
    stringBuffer.append(TEXT_137);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.EPackage"));
    stringBuffer.append(TEXT_138);
    stringBuffer.append(genPackage.getPackageClassName());
    stringBuffer.append(TEXT_139);
    if (!genPackage.getPackageSimpleDependencies().isEmpty()) {
    stringBuffer.append(TEXT_140);
    for (GenPackage dep : genPackage.getPackageSimpleDependencies()) {
    stringBuffer.append(TEXT_141);
    stringBuffer.append(dep.getImportedPackageInterfaceName());
    stringBuffer.append(TEXT_142);
    }
    stringBuffer.append(TEXT_143);
    }
    if (!genPackage.getPackageInterDependencies().isEmpty()) {
    stringBuffer.append(TEXT_144);
    for (GenPackage interdep : genPackage.getPackageInterDependencies()) {
    stringBuffer.append(TEXT_145);
    stringBuffer.append(interdep.getImportedPackageClassName());
    stringBuffer.append(TEXT_146);
    stringBuffer.append(genPackage.getPackageInstanceVariable(interdep));
    stringBuffer.append(TEXT_147);
    stringBuffer.append(interdep.getImportedPackageClassName());
    stringBuffer.append(TEXT_148);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.EPackage"));
    stringBuffer.append(TEXT_149);
    stringBuffer.append(interdep.getImportedPackageInterfaceName());
    stringBuffer.append(TEXT_150);
    stringBuffer.append(interdep.getImportedPackageClassName());
    stringBuffer.append(TEXT_151);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.EPackage"));
    stringBuffer.append(TEXT_152);
    stringBuffer.append(interdep.getImportedPackageInterfaceName());
    stringBuffer.append(TEXT_153);
    stringBuffer.append(interdep.getImportedPackageInterfaceName());
    stringBuffer.append(TEXT_154);
    }
    stringBuffer.append(TEXT_155);
    }
    if (genPackage.isLoadedInitialization() || !genPackage.getPackageLoadInterDependencies().isEmpty()) {
    stringBuffer.append(TEXT_156);
    if (genPackage.isLoadingInitialization()) {
    stringBuffer.append(TEXT_157);
    stringBuffer.append(genPackage.getBasicPackageName());
    stringBuffer.append(TEXT_158);
    }
    for (GenPackage interdep : genPackage.getPackageLoadInterDependencies()) {
    if (interdep.isLoadingInitialization()) {
    stringBuffer.append(TEXT_159);
    stringBuffer.append(genPackage.getPackageInstanceVariable(interdep));
    stringBuffer.append(TEXT_160);
    }
    }
    stringBuffer.append(TEXT_161);
    }
    if (!genPackage.isLoadedInitialization() || !genPackage.getPackageBuildInterDependencies().isEmpty()) {
    stringBuffer.append(TEXT_162);
    if (!genPackage.isLoadedInitialization()) {
    stringBuffer.append(TEXT_163);
    stringBuffer.append(genPackage.getBasicPackageName());
    stringBuffer.append(TEXT_164);
    }
    for (GenPackage interdep : genPackage.getPackageBuildInterDependencies()) {
    stringBuffer.append(TEXT_165);
    stringBuffer.append(genPackage.getPackageInstanceVariable(interdep));
    stringBuffer.append(TEXT_166);
    }
    stringBuffer.append(TEXT_167);
    if (!genPackage.isLoadedInitialization()) {
    stringBuffer.append(TEXT_168);
    stringBuffer.append(genPackage.getBasicPackageName());
    stringBuffer.append(TEXT_169);
    }
    for (GenPackage interdep : genPackage.getPackageBuildInterDependencies()) {
    stringBuffer.append(TEXT_170);
    stringBuffer.append(genPackage.getPackageInstanceVariable(interdep));
    stringBuffer.append(TEXT_171);
    }
    stringBuffer.append(TEXT_172);
    }
    if (genPackage.isLoadedInitialization() || !genPackage.getPackageLoadInterDependencies().isEmpty()) {
    stringBuffer.append(TEXT_173);
    if (genPackage.isLoadedInitialization()) {
    stringBuffer.append(TEXT_174);
    stringBuffer.append(genPackage.getBasicPackageName());
    stringBuffer.append(TEXT_175);
    }
    for (GenPackage interdep : genPackage.getPackageLoadInterDependencies()) {
    stringBuffer.append(TEXT_176);
    stringBuffer.append(genPackage.getPackageInstanceVariable(interdep));
    stringBuffer.append(TEXT_177);
    }
    stringBuffer.append(TEXT_178);
    }
    if (genPackage.hasConstraints()) {
    stringBuffer.append(TEXT_179);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.EValidator"));
    stringBuffer.append(TEXT_180);
    stringBuffer.append(genPackage.getBasicPackageName());
    stringBuffer.append(TEXT_181);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.EValidator"));
    stringBuffer.append(TEXT_182);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.EValidator"));
    stringBuffer.append(TEXT_183);
    stringBuffer.append(genPackage.getImportedValidatorClassName());
    stringBuffer.append(TEXT_184);
    }
    if (!genPackage.isEcorePackage()) {
    stringBuffer.append(TEXT_185);
    stringBuffer.append(genPackage.getBasicPackageName());
    stringBuffer.append(TEXT_186);
    }
    stringBuffer.append(TEXT_187);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.EPackage"));
    stringBuffer.append(TEXT_188);
    stringBuffer.append(genPackage.getImportedPackageInterfaceName());
    stringBuffer.append(TEXT_189);
    stringBuffer.append(genPackage.getBasicPackageName());
    stringBuffer.append(TEXT_190);
    stringBuffer.append(genPackage.getBasicPackageName());
    stringBuffer.append(TEXT_191);
    if (genModel.getRuntimePlatform() == GenRuntimePlatform.GWT) {
    stringBuffer.append(TEXT_192);
    Set<String> helpers = new HashSet<String>(); for (GenClassifier genClassifier : genPackage.getGenClassifiers()) {
    if (genClassifier instanceof GenClass) { GenClass genClass = (GenClass)genClassifier;
    if (!genClass.isDynamic()) { String theClass = genClass.isMapEntry() ? genClass.getImportedClassName() : genClass.getRawImportedInterfaceName(); if (helpers.add(theClass)) {
    stringBuffer.append(TEXT_193);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.util.Reflect"));
    stringBuffer.append(TEXT_194);
    stringBuffer.append(theClass);
    stringBuffer.append(TEXT_195);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.util.Reflect"));
    stringBuffer.append(TEXT_196);
    stringBuffer.append(genClass.isMapEntry() ? genClass.getImportedClassName() : genClass.getRawImportedInterfaceName() + genClass.getInterfaceWildTypeArguments());
    stringBuffer.append(TEXT_197);
    stringBuffer.append(theClass);
    stringBuffer.append(TEXT_198);
    }}
    } else if (genClassifier instanceof GenDataType) { GenDataType genDataType = (GenDataType)genClassifier;
    if (!genDataType.isPrimitiveType() && !genDataType.isObjectType()) { String theClass = genDataType.getRawImportedInstanceClassName(); if (helpers.add(theClass)) { 
    stringBuffer.append(TEXT_199);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.util.Reflect"));
    stringBuffer.append(TEXT_200);
    stringBuffer.append(theClass);
    stringBuffer.append(TEXT_201);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.util.Reflect"));
    stringBuffer.append(TEXT_202);
    stringBuffer.append(theClass);
    stringBuffer.append(TEXT_203);
    if (genDataType.isArrayType()) { String componentType = theClass; String indices = ""; while(componentType.endsWith("[]")) { componentType = componentType.substring(0, componentType.length() - 2); indices += "[]";}
    stringBuffer.append(TEXT_204);
    stringBuffer.append(componentType);
    stringBuffer.append(TEXT_205);
    stringBuffer.append(indices);
    stringBuffer.append(TEXT_206);
    } else {
    stringBuffer.append(TEXT_207);
    stringBuffer.append(theClass);
    stringBuffer.append(TEXT_208);
    }
    stringBuffer.append(TEXT_209);
    }}
    }
    }
    stringBuffer.append(TEXT_210);
    stringBuffer.append(genModel.getImportedName("com.google.gwt.user.client.rpc.IsSerializable"));
    stringBuffer.append(TEXT_211);
    for (GenClassifier genClassifier : genPackage.getGenClassifiers()) {
    if (genClassifier instanceof GenClass) { GenClass genClass = (GenClass)genClassifier;
    if (!genClass.isDynamic()) {
    stringBuffer.append(TEXT_212);
    stringBuffer.append(genClass.isMapEntry() ? genClass.getImportedClassName() : genClass.getImportedWildcardInstanceClassName());
    stringBuffer.append(TEXT_213);
    stringBuffer.append(genClass.getSafeUncapName());
    stringBuffer.append(TEXT_214);
    }
    } else if (genClassifier instanceof GenDataType) { GenDataType genDataType = (GenDataType)genClassifier;
    if (!genDataType.isObjectType() && genDataType.isSerializable()) {
    stringBuffer.append(TEXT_215);
    stringBuffer.append(genDataType.getImportedWildcardInstanceClassName());
    stringBuffer.append(TEXT_216);
    stringBuffer.append(genDataType.getSafeUncapName());
    stringBuffer.append(TEXT_217);
    }
    }
    }
    stringBuffer.append(TEXT_218);
    }
    stringBuffer.append(TEXT_219);
    }
    if (isInterface) { // TODO REMOVE THIS BOGUS EMPTY LINE
    stringBuffer.append(TEXT_220);
    }
    for (GenClassifier genClassifier : genPackage.getGenClassifiers()) {
    if (isInterface) {
    stringBuffer.append(TEXT_221);
    if (genClassifier instanceof GenClass) { GenClass genClass = (GenClass)genClassifier;
    stringBuffer.append(TEXT_222);
    stringBuffer.append(genClass.getQualifiedInterfaceName());
    stringBuffer.append(TEXT_223);
    stringBuffer.append(genClass.getFormattedName());
    stringBuffer.append(TEXT_224);
    stringBuffer.append(genClass.getFormattedName());
    stringBuffer.append(TEXT_225);
    stringBuffer.append(genClass.getQualifiedInterfaceName());
    if (!genModel.isSuppressEMFModelTags() && (genClass.isExternalInterface() || genClass.isDynamic())) { boolean first = true; for (StringTokenizer stringTokenizer = new StringTokenizer(genClass.getModelInfo(), "\n\r"); stringTokenizer.hasMoreTokens(); ) { String modelInfo = stringTokenizer.nextToken(); if (first) { first = false;
    stringBuffer.append(TEXT_226);
    stringBuffer.append(modelInfo);
    } else {
    stringBuffer.append(TEXT_227);
    stringBuffer.append(modelInfo);
    }} if (first) {
    stringBuffer.append(TEXT_228);
    }}
    } else if (genClassifier instanceof GenEnum) { GenEnum genEnum = (GenEnum)genClassifier;
    stringBuffer.append(TEXT_229);
    stringBuffer.append(genEnum.getQualifiedName());
    stringBuffer.append(TEXT_230);
    stringBuffer.append(genEnum.getFormattedName());
    stringBuffer.append(TEXT_231);
    stringBuffer.append(genEnum.getFormattedName());
    stringBuffer.append(TEXT_232);
    stringBuffer.append(genEnum.getQualifiedName());
    } else if (genClassifier instanceof GenDataType) { GenDataType genDataType = (GenDataType)genClassifier;
    if (genDataType.isPrimitiveType() || genDataType.isArrayType()) {
    stringBuffer.append(TEXT_233);
    stringBuffer.append(genDataType.getFormattedName());
    stringBuffer.append(TEXT_234);
    } else {
    stringBuffer.append(TEXT_235);
    stringBuffer.append(genDataType.getRawInstanceClassName());
    stringBuffer.append(TEXT_236);
    stringBuffer.append(genDataType.getFormattedName());
    stringBuffer.append(TEXT_237);
    }
    stringBuffer.append(TEXT_238);
    if (genDataType.hasDocumentation()) {
    stringBuffer.append(TEXT_239);
    stringBuffer.append(genDataType.getDocumentation(genModel.getIndentation(stringBuffer)));
    stringBuffer.append(TEXT_240);
    }
    stringBuffer.append(TEXT_241);
    stringBuffer.append(genDataType.getFormattedName());
    stringBuffer.append(TEXT_242);
    if (!genDataType.isPrimitiveType() && !genDataType.isArrayType()) {
    stringBuffer.append(TEXT_243);
    stringBuffer.append(genDataType.getRawInstanceClassName());
    }
    if (!genModel.isSuppressEMFModelTags()) {boolean first = true; for (StringTokenizer stringTokenizer = new StringTokenizer(genDataType.getModelInfo(), "\n\r"); stringTokenizer.hasMoreTokens(); ) { String modelInfo = stringTokenizer.nextToken(); if (first) { first = false;
    stringBuffer.append(TEXT_244);
    stringBuffer.append(modelInfo);
    } else {
    stringBuffer.append(TEXT_245);
    stringBuffer.append(modelInfo);
    }} if (first) {
    stringBuffer.append(TEXT_246);
    }}
    }
    if ((genClassifier instanceof GenClass || genClassifier instanceof GenEnum) && genClassifier.hasAPITags()) {
    stringBuffer.append(TEXT_247);
    stringBuffer.append(genClassifier.getAPITags(genModel.getIndentation(stringBuffer)));
    }
    stringBuffer.append(TEXT_248);
    } else {
    stringBuffer.append(TEXT_249);
    if (genClassifier.hasAPITags()) {
    stringBuffer.append(TEXT_250);
    stringBuffer.append(genClassifier.getAPITags(genModel.getIndentation(stringBuffer)));
    }
    stringBuffer.append(TEXT_251);
    }
    if (isJDK50 && genClassifier.hasAPIDeprecatedTag()) {
    stringBuffer.append(TEXT_252);
    }
    if (isImplementation) {
    stringBuffer.append(TEXT_253);
    stringBuffer.append(genClassifier.getImportedMetaType());
    stringBuffer.append(TEXT_254);
    stringBuffer.append(genClassifier.getClassifierAccessorName());
    stringBuffer.append(TEXT_255);
    if (genPackage.isLoadedInitialization()) {
    stringBuffer.append(TEXT_256);
    stringBuffer.append(genClassifier.getClassifierInstanceName());
    stringBuffer.append(TEXT_257);
    stringBuffer.append(genClassifier.getClassifierInstanceName());
    stringBuffer.append(TEXT_258);
    stringBuffer.append(genClassifier.getImportedMetaType());
    stringBuffer.append(TEXT_259);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.EPackage"));
    stringBuffer.append(TEXT_260);
    stringBuffer.append(genPackage.getImportedPackageInterfaceName());
    stringBuffer.append(TEXT_261);
    stringBuffer.append(genPackage.getLocalClassifierIndex(genClassifier));
    stringBuffer.append(TEXT_262);
    }
    stringBuffer.append(TEXT_263);
    stringBuffer.append(genClassifier.getClassifierInstanceName());
    stringBuffer.append(TEXT_264);
    } else {
    stringBuffer.append(TEXT_265);
    stringBuffer.append(genClassifier.getImportedMetaType());
    stringBuffer.append(TEXT_266);
    stringBuffer.append(genClassifier.getClassifierAccessorName());
    stringBuffer.append(TEXT_267);
    }
    if (genClassifier instanceof GenClass) { GenClass genClass = (GenClass)genClassifier;
    for (GenFeature genFeature : genClass.getGenFeatures()) {
    if (isInterface) {
    stringBuffer.append(TEXT_268);
    stringBuffer.append(genFeature.getFeatureKind());
    stringBuffer.append(TEXT_269);
    stringBuffer.append(genClass.getQualifiedInterfaceName());
    if (!genClass.isMapEntry() && !genFeature.isSuppressedGetVisibility()) {
    stringBuffer.append(TEXT_270);
    stringBuffer.append(genFeature.getGetAccessor());
    }
    stringBuffer.append(TEXT_271);
    stringBuffer.append(genFeature.getFormattedName());
    stringBuffer.append(TEXT_272);
    stringBuffer.append(genFeature.getFeatureKind());
    stringBuffer.append(TEXT_273);
    stringBuffer.append(genFeature.getFormattedName());
    stringBuffer.append(TEXT_274);
    stringBuffer.append(genClass.getQualifiedInterfaceName());
    if (!genClass.isMapEntry() && !genFeature.isSuppressedGetVisibility()) {
    stringBuffer.append(TEXT_275);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_276);
    }
    stringBuffer.append(TEXT_277);
    stringBuffer.append(genClass.getClassifierAccessorName());
    stringBuffer.append(TEXT_278);
    if (genFeature.hasImplicitAPITags()) {
    stringBuffer.append(TEXT_279);
    stringBuffer.append(genFeature.getImplicitAPITags(genModel.getIndentation(stringBuffer)));
    }
    stringBuffer.append(TEXT_280);
    } else {
    stringBuffer.append(TEXT_281);
    if (genFeature.hasImplicitAPITags()) {
    stringBuffer.append(TEXT_282);
    stringBuffer.append(genFeature.getImplicitAPITags(genModel.getIndentation(stringBuffer)));
    }
    stringBuffer.append(TEXT_283);
    }
    if (isJDK50 && genFeature.hasImplicitAPIDeprecatedTag()) {
    stringBuffer.append(TEXT_284);
    }
    if (isImplementation) {
    stringBuffer.append(TEXT_285);
    stringBuffer.append(genFeature.getImportedMetaType());
    stringBuffer.append(TEXT_286);
    stringBuffer.append(genFeature.getFeatureAccessorName());
    stringBuffer.append(TEXT_287);
    if (!genPackage.isLoadedInitialization()) {
    stringBuffer.append(TEXT_288);
    stringBuffer.append(genFeature.getImportedMetaType());
    stringBuffer.append(TEXT_289);
    stringBuffer.append(genClass.getClassifierInstanceName());
    stringBuffer.append(TEXT_290);
    stringBuffer.append(genClass.getLocalFeatureIndex(genFeature));
    stringBuffer.append(TEXT_291);
    } else {
    stringBuffer.append(TEXT_292);
    stringBuffer.append(genFeature.getImportedMetaType());
    stringBuffer.append(TEXT_293);
    stringBuffer.append(genClassifier.getClassifierAccessorName());
    stringBuffer.append(TEXT_294);
    stringBuffer.append(genClass.getLocalFeatureIndex(genFeature));
    stringBuffer.append(TEXT_295);
    }
    stringBuffer.append(TEXT_296);
    } else {
    stringBuffer.append(TEXT_297);
    stringBuffer.append(genFeature.getImportedMetaType());
    stringBuffer.append(TEXT_298);
    stringBuffer.append(genFeature.getFeatureAccessorName());
    stringBuffer.append(TEXT_299);
    }
    stringBuffer.append(TEXT_300);
    }
    if (genModel.isOperationReflection()) {
    for (GenOperation genOperation : genClass.getGenOperations()) {
    if (isInterface) {
    stringBuffer.append(TEXT_301);
    stringBuffer.append(genClass.getQualifiedInterfaceName());
    stringBuffer.append(TEXT_302);
    stringBuffer.append(genOperation.getName());
    stringBuffer.append(TEXT_303);
    stringBuffer.append(genOperation.getParameterTypes(", "));
    stringBuffer.append(TEXT_304);
    stringBuffer.append(genOperation.getFormattedName());
    stringBuffer.append(TEXT_305);
    stringBuffer.append(genOperation.getFormattedName());
    stringBuffer.append(TEXT_306);
    stringBuffer.append(genClass.getQualifiedInterfaceName());
    stringBuffer.append(TEXT_307);
    stringBuffer.append(genOperation.getName());
    stringBuffer.append(TEXT_308);
    stringBuffer.append(genOperation.getParameterTypes(", "));
    stringBuffer.append(TEXT_309);
    if (genOperation.hasImplicitAPITags()) {
    stringBuffer.append(TEXT_310);
    stringBuffer.append(genOperation.getImplicitAPITags(genModel.getIndentation(stringBuffer)));
    }
    stringBuffer.append(TEXT_311);
    } else {
    stringBuffer.append(TEXT_312);
    if (genOperation.hasImplicitAPITags()) {
    stringBuffer.append(TEXT_313);
    stringBuffer.append(genOperation.getImplicitAPITags(genModel.getIndentation(stringBuffer)));
    }
    stringBuffer.append(TEXT_314);
    }
    if (isJDK50 && genOperation.hasImplicitAPIDeprecatedTag()) {
    stringBuffer.append(TEXT_315);
    }
    if (isImplementation) {
    stringBuffer.append(TEXT_316);
    stringBuffer.append(genOperation.getImportedMetaType());
    stringBuffer.append(TEXT_317);
    stringBuffer.append(genOperation.getOperationAccessorName());
    stringBuffer.append(TEXT_318);
    if (!genPackage.isLoadedInitialization()) {
    stringBuffer.append(TEXT_319);
    stringBuffer.append(genClass.getClassifierInstanceName());
    stringBuffer.append(TEXT_320);
    stringBuffer.append(genClass.getLocalOperationIndex(genOperation));
    stringBuffer.append(TEXT_321);
    } else {
    stringBuffer.append(TEXT_322);
    stringBuffer.append(genClassifier.getClassifierAccessorName());
    stringBuffer.append(TEXT_323);
    stringBuffer.append(genClass.getLocalOperationIndex(genOperation));
    stringBuffer.append(TEXT_324);
    }
    stringBuffer.append(TEXT_325);
    } else {
    stringBuffer.append(TEXT_326);
    stringBuffer.append(genOperation.getImportedMetaType());
    stringBuffer.append(TEXT_327);
    stringBuffer.append(genOperation.getOperationAccessorName());
    stringBuffer.append(TEXT_328);
    }
    stringBuffer.append(TEXT_329);
    }
    }
    }
    }
    if (isInterface) {
    stringBuffer.append(TEXT_330);
    } else {
    stringBuffer.append(TEXT_331);
    }
    if (isImplementation) {
    stringBuffer.append(TEXT_332);
    stringBuffer.append(genPackage.getImportedFactoryInterfaceName());
    stringBuffer.append(TEXT_333);
    stringBuffer.append(genPackage.getFactoryName());
    stringBuffer.append(TEXT_334);
    stringBuffer.append(genPackage.getImportedFactoryInterfaceName());
    stringBuffer.append(TEXT_335);
    } else {
    stringBuffer.append(TEXT_336);
    stringBuffer.append(genPackage.getFactoryInterfaceName());
    stringBuffer.append(TEXT_337);
    stringBuffer.append(genPackage.getFactoryName());
    stringBuffer.append(TEXT_338);
    }
    stringBuffer.append(TEXT_339);
    if (isImplementation) {
    if (!genPackage.isLoadedInitialization()) {
    stringBuffer.append(TEXT_340);
    {boolean needsSuppressDeprecation = false; if (!packageNeedsSuppressDeprecation && isJDK50) { LOOP: for (GenClass genClass : genPackage.getGenClasses()) { for (GenFeature genFeature : genClass.getGenFeatures()) { if (genFeature.hasAPIDeprecatedTag()) { needsSuppressDeprecation = true; break LOOP; }}
      for (GenOperation genOperation : genClass.getGenOperations()) { if (genOperation.hasAPIDeprecatedTag()) { needsSuppressDeprecation = true; break LOOP; }}} if (needsSuppressDeprecation) {
    stringBuffer.append(TEXT_341);
    }}}
    stringBuffer.append(TEXT_342);
    if (!genPackage.getGenClasses().isEmpty()) {
    stringBuffer.append(TEXT_343);
    for (Iterator<GenClass> c=genPackage.getGenClasses().iterator(); c.hasNext();) { GenClass genClass = c.next();
    stringBuffer.append(TEXT_344);
    stringBuffer.append(genClass.getClassifierInstanceName());
    stringBuffer.append(TEXT_345);
    stringBuffer.append(genClass.getMetaType());
    stringBuffer.append(TEXT_346);
    stringBuffer.append(genClass.getClassifierID());
    stringBuffer.append(TEXT_347);
    for (GenFeature genFeature : genClass.getGenFeatures()) {
    stringBuffer.append(TEXT_348);
    stringBuffer.append(genFeature.getMetaType());
    stringBuffer.append(TEXT_349);
    stringBuffer.append(genClass.getClassifierInstanceName());
    stringBuffer.append(TEXT_350);
    stringBuffer.append(genClass.getFeatureID(genFeature));
    stringBuffer.append(TEXT_351);
    }
    if (genModel.isOperationReflection()) {
    for (GenOperation genOperation : genClass.getGenOperations()) {
    stringBuffer.append(TEXT_352);
    stringBuffer.append(genClass.getClassifierInstanceName());
    stringBuffer.append(TEXT_353);
    stringBuffer.append(genClass.getOperationID(genOperation, false));
    stringBuffer.append(TEXT_354);
    }
    }
    if (c.hasNext()) {
    stringBuffer.append(TEXT_355);
    }
    }
    }
    if (!genPackage.getGenEnums().isEmpty()) {
    stringBuffer.append(TEXT_356);
    for (GenEnum genEnum : genPackage.getGenEnums()) {
    stringBuffer.append(TEXT_357);
    stringBuffer.append(genEnum.getClassifierInstanceName());
    stringBuffer.append(TEXT_358);
    stringBuffer.append(genEnum.getClassifierID());
    stringBuffer.append(TEXT_359);
    }
    }
    if (!genPackage.getGenDataTypes().isEmpty()) {
    stringBuffer.append(TEXT_360);
    for (GenDataType genDataType : genPackage.getGenDataTypes()) {
    stringBuffer.append(TEXT_361);
    stringBuffer.append(genDataType.getClassifierInstanceName());
    stringBuffer.append(TEXT_362);
    stringBuffer.append(genDataType.getClassifierID());
    stringBuffer.append(TEXT_363);
    }
    }
    stringBuffer.append(TEXT_364);
    
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
  Iterator<Object> iterator;
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

    stringBuffer.append(TEXT_365);
    {boolean needsSuppressDeprecation = false; if (!packageNeedsSuppressDeprecation && isJDK50) { LOOP: for (GenEnum genEnum : genPackage.getGenEnums()) { for (GenEnumLiteral genEnumLiteral : genEnum.getGenEnumLiterals()) { if (genEnumLiteral.hasAPIDeprecatedTag()) { needsSuppressDeprecation = true; break LOOP; }}} if (needsSuppressDeprecation) {
    stringBuffer.append(TEXT_366);
    }}}
    stringBuffer.append(TEXT_367);
    if (!genPackage.getPackageInitializationDependencies().isEmpty()) {
    stringBuffer.append(TEXT_368);
    for (GenPackage dep : genPackage.getPackageInitializationDependencies()) {
    stringBuffer.append(TEXT_369);
    stringBuffer.append(dep.getImportedPackageInterfaceName());
    stringBuffer.append(TEXT_370);
    stringBuffer.append(genPackage.getPackageInstanceVariable(dep));
    stringBuffer.append(TEXT_371);
    stringBuffer.append(dep.getImportedPackageInterfaceName());
    stringBuffer.append(TEXT_372);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.EPackage"));
    stringBuffer.append(TEXT_373);
    stringBuffer.append(dep.getImportedPackageInterfaceName());
    stringBuffer.append(TEXT_374);
    }
    }
    if (!genPackage.getSubGenPackages().isEmpty()) {
    stringBuffer.append(TEXT_375);
    for (GenPackage sub : genPackage.getSubGenPackages()) {
    stringBuffer.append(TEXT_376);
    stringBuffer.append(genPackage.getPackageInstanceVariable(sub));
    stringBuffer.append(TEXT_377);
    }
    }
    if (!genPackage.getGenClasses().isEmpty()) { boolean firstOperationAssignment = true; int maxTypeParameterAssignment = 0;
    if (genModel.useGenerics()) {
    stringBuffer.append(TEXT_378);
    for (GenClassifier genClassifier : genPackage.getGenClassifiers()) {
    for (GenTypeParameter genTypeParameter : genClassifier.getGenTypeParameters()) {
    if (!genTypeParameter.getEcoreTypeParameter().getEBounds().isEmpty() || genTypeParameter.isUsed()) {
    stringBuffer.append(TEXT_379);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.ETypeParameter"));
    stringBuffer.append(TEXT_380);
    stringBuffer.append(genClassifier.getClassifierInstanceName());
    stringBuffer.append(TEXT_381);
    stringBuffer.append(genTypeParameter.getName());
    stringBuffer.append(TEXT_382);
    stringBuffer.append(genClassifier.getClassifierInstanceName());
    stringBuffer.append(TEXT_383);
    stringBuffer.append(genTypeParameter.getName());
    stringBuffer.append(TEXT_384);
    stringBuffer.append(genModel.getNonNLS());
    } else {
    stringBuffer.append(TEXT_385);
    stringBuffer.append(genClassifier.getClassifierInstanceName());
    stringBuffer.append(TEXT_386);
    stringBuffer.append(genTypeParameter.getName());
    stringBuffer.append(TEXT_387);
    stringBuffer.append(genModel.getNonNLS());
    }
    }
    }
    }
    if (genModel.useGenerics()) {
    stringBuffer.append(TEXT_388);
    for (GenClassifier genClassifier : genPackage.getGenClassifiers()) {
    for (GenTypeParameter genTypeParameter : genClassifier.getGenTypeParameters()) {
    for (EGenericType bound : genTypeParameter.getEcoreTypeParameter().getEBounds()) {
    for (InformationIterator i=new InformationIterator(bound); i.hasNext(); ) { Information info = i.next(); String prefix = ""; if (maxGenericTypeAssignment <= info.depth) { ++maxGenericTypeAssignment; prefix = genModel.getImportedName("org.eclipse.emf.ecore.EGenericType") + " "; }
    stringBuffer.append(TEXT_389);
    stringBuffer.append(prefix);
    stringBuffer.append(TEXT_390);
    stringBuffer.append(info.depth + 1);
    stringBuffer.append(TEXT_391);
    stringBuffer.append(info.type);
    stringBuffer.append(TEXT_392);
    if (info.depth > 0) {
    stringBuffer.append(TEXT_393);
    stringBuffer.append(info.depth);
    stringBuffer.append(TEXT_394);
    stringBuffer.append(info.accessor);
    stringBuffer.append(TEXT_395);
    stringBuffer.append(info.depth + 1);
    stringBuffer.append(TEXT_396);
    }
    }
    stringBuffer.append(TEXT_397);
    stringBuffer.append(genClassifier.getClassifierInstanceName());
    stringBuffer.append(TEXT_398);
    stringBuffer.append(genTypeParameter.getName());
    stringBuffer.append(TEXT_399);
    }
    }
    }
    }
    stringBuffer.append(TEXT_400);
    for (GenClass genClass : genPackage.getGenClasses()) {
    if (!genClass.hasGenericSuperTypes()) {
    for (GenClass baseGenClass : genClass.getBaseGenClasses()) {
    stringBuffer.append(TEXT_401);
    stringBuffer.append(genClass.getClassifierInstanceName());
    stringBuffer.append(TEXT_402);
    stringBuffer.append(genPackage.getPackageInstanceVariable(baseGenClass.getGenPackage()));
    stringBuffer.append(TEXT_403);
    stringBuffer.append(baseGenClass.getClassifierAccessorName());
    stringBuffer.append(TEXT_404);
    }
    } else {
    for (EGenericType superType : genClass.getEcoreClass().getEGenericSuperTypes()) {
    for (InformationIterator i=new InformationIterator(superType); i.hasNext(); ) { Information info = i.next(); String prefix = ""; if (maxGenericTypeAssignment <= info.depth) { ++maxGenericTypeAssignment; prefix = genModel.getImportedName("org.eclipse.emf.ecore.EGenericType") + " "; }
    stringBuffer.append(TEXT_405);
    stringBuffer.append(prefix);
    stringBuffer.append(TEXT_406);
    stringBuffer.append(info.depth + 1);
    stringBuffer.append(TEXT_407);
    stringBuffer.append(info.type);
    stringBuffer.append(TEXT_408);
    if (info.depth > 0) {
    stringBuffer.append(TEXT_409);
    stringBuffer.append(info.depth);
    stringBuffer.append(TEXT_410);
    stringBuffer.append(info.accessor);
    stringBuffer.append(TEXT_411);
    stringBuffer.append(info.depth + 1);
    stringBuffer.append(TEXT_412);
    }
    }
    stringBuffer.append(TEXT_413);
    stringBuffer.append(genClass.getClassifierInstanceName());
    stringBuffer.append(TEXT_414);
    }
    }
    }
    stringBuffer.append(TEXT_415);
    if (genModel.isOperationReflection()) {
    stringBuffer.append(TEXT_416);
    } else {
    stringBuffer.append(TEXT_417);
    }
    for (Iterator<GenClass> c=genPackage.getGenClasses().iterator(); c.hasNext();) { GenClass genClass = c.next(); boolean hasInstanceTypeName = genModel.useGenerics() && genClass.getEcoreClass().getInstanceTypeName() != null && genClass.getEcoreClass().getInstanceTypeName().contains("<");
    stringBuffer.append(TEXT_418);
    stringBuffer.append(genClass.getClassifierInstanceName());
    stringBuffer.append(TEXT_419);
    if (genClass.isDynamic()) {
    stringBuffer.append(TEXT_420);
    } else {
    stringBuffer.append(genClass.getRawImportedInterfaceName());
    stringBuffer.append(TEXT_421);
    }
    stringBuffer.append(TEXT_422);
    stringBuffer.append(genClass.getName());
    stringBuffer.append(TEXT_423);
    stringBuffer.append(genClass.getAbstractFlag());
    stringBuffer.append(TEXT_424);
    stringBuffer.append(genClass.getInterfaceFlag());
    stringBuffer.append(TEXT_425);
    stringBuffer.append(genClass.getGeneratedInstanceClassFlag());
    if (hasInstanceTypeName) {
    stringBuffer.append(TEXT_426);
    stringBuffer.append(genClass.getEcoreClass().getInstanceTypeName());
    stringBuffer.append(TEXT_427);
    }
    stringBuffer.append(TEXT_428);
    stringBuffer.append(genModel.getNonNLS());
    if (hasInstanceTypeName) {
    stringBuffer.append(genModel.getNonNLS(2));
    }
    for (GenFeature genFeature : genClass.getGenFeatures()) {
    if (genFeature.hasGenericType()) {
    for (InformationIterator i=new InformationIterator(genFeature.getEcoreFeature().getEGenericType()); i.hasNext(); ) { Information info = i.next(); String prefix = ""; if (maxGenericTypeAssignment <= info.depth) { ++maxGenericTypeAssignment; prefix = genModel.getImportedName("org.eclipse.emf.ecore.EGenericType") + " "; }
    stringBuffer.append(TEXT_429);
    stringBuffer.append(prefix);
    stringBuffer.append(TEXT_430);
    stringBuffer.append(info.depth + 1);
    stringBuffer.append(TEXT_431);
    stringBuffer.append(info.type);
    stringBuffer.append(TEXT_432);
    if (info.depth > 0) {
    stringBuffer.append(TEXT_433);
    stringBuffer.append(info.depth);
    stringBuffer.append(TEXT_434);
    stringBuffer.append(info.accessor);
    stringBuffer.append(TEXT_435);
    stringBuffer.append(info.depth + 1);
    stringBuffer.append(TEXT_436);
    }
    }
    }
    if (genFeature.isReferenceType()) { GenFeature reverseGenFeature = genFeature.getReverse();
    String reverse = reverseGenFeature == null ? "null" : genPackage.getPackageInstanceVariable(reverseGenFeature.getGenPackage()) + ".get" + reverseGenFeature.getFeatureAccessorName() + "()";
    stringBuffer.append(TEXT_437);
    stringBuffer.append(genFeature.getFeatureAccessorName());
    stringBuffer.append(TEXT_438);
    if (genFeature.hasGenericType()) {
    stringBuffer.append(TEXT_439);
    } else {
    stringBuffer.append(genPackage.getPackageInstanceVariable(genFeature.getTypeGenPackage()));
    stringBuffer.append(TEXT_440);
    stringBuffer.append(genFeature.getTypeClassifierAccessorName());
    stringBuffer.append(TEXT_441);
    }
    stringBuffer.append(TEXT_442);
    stringBuffer.append(reverse);
    stringBuffer.append(TEXT_443);
    stringBuffer.append(genFeature.getName());
    stringBuffer.append(TEXT_444);
    stringBuffer.append(genFeature.getDefaultValue());
    stringBuffer.append(TEXT_445);
    stringBuffer.append(genFeature.getLowerBound());
    stringBuffer.append(TEXT_446);
    stringBuffer.append(genFeature.getUpperBound());
    stringBuffer.append(TEXT_447);
    stringBuffer.append(genFeature.getContainerClass());
    stringBuffer.append(TEXT_448);
    stringBuffer.append(genFeature.getTransientFlag());
    stringBuffer.append(TEXT_449);
    stringBuffer.append(genFeature.getVolatileFlag());
    stringBuffer.append(TEXT_450);
    stringBuffer.append(genFeature.getChangeableFlag());
    stringBuffer.append(TEXT_451);
    stringBuffer.append(genFeature.getContainmentFlag());
    stringBuffer.append(TEXT_452);
    stringBuffer.append(genFeature.getResolveProxiesFlag());
    stringBuffer.append(TEXT_453);
    stringBuffer.append(genFeature.getUnsettableFlag());
    stringBuffer.append(TEXT_454);
    stringBuffer.append(genFeature.getUniqueFlag());
    stringBuffer.append(TEXT_455);
    stringBuffer.append(genFeature.getDerivedFlag());
    stringBuffer.append(TEXT_456);
    stringBuffer.append(genFeature.getOrderedFlag());
    stringBuffer.append(TEXT_457);
    stringBuffer.append(genModel.getNonNLS());
    stringBuffer.append(genModel.getNonNLS(genFeature.getDefaultValue(), 2));
    for (GenFeature keyFeature : genFeature.getKeys()) {
    stringBuffer.append(TEXT_458);
    stringBuffer.append(genFeature.getFeatureAccessorName());
    stringBuffer.append(TEXT_459);
    stringBuffer.append(genPackage.getPackageInstanceVariable(keyFeature.getGenPackage()));
    stringBuffer.append(TEXT_460);
    stringBuffer.append(keyFeature.getFeatureAccessorName());
    stringBuffer.append(TEXT_461);
    }
    } else {
    stringBuffer.append(TEXT_462);
    stringBuffer.append(genFeature.getFeatureAccessorName());
    stringBuffer.append(TEXT_463);
    if (genFeature.hasGenericType()) {
    stringBuffer.append(TEXT_464);
    } else {
    stringBuffer.append(genPackage.getPackageInstanceVariable(genFeature.getTypeGenPackage()));
    stringBuffer.append(TEXT_465);
    stringBuffer.append(genFeature.getTypeClassifierAccessorName());
    stringBuffer.append(TEXT_466);
    }
    stringBuffer.append(TEXT_467);
    stringBuffer.append(genFeature.getName());
    stringBuffer.append(TEXT_468);
    stringBuffer.append(genFeature.getDefaultValue());
    stringBuffer.append(TEXT_469);
    stringBuffer.append(genFeature.getLowerBound());
    stringBuffer.append(TEXT_470);
    stringBuffer.append(genFeature.getUpperBound());
    stringBuffer.append(TEXT_471);
    stringBuffer.append(genFeature.getContainerClass());
    stringBuffer.append(TEXT_472);
    stringBuffer.append(genFeature.getTransientFlag());
    stringBuffer.append(TEXT_473);
    stringBuffer.append(genFeature.getVolatileFlag());
    stringBuffer.append(TEXT_474);
    stringBuffer.append(genFeature.getChangeableFlag());
    stringBuffer.append(TEXT_475);
    stringBuffer.append(genFeature.getUnsettableFlag());
    stringBuffer.append(TEXT_476);
    stringBuffer.append(genFeature.getIDFlag());
    stringBuffer.append(TEXT_477);
    stringBuffer.append(genFeature.getUniqueFlag());
    stringBuffer.append(TEXT_478);
    stringBuffer.append(genFeature.getDerivedFlag());
    stringBuffer.append(TEXT_479);
    stringBuffer.append(genFeature.getOrderedFlag());
    stringBuffer.append(TEXT_480);
    stringBuffer.append(genModel.getNonNLS());
    stringBuffer.append(genModel.getNonNLS(genFeature.getDefaultValue(), 2));
    }
    }
    for (GenOperation genOperation : genClass.getGenOperations()) {String prefix = ""; if (genOperation.hasGenericType() || !genOperation.getGenParameters().isEmpty() || !genOperation.getGenExceptions().isEmpty() || !genOperation.getGenTypeParameters().isEmpty()) { if (firstOperationAssignment) { firstOperationAssignment = false; prefix = genModel.getImportedName("org.eclipse.emf.ecore.EOperation") + " op = "; } else { prefix = "op = "; }} 
    stringBuffer.append(TEXT_481);
    if (genModel.useGenerics()) {
    stringBuffer.append(TEXT_482);
    stringBuffer.append(prefix);
    if (genModel.isOperationReflection()) {
    stringBuffer.append(TEXT_483);
    stringBuffer.append(genOperation.getOperationAccessorName());
    stringBuffer.append(TEXT_484);
    } else {
    stringBuffer.append(TEXT_485);
    stringBuffer.append(genClass.getClassifierInstanceName());
    }
    stringBuffer.append(TEXT_486);
    if (genOperation.isVoid() || genOperation.hasGenericType()) {
    stringBuffer.append(TEXT_487);
    } else {
    stringBuffer.append(genPackage.getPackageInstanceVariable(genOperation.getTypeGenPackage()));
    stringBuffer.append(TEXT_488);
    stringBuffer.append(genOperation.getTypeClassifierAccessorName());
    stringBuffer.append(TEXT_489);
    }
    stringBuffer.append(TEXT_490);
    stringBuffer.append(genOperation.getName());
    stringBuffer.append(TEXT_491);
    stringBuffer.append(genOperation.getLowerBound());
    stringBuffer.append(TEXT_492);
    stringBuffer.append(genOperation.getUpperBound());
    stringBuffer.append(TEXT_493);
    stringBuffer.append(genOperation.getUniqueFlag());
    stringBuffer.append(TEXT_494);
    stringBuffer.append(genOperation.getOrderedFlag());
    stringBuffer.append(TEXT_495);
    stringBuffer.append(genModel.getNonNLS());
    } else if (!genOperation.isVoid()) {
    if (!genOperation.getEcoreOperation().isOrdered() || !genOperation.getEcoreOperation().isUnique()) { needsAddEOperation = true;
    stringBuffer.append(TEXT_496);
    stringBuffer.append(prefix);
    if (genModel.isOperationReflection()) {
    stringBuffer.append(TEXT_497);
    stringBuffer.append(genOperation.getOperationAccessorName());
    stringBuffer.append(TEXT_498);
    } else {
    stringBuffer.append(TEXT_499);
    stringBuffer.append(genClass.getClassifierInstanceName());
    }
    stringBuffer.append(TEXT_500);
    stringBuffer.append(genPackage.getPackageInstanceVariable(genOperation.getTypeGenPackage()));
    stringBuffer.append(TEXT_501);
    stringBuffer.append(genOperation.getTypeClassifierAccessorName());
    stringBuffer.append(TEXT_502);
    stringBuffer.append(genOperation.getName());
    stringBuffer.append(TEXT_503);
    stringBuffer.append(genOperation.getLowerBound());
    stringBuffer.append(TEXT_504);
    stringBuffer.append(genOperation.getUpperBound());
    stringBuffer.append(TEXT_505);
    stringBuffer.append(genOperation.getUniqueFlag());
    stringBuffer.append(TEXT_506);
    stringBuffer.append(genOperation.getOrderedFlag());
    stringBuffer.append(TEXT_507);
    stringBuffer.append(genModel.getNonNLS());
    } else {
    stringBuffer.append(TEXT_508);
    stringBuffer.append(prefix);
    if (genModel.isOperationReflection()) {
    stringBuffer.append(TEXT_509);
    stringBuffer.append(genOperation.getOperationAccessorName());
    stringBuffer.append(TEXT_510);
    } else {
    stringBuffer.append(TEXT_511);
    stringBuffer.append(genClass.getClassifierInstanceName());
    }
    stringBuffer.append(TEXT_512);
    stringBuffer.append(genPackage.getPackageInstanceVariable(genOperation.getTypeGenPackage()));
    stringBuffer.append(TEXT_513);
    stringBuffer.append(genOperation.getTypeClassifierAccessorName());
    stringBuffer.append(TEXT_514);
    stringBuffer.append(genOperation.getName());
    stringBuffer.append(TEXT_515);
    stringBuffer.append(genOperation.getLowerBound());
    stringBuffer.append(TEXT_516);
    stringBuffer.append(genOperation.getUpperBound());
    stringBuffer.append(TEXT_517);
    stringBuffer.append(genModel.getNonNLS());
    }
    } else {
    stringBuffer.append(TEXT_518);
    stringBuffer.append(prefix);
    if (genModel.isOperationReflection()) {
    stringBuffer.append(TEXT_519);
    stringBuffer.append(genOperation.getOperationAccessorName());
    stringBuffer.append(TEXT_520);
    } else {
    stringBuffer.append(TEXT_521);
    stringBuffer.append(genClass.getClassifierInstanceName());
    }
    stringBuffer.append(TEXT_522);
    stringBuffer.append(genOperation.getName());
    stringBuffer.append(TEXT_523);
    stringBuffer.append(genModel.getNonNLS());
    }
    if (genModel.useGenerics()) {
    for (ListIterator<GenTypeParameter> t=genOperation.getGenTypeParameters().listIterator(); t.hasNext(); ) { GenTypeParameter genTypeParameter = t.next(); String typeParameterVariable = ""; if (!genTypeParameter.getEcoreTypeParameter().getEBounds().isEmpty() || genTypeParameter.isUsed()) { if (maxTypeParameterAssignment <= t.previousIndex()) { ++maxTypeParameterAssignment; typeParameterVariable = genModel.getImportedName("org.eclipse.emf.ecore.ETypeParameter") + " t" + t.nextIndex() + " = "; } else { typeParameterVariable = "t" + t.nextIndex() + " = "; }} 
    stringBuffer.append(TEXT_524);
    stringBuffer.append(typeParameterVariable);
    stringBuffer.append(TEXT_525);
    stringBuffer.append(genTypeParameter.getName());
    stringBuffer.append(TEXT_526);
    stringBuffer.append(genModel.getNonNLS());
    for (EGenericType typeParameter : genTypeParameter.getEcoreTypeParameter().getEBounds()) {
    for (InformationIterator i=new InformationIterator(typeParameter); i.hasNext(); ) { Information info = i.next(); String typePrefix = ""; if (maxGenericTypeAssignment <= info.depth) { ++maxGenericTypeAssignment; typePrefix = genModel.getImportedName("org.eclipse.emf.ecore.EGenericType") + " "; }
    stringBuffer.append(TEXT_527);
    stringBuffer.append(typePrefix);
    stringBuffer.append(TEXT_528);
    stringBuffer.append(info.depth + 1);
    stringBuffer.append(TEXT_529);
    stringBuffer.append(info.type);
    stringBuffer.append(TEXT_530);
    if (info.depth > 0) {
    stringBuffer.append(TEXT_531);
    stringBuffer.append(info.depth);
    stringBuffer.append(TEXT_532);
    stringBuffer.append(info.accessor);
    stringBuffer.append(TEXT_533);
    stringBuffer.append(info.depth + 1);
    stringBuffer.append(TEXT_534);
    }
    }
    stringBuffer.append(TEXT_535);
    stringBuffer.append(t.nextIndex());
    stringBuffer.append(TEXT_536);
    }
    }
    }
    for (GenParameter genParameter : genOperation.getGenParameters()) {
    if (genParameter.hasGenericType()) {
    for (InformationIterator i=new InformationIterator(genParameter.getEcoreParameter().getEGenericType()); i.hasNext(); ) { Information info = i.next(); String typePrefix = ""; if (maxGenericTypeAssignment <= info.depth) { ++maxGenericTypeAssignment; typePrefix = genModel.getImportedName("org.eclipse.emf.ecore.EGenericType") + " "; }
    stringBuffer.append(TEXT_537);
    stringBuffer.append(typePrefix);
    stringBuffer.append(TEXT_538);
    stringBuffer.append(info.depth + 1);
    stringBuffer.append(TEXT_539);
    stringBuffer.append(info.type);
    stringBuffer.append(TEXT_540);
    if (info.depth > 0) {
    stringBuffer.append(TEXT_541);
    stringBuffer.append(info.depth);
    stringBuffer.append(TEXT_542);
    stringBuffer.append(info.accessor);
    stringBuffer.append(TEXT_543);
    stringBuffer.append(info.depth + 1);
    stringBuffer.append(TEXT_544);
    }
    }
    }
    if (genModel.useGenerics()) {
    stringBuffer.append(TEXT_545);
    if (genParameter.hasGenericType()){
    stringBuffer.append(TEXT_546);
    } else {
    stringBuffer.append(genPackage.getPackageInstanceVariable(genParameter.getTypeGenPackage()));
    stringBuffer.append(TEXT_547);
    stringBuffer.append(genParameter.getTypeClassifierAccessorName());
    stringBuffer.append(TEXT_548);
    }
    stringBuffer.append(TEXT_549);
    stringBuffer.append(genParameter.getName());
    stringBuffer.append(TEXT_550);
    stringBuffer.append(genParameter.getLowerBound());
    stringBuffer.append(TEXT_551);
    stringBuffer.append(genParameter.getUpperBound());
    stringBuffer.append(TEXT_552);
    stringBuffer.append(genParameter.getUniqueFlag());
    stringBuffer.append(TEXT_553);
    stringBuffer.append(genParameter.getOrderedFlag());
    stringBuffer.append(TEXT_554);
    stringBuffer.append(genModel.getNonNLS());
    } else if (!genParameter.getEcoreParameter().isOrdered() || !genParameter.getEcoreParameter().isUnique()) { needsAddEParameter = true;
    stringBuffer.append(TEXT_555);
    if (genParameter.hasGenericType()){
    stringBuffer.append(TEXT_556);
    } else {
    stringBuffer.append(genPackage.getPackageInstanceVariable(genParameter.getTypeGenPackage()));
    stringBuffer.append(TEXT_557);
    stringBuffer.append(genParameter.getTypeClassifierAccessorName());
    stringBuffer.append(TEXT_558);
    }
    stringBuffer.append(TEXT_559);
    stringBuffer.append(genParameter.getName());
    stringBuffer.append(TEXT_560);
    stringBuffer.append(genParameter.getLowerBound());
    stringBuffer.append(TEXT_561);
    stringBuffer.append(genParameter.getUpperBound());
    stringBuffer.append(TEXT_562);
    stringBuffer.append(genParameter.getUniqueFlag());
    stringBuffer.append(TEXT_563);
    stringBuffer.append(genParameter.getOrderedFlag());
    stringBuffer.append(TEXT_564);
    stringBuffer.append(genModel.getNonNLS());
    } else {
    stringBuffer.append(TEXT_565);
    if (genParameter.hasGenericType()){
    stringBuffer.append(TEXT_566);
    } else {
    stringBuffer.append(genPackage.getPackageInstanceVariable(genParameter.getTypeGenPackage()));
    stringBuffer.append(TEXT_567);
    stringBuffer.append(genParameter.getTypeClassifierAccessorName());
    stringBuffer.append(TEXT_568);
    }
    stringBuffer.append(TEXT_569);
    stringBuffer.append(genParameter.getName());
    stringBuffer.append(TEXT_570);
    stringBuffer.append(genParameter.getLowerBound());
    stringBuffer.append(TEXT_571);
    stringBuffer.append(genParameter.getUpperBound());
    stringBuffer.append(TEXT_572);
    stringBuffer.append(genModel.getNonNLS());
    }
    }
    if (genOperation.hasGenericExceptions()) {
    for (EGenericType genericExceptions : genOperation.getEcoreOperation().getEGenericExceptions()) {
    for (InformationIterator i=new InformationIterator(genericExceptions); i.hasNext(); ) { Information info = i.next(); String typePrefix = ""; if (maxGenericTypeAssignment <= info.depth) { ++maxGenericTypeAssignment; typePrefix = genModel.getImportedName("org.eclipse.emf.ecore.EGenericType") + " "; }
    stringBuffer.append(TEXT_573);
    stringBuffer.append(typePrefix);
    stringBuffer.append(TEXT_574);
    stringBuffer.append(info.depth + 1);
    stringBuffer.append(TEXT_575);
    stringBuffer.append(info.type);
    stringBuffer.append(TEXT_576);
    if (info.depth > 0) {
    stringBuffer.append(TEXT_577);
    stringBuffer.append(info.depth);
    stringBuffer.append(TEXT_578);
    stringBuffer.append(info.accessor);
    stringBuffer.append(TEXT_579);
    stringBuffer.append(info.depth + 1);
    stringBuffer.append(TEXT_580);
    }
    stringBuffer.append(TEXT_581);
    stringBuffer.append(info.depth + 1);
    stringBuffer.append(TEXT_582);
    }
    }
    } else {
    for (GenClassifier genException : genOperation.getGenExceptions()) {
    stringBuffer.append(TEXT_583);
    stringBuffer.append(genPackage.getPackageInstanceVariable(genException.getGenPackage()));
    stringBuffer.append(TEXT_584);
    stringBuffer.append(genException.getClassifierAccessorName());
    stringBuffer.append(TEXT_585);
    }
    }
    if (!genOperation.isVoid() && genOperation.hasGenericType()) {
    for (InformationIterator i=new InformationIterator(genOperation.getEcoreOperation().getEGenericType()); i.hasNext(); ) { Information info = i.next(); String typePrefix = ""; if (maxGenericTypeAssignment <= info.depth) { ++maxGenericTypeAssignment; typePrefix = genModel.getImportedName("org.eclipse.emf.ecore.EGenericType") + " "; }
    stringBuffer.append(TEXT_586);
    stringBuffer.append(typePrefix);
    stringBuffer.append(TEXT_587);
    stringBuffer.append(info.depth + 1);
    stringBuffer.append(TEXT_588);
    stringBuffer.append(info.type);
    stringBuffer.append(TEXT_589);
    if (info.depth > 0) {
    stringBuffer.append(TEXT_590);
    stringBuffer.append(info.depth);
    stringBuffer.append(TEXT_591);
    stringBuffer.append(info.accessor);
    stringBuffer.append(TEXT_592);
    stringBuffer.append(info.depth + 1);
    stringBuffer.append(TEXT_593);
    }
    }
    stringBuffer.append(TEXT_594);
    }
    }
    if (c.hasNext()) {
    stringBuffer.append(TEXT_595);
    }
    }
    }
    if (!genPackage.getGenEnums().isEmpty()) {
    stringBuffer.append(TEXT_596);
    for (Iterator<GenEnum> e=genPackage.getGenEnums().iterator(); e.hasNext();) { GenEnum genEnum = e.next();
    stringBuffer.append(TEXT_597);
    stringBuffer.append(genEnum.getClassifierInstanceName());
    stringBuffer.append(TEXT_598);
    stringBuffer.append(genEnum.getImportedName());
    stringBuffer.append(TEXT_599);
    stringBuffer.append(genEnum.getName());
    stringBuffer.append(TEXT_600);
    stringBuffer.append(genModel.getNonNLS());
    for (GenEnumLiteral genEnumLiteral : genEnum.getGenEnumLiterals()) {
    stringBuffer.append(TEXT_601);
    stringBuffer.append(genEnum.getClassifierInstanceName());
    stringBuffer.append(TEXT_602);
    stringBuffer.append(genEnum.getImportedName().equals(genEnum.getClassifierID()) ? genEnum.getQualifiedName() : genEnum.getImportedName());
    stringBuffer.append(TEXT_603);
    stringBuffer.append(genEnumLiteral.getEnumLiteralInstanceConstantName());
    stringBuffer.append(TEXT_604);
    }
    if (e.hasNext()) {
    stringBuffer.append(TEXT_605);
    }
    }
    }
    if (!genPackage.getGenDataTypes().isEmpty()) {
    stringBuffer.append(TEXT_606);
    for (GenDataType genDataType : genPackage.getGenDataTypes()) {boolean hasInstanceTypeName = genModel.useGenerics() && genDataType.getEcoreDataType().getInstanceTypeName() != null && genDataType.getEcoreDataType().getInstanceTypeName().contains("<");
    stringBuffer.append(TEXT_607);
    stringBuffer.append(genDataType.getClassifierInstanceName());
    stringBuffer.append(TEXT_608);
    stringBuffer.append(genDataType.getRawImportedInstanceClassName());
    stringBuffer.append(TEXT_609);
    stringBuffer.append(genDataType.getName());
    stringBuffer.append(TEXT_610);
    stringBuffer.append(genDataType.getSerializableFlag());
    stringBuffer.append(TEXT_611);
    stringBuffer.append(genDataType.getGeneratedInstanceClassFlag());
    if (hasInstanceTypeName) {
    stringBuffer.append(TEXT_612);
    stringBuffer.append(genDataType.getEcoreDataType().getInstanceTypeName());
    stringBuffer.append(TEXT_613);
    }
    stringBuffer.append(TEXT_614);
    stringBuffer.append(genModel.getNonNLS());
    if (hasInstanceTypeName) {
    stringBuffer.append(genModel.getNonNLS(2));
    }
    }
    }
    if (genPackage.getSuperGenPackage() == null) {
    stringBuffer.append(TEXT_615);
    stringBuffer.append(genPackage.getSchemaLocation());
    stringBuffer.append(TEXT_616);
    }
    if (!genPackage.isEcorePackage() && !genPackage.getAnnotationSources().isEmpty()) {
    stringBuffer.append(TEXT_617);
    for (String annotationSource : genPackage.getAnnotationSources()) {
    stringBuffer.append(TEXT_618);
    stringBuffer.append(annotationSource);
    stringBuffer.append(TEXT_619);
    stringBuffer.append(genPackage.getAnnotationSourceIdentifier(annotationSource));
    stringBuffer.append(TEXT_620);
    }
    }
    stringBuffer.append(TEXT_621);
    for (String annotationSource : genPackage.getAnnotationSources()) {
    stringBuffer.append(TEXT_622);
    stringBuffer.append(annotationSource);
    stringBuffer.append(TEXT_623);
    stringBuffer.append(genPackage.getAnnotationSourceIdentifier(annotationSource));
    stringBuffer.append(TEXT_624);
    if (annotationSource == null) {
    stringBuffer.append(TEXT_625);
    } else {
    stringBuffer.append(TEXT_626);
    stringBuffer.append(annotationSource);
    stringBuffer.append(TEXT_627);
    stringBuffer.append(genModel.getNonNLS());
    }
    for (EAnnotation eAnnotation : genPackage.getAllAnnotations()) { List<GenPackage.AnnotationReferenceData> annotationReferenceDataList = genPackage.getReferenceData(eAnnotation);
    if (annotationSource == null ? eAnnotation.getSource() == null : annotationSource.equals(eAnnotation.getSource())) {
    stringBuffer.append(TEXT_628);
    stringBuffer.append(genPackage.getAnnotatedModelElementAccessor(eAnnotation));
    stringBuffer.append(TEXT_629);
    for (Iterator<Map.Entry<String, String>> k = eAnnotation.getDetails().iterator(); k.hasNext();) { Map.Entry<String, String> detail = k.next(); String key = Literals.toStringLiteral(detail.getKey(), genModel); String value = Literals.toStringLiteral(detail.getValue(), genModel);
    stringBuffer.append(TEXT_630);
    stringBuffer.append(key);
    stringBuffer.append(TEXT_631);
    stringBuffer.append(value);
    stringBuffer.append(k.hasNext() ? "," : "");
    stringBuffer.append(genModel.getNonNLS(key + value));
    }
    stringBuffer.append(TEXT_632);
    if (annotationReferenceDataList.isEmpty()) {
    stringBuffer.append(TEXT_633);
    } else {
    stringBuffer.append(TEXT_634);
    }
    if (!annotationReferenceDataList.isEmpty()) {
    stringBuffer.append(TEXT_635);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.util.URI"));
    stringBuffer.append(TEXT_636);
    for (Iterator<GenPackage.AnnotationReferenceData> k = annotationReferenceDataList.iterator(); k.hasNext();) { GenPackage.AnnotationReferenceData annotationReferenceData = k.next();
    stringBuffer.append(TEXT_637);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.util.URI"));
    stringBuffer.append(TEXT_638);
    if (annotationReferenceData.containingGenPackage != genPackage) {
    stringBuffer.append(annotationReferenceData.containingGenPackage.getImportedPackageInterfaceName());
    stringBuffer.append(TEXT_639);
    }
    stringBuffer.append(TEXT_640);
    stringBuffer.append(annotationReferenceData.uriFragment);
    stringBuffer.append(TEXT_641);
    if (k.hasNext()) {
    stringBuffer.append(TEXT_642);
    }
    stringBuffer.append(genModel.getNonNLS());
    }
    stringBuffer.append(TEXT_643);
    }
    for (EAnnotation nestedEAnnotation : genPackage.getAllNestedAnnotations(eAnnotation)) {String nestedAnnotationSource = nestedEAnnotation.getSource();  int depth = 0; boolean nonContentAnnotation = false; StringBuilder path = new StringBuilder();  for (EObject eContainer = nestedEAnnotation.eContainer(), child = nestedEAnnotation; child != eAnnotation; child = eContainer, eContainer = eContainer.eContainer())  {  boolean nonContentChild = child.eContainmentFeature() != EcorePackage.Literals.EANNOTATION__CONTENTS; if (path.length() != 0) { path.insert(0, ", ");  } path.insert(0, nonContentChild); if (nonContentChild) { nonContentAnnotation = true; } ++depth;  } List<GenPackage.AnnotationReferenceData> nestedAnnotationReferenceDataList = genPackage.getReferenceData(nestedEAnnotation);
    stringBuffer.append(TEXT_644);
    stringBuffer.append(genPackage.getAnnotatedModelElementAccessor(eAnnotation));
    stringBuffer.append(TEXT_645);
    if (nonContentAnnotation && genModel.getRuntimeVersion().getValue() >= GenRuntimeVersion.EMF210_VALUE) {
    stringBuffer.append(TEXT_646);
    stringBuffer.append(path.toString());
    stringBuffer.append(TEXT_647);
    } else {
    stringBuffer.append(depth);
    }
    stringBuffer.append(TEXT_648);
    if (nestedAnnotationSource == null) {
    stringBuffer.append(TEXT_649);
    } else {
    stringBuffer.append(TEXT_650);
    stringBuffer.append(nestedAnnotationSource);
    stringBuffer.append(TEXT_651);
    stringBuffer.append(genModel.getNonNLS());
    }
    stringBuffer.append(TEXT_652);
    for (Iterator<Map.Entry<String, String>> l = nestedEAnnotation.getDetails().iterator(); l.hasNext();) { Map.Entry<String, String> detail = l.next(); String key = Literals.toStringLiteral(detail.getKey(), genModel); String value = Literals.toStringLiteral(detail.getValue(), genModel);
    stringBuffer.append(TEXT_653);
    stringBuffer.append(key);
    stringBuffer.append(TEXT_654);
    stringBuffer.append(value);
    stringBuffer.append(l.hasNext() ? "," : "");
    stringBuffer.append(genModel.getNonNLS(key + value));
    }
    stringBuffer.append(TEXT_655);
    if (nestedAnnotationReferenceDataList.isEmpty()) {
    stringBuffer.append(TEXT_656);
    } else {
    stringBuffer.append(TEXT_657);
    }
    if (!nestedAnnotationReferenceDataList.isEmpty()) {
    stringBuffer.append(TEXT_658);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.util.URI"));
    stringBuffer.append(TEXT_659);
    for (Iterator<GenPackage.AnnotationReferenceData> l = nestedAnnotationReferenceDataList.iterator(); l.hasNext();) { GenPackage.AnnotationReferenceData annotationReferenceData = l.next();
    stringBuffer.append(TEXT_660);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.util.URI"));
    stringBuffer.append(TEXT_661);
    if (annotationReferenceData.containingGenPackage != genPackage) {
    stringBuffer.append(annotationReferenceData.containingGenPackage.getImportedPackageInterfaceName());
    stringBuffer.append(TEXT_662);
    }
    stringBuffer.append(TEXT_663);
    stringBuffer.append(annotationReferenceData.uriFragment);
    stringBuffer.append(TEXT_664);
    if (l.hasNext()) {
    stringBuffer.append(TEXT_665);
    }
    stringBuffer.append(genModel.getNonNLS());
    }
    stringBuffer.append(TEXT_666);
    }
    }
    }
    }
    stringBuffer.append(TEXT_667);
    }
    } else {
    if (genPackage.isLoadingInitialization()) {
    stringBuffer.append(TEXT_668);
    stringBuffer.append(genModel.getImportedName("java.net.URL"));
    stringBuffer.append(TEXT_669);
    stringBuffer.append(genModel.getNonNLS());
    stringBuffer.append(TEXT_670);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.util.URI"));
    stringBuffer.append(TEXT_671);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.util.URI"));
    stringBuffer.append(TEXT_672);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.resource.Resource"));
    stringBuffer.append(TEXT_673);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.xmi.impl.EcoreResourceFactoryImpl"));
    stringBuffer.append(TEXT_674);
    stringBuffer.append(genModel.getImportedName("java.io.IOException"));
    stringBuffer.append(TEXT_675);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.util.WrappedException"));
    stringBuffer.append(TEXT_676);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.EPackage"));
    stringBuffer.append(TEXT_677);
    }
    stringBuffer.append(TEXT_678);
    if (genModel.useClassOverrideAnnotation()) {
    stringBuffer.append(TEXT_679);
    }
    stringBuffer.append(TEXT_680);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.EClassifier"));
    stringBuffer.append(TEXT_681);
    ArrayList<GenClass> dynamicGenClasses = new ArrayList<GenClass>(); for (GenClass genClass : genPackage.getGenClasses()) { if (genClass.isDynamic()) { dynamicGenClasses.add(genClass); } }
    if (dynamicGenClasses.isEmpty()) {
    stringBuffer.append(TEXT_682);
    stringBuffer.append(genPackage.getInterfacePackageName());
    stringBuffer.append(TEXT_683);
    stringBuffer.append(genModel.getNonNLS());
    stringBuffer.append(TEXT_684);
    } else {
    stringBuffer.append(TEXT_685);
    for (GenClass genClass : dynamicGenClasses) {
    if (genClass.isDynamic()) {
    stringBuffer.append(TEXT_686);
    stringBuffer.append(genPackage.getClassifierID(genClass));
    stringBuffer.append(TEXT_687);
    }
    }
    stringBuffer.append(TEXT_688);
    stringBuffer.append(genPackage.getInterfacePackageName());
    stringBuffer.append(TEXT_689);
    stringBuffer.append(genModel.getNonNLS());
    stringBuffer.append(TEXT_690);
    }
    stringBuffer.append(TEXT_691);
    }
    if (needsAddEOperation) {
    stringBuffer.append(TEXT_692);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.EOperation"));
    stringBuffer.append(TEXT_693);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.EClass"));
    stringBuffer.append(TEXT_694);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.EClassifier"));
    stringBuffer.append(TEXT_695);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.EOperation"));
    stringBuffer.append(TEXT_696);
    }
    if (needsAddEParameter) {
    stringBuffer.append(TEXT_697);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.EParameter"));
    stringBuffer.append(TEXT_698);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.EOperation"));
    stringBuffer.append(TEXT_699);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.EClassifier"));
    stringBuffer.append(TEXT_700);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.EParameter"));
    stringBuffer.append(TEXT_701);
    }
    }
    if (isInterface && genPackage.isLiteralsInterface()) {
    stringBuffer.append(TEXT_702);
    if (genModel.isOperationReflection()) {
    stringBuffer.append(TEXT_703);
    }
    stringBuffer.append(TEXT_704);
    if (isImplementation) {
    stringBuffer.append(TEXT_705);
    }
    stringBuffer.append(TEXT_706);
    for (GenClassifier genClassifier : genPackage.getGenClassifiers()) {
    stringBuffer.append(TEXT_707);
    if (genClassifier instanceof GenClass) { GenClass genClass = (GenClass)genClassifier;
    if (!genClass.isInterface()) {
    stringBuffer.append(TEXT_708);
    stringBuffer.append(genClass.getQualifiedClassName());
    stringBuffer.append(TEXT_709);
    stringBuffer.append(genClass.getFormattedName());
    stringBuffer.append(TEXT_710);
    stringBuffer.append(genClass.getQualifiedClassName());
    } else {
    stringBuffer.append(TEXT_711);
    stringBuffer.append(genClass.getQualifiedInterfaceName());
    stringBuffer.append(TEXT_712);
    stringBuffer.append(genClass.getFormattedName());
    stringBuffer.append(TEXT_713);
    stringBuffer.append(genClass.getQualifiedInterfaceName());
    }
    } else if (genClassifier instanceof GenEnum) { GenEnum genEnum = (GenEnum)genClassifier;
    stringBuffer.append(TEXT_714);
    stringBuffer.append(genEnum.getQualifiedName());
    stringBuffer.append(TEXT_715);
    stringBuffer.append(genEnum.getFormattedName());
    stringBuffer.append(TEXT_716);
    stringBuffer.append(genEnum.getQualifiedName());
    } else if (genClassifier instanceof GenDataType) { GenDataType genDataType = (GenDataType)genClassifier;
    stringBuffer.append(TEXT_717);
    stringBuffer.append(genDataType.getFormattedName());
    stringBuffer.append(TEXT_718);
    if (!genDataType.isPrimitiveType() && !genDataType.isArrayType()) {
    stringBuffer.append(TEXT_719);
    stringBuffer.append(genDataType.getRawInstanceClassName());
    }
    }
    stringBuffer.append(TEXT_720);
    stringBuffer.append(genPackage.getQualifiedPackageClassName());
    stringBuffer.append(TEXT_721);
    stringBuffer.append(genClassifier.getClassifierAccessorName());
    stringBuffer.append(TEXT_722);
    if (genClassifier.hasAPITags()) {
    stringBuffer.append(TEXT_723);
    stringBuffer.append(genClassifier.getAPITags(genModel.getIndentation(stringBuffer)));
    }
    stringBuffer.append(TEXT_724);
    if (isJDK50 && genClassifier.hasAPIDeprecatedTag()) {
    stringBuffer.append(TEXT_725);
    }
    stringBuffer.append(TEXT_726);
    stringBuffer.append(publicStaticFinalFlag);
    stringBuffer.append(genClassifier.getImportedMetaType());
    stringBuffer.append(TEXT_727);
    stringBuffer.append(genPackage.getClassifierID(genClassifier));
    stringBuffer.append(TEXT_728);
    stringBuffer.append(genClassifier.getClassifierAccessorName());
    stringBuffer.append(TEXT_729);
    if (genClassifier instanceof GenClass) { GenClass genClass = (GenClass)genClassifier;
    for (GenFeature genFeature : genClass.getGenFeatures()) {
    stringBuffer.append(TEXT_730);
    stringBuffer.append(genFeature.getFormattedName());
    stringBuffer.append(TEXT_731);
    stringBuffer.append(genFeature.getFeatureKind());
    stringBuffer.append(TEXT_732);
    if (genFeature.hasImplicitAPITags()) {
    stringBuffer.append(TEXT_733);
    stringBuffer.append(genFeature.getImplicitAPITags(genModel.getIndentation(stringBuffer)));
    }
    stringBuffer.append(TEXT_734);
    if (isJDK50 && genFeature.hasImplicitAPIDeprecatedTag()) {
    stringBuffer.append(TEXT_735);
    }
    stringBuffer.append(TEXT_736);
    stringBuffer.append(publicStaticFinalFlag);
    stringBuffer.append(genFeature.getImportedMetaType());
    stringBuffer.append(TEXT_737);
    stringBuffer.append(genClass.getFeatureID(genFeature));
    stringBuffer.append(TEXT_738);
    stringBuffer.append(genFeature.getFeatureAccessorName());
    stringBuffer.append(TEXT_739);
    }
    if (genModel.isOperationReflection()) {
    for (GenOperation genOperation : genClass.getGenOperations()) {
    stringBuffer.append(TEXT_740);
    stringBuffer.append(genOperation.getFormattedName());
    stringBuffer.append(TEXT_741);
    if (genOperation.hasImplicitAPITags()) {
    stringBuffer.append(TEXT_742);
    stringBuffer.append(genOperation.getImplicitAPITags(genModel.getIndentation(stringBuffer)));
    }
    stringBuffer.append(TEXT_743);
    if (isJDK50 && genOperation.hasImplicitAPIDeprecatedTag()) {
    stringBuffer.append(TEXT_744);
    }
    stringBuffer.append(TEXT_745);
    stringBuffer.append(publicStaticFinalFlag);
    stringBuffer.append(genOperation.getImportedMetaType());
    stringBuffer.append(TEXT_746);
    stringBuffer.append(genClass.getOperationID(genOperation, false));
    stringBuffer.append(TEXT_747);
    stringBuffer.append(genOperation.getOperationAccessorName());
    stringBuffer.append(TEXT_748);
    }
    }
    }
    }
    stringBuffer.append(TEXT_749);
    }
    stringBuffer.append(TEXT_750);
    stringBuffer.append(isInterface ? genPackage.getPackageInterfaceName() : genPackage.getPackageClassName());
    genModel.emitSortedImports();
    stringBuffer.append(TEXT_751);
    return stringBuffer.toString();
  }
}
