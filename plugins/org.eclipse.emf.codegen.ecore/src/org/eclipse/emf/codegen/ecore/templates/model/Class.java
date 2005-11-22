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
  protected final String TEXT_169 = ")eVirtualGet(";
  protected final String TEXT_170 = ", ";
  protected final String TEXT_171 = "_EDEFAULT";
  protected final String TEXT_172 = ");";
  protected final String TEXT_173 = NL + "\t\treturn (";
  protected final String TEXT_174 = " & ";
  protected final String TEXT_175 = "_EFLAG) != 0;";
  protected final String TEXT_176 = NL + "\t\treturn ";
  protected final String TEXT_177 = ";";
  protected final String TEXT_178 = NL + "\t\t";
  protected final String TEXT_179 = " ";
  protected final String TEXT_180 = " = basicGet";
  protected final String TEXT_181 = "();" + NL + "\t\treturn ";
  protected final String TEXT_182 = " != null && ";
  protected final String TEXT_183 = ".eIsProxy() ? ";
  protected final String TEXT_184 = "eResolveProxy((";
  protected final String TEXT_185 = ")";
  protected final String TEXT_186 = ") : ";
  protected final String TEXT_187 = ";";
  protected final String TEXT_188 = NL + "\t\treturn new ";
  protected final String TEXT_189 = "((";
  protected final String TEXT_190 = ".Internal)((";
  protected final String TEXT_191 = ".Internal.Wrapper)get";
  protected final String TEXT_192 = "()).featureMap().list(";
  protected final String TEXT_193 = "()));";
  protected final String TEXT_194 = NL + "\t\treturn (";
  protected final String TEXT_195 = ")((";
  protected final String TEXT_196 = ")get";
  protected final String TEXT_197 = "()).list(";
  protected final String TEXT_198 = "());";
  protected final String TEXT_199 = NL + "\t\treturn ((";
  protected final String TEXT_200 = ".Internal.Wrapper)get";
  protected final String TEXT_201 = "()).featureMap().list(";
  protected final String TEXT_202 = "());";
  protected final String TEXT_203 = NL + "\t\treturn ((";
  protected final String TEXT_204 = ")get";
  protected final String TEXT_205 = "()).list(";
  protected final String TEXT_206 = "());";
  protected final String TEXT_207 = NL + "\t\treturn ";
  protected final String TEXT_208 = "(";
  protected final String TEXT_209 = "(";
  protected final String TEXT_210 = ")((";
  protected final String TEXT_211 = ".Internal.Wrapper)get";
  protected final String TEXT_212 = "()).featureMap().get(";
  protected final String TEXT_213 = "(), true)";
  protected final String TEXT_214 = ").";
  protected final String TEXT_215 = "()";
  protected final String TEXT_216 = ";";
  protected final String TEXT_217 = NL + "\t\treturn ";
  protected final String TEXT_218 = "(";
  protected final String TEXT_219 = "(";
  protected final String TEXT_220 = ")get";
  protected final String TEXT_221 = "().get(";
  protected final String TEXT_222 = "(), true)";
  protected final String TEXT_223 = ").";
  protected final String TEXT_224 = "()";
  protected final String TEXT_225 = ";";
  protected final String TEXT_226 = NL + "\t\t// TODO: implement this method to return the '";
  protected final String TEXT_227 = "' ";
  protected final String TEXT_228 = NL + "\t\t// Ensure that you remove @generated or mark it @generated NOT" + NL + "\t\tthrow new UnsupportedOperationException();";
  protected final String TEXT_229 = NL + "\t}" + NL;
  protected final String TEXT_230 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */" + NL + "\tpublic ";
  protected final String TEXT_231 = " basicGet";
  protected final String TEXT_232 = "()" + NL + "\t{";
  protected final String TEXT_233 = NL + "\t\tif (eContainerFeatureID != ";
  protected final String TEXT_234 = ") return null;" + NL + "\t\treturn (";
  protected final String TEXT_235 = ")eInternalContainer();";
  protected final String TEXT_236 = NL + "\t\treturn (";
  protected final String TEXT_237 = ")eVirtualGet(";
  protected final String TEXT_238 = ");";
  protected final String TEXT_239 = NL + "\t\treturn ";
  protected final String TEXT_240 = ";";
  protected final String TEXT_241 = NL + "\t\treturn (";
  protected final String TEXT_242 = ")((";
  protected final String TEXT_243 = ".Internal.Wrapper)get";
  protected final String TEXT_244 = "()).featureMap().get(";
  protected final String TEXT_245 = "(), false);";
  protected final String TEXT_246 = NL + "\t\treturn (";
  protected final String TEXT_247 = ")get";
  protected final String TEXT_248 = "().get(";
  protected final String TEXT_249 = "(), false);";
  protected final String TEXT_250 = NL + "\t\t// TODO: implement this method to return the '";
  protected final String TEXT_251 = "' ";
  protected final String TEXT_252 = NL + "\t\t// -> do not perform proxy resolution" + NL + "\t\t// Ensure that you remove @generated or mark it @generated NOT" + NL + "\t\tthrow new UnsupportedOperationException();";
  protected final String TEXT_253 = NL + "\t}" + NL;
  protected final String TEXT_254 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */" + NL + "\tpublic ";
  protected final String TEXT_255 = " basicSet";
  protected final String TEXT_256 = "(";
  protected final String TEXT_257 = " new";
  protected final String TEXT_258 = ", ";
  protected final String TEXT_259 = " msgs)" + NL + "\t{";
  protected final String TEXT_260 = NL + "\t\tObject old";
  protected final String TEXT_261 = " = eVirtualSet(";
  protected final String TEXT_262 = ", new";
  protected final String TEXT_263 = ");";
  protected final String TEXT_264 = NL + "\t\t";
  protected final String TEXT_265 = " old";
  protected final String TEXT_266 = " = ";
  protected final String TEXT_267 = ";" + NL + "\t\t";
  protected final String TEXT_268 = " = new";
  protected final String TEXT_269 = ";";
  protected final String TEXT_270 = NL + "\t\tboolean isSetChange = old";
  protected final String TEXT_271 = " == EVIRTUAL_NO_VALUE;";
  protected final String TEXT_272 = NL + "\t\tboolean old";
  protected final String TEXT_273 = "ESet = (";
  protected final String TEXT_274 = " & ";
  protected final String TEXT_275 = "_ESETFLAG) != 0;" + NL + "\t\t";
  protected final String TEXT_276 = " |= ";
  protected final String TEXT_277 = "_ESETFLAG;";
  protected final String TEXT_278 = NL + "\t\tboolean old";
  protected final String TEXT_279 = "ESet = ";
  protected final String TEXT_280 = "ESet;" + NL + "\t\t";
  protected final String TEXT_281 = "ESet = true;";
  protected final String TEXT_282 = NL + "\t\tif (eNotificationRequired())" + NL + "\t\t{";
  protected final String TEXT_283 = NL + "\t\t\t";
  protected final String TEXT_284 = " notification = new ";
  protected final String TEXT_285 = "(this, ";
  protected final String TEXT_286 = ".SET, ";
  protected final String TEXT_287 = ", ";
  protected final String TEXT_288 = "isSetChange ? null : old";
  protected final String TEXT_289 = "old";
  protected final String TEXT_290 = ", new";
  protected final String TEXT_291 = ", ";
  protected final String TEXT_292 = "isSetChange";
  protected final String TEXT_293 = "!old";
  protected final String TEXT_294 = "ESet";
  protected final String TEXT_295 = ");";
  protected final String TEXT_296 = NL + "\t\t\t";
  protected final String TEXT_297 = " notification = new ";
  protected final String TEXT_298 = "(this, ";
  protected final String TEXT_299 = ".SET, ";
  protected final String TEXT_300 = ", ";
  protected final String TEXT_301 = "old";
  protected final String TEXT_302 = " == EVIRTUAL_NO_VALUE ? null : old";
  protected final String TEXT_303 = "old";
  protected final String TEXT_304 = ", new";
  protected final String TEXT_305 = ");";
  protected final String TEXT_306 = NL + "\t\t\tif (msgs == null) msgs = notification; else msgs.add(notification);" + NL + "\t\t}";
  protected final String TEXT_307 = NL + "\t\treturn msgs;";
  protected final String TEXT_308 = NL + "\t\treturn ((";
  protected final String TEXT_309 = ".Internal)((";
  protected final String TEXT_310 = ".Internal.Wrapper)get";
  protected final String TEXT_311 = "()).featureMap()).basicAdd(";
  protected final String TEXT_312 = "(), new";
  protected final String TEXT_313 = ", msgs);";
  protected final String TEXT_314 = NL + "\t\treturn ((";
  protected final String TEXT_315 = ".Internal)get";
  protected final String TEXT_316 = "()).basicAdd(";
  protected final String TEXT_317 = "(), new";
  protected final String TEXT_318 = ", msgs);";
  protected final String TEXT_319 = NL + "\t\t// TODO: implement this method to set the contained '";
  protected final String TEXT_320 = "' ";
  protected final String TEXT_321 = NL + "\t\t// -> this method is automatically invoked to keep the containment relationship in synch" + NL + "\t\t// -> do not modify other features" + NL + "\t\t// -> return msgs, after adding any generated Notification to it (if it is null, a NotificationChain object must be created first)" + NL + "\t\t// Ensure that you remove @generated or mark it @generated NOT" + NL + "\t\tthrow new UnsupportedOperationException();";
  protected final String TEXT_322 = NL + "\t}" + NL;
  protected final String TEXT_323 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */" + NL + "\tpublic void set";
  protected final String TEXT_324 = "(";
  protected final String TEXT_325 = " new";
  protected final String TEXT_326 = ")" + NL + "\t{";
  protected final String TEXT_327 = NL + "\t\teSet(";
  protected final String TEXT_328 = ".eINSTANCE.get";
  protected final String TEXT_329 = "(), ";
  protected final String TEXT_330 = "new ";
  protected final String TEXT_331 = "(";
  protected final String TEXT_332 = "new";
  protected final String TEXT_333 = ")";
  protected final String TEXT_334 = ");";
  protected final String TEXT_335 = NL + "\t\tif (new";
  protected final String TEXT_336 = " != eInternalContainer() || (eContainerFeatureID != ";
  protected final String TEXT_337 = " && new";
  protected final String TEXT_338 = " != null))" + NL + "\t\t{" + NL + "\t\t\tif (";
  protected final String TEXT_339 = ".isAncestor(this, ";
  protected final String TEXT_340 = "new";
  protected final String TEXT_341 = "))" + NL + "\t\t\t\tthrow new ";
  protected final String TEXT_342 = "(\"Recursive containment not allowed for \" + toString());";
  protected final String TEXT_343 = NL + "\t\t\t";
  protected final String TEXT_344 = " msgs = null;" + NL + "\t\t\tif (eInternalContainer() != null)" + NL + "\t\t\t\tmsgs = eBasicRemoveFromContainer(msgs);" + NL + "\t\t\tif (new";
  protected final String TEXT_345 = " != null)" + NL + "\t\t\t\tmsgs = ((";
  protected final String TEXT_346 = ")new";
  protected final String TEXT_347 = ").eInverseAdd(this, ";
  protected final String TEXT_348 = ", ";
  protected final String TEXT_349 = ".class, msgs);" + NL + "\t\t\tmsgs = eBasicSetContainer((";
  protected final String TEXT_350 = ")new";
  protected final String TEXT_351 = ", ";
  protected final String TEXT_352 = ", msgs);" + NL + "\t\t\tif (msgs != null) msgs.dispatch();" + NL + "\t\t}" + NL + "\t\telse if (eNotificationRequired())" + NL + "\t\t\teNotify(new ";
  protected final String TEXT_353 = "(this, ";
  protected final String TEXT_354 = ".SET, ";
  protected final String TEXT_355 = ", new";
  protected final String TEXT_356 = ", new";
  protected final String TEXT_357 = "));";
  protected final String TEXT_358 = NL + "\t\t";
  protected final String TEXT_359 = " ";
  protected final String TEXT_360 = " = (";
  protected final String TEXT_361 = ")eVirtualGet(";
  protected final String TEXT_362 = ");";
  protected final String TEXT_363 = NL + "\t\tif (new";
  protected final String TEXT_364 = " != ";
  protected final String TEXT_365 = ")" + NL + "\t\t{" + NL + "\t\t\t";
  protected final String TEXT_366 = " msgs = null;" + NL + "\t\t\tif (";
  protected final String TEXT_367 = " != null)";
  protected final String TEXT_368 = NL + "\t\t\t\tmsgs = ((";
  protected final String TEXT_369 = ")";
  protected final String TEXT_370 = ").eInverseRemove(this, EOPPOSITE_FEATURE_BASE - ";
  protected final String TEXT_371 = ", null, msgs);" + NL + "\t\t\tif (new";
  protected final String TEXT_372 = " != null)" + NL + "\t\t\t\tmsgs = ((";
  protected final String TEXT_373 = ")new";
  protected final String TEXT_374 = ").eInverseAdd(this, EOPPOSITE_FEATURE_BASE - ";
  protected final String TEXT_375 = ", null, msgs);";
  protected final String TEXT_376 = NL + "\t\t\t\tmsgs = ((";
  protected final String TEXT_377 = ")";
  protected final String TEXT_378 = ").eInverseRemove(this, ";
  protected final String TEXT_379 = ", ";
  protected final String TEXT_380 = ".class, msgs);" + NL + "\t\t\tif (new";
  protected final String TEXT_381 = " != null)" + NL + "\t\t\t\tmsgs = ((";
  protected final String TEXT_382 = ")new";
  protected final String TEXT_383 = ").eInverseAdd(this, ";
  protected final String TEXT_384 = ", ";
  protected final String TEXT_385 = ".class, msgs);";
  protected final String TEXT_386 = NL + "\t\t\tmsgs = basicSet";
  protected final String TEXT_387 = "(";
  protected final String TEXT_388 = "new";
  protected final String TEXT_389 = ", msgs);" + NL + "\t\t\tif (msgs != null) msgs.dispatch();" + NL + "\t\t}";
  protected final String TEXT_390 = NL + "\t\telse" + NL + "    \t{";
  protected final String TEXT_391 = NL + "\t\t\tboolean old";
  protected final String TEXT_392 = "ESet = eVirtualIsSet(";
  protected final String TEXT_393 = ");";
  protected final String TEXT_394 = NL + "\t\t\tboolean old";
  protected final String TEXT_395 = "ESet = (";
  protected final String TEXT_396 = " & ";
  protected final String TEXT_397 = "_ESETFLAG) != 0;" + NL + "\t\t\t";
  protected final String TEXT_398 = " |= ";
  protected final String TEXT_399 = "_ESETFLAG;";
  protected final String TEXT_400 = NL + "\t\t\tboolean old";
  protected final String TEXT_401 = "ESet = ";
  protected final String TEXT_402 = "ESet;" + NL + "\t\t\t";
  protected final String TEXT_403 = "ESet = true;";
  protected final String TEXT_404 = NL + "\t\t\tif (eNotificationRequired())" + NL + "\t\t\t\teNotify(new ";
  protected final String TEXT_405 = "(this, ";
  protected final String TEXT_406 = ".SET, ";
  protected final String TEXT_407 = ", new";
  protected final String TEXT_408 = ", new";
  protected final String TEXT_409 = ", !old";
  protected final String TEXT_410 = "ESet));" + NL + "    \t}";
  protected final String TEXT_411 = NL + "\t\telse if (eNotificationRequired())" + NL + "\t\t\teNotify(new ";
  protected final String TEXT_412 = "(this, ";
  protected final String TEXT_413 = ".SET, ";
  protected final String TEXT_414 = ", new";
  protected final String TEXT_415 = ", new";
  protected final String TEXT_416 = "));";
  protected final String TEXT_417 = NL + "\t\t";
  protected final String TEXT_418 = " old";
  protected final String TEXT_419 = " = (";
  protected final String TEXT_420 = " & ";
  protected final String TEXT_421 = "_EFLAG) != 0;" + NL + "\t\tif (new";
  protected final String TEXT_422 = ") ";
  protected final String TEXT_423 = " |= ";
  protected final String TEXT_424 = "_EFLAG; else ";
  protected final String TEXT_425 = " &= ~";
  protected final String TEXT_426 = "_EFLAG;";
  protected final String TEXT_427 = NL + "\t\t";
  protected final String TEXT_428 = " old";
  protected final String TEXT_429 = " = ";
  protected final String TEXT_430 = ";";
  protected final String TEXT_431 = NL + "\t\t";
  protected final String TEXT_432 = " ";
  protected final String TEXT_433 = " = new";
  protected final String TEXT_434 = " == null ? ";
  protected final String TEXT_435 = "_EDEFAULT : new";
  protected final String TEXT_436 = ";";
  protected final String TEXT_437 = NL + "\t\t";
  protected final String TEXT_438 = " = new";
  protected final String TEXT_439 = " == null ? ";
  protected final String TEXT_440 = "_EDEFAULT : new";
  protected final String TEXT_441 = ";";
  protected final String TEXT_442 = NL + "\t\t";
  protected final String TEXT_443 = " ";
  protected final String TEXT_444 = " = ";
  protected final String TEXT_445 = "new";
  protected final String TEXT_446 = ";";
  protected final String TEXT_447 = NL + "\t\t";
  protected final String TEXT_448 = " = ";
  protected final String TEXT_449 = "new";
  protected final String TEXT_450 = ";";
  protected final String TEXT_451 = NL + "\t\tObject old";
  protected final String TEXT_452 = " = eVirtualSet(";
  protected final String TEXT_453 = ", ";
  protected final String TEXT_454 = ");";
  protected final String TEXT_455 = NL + "\t\tboolean isSetChange = old";
  protected final String TEXT_456 = " == EVIRTUAL_NO_VALUE;";
  protected final String TEXT_457 = NL + "\t\tboolean old";
  protected final String TEXT_458 = "ESet = (";
  protected final String TEXT_459 = " & ";
  protected final String TEXT_460 = "_ESETFLAG) != 0;" + NL + "\t\t";
  protected final String TEXT_461 = " |= ";
  protected final String TEXT_462 = "_ESETFLAG;";
  protected final String TEXT_463 = NL + "\t\tboolean old";
  protected final String TEXT_464 = "ESet = ";
  protected final String TEXT_465 = "ESet;" + NL + "\t\t";
  protected final String TEXT_466 = "ESet = true;";
  protected final String TEXT_467 = NL + "\t\tif (eNotificationRequired())" + NL + "\t\t\teNotify(new ";
  protected final String TEXT_468 = "(this, ";
  protected final String TEXT_469 = ".SET, ";
  protected final String TEXT_470 = ", ";
  protected final String TEXT_471 = "isSetChange ? ";
  protected final String TEXT_472 = "null";
  protected final String TEXT_473 = "_EDEFAULT";
  protected final String TEXT_474 = " : old";
  protected final String TEXT_475 = "old";
  protected final String TEXT_476 = ", ";
  protected final String TEXT_477 = "new";
  protected final String TEXT_478 = ", ";
  protected final String TEXT_479 = "isSetChange";
  protected final String TEXT_480 = "!old";
  protected final String TEXT_481 = "ESet";
  protected final String TEXT_482 = "));";
  protected final String TEXT_483 = NL + "\t\tif (eNotificationRequired())" + NL + "\t\t\teNotify(new ";
  protected final String TEXT_484 = "(this, ";
  protected final String TEXT_485 = ".SET, ";
  protected final String TEXT_486 = ", ";
  protected final String TEXT_487 = "old";
  protected final String TEXT_488 = " == EVIRTUAL_NO_VALUE ? ";
  protected final String TEXT_489 = "null";
  protected final String TEXT_490 = "_EDEFAULT";
  protected final String TEXT_491 = " : old";
  protected final String TEXT_492 = "old";
  protected final String TEXT_493 = ", ";
  protected final String TEXT_494 = "new";
  protected final String TEXT_495 = "));";
  protected final String TEXT_496 = NL + "\t\t((";
  protected final String TEXT_497 = ".Internal)((";
  protected final String TEXT_498 = ".Internal.Wrapper)get";
  protected final String TEXT_499 = "()).featureMap()).set(";
  protected final String TEXT_500 = "(), ";
  protected final String TEXT_501 = "new ";
  protected final String TEXT_502 = "(";
  protected final String TEXT_503 = "new";
  protected final String TEXT_504 = ")";
  protected final String TEXT_505 = ");";
  protected final String TEXT_506 = NL + "\t\t((";
  protected final String TEXT_507 = ".Internal)get";
  protected final String TEXT_508 = "()).set(";
  protected final String TEXT_509 = "(), ";
  protected final String TEXT_510 = "new ";
  protected final String TEXT_511 = "(";
  protected final String TEXT_512 = "new";
  protected final String TEXT_513 = ")";
  protected final String TEXT_514 = ");";
  protected final String TEXT_515 = NL + "\t\t// TODO: implement this method to set the '";
  protected final String TEXT_516 = "' ";
  protected final String TEXT_517 = NL + "\t\t// Ensure that you remove @generated or mark it @generated NOT" + NL + "\t\tthrow new UnsupportedOperationException();";
  protected final String TEXT_518 = NL + "\t}" + NL;
  protected final String TEXT_519 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */" + NL + "\tpublic ";
  protected final String TEXT_520 = " basicUnset";
  protected final String TEXT_521 = "(";
  protected final String TEXT_522 = " msgs)" + NL + "\t{";
  protected final String TEXT_523 = NL + "\t\tObject old";
  protected final String TEXT_524 = " = eVirtualUnset(";
  protected final String TEXT_525 = ");";
  protected final String TEXT_526 = NL + "\t\t";
  protected final String TEXT_527 = " old";
  protected final String TEXT_528 = " = ";
  protected final String TEXT_529 = ";" + NL + "\t\t";
  protected final String TEXT_530 = " = null;";
  protected final String TEXT_531 = NL + "\t\tboolean isSetChange = old";
  protected final String TEXT_532 = " != EVIRTUAL_NO_VALUE;";
  protected final String TEXT_533 = NL + "\t\tboolean old";
  protected final String TEXT_534 = "ESet = (";
  protected final String TEXT_535 = " & ";
  protected final String TEXT_536 = "_ESETFLAG) != 0;" + NL + "\t\t";
  protected final String TEXT_537 = " &= ~";
  protected final String TEXT_538 = "_ESETFLAG;";
  protected final String TEXT_539 = NL + "\t\tboolean old";
  protected final String TEXT_540 = "ESet = ";
  protected final String TEXT_541 = "ESet;" + NL + "\t\t";
  protected final String TEXT_542 = "ESet = false;";
  protected final String TEXT_543 = NL + "\t\tif (eNotificationRequired())" + NL + "\t\t{" + NL + "\t\t\t";
  protected final String TEXT_544 = " notification = new ";
  protected final String TEXT_545 = "(this, ";
  protected final String TEXT_546 = ".UNSET, ";
  protected final String TEXT_547 = ", ";
  protected final String TEXT_548 = "isSetChange ? old";
  protected final String TEXT_549 = " : null";
  protected final String TEXT_550 = "old";
  protected final String TEXT_551 = ", null, ";
  protected final String TEXT_552 = "isSetChange";
  protected final String TEXT_553 = "old";
  protected final String TEXT_554 = "ESet";
  protected final String TEXT_555 = ");" + NL + "\t\t\tif (msgs == null) msgs = notification; else msgs.add(notification);" + NL + "\t\t}" + NL + "\t\treturn msgs;";
  protected final String TEXT_556 = NL + "\t\t// TODO: implement this method to unset the contained '";
  protected final String TEXT_557 = "' ";
  protected final String TEXT_558 = NL + "\t\t// -> this method is automatically invoked to keep the containment relationship in synch" + NL + "\t\t// -> do not modify other features" + NL + "\t\t// -> return msgs, after adding any generated Notification to it (if it is null, a NotificationChain object must be created first)" + NL + "\t\t// Ensure that you remove @generated or mark it @generated NOT" + NL + "\t\tthrow new UnsupportedOperationException();";
  protected final String TEXT_559 = NL + "\t}" + NL;
  protected final String TEXT_560 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */" + NL + "\tpublic void unset";
  protected final String TEXT_561 = "()" + NL + "\t{";
  protected final String TEXT_562 = NL + "\t\teUnset(";
  protected final String TEXT_563 = ".eINSTANCE.get";
  protected final String TEXT_564 = "());";
  protected final String TEXT_565 = NL + "\t\t((";
  protected final String TEXT_566 = ".Unsettable)get";
  protected final String TEXT_567 = "()).unset();";
  protected final String TEXT_568 = NL + "\t\t";
  protected final String TEXT_569 = " ";
  protected final String TEXT_570 = " = (";
  protected final String TEXT_571 = ")eVirtualGet(";
  protected final String TEXT_572 = ");";
  protected final String TEXT_573 = NL + "\t\tif (";
  protected final String TEXT_574 = " != null)" + NL + "\t\t{" + NL + "\t\t\t";
  protected final String TEXT_575 = " msgs = null;";
  protected final String TEXT_576 = NL + "\t\t\tmsgs = ((";
  protected final String TEXT_577 = ")";
  protected final String TEXT_578 = ").eInverseRemove(this, EOPPOSITE_FEATURE_BASE - ";
  protected final String TEXT_579 = ", null, msgs);";
  protected final String TEXT_580 = NL + "\t\t\tmsgs = ((";
  protected final String TEXT_581 = ")";
  protected final String TEXT_582 = ").eInverseRemove(this, ";
  protected final String TEXT_583 = ", ";
  protected final String TEXT_584 = ".class, msgs);";
  protected final String TEXT_585 = NL + "\t\t\tmsgs = basicUnset";
  protected final String TEXT_586 = "(msgs);" + NL + "\t\t\tif (msgs != null) msgs.dispatch();" + NL + "\t\t}" + NL + "\t\telse" + NL + "    \t{";
  protected final String TEXT_587 = NL + "\t\t\tboolean old";
  protected final String TEXT_588 = "ESet = eVirtualIsSet(";
  protected final String TEXT_589 = ");";
  protected final String TEXT_590 = NL + "\t\t\tboolean old";
  protected final String TEXT_591 = "ESet = (";
  protected final String TEXT_592 = " & ";
  protected final String TEXT_593 = "_ESETFLAG) != 0;" + NL + "\t\t\t";
  protected final String TEXT_594 = " &= ~";
  protected final String TEXT_595 = "_ESETFLAG;";
  protected final String TEXT_596 = NL + "\t\t\tboolean old";
  protected final String TEXT_597 = "ESet = ";
  protected final String TEXT_598 = "ESet;" + NL + "\t\t\t";
  protected final String TEXT_599 = "ESet = false;";
  protected final String TEXT_600 = NL + "\t\t\tif (eNotificationRequired())" + NL + "\t\t\t\teNotify(new ";
  protected final String TEXT_601 = "(this, ";
  protected final String TEXT_602 = ".UNSET, ";
  protected final String TEXT_603 = ", null, null, old";
  protected final String TEXT_604 = "ESet));" + NL + "    \t}";
  protected final String TEXT_605 = NL + "\t\t";
  protected final String TEXT_606 = " old";
  protected final String TEXT_607 = " = (";
  protected final String TEXT_608 = " & ";
  protected final String TEXT_609 = "_EFLAG) != 0;";
  protected final String TEXT_610 = NL + "\t\tObject old";
  protected final String TEXT_611 = " = eVirtualUnset(";
  protected final String TEXT_612 = ");";
  protected final String TEXT_613 = NL + "\t\t";
  protected final String TEXT_614 = " old";
  protected final String TEXT_615 = " = ";
  protected final String TEXT_616 = ";";
  protected final String TEXT_617 = NL + "\t\tboolean isSetChange = old";
  protected final String TEXT_618 = " != EVIRTUAL_NO_VALUE;";
  protected final String TEXT_619 = NL + "\t\tboolean old";
  protected final String TEXT_620 = "ESet = (";
  protected final String TEXT_621 = " & ";
  protected final String TEXT_622 = "_ESETFLAG) != 0;";
  protected final String TEXT_623 = NL + "\t\tboolean old";
  protected final String TEXT_624 = "ESet = ";
  protected final String TEXT_625 = "ESet;";
  protected final String TEXT_626 = NL + "\t\t";
  protected final String TEXT_627 = " = null;";
  protected final String TEXT_628 = NL + "\t\t";
  protected final String TEXT_629 = " &= ~";
  protected final String TEXT_630 = "_ESETFLAG;";
  protected final String TEXT_631 = NL + "\t\t";
  protected final String TEXT_632 = "ESet = false;";
  protected final String TEXT_633 = NL + "\t\tif (eNotificationRequired())" + NL + "\t\t\teNotify(new ";
  protected final String TEXT_634 = "(this, ";
  protected final String TEXT_635 = ".UNSET, ";
  protected final String TEXT_636 = ", ";
  protected final String TEXT_637 = "isSetChange ? old";
  protected final String TEXT_638 = " : null";
  protected final String TEXT_639 = "old";
  protected final String TEXT_640 = ", null, ";
  protected final String TEXT_641 = "isSetChange";
  protected final String TEXT_642 = "old";
  protected final String TEXT_643 = "ESet";
  protected final String TEXT_644 = "));";
  protected final String TEXT_645 = NL + "\t\tif (";
  protected final String TEXT_646 = "_EDEFAULT) ";
  protected final String TEXT_647 = " |= ";
  protected final String TEXT_648 = "_EFLAG; else ";
  protected final String TEXT_649 = " &= ~";
  protected final String TEXT_650 = "_EFLAG;";
  protected final String TEXT_651 = NL + "\t\t";
  protected final String TEXT_652 = " = ";
  protected final String TEXT_653 = "_EDEFAULT;";
  protected final String TEXT_654 = NL + "\t\t";
  protected final String TEXT_655 = " &= ~";
  protected final String TEXT_656 = "_ESETFLAG;";
  protected final String TEXT_657 = NL + "\t\t";
  protected final String TEXT_658 = "ESet = false;";
  protected final String TEXT_659 = NL + "\t\tif (eNotificationRequired())" + NL + "\t\t\teNotify(new ";
  protected final String TEXT_660 = "(this, ";
  protected final String TEXT_661 = ".UNSET, ";
  protected final String TEXT_662 = ", ";
  protected final String TEXT_663 = "isSetChange ? old";
  protected final String TEXT_664 = " : ";
  protected final String TEXT_665 = "_EDEFAULT";
  protected final String TEXT_666 = "old";
  protected final String TEXT_667 = ", ";
  protected final String TEXT_668 = "_EDEFAULT, ";
  protected final String TEXT_669 = "isSetChange";
  protected final String TEXT_670 = "old";
  protected final String TEXT_671 = "ESet";
  protected final String TEXT_672 = "));";
  protected final String TEXT_673 = NL + "\t\t((";
  protected final String TEXT_674 = ".Internal)((";
  protected final String TEXT_675 = ".Internal.Wrapper)get";
  protected final String TEXT_676 = "()).featureMap()).clear(";
  protected final String TEXT_677 = "());";
  protected final String TEXT_678 = NL + "\t\t((";
  protected final String TEXT_679 = ".Internal)get";
  protected final String TEXT_680 = "()).clear(";
  protected final String TEXT_681 = "());";
  protected final String TEXT_682 = NL + "\t\t// TODO: implement this method to unset the '";
  protected final String TEXT_683 = "' ";
  protected final String TEXT_684 = NL + "\t\t// Ensure that you remove @generated or mark it @generated NOT" + NL + "\t\tthrow new UnsupportedOperationException();";
  protected final String TEXT_685 = NL + "\t}" + NL;
  protected final String TEXT_686 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */" + NL + "\tpublic boolean isSet";
  protected final String TEXT_687 = "()" + NL + "\t{";
  protected final String TEXT_688 = NL + "\t\treturn eIsSet(";
  protected final String TEXT_689 = ".eINSTANCE.get";
  protected final String TEXT_690 = "());";
  protected final String TEXT_691 = NL + "\t\t";
  protected final String TEXT_692 = " ";
  protected final String TEXT_693 = " = (";
  protected final String TEXT_694 = ")eVirtualGet(";
  protected final String TEXT_695 = ");";
  protected final String TEXT_696 = NL + "\t\treturn ";
  protected final String TEXT_697 = " != null && ((";
  protected final String TEXT_698 = ".Unsettable)";
  protected final String TEXT_699 = ").isSet();";
  protected final String TEXT_700 = NL + "\t\treturn eVirtualIsSet(";
  protected final String TEXT_701 = ");";
  protected final String TEXT_702 = NL + "\t\treturn (";
  protected final String TEXT_703 = " & ";
  protected final String TEXT_704 = "_ESETFLAG) != 0;";
  protected final String TEXT_705 = NL + "\t\treturn ";
  protected final String TEXT_706 = "ESet;";
  protected final String TEXT_707 = NL + "\t\treturn !((";
  protected final String TEXT_708 = ".Internal)((";
  protected final String TEXT_709 = ".Internal.Wrapper)get";
  protected final String TEXT_710 = "()).featureMap()).isEmpty(";
  protected final String TEXT_711 = "());";
  protected final String TEXT_712 = NL + "\t\treturn !((";
  protected final String TEXT_713 = ".Internal)get";
  protected final String TEXT_714 = "()).isEmpty(";
  protected final String TEXT_715 = "());";
  protected final String TEXT_716 = NL + "\t\t// TODO: implement this method to return whether the '";
  protected final String TEXT_717 = "' ";
  protected final String TEXT_718 = " is set" + NL + "\t\t// Ensure that you remove @generated or mark it @generated NOT" + NL + "\t\tthrow new UnsupportedOperationException();";
  protected final String TEXT_719 = NL + "\t}" + NL;
  protected final String TEXT_720 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */" + NL + "\tpublic ";
  protected final String TEXT_721 = " ";
  protected final String TEXT_722 = "(";
  protected final String TEXT_723 = ")";
  protected final String TEXT_724 = NL + "\t{";
  protected final String TEXT_725 = NL + "\t\t";
  protected final String TEXT_726 = NL + "\t\t// TODO: implement this method" + NL + "\t\t// -> specify the condition that violates the invariant" + NL + "\t\t// -> verify the details of the diagnostic, including severity and message" + NL + "\t\t// Ensure that you remove @generated or mark it @generated NOT" + NL + "\t\tif (false)" + NL + "\t\t{" + NL + "\t\t\tif (";
  protected final String TEXT_727 = " != null)" + NL + "\t\t\t{" + NL + "\t\t\t\t";
  protected final String TEXT_728 = ".add" + NL + "\t\t\t\t\t(new ";
  protected final String TEXT_729 = NL + "\t\t\t\t\t\t(";
  protected final String TEXT_730 = ".ERROR," + NL + "\t\t\t\t\t\t ";
  protected final String TEXT_731 = ".DIAGNOSTIC_SOURCE," + NL + "\t\t\t\t\t\t ";
  protected final String TEXT_732 = ".";
  protected final String TEXT_733 = "," + NL + "\t\t\t\t\t\t ";
  protected final String TEXT_734 = ".INSTANCE.getString(\"_UI_GenericInvariant_diagnostic\", new Object[] { \"";
  protected final String TEXT_735 = "\", ";
  protected final String TEXT_736 = ".getObjectLabel(this, ";
  protected final String TEXT_737 = ") }),";
  protected final String TEXT_738 = NL + "\t\t\t\t\t\t new Object [] { this }));" + NL + "\t\t\t}" + NL + "\t\t\treturn false;" + NL + "\t\t}" + NL + "\t\treturn true;";
  protected final String TEXT_739 = NL + "\t\t// TODO: implement this method" + NL + "\t\t// Ensure that you remove @generated or mark it @generated NOT" + NL + "\t\tthrow new UnsupportedOperationException();";
  protected final String TEXT_740 = NL + "\t}" + NL;
  protected final String TEXT_741 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */" + NL + "\tpublic ";
  protected final String TEXT_742 = " eInverseAdd(";
  protected final String TEXT_743 = " otherEnd, int featureID, Class baseClass, ";
  protected final String TEXT_744 = " msgs)" + NL + "\t{" + NL + "\t\tif (featureID >= 0)" + NL + "\t\t{" + NL + "\t\t\tswitch (eDerivedStructuralFeatureID(featureID, baseClass))" + NL + "\t\t\t{";
  protected final String TEXT_745 = NL + "\t\t\t\tcase ";
  protected final String TEXT_746 = ":";
  protected final String TEXT_747 = NL + "\t\t\t\t\treturn ((";
  protected final String TEXT_748 = ")((";
  protected final String TEXT_749 = ".InternalMapView)";
  protected final String TEXT_750 = "()).eMap()).basicAdd(otherEnd, msgs);";
  protected final String TEXT_751 = NL + "\t\t\t\t\treturn ((";
  protected final String TEXT_752 = ")";
  protected final String TEXT_753 = "()).basicAdd(otherEnd, msgs);";
  protected final String TEXT_754 = NL + "\t\t\t\t\tif (eInternalContainer() != null)" + NL + "\t\t\t\t\t\tmsgs = eBasicRemoveFromContainer(msgs);" + NL + "\t\t\t\t\treturn eBasicSetContainer(otherEnd, ";
  protected final String TEXT_755 = ", msgs);";
  protected final String TEXT_756 = NL + "\t\t\t\t\t";
  protected final String TEXT_757 = " ";
  protected final String TEXT_758 = " = (";
  protected final String TEXT_759 = ")eVirtualGet(";
  protected final String TEXT_760 = ");";
  protected final String TEXT_761 = NL + "\t\t\t\t\tif (";
  protected final String TEXT_762 = " != null)";
  protected final String TEXT_763 = NL + "\t\t\t\t\t\tmsgs = ((";
  protected final String TEXT_764 = ")";
  protected final String TEXT_765 = ").eInverseRemove(this, EOPPOSITE_FEATURE_BASE - ";
  protected final String TEXT_766 = ", null, msgs);";
  protected final String TEXT_767 = NL + "\t\t\t\t\t\tmsgs = ((";
  protected final String TEXT_768 = ")";
  protected final String TEXT_769 = ").eInverseRemove(this, ";
  protected final String TEXT_770 = ", ";
  protected final String TEXT_771 = ".class, msgs);";
  protected final String TEXT_772 = NL + "\t\t\t\t\treturn basicSet";
  protected final String TEXT_773 = "((";
  protected final String TEXT_774 = ")otherEnd, msgs);";
  protected final String TEXT_775 = NL + "\t\t\t\tdefault:" + NL + "\t\t\t\t\treturn eDynamicInverseAdd(otherEnd, featureID, baseClass, msgs);" + NL + "\t\t\t}" + NL + "\t\t}" + NL + "\t\tif (eInternalContainer() != null)" + NL + "\t\t\tmsgs = eBasicRemoveFromContainer(msgs);" + NL + "\t\treturn eBasicSetContainer(otherEnd, featureID, msgs);" + NL + "\t}" + NL;
  protected final String TEXT_776 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */" + NL + "\tpublic ";
  protected final String TEXT_777 = " eInverseRemove(";
  protected final String TEXT_778 = " otherEnd, int featureID, Class baseClass, ";
  protected final String TEXT_779 = " msgs)" + NL + "\t{" + NL + "\t\tif (featureID >= 0)" + NL + "\t\t{" + NL + "\t\t\tswitch (eDerivedStructuralFeatureID(featureID, baseClass))" + NL + "\t\t\t{";
  protected final String TEXT_780 = NL + "\t\t\t\tcase ";
  protected final String TEXT_781 = ":";
  protected final String TEXT_782 = NL + "\t\t\t\t\treturn ((";
  protected final String TEXT_783 = ")((";
  protected final String TEXT_784 = ".InternalMapView)";
  protected final String TEXT_785 = "()).eMap()).basicRemove(otherEnd, msgs);";
  protected final String TEXT_786 = NL + "\t\t\t\treturn ((";
  protected final String TEXT_787 = ")((";
  protected final String TEXT_788 = ".Internal.Wrapper)";
  protected final String TEXT_789 = "()).featureMap()).basicRemove(otherEnd, msgs);";
  protected final String TEXT_790 = NL + "\t\t\t\t\treturn ((";
  protected final String TEXT_791 = ")";
  protected final String TEXT_792 = "()).basicRemove(otherEnd, msgs);";
  protected final String TEXT_793 = NL + "\t\t\t\t\treturn eBasicSetContainer(null, ";
  protected final String TEXT_794 = ", msgs);";
  protected final String TEXT_795 = NL + "\t\t\t\t\treturn basicUnset";
  protected final String TEXT_796 = "(msgs);";
  protected final String TEXT_797 = NL + "\t\t\t\t\treturn basicSet";
  protected final String TEXT_798 = "(null, msgs);";
  protected final String TEXT_799 = NL + "\t\t\t\tdefault:" + NL + "\t\t\t\t\treturn eDynamicInverseRemove(otherEnd, featureID, baseClass, msgs);" + NL + "\t\t\t}" + NL + "\t\t}" + NL + "\t\treturn eBasicSetContainer(null, featureID, msgs);" + NL + "\t}" + NL;
  protected final String TEXT_800 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */" + NL + "\tpublic ";
  protected final String TEXT_801 = " eBasicRemoveFromContainer(";
  protected final String TEXT_802 = " msgs)" + NL + "\t{" + NL + "\t\tif (eContainerFeatureID >= 0)" + NL + "\t\t{" + NL + "\t\t\tswitch (eContainerFeatureID)" + NL + "\t\t\t{";
  protected final String TEXT_803 = NL + "\t\t\t\tcase ";
  protected final String TEXT_804 = ":" + NL + "\t\t\t\t\treturn eInternalContainer().eInverseRemove(this, ";
  protected final String TEXT_805 = ", ";
  protected final String TEXT_806 = ".class, msgs);";
  protected final String TEXT_807 = NL + "\t\t\t\tdefault:" + NL + "\t\t\t\t\treturn eDynamicBasicRemoveFromContainer(msgs);" + NL + "\t\t\t}" + NL + "\t\t}" + NL + "\t\treturn eInternalContainer().eInverseRemove(this, EOPPOSITE_FEATURE_BASE - eContainerFeatureID, null, msgs);" + NL + "\t}" + NL;
  protected final String TEXT_808 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */" + NL + "\tpublic Object eGet(int featureID, boolean resolve, boolean coreType)" + NL + "\t{" + NL + "\t\tswitch (featureID)" + NL + "\t\t{";
  protected final String TEXT_809 = NL + "\t\t\tcase ";
  protected final String TEXT_810 = ":";
  protected final String TEXT_811 = NL + "\t\t\t\treturn ";
  protected final String TEXT_812 = "() ? Boolean.TRUE : Boolean.FALSE;";
  protected final String TEXT_813 = NL + "\t\t\t\treturn new ";
  protected final String TEXT_814 = "(";
  protected final String TEXT_815 = "());";
  protected final String TEXT_816 = NL + "\t\t\t\tif (resolve) return ";
  protected final String TEXT_817 = "();" + NL + "\t\t\t\treturn basicGet";
  protected final String TEXT_818 = "();";
  protected final String TEXT_819 = NL + "\t\t\t\tif (coreType) return ((";
  protected final String TEXT_820 = ".InternalMapView)";
  protected final String TEXT_821 = "()).eMap();" + NL + "\t\t\t\telse return ";
  protected final String TEXT_822 = "();";
  protected final String TEXT_823 = NL + "\t\t\t\tif (coreType) return ";
  protected final String TEXT_824 = "();" + NL + "\t\t\t\telse return ";
  protected final String TEXT_825 = "().map();";
  protected final String TEXT_826 = NL + "\t\t\t\tif (coreType) return ((";
  protected final String TEXT_827 = ".Internal.Wrapper)";
  protected final String TEXT_828 = "()).featureMap();" + NL + "\t\t\t\treturn ";
  protected final String TEXT_829 = "();";
  protected final String TEXT_830 = NL + "\t\t\t\tif (coreType) return ";
  protected final String TEXT_831 = "();" + NL + "\t\t\t\treturn ((";
  protected final String TEXT_832 = ".Internal)";
  protected final String TEXT_833 = "()).getWrapper();";
  protected final String TEXT_834 = NL + "\t\t\t\treturn ";
  protected final String TEXT_835 = "();";
  protected final String TEXT_836 = NL + "\t\t}" + NL + "\t\treturn eDynamicGet(featureID, resolve, coreType);" + NL + "\t}" + NL;
  protected final String TEXT_837 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */" + NL + "\tpublic void eSet(int featureID, Object newValue)" + NL + "\t{" + NL + "\t\tswitch (featureID)" + NL + "\t\t{";
  protected final String TEXT_838 = NL + "\t\t\tcase ";
  protected final String TEXT_839 = ":";
  protected final String TEXT_840 = NL + "\t\t\t\t((";
  protected final String TEXT_841 = ".Internal)((";
  protected final String TEXT_842 = ".Internal.Wrapper)";
  protected final String TEXT_843 = "()).featureMap()).set(newValue);";
  protected final String TEXT_844 = NL + "\t\t\t\t((";
  protected final String TEXT_845 = ".Internal)";
  protected final String TEXT_846 = "()).set(newValue);";
  protected final String TEXT_847 = NL + "\t\t\t\t((";
  protected final String TEXT_848 = ".Setting)((";
  protected final String TEXT_849 = ".InternalMapView)";
  protected final String TEXT_850 = "()).eMap()).set(newValue);";
  protected final String TEXT_851 = NL + "\t\t\t\t((";
  protected final String TEXT_852 = ".Setting)";
  protected final String TEXT_853 = "()).set(newValue);";
  protected final String TEXT_854 = NL + "\t\t\t\t";
  protected final String TEXT_855 = "().clear();" + NL + "\t\t\t\t";
  protected final String TEXT_856 = "().addAll((";
  protected final String TEXT_857 = ")newValue);";
  protected final String TEXT_858 = NL + "\t\t\t\tset";
  protected final String TEXT_859 = "(((";
  protected final String TEXT_860 = ")newValue).";
  protected final String TEXT_861 = "());";
  protected final String TEXT_862 = NL + "\t\t\t\tset";
  protected final String TEXT_863 = "((";
  protected final String TEXT_864 = ")newValue);";
  protected final String TEXT_865 = NL + "\t\t\t\treturn;";
  protected final String TEXT_866 = NL + "\t\t}" + NL + "\t\teDynamicSet(featureID, newValue);" + NL + "\t}" + NL + "" + NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */" + NL + "\tpublic void eUnset(int featureID)" + NL + "\t{" + NL + "\t\tswitch (featureID)" + NL + "\t\t{";
  protected final String TEXT_867 = NL + "\t\t\tcase ";
  protected final String TEXT_868 = ":";
  protected final String TEXT_869 = NL + "\t\t\t\t((";
  protected final String TEXT_870 = ".Internal.Wrapper)";
  protected final String TEXT_871 = "()).featureMap().clear();";
  protected final String TEXT_872 = NL + "\t\t\t\t";
  protected final String TEXT_873 = "().clear();";
  protected final String TEXT_874 = NL + "\t\t\t\tunset";
  protected final String TEXT_875 = "();";
  protected final String TEXT_876 = NL + "\t\t\t\tset";
  protected final String TEXT_877 = "((";
  protected final String TEXT_878 = ")null);";
  protected final String TEXT_879 = NL + "\t\t\t\tset";
  protected final String TEXT_880 = "(";
  protected final String TEXT_881 = "_EDEFAULT);";
  protected final String TEXT_882 = NL + "\t\t\t\treturn;";
  protected final String TEXT_883 = NL + "\t\t}" + NL + "\t\teDynamicUnset(featureID);" + NL + "\t}" + NL;
  protected final String TEXT_884 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */" + NL + "\tpublic boolean eIsSet(int featureID)" + NL + "\t{" + NL + "\t\tswitch (featureID)" + NL + "\t\t{";
  protected final String TEXT_885 = NL + "\t\t\tcase ";
  protected final String TEXT_886 = ":";
  protected final String TEXT_887 = NL + "\t\t\t\treturn !((";
  protected final String TEXT_888 = ".Internal.Wrapper)";
  protected final String TEXT_889 = "()).featureMap().isEmpty();";
  protected final String TEXT_890 = NL + "\t\t\t\treturn ";
  protected final String TEXT_891 = " != null && !";
  protected final String TEXT_892 = ".featureMap().isEmpty();";
  protected final String TEXT_893 = NL + "\t\t\t\treturn ";
  protected final String TEXT_894 = " != null && !";
  protected final String TEXT_895 = ".isEmpty();";
  protected final String TEXT_896 = NL + "\t\t\t\t";
  protected final String TEXT_897 = " ";
  protected final String TEXT_898 = " = (";
  protected final String TEXT_899 = ")eVirtualGet(";
  protected final String TEXT_900 = ");" + NL + "\t\t\t\treturn ";
  protected final String TEXT_901 = " != null && !";
  protected final String TEXT_902 = ".isEmpty();";
  protected final String TEXT_903 = NL + "\t\t\t\treturn !";
  protected final String TEXT_904 = "().isEmpty();";
  protected final String TEXT_905 = NL + "\t\t\t\treturn isSet";
  protected final String TEXT_906 = "();";
  protected final String TEXT_907 = NL + "\t\t\t\treturn ";
  protected final String TEXT_908 = " != null;";
  protected final String TEXT_909 = NL + "\t\t\t\treturn eVirtualGet(";
  protected final String TEXT_910 = ") != null;";
  protected final String TEXT_911 = NL + "\t\t\t\treturn basicGet";
  protected final String TEXT_912 = "() != null;";
  protected final String TEXT_913 = NL + "\t\t\t\treturn ";
  protected final String TEXT_914 = " != null;";
  protected final String TEXT_915 = NL + "\t\t\t\treturn eVirtualGet(";
  protected final String TEXT_916 = ") != null;";
  protected final String TEXT_917 = NL + "\t\t\t\treturn ";
  protected final String TEXT_918 = "() != null;";
  protected final String TEXT_919 = NL + "\t\t\t\treturn ((";
  protected final String TEXT_920 = " & ";
  protected final String TEXT_921 = "_EFLAG) != 0) != ";
  protected final String TEXT_922 = "_EDEFAULT;";
  protected final String TEXT_923 = NL + "\t\t\t\treturn ";
  protected final String TEXT_924 = " != ";
  protected final String TEXT_925 = "_EDEFAULT;";
  protected final String TEXT_926 = NL + "\t\t\t\treturn eVirtualGet(";
  protected final String TEXT_927 = ", ";
  protected final String TEXT_928 = "_EDEFAULT) != ";
  protected final String TEXT_929 = "_EDEFAULT;";
  protected final String TEXT_930 = NL + "\t\t\t\treturn ";
  protected final String TEXT_931 = "() != ";
  protected final String TEXT_932 = "_EDEFAULT;";
  protected final String TEXT_933 = NL + "\t\t\t\treturn ";
  protected final String TEXT_934 = "_EDEFAULT == null ? ";
  protected final String TEXT_935 = " != null : !";
  protected final String TEXT_936 = "_EDEFAULT.equals(";
  protected final String TEXT_937 = ");";
  protected final String TEXT_938 = NL + "\t\t\t\t";
  protected final String TEXT_939 = " ";
  protected final String TEXT_940 = " = (";
  protected final String TEXT_941 = ")eVirtualGet(";
  protected final String TEXT_942 = ", ";
  protected final String TEXT_943 = "_EDEFAULT);" + NL + "\t\t\t\treturn ";
  protected final String TEXT_944 = "_EDEFAULT == null ? ";
  protected final String TEXT_945 = " != null : !";
  protected final String TEXT_946 = "_EDEFAULT.equals(";
  protected final String TEXT_947 = ");";
  protected final String TEXT_948 = NL + "\t\t\t\treturn ";
  protected final String TEXT_949 = "_EDEFAULT == null ? ";
  protected final String TEXT_950 = "() != null : !";
  protected final String TEXT_951 = "_EDEFAULT.equals(";
  protected final String TEXT_952 = "());";
  protected final String TEXT_953 = NL + "\t\t}" + NL + "\t\treturn eDynamicIsSet(featureID);" + NL + "\t}" + NL;
  protected final String TEXT_954 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */" + NL + "\tpublic int eBaseStructuralFeatureID(int derivedFeatureID, Class baseClass)" + NL + "\t{";
  protected final String TEXT_955 = NL + "\t\tif (baseClass == ";
  protected final String TEXT_956 = ".class)" + NL + "\t\t{" + NL + "\t\t\tswitch (derivedFeatureID)" + NL + "\t\t\t{";
  protected final String TEXT_957 = NL + "\t\t\t\tcase ";
  protected final String TEXT_958 = ": return ";
  protected final String TEXT_959 = ";";
  protected final String TEXT_960 = NL + "\t\t\t\tdefault: return -1;" + NL + "\t\t\t}" + NL + "\t\t}";
  protected final String TEXT_961 = NL + "\t\treturn super.eBaseStructuralFeatureID(derivedFeatureID, baseClass);" + NL + "\t}" + NL + "" + NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */" + NL + "\tpublic int eDerivedStructuralFeatureID(int baseFeatureID, Class baseClass)" + NL + "\t{";
  protected final String TEXT_962 = NL + "\t\tif (baseClass == ";
  protected final String TEXT_963 = ".class)" + NL + "\t\t{" + NL + "\t\t\tswitch (baseFeatureID)" + NL + "\t\t\t{";
  protected final String TEXT_964 = NL + "\t\t\t\tcase ";
  protected final String TEXT_965 = ": return ";
  protected final String TEXT_966 = ";";
  protected final String TEXT_967 = NL + "\t\t\t\tdefault: return -1;" + NL + "\t\t\t}" + NL + "\t\t}";
  protected final String TEXT_968 = NL + "\t\treturn super.eDerivedStructuralFeatureID(baseFeatureID, baseClass);" + NL + "\t}" + NL;
  protected final String TEXT_969 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */" + NL + "\tprotected Object[] eVirtualValues()" + NL + "\t{" + NL + "\t\treturn ";
  protected final String TEXT_970 = ";" + NL + "\t}" + NL + "" + NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */" + NL + "\tprotected void eSetVirtualValues(Object[] newValues)" + NL + "\t{" + NL + "\t\t";
  protected final String TEXT_971 = " = newValues;" + NL + "\t}" + NL;
  protected final String TEXT_972 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */" + NL + "\tprotected int eVirtualIndexBits(int offset)" + NL + "\t{" + NL + "\t\tswitch (offset)" + NL + "\t\t{";
  protected final String TEXT_973 = NL + "\t\t\tcase ";
  protected final String TEXT_974 = " :" + NL + "\t\t\t\treturn ";
  protected final String TEXT_975 = ";";
  protected final String TEXT_976 = NL + "\t\t\tdefault :" + NL + "\t\t\t\tthrow new IndexOutOfBoundsException();" + NL + "\t\t}" + NL + "\t}" + NL + "" + NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */" + NL + "\tprotected void eSetVirtualIndexBits(int offset, int newIndexBits)" + NL + "\t{" + NL + "\t\tswitch (offset)" + NL + "\t\t{";
  protected final String TEXT_977 = NL + "\t\t\tcase ";
  protected final String TEXT_978 = " :" + NL + "\t\t\t\t";
  protected final String TEXT_979 = " = newIndexBits;" + NL + "\t\t\t\tbreak;";
  protected final String TEXT_980 = NL + "\t\t\tdefault :" + NL + "\t\t\t\tthrow new IndexOutOfBoundsException();" + NL + "\t\t}" + NL + "\t}" + NL;
  protected final String TEXT_981 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */" + NL + "\tpublic String toString()" + NL + "\t{" + NL + "\t\tif (eIsProxy()) return super.toString();" + NL + "" + NL + "\t\tStringBuffer result = new StringBuffer(super.toString());";
  protected final String TEXT_982 = NL + "\t\tresult.append(\" (";
  protected final String TEXT_983 = ": \");";
  protected final String TEXT_984 = NL + "\t\tresult.append(\", ";
  protected final String TEXT_985 = ": \");";
  protected final String TEXT_986 = NL + "\t\tif (eVirtualIsSet(";
  protected final String TEXT_987 = ")) result.append(eVirtualGet(";
  protected final String TEXT_988 = ")); else result.append(\"<unset>\");";
  protected final String TEXT_989 = NL + "\t\tif (";
  protected final String TEXT_990 = "(";
  protected final String TEXT_991 = " & ";
  protected final String TEXT_992 = "_ESETFLAG) != 0";
  protected final String TEXT_993 = "ESet";
  protected final String TEXT_994 = ") result.append((";
  protected final String TEXT_995 = " & ";
  protected final String TEXT_996 = "_EFLAG) != 0); else result.append(\"<unset>\");";
  protected final String TEXT_997 = NL + "\t\tif (";
  protected final String TEXT_998 = "(";
  protected final String TEXT_999 = " & ";
  protected final String TEXT_1000 = "_ESETFLAG) != 0";
  protected final String TEXT_1001 = "ESet";
  protected final String TEXT_1002 = ") result.append(";
  protected final String TEXT_1003 = "); else result.append(\"<unset>\");";
  protected final String TEXT_1004 = NL + "\t\tresult.append(eVirtualGet(";
  protected final String TEXT_1005 = ", ";
  protected final String TEXT_1006 = "_EDEFAULT";
  protected final String TEXT_1007 = "));";
  protected final String TEXT_1008 = NL + "\t\tresult.append((";
  protected final String TEXT_1009 = " & ";
  protected final String TEXT_1010 = "_EFLAG) != 0);";
  protected final String TEXT_1011 = NL + "\t\tresult.append(";
  protected final String TEXT_1012 = ");";
  protected final String TEXT_1013 = NL + "\t\tresult.append(')');" + NL + "\t\treturn result.toString();" + NL + "\t}" + NL;
  protected final String TEXT_1014 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */" + NL + "\tprotected int hash = -1;" + NL + "" + NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */" + NL + "\tpublic int getHash()" + NL + "\t{" + NL + "\t\tif (hash == -1)" + NL + "\t\t{" + NL + "\t\t\tObject theKey = getKey();" + NL + "\t\t\thash = (theKey == null ? 0 : theKey.hashCode());" + NL + "\t\t}" + NL + "\t\treturn hash;" + NL + "\t}" + NL + "" + NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */" + NL + "\tpublic void setHash(int hash)" + NL + "\t{" + NL + "\t\tthis.hash = hash;" + NL + "\t}" + NL + "" + NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */" + NL + "\tpublic Object getKey()" + NL + "\t{";
  protected final String TEXT_1015 = NL + "\t\treturn new ";
  protected final String TEXT_1016 = "(getTypedKey());";
  protected final String TEXT_1017 = NL + "\t\treturn getTypedKey();";
  protected final String TEXT_1018 = NL + "\t}" + NL + "" + NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */" + NL + "\tpublic void setKey(Object key)" + NL + "\t{";
  protected final String TEXT_1019 = NL + "\t\tgetTypedKey().addAll((";
  protected final String TEXT_1020 = ")key);";
  protected final String TEXT_1021 = NL + "\t\tsetTypedKey(((";
  protected final String TEXT_1022 = ")key).";
  protected final String TEXT_1023 = "());";
  protected final String TEXT_1024 = NL + "\t\tsetTypedKey((";
  protected final String TEXT_1025 = ")key);";
  protected final String TEXT_1026 = NL + "\t}" + NL + "" + NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */" + NL + "\tpublic Object getValue()" + NL + "\t{";
  protected final String TEXT_1027 = NL + "\t\treturn new ";
  protected final String TEXT_1028 = "(getTypedValue());";
  protected final String TEXT_1029 = NL + "\t\treturn getTypedValue();";
  protected final String TEXT_1030 = NL + "\t}" + NL + "" + NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */" + NL + "\tpublic Object setValue(Object value)" + NL + "\t{" + NL + "\t\tObject oldValue = getValue();";
  protected final String TEXT_1031 = NL + "\t\tgetTypedValue().clear();" + NL + "\t\tgetTypedValue().addAll((";
  protected final String TEXT_1032 = ")value);";
  protected final String TEXT_1033 = NL + "\t\tsetTypedValue(((";
  protected final String TEXT_1034 = ")value).";
  protected final String TEXT_1035 = "());";
  protected final String TEXT_1036 = NL + "\t\tsetTypedValue((";
  protected final String TEXT_1037 = ")value);";
  protected final String TEXT_1038 = NL + "\t\treturn oldValue;" + NL + "\t}" + NL + "" + NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */" + NL + "\tpublic ";
  protected final String TEXT_1039 = " getEMap()" + NL + "\t{" + NL + "\t\t";
  protected final String TEXT_1040 = " container = eContainer();" + NL + "\t\treturn container == null ? null : (";
  protected final String TEXT_1041 = ")container.eGet(eContainmentFeature());" + NL + "\t}" + NL;
  protected final String TEXT_1042 = NL + "} //";
  protected final String TEXT_1043 = NL;

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
    if (genFeature.isResolveProxies()) {
    if (genModel.isVirtualDelegation()) {
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
    if (!genFeature.isResolveProxies() && genModel.isVirtualDelegation() && !genFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_168);
    stringBuffer.append(genFeature.getImportedType());
    stringBuffer.append(TEXT_169);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    if (!genFeature.isReferenceType()) {
    stringBuffer.append(TEXT_170);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_171);
    }
    stringBuffer.append(TEXT_172);
    } else if (genClass.isFlag(genFeature)) {
    stringBuffer.append(TEXT_173);
    stringBuffer.append(genClass.getFlagsField(genFeature));
    stringBuffer.append(TEXT_174);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_175);
    } else {
    stringBuffer.append(TEXT_176);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_177);
    }
    }
    } else {//volatile
    if (genFeature.isResolveProxies() && !genFeature.isListType()) {
    stringBuffer.append(TEXT_178);
    stringBuffer.append(genFeature.getImportedType());
    stringBuffer.append(TEXT_179);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_180);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_181);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_182);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_183);
    stringBuffer.append(genFeature.getNonEObjectInternalTypeCast());
    stringBuffer.append(TEXT_184);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.InternalEObject"));
    stringBuffer.append(TEXT_185);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_186);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_187);
    } else if (genFeature.hasDelegateFeature()) { GenFeature delegateFeature = genFeature.getDelegateFeature();
    if (genFeature.isFeatureMapType()) {
    if (delegateFeature.isWrappedFeatureMapType()) {
    stringBuffer.append(TEXT_188);
    stringBuffer.append(genFeature.getImportedEffectiveFeatureMapWrapperClass());
    stringBuffer.append(TEXT_189);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.FeatureMap"));
    stringBuffer.append(TEXT_190);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.FeatureMap"));
    stringBuffer.append(TEXT_191);
    stringBuffer.append(delegateFeature.getAccessorName());
    stringBuffer.append(TEXT_192);
    stringBuffer.append(genFeature.getQualifiedFeatureAccessorName());
    stringBuffer.append(TEXT_193);
    } else {
    stringBuffer.append(TEXT_194);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.FeatureMap"));
    stringBuffer.append(TEXT_195);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.FeatureMap"));
    stringBuffer.append(TEXT_196);
    stringBuffer.append(delegateFeature.getAccessorName());
    stringBuffer.append(TEXT_197);
    stringBuffer.append(genFeature.getQualifiedFeatureAccessorName());
    stringBuffer.append(TEXT_198);
    }
    } else if (genFeature.isListType()) {
    if (delegateFeature.isWrappedFeatureMapType()) {
    stringBuffer.append(TEXT_199);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.FeatureMap"));
    stringBuffer.append(TEXT_200);
    stringBuffer.append(delegateFeature.getAccessorName());
    stringBuffer.append(TEXT_201);
    stringBuffer.append(genFeature.getQualifiedFeatureAccessorName());
    stringBuffer.append(TEXT_202);
    } else {
    stringBuffer.append(TEXT_203);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.FeatureMap"));
    stringBuffer.append(TEXT_204);
    stringBuffer.append(delegateFeature.getAccessorName());
    stringBuffer.append(TEXT_205);
    stringBuffer.append(genFeature.getQualifiedFeatureAccessorName());
    stringBuffer.append(TEXT_206);
    }
    } else {
    if (delegateFeature.isWrappedFeatureMapType()) {
    stringBuffer.append(TEXT_207);
    if (genFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_208);
    }
    stringBuffer.append(TEXT_209);
    stringBuffer.append(genFeature.getObjectType());
    stringBuffer.append(TEXT_210);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.FeatureMap"));
    stringBuffer.append(TEXT_211);
    stringBuffer.append(delegateFeature.getAccessorName());
    stringBuffer.append(TEXT_212);
    stringBuffer.append(genFeature.getQualifiedFeatureAccessorName());
    stringBuffer.append(TEXT_213);
    if (genFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_214);
    stringBuffer.append(genFeature.getPrimitiveValueFunction());
    stringBuffer.append(TEXT_215);
    }
    stringBuffer.append(TEXT_216);
    } else {
    stringBuffer.append(TEXT_217);
    if (genFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_218);
    }
    stringBuffer.append(TEXT_219);
    stringBuffer.append(genFeature.getObjectType());
    stringBuffer.append(TEXT_220);
    stringBuffer.append(delegateFeature.getAccessorName());
    stringBuffer.append(TEXT_221);
    stringBuffer.append(genFeature.getQualifiedFeatureAccessorName());
    stringBuffer.append(TEXT_222);
    if (genFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_223);
    stringBuffer.append(genFeature.getPrimitiveValueFunction());
    stringBuffer.append(TEXT_224);
    }
    stringBuffer.append(TEXT_225);
    }
    }
    } else {
    stringBuffer.append(TEXT_226);
    stringBuffer.append(genFeature.getFormattedName());
    stringBuffer.append(TEXT_227);
    stringBuffer.append(genFeature.getFeatureKind());
    stringBuffer.append(TEXT_228);
    //Class/getGenFeature.todo.override.javajetinc
    }
    }
    stringBuffer.append(TEXT_229);
    //Class/getGenFeature.override.javajetinc
    }
    if (!genModel.isReflectiveDelegation() && genFeature.isBasicGet()) {
    stringBuffer.append(TEXT_230);
    stringBuffer.append(genFeature.getImportedType());
    stringBuffer.append(TEXT_231);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_232);
    if (genFeature.isContainer()) {
    stringBuffer.append(TEXT_233);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(TEXT_234);
    stringBuffer.append(genFeature.getImportedType());
    stringBuffer.append(TEXT_235);
    } else if (!genFeature.isVolatile()) {
    if (genModel.isVirtualDelegation()) {
    stringBuffer.append(TEXT_236);
    stringBuffer.append(genFeature.getImportedType());
    stringBuffer.append(TEXT_237);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(TEXT_238);
    } else {
    stringBuffer.append(TEXT_239);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_240);
    }
    } else if (genFeature.hasDelegateFeature()) { GenFeature delegateFeature = genFeature.getDelegateFeature();
    if (delegateFeature.isWrappedFeatureMapType()) {
    stringBuffer.append(TEXT_241);
    stringBuffer.append(genFeature.getImportedType());
    stringBuffer.append(TEXT_242);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.FeatureMap"));
    stringBuffer.append(TEXT_243);
    stringBuffer.append(delegateFeature.getAccessorName());
    stringBuffer.append(TEXT_244);
    stringBuffer.append(genFeature.getQualifiedFeatureAccessorName());
    stringBuffer.append(TEXT_245);
    } else {
    stringBuffer.append(TEXT_246);
    stringBuffer.append(genFeature.getImportedType());
    stringBuffer.append(TEXT_247);
    stringBuffer.append(delegateFeature.getAccessorName());
    stringBuffer.append(TEXT_248);
    stringBuffer.append(genFeature.getQualifiedFeatureAccessorName());
    stringBuffer.append(TEXT_249);
    }
    } else {
    stringBuffer.append(TEXT_250);
    stringBuffer.append(genFeature.getFormattedName());
    stringBuffer.append(TEXT_251);
    stringBuffer.append(genFeature.getFeatureKind());
    stringBuffer.append(TEXT_252);
    //Class/basicGetGenFeature.todo.override.javajetinc
    }
    stringBuffer.append(TEXT_253);
    //Class/basicGetGenFeature.override.javajetinc
    }
    if (!genModel.isReflectiveDelegation() && genFeature.isBasicSet()) {
    stringBuffer.append(TEXT_254);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.notify.NotificationChain"));
    stringBuffer.append(TEXT_255);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_256);
    stringBuffer.append(genFeature.getImportedInternalType());
    stringBuffer.append(TEXT_257);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_258);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.notify.NotificationChain"));
    stringBuffer.append(TEXT_259);
    if (!genFeature.isVolatile()) {
    if (genModel.isVirtualDelegation()) {
    stringBuffer.append(TEXT_260);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_261);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(TEXT_262);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_263);
    } else {
    stringBuffer.append(TEXT_264);
    stringBuffer.append(genFeature.getImportedType());
    stringBuffer.append(TEXT_265);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_266);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_267);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_268);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_269);
    }
    if (genFeature.isUnsettable()) {
    if (genModel.isVirtualDelegation()) {
    stringBuffer.append(TEXT_270);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_271);
    } else if (genClass.isESetFlag(genFeature)) {
    stringBuffer.append(TEXT_272);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_273);
    stringBuffer.append(genClass.getESetFlagsField(genFeature));
    stringBuffer.append(TEXT_274);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_275);
    stringBuffer.append(genClass.getESetFlagsField(genFeature));
    stringBuffer.append(TEXT_276);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_277);
    } else {
    stringBuffer.append(TEXT_278);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_279);
    stringBuffer.append(genFeature.getUncapName());
    stringBuffer.append(TEXT_280);
    stringBuffer.append(genFeature.getUncapName());
    stringBuffer.append(TEXT_281);
    }
    }
    stringBuffer.append(TEXT_282);
    if (genFeature.isUnsettable()) {
    stringBuffer.append(TEXT_283);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.impl.ENotificationImpl"));
    stringBuffer.append(TEXT_284);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.impl.ENotificationImpl"));
    stringBuffer.append(TEXT_285);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.notify.Notification"));
    stringBuffer.append(TEXT_286);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(TEXT_287);
    if (genModel.isVirtualDelegation()) {
    stringBuffer.append(TEXT_288);
    stringBuffer.append(genFeature.getCapName());
    } else {
    stringBuffer.append(TEXT_289);
    stringBuffer.append(genFeature.getCapName());
    }
    stringBuffer.append(TEXT_290);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_291);
    if (genModel.isVirtualDelegation()) {
    stringBuffer.append(TEXT_292);
    } else {
    stringBuffer.append(TEXT_293);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_294);
    }
    stringBuffer.append(TEXT_295);
    } else {
    stringBuffer.append(TEXT_296);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.impl.ENotificationImpl"));
    stringBuffer.append(TEXT_297);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.impl.ENotificationImpl"));
    stringBuffer.append(TEXT_298);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.notify.Notification"));
    stringBuffer.append(TEXT_299);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(TEXT_300);
    if (genModel.isVirtualDelegation()) {
    stringBuffer.append(TEXT_301);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_302);
    stringBuffer.append(genFeature.getCapName());
    } else {
    stringBuffer.append(TEXT_303);
    stringBuffer.append(genFeature.getCapName());
    }
    stringBuffer.append(TEXT_304);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_305);
    }
    stringBuffer.append(TEXT_306);
    stringBuffer.append(TEXT_307);
    } else if (genFeature.hasDelegateFeature()) { GenFeature delegateFeature = genFeature.getDelegateFeature();
    if (delegateFeature.isWrappedFeatureMapType()) {
    stringBuffer.append(TEXT_308);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.FeatureMap"));
    stringBuffer.append(TEXT_309);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.FeatureMap"));
    stringBuffer.append(TEXT_310);
    stringBuffer.append(delegateFeature.getAccessorName());
    stringBuffer.append(TEXT_311);
    stringBuffer.append(genFeature.getQualifiedFeatureAccessorName());
    stringBuffer.append(TEXT_312);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_313);
    } else {
    stringBuffer.append(TEXT_314);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.FeatureMap"));
    stringBuffer.append(TEXT_315);
    stringBuffer.append(delegateFeature.getAccessorName());
    stringBuffer.append(TEXT_316);
    stringBuffer.append(genFeature.getQualifiedFeatureAccessorName());
    stringBuffer.append(TEXT_317);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_318);
    }
    } else {
    stringBuffer.append(TEXT_319);
    stringBuffer.append(genFeature.getFormattedName());
    stringBuffer.append(TEXT_320);
    stringBuffer.append(genFeature.getFeatureKind());
    stringBuffer.append(TEXT_321);
    //Class/basicSetGenFeature.todo.override.javajetinc
    }
    stringBuffer.append(TEXT_322);
    //Class/basicSetGenFeature.override.javajetinc
    }
    if (genFeature.isSet()) {
    stringBuffer.append(TEXT_323);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_324);
    stringBuffer.append(genFeature.getImportedType());
    stringBuffer.append(TEXT_325);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_326);
    if (genModel.isReflectiveDelegation()) {
    stringBuffer.append(TEXT_327);
    stringBuffer.append(genFeature.getGenPackage().getImportedPackageInterfaceName());
    stringBuffer.append(TEXT_328);
    stringBuffer.append(genFeature.getFeatureAccessorName());
    stringBuffer.append(TEXT_329);
    if (genFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_330);
    stringBuffer.append(genFeature.getObjectType());
    stringBuffer.append(TEXT_331);
    }
    stringBuffer.append(TEXT_332);
    stringBuffer.append(genFeature.getCapName());
    if (genFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_333);
    }
    stringBuffer.append(TEXT_334);
    } else if (!genFeature.isVolatile()) {
    if (genFeature.isContainer()) { GenFeature reverseFeature = genFeature.getReverse(); GenClass targetClass = reverseFeature.getGenClass();
    stringBuffer.append(TEXT_335);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_336);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(TEXT_337);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_338);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.EcoreUtil"));
    stringBuffer.append(TEXT_339);
    stringBuffer.append(genFeature.getEObjectCast());
    stringBuffer.append(TEXT_340);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_341);
    stringBuffer.append(genModel.getImportedName("java.lang.IllegalArgumentException"));
    stringBuffer.append(TEXT_342);
    stringBuffer.append(genModel.getNonNLS());
    stringBuffer.append(TEXT_343);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.notify.NotificationChain"));
    stringBuffer.append(TEXT_344);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_345);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.InternalEObject"));
    stringBuffer.append(TEXT_346);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_347);
    stringBuffer.append(targetClass.getQualifiedFeatureID(reverseFeature));
    stringBuffer.append(TEXT_348);
    stringBuffer.append(targetClass.getImportedInterfaceName());
    stringBuffer.append(TEXT_349);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.InternalEObject"));
    stringBuffer.append(TEXT_350);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_351);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(TEXT_352);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.impl.ENotificationImpl"));
    stringBuffer.append(TEXT_353);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.notify.Notification"));
    stringBuffer.append(TEXT_354);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(TEXT_355);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_356);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_357);
    } else if (genFeature.isBidirectional() || genFeature.isContains()) {
    if (genModel.isVirtualDelegation()) {
    stringBuffer.append(TEXT_358);
    stringBuffer.append(genFeature.getImportedType());
    stringBuffer.append(TEXT_359);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_360);
    stringBuffer.append(genFeature.getImportedType());
    stringBuffer.append(TEXT_361);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(TEXT_362);
    }
    stringBuffer.append(TEXT_363);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_364);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_365);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.notify.NotificationChain"));
    stringBuffer.append(TEXT_366);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_367);
    if (!genFeature.isBidirectional()) {
    stringBuffer.append(TEXT_368);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.InternalEObject"));
    stringBuffer.append(TEXT_369);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_370);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(TEXT_371);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_372);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.InternalEObject"));
    stringBuffer.append(TEXT_373);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_374);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(TEXT_375);
    } else { GenFeature reverseFeature = genFeature.getReverse(); GenClass targetClass = reverseFeature.getGenClass();
    stringBuffer.append(TEXT_376);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.InternalEObject"));
    stringBuffer.append(TEXT_377);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_378);
    stringBuffer.append(targetClass.getQualifiedFeatureID(reverseFeature));
    stringBuffer.append(TEXT_379);
    stringBuffer.append(targetClass.getImportedInterfaceName());
    stringBuffer.append(TEXT_380);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_381);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.InternalEObject"));
    stringBuffer.append(TEXT_382);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_383);
    stringBuffer.append(targetClass.getQualifiedFeatureID(reverseFeature));
    stringBuffer.append(TEXT_384);
    stringBuffer.append(targetClass.getImportedInterfaceName());
    stringBuffer.append(TEXT_385);
    }
    stringBuffer.append(TEXT_386);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_387);
    stringBuffer.append(genFeature.getInternalTypeCast());
    stringBuffer.append(TEXT_388);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_389);
    if (genFeature.isUnsettable()) {
    stringBuffer.append(TEXT_390);
    if (genModel.isVirtualDelegation()) {
    stringBuffer.append(TEXT_391);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_392);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(TEXT_393);
    } else if (genClass.isESetFlag(genFeature)) {
    stringBuffer.append(TEXT_394);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_395);
    stringBuffer.append(genClass.getESetFlagsField(genFeature));
    stringBuffer.append(TEXT_396);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_397);
    stringBuffer.append(genClass.getESetFlagsField(genFeature));
    stringBuffer.append(TEXT_398);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_399);
    } else {
    stringBuffer.append(TEXT_400);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_401);
    stringBuffer.append(genFeature.getUncapName());
    stringBuffer.append(TEXT_402);
    stringBuffer.append(genFeature.getUncapName());
    stringBuffer.append(TEXT_403);
    }
    stringBuffer.append(TEXT_404);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.impl.ENotificationImpl"));
    stringBuffer.append(TEXT_405);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.notify.Notification"));
    stringBuffer.append(TEXT_406);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(TEXT_407);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_408);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_409);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_410);
    } else {
    stringBuffer.append(TEXT_411);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.impl.ENotificationImpl"));
    stringBuffer.append(TEXT_412);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.notify.Notification"));
    stringBuffer.append(TEXT_413);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(TEXT_414);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_415);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_416);
    }
    } else {
    if (genClass.isFlag(genFeature)) {
    stringBuffer.append(TEXT_417);
    stringBuffer.append(genFeature.getImportedType());
    stringBuffer.append(TEXT_418);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_419);
    stringBuffer.append(genClass.getFlagsField(genFeature));
    stringBuffer.append(TEXT_420);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_421);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_422);
    stringBuffer.append(genClass.getFlagsField(genFeature));
    stringBuffer.append(TEXT_423);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_424);
    stringBuffer.append(genClass.getFlagsField(genFeature));
    stringBuffer.append(TEXT_425);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_426);
    } else {
    if (!genModel.isVirtualDelegation() || genFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_427);
    stringBuffer.append(genFeature.getImportedType());
    stringBuffer.append(TEXT_428);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_429);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_430);
    }
    if (genFeature.isEnumType()) {
    if (genModel.isVirtualDelegation()) {
    stringBuffer.append(TEXT_431);
    stringBuffer.append(genFeature.getImportedType());
    stringBuffer.append(TEXT_432);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_433);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_434);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_435);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_436);
    } else {
    stringBuffer.append(TEXT_437);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_438);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_439);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_440);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_441);
    }
    } else {
    if (genModel.isVirtualDelegation() && !genFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_442);
    stringBuffer.append(genFeature.getImportedType());
    stringBuffer.append(TEXT_443);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_444);
    stringBuffer.append(genFeature.getInternalTypeCast());
    stringBuffer.append(TEXT_445);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_446);
    } else {
    stringBuffer.append(TEXT_447);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_448);
    stringBuffer.append(genFeature.getInternalTypeCast());
    stringBuffer.append(TEXT_449);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_450);
    }
    }
    if (genModel.isVirtualDelegation() && !genFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_451);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_452);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(TEXT_453);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_454);
    }
    }
    if (genFeature.isUnsettable()) {
    if (genModel.isVirtualDelegation() && !genFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_455);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_456);
    } else if (genClass.isESetFlag(genFeature)) {
    stringBuffer.append(TEXT_457);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_458);
    stringBuffer.append(genClass.getESetFlagsField(genFeature));
    stringBuffer.append(TEXT_459);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_460);
    stringBuffer.append(genClass.getESetFlagsField(genFeature));
    stringBuffer.append(TEXT_461);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_462);
    } else {
    stringBuffer.append(TEXT_463);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_464);
    stringBuffer.append(genFeature.getUncapName());
    stringBuffer.append(TEXT_465);
    stringBuffer.append(genFeature.getUncapName());
    stringBuffer.append(TEXT_466);
    }
    stringBuffer.append(TEXT_467);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.impl.ENotificationImpl"));
    stringBuffer.append(TEXT_468);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.notify.Notification"));
    stringBuffer.append(TEXT_469);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(TEXT_470);
    if (genModel.isVirtualDelegation() && !genFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_471);
    if (genFeature.isReferenceType()) {
    stringBuffer.append(TEXT_472);
    } else {
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_473);
    }
    stringBuffer.append(TEXT_474);
    stringBuffer.append(genFeature.getCapName());
    } else {
    stringBuffer.append(TEXT_475);
    stringBuffer.append(genFeature.getCapName());
    }
    stringBuffer.append(TEXT_476);
    if (genClass.isFlag(genFeature)) {
    stringBuffer.append(TEXT_477);
    stringBuffer.append(genFeature.getCapName());
    } else {
    stringBuffer.append(genFeature.getSafeName());
    }
    stringBuffer.append(TEXT_478);
    if (genModel.isVirtualDelegation() && !genFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_479);
    } else {
    stringBuffer.append(TEXT_480);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_481);
    }
    stringBuffer.append(TEXT_482);
    } else {
    stringBuffer.append(TEXT_483);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.impl.ENotificationImpl"));
    stringBuffer.append(TEXT_484);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.notify.Notification"));
    stringBuffer.append(TEXT_485);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(TEXT_486);
    if (genModel.isVirtualDelegation() && !genFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_487);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_488);
    if (genFeature.isReferenceType()) {
    stringBuffer.append(TEXT_489);
    } else {
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_490);
    }
    stringBuffer.append(TEXT_491);
    stringBuffer.append(genFeature.getCapName());
    } else {
    stringBuffer.append(TEXT_492);
    stringBuffer.append(genFeature.getCapName());
    }
    stringBuffer.append(TEXT_493);
    if (genClass.isFlag(genFeature)) {
    stringBuffer.append(TEXT_494);
    stringBuffer.append(genFeature.getCapName());
    } else {
    stringBuffer.append(genFeature.getSafeName());
    }
    stringBuffer.append(TEXT_495);
    }
    }
    } else if (genFeature.hasDelegateFeature()) { GenFeature delegateFeature = genFeature.getDelegateFeature();
    if (delegateFeature.isWrappedFeatureMapType()) {
    stringBuffer.append(TEXT_496);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.FeatureMap"));
    stringBuffer.append(TEXT_497);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.FeatureMap"));
    stringBuffer.append(TEXT_498);
    stringBuffer.append(delegateFeature.getAccessorName());
    stringBuffer.append(TEXT_499);
    stringBuffer.append(genFeature.getQualifiedFeatureAccessorName());
    stringBuffer.append(TEXT_500);
    if (genFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_501);
    stringBuffer.append(genFeature.getObjectType());
    stringBuffer.append(TEXT_502);
    }
    stringBuffer.append(TEXT_503);
    stringBuffer.append(genFeature.getCapName());
    if (genFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_504);
    }
    stringBuffer.append(TEXT_505);
    } else {
    stringBuffer.append(TEXT_506);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.FeatureMap"));
    stringBuffer.append(TEXT_507);
    stringBuffer.append(delegateFeature.getAccessorName());
    stringBuffer.append(TEXT_508);
    stringBuffer.append(genFeature.getQualifiedFeatureAccessorName());
    stringBuffer.append(TEXT_509);
    if (genFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_510);
    stringBuffer.append(genFeature.getObjectType());
    stringBuffer.append(TEXT_511);
    }
    stringBuffer.append(TEXT_512);
    stringBuffer.append(genFeature.getCapName());
    if (genFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_513);
    }
    stringBuffer.append(TEXT_514);
    }
    } else {
    stringBuffer.append(TEXT_515);
    stringBuffer.append(genFeature.getFormattedName());
    stringBuffer.append(TEXT_516);
    stringBuffer.append(genFeature.getFeatureKind());
    stringBuffer.append(TEXT_517);
    //Class/setGenFeature.todo.override.javajetinc
    }
    stringBuffer.append(TEXT_518);
    //Class/setGenFeature.override.javajetinc
    }
    if (!genModel.isReflectiveDelegation() && genFeature.isBasicUnset()) {
    stringBuffer.append(TEXT_519);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.notify.NotificationChain"));
    stringBuffer.append(TEXT_520);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_521);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.notify.NotificationChain"));
    stringBuffer.append(TEXT_522);
    if (!genFeature.isVolatile()) {
    if (genModel.isVirtualDelegation()) {
    stringBuffer.append(TEXT_523);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_524);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(TEXT_525);
    } else {
    stringBuffer.append(TEXT_526);
    stringBuffer.append(genFeature.getImportedType());
    stringBuffer.append(TEXT_527);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_528);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_529);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_530);
    }
    if (genModel.isVirtualDelegation()) {
    stringBuffer.append(TEXT_531);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_532);
    } else if (genClass.isESetFlag(genFeature)) {
    stringBuffer.append(TEXT_533);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_534);
    stringBuffer.append(genClass.getESetFlagsField(genFeature));
    stringBuffer.append(TEXT_535);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_536);
    stringBuffer.append(genClass.getESetFlagsField(genFeature));
    stringBuffer.append(TEXT_537);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_538);
    } else {
    stringBuffer.append(TEXT_539);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_540);
    stringBuffer.append(genFeature.getUncapName());
    stringBuffer.append(TEXT_541);
    stringBuffer.append(genFeature.getUncapName());
    stringBuffer.append(TEXT_542);
    }
    stringBuffer.append(TEXT_543);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.impl.ENotificationImpl"));
    stringBuffer.append(TEXT_544);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.impl.ENotificationImpl"));
    stringBuffer.append(TEXT_545);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.notify.Notification"));
    stringBuffer.append(TEXT_546);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(TEXT_547);
    if (genModel.isVirtualDelegation()) {
    stringBuffer.append(TEXT_548);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_549);
    } else {
    stringBuffer.append(TEXT_550);
    stringBuffer.append(genFeature.getCapName());
    }
    stringBuffer.append(TEXT_551);
    if (genModel.isVirtualDelegation()) {
    stringBuffer.append(TEXT_552);
    } else {
    stringBuffer.append(TEXT_553);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_554);
    }
    stringBuffer.append(TEXT_555);
    } else {
    stringBuffer.append(TEXT_556);
    stringBuffer.append(genFeature.getFormattedName());
    stringBuffer.append(TEXT_557);
    stringBuffer.append(genFeature.getFeatureKind());
    stringBuffer.append(TEXT_558);
    //Class/basicUnsetGenFeature.todo.override.javajetinc
    }
    stringBuffer.append(TEXT_559);
    //Class.basicUnsetGenFeature.override.javajetinc
    }
    if (genFeature.isUnset()) {
    stringBuffer.append(TEXT_560);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_561);
    if (genModel.isReflectiveDelegation()) {
    stringBuffer.append(TEXT_562);
    stringBuffer.append(genFeature.getGenPackage().getImportedPackageInterfaceName());
    stringBuffer.append(TEXT_563);
    stringBuffer.append(genFeature.getFeatureAccessorName());
    stringBuffer.append(TEXT_564);
    } else if (!genFeature.isVolatile()) {
    if (genFeature.isListType()) {
    stringBuffer.append(TEXT_565);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.InternalEList"));
    stringBuffer.append(TEXT_566);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_567);
    } else if (genFeature.isBidirectional() || genFeature.isContains()) {
    if (genModel.isVirtualDelegation()) {
    stringBuffer.append(TEXT_568);
    stringBuffer.append(genFeature.getImportedType());
    stringBuffer.append(TEXT_569);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_570);
    stringBuffer.append(genFeature.getImportedType());
    stringBuffer.append(TEXT_571);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(TEXT_572);
    }
    stringBuffer.append(TEXT_573);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_574);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.notify.NotificationChain"));
    stringBuffer.append(TEXT_575);
    if (!genFeature.isBidirectional()) {
    stringBuffer.append(TEXT_576);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.InternalEObject"));
    stringBuffer.append(TEXT_577);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_578);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(TEXT_579);
    } else { GenFeature reverseFeature = genFeature.getReverse(); GenClass targetClass = reverseFeature.getGenClass();
    stringBuffer.append(TEXT_580);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.InternalEObject"));
    stringBuffer.append(TEXT_581);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_582);
    stringBuffer.append(targetClass.getQualifiedFeatureID(reverseFeature));
    stringBuffer.append(TEXT_583);
    stringBuffer.append(targetClass.getImportedInterfaceName());
    stringBuffer.append(TEXT_584);
    }
    stringBuffer.append(TEXT_585);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_586);
    if (genModel.isVirtualDelegation()) {
    stringBuffer.append(TEXT_587);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_588);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(TEXT_589);
    } else if (genClass.isESetFlag(genFeature)) {
    stringBuffer.append(TEXT_590);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_591);
    stringBuffer.append(genClass.getESetFlagsField(genFeature));
    stringBuffer.append(TEXT_592);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_593);
    stringBuffer.append(genClass.getESetFlagsField(genFeature));
    stringBuffer.append(TEXT_594);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_595);
    } else {
    stringBuffer.append(TEXT_596);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_597);
    stringBuffer.append(genFeature.getUncapName());
    stringBuffer.append(TEXT_598);
    stringBuffer.append(genFeature.getUncapName());
    stringBuffer.append(TEXT_599);
    }
    stringBuffer.append(TEXT_600);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.impl.ENotificationImpl"));
    stringBuffer.append(TEXT_601);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.notify.Notification"));
    stringBuffer.append(TEXT_602);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(TEXT_603);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_604);
    } else {
    if (genClass.isFlag(genFeature)) {
    stringBuffer.append(TEXT_605);
    stringBuffer.append(genFeature.getImportedType());
    stringBuffer.append(TEXT_606);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_607);
    stringBuffer.append(genClass.getFlagsField(genFeature));
    stringBuffer.append(TEXT_608);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_609);
    } else if (genModel.isVirtualDelegation() && !genFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_610);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_611);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(TEXT_612);
    } else {
    stringBuffer.append(TEXT_613);
    stringBuffer.append(genFeature.getImportedType());
    stringBuffer.append(TEXT_614);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_615);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_616);
    }
    if (genModel.isVirtualDelegation() && !genFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_617);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_618);
    } else if (genClass.isESetFlag(genFeature)) {
    stringBuffer.append(TEXT_619);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_620);
    stringBuffer.append(genClass.getESetFlagsField(genFeature));
    stringBuffer.append(TEXT_621);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_622);
    } else {
    stringBuffer.append(TEXT_623);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_624);
    stringBuffer.append(genFeature.getUncapName());
    stringBuffer.append(TEXT_625);
    }
    if (genFeature.isReferenceType()) {
    stringBuffer.append(TEXT_626);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_627);
    if (!genModel.isVirtualDelegation()) {
    if (genClass.isESetFlag(genFeature)) {
    stringBuffer.append(TEXT_628);
    stringBuffer.append(genClass.getESetFlagsField(genFeature));
    stringBuffer.append(TEXT_629);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_630);
    } else {
    stringBuffer.append(TEXT_631);
    stringBuffer.append(genFeature.getUncapName());
    stringBuffer.append(TEXT_632);
    }
    }
    stringBuffer.append(TEXT_633);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.impl.ENotificationImpl"));
    stringBuffer.append(TEXT_634);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.notify.Notification"));
    stringBuffer.append(TEXT_635);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(TEXT_636);
    if (genModel.isVirtualDelegation()) {
    stringBuffer.append(TEXT_637);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_638);
    } else {
    stringBuffer.append(TEXT_639);
    stringBuffer.append(genFeature.getCapName());
    }
    stringBuffer.append(TEXT_640);
    if (genModel.isVirtualDelegation()) {
    stringBuffer.append(TEXT_641);
    } else {
    stringBuffer.append(TEXT_642);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_643);
    }
    stringBuffer.append(TEXT_644);
    } else {
    if (genClass.isFlag(genFeature)) {
    stringBuffer.append(TEXT_645);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_646);
    stringBuffer.append(genClass.getFlagsField(genFeature));
    stringBuffer.append(TEXT_647);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_648);
    stringBuffer.append(genClass.getFlagsField(genFeature));
    stringBuffer.append(TEXT_649);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_650);
    } else if (!genModel.isVirtualDelegation() || genFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_651);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_652);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_653);
    }
    if (!genModel.isVirtualDelegation() || genFeature.isPrimitiveType()) {
    if (genClass.isESetFlag(genFeature)) {
    stringBuffer.append(TEXT_654);
    stringBuffer.append(genClass.getESetFlagsField(genFeature));
    stringBuffer.append(TEXT_655);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_656);
    } else {
    stringBuffer.append(TEXT_657);
    stringBuffer.append(genFeature.getUncapName());
    stringBuffer.append(TEXT_658);
    }
    }
    stringBuffer.append(TEXT_659);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.impl.ENotificationImpl"));
    stringBuffer.append(TEXT_660);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.notify.Notification"));
    stringBuffer.append(TEXT_661);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(TEXT_662);
    if (genModel.isVirtualDelegation() && !genFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_663);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_664);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_665);
    } else {
    stringBuffer.append(TEXT_666);
    stringBuffer.append(genFeature.getCapName());
    }
    stringBuffer.append(TEXT_667);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_668);
    if (genModel.isVirtualDelegation() && !genFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_669);
    } else {
    stringBuffer.append(TEXT_670);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_671);
    }
    stringBuffer.append(TEXT_672);
    }
    }
    } else if (genFeature.hasDelegateFeature()) { GenFeature delegateFeature = genFeature.getDelegateFeature();
    if (delegateFeature.isWrappedFeatureMapType()) {
    stringBuffer.append(TEXT_673);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.FeatureMap"));
    stringBuffer.append(TEXT_674);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.FeatureMap"));
    stringBuffer.append(TEXT_675);
    stringBuffer.append(delegateFeature.getAccessorName());
    stringBuffer.append(TEXT_676);
    stringBuffer.append(genFeature.getQualifiedFeatureAccessorName());
    stringBuffer.append(TEXT_677);
    } else {
    stringBuffer.append(TEXT_678);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.FeatureMap"));
    stringBuffer.append(TEXT_679);
    stringBuffer.append(delegateFeature.getAccessorName());
    stringBuffer.append(TEXT_680);
    stringBuffer.append(genFeature.getQualifiedFeatureAccessorName());
    stringBuffer.append(TEXT_681);
    }
    } else {
    stringBuffer.append(TEXT_682);
    stringBuffer.append(genFeature.getFormattedName());
    stringBuffer.append(TEXT_683);
    stringBuffer.append(genFeature.getFeatureKind());
    stringBuffer.append(TEXT_684);
    //Class/unsetGenFeature.todo.override.javajetinc
    }
    stringBuffer.append(TEXT_685);
    //Class/unsetGenFeature.override.javajetinc
    }
    if (genFeature.isIsSet()) {
    stringBuffer.append(TEXT_686);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_687);
    if (genModel.isReflectiveDelegation()) {
    stringBuffer.append(TEXT_688);
    stringBuffer.append(genFeature.getGenPackage().getImportedPackageInterfaceName());
    stringBuffer.append(TEXT_689);
    stringBuffer.append(genFeature.getFeatureAccessorName());
    stringBuffer.append(TEXT_690);
    } else if (!genFeature.isVolatile()) {
    if (genFeature.isListType()) {
    if (genModel.isVirtualDelegation()) {
    stringBuffer.append(TEXT_691);
    stringBuffer.append(genFeature.getImportedType());
    stringBuffer.append(TEXT_692);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_693);
    stringBuffer.append(genFeature.getImportedType());
    stringBuffer.append(TEXT_694);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(TEXT_695);
    }
    stringBuffer.append(TEXT_696);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_697);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.InternalEList"));
    stringBuffer.append(TEXT_698);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_699);
    } else {
    if (genModel.isVirtualDelegation() && !genFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_700);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(TEXT_701);
    } else if (genClass.isESetFlag(genFeature)) {
    stringBuffer.append(TEXT_702);
    stringBuffer.append(genClass.getESetFlagsField(genFeature));
    stringBuffer.append(TEXT_703);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_704);
    } else {
    stringBuffer.append(TEXT_705);
    stringBuffer.append(genFeature.getUncapName());
    stringBuffer.append(TEXT_706);
    }
    }
    } else if (genFeature.hasDelegateFeature()) { GenFeature delegateFeature = genFeature.getDelegateFeature();
    if (delegateFeature.isWrappedFeatureMapType()) {
    stringBuffer.append(TEXT_707);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.FeatureMap"));
    stringBuffer.append(TEXT_708);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.FeatureMap"));
    stringBuffer.append(TEXT_709);
    stringBuffer.append(delegateFeature.getAccessorName());
    stringBuffer.append(TEXT_710);
    stringBuffer.append(genFeature.getQualifiedFeatureAccessorName());
    stringBuffer.append(TEXT_711);
    } else {
    stringBuffer.append(TEXT_712);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.FeatureMap"));
    stringBuffer.append(TEXT_713);
    stringBuffer.append(delegateFeature.getAccessorName());
    stringBuffer.append(TEXT_714);
    stringBuffer.append(genFeature.getQualifiedFeatureAccessorName());
    stringBuffer.append(TEXT_715);
    }
    } else {
    stringBuffer.append(TEXT_716);
    stringBuffer.append(genFeature.getFormattedName());
    stringBuffer.append(TEXT_717);
    stringBuffer.append(genFeature.getFeatureKind());
    stringBuffer.append(TEXT_718);
    //Class/isSetGenFeature.todo.override.javajetinc
    }
    stringBuffer.append(TEXT_719);
    //Class/isSetGenFeature.override.javajetinc
    }
    //Class/implementedGenFeature.override.javajetinc
    }//for
    for (Iterator i=genClass.getImplementedGenOperations().iterator(); i.hasNext();) { GenOperation genOperation = (GenOperation)i.next();
    stringBuffer.append(TEXT_720);
    stringBuffer.append(genOperation.getImportedType());
    stringBuffer.append(TEXT_721);
    stringBuffer.append(genOperation.getName());
    stringBuffer.append(TEXT_722);
    stringBuffer.append(genOperation.getParameters());
    stringBuffer.append(TEXT_723);
    stringBuffer.append(genOperation.getThrows());
    stringBuffer.append(TEXT_724);
    if (genOperation.hasBody()) {
    stringBuffer.append(TEXT_725);
    stringBuffer.append(genOperation.getBody(genModel.getIndentation(stringBuffer)));
    } else if (genOperation.isInvariant()) {GenClass opClass = genOperation.getGenClass(); String diagnostics = ((GenParameter)genOperation.getGenParameters().get(0)).getName(); String context = ((GenParameter)genOperation.getGenParameters().get(1)).getName();
    stringBuffer.append(TEXT_726);
    stringBuffer.append(diagnostics);
    stringBuffer.append(TEXT_727);
    stringBuffer.append(diagnostics);
    stringBuffer.append(TEXT_728);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.util.BasicDiagnostic"));
    stringBuffer.append(TEXT_729);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.util.Diagnostic"));
    stringBuffer.append(TEXT_730);
    stringBuffer.append(opClass.getGenPackage().getImportedValidatorClassName());
    stringBuffer.append(TEXT_731);
    stringBuffer.append(opClass.getGenPackage().getImportedValidatorClassName());
    stringBuffer.append(TEXT_732);
    stringBuffer.append(opClass.getOperationID(genOperation));
    stringBuffer.append(TEXT_733);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.plugin.EcorePlugin"));
    stringBuffer.append(TEXT_734);
    stringBuffer.append(genOperation.getName());
    stringBuffer.append(TEXT_735);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.EObjectValidator"));
    stringBuffer.append(TEXT_736);
    stringBuffer.append(context);
    stringBuffer.append(TEXT_737);
    stringBuffer.append(genModel.getNonNLS());
    stringBuffer.append(genModel.getNonNLS(2));
    stringBuffer.append(TEXT_738);
    } else {
    stringBuffer.append(TEXT_739);
    //Class/implementedGenOperation.todo.override.javajetinc
    }
    stringBuffer.append(TEXT_740);
    //Class/implementedGenOperation.override.javajetinc
    }//for
    if (!genModel.isReflectiveDelegation() && genClass.implementsAny(genClass.getEInverseAddGenFeatures())) {
    stringBuffer.append(TEXT_741);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.notify.NotificationChain"));
    stringBuffer.append(TEXT_742);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.InternalEObject"));
    stringBuffer.append(TEXT_743);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.notify.NotificationChain"));
    stringBuffer.append(TEXT_744);
    for (Iterator i=genClass.getEInverseAddGenFeatures().iterator(); i.hasNext();) { GenFeature genFeature = (GenFeature)i.next();
    stringBuffer.append(TEXT_745);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(TEXT_746);
    if (genFeature.isListType()) {
    if (genFeature.isMapType() && genFeature.isEffectiveSuppressEMFTypes()) {
    stringBuffer.append(TEXT_747);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.InternalEList"));
    stringBuffer.append(TEXT_748);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.util.EMap"));
    stringBuffer.append(TEXT_749);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_750);
    } else {
    stringBuffer.append(TEXT_751);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.InternalEList"));
    stringBuffer.append(TEXT_752);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_753);
    }
    } else if (genFeature.isContainer()) {
    stringBuffer.append(TEXT_754);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(TEXT_755);
    } else {
    if (genClass.getImplementedGenFeatures().contains(genFeature) ? genModel.isVirtualDelegation() : genFeature.getGenModel().isVirtualDelegation()) {
    stringBuffer.append(TEXT_756);
    stringBuffer.append(genFeature.getImportedType());
    stringBuffer.append(TEXT_757);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_758);
    stringBuffer.append(genFeature.getImportedType());
    stringBuffer.append(TEXT_759);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(TEXT_760);
    }
    stringBuffer.append(TEXT_761);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_762);
    if (genFeature.isContains()) {
    stringBuffer.append(TEXT_763);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.InternalEObject"));
    stringBuffer.append(TEXT_764);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_765);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(TEXT_766);
    } else { GenFeature reverseFeature = genFeature.getReverse(); GenClass targetClass = reverseFeature.getGenClass();
    stringBuffer.append(TEXT_767);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.InternalEObject"));
    stringBuffer.append(TEXT_768);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_769);
    stringBuffer.append(targetClass.getQualifiedFeatureID(reverseFeature));
    stringBuffer.append(TEXT_770);
    stringBuffer.append(targetClass.getImportedInterfaceName());
    stringBuffer.append(TEXT_771);
    }
    stringBuffer.append(TEXT_772);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_773);
    stringBuffer.append(genFeature.getImportedType());
    stringBuffer.append(TEXT_774);
    }
    }
    stringBuffer.append(TEXT_775);
    }
    if (!genModel.isReflectiveDelegation() && genClass.implementsAny(genClass.getEInverseRemoveGenFeatures())) {
    stringBuffer.append(TEXT_776);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.notify.NotificationChain"));
    stringBuffer.append(TEXT_777);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.InternalEObject"));
    stringBuffer.append(TEXT_778);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.notify.NotificationChain"));
    stringBuffer.append(TEXT_779);
    for (Iterator i=genClass.getEInverseRemoveGenFeatures().iterator(); i.hasNext();) { GenFeature genFeature = (GenFeature)i.next();
    stringBuffer.append(TEXT_780);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(TEXT_781);
    if (genFeature.isListType()) {
    if (genFeature.isMapType() && genFeature.isEffectiveSuppressEMFTypes()) {
    stringBuffer.append(TEXT_782);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.InternalEList"));
    stringBuffer.append(TEXT_783);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.util.EMap"));
    stringBuffer.append(TEXT_784);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_785);
    } else if (genFeature.isWrappedFeatureMapType()) {
    stringBuffer.append(TEXT_786);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.InternalEList"));
    stringBuffer.append(TEXT_787);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.FeatureMap"));
    stringBuffer.append(TEXT_788);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_789);
    } else {
    stringBuffer.append(TEXT_790);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.InternalEList"));
    stringBuffer.append(TEXT_791);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_792);
    }
    } else if (genFeature.isContainer()) {
    stringBuffer.append(TEXT_793);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(TEXT_794);
    } else if (genFeature.isUnsettable()) {
    stringBuffer.append(TEXT_795);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_796);
    } else {
    stringBuffer.append(TEXT_797);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_798);
    }
    }
    stringBuffer.append(TEXT_799);
    }
    if (!genModel.isReflectiveDelegation() && genClass.implementsAny(genClass.getEBasicRemoveFromContainerGenFeatures())) {
    stringBuffer.append(TEXT_800);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.notify.NotificationChain"));
    stringBuffer.append(TEXT_801);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.notify.NotificationChain"));
    stringBuffer.append(TEXT_802);
    for (Iterator i=genClass.getEBasicRemoveFromContainerGenFeatures().iterator(); i.hasNext();) { GenFeature genFeature = (GenFeature)i.next();
    GenFeature reverseFeature = genFeature.getReverse(); GenClass targetClass = reverseFeature.getGenClass();
    stringBuffer.append(TEXT_803);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(TEXT_804);
    stringBuffer.append(targetClass.getQualifiedFeatureID(reverseFeature));
    stringBuffer.append(TEXT_805);
    stringBuffer.append(targetClass.getImportedInterfaceName());
    stringBuffer.append(TEXT_806);
    }
    stringBuffer.append(TEXT_807);
    }
    if (!genModel.isReflectiveDelegation() && !genClass.getImplementedGenFeatures().isEmpty()) {
    stringBuffer.append(TEXT_808);
    for (Iterator i=genClass.getAllGenFeatures().iterator(); i.hasNext();) { GenFeature genFeature = (GenFeature)i.next();
    stringBuffer.append(TEXT_809);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(TEXT_810);
    if (genFeature.isPrimitiveType()) {
    if (genFeature.isBooleanType()) {
    stringBuffer.append(TEXT_811);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_812);
    } else {
    stringBuffer.append(TEXT_813);
    stringBuffer.append(genFeature.getObjectType());
    stringBuffer.append(TEXT_814);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_815);
    }
    } else if (genFeature.isResolveProxies() && !genFeature.isListType()) {
    stringBuffer.append(TEXT_816);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_817);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_818);
    } else if (genFeature.isMapType()) {
    if (genFeature.isEffectiveSuppressEMFTypes()) {
    stringBuffer.append(TEXT_819);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.util.EMap"));
    stringBuffer.append(TEXT_820);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_821);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_822);
    } else {
    stringBuffer.append(TEXT_823);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_824);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_825);
    }
    } else if (genFeature.isWrappedFeatureMapType()) {
    stringBuffer.append(TEXT_826);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.FeatureMap"));
    stringBuffer.append(TEXT_827);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_828);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_829);
    } else if (genFeature.isFeatureMapType()) {
    stringBuffer.append(TEXT_830);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_831);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.FeatureMap"));
    stringBuffer.append(TEXT_832);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_833);
    } else {
    stringBuffer.append(TEXT_834);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_835);
    }
    }
    stringBuffer.append(TEXT_836);
    }
    if (!genModel.isReflectiveDelegation() && genClass.implementsAny(genClass.getESetGenFeatures())) {
    stringBuffer.append(TEXT_837);
    for (Iterator i=genClass.getESetGenFeatures().iterator(); i.hasNext();) { GenFeature genFeature = (GenFeature)i.next();
    stringBuffer.append(TEXT_838);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(TEXT_839);
    if (genFeature.isListType()) {
    if (genFeature.isWrappedFeatureMapType()) {
    stringBuffer.append(TEXT_840);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.FeatureMap"));
    stringBuffer.append(TEXT_841);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.FeatureMap"));
    stringBuffer.append(TEXT_842);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_843);
    } else if (genFeature.isFeatureMapType()) {
    stringBuffer.append(TEXT_844);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.FeatureMap"));
    stringBuffer.append(TEXT_845);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_846);
    } else if (genFeature.isMapType()) {
    if (genFeature.isEffectiveSuppressEMFTypes()) {
    stringBuffer.append(TEXT_847);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.EStructuralFeature"));
    stringBuffer.append(TEXT_848);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.util.EMap"));
    stringBuffer.append(TEXT_849);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_850);
    } else {
    stringBuffer.append(TEXT_851);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.EStructuralFeature"));
    stringBuffer.append(TEXT_852);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_853);
    }
    } else {
    stringBuffer.append(TEXT_854);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_855);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_856);
    stringBuffer.append(genModel.getImportedName("java.util.Collection"));
    stringBuffer.append(TEXT_857);
    }
    } else if (genFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_858);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_859);
    stringBuffer.append(genFeature.getObjectType());
    stringBuffer.append(TEXT_860);
    stringBuffer.append(genFeature.getPrimitiveValueFunction());
    stringBuffer.append(TEXT_861);
    } else {
    stringBuffer.append(TEXT_862);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_863);
    stringBuffer.append(genFeature.getImportedType());
    stringBuffer.append(TEXT_864);
    }
    stringBuffer.append(TEXT_865);
    }
    stringBuffer.append(TEXT_866);
    for (Iterator i=genClass.getESetGenFeatures().iterator(); i.hasNext();) { GenFeature genFeature = (GenFeature)i.next();
    stringBuffer.append(TEXT_867);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(TEXT_868);
    if (genFeature.isListType() && !genFeature.isUnsettable()) {
    if (genFeature.isWrappedFeatureMapType()) {
    stringBuffer.append(TEXT_869);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.FeatureMap"));
    stringBuffer.append(TEXT_870);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_871);
    } else {
    stringBuffer.append(TEXT_872);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_873);
    }
    } else if (genFeature.isUnsettable()) {
    stringBuffer.append(TEXT_874);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_875);
    } else if (genFeature.isReferenceType()) {
    stringBuffer.append(TEXT_876);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_877);
    stringBuffer.append(genFeature.getImportedType());
    stringBuffer.append(TEXT_878);
    } else {
    stringBuffer.append(TEXT_879);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_880);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_881);
    }
    stringBuffer.append(TEXT_882);
    }
    stringBuffer.append(TEXT_883);
    }
    if (!genModel.isReflectiveDelegation() && !genClass.getImplementedGenFeatures().isEmpty()) {
    stringBuffer.append(TEXT_884);
    for (Iterator i=genClass.getAllGenFeatures().iterator(); i.hasNext();) { GenFeature genFeature = (GenFeature)i.next();
    stringBuffer.append(TEXT_885);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(TEXT_886);
    if (genFeature.isListType() && !genFeature.isUnsettable()) {
    if (genFeature.isWrappedFeatureMapType()) {
    if (genFeature.isVolatile()) {
    stringBuffer.append(TEXT_887);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.FeatureMap"));
    stringBuffer.append(TEXT_888);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_889);
    } else {
    stringBuffer.append(TEXT_890);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_891);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_892);
    }
    } else {
    if (genClass.isField(genFeature)) {
    stringBuffer.append(TEXT_893);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_894);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_895);
    } else {
    if (genFeature.isField() && (genClass.getImplementedGenFeatures().contains(genFeature) ? genModel.isVirtualDelegation() : genFeature.getGenModel().isVirtualDelegation())) {
    stringBuffer.append(TEXT_896);
    stringBuffer.append(genFeature.getImportedType());
    stringBuffer.append(TEXT_897);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_898);
    stringBuffer.append(genFeature.getImportedType());
    stringBuffer.append(TEXT_899);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(TEXT_900);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_901);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_902);
    } else {
    stringBuffer.append(TEXT_903);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_904);
    }
    }
    }
    } else if (genFeature.isUnsettable()) {
    stringBuffer.append(TEXT_905);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_906);
    } else if (genFeature.isResolveProxies()) {
    if (genClass.isField(genFeature)) {
    stringBuffer.append(TEXT_907);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_908);
    } else {
    if (genFeature.isField() && (genClass.getImplementedGenFeatures().contains(genFeature) ? genModel.isVirtualDelegation() : genFeature.getGenModel().isVirtualDelegation())) {
    stringBuffer.append(TEXT_909);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(TEXT_910);
    } else {
    stringBuffer.append(TEXT_911);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_912);
    }
    }
    } else if (genFeature.isReferenceType()) {
    if (genClass.isField(genFeature)) {
    stringBuffer.append(TEXT_913);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_914);
    } else {
    if (genFeature.isField() && (genClass.getImplementedGenFeatures().contains(genFeature) ? genModel.isVirtualDelegation() : genFeature.getGenModel().isVirtualDelegation())) {
    stringBuffer.append(TEXT_915);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(TEXT_916);
    } else {
    stringBuffer.append(TEXT_917);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_918);
    }
    }
    } else if (genFeature.isPrimitiveType() || genFeature.isEnumType()) {
    if (genClass.isField(genFeature)) {
    if (genClass.isFlag(genFeature)) {
    stringBuffer.append(TEXT_919);
    stringBuffer.append(genClass.getFlagsField(genFeature));
    stringBuffer.append(TEXT_920);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_921);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_922);
    } else {
    stringBuffer.append(TEXT_923);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_924);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_925);
    }
    } else {
    if (genFeature.isEnumType() && genFeature.isField() && (genClass.getImplementedGenFeatures().contains(genFeature) ? genModel.isVirtualDelegation() : genFeature.getGenModel().isVirtualDelegation())) {
    stringBuffer.append(TEXT_926);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(TEXT_927);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_928);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_929);
    } else {
    stringBuffer.append(TEXT_930);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_931);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_932);
    }
    }
    } else {//datatype
    if (genClass.isField(genFeature)) {
    stringBuffer.append(TEXT_933);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_934);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_935);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_936);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_937);
    } else {
    if (genFeature.isField() && (genClass.getImplementedGenFeatures().contains(genFeature) ? genModel.isVirtualDelegation() : genFeature.getGenModel().isVirtualDelegation())) {
    stringBuffer.append(TEXT_938);
    stringBuffer.append(genFeature.getImportedType());
    stringBuffer.append(TEXT_939);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_940);
    stringBuffer.append(genFeature.getImportedType());
    stringBuffer.append(TEXT_941);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(TEXT_942);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_943);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_944);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_945);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_946);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_947);
    } else {
    stringBuffer.append(TEXT_948);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_949);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_950);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_951);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_952);
    }
    }
    }
    }
    stringBuffer.append(TEXT_953);
    //Class/eIsSet.override.javajetinc
    }
    if (!genClass.getMixinGenFeatures().isEmpty()) {
    stringBuffer.append(TEXT_954);
    for (Iterator m=genClass.getMixinGenClasses().iterator(); m.hasNext();) { GenClass mixinGenClass = (GenClass)m.next(); 
    stringBuffer.append(TEXT_955);
    stringBuffer.append(mixinGenClass.getImportedInterfaceName());
    stringBuffer.append(TEXT_956);
    for (Iterator f=mixinGenClass.getGenFeatures().iterator(); f.hasNext();) { GenFeature genFeature = (GenFeature)f.next(); 
    stringBuffer.append(TEXT_957);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(TEXT_958);
    stringBuffer.append(mixinGenClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(TEXT_959);
    }
    stringBuffer.append(TEXT_960);
    }
    stringBuffer.append(TEXT_961);
    for (Iterator m=genClass.getMixinGenClasses().iterator(); m.hasNext();) { GenClass mixinGenClass = (GenClass)m.next(); 
    stringBuffer.append(TEXT_962);
    stringBuffer.append(mixinGenClass.getImportedInterfaceName());
    stringBuffer.append(TEXT_963);
    for (Iterator f=mixinGenClass.getGenFeatures().iterator(); f.hasNext();) { GenFeature genFeature = (GenFeature)f.next(); 
    stringBuffer.append(TEXT_964);
    stringBuffer.append(mixinGenClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(TEXT_965);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(TEXT_966);
    }
    stringBuffer.append(TEXT_967);
    }
    stringBuffer.append(TEXT_968);
    }
    if (genModel.isVirtualDelegation()) { String eVirtualValuesField = genClass.getEVirtualValuesField();
    if (eVirtualValuesField != null) {
    stringBuffer.append(TEXT_969);
    stringBuffer.append(eVirtualValuesField);
    stringBuffer.append(TEXT_970);
    stringBuffer.append(eVirtualValuesField);
    stringBuffer.append(TEXT_971);
    }
    { List eVirtualIndexBitFields = genClass.getEVirtualIndexBitFields(new ArrayList());
    if (!eVirtualIndexBitFields.isEmpty()) { List allEVirtualIndexBitFields = genClass.getAllEVirtualIndexBitFields(new ArrayList());
    stringBuffer.append(TEXT_972);
    for (int i = 0; i < allEVirtualIndexBitFields.size(); i++) {
    stringBuffer.append(TEXT_973);
    stringBuffer.append(i);
    stringBuffer.append(TEXT_974);
    stringBuffer.append(allEVirtualIndexBitFields.get(i));
    stringBuffer.append(TEXT_975);
    }
    stringBuffer.append(TEXT_976);
    for (int i = 0; i < allEVirtualIndexBitFields.size(); i++) {
    stringBuffer.append(TEXT_977);
    stringBuffer.append(i);
    stringBuffer.append(TEXT_978);
    stringBuffer.append(allEVirtualIndexBitFields.get(i));
    stringBuffer.append(TEXT_979);
    }
    stringBuffer.append(TEXT_980);
    }
    }
    }
    if (!genModel.isReflectiveDelegation() && !genClass.getToStringGenFeatures().isEmpty()) {
    stringBuffer.append(TEXT_981);
    { boolean first = true;
    for (Iterator i=genClass.getToStringGenFeatures().iterator(); i.hasNext(); ) { GenFeature genFeature = (GenFeature)i.next();
    if (first) { first = false;
    stringBuffer.append(TEXT_982);
    stringBuffer.append(genFeature.getName());
    stringBuffer.append(TEXT_983);
    stringBuffer.append(genModel.getNonNLS());
    } else {
    stringBuffer.append(TEXT_984);
    stringBuffer.append(genFeature.getName());
    stringBuffer.append(TEXT_985);
    stringBuffer.append(genModel.getNonNLS());
    }
    if (genFeature.isUnsettable() && !genFeature.isListType()) {
    if (genModel.isVirtualDelegation() && !genFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_986);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(TEXT_987);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(TEXT_988);
    stringBuffer.append(genModel.getNonNLS());
    } else {
    if (genClass.isFlag(genFeature)) {
    stringBuffer.append(TEXT_989);
    if (genClass.isESetFlag(genFeature)) {
    stringBuffer.append(TEXT_990);
    stringBuffer.append(genClass.getESetFlagsField(genFeature));
    stringBuffer.append(TEXT_991);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_992);
    } else {
    stringBuffer.append(genFeature.getUncapName());
    stringBuffer.append(TEXT_993);
    }
    stringBuffer.append(TEXT_994);
    stringBuffer.append(genClass.getFlagsField(genFeature));
    stringBuffer.append(TEXT_995);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_996);
    stringBuffer.append(genModel.getNonNLS());
    } else {
    stringBuffer.append(TEXT_997);
    if (genClass.isESetFlag(genFeature)) {
    stringBuffer.append(TEXT_998);
    stringBuffer.append(genClass.getESetFlagsField(genFeature));
    stringBuffer.append(TEXT_999);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_1000);
    } else {
    stringBuffer.append(genFeature.getUncapName());
    stringBuffer.append(TEXT_1001);
    }
    stringBuffer.append(TEXT_1002);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_1003);
    stringBuffer.append(genModel.getNonNLS());
    }
    }
    } else {
    if (genModel.isVirtualDelegation() && !genFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_1004);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    if (!genFeature.isListType() && !genFeature.isReferenceType()){
    stringBuffer.append(TEXT_1005);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_1006);
    }
    stringBuffer.append(TEXT_1007);
    } else {
    if (genClass.isFlag(genFeature)) {
    stringBuffer.append(TEXT_1008);
    stringBuffer.append(genClass.getFlagsField(genFeature));
    stringBuffer.append(TEXT_1009);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_1010);
    } else {
    stringBuffer.append(TEXT_1011);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_1012);
    }
    }
    }
    }
    }
    stringBuffer.append(TEXT_1013);
    }
    if (genClass.isMapEntry()) { GenFeature keyFeature = genClass.getMapEntryKeyFeature(); GenFeature valueFeature = genClass.getMapEntryValueFeature();
    stringBuffer.append(TEXT_1014);
    if (keyFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_1015);
    stringBuffer.append(keyFeature.getObjectType());
    stringBuffer.append(TEXT_1016);
    } else {
    stringBuffer.append(TEXT_1017);
    }
    stringBuffer.append(TEXT_1018);
    if (keyFeature.isListType()) {
    stringBuffer.append(TEXT_1019);
    stringBuffer.append(genModel.getImportedName("java.util.Collection"));
    stringBuffer.append(TEXT_1020);
    } else if (keyFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_1021);
    stringBuffer.append(keyFeature.getObjectType());
    stringBuffer.append(TEXT_1022);
    stringBuffer.append(keyFeature.getPrimitiveValueFunction());
    stringBuffer.append(TEXT_1023);
    } else {
    stringBuffer.append(TEXT_1024);
    stringBuffer.append(keyFeature.getImportedType());
    stringBuffer.append(TEXT_1025);
    }
    stringBuffer.append(TEXT_1026);
    if (valueFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_1027);
    stringBuffer.append(valueFeature.getObjectType());
    stringBuffer.append(TEXT_1028);
    } else {
    stringBuffer.append(TEXT_1029);
    }
    stringBuffer.append(TEXT_1030);
    if (valueFeature.isListType()) {
    stringBuffer.append(TEXT_1031);
    stringBuffer.append(genModel.getImportedName("java.util.Collection"));
    stringBuffer.append(TEXT_1032);
    } else if (valueFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_1033);
    stringBuffer.append(valueFeature.getObjectType());
    stringBuffer.append(TEXT_1034);
    stringBuffer.append(valueFeature.getPrimitiveValueFunction());
    stringBuffer.append(TEXT_1035);
    } else {
    stringBuffer.append(TEXT_1036);
    stringBuffer.append(valueFeature.getImportedType());
    stringBuffer.append(TEXT_1037);
    }
    stringBuffer.append(TEXT_1038);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.util.EMap"));
    stringBuffer.append(TEXT_1039);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.EObject"));
    stringBuffer.append(TEXT_1040);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.util.EMap"));
    stringBuffer.append(TEXT_1041);
    }
    stringBuffer.append(TEXT_1042);
    stringBuffer.append(genClass.getClassName());
    genModel.emitSortedImports();
    stringBuffer.append(TEXT_1043);
    return stringBuffer.toString();
  }
}
