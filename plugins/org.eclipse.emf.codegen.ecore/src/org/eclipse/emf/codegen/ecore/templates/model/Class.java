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
  protected final String TEXT_37 = "()" + NL + "\t * @generated" + NL + "\t * @ordered" + NL + "\t */" + NL + "\tprotected static final ";
  protected final String TEXT_38 = " ";
  protected final String TEXT_39 = "_EDEFAULT = ";
  protected final String TEXT_40 = ";";
  protected final String TEXT_41 = NL;
  protected final String TEXT_42 = NL + "\t/**" + NL + "\t * The cached value of the '{@link #";
  protected final String TEXT_43 = "() <em>";
  protected final String TEXT_44 = "</em>}' ";
  protected final String TEXT_45 = "." + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @see #";
  protected final String TEXT_46 = "()" + NL + "\t * @generated" + NL + "\t * @ordered" + NL + "\t */" + NL + "\tprotected ";
  protected final String TEXT_47 = " ";
  protected final String TEXT_48 = " = ";
  protected final String TEXT_49 = "_EDEFAULT;" + NL;
  protected final String TEXT_50 = NL + "\t/**" + NL + "\t * This is true if the ";
  protected final String TEXT_51 = " ";
  protected final String TEXT_52 = " has been set." + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t * @ordered" + NL + "\t */" + NL + "\tprotected boolean ";
  protected final String TEXT_53 = "ESet = false;" + NL;
  protected final String TEXT_54 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */" + NL + "\tprotected ";
  protected final String TEXT_55 = "()" + NL + "\t{" + NL + "\t\tsuper();" + NL + "\t}" + NL + "" + NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */" + NL + "\tprotected ";
  protected final String TEXT_56 = " eStaticClass()" + NL + "\t{" + NL + "\t\treturn ";
  protected final String TEXT_57 = ".eINSTANCE.get";
  protected final String TEXT_58 = "();" + NL + "\t}" + NL;
  protected final String TEXT_59 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */" + NL + "\tpublic ";
  protected final String TEXT_60 = " ";
  protected final String TEXT_61 = "()" + NL + "\t{";
  protected final String TEXT_62 = NL + "\t\treturn ";
  protected final String TEXT_63 = "(";
  protected final String TEXT_64 = "(";
  protected final String TEXT_65 = ")eGet(";
  protected final String TEXT_66 = ".eINSTANCE.get";
  protected final String TEXT_67 = "(), true)";
  protected final String TEXT_68 = ").";
  protected final String TEXT_69 = "()";
  protected final String TEXT_70 = ";";
  protected final String TEXT_71 = NL + "\t\tif (";
  protected final String TEXT_72 = " == null)" + NL + "\t\t{";
  protected final String TEXT_73 = NL + "\t\t\t";
  protected final String TEXT_74 = " = new ";
  protected final String TEXT_75 = "(";
  protected final String TEXT_76 = ".eINSTANCE.get";
  protected final String TEXT_77 = "(), ";
  protected final String TEXT_78 = ".class, this, ";
  protected final String TEXT_79 = ");";
  protected final String TEXT_80 = NL + "\t\t\t";
  protected final String TEXT_81 = " = new ";
  protected final String TEXT_82 = "(";
  protected final String TEXT_83 = ".class, this, ";
  protected final String TEXT_84 = ", ";
  protected final String TEXT_85 = ");";
  protected final String TEXT_86 = NL + "\t\t\t";
  protected final String TEXT_87 = " = new ";
  protected final String TEXT_88 = "(";
  protected final String TEXT_89 = ".class, this, ";
  protected final String TEXT_90 = ");";
  protected final String TEXT_91 = NL + "\t\t\t";
  protected final String TEXT_92 = " = new ";
  protected final String TEXT_93 = ".ManyInverse(";
  protected final String TEXT_94 = ".class, this, ";
  protected final String TEXT_95 = ", ";
  protected final String TEXT_96 = ");";
  protected final String TEXT_97 = NL + "\t\t\t";
  protected final String TEXT_98 = " = new ";
  protected final String TEXT_99 = ".ManyInverse(";
  protected final String TEXT_100 = ".class, this, ";
  protected final String TEXT_101 = ", ";
  protected final String TEXT_102 = ");";
  protected final String TEXT_103 = NL + "\t\t\t";
  protected final String TEXT_104 = " = new ";
  protected final String TEXT_105 = "(";
  protected final String TEXT_106 = ".class, this, ";
  protected final String TEXT_107 = ", ";
  protected final String TEXT_108 = ");";
  protected final String TEXT_109 = NL + "\t\t\t";
  protected final String TEXT_110 = " = new ";
  protected final String TEXT_111 = "(";
  protected final String TEXT_112 = ".class, this, ";
  protected final String TEXT_113 = ", ";
  protected final String TEXT_114 = ");";
  protected final String TEXT_115 = NL + "\t\t\t";
  protected final String TEXT_116 = " = new ";
  protected final String TEXT_117 = "(";
  protected final String TEXT_118 = ".class, this, ";
  protected final String TEXT_119 = ");";
  protected final String TEXT_120 = NL + "\t\t\t";
  protected final String TEXT_121 = " = new ";
  protected final String TEXT_122 = "(";
  protected final String TEXT_123 = ".class, this, ";
  protected final String TEXT_124 = ");";
  protected final String TEXT_125 = NL + "\t\t\t";
  protected final String TEXT_126 = " = new ";
  protected final String TEXT_127 = "(new ";
  protected final String TEXT_128 = "(this, ";
  protected final String TEXT_129 = "));";
  protected final String TEXT_130 = NL + "\t\t\t";
  protected final String TEXT_131 = " = new ";
  protected final String TEXT_132 = "(this, ";
  protected final String TEXT_133 = ");";
  protected final String TEXT_134 = NL + "\t\t\t";
  protected final String TEXT_135 = " = new ";
  protected final String TEXT_136 = "(";
  protected final String TEXT_137 = ".class, this, ";
  protected final String TEXT_138 = ");";
  protected final String TEXT_139 = NL + "\t\t\t";
  protected final String TEXT_140 = " = new ";
  protected final String TEXT_141 = "(";
  protected final String TEXT_142 = ".class, this, ";
  protected final String TEXT_143 = ");";
  protected final String TEXT_144 = NL + "\t\t}" + NL + "\t\treturn ";
  protected final String TEXT_145 = ";";
  protected final String TEXT_146 = NL + "\t\tif (eContainerFeatureID != ";
  protected final String TEXT_147 = ") return null;" + NL + "\t\treturn (";
  protected final String TEXT_148 = ")eContainer;";
  protected final String TEXT_149 = NL + "\t\tif (";
  protected final String TEXT_150 = " != null && ";
  protected final String TEXT_151 = ".eIsProxy())" + NL + "\t\t{" + NL + "\t\t\t";
  protected final String TEXT_152 = " old";
  protected final String TEXT_153 = " = ";
  protected final String TEXT_154 = ";" + NL + "\t\t\t";
  protected final String TEXT_155 = " = (";
  protected final String TEXT_156 = ")eResolveProxy((";
  protected final String TEXT_157 = ")";
  protected final String TEXT_158 = ");" + NL + "\t\t\tif (";
  protected final String TEXT_159 = " != old";
  protected final String TEXT_160 = ")" + NL + "\t\t\t{" + NL + "\t\t\t\tif (eNotificationRequired())" + NL + "\t\t\t\t\teNotify(new ";
  protected final String TEXT_161 = "(this, ";
  protected final String TEXT_162 = ".RESOLVE, ";
  protected final String TEXT_163 = ", old";
  protected final String TEXT_164 = ", ";
  protected final String TEXT_165 = "));" + NL + "\t\t\t}" + NL + "\t\t}";
  protected final String TEXT_166 = NL + "\t\treturn ";
  protected final String TEXT_167 = ";";
  protected final String TEXT_168 = NL + "\t\t";
  protected final String TEXT_169 = " ";
  protected final String TEXT_170 = " = basicGet";
  protected final String TEXT_171 = "();" + NL + "\t\treturn ";
  protected final String TEXT_172 = " == null ? null : (";
  protected final String TEXT_173 = ")eResolveProxy((";
  protected final String TEXT_174 = ")";
  protected final String TEXT_175 = ");";
  protected final String TEXT_176 = NL + "\t\treturn new ";
  protected final String TEXT_177 = "((";
  protected final String TEXT_178 = ")((";
  protected final String TEXT_179 = ")get";
  protected final String TEXT_180 = "()).featureMap().list(";
  protected final String TEXT_181 = "()));";
  protected final String TEXT_182 = NL + "\t\treturn (";
  protected final String TEXT_183 = ")((";
  protected final String TEXT_184 = ")get";
  protected final String TEXT_185 = "()).list(";
  protected final String TEXT_186 = "());";
  protected final String TEXT_187 = NL + "\t\treturn ((";
  protected final String TEXT_188 = ")get";
  protected final String TEXT_189 = "()).featureMap().list(";
  protected final String TEXT_190 = "());";
  protected final String TEXT_191 = NL + "\t\treturn ((";
  protected final String TEXT_192 = ")get";
  protected final String TEXT_193 = "()).list(";
  protected final String TEXT_194 = "());";
  protected final String TEXT_195 = NL + "\t\treturn ";
  protected final String TEXT_196 = "(";
  protected final String TEXT_197 = "(";
  protected final String TEXT_198 = ")((";
  protected final String TEXT_199 = ")get";
  protected final String TEXT_200 = "()).featureMap().get(";
  protected final String TEXT_201 = "(), true)";
  protected final String TEXT_202 = ").";
  protected final String TEXT_203 = "()";
  protected final String TEXT_204 = ";";
  protected final String TEXT_205 = NL + "\t\treturn ";
  protected final String TEXT_206 = "(";
  protected final String TEXT_207 = "(";
  protected final String TEXT_208 = ")get";
  protected final String TEXT_209 = "().get(";
  protected final String TEXT_210 = "(), true)";
  protected final String TEXT_211 = ").";
  protected final String TEXT_212 = "()";
  protected final String TEXT_213 = ";";
  protected final String TEXT_214 = NL + "\t\t// TODO: implement this method to return the '";
  protected final String TEXT_215 = "' ";
  protected final String TEXT_216 = NL + "\t\t// Ensure that you remove @generated or mark it @generated NOT" + NL + "\t\tthrow new UnsupportedOperationException();";
  protected final String TEXT_217 = NL + "\t}" + NL;
  protected final String TEXT_218 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */" + NL + "\tpublic ";
  protected final String TEXT_219 = " basicGet";
  protected final String TEXT_220 = "()" + NL + "\t{";
  protected final String TEXT_221 = NL + "\t\treturn ";
  protected final String TEXT_222 = ";";
  protected final String TEXT_223 = NL + "\t\treturn (";
  protected final String TEXT_224 = ")((";
  protected final String TEXT_225 = ")get";
  protected final String TEXT_226 = "()).featureMap().get(";
  protected final String TEXT_227 = "(), false);";
  protected final String TEXT_228 = NL + "\t\treturn (";
  protected final String TEXT_229 = ")get";
  protected final String TEXT_230 = "().get(";
  protected final String TEXT_231 = "(), false);";
  protected final String TEXT_232 = NL + "\t\t// TODO: implement this method to return the '";
  protected final String TEXT_233 = "' ";
  protected final String TEXT_234 = NL + "\t\t// -> do not perform proxy resolution" + NL + "\t\t// Ensure that you remove @generated or mark it @generated NOT" + NL + "\t\tthrow new UnsupportedOperationException();";
  protected final String TEXT_235 = NL + "\t}" + NL;
  protected final String TEXT_236 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */" + NL + "\tpublic ";
  protected final String TEXT_237 = " basicSet";
  protected final String TEXT_238 = "(";
  protected final String TEXT_239 = " new";
  protected final String TEXT_240 = ", ";
  protected final String TEXT_241 = " msgs)" + NL + "\t{";
  protected final String TEXT_242 = NL + "\t\t";
  protected final String TEXT_243 = " old";
  protected final String TEXT_244 = " = ";
  protected final String TEXT_245 = ";" + NL + "\t\t";
  protected final String TEXT_246 = " = new";
  protected final String TEXT_247 = ";";
  protected final String TEXT_248 = NL + "\t\tboolean old";
  protected final String TEXT_249 = "ESet = ";
  protected final String TEXT_250 = "ESet;" + NL + "\t\t";
  protected final String TEXT_251 = "ESet = true;";
  protected final String TEXT_252 = NL + "\t\tif (eNotificationRequired())" + NL + "\t\t{";
  protected final String TEXT_253 = NL + "\t\t\t";
  protected final String TEXT_254 = " notification = new ";
  protected final String TEXT_255 = "(this, ";
  protected final String TEXT_256 = ".SET, ";
  protected final String TEXT_257 = ", old";
  protected final String TEXT_258 = ", new";
  protected final String TEXT_259 = ", !old";
  protected final String TEXT_260 = "ESet);";
  protected final String TEXT_261 = NL + "\t\t\t";
  protected final String TEXT_262 = " notification = new ";
  protected final String TEXT_263 = "(this, ";
  protected final String TEXT_264 = ".SET, ";
  protected final String TEXT_265 = ", old";
  protected final String TEXT_266 = ", new";
  protected final String TEXT_267 = ");";
  protected final String TEXT_268 = NL + "\t\t\tif (msgs == null) msgs = notification; else msgs.add(notification);" + NL + "\t\t}" + NL + "\t\treturn msgs;";
  protected final String TEXT_269 = NL + "\t\treturn ((";
  protected final String TEXT_270 = ".Internal)((";
  protected final String TEXT_271 = ")get";
  protected final String TEXT_272 = "()).featureMap()).basicAdd(";
  protected final String TEXT_273 = "(), new";
  protected final String TEXT_274 = ", null);";
  protected final String TEXT_275 = NL + "\t\treturn ((";
  protected final String TEXT_276 = ".Internal)get";
  protected final String TEXT_277 = "()).basicAdd(";
  protected final String TEXT_278 = "(), new";
  protected final String TEXT_279 = ", null);";
  protected final String TEXT_280 = NL + "\t\t// TODO: implement this method to set the contained '";
  protected final String TEXT_281 = "' ";
  protected final String TEXT_282 = NL + "\t\t// -> this method is automatically invoked to keep the containment relationship in synch" + NL + "\t\t// -> do not modify other features" + NL + "\t\t// -> return msgs, after adding any generated Notification to it (if it is null, a NotificationChain object must be created first)" + NL + "\t\t// Ensure that you remove @generated or mark it @generated NOT" + NL + "\t\tthrow new UnsupportedOperationException();";
  protected final String TEXT_283 = NL + "\t}" + NL;
  protected final String TEXT_284 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */" + NL + "\tpublic void set";
  protected final String TEXT_285 = "(";
  protected final String TEXT_286 = " new";
  protected final String TEXT_287 = ")" + NL + "\t{";
  protected final String TEXT_288 = NL + "\t\teSet(";
  protected final String TEXT_289 = ".eINSTANCE.get";
  protected final String TEXT_290 = "(), ";
  protected final String TEXT_291 = "new ";
  protected final String TEXT_292 = "(";
  protected final String TEXT_293 = "new";
  protected final String TEXT_294 = ")";
  protected final String TEXT_295 = ");";
  protected final String TEXT_296 = NL + "\t\tif (new";
  protected final String TEXT_297 = " != eContainer || (eContainerFeatureID != ";
  protected final String TEXT_298 = " && new";
  protected final String TEXT_299 = " != null))" + NL + "\t\t{" + NL + "\t\t\tif (";
  protected final String TEXT_300 = ".isAncestor(this, ";
  protected final String TEXT_301 = "new";
  protected final String TEXT_302 = "))" + NL + "\t\t\t\tthrow new ";
  protected final String TEXT_303 = "(\"Recursive containment not allowed for \" + toString());";
  protected final String TEXT_304 = NL + "\t\t\t";
  protected final String TEXT_305 = " msgs = null;" + NL + "\t\t\tif (eContainer != null)" + NL + "\t\t\t\tmsgs = eBasicRemoveFromContainer(msgs);" + NL + "\t\t\tif (new";
  protected final String TEXT_306 = " != null)" + NL + "\t\t\t\tmsgs = ((";
  protected final String TEXT_307 = ")new";
  protected final String TEXT_308 = ").eInverseAdd(this, ";
  protected final String TEXT_309 = ", ";
  protected final String TEXT_310 = ".class, msgs);" + NL + "\t\t\tmsgs = eBasicSetContainer((";
  protected final String TEXT_311 = ")new";
  protected final String TEXT_312 = ", ";
  protected final String TEXT_313 = ", msgs);" + NL + "\t\t\tif (msgs != null) msgs.dispatch();" + NL + "\t\t}" + NL + "\t\telse if (eNotificationRequired())" + NL + "\t\t\teNotify(new ";
  protected final String TEXT_314 = "(this, ";
  protected final String TEXT_315 = ".SET, ";
  protected final String TEXT_316 = ", new";
  protected final String TEXT_317 = ", new";
  protected final String TEXT_318 = "));";
  protected final String TEXT_319 = NL + "\t\tif (new";
  protected final String TEXT_320 = " != ";
  protected final String TEXT_321 = ")" + NL + "\t\t{" + NL + "\t\t\t";
  protected final String TEXT_322 = " msgs = null;" + NL + "\t\t\tif (";
  protected final String TEXT_323 = " != null)";
  protected final String TEXT_324 = NL + "\t\t\t\tmsgs = ((";
  protected final String TEXT_325 = ")";
  protected final String TEXT_326 = ").eInverseRemove(this, EOPPOSITE_FEATURE_BASE - ";
  protected final String TEXT_327 = ", null, msgs);" + NL + "\t\t\tif (new";
  protected final String TEXT_328 = " != null)" + NL + "\t\t\t\tmsgs = ((";
  protected final String TEXT_329 = ")new";
  protected final String TEXT_330 = ").eInverseAdd(this, EOPPOSITE_FEATURE_BASE - ";
  protected final String TEXT_331 = ", null, msgs);";
  protected final String TEXT_332 = NL + "\t\t\t\tmsgs = ((";
  protected final String TEXT_333 = ")";
  protected final String TEXT_334 = ").eInverseRemove(this, ";
  protected final String TEXT_335 = ", ";
  protected final String TEXT_336 = ".class, msgs);" + NL + "\t\t\tif (new";
  protected final String TEXT_337 = " != null)" + NL + "\t\t\t\tmsgs = ((";
  protected final String TEXT_338 = ")new";
  protected final String TEXT_339 = ").eInverseAdd(this, ";
  protected final String TEXT_340 = ", ";
  protected final String TEXT_341 = ".class, msgs);";
  protected final String TEXT_342 = NL + "\t\t\tmsgs = basicSet";
  protected final String TEXT_343 = "(";
  protected final String TEXT_344 = "new";
  protected final String TEXT_345 = ", msgs);" + NL + "\t\t\tif (msgs != null) msgs.dispatch();" + NL + "\t\t}";
  protected final String TEXT_346 = NL + "\t\telse" + NL + "    \t{" + NL + "\t\t\tboolean old";
  protected final String TEXT_347 = "ESet = ";
  protected final String TEXT_348 = "ESet;" + NL + "\t\t\t";
  protected final String TEXT_349 = "ESet = true;" + NL + "\t\t\tif (eNotificationRequired())" + NL + "\t\t\t\teNotify(new ";
  protected final String TEXT_350 = "(this, ";
  protected final String TEXT_351 = ".SET, ";
  protected final String TEXT_352 = ", new";
  protected final String TEXT_353 = ", new";
  protected final String TEXT_354 = ", !old";
  protected final String TEXT_355 = "ESet));" + NL + "    \t}";
  protected final String TEXT_356 = NL + "\t\telse if (eNotificationRequired())" + NL + "\t\t\teNotify(new ";
  protected final String TEXT_357 = "(this, ";
  protected final String TEXT_358 = ".SET, ";
  protected final String TEXT_359 = ", new";
  protected final String TEXT_360 = ", new";
  protected final String TEXT_361 = "));";
  protected final String TEXT_362 = NL + "\t\t";
  protected final String TEXT_363 = " old";
  protected final String TEXT_364 = " = ";
  protected final String TEXT_365 = ";";
  protected final String TEXT_366 = NL + "\t\t";
  protected final String TEXT_367 = " = new";
  protected final String TEXT_368 = " == null ? ";
  protected final String TEXT_369 = "_EDEFAULT : new";
  protected final String TEXT_370 = ";";
  protected final String TEXT_371 = NL + "\t\t";
  protected final String TEXT_372 = " = ";
  protected final String TEXT_373 = "new";
  protected final String TEXT_374 = ";";
  protected final String TEXT_375 = NL + "\t\tboolean old";
  protected final String TEXT_376 = "ESet = ";
  protected final String TEXT_377 = "ESet;" + NL + "\t\t";
  protected final String TEXT_378 = "ESet = true;" + NL + "\t\tif (eNotificationRequired())" + NL + "\t\t\teNotify(new ";
  protected final String TEXT_379 = "(this, ";
  protected final String TEXT_380 = ".SET, ";
  protected final String TEXT_381 = ", old";
  protected final String TEXT_382 = ", ";
  protected final String TEXT_383 = ", !old";
  protected final String TEXT_384 = "ESet));";
  protected final String TEXT_385 = NL + "\t\tif (eNotificationRequired())" + NL + "\t\t\teNotify(new ";
  protected final String TEXT_386 = "(this, ";
  protected final String TEXT_387 = ".SET, ";
  protected final String TEXT_388 = ", old";
  protected final String TEXT_389 = ", ";
  protected final String TEXT_390 = "));";
  protected final String TEXT_391 = NL + "\t\t((";
  protected final String TEXT_392 = ".Internal)((";
  protected final String TEXT_393 = ")get";
  protected final String TEXT_394 = "()).featureMap()).set(";
  protected final String TEXT_395 = "(), ";
  protected final String TEXT_396 = "new ";
  protected final String TEXT_397 = "(";
  protected final String TEXT_398 = "new";
  protected final String TEXT_399 = ")";
  protected final String TEXT_400 = ");";
  protected final String TEXT_401 = NL + "\t\t((";
  protected final String TEXT_402 = ".Internal)get";
  protected final String TEXT_403 = "()).set(";
  protected final String TEXT_404 = "(), ";
  protected final String TEXT_405 = "new ";
  protected final String TEXT_406 = "(";
  protected final String TEXT_407 = "new";
  protected final String TEXT_408 = ")";
  protected final String TEXT_409 = ");";
  protected final String TEXT_410 = NL + "\t\t// TODO: implement this method to set the '";
  protected final String TEXT_411 = "' ";
  protected final String TEXT_412 = NL + "\t\t// Ensure that you remove @generated or mark it @generated NOT" + NL + "\t\tthrow new UnsupportedOperationException();";
  protected final String TEXT_413 = NL + "\t}" + NL;
  protected final String TEXT_414 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */" + NL + "\tpublic ";
  protected final String TEXT_415 = " basicUnset";
  protected final String TEXT_416 = "(";
  protected final String TEXT_417 = " msgs)" + NL + "\t{";
  protected final String TEXT_418 = NL + "\t\t";
  protected final String TEXT_419 = " old";
  protected final String TEXT_420 = " = ";
  protected final String TEXT_421 = ";" + NL + "\t\t";
  protected final String TEXT_422 = " = null;" + NL + "\t\tboolean old";
  protected final String TEXT_423 = "ESet = ";
  protected final String TEXT_424 = "ESet;" + NL + "\t\t";
  protected final String TEXT_425 = "ESet = false;" + NL + "\t\tif (eNotificationRequired())" + NL + "\t\t{" + NL + "\t\t\t";
  protected final String TEXT_426 = " notification = new ";
  protected final String TEXT_427 = "(this, ";
  protected final String TEXT_428 = ".UNSET, ";
  protected final String TEXT_429 = ", old";
  protected final String TEXT_430 = ", null, old";
  protected final String TEXT_431 = "ESet);" + NL + "\t\t\tif (msgs == null) msgs = notification; else msgs.add(notification);" + NL + "\t\t}" + NL + "\t\treturn msgs;";
  protected final String TEXT_432 = NL + "\t\t// TODO: implement this method to unset the contained '";
  protected final String TEXT_433 = "' ";
  protected final String TEXT_434 = NL + "\t\t// -> this method is automatically invoked to keep the containment relationship in synch" + NL + "\t\t// -> do not modify other features" + NL + "\t\t// -> return msgs, after adding any generated Notification to it (if it is null, a NotificationChain object must be created first)" + NL + "\t\t// Ensure that you remove @generated or mark it @generated NOT" + NL + "\t\tthrow new UnsupportedOperationException();";
  protected final String TEXT_435 = NL + "\t}" + NL;
  protected final String TEXT_436 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */" + NL + "\tpublic void unset";
  protected final String TEXT_437 = "()" + NL + "\t{";
  protected final String TEXT_438 = NL + "\t\teUnset(";
  protected final String TEXT_439 = ".eINSTANCE.get";
  protected final String TEXT_440 = "());";
  protected final String TEXT_441 = NL + "\t\t((";
  protected final String TEXT_442 = ".Unsettable)get";
  protected final String TEXT_443 = "()).unset();";
  protected final String TEXT_444 = NL + "\t\tif (";
  protected final String TEXT_445 = " != null)" + NL + "\t\t{" + NL + "\t\t\t";
  protected final String TEXT_446 = " msgs = null;";
  protected final String TEXT_447 = NL + "\t\t\tmsgs = ((";
  protected final String TEXT_448 = ")";
  protected final String TEXT_449 = ").eInverseRemove(this, EOPPOSITE_FEATURE_BASE - ";
  protected final String TEXT_450 = ", null, msgs);";
  protected final String TEXT_451 = NL + "\t\t\tmsgs = ((";
  protected final String TEXT_452 = ")";
  protected final String TEXT_453 = ").eInverseRemove(this, ";
  protected final String TEXT_454 = ", ";
  protected final String TEXT_455 = ".class, msgs);";
  protected final String TEXT_456 = NL + "\t\t\tmsgs = basicUnset";
  protected final String TEXT_457 = "(msgs);" + NL + "\t\t\tif (msgs != null) msgs.dispatch();" + NL + "\t\t}" + NL + "\t\telse" + NL + "    \t{" + NL + "\t\t\tboolean old";
  protected final String TEXT_458 = "ESet = ";
  protected final String TEXT_459 = "ESet;" + NL + "\t\t\t";
  protected final String TEXT_460 = "ESet = false;" + NL + "\t\t\tif (eNotificationRequired())" + NL + "\t\t\t\teNotify(new ";
  protected final String TEXT_461 = "(this, ";
  protected final String TEXT_462 = ".UNSET, ";
  protected final String TEXT_463 = ", null, null, old";
  protected final String TEXT_464 = "ESet));" + NL + "    \t}";
  protected final String TEXT_465 = NL + "\t\t";
  protected final String TEXT_466 = " old";
  protected final String TEXT_467 = " = ";
  protected final String TEXT_468 = ";" + NL + "\t\tboolean old";
  protected final String TEXT_469 = "ESet = ";
  protected final String TEXT_470 = "ESet;";
  protected final String TEXT_471 = NL + "\t\t";
  protected final String TEXT_472 = " = null;" + NL + "\t\t";
  protected final String TEXT_473 = "ESet = false;" + NL + "\t\tif (eNotificationRequired())" + NL + "\t\t\teNotify(new ";
  protected final String TEXT_474 = "(this, ";
  protected final String TEXT_475 = ".UNSET, ";
  protected final String TEXT_476 = ", old";
  protected final String TEXT_477 = ", null, old";
  protected final String TEXT_478 = "ESet));";
  protected final String TEXT_479 = NL + "\t\t";
  protected final String TEXT_480 = " = ";
  protected final String TEXT_481 = "_EDEFAULT;" + NL + "\t\t";
  protected final String TEXT_482 = "ESet = false;" + NL + "\t\tif (eNotificationRequired())" + NL + "\t\t\teNotify(new ";
  protected final String TEXT_483 = "(this, ";
  protected final String TEXT_484 = ".UNSET, ";
  protected final String TEXT_485 = ", old";
  protected final String TEXT_486 = ", ";
  protected final String TEXT_487 = "_EDEFAULT, old";
  protected final String TEXT_488 = "ESet));";
  protected final String TEXT_489 = NL + "\t\t((";
  protected final String TEXT_490 = ".Internal)((";
  protected final String TEXT_491 = ")get";
  protected final String TEXT_492 = "()).featureMap()).clear(";
  protected final String TEXT_493 = "());";
  protected final String TEXT_494 = NL + "\t\t((";
  protected final String TEXT_495 = ".Internal)get";
  protected final String TEXT_496 = "()).clear(";
  protected final String TEXT_497 = "());";
  protected final String TEXT_498 = NL + "\t\t// TODO: implement this method to unset the '";
  protected final String TEXT_499 = "' ";
  protected final String TEXT_500 = NL + "\t\t// Ensure that you remove @generated or mark it @generated NOT" + NL + "\t\tthrow new UnsupportedOperationException();";
  protected final String TEXT_501 = NL + "\t}" + NL;
  protected final String TEXT_502 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */" + NL + "\tpublic boolean isSet";
  protected final String TEXT_503 = "()" + NL + "\t{";
  protected final String TEXT_504 = NL + "\t\treturn eIsSet(";
  protected final String TEXT_505 = ".eINSTANCE.get";
  protected final String TEXT_506 = "());";
  protected final String TEXT_507 = NL + "\t\treturn ";
  protected final String TEXT_508 = " != null && ((";
  protected final String TEXT_509 = ".Unsettable)";
  protected final String TEXT_510 = ").isSet();";
  protected final String TEXT_511 = NL + "\t\treturn ";
  protected final String TEXT_512 = "ESet;";
  protected final String TEXT_513 = NL + "\t\treturn !((";
  protected final String TEXT_514 = ".Internal)((";
  protected final String TEXT_515 = ")get";
  protected final String TEXT_516 = "()).featureMap()).isEmpty(";
  protected final String TEXT_517 = "());";
  protected final String TEXT_518 = NL + "\t\treturn !((";
  protected final String TEXT_519 = ".Internal)get";
  protected final String TEXT_520 = "()).isEmpty(";
  protected final String TEXT_521 = "());";
  protected final String TEXT_522 = NL + "\t\t// TODO: implement this method to return whether the '";
  protected final String TEXT_523 = "' ";
  protected final String TEXT_524 = " is set" + NL + "\t\t// Ensure that you remove @generated or mark it @generated NOT" + NL + "\t\tthrow new UnsupportedOperationException();";
  protected final String TEXT_525 = NL + "\t}" + NL;
  protected final String TEXT_526 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */" + NL + "\tpublic ";
  protected final String TEXT_527 = " ";
  protected final String TEXT_528 = "(";
  protected final String TEXT_529 = ")";
  protected final String TEXT_530 = NL + "\t{";
  protected final String TEXT_531 = NL + "\t\t";
  protected final String TEXT_532 = NL + "\t\t// TODO: implement this method" + NL + "\t\t// Ensure that you remove @generated or mark it @generated NOT" + NL + "\t\treturn true;";
  protected final String TEXT_533 = NL + "\t\t// TODO: implement this method" + NL + "\t\t// Ensure that you remove @generated or mark it @generated NOT" + NL + "\t\tthrow new UnsupportedOperationException();";
  protected final String TEXT_534 = NL + "\t}" + NL;
  protected final String TEXT_535 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */" + NL + "\tpublic ";
  protected final String TEXT_536 = " eInverseAdd(";
  protected final String TEXT_537 = " otherEnd, int featureID, Class baseClass, ";
  protected final String TEXT_538 = " msgs)" + NL + "\t{" + NL + "\t\tif (featureID >= 0)" + NL + "\t\t{" + NL + "\t\t\tswitch (eDerivedStructuralFeatureID(featureID, baseClass))" + NL + "\t\t\t{";
  protected final String TEXT_539 = NL + "\t\t\t\tcase ";
  protected final String TEXT_540 = ":";
  protected final String TEXT_541 = NL + "\t\t\t\t\treturn ((";
  protected final String TEXT_542 = ")((";
  protected final String TEXT_543 = ")";
  protected final String TEXT_544 = "()).eMap()).basicAdd(otherEnd, msgs);";
  protected final String TEXT_545 = NL + "\t\t\t\t\treturn ((";
  protected final String TEXT_546 = ")";
  protected final String TEXT_547 = "()).basicAdd(otherEnd, msgs);";
  protected final String TEXT_548 = NL + "\t\t\t\t\tif (eContainer != null)" + NL + "\t\t\t\t\t\tmsgs = eBasicRemoveFromContainer(msgs);" + NL + "\t\t\t\t\treturn eBasicSetContainer(otherEnd, ";
  protected final String TEXT_549 = ", msgs);";
  protected final String TEXT_550 = NL + "\t\t\t\t\tif (";
  protected final String TEXT_551 = " != null)";
  protected final String TEXT_552 = NL + "\t\t\t\t\t\tmsgs = ((";
  protected final String TEXT_553 = ")";
  protected final String TEXT_554 = ").eInverseRemove(this, EOPPOSITE_FEATURE_BASE - ";
  protected final String TEXT_555 = ", null, msgs);";
  protected final String TEXT_556 = NL + "\t\t\t\t\t\tmsgs = ((";
  protected final String TEXT_557 = ")";
  protected final String TEXT_558 = ").eInverseRemove(this, ";
  protected final String TEXT_559 = ", ";
  protected final String TEXT_560 = ".class, msgs);";
  protected final String TEXT_561 = NL + "\t\t\t\t\treturn basicSet";
  protected final String TEXT_562 = "((";
  protected final String TEXT_563 = ")otherEnd, msgs);";
  protected final String TEXT_564 = NL + "\t\t\t\tdefault:" + NL + "\t\t\t\t\treturn eDynamicInverseAdd(otherEnd, featureID, baseClass, msgs);" + NL + "\t\t\t}" + NL + "\t\t}" + NL + "\t\tif (eContainer != null)" + NL + "\t\t\tmsgs = eBasicRemoveFromContainer(msgs);" + NL + "\t\treturn eBasicSetContainer(otherEnd, featureID, msgs);" + NL + "\t}" + NL;
  protected final String TEXT_565 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */" + NL + "\tpublic ";
  protected final String TEXT_566 = " eInverseRemove(";
  protected final String TEXT_567 = " otherEnd, int featureID, Class baseClass, ";
  protected final String TEXT_568 = " msgs)" + NL + "\t{" + NL + "\t\tif (featureID >= 0)" + NL + "\t\t{" + NL + "\t\t\tswitch (eDerivedStructuralFeatureID(featureID, baseClass))" + NL + "\t\t\t{";
  protected final String TEXT_569 = NL + "\t\t\t\tcase ";
  protected final String TEXT_570 = ":";
  protected final String TEXT_571 = NL + "\t\t\t\t\treturn ((";
  protected final String TEXT_572 = ")((";
  protected final String TEXT_573 = ")";
  protected final String TEXT_574 = "()).eMap()).basicRemove(otherEnd, msgs);";
  protected final String TEXT_575 = NL + "\t\t\t\treturn ((";
  protected final String TEXT_576 = ")((";
  protected final String TEXT_577 = ")";
  protected final String TEXT_578 = "()).featureMap()).basicRemove(otherEnd, msgs);";
  protected final String TEXT_579 = NL + "\t\t\t\t\treturn ((";
  protected final String TEXT_580 = ")";
  protected final String TEXT_581 = "()).basicRemove(otherEnd, msgs);";
  protected final String TEXT_582 = NL + "\t\t\t\t\treturn eBasicSetContainer(null, ";
  protected final String TEXT_583 = ", msgs);";
  protected final String TEXT_584 = NL + "\t\t\t\t\treturn basicUnset";
  protected final String TEXT_585 = "(msgs);";
  protected final String TEXT_586 = NL + "\t\t\t\t\treturn basicSet";
  protected final String TEXT_587 = "(null, msgs);";
  protected final String TEXT_588 = NL + "\t\t\t\tdefault:" + NL + "\t\t\t\t\treturn eDynamicInverseRemove(otherEnd, featureID, baseClass, msgs);" + NL + "\t\t\t}" + NL + "\t\t}" + NL + "\t\treturn eBasicSetContainer(null, featureID, msgs);" + NL + "\t}" + NL;
  protected final String TEXT_589 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */" + NL + "\tpublic ";
  protected final String TEXT_590 = " eBasicRemoveFromContainer(";
  protected final String TEXT_591 = " msgs)" + NL + "\t{" + NL + "\t\tif (eContainerFeatureID >= 0)" + NL + "\t\t{" + NL + "\t\t\tswitch (eContainerFeatureID)" + NL + "\t\t\t{";
  protected final String TEXT_592 = NL + "\t\t\t\tcase ";
  protected final String TEXT_593 = ":" + NL + "\t\t\t\t\treturn ((";
  protected final String TEXT_594 = ")eContainer).eInverseRemove(this, ";
  protected final String TEXT_595 = ", ";
  protected final String TEXT_596 = ".class, msgs);";
  protected final String TEXT_597 = NL + "\t\t\t\tdefault:" + NL + "\t\t\t\t\treturn eDynamicBasicRemoveFromContainer(msgs);" + NL + "\t\t\t}" + NL + "\t\t}" + NL + "\t\treturn ((";
  protected final String TEXT_598 = ")eContainer).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - eContainerFeatureID, null, msgs);" + NL + "\t}" + NL;
  protected final String TEXT_599 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */" + NL + "\tpublic Object eGet(";
  protected final String TEXT_600 = " eFeature, boolean resolve)" + NL + "\t{" + NL + "\t\tswitch (eDerivedStructuralFeatureID(eFeature))" + NL + "\t\t{";
  protected final String TEXT_601 = NL + "\t\t\tcase ";
  protected final String TEXT_602 = ":";
  protected final String TEXT_603 = NL + "\t\t\t\treturn ";
  protected final String TEXT_604 = "() ? Boolean.TRUE : Boolean.FALSE;";
  protected final String TEXT_605 = NL + "\t\t\t\treturn new ";
  protected final String TEXT_606 = "(";
  protected final String TEXT_607 = "());";
  protected final String TEXT_608 = NL + "\t\t\t\tif (resolve) return ";
  protected final String TEXT_609 = "();" + NL + "\t\t\t\treturn basicGet";
  protected final String TEXT_610 = "();";
  protected final String TEXT_611 = NL + "\t\t\t\treturn ((";
  protected final String TEXT_612 = ")";
  protected final String TEXT_613 = "()).eMap();";
  protected final String TEXT_614 = NL + "\t\t\t\treturn ((";
  protected final String TEXT_615 = ")";
  protected final String TEXT_616 = "()).featureMap();";
  protected final String TEXT_617 = NL + "\t\t\t\treturn ";
  protected final String TEXT_618 = "();";
  protected final String TEXT_619 = NL + "\t\t}" + NL + "\t\treturn eDynamicGet(eFeature, resolve);" + NL + "\t}" + NL;
  protected final String TEXT_620 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */" + NL + "\tpublic void eSet(";
  protected final String TEXT_621 = " eFeature, Object newValue)" + NL + "\t{" + NL + "\t\tswitch (eDerivedStructuralFeatureID(eFeature))" + NL + "\t\t{";
  protected final String TEXT_622 = NL + "\t\t\tcase ";
  protected final String TEXT_623 = ":";
  protected final String TEXT_624 = NL + "\t\t\t\t((";
  protected final String TEXT_625 = ")";
  protected final String TEXT_626 = "()).featureMap().clear();";
  protected final String TEXT_627 = NL + "\t\t\t\t";
  protected final String TEXT_628 = "().clear();";
  protected final String TEXT_629 = NL + "\t\t\t\t((";
  protected final String TEXT_630 = ")";
  protected final String TEXT_631 = "()).featureMap().addAll((";
  protected final String TEXT_632 = ")newValue);";
  protected final String TEXT_633 = NL + "\t\t\t\t((";
  protected final String TEXT_634 = ")";
  protected final String TEXT_635 = "()).eMap().addAll((";
  protected final String TEXT_636 = ")newValue);";
  protected final String TEXT_637 = NL + "\t\t\t\t";
  protected final String TEXT_638 = "().addAll((";
  protected final String TEXT_639 = ")newValue);";
  protected final String TEXT_640 = NL + "\t\t\t\tset";
  protected final String TEXT_641 = "(((";
  protected final String TEXT_642 = ")newValue).";
  protected final String TEXT_643 = "());";
  protected final String TEXT_644 = NL + "\t\t\t\tset";
  protected final String TEXT_645 = "((";
  protected final String TEXT_646 = ")newValue);";
  protected final String TEXT_647 = NL + "\t\t\t\treturn;";
  protected final String TEXT_648 = NL + "\t\t}" + NL + "\t\teDynamicSet(eFeature, newValue);" + NL + "\t}" + NL + "" + NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */" + NL + "\tpublic void eUnset(";
  protected final String TEXT_649 = " eFeature)" + NL + "\t{" + NL + "\t\tswitch (eDerivedStructuralFeatureID(eFeature))" + NL + "\t\t{";
  protected final String TEXT_650 = NL + "\t\t\tcase ";
  protected final String TEXT_651 = ":";
  protected final String TEXT_652 = NL + "\t\t\t\t((";
  protected final String TEXT_653 = ")";
  protected final String TEXT_654 = "()).featureMap().clear();";
  protected final String TEXT_655 = NL + "\t\t\t\t";
  protected final String TEXT_656 = "().clear();";
  protected final String TEXT_657 = NL + "\t\t\t\tunset";
  protected final String TEXT_658 = "();";
  protected final String TEXT_659 = NL + "\t\t\t\tset";
  protected final String TEXT_660 = "((";
  protected final String TEXT_661 = ")null);";
  protected final String TEXT_662 = NL + "\t\t\t\tset";
  protected final String TEXT_663 = "(";
  protected final String TEXT_664 = "_EDEFAULT);";
  protected final String TEXT_665 = NL + "\t\t\t\treturn;";
  protected final String TEXT_666 = NL + "\t\t}" + NL + "\t\teDynamicUnset(eFeature);" + NL + "\t}" + NL;
  protected final String TEXT_667 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */" + NL + "\tpublic boolean eIsSet(";
  protected final String TEXT_668 = " eFeature)" + NL + "\t{" + NL + "\t\tswitch (eDerivedStructuralFeatureID(eFeature))" + NL + "\t\t{";
  protected final String TEXT_669 = NL + "\t\t\tcase ";
  protected final String TEXT_670 = ":";
  protected final String TEXT_671 = NL + "\t\t\t\treturn !((";
  protected final String TEXT_672 = ")";
  protected final String TEXT_673 = "()).featureMap().isEmpty();";
  protected final String TEXT_674 = NL + "\t\t\t\treturn ";
  protected final String TEXT_675 = " != null && !";
  protected final String TEXT_676 = ".featureMap().isEmpty();";
  protected final String TEXT_677 = NL + "\t\t\t\treturn !";
  protected final String TEXT_678 = "().isEmpty();";
  protected final String TEXT_679 = NL + "\t\t\t\treturn ";
  protected final String TEXT_680 = " != null && !";
  protected final String TEXT_681 = ".isEmpty();";
  protected final String TEXT_682 = NL + "\t\t\t\treturn isSet";
  protected final String TEXT_683 = "();";
  protected final String TEXT_684 = NL + "\t\t\t\treturn basicGet";
  protected final String TEXT_685 = "() != null;";
  protected final String TEXT_686 = NL + "\t\t\t\treturn ";
  protected final String TEXT_687 = " != null;";
  protected final String TEXT_688 = NL + "\t\t\t\treturn ";
  protected final String TEXT_689 = "() != null;";
  protected final String TEXT_690 = NL + "\t\t\t\treturn ";
  protected final String TEXT_691 = " != null;";
  protected final String TEXT_692 = NL + "\t\t\t\treturn ";
  protected final String TEXT_693 = "() != ";
  protected final String TEXT_694 = "_EDEFAULT;";
  protected final String TEXT_695 = NL + "\t\t\t\treturn ";
  protected final String TEXT_696 = " != ";
  protected final String TEXT_697 = "_EDEFAULT;";
  protected final String TEXT_698 = NL + "\t\t\t\treturn ";
  protected final String TEXT_699 = "_EDEFAULT == null ? ";
  protected final String TEXT_700 = "() != null : !";
  protected final String TEXT_701 = "_EDEFAULT.equals(";
  protected final String TEXT_702 = "());";
  protected final String TEXT_703 = NL + "\t\t\t\treturn ";
  protected final String TEXT_704 = "_EDEFAULT == null ? ";
  protected final String TEXT_705 = " != null : !";
  protected final String TEXT_706 = "_EDEFAULT.equals(";
  protected final String TEXT_707 = ");";
  protected final String TEXT_708 = NL + "\t\t}" + NL + "\t\treturn eDynamicIsSet(eFeature);" + NL + "\t}" + NL;
  protected final String TEXT_709 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */" + NL + "\tpublic int eBaseStructuralFeatureID(int derivedFeatureID, Class baseClass)" + NL + "\t{";
  protected final String TEXT_710 = NL + "\t\tif (baseClass == ";
  protected final String TEXT_711 = ".class)" + NL + "\t\t{" + NL + "\t\t\tswitch (derivedFeatureID)" + NL + "\t\t\t{";
  protected final String TEXT_712 = NL + "\t\t\t\tcase ";
  protected final String TEXT_713 = ": return ";
  protected final String TEXT_714 = ";";
  protected final String TEXT_715 = NL + "\t\t\t\tdefault: return -1;" + NL + "\t\t\t}" + NL + "\t\t}";
  protected final String TEXT_716 = NL + "\t\treturn super.eBaseStructuralFeatureID(derivedFeatureID, baseClass);" + NL + "\t}" + NL + "" + NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */" + NL + "\tpublic int eDerivedStructuralFeatureID(int baseFeatureID, Class baseClass)" + NL + "\t{";
  protected final String TEXT_717 = NL + "\t\tif (baseClass == ";
  protected final String TEXT_718 = ".class)" + NL + "\t\t{" + NL + "\t\t\tswitch (baseFeatureID)" + NL + "\t\t\t{";
  protected final String TEXT_719 = NL + "\t\t\t\tcase ";
  protected final String TEXT_720 = ": return ";
  protected final String TEXT_721 = ";";
  protected final String TEXT_722 = NL + "\t\t\t\tdefault: return -1;" + NL + "\t\t\t}" + NL + "\t\t}";
  protected final String TEXT_723 = NL + "\t\treturn super.eDerivedStructuralFeatureID(baseFeatureID, baseClass);" + NL + "\t}" + NL;
  protected final String TEXT_724 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */" + NL + "\tpublic String toString()" + NL + "\t{" + NL + "\t\tif (eIsProxy()) return super.toString();" + NL + "" + NL + "\t\tStringBuffer result = new StringBuffer(super.toString());";
  protected final String TEXT_725 = NL + "\t\tresult.append(\" (";
  protected final String TEXT_726 = ": \");";
  protected final String TEXT_727 = NL + "\t\tresult.append(\", ";
  protected final String TEXT_728 = ": \");";
  protected final String TEXT_729 = NL + "\t\tif (";
  protected final String TEXT_730 = "ESet) result.append(";
  protected final String TEXT_731 = "); else result.append(\"<unset>\");";
  protected final String TEXT_732 = NL + "\t\tresult.append(";
  protected final String TEXT_733 = ");";
  protected final String TEXT_734 = NL + "\t\tresult.append(')');" + NL + "\t\treturn result.toString();" + NL + "\t}" + NL;
  protected final String TEXT_735 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */" + NL + "\tprotected int hash = -1;" + NL + "" + NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */" + NL + "\tpublic int getHash()" + NL + "\t{" + NL + "\t\tif (hash == -1)" + NL + "\t\t{" + NL + "\t\t\tObject theKey = getKey();" + NL + "\t\t\thash = (theKey == null ? 0 : theKey.hashCode());" + NL + "\t\t}" + NL + "\t\treturn hash;" + NL + "\t}" + NL + "" + NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */" + NL + "\tpublic void setHash(int hash)" + NL + "\t{" + NL + "\t\tthis.hash = hash;" + NL + "\t}" + NL + "" + NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */" + NL + "\tpublic Object getKey()" + NL + "\t{";
  protected final String TEXT_736 = NL + "\t\treturn new ";
  protected final String TEXT_737 = "(getTypedKey());";
  protected final String TEXT_738 = NL + "\t\treturn getTypedKey();";
  protected final String TEXT_739 = NL + "\t}" + NL + "" + NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */" + NL + "\tpublic void setKey(Object key)" + NL + "\t{";
  protected final String TEXT_740 = NL + "\t\tgetTypedKey().addAll((";
  protected final String TEXT_741 = ")key);";
  protected final String TEXT_742 = NL + "\t\tsetTypedKey(((";
  protected final String TEXT_743 = ")key).";
  protected final String TEXT_744 = "());";
  protected final String TEXT_745 = NL + "\t\tsetTypedKey((";
  protected final String TEXT_746 = ")key);";
  protected final String TEXT_747 = NL + "\t}" + NL + "" + NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */" + NL + "\tpublic Object getValue()" + NL + "\t{";
  protected final String TEXT_748 = NL + "\t\treturn new ";
  protected final String TEXT_749 = "(getTypedValue());";
  protected final String TEXT_750 = NL + "\t\treturn getTypedValue();";
  protected final String TEXT_751 = NL + "\t}" + NL + "" + NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */" + NL + "\tpublic Object setValue(Object value)" + NL + "\t{" + NL + "\t\tObject oldValue = getValue();";
  protected final String TEXT_752 = NL + "\t\tgetTypedValue().clear();" + NL + "\t\tgetTypedValue().addAll((";
  protected final String TEXT_753 = ")value);";
  protected final String TEXT_754 = NL + "\t\tsetTypedValue(((";
  protected final String TEXT_755 = ")value).";
  protected final String TEXT_756 = "());";
  protected final String TEXT_757 = NL + "\t\tsetTypedValue((";
  protected final String TEXT_758 = ")value);";
  protected final String TEXT_759 = NL + "\t\treturn oldValue;" + NL + "\t}" + NL + "" + NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */" + NL + "\tpublic ";
  protected final String TEXT_760 = " getEMap()" + NL + "\t{" + NL + "\t\t";
  protected final String TEXT_761 = " container = eContainer();" + NL + "\t\treturn container == null ? null : (";
  protected final String TEXT_762 = ")container.eGet(eContainmentFeature());" + NL + "\t}" + NL;
  protected final String TEXT_763 = NL + "} //";
  protected final String TEXT_764 = NL;

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
    if (!genModel.isReflectiveDelegation()) {
    for (Iterator i=genClass.getImplementedGenFeatures().iterator(); i.hasNext();) { GenFeature genFeature = (GenFeature)i.next();
    if (!genFeature.isContainer()) {
    if (genFeature.isListType() || genFeature.isReferenceType()) {
    if (!genFeature.isVolatile()) {
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
    }
    } else {
    if (!genFeature.isVolatile() || !genModel.isReflectiveDelegation() && (!genFeature.hasDelegateFeature() || !genFeature.isUnsettable())) {
    stringBuffer.append(TEXT_33);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_34);
    stringBuffer.append(genFeature.getFormattedName());
    stringBuffer.append(TEXT_35);
    stringBuffer.append(genFeature.getFeatureKind());
    stringBuffer.append(TEXT_36);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_37);
    stringBuffer.append(genFeature.getImportedType());
    stringBuffer.append(TEXT_38);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_39);
    stringBuffer.append(genFeature.getStaticDefaultValue());
    stringBuffer.append(TEXT_40);
    stringBuffer.append(genModel.getNonNLS(genFeature.getStaticDefaultValue()));
    stringBuffer.append(TEXT_41);
    }
    if (!genFeature.isVolatile()) {
    stringBuffer.append(TEXT_42);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_43);
    stringBuffer.append(genFeature.getFormattedName());
    stringBuffer.append(TEXT_44);
    stringBuffer.append(genFeature.getFeatureKind());
    stringBuffer.append(TEXT_45);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_46);
    stringBuffer.append(genFeature.getImportedType());
    stringBuffer.append(TEXT_47);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_48);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_49);
    if (!genFeature.isListType() && genFeature.isUnsettable() && genFeature.isChangeable()) {
    stringBuffer.append(TEXT_50);
    stringBuffer.append(genFeature.getFormattedName());
    stringBuffer.append(TEXT_51);
    stringBuffer.append(genFeature.getFeatureKind());
    stringBuffer.append(TEXT_52);
    stringBuffer.append(genFeature.getUncapName());
    stringBuffer.append(TEXT_53);
    }
    }
    }
    }
    }
    }
    stringBuffer.append(TEXT_54);
    stringBuffer.append(genClass.getClassName());
    stringBuffer.append(TEXT_55);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.EClass"));
    stringBuffer.append(TEXT_56);
    stringBuffer.append(genPackage.getImportedPackageInterfaceName());
    stringBuffer.append(TEXT_57);
    stringBuffer.append(genClass.getClassifierAccessorName());
    stringBuffer.append(TEXT_58);
    for (Iterator i=genClass.getImplementedGenFeatures().iterator(); i.hasNext();) { GenFeature genFeature = (GenFeature)i.next();
    stringBuffer.append(TEXT_59);
    stringBuffer.append(genFeature.getImportedType());
    stringBuffer.append(TEXT_60);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_61);
    if (genModel.isReflectiveDelegation()) {
    stringBuffer.append(TEXT_62);
    if (genFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_63);
    }
    stringBuffer.append(TEXT_64);
    stringBuffer.append(genFeature.getObjectType());
    stringBuffer.append(TEXT_65);
    stringBuffer.append(genFeature.getGenPackage().getImportedPackageInterfaceName());
    stringBuffer.append(TEXT_66);
    stringBuffer.append(genFeature.getFeatureAccessorName());
    stringBuffer.append(TEXT_67);
    if (genFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_68);
    stringBuffer.append(genFeature.getPrimitiveValueFunction());
    stringBuffer.append(TEXT_69);
    }
    stringBuffer.append(TEXT_70);
    } else if (!genFeature.isVolatile()) {
    if (genFeature.isListType()) { String unsettable = genFeature.isUnsettable() ? ".Unsettable" : ""; 
    stringBuffer.append(TEXT_71);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_72);
    if (genFeature.isMapType()) { GenClass mapGenClass = genFeature.getMapGenClass(); GenPackage mapGenPackage = mapGenClass.getGenPackage(); 
    stringBuffer.append(TEXT_73);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_74);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.EcoreEMap"));
    stringBuffer.append(TEXT_75);
    stringBuffer.append(mapGenPackage.getImportedPackageInterfaceName());
    stringBuffer.append(TEXT_76);
    stringBuffer.append(mapGenClass.getName());
    stringBuffer.append(TEXT_77);
    stringBuffer.append(genFeature.getMapItemType());
    stringBuffer.append(TEXT_78);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(TEXT_79);
    } else if (genFeature.isContains()) {
    if (genFeature.isBidirectional()) { GenFeature reverseFeature = genFeature.getReverse();
    stringBuffer.append(TEXT_80);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_81);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.EObjectContainmentWithInverseEList"));
    stringBuffer.append(unsettable);
    stringBuffer.append(TEXT_82);
    stringBuffer.append(genFeature.getListItemType());
    stringBuffer.append(TEXT_83);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(TEXT_84);
    stringBuffer.append(reverseFeature.getGenClass().getQualifiedFeatureID(reverseFeature));
    stringBuffer.append(TEXT_85);
    } else {
    stringBuffer.append(TEXT_86);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_87);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.EObjectContainmentEList"));
    stringBuffer.append(unsettable);
    stringBuffer.append(TEXT_88);
    stringBuffer.append(genFeature.getListItemType());
    stringBuffer.append(TEXT_89);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(TEXT_90);
    }
    } else if (genFeature.isReferenceType()) {
    if (genFeature.isBidirectional()) { GenFeature reverseFeature = genFeature.getReverse();
    if (reverseFeature.isListType()) {
    if (genFeature.isResolveProxies()) {
    stringBuffer.append(TEXT_91);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_92);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.EObjectWithInverseResolvingEList"));
    stringBuffer.append(unsettable);
    stringBuffer.append(TEXT_93);
    stringBuffer.append(genFeature.getListItemType());
    stringBuffer.append(TEXT_94);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(TEXT_95);
    stringBuffer.append(reverseFeature.getGenClass().getQualifiedFeatureID(reverseFeature));
    stringBuffer.append(TEXT_96);
    } else {
    stringBuffer.append(TEXT_97);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_98);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.EObjectWithInverseEList"));
    stringBuffer.append(unsettable);
    stringBuffer.append(TEXT_99);
    stringBuffer.append(genFeature.getListItemType());
    stringBuffer.append(TEXT_100);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(TEXT_101);
    stringBuffer.append(reverseFeature.getGenClass().getQualifiedFeatureID(reverseFeature));
    stringBuffer.append(TEXT_102);
    }
    } else {
    if (genFeature.isResolveProxies()) {
    stringBuffer.append(TEXT_103);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_104);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.EObjectWithInverseResolvingEList"));
    stringBuffer.append(unsettable);
    stringBuffer.append(TEXT_105);
    stringBuffer.append(genFeature.getListItemType());
    stringBuffer.append(TEXT_106);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(TEXT_107);
    stringBuffer.append(reverseFeature.getGenClass().getQualifiedFeatureID(reverseFeature));
    stringBuffer.append(TEXT_108);
    } else {
    stringBuffer.append(TEXT_109);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_110);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.EObjectWithInverseEList"));
    stringBuffer.append(unsettable);
    stringBuffer.append(TEXT_111);
    stringBuffer.append(genFeature.getListItemType());
    stringBuffer.append(TEXT_112);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(TEXT_113);
    stringBuffer.append(reverseFeature.getGenClass().getQualifiedFeatureID(reverseFeature));
    stringBuffer.append(TEXT_114);
    }
    }
    } else {
    if (genFeature.isResolveProxies()) {
    stringBuffer.append(TEXT_115);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_116);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.EObjectResolvingEList"));
    stringBuffer.append(unsettable);
    stringBuffer.append(TEXT_117);
    stringBuffer.append(genFeature.getListItemType());
    stringBuffer.append(TEXT_118);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(TEXT_119);
    } else {
    stringBuffer.append(TEXT_120);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_121);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.EObjectEList"));
    stringBuffer.append(unsettable);
    stringBuffer.append(TEXT_122);
    stringBuffer.append(genFeature.getListItemType());
    stringBuffer.append(TEXT_123);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(TEXT_124);
    }
    }
    } else if (genFeature.isFeatureMapType()) {
    if (genFeature.isFeatureMapWrapped()) {
    stringBuffer.append(TEXT_125);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_126);
    stringBuffer.append(genFeature.getImportedEffectiveFeatureMapWrapperClass());
    stringBuffer.append(TEXT_127);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.BasicFeatureMap"));
    stringBuffer.append(TEXT_128);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(TEXT_129);
    } else {
    stringBuffer.append(TEXT_130);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_131);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.BasicFeatureMap"));
    stringBuffer.append(TEXT_132);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(TEXT_133);
    }
    } else {//datatype
    if (genFeature.isUnique()) {
    stringBuffer.append(TEXT_134);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_135);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.EDataTypeUniqueEList"));
    stringBuffer.append(unsettable);
    stringBuffer.append(TEXT_136);
    stringBuffer.append(genFeature.getListItemType());
    stringBuffer.append(TEXT_137);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(TEXT_138);
    } else {
    stringBuffer.append(TEXT_139);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_140);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.EDataTypeEList"));
    stringBuffer.append(unsettable);
    stringBuffer.append(TEXT_141);
    stringBuffer.append(genFeature.getListItemType());
    stringBuffer.append(TEXT_142);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(TEXT_143);
    }
    }
    stringBuffer.append(TEXT_144);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(genFeature.isMapType() && genFeature.isEffectiveSuppressEMFTypes() ? ".map()" : "");
    stringBuffer.append(TEXT_145);
    } else if (genFeature.isContainer()) {
    stringBuffer.append(TEXT_146);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(TEXT_147);
    stringBuffer.append(genFeature.getImportedType());
    stringBuffer.append(TEXT_148);
    } else {
    if (genFeature.isResolveProxies()) {
    stringBuffer.append(TEXT_149);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_150);
    stringBuffer.append(genFeature.getSafeNameAsEObject());
    stringBuffer.append(TEXT_151);
    stringBuffer.append(genFeature.getImportedType());
    stringBuffer.append(TEXT_152);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_153);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_154);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_155);
    stringBuffer.append(genFeature.getImportedInternalType());
    stringBuffer.append(TEXT_156);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.InternalEObject"));
    stringBuffer.append(TEXT_157);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_158);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_159);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_160);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.impl.ENotificationImpl"));
    stringBuffer.append(TEXT_161);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.notify.Notification"));
    stringBuffer.append(TEXT_162);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(TEXT_163);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_164);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_165);
    }
    stringBuffer.append(TEXT_166);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_167);
    }
    } else {//volatile
    if (genFeature.isResolveProxies() && !genFeature.isListType()) {
    stringBuffer.append(TEXT_168);
    stringBuffer.append(genFeature.getImportedType());
    stringBuffer.append(TEXT_169);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_170);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_171);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_172);
    stringBuffer.append(genFeature.getImportedInternalType());
    stringBuffer.append(TEXT_173);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.InternalEObject"));
    stringBuffer.append(TEXT_174);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_175);
    } else if (genFeature.hasDelegateFeature()) { GenFeature delegateFeature = genFeature.getDelegateFeature();
    if (genFeature.isFeatureMapType()) {
    if (delegateFeature.isFeatureMapWrapped()) {
    stringBuffer.append(TEXT_176);
    stringBuffer.append(genFeature.getImportedEffectiveFeatureMapWrapperClass());
    stringBuffer.append(TEXT_177);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.FeatureMap$Internal"));
    stringBuffer.append(TEXT_178);
    stringBuffer.append(delegateFeature.getImportedEffectiveFeatureMapWrapperInternalInterface());
    stringBuffer.append(TEXT_179);
    stringBuffer.append(delegateFeature.getAccessorName());
    stringBuffer.append(TEXT_180);
    stringBuffer.append(genFeature.getQualifiedFeatureAccessorName());
    stringBuffer.append(TEXT_181);
    } else {
    stringBuffer.append(TEXT_182);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.FeatureMap"));
    stringBuffer.append(TEXT_183);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.FeatureMap"));
    stringBuffer.append(TEXT_184);
    stringBuffer.append(delegateFeature.getAccessorName());
    stringBuffer.append(TEXT_185);
    stringBuffer.append(genFeature.getQualifiedFeatureAccessorName());
    stringBuffer.append(TEXT_186);
    }
    } else if (genFeature.isListType()) {
    if (delegateFeature.isFeatureMapWrapped()) {
    stringBuffer.append(TEXT_187);
    stringBuffer.append(delegateFeature.getImportedEffectiveFeatureMapWrapperInternalInterface());
    stringBuffer.append(TEXT_188);
    stringBuffer.append(delegateFeature.getAccessorName());
    stringBuffer.append(TEXT_189);
    stringBuffer.append(genFeature.getQualifiedFeatureAccessorName());
    stringBuffer.append(TEXT_190);
    } else {
    stringBuffer.append(TEXT_191);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.FeatureMap"));
    stringBuffer.append(TEXT_192);
    stringBuffer.append(delegateFeature.getAccessorName());
    stringBuffer.append(TEXT_193);
    stringBuffer.append(genFeature.getQualifiedFeatureAccessorName());
    stringBuffer.append(TEXT_194);
    }
    } else {
    if (delegateFeature.isFeatureMapWrapped()) {
    stringBuffer.append(TEXT_195);
    if (genFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_196);
    }
    stringBuffer.append(TEXT_197);
    stringBuffer.append(genFeature.getObjectType());
    stringBuffer.append(TEXT_198);
    stringBuffer.append(delegateFeature.getImportedEffectiveFeatureMapWrapperInternalInterface());
    stringBuffer.append(TEXT_199);
    stringBuffer.append(delegateFeature.getAccessorName());
    stringBuffer.append(TEXT_200);
    stringBuffer.append(genFeature.getQualifiedFeatureAccessorName());
    stringBuffer.append(TEXT_201);
    if (genFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_202);
    stringBuffer.append(genFeature.getPrimitiveValueFunction());
    stringBuffer.append(TEXT_203);
    }
    stringBuffer.append(TEXT_204);
    } else {
    stringBuffer.append(TEXT_205);
    if (genFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_206);
    }
    stringBuffer.append(TEXT_207);
    stringBuffer.append(genFeature.getObjectType());
    stringBuffer.append(TEXT_208);
    stringBuffer.append(delegateFeature.getAccessorName());
    stringBuffer.append(TEXT_209);
    stringBuffer.append(genFeature.getQualifiedFeatureAccessorName());
    stringBuffer.append(TEXT_210);
    if (genFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_211);
    stringBuffer.append(genFeature.getPrimitiveValueFunction());
    stringBuffer.append(TEXT_212);
    }
    stringBuffer.append(TEXT_213);
    }
    }
    } else {
    stringBuffer.append(TEXT_214);
    stringBuffer.append(genFeature.getFormattedName());
    stringBuffer.append(TEXT_215);
    stringBuffer.append(genFeature.getFeatureKind());
    stringBuffer.append(TEXT_216);
    }
    }
    stringBuffer.append(TEXT_217);
    if (!genModel.isReflectiveDelegation() && genFeature.isResolveProxies() && !genFeature.isListType()) {
    stringBuffer.append(TEXT_218);
    stringBuffer.append(genFeature.getImportedType());
    stringBuffer.append(TEXT_219);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_220);
    if (!genFeature.isVolatile()) {
    stringBuffer.append(TEXT_221);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_222);
    } else if (genFeature.hasDelegateFeature()) { GenFeature delegateFeature = genFeature.getDelegateFeature();
    if (delegateFeature.isFeatureMapWrapped()) {
    stringBuffer.append(TEXT_223);
    stringBuffer.append(genFeature.getImportedType());
    stringBuffer.append(TEXT_224);
    stringBuffer.append(delegateFeature.getImportedEffectiveFeatureMapWrapperInternalInterface());
    stringBuffer.append(TEXT_225);
    stringBuffer.append(delegateFeature.getAccessorName());
    stringBuffer.append(TEXT_226);
    stringBuffer.append(genFeature.getQualifiedFeatureAccessorName());
    stringBuffer.append(TEXT_227);
    } else {
    stringBuffer.append(TEXT_228);
    stringBuffer.append(genFeature.getImportedType());
    stringBuffer.append(TEXT_229);
    stringBuffer.append(delegateFeature.getAccessorName());
    stringBuffer.append(TEXT_230);
    stringBuffer.append(genFeature.getQualifiedFeatureAccessorName());
    stringBuffer.append(TEXT_231);
    }
    } else {
    stringBuffer.append(TEXT_232);
    stringBuffer.append(genFeature.getFormattedName());
    stringBuffer.append(TEXT_233);
    stringBuffer.append(genFeature.getFeatureKind());
    stringBuffer.append(TEXT_234);
    }
    stringBuffer.append(TEXT_235);
    }
    if (!genFeature.isListType()) {
    if (!genModel.isReflectiveDelegation() && (genFeature.isBidirectional() && !genFeature.isContainer() && !genFeature.isVolatile() || genFeature.isContains())) {
    stringBuffer.append(TEXT_236);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.notify.NotificationChain"));
    stringBuffer.append(TEXT_237);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_238);
    stringBuffer.append(genFeature.getImportedInternalType());
    stringBuffer.append(TEXT_239);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_240);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.notify.NotificationChain"));
    stringBuffer.append(TEXT_241);
    if (!genFeature.isVolatile()) {
    stringBuffer.append(TEXT_242);
    stringBuffer.append(genFeature.getImportedType());
    stringBuffer.append(TEXT_243);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_244);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_245);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_246);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_247);
    if (genFeature.isUnsettable()) {
    stringBuffer.append(TEXT_248);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_249);
    stringBuffer.append(genFeature.getUncapName());
    stringBuffer.append(TEXT_250);
    stringBuffer.append(genFeature.getUncapName());
    stringBuffer.append(TEXT_251);
    }
    stringBuffer.append(TEXT_252);
    if (genFeature.isUnsettable()) {
    stringBuffer.append(TEXT_253);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.impl.ENotificationImpl"));
    stringBuffer.append(TEXT_254);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.impl.ENotificationImpl"));
    stringBuffer.append(TEXT_255);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.notify.Notification"));
    stringBuffer.append(TEXT_256);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(TEXT_257);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_258);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_259);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_260);
    } else {
    stringBuffer.append(TEXT_261);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.impl.ENotificationImpl"));
    stringBuffer.append(TEXT_262);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.impl.ENotificationImpl"));
    stringBuffer.append(TEXT_263);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.notify.Notification"));
    stringBuffer.append(TEXT_264);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(TEXT_265);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_266);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_267);
    }
    stringBuffer.append(TEXT_268);
    } else if (genFeature.hasDelegateFeature()) { GenFeature delegateFeature = genFeature.getDelegateFeature();
    if (delegateFeature.isFeatureMapWrapped()) {
    stringBuffer.append(TEXT_269);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.FeatureMap"));
    stringBuffer.append(TEXT_270);
    stringBuffer.append(delegateFeature.getImportedEffectiveFeatureMapWrapperInternalInterface());
    stringBuffer.append(TEXT_271);
    stringBuffer.append(delegateFeature.getAccessorName());
    stringBuffer.append(TEXT_272);
    stringBuffer.append(genFeature.getQualifiedFeatureAccessorName());
    stringBuffer.append(TEXT_273);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_274);
    } else {
    stringBuffer.append(TEXT_275);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.FeatureMap"));
    stringBuffer.append(TEXT_276);
    stringBuffer.append(delegateFeature.getAccessorName());
    stringBuffer.append(TEXT_277);
    stringBuffer.append(genFeature.getQualifiedFeatureAccessorName());
    stringBuffer.append(TEXT_278);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_279);
    }
    } else {
    stringBuffer.append(TEXT_280);
    stringBuffer.append(genFeature.getFormattedName());
    stringBuffer.append(TEXT_281);
    stringBuffer.append(genFeature.getFeatureKind());
    stringBuffer.append(TEXT_282);
    }
    stringBuffer.append(TEXT_283);
    }
    if (genFeature.isChangeable()) {
    stringBuffer.append(TEXT_284);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_285);
    stringBuffer.append(genFeature.getImportedType());
    stringBuffer.append(TEXT_286);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_287);
    if (genModel.isReflectiveDelegation()) {
    stringBuffer.append(TEXT_288);
    stringBuffer.append(genFeature.getGenPackage().getImportedPackageInterfaceName());
    stringBuffer.append(TEXT_289);
    stringBuffer.append(genFeature.getFeatureAccessorName());
    stringBuffer.append(TEXT_290);
    if (genFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_291);
    stringBuffer.append(genFeature.getObjectType());
    stringBuffer.append(TEXT_292);
    }
    stringBuffer.append(TEXT_293);
    stringBuffer.append(genFeature.getCapName());
    if (genFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_294);
    }
    stringBuffer.append(TEXT_295);
    } else if (!genFeature.isVolatile()) {
    if (genFeature.isContainer()) { GenFeature reverseFeature = genFeature.getReverse(); GenClass targetClass = reverseFeature.getGenClass();
    stringBuffer.append(TEXT_296);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_297);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(TEXT_298);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_299);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.EcoreUtil"));
    stringBuffer.append(TEXT_300);
    stringBuffer.append(genFeature.getEObjectCast());
    stringBuffer.append(TEXT_301);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_302);
    stringBuffer.append(genModel.getImportedName("java.lang.IllegalArgumentException"));
    stringBuffer.append(TEXT_303);
    stringBuffer.append(genModel.getNonNLS());
    stringBuffer.append(TEXT_304);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.notify.NotificationChain"));
    stringBuffer.append(TEXT_305);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_306);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.InternalEObject"));
    stringBuffer.append(TEXT_307);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_308);
    stringBuffer.append(targetClass.getQualifiedFeatureID(reverseFeature));
    stringBuffer.append(TEXT_309);
    stringBuffer.append(targetClass.getImportedInterfaceName());
    stringBuffer.append(TEXT_310);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.InternalEObject"));
    stringBuffer.append(TEXT_311);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_312);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(TEXT_313);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.impl.ENotificationImpl"));
    stringBuffer.append(TEXT_314);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.notify.Notification"));
    stringBuffer.append(TEXT_315);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(TEXT_316);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_317);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_318);
    } else if (genFeature.isBidirectional() || genFeature.isContains()) {
    stringBuffer.append(TEXT_319);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_320);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_321);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.notify.NotificationChain"));
    stringBuffer.append(TEXT_322);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_323);
    if (!genFeature.isBidirectional()) {
    stringBuffer.append(TEXT_324);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.InternalEObject"));
    stringBuffer.append(TEXT_325);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_326);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(TEXT_327);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_328);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.InternalEObject"));
    stringBuffer.append(TEXT_329);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_330);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(TEXT_331);
    } else { GenFeature reverseFeature = genFeature.getReverse(); GenClass targetClass = reverseFeature.getGenClass();
    stringBuffer.append(TEXT_332);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.InternalEObject"));
    stringBuffer.append(TEXT_333);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_334);
    stringBuffer.append(targetClass.getQualifiedFeatureID(reverseFeature));
    stringBuffer.append(TEXT_335);
    stringBuffer.append(targetClass.getImportedInterfaceName());
    stringBuffer.append(TEXT_336);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_337);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.InternalEObject"));
    stringBuffer.append(TEXT_338);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_339);
    stringBuffer.append(targetClass.getQualifiedFeatureID(reverseFeature));
    stringBuffer.append(TEXT_340);
    stringBuffer.append(targetClass.getImportedInterfaceName());
    stringBuffer.append(TEXT_341);
    }
    stringBuffer.append(TEXT_342);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_343);
    stringBuffer.append(genFeature.getInternalTypeCast());
    stringBuffer.append(TEXT_344);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_345);
    if (genFeature.isUnsettable()) {
    stringBuffer.append(TEXT_346);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_347);
    stringBuffer.append(genFeature.getUncapName());
    stringBuffer.append(TEXT_348);
    stringBuffer.append(genFeature.getUncapName());
    stringBuffer.append(TEXT_349);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.impl.ENotificationImpl"));
    stringBuffer.append(TEXT_350);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.notify.Notification"));
    stringBuffer.append(TEXT_351);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(TEXT_352);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_353);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_354);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_355);
    } else {
    stringBuffer.append(TEXT_356);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.impl.ENotificationImpl"));
    stringBuffer.append(TEXT_357);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.notify.Notification"));
    stringBuffer.append(TEXT_358);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(TEXT_359);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_360);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_361);
    }
    } else {
    stringBuffer.append(TEXT_362);
    stringBuffer.append(genFeature.getImportedType());
    stringBuffer.append(TEXT_363);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_364);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_365);
    if (genFeature.isEnumType()) {
    stringBuffer.append(TEXT_366);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_367);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_368);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_369);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_370);
    } else {
    stringBuffer.append(TEXT_371);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_372);
    stringBuffer.append(genFeature.getInternalTypeCast());
    stringBuffer.append(TEXT_373);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_374);
    }
    if (genFeature.isUnsettable()) {
    stringBuffer.append(TEXT_375);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_376);
    stringBuffer.append(genFeature.getUncapName());
    stringBuffer.append(TEXT_377);
    stringBuffer.append(genFeature.getUncapName());
    stringBuffer.append(TEXT_378);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.impl.ENotificationImpl"));
    stringBuffer.append(TEXT_379);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.notify.Notification"));
    stringBuffer.append(TEXT_380);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(TEXT_381);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_382);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_383);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_384);
    } else {
    stringBuffer.append(TEXT_385);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.impl.ENotificationImpl"));
    stringBuffer.append(TEXT_386);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.notify.Notification"));
    stringBuffer.append(TEXT_387);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(TEXT_388);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_389);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_390);
    }
    }
    } else if (genFeature.hasDelegateFeature()) { GenFeature delegateFeature = genFeature.getDelegateFeature();
    if (delegateFeature.isFeatureMapWrapped()) {
    stringBuffer.append(TEXT_391);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.FeatureMap"));
    stringBuffer.append(TEXT_392);
    stringBuffer.append(delegateFeature.getImportedEffectiveFeatureMapWrapperInternalInterface());
    stringBuffer.append(TEXT_393);
    stringBuffer.append(delegateFeature.getAccessorName());
    stringBuffer.append(TEXT_394);
    stringBuffer.append(genFeature.getQualifiedFeatureAccessorName());
    stringBuffer.append(TEXT_395);
    if (genFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_396);
    stringBuffer.append(genFeature.getObjectType());
    stringBuffer.append(TEXT_397);
    }
    stringBuffer.append(TEXT_398);
    stringBuffer.append(genFeature.getCapName());
    if (genFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_399);
    }
    stringBuffer.append(TEXT_400);
    } else {
    stringBuffer.append(TEXT_401);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.FeatureMap"));
    stringBuffer.append(TEXT_402);
    stringBuffer.append(delegateFeature.getAccessorName());
    stringBuffer.append(TEXT_403);
    stringBuffer.append(genFeature.getQualifiedFeatureAccessorName());
    stringBuffer.append(TEXT_404);
    if (genFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_405);
    stringBuffer.append(genFeature.getObjectType());
    stringBuffer.append(TEXT_406);
    }
    stringBuffer.append(TEXT_407);
    stringBuffer.append(genFeature.getCapName());
    if (genFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_408);
    }
    stringBuffer.append(TEXT_409);
    }
    } else {
    stringBuffer.append(TEXT_410);
    stringBuffer.append(genFeature.getFormattedName());
    stringBuffer.append(TEXT_411);
    stringBuffer.append(genFeature.getFeatureKind());
    stringBuffer.append(TEXT_412);
    }
    stringBuffer.append(TEXT_413);
    }//if (genFeature.isChangeable())
    }//if (!genFeature.isListType())
    if (genFeature.isUnsettable()) {
    if (genFeature.isChangeable()) {
    if (!genFeature.isListType() && genFeature.isReferenceType() && (genFeature.isBidirectional() || genFeature.isContains())) {
    stringBuffer.append(TEXT_414);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.notify.NotificationChain"));
    stringBuffer.append(TEXT_415);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_416);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.notify.NotificationChain"));
    stringBuffer.append(TEXT_417);
    if (!genFeature.isVolatile()) {
    stringBuffer.append(TEXT_418);
    stringBuffer.append(genFeature.getImportedType());
    stringBuffer.append(TEXT_419);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_420);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_421);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_422);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_423);
    stringBuffer.append(genFeature.getUncapName());
    stringBuffer.append(TEXT_424);
    stringBuffer.append(genFeature.getUncapName());
    stringBuffer.append(TEXT_425);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.impl.ENotificationImpl"));
    stringBuffer.append(TEXT_426);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.impl.ENotificationImpl"));
    stringBuffer.append(TEXT_427);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.notify.Notification"));
    stringBuffer.append(TEXT_428);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(TEXT_429);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_430);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_431);
    } else {
    stringBuffer.append(TEXT_432);
    stringBuffer.append(genFeature.getFormattedName());
    stringBuffer.append(TEXT_433);
    stringBuffer.append(genFeature.getFeatureKind());
    stringBuffer.append(TEXT_434);
    }
    stringBuffer.append(TEXT_435);
    }
    stringBuffer.append(TEXT_436);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_437);
    if (genModel.isReflectiveDelegation()) {
    stringBuffer.append(TEXT_438);
    stringBuffer.append(genFeature.getGenPackage().getImportedPackageInterfaceName());
    stringBuffer.append(TEXT_439);
    stringBuffer.append(genFeature.getFeatureAccessorName());
    stringBuffer.append(TEXT_440);
    } else if (!genFeature.isVolatile()) {
    if (genFeature.isListType()) {
    stringBuffer.append(TEXT_441);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.InternalEList"));
    stringBuffer.append(TEXT_442);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_443);
    } else if (genFeature.isBidirectional() || genFeature.isContains()) {
    stringBuffer.append(TEXT_444);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_445);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.notify.NotificationChain"));
    stringBuffer.append(TEXT_446);
    if (!genFeature.isBidirectional()) {
    stringBuffer.append(TEXT_447);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.InternalEObject"));
    stringBuffer.append(TEXT_448);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_449);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(TEXT_450);
    } else { GenFeature reverseFeature = genFeature.getReverse(); GenClass targetClass = reverseFeature.getGenClass();
    stringBuffer.append(TEXT_451);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.InternalEObject"));
    stringBuffer.append(TEXT_452);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_453);
    stringBuffer.append(targetClass.getQualifiedFeatureID(reverseFeature));
    stringBuffer.append(TEXT_454);
    stringBuffer.append(targetClass.getImportedInterfaceName());
    stringBuffer.append(TEXT_455);
    }
    stringBuffer.append(TEXT_456);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_457);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_458);
    stringBuffer.append(genFeature.getUncapName());
    stringBuffer.append(TEXT_459);
    stringBuffer.append(genFeature.getUncapName());
    stringBuffer.append(TEXT_460);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.impl.ENotificationImpl"));
    stringBuffer.append(TEXT_461);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.notify.Notification"));
    stringBuffer.append(TEXT_462);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(TEXT_463);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_464);
    } else {
    stringBuffer.append(TEXT_465);
    stringBuffer.append(genFeature.getImportedType());
    stringBuffer.append(TEXT_466);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_467);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_468);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_469);
    stringBuffer.append(genFeature.getUncapName());
    stringBuffer.append(TEXT_470);
    if (genFeature.isReferenceType()) {
    stringBuffer.append(TEXT_471);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_472);
    stringBuffer.append(genFeature.getUncapName());
    stringBuffer.append(TEXT_473);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.impl.ENotificationImpl"));
    stringBuffer.append(TEXT_474);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.notify.Notification"));
    stringBuffer.append(TEXT_475);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(TEXT_476);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_477);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_478);
    } else {
    stringBuffer.append(TEXT_479);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_480);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_481);
    stringBuffer.append(genFeature.getUncapName());
    stringBuffer.append(TEXT_482);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.impl.ENotificationImpl"));
    stringBuffer.append(TEXT_483);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.notify.Notification"));
    stringBuffer.append(TEXT_484);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(TEXT_485);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_486);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_487);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_488);
    }
    }
    } else if (genFeature.hasDelegateFeature()) { GenFeature delegateFeature = genFeature.getDelegateFeature();
    if (delegateFeature.isFeatureMapWrapped()) {
    stringBuffer.append(TEXT_489);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.FeatureMap"));
    stringBuffer.append(TEXT_490);
    stringBuffer.append(delegateFeature.getImportedEffectiveFeatureMapWrapperInternalInterface());
    stringBuffer.append(TEXT_491);
    stringBuffer.append(delegateFeature.getAccessorName());
    stringBuffer.append(TEXT_492);
    stringBuffer.append(genFeature.getQualifiedFeatureAccessorName());
    stringBuffer.append(TEXT_493);
    } else {
    stringBuffer.append(TEXT_494);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.FeatureMap"));
    stringBuffer.append(TEXT_495);
    stringBuffer.append(delegateFeature.getAccessorName());
    stringBuffer.append(TEXT_496);
    stringBuffer.append(genFeature.getQualifiedFeatureAccessorName());
    stringBuffer.append(TEXT_497);
    }
    } else {
    stringBuffer.append(TEXT_498);
    stringBuffer.append(genFeature.getFormattedName());
    stringBuffer.append(TEXT_499);
    stringBuffer.append(genFeature.getFeatureKind());
    stringBuffer.append(TEXT_500);
    }
    stringBuffer.append(TEXT_501);
    }//if (genFeature.isChangeable())
    stringBuffer.append(TEXT_502);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_503);
    if (genModel.isReflectiveDelegation()) {
    stringBuffer.append(TEXT_504);
    stringBuffer.append(genFeature.getGenPackage().getImportedPackageInterfaceName());
    stringBuffer.append(TEXT_505);
    stringBuffer.append(genFeature.getFeatureAccessorName());
    stringBuffer.append(TEXT_506);
    } else if (!genFeature.isVolatile()) {
    if (genFeature.isListType()) {
    stringBuffer.append(TEXT_507);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_508);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.InternalEList"));
    stringBuffer.append(TEXT_509);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_510);
    } else {
    stringBuffer.append(TEXT_511);
    stringBuffer.append(genFeature.getUncapName());
    stringBuffer.append(TEXT_512);
    }
    } else if (genFeature.hasDelegateFeature()) { GenFeature delegateFeature = genFeature.getDelegateFeature();
    if (delegateFeature.isFeatureMapWrapped()) {
    stringBuffer.append(TEXT_513);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.FeatureMap"));
    stringBuffer.append(TEXT_514);
    stringBuffer.append(delegateFeature.getImportedEffectiveFeatureMapWrapperInternalInterface());
    stringBuffer.append(TEXT_515);
    stringBuffer.append(delegateFeature.getAccessorName());
    stringBuffer.append(TEXT_516);
    stringBuffer.append(genFeature.getQualifiedFeatureAccessorName());
    stringBuffer.append(TEXT_517);
    } else {
    stringBuffer.append(TEXT_518);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.FeatureMap"));
    stringBuffer.append(TEXT_519);
    stringBuffer.append(delegateFeature.getAccessorName());
    stringBuffer.append(TEXT_520);
    stringBuffer.append(genFeature.getQualifiedFeatureAccessorName());
    stringBuffer.append(TEXT_521);
    }
    } else {
    stringBuffer.append(TEXT_522);
    stringBuffer.append(genFeature.getFormattedName());
    stringBuffer.append(TEXT_523);
    stringBuffer.append(genFeature.getFeatureKind());
    stringBuffer.append(TEXT_524);
    }
    stringBuffer.append(TEXT_525);
    }
    }//for
    for (Iterator i=genClass.getImplementedGenOperations().iterator(); i.hasNext();) { GenOperation genOperation = (GenOperation)i.next();
    stringBuffer.append(TEXT_526);
    stringBuffer.append(genOperation.getImportedReturnType());
    stringBuffer.append(TEXT_527);
    stringBuffer.append(genOperation.getName());
    stringBuffer.append(TEXT_528);
    stringBuffer.append(genOperation.getParameters());
    stringBuffer.append(TEXT_529);
    stringBuffer.append(genOperation.getThrows());
    stringBuffer.append(TEXT_530);
    if (genOperation.hasBody()) {
    stringBuffer.append(TEXT_531);
    stringBuffer.append(genOperation.getBody(genModel.getIndentation(stringBuffer)));
    } else if (genOperation.isInvariant()) {
    stringBuffer.append(TEXT_532);
    } else {
    stringBuffer.append(TEXT_533);
    }
    stringBuffer.append(TEXT_534);
    }//for
    if (!genModel.isReflectiveDelegation() && !genClass.getEInverseAddGenFeatures().isEmpty()) {
    stringBuffer.append(TEXT_535);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.notify.NotificationChain"));
    stringBuffer.append(TEXT_536);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.InternalEObject"));
    stringBuffer.append(TEXT_537);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.notify.NotificationChain"));
    stringBuffer.append(TEXT_538);
    for (Iterator i=genClass.getEInverseAddGenFeatures().iterator(); i.hasNext();) { GenFeature genFeature = (GenFeature)i.next();
    stringBuffer.append(TEXT_539);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(TEXT_540);
    if (genFeature.isListType()) {
    if (genFeature.isMapType() && genFeature.isEffectiveSuppressEMFTypes()) {
    stringBuffer.append(TEXT_541);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.InternalEList"));
    stringBuffer.append(TEXT_542);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.util.EMap$InternalMapView"));
    stringBuffer.append(TEXT_543);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_544);
    } else {
    stringBuffer.append(TEXT_545);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.InternalEList"));
    stringBuffer.append(TEXT_546);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_547);
    }
    } else if (genFeature.isContainer()) {
    stringBuffer.append(TEXT_548);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(TEXT_549);
    } else {
    stringBuffer.append(TEXT_550);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_551);
    if (genFeature.isContains()) {
    stringBuffer.append(TEXT_552);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.InternalEObject"));
    stringBuffer.append(TEXT_553);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_554);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(TEXT_555);
    } else { GenFeature reverseFeature = genFeature.getReverse(); GenClass targetClass = reverseFeature.getGenClass();
    stringBuffer.append(TEXT_556);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.InternalEObject"));
    stringBuffer.append(TEXT_557);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_558);
    stringBuffer.append(targetClass.getQualifiedFeatureID(reverseFeature));
    stringBuffer.append(TEXT_559);
    stringBuffer.append(targetClass.getImportedInterfaceName());
    stringBuffer.append(TEXT_560);
    }
    stringBuffer.append(TEXT_561);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_562);
    stringBuffer.append(genFeature.getImportedType());
    stringBuffer.append(TEXT_563);
    }
    }
    stringBuffer.append(TEXT_564);
    }
    if (!genModel.isReflectiveDelegation() && !genClass.getEInverseRemoveGenFeatures().isEmpty()) {
    stringBuffer.append(TEXT_565);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.notify.NotificationChain"));
    stringBuffer.append(TEXT_566);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.InternalEObject"));
    stringBuffer.append(TEXT_567);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.notify.NotificationChain"));
    stringBuffer.append(TEXT_568);
    for (Iterator i=genClass.getEInverseRemoveGenFeatures().iterator(); i.hasNext();) { GenFeature genFeature = (GenFeature)i.next();
    stringBuffer.append(TEXT_569);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(TEXT_570);
    if (genFeature.isListType()) {
    if (genFeature.isMapType() && genFeature.isEffectiveSuppressEMFTypes()) {
    stringBuffer.append(TEXT_571);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.InternalEList"));
    stringBuffer.append(TEXT_572);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.util.EMap$InternalMapView"));
    stringBuffer.append(TEXT_573);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_574);
    } else if (genFeature.isFeatureMapWrapped()) {
    stringBuffer.append(TEXT_575);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.InternalEList"));
    stringBuffer.append(TEXT_576);
    stringBuffer.append(genFeature.getImportedEffectiveFeatureMapWrapperInternalInterface());
    stringBuffer.append(TEXT_577);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_578);
    } else {
    stringBuffer.append(TEXT_579);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.InternalEList"));
    stringBuffer.append(TEXT_580);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_581);
    }
    } else if (genFeature.isContainer()) {
    stringBuffer.append(TEXT_582);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(TEXT_583);
    } else if (genFeature.isUnsettable()) {
    stringBuffer.append(TEXT_584);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_585);
    } else {
    stringBuffer.append(TEXT_586);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_587);
    }
    }
    stringBuffer.append(TEXT_588);
    }
    if (!genModel.isReflectiveDelegation() && !genClass.getEBasicRemoveFromContainerGenFeatures().isEmpty()) {
    stringBuffer.append(TEXT_589);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.notify.NotificationChain"));
    stringBuffer.append(TEXT_590);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.notify.NotificationChain"));
    stringBuffer.append(TEXT_591);
    for (Iterator i=genClass.getEBasicRemoveFromContainerGenFeatures().iterator(); i.hasNext();) { GenFeature genFeature = (GenFeature)i.next();
    GenFeature reverseFeature = genFeature.getReverse(); GenClass targetClass = reverseFeature.getGenClass();
    stringBuffer.append(TEXT_592);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(TEXT_593);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.InternalEObject"));
    stringBuffer.append(TEXT_594);
    stringBuffer.append(targetClass.getQualifiedFeatureID(reverseFeature));
    stringBuffer.append(TEXT_595);
    stringBuffer.append(targetClass.getImportedInterfaceName());
    stringBuffer.append(TEXT_596);
    }
    stringBuffer.append(TEXT_597);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.InternalEObject"));
    stringBuffer.append(TEXT_598);
    }
    if (!genModel.isReflectiveDelegation() && !genClass.getAllGenFeatures().isEmpty()) {
    stringBuffer.append(TEXT_599);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.EStructuralFeature"));
    stringBuffer.append(TEXT_600);
    for (Iterator i=genClass.getAllGenFeatures().iterator(); i.hasNext();) { GenFeature genFeature = (GenFeature)i.next();
    stringBuffer.append(TEXT_601);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(TEXT_602);
    if (genFeature.isPrimitiveType() && !genFeature.isListType()) {
    if (genFeature.isBooleanType()) {
    stringBuffer.append(TEXT_603);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_604);
    } else {
    stringBuffer.append(TEXT_605);
    stringBuffer.append(genFeature.getObjectType());
    stringBuffer.append(TEXT_606);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_607);
    }
    } else if (genFeature.isResolveProxies() && !genFeature.isListType()) {
    stringBuffer.append(TEXT_608);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_609);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_610);
    } else if (genFeature.isMapType() && genFeature.isEffectiveSuppressEMFTypes()) {
    stringBuffer.append(TEXT_611);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.util.EMap$InternalMapView"));
    stringBuffer.append(TEXT_612);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_613);
    } else if (genFeature.isFeatureMapWrapped()) {
    stringBuffer.append(TEXT_614);
    stringBuffer.append(genFeature.getImportedEffectiveFeatureMapWrapperInternalInterface());
    stringBuffer.append(TEXT_615);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_616);
    } else {
    stringBuffer.append(TEXT_617);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_618);
    }
    }
    stringBuffer.append(TEXT_619);
    }
    if (!genModel.isReflectiveDelegation() && !genClass.getESetGenFeatures().isEmpty()) {
    stringBuffer.append(TEXT_620);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.EStructuralFeature"));
    stringBuffer.append(TEXT_621);
    for (Iterator i=genClass.getESetGenFeatures().iterator(); i.hasNext();) { GenFeature genFeature = (GenFeature)i.next();
    stringBuffer.append(TEXT_622);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(TEXT_623);
    if (genFeature.isListType()) {
    if (genFeature.isFeatureMapWrapped()) {
    stringBuffer.append(TEXT_624);
    stringBuffer.append(genFeature.getImportedEffectiveFeatureMapWrapperInternalInterface());
    stringBuffer.append(TEXT_625);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_626);
    } else {
    stringBuffer.append(TEXT_627);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_628);
    }
    if (genFeature.isFeatureMapWrapped()) {
    stringBuffer.append(TEXT_629);
    stringBuffer.append(genFeature.getImportedEffectiveFeatureMapWrapperInternalInterface());
    stringBuffer.append(TEXT_630);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_631);
    stringBuffer.append(genModel.getImportedName("java.util.Collection"));
    stringBuffer.append(TEXT_632);
    } else if (genFeature.isMapType() && genFeature.isEffectiveSuppressEMFTypes()) {
    stringBuffer.append(TEXT_633);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.util.EMap$InternalMapView"));
    stringBuffer.append(TEXT_634);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_635);
    stringBuffer.append(genModel.getImportedName("java.util.Collection"));
    stringBuffer.append(TEXT_636);
    } else {
    stringBuffer.append(TEXT_637);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_638);
    stringBuffer.append(genModel.getImportedName("java.util.Collection"));
    stringBuffer.append(TEXT_639);
    }
    } else if (genFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_640);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_641);
    stringBuffer.append(genFeature.getObjectType());
    stringBuffer.append(TEXT_642);
    stringBuffer.append(genFeature.getPrimitiveValueFunction());
    stringBuffer.append(TEXT_643);
    } else {
    stringBuffer.append(TEXT_644);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_645);
    stringBuffer.append(genFeature.getImportedType());
    stringBuffer.append(TEXT_646);
    }
    stringBuffer.append(TEXT_647);
    }
    stringBuffer.append(TEXT_648);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.EStructuralFeature"));
    stringBuffer.append(TEXT_649);
    for (Iterator i=genClass.getESetGenFeatures().iterator(); i.hasNext();) { GenFeature genFeature = (GenFeature)i.next();
    stringBuffer.append(TEXT_650);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(TEXT_651);
    if (genFeature.isListType() && !genFeature.isUnsettable()) {
    if (genFeature.isFeatureMapWrapped()) {
    stringBuffer.append(TEXT_652);
    stringBuffer.append(genFeature.getImportedEffectiveFeatureMapWrapperInternalInterface());
    stringBuffer.append(TEXT_653);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_654);
    } else {
    stringBuffer.append(TEXT_655);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_656);
    }
    } else if (genFeature.isUnsettable()) {
    stringBuffer.append(TEXT_657);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_658);
    } else if (genFeature.isReferenceType()) {
    stringBuffer.append(TEXT_659);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_660);
    stringBuffer.append(genFeature.getImportedType());
    stringBuffer.append(TEXT_661);
    } else {
    stringBuffer.append(TEXT_662);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_663);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_664);
    }
    stringBuffer.append(TEXT_665);
    }
    stringBuffer.append(TEXT_666);
    }
    if (!genModel.isReflectiveDelegation() && !genClass.getAllGenFeatures().isEmpty()) {
    stringBuffer.append(TEXT_667);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.EStructuralFeature"));
    stringBuffer.append(TEXT_668);
    for (Iterator i=genClass.getAllGenFeatures().iterator(); i.hasNext();) { GenFeature genFeature = (GenFeature)i.next();
    stringBuffer.append(TEXT_669);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(TEXT_670);
    if (genFeature.isListType() && !genFeature.isUnsettable()) {
    if (genFeature.isFeatureMapWrapped()) {
    if (genFeature.isVolatile()) {
    stringBuffer.append(TEXT_671);
    stringBuffer.append(genFeature.getImportedEffectiveFeatureMapWrapperInternalInterface());
    stringBuffer.append(TEXT_672);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_673);
    } else {
    stringBuffer.append(TEXT_674);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_675);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_676);
    }
    } else {
    if (genFeature.isVolatile()) {
    stringBuffer.append(TEXT_677);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_678);
    } else {
    stringBuffer.append(TEXT_679);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_680);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_681);
    }
    }
    } else if (genFeature.isUnsettable()) {
    stringBuffer.append(TEXT_682);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_683);
    } else if (genFeature.isResolveProxies()) {
    if (genFeature.isVolatile()) {
    stringBuffer.append(TEXT_684);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_685);
    } else {
    stringBuffer.append(TEXT_686);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_687);
    }
    } else if (genFeature.isReferenceType()) {
    if (genFeature.isVolatile() || genFeature.isContainer()) {
    stringBuffer.append(TEXT_688);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_689);
    } else {
    stringBuffer.append(TEXT_690);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_691);
    }
    } else if (genFeature.isPrimitiveType() || genFeature.isEnumType()) {
    if (genFeature.isVolatile()) {
    stringBuffer.append(TEXT_692);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_693);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_694);
    } else {
    stringBuffer.append(TEXT_695);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_696);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_697);
    }
    } else {//datatype
    if (genFeature.isVolatile()) {
    stringBuffer.append(TEXT_698);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_699);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_700);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_701);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_702);
    } else {
    stringBuffer.append(TEXT_703);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_704);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_705);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_706);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_707);
    }
    }
    }
    stringBuffer.append(TEXT_708);
    }
    if (!genClass.getMixinGenFeatures().isEmpty()) {
    stringBuffer.append(TEXT_709);
    for (Iterator m=genClass.getMixinGenClasses().iterator(); m.hasNext();) { GenClass mixinGenClass = (GenClass)m.next(); 
    stringBuffer.append(TEXT_710);
    stringBuffer.append(mixinGenClass.getImportedInterfaceName());
    stringBuffer.append(TEXT_711);
    for (Iterator f=mixinGenClass.getGenFeatures().iterator(); f.hasNext();) { GenFeature genFeature = (GenFeature)f.next(); 
    stringBuffer.append(TEXT_712);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(TEXT_713);
    stringBuffer.append(mixinGenClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(TEXT_714);
    }
    stringBuffer.append(TEXT_715);
    }
    stringBuffer.append(TEXT_716);
    for (Iterator m=genClass.getMixinGenClasses().iterator(); m.hasNext();) { GenClass mixinGenClass = (GenClass)m.next(); 
    stringBuffer.append(TEXT_717);
    stringBuffer.append(mixinGenClass.getImportedInterfaceName());
    stringBuffer.append(TEXT_718);
    for (Iterator f=mixinGenClass.getGenFeatures().iterator(); f.hasNext();) { GenFeature genFeature = (GenFeature)f.next(); 
    stringBuffer.append(TEXT_719);
    stringBuffer.append(mixinGenClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(TEXT_720);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(TEXT_721);
    }
    stringBuffer.append(TEXT_722);
    }
    stringBuffer.append(TEXT_723);
    }
    if (!genModel.isReflectiveDelegation() && !genClass.getToStringGenFeatures().isEmpty()) {
    stringBuffer.append(TEXT_724);
    { boolean first = true;
    for (Iterator i=genClass.getToStringGenFeatures().iterator(); i.hasNext(); ) { GenFeature genFeature = (GenFeature)i.next();
    if (first) { first = false;
    stringBuffer.append(TEXT_725);
    stringBuffer.append(genFeature.getName());
    stringBuffer.append(TEXT_726);
    stringBuffer.append(genModel.getNonNLS());
    } else {
    stringBuffer.append(TEXT_727);
    stringBuffer.append(genFeature.getName());
    stringBuffer.append(TEXT_728);
    stringBuffer.append(genModel.getNonNLS());
    }
    if (genFeature.isUnsettable() && !genFeature.isListType()) {
    stringBuffer.append(TEXT_729);
    stringBuffer.append(genFeature.getUncapName());
    stringBuffer.append(TEXT_730);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_731);
    stringBuffer.append(genModel.getNonNLS());
    } else {
    stringBuffer.append(TEXT_732);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_733);
    }
    }
    }
    stringBuffer.append(TEXT_734);
    }
    if (genClass.isMapEntry()) { GenFeature keyFeature = genClass.getMapEntryKeyFeature(); GenFeature valueFeature = genClass.getMapEntryValueFeature();
    stringBuffer.append(TEXT_735);
    if (keyFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_736);
    stringBuffer.append(keyFeature.getObjectType());
    stringBuffer.append(TEXT_737);
    } else {
    stringBuffer.append(TEXT_738);
    }
    stringBuffer.append(TEXT_739);
    if (keyFeature.isListType()) {
    stringBuffer.append(TEXT_740);
    stringBuffer.append(genModel.getImportedName("java.util.Collection"));
    stringBuffer.append(TEXT_741);
    } else if (keyFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_742);
    stringBuffer.append(keyFeature.getObjectType());
    stringBuffer.append(TEXT_743);
    stringBuffer.append(keyFeature.getPrimitiveValueFunction());
    stringBuffer.append(TEXT_744);
    } else {
    stringBuffer.append(TEXT_745);
    stringBuffer.append(keyFeature.getImportedType());
    stringBuffer.append(TEXT_746);
    }
    stringBuffer.append(TEXT_747);
    if (valueFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_748);
    stringBuffer.append(valueFeature.getObjectType());
    stringBuffer.append(TEXT_749);
    } else {
    stringBuffer.append(TEXT_750);
    }
    stringBuffer.append(TEXT_751);
    if (valueFeature.isListType()) {
    stringBuffer.append(TEXT_752);
    stringBuffer.append(genModel.getImportedName("java.util.Collection"));
    stringBuffer.append(TEXT_753);
    } else if (valueFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_754);
    stringBuffer.append(valueFeature.getObjectType());
    stringBuffer.append(TEXT_755);
    stringBuffer.append(valueFeature.getPrimitiveValueFunction());
    stringBuffer.append(TEXT_756);
    } else {
    stringBuffer.append(TEXT_757);
    stringBuffer.append(valueFeature.getImportedType());
    stringBuffer.append(TEXT_758);
    }
    stringBuffer.append(TEXT_759);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.util.EMap"));
    stringBuffer.append(TEXT_760);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.EObject"));
    stringBuffer.append(TEXT_761);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.util.EMap"));
    stringBuffer.append(TEXT_762);
    }
    stringBuffer.append(TEXT_763);
    stringBuffer.append(genClass.getClassName());
    genModel.emitSortedImports();
    stringBuffer.append(TEXT_764);
    return stringBuffer.toString();
  }
}
