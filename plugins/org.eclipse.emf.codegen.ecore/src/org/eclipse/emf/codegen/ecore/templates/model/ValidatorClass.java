package org.eclipse.emf.codegen.ecore.templates.model;

import java.util.*;
import org.eclipse.emf.codegen.ecore.genmodel.*;
import org.eclipse.emf.codegen.util.CodeGenUtil;
import org.eclipse.emf.codegen.ecore.genmodel.util.GenModelUtil;

public class ValidatorClass
{
  protected static String nl;
  public static synchronized ValidatorClass create(String lineSeparator)
  {
    nl = lineSeparator;
    ValidatorClass result = new ValidatorClass();
    nl = null;
    return result;
  }

  public final String NL = nl == null ? (System.getProperties().getProperty("line.separator")) : nl;
  protected final String TEXT_1 = "";
  protected final String TEXT_2 = "/**";
  protected final String TEXT_3 = NL + " * ";
  protected final String TEXT_4 = NL + " */" + NL + "package ";
  protected final String TEXT_5 = ";" + NL;
  protected final String TEXT_6 = NL + NL + "/**" + NL + " * <!-- begin-user-doc -->" + NL + " * The <b>Validator</b> for the model." + NL + " * <!-- end-user-doc -->" + NL + " * @see ";
  protected final String TEXT_7 = NL + " * ";
  protected final String TEXT_8 = NL + " * @generated" + NL + " */";
  protected final String TEXT_9 = NL + "@Deprecated";
  protected final String TEXT_10 = NL + "@SuppressWarnings(\"deprecation\")";
  protected final String TEXT_11 = NL + "public class ";
  protected final String TEXT_12 = " extends ";
  protected final String TEXT_13 = NL + "{";
  protected final String TEXT_14 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */" + NL + "\tpublic static final ";
  protected final String TEXT_15 = " copyright = ";
  protected final String TEXT_16 = ";";
  protected final String TEXT_17 = NL;
  protected final String TEXT_18 = NL + "\t/**" + NL + "\t * The cached model package" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */" + NL + "\tpublic static final ";
  protected final String TEXT_19 = " INSTANCE = new ";
  protected final String TEXT_20 = "();" + NL + "" + NL + "\t/**" + NL + "\t * A constant for the {@link org.eclipse.emf.common.util.Diagnostic#getSource() source} of diagnostic {@link org.eclipse.emf.common.util.Diagnostic#getCode() codes} from this package." + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @see org.eclipse.emf.common.util.Diagnostic#getSource()" + NL + "\t * @see org.eclipse.emf.common.util.Diagnostic#getCode()" + NL + "\t * @generated" + NL + "\t */" + NL + "\tpublic static final String DIAGNOSTIC_SOURCE = \"";
  protected final String TEXT_21 = "\";";
  protected final String TEXT_22 = NL;
  protected final String TEXT_23 = NL + "\t/**" + NL + "\t * The {@link org.eclipse.emf.common.util.Diagnostic#getCode() code} for constraint '";
  protected final String TEXT_24 = "' of '";
  protected final String TEXT_25 = "'." + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */" + NL + "\tpublic static final int ";
  protected final String TEXT_26 = " = ";
  protected final String TEXT_27 = ";" + NL;
  protected final String TEXT_28 = NL + "\t/**" + NL + "\t * A constant with a fixed name that can be used as the base value for additional hand written constants." + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */" + NL + "\tprivate static final int GENERATED_DIAGNOSTIC_CODE_COUNT = ";
  protected final String TEXT_29 = ";" + NL + "" + NL + "\t/**" + NL + "\t * A constant with a fixed name that can be used as the base value for additional hand written constants in a derived class." + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */" + NL + "\tprotected static final int DIAGNOSTIC_CODE_COUNT = GENERATED_DIAGNOSTIC_CODE_COUNT;" + NL;
  protected final String TEXT_30 = NL + "\t/**" + NL + "\t * The cached base package validator." + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */" + NL + "\tprotected ";
  protected final String TEXT_31 = " ";
  protected final String TEXT_32 = "Validator;" + NL;
  protected final String TEXT_33 = NL + "\t/**" + NL + "\t * Delegates evaluation of the given invariant expression against the object in the given context." + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */" + NL + "\tpublic static boolean validate(";
  protected final String TEXT_34 = " eClass, ";
  protected final String TEXT_35 = " eObject, DiagnosticChain diagnostics, ";
  protected final String TEXT_36 = " context, ";
  protected final String TEXT_37 = " validationDelegate, ";
  protected final String TEXT_38 = " invariant, ";
  protected final String TEXT_39 = " expression, int severity, ";
  protected final String TEXT_40 = " source, int code)" + NL + "\t{" + NL + "\t\treturn ";
  protected final String TEXT_41 = ".validate(eClass, eObject, diagnostics, context, validationDelegate, invariant, expression, severity, source, code);" + NL + "\t}" + NL;
  protected final String TEXT_42 = NL + "\t/**" + NL + "\t * Creates an instance of the switch." + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */" + NL + "\tpublic ";
  protected final String TEXT_43 = "()" + NL + "\t{" + NL + "\t\tsuper();";
  protected final String TEXT_44 = NL + "\t\t";
  protected final String TEXT_45 = "Validator = ";
  protected final String TEXT_46 = ".INSTANCE;";
  protected final String TEXT_47 = NL + "\t}" + NL + "" + NL + "\t/**" + NL + "\t * Returns the package of this validator switch." + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */";
  protected final String TEXT_48 = NL + "\t@Override";
  protected final String TEXT_49 = NL + "\tprotected EPackage getEPackage()" + NL + "\t{" + NL + "\t  return ";
  protected final String TEXT_50 = ".eINSTANCE;" + NL + "\t}" + NL + "" + NL + "\t/**" + NL + "\t * Calls <code>validateXXX</code> for the corresponding classifier of the model." + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */";
  protected final String TEXT_51 = NL + "\t@Override";
  protected final String TEXT_52 = NL + "\t@SuppressWarnings(\"unchecked\")";
  protected final String TEXT_53 = NL + "\tprotected boolean validate(int classifierID, Object value, DiagnosticChain diagnostics, ";
  protected final String TEXT_54 = " context)" + NL + "\t{" + NL + "\t\tswitch (classifierID)" + NL + "\t\t{";
  protected final String TEXT_55 = NL + "\t\t\tcase ";
  protected final String TEXT_56 = ".";
  protected final String TEXT_57 = ":";
  protected final String TEXT_58 = NL + "\t\t\t\treturn validate";
  protected final String TEXT_59 = "(((";
  protected final String TEXT_60 = ")value).";
  protected final String TEXT_61 = "(), diagnostics, context);";
  protected final String TEXT_62 = NL + "\t\t\t\treturn validate";
  protected final String TEXT_63 = "((";
  protected final String TEXT_64 = ")value, diagnostics, context);";
  protected final String TEXT_65 = NL + "\t\t\t\treturn validate";
  protected final String TEXT_66 = "(value, diagnostics, context);";
  protected final String TEXT_67 = NL + "\t\t\t\treturn validate";
  protected final String TEXT_68 = "((";
  protected final String TEXT_69 = ")value, diagnostics, context);";
  protected final String TEXT_70 = NL + "\t\t\t\treturn validate";
  protected final String TEXT_71 = "((";
  protected final String TEXT_72 = ")value, diagnostics, context);";
  protected final String TEXT_73 = NL + "\t\t\tdefault:" + NL + "\t\t\t\treturn true;" + NL + "\t\t}" + NL + "\t}" + NL;
  protected final String TEXT_74 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->";
  protected final String TEXT_75 = NL + "\t * ";
  protected final String TEXT_76 = NL + "\t * @generated" + NL + "\t */";
  protected final String TEXT_77 = NL + "\t@Deprecated";
  protected final String TEXT_78 = NL + "\tpublic boolean validate";
  protected final String TEXT_79 = "(";
  protected final String TEXT_80 = " ";
  protected final String TEXT_81 = ", DiagnosticChain ";
  protected final String TEXT_82 = ", ";
  protected final String TEXT_83 = " ";
  protected final String TEXT_84 = ")" + NL + "\t{";
  protected final String TEXT_85 = NL + "\t\treturn true;";
  protected final String TEXT_86 = NL + "\t\treturn validate_EveryDefaultConstraint(";
  protected final String TEXT_87 = "(";
  protected final String TEXT_88 = ")";
  protected final String TEXT_89 = ", ";
  protected final String TEXT_90 = ", ";
  protected final String TEXT_91 = ");";
  protected final String TEXT_92 = NL + "\t\tif (!validate_NoCircularContainment(";
  protected final String TEXT_93 = "(";
  protected final String TEXT_94 = ")";
  protected final String TEXT_95 = ", ";
  protected final String TEXT_96 = ", ";
  protected final String TEXT_97 = ")) return false;";
  protected final String TEXT_98 = NL + "\t\tboolean ";
  protected final String TEXT_99 = " = ";
  protected final String TEXT_100 = "validate";
  protected final String TEXT_101 = "_";
  protected final String TEXT_102 = "(";
  protected final String TEXT_103 = ", ";
  protected final String TEXT_104 = ", ";
  protected final String TEXT_105 = ");";
  protected final String TEXT_106 = NL + "\t\tif (";
  protected final String TEXT_107 = " || ";
  protected final String TEXT_108 = " != null) ";
  protected final String TEXT_109 = " &= ";
  protected final String TEXT_110 = "validate";
  protected final String TEXT_111 = "_";
  protected final String TEXT_112 = "(";
  protected final String TEXT_113 = ", ";
  protected final String TEXT_114 = ", ";
  protected final String TEXT_115 = ");";
  protected final String TEXT_116 = NL + "\t\treturn ";
  protected final String TEXT_117 = ";";
  protected final String TEXT_118 = NL + "\t}" + NL;
  protected final String TEXT_119 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t * @see #validate";
  protected final String TEXT_120 = "_";
  protected final String TEXT_121 = NL + "\t */" + NL + "\tpublic static final ";
  protected final String TEXT_122 = " ";
  protected final String TEXT_123 = "__VALUE = ";
  protected final String TEXT_124 = ";" + NL;
  protected final String TEXT_125 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t * @see #validate";
  protected final String TEXT_126 = "_";
  protected final String TEXT_127 = NL + "\t */" + NL + "\tpublic static final ";
  protected final String TEXT_128 = " ";
  protected final String TEXT_129 = "__VALUE = ";
  protected final String TEXT_130 = ";" + NL;
  protected final String TEXT_131 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t * @see #validate";
  protected final String TEXT_132 = "_";
  protected final String TEXT_133 = NL + "\t */" + NL + "\tpublic static final ";
  protected final String TEXT_134 = " ";
  protected final String TEXT_135 = "__UPPER_BOUND = ";
  protected final String TEXT_136 = ";" + NL + "" + NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t * @see #validate";
  protected final String TEXT_137 = "_";
  protected final String TEXT_138 = NL + "\t */" + NL + "\tpublic static final ";
  protected final String TEXT_139 = " ";
  protected final String TEXT_140 = "__LOWER_BOUND = ";
  protected final String TEXT_141 = ";" + NL;
  protected final String TEXT_142 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t * @see #validate";
  protected final String TEXT_143 = "_";
  protected final String TEXT_144 = NL + "\t */" + NL + "\tpublic static final  PatternMatcher [][] ";
  protected final String TEXT_145 = "__VALUES =" + NL + "\t\tnew PatternMatcher [][]" + NL + "\t\t{";
  protected final String TEXT_146 = NL + "\t\t\tnew PatternMatcher []" + NL + "\t\t\t{";
  protected final String TEXT_147 = NL + "\t\t\t\t";
  protected final String TEXT_148 = ".createPatternMatcher(";
  protected final String TEXT_149 = ")";
  protected final String TEXT_150 = NL + "\t\t\t}";
  protected final String TEXT_151 = NL + "\t\t};" + NL;
  protected final String TEXT_152 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t * @see #validate";
  protected final String TEXT_153 = "_";
  protected final String TEXT_154 = NL + "\t */";
  protected final String TEXT_155 = NL + "\t@SuppressWarnings(\"unchecked\")";
  protected final String TEXT_156 = NL + "\tpublic static final ";
  protected final String TEXT_157 = " ";
  protected final String TEXT_158 = "__VALUES =" + NL + "\t\twrapEnumerationValues" + NL + "\t\t\t(new Object[]" + NL + "\t\t\t {";
  protected final String TEXT_159 = NL + "\t\t\t\t ";
  protected final String TEXT_160 = "new ";
  protected final String TEXT_161 = "(";
  protected final String TEXT_162 = ")";
  protected final String TEXT_163 = NL + "\t\t\t });" + NL;
  protected final String TEXT_164 = NL + "\t/**" + NL + "\t * The cached validation expression for the ";
  protected final String TEXT_165 = " constraint of '<em>";
  protected final String TEXT_166 = "</em>'." + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */" + NL + "\tprotected static final ";
  protected final String TEXT_167 = " ";
  protected final String TEXT_168 = "__";
  protected final String TEXT_169 = "__EEXPRESSION = \"";
  protected final String TEXT_170 = "\";";
  protected final String TEXT_171 = NL;
  protected final String TEXT_172 = NL + "\t/**" + NL + "\t * Validates the ";
  protected final String TEXT_173 = " constraint of '<em>";
  protected final String TEXT_174 = "</em>'." + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */" + NL + "\tpublic boolean validate";
  protected final String TEXT_175 = "_";
  protected final String TEXT_176 = "(";
  protected final String TEXT_177 = " ";
  protected final String TEXT_178 = ", DiagnosticChain ";
  protected final String TEXT_179 = ", ";
  protected final String TEXT_180 = " ";
  protected final String TEXT_181 = ")" + NL + "\t{";
  protected final String TEXT_182 = NL + "\t\tboolean ";
  protected final String TEXT_183 = " = true;" + NL + "\t\tfor (";
  protected final String TEXT_184 = " i = ";
  protected final String TEXT_185 = ".iterator(); i.hasNext() && (result || diagnostics != null); )" + NL + "\t\t{" + NL + "\t\t\tObject item = i.next();";
  protected final String TEXT_186 = NL + "\t\t\tif (";
  protected final String TEXT_187 = ".isInstance(item))" + NL + "\t\t\t{" + NL + "\t\t\t\tresult &= ";
  protected final String TEXT_188 = "validate";
  protected final String TEXT_189 = "(";
  protected final String TEXT_190 = "(";
  protected final String TEXT_191 = "(";
  protected final String TEXT_192 = ")";
  protected final String TEXT_193 = ").";
  protected final String TEXT_194 = "()";
  protected final String TEXT_195 = ", ";
  protected final String TEXT_196 = ", ";
  protected final String TEXT_197 = ");" + NL + "\t\t\t}" + NL + "\t\t\telse";
  protected final String TEXT_198 = NL + "\t\t\tif (!";
  protected final String TEXT_199 = ".isInstance(item))";
  protected final String TEXT_200 = NL + "\t\t\t{" + NL + "\t\t\t\tresult = false;" + NL + "\t\t\t\treportDataValueTypeViolation(";
  protected final String TEXT_201 = ", item, ";
  protected final String TEXT_202 = ", ";
  protected final String TEXT_203 = ");" + NL + "\t\t\t}" + NL + "\t\t}" + NL + "\t\treturn result;";
  protected final String TEXT_204 = NL + "\t\tif (diagnostics != null)" + NL + "\t\t{" + NL + "\t\t\t";
  protected final String TEXT_205 = " tempDiagnostics = new BasicDiagnostic();";
  protected final String TEXT_206 = NL + "\t\t\tif (";
  protected final String TEXT_207 = "validate";
  protected final String TEXT_208 = "(";
  protected final String TEXT_209 = ", tempDiagnostics, ";
  protected final String TEXT_210 = ")) return true;";
  protected final String TEXT_211 = NL + "\t\t\tif (";
  protected final String TEXT_212 = ".isInstance(";
  protected final String TEXT_213 = "))" + NL + "\t\t\t{" + NL + "\t\t\t\t";
  protected final String TEXT_214 = "if (";
  protected final String TEXT_215 = "validate";
  protected final String TEXT_216 = "(";
  protected final String TEXT_217 = "(";
  protected final String TEXT_218 = "(";
  protected final String TEXT_219 = ")";
  protected final String TEXT_220 = ").";
  protected final String TEXT_221 = "()";
  protected final String TEXT_222 = ", tempDiagnostics, ";
  protected final String TEXT_223 = ")) ";
  protected final String TEXT_224 = "return true;" + NL + "\t\t\t}";
  protected final String TEXT_225 = NL + "\t\t\tfor (";
  protected final String TEXT_226 = " diagnostic : tempDiagnostics.getChildren())" + NL + "\t\t\t{" + NL + "\t\t\t\tdiagnostics.add(diagnostic);" + NL + "\t\t\t}";
  protected final String TEXT_227 = NL + "\t\t\t";
  protected final String TEXT_228 = " children = tempDiagnostics.getChildren();" + NL + "\t\t\tfor (int i = 0; i < children.size(); i++)" + NL + "\t\t\t{" + NL + "\t\t\t\tdiagnostics.add((";
  protected final String TEXT_229 = ")children.get(i));" + NL + "\t\t\t}";
  protected final String TEXT_230 = NL + "\t\t}" + NL + "\t\telse" + NL + "\t\t{";
  protected final String TEXT_231 = NL + "\t\t\tif (";
  protected final String TEXT_232 = "validate";
  protected final String TEXT_233 = "(";
  protected final String TEXT_234 = ", null, ";
  protected final String TEXT_235 = ")) return true;";
  protected final String TEXT_236 = NL + "\t\t\tif (";
  protected final String TEXT_237 = ".isInstance(";
  protected final String TEXT_238 = "))" + NL + "\t\t\t{" + NL + "\t\t\t\t";
  protected final String TEXT_239 = "if (";
  protected final String TEXT_240 = "validate";
  protected final String TEXT_241 = "(";
  protected final String TEXT_242 = "(";
  protected final String TEXT_243 = "(";
  protected final String TEXT_244 = ")";
  protected final String TEXT_245 = ").";
  protected final String TEXT_246 = "()";
  protected final String TEXT_247 = ", null, ";
  protected final String TEXT_248 = ")) ";
  protected final String TEXT_249 = "return true;" + NL + "\t\t\t}";
  protected final String TEXT_250 = NL + "\t\t}" + NL + "\t\treturn false;";
  protected final String TEXT_251 = NL + "\t\treturn validatePattern(";
  protected final String TEXT_252 = ", ";
  protected final String TEXT_253 = "new ";
  protected final String TEXT_254 = "(";
  protected final String TEXT_255 = ")";
  protected final String TEXT_256 = ", ";
  protected final String TEXT_257 = "__VALUES, ";
  protected final String TEXT_258 = ", ";
  protected final String TEXT_259 = ");";
  protected final String TEXT_260 = NL + "\t\t";
  protected final String TEXT_261 = " ";
  protected final String TEXT_262 = " = new ";
  protected final String TEXT_263 = "(";
  protected final String TEXT_264 = ");";
  protected final String TEXT_265 = NL + "\t\tboolean ";
  protected final String TEXT_266 = " = ";
  protected final String TEXT_267 = "__VALUES.contains(";
  protected final String TEXT_268 = ");" + NL + "\t\tif (!";
  protected final String TEXT_269 = " && ";
  protected final String TEXT_270 = " != null)" + NL + "\t\t\treportEnumerationViolation(";
  protected final String TEXT_271 = ", ";
  protected final String TEXT_272 = ", ";
  protected final String TEXT_273 = "__VALUES, ";
  protected final String TEXT_274 = ", ";
  protected final String TEXT_275 = ");" + NL + "\t\treturn ";
  protected final String TEXT_276 = ";";
  protected final String TEXT_277 = NL + "\t\tboolean ";
  protected final String TEXT_278 = " = ";
  protected final String TEXT_279 = " ";
  protected final String TEXT_280 = " ";
  protected final String TEXT_281 = "__VALUE;" + NL + "\t\tif (!";
  protected final String TEXT_282 = " && ";
  protected final String TEXT_283 = " != null)";
  protected final String TEXT_284 = NL + "\t\t\treportMinViolation(";
  protected final String TEXT_285 = ", new ";
  protected final String TEXT_286 = "(";
  protected final String TEXT_287 = "), new ";
  protected final String TEXT_288 = "(";
  protected final String TEXT_289 = "__VALUE), ";
  protected final String TEXT_290 = ", ";
  protected final String TEXT_291 = ", ";
  protected final String TEXT_292 = ");";
  protected final String TEXT_293 = NL + "\t\t\treportMinViolation(";
  protected final String TEXT_294 = ", ";
  protected final String TEXT_295 = ", ";
  protected final String TEXT_296 = "__VALUE, ";
  protected final String TEXT_297 = ", ";
  protected final String TEXT_298 = ", ";
  protected final String TEXT_299 = ");";
  protected final String TEXT_300 = NL + "\t\treturn ";
  protected final String TEXT_301 = ";";
  protected final String TEXT_302 = NL + "\t\tint ";
  protected final String TEXT_303 = " = ";
  protected final String TEXT_304 = ".compareCalendar(";
  protected final String TEXT_305 = ", ";
  protected final String TEXT_306 = "__VALUE);" + NL + "\t\tboolean ";
  protected final String TEXT_307 = " = ";
  protected final String TEXT_308 = " == 0 || ";
  protected final String TEXT_309 = " == 1;";
  protected final String TEXT_310 = NL + "\t\tint ";
  protected final String TEXT_311 = " = ";
  protected final String TEXT_312 = ".compareDuration(";
  protected final String TEXT_313 = ", ";
  protected final String TEXT_314 = "__VALUE);" + NL + "\t\tboolean ";
  protected final String TEXT_315 = " = ";
  protected final String TEXT_316 = " == 0 || ";
  protected final String TEXT_317 = " == 1;";
  protected final String TEXT_318 = NL + "\t\tboolean ";
  protected final String TEXT_319 = " = ";
  protected final String TEXT_320 = ".compareTo(";
  protected final String TEXT_321 = "__VALUE) ";
  protected final String TEXT_322 = " 0;";
  protected final String TEXT_323 = NL + "\t\tif (!";
  protected final String TEXT_324 = " && ";
  protected final String TEXT_325 = " != null)" + NL + "\t\t\treportMinViolation(";
  protected final String TEXT_326 = ", ";
  protected final String TEXT_327 = ", ";
  protected final String TEXT_328 = "__VALUE, ";
  protected final String TEXT_329 = ", ";
  protected final String TEXT_330 = ", ";
  protected final String TEXT_331 = ");" + NL + "\t\treturn ";
  protected final String TEXT_332 = ";";
  protected final String TEXT_333 = NL + "\t\tboolean ";
  protected final String TEXT_334 = " = ";
  protected final String TEXT_335 = " ";
  protected final String TEXT_336 = " ";
  protected final String TEXT_337 = "__VALUE;" + NL + "\t\tif (!";
  protected final String TEXT_338 = " && ";
  protected final String TEXT_339 = " != null)";
  protected final String TEXT_340 = NL + "\t\t\treportMaxViolation(";
  protected final String TEXT_341 = ", new ";
  protected final String TEXT_342 = "(";
  protected final String TEXT_343 = "), new ";
  protected final String TEXT_344 = "(";
  protected final String TEXT_345 = "__VALUE), ";
  protected final String TEXT_346 = ", ";
  protected final String TEXT_347 = ", ";
  protected final String TEXT_348 = ");";
  protected final String TEXT_349 = NL + "\t\t\treportMaxViolation(";
  protected final String TEXT_350 = ", ";
  protected final String TEXT_351 = ", ";
  protected final String TEXT_352 = "__VALUE, ";
  protected final String TEXT_353 = ", ";
  protected final String TEXT_354 = ", ";
  protected final String TEXT_355 = ");";
  protected final String TEXT_356 = NL + "\t\treturn ";
  protected final String TEXT_357 = ";";
  protected final String TEXT_358 = NL + "\t\tint ";
  protected final String TEXT_359 = " = ";
  protected final String TEXT_360 = ".compareCalendar(";
  protected final String TEXT_361 = ", ";
  protected final String TEXT_362 = "__VALUE);" + NL + "\t\tboolean ";
  protected final String TEXT_363 = " = ";
  protected final String TEXT_364 = " == 0 || ";
  protected final String TEXT_365 = " == -1;";
  protected final String TEXT_366 = NL + "\t\tint ";
  protected final String TEXT_367 = " = ";
  protected final String TEXT_368 = ".compareDuration(";
  protected final String TEXT_369 = ", ";
  protected final String TEXT_370 = "__VALUE);" + NL + "\t\tboolean ";
  protected final String TEXT_371 = " = ";
  protected final String TEXT_372 = " == 0 || ";
  protected final String TEXT_373 = " == -1;";
  protected final String TEXT_374 = NL + "\t\tboolean ";
  protected final String TEXT_375 = " = ";
  protected final String TEXT_376 = ".compareTo(";
  protected final String TEXT_377 = "__VALUE) ";
  protected final String TEXT_378 = " 0;";
  protected final String TEXT_379 = NL + "\t\tif (!";
  protected final String TEXT_380 = " && ";
  protected final String TEXT_381 = " != null)" + NL + "\t\t\treportMaxViolation(";
  protected final String TEXT_382 = ", ";
  protected final String TEXT_383 = ", ";
  protected final String TEXT_384 = "__VALUE, ";
  protected final String TEXT_385 = ", ";
  protected final String TEXT_386 = ", ";
  protected final String TEXT_387 = ");" + NL + "\t\treturn ";
  protected final String TEXT_388 = ";";
  protected final String TEXT_389 = NL + "\t\tint length = ";
  protected final String TEXT_390 = ".";
  protected final String TEXT_391 = ";" + NL + "\t\tboolean ";
  protected final String TEXT_392 = " = length >= ";
  protected final String TEXT_393 = ";" + NL + "\t\tif (!";
  protected final String TEXT_394 = " && ";
  protected final String TEXT_395 = " != null)" + NL + "\t\t\treportMinLengthViolation(";
  protected final String TEXT_396 = ", ";
  protected final String TEXT_397 = ", length, ";
  protected final String TEXT_398 = ", ";
  protected final String TEXT_399 = ", ";
  protected final String TEXT_400 = ");" + NL + "\t\treturn ";
  protected final String TEXT_401 = ";";
  protected final String TEXT_402 = NL + "\t\tint length = ";
  protected final String TEXT_403 = ".";
  protected final String TEXT_404 = ";" + NL + "\t\tboolean ";
  protected final String TEXT_405 = " = length <= ";
  protected final String TEXT_406 = ";" + NL + "\t\tif (!";
  protected final String TEXT_407 = " && ";
  protected final String TEXT_408 = " != null)" + NL + "\t\t\treportMaxLengthViolation(";
  protected final String TEXT_409 = ", ";
  protected final String TEXT_410 = ", length, ";
  protected final String TEXT_411 = ", ";
  protected final String TEXT_412 = ", ";
  protected final String TEXT_413 = ");" + NL + "\t\treturn ";
  protected final String TEXT_414 = ";";
  protected final String TEXT_415 = NL + "\t\tboolean ";
  protected final String TEXT_416 = " = ";
  protected final String TEXT_417 = " > ";
  protected final String TEXT_418 = "__LOWER_BOUND && ";
  protected final String TEXT_419 = " < ";
  protected final String TEXT_420 = "__UPPER_BOUND;" + NL + "\t\tif (!";
  protected final String TEXT_421 = " && ";
  protected final String TEXT_422 = " != null)";
  protected final String TEXT_423 = NL + "\t\t\treportTotalDigitsViolation(";
  protected final String TEXT_424 = ", new ";
  protected final String TEXT_425 = "(";
  protected final String TEXT_426 = "), ";
  protected final String TEXT_427 = ", ";
  protected final String TEXT_428 = ", ";
  protected final String TEXT_429 = ");";
  protected final String TEXT_430 = NL + "\t\t\treportTotalDigitsViolation(";
  protected final String TEXT_431 = ", ";
  protected final String TEXT_432 = ", ";
  protected final String TEXT_433 = ", ";
  protected final String TEXT_434 = ", ";
  protected final String TEXT_435 = ");";
  protected final String TEXT_436 = NL + "\t\tint scale = ";
  protected final String TEXT_437 = ".scale();" + NL + "\t\tint totalDigits = scale < 0 ? ";
  protected final String TEXT_438 = ".precision() - scale : ";
  protected final String TEXT_439 = ".precision();" + NL + "\t\tboolean ";
  protected final String TEXT_440 = " = totalDigits <= ";
  protected final String TEXT_441 = ";" + NL + "\t\tif (!";
  protected final String TEXT_442 = " && ";
  protected final String TEXT_443 = " != null)" + NL + "\t\t\treportTotalDigitsViolation(";
  protected final String TEXT_444 = ", ";
  protected final String TEXT_445 = ", ";
  protected final String TEXT_446 = ", ";
  protected final String TEXT_447 = ", ";
  protected final String TEXT_448 = ");";
  protected final String TEXT_449 = NL + "\t\tboolean ";
  protected final String TEXT_450 = " = ";
  protected final String TEXT_451 = ".compareTo(";
  protected final String TEXT_452 = "__LOWER_BOUND) > 0 && ";
  protected final String TEXT_453 = ".compareTo(";
  protected final String TEXT_454 = "__UPPER_BOUND) < 0;" + NL + "\t\tif (!";
  protected final String TEXT_455 = " && ";
  protected final String TEXT_456 = " != null)" + NL + "\t\t\treportTotalDigitsViolation(";
  protected final String TEXT_457 = ", ";
  protected final String TEXT_458 = ", ";
  protected final String TEXT_459 = ", ";
  protected final String TEXT_460 = ", ";
  protected final String TEXT_461 = ");";
  protected final String TEXT_462 = NL + "\t\treturn ";
  protected final String TEXT_463 = ";";
  protected final String TEXT_464 = NL + "\t\tboolean ";
  protected final String TEXT_465 = " = ";
  protected final String TEXT_466 = ".scale() <= ";
  protected final String TEXT_467 = ";" + NL + "\t\tif (!";
  protected final String TEXT_468 = " && ";
  protected final String TEXT_469 = " != null)" + NL + "\t\t\treportFractionDigitsViolation(";
  protected final String TEXT_470 = ", ";
  protected final String TEXT_471 = ", ";
  protected final String TEXT_472 = ", ";
  protected final String TEXT_473 = ", ";
  protected final String TEXT_474 = ");" + NL + "\t\treturn ";
  protected final String TEXT_475 = ";";
  protected final String TEXT_476 = NL + "\t\t// TODO override the constraint, if desired" + NL + "\t\t// -> uncomment the scaffolding" + NL + "\t\t// -> specify the condition that violates the constraint" + NL + "\t\t// -> verify the diagnostic details, including severity, code, and message" + NL + "\t\t// Ensure that you remove @generated or mark it @generated NOT" + NL + "\t\tif (false)" + NL + "\t\t{" + NL + "\t\t\tif (";
  protected final String TEXT_477 = " != null)" + NL + "\t\t\t{" + NL + "\t\t\t\t";
  protected final String TEXT_478 = ".add";
  protected final String TEXT_479 = NL + "\t\t\t\t\t(createDiagnostic" + NL + "\t\t\t\t\t\t(";
  protected final String TEXT_480 = ".ERROR," + NL + "\t\t\t\t\t\t DIAGNOSTIC_SOURCE," + NL + "\t\t\t\t\t\t 0," + NL + "\t\t\t\t\t\t \"_UI_GenericConstraint_diagnostic\",";
  protected final String TEXT_481 = NL + "\t\t\t\t\t\t new Object[] { \"";
  protected final String TEXT_482 = "\", getValueLabel(";
  protected final String TEXT_483 = ", ";
  protected final String TEXT_484 = ", ";
  protected final String TEXT_485 = ") },";
  protected final String TEXT_486 = NL + "\t\t\t\t\t\t new Object[] { ";
  protected final String TEXT_487 = " }," + NL + "\t\t\t\t\t\t context));";
  protected final String TEXT_488 = NL + "\t\t\t\t\t(new ";
  protected final String TEXT_489 = NL + "\t\t\t\t\t\t(";
  protected final String TEXT_490 = ".ERROR," + NL + "\t\t\t\t\t\t DIAGNOSTIC_SOURCE," + NL + "\t\t\t\t\t\t 0," + NL + "\t\t\t\t\t\t ";
  protected final String TEXT_491 = ".INSTANCE.getString(\"_UI_GenericConstraint_diagnostic\", new Object[] { \"";
  protected final String TEXT_492 = "\", getValueLabel(";
  protected final String TEXT_493 = ", ";
  protected final String TEXT_494 = ", ";
  protected final String TEXT_495 = ") }),";
  protected final String TEXT_496 = NL + "\t\t\t\t\t\t new Object[] { ";
  protected final String TEXT_497 = " }));";
  protected final String TEXT_498 = NL + "\t\t\t}" + NL + "\t\t\treturn false;" + NL + "\t\t}" + NL + "\t\treturn ";
  protected final String TEXT_499 = "validate";
  protected final String TEXT_500 = "_";
  protected final String TEXT_501 = "(";
  protected final String TEXT_502 = ", ";
  protected final String TEXT_503 = ", ";
  protected final String TEXT_504 = ");";
  protected final String TEXT_505 = NL + "\t\treturn" + NL + "\t\t\tvalidate" + NL + "\t\t\t\t(";
  protected final String TEXT_506 = "," + NL + "\t\t\t\t ";
  protected final String TEXT_507 = "," + NL + "\t\t\t\t ";
  protected final String TEXT_508 = "," + NL + "\t\t\t\t ";
  protected final String TEXT_509 = "," + NL + "\t\t\t\t \"";
  protected final String TEXT_510 = "\",";
  protected final String TEXT_511 = NL + "\t\t\t\t \"";
  protected final String TEXT_512 = "\",";
  protected final String TEXT_513 = NL + "\t\t\t\t ";
  protected final String TEXT_514 = "__";
  protected final String TEXT_515 = "__EEXPRESSION," + NL + "\t\t\t\t ";
  protected final String TEXT_516 = ".ERROR," + NL + "\t\t\t\t DIAGNOSTIC_SOURCE," + NL + "\t\t\t\t 0);";
  protected final String TEXT_517 = NL + "\t\t// TODO implement the constraint" + NL + "\t\t// -> specify the condition that violates the constraint" + NL + "\t\t// -> verify the diagnostic details, including severity, code, and message" + NL + "\t\t// Ensure that you remove @generated or mark it @generated NOT" + NL + "\t\tif (false)" + NL + "\t\t{" + NL + "\t\t\tif (";
  protected final String TEXT_518 = " != null)" + NL + "\t\t\t{" + NL + "\t\t\t\t";
  protected final String TEXT_519 = ".add";
  protected final String TEXT_520 = NL + "\t\t\t\t\t(createDiagnostic" + NL + "\t\t\t\t\t\t(";
  protected final String TEXT_521 = ".ERROR," + NL + "\t\t\t\t\t\t DIAGNOSTIC_SOURCE," + NL + "\t\t\t\t\t\t 0," + NL + "\t\t\t\t\t\t \"_UI_GenericConstraint_diagnostic\",";
  protected final String TEXT_522 = NL + "\t\t\t\t\t\t new Object[] { \"";
  protected final String TEXT_523 = "\", getValueLabel(";
  protected final String TEXT_524 = ", ";
  protected final String TEXT_525 = ", ";
  protected final String TEXT_526 = ") },";
  protected final String TEXT_527 = NL + "\t\t\t\t\t\t new Object[] { ";
  protected final String TEXT_528 = " }," + NL + "\t\t\t\t\t\t context));";
  protected final String TEXT_529 = NL + "\t\t\t\t\t(new ";
  protected final String TEXT_530 = NL + "\t\t\t\t\t\t(";
  protected final String TEXT_531 = ".ERROR," + NL + "\t\t\t\t\t\t DIAGNOSTIC_SOURCE," + NL + "\t\t\t\t\t\t 0," + NL + "\t\t\t\t\t\t ";
  protected final String TEXT_532 = ".INSTANCE.getString(\"_UI_GenericConstraint_diagnostic\", new Object[] { \"";
  protected final String TEXT_533 = "\", getValueLabel(";
  protected final String TEXT_534 = ", ";
  protected final String TEXT_535 = ", ";
  protected final String TEXT_536 = ") }),";
  protected final String TEXT_537 = NL + "\t\t\t\t\t\t new Object[] { ";
  protected final String TEXT_538 = " }));";
  protected final String TEXT_539 = NL + "\t\t\t}" + NL + "\t\t\treturn false;" + NL + "\t\t}" + NL + "\t\treturn true;";
  protected final String TEXT_540 = NL + "\t\treturn ";
  protected final String TEXT_541 = ".";
  protected final String TEXT_542 = "(";
  protected final String TEXT_543 = ", ";
  protected final String TEXT_544 = ");";
  protected final String TEXT_545 = NL + "\t\t// TODO override the constraint, if desired" + NL + "\t\t// -> uncomment the scaffolding" + NL + "\t\t// -> specify the condition that violates the constraint" + NL + "\t\t// -> verify the diagnostic details, including severity, code, and message" + NL + "\t\t// Ensure that you remove @generated or mark it @generated NOT" + NL + "\t\tif (false)" + NL + "\t\t{" + NL + "\t\t\tif (";
  protected final String TEXT_546 = " != null)" + NL + "\t\t\t{" + NL + "\t\t\t\t";
  protected final String TEXT_547 = ".add";
  protected final String TEXT_548 = NL + "\t\t\t\t\t(createDiagnostic" + NL + "\t\t\t\t\t\t(";
  protected final String TEXT_549 = ".ERROR," + NL + "\t\t\t\t\t\t DIAGNOSTIC_SOURCE," + NL + "\t\t\t\t\t\t 0," + NL + "\t\t\t\t\t\t \"_UI_GenericConstraint_diagnostic\",";
  protected final String TEXT_550 = NL + "\t\t\t\t\t\t new Object[] { \"";
  protected final String TEXT_551 = "\", getObjectLabel(";
  protected final String TEXT_552 = ", ";
  protected final String TEXT_553 = ") },";
  protected final String TEXT_554 = NL + "\t\t\t\t\t\t new Object[] { ";
  protected final String TEXT_555 = " }," + NL + "\t\t\t\t\t\t context));";
  protected final String TEXT_556 = NL + "\t\t\t\t\t(new ";
  protected final String TEXT_557 = NL + "\t\t\t\t\t\t(";
  protected final String TEXT_558 = ".ERROR," + NL + "\t\t\t\t\t\t DIAGNOSTIC_SOURCE," + NL + "\t\t\t\t\t\t 0," + NL + "\t\t\t\t\t\t ";
  protected final String TEXT_559 = ".INSTANCE.getString(\"_UI_GenericConstraint_diagnostic\", new Object[] { \"";
  protected final String TEXT_560 = "\", getObjectLabel(";
  protected final String TEXT_561 = ", ";
  protected final String TEXT_562 = ") }),";
  protected final String TEXT_563 = NL + "\t\t\t\t\t\t new Object[] { ";
  protected final String TEXT_564 = " }));";
  protected final String TEXT_565 = NL + "\t\t\t}" + NL + "\t\t\treturn false;" + NL + "\t\t}" + NL + "\t\treturn ";
  protected final String TEXT_566 = "validate";
  protected final String TEXT_567 = "_";
  protected final String TEXT_568 = "(";
  protected final String TEXT_569 = ", ";
  protected final String TEXT_570 = ", ";
  protected final String TEXT_571 = ");";
  protected final String TEXT_572 = NL + "\t\treturn" + NL + "\t\t\tvalidate" + NL + "\t\t\t\t(";
  protected final String TEXT_573 = "," + NL + "\t\t\t\t ";
  protected final String TEXT_574 = "(";
  protected final String TEXT_575 = ")";
  protected final String TEXT_576 = "," + NL + "\t\t\t\t ";
  protected final String TEXT_577 = "," + NL + "\t\t\t\t ";
  protected final String TEXT_578 = "," + NL + "\t\t\t\t \"";
  protected final String TEXT_579 = "\",";
  protected final String TEXT_580 = NL + "\t\t\t\t \"";
  protected final String TEXT_581 = "\",";
  protected final String TEXT_582 = NL + "\t\t\t\t ";
  protected final String TEXT_583 = "__";
  protected final String TEXT_584 = "__EEXPRESSION," + NL + "\t\t\t\t ";
  protected final String TEXT_585 = ".ERROR," + NL + "\t\t\t\t DIAGNOSTIC_SOURCE," + NL + "\t\t\t\t 0);";
  protected final String TEXT_586 = NL + "\t\t// TODO implement the constraint" + NL + "\t\t// -> specify the condition that violates the constraint" + NL + "\t\t// -> verify the diagnostic details, including severity, code, and message" + NL + "\t\t// Ensure that you remove @generated or mark it @generated NOT" + NL + "\t\tif (false)" + NL + "\t\t{" + NL + "\t\t\tif (";
  protected final String TEXT_587 = " != null)" + NL + "\t\t\t{" + NL + "\t\t\t\t";
  protected final String TEXT_588 = ".add";
  protected final String TEXT_589 = NL + "\t\t\t\t\t(createDiagnostic" + NL + "\t\t\t\t\t\t(";
  protected final String TEXT_590 = ".ERROR," + NL + "\t\t\t\t\t\t DIAGNOSTIC_SOURCE," + NL + "\t\t\t\t\t\t 0," + NL + "\t\t\t\t\t\t \"_UI_GenericConstraint_diagnostic\",";
  protected final String TEXT_591 = NL + "\t\t\t\t\t\t new Object[] { \"";
  protected final String TEXT_592 = "\", getObjectLabel(";
  protected final String TEXT_593 = ", ";
  protected final String TEXT_594 = ") },";
  protected final String TEXT_595 = NL + "\t\t\t\t\t\t new Object[] { ";
  protected final String TEXT_596 = " }," + NL + "\t\t\t\t\t\t context));";
  protected final String TEXT_597 = NL + "\t\t\t\t\t(new ";
  protected final String TEXT_598 = NL + "\t\t\t\t\t\t(";
  protected final String TEXT_599 = ".ERROR," + NL + "\t\t\t\t\t\t DIAGNOSTIC_SOURCE," + NL + "\t\t\t\t\t\t 0," + NL + "\t\t\t\t\t\t ";
  protected final String TEXT_600 = ".INSTANCE.getString(\"_UI_GenericConstraint_diagnostic\", new Object[] { \"";
  protected final String TEXT_601 = "\", getObjectLabel(";
  protected final String TEXT_602 = ", ";
  protected final String TEXT_603 = ") }),";
  protected final String TEXT_604 = NL + "\t\t\t\t\t\t new Object[] { ";
  protected final String TEXT_605 = " }));";
  protected final String TEXT_606 = NL + "\t\t\t}" + NL + "\t\t\treturn false;" + NL + "\t\t}" + NL + "\t\treturn true;";
  protected final String TEXT_607 = NL + "\t}" + NL;
  protected final String TEXT_608 = NL + "\t/**" + NL + "\t * Returns the resource locator that will be used to fetch messages for this validator's diagnostics." + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */";
  protected final String TEXT_609 = NL + "\t@Override";
  protected final String TEXT_610 = NL + "\tpublic ";
  protected final String TEXT_611 = " getResourceLocator()" + NL + "\t{";
  protected final String TEXT_612 = NL + "\t\treturn ";
  protected final String TEXT_613 = ".INSTANCE;";
  protected final String TEXT_614 = NL + "\t\t// TODO" + NL + "\t\t// Specialize this to return a resource locator for messages specific to this validator." + NL + "\t\t// Ensure that you remove @generated or mark it @generated NOT" + NL + "\t\treturn super.getResourceLocator();";
  protected final String TEXT_615 = NL + "\t}" + NL;
  protected final String TEXT_616 = NL + "} //";
  protected final String TEXT_617 = NL;

  public String generate(Object argument)
  {
    final StringBuffer stringBuffer = new StringBuffer();
    
/**
 * Copyright (c) 2002-2013 IBM Corporation and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *   IBM - Initial API and implementation
 */

    GenPackage genPackage = (GenPackage)argument; GenModel genModel=genPackage.getGenModel();
    final boolean isJDK50 = genModel.getComplianceLevel().getValue() >= GenJDKLevel.JDK50;
    final String singleWildcard = genModel.getComplianceLevel().getValue() >= GenJDKLevel.JDK50 ? "<?>" : "";
    stringBuffer.append(TEXT_1);
    stringBuffer.append(TEXT_2);
    {GenBase copyrightHolder = argument instanceof GenBase ? (GenBase)argument : argument instanceof Object[] && ((Object[])argument)[0] instanceof GenBase ? (GenBase)((Object[])argument)[0] : null;
    if (copyrightHolder != null && copyrightHolder.hasCopyright()) {
    stringBuffer.append(TEXT_3);
    stringBuffer.append(copyrightHolder.getCopyright(copyrightHolder.getGenModel().getIndentation(stringBuffer)));
    }}
    stringBuffer.append(TEXT_4);
    stringBuffer.append(genPackage.getUtilitiesPackageName());
    stringBuffer.append(TEXT_5);
    genModel.addImport("java.util.Map");
    final String _Map = genModel.useGenerics() ? "Map<" + genModel.getImportedName("java.lang.Object") + ", " + genModel.getImportedName("java.lang.Object") + ">" : "Map";
    final String objectArgument = genModel.useGenerics() ? "<" + genModel.getImportedName("java.lang.Object") + ">" : "";
    genModel.addImport("org.eclipse.emf.common.util.DiagnosticChain");
    genModel.addImport("org.eclipse.emf.ecore.EPackage");
    if (!genPackage.hasJavaLangConflict() && !genPackage.getUtilitiesPackageName().equals(genPackage.getInterfacePackageName())) genModel.addImport(genPackage.getInterfacePackageName() + ".*");
    genModel.markImportLocation(stringBuffer);
    genModel.addPseudoImport("org.eclipse.emf.ecore.EValidator.Descriptor");
    genModel.addPseudoImport("org.eclipse.emf.ecore.EValidator.Registry");
    genModel.addPseudoImport("org.eclipse.emf.ecore.EValidator.SubstitutionLabelProvider");
    genModel.addPseudoImport("org.eclipse.emf.ecore.EValidator.PatternMatcher");
    stringBuffer.append(TEXT_6);
    stringBuffer.append(genPackage.getQualifiedPackageInterfaceName());
    if (genPackage.hasAPITags()) {
    stringBuffer.append(TEXT_7);
    stringBuffer.append(genPackage.getAPITags(genModel.getIndentation(stringBuffer)));
    }
    stringBuffer.append(TEXT_8);
    if (isJDK50 && genPackage.hasAPIDeprecatedTag()) {
    stringBuffer.append(TEXT_9);
    } else if (isJDK50 && GenModelUtil.hasAPIDeprecatedTag(genPackage.getGenClassifiers())) {
    stringBuffer.append(TEXT_10);
    }
    stringBuffer.append(TEXT_11);
    stringBuffer.append(genPackage.getValidatorClassName());
    stringBuffer.append(TEXT_12);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.EObjectValidator"));
    stringBuffer.append(TEXT_13);
    if (genModel.hasCopyrightField()) {
    stringBuffer.append(TEXT_14);
    stringBuffer.append(genModel.getImportedName("java.lang.String"));
    stringBuffer.append(TEXT_15);
    stringBuffer.append(genModel.getCopyrightFieldLiteral());
    stringBuffer.append(TEXT_16);
    stringBuffer.append(genModel.getNonNLS());
    stringBuffer.append(TEXT_17);
    }
    stringBuffer.append(TEXT_18);
    stringBuffer.append(genPackage.getValidatorClassName());
    stringBuffer.append(TEXT_19);
    stringBuffer.append(genPackage.getValidatorClassName());
    stringBuffer.append(TEXT_20);
    stringBuffer.append(genPackage.getInterfacePackageName());
    stringBuffer.append(TEXT_21);
    stringBuffer.append(genModel.getNonNLS());
    stringBuffer.append(TEXT_22);
    int count = 0; for (GenClass genClass : genPackage.getGenClasses()) {
    for (GenOperation genOperation : genClass.getInvariantOperations()) {
    stringBuffer.append(TEXT_23);
    stringBuffer.append(genOperation.getFormattedName());
    stringBuffer.append(TEXT_24);
    stringBuffer.append(genClass.getFormattedName());
    stringBuffer.append(TEXT_25);
    stringBuffer.append(genClass.getOperationID(genOperation));
    stringBuffer.append(TEXT_26);
    stringBuffer.append(++count);
    stringBuffer.append(TEXT_27);
    }}
    stringBuffer.append(TEXT_28);
    stringBuffer.append(count);
    stringBuffer.append(TEXT_29);
    for (GenPackage baseGenPackage : genPackage.getAllValidatorBaseGenPackages()) {
    stringBuffer.append(TEXT_30);
    stringBuffer.append(baseGenPackage.getImportedValidatorClassName());
    stringBuffer.append(TEXT_31);
    stringBuffer.append(genPackage.getValidatorPackageUniqueSafeName(baseGenPackage));
    stringBuffer.append(TEXT_32);
    }
    if (genPackage.hasInvariantExpressions()) {
    stringBuffer.append(TEXT_33);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.EClass"));
    stringBuffer.append(TEXT_34);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.EObject"));
    stringBuffer.append(TEXT_35);
    stringBuffer.append(_Map);
    stringBuffer.append(TEXT_36);
    stringBuffer.append(genModel.getImportedName("java.lang.String"));
    stringBuffer.append(TEXT_37);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.EOperation"));
    stringBuffer.append(TEXT_38);
    stringBuffer.append(genModel.getImportedName("java.lang.String"));
    stringBuffer.append(TEXT_39);
    stringBuffer.append(genModel.getImportedName("java.lang.String"));
    stringBuffer.append(TEXT_40);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.EObjectValidator"));
    stringBuffer.append(TEXT_41);
    }
    stringBuffer.append(TEXT_42);
    stringBuffer.append(genPackage.getValidatorClassName());
    stringBuffer.append(TEXT_43);
    for (GenPackage baseGenPackage : genPackage.getAllValidatorBaseGenPackages()) {
    stringBuffer.append(TEXT_44);
    stringBuffer.append(genPackage.getValidatorPackageUniqueSafeName(baseGenPackage));
    stringBuffer.append(TEXT_45);
    stringBuffer.append(baseGenPackage.getImportedValidatorClassName());
    stringBuffer.append(TEXT_46);
    }
    stringBuffer.append(TEXT_47);
    if (genModel.useClassOverrideAnnotation()) {
    stringBuffer.append(TEXT_48);
    }
    stringBuffer.append(TEXT_49);
    stringBuffer.append(genPackage.getImportedPackageInterfaceName());
    stringBuffer.append(TEXT_50);
    if (genModel.useClassOverrideAnnotation()) {
    stringBuffer.append(TEXT_51);
    }
    for (GenClassifier genClassifier : genPackage.getGenClassifiers()) {
    if (genClassifier.isUncheckedCast()) {
    stringBuffer.append(TEXT_52);
    break; }
    }
    stringBuffer.append(TEXT_53);
    stringBuffer.append(_Map);
    stringBuffer.append(TEXT_54);
    for (GenClassifier genClassifier : genPackage.getGenClassifiers()) {
    stringBuffer.append(TEXT_55);
    stringBuffer.append(genPackage.getImportedPackageInterfaceName());
    stringBuffer.append(TEXT_56);
    stringBuffer.append(genPackage.getClassifierID(genClassifier));
    stringBuffer.append(TEXT_57);
    if (genClassifier instanceof GenDataType) { GenDataType genDataType = (GenDataType)genClassifier;
    if (genDataType.isPrimitiveType()) {
    if (genModel.getComplianceLevel().getValue() < GenJDKLevel.JDK50) {
    stringBuffer.append(TEXT_58);
    stringBuffer.append(genDataType.getName());
    stringBuffer.append(TEXT_59);
    stringBuffer.append(genDataType.getObjectInstanceClassName());
    stringBuffer.append(TEXT_60);
    stringBuffer.append(genDataType.getPrimitiveValueFunction());
    stringBuffer.append(TEXT_61);
    } else {
    stringBuffer.append(TEXT_62);
    stringBuffer.append(genDataType.getName());
    stringBuffer.append(TEXT_63);
    stringBuffer.append(genDataType.getObjectInstanceClassName());
    stringBuffer.append(TEXT_64);
    }
    } else if (genDataType.isObjectType()) {
    stringBuffer.append(TEXT_65);
    stringBuffer.append(genDataType.getName());
    stringBuffer.append(TEXT_66);
    } else {
    stringBuffer.append(TEXT_67);
    stringBuffer.append(genDataType.getName());
    stringBuffer.append(TEXT_68);
    stringBuffer.append(genDataType.getObjectType().getImportedWildcardInstanceClassName());
    stringBuffer.append(TEXT_69);
    }
    } else { GenClass genClass = (GenClass)genClassifier;
    stringBuffer.append(TEXT_70);
    stringBuffer.append(genClass.getName());
    stringBuffer.append(TEXT_71);
    stringBuffer.append(genClass.getImportedWildcardInstanceClassName());
    stringBuffer.append(TEXT_72);
    }
    }
    stringBuffer.append(TEXT_73);
    for (GenClassifier genClassifier : genPackage.getGenClassifiers()) {String result = "result".equals(genClassifier.getSafeUncapName()) ? "theResult" : "result"; String diagnostics = "diagnostics".equals(genClassifier.getSafeUncapName()) ? "theDiagnostics" : "diagnostics"; String item = "item".equals(genClassifier.getSafeUncapName()) ? "theItem" : "item"; String context = "context".equals(genClassifier.getSafeUncapName()) ? "theContext" : "context";
    stringBuffer.append(TEXT_74);
    if (genClassifier.hasAPITags()) {
    stringBuffer.append(TEXT_75);
    stringBuffer.append(genClassifier.getAPITags(genModel.getIndentation(stringBuffer)));
    }
    stringBuffer.append(TEXT_76);
    if (isJDK50 && genClassifier.hasAPIDeprecatedTag()) {
    stringBuffer.append(TEXT_77);
    }
    stringBuffer.append(TEXT_78);
    stringBuffer.append(genClassifier.getName());
    stringBuffer.append(TEXT_79);
    stringBuffer.append(genClassifier.getImportedWildcardInstanceClassName());
    stringBuffer.append(TEXT_80);
    stringBuffer.append(genClassifier.getSafeUncapName());
    stringBuffer.append(TEXT_81);
    stringBuffer.append(diagnostics);
    stringBuffer.append(TEXT_82);
    stringBuffer.append(_Map);
    stringBuffer.append(TEXT_83);
    stringBuffer.append(context);
    stringBuffer.append(TEXT_84);
    if (genClassifier.getAllGenConstraints().isEmpty()) {
    stringBuffer.append(TEXT_85);
    } else if (genClassifier.hasOnlyDefaultConstraints()) {
    stringBuffer.append(TEXT_86);
    if (!((GenClass)genClassifier).isEObjectExtension()){
    stringBuffer.append(TEXT_87);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.EObject"));
    stringBuffer.append(TEXT_88);
    }
    stringBuffer.append(genClassifier.getSafeUncapName());
    stringBuffer.append(TEXT_89);
    stringBuffer.append(diagnostics);
    stringBuffer.append(TEXT_90);
    stringBuffer.append(context);
    stringBuffer.append(TEXT_91);
    } else { boolean first = true;
    for (String constraint : genClassifier.getAllGenConstraints()) {GenClassifier constraintImplementor = genClassifier.getConstraintImplementor(constraint);
  String delegate = constraintImplementor == null || constraintImplementor.getGenPackage() == genPackage ? "" : genPackage.getValidatorPackageUniqueSafeName(constraintImplementor.getGenPackage()) + "Validator.";
  String cast = constraintImplementor == null && genClassifier instanceof GenClass && !((GenClass)genClassifier).isEObjectExtension() ? "(" + genModel.getImportedName("org.eclipse.emf.ecore.EObject") + ")" : "";
  String accessor = constraintImplementor != null && genClassifier instanceof GenDataType && !((GenDataType)genClassifier).isPrimitiveType() && ((GenDataType)constraintImplementor).isPrimitiveType() && genModel.getComplianceLevel().getValue() < GenJDKLevel.JDK50 ? "." + ((GenDataType)constraintImplementor).getPrimitiveValueFunction() + "()" : "";
    if ("NoCircularContainment".equals(constraint)) {
    stringBuffer.append(TEXT_92);
    if (!((GenClass)genClassifier).isEObjectExtension()){
    stringBuffer.append(TEXT_93);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.EObject"));
    stringBuffer.append(TEXT_94);
    }
    stringBuffer.append(genClassifier.getSafeUncapName());
    stringBuffer.append(TEXT_95);
    stringBuffer.append(diagnostics);
    stringBuffer.append(TEXT_96);
    stringBuffer.append(context);
    stringBuffer.append(TEXT_97);
    } else if (first) { first = false;
    stringBuffer.append(TEXT_98);
    stringBuffer.append(result);
    stringBuffer.append(TEXT_99);
    stringBuffer.append(delegate);
    stringBuffer.append(TEXT_100);
    stringBuffer.append(constraintImplementor == null ? "" : constraintImplementor.getName());
    stringBuffer.append(TEXT_101);
    stringBuffer.append(constraint);
    stringBuffer.append(TEXT_102);
    stringBuffer.append(cast);
    stringBuffer.append(genClassifier.getSafeUncapName());
    stringBuffer.append(accessor);
    stringBuffer.append(TEXT_103);
    stringBuffer.append(diagnostics);
    stringBuffer.append(TEXT_104);
    stringBuffer.append(context);
    stringBuffer.append(TEXT_105);
    } else {
    stringBuffer.append(TEXT_106);
    stringBuffer.append(result);
    stringBuffer.append(TEXT_107);
    stringBuffer.append(diagnostics);
    stringBuffer.append(TEXT_108);
    stringBuffer.append(result);
    stringBuffer.append(TEXT_109);
    stringBuffer.append(delegate);
    stringBuffer.append(TEXT_110);
    stringBuffer.append(constraintImplementor == null ? "" : constraintImplementor.getName());
    stringBuffer.append(TEXT_111);
    stringBuffer.append(constraint);
    stringBuffer.append(TEXT_112);
    stringBuffer.append(cast);
    stringBuffer.append(genClassifier.getSafeUncapName());
    stringBuffer.append(accessor);
    stringBuffer.append(TEXT_113);
    stringBuffer.append(diagnostics);
    stringBuffer.append(TEXT_114);
    stringBuffer.append(context);
    stringBuffer.append(TEXT_115);
    }
    }
    stringBuffer.append(TEXT_116);
    stringBuffer.append(result);
    stringBuffer.append(TEXT_117);
    }
    stringBuffer.append(TEXT_118);
    for (String constraint : genClassifier.getGenConstraints())
{GenClassifier constraintDelegate = genClassifier.getConstraintDelegate(constraint);
  String constant = genClassifier.getClassifierID() + "__" + CodeGenUtil.format(constraint, '_', null, false, false).toUpperCase(genClassifier.getGenModel().getLocale());
  String delegate = constraintDelegate == null || constraintDelegate.getGenPackage() == genPackage ? "" : genPackage.getValidatorPackageUniqueSafeName(constraintDelegate.getGenPackage()) + "Validator.";
  String cast = constraintDelegate == null && genClassifier instanceof GenClass && !((GenClass)genClassifier).isEObjectExtension() ? "(" + genModel.getImportedName("org.eclipse.emf.ecore.EObject") + ")" : "";
  String accessor = constraintDelegate != null && genClassifier instanceof GenDataType && !((GenDataType)genClassifier).isPrimitiveType() && ((GenDataType)constraintDelegate).isPrimitiveType()  && genModel.getComplianceLevel().getValue() < GenJDKLevel.JDK50 ? "." + ((GenDataType)constraintDelegate).getPrimitiveValueFunction() + "()" : "";
    if (genClassifier instanceof GenDataType) { GenDataType genDataType = (GenDataType)genClassifier;
    if (constraint.equals("Min") && genDataType.getMinLiteral() != null) {
    stringBuffer.append(TEXT_119);
    stringBuffer.append(genDataType.getName());
    stringBuffer.append(TEXT_120);
    stringBuffer.append(constraint);
    stringBuffer.append(TEXT_121);
    stringBuffer.append(genDataType.getImportedInstanceClassName());
    stringBuffer.append(TEXT_122);
    stringBuffer.append(constant);
    stringBuffer.append(TEXT_123);
    stringBuffer.append(genDataType.getStaticValue(genDataType.getMinLiteral()));
    stringBuffer.append(TEXT_124);
    } else if (constraint.equals("Max") && genDataType.getMaxLiteral() != null) {
    stringBuffer.append(TEXT_125);
    stringBuffer.append(genDataType.getName());
    stringBuffer.append(TEXT_126);
    stringBuffer.append(constraint);
    stringBuffer.append(TEXT_127);
    stringBuffer.append(genDataType.getImportedInstanceClassName());
    stringBuffer.append(TEXT_128);
    stringBuffer.append(constant);
    stringBuffer.append(TEXT_129);
    stringBuffer.append(genDataType.getStaticValue(genDataType.getMaxLiteral()));
    stringBuffer.append(TEXT_130);
    } else if (constraint.equals("TotalDigits") && genDataType.getTotalDigits() != -1 && !"java.math.BigDecimal".equals(genDataType.getQualifiedInstanceClassName())) { String value = "1"; for (int digitCount = genDataType.getTotalDigits(); digitCount > 0; --digitCount) value += "0"; 
    stringBuffer.append(TEXT_131);
    stringBuffer.append(genDataType.getName());
    stringBuffer.append(TEXT_132);
    stringBuffer.append(constraint);
    stringBuffer.append(TEXT_133);
    stringBuffer.append(genDataType.getImportedInstanceClassName());
    stringBuffer.append(TEXT_134);
    stringBuffer.append(constant);
    stringBuffer.append(TEXT_135);
    stringBuffer.append(genDataType.getStaticValue(value));
    stringBuffer.append(TEXT_136);
    stringBuffer.append(genDataType.getName());
    stringBuffer.append(TEXT_137);
    stringBuffer.append(constraint);
    stringBuffer.append(TEXT_138);
    stringBuffer.append(genDataType.getImportedInstanceClassName());
    stringBuffer.append(TEXT_139);
    stringBuffer.append(constant);
    stringBuffer.append(TEXT_140);
    stringBuffer.append(genDataType.getStaticValue("-" + value));
    stringBuffer.append(TEXT_141);
    } else if (constraint.equals("Pattern") && !genDataType.getPatterns().isEmpty()) {
    stringBuffer.append(TEXT_142);
    stringBuffer.append(genDataType.getName());
    stringBuffer.append(TEXT_143);
    stringBuffer.append(constraint);
    stringBuffer.append(TEXT_144);
    stringBuffer.append(constant);
    stringBuffer.append(TEXT_145);
    for (Iterator<List<String>> k = genDataType.getPatterns().iterator(); k.hasNext(); ) { List<String> patternList = k.next();
    stringBuffer.append(TEXT_146);
    for (Iterator<String> p = patternList.iterator(); p.hasNext(); ) { String pattern = p.next();
    stringBuffer.append(TEXT_147);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.xml.type.util.XMLTypeUtil"));
    stringBuffer.append(TEXT_148);
    stringBuffer.append(pattern);
    stringBuffer.append(TEXT_149);
    stringBuffer.append(p.hasNext() ? "," : "");
    }
    stringBuffer.append(TEXT_150);
    stringBuffer.append(k.hasNext() ? "," : "");
    }
    stringBuffer.append(TEXT_151);
    } else if (constraint.equals("Enumeration") && !genDataType.getEnumerationLiterals().isEmpty()) {
    stringBuffer.append(TEXT_152);
    stringBuffer.append(genDataType.getName());
    stringBuffer.append(TEXT_153);
    stringBuffer.append(constraint);
    stringBuffer.append(TEXT_154);
    if (genDataType.isUncheckedCast()) {
    stringBuffer.append(TEXT_155);
    }
    stringBuffer.append(TEXT_156);
    stringBuffer.append(genModel.getImportedName("java.util.Collection"));
    stringBuffer.append(objectArgument);
    stringBuffer.append(TEXT_157);
    stringBuffer.append(constant);
    stringBuffer.append(TEXT_158);
    for (Iterator<String> k = genDataType.getEnumerationLiterals().iterator(); k.hasNext(); ) { String literal = k.next();
    stringBuffer.append(TEXT_159);
    if (genDataType.isPrimitiveType() && genModel.getComplianceLevel().getValue() < GenJDKLevel.JDK50) {
    stringBuffer.append(TEXT_160);
    stringBuffer.append(genDataType.getObjectInstanceClassName());
    stringBuffer.append(TEXT_161);
    }
    stringBuffer.append(genDataType.getStaticValue(literal, false));
    if (genDataType.isPrimitiveType() && genModel.getComplianceLevel().getValue() < GenJDKLevel.JDK50) {
    stringBuffer.append(TEXT_162);
    }
    stringBuffer.append(k.hasNext() ? "," : "");
    }
    stringBuffer.append(TEXT_163);
    }
    }
    if (genClassifier.hasConstraintExpression(constraint)) {
    stringBuffer.append(TEXT_164);
    stringBuffer.append(constraint);
    stringBuffer.append(TEXT_165);
    stringBuffer.append(genClassifier.getFormattedName());
    stringBuffer.append(TEXT_166);
    stringBuffer.append(genModel.getImportedName("java.lang.String"));
    stringBuffer.append(TEXT_167);
    stringBuffer.append(genClassifier.getClassifierID());
    stringBuffer.append(TEXT_168);
    stringBuffer.append(CodeGenUtil.upperName(constraint));
    stringBuffer.append(TEXT_169);
    stringBuffer.append(genClassifier.getConstraintExpression(constraint, "\t\t"));
    stringBuffer.append(TEXT_170);
    stringBuffer.append(genModel.getNonNLS());
    stringBuffer.append(TEXT_171);
    }
    stringBuffer.append(TEXT_172);
    stringBuffer.append(constraint);
    stringBuffer.append(TEXT_173);
    stringBuffer.append(genClassifier.getFormattedName());
    stringBuffer.append(TEXT_174);
    stringBuffer.append(genClassifier.getName());
    stringBuffer.append(TEXT_175);
    stringBuffer.append(constraint);
    stringBuffer.append(TEXT_176);
    stringBuffer.append(genClassifier.getImportedWildcardInstanceClassName());
    stringBuffer.append(TEXT_177);
    stringBuffer.append(genClassifier.getSafeUncapName());
    stringBuffer.append(TEXT_178);
    stringBuffer.append(diagnostics);
    stringBuffer.append(TEXT_179);
    stringBuffer.append(_Map);
    stringBuffer.append(TEXT_180);
    stringBuffer.append(context);
    stringBuffer.append(TEXT_181);
    if (genClassifier instanceof GenDataType) { GenDataType genDataType = (GenDataType)genClassifier;
    if (constraint.equals("ItemType") && genDataType.getItemType() != null) { GenDataType itemType = genDataType.getItemType(); String itemDelegate = itemType.getGenPackage() == genPackage ? "" : genPackage.getValidatorPackageUniqueSafeName(itemType.getGenPackage()) + "Validator.";
    stringBuffer.append(TEXT_182);
    stringBuffer.append(result);
    stringBuffer.append(TEXT_183);
    stringBuffer.append(genModel.getImportedName("java.util.Iterator"));
    stringBuffer.append(singleWildcard);
    stringBuffer.append(TEXT_184);
    stringBuffer.append(genClassifier.getSafeUncapName());
    stringBuffer.append(TEXT_185);
    if (itemType.getGenPackage().hasConstraints()) {
    stringBuffer.append(TEXT_186);
    stringBuffer.append(itemType.getQualifiedClassifierAccessor());
    stringBuffer.append(TEXT_187);
    stringBuffer.append(itemDelegate);
    stringBuffer.append(TEXT_188);
    stringBuffer.append(itemType.getName());
    stringBuffer.append(TEXT_189);
    if (itemType.isPrimitiveType() && genModel.getComplianceLevel().getValue() < GenJDKLevel.JDK50) {
    stringBuffer.append(TEXT_190);
    }
    if (!itemType.isObjectType()) {
    stringBuffer.append(TEXT_191);
    stringBuffer.append(itemType.getObjectInstanceClassName());
    stringBuffer.append(TEXT_192);
    }
    stringBuffer.append(item);
    if (itemType.isPrimitiveType() && genModel.getComplianceLevel().getValue() < GenJDKLevel.JDK50) {
    stringBuffer.append(TEXT_193);
    stringBuffer.append(itemType.getPrimitiveValueFunction());
    stringBuffer.append(TEXT_194);
    }
    stringBuffer.append(TEXT_195);
    stringBuffer.append(diagnostics);
    stringBuffer.append(TEXT_196);
    stringBuffer.append(context);
    stringBuffer.append(TEXT_197);
    } else {
    stringBuffer.append(TEXT_198);
    stringBuffer.append(itemType.getQualifiedClassifierAccessor());
    stringBuffer.append(TEXT_199);
    }
    stringBuffer.append(TEXT_200);
    stringBuffer.append(itemType.getQualifiedClassifierAccessor());
    stringBuffer.append(TEXT_201);
    stringBuffer.append(diagnostics);
    stringBuffer.append(TEXT_202);
    stringBuffer.append(context);
    stringBuffer.append(TEXT_203);
    } else if (constraint.equals("MemberTypes") && !genDataType.getMemberTypes().isEmpty()) {
    stringBuffer.append(TEXT_204);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.util.BasicDiagnostic"));
    stringBuffer.append(TEXT_205);
    for (ListIterator<GenDataType> k = genDataType.getMemberTypes().listIterator(); k.hasNext(); ) { GenDataType memberType = k.next(); String memberDelegate = memberType.getGenPackage() == genPackage ? "" : genPackage.getValidatorPackageUniqueSafeName(memberType.getGenPackage()) + "Validator.";
    if (genDataType.isPrimitiveType()) {
    stringBuffer.append(TEXT_206);
    stringBuffer.append(memberDelegate);
    stringBuffer.append(TEXT_207);
    stringBuffer.append(memberType.getName());
    stringBuffer.append(TEXT_208);
    stringBuffer.append(genClassifier.getSafeUncapName());
    stringBuffer.append(TEXT_209);
    stringBuffer.append(context);
    stringBuffer.append(TEXT_210);
    } else {
    stringBuffer.append(TEXT_211);
    stringBuffer.append(memberType.getQualifiedClassifierAccessor());
    stringBuffer.append(TEXT_212);
    stringBuffer.append(genClassifier.getSafeUncapName());
    stringBuffer.append(TEXT_213);
    if (memberType.getGenPackage().hasConstraints()) {
    stringBuffer.append(TEXT_214);
    stringBuffer.append(memberDelegate);
    stringBuffer.append(TEXT_215);
    stringBuffer.append(memberType.getName());
    stringBuffer.append(TEXT_216);
    if (memberType.isPrimitiveType() && genModel.getComplianceLevel().getValue() < GenJDKLevel.JDK50) {
    stringBuffer.append(TEXT_217);
    }
    if (!memberType.isObjectType() && !memberType.getQualifiedInstanceClassName().equals(genDataType.getQualifiedInstanceClassName())) {
    stringBuffer.append(TEXT_218);
    stringBuffer.append(memberType.getImportedWildcardObjectInstanceClassName());
    stringBuffer.append(TEXT_219);
    }
    stringBuffer.append(genClassifier.getSafeUncapName());
    if (memberType.isPrimitiveType() && genModel.getComplianceLevel().getValue() < GenJDKLevel.JDK50) {
    stringBuffer.append(TEXT_220);
    stringBuffer.append(memberType.getPrimitiveValueFunction());
    stringBuffer.append(TEXT_221);
    }
    stringBuffer.append(TEXT_222);
    stringBuffer.append(context);
    stringBuffer.append(TEXT_223);
    }
    stringBuffer.append(TEXT_224);
    }
    }
    if (genModel.useGenerics()) {
    stringBuffer.append(TEXT_225);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.util.Diagnostic"));
    stringBuffer.append(TEXT_226);
    } else {
    stringBuffer.append(TEXT_227);
    stringBuffer.append(genModel.getImportedName("java.util.List"));
    stringBuffer.append(TEXT_228);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.util.Diagnostic"));
    stringBuffer.append(TEXT_229);
    }
    stringBuffer.append(TEXT_230);
    for (ListIterator<GenDataType> k = genDataType.getMemberTypes().listIterator(); k.hasNext(); ) { GenDataType memberType = k.next(); String memberDelegate = memberType.getGenPackage() == genPackage ? "" : genPackage.getValidatorPackageUniqueSafeName(memberType.getGenPackage()) + "Validator.";
    if (genDataType.isPrimitiveType()) {
    stringBuffer.append(TEXT_231);
    stringBuffer.append(memberDelegate);
    stringBuffer.append(TEXT_232);
    stringBuffer.append(memberType.getName());
    stringBuffer.append(TEXT_233);
    stringBuffer.append(genClassifier.getSafeUncapName());
    stringBuffer.append(TEXT_234);
    stringBuffer.append(context);
    stringBuffer.append(TEXT_235);
    } else {
    stringBuffer.append(TEXT_236);
    stringBuffer.append(memberType.getQualifiedClassifierAccessor());
    stringBuffer.append(TEXT_237);
    stringBuffer.append(genClassifier.getSafeUncapName());
    stringBuffer.append(TEXT_238);
    if (memberType.getGenPackage().hasConstraints()) {
    stringBuffer.append(TEXT_239);
    stringBuffer.append(memberDelegate);
    stringBuffer.append(TEXT_240);
    stringBuffer.append(memberType.getName());
    stringBuffer.append(TEXT_241);
    if (memberType.isPrimitiveType() && genModel.getComplianceLevel().getValue() < GenJDKLevel.JDK50) {
    stringBuffer.append(TEXT_242);
    }
    if (!memberType.isObjectType() && !memberType.getQualifiedInstanceClassName().equals(genDataType.getQualifiedInstanceClassName())) {
    stringBuffer.append(TEXT_243);
    stringBuffer.append(memberType.getImportedWildcardObjectInstanceClassName());
    stringBuffer.append(TEXT_244);
    }
    stringBuffer.append(genClassifier.getSafeUncapName());
    if (memberType.isPrimitiveType() && genModel.getComplianceLevel().getValue() < GenJDKLevel.JDK50) {
    stringBuffer.append(TEXT_245);
    stringBuffer.append(memberType.getPrimitiveValueFunction());
    stringBuffer.append(TEXT_246);
    }
    stringBuffer.append(TEXT_247);
    stringBuffer.append(context);
    stringBuffer.append(TEXT_248);
    }
    stringBuffer.append(TEXT_249);
    }
    }
    stringBuffer.append(TEXT_250);
    } else if (constraint.equals("Pattern") && !genDataType.getPatterns().isEmpty()) {
    stringBuffer.append(TEXT_251);
    stringBuffer.append(genDataType.getQualifiedClassifierAccessor());
    stringBuffer.append(TEXT_252);
    if (genDataType.isPrimitiveType() && genModel.getComplianceLevel().getValue() < GenJDKLevel.JDK50) {
    stringBuffer.append(TEXT_253);
    stringBuffer.append(genDataType.getObjectInstanceClassName());
    stringBuffer.append(TEXT_254);
    }
    stringBuffer.append(genDataType.getSafeUncapName());
    if (genDataType.isPrimitiveType() && genModel.getComplianceLevel().getValue() < GenJDKLevel.JDK50) {
    stringBuffer.append(TEXT_255);
    }
    stringBuffer.append(TEXT_256);
    stringBuffer.append(constant);
    stringBuffer.append(TEXT_257);
    stringBuffer.append(diagnostics);
    stringBuffer.append(TEXT_258);
    stringBuffer.append(context);
    stringBuffer.append(TEXT_259);
    } else if (constraint.equals("Enumeration") && !genDataType.getEnumerationLiterals().isEmpty()) { String variable = genDataType.getSafeUncapName();
    if (genDataType.isPrimitiveType() && genModel.getComplianceLevel().getValue() < GenJDKLevel.JDK50) { variable = variable + "Object";
    stringBuffer.append(TEXT_260);
    stringBuffer.append(genDataType.getObjectInstanceClassName());
    stringBuffer.append(TEXT_261);
    stringBuffer.append(variable);
    stringBuffer.append(TEXT_262);
    stringBuffer.append(genDataType.getObjectInstanceClassName());
    stringBuffer.append(TEXT_263);
    stringBuffer.append(genDataType.getSafeUncapName());
    stringBuffer.append(TEXT_264);
    }
    stringBuffer.append(TEXT_265);
    stringBuffer.append(result);
    stringBuffer.append(TEXT_266);
    stringBuffer.append(constant);
    stringBuffer.append(TEXT_267);
    stringBuffer.append(variable);
    stringBuffer.append(TEXT_268);
    stringBuffer.append(result);
    stringBuffer.append(TEXT_269);
    stringBuffer.append(diagnostics);
    stringBuffer.append(TEXT_270);
    stringBuffer.append(genDataType.getQualifiedClassifierAccessor());
    stringBuffer.append(TEXT_271);
    stringBuffer.append(variable);
    stringBuffer.append(TEXT_272);
    stringBuffer.append(constant);
    stringBuffer.append(TEXT_273);
    stringBuffer.append(diagnostics);
    stringBuffer.append(TEXT_274);
    stringBuffer.append(context);
    stringBuffer.append(TEXT_275);
    stringBuffer.append(result);
    stringBuffer.append(TEXT_276);
    } else if (constraint.equals("Min") && genDataType.getMinLiteral() != null) {
    if (genDataType.isPrimitiveType()) {
    stringBuffer.append(TEXT_277);
    stringBuffer.append(result);
    stringBuffer.append(TEXT_278);
    stringBuffer.append(genDataType.getSafeUncapName());
    stringBuffer.append(TEXT_279);
    stringBuffer.append(genDataType.isMinInclusive() ? ">=" : ">");
    stringBuffer.append(TEXT_280);
    stringBuffer.append(constant);
    stringBuffer.append(TEXT_281);
    stringBuffer.append(result);
    stringBuffer.append(TEXT_282);
    stringBuffer.append(diagnostics);
    stringBuffer.append(TEXT_283);
    if (genModel.getComplianceLevel().getValue() < GenJDKLevel.JDK50) {
    stringBuffer.append(TEXT_284);
    stringBuffer.append(genDataType.getQualifiedClassifierAccessor());
    stringBuffer.append(TEXT_285);
    stringBuffer.append(genDataType.getObjectInstanceClassName());
    stringBuffer.append(TEXT_286);
    stringBuffer.append(genDataType.getSafeUncapName());
    stringBuffer.append(TEXT_287);
    stringBuffer.append(genDataType.getObjectInstanceClassName());
    stringBuffer.append(TEXT_288);
    stringBuffer.append(constant);
    stringBuffer.append(TEXT_289);
    stringBuffer.append(genDataType.isMinInclusive() ? "true" : "false");
    stringBuffer.append(TEXT_290);
    stringBuffer.append(diagnostics);
    stringBuffer.append(TEXT_291);
    stringBuffer.append(context);
    stringBuffer.append(TEXT_292);
    } else {
    stringBuffer.append(TEXT_293);
    stringBuffer.append(genDataType.getQualifiedClassifierAccessor());
    stringBuffer.append(TEXT_294);
    stringBuffer.append(genDataType.getSafeUncapName());
    stringBuffer.append(TEXT_295);
    stringBuffer.append(constant);
    stringBuffer.append(TEXT_296);
    stringBuffer.append(genDataType.isMinInclusive() ? "true" : "false");
    stringBuffer.append(TEXT_297);
    stringBuffer.append(diagnostics);
    stringBuffer.append(TEXT_298);
    stringBuffer.append(context);
    stringBuffer.append(TEXT_299);
    }
    stringBuffer.append(TEXT_300);
    stringBuffer.append(result);
    stringBuffer.append(TEXT_301);
    } else { String comparison = "comparison".equals(genClassifier.getSafeUncapName()) ? "theComparison" : "comparison";
    if (genDataType.isXMLCalendar()) {
    stringBuffer.append(TEXT_302);
    stringBuffer.append(comparison);
    stringBuffer.append(TEXT_303);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.xml.type.util.XMLTypeUtil"));
    stringBuffer.append(TEXT_304);
    stringBuffer.append(genDataType.getSafeUncapName());
    stringBuffer.append(TEXT_305);
    stringBuffer.append(constant);
    stringBuffer.append(TEXT_306);
    stringBuffer.append(result);
    stringBuffer.append(TEXT_307);
    if (genDataType.isMinInclusive()) {
    stringBuffer.append(comparison);
    stringBuffer.append(TEXT_308);
    }
    stringBuffer.append(comparison);
    stringBuffer.append(TEXT_309);
    } else if (genDataType.isXMLDuration()) {
    stringBuffer.append(TEXT_310);
    stringBuffer.append(comparison);
    stringBuffer.append(TEXT_311);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.xml.type.util.XMLTypeUtil"));
    stringBuffer.append(TEXT_312);
    stringBuffer.append(genDataType.getSafeUncapName());
    stringBuffer.append(TEXT_313);
    stringBuffer.append(constant);
    stringBuffer.append(TEXT_314);
    stringBuffer.append(result);
    stringBuffer.append(TEXT_315);
    if (genDataType.isMinInclusive()) {
    stringBuffer.append(comparison);
    stringBuffer.append(TEXT_316);
    }
    stringBuffer.append(comparison);
    stringBuffer.append(TEXT_317);
    } else {
    stringBuffer.append(TEXT_318);
    stringBuffer.append(result);
    stringBuffer.append(TEXT_319);
    stringBuffer.append(genDataType.getSafeUncapName());
    stringBuffer.append(TEXT_320);
    stringBuffer.append(constant);
    stringBuffer.append(TEXT_321);
    stringBuffer.append(genDataType.isMinInclusive() ? ">=" : ">");
    stringBuffer.append(TEXT_322);
    }
    stringBuffer.append(TEXT_323);
    stringBuffer.append(result);
    stringBuffer.append(TEXT_324);
    stringBuffer.append(diagnostics);
    stringBuffer.append(TEXT_325);
    stringBuffer.append(genDataType.getQualifiedClassifierAccessor());
    stringBuffer.append(TEXT_326);
    stringBuffer.append(genDataType.getSafeUncapName());
    stringBuffer.append(TEXT_327);
    stringBuffer.append(constant);
    stringBuffer.append(TEXT_328);
    stringBuffer.append(genDataType.isMinInclusive() ? "true" : "false");
    stringBuffer.append(TEXT_329);
    stringBuffer.append(diagnostics);
    stringBuffer.append(TEXT_330);
    stringBuffer.append(context);
    stringBuffer.append(TEXT_331);
    stringBuffer.append(result);
    stringBuffer.append(TEXT_332);
    }
    } else if (constraint.equals("Max") && genDataType.getMaxLiteral() != null) {
    if (genDataType.isPrimitiveType()) {
    stringBuffer.append(TEXT_333);
    stringBuffer.append(result);
    stringBuffer.append(TEXT_334);
    stringBuffer.append(genDataType.getSafeUncapName());
    stringBuffer.append(TEXT_335);
    stringBuffer.append(genDataType.isMaxInclusive() ? "<=" : "<");
    stringBuffer.append(TEXT_336);
    stringBuffer.append(constant);
    stringBuffer.append(TEXT_337);
    stringBuffer.append(result);
    stringBuffer.append(TEXT_338);
    stringBuffer.append(diagnostics);
    stringBuffer.append(TEXT_339);
    if (genModel.getComplianceLevel().getValue() < GenJDKLevel.JDK50) {
    stringBuffer.append(TEXT_340);
    stringBuffer.append(genDataType.getQualifiedClassifierAccessor());
    stringBuffer.append(TEXT_341);
    stringBuffer.append(genDataType.getObjectInstanceClassName());
    stringBuffer.append(TEXT_342);
    stringBuffer.append(genDataType.getSafeUncapName());
    stringBuffer.append(TEXT_343);
    stringBuffer.append(genDataType.getObjectInstanceClassName());
    stringBuffer.append(TEXT_344);
    stringBuffer.append(constant);
    stringBuffer.append(TEXT_345);
    stringBuffer.append(genDataType.isMaxInclusive() ? "true" : "false");
    stringBuffer.append(TEXT_346);
    stringBuffer.append(diagnostics);
    stringBuffer.append(TEXT_347);
    stringBuffer.append(context);
    stringBuffer.append(TEXT_348);
    } else {
    stringBuffer.append(TEXT_349);
    stringBuffer.append(genDataType.getQualifiedClassifierAccessor());
    stringBuffer.append(TEXT_350);
    stringBuffer.append(genDataType.getSafeUncapName());
    stringBuffer.append(TEXT_351);
    stringBuffer.append(constant);
    stringBuffer.append(TEXT_352);
    stringBuffer.append(genDataType.isMaxInclusive() ? "true" : "false");
    stringBuffer.append(TEXT_353);
    stringBuffer.append(diagnostics);
    stringBuffer.append(TEXT_354);
    stringBuffer.append(context);
    stringBuffer.append(TEXT_355);
    }
    stringBuffer.append(TEXT_356);
    stringBuffer.append(result);
    stringBuffer.append(TEXT_357);
    } else { String comparison = "comparison".equals(genClassifier.getSafeUncapName()) ? "theComparison" : "comparison";
    if (genDataType.isXMLCalendar()) {
    stringBuffer.append(TEXT_358);
    stringBuffer.append(comparison);
    stringBuffer.append(TEXT_359);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.xml.type.util.XMLTypeUtil"));
    stringBuffer.append(TEXT_360);
    stringBuffer.append(genDataType.getSafeUncapName());
    stringBuffer.append(TEXT_361);
    stringBuffer.append(constant);
    stringBuffer.append(TEXT_362);
    stringBuffer.append(result);
    stringBuffer.append(TEXT_363);
    if (genDataType.isMaxInclusive()) {
    stringBuffer.append(comparison);
    stringBuffer.append(TEXT_364);
    }
    stringBuffer.append(comparison);
    stringBuffer.append(TEXT_365);
    } else if (genDataType.isXMLDuration()) {
    stringBuffer.append(TEXT_366);
    stringBuffer.append(comparison);
    stringBuffer.append(TEXT_367);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.xml.type.util.XMLTypeUtil"));
    stringBuffer.append(TEXT_368);
    stringBuffer.append(genDataType.getSafeUncapName());
    stringBuffer.append(TEXT_369);
    stringBuffer.append(constant);
    stringBuffer.append(TEXT_370);
    stringBuffer.append(result);
    stringBuffer.append(TEXT_371);
    if (genDataType.isMaxInclusive()) {
    stringBuffer.append(comparison);
    stringBuffer.append(TEXT_372);
    }
    stringBuffer.append(comparison);
    stringBuffer.append(TEXT_373);
    } else {
    stringBuffer.append(TEXT_374);
    stringBuffer.append(result);
    stringBuffer.append(TEXT_375);
    stringBuffer.append(genDataType.getSafeUncapName());
    stringBuffer.append(TEXT_376);
    stringBuffer.append(constant);
    stringBuffer.append(TEXT_377);
    stringBuffer.append(genDataType.isMaxInclusive() ? "<=" : "<");
    stringBuffer.append(TEXT_378);
    }
    stringBuffer.append(TEXT_379);
    stringBuffer.append(result);
    stringBuffer.append(TEXT_380);
    stringBuffer.append(diagnostics);
    stringBuffer.append(TEXT_381);
    stringBuffer.append(genDataType.getQualifiedClassifierAccessor());
    stringBuffer.append(TEXT_382);
    stringBuffer.append(genDataType.getSafeUncapName());
    stringBuffer.append(TEXT_383);
    stringBuffer.append(constant);
    stringBuffer.append(TEXT_384);
    stringBuffer.append(genDataType.isMaxInclusive() ? "true" : "false");
    stringBuffer.append(TEXT_385);
    stringBuffer.append(diagnostics);
    stringBuffer.append(TEXT_386);
    stringBuffer.append(context);
    stringBuffer.append(TEXT_387);
    stringBuffer.append(result);
    stringBuffer.append(TEXT_388);
    }
    } else if (constraint.equals("MinLength") && genDataType.getMinLength() != -1) {
    stringBuffer.append(TEXT_389);
    stringBuffer.append(genDataType.getSafeUncapName());
    stringBuffer.append(TEXT_390);
    stringBuffer.append(genDataType.getLengthAccessorFunction());
    stringBuffer.append(TEXT_391);
    stringBuffer.append(result);
    stringBuffer.append(TEXT_392);
    stringBuffer.append(genDataType.getMinLength());
    stringBuffer.append(TEXT_393);
    stringBuffer.append(result);
    stringBuffer.append(TEXT_394);
    stringBuffer.append(diagnostics);
    stringBuffer.append(TEXT_395);
    stringBuffer.append(genDataType.getQualifiedClassifierAccessor());
    stringBuffer.append(TEXT_396);
    stringBuffer.append(genDataType.getSafeUncapName());
    stringBuffer.append(TEXT_397);
    stringBuffer.append(genDataType.getMinLength());
    stringBuffer.append(TEXT_398);
    stringBuffer.append(diagnostics);
    stringBuffer.append(TEXT_399);
    stringBuffer.append(context);
    stringBuffer.append(TEXT_400);
    stringBuffer.append(result);
    stringBuffer.append(TEXT_401);
    } else if (constraint.equals("MaxLength") && genDataType.getMaxLength() != -1) {
    stringBuffer.append(TEXT_402);
    stringBuffer.append(genDataType.getSafeUncapName());
    stringBuffer.append(TEXT_403);
    stringBuffer.append(genDataType.getLengthAccessorFunction());
    stringBuffer.append(TEXT_404);
    stringBuffer.append(result);
    stringBuffer.append(TEXT_405);
    stringBuffer.append(genDataType.getMaxLength());
    stringBuffer.append(TEXT_406);
    stringBuffer.append(result);
    stringBuffer.append(TEXT_407);
    stringBuffer.append(diagnostics);
    stringBuffer.append(TEXT_408);
    stringBuffer.append(genDataType.getQualifiedClassifierAccessor());
    stringBuffer.append(TEXT_409);
    stringBuffer.append(genDataType.getSafeUncapName());
    stringBuffer.append(TEXT_410);
    stringBuffer.append(genDataType.getMaxLength());
    stringBuffer.append(TEXT_411);
    stringBuffer.append(diagnostics);
    stringBuffer.append(TEXT_412);
    stringBuffer.append(context);
    stringBuffer.append(TEXT_413);
    stringBuffer.append(result);
    stringBuffer.append(TEXT_414);
    } else if (constraint.equals("TotalDigits") && genDataType.getTotalDigits() != -1) {
    if (genDataType.isPrimitiveType()) {
    stringBuffer.append(TEXT_415);
    stringBuffer.append(result);
    stringBuffer.append(TEXT_416);
    stringBuffer.append(genDataType.getSafeUncapName());
    stringBuffer.append(TEXT_417);
    stringBuffer.append(constant);
    stringBuffer.append(TEXT_418);
    stringBuffer.append(genDataType.getSafeUncapName());
    stringBuffer.append(TEXT_419);
    stringBuffer.append(constant);
    stringBuffer.append(TEXT_420);
    stringBuffer.append(result);
    stringBuffer.append(TEXT_421);
    stringBuffer.append(diagnostics);
    stringBuffer.append(TEXT_422);
    if (genModel.getComplianceLevel().getValue() < GenJDKLevel.JDK50) {
    stringBuffer.append(TEXT_423);
    stringBuffer.append(genDataType.getQualifiedClassifierAccessor());
    stringBuffer.append(TEXT_424);
    stringBuffer.append(genDataType.getObjectInstanceClassName());
    stringBuffer.append(TEXT_425);
    stringBuffer.append(genDataType.getSafeUncapName());
    stringBuffer.append(TEXT_426);
    stringBuffer.append(genDataType.getTotalDigits());
    stringBuffer.append(TEXT_427);
    stringBuffer.append(diagnostics);
    stringBuffer.append(TEXT_428);
    stringBuffer.append(context);
    stringBuffer.append(TEXT_429);
    } else {
    stringBuffer.append(TEXT_430);
    stringBuffer.append(genDataType.getQualifiedClassifierAccessor());
    stringBuffer.append(TEXT_431);
    stringBuffer.append(genDataType.getSafeUncapName());
    stringBuffer.append(TEXT_432);
    stringBuffer.append(genDataType.getTotalDigits());
    stringBuffer.append(TEXT_433);
    stringBuffer.append(diagnostics);
    stringBuffer.append(TEXT_434);
    stringBuffer.append(context);
    stringBuffer.append(TEXT_435);
    }
    } else if ("java.math.BigDecimal".equals(genDataType.getQualifiedInstanceClassName())) {
    stringBuffer.append(TEXT_436);
    stringBuffer.append(genDataType.getSafeUncapName());
    stringBuffer.append(TEXT_437);
    stringBuffer.append(genDataType.getSafeUncapName());
    stringBuffer.append(TEXT_438);
    stringBuffer.append(genDataType.getSafeUncapName());
    stringBuffer.append(TEXT_439);
    stringBuffer.append(result);
    stringBuffer.append(TEXT_440);
    stringBuffer.append(genDataType.getTotalDigits());
    stringBuffer.append(TEXT_441);
    stringBuffer.append(result);
    stringBuffer.append(TEXT_442);
    stringBuffer.append(diagnostics);
    stringBuffer.append(TEXT_443);
    stringBuffer.append(genDataType.getQualifiedClassifierAccessor());
    stringBuffer.append(TEXT_444);
    stringBuffer.append(genDataType.getSafeUncapName());
    stringBuffer.append(TEXT_445);
    stringBuffer.append(genDataType.getTotalDigits());
    stringBuffer.append(TEXT_446);
    stringBuffer.append(diagnostics);
    stringBuffer.append(TEXT_447);
    stringBuffer.append(context);
    stringBuffer.append(TEXT_448);
    } else {
    stringBuffer.append(TEXT_449);
    stringBuffer.append(result);
    stringBuffer.append(TEXT_450);
    stringBuffer.append(genDataType.getSafeUncapName());
    stringBuffer.append(TEXT_451);
    stringBuffer.append(constant);
    stringBuffer.append(TEXT_452);
    stringBuffer.append(genDataType.getSafeUncapName());
    stringBuffer.append(TEXT_453);
    stringBuffer.append(constant);
    stringBuffer.append(TEXT_454);
    stringBuffer.append(result);
    stringBuffer.append(TEXT_455);
    stringBuffer.append(diagnostics);
    stringBuffer.append(TEXT_456);
    stringBuffer.append(genDataType.getQualifiedClassifierAccessor());
    stringBuffer.append(TEXT_457);
    stringBuffer.append(genDataType.getSafeUncapName());
    stringBuffer.append(TEXT_458);
    stringBuffer.append(genDataType.getTotalDigits());
    stringBuffer.append(TEXT_459);
    stringBuffer.append(diagnostics);
    stringBuffer.append(TEXT_460);
    stringBuffer.append(context);
    stringBuffer.append(TEXT_461);
    }
    stringBuffer.append(TEXT_462);
    stringBuffer.append(result);
    stringBuffer.append(TEXT_463);
    } else if (constraint.equals("FractionDigits") && genDataType.getFractionDigits() != -1 && "java.math.BigDecimal".equals(genDataType.getQualifiedInstanceClassName())) {
    stringBuffer.append(TEXT_464);
    stringBuffer.append(result);
    stringBuffer.append(TEXT_465);
    stringBuffer.append(genDataType.getSafeUncapName());
    stringBuffer.append(TEXT_466);
    stringBuffer.append(genDataType.getFractionDigits());
    stringBuffer.append(TEXT_467);
    stringBuffer.append(result);
    stringBuffer.append(TEXT_468);
    stringBuffer.append(diagnostics);
    stringBuffer.append(TEXT_469);
    stringBuffer.append(genDataType.getQualifiedClassifierAccessor());
    stringBuffer.append(TEXT_470);
    stringBuffer.append(genDataType.getSafeUncapName());
    stringBuffer.append(TEXT_471);
    stringBuffer.append(genDataType.getFractionDigits());
    stringBuffer.append(TEXT_472);
    stringBuffer.append(diagnostics);
    stringBuffer.append(TEXT_473);
    stringBuffer.append(context);
    stringBuffer.append(TEXT_474);
    stringBuffer.append(result);
    stringBuffer.append(TEXT_475);
    } else if (constraintDelegate != null) {
    stringBuffer.append(TEXT_476);
    stringBuffer.append(diagnostics);
    stringBuffer.append(TEXT_477);
    stringBuffer.append(diagnostics);
    stringBuffer.append(TEXT_478);
    if (genModel.getRuntimeVersion().getValue() >= GenRuntimeVersion.EMF24_VALUE) {
    stringBuffer.append(TEXT_479);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.util.Diagnostic"));
    stringBuffer.append(TEXT_480);
    stringBuffer.append(genModel.getNonNLS());
    stringBuffer.append(TEXT_481);
    stringBuffer.append(constraint);
    stringBuffer.append(TEXT_482);
    stringBuffer.append(genDataType.getQualifiedClassifierAccessor());
    stringBuffer.append(TEXT_483);
    stringBuffer.append(genClassifier.getSafeUncapName());
    stringBuffer.append(TEXT_484);
    stringBuffer.append(context);
    stringBuffer.append(TEXT_485);
    stringBuffer.append(genModel.getNonNLS());
    stringBuffer.append(TEXT_486);
    stringBuffer.append(genClassifier.getSafeUncapName());
    stringBuffer.append(TEXT_487);
    } else {
    stringBuffer.append(TEXT_488);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.util.BasicDiagnostic"));
    stringBuffer.append(TEXT_489);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.util.Diagnostic"));
    stringBuffer.append(TEXT_490);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.plugin.EcorePlugin"));
    stringBuffer.append(TEXT_491);
    stringBuffer.append(constraint);
    stringBuffer.append(TEXT_492);
    stringBuffer.append(genDataType.getQualifiedClassifierAccessor());
    stringBuffer.append(TEXT_493);
    stringBuffer.append(genClassifier.getSafeUncapName());
    stringBuffer.append(TEXT_494);
    stringBuffer.append(context);
    stringBuffer.append(TEXT_495);
    stringBuffer.append(genModel.getNonNLS());
    stringBuffer.append(genModel.getNonNLS(2));
    stringBuffer.append(TEXT_496);
    stringBuffer.append(genClassifier.getSafeUncapName());
    stringBuffer.append(TEXT_497);
    }
    stringBuffer.append(TEXT_498);
    stringBuffer.append(delegate);
    stringBuffer.append(TEXT_499);
    stringBuffer.append(constraintDelegate.getName());
    stringBuffer.append(TEXT_500);
    stringBuffer.append(constraint);
    stringBuffer.append(TEXT_501);
    stringBuffer.append(genClassifier.getSafeUncapName());
    stringBuffer.append(accessor);
    stringBuffer.append(TEXT_502);
    stringBuffer.append(diagnostics);
    stringBuffer.append(TEXT_503);
    stringBuffer.append(context);
    stringBuffer.append(TEXT_504);
    } else {
    if (genClassifier.hasConstraintExpression(constraint)) {
    stringBuffer.append(TEXT_505);
    stringBuffer.append(genClassifier.getQualifiedClassifierAccessor());
    stringBuffer.append(TEXT_506);
    stringBuffer.append(genClassifier.getSafeUncapName());
    stringBuffer.append(TEXT_507);
    stringBuffer.append(diagnostics);
    stringBuffer.append(TEXT_508);
    stringBuffer.append(context);
    stringBuffer.append(TEXT_509);
    stringBuffer.append(genClassifier.getValidationDelegate(constraint));
    stringBuffer.append(TEXT_510);
    stringBuffer.append(genModel.getNonNLS());
    stringBuffer.append(TEXT_511);
    stringBuffer.append(constraint);
    stringBuffer.append(TEXT_512);
    stringBuffer.append(genModel.getNonNLS());
    stringBuffer.append(TEXT_513);
    stringBuffer.append(genClassifier.getClassifierID());
    stringBuffer.append(TEXT_514);
    stringBuffer.append(CodeGenUtil.upperName(constraint));
    stringBuffer.append(TEXT_515);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.util.Diagnostic"));
    stringBuffer.append(TEXT_516);
    } else {
    stringBuffer.append(TEXT_517);
    stringBuffer.append(diagnostics);
    stringBuffer.append(TEXT_518);
    stringBuffer.append(diagnostics);
    stringBuffer.append(TEXT_519);
    if (genModel.getRuntimeVersion().getValue() >= GenRuntimeVersion.EMF24_VALUE) {
    stringBuffer.append(TEXT_520);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.util.Diagnostic"));
    stringBuffer.append(TEXT_521);
    stringBuffer.append(genModel.getNonNLS());
    stringBuffer.append(TEXT_522);
    stringBuffer.append(constraint);
    stringBuffer.append(TEXT_523);
    stringBuffer.append(genDataType.getQualifiedClassifierAccessor());
    stringBuffer.append(TEXT_524);
    stringBuffer.append(genClassifier.getSafeUncapName());
    stringBuffer.append(TEXT_525);
    stringBuffer.append(context);
    stringBuffer.append(TEXT_526);
    stringBuffer.append(genModel.getNonNLS());
    stringBuffer.append(TEXT_527);
    stringBuffer.append(genClassifier.getSafeUncapName());
    stringBuffer.append(TEXT_528);
    } else {
    stringBuffer.append(TEXT_529);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.util.BasicDiagnostic"));
    stringBuffer.append(TEXT_530);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.util.Diagnostic"));
    stringBuffer.append(TEXT_531);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.plugin.EcorePlugin"));
    stringBuffer.append(TEXT_532);
    stringBuffer.append(constraint);
    stringBuffer.append(TEXT_533);
    stringBuffer.append(genDataType.getQualifiedClassifierAccessor());
    stringBuffer.append(TEXT_534);
    stringBuffer.append(genClassifier.getSafeUncapName());
    stringBuffer.append(TEXT_535);
    stringBuffer.append(context);
    stringBuffer.append(TEXT_536);
    stringBuffer.append(genModel.getNonNLS());
    stringBuffer.append(genModel.getNonNLS(2));
    stringBuffer.append(TEXT_537);
    stringBuffer.append(genClassifier.getSafeUncapName());
    stringBuffer.append(TEXT_538);
    }
    stringBuffer.append(TEXT_539);
    }
    }
    } else { GenOperation genOperation = ((GenClass)genClassifier).getInvariantOperation(constraint); if (genOperation != null) {
    stringBuffer.append(TEXT_540);
    stringBuffer.append(genClassifier.getSafeUncapName());
    stringBuffer.append(TEXT_541);
    stringBuffer.append(constraint);
    stringBuffer.append(TEXT_542);
    stringBuffer.append(diagnostics);
    stringBuffer.append(TEXT_543);
    stringBuffer.append(context);
    stringBuffer.append(TEXT_544);
    } else if (constraintDelegate != null) {
    stringBuffer.append(TEXT_545);
    stringBuffer.append(diagnostics);
    stringBuffer.append(TEXT_546);
    stringBuffer.append(diagnostics);
    stringBuffer.append(TEXT_547);
    if (genModel.getRuntimeVersion().getValue() >= GenRuntimeVersion.EMF24_VALUE) {
    stringBuffer.append(TEXT_548);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.util.Diagnostic"));
    stringBuffer.append(TEXT_549);
    stringBuffer.append(genModel.getNonNLS());
    stringBuffer.append(TEXT_550);
    stringBuffer.append(constraint);
    stringBuffer.append(TEXT_551);
    stringBuffer.append(genClassifier.getSafeUncapName());
    stringBuffer.append(TEXT_552);
    stringBuffer.append(context);
    stringBuffer.append(TEXT_553);
    stringBuffer.append(genModel.getNonNLS());
    stringBuffer.append(TEXT_554);
    stringBuffer.append(genClassifier.getSafeUncapName());
    stringBuffer.append(TEXT_555);
    } else {
    stringBuffer.append(TEXT_556);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.util.BasicDiagnostic"));
    stringBuffer.append(TEXT_557);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.util.Diagnostic"));
    stringBuffer.append(TEXT_558);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.plugin.EcorePlugin"));
    stringBuffer.append(TEXT_559);
    stringBuffer.append(constraint);
    stringBuffer.append(TEXT_560);
    stringBuffer.append(genClassifier.getSafeUncapName());
    stringBuffer.append(TEXT_561);
    stringBuffer.append(context);
    stringBuffer.append(TEXT_562);
    stringBuffer.append(genModel.getNonNLS());
    stringBuffer.append(genModel.getNonNLS(2));
    stringBuffer.append(TEXT_563);
    stringBuffer.append(genClassifier.getSafeUncapName());
    stringBuffer.append(TEXT_564);
    }
    stringBuffer.append(TEXT_565);
    stringBuffer.append(delegate);
    stringBuffer.append(TEXT_566);
    stringBuffer.append(constraintDelegate.getName());
    stringBuffer.append(TEXT_567);
    stringBuffer.append(constraint);
    stringBuffer.append(TEXT_568);
    stringBuffer.append(cast);
    stringBuffer.append(genClassifier.getSafeUncapName());
    stringBuffer.append(TEXT_569);
    stringBuffer.append(diagnostics);
    stringBuffer.append(TEXT_570);
    stringBuffer.append(context);
    stringBuffer.append(TEXT_571);
    } else {
    if (genClassifier.hasConstraintExpression(constraint)) {
    stringBuffer.append(TEXT_572);
    stringBuffer.append(genClassifier.getQualifiedClassifierAccessor());
    stringBuffer.append(TEXT_573);
    if (!((GenClass)genClassifier).isEObjectExtension()) {
    stringBuffer.append(TEXT_574);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.EObject"));
    stringBuffer.append(TEXT_575);
    }
    stringBuffer.append(genClassifier.getSafeUncapName());
    stringBuffer.append(TEXT_576);
    stringBuffer.append(diagnostics);
    stringBuffer.append(TEXT_577);
    stringBuffer.append(context);
    stringBuffer.append(TEXT_578);
    stringBuffer.append(genClassifier.getValidationDelegate(constraint));
    stringBuffer.append(TEXT_579);
    stringBuffer.append(genModel.getNonNLS());
    stringBuffer.append(TEXT_580);
    stringBuffer.append(constraint);
    stringBuffer.append(TEXT_581);
    stringBuffer.append(genModel.getNonNLS());
    stringBuffer.append(TEXT_582);
    stringBuffer.append(genClassifier.getClassifierID());
    stringBuffer.append(TEXT_583);
    stringBuffer.append(CodeGenUtil.upperName(constraint));
    stringBuffer.append(TEXT_584);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.util.Diagnostic"));
    stringBuffer.append(TEXT_585);
    } else {
    stringBuffer.append(TEXT_586);
    stringBuffer.append(diagnostics);
    stringBuffer.append(TEXT_587);
    stringBuffer.append(diagnostics);
    stringBuffer.append(TEXT_588);
    if (genModel.getRuntimeVersion().getValue() >= GenRuntimeVersion.EMF24_VALUE) {
    stringBuffer.append(TEXT_589);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.util.Diagnostic"));
    stringBuffer.append(TEXT_590);
    stringBuffer.append(genModel.getNonNLS());
    stringBuffer.append(TEXT_591);
    stringBuffer.append(constraint);
    stringBuffer.append(TEXT_592);
    stringBuffer.append(genClassifier.getSafeUncapName());
    stringBuffer.append(TEXT_593);
    stringBuffer.append(context);
    stringBuffer.append(TEXT_594);
    stringBuffer.append(genModel.getNonNLS());
    stringBuffer.append(TEXT_595);
    stringBuffer.append(genClassifier.getSafeUncapName());
    stringBuffer.append(TEXT_596);
    } else {
    stringBuffer.append(TEXT_597);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.util.BasicDiagnostic"));
    stringBuffer.append(TEXT_598);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.util.Diagnostic"));
    stringBuffer.append(TEXT_599);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.plugin.EcorePlugin"));
    stringBuffer.append(TEXT_600);
    stringBuffer.append(constraint);
    stringBuffer.append(TEXT_601);
    stringBuffer.append(genClassifier.getSafeUncapName());
    stringBuffer.append(TEXT_602);
    stringBuffer.append(context);
    stringBuffer.append(TEXT_603);
    stringBuffer.append(genModel.getNonNLS());
    stringBuffer.append(genModel.getNonNLS(2));
    stringBuffer.append(TEXT_604);
    stringBuffer.append(genClassifier.getSafeUncapName());
    stringBuffer.append(TEXT_605);
    }
    stringBuffer.append(TEXT_606);
    }
    }}
    stringBuffer.append(TEXT_607);
    }
    }
    if (genModel.getRuntimeVersion().getValue() >= GenRuntimeVersion.EMF24_VALUE) {
    stringBuffer.append(TEXT_608);
    if (genModel.useClassOverrideAnnotation()) {
    stringBuffer.append(TEXT_609);
    }
    stringBuffer.append(TEXT_610);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.util.ResourceLocator"));
    stringBuffer.append(TEXT_611);
    if (genModel.hasModelPluginClass()) {
    stringBuffer.append(TEXT_612);
    stringBuffer.append(genModel.getImportedName(genModel.getQualifiedModelPluginClassName()));
    stringBuffer.append(TEXT_613);
    } else {
    stringBuffer.append(TEXT_614);
    }
    stringBuffer.append(TEXT_615);
    }
    stringBuffer.append(TEXT_616);
    stringBuffer.append(genPackage.getValidatorClassName());
    genModel.emitSortedImports();
    stringBuffer.append(TEXT_617);
    return stringBuffer.toString();
  }
}
