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
  protected final String TEXT_83 = ";" + NL + "\t}" + NL;
  protected final String TEXT_84 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */" + NL + "\tpublic ";
  protected final String TEXT_85 = " ";
  protected final String TEXT_86 = "()" + NL + "\t{";
  protected final String TEXT_87 = NL + "\t\treturn ";
  protected final String TEXT_88 = "(";
  protected final String TEXT_89 = "(";
  protected final String TEXT_90 = ")eGet(";
  protected final String TEXT_91 = ", true)";
  protected final String TEXT_92 = ").";
  protected final String TEXT_93 = "()";
  protected final String TEXT_94 = ";";
  protected final String TEXT_95 = NL + "\t\t";
  protected final String TEXT_96 = " ";
  protected final String TEXT_97 = " = (";
  protected final String TEXT_98 = ")eVirtualGet(";
  protected final String TEXT_99 = ");";
  protected final String TEXT_100 = NL + "\t\tif (";
  protected final String TEXT_101 = " == null)" + NL + "\t\t{";
  protected final String TEXT_102 = NL + "\t\t\teVirtualSet(";
  protected final String TEXT_103 = ", ";
  protected final String TEXT_104 = " = new ";
  protected final String TEXT_105 = ");";
  protected final String TEXT_106 = NL + "\t\t\t";
  protected final String TEXT_107 = " = new ";
  protected final String TEXT_108 = ";";
  protected final String TEXT_109 = NL + "\t\t}" + NL + "\t\treturn ";
  protected final String TEXT_110 = ";";
  protected final String TEXT_111 = NL + "\t\tif (eContainerFeatureID != ";
  protected final String TEXT_112 = ") return null;" + NL + "\t\treturn (";
  protected final String TEXT_113 = ")eContainer();";
  protected final String TEXT_114 = NL + "\t\t";
  protected final String TEXT_115 = " ";
  protected final String TEXT_116 = " = (";
  protected final String TEXT_117 = ")eVirtualGet(";
  protected final String TEXT_118 = ", ";
  protected final String TEXT_119 = "_EDEFAULT";
  protected final String TEXT_120 = ");";
  protected final String TEXT_121 = NL + "\t\tif (";
  protected final String TEXT_122 = " != null && ";
  protected final String TEXT_123 = ".eIsProxy())" + NL + "\t\t{" + NL + "\t\t\t";
  protected final String TEXT_124 = " old";
  protected final String TEXT_125 = " = (";
  protected final String TEXT_126 = ")";
  protected final String TEXT_127 = ";" + NL + "\t\t\t";
  protected final String TEXT_128 = " = ";
  protected final String TEXT_129 = "eResolveProxy(old";
  protected final String TEXT_130 = ");" + NL + "\t\t\tif (";
  protected final String TEXT_131 = " != old";
  protected final String TEXT_132 = ")" + NL + "\t\t\t{";
  protected final String TEXT_133 = NL + "\t\t\t\t";
  protected final String TEXT_134 = " new";
  protected final String TEXT_135 = " = (";
  protected final String TEXT_136 = ")";
  protected final String TEXT_137 = ";";
  protected final String TEXT_138 = NL + "\t\t\t\t";
  protected final String TEXT_139 = " msgs = old";
  protected final String TEXT_140 = ".eInverseRemove(this, EOPPOSITE_FEATURE_BASE - ";
  protected final String TEXT_141 = ", null, null);";
  protected final String TEXT_142 = NL + "\t\t\t\t";
  protected final String TEXT_143 = " msgs =  old";
  protected final String TEXT_144 = ".eInverseRemove(this, ";
  protected final String TEXT_145 = ", ";
  protected final String TEXT_146 = ".class, null);";
  protected final String TEXT_147 = NL + "\t\t\t\tif (new";
  protected final String TEXT_148 = ".eInternalContainer() != null)" + NL + "\t\t\t\t{";
  protected final String TEXT_149 = NL + "\t\t\t\t\tmsgs = new";
  protected final String TEXT_150 = ".eInverseAdd(this, EOPPOSITE_FEATURE_BASE - ";
  protected final String TEXT_151 = ", null, msgs);";
  protected final String TEXT_152 = NL + "\t\t\t\t\tmsgs =  new";
  protected final String TEXT_153 = ".eInverseAdd(this, ";
  protected final String TEXT_154 = ", ";
  protected final String TEXT_155 = ".class, msgs);";
  protected final String TEXT_156 = NL + "\t\t\t\t}" + NL + "\t\t\t\tif (msgs != null) msgs.dispatch();";
  protected final String TEXT_157 = NL + "\t\t\t\teVirtualSet(";
  protected final String TEXT_158 = ", ";
  protected final String TEXT_159 = ");";
  protected final String TEXT_160 = NL + "\t\t\t\tif (eNotificationRequired())" + NL + "\t\t\t\t\teNotify(new ";
  protected final String TEXT_161 = "(this, ";
  protected final String TEXT_162 = ".RESOLVE, ";
  protected final String TEXT_163 = ", old";
  protected final String TEXT_164 = ", ";
  protected final String TEXT_165 = "));" + NL + "\t\t\t}" + NL + "\t\t}";
  protected final String TEXT_166 = NL + "\t\treturn (";
  protected final String TEXT_167 = ")eVirtualGet(";
  protected final String TEXT_168 = ", ";
  protected final String TEXT_169 = "_EDEFAULT";
  protected final String TEXT_170 = ");";
  protected final String TEXT_171 = NL + "\t\treturn (";
  protected final String TEXT_172 = " & ";
  protected final String TEXT_173 = "_EFLAG) != 0;";
  protected final String TEXT_174 = NL + "\t\treturn ";
  protected final String TEXT_175 = ";";
  protected final String TEXT_176 = NL + "\t\t";
  protected final String TEXT_177 = " ";
  protected final String TEXT_178 = " = basicGet";
  protected final String TEXT_179 = "();" + NL + "\t\treturn ";
  protected final String TEXT_180 = " != null && ";
  protected final String TEXT_181 = ".eIsProxy() ? ";
  protected final String TEXT_182 = "eResolveProxy((";
  protected final String TEXT_183 = ")";
  protected final String TEXT_184 = ") : ";
  protected final String TEXT_185 = ";";
  protected final String TEXT_186 = NL + "\t\treturn new ";
  protected final String TEXT_187 = "((";
  protected final String TEXT_188 = ".Internal)((";
  protected final String TEXT_189 = ".Internal.Wrapper)get";
  protected final String TEXT_190 = "()).featureMap().list(";
  protected final String TEXT_191 = "));";
  protected final String TEXT_192 = NL + "\t\treturn (";
  protected final String TEXT_193 = ")((";
  protected final String TEXT_194 = ")get";
  protected final String TEXT_195 = "()).list(";
  protected final String TEXT_196 = ");";
  protected final String TEXT_197 = NL + "\t\treturn ((";
  protected final String TEXT_198 = ".Internal.Wrapper)get";
  protected final String TEXT_199 = "()).featureMap().list(";
  protected final String TEXT_200 = ");";
  protected final String TEXT_201 = NL + "\t\treturn ((";
  protected final String TEXT_202 = ")get";
  protected final String TEXT_203 = "()).list(";
  protected final String TEXT_204 = ");";
  protected final String TEXT_205 = NL + "\t\treturn ";
  protected final String TEXT_206 = "(";
  protected final String TEXT_207 = "(";
  protected final String TEXT_208 = ")((";
  protected final String TEXT_209 = ".Internal.Wrapper)get";
  protected final String TEXT_210 = "()).featureMap().get(";
  protected final String TEXT_211 = ", true)";
  protected final String TEXT_212 = ").";
  protected final String TEXT_213 = "()";
  protected final String TEXT_214 = ";";
  protected final String TEXT_215 = NL + "\t\treturn ";
  protected final String TEXT_216 = "(";
  protected final String TEXT_217 = "(";
  protected final String TEXT_218 = ")get";
  protected final String TEXT_219 = "().get(";
  protected final String TEXT_220 = ", true)";
  protected final String TEXT_221 = ").";
  protected final String TEXT_222 = "()";
  protected final String TEXT_223 = ";";
  protected final String TEXT_224 = NL + "\t\t// TODO: implement this method to return the '";
  protected final String TEXT_225 = "' ";
  protected final String TEXT_226 = NL + "\t\t// Ensure that you remove @generated or mark it @generated NOT" + NL + "\t\tthrow new UnsupportedOperationException();";
  protected final String TEXT_227 = NL + "\t}" + NL;
  protected final String TEXT_228 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */" + NL + "\tpublic ";
  protected final String TEXT_229 = " basicGet";
  protected final String TEXT_230 = "()" + NL + "\t{";
  protected final String TEXT_231 = NL + "\t\tif (eContainerFeatureID != ";
  protected final String TEXT_232 = ") return null;" + NL + "\t\treturn (";
  protected final String TEXT_233 = ")eInternalContainer();";
  protected final String TEXT_234 = NL + "\t\treturn (";
  protected final String TEXT_235 = ")eVirtualGet(";
  protected final String TEXT_236 = ");";
  protected final String TEXT_237 = NL + "\t\treturn ";
  protected final String TEXT_238 = ";";
  protected final String TEXT_239 = NL + "\t\treturn (";
  protected final String TEXT_240 = ")((";
  protected final String TEXT_241 = ".Internal.Wrapper)get";
  protected final String TEXT_242 = "()).featureMap().get(";
  protected final String TEXT_243 = ", false);";
  protected final String TEXT_244 = NL + "\t\treturn (";
  protected final String TEXT_245 = ")get";
  protected final String TEXT_246 = "().get(";
  protected final String TEXT_247 = ", false);";
  protected final String TEXT_248 = NL + "\t\t// TODO: implement this method to return the '";
  protected final String TEXT_249 = "' ";
  protected final String TEXT_250 = NL + "\t\t// -> do not perform proxy resolution" + NL + "\t\t// Ensure that you remove @generated or mark it @generated NOT" + NL + "\t\tthrow new UnsupportedOperationException();";
  protected final String TEXT_251 = NL + "\t}" + NL;
  protected final String TEXT_252 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */" + NL + "\tpublic ";
  protected final String TEXT_253 = " basicSet";
  protected final String TEXT_254 = "(";
  protected final String TEXT_255 = " new";
  protected final String TEXT_256 = ", ";
  protected final String TEXT_257 = " msgs)" + NL + "\t{";
  protected final String TEXT_258 = NL + "\t\tObject old";
  protected final String TEXT_259 = " = eVirtualSet(";
  protected final String TEXT_260 = ", new";
  protected final String TEXT_261 = ");";
  protected final String TEXT_262 = NL + "\t\t";
  protected final String TEXT_263 = " old";
  protected final String TEXT_264 = " = ";
  protected final String TEXT_265 = ";" + NL + "\t\t";
  protected final String TEXT_266 = " = new";
  protected final String TEXT_267 = ";";
  protected final String TEXT_268 = NL + "\t\tboolean isSetChange = old";
  protected final String TEXT_269 = " == EVIRTUAL_NO_VALUE;";
  protected final String TEXT_270 = NL + "\t\tboolean old";
  protected final String TEXT_271 = "ESet = (";
  protected final String TEXT_272 = " & ";
  protected final String TEXT_273 = "_ESETFLAG) != 0;" + NL + "\t\t";
  protected final String TEXT_274 = " |= ";
  protected final String TEXT_275 = "_ESETFLAG;";
  protected final String TEXT_276 = NL + "\t\tboolean old";
  protected final String TEXT_277 = "ESet = ";
  protected final String TEXT_278 = "ESet;" + NL + "\t\t";
  protected final String TEXT_279 = "ESet = true;";
  protected final String TEXT_280 = NL + "\t\tif (eNotificationRequired())" + NL + "\t\t{";
  protected final String TEXT_281 = NL + "\t\t\t";
  protected final String TEXT_282 = " notification = new ";
  protected final String TEXT_283 = "(this, ";
  protected final String TEXT_284 = ".SET, ";
  protected final String TEXT_285 = ", ";
  protected final String TEXT_286 = "isSetChange ? null : old";
  protected final String TEXT_287 = "old";
  protected final String TEXT_288 = ", new";
  protected final String TEXT_289 = ", ";
  protected final String TEXT_290 = "isSetChange";
  protected final String TEXT_291 = "!old";
  protected final String TEXT_292 = "ESet";
  protected final String TEXT_293 = ");";
  protected final String TEXT_294 = NL + "\t\t\t";
  protected final String TEXT_295 = " notification = new ";
  protected final String TEXT_296 = "(this, ";
  protected final String TEXT_297 = ".SET, ";
  protected final String TEXT_298 = ", ";
  protected final String TEXT_299 = "old";
  protected final String TEXT_300 = " == EVIRTUAL_NO_VALUE ? null : old";
  protected final String TEXT_301 = "old";
  protected final String TEXT_302 = ", new";
  protected final String TEXT_303 = ");";
  protected final String TEXT_304 = NL + "\t\t\tif (msgs == null) msgs = notification; else msgs.add(notification);" + NL + "\t\t}";
  protected final String TEXT_305 = NL + "\t\treturn msgs;";
  protected final String TEXT_306 = NL + "\t\treturn ((";
  protected final String TEXT_307 = ".Internal)((";
  protected final String TEXT_308 = ".Internal.Wrapper)get";
  protected final String TEXT_309 = "()).featureMap()).basicAdd(";
  protected final String TEXT_310 = ", new";
  protected final String TEXT_311 = ", msgs);";
  protected final String TEXT_312 = NL + "\t\treturn ((";
  protected final String TEXT_313 = ".Internal)get";
  protected final String TEXT_314 = "()).basicAdd(";
  protected final String TEXT_315 = ", new";
  protected final String TEXT_316 = ", msgs);";
  protected final String TEXT_317 = NL + "\t\t// TODO: implement this method to set the contained '";
  protected final String TEXT_318 = "' ";
  protected final String TEXT_319 = NL + "\t\t// -> this method is automatically invoked to keep the containment relationship in synch" + NL + "\t\t// -> do not modify other features" + NL + "\t\t// -> return msgs, after adding any generated Notification to it (if it is null, a NotificationChain object must be created first)" + NL + "\t\t// Ensure that you remove @generated or mark it @generated NOT" + NL + "\t\tthrow new UnsupportedOperationException();";
  protected final String TEXT_320 = NL + "\t}" + NL;
  protected final String TEXT_321 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */" + NL + "\tpublic void set";
  protected final String TEXT_322 = "(";
  protected final String TEXT_323 = " new";
  protected final String TEXT_324 = ")" + NL + "\t{";
  protected final String TEXT_325 = NL + "\t\teSet(";
  protected final String TEXT_326 = ", ";
  protected final String TEXT_327 = "new ";
  protected final String TEXT_328 = "(";
  protected final String TEXT_329 = "new";
  protected final String TEXT_330 = ")";
  protected final String TEXT_331 = ");";
  protected final String TEXT_332 = NL + "\t\tif (new";
  protected final String TEXT_333 = " != eInternalContainer() || (eContainerFeatureID != ";
  protected final String TEXT_334 = " && new";
  protected final String TEXT_335 = " != null))" + NL + "\t\t{" + NL + "\t\t\tif (";
  protected final String TEXT_336 = ".isAncestor(this, ";
  protected final String TEXT_337 = "new";
  protected final String TEXT_338 = "))" + NL + "\t\t\t\tthrow new ";
  protected final String TEXT_339 = "(\"Recursive containment not allowed for \" + toString());";
  protected final String TEXT_340 = NL + "\t\t\t";
  protected final String TEXT_341 = " msgs = null;" + NL + "\t\t\tif (eInternalContainer() != null)" + NL + "\t\t\t\tmsgs = eBasicRemoveFromContainer(msgs);" + NL + "\t\t\tif (new";
  protected final String TEXT_342 = " != null)" + NL + "\t\t\t\tmsgs = ((";
  protected final String TEXT_343 = ")new";
  protected final String TEXT_344 = ").eInverseAdd(this, ";
  protected final String TEXT_345 = ", ";
  protected final String TEXT_346 = ".class, msgs);" + NL + "\t\t\tmsgs = eBasicSetContainer((";
  protected final String TEXT_347 = ")new";
  protected final String TEXT_348 = ", ";
  protected final String TEXT_349 = ", msgs);" + NL + "\t\t\tif (msgs != null) msgs.dispatch();" + NL + "\t\t}" + NL + "\t\telse if (eNotificationRequired())" + NL + "\t\t\teNotify(new ";
  protected final String TEXT_350 = "(this, ";
  protected final String TEXT_351 = ".SET, ";
  protected final String TEXT_352 = ", new";
  protected final String TEXT_353 = ", new";
  protected final String TEXT_354 = "));";
  protected final String TEXT_355 = NL + "\t\t";
  protected final String TEXT_356 = " ";
  protected final String TEXT_357 = " = (";
  protected final String TEXT_358 = ")eVirtualGet(";
  protected final String TEXT_359 = ");";
  protected final String TEXT_360 = NL + "\t\tif (new";
  protected final String TEXT_361 = " != ";
  protected final String TEXT_362 = ")" + NL + "\t\t{" + NL + "\t\t\t";
  protected final String TEXT_363 = " msgs = null;" + NL + "\t\t\tif (";
  protected final String TEXT_364 = " != null)";
  protected final String TEXT_365 = NL + "\t\t\t\tmsgs = ((";
  protected final String TEXT_366 = ")";
  protected final String TEXT_367 = ").eInverseRemove(this, EOPPOSITE_FEATURE_BASE - ";
  protected final String TEXT_368 = ", null, msgs);" + NL + "\t\t\tif (new";
  protected final String TEXT_369 = " != null)" + NL + "\t\t\t\tmsgs = ((";
  protected final String TEXT_370 = ")new";
  protected final String TEXT_371 = ").eInverseAdd(this, EOPPOSITE_FEATURE_BASE - ";
  protected final String TEXT_372 = ", null, msgs);";
  protected final String TEXT_373 = NL + "\t\t\t\tmsgs = ((";
  protected final String TEXT_374 = ")";
  protected final String TEXT_375 = ").eInverseRemove(this, ";
  protected final String TEXT_376 = ", ";
  protected final String TEXT_377 = ".class, msgs);" + NL + "\t\t\tif (new";
  protected final String TEXT_378 = " != null)" + NL + "\t\t\t\tmsgs = ((";
  protected final String TEXT_379 = ")new";
  protected final String TEXT_380 = ").eInverseAdd(this, ";
  protected final String TEXT_381 = ", ";
  protected final String TEXT_382 = ".class, msgs);";
  protected final String TEXT_383 = NL + "\t\t\tmsgs = basicSet";
  protected final String TEXT_384 = "(";
  protected final String TEXT_385 = "new";
  protected final String TEXT_386 = ", msgs);" + NL + "\t\t\tif (msgs != null) msgs.dispatch();" + NL + "\t\t}";
  protected final String TEXT_387 = NL + "\t\telse" + NL + "    \t{";
  protected final String TEXT_388 = NL + "\t\t\tboolean old";
  protected final String TEXT_389 = "ESet = eVirtualIsSet(";
  protected final String TEXT_390 = ");";
  protected final String TEXT_391 = NL + "\t\t\tboolean old";
  protected final String TEXT_392 = "ESet = (";
  protected final String TEXT_393 = " & ";
  protected final String TEXT_394 = "_ESETFLAG) != 0;" + NL + "\t\t\t";
  protected final String TEXT_395 = " |= ";
  protected final String TEXT_396 = "_ESETFLAG;";
  protected final String TEXT_397 = NL + "\t\t\tboolean old";
  protected final String TEXT_398 = "ESet = ";
  protected final String TEXT_399 = "ESet;" + NL + "\t\t\t";
  protected final String TEXT_400 = "ESet = true;";
  protected final String TEXT_401 = NL + "\t\t\tif (eNotificationRequired())" + NL + "\t\t\t\teNotify(new ";
  protected final String TEXT_402 = "(this, ";
  protected final String TEXT_403 = ".SET, ";
  protected final String TEXT_404 = ", new";
  protected final String TEXT_405 = ", new";
  protected final String TEXT_406 = ", !old";
  protected final String TEXT_407 = "ESet));" + NL + "    \t}";
  protected final String TEXT_408 = NL + "\t\telse if (eNotificationRequired())" + NL + "\t\t\teNotify(new ";
  protected final String TEXT_409 = "(this, ";
  protected final String TEXT_410 = ".SET, ";
  protected final String TEXT_411 = ", new";
  protected final String TEXT_412 = ", new";
  protected final String TEXT_413 = "));";
  protected final String TEXT_414 = NL + "\t\t";
  protected final String TEXT_415 = " old";
  protected final String TEXT_416 = " = (";
  protected final String TEXT_417 = " & ";
  protected final String TEXT_418 = "_EFLAG) != 0;" + NL + "\t\tif (new";
  protected final String TEXT_419 = ") ";
  protected final String TEXT_420 = " |= ";
  protected final String TEXT_421 = "_EFLAG; else ";
  protected final String TEXT_422 = " &= ~";
  protected final String TEXT_423 = "_EFLAG;";
  protected final String TEXT_424 = NL + "\t\t";
  protected final String TEXT_425 = " old";
  protected final String TEXT_426 = " = ";
  protected final String TEXT_427 = ";";
  protected final String TEXT_428 = NL + "\t\t";
  protected final String TEXT_429 = " ";
  protected final String TEXT_430 = " = new";
  protected final String TEXT_431 = " == null ? ";
  protected final String TEXT_432 = "_EDEFAULT : new";
  protected final String TEXT_433 = ";";
  protected final String TEXT_434 = NL + "\t\t";
  protected final String TEXT_435 = " = new";
  protected final String TEXT_436 = " == null ? ";
  protected final String TEXT_437 = "_EDEFAULT : new";
  protected final String TEXT_438 = ";";
  protected final String TEXT_439 = NL + "\t\t";
  protected final String TEXT_440 = " ";
  protected final String TEXT_441 = " = ";
  protected final String TEXT_442 = "new";
  protected final String TEXT_443 = ";";
  protected final String TEXT_444 = NL + "\t\t";
  protected final String TEXT_445 = " = ";
  protected final String TEXT_446 = "new";
  protected final String TEXT_447 = ";";
  protected final String TEXT_448 = NL + "\t\tObject old";
  protected final String TEXT_449 = " = eVirtualSet(";
  protected final String TEXT_450 = ", ";
  protected final String TEXT_451 = ");";
  protected final String TEXT_452 = NL + "\t\tboolean isSetChange = old";
  protected final String TEXT_453 = " == EVIRTUAL_NO_VALUE;";
  protected final String TEXT_454 = NL + "\t\tboolean old";
  protected final String TEXT_455 = "ESet = (";
  protected final String TEXT_456 = " & ";
  protected final String TEXT_457 = "_ESETFLAG) != 0;" + NL + "\t\t";
  protected final String TEXT_458 = " |= ";
  protected final String TEXT_459 = "_ESETFLAG;";
  protected final String TEXT_460 = NL + "\t\tboolean old";
  protected final String TEXT_461 = "ESet = ";
  protected final String TEXT_462 = "ESet;" + NL + "\t\t";
  protected final String TEXT_463 = "ESet = true;";
  protected final String TEXT_464 = NL + "\t\tif (eNotificationRequired())" + NL + "\t\t\teNotify(new ";
  protected final String TEXT_465 = "(this, ";
  protected final String TEXT_466 = ".SET, ";
  protected final String TEXT_467 = ", ";
  protected final String TEXT_468 = "isSetChange ? ";
  protected final String TEXT_469 = "null";
  protected final String TEXT_470 = "_EDEFAULT";
  protected final String TEXT_471 = " : old";
  protected final String TEXT_472 = "old";
  protected final String TEXT_473 = ", ";
  protected final String TEXT_474 = "new";
  protected final String TEXT_475 = ", ";
  protected final String TEXT_476 = "isSetChange";
  protected final String TEXT_477 = "!old";
  protected final String TEXT_478 = "ESet";
  protected final String TEXT_479 = "));";
  protected final String TEXT_480 = NL + "\t\tif (eNotificationRequired())" + NL + "\t\t\teNotify(new ";
  protected final String TEXT_481 = "(this, ";
  protected final String TEXT_482 = ".SET, ";
  protected final String TEXT_483 = ", ";
  protected final String TEXT_484 = "old";
  protected final String TEXT_485 = " == EVIRTUAL_NO_VALUE ? ";
  protected final String TEXT_486 = "null";
  protected final String TEXT_487 = "_EDEFAULT";
  protected final String TEXT_488 = " : old";
  protected final String TEXT_489 = "old";
  protected final String TEXT_490 = ", ";
  protected final String TEXT_491 = "new";
  protected final String TEXT_492 = "));";
  protected final String TEXT_493 = NL + "\t\t((";
  protected final String TEXT_494 = ".Internal)((";
  protected final String TEXT_495 = ".Internal.Wrapper)get";
  protected final String TEXT_496 = "()).featureMap()).set(";
  protected final String TEXT_497 = ", ";
  protected final String TEXT_498 = "new ";
  protected final String TEXT_499 = "(";
  protected final String TEXT_500 = "new";
  protected final String TEXT_501 = ")";
  protected final String TEXT_502 = ");";
  protected final String TEXT_503 = NL + "\t\t((";
  protected final String TEXT_504 = ".Internal)get";
  protected final String TEXT_505 = "()).set(";
  protected final String TEXT_506 = ", ";
  protected final String TEXT_507 = "new ";
  protected final String TEXT_508 = "(";
  protected final String TEXT_509 = "new";
  protected final String TEXT_510 = ")";
  protected final String TEXT_511 = ");";
  protected final String TEXT_512 = NL + "\t\t// TODO: implement this method to set the '";
  protected final String TEXT_513 = "' ";
  protected final String TEXT_514 = NL + "\t\t// Ensure that you remove @generated or mark it @generated NOT" + NL + "\t\tthrow new UnsupportedOperationException();";
  protected final String TEXT_515 = NL + "\t}" + NL;
  protected final String TEXT_516 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */" + NL + "\tpublic ";
  protected final String TEXT_517 = " basicUnset";
  protected final String TEXT_518 = "(";
  protected final String TEXT_519 = " msgs)" + NL + "\t{";
  protected final String TEXT_520 = NL + "\t\tObject old";
  protected final String TEXT_521 = " = eVirtualUnset(";
  protected final String TEXT_522 = ");";
  protected final String TEXT_523 = NL + "\t\t";
  protected final String TEXT_524 = " old";
  protected final String TEXT_525 = " = ";
  protected final String TEXT_526 = ";" + NL + "\t\t";
  protected final String TEXT_527 = " = null;";
  protected final String TEXT_528 = NL + "\t\tboolean isSetChange = old";
  protected final String TEXT_529 = " != EVIRTUAL_NO_VALUE;";
  protected final String TEXT_530 = NL + "\t\tboolean old";
  protected final String TEXT_531 = "ESet = (";
  protected final String TEXT_532 = " & ";
  protected final String TEXT_533 = "_ESETFLAG) != 0;" + NL + "\t\t";
  protected final String TEXT_534 = " &= ~";
  protected final String TEXT_535 = "_ESETFLAG;";
  protected final String TEXT_536 = NL + "\t\tboolean old";
  protected final String TEXT_537 = "ESet = ";
  protected final String TEXT_538 = "ESet;" + NL + "\t\t";
  protected final String TEXT_539 = "ESet = false;";
  protected final String TEXT_540 = NL + "\t\tif (eNotificationRequired())" + NL + "\t\t{" + NL + "\t\t\t";
  protected final String TEXT_541 = " notification = new ";
  protected final String TEXT_542 = "(this, ";
  protected final String TEXT_543 = ".UNSET, ";
  protected final String TEXT_544 = ", ";
  protected final String TEXT_545 = "isSetChange ? old";
  protected final String TEXT_546 = " : null";
  protected final String TEXT_547 = "old";
  protected final String TEXT_548 = ", null, ";
  protected final String TEXT_549 = "isSetChange";
  protected final String TEXT_550 = "old";
  protected final String TEXT_551 = "ESet";
  protected final String TEXT_552 = ");" + NL + "\t\t\tif (msgs == null) msgs = notification; else msgs.add(notification);" + NL + "\t\t}" + NL + "\t\treturn msgs;";
  protected final String TEXT_553 = NL + "\t\t// TODO: implement this method to unset the contained '";
  protected final String TEXT_554 = "' ";
  protected final String TEXT_555 = NL + "\t\t// -> this method is automatically invoked to keep the containment relationship in synch" + NL + "\t\t// -> do not modify other features" + NL + "\t\t// -> return msgs, after adding any generated Notification to it (if it is null, a NotificationChain object must be created first)" + NL + "\t\t// Ensure that you remove @generated or mark it @generated NOT" + NL + "\t\tthrow new UnsupportedOperationException();";
  protected final String TEXT_556 = NL + "\t}" + NL;
  protected final String TEXT_557 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */" + NL + "\tpublic void unset";
  protected final String TEXT_558 = "()" + NL + "\t{";
  protected final String TEXT_559 = NL + "\t\teUnset(";
  protected final String TEXT_560 = ");";
  protected final String TEXT_561 = NL + "\t\t((";
  protected final String TEXT_562 = ".Unsettable)get";
  protected final String TEXT_563 = "()).unset();";
  protected final String TEXT_564 = NL + "\t\t";
  protected final String TEXT_565 = " ";
  protected final String TEXT_566 = " = (";
  protected final String TEXT_567 = ")eVirtualGet(";
  protected final String TEXT_568 = ");";
  protected final String TEXT_569 = NL + "\t\tif (";
  protected final String TEXT_570 = " != null)" + NL + "\t\t{" + NL + "\t\t\t";
  protected final String TEXT_571 = " msgs = null;";
  protected final String TEXT_572 = NL + "\t\t\tmsgs = ((";
  protected final String TEXT_573 = ")";
  protected final String TEXT_574 = ").eInverseRemove(this, EOPPOSITE_FEATURE_BASE - ";
  protected final String TEXT_575 = ", null, msgs);";
  protected final String TEXT_576 = NL + "\t\t\tmsgs = ((";
  protected final String TEXT_577 = ")";
  protected final String TEXT_578 = ").eInverseRemove(this, ";
  protected final String TEXT_579 = ", ";
  protected final String TEXT_580 = ".class, msgs);";
  protected final String TEXT_581 = NL + "\t\t\tmsgs = basicUnset";
  protected final String TEXT_582 = "(msgs);" + NL + "\t\t\tif (msgs != null) msgs.dispatch();" + NL + "\t\t}" + NL + "\t\telse" + NL + "    \t{";
  protected final String TEXT_583 = NL + "\t\t\tboolean old";
  protected final String TEXT_584 = "ESet = eVirtualIsSet(";
  protected final String TEXT_585 = ");";
  protected final String TEXT_586 = NL + "\t\t\tboolean old";
  protected final String TEXT_587 = "ESet = (";
  protected final String TEXT_588 = " & ";
  protected final String TEXT_589 = "_ESETFLAG) != 0;" + NL + "\t\t\t";
  protected final String TEXT_590 = " &= ~";
  protected final String TEXT_591 = "_ESETFLAG;";
  protected final String TEXT_592 = NL + "\t\t\tboolean old";
  protected final String TEXT_593 = "ESet = ";
  protected final String TEXT_594 = "ESet;" + NL + "\t\t\t";
  protected final String TEXT_595 = "ESet = false;";
  protected final String TEXT_596 = NL + "\t\t\tif (eNotificationRequired())" + NL + "\t\t\t\teNotify(new ";
  protected final String TEXT_597 = "(this, ";
  protected final String TEXT_598 = ".UNSET, ";
  protected final String TEXT_599 = ", null, null, old";
  protected final String TEXT_600 = "ESet));" + NL + "    \t}";
  protected final String TEXT_601 = NL + "\t\t";
  protected final String TEXT_602 = " old";
  protected final String TEXT_603 = " = (";
  protected final String TEXT_604 = " & ";
  protected final String TEXT_605 = "_EFLAG) != 0;";
  protected final String TEXT_606 = NL + "\t\tObject old";
  protected final String TEXT_607 = " = eVirtualUnset(";
  protected final String TEXT_608 = ");";
  protected final String TEXT_609 = NL + "\t\t";
  protected final String TEXT_610 = " old";
  protected final String TEXT_611 = " = ";
  protected final String TEXT_612 = ";";
  protected final String TEXT_613 = NL + "\t\tboolean isSetChange = old";
  protected final String TEXT_614 = " != EVIRTUAL_NO_VALUE;";
  protected final String TEXT_615 = NL + "\t\tboolean old";
  protected final String TEXT_616 = "ESet = (";
  protected final String TEXT_617 = " & ";
  protected final String TEXT_618 = "_ESETFLAG) != 0;";
  protected final String TEXT_619 = NL + "\t\tboolean old";
  protected final String TEXT_620 = "ESet = ";
  protected final String TEXT_621 = "ESet;";
  protected final String TEXT_622 = NL + "\t\t";
  protected final String TEXT_623 = " = null;";
  protected final String TEXT_624 = NL + "\t\t";
  protected final String TEXT_625 = " &= ~";
  protected final String TEXT_626 = "_ESETFLAG;";
  protected final String TEXT_627 = NL + "\t\t";
  protected final String TEXT_628 = "ESet = false;";
  protected final String TEXT_629 = NL + "\t\tif (eNotificationRequired())" + NL + "\t\t\teNotify(new ";
  protected final String TEXT_630 = "(this, ";
  protected final String TEXT_631 = ".UNSET, ";
  protected final String TEXT_632 = ", ";
  protected final String TEXT_633 = "isSetChange ? old";
  protected final String TEXT_634 = " : null";
  protected final String TEXT_635 = "old";
  protected final String TEXT_636 = ", null, ";
  protected final String TEXT_637 = "isSetChange";
  protected final String TEXT_638 = "old";
  protected final String TEXT_639 = "ESet";
  protected final String TEXT_640 = "));";
  protected final String TEXT_641 = NL + "\t\tif (";
  protected final String TEXT_642 = "_EDEFAULT) ";
  protected final String TEXT_643 = " |= ";
  protected final String TEXT_644 = "_EFLAG; else ";
  protected final String TEXT_645 = " &= ~";
  protected final String TEXT_646 = "_EFLAG;";
  protected final String TEXT_647 = NL + "\t\t";
  protected final String TEXT_648 = " = ";
  protected final String TEXT_649 = "_EDEFAULT;";
  protected final String TEXT_650 = NL + "\t\t";
  protected final String TEXT_651 = " &= ~";
  protected final String TEXT_652 = "_ESETFLAG;";
  protected final String TEXT_653 = NL + "\t\t";
  protected final String TEXT_654 = "ESet = false;";
  protected final String TEXT_655 = NL + "\t\tif (eNotificationRequired())" + NL + "\t\t\teNotify(new ";
  protected final String TEXT_656 = "(this, ";
  protected final String TEXT_657 = ".UNSET, ";
  protected final String TEXT_658 = ", ";
  protected final String TEXT_659 = "isSetChange ? old";
  protected final String TEXT_660 = " : ";
  protected final String TEXT_661 = "_EDEFAULT";
  protected final String TEXT_662 = "old";
  protected final String TEXT_663 = ", ";
  protected final String TEXT_664 = "_EDEFAULT, ";
  protected final String TEXT_665 = "isSetChange";
  protected final String TEXT_666 = "old";
  protected final String TEXT_667 = "ESet";
  protected final String TEXT_668 = "));";
  protected final String TEXT_669 = NL + "\t\t((";
  protected final String TEXT_670 = ".Internal)((";
  protected final String TEXT_671 = ".Internal.Wrapper)get";
  protected final String TEXT_672 = "()).featureMap()).clear(";
  protected final String TEXT_673 = ");";
  protected final String TEXT_674 = NL + "\t\t((";
  protected final String TEXT_675 = ".Internal)get";
  protected final String TEXT_676 = "()).clear(";
  protected final String TEXT_677 = ");";
  protected final String TEXT_678 = NL + "\t\t// TODO: implement this method to unset the '";
  protected final String TEXT_679 = "' ";
  protected final String TEXT_680 = NL + "\t\t// Ensure that you remove @generated or mark it @generated NOT" + NL + "\t\tthrow new UnsupportedOperationException();";
  protected final String TEXT_681 = NL + "\t}" + NL;
  protected final String TEXT_682 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */" + NL + "\tpublic boolean isSet";
  protected final String TEXT_683 = "()" + NL + "\t{";
  protected final String TEXT_684 = NL + "\t\treturn eIsSet(";
  protected final String TEXT_685 = ");";
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
  protected final String TEXT_704 = ".Internal.Wrapper)get";
  protected final String TEXT_705 = "()).featureMap()).isEmpty(";
  protected final String TEXT_706 = ");";
  protected final String TEXT_707 = NL + "\t\treturn !((";
  protected final String TEXT_708 = ".Internal)get";
  protected final String TEXT_709 = "()).isEmpty(";
  protected final String TEXT_710 = ");";
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
  protected final String TEXT_738 = " otherEnd, int featureID, ";
  protected final String TEXT_739 = " msgs)" + NL + "\t{" + NL + "\t\tswitch (featureID)" + NL + "\t\t{";
  protected final String TEXT_740 = NL + "\t\t\tcase ";
  protected final String TEXT_741 = ":";
  protected final String TEXT_742 = NL + "\t\t\t\treturn ((";
  protected final String TEXT_743 = ")((";
  protected final String TEXT_744 = ".InternalMapView)";
  protected final String TEXT_745 = "()).eMap()).basicAdd(otherEnd, msgs);";
  protected final String TEXT_746 = NL + "\t\t\t\treturn ((";
  protected final String TEXT_747 = ")";
  protected final String TEXT_748 = "()).basicAdd(otherEnd, msgs);";
  protected final String TEXT_749 = NL + "\t\t\t\tif (eInternalContainer() != null)" + NL + "\t\t\t\t\tmsgs = eBasicRemoveFromContainer(msgs);" + NL + "\t\t\t\treturn eBasicSetContainer(otherEnd, ";
  protected final String TEXT_750 = ", msgs);";
  protected final String TEXT_751 = NL + "\t\t\t\t";
  protected final String TEXT_752 = " ";
  protected final String TEXT_753 = " = (";
  protected final String TEXT_754 = ")eVirtualGet(";
  protected final String TEXT_755 = ");";
  protected final String TEXT_756 = NL + "\t\t\t\tif (";
  protected final String TEXT_757 = " != null)";
  protected final String TEXT_758 = NL + "\t\t\t\t\tmsgs = ((";
  protected final String TEXT_759 = ")";
  protected final String TEXT_760 = ").eInverseRemove(this, EOPPOSITE_FEATURE_BASE - ";
  protected final String TEXT_761 = ", null, msgs);";
  protected final String TEXT_762 = NL + "\t\t\t\t\tmsgs = ((";
  protected final String TEXT_763 = ")";
  protected final String TEXT_764 = ").eInverseRemove(this, ";
  protected final String TEXT_765 = ", ";
  protected final String TEXT_766 = ".class, msgs);";
  protected final String TEXT_767 = NL + "\t\t\t\treturn basicSet";
  protected final String TEXT_768 = "((";
  protected final String TEXT_769 = ")otherEnd, msgs);";
  protected final String TEXT_770 = NL + "\t\t}";
  protected final String TEXT_771 = NL + "\t\treturn super.eInverseAdd(otherEnd, featureID, msgs);";
  protected final String TEXT_772 = NL + "\t\treturn eDynamicInverseAdd(otherEnd, featureID, null, msgs);";
  protected final String TEXT_773 = NL + "\t}" + NL;
  protected final String TEXT_774 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */" + NL + "\tpublic ";
  protected final String TEXT_775 = " eInverseRemove(";
  protected final String TEXT_776 = " otherEnd, int featureID, ";
  protected final String TEXT_777 = " msgs)" + NL + "\t{" + NL + "\t\tswitch (featureID)" + NL + "\t\t{";
  protected final String TEXT_778 = NL + "\t\t\tcase ";
  protected final String TEXT_779 = ":";
  protected final String TEXT_780 = NL + "\t\t\t\treturn ((";
  protected final String TEXT_781 = ")((";
  protected final String TEXT_782 = ".InternalMapView)";
  protected final String TEXT_783 = "()).eMap()).basicRemove(otherEnd, msgs);";
  protected final String TEXT_784 = NL + "\t\t\t\treturn ((";
  protected final String TEXT_785 = ")((";
  protected final String TEXT_786 = ".Internal.Wrapper)";
  protected final String TEXT_787 = "()).featureMap()).basicRemove(otherEnd, msgs);";
  protected final String TEXT_788 = NL + "\t\t\t\treturn ((";
  protected final String TEXT_789 = ")";
  protected final String TEXT_790 = "()).basicRemove(otherEnd, msgs);";
  protected final String TEXT_791 = NL + "\t\t\t\treturn eBasicSetContainer(null, ";
  protected final String TEXT_792 = ", msgs);";
  protected final String TEXT_793 = NL + "\t\t\t\treturn basicUnset";
  protected final String TEXT_794 = "(msgs);";
  protected final String TEXT_795 = NL + "\t\t\t\treturn basicSet";
  protected final String TEXT_796 = "(null, msgs);";
  protected final String TEXT_797 = NL + "\t\t}";
  protected final String TEXT_798 = NL + "\t\treturn super.eInverseRemove(otherEnd, featureID, msgs);";
  protected final String TEXT_799 = NL + "\t\treturn eDynamicInverseRemove(otherEnd, featureID, null, msgs);";
  protected final String TEXT_800 = NL + "\t}" + NL;
  protected final String TEXT_801 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */" + NL + "\tpublic ";
  protected final String TEXT_802 = " eBasicRemoveFromContainerFeature(";
  protected final String TEXT_803 = " msgs)" + NL + "\t{" + NL + "\t\tswitch (eContainerFeatureID)" + NL + "\t\t{";
  protected final String TEXT_804 = NL + "\t\t\tcase ";
  protected final String TEXT_805 = ":" + NL + "\t\t\t\treturn eInternalContainer().eInverseRemove(this, ";
  protected final String TEXT_806 = ", ";
  protected final String TEXT_807 = ".class, msgs);";
  protected final String TEXT_808 = NL + "\t\t}";
  protected final String TEXT_809 = NL + "\t\treturn super.eBasicRemoveFromContainerFeature(msgs);";
  protected final String TEXT_810 = NL + "\t\treturn eDynamicBasicRemoveFromContainer(msgs);";
  protected final String TEXT_811 = NL + "\t}" + NL;
  protected final String TEXT_812 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */" + NL + "\tpublic Object eGet(int featureID, boolean resolve, boolean coreType)" + NL + "\t{" + NL + "\t\tswitch (featureID)" + NL + "\t\t{";
  protected final String TEXT_813 = NL + "\t\t\tcase ";
  protected final String TEXT_814 = ":";
  protected final String TEXT_815 = NL + "\t\t\t\treturn ";
  protected final String TEXT_816 = "() ? Boolean.TRUE : Boolean.FALSE;";
  protected final String TEXT_817 = NL + "\t\t\t\treturn new ";
  protected final String TEXT_818 = "(";
  protected final String TEXT_819 = "());";
  protected final String TEXT_820 = NL + "\t\t\t\tif (resolve) return ";
  protected final String TEXT_821 = "();" + NL + "\t\t\t\treturn basicGet";
  protected final String TEXT_822 = "();";
  protected final String TEXT_823 = NL + "\t\t\t\tif (coreType) return ((";
  protected final String TEXT_824 = ".InternalMapView)";
  protected final String TEXT_825 = "()).eMap();" + NL + "\t\t\t\telse return ";
  protected final String TEXT_826 = "();";
  protected final String TEXT_827 = NL + "\t\t\t\tif (coreType) return ";
  protected final String TEXT_828 = "();" + NL + "\t\t\t\telse return ";
  protected final String TEXT_829 = "().map();";
  protected final String TEXT_830 = NL + "\t\t\t\tif (coreType) return ((";
  protected final String TEXT_831 = ".Internal.Wrapper)";
  protected final String TEXT_832 = "()).featureMap();" + NL + "\t\t\t\treturn ";
  protected final String TEXT_833 = "();";
  protected final String TEXT_834 = NL + "\t\t\t\tif (coreType) return ";
  protected final String TEXT_835 = "();" + NL + "\t\t\t\treturn ((";
  protected final String TEXT_836 = ".Internal)";
  protected final String TEXT_837 = "()).getWrapper();";
  protected final String TEXT_838 = NL + "\t\t\t\treturn ";
  protected final String TEXT_839 = "();";
  protected final String TEXT_840 = NL + "\t\t}";
  protected final String TEXT_841 = NL + "\t\treturn super.eGet(featureID, resolve, coreType);";
  protected final String TEXT_842 = NL + "\t\treturn eDynamicGet(featureID, resolve, coreType);";
  protected final String TEXT_843 = NL + "\t}" + NL;
  protected final String TEXT_844 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */" + NL + "\tpublic void eSet(int featureID, Object newValue)" + NL + "\t{" + NL + "\t\tswitch (featureID)" + NL + "\t\t{";
  protected final String TEXT_845 = NL + "\t\t\tcase ";
  protected final String TEXT_846 = ":";
  protected final String TEXT_847 = NL + "\t\t\t\t((";
  protected final String TEXT_848 = ".Internal)((";
  protected final String TEXT_849 = ".Internal.Wrapper)";
  protected final String TEXT_850 = "()).featureMap()).set(newValue);";
  protected final String TEXT_851 = NL + "\t\t\t\t((";
  protected final String TEXT_852 = ".Internal)";
  protected final String TEXT_853 = "()).set(newValue);";
  protected final String TEXT_854 = NL + "\t\t\t\t((";
  protected final String TEXT_855 = ".Setting)((";
  protected final String TEXT_856 = ".InternalMapView)";
  protected final String TEXT_857 = "()).eMap()).set(newValue);";
  protected final String TEXT_858 = NL + "\t\t\t\t((";
  protected final String TEXT_859 = ".Setting)";
  protected final String TEXT_860 = "()).set(newValue);";
  protected final String TEXT_861 = NL + "\t\t\t\t";
  protected final String TEXT_862 = "().clear();" + NL + "\t\t\t\t";
  protected final String TEXT_863 = "().addAll((";
  protected final String TEXT_864 = ")newValue);";
  protected final String TEXT_865 = NL + "\t\t\t\tset";
  protected final String TEXT_866 = "(((";
  protected final String TEXT_867 = ")newValue).";
  protected final String TEXT_868 = "());";
  protected final String TEXT_869 = NL + "\t\t\t\tset";
  protected final String TEXT_870 = "((";
  protected final String TEXT_871 = ")newValue);";
  protected final String TEXT_872 = NL + "\t\t\t\treturn;";
  protected final String TEXT_873 = NL + "\t\t}";
  protected final String TEXT_874 = NL + "\t\tsuper.eSet(featureID, newValue);";
  protected final String TEXT_875 = NL + "\t\teDynamicSet(featureID, newValue);";
  protected final String TEXT_876 = NL + "\t}" + NL + "" + NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */" + NL + "\tpublic void eUnset(int featureID)" + NL + "\t{" + NL + "\t\tswitch (featureID)" + NL + "\t\t{";
  protected final String TEXT_877 = NL + "\t\t\tcase ";
  protected final String TEXT_878 = ":";
  protected final String TEXT_879 = NL + "\t\t\t\t((";
  protected final String TEXT_880 = ".Internal.Wrapper)";
  protected final String TEXT_881 = "()).featureMap().clear();";
  protected final String TEXT_882 = NL + "\t\t\t\t";
  protected final String TEXT_883 = "().clear();";
  protected final String TEXT_884 = NL + "\t\t\t\tunset";
  protected final String TEXT_885 = "();";
  protected final String TEXT_886 = NL + "\t\t\t\tset";
  protected final String TEXT_887 = "((";
  protected final String TEXT_888 = ")null);";
  protected final String TEXT_889 = NL + "\t\t\t\tset";
  protected final String TEXT_890 = "(";
  protected final String TEXT_891 = "_EDEFAULT);";
  protected final String TEXT_892 = NL + "\t\t\t\treturn;";
  protected final String TEXT_893 = NL + "\t\t}";
  protected final String TEXT_894 = NL + "\t\tsuper.eUnset(featureID);";
  protected final String TEXT_895 = NL + "\t\teDynamicUnset(featureID);";
  protected final String TEXT_896 = NL + "\t}" + NL;
  protected final String TEXT_897 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */" + NL + "\tpublic boolean eIsSet(int featureID)" + NL + "\t{" + NL + "\t\tswitch (featureID)" + NL + "\t\t{";
  protected final String TEXT_898 = NL + "\t\t\tcase ";
  protected final String TEXT_899 = ":";
  protected final String TEXT_900 = NL + "\t\t\t\treturn !((";
  protected final String TEXT_901 = ".Internal.Wrapper)";
  protected final String TEXT_902 = "()).featureMap().isEmpty();";
  protected final String TEXT_903 = NL + "\t\t\t\treturn ";
  protected final String TEXT_904 = " != null && !";
  protected final String TEXT_905 = ".featureMap().isEmpty();";
  protected final String TEXT_906 = NL + "\t\t\t\treturn ";
  protected final String TEXT_907 = " != null && !";
  protected final String TEXT_908 = ".isEmpty();";
  protected final String TEXT_909 = NL + "\t\t\t\t";
  protected final String TEXT_910 = " ";
  protected final String TEXT_911 = " = (";
  protected final String TEXT_912 = ")eVirtualGet(";
  protected final String TEXT_913 = ");" + NL + "\t\t\t\treturn ";
  protected final String TEXT_914 = " != null && !";
  protected final String TEXT_915 = ".isEmpty();";
  protected final String TEXT_916 = NL + "\t\t\t\treturn !";
  protected final String TEXT_917 = "().isEmpty();";
  protected final String TEXT_918 = NL + "\t\t\t\treturn isSet";
  protected final String TEXT_919 = "();";
  protected final String TEXT_920 = NL + "\t\t\t\treturn ";
  protected final String TEXT_921 = " != null;";
  protected final String TEXT_922 = NL + "\t\t\t\treturn eVirtualGet(";
  protected final String TEXT_923 = ") != null;";
  protected final String TEXT_924 = NL + "\t\t\t\treturn basicGet";
  protected final String TEXT_925 = "() != null;";
  protected final String TEXT_926 = NL + "\t\t\t\treturn ";
  protected final String TEXT_927 = " != null;";
  protected final String TEXT_928 = NL + "\t\t\t\treturn eVirtualGet(";
  protected final String TEXT_929 = ") != null;";
  protected final String TEXT_930 = NL + "\t\t\t\treturn ";
  protected final String TEXT_931 = "() != null;";
  protected final String TEXT_932 = NL + "\t\t\t\treturn ((";
  protected final String TEXT_933 = " & ";
  protected final String TEXT_934 = "_EFLAG) != 0) != ";
  protected final String TEXT_935 = "_EDEFAULT;";
  protected final String TEXT_936 = NL + "\t\t\t\treturn ";
  protected final String TEXT_937 = " != ";
  protected final String TEXT_938 = "_EDEFAULT;";
  protected final String TEXT_939 = NL + "\t\t\t\treturn eVirtualGet(";
  protected final String TEXT_940 = ", ";
  protected final String TEXT_941 = "_EDEFAULT) != ";
  protected final String TEXT_942 = "_EDEFAULT;";
  protected final String TEXT_943 = NL + "\t\t\t\treturn ";
  protected final String TEXT_944 = "() != ";
  protected final String TEXT_945 = "_EDEFAULT;";
  protected final String TEXT_946 = NL + "\t\t\t\treturn ";
  protected final String TEXT_947 = "_EDEFAULT == null ? ";
  protected final String TEXT_948 = " != null : !";
  protected final String TEXT_949 = "_EDEFAULT.equals(";
  protected final String TEXT_950 = ");";
  protected final String TEXT_951 = NL + "\t\t\t\t";
  protected final String TEXT_952 = " ";
  protected final String TEXT_953 = " = (";
  protected final String TEXT_954 = ")eVirtualGet(";
  protected final String TEXT_955 = ", ";
  protected final String TEXT_956 = "_EDEFAULT);" + NL + "\t\t\t\treturn ";
  protected final String TEXT_957 = "_EDEFAULT == null ? ";
  protected final String TEXT_958 = " != null : !";
  protected final String TEXT_959 = "_EDEFAULT.equals(";
  protected final String TEXT_960 = ");";
  protected final String TEXT_961 = NL + "\t\t\t\treturn ";
  protected final String TEXT_962 = "_EDEFAULT == null ? ";
  protected final String TEXT_963 = "() != null : !";
  protected final String TEXT_964 = "_EDEFAULT.equals(";
  protected final String TEXT_965 = "());";
  protected final String TEXT_966 = NL + "\t\t}";
  protected final String TEXT_967 = NL + "\t\treturn super.eIsSet(featureID);";
  protected final String TEXT_968 = NL + "\t\treturn eDynamicIsSet(featureID);";
  protected final String TEXT_969 = NL + "\t}" + NL;
  protected final String TEXT_970 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */" + NL + "\tpublic int eBaseStructuralFeatureID(int derivedFeatureID, Class baseClass)" + NL + "\t{";
  protected final String TEXT_971 = NL + "\t\tif (baseClass == ";
  protected final String TEXT_972 = ".class)" + NL + "\t\t{" + NL + "\t\t\tswitch (derivedFeatureID)" + NL + "\t\t\t{";
  protected final String TEXT_973 = NL + "\t\t\t\tcase ";
  protected final String TEXT_974 = ": return ";
  protected final String TEXT_975 = ";";
  protected final String TEXT_976 = NL + "\t\t\t\tdefault: return -1;" + NL + "\t\t\t}" + NL + "\t\t}";
  protected final String TEXT_977 = NL + "\t\treturn super.eBaseStructuralFeatureID(derivedFeatureID, baseClass);" + NL + "\t}" + NL + "" + NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */" + NL + "\tpublic int eDerivedStructuralFeatureID(int baseFeatureID, Class baseClass)" + NL + "\t{";
  protected final String TEXT_978 = NL + "\t\tif (baseClass == ";
  protected final String TEXT_979 = ".class)" + NL + "\t\t{" + NL + "\t\t\tswitch (baseFeatureID)" + NL + "\t\t\t{";
  protected final String TEXT_980 = NL + "\t\t\t\tcase ";
  protected final String TEXT_981 = ": return ";
  protected final String TEXT_982 = ";";
  protected final String TEXT_983 = NL + "\t\t\t\tdefault: return -1;" + NL + "\t\t\t}" + NL + "\t\t}";
  protected final String TEXT_984 = NL + "\t\treturn super.eDerivedStructuralFeatureID(baseFeatureID, baseClass);" + NL + "\t}" + NL;
  protected final String TEXT_985 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */" + NL + "\tprotected Object[] eVirtualValues()" + NL + "\t{" + NL + "\t\treturn ";
  protected final String TEXT_986 = ";" + NL + "\t}" + NL + "" + NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */" + NL + "\tprotected void eSetVirtualValues(Object[] newValues)" + NL + "\t{" + NL + "\t\t";
  protected final String TEXT_987 = " = newValues;" + NL + "\t}" + NL;
  protected final String TEXT_988 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */" + NL + "\tprotected int eVirtualIndexBits(int offset)" + NL + "\t{" + NL + "\t\tswitch (offset)" + NL + "\t\t{";
  protected final String TEXT_989 = NL + "\t\t\tcase ";
  protected final String TEXT_990 = " :" + NL + "\t\t\t\treturn ";
  protected final String TEXT_991 = ";";
  protected final String TEXT_992 = NL + "\t\t\tdefault :" + NL + "\t\t\t\tthrow new IndexOutOfBoundsException();" + NL + "\t\t}" + NL + "\t}" + NL + "" + NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */" + NL + "\tprotected void eSetVirtualIndexBits(int offset, int newIndexBits)" + NL + "\t{" + NL + "\t\tswitch (offset)" + NL + "\t\t{";
  protected final String TEXT_993 = NL + "\t\t\tcase ";
  protected final String TEXT_994 = " :" + NL + "\t\t\t\t";
  protected final String TEXT_995 = " = newIndexBits;" + NL + "\t\t\t\tbreak;";
  protected final String TEXT_996 = NL + "\t\t\tdefault :" + NL + "\t\t\t\tthrow new IndexOutOfBoundsException();" + NL + "\t\t}" + NL + "\t}" + NL;
  protected final String TEXT_997 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */" + NL + "\tpublic String toString()" + NL + "\t{" + NL + "\t\tif (eIsProxy()) return super.toString();" + NL + "" + NL + "\t\tStringBuffer result = new StringBuffer(super.toString());";
  protected final String TEXT_998 = NL + "\t\tresult.append(\" (";
  protected final String TEXT_999 = ": \");";
  protected final String TEXT_1000 = NL + "\t\tresult.append(\", ";
  protected final String TEXT_1001 = ": \");";
  protected final String TEXT_1002 = NL + "\t\tif (eVirtualIsSet(";
  protected final String TEXT_1003 = ")) result.append(eVirtualGet(";
  protected final String TEXT_1004 = ")); else result.append(\"<unset>\");";
  protected final String TEXT_1005 = NL + "\t\tif (";
  protected final String TEXT_1006 = "(";
  protected final String TEXT_1007 = " & ";
  protected final String TEXT_1008 = "_ESETFLAG) != 0";
  protected final String TEXT_1009 = "ESet";
  protected final String TEXT_1010 = ") result.append((";
  protected final String TEXT_1011 = " & ";
  protected final String TEXT_1012 = "_EFLAG) != 0); else result.append(\"<unset>\");";
  protected final String TEXT_1013 = NL + "\t\tif (";
  protected final String TEXT_1014 = "(";
  protected final String TEXT_1015 = " & ";
  protected final String TEXT_1016 = "_ESETFLAG) != 0";
  protected final String TEXT_1017 = "ESet";
  protected final String TEXT_1018 = ") result.append(";
  protected final String TEXT_1019 = "); else result.append(\"<unset>\");";
  protected final String TEXT_1020 = NL + "\t\tresult.append(eVirtualGet(";
  protected final String TEXT_1021 = ", ";
  protected final String TEXT_1022 = "_EDEFAULT";
  protected final String TEXT_1023 = "));";
  protected final String TEXT_1024 = NL + "\t\tresult.append((";
  protected final String TEXT_1025 = " & ";
  protected final String TEXT_1026 = "_EFLAG) != 0);";
  protected final String TEXT_1027 = NL + "\t\tresult.append(";
  protected final String TEXT_1028 = ");";
  protected final String TEXT_1029 = NL + "\t\tresult.append(')');" + NL + "\t\treturn result.toString();" + NL + "\t}" + NL;
  protected final String TEXT_1030 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */" + NL + "\tprotected int hash = -1;" + NL + "" + NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */" + NL + "\tpublic int getHash()" + NL + "\t{" + NL + "\t\tif (hash == -1)" + NL + "\t\t{" + NL + "\t\t\tObject theKey = getKey();" + NL + "\t\t\thash = (theKey == null ? 0 : theKey.hashCode());" + NL + "\t\t}" + NL + "\t\treturn hash;" + NL + "\t}" + NL + "" + NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */" + NL + "\tpublic void setHash(int hash)" + NL + "\t{" + NL + "\t\tthis.hash = hash;" + NL + "\t}" + NL + "" + NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */" + NL + "\tpublic Object getKey()" + NL + "\t{";
  protected final String TEXT_1031 = NL + "\t\treturn new ";
  protected final String TEXT_1032 = "(getTypedKey());";
  protected final String TEXT_1033 = NL + "\t\treturn getTypedKey();";
  protected final String TEXT_1034 = NL + "\t}" + NL + "" + NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */" + NL + "\tpublic void setKey(Object key)" + NL + "\t{";
  protected final String TEXT_1035 = NL + "\t\tgetTypedKey().addAll((";
  protected final String TEXT_1036 = ")key);";
  protected final String TEXT_1037 = NL + "\t\tsetTypedKey(((";
  protected final String TEXT_1038 = ")key).";
  protected final String TEXT_1039 = "());";
  protected final String TEXT_1040 = NL + "\t\tsetTypedKey((";
  protected final String TEXT_1041 = ")key);";
  protected final String TEXT_1042 = NL + "\t}" + NL + "" + NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */" + NL + "\tpublic Object getValue()" + NL + "\t{";
  protected final String TEXT_1043 = NL + "\t\treturn new ";
  protected final String TEXT_1044 = "(getTypedValue());";
  protected final String TEXT_1045 = NL + "\t\treturn getTypedValue();";
  protected final String TEXT_1046 = NL + "\t}" + NL + "" + NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */" + NL + "\tpublic Object setValue(Object value)" + NL + "\t{" + NL + "\t\tObject oldValue = getValue();";
  protected final String TEXT_1047 = NL + "\t\tgetTypedValue().clear();" + NL + "\t\tgetTypedValue().addAll((";
  protected final String TEXT_1048 = ")value);";
  protected final String TEXT_1049 = NL + "\t\tsetTypedValue(((";
  protected final String TEXT_1050 = ")value).";
  protected final String TEXT_1051 = "());";
  protected final String TEXT_1052 = NL + "\t\tsetTypedValue((";
  protected final String TEXT_1053 = ")value);";
  protected final String TEXT_1054 = NL + "\t\treturn oldValue;" + NL + "\t}" + NL + "" + NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */" + NL + "\tpublic ";
  protected final String TEXT_1055 = " getEMap()" + NL + "\t{" + NL + "\t\t";
  protected final String TEXT_1056 = " container = eContainer();" + NL + "\t\treturn container == null ? null : (";
  protected final String TEXT_1057 = ")container.eGet(eContainmentFeature());" + NL + "\t}" + NL;
  protected final String TEXT_1058 = NL + "} //";
  protected final String TEXT_1059 = NL;

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
    stringBuffer.append(genClass.getQualifiedClassifierAccessor());
    stringBuffer.append(TEXT_83);
    for (Iterator i=genClass.getImplementedGenFeatures().iterator(); i.hasNext();) { GenFeature genFeature = (GenFeature)i.next();
    if (genFeature.isGet()) {
    stringBuffer.append(TEXT_84);
    stringBuffer.append(genFeature.getImportedType());
    stringBuffer.append(TEXT_85);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_86);
    if (genModel.isReflectiveDelegation()) {
    stringBuffer.append(TEXT_87);
    if (genFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_88);
    }
    stringBuffer.append(TEXT_89);
    stringBuffer.append(genFeature.getObjectType());
    stringBuffer.append(TEXT_90);
    stringBuffer.append(genFeature.getQualifiedFeatureAccessor());
    stringBuffer.append(TEXT_91);
    if (genFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_92);
    stringBuffer.append(genFeature.getPrimitiveValueFunction());
    stringBuffer.append(TEXT_93);
    }
    stringBuffer.append(TEXT_94);
    } else if (!genFeature.isVolatile()) {
    if (genFeature.isListType()) {
    if (genModel.isVirtualDelegation()) {
    stringBuffer.append(TEXT_95);
    stringBuffer.append(genFeature.getImportedType());
    stringBuffer.append(TEXT_96);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_97);
    stringBuffer.append(genFeature.getImportedType());
    stringBuffer.append(TEXT_98);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(TEXT_99);
    }
    stringBuffer.append(TEXT_100);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_101);
    if (genModel.isVirtualDelegation()) {
    stringBuffer.append(TEXT_102);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(TEXT_103);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_104);
    stringBuffer.append(genClass.getListConstructor(genFeature));
    stringBuffer.append(TEXT_105);
    } else {
    stringBuffer.append(TEXT_106);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_107);
    stringBuffer.append(genClass.getListConstructor(genFeature));
    stringBuffer.append(TEXT_108);
    }
    stringBuffer.append(TEXT_109);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(genFeature.isMapType() && genFeature.isEffectiveSuppressEMFTypes() ? ".map()" : "");
    stringBuffer.append(TEXT_110);
    } else if (genFeature.isContainer()) {
    stringBuffer.append(TEXT_111);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(TEXT_112);
    stringBuffer.append(genFeature.getImportedType());
    stringBuffer.append(TEXT_113);
    } else {
    if (genFeature.isResolveProxies()) {
    if (genModel.isVirtualDelegation()) {
    stringBuffer.append(TEXT_114);
    stringBuffer.append(genFeature.getImportedType());
    stringBuffer.append(TEXT_115);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_116);
    stringBuffer.append(genFeature.getImportedType());
    stringBuffer.append(TEXT_117);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    if (!genFeature.isReferenceType()) {
    stringBuffer.append(TEXT_118);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_119);
    }
    stringBuffer.append(TEXT_120);
    }
    stringBuffer.append(TEXT_121);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_122);
    stringBuffer.append(genFeature.getSafeNameAsEObject());
    stringBuffer.append(TEXT_123);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.InternalEObject"));
    stringBuffer.append(TEXT_124);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_125);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.InternalEObject"));
    stringBuffer.append(TEXT_126);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_127);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_128);
    stringBuffer.append(genFeature.getNonEObjectInternalTypeCast());
    stringBuffer.append(TEXT_129);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_130);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_131);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_132);
    if (genFeature.isContains()) {
    stringBuffer.append(TEXT_133);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.InternalEObject"));
    stringBuffer.append(TEXT_134);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_135);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.InternalEObject"));
    stringBuffer.append(TEXT_136);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_137);
    if (!genFeature.isBidirectional()) {
    stringBuffer.append(TEXT_138);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.notify.NotificationChain"));
    stringBuffer.append(TEXT_139);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_140);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(TEXT_141);
    } else { GenFeature reverseFeature = genFeature.getReverse(); GenClass targetClass = reverseFeature.getGenClass();
    stringBuffer.append(TEXT_142);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.notify.NotificationChain"));
    stringBuffer.append(TEXT_143);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_144);
    stringBuffer.append(targetClass.getQualifiedFeatureID(reverseFeature));
    stringBuffer.append(TEXT_145);
    stringBuffer.append(targetClass.getImportedInterfaceName());
    stringBuffer.append(TEXT_146);
    }
    stringBuffer.append(TEXT_147);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_148);
    if (!genFeature.isBidirectional()) {
    stringBuffer.append(TEXT_149);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_150);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(TEXT_151);
    } else { GenFeature reverseFeature = genFeature.getReverse(); GenClass targetClass = reverseFeature.getGenClass();
    stringBuffer.append(TEXT_152);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_153);
    stringBuffer.append(targetClass.getQualifiedFeatureID(reverseFeature));
    stringBuffer.append(TEXT_154);
    stringBuffer.append(targetClass.getImportedInterfaceName());
    stringBuffer.append(TEXT_155);
    }
    stringBuffer.append(TEXT_156);
    } else if (genModel.isVirtualDelegation()) {
    stringBuffer.append(TEXT_157);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(TEXT_158);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_159);
    }
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
    if (!genFeature.isResolveProxies() && genModel.isVirtualDelegation() && !genFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_166);
    stringBuffer.append(genFeature.getImportedType());
    stringBuffer.append(TEXT_167);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    if (!genFeature.isReferenceType()) {
    stringBuffer.append(TEXT_168);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_169);
    }
    stringBuffer.append(TEXT_170);
    } else if (genClass.isFlag(genFeature)) {
    stringBuffer.append(TEXT_171);
    stringBuffer.append(genClass.getFlagsField(genFeature));
    stringBuffer.append(TEXT_172);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_173);
    } else {
    stringBuffer.append(TEXT_174);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_175);
    }
    }
    } else {//volatile
    if (genFeature.isResolveProxies() && !genFeature.isListType()) {
    stringBuffer.append(TEXT_176);
    stringBuffer.append(genFeature.getImportedType());
    stringBuffer.append(TEXT_177);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_178);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_179);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_180);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_181);
    stringBuffer.append(genFeature.getNonEObjectInternalTypeCast());
    stringBuffer.append(TEXT_182);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.InternalEObject"));
    stringBuffer.append(TEXT_183);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_184);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_185);
    } else if (genFeature.hasDelegateFeature()) { GenFeature delegateFeature = genFeature.getDelegateFeature();
    if (genFeature.isFeatureMapType()) {
    if (delegateFeature.isWrappedFeatureMapType()) {
    stringBuffer.append(TEXT_186);
    stringBuffer.append(genFeature.getImportedEffectiveFeatureMapWrapperClass());
    stringBuffer.append(TEXT_187);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.FeatureMap"));
    stringBuffer.append(TEXT_188);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.FeatureMap"));
    stringBuffer.append(TEXT_189);
    stringBuffer.append(delegateFeature.getAccessorName());
    stringBuffer.append(TEXT_190);
    stringBuffer.append(genFeature.getQualifiedFeatureAccessor());
    stringBuffer.append(TEXT_191);
    } else {
    stringBuffer.append(TEXT_192);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.FeatureMap"));
    stringBuffer.append(TEXT_193);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.FeatureMap"));
    stringBuffer.append(TEXT_194);
    stringBuffer.append(delegateFeature.getAccessorName());
    stringBuffer.append(TEXT_195);
    stringBuffer.append(genFeature.getQualifiedFeatureAccessor());
    stringBuffer.append(TEXT_196);
    }
    } else if (genFeature.isListType()) {
    if (delegateFeature.isWrappedFeatureMapType()) {
    stringBuffer.append(TEXT_197);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.FeatureMap"));
    stringBuffer.append(TEXT_198);
    stringBuffer.append(delegateFeature.getAccessorName());
    stringBuffer.append(TEXT_199);
    stringBuffer.append(genFeature.getQualifiedFeatureAccessor());
    stringBuffer.append(TEXT_200);
    } else {
    stringBuffer.append(TEXT_201);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.FeatureMap"));
    stringBuffer.append(TEXT_202);
    stringBuffer.append(delegateFeature.getAccessorName());
    stringBuffer.append(TEXT_203);
    stringBuffer.append(genFeature.getQualifiedFeatureAccessor());
    stringBuffer.append(TEXT_204);
    }
    } else {
    if (delegateFeature.isWrappedFeatureMapType()) {
    stringBuffer.append(TEXT_205);
    if (genFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_206);
    }
    stringBuffer.append(TEXT_207);
    stringBuffer.append(genFeature.getObjectType());
    stringBuffer.append(TEXT_208);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.FeatureMap"));
    stringBuffer.append(TEXT_209);
    stringBuffer.append(delegateFeature.getAccessorName());
    stringBuffer.append(TEXT_210);
    stringBuffer.append(genFeature.getQualifiedFeatureAccessor());
    stringBuffer.append(TEXT_211);
    if (genFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_212);
    stringBuffer.append(genFeature.getPrimitiveValueFunction());
    stringBuffer.append(TEXT_213);
    }
    stringBuffer.append(TEXT_214);
    } else {
    stringBuffer.append(TEXT_215);
    if (genFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_216);
    }
    stringBuffer.append(TEXT_217);
    stringBuffer.append(genFeature.getObjectType());
    stringBuffer.append(TEXT_218);
    stringBuffer.append(delegateFeature.getAccessorName());
    stringBuffer.append(TEXT_219);
    stringBuffer.append(genFeature.getQualifiedFeatureAccessor());
    stringBuffer.append(TEXT_220);
    if (genFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_221);
    stringBuffer.append(genFeature.getPrimitiveValueFunction());
    stringBuffer.append(TEXT_222);
    }
    stringBuffer.append(TEXT_223);
    }
    }
    } else {
    stringBuffer.append(TEXT_224);
    stringBuffer.append(genFeature.getFormattedName());
    stringBuffer.append(TEXT_225);
    stringBuffer.append(genFeature.getFeatureKind());
    stringBuffer.append(TEXT_226);
    //Class/getGenFeature.todo.override.javajetinc
    }
    }
    stringBuffer.append(TEXT_227);
    //Class/getGenFeature.override.javajetinc
    }
    if (!genModel.isReflectiveDelegation() && genFeature.isBasicGet()) {
    stringBuffer.append(TEXT_228);
    stringBuffer.append(genFeature.getImportedType());
    stringBuffer.append(TEXT_229);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_230);
    if (genFeature.isContainer()) {
    stringBuffer.append(TEXT_231);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(TEXT_232);
    stringBuffer.append(genFeature.getImportedType());
    stringBuffer.append(TEXT_233);
    } else if (!genFeature.isVolatile()) {
    if (genModel.isVirtualDelegation()) {
    stringBuffer.append(TEXT_234);
    stringBuffer.append(genFeature.getImportedType());
    stringBuffer.append(TEXT_235);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(TEXT_236);
    } else {
    stringBuffer.append(TEXT_237);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_238);
    }
    } else if (genFeature.hasDelegateFeature()) { GenFeature delegateFeature = genFeature.getDelegateFeature();
    if (delegateFeature.isWrappedFeatureMapType()) {
    stringBuffer.append(TEXT_239);
    stringBuffer.append(genFeature.getImportedType());
    stringBuffer.append(TEXT_240);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.FeatureMap"));
    stringBuffer.append(TEXT_241);
    stringBuffer.append(delegateFeature.getAccessorName());
    stringBuffer.append(TEXT_242);
    stringBuffer.append(genFeature.getQualifiedFeatureAccessor());
    stringBuffer.append(TEXT_243);
    } else {
    stringBuffer.append(TEXT_244);
    stringBuffer.append(genFeature.getImportedType());
    stringBuffer.append(TEXT_245);
    stringBuffer.append(delegateFeature.getAccessorName());
    stringBuffer.append(TEXT_246);
    stringBuffer.append(genFeature.getQualifiedFeatureAccessor());
    stringBuffer.append(TEXT_247);
    }
    } else {
    stringBuffer.append(TEXT_248);
    stringBuffer.append(genFeature.getFormattedName());
    stringBuffer.append(TEXT_249);
    stringBuffer.append(genFeature.getFeatureKind());
    stringBuffer.append(TEXT_250);
    //Class/basicGetGenFeature.todo.override.javajetinc
    }
    stringBuffer.append(TEXT_251);
    //Class/basicGetGenFeature.override.javajetinc
    }
    if (!genModel.isReflectiveDelegation() && genFeature.isBasicSet()) {
    stringBuffer.append(TEXT_252);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.notify.NotificationChain"));
    stringBuffer.append(TEXT_253);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_254);
    stringBuffer.append(genFeature.getImportedInternalType());
    stringBuffer.append(TEXT_255);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_256);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.notify.NotificationChain"));
    stringBuffer.append(TEXT_257);
    if (!genFeature.isVolatile()) {
    if (genModel.isVirtualDelegation()) {
    stringBuffer.append(TEXT_258);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_259);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(TEXT_260);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_261);
    } else {
    stringBuffer.append(TEXT_262);
    stringBuffer.append(genFeature.getImportedType());
    stringBuffer.append(TEXT_263);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_264);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_265);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_266);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_267);
    }
    if (genFeature.isUnsettable()) {
    if (genModel.isVirtualDelegation()) {
    stringBuffer.append(TEXT_268);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_269);
    } else if (genClass.isESetFlag(genFeature)) {
    stringBuffer.append(TEXT_270);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_271);
    stringBuffer.append(genClass.getESetFlagsField(genFeature));
    stringBuffer.append(TEXT_272);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_273);
    stringBuffer.append(genClass.getESetFlagsField(genFeature));
    stringBuffer.append(TEXT_274);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_275);
    } else {
    stringBuffer.append(TEXT_276);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_277);
    stringBuffer.append(genFeature.getUncapName());
    stringBuffer.append(TEXT_278);
    stringBuffer.append(genFeature.getUncapName());
    stringBuffer.append(TEXT_279);
    }
    }
    stringBuffer.append(TEXT_280);
    if (genFeature.isUnsettable()) {
    stringBuffer.append(TEXT_281);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.impl.ENotificationImpl"));
    stringBuffer.append(TEXT_282);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.impl.ENotificationImpl"));
    stringBuffer.append(TEXT_283);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.notify.Notification"));
    stringBuffer.append(TEXT_284);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(TEXT_285);
    if (genModel.isVirtualDelegation()) {
    stringBuffer.append(TEXT_286);
    stringBuffer.append(genFeature.getCapName());
    } else {
    stringBuffer.append(TEXT_287);
    stringBuffer.append(genFeature.getCapName());
    }
    stringBuffer.append(TEXT_288);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_289);
    if (genModel.isVirtualDelegation()) {
    stringBuffer.append(TEXT_290);
    } else {
    stringBuffer.append(TEXT_291);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_292);
    }
    stringBuffer.append(TEXT_293);
    } else {
    stringBuffer.append(TEXT_294);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.impl.ENotificationImpl"));
    stringBuffer.append(TEXT_295);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.impl.ENotificationImpl"));
    stringBuffer.append(TEXT_296);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.notify.Notification"));
    stringBuffer.append(TEXT_297);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(TEXT_298);
    if (genModel.isVirtualDelegation()) {
    stringBuffer.append(TEXT_299);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_300);
    stringBuffer.append(genFeature.getCapName());
    } else {
    stringBuffer.append(TEXT_301);
    stringBuffer.append(genFeature.getCapName());
    }
    stringBuffer.append(TEXT_302);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_303);
    }
    stringBuffer.append(TEXT_304);
    stringBuffer.append(TEXT_305);
    } else if (genFeature.hasDelegateFeature()) { GenFeature delegateFeature = genFeature.getDelegateFeature();
    if (delegateFeature.isWrappedFeatureMapType()) {
    stringBuffer.append(TEXT_306);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.FeatureMap"));
    stringBuffer.append(TEXT_307);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.FeatureMap"));
    stringBuffer.append(TEXT_308);
    stringBuffer.append(delegateFeature.getAccessorName());
    stringBuffer.append(TEXT_309);
    stringBuffer.append(genFeature.getQualifiedFeatureAccessor());
    stringBuffer.append(TEXT_310);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_311);
    } else {
    stringBuffer.append(TEXT_312);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.FeatureMap"));
    stringBuffer.append(TEXT_313);
    stringBuffer.append(delegateFeature.getAccessorName());
    stringBuffer.append(TEXT_314);
    stringBuffer.append(genFeature.getQualifiedFeatureAccessor());
    stringBuffer.append(TEXT_315);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_316);
    }
    } else {
    stringBuffer.append(TEXT_317);
    stringBuffer.append(genFeature.getFormattedName());
    stringBuffer.append(TEXT_318);
    stringBuffer.append(genFeature.getFeatureKind());
    stringBuffer.append(TEXT_319);
    //Class/basicSetGenFeature.todo.override.javajetinc
    }
    stringBuffer.append(TEXT_320);
    //Class/basicSetGenFeature.override.javajetinc
    }
    if (genFeature.isSet()) {
    stringBuffer.append(TEXT_321);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_322);
    stringBuffer.append(genFeature.getImportedType());
    stringBuffer.append(TEXT_323);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_324);
    if (genModel.isReflectiveDelegation()) {
    stringBuffer.append(TEXT_325);
    stringBuffer.append(genFeature.getQualifiedFeatureAccessor());
    stringBuffer.append(TEXT_326);
    if (genFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_327);
    stringBuffer.append(genFeature.getObjectType());
    stringBuffer.append(TEXT_328);
    }
    stringBuffer.append(TEXT_329);
    stringBuffer.append(genFeature.getCapName());
    if (genFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_330);
    }
    stringBuffer.append(TEXT_331);
    } else if (!genFeature.isVolatile()) {
    if (genFeature.isContainer()) { GenFeature reverseFeature = genFeature.getReverse(); GenClass targetClass = reverseFeature.getGenClass();
    stringBuffer.append(TEXT_332);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_333);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(TEXT_334);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_335);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.EcoreUtil"));
    stringBuffer.append(TEXT_336);
    stringBuffer.append(genFeature.getEObjectCast());
    stringBuffer.append(TEXT_337);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_338);
    stringBuffer.append(genModel.getImportedName("java.lang.IllegalArgumentException"));
    stringBuffer.append(TEXT_339);
    stringBuffer.append(genModel.getNonNLS());
    stringBuffer.append(TEXT_340);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.notify.NotificationChain"));
    stringBuffer.append(TEXT_341);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_342);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.InternalEObject"));
    stringBuffer.append(TEXT_343);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_344);
    stringBuffer.append(targetClass.getQualifiedFeatureID(reverseFeature));
    stringBuffer.append(TEXT_345);
    stringBuffer.append(targetClass.getImportedInterfaceName());
    stringBuffer.append(TEXT_346);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.InternalEObject"));
    stringBuffer.append(TEXT_347);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_348);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
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
    } else if (genFeature.isBidirectional() || genFeature.isContains()) {
    if (genModel.isVirtualDelegation()) {
    stringBuffer.append(TEXT_355);
    stringBuffer.append(genFeature.getImportedType());
    stringBuffer.append(TEXT_356);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_357);
    stringBuffer.append(genFeature.getImportedType());
    stringBuffer.append(TEXT_358);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(TEXT_359);
    }
    stringBuffer.append(TEXT_360);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_361);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_362);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.notify.NotificationChain"));
    stringBuffer.append(TEXT_363);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_364);
    if (!genFeature.isBidirectional()) {
    stringBuffer.append(TEXT_365);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.InternalEObject"));
    stringBuffer.append(TEXT_366);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_367);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(TEXT_368);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_369);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.InternalEObject"));
    stringBuffer.append(TEXT_370);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_371);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(TEXT_372);
    } else { GenFeature reverseFeature = genFeature.getReverse(); GenClass targetClass = reverseFeature.getGenClass();
    stringBuffer.append(TEXT_373);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.InternalEObject"));
    stringBuffer.append(TEXT_374);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_375);
    stringBuffer.append(targetClass.getQualifiedFeatureID(reverseFeature));
    stringBuffer.append(TEXT_376);
    stringBuffer.append(targetClass.getImportedInterfaceName());
    stringBuffer.append(TEXT_377);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_378);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.InternalEObject"));
    stringBuffer.append(TEXT_379);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_380);
    stringBuffer.append(targetClass.getQualifiedFeatureID(reverseFeature));
    stringBuffer.append(TEXT_381);
    stringBuffer.append(targetClass.getImportedInterfaceName());
    stringBuffer.append(TEXT_382);
    }
    stringBuffer.append(TEXT_383);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_384);
    stringBuffer.append(genFeature.getInternalTypeCast());
    stringBuffer.append(TEXT_385);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_386);
    if (genFeature.isUnsettable()) {
    stringBuffer.append(TEXT_387);
    if (genModel.isVirtualDelegation()) {
    stringBuffer.append(TEXT_388);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_389);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(TEXT_390);
    } else if (genClass.isESetFlag(genFeature)) {
    stringBuffer.append(TEXT_391);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_392);
    stringBuffer.append(genClass.getESetFlagsField(genFeature));
    stringBuffer.append(TEXT_393);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_394);
    stringBuffer.append(genClass.getESetFlagsField(genFeature));
    stringBuffer.append(TEXT_395);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_396);
    } else {
    stringBuffer.append(TEXT_397);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_398);
    stringBuffer.append(genFeature.getUncapName());
    stringBuffer.append(TEXT_399);
    stringBuffer.append(genFeature.getUncapName());
    stringBuffer.append(TEXT_400);
    }
    stringBuffer.append(TEXT_401);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.impl.ENotificationImpl"));
    stringBuffer.append(TEXT_402);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.notify.Notification"));
    stringBuffer.append(TEXT_403);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(TEXT_404);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_405);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_406);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_407);
    } else {
    stringBuffer.append(TEXT_408);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.impl.ENotificationImpl"));
    stringBuffer.append(TEXT_409);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.notify.Notification"));
    stringBuffer.append(TEXT_410);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(TEXT_411);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_412);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_413);
    }
    } else {
    if (genClass.isFlag(genFeature)) {
    stringBuffer.append(TEXT_414);
    stringBuffer.append(genFeature.getImportedType());
    stringBuffer.append(TEXT_415);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_416);
    stringBuffer.append(genClass.getFlagsField(genFeature));
    stringBuffer.append(TEXT_417);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_418);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_419);
    stringBuffer.append(genClass.getFlagsField(genFeature));
    stringBuffer.append(TEXT_420);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_421);
    stringBuffer.append(genClass.getFlagsField(genFeature));
    stringBuffer.append(TEXT_422);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_423);
    } else {
    if (!genModel.isVirtualDelegation() || genFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_424);
    stringBuffer.append(genFeature.getImportedType());
    stringBuffer.append(TEXT_425);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_426);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_427);
    }
    if (genFeature.isEnumType()) {
    if (genModel.isVirtualDelegation()) {
    stringBuffer.append(TEXT_428);
    stringBuffer.append(genFeature.getImportedType());
    stringBuffer.append(TEXT_429);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_430);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_431);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_432);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_433);
    } else {
    stringBuffer.append(TEXT_434);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_435);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_436);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_437);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_438);
    }
    } else {
    if (genModel.isVirtualDelegation() && !genFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_439);
    stringBuffer.append(genFeature.getImportedType());
    stringBuffer.append(TEXT_440);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_441);
    stringBuffer.append(genFeature.getInternalTypeCast());
    stringBuffer.append(TEXT_442);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_443);
    } else {
    stringBuffer.append(TEXT_444);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_445);
    stringBuffer.append(genFeature.getInternalTypeCast());
    stringBuffer.append(TEXT_446);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_447);
    }
    }
    if (genModel.isVirtualDelegation() && !genFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_448);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_449);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(TEXT_450);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_451);
    }
    }
    if (genFeature.isUnsettable()) {
    if (genModel.isVirtualDelegation() && !genFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_452);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_453);
    } else if (genClass.isESetFlag(genFeature)) {
    stringBuffer.append(TEXT_454);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_455);
    stringBuffer.append(genClass.getESetFlagsField(genFeature));
    stringBuffer.append(TEXT_456);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_457);
    stringBuffer.append(genClass.getESetFlagsField(genFeature));
    stringBuffer.append(TEXT_458);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_459);
    } else {
    stringBuffer.append(TEXT_460);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_461);
    stringBuffer.append(genFeature.getUncapName());
    stringBuffer.append(TEXT_462);
    stringBuffer.append(genFeature.getUncapName());
    stringBuffer.append(TEXT_463);
    }
    stringBuffer.append(TEXT_464);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.impl.ENotificationImpl"));
    stringBuffer.append(TEXT_465);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.notify.Notification"));
    stringBuffer.append(TEXT_466);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(TEXT_467);
    if (genModel.isVirtualDelegation() && !genFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_468);
    if (genFeature.isReferenceType()) {
    stringBuffer.append(TEXT_469);
    } else {
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_470);
    }
    stringBuffer.append(TEXT_471);
    stringBuffer.append(genFeature.getCapName());
    } else {
    stringBuffer.append(TEXT_472);
    stringBuffer.append(genFeature.getCapName());
    }
    stringBuffer.append(TEXT_473);
    if (genClass.isFlag(genFeature)) {
    stringBuffer.append(TEXT_474);
    stringBuffer.append(genFeature.getCapName());
    } else {
    stringBuffer.append(genFeature.getSafeName());
    }
    stringBuffer.append(TEXT_475);
    if (genModel.isVirtualDelegation() && !genFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_476);
    } else {
    stringBuffer.append(TEXT_477);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_478);
    }
    stringBuffer.append(TEXT_479);
    } else {
    stringBuffer.append(TEXT_480);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.impl.ENotificationImpl"));
    stringBuffer.append(TEXT_481);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.notify.Notification"));
    stringBuffer.append(TEXT_482);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(TEXT_483);
    if (genModel.isVirtualDelegation() && !genFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_484);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_485);
    if (genFeature.isReferenceType()) {
    stringBuffer.append(TEXT_486);
    } else {
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_487);
    }
    stringBuffer.append(TEXT_488);
    stringBuffer.append(genFeature.getCapName());
    } else {
    stringBuffer.append(TEXT_489);
    stringBuffer.append(genFeature.getCapName());
    }
    stringBuffer.append(TEXT_490);
    if (genClass.isFlag(genFeature)) {
    stringBuffer.append(TEXT_491);
    stringBuffer.append(genFeature.getCapName());
    } else {
    stringBuffer.append(genFeature.getSafeName());
    }
    stringBuffer.append(TEXT_492);
    }
    }
    } else if (genFeature.hasDelegateFeature()) { GenFeature delegateFeature = genFeature.getDelegateFeature();
    if (delegateFeature.isWrappedFeatureMapType()) {
    stringBuffer.append(TEXT_493);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.FeatureMap"));
    stringBuffer.append(TEXT_494);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.FeatureMap"));
    stringBuffer.append(TEXT_495);
    stringBuffer.append(delegateFeature.getAccessorName());
    stringBuffer.append(TEXT_496);
    stringBuffer.append(genFeature.getQualifiedFeatureAccessor());
    stringBuffer.append(TEXT_497);
    if (genFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_498);
    stringBuffer.append(genFeature.getObjectType());
    stringBuffer.append(TEXT_499);
    }
    stringBuffer.append(TEXT_500);
    stringBuffer.append(genFeature.getCapName());
    if (genFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_501);
    }
    stringBuffer.append(TEXT_502);
    } else {
    stringBuffer.append(TEXT_503);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.FeatureMap"));
    stringBuffer.append(TEXT_504);
    stringBuffer.append(delegateFeature.getAccessorName());
    stringBuffer.append(TEXT_505);
    stringBuffer.append(genFeature.getQualifiedFeatureAccessor());
    stringBuffer.append(TEXT_506);
    if (genFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_507);
    stringBuffer.append(genFeature.getObjectType());
    stringBuffer.append(TEXT_508);
    }
    stringBuffer.append(TEXT_509);
    stringBuffer.append(genFeature.getCapName());
    if (genFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_510);
    }
    stringBuffer.append(TEXT_511);
    }
    } else {
    stringBuffer.append(TEXT_512);
    stringBuffer.append(genFeature.getFormattedName());
    stringBuffer.append(TEXT_513);
    stringBuffer.append(genFeature.getFeatureKind());
    stringBuffer.append(TEXT_514);
    //Class/setGenFeature.todo.override.javajetinc
    }
    stringBuffer.append(TEXT_515);
    //Class/setGenFeature.override.javajetinc
    }
    if (!genModel.isReflectiveDelegation() && genFeature.isBasicUnset()) {
    stringBuffer.append(TEXT_516);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.notify.NotificationChain"));
    stringBuffer.append(TEXT_517);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_518);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.notify.NotificationChain"));
    stringBuffer.append(TEXT_519);
    if (!genFeature.isVolatile()) {
    if (genModel.isVirtualDelegation()) {
    stringBuffer.append(TEXT_520);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_521);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(TEXT_522);
    } else {
    stringBuffer.append(TEXT_523);
    stringBuffer.append(genFeature.getImportedType());
    stringBuffer.append(TEXT_524);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_525);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_526);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_527);
    }
    if (genModel.isVirtualDelegation()) {
    stringBuffer.append(TEXT_528);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_529);
    } else if (genClass.isESetFlag(genFeature)) {
    stringBuffer.append(TEXT_530);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_531);
    stringBuffer.append(genClass.getESetFlagsField(genFeature));
    stringBuffer.append(TEXT_532);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_533);
    stringBuffer.append(genClass.getESetFlagsField(genFeature));
    stringBuffer.append(TEXT_534);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_535);
    } else {
    stringBuffer.append(TEXT_536);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_537);
    stringBuffer.append(genFeature.getUncapName());
    stringBuffer.append(TEXT_538);
    stringBuffer.append(genFeature.getUncapName());
    stringBuffer.append(TEXT_539);
    }
    stringBuffer.append(TEXT_540);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.impl.ENotificationImpl"));
    stringBuffer.append(TEXT_541);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.impl.ENotificationImpl"));
    stringBuffer.append(TEXT_542);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.notify.Notification"));
    stringBuffer.append(TEXT_543);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(TEXT_544);
    if (genModel.isVirtualDelegation()) {
    stringBuffer.append(TEXT_545);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_546);
    } else {
    stringBuffer.append(TEXT_547);
    stringBuffer.append(genFeature.getCapName());
    }
    stringBuffer.append(TEXT_548);
    if (genModel.isVirtualDelegation()) {
    stringBuffer.append(TEXT_549);
    } else {
    stringBuffer.append(TEXT_550);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_551);
    }
    stringBuffer.append(TEXT_552);
    } else {
    stringBuffer.append(TEXT_553);
    stringBuffer.append(genFeature.getFormattedName());
    stringBuffer.append(TEXT_554);
    stringBuffer.append(genFeature.getFeatureKind());
    stringBuffer.append(TEXT_555);
    //Class/basicUnsetGenFeature.todo.override.javajetinc
    }
    stringBuffer.append(TEXT_556);
    //Class.basicUnsetGenFeature.override.javajetinc
    }
    if (genFeature.isUnset()) {
    stringBuffer.append(TEXT_557);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_558);
    if (genModel.isReflectiveDelegation()) {
    stringBuffer.append(TEXT_559);
    stringBuffer.append(genFeature.getQualifiedFeatureAccessor());
    stringBuffer.append(TEXT_560);
    } else if (!genFeature.isVolatile()) {
    if (genFeature.isListType()) {
    stringBuffer.append(TEXT_561);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.InternalEList"));
    stringBuffer.append(TEXT_562);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_563);
    } else if (genFeature.isBidirectional() || genFeature.isContains()) {
    if (genModel.isVirtualDelegation()) {
    stringBuffer.append(TEXT_564);
    stringBuffer.append(genFeature.getImportedType());
    stringBuffer.append(TEXT_565);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_566);
    stringBuffer.append(genFeature.getImportedType());
    stringBuffer.append(TEXT_567);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(TEXT_568);
    }
    stringBuffer.append(TEXT_569);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_570);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.notify.NotificationChain"));
    stringBuffer.append(TEXT_571);
    if (!genFeature.isBidirectional()) {
    stringBuffer.append(TEXT_572);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.InternalEObject"));
    stringBuffer.append(TEXT_573);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_574);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(TEXT_575);
    } else { GenFeature reverseFeature = genFeature.getReverse(); GenClass targetClass = reverseFeature.getGenClass();
    stringBuffer.append(TEXT_576);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.InternalEObject"));
    stringBuffer.append(TEXT_577);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_578);
    stringBuffer.append(targetClass.getQualifiedFeatureID(reverseFeature));
    stringBuffer.append(TEXT_579);
    stringBuffer.append(targetClass.getImportedInterfaceName());
    stringBuffer.append(TEXT_580);
    }
    stringBuffer.append(TEXT_581);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_582);
    if (genModel.isVirtualDelegation()) {
    stringBuffer.append(TEXT_583);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_584);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(TEXT_585);
    } else if (genClass.isESetFlag(genFeature)) {
    stringBuffer.append(TEXT_586);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_587);
    stringBuffer.append(genClass.getESetFlagsField(genFeature));
    stringBuffer.append(TEXT_588);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_589);
    stringBuffer.append(genClass.getESetFlagsField(genFeature));
    stringBuffer.append(TEXT_590);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_591);
    } else {
    stringBuffer.append(TEXT_592);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_593);
    stringBuffer.append(genFeature.getUncapName());
    stringBuffer.append(TEXT_594);
    stringBuffer.append(genFeature.getUncapName());
    stringBuffer.append(TEXT_595);
    }
    stringBuffer.append(TEXT_596);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.impl.ENotificationImpl"));
    stringBuffer.append(TEXT_597);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.notify.Notification"));
    stringBuffer.append(TEXT_598);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(TEXT_599);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_600);
    } else {
    if (genClass.isFlag(genFeature)) {
    stringBuffer.append(TEXT_601);
    stringBuffer.append(genFeature.getImportedType());
    stringBuffer.append(TEXT_602);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_603);
    stringBuffer.append(genClass.getFlagsField(genFeature));
    stringBuffer.append(TEXT_604);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_605);
    } else if (genModel.isVirtualDelegation() && !genFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_606);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_607);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(TEXT_608);
    } else {
    stringBuffer.append(TEXT_609);
    stringBuffer.append(genFeature.getImportedType());
    stringBuffer.append(TEXT_610);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_611);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_612);
    }
    if (genModel.isVirtualDelegation() && !genFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_613);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_614);
    } else if (genClass.isESetFlag(genFeature)) {
    stringBuffer.append(TEXT_615);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_616);
    stringBuffer.append(genClass.getESetFlagsField(genFeature));
    stringBuffer.append(TEXT_617);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_618);
    } else {
    stringBuffer.append(TEXT_619);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_620);
    stringBuffer.append(genFeature.getUncapName());
    stringBuffer.append(TEXT_621);
    }
    if (genFeature.isReferenceType()) {
    stringBuffer.append(TEXT_622);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_623);
    if (!genModel.isVirtualDelegation()) {
    if (genClass.isESetFlag(genFeature)) {
    stringBuffer.append(TEXT_624);
    stringBuffer.append(genClass.getESetFlagsField(genFeature));
    stringBuffer.append(TEXT_625);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_626);
    } else {
    stringBuffer.append(TEXT_627);
    stringBuffer.append(genFeature.getUncapName());
    stringBuffer.append(TEXT_628);
    }
    }
    stringBuffer.append(TEXT_629);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.impl.ENotificationImpl"));
    stringBuffer.append(TEXT_630);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.notify.Notification"));
    stringBuffer.append(TEXT_631);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(TEXT_632);
    if (genModel.isVirtualDelegation()) {
    stringBuffer.append(TEXT_633);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_634);
    } else {
    stringBuffer.append(TEXT_635);
    stringBuffer.append(genFeature.getCapName());
    }
    stringBuffer.append(TEXT_636);
    if (genModel.isVirtualDelegation()) {
    stringBuffer.append(TEXT_637);
    } else {
    stringBuffer.append(TEXT_638);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_639);
    }
    stringBuffer.append(TEXT_640);
    } else {
    if (genClass.isFlag(genFeature)) {
    stringBuffer.append(TEXT_641);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_642);
    stringBuffer.append(genClass.getFlagsField(genFeature));
    stringBuffer.append(TEXT_643);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_644);
    stringBuffer.append(genClass.getFlagsField(genFeature));
    stringBuffer.append(TEXT_645);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_646);
    } else if (!genModel.isVirtualDelegation() || genFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_647);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_648);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_649);
    }
    if (!genModel.isVirtualDelegation() || genFeature.isPrimitiveType()) {
    if (genClass.isESetFlag(genFeature)) {
    stringBuffer.append(TEXT_650);
    stringBuffer.append(genClass.getESetFlagsField(genFeature));
    stringBuffer.append(TEXT_651);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_652);
    } else {
    stringBuffer.append(TEXT_653);
    stringBuffer.append(genFeature.getUncapName());
    stringBuffer.append(TEXT_654);
    }
    }
    stringBuffer.append(TEXT_655);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.impl.ENotificationImpl"));
    stringBuffer.append(TEXT_656);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.notify.Notification"));
    stringBuffer.append(TEXT_657);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(TEXT_658);
    if (genModel.isVirtualDelegation() && !genFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_659);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_660);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_661);
    } else {
    stringBuffer.append(TEXT_662);
    stringBuffer.append(genFeature.getCapName());
    }
    stringBuffer.append(TEXT_663);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_664);
    if (genModel.isVirtualDelegation() && !genFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_665);
    } else {
    stringBuffer.append(TEXT_666);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_667);
    }
    stringBuffer.append(TEXT_668);
    }
    }
    } else if (genFeature.hasDelegateFeature()) { GenFeature delegateFeature = genFeature.getDelegateFeature();
    if (delegateFeature.isWrappedFeatureMapType()) {
    stringBuffer.append(TEXT_669);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.FeatureMap"));
    stringBuffer.append(TEXT_670);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.FeatureMap"));
    stringBuffer.append(TEXT_671);
    stringBuffer.append(delegateFeature.getAccessorName());
    stringBuffer.append(TEXT_672);
    stringBuffer.append(genFeature.getQualifiedFeatureAccessor());
    stringBuffer.append(TEXT_673);
    } else {
    stringBuffer.append(TEXT_674);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.FeatureMap"));
    stringBuffer.append(TEXT_675);
    stringBuffer.append(delegateFeature.getAccessorName());
    stringBuffer.append(TEXT_676);
    stringBuffer.append(genFeature.getQualifiedFeatureAccessor());
    stringBuffer.append(TEXT_677);
    }
    } else {
    stringBuffer.append(TEXT_678);
    stringBuffer.append(genFeature.getFormattedName());
    stringBuffer.append(TEXT_679);
    stringBuffer.append(genFeature.getFeatureKind());
    stringBuffer.append(TEXT_680);
    //Class/unsetGenFeature.todo.override.javajetinc
    }
    stringBuffer.append(TEXT_681);
    //Class/unsetGenFeature.override.javajetinc
    }
    if (genFeature.isIsSet()) {
    stringBuffer.append(TEXT_682);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_683);
    if (genModel.isReflectiveDelegation()) {
    stringBuffer.append(TEXT_684);
    stringBuffer.append(genFeature.getQualifiedFeatureAccessor());
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
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.FeatureMap"));
    stringBuffer.append(TEXT_704);
    stringBuffer.append(delegateFeature.getAccessorName());
    stringBuffer.append(TEXT_705);
    stringBuffer.append(genFeature.getQualifiedFeatureAccessor());
    stringBuffer.append(TEXT_706);
    } else {
    stringBuffer.append(TEXT_707);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.FeatureMap"));
    stringBuffer.append(TEXT_708);
    stringBuffer.append(delegateFeature.getAccessorName());
    stringBuffer.append(TEXT_709);
    stringBuffer.append(genFeature.getQualifiedFeatureAccessor());
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
    if (!genModel.isMinimalReflectiveMethods() || genClass.getImplementedGenFeatures().contains(genFeature)) {
    stringBuffer.append(TEXT_740);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(TEXT_741);
    if (genFeature.isListType()) {
    if (genFeature.isMapType() && genFeature.isEffectiveSuppressEMFTypes()) {
    stringBuffer.append(TEXT_742);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.InternalEList"));
    stringBuffer.append(TEXT_743);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.util.EMap"));
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
    }
    stringBuffer.append(TEXT_770);
    if (genModel.isMinimalReflectiveMethods()) {
    stringBuffer.append(TEXT_771);
    } else {
    stringBuffer.append(TEXT_772);
    }
    stringBuffer.append(TEXT_773);
    }
    if (!genModel.isReflectiveDelegation() && genClass.implementsAny(genClass.getEInverseRemoveGenFeatures())) {
    stringBuffer.append(TEXT_774);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.notify.NotificationChain"));
    stringBuffer.append(TEXT_775);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.InternalEObject"));
    stringBuffer.append(TEXT_776);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.notify.NotificationChain"));
    stringBuffer.append(TEXT_777);
    for (Iterator i=genClass.getEInverseRemoveGenFeatures().iterator(); i.hasNext();) { GenFeature genFeature = (GenFeature)i.next();
    if (!genModel.isMinimalReflectiveMethods() || genClass.getImplementedGenFeatures().contains(genFeature)) {
    stringBuffer.append(TEXT_778);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(TEXT_779);
    if (genFeature.isListType()) {
    if (genFeature.isMapType() && genFeature.isEffectiveSuppressEMFTypes()) {
    stringBuffer.append(TEXT_780);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.InternalEList"));
    stringBuffer.append(TEXT_781);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.util.EMap"));
    stringBuffer.append(TEXT_782);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_783);
    } else if (genFeature.isWrappedFeatureMapType()) {
    stringBuffer.append(TEXT_784);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.InternalEList"));
    stringBuffer.append(TEXT_785);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.FeatureMap"));
    stringBuffer.append(TEXT_786);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_787);
    } else {
    stringBuffer.append(TEXT_788);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.InternalEList"));
    stringBuffer.append(TEXT_789);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_790);
    }
    } else if (genFeature.isContainer()) {
    stringBuffer.append(TEXT_791);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(TEXT_792);
    } else if (genFeature.isUnsettable()) {
    stringBuffer.append(TEXT_793);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_794);
    } else {
    stringBuffer.append(TEXT_795);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_796);
    }
    }
    }
    stringBuffer.append(TEXT_797);
    if (genModel.isMinimalReflectiveMethods()) {
    stringBuffer.append(TEXT_798);
    } else {
    stringBuffer.append(TEXT_799);
    }
    stringBuffer.append(TEXT_800);
    }
    if (!genModel.isReflectiveDelegation() && genClass.implementsAny(genClass.getEBasicRemoveFromContainerGenFeatures())) {
    stringBuffer.append(TEXT_801);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.notify.NotificationChain"));
    stringBuffer.append(TEXT_802);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.notify.NotificationChain"));
    stringBuffer.append(TEXT_803);
    for (Iterator i=genClass.getEBasicRemoveFromContainerGenFeatures().iterator(); i.hasNext();) { GenFeature genFeature = (GenFeature)i.next();
    GenFeature reverseFeature = genFeature.getReverse(); GenClass targetClass = reverseFeature.getGenClass();
    if (!genModel.isMinimalReflectiveMethods() || genClass.getImplementedGenFeatures().contains(genFeature)) {
    stringBuffer.append(TEXT_804);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(TEXT_805);
    stringBuffer.append(targetClass.getQualifiedFeatureID(reverseFeature));
    stringBuffer.append(TEXT_806);
    stringBuffer.append(targetClass.getImportedInterfaceName());
    stringBuffer.append(TEXT_807);
    }
    }
    stringBuffer.append(TEXT_808);
    if (genModel.isMinimalReflectiveMethods()) {
    stringBuffer.append(TEXT_809);
    } else {
    stringBuffer.append(TEXT_810);
    }
    stringBuffer.append(TEXT_811);
    }
    if (!genModel.isReflectiveDelegation() && !genClass.getImplementedGenFeatures().isEmpty()) {
    stringBuffer.append(TEXT_812);
    for (Iterator i=genClass.getAllGenFeatures().iterator(); i.hasNext();) { GenFeature genFeature = (GenFeature)i.next();
    if (!genModel.isMinimalReflectiveMethods() || genClass.getImplementedGenFeatures().contains(genFeature)) {
    stringBuffer.append(TEXT_813);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(TEXT_814);
    if (genFeature.isPrimitiveType()) {
    if (genFeature.isBooleanType()) {
    stringBuffer.append(TEXT_815);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_816);
    } else {
    stringBuffer.append(TEXT_817);
    stringBuffer.append(genFeature.getObjectType());
    stringBuffer.append(TEXT_818);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_819);
    }
    } else if (genFeature.isResolveProxies() && !genFeature.isListType()) {
    stringBuffer.append(TEXT_820);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_821);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_822);
    } else if (genFeature.isMapType()) {
    if (genFeature.isEffectiveSuppressEMFTypes()) {
    stringBuffer.append(TEXT_823);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.util.EMap"));
    stringBuffer.append(TEXT_824);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_825);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_826);
    } else {
    stringBuffer.append(TEXT_827);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_828);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_829);
    }
    } else if (genFeature.isWrappedFeatureMapType()) {
    stringBuffer.append(TEXT_830);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.FeatureMap"));
    stringBuffer.append(TEXT_831);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_832);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_833);
    } else if (genFeature.isFeatureMapType()) {
    stringBuffer.append(TEXT_834);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_835);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.FeatureMap"));
    stringBuffer.append(TEXT_836);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_837);
    } else {
    stringBuffer.append(TEXT_838);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_839);
    }
    }
    }
    stringBuffer.append(TEXT_840);
    if (genModel.isMinimalReflectiveMethods()) {
    stringBuffer.append(TEXT_841);
    } else {
    stringBuffer.append(TEXT_842);
    }
    stringBuffer.append(TEXT_843);
    }
    if (!genModel.isReflectiveDelegation() && genClass.implementsAny(genClass.getESetGenFeatures())) {
    stringBuffer.append(TEXT_844);
    for (Iterator i=genClass.getESetGenFeatures().iterator(); i.hasNext();) { GenFeature genFeature = (GenFeature)i.next();
    if (!genModel.isMinimalReflectiveMethods() || genClass.getImplementedGenFeatures().contains(genFeature)) {
    stringBuffer.append(TEXT_845);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(TEXT_846);
    if (genFeature.isListType()) {
    if (genFeature.isWrappedFeatureMapType()) {
    stringBuffer.append(TEXT_847);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.FeatureMap"));
    stringBuffer.append(TEXT_848);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.FeatureMap"));
    stringBuffer.append(TEXT_849);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_850);
    } else if (genFeature.isFeatureMapType()) {
    stringBuffer.append(TEXT_851);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.FeatureMap"));
    stringBuffer.append(TEXT_852);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_853);
    } else if (genFeature.isMapType()) {
    if (genFeature.isEffectiveSuppressEMFTypes()) {
    stringBuffer.append(TEXT_854);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.EStructuralFeature"));
    stringBuffer.append(TEXT_855);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.util.EMap"));
    stringBuffer.append(TEXT_856);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_857);
    } else {
    stringBuffer.append(TEXT_858);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.EStructuralFeature"));
    stringBuffer.append(TEXT_859);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_860);
    }
    } else {
    stringBuffer.append(TEXT_861);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_862);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_863);
    stringBuffer.append(genModel.getImportedName("java.util.Collection"));
    stringBuffer.append(TEXT_864);
    }
    } else if (genFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_865);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_866);
    stringBuffer.append(genFeature.getObjectType());
    stringBuffer.append(TEXT_867);
    stringBuffer.append(genFeature.getPrimitiveValueFunction());
    stringBuffer.append(TEXT_868);
    } else {
    stringBuffer.append(TEXT_869);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_870);
    stringBuffer.append(genFeature.getImportedType());
    stringBuffer.append(TEXT_871);
    }
    stringBuffer.append(TEXT_872);
    }
    }
    stringBuffer.append(TEXT_873);
    if (genModel.isMinimalReflectiveMethods()) {
    stringBuffer.append(TEXT_874);
    } else {
    stringBuffer.append(TEXT_875);
    }
    stringBuffer.append(TEXT_876);
    for (Iterator i=genClass.getESetGenFeatures().iterator(); i.hasNext();) { GenFeature genFeature = (GenFeature)i.next();
    if (!genModel.isMinimalReflectiveMethods() || genClass.getImplementedGenFeatures().contains(genFeature)) {
    stringBuffer.append(TEXT_877);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(TEXT_878);
    if (genFeature.isListType() && !genFeature.isUnsettable()) {
    if (genFeature.isWrappedFeatureMapType()) {
    stringBuffer.append(TEXT_879);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.FeatureMap"));
    stringBuffer.append(TEXT_880);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_881);
    } else {
    stringBuffer.append(TEXT_882);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_883);
    }
    } else if (genFeature.isUnsettable()) {
    stringBuffer.append(TEXT_884);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_885);
    } else if (genFeature.isReferenceType()) {
    stringBuffer.append(TEXT_886);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_887);
    stringBuffer.append(genFeature.getImportedType());
    stringBuffer.append(TEXT_888);
    } else {
    stringBuffer.append(TEXT_889);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_890);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_891);
    }
    stringBuffer.append(TEXT_892);
    }
    }
    stringBuffer.append(TEXT_893);
    if (genModel.isMinimalReflectiveMethods()) {
    stringBuffer.append(TEXT_894);
    } else {
    stringBuffer.append(TEXT_895);
    }
    stringBuffer.append(TEXT_896);
    }
    if (!genModel.isReflectiveDelegation() && !genClass.getImplementedGenFeatures().isEmpty()) {
    stringBuffer.append(TEXT_897);
    for (Iterator i=genClass.getAllGenFeatures().iterator(); i.hasNext();) { GenFeature genFeature = (GenFeature)i.next();
    if (!genModel.isMinimalReflectiveMethods() || genClass.getImplementedGenFeatures().contains(genFeature)) {
    stringBuffer.append(TEXT_898);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(TEXT_899);
    if (genFeature.isListType() && !genFeature.isUnsettable()) {
    if (genFeature.isWrappedFeatureMapType()) {
    if (genFeature.isVolatile()) {
    stringBuffer.append(TEXT_900);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.FeatureMap"));
    stringBuffer.append(TEXT_901);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_902);
    } else {
    stringBuffer.append(TEXT_903);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_904);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_905);
    }
    } else {
    if (genClass.isField(genFeature)) {
    stringBuffer.append(TEXT_906);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_907);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_908);
    } else {
    if (genFeature.isField() && (genClass.getImplementedGenFeatures().contains(genFeature) ? genModel.isVirtualDelegation() : genFeature.getGenModel().isVirtualDelegation())) {
    stringBuffer.append(TEXT_909);
    stringBuffer.append(genFeature.getImportedType());
    stringBuffer.append(TEXT_910);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_911);
    stringBuffer.append(genFeature.getImportedType());
    stringBuffer.append(TEXT_912);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(TEXT_913);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_914);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_915);
    } else {
    stringBuffer.append(TEXT_916);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_917);
    }
    }
    }
    } else if (genFeature.isUnsettable()) {
    stringBuffer.append(TEXT_918);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_919);
    } else if (genFeature.isResolveProxies()) {
    if (genClass.isField(genFeature)) {
    stringBuffer.append(TEXT_920);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_921);
    } else {
    if (genFeature.isField() && (genClass.getImplementedGenFeatures().contains(genFeature) ? genModel.isVirtualDelegation() : genFeature.getGenModel().isVirtualDelegation())) {
    stringBuffer.append(TEXT_922);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(TEXT_923);
    } else {
    stringBuffer.append(TEXT_924);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_925);
    }
    }
    } else if (genFeature.isReferenceType()) {
    if (genClass.isField(genFeature)) {
    stringBuffer.append(TEXT_926);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_927);
    } else {
    if (genFeature.isField() && (genClass.getImplementedGenFeatures().contains(genFeature) ? genModel.isVirtualDelegation() : genFeature.getGenModel().isVirtualDelegation())) {
    stringBuffer.append(TEXT_928);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(TEXT_929);
    } else {
    stringBuffer.append(TEXT_930);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_931);
    }
    }
    } else if (genFeature.isPrimitiveType() || genFeature.isEnumType()) {
    if (genClass.isField(genFeature)) {
    if (genClass.isFlag(genFeature)) {
    stringBuffer.append(TEXT_932);
    stringBuffer.append(genClass.getFlagsField(genFeature));
    stringBuffer.append(TEXT_933);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_934);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_935);
    } else {
    stringBuffer.append(TEXT_936);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_937);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_938);
    }
    } else {
    if (genFeature.isEnumType() && genFeature.isField() && (genClass.getImplementedGenFeatures().contains(genFeature) ? genModel.isVirtualDelegation() : genFeature.getGenModel().isVirtualDelegation())) {
    stringBuffer.append(TEXT_939);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(TEXT_940);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_941);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_942);
    } else {
    stringBuffer.append(TEXT_943);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_944);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_945);
    }
    }
    } else {//datatype
    if (genClass.isField(genFeature)) {
    stringBuffer.append(TEXT_946);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_947);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_948);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_949);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_950);
    } else {
    if (genFeature.isField() && (genClass.getImplementedGenFeatures().contains(genFeature) ? genModel.isVirtualDelegation() : genFeature.getGenModel().isVirtualDelegation())) {
    stringBuffer.append(TEXT_951);
    stringBuffer.append(genFeature.getImportedType());
    stringBuffer.append(TEXT_952);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_953);
    stringBuffer.append(genFeature.getImportedType());
    stringBuffer.append(TEXT_954);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(TEXT_955);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_956);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_957);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_958);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_959);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_960);
    } else {
    stringBuffer.append(TEXT_961);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_962);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_963);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_964);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_965);
    }
    }
    }
    }
    }
    stringBuffer.append(TEXT_966);
    if (genModel.isMinimalReflectiveMethods()) {
    stringBuffer.append(TEXT_967);
    } else {
    stringBuffer.append(TEXT_968);
    }
    stringBuffer.append(TEXT_969);
    //Class/eIsSet.override.javajetinc
    }
    if (!genClass.getMixinGenFeatures().isEmpty()) {
    stringBuffer.append(TEXT_970);
    for (Iterator m=genClass.getMixinGenClasses().iterator(); m.hasNext();) { GenClass mixinGenClass = (GenClass)m.next(); 
    stringBuffer.append(TEXT_971);
    stringBuffer.append(mixinGenClass.getImportedInterfaceName());
    stringBuffer.append(TEXT_972);
    for (Iterator f=mixinGenClass.getGenFeatures().iterator(); f.hasNext();) { GenFeature genFeature = (GenFeature)f.next(); 
    stringBuffer.append(TEXT_973);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(TEXT_974);
    stringBuffer.append(mixinGenClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(TEXT_975);
    }
    stringBuffer.append(TEXT_976);
    }
    stringBuffer.append(TEXT_977);
    for (Iterator m=genClass.getMixinGenClasses().iterator(); m.hasNext();) { GenClass mixinGenClass = (GenClass)m.next(); 
    stringBuffer.append(TEXT_978);
    stringBuffer.append(mixinGenClass.getImportedInterfaceName());
    stringBuffer.append(TEXT_979);
    for (Iterator f=mixinGenClass.getGenFeatures().iterator(); f.hasNext();) { GenFeature genFeature = (GenFeature)f.next(); 
    stringBuffer.append(TEXT_980);
    stringBuffer.append(mixinGenClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(TEXT_981);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(TEXT_982);
    }
    stringBuffer.append(TEXT_983);
    }
    stringBuffer.append(TEXT_984);
    }
    if (genModel.isVirtualDelegation()) { String eVirtualValuesField = genClass.getEVirtualValuesField();
    if (eVirtualValuesField != null) {
    stringBuffer.append(TEXT_985);
    stringBuffer.append(eVirtualValuesField);
    stringBuffer.append(TEXT_986);
    stringBuffer.append(eVirtualValuesField);
    stringBuffer.append(TEXT_987);
    }
    { List eVirtualIndexBitFields = genClass.getEVirtualIndexBitFields(new ArrayList());
    if (!eVirtualIndexBitFields.isEmpty()) { List allEVirtualIndexBitFields = genClass.getAllEVirtualIndexBitFields(new ArrayList());
    stringBuffer.append(TEXT_988);
    for (int i = 0; i < allEVirtualIndexBitFields.size(); i++) {
    stringBuffer.append(TEXT_989);
    stringBuffer.append(i);
    stringBuffer.append(TEXT_990);
    stringBuffer.append(allEVirtualIndexBitFields.get(i));
    stringBuffer.append(TEXT_991);
    }
    stringBuffer.append(TEXT_992);
    for (int i = 0; i < allEVirtualIndexBitFields.size(); i++) {
    stringBuffer.append(TEXT_993);
    stringBuffer.append(i);
    stringBuffer.append(TEXT_994);
    stringBuffer.append(allEVirtualIndexBitFields.get(i));
    stringBuffer.append(TEXT_995);
    }
    stringBuffer.append(TEXT_996);
    }
    }
    }
    if (!genModel.isReflectiveDelegation() && !genClass.getToStringGenFeatures().isEmpty()) {
    stringBuffer.append(TEXT_997);
    { boolean first = true;
    for (Iterator i=genClass.getToStringGenFeatures().iterator(); i.hasNext(); ) { GenFeature genFeature = (GenFeature)i.next();
    if (first) { first = false;
    stringBuffer.append(TEXT_998);
    stringBuffer.append(genFeature.getName());
    stringBuffer.append(TEXT_999);
    stringBuffer.append(genModel.getNonNLS());
    } else {
    stringBuffer.append(TEXT_1000);
    stringBuffer.append(genFeature.getName());
    stringBuffer.append(TEXT_1001);
    stringBuffer.append(genModel.getNonNLS());
    }
    if (genFeature.isUnsettable() && !genFeature.isListType()) {
    if (genModel.isVirtualDelegation() && !genFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_1002);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(TEXT_1003);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(TEXT_1004);
    stringBuffer.append(genModel.getNonNLS());
    } else {
    if (genClass.isFlag(genFeature)) {
    stringBuffer.append(TEXT_1005);
    if (genClass.isESetFlag(genFeature)) {
    stringBuffer.append(TEXT_1006);
    stringBuffer.append(genClass.getESetFlagsField(genFeature));
    stringBuffer.append(TEXT_1007);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_1008);
    } else {
    stringBuffer.append(genFeature.getUncapName());
    stringBuffer.append(TEXT_1009);
    }
    stringBuffer.append(TEXT_1010);
    stringBuffer.append(genClass.getFlagsField(genFeature));
    stringBuffer.append(TEXT_1011);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_1012);
    stringBuffer.append(genModel.getNonNLS());
    } else {
    stringBuffer.append(TEXT_1013);
    if (genClass.isESetFlag(genFeature)) {
    stringBuffer.append(TEXT_1014);
    stringBuffer.append(genClass.getESetFlagsField(genFeature));
    stringBuffer.append(TEXT_1015);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_1016);
    } else {
    stringBuffer.append(genFeature.getUncapName());
    stringBuffer.append(TEXT_1017);
    }
    stringBuffer.append(TEXT_1018);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_1019);
    stringBuffer.append(genModel.getNonNLS());
    }
    }
    } else {
    if (genModel.isVirtualDelegation() && !genFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_1020);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    if (!genFeature.isListType() && !genFeature.isReferenceType()){
    stringBuffer.append(TEXT_1021);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_1022);
    }
    stringBuffer.append(TEXT_1023);
    } else {
    if (genClass.isFlag(genFeature)) {
    stringBuffer.append(TEXT_1024);
    stringBuffer.append(genClass.getFlagsField(genFeature));
    stringBuffer.append(TEXT_1025);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_1026);
    } else {
    stringBuffer.append(TEXT_1027);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_1028);
    }
    }
    }
    }
    }
    stringBuffer.append(TEXT_1029);
    }
    if (genClass.isMapEntry()) { GenFeature keyFeature = genClass.getMapEntryKeyFeature(); GenFeature valueFeature = genClass.getMapEntryValueFeature();
    stringBuffer.append(TEXT_1030);
    if (keyFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_1031);
    stringBuffer.append(keyFeature.getObjectType());
    stringBuffer.append(TEXT_1032);
    } else {
    stringBuffer.append(TEXT_1033);
    }
    stringBuffer.append(TEXT_1034);
    if (keyFeature.isListType()) {
    stringBuffer.append(TEXT_1035);
    stringBuffer.append(genModel.getImportedName("java.util.Collection"));
    stringBuffer.append(TEXT_1036);
    } else if (keyFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_1037);
    stringBuffer.append(keyFeature.getObjectType());
    stringBuffer.append(TEXT_1038);
    stringBuffer.append(keyFeature.getPrimitiveValueFunction());
    stringBuffer.append(TEXT_1039);
    } else {
    stringBuffer.append(TEXT_1040);
    stringBuffer.append(keyFeature.getImportedType());
    stringBuffer.append(TEXT_1041);
    }
    stringBuffer.append(TEXT_1042);
    if (valueFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_1043);
    stringBuffer.append(valueFeature.getObjectType());
    stringBuffer.append(TEXT_1044);
    } else {
    stringBuffer.append(TEXT_1045);
    }
    stringBuffer.append(TEXT_1046);
    if (valueFeature.isListType()) {
    stringBuffer.append(TEXT_1047);
    stringBuffer.append(genModel.getImportedName("java.util.Collection"));
    stringBuffer.append(TEXT_1048);
    } else if (valueFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_1049);
    stringBuffer.append(valueFeature.getObjectType());
    stringBuffer.append(TEXT_1050);
    stringBuffer.append(valueFeature.getPrimitiveValueFunction());
    stringBuffer.append(TEXT_1051);
    } else {
    stringBuffer.append(TEXT_1052);
    stringBuffer.append(valueFeature.getImportedType());
    stringBuffer.append(TEXT_1053);
    }
    stringBuffer.append(TEXT_1054);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.util.EMap"));
    stringBuffer.append(TEXT_1055);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.EObject"));
    stringBuffer.append(TEXT_1056);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.util.EMap"));
    stringBuffer.append(TEXT_1057);
    }
    stringBuffer.append(TEXT_1058);
    stringBuffer.append(genClass.getClassName());
    genModel.emitSortedImports();
    stringBuffer.append(TEXT_1059);
    return stringBuffer.toString();
  }
}
