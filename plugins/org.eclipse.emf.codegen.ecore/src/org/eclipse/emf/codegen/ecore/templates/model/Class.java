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
  protected final String TEXT_67 = NL + "\t/**" + NL + "\t * The empty value for the '{@link #";
  protected final String TEXT_68 = "() <em>";
  protected final String TEXT_69 = "</em>}' array accessor." + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @see #";
  protected final String TEXT_70 = "()" + NL + "\t * @generated" + NL + "\t * @ordered" + NL + "\t */" + NL + "\tprotected static final ";
  protected final String TEXT_71 = "[] ";
  protected final String TEXT_72 = "_EEMPTY_ARRAY = new ";
  protected final String TEXT_73 = " [0];" + NL;
  protected final String TEXT_74 = NL + "\t/**" + NL + "\t * The default value of the '{@link #";
  protected final String TEXT_75 = "() <em>";
  protected final String TEXT_76 = "</em>}' ";
  protected final String TEXT_77 = "." + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @see #";
  protected final String TEXT_78 = "()" + NL + "\t * @generated" + NL + "\t * @ordered" + NL + "\t */" + NL + "\tprotected static final ";
  protected final String TEXT_79 = " ";
  protected final String TEXT_80 = "_EDEFAULT = ";
  protected final String TEXT_81 = ";";
  protected final String TEXT_82 = NL;
  protected final String TEXT_83 = NL + "\t/**" + NL + "\t * An additional set of bit flags representing the values of boolean attributes and whether unsettable features have been set." + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t * @ordered" + NL + "\t */" + NL + "\tprotected int ";
  protected final String TEXT_84 = " = 0;" + NL;
  protected final String TEXT_85 = NL + "\t/**" + NL + "\t * The flag representing the value of the '{@link #";
  protected final String TEXT_86 = "() <em>";
  protected final String TEXT_87 = "</em>}' ";
  protected final String TEXT_88 = "." + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @see #";
  protected final String TEXT_89 = "()" + NL + "\t * @generated" + NL + "\t * @ordered" + NL + "\t */" + NL + "\tprotected static final int ";
  protected final String TEXT_90 = "_EFLAG = 1 ";
  protected final String TEXT_91 = ";" + NL;
  protected final String TEXT_92 = NL + "\t/**" + NL + "\t * The cached value of the '{@link #";
  protected final String TEXT_93 = "() <em>";
  protected final String TEXT_94 = "</em>}' ";
  protected final String TEXT_95 = "." + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @see #";
  protected final String TEXT_96 = "()" + NL + "\t * @generated" + NL + "\t * @ordered" + NL + "\t */" + NL + "\tprotected ";
  protected final String TEXT_97 = " ";
  protected final String TEXT_98 = " = ";
  protected final String TEXT_99 = "_EDEFAULT;" + NL;
  protected final String TEXT_100 = NL + "\t/**" + NL + "\t * An additional set of bit flags representing the values of boolean attributes and whether unsettable features have been set." + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t * @ordered" + NL + "\t */" + NL + "\tprotected int ";
  protected final String TEXT_101 = " = 0;" + NL;
  protected final String TEXT_102 = NL + "\t/**" + NL + "\t * The flag representing whether the ";
  protected final String TEXT_103 = " ";
  protected final String TEXT_104 = " has been set." + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t * @ordered" + NL + "\t */" + NL + "\tprotected static final int ";
  protected final String TEXT_105 = "_ESETFLAG = 1 ";
  protected final String TEXT_106 = ";" + NL;
  protected final String TEXT_107 = NL + "\t/**" + NL + "\t * This is true if the ";
  protected final String TEXT_108 = " ";
  protected final String TEXT_109 = " has been set." + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t * @ordered" + NL + "\t */" + NL + "\tprotected boolean ";
  protected final String TEXT_110 = "ESet = false;" + NL;
  protected final String TEXT_111 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */" + NL + "\tprotected ";
  protected final String TEXT_112 = "()" + NL + "\t{" + NL + "\t\tsuper();";
  protected final String TEXT_113 = NL + "\t\t";
  protected final String TEXT_114 = " |= ";
  protected final String TEXT_115 = "_EFLAG;";
  protected final String TEXT_116 = NL + "\t}" + NL + "" + NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */" + NL + "\tprotected ";
  protected final String TEXT_117 = " eStaticClass()" + NL + "\t{" + NL + "\t\treturn ";
  protected final String TEXT_118 = ";" + NL + "\t}" + NL;
  protected final String TEXT_119 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */";
  protected final String TEXT_120 = NL + "\t";
  protected final String TEXT_121 = "[] ";
  protected final String TEXT_122 = "();" + NL;
  protected final String TEXT_123 = NL + "\tpublic ";
  protected final String TEXT_124 = "[] ";
  protected final String TEXT_125 = "()" + NL + "\t{";
  protected final String TEXT_126 = NL + "\t\t";
  protected final String TEXT_127 = " list = (";
  protected final String TEXT_128 = ")";
  protected final String TEXT_129 = "();" + NL + "\t\tif (list.isEmpty()) return ";
  protected final String TEXT_130 = "_EEMPTY_ARRAY;";
  protected final String TEXT_131 = NL + "\t\tif (";
  protected final String TEXT_132 = " == null || ";
  protected final String TEXT_133 = ".isEmpty()) return ";
  protected final String TEXT_134 = "_EEMPTY_ARRAY;" + NL + "\t\t";
  protected final String TEXT_135 = " list = (";
  protected final String TEXT_136 = ")";
  protected final String TEXT_137 = ";";
  protected final String TEXT_138 = NL + "\t\tlist.shrink();" + NL + "\t\treturn (";
  protected final String TEXT_139 = "[])list.data();" + NL + "\t}" + NL;
  protected final String TEXT_140 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */";
  protected final String TEXT_141 = NL + "\t";
  protected final String TEXT_142 = " get";
  protected final String TEXT_143 = "(int index);" + NL;
  protected final String TEXT_144 = NL + "\tpublic ";
  protected final String TEXT_145 = " get";
  protected final String TEXT_146 = "(int index)" + NL + "\t{" + NL + "\t\treturn (";
  protected final String TEXT_147 = ")";
  protected final String TEXT_148 = "().get(index);" + NL + "\t}" + NL;
  protected final String TEXT_149 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */";
  protected final String TEXT_150 = NL + "\tint get";
  protected final String TEXT_151 = "Length();" + NL;
  protected final String TEXT_152 = NL + "\tpublic int get";
  protected final String TEXT_153 = "Length()" + NL + "\t{";
  protected final String TEXT_154 = NL + "\t\treturn ";
  protected final String TEXT_155 = "().size();";
  protected final String TEXT_156 = NL + "\t\treturn ";
  protected final String TEXT_157 = " == null ? 0 : ";
  protected final String TEXT_158 = ".size();";
  protected final String TEXT_159 = NL + "\t}" + NL;
  protected final String TEXT_160 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */";
  protected final String TEXT_161 = NL + "\tvoid set";
  protected final String TEXT_162 = "(";
  protected final String TEXT_163 = "[] new";
  protected final String TEXT_164 = ");" + NL;
  protected final String TEXT_165 = NL + "\tpublic void set";
  protected final String TEXT_166 = "(";
  protected final String TEXT_167 = "[] new";
  protected final String TEXT_168 = ")" + NL + "\t{" + NL + "\t\t((";
  protected final String TEXT_169 = ")";
  protected final String TEXT_170 = "()).setData(new";
  protected final String TEXT_171 = ".length, new";
  protected final String TEXT_172 = ");" + NL + "\t}" + NL;
  protected final String TEXT_173 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */";
  protected final String TEXT_174 = NL + "\tvoid set";
  protected final String TEXT_175 = "(int index, ";
  protected final String TEXT_176 = " element);" + NL;
  protected final String TEXT_177 = NL + "\tpublic void set";
  protected final String TEXT_178 = "(int index, ";
  protected final String TEXT_179 = " element)" + NL + "\t{" + NL + "\t\t";
  protected final String TEXT_180 = "().set(index, element);" + NL + "\t}" + NL;
  protected final String TEXT_181 = NL + "\t/**" + NL + "\t * Returns the value of the '<em><b>";
  protected final String TEXT_182 = "</b></em>' ";
  protected final String TEXT_183 = ".";
  protected final String TEXT_184 = NL + "\t * The key is of type ";
  protected final String TEXT_185 = "list of {@link ";
  protected final String TEXT_186 = "}";
  protected final String TEXT_187 = "{@link ";
  protected final String TEXT_188 = "}";
  protected final String TEXT_189 = "," + NL + "\t * and the value is of type ";
  protected final String TEXT_190 = "list of {@link ";
  protected final String TEXT_191 = "}";
  protected final String TEXT_192 = "{@link ";
  protected final String TEXT_193 = "}";
  protected final String TEXT_194 = ",";
  protected final String TEXT_195 = NL + "\t * The list contents are of type {@link ";
  protected final String TEXT_196 = "}.";
  protected final String TEXT_197 = NL + "\t * The default value is <code>";
  protected final String TEXT_198 = "</code>.";
  protected final String TEXT_199 = NL + "\t * The literals are from the enumeration {@link ";
  protected final String TEXT_200 = "}.";
  protected final String TEXT_201 = NL + "\t * It is bidirectional and its opposite is '{@link ";
  protected final String TEXT_202 = "#";
  protected final String TEXT_203 = " <em>";
  protected final String TEXT_204 = "</em>}'.";
  protected final String TEXT_205 = NL + "\t * <!-- begin-user-doc -->";
  protected final String TEXT_206 = NL + "\t * <p>" + NL + "\t * If the meaning of the '<em>";
  protected final String TEXT_207 = "</em>' ";
  protected final String TEXT_208 = " isn't clear," + NL + "\t * there really should be more of a description here..." + NL + "\t * </p>";
  protected final String TEXT_209 = NL + "\t * <!-- end-user-doc -->";
  protected final String TEXT_210 = NL + "\t * <!-- begin-model-doc -->" + NL + "\t * ";
  protected final String TEXT_211 = NL + "\t * <!-- end-model-doc -->";
  protected final String TEXT_212 = NL + "\t * @return the value of the '<em>";
  protected final String TEXT_213 = "</em>' ";
  protected final String TEXT_214 = ".";
  protected final String TEXT_215 = NL + "\t * @see ";
  protected final String TEXT_216 = NL + "\t * @see #isSet";
  protected final String TEXT_217 = "()";
  protected final String TEXT_218 = NL + "\t * @see #unset";
  protected final String TEXT_219 = "()";
  protected final String TEXT_220 = NL + "\t * @see #set";
  protected final String TEXT_221 = "(";
  protected final String TEXT_222 = ")";
  protected final String TEXT_223 = NL + "\t * @see ";
  protected final String TEXT_224 = "#get";
  protected final String TEXT_225 = "()";
  protected final String TEXT_226 = NL + "\t * @see ";
  protected final String TEXT_227 = "#";
  protected final String TEXT_228 = NL + "\t * @model ";
  protected final String TEXT_229 = NL + "\t *        ";
  protected final String TEXT_230 = NL + "\t * @model";
  protected final String TEXT_231 = NL + "\t * @generated" + NL + "\t */";
  protected final String TEXT_232 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */";
  protected final String TEXT_233 = NL + "\t";
  protected final String TEXT_234 = " ";
  protected final String TEXT_235 = "();" + NL;
  protected final String TEXT_236 = NL + "\tpublic ";
  protected final String TEXT_237 = " ";
  protected final String TEXT_238 = "()" + NL + "\t{";
  protected final String TEXT_239 = NL + "\t\treturn ";
  protected final String TEXT_240 = "(";
  protected final String TEXT_241 = "(";
  protected final String TEXT_242 = ")eGet(";
  protected final String TEXT_243 = ", true)";
  protected final String TEXT_244 = ").";
  protected final String TEXT_245 = "()";
  protected final String TEXT_246 = ";";
  protected final String TEXT_247 = NL + "\t\t";
  protected final String TEXT_248 = " ";
  protected final String TEXT_249 = " = (";
  protected final String TEXT_250 = ")eVirtualGet(";
  protected final String TEXT_251 = ");";
  protected final String TEXT_252 = NL + "\t\tif (";
  protected final String TEXT_253 = " == null)" + NL + "\t\t{";
  protected final String TEXT_254 = NL + "\t\t\teVirtualSet(";
  protected final String TEXT_255 = ", ";
  protected final String TEXT_256 = " = new ";
  protected final String TEXT_257 = ");";
  protected final String TEXT_258 = NL + "\t\t\t";
  protected final String TEXT_259 = " = new ";
  protected final String TEXT_260 = ";";
  protected final String TEXT_261 = NL + "\t\t}" + NL + "\t\treturn ";
  protected final String TEXT_262 = ";";
  protected final String TEXT_263 = NL + "\t\tif (eContainerFeatureID != ";
  protected final String TEXT_264 = ") return null;" + NL + "\t\treturn (";
  protected final String TEXT_265 = ")eContainer();";
  protected final String TEXT_266 = NL + "\t\t";
  protected final String TEXT_267 = " ";
  protected final String TEXT_268 = " = (";
  protected final String TEXT_269 = ")eVirtualGet(";
  protected final String TEXT_270 = ", ";
  protected final String TEXT_271 = "_EDEFAULT";
  protected final String TEXT_272 = ");";
  protected final String TEXT_273 = NL + "\t\tif (";
  protected final String TEXT_274 = " != null && ";
  protected final String TEXT_275 = ".eIsProxy())" + NL + "\t\t{" + NL + "\t\t\t";
  protected final String TEXT_276 = " old";
  protected final String TEXT_277 = " = (";
  protected final String TEXT_278 = ")";
  protected final String TEXT_279 = ";" + NL + "\t\t\t";
  protected final String TEXT_280 = " = ";
  protected final String TEXT_281 = "eResolveProxy(old";
  protected final String TEXT_282 = ");" + NL + "\t\t\tif (";
  protected final String TEXT_283 = " != old";
  protected final String TEXT_284 = ")" + NL + "\t\t\t{";
  protected final String TEXT_285 = NL + "\t\t\t\t";
  protected final String TEXT_286 = " new";
  protected final String TEXT_287 = " = (";
  protected final String TEXT_288 = ")";
  protected final String TEXT_289 = ";";
  protected final String TEXT_290 = NL + "\t\t\t\t";
  protected final String TEXT_291 = " msgs = old";
  protected final String TEXT_292 = ".eInverseRemove(this, EOPPOSITE_FEATURE_BASE - ";
  protected final String TEXT_293 = ", null, null);";
  protected final String TEXT_294 = NL + "\t\t\t\t";
  protected final String TEXT_295 = " msgs =  old";
  protected final String TEXT_296 = ".eInverseRemove(this, ";
  protected final String TEXT_297 = ", ";
  protected final String TEXT_298 = ".class, null);";
  protected final String TEXT_299 = NL + "\t\t\t\tif (new";
  protected final String TEXT_300 = ".eInternalContainer() == null)" + NL + "\t\t\t\t{";
  protected final String TEXT_301 = NL + "\t\t\t\t\tmsgs = new";
  protected final String TEXT_302 = ".eInverseAdd(this, EOPPOSITE_FEATURE_BASE - ";
  protected final String TEXT_303 = ", null, msgs);";
  protected final String TEXT_304 = NL + "\t\t\t\t\tmsgs =  new";
  protected final String TEXT_305 = ".eInverseAdd(this, ";
  protected final String TEXT_306 = ", ";
  protected final String TEXT_307 = ".class, msgs);";
  protected final String TEXT_308 = NL + "\t\t\t\t}" + NL + "\t\t\t\tif (msgs != null) msgs.dispatch();";
  protected final String TEXT_309 = NL + "\t\t\t\teVirtualSet(";
  protected final String TEXT_310 = ", ";
  protected final String TEXT_311 = ");";
  protected final String TEXT_312 = NL + "\t\t\t\tif (eNotificationRequired())" + NL + "\t\t\t\t\teNotify(new ";
  protected final String TEXT_313 = "(this, ";
  protected final String TEXT_314 = ".RESOLVE, ";
  protected final String TEXT_315 = ", old";
  protected final String TEXT_316 = ", ";
  protected final String TEXT_317 = "));";
  protected final String TEXT_318 = NL + "\t\t\t}" + NL + "\t\t}";
  protected final String TEXT_319 = NL + "\t\treturn (";
  protected final String TEXT_320 = ")eVirtualGet(";
  protected final String TEXT_321 = ", ";
  protected final String TEXT_322 = "_EDEFAULT";
  protected final String TEXT_323 = ");";
  protected final String TEXT_324 = NL + "\t\treturn (";
  protected final String TEXT_325 = " & ";
  protected final String TEXT_326 = "_EFLAG) != 0;";
  protected final String TEXT_327 = NL + "\t\treturn ";
  protected final String TEXT_328 = ";";
  protected final String TEXT_329 = NL + "\t\t";
  protected final String TEXT_330 = " ";
  protected final String TEXT_331 = " = basicGet";
  protected final String TEXT_332 = "();" + NL + "\t\treturn ";
  protected final String TEXT_333 = " != null && ";
  protected final String TEXT_334 = ".eIsProxy() ? ";
  protected final String TEXT_335 = "eResolveProxy((";
  protected final String TEXT_336 = ")";
  protected final String TEXT_337 = ") : ";
  protected final String TEXT_338 = ";";
  protected final String TEXT_339 = NL + "\t\treturn new ";
  protected final String TEXT_340 = "((";
  protected final String TEXT_341 = ".Internal)((";
  protected final String TEXT_342 = ".Internal.Wrapper)get";
  protected final String TEXT_343 = "()).featureMap().list(";
  protected final String TEXT_344 = "));";
  protected final String TEXT_345 = NL + "\t\treturn (";
  protected final String TEXT_346 = ")((";
  protected final String TEXT_347 = ")get";
  protected final String TEXT_348 = "()).list(";
  protected final String TEXT_349 = ");";
  protected final String TEXT_350 = NL + "\t\treturn ((";
  protected final String TEXT_351 = ".Internal.Wrapper)get";
  protected final String TEXT_352 = "()).featureMap().list(";
  protected final String TEXT_353 = ");";
  protected final String TEXT_354 = NL + "\t\treturn ((";
  protected final String TEXT_355 = ")get";
  protected final String TEXT_356 = "()).list(";
  protected final String TEXT_357 = ");";
  protected final String TEXT_358 = NL + "\t\treturn ";
  protected final String TEXT_359 = "(";
  protected final String TEXT_360 = "(";
  protected final String TEXT_361 = ")((";
  protected final String TEXT_362 = ".Internal.Wrapper)get";
  protected final String TEXT_363 = "()).featureMap().get(";
  protected final String TEXT_364 = ", true)";
  protected final String TEXT_365 = ").";
  protected final String TEXT_366 = "()";
  protected final String TEXT_367 = ";";
  protected final String TEXT_368 = NL + "\t\treturn ";
  protected final String TEXT_369 = "(";
  protected final String TEXT_370 = "(";
  protected final String TEXT_371 = ")get";
  protected final String TEXT_372 = "().get(";
  protected final String TEXT_373 = ", true)";
  protected final String TEXT_374 = ").";
  protected final String TEXT_375 = "()";
  protected final String TEXT_376 = ";";
  protected final String TEXT_377 = NL + "\t\t// TODO: implement this method to return the '";
  protected final String TEXT_378 = "' ";
  protected final String TEXT_379 = NL + "\t\t// Ensure that you remove @generated or mark it @generated NOT" + NL + "\t\tthrow new UnsupportedOperationException();";
  protected final String TEXT_380 = NL + "\t}" + NL;
  protected final String TEXT_381 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */" + NL + "\tpublic ";
  protected final String TEXT_382 = " basicGet";
  protected final String TEXT_383 = "()" + NL + "\t{";
  protected final String TEXT_384 = NL + "\t\tif (eContainerFeatureID != ";
  protected final String TEXT_385 = ") return null;" + NL + "\t\treturn (";
  protected final String TEXT_386 = ")eInternalContainer();";
  protected final String TEXT_387 = NL + "\t\treturn (";
  protected final String TEXT_388 = ")eVirtualGet(";
  protected final String TEXT_389 = ");";
  protected final String TEXT_390 = NL + "\t\treturn ";
  protected final String TEXT_391 = ";";
  protected final String TEXT_392 = NL + "\t\treturn (";
  protected final String TEXT_393 = ")((";
  protected final String TEXT_394 = ".Internal.Wrapper)get";
  protected final String TEXT_395 = "()).featureMap().get(";
  protected final String TEXT_396 = ", false);";
  protected final String TEXT_397 = NL + "\t\treturn (";
  protected final String TEXT_398 = ")get";
  protected final String TEXT_399 = "().get(";
  protected final String TEXT_400 = ", false);";
  protected final String TEXT_401 = NL + "\t\t// TODO: implement this method to return the '";
  protected final String TEXT_402 = "' ";
  protected final String TEXT_403 = NL + "\t\t// -> do not perform proxy resolution" + NL + "\t\t// Ensure that you remove @generated or mark it @generated NOT" + NL + "\t\tthrow new UnsupportedOperationException();";
  protected final String TEXT_404 = NL + "\t}" + NL;
  protected final String TEXT_405 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */" + NL + "\tpublic ";
  protected final String TEXT_406 = " basicSet";
  protected final String TEXT_407 = "(";
  protected final String TEXT_408 = " new";
  protected final String TEXT_409 = ", ";
  protected final String TEXT_410 = " msgs)" + NL + "\t{";
  protected final String TEXT_411 = NL + "\t\tObject old";
  protected final String TEXT_412 = " = eVirtualSet(";
  protected final String TEXT_413 = ", new";
  protected final String TEXT_414 = ");";
  protected final String TEXT_415 = NL + "\t\t";
  protected final String TEXT_416 = " old";
  protected final String TEXT_417 = " = ";
  protected final String TEXT_418 = ";" + NL + "\t\t";
  protected final String TEXT_419 = " = new";
  protected final String TEXT_420 = ";";
  protected final String TEXT_421 = NL + "\t\tboolean isSetChange = old";
  protected final String TEXT_422 = " == EVIRTUAL_NO_VALUE;";
  protected final String TEXT_423 = NL + "\t\tboolean old";
  protected final String TEXT_424 = "ESet = (";
  protected final String TEXT_425 = " & ";
  protected final String TEXT_426 = "_ESETFLAG) != 0;" + NL + "\t\t";
  protected final String TEXT_427 = " |= ";
  protected final String TEXT_428 = "_ESETFLAG;";
  protected final String TEXT_429 = NL + "\t\tboolean old";
  protected final String TEXT_430 = "ESet = ";
  protected final String TEXT_431 = "ESet;" + NL + "\t\t";
  protected final String TEXT_432 = "ESet = true;";
  protected final String TEXT_433 = NL + "\t\tif (eNotificationRequired())" + NL + "\t\t{";
  protected final String TEXT_434 = NL + "\t\t\t";
  protected final String TEXT_435 = " notification = new ";
  protected final String TEXT_436 = "(this, ";
  protected final String TEXT_437 = ".SET, ";
  protected final String TEXT_438 = ", ";
  protected final String TEXT_439 = "isSetChange ? null : old";
  protected final String TEXT_440 = "old";
  protected final String TEXT_441 = ", new";
  protected final String TEXT_442 = ", ";
  protected final String TEXT_443 = "isSetChange";
  protected final String TEXT_444 = "!old";
  protected final String TEXT_445 = "ESet";
  protected final String TEXT_446 = ");";
  protected final String TEXT_447 = NL + "\t\t\t";
  protected final String TEXT_448 = " notification = new ";
  protected final String TEXT_449 = "(this, ";
  protected final String TEXT_450 = ".SET, ";
  protected final String TEXT_451 = ", ";
  protected final String TEXT_452 = "old";
  protected final String TEXT_453 = " == EVIRTUAL_NO_VALUE ? null : old";
  protected final String TEXT_454 = "old";
  protected final String TEXT_455 = ", new";
  protected final String TEXT_456 = ");";
  protected final String TEXT_457 = NL + "\t\t\tif (msgs == null) msgs = notification; else msgs.add(notification);" + NL + "\t\t}";
  protected final String TEXT_458 = NL + "\t\treturn msgs;";
  protected final String TEXT_459 = NL + "\t\treturn ((";
  protected final String TEXT_460 = ".Internal)((";
  protected final String TEXT_461 = ".Internal.Wrapper)get";
  protected final String TEXT_462 = "()).featureMap()).basicAdd(";
  protected final String TEXT_463 = ", new";
  protected final String TEXT_464 = ", msgs);";
  protected final String TEXT_465 = NL + "\t\treturn ((";
  protected final String TEXT_466 = ".Internal)get";
  protected final String TEXT_467 = "()).basicAdd(";
  protected final String TEXT_468 = ", new";
  protected final String TEXT_469 = ", msgs);";
  protected final String TEXT_470 = NL + "\t\t// TODO: implement this method to set the contained '";
  protected final String TEXT_471 = "' ";
  protected final String TEXT_472 = NL + "\t\t// -> this method is automatically invoked to keep the containment relationship in synch" + NL + "\t\t// -> do not modify other features" + NL + "\t\t// -> return msgs, after adding any generated Notification to it (if it is null, a NotificationChain object must be created first)" + NL + "\t\t// Ensure that you remove @generated or mark it @generated NOT" + NL + "\t\tthrow new UnsupportedOperationException();";
  protected final String TEXT_473 = NL + "\t}" + NL;
  protected final String TEXT_474 = NL + "\t/**" + NL + "\t * Sets the value of the '{@link ";
  protected final String TEXT_475 = "#";
  protected final String TEXT_476 = " <em>";
  protected final String TEXT_477 = "</em>}' ";
  protected final String TEXT_478 = "." + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @param value the new value of the '<em>";
  protected final String TEXT_479 = "</em>' ";
  protected final String TEXT_480 = ".";
  protected final String TEXT_481 = NL + "\t * @see ";
  protected final String TEXT_482 = NL + "\t * @see #isSet";
  protected final String TEXT_483 = "()";
  protected final String TEXT_484 = NL + "\t * @see #unset";
  protected final String TEXT_485 = "()";
  protected final String TEXT_486 = NL + "\t * @see #";
  protected final String TEXT_487 = "()" + NL + "\t * @generated" + NL + "\t */";
  protected final String TEXT_488 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */";
  protected final String TEXT_489 = NL + "\tvoid set";
  protected final String TEXT_490 = "(";
  protected final String TEXT_491 = " value);" + NL;
  protected final String TEXT_492 = NL + "\tpublic void set";
  protected final String TEXT_493 = "(";
  protected final String TEXT_494 = " new";
  protected final String TEXT_495 = ")" + NL + "\t{";
  protected final String TEXT_496 = NL + "\t\teSet(";
  protected final String TEXT_497 = ", ";
  protected final String TEXT_498 = "new ";
  protected final String TEXT_499 = "(";
  protected final String TEXT_500 = "new";
  protected final String TEXT_501 = ")";
  protected final String TEXT_502 = ");";
  protected final String TEXT_503 = NL + "\t\tif (new";
  protected final String TEXT_504 = " != eInternalContainer() || (eContainerFeatureID != ";
  protected final String TEXT_505 = " && new";
  protected final String TEXT_506 = " != null))" + NL + "\t\t{" + NL + "\t\t\tif (";
  protected final String TEXT_507 = ".isAncestor(this, ";
  protected final String TEXT_508 = "new";
  protected final String TEXT_509 = "))" + NL + "\t\t\t\tthrow new ";
  protected final String TEXT_510 = "(\"Recursive containment not allowed for \" + toString());";
  protected final String TEXT_511 = NL + "\t\t\t";
  protected final String TEXT_512 = " msgs = null;" + NL + "\t\t\tif (eInternalContainer() != null)" + NL + "\t\t\t\tmsgs = eBasicRemoveFromContainer(msgs);" + NL + "\t\t\tif (new";
  protected final String TEXT_513 = " != null)" + NL + "\t\t\t\tmsgs = ((";
  protected final String TEXT_514 = ")new";
  protected final String TEXT_515 = ").eInverseAdd(this, ";
  protected final String TEXT_516 = ", ";
  protected final String TEXT_517 = ".class, msgs);" + NL + "\t\t\tmsgs = eBasicSetContainer((";
  protected final String TEXT_518 = ")new";
  protected final String TEXT_519 = ", ";
  protected final String TEXT_520 = ", msgs);" + NL + "\t\t\tif (msgs != null) msgs.dispatch();" + NL + "\t\t}";
  protected final String TEXT_521 = NL + "\t\telse if (eNotificationRequired())" + NL + "\t\t\teNotify(new ";
  protected final String TEXT_522 = "(this, ";
  protected final String TEXT_523 = ".SET, ";
  protected final String TEXT_524 = ", new";
  protected final String TEXT_525 = ", new";
  protected final String TEXT_526 = "));";
  protected final String TEXT_527 = NL + "\t\t";
  protected final String TEXT_528 = " ";
  protected final String TEXT_529 = " = (";
  protected final String TEXT_530 = ")eVirtualGet(";
  protected final String TEXT_531 = ");";
  protected final String TEXT_532 = NL + "\t\tif (new";
  protected final String TEXT_533 = " != ";
  protected final String TEXT_534 = ")" + NL + "\t\t{" + NL + "\t\t\t";
  protected final String TEXT_535 = " msgs = null;" + NL + "\t\t\tif (";
  protected final String TEXT_536 = " != null)";
  protected final String TEXT_537 = NL + "\t\t\t\tmsgs = ((";
  protected final String TEXT_538 = ")";
  protected final String TEXT_539 = ").eInverseRemove(this, EOPPOSITE_FEATURE_BASE - ";
  protected final String TEXT_540 = ", null, msgs);" + NL + "\t\t\tif (new";
  protected final String TEXT_541 = " != null)" + NL + "\t\t\t\tmsgs = ((";
  protected final String TEXT_542 = ")new";
  protected final String TEXT_543 = ").eInverseAdd(this, EOPPOSITE_FEATURE_BASE - ";
  protected final String TEXT_544 = ", null, msgs);";
  protected final String TEXT_545 = NL + "\t\t\t\tmsgs = ((";
  protected final String TEXT_546 = ")";
  protected final String TEXT_547 = ").eInverseRemove(this, ";
  protected final String TEXT_548 = ", ";
  protected final String TEXT_549 = ".class, msgs);" + NL + "\t\t\tif (new";
  protected final String TEXT_550 = " != null)" + NL + "\t\t\t\tmsgs = ((";
  protected final String TEXT_551 = ")new";
  protected final String TEXT_552 = ").eInverseAdd(this, ";
  protected final String TEXT_553 = ", ";
  protected final String TEXT_554 = ".class, msgs);";
  protected final String TEXT_555 = NL + "\t\t\tmsgs = basicSet";
  protected final String TEXT_556 = "(";
  protected final String TEXT_557 = "new";
  protected final String TEXT_558 = ", msgs);" + NL + "\t\t\tif (msgs != null) msgs.dispatch();" + NL + "\t\t}";
  protected final String TEXT_559 = NL + "\t\telse" + NL + "    \t{";
  protected final String TEXT_560 = NL + "\t\t\tboolean old";
  protected final String TEXT_561 = "ESet = eVirtualIsSet(";
  protected final String TEXT_562 = ");";
  protected final String TEXT_563 = NL + "\t\t\tboolean old";
  protected final String TEXT_564 = "ESet = (";
  protected final String TEXT_565 = " & ";
  protected final String TEXT_566 = "_ESETFLAG) != 0;";
  protected final String TEXT_567 = NL + "\t\t\t";
  protected final String TEXT_568 = " |= ";
  protected final String TEXT_569 = "_ESETFLAG;";
  protected final String TEXT_570 = NL + "\t\t\tboolean old";
  protected final String TEXT_571 = "ESet = ";
  protected final String TEXT_572 = "ESet;";
  protected final String TEXT_573 = NL + "\t\t\t";
  protected final String TEXT_574 = "ESet = true;";
  protected final String TEXT_575 = NL + "\t\t\tif (eNotificationRequired())" + NL + "\t\t\t\teNotify(new ";
  protected final String TEXT_576 = "(this, ";
  protected final String TEXT_577 = ".SET, ";
  protected final String TEXT_578 = ", new";
  protected final String TEXT_579 = ", new";
  protected final String TEXT_580 = ", !old";
  protected final String TEXT_581 = "ESet));";
  protected final String TEXT_582 = NL + "    \t}";
  protected final String TEXT_583 = NL + "\t\telse if (eNotificationRequired())" + NL + "\t\t\teNotify(new ";
  protected final String TEXT_584 = "(this, ";
  protected final String TEXT_585 = ".SET, ";
  protected final String TEXT_586 = ", new";
  protected final String TEXT_587 = ", new";
  protected final String TEXT_588 = "));";
  protected final String TEXT_589 = NL + "\t\t";
  protected final String TEXT_590 = " old";
  protected final String TEXT_591 = " = (";
  protected final String TEXT_592 = " & ";
  protected final String TEXT_593 = "_EFLAG) != 0;";
  protected final String TEXT_594 = NL + "\t\tif (new";
  protected final String TEXT_595 = ") ";
  protected final String TEXT_596 = " |= ";
  protected final String TEXT_597 = "_EFLAG; else ";
  protected final String TEXT_598 = " &= ~";
  protected final String TEXT_599 = "_EFLAG;";
  protected final String TEXT_600 = NL + "\t\t";
  protected final String TEXT_601 = " old";
  protected final String TEXT_602 = " = ";
  protected final String TEXT_603 = ";";
  protected final String TEXT_604 = NL + "\t\t";
  protected final String TEXT_605 = " ";
  protected final String TEXT_606 = " = new";
  protected final String TEXT_607 = " == null ? ";
  protected final String TEXT_608 = "_EDEFAULT : new";
  protected final String TEXT_609 = ";";
  protected final String TEXT_610 = NL + "\t\t";
  protected final String TEXT_611 = " = new";
  protected final String TEXT_612 = " == null ? ";
  protected final String TEXT_613 = "_EDEFAULT : new";
  protected final String TEXT_614 = ";";
  protected final String TEXT_615 = NL + "\t\t";
  protected final String TEXT_616 = " ";
  protected final String TEXT_617 = " = ";
  protected final String TEXT_618 = "new";
  protected final String TEXT_619 = ";";
  protected final String TEXT_620 = NL + "\t\t";
  protected final String TEXT_621 = " = ";
  protected final String TEXT_622 = "new";
  protected final String TEXT_623 = ";";
  protected final String TEXT_624 = NL + "\t\tObject old";
  protected final String TEXT_625 = " = eVirtualSet(";
  protected final String TEXT_626 = ", ";
  protected final String TEXT_627 = ");";
  protected final String TEXT_628 = NL + "\t\tboolean isSetChange = old";
  protected final String TEXT_629 = " == EVIRTUAL_NO_VALUE;";
  protected final String TEXT_630 = NL + "\t\tboolean old";
  protected final String TEXT_631 = "ESet = (";
  protected final String TEXT_632 = " & ";
  protected final String TEXT_633 = "_ESETFLAG) != 0;";
  protected final String TEXT_634 = NL + "\t\t";
  protected final String TEXT_635 = " |= ";
  protected final String TEXT_636 = "_ESETFLAG;";
  protected final String TEXT_637 = NL + "\t\tboolean old";
  protected final String TEXT_638 = "ESet = ";
  protected final String TEXT_639 = "ESet;";
  protected final String TEXT_640 = NL + "\t\t";
  protected final String TEXT_641 = "ESet = true;";
  protected final String TEXT_642 = NL + "\t\tif (eNotificationRequired())" + NL + "\t\t\teNotify(new ";
  protected final String TEXT_643 = "(this, ";
  protected final String TEXT_644 = ".SET, ";
  protected final String TEXT_645 = ", ";
  protected final String TEXT_646 = "isSetChange ? ";
  protected final String TEXT_647 = "null";
  protected final String TEXT_648 = "_EDEFAULT";
  protected final String TEXT_649 = " : old";
  protected final String TEXT_650 = "old";
  protected final String TEXT_651 = ", ";
  protected final String TEXT_652 = "new";
  protected final String TEXT_653 = ", ";
  protected final String TEXT_654 = "isSetChange";
  protected final String TEXT_655 = "!old";
  protected final String TEXT_656 = "ESet";
  protected final String TEXT_657 = "));";
  protected final String TEXT_658 = NL + "\t\tif (eNotificationRequired())" + NL + "\t\t\teNotify(new ";
  protected final String TEXT_659 = "(this, ";
  protected final String TEXT_660 = ".SET, ";
  protected final String TEXT_661 = ", ";
  protected final String TEXT_662 = "old";
  protected final String TEXT_663 = " == EVIRTUAL_NO_VALUE ? ";
  protected final String TEXT_664 = "null";
  protected final String TEXT_665 = "_EDEFAULT";
  protected final String TEXT_666 = " : old";
  protected final String TEXT_667 = "old";
  protected final String TEXT_668 = ", ";
  protected final String TEXT_669 = "new";
  protected final String TEXT_670 = "));";
  protected final String TEXT_671 = NL + "\t\t((";
  protected final String TEXT_672 = ".Internal)((";
  protected final String TEXT_673 = ".Internal.Wrapper)get";
  protected final String TEXT_674 = "()).featureMap()).set(";
  protected final String TEXT_675 = ", ";
  protected final String TEXT_676 = "new ";
  protected final String TEXT_677 = "(";
  protected final String TEXT_678 = "new";
  protected final String TEXT_679 = ")";
  protected final String TEXT_680 = ");";
  protected final String TEXT_681 = NL + "\t\t((";
  protected final String TEXT_682 = ".Internal)get";
  protected final String TEXT_683 = "()).set(";
  protected final String TEXT_684 = ", ";
  protected final String TEXT_685 = "new ";
  protected final String TEXT_686 = "(";
  protected final String TEXT_687 = "new";
  protected final String TEXT_688 = ")";
  protected final String TEXT_689 = ");";
  protected final String TEXT_690 = NL + "\t\t// TODO: implement this method to set the '";
  protected final String TEXT_691 = "' ";
  protected final String TEXT_692 = NL + "\t\t// Ensure that you remove @generated or mark it @generated NOT" + NL + "\t\tthrow new UnsupportedOperationException();";
  protected final String TEXT_693 = NL + "\t}" + NL;
  protected final String TEXT_694 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */" + NL + "\tpublic ";
  protected final String TEXT_695 = " basicUnset";
  protected final String TEXT_696 = "(";
  protected final String TEXT_697 = " msgs)" + NL + "\t{";
  protected final String TEXT_698 = NL + "\t\tObject old";
  protected final String TEXT_699 = " = eVirtualUnset(";
  protected final String TEXT_700 = ");";
  protected final String TEXT_701 = NL + "\t\t";
  protected final String TEXT_702 = " old";
  protected final String TEXT_703 = " = ";
  protected final String TEXT_704 = ";" + NL + "\t\t";
  protected final String TEXT_705 = " = null;";
  protected final String TEXT_706 = NL + "\t\tboolean isSetChange = old";
  protected final String TEXT_707 = " != EVIRTUAL_NO_VALUE;";
  protected final String TEXT_708 = NL + "\t\tboolean old";
  protected final String TEXT_709 = "ESet = (";
  protected final String TEXT_710 = " & ";
  protected final String TEXT_711 = "_ESETFLAG) != 0;" + NL + "\t\t";
  protected final String TEXT_712 = " &= ~";
  protected final String TEXT_713 = "_ESETFLAG;";
  protected final String TEXT_714 = NL + "\t\tboolean old";
  protected final String TEXT_715 = "ESet = ";
  protected final String TEXT_716 = "ESet;" + NL + "\t\t";
  protected final String TEXT_717 = "ESet = false;";
  protected final String TEXT_718 = NL + "\t\tif (eNotificationRequired())" + NL + "\t\t{" + NL + "\t\t\t";
  protected final String TEXT_719 = " notification = new ";
  protected final String TEXT_720 = "(this, ";
  protected final String TEXT_721 = ".UNSET, ";
  protected final String TEXT_722 = ", ";
  protected final String TEXT_723 = "isSetChange ? old";
  protected final String TEXT_724 = " : null";
  protected final String TEXT_725 = "old";
  protected final String TEXT_726 = ", null, ";
  protected final String TEXT_727 = "isSetChange";
  protected final String TEXT_728 = "old";
  protected final String TEXT_729 = "ESet";
  protected final String TEXT_730 = ");" + NL + "\t\t\tif (msgs == null) msgs = notification; else msgs.add(notification);" + NL + "\t\t}" + NL + "\t\treturn msgs;";
  protected final String TEXT_731 = NL + "\t\t// TODO: implement this method to unset the contained '";
  protected final String TEXT_732 = "' ";
  protected final String TEXT_733 = NL + "\t\t// -> this method is automatically invoked to keep the containment relationship in synch" + NL + "\t\t// -> do not modify other features" + NL + "\t\t// -> return msgs, after adding any generated Notification to it (if it is null, a NotificationChain object must be created first)" + NL + "\t\t// Ensure that you remove @generated or mark it @generated NOT" + NL + "\t\tthrow new UnsupportedOperationException();";
  protected final String TEXT_734 = NL + "\t}" + NL;
  protected final String TEXT_735 = NL + "\t/**" + NL + "\t * Unsets the value of the '{@link ";
  protected final String TEXT_736 = "#";
  protected final String TEXT_737 = " <em>";
  protected final String TEXT_738 = "</em>}' ";
  protected final String TEXT_739 = "." + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->";
  protected final String TEXT_740 = NL + "\t * @see #isSet";
  protected final String TEXT_741 = "()";
  protected final String TEXT_742 = NL + "\t * @see #";
  protected final String TEXT_743 = "()";
  protected final String TEXT_744 = NL + "\t * @see #set";
  protected final String TEXT_745 = "(";
  protected final String TEXT_746 = ")";
  protected final String TEXT_747 = NL + "\t * @generated" + NL + "\t */";
  protected final String TEXT_748 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */";
  protected final String TEXT_749 = NL + "\tvoid unset";
  protected final String TEXT_750 = "();" + NL;
  protected final String TEXT_751 = NL + "\tpublic void unset";
  protected final String TEXT_752 = "()" + NL + "\t{";
  protected final String TEXT_753 = NL + "\t\teUnset(";
  protected final String TEXT_754 = ");";
  protected final String TEXT_755 = NL + "\t\t((";
  protected final String TEXT_756 = ".Unsettable)get";
  protected final String TEXT_757 = "()).unset();";
  protected final String TEXT_758 = NL + "\t\t";
  protected final String TEXT_759 = " ";
  protected final String TEXT_760 = " = (";
  protected final String TEXT_761 = ")eVirtualGet(";
  protected final String TEXT_762 = ");";
  protected final String TEXT_763 = NL + "\t\tif (";
  protected final String TEXT_764 = " != null)" + NL + "\t\t{" + NL + "\t\t\t";
  protected final String TEXT_765 = " msgs = null;";
  protected final String TEXT_766 = NL + "\t\t\tmsgs = ((";
  protected final String TEXT_767 = ")";
  protected final String TEXT_768 = ").eInverseRemove(this, EOPPOSITE_FEATURE_BASE - ";
  protected final String TEXT_769 = ", null, msgs);";
  protected final String TEXT_770 = NL + "\t\t\tmsgs = ((";
  protected final String TEXT_771 = ")";
  protected final String TEXT_772 = ").eInverseRemove(this, ";
  protected final String TEXT_773 = ", ";
  protected final String TEXT_774 = ".class, msgs);";
  protected final String TEXT_775 = NL + "\t\t\tmsgs = basicUnset";
  protected final String TEXT_776 = "(msgs);" + NL + "\t\t\tif (msgs != null) msgs.dispatch();" + NL + "\t\t}" + NL + "\t\telse" + NL + "    \t{";
  protected final String TEXT_777 = NL + "\t\t\tboolean old";
  protected final String TEXT_778 = "ESet = eVirtualIsSet(";
  protected final String TEXT_779 = ");";
  protected final String TEXT_780 = NL + "\t\t\tboolean old";
  protected final String TEXT_781 = "ESet = (";
  protected final String TEXT_782 = " & ";
  protected final String TEXT_783 = "_ESETFLAG) != 0;";
  protected final String TEXT_784 = NL + "\t\t\t";
  protected final String TEXT_785 = " &= ~";
  protected final String TEXT_786 = "_ESETFLAG;";
  protected final String TEXT_787 = NL + "\t\t\tboolean old";
  protected final String TEXT_788 = "ESet = ";
  protected final String TEXT_789 = "ESet;";
  protected final String TEXT_790 = NL + "\t\t\t";
  protected final String TEXT_791 = "ESet = false;";
  protected final String TEXT_792 = NL + "\t\t\tif (eNotificationRequired())" + NL + "\t\t\t\teNotify(new ";
  protected final String TEXT_793 = "(this, ";
  protected final String TEXT_794 = ".UNSET, ";
  protected final String TEXT_795 = ", null, null, old";
  protected final String TEXT_796 = "ESet));";
  protected final String TEXT_797 = NL + "    \t}";
  protected final String TEXT_798 = NL + "\t\t";
  protected final String TEXT_799 = " old";
  protected final String TEXT_800 = " = (";
  protected final String TEXT_801 = " & ";
  protected final String TEXT_802 = "_EFLAG) != 0;";
  protected final String TEXT_803 = NL + "\t\tObject old";
  protected final String TEXT_804 = " = eVirtualUnset(";
  protected final String TEXT_805 = ");";
  protected final String TEXT_806 = NL + "\t\t";
  protected final String TEXT_807 = " old";
  protected final String TEXT_808 = " = ";
  protected final String TEXT_809 = ";";
  protected final String TEXT_810 = NL + "\t\tboolean isSetChange = old";
  protected final String TEXT_811 = " != EVIRTUAL_NO_VALUE;";
  protected final String TEXT_812 = NL + "\t\tboolean old";
  protected final String TEXT_813 = "ESet = (";
  protected final String TEXT_814 = " & ";
  protected final String TEXT_815 = "_ESETFLAG) != 0;";
  protected final String TEXT_816 = NL + "\t\tboolean old";
  protected final String TEXT_817 = "ESet = ";
  protected final String TEXT_818 = "ESet;";
  protected final String TEXT_819 = NL + "\t\t";
  protected final String TEXT_820 = " = null;";
  protected final String TEXT_821 = NL + "\t\t";
  protected final String TEXT_822 = " &= ~";
  protected final String TEXT_823 = "_ESETFLAG;";
  protected final String TEXT_824 = NL + "\t\t";
  protected final String TEXT_825 = "ESet = false;";
  protected final String TEXT_826 = NL + "\t\tif (eNotificationRequired())" + NL + "\t\t\teNotify(new ";
  protected final String TEXT_827 = "(this, ";
  protected final String TEXT_828 = ".UNSET, ";
  protected final String TEXT_829 = ", ";
  protected final String TEXT_830 = "isSetChange ? old";
  protected final String TEXT_831 = " : null";
  protected final String TEXT_832 = "old";
  protected final String TEXT_833 = ", null, ";
  protected final String TEXT_834 = "isSetChange";
  protected final String TEXT_835 = "old";
  protected final String TEXT_836 = "ESet";
  protected final String TEXT_837 = "));";
  protected final String TEXT_838 = NL + "\t\tif (";
  protected final String TEXT_839 = "_EDEFAULT) ";
  protected final String TEXT_840 = " |= ";
  protected final String TEXT_841 = "_EFLAG; else ";
  protected final String TEXT_842 = " &= ~";
  protected final String TEXT_843 = "_EFLAG;";
  protected final String TEXT_844 = NL + "\t\t";
  protected final String TEXT_845 = " = ";
  protected final String TEXT_846 = "_EDEFAULT;";
  protected final String TEXT_847 = NL + "\t\t";
  protected final String TEXT_848 = " &= ~";
  protected final String TEXT_849 = "_ESETFLAG;";
  protected final String TEXT_850 = NL + "\t\t";
  protected final String TEXT_851 = "ESet = false;";
  protected final String TEXT_852 = NL + "\t\tif (eNotificationRequired())" + NL + "\t\t\teNotify(new ";
  protected final String TEXT_853 = "(this, ";
  protected final String TEXT_854 = ".UNSET, ";
  protected final String TEXT_855 = ", ";
  protected final String TEXT_856 = "isSetChange ? old";
  protected final String TEXT_857 = " : ";
  protected final String TEXT_858 = "_EDEFAULT";
  protected final String TEXT_859 = "old";
  protected final String TEXT_860 = ", ";
  protected final String TEXT_861 = "_EDEFAULT, ";
  protected final String TEXT_862 = "isSetChange";
  protected final String TEXT_863 = "old";
  protected final String TEXT_864 = "ESet";
  protected final String TEXT_865 = "));";
  protected final String TEXT_866 = NL + "\t\t((";
  protected final String TEXT_867 = ".Internal)((";
  protected final String TEXT_868 = ".Internal.Wrapper)get";
  protected final String TEXT_869 = "()).featureMap()).clear(";
  protected final String TEXT_870 = ");";
  protected final String TEXT_871 = NL + "\t\t((";
  protected final String TEXT_872 = ".Internal)get";
  protected final String TEXT_873 = "()).clear(";
  protected final String TEXT_874 = ");";
  protected final String TEXT_875 = NL + "\t\t// TODO: implement this method to unset the '";
  protected final String TEXT_876 = "' ";
  protected final String TEXT_877 = NL + "\t\t// Ensure that you remove @generated or mark it @generated NOT" + NL + "\t\tthrow new UnsupportedOperationException();";
  protected final String TEXT_878 = NL + "\t}" + NL;
  protected final String TEXT_879 = NL + "\t/**" + NL + "\t * Returns whether the value of the '{@link ";
  protected final String TEXT_880 = "#";
  protected final String TEXT_881 = " <em>";
  protected final String TEXT_882 = "</em>}' ";
  protected final String TEXT_883 = " is set." + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @return whether the value of the '<em>";
  protected final String TEXT_884 = "</em>' ";
  protected final String TEXT_885 = " is set.";
  protected final String TEXT_886 = NL + "\t * @see #unset";
  protected final String TEXT_887 = "()";
  protected final String TEXT_888 = NL + "\t * @see #";
  protected final String TEXT_889 = "()";
  protected final String TEXT_890 = NL + "\t * @see #set";
  protected final String TEXT_891 = "(";
  protected final String TEXT_892 = ")";
  protected final String TEXT_893 = NL + "\t * @generated" + NL + "\t */";
  protected final String TEXT_894 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */";
  protected final String TEXT_895 = NL + "\tboolean isSet";
  protected final String TEXT_896 = "();" + NL;
  protected final String TEXT_897 = NL + "\tpublic boolean isSet";
  protected final String TEXT_898 = "()" + NL + "\t{";
  protected final String TEXT_899 = NL + "\t\treturn eIsSet(";
  protected final String TEXT_900 = ");";
  protected final String TEXT_901 = NL + "\t\t";
  protected final String TEXT_902 = " ";
  protected final String TEXT_903 = " = (";
  protected final String TEXT_904 = ")eVirtualGet(";
  protected final String TEXT_905 = ");";
  protected final String TEXT_906 = NL + "\t\treturn ";
  protected final String TEXT_907 = " != null && ((";
  protected final String TEXT_908 = ".Unsettable)";
  protected final String TEXT_909 = ").isSet();";
  protected final String TEXT_910 = NL + "\t\treturn eVirtualIsSet(";
  protected final String TEXT_911 = ");";
  protected final String TEXT_912 = NL + "\t\treturn (";
  protected final String TEXT_913 = " & ";
  protected final String TEXT_914 = "_ESETFLAG) != 0;";
  protected final String TEXT_915 = NL + "\t\treturn ";
  protected final String TEXT_916 = "ESet;";
  protected final String TEXT_917 = NL + "\t\treturn !((";
  protected final String TEXT_918 = ".Internal)((";
  protected final String TEXT_919 = ".Internal.Wrapper)get";
  protected final String TEXT_920 = "()).featureMap()).isEmpty(";
  protected final String TEXT_921 = ");";
  protected final String TEXT_922 = NL + "\t\treturn !((";
  protected final String TEXT_923 = ".Internal)get";
  protected final String TEXT_924 = "()).isEmpty(";
  protected final String TEXT_925 = ");";
  protected final String TEXT_926 = NL + "\t\t// TODO: implement this method to return whether the '";
  protected final String TEXT_927 = "' ";
  protected final String TEXT_928 = " is set" + NL + "\t\t// Ensure that you remove @generated or mark it @generated NOT" + NL + "\t\tthrow new UnsupportedOperationException();";
  protected final String TEXT_929 = NL + "\t}" + NL;
  protected final String TEXT_930 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->";
  protected final String TEXT_931 = NL + "\t * <!-- begin-model-doc -->" + NL + "\t * ";
  protected final String TEXT_932 = NL + "\t * <!-- end-model-doc -->";
  protected final String TEXT_933 = NL + "\t * @model ";
  protected final String TEXT_934 = NL + "\t *        ";
  protected final String TEXT_935 = NL + "\t * @model";
  protected final String TEXT_936 = NL + "\t * @generated" + NL + "\t */";
  protected final String TEXT_937 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */";
  protected final String TEXT_938 = NL + "\t";
  protected final String TEXT_939 = " ";
  protected final String TEXT_940 = "(";
  protected final String TEXT_941 = ")";
  protected final String TEXT_942 = ";" + NL;
  protected final String TEXT_943 = NL + "\tpublic ";
  protected final String TEXT_944 = " ";
  protected final String TEXT_945 = "(";
  protected final String TEXT_946 = ")";
  protected final String TEXT_947 = NL + "\t{";
  protected final String TEXT_948 = NL + "\t\t";
  protected final String TEXT_949 = NL + "\t\t// TODO: implement this method" + NL + "\t\t// -> specify the condition that violates the invariant" + NL + "\t\t// -> verify the details of the diagnostic, including severity and message" + NL + "\t\t// Ensure that you remove @generated or mark it @generated NOT" + NL + "\t\tif (false)" + NL + "\t\t{" + NL + "\t\t\tif (";
  protected final String TEXT_950 = " != null)" + NL + "\t\t\t{" + NL + "\t\t\t\t";
  protected final String TEXT_951 = ".add" + NL + "\t\t\t\t\t(new ";
  protected final String TEXT_952 = NL + "\t\t\t\t\t\t(";
  protected final String TEXT_953 = ".ERROR," + NL + "\t\t\t\t\t\t ";
  protected final String TEXT_954 = ".DIAGNOSTIC_SOURCE," + NL + "\t\t\t\t\t\t ";
  protected final String TEXT_955 = ".";
  protected final String TEXT_956 = "," + NL + "\t\t\t\t\t\t ";
  protected final String TEXT_957 = ".INSTANCE.getString(\"_UI_GenericInvariant_diagnostic\", new Object[] { \"";
  protected final String TEXT_958 = "\", ";
  protected final String TEXT_959 = ".getObjectLabel(this, ";
  protected final String TEXT_960 = ") }),";
  protected final String TEXT_961 = NL + "\t\t\t\t\t\t new Object [] { this }));" + NL + "\t\t\t}" + NL + "\t\t\treturn false;" + NL + "\t\t}" + NL + "\t\treturn true;";
  protected final String TEXT_962 = NL + "\t\t// TODO: implement this method" + NL + "\t\t// Ensure that you remove @generated or mark it @generated NOT" + NL + "\t\tthrow new UnsupportedOperationException();";
  protected final String TEXT_963 = NL + "\t}" + NL;
  protected final String TEXT_964 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */" + NL + "\tpublic ";
  protected final String TEXT_965 = " eInverseAdd(";
  protected final String TEXT_966 = " otherEnd, int featureID, ";
  protected final String TEXT_967 = " msgs)" + NL + "\t{" + NL + "\t\tswitch (featureID)" + NL + "\t\t{";
  protected final String TEXT_968 = NL + "\t\t\tcase ";
  protected final String TEXT_969 = ":";
  protected final String TEXT_970 = NL + "\t\t\t\treturn ((";
  protected final String TEXT_971 = ")((";
  protected final String TEXT_972 = ".InternalMapView)";
  protected final String TEXT_973 = "()).eMap()).basicAdd(otherEnd, msgs);";
  protected final String TEXT_974 = NL + "\t\t\t\treturn ((";
  protected final String TEXT_975 = ")";
  protected final String TEXT_976 = "()).basicAdd(otherEnd, msgs);";
  protected final String TEXT_977 = NL + "\t\t\t\tif (eInternalContainer() != null)" + NL + "\t\t\t\t\tmsgs = eBasicRemoveFromContainer(msgs);" + NL + "\t\t\t\treturn eBasicSetContainer(otherEnd, ";
  protected final String TEXT_978 = ", msgs);";
  protected final String TEXT_979 = NL + "\t\t\t\t";
  protected final String TEXT_980 = " ";
  protected final String TEXT_981 = " = (";
  protected final String TEXT_982 = ")eVirtualGet(";
  protected final String TEXT_983 = ");";
  protected final String TEXT_984 = NL + "\t\t\t\tif (";
  protected final String TEXT_985 = " != null)";
  protected final String TEXT_986 = NL + "\t\t\t\t\tmsgs = ((";
  protected final String TEXT_987 = ")";
  protected final String TEXT_988 = ").eInverseRemove(this, EOPPOSITE_FEATURE_BASE - ";
  protected final String TEXT_989 = ", null, msgs);";
  protected final String TEXT_990 = NL + "\t\t\t\t\tmsgs = ((";
  protected final String TEXT_991 = ")";
  protected final String TEXT_992 = ").eInverseRemove(this, ";
  protected final String TEXT_993 = ", ";
  protected final String TEXT_994 = ".class, msgs);";
  protected final String TEXT_995 = NL + "\t\t\t\treturn basicSet";
  protected final String TEXT_996 = "((";
  protected final String TEXT_997 = ")otherEnd, msgs);";
  protected final String TEXT_998 = NL + "\t\t}";
  protected final String TEXT_999 = NL + "\t\treturn super.eInverseAdd(otherEnd, featureID, msgs);";
  protected final String TEXT_1000 = NL + "\t\treturn eDynamicInverseAdd(otherEnd, featureID, msgs);";
  protected final String TEXT_1001 = NL + "\t}" + NL;
  protected final String TEXT_1002 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */" + NL + "\tpublic ";
  protected final String TEXT_1003 = " eInverseRemove(";
  protected final String TEXT_1004 = " otherEnd, int featureID, ";
  protected final String TEXT_1005 = " msgs)" + NL + "\t{" + NL + "\t\tswitch (featureID)" + NL + "\t\t{";
  protected final String TEXT_1006 = NL + "\t\t\tcase ";
  protected final String TEXT_1007 = ":";
  protected final String TEXT_1008 = NL + "\t\t\t\treturn ((";
  protected final String TEXT_1009 = ")((";
  protected final String TEXT_1010 = ".InternalMapView)";
  protected final String TEXT_1011 = "()).eMap()).basicRemove(otherEnd, msgs);";
  protected final String TEXT_1012 = NL + "\t\t\t\treturn ((";
  protected final String TEXT_1013 = ")((";
  protected final String TEXT_1014 = ".Internal.Wrapper)";
  protected final String TEXT_1015 = "()).featureMap()).basicRemove(otherEnd, msgs);";
  protected final String TEXT_1016 = NL + "\t\t\t\treturn ((";
  protected final String TEXT_1017 = ")";
  protected final String TEXT_1018 = "()).basicRemove(otherEnd, msgs);";
  protected final String TEXT_1019 = NL + "\t\t\t\treturn eBasicSetContainer(null, ";
  protected final String TEXT_1020 = ", msgs);";
  protected final String TEXT_1021 = NL + "\t\t\t\treturn basicUnset";
  protected final String TEXT_1022 = "(msgs);";
  protected final String TEXT_1023 = NL + "\t\t\t\treturn basicSet";
  protected final String TEXT_1024 = "(null, msgs);";
  protected final String TEXT_1025 = NL + "\t\t}";
  protected final String TEXT_1026 = NL + "\t\treturn super.eInverseRemove(otherEnd, featureID, msgs);";
  protected final String TEXT_1027 = NL + "\t\treturn eDynamicInverseRemove(otherEnd, featureID, msgs);";
  protected final String TEXT_1028 = NL + "\t}" + NL;
  protected final String TEXT_1029 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */" + NL + "\tpublic ";
  protected final String TEXT_1030 = " eBasicRemoveFromContainerFeature(";
  protected final String TEXT_1031 = " msgs)" + NL + "\t{" + NL + "\t\tswitch (eContainerFeatureID)" + NL + "\t\t{";
  protected final String TEXT_1032 = NL + "\t\t\tcase ";
  protected final String TEXT_1033 = ":" + NL + "\t\t\t\treturn eInternalContainer().eInverseRemove(this, ";
  protected final String TEXT_1034 = ", ";
  protected final String TEXT_1035 = ".class, msgs);";
  protected final String TEXT_1036 = NL + "\t\t}";
  protected final String TEXT_1037 = NL + "\t\treturn super.eBasicRemoveFromContainerFeature(msgs);";
  protected final String TEXT_1038 = NL + "\t\treturn eDynamicBasicRemoveFromContainer(msgs);";
  protected final String TEXT_1039 = NL + "\t}" + NL;
  protected final String TEXT_1040 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */" + NL + "\tpublic Object eGet(int featureID, boolean resolve, boolean coreType)" + NL + "\t{" + NL + "\t\tswitch (featureID)" + NL + "\t\t{";
  protected final String TEXT_1041 = NL + "\t\t\tcase ";
  protected final String TEXT_1042 = ":";
  protected final String TEXT_1043 = NL + "\t\t\t\treturn ";
  protected final String TEXT_1044 = "() ? Boolean.TRUE : Boolean.FALSE;";
  protected final String TEXT_1045 = NL + "\t\t\t\treturn new ";
  protected final String TEXT_1046 = "(";
  protected final String TEXT_1047 = "());";
  protected final String TEXT_1048 = NL + "\t\t\t\tif (resolve) return ";
  protected final String TEXT_1049 = "();" + NL + "\t\t\t\treturn basicGet";
  protected final String TEXT_1050 = "();";
  protected final String TEXT_1051 = NL + "\t\t\t\tif (coreType) return ((";
  protected final String TEXT_1052 = ".InternalMapView)";
  protected final String TEXT_1053 = "()).eMap();" + NL + "\t\t\t\telse return ";
  protected final String TEXT_1054 = "();";
  protected final String TEXT_1055 = NL + "\t\t\t\tif (coreType) return ";
  protected final String TEXT_1056 = "();" + NL + "\t\t\t\telse return ";
  protected final String TEXT_1057 = "().map();";
  protected final String TEXT_1058 = NL + "\t\t\t\tif (coreType) return ((";
  protected final String TEXT_1059 = ".Internal.Wrapper)";
  protected final String TEXT_1060 = "()).featureMap();" + NL + "\t\t\t\treturn ";
  protected final String TEXT_1061 = "();";
  protected final String TEXT_1062 = NL + "\t\t\t\tif (coreType) return ";
  protected final String TEXT_1063 = "();" + NL + "\t\t\t\treturn ((";
  protected final String TEXT_1064 = ".Internal)";
  protected final String TEXT_1065 = "()).getWrapper();";
  protected final String TEXT_1066 = NL + "\t\t\t\treturn ";
  protected final String TEXT_1067 = "();";
  protected final String TEXT_1068 = NL + "\t\t}";
  protected final String TEXT_1069 = NL + "\t\treturn super.eGet(featureID, resolve, coreType);";
  protected final String TEXT_1070 = NL + "\t\treturn eDynamicGet(featureID, resolve, coreType);";
  protected final String TEXT_1071 = NL + "\t}" + NL;
  protected final String TEXT_1072 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */" + NL + "\tpublic void eSet(int featureID, Object newValue)" + NL + "\t{" + NL + "\t\tswitch (featureID)" + NL + "\t\t{";
  protected final String TEXT_1073 = NL + "\t\t\tcase ";
  protected final String TEXT_1074 = ":";
  protected final String TEXT_1075 = NL + "\t\t\t\t((";
  protected final String TEXT_1076 = ".Internal)((";
  protected final String TEXT_1077 = ".Internal.Wrapper)";
  protected final String TEXT_1078 = "()).featureMap()).set(newValue);";
  protected final String TEXT_1079 = NL + "\t\t\t\t((";
  protected final String TEXT_1080 = ".Internal)";
  protected final String TEXT_1081 = "()).set(newValue);";
  protected final String TEXT_1082 = NL + "\t\t\t\t((";
  protected final String TEXT_1083 = ".Setting)((";
  protected final String TEXT_1084 = ".InternalMapView)";
  protected final String TEXT_1085 = "()).eMap()).set(newValue);";
  protected final String TEXT_1086 = NL + "\t\t\t\t((";
  protected final String TEXT_1087 = ".Setting)";
  protected final String TEXT_1088 = "()).set(newValue);";
  protected final String TEXT_1089 = NL + "\t\t\t\t";
  protected final String TEXT_1090 = "().clear();" + NL + "\t\t\t\t";
  protected final String TEXT_1091 = "().addAll((";
  protected final String TEXT_1092 = ")newValue);";
  protected final String TEXT_1093 = NL + "\t\t\t\tset";
  protected final String TEXT_1094 = "(((";
  protected final String TEXT_1095 = ")newValue).";
  protected final String TEXT_1096 = "());";
  protected final String TEXT_1097 = NL + "\t\t\t\tset";
  protected final String TEXT_1098 = "((";
  protected final String TEXT_1099 = ")newValue);";
  protected final String TEXT_1100 = NL + "\t\t\t\treturn;";
  protected final String TEXT_1101 = NL + "\t\t}";
  protected final String TEXT_1102 = NL + "\t\tsuper.eSet(featureID, newValue);";
  protected final String TEXT_1103 = NL + "\t\teDynamicSet(featureID, newValue);";
  protected final String TEXT_1104 = NL + "\t}" + NL + "" + NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */" + NL + "\tpublic void eUnset(int featureID)" + NL + "\t{" + NL + "\t\tswitch (featureID)" + NL + "\t\t{";
  protected final String TEXT_1105 = NL + "\t\t\tcase ";
  protected final String TEXT_1106 = ":";
  protected final String TEXT_1107 = NL + "\t\t\t\t((";
  protected final String TEXT_1108 = ".Internal.Wrapper)";
  protected final String TEXT_1109 = "()).featureMap().clear();";
  protected final String TEXT_1110 = NL + "\t\t\t\t";
  protected final String TEXT_1111 = "().clear();";
  protected final String TEXT_1112 = NL + "\t\t\t\tunset";
  protected final String TEXT_1113 = "();";
  protected final String TEXT_1114 = NL + "\t\t\t\tset";
  protected final String TEXT_1115 = "((";
  protected final String TEXT_1116 = ")null);";
  protected final String TEXT_1117 = NL + "\t\t\t\tset";
  protected final String TEXT_1118 = "(";
  protected final String TEXT_1119 = "_EDEFAULT);";
  protected final String TEXT_1120 = NL + "\t\t\t\treturn;";
  protected final String TEXT_1121 = NL + "\t\t}";
  protected final String TEXT_1122 = NL + "\t\tsuper.eUnset(featureID);";
  protected final String TEXT_1123 = NL + "\t\teDynamicUnset(featureID);";
  protected final String TEXT_1124 = NL + "\t}" + NL;
  protected final String TEXT_1125 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */" + NL + "\tpublic boolean eIsSet(int featureID)" + NL + "\t{" + NL + "\t\tswitch (featureID)" + NL + "\t\t{";
  protected final String TEXT_1126 = NL + "\t\t\tcase ";
  protected final String TEXT_1127 = ":";
  protected final String TEXT_1128 = NL + "\t\t\t\treturn !((";
  protected final String TEXT_1129 = ".Internal.Wrapper)";
  protected final String TEXT_1130 = "()).featureMap().isEmpty();";
  protected final String TEXT_1131 = NL + "\t\t\t\treturn ";
  protected final String TEXT_1132 = " != null && !";
  protected final String TEXT_1133 = ".featureMap().isEmpty();";
  protected final String TEXT_1134 = NL + "\t\t\t\treturn ";
  protected final String TEXT_1135 = " != null && !";
  protected final String TEXT_1136 = ".isEmpty();";
  protected final String TEXT_1137 = NL + "\t\t\t\t";
  protected final String TEXT_1138 = " ";
  protected final String TEXT_1139 = " = (";
  protected final String TEXT_1140 = ")eVirtualGet(";
  protected final String TEXT_1141 = ");" + NL + "\t\t\t\treturn ";
  protected final String TEXT_1142 = " != null && !";
  protected final String TEXT_1143 = ".isEmpty();";
  protected final String TEXT_1144 = NL + "\t\t\t\treturn !";
  protected final String TEXT_1145 = "().isEmpty();";
  protected final String TEXT_1146 = NL + "\t\t\t\treturn isSet";
  protected final String TEXT_1147 = "();";
  protected final String TEXT_1148 = NL + "\t\t\t\treturn ";
  protected final String TEXT_1149 = " != null;";
  protected final String TEXT_1150 = NL + "\t\t\t\treturn eVirtualGet(";
  protected final String TEXT_1151 = ") != null;";
  protected final String TEXT_1152 = NL + "\t\t\t\treturn basicGet";
  protected final String TEXT_1153 = "() != null;";
  protected final String TEXT_1154 = NL + "\t\t\t\treturn ";
  protected final String TEXT_1155 = " != null;";
  protected final String TEXT_1156 = NL + "\t\t\t\treturn eVirtualGet(";
  protected final String TEXT_1157 = ") != null;";
  protected final String TEXT_1158 = NL + "\t\t\t\treturn ";
  protected final String TEXT_1159 = "() != null;";
  protected final String TEXT_1160 = NL + "\t\t\t\treturn ((";
  protected final String TEXT_1161 = " & ";
  protected final String TEXT_1162 = "_EFLAG) != 0) != ";
  protected final String TEXT_1163 = "_EDEFAULT;";
  protected final String TEXT_1164 = NL + "\t\t\t\treturn ";
  protected final String TEXT_1165 = " != ";
  protected final String TEXT_1166 = "_EDEFAULT;";
  protected final String TEXT_1167 = NL + "\t\t\t\treturn eVirtualGet(";
  protected final String TEXT_1168 = ", ";
  protected final String TEXT_1169 = "_EDEFAULT) != ";
  protected final String TEXT_1170 = "_EDEFAULT;";
  protected final String TEXT_1171 = NL + "\t\t\t\treturn ";
  protected final String TEXT_1172 = "() != ";
  protected final String TEXT_1173 = "_EDEFAULT;";
  protected final String TEXT_1174 = NL + "\t\t\t\treturn ";
  protected final String TEXT_1175 = "_EDEFAULT == null ? ";
  protected final String TEXT_1176 = " != null : !";
  protected final String TEXT_1177 = "_EDEFAULT.equals(";
  protected final String TEXT_1178 = ");";
  protected final String TEXT_1179 = NL + "\t\t\t\t";
  protected final String TEXT_1180 = " ";
  protected final String TEXT_1181 = " = (";
  protected final String TEXT_1182 = ")eVirtualGet(";
  protected final String TEXT_1183 = ", ";
  protected final String TEXT_1184 = "_EDEFAULT);" + NL + "\t\t\t\treturn ";
  protected final String TEXT_1185 = "_EDEFAULT == null ? ";
  protected final String TEXT_1186 = " != null : !";
  protected final String TEXT_1187 = "_EDEFAULT.equals(";
  protected final String TEXT_1188 = ");";
  protected final String TEXT_1189 = NL + "\t\t\t\treturn ";
  protected final String TEXT_1190 = "_EDEFAULT == null ? ";
  protected final String TEXT_1191 = "() != null : !";
  protected final String TEXT_1192 = "_EDEFAULT.equals(";
  protected final String TEXT_1193 = "());";
  protected final String TEXT_1194 = NL + "\t\t}";
  protected final String TEXT_1195 = NL + "\t\treturn super.eIsSet(featureID);";
  protected final String TEXT_1196 = NL + "\t\treturn eDynamicIsSet(featureID);";
  protected final String TEXT_1197 = NL + "\t}" + NL;
  protected final String TEXT_1198 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */" + NL + "\tpublic int eBaseStructuralFeatureID(int derivedFeatureID, Class baseClass)" + NL + "\t{";
  protected final String TEXT_1199 = NL + "\t\tif (baseClass == ";
  protected final String TEXT_1200 = ".class)" + NL + "\t\t{" + NL + "\t\t\tswitch (derivedFeatureID)" + NL + "\t\t\t{";
  protected final String TEXT_1201 = NL + "\t\t\t\tcase ";
  protected final String TEXT_1202 = ": return ";
  protected final String TEXT_1203 = ";";
  protected final String TEXT_1204 = NL + "\t\t\t\tdefault: return -1;" + NL + "\t\t\t}" + NL + "\t\t}";
  protected final String TEXT_1205 = NL + "\t\treturn super.eBaseStructuralFeatureID(derivedFeatureID, baseClass);" + NL + "\t}" + NL + "" + NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */" + NL + "\tpublic int eDerivedStructuralFeatureID(int baseFeatureID, Class baseClass)" + NL + "\t{";
  protected final String TEXT_1206 = NL + "\t\tif (baseClass == ";
  protected final String TEXT_1207 = ".class)" + NL + "\t\t{" + NL + "\t\t\tswitch (baseFeatureID)" + NL + "\t\t\t{";
  protected final String TEXT_1208 = NL + "\t\t\t\tcase ";
  protected final String TEXT_1209 = ": return ";
  protected final String TEXT_1210 = ";";
  protected final String TEXT_1211 = NL + "\t\t\t\tdefault: return -1;" + NL + "\t\t\t}" + NL + "\t\t}";
  protected final String TEXT_1212 = NL + "\t\treturn super.eDerivedStructuralFeatureID(baseFeatureID, baseClass);" + NL + "\t}" + NL;
  protected final String TEXT_1213 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */" + NL + "\tprotected Object[] eVirtualValues()" + NL + "\t{" + NL + "\t\treturn ";
  protected final String TEXT_1214 = ";" + NL + "\t}" + NL + "" + NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */" + NL + "\tprotected void eSetVirtualValues(Object[] newValues)" + NL + "\t{" + NL + "\t\t";
  protected final String TEXT_1215 = " = newValues;" + NL + "\t}" + NL;
  protected final String TEXT_1216 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */" + NL + "\tprotected int eVirtualIndexBits(int offset)" + NL + "\t{" + NL + "\t\tswitch (offset)" + NL + "\t\t{";
  protected final String TEXT_1217 = NL + "\t\t\tcase ";
  protected final String TEXT_1218 = " :" + NL + "\t\t\t\treturn ";
  protected final String TEXT_1219 = ";";
  protected final String TEXT_1220 = NL + "\t\t\tdefault :" + NL + "\t\t\t\tthrow new IndexOutOfBoundsException();" + NL + "\t\t}" + NL + "\t}" + NL + "" + NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */" + NL + "\tprotected void eSetVirtualIndexBits(int offset, int newIndexBits)" + NL + "\t{" + NL + "\t\tswitch (offset)" + NL + "\t\t{";
  protected final String TEXT_1221 = NL + "\t\t\tcase ";
  protected final String TEXT_1222 = " :" + NL + "\t\t\t\t";
  protected final String TEXT_1223 = " = newIndexBits;" + NL + "\t\t\t\tbreak;";
  protected final String TEXT_1224 = NL + "\t\t\tdefault :" + NL + "\t\t\t\tthrow new IndexOutOfBoundsException();" + NL + "\t\t}" + NL + "\t}" + NL;
  protected final String TEXT_1225 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */" + NL + "\tpublic String toString()" + NL + "\t{" + NL + "\t\tif (eIsProxy()) return super.toString();" + NL + "" + NL + "\t\tStringBuffer result = new StringBuffer(super.toString());";
  protected final String TEXT_1226 = NL + "\t\tresult.append(\" (";
  protected final String TEXT_1227 = ": \");";
  protected final String TEXT_1228 = NL + "\t\tresult.append(\", ";
  protected final String TEXT_1229 = ": \");";
  protected final String TEXT_1230 = NL + "\t\tif (eVirtualIsSet(";
  protected final String TEXT_1231 = ")) result.append(eVirtualGet(";
  protected final String TEXT_1232 = ")); else result.append(\"<unset>\");";
  protected final String TEXT_1233 = NL + "\t\tif (";
  protected final String TEXT_1234 = "(";
  protected final String TEXT_1235 = " & ";
  protected final String TEXT_1236 = "_ESETFLAG) != 0";
  protected final String TEXT_1237 = "ESet";
  protected final String TEXT_1238 = ") result.append((";
  protected final String TEXT_1239 = " & ";
  protected final String TEXT_1240 = "_EFLAG) != 0); else result.append(\"<unset>\");";
  protected final String TEXT_1241 = NL + "\t\tif (";
  protected final String TEXT_1242 = "(";
  protected final String TEXT_1243 = " & ";
  protected final String TEXT_1244 = "_ESETFLAG) != 0";
  protected final String TEXT_1245 = "ESet";
  protected final String TEXT_1246 = ") result.append(";
  protected final String TEXT_1247 = "); else result.append(\"<unset>\");";
  protected final String TEXT_1248 = NL + "\t\tresult.append(eVirtualGet(";
  protected final String TEXT_1249 = ", ";
  protected final String TEXT_1250 = "_EDEFAULT";
  protected final String TEXT_1251 = "));";
  protected final String TEXT_1252 = NL + "\t\tresult.append((";
  protected final String TEXT_1253 = " & ";
  protected final String TEXT_1254 = "_EFLAG) != 0);";
  protected final String TEXT_1255 = NL + "\t\tresult.append(";
  protected final String TEXT_1256 = ");";
  protected final String TEXT_1257 = NL + "\t\tresult.append(')');" + NL + "\t\treturn result.toString();" + NL + "\t}" + NL;
  protected final String TEXT_1258 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */" + NL + "\tprotected int hash = -1;" + NL + "" + NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */" + NL + "\tpublic int getHash()" + NL + "\t{" + NL + "\t\tif (hash == -1)" + NL + "\t\t{" + NL + "\t\t\tObject theKey = getKey();" + NL + "\t\t\thash = (theKey == null ? 0 : theKey.hashCode());" + NL + "\t\t}" + NL + "\t\treturn hash;" + NL + "\t}" + NL + "" + NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */" + NL + "\tpublic void setHash(int hash)" + NL + "\t{" + NL + "\t\tthis.hash = hash;" + NL + "\t}" + NL + "" + NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */" + NL + "\tpublic Object getKey()" + NL + "\t{";
  protected final String TEXT_1259 = NL + "\t\treturn new ";
  protected final String TEXT_1260 = "(getTypedKey());";
  protected final String TEXT_1261 = NL + "\t\treturn getTypedKey();";
  protected final String TEXT_1262 = NL + "\t}" + NL + "" + NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */" + NL + "\tpublic void setKey(Object key)" + NL + "\t{";
  protected final String TEXT_1263 = NL + "\t\tgetTypedKey().addAll((";
  protected final String TEXT_1264 = ")key);";
  protected final String TEXT_1265 = NL + "\t\tsetTypedKey(((";
  protected final String TEXT_1266 = ")key).";
  protected final String TEXT_1267 = "());";
  protected final String TEXT_1268 = NL + "\t\tsetTypedKey((";
  protected final String TEXT_1269 = ")key);";
  protected final String TEXT_1270 = NL + "\t}" + NL + "" + NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */" + NL + "\tpublic Object getValue()" + NL + "\t{";
  protected final String TEXT_1271 = NL + "\t\treturn new ";
  protected final String TEXT_1272 = "(getTypedValue());";
  protected final String TEXT_1273 = NL + "\t\treturn getTypedValue();";
  protected final String TEXT_1274 = NL + "\t}" + NL + "" + NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */" + NL + "\tpublic Object setValue(Object value)" + NL + "\t{" + NL + "\t\tObject oldValue = getValue();";
  protected final String TEXT_1275 = NL + "\t\tgetTypedValue().clear();" + NL + "\t\tgetTypedValue().addAll((";
  protected final String TEXT_1276 = ")value);";
  protected final String TEXT_1277 = NL + "\t\tsetTypedValue(((";
  protected final String TEXT_1278 = ")value).";
  protected final String TEXT_1279 = "());";
  protected final String TEXT_1280 = NL + "\t\tsetTypedValue((";
  protected final String TEXT_1281 = ")value);";
  protected final String TEXT_1282 = NL + "\t\treturn oldValue;" + NL + "\t}" + NL + "" + NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */" + NL + "\tpublic ";
  protected final String TEXT_1283 = " getEMap()" + NL + "\t{" + NL + "\t\t";
  protected final String TEXT_1284 = " container = eContainer();" + NL + "\t\treturn container == null ? null : (";
  protected final String TEXT_1285 = ")container.eGet(eContainmentFeature());" + NL + "\t}" + NL;
  protected final String TEXT_1286 = NL + "} //";
  protected final String TEXT_1287 = NL;

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
    if (genModel.isArrayAccessors() && !genFeature.isFeatureMapType() && !genFeature.isMapType()) {
    stringBuffer.append(TEXT_67);
    stringBuffer.append(genFeature.getGetArrayAccessor());
    stringBuffer.append(TEXT_68);
    stringBuffer.append(genFeature.getFormattedName());
    stringBuffer.append(TEXT_69);
    stringBuffer.append(genFeature.getGetArrayAccessor());
    stringBuffer.append(TEXT_70);
    stringBuffer.append(genFeature.getListItemType());
    stringBuffer.append(TEXT_71);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_72);
    stringBuffer.append(genFeature.getListItemType());
    stringBuffer.append(TEXT_73);
    }
    } else {
    if (!genFeature.isVolatile() || !genModel.isReflectiveDelegation() && (!genFeature.hasDelegateFeature() || !genFeature.isUnsettable())) {
    stringBuffer.append(TEXT_74);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_75);
    stringBuffer.append(genFeature.getFormattedName());
    stringBuffer.append(TEXT_76);
    stringBuffer.append(genFeature.getFeatureKind());
    stringBuffer.append(TEXT_77);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_78);
    stringBuffer.append(genFeature.getImportedType());
    stringBuffer.append(TEXT_79);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_80);
    stringBuffer.append(genFeature.getStaticDefaultValue());
    stringBuffer.append(TEXT_81);
    stringBuffer.append(genModel.getNonNLS(genFeature.getStaticDefaultValue()));
    stringBuffer.append(TEXT_82);
    }
    if (genClass.isField(genFeature)) {
    if (genClass.isFlag(genFeature)) {
    if (genClass.getFlagIndex(genFeature) > 31 && genClass.getFlagIndex(genFeature) % 32 == 0) {
    stringBuffer.append(TEXT_83);
    stringBuffer.append(genClass.getFlagsField(genFeature));
    stringBuffer.append(TEXT_84);
    }
    stringBuffer.append(TEXT_85);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_86);
    stringBuffer.append(genFeature.getFormattedName());
    stringBuffer.append(TEXT_87);
    stringBuffer.append(genFeature.getFeatureKind());
    stringBuffer.append(TEXT_88);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_89);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_90);
    stringBuffer.append("<< " + genClass.getFlagIndex(genFeature) % 32 );
    stringBuffer.append(TEXT_91);
    } else {
    stringBuffer.append(TEXT_92);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_93);
    stringBuffer.append(genFeature.getFormattedName());
    stringBuffer.append(TEXT_94);
    stringBuffer.append(genFeature.getFeatureKind());
    stringBuffer.append(TEXT_95);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_96);
    stringBuffer.append(genFeature.getImportedType());
    stringBuffer.append(TEXT_97);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_98);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_99);
    }
    }
    }
    if (genClass.isESetField(genFeature)) {
    if (genClass.isESetFlag(genFeature)) {
    if (genClass.getESetFlagIndex(genFeature) > 31 && genClass.getESetFlagIndex(genFeature) % 32 == 0) {
    stringBuffer.append(TEXT_100);
    stringBuffer.append(genClass.getESetFlagsField(genFeature));
    stringBuffer.append(TEXT_101);
    }
    stringBuffer.append(TEXT_102);
    stringBuffer.append(genFeature.getFormattedName());
    stringBuffer.append(TEXT_103);
    stringBuffer.append(genFeature.getFeatureKind());
    stringBuffer.append(TEXT_104);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_105);
    stringBuffer.append("<< " + genClass.getESetFlagIndex(genFeature) % 32 );
    stringBuffer.append(TEXT_106);
    } else {
    stringBuffer.append(TEXT_107);
    stringBuffer.append(genFeature.getFormattedName());
    stringBuffer.append(TEXT_108);
    stringBuffer.append(genFeature.getFeatureKind());
    stringBuffer.append(TEXT_109);
    stringBuffer.append(genFeature.getUncapName());
    stringBuffer.append(TEXT_110);
    }
    }
    }
    //Class/declaredFieldGenFeature.override.javajetinc
    }
    if (isImplementation) {
    stringBuffer.append(TEXT_111);
    stringBuffer.append(genClass.getClassName());
    stringBuffer.append(TEXT_112);
    for (Iterator i=genClass.getFlagGenFeatures("true").iterator(); i.hasNext();) { GenFeature genFeature = (GenFeature)i.next();
    stringBuffer.append(TEXT_113);
    stringBuffer.append(genClass.getFlagsField(genFeature));
    stringBuffer.append(TEXT_114);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_115);
    }
    stringBuffer.append(TEXT_116);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.EClass"));
    stringBuffer.append(TEXT_117);
    stringBuffer.append(genClass.getQualifiedClassifierAccessor());
    stringBuffer.append(TEXT_118);
    }
    for (Iterator i=(isImplementation ? genClass.getImplementedGenFeatures() : genClass.getDeclaredGenFeatures()).iterator(); i.hasNext();) { GenFeature genFeature = (GenFeature)i.next();
    if (genModel.isArrayAccessors() && genFeature.isListType() && !genFeature.isFeatureMapType() && !genFeature.isMapType()) {
    stringBuffer.append(TEXT_119);
    if (!isImplementation) {
    stringBuffer.append(TEXT_120);
    stringBuffer.append(genFeature.getListItemType());
    stringBuffer.append(TEXT_121);
    stringBuffer.append(genFeature.getGetArrayAccessor());
    stringBuffer.append(TEXT_122);
    } else {
    stringBuffer.append(TEXT_123);
    stringBuffer.append(genFeature.getListItemType());
    stringBuffer.append(TEXT_124);
    stringBuffer.append(genFeature.getGetArrayAccessor());
    stringBuffer.append(TEXT_125);
    if (genFeature.isVolatile()) {
    stringBuffer.append(TEXT_126);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.util.BasicEList"));
    stringBuffer.append(TEXT_127);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.util.BasicEList"));
    stringBuffer.append(TEXT_128);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_129);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_130);
    } else {
    stringBuffer.append(TEXT_131);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_132);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_133);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_134);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.util.BasicEList"));
    stringBuffer.append(TEXT_135);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.util.BasicEList"));
    stringBuffer.append(TEXT_136);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_137);
    }
    stringBuffer.append(TEXT_138);
    stringBuffer.append(genFeature.getListItemType());
    stringBuffer.append(TEXT_139);
    }
    stringBuffer.append(TEXT_140);
    if (!isImplementation) {
    stringBuffer.append(TEXT_141);
    stringBuffer.append(genFeature.getListItemType());
    stringBuffer.append(TEXT_142);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_143);
    } else {
    stringBuffer.append(TEXT_144);
    stringBuffer.append(genFeature.getListItemType());
    stringBuffer.append(TEXT_145);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_146);
    stringBuffer.append(genFeature.getListItemType());
    stringBuffer.append(TEXT_147);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_148);
    }
    stringBuffer.append(TEXT_149);
    if (!isImplementation) {
    stringBuffer.append(TEXT_150);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_151);
    } else {
    stringBuffer.append(TEXT_152);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_153);
    if (genFeature.isVolatile()) {
    stringBuffer.append(TEXT_154);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_155);
    } else {
    stringBuffer.append(TEXT_156);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_157);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_158);
    }
    stringBuffer.append(TEXT_159);
    }
    stringBuffer.append(TEXT_160);
    if (!isImplementation) {
    stringBuffer.append(TEXT_161);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_162);
    stringBuffer.append(genFeature.getListItemType());
    stringBuffer.append(TEXT_163);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_164);
    } else {
    stringBuffer.append(TEXT_165);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_166);
    stringBuffer.append(genFeature.getListItemType());
    stringBuffer.append(TEXT_167);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_168);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.util.BasicEList"));
    stringBuffer.append(TEXT_169);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_170);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_171);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_172);
    }
    stringBuffer.append(TEXT_173);
    if (!isImplementation) {
    stringBuffer.append(TEXT_174);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_175);
    stringBuffer.append(genFeature.getListItemType());
    stringBuffer.append(TEXT_176);
    } else {
    stringBuffer.append(TEXT_177);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_178);
    stringBuffer.append(genFeature.getListItemType());
    stringBuffer.append(TEXT_179);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_180);
    }
    }
    if (genFeature.isGet() && (isImplementation || !genFeature.isSuppressedGetVisibility())) {
    if (isInterface) {
    stringBuffer.append(TEXT_181);
    stringBuffer.append(genFeature.getFormattedName());
    stringBuffer.append(TEXT_182);
    stringBuffer.append(genFeature.getFeatureKind());
    stringBuffer.append(TEXT_183);
    if (genFeature.isListType()) {
    if (genFeature.isMapType()) { GenFeature keyFeature = genFeature.getMapEntryTypeGenClass().getMapEntryKeyFeature(); GenFeature valueFeature = genFeature.getMapEntryTypeGenClass().getMapEntryValueFeature(); 
    stringBuffer.append(TEXT_184);
    if (keyFeature.isListType()) {
    stringBuffer.append(TEXT_185);
    stringBuffer.append(keyFeature.getQualifiedListItemType());
    stringBuffer.append(TEXT_186);
    } else {
    stringBuffer.append(TEXT_187);
    stringBuffer.append(keyFeature.getType());
    stringBuffer.append(TEXT_188);
    }
    stringBuffer.append(TEXT_189);
    if (valueFeature.isListType()) {
    stringBuffer.append(TEXT_190);
    stringBuffer.append(valueFeature.getQualifiedListItemType());
    stringBuffer.append(TEXT_191);
    } else {
    stringBuffer.append(TEXT_192);
    stringBuffer.append(valueFeature.getType());
    stringBuffer.append(TEXT_193);
    }
    stringBuffer.append(TEXT_194);
    } else if (!genFeature.isWrappedFeatureMapType() && !(genModel.isSuppressEMFMetaData() && "org.eclipse.emf.ecore.EObject".equals(genFeature.getQualifiedListItemType()))) {
    stringBuffer.append(TEXT_195);
    stringBuffer.append(genFeature.getQualifiedListItemType());
    stringBuffer.append(TEXT_196);
    }
    } else if (genFeature.isSetDefaultValue()) {
    stringBuffer.append(TEXT_197);
    stringBuffer.append(genFeature.getDefaultValue());
    stringBuffer.append(TEXT_198);
    }
    if (genFeature.getTypeGenEnum() != null) {
    stringBuffer.append(TEXT_199);
    stringBuffer.append(genFeature.getTypeGenEnum().getQualifiedName());
    stringBuffer.append(TEXT_200);
    }
    if (genFeature.isBidirectional() && !genFeature.getReverse().getGenClass().isMapEntry()) { GenFeature reverseGenFeature = genFeature.getReverse(); 
    if (!reverseGenFeature.isSuppressedGetVisibility()) {
    stringBuffer.append(TEXT_201);
    stringBuffer.append(reverseGenFeature.getGenClass().getQualifiedInterfaceName());
    stringBuffer.append(TEXT_202);
    stringBuffer.append(reverseGenFeature.getGetAccessor());
    stringBuffer.append(TEXT_203);
    stringBuffer.append(reverseGenFeature.getFormattedName());
    stringBuffer.append(TEXT_204);
    }
    }
    stringBuffer.append(TEXT_205);
    if (!genFeature.hasDocumentation()) {
    stringBuffer.append(TEXT_206);
    stringBuffer.append(genFeature.getFormattedName());
    stringBuffer.append(TEXT_207);
    stringBuffer.append(genFeature.getFeatureKind());
    stringBuffer.append(TEXT_208);
    }
    stringBuffer.append(TEXT_209);
    if (genFeature.hasDocumentation()) {
    stringBuffer.append(TEXT_210);
    stringBuffer.append(genFeature.getDocumentation(genModel.getIndentation(stringBuffer)));
    stringBuffer.append(TEXT_211);
    }
    stringBuffer.append(TEXT_212);
    stringBuffer.append(genFeature.getFormattedName());
    stringBuffer.append(TEXT_213);
    stringBuffer.append(genFeature.getFeatureKind());
    stringBuffer.append(TEXT_214);
    if (genFeature.getTypeGenEnum() != null) {
    stringBuffer.append(TEXT_215);
    stringBuffer.append(genFeature.getTypeGenEnum().getQualifiedName());
    }
    if (genFeature.isUnsettable()) {
    if (!genFeature.isSuppressedIsSetVisibility()) {
    stringBuffer.append(TEXT_216);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_217);
    }
    if (genFeature.isChangeable() && !genFeature.isSuppressedUnsetVisibility()) {
    stringBuffer.append(TEXT_218);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_219);
    }
    }
    if (genFeature.isChangeable() && !genFeature.isListType() && !genFeature.isSuppressedSetVisibility()) {
    stringBuffer.append(TEXT_220);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_221);
    stringBuffer.append(genFeature.getImportedType());
    stringBuffer.append(TEXT_222);
    }
    if (!genModel.isSuppressEMFMetaData()) {
    stringBuffer.append(TEXT_223);
    stringBuffer.append(genPackage.getQualifiedPackageInterfaceName());
    stringBuffer.append(TEXT_224);
    stringBuffer.append(genFeature.getFeatureAccessorName());
    stringBuffer.append(TEXT_225);
    }
    if (genFeature.isBidirectional() && !genFeature.getReverse().getGenClass().isMapEntry()) { GenFeature reverseGenFeature = genFeature.getReverse(); 
    if (!reverseGenFeature.isSuppressedGetVisibility()) {
    stringBuffer.append(TEXT_226);
    stringBuffer.append(reverseGenFeature.getGenClass().getQualifiedInterfaceName());
    stringBuffer.append(TEXT_227);
    stringBuffer.append(reverseGenFeature.getGetAccessor());
    }
    }
    if (!genModel.isSuppressEMFModelTags()) { boolean first = true; for (StringTokenizer stringTokenizer = new StringTokenizer(genFeature.getModelInfo(), "\n\r"); stringTokenizer.hasMoreTokens(); ) { String modelInfo = stringTokenizer.nextToken(); if (first) { first = false;
    stringBuffer.append(TEXT_228);
    stringBuffer.append(modelInfo);
    } else {
    stringBuffer.append(TEXT_229);
    stringBuffer.append(modelInfo);
    }} if (first) {
    stringBuffer.append(TEXT_230);
    }}
    stringBuffer.append(TEXT_231);
    } else {
    stringBuffer.append(TEXT_232);
    }
    if (!isImplementation) {
    stringBuffer.append(TEXT_233);
    stringBuffer.append(genFeature.getImportedType());
    stringBuffer.append(TEXT_234);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_235);
    } else {
    stringBuffer.append(TEXT_236);
    stringBuffer.append(genFeature.getImportedType());
    stringBuffer.append(TEXT_237);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_238);
    if (genModel.isReflectiveDelegation()) {
    stringBuffer.append(TEXT_239);
    if (genFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_240);
    }
    stringBuffer.append(TEXT_241);
    stringBuffer.append(genFeature.getObjectType());
    stringBuffer.append(TEXT_242);
    stringBuffer.append(genFeature.getQualifiedFeatureAccessor());
    stringBuffer.append(TEXT_243);
    if (genFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_244);
    stringBuffer.append(genFeature.getPrimitiveValueFunction());
    stringBuffer.append(TEXT_245);
    }
    stringBuffer.append(TEXT_246);
    } else if (!genFeature.isVolatile()) {
    if (genFeature.isListType()) {
    if (genModel.isVirtualDelegation()) {
    stringBuffer.append(TEXT_247);
    stringBuffer.append(genFeature.getImportedType());
    stringBuffer.append(TEXT_248);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_249);
    stringBuffer.append(genFeature.getImportedType());
    stringBuffer.append(TEXT_250);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(TEXT_251);
    }
    stringBuffer.append(TEXT_252);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_253);
    if (genModel.isVirtualDelegation()) {
    stringBuffer.append(TEXT_254);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(TEXT_255);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_256);
    stringBuffer.append(genClass.getListConstructor(genFeature));
    stringBuffer.append(TEXT_257);
    } else {
    stringBuffer.append(TEXT_258);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_259);
    stringBuffer.append(genClass.getListConstructor(genFeature));
    stringBuffer.append(TEXT_260);
    }
    stringBuffer.append(TEXT_261);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(genFeature.isMapType() && genFeature.isEffectiveSuppressEMFTypes() ? ".map()" : "");
    stringBuffer.append(TEXT_262);
    } else if (genFeature.isContainer()) {
    stringBuffer.append(TEXT_263);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(TEXT_264);
    stringBuffer.append(genFeature.getImportedType());
    stringBuffer.append(TEXT_265);
    } else {
    if (genFeature.isResolveProxies()) {
    if (genModel.isVirtualDelegation()) {
    stringBuffer.append(TEXT_266);
    stringBuffer.append(genFeature.getImportedType());
    stringBuffer.append(TEXT_267);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_268);
    stringBuffer.append(genFeature.getImportedType());
    stringBuffer.append(TEXT_269);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    if (!genFeature.isReferenceType()) {
    stringBuffer.append(TEXT_270);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_271);
    }
    stringBuffer.append(TEXT_272);
    }
    stringBuffer.append(TEXT_273);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_274);
    stringBuffer.append(genFeature.getSafeNameAsEObject());
    stringBuffer.append(TEXT_275);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.InternalEObject"));
    stringBuffer.append(TEXT_276);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_277);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.InternalEObject"));
    stringBuffer.append(TEXT_278);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_279);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_280);
    stringBuffer.append(genFeature.getNonEObjectInternalTypeCast());
    stringBuffer.append(TEXT_281);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_282);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_283);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_284);
    if (genFeature.isEffectiveContains()) {
    stringBuffer.append(TEXT_285);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.InternalEObject"));
    stringBuffer.append(TEXT_286);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_287);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.InternalEObject"));
    stringBuffer.append(TEXT_288);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_289);
    if (!genFeature.isBidirectional()) {
    stringBuffer.append(TEXT_290);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.notify.NotificationChain"));
    stringBuffer.append(TEXT_291);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_292);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(TEXT_293);
    } else { GenFeature reverseFeature = genFeature.getReverse(); GenClass targetClass = reverseFeature.getGenClass();
    stringBuffer.append(TEXT_294);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.notify.NotificationChain"));
    stringBuffer.append(TEXT_295);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_296);
    stringBuffer.append(targetClass.getQualifiedFeatureID(reverseFeature));
    stringBuffer.append(TEXT_297);
    stringBuffer.append(targetClass.getImportedInterfaceName());
    stringBuffer.append(TEXT_298);
    }
    stringBuffer.append(TEXT_299);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_300);
    if (!genFeature.isBidirectional()) {
    stringBuffer.append(TEXT_301);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_302);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(TEXT_303);
    } else { GenFeature reverseFeature = genFeature.getReverse(); GenClass targetClass = reverseFeature.getGenClass();
    stringBuffer.append(TEXT_304);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_305);
    stringBuffer.append(targetClass.getQualifiedFeatureID(reverseFeature));
    stringBuffer.append(TEXT_306);
    stringBuffer.append(targetClass.getImportedInterfaceName());
    stringBuffer.append(TEXT_307);
    }
    stringBuffer.append(TEXT_308);
    } else if (genModel.isVirtualDelegation()) {
    stringBuffer.append(TEXT_309);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(TEXT_310);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_311);
    }
    if (!genModel.isSuppressNotification()) {
    stringBuffer.append(TEXT_312);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.impl.ENotificationImpl"));
    stringBuffer.append(TEXT_313);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.notify.Notification"));
    stringBuffer.append(TEXT_314);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(TEXT_315);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_316);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_317);
    }
    stringBuffer.append(TEXT_318);
    }
    if (!genFeature.isResolveProxies() && genModel.isVirtualDelegation() && !genFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_319);
    stringBuffer.append(genFeature.getImportedType());
    stringBuffer.append(TEXT_320);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    if (!genFeature.isReferenceType()) {
    stringBuffer.append(TEXT_321);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_322);
    }
    stringBuffer.append(TEXT_323);
    } else if (genClass.isFlag(genFeature)) {
    stringBuffer.append(TEXT_324);
    stringBuffer.append(genClass.getFlagsField(genFeature));
    stringBuffer.append(TEXT_325);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_326);
    } else {
    stringBuffer.append(TEXT_327);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_328);
    }
    }
    } else {//volatile
    if (genFeature.isResolveProxies() && !genFeature.isListType()) {
    stringBuffer.append(TEXT_329);
    stringBuffer.append(genFeature.getImportedType());
    stringBuffer.append(TEXT_330);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_331);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_332);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_333);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_334);
    stringBuffer.append(genFeature.getNonEObjectInternalTypeCast());
    stringBuffer.append(TEXT_335);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.InternalEObject"));
    stringBuffer.append(TEXT_336);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_337);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_338);
    } else if (genFeature.hasDelegateFeature()) { GenFeature delegateFeature = genFeature.getDelegateFeature();
    if (genFeature.isFeatureMapType()) {
    if (delegateFeature.isWrappedFeatureMapType()) {
    stringBuffer.append(TEXT_339);
    stringBuffer.append(genFeature.getImportedEffectiveFeatureMapWrapperClass());
    stringBuffer.append(TEXT_340);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.FeatureMap"));
    stringBuffer.append(TEXT_341);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.FeatureMap"));
    stringBuffer.append(TEXT_342);
    stringBuffer.append(delegateFeature.getAccessorName());
    stringBuffer.append(TEXT_343);
    stringBuffer.append(genFeature.getQualifiedFeatureAccessor());
    stringBuffer.append(TEXT_344);
    } else {
    stringBuffer.append(TEXT_345);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.FeatureMap"));
    stringBuffer.append(TEXT_346);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.FeatureMap"));
    stringBuffer.append(TEXT_347);
    stringBuffer.append(delegateFeature.getAccessorName());
    stringBuffer.append(TEXT_348);
    stringBuffer.append(genFeature.getQualifiedFeatureAccessor());
    stringBuffer.append(TEXT_349);
    }
    } else if (genFeature.isListType()) {
    if (delegateFeature.isWrappedFeatureMapType()) {
    stringBuffer.append(TEXT_350);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.FeatureMap"));
    stringBuffer.append(TEXT_351);
    stringBuffer.append(delegateFeature.getAccessorName());
    stringBuffer.append(TEXT_352);
    stringBuffer.append(genFeature.getQualifiedFeatureAccessor());
    stringBuffer.append(TEXT_353);
    } else {
    stringBuffer.append(TEXT_354);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.FeatureMap"));
    stringBuffer.append(TEXT_355);
    stringBuffer.append(delegateFeature.getAccessorName());
    stringBuffer.append(TEXT_356);
    stringBuffer.append(genFeature.getQualifiedFeatureAccessor());
    stringBuffer.append(TEXT_357);
    }
    } else {
    if (delegateFeature.isWrappedFeatureMapType()) {
    stringBuffer.append(TEXT_358);
    if (genFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_359);
    }
    stringBuffer.append(TEXT_360);
    stringBuffer.append(genFeature.getObjectType());
    stringBuffer.append(TEXT_361);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.FeatureMap"));
    stringBuffer.append(TEXT_362);
    stringBuffer.append(delegateFeature.getAccessorName());
    stringBuffer.append(TEXT_363);
    stringBuffer.append(genFeature.getQualifiedFeatureAccessor());
    stringBuffer.append(TEXT_364);
    if (genFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_365);
    stringBuffer.append(genFeature.getPrimitiveValueFunction());
    stringBuffer.append(TEXT_366);
    }
    stringBuffer.append(TEXT_367);
    } else {
    stringBuffer.append(TEXT_368);
    if (genFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_369);
    }
    stringBuffer.append(TEXT_370);
    stringBuffer.append(genFeature.getObjectType());
    stringBuffer.append(TEXT_371);
    stringBuffer.append(delegateFeature.getAccessorName());
    stringBuffer.append(TEXT_372);
    stringBuffer.append(genFeature.getQualifiedFeatureAccessor());
    stringBuffer.append(TEXT_373);
    if (genFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_374);
    stringBuffer.append(genFeature.getPrimitiveValueFunction());
    stringBuffer.append(TEXT_375);
    }
    stringBuffer.append(TEXT_376);
    }
    }
    } else {
    stringBuffer.append(TEXT_377);
    stringBuffer.append(genFeature.getFormattedName());
    stringBuffer.append(TEXT_378);
    stringBuffer.append(genFeature.getFeatureKind());
    stringBuffer.append(TEXT_379);
    //Class/getGenFeature.todo.override.javajetinc
    }
    }
    stringBuffer.append(TEXT_380);
    }
    //Class/getGenFeature.override.javajetinc
    }
    if (isImplementation && !genModel.isReflectiveDelegation() && genFeature.isBasicGet()) {
    stringBuffer.append(TEXT_381);
    stringBuffer.append(genFeature.getImportedType());
    stringBuffer.append(TEXT_382);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_383);
    if (genFeature.isContainer()) {
    stringBuffer.append(TEXT_384);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(TEXT_385);
    stringBuffer.append(genFeature.getImportedType());
    stringBuffer.append(TEXT_386);
    } else if (!genFeature.isVolatile()) {
    if (genModel.isVirtualDelegation()) {
    stringBuffer.append(TEXT_387);
    stringBuffer.append(genFeature.getImportedType());
    stringBuffer.append(TEXT_388);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(TEXT_389);
    } else {
    stringBuffer.append(TEXT_390);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_391);
    }
    } else if (genFeature.hasDelegateFeature()) { GenFeature delegateFeature = genFeature.getDelegateFeature();
    if (delegateFeature.isWrappedFeatureMapType()) {
    stringBuffer.append(TEXT_392);
    stringBuffer.append(genFeature.getImportedType());
    stringBuffer.append(TEXT_393);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.FeatureMap"));
    stringBuffer.append(TEXT_394);
    stringBuffer.append(delegateFeature.getAccessorName());
    stringBuffer.append(TEXT_395);
    stringBuffer.append(genFeature.getQualifiedFeatureAccessor());
    stringBuffer.append(TEXT_396);
    } else {
    stringBuffer.append(TEXT_397);
    stringBuffer.append(genFeature.getImportedType());
    stringBuffer.append(TEXT_398);
    stringBuffer.append(delegateFeature.getAccessorName());
    stringBuffer.append(TEXT_399);
    stringBuffer.append(genFeature.getQualifiedFeatureAccessor());
    stringBuffer.append(TEXT_400);
    }
    } else {
    stringBuffer.append(TEXT_401);
    stringBuffer.append(genFeature.getFormattedName());
    stringBuffer.append(TEXT_402);
    stringBuffer.append(genFeature.getFeatureKind());
    stringBuffer.append(TEXT_403);
    //Class/basicGetGenFeature.todo.override.javajetinc
    }
    stringBuffer.append(TEXT_404);
    //Class/basicGetGenFeature.override.javajetinc
    }
    if (isImplementation && !genModel.isReflectiveDelegation() && genFeature.isBasicSet()) {
    stringBuffer.append(TEXT_405);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.notify.NotificationChain"));
    stringBuffer.append(TEXT_406);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_407);
    stringBuffer.append(genFeature.getImportedInternalType());
    stringBuffer.append(TEXT_408);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_409);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.notify.NotificationChain"));
    stringBuffer.append(TEXT_410);
    if (!genFeature.isVolatile()) {
    if (genModel.isVirtualDelegation()) {
    stringBuffer.append(TEXT_411);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_412);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(TEXT_413);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_414);
    } else {
    stringBuffer.append(TEXT_415);
    stringBuffer.append(genFeature.getImportedType());
    stringBuffer.append(TEXT_416);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_417);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_418);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_419);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_420);
    }
    if (genFeature.isUnsettable()) {
    if (genModel.isVirtualDelegation()) {
    stringBuffer.append(TEXT_421);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_422);
    } else if (genClass.isESetFlag(genFeature)) {
    stringBuffer.append(TEXT_423);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_424);
    stringBuffer.append(genClass.getESetFlagsField(genFeature));
    stringBuffer.append(TEXT_425);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_426);
    stringBuffer.append(genClass.getESetFlagsField(genFeature));
    stringBuffer.append(TEXT_427);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_428);
    } else {
    stringBuffer.append(TEXT_429);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_430);
    stringBuffer.append(genFeature.getUncapName());
    stringBuffer.append(TEXT_431);
    stringBuffer.append(genFeature.getUncapName());
    stringBuffer.append(TEXT_432);
    }
    }
    if (!genModel.isSuppressNotification()) {
    stringBuffer.append(TEXT_433);
    if (genFeature.isUnsettable()) {
    stringBuffer.append(TEXT_434);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.impl.ENotificationImpl"));
    stringBuffer.append(TEXT_435);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.impl.ENotificationImpl"));
    stringBuffer.append(TEXT_436);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.notify.Notification"));
    stringBuffer.append(TEXT_437);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(TEXT_438);
    if (genModel.isVirtualDelegation()) {
    stringBuffer.append(TEXT_439);
    stringBuffer.append(genFeature.getCapName());
    } else {
    stringBuffer.append(TEXT_440);
    stringBuffer.append(genFeature.getCapName());
    }
    stringBuffer.append(TEXT_441);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_442);
    if (genModel.isVirtualDelegation()) {
    stringBuffer.append(TEXT_443);
    } else {
    stringBuffer.append(TEXT_444);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_445);
    }
    stringBuffer.append(TEXT_446);
    } else {
    stringBuffer.append(TEXT_447);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.impl.ENotificationImpl"));
    stringBuffer.append(TEXT_448);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.impl.ENotificationImpl"));
    stringBuffer.append(TEXT_449);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.notify.Notification"));
    stringBuffer.append(TEXT_450);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(TEXT_451);
    if (genModel.isVirtualDelegation()) {
    stringBuffer.append(TEXT_452);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_453);
    stringBuffer.append(genFeature.getCapName());
    } else {
    stringBuffer.append(TEXT_454);
    stringBuffer.append(genFeature.getCapName());
    }
    stringBuffer.append(TEXT_455);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_456);
    }
    stringBuffer.append(TEXT_457);
    }
    stringBuffer.append(TEXT_458);
    } else if (genFeature.hasDelegateFeature()) { GenFeature delegateFeature = genFeature.getDelegateFeature();
    if (delegateFeature.isWrappedFeatureMapType()) {
    stringBuffer.append(TEXT_459);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.FeatureMap"));
    stringBuffer.append(TEXT_460);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.FeatureMap"));
    stringBuffer.append(TEXT_461);
    stringBuffer.append(delegateFeature.getAccessorName());
    stringBuffer.append(TEXT_462);
    stringBuffer.append(genFeature.getQualifiedFeatureAccessor());
    stringBuffer.append(TEXT_463);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_464);
    } else {
    stringBuffer.append(TEXT_465);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.FeatureMap"));
    stringBuffer.append(TEXT_466);
    stringBuffer.append(delegateFeature.getAccessorName());
    stringBuffer.append(TEXT_467);
    stringBuffer.append(genFeature.getQualifiedFeatureAccessor());
    stringBuffer.append(TEXT_468);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_469);
    }
    } else {
    stringBuffer.append(TEXT_470);
    stringBuffer.append(genFeature.getFormattedName());
    stringBuffer.append(TEXT_471);
    stringBuffer.append(genFeature.getFeatureKind());
    stringBuffer.append(TEXT_472);
    //Class/basicSetGenFeature.todo.override.javajetinc
    }
    stringBuffer.append(TEXT_473);
    //Class/basicSetGenFeature.override.javajetinc
    }
    if (genFeature.isSet() && (isImplementation || !genFeature.isSuppressedSetVisibility())) {
    if (isInterface) { 
    stringBuffer.append(TEXT_474);
    stringBuffer.append(genClass.getQualifiedInterfaceName());
    stringBuffer.append(TEXT_475);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_476);
    stringBuffer.append(genFeature.getFormattedName());
    stringBuffer.append(TEXT_477);
    stringBuffer.append(genFeature.getFeatureKind());
    stringBuffer.append(TEXT_478);
    stringBuffer.append(genFeature.getFormattedName());
    stringBuffer.append(TEXT_479);
    stringBuffer.append(genFeature.getFeatureKind());
    stringBuffer.append(TEXT_480);
    if (genFeature.isEnumType()) {
    stringBuffer.append(TEXT_481);
    stringBuffer.append(genFeature.getTypeGenEnum().getQualifiedName());
    }
    if (genFeature.isUnsettable()) {
    if (!genFeature.isSuppressedIsSetVisibility()) {
    stringBuffer.append(TEXT_482);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_483);
    }
    if (!genFeature.isSuppressedUnsetVisibility()) {
    stringBuffer.append(TEXT_484);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_485);
    }
    }
    stringBuffer.append(TEXT_486);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_487);
    } else {
    stringBuffer.append(TEXT_488);
    }
    if (!isImplementation) { 
    stringBuffer.append(TEXT_489);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_490);
    stringBuffer.append(genFeature.getImportedType());
    stringBuffer.append(TEXT_491);
    } else {
    stringBuffer.append(TEXT_492);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_493);
    stringBuffer.append(genFeature.getImportedType());
    stringBuffer.append(TEXT_494);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_495);
    if (genModel.isReflectiveDelegation()) {
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
    } else if (!genFeature.isVolatile()) {
    if (genFeature.isContainer()) { GenFeature reverseFeature = genFeature.getReverse(); GenClass targetClass = reverseFeature.getGenClass();
    stringBuffer.append(TEXT_503);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_504);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(TEXT_505);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_506);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.EcoreUtil"));
    stringBuffer.append(TEXT_507);
    stringBuffer.append(genFeature.getEObjectCast());
    stringBuffer.append(TEXT_508);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_509);
    stringBuffer.append(genModel.getImportedName("java.lang.IllegalArgumentException"));
    stringBuffer.append(TEXT_510);
    stringBuffer.append(genModel.getNonNLS());
    stringBuffer.append(TEXT_511);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.notify.NotificationChain"));
    stringBuffer.append(TEXT_512);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_513);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.InternalEObject"));
    stringBuffer.append(TEXT_514);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_515);
    stringBuffer.append(targetClass.getQualifiedFeatureID(reverseFeature));
    stringBuffer.append(TEXT_516);
    stringBuffer.append(targetClass.getImportedInterfaceName());
    stringBuffer.append(TEXT_517);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.InternalEObject"));
    stringBuffer.append(TEXT_518);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_519);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(TEXT_520);
    if (!genModel.isSuppressNotification()) {
    stringBuffer.append(TEXT_521);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.impl.ENotificationImpl"));
    stringBuffer.append(TEXT_522);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.notify.Notification"));
    stringBuffer.append(TEXT_523);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(TEXT_524);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_525);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_526);
    }
    } else if (genFeature.isBidirectional() || genFeature.isEffectiveContains()) {
    if (genModel.isVirtualDelegation()) {
    stringBuffer.append(TEXT_527);
    stringBuffer.append(genFeature.getImportedType());
    stringBuffer.append(TEXT_528);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_529);
    stringBuffer.append(genFeature.getImportedType());
    stringBuffer.append(TEXT_530);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(TEXT_531);
    }
    stringBuffer.append(TEXT_532);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_533);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_534);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.notify.NotificationChain"));
    stringBuffer.append(TEXT_535);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_536);
    if (!genFeature.isBidirectional()) {
    stringBuffer.append(TEXT_537);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.InternalEObject"));
    stringBuffer.append(TEXT_538);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_539);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(TEXT_540);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_541);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.InternalEObject"));
    stringBuffer.append(TEXT_542);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_543);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(TEXT_544);
    } else { GenFeature reverseFeature = genFeature.getReverse(); GenClass targetClass = reverseFeature.getGenClass();
    stringBuffer.append(TEXT_545);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.InternalEObject"));
    stringBuffer.append(TEXT_546);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_547);
    stringBuffer.append(targetClass.getQualifiedFeatureID(reverseFeature));
    stringBuffer.append(TEXT_548);
    stringBuffer.append(targetClass.getImportedInterfaceName());
    stringBuffer.append(TEXT_549);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_550);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.InternalEObject"));
    stringBuffer.append(TEXT_551);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_552);
    stringBuffer.append(targetClass.getQualifiedFeatureID(reverseFeature));
    stringBuffer.append(TEXT_553);
    stringBuffer.append(targetClass.getImportedInterfaceName());
    stringBuffer.append(TEXT_554);
    }
    stringBuffer.append(TEXT_555);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_556);
    stringBuffer.append(genFeature.getInternalTypeCast());
    stringBuffer.append(TEXT_557);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_558);
    if (genFeature.isUnsettable()) {
    stringBuffer.append(TEXT_559);
    if (genModel.isVirtualDelegation()) {
    stringBuffer.append(TEXT_560);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_561);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(TEXT_562);
    } else if (genClass.isESetFlag(genFeature)) {
    if (!genModel.isSuppressNotification()) {
    stringBuffer.append(TEXT_563);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_564);
    stringBuffer.append(genClass.getESetFlagsField(genFeature));
    stringBuffer.append(TEXT_565);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_566);
    }
    stringBuffer.append(TEXT_567);
    stringBuffer.append(genClass.getESetFlagsField(genFeature));
    stringBuffer.append(TEXT_568);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_569);
    } else {
    if (!genModel.isSuppressNotification()) {
    stringBuffer.append(TEXT_570);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_571);
    stringBuffer.append(genFeature.getUncapName());
    stringBuffer.append(TEXT_572);
    }
    stringBuffer.append(TEXT_573);
    stringBuffer.append(genFeature.getUncapName());
    stringBuffer.append(TEXT_574);
    }
    if (!genModel.isSuppressNotification()) {
    stringBuffer.append(TEXT_575);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.impl.ENotificationImpl"));
    stringBuffer.append(TEXT_576);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.notify.Notification"));
    stringBuffer.append(TEXT_577);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(TEXT_578);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_579);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_580);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_581);
    }
    stringBuffer.append(TEXT_582);
    } else {
    if (!genModel.isSuppressNotification()) {
    stringBuffer.append(TEXT_583);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.impl.ENotificationImpl"));
    stringBuffer.append(TEXT_584);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.notify.Notification"));
    stringBuffer.append(TEXT_585);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(TEXT_586);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_587);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_588);
    }
    }
    } else {
    if (genClass.isFlag(genFeature)) {
    if (!genModel.isSuppressNotification()) {
    stringBuffer.append(TEXT_589);
    stringBuffer.append(genFeature.getImportedType());
    stringBuffer.append(TEXT_590);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_591);
    stringBuffer.append(genClass.getFlagsField(genFeature));
    stringBuffer.append(TEXT_592);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_593);
    }
    stringBuffer.append(TEXT_594);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_595);
    stringBuffer.append(genClass.getFlagsField(genFeature));
    stringBuffer.append(TEXT_596);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_597);
    stringBuffer.append(genClass.getFlagsField(genFeature));
    stringBuffer.append(TEXT_598);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_599);
    } else {
    if (!genModel.isVirtualDelegation() || genFeature.isPrimitiveType()) {
    if (!genModel.isSuppressNotification()) {
    stringBuffer.append(TEXT_600);
    stringBuffer.append(genFeature.getImportedType());
    stringBuffer.append(TEXT_601);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_602);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_603);
    }
    }
    if (genFeature.isEnumType()) {
    if (genModel.isVirtualDelegation()) {
    stringBuffer.append(TEXT_604);
    stringBuffer.append(genFeature.getImportedType());
    stringBuffer.append(TEXT_605);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_606);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_607);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_608);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_609);
    } else {
    stringBuffer.append(TEXT_610);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_611);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_612);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_613);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_614);
    }
    } else {
    if (genModel.isVirtualDelegation() && !genFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_615);
    stringBuffer.append(genFeature.getImportedType());
    stringBuffer.append(TEXT_616);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_617);
    stringBuffer.append(genFeature.getInternalTypeCast());
    stringBuffer.append(TEXT_618);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_619);
    } else {
    stringBuffer.append(TEXT_620);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_621);
    stringBuffer.append(genFeature.getInternalTypeCast());
    stringBuffer.append(TEXT_622);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_623);
    }
    }
    if (genModel.isVirtualDelegation() && !genFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_624);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_625);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(TEXT_626);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_627);
    }
    }
    if (genFeature.isUnsettable()) {
    if (genModel.isVirtualDelegation() && !genFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_628);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_629);
    } else if (genClass.isESetFlag(genFeature)) {
    if (!genModel.isSuppressNotification()) {
    stringBuffer.append(TEXT_630);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_631);
    stringBuffer.append(genClass.getESetFlagsField(genFeature));
    stringBuffer.append(TEXT_632);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_633);
    }
    stringBuffer.append(TEXT_634);
    stringBuffer.append(genClass.getESetFlagsField(genFeature));
    stringBuffer.append(TEXT_635);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_636);
    } else {
    if (!genModel.isSuppressNotification()) {
    stringBuffer.append(TEXT_637);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_638);
    stringBuffer.append(genFeature.getUncapName());
    stringBuffer.append(TEXT_639);
    }
    stringBuffer.append(TEXT_640);
    stringBuffer.append(genFeature.getUncapName());
    stringBuffer.append(TEXT_641);
    }
    if (!genModel.isSuppressNotification()) {
    stringBuffer.append(TEXT_642);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.impl.ENotificationImpl"));
    stringBuffer.append(TEXT_643);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.notify.Notification"));
    stringBuffer.append(TEXT_644);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(TEXT_645);
    if (genModel.isVirtualDelegation() && !genFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_646);
    if (genFeature.isReferenceType()) {
    stringBuffer.append(TEXT_647);
    } else {
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_648);
    }
    stringBuffer.append(TEXT_649);
    stringBuffer.append(genFeature.getCapName());
    } else {
    stringBuffer.append(TEXT_650);
    stringBuffer.append(genFeature.getCapName());
    }
    stringBuffer.append(TEXT_651);
    if (genClass.isFlag(genFeature)) {
    stringBuffer.append(TEXT_652);
    stringBuffer.append(genFeature.getCapName());
    } else {
    stringBuffer.append(genFeature.getSafeName());
    }
    stringBuffer.append(TEXT_653);
    if (genModel.isVirtualDelegation() && !genFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_654);
    } else {
    stringBuffer.append(TEXT_655);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_656);
    }
    stringBuffer.append(TEXT_657);
    }
    } else {
    if (!genModel.isSuppressNotification()) {
    stringBuffer.append(TEXT_658);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.impl.ENotificationImpl"));
    stringBuffer.append(TEXT_659);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.notify.Notification"));
    stringBuffer.append(TEXT_660);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(TEXT_661);
    if (genModel.isVirtualDelegation() && !genFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_662);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_663);
    if (genFeature.isReferenceType()) {
    stringBuffer.append(TEXT_664);
    } else {
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_665);
    }
    stringBuffer.append(TEXT_666);
    stringBuffer.append(genFeature.getCapName());
    } else {
    stringBuffer.append(TEXT_667);
    stringBuffer.append(genFeature.getCapName());
    }
    stringBuffer.append(TEXT_668);
    if (genClass.isFlag(genFeature)) {
    stringBuffer.append(TEXT_669);
    stringBuffer.append(genFeature.getCapName());
    } else {
    stringBuffer.append(genFeature.getSafeName());
    }
    stringBuffer.append(TEXT_670);
    }
    }
    }
    } else if (genFeature.hasDelegateFeature()) { GenFeature delegateFeature = genFeature.getDelegateFeature();
    if (delegateFeature.isWrappedFeatureMapType()) {
    stringBuffer.append(TEXT_671);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.FeatureMap"));
    stringBuffer.append(TEXT_672);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.FeatureMap"));
    stringBuffer.append(TEXT_673);
    stringBuffer.append(delegateFeature.getAccessorName());
    stringBuffer.append(TEXT_674);
    stringBuffer.append(genFeature.getQualifiedFeatureAccessor());
    stringBuffer.append(TEXT_675);
    if (genFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_676);
    stringBuffer.append(genFeature.getObjectType());
    stringBuffer.append(TEXT_677);
    }
    stringBuffer.append(TEXT_678);
    stringBuffer.append(genFeature.getCapName());
    if (genFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_679);
    }
    stringBuffer.append(TEXT_680);
    } else {
    stringBuffer.append(TEXT_681);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.FeatureMap"));
    stringBuffer.append(TEXT_682);
    stringBuffer.append(delegateFeature.getAccessorName());
    stringBuffer.append(TEXT_683);
    stringBuffer.append(genFeature.getQualifiedFeatureAccessor());
    stringBuffer.append(TEXT_684);
    if (genFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_685);
    stringBuffer.append(genFeature.getObjectType());
    stringBuffer.append(TEXT_686);
    }
    stringBuffer.append(TEXT_687);
    stringBuffer.append(genFeature.getCapName());
    if (genFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_688);
    }
    stringBuffer.append(TEXT_689);
    }
    } else {
    stringBuffer.append(TEXT_690);
    stringBuffer.append(genFeature.getFormattedName());
    stringBuffer.append(TEXT_691);
    stringBuffer.append(genFeature.getFeatureKind());
    stringBuffer.append(TEXT_692);
    //Class/setGenFeature.todo.override.javajetinc
    }
    stringBuffer.append(TEXT_693);
    }
    //Class/setGenFeature.override.javajetinc
    }
    if (isImplementation && !genModel.isReflectiveDelegation() && genFeature.isBasicUnset()) {
    stringBuffer.append(TEXT_694);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.notify.NotificationChain"));
    stringBuffer.append(TEXT_695);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_696);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.notify.NotificationChain"));
    stringBuffer.append(TEXT_697);
    if (!genFeature.isVolatile()) {
    if (genModel.isVirtualDelegation()) {
    stringBuffer.append(TEXT_698);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_699);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(TEXT_700);
    } else {
    stringBuffer.append(TEXT_701);
    stringBuffer.append(genFeature.getImportedType());
    stringBuffer.append(TEXT_702);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_703);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_704);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_705);
    }
    if (genModel.isVirtualDelegation()) {
    stringBuffer.append(TEXT_706);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_707);
    } else if (genClass.isESetFlag(genFeature)) {
    stringBuffer.append(TEXT_708);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_709);
    stringBuffer.append(genClass.getESetFlagsField(genFeature));
    stringBuffer.append(TEXT_710);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_711);
    stringBuffer.append(genClass.getESetFlagsField(genFeature));
    stringBuffer.append(TEXT_712);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_713);
    } else {
    stringBuffer.append(TEXT_714);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_715);
    stringBuffer.append(genFeature.getUncapName());
    stringBuffer.append(TEXT_716);
    stringBuffer.append(genFeature.getUncapName());
    stringBuffer.append(TEXT_717);
    }
    if (!genModel.isSuppressNotification()) {
    stringBuffer.append(TEXT_718);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.impl.ENotificationImpl"));
    stringBuffer.append(TEXT_719);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.impl.ENotificationImpl"));
    stringBuffer.append(TEXT_720);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.notify.Notification"));
    stringBuffer.append(TEXT_721);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(TEXT_722);
    if (genModel.isVirtualDelegation()) {
    stringBuffer.append(TEXT_723);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_724);
    } else {
    stringBuffer.append(TEXT_725);
    stringBuffer.append(genFeature.getCapName());
    }
    stringBuffer.append(TEXT_726);
    if (genModel.isVirtualDelegation()) {
    stringBuffer.append(TEXT_727);
    } else {
    stringBuffer.append(TEXT_728);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_729);
    }
    stringBuffer.append(TEXT_730);
    }
    } else {
    stringBuffer.append(TEXT_731);
    stringBuffer.append(genFeature.getFormattedName());
    stringBuffer.append(TEXT_732);
    stringBuffer.append(genFeature.getFeatureKind());
    stringBuffer.append(TEXT_733);
    //Class/basicUnsetGenFeature.todo.override.javajetinc
    }
    stringBuffer.append(TEXT_734);
    //Class.basicUnsetGenFeature.override.javajetinc
    }
    if (genFeature.isUnset() && (isImplementation || !genFeature.isSuppressedUnsetVisibility())) {
    if (isInterface) {
    stringBuffer.append(TEXT_735);
    stringBuffer.append(genClass.getQualifiedInterfaceName());
    stringBuffer.append(TEXT_736);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_737);
    stringBuffer.append(genFeature.getFormattedName());
    stringBuffer.append(TEXT_738);
    stringBuffer.append(genFeature.getFeatureKind());
    stringBuffer.append(TEXT_739);
    if (!genFeature.isSuppressedIsSetVisibility()) {
    stringBuffer.append(TEXT_740);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_741);
    }
    stringBuffer.append(TEXT_742);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_743);
    if (!genFeature.isListType() && !genFeature.isSuppressedSetVisibility()) {
    stringBuffer.append(TEXT_744);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_745);
    stringBuffer.append(genFeature.getImportedType());
    stringBuffer.append(TEXT_746);
    }
    stringBuffer.append(TEXT_747);
    } else {
    stringBuffer.append(TEXT_748);
    }
    if (!isImplementation) {
    stringBuffer.append(TEXT_749);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_750);
    } else {
    stringBuffer.append(TEXT_751);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_752);
    if (genModel.isReflectiveDelegation()) {
    stringBuffer.append(TEXT_753);
    stringBuffer.append(genFeature.getQualifiedFeatureAccessor());
    stringBuffer.append(TEXT_754);
    } else if (!genFeature.isVolatile()) {
    if (genFeature.isListType()) {
    stringBuffer.append(TEXT_755);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.InternalEList"));
    stringBuffer.append(TEXT_756);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_757);
    } else if (genFeature.isBidirectional() || genFeature.isEffectiveContains()) {
    if (genModel.isVirtualDelegation()) {
    stringBuffer.append(TEXT_758);
    stringBuffer.append(genFeature.getImportedType());
    stringBuffer.append(TEXT_759);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_760);
    stringBuffer.append(genFeature.getImportedType());
    stringBuffer.append(TEXT_761);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(TEXT_762);
    }
    stringBuffer.append(TEXT_763);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_764);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.notify.NotificationChain"));
    stringBuffer.append(TEXT_765);
    if (!genFeature.isBidirectional()) {
    stringBuffer.append(TEXT_766);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.InternalEObject"));
    stringBuffer.append(TEXT_767);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_768);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(TEXT_769);
    } else { GenFeature reverseFeature = genFeature.getReverse(); GenClass targetClass = reverseFeature.getGenClass();
    stringBuffer.append(TEXT_770);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.InternalEObject"));
    stringBuffer.append(TEXT_771);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_772);
    stringBuffer.append(targetClass.getQualifiedFeatureID(reverseFeature));
    stringBuffer.append(TEXT_773);
    stringBuffer.append(targetClass.getImportedInterfaceName());
    stringBuffer.append(TEXT_774);
    }
    stringBuffer.append(TEXT_775);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_776);
    if (genModel.isVirtualDelegation()) {
    stringBuffer.append(TEXT_777);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_778);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(TEXT_779);
    } else if (genClass.isESetFlag(genFeature)) {
    if (!genModel.isSuppressNotification()) {
    stringBuffer.append(TEXT_780);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_781);
    stringBuffer.append(genClass.getESetFlagsField(genFeature));
    stringBuffer.append(TEXT_782);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_783);
    }
    stringBuffer.append(TEXT_784);
    stringBuffer.append(genClass.getESetFlagsField(genFeature));
    stringBuffer.append(TEXT_785);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_786);
    } else {
    if (!genModel.isSuppressNotification()) {
    stringBuffer.append(TEXT_787);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_788);
    stringBuffer.append(genFeature.getUncapName());
    stringBuffer.append(TEXT_789);
    }
    stringBuffer.append(TEXT_790);
    stringBuffer.append(genFeature.getUncapName());
    stringBuffer.append(TEXT_791);
    }
    if (!genModel.isSuppressNotification()) {
    stringBuffer.append(TEXT_792);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.impl.ENotificationImpl"));
    stringBuffer.append(TEXT_793);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.notify.Notification"));
    stringBuffer.append(TEXT_794);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(TEXT_795);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_796);
    }
    stringBuffer.append(TEXT_797);
    } else {
    if (genClass.isFlag(genFeature)) {
    if (!genModel.isSuppressNotification()) {
    stringBuffer.append(TEXT_798);
    stringBuffer.append(genFeature.getImportedType());
    stringBuffer.append(TEXT_799);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_800);
    stringBuffer.append(genClass.getFlagsField(genFeature));
    stringBuffer.append(TEXT_801);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_802);
    }
    } else if (genModel.isVirtualDelegation() && !genFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_803);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_804);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(TEXT_805);
    } else {
    if (!genModel.isSuppressNotification()) {
    stringBuffer.append(TEXT_806);
    stringBuffer.append(genFeature.getImportedType());
    stringBuffer.append(TEXT_807);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_808);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_809);
    }
    }
    if (genModel.isVirtualDelegation() && !genFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_810);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_811);
    } else if (genClass.isESetFlag(genFeature)) {
    stringBuffer.append(TEXT_812);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_813);
    stringBuffer.append(genClass.getESetFlagsField(genFeature));
    stringBuffer.append(TEXT_814);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_815);
    } else {
    if (!genModel.isSuppressNotification()) {
    stringBuffer.append(TEXT_816);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_817);
    stringBuffer.append(genFeature.getUncapName());
    stringBuffer.append(TEXT_818);
    }
    }
    if (genFeature.isReferenceType()) {
    stringBuffer.append(TEXT_819);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_820);
    if (!genModel.isVirtualDelegation()) {
    if (genClass.isESetFlag(genFeature)) {
    stringBuffer.append(TEXT_821);
    stringBuffer.append(genClass.getESetFlagsField(genFeature));
    stringBuffer.append(TEXT_822);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_823);
    } else {
    stringBuffer.append(TEXT_824);
    stringBuffer.append(genFeature.getUncapName());
    stringBuffer.append(TEXT_825);
    }
    }
    if (!genModel.isSuppressNotification()) {
    stringBuffer.append(TEXT_826);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.impl.ENotificationImpl"));
    stringBuffer.append(TEXT_827);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.notify.Notification"));
    stringBuffer.append(TEXT_828);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(TEXT_829);
    if (genModel.isVirtualDelegation()) {
    stringBuffer.append(TEXT_830);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_831);
    } else {
    stringBuffer.append(TEXT_832);
    stringBuffer.append(genFeature.getCapName());
    }
    stringBuffer.append(TEXT_833);
    if (genModel.isVirtualDelegation()) {
    stringBuffer.append(TEXT_834);
    } else {
    stringBuffer.append(TEXT_835);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_836);
    }
    stringBuffer.append(TEXT_837);
    }
    } else {
    if (genClass.isFlag(genFeature)) {
    stringBuffer.append(TEXT_838);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_839);
    stringBuffer.append(genClass.getFlagsField(genFeature));
    stringBuffer.append(TEXT_840);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_841);
    stringBuffer.append(genClass.getFlagsField(genFeature));
    stringBuffer.append(TEXT_842);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_843);
    } else if (!genModel.isVirtualDelegation() || genFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_844);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_845);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_846);
    }
    if (!genModel.isVirtualDelegation() || genFeature.isPrimitiveType()) {
    if (genClass.isESetFlag(genFeature)) {
    stringBuffer.append(TEXT_847);
    stringBuffer.append(genClass.getESetFlagsField(genFeature));
    stringBuffer.append(TEXT_848);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_849);
    } else {
    stringBuffer.append(TEXT_850);
    stringBuffer.append(genFeature.getUncapName());
    stringBuffer.append(TEXT_851);
    }
    }
    if (!genModel.isSuppressNotification()) {
    stringBuffer.append(TEXT_852);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.impl.ENotificationImpl"));
    stringBuffer.append(TEXT_853);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.notify.Notification"));
    stringBuffer.append(TEXT_854);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(TEXT_855);
    if (genModel.isVirtualDelegation() && !genFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_856);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_857);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_858);
    } else {
    stringBuffer.append(TEXT_859);
    stringBuffer.append(genFeature.getCapName());
    }
    stringBuffer.append(TEXT_860);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_861);
    if (genModel.isVirtualDelegation() && !genFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_862);
    } else {
    stringBuffer.append(TEXT_863);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_864);
    }
    stringBuffer.append(TEXT_865);
    }
    }
    }
    } else if (genFeature.hasDelegateFeature()) { GenFeature delegateFeature = genFeature.getDelegateFeature();
    if (delegateFeature.isWrappedFeatureMapType()) {
    stringBuffer.append(TEXT_866);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.FeatureMap"));
    stringBuffer.append(TEXT_867);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.FeatureMap"));
    stringBuffer.append(TEXT_868);
    stringBuffer.append(delegateFeature.getAccessorName());
    stringBuffer.append(TEXT_869);
    stringBuffer.append(genFeature.getQualifiedFeatureAccessor());
    stringBuffer.append(TEXT_870);
    } else {
    stringBuffer.append(TEXT_871);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.FeatureMap"));
    stringBuffer.append(TEXT_872);
    stringBuffer.append(delegateFeature.getAccessorName());
    stringBuffer.append(TEXT_873);
    stringBuffer.append(genFeature.getQualifiedFeatureAccessor());
    stringBuffer.append(TEXT_874);
    }
    } else {
    stringBuffer.append(TEXT_875);
    stringBuffer.append(genFeature.getFormattedName());
    stringBuffer.append(TEXT_876);
    stringBuffer.append(genFeature.getFeatureKind());
    stringBuffer.append(TEXT_877);
    //Class/unsetGenFeature.todo.override.javajetinc
    }
    stringBuffer.append(TEXT_878);
    }
    //Class/unsetGenFeature.override.javajetinc
    }
    if (genFeature.isIsSet() && (isImplementation || !genFeature.isSuppressedIsSetVisibility())) {
    if (isInterface) {
    stringBuffer.append(TEXT_879);
    stringBuffer.append(genClass.getQualifiedInterfaceName());
    stringBuffer.append(TEXT_880);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_881);
    stringBuffer.append(genFeature.getFormattedName());
    stringBuffer.append(TEXT_882);
    stringBuffer.append(genFeature.getFeatureKind());
    stringBuffer.append(TEXT_883);
    stringBuffer.append(genFeature.getFormattedName());
    stringBuffer.append(TEXT_884);
    stringBuffer.append(genFeature.getFeatureKind());
    stringBuffer.append(TEXT_885);
    if (genFeature.isChangeable() && !genFeature.isSuppressedUnsetVisibility()) {
    stringBuffer.append(TEXT_886);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_887);
    }
    stringBuffer.append(TEXT_888);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_889);
    if (!genFeature.isListType() && genFeature.isChangeable() && !genFeature.isSuppressedSetVisibility()) {
    stringBuffer.append(TEXT_890);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_891);
    stringBuffer.append(genFeature.getImportedType());
    stringBuffer.append(TEXT_892);
    }
    stringBuffer.append(TEXT_893);
    } else {
    stringBuffer.append(TEXT_894);
    }
    if (!isImplementation) {
    stringBuffer.append(TEXT_895);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_896);
    } else {
    stringBuffer.append(TEXT_897);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_898);
    if (genModel.isReflectiveDelegation()) {
    stringBuffer.append(TEXT_899);
    stringBuffer.append(genFeature.getQualifiedFeatureAccessor());
    stringBuffer.append(TEXT_900);
    } else if (!genFeature.isVolatile()) {
    if (genFeature.isListType()) {
    if (genModel.isVirtualDelegation()) {
    stringBuffer.append(TEXT_901);
    stringBuffer.append(genFeature.getImportedType());
    stringBuffer.append(TEXT_902);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_903);
    stringBuffer.append(genFeature.getImportedType());
    stringBuffer.append(TEXT_904);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(TEXT_905);
    }
    stringBuffer.append(TEXT_906);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_907);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.InternalEList"));
    stringBuffer.append(TEXT_908);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_909);
    } else {
    if (genModel.isVirtualDelegation() && !genFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_910);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(TEXT_911);
    } else if (genClass.isESetFlag(genFeature)) {
    stringBuffer.append(TEXT_912);
    stringBuffer.append(genClass.getESetFlagsField(genFeature));
    stringBuffer.append(TEXT_913);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_914);
    } else {
    stringBuffer.append(TEXT_915);
    stringBuffer.append(genFeature.getUncapName());
    stringBuffer.append(TEXT_916);
    }
    }
    } else if (genFeature.hasDelegateFeature()) { GenFeature delegateFeature = genFeature.getDelegateFeature();
    if (delegateFeature.isWrappedFeatureMapType()) {
    stringBuffer.append(TEXT_917);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.FeatureMap"));
    stringBuffer.append(TEXT_918);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.FeatureMap"));
    stringBuffer.append(TEXT_919);
    stringBuffer.append(delegateFeature.getAccessorName());
    stringBuffer.append(TEXT_920);
    stringBuffer.append(genFeature.getQualifiedFeatureAccessor());
    stringBuffer.append(TEXT_921);
    } else {
    stringBuffer.append(TEXT_922);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.FeatureMap"));
    stringBuffer.append(TEXT_923);
    stringBuffer.append(delegateFeature.getAccessorName());
    stringBuffer.append(TEXT_924);
    stringBuffer.append(genFeature.getQualifiedFeatureAccessor());
    stringBuffer.append(TEXT_925);
    }
    } else {
    stringBuffer.append(TEXT_926);
    stringBuffer.append(genFeature.getFormattedName());
    stringBuffer.append(TEXT_927);
    stringBuffer.append(genFeature.getFeatureKind());
    stringBuffer.append(TEXT_928);
    //Class/isSetGenFeature.todo.override.javajetinc
    }
    stringBuffer.append(TEXT_929);
    }
    //Class/isSetGenFeature.override.javajetinc
    }
    //Class/genFeature.override.javajetinc
    }//for
    for (Iterator i= (isImplementation ? genClass.getImplementedGenOperations() : genClass.getDeclaredGenOperations()).iterator(); i.hasNext();) { GenOperation genOperation = (GenOperation)i.next();
    if (isInterface) {
    stringBuffer.append(TEXT_930);
    if (genOperation.hasDocumentation()) {
    stringBuffer.append(TEXT_931);
    stringBuffer.append(genOperation.getDocumentation(genModel.getIndentation(stringBuffer)));
    stringBuffer.append(TEXT_932);
    }
    if (!genModel.isSuppressEMFModelTags()) { boolean first = true; for (StringTokenizer stringTokenizer = new StringTokenizer(genOperation.getModelInfo(), "\n\r"); stringTokenizer.hasMoreTokens(); ) { String modelInfo = stringTokenizer.nextToken(); if (first) { first = false;
    stringBuffer.append(TEXT_933);
    stringBuffer.append(modelInfo);
    } else {
    stringBuffer.append(TEXT_934);
    stringBuffer.append(modelInfo);
    }} if (first) {
    stringBuffer.append(TEXT_935);
    }}
    stringBuffer.append(TEXT_936);
    } else {
    stringBuffer.append(TEXT_937);
    }
    if (!isImplementation) {
    stringBuffer.append(TEXT_938);
    stringBuffer.append(genOperation.getImportedType());
    stringBuffer.append(TEXT_939);
    stringBuffer.append(genOperation.getName());
    stringBuffer.append(TEXT_940);
    stringBuffer.append(genOperation.getParameters());
    stringBuffer.append(TEXT_941);
    stringBuffer.append(genOperation.getThrows());
    stringBuffer.append(TEXT_942);
    } else {
    stringBuffer.append(TEXT_943);
    stringBuffer.append(genOperation.getImportedType());
    stringBuffer.append(TEXT_944);
    stringBuffer.append(genOperation.getName());
    stringBuffer.append(TEXT_945);
    stringBuffer.append(genOperation.getParameters());
    stringBuffer.append(TEXT_946);
    stringBuffer.append(genOperation.getThrows());
    stringBuffer.append(TEXT_947);
    if (genOperation.hasBody()) {
    stringBuffer.append(TEXT_948);
    stringBuffer.append(genOperation.getBody(genModel.getIndentation(stringBuffer)));
    } else if (genOperation.isInvariant()) {GenClass opClass = genOperation.getGenClass(); String diagnostics = ((GenParameter)genOperation.getGenParameters().get(0)).getName(); String context = ((GenParameter)genOperation.getGenParameters().get(1)).getName();
    stringBuffer.append(TEXT_949);
    stringBuffer.append(diagnostics);
    stringBuffer.append(TEXT_950);
    stringBuffer.append(diagnostics);
    stringBuffer.append(TEXT_951);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.util.BasicDiagnostic"));
    stringBuffer.append(TEXT_952);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.util.Diagnostic"));
    stringBuffer.append(TEXT_953);
    stringBuffer.append(opClass.getGenPackage().getImportedValidatorClassName());
    stringBuffer.append(TEXT_954);
    stringBuffer.append(opClass.getGenPackage().getImportedValidatorClassName());
    stringBuffer.append(TEXT_955);
    stringBuffer.append(opClass.getOperationID(genOperation));
    stringBuffer.append(TEXT_956);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.plugin.EcorePlugin"));
    stringBuffer.append(TEXT_957);
    stringBuffer.append(genOperation.getName());
    stringBuffer.append(TEXT_958);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.EObjectValidator"));
    stringBuffer.append(TEXT_959);
    stringBuffer.append(context);
    stringBuffer.append(TEXT_960);
    stringBuffer.append(genModel.getNonNLS());
    stringBuffer.append(genModel.getNonNLS(2));
    stringBuffer.append(TEXT_961);
    } else {
    stringBuffer.append(TEXT_962);
    //Class/implementedGenOperation.todo.override.javajetinc
    }
    stringBuffer.append(TEXT_963);
    }
    //Class/implementedGenOperation.override.javajetinc
    }//for
    if (isImplementation && !genModel.isReflectiveDelegation() && genClass.implementsAny(genClass.getEInverseAddGenFeatures())) {
    stringBuffer.append(TEXT_964);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.notify.NotificationChain"));
    stringBuffer.append(TEXT_965);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.InternalEObject"));
    stringBuffer.append(TEXT_966);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.notify.NotificationChain"));
    stringBuffer.append(TEXT_967);
    for (Iterator i=genClass.getEInverseAddGenFeatures().iterator(); i.hasNext();) { GenFeature genFeature = (GenFeature)i.next();
    if (!genModel.isMinimalReflectiveMethods() || genClass.getImplementedGenFeatures().contains(genFeature)) {
    stringBuffer.append(TEXT_968);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(TEXT_969);
    if (genFeature.isListType()) {
    if (genFeature.isMapType() && genFeature.isEffectiveSuppressEMFTypes()) {
    stringBuffer.append(TEXT_970);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.InternalEList"));
    stringBuffer.append(TEXT_971);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.util.EMap"));
    stringBuffer.append(TEXT_972);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_973);
    } else {
    stringBuffer.append(TEXT_974);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.InternalEList"));
    stringBuffer.append(TEXT_975);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_976);
    }
    } else if (genFeature.isContainer()) {
    stringBuffer.append(TEXT_977);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(TEXT_978);
    } else {
    if (genClass.getImplementingGenModel(genFeature).isVirtualDelegation()) {
    stringBuffer.append(TEXT_979);
    stringBuffer.append(genFeature.getImportedType());
    stringBuffer.append(TEXT_980);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_981);
    stringBuffer.append(genFeature.getImportedType());
    stringBuffer.append(TEXT_982);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(TEXT_983);
    }
    stringBuffer.append(TEXT_984);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_985);
    if (genFeature.isEffectiveContains()) {
    stringBuffer.append(TEXT_986);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.InternalEObject"));
    stringBuffer.append(TEXT_987);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_988);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(TEXT_989);
    } else { GenFeature reverseFeature = genFeature.getReverse(); GenClass targetClass = reverseFeature.getGenClass();
    stringBuffer.append(TEXT_990);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.InternalEObject"));
    stringBuffer.append(TEXT_991);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_992);
    stringBuffer.append(targetClass.getQualifiedFeatureID(reverseFeature));
    stringBuffer.append(TEXT_993);
    stringBuffer.append(targetClass.getImportedInterfaceName());
    stringBuffer.append(TEXT_994);
    }
    stringBuffer.append(TEXT_995);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_996);
    stringBuffer.append(genFeature.getImportedType());
    stringBuffer.append(TEXT_997);
    }
    }
    }
    stringBuffer.append(TEXT_998);
    if (genModel.isMinimalReflectiveMethods()) {
    stringBuffer.append(TEXT_999);
    } else {
    stringBuffer.append(TEXT_1000);
    }
    stringBuffer.append(TEXT_1001);
    }
    if (isImplementation && !genModel.isReflectiveDelegation() && genClass.implementsAny(genClass.getEInverseRemoveGenFeatures())) {
    stringBuffer.append(TEXT_1002);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.notify.NotificationChain"));
    stringBuffer.append(TEXT_1003);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.InternalEObject"));
    stringBuffer.append(TEXT_1004);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.notify.NotificationChain"));
    stringBuffer.append(TEXT_1005);
    for (Iterator i=genClass.getEInverseRemoveGenFeatures().iterator(); i.hasNext();) { GenFeature genFeature = (GenFeature)i.next();
    if (!genModel.isMinimalReflectiveMethods() || genClass.getImplementedGenFeatures().contains(genFeature)) {
    stringBuffer.append(TEXT_1006);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(TEXT_1007);
    if (genFeature.isListType()) {
    if (genFeature.isMapType() && genFeature.isEffectiveSuppressEMFTypes()) {
    stringBuffer.append(TEXT_1008);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.InternalEList"));
    stringBuffer.append(TEXT_1009);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.util.EMap"));
    stringBuffer.append(TEXT_1010);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_1011);
    } else if (genFeature.isWrappedFeatureMapType()) {
    stringBuffer.append(TEXT_1012);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.InternalEList"));
    stringBuffer.append(TEXT_1013);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.FeatureMap"));
    stringBuffer.append(TEXT_1014);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_1015);
    } else {
    stringBuffer.append(TEXT_1016);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.InternalEList"));
    stringBuffer.append(TEXT_1017);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_1018);
    }
    } else if (genFeature.isContainer()) {
    stringBuffer.append(TEXT_1019);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(TEXT_1020);
    } else if (genFeature.isUnsettable()) {
    stringBuffer.append(TEXT_1021);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_1022);
    } else {
    stringBuffer.append(TEXT_1023);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_1024);
    }
    }
    }
    stringBuffer.append(TEXT_1025);
    if (genModel.isMinimalReflectiveMethods()) {
    stringBuffer.append(TEXT_1026);
    } else {
    stringBuffer.append(TEXT_1027);
    }
    stringBuffer.append(TEXT_1028);
    }
    if (isImplementation && !genModel.isReflectiveDelegation() && genClass.implementsAny(genClass.getEBasicRemoveFromContainerGenFeatures())) {
    stringBuffer.append(TEXT_1029);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.notify.NotificationChain"));
    stringBuffer.append(TEXT_1030);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.notify.NotificationChain"));
    stringBuffer.append(TEXT_1031);
    for (Iterator i=genClass.getEBasicRemoveFromContainerGenFeatures().iterator(); i.hasNext();) { GenFeature genFeature = (GenFeature)i.next();
    GenFeature reverseFeature = genFeature.getReverse(); GenClass targetClass = reverseFeature.getGenClass();
    if (!genModel.isMinimalReflectiveMethods() || genClass.getImplementedGenFeatures().contains(genFeature)) {
    stringBuffer.append(TEXT_1032);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(TEXT_1033);
    stringBuffer.append(targetClass.getQualifiedFeatureID(reverseFeature));
    stringBuffer.append(TEXT_1034);
    stringBuffer.append(targetClass.getImportedInterfaceName());
    stringBuffer.append(TEXT_1035);
    }
    }
    stringBuffer.append(TEXT_1036);
    if (genModel.isMinimalReflectiveMethods()) {
    stringBuffer.append(TEXT_1037);
    } else {
    stringBuffer.append(TEXT_1038);
    }
    stringBuffer.append(TEXT_1039);
    }
    if (isImplementation && !genModel.isReflectiveDelegation() && !genClass.getImplementedGenFeatures().isEmpty()) {
    stringBuffer.append(TEXT_1040);
    for (Iterator i=genClass.getAllGenFeatures().iterator(); i.hasNext();) { GenFeature genFeature = (GenFeature)i.next();
    if (!genModel.isMinimalReflectiveMethods() || genClass.getImplementedGenFeatures().contains(genFeature)) {
    stringBuffer.append(TEXT_1041);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(TEXT_1042);
    if (genFeature.isPrimitiveType()) {
    if (genFeature.isBooleanType()) {
    stringBuffer.append(TEXT_1043);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_1044);
    } else {
    stringBuffer.append(TEXT_1045);
    stringBuffer.append(genFeature.getObjectType());
    stringBuffer.append(TEXT_1046);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_1047);
    }
    } else if (genFeature.isResolveProxies() && !genFeature.isListType()) {
    stringBuffer.append(TEXT_1048);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_1049);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_1050);
    } else if (genFeature.isMapType()) {
    if (genFeature.isEffectiveSuppressEMFTypes()) {
    stringBuffer.append(TEXT_1051);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.util.EMap"));
    stringBuffer.append(TEXT_1052);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_1053);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_1054);
    } else {
    stringBuffer.append(TEXT_1055);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_1056);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_1057);
    }
    } else if (genFeature.isWrappedFeatureMapType()) {
    stringBuffer.append(TEXT_1058);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.FeatureMap"));
    stringBuffer.append(TEXT_1059);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_1060);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_1061);
    } else if (genFeature.isFeatureMapType()) {
    stringBuffer.append(TEXT_1062);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_1063);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.FeatureMap"));
    stringBuffer.append(TEXT_1064);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_1065);
    } else {
    stringBuffer.append(TEXT_1066);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_1067);
    }
    }
    }
    stringBuffer.append(TEXT_1068);
    if (genModel.isMinimalReflectiveMethods()) {
    stringBuffer.append(TEXT_1069);
    } else {
    stringBuffer.append(TEXT_1070);
    }
    stringBuffer.append(TEXT_1071);
    }
    if (isImplementation && !genModel.isReflectiveDelegation() && genClass.implementsAny(genClass.getESetGenFeatures())) {
    stringBuffer.append(TEXT_1072);
    for (Iterator i=genClass.getESetGenFeatures().iterator(); i.hasNext();) { GenFeature genFeature = (GenFeature)i.next();
    if (!genModel.isMinimalReflectiveMethods() || genClass.getImplementedGenFeatures().contains(genFeature)) {
    stringBuffer.append(TEXT_1073);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(TEXT_1074);
    if (genFeature.isListType()) {
    if (genFeature.isWrappedFeatureMapType()) {
    stringBuffer.append(TEXT_1075);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.FeatureMap"));
    stringBuffer.append(TEXT_1076);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.FeatureMap"));
    stringBuffer.append(TEXT_1077);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_1078);
    } else if (genFeature.isFeatureMapType()) {
    stringBuffer.append(TEXT_1079);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.FeatureMap"));
    stringBuffer.append(TEXT_1080);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_1081);
    } else if (genFeature.isMapType()) {
    if (genFeature.isEffectiveSuppressEMFTypes()) {
    stringBuffer.append(TEXT_1082);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.EStructuralFeature"));
    stringBuffer.append(TEXT_1083);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.util.EMap"));
    stringBuffer.append(TEXT_1084);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_1085);
    } else {
    stringBuffer.append(TEXT_1086);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.EStructuralFeature"));
    stringBuffer.append(TEXT_1087);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_1088);
    }
    } else {
    stringBuffer.append(TEXT_1089);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_1090);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_1091);
    stringBuffer.append(genModel.getImportedName("java.util.Collection"));
    stringBuffer.append(TEXT_1092);
    }
    } else if (genFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_1093);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_1094);
    stringBuffer.append(genFeature.getObjectType());
    stringBuffer.append(TEXT_1095);
    stringBuffer.append(genFeature.getPrimitiveValueFunction());
    stringBuffer.append(TEXT_1096);
    } else {
    stringBuffer.append(TEXT_1097);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_1098);
    stringBuffer.append(genFeature.getImportedType());
    stringBuffer.append(TEXT_1099);
    }
    stringBuffer.append(TEXT_1100);
    }
    }
    stringBuffer.append(TEXT_1101);
    if (genModel.isMinimalReflectiveMethods()) {
    stringBuffer.append(TEXT_1102);
    } else {
    stringBuffer.append(TEXT_1103);
    }
    stringBuffer.append(TEXT_1104);
    for (Iterator i=genClass.getESetGenFeatures().iterator(); i.hasNext();) { GenFeature genFeature = (GenFeature)i.next();
    if (!genModel.isMinimalReflectiveMethods() || genClass.getImplementedGenFeatures().contains(genFeature)) {
    stringBuffer.append(TEXT_1105);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(TEXT_1106);
    if (genFeature.isListType() && !genFeature.isUnsettable()) {
    if (genFeature.isWrappedFeatureMapType()) {
    stringBuffer.append(TEXT_1107);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.FeatureMap"));
    stringBuffer.append(TEXT_1108);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_1109);
    } else {
    stringBuffer.append(TEXT_1110);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_1111);
    }
    } else if (genFeature.isUnsettable()) {
    stringBuffer.append(TEXT_1112);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_1113);
    } else if (genFeature.isReferenceType()) {
    stringBuffer.append(TEXT_1114);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_1115);
    stringBuffer.append(genFeature.getImportedType());
    stringBuffer.append(TEXT_1116);
    } else {
    stringBuffer.append(TEXT_1117);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_1118);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_1119);
    }
    stringBuffer.append(TEXT_1120);
    }
    }
    stringBuffer.append(TEXT_1121);
    if (genModel.isMinimalReflectiveMethods()) {
    stringBuffer.append(TEXT_1122);
    } else {
    stringBuffer.append(TEXT_1123);
    }
    stringBuffer.append(TEXT_1124);
    }
    if (isImplementation && !genModel.isReflectiveDelegation() && !genClass.getImplementedGenFeatures().isEmpty()) {
    stringBuffer.append(TEXT_1125);
    for (Iterator i=genClass.getAllGenFeatures().iterator(); i.hasNext();) { GenFeature genFeature = (GenFeature)i.next();
    if (!genModel.isMinimalReflectiveMethods() || genClass.getImplementedGenFeatures().contains(genFeature)) {
    stringBuffer.append(TEXT_1126);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(TEXT_1127);
    if (genFeature.isListType() && !genFeature.isUnsettable()) {
    if (genFeature.isWrappedFeatureMapType()) {
    if (genFeature.isVolatile()) {
    stringBuffer.append(TEXT_1128);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.FeatureMap"));
    stringBuffer.append(TEXT_1129);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_1130);
    } else {
    stringBuffer.append(TEXT_1131);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_1132);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_1133);
    }
    } else {
    if (genClass.isField(genFeature)) {
    stringBuffer.append(TEXT_1134);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_1135);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_1136);
    } else {
    if (genFeature.isField() && genClass.getImplementingGenModel(genFeature).isVirtualDelegation()) {
    stringBuffer.append(TEXT_1137);
    stringBuffer.append(genFeature.getImportedType());
    stringBuffer.append(TEXT_1138);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_1139);
    stringBuffer.append(genFeature.getImportedType());
    stringBuffer.append(TEXT_1140);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(TEXT_1141);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_1142);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_1143);
    } else {
    stringBuffer.append(TEXT_1144);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_1145);
    }
    }
    }
    } else if (genFeature.isUnsettable()) {
    stringBuffer.append(TEXT_1146);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_1147);
    } else if (genFeature.isResolveProxies()) {
    if (genClass.isField(genFeature)) {
    stringBuffer.append(TEXT_1148);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_1149);
    } else {
    if (genFeature.isField() && genClass.getImplementingGenModel(genFeature).isVirtualDelegation()) {
    stringBuffer.append(TEXT_1150);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(TEXT_1151);
    } else {
    stringBuffer.append(TEXT_1152);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_1153);
    }
    }
    } else if (genFeature.isReferenceType()) {
    if (genClass.isField(genFeature)) {
    stringBuffer.append(TEXT_1154);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_1155);
    } else {
    if (genFeature.isField() && genClass.getImplementingGenModel(genFeature).isVirtualDelegation()) {
    stringBuffer.append(TEXT_1156);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(TEXT_1157);
    } else {
    stringBuffer.append(TEXT_1158);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_1159);
    }
    }
    } else if (genFeature.isPrimitiveType() || genFeature.isEnumType()) {
    if (genClass.isField(genFeature)) {
    if (genClass.isFlag(genFeature)) {
    stringBuffer.append(TEXT_1160);
    stringBuffer.append(genClass.getFlagsField(genFeature));
    stringBuffer.append(TEXT_1161);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_1162);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_1163);
    } else {
    stringBuffer.append(TEXT_1164);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_1165);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_1166);
    }
    } else {
    if (genFeature.isEnumType() && genFeature.isField() && genClass.getImplementingGenModel(genFeature).isVirtualDelegation()) {
    stringBuffer.append(TEXT_1167);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(TEXT_1168);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_1169);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_1170);
    } else {
    stringBuffer.append(TEXT_1171);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_1172);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_1173);
    }
    }
    } else {//datatype
    if (genClass.isField(genFeature)) {
    stringBuffer.append(TEXT_1174);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_1175);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_1176);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_1177);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_1178);
    } else {
    if (genFeature.isField() && genClass.getImplementingGenModel(genFeature).isVirtualDelegation()) {
    stringBuffer.append(TEXT_1179);
    stringBuffer.append(genFeature.getImportedType());
    stringBuffer.append(TEXT_1180);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_1181);
    stringBuffer.append(genFeature.getImportedType());
    stringBuffer.append(TEXT_1182);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(TEXT_1183);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_1184);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_1185);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_1186);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_1187);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_1188);
    } else {
    stringBuffer.append(TEXT_1189);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_1190);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_1191);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_1192);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_1193);
    }
    }
    }
    }
    }
    stringBuffer.append(TEXT_1194);
    if (genModel.isMinimalReflectiveMethods()) {
    stringBuffer.append(TEXT_1195);
    } else {
    stringBuffer.append(TEXT_1196);
    }
    stringBuffer.append(TEXT_1197);
    //Class/eIsSet.override.javajetinc
    }
    if (isImplementation && !genClass.getMixinGenFeatures().isEmpty()) {
    stringBuffer.append(TEXT_1198);
    for (Iterator m=genClass.getMixinGenClasses().iterator(); m.hasNext();) { GenClass mixinGenClass = (GenClass)m.next(); 
    stringBuffer.append(TEXT_1199);
    stringBuffer.append(mixinGenClass.getImportedInterfaceName());
    stringBuffer.append(TEXT_1200);
    for (Iterator f=mixinGenClass.getGenFeatures().iterator(); f.hasNext();) { GenFeature genFeature = (GenFeature)f.next(); 
    stringBuffer.append(TEXT_1201);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(TEXT_1202);
    stringBuffer.append(mixinGenClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(TEXT_1203);
    }
    stringBuffer.append(TEXT_1204);
    }
    stringBuffer.append(TEXT_1205);
    for (Iterator m=genClass.getMixinGenClasses().iterator(); m.hasNext();) { GenClass mixinGenClass = (GenClass)m.next(); 
    stringBuffer.append(TEXT_1206);
    stringBuffer.append(mixinGenClass.getImportedInterfaceName());
    stringBuffer.append(TEXT_1207);
    for (Iterator f=mixinGenClass.getGenFeatures().iterator(); f.hasNext();) { GenFeature genFeature = (GenFeature)f.next(); 
    stringBuffer.append(TEXT_1208);
    stringBuffer.append(mixinGenClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(TEXT_1209);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(TEXT_1210);
    }
    stringBuffer.append(TEXT_1211);
    }
    stringBuffer.append(TEXT_1212);
    }
    if (isImplementation && genModel.isVirtualDelegation()) { String eVirtualValuesField = genClass.getEVirtualValuesField();
    if (eVirtualValuesField != null) {
    stringBuffer.append(TEXT_1213);
    stringBuffer.append(eVirtualValuesField);
    stringBuffer.append(TEXT_1214);
    stringBuffer.append(eVirtualValuesField);
    stringBuffer.append(TEXT_1215);
    }
    { List eVirtualIndexBitFields = genClass.getEVirtualIndexBitFields(new ArrayList());
    if (!eVirtualIndexBitFields.isEmpty()) { List allEVirtualIndexBitFields = genClass.getAllEVirtualIndexBitFields(new ArrayList());
    stringBuffer.append(TEXT_1216);
    for (int i = 0; i < allEVirtualIndexBitFields.size(); i++) {
    stringBuffer.append(TEXT_1217);
    stringBuffer.append(i);
    stringBuffer.append(TEXT_1218);
    stringBuffer.append(allEVirtualIndexBitFields.get(i));
    stringBuffer.append(TEXT_1219);
    }
    stringBuffer.append(TEXT_1220);
    for (int i = 0; i < allEVirtualIndexBitFields.size(); i++) {
    stringBuffer.append(TEXT_1221);
    stringBuffer.append(i);
    stringBuffer.append(TEXT_1222);
    stringBuffer.append(allEVirtualIndexBitFields.get(i));
    stringBuffer.append(TEXT_1223);
    }
    stringBuffer.append(TEXT_1224);
    }
    }
    }
    if (isImplementation && !genModel.isReflectiveDelegation() && !genClass.getToStringGenFeatures().isEmpty()) {
    stringBuffer.append(TEXT_1225);
    { boolean first = true;
    for (Iterator i=genClass.getToStringGenFeatures().iterator(); i.hasNext(); ) { GenFeature genFeature = (GenFeature)i.next();
    if (first) { first = false;
    stringBuffer.append(TEXT_1226);
    stringBuffer.append(genFeature.getName());
    stringBuffer.append(TEXT_1227);
    stringBuffer.append(genModel.getNonNLS());
    } else {
    stringBuffer.append(TEXT_1228);
    stringBuffer.append(genFeature.getName());
    stringBuffer.append(TEXT_1229);
    stringBuffer.append(genModel.getNonNLS());
    }
    if (genFeature.isUnsettable() && !genFeature.isListType()) {
    if (genModel.isVirtualDelegation() && !genFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_1230);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(TEXT_1231);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(TEXT_1232);
    stringBuffer.append(genModel.getNonNLS());
    } else {
    if (genClass.isFlag(genFeature)) {
    stringBuffer.append(TEXT_1233);
    if (genClass.isESetFlag(genFeature)) {
    stringBuffer.append(TEXT_1234);
    stringBuffer.append(genClass.getESetFlagsField(genFeature));
    stringBuffer.append(TEXT_1235);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_1236);
    } else {
    stringBuffer.append(genFeature.getUncapName());
    stringBuffer.append(TEXT_1237);
    }
    stringBuffer.append(TEXT_1238);
    stringBuffer.append(genClass.getFlagsField(genFeature));
    stringBuffer.append(TEXT_1239);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_1240);
    stringBuffer.append(genModel.getNonNLS());
    } else {
    stringBuffer.append(TEXT_1241);
    if (genClass.isESetFlag(genFeature)) {
    stringBuffer.append(TEXT_1242);
    stringBuffer.append(genClass.getESetFlagsField(genFeature));
    stringBuffer.append(TEXT_1243);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_1244);
    } else {
    stringBuffer.append(genFeature.getUncapName());
    stringBuffer.append(TEXT_1245);
    }
    stringBuffer.append(TEXT_1246);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_1247);
    stringBuffer.append(genModel.getNonNLS());
    }
    }
    } else {
    if (genModel.isVirtualDelegation() && !genFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_1248);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    if (!genFeature.isListType() && !genFeature.isReferenceType()){
    stringBuffer.append(TEXT_1249);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_1250);
    }
    stringBuffer.append(TEXT_1251);
    } else {
    if (genClass.isFlag(genFeature)) {
    stringBuffer.append(TEXT_1252);
    stringBuffer.append(genClass.getFlagsField(genFeature));
    stringBuffer.append(TEXT_1253);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_1254);
    } else {
    stringBuffer.append(TEXT_1255);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_1256);
    }
    }
    }
    }
    }
    stringBuffer.append(TEXT_1257);
    }
    if (isImplementation && genClass.isMapEntry()) { GenFeature keyFeature = genClass.getMapEntryKeyFeature(); GenFeature valueFeature = genClass.getMapEntryValueFeature();
    stringBuffer.append(TEXT_1258);
    if (keyFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_1259);
    stringBuffer.append(keyFeature.getObjectType());
    stringBuffer.append(TEXT_1260);
    } else {
    stringBuffer.append(TEXT_1261);
    }
    stringBuffer.append(TEXT_1262);
    if (keyFeature.isListType()) {
    stringBuffer.append(TEXT_1263);
    stringBuffer.append(genModel.getImportedName("java.util.Collection"));
    stringBuffer.append(TEXT_1264);
    } else if (keyFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_1265);
    stringBuffer.append(keyFeature.getObjectType());
    stringBuffer.append(TEXT_1266);
    stringBuffer.append(keyFeature.getPrimitiveValueFunction());
    stringBuffer.append(TEXT_1267);
    } else {
    stringBuffer.append(TEXT_1268);
    stringBuffer.append(keyFeature.getImportedType());
    stringBuffer.append(TEXT_1269);
    }
    stringBuffer.append(TEXT_1270);
    if (valueFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_1271);
    stringBuffer.append(valueFeature.getObjectType());
    stringBuffer.append(TEXT_1272);
    } else {
    stringBuffer.append(TEXT_1273);
    }
    stringBuffer.append(TEXT_1274);
    if (valueFeature.isListType()) {
    stringBuffer.append(TEXT_1275);
    stringBuffer.append(genModel.getImportedName("java.util.Collection"));
    stringBuffer.append(TEXT_1276);
    } else if (valueFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_1277);
    stringBuffer.append(valueFeature.getObjectType());
    stringBuffer.append(TEXT_1278);
    stringBuffer.append(valueFeature.getPrimitiveValueFunction());
    stringBuffer.append(TEXT_1279);
    } else {
    stringBuffer.append(TEXT_1280);
    stringBuffer.append(valueFeature.getImportedType());
    stringBuffer.append(TEXT_1281);
    }
    stringBuffer.append(TEXT_1282);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.util.EMap"));
    stringBuffer.append(TEXT_1283);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.EObject"));
    stringBuffer.append(TEXT_1284);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.util.EMap"));
    stringBuffer.append(TEXT_1285);
    }
    stringBuffer.append(TEXT_1286);
    stringBuffer.append(isInterface ? " " + genClass.getInterfaceName() : genClass.getClassName());
    // TODO fix the space above
    genModel.emitSortedImports();
    stringBuffer.append(TEXT_1287);
    return stringBuffer.toString();
  }
}
