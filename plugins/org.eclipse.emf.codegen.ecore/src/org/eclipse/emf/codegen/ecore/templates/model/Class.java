package org.eclipse.emf.codegen.ecore.templates.model;

import java.util.*;
import org.eclipse.emf.codegen.ecore.genmodel.*;

public class Class
{
  protected static String nl;
  public static synchronized Class create(String lineSeparator)
  {
    nl = lineSeparator;
    Class result = new Class();
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
  protected final String TEXT_10 = NL;
  protected final String TEXT_11 = NL + "/**" + NL + " * <!-- begin-user-doc -->" + NL + " * A representation of the model object '<em><b>";
  protected final String TEXT_12 = "</b></em>'." + NL + " * <!-- end-user-doc -->";
  protected final String TEXT_13 = NL + " *" + NL + " * <!-- begin-model-doc -->" + NL + " * ";
  protected final String TEXT_14 = NL + " * <!-- end-model-doc -->";
  protected final String TEXT_15 = NL + " *";
  protected final String TEXT_16 = NL + " * <p>" + NL + " * The following features are supported:" + NL + " * <ul>";
  protected final String TEXT_17 = NL + " *   <li>{@link ";
  protected final String TEXT_18 = "#";
  protected final String TEXT_19 = " <em>";
  protected final String TEXT_20 = "</em>}</li>";
  protected final String TEXT_21 = NL + " * </ul>" + NL + " * </p>";
  protected final String TEXT_22 = NL + " *";
  protected final String TEXT_23 = NL + " * @see ";
  protected final String TEXT_24 = "#get";
  protected final String TEXT_25 = "()";
  protected final String TEXT_26 = NL + " * @model ";
  protected final String TEXT_27 = NL + " *        ";
  protected final String TEXT_28 = NL + " * @model";
  protected final String TEXT_29 = NL + " * @extends ";
  protected final String TEXT_30 = NL + " * @generated" + NL + " */";
  protected final String TEXT_31 = NL + "/**" + NL + " * <!-- begin-user-doc -->" + NL + " * An implementation of the model object '<em><b>";
  protected final String TEXT_32 = "</b></em>'." + NL + " * <!-- end-user-doc -->" + NL + " * <p>";
  protected final String TEXT_33 = NL + " * The following features are implemented:" + NL + " * <ul>";
  protected final String TEXT_34 = NL + " *   <li>{@link ";
  protected final String TEXT_35 = "#";
  protected final String TEXT_36 = " <em>";
  protected final String TEXT_37 = "</em>}</li>";
  protected final String TEXT_38 = NL + " * </ul>";
  protected final String TEXT_39 = NL + " * </p>" + NL + " *" + NL + " * @generated" + NL + " */";
  protected final String TEXT_40 = NL + "public";
  protected final String TEXT_41 = " abstract";
  protected final String TEXT_42 = " class ";
  protected final String TEXT_43 = NL + "public interface ";
  protected final String TEXT_44 = NL + "{";
  protected final String TEXT_45 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */" + NL + "\t";
  protected final String TEXT_46 = " copyright = \"";
  protected final String TEXT_47 = "\";";
  protected final String TEXT_48 = NL;
  protected final String TEXT_49 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */" + NL + "\tpublic static final ";
  protected final String TEXT_50 = " mofDriverNumber = \"";
  protected final String TEXT_51 = "\";";
  protected final String TEXT_52 = NL;
  protected final String TEXT_53 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */" + NL + "\tprivate static final long serialVersionUID = 1L;" + NL;
  protected final String TEXT_54 = NL + "\t/**" + NL + "\t * An array of objects representing the values of non-primitive features." + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */" + NL + "\tprotected Object[] ";
  protected final String TEXT_55 = " = null;" + NL;
  protected final String TEXT_56 = NL + "\t/**" + NL + "\t * A bit field representing the indices of non-primitive feature values." + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */" + NL + "\tprotected int ";
  protected final String TEXT_57 = " = 0;" + NL;
  protected final String TEXT_58 = NL + "\t/**" + NL + "\t * A set of bit flags representing the values of boolean attributes and whether unsettable features have been set." + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t * @ordered" + NL + "\t */" + NL + "\tprotected int ";
  protected final String TEXT_59 = " = 0;" + NL;
  protected final String TEXT_60 = NL + "\t/**" + NL + "\t * The cached value of the '{@link #";
  protected final String TEXT_61 = "() <em>";
  protected final String TEXT_62 = "</em>}' ";
  protected final String TEXT_63 = "." + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @see #";
  protected final String TEXT_64 = "()" + NL + "\t * @generated" + NL + "\t * @ordered" + NL + "\t */" + NL + "\tprotected ";
  protected final String TEXT_65 = " ";
  protected final String TEXT_66 = " = null;" + NL;
  protected final String TEXT_67 = NL + "\t/**" + NL + "\t * The default value of the '{@link #";
  protected final String TEXT_68 = "() <em>";
  protected final String TEXT_69 = "</em>}' ";
  protected final String TEXT_70 = "." + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @see #";
  protected final String TEXT_71 = "()" + NL + "\t * @generated" + NL + "\t * @ordered" + NL + "\t */" + NL + "\tprotected static final ";
  protected final String TEXT_72 = " ";
  protected final String TEXT_73 = "_EDEFAULT = ";
  protected final String TEXT_74 = ";";
  protected final String TEXT_75 = NL;
  protected final String TEXT_76 = NL + "\t/**" + NL + "\t * An additional set of bit flags representing the values of boolean attributes and whether unsettable features have been set." + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t * @ordered" + NL + "\t */" + NL + "\tprotected int ";
  protected final String TEXT_77 = " = 0;" + NL;
  protected final String TEXT_78 = NL + "\t/**" + NL + "\t * The flag representing the value of the '{@link #";
  protected final String TEXT_79 = "() <em>";
  protected final String TEXT_80 = "</em>}' ";
  protected final String TEXT_81 = "." + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @see #";
  protected final String TEXT_82 = "()" + NL + "\t * @generated" + NL + "\t * @ordered" + NL + "\t */" + NL + "\tprotected static final int ";
  protected final String TEXT_83 = "_EFLAG = 1 ";
  protected final String TEXT_84 = ";" + NL;
  protected final String TEXT_85 = NL + "\t/**" + NL + "\t * The cached value of the '{@link #";
  protected final String TEXT_86 = "() <em>";
  protected final String TEXT_87 = "</em>}' ";
  protected final String TEXT_88 = "." + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @see #";
  protected final String TEXT_89 = "()" + NL + "\t * @generated" + NL + "\t * @ordered" + NL + "\t */" + NL + "\tprotected ";
  protected final String TEXT_90 = " ";
  protected final String TEXT_91 = " = ";
  protected final String TEXT_92 = "_EDEFAULT;" + NL;
  protected final String TEXT_93 = NL + "\t/**" + NL + "\t * An additional set of bit flags representing the values of boolean attributes and whether unsettable features have been set." + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t * @ordered" + NL + "\t */" + NL + "\tprotected int ";
  protected final String TEXT_94 = " = 0;" + NL;
  protected final String TEXT_95 = NL + "\t/**" + NL + "\t * The flag representing whether the ";
  protected final String TEXT_96 = " ";
  protected final String TEXT_97 = " has been set." + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t * @ordered" + NL + "\t */" + NL + "\tprotected static final int ";
  protected final String TEXT_98 = "_ESETFLAG = 1 ";
  protected final String TEXT_99 = ";" + NL;
  protected final String TEXT_100 = NL + "\t/**" + NL + "\t * This is true if the ";
  protected final String TEXT_101 = " ";
  protected final String TEXT_102 = " has been set." + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t * @ordered" + NL + "\t */" + NL + "\tprotected boolean ";
  protected final String TEXT_103 = "ESet = false;" + NL;
  protected final String TEXT_104 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */" + NL + "\tprotected ";
  protected final String TEXT_105 = "()" + NL + "\t{" + NL + "\t\tsuper();";
  protected final String TEXT_106 = NL + "\t\t";
  protected final String TEXT_107 = " |= ";
  protected final String TEXT_108 = "_EFLAG;";
  protected final String TEXT_109 = NL + "\t}" + NL + "" + NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */" + NL + "\tprotected ";
  protected final String TEXT_110 = " eStaticClass()" + NL + "\t{" + NL + "\t\treturn ";
  protected final String TEXT_111 = ";" + NL + "\t}" + NL;
  protected final String TEXT_112 = NL + "\t/**" + NL + "\t * Returns the value of the '<em><b>";
  protected final String TEXT_113 = "</b></em>' ";
  protected final String TEXT_114 = ".";
  protected final String TEXT_115 = NL + "\t * The key is of type ";
  protected final String TEXT_116 = "list of {@link ";
  protected final String TEXT_117 = "}";
  protected final String TEXT_118 = "{@link ";
  protected final String TEXT_119 = "}";
  protected final String TEXT_120 = "," + NL + "\t * and the value is of type ";
  protected final String TEXT_121 = "list of {@link ";
  protected final String TEXT_122 = "}";
  protected final String TEXT_123 = "{@link ";
  protected final String TEXT_124 = "}";
  protected final String TEXT_125 = ",";
  protected final String TEXT_126 = NL + "\t * The list contents are of type {@link ";
  protected final String TEXT_127 = "}.";
  protected final String TEXT_128 = NL + "\t * The default value is <code>";
  protected final String TEXT_129 = "</code>.";
  protected final String TEXT_130 = NL + "\t * The literals are from the enumeration {@link ";
  protected final String TEXT_131 = "}.";
  protected final String TEXT_132 = NL + "\t * It is bidirectional and its opposite is '{@link ";
  protected final String TEXT_133 = "#";
  protected final String TEXT_134 = " <em>";
  protected final String TEXT_135 = "</em>}'.";
  protected final String TEXT_136 = NL + "\t * <!-- begin-user-doc -->";
  protected final String TEXT_137 = NL + "\t * <p>" + NL + "\t * If the meaning of the '<em>";
  protected final String TEXT_138 = "</em>' ";
  protected final String TEXT_139 = " isn't clear," + NL + "\t * there really should be more of a description here..." + NL + "\t * </p>";
  protected final String TEXT_140 = NL + "\t * <!-- end-user-doc -->";
  protected final String TEXT_141 = NL + "\t * <!-- begin-model-doc -->" + NL + "\t * ";
  protected final String TEXT_142 = NL + "\t * <!-- end-model-doc -->";
  protected final String TEXT_143 = NL + "\t * @return the value of the '<em>";
  protected final String TEXT_144 = "</em>' ";
  protected final String TEXT_145 = ".";
  protected final String TEXT_146 = NL + "\t * @see ";
  protected final String TEXT_147 = NL + "\t * @see #isSet";
  protected final String TEXT_148 = "()";
  protected final String TEXT_149 = NL + "\t * @see #unset";
  protected final String TEXT_150 = "()";
  protected final String TEXT_151 = NL + "\t * @see #set";
  protected final String TEXT_152 = "(";
  protected final String TEXT_153 = ")";
  protected final String TEXT_154 = NL + "\t * @see ";
  protected final String TEXT_155 = "#get";
  protected final String TEXT_156 = "()";
  protected final String TEXT_157 = NL + "\t * @see ";
  protected final String TEXT_158 = "#";
  protected final String TEXT_159 = NL + "\t * @model ";
  protected final String TEXT_160 = NL + "\t *        ";
  protected final String TEXT_161 = NL + "\t * @model";
  protected final String TEXT_162 = NL + "\t * @generated" + NL + "\t */";
  protected final String TEXT_163 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */";
  protected final String TEXT_164 = NL + "\t";
  protected final String TEXT_165 = " ";
  protected final String TEXT_166 = "();" + NL;
  protected final String TEXT_167 = NL + "\tpublic ";
  protected final String TEXT_168 = " ";
  protected final String TEXT_169 = "()" + NL + "\t{";
  protected final String TEXT_170 = NL + "\t\treturn ";
  protected final String TEXT_171 = "(";
  protected final String TEXT_172 = "(";
  protected final String TEXT_173 = ")eGet(";
  protected final String TEXT_174 = ", true)";
  protected final String TEXT_175 = ").";
  protected final String TEXT_176 = "()";
  protected final String TEXT_177 = ";";
  protected final String TEXT_178 = NL + "\t\t";
  protected final String TEXT_179 = " ";
  protected final String TEXT_180 = " = (";
  protected final String TEXT_181 = ")eVirtualGet(";
  protected final String TEXT_182 = ");";
  protected final String TEXT_183 = NL + "\t\tif (";
  protected final String TEXT_184 = " == null)" + NL + "\t\t{";
  protected final String TEXT_185 = NL + "\t\t\teVirtualSet(";
  protected final String TEXT_186 = ", ";
  protected final String TEXT_187 = " = new ";
  protected final String TEXT_188 = ");";
  protected final String TEXT_189 = NL + "\t\t\t";
  protected final String TEXT_190 = " = new ";
  protected final String TEXT_191 = ";";
  protected final String TEXT_192 = NL + "\t\t}" + NL + "\t\treturn ";
  protected final String TEXT_193 = ";";
  protected final String TEXT_194 = NL + "\t\tif (eContainerFeatureID != ";
  protected final String TEXT_195 = ") return null;" + NL + "\t\treturn (";
  protected final String TEXT_196 = ")eContainer();";
  protected final String TEXT_197 = NL + "\t\t";
  protected final String TEXT_198 = " ";
  protected final String TEXT_199 = " = (";
  protected final String TEXT_200 = ")eVirtualGet(";
  protected final String TEXT_201 = ", ";
  protected final String TEXT_202 = "_EDEFAULT";
  protected final String TEXT_203 = ");";
  protected final String TEXT_204 = NL + "\t\tif (";
  protected final String TEXT_205 = " != null && ";
  protected final String TEXT_206 = ".eIsProxy())" + NL + "\t\t{" + NL + "\t\t\t";
  protected final String TEXT_207 = " old";
  protected final String TEXT_208 = " = (";
  protected final String TEXT_209 = ")";
  protected final String TEXT_210 = ";" + NL + "\t\t\t";
  protected final String TEXT_211 = " = ";
  protected final String TEXT_212 = "eResolveProxy(old";
  protected final String TEXT_213 = ");" + NL + "\t\t\tif (";
  protected final String TEXT_214 = " != old";
  protected final String TEXT_215 = ")" + NL + "\t\t\t{";
  protected final String TEXT_216 = NL + "\t\t\t\t";
  protected final String TEXT_217 = " new";
  protected final String TEXT_218 = " = (";
  protected final String TEXT_219 = ")";
  protected final String TEXT_220 = ";";
  protected final String TEXT_221 = NL + "\t\t\t\t";
  protected final String TEXT_222 = " msgs = old";
  protected final String TEXT_223 = ".eInverseRemove(this, EOPPOSITE_FEATURE_BASE - ";
  protected final String TEXT_224 = ", null, null);";
  protected final String TEXT_225 = NL + "\t\t\t\t";
  protected final String TEXT_226 = " msgs =  old";
  protected final String TEXT_227 = ".eInverseRemove(this, ";
  protected final String TEXT_228 = ", ";
  protected final String TEXT_229 = ".class, null);";
  protected final String TEXT_230 = NL + "\t\t\t\tif (new";
  protected final String TEXT_231 = ".eInternalContainer() != null)" + NL + "\t\t\t\t{";
  protected final String TEXT_232 = NL + "\t\t\t\t\tmsgs = new";
  protected final String TEXT_233 = ".eInverseAdd(this, EOPPOSITE_FEATURE_BASE - ";
  protected final String TEXT_234 = ", null, msgs);";
  protected final String TEXT_235 = NL + "\t\t\t\t\tmsgs =  new";
  protected final String TEXT_236 = ".eInverseAdd(this, ";
  protected final String TEXT_237 = ", ";
  protected final String TEXT_238 = ".class, msgs);";
  protected final String TEXT_239 = NL + "\t\t\t\t}" + NL + "\t\t\t\tif (msgs != null) msgs.dispatch();";
  protected final String TEXT_240 = NL + "\t\t\t\teVirtualSet(";
  protected final String TEXT_241 = ", ";
  protected final String TEXT_242 = ");";
  protected final String TEXT_243 = NL + "\t\t\t\tif (eNotificationRequired())" + NL + "\t\t\t\t\teNotify(new ";
  protected final String TEXT_244 = "(this, ";
  protected final String TEXT_245 = ".RESOLVE, ";
  protected final String TEXT_246 = ", old";
  protected final String TEXT_247 = ", ";
  protected final String TEXT_248 = "));" + NL + "\t\t\t}" + NL + "\t\t}";
  protected final String TEXT_249 = NL + "\t\treturn (";
  protected final String TEXT_250 = ")eVirtualGet(";
  protected final String TEXT_251 = ", ";
  protected final String TEXT_252 = "_EDEFAULT";
  protected final String TEXT_253 = ");";
  protected final String TEXT_254 = NL + "\t\treturn (";
  protected final String TEXT_255 = " & ";
  protected final String TEXT_256 = "_EFLAG) != 0;";
  protected final String TEXT_257 = NL + "\t\treturn ";
  protected final String TEXT_258 = ";";
  protected final String TEXT_259 = NL + "\t\t";
  protected final String TEXT_260 = " ";
  protected final String TEXT_261 = " = basicGet";
  protected final String TEXT_262 = "();" + NL + "\t\treturn ";
  protected final String TEXT_263 = " != null && ";
  protected final String TEXT_264 = ".eIsProxy() ? ";
  protected final String TEXT_265 = "eResolveProxy((";
  protected final String TEXT_266 = ")";
  protected final String TEXT_267 = ") : ";
  protected final String TEXT_268 = ";";
  protected final String TEXT_269 = NL + "\t\treturn new ";
  protected final String TEXT_270 = "((";
  protected final String TEXT_271 = ".Internal)((";
  protected final String TEXT_272 = ".Internal.Wrapper)get";
  protected final String TEXT_273 = "()).featureMap().list(";
  protected final String TEXT_274 = "));";
  protected final String TEXT_275 = NL + "\t\treturn (";
  protected final String TEXT_276 = ")((";
  protected final String TEXT_277 = ")get";
  protected final String TEXT_278 = "()).list(";
  protected final String TEXT_279 = ");";
  protected final String TEXT_280 = NL + "\t\treturn ((";
  protected final String TEXT_281 = ".Internal.Wrapper)get";
  protected final String TEXT_282 = "()).featureMap().list(";
  protected final String TEXT_283 = ");";
  protected final String TEXT_284 = NL + "\t\treturn ((";
  protected final String TEXT_285 = ")get";
  protected final String TEXT_286 = "()).list(";
  protected final String TEXT_287 = ");";
  protected final String TEXT_288 = NL + "\t\treturn ";
  protected final String TEXT_289 = "(";
  protected final String TEXT_290 = "(";
  protected final String TEXT_291 = ")((";
  protected final String TEXT_292 = ".Internal.Wrapper)get";
  protected final String TEXT_293 = "()).featureMap().get(";
  protected final String TEXT_294 = ", true)";
  protected final String TEXT_295 = ").";
  protected final String TEXT_296 = "()";
  protected final String TEXT_297 = ";";
  protected final String TEXT_298 = NL + "\t\treturn ";
  protected final String TEXT_299 = "(";
  protected final String TEXT_300 = "(";
  protected final String TEXT_301 = ")get";
  protected final String TEXT_302 = "().get(";
  protected final String TEXT_303 = ", true)";
  protected final String TEXT_304 = ").";
  protected final String TEXT_305 = "()";
  protected final String TEXT_306 = ";";
  protected final String TEXT_307 = NL + "\t\t// TODO: implement this method to return the '";
  protected final String TEXT_308 = "' ";
  protected final String TEXT_309 = NL + "\t\t// Ensure that you remove @generated or mark it @generated NOT" + NL + "\t\tthrow new UnsupportedOperationException();";
  protected final String TEXT_310 = NL + "\t}" + NL;
  protected final String TEXT_311 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */" + NL + "\tpublic ";
  protected final String TEXT_312 = " basicGet";
  protected final String TEXT_313 = "()" + NL + "\t{";
  protected final String TEXT_314 = NL + "\t\tif (eContainerFeatureID != ";
  protected final String TEXT_315 = ") return null;" + NL + "\t\treturn (";
  protected final String TEXT_316 = ")eInternalContainer();";
  protected final String TEXT_317 = NL + "\t\treturn (";
  protected final String TEXT_318 = ")eVirtualGet(";
  protected final String TEXT_319 = ");";
  protected final String TEXT_320 = NL + "\t\treturn ";
  protected final String TEXT_321 = ";";
  protected final String TEXT_322 = NL + "\t\treturn (";
  protected final String TEXT_323 = ")((";
  protected final String TEXT_324 = ".Internal.Wrapper)get";
  protected final String TEXT_325 = "()).featureMap().get(";
  protected final String TEXT_326 = ", false);";
  protected final String TEXT_327 = NL + "\t\treturn (";
  protected final String TEXT_328 = ")get";
  protected final String TEXT_329 = "().get(";
  protected final String TEXT_330 = ", false);";
  protected final String TEXT_331 = NL + "\t\t// TODO: implement this method to return the '";
  protected final String TEXT_332 = "' ";
  protected final String TEXT_333 = NL + "\t\t// -> do not perform proxy resolution" + NL + "\t\t// Ensure that you remove @generated or mark it @generated NOT" + NL + "\t\tthrow new UnsupportedOperationException();";
  protected final String TEXT_334 = NL + "\t}" + NL;
  protected final String TEXT_335 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */" + NL + "\tpublic ";
  protected final String TEXT_336 = " basicSet";
  protected final String TEXT_337 = "(";
  protected final String TEXT_338 = " new";
  protected final String TEXT_339 = ", ";
  protected final String TEXT_340 = " msgs)" + NL + "\t{";
  protected final String TEXT_341 = NL + "\t\tObject old";
  protected final String TEXT_342 = " = eVirtualSet(";
  protected final String TEXT_343 = ", new";
  protected final String TEXT_344 = ");";
  protected final String TEXT_345 = NL + "\t\t";
  protected final String TEXT_346 = " old";
  protected final String TEXT_347 = " = ";
  protected final String TEXT_348 = ";" + NL + "\t\t";
  protected final String TEXT_349 = " = new";
  protected final String TEXT_350 = ";";
  protected final String TEXT_351 = NL + "\t\tboolean isSetChange = old";
  protected final String TEXT_352 = " == EVIRTUAL_NO_VALUE;";
  protected final String TEXT_353 = NL + "\t\tboolean old";
  protected final String TEXT_354 = "ESet = (";
  protected final String TEXT_355 = " & ";
  protected final String TEXT_356 = "_ESETFLAG) != 0;" + NL + "\t\t";
  protected final String TEXT_357 = " |= ";
  protected final String TEXT_358 = "_ESETFLAG;";
  protected final String TEXT_359 = NL + "\t\tboolean old";
  protected final String TEXT_360 = "ESet = ";
  protected final String TEXT_361 = "ESet;" + NL + "\t\t";
  protected final String TEXT_362 = "ESet = true;";
  protected final String TEXT_363 = NL + "\t\tif (eNotificationRequired())" + NL + "\t\t{";
  protected final String TEXT_364 = NL + "\t\t\t";
  protected final String TEXT_365 = " notification = new ";
  protected final String TEXT_366 = "(this, ";
  protected final String TEXT_367 = ".SET, ";
  protected final String TEXT_368 = ", ";
  protected final String TEXT_369 = "isSetChange ? null : old";
  protected final String TEXT_370 = "old";
  protected final String TEXT_371 = ", new";
  protected final String TEXT_372 = ", ";
  protected final String TEXT_373 = "isSetChange";
  protected final String TEXT_374 = "!old";
  protected final String TEXT_375 = "ESet";
  protected final String TEXT_376 = ");";
  protected final String TEXT_377 = NL + "\t\t\t";
  protected final String TEXT_378 = " notification = new ";
  protected final String TEXT_379 = "(this, ";
  protected final String TEXT_380 = ".SET, ";
  protected final String TEXT_381 = ", ";
  protected final String TEXT_382 = "old";
  protected final String TEXT_383 = " == EVIRTUAL_NO_VALUE ? null : old";
  protected final String TEXT_384 = "old";
  protected final String TEXT_385 = ", new";
  protected final String TEXT_386 = ");";
  protected final String TEXT_387 = NL + "\t\t\tif (msgs == null) msgs = notification; else msgs.add(notification);" + NL + "\t\t}";
  protected final String TEXT_388 = NL + "\t\treturn msgs;";
  protected final String TEXT_389 = NL + "\t\treturn ((";
  protected final String TEXT_390 = ".Internal)((";
  protected final String TEXT_391 = ".Internal.Wrapper)get";
  protected final String TEXT_392 = "()).featureMap()).basicAdd(";
  protected final String TEXT_393 = ", new";
  protected final String TEXT_394 = ", msgs);";
  protected final String TEXT_395 = NL + "\t\treturn ((";
  protected final String TEXT_396 = ".Internal)get";
  protected final String TEXT_397 = "()).basicAdd(";
  protected final String TEXT_398 = ", new";
  protected final String TEXT_399 = ", msgs);";
  protected final String TEXT_400 = NL + "\t\t// TODO: implement this method to set the contained '";
  protected final String TEXT_401 = "' ";
  protected final String TEXT_402 = NL + "\t\t// -> this method is automatically invoked to keep the containment relationship in synch" + NL + "\t\t// -> do not modify other features" + NL + "\t\t// -> return msgs, after adding any generated Notification to it (if it is null, a NotificationChain object must be created first)" + NL + "\t\t// Ensure that you remove @generated or mark it @generated NOT" + NL + "\t\tthrow new UnsupportedOperationException();";
  protected final String TEXT_403 = NL + "\t}" + NL;
  protected final String TEXT_404 = NL + "\t/**" + NL + "\t * Sets the value of the '{@link ";
  protected final String TEXT_405 = "#";
  protected final String TEXT_406 = " <em>";
  protected final String TEXT_407 = "</em>}' ";
  protected final String TEXT_408 = "." + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @param value the new value of the '<em>";
  protected final String TEXT_409 = "</em>' ";
  protected final String TEXT_410 = ".";
  protected final String TEXT_411 = NL + "\t * @see ";
  protected final String TEXT_412 = NL + "\t * @see #isSet";
  protected final String TEXT_413 = "()";
  protected final String TEXT_414 = NL + "\t * @see #unset";
  protected final String TEXT_415 = "()";
  protected final String TEXT_416 = NL + "\t * @see #";
  protected final String TEXT_417 = "()" + NL + "\t * @generated" + NL + "\t */";
  protected final String TEXT_418 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */";
  protected final String TEXT_419 = NL + "\tvoid set";
  protected final String TEXT_420 = "(";
  protected final String TEXT_421 = " value);" + NL;
  protected final String TEXT_422 = NL + "\tpublic void set";
  protected final String TEXT_423 = "(";
  protected final String TEXT_424 = " new";
  protected final String TEXT_425 = ")" + NL + "\t{";
  protected final String TEXT_426 = NL + "\t\teSet(";
  protected final String TEXT_427 = ", ";
  protected final String TEXT_428 = "new ";
  protected final String TEXT_429 = "(";
  protected final String TEXT_430 = "new";
  protected final String TEXT_431 = ")";
  protected final String TEXT_432 = ");";
  protected final String TEXT_433 = NL + "\t\tif (new";
  protected final String TEXT_434 = " != eInternalContainer() || (eContainerFeatureID != ";
  protected final String TEXT_435 = " && new";
  protected final String TEXT_436 = " != null))" + NL + "\t\t{" + NL + "\t\t\tif (";
  protected final String TEXT_437 = ".isAncestor(this, ";
  protected final String TEXT_438 = "new";
  protected final String TEXT_439 = "))" + NL + "\t\t\t\tthrow new ";
  protected final String TEXT_440 = "(\"Recursive containment not allowed for \" + toString());";
  protected final String TEXT_441 = NL + "\t\t\t";
  protected final String TEXT_442 = " msgs = null;" + NL + "\t\t\tif (eInternalContainer() != null)" + NL + "\t\t\t\tmsgs = eBasicRemoveFromContainer(msgs);" + NL + "\t\t\tif (new";
  protected final String TEXT_443 = " != null)" + NL + "\t\t\t\tmsgs = ((";
  protected final String TEXT_444 = ")new";
  protected final String TEXT_445 = ").eInverseAdd(this, ";
  protected final String TEXT_446 = ", ";
  protected final String TEXT_447 = ".class, msgs);" + NL + "\t\t\tmsgs = eBasicSetContainer((";
  protected final String TEXT_448 = ")new";
  protected final String TEXT_449 = ", ";
  protected final String TEXT_450 = ", msgs);" + NL + "\t\t\tif (msgs != null) msgs.dispatch();" + NL + "\t\t}" + NL + "\t\telse if (eNotificationRequired())" + NL + "\t\t\teNotify(new ";
  protected final String TEXT_451 = "(this, ";
  protected final String TEXT_452 = ".SET, ";
  protected final String TEXT_453 = ", new";
  protected final String TEXT_454 = ", new";
  protected final String TEXT_455 = "));";
  protected final String TEXT_456 = NL + "\t\t";
  protected final String TEXT_457 = " ";
  protected final String TEXT_458 = " = (";
  protected final String TEXT_459 = ")eVirtualGet(";
  protected final String TEXT_460 = ");";
  protected final String TEXT_461 = NL + "\t\tif (new";
  protected final String TEXT_462 = " != ";
  protected final String TEXT_463 = ")" + NL + "\t\t{" + NL + "\t\t\t";
  protected final String TEXT_464 = " msgs = null;" + NL + "\t\t\tif (";
  protected final String TEXT_465 = " != null)";
  protected final String TEXT_466 = NL + "\t\t\t\tmsgs = ((";
  protected final String TEXT_467 = ")";
  protected final String TEXT_468 = ").eInverseRemove(this, EOPPOSITE_FEATURE_BASE - ";
  protected final String TEXT_469 = ", null, msgs);" + NL + "\t\t\tif (new";
  protected final String TEXT_470 = " != null)" + NL + "\t\t\t\tmsgs = ((";
  protected final String TEXT_471 = ")new";
  protected final String TEXT_472 = ").eInverseAdd(this, EOPPOSITE_FEATURE_BASE - ";
  protected final String TEXT_473 = ", null, msgs);";
  protected final String TEXT_474 = NL + "\t\t\t\tmsgs = ((";
  protected final String TEXT_475 = ")";
  protected final String TEXT_476 = ").eInverseRemove(this, ";
  protected final String TEXT_477 = ", ";
  protected final String TEXT_478 = ".class, msgs);" + NL + "\t\t\tif (new";
  protected final String TEXT_479 = " != null)" + NL + "\t\t\t\tmsgs = ((";
  protected final String TEXT_480 = ")new";
  protected final String TEXT_481 = ").eInverseAdd(this, ";
  protected final String TEXT_482 = ", ";
  protected final String TEXT_483 = ".class, msgs);";
  protected final String TEXT_484 = NL + "\t\t\tmsgs = basicSet";
  protected final String TEXT_485 = "(";
  protected final String TEXT_486 = "new";
  protected final String TEXT_487 = ", msgs);" + NL + "\t\t\tif (msgs != null) msgs.dispatch();" + NL + "\t\t}";
  protected final String TEXT_488 = NL + "\t\telse" + NL + "    \t{";
  protected final String TEXT_489 = NL + "\t\t\tboolean old";
  protected final String TEXT_490 = "ESet = eVirtualIsSet(";
  protected final String TEXT_491 = ");";
  protected final String TEXT_492 = NL + "\t\t\tboolean old";
  protected final String TEXT_493 = "ESet = (";
  protected final String TEXT_494 = " & ";
  protected final String TEXT_495 = "_ESETFLAG) != 0;" + NL + "\t\t\t";
  protected final String TEXT_496 = " |= ";
  protected final String TEXT_497 = "_ESETFLAG;";
  protected final String TEXT_498 = NL + "\t\t\tboolean old";
  protected final String TEXT_499 = "ESet = ";
  protected final String TEXT_500 = "ESet;" + NL + "\t\t\t";
  protected final String TEXT_501 = "ESet = true;";
  protected final String TEXT_502 = NL + "\t\t\tif (eNotificationRequired())" + NL + "\t\t\t\teNotify(new ";
  protected final String TEXT_503 = "(this, ";
  protected final String TEXT_504 = ".SET, ";
  protected final String TEXT_505 = ", new";
  protected final String TEXT_506 = ", new";
  protected final String TEXT_507 = ", !old";
  protected final String TEXT_508 = "ESet));" + NL + "    \t}";
  protected final String TEXT_509 = NL + "\t\telse if (eNotificationRequired())" + NL + "\t\t\teNotify(new ";
  protected final String TEXT_510 = "(this, ";
  protected final String TEXT_511 = ".SET, ";
  protected final String TEXT_512 = ", new";
  protected final String TEXT_513 = ", new";
  protected final String TEXT_514 = "));";
  protected final String TEXT_515 = NL + "\t\t";
  protected final String TEXT_516 = " old";
  protected final String TEXT_517 = " = (";
  protected final String TEXT_518 = " & ";
  protected final String TEXT_519 = "_EFLAG) != 0;" + NL + "\t\tif (new";
  protected final String TEXT_520 = ") ";
  protected final String TEXT_521 = " |= ";
  protected final String TEXT_522 = "_EFLAG; else ";
  protected final String TEXT_523 = " &= ~";
  protected final String TEXT_524 = "_EFLAG;";
  protected final String TEXT_525 = NL + "\t\t";
  protected final String TEXT_526 = " old";
  protected final String TEXT_527 = " = ";
  protected final String TEXT_528 = ";";
  protected final String TEXT_529 = NL + "\t\t";
  protected final String TEXT_530 = " ";
  protected final String TEXT_531 = " = new";
  protected final String TEXT_532 = " == null ? ";
  protected final String TEXT_533 = "_EDEFAULT : new";
  protected final String TEXT_534 = ";";
  protected final String TEXT_535 = NL + "\t\t";
  protected final String TEXT_536 = " = new";
  protected final String TEXT_537 = " == null ? ";
  protected final String TEXT_538 = "_EDEFAULT : new";
  protected final String TEXT_539 = ";";
  protected final String TEXT_540 = NL + "\t\t";
  protected final String TEXT_541 = " ";
  protected final String TEXT_542 = " = ";
  protected final String TEXT_543 = "new";
  protected final String TEXT_544 = ";";
  protected final String TEXT_545 = NL + "\t\t";
  protected final String TEXT_546 = " = ";
  protected final String TEXT_547 = "new";
  protected final String TEXT_548 = ";";
  protected final String TEXT_549 = NL + "\t\tObject old";
  protected final String TEXT_550 = " = eVirtualSet(";
  protected final String TEXT_551 = ", ";
  protected final String TEXT_552 = ");";
  protected final String TEXT_553 = NL + "\t\tboolean isSetChange = old";
  protected final String TEXT_554 = " == EVIRTUAL_NO_VALUE;";
  protected final String TEXT_555 = NL + "\t\tboolean old";
  protected final String TEXT_556 = "ESet = (";
  protected final String TEXT_557 = " & ";
  protected final String TEXT_558 = "_ESETFLAG) != 0;" + NL + "\t\t";
  protected final String TEXT_559 = " |= ";
  protected final String TEXT_560 = "_ESETFLAG;";
  protected final String TEXT_561 = NL + "\t\tboolean old";
  protected final String TEXT_562 = "ESet = ";
  protected final String TEXT_563 = "ESet;" + NL + "\t\t";
  protected final String TEXT_564 = "ESet = true;";
  protected final String TEXT_565 = NL + "\t\tif (eNotificationRequired())" + NL + "\t\t\teNotify(new ";
  protected final String TEXT_566 = "(this, ";
  protected final String TEXT_567 = ".SET, ";
  protected final String TEXT_568 = ", ";
  protected final String TEXT_569 = "isSetChange ? ";
  protected final String TEXT_570 = "null";
  protected final String TEXT_571 = "_EDEFAULT";
  protected final String TEXT_572 = " : old";
  protected final String TEXT_573 = "old";
  protected final String TEXT_574 = ", ";
  protected final String TEXT_575 = "new";
  protected final String TEXT_576 = ", ";
  protected final String TEXT_577 = "isSetChange";
  protected final String TEXT_578 = "!old";
  protected final String TEXT_579 = "ESet";
  protected final String TEXT_580 = "));";
  protected final String TEXT_581 = NL + "\t\tif (eNotificationRequired())" + NL + "\t\t\teNotify(new ";
  protected final String TEXT_582 = "(this, ";
  protected final String TEXT_583 = ".SET, ";
  protected final String TEXT_584 = ", ";
  protected final String TEXT_585 = "old";
  protected final String TEXT_586 = " == EVIRTUAL_NO_VALUE ? ";
  protected final String TEXT_587 = "null";
  protected final String TEXT_588 = "_EDEFAULT";
  protected final String TEXT_589 = " : old";
  protected final String TEXT_590 = "old";
  protected final String TEXT_591 = ", ";
  protected final String TEXT_592 = "new";
  protected final String TEXT_593 = "));";
  protected final String TEXT_594 = NL + "\t\t((";
  protected final String TEXT_595 = ".Internal)((";
  protected final String TEXT_596 = ".Internal.Wrapper)get";
  protected final String TEXT_597 = "()).featureMap()).set(";
  protected final String TEXT_598 = ", ";
  protected final String TEXT_599 = "new ";
  protected final String TEXT_600 = "(";
  protected final String TEXT_601 = "new";
  protected final String TEXT_602 = ")";
  protected final String TEXT_603 = ");";
  protected final String TEXT_604 = NL + "\t\t((";
  protected final String TEXT_605 = ".Internal)get";
  protected final String TEXT_606 = "()).set(";
  protected final String TEXT_607 = ", ";
  protected final String TEXT_608 = "new ";
  protected final String TEXT_609 = "(";
  protected final String TEXT_610 = "new";
  protected final String TEXT_611 = ")";
  protected final String TEXT_612 = ");";
  protected final String TEXT_613 = NL + "\t\t// TODO: implement this method to set the '";
  protected final String TEXT_614 = "' ";
  protected final String TEXT_615 = NL + "\t\t// Ensure that you remove @generated or mark it @generated NOT" + NL + "\t\tthrow new UnsupportedOperationException();";
  protected final String TEXT_616 = NL + "\t}" + NL;
  protected final String TEXT_617 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */" + NL + "\tpublic ";
  protected final String TEXT_618 = " basicUnset";
  protected final String TEXT_619 = "(";
  protected final String TEXT_620 = " msgs)" + NL + "\t{";
  protected final String TEXT_621 = NL + "\t\tObject old";
  protected final String TEXT_622 = " = eVirtualUnset(";
  protected final String TEXT_623 = ");";
  protected final String TEXT_624 = NL + "\t\t";
  protected final String TEXT_625 = " old";
  protected final String TEXT_626 = " = ";
  protected final String TEXT_627 = ";" + NL + "\t\t";
  protected final String TEXT_628 = " = null;";
  protected final String TEXT_629 = NL + "\t\tboolean isSetChange = old";
  protected final String TEXT_630 = " != EVIRTUAL_NO_VALUE;";
  protected final String TEXT_631 = NL + "\t\tboolean old";
  protected final String TEXT_632 = "ESet = (";
  protected final String TEXT_633 = " & ";
  protected final String TEXT_634 = "_ESETFLAG) != 0;" + NL + "\t\t";
  protected final String TEXT_635 = " &= ~";
  protected final String TEXT_636 = "_ESETFLAG;";
  protected final String TEXT_637 = NL + "\t\tboolean old";
  protected final String TEXT_638 = "ESet = ";
  protected final String TEXT_639 = "ESet;" + NL + "\t\t";
  protected final String TEXT_640 = "ESet = false;";
  protected final String TEXT_641 = NL + "\t\tif (eNotificationRequired())" + NL + "\t\t{" + NL + "\t\t\t";
  protected final String TEXT_642 = " notification = new ";
  protected final String TEXT_643 = "(this, ";
  protected final String TEXT_644 = ".UNSET, ";
  protected final String TEXT_645 = ", ";
  protected final String TEXT_646 = "isSetChange ? old";
  protected final String TEXT_647 = " : null";
  protected final String TEXT_648 = "old";
  protected final String TEXT_649 = ", null, ";
  protected final String TEXT_650 = "isSetChange";
  protected final String TEXT_651 = "old";
  protected final String TEXT_652 = "ESet";
  protected final String TEXT_653 = ");" + NL + "\t\t\tif (msgs == null) msgs = notification; else msgs.add(notification);" + NL + "\t\t}" + NL + "\t\treturn msgs;";
  protected final String TEXT_654 = NL + "\t\t// TODO: implement this method to unset the contained '";
  protected final String TEXT_655 = "' ";
  protected final String TEXT_656 = NL + "\t\t// -> this method is automatically invoked to keep the containment relationship in synch" + NL + "\t\t// -> do not modify other features" + NL + "\t\t// -> return msgs, after adding any generated Notification to it (if it is null, a NotificationChain object must be created first)" + NL + "\t\t// Ensure that you remove @generated or mark it @generated NOT" + NL + "\t\tthrow new UnsupportedOperationException();";
  protected final String TEXT_657 = NL + "\t}" + NL;
  protected final String TEXT_658 = NL + "\t/**" + NL + "\t * Unsets the value of the '{@link ";
  protected final String TEXT_659 = "#";
  protected final String TEXT_660 = " <em>";
  protected final String TEXT_661 = "</em>}' ";
  protected final String TEXT_662 = "." + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->";
  protected final String TEXT_663 = NL + "\t * @see #isSet";
  protected final String TEXT_664 = "()";
  protected final String TEXT_665 = NL + "\t * @see #";
  protected final String TEXT_666 = "()";
  protected final String TEXT_667 = NL + "\t * @see #set";
  protected final String TEXT_668 = "(";
  protected final String TEXT_669 = ")";
  protected final String TEXT_670 = NL + "\t * @generated" + NL + "\t */";
  protected final String TEXT_671 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */";
  protected final String TEXT_672 = NL + "\tvoid unset";
  protected final String TEXT_673 = "();" + NL;
  protected final String TEXT_674 = NL + "\tpublic void unset";
  protected final String TEXT_675 = "()" + NL + "\t{";
  protected final String TEXT_676 = NL + "\t\teUnset(";
  protected final String TEXT_677 = ");";
  protected final String TEXT_678 = NL + "\t\t((";
  protected final String TEXT_679 = ".Unsettable)get";
  protected final String TEXT_680 = "()).unset();";
  protected final String TEXT_681 = NL + "\t\t";
  protected final String TEXT_682 = " ";
  protected final String TEXT_683 = " = (";
  protected final String TEXT_684 = ")eVirtualGet(";
  protected final String TEXT_685 = ");";
  protected final String TEXT_686 = NL + "\t\tif (";
  protected final String TEXT_687 = " != null)" + NL + "\t\t{" + NL + "\t\t\t";
  protected final String TEXT_688 = " msgs = null;";
  protected final String TEXT_689 = NL + "\t\t\tmsgs = ((";
  protected final String TEXT_690 = ")";
  protected final String TEXT_691 = ").eInverseRemove(this, EOPPOSITE_FEATURE_BASE - ";
  protected final String TEXT_692 = ", null, msgs);";
  protected final String TEXT_693 = NL + "\t\t\tmsgs = ((";
  protected final String TEXT_694 = ")";
  protected final String TEXT_695 = ").eInverseRemove(this, ";
  protected final String TEXT_696 = ", ";
  protected final String TEXT_697 = ".class, msgs);";
  protected final String TEXT_698 = NL + "\t\t\tmsgs = basicUnset";
  protected final String TEXT_699 = "(msgs);" + NL + "\t\t\tif (msgs != null) msgs.dispatch();" + NL + "\t\t}" + NL + "\t\telse" + NL + "    \t{";
  protected final String TEXT_700 = NL + "\t\t\tboolean old";
  protected final String TEXT_701 = "ESet = eVirtualIsSet(";
  protected final String TEXT_702 = ");";
  protected final String TEXT_703 = NL + "\t\t\tboolean old";
  protected final String TEXT_704 = "ESet = (";
  protected final String TEXT_705 = " & ";
  protected final String TEXT_706 = "_ESETFLAG) != 0;" + NL + "\t\t\t";
  protected final String TEXT_707 = " &= ~";
  protected final String TEXT_708 = "_ESETFLAG;";
  protected final String TEXT_709 = NL + "\t\t\tboolean old";
  protected final String TEXT_710 = "ESet = ";
  protected final String TEXT_711 = "ESet;" + NL + "\t\t\t";
  protected final String TEXT_712 = "ESet = false;";
  protected final String TEXT_713 = NL + "\t\t\tif (eNotificationRequired())" + NL + "\t\t\t\teNotify(new ";
  protected final String TEXT_714 = "(this, ";
  protected final String TEXT_715 = ".UNSET, ";
  protected final String TEXT_716 = ", null, null, old";
  protected final String TEXT_717 = "ESet));" + NL + "    \t}";
  protected final String TEXT_718 = NL + "\t\t";
  protected final String TEXT_719 = " old";
  protected final String TEXT_720 = " = (";
  protected final String TEXT_721 = " & ";
  protected final String TEXT_722 = "_EFLAG) != 0;";
  protected final String TEXT_723 = NL + "\t\tObject old";
  protected final String TEXT_724 = " = eVirtualUnset(";
  protected final String TEXT_725 = ");";
  protected final String TEXT_726 = NL + "\t\t";
  protected final String TEXT_727 = " old";
  protected final String TEXT_728 = " = ";
  protected final String TEXT_729 = ";";
  protected final String TEXT_730 = NL + "\t\tboolean isSetChange = old";
  protected final String TEXT_731 = " != EVIRTUAL_NO_VALUE;";
  protected final String TEXT_732 = NL + "\t\tboolean old";
  protected final String TEXT_733 = "ESet = (";
  protected final String TEXT_734 = " & ";
  protected final String TEXT_735 = "_ESETFLAG) != 0;";
  protected final String TEXT_736 = NL + "\t\tboolean old";
  protected final String TEXT_737 = "ESet = ";
  protected final String TEXT_738 = "ESet;";
  protected final String TEXT_739 = NL + "\t\t";
  protected final String TEXT_740 = " = null;";
  protected final String TEXT_741 = NL + "\t\t";
  protected final String TEXT_742 = " &= ~";
  protected final String TEXT_743 = "_ESETFLAG;";
  protected final String TEXT_744 = NL + "\t\t";
  protected final String TEXT_745 = "ESet = false;";
  protected final String TEXT_746 = NL + "\t\tif (eNotificationRequired())" + NL + "\t\t\teNotify(new ";
  protected final String TEXT_747 = "(this, ";
  protected final String TEXT_748 = ".UNSET, ";
  protected final String TEXT_749 = ", ";
  protected final String TEXT_750 = "isSetChange ? old";
  protected final String TEXT_751 = " : null";
  protected final String TEXT_752 = "old";
  protected final String TEXT_753 = ", null, ";
  protected final String TEXT_754 = "isSetChange";
  protected final String TEXT_755 = "old";
  protected final String TEXT_756 = "ESet";
  protected final String TEXT_757 = "));";
  protected final String TEXT_758 = NL + "\t\tif (";
  protected final String TEXT_759 = "_EDEFAULT) ";
  protected final String TEXT_760 = " |= ";
  protected final String TEXT_761 = "_EFLAG; else ";
  protected final String TEXT_762 = " &= ~";
  protected final String TEXT_763 = "_EFLAG;";
  protected final String TEXT_764 = NL + "\t\t";
  protected final String TEXT_765 = " = ";
  protected final String TEXT_766 = "_EDEFAULT;";
  protected final String TEXT_767 = NL + "\t\t";
  protected final String TEXT_768 = " &= ~";
  protected final String TEXT_769 = "_ESETFLAG;";
  protected final String TEXT_770 = NL + "\t\t";
  protected final String TEXT_771 = "ESet = false;";
  protected final String TEXT_772 = NL + "\t\tif (eNotificationRequired())" + NL + "\t\t\teNotify(new ";
  protected final String TEXT_773 = "(this, ";
  protected final String TEXT_774 = ".UNSET, ";
  protected final String TEXT_775 = ", ";
  protected final String TEXT_776 = "isSetChange ? old";
  protected final String TEXT_777 = " : ";
  protected final String TEXT_778 = "_EDEFAULT";
  protected final String TEXT_779 = "old";
  protected final String TEXT_780 = ", ";
  protected final String TEXT_781 = "_EDEFAULT, ";
  protected final String TEXT_782 = "isSetChange";
  protected final String TEXT_783 = "old";
  protected final String TEXT_784 = "ESet";
  protected final String TEXT_785 = "));";
  protected final String TEXT_786 = NL + "\t\t((";
  protected final String TEXT_787 = ".Internal)((";
  protected final String TEXT_788 = ".Internal.Wrapper)get";
  protected final String TEXT_789 = "()).featureMap()).clear(";
  protected final String TEXT_790 = ");";
  protected final String TEXT_791 = NL + "\t\t((";
  protected final String TEXT_792 = ".Internal)get";
  protected final String TEXT_793 = "()).clear(";
  protected final String TEXT_794 = ");";
  protected final String TEXT_795 = NL + "\t\t// TODO: implement this method to unset the '";
  protected final String TEXT_796 = "' ";
  protected final String TEXT_797 = NL + "\t\t// Ensure that you remove @generated or mark it @generated NOT" + NL + "\t\tthrow new UnsupportedOperationException();";
  protected final String TEXT_798 = NL + "\t}" + NL;
  protected final String TEXT_799 = NL + "\t/**" + NL + "\t * Returns whether the value of the '{@link ";
  protected final String TEXT_800 = "#";
  protected final String TEXT_801 = " <em>";
  protected final String TEXT_802 = "</em>}' ";
  protected final String TEXT_803 = " is set." + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @return whether the value of the '<em>";
  protected final String TEXT_804 = "</em>' ";
  protected final String TEXT_805 = " is set.";
  protected final String TEXT_806 = NL + "\t * @see #unset";
  protected final String TEXT_807 = "()";
  protected final String TEXT_808 = NL + "\t * @see #";
  protected final String TEXT_809 = "()";
  protected final String TEXT_810 = NL + "\t * @see #set";
  protected final String TEXT_811 = "(";
  protected final String TEXT_812 = ")";
  protected final String TEXT_813 = NL + "\t * @generated" + NL + "\t */";
  protected final String TEXT_814 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */";
  protected final String TEXT_815 = NL + "\tboolean isSet";
  protected final String TEXT_816 = "();" + NL;
  protected final String TEXT_817 = NL + "\tpublic boolean isSet";
  protected final String TEXT_818 = "()" + NL + "\t{";
  protected final String TEXT_819 = NL + "\t\treturn eIsSet(";
  protected final String TEXT_820 = ");";
  protected final String TEXT_821 = NL + "\t\t";
  protected final String TEXT_822 = " ";
  protected final String TEXT_823 = " = (";
  protected final String TEXT_824 = ")eVirtualGet(";
  protected final String TEXT_825 = ");";
  protected final String TEXT_826 = NL + "\t\treturn ";
  protected final String TEXT_827 = " != null && ((";
  protected final String TEXT_828 = ".Unsettable)";
  protected final String TEXT_829 = ").isSet();";
  protected final String TEXT_830 = NL + "\t\treturn eVirtualIsSet(";
  protected final String TEXT_831 = ");";
  protected final String TEXT_832 = NL + "\t\treturn (";
  protected final String TEXT_833 = " & ";
  protected final String TEXT_834 = "_ESETFLAG) != 0;";
  protected final String TEXT_835 = NL + "\t\treturn ";
  protected final String TEXT_836 = "ESet;";
  protected final String TEXT_837 = NL + "\t\treturn !((";
  protected final String TEXT_838 = ".Internal)((";
  protected final String TEXT_839 = ".Internal.Wrapper)get";
  protected final String TEXT_840 = "()).featureMap()).isEmpty(";
  protected final String TEXT_841 = ");";
  protected final String TEXT_842 = NL + "\t\treturn !((";
  protected final String TEXT_843 = ".Internal)get";
  protected final String TEXT_844 = "()).isEmpty(";
  protected final String TEXT_845 = ");";
  protected final String TEXT_846 = NL + "\t\t// TODO: implement this method to return whether the '";
  protected final String TEXT_847 = "' ";
  protected final String TEXT_848 = " is set" + NL + "\t\t// Ensure that you remove @generated or mark it @generated NOT" + NL + "\t\tthrow new UnsupportedOperationException();";
  protected final String TEXT_849 = NL + "\t}" + NL;
  protected final String TEXT_850 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->";
  protected final String TEXT_851 = NL + "\t * <!-- begin-model-doc -->" + NL + "\t * ";
  protected final String TEXT_852 = NL + "\t * <!-- end-model-doc -->";
  protected final String TEXT_853 = NL + "\t * @model ";
  protected final String TEXT_854 = NL + "\t *        ";
  protected final String TEXT_855 = NL + "\t * @model";
  protected final String TEXT_856 = NL + "\t * @generated" + NL + "\t */";
  protected final String TEXT_857 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */";
  protected final String TEXT_858 = NL + "\t";
  protected final String TEXT_859 = " ";
  protected final String TEXT_860 = "(";
  protected final String TEXT_861 = ")";
  protected final String TEXT_862 = ";" + NL;
  protected final String TEXT_863 = NL + "\tpublic ";
  protected final String TEXT_864 = " ";
  protected final String TEXT_865 = "(";
  protected final String TEXT_866 = ")";
  protected final String TEXT_867 = NL + "\t{";
  protected final String TEXT_868 = NL + "\t\t";
  protected final String TEXT_869 = NL + "\t\t// TODO: implement this method" + NL + "\t\t// -> specify the condition that violates the invariant" + NL + "\t\t// -> verify the details of the diagnostic, including severity and message" + NL + "\t\t// Ensure that you remove @generated or mark it @generated NOT" + NL + "\t\tif (false)" + NL + "\t\t{" + NL + "\t\t\tif (";
  protected final String TEXT_870 = " != null)" + NL + "\t\t\t{" + NL + "\t\t\t\t";
  protected final String TEXT_871 = ".add" + NL + "\t\t\t\t\t(new ";
  protected final String TEXT_872 = NL + "\t\t\t\t\t\t(";
  protected final String TEXT_873 = ".ERROR," + NL + "\t\t\t\t\t\t ";
  protected final String TEXT_874 = ".DIAGNOSTIC_SOURCE," + NL + "\t\t\t\t\t\t ";
  protected final String TEXT_875 = ".";
  protected final String TEXT_876 = "," + NL + "\t\t\t\t\t\t ";
  protected final String TEXT_877 = ".INSTANCE.getString(\"_UI_GenericInvariant_diagnostic\", new Object[] { \"";
  protected final String TEXT_878 = "\", ";
  protected final String TEXT_879 = ".getObjectLabel(this, ";
  protected final String TEXT_880 = ") }),";
  protected final String TEXT_881 = NL + "\t\t\t\t\t\t new Object [] { this }));" + NL + "\t\t\t}" + NL + "\t\t\treturn false;" + NL + "\t\t}" + NL + "\t\treturn true;";
  protected final String TEXT_882 = NL + "\t\t// TODO: implement this method" + NL + "\t\t// Ensure that you remove @generated or mark it @generated NOT" + NL + "\t\tthrow new UnsupportedOperationException();";
  protected final String TEXT_883 = NL + "\t}" + NL;
  protected final String TEXT_884 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */" + NL + "\tpublic ";
  protected final String TEXT_885 = " eInverseAdd(";
  protected final String TEXT_886 = " otherEnd, int featureID, ";
  protected final String TEXT_887 = " msgs)" + NL + "\t{" + NL + "\t\tswitch (featureID)" + NL + "\t\t{";
  protected final String TEXT_888 = NL + "\t\t\tcase ";
  protected final String TEXT_889 = ":";
  protected final String TEXT_890 = NL + "\t\t\t\treturn ((";
  protected final String TEXT_891 = ")((";
  protected final String TEXT_892 = ".InternalMapView)";
  protected final String TEXT_893 = "()).eMap()).basicAdd(otherEnd, msgs);";
  protected final String TEXT_894 = NL + "\t\t\t\treturn ((";
  protected final String TEXT_895 = ")";
  protected final String TEXT_896 = "()).basicAdd(otherEnd, msgs);";
  protected final String TEXT_897 = NL + "\t\t\t\tif (eInternalContainer() != null)" + NL + "\t\t\t\t\tmsgs = eBasicRemoveFromContainer(msgs);" + NL + "\t\t\t\treturn eBasicSetContainer(otherEnd, ";
  protected final String TEXT_898 = ", msgs);";
  protected final String TEXT_899 = NL + "\t\t\t\t";
  protected final String TEXT_900 = " ";
  protected final String TEXT_901 = " = (";
  protected final String TEXT_902 = ")eVirtualGet(";
  protected final String TEXT_903 = ");";
  protected final String TEXT_904 = NL + "\t\t\t\tif (";
  protected final String TEXT_905 = " != null)";
  protected final String TEXT_906 = NL + "\t\t\t\t\tmsgs = ((";
  protected final String TEXT_907 = ")";
  protected final String TEXT_908 = ").eInverseRemove(this, EOPPOSITE_FEATURE_BASE - ";
  protected final String TEXT_909 = ", null, msgs);";
  protected final String TEXT_910 = NL + "\t\t\t\t\tmsgs = ((";
  protected final String TEXT_911 = ")";
  protected final String TEXT_912 = ").eInverseRemove(this, ";
  protected final String TEXT_913 = ", ";
  protected final String TEXT_914 = ".class, msgs);";
  protected final String TEXT_915 = NL + "\t\t\t\treturn basicSet";
  protected final String TEXT_916 = "((";
  protected final String TEXT_917 = ")otherEnd, msgs);";
  protected final String TEXT_918 = NL + "\t\t}";
  protected final String TEXT_919 = NL + "\t\treturn super.eInverseAdd(otherEnd, featureID, msgs);";
  protected final String TEXT_920 = NL + "\t\treturn eDynamicInverseAdd(otherEnd, featureID, msgs);";
  protected final String TEXT_921 = NL + "\t}" + NL;
  protected final String TEXT_922 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */" + NL + "\tpublic ";
  protected final String TEXT_923 = " eInverseRemove(";
  protected final String TEXT_924 = " otherEnd, int featureID, ";
  protected final String TEXT_925 = " msgs)" + NL + "\t{" + NL + "\t\tswitch (featureID)" + NL + "\t\t{";
  protected final String TEXT_926 = NL + "\t\t\tcase ";
  protected final String TEXT_927 = ":";
  protected final String TEXT_928 = NL + "\t\t\t\treturn ((";
  protected final String TEXT_929 = ")((";
  protected final String TEXT_930 = ".InternalMapView)";
  protected final String TEXT_931 = "()).eMap()).basicRemove(otherEnd, msgs);";
  protected final String TEXT_932 = NL + "\t\t\t\treturn ((";
  protected final String TEXT_933 = ")((";
  protected final String TEXT_934 = ".Internal.Wrapper)";
  protected final String TEXT_935 = "()).featureMap()).basicRemove(otherEnd, msgs);";
  protected final String TEXT_936 = NL + "\t\t\t\treturn ((";
  protected final String TEXT_937 = ")";
  protected final String TEXT_938 = "()).basicRemove(otherEnd, msgs);";
  protected final String TEXT_939 = NL + "\t\t\t\treturn eBasicSetContainer(null, ";
  protected final String TEXT_940 = ", msgs);";
  protected final String TEXT_941 = NL + "\t\t\t\treturn basicUnset";
  protected final String TEXT_942 = "(msgs);";
  protected final String TEXT_943 = NL + "\t\t\t\treturn basicSet";
  protected final String TEXT_944 = "(null, msgs);";
  protected final String TEXT_945 = NL + "\t\t}";
  protected final String TEXT_946 = NL + "\t\treturn super.eInverseRemove(otherEnd, featureID, msgs);";
  protected final String TEXT_947 = NL + "\t\treturn eDynamicInverseRemove(otherEnd, featureID, msgs);";
  protected final String TEXT_948 = NL + "\t}" + NL;
  protected final String TEXT_949 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */" + NL + "\tpublic ";
  protected final String TEXT_950 = " eBasicRemoveFromContainerFeature(";
  protected final String TEXT_951 = " msgs)" + NL + "\t{" + NL + "\t\tswitch (eContainerFeatureID)" + NL + "\t\t{";
  protected final String TEXT_952 = NL + "\t\t\tcase ";
  protected final String TEXT_953 = ":" + NL + "\t\t\t\treturn eInternalContainer().eInverseRemove(this, ";
  protected final String TEXT_954 = ", ";
  protected final String TEXT_955 = ".class, msgs);";
  protected final String TEXT_956 = NL + "\t\t}";
  protected final String TEXT_957 = NL + "\t\treturn super.eBasicRemoveFromContainerFeature(msgs);";
  protected final String TEXT_958 = NL + "\t\treturn eDynamicBasicRemoveFromContainer(msgs);";
  protected final String TEXT_959 = NL + "\t}" + NL;
  protected final String TEXT_960 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */" + NL + "\tpublic Object eGet(int featureID, boolean resolve, boolean coreType)" + NL + "\t{" + NL + "\t\tswitch (featureID)" + NL + "\t\t{";
  protected final String TEXT_961 = NL + "\t\t\tcase ";
  protected final String TEXT_962 = ":";
  protected final String TEXT_963 = NL + "\t\t\t\treturn ";
  protected final String TEXT_964 = "() ? Boolean.TRUE : Boolean.FALSE;";
  protected final String TEXT_965 = NL + "\t\t\t\treturn new ";
  protected final String TEXT_966 = "(";
  protected final String TEXT_967 = "());";
  protected final String TEXT_968 = NL + "\t\t\t\tif (resolve) return ";
  protected final String TEXT_969 = "();" + NL + "\t\t\t\treturn basicGet";
  protected final String TEXT_970 = "();";
  protected final String TEXT_971 = NL + "\t\t\t\tif (coreType) return ((";
  protected final String TEXT_972 = ".InternalMapView)";
  protected final String TEXT_973 = "()).eMap();" + NL + "\t\t\t\telse return ";
  protected final String TEXT_974 = "();";
  protected final String TEXT_975 = NL + "\t\t\t\tif (coreType) return ";
  protected final String TEXT_976 = "();" + NL + "\t\t\t\telse return ";
  protected final String TEXT_977 = "().map();";
  protected final String TEXT_978 = NL + "\t\t\t\tif (coreType) return ((";
  protected final String TEXT_979 = ".Internal.Wrapper)";
  protected final String TEXT_980 = "()).featureMap();" + NL + "\t\t\t\treturn ";
  protected final String TEXT_981 = "();";
  protected final String TEXT_982 = NL + "\t\t\t\tif (coreType) return ";
  protected final String TEXT_983 = "();" + NL + "\t\t\t\treturn ((";
  protected final String TEXT_984 = ".Internal)";
  protected final String TEXT_985 = "()).getWrapper();";
  protected final String TEXT_986 = NL + "\t\t\t\treturn ";
  protected final String TEXT_987 = "();";
  protected final String TEXT_988 = NL + "\t\t}";
  protected final String TEXT_989 = NL + "\t\treturn super.eGet(featureID, resolve, coreType);";
  protected final String TEXT_990 = NL + "\t\treturn eDynamicGet(featureID, resolve, coreType);";
  protected final String TEXT_991 = NL + "\t}" + NL;
  protected final String TEXT_992 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */" + NL + "\tpublic void eSet(int featureID, Object newValue)" + NL + "\t{" + NL + "\t\tswitch (featureID)" + NL + "\t\t{";
  protected final String TEXT_993 = NL + "\t\t\tcase ";
  protected final String TEXT_994 = ":";
  protected final String TEXT_995 = NL + "\t\t\t\t((";
  protected final String TEXT_996 = ".Internal)((";
  protected final String TEXT_997 = ".Internal.Wrapper)";
  protected final String TEXT_998 = "()).featureMap()).set(newValue);";
  protected final String TEXT_999 = NL + "\t\t\t\t((";
  protected final String TEXT_1000 = ".Internal)";
  protected final String TEXT_1001 = "()).set(newValue);";
  protected final String TEXT_1002 = NL + "\t\t\t\t((";
  protected final String TEXT_1003 = ".Setting)((";
  protected final String TEXT_1004 = ".InternalMapView)";
  protected final String TEXT_1005 = "()).eMap()).set(newValue);";
  protected final String TEXT_1006 = NL + "\t\t\t\t((";
  protected final String TEXT_1007 = ".Setting)";
  protected final String TEXT_1008 = "()).set(newValue);";
  protected final String TEXT_1009 = NL + "\t\t\t\t";
  protected final String TEXT_1010 = "().clear();" + NL + "\t\t\t\t";
  protected final String TEXT_1011 = "().addAll((";
  protected final String TEXT_1012 = ")newValue);";
  protected final String TEXT_1013 = NL + "\t\t\t\tset";
  protected final String TEXT_1014 = "(((";
  protected final String TEXT_1015 = ")newValue).";
  protected final String TEXT_1016 = "());";
  protected final String TEXT_1017 = NL + "\t\t\t\tset";
  protected final String TEXT_1018 = "((";
  protected final String TEXT_1019 = ")newValue);";
  protected final String TEXT_1020 = NL + "\t\t\t\treturn;";
  protected final String TEXT_1021 = NL + "\t\t}";
  protected final String TEXT_1022 = NL + "\t\tsuper.eSet(featureID, newValue);";
  protected final String TEXT_1023 = NL + "\t\teDynamicSet(featureID, newValue);";
  protected final String TEXT_1024 = NL + "\t}" + NL + "" + NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */" + NL + "\tpublic void eUnset(int featureID)" + NL + "\t{" + NL + "\t\tswitch (featureID)" + NL + "\t\t{";
  protected final String TEXT_1025 = NL + "\t\t\tcase ";
  protected final String TEXT_1026 = ":";
  protected final String TEXT_1027 = NL + "\t\t\t\t((";
  protected final String TEXT_1028 = ".Internal.Wrapper)";
  protected final String TEXT_1029 = "()).featureMap().clear();";
  protected final String TEXT_1030 = NL + "\t\t\t\t";
  protected final String TEXT_1031 = "().clear();";
  protected final String TEXT_1032 = NL + "\t\t\t\tunset";
  protected final String TEXT_1033 = "();";
  protected final String TEXT_1034 = NL + "\t\t\t\tset";
  protected final String TEXT_1035 = "((";
  protected final String TEXT_1036 = ")null);";
  protected final String TEXT_1037 = NL + "\t\t\t\tset";
  protected final String TEXT_1038 = "(";
  protected final String TEXT_1039 = "_EDEFAULT);";
  protected final String TEXT_1040 = NL + "\t\t\t\treturn;";
  protected final String TEXT_1041 = NL + "\t\t}";
  protected final String TEXT_1042 = NL + "\t\tsuper.eUnset(featureID);";
  protected final String TEXT_1043 = NL + "\t\teDynamicUnset(featureID);";
  protected final String TEXT_1044 = NL + "\t}" + NL;
  protected final String TEXT_1045 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */" + NL + "\tpublic boolean eIsSet(int featureID)" + NL + "\t{" + NL + "\t\tswitch (featureID)" + NL + "\t\t{";
  protected final String TEXT_1046 = NL + "\t\t\tcase ";
  protected final String TEXT_1047 = ":";
  protected final String TEXT_1048 = NL + "\t\t\t\treturn !((";
  protected final String TEXT_1049 = ".Internal.Wrapper)";
  protected final String TEXT_1050 = "()).featureMap().isEmpty();";
  protected final String TEXT_1051 = NL + "\t\t\t\treturn ";
  protected final String TEXT_1052 = " != null && !";
  protected final String TEXT_1053 = ".featureMap().isEmpty();";
  protected final String TEXT_1054 = NL + "\t\t\t\treturn ";
  protected final String TEXT_1055 = " != null && !";
  protected final String TEXT_1056 = ".isEmpty();";
  protected final String TEXT_1057 = NL + "\t\t\t\t";
  protected final String TEXT_1058 = " ";
  protected final String TEXT_1059 = " = (";
  protected final String TEXT_1060 = ")eVirtualGet(";
  protected final String TEXT_1061 = ");" + NL + "\t\t\t\treturn ";
  protected final String TEXT_1062 = " != null && !";
  protected final String TEXT_1063 = ".isEmpty();";
  protected final String TEXT_1064 = NL + "\t\t\t\treturn !";
  protected final String TEXT_1065 = "().isEmpty();";
  protected final String TEXT_1066 = NL + "\t\t\t\treturn isSet";
  protected final String TEXT_1067 = "();";
  protected final String TEXT_1068 = NL + "\t\t\t\treturn ";
  protected final String TEXT_1069 = " != null;";
  protected final String TEXT_1070 = NL + "\t\t\t\treturn eVirtualGet(";
  protected final String TEXT_1071 = ") != null;";
  protected final String TEXT_1072 = NL + "\t\t\t\treturn basicGet";
  protected final String TEXT_1073 = "() != null;";
  protected final String TEXT_1074 = NL + "\t\t\t\treturn ";
  protected final String TEXT_1075 = " != null;";
  protected final String TEXT_1076 = NL + "\t\t\t\treturn eVirtualGet(";
  protected final String TEXT_1077 = ") != null;";
  protected final String TEXT_1078 = NL + "\t\t\t\treturn ";
  protected final String TEXT_1079 = "() != null;";
  protected final String TEXT_1080 = NL + "\t\t\t\treturn ((";
  protected final String TEXT_1081 = " & ";
  protected final String TEXT_1082 = "_EFLAG) != 0) != ";
  protected final String TEXT_1083 = "_EDEFAULT;";
  protected final String TEXT_1084 = NL + "\t\t\t\treturn ";
  protected final String TEXT_1085 = " != ";
  protected final String TEXT_1086 = "_EDEFAULT;";
  protected final String TEXT_1087 = NL + "\t\t\t\treturn eVirtualGet(";
  protected final String TEXT_1088 = ", ";
  protected final String TEXT_1089 = "_EDEFAULT) != ";
  protected final String TEXT_1090 = "_EDEFAULT;";
  protected final String TEXT_1091 = NL + "\t\t\t\treturn ";
  protected final String TEXT_1092 = "() != ";
  protected final String TEXT_1093 = "_EDEFAULT;";
  protected final String TEXT_1094 = NL + "\t\t\t\treturn ";
  protected final String TEXT_1095 = "_EDEFAULT == null ? ";
  protected final String TEXT_1096 = " != null : !";
  protected final String TEXT_1097 = "_EDEFAULT.equals(";
  protected final String TEXT_1098 = ");";
  protected final String TEXT_1099 = NL + "\t\t\t\t";
  protected final String TEXT_1100 = " ";
  protected final String TEXT_1101 = " = (";
  protected final String TEXT_1102 = ")eVirtualGet(";
  protected final String TEXT_1103 = ", ";
  protected final String TEXT_1104 = "_EDEFAULT);" + NL + "\t\t\t\treturn ";
  protected final String TEXT_1105 = "_EDEFAULT == null ? ";
  protected final String TEXT_1106 = " != null : !";
  protected final String TEXT_1107 = "_EDEFAULT.equals(";
  protected final String TEXT_1108 = ");";
  protected final String TEXT_1109 = NL + "\t\t\t\treturn ";
  protected final String TEXT_1110 = "_EDEFAULT == null ? ";
  protected final String TEXT_1111 = "() != null : !";
  protected final String TEXT_1112 = "_EDEFAULT.equals(";
  protected final String TEXT_1113 = "());";
  protected final String TEXT_1114 = NL + "\t\t}";
  protected final String TEXT_1115 = NL + "\t\treturn super.eIsSet(featureID);";
  protected final String TEXT_1116 = NL + "\t\treturn eDynamicIsSet(featureID);";
  protected final String TEXT_1117 = NL + "\t}" + NL;
  protected final String TEXT_1118 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */" + NL + "\tpublic int eBaseStructuralFeatureID(int derivedFeatureID, Class baseClass)" + NL + "\t{";
  protected final String TEXT_1119 = NL + "\t\tif (baseClass == ";
  protected final String TEXT_1120 = ".class)" + NL + "\t\t{" + NL + "\t\t\tswitch (derivedFeatureID)" + NL + "\t\t\t{";
  protected final String TEXT_1121 = NL + "\t\t\t\tcase ";
  protected final String TEXT_1122 = ": return ";
  protected final String TEXT_1123 = ";";
  protected final String TEXT_1124 = NL + "\t\t\t\tdefault: return -1;" + NL + "\t\t\t}" + NL + "\t\t}";
  protected final String TEXT_1125 = NL + "\t\treturn super.eBaseStructuralFeatureID(derivedFeatureID, baseClass);" + NL + "\t}" + NL + "" + NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */" + NL + "\tpublic int eDerivedStructuralFeatureID(int baseFeatureID, Class baseClass)" + NL + "\t{";
  protected final String TEXT_1126 = NL + "\t\tif (baseClass == ";
  protected final String TEXT_1127 = ".class)" + NL + "\t\t{" + NL + "\t\t\tswitch (baseFeatureID)" + NL + "\t\t\t{";
  protected final String TEXT_1128 = NL + "\t\t\t\tcase ";
  protected final String TEXT_1129 = ": return ";
  protected final String TEXT_1130 = ";";
  protected final String TEXT_1131 = NL + "\t\t\t\tdefault: return -1;" + NL + "\t\t\t}" + NL + "\t\t}";
  protected final String TEXT_1132 = NL + "\t\treturn super.eDerivedStructuralFeatureID(baseFeatureID, baseClass);" + NL + "\t}" + NL;
  protected final String TEXT_1133 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */" + NL + "\tprotected Object[] eVirtualValues()" + NL + "\t{" + NL + "\t\treturn ";
  protected final String TEXT_1134 = ";" + NL + "\t}" + NL + "" + NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */" + NL + "\tprotected void eSetVirtualValues(Object[] newValues)" + NL + "\t{" + NL + "\t\t";
  protected final String TEXT_1135 = " = newValues;" + NL + "\t}" + NL;
  protected final String TEXT_1136 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */" + NL + "\tprotected int eVirtualIndexBits(int offset)" + NL + "\t{" + NL + "\t\tswitch (offset)" + NL + "\t\t{";
  protected final String TEXT_1137 = NL + "\t\t\tcase ";
  protected final String TEXT_1138 = " :" + NL + "\t\t\t\treturn ";
  protected final String TEXT_1139 = ";";
  protected final String TEXT_1140 = NL + "\t\t\tdefault :" + NL + "\t\t\t\tthrow new IndexOutOfBoundsException();" + NL + "\t\t}" + NL + "\t}" + NL + "" + NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */" + NL + "\tprotected void eSetVirtualIndexBits(int offset, int newIndexBits)" + NL + "\t{" + NL + "\t\tswitch (offset)" + NL + "\t\t{";
  protected final String TEXT_1141 = NL + "\t\t\tcase ";
  protected final String TEXT_1142 = " :" + NL + "\t\t\t\t";
  protected final String TEXT_1143 = " = newIndexBits;" + NL + "\t\t\t\tbreak;";
  protected final String TEXT_1144 = NL + "\t\t\tdefault :" + NL + "\t\t\t\tthrow new IndexOutOfBoundsException();" + NL + "\t\t}" + NL + "\t}" + NL;
  protected final String TEXT_1145 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */" + NL + "\tpublic String toString()" + NL + "\t{" + NL + "\t\tif (eIsProxy()) return super.toString();" + NL + "" + NL + "\t\tStringBuffer result = new StringBuffer(super.toString());";
  protected final String TEXT_1146 = NL + "\t\tresult.append(\" (";
  protected final String TEXT_1147 = ": \");";
  protected final String TEXT_1148 = NL + "\t\tresult.append(\", ";
  protected final String TEXT_1149 = ": \");";
  protected final String TEXT_1150 = NL + "\t\tif (eVirtualIsSet(";
  protected final String TEXT_1151 = ")) result.append(eVirtualGet(";
  protected final String TEXT_1152 = ")); else result.append(\"<unset>\");";
  protected final String TEXT_1153 = NL + "\t\tif (";
  protected final String TEXT_1154 = "(";
  protected final String TEXT_1155 = " & ";
  protected final String TEXT_1156 = "_ESETFLAG) != 0";
  protected final String TEXT_1157 = "ESet";
  protected final String TEXT_1158 = ") result.append((";
  protected final String TEXT_1159 = " & ";
  protected final String TEXT_1160 = "_EFLAG) != 0); else result.append(\"<unset>\");";
  protected final String TEXT_1161 = NL + "\t\tif (";
  protected final String TEXT_1162 = "(";
  protected final String TEXT_1163 = " & ";
  protected final String TEXT_1164 = "_ESETFLAG) != 0";
  protected final String TEXT_1165 = "ESet";
  protected final String TEXT_1166 = ") result.append(";
  protected final String TEXT_1167 = "); else result.append(\"<unset>\");";
  protected final String TEXT_1168 = NL + "\t\tresult.append(eVirtualGet(";
  protected final String TEXT_1169 = ", ";
  protected final String TEXT_1170 = "_EDEFAULT";
  protected final String TEXT_1171 = "));";
  protected final String TEXT_1172 = NL + "\t\tresult.append((";
  protected final String TEXT_1173 = " & ";
  protected final String TEXT_1174 = "_EFLAG) != 0);";
  protected final String TEXT_1175 = NL + "\t\tresult.append(";
  protected final String TEXT_1176 = ");";
  protected final String TEXT_1177 = NL + "\t\tresult.append(')');" + NL + "\t\treturn result.toString();" + NL + "\t}" + NL;
  protected final String TEXT_1178 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */" + NL + "\tprotected int hash = -1;" + NL + "" + NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */" + NL + "\tpublic int getHash()" + NL + "\t{" + NL + "\t\tif (hash == -1)" + NL + "\t\t{" + NL + "\t\t\tObject theKey = getKey();" + NL + "\t\t\thash = (theKey == null ? 0 : theKey.hashCode());" + NL + "\t\t}" + NL + "\t\treturn hash;" + NL + "\t}" + NL + "" + NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */" + NL + "\tpublic void setHash(int hash)" + NL + "\t{" + NL + "\t\tthis.hash = hash;" + NL + "\t}" + NL + "" + NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */" + NL + "\tpublic Object getKey()" + NL + "\t{";
  protected final String TEXT_1179 = NL + "\t\treturn new ";
  protected final String TEXT_1180 = "(getTypedKey());";
  protected final String TEXT_1181 = NL + "\t\treturn getTypedKey();";
  protected final String TEXT_1182 = NL + "\t}" + NL + "" + NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */" + NL + "\tpublic void setKey(Object key)" + NL + "\t{";
  protected final String TEXT_1183 = NL + "\t\tgetTypedKey().addAll((";
  protected final String TEXT_1184 = ")key);";
  protected final String TEXT_1185 = NL + "\t\tsetTypedKey(((";
  protected final String TEXT_1186 = ")key).";
  protected final String TEXT_1187 = "());";
  protected final String TEXT_1188 = NL + "\t\tsetTypedKey((";
  protected final String TEXT_1189 = ")key);";
  protected final String TEXT_1190 = NL + "\t}" + NL + "" + NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */" + NL + "\tpublic Object getValue()" + NL + "\t{";
  protected final String TEXT_1191 = NL + "\t\treturn new ";
  protected final String TEXT_1192 = "(getTypedValue());";
  protected final String TEXT_1193 = NL + "\t\treturn getTypedValue();";
  protected final String TEXT_1194 = NL + "\t}" + NL + "" + NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */" + NL + "\tpublic Object setValue(Object value)" + NL + "\t{" + NL + "\t\tObject oldValue = getValue();";
  protected final String TEXT_1195 = NL + "\t\tgetTypedValue().clear();" + NL + "\t\tgetTypedValue().addAll((";
  protected final String TEXT_1196 = ")value);";
  protected final String TEXT_1197 = NL + "\t\tsetTypedValue(((";
  protected final String TEXT_1198 = ")value).";
  protected final String TEXT_1199 = "());";
  protected final String TEXT_1200 = NL + "\t\tsetTypedValue((";
  protected final String TEXT_1201 = ")value);";
  protected final String TEXT_1202 = NL + "\t\treturn oldValue;" + NL + "\t}" + NL + "" + NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */" + NL + "\tpublic ";
  protected final String TEXT_1203 = " getEMap()" + NL + "\t{" + NL + "\t\t";
  protected final String TEXT_1204 = " container = eContainer();" + NL + "\t\treturn container == null ? null : (";
  protected final String TEXT_1205 = ")container.eGet(eContainmentFeature());" + NL + "\t}" + NL;
  protected final String TEXT_1206 = NL + "} //";
  protected final String TEXT_1207 = NL;

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

    GenClass genClass = (GenClass)((Object[])argument)[0]; GenPackage genPackage = genClass.getGenPackage(); GenModel genModel=genPackage.getGenModel();
    boolean isInterface = Boolean.TRUE.equals(((Object[])argument)[1]); boolean isImplementation = Boolean.TRUE.equals(((Object[])argument)[2]);
    String publicStaticFinalFlag = isImplementation ? "public static final " : "";
    stringBuffer.append(TEXT_1);
    stringBuffer.append(TEXT_2);
    stringBuffer.append("$");
    stringBuffer.append(TEXT_3);
    stringBuffer.append("$");
    stringBuffer.append(TEXT_4);
    if (isInterface) {
    stringBuffer.append(TEXT_5);
    stringBuffer.append(genPackage.getInterfacePackageName());
    stringBuffer.append(TEXT_6);
    } else {
    stringBuffer.append(TEXT_7);
    stringBuffer.append(genPackage.getClassPackageName());
    stringBuffer.append(TEXT_8);
    }
    stringBuffer.append(TEXT_9);
    genModel.markImportLocation(stringBuffer, genPackage);
    stringBuffer.append(TEXT_10);
    if (isInterface) {
    stringBuffer.append(TEXT_11);
    stringBuffer.append(genClass.getFormattedName());
    stringBuffer.append(TEXT_12);
    if (genClass.hasDocumentation()) {
    stringBuffer.append(TEXT_13);
    stringBuffer.append(genClass.getDocumentation(genModel.getIndentation(stringBuffer)));
    stringBuffer.append(TEXT_14);
    }
    stringBuffer.append(TEXT_15);
    if (!genClass.getGenFeatures().isEmpty()) {
    stringBuffer.append(TEXT_16);
    for (Iterator i=genClass.getGenFeatures().iterator(); i.hasNext();) { GenFeature genFeature = (GenFeature)i.next();
    if (!genFeature.isSuppressedGetVisibility()) {
    stringBuffer.append(TEXT_17);
    stringBuffer.append(genClass.getQualifiedInterfaceName());
    stringBuffer.append(TEXT_18);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_19);
    stringBuffer.append(genFeature.getFormattedName());
    stringBuffer.append(TEXT_20);
    }
    }
    stringBuffer.append(TEXT_21);
    }
    stringBuffer.append(TEXT_22);
    if (!genModel.isSuppressEMFMetaData()) {
    stringBuffer.append(TEXT_23);
    stringBuffer.append(genPackage.getQualifiedPackageInterfaceName());
    stringBuffer.append(TEXT_24);
    stringBuffer.append(genClass.getClassifierAccessorName());
    stringBuffer.append(TEXT_25);
    }
    if (!genModel.isSuppressEMFModelTags()) { boolean first = true; for (StringTokenizer stringTokenizer = new StringTokenizer(genClass.getModelInfo(), "\n\r"); stringTokenizer.hasMoreTokens(); ) { String modelInfo = stringTokenizer.nextToken(); if (first) { first = false;
    stringBuffer.append(TEXT_26);
    stringBuffer.append(modelInfo);
    } else {
    stringBuffer.append(TEXT_27);
    stringBuffer.append(modelInfo);
    }} if (first) {
    stringBuffer.append(TEXT_28);
    }}
    if (genClass.needsRootExtendsInterfaceExtendsTag()) {
    stringBuffer.append(TEXT_29);
    stringBuffer.append(genModel.getImportedName(genModel.getRootExtendsInterface()));
    }
    stringBuffer.append(TEXT_30);
    } else {
    stringBuffer.append(TEXT_31);
    stringBuffer.append(genClass.getFormattedName());
    stringBuffer.append(TEXT_32);
    if (!genClass.getImplementedGenFeatures().isEmpty()) {
    stringBuffer.append(TEXT_33);
    for (Iterator i=genClass.getImplementedGenFeatures().iterator(); i.hasNext();) { GenFeature genFeature = (GenFeature)i.next();
    stringBuffer.append(TEXT_34);
    stringBuffer.append(genClass.getQualifiedClassName());
    stringBuffer.append(TEXT_35);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_36);
    stringBuffer.append(genFeature.getFormattedName());
    stringBuffer.append(TEXT_37);
    }
    stringBuffer.append(TEXT_38);
    }
    stringBuffer.append(TEXT_39);
    }
    if (isImplementation) {
    stringBuffer.append(TEXT_40);
    if (genClass.isAbstract()) {
    stringBuffer.append(TEXT_41);
    }
    stringBuffer.append(TEXT_42);
    stringBuffer.append(genClass.getClassName());
    stringBuffer.append(genClass.getClassExtends());
    stringBuffer.append(genClass.getClassImplements());
    } else {
    stringBuffer.append(TEXT_43);
    stringBuffer.append(genClass.getInterfaceName());
    stringBuffer.append(genClass.getInterfaceExtends());
    }
    stringBuffer.append(TEXT_44);
    if (genModel.getCopyrightText() != null) {
    stringBuffer.append(TEXT_45);
    stringBuffer.append(publicStaticFinalFlag);
    stringBuffer.append(genModel.getImportedName("java.lang.String"));
    stringBuffer.append(TEXT_46);
    stringBuffer.append(genModel.getCopyrightText());
    stringBuffer.append(TEXT_47);
    stringBuffer.append(genModel.getNonNLS());
    stringBuffer.append(TEXT_48);
    }
    if (isImplementation && genModel.getDriverNumber() != null) {
    stringBuffer.append(TEXT_49);
    stringBuffer.append(genModel.getImportedName("java.lang.String"));
    stringBuffer.append(TEXT_50);
    stringBuffer.append(genModel.getDriverNumber());
    stringBuffer.append(TEXT_51);
    stringBuffer.append(genModel.getNonNLS());
    stringBuffer.append(TEXT_52);
    }
    if (isImplementation && genClass.isJavaIOSerializable()) {
    stringBuffer.append(TEXT_53);
    }
    if (isImplementation && genModel.isVirtualDelegation()) { String eVirtualValuesField = genClass.getEVirtualValuesField();
    if (eVirtualValuesField != null) {
    stringBuffer.append(TEXT_54);
    stringBuffer.append(eVirtualValuesField);
    stringBuffer.append(TEXT_55);
    }
    { List eVirtualIndexBitFields = genClass.getEVirtualIndexBitFields(new ArrayList());
    if (!eVirtualIndexBitFields.isEmpty()) {
    for (Iterator i = eVirtualIndexBitFields.iterator(); i.hasNext();) { String eVirtualIndexBitField = (String)i.next();
    stringBuffer.append(TEXT_56);
    stringBuffer.append(eVirtualIndexBitField);
    stringBuffer.append(TEXT_57);
    }
    }
    }
    }
    if (isImplementation && genClass.isModelRoot() && genModel.isBooleanFlagsEnabled() && genModel.getBooleanFlagsReservedBits() == -1) {
    stringBuffer.append(TEXT_58);
    stringBuffer.append(genModel.getBooleanFlagsField());
    stringBuffer.append(TEXT_59);
    }
    if (isImplementation && !genModel.isReflectiveDelegation()) {
    for (Iterator i=genClass.getDeclaredFieldGenFeatures().iterator(); i.hasNext();) { GenFeature genFeature = (GenFeature)i.next();
    if (genFeature.isListType() || genFeature.isReferenceType()) {
    if (genClass.isField(genFeature)) {
    stringBuffer.append(TEXT_60);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_61);
    stringBuffer.append(genFeature.getFormattedName());
    stringBuffer.append(TEXT_62);
    stringBuffer.append(genFeature.getFeatureKind());
    stringBuffer.append(TEXT_63);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_64);
    stringBuffer.append(genFeature.getImportedInternalType());
    stringBuffer.append(TEXT_65);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_66);
    }
    } else {
    if (!genFeature.isVolatile() || !genModel.isReflectiveDelegation() && (!genFeature.hasDelegateFeature() || !genFeature.isUnsettable())) {
    stringBuffer.append(TEXT_67);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_68);
    stringBuffer.append(genFeature.getFormattedName());
    stringBuffer.append(TEXT_69);
    stringBuffer.append(genFeature.getFeatureKind());
    stringBuffer.append(TEXT_70);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_71);
    stringBuffer.append(genFeature.getImportedType());
    stringBuffer.append(TEXT_72);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_73);
    stringBuffer.append(genFeature.getStaticDefaultValue());
    stringBuffer.append(TEXT_74);
    stringBuffer.append(genModel.getNonNLS(genFeature.getStaticDefaultValue()));
    stringBuffer.append(TEXT_75);
    }
    if (genClass.isField(genFeature)) {
    if (genClass.isFlag(genFeature)) {
    if (genClass.getFlagIndex(genFeature) > 31 && genClass.getFlagIndex(genFeature) % 32 == 0) {
    stringBuffer.append(TEXT_76);
    stringBuffer.append(genClass.getFlagsField(genFeature));
    stringBuffer.append(TEXT_77);
    }
    stringBuffer.append(TEXT_78);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_79);
    stringBuffer.append(genFeature.getFormattedName());
    stringBuffer.append(TEXT_80);
    stringBuffer.append(genFeature.getFeatureKind());
    stringBuffer.append(TEXT_81);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_82);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_83);
    stringBuffer.append("<< " + genClass.getFlagIndex(genFeature) % 32 );
    stringBuffer.append(TEXT_84);
    } else {
    stringBuffer.append(TEXT_85);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_86);
    stringBuffer.append(genFeature.getFormattedName());
    stringBuffer.append(TEXT_87);
    stringBuffer.append(genFeature.getFeatureKind());
    stringBuffer.append(TEXT_88);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_89);
    stringBuffer.append(genFeature.getImportedType());
    stringBuffer.append(TEXT_90);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_91);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_92);
    }
    }
    }
    if (genClass.isESetField(genFeature)) {
    if (genClass.isESetFlag(genFeature)) {
    if (genClass.getESetFlagIndex(genFeature) > 31 && genClass.getESetFlagIndex(genFeature) % 32 == 0) {
    stringBuffer.append(TEXT_93);
    stringBuffer.append(genClass.getESetFlagsField(genFeature));
    stringBuffer.append(TEXT_94);
    }
    stringBuffer.append(TEXT_95);
    stringBuffer.append(genFeature.getFormattedName());
    stringBuffer.append(TEXT_96);
    stringBuffer.append(genFeature.getFeatureKind());
    stringBuffer.append(TEXT_97);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_98);
    stringBuffer.append("<< " + genClass.getESetFlagIndex(genFeature) % 32 );
    stringBuffer.append(TEXT_99);
    } else {
    stringBuffer.append(TEXT_100);
    stringBuffer.append(genFeature.getFormattedName());
    stringBuffer.append(TEXT_101);
    stringBuffer.append(genFeature.getFeatureKind());
    stringBuffer.append(TEXT_102);
    stringBuffer.append(genFeature.getUncapName());
    stringBuffer.append(TEXT_103);
    }
    }
    }
    //Class/declaredFieldGenFeature.override.javajetinc
    }
    if (isImplementation) {
    stringBuffer.append(TEXT_104);
    stringBuffer.append(genClass.getClassName());
    stringBuffer.append(TEXT_105);
    for (Iterator i=genClass.getFlagGenFeatures("true").iterator(); i.hasNext();) { GenFeature genFeature = (GenFeature)i.next();
    stringBuffer.append(TEXT_106);
    stringBuffer.append(genClass.getFlagsField(genFeature));
    stringBuffer.append(TEXT_107);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_108);
    }
    stringBuffer.append(TEXT_109);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.EClass"));
    stringBuffer.append(TEXT_110);
    stringBuffer.append(genClass.getQualifiedClassifierAccessor());
    stringBuffer.append(TEXT_111);
    }
    for (Iterator i=(isImplementation ? genClass.getImplementedGenFeatures() : genClass.getDeclaredGenFeatures()).iterator(); i.hasNext();) { GenFeature genFeature = (GenFeature)i.next();
    if (genFeature.isGet() && (isImplementation || !genFeature.isSuppressedGetVisibility())) {
    if (isInterface) {
    stringBuffer.append(TEXT_112);
    stringBuffer.append(genFeature.getFormattedName());
    stringBuffer.append(TEXT_113);
    stringBuffer.append(genFeature.getFeatureKind());
    stringBuffer.append(TEXT_114);
    if (genFeature.isListType()) {
    if (genFeature.isMapType()) { GenFeature keyFeature = genFeature.getMapEntryTypeGenClass().getMapEntryKeyFeature(); GenFeature valueFeature = genFeature.getMapEntryTypeGenClass().getMapEntryValueFeature(); 
    stringBuffer.append(TEXT_115);
    if (keyFeature.isListType()) {
    stringBuffer.append(TEXT_116);
    stringBuffer.append(keyFeature.getQualifiedListItemType());
    stringBuffer.append(TEXT_117);
    } else {
    stringBuffer.append(TEXT_118);
    stringBuffer.append(keyFeature.getType());
    stringBuffer.append(TEXT_119);
    }
    stringBuffer.append(TEXT_120);
    if (valueFeature.isListType()) {
    stringBuffer.append(TEXT_121);
    stringBuffer.append(valueFeature.getQualifiedListItemType());
    stringBuffer.append(TEXT_122);
    } else {
    stringBuffer.append(TEXT_123);
    stringBuffer.append(valueFeature.getType());
    stringBuffer.append(TEXT_124);
    }
    stringBuffer.append(TEXT_125);
    } else if (!genFeature.isWrappedFeatureMapType() && !(genModel.isSuppressEMFMetaData() && "org.eclipse.emf.ecore.EObject".equals(genFeature.getQualifiedListItemType()))) {
    stringBuffer.append(TEXT_126);
    stringBuffer.append(genFeature.getQualifiedListItemType());
    stringBuffer.append(TEXT_127);
    }
    } else if (genFeature.isSetDefaultValue()) {
    stringBuffer.append(TEXT_128);
    stringBuffer.append(genFeature.getDefaultValue());
    stringBuffer.append(TEXT_129);
    }
    if (genFeature.getTypeGenEnum() != null) {
    stringBuffer.append(TEXT_130);
    stringBuffer.append(genFeature.getTypeGenEnum().getQualifiedName());
    stringBuffer.append(TEXT_131);
    }
    if (genFeature.isBidirectional() && !genFeature.getReverse().getGenClass().isMapEntry()) { GenFeature reverseGenFeature = genFeature.getReverse(); 
    if (!reverseGenFeature.isSuppressedGetVisibility()) {
    stringBuffer.append(TEXT_132);
    stringBuffer.append(reverseGenFeature.getGenClass().getQualifiedInterfaceName());
    stringBuffer.append(TEXT_133);
    stringBuffer.append(reverseGenFeature.getGetAccessor());
    stringBuffer.append(TEXT_134);
    stringBuffer.append(reverseGenFeature.getFormattedName());
    stringBuffer.append(TEXT_135);
    }
    }
    stringBuffer.append(TEXT_136);
    if (!genFeature.hasDocumentation()) {
    stringBuffer.append(TEXT_137);
    stringBuffer.append(genFeature.getFormattedName());
    stringBuffer.append(TEXT_138);
    stringBuffer.append(genFeature.getFeatureKind());
    stringBuffer.append(TEXT_139);
    }
    stringBuffer.append(TEXT_140);
    if (genFeature.hasDocumentation()) {
    stringBuffer.append(TEXT_141);
    stringBuffer.append(genFeature.getDocumentation(genModel.getIndentation(stringBuffer)));
    stringBuffer.append(TEXT_142);
    }
    stringBuffer.append(TEXT_143);
    stringBuffer.append(genFeature.getFormattedName());
    stringBuffer.append(TEXT_144);
    stringBuffer.append(genFeature.getFeatureKind());
    stringBuffer.append(TEXT_145);
    if (genFeature.getTypeGenEnum() != null) {
    stringBuffer.append(TEXT_146);
    stringBuffer.append(genFeature.getTypeGenEnum().getQualifiedName());
    }
    if (genFeature.isUnsettable()) {
    if (!genFeature.isSuppressedIsSetVisibility()) {
    stringBuffer.append(TEXT_147);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_148);
    }
    if (genFeature.isChangeable() && !genFeature.isSuppressedUnsetVisibility()) {
    stringBuffer.append(TEXT_149);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_150);
    }
    }
    if (genFeature.isChangeable() && !genFeature.isListType() && !genFeature.isSuppressedSetVisibility()) {
    stringBuffer.append(TEXT_151);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_152);
    stringBuffer.append(genFeature.getImportedType());
    stringBuffer.append(TEXT_153);
    }
    if (!genModel.isSuppressEMFMetaData()) {
    stringBuffer.append(TEXT_154);
    stringBuffer.append(genPackage.getQualifiedPackageInterfaceName());
    stringBuffer.append(TEXT_155);
    stringBuffer.append(genFeature.getFeatureAccessorName());
    stringBuffer.append(TEXT_156);
    }
    if (genFeature.isBidirectional() && !genFeature.getReverse().getGenClass().isMapEntry()) { GenFeature reverseGenFeature = genFeature.getReverse(); 
    if (!reverseGenFeature.isSuppressedGetVisibility()) {
    stringBuffer.append(TEXT_157);
    stringBuffer.append(reverseGenFeature.getGenClass().getQualifiedInterfaceName());
    stringBuffer.append(TEXT_158);
    stringBuffer.append(reverseGenFeature.getGetAccessor());
    }
    }
    if (!genModel.isSuppressEMFModelTags()) { boolean first = true; for (StringTokenizer stringTokenizer = new StringTokenizer(genFeature.getModelInfo(), "\n\r"); stringTokenizer.hasMoreTokens(); ) { String modelInfo = stringTokenizer.nextToken(); if (first) { first = false;
    stringBuffer.append(TEXT_159);
    stringBuffer.append(modelInfo);
    } else {
    stringBuffer.append(TEXT_160);
    stringBuffer.append(modelInfo);
    }} if (first) {
    stringBuffer.append(TEXT_161);
    }}
    stringBuffer.append(TEXT_162);
    } else {
    stringBuffer.append(TEXT_163);
    }
    if (!isImplementation) {
    stringBuffer.append(TEXT_164);
    stringBuffer.append(genFeature.getImportedType());
    stringBuffer.append(TEXT_165);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_166);
    } else {
    stringBuffer.append(TEXT_167);
    stringBuffer.append(genFeature.getImportedType());
    stringBuffer.append(TEXT_168);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_169);
    if (genModel.isReflectiveDelegation()) {
    stringBuffer.append(TEXT_170);
    if (genFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_171);
    }
    stringBuffer.append(TEXT_172);
    stringBuffer.append(genFeature.getObjectType());
    stringBuffer.append(TEXT_173);
    stringBuffer.append(genFeature.getQualifiedFeatureAccessor());
    stringBuffer.append(TEXT_174);
    if (genFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_175);
    stringBuffer.append(genFeature.getPrimitiveValueFunction());
    stringBuffer.append(TEXT_176);
    }
    stringBuffer.append(TEXT_177);
    } else if (!genFeature.isVolatile()) {
    if (genFeature.isListType()) {
    if (genModel.isVirtualDelegation()) {
    stringBuffer.append(TEXT_178);
    stringBuffer.append(genFeature.getImportedType());
    stringBuffer.append(TEXT_179);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_180);
    stringBuffer.append(genFeature.getImportedType());
    stringBuffer.append(TEXT_181);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(TEXT_182);
    }
    stringBuffer.append(TEXT_183);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_184);
    if (genModel.isVirtualDelegation()) {
    stringBuffer.append(TEXT_185);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(TEXT_186);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_187);
    stringBuffer.append(genClass.getListConstructor(genFeature));
    stringBuffer.append(TEXT_188);
    } else {
    stringBuffer.append(TEXT_189);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_190);
    stringBuffer.append(genClass.getListConstructor(genFeature));
    stringBuffer.append(TEXT_191);
    }
    stringBuffer.append(TEXT_192);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(genFeature.isMapType() && genFeature.isEffectiveSuppressEMFTypes() ? ".map()" : "");
    stringBuffer.append(TEXT_193);
    } else if (genFeature.isContainer()) {
    stringBuffer.append(TEXT_194);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(TEXT_195);
    stringBuffer.append(genFeature.getImportedType());
    stringBuffer.append(TEXT_196);
    } else {
    if (genFeature.isResolveProxies()) {
    if (genModel.isVirtualDelegation()) {
    stringBuffer.append(TEXT_197);
    stringBuffer.append(genFeature.getImportedType());
    stringBuffer.append(TEXT_198);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_199);
    stringBuffer.append(genFeature.getImportedType());
    stringBuffer.append(TEXT_200);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    if (!genFeature.isReferenceType()) {
    stringBuffer.append(TEXT_201);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_202);
    }
    stringBuffer.append(TEXT_203);
    }
    stringBuffer.append(TEXT_204);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_205);
    stringBuffer.append(genFeature.getSafeNameAsEObject());
    stringBuffer.append(TEXT_206);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.InternalEObject"));
    stringBuffer.append(TEXT_207);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_208);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.InternalEObject"));
    stringBuffer.append(TEXT_209);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_210);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_211);
    stringBuffer.append(genFeature.getNonEObjectInternalTypeCast());
    stringBuffer.append(TEXT_212);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_213);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_214);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_215);
    if (genFeature.isContains()) {
    stringBuffer.append(TEXT_216);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.InternalEObject"));
    stringBuffer.append(TEXT_217);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_218);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.InternalEObject"));
    stringBuffer.append(TEXT_219);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_220);
    if (!genFeature.isBidirectional()) {
    stringBuffer.append(TEXT_221);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.notify.NotificationChain"));
    stringBuffer.append(TEXT_222);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_223);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(TEXT_224);
    } else { GenFeature reverseFeature = genFeature.getReverse(); GenClass targetClass = reverseFeature.getGenClass();
    stringBuffer.append(TEXT_225);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.notify.NotificationChain"));
    stringBuffer.append(TEXT_226);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_227);
    stringBuffer.append(targetClass.getQualifiedFeatureID(reverseFeature));
    stringBuffer.append(TEXT_228);
    stringBuffer.append(targetClass.getImportedInterfaceName());
    stringBuffer.append(TEXT_229);
    }
    stringBuffer.append(TEXT_230);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_231);
    if (!genFeature.isBidirectional()) {
    stringBuffer.append(TEXT_232);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_233);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(TEXT_234);
    } else { GenFeature reverseFeature = genFeature.getReverse(); GenClass targetClass = reverseFeature.getGenClass();
    stringBuffer.append(TEXT_235);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_236);
    stringBuffer.append(targetClass.getQualifiedFeatureID(reverseFeature));
    stringBuffer.append(TEXT_237);
    stringBuffer.append(targetClass.getImportedInterfaceName());
    stringBuffer.append(TEXT_238);
    }
    stringBuffer.append(TEXT_239);
    } else if (genModel.isVirtualDelegation()) {
    stringBuffer.append(TEXT_240);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(TEXT_241);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_242);
    }
    stringBuffer.append(TEXT_243);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.impl.ENotificationImpl"));
    stringBuffer.append(TEXT_244);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.notify.Notification"));
    stringBuffer.append(TEXT_245);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(TEXT_246);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_247);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_248);
    }
    if (!genFeature.isResolveProxies() && genModel.isVirtualDelegation() && !genFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_249);
    stringBuffer.append(genFeature.getImportedType());
    stringBuffer.append(TEXT_250);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    if (!genFeature.isReferenceType()) {
    stringBuffer.append(TEXT_251);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_252);
    }
    stringBuffer.append(TEXT_253);
    } else if (genClass.isFlag(genFeature)) {
    stringBuffer.append(TEXT_254);
    stringBuffer.append(genClass.getFlagsField(genFeature));
    stringBuffer.append(TEXT_255);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_256);
    } else {
    stringBuffer.append(TEXT_257);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_258);
    }
    }
    } else {//volatile
    if (genFeature.isResolveProxies() && !genFeature.isListType()) {
    stringBuffer.append(TEXT_259);
    stringBuffer.append(genFeature.getImportedType());
    stringBuffer.append(TEXT_260);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_261);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_262);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_263);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_264);
    stringBuffer.append(genFeature.getNonEObjectInternalTypeCast());
    stringBuffer.append(TEXT_265);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.InternalEObject"));
    stringBuffer.append(TEXT_266);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_267);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_268);
    } else if (genFeature.hasDelegateFeature()) { GenFeature delegateFeature = genFeature.getDelegateFeature();
    if (genFeature.isFeatureMapType()) {
    if (delegateFeature.isWrappedFeatureMapType()) {
    stringBuffer.append(TEXT_269);
    stringBuffer.append(genFeature.getImportedEffectiveFeatureMapWrapperClass());
    stringBuffer.append(TEXT_270);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.FeatureMap"));
    stringBuffer.append(TEXT_271);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.FeatureMap"));
    stringBuffer.append(TEXT_272);
    stringBuffer.append(delegateFeature.getAccessorName());
    stringBuffer.append(TEXT_273);
    stringBuffer.append(genFeature.getQualifiedFeatureAccessor());
    stringBuffer.append(TEXT_274);
    } else {
    stringBuffer.append(TEXT_275);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.FeatureMap"));
    stringBuffer.append(TEXT_276);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.FeatureMap"));
    stringBuffer.append(TEXT_277);
    stringBuffer.append(delegateFeature.getAccessorName());
    stringBuffer.append(TEXT_278);
    stringBuffer.append(genFeature.getQualifiedFeatureAccessor());
    stringBuffer.append(TEXT_279);
    }
    } else if (genFeature.isListType()) {
    if (delegateFeature.isWrappedFeatureMapType()) {
    stringBuffer.append(TEXT_280);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.FeatureMap"));
    stringBuffer.append(TEXT_281);
    stringBuffer.append(delegateFeature.getAccessorName());
    stringBuffer.append(TEXT_282);
    stringBuffer.append(genFeature.getQualifiedFeatureAccessor());
    stringBuffer.append(TEXT_283);
    } else {
    stringBuffer.append(TEXT_284);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.FeatureMap"));
    stringBuffer.append(TEXT_285);
    stringBuffer.append(delegateFeature.getAccessorName());
    stringBuffer.append(TEXT_286);
    stringBuffer.append(genFeature.getQualifiedFeatureAccessor());
    stringBuffer.append(TEXT_287);
    }
    } else {
    if (delegateFeature.isWrappedFeatureMapType()) {
    stringBuffer.append(TEXT_288);
    if (genFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_289);
    }
    stringBuffer.append(TEXT_290);
    stringBuffer.append(genFeature.getObjectType());
    stringBuffer.append(TEXT_291);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.FeatureMap"));
    stringBuffer.append(TEXT_292);
    stringBuffer.append(delegateFeature.getAccessorName());
    stringBuffer.append(TEXT_293);
    stringBuffer.append(genFeature.getQualifiedFeatureAccessor());
    stringBuffer.append(TEXT_294);
    if (genFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_295);
    stringBuffer.append(genFeature.getPrimitiveValueFunction());
    stringBuffer.append(TEXT_296);
    }
    stringBuffer.append(TEXT_297);
    } else {
    stringBuffer.append(TEXT_298);
    if (genFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_299);
    }
    stringBuffer.append(TEXT_300);
    stringBuffer.append(genFeature.getObjectType());
    stringBuffer.append(TEXT_301);
    stringBuffer.append(delegateFeature.getAccessorName());
    stringBuffer.append(TEXT_302);
    stringBuffer.append(genFeature.getQualifiedFeatureAccessor());
    stringBuffer.append(TEXT_303);
    if (genFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_304);
    stringBuffer.append(genFeature.getPrimitiveValueFunction());
    stringBuffer.append(TEXT_305);
    }
    stringBuffer.append(TEXT_306);
    }
    }
    } else {
    stringBuffer.append(TEXT_307);
    stringBuffer.append(genFeature.getFormattedName());
    stringBuffer.append(TEXT_308);
    stringBuffer.append(genFeature.getFeatureKind());
    stringBuffer.append(TEXT_309);
    //Class/getGenFeature.todo.override.javajetinc
    }
    }
    stringBuffer.append(TEXT_310);
    }
    //Class/getGenFeature.override.javajetinc
    }
    if (isImplementation && !genModel.isReflectiveDelegation() && genFeature.isBasicGet()) {
    stringBuffer.append(TEXT_311);
    stringBuffer.append(genFeature.getImportedType());
    stringBuffer.append(TEXT_312);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_313);
    if (genFeature.isContainer()) {
    stringBuffer.append(TEXT_314);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(TEXT_315);
    stringBuffer.append(genFeature.getImportedType());
    stringBuffer.append(TEXT_316);
    } else if (!genFeature.isVolatile()) {
    if (genModel.isVirtualDelegation()) {
    stringBuffer.append(TEXT_317);
    stringBuffer.append(genFeature.getImportedType());
    stringBuffer.append(TEXT_318);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(TEXT_319);
    } else {
    stringBuffer.append(TEXT_320);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_321);
    }
    } else if (genFeature.hasDelegateFeature()) { GenFeature delegateFeature = genFeature.getDelegateFeature();
    if (delegateFeature.isWrappedFeatureMapType()) {
    stringBuffer.append(TEXT_322);
    stringBuffer.append(genFeature.getImportedType());
    stringBuffer.append(TEXT_323);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.FeatureMap"));
    stringBuffer.append(TEXT_324);
    stringBuffer.append(delegateFeature.getAccessorName());
    stringBuffer.append(TEXT_325);
    stringBuffer.append(genFeature.getQualifiedFeatureAccessor());
    stringBuffer.append(TEXT_326);
    } else {
    stringBuffer.append(TEXT_327);
    stringBuffer.append(genFeature.getImportedType());
    stringBuffer.append(TEXT_328);
    stringBuffer.append(delegateFeature.getAccessorName());
    stringBuffer.append(TEXT_329);
    stringBuffer.append(genFeature.getQualifiedFeatureAccessor());
    stringBuffer.append(TEXT_330);
    }
    } else {
    stringBuffer.append(TEXT_331);
    stringBuffer.append(genFeature.getFormattedName());
    stringBuffer.append(TEXT_332);
    stringBuffer.append(genFeature.getFeatureKind());
    stringBuffer.append(TEXT_333);
    //Class/basicGetGenFeature.todo.override.javajetinc
    }
    stringBuffer.append(TEXT_334);
    //Class/basicGetGenFeature.override.javajetinc
    }
    if (isImplementation && !genModel.isReflectiveDelegation() && genFeature.isBasicSet()) {
    stringBuffer.append(TEXT_335);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.notify.NotificationChain"));
    stringBuffer.append(TEXT_336);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_337);
    stringBuffer.append(genFeature.getImportedInternalType());
    stringBuffer.append(TEXT_338);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_339);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.notify.NotificationChain"));
    stringBuffer.append(TEXT_340);
    if (!genFeature.isVolatile()) {
    if (genModel.isVirtualDelegation()) {
    stringBuffer.append(TEXT_341);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_342);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(TEXT_343);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_344);
    } else {
    stringBuffer.append(TEXT_345);
    stringBuffer.append(genFeature.getImportedType());
    stringBuffer.append(TEXT_346);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_347);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_348);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_349);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_350);
    }
    if (genFeature.isUnsettable()) {
    if (genModel.isVirtualDelegation()) {
    stringBuffer.append(TEXT_351);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_352);
    } else if (genClass.isESetFlag(genFeature)) {
    stringBuffer.append(TEXT_353);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_354);
    stringBuffer.append(genClass.getESetFlagsField(genFeature));
    stringBuffer.append(TEXT_355);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_356);
    stringBuffer.append(genClass.getESetFlagsField(genFeature));
    stringBuffer.append(TEXT_357);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_358);
    } else {
    stringBuffer.append(TEXT_359);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_360);
    stringBuffer.append(genFeature.getUncapName());
    stringBuffer.append(TEXT_361);
    stringBuffer.append(genFeature.getUncapName());
    stringBuffer.append(TEXT_362);
    }
    }
    stringBuffer.append(TEXT_363);
    if (genFeature.isUnsettable()) {
    stringBuffer.append(TEXT_364);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.impl.ENotificationImpl"));
    stringBuffer.append(TEXT_365);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.impl.ENotificationImpl"));
    stringBuffer.append(TEXT_366);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.notify.Notification"));
    stringBuffer.append(TEXT_367);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(TEXT_368);
    if (genModel.isVirtualDelegation()) {
    stringBuffer.append(TEXT_369);
    stringBuffer.append(genFeature.getCapName());
    } else {
    stringBuffer.append(TEXT_370);
    stringBuffer.append(genFeature.getCapName());
    }
    stringBuffer.append(TEXT_371);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_372);
    if (genModel.isVirtualDelegation()) {
    stringBuffer.append(TEXT_373);
    } else {
    stringBuffer.append(TEXT_374);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_375);
    }
    stringBuffer.append(TEXT_376);
    } else {
    stringBuffer.append(TEXT_377);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.impl.ENotificationImpl"));
    stringBuffer.append(TEXT_378);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.impl.ENotificationImpl"));
    stringBuffer.append(TEXT_379);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.notify.Notification"));
    stringBuffer.append(TEXT_380);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(TEXT_381);
    if (genModel.isVirtualDelegation()) {
    stringBuffer.append(TEXT_382);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_383);
    stringBuffer.append(genFeature.getCapName());
    } else {
    stringBuffer.append(TEXT_384);
    stringBuffer.append(genFeature.getCapName());
    }
    stringBuffer.append(TEXT_385);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_386);
    }
    stringBuffer.append(TEXT_387);
    stringBuffer.append(TEXT_388);
    } else if (genFeature.hasDelegateFeature()) { GenFeature delegateFeature = genFeature.getDelegateFeature();
    if (delegateFeature.isWrappedFeatureMapType()) {
    stringBuffer.append(TEXT_389);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.FeatureMap"));
    stringBuffer.append(TEXT_390);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.FeatureMap"));
    stringBuffer.append(TEXT_391);
    stringBuffer.append(delegateFeature.getAccessorName());
    stringBuffer.append(TEXT_392);
    stringBuffer.append(genFeature.getQualifiedFeatureAccessor());
    stringBuffer.append(TEXT_393);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_394);
    } else {
    stringBuffer.append(TEXT_395);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.FeatureMap"));
    stringBuffer.append(TEXT_396);
    stringBuffer.append(delegateFeature.getAccessorName());
    stringBuffer.append(TEXT_397);
    stringBuffer.append(genFeature.getQualifiedFeatureAccessor());
    stringBuffer.append(TEXT_398);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_399);
    }
    } else {
    stringBuffer.append(TEXT_400);
    stringBuffer.append(genFeature.getFormattedName());
    stringBuffer.append(TEXT_401);
    stringBuffer.append(genFeature.getFeatureKind());
    stringBuffer.append(TEXT_402);
    //Class/basicSetGenFeature.todo.override.javajetinc
    }
    stringBuffer.append(TEXT_403);
    //Class/basicSetGenFeature.override.javajetinc
    }
    if (genFeature.isSet() && (isImplementation || !genFeature.isSuppressedSetVisibility())) {
    if (isInterface) { 
    stringBuffer.append(TEXT_404);
    stringBuffer.append(genClass.getQualifiedInterfaceName());
    stringBuffer.append(TEXT_405);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_406);
    stringBuffer.append(genFeature.getFormattedName());
    stringBuffer.append(TEXT_407);
    stringBuffer.append(genFeature.getFeatureKind());
    stringBuffer.append(TEXT_408);
    stringBuffer.append(genFeature.getFormattedName());
    stringBuffer.append(TEXT_409);
    stringBuffer.append(genFeature.getFeatureKind());
    stringBuffer.append(TEXT_410);
    if (genFeature.isEnumType()) {
    stringBuffer.append(TEXT_411);
    stringBuffer.append(genFeature.getTypeGenEnum().getQualifiedName());
    }
    if (genFeature.isUnsettable()) {
    if (!genFeature.isSuppressedIsSetVisibility()) {
    stringBuffer.append(TEXT_412);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_413);
    }
    if (!genFeature.isSuppressedUnsetVisibility()) {
    stringBuffer.append(TEXT_414);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_415);
    }
    }
    stringBuffer.append(TEXT_416);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_417);
    } else {
    stringBuffer.append(TEXT_418);
    }
    if (!isImplementation) { 
    stringBuffer.append(TEXT_419);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_420);
    stringBuffer.append(genFeature.getImportedType());
    stringBuffer.append(TEXT_421);
    } else {
    stringBuffer.append(TEXT_422);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_423);
    stringBuffer.append(genFeature.getImportedType());
    stringBuffer.append(TEXT_424);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_425);
    if (genModel.isReflectiveDelegation()) {
    stringBuffer.append(TEXT_426);
    stringBuffer.append(genFeature.getQualifiedFeatureAccessor());
    stringBuffer.append(TEXT_427);
    if (genFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_428);
    stringBuffer.append(genFeature.getObjectType());
    stringBuffer.append(TEXT_429);
    }
    stringBuffer.append(TEXT_430);
    stringBuffer.append(genFeature.getCapName());
    if (genFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_431);
    }
    stringBuffer.append(TEXT_432);
    } else if (!genFeature.isVolatile()) {
    if (genFeature.isContainer()) { GenFeature reverseFeature = genFeature.getReverse(); GenClass targetClass = reverseFeature.getGenClass();
    stringBuffer.append(TEXT_433);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_434);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(TEXT_435);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_436);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.EcoreUtil"));
    stringBuffer.append(TEXT_437);
    stringBuffer.append(genFeature.getEObjectCast());
    stringBuffer.append(TEXT_438);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_439);
    stringBuffer.append(genModel.getImportedName("java.lang.IllegalArgumentException"));
    stringBuffer.append(TEXT_440);
    stringBuffer.append(genModel.getNonNLS());
    stringBuffer.append(TEXT_441);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.notify.NotificationChain"));
    stringBuffer.append(TEXT_442);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_443);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.InternalEObject"));
    stringBuffer.append(TEXT_444);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_445);
    stringBuffer.append(targetClass.getQualifiedFeatureID(reverseFeature));
    stringBuffer.append(TEXT_446);
    stringBuffer.append(targetClass.getImportedInterfaceName());
    stringBuffer.append(TEXT_447);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.InternalEObject"));
    stringBuffer.append(TEXT_448);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_449);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(TEXT_450);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.impl.ENotificationImpl"));
    stringBuffer.append(TEXT_451);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.notify.Notification"));
    stringBuffer.append(TEXT_452);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(TEXT_453);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_454);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_455);
    } else if (genFeature.isBidirectional() || genFeature.isContains()) {
    if (genModel.isVirtualDelegation()) {
    stringBuffer.append(TEXT_456);
    stringBuffer.append(genFeature.getImportedType());
    stringBuffer.append(TEXT_457);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_458);
    stringBuffer.append(genFeature.getImportedType());
    stringBuffer.append(TEXT_459);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(TEXT_460);
    }
    stringBuffer.append(TEXT_461);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_462);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_463);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.notify.NotificationChain"));
    stringBuffer.append(TEXT_464);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_465);
    if (!genFeature.isBidirectional()) {
    stringBuffer.append(TEXT_466);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.InternalEObject"));
    stringBuffer.append(TEXT_467);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_468);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(TEXT_469);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_470);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.InternalEObject"));
    stringBuffer.append(TEXT_471);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_472);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(TEXT_473);
    } else { GenFeature reverseFeature = genFeature.getReverse(); GenClass targetClass = reverseFeature.getGenClass();
    stringBuffer.append(TEXT_474);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.InternalEObject"));
    stringBuffer.append(TEXT_475);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_476);
    stringBuffer.append(targetClass.getQualifiedFeatureID(reverseFeature));
    stringBuffer.append(TEXT_477);
    stringBuffer.append(targetClass.getImportedInterfaceName());
    stringBuffer.append(TEXT_478);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_479);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.InternalEObject"));
    stringBuffer.append(TEXT_480);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_481);
    stringBuffer.append(targetClass.getQualifiedFeatureID(reverseFeature));
    stringBuffer.append(TEXT_482);
    stringBuffer.append(targetClass.getImportedInterfaceName());
    stringBuffer.append(TEXT_483);
    }
    stringBuffer.append(TEXT_484);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_485);
    stringBuffer.append(genFeature.getInternalTypeCast());
    stringBuffer.append(TEXT_486);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_487);
    if (genFeature.isUnsettable()) {
    stringBuffer.append(TEXT_488);
    if (genModel.isVirtualDelegation()) {
    stringBuffer.append(TEXT_489);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_490);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(TEXT_491);
    } else if (genClass.isESetFlag(genFeature)) {
    stringBuffer.append(TEXT_492);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_493);
    stringBuffer.append(genClass.getESetFlagsField(genFeature));
    stringBuffer.append(TEXT_494);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_495);
    stringBuffer.append(genClass.getESetFlagsField(genFeature));
    stringBuffer.append(TEXT_496);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_497);
    } else {
    stringBuffer.append(TEXT_498);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_499);
    stringBuffer.append(genFeature.getUncapName());
    stringBuffer.append(TEXT_500);
    stringBuffer.append(genFeature.getUncapName());
    stringBuffer.append(TEXT_501);
    }
    stringBuffer.append(TEXT_502);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.impl.ENotificationImpl"));
    stringBuffer.append(TEXT_503);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.notify.Notification"));
    stringBuffer.append(TEXT_504);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(TEXT_505);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_506);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_507);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_508);
    } else {
    stringBuffer.append(TEXT_509);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.impl.ENotificationImpl"));
    stringBuffer.append(TEXT_510);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.notify.Notification"));
    stringBuffer.append(TEXT_511);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(TEXT_512);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_513);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_514);
    }
    } else {
    if (genClass.isFlag(genFeature)) {
    stringBuffer.append(TEXT_515);
    stringBuffer.append(genFeature.getImportedType());
    stringBuffer.append(TEXT_516);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_517);
    stringBuffer.append(genClass.getFlagsField(genFeature));
    stringBuffer.append(TEXT_518);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_519);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_520);
    stringBuffer.append(genClass.getFlagsField(genFeature));
    stringBuffer.append(TEXT_521);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_522);
    stringBuffer.append(genClass.getFlagsField(genFeature));
    stringBuffer.append(TEXT_523);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_524);
    } else {
    if (!genModel.isVirtualDelegation() || genFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_525);
    stringBuffer.append(genFeature.getImportedType());
    stringBuffer.append(TEXT_526);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_527);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_528);
    }
    if (genFeature.isEnumType()) {
    if (genModel.isVirtualDelegation()) {
    stringBuffer.append(TEXT_529);
    stringBuffer.append(genFeature.getImportedType());
    stringBuffer.append(TEXT_530);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_531);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_532);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_533);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_534);
    } else {
    stringBuffer.append(TEXT_535);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_536);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_537);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_538);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_539);
    }
    } else {
    if (genModel.isVirtualDelegation() && !genFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_540);
    stringBuffer.append(genFeature.getImportedType());
    stringBuffer.append(TEXT_541);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_542);
    stringBuffer.append(genFeature.getInternalTypeCast());
    stringBuffer.append(TEXT_543);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_544);
    } else {
    stringBuffer.append(TEXT_545);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_546);
    stringBuffer.append(genFeature.getInternalTypeCast());
    stringBuffer.append(TEXT_547);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_548);
    }
    }
    if (genModel.isVirtualDelegation() && !genFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_549);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_550);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(TEXT_551);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_552);
    }
    }
    if (genFeature.isUnsettable()) {
    if (genModel.isVirtualDelegation() && !genFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_553);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_554);
    } else if (genClass.isESetFlag(genFeature)) {
    stringBuffer.append(TEXT_555);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_556);
    stringBuffer.append(genClass.getESetFlagsField(genFeature));
    stringBuffer.append(TEXT_557);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_558);
    stringBuffer.append(genClass.getESetFlagsField(genFeature));
    stringBuffer.append(TEXT_559);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_560);
    } else {
    stringBuffer.append(TEXT_561);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_562);
    stringBuffer.append(genFeature.getUncapName());
    stringBuffer.append(TEXT_563);
    stringBuffer.append(genFeature.getUncapName());
    stringBuffer.append(TEXT_564);
    }
    stringBuffer.append(TEXT_565);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.impl.ENotificationImpl"));
    stringBuffer.append(TEXT_566);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.notify.Notification"));
    stringBuffer.append(TEXT_567);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(TEXT_568);
    if (genModel.isVirtualDelegation() && !genFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_569);
    if (genFeature.isReferenceType()) {
    stringBuffer.append(TEXT_570);
    } else {
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_571);
    }
    stringBuffer.append(TEXT_572);
    stringBuffer.append(genFeature.getCapName());
    } else {
    stringBuffer.append(TEXT_573);
    stringBuffer.append(genFeature.getCapName());
    }
    stringBuffer.append(TEXT_574);
    if (genClass.isFlag(genFeature)) {
    stringBuffer.append(TEXT_575);
    stringBuffer.append(genFeature.getCapName());
    } else {
    stringBuffer.append(genFeature.getSafeName());
    }
    stringBuffer.append(TEXT_576);
    if (genModel.isVirtualDelegation() && !genFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_577);
    } else {
    stringBuffer.append(TEXT_578);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_579);
    }
    stringBuffer.append(TEXT_580);
    } else {
    stringBuffer.append(TEXT_581);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.impl.ENotificationImpl"));
    stringBuffer.append(TEXT_582);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.notify.Notification"));
    stringBuffer.append(TEXT_583);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(TEXT_584);
    if (genModel.isVirtualDelegation() && !genFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_585);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_586);
    if (genFeature.isReferenceType()) {
    stringBuffer.append(TEXT_587);
    } else {
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_588);
    }
    stringBuffer.append(TEXT_589);
    stringBuffer.append(genFeature.getCapName());
    } else {
    stringBuffer.append(TEXT_590);
    stringBuffer.append(genFeature.getCapName());
    }
    stringBuffer.append(TEXT_591);
    if (genClass.isFlag(genFeature)) {
    stringBuffer.append(TEXT_592);
    stringBuffer.append(genFeature.getCapName());
    } else {
    stringBuffer.append(genFeature.getSafeName());
    }
    stringBuffer.append(TEXT_593);
    }
    }
    } else if (genFeature.hasDelegateFeature()) { GenFeature delegateFeature = genFeature.getDelegateFeature();
    if (delegateFeature.isWrappedFeatureMapType()) {
    stringBuffer.append(TEXT_594);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.FeatureMap"));
    stringBuffer.append(TEXT_595);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.FeatureMap"));
    stringBuffer.append(TEXT_596);
    stringBuffer.append(delegateFeature.getAccessorName());
    stringBuffer.append(TEXT_597);
    stringBuffer.append(genFeature.getQualifiedFeatureAccessor());
    stringBuffer.append(TEXT_598);
    if (genFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_599);
    stringBuffer.append(genFeature.getObjectType());
    stringBuffer.append(TEXT_600);
    }
    stringBuffer.append(TEXT_601);
    stringBuffer.append(genFeature.getCapName());
    if (genFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_602);
    }
    stringBuffer.append(TEXT_603);
    } else {
    stringBuffer.append(TEXT_604);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.FeatureMap"));
    stringBuffer.append(TEXT_605);
    stringBuffer.append(delegateFeature.getAccessorName());
    stringBuffer.append(TEXT_606);
    stringBuffer.append(genFeature.getQualifiedFeatureAccessor());
    stringBuffer.append(TEXT_607);
    if (genFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_608);
    stringBuffer.append(genFeature.getObjectType());
    stringBuffer.append(TEXT_609);
    }
    stringBuffer.append(TEXT_610);
    stringBuffer.append(genFeature.getCapName());
    if (genFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_611);
    }
    stringBuffer.append(TEXT_612);
    }
    } else {
    stringBuffer.append(TEXT_613);
    stringBuffer.append(genFeature.getFormattedName());
    stringBuffer.append(TEXT_614);
    stringBuffer.append(genFeature.getFeatureKind());
    stringBuffer.append(TEXT_615);
    //Class/setGenFeature.todo.override.javajetinc
    }
    stringBuffer.append(TEXT_616);
    }
    //Class/setGenFeature.override.javajetinc
    }
    if (isImplementation && !genModel.isReflectiveDelegation() && genFeature.isBasicUnset()) {
    stringBuffer.append(TEXT_617);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.notify.NotificationChain"));
    stringBuffer.append(TEXT_618);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_619);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.notify.NotificationChain"));
    stringBuffer.append(TEXT_620);
    if (!genFeature.isVolatile()) {
    if (genModel.isVirtualDelegation()) {
    stringBuffer.append(TEXT_621);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_622);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(TEXT_623);
    } else {
    stringBuffer.append(TEXT_624);
    stringBuffer.append(genFeature.getImportedType());
    stringBuffer.append(TEXT_625);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_626);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_627);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_628);
    }
    if (genModel.isVirtualDelegation()) {
    stringBuffer.append(TEXT_629);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_630);
    } else if (genClass.isESetFlag(genFeature)) {
    stringBuffer.append(TEXT_631);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_632);
    stringBuffer.append(genClass.getESetFlagsField(genFeature));
    stringBuffer.append(TEXT_633);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_634);
    stringBuffer.append(genClass.getESetFlagsField(genFeature));
    stringBuffer.append(TEXT_635);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_636);
    } else {
    stringBuffer.append(TEXT_637);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_638);
    stringBuffer.append(genFeature.getUncapName());
    stringBuffer.append(TEXT_639);
    stringBuffer.append(genFeature.getUncapName());
    stringBuffer.append(TEXT_640);
    }
    stringBuffer.append(TEXT_641);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.impl.ENotificationImpl"));
    stringBuffer.append(TEXT_642);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.impl.ENotificationImpl"));
    stringBuffer.append(TEXT_643);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.notify.Notification"));
    stringBuffer.append(TEXT_644);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(TEXT_645);
    if (genModel.isVirtualDelegation()) {
    stringBuffer.append(TEXT_646);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_647);
    } else {
    stringBuffer.append(TEXT_648);
    stringBuffer.append(genFeature.getCapName());
    }
    stringBuffer.append(TEXT_649);
    if (genModel.isVirtualDelegation()) {
    stringBuffer.append(TEXT_650);
    } else {
    stringBuffer.append(TEXT_651);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_652);
    }
    stringBuffer.append(TEXT_653);
    } else {
    stringBuffer.append(TEXT_654);
    stringBuffer.append(genFeature.getFormattedName());
    stringBuffer.append(TEXT_655);
    stringBuffer.append(genFeature.getFeatureKind());
    stringBuffer.append(TEXT_656);
    //Class/basicUnsetGenFeature.todo.override.javajetinc
    }
    stringBuffer.append(TEXT_657);
    //Class.basicUnsetGenFeature.override.javajetinc
    }
    if (genFeature.isUnset() && (isImplementation || !genFeature.isSuppressedUnsetVisibility())) {
    if (isInterface) {
    stringBuffer.append(TEXT_658);
    stringBuffer.append(genClass.getQualifiedInterfaceName());
    stringBuffer.append(TEXT_659);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_660);
    stringBuffer.append(genFeature.getFormattedName());
    stringBuffer.append(TEXT_661);
    stringBuffer.append(genFeature.getFeatureKind());
    stringBuffer.append(TEXT_662);
    if (!genFeature.isSuppressedIsSetVisibility()) {
    stringBuffer.append(TEXT_663);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_664);
    }
    stringBuffer.append(TEXT_665);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_666);
    if (!genFeature.isListType() && !genFeature.isSuppressedSetVisibility()) {
    stringBuffer.append(TEXT_667);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_668);
    stringBuffer.append(genFeature.getImportedType());
    stringBuffer.append(TEXT_669);
    }
    stringBuffer.append(TEXT_670);
    } else {
    stringBuffer.append(TEXT_671);
    }
    if (!isImplementation) {
    stringBuffer.append(TEXT_672);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_673);
    } else {
    stringBuffer.append(TEXT_674);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_675);
    if (genModel.isReflectiveDelegation()) {
    stringBuffer.append(TEXT_676);
    stringBuffer.append(genFeature.getQualifiedFeatureAccessor());
    stringBuffer.append(TEXT_677);
    } else if (!genFeature.isVolatile()) {
    if (genFeature.isListType()) {
    stringBuffer.append(TEXT_678);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.InternalEList"));
    stringBuffer.append(TEXT_679);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_680);
    } else if (genFeature.isBidirectional() || genFeature.isContains()) {
    if (genModel.isVirtualDelegation()) {
    stringBuffer.append(TEXT_681);
    stringBuffer.append(genFeature.getImportedType());
    stringBuffer.append(TEXT_682);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_683);
    stringBuffer.append(genFeature.getImportedType());
    stringBuffer.append(TEXT_684);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(TEXT_685);
    }
    stringBuffer.append(TEXT_686);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_687);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.notify.NotificationChain"));
    stringBuffer.append(TEXT_688);
    if (!genFeature.isBidirectional()) {
    stringBuffer.append(TEXT_689);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.InternalEObject"));
    stringBuffer.append(TEXT_690);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_691);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(TEXT_692);
    } else { GenFeature reverseFeature = genFeature.getReverse(); GenClass targetClass = reverseFeature.getGenClass();
    stringBuffer.append(TEXT_693);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.InternalEObject"));
    stringBuffer.append(TEXT_694);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_695);
    stringBuffer.append(targetClass.getQualifiedFeatureID(reverseFeature));
    stringBuffer.append(TEXT_696);
    stringBuffer.append(targetClass.getImportedInterfaceName());
    stringBuffer.append(TEXT_697);
    }
    stringBuffer.append(TEXT_698);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_699);
    if (genModel.isVirtualDelegation()) {
    stringBuffer.append(TEXT_700);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_701);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(TEXT_702);
    } else if (genClass.isESetFlag(genFeature)) {
    stringBuffer.append(TEXT_703);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_704);
    stringBuffer.append(genClass.getESetFlagsField(genFeature));
    stringBuffer.append(TEXT_705);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_706);
    stringBuffer.append(genClass.getESetFlagsField(genFeature));
    stringBuffer.append(TEXT_707);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_708);
    } else {
    stringBuffer.append(TEXT_709);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_710);
    stringBuffer.append(genFeature.getUncapName());
    stringBuffer.append(TEXT_711);
    stringBuffer.append(genFeature.getUncapName());
    stringBuffer.append(TEXT_712);
    }
    stringBuffer.append(TEXT_713);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.impl.ENotificationImpl"));
    stringBuffer.append(TEXT_714);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.notify.Notification"));
    stringBuffer.append(TEXT_715);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(TEXT_716);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_717);
    } else {
    if (genClass.isFlag(genFeature)) {
    stringBuffer.append(TEXT_718);
    stringBuffer.append(genFeature.getImportedType());
    stringBuffer.append(TEXT_719);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_720);
    stringBuffer.append(genClass.getFlagsField(genFeature));
    stringBuffer.append(TEXT_721);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_722);
    } else if (genModel.isVirtualDelegation() && !genFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_723);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_724);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(TEXT_725);
    } else {
    stringBuffer.append(TEXT_726);
    stringBuffer.append(genFeature.getImportedType());
    stringBuffer.append(TEXT_727);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_728);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_729);
    }
    if (genModel.isVirtualDelegation() && !genFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_730);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_731);
    } else if (genClass.isESetFlag(genFeature)) {
    stringBuffer.append(TEXT_732);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_733);
    stringBuffer.append(genClass.getESetFlagsField(genFeature));
    stringBuffer.append(TEXT_734);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_735);
    } else {
    stringBuffer.append(TEXT_736);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_737);
    stringBuffer.append(genFeature.getUncapName());
    stringBuffer.append(TEXT_738);
    }
    if (genFeature.isReferenceType()) {
    stringBuffer.append(TEXT_739);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_740);
    if (!genModel.isVirtualDelegation()) {
    if (genClass.isESetFlag(genFeature)) {
    stringBuffer.append(TEXT_741);
    stringBuffer.append(genClass.getESetFlagsField(genFeature));
    stringBuffer.append(TEXT_742);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_743);
    } else {
    stringBuffer.append(TEXT_744);
    stringBuffer.append(genFeature.getUncapName());
    stringBuffer.append(TEXT_745);
    }
    }
    stringBuffer.append(TEXT_746);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.impl.ENotificationImpl"));
    stringBuffer.append(TEXT_747);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.notify.Notification"));
    stringBuffer.append(TEXT_748);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(TEXT_749);
    if (genModel.isVirtualDelegation()) {
    stringBuffer.append(TEXT_750);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_751);
    } else {
    stringBuffer.append(TEXT_752);
    stringBuffer.append(genFeature.getCapName());
    }
    stringBuffer.append(TEXT_753);
    if (genModel.isVirtualDelegation()) {
    stringBuffer.append(TEXT_754);
    } else {
    stringBuffer.append(TEXT_755);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_756);
    }
    stringBuffer.append(TEXT_757);
    } else {
    if (genClass.isFlag(genFeature)) {
    stringBuffer.append(TEXT_758);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_759);
    stringBuffer.append(genClass.getFlagsField(genFeature));
    stringBuffer.append(TEXT_760);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_761);
    stringBuffer.append(genClass.getFlagsField(genFeature));
    stringBuffer.append(TEXT_762);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_763);
    } else if (!genModel.isVirtualDelegation() || genFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_764);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_765);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_766);
    }
    if (!genModel.isVirtualDelegation() || genFeature.isPrimitiveType()) {
    if (genClass.isESetFlag(genFeature)) {
    stringBuffer.append(TEXT_767);
    stringBuffer.append(genClass.getESetFlagsField(genFeature));
    stringBuffer.append(TEXT_768);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_769);
    } else {
    stringBuffer.append(TEXT_770);
    stringBuffer.append(genFeature.getUncapName());
    stringBuffer.append(TEXT_771);
    }
    }
    stringBuffer.append(TEXT_772);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.impl.ENotificationImpl"));
    stringBuffer.append(TEXT_773);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.notify.Notification"));
    stringBuffer.append(TEXT_774);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(TEXT_775);
    if (genModel.isVirtualDelegation() && !genFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_776);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_777);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_778);
    } else {
    stringBuffer.append(TEXT_779);
    stringBuffer.append(genFeature.getCapName());
    }
    stringBuffer.append(TEXT_780);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_781);
    if (genModel.isVirtualDelegation() && !genFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_782);
    } else {
    stringBuffer.append(TEXT_783);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_784);
    }
    stringBuffer.append(TEXT_785);
    }
    }
    } else if (genFeature.hasDelegateFeature()) { GenFeature delegateFeature = genFeature.getDelegateFeature();
    if (delegateFeature.isWrappedFeatureMapType()) {
    stringBuffer.append(TEXT_786);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.FeatureMap"));
    stringBuffer.append(TEXT_787);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.FeatureMap"));
    stringBuffer.append(TEXT_788);
    stringBuffer.append(delegateFeature.getAccessorName());
    stringBuffer.append(TEXT_789);
    stringBuffer.append(genFeature.getQualifiedFeatureAccessor());
    stringBuffer.append(TEXT_790);
    } else {
    stringBuffer.append(TEXT_791);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.FeatureMap"));
    stringBuffer.append(TEXT_792);
    stringBuffer.append(delegateFeature.getAccessorName());
    stringBuffer.append(TEXT_793);
    stringBuffer.append(genFeature.getQualifiedFeatureAccessor());
    stringBuffer.append(TEXT_794);
    }
    } else {
    stringBuffer.append(TEXT_795);
    stringBuffer.append(genFeature.getFormattedName());
    stringBuffer.append(TEXT_796);
    stringBuffer.append(genFeature.getFeatureKind());
    stringBuffer.append(TEXT_797);
    //Class/unsetGenFeature.todo.override.javajetinc
    }
    stringBuffer.append(TEXT_798);
    }
    //Class/unsetGenFeature.override.javajetinc
    }
    if (genFeature.isIsSet() && (isImplementation || !genFeature.isSuppressedIsSetVisibility())) {
    if (isInterface) {
    stringBuffer.append(TEXT_799);
    stringBuffer.append(genClass.getQualifiedInterfaceName());
    stringBuffer.append(TEXT_800);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_801);
    stringBuffer.append(genFeature.getFormattedName());
    stringBuffer.append(TEXT_802);
    stringBuffer.append(genFeature.getFeatureKind());
    stringBuffer.append(TEXT_803);
    stringBuffer.append(genFeature.getFormattedName());
    stringBuffer.append(TEXT_804);
    stringBuffer.append(genFeature.getFeatureKind());
    stringBuffer.append(TEXT_805);
    if (genFeature.isChangeable() && !genFeature.isSuppressedUnsetVisibility()) {
    stringBuffer.append(TEXT_806);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_807);
    }
    stringBuffer.append(TEXT_808);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_809);
    if (!genFeature.isListType() && genFeature.isChangeable() && !genFeature.isSuppressedSetVisibility()) {
    stringBuffer.append(TEXT_810);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_811);
    stringBuffer.append(genFeature.getImportedType());
    stringBuffer.append(TEXT_812);
    }
    stringBuffer.append(TEXT_813);
    } else {
    stringBuffer.append(TEXT_814);
    }
    if (!isImplementation) {
    stringBuffer.append(TEXT_815);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_816);
    } else {
    stringBuffer.append(TEXT_817);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_818);
    if (genModel.isReflectiveDelegation()) {
    stringBuffer.append(TEXT_819);
    stringBuffer.append(genFeature.getQualifiedFeatureAccessor());
    stringBuffer.append(TEXT_820);
    } else if (!genFeature.isVolatile()) {
    if (genFeature.isListType()) {
    if (genModel.isVirtualDelegation()) {
    stringBuffer.append(TEXT_821);
    stringBuffer.append(genFeature.getImportedType());
    stringBuffer.append(TEXT_822);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_823);
    stringBuffer.append(genFeature.getImportedType());
    stringBuffer.append(TEXT_824);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(TEXT_825);
    }
    stringBuffer.append(TEXT_826);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_827);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.InternalEList"));
    stringBuffer.append(TEXT_828);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_829);
    } else {
    if (genModel.isVirtualDelegation() && !genFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_830);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(TEXT_831);
    } else if (genClass.isESetFlag(genFeature)) {
    stringBuffer.append(TEXT_832);
    stringBuffer.append(genClass.getESetFlagsField(genFeature));
    stringBuffer.append(TEXT_833);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_834);
    } else {
    stringBuffer.append(TEXT_835);
    stringBuffer.append(genFeature.getUncapName());
    stringBuffer.append(TEXT_836);
    }
    }
    } else if (genFeature.hasDelegateFeature()) { GenFeature delegateFeature = genFeature.getDelegateFeature();
    if (delegateFeature.isWrappedFeatureMapType()) {
    stringBuffer.append(TEXT_837);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.FeatureMap"));
    stringBuffer.append(TEXT_838);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.FeatureMap"));
    stringBuffer.append(TEXT_839);
    stringBuffer.append(delegateFeature.getAccessorName());
    stringBuffer.append(TEXT_840);
    stringBuffer.append(genFeature.getQualifiedFeatureAccessor());
    stringBuffer.append(TEXT_841);
    } else {
    stringBuffer.append(TEXT_842);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.FeatureMap"));
    stringBuffer.append(TEXT_843);
    stringBuffer.append(delegateFeature.getAccessorName());
    stringBuffer.append(TEXT_844);
    stringBuffer.append(genFeature.getQualifiedFeatureAccessor());
    stringBuffer.append(TEXT_845);
    }
    } else {
    stringBuffer.append(TEXT_846);
    stringBuffer.append(genFeature.getFormattedName());
    stringBuffer.append(TEXT_847);
    stringBuffer.append(genFeature.getFeatureKind());
    stringBuffer.append(TEXT_848);
    //Class/isSetGenFeature.todo.override.javajetinc
    }
    stringBuffer.append(TEXT_849);
    }
    //Class/isSetGenFeature.override.javajetinc
    }
    //Class/genFeature.override.javajetinc
    }//for
    for (Iterator i= (isImplementation ? genClass.getImplementedGenOperations() : genClass.getDeclaredGenOperations()).iterator(); i.hasNext();) { GenOperation genOperation = (GenOperation)i.next();
    if (isInterface) {
    stringBuffer.append(TEXT_850);
    if (genOperation.hasDocumentation()) {
    stringBuffer.append(TEXT_851);
    stringBuffer.append(genOperation.getDocumentation(genModel.getIndentation(stringBuffer)));
    stringBuffer.append(TEXT_852);
    }
    if (!genModel.isSuppressEMFModelTags()) { boolean first = true; for (StringTokenizer stringTokenizer = new StringTokenizer(genOperation.getModelInfo(), "\n\r"); stringTokenizer.hasMoreTokens(); ) { String modelInfo = stringTokenizer.nextToken(); if (first) { first = false;
    stringBuffer.append(TEXT_853);
    stringBuffer.append(modelInfo);
    } else {
    stringBuffer.append(TEXT_854);
    stringBuffer.append(modelInfo);
    }} if (first) {
    stringBuffer.append(TEXT_855);
    }}
    stringBuffer.append(TEXT_856);
    } else {
    stringBuffer.append(TEXT_857);
    }
    if (!isImplementation) {
    stringBuffer.append(TEXT_858);
    stringBuffer.append(genOperation.getImportedType());
    stringBuffer.append(TEXT_859);
    stringBuffer.append(genOperation.getName());
    stringBuffer.append(TEXT_860);
    stringBuffer.append(genOperation.getParameters());
    stringBuffer.append(TEXT_861);
    stringBuffer.append(genOperation.getThrows());
    stringBuffer.append(TEXT_862);
    } else {
    stringBuffer.append(TEXT_863);
    stringBuffer.append(genOperation.getImportedType());
    stringBuffer.append(TEXT_864);
    stringBuffer.append(genOperation.getName());
    stringBuffer.append(TEXT_865);
    stringBuffer.append(genOperation.getParameters());
    stringBuffer.append(TEXT_866);
    stringBuffer.append(genOperation.getThrows());
    stringBuffer.append(TEXT_867);
    if (genOperation.hasBody()) {
    stringBuffer.append(TEXT_868);
    stringBuffer.append(genOperation.getBody(genModel.getIndentation(stringBuffer)));
    } else if (genOperation.isInvariant()) {GenClass opClass = genOperation.getGenClass(); String diagnostics = ((GenParameter)genOperation.getGenParameters().get(0)).getName(); String context = ((GenParameter)genOperation.getGenParameters().get(1)).getName();
    stringBuffer.append(TEXT_869);
    stringBuffer.append(diagnostics);
    stringBuffer.append(TEXT_870);
    stringBuffer.append(diagnostics);
    stringBuffer.append(TEXT_871);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.util.BasicDiagnostic"));
    stringBuffer.append(TEXT_872);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.util.Diagnostic"));
    stringBuffer.append(TEXT_873);
    stringBuffer.append(opClass.getGenPackage().getImportedValidatorClassName());
    stringBuffer.append(TEXT_874);
    stringBuffer.append(opClass.getGenPackage().getImportedValidatorClassName());
    stringBuffer.append(TEXT_875);
    stringBuffer.append(opClass.getOperationID(genOperation));
    stringBuffer.append(TEXT_876);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.plugin.EcorePlugin"));
    stringBuffer.append(TEXT_877);
    stringBuffer.append(genOperation.getName());
    stringBuffer.append(TEXT_878);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.EObjectValidator"));
    stringBuffer.append(TEXT_879);
    stringBuffer.append(context);
    stringBuffer.append(TEXT_880);
    stringBuffer.append(genModel.getNonNLS());
    stringBuffer.append(genModel.getNonNLS(2));
    stringBuffer.append(TEXT_881);
    } else {
    stringBuffer.append(TEXT_882);
    //Class/implementedGenOperation.todo.override.javajetinc
    }
    stringBuffer.append(TEXT_883);
    }
    //Class/implementedGenOperation.override.javajetinc
    }//for
    if (isImplementation && !genModel.isReflectiveDelegation() && genClass.implementsAny(genClass.getEInverseAddGenFeatures())) {
    stringBuffer.append(TEXT_884);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.notify.NotificationChain"));
    stringBuffer.append(TEXT_885);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.InternalEObject"));
    stringBuffer.append(TEXT_886);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.notify.NotificationChain"));
    stringBuffer.append(TEXT_887);
    for (Iterator i=genClass.getEInverseAddGenFeatures().iterator(); i.hasNext();) { GenFeature genFeature = (GenFeature)i.next();
    if (!genModel.isMinimalReflectiveMethods() || genClass.getImplementedGenFeatures().contains(genFeature)) {
    stringBuffer.append(TEXT_888);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(TEXT_889);
    if (genFeature.isListType()) {
    if (genFeature.isMapType() && genFeature.isEffectiveSuppressEMFTypes()) {
    stringBuffer.append(TEXT_890);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.InternalEList"));
    stringBuffer.append(TEXT_891);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.util.EMap"));
    stringBuffer.append(TEXT_892);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_893);
    } else {
    stringBuffer.append(TEXT_894);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.InternalEList"));
    stringBuffer.append(TEXT_895);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_896);
    }
    } else if (genFeature.isContainer()) {
    stringBuffer.append(TEXT_897);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(TEXT_898);
    } else {
    if (genClass.getImplementingGenModel(genFeature).isVirtualDelegation()) {
    stringBuffer.append(TEXT_899);
    stringBuffer.append(genFeature.getImportedType());
    stringBuffer.append(TEXT_900);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_901);
    stringBuffer.append(genFeature.getImportedType());
    stringBuffer.append(TEXT_902);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(TEXT_903);
    }
    stringBuffer.append(TEXT_904);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_905);
    if (genFeature.isContains()) {
    stringBuffer.append(TEXT_906);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.InternalEObject"));
    stringBuffer.append(TEXT_907);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_908);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(TEXT_909);
    } else { GenFeature reverseFeature = genFeature.getReverse(); GenClass targetClass = reverseFeature.getGenClass();
    stringBuffer.append(TEXT_910);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.InternalEObject"));
    stringBuffer.append(TEXT_911);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_912);
    stringBuffer.append(targetClass.getQualifiedFeatureID(reverseFeature));
    stringBuffer.append(TEXT_913);
    stringBuffer.append(targetClass.getImportedInterfaceName());
    stringBuffer.append(TEXT_914);
    }
    stringBuffer.append(TEXT_915);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_916);
    stringBuffer.append(genFeature.getImportedType());
    stringBuffer.append(TEXT_917);
    }
    }
    }
    stringBuffer.append(TEXT_918);
    if (genModel.isMinimalReflectiveMethods()) {
    stringBuffer.append(TEXT_919);
    } else {
    stringBuffer.append(TEXT_920);
    }
    stringBuffer.append(TEXT_921);
    }
    if (isImplementation && !genModel.isReflectiveDelegation() && genClass.implementsAny(genClass.getEInverseRemoveGenFeatures())) {
    stringBuffer.append(TEXT_922);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.notify.NotificationChain"));
    stringBuffer.append(TEXT_923);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.InternalEObject"));
    stringBuffer.append(TEXT_924);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.notify.NotificationChain"));
    stringBuffer.append(TEXT_925);
    for (Iterator i=genClass.getEInverseRemoveGenFeatures().iterator(); i.hasNext();) { GenFeature genFeature = (GenFeature)i.next();
    if (!genModel.isMinimalReflectiveMethods() || genClass.getImplementedGenFeatures().contains(genFeature)) {
    stringBuffer.append(TEXT_926);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(TEXT_927);
    if (genFeature.isListType()) {
    if (genFeature.isMapType() && genFeature.isEffectiveSuppressEMFTypes()) {
    stringBuffer.append(TEXT_928);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.InternalEList"));
    stringBuffer.append(TEXT_929);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.util.EMap"));
    stringBuffer.append(TEXT_930);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_931);
    } else if (genFeature.isWrappedFeatureMapType()) {
    stringBuffer.append(TEXT_932);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.InternalEList"));
    stringBuffer.append(TEXT_933);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.FeatureMap"));
    stringBuffer.append(TEXT_934);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_935);
    } else {
    stringBuffer.append(TEXT_936);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.InternalEList"));
    stringBuffer.append(TEXT_937);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_938);
    }
    } else if (genFeature.isContainer()) {
    stringBuffer.append(TEXT_939);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(TEXT_940);
    } else if (genFeature.isUnsettable()) {
    stringBuffer.append(TEXT_941);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_942);
    } else {
    stringBuffer.append(TEXT_943);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_944);
    }
    }
    }
    stringBuffer.append(TEXT_945);
    if (genModel.isMinimalReflectiveMethods()) {
    stringBuffer.append(TEXT_946);
    } else {
    stringBuffer.append(TEXT_947);
    }
    stringBuffer.append(TEXT_948);
    }
    if (isImplementation && !genModel.isReflectiveDelegation() && genClass.implementsAny(genClass.getEBasicRemoveFromContainerGenFeatures())) {
    stringBuffer.append(TEXT_949);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.notify.NotificationChain"));
    stringBuffer.append(TEXT_950);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.notify.NotificationChain"));
    stringBuffer.append(TEXT_951);
    for (Iterator i=genClass.getEBasicRemoveFromContainerGenFeatures().iterator(); i.hasNext();) { GenFeature genFeature = (GenFeature)i.next();
    GenFeature reverseFeature = genFeature.getReverse(); GenClass targetClass = reverseFeature.getGenClass();
    if (!genModel.isMinimalReflectiveMethods() || genClass.getImplementedGenFeatures().contains(genFeature)) {
    stringBuffer.append(TEXT_952);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(TEXT_953);
    stringBuffer.append(targetClass.getQualifiedFeatureID(reverseFeature));
    stringBuffer.append(TEXT_954);
    stringBuffer.append(targetClass.getImportedInterfaceName());
    stringBuffer.append(TEXT_955);
    }
    }
    stringBuffer.append(TEXT_956);
    if (genModel.isMinimalReflectiveMethods()) {
    stringBuffer.append(TEXT_957);
    } else {
    stringBuffer.append(TEXT_958);
    }
    stringBuffer.append(TEXT_959);
    }
    if (isImplementation && !genModel.isReflectiveDelegation() && !genClass.getImplementedGenFeatures().isEmpty()) {
    stringBuffer.append(TEXT_960);
    for (Iterator i=genClass.getAllGenFeatures().iterator(); i.hasNext();) { GenFeature genFeature = (GenFeature)i.next();
    if (!genModel.isMinimalReflectiveMethods() || genClass.getImplementedGenFeatures().contains(genFeature)) {
    stringBuffer.append(TEXT_961);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(TEXT_962);
    if (genFeature.isPrimitiveType()) {
    if (genFeature.isBooleanType()) {
    stringBuffer.append(TEXT_963);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_964);
    } else {
    stringBuffer.append(TEXT_965);
    stringBuffer.append(genFeature.getObjectType());
    stringBuffer.append(TEXT_966);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_967);
    }
    } else if (genFeature.isResolveProxies() && !genFeature.isListType()) {
    stringBuffer.append(TEXT_968);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_969);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_970);
    } else if (genFeature.isMapType()) {
    if (genFeature.isEffectiveSuppressEMFTypes()) {
    stringBuffer.append(TEXT_971);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.util.EMap"));
    stringBuffer.append(TEXT_972);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_973);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_974);
    } else {
    stringBuffer.append(TEXT_975);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_976);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_977);
    }
    } else if (genFeature.isWrappedFeatureMapType()) {
    stringBuffer.append(TEXT_978);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.FeatureMap"));
    stringBuffer.append(TEXT_979);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_980);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_981);
    } else if (genFeature.isFeatureMapType()) {
    stringBuffer.append(TEXT_982);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_983);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.FeatureMap"));
    stringBuffer.append(TEXT_984);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_985);
    } else {
    stringBuffer.append(TEXT_986);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_987);
    }
    }
    }
    stringBuffer.append(TEXT_988);
    if (genModel.isMinimalReflectiveMethods()) {
    stringBuffer.append(TEXT_989);
    } else {
    stringBuffer.append(TEXT_990);
    }
    stringBuffer.append(TEXT_991);
    }
    if (isImplementation && !genModel.isReflectiveDelegation() && genClass.implementsAny(genClass.getESetGenFeatures())) {
    stringBuffer.append(TEXT_992);
    for (Iterator i=genClass.getESetGenFeatures().iterator(); i.hasNext();) { GenFeature genFeature = (GenFeature)i.next();
    if (!genModel.isMinimalReflectiveMethods() || genClass.getImplementedGenFeatures().contains(genFeature)) {
    stringBuffer.append(TEXT_993);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(TEXT_994);
    if (genFeature.isListType()) {
    if (genFeature.isWrappedFeatureMapType()) {
    stringBuffer.append(TEXT_995);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.FeatureMap"));
    stringBuffer.append(TEXT_996);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.FeatureMap"));
    stringBuffer.append(TEXT_997);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_998);
    } else if (genFeature.isFeatureMapType()) {
    stringBuffer.append(TEXT_999);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.FeatureMap"));
    stringBuffer.append(TEXT_1000);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_1001);
    } else if (genFeature.isMapType()) {
    if (genFeature.isEffectiveSuppressEMFTypes()) {
    stringBuffer.append(TEXT_1002);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.EStructuralFeature"));
    stringBuffer.append(TEXT_1003);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.util.EMap"));
    stringBuffer.append(TEXT_1004);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_1005);
    } else {
    stringBuffer.append(TEXT_1006);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.EStructuralFeature"));
    stringBuffer.append(TEXT_1007);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_1008);
    }
    } else {
    stringBuffer.append(TEXT_1009);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_1010);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_1011);
    stringBuffer.append(genModel.getImportedName("java.util.Collection"));
    stringBuffer.append(TEXT_1012);
    }
    } else if (genFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_1013);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_1014);
    stringBuffer.append(genFeature.getObjectType());
    stringBuffer.append(TEXT_1015);
    stringBuffer.append(genFeature.getPrimitiveValueFunction());
    stringBuffer.append(TEXT_1016);
    } else {
    stringBuffer.append(TEXT_1017);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_1018);
    stringBuffer.append(genFeature.getImportedType());
    stringBuffer.append(TEXT_1019);
    }
    stringBuffer.append(TEXT_1020);
    }
    }
    stringBuffer.append(TEXT_1021);
    if (genModel.isMinimalReflectiveMethods()) {
    stringBuffer.append(TEXT_1022);
    } else {
    stringBuffer.append(TEXT_1023);
    }
    stringBuffer.append(TEXT_1024);
    for (Iterator i=genClass.getESetGenFeatures().iterator(); i.hasNext();) { GenFeature genFeature = (GenFeature)i.next();
    if (!genModel.isMinimalReflectiveMethods() || genClass.getImplementedGenFeatures().contains(genFeature)) {
    stringBuffer.append(TEXT_1025);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(TEXT_1026);
    if (genFeature.isListType() && !genFeature.isUnsettable()) {
    if (genFeature.isWrappedFeatureMapType()) {
    stringBuffer.append(TEXT_1027);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.FeatureMap"));
    stringBuffer.append(TEXT_1028);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_1029);
    } else {
    stringBuffer.append(TEXT_1030);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_1031);
    }
    } else if (genFeature.isUnsettable()) {
    stringBuffer.append(TEXT_1032);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_1033);
    } else if (genFeature.isReferenceType()) {
    stringBuffer.append(TEXT_1034);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_1035);
    stringBuffer.append(genFeature.getImportedType());
    stringBuffer.append(TEXT_1036);
    } else {
    stringBuffer.append(TEXT_1037);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_1038);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_1039);
    }
    stringBuffer.append(TEXT_1040);
    }
    }
    stringBuffer.append(TEXT_1041);
    if (genModel.isMinimalReflectiveMethods()) {
    stringBuffer.append(TEXT_1042);
    } else {
    stringBuffer.append(TEXT_1043);
    }
    stringBuffer.append(TEXT_1044);
    }
    if (isImplementation && !genModel.isReflectiveDelegation() && !genClass.getImplementedGenFeatures().isEmpty()) {
    stringBuffer.append(TEXT_1045);
    for (Iterator i=genClass.getAllGenFeatures().iterator(); i.hasNext();) { GenFeature genFeature = (GenFeature)i.next();
    if (!genModel.isMinimalReflectiveMethods() || genClass.getImplementedGenFeatures().contains(genFeature)) {
    stringBuffer.append(TEXT_1046);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(TEXT_1047);
    if (genFeature.isListType() && !genFeature.isUnsettable()) {
    if (genFeature.isWrappedFeatureMapType()) {
    if (genFeature.isVolatile()) {
    stringBuffer.append(TEXT_1048);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.FeatureMap"));
    stringBuffer.append(TEXT_1049);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_1050);
    } else {
    stringBuffer.append(TEXT_1051);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_1052);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_1053);
    }
    } else {
    if (genClass.isField(genFeature)) {
    stringBuffer.append(TEXT_1054);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_1055);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_1056);
    } else {
    if (genFeature.isField() && genClass.getImplementingGenModel(genFeature).isVirtualDelegation()) {
    stringBuffer.append(TEXT_1057);
    stringBuffer.append(genFeature.getImportedType());
    stringBuffer.append(TEXT_1058);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_1059);
    stringBuffer.append(genFeature.getImportedType());
    stringBuffer.append(TEXT_1060);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(TEXT_1061);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_1062);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_1063);
    } else {
    stringBuffer.append(TEXT_1064);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_1065);
    }
    }
    }
    } else if (genFeature.isUnsettable()) {
    stringBuffer.append(TEXT_1066);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_1067);
    } else if (genFeature.isResolveProxies()) {
    if (genClass.isField(genFeature)) {
    stringBuffer.append(TEXT_1068);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_1069);
    } else {
    if (genFeature.isField() && genClass.getImplementingGenModel(genFeature).isVirtualDelegation()) {
    stringBuffer.append(TEXT_1070);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(TEXT_1071);
    } else {
    stringBuffer.append(TEXT_1072);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_1073);
    }
    }
    } else if (genFeature.isReferenceType()) {
    if (genClass.isField(genFeature)) {
    stringBuffer.append(TEXT_1074);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_1075);
    } else {
    if (genFeature.isField() && genClass.getImplementingGenModel(genFeature).isVirtualDelegation()) {
    stringBuffer.append(TEXT_1076);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(TEXT_1077);
    } else {
    stringBuffer.append(TEXT_1078);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_1079);
    }
    }
    } else if (genFeature.isPrimitiveType() || genFeature.isEnumType()) {
    if (genClass.isField(genFeature)) {
    if (genClass.isFlag(genFeature)) {
    stringBuffer.append(TEXT_1080);
    stringBuffer.append(genClass.getFlagsField(genFeature));
    stringBuffer.append(TEXT_1081);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_1082);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_1083);
    } else {
    stringBuffer.append(TEXT_1084);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_1085);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_1086);
    }
    } else {
    if (genFeature.isEnumType() && genFeature.isField() && genClass.getImplementingGenModel(genFeature).isVirtualDelegation()) {
    stringBuffer.append(TEXT_1087);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(TEXT_1088);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_1089);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_1090);
    } else {
    stringBuffer.append(TEXT_1091);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_1092);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_1093);
    }
    }
    } else {//datatype
    if (genClass.isField(genFeature)) {
    stringBuffer.append(TEXT_1094);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_1095);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_1096);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_1097);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_1098);
    } else {
    if (genFeature.isField() && genClass.getImplementingGenModel(genFeature).isVirtualDelegation()) {
    stringBuffer.append(TEXT_1099);
    stringBuffer.append(genFeature.getImportedType());
    stringBuffer.append(TEXT_1100);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_1101);
    stringBuffer.append(genFeature.getImportedType());
    stringBuffer.append(TEXT_1102);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(TEXT_1103);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_1104);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_1105);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_1106);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_1107);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_1108);
    } else {
    stringBuffer.append(TEXT_1109);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_1110);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_1111);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_1112);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_1113);
    }
    }
    }
    }
    }
    stringBuffer.append(TEXT_1114);
    if (genModel.isMinimalReflectiveMethods()) {
    stringBuffer.append(TEXT_1115);
    } else {
    stringBuffer.append(TEXT_1116);
    }
    stringBuffer.append(TEXT_1117);
    //Class/eIsSet.override.javajetinc
    }
    if (isImplementation && !genClass.getMixinGenFeatures().isEmpty()) {
    stringBuffer.append(TEXT_1118);
    for (Iterator m=genClass.getMixinGenClasses().iterator(); m.hasNext();) { GenClass mixinGenClass = (GenClass)m.next(); 
    stringBuffer.append(TEXT_1119);
    stringBuffer.append(mixinGenClass.getImportedInterfaceName());
    stringBuffer.append(TEXT_1120);
    for (Iterator f=mixinGenClass.getGenFeatures().iterator(); f.hasNext();) { GenFeature genFeature = (GenFeature)f.next(); 
    stringBuffer.append(TEXT_1121);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(TEXT_1122);
    stringBuffer.append(mixinGenClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(TEXT_1123);
    }
    stringBuffer.append(TEXT_1124);
    }
    stringBuffer.append(TEXT_1125);
    for (Iterator m=genClass.getMixinGenClasses().iterator(); m.hasNext();) { GenClass mixinGenClass = (GenClass)m.next(); 
    stringBuffer.append(TEXT_1126);
    stringBuffer.append(mixinGenClass.getImportedInterfaceName());
    stringBuffer.append(TEXT_1127);
    for (Iterator f=mixinGenClass.getGenFeatures().iterator(); f.hasNext();) { GenFeature genFeature = (GenFeature)f.next(); 
    stringBuffer.append(TEXT_1128);
    stringBuffer.append(mixinGenClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(TEXT_1129);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(TEXT_1130);
    }
    stringBuffer.append(TEXT_1131);
    }
    stringBuffer.append(TEXT_1132);
    }
    if (isImplementation && genModel.isVirtualDelegation()) { String eVirtualValuesField = genClass.getEVirtualValuesField();
    if (eVirtualValuesField != null) {
    stringBuffer.append(TEXT_1133);
    stringBuffer.append(eVirtualValuesField);
    stringBuffer.append(TEXT_1134);
    stringBuffer.append(eVirtualValuesField);
    stringBuffer.append(TEXT_1135);
    }
    { List eVirtualIndexBitFields = genClass.getEVirtualIndexBitFields(new ArrayList());
    if (!eVirtualIndexBitFields.isEmpty()) { List allEVirtualIndexBitFields = genClass.getAllEVirtualIndexBitFields(new ArrayList());
    stringBuffer.append(TEXT_1136);
    for (int i = 0; i < allEVirtualIndexBitFields.size(); i++) {
    stringBuffer.append(TEXT_1137);
    stringBuffer.append(i);
    stringBuffer.append(TEXT_1138);
    stringBuffer.append(allEVirtualIndexBitFields.get(i));
    stringBuffer.append(TEXT_1139);
    }
    stringBuffer.append(TEXT_1140);
    for (int i = 0; i < allEVirtualIndexBitFields.size(); i++) {
    stringBuffer.append(TEXT_1141);
    stringBuffer.append(i);
    stringBuffer.append(TEXT_1142);
    stringBuffer.append(allEVirtualIndexBitFields.get(i));
    stringBuffer.append(TEXT_1143);
    }
    stringBuffer.append(TEXT_1144);
    }
    }
    }
    if (isImplementation && !genModel.isReflectiveDelegation() && !genClass.getToStringGenFeatures().isEmpty()) {
    stringBuffer.append(TEXT_1145);
    { boolean first = true;
    for (Iterator i=genClass.getToStringGenFeatures().iterator(); i.hasNext(); ) { GenFeature genFeature = (GenFeature)i.next();
    if (first) { first = false;
    stringBuffer.append(TEXT_1146);
    stringBuffer.append(genFeature.getName());
    stringBuffer.append(TEXT_1147);
    stringBuffer.append(genModel.getNonNLS());
    } else {
    stringBuffer.append(TEXT_1148);
    stringBuffer.append(genFeature.getName());
    stringBuffer.append(TEXT_1149);
    stringBuffer.append(genModel.getNonNLS());
    }
    if (genFeature.isUnsettable() && !genFeature.isListType()) {
    if (genModel.isVirtualDelegation() && !genFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_1150);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(TEXT_1151);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(TEXT_1152);
    stringBuffer.append(genModel.getNonNLS());
    } else {
    if (genClass.isFlag(genFeature)) {
    stringBuffer.append(TEXT_1153);
    if (genClass.isESetFlag(genFeature)) {
    stringBuffer.append(TEXT_1154);
    stringBuffer.append(genClass.getESetFlagsField(genFeature));
    stringBuffer.append(TEXT_1155);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_1156);
    } else {
    stringBuffer.append(genFeature.getUncapName());
    stringBuffer.append(TEXT_1157);
    }
    stringBuffer.append(TEXT_1158);
    stringBuffer.append(genClass.getFlagsField(genFeature));
    stringBuffer.append(TEXT_1159);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_1160);
    stringBuffer.append(genModel.getNonNLS());
    } else {
    stringBuffer.append(TEXT_1161);
    if (genClass.isESetFlag(genFeature)) {
    stringBuffer.append(TEXT_1162);
    stringBuffer.append(genClass.getESetFlagsField(genFeature));
    stringBuffer.append(TEXT_1163);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_1164);
    } else {
    stringBuffer.append(genFeature.getUncapName());
    stringBuffer.append(TEXT_1165);
    }
    stringBuffer.append(TEXT_1166);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_1167);
    stringBuffer.append(genModel.getNonNLS());
    }
    }
    } else {
    if (genModel.isVirtualDelegation() && !genFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_1168);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    if (!genFeature.isListType() && !genFeature.isReferenceType()){
    stringBuffer.append(TEXT_1169);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_1170);
    }
    stringBuffer.append(TEXT_1171);
    } else {
    if (genClass.isFlag(genFeature)) {
    stringBuffer.append(TEXT_1172);
    stringBuffer.append(genClass.getFlagsField(genFeature));
    stringBuffer.append(TEXT_1173);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_1174);
    } else {
    stringBuffer.append(TEXT_1175);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_1176);
    }
    }
    }
    }
    }
    stringBuffer.append(TEXT_1177);
    }
    if (isImplementation && genClass.isMapEntry()) { GenFeature keyFeature = genClass.getMapEntryKeyFeature(); GenFeature valueFeature = genClass.getMapEntryValueFeature();
    stringBuffer.append(TEXT_1178);
    if (keyFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_1179);
    stringBuffer.append(keyFeature.getObjectType());
    stringBuffer.append(TEXT_1180);
    } else {
    stringBuffer.append(TEXT_1181);
    }
    stringBuffer.append(TEXT_1182);
    if (keyFeature.isListType()) {
    stringBuffer.append(TEXT_1183);
    stringBuffer.append(genModel.getImportedName("java.util.Collection"));
    stringBuffer.append(TEXT_1184);
    } else if (keyFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_1185);
    stringBuffer.append(keyFeature.getObjectType());
    stringBuffer.append(TEXT_1186);
    stringBuffer.append(keyFeature.getPrimitiveValueFunction());
    stringBuffer.append(TEXT_1187);
    } else {
    stringBuffer.append(TEXT_1188);
    stringBuffer.append(keyFeature.getImportedType());
    stringBuffer.append(TEXT_1189);
    }
    stringBuffer.append(TEXT_1190);
    if (valueFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_1191);
    stringBuffer.append(valueFeature.getObjectType());
    stringBuffer.append(TEXT_1192);
    } else {
    stringBuffer.append(TEXT_1193);
    }
    stringBuffer.append(TEXT_1194);
    if (valueFeature.isListType()) {
    stringBuffer.append(TEXT_1195);
    stringBuffer.append(genModel.getImportedName("java.util.Collection"));
    stringBuffer.append(TEXT_1196);
    } else if (valueFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_1197);
    stringBuffer.append(valueFeature.getObjectType());
    stringBuffer.append(TEXT_1198);
    stringBuffer.append(valueFeature.getPrimitiveValueFunction());
    stringBuffer.append(TEXT_1199);
    } else {
    stringBuffer.append(TEXT_1200);
    stringBuffer.append(valueFeature.getImportedType());
    stringBuffer.append(TEXT_1201);
    }
    stringBuffer.append(TEXT_1202);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.util.EMap"));
    stringBuffer.append(TEXT_1203);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.EObject"));
    stringBuffer.append(TEXT_1204);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.util.EMap"));
    stringBuffer.append(TEXT_1205);
    }
    stringBuffer.append(TEXT_1206);
    stringBuffer.append(isInterface ? " " + genClass.getInterfaceName() : genClass.getClassName());
    // TODO fix the space above
    genModel.emitSortedImports();
    stringBuffer.append(TEXT_1207);
    return stringBuffer.toString();
  }
}
