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
  protected final String TEXT_26 = NL + "\t/**" + NL + "\t * A set of bit flags representing the values of boolean attributes and whether unsettable features have been set." + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t * @ordered" + NL + "\t */" + NL + "\tprotected int ";
  protected final String TEXT_27 = " = 0;" + NL;
  protected final String TEXT_28 = NL + "\t/**" + NL + "\t * The cached value of the '{@link #";
  protected final String TEXT_29 = "() <em>";
  protected final String TEXT_30 = "</em>}' ";
  protected final String TEXT_31 = "." + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @see #";
  protected final String TEXT_32 = "()" + NL + "\t * @generated" + NL + "\t * @ordered" + NL + "\t */" + NL + "\tprotected ";
  protected final String TEXT_33 = " ";
  protected final String TEXT_34 = " = null;" + NL;
  protected final String TEXT_35 = NL + "\t/**" + NL + "\t * The default value of the '{@link #";
  protected final String TEXT_36 = "() <em>";
  protected final String TEXT_37 = "</em>}' ";
  protected final String TEXT_38 = "." + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @see #";
  protected final String TEXT_39 = "()" + NL + "\t * @generated" + NL + "\t * @ordered" + NL + "\t */" + NL + "\tprotected static final ";
  protected final String TEXT_40 = " ";
  protected final String TEXT_41 = "_EDEFAULT = ";
  protected final String TEXT_42 = ";";
  protected final String TEXT_43 = NL;
  protected final String TEXT_44 = NL + "\t/**" + NL + "\t * An additional set of bit flags representing the values of boolean attributes and whether unsettable features have been set." + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t * @ordered" + NL + "\t */" + NL + "\tprotected int ";
  protected final String TEXT_45 = " = 0;" + NL;
  protected final String TEXT_46 = NL + "\t/**" + NL + "\t * The flag representing the value of the '{@link #";
  protected final String TEXT_47 = "() <em>";
  protected final String TEXT_48 = "</em>}' ";
  protected final String TEXT_49 = "." + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @see #";
  protected final String TEXT_50 = "()" + NL + "\t * @generated" + NL + "\t * @ordered" + NL + "\t */" + NL + "\tprotected static final int ";
  protected final String TEXT_51 = "_EFLAG = 1 ";
  protected final String TEXT_52 = ";" + NL;
  protected final String TEXT_53 = NL + "\t/**" + NL + "\t * The cached value of the '{@link #";
  protected final String TEXT_54 = "() <em>";
  protected final String TEXT_55 = "</em>}' ";
  protected final String TEXT_56 = "." + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @see #";
  protected final String TEXT_57 = "()" + NL + "\t * @generated" + NL + "\t * @ordered" + NL + "\t */" + NL + "\tprotected ";
  protected final String TEXT_58 = " ";
  protected final String TEXT_59 = " = ";
  protected final String TEXT_60 = "_EDEFAULT;" + NL;
  protected final String TEXT_61 = NL + "\t/**" + NL + "\t * An additional set of bit flags representing the values of boolean attributes and whether unsettable features have been set." + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t * @ordered" + NL + "\t */" + NL + "\tprotected int ";
  protected final String TEXT_62 = " = 0;" + NL;
  protected final String TEXT_63 = NL + "\t/**" + NL + "\t * The flag representing whether the ";
  protected final String TEXT_64 = " ";
  protected final String TEXT_65 = " has been set." + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t * @ordered" + NL + "\t */" + NL + "\tprotected static final int ";
  protected final String TEXT_66 = "_ESETFLAG = 1 ";
  protected final String TEXT_67 = ";" + NL;
  protected final String TEXT_68 = NL + "\t/**" + NL + "\t * This is true if the ";
  protected final String TEXT_69 = " ";
  protected final String TEXT_70 = " has been set." + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t * @ordered" + NL + "\t */" + NL + "\tprotected boolean ";
  protected final String TEXT_71 = "ESet = false;" + NL;
  protected final String TEXT_72 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */" + NL + "\tprotected ";
  protected final String TEXT_73 = "()" + NL + "\t{" + NL + "\t\tsuper();";
  protected final String TEXT_74 = NL + "\t\t";
  protected final String TEXT_75 = " |= ";
  protected final String TEXT_76 = "_EFLAG;";
  protected final String TEXT_77 = NL + "\t}" + NL + "" + NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */" + NL + "\tprotected ";
  protected final String TEXT_78 = " eStaticClass()" + NL + "\t{" + NL + "\t\treturn ";
  protected final String TEXT_79 = ".eINSTANCE.get";
  protected final String TEXT_80 = "();" + NL + "\t}" + NL;
  protected final String TEXT_81 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */" + NL + "\tpublic ";
  protected final String TEXT_82 = " ";
  protected final String TEXT_83 = "()" + NL + "\t{";
  protected final String TEXT_84 = NL + "\t\treturn ";
  protected final String TEXT_85 = "(";
  protected final String TEXT_86 = "(";
  protected final String TEXT_87 = ")eGet(";
  protected final String TEXT_88 = ".eINSTANCE.get";
  protected final String TEXT_89 = "(), true)";
  protected final String TEXT_90 = ").";
  protected final String TEXT_91 = "()";
  protected final String TEXT_92 = ";";
  protected final String TEXT_93 = NL + "\t\tif (";
  protected final String TEXT_94 = " == null)" + NL + "\t\t{";
  protected final String TEXT_95 = NL + "\t\t\t";
  protected final String TEXT_96 = " = new ";
  protected final String TEXT_97 = "(";
  protected final String TEXT_98 = ".eINSTANCE.get";
  protected final String TEXT_99 = "(), ";
  protected final String TEXT_100 = ".class, this, ";
  protected final String TEXT_101 = ");";
  protected final String TEXT_102 = NL + "\t\t\t";
  protected final String TEXT_103 = " = new ";
  protected final String TEXT_104 = "(";
  protected final String TEXT_105 = ".class, this, ";
  protected final String TEXT_106 = ", ";
  protected final String TEXT_107 = ");";
  protected final String TEXT_108 = NL + "\t\t\t";
  protected final String TEXT_109 = " = new ";
  protected final String TEXT_110 = "(";
  protected final String TEXT_111 = ".class, this, ";
  protected final String TEXT_112 = ");";
  protected final String TEXT_113 = NL + "\t\t\t";
  protected final String TEXT_114 = " = new ";
  protected final String TEXT_115 = ".ManyInverse(";
  protected final String TEXT_116 = ".class, this, ";
  protected final String TEXT_117 = ", ";
  protected final String TEXT_118 = ");";
  protected final String TEXT_119 = NL + "\t\t\t";
  protected final String TEXT_120 = " = new ";
  protected final String TEXT_121 = ".ManyInverse(";
  protected final String TEXT_122 = ".class, this, ";
  protected final String TEXT_123 = ", ";
  protected final String TEXT_124 = ");";
  protected final String TEXT_125 = NL + "\t\t\t";
  protected final String TEXT_126 = " = new ";
  protected final String TEXT_127 = "(";
  protected final String TEXT_128 = ".class, this, ";
  protected final String TEXT_129 = ", ";
  protected final String TEXT_130 = ");";
  protected final String TEXT_131 = NL + "\t\t\t";
  protected final String TEXT_132 = " = new ";
  protected final String TEXT_133 = "(";
  protected final String TEXT_134 = ".class, this, ";
  protected final String TEXT_135 = ", ";
  protected final String TEXT_136 = ");";
  protected final String TEXT_137 = NL + "\t\t\t";
  protected final String TEXT_138 = " = new ";
  protected final String TEXT_139 = "(";
  protected final String TEXT_140 = ".class, this, ";
  protected final String TEXT_141 = ");";
  protected final String TEXT_142 = NL + "\t\t\t";
  protected final String TEXT_143 = " = new ";
  protected final String TEXT_144 = "(";
  protected final String TEXT_145 = ".class, this, ";
  protected final String TEXT_146 = ");";
  protected final String TEXT_147 = NL + "\t\t\t";
  protected final String TEXT_148 = " = new ";
  protected final String TEXT_149 = "(new ";
  protected final String TEXT_150 = "(this, ";
  protected final String TEXT_151 = "));";
  protected final String TEXT_152 = NL + "\t\t\t";
  protected final String TEXT_153 = " = new ";
  protected final String TEXT_154 = "(this, ";
  protected final String TEXT_155 = ");";
  protected final String TEXT_156 = NL + "\t\t\t";
  protected final String TEXT_157 = " = new ";
  protected final String TEXT_158 = "(";
  protected final String TEXT_159 = ".class, this, ";
  protected final String TEXT_160 = ");";
  protected final String TEXT_161 = NL + "\t\t\t";
  protected final String TEXT_162 = " = new ";
  protected final String TEXT_163 = "(";
  protected final String TEXT_164 = ".class, this, ";
  protected final String TEXT_165 = ");";
  protected final String TEXT_166 = NL + "\t\t}" + NL + "\t\treturn ";
  protected final String TEXT_167 = ";";
  protected final String TEXT_168 = NL + "\t\tif (eContainerFeatureID != ";
  protected final String TEXT_169 = ") return null;" + NL + "\t\treturn (";
  protected final String TEXT_170 = ")eContainer;";
  protected final String TEXT_171 = NL + "\t\tif (";
  protected final String TEXT_172 = " != null && ";
  protected final String TEXT_173 = ".eIsProxy())" + NL + "\t\t{" + NL + "\t\t\t";
  protected final String TEXT_174 = " old";
  protected final String TEXT_175 = " = ";
  protected final String TEXT_176 = ";" + NL + "\t\t\t";
  protected final String TEXT_177 = " = (";
  protected final String TEXT_178 = ")eResolveProxy((";
  protected final String TEXT_179 = ")";
  protected final String TEXT_180 = ");" + NL + "\t\t\tif (";
  protected final String TEXT_181 = " != old";
  protected final String TEXT_182 = ")" + NL + "\t\t\t{" + NL + "\t\t\t\tif (eNotificationRequired())" + NL + "\t\t\t\t\teNotify(new ";
  protected final String TEXT_183 = "(this, ";
  protected final String TEXT_184 = ".RESOLVE, ";
  protected final String TEXT_185 = ", old";
  protected final String TEXT_186 = ", ";
  protected final String TEXT_187 = "));" + NL + "\t\t\t}" + NL + "\t\t}";
  protected final String TEXT_188 = NL + "\t\treturn (";
  protected final String TEXT_189 = " & ";
  protected final String TEXT_190 = "_EFLAG) != 0;";
  protected final String TEXT_191 = NL + "\t\treturn ";
  protected final String TEXT_192 = ";";
  protected final String TEXT_193 = NL + "\t\t";
  protected final String TEXT_194 = " ";
  protected final String TEXT_195 = " = basicGet";
  protected final String TEXT_196 = "();" + NL + "\t\treturn ";
  protected final String TEXT_197 = " == null ? null : (";
  protected final String TEXT_198 = ")eResolveProxy((";
  protected final String TEXT_199 = ")";
  protected final String TEXT_200 = ");";
  protected final String TEXT_201 = NL + "\t\treturn new ";
  protected final String TEXT_202 = "((";
  protected final String TEXT_203 = ")((";
  protected final String TEXT_204 = ")get";
  protected final String TEXT_205 = "()).featureMap().list(";
  protected final String TEXT_206 = "()));";
  protected final String TEXT_207 = NL + "\t\treturn (";
  protected final String TEXT_208 = ")((";
  protected final String TEXT_209 = ")get";
  protected final String TEXT_210 = "()).list(";
  protected final String TEXT_211 = "());";
  protected final String TEXT_212 = NL + "\t\treturn ((";
  protected final String TEXT_213 = ")get";
  protected final String TEXT_214 = "()).featureMap().list(";
  protected final String TEXT_215 = "());";
  protected final String TEXT_216 = NL + "\t\treturn ((";
  protected final String TEXT_217 = ")get";
  protected final String TEXT_218 = "()).list(";
  protected final String TEXT_219 = "());";
  protected final String TEXT_220 = NL + "\t\treturn ";
  protected final String TEXT_221 = "(";
  protected final String TEXT_222 = "(";
  protected final String TEXT_223 = ")((";
  protected final String TEXT_224 = ")get";
  protected final String TEXT_225 = "()).featureMap().get(";
  protected final String TEXT_226 = "(), true)";
  protected final String TEXT_227 = ").";
  protected final String TEXT_228 = "()";
  protected final String TEXT_229 = ";";
  protected final String TEXT_230 = NL + "\t\treturn ";
  protected final String TEXT_231 = "(";
  protected final String TEXT_232 = "(";
  protected final String TEXT_233 = ")get";
  protected final String TEXT_234 = "().get(";
  protected final String TEXT_235 = "(), true)";
  protected final String TEXT_236 = ").";
  protected final String TEXT_237 = "()";
  protected final String TEXT_238 = ";";
  protected final String TEXT_239 = NL + "\t\t// TODO: implement this method to return the '";
  protected final String TEXT_240 = "' ";
  protected final String TEXT_241 = NL + "\t\t// Ensure that you remove @generated or mark it @generated NOT" + NL + "\t\tthrow new UnsupportedOperationException();";
  protected final String TEXT_242 = NL + "\t}" + NL;
  protected final String TEXT_243 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */" + NL + "\tpublic ";
  protected final String TEXT_244 = " basicGet";
  protected final String TEXT_245 = "()" + NL + "\t{";
  protected final String TEXT_246 = NL + "\t\treturn ";
  protected final String TEXT_247 = ";";
  protected final String TEXT_248 = NL + "\t\treturn (";
  protected final String TEXT_249 = ")((";
  protected final String TEXT_250 = ")get";
  protected final String TEXT_251 = "()).featureMap().get(";
  protected final String TEXT_252 = "(), false);";
  protected final String TEXT_253 = NL + "\t\treturn (";
  protected final String TEXT_254 = ")get";
  protected final String TEXT_255 = "().get(";
  protected final String TEXT_256 = "(), false);";
  protected final String TEXT_257 = NL + "\t\t// TODO: implement this method to return the '";
  protected final String TEXT_258 = "' ";
  protected final String TEXT_259 = NL + "\t\t// -> do not perform proxy resolution" + NL + "\t\t// Ensure that you remove @generated or mark it @generated NOT" + NL + "\t\tthrow new UnsupportedOperationException();";
  protected final String TEXT_260 = NL + "\t}" + NL;
  protected final String TEXT_261 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */" + NL + "\tpublic ";
  protected final String TEXT_262 = " basicSet";
  protected final String TEXT_263 = "(";
  protected final String TEXT_264 = " new";
  protected final String TEXT_265 = ", ";
  protected final String TEXT_266 = " msgs)" + NL + "\t{";
  protected final String TEXT_267 = NL + "\t\t";
  protected final String TEXT_268 = " old";
  protected final String TEXT_269 = " = ";
  protected final String TEXT_270 = ";" + NL + "\t\t";
  protected final String TEXT_271 = " = new";
  protected final String TEXT_272 = ";";
  protected final String TEXT_273 = NL + "\t\tboolean old";
  protected final String TEXT_274 = "ESet = (";
  protected final String TEXT_275 = " & ";
  protected final String TEXT_276 = "_ESETFLAG) != 0;" + NL + "\t\t";
  protected final String TEXT_277 = " |= ";
  protected final String TEXT_278 = "_ESETFLAG;";
  protected final String TEXT_279 = NL + "\t\tboolean old";
  protected final String TEXT_280 = "ESet = ";
  protected final String TEXT_281 = "ESet;" + NL + "\t\t";
  protected final String TEXT_282 = "ESet = true;";
  protected final String TEXT_283 = NL + "\t\tif (eNotificationRequired())" + NL + "\t\t{";
  protected final String TEXT_284 = NL + "\t\t\t";
  protected final String TEXT_285 = " notification = new ";
  protected final String TEXT_286 = "(this, ";
  protected final String TEXT_287 = ".SET, ";
  protected final String TEXT_288 = ", old";
  protected final String TEXT_289 = ", new";
  protected final String TEXT_290 = ", !old";
  protected final String TEXT_291 = "ESet);";
  protected final String TEXT_292 = NL + "\t\t\t";
  protected final String TEXT_293 = " notification = new ";
  protected final String TEXT_294 = "(this, ";
  protected final String TEXT_295 = ".SET, ";
  protected final String TEXT_296 = ", old";
  protected final String TEXT_297 = ", new";
  protected final String TEXT_298 = ");";
  protected final String TEXT_299 = NL + "\t\t\tif (msgs == null) msgs = notification; else msgs.add(notification);" + NL + "\t\t}" + NL + "\t\treturn msgs;";
  protected final String TEXT_300 = NL + "\t\treturn ((";
  protected final String TEXT_301 = ".Internal)((";
  protected final String TEXT_302 = ")get";
  protected final String TEXT_303 = "()).featureMap()).basicAdd(";
  protected final String TEXT_304 = "(), new";
  protected final String TEXT_305 = ", msgs);";
  protected final String TEXT_306 = NL + "\t\treturn ((";
  protected final String TEXT_307 = ".Internal)get";
  protected final String TEXT_308 = "()).basicAdd(";
  protected final String TEXT_309 = "(), new";
  protected final String TEXT_310 = ", msgs);";
  protected final String TEXT_311 = NL + "\t\t// TODO: implement this method to set the contained '";
  protected final String TEXT_312 = "' ";
  protected final String TEXT_313 = NL + "\t\t// -> this method is automatically invoked to keep the containment relationship in synch" + NL + "\t\t// -> do not modify other features" + NL + "\t\t// -> return msgs, after adding any generated Notification to it (if it is null, a NotificationChain object must be created first)" + NL + "\t\t// Ensure that you remove @generated or mark it @generated NOT" + NL + "\t\tthrow new UnsupportedOperationException();";
  protected final String TEXT_314 = NL + "\t}" + NL;
  protected final String TEXT_315 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */" + NL + "\tpublic void set";
  protected final String TEXT_316 = "(";
  protected final String TEXT_317 = " new";
  protected final String TEXT_318 = ")" + NL + "\t{";
  protected final String TEXT_319 = NL + "\t\teSet(";
  protected final String TEXT_320 = ".eINSTANCE.get";
  protected final String TEXT_321 = "(), ";
  protected final String TEXT_322 = "new ";
  protected final String TEXT_323 = "(";
  protected final String TEXT_324 = "new";
  protected final String TEXT_325 = ")";
  protected final String TEXT_326 = ");";
  protected final String TEXT_327 = NL + "\t\tif (new";
  protected final String TEXT_328 = " != eContainer || (eContainerFeatureID != ";
  protected final String TEXT_329 = " && new";
  protected final String TEXT_330 = " != null))" + NL + "\t\t{" + NL + "\t\t\tif (";
  protected final String TEXT_331 = ".isAncestor(this, ";
  protected final String TEXT_332 = "new";
  protected final String TEXT_333 = "))" + NL + "\t\t\t\tthrow new ";
  protected final String TEXT_334 = "(\"Recursive containment not allowed for \" + toString());";
  protected final String TEXT_335 = NL + "\t\t\t";
  protected final String TEXT_336 = " msgs = null;" + NL + "\t\t\tif (eContainer != null)" + NL + "\t\t\t\tmsgs = eBasicRemoveFromContainer(msgs);" + NL + "\t\t\tif (new";
  protected final String TEXT_337 = " != null)" + NL + "\t\t\t\tmsgs = ((";
  protected final String TEXT_338 = ")new";
  protected final String TEXT_339 = ").eInverseAdd(this, ";
  protected final String TEXT_340 = ", ";
  protected final String TEXT_341 = ".class, msgs);" + NL + "\t\t\tmsgs = eBasicSetContainer((";
  protected final String TEXT_342 = ")new";
  protected final String TEXT_343 = ", ";
  protected final String TEXT_344 = ", msgs);" + NL + "\t\t\tif (msgs != null) msgs.dispatch();" + NL + "\t\t}" + NL + "\t\telse if (eNotificationRequired())" + NL + "\t\t\teNotify(new ";
  protected final String TEXT_345 = "(this, ";
  protected final String TEXT_346 = ".SET, ";
  protected final String TEXT_347 = ", new";
  protected final String TEXT_348 = ", new";
  protected final String TEXT_349 = "));";
  protected final String TEXT_350 = NL + "\t\tif (new";
  protected final String TEXT_351 = " != ";
  protected final String TEXT_352 = ")" + NL + "\t\t{" + NL + "\t\t\t";
  protected final String TEXT_353 = " msgs = null;" + NL + "\t\t\tif (";
  protected final String TEXT_354 = " != null)";
  protected final String TEXT_355 = NL + "\t\t\t\tmsgs = ((";
  protected final String TEXT_356 = ")";
  protected final String TEXT_357 = ").eInverseRemove(this, EOPPOSITE_FEATURE_BASE - ";
  protected final String TEXT_358 = ", null, msgs);" + NL + "\t\t\tif (new";
  protected final String TEXT_359 = " != null)" + NL + "\t\t\t\tmsgs = ((";
  protected final String TEXT_360 = ")new";
  protected final String TEXT_361 = ").eInverseAdd(this, EOPPOSITE_FEATURE_BASE - ";
  protected final String TEXT_362 = ", null, msgs);";
  protected final String TEXT_363 = NL + "\t\t\t\tmsgs = ((";
  protected final String TEXT_364 = ")";
  protected final String TEXT_365 = ").eInverseRemove(this, ";
  protected final String TEXT_366 = ", ";
  protected final String TEXT_367 = ".class, msgs);" + NL + "\t\t\tif (new";
  protected final String TEXT_368 = " != null)" + NL + "\t\t\t\tmsgs = ((";
  protected final String TEXT_369 = ")new";
  protected final String TEXT_370 = ").eInverseAdd(this, ";
  protected final String TEXT_371 = ", ";
  protected final String TEXT_372 = ".class, msgs);";
  protected final String TEXT_373 = NL + "\t\t\tmsgs = basicSet";
  protected final String TEXT_374 = "(";
  protected final String TEXT_375 = "new";
  protected final String TEXT_376 = ", msgs);" + NL + "\t\t\tif (msgs != null) msgs.dispatch();" + NL + "\t\t}";
  protected final String TEXT_377 = NL + "\t\telse" + NL + "    \t{";
  protected final String TEXT_378 = NL + "\t\tboolean old";
  protected final String TEXT_379 = "ESet = (";
  protected final String TEXT_380 = " & ";
  protected final String TEXT_381 = "_ESETFLAG) != 0;" + NL + "\t\t";
  protected final String TEXT_382 = " |= ";
  protected final String TEXT_383 = "_ESETFLAG;";
  protected final String TEXT_384 = NL + "\t\t\tboolean old";
  protected final String TEXT_385 = "ESet = ";
  protected final String TEXT_386 = "ESet;" + NL + "\t\t\t";
  protected final String TEXT_387 = "ESet = true;";
  protected final String TEXT_388 = NL + "\t\t\tif (eNotificationRequired())" + NL + "\t\t\t\teNotify(new ";
  protected final String TEXT_389 = "(this, ";
  protected final String TEXT_390 = ".SET, ";
  protected final String TEXT_391 = ", new";
  protected final String TEXT_392 = ", new";
  protected final String TEXT_393 = ", !old";
  protected final String TEXT_394 = "ESet));" + NL + "    \t}";
  protected final String TEXT_395 = NL + "\t\telse if (eNotificationRequired())" + NL + "\t\t\teNotify(new ";
  protected final String TEXT_396 = "(this, ";
  protected final String TEXT_397 = ".SET, ";
  protected final String TEXT_398 = ", new";
  protected final String TEXT_399 = ", new";
  protected final String TEXT_400 = "));";
  protected final String TEXT_401 = NL + "\t\t";
  protected final String TEXT_402 = " old";
  protected final String TEXT_403 = " = (";
  protected final String TEXT_404 = " & ";
  protected final String TEXT_405 = "_EFLAG) != 0;" + NL + "\t\tif (new";
  protected final String TEXT_406 = ") ";
  protected final String TEXT_407 = " |= ";
  protected final String TEXT_408 = "_EFLAG; else ";
  protected final String TEXT_409 = " &= ~";
  protected final String TEXT_410 = "_EFLAG;";
  protected final String TEXT_411 = NL + "\t\t";
  protected final String TEXT_412 = " old";
  protected final String TEXT_413 = " = ";
  protected final String TEXT_414 = ";";
  protected final String TEXT_415 = NL + "\t\t";
  protected final String TEXT_416 = " = new";
  protected final String TEXT_417 = " == null ? ";
  protected final String TEXT_418 = "_EDEFAULT : new";
  protected final String TEXT_419 = ";";
  protected final String TEXT_420 = NL + "\t\t";
  protected final String TEXT_421 = " = ";
  protected final String TEXT_422 = "new";
  protected final String TEXT_423 = ";";
  protected final String TEXT_424 = NL + "\t\tboolean old";
  protected final String TEXT_425 = "ESet = (";
  protected final String TEXT_426 = " & ";
  protected final String TEXT_427 = "_ESETFLAG) != 0;" + NL + "\t\t";
  protected final String TEXT_428 = " |= ";
  protected final String TEXT_429 = "_ESETFLAG;";
  protected final String TEXT_430 = NL + "\t\tboolean old";
  protected final String TEXT_431 = "ESet = ";
  protected final String TEXT_432 = "ESet;" + NL + "\t\t";
  protected final String TEXT_433 = "ESet = true;";
  protected final String TEXT_434 = NL + "\t\tif (eNotificationRequired())" + NL + "\t\t\teNotify(new ";
  protected final String TEXT_435 = "(this, ";
  protected final String TEXT_436 = ".SET, ";
  protected final String TEXT_437 = ", old";
  protected final String TEXT_438 = ", ";
  protected final String TEXT_439 = "new";
  protected final String TEXT_440 = ", !old";
  protected final String TEXT_441 = "ESet));";
  protected final String TEXT_442 = NL + "\t\tif (eNotificationRequired())" + NL + "\t\t\teNotify(new ";
  protected final String TEXT_443 = "(this, ";
  protected final String TEXT_444 = ".SET, ";
  protected final String TEXT_445 = ", old";
  protected final String TEXT_446 = ", ";
  protected final String TEXT_447 = "new";
  protected final String TEXT_448 = "));";
  protected final String TEXT_449 = NL + "\t\t((";
  protected final String TEXT_450 = ".Internal)((";
  protected final String TEXT_451 = ")get";
  protected final String TEXT_452 = "()).featureMap()).set(";
  protected final String TEXT_453 = "(), ";
  protected final String TEXT_454 = "new ";
  protected final String TEXT_455 = "(";
  protected final String TEXT_456 = "new";
  protected final String TEXT_457 = ")";
  protected final String TEXT_458 = ");";
  protected final String TEXT_459 = NL + "\t\t((";
  protected final String TEXT_460 = ".Internal)get";
  protected final String TEXT_461 = "()).set(";
  protected final String TEXT_462 = "(), ";
  protected final String TEXT_463 = "new ";
  protected final String TEXT_464 = "(";
  protected final String TEXT_465 = "new";
  protected final String TEXT_466 = ")";
  protected final String TEXT_467 = ");";
  protected final String TEXT_468 = NL + "\t\t// TODO: implement this method to set the '";
  protected final String TEXT_469 = "' ";
  protected final String TEXT_470 = NL + "\t\t// Ensure that you remove @generated or mark it @generated NOT" + NL + "\t\tthrow new UnsupportedOperationException();";
  protected final String TEXT_471 = NL + "\t}" + NL;
  protected final String TEXT_472 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */" + NL + "\tpublic ";
  protected final String TEXT_473 = " basicUnset";
  protected final String TEXT_474 = "(";
  protected final String TEXT_475 = " msgs)" + NL + "\t{";
  protected final String TEXT_476 = NL + "\t\t";
  protected final String TEXT_477 = " old";
  protected final String TEXT_478 = " = ";
  protected final String TEXT_479 = ";" + NL + "\t\t";
  protected final String TEXT_480 = " = null;";
  protected final String TEXT_481 = NL + "\t\tboolean old";
  protected final String TEXT_482 = "ESet = (";
  protected final String TEXT_483 = " & ";
  protected final String TEXT_484 = "_ESETFLAG) != 0;" + NL + "\t\t";
  protected final String TEXT_485 = " &= ~";
  protected final String TEXT_486 = "_ESETFLAG;";
  protected final String TEXT_487 = NL + "\t\tboolean old";
  protected final String TEXT_488 = "ESet = ";
  protected final String TEXT_489 = "ESet;" + NL + "\t\t";
  protected final String TEXT_490 = "ESet = false;";
  protected final String TEXT_491 = NL + "\t\tif (eNotificationRequired())" + NL + "\t\t{" + NL + "\t\t\t";
  protected final String TEXT_492 = " notification = new ";
  protected final String TEXT_493 = "(this, ";
  protected final String TEXT_494 = ".UNSET, ";
  protected final String TEXT_495 = ", old";
  protected final String TEXT_496 = ", null, old";
  protected final String TEXT_497 = "ESet);" + NL + "\t\t\tif (msgs == null) msgs = notification; else msgs.add(notification);" + NL + "\t\t}" + NL + "\t\treturn msgs;";
  protected final String TEXT_498 = NL + "\t\t// TODO: implement this method to unset the contained '";
  protected final String TEXT_499 = "' ";
  protected final String TEXT_500 = NL + "\t\t// -> this method is automatically invoked to keep the containment relationship in synch" + NL + "\t\t// -> do not modify other features" + NL + "\t\t// -> return msgs, after adding any generated Notification to it (if it is null, a NotificationChain object must be created first)" + NL + "\t\t// Ensure that you remove @generated or mark it @generated NOT" + NL + "\t\tthrow new UnsupportedOperationException();";
  protected final String TEXT_501 = NL + "\t}" + NL;
  protected final String TEXT_502 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */" + NL + "\tpublic void unset";
  protected final String TEXT_503 = "()" + NL + "\t{";
  protected final String TEXT_504 = NL + "\t\teUnset(";
  protected final String TEXT_505 = ".eINSTANCE.get";
  protected final String TEXT_506 = "());";
  protected final String TEXT_507 = NL + "\t\t((";
  protected final String TEXT_508 = ".Unsettable)get";
  protected final String TEXT_509 = "()).unset();";
  protected final String TEXT_510 = NL + "\t\tif (";
  protected final String TEXT_511 = " != null)" + NL + "\t\t{" + NL + "\t\t\t";
  protected final String TEXT_512 = " msgs = null;";
  protected final String TEXT_513 = NL + "\t\t\tmsgs = ((";
  protected final String TEXT_514 = ")";
  protected final String TEXT_515 = ").eInverseRemove(this, EOPPOSITE_FEATURE_BASE - ";
  protected final String TEXT_516 = ", null, msgs);";
  protected final String TEXT_517 = NL + "\t\t\tmsgs = ((";
  protected final String TEXT_518 = ")";
  protected final String TEXT_519 = ").eInverseRemove(this, ";
  protected final String TEXT_520 = ", ";
  protected final String TEXT_521 = ".class, msgs);";
  protected final String TEXT_522 = NL + "\t\t\tmsgs = basicUnset";
  protected final String TEXT_523 = "(msgs);" + NL + "\t\t\tif (msgs != null) msgs.dispatch();" + NL + "\t\t}" + NL + "\t\telse" + NL + "    \t{";
  protected final String TEXT_524 = NL + "\t\t\tboolean old";
  protected final String TEXT_525 = "ESet = (";
  protected final String TEXT_526 = " & ";
  protected final String TEXT_527 = "_ESETFLAG) != 0;" + NL + "\t\t\t";
  protected final String TEXT_528 = " &= ~";
  protected final String TEXT_529 = "_ESETFLAG;";
  protected final String TEXT_530 = NL + "\t\t\tboolean old";
  protected final String TEXT_531 = "ESet = ";
  protected final String TEXT_532 = "ESet;" + NL + "\t\t\t";
  protected final String TEXT_533 = "ESet = false;";
  protected final String TEXT_534 = NL + "\t\t\tif (eNotificationRequired())" + NL + "\t\t\t\teNotify(new ";
  protected final String TEXT_535 = "(this, ";
  protected final String TEXT_536 = ".UNSET, ";
  protected final String TEXT_537 = ", null, null, old";
  protected final String TEXT_538 = "ESet));" + NL + "    \t}";
  protected final String TEXT_539 = NL + "\t\t";
  protected final String TEXT_540 = " old";
  protected final String TEXT_541 = " = (";
  protected final String TEXT_542 = " & ";
  protected final String TEXT_543 = "_EFLAG) != 0;";
  protected final String TEXT_544 = NL + "\t\t";
  protected final String TEXT_545 = " old";
  protected final String TEXT_546 = " = ";
  protected final String TEXT_547 = ";";
  protected final String TEXT_548 = NL + "\t\tboolean old";
  protected final String TEXT_549 = "ESet = (";
  protected final String TEXT_550 = " & ";
  protected final String TEXT_551 = "_ESETFLAG) != 0;";
  protected final String TEXT_552 = NL + "\t\tboolean old";
  protected final String TEXT_553 = "ESet = ";
  protected final String TEXT_554 = "ESet;";
  protected final String TEXT_555 = NL + "\t\t";
  protected final String TEXT_556 = " = null;";
  protected final String TEXT_557 = NL + "\t\t";
  protected final String TEXT_558 = " &= ~";
  protected final String TEXT_559 = "_ESETFLAG;";
  protected final String TEXT_560 = NL + "\t\t";
  protected final String TEXT_561 = "ESet = false;";
  protected final String TEXT_562 = NL + "\t\tif (eNotificationRequired())" + NL + "\t\t\teNotify(new ";
  protected final String TEXT_563 = "(this, ";
  protected final String TEXT_564 = ".UNSET, ";
  protected final String TEXT_565 = ", old";
  protected final String TEXT_566 = ", null, old";
  protected final String TEXT_567 = "ESet));";
  protected final String TEXT_568 = NL + "\t\tif (";
  protected final String TEXT_569 = "_EDEFAULT) ";
  protected final String TEXT_570 = " |= ";
  protected final String TEXT_571 = "_EFLAG; else ";
  protected final String TEXT_572 = " &= ~";
  protected final String TEXT_573 = "_EFLAG;";
  protected final String TEXT_574 = NL + "\t\t";
  protected final String TEXT_575 = " = ";
  protected final String TEXT_576 = "_EDEFAULT;";
  protected final String TEXT_577 = NL + "\t\t";
  protected final String TEXT_578 = " &= ~";
  protected final String TEXT_579 = "_ESETFLAG;";
  protected final String TEXT_580 = NL + "\t\t";
  protected final String TEXT_581 = "ESet = false;";
  protected final String TEXT_582 = NL + "\t\tif (eNotificationRequired())" + NL + "\t\t\teNotify(new ";
  protected final String TEXT_583 = "(this, ";
  protected final String TEXT_584 = ".UNSET, ";
  protected final String TEXT_585 = ", old";
  protected final String TEXT_586 = ", ";
  protected final String TEXT_587 = "_EDEFAULT, old";
  protected final String TEXT_588 = "ESet));";
  protected final String TEXT_589 = NL + "\t\t((";
  protected final String TEXT_590 = ".Internal)((";
  protected final String TEXT_591 = ")get";
  protected final String TEXT_592 = "()).featureMap()).clear(";
  protected final String TEXT_593 = "());";
  protected final String TEXT_594 = NL + "\t\t((";
  protected final String TEXT_595 = ".Internal)get";
  protected final String TEXT_596 = "()).clear(";
  protected final String TEXT_597 = "());";
  protected final String TEXT_598 = NL + "\t\t// TODO: implement this method to unset the '";
  protected final String TEXT_599 = "' ";
  protected final String TEXT_600 = NL + "\t\t// Ensure that you remove @generated or mark it @generated NOT" + NL + "\t\tthrow new UnsupportedOperationException();";
  protected final String TEXT_601 = NL + "\t}" + NL;
  protected final String TEXT_602 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */" + NL + "\tpublic boolean isSet";
  protected final String TEXT_603 = "()" + NL + "\t{";
  protected final String TEXT_604 = NL + "\t\treturn eIsSet(";
  protected final String TEXT_605 = ".eINSTANCE.get";
  protected final String TEXT_606 = "());";
  protected final String TEXT_607 = NL + "\t\treturn ";
  protected final String TEXT_608 = " != null && ((";
  protected final String TEXT_609 = ".Unsettable)";
  protected final String TEXT_610 = ").isSet();";
  protected final String TEXT_611 = NL + "\t\treturn (";
  protected final String TEXT_612 = " & ";
  protected final String TEXT_613 = "_ESETFLAG) != 0;";
  protected final String TEXT_614 = NL + "\t\treturn ";
  protected final String TEXT_615 = "ESet;";
  protected final String TEXT_616 = NL + "\t\treturn !((";
  protected final String TEXT_617 = ".Internal)((";
  protected final String TEXT_618 = ")get";
  protected final String TEXT_619 = "()).featureMap()).isEmpty(";
  protected final String TEXT_620 = "());";
  protected final String TEXT_621 = NL + "\t\treturn !((";
  protected final String TEXT_622 = ".Internal)get";
  protected final String TEXT_623 = "()).isEmpty(";
  protected final String TEXT_624 = "());";
  protected final String TEXT_625 = NL + "\t\t// TODO: implement this method to return whether the '";
  protected final String TEXT_626 = "' ";
  protected final String TEXT_627 = " is set" + NL + "\t\t// Ensure that you remove @generated or mark it @generated NOT" + NL + "\t\tthrow new UnsupportedOperationException();";
  protected final String TEXT_628 = NL + "\t}" + NL;
  protected final String TEXT_629 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */" + NL + "\tpublic ";
  protected final String TEXT_630 = " ";
  protected final String TEXT_631 = "(";
  protected final String TEXT_632 = ")";
  protected final String TEXT_633 = NL + "\t{";
  protected final String TEXT_634 = NL + "\t\t";
  protected final String TEXT_635 = NL + "\t\t// TODO: implement this method" + NL + "\t\t// -> specify the condition that violates the invariant" + NL + "\t\t// -> verify the details of the diagnostic, including severity and message" + NL + "\t\t// Ensure that you remove @generated or mark it @generated NOT" + NL + "\t\tif (false)" + NL + "\t\t{" + NL + "\t\t\tif (";
  protected final String TEXT_636 = " != null)" + NL + "\t\t\t{" + NL + "\t\t\t\t";
  protected final String TEXT_637 = ".add" + NL + "\t\t\t\t\t(new ";
  protected final String TEXT_638 = NL + "\t\t\t\t\t\t(";
  protected final String TEXT_639 = ".ERROR," + NL + "\t\t\t\t\t\t ";
  protected final String TEXT_640 = ".DIAGNOSTIC_SOURCE," + NL + "\t\t\t\t\t\t ";
  protected final String TEXT_641 = ".";
  protected final String TEXT_642 = "," + NL + "\t\t\t\t\t\t ";
  protected final String TEXT_643 = ".INSTANCE.getString(\"_UI_GenericInvariant_diagnostic\", new Object[] { \"";
  protected final String TEXT_644 = "\", ";
  protected final String TEXT_645 = ".getObjectLabel(this, ";
  protected final String TEXT_646 = ") }),";
  protected final String TEXT_647 = NL + "\t\t\t\t\t\t new Object [] { this }));" + NL + "\t\t\t}" + NL + "\t\t\treturn false;" + NL + "\t\t}" + NL + "\t\treturn true;";
  protected final String TEXT_648 = NL + "\t\t// TODO: implement this method" + NL + "\t\t// Ensure that you remove @generated or mark it @generated NOT" + NL + "\t\tthrow new UnsupportedOperationException();";
  protected final String TEXT_649 = NL + "\t}" + NL;
  protected final String TEXT_650 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */" + NL + "\tpublic ";
  protected final String TEXT_651 = " eInverseAdd(";
  protected final String TEXT_652 = " otherEnd, int featureID, Class baseClass, ";
  protected final String TEXT_653 = " msgs)" + NL + "\t{" + NL + "\t\tif (featureID >= 0)" + NL + "\t\t{" + NL + "\t\t\tswitch (eDerivedStructuralFeatureID(featureID, baseClass))" + NL + "\t\t\t{";
  protected final String TEXT_654 = NL + "\t\t\t\tcase ";
  protected final String TEXT_655 = ":";
  protected final String TEXT_656 = NL + "\t\t\t\t\treturn ((";
  protected final String TEXT_657 = ")((";
  protected final String TEXT_658 = ")";
  protected final String TEXT_659 = "()).eMap()).basicAdd(otherEnd, msgs);";
  protected final String TEXT_660 = NL + "\t\t\t\t\treturn ((";
  protected final String TEXT_661 = ")";
  protected final String TEXT_662 = "()).basicAdd(otherEnd, msgs);";
  protected final String TEXT_663 = NL + "\t\t\t\t\tif (eContainer != null)" + NL + "\t\t\t\t\t\tmsgs = eBasicRemoveFromContainer(msgs);" + NL + "\t\t\t\t\treturn eBasicSetContainer(otherEnd, ";
  protected final String TEXT_664 = ", msgs);";
  protected final String TEXT_665 = NL + "\t\t\t\t\tif (";
  protected final String TEXT_666 = " != null)";
  protected final String TEXT_667 = NL + "\t\t\t\t\t\tmsgs = ((";
  protected final String TEXT_668 = ")";
  protected final String TEXT_669 = ").eInverseRemove(this, EOPPOSITE_FEATURE_BASE - ";
  protected final String TEXT_670 = ", null, msgs);";
  protected final String TEXT_671 = NL + "\t\t\t\t\t\tmsgs = ((";
  protected final String TEXT_672 = ")";
  protected final String TEXT_673 = ").eInverseRemove(this, ";
  protected final String TEXT_674 = ", ";
  protected final String TEXT_675 = ".class, msgs);";
  protected final String TEXT_676 = NL + "\t\t\t\t\treturn basicSet";
  protected final String TEXT_677 = "((";
  protected final String TEXT_678 = ")otherEnd, msgs);";
  protected final String TEXT_679 = NL + "\t\t\t\tdefault:" + NL + "\t\t\t\t\treturn eDynamicInverseAdd(otherEnd, featureID, baseClass, msgs);" + NL + "\t\t\t}" + NL + "\t\t}" + NL + "\t\tif (eContainer != null)" + NL + "\t\t\tmsgs = eBasicRemoveFromContainer(msgs);" + NL + "\t\treturn eBasicSetContainer(otherEnd, featureID, msgs);" + NL + "\t}" + NL;
  protected final String TEXT_680 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */" + NL + "\tpublic ";
  protected final String TEXT_681 = " eInverseRemove(";
  protected final String TEXT_682 = " otherEnd, int featureID, Class baseClass, ";
  protected final String TEXT_683 = " msgs)" + NL + "\t{" + NL + "\t\tif (featureID >= 0)" + NL + "\t\t{" + NL + "\t\t\tswitch (eDerivedStructuralFeatureID(featureID, baseClass))" + NL + "\t\t\t{";
  protected final String TEXT_684 = NL + "\t\t\t\tcase ";
  protected final String TEXT_685 = ":";
  protected final String TEXT_686 = NL + "\t\t\t\t\treturn ((";
  protected final String TEXT_687 = ")((";
  protected final String TEXT_688 = ")";
  protected final String TEXT_689 = "()).eMap()).basicRemove(otherEnd, msgs);";
  protected final String TEXT_690 = NL + "\t\t\t\treturn ((";
  protected final String TEXT_691 = ")((";
  protected final String TEXT_692 = ")";
  protected final String TEXT_693 = "()).featureMap()).basicRemove(otherEnd, msgs);";
  protected final String TEXT_694 = NL + "\t\t\t\t\treturn ((";
  protected final String TEXT_695 = ")";
  protected final String TEXT_696 = "()).basicRemove(otherEnd, msgs);";
  protected final String TEXT_697 = NL + "\t\t\t\t\treturn eBasicSetContainer(null, ";
  protected final String TEXT_698 = ", msgs);";
  protected final String TEXT_699 = NL + "\t\t\t\t\treturn basicUnset";
  protected final String TEXT_700 = "(msgs);";
  protected final String TEXT_701 = NL + "\t\t\t\t\treturn basicSet";
  protected final String TEXT_702 = "(null, msgs);";
  protected final String TEXT_703 = NL + "\t\t\t\tdefault:" + NL + "\t\t\t\t\treturn eDynamicInverseRemove(otherEnd, featureID, baseClass, msgs);" + NL + "\t\t\t}" + NL + "\t\t}" + NL + "\t\treturn eBasicSetContainer(null, featureID, msgs);" + NL + "\t}" + NL;
  protected final String TEXT_704 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */" + NL + "\tpublic ";
  protected final String TEXT_705 = " eBasicRemoveFromContainer(";
  protected final String TEXT_706 = " msgs)" + NL + "\t{" + NL + "\t\tif (eContainerFeatureID >= 0)" + NL + "\t\t{" + NL + "\t\t\tswitch (eContainerFeatureID)" + NL + "\t\t\t{";
  protected final String TEXT_707 = NL + "\t\t\t\tcase ";
  protected final String TEXT_708 = ":" + NL + "\t\t\t\t\treturn eContainer.eInverseRemove(this, ";
  protected final String TEXT_709 = ", ";
  protected final String TEXT_710 = ".class, msgs);";
  protected final String TEXT_711 = NL + "\t\t\t\tdefault:" + NL + "\t\t\t\t\treturn eDynamicBasicRemoveFromContainer(msgs);" + NL + "\t\t\t}" + NL + "\t\t}" + NL + "\t\treturn eContainer.eInverseRemove(this, EOPPOSITE_FEATURE_BASE - eContainerFeatureID, null, msgs);" + NL + "\t}" + NL;
  protected final String TEXT_712 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */" + NL + "\tpublic Object eGet(";
  protected final String TEXT_713 = " eFeature, boolean resolve)" + NL + "\t{" + NL + "\t\tswitch (eDerivedStructuralFeatureID(eFeature))" + NL + "\t\t{";
  protected final String TEXT_714 = NL + "\t\t\tcase ";
  protected final String TEXT_715 = ":";
  protected final String TEXT_716 = NL + "\t\t\t\treturn ";
  protected final String TEXT_717 = "() ? Boolean.TRUE : Boolean.FALSE;";
  protected final String TEXT_718 = NL + "\t\t\t\treturn new ";
  protected final String TEXT_719 = "(";
  protected final String TEXT_720 = "());";
  protected final String TEXT_721 = NL + "\t\t\t\tif (resolve) return ";
  protected final String TEXT_722 = "();" + NL + "\t\t\t\treturn basicGet";
  protected final String TEXT_723 = "();";
  protected final String TEXT_724 = NL + "\t\t\t\treturn ((";
  protected final String TEXT_725 = ")";
  protected final String TEXT_726 = "()).eMap();";
  protected final String TEXT_727 = NL + "\t\t\t\treturn ((";
  protected final String TEXT_728 = ")";
  protected final String TEXT_729 = "()).featureMap();";
  protected final String TEXT_730 = NL + "\t\t\t\treturn ";
  protected final String TEXT_731 = "();";
  protected final String TEXT_732 = NL + "\t\t}" + NL + "\t\treturn eDynamicGet(eFeature, resolve);" + NL + "\t}" + NL;
  protected final String TEXT_733 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */" + NL + "\tpublic void eSet(";
  protected final String TEXT_734 = " eFeature, Object newValue)" + NL + "\t{" + NL + "\t\tswitch (eDerivedStructuralFeatureID(eFeature))" + NL + "\t\t{";
  protected final String TEXT_735 = NL + "\t\t\tcase ";
  protected final String TEXT_736 = ":";
  protected final String TEXT_737 = NL + "\t\t\t\t((";
  protected final String TEXT_738 = ")";
  protected final String TEXT_739 = "()).featureMap().clear();";
  protected final String TEXT_740 = NL + "\t\t\t\t";
  protected final String TEXT_741 = "().clear();";
  protected final String TEXT_742 = NL + "\t\t\t\t((";
  protected final String TEXT_743 = ")";
  protected final String TEXT_744 = "()).featureMap().addAll((";
  protected final String TEXT_745 = ")newValue);";
  protected final String TEXT_746 = NL + "\t\t\t\t((";
  protected final String TEXT_747 = ")";
  protected final String TEXT_748 = "()).eMap().addAll((";
  protected final String TEXT_749 = ")newValue);";
  protected final String TEXT_750 = NL + "\t\t\t\t";
  protected final String TEXT_751 = "().addAll((";
  protected final String TEXT_752 = ")newValue);";
  protected final String TEXT_753 = NL + "\t\t\t\tset";
  protected final String TEXT_754 = "(((";
  protected final String TEXT_755 = ")newValue).";
  protected final String TEXT_756 = "());";
  protected final String TEXT_757 = NL + "\t\t\t\tset";
  protected final String TEXT_758 = "((";
  protected final String TEXT_759 = ")newValue);";
  protected final String TEXT_760 = NL + "\t\t\t\treturn;";
  protected final String TEXT_761 = NL + "\t\t}" + NL + "\t\teDynamicSet(eFeature, newValue);" + NL + "\t}" + NL + "" + NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */" + NL + "\tpublic void eUnset(";
  protected final String TEXT_762 = " eFeature)" + NL + "\t{" + NL + "\t\tswitch (eDerivedStructuralFeatureID(eFeature))" + NL + "\t\t{";
  protected final String TEXT_763 = NL + "\t\t\tcase ";
  protected final String TEXT_764 = ":";
  protected final String TEXT_765 = NL + "\t\t\t\t((";
  protected final String TEXT_766 = ")";
  protected final String TEXT_767 = "()).featureMap().clear();";
  protected final String TEXT_768 = NL + "\t\t\t\t";
  protected final String TEXT_769 = "().clear();";
  protected final String TEXT_770 = NL + "\t\t\t\tunset";
  protected final String TEXT_771 = "();";
  protected final String TEXT_772 = NL + "\t\t\t\tset";
  protected final String TEXT_773 = "((";
  protected final String TEXT_774 = ")null);";
  protected final String TEXT_775 = NL + "\t\t\t\tset";
  protected final String TEXT_776 = "(";
  protected final String TEXT_777 = "_EDEFAULT);";
  protected final String TEXT_778 = NL + "\t\t\t\treturn;";
  protected final String TEXT_779 = NL + "\t\t}" + NL + "\t\teDynamicUnset(eFeature);" + NL + "\t}" + NL;
  protected final String TEXT_780 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */" + NL + "\tpublic boolean eIsSet(";
  protected final String TEXT_781 = " eFeature)" + NL + "\t{" + NL + "\t\tswitch (eDerivedStructuralFeatureID(eFeature))" + NL + "\t\t{";
  protected final String TEXT_782 = NL + "\t\t\tcase ";
  protected final String TEXT_783 = ":";
  protected final String TEXT_784 = NL + "\t\t\t\treturn !((";
  protected final String TEXT_785 = ")";
  protected final String TEXT_786 = "()).featureMap().isEmpty();";
  protected final String TEXT_787 = NL + "\t\t\t\treturn ";
  protected final String TEXT_788 = " != null && !";
  protected final String TEXT_789 = ".featureMap().isEmpty();";
  protected final String TEXT_790 = NL + "\t\t\t\treturn !";
  protected final String TEXT_791 = "().isEmpty();";
  protected final String TEXT_792 = NL + "\t\t\t\treturn ";
  protected final String TEXT_793 = " != null && !";
  protected final String TEXT_794 = ".isEmpty();";
  protected final String TEXT_795 = NL + "\t\t\t\treturn isSet";
  protected final String TEXT_796 = "();";
  protected final String TEXT_797 = NL + "\t\t\t\treturn basicGet";
  protected final String TEXT_798 = "() != null;";
  protected final String TEXT_799 = NL + "\t\t\t\treturn ";
  protected final String TEXT_800 = " != null;";
  protected final String TEXT_801 = NL + "\t\t\t\treturn ";
  protected final String TEXT_802 = "() != null;";
  protected final String TEXT_803 = NL + "\t\t\t\treturn ";
  protected final String TEXT_804 = " != null;";
  protected final String TEXT_805 = NL + "\t\t\t\treturn ";
  protected final String TEXT_806 = "() != ";
  protected final String TEXT_807 = "_EDEFAULT;";
  protected final String TEXT_808 = NL + "\t\t\t\treturn ((";
  protected final String TEXT_809 = " & ";
  protected final String TEXT_810 = "_EFLAG) != 0) != ";
  protected final String TEXT_811 = "_EDEFAULT;";
  protected final String TEXT_812 = NL + "\t\t\t\treturn ";
  protected final String TEXT_813 = " != ";
  protected final String TEXT_814 = "_EDEFAULT;";
  protected final String TEXT_815 = NL + "\t\t\t\treturn ";
  protected final String TEXT_816 = "_EDEFAULT == null ? ";
  protected final String TEXT_817 = "() != null : !";
  protected final String TEXT_818 = "_EDEFAULT.equals(";
  protected final String TEXT_819 = "());";
  protected final String TEXT_820 = NL + "\t\t\t\treturn ";
  protected final String TEXT_821 = "_EDEFAULT == null ? ";
  protected final String TEXT_822 = " != null : !";
  protected final String TEXT_823 = "_EDEFAULT.equals(";
  protected final String TEXT_824 = ");";
  protected final String TEXT_825 = NL + "\t\t}" + NL + "\t\treturn eDynamicIsSet(eFeature);" + NL + "\t}" + NL;
  protected final String TEXT_826 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */" + NL + "\tpublic int eBaseStructuralFeatureID(int derivedFeatureID, Class baseClass)" + NL + "\t{";
  protected final String TEXT_827 = NL + "\t\tif (baseClass == ";
  protected final String TEXT_828 = ".class)" + NL + "\t\t{" + NL + "\t\t\tswitch (derivedFeatureID)" + NL + "\t\t\t{";
  protected final String TEXT_829 = NL + "\t\t\t\tcase ";
  protected final String TEXT_830 = ": return ";
  protected final String TEXT_831 = ";";
  protected final String TEXT_832 = NL + "\t\t\t\tdefault: return -1;" + NL + "\t\t\t}" + NL + "\t\t}";
  protected final String TEXT_833 = NL + "\t\treturn super.eBaseStructuralFeatureID(derivedFeatureID, baseClass);" + NL + "\t}" + NL + "" + NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */" + NL + "\tpublic int eDerivedStructuralFeatureID(int baseFeatureID, Class baseClass)" + NL + "\t{";
  protected final String TEXT_834 = NL + "\t\tif (baseClass == ";
  protected final String TEXT_835 = ".class)" + NL + "\t\t{" + NL + "\t\t\tswitch (baseFeatureID)" + NL + "\t\t\t{";
  protected final String TEXT_836 = NL + "\t\t\t\tcase ";
  protected final String TEXT_837 = ": return ";
  protected final String TEXT_838 = ";";
  protected final String TEXT_839 = NL + "\t\t\t\tdefault: return -1;" + NL + "\t\t\t}" + NL + "\t\t}";
  protected final String TEXT_840 = NL + "\t\treturn super.eDerivedStructuralFeatureID(baseFeatureID, baseClass);" + NL + "\t}" + NL;
  protected final String TEXT_841 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */" + NL + "\tpublic String toString()" + NL + "\t{" + NL + "\t\tif (eIsProxy()) return super.toString();" + NL + "" + NL + "\t\tStringBuffer result = new StringBuffer(super.toString());";
  protected final String TEXT_842 = NL + "\t\tresult.append(\" (";
  protected final String TEXT_843 = ": \");";
  protected final String TEXT_844 = NL + "\t\tresult.append(\", ";
  protected final String TEXT_845 = ": \");";
  protected final String TEXT_846 = NL + "\t\tif (";
  protected final String TEXT_847 = "(";
  protected final String TEXT_848 = " & ";
  protected final String TEXT_849 = "_ESETFLAG) != 0";
  protected final String TEXT_850 = "ESet";
  protected final String TEXT_851 = ") result.append((";
  protected final String TEXT_852 = " & ";
  protected final String TEXT_853 = "_EFLAG) != 0); else result.append(\"<unset>\");";
  protected final String TEXT_854 = NL + "\t\tif (";
  protected final String TEXT_855 = "(";
  protected final String TEXT_856 = " & ";
  protected final String TEXT_857 = "_ESETFLAG) != 0";
  protected final String TEXT_858 = "ESet";
  protected final String TEXT_859 = ") result.append(";
  protected final String TEXT_860 = "); else result.append(\"<unset>\");";
  protected final String TEXT_861 = NL + "\t\tresult.append((";
  protected final String TEXT_862 = " & ";
  protected final String TEXT_863 = "_EFLAG) != 0);";
  protected final String TEXT_864 = NL + "\t\tresult.append(";
  protected final String TEXT_865 = ");";
  protected final String TEXT_866 = NL + "\t\tresult.append(')');" + NL + "\t\treturn result.toString();" + NL + "\t}" + NL;
  protected final String TEXT_867 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */" + NL + "\tprotected int hash = -1;" + NL + "" + NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */" + NL + "\tpublic int getHash()" + NL + "\t{" + NL + "\t\tif (hash == -1)" + NL + "\t\t{" + NL + "\t\t\tObject theKey = getKey();" + NL + "\t\t\thash = (theKey == null ? 0 : theKey.hashCode());" + NL + "\t\t}" + NL + "\t\treturn hash;" + NL + "\t}" + NL + "" + NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */" + NL + "\tpublic void setHash(int hash)" + NL + "\t{" + NL + "\t\tthis.hash = hash;" + NL + "\t}" + NL + "" + NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */" + NL + "\tpublic Object getKey()" + NL + "\t{";
  protected final String TEXT_868 = NL + "\t\treturn new ";
  protected final String TEXT_869 = "(getTypedKey());";
  protected final String TEXT_870 = NL + "\t\treturn getTypedKey();";
  protected final String TEXT_871 = NL + "\t}" + NL + "" + NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */" + NL + "\tpublic void setKey(Object key)" + NL + "\t{";
  protected final String TEXT_872 = NL + "\t\tgetTypedKey().addAll((";
  protected final String TEXT_873 = ")key);";
  protected final String TEXT_874 = NL + "\t\tsetTypedKey(((";
  protected final String TEXT_875 = ")key).";
  protected final String TEXT_876 = "());";
  protected final String TEXT_877 = NL + "\t\tsetTypedKey((";
  protected final String TEXT_878 = ")key);";
  protected final String TEXT_879 = NL + "\t}" + NL + "" + NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */" + NL + "\tpublic Object getValue()" + NL + "\t{";
  protected final String TEXT_880 = NL + "\t\treturn new ";
  protected final String TEXT_881 = "(getTypedValue());";
  protected final String TEXT_882 = NL + "\t\treturn getTypedValue();";
  protected final String TEXT_883 = NL + "\t}" + NL + "" + NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */" + NL + "\tpublic Object setValue(Object value)" + NL + "\t{" + NL + "\t\tObject oldValue = getValue();";
  protected final String TEXT_884 = NL + "\t\tgetTypedValue().clear();" + NL + "\t\tgetTypedValue().addAll((";
  protected final String TEXT_885 = ")value);";
  protected final String TEXT_886 = NL + "\t\tsetTypedValue(((";
  protected final String TEXT_887 = ")value).";
  protected final String TEXT_888 = "());";
  protected final String TEXT_889 = NL + "\t\tsetTypedValue((";
  protected final String TEXT_890 = ")value);";
  protected final String TEXT_891 = NL + "\t\treturn oldValue;" + NL + "\t}" + NL + "" + NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */" + NL + "\tpublic ";
  protected final String TEXT_892 = " getEMap()" + NL + "\t{" + NL + "\t\t";
  protected final String TEXT_893 = " container = eContainer();" + NL + "\t\treturn container == null ? null : (";
  protected final String TEXT_894 = ")container.eGet(eContainmentFeature());" + NL + "\t}" + NL;
  protected final String TEXT_895 = NL + "} //";
  protected final String TEXT_896 = NL;

  public String generate(Object argument)
  {
    StringBuffer stringBuffer = new StringBuffer();
    
/**
 * <copyright>
 *
 * Copyright (c) 2002-2005 IBM Corporation and others.
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
    if ((genClass.getClassExtendsGenClass() == null || genClass.getClassExtendsGenClass().getGenModel() != genModel) && genModel.isBooleanFlagsEnabled() && genModel.getBooleanFlagsReservedBits() == -1) {
    stringBuffer.append(TEXT_26);
    stringBuffer.append(genModel.getBooleanFlagsField());
    stringBuffer.append(TEXT_27);
    }
    if (!genModel.isReflectiveDelegation()) {
    for (Iterator i=genClass.getImplementedGenFeatures().iterator(); i.hasNext();) { GenFeature genFeature = (GenFeature)i.next();
    if (!genFeature.isContainer()) {
    if (genFeature.isListType() || genFeature.isReferenceType()) {
    if (!genFeature.isVolatile()) {
    stringBuffer.append(TEXT_28);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_29);
    stringBuffer.append(genFeature.getFormattedName());
    stringBuffer.append(TEXT_30);
    stringBuffer.append(genFeature.getFeatureKind());
    stringBuffer.append(TEXT_31);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_32);
    stringBuffer.append(genFeature.getImportedInternalType());
    stringBuffer.append(TEXT_33);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_34);
    }
    } else {
    if (!genFeature.isVolatile() || !genModel.isReflectiveDelegation() && (!genFeature.hasDelegateFeature() || !genFeature.isUnsettable())) {
    stringBuffer.append(TEXT_35);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_36);
    stringBuffer.append(genFeature.getFormattedName());
    stringBuffer.append(TEXT_37);
    stringBuffer.append(genFeature.getFeatureKind());
    stringBuffer.append(TEXT_38);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_39);
    stringBuffer.append(genFeature.getImportedType());
    stringBuffer.append(TEXT_40);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_41);
    stringBuffer.append(genFeature.getStaticDefaultValue());
    stringBuffer.append(TEXT_42);
    stringBuffer.append(genModel.getNonNLS(genFeature.getStaticDefaultValue()));
    stringBuffer.append(TEXT_43);
    }
    if (!genFeature.isVolatile()) {
    if (genFeature.isFlag()) {
    if (genClass.getFlagIndex(genFeature) > 31 && genClass.getFlagIndex(genFeature) % 32 == 0) {
    stringBuffer.append(TEXT_44);
    stringBuffer.append(genClass.getFlagsField(genFeature));
    stringBuffer.append(TEXT_45);
    }
    stringBuffer.append(TEXT_46);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_47);
    stringBuffer.append(genFeature.getFormattedName());
    stringBuffer.append(TEXT_48);
    stringBuffer.append(genFeature.getFeatureKind());
    stringBuffer.append(TEXT_49);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_50);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_51);
    stringBuffer.append("<< " + genClass.getFlagIndex(genFeature) % 32 );
    stringBuffer.append(TEXT_52);
    } else {
    stringBuffer.append(TEXT_53);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_54);
    stringBuffer.append(genFeature.getFormattedName());
    stringBuffer.append(TEXT_55);
    stringBuffer.append(genFeature.getFeatureKind());
    stringBuffer.append(TEXT_56);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_57);
    stringBuffer.append(genFeature.getImportedType());
    stringBuffer.append(TEXT_58);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_59);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_60);
    }
    }
    }
    if (!genFeature.isListType() && genFeature.isUnsettable() && !genFeature.isVolatile()) {
    if (genFeature.isESetFlag()) {
    if (genClass.getESetFlagIndex(genFeature) > 31 && genClass.getESetFlagIndex(genFeature) % 32 == 0) {
    stringBuffer.append(TEXT_61);
    stringBuffer.append(genClass.getESetFlagsField(genFeature));
    stringBuffer.append(TEXT_62);
    }
    stringBuffer.append(TEXT_63);
    stringBuffer.append(genFeature.getFormattedName());
    stringBuffer.append(TEXT_64);
    stringBuffer.append(genFeature.getFeatureKind());
    stringBuffer.append(TEXT_65);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_66);
    stringBuffer.append("<< " + genClass.getESetFlagIndex(genFeature) % 32 );
    stringBuffer.append(TEXT_67);
    } else {
    stringBuffer.append(TEXT_68);
    stringBuffer.append(genFeature.getFormattedName());
    stringBuffer.append(TEXT_69);
    stringBuffer.append(genFeature.getFeatureKind());
    stringBuffer.append(TEXT_70);
    stringBuffer.append(genFeature.getUncapName());
    stringBuffer.append(TEXT_71);
    }
    }
    }
    }
    }
    stringBuffer.append(TEXT_72);
    stringBuffer.append(genClass.getClassName());
    stringBuffer.append(TEXT_73);
    for (Iterator i=genClass.getFlagGenFeatures("true").iterator(); i.hasNext();) { GenFeature genFeature = (GenFeature)i.next();
    stringBuffer.append(TEXT_74);
    stringBuffer.append(genClass.getFlagsField(genFeature));
    stringBuffer.append(TEXT_75);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_76);
    }
    stringBuffer.append(TEXT_77);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.EClass"));
    stringBuffer.append(TEXT_78);
    stringBuffer.append(genPackage.getImportedPackageInterfaceName());
    stringBuffer.append(TEXT_79);
    stringBuffer.append(genClass.getClassifierAccessorName());
    stringBuffer.append(TEXT_80);
    for (Iterator i=genClass.getImplementedGenFeatures().iterator(); i.hasNext();) { GenFeature genFeature = (GenFeature)i.next();
    stringBuffer.append(TEXT_81);
    stringBuffer.append(genFeature.getImportedType());
    stringBuffer.append(TEXT_82);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_83);
    if (genModel.isReflectiveDelegation()) {
    stringBuffer.append(TEXT_84);
    if (genFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_85);
    }
    stringBuffer.append(TEXT_86);
    stringBuffer.append(genFeature.getObjectType());
    stringBuffer.append(TEXT_87);
    stringBuffer.append(genFeature.getGenPackage().getImportedPackageInterfaceName());
    stringBuffer.append(TEXT_88);
    stringBuffer.append(genFeature.getFeatureAccessorName());
    stringBuffer.append(TEXT_89);
    if (genFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_90);
    stringBuffer.append(genFeature.getPrimitiveValueFunction());
    stringBuffer.append(TEXT_91);
    }
    stringBuffer.append(TEXT_92);
    } else if (!genFeature.isVolatile()) {
    if (genFeature.isListType()) { String unsettable = genFeature.isUnsettable() ? ".Unsettable" : ""; 
    stringBuffer.append(TEXT_93);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_94);
    if (genFeature.isMapType()) { GenClass mapGenClass = genFeature.getMapGenClass(); GenPackage mapGenPackage = mapGenClass.getGenPackage(); 
    stringBuffer.append(TEXT_95);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_96);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.EcoreEMap"));
    stringBuffer.append(TEXT_97);
    stringBuffer.append(mapGenPackage.getImportedPackageInterfaceName());
    stringBuffer.append(TEXT_98);
    stringBuffer.append(mapGenClass.getName());
    stringBuffer.append(TEXT_99);
    stringBuffer.append(genFeature.getMapItemType());
    stringBuffer.append(TEXT_100);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(TEXT_101);
    } else if (genFeature.isContains()) {
    if (genFeature.isBidirectional()) { GenFeature reverseFeature = genFeature.getReverse();
    stringBuffer.append(TEXT_102);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_103);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.EObjectContainmentWithInverseEList"));
    stringBuffer.append(unsettable);
    stringBuffer.append(TEXT_104);
    stringBuffer.append(genFeature.getListItemType());
    stringBuffer.append(TEXT_105);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(TEXT_106);
    stringBuffer.append(reverseFeature.getGenClass().getQualifiedFeatureID(reverseFeature));
    stringBuffer.append(TEXT_107);
    } else {
    stringBuffer.append(TEXT_108);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_109);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.EObjectContainmentEList"));
    stringBuffer.append(unsettable);
    stringBuffer.append(TEXT_110);
    stringBuffer.append(genFeature.getListItemType());
    stringBuffer.append(TEXT_111);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(TEXT_112);
    }
    } else if (genFeature.isReferenceType()) {
    if (genFeature.isBidirectional()) { GenFeature reverseFeature = genFeature.getReverse();
    if (reverseFeature.isListType()) {
    if (genFeature.isResolveProxies()) {
    stringBuffer.append(TEXT_113);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_114);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.EObjectWithInverseResolvingEList"));
    stringBuffer.append(unsettable);
    stringBuffer.append(TEXT_115);
    stringBuffer.append(genFeature.getListItemType());
    stringBuffer.append(TEXT_116);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(TEXT_117);
    stringBuffer.append(reverseFeature.getGenClass().getQualifiedFeatureID(reverseFeature));
    stringBuffer.append(TEXT_118);
    } else {
    stringBuffer.append(TEXT_119);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_120);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.EObjectWithInverseEList"));
    stringBuffer.append(unsettable);
    stringBuffer.append(TEXT_121);
    stringBuffer.append(genFeature.getListItemType());
    stringBuffer.append(TEXT_122);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(TEXT_123);
    stringBuffer.append(reverseFeature.getGenClass().getQualifiedFeatureID(reverseFeature));
    stringBuffer.append(TEXT_124);
    }
    } else {
    if (genFeature.isResolveProxies()) {
    stringBuffer.append(TEXT_125);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_126);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.EObjectWithInverseResolvingEList"));
    stringBuffer.append(unsettable);
    stringBuffer.append(TEXT_127);
    stringBuffer.append(genFeature.getListItemType());
    stringBuffer.append(TEXT_128);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(TEXT_129);
    stringBuffer.append(reverseFeature.getGenClass().getQualifiedFeatureID(reverseFeature));
    stringBuffer.append(TEXT_130);
    } else {
    stringBuffer.append(TEXT_131);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_132);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.EObjectWithInverseEList"));
    stringBuffer.append(unsettable);
    stringBuffer.append(TEXT_133);
    stringBuffer.append(genFeature.getListItemType());
    stringBuffer.append(TEXT_134);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(TEXT_135);
    stringBuffer.append(reverseFeature.getGenClass().getQualifiedFeatureID(reverseFeature));
    stringBuffer.append(TEXT_136);
    }
    }
    } else {
    if (genFeature.isResolveProxies()) {
    stringBuffer.append(TEXT_137);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_138);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.EObjectResolvingEList"));
    stringBuffer.append(unsettable);
    stringBuffer.append(TEXT_139);
    stringBuffer.append(genFeature.getListItemType());
    stringBuffer.append(TEXT_140);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(TEXT_141);
    } else {
    stringBuffer.append(TEXT_142);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_143);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.EObjectEList"));
    stringBuffer.append(unsettable);
    stringBuffer.append(TEXT_144);
    stringBuffer.append(genFeature.getListItemType());
    stringBuffer.append(TEXT_145);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(TEXT_146);
    }
    }
    } else if (genFeature.isFeatureMapType()) {
    if (genFeature.isFeatureMapWrapped()) {
    stringBuffer.append(TEXT_147);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_148);
    stringBuffer.append(genFeature.getImportedEffectiveFeatureMapWrapperClass());
    stringBuffer.append(TEXT_149);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.BasicFeatureMap"));
    stringBuffer.append(TEXT_150);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(TEXT_151);
    } else {
    stringBuffer.append(TEXT_152);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_153);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.BasicFeatureMap"));
    stringBuffer.append(TEXT_154);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(TEXT_155);
    }
    } else {//datatype
    if (genFeature.isUnique()) {
    stringBuffer.append(TEXT_156);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_157);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.EDataTypeUniqueEList"));
    stringBuffer.append(unsettable);
    stringBuffer.append(TEXT_158);
    stringBuffer.append(genFeature.getListItemType());
    stringBuffer.append(TEXT_159);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(TEXT_160);
    } else {
    stringBuffer.append(TEXT_161);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_162);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.EDataTypeEList"));
    stringBuffer.append(unsettable);
    stringBuffer.append(TEXT_163);
    stringBuffer.append(genFeature.getListItemType());
    stringBuffer.append(TEXT_164);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(TEXT_165);
    }
    }
    stringBuffer.append(TEXT_166);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(genFeature.isMapType() && genFeature.isEffectiveSuppressEMFTypes() ? ".map()" : "");
    stringBuffer.append(TEXT_167);
    } else if (genFeature.isContainer()) {
    stringBuffer.append(TEXT_168);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(TEXT_169);
    stringBuffer.append(genFeature.getImportedType());
    stringBuffer.append(TEXT_170);
    } else {
    if (genFeature.isResolveProxies()) {
    stringBuffer.append(TEXT_171);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_172);
    stringBuffer.append(genFeature.getSafeNameAsEObject());
    stringBuffer.append(TEXT_173);
    stringBuffer.append(genFeature.getImportedType());
    stringBuffer.append(TEXT_174);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_175);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_176);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_177);
    stringBuffer.append(genFeature.getImportedInternalType());
    stringBuffer.append(TEXT_178);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.InternalEObject"));
    stringBuffer.append(TEXT_179);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_180);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_181);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_182);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.impl.ENotificationImpl"));
    stringBuffer.append(TEXT_183);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.notify.Notification"));
    stringBuffer.append(TEXT_184);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(TEXT_185);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_186);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_187);
    }
    if (genFeature.isFlag()) {
    stringBuffer.append(TEXT_188);
    stringBuffer.append(genClass.getFlagsField(genFeature));
    stringBuffer.append(TEXT_189);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_190);
    } else {
    stringBuffer.append(TEXT_191);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_192);
    }
    }
    } else {//volatile
    if (genFeature.isResolveProxies() && !genFeature.isListType()) {
    stringBuffer.append(TEXT_193);
    stringBuffer.append(genFeature.getImportedType());
    stringBuffer.append(TEXT_194);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_195);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_196);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_197);
    stringBuffer.append(genFeature.getImportedInternalType());
    stringBuffer.append(TEXT_198);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.InternalEObject"));
    stringBuffer.append(TEXT_199);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_200);
    } else if (genFeature.hasDelegateFeature()) { GenFeature delegateFeature = genFeature.getDelegateFeature();
    if (genFeature.isFeatureMapType()) {
    if (delegateFeature.isFeatureMapWrapped()) {
    stringBuffer.append(TEXT_201);
    stringBuffer.append(genFeature.getImportedEffectiveFeatureMapWrapperClass());
    stringBuffer.append(TEXT_202);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.FeatureMap$Internal"));
    stringBuffer.append(TEXT_203);
    stringBuffer.append(delegateFeature.getImportedEffectiveFeatureMapWrapperInternalInterface());
    stringBuffer.append(TEXT_204);
    stringBuffer.append(delegateFeature.getAccessorName());
    stringBuffer.append(TEXT_205);
    stringBuffer.append(genFeature.getQualifiedFeatureAccessorName());
    stringBuffer.append(TEXT_206);
    } else {
    stringBuffer.append(TEXT_207);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.FeatureMap"));
    stringBuffer.append(TEXT_208);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.FeatureMap"));
    stringBuffer.append(TEXT_209);
    stringBuffer.append(delegateFeature.getAccessorName());
    stringBuffer.append(TEXT_210);
    stringBuffer.append(genFeature.getQualifiedFeatureAccessorName());
    stringBuffer.append(TEXT_211);
    }
    } else if (genFeature.isListType()) {
    if (delegateFeature.isFeatureMapWrapped()) {
    stringBuffer.append(TEXT_212);
    stringBuffer.append(delegateFeature.getImportedEffectiveFeatureMapWrapperInternalInterface());
    stringBuffer.append(TEXT_213);
    stringBuffer.append(delegateFeature.getAccessorName());
    stringBuffer.append(TEXT_214);
    stringBuffer.append(genFeature.getQualifiedFeatureAccessorName());
    stringBuffer.append(TEXT_215);
    } else {
    stringBuffer.append(TEXT_216);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.FeatureMap"));
    stringBuffer.append(TEXT_217);
    stringBuffer.append(delegateFeature.getAccessorName());
    stringBuffer.append(TEXT_218);
    stringBuffer.append(genFeature.getQualifiedFeatureAccessorName());
    stringBuffer.append(TEXT_219);
    }
    } else {
    if (delegateFeature.isFeatureMapWrapped()) {
    stringBuffer.append(TEXT_220);
    if (genFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_221);
    }
    stringBuffer.append(TEXT_222);
    stringBuffer.append(genFeature.getObjectType());
    stringBuffer.append(TEXT_223);
    stringBuffer.append(delegateFeature.getImportedEffectiveFeatureMapWrapperInternalInterface());
    stringBuffer.append(TEXT_224);
    stringBuffer.append(delegateFeature.getAccessorName());
    stringBuffer.append(TEXT_225);
    stringBuffer.append(genFeature.getQualifiedFeatureAccessorName());
    stringBuffer.append(TEXT_226);
    if (genFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_227);
    stringBuffer.append(genFeature.getPrimitiveValueFunction());
    stringBuffer.append(TEXT_228);
    }
    stringBuffer.append(TEXT_229);
    } else {
    stringBuffer.append(TEXT_230);
    if (genFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_231);
    }
    stringBuffer.append(TEXT_232);
    stringBuffer.append(genFeature.getObjectType());
    stringBuffer.append(TEXT_233);
    stringBuffer.append(delegateFeature.getAccessorName());
    stringBuffer.append(TEXT_234);
    stringBuffer.append(genFeature.getQualifiedFeatureAccessorName());
    stringBuffer.append(TEXT_235);
    if (genFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_236);
    stringBuffer.append(genFeature.getPrimitiveValueFunction());
    stringBuffer.append(TEXT_237);
    }
    stringBuffer.append(TEXT_238);
    }
    }
    } else {
    stringBuffer.append(TEXT_239);
    stringBuffer.append(genFeature.getFormattedName());
    stringBuffer.append(TEXT_240);
    stringBuffer.append(genFeature.getFeatureKind());
    stringBuffer.append(TEXT_241);
    }
    }
    stringBuffer.append(TEXT_242);
    if (!genModel.isReflectiveDelegation() && genFeature.isResolveProxies() && !genFeature.isListType()) {
    stringBuffer.append(TEXT_243);
    stringBuffer.append(genFeature.getImportedType());
    stringBuffer.append(TEXT_244);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_245);
    if (!genFeature.isVolatile()) {
    stringBuffer.append(TEXT_246);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_247);
    } else if (genFeature.hasDelegateFeature()) { GenFeature delegateFeature = genFeature.getDelegateFeature();
    if (delegateFeature.isFeatureMapWrapped()) {
    stringBuffer.append(TEXT_248);
    stringBuffer.append(genFeature.getImportedType());
    stringBuffer.append(TEXT_249);
    stringBuffer.append(delegateFeature.getImportedEffectiveFeatureMapWrapperInternalInterface());
    stringBuffer.append(TEXT_250);
    stringBuffer.append(delegateFeature.getAccessorName());
    stringBuffer.append(TEXT_251);
    stringBuffer.append(genFeature.getQualifiedFeatureAccessorName());
    stringBuffer.append(TEXT_252);
    } else {
    stringBuffer.append(TEXT_253);
    stringBuffer.append(genFeature.getImportedType());
    stringBuffer.append(TEXT_254);
    stringBuffer.append(delegateFeature.getAccessorName());
    stringBuffer.append(TEXT_255);
    stringBuffer.append(genFeature.getQualifiedFeatureAccessorName());
    stringBuffer.append(TEXT_256);
    }
    } else {
    stringBuffer.append(TEXT_257);
    stringBuffer.append(genFeature.getFormattedName());
    stringBuffer.append(TEXT_258);
    stringBuffer.append(genFeature.getFeatureKind());
    stringBuffer.append(TEXT_259);
    }
    stringBuffer.append(TEXT_260);
    }
    if (!genFeature.isListType()) {
    if (!genModel.isReflectiveDelegation() && (genFeature.isBidirectional() && !genFeature.isContainer() && !genFeature.isVolatile() || genFeature.isContains())) {
    stringBuffer.append(TEXT_261);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.notify.NotificationChain"));
    stringBuffer.append(TEXT_262);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_263);
    stringBuffer.append(genFeature.getImportedInternalType());
    stringBuffer.append(TEXT_264);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_265);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.notify.NotificationChain"));
    stringBuffer.append(TEXT_266);
    if (!genFeature.isVolatile()) {
    stringBuffer.append(TEXT_267);
    stringBuffer.append(genFeature.getImportedType());
    stringBuffer.append(TEXT_268);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_269);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_270);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_271);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_272);
    if (genFeature.isUnsettable()) {
    if (genFeature.isESetFlag()) {
    stringBuffer.append(TEXT_273);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_274);
    stringBuffer.append(genClass.getESetFlagsField(genFeature));
    stringBuffer.append(TEXT_275);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_276);
    stringBuffer.append(genClass.getESetFlagsField(genFeature));
    stringBuffer.append(TEXT_277);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_278);
    } else {
    stringBuffer.append(TEXT_279);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_280);
    stringBuffer.append(genFeature.getUncapName());
    stringBuffer.append(TEXT_281);
    stringBuffer.append(genFeature.getUncapName());
    stringBuffer.append(TEXT_282);
    }
    }
    stringBuffer.append(TEXT_283);
    if (genFeature.isUnsettable()) {
    stringBuffer.append(TEXT_284);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.impl.ENotificationImpl"));
    stringBuffer.append(TEXT_285);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.impl.ENotificationImpl"));
    stringBuffer.append(TEXT_286);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.notify.Notification"));
    stringBuffer.append(TEXT_287);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(TEXT_288);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_289);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_290);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_291);
    } else {
    stringBuffer.append(TEXT_292);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.impl.ENotificationImpl"));
    stringBuffer.append(TEXT_293);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.impl.ENotificationImpl"));
    stringBuffer.append(TEXT_294);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.notify.Notification"));
    stringBuffer.append(TEXT_295);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(TEXT_296);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_297);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_298);
    }
    stringBuffer.append(TEXT_299);
    } else if (genFeature.hasDelegateFeature()) { GenFeature delegateFeature = genFeature.getDelegateFeature();
    if (delegateFeature.isFeatureMapWrapped()) {
    stringBuffer.append(TEXT_300);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.FeatureMap"));
    stringBuffer.append(TEXT_301);
    stringBuffer.append(delegateFeature.getImportedEffectiveFeatureMapWrapperInternalInterface());
    stringBuffer.append(TEXT_302);
    stringBuffer.append(delegateFeature.getAccessorName());
    stringBuffer.append(TEXT_303);
    stringBuffer.append(genFeature.getQualifiedFeatureAccessorName());
    stringBuffer.append(TEXT_304);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_305);
    } else {
    stringBuffer.append(TEXT_306);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.FeatureMap"));
    stringBuffer.append(TEXT_307);
    stringBuffer.append(delegateFeature.getAccessorName());
    stringBuffer.append(TEXT_308);
    stringBuffer.append(genFeature.getQualifiedFeatureAccessorName());
    stringBuffer.append(TEXT_309);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_310);
    }
    } else {
    stringBuffer.append(TEXT_311);
    stringBuffer.append(genFeature.getFormattedName());
    stringBuffer.append(TEXT_312);
    stringBuffer.append(genFeature.getFeatureKind());
    stringBuffer.append(TEXT_313);
    }
    stringBuffer.append(TEXT_314);
    }
    if (genFeature.isChangeable()) {
    stringBuffer.append(TEXT_315);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_316);
    stringBuffer.append(genFeature.getImportedType());
    stringBuffer.append(TEXT_317);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_318);
    if (genModel.isReflectiveDelegation()) {
    stringBuffer.append(TEXT_319);
    stringBuffer.append(genFeature.getGenPackage().getImportedPackageInterfaceName());
    stringBuffer.append(TEXT_320);
    stringBuffer.append(genFeature.getFeatureAccessorName());
    stringBuffer.append(TEXT_321);
    if (genFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_322);
    stringBuffer.append(genFeature.getObjectType());
    stringBuffer.append(TEXT_323);
    }
    stringBuffer.append(TEXT_324);
    stringBuffer.append(genFeature.getCapName());
    if (genFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_325);
    }
    stringBuffer.append(TEXT_326);
    } else if (!genFeature.isVolatile()) {
    if (genFeature.isContainer()) { GenFeature reverseFeature = genFeature.getReverse(); GenClass targetClass = reverseFeature.getGenClass();
    stringBuffer.append(TEXT_327);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_328);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(TEXT_329);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_330);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.EcoreUtil"));
    stringBuffer.append(TEXT_331);
    stringBuffer.append(genFeature.getEObjectCast());
    stringBuffer.append(TEXT_332);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_333);
    stringBuffer.append(genModel.getImportedName("java.lang.IllegalArgumentException"));
    stringBuffer.append(TEXT_334);
    stringBuffer.append(genModel.getNonNLS());
    stringBuffer.append(TEXT_335);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.notify.NotificationChain"));
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
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.InternalEObject"));
    stringBuffer.append(TEXT_342);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_343);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(TEXT_344);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.impl.ENotificationImpl"));
    stringBuffer.append(TEXT_345);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.notify.Notification"));
    stringBuffer.append(TEXT_346);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(TEXT_347);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_348);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_349);
    } else if (genFeature.isBidirectional() || genFeature.isContains()) {
    stringBuffer.append(TEXT_350);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_351);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_352);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.notify.NotificationChain"));
    stringBuffer.append(TEXT_353);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_354);
    if (!genFeature.isBidirectional()) {
    stringBuffer.append(TEXT_355);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.InternalEObject"));
    stringBuffer.append(TEXT_356);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_357);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(TEXT_358);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_359);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.InternalEObject"));
    stringBuffer.append(TEXT_360);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_361);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(TEXT_362);
    } else { GenFeature reverseFeature = genFeature.getReverse(); GenClass targetClass = reverseFeature.getGenClass();
    stringBuffer.append(TEXT_363);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.InternalEObject"));
    stringBuffer.append(TEXT_364);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_365);
    stringBuffer.append(targetClass.getQualifiedFeatureID(reverseFeature));
    stringBuffer.append(TEXT_366);
    stringBuffer.append(targetClass.getImportedInterfaceName());
    stringBuffer.append(TEXT_367);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_368);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.InternalEObject"));
    stringBuffer.append(TEXT_369);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_370);
    stringBuffer.append(targetClass.getQualifiedFeatureID(reverseFeature));
    stringBuffer.append(TEXT_371);
    stringBuffer.append(targetClass.getImportedInterfaceName());
    stringBuffer.append(TEXT_372);
    }
    stringBuffer.append(TEXT_373);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_374);
    stringBuffer.append(genFeature.getInternalTypeCast());
    stringBuffer.append(TEXT_375);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_376);
    if (genFeature.isUnsettable()) {
    stringBuffer.append(TEXT_377);
    if (genFeature.isESetFlag()) {
    stringBuffer.append(TEXT_378);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_379);
    stringBuffer.append(genClass.getESetFlagsField(genFeature));
    stringBuffer.append(TEXT_380);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_381);
    stringBuffer.append(genClass.getESetFlagsField(genFeature));
    stringBuffer.append(TEXT_382);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_383);
    } else {
    stringBuffer.append(TEXT_384);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_385);
    stringBuffer.append(genFeature.getUncapName());
    stringBuffer.append(TEXT_386);
    stringBuffer.append(genFeature.getUncapName());
    stringBuffer.append(TEXT_387);
    }
    stringBuffer.append(TEXT_388);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.impl.ENotificationImpl"));
    stringBuffer.append(TEXT_389);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.notify.Notification"));
    stringBuffer.append(TEXT_390);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(TEXT_391);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_392);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_393);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_394);
    } else {
    stringBuffer.append(TEXT_395);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.impl.ENotificationImpl"));
    stringBuffer.append(TEXT_396);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.notify.Notification"));
    stringBuffer.append(TEXT_397);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(TEXT_398);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_399);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_400);
    }
    } else {
    if (genFeature.isFlag()) {
    stringBuffer.append(TEXT_401);
    stringBuffer.append(genFeature.getImportedType());
    stringBuffer.append(TEXT_402);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_403);
    stringBuffer.append(genClass.getFlagsField(genFeature));
    stringBuffer.append(TEXT_404);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_405);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_406);
    stringBuffer.append(genClass.getFlagsField(genFeature));
    stringBuffer.append(TEXT_407);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_408);
    stringBuffer.append(genClass.getFlagsField(genFeature));
    stringBuffer.append(TEXT_409);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_410);
    } else {
    stringBuffer.append(TEXT_411);
    stringBuffer.append(genFeature.getImportedType());
    stringBuffer.append(TEXT_412);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_413);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_414);
    if (genFeature.isEnumType()) {
    stringBuffer.append(TEXT_415);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_416);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_417);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_418);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_419);
    } else {
    stringBuffer.append(TEXT_420);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_421);
    stringBuffer.append(genFeature.getInternalTypeCast());
    stringBuffer.append(TEXT_422);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_423);
    }
    }
    if (genFeature.isUnsettable()) {
    if (genFeature.isESetFlag()) {
    stringBuffer.append(TEXT_424);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_425);
    stringBuffer.append(genClass.getESetFlagsField(genFeature));
    stringBuffer.append(TEXT_426);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_427);
    stringBuffer.append(genClass.getESetFlagsField(genFeature));
    stringBuffer.append(TEXT_428);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_429);
    } else {
    stringBuffer.append(TEXT_430);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_431);
    stringBuffer.append(genFeature.getUncapName());
    stringBuffer.append(TEXT_432);
    stringBuffer.append(genFeature.getUncapName());
    stringBuffer.append(TEXT_433);
    }
    stringBuffer.append(TEXT_434);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.impl.ENotificationImpl"));
    stringBuffer.append(TEXT_435);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.notify.Notification"));
    stringBuffer.append(TEXT_436);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(TEXT_437);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_438);
    if (genFeature.isFlag()) {
    stringBuffer.append(TEXT_439);
    stringBuffer.append(genFeature.getCapName());
    } else {
    stringBuffer.append(genFeature.getSafeName());
    }
    stringBuffer.append(TEXT_440);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_441);
    } else {
    stringBuffer.append(TEXT_442);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.impl.ENotificationImpl"));
    stringBuffer.append(TEXT_443);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.notify.Notification"));
    stringBuffer.append(TEXT_444);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(TEXT_445);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_446);
    if (genFeature.isFlag()) {
    stringBuffer.append(TEXT_447);
    stringBuffer.append(genFeature.getCapName());
    } else {
    stringBuffer.append(genFeature.getSafeName());
    }
    stringBuffer.append(TEXT_448);
    }
    }
    } else if (genFeature.hasDelegateFeature()) { GenFeature delegateFeature = genFeature.getDelegateFeature();
    if (delegateFeature.isFeatureMapWrapped()) {
    stringBuffer.append(TEXT_449);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.FeatureMap"));
    stringBuffer.append(TEXT_450);
    stringBuffer.append(delegateFeature.getImportedEffectiveFeatureMapWrapperInternalInterface());
    stringBuffer.append(TEXT_451);
    stringBuffer.append(delegateFeature.getAccessorName());
    stringBuffer.append(TEXT_452);
    stringBuffer.append(genFeature.getQualifiedFeatureAccessorName());
    stringBuffer.append(TEXT_453);
    if (genFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_454);
    stringBuffer.append(genFeature.getObjectType());
    stringBuffer.append(TEXT_455);
    }
    stringBuffer.append(TEXT_456);
    stringBuffer.append(genFeature.getCapName());
    if (genFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_457);
    }
    stringBuffer.append(TEXT_458);
    } else {
    stringBuffer.append(TEXT_459);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.FeatureMap"));
    stringBuffer.append(TEXT_460);
    stringBuffer.append(delegateFeature.getAccessorName());
    stringBuffer.append(TEXT_461);
    stringBuffer.append(genFeature.getQualifiedFeatureAccessorName());
    stringBuffer.append(TEXT_462);
    if (genFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_463);
    stringBuffer.append(genFeature.getObjectType());
    stringBuffer.append(TEXT_464);
    }
    stringBuffer.append(TEXT_465);
    stringBuffer.append(genFeature.getCapName());
    if (genFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_466);
    }
    stringBuffer.append(TEXT_467);
    }
    } else {
    stringBuffer.append(TEXT_468);
    stringBuffer.append(genFeature.getFormattedName());
    stringBuffer.append(TEXT_469);
    stringBuffer.append(genFeature.getFeatureKind());
    stringBuffer.append(TEXT_470);
    }
    stringBuffer.append(TEXT_471);
    }//if (genFeature.isChangeable())
    }//if (!genFeature.isListType())
    if (genFeature.isUnsettable()) {
    if (genFeature.isChangeable()) {
    if (!genFeature.isListType() && genFeature.isReferenceType() && (genFeature.isBidirectional() || genFeature.isContains())) {
    stringBuffer.append(TEXT_472);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.notify.NotificationChain"));
    stringBuffer.append(TEXT_473);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_474);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.notify.NotificationChain"));
    stringBuffer.append(TEXT_475);
    if (!genFeature.isVolatile()) {
    stringBuffer.append(TEXT_476);
    stringBuffer.append(genFeature.getImportedType());
    stringBuffer.append(TEXT_477);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_478);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_479);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_480);
    if (genFeature.isESetFlag()) {
    stringBuffer.append(TEXT_481);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_482);
    stringBuffer.append(genClass.getESetFlagsField(genFeature));
    stringBuffer.append(TEXT_483);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_484);
    stringBuffer.append(genClass.getESetFlagsField(genFeature));
    stringBuffer.append(TEXT_485);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_486);
    } else {
    stringBuffer.append(TEXT_487);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_488);
    stringBuffer.append(genFeature.getUncapName());
    stringBuffer.append(TEXT_489);
    stringBuffer.append(genFeature.getUncapName());
    stringBuffer.append(TEXT_490);
    }
    stringBuffer.append(TEXT_491);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.impl.ENotificationImpl"));
    stringBuffer.append(TEXT_492);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.impl.ENotificationImpl"));
    stringBuffer.append(TEXT_493);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.notify.Notification"));
    stringBuffer.append(TEXT_494);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(TEXT_495);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_496);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_497);
    } else {
    stringBuffer.append(TEXT_498);
    stringBuffer.append(genFeature.getFormattedName());
    stringBuffer.append(TEXT_499);
    stringBuffer.append(genFeature.getFeatureKind());
    stringBuffer.append(TEXT_500);
    }
    stringBuffer.append(TEXT_501);
    }
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
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.InternalEList"));
    stringBuffer.append(TEXT_508);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_509);
    } else if (genFeature.isBidirectional() || genFeature.isContains()) {
    stringBuffer.append(TEXT_510);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_511);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.notify.NotificationChain"));
    stringBuffer.append(TEXT_512);
    if (!genFeature.isBidirectional()) {
    stringBuffer.append(TEXT_513);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.InternalEObject"));
    stringBuffer.append(TEXT_514);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_515);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(TEXT_516);
    } else { GenFeature reverseFeature = genFeature.getReverse(); GenClass targetClass = reverseFeature.getGenClass();
    stringBuffer.append(TEXT_517);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.InternalEObject"));
    stringBuffer.append(TEXT_518);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_519);
    stringBuffer.append(targetClass.getQualifiedFeatureID(reverseFeature));
    stringBuffer.append(TEXT_520);
    stringBuffer.append(targetClass.getImportedInterfaceName());
    stringBuffer.append(TEXT_521);
    }
    stringBuffer.append(TEXT_522);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_523);
    if (genFeature.isESetFlag()) {
    stringBuffer.append(TEXT_524);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_525);
    stringBuffer.append(genClass.getESetFlagsField(genFeature));
    stringBuffer.append(TEXT_526);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_527);
    stringBuffer.append(genClass.getESetFlagsField(genFeature));
    stringBuffer.append(TEXT_528);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_529);
    } else {
    stringBuffer.append(TEXT_530);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_531);
    stringBuffer.append(genFeature.getUncapName());
    stringBuffer.append(TEXT_532);
    stringBuffer.append(genFeature.getUncapName());
    stringBuffer.append(TEXT_533);
    }
    stringBuffer.append(TEXT_534);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.impl.ENotificationImpl"));
    stringBuffer.append(TEXT_535);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.notify.Notification"));
    stringBuffer.append(TEXT_536);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(TEXT_537);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_538);
    } else {
    if (genFeature.isFlag()) {
    stringBuffer.append(TEXT_539);
    stringBuffer.append(genFeature.getImportedType());
    stringBuffer.append(TEXT_540);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_541);
    stringBuffer.append(genClass.getFlagsField(genFeature));
    stringBuffer.append(TEXT_542);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_543);
    } else {
    stringBuffer.append(TEXT_544);
    stringBuffer.append(genFeature.getImportedType());
    stringBuffer.append(TEXT_545);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_546);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_547);
    }
    if (genFeature.isESetFlag()) {
    stringBuffer.append(TEXT_548);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_549);
    stringBuffer.append(genClass.getESetFlagsField(genFeature));
    stringBuffer.append(TEXT_550);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_551);
    } else {
    stringBuffer.append(TEXT_552);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_553);
    stringBuffer.append(genFeature.getUncapName());
    stringBuffer.append(TEXT_554);
    }
    if (genFeature.isReferenceType()) {
    stringBuffer.append(TEXT_555);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_556);
    if (genFeature.isESetFlag()) {
    stringBuffer.append(TEXT_557);
    stringBuffer.append(genClass.getESetFlagsField(genFeature));
    stringBuffer.append(TEXT_558);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_559);
    } else {
    stringBuffer.append(TEXT_560);
    stringBuffer.append(genFeature.getUncapName());
    stringBuffer.append(TEXT_561);
    }
    stringBuffer.append(TEXT_562);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.impl.ENotificationImpl"));
    stringBuffer.append(TEXT_563);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.notify.Notification"));
    stringBuffer.append(TEXT_564);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(TEXT_565);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_566);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_567);
    } else {
    if (genFeature.isFlag()) {
    stringBuffer.append(TEXT_568);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_569);
    stringBuffer.append(genClass.getFlagsField(genFeature));
    stringBuffer.append(TEXT_570);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_571);
    stringBuffer.append(genClass.getFlagsField(genFeature));
    stringBuffer.append(TEXT_572);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_573);
    } else {
    stringBuffer.append(TEXT_574);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_575);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_576);
    }
    if (genFeature.isESetFlag()) {
    stringBuffer.append(TEXT_577);
    stringBuffer.append(genClass.getESetFlagsField(genFeature));
    stringBuffer.append(TEXT_578);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_579);
    } else {
    stringBuffer.append(TEXT_580);
    stringBuffer.append(genFeature.getUncapName());
    stringBuffer.append(TEXT_581);
    }
    stringBuffer.append(TEXT_582);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.impl.ENotificationImpl"));
    stringBuffer.append(TEXT_583);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.notify.Notification"));
    stringBuffer.append(TEXT_584);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(TEXT_585);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_586);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_587);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_588);
    }
    }
    } else if (genFeature.hasDelegateFeature()) { GenFeature delegateFeature = genFeature.getDelegateFeature();
    if (delegateFeature.isFeatureMapWrapped()) {
    stringBuffer.append(TEXT_589);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.FeatureMap"));
    stringBuffer.append(TEXT_590);
    stringBuffer.append(delegateFeature.getImportedEffectiveFeatureMapWrapperInternalInterface());
    stringBuffer.append(TEXT_591);
    stringBuffer.append(delegateFeature.getAccessorName());
    stringBuffer.append(TEXT_592);
    stringBuffer.append(genFeature.getQualifiedFeatureAccessorName());
    stringBuffer.append(TEXT_593);
    } else {
    stringBuffer.append(TEXT_594);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.FeatureMap"));
    stringBuffer.append(TEXT_595);
    stringBuffer.append(delegateFeature.getAccessorName());
    stringBuffer.append(TEXT_596);
    stringBuffer.append(genFeature.getQualifiedFeatureAccessorName());
    stringBuffer.append(TEXT_597);
    }
    } else {
    stringBuffer.append(TEXT_598);
    stringBuffer.append(genFeature.getFormattedName());
    stringBuffer.append(TEXT_599);
    stringBuffer.append(genFeature.getFeatureKind());
    stringBuffer.append(TEXT_600);
    }
    stringBuffer.append(TEXT_601);
    }//if (genFeature.isChangeable())
    stringBuffer.append(TEXT_602);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_603);
    if (genModel.isReflectiveDelegation()) {
    stringBuffer.append(TEXT_604);
    stringBuffer.append(genFeature.getGenPackage().getImportedPackageInterfaceName());
    stringBuffer.append(TEXT_605);
    stringBuffer.append(genFeature.getFeatureAccessorName());
    stringBuffer.append(TEXT_606);
    } else if (!genFeature.isVolatile()) {
    if (genFeature.isListType()) {
    stringBuffer.append(TEXT_607);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_608);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.InternalEList"));
    stringBuffer.append(TEXT_609);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_610);
    } else {
    if (genFeature.isESetFlag()) {
    stringBuffer.append(TEXT_611);
    stringBuffer.append(genClass.getESetFlagsField(genFeature));
    stringBuffer.append(TEXT_612);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_613);
    } else {
    stringBuffer.append(TEXT_614);
    stringBuffer.append(genFeature.getUncapName());
    stringBuffer.append(TEXT_615);
    }
    }
    } else if (genFeature.hasDelegateFeature()) { GenFeature delegateFeature = genFeature.getDelegateFeature();
    if (delegateFeature.isFeatureMapWrapped()) {
    stringBuffer.append(TEXT_616);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.FeatureMap"));
    stringBuffer.append(TEXT_617);
    stringBuffer.append(delegateFeature.getImportedEffectiveFeatureMapWrapperInternalInterface());
    stringBuffer.append(TEXT_618);
    stringBuffer.append(delegateFeature.getAccessorName());
    stringBuffer.append(TEXT_619);
    stringBuffer.append(genFeature.getQualifiedFeatureAccessorName());
    stringBuffer.append(TEXT_620);
    } else {
    stringBuffer.append(TEXT_621);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.FeatureMap"));
    stringBuffer.append(TEXT_622);
    stringBuffer.append(delegateFeature.getAccessorName());
    stringBuffer.append(TEXT_623);
    stringBuffer.append(genFeature.getQualifiedFeatureAccessorName());
    stringBuffer.append(TEXT_624);
    }
    } else {
    stringBuffer.append(TEXT_625);
    stringBuffer.append(genFeature.getFormattedName());
    stringBuffer.append(TEXT_626);
    stringBuffer.append(genFeature.getFeatureKind());
    stringBuffer.append(TEXT_627);
    }
    stringBuffer.append(TEXT_628);
    }
    }//for
    for (Iterator i=genClass.getImplementedGenOperations().iterator(); i.hasNext();) { GenOperation genOperation = (GenOperation)i.next();
    stringBuffer.append(TEXT_629);
    stringBuffer.append(genOperation.getImportedReturnType());
    stringBuffer.append(TEXT_630);
    stringBuffer.append(genOperation.getName());
    stringBuffer.append(TEXT_631);
    stringBuffer.append(genOperation.getParameters());
    stringBuffer.append(TEXT_632);
    stringBuffer.append(genOperation.getThrows());
    stringBuffer.append(TEXT_633);
    if (genOperation.hasBody()) {
    stringBuffer.append(TEXT_634);
    stringBuffer.append(genOperation.getBody(genModel.getIndentation(stringBuffer)));
    } else if (genOperation.isInvariant()) {GenClass opClass = genOperation.getGenClass(); String diagnostics = ((GenParameter)genOperation.getGenParameters().get(0)).getName(); String context = ((GenParameter)genOperation.getGenParameters().get(1)).getName();
    stringBuffer.append(TEXT_635);
    stringBuffer.append(diagnostics);
    stringBuffer.append(TEXT_636);
    stringBuffer.append(diagnostics);
    stringBuffer.append(TEXT_637);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.util.BasicDiagnostic"));
    stringBuffer.append(TEXT_638);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.util.Diagnostic"));
    stringBuffer.append(TEXT_639);
    stringBuffer.append(opClass.getGenPackage().getImportedValidatorClassName());
    stringBuffer.append(TEXT_640);
    stringBuffer.append(opClass.getGenPackage().getImportedValidatorClassName());
    stringBuffer.append(TEXT_641);
    stringBuffer.append(opClass.getOperationID(genOperation));
    stringBuffer.append(TEXT_642);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.plugin.EcorePlugin"));
    stringBuffer.append(TEXT_643);
    stringBuffer.append(genOperation.getName());
    stringBuffer.append(TEXT_644);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.EObjectValidator"));
    stringBuffer.append(TEXT_645);
    stringBuffer.append(context);
    stringBuffer.append(TEXT_646);
    stringBuffer.append(genModel.getNonNLS());
    stringBuffer.append(genModel.getNonNLS(2));
    stringBuffer.append(TEXT_647);
    } else {
    stringBuffer.append(TEXT_648);
    }
    stringBuffer.append(TEXT_649);
    }//for
    if (!genModel.isReflectiveDelegation() && !genClass.getEInverseAddGenFeatures().isEmpty()) {
    stringBuffer.append(TEXT_650);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.notify.NotificationChain"));
    stringBuffer.append(TEXT_651);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.InternalEObject"));
    stringBuffer.append(TEXT_652);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.notify.NotificationChain"));
    stringBuffer.append(TEXT_653);
    for (Iterator i=genClass.getEInverseAddGenFeatures().iterator(); i.hasNext();) { GenFeature genFeature = (GenFeature)i.next();
    stringBuffer.append(TEXT_654);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(TEXT_655);
    if (genFeature.isListType()) {
    if (genFeature.isMapType() && genFeature.isEffectiveSuppressEMFTypes()) {
    stringBuffer.append(TEXT_656);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.InternalEList"));
    stringBuffer.append(TEXT_657);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.util.EMap$InternalMapView"));
    stringBuffer.append(TEXT_658);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_659);
    } else {
    stringBuffer.append(TEXT_660);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.InternalEList"));
    stringBuffer.append(TEXT_661);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_662);
    }
    } else if (genFeature.isContainer()) {
    stringBuffer.append(TEXT_663);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(TEXT_664);
    } else {
    stringBuffer.append(TEXT_665);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_666);
    if (genFeature.isContains()) {
    stringBuffer.append(TEXT_667);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.InternalEObject"));
    stringBuffer.append(TEXT_668);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_669);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(TEXT_670);
    } else { GenFeature reverseFeature = genFeature.getReverse(); GenClass targetClass = reverseFeature.getGenClass();
    stringBuffer.append(TEXT_671);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.InternalEObject"));
    stringBuffer.append(TEXT_672);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_673);
    stringBuffer.append(targetClass.getQualifiedFeatureID(reverseFeature));
    stringBuffer.append(TEXT_674);
    stringBuffer.append(targetClass.getImportedInterfaceName());
    stringBuffer.append(TEXT_675);
    }
    stringBuffer.append(TEXT_676);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_677);
    stringBuffer.append(genFeature.getImportedType());
    stringBuffer.append(TEXT_678);
    }
    }
    stringBuffer.append(TEXT_679);
    }
    if (!genModel.isReflectiveDelegation() && !genClass.getEInverseRemoveGenFeatures().isEmpty()) {
    stringBuffer.append(TEXT_680);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.notify.NotificationChain"));
    stringBuffer.append(TEXT_681);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.InternalEObject"));
    stringBuffer.append(TEXT_682);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.notify.NotificationChain"));
    stringBuffer.append(TEXT_683);
    for (Iterator i=genClass.getEInverseRemoveGenFeatures().iterator(); i.hasNext();) { GenFeature genFeature = (GenFeature)i.next();
    stringBuffer.append(TEXT_684);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(TEXT_685);
    if (genFeature.isListType()) {
    if (genFeature.isMapType() && genFeature.isEffectiveSuppressEMFTypes()) {
    stringBuffer.append(TEXT_686);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.InternalEList"));
    stringBuffer.append(TEXT_687);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.util.EMap$InternalMapView"));
    stringBuffer.append(TEXT_688);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_689);
    } else if (genFeature.isFeatureMapWrapped()) {
    stringBuffer.append(TEXT_690);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.InternalEList"));
    stringBuffer.append(TEXT_691);
    stringBuffer.append(genFeature.getImportedEffectiveFeatureMapWrapperInternalInterface());
    stringBuffer.append(TEXT_692);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_693);
    } else {
    stringBuffer.append(TEXT_694);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.InternalEList"));
    stringBuffer.append(TEXT_695);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_696);
    }
    } else if (genFeature.isContainer()) {
    stringBuffer.append(TEXT_697);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(TEXT_698);
    } else if (genFeature.isUnsettable()) {
    stringBuffer.append(TEXT_699);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_700);
    } else {
    stringBuffer.append(TEXT_701);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_702);
    }
    }
    stringBuffer.append(TEXT_703);
    }
    if (!genModel.isReflectiveDelegation() && !genClass.getEBasicRemoveFromContainerGenFeatures().isEmpty()) {
    stringBuffer.append(TEXT_704);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.notify.NotificationChain"));
    stringBuffer.append(TEXT_705);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.notify.NotificationChain"));
    stringBuffer.append(TEXT_706);
    for (Iterator i=genClass.getEBasicRemoveFromContainerGenFeatures().iterator(); i.hasNext();) { GenFeature genFeature = (GenFeature)i.next();
    GenFeature reverseFeature = genFeature.getReverse(); GenClass targetClass = reverseFeature.getGenClass();
    stringBuffer.append(TEXT_707);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(TEXT_708);
    stringBuffer.append(targetClass.getQualifiedFeatureID(reverseFeature));
    stringBuffer.append(TEXT_709);
    stringBuffer.append(targetClass.getImportedInterfaceName());
    stringBuffer.append(TEXT_710);
    }
    stringBuffer.append(TEXT_711);
    }
    if (!genModel.isReflectiveDelegation() && !genClass.getAllGenFeatures().isEmpty()) {
    stringBuffer.append(TEXT_712);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.EStructuralFeature"));
    stringBuffer.append(TEXT_713);
    for (Iterator i=genClass.getAllGenFeatures().iterator(); i.hasNext();) { GenFeature genFeature = (GenFeature)i.next();
    stringBuffer.append(TEXT_714);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(TEXT_715);
    if (genFeature.isPrimitiveType() && !genFeature.isListType()) {
    if (genFeature.isBooleanType()) {
    stringBuffer.append(TEXT_716);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_717);
    } else {
    stringBuffer.append(TEXT_718);
    stringBuffer.append(genFeature.getObjectType());
    stringBuffer.append(TEXT_719);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_720);
    }
    } else if (genFeature.isResolveProxies() && !genFeature.isListType()) {
    stringBuffer.append(TEXT_721);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_722);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_723);
    } else if (genFeature.isMapType() && genFeature.isEffectiveSuppressEMFTypes()) {
    stringBuffer.append(TEXT_724);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.util.EMap$InternalMapView"));
    stringBuffer.append(TEXT_725);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_726);
    } else if (genFeature.isFeatureMapWrapped()) {
    stringBuffer.append(TEXT_727);
    stringBuffer.append(genFeature.getImportedEffectiveFeatureMapWrapperInternalInterface());
    stringBuffer.append(TEXT_728);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_729);
    } else {
    stringBuffer.append(TEXT_730);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_731);
    }
    }
    stringBuffer.append(TEXT_732);
    }
    if (!genModel.isReflectiveDelegation() && !genClass.getESetGenFeatures().isEmpty()) {
    stringBuffer.append(TEXT_733);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.EStructuralFeature"));
    stringBuffer.append(TEXT_734);
    for (Iterator i=genClass.getESetGenFeatures().iterator(); i.hasNext();) { GenFeature genFeature = (GenFeature)i.next();
    stringBuffer.append(TEXT_735);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(TEXT_736);
    if (genFeature.isListType()) {
    if (genFeature.isFeatureMapWrapped()) {
    stringBuffer.append(TEXT_737);
    stringBuffer.append(genFeature.getImportedEffectiveFeatureMapWrapperInternalInterface());
    stringBuffer.append(TEXT_738);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_739);
    } else {
    stringBuffer.append(TEXT_740);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_741);
    }
    if (genFeature.isFeatureMapWrapped()) {
    stringBuffer.append(TEXT_742);
    stringBuffer.append(genFeature.getImportedEffectiveFeatureMapWrapperInternalInterface());
    stringBuffer.append(TEXT_743);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_744);
    stringBuffer.append(genModel.getImportedName("java.util.Collection"));
    stringBuffer.append(TEXT_745);
    } else if (genFeature.isMapType() && genFeature.isEffectiveSuppressEMFTypes()) {
    stringBuffer.append(TEXT_746);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.util.EMap$InternalMapView"));
    stringBuffer.append(TEXT_747);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_748);
    stringBuffer.append(genModel.getImportedName("java.util.Collection"));
    stringBuffer.append(TEXT_749);
    } else {
    stringBuffer.append(TEXT_750);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_751);
    stringBuffer.append(genModel.getImportedName("java.util.Collection"));
    stringBuffer.append(TEXT_752);
    }
    } else if (genFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_753);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_754);
    stringBuffer.append(genFeature.getObjectType());
    stringBuffer.append(TEXT_755);
    stringBuffer.append(genFeature.getPrimitiveValueFunction());
    stringBuffer.append(TEXT_756);
    } else {
    stringBuffer.append(TEXT_757);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_758);
    stringBuffer.append(genFeature.getImportedType());
    stringBuffer.append(TEXT_759);
    }
    stringBuffer.append(TEXT_760);
    }
    stringBuffer.append(TEXT_761);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.EStructuralFeature"));
    stringBuffer.append(TEXT_762);
    for (Iterator i=genClass.getESetGenFeatures().iterator(); i.hasNext();) { GenFeature genFeature = (GenFeature)i.next();
    stringBuffer.append(TEXT_763);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(TEXT_764);
    if (genFeature.isListType() && !genFeature.isUnsettable()) {
    if (genFeature.isFeatureMapWrapped()) {
    stringBuffer.append(TEXT_765);
    stringBuffer.append(genFeature.getImportedEffectiveFeatureMapWrapperInternalInterface());
    stringBuffer.append(TEXT_766);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_767);
    } else {
    stringBuffer.append(TEXT_768);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_769);
    }
    } else if (genFeature.isUnsettable()) {
    stringBuffer.append(TEXT_770);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_771);
    } else if (genFeature.isReferenceType()) {
    stringBuffer.append(TEXT_772);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_773);
    stringBuffer.append(genFeature.getImportedType());
    stringBuffer.append(TEXT_774);
    } else {
    stringBuffer.append(TEXT_775);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_776);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_777);
    }
    stringBuffer.append(TEXT_778);
    }
    stringBuffer.append(TEXT_779);
    }
    if (!genModel.isReflectiveDelegation() && !genClass.getAllGenFeatures().isEmpty()) {
    stringBuffer.append(TEXT_780);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.EStructuralFeature"));
    stringBuffer.append(TEXT_781);
    for (Iterator i=genClass.getAllGenFeatures().iterator(); i.hasNext();) { GenFeature genFeature = (GenFeature)i.next();
    stringBuffer.append(TEXT_782);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(TEXT_783);
    if (genFeature.isListType() && !genFeature.isUnsettable()) {
    if (genFeature.isFeatureMapWrapped()) {
    if (genFeature.isVolatile()) {
    stringBuffer.append(TEXT_784);
    stringBuffer.append(genFeature.getImportedEffectiveFeatureMapWrapperInternalInterface());
    stringBuffer.append(TEXT_785);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_786);
    } else {
    stringBuffer.append(TEXT_787);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_788);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_789);
    }
    } else {
    if (genFeature.isVolatile()) {
    stringBuffer.append(TEXT_790);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_791);
    } else {
    stringBuffer.append(TEXT_792);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_793);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_794);
    }
    }
    } else if (genFeature.isUnsettable()) {
    stringBuffer.append(TEXT_795);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_796);
    } else if (genFeature.isResolveProxies()) {
    if (genFeature.isVolatile()) {
    stringBuffer.append(TEXT_797);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_798);
    } else {
    stringBuffer.append(TEXT_799);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_800);
    }
    } else if (genFeature.isReferenceType()) {
    if (genFeature.isVolatile() || genFeature.isContainer()) {
    stringBuffer.append(TEXT_801);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_802);
    } else {
    stringBuffer.append(TEXT_803);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_804);
    }
    } else if (genFeature.isPrimitiveType() || genFeature.isEnumType()) {
    if (genFeature.isVolatile()) {
    stringBuffer.append(TEXT_805);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_806);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_807);
    } else {
    if (genFeature.isFlag()) {
    stringBuffer.append(TEXT_808);
    stringBuffer.append(genClass.getFlagsField(genFeature));
    stringBuffer.append(TEXT_809);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_810);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_811);
    } else {
    stringBuffer.append(TEXT_812);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_813);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_814);
    }
    }
    } else {//datatype
    if (genFeature.isVolatile()) {
    stringBuffer.append(TEXT_815);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_816);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_817);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_818);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_819);
    } else {
    stringBuffer.append(TEXT_820);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_821);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_822);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_823);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_824);
    }
    }
    }
    stringBuffer.append(TEXT_825);
    }
    if (!genClass.getMixinGenFeatures().isEmpty()) {
    stringBuffer.append(TEXT_826);
    for (Iterator m=genClass.getMixinGenClasses().iterator(); m.hasNext();) { GenClass mixinGenClass = (GenClass)m.next(); 
    stringBuffer.append(TEXT_827);
    stringBuffer.append(mixinGenClass.getImportedInterfaceName());
    stringBuffer.append(TEXT_828);
    for (Iterator f=mixinGenClass.getGenFeatures().iterator(); f.hasNext();) { GenFeature genFeature = (GenFeature)f.next(); 
    stringBuffer.append(TEXT_829);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(TEXT_830);
    stringBuffer.append(mixinGenClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(TEXT_831);
    }
    stringBuffer.append(TEXT_832);
    }
    stringBuffer.append(TEXT_833);
    for (Iterator m=genClass.getMixinGenClasses().iterator(); m.hasNext();) { GenClass mixinGenClass = (GenClass)m.next(); 
    stringBuffer.append(TEXT_834);
    stringBuffer.append(mixinGenClass.getImportedInterfaceName());
    stringBuffer.append(TEXT_835);
    for (Iterator f=mixinGenClass.getGenFeatures().iterator(); f.hasNext();) { GenFeature genFeature = (GenFeature)f.next(); 
    stringBuffer.append(TEXT_836);
    stringBuffer.append(mixinGenClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(TEXT_837);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(TEXT_838);
    }
    stringBuffer.append(TEXT_839);
    }
    stringBuffer.append(TEXT_840);
    }
    if (!genModel.isReflectiveDelegation() && !genClass.getToStringGenFeatures().isEmpty()) {
    stringBuffer.append(TEXT_841);
    { boolean first = true;
    for (Iterator i=genClass.getToStringGenFeatures().iterator(); i.hasNext(); ) { GenFeature genFeature = (GenFeature)i.next();
    if (first) { first = false;
    stringBuffer.append(TEXT_842);
    stringBuffer.append(genFeature.getName());
    stringBuffer.append(TEXT_843);
    stringBuffer.append(genModel.getNonNLS());
    } else {
    stringBuffer.append(TEXT_844);
    stringBuffer.append(genFeature.getName());
    stringBuffer.append(TEXT_845);
    stringBuffer.append(genModel.getNonNLS());
    }
    if (genFeature.isUnsettable() && !genFeature.isListType()) {
    if (genFeature.isFlag()) {
    stringBuffer.append(TEXT_846);
    if (genFeature.isESetFlag()) {
    stringBuffer.append(TEXT_847);
    stringBuffer.append(genClass.getESetFlagsField(genFeature));
    stringBuffer.append(TEXT_848);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_849);
    } else {
    stringBuffer.append(genFeature.getUncapName());
    stringBuffer.append(TEXT_850);
    }
    stringBuffer.append(TEXT_851);
    stringBuffer.append(genClass.getFlagsField(genFeature));
    stringBuffer.append(TEXT_852);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_853);
    stringBuffer.append(genModel.getNonNLS());
    } else {
    stringBuffer.append(TEXT_854);
    if (genFeature.isESetFlag()) {
    stringBuffer.append(TEXT_855);
    stringBuffer.append(genClass.getESetFlagsField(genFeature));
    stringBuffer.append(TEXT_856);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_857);
    } else {
    stringBuffer.append(genFeature.getUncapName());
    stringBuffer.append(TEXT_858);
    }
    stringBuffer.append(TEXT_859);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_860);
    stringBuffer.append(genModel.getNonNLS());
    }
    } else {
    if (genFeature.isFlag()) {
    stringBuffer.append(TEXT_861);
    stringBuffer.append(genClass.getFlagsField(genFeature));
    stringBuffer.append(TEXT_862);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_863);
    } else {
    stringBuffer.append(TEXT_864);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_865);
    }
    }
    }
    }
    stringBuffer.append(TEXT_866);
    }
    if (genClass.isMapEntry()) { GenFeature keyFeature = genClass.getMapEntryKeyFeature(); GenFeature valueFeature = genClass.getMapEntryValueFeature();
    stringBuffer.append(TEXT_867);
    if (keyFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_868);
    stringBuffer.append(keyFeature.getObjectType());
    stringBuffer.append(TEXT_869);
    } else {
    stringBuffer.append(TEXT_870);
    }
    stringBuffer.append(TEXT_871);
    if (keyFeature.isListType()) {
    stringBuffer.append(TEXT_872);
    stringBuffer.append(genModel.getImportedName("java.util.Collection"));
    stringBuffer.append(TEXT_873);
    } else if (keyFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_874);
    stringBuffer.append(keyFeature.getObjectType());
    stringBuffer.append(TEXT_875);
    stringBuffer.append(keyFeature.getPrimitiveValueFunction());
    stringBuffer.append(TEXT_876);
    } else {
    stringBuffer.append(TEXT_877);
    stringBuffer.append(keyFeature.getImportedType());
    stringBuffer.append(TEXT_878);
    }
    stringBuffer.append(TEXT_879);
    if (valueFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_880);
    stringBuffer.append(valueFeature.getObjectType());
    stringBuffer.append(TEXT_881);
    } else {
    stringBuffer.append(TEXT_882);
    }
    stringBuffer.append(TEXT_883);
    if (valueFeature.isListType()) {
    stringBuffer.append(TEXT_884);
    stringBuffer.append(genModel.getImportedName("java.util.Collection"));
    stringBuffer.append(TEXT_885);
    } else if (valueFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_886);
    stringBuffer.append(valueFeature.getObjectType());
    stringBuffer.append(TEXT_887);
    stringBuffer.append(valueFeature.getPrimitiveValueFunction());
    stringBuffer.append(TEXT_888);
    } else {
    stringBuffer.append(TEXT_889);
    stringBuffer.append(valueFeature.getImportedType());
    stringBuffer.append(TEXT_890);
    }
    stringBuffer.append(TEXT_891);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.util.EMap"));
    stringBuffer.append(TEXT_892);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.EObject"));
    stringBuffer.append(TEXT_893);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.util.EMap"));
    stringBuffer.append(TEXT_894);
    }
    stringBuffer.append(TEXT_895);
    stringBuffer.append(genClass.getClassName());
    genModel.emitSortedImports();
    stringBuffer.append(TEXT_896);
    return stringBuffer.toString();
  }
}
