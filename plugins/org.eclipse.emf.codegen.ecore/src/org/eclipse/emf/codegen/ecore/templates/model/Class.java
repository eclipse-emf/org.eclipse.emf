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
  protected final String TEXT_94 = " == null)" + NL + "\t\t{" + NL + "\t\t\t";
  protected final String TEXT_95 = " = new ";
  protected final String TEXT_96 = ";" + NL + "\t\t}" + NL + "\t\treturn ";
  protected final String TEXT_97 = ";";
  protected final String TEXT_98 = NL + "\t\tif (eContainerFeatureID != ";
  protected final String TEXT_99 = ") return null;" + NL + "\t\treturn (";
  protected final String TEXT_100 = ")eContainer;";
  protected final String TEXT_101 = NL + "\t\tif (";
  protected final String TEXT_102 = " != null && ";
  protected final String TEXT_103 = ".eIsProxy())" + NL + "\t\t{" + NL + "\t\t\t";
  protected final String TEXT_104 = " old";
  protected final String TEXT_105 = " = ";
  protected final String TEXT_106 = ";" + NL + "\t\t\t";
  protected final String TEXT_107 = " = (";
  protected final String TEXT_108 = ")eResolveProxy((";
  protected final String TEXT_109 = ")";
  protected final String TEXT_110 = ");" + NL + "\t\t\tif (";
  protected final String TEXT_111 = " != old";
  protected final String TEXT_112 = ")" + NL + "\t\t\t{" + NL + "\t\t\t\tif (eNotificationRequired())" + NL + "\t\t\t\t\teNotify(new ";
  protected final String TEXT_113 = "(this, ";
  protected final String TEXT_114 = ".RESOLVE, ";
  protected final String TEXT_115 = ", old";
  protected final String TEXT_116 = ", ";
  protected final String TEXT_117 = "));" + NL + "\t\t\t}" + NL + "\t\t}";
  protected final String TEXT_118 = NL + "\t\treturn (";
  protected final String TEXT_119 = " & ";
  protected final String TEXT_120 = "_EFLAG) != 0;";
  protected final String TEXT_121 = NL + "\t\treturn ";
  protected final String TEXT_122 = ";";
  protected final String TEXT_123 = NL + "\t\t";
  protected final String TEXT_124 = " ";
  protected final String TEXT_125 = " = basicGet";
  protected final String TEXT_126 = "();" + NL + "\t\treturn ";
  protected final String TEXT_127 = " == null ? null : (";
  protected final String TEXT_128 = ")eResolveProxy((";
  protected final String TEXT_129 = ")";
  protected final String TEXT_130 = ");";
  protected final String TEXT_131 = NL + "\t\treturn new ";
  protected final String TEXT_132 = "((";
  protected final String TEXT_133 = ")((";
  protected final String TEXT_134 = ")get";
  protected final String TEXT_135 = "()).featureMap().list(";
  protected final String TEXT_136 = "()));";
  protected final String TEXT_137 = NL + "\t\treturn (";
  protected final String TEXT_138 = ")((";
  protected final String TEXT_139 = ")get";
  protected final String TEXT_140 = "()).list(";
  protected final String TEXT_141 = "());";
  protected final String TEXT_142 = NL + "\t\treturn ((";
  protected final String TEXT_143 = ")get";
  protected final String TEXT_144 = "()).featureMap().list(";
  protected final String TEXT_145 = "());";
  protected final String TEXT_146 = NL + "\t\treturn ((";
  protected final String TEXT_147 = ")get";
  protected final String TEXT_148 = "()).list(";
  protected final String TEXT_149 = "());";
  protected final String TEXT_150 = NL + "\t\treturn ";
  protected final String TEXT_151 = "(";
  protected final String TEXT_152 = "(";
  protected final String TEXT_153 = ")((";
  protected final String TEXT_154 = ")get";
  protected final String TEXT_155 = "()).featureMap().get(";
  protected final String TEXT_156 = "(), true)";
  protected final String TEXT_157 = ").";
  protected final String TEXT_158 = "()";
  protected final String TEXT_159 = ";";
  protected final String TEXT_160 = NL + "\t\treturn ";
  protected final String TEXT_161 = "(";
  protected final String TEXT_162 = "(";
  protected final String TEXT_163 = ")get";
  protected final String TEXT_164 = "().get(";
  protected final String TEXT_165 = "(), true)";
  protected final String TEXT_166 = ").";
  protected final String TEXT_167 = "()";
  protected final String TEXT_168 = ";";
  protected final String TEXT_169 = NL + "\t\t// TODO: implement this method to return the '";
  protected final String TEXT_170 = "' ";
  protected final String TEXT_171 = NL + "\t\t// Ensure that you remove @generated or mark it @generated NOT" + NL + "\t\tthrow new UnsupportedOperationException();";
  protected final String TEXT_172 = NL + "\t}" + NL;
  protected final String TEXT_173 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */" + NL + "\tpublic ";
  protected final String TEXT_174 = " basicGet";
  protected final String TEXT_175 = "()" + NL + "\t{";
  protected final String TEXT_176 = NL + "\t\treturn ";
  protected final String TEXT_177 = ";";
  protected final String TEXT_178 = NL + "\t\treturn (";
  protected final String TEXT_179 = ")((";
  protected final String TEXT_180 = ")get";
  protected final String TEXT_181 = "()).featureMap().get(";
  protected final String TEXT_182 = "(), false);";
  protected final String TEXT_183 = NL + "\t\treturn (";
  protected final String TEXT_184 = ")get";
  protected final String TEXT_185 = "().get(";
  protected final String TEXT_186 = "(), false);";
  protected final String TEXT_187 = NL + "\t\t// TODO: implement this method to return the '";
  protected final String TEXT_188 = "' ";
  protected final String TEXT_189 = NL + "\t\t// -> do not perform proxy resolution" + NL + "\t\t// Ensure that you remove @generated or mark it @generated NOT" + NL + "\t\tthrow new UnsupportedOperationException();";
  protected final String TEXT_190 = NL + "\t}" + NL;
  protected final String TEXT_191 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */" + NL + "\tpublic ";
  protected final String TEXT_192 = " basicSet";
  protected final String TEXT_193 = "(";
  protected final String TEXT_194 = " new";
  protected final String TEXT_195 = ", ";
  protected final String TEXT_196 = " msgs)" + NL + "\t{";
  protected final String TEXT_197 = NL + "\t\t";
  protected final String TEXT_198 = " old";
  protected final String TEXT_199 = " = ";
  protected final String TEXT_200 = ";" + NL + "\t\t";
  protected final String TEXT_201 = " = new";
  protected final String TEXT_202 = ";";
  protected final String TEXT_203 = NL + "\t\tboolean old";
  protected final String TEXT_204 = "ESet = (";
  protected final String TEXT_205 = " & ";
  protected final String TEXT_206 = "_ESETFLAG) != 0;" + NL + "\t\t";
  protected final String TEXT_207 = " |= ";
  protected final String TEXT_208 = "_ESETFLAG;";
  protected final String TEXT_209 = NL + "\t\tboolean old";
  protected final String TEXT_210 = "ESet = ";
  protected final String TEXT_211 = "ESet;" + NL + "\t\t";
  protected final String TEXT_212 = "ESet = true;";
  protected final String TEXT_213 = NL + "\t\tif (eNotificationRequired())" + NL + "\t\t{";
  protected final String TEXT_214 = NL + "\t\t\t";
  protected final String TEXT_215 = " notification = new ";
  protected final String TEXT_216 = "(this, ";
  protected final String TEXT_217 = ".SET, ";
  protected final String TEXT_218 = ", old";
  protected final String TEXT_219 = ", new";
  protected final String TEXT_220 = ", !old";
  protected final String TEXT_221 = "ESet);";
  protected final String TEXT_222 = NL + "\t\t\t";
  protected final String TEXT_223 = " notification = new ";
  protected final String TEXT_224 = "(this, ";
  protected final String TEXT_225 = ".SET, ";
  protected final String TEXT_226 = ", old";
  protected final String TEXT_227 = ", new";
  protected final String TEXT_228 = ");";
  protected final String TEXT_229 = NL + "\t\t\tif (msgs == null) msgs = notification; else msgs.add(notification);" + NL + "\t\t}";
  protected final String TEXT_230 = NL + "\t\treturn msgs;";
  protected final String TEXT_231 = NL + "\t\treturn ((";
  protected final String TEXT_232 = ".Internal)((";
  protected final String TEXT_233 = ")get";
  protected final String TEXT_234 = "()).featureMap()).basicAdd(";
  protected final String TEXT_235 = "(), new";
  protected final String TEXT_236 = ", msgs);";
  protected final String TEXT_237 = NL + "\t\treturn ((";
  protected final String TEXT_238 = ".Internal)get";
  protected final String TEXT_239 = "()).basicAdd(";
  protected final String TEXT_240 = "(), new";
  protected final String TEXT_241 = ", msgs);";
  protected final String TEXT_242 = NL + "\t\t// TODO: implement this method to set the contained '";
  protected final String TEXT_243 = "' ";
  protected final String TEXT_244 = NL + "\t\t// -> this method is automatically invoked to keep the containment relationship in synch" + NL + "\t\t// -> do not modify other features" + NL + "\t\t// -> return msgs, after adding any generated Notification to it (if it is null, a NotificationChain object must be created first)" + NL + "\t\t// Ensure that you remove @generated or mark it @generated NOT" + NL + "\t\tthrow new UnsupportedOperationException();";
  protected final String TEXT_245 = NL + "\t}" + NL;
  protected final String TEXT_246 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */" + NL + "\tpublic void set";
  protected final String TEXT_247 = "(";
  protected final String TEXT_248 = " new";
  protected final String TEXT_249 = ")" + NL + "\t{";
  protected final String TEXT_250 = NL + "\t\teSet(";
  protected final String TEXT_251 = ".eINSTANCE.get";
  protected final String TEXT_252 = "(), ";
  protected final String TEXT_253 = "new ";
  protected final String TEXT_254 = "(";
  protected final String TEXT_255 = "new";
  protected final String TEXT_256 = ")";
  protected final String TEXT_257 = ");";
  protected final String TEXT_258 = NL + "\t\tif (new";
  protected final String TEXT_259 = " != eContainer || (eContainerFeatureID != ";
  protected final String TEXT_260 = " && new";
  protected final String TEXT_261 = " != null))" + NL + "\t\t{" + NL + "\t\t\tif (";
  protected final String TEXT_262 = ".isAncestor(this, ";
  protected final String TEXT_263 = "new";
  protected final String TEXT_264 = "))" + NL + "\t\t\t\tthrow new ";
  protected final String TEXT_265 = "(\"Recursive containment not allowed for \" + toString());";
  protected final String TEXT_266 = NL + "\t\t\t";
  protected final String TEXT_267 = " msgs = null;" + NL + "\t\t\tif (eContainer != null)" + NL + "\t\t\t\tmsgs = eBasicRemoveFromContainer(msgs);" + NL + "\t\t\tif (new";
  protected final String TEXT_268 = " != null)" + NL + "\t\t\t\tmsgs = ((";
  protected final String TEXT_269 = ")new";
  protected final String TEXT_270 = ").eInverseAdd(this, ";
  protected final String TEXT_271 = ", ";
  protected final String TEXT_272 = ".class, msgs);" + NL + "\t\t\tmsgs = eBasicSetContainer((";
  protected final String TEXT_273 = ")new";
  protected final String TEXT_274 = ", ";
  protected final String TEXT_275 = ", msgs);" + NL + "\t\t\tif (msgs != null) msgs.dispatch();" + NL + "\t\t}" + NL + "\t\telse if (eNotificationRequired())" + NL + "\t\t\teNotify(new ";
  protected final String TEXT_276 = "(this, ";
  protected final String TEXT_277 = ".SET, ";
  protected final String TEXT_278 = ", new";
  protected final String TEXT_279 = ", new";
  protected final String TEXT_280 = "));";
  protected final String TEXT_281 = NL + "\t\tif (new";
  protected final String TEXT_282 = " != ";
  protected final String TEXT_283 = ")" + NL + "\t\t{" + NL + "\t\t\t";
  protected final String TEXT_284 = " msgs = null;" + NL + "\t\t\tif (";
  protected final String TEXT_285 = " != null)";
  protected final String TEXT_286 = NL + "\t\t\t\tmsgs = ((";
  protected final String TEXT_287 = ")";
  protected final String TEXT_288 = ").eInverseRemove(this, EOPPOSITE_FEATURE_BASE - ";
  protected final String TEXT_289 = ", null, msgs);" + NL + "\t\t\tif (new";
  protected final String TEXT_290 = " != null)" + NL + "\t\t\t\tmsgs = ((";
  protected final String TEXT_291 = ")new";
  protected final String TEXT_292 = ").eInverseAdd(this, EOPPOSITE_FEATURE_BASE - ";
  protected final String TEXT_293 = ", null, msgs);";
  protected final String TEXT_294 = NL + "\t\t\t\tmsgs = ((";
  protected final String TEXT_295 = ")";
  protected final String TEXT_296 = ").eInverseRemove(this, ";
  protected final String TEXT_297 = ", ";
  protected final String TEXT_298 = ".class, msgs);" + NL + "\t\t\tif (new";
  protected final String TEXT_299 = " != null)" + NL + "\t\t\t\tmsgs = ((";
  protected final String TEXT_300 = ")new";
  protected final String TEXT_301 = ").eInverseAdd(this, ";
  protected final String TEXT_302 = ", ";
  protected final String TEXT_303 = ".class, msgs);";
  protected final String TEXT_304 = NL + "\t\t\tmsgs = basicSet";
  protected final String TEXT_305 = "(";
  protected final String TEXT_306 = "new";
  protected final String TEXT_307 = ", msgs);" + NL + "\t\t\tif (msgs != null) msgs.dispatch();" + NL + "\t\t}";
  protected final String TEXT_308 = NL + "\t\telse" + NL + "    \t{";
  protected final String TEXT_309 = NL + "\t\tboolean old";
  protected final String TEXT_310 = "ESet = (";
  protected final String TEXT_311 = " & ";
  protected final String TEXT_312 = "_ESETFLAG) != 0;" + NL + "\t\t";
  protected final String TEXT_313 = " |= ";
  protected final String TEXT_314 = "_ESETFLAG;";
  protected final String TEXT_315 = NL + "\t\t\tboolean old";
  protected final String TEXT_316 = "ESet = ";
  protected final String TEXT_317 = "ESet;" + NL + "\t\t\t";
  protected final String TEXT_318 = "ESet = true;";
  protected final String TEXT_319 = NL + "\t\t\tif (eNotificationRequired())" + NL + "\t\t\t\teNotify(new ";
  protected final String TEXT_320 = "(this, ";
  protected final String TEXT_321 = ".SET, ";
  protected final String TEXT_322 = ", new";
  protected final String TEXT_323 = ", new";
  protected final String TEXT_324 = ", !old";
  protected final String TEXT_325 = "ESet));" + NL + "    \t}";
  protected final String TEXT_326 = NL + "\t\telse if (eNotificationRequired())" + NL + "\t\t\teNotify(new ";
  protected final String TEXT_327 = "(this, ";
  protected final String TEXT_328 = ".SET, ";
  protected final String TEXT_329 = ", new";
  protected final String TEXT_330 = ", new";
  protected final String TEXT_331 = "));";
  protected final String TEXT_332 = NL + "\t\t";
  protected final String TEXT_333 = " old";
  protected final String TEXT_334 = " = (";
  protected final String TEXT_335 = " & ";
  protected final String TEXT_336 = "_EFLAG) != 0;" + NL + "\t\tif (new";
  protected final String TEXT_337 = ") ";
  protected final String TEXT_338 = " |= ";
  protected final String TEXT_339 = "_EFLAG; else ";
  protected final String TEXT_340 = " &= ~";
  protected final String TEXT_341 = "_EFLAG;";
  protected final String TEXT_342 = NL + "\t\t";
  protected final String TEXT_343 = " old";
  protected final String TEXT_344 = " = ";
  protected final String TEXT_345 = ";";
  protected final String TEXT_346 = NL + "\t\t";
  protected final String TEXT_347 = " = new";
  protected final String TEXT_348 = " == null ? ";
  protected final String TEXT_349 = "_EDEFAULT : new";
  protected final String TEXT_350 = ";";
  protected final String TEXT_351 = NL + "\t\t";
  protected final String TEXT_352 = " = ";
  protected final String TEXT_353 = "new";
  protected final String TEXT_354 = ";";
  protected final String TEXT_355 = NL + "\t\tboolean old";
  protected final String TEXT_356 = "ESet = (";
  protected final String TEXT_357 = " & ";
  protected final String TEXT_358 = "_ESETFLAG) != 0;" + NL + "\t\t";
  protected final String TEXT_359 = " |= ";
  protected final String TEXT_360 = "_ESETFLAG;";
  protected final String TEXT_361 = NL + "\t\tboolean old";
  protected final String TEXT_362 = "ESet = ";
  protected final String TEXT_363 = "ESet;" + NL + "\t\t";
  protected final String TEXT_364 = "ESet = true;";
  protected final String TEXT_365 = NL + "\t\tif (eNotificationRequired())" + NL + "\t\t\teNotify(new ";
  protected final String TEXT_366 = "(this, ";
  protected final String TEXT_367 = ".SET, ";
  protected final String TEXT_368 = ", old";
  protected final String TEXT_369 = ", ";
  protected final String TEXT_370 = "new";
  protected final String TEXT_371 = ", !old";
  protected final String TEXT_372 = "ESet));";
  protected final String TEXT_373 = NL + "\t\tif (eNotificationRequired())" + NL + "\t\t\teNotify(new ";
  protected final String TEXT_374 = "(this, ";
  protected final String TEXT_375 = ".SET, ";
  protected final String TEXT_376 = ", old";
  protected final String TEXT_377 = ", ";
  protected final String TEXT_378 = "new";
  protected final String TEXT_379 = "));";
  protected final String TEXT_380 = NL + "\t\t((";
  protected final String TEXT_381 = ".Internal)((";
  protected final String TEXT_382 = ")get";
  protected final String TEXT_383 = "()).featureMap()).set(";
  protected final String TEXT_384 = "(), ";
  protected final String TEXT_385 = "new ";
  protected final String TEXT_386 = "(";
  protected final String TEXT_387 = "new";
  protected final String TEXT_388 = ")";
  protected final String TEXT_389 = ");";
  protected final String TEXT_390 = NL + "\t\t((";
  protected final String TEXT_391 = ".Internal)get";
  protected final String TEXT_392 = "()).set(";
  protected final String TEXT_393 = "(), ";
  protected final String TEXT_394 = "new ";
  protected final String TEXT_395 = "(";
  protected final String TEXT_396 = "new";
  protected final String TEXT_397 = ")";
  protected final String TEXT_398 = ");";
  protected final String TEXT_399 = NL + "\t\t// TODO: implement this method to set the '";
  protected final String TEXT_400 = "' ";
  protected final String TEXT_401 = NL + "\t\t// Ensure that you remove @generated or mark it @generated NOT" + NL + "\t\tthrow new UnsupportedOperationException();";
  protected final String TEXT_402 = NL + "\t}" + NL;
  protected final String TEXT_403 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */" + NL + "\tpublic ";
  protected final String TEXT_404 = " basicUnset";
  protected final String TEXT_405 = "(";
  protected final String TEXT_406 = " msgs)" + NL + "\t{";
  protected final String TEXT_407 = NL + "\t\t";
  protected final String TEXT_408 = " old";
  protected final String TEXT_409 = " = ";
  protected final String TEXT_410 = ";" + NL + "\t\t";
  protected final String TEXT_411 = " = null;";
  protected final String TEXT_412 = NL + "\t\tboolean old";
  protected final String TEXT_413 = "ESet = (";
  protected final String TEXT_414 = " & ";
  protected final String TEXT_415 = "_ESETFLAG) != 0;" + NL + "\t\t";
  protected final String TEXT_416 = " &= ~";
  protected final String TEXT_417 = "_ESETFLAG;";
  protected final String TEXT_418 = NL + "\t\tboolean old";
  protected final String TEXT_419 = "ESet = ";
  protected final String TEXT_420 = "ESet;" + NL + "\t\t";
  protected final String TEXT_421 = "ESet = false;";
  protected final String TEXT_422 = NL + "\t\tif (eNotificationRequired())" + NL + "\t\t{" + NL + "\t\t\t";
  protected final String TEXT_423 = " notification = new ";
  protected final String TEXT_424 = "(this, ";
  protected final String TEXT_425 = ".UNSET, ";
  protected final String TEXT_426 = ", old";
  protected final String TEXT_427 = ", null, old";
  protected final String TEXT_428 = "ESet);" + NL + "\t\t\tif (msgs == null) msgs = notification; else msgs.add(notification);" + NL + "\t\t}" + NL + "\t\treturn msgs;";
  protected final String TEXT_429 = NL + "\t\t// TODO: implement this method to unset the contained '";
  protected final String TEXT_430 = "' ";
  protected final String TEXT_431 = NL + "\t\t// -> this method is automatically invoked to keep the containment relationship in synch" + NL + "\t\t// -> do not modify other features" + NL + "\t\t// -> return msgs, after adding any generated Notification to it (if it is null, a NotificationChain object must be created first)" + NL + "\t\t// Ensure that you remove @generated or mark it @generated NOT" + NL + "\t\tthrow new UnsupportedOperationException();";
  protected final String TEXT_432 = NL + "\t}" + NL;
  protected final String TEXT_433 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */" + NL + "\tpublic void unset";
  protected final String TEXT_434 = "()" + NL + "\t{";
  protected final String TEXT_435 = NL + "\t\teUnset(";
  protected final String TEXT_436 = ".eINSTANCE.get";
  protected final String TEXT_437 = "());";
  protected final String TEXT_438 = NL + "\t\t((";
  protected final String TEXT_439 = ".Unsettable)get";
  protected final String TEXT_440 = "()).unset();";
  protected final String TEXT_441 = NL + "\t\tif (";
  protected final String TEXT_442 = " != null)" + NL + "\t\t{" + NL + "\t\t\t";
  protected final String TEXT_443 = " msgs = null;";
  protected final String TEXT_444 = NL + "\t\t\tmsgs = ((";
  protected final String TEXT_445 = ")";
  protected final String TEXT_446 = ").eInverseRemove(this, EOPPOSITE_FEATURE_BASE - ";
  protected final String TEXT_447 = ", null, msgs);";
  protected final String TEXT_448 = NL + "\t\t\tmsgs = ((";
  protected final String TEXT_449 = ")";
  protected final String TEXT_450 = ").eInverseRemove(this, ";
  protected final String TEXT_451 = ", ";
  protected final String TEXT_452 = ".class, msgs);";
  protected final String TEXT_453 = NL + "\t\t\tmsgs = basicUnset";
  protected final String TEXT_454 = "(msgs);" + NL + "\t\t\tif (msgs != null) msgs.dispatch();" + NL + "\t\t}" + NL + "\t\telse" + NL + "    \t{";
  protected final String TEXT_455 = NL + "\t\t\tboolean old";
  protected final String TEXT_456 = "ESet = (";
  protected final String TEXT_457 = " & ";
  protected final String TEXT_458 = "_ESETFLAG) != 0;" + NL + "\t\t\t";
  protected final String TEXT_459 = " &= ~";
  protected final String TEXT_460 = "_ESETFLAG;";
  protected final String TEXT_461 = NL + "\t\t\tboolean old";
  protected final String TEXT_462 = "ESet = ";
  protected final String TEXT_463 = "ESet;" + NL + "\t\t\t";
  protected final String TEXT_464 = "ESet = false;";
  protected final String TEXT_465 = NL + "\t\t\tif (eNotificationRequired())" + NL + "\t\t\t\teNotify(new ";
  protected final String TEXT_466 = "(this, ";
  protected final String TEXT_467 = ".UNSET, ";
  protected final String TEXT_468 = ", null, null, old";
  protected final String TEXT_469 = "ESet));" + NL + "    \t}";
  protected final String TEXT_470 = NL + "\t\t";
  protected final String TEXT_471 = " old";
  protected final String TEXT_472 = " = (";
  protected final String TEXT_473 = " & ";
  protected final String TEXT_474 = "_EFLAG) != 0;";
  protected final String TEXT_475 = NL + "\t\t";
  protected final String TEXT_476 = " old";
  protected final String TEXT_477 = " = ";
  protected final String TEXT_478 = ";";
  protected final String TEXT_479 = NL + "\t\tboolean old";
  protected final String TEXT_480 = "ESet = (";
  protected final String TEXT_481 = " & ";
  protected final String TEXT_482 = "_ESETFLAG) != 0;";
  protected final String TEXT_483 = NL + "\t\tboolean old";
  protected final String TEXT_484 = "ESet = ";
  protected final String TEXT_485 = "ESet;";
  protected final String TEXT_486 = NL + "\t\t";
  protected final String TEXT_487 = " = null;";
  protected final String TEXT_488 = NL + "\t\t";
  protected final String TEXT_489 = " &= ~";
  protected final String TEXT_490 = "_ESETFLAG;";
  protected final String TEXT_491 = NL + "\t\t";
  protected final String TEXT_492 = "ESet = false;";
  protected final String TEXT_493 = NL + "\t\tif (eNotificationRequired())" + NL + "\t\t\teNotify(new ";
  protected final String TEXT_494 = "(this, ";
  protected final String TEXT_495 = ".UNSET, ";
  protected final String TEXT_496 = ", old";
  protected final String TEXT_497 = ", null, old";
  protected final String TEXT_498 = "ESet));";
  protected final String TEXT_499 = NL + "\t\tif (";
  protected final String TEXT_500 = "_EDEFAULT) ";
  protected final String TEXT_501 = " |= ";
  protected final String TEXT_502 = "_EFLAG; else ";
  protected final String TEXT_503 = " &= ~";
  protected final String TEXT_504 = "_EFLAG;";
  protected final String TEXT_505 = NL + "\t\t";
  protected final String TEXT_506 = " = ";
  protected final String TEXT_507 = "_EDEFAULT;";
  protected final String TEXT_508 = NL + "\t\t";
  protected final String TEXT_509 = " &= ~";
  protected final String TEXT_510 = "_ESETFLAG;";
  protected final String TEXT_511 = NL + "\t\t";
  protected final String TEXT_512 = "ESet = false;";
  protected final String TEXT_513 = NL + "\t\tif (eNotificationRequired())" + NL + "\t\t\teNotify(new ";
  protected final String TEXT_514 = "(this, ";
  protected final String TEXT_515 = ".UNSET, ";
  protected final String TEXT_516 = ", old";
  protected final String TEXT_517 = ", ";
  protected final String TEXT_518 = "_EDEFAULT, old";
  protected final String TEXT_519 = "ESet));";
  protected final String TEXT_520 = NL + "\t\t((";
  protected final String TEXT_521 = ".Internal)((";
  protected final String TEXT_522 = ")get";
  protected final String TEXT_523 = "()).featureMap()).clear(";
  protected final String TEXT_524 = "());";
  protected final String TEXT_525 = NL + "\t\t((";
  protected final String TEXT_526 = ".Internal)get";
  protected final String TEXT_527 = "()).clear(";
  protected final String TEXT_528 = "());";
  protected final String TEXT_529 = NL + "\t\t// TODO: implement this method to unset the '";
  protected final String TEXT_530 = "' ";
  protected final String TEXT_531 = NL + "\t\t// Ensure that you remove @generated or mark it @generated NOT" + NL + "\t\tthrow new UnsupportedOperationException();";
  protected final String TEXT_532 = NL + "\t}" + NL;
  protected final String TEXT_533 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */" + NL + "\tpublic boolean isSet";
  protected final String TEXT_534 = "()" + NL + "\t{";
  protected final String TEXT_535 = NL + "\t\treturn eIsSet(";
  protected final String TEXT_536 = ".eINSTANCE.get";
  protected final String TEXT_537 = "());";
  protected final String TEXT_538 = NL + "\t\treturn ";
  protected final String TEXT_539 = " != null && ((";
  protected final String TEXT_540 = ".Unsettable)";
  protected final String TEXT_541 = ").isSet();";
  protected final String TEXT_542 = NL + "\t\treturn (";
  protected final String TEXT_543 = " & ";
  protected final String TEXT_544 = "_ESETFLAG) != 0;";
  protected final String TEXT_545 = NL + "\t\treturn ";
  protected final String TEXT_546 = "ESet;";
  protected final String TEXT_547 = NL + "\t\treturn !((";
  protected final String TEXT_548 = ".Internal)((";
  protected final String TEXT_549 = ")get";
  protected final String TEXT_550 = "()).featureMap()).isEmpty(";
  protected final String TEXT_551 = "());";
  protected final String TEXT_552 = NL + "\t\treturn !((";
  protected final String TEXT_553 = ".Internal)get";
  protected final String TEXT_554 = "()).isEmpty(";
  protected final String TEXT_555 = "());";
  protected final String TEXT_556 = NL + "\t\t// TODO: implement this method to return whether the '";
  protected final String TEXT_557 = "' ";
  protected final String TEXT_558 = " is set" + NL + "\t\t// Ensure that you remove @generated or mark it @generated NOT" + NL + "\t\tthrow new UnsupportedOperationException();";
  protected final String TEXT_559 = NL + "\t}" + NL;
  protected final String TEXT_560 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */" + NL + "\tpublic ";
  protected final String TEXT_561 = " ";
  protected final String TEXT_562 = "(";
  protected final String TEXT_563 = ")";
  protected final String TEXT_564 = NL + "\t{";
  protected final String TEXT_565 = NL + "\t\t";
  protected final String TEXT_566 = NL + "\t\t// TODO: implement this method" + NL + "\t\t// -> specify the condition that violates the invariant" + NL + "\t\t// -> verify the details of the diagnostic, including severity and message" + NL + "\t\t// Ensure that you remove @generated or mark it @generated NOT" + NL + "\t\tif (false)" + NL + "\t\t{" + NL + "\t\t\tif (";
  protected final String TEXT_567 = " != null)" + NL + "\t\t\t{" + NL + "\t\t\t\t";
  protected final String TEXT_568 = ".add" + NL + "\t\t\t\t\t(new ";
  protected final String TEXT_569 = NL + "\t\t\t\t\t\t(";
  protected final String TEXT_570 = ".ERROR," + NL + "\t\t\t\t\t\t ";
  protected final String TEXT_571 = ".DIAGNOSTIC_SOURCE," + NL + "\t\t\t\t\t\t ";
  protected final String TEXT_572 = ".";
  protected final String TEXT_573 = "," + NL + "\t\t\t\t\t\t ";
  protected final String TEXT_574 = ".INSTANCE.getString(\"_UI_GenericInvariant_diagnostic\", new Object[] { \"";
  protected final String TEXT_575 = "\", ";
  protected final String TEXT_576 = ".getObjectLabel(this, ";
  protected final String TEXT_577 = ") }),";
  protected final String TEXT_578 = NL + "\t\t\t\t\t\t new Object [] { this }));" + NL + "\t\t\t}" + NL + "\t\t\treturn false;" + NL + "\t\t}" + NL + "\t\treturn true;";
  protected final String TEXT_579 = NL + "\t\t// TODO: implement this method" + NL + "\t\t// Ensure that you remove @generated or mark it @generated NOT" + NL + "\t\tthrow new UnsupportedOperationException();";
  protected final String TEXT_580 = NL + "\t}" + NL;
  protected final String TEXT_581 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */" + NL + "\tpublic ";
  protected final String TEXT_582 = " eInverseAdd(";
  protected final String TEXT_583 = " otherEnd, int featureID, Class baseClass, ";
  protected final String TEXT_584 = " msgs)" + NL + "\t{" + NL + "\t\tif (featureID >= 0)" + NL + "\t\t{" + NL + "\t\t\tswitch (eDerivedStructuralFeatureID(featureID, baseClass))" + NL + "\t\t\t{";
  protected final String TEXT_585 = NL + "\t\t\t\tcase ";
  protected final String TEXT_586 = ":";
  protected final String TEXT_587 = NL + "\t\t\t\t\treturn ((";
  protected final String TEXT_588 = ")((";
  protected final String TEXT_589 = ")";
  protected final String TEXT_590 = "()).eMap()).basicAdd(otherEnd, msgs);";
  protected final String TEXT_591 = NL + "\t\t\t\t\treturn ((";
  protected final String TEXT_592 = ")";
  protected final String TEXT_593 = "()).basicAdd(otherEnd, msgs);";
  protected final String TEXT_594 = NL + "\t\t\t\t\tif (eContainer != null)" + NL + "\t\t\t\t\t\tmsgs = eBasicRemoveFromContainer(msgs);" + NL + "\t\t\t\t\treturn eBasicSetContainer(otherEnd, ";
  protected final String TEXT_595 = ", msgs);";
  protected final String TEXT_596 = NL + "\t\t\t\t\tif (";
  protected final String TEXT_597 = " != null)";
  protected final String TEXT_598 = NL + "\t\t\t\t\t\tmsgs = ((";
  protected final String TEXT_599 = ")";
  protected final String TEXT_600 = ").eInverseRemove(this, EOPPOSITE_FEATURE_BASE - ";
  protected final String TEXT_601 = ", null, msgs);";
  protected final String TEXT_602 = NL + "\t\t\t\t\t\tmsgs = ((";
  protected final String TEXT_603 = ")";
  protected final String TEXT_604 = ").eInverseRemove(this, ";
  protected final String TEXT_605 = ", ";
  protected final String TEXT_606 = ".class, msgs);";
  protected final String TEXT_607 = NL + "\t\t\t\t\treturn basicSet";
  protected final String TEXT_608 = "((";
  protected final String TEXT_609 = ")otherEnd, msgs);";
  protected final String TEXT_610 = NL + "\t\t\t\tdefault:" + NL + "\t\t\t\t\treturn eDynamicInverseAdd(otherEnd, featureID, baseClass, msgs);" + NL + "\t\t\t}" + NL + "\t\t}" + NL + "\t\tif (eContainer != null)" + NL + "\t\t\tmsgs = eBasicRemoveFromContainer(msgs);" + NL + "\t\treturn eBasicSetContainer(otherEnd, featureID, msgs);" + NL + "\t}" + NL;
  protected final String TEXT_611 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */" + NL + "\tpublic ";
  protected final String TEXT_612 = " eInverseRemove(";
  protected final String TEXT_613 = " otherEnd, int featureID, Class baseClass, ";
  protected final String TEXT_614 = " msgs)" + NL + "\t{" + NL + "\t\tif (featureID >= 0)" + NL + "\t\t{" + NL + "\t\t\tswitch (eDerivedStructuralFeatureID(featureID, baseClass))" + NL + "\t\t\t{";
  protected final String TEXT_615 = NL + "\t\t\t\tcase ";
  protected final String TEXT_616 = ":";
  protected final String TEXT_617 = NL + "\t\t\t\t\treturn ((";
  protected final String TEXT_618 = ")((";
  protected final String TEXT_619 = ")";
  protected final String TEXT_620 = "()).eMap()).basicRemove(otherEnd, msgs);";
  protected final String TEXT_621 = NL + "\t\t\t\treturn ((";
  protected final String TEXT_622 = ")((";
  protected final String TEXT_623 = ")";
  protected final String TEXT_624 = "()).featureMap()).basicRemove(otherEnd, msgs);";
  protected final String TEXT_625 = NL + "\t\t\t\t\treturn ((";
  protected final String TEXT_626 = ")";
  protected final String TEXT_627 = "()).basicRemove(otherEnd, msgs);";
  protected final String TEXT_628 = NL + "\t\t\t\t\treturn eBasicSetContainer(null, ";
  protected final String TEXT_629 = ", msgs);";
  protected final String TEXT_630 = NL + "\t\t\t\t\treturn basicUnset";
  protected final String TEXT_631 = "(msgs);";
  protected final String TEXT_632 = NL + "\t\t\t\t\treturn basicSet";
  protected final String TEXT_633 = "(null, msgs);";
  protected final String TEXT_634 = NL + "\t\t\t\tdefault:" + NL + "\t\t\t\t\treturn eDynamicInverseRemove(otherEnd, featureID, baseClass, msgs);" + NL + "\t\t\t}" + NL + "\t\t}" + NL + "\t\treturn eBasicSetContainer(null, featureID, msgs);" + NL + "\t}" + NL;
  protected final String TEXT_635 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */" + NL + "\tpublic ";
  protected final String TEXT_636 = " eBasicRemoveFromContainer(";
  protected final String TEXT_637 = " msgs)" + NL + "\t{" + NL + "\t\tif (eContainerFeatureID >= 0)" + NL + "\t\t{" + NL + "\t\t\tswitch (eContainerFeatureID)" + NL + "\t\t\t{";
  protected final String TEXT_638 = NL + "\t\t\t\tcase ";
  protected final String TEXT_639 = ":" + NL + "\t\t\t\t\treturn eContainer.eInverseRemove(this, ";
  protected final String TEXT_640 = ", ";
  protected final String TEXT_641 = ".class, msgs);";
  protected final String TEXT_642 = NL + "\t\t\t\tdefault:" + NL + "\t\t\t\t\treturn eDynamicBasicRemoveFromContainer(msgs);" + NL + "\t\t\t}" + NL + "\t\t}" + NL + "\t\treturn eContainer.eInverseRemove(this, EOPPOSITE_FEATURE_BASE - eContainerFeatureID, null, msgs);" + NL + "\t}" + NL;
  protected final String TEXT_643 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */" + NL + "\tpublic Object eGet(";
  protected final String TEXT_644 = " eFeature, boolean resolve)" + NL + "\t{" + NL + "\t\tswitch (eDerivedStructuralFeatureID(eFeature))" + NL + "\t\t{";
  protected final String TEXT_645 = NL + "\t\t\tcase ";
  protected final String TEXT_646 = ":";
  protected final String TEXT_647 = NL + "\t\t\t\treturn ";
  protected final String TEXT_648 = "() ? Boolean.TRUE : Boolean.FALSE;";
  protected final String TEXT_649 = NL + "\t\t\t\treturn new ";
  protected final String TEXT_650 = "(";
  protected final String TEXT_651 = "());";
  protected final String TEXT_652 = NL + "\t\t\t\tif (resolve) return ";
  protected final String TEXT_653 = "();" + NL + "\t\t\t\treturn basicGet";
  protected final String TEXT_654 = "();";
  protected final String TEXT_655 = NL + "\t\t\t\treturn ((";
  protected final String TEXT_656 = ")";
  protected final String TEXT_657 = "()).eMap();";
  protected final String TEXT_658 = NL + "\t\t\t\treturn ((";
  protected final String TEXT_659 = ")";
  protected final String TEXT_660 = "()).featureMap();";
  protected final String TEXT_661 = NL + "\t\t\t\treturn ";
  protected final String TEXT_662 = "();";
  protected final String TEXT_663 = NL + "\t\t}" + NL + "\t\treturn eDynamicGet(eFeature, resolve);" + NL + "\t}" + NL;
  protected final String TEXT_664 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */" + NL + "\tpublic void eSet(";
  protected final String TEXT_665 = " eFeature, Object newValue)" + NL + "\t{" + NL + "\t\tswitch (eDerivedStructuralFeatureID(eFeature))" + NL + "\t\t{";
  protected final String TEXT_666 = NL + "\t\t\tcase ";
  protected final String TEXT_667 = ":";
  protected final String TEXT_668 = NL + "\t\t\t\t((";
  protected final String TEXT_669 = ")";
  protected final String TEXT_670 = "()).featureMap().clear();";
  protected final String TEXT_671 = NL + "\t\t\t\t";
  protected final String TEXT_672 = "().clear();";
  protected final String TEXT_673 = NL + "\t\t\t\t((";
  protected final String TEXT_674 = ")";
  protected final String TEXT_675 = "()).featureMap().addAll((";
  protected final String TEXT_676 = ")newValue);";
  protected final String TEXT_677 = NL + "\t\t\t\t((";
  protected final String TEXT_678 = ")";
  protected final String TEXT_679 = "()).eMap().addAll((";
  protected final String TEXT_680 = ")newValue);";
  protected final String TEXT_681 = NL + "\t\t\t\t";
  protected final String TEXT_682 = "().addAll((";
  protected final String TEXT_683 = ")newValue);";
  protected final String TEXT_684 = NL + "\t\t\t\tset";
  protected final String TEXT_685 = "(((";
  protected final String TEXT_686 = ")newValue).";
  protected final String TEXT_687 = "());";
  protected final String TEXT_688 = NL + "\t\t\t\tset";
  protected final String TEXT_689 = "((";
  protected final String TEXT_690 = ")newValue);";
  protected final String TEXT_691 = NL + "\t\t\t\treturn;";
  protected final String TEXT_692 = NL + "\t\t}" + NL + "\t\teDynamicSet(eFeature, newValue);" + NL + "\t}" + NL + "" + NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */" + NL + "\tpublic void eUnset(";
  protected final String TEXT_693 = " eFeature)" + NL + "\t{" + NL + "\t\tswitch (eDerivedStructuralFeatureID(eFeature))" + NL + "\t\t{";
  protected final String TEXT_694 = NL + "\t\t\tcase ";
  protected final String TEXT_695 = ":";
  protected final String TEXT_696 = NL + "\t\t\t\t((";
  protected final String TEXT_697 = ")";
  protected final String TEXT_698 = "()).featureMap().clear();";
  protected final String TEXT_699 = NL + "\t\t\t\t";
  protected final String TEXT_700 = "().clear();";
  protected final String TEXT_701 = NL + "\t\t\t\tunset";
  protected final String TEXT_702 = "();";
  protected final String TEXT_703 = NL + "\t\t\t\tset";
  protected final String TEXT_704 = "((";
  protected final String TEXT_705 = ")null);";
  protected final String TEXT_706 = NL + "\t\t\t\tset";
  protected final String TEXT_707 = "(";
  protected final String TEXT_708 = "_EDEFAULT);";
  protected final String TEXT_709 = NL + "\t\t\t\treturn;";
  protected final String TEXT_710 = NL + "\t\t}" + NL + "\t\teDynamicUnset(eFeature);" + NL + "\t}" + NL;
  protected final String TEXT_711 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */" + NL + "\tpublic boolean eIsSet(";
  protected final String TEXT_712 = " eFeature)" + NL + "\t{" + NL + "\t\tswitch (eDerivedStructuralFeatureID(eFeature))" + NL + "\t\t{";
  protected final String TEXT_713 = NL + "\t\t\tcase ";
  protected final String TEXT_714 = ":";
  protected final String TEXT_715 = NL + "\t\t\t\treturn !((";
  protected final String TEXT_716 = ")";
  protected final String TEXT_717 = "()).featureMap().isEmpty();";
  protected final String TEXT_718 = NL + "\t\t\t\treturn ";
  protected final String TEXT_719 = " != null && !";
  protected final String TEXT_720 = ".featureMap().isEmpty();";
  protected final String TEXT_721 = NL + "\t\t\t\treturn ";
  protected final String TEXT_722 = " != null && !";
  protected final String TEXT_723 = ".isEmpty();";
  protected final String TEXT_724 = NL + "\t\t\t\treturn !";
  protected final String TEXT_725 = "().isEmpty();";
  protected final String TEXT_726 = NL + "\t\t\t\treturn isSet";
  protected final String TEXT_727 = "();";
  protected final String TEXT_728 = NL + "\t\t\t\treturn ";
  protected final String TEXT_729 = " != null;";
  protected final String TEXT_730 = NL + "\t\t\t\treturn basicGet";
  protected final String TEXT_731 = "() != null;";
  protected final String TEXT_732 = NL + "\t\t\t\treturn ";
  protected final String TEXT_733 = " != null;";
  protected final String TEXT_734 = NL + "\t\t\t\treturn ";
  protected final String TEXT_735 = "() != null;";
  protected final String TEXT_736 = NL + "\t\t\t\treturn ((";
  protected final String TEXT_737 = " & ";
  protected final String TEXT_738 = "_EFLAG) != 0) != ";
  protected final String TEXT_739 = "_EDEFAULT;";
  protected final String TEXT_740 = NL + "\t\t\t\treturn ";
  protected final String TEXT_741 = " != ";
  protected final String TEXT_742 = "_EDEFAULT;";
  protected final String TEXT_743 = NL + "\t\t\t\treturn ";
  protected final String TEXT_744 = "() != ";
  protected final String TEXT_745 = "_EDEFAULT;";
  protected final String TEXT_746 = NL + "\t\t\t\treturn ";
  protected final String TEXT_747 = "_EDEFAULT == null ? ";
  protected final String TEXT_748 = " != null : !";
  protected final String TEXT_749 = "_EDEFAULT.equals(";
  protected final String TEXT_750 = ");";
  protected final String TEXT_751 = NL + "\t\t\t\treturn ";
  protected final String TEXT_752 = "_EDEFAULT == null ? ";
  protected final String TEXT_753 = "() != null : !";
  protected final String TEXT_754 = "_EDEFAULT.equals(";
  protected final String TEXT_755 = "());";
  protected final String TEXT_756 = NL + "\t\t}" + NL + "\t\treturn eDynamicIsSet(eFeature);" + NL + "\t}" + NL;
  protected final String TEXT_757 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */" + NL + "\tpublic int eBaseStructuralFeatureID(int derivedFeatureID, Class baseClass)" + NL + "\t{";
  protected final String TEXT_758 = NL + "\t\tif (baseClass == ";
  protected final String TEXT_759 = ".class)" + NL + "\t\t{" + NL + "\t\t\tswitch (derivedFeatureID)" + NL + "\t\t\t{";
  protected final String TEXT_760 = NL + "\t\t\t\tcase ";
  protected final String TEXT_761 = ": return ";
  protected final String TEXT_762 = ";";
  protected final String TEXT_763 = NL + "\t\t\t\tdefault: return -1;" + NL + "\t\t\t}" + NL + "\t\t}";
  protected final String TEXT_764 = NL + "\t\treturn super.eBaseStructuralFeatureID(derivedFeatureID, baseClass);" + NL + "\t}" + NL + "" + NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */" + NL + "\tpublic int eDerivedStructuralFeatureID(int baseFeatureID, Class baseClass)" + NL + "\t{";
  protected final String TEXT_765 = NL + "\t\tif (baseClass == ";
  protected final String TEXT_766 = ".class)" + NL + "\t\t{" + NL + "\t\t\tswitch (baseFeatureID)" + NL + "\t\t\t{";
  protected final String TEXT_767 = NL + "\t\t\t\tcase ";
  protected final String TEXT_768 = ": return ";
  protected final String TEXT_769 = ";";
  protected final String TEXT_770 = NL + "\t\t\t\tdefault: return -1;" + NL + "\t\t\t}" + NL + "\t\t}";
  protected final String TEXT_771 = NL + "\t\treturn super.eDerivedStructuralFeatureID(baseFeatureID, baseClass);" + NL + "\t}" + NL;
  protected final String TEXT_772 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */" + NL + "\tpublic String toString()" + NL + "\t{" + NL + "\t\tif (eIsProxy()) return super.toString();" + NL + "" + NL + "\t\tStringBuffer result = new StringBuffer(super.toString());";
  protected final String TEXT_773 = NL + "\t\tresult.append(\" (";
  protected final String TEXT_774 = ": \");";
  protected final String TEXT_775 = NL + "\t\tresult.append(\", ";
  protected final String TEXT_776 = ": \");";
  protected final String TEXT_777 = NL + "\t\tif (";
  protected final String TEXT_778 = "(";
  protected final String TEXT_779 = " & ";
  protected final String TEXT_780 = "_ESETFLAG) != 0";
  protected final String TEXT_781 = "ESet";
  protected final String TEXT_782 = ") result.append((";
  protected final String TEXT_783 = " & ";
  protected final String TEXT_784 = "_EFLAG) != 0); else result.append(\"<unset>\");";
  protected final String TEXT_785 = NL + "\t\tif (";
  protected final String TEXT_786 = "(";
  protected final String TEXT_787 = " & ";
  protected final String TEXT_788 = "_ESETFLAG) != 0";
  protected final String TEXT_789 = "ESet";
  protected final String TEXT_790 = ") result.append(";
  protected final String TEXT_791 = "); else result.append(\"<unset>\");";
  protected final String TEXT_792 = NL + "\t\tresult.append((";
  protected final String TEXT_793 = " & ";
  protected final String TEXT_794 = "_EFLAG) != 0);";
  protected final String TEXT_795 = NL + "\t\tresult.append(";
  protected final String TEXT_796 = ");";
  protected final String TEXT_797 = NL + "\t\tresult.append(')');" + NL + "\t\treturn result.toString();" + NL + "\t}" + NL;
  protected final String TEXT_798 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */" + NL + "\tprotected int hash = -1;" + NL + "" + NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */" + NL + "\tpublic int getHash()" + NL + "\t{" + NL + "\t\tif (hash == -1)" + NL + "\t\t{" + NL + "\t\t\tObject theKey = getKey();" + NL + "\t\t\thash = (theKey == null ? 0 : theKey.hashCode());" + NL + "\t\t}" + NL + "\t\treturn hash;" + NL + "\t}" + NL + "" + NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */" + NL + "\tpublic void setHash(int hash)" + NL + "\t{" + NL + "\t\tthis.hash = hash;" + NL + "\t}" + NL + "" + NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */" + NL + "\tpublic Object getKey()" + NL + "\t{";
  protected final String TEXT_799 = NL + "\t\treturn new ";
  protected final String TEXT_800 = "(getTypedKey());";
  protected final String TEXT_801 = NL + "\t\treturn getTypedKey();";
  protected final String TEXT_802 = NL + "\t}" + NL + "" + NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */" + NL + "\tpublic void setKey(Object key)" + NL + "\t{";
  protected final String TEXT_803 = NL + "\t\tgetTypedKey().addAll((";
  protected final String TEXT_804 = ")key);";
  protected final String TEXT_805 = NL + "\t\tsetTypedKey(((";
  protected final String TEXT_806 = ")key).";
  protected final String TEXT_807 = "());";
  protected final String TEXT_808 = NL + "\t\tsetTypedKey((";
  protected final String TEXT_809 = ")key);";
  protected final String TEXT_810 = NL + "\t}" + NL + "" + NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */" + NL + "\tpublic Object getValue()" + NL + "\t{";
  protected final String TEXT_811 = NL + "\t\treturn new ";
  protected final String TEXT_812 = "(getTypedValue());";
  protected final String TEXT_813 = NL + "\t\treturn getTypedValue();";
  protected final String TEXT_814 = NL + "\t}" + NL + "" + NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */" + NL + "\tpublic Object setValue(Object value)" + NL + "\t{" + NL + "\t\tObject oldValue = getValue();";
  protected final String TEXT_815 = NL + "\t\tgetTypedValue().clear();" + NL + "\t\tgetTypedValue().addAll((";
  protected final String TEXT_816 = ")value);";
  protected final String TEXT_817 = NL + "\t\tsetTypedValue(((";
  protected final String TEXT_818 = ")value).";
  protected final String TEXT_819 = "());";
  protected final String TEXT_820 = NL + "\t\tsetTypedValue((";
  protected final String TEXT_821 = ")value);";
  protected final String TEXT_822 = NL + "\t\treturn oldValue;" + NL + "\t}" + NL + "" + NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */" + NL + "\tpublic ";
  protected final String TEXT_823 = " getEMap()" + NL + "\t{" + NL + "\t\t";
  protected final String TEXT_824 = " container = eContainer();" + NL + "\t\treturn container == null ? null : (";
  protected final String TEXT_825 = ")container.eGet(eContainmentFeature());" + NL + "\t}" + NL;
  protected final String TEXT_826 = NL + "} //";
  protected final String TEXT_827 = NL;

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
    if (genClass.isModelRoot() && genModel.isBooleanFlagsEnabled() && genModel.getBooleanFlagsReservedBits() == -1) {
    stringBuffer.append(TEXT_26);
    stringBuffer.append(genModel.getBooleanFlagsField());
    stringBuffer.append(TEXT_27);
    }
    if (!genModel.isReflectiveDelegation()) {
    for (Iterator i=genClass.getDeclaredFieldGenFeatures().iterator(); i.hasNext();) { GenFeature genFeature = (GenFeature)i.next();
    if (genFeature.isListType() || genFeature.isReferenceType()) {
    if (genClass.isField(genFeature)) {
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
    if (genClass.isField(genFeature)) {
    if (genClass.isFlag(genFeature)) {
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
    if (genClass.isESetField(genFeature)) {
    if (genClass.isESetFlag(genFeature)) {
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
    //Class/declaredFieldGenFeature.override.javajetinc
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
    if (genFeature.isGet()) {
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
    if (genFeature.isListType()) {
    stringBuffer.append(TEXT_93);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_94);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_95);
    stringBuffer.append(genClass.getListConstructor(genFeature));
    stringBuffer.append(TEXT_96);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(genFeature.isMapType() && genFeature.isEffectiveSuppressEMFTypes() ? ".map()" : "");
    stringBuffer.append(TEXT_97);
    } else if (genFeature.isContainer()) {
    stringBuffer.append(TEXT_98);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(TEXT_99);
    stringBuffer.append(genFeature.getImportedType());
    stringBuffer.append(TEXT_100);
    } else {
    if (genFeature.isResolveProxies()) {
    stringBuffer.append(TEXT_101);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_102);
    stringBuffer.append(genFeature.getSafeNameAsEObject());
    stringBuffer.append(TEXT_103);
    stringBuffer.append(genFeature.getImportedType());
    stringBuffer.append(TEXT_104);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_105);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_106);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_107);
    stringBuffer.append(genFeature.getImportedInternalType());
    stringBuffer.append(TEXT_108);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.InternalEObject"));
    stringBuffer.append(TEXT_109);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_110);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_111);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_112);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.impl.ENotificationImpl"));
    stringBuffer.append(TEXT_113);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.notify.Notification"));
    stringBuffer.append(TEXT_114);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(TEXT_115);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_116);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_117);
    }
    if (genClass.isFlag(genFeature)) {
    stringBuffer.append(TEXT_118);
    stringBuffer.append(genClass.getFlagsField(genFeature));
    stringBuffer.append(TEXT_119);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_120);
    } else {
    stringBuffer.append(TEXT_121);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_122);
    }
    }
    } else {//volatile
    if (genFeature.isResolveProxies() && !genFeature.isListType()) {
    stringBuffer.append(TEXT_123);
    stringBuffer.append(genFeature.getImportedType());
    stringBuffer.append(TEXT_124);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_125);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_126);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_127);
    stringBuffer.append(genFeature.getImportedInternalType());
    stringBuffer.append(TEXT_128);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.InternalEObject"));
    stringBuffer.append(TEXT_129);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_130);
    } else if (genFeature.hasDelegateFeature()) { GenFeature delegateFeature = genFeature.getDelegateFeature();
    if (genFeature.isFeatureMapType()) {
    if (delegateFeature.isFeatureMapWrapped()) {
    stringBuffer.append(TEXT_131);
    stringBuffer.append(genFeature.getImportedEffectiveFeatureMapWrapperClass());
    stringBuffer.append(TEXT_132);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.FeatureMap$Internal"));
    stringBuffer.append(TEXT_133);
    stringBuffer.append(delegateFeature.getImportedEffectiveFeatureMapWrapperInternalInterface());
    stringBuffer.append(TEXT_134);
    stringBuffer.append(delegateFeature.getAccessorName());
    stringBuffer.append(TEXT_135);
    stringBuffer.append(genFeature.getQualifiedFeatureAccessorName());
    stringBuffer.append(TEXT_136);
    } else {
    stringBuffer.append(TEXT_137);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.FeatureMap"));
    stringBuffer.append(TEXT_138);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.FeatureMap"));
    stringBuffer.append(TEXT_139);
    stringBuffer.append(delegateFeature.getAccessorName());
    stringBuffer.append(TEXT_140);
    stringBuffer.append(genFeature.getQualifiedFeatureAccessorName());
    stringBuffer.append(TEXT_141);
    }
    } else if (genFeature.isListType()) {
    if (delegateFeature.isFeatureMapWrapped()) {
    stringBuffer.append(TEXT_142);
    stringBuffer.append(delegateFeature.getImportedEffectiveFeatureMapWrapperInternalInterface());
    stringBuffer.append(TEXT_143);
    stringBuffer.append(delegateFeature.getAccessorName());
    stringBuffer.append(TEXT_144);
    stringBuffer.append(genFeature.getQualifiedFeatureAccessorName());
    stringBuffer.append(TEXT_145);
    } else {
    stringBuffer.append(TEXT_146);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.FeatureMap"));
    stringBuffer.append(TEXT_147);
    stringBuffer.append(delegateFeature.getAccessorName());
    stringBuffer.append(TEXT_148);
    stringBuffer.append(genFeature.getQualifiedFeatureAccessorName());
    stringBuffer.append(TEXT_149);
    }
    } else {
    if (delegateFeature.isFeatureMapWrapped()) {
    stringBuffer.append(TEXT_150);
    if (genFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_151);
    }
    stringBuffer.append(TEXT_152);
    stringBuffer.append(genFeature.getObjectType());
    stringBuffer.append(TEXT_153);
    stringBuffer.append(delegateFeature.getImportedEffectiveFeatureMapWrapperInternalInterface());
    stringBuffer.append(TEXT_154);
    stringBuffer.append(delegateFeature.getAccessorName());
    stringBuffer.append(TEXT_155);
    stringBuffer.append(genFeature.getQualifiedFeatureAccessorName());
    stringBuffer.append(TEXT_156);
    if (genFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_157);
    stringBuffer.append(genFeature.getPrimitiveValueFunction());
    stringBuffer.append(TEXT_158);
    }
    stringBuffer.append(TEXT_159);
    } else {
    stringBuffer.append(TEXT_160);
    if (genFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_161);
    }
    stringBuffer.append(TEXT_162);
    stringBuffer.append(genFeature.getObjectType());
    stringBuffer.append(TEXT_163);
    stringBuffer.append(delegateFeature.getAccessorName());
    stringBuffer.append(TEXT_164);
    stringBuffer.append(genFeature.getQualifiedFeatureAccessorName());
    stringBuffer.append(TEXT_165);
    if (genFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_166);
    stringBuffer.append(genFeature.getPrimitiveValueFunction());
    stringBuffer.append(TEXT_167);
    }
    stringBuffer.append(TEXT_168);
    }
    }
    } else {
    stringBuffer.append(TEXT_169);
    stringBuffer.append(genFeature.getFormattedName());
    stringBuffer.append(TEXT_170);
    stringBuffer.append(genFeature.getFeatureKind());
    stringBuffer.append(TEXT_171);
    //Class/getGenFeature.todo.override.javajetinc
    }
    }
    stringBuffer.append(TEXT_172);
    //Class/getGenFeature.override.javajetinc
    }
    if (genFeature.isBasicGet()) {
    stringBuffer.append(TEXT_173);
    stringBuffer.append(genFeature.getImportedType());
    stringBuffer.append(TEXT_174);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_175);
    if (!genFeature.isVolatile()) {
    stringBuffer.append(TEXT_176);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_177);
    } else if (genFeature.hasDelegateFeature()) { GenFeature delegateFeature = genFeature.getDelegateFeature();
    if (delegateFeature.isFeatureMapWrapped()) {
    stringBuffer.append(TEXT_178);
    stringBuffer.append(genFeature.getImportedType());
    stringBuffer.append(TEXT_179);
    stringBuffer.append(delegateFeature.getImportedEffectiveFeatureMapWrapperInternalInterface());
    stringBuffer.append(TEXT_180);
    stringBuffer.append(delegateFeature.getAccessorName());
    stringBuffer.append(TEXT_181);
    stringBuffer.append(genFeature.getQualifiedFeatureAccessorName());
    stringBuffer.append(TEXT_182);
    } else {
    stringBuffer.append(TEXT_183);
    stringBuffer.append(genFeature.getImportedType());
    stringBuffer.append(TEXT_184);
    stringBuffer.append(delegateFeature.getAccessorName());
    stringBuffer.append(TEXT_185);
    stringBuffer.append(genFeature.getQualifiedFeatureAccessorName());
    stringBuffer.append(TEXT_186);
    }
    } else {
    stringBuffer.append(TEXT_187);
    stringBuffer.append(genFeature.getFormattedName());
    stringBuffer.append(TEXT_188);
    stringBuffer.append(genFeature.getFeatureKind());
    stringBuffer.append(TEXT_189);
    //Class/basicGetGenFeature.todo.override.javajetinc
    }
    stringBuffer.append(TEXT_190);
    //Class/basicGetGenFeature.override.javajetinc
    }
    if (genFeature.isBasicSet()) {
    stringBuffer.append(TEXT_191);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.notify.NotificationChain"));
    stringBuffer.append(TEXT_192);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_193);
    stringBuffer.append(genFeature.getImportedInternalType());
    stringBuffer.append(TEXT_194);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_195);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.notify.NotificationChain"));
    stringBuffer.append(TEXT_196);
    if (!genFeature.isVolatile()) {
    stringBuffer.append(TEXT_197);
    stringBuffer.append(genFeature.getImportedType());
    stringBuffer.append(TEXT_198);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_199);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_200);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_201);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_202);
    if (genFeature.isUnsettable()) {
    if (genClass.isESetFlag(genFeature)) {
    stringBuffer.append(TEXT_203);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_204);
    stringBuffer.append(genClass.getESetFlagsField(genFeature));
    stringBuffer.append(TEXT_205);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_206);
    stringBuffer.append(genClass.getESetFlagsField(genFeature));
    stringBuffer.append(TEXT_207);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_208);
    } else {
    stringBuffer.append(TEXT_209);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_210);
    stringBuffer.append(genFeature.getUncapName());
    stringBuffer.append(TEXT_211);
    stringBuffer.append(genFeature.getUncapName());
    stringBuffer.append(TEXT_212);
    }
    }
    stringBuffer.append(TEXT_213);
    if (genFeature.isUnsettable()) {
    stringBuffer.append(TEXT_214);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.impl.ENotificationImpl"));
    stringBuffer.append(TEXT_215);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.impl.ENotificationImpl"));
    stringBuffer.append(TEXT_216);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.notify.Notification"));
    stringBuffer.append(TEXT_217);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(TEXT_218);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_219);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_220);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_221);
    } else {
    stringBuffer.append(TEXT_222);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.impl.ENotificationImpl"));
    stringBuffer.append(TEXT_223);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.impl.ENotificationImpl"));
    stringBuffer.append(TEXT_224);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.notify.Notification"));
    stringBuffer.append(TEXT_225);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(TEXT_226);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_227);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_228);
    }
    stringBuffer.append(TEXT_229);
    stringBuffer.append(TEXT_230);
    } else if (genFeature.hasDelegateFeature()) { GenFeature delegateFeature = genFeature.getDelegateFeature();
    if (delegateFeature.isFeatureMapWrapped()) {
    stringBuffer.append(TEXT_231);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.FeatureMap"));
    stringBuffer.append(TEXT_232);
    stringBuffer.append(delegateFeature.getImportedEffectiveFeatureMapWrapperInternalInterface());
    stringBuffer.append(TEXT_233);
    stringBuffer.append(delegateFeature.getAccessorName());
    stringBuffer.append(TEXT_234);
    stringBuffer.append(genFeature.getQualifiedFeatureAccessorName());
    stringBuffer.append(TEXT_235);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_236);
    } else {
    stringBuffer.append(TEXT_237);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.FeatureMap"));
    stringBuffer.append(TEXT_238);
    stringBuffer.append(delegateFeature.getAccessorName());
    stringBuffer.append(TEXT_239);
    stringBuffer.append(genFeature.getQualifiedFeatureAccessorName());
    stringBuffer.append(TEXT_240);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_241);
    }
    } else {
    stringBuffer.append(TEXT_242);
    stringBuffer.append(genFeature.getFormattedName());
    stringBuffer.append(TEXT_243);
    stringBuffer.append(genFeature.getFeatureKind());
    stringBuffer.append(TEXT_244);
    //Class/basicSetGenFeature.todo.override.javajetinc
    }
    stringBuffer.append(TEXT_245);
    //Class/basicSetGenFeature.override.javajetinc
    }
    if (genFeature.isSet()) {
    stringBuffer.append(TEXT_246);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_247);
    stringBuffer.append(genFeature.getImportedType());
    stringBuffer.append(TEXT_248);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_249);
    if (genModel.isReflectiveDelegation()) {
    stringBuffer.append(TEXT_250);
    stringBuffer.append(genFeature.getGenPackage().getImportedPackageInterfaceName());
    stringBuffer.append(TEXT_251);
    stringBuffer.append(genFeature.getFeatureAccessorName());
    stringBuffer.append(TEXT_252);
    if (genFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_253);
    stringBuffer.append(genFeature.getObjectType());
    stringBuffer.append(TEXT_254);
    }
    stringBuffer.append(TEXT_255);
    stringBuffer.append(genFeature.getCapName());
    if (genFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_256);
    }
    stringBuffer.append(TEXT_257);
    } else if (!genFeature.isVolatile()) {
    if (genFeature.isContainer()) { GenFeature reverseFeature = genFeature.getReverse(); GenClass targetClass = reverseFeature.getGenClass();
    stringBuffer.append(TEXT_258);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_259);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(TEXT_260);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_261);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.EcoreUtil"));
    stringBuffer.append(TEXT_262);
    stringBuffer.append(genFeature.getEObjectCast());
    stringBuffer.append(TEXT_263);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_264);
    stringBuffer.append(genModel.getImportedName("java.lang.IllegalArgumentException"));
    stringBuffer.append(TEXT_265);
    stringBuffer.append(genModel.getNonNLS());
    stringBuffer.append(TEXT_266);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.notify.NotificationChain"));
    stringBuffer.append(TEXT_267);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_268);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.InternalEObject"));
    stringBuffer.append(TEXT_269);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_270);
    stringBuffer.append(targetClass.getQualifiedFeatureID(reverseFeature));
    stringBuffer.append(TEXT_271);
    stringBuffer.append(targetClass.getImportedInterfaceName());
    stringBuffer.append(TEXT_272);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.InternalEObject"));
    stringBuffer.append(TEXT_273);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_274);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(TEXT_275);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.impl.ENotificationImpl"));
    stringBuffer.append(TEXT_276);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.notify.Notification"));
    stringBuffer.append(TEXT_277);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(TEXT_278);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_279);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_280);
    } else if (genFeature.isBidirectional() || genFeature.isContains()) {
    stringBuffer.append(TEXT_281);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_282);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_283);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.notify.NotificationChain"));
    stringBuffer.append(TEXT_284);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_285);
    if (!genFeature.isBidirectional()) {
    stringBuffer.append(TEXT_286);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.InternalEObject"));
    stringBuffer.append(TEXT_287);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_288);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(TEXT_289);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_290);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.InternalEObject"));
    stringBuffer.append(TEXT_291);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_292);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(TEXT_293);
    } else { GenFeature reverseFeature = genFeature.getReverse(); GenClass targetClass = reverseFeature.getGenClass();
    stringBuffer.append(TEXT_294);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.InternalEObject"));
    stringBuffer.append(TEXT_295);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_296);
    stringBuffer.append(targetClass.getQualifiedFeatureID(reverseFeature));
    stringBuffer.append(TEXT_297);
    stringBuffer.append(targetClass.getImportedInterfaceName());
    stringBuffer.append(TEXT_298);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_299);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.InternalEObject"));
    stringBuffer.append(TEXT_300);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_301);
    stringBuffer.append(targetClass.getQualifiedFeatureID(reverseFeature));
    stringBuffer.append(TEXT_302);
    stringBuffer.append(targetClass.getImportedInterfaceName());
    stringBuffer.append(TEXT_303);
    }
    stringBuffer.append(TEXT_304);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_305);
    stringBuffer.append(genFeature.getInternalTypeCast());
    stringBuffer.append(TEXT_306);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_307);
    if (genFeature.isUnsettable()) {
    stringBuffer.append(TEXT_308);
    if (genClass.isESetFlag(genFeature)) {
    stringBuffer.append(TEXT_309);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_310);
    stringBuffer.append(genClass.getESetFlagsField(genFeature));
    stringBuffer.append(TEXT_311);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_312);
    stringBuffer.append(genClass.getESetFlagsField(genFeature));
    stringBuffer.append(TEXT_313);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_314);
    } else {
    stringBuffer.append(TEXT_315);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_316);
    stringBuffer.append(genFeature.getUncapName());
    stringBuffer.append(TEXT_317);
    stringBuffer.append(genFeature.getUncapName());
    stringBuffer.append(TEXT_318);
    }
    stringBuffer.append(TEXT_319);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.impl.ENotificationImpl"));
    stringBuffer.append(TEXT_320);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.notify.Notification"));
    stringBuffer.append(TEXT_321);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(TEXT_322);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_323);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_324);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_325);
    } else {
    stringBuffer.append(TEXT_326);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.impl.ENotificationImpl"));
    stringBuffer.append(TEXT_327);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.notify.Notification"));
    stringBuffer.append(TEXT_328);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(TEXT_329);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_330);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_331);
    }
    } else {
    if (genClass.isFlag(genFeature)) {
    stringBuffer.append(TEXT_332);
    stringBuffer.append(genFeature.getImportedType());
    stringBuffer.append(TEXT_333);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_334);
    stringBuffer.append(genClass.getFlagsField(genFeature));
    stringBuffer.append(TEXT_335);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_336);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_337);
    stringBuffer.append(genClass.getFlagsField(genFeature));
    stringBuffer.append(TEXT_338);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_339);
    stringBuffer.append(genClass.getFlagsField(genFeature));
    stringBuffer.append(TEXT_340);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_341);
    } else {
    stringBuffer.append(TEXT_342);
    stringBuffer.append(genFeature.getImportedType());
    stringBuffer.append(TEXT_343);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_344);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_345);
    if (genFeature.isEnumType()) {
    stringBuffer.append(TEXT_346);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_347);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_348);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_349);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_350);
    } else {
    stringBuffer.append(TEXT_351);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_352);
    stringBuffer.append(genFeature.getInternalTypeCast());
    stringBuffer.append(TEXT_353);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_354);
    }
    }
    if (genFeature.isUnsettable()) {
    if (genClass.isESetFlag(genFeature)) {
    stringBuffer.append(TEXT_355);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_356);
    stringBuffer.append(genClass.getESetFlagsField(genFeature));
    stringBuffer.append(TEXT_357);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_358);
    stringBuffer.append(genClass.getESetFlagsField(genFeature));
    stringBuffer.append(TEXT_359);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_360);
    } else {
    stringBuffer.append(TEXT_361);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_362);
    stringBuffer.append(genFeature.getUncapName());
    stringBuffer.append(TEXT_363);
    stringBuffer.append(genFeature.getUncapName());
    stringBuffer.append(TEXT_364);
    }
    stringBuffer.append(TEXT_365);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.impl.ENotificationImpl"));
    stringBuffer.append(TEXT_366);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.notify.Notification"));
    stringBuffer.append(TEXT_367);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(TEXT_368);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_369);
    if (genClass.isFlag(genFeature)) {
    stringBuffer.append(TEXT_370);
    stringBuffer.append(genFeature.getCapName());
    } else {
    stringBuffer.append(genFeature.getSafeName());
    }
    stringBuffer.append(TEXT_371);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_372);
    } else {
    stringBuffer.append(TEXT_373);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.impl.ENotificationImpl"));
    stringBuffer.append(TEXT_374);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.notify.Notification"));
    stringBuffer.append(TEXT_375);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(TEXT_376);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_377);
    if (genClass.isFlag(genFeature)) {
    stringBuffer.append(TEXT_378);
    stringBuffer.append(genFeature.getCapName());
    } else {
    stringBuffer.append(genFeature.getSafeName());
    }
    stringBuffer.append(TEXT_379);
    }
    }
    } else if (genFeature.hasDelegateFeature()) { GenFeature delegateFeature = genFeature.getDelegateFeature();
    if (delegateFeature.isFeatureMapWrapped()) {
    stringBuffer.append(TEXT_380);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.FeatureMap"));
    stringBuffer.append(TEXT_381);
    stringBuffer.append(delegateFeature.getImportedEffectiveFeatureMapWrapperInternalInterface());
    stringBuffer.append(TEXT_382);
    stringBuffer.append(delegateFeature.getAccessorName());
    stringBuffer.append(TEXT_383);
    stringBuffer.append(genFeature.getQualifiedFeatureAccessorName());
    stringBuffer.append(TEXT_384);
    if (genFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_385);
    stringBuffer.append(genFeature.getObjectType());
    stringBuffer.append(TEXT_386);
    }
    stringBuffer.append(TEXT_387);
    stringBuffer.append(genFeature.getCapName());
    if (genFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_388);
    }
    stringBuffer.append(TEXT_389);
    } else {
    stringBuffer.append(TEXT_390);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.FeatureMap"));
    stringBuffer.append(TEXT_391);
    stringBuffer.append(delegateFeature.getAccessorName());
    stringBuffer.append(TEXT_392);
    stringBuffer.append(genFeature.getQualifiedFeatureAccessorName());
    stringBuffer.append(TEXT_393);
    if (genFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_394);
    stringBuffer.append(genFeature.getObjectType());
    stringBuffer.append(TEXT_395);
    }
    stringBuffer.append(TEXT_396);
    stringBuffer.append(genFeature.getCapName());
    if (genFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_397);
    }
    stringBuffer.append(TEXT_398);
    }
    } else {
    stringBuffer.append(TEXT_399);
    stringBuffer.append(genFeature.getFormattedName());
    stringBuffer.append(TEXT_400);
    stringBuffer.append(genFeature.getFeatureKind());
    stringBuffer.append(TEXT_401);
    //Class/setGenFeature.todo.override.javajetinc
    }
    stringBuffer.append(TEXT_402);
    //Class/setGenFeature.override.javajetinc
    }
    if (genFeature.isBasicUnset()) {
    stringBuffer.append(TEXT_403);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.notify.NotificationChain"));
    stringBuffer.append(TEXT_404);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_405);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.notify.NotificationChain"));
    stringBuffer.append(TEXT_406);
    if (!genFeature.isVolatile()) {
    stringBuffer.append(TEXT_407);
    stringBuffer.append(genFeature.getImportedType());
    stringBuffer.append(TEXT_408);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_409);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_410);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_411);
    if (genClass.isESetFlag(genFeature)) {
    stringBuffer.append(TEXT_412);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_413);
    stringBuffer.append(genClass.getESetFlagsField(genFeature));
    stringBuffer.append(TEXT_414);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_415);
    stringBuffer.append(genClass.getESetFlagsField(genFeature));
    stringBuffer.append(TEXT_416);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_417);
    } else {
    stringBuffer.append(TEXT_418);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_419);
    stringBuffer.append(genFeature.getUncapName());
    stringBuffer.append(TEXT_420);
    stringBuffer.append(genFeature.getUncapName());
    stringBuffer.append(TEXT_421);
    }
    stringBuffer.append(TEXT_422);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.impl.ENotificationImpl"));
    stringBuffer.append(TEXT_423);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.impl.ENotificationImpl"));
    stringBuffer.append(TEXT_424);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.notify.Notification"));
    stringBuffer.append(TEXT_425);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(TEXT_426);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_427);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_428);
    } else {
    stringBuffer.append(TEXT_429);
    stringBuffer.append(genFeature.getFormattedName());
    stringBuffer.append(TEXT_430);
    stringBuffer.append(genFeature.getFeatureKind());
    stringBuffer.append(TEXT_431);
    //Class/basicUnsetGenFeature.todo.override.javajetinc
    }
    stringBuffer.append(TEXT_432);
    //Class.basicUnsetGenFeature.override.javajetinc
    }
    if (genFeature.isUnset()) {
    stringBuffer.append(TEXT_433);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_434);
    if (genModel.isReflectiveDelegation()) {
    stringBuffer.append(TEXT_435);
    stringBuffer.append(genFeature.getGenPackage().getImportedPackageInterfaceName());
    stringBuffer.append(TEXT_436);
    stringBuffer.append(genFeature.getFeatureAccessorName());
    stringBuffer.append(TEXT_437);
    } else if (!genFeature.isVolatile()) {
    if (genFeature.isListType()) {
    stringBuffer.append(TEXT_438);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.InternalEList"));
    stringBuffer.append(TEXT_439);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_440);
    } else if (genFeature.isBidirectional() || genFeature.isContains()) {
    stringBuffer.append(TEXT_441);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_442);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.notify.NotificationChain"));
    stringBuffer.append(TEXT_443);
    if (!genFeature.isBidirectional()) {
    stringBuffer.append(TEXT_444);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.InternalEObject"));
    stringBuffer.append(TEXT_445);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_446);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(TEXT_447);
    } else { GenFeature reverseFeature = genFeature.getReverse(); GenClass targetClass = reverseFeature.getGenClass();
    stringBuffer.append(TEXT_448);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.InternalEObject"));
    stringBuffer.append(TEXT_449);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_450);
    stringBuffer.append(targetClass.getQualifiedFeatureID(reverseFeature));
    stringBuffer.append(TEXT_451);
    stringBuffer.append(targetClass.getImportedInterfaceName());
    stringBuffer.append(TEXT_452);
    }
    stringBuffer.append(TEXT_453);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_454);
    if (genClass.isESetFlag(genFeature)) {
    stringBuffer.append(TEXT_455);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_456);
    stringBuffer.append(genClass.getESetFlagsField(genFeature));
    stringBuffer.append(TEXT_457);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_458);
    stringBuffer.append(genClass.getESetFlagsField(genFeature));
    stringBuffer.append(TEXT_459);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_460);
    } else {
    stringBuffer.append(TEXT_461);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_462);
    stringBuffer.append(genFeature.getUncapName());
    stringBuffer.append(TEXT_463);
    stringBuffer.append(genFeature.getUncapName());
    stringBuffer.append(TEXT_464);
    }
    stringBuffer.append(TEXT_465);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.impl.ENotificationImpl"));
    stringBuffer.append(TEXT_466);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.notify.Notification"));
    stringBuffer.append(TEXT_467);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(TEXT_468);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_469);
    } else {
    if (genClass.isFlag(genFeature)) {
    stringBuffer.append(TEXT_470);
    stringBuffer.append(genFeature.getImportedType());
    stringBuffer.append(TEXT_471);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_472);
    stringBuffer.append(genClass.getFlagsField(genFeature));
    stringBuffer.append(TEXT_473);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_474);
    } else {
    stringBuffer.append(TEXT_475);
    stringBuffer.append(genFeature.getImportedType());
    stringBuffer.append(TEXT_476);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_477);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_478);
    }
    if (genClass.isESetFlag(genFeature)) {
    stringBuffer.append(TEXT_479);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_480);
    stringBuffer.append(genClass.getESetFlagsField(genFeature));
    stringBuffer.append(TEXT_481);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_482);
    } else {
    stringBuffer.append(TEXT_483);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_484);
    stringBuffer.append(genFeature.getUncapName());
    stringBuffer.append(TEXT_485);
    }
    if (genFeature.isReferenceType()) {
    stringBuffer.append(TEXT_486);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_487);
    if (genClass.isESetFlag(genFeature)) {
    stringBuffer.append(TEXT_488);
    stringBuffer.append(genClass.getESetFlagsField(genFeature));
    stringBuffer.append(TEXT_489);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_490);
    } else {
    stringBuffer.append(TEXT_491);
    stringBuffer.append(genFeature.getUncapName());
    stringBuffer.append(TEXT_492);
    }
    stringBuffer.append(TEXT_493);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.impl.ENotificationImpl"));
    stringBuffer.append(TEXT_494);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.notify.Notification"));
    stringBuffer.append(TEXT_495);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(TEXT_496);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_497);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_498);
    } else {
    if (genClass.isFlag(genFeature)) {
    stringBuffer.append(TEXT_499);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_500);
    stringBuffer.append(genClass.getFlagsField(genFeature));
    stringBuffer.append(TEXT_501);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_502);
    stringBuffer.append(genClass.getFlagsField(genFeature));
    stringBuffer.append(TEXT_503);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_504);
    } else {
    stringBuffer.append(TEXT_505);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_506);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_507);
    }
    if (genClass.isESetFlag(genFeature)) {
    stringBuffer.append(TEXT_508);
    stringBuffer.append(genClass.getESetFlagsField(genFeature));
    stringBuffer.append(TEXT_509);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_510);
    } else {
    stringBuffer.append(TEXT_511);
    stringBuffer.append(genFeature.getUncapName());
    stringBuffer.append(TEXT_512);
    }
    stringBuffer.append(TEXT_513);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.impl.ENotificationImpl"));
    stringBuffer.append(TEXT_514);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.notify.Notification"));
    stringBuffer.append(TEXT_515);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(TEXT_516);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_517);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_518);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_519);
    }
    }
    } else if (genFeature.hasDelegateFeature()) { GenFeature delegateFeature = genFeature.getDelegateFeature();
    if (delegateFeature.isFeatureMapWrapped()) {
    stringBuffer.append(TEXT_520);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.FeatureMap"));
    stringBuffer.append(TEXT_521);
    stringBuffer.append(delegateFeature.getImportedEffectiveFeatureMapWrapperInternalInterface());
    stringBuffer.append(TEXT_522);
    stringBuffer.append(delegateFeature.getAccessorName());
    stringBuffer.append(TEXT_523);
    stringBuffer.append(genFeature.getQualifiedFeatureAccessorName());
    stringBuffer.append(TEXT_524);
    } else {
    stringBuffer.append(TEXT_525);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.FeatureMap"));
    stringBuffer.append(TEXT_526);
    stringBuffer.append(delegateFeature.getAccessorName());
    stringBuffer.append(TEXT_527);
    stringBuffer.append(genFeature.getQualifiedFeatureAccessorName());
    stringBuffer.append(TEXT_528);
    }
    } else {
    stringBuffer.append(TEXT_529);
    stringBuffer.append(genFeature.getFormattedName());
    stringBuffer.append(TEXT_530);
    stringBuffer.append(genFeature.getFeatureKind());
    stringBuffer.append(TEXT_531);
    //Class/unsetGenFeature.todo.override.javajetinc
    }
    stringBuffer.append(TEXT_532);
    //Class/unsetGenFeature.override.javajetinc
    }
    if (genFeature.isIsSet()) {
    stringBuffer.append(TEXT_533);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_534);
    if (genModel.isReflectiveDelegation()) {
    stringBuffer.append(TEXT_535);
    stringBuffer.append(genFeature.getGenPackage().getImportedPackageInterfaceName());
    stringBuffer.append(TEXT_536);
    stringBuffer.append(genFeature.getFeatureAccessorName());
    stringBuffer.append(TEXT_537);
    } else if (!genFeature.isVolatile()) {
    if (genFeature.isListType()) {
    stringBuffer.append(TEXT_538);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_539);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.InternalEList"));
    stringBuffer.append(TEXT_540);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_541);
    } else {
    if (genClass.isESetFlag(genFeature)) {
    stringBuffer.append(TEXT_542);
    stringBuffer.append(genClass.getESetFlagsField(genFeature));
    stringBuffer.append(TEXT_543);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_544);
    } else {
    stringBuffer.append(TEXT_545);
    stringBuffer.append(genFeature.getUncapName());
    stringBuffer.append(TEXT_546);
    }
    }
    } else if (genFeature.hasDelegateFeature()) { GenFeature delegateFeature = genFeature.getDelegateFeature();
    if (delegateFeature.isFeatureMapWrapped()) {
    stringBuffer.append(TEXT_547);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.FeatureMap"));
    stringBuffer.append(TEXT_548);
    stringBuffer.append(delegateFeature.getImportedEffectiveFeatureMapWrapperInternalInterface());
    stringBuffer.append(TEXT_549);
    stringBuffer.append(delegateFeature.getAccessorName());
    stringBuffer.append(TEXT_550);
    stringBuffer.append(genFeature.getQualifiedFeatureAccessorName());
    stringBuffer.append(TEXT_551);
    } else {
    stringBuffer.append(TEXT_552);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.FeatureMap"));
    stringBuffer.append(TEXT_553);
    stringBuffer.append(delegateFeature.getAccessorName());
    stringBuffer.append(TEXT_554);
    stringBuffer.append(genFeature.getQualifiedFeatureAccessorName());
    stringBuffer.append(TEXT_555);
    }
    } else {
    stringBuffer.append(TEXT_556);
    stringBuffer.append(genFeature.getFormattedName());
    stringBuffer.append(TEXT_557);
    stringBuffer.append(genFeature.getFeatureKind());
    stringBuffer.append(TEXT_558);
    //Class/isSetGenFeature.todo.override.javajetinc
    }
    stringBuffer.append(TEXT_559);
    //Class/isSetGenFeature.override.javajetinc
    }
    //Class/implementedGenFeature.override.javajetinc
    }//for
    for (Iterator i=genClass.getImplementedGenOperations().iterator(); i.hasNext();) { GenOperation genOperation = (GenOperation)i.next();
    stringBuffer.append(TEXT_560);
    stringBuffer.append(genOperation.getImportedReturnType());
    stringBuffer.append(TEXT_561);
    stringBuffer.append(genOperation.getName());
    stringBuffer.append(TEXT_562);
    stringBuffer.append(genOperation.getParameters());
    stringBuffer.append(TEXT_563);
    stringBuffer.append(genOperation.getThrows());
    stringBuffer.append(TEXT_564);
    if (genOperation.hasBody()) {
    stringBuffer.append(TEXT_565);
    stringBuffer.append(genOperation.getBody(genModel.getIndentation(stringBuffer)));
    } else if (genOperation.isInvariant()) {GenClass opClass = genOperation.getGenClass(); String diagnostics = ((GenParameter)genOperation.getGenParameters().get(0)).getName(); String context = ((GenParameter)genOperation.getGenParameters().get(1)).getName();
    stringBuffer.append(TEXT_566);
    stringBuffer.append(diagnostics);
    stringBuffer.append(TEXT_567);
    stringBuffer.append(diagnostics);
    stringBuffer.append(TEXT_568);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.util.BasicDiagnostic"));
    stringBuffer.append(TEXT_569);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.util.Diagnostic"));
    stringBuffer.append(TEXT_570);
    stringBuffer.append(opClass.getGenPackage().getImportedValidatorClassName());
    stringBuffer.append(TEXT_571);
    stringBuffer.append(opClass.getGenPackage().getImportedValidatorClassName());
    stringBuffer.append(TEXT_572);
    stringBuffer.append(opClass.getOperationID(genOperation));
    stringBuffer.append(TEXT_573);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.plugin.EcorePlugin"));
    stringBuffer.append(TEXT_574);
    stringBuffer.append(genOperation.getName());
    stringBuffer.append(TEXT_575);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.EObjectValidator"));
    stringBuffer.append(TEXT_576);
    stringBuffer.append(context);
    stringBuffer.append(TEXT_577);
    stringBuffer.append(genModel.getNonNLS());
    stringBuffer.append(genModel.getNonNLS(2));
    stringBuffer.append(TEXT_578);
    } else {
    stringBuffer.append(TEXT_579);
    //Class/implementedGenOperation.todo.override.javajetinc
    }
    stringBuffer.append(TEXT_580);
    //Class/implementedGenOperation.override.javajetinc
    }//for
    if (!genModel.isReflectiveDelegation() && !genClass.getEInverseAddGenFeatures().isEmpty()) {
    stringBuffer.append(TEXT_581);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.notify.NotificationChain"));
    stringBuffer.append(TEXT_582);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.InternalEObject"));
    stringBuffer.append(TEXT_583);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.notify.NotificationChain"));
    stringBuffer.append(TEXT_584);
    for (Iterator i=genClass.getEInverseAddGenFeatures().iterator(); i.hasNext();) { GenFeature genFeature = (GenFeature)i.next();
    stringBuffer.append(TEXT_585);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(TEXT_586);
    if (genFeature.isListType()) {
    if (genFeature.isMapType() && genFeature.isEffectiveSuppressEMFTypes()) {
    stringBuffer.append(TEXT_587);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.InternalEList"));
    stringBuffer.append(TEXT_588);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.util.EMap$InternalMapView"));
    stringBuffer.append(TEXT_589);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_590);
    } else {
    stringBuffer.append(TEXT_591);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.InternalEList"));
    stringBuffer.append(TEXT_592);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_593);
    }
    } else if (genFeature.isContainer()) {
    stringBuffer.append(TEXT_594);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(TEXT_595);
    } else {
    stringBuffer.append(TEXT_596);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_597);
    if (genFeature.isContains()) {
    stringBuffer.append(TEXT_598);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.InternalEObject"));
    stringBuffer.append(TEXT_599);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_600);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(TEXT_601);
    } else { GenFeature reverseFeature = genFeature.getReverse(); GenClass targetClass = reverseFeature.getGenClass();
    stringBuffer.append(TEXT_602);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.InternalEObject"));
    stringBuffer.append(TEXT_603);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_604);
    stringBuffer.append(targetClass.getQualifiedFeatureID(reverseFeature));
    stringBuffer.append(TEXT_605);
    stringBuffer.append(targetClass.getImportedInterfaceName());
    stringBuffer.append(TEXT_606);
    }
    stringBuffer.append(TEXT_607);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_608);
    stringBuffer.append(genFeature.getImportedType());
    stringBuffer.append(TEXT_609);
    }
    }
    stringBuffer.append(TEXT_610);
    }
    if (!genModel.isReflectiveDelegation() && !genClass.getEInverseRemoveGenFeatures().isEmpty()) {
    stringBuffer.append(TEXT_611);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.notify.NotificationChain"));
    stringBuffer.append(TEXT_612);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.InternalEObject"));
    stringBuffer.append(TEXT_613);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.notify.NotificationChain"));
    stringBuffer.append(TEXT_614);
    for (Iterator i=genClass.getEInverseRemoveGenFeatures().iterator(); i.hasNext();) { GenFeature genFeature = (GenFeature)i.next();
    stringBuffer.append(TEXT_615);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(TEXT_616);
    if (genFeature.isListType()) {
    if (genFeature.isMapType() && genFeature.isEffectiveSuppressEMFTypes()) {
    stringBuffer.append(TEXT_617);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.InternalEList"));
    stringBuffer.append(TEXT_618);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.util.EMap$InternalMapView"));
    stringBuffer.append(TEXT_619);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_620);
    } else if (genFeature.isFeatureMapWrapped()) {
    stringBuffer.append(TEXT_621);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.InternalEList"));
    stringBuffer.append(TEXT_622);
    stringBuffer.append(genFeature.getImportedEffectiveFeatureMapWrapperInternalInterface());
    stringBuffer.append(TEXT_623);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_624);
    } else {
    stringBuffer.append(TEXT_625);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.InternalEList"));
    stringBuffer.append(TEXT_626);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_627);
    }
    } else if (genFeature.isContainer()) {
    stringBuffer.append(TEXT_628);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(TEXT_629);
    } else if (genFeature.isUnsettable()) {
    stringBuffer.append(TEXT_630);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_631);
    } else {
    stringBuffer.append(TEXT_632);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_633);
    }
    }
    stringBuffer.append(TEXT_634);
    }
    if (!genModel.isReflectiveDelegation() && !genClass.getEBasicRemoveFromContainerGenFeatures().isEmpty()) {
    stringBuffer.append(TEXT_635);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.notify.NotificationChain"));
    stringBuffer.append(TEXT_636);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.notify.NotificationChain"));
    stringBuffer.append(TEXT_637);
    for (Iterator i=genClass.getEBasicRemoveFromContainerGenFeatures().iterator(); i.hasNext();) { GenFeature genFeature = (GenFeature)i.next();
    GenFeature reverseFeature = genFeature.getReverse(); GenClass targetClass = reverseFeature.getGenClass();
    stringBuffer.append(TEXT_638);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(TEXT_639);
    stringBuffer.append(targetClass.getQualifiedFeatureID(reverseFeature));
    stringBuffer.append(TEXT_640);
    stringBuffer.append(targetClass.getImportedInterfaceName());
    stringBuffer.append(TEXT_641);
    }
    stringBuffer.append(TEXT_642);
    }
    if (!genModel.isReflectiveDelegation() && !genClass.getAllGenFeatures().isEmpty()) {
    stringBuffer.append(TEXT_643);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.EStructuralFeature"));
    stringBuffer.append(TEXT_644);
    for (Iterator i=genClass.getAllGenFeatures().iterator(); i.hasNext();) { GenFeature genFeature = (GenFeature)i.next();
    stringBuffer.append(TEXT_645);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(TEXT_646);
    if (genFeature.isPrimitiveType() && !genFeature.isListType()) {
    if (genFeature.isBooleanType()) {
    stringBuffer.append(TEXT_647);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_648);
    } else {
    stringBuffer.append(TEXT_649);
    stringBuffer.append(genFeature.getObjectType());
    stringBuffer.append(TEXT_650);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_651);
    }
    } else if (genFeature.isResolveProxies() && !genFeature.isListType()) {
    stringBuffer.append(TEXT_652);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_653);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_654);
    } else if (genFeature.isMapType() && genFeature.isEffectiveSuppressEMFTypes()) {
    stringBuffer.append(TEXT_655);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.util.EMap$InternalMapView"));
    stringBuffer.append(TEXT_656);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_657);
    } else if (genFeature.isFeatureMapWrapped()) {
    stringBuffer.append(TEXT_658);
    stringBuffer.append(genFeature.getImportedEffectiveFeatureMapWrapperInternalInterface());
    stringBuffer.append(TEXT_659);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_660);
    } else {
    stringBuffer.append(TEXT_661);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_662);
    }
    }
    stringBuffer.append(TEXT_663);
    }
    if (!genModel.isReflectiveDelegation() && !genClass.getESetGenFeatures().isEmpty()) {
    stringBuffer.append(TEXT_664);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.EStructuralFeature"));
    stringBuffer.append(TEXT_665);
    for (Iterator i=genClass.getESetGenFeatures().iterator(); i.hasNext();) { GenFeature genFeature = (GenFeature)i.next();
    stringBuffer.append(TEXT_666);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(TEXT_667);
    if (genFeature.isListType()) {
    if (genFeature.isFeatureMapWrapped()) {
    stringBuffer.append(TEXT_668);
    stringBuffer.append(genFeature.getImportedEffectiveFeatureMapWrapperInternalInterface());
    stringBuffer.append(TEXT_669);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_670);
    } else {
    stringBuffer.append(TEXT_671);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_672);
    }
    if (genFeature.isFeatureMapWrapped()) {
    stringBuffer.append(TEXT_673);
    stringBuffer.append(genFeature.getImportedEffectiveFeatureMapWrapperInternalInterface());
    stringBuffer.append(TEXT_674);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_675);
    stringBuffer.append(genModel.getImportedName("java.util.Collection"));
    stringBuffer.append(TEXT_676);
    } else if (genFeature.isMapType() && genFeature.isEffectiveSuppressEMFTypes()) {
    stringBuffer.append(TEXT_677);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.util.EMap$InternalMapView"));
    stringBuffer.append(TEXT_678);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_679);
    stringBuffer.append(genModel.getImportedName("java.util.Collection"));
    stringBuffer.append(TEXT_680);
    } else {
    stringBuffer.append(TEXT_681);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_682);
    stringBuffer.append(genModel.getImportedName("java.util.Collection"));
    stringBuffer.append(TEXT_683);
    }
    } else if (genFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_684);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_685);
    stringBuffer.append(genFeature.getObjectType());
    stringBuffer.append(TEXT_686);
    stringBuffer.append(genFeature.getPrimitiveValueFunction());
    stringBuffer.append(TEXT_687);
    } else {
    stringBuffer.append(TEXT_688);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_689);
    stringBuffer.append(genFeature.getImportedType());
    stringBuffer.append(TEXT_690);
    }
    stringBuffer.append(TEXT_691);
    }
    stringBuffer.append(TEXT_692);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.EStructuralFeature"));
    stringBuffer.append(TEXT_693);
    for (Iterator i=genClass.getESetGenFeatures().iterator(); i.hasNext();) { GenFeature genFeature = (GenFeature)i.next();
    stringBuffer.append(TEXT_694);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(TEXT_695);
    if (genFeature.isListType() && !genFeature.isUnsettable()) {
    if (genFeature.isFeatureMapWrapped()) {
    stringBuffer.append(TEXT_696);
    stringBuffer.append(genFeature.getImportedEffectiveFeatureMapWrapperInternalInterface());
    stringBuffer.append(TEXT_697);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_698);
    } else {
    stringBuffer.append(TEXT_699);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_700);
    }
    } else if (genFeature.isUnsettable()) {
    stringBuffer.append(TEXT_701);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_702);
    } else if (genFeature.isReferenceType()) {
    stringBuffer.append(TEXT_703);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_704);
    stringBuffer.append(genFeature.getImportedType());
    stringBuffer.append(TEXT_705);
    } else {
    stringBuffer.append(TEXT_706);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_707);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_708);
    }
    stringBuffer.append(TEXT_709);
    }
    stringBuffer.append(TEXT_710);
    }
    if (!genModel.isReflectiveDelegation() && !genClass.getAllGenFeatures().isEmpty()) {
    stringBuffer.append(TEXT_711);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.EStructuralFeature"));
    stringBuffer.append(TEXT_712);
    for (Iterator i=genClass.getAllGenFeatures().iterator(); i.hasNext();) { GenFeature genFeature = (GenFeature)i.next();
    stringBuffer.append(TEXT_713);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(TEXT_714);
    if (genFeature.isListType() && !genFeature.isUnsettable()) {
    if (genFeature.isFeatureMapWrapped()) {
    if (genFeature.isVolatile()) {
    stringBuffer.append(TEXT_715);
    stringBuffer.append(genFeature.getImportedEffectiveFeatureMapWrapperInternalInterface());
    stringBuffer.append(TEXT_716);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_717);
    } else {
    stringBuffer.append(TEXT_718);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_719);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_720);
    }
    } else {
    if (genClass.isField(genFeature)) {
    stringBuffer.append(TEXT_721);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_722);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_723);
    } else {
    stringBuffer.append(TEXT_724);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_725);
    }
    }
    } else if (genFeature.isUnsettable()) {
    stringBuffer.append(TEXT_726);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_727);
    } else if (genFeature.isResolveProxies()) {
    if (genClass.isField(genFeature)) {
    stringBuffer.append(TEXT_728);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_729);
    } else {
    stringBuffer.append(TEXT_730);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_731);
    }
    } else if (genFeature.isReferenceType()) {
    if (genClass.isField(genFeature)) {
    stringBuffer.append(TEXT_732);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_733);
    } else {
    stringBuffer.append(TEXT_734);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_735);
    }
    } else if (genFeature.isPrimitiveType() || genFeature.isEnumType()) {
    if (genClass.isField(genFeature)) {
    if (genClass.isFlag(genFeature)) {
    stringBuffer.append(TEXT_736);
    stringBuffer.append(genClass.getFlagsField(genFeature));
    stringBuffer.append(TEXT_737);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_738);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_739);
    } else {
    stringBuffer.append(TEXT_740);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_741);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_742);
    }
    } else {
    stringBuffer.append(TEXT_743);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_744);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_745);
    }
    } else {//datatype
    if (genClass.isField(genFeature)) {
    stringBuffer.append(TEXT_746);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_747);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_748);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_749);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_750);
    } else {
    stringBuffer.append(TEXT_751);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_752);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_753);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_754);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_755);
    }
    }
    }
    stringBuffer.append(TEXT_756);
    }
    if (!genClass.getMixinGenFeatures().isEmpty()) {
    stringBuffer.append(TEXT_757);
    for (Iterator m=genClass.getMixinGenClasses().iterator(); m.hasNext();) { GenClass mixinGenClass = (GenClass)m.next(); 
    stringBuffer.append(TEXT_758);
    stringBuffer.append(mixinGenClass.getImportedInterfaceName());
    stringBuffer.append(TEXT_759);
    for (Iterator f=mixinGenClass.getGenFeatures().iterator(); f.hasNext();) { GenFeature genFeature = (GenFeature)f.next(); 
    stringBuffer.append(TEXT_760);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(TEXT_761);
    stringBuffer.append(mixinGenClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(TEXT_762);
    }
    stringBuffer.append(TEXT_763);
    }
    stringBuffer.append(TEXT_764);
    for (Iterator m=genClass.getMixinGenClasses().iterator(); m.hasNext();) { GenClass mixinGenClass = (GenClass)m.next(); 
    stringBuffer.append(TEXT_765);
    stringBuffer.append(mixinGenClass.getImportedInterfaceName());
    stringBuffer.append(TEXT_766);
    for (Iterator f=mixinGenClass.getGenFeatures().iterator(); f.hasNext();) { GenFeature genFeature = (GenFeature)f.next(); 
    stringBuffer.append(TEXT_767);
    stringBuffer.append(mixinGenClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(TEXT_768);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(TEXT_769);
    }
    stringBuffer.append(TEXT_770);
    }
    stringBuffer.append(TEXT_771);
    }
    if (!genModel.isReflectiveDelegation() && !genClass.getToStringGenFeatures().isEmpty()) {
    stringBuffer.append(TEXT_772);
    { boolean first = true;
    for (Iterator i=genClass.getToStringGenFeatures().iterator(); i.hasNext(); ) { GenFeature genFeature = (GenFeature)i.next();
    if (first) { first = false;
    stringBuffer.append(TEXT_773);
    stringBuffer.append(genFeature.getName());
    stringBuffer.append(TEXT_774);
    stringBuffer.append(genModel.getNonNLS());
    } else {
    stringBuffer.append(TEXT_775);
    stringBuffer.append(genFeature.getName());
    stringBuffer.append(TEXT_776);
    stringBuffer.append(genModel.getNonNLS());
    }
    if (genFeature.isUnsettable() && !genFeature.isListType()) {
    if (genClass.isFlag(genFeature)) {
    stringBuffer.append(TEXT_777);
    if (genClass.isESetFlag(genFeature)) {
    stringBuffer.append(TEXT_778);
    stringBuffer.append(genClass.getESetFlagsField(genFeature));
    stringBuffer.append(TEXT_779);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_780);
    } else {
    stringBuffer.append(genFeature.getUncapName());
    stringBuffer.append(TEXT_781);
    }
    stringBuffer.append(TEXT_782);
    stringBuffer.append(genClass.getFlagsField(genFeature));
    stringBuffer.append(TEXT_783);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_784);
    stringBuffer.append(genModel.getNonNLS());
    } else {
    stringBuffer.append(TEXT_785);
    if (genClass.isESetFlag(genFeature)) {
    stringBuffer.append(TEXT_786);
    stringBuffer.append(genClass.getESetFlagsField(genFeature));
    stringBuffer.append(TEXT_787);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_788);
    } else {
    stringBuffer.append(genFeature.getUncapName());
    stringBuffer.append(TEXT_789);
    }
    stringBuffer.append(TEXT_790);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_791);
    stringBuffer.append(genModel.getNonNLS());
    }
    } else {
    if (genClass.isFlag(genFeature)) {
    stringBuffer.append(TEXT_792);
    stringBuffer.append(genClass.getFlagsField(genFeature));
    stringBuffer.append(TEXT_793);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_794);
    } else {
    stringBuffer.append(TEXT_795);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_796);
    }
    }
    }
    }
    stringBuffer.append(TEXT_797);
    }
    if (genClass.isMapEntry()) { GenFeature keyFeature = genClass.getMapEntryKeyFeature(); GenFeature valueFeature = genClass.getMapEntryValueFeature();
    stringBuffer.append(TEXT_798);
    if (keyFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_799);
    stringBuffer.append(keyFeature.getObjectType());
    stringBuffer.append(TEXT_800);
    } else {
    stringBuffer.append(TEXT_801);
    }
    stringBuffer.append(TEXT_802);
    if (keyFeature.isListType()) {
    stringBuffer.append(TEXT_803);
    stringBuffer.append(genModel.getImportedName("java.util.Collection"));
    stringBuffer.append(TEXT_804);
    } else if (keyFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_805);
    stringBuffer.append(keyFeature.getObjectType());
    stringBuffer.append(TEXT_806);
    stringBuffer.append(keyFeature.getPrimitiveValueFunction());
    stringBuffer.append(TEXT_807);
    } else {
    stringBuffer.append(TEXT_808);
    stringBuffer.append(keyFeature.getImportedType());
    stringBuffer.append(TEXT_809);
    }
    stringBuffer.append(TEXT_810);
    if (valueFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_811);
    stringBuffer.append(valueFeature.getObjectType());
    stringBuffer.append(TEXT_812);
    } else {
    stringBuffer.append(TEXT_813);
    }
    stringBuffer.append(TEXT_814);
    if (valueFeature.isListType()) {
    stringBuffer.append(TEXT_815);
    stringBuffer.append(genModel.getImportedName("java.util.Collection"));
    stringBuffer.append(TEXT_816);
    } else if (valueFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_817);
    stringBuffer.append(valueFeature.getObjectType());
    stringBuffer.append(TEXT_818);
    stringBuffer.append(valueFeature.getPrimitiveValueFunction());
    stringBuffer.append(TEXT_819);
    } else {
    stringBuffer.append(TEXT_820);
    stringBuffer.append(valueFeature.getImportedType());
    stringBuffer.append(TEXT_821);
    }
    stringBuffer.append(TEXT_822);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.util.EMap"));
    stringBuffer.append(TEXT_823);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.EObject"));
    stringBuffer.append(TEXT_824);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.util.EMap"));
    stringBuffer.append(TEXT_825);
    }
    stringBuffer.append(TEXT_826);
    stringBuffer.append(genClass.getClassName());
    genModel.emitSortedImports();
    stringBuffer.append(TEXT_827);
    return stringBuffer.toString();
  }
}
