package org.eclipse.emf.codegen.ecore.templates.model;

import java.util.*;
import org.eclipse.emf.codegen.ecore.genmodel.*;

public class Class
{
  protected final String NL = System.getProperties().getProperty("line.separator");
  protected final String TEXT_1 = "";
  protected final String TEXT_2 = "/**" + NL + " * <copyright>" + NL + " * </copyright>" + NL + " *" + NL + " * ";
  protected final String TEXT_3 = "Id";
  protected final String TEXT_4 = NL + " */" + NL + "package ";
  protected final String TEXT_5 = ";" + NL;
  protected final String TEXT_6 = NL + NL + "/**" + NL + " * <!-- begin-user-doc -->" + NL + " * An implementation of the model object '<em><b>";
  protected final String TEXT_7 = "</b></em>'." + NL + " * <!-- end-user-doc -->" + NL + " * <p>";
  protected final String TEXT_8 = NL + " * The following features are implemented:" + NL + " * <ul>";
  protected final String TEXT_9 = NL + " *   <li>{@link ";
  protected final String TEXT_10 = "#";
  protected final String TEXT_11 = " <em>";
  protected final String TEXT_12 = "</em>}</li>";
  protected final String TEXT_13 = NL + " * </ul>";
  protected final String TEXT_14 = NL + " * </p>" + NL + " *" + NL + " * @generated" + NL + " */" + NL + "public";
  protected final String TEXT_15 = " abstract";
  protected final String TEXT_16 = " class ";
  protected final String TEXT_17 = NL + "{";
  protected final String TEXT_18 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */" + NL + "\tpublic static final ";
  protected final String TEXT_19 = " copyright = \"";
  protected final String TEXT_20 = "\";";
  protected final String TEXT_21 = NL;
  protected final String TEXT_22 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */" + NL + "\tpublic static final ";
  protected final String TEXT_23 = " mofDriverNumber = \"";
  protected final String TEXT_24 = "\";";
  protected final String TEXT_25 = NL;
  protected final String TEXT_26 = NL + "\t/**" + NL + "\t * The cached value of the '{@link #";
  protected final String TEXT_27 = "() <em>";
  protected final String TEXT_28 = "</em>}' ";
  protected final String TEXT_29 = "." + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @see #";
  protected final String TEXT_30 = "()" + NL + "\t * @generated" + NL + "\t * @ordered" + NL + "\t */" + NL + "\tprotected ";
  protected final String TEXT_31 = " ";
  protected final String TEXT_32 = " = null;" + NL;
  protected final String TEXT_33 = NL + "\t/**" + NL + "\t * The default value of the '{@link #";
  protected final String TEXT_34 = "() <em>";
  protected final String TEXT_35 = "</em>}' ";
  protected final String TEXT_36 = "." + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @see #";
  protected final String TEXT_37 = "()" + NL + "\t * @generated" + NL + "\t * @ordered" + NL + "\t */";
  protected final String TEXT_38 = NL + "\tprotected static final ";
  protected final String TEXT_39 = " ";
  protected final String TEXT_40 = "_EDEFAULT = ";
  protected final String TEXT_41 = ";";
  protected final String TEXT_42 = NL + "\tprotected static final ";
  protected final String TEXT_43 = " ";
  protected final String TEXT_44 = "_EDEFAULT = ";
  protected final String TEXT_45 = "(";
  protected final String TEXT_46 = "(";
  protected final String TEXT_47 = ")";
  protected final String TEXT_48 = ".eINSTANCE.createFromString(";
  protected final String TEXT_49 = ".eINSTANCE.get";
  protected final String TEXT_50 = "(), ";
  protected final String TEXT_51 = ")";
  protected final String TEXT_52 = ").";
  protected final String TEXT_53 = "Value()";
  protected final String TEXT_54 = ";";
  protected final String TEXT_55 = NL + "\tprotected static final ";
  protected final String TEXT_56 = " ";
  protected final String TEXT_57 = "_EDEFAULT = null;";
  protected final String TEXT_58 = NL + NL + "\t/**" + NL + "\t * The cached value of the '{@link #";
  protected final String TEXT_59 = "() <em>";
  protected final String TEXT_60 = "</em>}' ";
  protected final String TEXT_61 = "." + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @see #";
  protected final String TEXT_62 = "()" + NL + "\t * @generated" + NL + "\t * @ordered" + NL + "\t */" + NL + "\tprotected ";
  protected final String TEXT_63 = " ";
  protected final String TEXT_64 = " = ";
  protected final String TEXT_65 = "_EDEFAULT;" + NL;
  protected final String TEXT_66 = NL + "\t/**" + NL + "\t * This is true if the ";
  protected final String TEXT_67 = " ";
  protected final String TEXT_68 = " has been set." + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t * @ordered" + NL + "\t */" + NL + "\tprotected boolean ";
  protected final String TEXT_69 = "ESet = false;" + NL;
  protected final String TEXT_70 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */" + NL + "\tprotected ";
  protected final String TEXT_71 = "()" + NL + "\t{" + NL + "\t\tsuper();" + NL + "\t}" + NL + "" + NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */" + NL + "\tprotected ";
  protected final String TEXT_72 = " eStaticClass()" + NL + "\t{" + NL + "\t\treturn ";
  protected final String TEXT_73 = ".eINSTANCE.get";
  protected final String TEXT_74 = "();" + NL + "\t}" + NL;
  protected final String TEXT_75 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */" + NL + "\tpublic ";
  protected final String TEXT_76 = " ";
  protected final String TEXT_77 = "()" + NL + "\t{";
  protected final String TEXT_78 = NL + "\t\tif (";
  protected final String TEXT_79 = " == null)" + NL + "\t\t{";
  protected final String TEXT_80 = NL + "\t\t\t";
  protected final String TEXT_81 = " = new ";
  protected final String TEXT_82 = "(";
  protected final String TEXT_83 = ".eINSTANCE.get";
  protected final String TEXT_84 = "(), ";
  protected final String TEXT_85 = ".class, this, ";
  protected final String TEXT_86 = ");";
  protected final String TEXT_87 = NL + "\t\t\t";
  protected final String TEXT_88 = " = new ";
  protected final String TEXT_89 = "(";
  protected final String TEXT_90 = ".class, this, ";
  protected final String TEXT_91 = ", ";
  protected final String TEXT_92 = ");";
  protected final String TEXT_93 = NL + "\t\t\t";
  protected final String TEXT_94 = " = new ";
  protected final String TEXT_95 = "(";
  protected final String TEXT_96 = ".class, this, ";
  protected final String TEXT_97 = ");";
  protected final String TEXT_98 = NL + "\t\t\t";
  protected final String TEXT_99 = " = new ";
  protected final String TEXT_100 = ".ManyInverse(";
  protected final String TEXT_101 = ".class, this, ";
  protected final String TEXT_102 = ", ";
  protected final String TEXT_103 = ");";
  protected final String TEXT_104 = NL + "\t\t\t";
  protected final String TEXT_105 = " = new ";
  protected final String TEXT_106 = ".ManyInverse(";
  protected final String TEXT_107 = ".class, this, ";
  protected final String TEXT_108 = ", ";
  protected final String TEXT_109 = ");";
  protected final String TEXT_110 = NL + "\t\t\t";
  protected final String TEXT_111 = " = new ";
  protected final String TEXT_112 = "(";
  protected final String TEXT_113 = ".class, this, ";
  protected final String TEXT_114 = ", ";
  protected final String TEXT_115 = ");";
  protected final String TEXT_116 = NL + "\t\t\t";
  protected final String TEXT_117 = " = new ";
  protected final String TEXT_118 = "(";
  protected final String TEXT_119 = ".class, this, ";
  protected final String TEXT_120 = ", ";
  protected final String TEXT_121 = ");";
  protected final String TEXT_122 = NL + "\t\t\t";
  protected final String TEXT_123 = " = new ";
  protected final String TEXT_124 = "(";
  protected final String TEXT_125 = ".class, this, ";
  protected final String TEXT_126 = ");";
  protected final String TEXT_127 = NL + "\t\t\t";
  protected final String TEXT_128 = " = new ";
  protected final String TEXT_129 = "(";
  protected final String TEXT_130 = ".class, this, ";
  protected final String TEXT_131 = ");";
  protected final String TEXT_132 = NL + "\t\t\t";
  protected final String TEXT_133 = " = new ";
  protected final String TEXT_134 = "(new ";
  protected final String TEXT_135 = "(this, ";
  protected final String TEXT_136 = "));";
  protected final String TEXT_137 = NL + "\t\t\t";
  protected final String TEXT_138 = " = new ";
  protected final String TEXT_139 = "(this, ";
  protected final String TEXT_140 = ");";
  protected final String TEXT_141 = NL + "\t\t\t";
  protected final String TEXT_142 = " = new ";
  protected final String TEXT_143 = "(";
  protected final String TEXT_144 = ".class, this, ";
  protected final String TEXT_145 = ");";
  protected final String TEXT_146 = NL + "\t\t\t";
  protected final String TEXT_147 = " = new ";
  protected final String TEXT_148 = "(";
  protected final String TEXT_149 = ".class, this, ";
  protected final String TEXT_150 = ");";
  protected final String TEXT_151 = NL + "\t\t}" + NL + "\t\treturn ";
  protected final String TEXT_152 = ";";
  protected final String TEXT_153 = NL + "\t\tif (eContainerFeatureID != ";
  protected final String TEXT_154 = ") return null;" + NL + "\t\treturn (";
  protected final String TEXT_155 = ")eContainer;";
  protected final String TEXT_156 = NL + "\t\tif (";
  protected final String TEXT_157 = " != null && ";
  protected final String TEXT_158 = ".eIsProxy())" + NL + "\t\t{" + NL + "\t\t\t";
  protected final String TEXT_159 = " old";
  protected final String TEXT_160 = " = ";
  protected final String TEXT_161 = ";" + NL + "\t\t\t";
  protected final String TEXT_162 = " = (";
  protected final String TEXT_163 = ")eResolveProxy((";
  protected final String TEXT_164 = ")";
  protected final String TEXT_165 = ");" + NL + "\t\t\tif (";
  protected final String TEXT_166 = " != old";
  protected final String TEXT_167 = ")" + NL + "\t\t\t{" + NL + "\t\t\t\tif (eNotificationRequired())" + NL + "\t\t\t\t\teNotify(new ";
  protected final String TEXT_168 = "(this, ";
  protected final String TEXT_169 = ".RESOLVE, ";
  protected final String TEXT_170 = ", old";
  protected final String TEXT_171 = ", ";
  protected final String TEXT_172 = "));" + NL + "\t\t\t}" + NL + "\t\t}";
  protected final String TEXT_173 = NL + "\t\treturn ";
  protected final String TEXT_174 = ";";
  protected final String TEXT_175 = NL + "\t\t";
  protected final String TEXT_176 = " ";
  protected final String TEXT_177 = " = basicGet";
  protected final String TEXT_178 = "();" + NL + "\t\treturn ";
  protected final String TEXT_179 = " == null ? null : (";
  protected final String TEXT_180 = ")eResolveProxy((";
  protected final String TEXT_181 = ")";
  protected final String TEXT_182 = ");";
  protected final String TEXT_183 = NL + "\t\treturn new ";
  protected final String TEXT_184 = "((";
  protected final String TEXT_185 = ")((";
  protected final String TEXT_186 = ")get";
  protected final String TEXT_187 = "()).featureMap().list(";
  protected final String TEXT_188 = "()));";
  protected final String TEXT_189 = NL + "\t\treturn (";
  protected final String TEXT_190 = ")((";
  protected final String TEXT_191 = ")get";
  protected final String TEXT_192 = "()).list(";
  protected final String TEXT_193 = "());";
  protected final String TEXT_194 = NL + "\t\treturn ((";
  protected final String TEXT_195 = ")get";
  protected final String TEXT_196 = "()).featureMap().list(";
  protected final String TEXT_197 = "());";
  protected final String TEXT_198 = NL + "\t\treturn ((";
  protected final String TEXT_199 = ")get";
  protected final String TEXT_200 = "()).list(";
  protected final String TEXT_201 = "());";
  protected final String TEXT_202 = NL + "\t\treturn ";
  protected final String TEXT_203 = "(";
  protected final String TEXT_204 = "(";
  protected final String TEXT_205 = ")((";
  protected final String TEXT_206 = ")get";
  protected final String TEXT_207 = "()).featureMap().get(";
  protected final String TEXT_208 = "(),true)";
  protected final String TEXT_209 = ").";
  protected final String TEXT_210 = "()";
  protected final String TEXT_211 = ";";
  protected final String TEXT_212 = NL + "\t\treturn ";
  protected final String TEXT_213 = "(";
  protected final String TEXT_214 = "(";
  protected final String TEXT_215 = ")get";
  protected final String TEXT_216 = "().get(";
  protected final String TEXT_217 = "(), true)";
  protected final String TEXT_218 = ").";
  protected final String TEXT_219 = "()";
  protected final String TEXT_220 = ";";
  protected final String TEXT_221 = NL + "\t\t// TODO: implement this method to return the '";
  protected final String TEXT_222 = "' ";
  protected final String TEXT_223 = NL + "\t\t// Ensure that you remove @generated or mark it @generated NOT" + NL + "\t\tthrow new UnsupportedOperationException();";
  protected final String TEXT_224 = NL + "\t}" + NL;
  protected final String TEXT_225 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */" + NL + "\tpublic ";
  protected final String TEXT_226 = " basicGet";
  protected final String TEXT_227 = "()" + NL + "\t{";
  protected final String TEXT_228 = NL + "\t\treturn ";
  protected final String TEXT_229 = ";";
  protected final String TEXT_230 = NL + "\t\treturn (";
  protected final String TEXT_231 = ")((";
  protected final String TEXT_232 = ")get";
  protected final String TEXT_233 = "()).featureMap().get(";
  protected final String TEXT_234 = "(), false);";
  protected final String TEXT_235 = NL + "\t\treturn (";
  protected final String TEXT_236 = ")get";
  protected final String TEXT_237 = "().get(";
  protected final String TEXT_238 = "(), false);";
  protected final String TEXT_239 = NL + "\t\t// TODO: implement this method to return the '";
  protected final String TEXT_240 = "' ";
  protected final String TEXT_241 = NL + "\t\t// -> do not perform proxy resolution" + NL + "\t\t// Ensure that you remove @generated or mark it @generated NOT" + NL + "\t\tthrow new UnsupportedOperationException();";
  protected final String TEXT_242 = NL + "\t}" + NL;
  protected final String TEXT_243 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */" + NL + "\tpublic ";
  protected final String TEXT_244 = " basicSet";
  protected final String TEXT_245 = "(";
  protected final String TEXT_246 = " new";
  protected final String TEXT_247 = ", ";
  protected final String TEXT_248 = " msgs)" + NL + "\t{";
  protected final String TEXT_249 = NL + "\t\t";
  protected final String TEXT_250 = " old";
  protected final String TEXT_251 = " = ";
  protected final String TEXT_252 = ";" + NL + "\t\t";
  protected final String TEXT_253 = " = new";
  protected final String TEXT_254 = ";";
  protected final String TEXT_255 = NL + "\t\tboolean old";
  protected final String TEXT_256 = "ESet = ";
  protected final String TEXT_257 = "ESet;" + NL + "\t\t";
  protected final String TEXT_258 = "ESet = true;";
  protected final String TEXT_259 = NL + "\t\tif (eNotificationRequired())" + NL + "\t\t{";
  protected final String TEXT_260 = NL + "\t\t\t";
  protected final String TEXT_261 = " notification = new ";
  protected final String TEXT_262 = "(this, ";
  protected final String TEXT_263 = ".SET, ";
  protected final String TEXT_264 = ", old";
  protected final String TEXT_265 = ", new";
  protected final String TEXT_266 = ", !old";
  protected final String TEXT_267 = "ESet);";
  protected final String TEXT_268 = NL + "\t\t\t";
  protected final String TEXT_269 = " notification = new ";
  protected final String TEXT_270 = "(this, ";
  protected final String TEXT_271 = ".SET, ";
  protected final String TEXT_272 = ", old";
  protected final String TEXT_273 = ", new";
  protected final String TEXT_274 = ");";
  protected final String TEXT_275 = NL + "\t\t\tif (msgs == null) msgs = notification; else msgs.add(notification);" + NL + "\t\t}" + NL + "\t\treturn msgs;";
  protected final String TEXT_276 = NL + "\t\treturn ((";
  protected final String TEXT_277 = ".Internal)((";
  protected final String TEXT_278 = ")get";
  protected final String TEXT_279 = "()).featureMap()).basicAdd(";
  protected final String TEXT_280 = "(), new";
  protected final String TEXT_281 = ", null);";
  protected final String TEXT_282 = NL + "\t\treturn ((";
  protected final String TEXT_283 = ".Internal)get";
  protected final String TEXT_284 = "()).basicAdd(";
  protected final String TEXT_285 = "(), new";
  protected final String TEXT_286 = ", null);";
  protected final String TEXT_287 = NL + "\t\t// TODO: implement this method to set the contained '";
  protected final String TEXT_288 = "' ";
  protected final String TEXT_289 = NL + "\t\t// -> this method is automatically invoked to keep the containment relationship in synch" + NL + "\t\t// -> do not modify other features" + NL + "\t\t// -> return msgs, after adding any generated Notification to it (if it is null, a NotificationChain object must be created first)" + NL + "\t\t// Ensure that you remove @generated or mark it @generated NOT" + NL + "\t\tthrow new UnsupportedOperationException();";
  protected final String TEXT_290 = NL + "\t}" + NL;
  protected final String TEXT_291 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */" + NL + "\tpublic void set";
  protected final String TEXT_292 = "(";
  protected final String TEXT_293 = " new";
  protected final String TEXT_294 = ")" + NL + "\t{";
  protected final String TEXT_295 = NL + "\t\tif (new";
  protected final String TEXT_296 = " != eContainer || (eContainerFeatureID != ";
  protected final String TEXT_297 = " && new";
  protected final String TEXT_298 = " != null))" + NL + "\t\t{" + NL + "\t\t\tif (";
  protected final String TEXT_299 = ".isAncestor(this, ";
  protected final String TEXT_300 = "new";
  protected final String TEXT_301 = "))" + NL + "\t\t\t\tthrow new ";
  protected final String TEXT_302 = "(\"Recursive containment not allowed for \" + toString());";
  protected final String TEXT_303 = NL + "\t\t\t";
  protected final String TEXT_304 = " msgs = null;" + NL + "\t\t\tif (eContainer != null)" + NL + "\t\t\t\tmsgs = eBasicRemoveFromContainer(msgs);" + NL + "\t\t\tif (new";
  protected final String TEXT_305 = " != null)" + NL + "\t\t\t\tmsgs = ((";
  protected final String TEXT_306 = ")new";
  protected final String TEXT_307 = ").eInverseAdd(this, ";
  protected final String TEXT_308 = ", ";
  protected final String TEXT_309 = ".class, msgs);" + NL + "\t\t\tmsgs = eBasicSetContainer((";
  protected final String TEXT_310 = ")new";
  protected final String TEXT_311 = ", ";
  protected final String TEXT_312 = ", msgs);" + NL + "\t\t\tif (msgs != null) msgs.dispatch();" + NL + "\t\t}" + NL + "\t\telse if (eNotificationRequired())" + NL + "\t\t\teNotify(new ";
  protected final String TEXT_313 = "(this, ";
  protected final String TEXT_314 = ".SET, ";
  protected final String TEXT_315 = ", new";
  protected final String TEXT_316 = ", new";
  protected final String TEXT_317 = "));";
  protected final String TEXT_318 = NL + "\t\tif (new";
  protected final String TEXT_319 = " != ";
  protected final String TEXT_320 = ")" + NL + "\t\t{" + NL + "\t\t\t";
  protected final String TEXT_321 = " msgs = null;" + NL + "\t\t\tif (";
  protected final String TEXT_322 = " != null)";
  protected final String TEXT_323 = NL + "\t\t\t\tmsgs = ((";
  protected final String TEXT_324 = ")";
  protected final String TEXT_325 = ").eInverseRemove(this, EOPPOSITE_FEATURE_BASE - ";
  protected final String TEXT_326 = ", null, msgs);" + NL + "\t\t\tif (new";
  protected final String TEXT_327 = " != null)" + NL + "\t\t\t\tmsgs = ((";
  protected final String TEXT_328 = ")new";
  protected final String TEXT_329 = ").eInverseAdd(this, EOPPOSITE_FEATURE_BASE - ";
  protected final String TEXT_330 = ", null, msgs);";
  protected final String TEXT_331 = NL + "\t\t\t\tmsgs = ((";
  protected final String TEXT_332 = ")";
  protected final String TEXT_333 = ").eInverseRemove(this, ";
  protected final String TEXT_334 = ", ";
  protected final String TEXT_335 = ".class, msgs);" + NL + "\t\t\tif (new";
  protected final String TEXT_336 = " != null)" + NL + "\t\t\t\tmsgs = ((";
  protected final String TEXT_337 = ")new";
  protected final String TEXT_338 = ").eInverseAdd(this, ";
  protected final String TEXT_339 = ", ";
  protected final String TEXT_340 = ".class, msgs);";
  protected final String TEXT_341 = NL + "\t\t\tmsgs = basicSet";
  protected final String TEXT_342 = "(";
  protected final String TEXT_343 = "new";
  protected final String TEXT_344 = ", msgs);" + NL + "\t\t\tif (msgs != null) msgs.dispatch();" + NL + "\t\t}";
  protected final String TEXT_345 = NL + "\t\telse" + NL + "    \t{" + NL + "\t\t\tboolean old";
  protected final String TEXT_346 = "ESet = ";
  protected final String TEXT_347 = "ESet;" + NL + "\t\t\t";
  protected final String TEXT_348 = "ESet = true;" + NL + "\t\t\tif (eNotificationRequired())" + NL + "\t\t\t\teNotify(new ";
  protected final String TEXT_349 = "(this, ";
  protected final String TEXT_350 = ".SET, ";
  protected final String TEXT_351 = ", new";
  protected final String TEXT_352 = ", new";
  protected final String TEXT_353 = ", !old";
  protected final String TEXT_354 = "ESet));" + NL + "    \t}";
  protected final String TEXT_355 = NL + "\t\telse if (eNotificationRequired())" + NL + "\t\t\teNotify(new ";
  protected final String TEXT_356 = "(this, ";
  protected final String TEXT_357 = ".SET, ";
  protected final String TEXT_358 = ", new";
  protected final String TEXT_359 = ", new";
  protected final String TEXT_360 = "));";
  protected final String TEXT_361 = NL + "\t\t";
  protected final String TEXT_362 = " old";
  protected final String TEXT_363 = " = ";
  protected final String TEXT_364 = ";";
  protected final String TEXT_365 = NL + "\t\t";
  protected final String TEXT_366 = " = new";
  protected final String TEXT_367 = " == null ? ";
  protected final String TEXT_368 = "_EDEFAULT : new";
  protected final String TEXT_369 = ";";
  protected final String TEXT_370 = NL + "\t\t";
  protected final String TEXT_371 = " = ";
  protected final String TEXT_372 = "new";
  protected final String TEXT_373 = ";";
  protected final String TEXT_374 = NL + "\t\tboolean old";
  protected final String TEXT_375 = "ESet = ";
  protected final String TEXT_376 = "ESet;" + NL + "\t\t";
  protected final String TEXT_377 = "ESet = true;" + NL + "\t\tif (eNotificationRequired())" + NL + "\t\t\teNotify(new ";
  protected final String TEXT_378 = "(this, ";
  protected final String TEXT_379 = ".SET, ";
  protected final String TEXT_380 = ", old";
  protected final String TEXT_381 = ", ";
  protected final String TEXT_382 = ", !old";
  protected final String TEXT_383 = "ESet));";
  protected final String TEXT_384 = NL + "\t\tif (eNotificationRequired())" + NL + "\t\t\teNotify(new ";
  protected final String TEXT_385 = "(this, ";
  protected final String TEXT_386 = ".SET, ";
  protected final String TEXT_387 = ", old";
  protected final String TEXT_388 = ", ";
  protected final String TEXT_389 = "));";
  protected final String TEXT_390 = NL + "\t\t((";
  protected final String TEXT_391 = ".Internal)((";
  protected final String TEXT_392 = ")get";
  protected final String TEXT_393 = "()).featureMap()).set(";
  protected final String TEXT_394 = "(), ";
  protected final String TEXT_395 = "new ";
  protected final String TEXT_396 = "(";
  protected final String TEXT_397 = "new";
  protected final String TEXT_398 = ")";
  protected final String TEXT_399 = ");";
  protected final String TEXT_400 = NL + "\t\t((";
  protected final String TEXT_401 = ".Internal)get";
  protected final String TEXT_402 = "()).set(";
  protected final String TEXT_403 = "(), ";
  protected final String TEXT_404 = "new ";
  protected final String TEXT_405 = "(";
  protected final String TEXT_406 = "new";
  protected final String TEXT_407 = ")";
  protected final String TEXT_408 = ");";
  protected final String TEXT_409 = NL + "\t\t// TODO: implement this method to set the '";
  protected final String TEXT_410 = "' ";
  protected final String TEXT_411 = NL + "\t\t// Ensure that you remove @generated or mark it @generated NOT" + NL + "\t\tthrow new UnsupportedOperationException();";
  protected final String TEXT_412 = NL + "\t}" + NL;
  protected final String TEXT_413 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */" + NL + "\tpublic ";
  protected final String TEXT_414 = " basicUnset";
  protected final String TEXT_415 = "(";
  protected final String TEXT_416 = " msgs)" + NL + "\t{";
  protected final String TEXT_417 = NL + "\t\t";
  protected final String TEXT_418 = " old";
  protected final String TEXT_419 = " = ";
  protected final String TEXT_420 = ";" + NL + "\t\t";
  protected final String TEXT_421 = " = null;" + NL + "\t\tboolean old";
  protected final String TEXT_422 = "ESet = ";
  protected final String TEXT_423 = "ESet;" + NL + "\t\t";
  protected final String TEXT_424 = "ESet = false;" + NL + "\t\tif (eNotificationRequired())" + NL + "\t\t{" + NL + "\t\t\t";
  protected final String TEXT_425 = " notification = new ";
  protected final String TEXT_426 = "(this, ";
  protected final String TEXT_427 = ".UNSET, ";
  protected final String TEXT_428 = ", old";
  protected final String TEXT_429 = ", null, old";
  protected final String TEXT_430 = "ESet);" + NL + "\t\t\tif (msgs == null) msgs = notification; else msgs.add(notification);" + NL + "\t\t}" + NL + "\t\treturn msgs;";
  protected final String TEXT_431 = NL + "\t\t// TODO: implement this method to unset the contained '";
  protected final String TEXT_432 = "' ";
  protected final String TEXT_433 = NL + "\t\t// -> this method is automatically invoked to keep the containment relationship in synch" + NL + "\t\t// -> do not modify other features" + NL + "\t\t// -> return msgs, after adding any generated Notification to it (if it is null, a NotificationChain object must be created first)" + NL + "\t\t// Ensure that you remove @generated or mark it @generated NOT" + NL + "\t\tthrow new UnsupportedOperationException();";
  protected final String TEXT_434 = NL + "\t}" + NL;
  protected final String TEXT_435 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */" + NL + "\tpublic void unset";
  protected final String TEXT_436 = "()" + NL + "\t{";
  protected final String TEXT_437 = NL + "\t\t((";
  protected final String TEXT_438 = ".Unsettable)get";
  protected final String TEXT_439 = "()).unset();";
  protected final String TEXT_440 = NL + "\t\tif (";
  protected final String TEXT_441 = " != null)" + NL + "\t\t{" + NL + "\t\t\t";
  protected final String TEXT_442 = " msgs = null;";
  protected final String TEXT_443 = NL + "\t\t\tmsgs = ((";
  protected final String TEXT_444 = ")";
  protected final String TEXT_445 = ").eInverseRemove(this, EOPPOSITE_FEATURE_BASE - ";
  protected final String TEXT_446 = ", null, msgs);";
  protected final String TEXT_447 = NL + "\t\t\tmsgs = ((";
  protected final String TEXT_448 = ")";
  protected final String TEXT_449 = ").eInverseRemove(this, ";
  protected final String TEXT_450 = ", ";
  protected final String TEXT_451 = ".class, msgs);";
  protected final String TEXT_452 = NL + "\t\t\tmsgs = basicUnset";
  protected final String TEXT_453 = "(msgs);" + NL + "\t\t\tif (msgs != null) msgs.dispatch();" + NL + "\t\t}" + NL + "\t\telse" + NL + "    \t{" + NL + "\t\t\tboolean old";
  protected final String TEXT_454 = "ESet = ";
  protected final String TEXT_455 = "ESet;" + NL + "\t\t\t";
  protected final String TEXT_456 = "ESet = false;" + NL + "\t\t\tif (eNotificationRequired())" + NL + "\t\t\t\teNotify(new ";
  protected final String TEXT_457 = "(this, ";
  protected final String TEXT_458 = ".UNSET, ";
  protected final String TEXT_459 = ", null, null, old";
  protected final String TEXT_460 = "ESet));" + NL + "    \t}";
  protected final String TEXT_461 = NL + "\t\t";
  protected final String TEXT_462 = " old";
  protected final String TEXT_463 = " = ";
  protected final String TEXT_464 = ";" + NL + "\t\tboolean old";
  protected final String TEXT_465 = "ESet = ";
  protected final String TEXT_466 = "ESet;";
  protected final String TEXT_467 = NL + "\t\t";
  protected final String TEXT_468 = " = null;" + NL + "\t\t";
  protected final String TEXT_469 = "ESet = false;" + NL + "\t\tif (eNotificationRequired())" + NL + "\t\t\teNotify(new ";
  protected final String TEXT_470 = "(this, ";
  protected final String TEXT_471 = ".UNSET, ";
  protected final String TEXT_472 = ", old";
  protected final String TEXT_473 = ", null, old";
  protected final String TEXT_474 = "ESet));";
  protected final String TEXT_475 = NL + "\t\t";
  protected final String TEXT_476 = " = ";
  protected final String TEXT_477 = "_EDEFAULT;" + NL + "\t\t";
  protected final String TEXT_478 = "ESet = false;" + NL + "\t\tif (eNotificationRequired())" + NL + "\t\t\teNotify(new ";
  protected final String TEXT_479 = "(this, ";
  protected final String TEXT_480 = ".UNSET, ";
  protected final String TEXT_481 = ", old";
  protected final String TEXT_482 = ", ";
  protected final String TEXT_483 = "_EDEFAULT, old";
  protected final String TEXT_484 = "ESet));";
  protected final String TEXT_485 = NL + "\t\t((";
  protected final String TEXT_486 = ".Internal)((";
  protected final String TEXT_487 = ")get";
  protected final String TEXT_488 = "()).featureMap()).clear(";
  protected final String TEXT_489 = "());";
  protected final String TEXT_490 = NL + "\t\t((";
  protected final String TEXT_491 = ".Internal)get";
  protected final String TEXT_492 = "()).clear(";
  protected final String TEXT_493 = "());";
  protected final String TEXT_494 = NL + "\t\t// TODO: implement this method to unset the '";
  protected final String TEXT_495 = "' ";
  protected final String TEXT_496 = NL + "\t\t// Ensure that you remove @generated or mark it @generated NOT" + NL + "\t\tthrow new UnsupportedOperationException();";
  protected final String TEXT_497 = NL + "\t}" + NL;
  protected final String TEXT_498 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */" + NL + "\tpublic boolean isSet";
  protected final String TEXT_499 = "()" + NL + "\t{";
  protected final String TEXT_500 = NL + "\t\treturn ";
  protected final String TEXT_501 = " != null && ((";
  protected final String TEXT_502 = ".Unsettable)";
  protected final String TEXT_503 = ").isSet();";
  protected final String TEXT_504 = NL + "\t\treturn ";
  protected final String TEXT_505 = "ESet;";
  protected final String TEXT_506 = NL + "\t\treturn !((";
  protected final String TEXT_507 = ".Internal)((";
  protected final String TEXT_508 = ")get";
  protected final String TEXT_509 = "()).featureMap()).isEmpty(";
  protected final String TEXT_510 = "());";
  protected final String TEXT_511 = NL + "\t\treturn !((";
  protected final String TEXT_512 = ".Internal)get";
  protected final String TEXT_513 = "()).isEmpty(";
  protected final String TEXT_514 = "());";
  protected final String TEXT_515 = NL + "\t\t// TODO: implement this method to return whether the '";
  protected final String TEXT_516 = "' ";
  protected final String TEXT_517 = " is set" + NL + "\t\t// Ensure that you remove @generated or mark it @generated NOT" + NL + "\t\tthrow new UnsupportedOperationException();";
  protected final String TEXT_518 = NL + "\t}" + NL;
  protected final String TEXT_519 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */" + NL + "\tpublic ";
  protected final String TEXT_520 = " ";
  protected final String TEXT_521 = "(";
  protected final String TEXT_522 = ")";
  protected final String TEXT_523 = NL + "\t{";
  protected final String TEXT_524 = NL + "\t\t";
  protected final String TEXT_525 = NL + "\t\t// TODO: implement this method" + NL + "\t\t// Ensure that you remove @generated or mark it @generated NOT" + NL + "\t\treturn true;";
  protected final String TEXT_526 = NL + "\t\t// TODO: implement this method" + NL + "\t\t// Ensure that you remove @generated or mark it @generated NOT" + NL + "\t\tthrow new UnsupportedOperationException();";
  protected final String TEXT_527 = NL + "\t}" + NL;
  protected final String TEXT_528 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */" + NL + "\tpublic ";
  protected final String TEXT_529 = " eInverseAdd(";
  protected final String TEXT_530 = " otherEnd, int featureID, Class baseClass, ";
  protected final String TEXT_531 = " msgs)" + NL + "\t{" + NL + "\t\tif (featureID >= 0)" + NL + "\t\t{" + NL + "\t\t\tswitch (eDerivedStructuralFeatureID(featureID, baseClass))" + NL + "\t\t\t{";
  protected final String TEXT_532 = NL + "\t\t\t\tcase ";
  protected final String TEXT_533 = ":";
  protected final String TEXT_534 = NL + "\t\t\t\t\treturn ((";
  protected final String TEXT_535 = ")((";
  protected final String TEXT_536 = ")";
  protected final String TEXT_537 = "()).eMap()).basicAdd(otherEnd, msgs);";
  protected final String TEXT_538 = NL + "\t\t\t\t\treturn ((";
  protected final String TEXT_539 = ")";
  protected final String TEXT_540 = "()).basicAdd(otherEnd, msgs);";
  protected final String TEXT_541 = NL + "\t\t\t\t\tif (eContainer != null)" + NL + "\t\t\t\t\t\tmsgs = eBasicRemoveFromContainer(msgs);" + NL + "\t\t\t\t\treturn eBasicSetContainer(otherEnd, ";
  protected final String TEXT_542 = ", msgs);";
  protected final String TEXT_543 = NL + "\t\t\t\t\tif (";
  protected final String TEXT_544 = " != null)";
  protected final String TEXT_545 = NL + "\t\t\t\t\t\tmsgs = ((";
  protected final String TEXT_546 = ")";
  protected final String TEXT_547 = ").eInverseRemove(this, EOPPOSITE_FEATURE_BASE - ";
  protected final String TEXT_548 = ", null, msgs);";
  protected final String TEXT_549 = NL + "\t\t\t\t\t\tmsgs = ((";
  protected final String TEXT_550 = ")";
  protected final String TEXT_551 = ").eInverseRemove(this, ";
  protected final String TEXT_552 = ", ";
  protected final String TEXT_553 = ".class, msgs);";
  protected final String TEXT_554 = NL + "\t\t\t\t\treturn basicSet";
  protected final String TEXT_555 = "((";
  protected final String TEXT_556 = ")otherEnd, msgs);";
  protected final String TEXT_557 = NL + "\t\t\t\tdefault:" + NL + "\t\t\t\t\treturn eDynamicInverseAdd(otherEnd, featureID, baseClass, msgs);" + NL + "\t\t\t}" + NL + "\t\t}" + NL + "\t\tif (eContainer != null)" + NL + "\t\t\tmsgs = eBasicRemoveFromContainer(msgs);" + NL + "\t\treturn eBasicSetContainer(otherEnd, featureID, msgs);" + NL + "\t}" + NL;
  protected final String TEXT_558 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */" + NL + "\tpublic ";
  protected final String TEXT_559 = " eInverseRemove(";
  protected final String TEXT_560 = " otherEnd, int featureID, Class baseClass, ";
  protected final String TEXT_561 = " msgs)" + NL + "\t{" + NL + "\t\tif (featureID >= 0)" + NL + "\t\t{" + NL + "\t\t\tswitch (eDerivedStructuralFeatureID(featureID, baseClass))" + NL + "\t\t\t{";
  protected final String TEXT_562 = NL + "\t\t\t\tcase ";
  protected final String TEXT_563 = ":";
  protected final String TEXT_564 = NL + "\t\t\t\t\treturn ((";
  protected final String TEXT_565 = ")((";
  protected final String TEXT_566 = ")";
  protected final String TEXT_567 = "()).eMap()).basicRemove(otherEnd, msgs);";
  protected final String TEXT_568 = NL + "\t\t\t\treturn ((";
  protected final String TEXT_569 = ")((";
  protected final String TEXT_570 = ")";
  protected final String TEXT_571 = "()).featureMap()).basicRemove(otherEnd, msgs);";
  protected final String TEXT_572 = NL + "\t\t\t\t\treturn ((";
  protected final String TEXT_573 = ")";
  protected final String TEXT_574 = "()).basicRemove(otherEnd, msgs);";
  protected final String TEXT_575 = NL + "\t\t\t\t\treturn eBasicSetContainer(null, ";
  protected final String TEXT_576 = ", msgs);";
  protected final String TEXT_577 = NL + "\t\t\t\t\treturn basicUnset";
  protected final String TEXT_578 = "(msgs);";
  protected final String TEXT_579 = NL + "\t\t\t\t\treturn basicSet";
  protected final String TEXT_580 = "(null, msgs);";
  protected final String TEXT_581 = NL + "\t\t\t\tdefault:" + NL + "\t\t\t\t\treturn eDynamicInverseRemove(otherEnd, featureID, baseClass, msgs);" + NL + "\t\t\t}" + NL + "\t\t}" + NL + "\t\treturn eBasicSetContainer(null, featureID, msgs);" + NL + "\t}" + NL;
  protected final String TEXT_582 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */" + NL + "\tpublic ";
  protected final String TEXT_583 = " eBasicRemoveFromContainer(";
  protected final String TEXT_584 = " msgs)" + NL + "\t{" + NL + "\t\tif (eContainerFeatureID >= 0)" + NL + "\t\t{" + NL + "\t\t\tswitch (eContainerFeatureID)" + NL + "\t\t\t{";
  protected final String TEXT_585 = NL + "\t\t\t\tcase ";
  protected final String TEXT_586 = ":" + NL + "\t\t\t\t\treturn ((";
  protected final String TEXT_587 = ")eContainer).eInverseRemove(this, ";
  protected final String TEXT_588 = ", ";
  protected final String TEXT_589 = ".class, msgs);";
  protected final String TEXT_590 = NL + "\t\t\t\tdefault:" + NL + "\t\t\t\t\treturn eDynamicBasicRemoveFromContainer(msgs);" + NL + "\t\t\t}" + NL + "\t\t}" + NL + "\t\treturn ((";
  protected final String TEXT_591 = ")eContainer).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - eContainerFeatureID, null, msgs);" + NL + "\t}" + NL;
  protected final String TEXT_592 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */" + NL + "\tpublic Object eGet(";
  protected final String TEXT_593 = " eFeature, boolean resolve)" + NL + "\t{" + NL + "\t\tswitch (eDerivedStructuralFeatureID(eFeature))" + NL + "\t\t{";
  protected final String TEXT_594 = NL + "\t\t\tcase ";
  protected final String TEXT_595 = ":";
  protected final String TEXT_596 = NL + "\t\t\t\treturn ";
  protected final String TEXT_597 = "() ? Boolean.TRUE : Boolean.FALSE;";
  protected final String TEXT_598 = NL + "\t\t\t\treturn new ";
  protected final String TEXT_599 = "(";
  protected final String TEXT_600 = "());";
  protected final String TEXT_601 = NL + "\t\t\t\tif (resolve) return ";
  protected final String TEXT_602 = "();" + NL + "\t\t\t\treturn basicGet";
  protected final String TEXT_603 = "();";
  protected final String TEXT_604 = NL + "\t\t\t\treturn ((";
  protected final String TEXT_605 = ")";
  protected final String TEXT_606 = "()).eMap();";
  protected final String TEXT_607 = NL + "\t\t\t\treturn ((";
  protected final String TEXT_608 = ")";
  protected final String TEXT_609 = "()).featureMap();";
  protected final String TEXT_610 = NL + "\t\t\t\treturn ";
  protected final String TEXT_611 = "();";
  protected final String TEXT_612 = NL + "\t\t}" + NL + "\t\treturn eDynamicGet(eFeature, resolve);" + NL + "\t}" + NL;
  protected final String TEXT_613 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */" + NL + "\tpublic void eSet(";
  protected final String TEXT_614 = " eFeature, Object newValue)" + NL + "\t{" + NL + "\t\tswitch (eDerivedStructuralFeatureID(eFeature))" + NL + "\t\t{";
  protected final String TEXT_615 = NL + "\t\t\tcase ";
  protected final String TEXT_616 = ":";
  protected final String TEXT_617 = NL + "\t\t\t\t((";
  protected final String TEXT_618 = ")";
  protected final String TEXT_619 = "()).featureMap().clear();";
  protected final String TEXT_620 = NL + "\t\t\t\t";
  protected final String TEXT_621 = "().clear();";
  protected final String TEXT_622 = NL + "\t\t\t\t((";
  protected final String TEXT_623 = ")";
  protected final String TEXT_624 = "()).featureMap().addAll((";
  protected final String TEXT_625 = ")newValue);";
  protected final String TEXT_626 = NL + "\t\t\t\t((";
  protected final String TEXT_627 = ")";
  protected final String TEXT_628 = "()).eMap().addAll((";
  protected final String TEXT_629 = ")newValue);";
  protected final String TEXT_630 = NL + "\t\t\t\t";
  protected final String TEXT_631 = "().addAll((";
  protected final String TEXT_632 = ")newValue);";
  protected final String TEXT_633 = NL + "\t\t\t\tset";
  protected final String TEXT_634 = "(((";
  protected final String TEXT_635 = ")newValue).";
  protected final String TEXT_636 = "());";
  protected final String TEXT_637 = NL + "\t\t\t\tset";
  protected final String TEXT_638 = "((";
  protected final String TEXT_639 = ")newValue);";
  protected final String TEXT_640 = NL + "\t\t\t\treturn;";
  protected final String TEXT_641 = NL + "\t\t}" + NL + "\t\teDynamicSet(eFeature, newValue);" + NL + "\t}" + NL + "" + NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */" + NL + "\tpublic void eUnset(";
  protected final String TEXT_642 = " eFeature)" + NL + "\t{" + NL + "\t\tswitch (eDerivedStructuralFeatureID(eFeature))" + NL + "\t\t{";
  protected final String TEXT_643 = NL + "\t\t\tcase ";
  protected final String TEXT_644 = ":";
  protected final String TEXT_645 = NL + "\t\t\t\t((";
  protected final String TEXT_646 = ")";
  protected final String TEXT_647 = "()).featureMap().clear();";
  protected final String TEXT_648 = NL + "\t\t\t\t";
  protected final String TEXT_649 = "().clear();";
  protected final String TEXT_650 = NL + "\t\t\t\tunset";
  protected final String TEXT_651 = "();";
  protected final String TEXT_652 = NL + "\t\t\t\tset";
  protected final String TEXT_653 = "((";
  protected final String TEXT_654 = ")";
  protected final String TEXT_655 = ");";
  protected final String TEXT_656 = NL + "\t\t\t\tset";
  protected final String TEXT_657 = "((";
  protected final String TEXT_658 = ")null);";
  protected final String TEXT_659 = NL + "\t\t\t\tset";
  protected final String TEXT_660 = "(";
  protected final String TEXT_661 = "_EDEFAULT);";
  protected final String TEXT_662 = NL + "\t\t\t\treturn;";
  protected final String TEXT_663 = NL + "\t\t}" + NL + "\t\teDynamicUnset(eFeature);" + NL + "\t}" + NL;
  protected final String TEXT_664 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */" + NL + "\tpublic boolean eIsSet(";
  protected final String TEXT_665 = " eFeature)" + NL + "\t{" + NL + "\t\tswitch (eDerivedStructuralFeatureID(eFeature))" + NL + "\t\t{";
  protected final String TEXT_666 = NL + "\t\t\tcase ";
  protected final String TEXT_667 = ":";
  protected final String TEXT_668 = NL + "\t\t\t\treturn !((";
  protected final String TEXT_669 = ")";
  protected final String TEXT_670 = "()).featureMap().isEmpty();";
  protected final String TEXT_671 = NL + "\t\t\t\treturn ";
  protected final String TEXT_672 = " != null && !";
  protected final String TEXT_673 = ".featureMap().isEmpty();";
  protected final String TEXT_674 = NL + "\t\t\t\treturn !";
  protected final String TEXT_675 = "().isEmpty();";
  protected final String TEXT_676 = NL + "\t\t\t\treturn ";
  protected final String TEXT_677 = " != null && !";
  protected final String TEXT_678 = ".isEmpty();";
  protected final String TEXT_679 = NL + "\t\t\t\treturn isSet";
  protected final String TEXT_680 = "();";
  protected final String TEXT_681 = NL + "\t\t\t\treturn basicGet";
  protected final String TEXT_682 = "() != null;";
  protected final String TEXT_683 = NL + "\t\t\t\treturn ";
  protected final String TEXT_684 = " != null;";
  protected final String TEXT_685 = NL + "\t\t\t\treturn ";
  protected final String TEXT_686 = "() != null;";
  protected final String TEXT_687 = NL + "\t\t\t\treturn ";
  protected final String TEXT_688 = " != null;";
  protected final String TEXT_689 = NL + "\t\t\t\treturn ";
  protected final String TEXT_690 = "() != ";
  protected final String TEXT_691 = ";";
  protected final String TEXT_692 = NL + "\t\t\t\treturn ";
  protected final String TEXT_693 = " != ";
  protected final String TEXT_694 = "_EDEFAULT;";
  protected final String TEXT_695 = NL + "\t\t\t\treturn !";
  protected final String TEXT_696 = ".equals(";
  protected final String TEXT_697 = "());";
  protected final String TEXT_698 = NL + "\t\t\t\treturn ";
  protected final String TEXT_699 = "() != null;";
  protected final String TEXT_700 = NL + "\t\t\t\treturn ";
  protected final String TEXT_701 = "_EDEFAULT == null ? ";
  protected final String TEXT_702 = " != null : !";
  protected final String TEXT_703 = "_EDEFAULT.equals(";
  protected final String TEXT_704 = ");";
  protected final String TEXT_705 = NL + "\t\t}" + NL + "\t\treturn eDynamicIsSet(eFeature);" + NL + "\t}" + NL;
  protected final String TEXT_706 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */" + NL + "\tpublic int eBaseStructuralFeatureID(int derivedFeatureID, Class baseClass)" + NL + "\t{";
  protected final String TEXT_707 = NL + "\t\tif (baseClass == ";
  protected final String TEXT_708 = ".class)" + NL + "\t\t{" + NL + "\t\t\tswitch (derivedFeatureID)" + NL + "\t\t\t{";
  protected final String TEXT_709 = NL + "\t\t\t\tcase ";
  protected final String TEXT_710 = ": return ";
  protected final String TEXT_711 = ";";
  protected final String TEXT_712 = NL + "\t\t\t\tdefault: return -1;" + NL + "\t\t\t}" + NL + "\t\t}";
  protected final String TEXT_713 = NL + "\t\treturn super.eBaseStructuralFeatureID(derivedFeatureID, baseClass);" + NL + "\t}" + NL + "" + NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */" + NL + "\tpublic int eDerivedStructuralFeatureID(int baseFeatureID, Class baseClass)" + NL + "\t{";
  protected final String TEXT_714 = NL + "\t\tif (baseClass == ";
  protected final String TEXT_715 = ".class)" + NL + "\t\t{" + NL + "\t\t\tswitch (baseFeatureID)" + NL + "\t\t\t{";
  protected final String TEXT_716 = NL + "\t\t\t\tcase ";
  protected final String TEXT_717 = ": return ";
  protected final String TEXT_718 = ";";
  protected final String TEXT_719 = NL + "\t\t\t\tdefault: return -1;" + NL + "\t\t\t}" + NL + "\t\t}";
  protected final String TEXT_720 = NL + "\t\treturn super.eDerivedStructuralFeatureID(baseFeatureID, baseClass);" + NL + "\t}" + NL;
  protected final String TEXT_721 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */" + NL + "\tpublic String toString()" + NL + "\t{" + NL + "\t\tif (eIsProxy()) return super.toString();" + NL + "" + NL + "\t\tStringBuffer result = new StringBuffer(super.toString());";
  protected final String TEXT_722 = NL + "\t\tresult.append(\" (";
  protected final String TEXT_723 = ": \");";
  protected final String TEXT_724 = NL + "\t\tresult.append(\", ";
  protected final String TEXT_725 = ": \");";
  protected final String TEXT_726 = NL + "\t\tif (";
  protected final String TEXT_727 = "ESet) result.append(";
  protected final String TEXT_728 = "); else result.append(\"<unset>\");";
  protected final String TEXT_729 = NL + "\t\tresult.append(";
  protected final String TEXT_730 = ");";
  protected final String TEXT_731 = NL + "\t\tresult.append(')');" + NL + "\t\treturn result.toString();" + NL + "\t}" + NL;
  protected final String TEXT_732 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */" + NL + "\tprotected int hash = -1;" + NL + "" + NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */" + NL + "\tpublic int getHash()" + NL + "\t{" + NL + "\t\tif (hash == -1)" + NL + "\t\t{" + NL + "\t\t\tObject theKey = getKey();" + NL + "\t\t\thash = (theKey == null ? 0 : theKey.hashCode());" + NL + "\t\t}" + NL + "\t\treturn hash;" + NL + "\t}" + NL + "" + NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */" + NL + "\tpublic void setHash(int hash)" + NL + "\t{" + NL + "\t\tthis.hash = hash;" + NL + "\t}" + NL + "" + NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */" + NL + "\tpublic Object getKey()" + NL + "\t{";
  protected final String TEXT_733 = NL + "\t\treturn new ";
  protected final String TEXT_734 = "(getTypedKey());";
  protected final String TEXT_735 = NL + "\t\treturn getTypedKey();";
  protected final String TEXT_736 = NL + "\t}" + NL + "" + NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */" + NL + "\tpublic void setKey(Object key)" + NL + "\t{";
  protected final String TEXT_737 = NL + "\t\tgetTypedKey().addAll((";
  protected final String TEXT_738 = ")key);";
  protected final String TEXT_739 = NL + "\t\tsetTypedKey(((";
  protected final String TEXT_740 = ")key).";
  protected final String TEXT_741 = "());";
  protected final String TEXT_742 = NL + "\t\tsetTypedKey((";
  protected final String TEXT_743 = ")key);";
  protected final String TEXT_744 = NL + "\t}" + NL + "" + NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */" + NL + "\tpublic Object getValue()" + NL + "\t{";
  protected final String TEXT_745 = NL + "\t\treturn new ";
  protected final String TEXT_746 = "(getTypedValue());";
  protected final String TEXT_747 = NL + "\t\treturn getTypedValue();";
  protected final String TEXT_748 = NL + "\t}" + NL + "" + NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */" + NL + "\tpublic Object setValue(Object value)" + NL + "\t{" + NL + "\t\tObject oldValue = getValue();";
  protected final String TEXT_749 = NL + "\t\tgetTypedValue().clear();" + NL + "\t\tgetTypedValue().addAll((";
  protected final String TEXT_750 = ")value);";
  protected final String TEXT_751 = NL + "\t\tsetTypedValue(((";
  protected final String TEXT_752 = ")value).";
  protected final String TEXT_753 = "());";
  protected final String TEXT_754 = NL + "\t\tsetTypedValue((";
  protected final String TEXT_755 = ")value);";
  protected final String TEXT_756 = NL + "\t\treturn oldValue;" + NL + "\t}" + NL + "" + NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */" + NL + "\tpublic ";
  protected final String TEXT_757 = " getEMap()" + NL + "\t{" + NL + "\t\t";
  protected final String TEXT_758 = " container = eContainer();" + NL + "\t\treturn container == null ? null : (";
  protected final String TEXT_759 = ")container.eGet(eContainmentFeature());" + NL + "\t}" + NL;
  protected final String TEXT_760 = NL + "} //";
  protected final String TEXT_761 = NL;

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
    stringBuffer.append(genPackage.getClassPackageName());
    stringBuffer.append(TEXT_5);
    genModel.markImportLocation(stringBuffer);
    stringBuffer.append(TEXT_6);
    stringBuffer.append(genClass.getFormattedName());
    stringBuffer.append(TEXT_7);
    if (!genClass.getImplementedGenFeatures().isEmpty()) {
    stringBuffer.append(TEXT_8);
    for (Iterator i=genClass.getImplementedGenFeatures().iterator(); i.hasNext();) { GenFeature genFeature = (GenFeature)i.next();
    stringBuffer.append(TEXT_9);
    stringBuffer.append(genClass.getQualifiedClassName());
    stringBuffer.append(TEXT_10);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_11);
    stringBuffer.append(genFeature.getFormattedName());
    stringBuffer.append(TEXT_12);
    }
    stringBuffer.append(TEXT_13);
    }
    stringBuffer.append(TEXT_14);
    if (genClass.isAbstract()) {
    stringBuffer.append(TEXT_15);
    }
    stringBuffer.append(TEXT_16);
    stringBuffer.append(genClass.getClassName());
    stringBuffer.append(genClass.getClassExtends());
    stringBuffer.append(genClass.getClassImplements());
    stringBuffer.append(TEXT_17);
    if (genModel.getCopyrightText() != null) {
    stringBuffer.append(TEXT_18);
    stringBuffer.append(genModel.getImportedName("java.lang.String"));
    stringBuffer.append(TEXT_19);
    stringBuffer.append(genModel.getCopyrightText());
    stringBuffer.append(TEXT_20);
    stringBuffer.append(genModel.getNonNLS());
    stringBuffer.append(TEXT_21);
    }
    if (genModel.getDriverNumber() != null) {
    stringBuffer.append(TEXT_22);
    stringBuffer.append(genModel.getImportedName("java.lang.String"));
    stringBuffer.append(TEXT_23);
    stringBuffer.append(genModel.getDriverNumber());
    stringBuffer.append(TEXT_24);
    stringBuffer.append(genModel.getNonNLS());
    stringBuffer.append(TEXT_25);
    }
    for (Iterator i=genClass.getImplementedGenFeatures().iterator(); i.hasNext();) { GenFeature genFeature = (GenFeature)i.next();
    if (!genFeature.isVolatile() && !genFeature.isContainer()) {
    if (genFeature.isListType() || genFeature.isReferenceType()) {
    stringBuffer.append(TEXT_26);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_27);
    stringBuffer.append(genFeature.getFormattedName());
    stringBuffer.append(TEXT_28);
    stringBuffer.append(genFeature.getFeatureKind());
    stringBuffer.append(TEXT_29);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_30);
    stringBuffer.append(genFeature.getImportedInternalType());
    stringBuffer.append(TEXT_31);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_32);
    } else {
    stringBuffer.append(TEXT_33);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_34);
    stringBuffer.append(genFeature.getFormattedName());
    stringBuffer.append(TEXT_35);
    stringBuffer.append(genFeature.getFeatureKind());
    stringBuffer.append(TEXT_36);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_37);
    if (genFeature.getStaticDefaultValue() != null) {
    stringBuffer.append(TEXT_38);
    stringBuffer.append(genFeature.getImportedType());
    stringBuffer.append(TEXT_39);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_40);
    stringBuffer.append(genFeature.getStaticDefaultValue());
    stringBuffer.append(TEXT_41);
    stringBuffer.append(genModel.getNonNLS(genFeature.getStaticDefaultValue()));
    } else { GenDataType genDataType = genFeature.getGenDataTypeType(); GenPackage typeGenPackage = genFeature.getTypeGenPackage();
    if (genDataType != null && typeGenPackage != null) {
    stringBuffer.append(TEXT_42);
    stringBuffer.append(genFeature.getImportedType());
    stringBuffer.append(TEXT_43);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_44);
    if (genFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_45);
    }
    stringBuffer.append(TEXT_46);
    stringBuffer.append(genFeature.getObjectType());
    stringBuffer.append(TEXT_47);
    stringBuffer.append(typeGenPackage.getImportedFactoryInterfaceName());
    stringBuffer.append(TEXT_48);
    stringBuffer.append(typeGenPackage.getImportedPackageInterfaceName());
    stringBuffer.append(TEXT_49);
    stringBuffer.append(genDataType.getName());
    stringBuffer.append(TEXT_50);
    stringBuffer.append(genFeature.getDefaultValue());
    stringBuffer.append(TEXT_51);
    if (genFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_52);
    stringBuffer.append(genFeature.getType());
    stringBuffer.append(TEXT_53);
    }
    stringBuffer.append(TEXT_54);
    stringBuffer.append(genModel.getNonNLS(genFeature.getDefaultValue()));
    } else {
    stringBuffer.append(TEXT_55);
    stringBuffer.append(genFeature.getImportedType());
    stringBuffer.append(TEXT_56);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_57);
    }
    }
    stringBuffer.append(TEXT_58);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_59);
    stringBuffer.append(genFeature.getFormattedName());
    stringBuffer.append(TEXT_60);
    stringBuffer.append(genFeature.getFeatureKind());
    stringBuffer.append(TEXT_61);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_62);
    stringBuffer.append(genFeature.getImportedType());
    stringBuffer.append(TEXT_63);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_64);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_65);
    }
    if (!genFeature.isListType() && genFeature.isUnsettable() && genFeature.isChangeable()) {
    stringBuffer.append(TEXT_66);
    stringBuffer.append(genFeature.getFormattedName());
    stringBuffer.append(TEXT_67);
    stringBuffer.append(genFeature.getFeatureKind());
    stringBuffer.append(TEXT_68);
    stringBuffer.append(genFeature.getUncapName());
    stringBuffer.append(TEXT_69);
    }
    }
    }
    stringBuffer.append(TEXT_70);
    stringBuffer.append(genClass.getClassName());
    stringBuffer.append(TEXT_71);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.EClass"));
    stringBuffer.append(TEXT_72);
    stringBuffer.append(genPackage.getImportedPackageInterfaceName());
    stringBuffer.append(TEXT_73);
    stringBuffer.append(genClass.getClassifierAccessorName());
    stringBuffer.append(TEXT_74);
    for (Iterator i=genClass.getImplementedGenFeatures().iterator(); i.hasNext();) { GenFeature genFeature = (GenFeature)i.next();
    stringBuffer.append(TEXT_75);
    stringBuffer.append(genFeature.getImportedType());
    stringBuffer.append(TEXT_76);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_77);
    if (!genFeature.isVolatile()) {
    if (genFeature.isListType()) { String unsettable = genFeature.isUnsettable() ? ".Unsettable" : ""; 
    stringBuffer.append(TEXT_78);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_79);
    if (genFeature.isMapType()) { GenClass mapGenClass = genFeature.getMapGenClass(); GenPackage mapGenPackage = mapGenClass.getGenPackage(); 
    stringBuffer.append(TEXT_80);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_81);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.EcoreEMap"));
    stringBuffer.append(TEXT_82);
    stringBuffer.append(mapGenPackage.getImportedPackageInterfaceName());
    stringBuffer.append(TEXT_83);
    stringBuffer.append(mapGenClass.getName());
    stringBuffer.append(TEXT_84);
    stringBuffer.append(genFeature.getMapItemType());
    stringBuffer.append(TEXT_85);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(TEXT_86);
    } else if (genFeature.isContains()) {
    if (genFeature.isBidirectional()) { GenFeature reverseFeature = genFeature.getReverse();
    stringBuffer.append(TEXT_87);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_88);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.EObjectContainmentWithInverseEList"));
    stringBuffer.append(unsettable);
    stringBuffer.append(TEXT_89);
    stringBuffer.append(genFeature.getListItemType());
    stringBuffer.append(TEXT_90);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(TEXT_91);
    stringBuffer.append(reverseFeature.getGenClass().getQualifiedFeatureID(reverseFeature));
    stringBuffer.append(TEXT_92);
    } else {
    stringBuffer.append(TEXT_93);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_94);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.EObjectContainmentEList"));
    stringBuffer.append(unsettable);
    stringBuffer.append(TEXT_95);
    stringBuffer.append(genFeature.getListItemType());
    stringBuffer.append(TEXT_96);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(TEXT_97);
    }
    } else if (genFeature.isReferenceType()) {
    if (genFeature.isBidirectional()) { GenFeature reverseFeature = genFeature.getReverse();
    if (reverseFeature.isListType()) {
    if (genFeature.isResolveProxies()) {
    stringBuffer.append(TEXT_98);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_99);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.EObjectWithInverseResolvingEList"));
    stringBuffer.append(unsettable);
    stringBuffer.append(TEXT_100);
    stringBuffer.append(genFeature.getListItemType());
    stringBuffer.append(TEXT_101);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(TEXT_102);
    stringBuffer.append(reverseFeature.getGenClass().getQualifiedFeatureID(reverseFeature));
    stringBuffer.append(TEXT_103);
    } else {
    stringBuffer.append(TEXT_104);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_105);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.EObjectWithInverseEList"));
    stringBuffer.append(unsettable);
    stringBuffer.append(TEXT_106);
    stringBuffer.append(genFeature.getListItemType());
    stringBuffer.append(TEXT_107);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(TEXT_108);
    stringBuffer.append(reverseFeature.getGenClass().getQualifiedFeatureID(reverseFeature));
    stringBuffer.append(TEXT_109);
    }
    } else {
    if (genFeature.isResolveProxies()) {
    stringBuffer.append(TEXT_110);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_111);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.EObjectWithInverseResolvingEList"));
    stringBuffer.append(unsettable);
    stringBuffer.append(TEXT_112);
    stringBuffer.append(genFeature.getListItemType());
    stringBuffer.append(TEXT_113);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(TEXT_114);
    stringBuffer.append(reverseFeature.getGenClass().getQualifiedFeatureID(reverseFeature));
    stringBuffer.append(TEXT_115);
    } else {
    stringBuffer.append(TEXT_116);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_117);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.EObjectWithInverseEList"));
    stringBuffer.append(unsettable);
    stringBuffer.append(TEXT_118);
    stringBuffer.append(genFeature.getListItemType());
    stringBuffer.append(TEXT_119);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(TEXT_120);
    stringBuffer.append(reverseFeature.getGenClass().getQualifiedFeatureID(reverseFeature));
    stringBuffer.append(TEXT_121);
    }
    }
    } else {
    if (genFeature.isResolveProxies()) {
    stringBuffer.append(TEXT_122);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_123);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.EObjectResolvingEList"));
    stringBuffer.append(unsettable);
    stringBuffer.append(TEXT_124);
    stringBuffer.append(genFeature.getListItemType());
    stringBuffer.append(TEXT_125);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(TEXT_126);
    } else {
    stringBuffer.append(TEXT_127);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_128);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.EObjectEList"));
    stringBuffer.append(unsettable);
    stringBuffer.append(TEXT_129);
    stringBuffer.append(genFeature.getListItemType());
    stringBuffer.append(TEXT_130);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(TEXT_131);
    }
    }
    } else if (genFeature.isFeatureMapType()) {
    if (genFeature.isFeatureMapWrapped()) {
    stringBuffer.append(TEXT_132);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_133);
    stringBuffer.append(genFeature.getImportedEffectiveFeatureMapWrapperClass());
    stringBuffer.append(TEXT_134);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.BasicFeatureMap"));
    stringBuffer.append(TEXT_135);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(TEXT_136);
    } else {
    stringBuffer.append(TEXT_137);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_138);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.BasicFeatureMap"));
    stringBuffer.append(TEXT_139);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(TEXT_140);
    }
    } else {//datatype
    if (genFeature.isUnique()) {
    stringBuffer.append(TEXT_141);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_142);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.EDataTypeUniqueEList"));
    stringBuffer.append(unsettable);
    stringBuffer.append(TEXT_143);
    stringBuffer.append(genFeature.getListItemType());
    stringBuffer.append(TEXT_144);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(TEXT_145);
    } else {
    stringBuffer.append(TEXT_146);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_147);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.EDataTypeEList"));
    stringBuffer.append(unsettable);
    stringBuffer.append(TEXT_148);
    stringBuffer.append(genFeature.getListItemType());
    stringBuffer.append(TEXT_149);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(TEXT_150);
    }
    }
    stringBuffer.append(TEXT_151);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(genFeature.isMapType() && genFeature.isEffectiveSuppressEMFTypes() ? ".map()" : "");
    stringBuffer.append(TEXT_152);
    } else if (genFeature.isContainer()) {
    stringBuffer.append(TEXT_153);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(TEXT_154);
    stringBuffer.append(genFeature.getImportedType());
    stringBuffer.append(TEXT_155);
    } else {
    if (genFeature.isResolveProxies()) {
    stringBuffer.append(TEXT_156);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_157);
    stringBuffer.append(genFeature.getSafeNameAsEObject());
    stringBuffer.append(TEXT_158);
    stringBuffer.append(genFeature.getImportedType());
    stringBuffer.append(TEXT_159);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_160);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_161);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_162);
    stringBuffer.append(genFeature.getImportedInternalType());
    stringBuffer.append(TEXT_163);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.InternalEObject"));
    stringBuffer.append(TEXT_164);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_165);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_166);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_167);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.impl.ENotificationImpl"));
    stringBuffer.append(TEXT_168);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.notify.Notification"));
    stringBuffer.append(TEXT_169);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(TEXT_170);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_171);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_172);
    }
    stringBuffer.append(TEXT_173);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_174);
    }
    } else {//volatile
    if (genFeature.isResolveProxies() && !genFeature.isListType()) {
    stringBuffer.append(TEXT_175);
    stringBuffer.append(genFeature.getImportedType());
    stringBuffer.append(TEXT_176);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_177);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_178);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_179);
    stringBuffer.append(genFeature.getImportedInternalType());
    stringBuffer.append(TEXT_180);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.InternalEObject"));
    stringBuffer.append(TEXT_181);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_182);
    } else if (genFeature.hasDelegateFeature()) { GenFeature delegateFeature = genFeature.getDelegateFeature();
    if (genFeature.isFeatureMapType()) {
    if (delegateFeature.isFeatureMapWrapped()) {
    stringBuffer.append(TEXT_183);
    stringBuffer.append(genFeature.getImportedEffectiveFeatureMapWrapperClass());
    stringBuffer.append(TEXT_184);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.FeatureMap$Internal"));
    stringBuffer.append(TEXT_185);
    stringBuffer.append(delegateFeature.getImportedEffectiveFeatureMapWrapperInternalInterface());
    stringBuffer.append(TEXT_186);
    stringBuffer.append(delegateFeature.getAccessorName());
    stringBuffer.append(TEXT_187);
    stringBuffer.append(genFeature.getQualifiedFeatureAccessorName());
    stringBuffer.append(TEXT_188);
    } else {
    stringBuffer.append(TEXT_189);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.FeatureMap"));
    stringBuffer.append(TEXT_190);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.FeatureMap"));
    stringBuffer.append(TEXT_191);
    stringBuffer.append(delegateFeature.getAccessorName());
    stringBuffer.append(TEXT_192);
    stringBuffer.append(genFeature.getQualifiedFeatureAccessorName());
    stringBuffer.append(TEXT_193);
    }
    } else if (genFeature.isListType()) {
    if (delegateFeature.isFeatureMapWrapped()) {
    stringBuffer.append(TEXT_194);
    stringBuffer.append(delegateFeature.getImportedEffectiveFeatureMapWrapperInternalInterface());
    stringBuffer.append(TEXT_195);
    stringBuffer.append(delegateFeature.getAccessorName());
    stringBuffer.append(TEXT_196);
    stringBuffer.append(genFeature.getQualifiedFeatureAccessorName());
    stringBuffer.append(TEXT_197);
    } else {
    stringBuffer.append(TEXT_198);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.FeatureMap"));
    stringBuffer.append(TEXT_199);
    stringBuffer.append(delegateFeature.getAccessorName());
    stringBuffer.append(TEXT_200);
    stringBuffer.append(genFeature.getQualifiedFeatureAccessorName());
    stringBuffer.append(TEXT_201);
    }
    } else {
    if (delegateFeature.isFeatureMapWrapped()) {
    stringBuffer.append(TEXT_202);
    if (genFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_203);
    }
    stringBuffer.append(TEXT_204);
    stringBuffer.append(genFeature.getObjectType());
    stringBuffer.append(TEXT_205);
    stringBuffer.append(delegateFeature.getImportedEffectiveFeatureMapWrapperInternalInterface());
    stringBuffer.append(TEXT_206);
    stringBuffer.append(delegateFeature.getAccessorName());
    stringBuffer.append(TEXT_207);
    stringBuffer.append(genFeature.getQualifiedFeatureAccessorName());
    stringBuffer.append(TEXT_208);
    if (genFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_209);
    stringBuffer.append(genFeature.getPrimitiveValueFunction());
    stringBuffer.append(TEXT_210);
    }
    stringBuffer.append(TEXT_211);
    } else {
    stringBuffer.append(TEXT_212);
    if (genFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_213);
    }
    stringBuffer.append(TEXT_214);
    stringBuffer.append(genFeature.getObjectType());
    stringBuffer.append(TEXT_215);
    stringBuffer.append(delegateFeature.getAccessorName());
    stringBuffer.append(TEXT_216);
    stringBuffer.append(genFeature.getQualifiedFeatureAccessorName());
    stringBuffer.append(TEXT_217);
    if (genFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_218);
    stringBuffer.append(genFeature.getPrimitiveValueFunction());
    stringBuffer.append(TEXT_219);
    }
    stringBuffer.append(TEXT_220);
    }
    }
    } else {
    stringBuffer.append(TEXT_221);
    stringBuffer.append(genFeature.getFormattedName());
    stringBuffer.append(TEXT_222);
    stringBuffer.append(genFeature.getFeatureKind());
    stringBuffer.append(TEXT_223);
    }
    }
    stringBuffer.append(TEXT_224);
    if (genFeature.isResolveProxies() && !genFeature.isListType()) {
    stringBuffer.append(TEXT_225);
    stringBuffer.append(genFeature.getImportedType());
    stringBuffer.append(TEXT_226);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_227);
    if (!genFeature.isVolatile()) {
    stringBuffer.append(TEXT_228);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_229);
    } else if (genFeature.hasDelegateFeature()) { GenFeature delegateFeature = genFeature.getDelegateFeature();
    if (delegateFeature.isFeatureMapWrapped()) {
    stringBuffer.append(TEXT_230);
    stringBuffer.append(genFeature.getImportedType());
    stringBuffer.append(TEXT_231);
    stringBuffer.append(delegateFeature.getImportedEffectiveFeatureMapWrapperInternalInterface());
    stringBuffer.append(TEXT_232);
    stringBuffer.append(delegateFeature.getAccessorName());
    stringBuffer.append(TEXT_233);
    stringBuffer.append(genFeature.getQualifiedFeatureAccessorName());
    stringBuffer.append(TEXT_234);
    } else {
    stringBuffer.append(TEXT_235);
    stringBuffer.append(genFeature.getImportedType());
    stringBuffer.append(TEXT_236);
    stringBuffer.append(delegateFeature.getAccessorName());
    stringBuffer.append(TEXT_237);
    stringBuffer.append(genFeature.getQualifiedFeatureAccessorName());
    stringBuffer.append(TEXT_238);
    }
    } else {
    stringBuffer.append(TEXT_239);
    stringBuffer.append(genFeature.getFormattedName());
    stringBuffer.append(TEXT_240);
    stringBuffer.append(genFeature.getFeatureKind());
    stringBuffer.append(TEXT_241);
    }
    stringBuffer.append(TEXT_242);
    }
    if (!genFeature.isListType()) {
    if (genFeature.isBidirectional() && !genFeature.isContainer() && !genFeature.isVolatile() || genFeature.isContains()) {
    stringBuffer.append(TEXT_243);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.notify.NotificationChain"));
    stringBuffer.append(TEXT_244);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_245);
    stringBuffer.append(genFeature.getImportedInternalType());
    stringBuffer.append(TEXT_246);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_247);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.notify.NotificationChain"));
    stringBuffer.append(TEXT_248);
    if (!genFeature.isVolatile()) {
    stringBuffer.append(TEXT_249);
    stringBuffer.append(genFeature.getImportedType());
    stringBuffer.append(TEXT_250);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_251);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_252);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_253);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_254);
    if (genFeature.isUnsettable()) {
    stringBuffer.append(TEXT_255);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_256);
    stringBuffer.append(genFeature.getUncapName());
    stringBuffer.append(TEXT_257);
    stringBuffer.append(genFeature.getUncapName());
    stringBuffer.append(TEXT_258);
    }
    stringBuffer.append(TEXT_259);
    if (genFeature.isUnsettable()) {
    stringBuffer.append(TEXT_260);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.impl.ENotificationImpl"));
    stringBuffer.append(TEXT_261);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.impl.ENotificationImpl"));
    stringBuffer.append(TEXT_262);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.notify.Notification"));
    stringBuffer.append(TEXT_263);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(TEXT_264);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_265);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_266);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_267);
    } else {
    stringBuffer.append(TEXT_268);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.impl.ENotificationImpl"));
    stringBuffer.append(TEXT_269);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.impl.ENotificationImpl"));
    stringBuffer.append(TEXT_270);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.notify.Notification"));
    stringBuffer.append(TEXT_271);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(TEXT_272);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_273);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_274);
    }
    stringBuffer.append(TEXT_275);
    } else if (genFeature.hasDelegateFeature()) { GenFeature delegateFeature = genFeature.getDelegateFeature();
    if (delegateFeature.isFeatureMapWrapped()) {
    stringBuffer.append(TEXT_276);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.FeatureMap"));
    stringBuffer.append(TEXT_277);
    stringBuffer.append(delegateFeature.getImportedEffectiveFeatureMapWrapperInternalInterface());
    stringBuffer.append(TEXT_278);
    stringBuffer.append(delegateFeature.getAccessorName());
    stringBuffer.append(TEXT_279);
    stringBuffer.append(genFeature.getQualifiedFeatureAccessorName());
    stringBuffer.append(TEXT_280);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_281);
    } else {
    stringBuffer.append(TEXT_282);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.FeatureMap"));
    stringBuffer.append(TEXT_283);
    stringBuffer.append(delegateFeature.getAccessorName());
    stringBuffer.append(TEXT_284);
    stringBuffer.append(genFeature.getQualifiedFeatureAccessorName());
    stringBuffer.append(TEXT_285);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_286);
    }
    } else {
    stringBuffer.append(TEXT_287);
    stringBuffer.append(genFeature.getFormattedName());
    stringBuffer.append(TEXT_288);
    stringBuffer.append(genFeature.getFeatureKind());
    stringBuffer.append(TEXT_289);
    }
    stringBuffer.append(TEXT_290);
    }
    if (genFeature.isChangeable()) {
    stringBuffer.append(TEXT_291);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_292);
    stringBuffer.append(genFeature.getImportedType());
    stringBuffer.append(TEXT_293);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_294);
    if (!genFeature.isVolatile()) {
    if (genFeature.isContainer()) { GenFeature reverseFeature = genFeature.getReverse(); GenClass targetClass = reverseFeature.getGenClass();
    stringBuffer.append(TEXT_295);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_296);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(TEXT_297);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_298);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.EcoreUtil"));
    stringBuffer.append(TEXT_299);
    stringBuffer.append(genFeature.getEObjectCast());
    stringBuffer.append(TEXT_300);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_301);
    stringBuffer.append(genModel.getImportedName("java.lang.IllegalArgumentException"));
    stringBuffer.append(TEXT_302);
    stringBuffer.append(genModel.getNonNLS());
    stringBuffer.append(TEXT_303);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.notify.NotificationChain"));
    stringBuffer.append(TEXT_304);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_305);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.InternalEObject"));
    stringBuffer.append(TEXT_306);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_307);
    stringBuffer.append(targetClass.getQualifiedFeatureID(reverseFeature));
    stringBuffer.append(TEXT_308);
    stringBuffer.append(targetClass.getImportedInterfaceName());
    stringBuffer.append(TEXT_309);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.InternalEObject"));
    stringBuffer.append(TEXT_310);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_311);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(TEXT_312);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.impl.ENotificationImpl"));
    stringBuffer.append(TEXT_313);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.notify.Notification"));
    stringBuffer.append(TEXT_314);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(TEXT_315);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_316);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_317);
    } else if (genFeature.isBidirectional() || genFeature.isContains()) {
    stringBuffer.append(TEXT_318);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_319);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_320);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.notify.NotificationChain"));
    stringBuffer.append(TEXT_321);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_322);
    if (!genFeature.isBidirectional()) {
    stringBuffer.append(TEXT_323);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.InternalEObject"));
    stringBuffer.append(TEXT_324);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_325);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(TEXT_326);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_327);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.InternalEObject"));
    stringBuffer.append(TEXT_328);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_329);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(TEXT_330);
    } else { GenFeature reverseFeature = genFeature.getReverse(); GenClass targetClass = reverseFeature.getGenClass();
    stringBuffer.append(TEXT_331);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.InternalEObject"));
    stringBuffer.append(TEXT_332);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_333);
    stringBuffer.append(targetClass.getQualifiedFeatureID(reverseFeature));
    stringBuffer.append(TEXT_334);
    stringBuffer.append(targetClass.getImportedInterfaceName());
    stringBuffer.append(TEXT_335);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_336);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.InternalEObject"));
    stringBuffer.append(TEXT_337);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_338);
    stringBuffer.append(targetClass.getQualifiedFeatureID(reverseFeature));
    stringBuffer.append(TEXT_339);
    stringBuffer.append(targetClass.getImportedInterfaceName());
    stringBuffer.append(TEXT_340);
    }
    stringBuffer.append(TEXT_341);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_342);
    stringBuffer.append(genFeature.getInternalTypeCast());
    stringBuffer.append(TEXT_343);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_344);
    if (genFeature.isUnsettable()) {
    stringBuffer.append(TEXT_345);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_346);
    stringBuffer.append(genFeature.getUncapName());
    stringBuffer.append(TEXT_347);
    stringBuffer.append(genFeature.getUncapName());
    stringBuffer.append(TEXT_348);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.impl.ENotificationImpl"));
    stringBuffer.append(TEXT_349);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.notify.Notification"));
    stringBuffer.append(TEXT_350);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(TEXT_351);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_352);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_353);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_354);
    } else {
    stringBuffer.append(TEXT_355);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.impl.ENotificationImpl"));
    stringBuffer.append(TEXT_356);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.notify.Notification"));
    stringBuffer.append(TEXT_357);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(TEXT_358);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_359);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_360);
    }
    } else {
    stringBuffer.append(TEXT_361);
    stringBuffer.append(genFeature.getImportedType());
    stringBuffer.append(TEXT_362);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_363);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_364);
    if (genFeature.isEnumType()) {
    stringBuffer.append(TEXT_365);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_366);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_367);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_368);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_369);
    } else {
    stringBuffer.append(TEXT_370);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_371);
    stringBuffer.append(genFeature.getInternalTypeCast());
    stringBuffer.append(TEXT_372);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_373);
    }
    if (genFeature.isUnsettable()) {
    stringBuffer.append(TEXT_374);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_375);
    stringBuffer.append(genFeature.getUncapName());
    stringBuffer.append(TEXT_376);
    stringBuffer.append(genFeature.getUncapName());
    stringBuffer.append(TEXT_377);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.impl.ENotificationImpl"));
    stringBuffer.append(TEXT_378);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.notify.Notification"));
    stringBuffer.append(TEXT_379);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(TEXT_380);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_381);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_382);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_383);
    } else {
    stringBuffer.append(TEXT_384);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.impl.ENotificationImpl"));
    stringBuffer.append(TEXT_385);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.notify.Notification"));
    stringBuffer.append(TEXT_386);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(TEXT_387);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_388);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_389);
    }
    }
    } else if (genFeature.hasDelegateFeature()) { GenFeature delegateFeature = genFeature.getDelegateFeature();
    if (delegateFeature.isFeatureMapWrapped()) {
    stringBuffer.append(TEXT_390);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.FeatureMap"));
    stringBuffer.append(TEXT_391);
    stringBuffer.append(delegateFeature.getImportedEffectiveFeatureMapWrapperInternalInterface());
    stringBuffer.append(TEXT_392);
    stringBuffer.append(delegateFeature.getAccessorName());
    stringBuffer.append(TEXT_393);
    stringBuffer.append(genFeature.getQualifiedFeatureAccessorName());
    stringBuffer.append(TEXT_394);
    if (genFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_395);
    stringBuffer.append(genFeature.getObjectType());
    stringBuffer.append(TEXT_396);
    }
    stringBuffer.append(TEXT_397);
    stringBuffer.append(genFeature.getCapName());
    if (genFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_398);
    }
    stringBuffer.append(TEXT_399);
    } else {
    stringBuffer.append(TEXT_400);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.FeatureMap"));
    stringBuffer.append(TEXT_401);
    stringBuffer.append(delegateFeature.getAccessorName());
    stringBuffer.append(TEXT_402);
    stringBuffer.append(genFeature.getQualifiedFeatureAccessorName());
    stringBuffer.append(TEXT_403);
    if (genFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_404);
    stringBuffer.append(genFeature.getObjectType());
    stringBuffer.append(TEXT_405);
    }
    stringBuffer.append(TEXT_406);
    stringBuffer.append(genFeature.getCapName());
    if (genFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_407);
    }
    stringBuffer.append(TEXT_408);
    }
    } else {
    stringBuffer.append(TEXT_409);
    stringBuffer.append(genFeature.getFormattedName());
    stringBuffer.append(TEXT_410);
    stringBuffer.append(genFeature.getFeatureKind());
    stringBuffer.append(TEXT_411);
    }
    stringBuffer.append(TEXT_412);
    }//if (genFeature.isChangeable())
    }//if (!genFeature.isListType())
    if (genFeature.isUnsettable()) {
    if (genFeature.isChangeable()) {
    if (!genFeature.isListType() && genFeature.isReferenceType() && (genFeature.isBidirectional() || genFeature.isContains())) {
    stringBuffer.append(TEXT_413);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.notify.NotificationChain"));
    stringBuffer.append(TEXT_414);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_415);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.notify.NotificationChain"));
    stringBuffer.append(TEXT_416);
    if (!genFeature.isVolatile()) {
    stringBuffer.append(TEXT_417);
    stringBuffer.append(genFeature.getImportedType());
    stringBuffer.append(TEXT_418);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_419);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_420);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_421);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_422);
    stringBuffer.append(genFeature.getUncapName());
    stringBuffer.append(TEXT_423);
    stringBuffer.append(genFeature.getUncapName());
    stringBuffer.append(TEXT_424);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.impl.ENotificationImpl"));
    stringBuffer.append(TEXT_425);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.impl.ENotificationImpl"));
    stringBuffer.append(TEXT_426);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.notify.Notification"));
    stringBuffer.append(TEXT_427);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(TEXT_428);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_429);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_430);
    } else {
    stringBuffer.append(TEXT_431);
    stringBuffer.append(genFeature.getFormattedName());
    stringBuffer.append(TEXT_432);
    stringBuffer.append(genFeature.getFeatureKind());
    stringBuffer.append(TEXT_433);
    }
    stringBuffer.append(TEXT_434);
    }
    stringBuffer.append(TEXT_435);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_436);
    if (!genFeature.isVolatile()) {
    if (genFeature.isListType()) {
    stringBuffer.append(TEXT_437);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.InternalEList"));
    stringBuffer.append(TEXT_438);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_439);
    } else if (genFeature.isBidirectional() || genFeature.isContains()) {
    stringBuffer.append(TEXT_440);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_441);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.notify.NotificationChain"));
    stringBuffer.append(TEXT_442);
    if (!genFeature.isBidirectional()) {
    stringBuffer.append(TEXT_443);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.InternalEObject"));
    stringBuffer.append(TEXT_444);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_445);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(TEXT_446);
    } else { GenFeature reverseFeature = genFeature.getReverse(); GenClass targetClass = reverseFeature.getGenClass();
    stringBuffer.append(TEXT_447);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.InternalEObject"));
    stringBuffer.append(TEXT_448);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_449);
    stringBuffer.append(targetClass.getQualifiedFeatureID(reverseFeature));
    stringBuffer.append(TEXT_450);
    stringBuffer.append(targetClass.getImportedInterfaceName());
    stringBuffer.append(TEXT_451);
    }
    stringBuffer.append(TEXT_452);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_453);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_454);
    stringBuffer.append(genFeature.getUncapName());
    stringBuffer.append(TEXT_455);
    stringBuffer.append(genFeature.getUncapName());
    stringBuffer.append(TEXT_456);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.impl.ENotificationImpl"));
    stringBuffer.append(TEXT_457);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.notify.Notification"));
    stringBuffer.append(TEXT_458);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(TEXT_459);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_460);
    } else {
    stringBuffer.append(TEXT_461);
    stringBuffer.append(genFeature.getImportedType());
    stringBuffer.append(TEXT_462);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_463);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_464);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_465);
    stringBuffer.append(genFeature.getUncapName());
    stringBuffer.append(TEXT_466);
    if (genFeature.isReferenceType()) {
    stringBuffer.append(TEXT_467);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_468);
    stringBuffer.append(genFeature.getUncapName());
    stringBuffer.append(TEXT_469);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.impl.ENotificationImpl"));
    stringBuffer.append(TEXT_470);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.notify.Notification"));
    stringBuffer.append(TEXT_471);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(TEXT_472);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_473);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_474);
    } else {
    stringBuffer.append(TEXT_475);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_476);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_477);
    stringBuffer.append(genFeature.getUncapName());
    stringBuffer.append(TEXT_478);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.impl.ENotificationImpl"));
    stringBuffer.append(TEXT_479);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.notify.Notification"));
    stringBuffer.append(TEXT_480);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(TEXT_481);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_482);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_483);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_484);
    }
    }
    } else if (genFeature.hasDelegateFeature()) { GenFeature delegateFeature = genFeature.getDelegateFeature();
    if (delegateFeature.isFeatureMapWrapped()) {
    stringBuffer.append(TEXT_485);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.FeatureMap"));
    stringBuffer.append(TEXT_486);
    stringBuffer.append(delegateFeature.getImportedEffectiveFeatureMapWrapperInternalInterface());
    stringBuffer.append(TEXT_487);
    stringBuffer.append(delegateFeature.getAccessorName());
    stringBuffer.append(TEXT_488);
    stringBuffer.append(genFeature.getQualifiedFeatureAccessorName());
    stringBuffer.append(TEXT_489);
    } else {
    stringBuffer.append(TEXT_490);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.FeatureMap"));
    stringBuffer.append(TEXT_491);
    stringBuffer.append(delegateFeature.getAccessorName());
    stringBuffer.append(TEXT_492);
    stringBuffer.append(genFeature.getQualifiedFeatureAccessorName());
    stringBuffer.append(TEXT_493);
    }
    } else {
    stringBuffer.append(TEXT_494);
    stringBuffer.append(genFeature.getFormattedName());
    stringBuffer.append(TEXT_495);
    stringBuffer.append(genFeature.getFeatureKind());
    stringBuffer.append(TEXT_496);
    }
    stringBuffer.append(TEXT_497);
    }//if (genFeature.isChangeable())
    stringBuffer.append(TEXT_498);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_499);
    if (!genFeature.isVolatile()) {
    if (genFeature.isListType()) {
    stringBuffer.append(TEXT_500);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_501);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.InternalEList"));
    stringBuffer.append(TEXT_502);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_503);
    } else {
    stringBuffer.append(TEXT_504);
    stringBuffer.append(genFeature.getUncapName());
    stringBuffer.append(TEXT_505);
    }
    } else if (genFeature.hasDelegateFeature()) { GenFeature delegateFeature = genFeature.getDelegateFeature();
    if (delegateFeature.isFeatureMapWrapped()) {
    stringBuffer.append(TEXT_506);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.FeatureMap"));
    stringBuffer.append(TEXT_507);
    stringBuffer.append(delegateFeature.getImportedEffectiveFeatureMapWrapperInternalInterface());
    stringBuffer.append(TEXT_508);
    stringBuffer.append(delegateFeature.getAccessorName());
    stringBuffer.append(TEXT_509);
    stringBuffer.append(genFeature.getQualifiedFeatureAccessorName());
    stringBuffer.append(TEXT_510);
    } else {
    stringBuffer.append(TEXT_511);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.FeatureMap"));
    stringBuffer.append(TEXT_512);
    stringBuffer.append(delegateFeature.getAccessorName());
    stringBuffer.append(TEXT_513);
    stringBuffer.append(genFeature.getQualifiedFeatureAccessorName());
    stringBuffer.append(TEXT_514);
    }
    } else {
    stringBuffer.append(TEXT_515);
    stringBuffer.append(genFeature.getFormattedName());
    stringBuffer.append(TEXT_516);
    stringBuffer.append(genFeature.getFeatureKind());
    stringBuffer.append(TEXT_517);
    }
    stringBuffer.append(TEXT_518);
    }
    }//for
    for (Iterator i=genClass.getImplementedGenOperations().iterator(); i.hasNext();) { GenOperation genOperation = (GenOperation)i.next();
    stringBuffer.append(TEXT_519);
    stringBuffer.append(genOperation.getImportedReturnType());
    stringBuffer.append(TEXT_520);
    stringBuffer.append(genOperation.getName());
    stringBuffer.append(TEXT_521);
    stringBuffer.append(genOperation.getParameters());
    stringBuffer.append(TEXT_522);
    stringBuffer.append(genOperation.getThrows());
    stringBuffer.append(TEXT_523);
    if (genOperation.hasBody()) {
    stringBuffer.append(TEXT_524);
    stringBuffer.append(genOperation.getBody(genModel.getIndentation(stringBuffer)));
    } else if (genOperation.isInvariant()) {
    stringBuffer.append(TEXT_525);
    } else {
    stringBuffer.append(TEXT_526);
    }
    stringBuffer.append(TEXT_527);
    }//for
    if (!genClass.getEInverseAddGenFeatures().isEmpty()) {
    stringBuffer.append(TEXT_528);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.notify.NotificationChain"));
    stringBuffer.append(TEXT_529);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.InternalEObject"));
    stringBuffer.append(TEXT_530);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.notify.NotificationChain"));
    stringBuffer.append(TEXT_531);
    for (Iterator i=genClass.getEInverseAddGenFeatures().iterator(); i.hasNext();) { GenFeature genFeature = (GenFeature)i.next();
    stringBuffer.append(TEXT_532);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(TEXT_533);
    if (genFeature.isListType()) {
    if (genFeature.isMapType() && genFeature.isEffectiveSuppressEMFTypes()) {
    stringBuffer.append(TEXT_534);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.InternalEList"));
    stringBuffer.append(TEXT_535);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.util.EMap$InternalMapView"));
    stringBuffer.append(TEXT_536);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_537);
    } else {
    stringBuffer.append(TEXT_538);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.InternalEList"));
    stringBuffer.append(TEXT_539);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_540);
    }
    } else if (genFeature.isContainer()) {
    stringBuffer.append(TEXT_541);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(TEXT_542);
    } else {
    stringBuffer.append(TEXT_543);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_544);
    if (genFeature.isContains()) {
    stringBuffer.append(TEXT_545);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.InternalEObject"));
    stringBuffer.append(TEXT_546);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_547);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(TEXT_548);
    } else { GenFeature reverseFeature = genFeature.getReverse(); GenClass targetClass = reverseFeature.getGenClass();
    stringBuffer.append(TEXT_549);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.InternalEObject"));
    stringBuffer.append(TEXT_550);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_551);
    stringBuffer.append(targetClass.getQualifiedFeatureID(reverseFeature));
    stringBuffer.append(TEXT_552);
    stringBuffer.append(targetClass.getImportedInterfaceName());
    stringBuffer.append(TEXT_553);
    }
    stringBuffer.append(TEXT_554);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_555);
    stringBuffer.append(genFeature.getImportedType());
    stringBuffer.append(TEXT_556);
    }
    }
    stringBuffer.append(TEXT_557);
    }
    if (!genClass.getEInverseRemoveGenFeatures().isEmpty()) {
    stringBuffer.append(TEXT_558);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.notify.NotificationChain"));
    stringBuffer.append(TEXT_559);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.InternalEObject"));
    stringBuffer.append(TEXT_560);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.notify.NotificationChain"));
    stringBuffer.append(TEXT_561);
    for (Iterator i=genClass.getEInverseRemoveGenFeatures().iterator(); i.hasNext();) { GenFeature genFeature = (GenFeature)i.next();
    stringBuffer.append(TEXT_562);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(TEXT_563);
    if (genFeature.isListType()) {
    if (genFeature.isMapType() && genFeature.isEffectiveSuppressEMFTypes()) {
    stringBuffer.append(TEXT_564);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.InternalEList"));
    stringBuffer.append(TEXT_565);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.util.EMap$InternalMapView"));
    stringBuffer.append(TEXT_566);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_567);
    } else if (genFeature.isFeatureMapWrapped()) {
    stringBuffer.append(TEXT_568);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.InternalEList"));
    stringBuffer.append(TEXT_569);
    stringBuffer.append(genFeature.getImportedEffectiveFeatureMapWrapperInternalInterface());
    stringBuffer.append(TEXT_570);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_571);
    } else {
    stringBuffer.append(TEXT_572);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.InternalEList"));
    stringBuffer.append(TEXT_573);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_574);
    }
    } else if (genFeature.isContainer()) {
    stringBuffer.append(TEXT_575);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(TEXT_576);
    } else if (genFeature.isUnsettable()) {
    stringBuffer.append(TEXT_577);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_578);
    } else {
    stringBuffer.append(TEXT_579);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_580);
    }
    }
    stringBuffer.append(TEXT_581);
    }
    if (!genClass.getEBasicRemoveFromContainerGenFeatures().isEmpty()) {
    stringBuffer.append(TEXT_582);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.notify.NotificationChain"));
    stringBuffer.append(TEXT_583);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.notify.NotificationChain"));
    stringBuffer.append(TEXT_584);
    for (Iterator i=genClass.getEBasicRemoveFromContainerGenFeatures().iterator(); i.hasNext();) { GenFeature genFeature = (GenFeature)i.next();
    GenFeature reverseFeature = genFeature.getReverse(); GenClass targetClass = reverseFeature.getGenClass();
    stringBuffer.append(TEXT_585);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(TEXT_586);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.InternalEObject"));
    stringBuffer.append(TEXT_587);
    stringBuffer.append(targetClass.getQualifiedFeatureID(reverseFeature));
    stringBuffer.append(TEXT_588);
    stringBuffer.append(targetClass.getImportedInterfaceName());
    stringBuffer.append(TEXT_589);
    }
    stringBuffer.append(TEXT_590);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.InternalEObject"));
    stringBuffer.append(TEXT_591);
    }
    if (!genClass.getAllGenFeatures().isEmpty()) {
    stringBuffer.append(TEXT_592);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.EStructuralFeature"));
    stringBuffer.append(TEXT_593);
    for (Iterator i=genClass.getAllGenFeatures().iterator(); i.hasNext();) { GenFeature genFeature = (GenFeature)i.next();
    stringBuffer.append(TEXT_594);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(TEXT_595);
    if (genFeature.isPrimitiveType() && !genFeature.isListType()) {
    if (genFeature.isBooleanType()) {
    stringBuffer.append(TEXT_596);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_597);
    } else {
    stringBuffer.append(TEXT_598);
    stringBuffer.append(genFeature.getObjectType());
    stringBuffer.append(TEXT_599);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_600);
    }
    } else if (genFeature.isResolveProxies() && !genFeature.isListType()) {
    stringBuffer.append(TEXT_601);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_602);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_603);
    } else if (genFeature.isMapType() && genFeature.isEffectiveSuppressEMFTypes()) {
    stringBuffer.append(TEXT_604);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.util.EMap$InternalMapView"));
    stringBuffer.append(TEXT_605);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_606);
    } else if (genFeature.isFeatureMapWrapped()) {
    stringBuffer.append(TEXT_607);
    stringBuffer.append(genFeature.getImportedEffectiveFeatureMapWrapperInternalInterface());
    stringBuffer.append(TEXT_608);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_609);
    } else {
    stringBuffer.append(TEXT_610);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_611);
    }
    }
    stringBuffer.append(TEXT_612);
    }
    if (!genClass.getESetGenFeatures().isEmpty()) {
    stringBuffer.append(TEXT_613);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.EStructuralFeature"));
    stringBuffer.append(TEXT_614);
    for (Iterator i=genClass.getESetGenFeatures().iterator(); i.hasNext();) { GenFeature genFeature = (GenFeature)i.next();
    stringBuffer.append(TEXT_615);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(TEXT_616);
    if (genFeature.isListType()) {
    if (genFeature.isFeatureMapWrapped()) {
    stringBuffer.append(TEXT_617);
    stringBuffer.append(genFeature.getImportedEffectiveFeatureMapWrapperInternalInterface());
    stringBuffer.append(TEXT_618);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_619);
    } else {
    stringBuffer.append(TEXT_620);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_621);
    }
    if (genFeature.isFeatureMapWrapped()) {
    stringBuffer.append(TEXT_622);
    stringBuffer.append(genFeature.getImportedEffectiveFeatureMapWrapperInternalInterface());
    stringBuffer.append(TEXT_623);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_624);
    stringBuffer.append(genModel.getImportedName("java.util.Collection"));
    stringBuffer.append(TEXT_625);
    } else if (genFeature.isMapType() && genFeature.isEffectiveSuppressEMFTypes()) {
    stringBuffer.append(TEXT_626);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.util.EMap$InternalMapView"));
    stringBuffer.append(TEXT_627);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_628);
    stringBuffer.append(genModel.getImportedName("java.util.Collection"));
    stringBuffer.append(TEXT_629);
    } else {
    stringBuffer.append(TEXT_630);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_631);
    stringBuffer.append(genModel.getImportedName("java.util.Collection"));
    stringBuffer.append(TEXT_632);
    }
    } else if (genFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_633);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_634);
    stringBuffer.append(genFeature.getObjectType());
    stringBuffer.append(TEXT_635);
    stringBuffer.append(genFeature.getPrimitiveValueFunction());
    stringBuffer.append(TEXT_636);
    } else {
    stringBuffer.append(TEXT_637);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_638);
    stringBuffer.append(genFeature.getImportedType());
    stringBuffer.append(TEXT_639);
    }
    stringBuffer.append(TEXT_640);
    }
    stringBuffer.append(TEXT_641);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.EStructuralFeature"));
    stringBuffer.append(TEXT_642);
    for (Iterator i=genClass.getESetGenFeatures().iterator(); i.hasNext();) { GenFeature genFeature = (GenFeature)i.next();
    stringBuffer.append(TEXT_643);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(TEXT_644);
    if (genFeature.isListType() && !genFeature.isUnsettable()) {
    if (genFeature.isFeatureMapWrapped()) {
    stringBuffer.append(TEXT_645);
    stringBuffer.append(genFeature.getImportedEffectiveFeatureMapWrapperInternalInterface());
    stringBuffer.append(TEXT_646);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_647);
    } else {
    stringBuffer.append(TEXT_648);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_649);
    }
    } else if (genFeature.isUnsettable()) {
    stringBuffer.append(TEXT_650);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_651);
    } else if (genFeature.isVolatile()) {
    stringBuffer.append(TEXT_652);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_653);
    stringBuffer.append(genFeature.getImportedType());
    stringBuffer.append(TEXT_654);
    stringBuffer.append(genFeature.getStaticDefaultValue());
    stringBuffer.append(TEXT_655);
    stringBuffer.append(genModel.getNonNLS(genFeature.getStaticDefaultValue()));
    } else if (genFeature.isReferenceType()) {
    stringBuffer.append(TEXT_656);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_657);
    stringBuffer.append(genFeature.getImportedType());
    stringBuffer.append(TEXT_658);
    } else {
    stringBuffer.append(TEXT_659);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_660);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_661);
    }
    stringBuffer.append(TEXT_662);
    }
    stringBuffer.append(TEXT_663);
    }
    if (!genClass.getAllGenFeatures().isEmpty()) {
    stringBuffer.append(TEXT_664);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.EStructuralFeature"));
    stringBuffer.append(TEXT_665);
    for (Iterator i=genClass.getAllGenFeatures().iterator(); i.hasNext();) { GenFeature genFeature = (GenFeature)i.next();
    stringBuffer.append(TEXT_666);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(TEXT_667);
    if (genFeature.isListType() && !genFeature.isUnsettable()) {
    if (genFeature.isFeatureMapWrapped()) {
    if (genFeature.isVolatile()) {
    stringBuffer.append(TEXT_668);
    stringBuffer.append(genFeature.getImportedEffectiveFeatureMapWrapperInternalInterface());
    stringBuffer.append(TEXT_669);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_670);
    } else {
    stringBuffer.append(TEXT_671);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_672);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_673);
    }
    } else {
    if (genFeature.isVolatile()) {
    stringBuffer.append(TEXT_674);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_675);
    } else {
    stringBuffer.append(TEXT_676);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_677);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_678);
    }
    }
    } else if (genFeature.isUnsettable()) {
    stringBuffer.append(TEXT_679);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_680);
    } else if (genFeature.isResolveProxies()) {
    if (genFeature.isVolatile()) {
    stringBuffer.append(TEXT_681);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_682);
    } else {
    stringBuffer.append(TEXT_683);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_684);
    }
    } else if (genFeature.isReferenceType()) {
    if (genFeature.isVolatile() || genFeature.isContainer()) {
    stringBuffer.append(TEXT_685);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_686);
    } else {
    stringBuffer.append(TEXT_687);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_688);
    }
    } else if (genFeature.isPrimitiveType() || genFeature.isEnumType()) {
    if (genFeature.isVolatile()) {
    stringBuffer.append(TEXT_689);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_690);
    stringBuffer.append(genFeature.getStaticDefaultValue());
    stringBuffer.append(TEXT_691);
    stringBuffer.append(genModel.getNonNLS(genFeature.getStaticDefaultValue()));
    } else {
    stringBuffer.append(TEXT_692);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_693);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_694);
    }
    } else {//datatype
    if (genFeature.isVolatile()) {
    if (genFeature.isSetDefaultValue()) {
    stringBuffer.append(TEXT_695);
    stringBuffer.append(genFeature.getStaticDefaultValue());
    stringBuffer.append(TEXT_696);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_697);
    stringBuffer.append(genModel.getNonNLS(genFeature.getStaticDefaultValue()));
    } else {
    stringBuffer.append(TEXT_698);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_699);
    }
    } else {
    stringBuffer.append(TEXT_700);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_701);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_702);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_703);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_704);
    }
    }
    }
    stringBuffer.append(TEXT_705);
    }
    if (!genClass.getMixinGenFeatures().isEmpty()) {
    stringBuffer.append(TEXT_706);
    for (Iterator m=genClass.getMixinGenClasses().iterator(); m.hasNext();) { GenClass mixinGenClass = (GenClass)m.next(); 
    stringBuffer.append(TEXT_707);
    stringBuffer.append(mixinGenClass.getImportedInterfaceName());
    stringBuffer.append(TEXT_708);
    for (Iterator f=mixinGenClass.getGenFeatures().iterator(); f.hasNext();) { GenFeature genFeature = (GenFeature)f.next(); 
    stringBuffer.append(TEXT_709);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(TEXT_710);
    stringBuffer.append(mixinGenClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(TEXT_711);
    }
    stringBuffer.append(TEXT_712);
    }
    stringBuffer.append(TEXT_713);
    for (Iterator m=genClass.getMixinGenClasses().iterator(); m.hasNext();) { GenClass mixinGenClass = (GenClass)m.next(); 
    stringBuffer.append(TEXT_714);
    stringBuffer.append(mixinGenClass.getImportedInterfaceName());
    stringBuffer.append(TEXT_715);
    for (Iterator f=mixinGenClass.getGenFeatures().iterator(); f.hasNext();) { GenFeature genFeature = (GenFeature)f.next(); 
    stringBuffer.append(TEXT_716);
    stringBuffer.append(mixinGenClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(TEXT_717);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(TEXT_718);
    }
    stringBuffer.append(TEXT_719);
    }
    stringBuffer.append(TEXT_720);
    }
    if (!genClass.getToStringGenFeatures().isEmpty()) {
    stringBuffer.append(TEXT_721);
    { boolean first = true;
    for (Iterator i=genClass.getToStringGenFeatures().iterator(); i.hasNext(); ) { GenFeature genFeature = (GenFeature)i.next();
    if (first) { first = false;
    stringBuffer.append(TEXT_722);
    stringBuffer.append(genFeature.getName());
    stringBuffer.append(TEXT_723);
    stringBuffer.append(genModel.getNonNLS());
    } else {
    stringBuffer.append(TEXT_724);
    stringBuffer.append(genFeature.getName());
    stringBuffer.append(TEXT_725);
    stringBuffer.append(genModel.getNonNLS());
    }
    if (genFeature.isUnsettable() && !genFeature.isListType()) {
    stringBuffer.append(TEXT_726);
    stringBuffer.append(genFeature.getUncapName());
    stringBuffer.append(TEXT_727);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_728);
    stringBuffer.append(genModel.getNonNLS());
    } else {
    stringBuffer.append(TEXT_729);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_730);
    }
    }
    }
    stringBuffer.append(TEXT_731);
    }
    if (genClass.isMapEntry()) { GenFeature keyFeature = genClass.getMapEntryKeyFeature(); GenFeature valueFeature = genClass.getMapEntryValueFeature();
    stringBuffer.append(TEXT_732);
    if (keyFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_733);
    stringBuffer.append(keyFeature.getObjectType());
    stringBuffer.append(TEXT_734);
    } else {
    stringBuffer.append(TEXT_735);
    }
    stringBuffer.append(TEXT_736);
    if (keyFeature.isListType()) {
    stringBuffer.append(TEXT_737);
    stringBuffer.append(genModel.getImportedName("java.util.Collection"));
    stringBuffer.append(TEXT_738);
    } else if (keyFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_739);
    stringBuffer.append(keyFeature.getObjectType());
    stringBuffer.append(TEXT_740);
    stringBuffer.append(keyFeature.getPrimitiveValueFunction());
    stringBuffer.append(TEXT_741);
    } else {
    stringBuffer.append(TEXT_742);
    stringBuffer.append(keyFeature.getImportedType());
    stringBuffer.append(TEXT_743);
    }
    stringBuffer.append(TEXT_744);
    if (valueFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_745);
    stringBuffer.append(valueFeature.getObjectType());
    stringBuffer.append(TEXT_746);
    } else {
    stringBuffer.append(TEXT_747);
    }
    stringBuffer.append(TEXT_748);
    if (valueFeature.isListType()) {
    stringBuffer.append(TEXT_749);
    stringBuffer.append(genModel.getImportedName("java.util.Collection"));
    stringBuffer.append(TEXT_750);
    } else if (valueFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_751);
    stringBuffer.append(valueFeature.getObjectType());
    stringBuffer.append(TEXT_752);
    stringBuffer.append(valueFeature.getPrimitiveValueFunction());
    stringBuffer.append(TEXT_753);
    } else {
    stringBuffer.append(TEXT_754);
    stringBuffer.append(valueFeature.getImportedType());
    stringBuffer.append(TEXT_755);
    }
    stringBuffer.append(TEXT_756);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.util.EMap"));
    stringBuffer.append(TEXT_757);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.EObject"));
    stringBuffer.append(TEXT_758);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.util.EMap"));
    stringBuffer.append(TEXT_759);
    }
    stringBuffer.append(TEXT_760);
    stringBuffer.append(genClass.getClassName());
    genModel.emitSortedImports();
    stringBuffer.append(TEXT_761);
    return stringBuffer.toString();
  }
}
