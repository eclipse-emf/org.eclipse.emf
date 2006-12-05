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
  protected final String TEXT_80 = " = ";
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
  protected final String TEXT_99 = ";" + NL;
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
  protected final String TEXT_116 = NL + "\t}" + NL + "" + NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */";
  protected final String TEXT_117 = NL + "\t@Override";
  protected final String TEXT_118 = NL + "\tprotected ";
  protected final String TEXT_119 = " eStaticClass()" + NL + "\t{" + NL + "\t\treturn ";
  protected final String TEXT_120 = ";" + NL + "\t}" + NL;
  protected final String TEXT_121 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */";
  protected final String TEXT_122 = NL + "\t";
  protected final String TEXT_123 = "[] ";
  protected final String TEXT_124 = "();" + NL;
  protected final String TEXT_125 = NL + "\tpublic ";
  protected final String TEXT_126 = "[] ";
  protected final String TEXT_127 = "()" + NL + "\t{";
  protected final String TEXT_128 = NL + "\t\t";
  protected final String TEXT_129 = " list = (";
  protected final String TEXT_130 = ")";
  protected final String TEXT_131 = "();" + NL + "\t\tif (list.isEmpty()) return ";
  protected final String TEXT_132 = "_EEMPTY_ARRAY;";
  protected final String TEXT_133 = NL + "\t\tif (";
  protected final String TEXT_134 = " == null || ";
  protected final String TEXT_135 = ".isEmpty()) return ";
  protected final String TEXT_136 = "_EEMPTY_ARRAY;" + NL + "\t\t";
  protected final String TEXT_137 = " list = (";
  protected final String TEXT_138 = ")";
  protected final String TEXT_139 = ";";
  protected final String TEXT_140 = NL + "\t\tlist.shrink();" + NL + "\t\treturn (";
  protected final String TEXT_141 = "[])list.data();" + NL + "\t}" + NL;
  protected final String TEXT_142 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */";
  protected final String TEXT_143 = NL + "\t";
  protected final String TEXT_144 = " get";
  protected final String TEXT_145 = "(int index);" + NL;
  protected final String TEXT_146 = NL + "\tpublic ";
  protected final String TEXT_147 = " get";
  protected final String TEXT_148 = "(int index)" + NL + "\t{" + NL + "\t\treturn (";
  protected final String TEXT_149 = ")";
  protected final String TEXT_150 = "().get(index);" + NL + "\t}" + NL;
  protected final String TEXT_151 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */";
  protected final String TEXT_152 = NL + "\tint get";
  protected final String TEXT_153 = "Length();" + NL;
  protected final String TEXT_154 = NL + "\tpublic int get";
  protected final String TEXT_155 = "Length()" + NL + "\t{";
  protected final String TEXT_156 = NL + "\t\treturn ";
  protected final String TEXT_157 = "().size();";
  protected final String TEXT_158 = NL + "\t\treturn ";
  protected final String TEXT_159 = " == null ? 0 : ";
  protected final String TEXT_160 = ".size();";
  protected final String TEXT_161 = NL + "\t}" + NL;
  protected final String TEXT_162 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */";
  protected final String TEXT_163 = NL + "\tvoid set";
  protected final String TEXT_164 = "(";
  protected final String TEXT_165 = "[] new";
  protected final String TEXT_166 = ");" + NL;
  protected final String TEXT_167 = NL + "\tpublic void set";
  protected final String TEXT_168 = "(";
  protected final String TEXT_169 = "[] new";
  protected final String TEXT_170 = ")" + NL + "\t{" + NL + "\t\t((";
  protected final String TEXT_171 = ")";
  protected final String TEXT_172 = "()).setData(new";
  protected final String TEXT_173 = ".length, new";
  protected final String TEXT_174 = ");" + NL + "\t}" + NL;
  protected final String TEXT_175 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */";
  protected final String TEXT_176 = NL + "\tvoid set";
  protected final String TEXT_177 = "(int index, ";
  protected final String TEXT_178 = " element);" + NL;
  protected final String TEXT_179 = NL + "\tpublic void set";
  protected final String TEXT_180 = "(int index, ";
  protected final String TEXT_181 = " element)" + NL + "\t{" + NL + "\t\t";
  protected final String TEXT_182 = "().set(index, element);" + NL + "\t}" + NL;
  protected final String TEXT_183 = NL + "\t/**" + NL + "\t * Returns the value of the '<em><b>";
  protected final String TEXT_184 = "</b></em>' ";
  protected final String TEXT_185 = ".";
  protected final String TEXT_186 = NL + "\t * The key is of type ";
  protected final String TEXT_187 = "list of {@link ";
  protected final String TEXT_188 = "}";
  protected final String TEXT_189 = "{@link ";
  protected final String TEXT_190 = "}";
  protected final String TEXT_191 = "," + NL + "\t * and the value is of type ";
  protected final String TEXT_192 = "list of {@link ";
  protected final String TEXT_193 = "}";
  protected final String TEXT_194 = "{@link ";
  protected final String TEXT_195 = "}";
  protected final String TEXT_196 = ",";
  protected final String TEXT_197 = NL + "\t * The list contents are of type {@link ";
  protected final String TEXT_198 = "}.";
  protected final String TEXT_199 = NL + "\t * The default value is <code>";
  protected final String TEXT_200 = "</code>.";
  protected final String TEXT_201 = NL + "\t * The literals are from the enumeration {@link ";
  protected final String TEXT_202 = "}.";
  protected final String TEXT_203 = NL + "\t * It is bidirectional and its opposite is '{@link ";
  protected final String TEXT_204 = "#";
  protected final String TEXT_205 = " <em>";
  protected final String TEXT_206 = "</em>}'.";
  protected final String TEXT_207 = NL + "\t * <!-- begin-user-doc -->";
  protected final String TEXT_208 = NL + "\t * <p>" + NL + "\t * If the meaning of the '<em>";
  protected final String TEXT_209 = "</em>' ";
  protected final String TEXT_210 = " isn't clear," + NL + "\t * there really should be more of a description here..." + NL + "\t * </p>";
  protected final String TEXT_211 = NL + "\t * <!-- end-user-doc -->";
  protected final String TEXT_212 = NL + "\t * <!-- begin-model-doc -->" + NL + "\t * ";
  protected final String TEXT_213 = NL + "\t * <!-- end-model-doc -->";
  protected final String TEXT_214 = NL + "\t * @return the value of the '<em>";
  protected final String TEXT_215 = "</em>' ";
  protected final String TEXT_216 = ".";
  protected final String TEXT_217 = NL + "\t * @see ";
  protected final String TEXT_218 = NL + "\t * @see #isSet";
  protected final String TEXT_219 = "()";
  protected final String TEXT_220 = NL + "\t * @see #unset";
  protected final String TEXT_221 = "()";
  protected final String TEXT_222 = NL + "\t * @see #set";
  protected final String TEXT_223 = "(";
  protected final String TEXT_224 = ")";
  protected final String TEXT_225 = NL + "\t * @see ";
  protected final String TEXT_226 = "#get";
  protected final String TEXT_227 = "()";
  protected final String TEXT_228 = NL + "\t * @see ";
  protected final String TEXT_229 = "#";
  protected final String TEXT_230 = NL + "\t * @model ";
  protected final String TEXT_231 = NL + "\t *        ";
  protected final String TEXT_232 = NL + "\t * @model";
  protected final String TEXT_233 = NL + "\t * @generated" + NL + "\t */";
  protected final String TEXT_234 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */";
  protected final String TEXT_235 = NL + "\t";
  protected final String TEXT_236 = " ";
  protected final String TEXT_237 = "();" + NL;
  protected final String TEXT_238 = NL + "\tpublic ";
  protected final String TEXT_239 = " ";
  protected final String TEXT_240 = "()" + NL + "\t{";
  protected final String TEXT_241 = NL + "\t\treturn ";
  protected final String TEXT_242 = "(";
  protected final String TEXT_243 = "(";
  protected final String TEXT_244 = ")eGet(";
  protected final String TEXT_245 = ", true)";
  protected final String TEXT_246 = ").";
  protected final String TEXT_247 = "()";
  protected final String TEXT_248 = ";";
  protected final String TEXT_249 = NL + "\t\t";
  protected final String TEXT_250 = " ";
  protected final String TEXT_251 = " = (";
  protected final String TEXT_252 = ")eVirtualGet(";
  protected final String TEXT_253 = ");";
  protected final String TEXT_254 = NL + "\t\tif (";
  protected final String TEXT_255 = " == null)" + NL + "\t\t{";
  protected final String TEXT_256 = NL + "\t\t\teVirtualSet(";
  protected final String TEXT_257 = ", ";
  protected final String TEXT_258 = " = new ";
  protected final String TEXT_259 = ");";
  protected final String TEXT_260 = NL + "\t\t\t";
  protected final String TEXT_261 = " = new ";
  protected final String TEXT_262 = ";";
  protected final String TEXT_263 = NL + "\t\t}" + NL + "\t\treturn ";
  protected final String TEXT_264 = ";";
  protected final String TEXT_265 = NL + "\t\tif (eContainerFeatureID != ";
  protected final String TEXT_266 = ") return null;" + NL + "\t\treturn (";
  protected final String TEXT_267 = ")eContainer();";
  protected final String TEXT_268 = NL + "\t\t";
  protected final String TEXT_269 = " ";
  protected final String TEXT_270 = " = (";
  protected final String TEXT_271 = ")eVirtualGet(";
  protected final String TEXT_272 = ", ";
  protected final String TEXT_273 = ");";
  protected final String TEXT_274 = NL + "\t\tif (";
  protected final String TEXT_275 = " != null && ";
  protected final String TEXT_276 = ".eIsProxy())" + NL + "\t\t{" + NL + "\t\t\t";
  protected final String TEXT_277 = " old";
  protected final String TEXT_278 = " = (";
  protected final String TEXT_279 = ")";
  protected final String TEXT_280 = ";" + NL + "\t\t\t";
  protected final String TEXT_281 = " = ";
  protected final String TEXT_282 = "eResolveProxy(old";
  protected final String TEXT_283 = ");" + NL + "\t\t\tif (";
  protected final String TEXT_284 = " != old";
  protected final String TEXT_285 = ")" + NL + "\t\t\t{";
  protected final String TEXT_286 = NL + "\t\t\t\t";
  protected final String TEXT_287 = " new";
  protected final String TEXT_288 = " = (";
  protected final String TEXT_289 = ")";
  protected final String TEXT_290 = ";";
  protected final String TEXT_291 = NL + "\t\t\t\t";
  protected final String TEXT_292 = " msgs = old";
  protected final String TEXT_293 = ".eInverseRemove(this, EOPPOSITE_FEATURE_BASE - ";
  protected final String TEXT_294 = ", null, null);";
  protected final String TEXT_295 = NL + "\t\t\t\t";
  protected final String TEXT_296 = " msgs =  old";
  protected final String TEXT_297 = ".eInverseRemove(this, ";
  protected final String TEXT_298 = ", ";
  protected final String TEXT_299 = ".class, null);";
  protected final String TEXT_300 = NL + "\t\t\t\tif (new";
  protected final String TEXT_301 = ".eInternalContainer() == null)" + NL + "\t\t\t\t{";
  protected final String TEXT_302 = NL + "\t\t\t\t\tmsgs = new";
  protected final String TEXT_303 = ".eInverseAdd(this, EOPPOSITE_FEATURE_BASE - ";
  protected final String TEXT_304 = ", null, msgs);";
  protected final String TEXT_305 = NL + "\t\t\t\t\tmsgs =  new";
  protected final String TEXT_306 = ".eInverseAdd(this, ";
  protected final String TEXT_307 = ", ";
  protected final String TEXT_308 = ".class, msgs);";
  protected final String TEXT_309 = NL + "\t\t\t\t}" + NL + "\t\t\t\tif (msgs != null) msgs.dispatch();";
  protected final String TEXT_310 = NL + "\t\t\t\teVirtualSet(";
  protected final String TEXT_311 = ", ";
  protected final String TEXT_312 = ");";
  protected final String TEXT_313 = NL + "\t\t\t\tif (eNotificationRequired())" + NL + "\t\t\t\t\teNotify(new ";
  protected final String TEXT_314 = "(this, ";
  protected final String TEXT_315 = ".RESOLVE, ";
  protected final String TEXT_316 = ", old";
  protected final String TEXT_317 = ", ";
  protected final String TEXT_318 = "));";
  protected final String TEXT_319 = NL + "\t\t\t}" + NL + "\t\t}";
  protected final String TEXT_320 = NL + "\t\treturn (";
  protected final String TEXT_321 = ")eVirtualGet(";
  protected final String TEXT_322 = ", ";
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
  protected final String TEXT_343 = "()).featureMap().";
  protected final String TEXT_344 = "list(";
  protected final String TEXT_345 = "));";
  protected final String TEXT_346 = NL + "\t\treturn (";
  protected final String TEXT_347 = ")get";
  protected final String TEXT_348 = "().";
  protected final String TEXT_349 = "list(";
  protected final String TEXT_350 = ");";
  protected final String TEXT_351 = NL + "\t\treturn ((";
  protected final String TEXT_352 = ".Internal.Wrapper)get";
  protected final String TEXT_353 = "()).featureMap().list(";
  protected final String TEXT_354 = ");";
  protected final String TEXT_355 = NL + "\t\treturn get";
  protected final String TEXT_356 = "().list(";
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
  protected final String TEXT_379 = NL + "\t\t// Ensure that you remove @generated or mark it @generated NOT";
  protected final String TEXT_380 = NL + "\t\t// The list is expected to implement org.eclipse.emf.ecore.util.InternalEList and org.eclipse.emf.ecore.EStructuralFeature.Setting" + NL + "\t\t// so it's likely that an appropriate subclass of org.eclipse.emf.ecore.util.";
  protected final String TEXT_381 = "EcoreEMap";
  protected final String TEXT_382 = "BasicFeatureMap";
  protected final String TEXT_383 = "EcoreEList";
  protected final String TEXT_384 = " should be used.";
  protected final String TEXT_385 = NL + "\t\tthrow new UnsupportedOperationException();";
  protected final String TEXT_386 = NL + "\t}" + NL;
  protected final String TEXT_387 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */" + NL + "\tpublic ";
  protected final String TEXT_388 = " basicGet";
  protected final String TEXT_389 = "()" + NL + "\t{";
  protected final String TEXT_390 = NL + "\t\tif (eContainerFeatureID != ";
  protected final String TEXT_391 = ") return null;" + NL + "\t\treturn (";
  protected final String TEXT_392 = ")eInternalContainer();";
  protected final String TEXT_393 = NL + "\t\treturn (";
  protected final String TEXT_394 = ")eVirtualGet(";
  protected final String TEXT_395 = ");";
  protected final String TEXT_396 = NL + "\t\treturn ";
  protected final String TEXT_397 = ";";
  protected final String TEXT_398 = NL + "\t\treturn (";
  protected final String TEXT_399 = ")((";
  protected final String TEXT_400 = ".Internal.Wrapper)get";
  protected final String TEXT_401 = "()).featureMap().get(";
  protected final String TEXT_402 = ", false);";
  protected final String TEXT_403 = NL + "\t\treturn (";
  protected final String TEXT_404 = ")get";
  protected final String TEXT_405 = "().get(";
  protected final String TEXT_406 = ", false);";
  protected final String TEXT_407 = NL + "\t\t// TODO: implement this method to return the '";
  protected final String TEXT_408 = "' ";
  protected final String TEXT_409 = NL + "\t\t// -> do not perform proxy resolution" + NL + "\t\t// Ensure that you remove @generated or mark it @generated NOT" + NL + "\t\tthrow new UnsupportedOperationException();";
  protected final String TEXT_410 = NL + "\t}" + NL;
  protected final String TEXT_411 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */" + NL + "\tpublic ";
  protected final String TEXT_412 = " basicSet";
  protected final String TEXT_413 = "(";
  protected final String TEXT_414 = " new";
  protected final String TEXT_415 = ", ";
  protected final String TEXT_416 = " msgs)" + NL + "\t{";
  protected final String TEXT_417 = NL + "\t\tmsgs = eBasicSetContainer((";
  protected final String TEXT_418 = ")new";
  protected final String TEXT_419 = ", ";
  protected final String TEXT_420 = ", msgs);";
  protected final String TEXT_421 = NL + "\t\treturn msgs;";
  protected final String TEXT_422 = NL + "\t\tObject old";
  protected final String TEXT_423 = " = eVirtualSet(";
  protected final String TEXT_424 = ", new";
  protected final String TEXT_425 = ");";
  protected final String TEXT_426 = NL + "\t\t";
  protected final String TEXT_427 = " old";
  protected final String TEXT_428 = " = ";
  protected final String TEXT_429 = ";" + NL + "\t\t";
  protected final String TEXT_430 = " = new";
  protected final String TEXT_431 = ";";
  protected final String TEXT_432 = NL + "\t\tboolean isSetChange = old";
  protected final String TEXT_433 = " == EVIRTUAL_NO_VALUE;";
  protected final String TEXT_434 = NL + "\t\tboolean old";
  protected final String TEXT_435 = "ESet = (";
  protected final String TEXT_436 = " & ";
  protected final String TEXT_437 = "_ESETFLAG) != 0;" + NL + "\t\t";
  protected final String TEXT_438 = " |= ";
  protected final String TEXT_439 = "_ESETFLAG;";
  protected final String TEXT_440 = NL + "\t\tboolean old";
  protected final String TEXT_441 = "ESet = ";
  protected final String TEXT_442 = "ESet;" + NL + "\t\t";
  protected final String TEXT_443 = "ESet = true;";
  protected final String TEXT_444 = NL + "\t\tif (eNotificationRequired())" + NL + "\t\t{";
  protected final String TEXT_445 = NL + "\t\t\t";
  protected final String TEXT_446 = " notification = new ";
  protected final String TEXT_447 = "(this, ";
  protected final String TEXT_448 = ".SET, ";
  protected final String TEXT_449 = ", ";
  protected final String TEXT_450 = "isSetChange ? null : old";
  protected final String TEXT_451 = "old";
  protected final String TEXT_452 = ", new";
  protected final String TEXT_453 = ", ";
  protected final String TEXT_454 = "isSetChange";
  protected final String TEXT_455 = "!old";
  protected final String TEXT_456 = "ESet";
  protected final String TEXT_457 = ");";
  protected final String TEXT_458 = NL + "\t\t\t";
  protected final String TEXT_459 = " notification = new ";
  protected final String TEXT_460 = "(this, ";
  protected final String TEXT_461 = ".SET, ";
  protected final String TEXT_462 = ", ";
  protected final String TEXT_463 = "old";
  protected final String TEXT_464 = " == EVIRTUAL_NO_VALUE ? null : old";
  protected final String TEXT_465 = "old";
  protected final String TEXT_466 = ", new";
  protected final String TEXT_467 = ");";
  protected final String TEXT_468 = NL + "\t\t\tif (msgs == null) msgs = notification; else msgs.add(notification);" + NL + "\t\t}";
  protected final String TEXT_469 = NL + "\t\treturn msgs;";
  protected final String TEXT_470 = NL + "\t\treturn ((";
  protected final String TEXT_471 = ".Internal)((";
  protected final String TEXT_472 = ".Internal.Wrapper)get";
  protected final String TEXT_473 = "()).featureMap()).basicAdd(";
  protected final String TEXT_474 = ", new";
  protected final String TEXT_475 = ", msgs);";
  protected final String TEXT_476 = NL + "\t\treturn ((";
  protected final String TEXT_477 = ".Internal)get";
  protected final String TEXT_478 = "()).basicAdd(";
  protected final String TEXT_479 = ", new";
  protected final String TEXT_480 = ", msgs);";
  protected final String TEXT_481 = NL + "\t\t// TODO: implement this method to set the contained '";
  protected final String TEXT_482 = "' ";
  protected final String TEXT_483 = NL + "\t\t// -> this method is automatically invoked to keep the containment relationship in synch" + NL + "\t\t// -> do not modify other features" + NL + "\t\t// -> return msgs, after adding any generated Notification to it (if it is null, a NotificationChain object must be created first)" + NL + "\t\t// Ensure that you remove @generated or mark it @generated NOT" + NL + "\t\tthrow new UnsupportedOperationException();";
  protected final String TEXT_484 = NL + "\t}" + NL;
  protected final String TEXT_485 = NL + "\t/**" + NL + "\t * Sets the value of the '{@link ";
  protected final String TEXT_486 = "#";
  protected final String TEXT_487 = " <em>";
  protected final String TEXT_488 = "</em>}' ";
  protected final String TEXT_489 = "." + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @param value the new value of the '<em>";
  protected final String TEXT_490 = "</em>' ";
  protected final String TEXT_491 = ".";
  protected final String TEXT_492 = NL + "\t * @see ";
  protected final String TEXT_493 = NL + "\t * @see #isSet";
  protected final String TEXT_494 = "()";
  protected final String TEXT_495 = NL + "\t * @see #unset";
  protected final String TEXT_496 = "()";
  protected final String TEXT_497 = NL + "\t * @see #";
  protected final String TEXT_498 = "()" + NL + "\t * @generated" + NL + "\t */";
  protected final String TEXT_499 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */";
  protected final String TEXT_500 = NL + "\tvoid set";
  protected final String TEXT_501 = "(";
  protected final String TEXT_502 = " value);" + NL;
  protected final String TEXT_503 = NL + "\tpublic void set";
  protected final String TEXT_504 = "(";
  protected final String TEXT_505 = " new";
  protected final String TEXT_506 = ")" + NL + "\t{";
  protected final String TEXT_507 = NL + "\t\teSet(";
  protected final String TEXT_508 = ", ";
  protected final String TEXT_509 = "new ";
  protected final String TEXT_510 = "(";
  protected final String TEXT_511 = "new";
  protected final String TEXT_512 = ")";
  protected final String TEXT_513 = ");";
  protected final String TEXT_514 = NL + "\t\tif (new";
  protected final String TEXT_515 = " != eInternalContainer() || (eContainerFeatureID != ";
  protected final String TEXT_516 = " && new";
  protected final String TEXT_517 = " != null))" + NL + "\t\t{" + NL + "\t\t\tif (";
  protected final String TEXT_518 = ".isAncestor(this, ";
  protected final String TEXT_519 = "new";
  protected final String TEXT_520 = "))" + NL + "\t\t\t\tthrow new ";
  protected final String TEXT_521 = "(\"Recursive containment not allowed for \" + toString());";
  protected final String TEXT_522 = NL + "\t\t\t";
  protected final String TEXT_523 = " msgs = null;" + NL + "\t\t\tif (eInternalContainer() != null)" + NL + "\t\t\t\tmsgs = eBasicRemoveFromContainer(msgs);" + NL + "\t\t\tif (new";
  protected final String TEXT_524 = " != null)" + NL + "\t\t\t\tmsgs = ((";
  protected final String TEXT_525 = ")new";
  protected final String TEXT_526 = ").eInverseAdd(this, ";
  protected final String TEXT_527 = ", ";
  protected final String TEXT_528 = ".class, msgs);" + NL + "\t\t\tmsgs = basicSet";
  protected final String TEXT_529 = "(";
  protected final String TEXT_530 = "new";
  protected final String TEXT_531 = ", msgs);" + NL + "\t\t\tif (msgs != null) msgs.dispatch();" + NL + "\t\t}";
  protected final String TEXT_532 = NL + "\t\telse if (eNotificationRequired())" + NL + "\t\t\teNotify(new ";
  protected final String TEXT_533 = "(this, ";
  protected final String TEXT_534 = ".SET, ";
  protected final String TEXT_535 = ", new";
  protected final String TEXT_536 = ", new";
  protected final String TEXT_537 = "));";
  protected final String TEXT_538 = NL + "\t\t";
  protected final String TEXT_539 = " ";
  protected final String TEXT_540 = " = (";
  protected final String TEXT_541 = ")eVirtualGet(";
  protected final String TEXT_542 = ");";
  protected final String TEXT_543 = NL + "\t\tif (new";
  protected final String TEXT_544 = " != ";
  protected final String TEXT_545 = ")" + NL + "\t\t{" + NL + "\t\t\t";
  protected final String TEXT_546 = " msgs = null;" + NL + "\t\t\tif (";
  protected final String TEXT_547 = " != null)";
  protected final String TEXT_548 = NL + "\t\t\t\tmsgs = ((";
  protected final String TEXT_549 = ")";
  protected final String TEXT_550 = ").eInverseRemove(this, EOPPOSITE_FEATURE_BASE - ";
  protected final String TEXT_551 = ", null, msgs);" + NL + "\t\t\tif (new";
  protected final String TEXT_552 = " != null)" + NL + "\t\t\t\tmsgs = ((";
  protected final String TEXT_553 = ")new";
  protected final String TEXT_554 = ").eInverseAdd(this, EOPPOSITE_FEATURE_BASE - ";
  protected final String TEXT_555 = ", null, msgs);";
  protected final String TEXT_556 = NL + "\t\t\t\tmsgs = ((";
  protected final String TEXT_557 = ")";
  protected final String TEXT_558 = ").eInverseRemove(this, ";
  protected final String TEXT_559 = ", ";
  protected final String TEXT_560 = ".class, msgs);" + NL + "\t\t\tif (new";
  protected final String TEXT_561 = " != null)" + NL + "\t\t\t\tmsgs = ((";
  protected final String TEXT_562 = ")new";
  protected final String TEXT_563 = ").eInverseAdd(this, ";
  protected final String TEXT_564 = ", ";
  protected final String TEXT_565 = ".class, msgs);";
  protected final String TEXT_566 = NL + "\t\t\tmsgs = basicSet";
  protected final String TEXT_567 = "(";
  protected final String TEXT_568 = "new";
  protected final String TEXT_569 = ", msgs);" + NL + "\t\t\tif (msgs != null) msgs.dispatch();" + NL + "\t\t}";
  protected final String TEXT_570 = NL + "\t\telse" + NL + "\t\t{";
  protected final String TEXT_571 = NL + "\t\t\tboolean old";
  protected final String TEXT_572 = "ESet = eVirtualIsSet(";
  protected final String TEXT_573 = ");";
  protected final String TEXT_574 = NL + "\t\t\tboolean old";
  protected final String TEXT_575 = "ESet = (";
  protected final String TEXT_576 = " & ";
  protected final String TEXT_577 = "_ESETFLAG) != 0;";
  protected final String TEXT_578 = NL + "\t\t\t";
  protected final String TEXT_579 = " |= ";
  protected final String TEXT_580 = "_ESETFLAG;";
  protected final String TEXT_581 = NL + "\t\t\tboolean old";
  protected final String TEXT_582 = "ESet = ";
  protected final String TEXT_583 = "ESet;";
  protected final String TEXT_584 = NL + "\t\t\t";
  protected final String TEXT_585 = "ESet = true;";
  protected final String TEXT_586 = NL + "\t\t\tif (eNotificationRequired())" + NL + "\t\t\t\teNotify(new ";
  protected final String TEXT_587 = "(this, ";
  protected final String TEXT_588 = ".SET, ";
  protected final String TEXT_589 = ", new";
  protected final String TEXT_590 = ", new";
  protected final String TEXT_591 = ", !old";
  protected final String TEXT_592 = "ESet));";
  protected final String TEXT_593 = NL + "\t\t}";
  protected final String TEXT_594 = NL + "\t\telse if (eNotificationRequired())" + NL + "\t\t\teNotify(new ";
  protected final String TEXT_595 = "(this, ";
  protected final String TEXT_596 = ".SET, ";
  protected final String TEXT_597 = ", new";
  protected final String TEXT_598 = ", new";
  protected final String TEXT_599 = "));";
  protected final String TEXT_600 = NL + "\t\t";
  protected final String TEXT_601 = " old";
  protected final String TEXT_602 = " = (";
  protected final String TEXT_603 = " & ";
  protected final String TEXT_604 = "_EFLAG) != 0;";
  protected final String TEXT_605 = NL + "\t\tif (new";
  protected final String TEXT_606 = ") ";
  protected final String TEXT_607 = " |= ";
  protected final String TEXT_608 = "_EFLAG; else ";
  protected final String TEXT_609 = " &= ~";
  protected final String TEXT_610 = "_EFLAG;";
  protected final String TEXT_611 = NL + "\t\t";
  protected final String TEXT_612 = " old";
  protected final String TEXT_613 = " = ";
  protected final String TEXT_614 = ";";
  protected final String TEXT_615 = NL + "\t\t";
  protected final String TEXT_616 = " ";
  protected final String TEXT_617 = " = new";
  protected final String TEXT_618 = " == null ? ";
  protected final String TEXT_619 = " : new";
  protected final String TEXT_620 = ";";
  protected final String TEXT_621 = NL + "\t\t";
  protected final String TEXT_622 = " = new";
  protected final String TEXT_623 = " == null ? ";
  protected final String TEXT_624 = " : new";
  protected final String TEXT_625 = ";";
  protected final String TEXT_626 = NL + "\t\t";
  protected final String TEXT_627 = " ";
  protected final String TEXT_628 = " = ";
  protected final String TEXT_629 = "new";
  protected final String TEXT_630 = ";";
  protected final String TEXT_631 = NL + "\t\t";
  protected final String TEXT_632 = " = ";
  protected final String TEXT_633 = "new";
  protected final String TEXT_634 = ";";
  protected final String TEXT_635 = NL + "\t\tObject old";
  protected final String TEXT_636 = " = eVirtualSet(";
  protected final String TEXT_637 = ", ";
  protected final String TEXT_638 = ");";
  protected final String TEXT_639 = NL + "\t\tboolean isSetChange = old";
  protected final String TEXT_640 = " == EVIRTUAL_NO_VALUE;";
  protected final String TEXT_641 = NL + "\t\tboolean old";
  protected final String TEXT_642 = "ESet = (";
  protected final String TEXT_643 = " & ";
  protected final String TEXT_644 = "_ESETFLAG) != 0;";
  protected final String TEXT_645 = NL + "\t\t";
  protected final String TEXT_646 = " |= ";
  protected final String TEXT_647 = "_ESETFLAG;";
  protected final String TEXT_648 = NL + "\t\tboolean old";
  protected final String TEXT_649 = "ESet = ";
  protected final String TEXT_650 = "ESet;";
  protected final String TEXT_651 = NL + "\t\t";
  protected final String TEXT_652 = "ESet = true;";
  protected final String TEXT_653 = NL + "\t\tif (eNotificationRequired())" + NL + "\t\t\teNotify(new ";
  protected final String TEXT_654 = "(this, ";
  protected final String TEXT_655 = ".SET, ";
  protected final String TEXT_656 = ", ";
  protected final String TEXT_657 = "isSetChange ? ";
  protected final String TEXT_658 = " : old";
  protected final String TEXT_659 = "old";
  protected final String TEXT_660 = ", ";
  protected final String TEXT_661 = "new";
  protected final String TEXT_662 = ", ";
  protected final String TEXT_663 = "isSetChange";
  protected final String TEXT_664 = "!old";
  protected final String TEXT_665 = "ESet";
  protected final String TEXT_666 = "));";
  protected final String TEXT_667 = NL + "\t\tif (eNotificationRequired())" + NL + "\t\t\teNotify(new ";
  protected final String TEXT_668 = "(this, ";
  protected final String TEXT_669 = ".SET, ";
  protected final String TEXT_670 = ", ";
  protected final String TEXT_671 = "old";
  protected final String TEXT_672 = " == EVIRTUAL_NO_VALUE ? ";
  protected final String TEXT_673 = " : old";
  protected final String TEXT_674 = "old";
  protected final String TEXT_675 = ", ";
  protected final String TEXT_676 = "new";
  protected final String TEXT_677 = "));";
  protected final String TEXT_678 = NL + "\t\t((";
  protected final String TEXT_679 = ".Internal)((";
  protected final String TEXT_680 = ".Internal.Wrapper)get";
  protected final String TEXT_681 = "()).featureMap()).set(";
  protected final String TEXT_682 = ", ";
  protected final String TEXT_683 = "new ";
  protected final String TEXT_684 = "(";
  protected final String TEXT_685 = "new";
  protected final String TEXT_686 = ")";
  protected final String TEXT_687 = ");";
  protected final String TEXT_688 = NL + "\t\t((";
  protected final String TEXT_689 = ".Internal)get";
  protected final String TEXT_690 = "()).set(";
  protected final String TEXT_691 = ", ";
  protected final String TEXT_692 = "new ";
  protected final String TEXT_693 = "(";
  protected final String TEXT_694 = "new";
  protected final String TEXT_695 = ")";
  protected final String TEXT_696 = ");";
  protected final String TEXT_697 = NL + "\t\t// TODO: implement this method to set the '";
  protected final String TEXT_698 = "' ";
  protected final String TEXT_699 = NL + "\t\t// Ensure that you remove @generated or mark it @generated NOT" + NL + "\t\tthrow new UnsupportedOperationException();";
  protected final String TEXT_700 = NL + "\t}" + NL;
  protected final String TEXT_701 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */" + NL + "\tpublic ";
  protected final String TEXT_702 = " basicUnset";
  protected final String TEXT_703 = "(";
  protected final String TEXT_704 = " msgs)" + NL + "\t{";
  protected final String TEXT_705 = NL + "\t\tObject old";
  protected final String TEXT_706 = " = eVirtualUnset(";
  protected final String TEXT_707 = ");";
  protected final String TEXT_708 = NL + "\t\t";
  protected final String TEXT_709 = " old";
  protected final String TEXT_710 = " = ";
  protected final String TEXT_711 = ";" + NL + "\t\t";
  protected final String TEXT_712 = " = null;";
  protected final String TEXT_713 = NL + "\t\tboolean isSetChange = old";
  protected final String TEXT_714 = " != EVIRTUAL_NO_VALUE;";
  protected final String TEXT_715 = NL + "\t\tboolean old";
  protected final String TEXT_716 = "ESet = (";
  protected final String TEXT_717 = " & ";
  protected final String TEXT_718 = "_ESETFLAG) != 0;" + NL + "\t\t";
  protected final String TEXT_719 = " &= ~";
  protected final String TEXT_720 = "_ESETFLAG;";
  protected final String TEXT_721 = NL + "\t\tboolean old";
  protected final String TEXT_722 = "ESet = ";
  protected final String TEXT_723 = "ESet;" + NL + "\t\t";
  protected final String TEXT_724 = "ESet = false;";
  protected final String TEXT_725 = NL + "\t\tif (eNotificationRequired())" + NL + "\t\t{" + NL + "\t\t\t";
  protected final String TEXT_726 = " notification = new ";
  protected final String TEXT_727 = "(this, ";
  protected final String TEXT_728 = ".UNSET, ";
  protected final String TEXT_729 = ", ";
  protected final String TEXT_730 = "isSetChange ? old";
  protected final String TEXT_731 = " : null";
  protected final String TEXT_732 = "old";
  protected final String TEXT_733 = ", null, ";
  protected final String TEXT_734 = "isSetChange";
  protected final String TEXT_735 = "old";
  protected final String TEXT_736 = "ESet";
  protected final String TEXT_737 = ");" + NL + "\t\t\tif (msgs == null) msgs = notification; else msgs.add(notification);" + NL + "\t\t}" + NL + "\t\treturn msgs;";
  protected final String TEXT_738 = NL + "\t\t// TODO: implement this method to unset the contained '";
  protected final String TEXT_739 = "' ";
  protected final String TEXT_740 = NL + "\t\t// -> this method is automatically invoked to keep the containment relationship in synch" + NL + "\t\t// -> do not modify other features" + NL + "\t\t// -> return msgs, after adding any generated Notification to it (if it is null, a NotificationChain object must be created first)" + NL + "\t\t// Ensure that you remove @generated or mark it @generated NOT" + NL + "\t\tthrow new UnsupportedOperationException();";
  protected final String TEXT_741 = NL + "\t}" + NL;
  protected final String TEXT_742 = NL + "\t/**" + NL + "\t * Unsets the value of the '{@link ";
  protected final String TEXT_743 = "#";
  protected final String TEXT_744 = " <em>";
  protected final String TEXT_745 = "</em>}' ";
  protected final String TEXT_746 = "." + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->";
  protected final String TEXT_747 = NL + "\t * @see #isSet";
  protected final String TEXT_748 = "()";
  protected final String TEXT_749 = NL + "\t * @see #";
  protected final String TEXT_750 = "()";
  protected final String TEXT_751 = NL + "\t * @see #set";
  protected final String TEXT_752 = "(";
  protected final String TEXT_753 = ")";
  protected final String TEXT_754 = NL + "\t * @generated" + NL + "\t */";
  protected final String TEXT_755 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */";
  protected final String TEXT_756 = NL + "\tvoid unset";
  protected final String TEXT_757 = "();" + NL;
  protected final String TEXT_758 = NL + "\tpublic void unset";
  protected final String TEXT_759 = "()" + NL + "\t{";
  protected final String TEXT_760 = NL + "\t\teUnset(";
  protected final String TEXT_761 = ");";
  protected final String TEXT_762 = NL + "\t\t";
  protected final String TEXT_763 = " ";
  protected final String TEXT_764 = " = (";
  protected final String TEXT_765 = ")eVirtualGet(";
  protected final String TEXT_766 = ");";
  protected final String TEXT_767 = NL + "\t\tif (";
  protected final String TEXT_768 = " != null) ((";
  protected final String TEXT_769 = ".Unsettable";
  protected final String TEXT_770 = ")";
  protected final String TEXT_771 = ").unset();";
  protected final String TEXT_772 = NL + "\t\t";
  protected final String TEXT_773 = " ";
  protected final String TEXT_774 = " = (";
  protected final String TEXT_775 = ")eVirtualGet(";
  protected final String TEXT_776 = ");";
  protected final String TEXT_777 = NL + "\t\tif (";
  protected final String TEXT_778 = " != null)" + NL + "\t\t{" + NL + "\t\t\t";
  protected final String TEXT_779 = " msgs = null;";
  protected final String TEXT_780 = NL + "\t\t\tmsgs = ((";
  protected final String TEXT_781 = ")";
  protected final String TEXT_782 = ").eInverseRemove(this, EOPPOSITE_FEATURE_BASE - ";
  protected final String TEXT_783 = ", null, msgs);";
  protected final String TEXT_784 = NL + "\t\t\tmsgs = ((";
  protected final String TEXT_785 = ")";
  protected final String TEXT_786 = ").eInverseRemove(this, ";
  protected final String TEXT_787 = ", ";
  protected final String TEXT_788 = ".class, msgs);";
  protected final String TEXT_789 = NL + "\t\t\tmsgs = basicUnset";
  protected final String TEXT_790 = "(msgs);" + NL + "\t\t\tif (msgs != null) msgs.dispatch();" + NL + "\t\t}" + NL + "\t\telse" + NL + "\t\t{";
  protected final String TEXT_791 = NL + "\t\t\tboolean old";
  protected final String TEXT_792 = "ESet = eVirtualIsSet(";
  protected final String TEXT_793 = ");";
  protected final String TEXT_794 = NL + "\t\t\tboolean old";
  protected final String TEXT_795 = "ESet = (";
  protected final String TEXT_796 = " & ";
  protected final String TEXT_797 = "_ESETFLAG) != 0;";
  protected final String TEXT_798 = NL + "\t\t\t";
  protected final String TEXT_799 = " &= ~";
  protected final String TEXT_800 = "_ESETFLAG;";
  protected final String TEXT_801 = NL + "\t\t\tboolean old";
  protected final String TEXT_802 = "ESet = ";
  protected final String TEXT_803 = "ESet;";
  protected final String TEXT_804 = NL + "\t\t\t";
  protected final String TEXT_805 = "ESet = false;";
  protected final String TEXT_806 = NL + "\t\t\tif (eNotificationRequired())" + NL + "\t\t\t\teNotify(new ";
  protected final String TEXT_807 = "(this, ";
  protected final String TEXT_808 = ".UNSET, ";
  protected final String TEXT_809 = ", null, null, old";
  protected final String TEXT_810 = "ESet));";
  protected final String TEXT_811 = NL + "\t\t}";
  protected final String TEXT_812 = NL + "\t\t";
  protected final String TEXT_813 = " old";
  protected final String TEXT_814 = " = (";
  protected final String TEXT_815 = " & ";
  protected final String TEXT_816 = "_EFLAG) != 0;";
  protected final String TEXT_817 = NL + "\t\tObject old";
  protected final String TEXT_818 = " = eVirtualUnset(";
  protected final String TEXT_819 = ");";
  protected final String TEXT_820 = NL + "\t\t";
  protected final String TEXT_821 = " old";
  protected final String TEXT_822 = " = ";
  protected final String TEXT_823 = ";";
  protected final String TEXT_824 = NL + "\t\tboolean isSetChange = old";
  protected final String TEXT_825 = " != EVIRTUAL_NO_VALUE;";
  protected final String TEXT_826 = NL + "\t\tboolean old";
  protected final String TEXT_827 = "ESet = (";
  protected final String TEXT_828 = " & ";
  protected final String TEXT_829 = "_ESETFLAG) != 0;";
  protected final String TEXT_830 = NL + "\t\tboolean old";
  protected final String TEXT_831 = "ESet = ";
  protected final String TEXT_832 = "ESet;";
  protected final String TEXT_833 = NL + "\t\t";
  protected final String TEXT_834 = " = null;";
  protected final String TEXT_835 = NL + "\t\t";
  protected final String TEXT_836 = " &= ~";
  protected final String TEXT_837 = "_ESETFLAG;";
  protected final String TEXT_838 = NL + "\t\t";
  protected final String TEXT_839 = "ESet = false;";
  protected final String TEXT_840 = NL + "\t\tif (eNotificationRequired())" + NL + "\t\t\teNotify(new ";
  protected final String TEXT_841 = "(this, ";
  protected final String TEXT_842 = ".UNSET, ";
  protected final String TEXT_843 = ", ";
  protected final String TEXT_844 = "isSetChange ? old";
  protected final String TEXT_845 = " : null";
  protected final String TEXT_846 = "old";
  protected final String TEXT_847 = ", null, ";
  protected final String TEXT_848 = "isSetChange";
  protected final String TEXT_849 = "old";
  protected final String TEXT_850 = "ESet";
  protected final String TEXT_851 = "));";
  protected final String TEXT_852 = NL + "\t\tif (";
  protected final String TEXT_853 = ") ";
  protected final String TEXT_854 = " |= ";
  protected final String TEXT_855 = "_EFLAG; else ";
  protected final String TEXT_856 = " &= ~";
  protected final String TEXT_857 = "_EFLAG;";
  protected final String TEXT_858 = NL + "\t\t";
  protected final String TEXT_859 = " = ";
  protected final String TEXT_860 = ";";
  protected final String TEXT_861 = NL + "\t\t";
  protected final String TEXT_862 = " &= ~";
  protected final String TEXT_863 = "_ESETFLAG;";
  protected final String TEXT_864 = NL + "\t\t";
  protected final String TEXT_865 = "ESet = false;";
  protected final String TEXT_866 = NL + "\t\tif (eNotificationRequired())" + NL + "\t\t\teNotify(new ";
  protected final String TEXT_867 = "(this, ";
  protected final String TEXT_868 = ".UNSET, ";
  protected final String TEXT_869 = ", ";
  protected final String TEXT_870 = "isSetChange ? old";
  protected final String TEXT_871 = " : ";
  protected final String TEXT_872 = "old";
  protected final String TEXT_873 = ", ";
  protected final String TEXT_874 = ", ";
  protected final String TEXT_875 = "isSetChange";
  protected final String TEXT_876 = "old";
  protected final String TEXT_877 = "ESet";
  protected final String TEXT_878 = "));";
  protected final String TEXT_879 = NL + "\t\t((";
  protected final String TEXT_880 = ".Internal)((";
  protected final String TEXT_881 = ".Internal.Wrapper)get";
  protected final String TEXT_882 = "()).featureMap()).clear(";
  protected final String TEXT_883 = ");";
  protected final String TEXT_884 = NL + "\t\t((";
  protected final String TEXT_885 = ".Internal)get";
  protected final String TEXT_886 = "()).clear(";
  protected final String TEXT_887 = ");";
  protected final String TEXT_888 = NL + "\t\t// TODO: implement this method to unset the '";
  protected final String TEXT_889 = "' ";
  protected final String TEXT_890 = NL + "\t\t// Ensure that you remove @generated or mark it @generated NOT" + NL + "\t\tthrow new UnsupportedOperationException();";
  protected final String TEXT_891 = NL + "\t}" + NL;
  protected final String TEXT_892 = NL + "\t/**" + NL + "\t * Returns whether the value of the '{@link ";
  protected final String TEXT_893 = "#";
  protected final String TEXT_894 = " <em>";
  protected final String TEXT_895 = "</em>}' ";
  protected final String TEXT_896 = " is set." + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @return whether the value of the '<em>";
  protected final String TEXT_897 = "</em>' ";
  protected final String TEXT_898 = " is set.";
  protected final String TEXT_899 = NL + "\t * @see #unset";
  protected final String TEXT_900 = "()";
  protected final String TEXT_901 = NL + "\t * @see #";
  protected final String TEXT_902 = "()";
  protected final String TEXT_903 = NL + "\t * @see #set";
  protected final String TEXT_904 = "(";
  protected final String TEXT_905 = ")";
  protected final String TEXT_906 = NL + "\t * @generated" + NL + "\t */";
  protected final String TEXT_907 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */";
  protected final String TEXT_908 = NL + "\tboolean isSet";
  protected final String TEXT_909 = "();" + NL;
  protected final String TEXT_910 = NL + "\tpublic boolean isSet";
  protected final String TEXT_911 = "()" + NL + "\t{";
  protected final String TEXT_912 = NL + "\t\treturn eIsSet(";
  protected final String TEXT_913 = ");";
  protected final String TEXT_914 = NL + "\t\t";
  protected final String TEXT_915 = " ";
  protected final String TEXT_916 = " = (";
  protected final String TEXT_917 = ")eVirtualGet(";
  protected final String TEXT_918 = ");";
  protected final String TEXT_919 = NL + "\t\treturn ";
  protected final String TEXT_920 = " != null && ((";
  protected final String TEXT_921 = ".Unsettable";
  protected final String TEXT_922 = ")";
  protected final String TEXT_923 = ").isSet();";
  protected final String TEXT_924 = NL + "\t\treturn eVirtualIsSet(";
  protected final String TEXT_925 = ");";
  protected final String TEXT_926 = NL + "\t\treturn (";
  protected final String TEXT_927 = " & ";
  protected final String TEXT_928 = "_ESETFLAG) != 0;";
  protected final String TEXT_929 = NL + "\t\treturn ";
  protected final String TEXT_930 = "ESet;";
  protected final String TEXT_931 = NL + "\t\treturn !((";
  protected final String TEXT_932 = ".Internal)((";
  protected final String TEXT_933 = ".Internal.Wrapper)get";
  protected final String TEXT_934 = "()).featureMap()).isEmpty(";
  protected final String TEXT_935 = ");";
  protected final String TEXT_936 = NL + "\t\treturn !((";
  protected final String TEXT_937 = ".Internal)get";
  protected final String TEXT_938 = "()).isEmpty(";
  protected final String TEXT_939 = ");";
  protected final String TEXT_940 = NL + "\t\t// TODO: implement this method to return whether the '";
  protected final String TEXT_941 = "' ";
  protected final String TEXT_942 = " is set" + NL + "\t\t// Ensure that you remove @generated or mark it @generated NOT" + NL + "\t\tthrow new UnsupportedOperationException();";
  protected final String TEXT_943 = NL + "\t}" + NL;
  protected final String TEXT_944 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->";
  protected final String TEXT_945 = NL + "\t * <!-- begin-model-doc -->" + NL + "\t * ";
  protected final String TEXT_946 = NL + "\t * <!-- end-model-doc -->";
  protected final String TEXT_947 = NL + "\t * @model ";
  protected final String TEXT_948 = NL + "\t *        ";
  protected final String TEXT_949 = NL + "\t * @model";
  protected final String TEXT_950 = NL + "\t * @generated" + NL + "\t */";
  protected final String TEXT_951 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */";
  protected final String TEXT_952 = NL + "\t";
  protected final String TEXT_953 = " ";
  protected final String TEXT_954 = "(";
  protected final String TEXT_955 = ")";
  protected final String TEXT_956 = ";" + NL;
  protected final String TEXT_957 = NL + "\tpublic ";
  protected final String TEXT_958 = " ";
  protected final String TEXT_959 = "(";
  protected final String TEXT_960 = ")";
  protected final String TEXT_961 = NL + "\t{";
  protected final String TEXT_962 = NL + "\t\t";
  protected final String TEXT_963 = NL + "\t\t// TODO: implement this method" + NL + "\t\t// -> specify the condition that violates the invariant" + NL + "\t\t// -> verify the details of the diagnostic, including severity and message" + NL + "\t\t// Ensure that you remove @generated or mark it @generated NOT" + NL + "\t\tif (false)" + NL + "\t\t{" + NL + "\t\t\tif (";
  protected final String TEXT_964 = " != null)" + NL + "\t\t\t{" + NL + "\t\t\t\t";
  protected final String TEXT_965 = ".add" + NL + "\t\t\t\t\t(new ";
  protected final String TEXT_966 = NL + "\t\t\t\t\t\t(";
  protected final String TEXT_967 = ".ERROR," + NL + "\t\t\t\t\t\t ";
  protected final String TEXT_968 = ".DIAGNOSTIC_SOURCE," + NL + "\t\t\t\t\t\t ";
  protected final String TEXT_969 = ".";
  protected final String TEXT_970 = "," + NL + "\t\t\t\t\t\t ";
  protected final String TEXT_971 = ".INSTANCE.getString(\"_UI_GenericInvariant_diagnostic\", new Object[] { \"";
  protected final String TEXT_972 = "\", ";
  protected final String TEXT_973 = ".getObjectLabel(this, ";
  protected final String TEXT_974 = ") }),";
  protected final String TEXT_975 = NL + "\t\t\t\t\t\t new Object [] { this }));" + NL + "\t\t\t}" + NL + "\t\t\treturn false;" + NL + "\t\t}" + NL + "\t\treturn true;";
  protected final String TEXT_976 = NL + "\t\t// TODO: implement this method" + NL + "\t\t// Ensure that you remove @generated or mark it @generated NOT" + NL + "\t\tthrow new UnsupportedOperationException();";
  protected final String TEXT_977 = NL + "\t}" + NL;
  protected final String TEXT_978 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */";
  protected final String TEXT_979 = NL + "\t@SuppressWarnings(\"unchecked\")";
  protected final String TEXT_980 = NL + "\t@Override";
  protected final String TEXT_981 = NL + "\tpublic ";
  protected final String TEXT_982 = " eInverseAdd(";
  protected final String TEXT_983 = " otherEnd, int featureID, ";
  protected final String TEXT_984 = " msgs)" + NL + "\t{" + NL + "\t\tswitch (featureID)" + NL + "\t\t{";
  protected final String TEXT_985 = NL + "\t\t\tcase ";
  protected final String TEXT_986 = ":";
  protected final String TEXT_987 = NL + "\t\t\t\treturn ((";
  protected final String TEXT_988 = "(";
  protected final String TEXT_989 = ".InternalMapView";
  protected final String TEXT_990 = ")";
  protected final String TEXT_991 = "()).eMap()).basicAdd(otherEnd, msgs);";
  protected final String TEXT_992 = NL + "\t\t\t\treturn (";
  protected final String TEXT_993 = "()).basicAdd(otherEnd, msgs);";
  protected final String TEXT_994 = NL + "\t\t\t\tif (eInternalContainer() != null)" + NL + "\t\t\t\t\tmsgs = eBasicRemoveFromContainer(msgs);";
  protected final String TEXT_995 = NL + "\t\t\t\treturn basicSet";
  protected final String TEXT_996 = "((";
  protected final String TEXT_997 = ")otherEnd, msgs);";
  protected final String TEXT_998 = NL + "\t\t\t\treturn eBasicSetContainer(otherEnd, ";
  protected final String TEXT_999 = ", msgs);";
  protected final String TEXT_1000 = NL + "\t\t\t\t";
  protected final String TEXT_1001 = " ";
  protected final String TEXT_1002 = " = (";
  protected final String TEXT_1003 = ")eVirtualGet(";
  protected final String TEXT_1004 = ");";
  protected final String TEXT_1005 = NL + "\t\t\t\tif (";
  protected final String TEXT_1006 = " != null)";
  protected final String TEXT_1007 = NL + "\t\t\t\t\tmsgs = ((";
  protected final String TEXT_1008 = ")";
  protected final String TEXT_1009 = ").eInverseRemove(this, EOPPOSITE_FEATURE_BASE - ";
  protected final String TEXT_1010 = ", null, msgs);";
  protected final String TEXT_1011 = NL + "\t\t\t\t\tmsgs = ((";
  protected final String TEXT_1012 = ")";
  protected final String TEXT_1013 = ").eInverseRemove(this, ";
  protected final String TEXT_1014 = ", ";
  protected final String TEXT_1015 = ".class, msgs);";
  protected final String TEXT_1016 = NL + "\t\t\t\treturn basicSet";
  protected final String TEXT_1017 = "((";
  protected final String TEXT_1018 = ")otherEnd, msgs);";
  protected final String TEXT_1019 = NL + "\t\t}";
  protected final String TEXT_1020 = NL + "\t\treturn super.eInverseAdd(otherEnd, featureID, msgs);";
  protected final String TEXT_1021 = NL + "\t\treturn eDynamicInverseAdd(otherEnd, featureID, msgs);";
  protected final String TEXT_1022 = NL + "\t}" + NL;
  protected final String TEXT_1023 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */";
  protected final String TEXT_1024 = NL + "\t@Override";
  protected final String TEXT_1025 = NL + "\tpublic ";
  protected final String TEXT_1026 = " eInverseRemove(";
  protected final String TEXT_1027 = " otherEnd, int featureID, ";
  protected final String TEXT_1028 = " msgs)" + NL + "\t{" + NL + "\t\tswitch (featureID)" + NL + "\t\t{";
  protected final String TEXT_1029 = NL + "\t\t\tcase ";
  protected final String TEXT_1030 = ":";
  protected final String TEXT_1031 = NL + "\t\t\t\treturn ((";
  protected final String TEXT_1032 = ")((";
  protected final String TEXT_1033 = ".InternalMapView)";
  protected final String TEXT_1034 = "()).eMap()).basicRemove(otherEnd, msgs);";
  protected final String TEXT_1035 = NL + "\t\t\t\treturn ((";
  protected final String TEXT_1036 = ")((";
  protected final String TEXT_1037 = ".Internal.Wrapper)";
  protected final String TEXT_1038 = "()).featureMap()).basicRemove(otherEnd, msgs);";
  protected final String TEXT_1039 = NL + "\t\t\t\treturn ((";
  protected final String TEXT_1040 = ")";
  protected final String TEXT_1041 = "()).basicRemove(otherEnd, msgs);";
  protected final String TEXT_1042 = NL + "\t\t\t\treturn eBasicSetContainer(null, ";
  protected final String TEXT_1043 = ", msgs);";
  protected final String TEXT_1044 = NL + "\t\t\t\treturn basicUnset";
  protected final String TEXT_1045 = "(msgs);";
  protected final String TEXT_1046 = NL + "\t\t\t\treturn basicSet";
  protected final String TEXT_1047 = "(null, msgs);";
  protected final String TEXT_1048 = NL + "\t\t}";
  protected final String TEXT_1049 = NL + "\t\treturn super.eInverseRemove(otherEnd, featureID, msgs);";
  protected final String TEXT_1050 = NL + "\t\treturn eDynamicInverseRemove(otherEnd, featureID, msgs);";
  protected final String TEXT_1051 = NL + "\t}" + NL;
  protected final String TEXT_1052 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */";
  protected final String TEXT_1053 = NL + "\t@Override";
  protected final String TEXT_1054 = NL + "\tpublic ";
  protected final String TEXT_1055 = " eBasicRemoveFromContainerFeature(";
  protected final String TEXT_1056 = " msgs)" + NL + "\t{" + NL + "\t\tswitch (eContainerFeatureID)" + NL + "\t\t{";
  protected final String TEXT_1057 = NL + "\t\t\tcase ";
  protected final String TEXT_1058 = ":" + NL + "\t\t\t\treturn eInternalContainer().eInverseRemove(this, ";
  protected final String TEXT_1059 = ", ";
  protected final String TEXT_1060 = ".class, msgs);";
  protected final String TEXT_1061 = NL + "\t\t}";
  protected final String TEXT_1062 = NL + "\t\treturn super.eBasicRemoveFromContainerFeature(msgs);";
  protected final String TEXT_1063 = NL + "\t\treturn eDynamicBasicRemoveFromContainer(msgs);";
  protected final String TEXT_1064 = NL + "\t}" + NL;
  protected final String TEXT_1065 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */";
  protected final String TEXT_1066 = NL + "\t@Override";
  protected final String TEXT_1067 = NL + "\tpublic Object eGet(int featureID, boolean resolve, boolean coreType)" + NL + "\t{" + NL + "\t\tswitch (featureID)" + NL + "\t\t{";
  protected final String TEXT_1068 = NL + "\t\t\tcase ";
  protected final String TEXT_1069 = ":";
  protected final String TEXT_1070 = NL + "\t\t\t\treturn ";
  protected final String TEXT_1071 = "() ? Boolean.TRUE : Boolean.FALSE;";
  protected final String TEXT_1072 = NL + "\t\t\t\treturn new ";
  protected final String TEXT_1073 = "(";
  protected final String TEXT_1074 = "());";
  protected final String TEXT_1075 = NL + "\t\t\t\tif (resolve) return ";
  protected final String TEXT_1076 = "();" + NL + "\t\t\t\treturn basicGet";
  protected final String TEXT_1077 = "();";
  protected final String TEXT_1078 = NL + "\t\t\t\tif (coreType) return ((";
  protected final String TEXT_1079 = ".InternalMapView)";
  protected final String TEXT_1080 = "()).eMap();" + NL + "\t\t\t\telse return ";
  protected final String TEXT_1081 = "();";
  protected final String TEXT_1082 = NL + "\t\t\t\tif (coreType) return ";
  protected final String TEXT_1083 = "();" + NL + "\t\t\t\telse return ";
  protected final String TEXT_1084 = "().map();";
  protected final String TEXT_1085 = NL + "\t\t\t\tif (coreType) return ((";
  protected final String TEXT_1086 = ".Internal.Wrapper)";
  protected final String TEXT_1087 = "()).featureMap();" + NL + "\t\t\t\treturn ";
  protected final String TEXT_1088 = "();";
  protected final String TEXT_1089 = NL + "\t\t\t\tif (coreType) return ";
  protected final String TEXT_1090 = "();" + NL + "\t\t\t\treturn ((";
  protected final String TEXT_1091 = ".Internal)";
  protected final String TEXT_1092 = "()).getWrapper();";
  protected final String TEXT_1093 = NL + "\t\t\t\treturn ";
  protected final String TEXT_1094 = "();";
  protected final String TEXT_1095 = NL + "\t\t}";
  protected final String TEXT_1096 = NL + "\t\treturn super.eGet(featureID, resolve, coreType);";
  protected final String TEXT_1097 = NL + "\t\treturn eDynamicGet(featureID, resolve, coreType);";
  protected final String TEXT_1098 = NL + "\t}" + NL;
  protected final String TEXT_1099 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */";
  protected final String TEXT_1100 = NL + "\t@SuppressWarnings(\"unchecked\")";
  protected final String TEXT_1101 = NL + "\t@Override";
  protected final String TEXT_1102 = NL + "\tpublic void eSet(int featureID, Object newValue)" + NL + "\t{" + NL + "\t\tswitch (featureID)" + NL + "\t\t{";
  protected final String TEXT_1103 = NL + "\t\t\tcase ";
  protected final String TEXT_1104 = ":";
  protected final String TEXT_1105 = NL + "\t\t\t\t((";
  protected final String TEXT_1106 = ".Internal)((";
  protected final String TEXT_1107 = ".Internal.Wrapper)";
  protected final String TEXT_1108 = "()).featureMap()).set(newValue);";
  protected final String TEXT_1109 = NL + "\t\t\t\t((";
  protected final String TEXT_1110 = ".Internal)";
  protected final String TEXT_1111 = "()).set(newValue);";
  protected final String TEXT_1112 = NL + "\t\t\t\t((";
  protected final String TEXT_1113 = ".Setting)((";
  protected final String TEXT_1114 = ".InternalMapView)";
  protected final String TEXT_1115 = "()).eMap()).set(newValue);";
  protected final String TEXT_1116 = NL + "\t\t\t\t((";
  protected final String TEXT_1117 = ".Setting)";
  protected final String TEXT_1118 = "()).set(newValue);";
  protected final String TEXT_1119 = NL + "\t\t\t\t";
  protected final String TEXT_1120 = "().clear();" + NL + "\t\t\t\t";
  protected final String TEXT_1121 = "().addAll((";
  protected final String TEXT_1122 = "<? extends ";
  protected final String TEXT_1123 = ">";
  protected final String TEXT_1124 = ")newValue);";
  protected final String TEXT_1125 = NL + "\t\t\t\tset";
  protected final String TEXT_1126 = "(((";
  protected final String TEXT_1127 = ")newValue).";
  protected final String TEXT_1128 = "());";
  protected final String TEXT_1129 = NL + "\t\t\t\tset";
  protected final String TEXT_1130 = "(";
  protected final String TEXT_1131 = "(";
  protected final String TEXT_1132 = ")";
  protected final String TEXT_1133 = "newValue);";
  protected final String TEXT_1134 = NL + "\t\t\t\treturn;";
  protected final String TEXT_1135 = NL + "\t\t}";
  protected final String TEXT_1136 = NL + "\t\tsuper.eSet(featureID, newValue);";
  protected final String TEXT_1137 = NL + "\t\teDynamicSet(featureID, newValue);";
  protected final String TEXT_1138 = NL + "\t}" + NL + "" + NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */";
  protected final String TEXT_1139 = NL + "\t@Override";
  protected final String TEXT_1140 = NL + "\tpublic void eUnset(int featureID)" + NL + "\t{" + NL + "\t\tswitch (featureID)" + NL + "\t\t{";
  protected final String TEXT_1141 = NL + "\t\t\tcase ";
  protected final String TEXT_1142 = ":";
  protected final String TEXT_1143 = NL + "\t\t\t\t((";
  protected final String TEXT_1144 = ".Internal.Wrapper)";
  protected final String TEXT_1145 = "()).featureMap().clear();";
  protected final String TEXT_1146 = NL + "\t\t\t\t";
  protected final String TEXT_1147 = "().clear();";
  protected final String TEXT_1148 = NL + "\t\t\t\tunset";
  protected final String TEXT_1149 = "();";
  protected final String TEXT_1150 = NL + "\t\t\t\tset";
  protected final String TEXT_1151 = "((";
  protected final String TEXT_1152 = ")null);";
  protected final String TEXT_1153 = NL + "\t\t\t\tset";
  protected final String TEXT_1154 = "(";
  protected final String TEXT_1155 = ");";
  protected final String TEXT_1156 = NL + "\t\t\t\treturn;";
  protected final String TEXT_1157 = NL + "\t\t}";
  protected final String TEXT_1158 = NL + "\t\tsuper.eUnset(featureID);";
  protected final String TEXT_1159 = NL + "\t\teDynamicUnset(featureID);";
  protected final String TEXT_1160 = NL + "\t}" + NL;
  protected final String TEXT_1161 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */";
  protected final String TEXT_1162 = NL + "\t@Override";
  protected final String TEXT_1163 = NL + "\tpublic boolean eIsSet(int featureID)" + NL + "\t{" + NL + "\t\tswitch (featureID)" + NL + "\t\t{";
  protected final String TEXT_1164 = NL + "\t\t\tcase ";
  protected final String TEXT_1165 = ":";
  protected final String TEXT_1166 = NL + "\t\t\t\treturn !((";
  protected final String TEXT_1167 = ".Internal.Wrapper)";
  protected final String TEXT_1168 = "()).featureMap().isEmpty();";
  protected final String TEXT_1169 = NL + "\t\t\t\treturn ";
  protected final String TEXT_1170 = " != null && !";
  protected final String TEXT_1171 = ".featureMap().isEmpty();";
  protected final String TEXT_1172 = NL + "\t\t\t\treturn ";
  protected final String TEXT_1173 = " != null && !";
  protected final String TEXT_1174 = ".isEmpty();";
  protected final String TEXT_1175 = NL + "\t\t\t\t";
  protected final String TEXT_1176 = " ";
  protected final String TEXT_1177 = " = (";
  protected final String TEXT_1178 = ")eVirtualGet(";
  protected final String TEXT_1179 = ");" + NL + "\t\t\t\treturn ";
  protected final String TEXT_1180 = " != null && !";
  protected final String TEXT_1181 = ".isEmpty();";
  protected final String TEXT_1182 = NL + "\t\t\t\treturn !";
  protected final String TEXT_1183 = "().isEmpty();";
  protected final String TEXT_1184 = NL + "\t\t\t\treturn isSet";
  protected final String TEXT_1185 = "();";
  protected final String TEXT_1186 = NL + "\t\t\t\treturn ";
  protected final String TEXT_1187 = " != null;";
  protected final String TEXT_1188 = NL + "\t\t\t\treturn eVirtualGet(";
  protected final String TEXT_1189 = ") != null;";
  protected final String TEXT_1190 = NL + "\t\t\t\treturn basicGet";
  protected final String TEXT_1191 = "() != null;";
  protected final String TEXT_1192 = NL + "\t\t\t\treturn ";
  protected final String TEXT_1193 = " != null;";
  protected final String TEXT_1194 = NL + "\t\t\t\treturn eVirtualGet(";
  protected final String TEXT_1195 = ") != null;";
  protected final String TEXT_1196 = NL + "\t\t\t\treturn ";
  protected final String TEXT_1197 = "() != null;";
  protected final String TEXT_1198 = NL + "\t\t\t\treturn ((";
  protected final String TEXT_1199 = " & ";
  protected final String TEXT_1200 = "_EFLAG) != 0) != ";
  protected final String TEXT_1201 = ";";
  protected final String TEXT_1202 = NL + "\t\t\t\treturn ";
  protected final String TEXT_1203 = " != ";
  protected final String TEXT_1204 = ";";
  protected final String TEXT_1205 = NL + "\t\t\t\treturn eVirtualGet(";
  protected final String TEXT_1206 = ", ";
  protected final String TEXT_1207 = ") != ";
  protected final String TEXT_1208 = ";";
  protected final String TEXT_1209 = NL + "\t\t\t\treturn ";
  protected final String TEXT_1210 = "() != ";
  protected final String TEXT_1211 = ";";
  protected final String TEXT_1212 = NL + "\t\t\t\treturn ";
  protected final String TEXT_1213 = " == null ? ";
  protected final String TEXT_1214 = " != null : !";
  protected final String TEXT_1215 = ".equals(";
  protected final String TEXT_1216 = ");";
  protected final String TEXT_1217 = NL + "\t\t\t\t";
  protected final String TEXT_1218 = " ";
  protected final String TEXT_1219 = " = (";
  protected final String TEXT_1220 = ")eVirtualGet(";
  protected final String TEXT_1221 = ", ";
  protected final String TEXT_1222 = ");" + NL + "\t\t\t\treturn ";
  protected final String TEXT_1223 = " == null ? ";
  protected final String TEXT_1224 = " != null : !";
  protected final String TEXT_1225 = ".equals(";
  protected final String TEXT_1226 = ");";
  protected final String TEXT_1227 = NL + "\t\t\t\treturn ";
  protected final String TEXT_1228 = " == null ? ";
  protected final String TEXT_1229 = "() != null : !";
  protected final String TEXT_1230 = ".equals(";
  protected final String TEXT_1231 = "());";
  protected final String TEXT_1232 = NL + "\t\t}";
  protected final String TEXT_1233 = NL + "\t\treturn super.eIsSet(featureID);";
  protected final String TEXT_1234 = NL + "\t\treturn eDynamicIsSet(featureID);";
  protected final String TEXT_1235 = NL + "\t}" + NL;
  protected final String TEXT_1236 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */";
  protected final String TEXT_1237 = NL + "\t@Override";
  protected final String TEXT_1238 = NL + "\tpublic int eBaseStructuralFeatureID(int derivedFeatureID, Class baseClass)" + NL + "\t{";
  protected final String TEXT_1239 = NL + "\t\tif (baseClass == ";
  protected final String TEXT_1240 = ".class)" + NL + "\t\t{" + NL + "\t\t\tswitch (derivedFeatureID)" + NL + "\t\t\t{";
  protected final String TEXT_1241 = NL + "\t\t\t\tcase ";
  protected final String TEXT_1242 = ": return ";
  protected final String TEXT_1243 = ";";
  protected final String TEXT_1244 = NL + "\t\t\t\tdefault: return -1;" + NL + "\t\t\t}" + NL + "\t\t}";
  protected final String TEXT_1245 = NL + "\t\treturn super.eBaseStructuralFeatureID(derivedFeatureID, baseClass);" + NL + "\t}" + NL + "" + NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */";
  protected final String TEXT_1246 = NL + "\t@Override";
  protected final String TEXT_1247 = NL + "\tpublic int eDerivedStructuralFeatureID(int baseFeatureID, Class baseClass)" + NL + "\t{";
  protected final String TEXT_1248 = NL + "\t\tif (baseClass == ";
  protected final String TEXT_1249 = ".class)" + NL + "\t\t{" + NL + "\t\t\tswitch (baseFeatureID)" + NL + "\t\t\t{";
  protected final String TEXT_1250 = NL + "\t\t\t\tcase ";
  protected final String TEXT_1251 = ": return ";
  protected final String TEXT_1252 = ";";
  protected final String TEXT_1253 = NL + "\t\t\t\tdefault: return -1;" + NL + "\t\t\t}" + NL + "\t\t}";
  protected final String TEXT_1254 = NL + "\t\treturn super.eDerivedStructuralFeatureID(baseFeatureID, baseClass);" + NL + "\t}" + NL;
  protected final String TEXT_1255 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */";
  protected final String TEXT_1256 = NL + "\t@Override";
  protected final String TEXT_1257 = NL + "\tprotected Object[] eVirtualValues()" + NL + "\t{" + NL + "\t\treturn ";
  protected final String TEXT_1258 = ";" + NL + "\t}" + NL + "" + NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */";
  protected final String TEXT_1259 = NL + "\t@Override";
  protected final String TEXT_1260 = NL + "\tprotected void eSetVirtualValues(Object[] newValues)" + NL + "\t{" + NL + "\t\t";
  protected final String TEXT_1261 = " = newValues;" + NL + "\t}" + NL;
  protected final String TEXT_1262 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */";
  protected final String TEXT_1263 = NL + "\t@Override";
  protected final String TEXT_1264 = NL + "\tprotected int eVirtualIndexBits(int offset)" + NL + "\t{" + NL + "\t\tswitch (offset)" + NL + "\t\t{";
  protected final String TEXT_1265 = NL + "\t\t\tcase ";
  protected final String TEXT_1266 = " :" + NL + "\t\t\t\treturn ";
  protected final String TEXT_1267 = ";";
  protected final String TEXT_1268 = NL + "\t\t\tdefault :" + NL + "\t\t\t\tthrow new IndexOutOfBoundsException();" + NL + "\t\t}" + NL + "\t}" + NL + "" + NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */";
  protected final String TEXT_1269 = NL + "\t@Override";
  protected final String TEXT_1270 = NL + "\tprotected void eSetVirtualIndexBits(int offset, int newIndexBits)" + NL + "\t{" + NL + "\t\tswitch (offset)" + NL + "\t\t{";
  protected final String TEXT_1271 = NL + "\t\t\tcase ";
  protected final String TEXT_1272 = " :" + NL + "\t\t\t\t";
  protected final String TEXT_1273 = " = newIndexBits;" + NL + "\t\t\t\tbreak;";
  protected final String TEXT_1274 = NL + "\t\t\tdefault :" + NL + "\t\t\t\tthrow new IndexOutOfBoundsException();" + NL + "\t\t}" + NL + "\t}" + NL;
  protected final String TEXT_1275 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */";
  protected final String TEXT_1276 = NL + "\t@Override";
  protected final String TEXT_1277 = NL + "\tpublic String toString()" + NL + "\t{" + NL + "\t\tif (eIsProxy()) return super.toString();" + NL + "" + NL + "\t\tStringBuffer result = new StringBuffer(super.toString());";
  protected final String TEXT_1278 = NL + "\t\tresult.append(\" (";
  protected final String TEXT_1279 = ": \");";
  protected final String TEXT_1280 = NL + "\t\tresult.append(\", ";
  protected final String TEXT_1281 = ": \");";
  protected final String TEXT_1282 = NL + "\t\tif (eVirtualIsSet(";
  protected final String TEXT_1283 = ")) result.append(eVirtualGet(";
  protected final String TEXT_1284 = ")); else result.append(\"<unset>\");";
  protected final String TEXT_1285 = NL + "\t\tif (";
  protected final String TEXT_1286 = "(";
  protected final String TEXT_1287 = " & ";
  protected final String TEXT_1288 = "_ESETFLAG) != 0";
  protected final String TEXT_1289 = "ESet";
  protected final String TEXT_1290 = ") result.append((";
  protected final String TEXT_1291 = " & ";
  protected final String TEXT_1292 = "_EFLAG) != 0); else result.append(\"<unset>\");";
  protected final String TEXT_1293 = NL + "\t\tif (";
  protected final String TEXT_1294 = "(";
  protected final String TEXT_1295 = " & ";
  protected final String TEXT_1296 = "_ESETFLAG) != 0";
  protected final String TEXT_1297 = "ESet";
  protected final String TEXT_1298 = ") result.append(";
  protected final String TEXT_1299 = "); else result.append(\"<unset>\");";
  protected final String TEXT_1300 = NL + "\t\tresult.append(eVirtualGet(";
  protected final String TEXT_1301 = ", ";
  protected final String TEXT_1302 = "));";
  protected final String TEXT_1303 = NL + "\t\tresult.append((";
  protected final String TEXT_1304 = " & ";
  protected final String TEXT_1305 = "_EFLAG) != 0);";
  protected final String TEXT_1306 = NL + "\t\tresult.append(";
  protected final String TEXT_1307 = ");";
  protected final String TEXT_1308 = NL + "\t\tresult.append(')');" + NL + "\t\treturn result.toString();" + NL + "\t}" + NL;
  protected final String TEXT_1309 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */" + NL + "\tprotected int hash = -1;" + NL + "" + NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */" + NL + "\tpublic int getHash()" + NL + "\t{" + NL + "\t\tif (hash == -1)" + NL + "\t\t{" + NL + "\t\t\t";
  protected final String TEXT_1310 = " theKey = getKey();" + NL + "\t\t\thash = (theKey == null ? 0 : theKey.hashCode());" + NL + "\t\t}" + NL + "\t\treturn hash;" + NL + "\t}" + NL + "" + NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */" + NL + "\tpublic void setHash(int hash)" + NL + "\t{" + NL + "\t\tthis.hash = hash;" + NL + "\t}" + NL + "" + NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */" + NL + "\tpublic ";
  protected final String TEXT_1311 = " getKey()" + NL + "\t{";
  protected final String TEXT_1312 = NL + "\t\treturn new ";
  protected final String TEXT_1313 = "(getTypedKey());";
  protected final String TEXT_1314 = NL + "\t\treturn getTypedKey();";
  protected final String TEXT_1315 = NL + "\t}" + NL + "" + NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */" + NL + "\tpublic void setKey(";
  protected final String TEXT_1316 = " key)" + NL + "\t{";
  protected final String TEXT_1317 = NL + "\t\tgetTypedKey().addAll((";
  protected final String TEXT_1318 = ")key);";
  protected final String TEXT_1319 = NL + "\t\tsetTypedKey(key);";
  protected final String TEXT_1320 = NL + "\t\tsetTypedKey(((";
  protected final String TEXT_1321 = ")key).";
  protected final String TEXT_1322 = "());";
  protected final String TEXT_1323 = NL + "\t\tsetTypedKey((";
  protected final String TEXT_1324 = ")key);";
  protected final String TEXT_1325 = NL + "\t}" + NL + "" + NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */" + NL + "\tpublic ";
  protected final String TEXT_1326 = " getValue()" + NL + "\t{";
  protected final String TEXT_1327 = NL + "\t\treturn new ";
  protected final String TEXT_1328 = "(getTypedValue());";
  protected final String TEXT_1329 = NL + "\t\treturn getTypedValue();";
  protected final String TEXT_1330 = NL + "\t}" + NL + "" + NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */" + NL + "\tpublic ";
  protected final String TEXT_1331 = " setValue(";
  protected final String TEXT_1332 = " value)" + NL + "\t{" + NL + "\t\t";
  protected final String TEXT_1333 = " oldValue = getValue();";
  protected final String TEXT_1334 = NL + "\t\tgetTypedValue().clear();" + NL + "\t\tgetTypedValue().addAll((";
  protected final String TEXT_1335 = ")value);";
  protected final String TEXT_1336 = NL + "\t\tsetTypedValue(value);";
  protected final String TEXT_1337 = NL + "\t\tsetTypedValue(((";
  protected final String TEXT_1338 = ")value).";
  protected final String TEXT_1339 = "());";
  protected final String TEXT_1340 = NL + "\t\tsetTypedValue((";
  protected final String TEXT_1341 = ")value);";
  protected final String TEXT_1342 = NL + "\t\treturn oldValue;" + NL + "\t}" + NL + "" + NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */";
  protected final String TEXT_1343 = NL + "\t@SuppressWarnings(\"unchecked\")";
  protected final String TEXT_1344 = NL + "\tpublic ";
  protected final String TEXT_1345 = " getEMap()" + NL + "\t{" + NL + "\t\t";
  protected final String TEXT_1346 = " container = eContainer();" + NL + "\t\treturn container == null ? null : (";
  protected final String TEXT_1347 = ")container.eGet(eContainmentFeature());" + NL + "\t}" + NL;
  protected final String TEXT_1348 = NL + "} //";
  protected final String TEXT_1349 = NL;

  public String generate(Object argument)
  {
    final StringBuffer stringBuffer = new StringBuffer();
    
/**
 * <copyright>
 *
 * Copyright (c) 2002-2006 IBM Corporation and others.
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

    final GenClass genClass = (GenClass)((Object[])argument)[0]; final GenPackage genPackage = genClass.getGenPackage(); final GenModel genModel=genPackage.getGenModel();
    final boolean isInterface = Boolean.TRUE.equals(((Object[])argument)[1]); final boolean isImplementation = Boolean.TRUE.equals(((Object[])argument)[2]);
    final String publicStaticFinalFlag = isImplementation ? "public static final " : "";
    final String singleWildcard = genModel.getComplianceLevel().getValue() >= GenJDKLevel.JDK50 ? "<?>" : "";
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
    stringBuffer.append(genClass.getTypeParameters().trim());
    stringBuffer.append(genClass.getClassExtends());
    stringBuffer.append(genClass.getClassImplements());
    } else {
    stringBuffer.append(TEXT_43);
    stringBuffer.append(genClass.getInterfaceName());
    stringBuffer.append(genClass.getTypeParameters().trim());
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
    if (genFeature.hasEDefault() && (!genFeature.isVolatile() || !genModel.isReflectiveDelegation() && (!genFeature.hasDelegateFeature() || !genFeature.isUnsettable()))) {
    stringBuffer.append(TEXT_74);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_75);
    stringBuffer.append(genFeature.getFormattedName());
    stringBuffer.append(TEXT_76);
    stringBuffer.append(genFeature.getFeatureKind());
    stringBuffer.append(TEXT_77);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_78);
    stringBuffer.append(genFeature.getRawImportedType());
    stringBuffer.append(TEXT_79);
    stringBuffer.append(genFeature.getEDefault());
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
    if (genFeature.hasEDefault()) {
    stringBuffer.append(TEXT_98);
    stringBuffer.append(genFeature.getEDefault());
    }
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
    if (genModel.useClassOverrideAnnotation()) {
    stringBuffer.append(TEXT_117);
    }
    stringBuffer.append(TEXT_118);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.EClass"));
    stringBuffer.append(TEXT_119);
    stringBuffer.append(genClass.getQualifiedClassifierAccessor());
    stringBuffer.append(TEXT_120);
    }
    new Runnable() { public void run() {
    for (Iterator i=(isImplementation ? genClass.getImplementedGenFeatures() : genClass.getDeclaredGenFeatures()).iterator(); i.hasNext();) { GenFeature genFeature = (GenFeature)i.next();
    if (genModel.isArrayAccessors() && genFeature.isListType() && !genFeature.isFeatureMapType() && !genFeature.isMapType()) {
    stringBuffer.append(TEXT_121);
    if (!isImplementation) {
    stringBuffer.append(TEXT_122);
    stringBuffer.append(genFeature.getListItemType());
    stringBuffer.append(TEXT_123);
    stringBuffer.append(genFeature.getGetArrayAccessor());
    stringBuffer.append(TEXT_124);
    } else {
    stringBuffer.append(TEXT_125);
    stringBuffer.append(genFeature.getListItemType());
    stringBuffer.append(TEXT_126);
    stringBuffer.append(genFeature.getGetArrayAccessor());
    stringBuffer.append(TEXT_127);
    if (genFeature.isVolatile()) {
    stringBuffer.append(TEXT_128);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.util.BasicEList"));
    stringBuffer.append(TEXT_129);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.util.BasicEList"));
    stringBuffer.append(TEXT_130);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_131);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_132);
    } else {
    stringBuffer.append(TEXT_133);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_134);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_135);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_136);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.util.BasicEList"));
    stringBuffer.append(TEXT_137);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.util.BasicEList"));
    stringBuffer.append(TEXT_138);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_139);
    }
    stringBuffer.append(TEXT_140);
    stringBuffer.append(genFeature.getListItemType());
    stringBuffer.append(TEXT_141);
    }
    stringBuffer.append(TEXT_142);
    if (!isImplementation) {
    stringBuffer.append(TEXT_143);
    stringBuffer.append(genFeature.getListItemType());
    stringBuffer.append(TEXT_144);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_145);
    } else {
    stringBuffer.append(TEXT_146);
    stringBuffer.append(genFeature.getListItemType());
    stringBuffer.append(TEXT_147);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_148);
    stringBuffer.append(genFeature.getListItemType());
    stringBuffer.append(TEXT_149);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_150);
    }
    stringBuffer.append(TEXT_151);
    if (!isImplementation) {
    stringBuffer.append(TEXT_152);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_153);
    } else {
    stringBuffer.append(TEXT_154);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_155);
    if (genFeature.isVolatile()) {
    stringBuffer.append(TEXT_156);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_157);
    } else {
    stringBuffer.append(TEXT_158);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_159);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_160);
    }
    stringBuffer.append(TEXT_161);
    }
    stringBuffer.append(TEXT_162);
    if (!isImplementation) {
    stringBuffer.append(TEXT_163);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_164);
    stringBuffer.append(genFeature.getListItemType());
    stringBuffer.append(TEXT_165);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_166);
    } else {
    stringBuffer.append(TEXT_167);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_168);
    stringBuffer.append(genFeature.getListItemType());
    stringBuffer.append(TEXT_169);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_170);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.util.BasicEList"));
    stringBuffer.append(TEXT_171);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_172);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_173);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_174);
    }
    stringBuffer.append(TEXT_175);
    if (!isImplementation) {
    stringBuffer.append(TEXT_176);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_177);
    stringBuffer.append(genFeature.getListItemType());
    stringBuffer.append(TEXT_178);
    } else {
    stringBuffer.append(TEXT_179);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_180);
    stringBuffer.append(genFeature.getListItemType());
    stringBuffer.append(TEXT_181);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_182);
    }
    }
    if (genFeature.isGet() && (isImplementation || !genFeature.isSuppressedGetVisibility())) {
    if (isInterface) {
    stringBuffer.append(TEXT_183);
    stringBuffer.append(genFeature.getFormattedName());
    stringBuffer.append(TEXT_184);
    stringBuffer.append(genFeature.getFeatureKind());
    stringBuffer.append(TEXT_185);
    if (genFeature.isListType()) {
    if (genFeature.isMapType()) { GenFeature keyFeature = genFeature.getMapEntryTypeGenClass().getMapEntryKeyFeature(); GenFeature valueFeature = genFeature.getMapEntryTypeGenClass().getMapEntryValueFeature(); 
    stringBuffer.append(TEXT_186);
    if (keyFeature.isListType()) {
    stringBuffer.append(TEXT_187);
    stringBuffer.append(keyFeature.getQualifiedListItemType());
    stringBuffer.append(TEXT_188);
    } else {
    stringBuffer.append(TEXT_189);
    stringBuffer.append(keyFeature.getType());
    stringBuffer.append(TEXT_190);
    }
    stringBuffer.append(TEXT_191);
    if (valueFeature.isListType()) {
    stringBuffer.append(TEXT_192);
    stringBuffer.append(valueFeature.getQualifiedListItemType());
    stringBuffer.append(TEXT_193);
    } else {
    stringBuffer.append(TEXT_194);
    stringBuffer.append(valueFeature.getType());
    stringBuffer.append(TEXT_195);
    }
    stringBuffer.append(TEXT_196);
    } else if (!genFeature.isWrappedFeatureMapType() && !(genModel.isSuppressEMFMetaData() && "org.eclipse.emf.ecore.EObject".equals(genFeature.getQualifiedListItemType()))) {
    stringBuffer.append(TEXT_197);
    stringBuffer.append(genFeature.getQualifiedListItemType());
    stringBuffer.append(TEXT_198);
    }
    } else if (genFeature.isSetDefaultValue()) {
    stringBuffer.append(TEXT_199);
    stringBuffer.append(genFeature.getDefaultValue());
    stringBuffer.append(TEXT_200);
    }
    if (genFeature.getTypeGenEnum() != null) {
    stringBuffer.append(TEXT_201);
    stringBuffer.append(genFeature.getTypeGenEnum().getQualifiedName());
    stringBuffer.append(TEXT_202);
    }
    if (genFeature.isBidirectional() && !genFeature.getReverse().getGenClass().isMapEntry()) { GenFeature reverseGenFeature = genFeature.getReverse(); 
    if (!reverseGenFeature.isSuppressedGetVisibility()) {
    stringBuffer.append(TEXT_203);
    stringBuffer.append(reverseGenFeature.getGenClass().getQualifiedInterfaceName());
    stringBuffer.append(TEXT_204);
    stringBuffer.append(reverseGenFeature.getGetAccessor());
    stringBuffer.append(TEXT_205);
    stringBuffer.append(reverseGenFeature.getFormattedName());
    stringBuffer.append(TEXT_206);
    }
    }
    stringBuffer.append(TEXT_207);
    if (!genFeature.hasDocumentation()) {
    stringBuffer.append(TEXT_208);
    stringBuffer.append(genFeature.getFormattedName());
    stringBuffer.append(TEXT_209);
    stringBuffer.append(genFeature.getFeatureKind());
    stringBuffer.append(TEXT_210);
    }
    stringBuffer.append(TEXT_211);
    if (genFeature.hasDocumentation()) {
    stringBuffer.append(TEXT_212);
    stringBuffer.append(genFeature.getDocumentation(genModel.getIndentation(stringBuffer)));
    stringBuffer.append(TEXT_213);
    }
    stringBuffer.append(TEXT_214);
    stringBuffer.append(genFeature.getFormattedName());
    stringBuffer.append(TEXT_215);
    stringBuffer.append(genFeature.getFeatureKind());
    stringBuffer.append(TEXT_216);
    if (genFeature.getTypeGenEnum() != null) {
    stringBuffer.append(TEXT_217);
    stringBuffer.append(genFeature.getTypeGenEnum().getQualifiedName());
    }
    if (genFeature.isUnsettable()) {
    if (!genFeature.isSuppressedIsSetVisibility()) {
    stringBuffer.append(TEXT_218);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_219);
    }
    if (genFeature.isChangeable() && !genFeature.isSuppressedUnsetVisibility()) {
    stringBuffer.append(TEXT_220);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_221);
    }
    }
    if (genFeature.isChangeable() && !genFeature.isListType() && !genFeature.isSuppressedSetVisibility()) {
    stringBuffer.append(TEXT_222);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_223);
    stringBuffer.append(genFeature.getImportedType());
    stringBuffer.append(TEXT_224);
    }
    if (!genModel.isSuppressEMFMetaData()) {
    stringBuffer.append(TEXT_225);
    stringBuffer.append(genPackage.getQualifiedPackageInterfaceName());
    stringBuffer.append(TEXT_226);
    stringBuffer.append(genFeature.getFeatureAccessorName());
    stringBuffer.append(TEXT_227);
    }
    if (genFeature.isBidirectional() && !genFeature.getReverse().getGenClass().isMapEntry()) { GenFeature reverseGenFeature = genFeature.getReverse(); 
    if (!reverseGenFeature.isSuppressedGetVisibility()) {
    stringBuffer.append(TEXT_228);
    stringBuffer.append(reverseGenFeature.getGenClass().getQualifiedInterfaceName());
    stringBuffer.append(TEXT_229);
    stringBuffer.append(reverseGenFeature.getGetAccessor());
    }
    }
    if (!genModel.isSuppressEMFModelTags()) { boolean first = true; for (StringTokenizer stringTokenizer = new StringTokenizer(genFeature.getModelInfo(), "\n\r"); stringTokenizer.hasMoreTokens(); ) { String modelInfo = stringTokenizer.nextToken(); if (first) { first = false;
    stringBuffer.append(TEXT_230);
    stringBuffer.append(modelInfo);
    } else {
    stringBuffer.append(TEXT_231);
    stringBuffer.append(modelInfo);
    }} if (first) {
    stringBuffer.append(TEXT_232);
    }}
    stringBuffer.append(TEXT_233);
    } else {
    stringBuffer.append(TEXT_234);
    }
    if (!isImplementation) {
    stringBuffer.append(TEXT_235);
    stringBuffer.append(genFeature.getImportedType());
    stringBuffer.append(TEXT_236);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_237);
    } else {
    stringBuffer.append(TEXT_238);
    stringBuffer.append(genFeature.getImportedType());
    stringBuffer.append(TEXT_239);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_240);
    if (genModel.isReflectiveDelegation()) {
    stringBuffer.append(TEXT_241);
    if (genFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_242);
    }
    stringBuffer.append(TEXT_243);
    stringBuffer.append(genFeature.getObjectType());
    stringBuffer.append(TEXT_244);
    stringBuffer.append(genFeature.getQualifiedFeatureAccessor());
    stringBuffer.append(TEXT_245);
    if (genFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_246);
    stringBuffer.append(genFeature.getPrimitiveValueFunction());
    stringBuffer.append(TEXT_247);
    }
    stringBuffer.append(TEXT_248);
    } else if (!genFeature.isVolatile()) {
    if (genFeature.isListType()) {
    if (genModel.isVirtualDelegation()) {
    stringBuffer.append(TEXT_249);
    stringBuffer.append(genFeature.getImportedType());
    stringBuffer.append(TEXT_250);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_251);
    stringBuffer.append(genFeature.getImportedType());
    stringBuffer.append(TEXT_252);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(TEXT_253);
    }
    stringBuffer.append(TEXT_254);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_255);
    if (genModel.isVirtualDelegation()) {
    stringBuffer.append(TEXT_256);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(TEXT_257);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_258);
    stringBuffer.append(genClass.getListConstructor(genFeature));
    stringBuffer.append(TEXT_259);
    } else {
    stringBuffer.append(TEXT_260);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_261);
    stringBuffer.append(genClass.getListConstructor(genFeature));
    stringBuffer.append(TEXT_262);
    }
    stringBuffer.append(TEXT_263);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(genFeature.isMapType() && genFeature.isEffectiveSuppressEMFTypes() ? ".map()" : "");
    stringBuffer.append(TEXT_264);
    } else if (genFeature.isContainer()) {
    stringBuffer.append(TEXT_265);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(TEXT_266);
    stringBuffer.append(genFeature.getImportedType());
    stringBuffer.append(TEXT_267);
    } else {
    if (genFeature.isResolveProxies()) {
    if (genModel.isVirtualDelegation()) {
    stringBuffer.append(TEXT_268);
    stringBuffer.append(genFeature.getImportedType());
    stringBuffer.append(TEXT_269);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_270);
    stringBuffer.append(genFeature.getImportedType());
    stringBuffer.append(TEXT_271);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    if (genFeature.hasEDefault()) {
    stringBuffer.append(TEXT_272);
    stringBuffer.append(genFeature.getEDefault());
    }
    stringBuffer.append(TEXT_273);
    }
    stringBuffer.append(TEXT_274);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_275);
    stringBuffer.append(genFeature.getSafeNameAsEObject());
    stringBuffer.append(TEXT_276);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.InternalEObject"));
    stringBuffer.append(TEXT_277);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_278);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.InternalEObject"));
    stringBuffer.append(TEXT_279);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_280);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_281);
    stringBuffer.append(genFeature.getNonEObjectInternalTypeCast());
    stringBuffer.append(TEXT_282);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_283);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_284);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_285);
    if (genFeature.isEffectiveContains()) {
    stringBuffer.append(TEXT_286);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.InternalEObject"));
    stringBuffer.append(TEXT_287);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_288);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.InternalEObject"));
    stringBuffer.append(TEXT_289);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_290);
    if (!genFeature.isBidirectional()) {
    stringBuffer.append(TEXT_291);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.notify.NotificationChain"));
    stringBuffer.append(TEXT_292);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_293);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(TEXT_294);
    } else { GenFeature reverseFeature = genFeature.getReverse(); GenClass targetClass = reverseFeature.getGenClass();
    stringBuffer.append(TEXT_295);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.notify.NotificationChain"));
    stringBuffer.append(TEXT_296);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_297);
    stringBuffer.append(targetClass.getQualifiedFeatureID(reverseFeature));
    stringBuffer.append(TEXT_298);
    stringBuffer.append(targetClass.getImportedInterfaceName());
    stringBuffer.append(TEXT_299);
    }
    stringBuffer.append(TEXT_300);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_301);
    if (!genFeature.isBidirectional()) {
    stringBuffer.append(TEXT_302);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_303);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(TEXT_304);
    } else { GenFeature reverseFeature = genFeature.getReverse(); GenClass targetClass = reverseFeature.getGenClass();
    stringBuffer.append(TEXT_305);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_306);
    stringBuffer.append(targetClass.getQualifiedFeatureID(reverseFeature));
    stringBuffer.append(TEXT_307);
    stringBuffer.append(targetClass.getImportedInterfaceName());
    stringBuffer.append(TEXT_308);
    }
    stringBuffer.append(TEXT_309);
    } else if (genModel.isVirtualDelegation()) {
    stringBuffer.append(TEXT_310);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(TEXT_311);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_312);
    }
    if (!genModel.isSuppressNotification()) {
    stringBuffer.append(TEXT_313);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.impl.ENotificationImpl"));
    stringBuffer.append(TEXT_314);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.notify.Notification"));
    stringBuffer.append(TEXT_315);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(TEXT_316);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_317);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_318);
    }
    stringBuffer.append(TEXT_319);
    }
    if (!genFeature.isResolveProxies() && genModel.isVirtualDelegation() && !genFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_320);
    stringBuffer.append(genFeature.getImportedType());
    stringBuffer.append(TEXT_321);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    if (genFeature.hasEDefault()) {
    stringBuffer.append(TEXT_322);
    stringBuffer.append(genFeature.getEDefault());
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
    String featureMapEntryTemplateArgument = genModel.getComplianceLevel().getValue() >= GenJDKLevel.JDK50 ? "<" + genModel.getImportedName("org.eclipse.emf.ecore.util.FeatureMap") + ".Entry>" : "";
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
    stringBuffer.append(featureMapEntryTemplateArgument);
    stringBuffer.append(TEXT_344);
    stringBuffer.append(genFeature.getQualifiedFeatureAccessor());
    stringBuffer.append(TEXT_345);
    } else {
    stringBuffer.append(TEXT_346);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.FeatureMap"));
    stringBuffer.append(TEXT_347);
    stringBuffer.append(delegateFeature.getAccessorName());
    stringBuffer.append(TEXT_348);
    stringBuffer.append(featureMapEntryTemplateArgument);
    stringBuffer.append(TEXT_349);
    stringBuffer.append(genFeature.getQualifiedFeatureAccessor());
    stringBuffer.append(TEXT_350);
    }
    } else if (genFeature.isListType()) {
    if (delegateFeature.isWrappedFeatureMapType()) {
    stringBuffer.append(TEXT_351);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.FeatureMap"));
    stringBuffer.append(TEXT_352);
    stringBuffer.append(delegateFeature.getAccessorName());
    stringBuffer.append(TEXT_353);
    stringBuffer.append(genFeature.getQualifiedFeatureAccessor());
    stringBuffer.append(TEXT_354);
    } else {
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
    if (genFeature.isListType()) {
    stringBuffer.append(TEXT_380);
    if (genFeature.isMapType()) {
    stringBuffer.append(TEXT_381);
    } else if (genFeature.isFeatureMapType()) {
    stringBuffer.append(TEXT_382);
    } else {
    stringBuffer.append(TEXT_383);
    }
    stringBuffer.append(TEXT_384);
    }
    stringBuffer.append(TEXT_385);
    //Class/getGenFeature.todo.override.javajetinc
    }
    }
    stringBuffer.append(TEXT_386);
    }
    //Class/getGenFeature.override.javajetinc
    }
    if (isImplementation && !genModel.isReflectiveDelegation() && genFeature.isBasicGet()) {
    stringBuffer.append(TEXT_387);
    stringBuffer.append(genFeature.getImportedType());
    stringBuffer.append(TEXT_388);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_389);
    if (genFeature.isContainer()) {
    stringBuffer.append(TEXT_390);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(TEXT_391);
    stringBuffer.append(genFeature.getImportedType());
    stringBuffer.append(TEXT_392);
    } else if (!genFeature.isVolatile()) {
    if (genModel.isVirtualDelegation()) {
    stringBuffer.append(TEXT_393);
    stringBuffer.append(genFeature.getImportedType());
    stringBuffer.append(TEXT_394);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(TEXT_395);
    } else {
    stringBuffer.append(TEXT_396);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_397);
    }
    } else if (genFeature.hasDelegateFeature()) { GenFeature delegateFeature = genFeature.getDelegateFeature();
    if (delegateFeature.isWrappedFeatureMapType()) {
    stringBuffer.append(TEXT_398);
    stringBuffer.append(genFeature.getImportedType());
    stringBuffer.append(TEXT_399);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.FeatureMap"));
    stringBuffer.append(TEXT_400);
    stringBuffer.append(delegateFeature.getAccessorName());
    stringBuffer.append(TEXT_401);
    stringBuffer.append(genFeature.getQualifiedFeatureAccessor());
    stringBuffer.append(TEXT_402);
    } else {
    stringBuffer.append(TEXT_403);
    stringBuffer.append(genFeature.getImportedType());
    stringBuffer.append(TEXT_404);
    stringBuffer.append(delegateFeature.getAccessorName());
    stringBuffer.append(TEXT_405);
    stringBuffer.append(genFeature.getQualifiedFeatureAccessor());
    stringBuffer.append(TEXT_406);
    }
    } else {
    stringBuffer.append(TEXT_407);
    stringBuffer.append(genFeature.getFormattedName());
    stringBuffer.append(TEXT_408);
    stringBuffer.append(genFeature.getFeatureKind());
    stringBuffer.append(TEXT_409);
    //Class/basicGetGenFeature.todo.override.javajetinc
    }
    stringBuffer.append(TEXT_410);
    //Class/basicGetGenFeature.override.javajetinc
    }
    if (isImplementation && !genModel.isReflectiveDelegation() && genFeature.isBasicSet()) {
    stringBuffer.append(TEXT_411);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.notify.NotificationChain"));
    stringBuffer.append(TEXT_412);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_413);
    stringBuffer.append(genFeature.getImportedInternalType());
    stringBuffer.append(TEXT_414);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_415);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.notify.NotificationChain"));
    stringBuffer.append(TEXT_416);
    if (genFeature.isContainer()) {
    stringBuffer.append(TEXT_417);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.InternalEObject"));
    stringBuffer.append(TEXT_418);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_419);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(TEXT_420);
    stringBuffer.append(TEXT_421);
    } else if (!genFeature.isVolatile()) {
    if (genModel.isVirtualDelegation()) {
    stringBuffer.append(TEXT_422);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_423);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(TEXT_424);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_425);
    } else {
    stringBuffer.append(TEXT_426);
    stringBuffer.append(genFeature.getImportedType());
    stringBuffer.append(TEXT_427);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_428);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_429);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_430);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_431);
    }
    if (genFeature.isUnsettable()) {
    if (genModel.isVirtualDelegation()) {
    stringBuffer.append(TEXT_432);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_433);
    } else if (genClass.isESetFlag(genFeature)) {
    stringBuffer.append(TEXT_434);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_435);
    stringBuffer.append(genClass.getESetFlagsField(genFeature));
    stringBuffer.append(TEXT_436);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_437);
    stringBuffer.append(genClass.getESetFlagsField(genFeature));
    stringBuffer.append(TEXT_438);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_439);
    } else {
    stringBuffer.append(TEXT_440);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_441);
    stringBuffer.append(genFeature.getUncapName());
    stringBuffer.append(TEXT_442);
    stringBuffer.append(genFeature.getUncapName());
    stringBuffer.append(TEXT_443);
    }
    }
    if (!genModel.isSuppressNotification()) {
    stringBuffer.append(TEXT_444);
    if (genFeature.isUnsettable()) {
    stringBuffer.append(TEXT_445);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.impl.ENotificationImpl"));
    stringBuffer.append(TEXT_446);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.impl.ENotificationImpl"));
    stringBuffer.append(TEXT_447);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.notify.Notification"));
    stringBuffer.append(TEXT_448);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(TEXT_449);
    if (genModel.isVirtualDelegation()) {
    stringBuffer.append(TEXT_450);
    stringBuffer.append(genFeature.getCapName());
    } else {
    stringBuffer.append(TEXT_451);
    stringBuffer.append(genFeature.getCapName());
    }
    stringBuffer.append(TEXT_452);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_453);
    if (genModel.isVirtualDelegation()) {
    stringBuffer.append(TEXT_454);
    } else {
    stringBuffer.append(TEXT_455);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_456);
    }
    stringBuffer.append(TEXT_457);
    } else {
    stringBuffer.append(TEXT_458);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.impl.ENotificationImpl"));
    stringBuffer.append(TEXT_459);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.impl.ENotificationImpl"));
    stringBuffer.append(TEXT_460);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.notify.Notification"));
    stringBuffer.append(TEXT_461);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(TEXT_462);
    if (genModel.isVirtualDelegation()) {
    stringBuffer.append(TEXT_463);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_464);
    stringBuffer.append(genFeature.getCapName());
    } else {
    stringBuffer.append(TEXT_465);
    stringBuffer.append(genFeature.getCapName());
    }
    stringBuffer.append(TEXT_466);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_467);
    }
    stringBuffer.append(TEXT_468);
    }
    stringBuffer.append(TEXT_469);
    } else if (genFeature.hasDelegateFeature()) { GenFeature delegateFeature = genFeature.getDelegateFeature();
    if (delegateFeature.isWrappedFeatureMapType()) {
    stringBuffer.append(TEXT_470);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.FeatureMap"));
    stringBuffer.append(TEXT_471);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.FeatureMap"));
    stringBuffer.append(TEXT_472);
    stringBuffer.append(delegateFeature.getAccessorName());
    stringBuffer.append(TEXT_473);
    stringBuffer.append(genFeature.getQualifiedFeatureAccessor());
    stringBuffer.append(TEXT_474);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_475);
    } else {
    stringBuffer.append(TEXT_476);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.FeatureMap"));
    stringBuffer.append(TEXT_477);
    stringBuffer.append(delegateFeature.getAccessorName());
    stringBuffer.append(TEXT_478);
    stringBuffer.append(genFeature.getQualifiedFeatureAccessor());
    stringBuffer.append(TEXT_479);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_480);
    }
    } else {
    stringBuffer.append(TEXT_481);
    stringBuffer.append(genFeature.getFormattedName());
    stringBuffer.append(TEXT_482);
    stringBuffer.append(genFeature.getFeatureKind());
    stringBuffer.append(TEXT_483);
    //Class/basicSetGenFeature.todo.override.javajetinc
    }
    stringBuffer.append(TEXT_484);
    //Class/basicSetGenFeature.override.javajetinc
    }
    if (genFeature.isSet() && (isImplementation || !genFeature.isSuppressedSetVisibility())) {
    if (isInterface) { 
    stringBuffer.append(TEXT_485);
    stringBuffer.append(genClass.getQualifiedInterfaceName());
    stringBuffer.append(TEXT_486);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_487);
    stringBuffer.append(genFeature.getFormattedName());
    stringBuffer.append(TEXT_488);
    stringBuffer.append(genFeature.getFeatureKind());
    stringBuffer.append(TEXT_489);
    stringBuffer.append(genFeature.getFormattedName());
    stringBuffer.append(TEXT_490);
    stringBuffer.append(genFeature.getFeatureKind());
    stringBuffer.append(TEXT_491);
    if (genFeature.isEnumType()) {
    stringBuffer.append(TEXT_492);
    stringBuffer.append(genFeature.getTypeGenEnum().getQualifiedName());
    }
    if (genFeature.isUnsettable()) {
    if (!genFeature.isSuppressedIsSetVisibility()) {
    stringBuffer.append(TEXT_493);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_494);
    }
    if (!genFeature.isSuppressedUnsetVisibility()) {
    stringBuffer.append(TEXT_495);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_496);
    }
    }
    stringBuffer.append(TEXT_497);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_498);
    } else {
    stringBuffer.append(TEXT_499);
    }
    if (!isImplementation) { 
    stringBuffer.append(TEXT_500);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_501);
    stringBuffer.append(genFeature.getImportedType());
    stringBuffer.append(TEXT_502);
    } else {
    stringBuffer.append(TEXT_503);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_504);
    stringBuffer.append(genFeature.getImportedType());
    stringBuffer.append(TEXT_505);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_506);
    if (genModel.isReflectiveDelegation()) {
    stringBuffer.append(TEXT_507);
    stringBuffer.append(genFeature.getQualifiedFeatureAccessor());
    stringBuffer.append(TEXT_508);
    if (genFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_509);
    stringBuffer.append(genFeature.getObjectType());
    stringBuffer.append(TEXT_510);
    }
    stringBuffer.append(TEXT_511);
    stringBuffer.append(genFeature.getCapName());
    if (genFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_512);
    }
    stringBuffer.append(TEXT_513);
    } else if (!genFeature.isVolatile()) {
    if (genFeature.isContainer()) { GenFeature reverseFeature = genFeature.getReverse(); GenClass targetClass = reverseFeature.getGenClass();
    stringBuffer.append(TEXT_514);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_515);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(TEXT_516);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_517);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.EcoreUtil"));
    stringBuffer.append(TEXT_518);
    stringBuffer.append(genFeature.getEObjectCast());
    stringBuffer.append(TEXT_519);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_520);
    stringBuffer.append(genModel.getImportedName("java.lang.IllegalArgumentException"));
    stringBuffer.append(TEXT_521);
    stringBuffer.append(genModel.getNonNLS());
    stringBuffer.append(TEXT_522);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.notify.NotificationChain"));
    stringBuffer.append(TEXT_523);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_524);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.InternalEObject"));
    stringBuffer.append(TEXT_525);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_526);
    stringBuffer.append(targetClass.getQualifiedFeatureID(reverseFeature));
    stringBuffer.append(TEXT_527);
    stringBuffer.append(targetClass.getImportedInterfaceName());
    stringBuffer.append(TEXT_528);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_529);
    stringBuffer.append(genFeature.getInternalTypeCast());
    stringBuffer.append(TEXT_530);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_531);
    if (!genModel.isSuppressNotification()) {
    stringBuffer.append(TEXT_532);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.impl.ENotificationImpl"));
    stringBuffer.append(TEXT_533);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.notify.Notification"));
    stringBuffer.append(TEXT_534);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(TEXT_535);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_536);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_537);
    }
    } else if (genFeature.isBidirectional() || genFeature.isEffectiveContains()) {
    if (genModel.isVirtualDelegation()) {
    stringBuffer.append(TEXT_538);
    stringBuffer.append(genFeature.getImportedType());
    stringBuffer.append(TEXT_539);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_540);
    stringBuffer.append(genFeature.getImportedType());
    stringBuffer.append(TEXT_541);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(TEXT_542);
    }
    stringBuffer.append(TEXT_543);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_544);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_545);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.notify.NotificationChain"));
    stringBuffer.append(TEXT_546);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_547);
    if (!genFeature.isBidirectional()) {
    stringBuffer.append(TEXT_548);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.InternalEObject"));
    stringBuffer.append(TEXT_549);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_550);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(TEXT_551);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_552);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.InternalEObject"));
    stringBuffer.append(TEXT_553);
    stringBuffer.append(genFeature.getCapName());
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
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_561);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.InternalEObject"));
    stringBuffer.append(TEXT_562);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_563);
    stringBuffer.append(targetClass.getQualifiedFeatureID(reverseFeature));
    stringBuffer.append(TEXT_564);
    stringBuffer.append(targetClass.getImportedInterfaceName());
    stringBuffer.append(TEXT_565);
    }
    stringBuffer.append(TEXT_566);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_567);
    stringBuffer.append(genFeature.getInternalTypeCast());
    stringBuffer.append(TEXT_568);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_569);
    if (genFeature.isUnsettable()) {
    stringBuffer.append(TEXT_570);
    if (genModel.isVirtualDelegation()) {
    stringBuffer.append(TEXT_571);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_572);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(TEXT_573);
    } else if (genClass.isESetFlag(genFeature)) {
    if (!genModel.isSuppressNotification()) {
    stringBuffer.append(TEXT_574);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_575);
    stringBuffer.append(genClass.getESetFlagsField(genFeature));
    stringBuffer.append(TEXT_576);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_577);
    }
    stringBuffer.append(TEXT_578);
    stringBuffer.append(genClass.getESetFlagsField(genFeature));
    stringBuffer.append(TEXT_579);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_580);
    } else {
    if (!genModel.isSuppressNotification()) {
    stringBuffer.append(TEXT_581);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_582);
    stringBuffer.append(genFeature.getUncapName());
    stringBuffer.append(TEXT_583);
    }
    stringBuffer.append(TEXT_584);
    stringBuffer.append(genFeature.getUncapName());
    stringBuffer.append(TEXT_585);
    }
    if (!genModel.isSuppressNotification()) {
    stringBuffer.append(TEXT_586);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.impl.ENotificationImpl"));
    stringBuffer.append(TEXT_587);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.notify.Notification"));
    stringBuffer.append(TEXT_588);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(TEXT_589);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_590);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_591);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_592);
    }
    stringBuffer.append(TEXT_593);
    } else {
    if (!genModel.isSuppressNotification()) {
    stringBuffer.append(TEXT_594);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.impl.ENotificationImpl"));
    stringBuffer.append(TEXT_595);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.notify.Notification"));
    stringBuffer.append(TEXT_596);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(TEXT_597);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_598);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_599);
    }
    }
    } else {
    if (genClass.isFlag(genFeature)) {
    if (!genModel.isSuppressNotification()) {
    stringBuffer.append(TEXT_600);
    stringBuffer.append(genFeature.getImportedType());
    stringBuffer.append(TEXT_601);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_602);
    stringBuffer.append(genClass.getFlagsField(genFeature));
    stringBuffer.append(TEXT_603);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_604);
    }
    stringBuffer.append(TEXT_605);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_606);
    stringBuffer.append(genClass.getFlagsField(genFeature));
    stringBuffer.append(TEXT_607);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_608);
    stringBuffer.append(genClass.getFlagsField(genFeature));
    stringBuffer.append(TEXT_609);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_610);
    } else {
    if (!genModel.isVirtualDelegation() || genFeature.isPrimitiveType()) {
    if (!genModel.isSuppressNotification()) {
    stringBuffer.append(TEXT_611);
    stringBuffer.append(genFeature.getImportedType());
    stringBuffer.append(TEXT_612);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_613);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_614);
    }
    }
    if (genFeature.isEnumType()) {
    if (genModel.isVirtualDelegation()) {
    stringBuffer.append(TEXT_615);
    stringBuffer.append(genFeature.getImportedType());
    stringBuffer.append(TEXT_616);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_617);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_618);
    stringBuffer.append(genFeature.getEDefault());
    stringBuffer.append(TEXT_619);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_620);
    } else {
    stringBuffer.append(TEXT_621);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_622);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_623);
    stringBuffer.append(genFeature.getEDefault());
    stringBuffer.append(TEXT_624);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_625);
    }
    } else {
    if (genModel.isVirtualDelegation() && !genFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_626);
    stringBuffer.append(genFeature.getImportedType());
    stringBuffer.append(TEXT_627);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_628);
    stringBuffer.append(genFeature.getInternalTypeCast());
    stringBuffer.append(TEXT_629);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_630);
    } else {
    stringBuffer.append(TEXT_631);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_632);
    stringBuffer.append(genFeature.getInternalTypeCast());
    stringBuffer.append(TEXT_633);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_634);
    }
    }
    if (genModel.isVirtualDelegation() && !genFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_635);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_636);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(TEXT_637);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_638);
    }
    }
    if (genFeature.isUnsettable()) {
    if (genModel.isVirtualDelegation() && !genFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_639);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_640);
    } else if (genClass.isESetFlag(genFeature)) {
    if (!genModel.isSuppressNotification()) {
    stringBuffer.append(TEXT_641);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_642);
    stringBuffer.append(genClass.getESetFlagsField(genFeature));
    stringBuffer.append(TEXT_643);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_644);
    }
    stringBuffer.append(TEXT_645);
    stringBuffer.append(genClass.getESetFlagsField(genFeature));
    stringBuffer.append(TEXT_646);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_647);
    } else {
    if (!genModel.isSuppressNotification()) {
    stringBuffer.append(TEXT_648);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_649);
    stringBuffer.append(genFeature.getUncapName());
    stringBuffer.append(TEXT_650);
    }
    stringBuffer.append(TEXT_651);
    stringBuffer.append(genFeature.getUncapName());
    stringBuffer.append(TEXT_652);
    }
    if (!genModel.isSuppressNotification()) {
    stringBuffer.append(TEXT_653);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.impl.ENotificationImpl"));
    stringBuffer.append(TEXT_654);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.notify.Notification"));
    stringBuffer.append(TEXT_655);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(TEXT_656);
    if (genModel.isVirtualDelegation() && !genFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_657);
    stringBuffer.append(genFeature.getEDefault());
    stringBuffer.append(TEXT_658);
    stringBuffer.append(genFeature.getCapName());
    } else {
    stringBuffer.append(TEXT_659);
    stringBuffer.append(genFeature.getCapName());
    }
    stringBuffer.append(TEXT_660);
    if (genClass.isFlag(genFeature)) {
    stringBuffer.append(TEXT_661);
    stringBuffer.append(genFeature.getCapName());
    } else {
    stringBuffer.append(genFeature.getSafeName());
    }
    stringBuffer.append(TEXT_662);
    if (genModel.isVirtualDelegation() && !genFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_663);
    } else {
    stringBuffer.append(TEXT_664);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_665);
    }
    stringBuffer.append(TEXT_666);
    }
    } else {
    if (!genModel.isSuppressNotification()) {
    stringBuffer.append(TEXT_667);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.impl.ENotificationImpl"));
    stringBuffer.append(TEXT_668);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.notify.Notification"));
    stringBuffer.append(TEXT_669);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(TEXT_670);
    if (genModel.isVirtualDelegation() && !genFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_671);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_672);
    stringBuffer.append(genFeature.getEDefault());
    stringBuffer.append(TEXT_673);
    stringBuffer.append(genFeature.getCapName());
    } else {
    stringBuffer.append(TEXT_674);
    stringBuffer.append(genFeature.getCapName());
    }
    stringBuffer.append(TEXT_675);
    if (genClass.isFlag(genFeature)) {
    stringBuffer.append(TEXT_676);
    stringBuffer.append(genFeature.getCapName());
    } else {
    stringBuffer.append(genFeature.getSafeName());
    }
    stringBuffer.append(TEXT_677);
    }
    }
    }
    } else if (genFeature.hasDelegateFeature()) { GenFeature delegateFeature = genFeature.getDelegateFeature();
    if (delegateFeature.isWrappedFeatureMapType()) {
    stringBuffer.append(TEXT_678);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.FeatureMap"));
    stringBuffer.append(TEXT_679);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.FeatureMap"));
    stringBuffer.append(TEXT_680);
    stringBuffer.append(delegateFeature.getAccessorName());
    stringBuffer.append(TEXT_681);
    stringBuffer.append(genFeature.getQualifiedFeatureAccessor());
    stringBuffer.append(TEXT_682);
    if (genFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_683);
    stringBuffer.append(genFeature.getObjectType());
    stringBuffer.append(TEXT_684);
    }
    stringBuffer.append(TEXT_685);
    stringBuffer.append(genFeature.getCapName());
    if (genFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_686);
    }
    stringBuffer.append(TEXT_687);
    } else {
    stringBuffer.append(TEXT_688);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.FeatureMap"));
    stringBuffer.append(TEXT_689);
    stringBuffer.append(delegateFeature.getAccessorName());
    stringBuffer.append(TEXT_690);
    stringBuffer.append(genFeature.getQualifiedFeatureAccessor());
    stringBuffer.append(TEXT_691);
    if (genFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_692);
    stringBuffer.append(genFeature.getObjectType());
    stringBuffer.append(TEXT_693);
    }
    stringBuffer.append(TEXT_694);
    stringBuffer.append(genFeature.getCapName());
    if (genFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_695);
    }
    stringBuffer.append(TEXT_696);
    }
    } else {
    stringBuffer.append(TEXT_697);
    stringBuffer.append(genFeature.getFormattedName());
    stringBuffer.append(TEXT_698);
    stringBuffer.append(genFeature.getFeatureKind());
    stringBuffer.append(TEXT_699);
    //Class/setGenFeature.todo.override.javajetinc
    }
    stringBuffer.append(TEXT_700);
    }
    //Class/setGenFeature.override.javajetinc
    }
    if (isImplementation && !genModel.isReflectiveDelegation() && genFeature.isBasicUnset()) {
    stringBuffer.append(TEXT_701);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.notify.NotificationChain"));
    stringBuffer.append(TEXT_702);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_703);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.notify.NotificationChain"));
    stringBuffer.append(TEXT_704);
    if (!genFeature.isVolatile()) {
    if (genModel.isVirtualDelegation()) {
    stringBuffer.append(TEXT_705);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_706);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(TEXT_707);
    } else {
    stringBuffer.append(TEXT_708);
    stringBuffer.append(genFeature.getImportedType());
    stringBuffer.append(TEXT_709);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_710);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_711);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_712);
    }
    if (genModel.isVirtualDelegation()) {
    stringBuffer.append(TEXT_713);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_714);
    } else if (genClass.isESetFlag(genFeature)) {
    stringBuffer.append(TEXT_715);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_716);
    stringBuffer.append(genClass.getESetFlagsField(genFeature));
    stringBuffer.append(TEXT_717);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_718);
    stringBuffer.append(genClass.getESetFlagsField(genFeature));
    stringBuffer.append(TEXT_719);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_720);
    } else {
    stringBuffer.append(TEXT_721);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_722);
    stringBuffer.append(genFeature.getUncapName());
    stringBuffer.append(TEXT_723);
    stringBuffer.append(genFeature.getUncapName());
    stringBuffer.append(TEXT_724);
    }
    if (!genModel.isSuppressNotification()) {
    stringBuffer.append(TEXT_725);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.impl.ENotificationImpl"));
    stringBuffer.append(TEXT_726);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.impl.ENotificationImpl"));
    stringBuffer.append(TEXT_727);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.notify.Notification"));
    stringBuffer.append(TEXT_728);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(TEXT_729);
    if (genModel.isVirtualDelegation()) {
    stringBuffer.append(TEXT_730);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_731);
    } else {
    stringBuffer.append(TEXT_732);
    stringBuffer.append(genFeature.getCapName());
    }
    stringBuffer.append(TEXT_733);
    if (genModel.isVirtualDelegation()) {
    stringBuffer.append(TEXT_734);
    } else {
    stringBuffer.append(TEXT_735);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_736);
    }
    stringBuffer.append(TEXT_737);
    }
    } else {
    stringBuffer.append(TEXT_738);
    stringBuffer.append(genFeature.getFormattedName());
    stringBuffer.append(TEXT_739);
    stringBuffer.append(genFeature.getFeatureKind());
    stringBuffer.append(TEXT_740);
    //Class/basicUnsetGenFeature.todo.override.javajetinc
    }
    stringBuffer.append(TEXT_741);
    //Class.basicUnsetGenFeature.override.javajetinc
    }
    if (genFeature.isUnset() && (isImplementation || !genFeature.isSuppressedUnsetVisibility())) {
    if (isInterface) {
    stringBuffer.append(TEXT_742);
    stringBuffer.append(genClass.getQualifiedInterfaceName());
    stringBuffer.append(TEXT_743);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_744);
    stringBuffer.append(genFeature.getFormattedName());
    stringBuffer.append(TEXT_745);
    stringBuffer.append(genFeature.getFeatureKind());
    stringBuffer.append(TEXT_746);
    if (!genFeature.isSuppressedIsSetVisibility()) {
    stringBuffer.append(TEXT_747);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_748);
    }
    stringBuffer.append(TEXT_749);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_750);
    if (!genFeature.isListType() && !genFeature.isSuppressedSetVisibility()) {
    stringBuffer.append(TEXT_751);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_752);
    stringBuffer.append(genFeature.getImportedType());
    stringBuffer.append(TEXT_753);
    }
    stringBuffer.append(TEXT_754);
    } else {
    stringBuffer.append(TEXT_755);
    }
    if (!isImplementation) {
    stringBuffer.append(TEXT_756);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_757);
    } else {
    stringBuffer.append(TEXT_758);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_759);
    if (genModel.isReflectiveDelegation()) {
    stringBuffer.append(TEXT_760);
    stringBuffer.append(genFeature.getQualifiedFeatureAccessor());
    stringBuffer.append(TEXT_761);
    } else if (!genFeature.isVolatile()) {
    if (genFeature.isListType()) {
    if (genModel.isVirtualDelegation()) {
    stringBuffer.append(TEXT_762);
    stringBuffer.append(genFeature.getImportedType());
    stringBuffer.append(TEXT_763);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_764);
    stringBuffer.append(genFeature.getImportedType());
    stringBuffer.append(TEXT_765);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(TEXT_766);
    }
    stringBuffer.append(TEXT_767);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_768);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.InternalEList"));
    stringBuffer.append(TEXT_769);
    stringBuffer.append(singleWildcard);
    stringBuffer.append(TEXT_770);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_771);
    } else if (genFeature.isBidirectional() || genFeature.isEffectiveContains()) {
    if (genModel.isVirtualDelegation()) {
    stringBuffer.append(TEXT_772);
    stringBuffer.append(genFeature.getImportedType());
    stringBuffer.append(TEXT_773);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_774);
    stringBuffer.append(genFeature.getImportedType());
    stringBuffer.append(TEXT_775);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(TEXT_776);
    }
    stringBuffer.append(TEXT_777);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_778);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.notify.NotificationChain"));
    stringBuffer.append(TEXT_779);
    if (!genFeature.isBidirectional()) {
    stringBuffer.append(TEXT_780);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.InternalEObject"));
    stringBuffer.append(TEXT_781);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_782);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(TEXT_783);
    } else { GenFeature reverseFeature = genFeature.getReverse(); GenClass targetClass = reverseFeature.getGenClass();
    stringBuffer.append(TEXT_784);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.InternalEObject"));
    stringBuffer.append(TEXT_785);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_786);
    stringBuffer.append(targetClass.getQualifiedFeatureID(reverseFeature));
    stringBuffer.append(TEXT_787);
    stringBuffer.append(targetClass.getImportedInterfaceName());
    stringBuffer.append(TEXT_788);
    }
    stringBuffer.append(TEXT_789);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_790);
    if (genModel.isVirtualDelegation()) {
    stringBuffer.append(TEXT_791);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_792);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(TEXT_793);
    } else if (genClass.isESetFlag(genFeature)) {
    if (!genModel.isSuppressNotification()) {
    stringBuffer.append(TEXT_794);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_795);
    stringBuffer.append(genClass.getESetFlagsField(genFeature));
    stringBuffer.append(TEXT_796);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_797);
    }
    stringBuffer.append(TEXT_798);
    stringBuffer.append(genClass.getESetFlagsField(genFeature));
    stringBuffer.append(TEXT_799);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_800);
    } else {
    if (!genModel.isSuppressNotification()) {
    stringBuffer.append(TEXT_801);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_802);
    stringBuffer.append(genFeature.getUncapName());
    stringBuffer.append(TEXT_803);
    }
    stringBuffer.append(TEXT_804);
    stringBuffer.append(genFeature.getUncapName());
    stringBuffer.append(TEXT_805);
    }
    if (!genModel.isSuppressNotification()) {
    stringBuffer.append(TEXT_806);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.impl.ENotificationImpl"));
    stringBuffer.append(TEXT_807);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.notify.Notification"));
    stringBuffer.append(TEXT_808);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(TEXT_809);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_810);
    }
    stringBuffer.append(TEXT_811);
    } else {
    if (genClass.isFlag(genFeature)) {
    if (!genModel.isSuppressNotification()) {
    stringBuffer.append(TEXT_812);
    stringBuffer.append(genFeature.getImportedType());
    stringBuffer.append(TEXT_813);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_814);
    stringBuffer.append(genClass.getFlagsField(genFeature));
    stringBuffer.append(TEXT_815);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_816);
    }
    } else if (genModel.isVirtualDelegation() && !genFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_817);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_818);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(TEXT_819);
    } else {
    if (!genModel.isSuppressNotification()) {
    stringBuffer.append(TEXT_820);
    stringBuffer.append(genFeature.getImportedType());
    stringBuffer.append(TEXT_821);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_822);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_823);
    }
    }
    if (genModel.isVirtualDelegation() && !genFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_824);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_825);
    } else if (genClass.isESetFlag(genFeature)) {
    stringBuffer.append(TEXT_826);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_827);
    stringBuffer.append(genClass.getESetFlagsField(genFeature));
    stringBuffer.append(TEXT_828);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_829);
    } else {
    if (!genModel.isSuppressNotification()) {
    stringBuffer.append(TEXT_830);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_831);
    stringBuffer.append(genFeature.getUncapName());
    stringBuffer.append(TEXT_832);
    }
    }
    if (genFeature.isReferenceType()) {
    stringBuffer.append(TEXT_833);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_834);
    if (!genModel.isVirtualDelegation()) {
    if (genClass.isESetFlag(genFeature)) {
    stringBuffer.append(TEXT_835);
    stringBuffer.append(genClass.getESetFlagsField(genFeature));
    stringBuffer.append(TEXT_836);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_837);
    } else {
    stringBuffer.append(TEXT_838);
    stringBuffer.append(genFeature.getUncapName());
    stringBuffer.append(TEXT_839);
    }
    }
    if (!genModel.isSuppressNotification()) {
    stringBuffer.append(TEXT_840);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.impl.ENotificationImpl"));
    stringBuffer.append(TEXT_841);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.notify.Notification"));
    stringBuffer.append(TEXT_842);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(TEXT_843);
    if (genModel.isVirtualDelegation()) {
    stringBuffer.append(TEXT_844);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_845);
    } else {
    stringBuffer.append(TEXT_846);
    stringBuffer.append(genFeature.getCapName());
    }
    stringBuffer.append(TEXT_847);
    if (genModel.isVirtualDelegation()) {
    stringBuffer.append(TEXT_848);
    } else {
    stringBuffer.append(TEXT_849);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_850);
    }
    stringBuffer.append(TEXT_851);
    }
    } else {
    if (genClass.isFlag(genFeature)) {
    stringBuffer.append(TEXT_852);
    stringBuffer.append(genFeature.getEDefault());
    stringBuffer.append(TEXT_853);
    stringBuffer.append(genClass.getFlagsField(genFeature));
    stringBuffer.append(TEXT_854);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_855);
    stringBuffer.append(genClass.getFlagsField(genFeature));
    stringBuffer.append(TEXT_856);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_857);
    } else if (!genModel.isVirtualDelegation() || genFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_858);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_859);
    stringBuffer.append(genFeature.getEDefault());
    stringBuffer.append(TEXT_860);
    }
    if (!genModel.isVirtualDelegation() || genFeature.isPrimitiveType()) {
    if (genClass.isESetFlag(genFeature)) {
    stringBuffer.append(TEXT_861);
    stringBuffer.append(genClass.getESetFlagsField(genFeature));
    stringBuffer.append(TEXT_862);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_863);
    } else {
    stringBuffer.append(TEXT_864);
    stringBuffer.append(genFeature.getUncapName());
    stringBuffer.append(TEXT_865);
    }
    }
    if (!genModel.isSuppressNotification()) {
    stringBuffer.append(TEXT_866);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.impl.ENotificationImpl"));
    stringBuffer.append(TEXT_867);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.notify.Notification"));
    stringBuffer.append(TEXT_868);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(TEXT_869);
    if (genModel.isVirtualDelegation() && !genFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_870);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_871);
    stringBuffer.append(genFeature.getEDefault());
    } else {
    stringBuffer.append(TEXT_872);
    stringBuffer.append(genFeature.getCapName());
    }
    stringBuffer.append(TEXT_873);
    stringBuffer.append(genFeature.getEDefault());
    stringBuffer.append(TEXT_874);
    if (genModel.isVirtualDelegation() && !genFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_875);
    } else {
    stringBuffer.append(TEXT_876);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_877);
    }
    stringBuffer.append(TEXT_878);
    }
    }
    }
    } else if (genFeature.hasDelegateFeature()) { GenFeature delegateFeature = genFeature.getDelegateFeature();
    if (delegateFeature.isWrappedFeatureMapType()) {
    stringBuffer.append(TEXT_879);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.FeatureMap"));
    stringBuffer.append(TEXT_880);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.FeatureMap"));
    stringBuffer.append(TEXT_881);
    stringBuffer.append(delegateFeature.getAccessorName());
    stringBuffer.append(TEXT_882);
    stringBuffer.append(genFeature.getQualifiedFeatureAccessor());
    stringBuffer.append(TEXT_883);
    } else {
    stringBuffer.append(TEXT_884);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.FeatureMap"));
    stringBuffer.append(TEXT_885);
    stringBuffer.append(delegateFeature.getAccessorName());
    stringBuffer.append(TEXT_886);
    stringBuffer.append(genFeature.getQualifiedFeatureAccessor());
    stringBuffer.append(TEXT_887);
    }
    } else {
    stringBuffer.append(TEXT_888);
    stringBuffer.append(genFeature.getFormattedName());
    stringBuffer.append(TEXT_889);
    stringBuffer.append(genFeature.getFeatureKind());
    stringBuffer.append(TEXT_890);
    //Class/unsetGenFeature.todo.override.javajetinc
    }
    stringBuffer.append(TEXT_891);
    }
    //Class/unsetGenFeature.override.javajetinc
    }
    if (genFeature.isIsSet() && (isImplementation || !genFeature.isSuppressedIsSetVisibility())) {
    if (isInterface) {
    stringBuffer.append(TEXT_892);
    stringBuffer.append(genClass.getQualifiedInterfaceName());
    stringBuffer.append(TEXT_893);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_894);
    stringBuffer.append(genFeature.getFormattedName());
    stringBuffer.append(TEXT_895);
    stringBuffer.append(genFeature.getFeatureKind());
    stringBuffer.append(TEXT_896);
    stringBuffer.append(genFeature.getFormattedName());
    stringBuffer.append(TEXT_897);
    stringBuffer.append(genFeature.getFeatureKind());
    stringBuffer.append(TEXT_898);
    if (genFeature.isChangeable() && !genFeature.isSuppressedUnsetVisibility()) {
    stringBuffer.append(TEXT_899);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_900);
    }
    stringBuffer.append(TEXT_901);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_902);
    if (!genFeature.isListType() && genFeature.isChangeable() && !genFeature.isSuppressedSetVisibility()) {
    stringBuffer.append(TEXT_903);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_904);
    stringBuffer.append(genFeature.getImportedType());
    stringBuffer.append(TEXT_905);
    }
    stringBuffer.append(TEXT_906);
    } else {
    stringBuffer.append(TEXT_907);
    }
    if (!isImplementation) {
    stringBuffer.append(TEXT_908);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_909);
    } else {
    stringBuffer.append(TEXT_910);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_911);
    if (genModel.isReflectiveDelegation()) {
    stringBuffer.append(TEXT_912);
    stringBuffer.append(genFeature.getQualifiedFeatureAccessor());
    stringBuffer.append(TEXT_913);
    } else if (!genFeature.isVolatile()) {
    if (genFeature.isListType()) {
    if (genModel.isVirtualDelegation()) {
    stringBuffer.append(TEXT_914);
    stringBuffer.append(genFeature.getImportedType());
    stringBuffer.append(TEXT_915);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_916);
    stringBuffer.append(genFeature.getImportedType());
    stringBuffer.append(TEXT_917);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(TEXT_918);
    }
    stringBuffer.append(TEXT_919);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_920);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.InternalEList"));
    stringBuffer.append(TEXT_921);
    stringBuffer.append(singleWildcard);
    stringBuffer.append(TEXT_922);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_923);
    } else {
    if (genModel.isVirtualDelegation() && !genFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_924);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(TEXT_925);
    } else if (genClass.isESetFlag(genFeature)) {
    stringBuffer.append(TEXT_926);
    stringBuffer.append(genClass.getESetFlagsField(genFeature));
    stringBuffer.append(TEXT_927);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_928);
    } else {
    stringBuffer.append(TEXT_929);
    stringBuffer.append(genFeature.getUncapName());
    stringBuffer.append(TEXT_930);
    }
    }
    } else if (genFeature.hasDelegateFeature()) { GenFeature delegateFeature = genFeature.getDelegateFeature();
    if (delegateFeature.isWrappedFeatureMapType()) {
    stringBuffer.append(TEXT_931);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.FeatureMap"));
    stringBuffer.append(TEXT_932);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.FeatureMap"));
    stringBuffer.append(TEXT_933);
    stringBuffer.append(delegateFeature.getAccessorName());
    stringBuffer.append(TEXT_934);
    stringBuffer.append(genFeature.getQualifiedFeatureAccessor());
    stringBuffer.append(TEXT_935);
    } else {
    stringBuffer.append(TEXT_936);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.FeatureMap"));
    stringBuffer.append(TEXT_937);
    stringBuffer.append(delegateFeature.getAccessorName());
    stringBuffer.append(TEXT_938);
    stringBuffer.append(genFeature.getQualifiedFeatureAccessor());
    stringBuffer.append(TEXT_939);
    }
    } else {
    stringBuffer.append(TEXT_940);
    stringBuffer.append(genFeature.getFormattedName());
    stringBuffer.append(TEXT_941);
    stringBuffer.append(genFeature.getFeatureKind());
    stringBuffer.append(TEXT_942);
    //Class/isSetGenFeature.todo.override.javajetinc
    }
    stringBuffer.append(TEXT_943);
    }
    //Class/isSetGenFeature.override.javajetinc
    }
    //Class/genFeature.override.javajetinc
    }//for
    }}.run();
    for (Iterator i= (isImplementation ? genClass.getImplementedGenOperations() : genClass.getDeclaredGenOperations()).iterator(); i.hasNext();) { GenOperation genOperation = (GenOperation)i.next();
    if (isInterface) {
    stringBuffer.append(TEXT_944);
    if (genOperation.hasDocumentation()) {
    stringBuffer.append(TEXT_945);
    stringBuffer.append(genOperation.getDocumentation(genModel.getIndentation(stringBuffer)));
    stringBuffer.append(TEXT_946);
    }
    if (!genModel.isSuppressEMFModelTags()) { boolean first = true; for (StringTokenizer stringTokenizer = new StringTokenizer(genOperation.getModelInfo(), "\n\r"); stringTokenizer.hasMoreTokens(); ) { String modelInfo = stringTokenizer.nextToken(); if (first) { first = false;
    stringBuffer.append(TEXT_947);
    stringBuffer.append(modelInfo);
    } else {
    stringBuffer.append(TEXT_948);
    stringBuffer.append(modelInfo);
    }} if (first) {
    stringBuffer.append(TEXT_949);
    }}
    stringBuffer.append(TEXT_950);
    } else {
    stringBuffer.append(TEXT_951);
    }
    if (!isImplementation) {
    stringBuffer.append(TEXT_952);
    stringBuffer.append(genOperation.getTypeParameters());
    stringBuffer.append(genOperation.getImportedType());
    stringBuffer.append(TEXT_953);
    stringBuffer.append(genOperation.getName());
    stringBuffer.append(TEXT_954);
    stringBuffer.append(genOperation.getParameters());
    stringBuffer.append(TEXT_955);
    stringBuffer.append(genOperation.getThrows());
    stringBuffer.append(TEXT_956);
    } else {
    stringBuffer.append(TEXT_957);
    stringBuffer.append(genOperation.getTypeParameters());
    stringBuffer.append(genOperation.getImportedType());
    stringBuffer.append(TEXT_958);
    stringBuffer.append(genOperation.getName());
    stringBuffer.append(TEXT_959);
    stringBuffer.append(genOperation.getParameters());
    stringBuffer.append(TEXT_960);
    stringBuffer.append(genOperation.getThrows());
    stringBuffer.append(TEXT_961);
    if (genOperation.hasBody()) {
    stringBuffer.append(TEXT_962);
    stringBuffer.append(genOperation.getBody(genModel.getIndentation(stringBuffer)));
    } else if (genOperation.isInvariant()) {GenClass opClass = genOperation.getGenClass(); String diagnostics = ((GenParameter)genOperation.getGenParameters().get(0)).getName(); String context = ((GenParameter)genOperation.getGenParameters().get(1)).getName();
    stringBuffer.append(TEXT_963);
    stringBuffer.append(diagnostics);
    stringBuffer.append(TEXT_964);
    stringBuffer.append(diagnostics);
    stringBuffer.append(TEXT_965);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.util.BasicDiagnostic"));
    stringBuffer.append(TEXT_966);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.util.Diagnostic"));
    stringBuffer.append(TEXT_967);
    stringBuffer.append(opClass.getGenPackage().getImportedValidatorClassName());
    stringBuffer.append(TEXT_968);
    stringBuffer.append(opClass.getGenPackage().getImportedValidatorClassName());
    stringBuffer.append(TEXT_969);
    stringBuffer.append(opClass.getOperationID(genOperation));
    stringBuffer.append(TEXT_970);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.plugin.EcorePlugin"));
    stringBuffer.append(TEXT_971);
    stringBuffer.append(genOperation.getName());
    stringBuffer.append(TEXT_972);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.EObjectValidator"));
    stringBuffer.append(TEXT_973);
    stringBuffer.append(context);
    stringBuffer.append(TEXT_974);
    stringBuffer.append(genModel.getNonNLS());
    stringBuffer.append(genModel.getNonNLS(2));
    stringBuffer.append(TEXT_975);
    } else {
    stringBuffer.append(TEXT_976);
    //Class/implementedGenOperation.todo.override.javajetinc
    }
    stringBuffer.append(TEXT_977);
    }
    //Class/implementedGenOperation.override.javajetinc
    }//for
    if (isImplementation && !genModel.isReflectiveDelegation() && genClass.implementsAny(genClass.getEInverseAddGenFeatures())) {
    stringBuffer.append(TEXT_978);
    if (genModel.useGenerics()) {
    for (Iterator i=genClass.getEInverseAddGenFeatures().iterator(); i.hasNext();) { GenFeature genFeature = (GenFeature)i.next();
    if (genFeature.isUncheckedCast()) {
    stringBuffer.append(TEXT_979);
    break; }
    }
    }
    if (genModel.useClassOverrideAnnotation()) {
    stringBuffer.append(TEXT_980);
    }
    stringBuffer.append(TEXT_981);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.notify.NotificationChain"));
    stringBuffer.append(TEXT_982);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.InternalEObject"));
    stringBuffer.append(TEXT_983);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.notify.NotificationChain"));
    stringBuffer.append(TEXT_984);
    for (Iterator i=genClass.getEInverseAddGenFeatures().iterator(); i.hasNext();) { GenFeature genFeature = (GenFeature)i.next();
    stringBuffer.append(TEXT_985);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(TEXT_986);
    if (genFeature.isListType()) { String cast = "("  + genModel.getImportedName("org.eclipse.emf.ecore.util.InternalEList") + (!genModel.useGenerics() ? ")" : "<" + genModel.getImportedName("org.eclipse.emf.ecore.InternalEObject") + ">)(" + genModel.getImportedName("org.eclipse.emf.ecore.util.InternalEList") + "<?>)");
    if (genFeature.isMapType() && genFeature.isEffectiveSuppressEMFTypes()) {
    stringBuffer.append(TEXT_987);
    stringBuffer.append(cast);
    stringBuffer.append(TEXT_988);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.util.EMap"));
    stringBuffer.append(TEXT_989);
    stringBuffer.append(genFeature.getImportedMapTemplateArguments());
    stringBuffer.append(TEXT_990);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_991);
    } else {
    stringBuffer.append(TEXT_992);
    stringBuffer.append(cast);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_993);
    }
    } else if (genFeature.isContainer()) {
    stringBuffer.append(TEXT_994);
    if (genFeature.isBasicSet()) {
    stringBuffer.append(TEXT_995);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_996);
    stringBuffer.append(genFeature.getImportedType());
    stringBuffer.append(TEXT_997);
    } else {
    stringBuffer.append(TEXT_998);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(TEXT_999);
    }
    } else {
    if (genClass.getImplementingGenModel(genFeature).isVirtualDelegation()) {
    stringBuffer.append(TEXT_1000);
    stringBuffer.append(genFeature.getImportedType());
    stringBuffer.append(TEXT_1001);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_1002);
    stringBuffer.append(genFeature.getImportedType());
    stringBuffer.append(TEXT_1003);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(TEXT_1004);
    }
    stringBuffer.append(TEXT_1005);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_1006);
    if (genFeature.isEffectiveContains()) {
    stringBuffer.append(TEXT_1007);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.InternalEObject"));
    stringBuffer.append(TEXT_1008);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_1009);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(TEXT_1010);
    } else { GenFeature reverseFeature = genFeature.getReverse(); GenClass targetClass = reverseFeature.getGenClass();
    stringBuffer.append(TEXT_1011);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.InternalEObject"));
    stringBuffer.append(TEXT_1012);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_1013);
    stringBuffer.append(targetClass.getQualifiedFeatureID(reverseFeature));
    stringBuffer.append(TEXT_1014);
    stringBuffer.append(targetClass.getImportedInterfaceName());
    stringBuffer.append(TEXT_1015);
    }
    stringBuffer.append(TEXT_1016);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_1017);
    stringBuffer.append(genFeature.getImportedType());
    stringBuffer.append(TEXT_1018);
    }
    }
    stringBuffer.append(TEXT_1019);
    if (genModel.isMinimalReflectiveMethods()) {
    stringBuffer.append(TEXT_1020);
    } else {
    stringBuffer.append(TEXT_1021);
    }
    stringBuffer.append(TEXT_1022);
    }
    if (isImplementation && !genModel.isReflectiveDelegation() && genClass.implementsAny(genClass.getEInverseRemoveGenFeatures())) {
    stringBuffer.append(TEXT_1023);
    if (genModel.useClassOverrideAnnotation()) {
    stringBuffer.append(TEXT_1024);
    }
    stringBuffer.append(TEXT_1025);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.notify.NotificationChain"));
    stringBuffer.append(TEXT_1026);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.InternalEObject"));
    stringBuffer.append(TEXT_1027);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.notify.NotificationChain"));
    stringBuffer.append(TEXT_1028);
    for (Iterator i=genClass.getEInverseRemoveGenFeatures().iterator(); i.hasNext();) { GenFeature genFeature = (GenFeature)i.next();
    stringBuffer.append(TEXT_1029);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(TEXT_1030);
    if (genFeature.isListType()) {
    if (genFeature.isMapType() && genFeature.isEffectiveSuppressEMFTypes()) {
    stringBuffer.append(TEXT_1031);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.InternalEList"));
    stringBuffer.append(singleWildcard);
    stringBuffer.append(TEXT_1032);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.util.EMap"));
    stringBuffer.append(TEXT_1033);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_1034);
    } else if (genFeature.isWrappedFeatureMapType()) {
    stringBuffer.append(TEXT_1035);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.InternalEList"));
    stringBuffer.append(singleWildcard);
    stringBuffer.append(TEXT_1036);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.FeatureMap"));
    stringBuffer.append(TEXT_1037);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_1038);
    } else {
    stringBuffer.append(TEXT_1039);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.InternalEList"));
    stringBuffer.append(singleWildcard);
    stringBuffer.append(TEXT_1040);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_1041);
    }
    } else if (genFeature.isContainer() && !genFeature.isBasicSet()) {
    stringBuffer.append(TEXT_1042);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(TEXT_1043);
    } else if (genFeature.isUnsettable()) {
    stringBuffer.append(TEXT_1044);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_1045);
    } else {
    stringBuffer.append(TEXT_1046);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_1047);
    }
    }
    stringBuffer.append(TEXT_1048);
    if (genModel.isMinimalReflectiveMethods()) {
    stringBuffer.append(TEXT_1049);
    } else {
    stringBuffer.append(TEXT_1050);
    }
    stringBuffer.append(TEXT_1051);
    }
    if (isImplementation && !genModel.isReflectiveDelegation() && genClass.implementsAny(genClass.getEBasicRemoveFromContainerGenFeatures())) {
    stringBuffer.append(TEXT_1052);
    if (genModel.useClassOverrideAnnotation()) {
    stringBuffer.append(TEXT_1053);
    }
    stringBuffer.append(TEXT_1054);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.notify.NotificationChain"));
    stringBuffer.append(TEXT_1055);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.notify.NotificationChain"));
    stringBuffer.append(TEXT_1056);
    for (Iterator i=genClass.getEBasicRemoveFromContainerGenFeatures().iterator(); i.hasNext();) { GenFeature genFeature = (GenFeature)i.next();
    GenFeature reverseFeature = genFeature.getReverse(); GenClass targetClass = reverseFeature.getGenClass();
    stringBuffer.append(TEXT_1057);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(TEXT_1058);
    stringBuffer.append(targetClass.getQualifiedFeatureID(reverseFeature));
    stringBuffer.append(TEXT_1059);
    stringBuffer.append(targetClass.getImportedInterfaceName());
    stringBuffer.append(TEXT_1060);
    }
    stringBuffer.append(TEXT_1061);
    if (genModel.isMinimalReflectiveMethods()) {
    stringBuffer.append(TEXT_1062);
    } else {
    stringBuffer.append(TEXT_1063);
    }
    stringBuffer.append(TEXT_1064);
    }
    if (isImplementation && !genModel.isReflectiveDelegation() && !genClass.getImplementedGenFeatures().isEmpty()) {
    stringBuffer.append(TEXT_1065);
    if (genModel.useClassOverrideAnnotation()) {
    stringBuffer.append(TEXT_1066);
    }
    stringBuffer.append(TEXT_1067);
    for (Iterator i=genModel.isMinimalReflectiveMethods() ? genClass.getImplementedGenFeatures().iterator() : genClass.getAllGenFeatures().iterator(); i.hasNext();) { GenFeature genFeature = (GenFeature)i.next();
    stringBuffer.append(TEXT_1068);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(TEXT_1069);
    if (genFeature.isPrimitiveType()) {
    if (genFeature.isBooleanType()) {
    stringBuffer.append(TEXT_1070);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_1071);
    } else {
    stringBuffer.append(TEXT_1072);
    stringBuffer.append(genFeature.getObjectType());
    stringBuffer.append(TEXT_1073);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_1074);
    }
    } else if (genFeature.isResolveProxies() && !genFeature.isListType()) {
    stringBuffer.append(TEXT_1075);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_1076);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_1077);
    } else if (genFeature.isMapType()) {
    if (genFeature.isEffectiveSuppressEMFTypes()) {
    stringBuffer.append(TEXT_1078);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.util.EMap"));
    stringBuffer.append(TEXT_1079);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_1080);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_1081);
    } else {
    stringBuffer.append(TEXT_1082);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_1083);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_1084);
    }
    } else if (genFeature.isWrappedFeatureMapType()) {
    stringBuffer.append(TEXT_1085);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.FeatureMap"));
    stringBuffer.append(TEXT_1086);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_1087);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_1088);
    } else if (genFeature.isFeatureMapType()) {
    stringBuffer.append(TEXT_1089);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_1090);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.FeatureMap"));
    stringBuffer.append(TEXT_1091);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_1092);
    } else {
    stringBuffer.append(TEXT_1093);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_1094);
    }
    }
    stringBuffer.append(TEXT_1095);
    if (genModel.isMinimalReflectiveMethods()) {
    stringBuffer.append(TEXT_1096);
    } else {
    stringBuffer.append(TEXT_1097);
    }
    stringBuffer.append(TEXT_1098);
    }
    if (isImplementation && !genModel.isReflectiveDelegation() && genClass.implementsAny(genClass.getESetGenFeatures())) {
    stringBuffer.append(TEXT_1099);
    if (genModel.useGenerics()) {
    for (Iterator i=genClass.getESetGenFeatures().iterator(); i.hasNext();) { GenFeature genFeature = (GenFeature)i.next();
    if (genFeature.isUncheckedCast() && !genFeature.isFeatureMapType() && !genFeature.isMapType()) {
    stringBuffer.append(TEXT_1100);
    break; }
    }
    }
    if (genModel.useClassOverrideAnnotation()) {
    stringBuffer.append(TEXT_1101);
    }
    stringBuffer.append(TEXT_1102);
    for (Iterator i=genClass.getESetGenFeatures().iterator(); i.hasNext();) { GenFeature genFeature = (GenFeature)i.next();
    stringBuffer.append(TEXT_1103);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(TEXT_1104);
    if (genFeature.isListType()) {
    if (genFeature.isWrappedFeatureMapType()) {
    stringBuffer.append(TEXT_1105);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.FeatureMap"));
    stringBuffer.append(TEXT_1106);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.FeatureMap"));
    stringBuffer.append(TEXT_1107);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_1108);
    } else if (genFeature.isFeatureMapType()) {
    stringBuffer.append(TEXT_1109);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.FeatureMap"));
    stringBuffer.append(TEXT_1110);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_1111);
    } else if (genFeature.isMapType()) {
    if (genFeature.isEffectiveSuppressEMFTypes()) {
    stringBuffer.append(TEXT_1112);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.EStructuralFeature"));
    stringBuffer.append(TEXT_1113);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.util.EMap"));
    stringBuffer.append(TEXT_1114);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_1115);
    } else {
    stringBuffer.append(TEXT_1116);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.EStructuralFeature"));
    stringBuffer.append(TEXT_1117);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_1118);
    }
    } else {
    stringBuffer.append(TEXT_1119);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_1120);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_1121);
    stringBuffer.append(genModel.getImportedName("java.util.Collection"));
    if (genModel.getComplianceLevel().getValue() >= GenJDKLevel.JDK50) {
    stringBuffer.append(TEXT_1122);
    stringBuffer.append(genFeature.getListItemType());
    stringBuffer.append(TEXT_1123);
    }
    stringBuffer.append(TEXT_1124);
    }
    } else if (genFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_1125);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_1126);
    stringBuffer.append(genFeature.getObjectType());
    stringBuffer.append(TEXT_1127);
    stringBuffer.append(genFeature.getPrimitiveValueFunction());
    stringBuffer.append(TEXT_1128);
    } else {
    stringBuffer.append(TEXT_1129);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_1130);
    if (genFeature.getTypeGenDataType() == null || !genFeature.getTypeGenDataType().isObjectType() || !genFeature.getRawType().equals(genFeature.getType())) {
    stringBuffer.append(TEXT_1131);
    stringBuffer.append(genFeature.getImportedType());
    stringBuffer.append(TEXT_1132);
    }
    stringBuffer.append(TEXT_1133);
    }
    stringBuffer.append(TEXT_1134);
    }
    stringBuffer.append(TEXT_1135);
    if (genModel.isMinimalReflectiveMethods()) {
    stringBuffer.append(TEXT_1136);
    } else {
    stringBuffer.append(TEXT_1137);
    }
    stringBuffer.append(TEXT_1138);
    if (genModel.useClassOverrideAnnotation()) {
    stringBuffer.append(TEXT_1139);
    }
    stringBuffer.append(TEXT_1140);
    for (Iterator i=genClass.getESetGenFeatures().iterator(); i.hasNext();) { GenFeature genFeature = (GenFeature)i.next();
    stringBuffer.append(TEXT_1141);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(TEXT_1142);
    if (genFeature.isListType() && !genFeature.isUnsettable()) {
    if (genFeature.isWrappedFeatureMapType()) {
    stringBuffer.append(TEXT_1143);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.FeatureMap"));
    stringBuffer.append(TEXT_1144);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_1145);
    } else {
    stringBuffer.append(TEXT_1146);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_1147);
    }
    } else if (genFeature.isUnsettable()) {
    stringBuffer.append(TEXT_1148);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_1149);
    } else if (!genFeature.hasEDefault()) {
    stringBuffer.append(TEXT_1150);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_1151);
    stringBuffer.append(genFeature.getImportedType());
    stringBuffer.append(TEXT_1152);
    } else {
    stringBuffer.append(TEXT_1153);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_1154);
    stringBuffer.append(genFeature.getEDefault());
    stringBuffer.append(TEXT_1155);
    }
    stringBuffer.append(TEXT_1156);
    }
    stringBuffer.append(TEXT_1157);
    if (genModel.isMinimalReflectiveMethods()) {
    stringBuffer.append(TEXT_1158);
    } else {
    stringBuffer.append(TEXT_1159);
    }
    stringBuffer.append(TEXT_1160);
    }
    if (isImplementation && !genModel.isReflectiveDelegation() && !genClass.getImplementedGenFeatures().isEmpty()) {
    stringBuffer.append(TEXT_1161);
    if (genModel.useClassOverrideAnnotation()) {
    stringBuffer.append(TEXT_1162);
    }
    stringBuffer.append(TEXT_1163);
    for (Iterator i=genModel.isMinimalReflectiveMethods() ? genClass.getImplementedGenFeatures().iterator() : genClass.getAllGenFeatures().iterator(); i.hasNext();) { GenFeature genFeature = (GenFeature)i.next();
    stringBuffer.append(TEXT_1164);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(TEXT_1165);
    if (genFeature.isListType() && !genFeature.isUnsettable()) {
    if (genFeature.isWrappedFeatureMapType()) {
    if (genFeature.isVolatile()) {
    stringBuffer.append(TEXT_1166);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.FeatureMap"));
    stringBuffer.append(TEXT_1167);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_1168);
    } else {
    stringBuffer.append(TEXT_1169);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_1170);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_1171);
    }
    } else {
    if (genClass.isField(genFeature)) {
    stringBuffer.append(TEXT_1172);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_1173);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_1174);
    } else {
    if (genFeature.isField() && genClass.getImplementingGenModel(genFeature).isVirtualDelegation()) {
    stringBuffer.append(TEXT_1175);
    stringBuffer.append(genFeature.getImportedType());
    stringBuffer.append(TEXT_1176);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_1177);
    stringBuffer.append(genFeature.getImportedType());
    stringBuffer.append(TEXT_1178);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(TEXT_1179);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_1180);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_1181);
    } else {
    stringBuffer.append(TEXT_1182);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_1183);
    }
    }
    }
    } else if (genFeature.isUnsettable()) {
    stringBuffer.append(TEXT_1184);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_1185);
    } else if (genFeature.isResolveProxies()) {
    if (genClass.isField(genFeature)) {
    stringBuffer.append(TEXT_1186);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_1187);
    } else {
    if (genFeature.isField() && genClass.getImplementingGenModel(genFeature).isVirtualDelegation()) {
    stringBuffer.append(TEXT_1188);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(TEXT_1189);
    } else {
    stringBuffer.append(TEXT_1190);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_1191);
    }
    }
    } else if (!genFeature.hasEDefault()) {
    if (genClass.isField(genFeature)) {
    stringBuffer.append(TEXT_1192);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_1193);
    } else {
    if (genFeature.isField() && genClass.getImplementingGenModel(genFeature).isVirtualDelegation()) {
    stringBuffer.append(TEXT_1194);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(TEXT_1195);
    } else {
    stringBuffer.append(TEXT_1196);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_1197);
    }
    }
    } else if (genFeature.isPrimitiveType() || genFeature.isEnumType()) {
    if (genClass.isField(genFeature)) {
    if (genClass.isFlag(genFeature)) {
    stringBuffer.append(TEXT_1198);
    stringBuffer.append(genClass.getFlagsField(genFeature));
    stringBuffer.append(TEXT_1199);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_1200);
    stringBuffer.append(genFeature.getEDefault());
    stringBuffer.append(TEXT_1201);
    } else {
    stringBuffer.append(TEXT_1202);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_1203);
    stringBuffer.append(genFeature.getEDefault());
    stringBuffer.append(TEXT_1204);
    }
    } else {
    if (genFeature.isEnumType() && genFeature.isField() && genClass.getImplementingGenModel(genFeature).isVirtualDelegation()) {
    stringBuffer.append(TEXT_1205);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(TEXT_1206);
    stringBuffer.append(genFeature.getEDefault());
    stringBuffer.append(TEXT_1207);
    stringBuffer.append(genFeature.getEDefault());
    stringBuffer.append(TEXT_1208);
    } else {
    stringBuffer.append(TEXT_1209);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_1210);
    stringBuffer.append(genFeature.getEDefault());
    stringBuffer.append(TEXT_1211);
    }
    }
    } else {//datatype
    if (genClass.isField(genFeature)) {
    stringBuffer.append(TEXT_1212);
    stringBuffer.append(genFeature.getEDefault());
    stringBuffer.append(TEXT_1213);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_1214);
    stringBuffer.append(genFeature.getEDefault());
    stringBuffer.append(TEXT_1215);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_1216);
    } else {
    if (genFeature.isField() && genClass.getImplementingGenModel(genFeature).isVirtualDelegation()) {
    stringBuffer.append(TEXT_1217);
    stringBuffer.append(genFeature.getImportedType());
    stringBuffer.append(TEXT_1218);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_1219);
    stringBuffer.append(genFeature.getImportedType());
    stringBuffer.append(TEXT_1220);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(TEXT_1221);
    stringBuffer.append(genFeature.getEDefault());
    stringBuffer.append(TEXT_1222);
    stringBuffer.append(genFeature.getEDefault());
    stringBuffer.append(TEXT_1223);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_1224);
    stringBuffer.append(genFeature.getEDefault());
    stringBuffer.append(TEXT_1225);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_1226);
    } else {
    stringBuffer.append(TEXT_1227);
    stringBuffer.append(genFeature.getEDefault());
    stringBuffer.append(TEXT_1228);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_1229);
    stringBuffer.append(genFeature.getEDefault());
    stringBuffer.append(TEXT_1230);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_1231);
    }
    }
    }
    }
    stringBuffer.append(TEXT_1232);
    if (genModel.isMinimalReflectiveMethods()) {
    stringBuffer.append(TEXT_1233);
    } else {
    stringBuffer.append(TEXT_1234);
    }
    stringBuffer.append(TEXT_1235);
    //Class/eIsSet.override.javajetinc
    }
    if (isImplementation && !genClass.getMixinGenFeatures().isEmpty()) {
    stringBuffer.append(TEXT_1236);
    if (genModel.useClassOverrideAnnotation()) {
    stringBuffer.append(TEXT_1237);
    }
    stringBuffer.append(TEXT_1238);
    for (Iterator m=genClass.getMixinGenClasses().iterator(); m.hasNext();) { GenClass mixinGenClass = (GenClass)m.next(); 
    stringBuffer.append(TEXT_1239);
    stringBuffer.append(mixinGenClass.getImportedInterfaceName());
    stringBuffer.append(TEXT_1240);
    for (Iterator f=mixinGenClass.getGenFeatures().iterator(); f.hasNext();) { GenFeature genFeature = (GenFeature)f.next(); 
    stringBuffer.append(TEXT_1241);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(TEXT_1242);
    stringBuffer.append(mixinGenClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(TEXT_1243);
    }
    stringBuffer.append(TEXT_1244);
    }
    stringBuffer.append(TEXT_1245);
    if (genModel.useClassOverrideAnnotation()) {
    stringBuffer.append(TEXT_1246);
    }
    stringBuffer.append(TEXT_1247);
    for (Iterator m=genClass.getMixinGenClasses().iterator(); m.hasNext();) { GenClass mixinGenClass = (GenClass)m.next(); 
    stringBuffer.append(TEXT_1248);
    stringBuffer.append(mixinGenClass.getImportedInterfaceName());
    stringBuffer.append(TEXT_1249);
    for (Iterator f=mixinGenClass.getGenFeatures().iterator(); f.hasNext();) { GenFeature genFeature = (GenFeature)f.next(); 
    stringBuffer.append(TEXT_1250);
    stringBuffer.append(mixinGenClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(TEXT_1251);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(TEXT_1252);
    }
    stringBuffer.append(TEXT_1253);
    }
    stringBuffer.append(TEXT_1254);
    }
    if (isImplementation && genModel.isVirtualDelegation()) { String eVirtualValuesField = genClass.getEVirtualValuesField();
    if (eVirtualValuesField != null) {
    stringBuffer.append(TEXT_1255);
    if (genModel.useClassOverrideAnnotation()) {
    stringBuffer.append(TEXT_1256);
    }
    stringBuffer.append(TEXT_1257);
    stringBuffer.append(eVirtualValuesField);
    stringBuffer.append(TEXT_1258);
    if (genModel.useClassOverrideAnnotation()) {
    stringBuffer.append(TEXT_1259);
    }
    stringBuffer.append(TEXT_1260);
    stringBuffer.append(eVirtualValuesField);
    stringBuffer.append(TEXT_1261);
    }
    { List eVirtualIndexBitFields = genClass.getEVirtualIndexBitFields(new ArrayList());
    if (!eVirtualIndexBitFields.isEmpty()) { List allEVirtualIndexBitFields = genClass.getAllEVirtualIndexBitFields(new ArrayList());
    stringBuffer.append(TEXT_1262);
    if (genModel.useClassOverrideAnnotation()) {
    stringBuffer.append(TEXT_1263);
    }
    stringBuffer.append(TEXT_1264);
    for (int i = 0; i < allEVirtualIndexBitFields.size(); i++) {
    stringBuffer.append(TEXT_1265);
    stringBuffer.append(i);
    stringBuffer.append(TEXT_1266);
    stringBuffer.append(allEVirtualIndexBitFields.get(i));
    stringBuffer.append(TEXT_1267);
    }
    stringBuffer.append(TEXT_1268);
    if (genModel.useClassOverrideAnnotation()) {
    stringBuffer.append(TEXT_1269);
    }
    stringBuffer.append(TEXT_1270);
    for (int i = 0; i < allEVirtualIndexBitFields.size(); i++) {
    stringBuffer.append(TEXT_1271);
    stringBuffer.append(i);
    stringBuffer.append(TEXT_1272);
    stringBuffer.append(allEVirtualIndexBitFields.get(i));
    stringBuffer.append(TEXT_1273);
    }
    stringBuffer.append(TEXT_1274);
    }
    }
    }
    if (isImplementation && !genModel.isReflectiveDelegation() && !genClass.getToStringGenFeatures().isEmpty()) {
    stringBuffer.append(TEXT_1275);
    if (genModel.useClassOverrideAnnotation()) {
    stringBuffer.append(TEXT_1276);
    }
    stringBuffer.append(TEXT_1277);
    { boolean first = true;
    for (Iterator i=genClass.getToStringGenFeatures().iterator(); i.hasNext(); ) { GenFeature genFeature = (GenFeature)i.next();
    if (first) { first = false;
    stringBuffer.append(TEXT_1278);
    stringBuffer.append(genFeature.getName());
    stringBuffer.append(TEXT_1279);
    stringBuffer.append(genModel.getNonNLS());
    } else {
    stringBuffer.append(TEXT_1280);
    stringBuffer.append(genFeature.getName());
    stringBuffer.append(TEXT_1281);
    stringBuffer.append(genModel.getNonNLS());
    }
    if (genFeature.isUnsettable() && !genFeature.isListType()) {
    if (genModel.isVirtualDelegation() && !genFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_1282);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(TEXT_1283);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(TEXT_1284);
    stringBuffer.append(genModel.getNonNLS());
    } else {
    if (genClass.isFlag(genFeature)) {
    stringBuffer.append(TEXT_1285);
    if (genClass.isESetFlag(genFeature)) {
    stringBuffer.append(TEXT_1286);
    stringBuffer.append(genClass.getESetFlagsField(genFeature));
    stringBuffer.append(TEXT_1287);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_1288);
    } else {
    stringBuffer.append(genFeature.getUncapName());
    stringBuffer.append(TEXT_1289);
    }
    stringBuffer.append(TEXT_1290);
    stringBuffer.append(genClass.getFlagsField(genFeature));
    stringBuffer.append(TEXT_1291);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_1292);
    stringBuffer.append(genModel.getNonNLS());
    } else {
    stringBuffer.append(TEXT_1293);
    if (genClass.isESetFlag(genFeature)) {
    stringBuffer.append(TEXT_1294);
    stringBuffer.append(genClass.getESetFlagsField(genFeature));
    stringBuffer.append(TEXT_1295);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_1296);
    } else {
    stringBuffer.append(genFeature.getUncapName());
    stringBuffer.append(TEXT_1297);
    }
    stringBuffer.append(TEXT_1298);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_1299);
    stringBuffer.append(genModel.getNonNLS());
    }
    }
    } else {
    if (genModel.isVirtualDelegation() && !genFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_1300);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    if (!genFeature.isListType() && !genFeature.isReferenceType()){
    stringBuffer.append(TEXT_1301);
    stringBuffer.append(genFeature.getEDefault());
    }
    stringBuffer.append(TEXT_1302);
    } else {
    if (genClass.isFlag(genFeature)) {
    stringBuffer.append(TEXT_1303);
    stringBuffer.append(genClass.getFlagsField(genFeature));
    stringBuffer.append(TEXT_1304);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_1305);
    } else {
    stringBuffer.append(TEXT_1306);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_1307);
    }
    }
    }
    }
    }
    stringBuffer.append(TEXT_1308);
    }
    if (isImplementation && genClass.isMapEntry()) { GenFeature keyFeature = genClass.getMapEntryKeyFeature(); GenFeature valueFeature = genClass.getMapEntryValueFeature();
    String objectType = genModel.getImportedName("java.lang.Object");
    String keyType = genModel.getComplianceLevel().getValue() >= GenJDKLevel.JDK50 ? keyFeature.getObjectType() : objectType;
    String valueType = genModel.getComplianceLevel().getValue() >= GenJDKLevel.JDK50 ? valueFeature.getObjectType() : objectType;
    String eMapType = genModel.getImportedName("org.eclipse.emf.common.util.EMap") + (genModel.getComplianceLevel().getValue() >= GenJDKLevel.JDK50 ? "<" + keyType + ", " + valueType + ">" : "");
    stringBuffer.append(TEXT_1309);
    stringBuffer.append(objectType);
    stringBuffer.append(TEXT_1310);
    stringBuffer.append(keyType);
    stringBuffer.append(TEXT_1311);
    if (keyFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_1312);
    stringBuffer.append(keyFeature.getObjectType());
    stringBuffer.append(TEXT_1313);
    } else {
    stringBuffer.append(TEXT_1314);
    }
    stringBuffer.append(TEXT_1315);
    stringBuffer.append(keyType);
    stringBuffer.append(TEXT_1316);
    if (keyFeature.isListType()) {
    stringBuffer.append(TEXT_1317);
    stringBuffer.append(genModel.getImportedName("java.util.Collection"));
    stringBuffer.append(TEXT_1318);
    } else if (genModel.getComplianceLevel().getValue() >= GenJDKLevel.JDK50) {
    stringBuffer.append(TEXT_1319);
    } else if (keyFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_1320);
    stringBuffer.append(keyFeature.getObjectType());
    stringBuffer.append(TEXT_1321);
    stringBuffer.append(keyFeature.getPrimitiveValueFunction());
    stringBuffer.append(TEXT_1322);
    } else {
    stringBuffer.append(TEXT_1323);
    stringBuffer.append(keyFeature.getImportedType());
    stringBuffer.append(TEXT_1324);
    }
    stringBuffer.append(TEXT_1325);
    stringBuffer.append(valueType);
    stringBuffer.append(TEXT_1326);
    if (valueFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_1327);
    stringBuffer.append(valueFeature.getObjectType());
    stringBuffer.append(TEXT_1328);
    } else {
    stringBuffer.append(TEXT_1329);
    }
    stringBuffer.append(TEXT_1330);
    stringBuffer.append(valueType);
    stringBuffer.append(TEXT_1331);
    stringBuffer.append(valueType);
    stringBuffer.append(TEXT_1332);
    stringBuffer.append(valueType);
    stringBuffer.append(TEXT_1333);
    if (valueFeature.isListType()) {
    stringBuffer.append(TEXT_1334);
    stringBuffer.append(genModel.getImportedName("java.util.Collection"));
    stringBuffer.append(TEXT_1335);
    } else if (genModel.getComplianceLevel().getValue() >= GenJDKLevel.JDK50) {
    stringBuffer.append(TEXT_1336);
    } else if (valueFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_1337);
    stringBuffer.append(valueFeature.getObjectType());
    stringBuffer.append(TEXT_1338);
    stringBuffer.append(valueFeature.getPrimitiveValueFunction());
    stringBuffer.append(TEXT_1339);
    } else {
    stringBuffer.append(TEXT_1340);
    stringBuffer.append(valueFeature.getImportedType());
    stringBuffer.append(TEXT_1341);
    }
    stringBuffer.append(TEXT_1342);
    if (genModel.useGenerics()) {
    stringBuffer.append(TEXT_1343);
    }
    stringBuffer.append(TEXT_1344);
    stringBuffer.append(eMapType);
    stringBuffer.append(TEXT_1345);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.EObject"));
    stringBuffer.append(TEXT_1346);
    stringBuffer.append(eMapType);
    stringBuffer.append(TEXT_1347);
    }
    stringBuffer.append(TEXT_1348);
    stringBuffer.append(isInterface ? " " + genClass.getInterfaceName() : genClass.getClassName());
    // TODO fix the space above
    genModel.emitSortedImports();
    stringBuffer.append(TEXT_1349);
    return stringBuffer.toString();
  }
}
