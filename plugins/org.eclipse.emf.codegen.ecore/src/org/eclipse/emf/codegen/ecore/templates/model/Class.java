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
  protected final String TEXT_26 = NL + "\t/**" + NL + "\t * An array of objects representing the values of non-primitive features." + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */" + NL + "\tprotected Object[] ";
  protected final String TEXT_27 = " = null;" + NL;
  protected final String TEXT_28 = NL + "\t/**" + NL + "\t * A bit field representing the indices of non-primitive feature values." + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */" + NL + "\tprotected int ";
  protected final String TEXT_29 = " = 0;" + NL;
  protected final String TEXT_30 = NL + "\t/**" + NL + "\t * A set of bit flags representing the values of boolean attributes and whether unsettable features have been set." + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t * @ordered" + NL + "\t */" + NL + "\tprotected int ";
  protected final String TEXT_31 = " = 0;" + NL;
  protected final String TEXT_32 = NL + "\t/**" + NL + "\t * The cached value of the '{@link #";
  protected final String TEXT_33 = "() <em>";
  protected final String TEXT_34 = "</em>}' ";
  protected final String TEXT_35 = "." + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @see #";
  protected final String TEXT_36 = "()" + NL + "\t * @generated" + NL + "\t * @ordered" + NL + "\t */" + NL + "\tprotected ";
  protected final String TEXT_37 = " ";
  protected final String TEXT_38 = " = null;" + NL;
  protected final String TEXT_39 = NL + "\t/**" + NL + "\t * The default value of the '{@link #";
  protected final String TEXT_40 = "() <em>";
  protected final String TEXT_41 = "</em>}' ";
  protected final String TEXT_42 = "." + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @see #";
  protected final String TEXT_43 = "()" + NL + "\t * @generated" + NL + "\t * @ordered" + NL + "\t */" + NL + "\tprotected static final ";
  protected final String TEXT_44 = " ";
  protected final String TEXT_45 = "_EDEFAULT = ";
  protected final String TEXT_46 = ";";
  protected final String TEXT_47 = NL;
  protected final String TEXT_48 = NL + "\t/**" + NL + "\t * An additional set of bit flags representing the values of boolean attributes and whether unsettable features have been set." + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t * @ordered" + NL + "\t */" + NL + "\tprotected int ";
  protected final String TEXT_49 = " = 0;" + NL;
  protected final String TEXT_50 = NL + "\t/**" + NL + "\t * The flag representing the value of the '{@link #";
  protected final String TEXT_51 = "() <em>";
  protected final String TEXT_52 = "</em>}' ";
  protected final String TEXT_53 = "." + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @see #";
  protected final String TEXT_54 = "()" + NL + "\t * @generated" + NL + "\t * @ordered" + NL + "\t */" + NL + "\tprotected static final int ";
  protected final String TEXT_55 = "_EFLAG = 1 ";
  protected final String TEXT_56 = ";" + NL;
  protected final String TEXT_57 = NL + "\t/**" + NL + "\t * The cached value of the '{@link #";
  protected final String TEXT_58 = "() <em>";
  protected final String TEXT_59 = "</em>}' ";
  protected final String TEXT_60 = "." + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @see #";
  protected final String TEXT_61 = "()" + NL + "\t * @generated" + NL + "\t * @ordered" + NL + "\t */" + NL + "\tprotected ";
  protected final String TEXT_62 = " ";
  protected final String TEXT_63 = " = ";
  protected final String TEXT_64 = "_EDEFAULT;" + NL;
  protected final String TEXT_65 = NL + "\t/**" + NL + "\t * An additional set of bit flags representing the values of boolean attributes and whether unsettable features have been set." + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t * @ordered" + NL + "\t */" + NL + "\tprotected int ";
  protected final String TEXT_66 = " = 0;" + NL;
  protected final String TEXT_67 = NL + "\t/**" + NL + "\t * The flag representing whether the ";
  protected final String TEXT_68 = " ";
  protected final String TEXT_69 = " has been set." + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t * @ordered" + NL + "\t */" + NL + "\tprotected static final int ";
  protected final String TEXT_70 = "_ESETFLAG = 1 ";
  protected final String TEXT_71 = ";" + NL;
  protected final String TEXT_72 = NL + "\t/**" + NL + "\t * This is true if the ";
  protected final String TEXT_73 = " ";
  protected final String TEXT_74 = " has been set." + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t * @ordered" + NL + "\t */" + NL + "\tprotected boolean ";
  protected final String TEXT_75 = "ESet = false;" + NL;
  protected final String TEXT_76 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */" + NL + "\tprotected ";
  protected final String TEXT_77 = "()" + NL + "\t{" + NL + "\t\tsuper();";
  protected final String TEXT_78 = NL + "\t\t";
  protected final String TEXT_79 = " |= ";
  protected final String TEXT_80 = "_EFLAG;";
  protected final String TEXT_81 = NL + "\t}" + NL + "" + NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */" + NL + "\tprotected ";
  protected final String TEXT_82 = " eStaticClass()" + NL + "\t{" + NL + "\t\treturn ";
  protected final String TEXT_83 = ".eINSTANCE.get";
  protected final String TEXT_84 = "();" + NL + "\t}" + NL;
  protected final String TEXT_85 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */" + NL + "\tpublic ";
  protected final String TEXT_86 = " ";
  protected final String TEXT_87 = "()" + NL + "\t{";
  protected final String TEXT_88 = NL + "\t\treturn ";
  protected final String TEXT_89 = "(";
  protected final String TEXT_90 = "(";
  protected final String TEXT_91 = ")eGet(";
  protected final String TEXT_92 = ".eINSTANCE.get";
  protected final String TEXT_93 = "(), true)";
  protected final String TEXT_94 = ").";
  protected final String TEXT_95 = "()";
  protected final String TEXT_96 = ";";
  protected final String TEXT_97 = NL + "\t\t";
  protected final String TEXT_98 = " ";
  protected final String TEXT_99 = " = (";
  protected final String TEXT_100 = ")eVirtualGet(";
  protected final String TEXT_101 = ");";
  protected final String TEXT_102 = NL + "\t\tif (";
  protected final String TEXT_103 = " == null)" + NL + "\t\t{";
  protected final String TEXT_104 = NL + "\t\t\teVirtualSet(";
  protected final String TEXT_105 = ", ";
  protected final String TEXT_106 = " = new ";
  protected final String TEXT_107 = ");";
  protected final String TEXT_108 = NL + "\t\t\t";
  protected final String TEXT_109 = " = new ";
  protected final String TEXT_110 = ";";
  protected final String TEXT_111 = NL + "\t\t}" + NL + "\t\treturn ";
  protected final String TEXT_112 = ";";
  protected final String TEXT_113 = NL + "\t\tif (eContainerFeatureID != ";
  protected final String TEXT_114 = ") return null;" + NL + "\t\treturn (";
  protected final String TEXT_115 = ")eContainer();";
  protected final String TEXT_116 = NL + "\t\t";
  protected final String TEXT_117 = " ";
  protected final String TEXT_118 = " = (";
  protected final String TEXT_119 = ")eVirtualGet(";
  protected final String TEXT_120 = ", ";
  protected final String TEXT_121 = "_EDEFAULT";
  protected final String TEXT_122 = ");";
  protected final String TEXT_123 = NL + "\t\tif (";
  protected final String TEXT_124 = " != null && ";
  protected final String TEXT_125 = ".eIsProxy())" + NL + "\t\t{" + NL + "\t\t\t";
  protected final String TEXT_126 = " old";
  protected final String TEXT_127 = " = (";
  protected final String TEXT_128 = ")";
  protected final String TEXT_129 = ";" + NL + "\t\t\t";
  protected final String TEXT_130 = " = ";
  protected final String TEXT_131 = "eResolveProxy(old";
  protected final String TEXT_132 = ");" + NL + "\t\t\tif (";
  protected final String TEXT_133 = " != old";
  protected final String TEXT_134 = ")" + NL + "\t\t\t{";
  protected final String TEXT_135 = NL + "\t\t\t\t";
  protected final String TEXT_136 = " new";
  protected final String TEXT_137 = " = (";
  protected final String TEXT_138 = ")";
  protected final String TEXT_139 = ";";
  protected final String TEXT_140 = NL + "\t\t\t\t";
  protected final String TEXT_141 = " msgs = old";
  protected final String TEXT_142 = ".eInverseRemove(this, EOPPOSITE_FEATURE_BASE - ";
  protected final String TEXT_143 = ", null, null);";
  protected final String TEXT_144 = NL + "\t\t\t\t";
  protected final String TEXT_145 = " msgs =  old";
  protected final String TEXT_146 = ".eInverseRemove(this, ";
  protected final String TEXT_147 = ", ";
  protected final String TEXT_148 = ".class, null);";
  protected final String TEXT_149 = NL + "\t\t\t\tif (new";
  protected final String TEXT_150 = ".eInternalContainer() != null)" + NL + "\t\t\t\t{";
  protected final String TEXT_151 = NL + "\t\t\t\t\tmsgs = new";
  protected final String TEXT_152 = ".eInverseAdd(this, EOPPOSITE_FEATURE_BASE - ";
  protected final String TEXT_153 = ", null, msgs);";
  protected final String TEXT_154 = NL + "\t\t\t\t\tmsgs =  new";
  protected final String TEXT_155 = ".eInverseAdd(this, ";
  protected final String TEXT_156 = ", ";
  protected final String TEXT_157 = ".class, msgs);";
  protected final String TEXT_158 = NL + "\t\t\t\t}" + NL + "\t\t\t\tif (msgs != null) msgs.dispatch();";
  protected final String TEXT_159 = NL + "\t\t\t\teVirtualSet(";
  protected final String TEXT_160 = ", ";
  protected final String TEXT_161 = ");";
  protected final String TEXT_162 = NL + "\t\t\t\tif (eNotificationRequired())" + NL + "\t\t\t\t\teNotify(new ";
  protected final String TEXT_163 = "(this, ";
  protected final String TEXT_164 = ".RESOLVE, ";
  protected final String TEXT_165 = ", old";
  protected final String TEXT_166 = ", ";
  protected final String TEXT_167 = "));" + NL + "\t\t\t}" + NL + "\t\t}";
  protected final String TEXT_168 = NL + "\t\treturn (";
  protected final String TEXT_169 = " & ";
  protected final String TEXT_170 = "_EFLAG) != 0;";
  protected final String TEXT_171 = NL + "\t\treturn ";
  protected final String TEXT_172 = ";";
  protected final String TEXT_173 = NL + "\t\t";
  protected final String TEXT_174 = " ";
  protected final String TEXT_175 = " = basicGet";
  protected final String TEXT_176 = "();" + NL + "\t\treturn ";
  protected final String TEXT_177 = " != null && ";
  protected final String TEXT_178 = ".eIsProxy() ? ";
  protected final String TEXT_179 = "eResolveProxy((";
  protected final String TEXT_180 = ")";
  protected final String TEXT_181 = ") : ";
  protected final String TEXT_182 = ";";
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
  protected final String TEXT_208 = "(), true)";
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
  protected final String TEXT_228 = NL + "\t\tif (eContainerFeatureID != ";
  protected final String TEXT_229 = ") return null;" + NL + "\t\treturn (";
  protected final String TEXT_230 = ")eInternalContainer();";
  protected final String TEXT_231 = NL + "\t\treturn (";
  protected final String TEXT_232 = ")eVirtualGet(";
  protected final String TEXT_233 = ");";
  protected final String TEXT_234 = NL + "\t\treturn ";
  protected final String TEXT_235 = ";";
  protected final String TEXT_236 = NL + "\t\treturn (";
  protected final String TEXT_237 = ")((";
  protected final String TEXT_238 = ")get";
  protected final String TEXT_239 = "()).featureMap().get(";
  protected final String TEXT_240 = "(), false);";
  protected final String TEXT_241 = NL + "\t\treturn (";
  protected final String TEXT_242 = ")get";
  protected final String TEXT_243 = "().get(";
  protected final String TEXT_244 = "(), false);";
  protected final String TEXT_245 = NL + "\t\t// TODO: implement this method to return the '";
  protected final String TEXT_246 = "' ";
  protected final String TEXT_247 = NL + "\t\t// -> do not perform proxy resolution" + NL + "\t\t// Ensure that you remove @generated or mark it @generated NOT" + NL + "\t\tthrow new UnsupportedOperationException();";
  protected final String TEXT_248 = NL + "\t}" + NL;
  protected final String TEXT_249 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */" + NL + "\tpublic ";
  protected final String TEXT_250 = " basicSet";
  protected final String TEXT_251 = "(";
  protected final String TEXT_252 = " new";
  protected final String TEXT_253 = ", ";
  protected final String TEXT_254 = " msgs)" + NL + "\t{";
  protected final String TEXT_255 = NL + "\t\tObject old";
  protected final String TEXT_256 = " = eVirtualSet(";
  protected final String TEXT_257 = ", new";
  protected final String TEXT_258 = ");";
  protected final String TEXT_259 = NL + "\t\t";
  protected final String TEXT_260 = " old";
  protected final String TEXT_261 = " = ";
  protected final String TEXT_262 = ";" + NL + "\t\t";
  protected final String TEXT_263 = " = new";
  protected final String TEXT_264 = ";";
  protected final String TEXT_265 = NL + "\t\tboolean isSetChange = old";
  protected final String TEXT_266 = " == EVIRTUAL_NO_VALUE;";
  protected final String TEXT_267 = NL + "\t\tboolean old";
  protected final String TEXT_268 = "ESet = (";
  protected final String TEXT_269 = " & ";
  protected final String TEXT_270 = "_ESETFLAG) != 0;" + NL + "\t\t";
  protected final String TEXT_271 = " |= ";
  protected final String TEXT_272 = "_ESETFLAG;";
  protected final String TEXT_273 = NL + "\t\tboolean old";
  protected final String TEXT_274 = "ESet = ";
  protected final String TEXT_275 = "ESet;" + NL + "\t\t";
  protected final String TEXT_276 = "ESet = true;";
  protected final String TEXT_277 = NL + "\t\tif (eNotificationRequired())" + NL + "\t\t{";
  protected final String TEXT_278 = NL + "\t\t\t";
  protected final String TEXT_279 = " notification = new ";
  protected final String TEXT_280 = "(this, ";
  protected final String TEXT_281 = ".SET, ";
  protected final String TEXT_282 = ", ";
  protected final String TEXT_283 = "isSetChange ? null : old";
  protected final String TEXT_284 = "old";
  protected final String TEXT_285 = ", new";
  protected final String TEXT_286 = ", ";
  protected final String TEXT_287 = "isSetChange";
  protected final String TEXT_288 = "!old";
  protected final String TEXT_289 = "ESet";
  protected final String TEXT_290 = ");";
  protected final String TEXT_291 = NL + "\t\t\t";
  protected final String TEXT_292 = " notification = new ";
  protected final String TEXT_293 = "(this, ";
  protected final String TEXT_294 = ".SET, ";
  protected final String TEXT_295 = ", ";
  protected final String TEXT_296 = "old";
  protected final String TEXT_297 = " == EVIRTUAL_NO_VALUE ? null : old";
  protected final String TEXT_298 = "old";
  protected final String TEXT_299 = ", new";
  protected final String TEXT_300 = ");";
  protected final String TEXT_301 = NL + "\t\t\tif (msgs == null) msgs = notification; else msgs.add(notification);" + NL + "\t\t}";
  protected final String TEXT_302 = NL + "\t\treturn msgs;";
  protected final String TEXT_303 = NL + "\t\treturn ((";
  protected final String TEXT_304 = ".Internal)((";
  protected final String TEXT_305 = ")get";
  protected final String TEXT_306 = "()).featureMap()).basicAdd(";
  protected final String TEXT_307 = "(), new";
  protected final String TEXT_308 = ", msgs);";
  protected final String TEXT_309 = NL + "\t\treturn ((";
  protected final String TEXT_310 = ".Internal)get";
  protected final String TEXT_311 = "()).basicAdd(";
  protected final String TEXT_312 = "(), new";
  protected final String TEXT_313 = ", msgs);";
  protected final String TEXT_314 = NL + "\t\t// TODO: implement this method to set the contained '";
  protected final String TEXT_315 = "' ";
  protected final String TEXT_316 = NL + "\t\t// -> this method is automatically invoked to keep the containment relationship in synch" + NL + "\t\t// -> do not modify other features" + NL + "\t\t// -> return msgs, after adding any generated Notification to it (if it is null, a NotificationChain object must be created first)" + NL + "\t\t// Ensure that you remove @generated or mark it @generated NOT" + NL + "\t\tthrow new UnsupportedOperationException();";
  protected final String TEXT_317 = NL + "\t}" + NL;
  protected final String TEXT_318 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */" + NL + "\tpublic void set";
  protected final String TEXT_319 = "(";
  protected final String TEXT_320 = " new";
  protected final String TEXT_321 = ")" + NL + "\t{";
  protected final String TEXT_322 = NL + "\t\teSet(";
  protected final String TEXT_323 = ".eINSTANCE.get";
  protected final String TEXT_324 = "(), ";
  protected final String TEXT_325 = "new ";
  protected final String TEXT_326 = "(";
  protected final String TEXT_327 = "new";
  protected final String TEXT_328 = ")";
  protected final String TEXT_329 = ");";
  protected final String TEXT_330 = NL + "\t\tif (new";
  protected final String TEXT_331 = " != eInternalContainer() || (eContainerFeatureID != ";
  protected final String TEXT_332 = " && new";
  protected final String TEXT_333 = " != null))" + NL + "\t\t{" + NL + "\t\t\tif (";
  protected final String TEXT_334 = ".isAncestor(this, ";
  protected final String TEXT_335 = "new";
  protected final String TEXT_336 = "))" + NL + "\t\t\t\tthrow new ";
  protected final String TEXT_337 = "(\"Recursive containment not allowed for \" + toString());";
  protected final String TEXT_338 = NL + "\t\t\t";
  protected final String TEXT_339 = " msgs = null;" + NL + "\t\t\tif (eInternalContainer() != null)" + NL + "\t\t\t\tmsgs = eBasicRemoveFromContainer(msgs);" + NL + "\t\t\tif (new";
  protected final String TEXT_340 = " != null)" + NL + "\t\t\t\tmsgs = ((";
  protected final String TEXT_341 = ")new";
  protected final String TEXT_342 = ").eInverseAdd(this, ";
  protected final String TEXT_343 = ", ";
  protected final String TEXT_344 = ".class, msgs);" + NL + "\t\t\tmsgs = eBasicSetContainer((";
  protected final String TEXT_345 = ")new";
  protected final String TEXT_346 = ", ";
  protected final String TEXT_347 = ", msgs);" + NL + "\t\t\tif (msgs != null) msgs.dispatch();" + NL + "\t\t}" + NL + "\t\telse if (eNotificationRequired())" + NL + "\t\t\teNotify(new ";
  protected final String TEXT_348 = "(this, ";
  protected final String TEXT_349 = ".SET, ";
  protected final String TEXT_350 = ", new";
  protected final String TEXT_351 = ", new";
  protected final String TEXT_352 = "));";
  protected final String TEXT_353 = NL + "\t\t";
  protected final String TEXT_354 = " ";
  protected final String TEXT_355 = " = (";
  protected final String TEXT_356 = ")eVirtualGet(";
  protected final String TEXT_357 = ");";
  protected final String TEXT_358 = NL + "\t\tif (new";
  protected final String TEXT_359 = " != ";
  protected final String TEXT_360 = ")" + NL + "\t\t{" + NL + "\t\t\t";
  protected final String TEXT_361 = " msgs = null;" + NL + "\t\t\tif (";
  protected final String TEXT_362 = " != null)";
  protected final String TEXT_363 = NL + "\t\t\t\tmsgs = ((";
  protected final String TEXT_364 = ")";
  protected final String TEXT_365 = ").eInverseRemove(this, EOPPOSITE_FEATURE_BASE - ";
  protected final String TEXT_366 = ", null, msgs);" + NL + "\t\t\tif (new";
  protected final String TEXT_367 = " != null)" + NL + "\t\t\t\tmsgs = ((";
  protected final String TEXT_368 = ")new";
  protected final String TEXT_369 = ").eInverseAdd(this, EOPPOSITE_FEATURE_BASE - ";
  protected final String TEXT_370 = ", null, msgs);";
  protected final String TEXT_371 = NL + "\t\t\t\tmsgs = ((";
  protected final String TEXT_372 = ")";
  protected final String TEXT_373 = ").eInverseRemove(this, ";
  protected final String TEXT_374 = ", ";
  protected final String TEXT_375 = ".class, msgs);" + NL + "\t\t\tif (new";
  protected final String TEXT_376 = " != null)" + NL + "\t\t\t\tmsgs = ((";
  protected final String TEXT_377 = ")new";
  protected final String TEXT_378 = ").eInverseAdd(this, ";
  protected final String TEXT_379 = ", ";
  protected final String TEXT_380 = ".class, msgs);";
  protected final String TEXT_381 = NL + "\t\t\tmsgs = basicSet";
  protected final String TEXT_382 = "(";
  protected final String TEXT_383 = "new";
  protected final String TEXT_384 = ", msgs);" + NL + "\t\t\tif (msgs != null) msgs.dispatch();" + NL + "\t\t}";
  protected final String TEXT_385 = NL + "\t\telse" + NL + "    \t{";
  protected final String TEXT_386 = NL + "\t\t\tboolean old";
  protected final String TEXT_387 = "ESet = eVirtualIsSet(";
  protected final String TEXT_388 = ");";
  protected final String TEXT_389 = NL + "\t\t\tboolean old";
  protected final String TEXT_390 = "ESet = (";
  protected final String TEXT_391 = " & ";
  protected final String TEXT_392 = "_ESETFLAG) != 0;" + NL + "\t\t\t";
  protected final String TEXT_393 = " |= ";
  protected final String TEXT_394 = "_ESETFLAG;";
  protected final String TEXT_395 = NL + "\t\t\tboolean old";
  protected final String TEXT_396 = "ESet = ";
  protected final String TEXT_397 = "ESet;" + NL + "\t\t\t";
  protected final String TEXT_398 = "ESet = true;";
  protected final String TEXT_399 = NL + "\t\t\tif (eNotificationRequired())" + NL + "\t\t\t\teNotify(new ";
  protected final String TEXT_400 = "(this, ";
  protected final String TEXT_401 = ".SET, ";
  protected final String TEXT_402 = ", new";
  protected final String TEXT_403 = ", new";
  protected final String TEXT_404 = ", !old";
  protected final String TEXT_405 = "ESet));" + NL + "    \t}";
  protected final String TEXT_406 = NL + "\t\telse if (eNotificationRequired())" + NL + "\t\t\teNotify(new ";
  protected final String TEXT_407 = "(this, ";
  protected final String TEXT_408 = ".SET, ";
  protected final String TEXT_409 = ", new";
  protected final String TEXT_410 = ", new";
  protected final String TEXT_411 = "));";
  protected final String TEXT_412 = NL + "\t\t";
  protected final String TEXT_413 = " old";
  protected final String TEXT_414 = " = (";
  protected final String TEXT_415 = " & ";
  protected final String TEXT_416 = "_EFLAG) != 0;" + NL + "\t\tif (new";
  protected final String TEXT_417 = ") ";
  protected final String TEXT_418 = " |= ";
  protected final String TEXT_419 = "_EFLAG; else ";
  protected final String TEXT_420 = " &= ~";
  protected final String TEXT_421 = "_EFLAG;";
  protected final String TEXT_422 = NL + "\t\t";
  protected final String TEXT_423 = " old";
  protected final String TEXT_424 = " = ";
  protected final String TEXT_425 = ";";
  protected final String TEXT_426 = NL + "\t\t";
  protected final String TEXT_427 = " ";
  protected final String TEXT_428 = " = new";
  protected final String TEXT_429 = " == null ? ";
  protected final String TEXT_430 = "_EDEFAULT : new";
  protected final String TEXT_431 = ";";
  protected final String TEXT_432 = NL + "\t\t";
  protected final String TEXT_433 = " = new";
  protected final String TEXT_434 = " == null ? ";
  protected final String TEXT_435 = "_EDEFAULT : new";
  protected final String TEXT_436 = ";";
  protected final String TEXT_437 = NL + "\t\t";
  protected final String TEXT_438 = " ";
  protected final String TEXT_439 = " = ";
  protected final String TEXT_440 = "new";
  protected final String TEXT_441 = ";";
  protected final String TEXT_442 = NL + "\t\t";
  protected final String TEXT_443 = " = ";
  protected final String TEXT_444 = "new";
  protected final String TEXT_445 = ";";
  protected final String TEXT_446 = NL + "\t\tObject old";
  protected final String TEXT_447 = " = eVirtualSet(";
  protected final String TEXT_448 = ", ";
  protected final String TEXT_449 = ");";
  protected final String TEXT_450 = NL + "\t\tboolean isSetChange = old";
  protected final String TEXT_451 = " == EVIRTUAL_NO_VALUE;";
  protected final String TEXT_452 = NL + "\t\tboolean old";
  protected final String TEXT_453 = "ESet = (";
  protected final String TEXT_454 = " & ";
  protected final String TEXT_455 = "_ESETFLAG) != 0;" + NL + "\t\t";
  protected final String TEXT_456 = " |= ";
  protected final String TEXT_457 = "_ESETFLAG;";
  protected final String TEXT_458 = NL + "\t\tboolean old";
  protected final String TEXT_459 = "ESet = ";
  protected final String TEXT_460 = "ESet;" + NL + "\t\t";
  protected final String TEXT_461 = "ESet = true;";
  protected final String TEXT_462 = NL + "\t\tif (eNotificationRequired())" + NL + "\t\t\teNotify(new ";
  protected final String TEXT_463 = "(this, ";
  protected final String TEXT_464 = ".SET, ";
  protected final String TEXT_465 = ", ";
  protected final String TEXT_466 = "isSetChange ? ";
  protected final String TEXT_467 = "null";
  protected final String TEXT_468 = "_EDEFAULT";
  protected final String TEXT_469 = " : old";
  protected final String TEXT_470 = "old";
  protected final String TEXT_471 = ", ";
  protected final String TEXT_472 = "new";
  protected final String TEXT_473 = ", ";
  protected final String TEXT_474 = "isSetChange";
  protected final String TEXT_475 = "!old";
  protected final String TEXT_476 = "ESet";
  protected final String TEXT_477 = "));";
  protected final String TEXT_478 = NL + "\t\tif (eNotificationRequired())" + NL + "\t\t\teNotify(new ";
  protected final String TEXT_479 = "(this, ";
  protected final String TEXT_480 = ".SET, ";
  protected final String TEXT_481 = ", ";
  protected final String TEXT_482 = "old";
  protected final String TEXT_483 = " == EVIRTUAL_NO_VALUE ? ";
  protected final String TEXT_484 = "null";
  protected final String TEXT_485 = "_EDEFAULT";
  protected final String TEXT_486 = " : old";
  protected final String TEXT_487 = "old";
  protected final String TEXT_488 = ", ";
  protected final String TEXT_489 = "new";
  protected final String TEXT_490 = "));";
  protected final String TEXT_491 = NL + "\t\t((";
  protected final String TEXT_492 = ".Internal)((";
  protected final String TEXT_493 = ")get";
  protected final String TEXT_494 = "()).featureMap()).set(";
  protected final String TEXT_495 = "(), ";
  protected final String TEXT_496 = "new ";
  protected final String TEXT_497 = "(";
  protected final String TEXT_498 = "new";
  protected final String TEXT_499 = ")";
  protected final String TEXT_500 = ");";
  protected final String TEXT_501 = NL + "\t\t((";
  protected final String TEXT_502 = ".Internal)get";
  protected final String TEXT_503 = "()).set(";
  protected final String TEXT_504 = "(), ";
  protected final String TEXT_505 = "new ";
  protected final String TEXT_506 = "(";
  protected final String TEXT_507 = "new";
  protected final String TEXT_508 = ")";
  protected final String TEXT_509 = ");";
  protected final String TEXT_510 = NL + "\t\t// TODO: implement this method to set the '";
  protected final String TEXT_511 = "' ";
  protected final String TEXT_512 = NL + "\t\t// Ensure that you remove @generated or mark it @generated NOT" + NL + "\t\tthrow new UnsupportedOperationException();";
  protected final String TEXT_513 = NL + "\t}" + NL;
  protected final String TEXT_514 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */" + NL + "\tpublic ";
  protected final String TEXT_515 = " basicUnset";
  protected final String TEXT_516 = "(";
  protected final String TEXT_517 = " msgs)" + NL + "\t{";
  protected final String TEXT_518 = NL + "\t\tObject old";
  protected final String TEXT_519 = " = eVirtualUnset(";
  protected final String TEXT_520 = ");";
  protected final String TEXT_521 = NL + "\t\t";
  protected final String TEXT_522 = " old";
  protected final String TEXT_523 = " = ";
  protected final String TEXT_524 = ";" + NL + "\t\t";
  protected final String TEXT_525 = " = null;";
  protected final String TEXT_526 = NL + "\t\tboolean isSetChange = old";
  protected final String TEXT_527 = " != EVIRTUAL_NO_VALUE;";
  protected final String TEXT_528 = NL + "\t\tboolean old";
  protected final String TEXT_529 = "ESet = (";
  protected final String TEXT_530 = " & ";
  protected final String TEXT_531 = "_ESETFLAG) != 0;" + NL + "\t\t";
  protected final String TEXT_532 = " &= ~";
  protected final String TEXT_533 = "_ESETFLAG;";
  protected final String TEXT_534 = NL + "\t\tboolean old";
  protected final String TEXT_535 = "ESet = ";
  protected final String TEXT_536 = "ESet;" + NL + "\t\t";
  protected final String TEXT_537 = "ESet = false;";
  protected final String TEXT_538 = NL + "\t\tif (eNotificationRequired())" + NL + "\t\t{" + NL + "\t\t\t";
  protected final String TEXT_539 = " notification = new ";
  protected final String TEXT_540 = "(this, ";
  protected final String TEXT_541 = ".UNSET, ";
  protected final String TEXT_542 = ", ";
  protected final String TEXT_543 = "isSetChange ? old";
  protected final String TEXT_544 = " : null";
  protected final String TEXT_545 = "old";
  protected final String TEXT_546 = ", null, ";
  protected final String TEXT_547 = "isSetChange";
  protected final String TEXT_548 = "old";
  protected final String TEXT_549 = "ESet";
  protected final String TEXT_550 = ");" + NL + "\t\t\tif (msgs == null) msgs = notification; else msgs.add(notification);" + NL + "\t\t}" + NL + "\t\treturn msgs;";
  protected final String TEXT_551 = NL + "\t\t// TODO: implement this method to unset the contained '";
  protected final String TEXT_552 = "' ";
  protected final String TEXT_553 = NL + "\t\t// -> this method is automatically invoked to keep the containment relationship in synch" + NL + "\t\t// -> do not modify other features" + NL + "\t\t// -> return msgs, after adding any generated Notification to it (if it is null, a NotificationChain object must be created first)" + NL + "\t\t// Ensure that you remove @generated or mark it @generated NOT" + NL + "\t\tthrow new UnsupportedOperationException();";
  protected final String TEXT_554 = NL + "\t}" + NL;
  protected final String TEXT_555 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */" + NL + "\tpublic void unset";
  protected final String TEXT_556 = "()" + NL + "\t{";
  protected final String TEXT_557 = NL + "\t\teUnset(";
  protected final String TEXT_558 = ".eINSTANCE.get";
  protected final String TEXT_559 = "());";
  protected final String TEXT_560 = NL + "\t\t((";
  protected final String TEXT_561 = ".Unsettable)get";
  protected final String TEXT_562 = "()).unset();";
  protected final String TEXT_563 = NL + "\t\t";
  protected final String TEXT_564 = " ";
  protected final String TEXT_565 = " = (";
  protected final String TEXT_566 = ")eVirtualGet(";
  protected final String TEXT_567 = ");";
  protected final String TEXT_568 = NL + "\t\tif (";
  protected final String TEXT_569 = " != null)" + NL + "\t\t{" + NL + "\t\t\t";
  protected final String TEXT_570 = " msgs = null;";
  protected final String TEXT_571 = NL + "\t\t\tmsgs = ((";
  protected final String TEXT_572 = ")";
  protected final String TEXT_573 = ").eInverseRemove(this, EOPPOSITE_FEATURE_BASE - ";
  protected final String TEXT_574 = ", null, msgs);";
  protected final String TEXT_575 = NL + "\t\t\tmsgs = ((";
  protected final String TEXT_576 = ")";
  protected final String TEXT_577 = ").eInverseRemove(this, ";
  protected final String TEXT_578 = ", ";
  protected final String TEXT_579 = ".class, msgs);";
  protected final String TEXT_580 = NL + "\t\t\tmsgs = basicUnset";
  protected final String TEXT_581 = "(msgs);" + NL + "\t\t\tif (msgs != null) msgs.dispatch();" + NL + "\t\t}" + NL + "\t\telse" + NL + "    \t{";
  protected final String TEXT_582 = NL + "\t\t\tboolean old";
  protected final String TEXT_583 = "ESet = eVirtualIsSet(";
  protected final String TEXT_584 = ");";
  protected final String TEXT_585 = NL + "\t\t\tboolean old";
  protected final String TEXT_586 = "ESet = (";
  protected final String TEXT_587 = " & ";
  protected final String TEXT_588 = "_ESETFLAG) != 0;" + NL + "\t\t\t";
  protected final String TEXT_589 = " &= ~";
  protected final String TEXT_590 = "_ESETFLAG;";
  protected final String TEXT_591 = NL + "\t\t\tboolean old";
  protected final String TEXT_592 = "ESet = ";
  protected final String TEXT_593 = "ESet;" + NL + "\t\t\t";
  protected final String TEXT_594 = "ESet = false;";
  protected final String TEXT_595 = NL + "\t\t\tif (eNotificationRequired())" + NL + "\t\t\t\teNotify(new ";
  protected final String TEXT_596 = "(this, ";
  protected final String TEXT_597 = ".UNSET, ";
  protected final String TEXT_598 = ", null, null, old";
  protected final String TEXT_599 = "ESet));" + NL + "    \t}";
  protected final String TEXT_600 = NL + "\t\t";
  protected final String TEXT_601 = " old";
  protected final String TEXT_602 = " = (";
  protected final String TEXT_603 = " & ";
  protected final String TEXT_604 = "_EFLAG) != 0;";
  protected final String TEXT_605 = NL + "\t\tObject old";
  protected final String TEXT_606 = " = eVirtualUnset(";
  protected final String TEXT_607 = ");";
  protected final String TEXT_608 = NL + "\t\t";
  protected final String TEXT_609 = " old";
  protected final String TEXT_610 = " = ";
  protected final String TEXT_611 = ";";
  protected final String TEXT_612 = NL + "\t\tboolean isSetChange = old";
  protected final String TEXT_613 = " != EVIRTUAL_NO_VALUE;";
  protected final String TEXT_614 = NL + "\t\tboolean old";
  protected final String TEXT_615 = "ESet = (";
  protected final String TEXT_616 = " & ";
  protected final String TEXT_617 = "_ESETFLAG) != 0;";
  protected final String TEXT_618 = NL + "\t\tboolean old";
  protected final String TEXT_619 = "ESet = ";
  protected final String TEXT_620 = "ESet;";
  protected final String TEXT_621 = NL + "\t\t";
  protected final String TEXT_622 = " = null;";
  protected final String TEXT_623 = NL + "\t\t";
  protected final String TEXT_624 = " &= ~";
  protected final String TEXT_625 = "_ESETFLAG;";
  protected final String TEXT_626 = NL + "\t\t";
  protected final String TEXT_627 = "ESet = false;";
  protected final String TEXT_628 = NL + "\t\tif (eNotificationRequired())" + NL + "\t\t\teNotify(new ";
  protected final String TEXT_629 = "(this, ";
  protected final String TEXT_630 = ".UNSET, ";
  protected final String TEXT_631 = ", ";
  protected final String TEXT_632 = "isSetChange ? old";
  protected final String TEXT_633 = " : null";
  protected final String TEXT_634 = "old";
  protected final String TEXT_635 = ", null, ";
  protected final String TEXT_636 = "isSetChange";
  protected final String TEXT_637 = "old";
  protected final String TEXT_638 = "ESet";
  protected final String TEXT_639 = "));";
  protected final String TEXT_640 = NL + "\t\tif (";
  protected final String TEXT_641 = "_EDEFAULT) ";
  protected final String TEXT_642 = " |= ";
  protected final String TEXT_643 = "_EFLAG; else ";
  protected final String TEXT_644 = " &= ~";
  protected final String TEXT_645 = "_EFLAG;";
  protected final String TEXT_646 = NL + "\t\t";
  protected final String TEXT_647 = " = ";
  protected final String TEXT_648 = "_EDEFAULT;";
  protected final String TEXT_649 = NL + "\t\t";
  protected final String TEXT_650 = " &= ~";
  protected final String TEXT_651 = "_ESETFLAG;";
  protected final String TEXT_652 = NL + "\t\t";
  protected final String TEXT_653 = "ESet = false;";
  protected final String TEXT_654 = NL + "\t\tif (eNotificationRequired())" + NL + "\t\t\teNotify(new ";
  protected final String TEXT_655 = "(this, ";
  protected final String TEXT_656 = ".UNSET, ";
  protected final String TEXT_657 = ", ";
  protected final String TEXT_658 = "isSetChange ? old";
  protected final String TEXT_659 = " : ";
  protected final String TEXT_660 = "_EDEFAULT";
  protected final String TEXT_661 = "old";
  protected final String TEXT_662 = ", ";
  protected final String TEXT_663 = "_EDEFAULT, ";
  protected final String TEXT_664 = "isSetChange";
  protected final String TEXT_665 = "old";
  protected final String TEXT_666 = "ESet";
  protected final String TEXT_667 = "));";
  protected final String TEXT_668 = NL + "\t\t((";
  protected final String TEXT_669 = ".Internal)((";
  protected final String TEXT_670 = ")get";
  protected final String TEXT_671 = "()).featureMap()).clear(";
  protected final String TEXT_672 = "());";
  protected final String TEXT_673 = NL + "\t\t((";
  protected final String TEXT_674 = ".Internal)get";
  protected final String TEXT_675 = "()).clear(";
  protected final String TEXT_676 = "());";
  protected final String TEXT_677 = NL + "\t\t// TODO: implement this method to unset the '";
  protected final String TEXT_678 = "' ";
  protected final String TEXT_679 = NL + "\t\t// Ensure that you remove @generated or mark it @generated NOT" + NL + "\t\tthrow new UnsupportedOperationException();";
  protected final String TEXT_680 = NL + "\t}" + NL;
  protected final String TEXT_681 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */" + NL + "\tpublic boolean isSet";
  protected final String TEXT_682 = "()" + NL + "\t{";
  protected final String TEXT_683 = NL + "\t\treturn eIsSet(";
  protected final String TEXT_684 = ".eINSTANCE.get";
  protected final String TEXT_685 = "());";
  protected final String TEXT_686 = NL + "\t\t";
  protected final String TEXT_687 = " ";
  protected final String TEXT_688 = " = (";
  protected final String TEXT_689 = ")eVirtualGet(";
  protected final String TEXT_690 = ");";
  protected final String TEXT_691 = NL + "\t\treturn ";
  protected final String TEXT_692 = " != null && ((";
  protected final String TEXT_693 = ".Unsettable)";
  protected final String TEXT_694 = ").isSet();";
  protected final String TEXT_695 = NL + "\t\treturn eVirtualIsSet(";
  protected final String TEXT_696 = ");";
  protected final String TEXT_697 = NL + "\t\treturn (";
  protected final String TEXT_698 = " & ";
  protected final String TEXT_699 = "_ESETFLAG) != 0;";
  protected final String TEXT_700 = NL + "\t\treturn ";
  protected final String TEXT_701 = "ESet;";
  protected final String TEXT_702 = NL + "\t\treturn !((";
  protected final String TEXT_703 = ".Internal)((";
  protected final String TEXT_704 = ")get";
  protected final String TEXT_705 = "()).featureMap()).isEmpty(";
  protected final String TEXT_706 = "());";
  protected final String TEXT_707 = NL + "\t\treturn !((";
  protected final String TEXT_708 = ".Internal)get";
  protected final String TEXT_709 = "()).isEmpty(";
  protected final String TEXT_710 = "());";
  protected final String TEXT_711 = NL + "\t\t// TODO: implement this method to return whether the '";
  protected final String TEXT_712 = "' ";
  protected final String TEXT_713 = " is set" + NL + "\t\t// Ensure that you remove @generated or mark it @generated NOT" + NL + "\t\tthrow new UnsupportedOperationException();";
  protected final String TEXT_714 = NL + "\t}" + NL;
  protected final String TEXT_715 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */" + NL + "\tpublic ";
  protected final String TEXT_716 = " ";
  protected final String TEXT_717 = "(";
  protected final String TEXT_718 = ")";
  protected final String TEXT_719 = NL + "\t{";
  protected final String TEXT_720 = NL + "\t\t";
  protected final String TEXT_721 = NL + "\t\t// TODO: implement this method" + NL + "\t\t// -> specify the condition that violates the invariant" + NL + "\t\t// -> verify the details of the diagnostic, including severity and message" + NL + "\t\t// Ensure that you remove @generated or mark it @generated NOT" + NL + "\t\tif (false)" + NL + "\t\t{" + NL + "\t\t\tif (";
  protected final String TEXT_722 = " != null)" + NL + "\t\t\t{" + NL + "\t\t\t\t";
  protected final String TEXT_723 = ".add" + NL + "\t\t\t\t\t(new ";
  protected final String TEXT_724 = NL + "\t\t\t\t\t\t(";
  protected final String TEXT_725 = ".ERROR," + NL + "\t\t\t\t\t\t ";
  protected final String TEXT_726 = ".DIAGNOSTIC_SOURCE," + NL + "\t\t\t\t\t\t ";
  protected final String TEXT_727 = ".";
  protected final String TEXT_728 = "," + NL + "\t\t\t\t\t\t ";
  protected final String TEXT_729 = ".INSTANCE.getString(\"_UI_GenericInvariant_diagnostic\", new Object[] { \"";
  protected final String TEXT_730 = "\", ";
  protected final String TEXT_731 = ".getObjectLabel(this, ";
  protected final String TEXT_732 = ") }),";
  protected final String TEXT_733 = NL + "\t\t\t\t\t\t new Object [] { this }));" + NL + "\t\t\t}" + NL + "\t\t\treturn false;" + NL + "\t\t}" + NL + "\t\treturn true;";
  protected final String TEXT_734 = NL + "\t\t// TODO: implement this method" + NL + "\t\t// Ensure that you remove @generated or mark it @generated NOT" + NL + "\t\tthrow new UnsupportedOperationException();";
  protected final String TEXT_735 = NL + "\t}" + NL;
  protected final String TEXT_736 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */" + NL + "\tpublic ";
  protected final String TEXT_737 = " eInverseAdd(";
  protected final String TEXT_738 = " otherEnd, int featureID, Class baseClass, ";
  protected final String TEXT_739 = " msgs)" + NL + "\t{" + NL + "\t\tif (featureID >= 0)" + NL + "\t\t{" + NL + "\t\t\tswitch (eDerivedStructuralFeatureID(featureID, baseClass))" + NL + "\t\t\t{";
  protected final String TEXT_740 = NL + "\t\t\t\tcase ";
  protected final String TEXT_741 = ":";
  protected final String TEXT_742 = NL + "\t\t\t\t\treturn ((";
  protected final String TEXT_743 = ")((";
  protected final String TEXT_744 = ")";
  protected final String TEXT_745 = "()).eMap()).basicAdd(otherEnd, msgs);";
  protected final String TEXT_746 = NL + "\t\t\t\t\treturn ((";
  protected final String TEXT_747 = ")";
  protected final String TEXT_748 = "()).basicAdd(otherEnd, msgs);";
  protected final String TEXT_749 = NL + "\t\t\t\t\tif (eInternalContainer() != null)" + NL + "\t\t\t\t\t\tmsgs = eBasicRemoveFromContainer(msgs);" + NL + "\t\t\t\t\treturn eBasicSetContainer(otherEnd, ";
  protected final String TEXT_750 = ", msgs);";
  protected final String TEXT_751 = NL + "\t\t\t\t\t";
  protected final String TEXT_752 = " ";
  protected final String TEXT_753 = " = (";
  protected final String TEXT_754 = ")eVirtualGet(";
  protected final String TEXT_755 = ");";
  protected final String TEXT_756 = NL + "\t\t\t\t\tif (";
  protected final String TEXT_757 = " != null)";
  protected final String TEXT_758 = NL + "\t\t\t\t\t\tmsgs = ((";
  protected final String TEXT_759 = ")";
  protected final String TEXT_760 = ").eInverseRemove(this, EOPPOSITE_FEATURE_BASE - ";
  protected final String TEXT_761 = ", null, msgs);";
  protected final String TEXT_762 = NL + "\t\t\t\t\t\tmsgs = ((";
  protected final String TEXT_763 = ")";
  protected final String TEXT_764 = ").eInverseRemove(this, ";
  protected final String TEXT_765 = ", ";
  protected final String TEXT_766 = ".class, msgs);";
  protected final String TEXT_767 = NL + "\t\t\t\t\treturn basicSet";
  protected final String TEXT_768 = "((";
  protected final String TEXT_769 = ")otherEnd, msgs);";
  protected final String TEXT_770 = NL + "\t\t\t\tdefault:" + NL + "\t\t\t\t\treturn eDynamicInverseAdd(otherEnd, featureID, baseClass, msgs);" + NL + "\t\t\t}" + NL + "\t\t}" + NL + "\t\tif (eInternalContainer() != null)" + NL + "\t\t\tmsgs = eBasicRemoveFromContainer(msgs);" + NL + "\t\treturn eBasicSetContainer(otherEnd, featureID, msgs);" + NL + "\t}" + NL;
  protected final String TEXT_771 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */" + NL + "\tpublic ";
  protected final String TEXT_772 = " eInverseRemove(";
  protected final String TEXT_773 = " otherEnd, int featureID, Class baseClass, ";
  protected final String TEXT_774 = " msgs)" + NL + "\t{" + NL + "\t\tif (featureID >= 0)" + NL + "\t\t{" + NL + "\t\t\tswitch (eDerivedStructuralFeatureID(featureID, baseClass))" + NL + "\t\t\t{";
  protected final String TEXT_775 = NL + "\t\t\t\tcase ";
  protected final String TEXT_776 = ":";
  protected final String TEXT_777 = NL + "\t\t\t\t\treturn ((";
  protected final String TEXT_778 = ")((";
  protected final String TEXT_779 = ")";
  protected final String TEXT_780 = "()).eMap()).basicRemove(otherEnd, msgs);";
  protected final String TEXT_781 = NL + "\t\t\t\treturn ((";
  protected final String TEXT_782 = ")((";
  protected final String TEXT_783 = ")";
  protected final String TEXT_784 = "()).featureMap()).basicRemove(otherEnd, msgs);";
  protected final String TEXT_785 = NL + "\t\t\t\t\treturn ((";
  protected final String TEXT_786 = ")";
  protected final String TEXT_787 = "()).basicRemove(otherEnd, msgs);";
  protected final String TEXT_788 = NL + "\t\t\t\t\treturn eBasicSetContainer(null, ";
  protected final String TEXT_789 = ", msgs);";
  protected final String TEXT_790 = NL + "\t\t\t\t\treturn basicUnset";
  protected final String TEXT_791 = "(msgs);";
  protected final String TEXT_792 = NL + "\t\t\t\t\treturn basicSet";
  protected final String TEXT_793 = "(null, msgs);";
  protected final String TEXT_794 = NL + "\t\t\t\tdefault:" + NL + "\t\t\t\t\treturn eDynamicInverseRemove(otherEnd, featureID, baseClass, msgs);" + NL + "\t\t\t}" + NL + "\t\t}" + NL + "\t\treturn eBasicSetContainer(null, featureID, msgs);" + NL + "\t}" + NL;
  protected final String TEXT_795 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */" + NL + "\tpublic ";
  protected final String TEXT_796 = " eBasicRemoveFromContainer(";
  protected final String TEXT_797 = " msgs)" + NL + "\t{" + NL + "\t\tif (eContainerFeatureID >= 0)" + NL + "\t\t{" + NL + "\t\t\tswitch (eContainerFeatureID)" + NL + "\t\t\t{";
  protected final String TEXT_798 = NL + "\t\t\t\tcase ";
  protected final String TEXT_799 = ":" + NL + "\t\t\t\t\treturn eInternalContainer().eInverseRemove(this, ";
  protected final String TEXT_800 = ", ";
  protected final String TEXT_801 = ".class, msgs);";
  protected final String TEXT_802 = NL + "\t\t\t\tdefault:" + NL + "\t\t\t\t\treturn eDynamicBasicRemoveFromContainer(msgs);" + NL + "\t\t\t}" + NL + "\t\t}" + NL + "\t\treturn eInternalContainer().eInverseRemove(this, EOPPOSITE_FEATURE_BASE - eContainerFeatureID, null, msgs);" + NL + "\t}" + NL;
  protected final String TEXT_803 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */" + NL + "\tpublic Object eGet(";
  protected final String TEXT_804 = " eFeature, boolean resolve)" + NL + "\t{" + NL + "\t\tswitch (eDerivedStructuralFeatureID(eFeature))" + NL + "\t\t{";
  protected final String TEXT_805 = NL + "\t\t\tcase ";
  protected final String TEXT_806 = ":";
  protected final String TEXT_807 = NL + "\t\t\t\treturn ";
  protected final String TEXT_808 = "() ? Boolean.TRUE : Boolean.FALSE;";
  protected final String TEXT_809 = NL + "\t\t\t\treturn new ";
  protected final String TEXT_810 = "(";
  protected final String TEXT_811 = "());";
  protected final String TEXT_812 = NL + "\t\t\t\tif (resolve) return ";
  protected final String TEXT_813 = "();" + NL + "\t\t\t\treturn basicGet";
  protected final String TEXT_814 = "();";
  protected final String TEXT_815 = NL + "\t\t\t\treturn ((";
  protected final String TEXT_816 = ")";
  protected final String TEXT_817 = "()).eMap();";
  protected final String TEXT_818 = NL + "\t\t\t\treturn ((";
  protected final String TEXT_819 = ")";
  protected final String TEXT_820 = "()).featureMap();";
  protected final String TEXT_821 = NL + "\t\t\t\treturn ";
  protected final String TEXT_822 = "();";
  protected final String TEXT_823 = NL + "\t\t}" + NL + "\t\treturn eDynamicGet(eFeature, resolve);" + NL + "\t}" + NL;
  protected final String TEXT_824 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */" + NL + "\tpublic void eSet(";
  protected final String TEXT_825 = " eFeature, Object newValue)" + NL + "\t{" + NL + "\t\tswitch (eDerivedStructuralFeatureID(eFeature))" + NL + "\t\t{";
  protected final String TEXT_826 = NL + "\t\t\tcase ";
  protected final String TEXT_827 = ":";
  protected final String TEXT_828 = NL + "\t\t\t\t((";
  protected final String TEXT_829 = ")";
  protected final String TEXT_830 = "()).featureMap().clear();";
  protected final String TEXT_831 = NL + "\t\t\t\t";
  protected final String TEXT_832 = "().clear();";
  protected final String TEXT_833 = NL + "\t\t\t\t((";
  protected final String TEXT_834 = ")";
  protected final String TEXT_835 = "()).featureMap().addAll((";
  protected final String TEXT_836 = ")newValue);";
  protected final String TEXT_837 = NL + "\t\t\t\t((";
  protected final String TEXT_838 = ")";
  protected final String TEXT_839 = "()).eMap().addAll((";
  protected final String TEXT_840 = ")newValue);";
  protected final String TEXT_841 = NL + "\t\t\t\t";
  protected final String TEXT_842 = "().addAll((";
  protected final String TEXT_843 = ")newValue);";
  protected final String TEXT_844 = NL + "\t\t\t\tset";
  protected final String TEXT_845 = "(((";
  protected final String TEXT_846 = ")newValue).";
  protected final String TEXT_847 = "());";
  protected final String TEXT_848 = NL + "\t\t\t\tset";
  protected final String TEXT_849 = "((";
  protected final String TEXT_850 = ")newValue);";
  protected final String TEXT_851 = NL + "\t\t\t\treturn;";
  protected final String TEXT_852 = NL + "\t\t}" + NL + "\t\teDynamicSet(eFeature, newValue);" + NL + "\t}" + NL + "" + NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */" + NL + "\tpublic void eUnset(";
  protected final String TEXT_853 = " eFeature)" + NL + "\t{" + NL + "\t\tswitch (eDerivedStructuralFeatureID(eFeature))" + NL + "\t\t{";
  protected final String TEXT_854 = NL + "\t\t\tcase ";
  protected final String TEXT_855 = ":";
  protected final String TEXT_856 = NL + "\t\t\t\t((";
  protected final String TEXT_857 = ")";
  protected final String TEXT_858 = "()).featureMap().clear();";
  protected final String TEXT_859 = NL + "\t\t\t\t";
  protected final String TEXT_860 = "().clear();";
  protected final String TEXT_861 = NL + "\t\t\t\tunset";
  protected final String TEXT_862 = "();";
  protected final String TEXT_863 = NL + "\t\t\t\tset";
  protected final String TEXT_864 = "((";
  protected final String TEXT_865 = ")null);";
  protected final String TEXT_866 = NL + "\t\t\t\tset";
  protected final String TEXT_867 = "(";
  protected final String TEXT_868 = "_EDEFAULT);";
  protected final String TEXT_869 = NL + "\t\t\t\treturn;";
  protected final String TEXT_870 = NL + "\t\t}" + NL + "\t\teDynamicUnset(eFeature);" + NL + "\t}" + NL;
  protected final String TEXT_871 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */" + NL + "\tpublic boolean eIsSet(";
  protected final String TEXT_872 = " eFeature)" + NL + "\t{" + NL + "\t\tswitch (eDerivedStructuralFeatureID(eFeature))" + NL + "\t\t{";
  protected final String TEXT_873 = NL + "\t\t\tcase ";
  protected final String TEXT_874 = ":";
  protected final String TEXT_875 = NL + "\t\t\t\treturn !((";
  protected final String TEXT_876 = ")";
  protected final String TEXT_877 = "()).featureMap().isEmpty();";
  protected final String TEXT_878 = NL + "\t\t\t\treturn ";
  protected final String TEXT_879 = " != null && !";
  protected final String TEXT_880 = ".featureMap().isEmpty();";
  protected final String TEXT_881 = NL + "\t\t\t\treturn ";
  protected final String TEXT_882 = " != null && !";
  protected final String TEXT_883 = ".isEmpty();";
  protected final String TEXT_884 = NL + "\t\t\t\t";
  protected final String TEXT_885 = " ";
  protected final String TEXT_886 = " = (";
  protected final String TEXT_887 = ")eVirtualGet(";
  protected final String TEXT_888 = ");" + NL + "\t\t\t\treturn ";
  protected final String TEXT_889 = " != null && !";
  protected final String TEXT_890 = ".isEmpty();";
  protected final String TEXT_891 = NL + "\t\t\t\treturn !";
  protected final String TEXT_892 = "().isEmpty();";
  protected final String TEXT_893 = NL + "\t\t\t\treturn isSet";
  protected final String TEXT_894 = "();";
  protected final String TEXT_895 = NL + "\t\t\t\treturn ";
  protected final String TEXT_896 = " != null;";
  protected final String TEXT_897 = NL + "\t\t\t\treturn eVirtualGet(";
  protected final String TEXT_898 = ") != null;";
  protected final String TEXT_899 = NL + "\t\t\t\treturn basicGet";
  protected final String TEXT_900 = "() != null;";
  protected final String TEXT_901 = NL + "\t\t\t\treturn ";
  protected final String TEXT_902 = " != null;";
  protected final String TEXT_903 = NL + "\t\t\t\treturn eVirtualGet(";
  protected final String TEXT_904 = ") != null;";
  protected final String TEXT_905 = NL + "\t\t\t\treturn ";
  protected final String TEXT_906 = "() != null;";
  protected final String TEXT_907 = NL + "\t\t\t\treturn ((";
  protected final String TEXT_908 = " & ";
  protected final String TEXT_909 = "_EFLAG) != 0) != ";
  protected final String TEXT_910 = "_EDEFAULT;";
  protected final String TEXT_911 = NL + "\t\t\t\treturn ";
  protected final String TEXT_912 = " != ";
  protected final String TEXT_913 = "_EDEFAULT;";
  protected final String TEXT_914 = NL + "\t\t\t\treturn eVirtualGet(";
  protected final String TEXT_915 = ", ";
  protected final String TEXT_916 = "_EDEFAULT) != ";
  protected final String TEXT_917 = "_EDEFAULT;";
  protected final String TEXT_918 = NL + "\t\t\t\treturn ";
  protected final String TEXT_919 = "() != ";
  protected final String TEXT_920 = "_EDEFAULT;";
  protected final String TEXT_921 = NL + "\t\t\t\treturn ";
  protected final String TEXT_922 = "_EDEFAULT == null ? ";
  protected final String TEXT_923 = " != null : !";
  protected final String TEXT_924 = "_EDEFAULT.equals(";
  protected final String TEXT_925 = ");";
  protected final String TEXT_926 = NL + "\t\t\t\t";
  protected final String TEXT_927 = " ";
  protected final String TEXT_928 = " = (";
  protected final String TEXT_929 = ")eVirtualGet(";
  protected final String TEXT_930 = ", ";
  protected final String TEXT_931 = "_EDEFAULT);" + NL + "\t\t\t\treturn ";
  protected final String TEXT_932 = "_EDEFAULT == null ? ";
  protected final String TEXT_933 = " != null : !";
  protected final String TEXT_934 = "_EDEFAULT.equals(";
  protected final String TEXT_935 = ");";
  protected final String TEXT_936 = NL + "\t\t\t\treturn ";
  protected final String TEXT_937 = "_EDEFAULT == null ? ";
  protected final String TEXT_938 = "() != null : !";
  protected final String TEXT_939 = "_EDEFAULT.equals(";
  protected final String TEXT_940 = "());";
  protected final String TEXT_941 = NL + "\t\t}" + NL + "\t\treturn eDynamicIsSet(eFeature);" + NL + "\t}" + NL;
  protected final String TEXT_942 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */" + NL + "\tpublic int eBaseStructuralFeatureID(int derivedFeatureID, Class baseClass)" + NL + "\t{";
  protected final String TEXT_943 = NL + "\t\tif (baseClass == ";
  protected final String TEXT_944 = ".class)" + NL + "\t\t{" + NL + "\t\t\tswitch (derivedFeatureID)" + NL + "\t\t\t{";
  protected final String TEXT_945 = NL + "\t\t\t\tcase ";
  protected final String TEXT_946 = ": return ";
  protected final String TEXT_947 = ";";
  protected final String TEXT_948 = NL + "\t\t\t\tdefault: return -1;" + NL + "\t\t\t}" + NL + "\t\t}";
  protected final String TEXT_949 = NL + "\t\treturn super.eBaseStructuralFeatureID(derivedFeatureID, baseClass);" + NL + "\t}" + NL + "" + NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */" + NL + "\tpublic int eDerivedStructuralFeatureID(int baseFeatureID, Class baseClass)" + NL + "\t{";
  protected final String TEXT_950 = NL + "\t\tif (baseClass == ";
  protected final String TEXT_951 = ".class)" + NL + "\t\t{" + NL + "\t\t\tswitch (baseFeatureID)" + NL + "\t\t\t{";
  protected final String TEXT_952 = NL + "\t\t\t\tcase ";
  protected final String TEXT_953 = ": return ";
  protected final String TEXT_954 = ";";
  protected final String TEXT_955 = NL + "\t\t\t\tdefault: return -1;" + NL + "\t\t\t}" + NL + "\t\t}";
  protected final String TEXT_956 = NL + "\t\treturn super.eDerivedStructuralFeatureID(baseFeatureID, baseClass);" + NL + "\t}" + NL;
  protected final String TEXT_957 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */" + NL + "\tprotected Object[] eVirtualValues()" + NL + "\t{" + NL + "\t\treturn ";
  protected final String TEXT_958 = ";" + NL + "\t}" + NL + "" + NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */" + NL + "\tprotected void eSetVirtualValues(Object[] newValues)" + NL + "\t{" + NL + "\t\t";
  protected final String TEXT_959 = " = newValues;" + NL + "\t}" + NL;
  protected final String TEXT_960 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */" + NL + "\tprotected int eVirtualIndexBits(int offset)" + NL + "\t{" + NL + "\t\tswitch (offset)" + NL + "\t\t{";
  protected final String TEXT_961 = NL + "\t\t\tcase ";
  protected final String TEXT_962 = " :" + NL + "\t\t\t\treturn ";
  protected final String TEXT_963 = ";";
  protected final String TEXT_964 = NL + "\t\t\tdefault :" + NL + "\t\t\t\tthrow new IndexOutOfBoundsException();" + NL + "\t\t}" + NL + "\t}" + NL + "" + NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */" + NL + "\tprotected void eSetVirtualIndexBits(int offset, int newIndexBits)" + NL + "\t{" + NL + "\t\tswitch (offset)" + NL + "\t\t{";
  protected final String TEXT_965 = NL + "\t\t\tcase ";
  protected final String TEXT_966 = " :" + NL + "\t\t\t\t";
  protected final String TEXT_967 = " = newIndexBits;" + NL + "\t\t\t\tbreak;";
  protected final String TEXT_968 = NL + "\t\t\tdefault :" + NL + "\t\t\t\tthrow new IndexOutOfBoundsException();" + NL + "\t\t}" + NL + "\t}" + NL;
  protected final String TEXT_969 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */" + NL + "\tpublic String toString()" + NL + "\t{" + NL + "\t\tif (eIsProxy()) return super.toString();" + NL + "" + NL + "\t\tStringBuffer result = new StringBuffer(super.toString());";
  protected final String TEXT_970 = NL + "\t\tresult.append(\" (";
  protected final String TEXT_971 = ": \");";
  protected final String TEXT_972 = NL + "\t\tresult.append(\", ";
  protected final String TEXT_973 = ": \");";
  protected final String TEXT_974 = NL + "\t\tif (eVirtualIsSet(";
  protected final String TEXT_975 = ")) result.append(eVirtualGet(";
  protected final String TEXT_976 = ")); else result.append(\"<unset>\");";
  protected final String TEXT_977 = NL + "\t\tif (";
  protected final String TEXT_978 = "(";
  protected final String TEXT_979 = " & ";
  protected final String TEXT_980 = "_ESETFLAG) != 0";
  protected final String TEXT_981 = "ESet";
  protected final String TEXT_982 = ") result.append((";
  protected final String TEXT_983 = " & ";
  protected final String TEXT_984 = "_EFLAG) != 0); else result.append(\"<unset>\");";
  protected final String TEXT_985 = NL + "\t\tif (";
  protected final String TEXT_986 = "(";
  protected final String TEXT_987 = " & ";
  protected final String TEXT_988 = "_ESETFLAG) != 0";
  protected final String TEXT_989 = "ESet";
  protected final String TEXT_990 = ") result.append(";
  protected final String TEXT_991 = "); else result.append(\"<unset>\");";
  protected final String TEXT_992 = NL + "\t\tresult.append(eVirtualGet(";
  protected final String TEXT_993 = ", ";
  protected final String TEXT_994 = "_EDEFAULT";
  protected final String TEXT_995 = "));";
  protected final String TEXT_996 = NL + "\t\tresult.append((";
  protected final String TEXT_997 = " & ";
  protected final String TEXT_998 = "_EFLAG) != 0);";
  protected final String TEXT_999 = NL + "\t\tresult.append(";
  protected final String TEXT_1000 = ");";
  protected final String TEXT_1001 = NL + "\t\tresult.append(')');" + NL + "\t\treturn result.toString();" + NL + "\t}" + NL;
  protected final String TEXT_1002 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */" + NL + "\tprotected int hash = -1;" + NL + "" + NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */" + NL + "\tpublic int getHash()" + NL + "\t{" + NL + "\t\tif (hash == -1)" + NL + "\t\t{" + NL + "\t\t\tObject theKey = getKey();" + NL + "\t\t\thash = (theKey == null ? 0 : theKey.hashCode());" + NL + "\t\t}" + NL + "\t\treturn hash;" + NL + "\t}" + NL + "" + NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */" + NL + "\tpublic void setHash(int hash)" + NL + "\t{" + NL + "\t\tthis.hash = hash;" + NL + "\t}" + NL + "" + NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */" + NL + "\tpublic Object getKey()" + NL + "\t{";
  protected final String TEXT_1003 = NL + "\t\treturn new ";
  protected final String TEXT_1004 = "(getTypedKey());";
  protected final String TEXT_1005 = NL + "\t\treturn getTypedKey();";
  protected final String TEXT_1006 = NL + "\t}" + NL + "" + NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */" + NL + "\tpublic void setKey(Object key)" + NL + "\t{";
  protected final String TEXT_1007 = NL + "\t\tgetTypedKey().addAll((";
  protected final String TEXT_1008 = ")key);";
  protected final String TEXT_1009 = NL + "\t\tsetTypedKey(((";
  protected final String TEXT_1010 = ")key).";
  protected final String TEXT_1011 = "());";
  protected final String TEXT_1012 = NL + "\t\tsetTypedKey((";
  protected final String TEXT_1013 = ")key);";
  protected final String TEXT_1014 = NL + "\t}" + NL + "" + NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */" + NL + "\tpublic Object getValue()" + NL + "\t{";
  protected final String TEXT_1015 = NL + "\t\treturn new ";
  protected final String TEXT_1016 = "(getTypedValue());";
  protected final String TEXT_1017 = NL + "\t\treturn getTypedValue();";
  protected final String TEXT_1018 = NL + "\t}" + NL + "" + NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */" + NL + "\tpublic Object setValue(Object value)" + NL + "\t{" + NL + "\t\tObject oldValue = getValue();";
  protected final String TEXT_1019 = NL + "\t\tgetTypedValue().clear();" + NL + "\t\tgetTypedValue().addAll((";
  protected final String TEXT_1020 = ")value);";
  protected final String TEXT_1021 = NL + "\t\tsetTypedValue(((";
  protected final String TEXT_1022 = ")value).";
  protected final String TEXT_1023 = "());";
  protected final String TEXT_1024 = NL + "\t\tsetTypedValue((";
  protected final String TEXT_1025 = ")value);";
  protected final String TEXT_1026 = NL + "\t\treturn oldValue;" + NL + "\t}" + NL + "" + NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */" + NL + "\tpublic ";
  protected final String TEXT_1027 = " getEMap()" + NL + "\t{" + NL + "\t\t";
  protected final String TEXT_1028 = " container = eContainer();" + NL + "\t\treturn container == null ? null : (";
  protected final String TEXT_1029 = ")container.eGet(eContainmentFeature());" + NL + "\t}" + NL;
  protected final String TEXT_1030 = NL + "} //";
  protected final String TEXT_1031 = NL;

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
    if (genModel.isVirtualDelegation()) { String eVirtualValuesField = genClass.getEVirtualValuesField();
    if (eVirtualValuesField != null) {
    stringBuffer.append(TEXT_26);
    stringBuffer.append(eVirtualValuesField);
    stringBuffer.append(TEXT_27);
    }
    { List eVirtualIndexBitFields = genClass.getEVirtualIndexBitFields(new ArrayList());
    if (!eVirtualIndexBitFields.isEmpty()) {
    for (Iterator i = eVirtualIndexBitFields.iterator(); i.hasNext();) { String eVirtualIndexBitField = (String)i.next();
    stringBuffer.append(TEXT_28);
    stringBuffer.append(eVirtualIndexBitField);
    stringBuffer.append(TEXT_29);
    }
    }
    }
    }
    if (genClass.isModelRoot() && genModel.isBooleanFlagsEnabled() && genModel.getBooleanFlagsReservedBits() == -1) {
    stringBuffer.append(TEXT_30);
    stringBuffer.append(genModel.getBooleanFlagsField());
    stringBuffer.append(TEXT_31);
    }
    if (!genModel.isReflectiveDelegation()) {
    for (Iterator i=genClass.getDeclaredFieldGenFeatures().iterator(); i.hasNext();) { GenFeature genFeature = (GenFeature)i.next();
    if (genFeature.isListType() || genFeature.isReferenceType()) {
    if (genClass.isField(genFeature)) {
    stringBuffer.append(TEXT_32);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_33);
    stringBuffer.append(genFeature.getFormattedName());
    stringBuffer.append(TEXT_34);
    stringBuffer.append(genFeature.getFeatureKind());
    stringBuffer.append(TEXT_35);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_36);
    stringBuffer.append(genFeature.getImportedInternalType());
    stringBuffer.append(TEXT_37);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_38);
    }
    } else {
    if (!genFeature.isVolatile() || !genModel.isReflectiveDelegation() && (!genFeature.hasDelegateFeature() || !genFeature.isUnsettable())) {
    stringBuffer.append(TEXT_39);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_40);
    stringBuffer.append(genFeature.getFormattedName());
    stringBuffer.append(TEXT_41);
    stringBuffer.append(genFeature.getFeatureKind());
    stringBuffer.append(TEXT_42);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_43);
    stringBuffer.append(genFeature.getImportedType());
    stringBuffer.append(TEXT_44);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_45);
    stringBuffer.append(genFeature.getStaticDefaultValue());
    stringBuffer.append(TEXT_46);
    stringBuffer.append(genModel.getNonNLS(genFeature.getStaticDefaultValue()));
    stringBuffer.append(TEXT_47);
    }
    if (genClass.isField(genFeature)) {
    if (genClass.isFlag(genFeature)) {
    if (genClass.getFlagIndex(genFeature) > 31 && genClass.getFlagIndex(genFeature) % 32 == 0) {
    stringBuffer.append(TEXT_48);
    stringBuffer.append(genClass.getFlagsField(genFeature));
    stringBuffer.append(TEXT_49);
    }
    stringBuffer.append(TEXT_50);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_51);
    stringBuffer.append(genFeature.getFormattedName());
    stringBuffer.append(TEXT_52);
    stringBuffer.append(genFeature.getFeatureKind());
    stringBuffer.append(TEXT_53);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_54);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_55);
    stringBuffer.append("<< " + genClass.getFlagIndex(genFeature) % 32 );
    stringBuffer.append(TEXT_56);
    } else {
    stringBuffer.append(TEXT_57);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_58);
    stringBuffer.append(genFeature.getFormattedName());
    stringBuffer.append(TEXT_59);
    stringBuffer.append(genFeature.getFeatureKind());
    stringBuffer.append(TEXT_60);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_61);
    stringBuffer.append(genFeature.getImportedType());
    stringBuffer.append(TEXT_62);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_63);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_64);
    }
    }
    }
    if (genClass.isESetField(genFeature)) {
    if (genClass.isESetFlag(genFeature)) {
    if (genClass.getESetFlagIndex(genFeature) > 31 && genClass.getESetFlagIndex(genFeature) % 32 == 0) {
    stringBuffer.append(TEXT_65);
    stringBuffer.append(genClass.getESetFlagsField(genFeature));
    stringBuffer.append(TEXT_66);
    }
    stringBuffer.append(TEXT_67);
    stringBuffer.append(genFeature.getFormattedName());
    stringBuffer.append(TEXT_68);
    stringBuffer.append(genFeature.getFeatureKind());
    stringBuffer.append(TEXT_69);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_70);
    stringBuffer.append("<< " + genClass.getESetFlagIndex(genFeature) % 32 );
    stringBuffer.append(TEXT_71);
    } else {
    stringBuffer.append(TEXT_72);
    stringBuffer.append(genFeature.getFormattedName());
    stringBuffer.append(TEXT_73);
    stringBuffer.append(genFeature.getFeatureKind());
    stringBuffer.append(TEXT_74);
    stringBuffer.append(genFeature.getUncapName());
    stringBuffer.append(TEXT_75);
    }
    }
    }
    //Class/declaredFieldGenFeature.override.javajetinc
    }
    stringBuffer.append(TEXT_76);
    stringBuffer.append(genClass.getClassName());
    stringBuffer.append(TEXT_77);
    for (Iterator i=genClass.getFlagGenFeatures("true").iterator(); i.hasNext();) { GenFeature genFeature = (GenFeature)i.next();
    stringBuffer.append(TEXT_78);
    stringBuffer.append(genClass.getFlagsField(genFeature));
    stringBuffer.append(TEXT_79);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_80);
    }
    stringBuffer.append(TEXT_81);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.EClass"));
    stringBuffer.append(TEXT_82);
    stringBuffer.append(genPackage.getImportedPackageInterfaceName());
    stringBuffer.append(TEXT_83);
    stringBuffer.append(genClass.getClassifierAccessorName());
    stringBuffer.append(TEXT_84);
    for (Iterator i=genClass.getImplementedGenFeatures().iterator(); i.hasNext();) { GenFeature genFeature = (GenFeature)i.next();
    if (genFeature.isGet()) {
    stringBuffer.append(TEXT_85);
    stringBuffer.append(genFeature.getImportedType());
    stringBuffer.append(TEXT_86);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_87);
    if (genModel.isReflectiveDelegation()) {
    stringBuffer.append(TEXT_88);
    if (genFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_89);
    }
    stringBuffer.append(TEXT_90);
    stringBuffer.append(genFeature.getObjectType());
    stringBuffer.append(TEXT_91);
    stringBuffer.append(genFeature.getGenPackage().getImportedPackageInterfaceName());
    stringBuffer.append(TEXT_92);
    stringBuffer.append(genFeature.getFeatureAccessorName());
    stringBuffer.append(TEXT_93);
    if (genFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_94);
    stringBuffer.append(genFeature.getPrimitiveValueFunction());
    stringBuffer.append(TEXT_95);
    }
    stringBuffer.append(TEXT_96);
    } else if (!genFeature.isVolatile()) {
    if (genFeature.isListType()) {
    if (genModel.isVirtualDelegation()) {
    stringBuffer.append(TEXT_97);
    stringBuffer.append(genFeature.getImportedType());
    stringBuffer.append(TEXT_98);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_99);
    stringBuffer.append(genFeature.getImportedType());
    stringBuffer.append(TEXT_100);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(TEXT_101);
    }
    stringBuffer.append(TEXT_102);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_103);
    if (genModel.isVirtualDelegation()) {
    stringBuffer.append(TEXT_104);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(TEXT_105);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_106);
    stringBuffer.append(genClass.getListConstructor(genFeature));
    stringBuffer.append(TEXT_107);
    } else {
    stringBuffer.append(TEXT_108);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_109);
    stringBuffer.append(genClass.getListConstructor(genFeature));
    stringBuffer.append(TEXT_110);
    }
    stringBuffer.append(TEXT_111);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(genFeature.isMapType() && genFeature.isEffectiveSuppressEMFTypes() ? ".map()" : "");
    stringBuffer.append(TEXT_112);
    } else if (genFeature.isContainer()) {
    stringBuffer.append(TEXT_113);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(TEXT_114);
    stringBuffer.append(genFeature.getImportedType());
    stringBuffer.append(TEXT_115);
    } else {
    if (genModel.isVirtualDelegation() && !genFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_116);
    stringBuffer.append(genFeature.getImportedType());
    stringBuffer.append(TEXT_117);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_118);
    stringBuffer.append(genFeature.getImportedType());
    stringBuffer.append(TEXT_119);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    if (!genFeature.isReferenceType()) {
    stringBuffer.append(TEXT_120);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_121);
    }
    stringBuffer.append(TEXT_122);
    }
    if (genFeature.isResolveProxies()) {
    stringBuffer.append(TEXT_123);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_124);
    stringBuffer.append(genFeature.getSafeNameAsEObject());
    stringBuffer.append(TEXT_125);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.InternalEObject"));
    stringBuffer.append(TEXT_126);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_127);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.InternalEObject"));
    stringBuffer.append(TEXT_128);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_129);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_130);
    stringBuffer.append(genFeature.getNonEObjectInternalTypeCast());
    stringBuffer.append(TEXT_131);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_132);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_133);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_134);
    if (genFeature.isContains()) {
    stringBuffer.append(TEXT_135);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.InternalEObject"));
    stringBuffer.append(TEXT_136);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_137);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.InternalEObject"));
    stringBuffer.append(TEXT_138);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_139);
    if (!genFeature.isBidirectional()) {
    stringBuffer.append(TEXT_140);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.notify.NotificationChain"));
    stringBuffer.append(TEXT_141);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_142);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(TEXT_143);
    } else { GenFeature reverseFeature = genFeature.getReverse(); GenClass targetClass = reverseFeature.getGenClass();
    stringBuffer.append(TEXT_144);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.notify.NotificationChain"));
    stringBuffer.append(TEXT_145);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_146);
    stringBuffer.append(targetClass.getQualifiedFeatureID(reverseFeature));
    stringBuffer.append(TEXT_147);
    stringBuffer.append(targetClass.getImportedInterfaceName());
    stringBuffer.append(TEXT_148);
    }
    stringBuffer.append(TEXT_149);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_150);
    if (!genFeature.isBidirectional()) {
    stringBuffer.append(TEXT_151);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_152);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(TEXT_153);
    } else { GenFeature reverseFeature = genFeature.getReverse(); GenClass targetClass = reverseFeature.getGenClass();
    stringBuffer.append(TEXT_154);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_155);
    stringBuffer.append(targetClass.getQualifiedFeatureID(reverseFeature));
    stringBuffer.append(TEXT_156);
    stringBuffer.append(targetClass.getImportedInterfaceName());
    stringBuffer.append(TEXT_157);
    }
    stringBuffer.append(TEXT_158);
    } else if (genModel.isVirtualDelegation()) {
    stringBuffer.append(TEXT_159);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(TEXT_160);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_161);
    }
    stringBuffer.append(TEXT_162);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.impl.ENotificationImpl"));
    stringBuffer.append(TEXT_163);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.notify.Notification"));
    stringBuffer.append(TEXT_164);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(TEXT_165);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_166);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_167);
    }
    if (genClass.isFlag(genFeature)) {
    stringBuffer.append(TEXT_168);
    stringBuffer.append(genClass.getFlagsField(genFeature));
    stringBuffer.append(TEXT_169);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_170);
    } else {
    stringBuffer.append(TEXT_171);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_172);
    }
    }
    } else {//volatile
    if (genFeature.isResolveProxies() && !genFeature.isListType()) {
    stringBuffer.append(TEXT_173);
    stringBuffer.append(genFeature.getImportedType());
    stringBuffer.append(TEXT_174);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_175);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_176);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_177);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_178);
    stringBuffer.append(genFeature.getNonEObjectInternalTypeCast());
    stringBuffer.append(TEXT_179);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.InternalEObject"));
    stringBuffer.append(TEXT_180);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_181);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_182);
    } else if (genFeature.hasDelegateFeature()) { GenFeature delegateFeature = genFeature.getDelegateFeature();
    if (genFeature.isFeatureMapType()) {
    if (delegateFeature.isWrappedFeatureMapType()) {
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
    if (delegateFeature.isWrappedFeatureMapType()) {
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
    if (delegateFeature.isWrappedFeatureMapType()) {
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
    //Class/getGenFeature.todo.override.javajetinc
    }
    }
    stringBuffer.append(TEXT_224);
    //Class/getGenFeature.override.javajetinc
    }
    if (!genModel.isReflectiveDelegation() && genFeature.isBasicGet()) {
    stringBuffer.append(TEXT_225);
    stringBuffer.append(genFeature.getImportedType());
    stringBuffer.append(TEXT_226);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_227);
    if (genFeature.isContainer()) {
    stringBuffer.append(TEXT_228);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(TEXT_229);
    stringBuffer.append(genFeature.getImportedType());
    stringBuffer.append(TEXT_230);
    } else if (!genFeature.isVolatile()) {
    if (genModel.isVirtualDelegation()) {
    stringBuffer.append(TEXT_231);
    stringBuffer.append(genFeature.getImportedType());
    stringBuffer.append(TEXT_232);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(TEXT_233);
    } else {
    stringBuffer.append(TEXT_234);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_235);
    }
    } else if (genFeature.hasDelegateFeature()) { GenFeature delegateFeature = genFeature.getDelegateFeature();
    if (delegateFeature.isWrappedFeatureMapType()) {
    stringBuffer.append(TEXT_236);
    stringBuffer.append(genFeature.getImportedType());
    stringBuffer.append(TEXT_237);
    stringBuffer.append(delegateFeature.getImportedEffectiveFeatureMapWrapperInternalInterface());
    stringBuffer.append(TEXT_238);
    stringBuffer.append(delegateFeature.getAccessorName());
    stringBuffer.append(TEXT_239);
    stringBuffer.append(genFeature.getQualifiedFeatureAccessorName());
    stringBuffer.append(TEXT_240);
    } else {
    stringBuffer.append(TEXT_241);
    stringBuffer.append(genFeature.getImportedType());
    stringBuffer.append(TEXT_242);
    stringBuffer.append(delegateFeature.getAccessorName());
    stringBuffer.append(TEXT_243);
    stringBuffer.append(genFeature.getQualifiedFeatureAccessorName());
    stringBuffer.append(TEXT_244);
    }
    } else {
    stringBuffer.append(TEXT_245);
    stringBuffer.append(genFeature.getFormattedName());
    stringBuffer.append(TEXT_246);
    stringBuffer.append(genFeature.getFeatureKind());
    stringBuffer.append(TEXT_247);
    //Class/basicGetGenFeature.todo.override.javajetinc
    }
    stringBuffer.append(TEXT_248);
    //Class/basicGetGenFeature.override.javajetinc
    }
    if (!genModel.isReflectiveDelegation() && genFeature.isBasicSet()) {
    stringBuffer.append(TEXT_249);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.notify.NotificationChain"));
    stringBuffer.append(TEXT_250);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_251);
    stringBuffer.append(genFeature.getImportedInternalType());
    stringBuffer.append(TEXT_252);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_253);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.notify.NotificationChain"));
    stringBuffer.append(TEXT_254);
    if (!genFeature.isVolatile()) {
    if (genModel.isVirtualDelegation()) {
    stringBuffer.append(TEXT_255);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_256);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(TEXT_257);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_258);
    } else {
    stringBuffer.append(TEXT_259);
    stringBuffer.append(genFeature.getImportedType());
    stringBuffer.append(TEXT_260);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_261);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_262);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_263);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_264);
    }
    if (genFeature.isUnsettable()) {
    if (genModel.isVirtualDelegation()) {
    stringBuffer.append(TEXT_265);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_266);
    } else if (genClass.isESetFlag(genFeature)) {
    stringBuffer.append(TEXT_267);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_268);
    stringBuffer.append(genClass.getESetFlagsField(genFeature));
    stringBuffer.append(TEXT_269);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_270);
    stringBuffer.append(genClass.getESetFlagsField(genFeature));
    stringBuffer.append(TEXT_271);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_272);
    } else {
    stringBuffer.append(TEXT_273);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_274);
    stringBuffer.append(genFeature.getUncapName());
    stringBuffer.append(TEXT_275);
    stringBuffer.append(genFeature.getUncapName());
    stringBuffer.append(TEXT_276);
    }
    }
    stringBuffer.append(TEXT_277);
    if (genFeature.isUnsettable()) {
    stringBuffer.append(TEXT_278);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.impl.ENotificationImpl"));
    stringBuffer.append(TEXT_279);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.impl.ENotificationImpl"));
    stringBuffer.append(TEXT_280);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.notify.Notification"));
    stringBuffer.append(TEXT_281);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(TEXT_282);
    if (genModel.isVirtualDelegation()) {
    stringBuffer.append(TEXT_283);
    stringBuffer.append(genFeature.getCapName());
    } else {
    stringBuffer.append(TEXT_284);
    stringBuffer.append(genFeature.getCapName());
    }
    stringBuffer.append(TEXT_285);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_286);
    if (genModel.isVirtualDelegation()) {
    stringBuffer.append(TEXT_287);
    } else {
    stringBuffer.append(TEXT_288);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_289);
    }
    stringBuffer.append(TEXT_290);
    } else {
    stringBuffer.append(TEXT_291);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.impl.ENotificationImpl"));
    stringBuffer.append(TEXT_292);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.impl.ENotificationImpl"));
    stringBuffer.append(TEXT_293);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.notify.Notification"));
    stringBuffer.append(TEXT_294);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(TEXT_295);
    if (genModel.isVirtualDelegation()) {
    stringBuffer.append(TEXT_296);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_297);
    stringBuffer.append(genFeature.getCapName());
    } else {
    stringBuffer.append(TEXT_298);
    stringBuffer.append(genFeature.getCapName());
    }
    stringBuffer.append(TEXT_299);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_300);
    }
    stringBuffer.append(TEXT_301);
    stringBuffer.append(TEXT_302);
    } else if (genFeature.hasDelegateFeature()) { GenFeature delegateFeature = genFeature.getDelegateFeature();
    if (delegateFeature.isWrappedFeatureMapType()) {
    stringBuffer.append(TEXT_303);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.FeatureMap"));
    stringBuffer.append(TEXT_304);
    stringBuffer.append(delegateFeature.getImportedEffectiveFeatureMapWrapperInternalInterface());
    stringBuffer.append(TEXT_305);
    stringBuffer.append(delegateFeature.getAccessorName());
    stringBuffer.append(TEXT_306);
    stringBuffer.append(genFeature.getQualifiedFeatureAccessorName());
    stringBuffer.append(TEXT_307);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_308);
    } else {
    stringBuffer.append(TEXT_309);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.FeatureMap"));
    stringBuffer.append(TEXT_310);
    stringBuffer.append(delegateFeature.getAccessorName());
    stringBuffer.append(TEXT_311);
    stringBuffer.append(genFeature.getQualifiedFeatureAccessorName());
    stringBuffer.append(TEXT_312);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_313);
    }
    } else {
    stringBuffer.append(TEXT_314);
    stringBuffer.append(genFeature.getFormattedName());
    stringBuffer.append(TEXT_315);
    stringBuffer.append(genFeature.getFeatureKind());
    stringBuffer.append(TEXT_316);
    //Class/basicSetGenFeature.todo.override.javajetinc
    }
    stringBuffer.append(TEXT_317);
    //Class/basicSetGenFeature.override.javajetinc
    }
    if (genFeature.isSet()) {
    stringBuffer.append(TEXT_318);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_319);
    stringBuffer.append(genFeature.getImportedType());
    stringBuffer.append(TEXT_320);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_321);
    if (genModel.isReflectiveDelegation()) {
    stringBuffer.append(TEXT_322);
    stringBuffer.append(genFeature.getGenPackage().getImportedPackageInterfaceName());
    stringBuffer.append(TEXT_323);
    stringBuffer.append(genFeature.getFeatureAccessorName());
    stringBuffer.append(TEXT_324);
    if (genFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_325);
    stringBuffer.append(genFeature.getObjectType());
    stringBuffer.append(TEXT_326);
    }
    stringBuffer.append(TEXT_327);
    stringBuffer.append(genFeature.getCapName());
    if (genFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_328);
    }
    stringBuffer.append(TEXT_329);
    } else if (!genFeature.isVolatile()) {
    if (genFeature.isContainer()) { GenFeature reverseFeature = genFeature.getReverse(); GenClass targetClass = reverseFeature.getGenClass();
    stringBuffer.append(TEXT_330);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_331);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(TEXT_332);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_333);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.EcoreUtil"));
    stringBuffer.append(TEXT_334);
    stringBuffer.append(genFeature.getEObjectCast());
    stringBuffer.append(TEXT_335);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_336);
    stringBuffer.append(genModel.getImportedName("java.lang.IllegalArgumentException"));
    stringBuffer.append(TEXT_337);
    stringBuffer.append(genModel.getNonNLS());
    stringBuffer.append(TEXT_338);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.notify.NotificationChain"));
    stringBuffer.append(TEXT_339);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_340);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.InternalEObject"));
    stringBuffer.append(TEXT_341);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_342);
    stringBuffer.append(targetClass.getQualifiedFeatureID(reverseFeature));
    stringBuffer.append(TEXT_343);
    stringBuffer.append(targetClass.getImportedInterfaceName());
    stringBuffer.append(TEXT_344);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.InternalEObject"));
    stringBuffer.append(TEXT_345);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_346);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(TEXT_347);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.impl.ENotificationImpl"));
    stringBuffer.append(TEXT_348);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.notify.Notification"));
    stringBuffer.append(TEXT_349);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(TEXT_350);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_351);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_352);
    } else if (genFeature.isBidirectional() || genFeature.isContains()) {
    if (genModel.isVirtualDelegation()) {
    stringBuffer.append(TEXT_353);
    stringBuffer.append(genFeature.getImportedType());
    stringBuffer.append(TEXT_354);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_355);
    stringBuffer.append(genFeature.getImportedType());
    stringBuffer.append(TEXT_356);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(TEXT_357);
    }
    stringBuffer.append(TEXT_358);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_359);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_360);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.notify.NotificationChain"));
    stringBuffer.append(TEXT_361);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_362);
    if (!genFeature.isBidirectional()) {
    stringBuffer.append(TEXT_363);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.InternalEObject"));
    stringBuffer.append(TEXT_364);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_365);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(TEXT_366);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_367);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.InternalEObject"));
    stringBuffer.append(TEXT_368);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_369);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(TEXT_370);
    } else { GenFeature reverseFeature = genFeature.getReverse(); GenClass targetClass = reverseFeature.getGenClass();
    stringBuffer.append(TEXT_371);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.InternalEObject"));
    stringBuffer.append(TEXT_372);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_373);
    stringBuffer.append(targetClass.getQualifiedFeatureID(reverseFeature));
    stringBuffer.append(TEXT_374);
    stringBuffer.append(targetClass.getImportedInterfaceName());
    stringBuffer.append(TEXT_375);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_376);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.InternalEObject"));
    stringBuffer.append(TEXT_377);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_378);
    stringBuffer.append(targetClass.getQualifiedFeatureID(reverseFeature));
    stringBuffer.append(TEXT_379);
    stringBuffer.append(targetClass.getImportedInterfaceName());
    stringBuffer.append(TEXT_380);
    }
    stringBuffer.append(TEXT_381);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_382);
    stringBuffer.append(genFeature.getInternalTypeCast());
    stringBuffer.append(TEXT_383);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_384);
    if (genFeature.isUnsettable()) {
    stringBuffer.append(TEXT_385);
    if (genModel.isVirtualDelegation()) {
    stringBuffer.append(TEXT_386);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_387);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(TEXT_388);
    } else if (genClass.isESetFlag(genFeature)) {
    stringBuffer.append(TEXT_389);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_390);
    stringBuffer.append(genClass.getESetFlagsField(genFeature));
    stringBuffer.append(TEXT_391);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_392);
    stringBuffer.append(genClass.getESetFlagsField(genFeature));
    stringBuffer.append(TEXT_393);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_394);
    } else {
    stringBuffer.append(TEXT_395);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_396);
    stringBuffer.append(genFeature.getUncapName());
    stringBuffer.append(TEXT_397);
    stringBuffer.append(genFeature.getUncapName());
    stringBuffer.append(TEXT_398);
    }
    stringBuffer.append(TEXT_399);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.impl.ENotificationImpl"));
    stringBuffer.append(TEXT_400);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.notify.Notification"));
    stringBuffer.append(TEXT_401);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(TEXT_402);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_403);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_404);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_405);
    } else {
    stringBuffer.append(TEXT_406);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.impl.ENotificationImpl"));
    stringBuffer.append(TEXT_407);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.notify.Notification"));
    stringBuffer.append(TEXT_408);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(TEXT_409);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_410);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_411);
    }
    } else {
    if (genClass.isFlag(genFeature)) {
    stringBuffer.append(TEXT_412);
    stringBuffer.append(genFeature.getImportedType());
    stringBuffer.append(TEXT_413);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_414);
    stringBuffer.append(genClass.getFlagsField(genFeature));
    stringBuffer.append(TEXT_415);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_416);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_417);
    stringBuffer.append(genClass.getFlagsField(genFeature));
    stringBuffer.append(TEXT_418);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_419);
    stringBuffer.append(genClass.getFlagsField(genFeature));
    stringBuffer.append(TEXT_420);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_421);
    } else {
    if (!genModel.isVirtualDelegation() || genFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_422);
    stringBuffer.append(genFeature.getImportedType());
    stringBuffer.append(TEXT_423);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_424);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_425);
    }
    if (genFeature.isEnumType()) {
    if (genModel.isVirtualDelegation()) {
    stringBuffer.append(TEXT_426);
    stringBuffer.append(genFeature.getImportedType());
    stringBuffer.append(TEXT_427);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_428);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_429);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_430);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_431);
    } else {
    stringBuffer.append(TEXT_432);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_433);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_434);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_435);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_436);
    }
    } else {
    if (genModel.isVirtualDelegation() && !genFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_437);
    stringBuffer.append(genFeature.getImportedType());
    stringBuffer.append(TEXT_438);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_439);
    stringBuffer.append(genFeature.getInternalTypeCast());
    stringBuffer.append(TEXT_440);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_441);
    } else {
    stringBuffer.append(TEXT_442);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_443);
    stringBuffer.append(genFeature.getInternalTypeCast());
    stringBuffer.append(TEXT_444);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_445);
    }
    }
    if (genModel.isVirtualDelegation() && !genFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_446);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_447);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(TEXT_448);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_449);
    }
    }
    if (genFeature.isUnsettable()) {
    if (genModel.isVirtualDelegation() && !genFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_450);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_451);
    } else if (genClass.isESetFlag(genFeature)) {
    stringBuffer.append(TEXT_452);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_453);
    stringBuffer.append(genClass.getESetFlagsField(genFeature));
    stringBuffer.append(TEXT_454);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_455);
    stringBuffer.append(genClass.getESetFlagsField(genFeature));
    stringBuffer.append(TEXT_456);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_457);
    } else {
    stringBuffer.append(TEXT_458);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_459);
    stringBuffer.append(genFeature.getUncapName());
    stringBuffer.append(TEXT_460);
    stringBuffer.append(genFeature.getUncapName());
    stringBuffer.append(TEXT_461);
    }
    stringBuffer.append(TEXT_462);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.impl.ENotificationImpl"));
    stringBuffer.append(TEXT_463);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.notify.Notification"));
    stringBuffer.append(TEXT_464);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(TEXT_465);
    if (genModel.isVirtualDelegation() && !genFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_466);
    if (genFeature.isReferenceType()) {
    stringBuffer.append(TEXT_467);
    } else {
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_468);
    }
    stringBuffer.append(TEXT_469);
    stringBuffer.append(genFeature.getCapName());
    } else {
    stringBuffer.append(TEXT_470);
    stringBuffer.append(genFeature.getCapName());
    }
    stringBuffer.append(TEXT_471);
    if (genClass.isFlag(genFeature)) {
    stringBuffer.append(TEXT_472);
    stringBuffer.append(genFeature.getCapName());
    } else {
    stringBuffer.append(genFeature.getSafeName());
    }
    stringBuffer.append(TEXT_473);
    if (genModel.isVirtualDelegation() && !genFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_474);
    } else {
    stringBuffer.append(TEXT_475);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_476);
    }
    stringBuffer.append(TEXT_477);
    } else {
    stringBuffer.append(TEXT_478);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.impl.ENotificationImpl"));
    stringBuffer.append(TEXT_479);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.notify.Notification"));
    stringBuffer.append(TEXT_480);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(TEXT_481);
    if (genModel.isVirtualDelegation() && !genFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_482);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_483);
    if (genFeature.isReferenceType()) {
    stringBuffer.append(TEXT_484);
    } else {
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_485);
    }
    stringBuffer.append(TEXT_486);
    stringBuffer.append(genFeature.getCapName());
    } else {
    stringBuffer.append(TEXT_487);
    stringBuffer.append(genFeature.getCapName());
    }
    stringBuffer.append(TEXT_488);
    if (genClass.isFlag(genFeature)) {
    stringBuffer.append(TEXT_489);
    stringBuffer.append(genFeature.getCapName());
    } else {
    stringBuffer.append(genFeature.getSafeName());
    }
    stringBuffer.append(TEXT_490);
    }
    }
    } else if (genFeature.hasDelegateFeature()) { GenFeature delegateFeature = genFeature.getDelegateFeature();
    if (delegateFeature.isWrappedFeatureMapType()) {
    stringBuffer.append(TEXT_491);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.FeatureMap"));
    stringBuffer.append(TEXT_492);
    stringBuffer.append(delegateFeature.getImportedEffectiveFeatureMapWrapperInternalInterface());
    stringBuffer.append(TEXT_493);
    stringBuffer.append(delegateFeature.getAccessorName());
    stringBuffer.append(TEXT_494);
    stringBuffer.append(genFeature.getQualifiedFeatureAccessorName());
    stringBuffer.append(TEXT_495);
    if (genFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_496);
    stringBuffer.append(genFeature.getObjectType());
    stringBuffer.append(TEXT_497);
    }
    stringBuffer.append(TEXT_498);
    stringBuffer.append(genFeature.getCapName());
    if (genFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_499);
    }
    stringBuffer.append(TEXT_500);
    } else {
    stringBuffer.append(TEXT_501);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.FeatureMap"));
    stringBuffer.append(TEXT_502);
    stringBuffer.append(delegateFeature.getAccessorName());
    stringBuffer.append(TEXT_503);
    stringBuffer.append(genFeature.getQualifiedFeatureAccessorName());
    stringBuffer.append(TEXT_504);
    if (genFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_505);
    stringBuffer.append(genFeature.getObjectType());
    stringBuffer.append(TEXT_506);
    }
    stringBuffer.append(TEXT_507);
    stringBuffer.append(genFeature.getCapName());
    if (genFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_508);
    }
    stringBuffer.append(TEXT_509);
    }
    } else {
    stringBuffer.append(TEXT_510);
    stringBuffer.append(genFeature.getFormattedName());
    stringBuffer.append(TEXT_511);
    stringBuffer.append(genFeature.getFeatureKind());
    stringBuffer.append(TEXT_512);
    //Class/setGenFeature.todo.override.javajetinc
    }
    stringBuffer.append(TEXT_513);
    //Class/setGenFeature.override.javajetinc
    }
    if (!genModel.isReflectiveDelegation() && genFeature.isBasicUnset()) {
    stringBuffer.append(TEXT_514);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.notify.NotificationChain"));
    stringBuffer.append(TEXT_515);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_516);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.notify.NotificationChain"));
    stringBuffer.append(TEXT_517);
    if (!genFeature.isVolatile()) {
    if (genModel.isVirtualDelegation()) {
    stringBuffer.append(TEXT_518);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_519);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(TEXT_520);
    } else {
    stringBuffer.append(TEXT_521);
    stringBuffer.append(genFeature.getImportedType());
    stringBuffer.append(TEXT_522);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_523);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_524);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_525);
    }
    if (genModel.isVirtualDelegation()) {
    stringBuffer.append(TEXT_526);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_527);
    } else if (genClass.isESetFlag(genFeature)) {
    stringBuffer.append(TEXT_528);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_529);
    stringBuffer.append(genClass.getESetFlagsField(genFeature));
    stringBuffer.append(TEXT_530);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_531);
    stringBuffer.append(genClass.getESetFlagsField(genFeature));
    stringBuffer.append(TEXT_532);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_533);
    } else {
    stringBuffer.append(TEXT_534);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_535);
    stringBuffer.append(genFeature.getUncapName());
    stringBuffer.append(TEXT_536);
    stringBuffer.append(genFeature.getUncapName());
    stringBuffer.append(TEXT_537);
    }
    stringBuffer.append(TEXT_538);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.impl.ENotificationImpl"));
    stringBuffer.append(TEXT_539);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.impl.ENotificationImpl"));
    stringBuffer.append(TEXT_540);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.notify.Notification"));
    stringBuffer.append(TEXT_541);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(TEXT_542);
    if (genModel.isVirtualDelegation()) {
    stringBuffer.append(TEXT_543);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_544);
    } else {
    stringBuffer.append(TEXT_545);
    stringBuffer.append(genFeature.getCapName());
    }
    stringBuffer.append(TEXT_546);
    if (genModel.isVirtualDelegation()) {
    stringBuffer.append(TEXT_547);
    } else {
    stringBuffer.append(TEXT_548);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_549);
    }
    stringBuffer.append(TEXT_550);
    } else {
    stringBuffer.append(TEXT_551);
    stringBuffer.append(genFeature.getFormattedName());
    stringBuffer.append(TEXT_552);
    stringBuffer.append(genFeature.getFeatureKind());
    stringBuffer.append(TEXT_553);
    //Class/basicUnsetGenFeature.todo.override.javajetinc
    }
    stringBuffer.append(TEXT_554);
    //Class.basicUnsetGenFeature.override.javajetinc
    }
    if (genFeature.isUnset()) {
    stringBuffer.append(TEXT_555);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_556);
    if (genModel.isReflectiveDelegation()) {
    stringBuffer.append(TEXT_557);
    stringBuffer.append(genFeature.getGenPackage().getImportedPackageInterfaceName());
    stringBuffer.append(TEXT_558);
    stringBuffer.append(genFeature.getFeatureAccessorName());
    stringBuffer.append(TEXT_559);
    } else if (!genFeature.isVolatile()) {
    if (genFeature.isListType()) {
    stringBuffer.append(TEXT_560);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.InternalEList"));
    stringBuffer.append(TEXT_561);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_562);
    } else if (genFeature.isBidirectional() || genFeature.isContains()) {
    if (genModel.isVirtualDelegation()) {
    stringBuffer.append(TEXT_563);
    stringBuffer.append(genFeature.getImportedType());
    stringBuffer.append(TEXT_564);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_565);
    stringBuffer.append(genFeature.getImportedType());
    stringBuffer.append(TEXT_566);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(TEXT_567);
    }
    stringBuffer.append(TEXT_568);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_569);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.notify.NotificationChain"));
    stringBuffer.append(TEXT_570);
    if (!genFeature.isBidirectional()) {
    stringBuffer.append(TEXT_571);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.InternalEObject"));
    stringBuffer.append(TEXT_572);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_573);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(TEXT_574);
    } else { GenFeature reverseFeature = genFeature.getReverse(); GenClass targetClass = reverseFeature.getGenClass();
    stringBuffer.append(TEXT_575);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.InternalEObject"));
    stringBuffer.append(TEXT_576);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_577);
    stringBuffer.append(targetClass.getQualifiedFeatureID(reverseFeature));
    stringBuffer.append(TEXT_578);
    stringBuffer.append(targetClass.getImportedInterfaceName());
    stringBuffer.append(TEXT_579);
    }
    stringBuffer.append(TEXT_580);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_581);
    if (genModel.isVirtualDelegation()) {
    stringBuffer.append(TEXT_582);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_583);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(TEXT_584);
    } else if (genClass.isESetFlag(genFeature)) {
    stringBuffer.append(TEXT_585);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_586);
    stringBuffer.append(genClass.getESetFlagsField(genFeature));
    stringBuffer.append(TEXT_587);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_588);
    stringBuffer.append(genClass.getESetFlagsField(genFeature));
    stringBuffer.append(TEXT_589);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_590);
    } else {
    stringBuffer.append(TEXT_591);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_592);
    stringBuffer.append(genFeature.getUncapName());
    stringBuffer.append(TEXT_593);
    stringBuffer.append(genFeature.getUncapName());
    stringBuffer.append(TEXT_594);
    }
    stringBuffer.append(TEXT_595);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.impl.ENotificationImpl"));
    stringBuffer.append(TEXT_596);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.notify.Notification"));
    stringBuffer.append(TEXT_597);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(TEXT_598);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_599);
    } else {
    if (genClass.isFlag(genFeature)) {
    stringBuffer.append(TEXT_600);
    stringBuffer.append(genFeature.getImportedType());
    stringBuffer.append(TEXT_601);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_602);
    stringBuffer.append(genClass.getFlagsField(genFeature));
    stringBuffer.append(TEXT_603);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_604);
    } else if (genModel.isVirtualDelegation() && !genFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_605);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_606);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(TEXT_607);
    } else {
    stringBuffer.append(TEXT_608);
    stringBuffer.append(genFeature.getImportedType());
    stringBuffer.append(TEXT_609);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_610);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_611);
    }
    if (genModel.isVirtualDelegation() && !genFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_612);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_613);
    } else if (genClass.isESetFlag(genFeature)) {
    stringBuffer.append(TEXT_614);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_615);
    stringBuffer.append(genClass.getESetFlagsField(genFeature));
    stringBuffer.append(TEXT_616);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_617);
    } else {
    stringBuffer.append(TEXT_618);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_619);
    stringBuffer.append(genFeature.getUncapName());
    stringBuffer.append(TEXT_620);
    }
    if (genFeature.isReferenceType()) {
    stringBuffer.append(TEXT_621);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_622);
    if (!genModel.isVirtualDelegation()) {
    if (genClass.isESetFlag(genFeature)) {
    stringBuffer.append(TEXT_623);
    stringBuffer.append(genClass.getESetFlagsField(genFeature));
    stringBuffer.append(TEXT_624);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_625);
    } else {
    stringBuffer.append(TEXT_626);
    stringBuffer.append(genFeature.getUncapName());
    stringBuffer.append(TEXT_627);
    }
    }
    stringBuffer.append(TEXT_628);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.impl.ENotificationImpl"));
    stringBuffer.append(TEXT_629);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.notify.Notification"));
    stringBuffer.append(TEXT_630);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(TEXT_631);
    if (genModel.isVirtualDelegation()) {
    stringBuffer.append(TEXT_632);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_633);
    } else {
    stringBuffer.append(TEXT_634);
    stringBuffer.append(genFeature.getCapName());
    }
    stringBuffer.append(TEXT_635);
    if (genModel.isVirtualDelegation()) {
    stringBuffer.append(TEXT_636);
    } else {
    stringBuffer.append(TEXT_637);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_638);
    }
    stringBuffer.append(TEXT_639);
    } else {
    if (genClass.isFlag(genFeature)) {
    stringBuffer.append(TEXT_640);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_641);
    stringBuffer.append(genClass.getFlagsField(genFeature));
    stringBuffer.append(TEXT_642);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_643);
    stringBuffer.append(genClass.getFlagsField(genFeature));
    stringBuffer.append(TEXT_644);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_645);
    } else if (!genModel.isVirtualDelegation() || genFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_646);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_647);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_648);
    }
    if (!genModel.isVirtualDelegation() || genFeature.isPrimitiveType()) {
    if (genClass.isESetFlag(genFeature)) {
    stringBuffer.append(TEXT_649);
    stringBuffer.append(genClass.getESetFlagsField(genFeature));
    stringBuffer.append(TEXT_650);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_651);
    } else {
    stringBuffer.append(TEXT_652);
    stringBuffer.append(genFeature.getUncapName());
    stringBuffer.append(TEXT_653);
    }
    }
    stringBuffer.append(TEXT_654);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.impl.ENotificationImpl"));
    stringBuffer.append(TEXT_655);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.notify.Notification"));
    stringBuffer.append(TEXT_656);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(TEXT_657);
    if (genModel.isVirtualDelegation() && !genFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_658);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_659);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_660);
    } else {
    stringBuffer.append(TEXT_661);
    stringBuffer.append(genFeature.getCapName());
    }
    stringBuffer.append(TEXT_662);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_663);
    if (genModel.isVirtualDelegation() && !genFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_664);
    } else {
    stringBuffer.append(TEXT_665);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_666);
    }
    stringBuffer.append(TEXT_667);
    }
    }
    } else if (genFeature.hasDelegateFeature()) { GenFeature delegateFeature = genFeature.getDelegateFeature();
    if (delegateFeature.isWrappedFeatureMapType()) {
    stringBuffer.append(TEXT_668);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.FeatureMap"));
    stringBuffer.append(TEXT_669);
    stringBuffer.append(delegateFeature.getImportedEffectiveFeatureMapWrapperInternalInterface());
    stringBuffer.append(TEXT_670);
    stringBuffer.append(delegateFeature.getAccessorName());
    stringBuffer.append(TEXT_671);
    stringBuffer.append(genFeature.getQualifiedFeatureAccessorName());
    stringBuffer.append(TEXT_672);
    } else {
    stringBuffer.append(TEXT_673);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.FeatureMap"));
    stringBuffer.append(TEXT_674);
    stringBuffer.append(delegateFeature.getAccessorName());
    stringBuffer.append(TEXT_675);
    stringBuffer.append(genFeature.getQualifiedFeatureAccessorName());
    stringBuffer.append(TEXT_676);
    }
    } else {
    stringBuffer.append(TEXT_677);
    stringBuffer.append(genFeature.getFormattedName());
    stringBuffer.append(TEXT_678);
    stringBuffer.append(genFeature.getFeatureKind());
    stringBuffer.append(TEXT_679);
    //Class/unsetGenFeature.todo.override.javajetinc
    }
    stringBuffer.append(TEXT_680);
    //Class/unsetGenFeature.override.javajetinc
    }
    if (genFeature.isIsSet()) {
    stringBuffer.append(TEXT_681);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_682);
    if (genModel.isReflectiveDelegation()) {
    stringBuffer.append(TEXT_683);
    stringBuffer.append(genFeature.getGenPackage().getImportedPackageInterfaceName());
    stringBuffer.append(TEXT_684);
    stringBuffer.append(genFeature.getFeatureAccessorName());
    stringBuffer.append(TEXT_685);
    } else if (!genFeature.isVolatile()) {
    if (genFeature.isListType()) {
    if (genModel.isVirtualDelegation()) {
    stringBuffer.append(TEXT_686);
    stringBuffer.append(genFeature.getImportedType());
    stringBuffer.append(TEXT_687);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_688);
    stringBuffer.append(genFeature.getImportedType());
    stringBuffer.append(TEXT_689);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(TEXT_690);
    }
    stringBuffer.append(TEXT_691);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_692);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.InternalEList"));
    stringBuffer.append(TEXT_693);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_694);
    } else {
    if (genModel.isVirtualDelegation() && !genFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_695);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(TEXT_696);
    } else if (genClass.isESetFlag(genFeature)) {
    stringBuffer.append(TEXT_697);
    stringBuffer.append(genClass.getESetFlagsField(genFeature));
    stringBuffer.append(TEXT_698);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_699);
    } else {
    stringBuffer.append(TEXT_700);
    stringBuffer.append(genFeature.getUncapName());
    stringBuffer.append(TEXT_701);
    }
    }
    } else if (genFeature.hasDelegateFeature()) { GenFeature delegateFeature = genFeature.getDelegateFeature();
    if (delegateFeature.isWrappedFeatureMapType()) {
    stringBuffer.append(TEXT_702);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.FeatureMap"));
    stringBuffer.append(TEXT_703);
    stringBuffer.append(delegateFeature.getImportedEffectiveFeatureMapWrapperInternalInterface());
    stringBuffer.append(TEXT_704);
    stringBuffer.append(delegateFeature.getAccessorName());
    stringBuffer.append(TEXT_705);
    stringBuffer.append(genFeature.getQualifiedFeatureAccessorName());
    stringBuffer.append(TEXT_706);
    } else {
    stringBuffer.append(TEXT_707);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.FeatureMap"));
    stringBuffer.append(TEXT_708);
    stringBuffer.append(delegateFeature.getAccessorName());
    stringBuffer.append(TEXT_709);
    stringBuffer.append(genFeature.getQualifiedFeatureAccessorName());
    stringBuffer.append(TEXT_710);
    }
    } else {
    stringBuffer.append(TEXT_711);
    stringBuffer.append(genFeature.getFormattedName());
    stringBuffer.append(TEXT_712);
    stringBuffer.append(genFeature.getFeatureKind());
    stringBuffer.append(TEXT_713);
    //Class/isSetGenFeature.todo.override.javajetinc
    }
    stringBuffer.append(TEXT_714);
    //Class/isSetGenFeature.override.javajetinc
    }
    //Class/implementedGenFeature.override.javajetinc
    }//for
    for (Iterator i=genClass.getImplementedGenOperations().iterator(); i.hasNext();) { GenOperation genOperation = (GenOperation)i.next();
    stringBuffer.append(TEXT_715);
    stringBuffer.append(genOperation.getImportedType());
    stringBuffer.append(TEXT_716);
    stringBuffer.append(genOperation.getName());
    stringBuffer.append(TEXT_717);
    stringBuffer.append(genOperation.getParameters());
    stringBuffer.append(TEXT_718);
    stringBuffer.append(genOperation.getThrows());
    stringBuffer.append(TEXT_719);
    if (genOperation.hasBody()) {
    stringBuffer.append(TEXT_720);
    stringBuffer.append(genOperation.getBody(genModel.getIndentation(stringBuffer)));
    } else if (genOperation.isInvariant()) {GenClass opClass = genOperation.getGenClass(); String diagnostics = ((GenParameter)genOperation.getGenParameters().get(0)).getName(); String context = ((GenParameter)genOperation.getGenParameters().get(1)).getName();
    stringBuffer.append(TEXT_721);
    stringBuffer.append(diagnostics);
    stringBuffer.append(TEXT_722);
    stringBuffer.append(diagnostics);
    stringBuffer.append(TEXT_723);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.util.BasicDiagnostic"));
    stringBuffer.append(TEXT_724);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.util.Diagnostic"));
    stringBuffer.append(TEXT_725);
    stringBuffer.append(opClass.getGenPackage().getImportedValidatorClassName());
    stringBuffer.append(TEXT_726);
    stringBuffer.append(opClass.getGenPackage().getImportedValidatorClassName());
    stringBuffer.append(TEXT_727);
    stringBuffer.append(opClass.getOperationID(genOperation));
    stringBuffer.append(TEXT_728);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.plugin.EcorePlugin"));
    stringBuffer.append(TEXT_729);
    stringBuffer.append(genOperation.getName());
    stringBuffer.append(TEXT_730);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.EObjectValidator"));
    stringBuffer.append(TEXT_731);
    stringBuffer.append(context);
    stringBuffer.append(TEXT_732);
    stringBuffer.append(genModel.getNonNLS());
    stringBuffer.append(genModel.getNonNLS(2));
    stringBuffer.append(TEXT_733);
    } else {
    stringBuffer.append(TEXT_734);
    //Class/implementedGenOperation.todo.override.javajetinc
    }
    stringBuffer.append(TEXT_735);
    //Class/implementedGenOperation.override.javajetinc
    }//for
    if (!genModel.isReflectiveDelegation() && genClass.implementsAny(genClass.getEInverseAddGenFeatures())) {
    stringBuffer.append(TEXT_736);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.notify.NotificationChain"));
    stringBuffer.append(TEXT_737);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.InternalEObject"));
    stringBuffer.append(TEXT_738);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.notify.NotificationChain"));
    stringBuffer.append(TEXT_739);
    for (Iterator i=genClass.getEInverseAddGenFeatures().iterator(); i.hasNext();) { GenFeature genFeature = (GenFeature)i.next();
    stringBuffer.append(TEXT_740);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(TEXT_741);
    if (genFeature.isListType()) {
    if (genFeature.isMapType() && genFeature.isEffectiveSuppressEMFTypes()) {
    stringBuffer.append(TEXT_742);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.InternalEList"));
    stringBuffer.append(TEXT_743);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.util.EMap$InternalMapView"));
    stringBuffer.append(TEXT_744);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_745);
    } else {
    stringBuffer.append(TEXT_746);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.InternalEList"));
    stringBuffer.append(TEXT_747);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_748);
    }
    } else if (genFeature.isContainer()) {
    stringBuffer.append(TEXT_749);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(TEXT_750);
    } else {
    if (genClass.getImplementedGenFeatures().contains(genFeature) ? genModel.isVirtualDelegation() : genFeature.getGenModel().isVirtualDelegation()) {
    stringBuffer.append(TEXT_751);
    stringBuffer.append(genFeature.getImportedType());
    stringBuffer.append(TEXT_752);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_753);
    stringBuffer.append(genFeature.getImportedType());
    stringBuffer.append(TEXT_754);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(TEXT_755);
    }
    stringBuffer.append(TEXT_756);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_757);
    if (genFeature.isContains()) {
    stringBuffer.append(TEXT_758);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.InternalEObject"));
    stringBuffer.append(TEXT_759);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_760);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(TEXT_761);
    } else { GenFeature reverseFeature = genFeature.getReverse(); GenClass targetClass = reverseFeature.getGenClass();
    stringBuffer.append(TEXT_762);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.InternalEObject"));
    stringBuffer.append(TEXT_763);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_764);
    stringBuffer.append(targetClass.getQualifiedFeatureID(reverseFeature));
    stringBuffer.append(TEXT_765);
    stringBuffer.append(targetClass.getImportedInterfaceName());
    stringBuffer.append(TEXT_766);
    }
    stringBuffer.append(TEXT_767);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_768);
    stringBuffer.append(genFeature.getImportedType());
    stringBuffer.append(TEXT_769);
    }
    }
    stringBuffer.append(TEXT_770);
    }
    if (!genModel.isReflectiveDelegation() && genClass.implementsAny(genClass.getEInverseRemoveGenFeatures())) {
    stringBuffer.append(TEXT_771);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.notify.NotificationChain"));
    stringBuffer.append(TEXT_772);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.InternalEObject"));
    stringBuffer.append(TEXT_773);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.notify.NotificationChain"));
    stringBuffer.append(TEXT_774);
    for (Iterator i=genClass.getEInverseRemoveGenFeatures().iterator(); i.hasNext();) { GenFeature genFeature = (GenFeature)i.next();
    stringBuffer.append(TEXT_775);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(TEXT_776);
    if (genFeature.isListType()) {
    if (genFeature.isMapType() && genFeature.isEffectiveSuppressEMFTypes()) {
    stringBuffer.append(TEXT_777);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.InternalEList"));
    stringBuffer.append(TEXT_778);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.util.EMap$InternalMapView"));
    stringBuffer.append(TEXT_779);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_780);
    } else if (genFeature.isWrappedFeatureMapType()) {
    stringBuffer.append(TEXT_781);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.InternalEList"));
    stringBuffer.append(TEXT_782);
    stringBuffer.append(genFeature.getImportedEffectiveFeatureMapWrapperInternalInterface());
    stringBuffer.append(TEXT_783);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_784);
    } else {
    stringBuffer.append(TEXT_785);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.InternalEList"));
    stringBuffer.append(TEXT_786);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_787);
    }
    } else if (genFeature.isContainer()) {
    stringBuffer.append(TEXT_788);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(TEXT_789);
    } else if (genFeature.isUnsettable()) {
    stringBuffer.append(TEXT_790);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_791);
    } else {
    stringBuffer.append(TEXT_792);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_793);
    }
    }
    stringBuffer.append(TEXT_794);
    }
    if (!genModel.isReflectiveDelegation() && genClass.implementsAny(genClass.getEBasicRemoveFromContainerGenFeatures())) {
    stringBuffer.append(TEXT_795);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.notify.NotificationChain"));
    stringBuffer.append(TEXT_796);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.notify.NotificationChain"));
    stringBuffer.append(TEXT_797);
    for (Iterator i=genClass.getEBasicRemoveFromContainerGenFeatures().iterator(); i.hasNext();) { GenFeature genFeature = (GenFeature)i.next();
    GenFeature reverseFeature = genFeature.getReverse(); GenClass targetClass = reverseFeature.getGenClass();
    stringBuffer.append(TEXT_798);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(TEXT_799);
    stringBuffer.append(targetClass.getQualifiedFeatureID(reverseFeature));
    stringBuffer.append(TEXT_800);
    stringBuffer.append(targetClass.getImportedInterfaceName());
    stringBuffer.append(TEXT_801);
    }
    stringBuffer.append(TEXT_802);
    }
    if (!genModel.isReflectiveDelegation() && !genClass.getImplementedGenFeatures().isEmpty()) {
    stringBuffer.append(TEXT_803);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.EStructuralFeature"));
    stringBuffer.append(TEXT_804);
    for (Iterator i=genClass.getAllGenFeatures().iterator(); i.hasNext();) { GenFeature genFeature = (GenFeature)i.next();
    stringBuffer.append(TEXT_805);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(TEXT_806);
    if (genFeature.isPrimitiveType()) {
    if (genFeature.isBooleanType()) {
    stringBuffer.append(TEXT_807);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_808);
    } else {
    stringBuffer.append(TEXT_809);
    stringBuffer.append(genFeature.getObjectType());
    stringBuffer.append(TEXT_810);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_811);
    }
    } else if (genFeature.isResolveProxies() && !genFeature.isListType()) {
    stringBuffer.append(TEXT_812);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_813);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_814);
    } else if (genFeature.isMapType() && genFeature.isEffectiveSuppressEMFTypes()) {
    stringBuffer.append(TEXT_815);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.util.EMap$InternalMapView"));
    stringBuffer.append(TEXT_816);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_817);
    } else if (genFeature.isWrappedFeatureMapType()) {
    stringBuffer.append(TEXT_818);
    stringBuffer.append(genFeature.getImportedEffectiveFeatureMapWrapperInternalInterface());
    stringBuffer.append(TEXT_819);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_820);
    } else {
    stringBuffer.append(TEXT_821);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_822);
    }
    }
    stringBuffer.append(TEXT_823);
    }
    if (!genModel.isReflectiveDelegation() && genClass.implementsAny(genClass.getESetGenFeatures())) {
    stringBuffer.append(TEXT_824);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.EStructuralFeature"));
    stringBuffer.append(TEXT_825);
    for (Iterator i=genClass.getESetGenFeatures().iterator(); i.hasNext();) { GenFeature genFeature = (GenFeature)i.next();
    stringBuffer.append(TEXT_826);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(TEXT_827);
    if (genFeature.isListType()) {
    if (genFeature.isWrappedFeatureMapType()) {
    stringBuffer.append(TEXT_828);
    stringBuffer.append(genFeature.getImportedEffectiveFeatureMapWrapperInternalInterface());
    stringBuffer.append(TEXT_829);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_830);
    } else {
    stringBuffer.append(TEXT_831);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_832);
    }
    if (genFeature.isWrappedFeatureMapType()) {
    stringBuffer.append(TEXT_833);
    stringBuffer.append(genFeature.getImportedEffectiveFeatureMapWrapperInternalInterface());
    stringBuffer.append(TEXT_834);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_835);
    stringBuffer.append(genModel.getImportedName("java.util.Collection"));
    stringBuffer.append(TEXT_836);
    } else if (genFeature.isMapType() && genFeature.isEffectiveSuppressEMFTypes()) {
    stringBuffer.append(TEXT_837);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.util.EMap$InternalMapView"));
    stringBuffer.append(TEXT_838);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_839);
    stringBuffer.append(genModel.getImportedName("java.util.Collection"));
    stringBuffer.append(TEXT_840);
    } else {
    stringBuffer.append(TEXT_841);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_842);
    stringBuffer.append(genModel.getImportedName("java.util.Collection"));
    stringBuffer.append(TEXT_843);
    }
    } else if (genFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_844);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_845);
    stringBuffer.append(genFeature.getObjectType());
    stringBuffer.append(TEXT_846);
    stringBuffer.append(genFeature.getPrimitiveValueFunction());
    stringBuffer.append(TEXT_847);
    } else {
    stringBuffer.append(TEXT_848);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_849);
    stringBuffer.append(genFeature.getImportedType());
    stringBuffer.append(TEXT_850);
    }
    stringBuffer.append(TEXT_851);
    }
    stringBuffer.append(TEXT_852);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.EStructuralFeature"));
    stringBuffer.append(TEXT_853);
    for (Iterator i=genClass.getESetGenFeatures().iterator(); i.hasNext();) { GenFeature genFeature = (GenFeature)i.next();
    stringBuffer.append(TEXT_854);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(TEXT_855);
    if (genFeature.isListType() && !genFeature.isUnsettable()) {
    if (genFeature.isWrappedFeatureMapType()) {
    stringBuffer.append(TEXT_856);
    stringBuffer.append(genFeature.getImportedEffectiveFeatureMapWrapperInternalInterface());
    stringBuffer.append(TEXT_857);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_858);
    } else {
    stringBuffer.append(TEXT_859);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_860);
    }
    } else if (genFeature.isUnsettable()) {
    stringBuffer.append(TEXT_861);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_862);
    } else if (genFeature.isReferenceType()) {
    stringBuffer.append(TEXT_863);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_864);
    stringBuffer.append(genFeature.getImportedType());
    stringBuffer.append(TEXT_865);
    } else {
    stringBuffer.append(TEXT_866);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_867);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_868);
    }
    stringBuffer.append(TEXT_869);
    }
    stringBuffer.append(TEXT_870);
    }
    if (!genModel.isReflectiveDelegation() && !genClass.getImplementedGenFeatures().isEmpty()) {
    stringBuffer.append(TEXT_871);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.EStructuralFeature"));
    stringBuffer.append(TEXT_872);
    for (Iterator i=genClass.getAllGenFeatures().iterator(); i.hasNext();) { GenFeature genFeature = (GenFeature)i.next();
    stringBuffer.append(TEXT_873);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(TEXT_874);
    if (genFeature.isListType() && !genFeature.isUnsettable()) {
    if (genFeature.isWrappedFeatureMapType()) {
    if (genFeature.isVolatile()) {
    stringBuffer.append(TEXT_875);
    stringBuffer.append(genFeature.getImportedEffectiveFeatureMapWrapperInternalInterface());
    stringBuffer.append(TEXT_876);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_877);
    } else {
    stringBuffer.append(TEXT_878);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_879);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_880);
    }
    } else {
    if (genClass.isField(genFeature)) {
    stringBuffer.append(TEXT_881);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_882);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_883);
    } else {
    if (genFeature.isField() && (genClass.getImplementedGenFeatures().contains(genFeature) ? genModel.isVirtualDelegation() : genFeature.getGenModel().isVirtualDelegation())) {
    stringBuffer.append(TEXT_884);
    stringBuffer.append(genFeature.getImportedType());
    stringBuffer.append(TEXT_885);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_886);
    stringBuffer.append(genFeature.getImportedType());
    stringBuffer.append(TEXT_887);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(TEXT_888);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_889);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_890);
    } else {
    stringBuffer.append(TEXT_891);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_892);
    }
    }
    }
    } else if (genFeature.isUnsettable()) {
    stringBuffer.append(TEXT_893);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_894);
    } else if (genFeature.isResolveProxies()) {
    if (genClass.isField(genFeature)) {
    stringBuffer.append(TEXT_895);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_896);
    } else {
    if (genFeature.isField() && (genClass.getImplementedGenFeatures().contains(genFeature) ? genModel.isVirtualDelegation() : genFeature.getGenModel().isVirtualDelegation())) {
    stringBuffer.append(TEXT_897);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(TEXT_898);
    } else {
    stringBuffer.append(TEXT_899);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_900);
    }
    }
    } else if (genFeature.isReferenceType()) {
    if (genClass.isField(genFeature)) {
    stringBuffer.append(TEXT_901);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_902);
    } else {
    if (genFeature.isField() && (genClass.getImplementedGenFeatures().contains(genFeature) ? genModel.isVirtualDelegation() : genFeature.getGenModel().isVirtualDelegation())) {
    stringBuffer.append(TEXT_903);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(TEXT_904);
    } else {
    stringBuffer.append(TEXT_905);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_906);
    }
    }
    } else if (genFeature.isPrimitiveType() || genFeature.isEnumType()) {
    if (genClass.isField(genFeature)) {
    if (genClass.isFlag(genFeature)) {
    stringBuffer.append(TEXT_907);
    stringBuffer.append(genClass.getFlagsField(genFeature));
    stringBuffer.append(TEXT_908);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_909);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_910);
    } else {
    stringBuffer.append(TEXT_911);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_912);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_913);
    }
    } else {
    if (genFeature.isEnumType() && genFeature.isField() && (genClass.getImplementedGenFeatures().contains(genFeature) ? genModel.isVirtualDelegation() : genFeature.getGenModel().isVirtualDelegation())) {
    stringBuffer.append(TEXT_914);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(TEXT_915);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_916);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_917);
    } else {
    stringBuffer.append(TEXT_918);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_919);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_920);
    }
    }
    } else {//datatype
    if (genClass.isField(genFeature)) {
    stringBuffer.append(TEXT_921);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_922);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_923);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_924);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_925);
    } else {
    if (genFeature.isField() && (genClass.getImplementedGenFeatures().contains(genFeature) ? genModel.isVirtualDelegation() : genFeature.getGenModel().isVirtualDelegation())) {
    stringBuffer.append(TEXT_926);
    stringBuffer.append(genFeature.getImportedType());
    stringBuffer.append(TEXT_927);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_928);
    stringBuffer.append(genFeature.getImportedType());
    stringBuffer.append(TEXT_929);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(TEXT_930);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_931);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_932);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_933);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_934);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_935);
    } else {
    stringBuffer.append(TEXT_936);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_937);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_938);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_939);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_940);
    }
    }
    }
    }
    stringBuffer.append(TEXT_941);
    //Class/eIsSet.override.javajetinc
    }
    if (!genClass.getMixinGenFeatures().isEmpty()) {
    stringBuffer.append(TEXT_942);
    for (Iterator m=genClass.getMixinGenClasses().iterator(); m.hasNext();) { GenClass mixinGenClass = (GenClass)m.next(); 
    stringBuffer.append(TEXT_943);
    stringBuffer.append(mixinGenClass.getImportedInterfaceName());
    stringBuffer.append(TEXT_944);
    for (Iterator f=mixinGenClass.getGenFeatures().iterator(); f.hasNext();) { GenFeature genFeature = (GenFeature)f.next(); 
    stringBuffer.append(TEXT_945);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(TEXT_946);
    stringBuffer.append(mixinGenClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(TEXT_947);
    }
    stringBuffer.append(TEXT_948);
    }
    stringBuffer.append(TEXT_949);
    for (Iterator m=genClass.getMixinGenClasses().iterator(); m.hasNext();) { GenClass mixinGenClass = (GenClass)m.next(); 
    stringBuffer.append(TEXT_950);
    stringBuffer.append(mixinGenClass.getImportedInterfaceName());
    stringBuffer.append(TEXT_951);
    for (Iterator f=mixinGenClass.getGenFeatures().iterator(); f.hasNext();) { GenFeature genFeature = (GenFeature)f.next(); 
    stringBuffer.append(TEXT_952);
    stringBuffer.append(mixinGenClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(TEXT_953);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(TEXT_954);
    }
    stringBuffer.append(TEXT_955);
    }
    stringBuffer.append(TEXT_956);
    }
    if (genModel.isVirtualDelegation()) { String eVirtualValuesField = genClass.getEVirtualValuesField();
    if (eVirtualValuesField != null) {
    stringBuffer.append(TEXT_957);
    stringBuffer.append(eVirtualValuesField);
    stringBuffer.append(TEXT_958);
    stringBuffer.append(eVirtualValuesField);
    stringBuffer.append(TEXT_959);
    }
    { List eVirtualIndexBitFields = genClass.getEVirtualIndexBitFields(new ArrayList());
    if (!eVirtualIndexBitFields.isEmpty()) { List allEVirtualIndexBitFields = genClass.getAllEVirtualIndexBitFields(new ArrayList());
    stringBuffer.append(TEXT_960);
    for (int i = 0; i < allEVirtualIndexBitFields.size(); i++) {
    stringBuffer.append(TEXT_961);
    stringBuffer.append(i);
    stringBuffer.append(TEXT_962);
    stringBuffer.append(allEVirtualIndexBitFields.get(i));
    stringBuffer.append(TEXT_963);
    }
    stringBuffer.append(TEXT_964);
    for (int i = 0; i < allEVirtualIndexBitFields.size(); i++) {
    stringBuffer.append(TEXT_965);
    stringBuffer.append(i);
    stringBuffer.append(TEXT_966);
    stringBuffer.append(allEVirtualIndexBitFields.get(i));
    stringBuffer.append(TEXT_967);
    }
    stringBuffer.append(TEXT_968);
    }
    }
    }
    if (!genModel.isReflectiveDelegation() && !genClass.getToStringGenFeatures().isEmpty()) {
    stringBuffer.append(TEXT_969);
    { boolean first = true;
    for (Iterator i=genClass.getToStringGenFeatures().iterator(); i.hasNext(); ) { GenFeature genFeature = (GenFeature)i.next();
    if (first) { first = false;
    stringBuffer.append(TEXT_970);
    stringBuffer.append(genFeature.getName());
    stringBuffer.append(TEXT_971);
    stringBuffer.append(genModel.getNonNLS());
    } else {
    stringBuffer.append(TEXT_972);
    stringBuffer.append(genFeature.getName());
    stringBuffer.append(TEXT_973);
    stringBuffer.append(genModel.getNonNLS());
    }
    if (genFeature.isUnsettable() && !genFeature.isListType()) {
    if (genModel.isVirtualDelegation() && !genFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_974);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(TEXT_975);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(TEXT_976);
    stringBuffer.append(genModel.getNonNLS());
    } else {
    if (genClass.isFlag(genFeature)) {
    stringBuffer.append(TEXT_977);
    if (genClass.isESetFlag(genFeature)) {
    stringBuffer.append(TEXT_978);
    stringBuffer.append(genClass.getESetFlagsField(genFeature));
    stringBuffer.append(TEXT_979);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_980);
    } else {
    stringBuffer.append(genFeature.getUncapName());
    stringBuffer.append(TEXT_981);
    }
    stringBuffer.append(TEXT_982);
    stringBuffer.append(genClass.getFlagsField(genFeature));
    stringBuffer.append(TEXT_983);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_984);
    stringBuffer.append(genModel.getNonNLS());
    } else {
    stringBuffer.append(TEXT_985);
    if (genClass.isESetFlag(genFeature)) {
    stringBuffer.append(TEXT_986);
    stringBuffer.append(genClass.getESetFlagsField(genFeature));
    stringBuffer.append(TEXT_987);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_988);
    } else {
    stringBuffer.append(genFeature.getUncapName());
    stringBuffer.append(TEXT_989);
    }
    stringBuffer.append(TEXT_990);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_991);
    stringBuffer.append(genModel.getNonNLS());
    }
    }
    } else {
    if (genModel.isVirtualDelegation() && !genFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_992);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    if (!genFeature.isListType() && !genFeature.isReferenceType()){
    stringBuffer.append(TEXT_993);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_994);
    }
    stringBuffer.append(TEXT_995);
    } else {
    if (genClass.isFlag(genFeature)) {
    stringBuffer.append(TEXT_996);
    stringBuffer.append(genClass.getFlagsField(genFeature));
    stringBuffer.append(TEXT_997);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_998);
    } else {
    stringBuffer.append(TEXT_999);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_1000);
    }
    }
    }
    }
    }
    stringBuffer.append(TEXT_1001);
    }
    if (genClass.isMapEntry()) { GenFeature keyFeature = genClass.getMapEntryKeyFeature(); GenFeature valueFeature = genClass.getMapEntryValueFeature();
    stringBuffer.append(TEXT_1002);
    if (keyFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_1003);
    stringBuffer.append(keyFeature.getObjectType());
    stringBuffer.append(TEXT_1004);
    } else {
    stringBuffer.append(TEXT_1005);
    }
    stringBuffer.append(TEXT_1006);
    if (keyFeature.isListType()) {
    stringBuffer.append(TEXT_1007);
    stringBuffer.append(genModel.getImportedName("java.util.Collection"));
    stringBuffer.append(TEXT_1008);
    } else if (keyFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_1009);
    stringBuffer.append(keyFeature.getObjectType());
    stringBuffer.append(TEXT_1010);
    stringBuffer.append(keyFeature.getPrimitiveValueFunction());
    stringBuffer.append(TEXT_1011);
    } else {
    stringBuffer.append(TEXT_1012);
    stringBuffer.append(keyFeature.getImportedType());
    stringBuffer.append(TEXT_1013);
    }
    stringBuffer.append(TEXT_1014);
    if (valueFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_1015);
    stringBuffer.append(valueFeature.getObjectType());
    stringBuffer.append(TEXT_1016);
    } else {
    stringBuffer.append(TEXT_1017);
    }
    stringBuffer.append(TEXT_1018);
    if (valueFeature.isListType()) {
    stringBuffer.append(TEXT_1019);
    stringBuffer.append(genModel.getImportedName("java.util.Collection"));
    stringBuffer.append(TEXT_1020);
    } else if (valueFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_1021);
    stringBuffer.append(valueFeature.getObjectType());
    stringBuffer.append(TEXT_1022);
    stringBuffer.append(valueFeature.getPrimitiveValueFunction());
    stringBuffer.append(TEXT_1023);
    } else {
    stringBuffer.append(TEXT_1024);
    stringBuffer.append(valueFeature.getImportedType());
    stringBuffer.append(TEXT_1025);
    }
    stringBuffer.append(TEXT_1026);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.util.EMap"));
    stringBuffer.append(TEXT_1027);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.EObject"));
    stringBuffer.append(TEXT_1028);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.util.EMap"));
    stringBuffer.append(TEXT_1029);
    }
    stringBuffer.append(TEXT_1030);
    stringBuffer.append(genClass.getClassName());
    genModel.emitSortedImports();
    stringBuffer.append(TEXT_1031);
    return stringBuffer.toString();
  }
}
