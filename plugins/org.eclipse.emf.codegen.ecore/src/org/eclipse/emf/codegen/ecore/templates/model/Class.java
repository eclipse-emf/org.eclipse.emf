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
  protected final String TEXT_80 = "; // TODO The default value literal \"";
  protected final String TEXT_81 = "\" is not valid.";
  protected final String TEXT_82 = " = ";
  protected final String TEXT_83 = ";";
  protected final String TEXT_84 = NL;
  protected final String TEXT_85 = NL + "\t/**" + NL + "\t * An additional set of bit flags representing the values of boolean attributes and whether unsettable features have been set." + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t * @ordered" + NL + "\t */" + NL + "\tprotected int ";
  protected final String TEXT_86 = " = 0;" + NL;
  protected final String TEXT_87 = NL + "\t/**" + NL + "\t * The flag representing the value of the '{@link #";
  protected final String TEXT_88 = "() <em>";
  protected final String TEXT_89 = "</em>}' ";
  protected final String TEXT_90 = "." + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @see #";
  protected final String TEXT_91 = "()" + NL + "\t * @generated" + NL + "\t * @ordered" + NL + "\t */" + NL + "\tprotected static final int ";
  protected final String TEXT_92 = "_EFLAG = 1 ";
  protected final String TEXT_93 = ";" + NL;
  protected final String TEXT_94 = NL + "\t/**" + NL + "\t * The cached value of the '{@link #";
  protected final String TEXT_95 = "() <em>";
  protected final String TEXT_96 = "</em>}' ";
  protected final String TEXT_97 = "." + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @see #";
  protected final String TEXT_98 = "()" + NL + "\t * @generated" + NL + "\t * @ordered" + NL + "\t */" + NL + "\tprotected ";
  protected final String TEXT_99 = " ";
  protected final String TEXT_100 = " = ";
  protected final String TEXT_101 = ";" + NL;
  protected final String TEXT_102 = NL + "\t/**" + NL + "\t * An additional set of bit flags representing the values of boolean attributes and whether unsettable features have been set." + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t * @ordered" + NL + "\t */" + NL + "\tprotected int ";
  protected final String TEXT_103 = " = 0;" + NL;
  protected final String TEXT_104 = NL + "\t/**" + NL + "\t * The flag representing whether the ";
  protected final String TEXT_105 = " ";
  protected final String TEXT_106 = " has been set." + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t * @ordered" + NL + "\t */" + NL + "\tprotected static final int ";
  protected final String TEXT_107 = "_ESETFLAG = 1 ";
  protected final String TEXT_108 = ";" + NL;
  protected final String TEXT_109 = NL + "\t/**" + NL + "\t * This is true if the ";
  protected final String TEXT_110 = " ";
  protected final String TEXT_111 = " has been set." + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t * @ordered" + NL + "\t */" + NL + "\tprotected boolean ";
  protected final String TEXT_112 = "ESet = false;" + NL;
  protected final String TEXT_113 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */" + NL + "\tprotected ";
  protected final String TEXT_114 = "()" + NL + "\t{" + NL + "\t\tsuper();";
  protected final String TEXT_115 = NL + "\t\t";
  protected final String TEXT_116 = " |= ";
  protected final String TEXT_117 = "_EFLAG;";
  protected final String TEXT_118 = NL + "\t}" + NL + "" + NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */";
  protected final String TEXT_119 = NL + "\t@Override";
  protected final String TEXT_120 = NL + "\tprotected ";
  protected final String TEXT_121 = " eStaticClass()" + NL + "\t{" + NL + "\t\treturn ";
  protected final String TEXT_122 = ";" + NL + "\t}" + NL;
  protected final String TEXT_123 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */";
  protected final String TEXT_124 = NL + "\t";
  protected final String TEXT_125 = "[] ";
  protected final String TEXT_126 = "();" + NL;
  protected final String TEXT_127 = NL + "\tpublic ";
  protected final String TEXT_128 = "[] ";
  protected final String TEXT_129 = "()" + NL + "\t{";
  protected final String TEXT_130 = NL + "\t\t";
  protected final String TEXT_131 = " list = (";
  protected final String TEXT_132 = ")";
  protected final String TEXT_133 = "();" + NL + "\t\tif (list.isEmpty()) return ";
  protected final String TEXT_134 = "_EEMPTY_ARRAY;";
  protected final String TEXT_135 = NL + "\t\tif (";
  protected final String TEXT_136 = " == null || ";
  protected final String TEXT_137 = ".isEmpty()) return ";
  protected final String TEXT_138 = "_EEMPTY_ARRAY;" + NL + "\t\t";
  protected final String TEXT_139 = " list = (";
  protected final String TEXT_140 = ")";
  protected final String TEXT_141 = ";";
  protected final String TEXT_142 = NL + "\t\tlist.shrink();" + NL + "\t\treturn (";
  protected final String TEXT_143 = "[])list.data();" + NL + "\t}" + NL;
  protected final String TEXT_144 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */";
  protected final String TEXT_145 = NL + "\t";
  protected final String TEXT_146 = " get";
  protected final String TEXT_147 = "(int index);" + NL;
  protected final String TEXT_148 = NL + "\tpublic ";
  protected final String TEXT_149 = " get";
  protected final String TEXT_150 = "(int index)" + NL + "\t{" + NL + "\t\treturn (";
  protected final String TEXT_151 = ")";
  protected final String TEXT_152 = "().get(index);" + NL + "\t}" + NL;
  protected final String TEXT_153 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */";
  protected final String TEXT_154 = NL + "\tint get";
  protected final String TEXT_155 = "Length();" + NL;
  protected final String TEXT_156 = NL + "\tpublic int get";
  protected final String TEXT_157 = "Length()" + NL + "\t{";
  protected final String TEXT_158 = NL + "\t\treturn ";
  protected final String TEXT_159 = "().size();";
  protected final String TEXT_160 = NL + "\t\treturn ";
  protected final String TEXT_161 = " == null ? 0 : ";
  protected final String TEXT_162 = ".size();";
  protected final String TEXT_163 = NL + "\t}" + NL;
  protected final String TEXT_164 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */";
  protected final String TEXT_165 = NL + "\tvoid set";
  protected final String TEXT_166 = "(";
  protected final String TEXT_167 = "[] new";
  protected final String TEXT_168 = ");" + NL;
  protected final String TEXT_169 = NL + "\tpublic void set";
  protected final String TEXT_170 = "(";
  protected final String TEXT_171 = "[] new";
  protected final String TEXT_172 = ")" + NL + "\t{" + NL + "\t\t((";
  protected final String TEXT_173 = ")";
  protected final String TEXT_174 = "()).setData(new";
  protected final String TEXT_175 = ".length, new";
  protected final String TEXT_176 = ");" + NL + "\t}" + NL;
  protected final String TEXT_177 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */";
  protected final String TEXT_178 = NL + "\tvoid set";
  protected final String TEXT_179 = "(int index, ";
  protected final String TEXT_180 = " element);" + NL;
  protected final String TEXT_181 = NL + "\tpublic void set";
  protected final String TEXT_182 = "(int index, ";
  protected final String TEXT_183 = " element)" + NL + "\t{" + NL + "\t\t";
  protected final String TEXT_184 = "().set(index, element);" + NL + "\t}" + NL;
  protected final String TEXT_185 = NL + "\t/**" + NL + "\t * Returns the value of the '<em><b>";
  protected final String TEXT_186 = "</b></em>' ";
  protected final String TEXT_187 = ".";
  protected final String TEXT_188 = NL + "\t * The key is of type ";
  protected final String TEXT_189 = "list of {@link ";
  protected final String TEXT_190 = "}";
  protected final String TEXT_191 = "{@link ";
  protected final String TEXT_192 = "}";
  protected final String TEXT_193 = "," + NL + "\t * and the value is of type ";
  protected final String TEXT_194 = "list of {@link ";
  protected final String TEXT_195 = "}";
  protected final String TEXT_196 = "{@link ";
  protected final String TEXT_197 = "}";
  protected final String TEXT_198 = ",";
  protected final String TEXT_199 = NL + "\t * The list contents are of type {@link ";
  protected final String TEXT_200 = "}.";
  protected final String TEXT_201 = NL + "\t * The default value is <code>";
  protected final String TEXT_202 = "</code>.";
  protected final String TEXT_203 = NL + "\t * The literals are from the enumeration {@link ";
  protected final String TEXT_204 = "}.";
  protected final String TEXT_205 = NL + "\t * It is bidirectional and its opposite is '{@link ";
  protected final String TEXT_206 = "#";
  protected final String TEXT_207 = " <em>";
  protected final String TEXT_208 = "</em>}'.";
  protected final String TEXT_209 = NL + "\t * <!-- begin-user-doc -->";
  protected final String TEXT_210 = NL + "\t * <p>" + NL + "\t * If the meaning of the '<em>";
  protected final String TEXT_211 = "</em>' ";
  protected final String TEXT_212 = " isn't clear," + NL + "\t * there really should be more of a description here..." + NL + "\t * </p>";
  protected final String TEXT_213 = NL + "\t * <!-- end-user-doc -->";
  protected final String TEXT_214 = NL + "\t * <!-- begin-model-doc -->" + NL + "\t * ";
  protected final String TEXT_215 = NL + "\t * <!-- end-model-doc -->";
  protected final String TEXT_216 = NL + "\t * @return the value of the '<em>";
  protected final String TEXT_217 = "</em>' ";
  protected final String TEXT_218 = ".";
  protected final String TEXT_219 = NL + "\t * @see ";
  protected final String TEXT_220 = NL + "\t * @see #isSet";
  protected final String TEXT_221 = "()";
  protected final String TEXT_222 = NL + "\t * @see #unset";
  protected final String TEXT_223 = "()";
  protected final String TEXT_224 = NL + "\t * @see #set";
  protected final String TEXT_225 = "(";
  protected final String TEXT_226 = ")";
  protected final String TEXT_227 = NL + "\t * @see ";
  protected final String TEXT_228 = "#get";
  protected final String TEXT_229 = "()";
  protected final String TEXT_230 = NL + "\t * @see ";
  protected final String TEXT_231 = "#";
  protected final String TEXT_232 = NL + "\t * @model ";
  protected final String TEXT_233 = NL + "\t *        ";
  protected final String TEXT_234 = NL + "\t * @model";
  protected final String TEXT_235 = NL + "\t * @generated" + NL + "\t */";
  protected final String TEXT_236 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */";
  protected final String TEXT_237 = NL + "\t";
  protected final String TEXT_238 = " ";
  protected final String TEXT_239 = "();" + NL;
  protected final String TEXT_240 = NL + "\t@SuppressWarnings(\"unchecked\")";
  protected final String TEXT_241 = NL + "\tpublic ";
  protected final String TEXT_242 = " ";
  protected final String TEXT_243 = "()" + NL + "\t{";
  protected final String TEXT_244 = NL + "\t\treturn ";
  protected final String TEXT_245 = "(";
  protected final String TEXT_246 = "(";
  protected final String TEXT_247 = ")eGet(";
  protected final String TEXT_248 = ", true)";
  protected final String TEXT_249 = ").";
  protected final String TEXT_250 = "()";
  protected final String TEXT_251 = ";";
  protected final String TEXT_252 = NL + "\t\t";
  protected final String TEXT_253 = " ";
  protected final String TEXT_254 = " = (";
  protected final String TEXT_255 = ")eVirtualGet(";
  protected final String TEXT_256 = ");";
  protected final String TEXT_257 = NL + "\t\tif (";
  protected final String TEXT_258 = " == null)" + NL + "\t\t{";
  protected final String TEXT_259 = NL + "\t\t\teVirtualSet(";
  protected final String TEXT_260 = ", ";
  protected final String TEXT_261 = " = new ";
  protected final String TEXT_262 = ");";
  protected final String TEXT_263 = NL + "\t\t\t";
  protected final String TEXT_264 = " = new ";
  protected final String TEXT_265 = ";";
  protected final String TEXT_266 = NL + "\t\t}" + NL + "\t\treturn ";
  protected final String TEXT_267 = ";";
  protected final String TEXT_268 = NL + "\t\tif (eContainerFeatureID != ";
  protected final String TEXT_269 = ") return null;" + NL + "\t\treturn (";
  protected final String TEXT_270 = ")eContainer();";
  protected final String TEXT_271 = NL + "\t\t";
  protected final String TEXT_272 = " ";
  protected final String TEXT_273 = " = (";
  protected final String TEXT_274 = ")eVirtualGet(";
  protected final String TEXT_275 = ", ";
  protected final String TEXT_276 = ");";
  protected final String TEXT_277 = NL + "\t\tif (";
  protected final String TEXT_278 = " != null && ";
  protected final String TEXT_279 = ".eIsProxy())" + NL + "\t\t{" + NL + "\t\t\t";
  protected final String TEXT_280 = " old";
  protected final String TEXT_281 = " = (";
  protected final String TEXT_282 = ")";
  protected final String TEXT_283 = ";" + NL + "\t\t\t";
  protected final String TEXT_284 = " = ";
  protected final String TEXT_285 = "eResolveProxy(old";
  protected final String TEXT_286 = ");" + NL + "\t\t\tif (";
  protected final String TEXT_287 = " != old";
  protected final String TEXT_288 = ")" + NL + "\t\t\t{";
  protected final String TEXT_289 = NL + "\t\t\t\t";
  protected final String TEXT_290 = " new";
  protected final String TEXT_291 = " = (";
  protected final String TEXT_292 = ")";
  protected final String TEXT_293 = ";";
  protected final String TEXT_294 = NL + "\t\t\t\t";
  protected final String TEXT_295 = " msgs = old";
  protected final String TEXT_296 = ".eInverseRemove(this, EOPPOSITE_FEATURE_BASE - ";
  protected final String TEXT_297 = ", null, null);";
  protected final String TEXT_298 = NL + "\t\t\t\t";
  protected final String TEXT_299 = " msgs =  old";
  protected final String TEXT_300 = ".eInverseRemove(this, ";
  protected final String TEXT_301 = ", ";
  protected final String TEXT_302 = ".class, null);";
  protected final String TEXT_303 = NL + "\t\t\t\tif (new";
  protected final String TEXT_304 = ".eInternalContainer() == null)" + NL + "\t\t\t\t{";
  protected final String TEXT_305 = NL + "\t\t\t\t\tmsgs = new";
  protected final String TEXT_306 = ".eInverseAdd(this, EOPPOSITE_FEATURE_BASE - ";
  protected final String TEXT_307 = ", null, msgs);";
  protected final String TEXT_308 = NL + "\t\t\t\t\tmsgs =  new";
  protected final String TEXT_309 = ".eInverseAdd(this, ";
  protected final String TEXT_310 = ", ";
  protected final String TEXT_311 = ".class, msgs);";
  protected final String TEXT_312 = NL + "\t\t\t\t}" + NL + "\t\t\t\tif (msgs != null) msgs.dispatch();";
  protected final String TEXT_313 = NL + "\t\t\t\teVirtualSet(";
  protected final String TEXT_314 = ", ";
  protected final String TEXT_315 = ");";
  protected final String TEXT_316 = NL + "\t\t\t\tif (eNotificationRequired())" + NL + "\t\t\t\t\teNotify(new ";
  protected final String TEXT_317 = "(this, ";
  protected final String TEXT_318 = ".RESOLVE, ";
  protected final String TEXT_319 = ", old";
  protected final String TEXT_320 = ", ";
  protected final String TEXT_321 = "));";
  protected final String TEXT_322 = NL + "\t\t\t}" + NL + "\t\t}";
  protected final String TEXT_323 = NL + "\t\treturn (";
  protected final String TEXT_324 = ")eVirtualGet(";
  protected final String TEXT_325 = ", ";
  protected final String TEXT_326 = ");";
  protected final String TEXT_327 = NL + "\t\treturn (";
  protected final String TEXT_328 = " & ";
  protected final String TEXT_329 = "_EFLAG) != 0;";
  protected final String TEXT_330 = NL + "\t\treturn ";
  protected final String TEXT_331 = ";";
  protected final String TEXT_332 = NL + "\t\t";
  protected final String TEXT_333 = " ";
  protected final String TEXT_334 = " = basicGet";
  protected final String TEXT_335 = "();" + NL + "\t\treturn ";
  protected final String TEXT_336 = " != null && ";
  protected final String TEXT_337 = ".eIsProxy() ? ";
  protected final String TEXT_338 = "eResolveProxy((";
  protected final String TEXT_339 = ")";
  protected final String TEXT_340 = ") : ";
  protected final String TEXT_341 = ";";
  protected final String TEXT_342 = NL + "\t\treturn new ";
  protected final String TEXT_343 = "((";
  protected final String TEXT_344 = ".Internal)((";
  protected final String TEXT_345 = ".Internal.Wrapper)get";
  protected final String TEXT_346 = "()).featureMap().";
  protected final String TEXT_347 = "list(";
  protected final String TEXT_348 = "));";
  protected final String TEXT_349 = NL + "\t\treturn (";
  protected final String TEXT_350 = ")get";
  protected final String TEXT_351 = "().";
  protected final String TEXT_352 = "list(";
  protected final String TEXT_353 = ");";
  protected final String TEXT_354 = NL + "\t\treturn ((";
  protected final String TEXT_355 = ".Internal.Wrapper)get";
  protected final String TEXT_356 = "()).featureMap().list(";
  protected final String TEXT_357 = ");";
  protected final String TEXT_358 = NL + "\t\treturn get";
  protected final String TEXT_359 = "().list(";
  protected final String TEXT_360 = ");";
  protected final String TEXT_361 = NL + "\t\treturn ";
  protected final String TEXT_362 = "(";
  protected final String TEXT_363 = "(";
  protected final String TEXT_364 = ")((";
  protected final String TEXT_365 = ".Internal.Wrapper)get";
  protected final String TEXT_366 = "()).featureMap().get(";
  protected final String TEXT_367 = ", true)";
  protected final String TEXT_368 = ").";
  protected final String TEXT_369 = "()";
  protected final String TEXT_370 = ";";
  protected final String TEXT_371 = NL + "\t\treturn ";
  protected final String TEXT_372 = "(";
  protected final String TEXT_373 = "(";
  protected final String TEXT_374 = ")get";
  protected final String TEXT_375 = "().get(";
  protected final String TEXT_376 = ", true)";
  protected final String TEXT_377 = ").";
  protected final String TEXT_378 = "()";
  protected final String TEXT_379 = ";";
  protected final String TEXT_380 = NL + "\t\t// TODO: implement this method to return the '";
  protected final String TEXT_381 = "' ";
  protected final String TEXT_382 = NL + "\t\t// Ensure that you remove @generated or mark it @generated NOT";
  protected final String TEXT_383 = NL + "\t\t// The list is expected to implement org.eclipse.emf.ecore.util.InternalEList and org.eclipse.emf.ecore.EStructuralFeature.Setting" + NL + "\t\t// so it's likely that an appropriate subclass of org.eclipse.emf.ecore.util.";
  protected final String TEXT_384 = "EcoreEMap";
  protected final String TEXT_385 = "BasicFeatureMap";
  protected final String TEXT_386 = "EcoreEList";
  protected final String TEXT_387 = " should be used.";
  protected final String TEXT_388 = NL + "\t\tthrow new UnsupportedOperationException();";
  protected final String TEXT_389 = NL + "\t}" + NL;
  protected final String TEXT_390 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */" + NL + "\tpublic ";
  protected final String TEXT_391 = " basicGet";
  protected final String TEXT_392 = "()" + NL + "\t{";
  protected final String TEXT_393 = NL + "\t\tif (eContainerFeatureID != ";
  protected final String TEXT_394 = ") return null;" + NL + "\t\treturn (";
  protected final String TEXT_395 = ")eInternalContainer();";
  protected final String TEXT_396 = NL + "\t\treturn (";
  protected final String TEXT_397 = ")eVirtualGet(";
  protected final String TEXT_398 = ");";
  protected final String TEXT_399 = NL + "\t\treturn ";
  protected final String TEXT_400 = ";";
  protected final String TEXT_401 = NL + "\t\treturn (";
  protected final String TEXT_402 = ")((";
  protected final String TEXT_403 = ".Internal.Wrapper)get";
  protected final String TEXT_404 = "()).featureMap().get(";
  protected final String TEXT_405 = ", false);";
  protected final String TEXT_406 = NL + "\t\treturn (";
  protected final String TEXT_407 = ")get";
  protected final String TEXT_408 = "().get(";
  protected final String TEXT_409 = ", false);";
  protected final String TEXT_410 = NL + "\t\t// TODO: implement this method to return the '";
  protected final String TEXT_411 = "' ";
  protected final String TEXT_412 = NL + "\t\t// -> do not perform proxy resolution" + NL + "\t\t// Ensure that you remove @generated or mark it @generated NOT" + NL + "\t\tthrow new UnsupportedOperationException();";
  protected final String TEXT_413 = NL + "\t}" + NL;
  protected final String TEXT_414 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */" + NL + "\tpublic ";
  protected final String TEXT_415 = " basicSet";
  protected final String TEXT_416 = "(";
  protected final String TEXT_417 = " new";
  protected final String TEXT_418 = ", ";
  protected final String TEXT_419 = " msgs)" + NL + "\t{";
  protected final String TEXT_420 = NL + "\t\tmsgs = eBasicSetContainer((";
  protected final String TEXT_421 = ")new";
  protected final String TEXT_422 = ", ";
  protected final String TEXT_423 = ", msgs);";
  protected final String TEXT_424 = NL + "\t\treturn msgs;";
  protected final String TEXT_425 = NL + "\t\tObject old";
  protected final String TEXT_426 = " = eVirtualSet(";
  protected final String TEXT_427 = ", new";
  protected final String TEXT_428 = ");";
  protected final String TEXT_429 = NL + "\t\t";
  protected final String TEXT_430 = " old";
  protected final String TEXT_431 = " = ";
  protected final String TEXT_432 = ";" + NL + "\t\t";
  protected final String TEXT_433 = " = new";
  protected final String TEXT_434 = ";";
  protected final String TEXT_435 = NL + "\t\tboolean isSetChange = old";
  protected final String TEXT_436 = " == EVIRTUAL_NO_VALUE;";
  protected final String TEXT_437 = NL + "\t\tboolean old";
  protected final String TEXT_438 = "ESet = (";
  protected final String TEXT_439 = " & ";
  protected final String TEXT_440 = "_ESETFLAG) != 0;" + NL + "\t\t";
  protected final String TEXT_441 = " |= ";
  protected final String TEXT_442 = "_ESETFLAG;";
  protected final String TEXT_443 = NL + "\t\tboolean old";
  protected final String TEXT_444 = "ESet = ";
  protected final String TEXT_445 = "ESet;" + NL + "\t\t";
  protected final String TEXT_446 = "ESet = true;";
  protected final String TEXT_447 = NL + "\t\tif (eNotificationRequired())" + NL + "\t\t{";
  protected final String TEXT_448 = NL + "\t\t\t";
  protected final String TEXT_449 = " notification = new ";
  protected final String TEXT_450 = "(this, ";
  protected final String TEXT_451 = ".SET, ";
  protected final String TEXT_452 = ", ";
  protected final String TEXT_453 = "isSetChange ? null : old";
  protected final String TEXT_454 = "old";
  protected final String TEXT_455 = ", new";
  protected final String TEXT_456 = ", ";
  protected final String TEXT_457 = "isSetChange";
  protected final String TEXT_458 = "!old";
  protected final String TEXT_459 = "ESet";
  protected final String TEXT_460 = ");";
  protected final String TEXT_461 = NL + "\t\t\t";
  protected final String TEXT_462 = " notification = new ";
  protected final String TEXT_463 = "(this, ";
  protected final String TEXT_464 = ".SET, ";
  protected final String TEXT_465 = ", ";
  protected final String TEXT_466 = "old";
  protected final String TEXT_467 = " == EVIRTUAL_NO_VALUE ? null : old";
  protected final String TEXT_468 = "old";
  protected final String TEXT_469 = ", new";
  protected final String TEXT_470 = ");";
  protected final String TEXT_471 = NL + "\t\t\tif (msgs == null) msgs = notification; else msgs.add(notification);" + NL + "\t\t}";
  protected final String TEXT_472 = NL + "\t\treturn msgs;";
  protected final String TEXT_473 = NL + "\t\treturn ((";
  protected final String TEXT_474 = ".Internal)((";
  protected final String TEXT_475 = ".Internal.Wrapper)get";
  protected final String TEXT_476 = "()).featureMap()).basicAdd(";
  protected final String TEXT_477 = ", new";
  protected final String TEXT_478 = ", msgs);";
  protected final String TEXT_479 = NL + "\t\treturn ((";
  protected final String TEXT_480 = ".Internal)get";
  protected final String TEXT_481 = "()).basicAdd(";
  protected final String TEXT_482 = ", new";
  protected final String TEXT_483 = ", msgs);";
  protected final String TEXT_484 = NL + "\t\t// TODO: implement this method to set the contained '";
  protected final String TEXT_485 = "' ";
  protected final String TEXT_486 = NL + "\t\t// -> this method is automatically invoked to keep the containment relationship in synch" + NL + "\t\t// -> do not modify other features" + NL + "\t\t// -> return msgs, after adding any generated Notification to it (if it is null, a NotificationChain object must be created first)" + NL + "\t\t// Ensure that you remove @generated or mark it @generated NOT" + NL + "\t\tthrow new UnsupportedOperationException();";
  protected final String TEXT_487 = NL + "\t}" + NL;
  protected final String TEXT_488 = NL + "\t/**" + NL + "\t * Sets the value of the '{@link ";
  protected final String TEXT_489 = "#";
  protected final String TEXT_490 = " <em>";
  protected final String TEXT_491 = "</em>}' ";
  protected final String TEXT_492 = "." + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @param value the new value of the '<em>";
  protected final String TEXT_493 = "</em>' ";
  protected final String TEXT_494 = ".";
  protected final String TEXT_495 = NL + "\t * @see ";
  protected final String TEXT_496 = NL + "\t * @see #isSet";
  protected final String TEXT_497 = "()";
  protected final String TEXT_498 = NL + "\t * @see #unset";
  protected final String TEXT_499 = "()";
  protected final String TEXT_500 = NL + "\t * @see #";
  protected final String TEXT_501 = "()" + NL + "\t * @generated" + NL + "\t */";
  protected final String TEXT_502 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */";
  protected final String TEXT_503 = NL + "\tvoid set";
  protected final String TEXT_504 = "(";
  protected final String TEXT_505 = " value);" + NL;
  protected final String TEXT_506 = NL + "\tpublic void set";
  protected final String TEXT_507 = "(";
  protected final String TEXT_508 = " new";
  protected final String TEXT_509 = ")" + NL + "\t{";
  protected final String TEXT_510 = NL + "\t\teSet(";
  protected final String TEXT_511 = ", ";
  protected final String TEXT_512 = "new ";
  protected final String TEXT_513 = "(";
  protected final String TEXT_514 = "new";
  protected final String TEXT_515 = ")";
  protected final String TEXT_516 = ");";
  protected final String TEXT_517 = NL + "\t\tif (new";
  protected final String TEXT_518 = " != eInternalContainer() || (eContainerFeatureID != ";
  protected final String TEXT_519 = " && new";
  protected final String TEXT_520 = " != null))" + NL + "\t\t{" + NL + "\t\t\tif (";
  protected final String TEXT_521 = ".isAncestor(this, ";
  protected final String TEXT_522 = "new";
  protected final String TEXT_523 = "))" + NL + "\t\t\t\tthrow new ";
  protected final String TEXT_524 = "(\"Recursive containment not allowed for \" + toString());";
  protected final String TEXT_525 = NL + "\t\t\t";
  protected final String TEXT_526 = " msgs = null;" + NL + "\t\t\tif (eInternalContainer() != null)" + NL + "\t\t\t\tmsgs = eBasicRemoveFromContainer(msgs);" + NL + "\t\t\tif (new";
  protected final String TEXT_527 = " != null)" + NL + "\t\t\t\tmsgs = ((";
  protected final String TEXT_528 = ")new";
  protected final String TEXT_529 = ").eInverseAdd(this, ";
  protected final String TEXT_530 = ", ";
  protected final String TEXT_531 = ".class, msgs);" + NL + "\t\t\tmsgs = basicSet";
  protected final String TEXT_532 = "(";
  protected final String TEXT_533 = "new";
  protected final String TEXT_534 = ", msgs);" + NL + "\t\t\tif (msgs != null) msgs.dispatch();" + NL + "\t\t}";
  protected final String TEXT_535 = NL + "\t\telse if (eNotificationRequired())" + NL + "\t\t\teNotify(new ";
  protected final String TEXT_536 = "(this, ";
  protected final String TEXT_537 = ".SET, ";
  protected final String TEXT_538 = ", new";
  protected final String TEXT_539 = ", new";
  protected final String TEXT_540 = "));";
  protected final String TEXT_541 = NL + "\t\t";
  protected final String TEXT_542 = " ";
  protected final String TEXT_543 = " = (";
  protected final String TEXT_544 = ")eVirtualGet(";
  protected final String TEXT_545 = ");";
  protected final String TEXT_546 = NL + "\t\tif (new";
  protected final String TEXT_547 = " != ";
  protected final String TEXT_548 = ")" + NL + "\t\t{" + NL + "\t\t\t";
  protected final String TEXT_549 = " msgs = null;" + NL + "\t\t\tif (";
  protected final String TEXT_550 = " != null)";
  protected final String TEXT_551 = NL + "\t\t\t\tmsgs = ((";
  protected final String TEXT_552 = ")";
  protected final String TEXT_553 = ").eInverseRemove(this, EOPPOSITE_FEATURE_BASE - ";
  protected final String TEXT_554 = ", null, msgs);" + NL + "\t\t\tif (new";
  protected final String TEXT_555 = " != null)" + NL + "\t\t\t\tmsgs = ((";
  protected final String TEXT_556 = ")new";
  protected final String TEXT_557 = ").eInverseAdd(this, EOPPOSITE_FEATURE_BASE - ";
  protected final String TEXT_558 = ", null, msgs);";
  protected final String TEXT_559 = NL + "\t\t\t\tmsgs = ((";
  protected final String TEXT_560 = ")";
  protected final String TEXT_561 = ").eInverseRemove(this, ";
  protected final String TEXT_562 = ", ";
  protected final String TEXT_563 = ".class, msgs);" + NL + "\t\t\tif (new";
  protected final String TEXT_564 = " != null)" + NL + "\t\t\t\tmsgs = ((";
  protected final String TEXT_565 = ")new";
  protected final String TEXT_566 = ").eInverseAdd(this, ";
  protected final String TEXT_567 = ", ";
  protected final String TEXT_568 = ".class, msgs);";
  protected final String TEXT_569 = NL + "\t\t\tmsgs = basicSet";
  protected final String TEXT_570 = "(";
  protected final String TEXT_571 = "new";
  protected final String TEXT_572 = ", msgs);" + NL + "\t\t\tif (msgs != null) msgs.dispatch();" + NL + "\t\t}";
  protected final String TEXT_573 = NL + "\t\telse" + NL + "\t\t{";
  protected final String TEXT_574 = NL + "\t\t\tboolean old";
  protected final String TEXT_575 = "ESet = eVirtualIsSet(";
  protected final String TEXT_576 = ");";
  protected final String TEXT_577 = NL + "\t\t\tboolean old";
  protected final String TEXT_578 = "ESet = (";
  protected final String TEXT_579 = " & ";
  protected final String TEXT_580 = "_ESETFLAG) != 0;";
  protected final String TEXT_581 = NL + "\t\t\t";
  protected final String TEXT_582 = " |= ";
  protected final String TEXT_583 = "_ESETFLAG;";
  protected final String TEXT_584 = NL + "\t\t\tboolean old";
  protected final String TEXT_585 = "ESet = ";
  protected final String TEXT_586 = "ESet;";
  protected final String TEXT_587 = NL + "\t\t\t";
  protected final String TEXT_588 = "ESet = true;";
  protected final String TEXT_589 = NL + "\t\t\tif (eNotificationRequired())" + NL + "\t\t\t\teNotify(new ";
  protected final String TEXT_590 = "(this, ";
  protected final String TEXT_591 = ".SET, ";
  protected final String TEXT_592 = ", new";
  protected final String TEXT_593 = ", new";
  protected final String TEXT_594 = ", !old";
  protected final String TEXT_595 = "ESet));";
  protected final String TEXT_596 = NL + "\t\t}";
  protected final String TEXT_597 = NL + "\t\telse if (eNotificationRequired())" + NL + "\t\t\teNotify(new ";
  protected final String TEXT_598 = "(this, ";
  protected final String TEXT_599 = ".SET, ";
  protected final String TEXT_600 = ", new";
  protected final String TEXT_601 = ", new";
  protected final String TEXT_602 = "));";
  protected final String TEXT_603 = NL + "\t\t";
  protected final String TEXT_604 = " old";
  protected final String TEXT_605 = " = (";
  protected final String TEXT_606 = " & ";
  protected final String TEXT_607 = "_EFLAG) != 0;";
  protected final String TEXT_608 = NL + "\t\tif (new";
  protected final String TEXT_609 = ") ";
  protected final String TEXT_610 = " |= ";
  protected final String TEXT_611 = "_EFLAG; else ";
  protected final String TEXT_612 = " &= ~";
  protected final String TEXT_613 = "_EFLAG;";
  protected final String TEXT_614 = NL + "\t\t";
  protected final String TEXT_615 = " old";
  protected final String TEXT_616 = " = ";
  protected final String TEXT_617 = ";";
  protected final String TEXT_618 = NL + "\t\t";
  protected final String TEXT_619 = " ";
  protected final String TEXT_620 = " = new";
  protected final String TEXT_621 = " == null ? ";
  protected final String TEXT_622 = " : new";
  protected final String TEXT_623 = ";";
  protected final String TEXT_624 = NL + "\t\t";
  protected final String TEXT_625 = " = new";
  protected final String TEXT_626 = " == null ? ";
  protected final String TEXT_627 = " : new";
  protected final String TEXT_628 = ";";
  protected final String TEXT_629 = NL + "\t\t";
  protected final String TEXT_630 = " ";
  protected final String TEXT_631 = " = ";
  protected final String TEXT_632 = "new";
  protected final String TEXT_633 = ";";
  protected final String TEXT_634 = NL + "\t\t";
  protected final String TEXT_635 = " = ";
  protected final String TEXT_636 = "new";
  protected final String TEXT_637 = ";";
  protected final String TEXT_638 = NL + "\t\tObject old";
  protected final String TEXT_639 = " = eVirtualSet(";
  protected final String TEXT_640 = ", ";
  protected final String TEXT_641 = ");";
  protected final String TEXT_642 = NL + "\t\tboolean isSetChange = old";
  protected final String TEXT_643 = " == EVIRTUAL_NO_VALUE;";
  protected final String TEXT_644 = NL + "\t\tboolean old";
  protected final String TEXT_645 = "ESet = (";
  protected final String TEXT_646 = " & ";
  protected final String TEXT_647 = "_ESETFLAG) != 0;";
  protected final String TEXT_648 = NL + "\t\t";
  protected final String TEXT_649 = " |= ";
  protected final String TEXT_650 = "_ESETFLAG;";
  protected final String TEXT_651 = NL + "\t\tboolean old";
  protected final String TEXT_652 = "ESet = ";
  protected final String TEXT_653 = "ESet;";
  protected final String TEXT_654 = NL + "\t\t";
  protected final String TEXT_655 = "ESet = true;";
  protected final String TEXT_656 = NL + "\t\tif (eNotificationRequired())" + NL + "\t\t\teNotify(new ";
  protected final String TEXT_657 = "(this, ";
  protected final String TEXT_658 = ".SET, ";
  protected final String TEXT_659 = ", ";
  protected final String TEXT_660 = "isSetChange ? ";
  protected final String TEXT_661 = " : old";
  protected final String TEXT_662 = "old";
  protected final String TEXT_663 = ", ";
  protected final String TEXT_664 = "new";
  protected final String TEXT_665 = ", ";
  protected final String TEXT_666 = "isSetChange";
  protected final String TEXT_667 = "!old";
  protected final String TEXT_668 = "ESet";
  protected final String TEXT_669 = "));";
  protected final String TEXT_670 = NL + "\t\tif (eNotificationRequired())" + NL + "\t\t\teNotify(new ";
  protected final String TEXT_671 = "(this, ";
  protected final String TEXT_672 = ".SET, ";
  protected final String TEXT_673 = ", ";
  protected final String TEXT_674 = "old";
  protected final String TEXT_675 = " == EVIRTUAL_NO_VALUE ? ";
  protected final String TEXT_676 = " : old";
  protected final String TEXT_677 = "old";
  protected final String TEXT_678 = ", ";
  protected final String TEXT_679 = "new";
  protected final String TEXT_680 = "));";
  protected final String TEXT_681 = NL + "\t\t((";
  protected final String TEXT_682 = ".Internal)((";
  protected final String TEXT_683 = ".Internal.Wrapper)get";
  protected final String TEXT_684 = "()).featureMap()).set(";
  protected final String TEXT_685 = ", ";
  protected final String TEXT_686 = "new ";
  protected final String TEXT_687 = "(";
  protected final String TEXT_688 = "new";
  protected final String TEXT_689 = ")";
  protected final String TEXT_690 = ");";
  protected final String TEXT_691 = NL + "\t\t((";
  protected final String TEXT_692 = ".Internal)get";
  protected final String TEXT_693 = "()).set(";
  protected final String TEXT_694 = ", ";
  protected final String TEXT_695 = "new ";
  protected final String TEXT_696 = "(";
  protected final String TEXT_697 = "new";
  protected final String TEXT_698 = ")";
  protected final String TEXT_699 = ");";
  protected final String TEXT_700 = NL + "\t\t// TODO: implement this method to set the '";
  protected final String TEXT_701 = "' ";
  protected final String TEXT_702 = NL + "\t\t// Ensure that you remove @generated or mark it @generated NOT" + NL + "\t\tthrow new UnsupportedOperationException();";
  protected final String TEXT_703 = NL + "\t}" + NL;
  protected final String TEXT_704 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */" + NL + "\tpublic ";
  protected final String TEXT_705 = " basicUnset";
  protected final String TEXT_706 = "(";
  protected final String TEXT_707 = " msgs)" + NL + "\t{";
  protected final String TEXT_708 = NL + "\t\tObject old";
  protected final String TEXT_709 = " = eVirtualUnset(";
  protected final String TEXT_710 = ");";
  protected final String TEXT_711 = NL + "\t\t";
  protected final String TEXT_712 = " old";
  protected final String TEXT_713 = " = ";
  protected final String TEXT_714 = ";" + NL + "\t\t";
  protected final String TEXT_715 = " = null;";
  protected final String TEXT_716 = NL + "\t\tboolean isSetChange = old";
  protected final String TEXT_717 = " != EVIRTUAL_NO_VALUE;";
  protected final String TEXT_718 = NL + "\t\tboolean old";
  protected final String TEXT_719 = "ESet = (";
  protected final String TEXT_720 = " & ";
  protected final String TEXT_721 = "_ESETFLAG) != 0;" + NL + "\t\t";
  protected final String TEXT_722 = " &= ~";
  protected final String TEXT_723 = "_ESETFLAG;";
  protected final String TEXT_724 = NL + "\t\tboolean old";
  protected final String TEXT_725 = "ESet = ";
  protected final String TEXT_726 = "ESet;" + NL + "\t\t";
  protected final String TEXT_727 = "ESet = false;";
  protected final String TEXT_728 = NL + "\t\tif (eNotificationRequired())" + NL + "\t\t{" + NL + "\t\t\t";
  protected final String TEXT_729 = " notification = new ";
  protected final String TEXT_730 = "(this, ";
  protected final String TEXT_731 = ".UNSET, ";
  protected final String TEXT_732 = ", ";
  protected final String TEXT_733 = "isSetChange ? old";
  protected final String TEXT_734 = " : null";
  protected final String TEXT_735 = "old";
  protected final String TEXT_736 = ", null, ";
  protected final String TEXT_737 = "isSetChange";
  protected final String TEXT_738 = "old";
  protected final String TEXT_739 = "ESet";
  protected final String TEXT_740 = ");" + NL + "\t\t\tif (msgs == null) msgs = notification; else msgs.add(notification);" + NL + "\t\t}" + NL + "\t\treturn msgs;";
  protected final String TEXT_741 = NL + "\t\t// TODO: implement this method to unset the contained '";
  protected final String TEXT_742 = "' ";
  protected final String TEXT_743 = NL + "\t\t// -> this method is automatically invoked to keep the containment relationship in synch" + NL + "\t\t// -> do not modify other features" + NL + "\t\t// -> return msgs, after adding any generated Notification to it (if it is null, a NotificationChain object must be created first)" + NL + "\t\t// Ensure that you remove @generated or mark it @generated NOT" + NL + "\t\tthrow new UnsupportedOperationException();";
  protected final String TEXT_744 = NL + "\t}" + NL;
  protected final String TEXT_745 = NL + "\t/**" + NL + "\t * Unsets the value of the '{@link ";
  protected final String TEXT_746 = "#";
  protected final String TEXT_747 = " <em>";
  protected final String TEXT_748 = "</em>}' ";
  protected final String TEXT_749 = "." + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->";
  protected final String TEXT_750 = NL + "\t * @see #isSet";
  protected final String TEXT_751 = "()";
  protected final String TEXT_752 = NL + "\t * @see #";
  protected final String TEXT_753 = "()";
  protected final String TEXT_754 = NL + "\t * @see #set";
  protected final String TEXT_755 = "(";
  protected final String TEXT_756 = ")";
  protected final String TEXT_757 = NL + "\t * @generated" + NL + "\t */";
  protected final String TEXT_758 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */";
  protected final String TEXT_759 = NL + "\tvoid unset";
  protected final String TEXT_760 = "();" + NL;
  protected final String TEXT_761 = NL + "\tpublic void unset";
  protected final String TEXT_762 = "()" + NL + "\t{";
  protected final String TEXT_763 = NL + "\t\teUnset(";
  protected final String TEXT_764 = ");";
  protected final String TEXT_765 = NL + "\t\t";
  protected final String TEXT_766 = " ";
  protected final String TEXT_767 = " = (";
  protected final String TEXT_768 = ")eVirtualGet(";
  protected final String TEXT_769 = ");";
  protected final String TEXT_770 = NL + "\t\tif (";
  protected final String TEXT_771 = " != null) ((";
  protected final String TEXT_772 = ".Unsettable";
  protected final String TEXT_773 = ")";
  protected final String TEXT_774 = ").unset();";
  protected final String TEXT_775 = NL + "\t\t";
  protected final String TEXT_776 = " ";
  protected final String TEXT_777 = " = (";
  protected final String TEXT_778 = ")eVirtualGet(";
  protected final String TEXT_779 = ");";
  protected final String TEXT_780 = NL + "\t\tif (";
  protected final String TEXT_781 = " != null)" + NL + "\t\t{" + NL + "\t\t\t";
  protected final String TEXT_782 = " msgs = null;";
  protected final String TEXT_783 = NL + "\t\t\tmsgs = ((";
  protected final String TEXT_784 = ")";
  protected final String TEXT_785 = ").eInverseRemove(this, EOPPOSITE_FEATURE_BASE - ";
  protected final String TEXT_786 = ", null, msgs);";
  protected final String TEXT_787 = NL + "\t\t\tmsgs = ((";
  protected final String TEXT_788 = ")";
  protected final String TEXT_789 = ").eInverseRemove(this, ";
  protected final String TEXT_790 = ", ";
  protected final String TEXT_791 = ".class, msgs);";
  protected final String TEXT_792 = NL + "\t\t\tmsgs = basicUnset";
  protected final String TEXT_793 = "(msgs);" + NL + "\t\t\tif (msgs != null) msgs.dispatch();" + NL + "\t\t}" + NL + "\t\telse" + NL + "\t\t{";
  protected final String TEXT_794 = NL + "\t\t\tboolean old";
  protected final String TEXT_795 = "ESet = eVirtualIsSet(";
  protected final String TEXT_796 = ");";
  protected final String TEXT_797 = NL + "\t\t\tboolean old";
  protected final String TEXT_798 = "ESet = (";
  protected final String TEXT_799 = " & ";
  protected final String TEXT_800 = "_ESETFLAG) != 0;";
  protected final String TEXT_801 = NL + "\t\t\t";
  protected final String TEXT_802 = " &= ~";
  protected final String TEXT_803 = "_ESETFLAG;";
  protected final String TEXT_804 = NL + "\t\t\tboolean old";
  protected final String TEXT_805 = "ESet = ";
  protected final String TEXT_806 = "ESet;";
  protected final String TEXT_807 = NL + "\t\t\t";
  protected final String TEXT_808 = "ESet = false;";
  protected final String TEXT_809 = NL + "\t\t\tif (eNotificationRequired())" + NL + "\t\t\t\teNotify(new ";
  protected final String TEXT_810 = "(this, ";
  protected final String TEXT_811 = ".UNSET, ";
  protected final String TEXT_812 = ", null, null, old";
  protected final String TEXT_813 = "ESet));";
  protected final String TEXT_814 = NL + "\t\t}";
  protected final String TEXT_815 = NL + "\t\t";
  protected final String TEXT_816 = " old";
  protected final String TEXT_817 = " = (";
  protected final String TEXT_818 = " & ";
  protected final String TEXT_819 = "_EFLAG) != 0;";
  protected final String TEXT_820 = NL + "\t\tObject old";
  protected final String TEXT_821 = " = eVirtualUnset(";
  protected final String TEXT_822 = ");";
  protected final String TEXT_823 = NL + "\t\t";
  protected final String TEXT_824 = " old";
  protected final String TEXT_825 = " = ";
  protected final String TEXT_826 = ";";
  protected final String TEXT_827 = NL + "\t\tboolean isSetChange = old";
  protected final String TEXT_828 = " != EVIRTUAL_NO_VALUE;";
  protected final String TEXT_829 = NL + "\t\tboolean old";
  protected final String TEXT_830 = "ESet = (";
  protected final String TEXT_831 = " & ";
  protected final String TEXT_832 = "_ESETFLAG) != 0;";
  protected final String TEXT_833 = NL + "\t\tboolean old";
  protected final String TEXT_834 = "ESet = ";
  protected final String TEXT_835 = "ESet;";
  protected final String TEXT_836 = NL + "\t\t";
  protected final String TEXT_837 = " = null;";
  protected final String TEXT_838 = NL + "\t\t";
  protected final String TEXT_839 = " &= ~";
  protected final String TEXT_840 = "_ESETFLAG;";
  protected final String TEXT_841 = NL + "\t\t";
  protected final String TEXT_842 = "ESet = false;";
  protected final String TEXT_843 = NL + "\t\tif (eNotificationRequired())" + NL + "\t\t\teNotify(new ";
  protected final String TEXT_844 = "(this, ";
  protected final String TEXT_845 = ".UNSET, ";
  protected final String TEXT_846 = ", ";
  protected final String TEXT_847 = "isSetChange ? old";
  protected final String TEXT_848 = " : null";
  protected final String TEXT_849 = "old";
  protected final String TEXT_850 = ", null, ";
  protected final String TEXT_851 = "isSetChange";
  protected final String TEXT_852 = "old";
  protected final String TEXT_853 = "ESet";
  protected final String TEXT_854 = "));";
  protected final String TEXT_855 = NL + "\t\tif (";
  protected final String TEXT_856 = ") ";
  protected final String TEXT_857 = " |= ";
  protected final String TEXT_858 = "_EFLAG; else ";
  protected final String TEXT_859 = " &= ~";
  protected final String TEXT_860 = "_EFLAG;";
  protected final String TEXT_861 = NL + "\t\t";
  protected final String TEXT_862 = " = ";
  protected final String TEXT_863 = ";";
  protected final String TEXT_864 = NL + "\t\t";
  protected final String TEXT_865 = " &= ~";
  protected final String TEXT_866 = "_ESETFLAG;";
  protected final String TEXT_867 = NL + "\t\t";
  protected final String TEXT_868 = "ESet = false;";
  protected final String TEXT_869 = NL + "\t\tif (eNotificationRequired())" + NL + "\t\t\teNotify(new ";
  protected final String TEXT_870 = "(this, ";
  protected final String TEXT_871 = ".UNSET, ";
  protected final String TEXT_872 = ", ";
  protected final String TEXT_873 = "isSetChange ? old";
  protected final String TEXT_874 = " : ";
  protected final String TEXT_875 = "old";
  protected final String TEXT_876 = ", ";
  protected final String TEXT_877 = ", ";
  protected final String TEXT_878 = "isSetChange";
  protected final String TEXT_879 = "old";
  protected final String TEXT_880 = "ESet";
  protected final String TEXT_881 = "));";
  protected final String TEXT_882 = NL + "\t\t((";
  protected final String TEXT_883 = ".Internal)((";
  protected final String TEXT_884 = ".Internal.Wrapper)get";
  protected final String TEXT_885 = "()).featureMap()).clear(";
  protected final String TEXT_886 = ");";
  protected final String TEXT_887 = NL + "\t\t((";
  protected final String TEXT_888 = ".Internal)get";
  protected final String TEXT_889 = "()).clear(";
  protected final String TEXT_890 = ");";
  protected final String TEXT_891 = NL + "\t\t// TODO: implement this method to unset the '";
  protected final String TEXT_892 = "' ";
  protected final String TEXT_893 = NL + "\t\t// Ensure that you remove @generated or mark it @generated NOT" + NL + "\t\tthrow new UnsupportedOperationException();";
  protected final String TEXT_894 = NL + "\t}" + NL;
  protected final String TEXT_895 = NL + "\t/**" + NL + "\t * Returns whether the value of the '{@link ";
  protected final String TEXT_896 = "#";
  protected final String TEXT_897 = " <em>";
  protected final String TEXT_898 = "</em>}' ";
  protected final String TEXT_899 = " is set." + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @return whether the value of the '<em>";
  protected final String TEXT_900 = "</em>' ";
  protected final String TEXT_901 = " is set.";
  protected final String TEXT_902 = NL + "\t * @see #unset";
  protected final String TEXT_903 = "()";
  protected final String TEXT_904 = NL + "\t * @see #";
  protected final String TEXT_905 = "()";
  protected final String TEXT_906 = NL + "\t * @see #set";
  protected final String TEXT_907 = "(";
  protected final String TEXT_908 = ")";
  protected final String TEXT_909 = NL + "\t * @generated" + NL + "\t */";
  protected final String TEXT_910 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */";
  protected final String TEXT_911 = NL + "\tboolean isSet";
  protected final String TEXT_912 = "();" + NL;
  protected final String TEXT_913 = NL + "\tpublic boolean isSet";
  protected final String TEXT_914 = "()" + NL + "\t{";
  protected final String TEXT_915 = NL + "\t\treturn eIsSet(";
  protected final String TEXT_916 = ");";
  protected final String TEXT_917 = NL + "\t\t";
  protected final String TEXT_918 = " ";
  protected final String TEXT_919 = " = (";
  protected final String TEXT_920 = ")eVirtualGet(";
  protected final String TEXT_921 = ");";
  protected final String TEXT_922 = NL + "\t\treturn ";
  protected final String TEXT_923 = " != null && ((";
  protected final String TEXT_924 = ".Unsettable";
  protected final String TEXT_925 = ")";
  protected final String TEXT_926 = ").isSet();";
  protected final String TEXT_927 = NL + "\t\treturn eVirtualIsSet(";
  protected final String TEXT_928 = ");";
  protected final String TEXT_929 = NL + "\t\treturn (";
  protected final String TEXT_930 = " & ";
  protected final String TEXT_931 = "_ESETFLAG) != 0;";
  protected final String TEXT_932 = NL + "\t\treturn ";
  protected final String TEXT_933 = "ESet;";
  protected final String TEXT_934 = NL + "\t\treturn !((";
  protected final String TEXT_935 = ".Internal)((";
  protected final String TEXT_936 = ".Internal.Wrapper)get";
  protected final String TEXT_937 = "()).featureMap()).isEmpty(";
  protected final String TEXT_938 = ");";
  protected final String TEXT_939 = NL + "\t\treturn !((";
  protected final String TEXT_940 = ".Internal)get";
  protected final String TEXT_941 = "()).isEmpty(";
  protected final String TEXT_942 = ");";
  protected final String TEXT_943 = NL + "\t\t// TODO: implement this method to return whether the '";
  protected final String TEXT_944 = "' ";
  protected final String TEXT_945 = " is set" + NL + "\t\t// Ensure that you remove @generated or mark it @generated NOT" + NL + "\t\tthrow new UnsupportedOperationException();";
  protected final String TEXT_946 = NL + "\t}" + NL;
  protected final String TEXT_947 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->";
  protected final String TEXT_948 = NL + "\t * <!-- begin-model-doc -->" + NL + "\t * ";
  protected final String TEXT_949 = NL + "\t * <!-- end-model-doc -->";
  protected final String TEXT_950 = NL + "\t * @model ";
  protected final String TEXT_951 = NL + "\t *        ";
  protected final String TEXT_952 = NL + "\t * @model";
  protected final String TEXT_953 = NL + "\t * @generated" + NL + "\t */";
  protected final String TEXT_954 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */";
  protected final String TEXT_955 = NL + "\t";
  protected final String TEXT_956 = " ";
  protected final String TEXT_957 = "(";
  protected final String TEXT_958 = ")";
  protected final String TEXT_959 = ";" + NL;
  protected final String TEXT_960 = NL + "\tpublic ";
  protected final String TEXT_961 = " ";
  protected final String TEXT_962 = "(";
  protected final String TEXT_963 = ")";
  protected final String TEXT_964 = NL + "\t{";
  protected final String TEXT_965 = NL + "\t\t";
  protected final String TEXT_966 = NL + "\t\t// TODO: implement this method" + NL + "\t\t// -> specify the condition that violates the invariant" + NL + "\t\t// -> verify the details of the diagnostic, including severity and message" + NL + "\t\t// Ensure that you remove @generated or mark it @generated NOT" + NL + "\t\tif (false)" + NL + "\t\t{" + NL + "\t\t\tif (";
  protected final String TEXT_967 = " != null)" + NL + "\t\t\t{" + NL + "\t\t\t\t";
  protected final String TEXT_968 = ".add" + NL + "\t\t\t\t\t(new ";
  protected final String TEXT_969 = NL + "\t\t\t\t\t\t(";
  protected final String TEXT_970 = ".ERROR," + NL + "\t\t\t\t\t\t ";
  protected final String TEXT_971 = ".DIAGNOSTIC_SOURCE," + NL + "\t\t\t\t\t\t ";
  protected final String TEXT_972 = ".";
  protected final String TEXT_973 = "," + NL + "\t\t\t\t\t\t ";
  protected final String TEXT_974 = ".INSTANCE.getString(\"_UI_GenericInvariant_diagnostic\", new Object[] { \"";
  protected final String TEXT_975 = "\", ";
  protected final String TEXT_976 = ".getObjectLabel(this, ";
  protected final String TEXT_977 = ") }),";
  protected final String TEXT_978 = NL + "\t\t\t\t\t\t new Object [] { this }));" + NL + "\t\t\t}" + NL + "\t\t\treturn false;" + NL + "\t\t}" + NL + "\t\treturn true;";
  protected final String TEXT_979 = NL + "\t\t// TODO: implement this method" + NL + "\t\t// Ensure that you remove @generated or mark it @generated NOT" + NL + "\t\tthrow new UnsupportedOperationException();";
  protected final String TEXT_980 = NL + "\t}" + NL;
  protected final String TEXT_981 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */";
  protected final String TEXT_982 = NL + "\t@SuppressWarnings(\"unchecked\")";
  protected final String TEXT_983 = NL + "\t@Override";
  protected final String TEXT_984 = NL + "\tpublic ";
  protected final String TEXT_985 = " eInverseAdd(";
  protected final String TEXT_986 = " otherEnd, int featureID, ";
  protected final String TEXT_987 = " msgs)" + NL + "\t{" + NL + "\t\tswitch (featureID)" + NL + "\t\t{";
  protected final String TEXT_988 = NL + "\t\t\tcase ";
  protected final String TEXT_989 = ":";
  protected final String TEXT_990 = NL + "\t\t\t\treturn ((";
  protected final String TEXT_991 = "(";
  protected final String TEXT_992 = ".InternalMapView";
  protected final String TEXT_993 = ")";
  protected final String TEXT_994 = "()).eMap()).basicAdd(otherEnd, msgs);";
  protected final String TEXT_995 = NL + "\t\t\t\treturn (";
  protected final String TEXT_996 = "()).basicAdd(otherEnd, msgs);";
  protected final String TEXT_997 = NL + "\t\t\t\tif (eInternalContainer() != null)" + NL + "\t\t\t\t\tmsgs = eBasicRemoveFromContainer(msgs);";
  protected final String TEXT_998 = NL + "\t\t\t\treturn basicSet";
  protected final String TEXT_999 = "((";
  protected final String TEXT_1000 = ")otherEnd, msgs);";
  protected final String TEXT_1001 = NL + "\t\t\t\treturn eBasicSetContainer(otherEnd, ";
  protected final String TEXT_1002 = ", msgs);";
  protected final String TEXT_1003 = NL + "\t\t\t\t";
  protected final String TEXT_1004 = " ";
  protected final String TEXT_1005 = " = (";
  protected final String TEXT_1006 = ")eVirtualGet(";
  protected final String TEXT_1007 = ");";
  protected final String TEXT_1008 = NL + "\t\t\t\tif (";
  protected final String TEXT_1009 = " != null)";
  protected final String TEXT_1010 = NL + "\t\t\t\t\tmsgs = ((";
  protected final String TEXT_1011 = ")";
  protected final String TEXT_1012 = ").eInverseRemove(this, EOPPOSITE_FEATURE_BASE - ";
  protected final String TEXT_1013 = ", null, msgs);";
  protected final String TEXT_1014 = NL + "\t\t\t\t\tmsgs = ((";
  protected final String TEXT_1015 = ")";
  protected final String TEXT_1016 = ").eInverseRemove(this, ";
  protected final String TEXT_1017 = ", ";
  protected final String TEXT_1018 = ".class, msgs);";
  protected final String TEXT_1019 = NL + "\t\t\t\treturn basicSet";
  protected final String TEXT_1020 = "((";
  protected final String TEXT_1021 = ")otherEnd, msgs);";
  protected final String TEXT_1022 = NL + "\t\t}";
  protected final String TEXT_1023 = NL + "\t\treturn super.eInverseAdd(otherEnd, featureID, msgs);";
  protected final String TEXT_1024 = NL + "\t\treturn eDynamicInverseAdd(otherEnd, featureID, msgs);";
  protected final String TEXT_1025 = NL + "\t}" + NL;
  protected final String TEXT_1026 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */";
  protected final String TEXT_1027 = NL + "\t@Override";
  protected final String TEXT_1028 = NL + "\tpublic ";
  protected final String TEXT_1029 = " eInverseRemove(";
  protected final String TEXT_1030 = " otherEnd, int featureID, ";
  protected final String TEXT_1031 = " msgs)" + NL + "\t{" + NL + "\t\tswitch (featureID)" + NL + "\t\t{";
  protected final String TEXT_1032 = NL + "\t\t\tcase ";
  protected final String TEXT_1033 = ":";
  protected final String TEXT_1034 = NL + "\t\t\t\treturn ((";
  protected final String TEXT_1035 = ")((";
  protected final String TEXT_1036 = ".InternalMapView)";
  protected final String TEXT_1037 = "()).eMap()).basicRemove(otherEnd, msgs);";
  protected final String TEXT_1038 = NL + "\t\t\t\treturn ((";
  protected final String TEXT_1039 = ")((";
  protected final String TEXT_1040 = ".Internal.Wrapper)";
  protected final String TEXT_1041 = "()).featureMap()).basicRemove(otherEnd, msgs);";
  protected final String TEXT_1042 = NL + "\t\t\t\treturn ((";
  protected final String TEXT_1043 = ")";
  protected final String TEXT_1044 = "()).basicRemove(otherEnd, msgs);";
  protected final String TEXT_1045 = NL + "\t\t\t\treturn eBasicSetContainer(null, ";
  protected final String TEXT_1046 = ", msgs);";
  protected final String TEXT_1047 = NL + "\t\t\t\treturn basicUnset";
  protected final String TEXT_1048 = "(msgs);";
  protected final String TEXT_1049 = NL + "\t\t\t\treturn basicSet";
  protected final String TEXT_1050 = "(null, msgs);";
  protected final String TEXT_1051 = NL + "\t\t}";
  protected final String TEXT_1052 = NL + "\t\treturn super.eInverseRemove(otherEnd, featureID, msgs);";
  protected final String TEXT_1053 = NL + "\t\treturn eDynamicInverseRemove(otherEnd, featureID, msgs);";
  protected final String TEXT_1054 = NL + "\t}" + NL;
  protected final String TEXT_1055 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */";
  protected final String TEXT_1056 = NL + "\t@Override";
  protected final String TEXT_1057 = NL + "\tpublic ";
  protected final String TEXT_1058 = " eBasicRemoveFromContainerFeature(";
  protected final String TEXT_1059 = " msgs)" + NL + "\t{" + NL + "\t\tswitch (eContainerFeatureID)" + NL + "\t\t{";
  protected final String TEXT_1060 = NL + "\t\t\tcase ";
  protected final String TEXT_1061 = ":" + NL + "\t\t\t\treturn eInternalContainer().eInverseRemove(this, ";
  protected final String TEXT_1062 = ", ";
  protected final String TEXT_1063 = ".class, msgs);";
  protected final String TEXT_1064 = NL + "\t\t}";
  protected final String TEXT_1065 = NL + "\t\treturn super.eBasicRemoveFromContainerFeature(msgs);";
  protected final String TEXT_1066 = NL + "\t\treturn eDynamicBasicRemoveFromContainer(msgs);";
  protected final String TEXT_1067 = NL + "\t}" + NL;
  protected final String TEXT_1068 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */";
  protected final String TEXT_1069 = NL + "\t@Override";
  protected final String TEXT_1070 = NL + "\tpublic Object eGet(int featureID, boolean resolve, boolean coreType)" + NL + "\t{" + NL + "\t\tswitch (featureID)" + NL + "\t\t{";
  protected final String TEXT_1071 = NL + "\t\t\tcase ";
  protected final String TEXT_1072 = ":";
  protected final String TEXT_1073 = NL + "\t\t\t\treturn ";
  protected final String TEXT_1074 = "() ? Boolean.TRUE : Boolean.FALSE;";
  protected final String TEXT_1075 = NL + "\t\t\t\treturn new ";
  protected final String TEXT_1076 = "(";
  protected final String TEXT_1077 = "());";
  protected final String TEXT_1078 = NL + "\t\t\t\tif (resolve) return ";
  protected final String TEXT_1079 = "();" + NL + "\t\t\t\treturn basicGet";
  protected final String TEXT_1080 = "();";
  protected final String TEXT_1081 = NL + "\t\t\t\tif (coreType) return ((";
  protected final String TEXT_1082 = ".InternalMapView)";
  protected final String TEXT_1083 = "()).eMap();" + NL + "\t\t\t\telse return ";
  protected final String TEXT_1084 = "();";
  protected final String TEXT_1085 = NL + "\t\t\t\tif (coreType) return ";
  protected final String TEXT_1086 = "();" + NL + "\t\t\t\telse return ";
  protected final String TEXT_1087 = "().map();";
  protected final String TEXT_1088 = NL + "\t\t\t\tif (coreType) return ((";
  protected final String TEXT_1089 = ".Internal.Wrapper)";
  protected final String TEXT_1090 = "()).featureMap();" + NL + "\t\t\t\treturn ";
  protected final String TEXT_1091 = "();";
  protected final String TEXT_1092 = NL + "\t\t\t\tif (coreType) return ";
  protected final String TEXT_1093 = "();" + NL + "\t\t\t\treturn ((";
  protected final String TEXT_1094 = ".Internal)";
  protected final String TEXT_1095 = "()).getWrapper();";
  protected final String TEXT_1096 = NL + "\t\t\t\treturn ";
  protected final String TEXT_1097 = "();";
  protected final String TEXT_1098 = NL + "\t\t}";
  protected final String TEXT_1099 = NL + "\t\treturn super.eGet(featureID, resolve, coreType);";
  protected final String TEXT_1100 = NL + "\t\treturn eDynamicGet(featureID, resolve, coreType);";
  protected final String TEXT_1101 = NL + "\t}" + NL;
  protected final String TEXT_1102 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */";
  protected final String TEXT_1103 = NL + "\t@SuppressWarnings(\"unchecked\")";
  protected final String TEXT_1104 = NL + "\t@Override";
  protected final String TEXT_1105 = NL + "\tpublic void eSet(int featureID, Object newValue)" + NL + "\t{" + NL + "\t\tswitch (featureID)" + NL + "\t\t{";
  protected final String TEXT_1106 = NL + "\t\t\tcase ";
  protected final String TEXT_1107 = ":";
  protected final String TEXT_1108 = NL + "\t\t\t\t((";
  protected final String TEXT_1109 = ".Internal)((";
  protected final String TEXT_1110 = ".Internal.Wrapper)";
  protected final String TEXT_1111 = "()).featureMap()).set(newValue);";
  protected final String TEXT_1112 = NL + "\t\t\t\t((";
  protected final String TEXT_1113 = ".Internal)";
  protected final String TEXT_1114 = "()).set(newValue);";
  protected final String TEXT_1115 = NL + "\t\t\t\t((";
  protected final String TEXT_1116 = ".Setting)((";
  protected final String TEXT_1117 = ".InternalMapView)";
  protected final String TEXT_1118 = "()).eMap()).set(newValue);";
  protected final String TEXT_1119 = NL + "\t\t\t\t((";
  protected final String TEXT_1120 = ".Setting)";
  protected final String TEXT_1121 = "()).set(newValue);";
  protected final String TEXT_1122 = NL + "\t\t\t\t";
  protected final String TEXT_1123 = "().clear();" + NL + "\t\t\t\t";
  protected final String TEXT_1124 = "().addAll((";
  protected final String TEXT_1125 = "<? extends ";
  protected final String TEXT_1126 = ">";
  protected final String TEXT_1127 = ")newValue);";
  protected final String TEXT_1128 = NL + "\t\t\t\tset";
  protected final String TEXT_1129 = "(((";
  protected final String TEXT_1130 = ")newValue).";
  protected final String TEXT_1131 = "());";
  protected final String TEXT_1132 = NL + "\t\t\t\tset";
  protected final String TEXT_1133 = "(";
  protected final String TEXT_1134 = "(";
  protected final String TEXT_1135 = ")";
  protected final String TEXT_1136 = "newValue);";
  protected final String TEXT_1137 = NL + "\t\t\t\treturn;";
  protected final String TEXT_1138 = NL + "\t\t}";
  protected final String TEXT_1139 = NL + "\t\tsuper.eSet(featureID, newValue);";
  protected final String TEXT_1140 = NL + "\t\teDynamicSet(featureID, newValue);";
  protected final String TEXT_1141 = NL + "\t}" + NL + "" + NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */";
  protected final String TEXT_1142 = NL + "\t@Override";
  protected final String TEXT_1143 = NL + "\tpublic void eUnset(int featureID)" + NL + "\t{" + NL + "\t\tswitch (featureID)" + NL + "\t\t{";
  protected final String TEXT_1144 = NL + "\t\t\tcase ";
  protected final String TEXT_1145 = ":";
  protected final String TEXT_1146 = NL + "\t\t\t\t((";
  protected final String TEXT_1147 = ".Internal.Wrapper)";
  protected final String TEXT_1148 = "()).featureMap().clear();";
  protected final String TEXT_1149 = NL + "\t\t\t\t";
  protected final String TEXT_1150 = "().clear();";
  protected final String TEXT_1151 = NL + "\t\t\t\tunset";
  protected final String TEXT_1152 = "();";
  protected final String TEXT_1153 = NL + "\t\t\t\tset";
  protected final String TEXT_1154 = "((";
  protected final String TEXT_1155 = ")null);";
  protected final String TEXT_1156 = NL + "\t\t\t\tset";
  protected final String TEXT_1157 = "(";
  protected final String TEXT_1158 = ");";
  protected final String TEXT_1159 = NL + "\t\t\t\treturn;";
  protected final String TEXT_1160 = NL + "\t\t}";
  protected final String TEXT_1161 = NL + "\t\tsuper.eUnset(featureID);";
  protected final String TEXT_1162 = NL + "\t\teDynamicUnset(featureID);";
  protected final String TEXT_1163 = NL + "\t}" + NL;
  protected final String TEXT_1164 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */";
  protected final String TEXT_1165 = NL + "\t@Override";
  protected final String TEXT_1166 = NL + "\tpublic boolean eIsSet(int featureID)" + NL + "\t{" + NL + "\t\tswitch (featureID)" + NL + "\t\t{";
  protected final String TEXT_1167 = NL + "\t\t\tcase ";
  protected final String TEXT_1168 = ":";
  protected final String TEXT_1169 = NL + "\t\t\t\treturn !((";
  protected final String TEXT_1170 = ".Internal.Wrapper)";
  protected final String TEXT_1171 = "()).featureMap().isEmpty();";
  protected final String TEXT_1172 = NL + "\t\t\t\treturn ";
  protected final String TEXT_1173 = " != null && !";
  protected final String TEXT_1174 = ".featureMap().isEmpty();";
  protected final String TEXT_1175 = NL + "\t\t\t\treturn ";
  protected final String TEXT_1176 = " != null && !";
  protected final String TEXT_1177 = ".isEmpty();";
  protected final String TEXT_1178 = NL + "\t\t\t\t";
  protected final String TEXT_1179 = " ";
  protected final String TEXT_1180 = " = (";
  protected final String TEXT_1181 = ")eVirtualGet(";
  protected final String TEXT_1182 = ");" + NL + "\t\t\t\treturn ";
  protected final String TEXT_1183 = " != null && !";
  protected final String TEXT_1184 = ".isEmpty();";
  protected final String TEXT_1185 = NL + "\t\t\t\treturn !";
  protected final String TEXT_1186 = "().isEmpty();";
  protected final String TEXT_1187 = NL + "\t\t\t\treturn isSet";
  protected final String TEXT_1188 = "();";
  protected final String TEXT_1189 = NL + "\t\t\t\treturn ";
  protected final String TEXT_1190 = " != null;";
  protected final String TEXT_1191 = NL + "\t\t\t\treturn eVirtualGet(";
  protected final String TEXT_1192 = ") != null;";
  protected final String TEXT_1193 = NL + "\t\t\t\treturn basicGet";
  protected final String TEXT_1194 = "() != null;";
  protected final String TEXT_1195 = NL + "\t\t\t\treturn ";
  protected final String TEXT_1196 = " != null;";
  protected final String TEXT_1197 = NL + "\t\t\t\treturn eVirtualGet(";
  protected final String TEXT_1198 = ") != null;";
  protected final String TEXT_1199 = NL + "\t\t\t\treturn ";
  protected final String TEXT_1200 = "() != null;";
  protected final String TEXT_1201 = NL + "\t\t\t\treturn ((";
  protected final String TEXT_1202 = " & ";
  protected final String TEXT_1203 = "_EFLAG) != 0) != ";
  protected final String TEXT_1204 = ";";
  protected final String TEXT_1205 = NL + "\t\t\t\treturn ";
  protected final String TEXT_1206 = " != ";
  protected final String TEXT_1207 = ";";
  protected final String TEXT_1208 = NL + "\t\t\t\treturn eVirtualGet(";
  protected final String TEXT_1209 = ", ";
  protected final String TEXT_1210 = ") != ";
  protected final String TEXT_1211 = ";";
  protected final String TEXT_1212 = NL + "\t\t\t\treturn ";
  protected final String TEXT_1213 = "() != ";
  protected final String TEXT_1214 = ";";
  protected final String TEXT_1215 = NL + "\t\t\t\treturn ";
  protected final String TEXT_1216 = " == null ? ";
  protected final String TEXT_1217 = " != null : !";
  protected final String TEXT_1218 = ".equals(";
  protected final String TEXT_1219 = ");";
  protected final String TEXT_1220 = NL + "\t\t\t\t";
  protected final String TEXT_1221 = " ";
  protected final String TEXT_1222 = " = (";
  protected final String TEXT_1223 = ")eVirtualGet(";
  protected final String TEXT_1224 = ", ";
  protected final String TEXT_1225 = ");" + NL + "\t\t\t\treturn ";
  protected final String TEXT_1226 = " == null ? ";
  protected final String TEXT_1227 = " != null : !";
  protected final String TEXT_1228 = ".equals(";
  protected final String TEXT_1229 = ");";
  protected final String TEXT_1230 = NL + "\t\t\t\treturn ";
  protected final String TEXT_1231 = " == null ? ";
  protected final String TEXT_1232 = "() != null : !";
  protected final String TEXT_1233 = ".equals(";
  protected final String TEXT_1234 = "());";
  protected final String TEXT_1235 = NL + "\t\t}";
  protected final String TEXT_1236 = NL + "\t\treturn super.eIsSet(featureID);";
  protected final String TEXT_1237 = NL + "\t\treturn eDynamicIsSet(featureID);";
  protected final String TEXT_1238 = NL + "\t}" + NL;
  protected final String TEXT_1239 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */";
  protected final String TEXT_1240 = NL + "\t@Override";
  protected final String TEXT_1241 = NL + "\tpublic int eBaseStructuralFeatureID(int derivedFeatureID, Class";
  protected final String TEXT_1242 = " baseClass)" + NL + "\t{";
  protected final String TEXT_1243 = NL + "\t\tif (baseClass == ";
  protected final String TEXT_1244 = ".class)" + NL + "\t\t{" + NL + "\t\t\tswitch (derivedFeatureID)" + NL + "\t\t\t{";
  protected final String TEXT_1245 = NL + "\t\t\t\tcase ";
  protected final String TEXT_1246 = ": return ";
  protected final String TEXT_1247 = ";";
  protected final String TEXT_1248 = NL + "\t\t\t\tdefault: return -1;" + NL + "\t\t\t}" + NL + "\t\t}";
  protected final String TEXT_1249 = NL + "\t\treturn super.eBaseStructuralFeatureID(derivedFeatureID, baseClass);" + NL + "\t}" + NL + "" + NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */";
  protected final String TEXT_1250 = NL + "\t@Override";
  protected final String TEXT_1251 = NL + "\tpublic int eDerivedStructuralFeatureID(int baseFeatureID, Class";
  protected final String TEXT_1252 = " baseClass)" + NL + "\t{";
  protected final String TEXT_1253 = NL + "\t\tif (baseClass == ";
  protected final String TEXT_1254 = ".class)" + NL + "\t\t{" + NL + "\t\t\tswitch (baseFeatureID)" + NL + "\t\t\t{";
  protected final String TEXT_1255 = NL + "\t\t\t\tcase ";
  protected final String TEXT_1256 = ": return ";
  protected final String TEXT_1257 = ";";
  protected final String TEXT_1258 = NL + "\t\t\t\tdefault: return -1;" + NL + "\t\t\t}" + NL + "\t\t}";
  protected final String TEXT_1259 = NL + "\t\treturn super.eDerivedStructuralFeatureID(baseFeatureID, baseClass);" + NL + "\t}" + NL;
  protected final String TEXT_1260 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */";
  protected final String TEXT_1261 = NL + "\t@Override";
  protected final String TEXT_1262 = NL + "\tprotected Object[] eVirtualValues()" + NL + "\t{" + NL + "\t\treturn ";
  protected final String TEXT_1263 = ";" + NL + "\t}" + NL + "" + NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */";
  protected final String TEXT_1264 = NL + "\t@Override";
  protected final String TEXT_1265 = NL + "\tprotected void eSetVirtualValues(Object[] newValues)" + NL + "\t{" + NL + "\t\t";
  protected final String TEXT_1266 = " = newValues;" + NL + "\t}" + NL;
  protected final String TEXT_1267 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */";
  protected final String TEXT_1268 = NL + "\t@Override";
  protected final String TEXT_1269 = NL + "\tprotected int eVirtualIndexBits(int offset)" + NL + "\t{" + NL + "\t\tswitch (offset)" + NL + "\t\t{";
  protected final String TEXT_1270 = NL + "\t\t\tcase ";
  protected final String TEXT_1271 = " :" + NL + "\t\t\t\treturn ";
  protected final String TEXT_1272 = ";";
  protected final String TEXT_1273 = NL + "\t\t\tdefault :" + NL + "\t\t\t\tthrow new IndexOutOfBoundsException();" + NL + "\t\t}" + NL + "\t}" + NL + "" + NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */";
  protected final String TEXT_1274 = NL + "\t@Override";
  protected final String TEXT_1275 = NL + "\tprotected void eSetVirtualIndexBits(int offset, int newIndexBits)" + NL + "\t{" + NL + "\t\tswitch (offset)" + NL + "\t\t{";
  protected final String TEXT_1276 = NL + "\t\t\tcase ";
  protected final String TEXT_1277 = " :" + NL + "\t\t\t\t";
  protected final String TEXT_1278 = " = newIndexBits;" + NL + "\t\t\t\tbreak;";
  protected final String TEXT_1279 = NL + "\t\t\tdefault :" + NL + "\t\t\t\tthrow new IndexOutOfBoundsException();" + NL + "\t\t}" + NL + "\t}" + NL;
  protected final String TEXT_1280 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */";
  protected final String TEXT_1281 = NL + "\t@Override";
  protected final String TEXT_1282 = NL + "\tpublic String toString()" + NL + "\t{" + NL + "\t\tif (eIsProxy()) return super.toString();" + NL + "" + NL + "\t\tStringBuffer result = new StringBuffer(super.toString());";
  protected final String TEXT_1283 = NL + "\t\tresult.append(\" (";
  protected final String TEXT_1284 = ": \");";
  protected final String TEXT_1285 = NL + "\t\tresult.append(\", ";
  protected final String TEXT_1286 = ": \");";
  protected final String TEXT_1287 = NL + "\t\tif (eVirtualIsSet(";
  protected final String TEXT_1288 = ")) result.append(eVirtualGet(";
  protected final String TEXT_1289 = ")); else result.append(\"<unset>\");";
  protected final String TEXT_1290 = NL + "\t\tif (";
  protected final String TEXT_1291 = "(";
  protected final String TEXT_1292 = " & ";
  protected final String TEXT_1293 = "_ESETFLAG) != 0";
  protected final String TEXT_1294 = "ESet";
  protected final String TEXT_1295 = ") result.append((";
  protected final String TEXT_1296 = " & ";
  protected final String TEXT_1297 = "_EFLAG) != 0); else result.append(\"<unset>\");";
  protected final String TEXT_1298 = NL + "\t\tif (";
  protected final String TEXT_1299 = "(";
  protected final String TEXT_1300 = " & ";
  protected final String TEXT_1301 = "_ESETFLAG) != 0";
  protected final String TEXT_1302 = "ESet";
  protected final String TEXT_1303 = ") result.append(";
  protected final String TEXT_1304 = "); else result.append(\"<unset>\");";
  protected final String TEXT_1305 = NL + "\t\tresult.append(eVirtualGet(";
  protected final String TEXT_1306 = ", ";
  protected final String TEXT_1307 = "));";
  protected final String TEXT_1308 = NL + "\t\tresult.append((";
  protected final String TEXT_1309 = " & ";
  protected final String TEXT_1310 = "_EFLAG) != 0);";
  protected final String TEXT_1311 = NL + "\t\tresult.append(";
  protected final String TEXT_1312 = ");";
  protected final String TEXT_1313 = NL + "\t\tresult.append(')');" + NL + "\t\treturn result.toString();" + NL + "\t}" + NL;
  protected final String TEXT_1314 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */" + NL + "\tprotected int hash = -1;" + NL + "" + NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */" + NL + "\tpublic int getHash()" + NL + "\t{" + NL + "\t\tif (hash == -1)" + NL + "\t\t{" + NL + "\t\t\t";
  protected final String TEXT_1315 = " theKey = getKey();" + NL + "\t\t\thash = (theKey == null ? 0 : theKey.hashCode());" + NL + "\t\t}" + NL + "\t\treturn hash;" + NL + "\t}" + NL + "" + NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */" + NL + "\tpublic void setHash(int hash)" + NL + "\t{" + NL + "\t\tthis.hash = hash;" + NL + "\t}" + NL + "" + NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */" + NL + "\tpublic ";
  protected final String TEXT_1316 = " getKey()" + NL + "\t{";
  protected final String TEXT_1317 = NL + "\t\treturn new ";
  protected final String TEXT_1318 = "(getTypedKey());";
  protected final String TEXT_1319 = NL + "\t\treturn getTypedKey();";
  protected final String TEXT_1320 = NL + "\t}" + NL + "" + NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */" + NL + "\tpublic void setKey(";
  protected final String TEXT_1321 = " key)" + NL + "\t{";
  protected final String TEXT_1322 = NL + "\t\tgetTypedKey().addAll((";
  protected final String TEXT_1323 = ")key);";
  protected final String TEXT_1324 = NL + "\t\tsetTypedKey(key);";
  protected final String TEXT_1325 = NL + "\t\tsetTypedKey(((";
  protected final String TEXT_1326 = ")key).";
  protected final String TEXT_1327 = "());";
  protected final String TEXT_1328 = NL + "\t\tsetTypedKey((";
  protected final String TEXT_1329 = ")key);";
  protected final String TEXT_1330 = NL + "\t}" + NL + "" + NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */" + NL + "\tpublic ";
  protected final String TEXT_1331 = " getValue()" + NL + "\t{";
  protected final String TEXT_1332 = NL + "\t\treturn new ";
  protected final String TEXT_1333 = "(getTypedValue());";
  protected final String TEXT_1334 = NL + "\t\treturn getTypedValue();";
  protected final String TEXT_1335 = NL + "\t}" + NL + "" + NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */" + NL + "\tpublic ";
  protected final String TEXT_1336 = " setValue(";
  protected final String TEXT_1337 = " value)" + NL + "\t{" + NL + "\t\t";
  protected final String TEXT_1338 = " oldValue = getValue();";
  protected final String TEXT_1339 = NL + "\t\tgetTypedValue().clear();" + NL + "\t\tgetTypedValue().addAll((";
  protected final String TEXT_1340 = ")value);";
  protected final String TEXT_1341 = NL + "\t\tsetTypedValue(value);";
  protected final String TEXT_1342 = NL + "\t\tsetTypedValue(((";
  protected final String TEXT_1343 = ")value).";
  protected final String TEXT_1344 = "());";
  protected final String TEXT_1345 = NL + "\t\tsetTypedValue((";
  protected final String TEXT_1346 = ")value);";
  protected final String TEXT_1347 = NL + "\t\treturn oldValue;" + NL + "\t}" + NL + "" + NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */";
  protected final String TEXT_1348 = NL + "\t@SuppressWarnings(\"unchecked\")";
  protected final String TEXT_1349 = NL + "\tpublic ";
  protected final String TEXT_1350 = " getEMap()" + NL + "\t{" + NL + "\t\t";
  protected final String TEXT_1351 = " container = eContainer();" + NL + "\t\treturn container == null ? null : (";
  protected final String TEXT_1352 = ")container.eGet(eContainmentFeature());" + NL + "\t}" + NL;
  protected final String TEXT_1353 = NL + "} //";
  protected final String TEXT_1354 = NL;

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
    if (genFeature.hasEDefault() && (!genFeature.isVolatile() || !genModel.isReflectiveDelegation() && (!genFeature.hasDelegateFeature() || !genFeature.isUnsettable()))) { String staticDefaultValue = genFeature.getStaticDefaultValue();
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
    if ("".equals(staticDefaultValue)) {
    stringBuffer.append(TEXT_80);
    stringBuffer.append(genFeature.getEcoreFeature().getDefaultValueLiteral());
    stringBuffer.append(TEXT_81);
    } else {
    stringBuffer.append(TEXT_82);
    stringBuffer.append(staticDefaultValue);
    stringBuffer.append(TEXT_83);
    stringBuffer.append(genModel.getNonNLS(staticDefaultValue));
    }
    stringBuffer.append(TEXT_84);
    }
    if (genClass.isField(genFeature)) {
    if (genClass.isFlag(genFeature)) {
    if (genClass.getFlagIndex(genFeature) > 31 && genClass.getFlagIndex(genFeature) % 32 == 0) {
    stringBuffer.append(TEXT_85);
    stringBuffer.append(genClass.getFlagsField(genFeature));
    stringBuffer.append(TEXT_86);
    }
    stringBuffer.append(TEXT_87);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_88);
    stringBuffer.append(genFeature.getFormattedName());
    stringBuffer.append(TEXT_89);
    stringBuffer.append(genFeature.getFeatureKind());
    stringBuffer.append(TEXT_90);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_91);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_92);
    stringBuffer.append("<< " + genClass.getFlagIndex(genFeature) % 32 );
    stringBuffer.append(TEXT_93);
    } else {
    stringBuffer.append(TEXT_94);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_95);
    stringBuffer.append(genFeature.getFormattedName());
    stringBuffer.append(TEXT_96);
    stringBuffer.append(genFeature.getFeatureKind());
    stringBuffer.append(TEXT_97);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_98);
    stringBuffer.append(genFeature.getImportedType());
    stringBuffer.append(TEXT_99);
    stringBuffer.append(genFeature.getSafeName());
    if (genFeature.hasEDefault()) {
    stringBuffer.append(TEXT_100);
    stringBuffer.append(genFeature.getEDefault());
    }
    stringBuffer.append(TEXT_101);
    }
    }
    }
    if (genClass.isESetField(genFeature)) {
    if (genClass.isESetFlag(genFeature)) {
    if (genClass.getESetFlagIndex(genFeature) > 31 && genClass.getESetFlagIndex(genFeature) % 32 == 0) {
    stringBuffer.append(TEXT_102);
    stringBuffer.append(genClass.getESetFlagsField(genFeature));
    stringBuffer.append(TEXT_103);
    }
    stringBuffer.append(TEXT_104);
    stringBuffer.append(genFeature.getFormattedName());
    stringBuffer.append(TEXT_105);
    stringBuffer.append(genFeature.getFeatureKind());
    stringBuffer.append(TEXT_106);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_107);
    stringBuffer.append("<< " + genClass.getESetFlagIndex(genFeature) % 32 );
    stringBuffer.append(TEXT_108);
    } else {
    stringBuffer.append(TEXT_109);
    stringBuffer.append(genFeature.getFormattedName());
    stringBuffer.append(TEXT_110);
    stringBuffer.append(genFeature.getFeatureKind());
    stringBuffer.append(TEXT_111);
    stringBuffer.append(genFeature.getUncapName());
    stringBuffer.append(TEXT_112);
    }
    }
    }
    //Class/declaredFieldGenFeature.override.javajetinc
    }
    if (isImplementation) {
    stringBuffer.append(TEXT_113);
    stringBuffer.append(genClass.getClassName());
    stringBuffer.append(TEXT_114);
    for (Iterator i=genClass.getFlagGenFeatures("true").iterator(); i.hasNext();) { GenFeature genFeature = (GenFeature)i.next();
    stringBuffer.append(TEXT_115);
    stringBuffer.append(genClass.getFlagsField(genFeature));
    stringBuffer.append(TEXT_116);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_117);
    }
    stringBuffer.append(TEXT_118);
    if (genModel.useClassOverrideAnnotation()) {
    stringBuffer.append(TEXT_119);
    }
    stringBuffer.append(TEXT_120);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.EClass"));
    stringBuffer.append(TEXT_121);
    stringBuffer.append(genClass.getQualifiedClassifierAccessor());
    stringBuffer.append(TEXT_122);
    }
    new Runnable() { public void run() {
    for (Iterator i=(isImplementation ? genClass.getImplementedGenFeatures() : genClass.getDeclaredGenFeatures()).iterator(); i.hasNext();) { GenFeature genFeature = (GenFeature)i.next();
    if (genModel.isArrayAccessors() && genFeature.isListType() && !genFeature.isFeatureMapType() && !genFeature.isMapType()) {
    stringBuffer.append(TEXT_123);
    if (!isImplementation) {
    stringBuffer.append(TEXT_124);
    stringBuffer.append(genFeature.getListItemType());
    stringBuffer.append(TEXT_125);
    stringBuffer.append(genFeature.getGetArrayAccessor());
    stringBuffer.append(TEXT_126);
    } else {
    stringBuffer.append(TEXT_127);
    stringBuffer.append(genFeature.getListItemType());
    stringBuffer.append(TEXT_128);
    stringBuffer.append(genFeature.getGetArrayAccessor());
    stringBuffer.append(TEXT_129);
    if (genFeature.isVolatile()) {
    stringBuffer.append(TEXT_130);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.util.BasicEList"));
    stringBuffer.append(TEXT_131);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.util.BasicEList"));
    stringBuffer.append(TEXT_132);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_133);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_134);
    } else {
    stringBuffer.append(TEXT_135);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_136);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_137);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_138);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.util.BasicEList"));
    stringBuffer.append(TEXT_139);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.util.BasicEList"));
    stringBuffer.append(TEXT_140);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_141);
    }
    stringBuffer.append(TEXT_142);
    stringBuffer.append(genFeature.getListItemType());
    stringBuffer.append(TEXT_143);
    }
    stringBuffer.append(TEXT_144);
    if (!isImplementation) {
    stringBuffer.append(TEXT_145);
    stringBuffer.append(genFeature.getListItemType());
    stringBuffer.append(TEXT_146);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_147);
    } else {
    stringBuffer.append(TEXT_148);
    stringBuffer.append(genFeature.getListItemType());
    stringBuffer.append(TEXT_149);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_150);
    stringBuffer.append(genFeature.getListItemType());
    stringBuffer.append(TEXT_151);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_152);
    }
    stringBuffer.append(TEXT_153);
    if (!isImplementation) {
    stringBuffer.append(TEXT_154);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_155);
    } else {
    stringBuffer.append(TEXT_156);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_157);
    if (genFeature.isVolatile()) {
    stringBuffer.append(TEXT_158);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_159);
    } else {
    stringBuffer.append(TEXT_160);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_161);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_162);
    }
    stringBuffer.append(TEXT_163);
    }
    stringBuffer.append(TEXT_164);
    if (!isImplementation) {
    stringBuffer.append(TEXT_165);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_166);
    stringBuffer.append(genFeature.getListItemType());
    stringBuffer.append(TEXT_167);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_168);
    } else {
    stringBuffer.append(TEXT_169);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_170);
    stringBuffer.append(genFeature.getListItemType());
    stringBuffer.append(TEXT_171);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_172);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.util.BasicEList"));
    stringBuffer.append(TEXT_173);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_174);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_175);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_176);
    }
    stringBuffer.append(TEXT_177);
    if (!isImplementation) {
    stringBuffer.append(TEXT_178);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_179);
    stringBuffer.append(genFeature.getListItemType());
    stringBuffer.append(TEXT_180);
    } else {
    stringBuffer.append(TEXT_181);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_182);
    stringBuffer.append(genFeature.getListItemType());
    stringBuffer.append(TEXT_183);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_184);
    }
    }
    if (genFeature.isGet() && (isImplementation || !genFeature.isSuppressedGetVisibility())) {
    if (isInterface) {
    stringBuffer.append(TEXT_185);
    stringBuffer.append(genFeature.getFormattedName());
    stringBuffer.append(TEXT_186);
    stringBuffer.append(genFeature.getFeatureKind());
    stringBuffer.append(TEXT_187);
    if (genFeature.isListType()) {
    if (genFeature.isMapType()) { GenFeature keyFeature = genFeature.getMapEntryTypeGenClass().getMapEntryKeyFeature(); GenFeature valueFeature = genFeature.getMapEntryTypeGenClass().getMapEntryValueFeature(); 
    stringBuffer.append(TEXT_188);
    if (keyFeature.isListType()) {
    stringBuffer.append(TEXT_189);
    stringBuffer.append(keyFeature.getQualifiedListItemType());
    stringBuffer.append(TEXT_190);
    } else {
    stringBuffer.append(TEXT_191);
    stringBuffer.append(keyFeature.getType());
    stringBuffer.append(TEXT_192);
    }
    stringBuffer.append(TEXT_193);
    if (valueFeature.isListType()) {
    stringBuffer.append(TEXT_194);
    stringBuffer.append(valueFeature.getQualifiedListItemType());
    stringBuffer.append(TEXT_195);
    } else {
    stringBuffer.append(TEXT_196);
    stringBuffer.append(valueFeature.getType());
    stringBuffer.append(TEXT_197);
    }
    stringBuffer.append(TEXT_198);
    } else if (!genFeature.isWrappedFeatureMapType() && !(genModel.isSuppressEMFMetaData() && "org.eclipse.emf.ecore.EObject".equals(genFeature.getQualifiedListItemType()))) {
    stringBuffer.append(TEXT_199);
    stringBuffer.append(genFeature.getQualifiedListItemType());
    stringBuffer.append(TEXT_200);
    }
    } else if (genFeature.isSetDefaultValue()) {
    stringBuffer.append(TEXT_201);
    stringBuffer.append(genFeature.getDefaultValue());
    stringBuffer.append(TEXT_202);
    }
    if (genFeature.getTypeGenEnum() != null) {
    stringBuffer.append(TEXT_203);
    stringBuffer.append(genFeature.getTypeGenEnum().getQualifiedName());
    stringBuffer.append(TEXT_204);
    }
    if (genFeature.isBidirectional() && !genFeature.getReverse().getGenClass().isMapEntry()) { GenFeature reverseGenFeature = genFeature.getReverse(); 
    if (!reverseGenFeature.isSuppressedGetVisibility()) {
    stringBuffer.append(TEXT_205);
    stringBuffer.append(reverseGenFeature.getGenClass().getQualifiedInterfaceName());
    stringBuffer.append(TEXT_206);
    stringBuffer.append(reverseGenFeature.getGetAccessor());
    stringBuffer.append(TEXT_207);
    stringBuffer.append(reverseGenFeature.getFormattedName());
    stringBuffer.append(TEXT_208);
    }
    }
    stringBuffer.append(TEXT_209);
    if (!genFeature.hasDocumentation()) {
    stringBuffer.append(TEXT_210);
    stringBuffer.append(genFeature.getFormattedName());
    stringBuffer.append(TEXT_211);
    stringBuffer.append(genFeature.getFeatureKind());
    stringBuffer.append(TEXT_212);
    }
    stringBuffer.append(TEXT_213);
    if (genFeature.hasDocumentation()) {
    stringBuffer.append(TEXT_214);
    stringBuffer.append(genFeature.getDocumentation(genModel.getIndentation(stringBuffer)));
    stringBuffer.append(TEXT_215);
    }
    stringBuffer.append(TEXT_216);
    stringBuffer.append(genFeature.getFormattedName());
    stringBuffer.append(TEXT_217);
    stringBuffer.append(genFeature.getFeatureKind());
    stringBuffer.append(TEXT_218);
    if (genFeature.getTypeGenEnum() != null) {
    stringBuffer.append(TEXT_219);
    stringBuffer.append(genFeature.getTypeGenEnum().getQualifiedName());
    }
    if (genFeature.isUnsettable()) {
    if (!genFeature.isSuppressedIsSetVisibility()) {
    stringBuffer.append(TEXT_220);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_221);
    }
    if (genFeature.isChangeable() && !genFeature.isSuppressedUnsetVisibility()) {
    stringBuffer.append(TEXT_222);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_223);
    }
    }
    if (genFeature.isChangeable() && !genFeature.isListType() && !genFeature.isSuppressedSetVisibility()) {
    stringBuffer.append(TEXT_224);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_225);
    stringBuffer.append(genFeature.getImportedType());
    stringBuffer.append(TEXT_226);
    }
    if (!genModel.isSuppressEMFMetaData()) {
    stringBuffer.append(TEXT_227);
    stringBuffer.append(genPackage.getQualifiedPackageInterfaceName());
    stringBuffer.append(TEXT_228);
    stringBuffer.append(genFeature.getFeatureAccessorName());
    stringBuffer.append(TEXT_229);
    }
    if (genFeature.isBidirectional() && !genFeature.getReverse().getGenClass().isMapEntry()) { GenFeature reverseGenFeature = genFeature.getReverse(); 
    if (!reverseGenFeature.isSuppressedGetVisibility()) {
    stringBuffer.append(TEXT_230);
    stringBuffer.append(reverseGenFeature.getGenClass().getQualifiedInterfaceName());
    stringBuffer.append(TEXT_231);
    stringBuffer.append(reverseGenFeature.getGetAccessor());
    }
    }
    if (!genModel.isSuppressEMFModelTags()) { boolean first = true; for (StringTokenizer stringTokenizer = new StringTokenizer(genFeature.getModelInfo(), "\n\r"); stringTokenizer.hasMoreTokens(); ) { String modelInfo = stringTokenizer.nextToken(); if (first) { first = false;
    stringBuffer.append(TEXT_232);
    stringBuffer.append(modelInfo);
    } else {
    stringBuffer.append(TEXT_233);
    stringBuffer.append(modelInfo);
    }} if (first) {
    stringBuffer.append(TEXT_234);
    }}
    stringBuffer.append(TEXT_235);
    } else {
    stringBuffer.append(TEXT_236);
    }
    if (!isImplementation) {
    stringBuffer.append(TEXT_237);
    stringBuffer.append(genFeature.getImportedType());
    stringBuffer.append(TEXT_238);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_239);
    } else {
    if (genModel.useGenerics() && genFeature.isResolveProxies() && !genFeature.isListType() && !genFeature.isContainer() && !genModel.isReflectiveDelegation() && genFeature.isUncheckedCast()) {
    stringBuffer.append(TEXT_240);
    }
    stringBuffer.append(TEXT_241);
    stringBuffer.append(genFeature.getImportedType());
    stringBuffer.append(TEXT_242);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_243);
    if (genModel.isReflectiveDelegation()) {
    stringBuffer.append(TEXT_244);
    if (genFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_245);
    }
    stringBuffer.append(TEXT_246);
    stringBuffer.append(genFeature.getObjectType());
    stringBuffer.append(TEXT_247);
    stringBuffer.append(genFeature.getQualifiedFeatureAccessor());
    stringBuffer.append(TEXT_248);
    if (genFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_249);
    stringBuffer.append(genFeature.getPrimitiveValueFunction());
    stringBuffer.append(TEXT_250);
    }
    stringBuffer.append(TEXT_251);
    } else if (!genFeature.isVolatile()) {
    if (genFeature.isListType()) {
    if (genModel.isVirtualDelegation()) {
    stringBuffer.append(TEXT_252);
    stringBuffer.append(genFeature.getImportedType());
    stringBuffer.append(TEXT_253);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_254);
    stringBuffer.append(genFeature.getImportedType());
    stringBuffer.append(TEXT_255);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(TEXT_256);
    }
    stringBuffer.append(TEXT_257);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_258);
    if (genModel.isVirtualDelegation()) {
    stringBuffer.append(TEXT_259);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(TEXT_260);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_261);
    stringBuffer.append(genClass.getListConstructor(genFeature));
    stringBuffer.append(TEXT_262);
    } else {
    stringBuffer.append(TEXT_263);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_264);
    stringBuffer.append(genClass.getListConstructor(genFeature));
    stringBuffer.append(TEXT_265);
    }
    stringBuffer.append(TEXT_266);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(genFeature.isMapType() && genFeature.isEffectiveSuppressEMFTypes() ? ".map()" : "");
    stringBuffer.append(TEXT_267);
    } else if (genFeature.isContainer()) {
    stringBuffer.append(TEXT_268);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(TEXT_269);
    stringBuffer.append(genFeature.getImportedType());
    stringBuffer.append(TEXT_270);
    } else {
    if (genFeature.isResolveProxies()) {
    if (genModel.isVirtualDelegation()) {
    stringBuffer.append(TEXT_271);
    stringBuffer.append(genFeature.getImportedType());
    stringBuffer.append(TEXT_272);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_273);
    stringBuffer.append(genFeature.getImportedType());
    stringBuffer.append(TEXT_274);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    if (genFeature.hasEDefault()) {
    stringBuffer.append(TEXT_275);
    stringBuffer.append(genFeature.getEDefault());
    }
    stringBuffer.append(TEXT_276);
    }
    stringBuffer.append(TEXT_277);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_278);
    stringBuffer.append(genFeature.getSafeNameAsEObject());
    stringBuffer.append(TEXT_279);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.InternalEObject"));
    stringBuffer.append(TEXT_280);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_281);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.InternalEObject"));
    stringBuffer.append(TEXT_282);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_283);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_284);
    stringBuffer.append(genFeature.getNonEObjectInternalTypeCast());
    stringBuffer.append(TEXT_285);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_286);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_287);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_288);
    if (genFeature.isEffectiveContains()) {
    stringBuffer.append(TEXT_289);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.InternalEObject"));
    stringBuffer.append(TEXT_290);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_291);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.InternalEObject"));
    stringBuffer.append(TEXT_292);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_293);
    if (!genFeature.isBidirectional()) {
    stringBuffer.append(TEXT_294);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.notify.NotificationChain"));
    stringBuffer.append(TEXT_295);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_296);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(TEXT_297);
    } else { GenFeature reverseFeature = genFeature.getReverse(); GenClass targetClass = reverseFeature.getGenClass();
    stringBuffer.append(TEXT_298);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.notify.NotificationChain"));
    stringBuffer.append(TEXT_299);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_300);
    stringBuffer.append(targetClass.getQualifiedFeatureID(reverseFeature));
    stringBuffer.append(TEXT_301);
    stringBuffer.append(targetClass.getImportedInterfaceName());
    stringBuffer.append(TEXT_302);
    }
    stringBuffer.append(TEXT_303);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_304);
    if (!genFeature.isBidirectional()) {
    stringBuffer.append(TEXT_305);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_306);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(TEXT_307);
    } else { GenFeature reverseFeature = genFeature.getReverse(); GenClass targetClass = reverseFeature.getGenClass();
    stringBuffer.append(TEXT_308);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_309);
    stringBuffer.append(targetClass.getQualifiedFeatureID(reverseFeature));
    stringBuffer.append(TEXT_310);
    stringBuffer.append(targetClass.getImportedInterfaceName());
    stringBuffer.append(TEXT_311);
    }
    stringBuffer.append(TEXT_312);
    } else if (genModel.isVirtualDelegation()) {
    stringBuffer.append(TEXT_313);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(TEXT_314);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_315);
    }
    if (!genModel.isSuppressNotification()) {
    stringBuffer.append(TEXT_316);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.impl.ENotificationImpl"));
    stringBuffer.append(TEXT_317);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.notify.Notification"));
    stringBuffer.append(TEXT_318);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(TEXT_319);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_320);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_321);
    }
    stringBuffer.append(TEXT_322);
    }
    if (!genFeature.isResolveProxies() && genModel.isVirtualDelegation() && !genFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_323);
    stringBuffer.append(genFeature.getImportedType());
    stringBuffer.append(TEXT_324);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    if (genFeature.hasEDefault()) {
    stringBuffer.append(TEXT_325);
    stringBuffer.append(genFeature.getEDefault());
    }
    stringBuffer.append(TEXT_326);
    } else if (genClass.isFlag(genFeature)) {
    stringBuffer.append(TEXT_327);
    stringBuffer.append(genClass.getFlagsField(genFeature));
    stringBuffer.append(TEXT_328);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_329);
    } else {
    stringBuffer.append(TEXT_330);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_331);
    }
    }
    } else {//volatile
    if (genFeature.isResolveProxies() && !genFeature.isListType()) {
    stringBuffer.append(TEXT_332);
    stringBuffer.append(genFeature.getImportedType());
    stringBuffer.append(TEXT_333);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_334);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_335);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_336);
    stringBuffer.append(genFeature.getSafeNameAsEObject());
    stringBuffer.append(TEXT_337);
    stringBuffer.append(genFeature.getNonEObjectInternalTypeCast());
    stringBuffer.append(TEXT_338);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.InternalEObject"));
    stringBuffer.append(TEXT_339);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_340);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_341);
    } else if (genFeature.hasDelegateFeature()) { GenFeature delegateFeature = genFeature.getDelegateFeature();
    if (genFeature.isFeatureMapType()) {
    String featureMapEntryTemplateArgument = genModel.getComplianceLevel().getValue() >= GenJDKLevel.JDK50 ? "<" + genModel.getImportedName("org.eclipse.emf.ecore.util.FeatureMap") + ".Entry>" : "";
    if (delegateFeature.isWrappedFeatureMapType()) {
    stringBuffer.append(TEXT_342);
    stringBuffer.append(genFeature.getImportedEffectiveFeatureMapWrapperClass());
    stringBuffer.append(TEXT_343);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.FeatureMap"));
    stringBuffer.append(TEXT_344);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.FeatureMap"));
    stringBuffer.append(TEXT_345);
    stringBuffer.append(delegateFeature.getAccessorName());
    stringBuffer.append(TEXT_346);
    stringBuffer.append(featureMapEntryTemplateArgument);
    stringBuffer.append(TEXT_347);
    stringBuffer.append(genFeature.getQualifiedFeatureAccessor());
    stringBuffer.append(TEXT_348);
    } else {
    stringBuffer.append(TEXT_349);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.FeatureMap"));
    stringBuffer.append(TEXT_350);
    stringBuffer.append(delegateFeature.getAccessorName());
    stringBuffer.append(TEXT_351);
    stringBuffer.append(featureMapEntryTemplateArgument);
    stringBuffer.append(TEXT_352);
    stringBuffer.append(genFeature.getQualifiedFeatureAccessor());
    stringBuffer.append(TEXT_353);
    }
    } else if (genFeature.isListType()) {
    if (delegateFeature.isWrappedFeatureMapType()) {
    stringBuffer.append(TEXT_354);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.FeatureMap"));
    stringBuffer.append(TEXT_355);
    stringBuffer.append(delegateFeature.getAccessorName());
    stringBuffer.append(TEXT_356);
    stringBuffer.append(genFeature.getQualifiedFeatureAccessor());
    stringBuffer.append(TEXT_357);
    } else {
    stringBuffer.append(TEXT_358);
    stringBuffer.append(delegateFeature.getAccessorName());
    stringBuffer.append(TEXT_359);
    stringBuffer.append(genFeature.getQualifiedFeatureAccessor());
    stringBuffer.append(TEXT_360);
    }
    } else {
    if (delegateFeature.isWrappedFeatureMapType()) {
    stringBuffer.append(TEXT_361);
    if (genFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_362);
    }
    stringBuffer.append(TEXT_363);
    stringBuffer.append(genFeature.getObjectType());
    stringBuffer.append(TEXT_364);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.FeatureMap"));
    stringBuffer.append(TEXT_365);
    stringBuffer.append(delegateFeature.getAccessorName());
    stringBuffer.append(TEXT_366);
    stringBuffer.append(genFeature.getQualifiedFeatureAccessor());
    stringBuffer.append(TEXT_367);
    if (genFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_368);
    stringBuffer.append(genFeature.getPrimitiveValueFunction());
    stringBuffer.append(TEXT_369);
    }
    stringBuffer.append(TEXT_370);
    } else {
    stringBuffer.append(TEXT_371);
    if (genFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_372);
    }
    stringBuffer.append(TEXT_373);
    stringBuffer.append(genFeature.getObjectType());
    stringBuffer.append(TEXT_374);
    stringBuffer.append(delegateFeature.getAccessorName());
    stringBuffer.append(TEXT_375);
    stringBuffer.append(genFeature.getQualifiedFeatureAccessor());
    stringBuffer.append(TEXT_376);
    if (genFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_377);
    stringBuffer.append(genFeature.getPrimitiveValueFunction());
    stringBuffer.append(TEXT_378);
    }
    stringBuffer.append(TEXT_379);
    }
    }
    } else {
    stringBuffer.append(TEXT_380);
    stringBuffer.append(genFeature.getFormattedName());
    stringBuffer.append(TEXT_381);
    stringBuffer.append(genFeature.getFeatureKind());
    stringBuffer.append(TEXT_382);
    if (genFeature.isListType()) {
    stringBuffer.append(TEXT_383);
    if (genFeature.isMapType()) {
    stringBuffer.append(TEXT_384);
    } else if (genFeature.isFeatureMapType()) {
    stringBuffer.append(TEXT_385);
    } else {
    stringBuffer.append(TEXT_386);
    }
    stringBuffer.append(TEXT_387);
    }
    stringBuffer.append(TEXT_388);
    //Class/getGenFeature.todo.override.javajetinc
    }
    }
    stringBuffer.append(TEXT_389);
    }
    //Class/getGenFeature.override.javajetinc
    }
    if (isImplementation && !genModel.isReflectiveDelegation() && genFeature.isBasicGet()) {
    stringBuffer.append(TEXT_390);
    stringBuffer.append(genFeature.getImportedType());
    stringBuffer.append(TEXT_391);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_392);
    if (genFeature.isContainer()) {
    stringBuffer.append(TEXT_393);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(TEXT_394);
    stringBuffer.append(genFeature.getImportedType());
    stringBuffer.append(TEXT_395);
    } else if (!genFeature.isVolatile()) {
    if (genModel.isVirtualDelegation()) {
    stringBuffer.append(TEXT_396);
    stringBuffer.append(genFeature.getImportedType());
    stringBuffer.append(TEXT_397);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(TEXT_398);
    } else {
    stringBuffer.append(TEXT_399);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_400);
    }
    } else if (genFeature.hasDelegateFeature()) { GenFeature delegateFeature = genFeature.getDelegateFeature();
    if (delegateFeature.isWrappedFeatureMapType()) {
    stringBuffer.append(TEXT_401);
    stringBuffer.append(genFeature.getImportedType());
    stringBuffer.append(TEXT_402);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.FeatureMap"));
    stringBuffer.append(TEXT_403);
    stringBuffer.append(delegateFeature.getAccessorName());
    stringBuffer.append(TEXT_404);
    stringBuffer.append(genFeature.getQualifiedFeatureAccessor());
    stringBuffer.append(TEXT_405);
    } else {
    stringBuffer.append(TEXT_406);
    stringBuffer.append(genFeature.getImportedType());
    stringBuffer.append(TEXT_407);
    stringBuffer.append(delegateFeature.getAccessorName());
    stringBuffer.append(TEXT_408);
    stringBuffer.append(genFeature.getQualifiedFeatureAccessor());
    stringBuffer.append(TEXT_409);
    }
    } else {
    stringBuffer.append(TEXT_410);
    stringBuffer.append(genFeature.getFormattedName());
    stringBuffer.append(TEXT_411);
    stringBuffer.append(genFeature.getFeatureKind());
    stringBuffer.append(TEXT_412);
    //Class/basicGetGenFeature.todo.override.javajetinc
    }
    stringBuffer.append(TEXT_413);
    //Class/basicGetGenFeature.override.javajetinc
    }
    if (isImplementation && !genModel.isReflectiveDelegation() && genFeature.isBasicSet()) {
    stringBuffer.append(TEXT_414);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.notify.NotificationChain"));
    stringBuffer.append(TEXT_415);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_416);
    stringBuffer.append(genFeature.getImportedInternalType());
    stringBuffer.append(TEXT_417);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_418);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.notify.NotificationChain"));
    stringBuffer.append(TEXT_419);
    if (genFeature.isContainer()) {
    stringBuffer.append(TEXT_420);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.InternalEObject"));
    stringBuffer.append(TEXT_421);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_422);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(TEXT_423);
    stringBuffer.append(TEXT_424);
    } else if (!genFeature.isVolatile()) {
    if (genModel.isVirtualDelegation()) {
    stringBuffer.append(TEXT_425);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_426);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(TEXT_427);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_428);
    } else {
    stringBuffer.append(TEXT_429);
    stringBuffer.append(genFeature.getImportedType());
    stringBuffer.append(TEXT_430);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_431);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_432);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_433);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_434);
    }
    if (genFeature.isUnsettable()) {
    if (genModel.isVirtualDelegation()) {
    stringBuffer.append(TEXT_435);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_436);
    } else if (genClass.isESetFlag(genFeature)) {
    stringBuffer.append(TEXT_437);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_438);
    stringBuffer.append(genClass.getESetFlagsField(genFeature));
    stringBuffer.append(TEXT_439);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_440);
    stringBuffer.append(genClass.getESetFlagsField(genFeature));
    stringBuffer.append(TEXT_441);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_442);
    } else {
    stringBuffer.append(TEXT_443);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_444);
    stringBuffer.append(genFeature.getUncapName());
    stringBuffer.append(TEXT_445);
    stringBuffer.append(genFeature.getUncapName());
    stringBuffer.append(TEXT_446);
    }
    }
    if (!genModel.isSuppressNotification()) {
    stringBuffer.append(TEXT_447);
    if (genFeature.isUnsettable()) {
    stringBuffer.append(TEXT_448);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.impl.ENotificationImpl"));
    stringBuffer.append(TEXT_449);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.impl.ENotificationImpl"));
    stringBuffer.append(TEXT_450);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.notify.Notification"));
    stringBuffer.append(TEXT_451);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(TEXT_452);
    if (genModel.isVirtualDelegation()) {
    stringBuffer.append(TEXT_453);
    stringBuffer.append(genFeature.getCapName());
    } else {
    stringBuffer.append(TEXT_454);
    stringBuffer.append(genFeature.getCapName());
    }
    stringBuffer.append(TEXT_455);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_456);
    if (genModel.isVirtualDelegation()) {
    stringBuffer.append(TEXT_457);
    } else {
    stringBuffer.append(TEXT_458);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_459);
    }
    stringBuffer.append(TEXT_460);
    } else {
    stringBuffer.append(TEXT_461);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.impl.ENotificationImpl"));
    stringBuffer.append(TEXT_462);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.impl.ENotificationImpl"));
    stringBuffer.append(TEXT_463);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.notify.Notification"));
    stringBuffer.append(TEXT_464);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(TEXT_465);
    if (genModel.isVirtualDelegation()) {
    stringBuffer.append(TEXT_466);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_467);
    stringBuffer.append(genFeature.getCapName());
    } else {
    stringBuffer.append(TEXT_468);
    stringBuffer.append(genFeature.getCapName());
    }
    stringBuffer.append(TEXT_469);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_470);
    }
    stringBuffer.append(TEXT_471);
    }
    stringBuffer.append(TEXT_472);
    } else if (genFeature.hasDelegateFeature()) { GenFeature delegateFeature = genFeature.getDelegateFeature();
    if (delegateFeature.isWrappedFeatureMapType()) {
    stringBuffer.append(TEXT_473);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.FeatureMap"));
    stringBuffer.append(TEXT_474);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.FeatureMap"));
    stringBuffer.append(TEXT_475);
    stringBuffer.append(delegateFeature.getAccessorName());
    stringBuffer.append(TEXT_476);
    stringBuffer.append(genFeature.getQualifiedFeatureAccessor());
    stringBuffer.append(TEXT_477);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_478);
    } else {
    stringBuffer.append(TEXT_479);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.FeatureMap"));
    stringBuffer.append(TEXT_480);
    stringBuffer.append(delegateFeature.getAccessorName());
    stringBuffer.append(TEXT_481);
    stringBuffer.append(genFeature.getQualifiedFeatureAccessor());
    stringBuffer.append(TEXT_482);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_483);
    }
    } else {
    stringBuffer.append(TEXT_484);
    stringBuffer.append(genFeature.getFormattedName());
    stringBuffer.append(TEXT_485);
    stringBuffer.append(genFeature.getFeatureKind());
    stringBuffer.append(TEXT_486);
    //Class/basicSetGenFeature.todo.override.javajetinc
    }
    stringBuffer.append(TEXT_487);
    //Class/basicSetGenFeature.override.javajetinc
    }
    if (genFeature.isSet() && (isImplementation || !genFeature.isSuppressedSetVisibility())) {
    if (isInterface) { 
    stringBuffer.append(TEXT_488);
    stringBuffer.append(genClass.getQualifiedInterfaceName());
    stringBuffer.append(TEXT_489);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_490);
    stringBuffer.append(genFeature.getFormattedName());
    stringBuffer.append(TEXT_491);
    stringBuffer.append(genFeature.getFeatureKind());
    stringBuffer.append(TEXT_492);
    stringBuffer.append(genFeature.getFormattedName());
    stringBuffer.append(TEXT_493);
    stringBuffer.append(genFeature.getFeatureKind());
    stringBuffer.append(TEXT_494);
    if (genFeature.isEnumType()) {
    stringBuffer.append(TEXT_495);
    stringBuffer.append(genFeature.getTypeGenEnum().getQualifiedName());
    }
    if (genFeature.isUnsettable()) {
    if (!genFeature.isSuppressedIsSetVisibility()) {
    stringBuffer.append(TEXT_496);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_497);
    }
    if (!genFeature.isSuppressedUnsetVisibility()) {
    stringBuffer.append(TEXT_498);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_499);
    }
    }
    stringBuffer.append(TEXT_500);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_501);
    } else {
    stringBuffer.append(TEXT_502);
    }
    if (!isImplementation) { 
    stringBuffer.append(TEXT_503);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_504);
    stringBuffer.append(genFeature.getImportedType());
    stringBuffer.append(TEXT_505);
    } else {
    stringBuffer.append(TEXT_506);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_507);
    stringBuffer.append(genFeature.getImportedType());
    stringBuffer.append(TEXT_508);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_509);
    if (genModel.isReflectiveDelegation()) {
    stringBuffer.append(TEXT_510);
    stringBuffer.append(genFeature.getQualifiedFeatureAccessor());
    stringBuffer.append(TEXT_511);
    if (genFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_512);
    stringBuffer.append(genFeature.getObjectType());
    stringBuffer.append(TEXT_513);
    }
    stringBuffer.append(TEXT_514);
    stringBuffer.append(genFeature.getCapName());
    if (genFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_515);
    }
    stringBuffer.append(TEXT_516);
    } else if (!genFeature.isVolatile()) {
    if (genFeature.isContainer()) { GenFeature reverseFeature = genFeature.getReverse(); GenClass targetClass = reverseFeature.getGenClass();
    stringBuffer.append(TEXT_517);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_518);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(TEXT_519);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_520);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.EcoreUtil"));
    stringBuffer.append(TEXT_521);
    stringBuffer.append(genFeature.getEObjectCast());
    stringBuffer.append(TEXT_522);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_523);
    stringBuffer.append(genModel.getImportedName("java.lang.IllegalArgumentException"));
    stringBuffer.append(TEXT_524);
    stringBuffer.append(genModel.getNonNLS());
    stringBuffer.append(TEXT_525);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.notify.NotificationChain"));
    stringBuffer.append(TEXT_526);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_527);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.InternalEObject"));
    stringBuffer.append(TEXT_528);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_529);
    stringBuffer.append(targetClass.getQualifiedFeatureID(reverseFeature));
    stringBuffer.append(TEXT_530);
    stringBuffer.append(targetClass.getImportedInterfaceName());
    stringBuffer.append(TEXT_531);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_532);
    stringBuffer.append(genFeature.getInternalTypeCast());
    stringBuffer.append(TEXT_533);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_534);
    if (!genModel.isSuppressNotification()) {
    stringBuffer.append(TEXT_535);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.impl.ENotificationImpl"));
    stringBuffer.append(TEXT_536);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.notify.Notification"));
    stringBuffer.append(TEXT_537);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(TEXT_538);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_539);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_540);
    }
    } else if (genFeature.isBidirectional() || genFeature.isEffectiveContains()) {
    if (genModel.isVirtualDelegation()) {
    stringBuffer.append(TEXT_541);
    stringBuffer.append(genFeature.getImportedType());
    stringBuffer.append(TEXT_542);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_543);
    stringBuffer.append(genFeature.getImportedType());
    stringBuffer.append(TEXT_544);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(TEXT_545);
    }
    stringBuffer.append(TEXT_546);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_547);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_548);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.notify.NotificationChain"));
    stringBuffer.append(TEXT_549);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_550);
    if (!genFeature.isBidirectional()) {
    stringBuffer.append(TEXT_551);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.InternalEObject"));
    stringBuffer.append(TEXT_552);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_553);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(TEXT_554);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_555);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.InternalEObject"));
    stringBuffer.append(TEXT_556);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_557);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(TEXT_558);
    } else { GenFeature reverseFeature = genFeature.getReverse(); GenClass targetClass = reverseFeature.getGenClass();
    stringBuffer.append(TEXT_559);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.InternalEObject"));
    stringBuffer.append(TEXT_560);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_561);
    stringBuffer.append(targetClass.getQualifiedFeatureID(reverseFeature));
    stringBuffer.append(TEXT_562);
    stringBuffer.append(targetClass.getImportedInterfaceName());
    stringBuffer.append(TEXT_563);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_564);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.InternalEObject"));
    stringBuffer.append(TEXT_565);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_566);
    stringBuffer.append(targetClass.getQualifiedFeatureID(reverseFeature));
    stringBuffer.append(TEXT_567);
    stringBuffer.append(targetClass.getImportedInterfaceName());
    stringBuffer.append(TEXT_568);
    }
    stringBuffer.append(TEXT_569);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_570);
    stringBuffer.append(genFeature.getInternalTypeCast());
    stringBuffer.append(TEXT_571);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_572);
    if (genFeature.isUnsettable()) {
    stringBuffer.append(TEXT_573);
    if (genModel.isVirtualDelegation()) {
    stringBuffer.append(TEXT_574);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_575);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(TEXT_576);
    } else if (genClass.isESetFlag(genFeature)) {
    if (!genModel.isSuppressNotification()) {
    stringBuffer.append(TEXT_577);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_578);
    stringBuffer.append(genClass.getESetFlagsField(genFeature));
    stringBuffer.append(TEXT_579);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_580);
    }
    stringBuffer.append(TEXT_581);
    stringBuffer.append(genClass.getESetFlagsField(genFeature));
    stringBuffer.append(TEXT_582);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_583);
    } else {
    if (!genModel.isSuppressNotification()) {
    stringBuffer.append(TEXT_584);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_585);
    stringBuffer.append(genFeature.getUncapName());
    stringBuffer.append(TEXT_586);
    }
    stringBuffer.append(TEXT_587);
    stringBuffer.append(genFeature.getUncapName());
    stringBuffer.append(TEXT_588);
    }
    if (!genModel.isSuppressNotification()) {
    stringBuffer.append(TEXT_589);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.impl.ENotificationImpl"));
    stringBuffer.append(TEXT_590);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.notify.Notification"));
    stringBuffer.append(TEXT_591);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(TEXT_592);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_593);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_594);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_595);
    }
    stringBuffer.append(TEXT_596);
    } else {
    if (!genModel.isSuppressNotification()) {
    stringBuffer.append(TEXT_597);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.impl.ENotificationImpl"));
    stringBuffer.append(TEXT_598);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.notify.Notification"));
    stringBuffer.append(TEXT_599);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(TEXT_600);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_601);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_602);
    }
    }
    } else {
    if (genClass.isFlag(genFeature)) {
    if (!genModel.isSuppressNotification()) {
    stringBuffer.append(TEXT_603);
    stringBuffer.append(genFeature.getImportedType());
    stringBuffer.append(TEXT_604);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_605);
    stringBuffer.append(genClass.getFlagsField(genFeature));
    stringBuffer.append(TEXT_606);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_607);
    }
    stringBuffer.append(TEXT_608);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_609);
    stringBuffer.append(genClass.getFlagsField(genFeature));
    stringBuffer.append(TEXT_610);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_611);
    stringBuffer.append(genClass.getFlagsField(genFeature));
    stringBuffer.append(TEXT_612);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_613);
    } else {
    if (!genModel.isVirtualDelegation() || genFeature.isPrimitiveType()) {
    if (!genModel.isSuppressNotification()) {
    stringBuffer.append(TEXT_614);
    stringBuffer.append(genFeature.getImportedType());
    stringBuffer.append(TEXT_615);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_616);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_617);
    }
    }
    if (genFeature.isEnumType()) {
    if (genModel.isVirtualDelegation()) {
    stringBuffer.append(TEXT_618);
    stringBuffer.append(genFeature.getImportedType());
    stringBuffer.append(TEXT_619);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_620);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_621);
    stringBuffer.append(genFeature.getEDefault());
    stringBuffer.append(TEXT_622);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_623);
    } else {
    stringBuffer.append(TEXT_624);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_625);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_626);
    stringBuffer.append(genFeature.getEDefault());
    stringBuffer.append(TEXT_627);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_628);
    }
    } else {
    if (genModel.isVirtualDelegation() && !genFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_629);
    stringBuffer.append(genFeature.getImportedType());
    stringBuffer.append(TEXT_630);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_631);
    stringBuffer.append(genFeature.getInternalTypeCast());
    stringBuffer.append(TEXT_632);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_633);
    } else {
    stringBuffer.append(TEXT_634);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_635);
    stringBuffer.append(genFeature.getInternalTypeCast());
    stringBuffer.append(TEXT_636);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_637);
    }
    }
    if (genModel.isVirtualDelegation() && !genFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_638);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_639);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(TEXT_640);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_641);
    }
    }
    if (genFeature.isUnsettable()) {
    if (genModel.isVirtualDelegation() && !genFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_642);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_643);
    } else if (genClass.isESetFlag(genFeature)) {
    if (!genModel.isSuppressNotification()) {
    stringBuffer.append(TEXT_644);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_645);
    stringBuffer.append(genClass.getESetFlagsField(genFeature));
    stringBuffer.append(TEXT_646);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_647);
    }
    stringBuffer.append(TEXT_648);
    stringBuffer.append(genClass.getESetFlagsField(genFeature));
    stringBuffer.append(TEXT_649);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_650);
    } else {
    if (!genModel.isSuppressNotification()) {
    stringBuffer.append(TEXT_651);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_652);
    stringBuffer.append(genFeature.getUncapName());
    stringBuffer.append(TEXT_653);
    }
    stringBuffer.append(TEXT_654);
    stringBuffer.append(genFeature.getUncapName());
    stringBuffer.append(TEXT_655);
    }
    if (!genModel.isSuppressNotification()) {
    stringBuffer.append(TEXT_656);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.impl.ENotificationImpl"));
    stringBuffer.append(TEXT_657);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.notify.Notification"));
    stringBuffer.append(TEXT_658);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(TEXT_659);
    if (genModel.isVirtualDelegation() && !genFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_660);
    stringBuffer.append(genFeature.getEDefault());
    stringBuffer.append(TEXT_661);
    stringBuffer.append(genFeature.getCapName());
    } else {
    stringBuffer.append(TEXT_662);
    stringBuffer.append(genFeature.getCapName());
    }
    stringBuffer.append(TEXT_663);
    if (genClass.isFlag(genFeature)) {
    stringBuffer.append(TEXT_664);
    stringBuffer.append(genFeature.getCapName());
    } else {
    stringBuffer.append(genFeature.getSafeName());
    }
    stringBuffer.append(TEXT_665);
    if (genModel.isVirtualDelegation() && !genFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_666);
    } else {
    stringBuffer.append(TEXT_667);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_668);
    }
    stringBuffer.append(TEXT_669);
    }
    } else {
    if (!genModel.isSuppressNotification()) {
    stringBuffer.append(TEXT_670);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.impl.ENotificationImpl"));
    stringBuffer.append(TEXT_671);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.notify.Notification"));
    stringBuffer.append(TEXT_672);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(TEXT_673);
    if (genModel.isVirtualDelegation() && !genFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_674);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_675);
    stringBuffer.append(genFeature.getEDefault());
    stringBuffer.append(TEXT_676);
    stringBuffer.append(genFeature.getCapName());
    } else {
    stringBuffer.append(TEXT_677);
    stringBuffer.append(genFeature.getCapName());
    }
    stringBuffer.append(TEXT_678);
    if (genClass.isFlag(genFeature)) {
    stringBuffer.append(TEXT_679);
    stringBuffer.append(genFeature.getCapName());
    } else {
    stringBuffer.append(genFeature.getSafeName());
    }
    stringBuffer.append(TEXT_680);
    }
    }
    }
    } else if (genFeature.hasDelegateFeature()) { GenFeature delegateFeature = genFeature.getDelegateFeature();
    if (delegateFeature.isWrappedFeatureMapType()) {
    stringBuffer.append(TEXT_681);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.FeatureMap"));
    stringBuffer.append(TEXT_682);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.FeatureMap"));
    stringBuffer.append(TEXT_683);
    stringBuffer.append(delegateFeature.getAccessorName());
    stringBuffer.append(TEXT_684);
    stringBuffer.append(genFeature.getQualifiedFeatureAccessor());
    stringBuffer.append(TEXT_685);
    if (genFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_686);
    stringBuffer.append(genFeature.getObjectType());
    stringBuffer.append(TEXT_687);
    }
    stringBuffer.append(TEXT_688);
    stringBuffer.append(genFeature.getCapName());
    if (genFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_689);
    }
    stringBuffer.append(TEXT_690);
    } else {
    stringBuffer.append(TEXT_691);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.FeatureMap"));
    stringBuffer.append(TEXT_692);
    stringBuffer.append(delegateFeature.getAccessorName());
    stringBuffer.append(TEXT_693);
    stringBuffer.append(genFeature.getQualifiedFeatureAccessor());
    stringBuffer.append(TEXT_694);
    if (genFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_695);
    stringBuffer.append(genFeature.getObjectType());
    stringBuffer.append(TEXT_696);
    }
    stringBuffer.append(TEXT_697);
    stringBuffer.append(genFeature.getCapName());
    if (genFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_698);
    }
    stringBuffer.append(TEXT_699);
    }
    } else {
    stringBuffer.append(TEXT_700);
    stringBuffer.append(genFeature.getFormattedName());
    stringBuffer.append(TEXT_701);
    stringBuffer.append(genFeature.getFeatureKind());
    stringBuffer.append(TEXT_702);
    //Class/setGenFeature.todo.override.javajetinc
    }
    stringBuffer.append(TEXT_703);
    }
    //Class/setGenFeature.override.javajetinc
    }
    if (isImplementation && !genModel.isReflectiveDelegation() && genFeature.isBasicUnset()) {
    stringBuffer.append(TEXT_704);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.notify.NotificationChain"));
    stringBuffer.append(TEXT_705);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_706);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.notify.NotificationChain"));
    stringBuffer.append(TEXT_707);
    if (!genFeature.isVolatile()) {
    if (genModel.isVirtualDelegation()) {
    stringBuffer.append(TEXT_708);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_709);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(TEXT_710);
    } else {
    stringBuffer.append(TEXT_711);
    stringBuffer.append(genFeature.getImportedType());
    stringBuffer.append(TEXT_712);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_713);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_714);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_715);
    }
    if (genModel.isVirtualDelegation()) {
    stringBuffer.append(TEXT_716);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_717);
    } else if (genClass.isESetFlag(genFeature)) {
    stringBuffer.append(TEXT_718);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_719);
    stringBuffer.append(genClass.getESetFlagsField(genFeature));
    stringBuffer.append(TEXT_720);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_721);
    stringBuffer.append(genClass.getESetFlagsField(genFeature));
    stringBuffer.append(TEXT_722);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_723);
    } else {
    stringBuffer.append(TEXT_724);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_725);
    stringBuffer.append(genFeature.getUncapName());
    stringBuffer.append(TEXT_726);
    stringBuffer.append(genFeature.getUncapName());
    stringBuffer.append(TEXT_727);
    }
    if (!genModel.isSuppressNotification()) {
    stringBuffer.append(TEXT_728);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.impl.ENotificationImpl"));
    stringBuffer.append(TEXT_729);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.impl.ENotificationImpl"));
    stringBuffer.append(TEXT_730);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.notify.Notification"));
    stringBuffer.append(TEXT_731);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(TEXT_732);
    if (genModel.isVirtualDelegation()) {
    stringBuffer.append(TEXT_733);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_734);
    } else {
    stringBuffer.append(TEXT_735);
    stringBuffer.append(genFeature.getCapName());
    }
    stringBuffer.append(TEXT_736);
    if (genModel.isVirtualDelegation()) {
    stringBuffer.append(TEXT_737);
    } else {
    stringBuffer.append(TEXT_738);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_739);
    }
    stringBuffer.append(TEXT_740);
    }
    } else {
    stringBuffer.append(TEXT_741);
    stringBuffer.append(genFeature.getFormattedName());
    stringBuffer.append(TEXT_742);
    stringBuffer.append(genFeature.getFeatureKind());
    stringBuffer.append(TEXT_743);
    //Class/basicUnsetGenFeature.todo.override.javajetinc
    }
    stringBuffer.append(TEXT_744);
    //Class.basicUnsetGenFeature.override.javajetinc
    }
    if (genFeature.isUnset() && (isImplementation || !genFeature.isSuppressedUnsetVisibility())) {
    if (isInterface) {
    stringBuffer.append(TEXT_745);
    stringBuffer.append(genClass.getQualifiedInterfaceName());
    stringBuffer.append(TEXT_746);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_747);
    stringBuffer.append(genFeature.getFormattedName());
    stringBuffer.append(TEXT_748);
    stringBuffer.append(genFeature.getFeatureKind());
    stringBuffer.append(TEXT_749);
    if (!genFeature.isSuppressedIsSetVisibility()) {
    stringBuffer.append(TEXT_750);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_751);
    }
    stringBuffer.append(TEXT_752);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_753);
    if (!genFeature.isListType() && !genFeature.isSuppressedSetVisibility()) {
    stringBuffer.append(TEXT_754);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_755);
    stringBuffer.append(genFeature.getImportedType());
    stringBuffer.append(TEXT_756);
    }
    stringBuffer.append(TEXT_757);
    } else {
    stringBuffer.append(TEXT_758);
    }
    if (!isImplementation) {
    stringBuffer.append(TEXT_759);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_760);
    } else {
    stringBuffer.append(TEXT_761);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_762);
    if (genModel.isReflectiveDelegation()) {
    stringBuffer.append(TEXT_763);
    stringBuffer.append(genFeature.getQualifiedFeatureAccessor());
    stringBuffer.append(TEXT_764);
    } else if (!genFeature.isVolatile()) {
    if (genFeature.isListType()) {
    if (genModel.isVirtualDelegation()) {
    stringBuffer.append(TEXT_765);
    stringBuffer.append(genFeature.getImportedType());
    stringBuffer.append(TEXT_766);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_767);
    stringBuffer.append(genFeature.getImportedType());
    stringBuffer.append(TEXT_768);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(TEXT_769);
    }
    stringBuffer.append(TEXT_770);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_771);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.InternalEList"));
    stringBuffer.append(TEXT_772);
    stringBuffer.append(singleWildcard);
    stringBuffer.append(TEXT_773);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_774);
    } else if (genFeature.isBidirectional() || genFeature.isEffectiveContains()) {
    if (genModel.isVirtualDelegation()) {
    stringBuffer.append(TEXT_775);
    stringBuffer.append(genFeature.getImportedType());
    stringBuffer.append(TEXT_776);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_777);
    stringBuffer.append(genFeature.getImportedType());
    stringBuffer.append(TEXT_778);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(TEXT_779);
    }
    stringBuffer.append(TEXT_780);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_781);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.notify.NotificationChain"));
    stringBuffer.append(TEXT_782);
    if (!genFeature.isBidirectional()) {
    stringBuffer.append(TEXT_783);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.InternalEObject"));
    stringBuffer.append(TEXT_784);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_785);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(TEXT_786);
    } else { GenFeature reverseFeature = genFeature.getReverse(); GenClass targetClass = reverseFeature.getGenClass();
    stringBuffer.append(TEXT_787);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.InternalEObject"));
    stringBuffer.append(TEXT_788);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_789);
    stringBuffer.append(targetClass.getQualifiedFeatureID(reverseFeature));
    stringBuffer.append(TEXT_790);
    stringBuffer.append(targetClass.getImportedInterfaceName());
    stringBuffer.append(TEXT_791);
    }
    stringBuffer.append(TEXT_792);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_793);
    if (genModel.isVirtualDelegation()) {
    stringBuffer.append(TEXT_794);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_795);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(TEXT_796);
    } else if (genClass.isESetFlag(genFeature)) {
    if (!genModel.isSuppressNotification()) {
    stringBuffer.append(TEXT_797);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_798);
    stringBuffer.append(genClass.getESetFlagsField(genFeature));
    stringBuffer.append(TEXT_799);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_800);
    }
    stringBuffer.append(TEXT_801);
    stringBuffer.append(genClass.getESetFlagsField(genFeature));
    stringBuffer.append(TEXT_802);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_803);
    } else {
    if (!genModel.isSuppressNotification()) {
    stringBuffer.append(TEXT_804);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_805);
    stringBuffer.append(genFeature.getUncapName());
    stringBuffer.append(TEXT_806);
    }
    stringBuffer.append(TEXT_807);
    stringBuffer.append(genFeature.getUncapName());
    stringBuffer.append(TEXT_808);
    }
    if (!genModel.isSuppressNotification()) {
    stringBuffer.append(TEXT_809);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.impl.ENotificationImpl"));
    stringBuffer.append(TEXT_810);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.notify.Notification"));
    stringBuffer.append(TEXT_811);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(TEXT_812);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_813);
    }
    stringBuffer.append(TEXT_814);
    } else {
    if (genClass.isFlag(genFeature)) {
    if (!genModel.isSuppressNotification()) {
    stringBuffer.append(TEXT_815);
    stringBuffer.append(genFeature.getImportedType());
    stringBuffer.append(TEXT_816);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_817);
    stringBuffer.append(genClass.getFlagsField(genFeature));
    stringBuffer.append(TEXT_818);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_819);
    }
    } else if (genModel.isVirtualDelegation() && !genFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_820);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_821);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(TEXT_822);
    } else {
    if (!genModel.isSuppressNotification()) {
    stringBuffer.append(TEXT_823);
    stringBuffer.append(genFeature.getImportedType());
    stringBuffer.append(TEXT_824);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_825);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_826);
    }
    }
    if (genModel.isVirtualDelegation() && !genFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_827);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_828);
    } else if (genClass.isESetFlag(genFeature)) {
    stringBuffer.append(TEXT_829);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_830);
    stringBuffer.append(genClass.getESetFlagsField(genFeature));
    stringBuffer.append(TEXT_831);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_832);
    } else {
    if (!genModel.isSuppressNotification()) {
    stringBuffer.append(TEXT_833);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_834);
    stringBuffer.append(genFeature.getUncapName());
    stringBuffer.append(TEXT_835);
    }
    }
    if (genFeature.isReferenceType()) {
    stringBuffer.append(TEXT_836);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_837);
    if (!genModel.isVirtualDelegation()) {
    if (genClass.isESetFlag(genFeature)) {
    stringBuffer.append(TEXT_838);
    stringBuffer.append(genClass.getESetFlagsField(genFeature));
    stringBuffer.append(TEXT_839);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_840);
    } else {
    stringBuffer.append(TEXT_841);
    stringBuffer.append(genFeature.getUncapName());
    stringBuffer.append(TEXT_842);
    }
    }
    if (!genModel.isSuppressNotification()) {
    stringBuffer.append(TEXT_843);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.impl.ENotificationImpl"));
    stringBuffer.append(TEXT_844);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.notify.Notification"));
    stringBuffer.append(TEXT_845);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(TEXT_846);
    if (genModel.isVirtualDelegation()) {
    stringBuffer.append(TEXT_847);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_848);
    } else {
    stringBuffer.append(TEXT_849);
    stringBuffer.append(genFeature.getCapName());
    }
    stringBuffer.append(TEXT_850);
    if (genModel.isVirtualDelegation()) {
    stringBuffer.append(TEXT_851);
    } else {
    stringBuffer.append(TEXT_852);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_853);
    }
    stringBuffer.append(TEXT_854);
    }
    } else {
    if (genClass.isFlag(genFeature)) {
    stringBuffer.append(TEXT_855);
    stringBuffer.append(genFeature.getEDefault());
    stringBuffer.append(TEXT_856);
    stringBuffer.append(genClass.getFlagsField(genFeature));
    stringBuffer.append(TEXT_857);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_858);
    stringBuffer.append(genClass.getFlagsField(genFeature));
    stringBuffer.append(TEXT_859);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_860);
    } else if (!genModel.isVirtualDelegation() || genFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_861);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_862);
    stringBuffer.append(genFeature.getEDefault());
    stringBuffer.append(TEXT_863);
    }
    if (!genModel.isVirtualDelegation() || genFeature.isPrimitiveType()) {
    if (genClass.isESetFlag(genFeature)) {
    stringBuffer.append(TEXT_864);
    stringBuffer.append(genClass.getESetFlagsField(genFeature));
    stringBuffer.append(TEXT_865);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_866);
    } else {
    stringBuffer.append(TEXT_867);
    stringBuffer.append(genFeature.getUncapName());
    stringBuffer.append(TEXT_868);
    }
    }
    if (!genModel.isSuppressNotification()) {
    stringBuffer.append(TEXT_869);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.impl.ENotificationImpl"));
    stringBuffer.append(TEXT_870);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.notify.Notification"));
    stringBuffer.append(TEXT_871);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(TEXT_872);
    if (genModel.isVirtualDelegation() && !genFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_873);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_874);
    stringBuffer.append(genFeature.getEDefault());
    } else {
    stringBuffer.append(TEXT_875);
    stringBuffer.append(genFeature.getCapName());
    }
    stringBuffer.append(TEXT_876);
    stringBuffer.append(genFeature.getEDefault());
    stringBuffer.append(TEXT_877);
    if (genModel.isVirtualDelegation() && !genFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_878);
    } else {
    stringBuffer.append(TEXT_879);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_880);
    }
    stringBuffer.append(TEXT_881);
    }
    }
    }
    } else if (genFeature.hasDelegateFeature()) { GenFeature delegateFeature = genFeature.getDelegateFeature();
    if (delegateFeature.isWrappedFeatureMapType()) {
    stringBuffer.append(TEXT_882);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.FeatureMap"));
    stringBuffer.append(TEXT_883);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.FeatureMap"));
    stringBuffer.append(TEXT_884);
    stringBuffer.append(delegateFeature.getAccessorName());
    stringBuffer.append(TEXT_885);
    stringBuffer.append(genFeature.getQualifiedFeatureAccessor());
    stringBuffer.append(TEXT_886);
    } else {
    stringBuffer.append(TEXT_887);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.FeatureMap"));
    stringBuffer.append(TEXT_888);
    stringBuffer.append(delegateFeature.getAccessorName());
    stringBuffer.append(TEXT_889);
    stringBuffer.append(genFeature.getQualifiedFeatureAccessor());
    stringBuffer.append(TEXT_890);
    }
    } else {
    stringBuffer.append(TEXT_891);
    stringBuffer.append(genFeature.getFormattedName());
    stringBuffer.append(TEXT_892);
    stringBuffer.append(genFeature.getFeatureKind());
    stringBuffer.append(TEXT_893);
    //Class/unsetGenFeature.todo.override.javajetinc
    }
    stringBuffer.append(TEXT_894);
    }
    //Class/unsetGenFeature.override.javajetinc
    }
    if (genFeature.isIsSet() && (isImplementation || !genFeature.isSuppressedIsSetVisibility())) {
    if (isInterface) {
    stringBuffer.append(TEXT_895);
    stringBuffer.append(genClass.getQualifiedInterfaceName());
    stringBuffer.append(TEXT_896);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_897);
    stringBuffer.append(genFeature.getFormattedName());
    stringBuffer.append(TEXT_898);
    stringBuffer.append(genFeature.getFeatureKind());
    stringBuffer.append(TEXT_899);
    stringBuffer.append(genFeature.getFormattedName());
    stringBuffer.append(TEXT_900);
    stringBuffer.append(genFeature.getFeatureKind());
    stringBuffer.append(TEXT_901);
    if (genFeature.isChangeable() && !genFeature.isSuppressedUnsetVisibility()) {
    stringBuffer.append(TEXT_902);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_903);
    }
    stringBuffer.append(TEXT_904);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_905);
    if (!genFeature.isListType() && genFeature.isChangeable() && !genFeature.isSuppressedSetVisibility()) {
    stringBuffer.append(TEXT_906);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_907);
    stringBuffer.append(genFeature.getImportedType());
    stringBuffer.append(TEXT_908);
    }
    stringBuffer.append(TEXT_909);
    } else {
    stringBuffer.append(TEXT_910);
    }
    if (!isImplementation) {
    stringBuffer.append(TEXT_911);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_912);
    } else {
    stringBuffer.append(TEXT_913);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_914);
    if (genModel.isReflectiveDelegation()) {
    stringBuffer.append(TEXT_915);
    stringBuffer.append(genFeature.getQualifiedFeatureAccessor());
    stringBuffer.append(TEXT_916);
    } else if (!genFeature.isVolatile()) {
    if (genFeature.isListType()) {
    if (genModel.isVirtualDelegation()) {
    stringBuffer.append(TEXT_917);
    stringBuffer.append(genFeature.getImportedType());
    stringBuffer.append(TEXT_918);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_919);
    stringBuffer.append(genFeature.getImportedType());
    stringBuffer.append(TEXT_920);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(TEXT_921);
    }
    stringBuffer.append(TEXT_922);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_923);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.InternalEList"));
    stringBuffer.append(TEXT_924);
    stringBuffer.append(singleWildcard);
    stringBuffer.append(TEXT_925);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_926);
    } else {
    if (genModel.isVirtualDelegation() && !genFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_927);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(TEXT_928);
    } else if (genClass.isESetFlag(genFeature)) {
    stringBuffer.append(TEXT_929);
    stringBuffer.append(genClass.getESetFlagsField(genFeature));
    stringBuffer.append(TEXT_930);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_931);
    } else {
    stringBuffer.append(TEXT_932);
    stringBuffer.append(genFeature.getUncapName());
    stringBuffer.append(TEXT_933);
    }
    }
    } else if (genFeature.hasDelegateFeature()) { GenFeature delegateFeature = genFeature.getDelegateFeature();
    if (delegateFeature.isWrappedFeatureMapType()) {
    stringBuffer.append(TEXT_934);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.FeatureMap"));
    stringBuffer.append(TEXT_935);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.FeatureMap"));
    stringBuffer.append(TEXT_936);
    stringBuffer.append(delegateFeature.getAccessorName());
    stringBuffer.append(TEXT_937);
    stringBuffer.append(genFeature.getQualifiedFeatureAccessor());
    stringBuffer.append(TEXT_938);
    } else {
    stringBuffer.append(TEXT_939);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.FeatureMap"));
    stringBuffer.append(TEXT_940);
    stringBuffer.append(delegateFeature.getAccessorName());
    stringBuffer.append(TEXT_941);
    stringBuffer.append(genFeature.getQualifiedFeatureAccessor());
    stringBuffer.append(TEXT_942);
    }
    } else {
    stringBuffer.append(TEXT_943);
    stringBuffer.append(genFeature.getFormattedName());
    stringBuffer.append(TEXT_944);
    stringBuffer.append(genFeature.getFeatureKind());
    stringBuffer.append(TEXT_945);
    //Class/isSetGenFeature.todo.override.javajetinc
    }
    stringBuffer.append(TEXT_946);
    }
    //Class/isSetGenFeature.override.javajetinc
    }
    //Class/genFeature.override.javajetinc
    }//for
    }}.run();
    for (Iterator i= (isImplementation ? genClass.getImplementedGenOperations() : genClass.getDeclaredGenOperations()).iterator(); i.hasNext();) { GenOperation genOperation = (GenOperation)i.next();
    if (isInterface) {
    stringBuffer.append(TEXT_947);
    if (genOperation.hasDocumentation()) {
    stringBuffer.append(TEXT_948);
    stringBuffer.append(genOperation.getDocumentation(genModel.getIndentation(stringBuffer)));
    stringBuffer.append(TEXT_949);
    }
    if (!genModel.isSuppressEMFModelTags()) { boolean first = true; for (StringTokenizer stringTokenizer = new StringTokenizer(genOperation.getModelInfo(), "\n\r"); stringTokenizer.hasMoreTokens(); ) { String modelInfo = stringTokenizer.nextToken(); if (first) { first = false;
    stringBuffer.append(TEXT_950);
    stringBuffer.append(modelInfo);
    } else {
    stringBuffer.append(TEXT_951);
    stringBuffer.append(modelInfo);
    }} if (first) {
    stringBuffer.append(TEXT_952);
    }}
    stringBuffer.append(TEXT_953);
    } else {
    stringBuffer.append(TEXT_954);
    }
    if (!isImplementation) {
    stringBuffer.append(TEXT_955);
    stringBuffer.append(genOperation.getTypeParameters());
    stringBuffer.append(genOperation.getImportedType());
    stringBuffer.append(TEXT_956);
    stringBuffer.append(genOperation.getName());
    stringBuffer.append(TEXT_957);
    stringBuffer.append(genOperation.getParameters());
    stringBuffer.append(TEXT_958);
    stringBuffer.append(genOperation.getThrows());
    stringBuffer.append(TEXT_959);
    } else {
    stringBuffer.append(TEXT_960);
    stringBuffer.append(genOperation.getTypeParameters());
    stringBuffer.append(genOperation.getImportedType());
    stringBuffer.append(TEXT_961);
    stringBuffer.append(genOperation.getName());
    stringBuffer.append(TEXT_962);
    stringBuffer.append(genOperation.getParameters());
    stringBuffer.append(TEXT_963);
    stringBuffer.append(genOperation.getThrows());
    stringBuffer.append(TEXT_964);
    if (genOperation.hasBody()) {
    stringBuffer.append(TEXT_965);
    stringBuffer.append(genOperation.getBody(genModel.getIndentation(stringBuffer)));
    } else if (genOperation.isInvariant()) {GenClass opClass = genOperation.getGenClass(); String diagnostics = ((GenParameter)genOperation.getGenParameters().get(0)).getName(); String context = ((GenParameter)genOperation.getGenParameters().get(1)).getName();
    stringBuffer.append(TEXT_966);
    stringBuffer.append(diagnostics);
    stringBuffer.append(TEXT_967);
    stringBuffer.append(diagnostics);
    stringBuffer.append(TEXT_968);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.util.BasicDiagnostic"));
    stringBuffer.append(TEXT_969);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.util.Diagnostic"));
    stringBuffer.append(TEXT_970);
    stringBuffer.append(opClass.getGenPackage().getImportedValidatorClassName());
    stringBuffer.append(TEXT_971);
    stringBuffer.append(opClass.getGenPackage().getImportedValidatorClassName());
    stringBuffer.append(TEXT_972);
    stringBuffer.append(opClass.getOperationID(genOperation));
    stringBuffer.append(TEXT_973);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.plugin.EcorePlugin"));
    stringBuffer.append(TEXT_974);
    stringBuffer.append(genOperation.getName());
    stringBuffer.append(TEXT_975);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.EObjectValidator"));
    stringBuffer.append(TEXT_976);
    stringBuffer.append(context);
    stringBuffer.append(TEXT_977);
    stringBuffer.append(genModel.getNonNLS());
    stringBuffer.append(genModel.getNonNLS(2));
    stringBuffer.append(TEXT_978);
    } else {
    stringBuffer.append(TEXT_979);
    //Class/implementedGenOperation.todo.override.javajetinc
    }
    stringBuffer.append(TEXT_980);
    }
    //Class/implementedGenOperation.override.javajetinc
    }//for
    if (isImplementation && !genModel.isReflectiveDelegation() && genClass.implementsAny(genClass.getEInverseAddGenFeatures())) {
    stringBuffer.append(TEXT_981);
    if (genModel.useGenerics()) {
    for (Iterator i=genClass.getEInverseAddGenFeatures().iterator(); i.hasNext();) { GenFeature genFeature = (GenFeature)i.next();
    if (genFeature.isUncheckedCast()) {
    stringBuffer.append(TEXT_982);
    break; }
    }
    }
    if (genModel.useClassOverrideAnnotation()) {
    stringBuffer.append(TEXT_983);
    }
    stringBuffer.append(TEXT_984);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.notify.NotificationChain"));
    stringBuffer.append(TEXT_985);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.InternalEObject"));
    stringBuffer.append(TEXT_986);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.notify.NotificationChain"));
    stringBuffer.append(TEXT_987);
    for (Iterator i=genClass.getEInverseAddGenFeatures().iterator(); i.hasNext();) { GenFeature genFeature = (GenFeature)i.next();
    stringBuffer.append(TEXT_988);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(TEXT_989);
    if (genFeature.isListType()) { String cast = "("  + genModel.getImportedName("org.eclipse.emf.ecore.util.InternalEList") + (!genModel.useGenerics() ? ")" : "<" + genModel.getImportedName("org.eclipse.emf.ecore.InternalEObject") + ">)(" + genModel.getImportedName("org.eclipse.emf.ecore.util.InternalEList") + "<?>)");
    if (genFeature.isMapType() && genFeature.isEffectiveSuppressEMFTypes()) {
    stringBuffer.append(TEXT_990);
    stringBuffer.append(cast);
    stringBuffer.append(TEXT_991);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.util.EMap"));
    stringBuffer.append(TEXT_992);
    stringBuffer.append(genFeature.getImportedMapTemplateArguments());
    stringBuffer.append(TEXT_993);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_994);
    } else {
    stringBuffer.append(TEXT_995);
    stringBuffer.append(cast);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_996);
    }
    } else if (genFeature.isContainer()) {
    stringBuffer.append(TEXT_997);
    if (genFeature.isBasicSet()) {
    stringBuffer.append(TEXT_998);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_999);
    stringBuffer.append(genFeature.getImportedType());
    stringBuffer.append(TEXT_1000);
    } else {
    stringBuffer.append(TEXT_1001);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(TEXT_1002);
    }
    } else {
    if (genClass.getImplementingGenModel(genFeature).isVirtualDelegation()) {
    stringBuffer.append(TEXT_1003);
    stringBuffer.append(genFeature.getImportedType());
    stringBuffer.append(TEXT_1004);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_1005);
    stringBuffer.append(genFeature.getImportedType());
    stringBuffer.append(TEXT_1006);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(TEXT_1007);
    }
    stringBuffer.append(TEXT_1008);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_1009);
    if (genFeature.isEffectiveContains()) {
    stringBuffer.append(TEXT_1010);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.InternalEObject"));
    stringBuffer.append(TEXT_1011);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_1012);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(TEXT_1013);
    } else { GenFeature reverseFeature = genFeature.getReverse(); GenClass targetClass = reverseFeature.getGenClass();
    stringBuffer.append(TEXT_1014);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.InternalEObject"));
    stringBuffer.append(TEXT_1015);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_1016);
    stringBuffer.append(targetClass.getQualifiedFeatureID(reverseFeature));
    stringBuffer.append(TEXT_1017);
    stringBuffer.append(targetClass.getImportedInterfaceName());
    stringBuffer.append(TEXT_1018);
    }
    stringBuffer.append(TEXT_1019);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_1020);
    stringBuffer.append(genFeature.getImportedType());
    stringBuffer.append(TEXT_1021);
    }
    }
    stringBuffer.append(TEXT_1022);
    if (genModel.isMinimalReflectiveMethods()) {
    stringBuffer.append(TEXT_1023);
    } else {
    stringBuffer.append(TEXT_1024);
    }
    stringBuffer.append(TEXT_1025);
    }
    if (isImplementation && !genModel.isReflectiveDelegation() && genClass.implementsAny(genClass.getEInverseRemoveGenFeatures())) {
    stringBuffer.append(TEXT_1026);
    if (genModel.useClassOverrideAnnotation()) {
    stringBuffer.append(TEXT_1027);
    }
    stringBuffer.append(TEXT_1028);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.notify.NotificationChain"));
    stringBuffer.append(TEXT_1029);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.InternalEObject"));
    stringBuffer.append(TEXT_1030);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.notify.NotificationChain"));
    stringBuffer.append(TEXT_1031);
    for (Iterator i=genClass.getEInverseRemoveGenFeatures().iterator(); i.hasNext();) { GenFeature genFeature = (GenFeature)i.next();
    stringBuffer.append(TEXT_1032);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(TEXT_1033);
    if (genFeature.isListType()) {
    if (genFeature.isMapType() && genFeature.isEffectiveSuppressEMFTypes()) {
    stringBuffer.append(TEXT_1034);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.InternalEList"));
    stringBuffer.append(singleWildcard);
    stringBuffer.append(TEXT_1035);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.util.EMap"));
    stringBuffer.append(TEXT_1036);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_1037);
    } else if (genFeature.isWrappedFeatureMapType()) {
    stringBuffer.append(TEXT_1038);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.InternalEList"));
    stringBuffer.append(singleWildcard);
    stringBuffer.append(TEXT_1039);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.FeatureMap"));
    stringBuffer.append(TEXT_1040);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_1041);
    } else {
    stringBuffer.append(TEXT_1042);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.InternalEList"));
    stringBuffer.append(singleWildcard);
    stringBuffer.append(TEXT_1043);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_1044);
    }
    } else if (genFeature.isContainer() && !genFeature.isBasicSet()) {
    stringBuffer.append(TEXT_1045);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(TEXT_1046);
    } else if (genFeature.isUnsettable()) {
    stringBuffer.append(TEXT_1047);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_1048);
    } else {
    stringBuffer.append(TEXT_1049);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_1050);
    }
    }
    stringBuffer.append(TEXT_1051);
    if (genModel.isMinimalReflectiveMethods()) {
    stringBuffer.append(TEXT_1052);
    } else {
    stringBuffer.append(TEXT_1053);
    }
    stringBuffer.append(TEXT_1054);
    }
    if (isImplementation && !genModel.isReflectiveDelegation() && genClass.implementsAny(genClass.getEBasicRemoveFromContainerGenFeatures())) {
    stringBuffer.append(TEXT_1055);
    if (genModel.useClassOverrideAnnotation()) {
    stringBuffer.append(TEXT_1056);
    }
    stringBuffer.append(TEXT_1057);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.notify.NotificationChain"));
    stringBuffer.append(TEXT_1058);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.notify.NotificationChain"));
    stringBuffer.append(TEXT_1059);
    for (Iterator i=genClass.getEBasicRemoveFromContainerGenFeatures().iterator(); i.hasNext();) { GenFeature genFeature = (GenFeature)i.next();
    GenFeature reverseFeature = genFeature.getReverse(); GenClass targetClass = reverseFeature.getGenClass();
    stringBuffer.append(TEXT_1060);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(TEXT_1061);
    stringBuffer.append(targetClass.getQualifiedFeatureID(reverseFeature));
    stringBuffer.append(TEXT_1062);
    stringBuffer.append(targetClass.getImportedInterfaceName());
    stringBuffer.append(TEXT_1063);
    }
    stringBuffer.append(TEXT_1064);
    if (genModel.isMinimalReflectiveMethods()) {
    stringBuffer.append(TEXT_1065);
    } else {
    stringBuffer.append(TEXT_1066);
    }
    stringBuffer.append(TEXT_1067);
    }
    if (isImplementation && !genModel.isReflectiveDelegation() && !genClass.getImplementedGenFeatures().isEmpty()) {
    stringBuffer.append(TEXT_1068);
    if (genModel.useClassOverrideAnnotation()) {
    stringBuffer.append(TEXT_1069);
    }
    stringBuffer.append(TEXT_1070);
    for (Iterator i=genModel.isMinimalReflectiveMethods() ? genClass.getImplementedGenFeatures().iterator() : genClass.getAllGenFeatures().iterator(); i.hasNext();) { GenFeature genFeature = (GenFeature)i.next();
    stringBuffer.append(TEXT_1071);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(TEXT_1072);
    if (genFeature.isPrimitiveType()) {
    if (genFeature.isBooleanType()) {
    stringBuffer.append(TEXT_1073);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_1074);
    } else {
    stringBuffer.append(TEXT_1075);
    stringBuffer.append(genFeature.getObjectType());
    stringBuffer.append(TEXT_1076);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_1077);
    }
    } else if (genFeature.isResolveProxies() && !genFeature.isListType()) {
    stringBuffer.append(TEXT_1078);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_1079);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_1080);
    } else if (genFeature.isMapType()) {
    if (genFeature.isEffectiveSuppressEMFTypes()) {
    stringBuffer.append(TEXT_1081);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.util.EMap"));
    stringBuffer.append(TEXT_1082);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_1083);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_1084);
    } else {
    stringBuffer.append(TEXT_1085);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_1086);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_1087);
    }
    } else if (genFeature.isWrappedFeatureMapType()) {
    stringBuffer.append(TEXT_1088);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.FeatureMap"));
    stringBuffer.append(TEXT_1089);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_1090);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_1091);
    } else if (genFeature.isFeatureMapType()) {
    stringBuffer.append(TEXT_1092);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_1093);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.FeatureMap"));
    stringBuffer.append(TEXT_1094);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_1095);
    } else {
    stringBuffer.append(TEXT_1096);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_1097);
    }
    }
    stringBuffer.append(TEXT_1098);
    if (genModel.isMinimalReflectiveMethods()) {
    stringBuffer.append(TEXT_1099);
    } else {
    stringBuffer.append(TEXT_1100);
    }
    stringBuffer.append(TEXT_1101);
    }
    if (isImplementation && !genModel.isReflectiveDelegation() && genClass.implementsAny(genClass.getESetGenFeatures())) {
    stringBuffer.append(TEXT_1102);
    if (genModel.useGenerics()) {
    for (Iterator i=genClass.getESetGenFeatures().iterator(); i.hasNext();) { GenFeature genFeature = (GenFeature)i.next();
    if (genFeature.isUncheckedCast() && !genFeature.isFeatureMapType() && !genFeature.isMapType()) {
    stringBuffer.append(TEXT_1103);
    break; }
    }
    }
    if (genModel.useClassOverrideAnnotation()) {
    stringBuffer.append(TEXT_1104);
    }
    stringBuffer.append(TEXT_1105);
    for (Iterator i=genClass.getESetGenFeatures().iterator(); i.hasNext();) { GenFeature genFeature = (GenFeature)i.next();
    stringBuffer.append(TEXT_1106);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(TEXT_1107);
    if (genFeature.isListType()) {
    if (genFeature.isWrappedFeatureMapType()) {
    stringBuffer.append(TEXT_1108);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.FeatureMap"));
    stringBuffer.append(TEXT_1109);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.FeatureMap"));
    stringBuffer.append(TEXT_1110);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_1111);
    } else if (genFeature.isFeatureMapType()) {
    stringBuffer.append(TEXT_1112);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.FeatureMap"));
    stringBuffer.append(TEXT_1113);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_1114);
    } else if (genFeature.isMapType()) {
    if (genFeature.isEffectiveSuppressEMFTypes()) {
    stringBuffer.append(TEXT_1115);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.EStructuralFeature"));
    stringBuffer.append(TEXT_1116);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.util.EMap"));
    stringBuffer.append(TEXT_1117);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_1118);
    } else {
    stringBuffer.append(TEXT_1119);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.EStructuralFeature"));
    stringBuffer.append(TEXT_1120);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_1121);
    }
    } else {
    stringBuffer.append(TEXT_1122);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_1123);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_1124);
    stringBuffer.append(genModel.getImportedName("java.util.Collection"));
    if (genModel.getComplianceLevel().getValue() >= GenJDKLevel.JDK50) {
    stringBuffer.append(TEXT_1125);
    stringBuffer.append(genFeature.getListItemType());
    stringBuffer.append(TEXT_1126);
    }
    stringBuffer.append(TEXT_1127);
    }
    } else if (genFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_1128);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_1129);
    stringBuffer.append(genFeature.getObjectType());
    stringBuffer.append(TEXT_1130);
    stringBuffer.append(genFeature.getPrimitiveValueFunction());
    stringBuffer.append(TEXT_1131);
    } else {
    stringBuffer.append(TEXT_1132);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_1133);
    if (genFeature.getTypeGenDataType() == null || !genFeature.getTypeGenDataType().isObjectType() || !genFeature.getRawType().equals(genFeature.getType())) {
    stringBuffer.append(TEXT_1134);
    stringBuffer.append(genFeature.getImportedType());
    stringBuffer.append(TEXT_1135);
    }
    stringBuffer.append(TEXT_1136);
    }
    stringBuffer.append(TEXT_1137);
    }
    stringBuffer.append(TEXT_1138);
    if (genModel.isMinimalReflectiveMethods()) {
    stringBuffer.append(TEXT_1139);
    } else {
    stringBuffer.append(TEXT_1140);
    }
    stringBuffer.append(TEXT_1141);
    if (genModel.useClassOverrideAnnotation()) {
    stringBuffer.append(TEXT_1142);
    }
    stringBuffer.append(TEXT_1143);
    for (Iterator i=genClass.getESetGenFeatures().iterator(); i.hasNext();) { GenFeature genFeature = (GenFeature)i.next();
    stringBuffer.append(TEXT_1144);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(TEXT_1145);
    if (genFeature.isListType() && !genFeature.isUnsettable()) {
    if (genFeature.isWrappedFeatureMapType()) {
    stringBuffer.append(TEXT_1146);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.FeatureMap"));
    stringBuffer.append(TEXT_1147);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_1148);
    } else {
    stringBuffer.append(TEXT_1149);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_1150);
    }
    } else if (genFeature.isUnsettable()) {
    stringBuffer.append(TEXT_1151);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_1152);
    } else if (!genFeature.hasEDefault()) {
    stringBuffer.append(TEXT_1153);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_1154);
    stringBuffer.append(genFeature.getImportedType());
    stringBuffer.append(TEXT_1155);
    } else {
    stringBuffer.append(TEXT_1156);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_1157);
    stringBuffer.append(genFeature.getEDefault());
    stringBuffer.append(TEXT_1158);
    }
    stringBuffer.append(TEXT_1159);
    }
    stringBuffer.append(TEXT_1160);
    if (genModel.isMinimalReflectiveMethods()) {
    stringBuffer.append(TEXT_1161);
    } else {
    stringBuffer.append(TEXT_1162);
    }
    stringBuffer.append(TEXT_1163);
    }
    if (isImplementation && !genModel.isReflectiveDelegation() && !genClass.getImplementedGenFeatures().isEmpty()) {
    stringBuffer.append(TEXT_1164);
    if (genModel.useClassOverrideAnnotation()) {
    stringBuffer.append(TEXT_1165);
    }
    stringBuffer.append(TEXT_1166);
    for (Iterator i=genModel.isMinimalReflectiveMethods() ? genClass.getImplementedGenFeatures().iterator() : genClass.getAllGenFeatures().iterator(); i.hasNext();) { GenFeature genFeature = (GenFeature)i.next();
    stringBuffer.append(TEXT_1167);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(TEXT_1168);
    if (genFeature.isListType() && !genFeature.isUnsettable()) {
    if (genFeature.isWrappedFeatureMapType()) {
    if (genFeature.isVolatile()) {
    stringBuffer.append(TEXT_1169);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.FeatureMap"));
    stringBuffer.append(TEXT_1170);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_1171);
    } else {
    stringBuffer.append(TEXT_1172);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_1173);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_1174);
    }
    } else {
    if (genClass.isField(genFeature)) {
    stringBuffer.append(TEXT_1175);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_1176);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_1177);
    } else {
    if (genFeature.isField() && genClass.getImplementingGenModel(genFeature).isVirtualDelegation()) {
    stringBuffer.append(TEXT_1178);
    stringBuffer.append(genFeature.getImportedType());
    stringBuffer.append(TEXT_1179);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_1180);
    stringBuffer.append(genFeature.getImportedType());
    stringBuffer.append(TEXT_1181);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(TEXT_1182);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_1183);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_1184);
    } else {
    stringBuffer.append(TEXT_1185);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_1186);
    }
    }
    }
    } else if (genFeature.isUnsettable()) {
    stringBuffer.append(TEXT_1187);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_1188);
    } else if (genFeature.isResolveProxies()) {
    if (genClass.isField(genFeature)) {
    stringBuffer.append(TEXT_1189);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_1190);
    } else {
    if (genFeature.isField() && genClass.getImplementingGenModel(genFeature).isVirtualDelegation()) {
    stringBuffer.append(TEXT_1191);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(TEXT_1192);
    } else {
    stringBuffer.append(TEXT_1193);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_1194);
    }
    }
    } else if (!genFeature.hasEDefault()) {
    if (genClass.isField(genFeature)) {
    stringBuffer.append(TEXT_1195);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_1196);
    } else {
    if (genFeature.isField() && genClass.getImplementingGenModel(genFeature).isVirtualDelegation()) {
    stringBuffer.append(TEXT_1197);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(TEXT_1198);
    } else {
    stringBuffer.append(TEXT_1199);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_1200);
    }
    }
    } else if (genFeature.isPrimitiveType() || genFeature.isEnumType()) {
    if (genClass.isField(genFeature)) {
    if (genClass.isFlag(genFeature)) {
    stringBuffer.append(TEXT_1201);
    stringBuffer.append(genClass.getFlagsField(genFeature));
    stringBuffer.append(TEXT_1202);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_1203);
    stringBuffer.append(genFeature.getEDefault());
    stringBuffer.append(TEXT_1204);
    } else {
    stringBuffer.append(TEXT_1205);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_1206);
    stringBuffer.append(genFeature.getEDefault());
    stringBuffer.append(TEXT_1207);
    }
    } else {
    if (genFeature.isEnumType() && genFeature.isField() && genClass.getImplementingGenModel(genFeature).isVirtualDelegation()) {
    stringBuffer.append(TEXT_1208);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(TEXT_1209);
    stringBuffer.append(genFeature.getEDefault());
    stringBuffer.append(TEXT_1210);
    stringBuffer.append(genFeature.getEDefault());
    stringBuffer.append(TEXT_1211);
    } else {
    stringBuffer.append(TEXT_1212);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_1213);
    stringBuffer.append(genFeature.getEDefault());
    stringBuffer.append(TEXT_1214);
    }
    }
    } else {//datatype
    if (genClass.isField(genFeature)) {
    stringBuffer.append(TEXT_1215);
    stringBuffer.append(genFeature.getEDefault());
    stringBuffer.append(TEXT_1216);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_1217);
    stringBuffer.append(genFeature.getEDefault());
    stringBuffer.append(TEXT_1218);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_1219);
    } else {
    if (genFeature.isField() && genClass.getImplementingGenModel(genFeature).isVirtualDelegation()) {
    stringBuffer.append(TEXT_1220);
    stringBuffer.append(genFeature.getImportedType());
    stringBuffer.append(TEXT_1221);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_1222);
    stringBuffer.append(genFeature.getImportedType());
    stringBuffer.append(TEXT_1223);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(TEXT_1224);
    stringBuffer.append(genFeature.getEDefault());
    stringBuffer.append(TEXT_1225);
    stringBuffer.append(genFeature.getEDefault());
    stringBuffer.append(TEXT_1226);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_1227);
    stringBuffer.append(genFeature.getEDefault());
    stringBuffer.append(TEXT_1228);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_1229);
    } else {
    stringBuffer.append(TEXT_1230);
    stringBuffer.append(genFeature.getEDefault());
    stringBuffer.append(TEXT_1231);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_1232);
    stringBuffer.append(genFeature.getEDefault());
    stringBuffer.append(TEXT_1233);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_1234);
    }
    }
    }
    }
    stringBuffer.append(TEXT_1235);
    if (genModel.isMinimalReflectiveMethods()) {
    stringBuffer.append(TEXT_1236);
    } else {
    stringBuffer.append(TEXT_1237);
    }
    stringBuffer.append(TEXT_1238);
    //Class/eIsSet.override.javajetinc
    }
    if (isImplementation && !genClass.getMixinGenFeatures().isEmpty()) {
    stringBuffer.append(TEXT_1239);
    if (genModel.useClassOverrideAnnotation()) {
    stringBuffer.append(TEXT_1240);
    }
    stringBuffer.append(TEXT_1241);
    stringBuffer.append(singleWildcard);
    stringBuffer.append(TEXT_1242);
    for (Iterator m=genClass.getMixinGenClasses().iterator(); m.hasNext();) { GenClass mixinGenClass = (GenClass)m.next(); 
    stringBuffer.append(TEXT_1243);
    stringBuffer.append(mixinGenClass.getImportedInterfaceName());
    stringBuffer.append(TEXT_1244);
    for (Iterator f=mixinGenClass.getGenFeatures().iterator(); f.hasNext();) { GenFeature genFeature = (GenFeature)f.next(); 
    stringBuffer.append(TEXT_1245);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(TEXT_1246);
    stringBuffer.append(mixinGenClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(TEXT_1247);
    }
    stringBuffer.append(TEXT_1248);
    }
    stringBuffer.append(TEXT_1249);
    if (genModel.useClassOverrideAnnotation()) {
    stringBuffer.append(TEXT_1250);
    }
    stringBuffer.append(TEXT_1251);
    stringBuffer.append(singleWildcard);
    stringBuffer.append(TEXT_1252);
    for (Iterator m=genClass.getMixinGenClasses().iterator(); m.hasNext();) { GenClass mixinGenClass = (GenClass)m.next(); 
    stringBuffer.append(TEXT_1253);
    stringBuffer.append(mixinGenClass.getImportedInterfaceName());
    stringBuffer.append(TEXT_1254);
    for (Iterator f=mixinGenClass.getGenFeatures().iterator(); f.hasNext();) { GenFeature genFeature = (GenFeature)f.next(); 
    stringBuffer.append(TEXT_1255);
    stringBuffer.append(mixinGenClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(TEXT_1256);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(TEXT_1257);
    }
    stringBuffer.append(TEXT_1258);
    }
    stringBuffer.append(TEXT_1259);
    }
    if (isImplementation && genModel.isVirtualDelegation()) { String eVirtualValuesField = genClass.getEVirtualValuesField();
    if (eVirtualValuesField != null) {
    stringBuffer.append(TEXT_1260);
    if (genModel.useClassOverrideAnnotation()) {
    stringBuffer.append(TEXT_1261);
    }
    stringBuffer.append(TEXT_1262);
    stringBuffer.append(eVirtualValuesField);
    stringBuffer.append(TEXT_1263);
    if (genModel.useClassOverrideAnnotation()) {
    stringBuffer.append(TEXT_1264);
    }
    stringBuffer.append(TEXT_1265);
    stringBuffer.append(eVirtualValuesField);
    stringBuffer.append(TEXT_1266);
    }
    { List eVirtualIndexBitFields = genClass.getEVirtualIndexBitFields(new ArrayList());
    if (!eVirtualIndexBitFields.isEmpty()) { List allEVirtualIndexBitFields = genClass.getAllEVirtualIndexBitFields(new ArrayList());
    stringBuffer.append(TEXT_1267);
    if (genModel.useClassOverrideAnnotation()) {
    stringBuffer.append(TEXT_1268);
    }
    stringBuffer.append(TEXT_1269);
    for (int i = 0; i < allEVirtualIndexBitFields.size(); i++) {
    stringBuffer.append(TEXT_1270);
    stringBuffer.append(i);
    stringBuffer.append(TEXT_1271);
    stringBuffer.append(allEVirtualIndexBitFields.get(i));
    stringBuffer.append(TEXT_1272);
    }
    stringBuffer.append(TEXT_1273);
    if (genModel.useClassOverrideAnnotation()) {
    stringBuffer.append(TEXT_1274);
    }
    stringBuffer.append(TEXT_1275);
    for (int i = 0; i < allEVirtualIndexBitFields.size(); i++) {
    stringBuffer.append(TEXT_1276);
    stringBuffer.append(i);
    stringBuffer.append(TEXT_1277);
    stringBuffer.append(allEVirtualIndexBitFields.get(i));
    stringBuffer.append(TEXT_1278);
    }
    stringBuffer.append(TEXT_1279);
    }
    }
    }
    if (isImplementation && !genModel.isReflectiveDelegation() && !genClass.getToStringGenFeatures().isEmpty()) {
    stringBuffer.append(TEXT_1280);
    if (genModel.useClassOverrideAnnotation()) {
    stringBuffer.append(TEXT_1281);
    }
    stringBuffer.append(TEXT_1282);
    { boolean first = true;
    for (Iterator i=genClass.getToStringGenFeatures().iterator(); i.hasNext(); ) { GenFeature genFeature = (GenFeature)i.next();
    if (first) { first = false;
    stringBuffer.append(TEXT_1283);
    stringBuffer.append(genFeature.getName());
    stringBuffer.append(TEXT_1284);
    stringBuffer.append(genModel.getNonNLS());
    } else {
    stringBuffer.append(TEXT_1285);
    stringBuffer.append(genFeature.getName());
    stringBuffer.append(TEXT_1286);
    stringBuffer.append(genModel.getNonNLS());
    }
    if (genFeature.isUnsettable() && !genFeature.isListType()) {
    if (genModel.isVirtualDelegation() && !genFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_1287);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(TEXT_1288);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(TEXT_1289);
    stringBuffer.append(genModel.getNonNLS());
    } else {
    if (genClass.isFlag(genFeature)) {
    stringBuffer.append(TEXT_1290);
    if (genClass.isESetFlag(genFeature)) {
    stringBuffer.append(TEXT_1291);
    stringBuffer.append(genClass.getESetFlagsField(genFeature));
    stringBuffer.append(TEXT_1292);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_1293);
    } else {
    stringBuffer.append(genFeature.getUncapName());
    stringBuffer.append(TEXT_1294);
    }
    stringBuffer.append(TEXT_1295);
    stringBuffer.append(genClass.getFlagsField(genFeature));
    stringBuffer.append(TEXT_1296);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_1297);
    stringBuffer.append(genModel.getNonNLS());
    } else {
    stringBuffer.append(TEXT_1298);
    if (genClass.isESetFlag(genFeature)) {
    stringBuffer.append(TEXT_1299);
    stringBuffer.append(genClass.getESetFlagsField(genFeature));
    stringBuffer.append(TEXT_1300);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_1301);
    } else {
    stringBuffer.append(genFeature.getUncapName());
    stringBuffer.append(TEXT_1302);
    }
    stringBuffer.append(TEXT_1303);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_1304);
    stringBuffer.append(genModel.getNonNLS());
    }
    }
    } else {
    if (genModel.isVirtualDelegation() && !genFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_1305);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    if (!genFeature.isListType() && !genFeature.isReferenceType()){
    stringBuffer.append(TEXT_1306);
    stringBuffer.append(genFeature.getEDefault());
    }
    stringBuffer.append(TEXT_1307);
    } else {
    if (genClass.isFlag(genFeature)) {
    stringBuffer.append(TEXT_1308);
    stringBuffer.append(genClass.getFlagsField(genFeature));
    stringBuffer.append(TEXT_1309);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_1310);
    } else {
    stringBuffer.append(TEXT_1311);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_1312);
    }
    }
    }
    }
    }
    stringBuffer.append(TEXT_1313);
    }
    if (isImplementation && genClass.isMapEntry()) { GenFeature keyFeature = genClass.getMapEntryKeyFeature(); GenFeature valueFeature = genClass.getMapEntryValueFeature();
    String objectType = genModel.getImportedName("java.lang.Object");
    String keyType = genModel.getComplianceLevel().getValue() >= GenJDKLevel.JDK50 ? keyFeature.getObjectType() : objectType;
    String valueType = genModel.getComplianceLevel().getValue() >= GenJDKLevel.JDK50 ? valueFeature.getObjectType() : objectType;
    String eMapType = genModel.getImportedName("org.eclipse.emf.common.util.EMap") + (genModel.getComplianceLevel().getValue() >= GenJDKLevel.JDK50 ? "<" + keyType + ", " + valueType + ">" : "");
    stringBuffer.append(TEXT_1314);
    stringBuffer.append(objectType);
    stringBuffer.append(TEXT_1315);
    stringBuffer.append(keyType);
    stringBuffer.append(TEXT_1316);
    if (keyFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_1317);
    stringBuffer.append(keyFeature.getObjectType());
    stringBuffer.append(TEXT_1318);
    } else {
    stringBuffer.append(TEXT_1319);
    }
    stringBuffer.append(TEXT_1320);
    stringBuffer.append(keyType);
    stringBuffer.append(TEXT_1321);
    if (keyFeature.isListType()) {
    stringBuffer.append(TEXT_1322);
    stringBuffer.append(genModel.getImportedName("java.util.Collection"));
    stringBuffer.append(TEXT_1323);
    } else if (genModel.getComplianceLevel().getValue() >= GenJDKLevel.JDK50) {
    stringBuffer.append(TEXT_1324);
    } else if (keyFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_1325);
    stringBuffer.append(keyFeature.getObjectType());
    stringBuffer.append(TEXT_1326);
    stringBuffer.append(keyFeature.getPrimitiveValueFunction());
    stringBuffer.append(TEXT_1327);
    } else {
    stringBuffer.append(TEXT_1328);
    stringBuffer.append(keyFeature.getImportedType());
    stringBuffer.append(TEXT_1329);
    }
    stringBuffer.append(TEXT_1330);
    stringBuffer.append(valueType);
    stringBuffer.append(TEXT_1331);
    if (valueFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_1332);
    stringBuffer.append(valueFeature.getObjectType());
    stringBuffer.append(TEXT_1333);
    } else {
    stringBuffer.append(TEXT_1334);
    }
    stringBuffer.append(TEXT_1335);
    stringBuffer.append(valueType);
    stringBuffer.append(TEXT_1336);
    stringBuffer.append(valueType);
    stringBuffer.append(TEXT_1337);
    stringBuffer.append(valueType);
    stringBuffer.append(TEXT_1338);
    if (valueFeature.isListType()) {
    stringBuffer.append(TEXT_1339);
    stringBuffer.append(genModel.getImportedName("java.util.Collection"));
    stringBuffer.append(TEXT_1340);
    } else if (genModel.getComplianceLevel().getValue() >= GenJDKLevel.JDK50) {
    stringBuffer.append(TEXT_1341);
    } else if (valueFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_1342);
    stringBuffer.append(valueFeature.getObjectType());
    stringBuffer.append(TEXT_1343);
    stringBuffer.append(valueFeature.getPrimitiveValueFunction());
    stringBuffer.append(TEXT_1344);
    } else {
    stringBuffer.append(TEXT_1345);
    stringBuffer.append(valueFeature.getImportedType());
    stringBuffer.append(TEXT_1346);
    }
    stringBuffer.append(TEXT_1347);
    if (genModel.useGenerics()) {
    stringBuffer.append(TEXT_1348);
    }
    stringBuffer.append(TEXT_1349);
    stringBuffer.append(eMapType);
    stringBuffer.append(TEXT_1350);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.EObject"));
    stringBuffer.append(TEXT_1351);
    stringBuffer.append(eMapType);
    stringBuffer.append(TEXT_1352);
    }
    stringBuffer.append(TEXT_1353);
    stringBuffer.append(isInterface ? " " + genClass.getInterfaceName() : genClass.getClassName());
    // TODO fix the space above
    genModel.emitSortedImports();
    stringBuffer.append(TEXT_1354);
    return stringBuffer.toString();
  }
}
