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
  protected final String TEXT_55 = ";" + NL;
  protected final String TEXT_56 = NL + "\t/**" + NL + "\t * A bit field representing the indices of non-primitive feature values." + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */" + NL + "\tprotected int ";
  protected final String TEXT_57 = ";" + NL;
  protected final String TEXT_58 = NL + "\t/**" + NL + "\t * A set of bit flags representing the values of boolean attributes and whether unsettable features have been set." + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t * @ordered" + NL + "\t */" + NL + "\tprotected int ";
  protected final String TEXT_59 = " = 0;" + NL;
  protected final String TEXT_60 = NL + "\t/**" + NL + "\t * The cached value of the '{@link #";
  protected final String TEXT_61 = "() <em>";
  protected final String TEXT_62 = "</em>}' ";
  protected final String TEXT_63 = "." + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @see #";
  protected final String TEXT_64 = "()" + NL + "\t * @generated" + NL + "\t * @ordered" + NL + "\t */" + NL + "\tprotected ";
  protected final String TEXT_65 = " ";
  protected final String TEXT_66 = ";" + NL;
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
  protected final String TEXT_78 = "()" + NL + "\t * @generated" + NL + "\t * @ordered" + NL + "\t */";
  protected final String TEXT_79 = NL + "\t@SuppressWarnings(\"unchecked\")";
  protected final String TEXT_80 = NL + "\tprotected static final ";
  protected final String TEXT_81 = " ";
  protected final String TEXT_82 = "; // TODO The default value literal \"";
  protected final String TEXT_83 = "\" is not valid.";
  protected final String TEXT_84 = " = ";
  protected final String TEXT_85 = ";";
  protected final String TEXT_86 = NL;
  protected final String TEXT_87 = NL + "\t/**" + NL + "\t * An additional set of bit flags representing the values of boolean attributes and whether unsettable features have been set." + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t * @ordered" + NL + "\t */" + NL + "\tprotected int ";
  protected final String TEXT_88 = " = 0;" + NL;
  protected final String TEXT_89 = NL + "\t/**" + NL + "\t * The flag representing the value of the '{@link #";
  protected final String TEXT_90 = "() <em>";
  protected final String TEXT_91 = "</em>}' ";
  protected final String TEXT_92 = "." + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @see #";
  protected final String TEXT_93 = "()" + NL + "\t * @generated" + NL + "\t * @ordered" + NL + "\t */" + NL + "\tprotected static final int ";
  protected final String TEXT_94 = "_EFLAG = 1 ";
  protected final String TEXT_95 = ";" + NL;
  protected final String TEXT_96 = NL + "\t/**" + NL + "\t * The cached value of the '{@link #";
  protected final String TEXT_97 = "() <em>";
  protected final String TEXT_98 = "</em>}' ";
  protected final String TEXT_99 = "." + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @see #";
  protected final String TEXT_100 = "()" + NL + "\t * @generated" + NL + "\t * @ordered" + NL + "\t */" + NL + "\tprotected ";
  protected final String TEXT_101 = " ";
  protected final String TEXT_102 = " = ";
  protected final String TEXT_103 = ";" + NL;
  protected final String TEXT_104 = NL + "\t/**" + NL + "\t * An additional set of bit flags representing the values of boolean attributes and whether unsettable features have been set." + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t * @ordered" + NL + "\t */" + NL + "\tprotected int ";
  protected final String TEXT_105 = " = 0;" + NL;
  protected final String TEXT_106 = NL + "\t/**" + NL + "\t * The flag representing whether the ";
  protected final String TEXT_107 = " ";
  protected final String TEXT_108 = " has been set." + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t * @ordered" + NL + "\t */" + NL + "\tprotected static final int ";
  protected final String TEXT_109 = "_ESETFLAG = 1 ";
  protected final String TEXT_110 = ";" + NL;
  protected final String TEXT_111 = NL + "\t/**" + NL + "\t * This is true if the ";
  protected final String TEXT_112 = " ";
  protected final String TEXT_113 = " has been set." + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t * @ordered" + NL + "\t */" + NL + "\tprotected boolean ";
  protected final String TEXT_114 = "ESet;" + NL;
  protected final String TEXT_115 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */" + NL + "\tprotected ";
  protected final String TEXT_116 = "()" + NL + "\t{" + NL + "\t\tsuper();";
  protected final String TEXT_117 = NL + "\t\t";
  protected final String TEXT_118 = " |= ";
  protected final String TEXT_119 = "_EFLAG;";
  protected final String TEXT_120 = NL + "\t}" + NL + "" + NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */";
  protected final String TEXT_121 = NL + "\t@Override";
  protected final String TEXT_122 = NL + "\tprotected ";
  protected final String TEXT_123 = " eStaticClass()" + NL + "\t{" + NL + "\t\treturn ";
  protected final String TEXT_124 = ";" + NL + "\t}" + NL;
  protected final String TEXT_125 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */";
  protected final String TEXT_126 = NL + "\t@Override";
  protected final String TEXT_127 = NL + "\tprotected int eStaticFeatureCount()" + NL + "\t{" + NL + "\t\treturn ";
  protected final String TEXT_128 = ";" + NL + "\t}" + NL;
  protected final String TEXT_129 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */";
  protected final String TEXT_130 = NL + "\t";
  protected final String TEXT_131 = "[] ";
  protected final String TEXT_132 = "();" + NL;
  protected final String TEXT_133 = NL + "\tpublic ";
  protected final String TEXT_134 = "[] ";
  protected final String TEXT_135 = "()" + NL + "\t{";
  protected final String TEXT_136 = NL + "\t\t";
  protected final String TEXT_137 = " list = (";
  protected final String TEXT_138 = ")";
  protected final String TEXT_139 = "();" + NL + "\t\tif (list.isEmpty()) return ";
  protected final String TEXT_140 = "_EEMPTY_ARRAY;";
  protected final String TEXT_141 = NL + "\t\tif (";
  protected final String TEXT_142 = " == null || ";
  protected final String TEXT_143 = ".isEmpty()) return ";
  protected final String TEXT_144 = "_EEMPTY_ARRAY;" + NL + "\t\t";
  protected final String TEXT_145 = " list = (";
  protected final String TEXT_146 = ")";
  protected final String TEXT_147 = ";";
  protected final String TEXT_148 = NL + "\t\tlist.shrink();" + NL + "\t\treturn (";
  protected final String TEXT_149 = "[])list.data();" + NL + "\t}" + NL;
  protected final String TEXT_150 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */";
  protected final String TEXT_151 = NL + "\t";
  protected final String TEXT_152 = " get";
  protected final String TEXT_153 = "(int index);" + NL;
  protected final String TEXT_154 = NL + "\tpublic ";
  protected final String TEXT_155 = " get";
  protected final String TEXT_156 = "(int index)" + NL + "\t{" + NL + "\t\treturn (";
  protected final String TEXT_157 = ")";
  protected final String TEXT_158 = "().get(index);" + NL + "\t}" + NL;
  protected final String TEXT_159 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */";
  protected final String TEXT_160 = NL + "\tint get";
  protected final String TEXT_161 = "Length();" + NL;
  protected final String TEXT_162 = NL + "\tpublic int get";
  protected final String TEXT_163 = "Length()" + NL + "\t{";
  protected final String TEXT_164 = NL + "\t\treturn ";
  protected final String TEXT_165 = "().size();";
  protected final String TEXT_166 = NL + "\t\treturn ";
  protected final String TEXT_167 = " == null ? 0 : ";
  protected final String TEXT_168 = ".size();";
  protected final String TEXT_169 = NL + "\t}" + NL;
  protected final String TEXT_170 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */";
  protected final String TEXT_171 = NL + "\tvoid set";
  protected final String TEXT_172 = "(";
  protected final String TEXT_173 = "[] new";
  protected final String TEXT_174 = ");" + NL;
  protected final String TEXT_175 = NL + "\tpublic void set";
  protected final String TEXT_176 = "(";
  protected final String TEXT_177 = "[] new";
  protected final String TEXT_178 = ")" + NL + "\t{" + NL + "\t\t((";
  protected final String TEXT_179 = ")";
  protected final String TEXT_180 = "()).setData(new";
  protected final String TEXT_181 = ".length, new";
  protected final String TEXT_182 = ");" + NL + "\t}" + NL;
  protected final String TEXT_183 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */";
  protected final String TEXT_184 = NL + "\tvoid set";
  protected final String TEXT_185 = "(int index, ";
  protected final String TEXT_186 = " element);" + NL;
  protected final String TEXT_187 = NL + "\tpublic void set";
  protected final String TEXT_188 = "(int index, ";
  protected final String TEXT_189 = " element)" + NL + "\t{" + NL + "\t\t";
  protected final String TEXT_190 = "().set(index, element);" + NL + "\t}" + NL;
  protected final String TEXT_191 = NL + "\t/**" + NL + "\t * Returns the value of the '<em><b>";
  protected final String TEXT_192 = "</b></em>' ";
  protected final String TEXT_193 = ".";
  protected final String TEXT_194 = NL + "\t * The key is of type ";
  protected final String TEXT_195 = "list of {@link ";
  protected final String TEXT_196 = "}";
  protected final String TEXT_197 = "{@link ";
  protected final String TEXT_198 = "}";
  protected final String TEXT_199 = "," + NL + "\t * and the value is of type ";
  protected final String TEXT_200 = "list of {@link ";
  protected final String TEXT_201 = "}";
  protected final String TEXT_202 = "{@link ";
  protected final String TEXT_203 = "}";
  protected final String TEXT_204 = ",";
  protected final String TEXT_205 = NL + "\t * The list contents are of type {@link ";
  protected final String TEXT_206 = "}.";
  protected final String TEXT_207 = NL + "\t * The default value is <code>";
  protected final String TEXT_208 = "</code>.";
  protected final String TEXT_209 = NL + "\t * The literals are from the enumeration {@link ";
  protected final String TEXT_210 = "}.";
  protected final String TEXT_211 = NL + "\t * It is bidirectional and its opposite is '{@link ";
  protected final String TEXT_212 = "#";
  protected final String TEXT_213 = " <em>";
  protected final String TEXT_214 = "</em>}'.";
  protected final String TEXT_215 = NL + "\t * <!-- begin-user-doc -->";
  protected final String TEXT_216 = NL + "\t * <p>" + NL + "\t * If the meaning of the '<em>";
  protected final String TEXT_217 = "</em>' ";
  protected final String TEXT_218 = " isn't clear," + NL + "\t * there really should be more of a description here..." + NL + "\t * </p>";
  protected final String TEXT_219 = NL + "\t * <!-- end-user-doc -->";
  protected final String TEXT_220 = NL + "\t * <!-- begin-model-doc -->" + NL + "\t * ";
  protected final String TEXT_221 = NL + "\t * <!-- end-model-doc -->";
  protected final String TEXT_222 = NL + "\t * @return the value of the '<em>";
  protected final String TEXT_223 = "</em>' ";
  protected final String TEXT_224 = ".";
  protected final String TEXT_225 = NL + "\t * @see ";
  protected final String TEXT_226 = NL + "\t * @see #isSet";
  protected final String TEXT_227 = "()";
  protected final String TEXT_228 = NL + "\t * @see #unset";
  protected final String TEXT_229 = "()";
  protected final String TEXT_230 = NL + "\t * @see #set";
  protected final String TEXT_231 = "(";
  protected final String TEXT_232 = ")";
  protected final String TEXT_233 = NL + "\t * @see ";
  protected final String TEXT_234 = "#get";
  protected final String TEXT_235 = "()";
  protected final String TEXT_236 = NL + "\t * @see ";
  protected final String TEXT_237 = "#";
  protected final String TEXT_238 = NL + "\t * @model ";
  protected final String TEXT_239 = NL + "\t *        ";
  protected final String TEXT_240 = NL + "\t * @model";
  protected final String TEXT_241 = NL + "\t * @generated" + NL + "\t */";
  protected final String TEXT_242 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */";
  protected final String TEXT_243 = NL + "\t";
  protected final String TEXT_244 = " ";
  protected final String TEXT_245 = "();" + NL;
  protected final String TEXT_246 = NL + "\t@SuppressWarnings(\"unchecked\")";
  protected final String TEXT_247 = NL + "\tpublic ";
  protected final String TEXT_248 = " ";
  protected final String TEXT_249 = "()" + NL + "\t{";
  protected final String TEXT_250 = NL + "\t\treturn ";
  protected final String TEXT_251 = "(";
  protected final String TEXT_252 = "(";
  protected final String TEXT_253 = ")eGet(";
  protected final String TEXT_254 = ", true)";
  protected final String TEXT_255 = ").";
  protected final String TEXT_256 = "()";
  protected final String TEXT_257 = ";";
  protected final String TEXT_258 = NL + "\t\t";
  protected final String TEXT_259 = " ";
  protected final String TEXT_260 = " = (";
  protected final String TEXT_261 = ")eVirtualGet(";
  protected final String TEXT_262 = ");";
  protected final String TEXT_263 = NL + "\t\tif (";
  protected final String TEXT_264 = " == null)" + NL + "\t\t{";
  protected final String TEXT_265 = NL + "\t\t\teVirtualSet(";
  protected final String TEXT_266 = ", ";
  protected final String TEXT_267 = " = new ";
  protected final String TEXT_268 = ");";
  protected final String TEXT_269 = NL + "\t\t\t";
  protected final String TEXT_270 = " = new ";
  protected final String TEXT_271 = ";";
  protected final String TEXT_272 = NL + "\t\t}" + NL + "\t\treturn ";
  protected final String TEXT_273 = ";";
  protected final String TEXT_274 = NL + "\t\tif (eContainerFeatureID != ";
  protected final String TEXT_275 = ") return null;" + NL + "\t\treturn (";
  protected final String TEXT_276 = ")eContainer();";
  protected final String TEXT_277 = NL + "\t\t";
  protected final String TEXT_278 = " ";
  protected final String TEXT_279 = " = (";
  protected final String TEXT_280 = ")eVirtualGet(";
  protected final String TEXT_281 = ", ";
  protected final String TEXT_282 = ");";
  protected final String TEXT_283 = NL + "\t\tif (";
  protected final String TEXT_284 = " != null && ";
  protected final String TEXT_285 = ".eIsProxy())" + NL + "\t\t{" + NL + "\t\t\t";
  protected final String TEXT_286 = " old";
  protected final String TEXT_287 = " = (";
  protected final String TEXT_288 = ")";
  protected final String TEXT_289 = ";" + NL + "\t\t\t";
  protected final String TEXT_290 = " = ";
  protected final String TEXT_291 = "eResolveProxy(old";
  protected final String TEXT_292 = ");" + NL + "\t\t\tif (";
  protected final String TEXT_293 = " != old";
  protected final String TEXT_294 = ")" + NL + "\t\t\t{";
  protected final String TEXT_295 = NL + "\t\t\t\t";
  protected final String TEXT_296 = " new";
  protected final String TEXT_297 = " = (";
  protected final String TEXT_298 = ")";
  protected final String TEXT_299 = ";";
  protected final String TEXT_300 = NL + "\t\t\t\t";
  protected final String TEXT_301 = " msgs = old";
  protected final String TEXT_302 = ".eInverseRemove(this, EOPPOSITE_FEATURE_BASE - ";
  protected final String TEXT_303 = ", null, null);";
  protected final String TEXT_304 = NL + "\t\t\t\t";
  protected final String TEXT_305 = " msgs =  old";
  protected final String TEXT_306 = ".eInverseRemove(this, ";
  protected final String TEXT_307 = ", ";
  protected final String TEXT_308 = ".class, null);";
  protected final String TEXT_309 = NL + "\t\t\t\tif (new";
  protected final String TEXT_310 = ".eInternalContainer() == null)" + NL + "\t\t\t\t{";
  protected final String TEXT_311 = NL + "\t\t\t\t\tmsgs = new";
  protected final String TEXT_312 = ".eInverseAdd(this, EOPPOSITE_FEATURE_BASE - ";
  protected final String TEXT_313 = ", null, msgs);";
  protected final String TEXT_314 = NL + "\t\t\t\t\tmsgs =  new";
  protected final String TEXT_315 = ".eInverseAdd(this, ";
  protected final String TEXT_316 = ", ";
  protected final String TEXT_317 = ".class, msgs);";
  protected final String TEXT_318 = NL + "\t\t\t\t}" + NL + "\t\t\t\tif (msgs != null) msgs.dispatch();";
  protected final String TEXT_319 = NL + "\t\t\t\teVirtualSet(";
  protected final String TEXT_320 = ", ";
  protected final String TEXT_321 = ");";
  protected final String TEXT_322 = NL + "\t\t\t\tif (eNotificationRequired())" + NL + "\t\t\t\t\teNotify(new ";
  protected final String TEXT_323 = "(this, ";
  protected final String TEXT_324 = ".RESOLVE, ";
  protected final String TEXT_325 = ", old";
  protected final String TEXT_326 = ", ";
  protected final String TEXT_327 = "));";
  protected final String TEXT_328 = NL + "\t\t\t}" + NL + "\t\t}";
  protected final String TEXT_329 = NL + "\t\treturn (";
  protected final String TEXT_330 = ")eVirtualGet(";
  protected final String TEXT_331 = ", ";
  protected final String TEXT_332 = ");";
  protected final String TEXT_333 = NL + "\t\treturn (";
  protected final String TEXT_334 = " & ";
  protected final String TEXT_335 = "_EFLAG) != 0;";
  protected final String TEXT_336 = NL + "\t\treturn ";
  protected final String TEXT_337 = ";";
  protected final String TEXT_338 = NL + "\t\t";
  protected final String TEXT_339 = " ";
  protected final String TEXT_340 = " = basicGet";
  protected final String TEXT_341 = "();" + NL + "\t\treturn ";
  protected final String TEXT_342 = " != null && ";
  protected final String TEXT_343 = ".eIsProxy() ? ";
  protected final String TEXT_344 = "eResolveProxy((";
  protected final String TEXT_345 = ")";
  protected final String TEXT_346 = ") : ";
  protected final String TEXT_347 = ";";
  protected final String TEXT_348 = NL + "\t\treturn new ";
  protected final String TEXT_349 = "((";
  protected final String TEXT_350 = ".Internal)((";
  protected final String TEXT_351 = ".Internal.Wrapper)get";
  protected final String TEXT_352 = "()).featureMap().";
  protected final String TEXT_353 = "list(";
  protected final String TEXT_354 = "));";
  protected final String TEXT_355 = NL + "\t\treturn (";
  protected final String TEXT_356 = ")get";
  protected final String TEXT_357 = "().";
  protected final String TEXT_358 = "list(";
  protected final String TEXT_359 = ");";
  protected final String TEXT_360 = NL + "\t\treturn ((";
  protected final String TEXT_361 = ".Internal.Wrapper)get";
  protected final String TEXT_362 = "()).featureMap().list(";
  protected final String TEXT_363 = ");";
  protected final String TEXT_364 = NL + "\t\treturn get";
  protected final String TEXT_365 = "().list(";
  protected final String TEXT_366 = ");";
  protected final String TEXT_367 = NL + "\t\treturn ";
  protected final String TEXT_368 = "(";
  protected final String TEXT_369 = "(";
  protected final String TEXT_370 = ")";
  protected final String TEXT_371 = "((";
  protected final String TEXT_372 = ".Internal.Wrapper)get";
  protected final String TEXT_373 = "()).featureMap().get(";
  protected final String TEXT_374 = ", true)";
  protected final String TEXT_375 = ").";
  protected final String TEXT_376 = "()";
  protected final String TEXT_377 = ";";
  protected final String TEXT_378 = NL + "\t\treturn ";
  protected final String TEXT_379 = "(";
  protected final String TEXT_380 = "(";
  protected final String TEXT_381 = ")";
  protected final String TEXT_382 = "get";
  protected final String TEXT_383 = "().get(";
  protected final String TEXT_384 = ", true)";
  protected final String TEXT_385 = ").";
  protected final String TEXT_386 = "()";
  protected final String TEXT_387 = ";";
  protected final String TEXT_388 = NL + "\t\t// TODO: implement this method to return the '";
  protected final String TEXT_389 = "' ";
  protected final String TEXT_390 = NL + "\t\t// Ensure that you remove @generated or mark it @generated NOT";
  protected final String TEXT_391 = NL + "\t\t// The list is expected to implement org.eclipse.emf.ecore.util.InternalEList and org.eclipse.emf.ecore.EStructuralFeature.Setting" + NL + "\t\t// so it's likely that an appropriate subclass of org.eclipse.emf.ecore.util.";
  protected final String TEXT_392 = "EcoreEMap";
  protected final String TEXT_393 = "BasicFeatureMap";
  protected final String TEXT_394 = "EcoreEList";
  protected final String TEXT_395 = " should be used.";
  protected final String TEXT_396 = NL + "\t\tthrow new UnsupportedOperationException();";
  protected final String TEXT_397 = NL + "\t}" + NL;
  protected final String TEXT_398 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */";
  protected final String TEXT_399 = NL + "\tpublic ";
  protected final String TEXT_400 = " basicGet";
  protected final String TEXT_401 = "()" + NL + "\t{";
  protected final String TEXT_402 = NL + "\t\tif (eContainerFeatureID != ";
  protected final String TEXT_403 = ") return null;" + NL + "\t\treturn (";
  protected final String TEXT_404 = ")eInternalContainer();";
  protected final String TEXT_405 = NL + "\t\treturn (";
  protected final String TEXT_406 = ")eVirtualGet(";
  protected final String TEXT_407 = ");";
  protected final String TEXT_408 = NL + "\t\treturn ";
  protected final String TEXT_409 = ";";
  protected final String TEXT_410 = NL + "\t\treturn (";
  protected final String TEXT_411 = ")((";
  protected final String TEXT_412 = ".Internal.Wrapper)get";
  protected final String TEXT_413 = "()).featureMap().get(";
  protected final String TEXT_414 = ", false);";
  protected final String TEXT_415 = NL + "\t\treturn (";
  protected final String TEXT_416 = ")get";
  protected final String TEXT_417 = "().get(";
  protected final String TEXT_418 = ", false);";
  protected final String TEXT_419 = NL + "\t\t// TODO: implement this method to return the '";
  protected final String TEXT_420 = "' ";
  protected final String TEXT_421 = NL + "\t\t// -> do not perform proxy resolution" + NL + "\t\t// Ensure that you remove @generated or mark it @generated NOT" + NL + "\t\tthrow new UnsupportedOperationException();";
  protected final String TEXT_422 = NL + "\t}" + NL;
  protected final String TEXT_423 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */";
  protected final String TEXT_424 = NL + "\tpublic ";
  protected final String TEXT_425 = " basicSet";
  protected final String TEXT_426 = "(";
  protected final String TEXT_427 = " new";
  protected final String TEXT_428 = ", ";
  protected final String TEXT_429 = " msgs)" + NL + "\t{";
  protected final String TEXT_430 = NL + "\t\tmsgs = eBasicSetContainer((";
  protected final String TEXT_431 = ")new";
  protected final String TEXT_432 = ", ";
  protected final String TEXT_433 = ", msgs);";
  protected final String TEXT_434 = NL + "\t\treturn msgs;";
  protected final String TEXT_435 = NL + "\t\tObject old";
  protected final String TEXT_436 = " = eVirtualSet(";
  protected final String TEXT_437 = ", new";
  protected final String TEXT_438 = ");";
  protected final String TEXT_439 = NL + "\t\t";
  protected final String TEXT_440 = " old";
  protected final String TEXT_441 = " = ";
  protected final String TEXT_442 = ";" + NL + "\t\t";
  protected final String TEXT_443 = " = new";
  protected final String TEXT_444 = ";";
  protected final String TEXT_445 = NL + "\t\tboolean isSetChange = old";
  protected final String TEXT_446 = " == EVIRTUAL_NO_VALUE;";
  protected final String TEXT_447 = NL + "\t\tboolean old";
  protected final String TEXT_448 = "ESet = (";
  protected final String TEXT_449 = " & ";
  protected final String TEXT_450 = "_ESETFLAG) != 0;" + NL + "\t\t";
  protected final String TEXT_451 = " |= ";
  protected final String TEXT_452 = "_ESETFLAG;";
  protected final String TEXT_453 = NL + "\t\tboolean old";
  protected final String TEXT_454 = "ESet = ";
  protected final String TEXT_455 = "ESet;" + NL + "\t\t";
  protected final String TEXT_456 = "ESet = true;";
  protected final String TEXT_457 = NL + "\t\tif (eNotificationRequired())" + NL + "\t\t{";
  protected final String TEXT_458 = NL + "\t\t\t";
  protected final String TEXT_459 = " notification = new ";
  protected final String TEXT_460 = "(this, ";
  protected final String TEXT_461 = ".SET, ";
  protected final String TEXT_462 = ", ";
  protected final String TEXT_463 = "isSetChange ? null : old";
  protected final String TEXT_464 = "old";
  protected final String TEXT_465 = ", new";
  protected final String TEXT_466 = ", ";
  protected final String TEXT_467 = "isSetChange";
  protected final String TEXT_468 = "!old";
  protected final String TEXT_469 = "ESet";
  protected final String TEXT_470 = ");";
  protected final String TEXT_471 = NL + "\t\t\t";
  protected final String TEXT_472 = " notification = new ";
  protected final String TEXT_473 = "(this, ";
  protected final String TEXT_474 = ".SET, ";
  protected final String TEXT_475 = ", ";
  protected final String TEXT_476 = "old";
  protected final String TEXT_477 = " == EVIRTUAL_NO_VALUE ? null : old";
  protected final String TEXT_478 = "old";
  protected final String TEXT_479 = ", new";
  protected final String TEXT_480 = ");";
  protected final String TEXT_481 = NL + "\t\t\tif (msgs == null) msgs = notification; else msgs.add(notification);" + NL + "\t\t}";
  protected final String TEXT_482 = NL + "\t\treturn msgs;";
  protected final String TEXT_483 = NL + "\t\treturn ((";
  protected final String TEXT_484 = ".Internal)((";
  protected final String TEXT_485 = ".Internal.Wrapper)get";
  protected final String TEXT_486 = "()).featureMap()).basicAdd(";
  protected final String TEXT_487 = ", new";
  protected final String TEXT_488 = ", msgs);";
  protected final String TEXT_489 = NL + "\t\treturn ((";
  protected final String TEXT_490 = ".Internal)get";
  protected final String TEXT_491 = "()).basicAdd(";
  protected final String TEXT_492 = ", new";
  protected final String TEXT_493 = ", msgs);";
  protected final String TEXT_494 = NL + "\t\t// TODO: implement this method to set the contained '";
  protected final String TEXT_495 = "' ";
  protected final String TEXT_496 = NL + "\t\t// -> this method is automatically invoked to keep the containment relationship in synch" + NL + "\t\t// -> do not modify other features" + NL + "\t\t// -> return msgs, after adding any generated Notification to it (if it is null, a NotificationChain object must be created first)" + NL + "\t\t// Ensure that you remove @generated or mark it @generated NOT" + NL + "\t\tthrow new UnsupportedOperationException();";
  protected final String TEXT_497 = NL + "\t}" + NL;
  protected final String TEXT_498 = NL + "\t/**" + NL + "\t * Sets the value of the '{@link ";
  protected final String TEXT_499 = "#";
  protected final String TEXT_500 = " <em>";
  protected final String TEXT_501 = "</em>}' ";
  protected final String TEXT_502 = ".";
  protected final String TEXT_503 = NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @param value the new value of the '<em>";
  protected final String TEXT_504 = "</em>' ";
  protected final String TEXT_505 = ".";
  protected final String TEXT_506 = NL + "\t * @see ";
  protected final String TEXT_507 = NL + "\t * @see #isSet";
  protected final String TEXT_508 = "()";
  protected final String TEXT_509 = NL + "\t * @see #unset";
  protected final String TEXT_510 = "()";
  protected final String TEXT_511 = NL + "\t * @see #";
  protected final String TEXT_512 = "()" + NL + "\t * @generated" + NL + "\t */";
  protected final String TEXT_513 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */";
  protected final String TEXT_514 = NL + "\tvoid set";
  protected final String TEXT_515 = "(";
  protected final String TEXT_516 = " value);" + NL;
  protected final String TEXT_517 = NL + "\tpublic void set";
  protected final String TEXT_518 = "(";
  protected final String TEXT_519 = " new";
  protected final String TEXT_520 = ")" + NL + "\t{";
  protected final String TEXT_521 = NL + "\t\teSet(";
  protected final String TEXT_522 = ", ";
  protected final String TEXT_523 = "new ";
  protected final String TEXT_524 = "(";
  protected final String TEXT_525 = "new";
  protected final String TEXT_526 = ")";
  protected final String TEXT_527 = ");";
  protected final String TEXT_528 = NL + "\t\tif (new";
  protected final String TEXT_529 = " != eInternalContainer() || (eContainerFeatureID != ";
  protected final String TEXT_530 = " && new";
  protected final String TEXT_531 = " != null))" + NL + "\t\t{" + NL + "\t\t\tif (";
  protected final String TEXT_532 = ".isAncestor(this, ";
  protected final String TEXT_533 = "new";
  protected final String TEXT_534 = "))" + NL + "\t\t\t\tthrow new ";
  protected final String TEXT_535 = "(\"Recursive containment not allowed for \" + toString());";
  protected final String TEXT_536 = NL + "\t\t\t";
  protected final String TEXT_537 = " msgs = null;" + NL + "\t\t\tif (eInternalContainer() != null)" + NL + "\t\t\t\tmsgs = eBasicRemoveFromContainer(msgs);" + NL + "\t\t\tif (new";
  protected final String TEXT_538 = " != null)" + NL + "\t\t\t\tmsgs = ((";
  protected final String TEXT_539 = ")new";
  protected final String TEXT_540 = ").eInverseAdd(this, ";
  protected final String TEXT_541 = ", ";
  protected final String TEXT_542 = ".class, msgs);" + NL + "\t\t\tmsgs = basicSet";
  protected final String TEXT_543 = "(";
  protected final String TEXT_544 = "new";
  protected final String TEXT_545 = ", msgs);" + NL + "\t\t\tif (msgs != null) msgs.dispatch();" + NL + "\t\t}";
  protected final String TEXT_546 = NL + "\t\telse if (eNotificationRequired())" + NL + "\t\t\teNotify(new ";
  protected final String TEXT_547 = "(this, ";
  protected final String TEXT_548 = ".SET, ";
  protected final String TEXT_549 = ", new";
  protected final String TEXT_550 = ", new";
  protected final String TEXT_551 = "));";
  protected final String TEXT_552 = NL + "\t\t";
  protected final String TEXT_553 = " ";
  protected final String TEXT_554 = " = (";
  protected final String TEXT_555 = ")eVirtualGet(";
  protected final String TEXT_556 = ");";
  protected final String TEXT_557 = NL + "\t\tif (new";
  protected final String TEXT_558 = " != ";
  protected final String TEXT_559 = ")" + NL + "\t\t{" + NL + "\t\t\t";
  protected final String TEXT_560 = " msgs = null;" + NL + "\t\t\tif (";
  protected final String TEXT_561 = " != null)";
  protected final String TEXT_562 = NL + "\t\t\t\tmsgs = ((";
  protected final String TEXT_563 = ")";
  protected final String TEXT_564 = ").eInverseRemove(this, EOPPOSITE_FEATURE_BASE - ";
  protected final String TEXT_565 = ", null, msgs);" + NL + "\t\t\tif (new";
  protected final String TEXT_566 = " != null)" + NL + "\t\t\t\tmsgs = ((";
  protected final String TEXT_567 = ")new";
  protected final String TEXT_568 = ").eInverseAdd(this, EOPPOSITE_FEATURE_BASE - ";
  protected final String TEXT_569 = ", null, msgs);";
  protected final String TEXT_570 = NL + "\t\t\t\tmsgs = ((";
  protected final String TEXT_571 = ")";
  protected final String TEXT_572 = ").eInverseRemove(this, ";
  protected final String TEXT_573 = ", ";
  protected final String TEXT_574 = ".class, msgs);" + NL + "\t\t\tif (new";
  protected final String TEXT_575 = " != null)" + NL + "\t\t\t\tmsgs = ((";
  protected final String TEXT_576 = ")new";
  protected final String TEXT_577 = ").eInverseAdd(this, ";
  protected final String TEXT_578 = ", ";
  protected final String TEXT_579 = ".class, msgs);";
  protected final String TEXT_580 = NL + "\t\t\tmsgs = basicSet";
  protected final String TEXT_581 = "(";
  protected final String TEXT_582 = "new";
  protected final String TEXT_583 = ", msgs);" + NL + "\t\t\tif (msgs != null) msgs.dispatch();" + NL + "\t\t}";
  protected final String TEXT_584 = NL + "\t\telse" + NL + "\t\t{";
  protected final String TEXT_585 = NL + "\t\t\tboolean old";
  protected final String TEXT_586 = "ESet = eVirtualIsSet(";
  protected final String TEXT_587 = ");";
  protected final String TEXT_588 = NL + "\t\t\tboolean old";
  protected final String TEXT_589 = "ESet = (";
  protected final String TEXT_590 = " & ";
  protected final String TEXT_591 = "_ESETFLAG) != 0;";
  protected final String TEXT_592 = NL + "\t\t\t";
  protected final String TEXT_593 = " |= ";
  protected final String TEXT_594 = "_ESETFLAG;";
  protected final String TEXT_595 = NL + "\t\t\tboolean old";
  protected final String TEXT_596 = "ESet = ";
  protected final String TEXT_597 = "ESet;";
  protected final String TEXT_598 = NL + "\t\t\t";
  protected final String TEXT_599 = "ESet = true;";
  protected final String TEXT_600 = NL + "\t\t\tif (eNotificationRequired())" + NL + "\t\t\t\teNotify(new ";
  protected final String TEXT_601 = "(this, ";
  protected final String TEXT_602 = ".SET, ";
  protected final String TEXT_603 = ", new";
  protected final String TEXT_604 = ", new";
  protected final String TEXT_605 = ", !old";
  protected final String TEXT_606 = "ESet));";
  protected final String TEXT_607 = NL + "\t\t}";
  protected final String TEXT_608 = NL + "\t\telse if (eNotificationRequired())" + NL + "\t\t\teNotify(new ";
  protected final String TEXT_609 = "(this, ";
  protected final String TEXT_610 = ".SET, ";
  protected final String TEXT_611 = ", new";
  protected final String TEXT_612 = ", new";
  protected final String TEXT_613 = "));";
  protected final String TEXT_614 = NL + "\t\t";
  protected final String TEXT_615 = " old";
  protected final String TEXT_616 = " = (";
  protected final String TEXT_617 = " & ";
  protected final String TEXT_618 = "_EFLAG) != 0;";
  protected final String TEXT_619 = NL + "\t\tif (new";
  protected final String TEXT_620 = ") ";
  protected final String TEXT_621 = " |= ";
  protected final String TEXT_622 = "_EFLAG; else ";
  protected final String TEXT_623 = " &= ~";
  protected final String TEXT_624 = "_EFLAG;";
  protected final String TEXT_625 = NL + "\t\t";
  protected final String TEXT_626 = " old";
  protected final String TEXT_627 = " = ";
  protected final String TEXT_628 = ";";
  protected final String TEXT_629 = NL + "\t\t";
  protected final String TEXT_630 = " ";
  protected final String TEXT_631 = " = new";
  protected final String TEXT_632 = " == null ? ";
  protected final String TEXT_633 = " : new";
  protected final String TEXT_634 = ";";
  protected final String TEXT_635 = NL + "\t\t";
  protected final String TEXT_636 = " = new";
  protected final String TEXT_637 = " == null ? ";
  protected final String TEXT_638 = " : new";
  protected final String TEXT_639 = ";";
  protected final String TEXT_640 = NL + "\t\t";
  protected final String TEXT_641 = " ";
  protected final String TEXT_642 = " = ";
  protected final String TEXT_643 = "new";
  protected final String TEXT_644 = ";";
  protected final String TEXT_645 = NL + "\t\t";
  protected final String TEXT_646 = " = ";
  protected final String TEXT_647 = "new";
  protected final String TEXT_648 = ";";
  protected final String TEXT_649 = NL + "\t\tObject old";
  protected final String TEXT_650 = " = eVirtualSet(";
  protected final String TEXT_651 = ", ";
  protected final String TEXT_652 = ");";
  protected final String TEXT_653 = NL + "\t\tboolean isSetChange = old";
  protected final String TEXT_654 = " == EVIRTUAL_NO_VALUE;";
  protected final String TEXT_655 = NL + "\t\tboolean old";
  protected final String TEXT_656 = "ESet = (";
  protected final String TEXT_657 = " & ";
  protected final String TEXT_658 = "_ESETFLAG) != 0;";
  protected final String TEXT_659 = NL + "\t\t";
  protected final String TEXT_660 = " |= ";
  protected final String TEXT_661 = "_ESETFLAG;";
  protected final String TEXT_662 = NL + "\t\tboolean old";
  protected final String TEXT_663 = "ESet = ";
  protected final String TEXT_664 = "ESet;";
  protected final String TEXT_665 = NL + "\t\t";
  protected final String TEXT_666 = "ESet = true;";
  protected final String TEXT_667 = NL + "\t\tif (eNotificationRequired())" + NL + "\t\t\teNotify(new ";
  protected final String TEXT_668 = "(this, ";
  protected final String TEXT_669 = ".SET, ";
  protected final String TEXT_670 = ", ";
  protected final String TEXT_671 = "isSetChange ? ";
  protected final String TEXT_672 = " : old";
  protected final String TEXT_673 = "old";
  protected final String TEXT_674 = ", ";
  protected final String TEXT_675 = "new";
  protected final String TEXT_676 = ", ";
  protected final String TEXT_677 = "isSetChange";
  protected final String TEXT_678 = "!old";
  protected final String TEXT_679 = "ESet";
  protected final String TEXT_680 = "));";
  protected final String TEXT_681 = NL + "\t\tif (eNotificationRequired())" + NL + "\t\t\teNotify(new ";
  protected final String TEXT_682 = "(this, ";
  protected final String TEXT_683 = ".SET, ";
  protected final String TEXT_684 = ", ";
  protected final String TEXT_685 = "old";
  protected final String TEXT_686 = " == EVIRTUAL_NO_VALUE ? ";
  protected final String TEXT_687 = " : old";
  protected final String TEXT_688 = "old";
  protected final String TEXT_689 = ", ";
  protected final String TEXT_690 = "new";
  protected final String TEXT_691 = "));";
  protected final String TEXT_692 = NL + "\t\t((";
  protected final String TEXT_693 = ".Internal)((";
  protected final String TEXT_694 = ".Internal.Wrapper)get";
  protected final String TEXT_695 = "()).featureMap()).set(";
  protected final String TEXT_696 = ", ";
  protected final String TEXT_697 = "new ";
  protected final String TEXT_698 = "(";
  protected final String TEXT_699 = "new";
  protected final String TEXT_700 = ")";
  protected final String TEXT_701 = ");";
  protected final String TEXT_702 = NL + "\t\t((";
  protected final String TEXT_703 = ".Internal)get";
  protected final String TEXT_704 = "()).set(";
  protected final String TEXT_705 = ", ";
  protected final String TEXT_706 = "new ";
  protected final String TEXT_707 = "(";
  protected final String TEXT_708 = "new";
  protected final String TEXT_709 = ")";
  protected final String TEXT_710 = ");";
  protected final String TEXT_711 = NL + "\t\t// TODO: implement this method to set the '";
  protected final String TEXT_712 = "' ";
  protected final String TEXT_713 = NL + "\t\t// Ensure that you remove @generated or mark it @generated NOT" + NL + "\t\tthrow new UnsupportedOperationException();";
  protected final String TEXT_714 = NL + "\t}" + NL;
  protected final String TEXT_715 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */";
  protected final String TEXT_716 = NL + "\tpublic ";
  protected final String TEXT_717 = " basicUnset";
  protected final String TEXT_718 = "(";
  protected final String TEXT_719 = " msgs)" + NL + "\t{";
  protected final String TEXT_720 = NL + "\t\tObject old";
  protected final String TEXT_721 = " = eVirtualUnset(";
  protected final String TEXT_722 = ");";
  protected final String TEXT_723 = NL + "\t\t";
  protected final String TEXT_724 = " old";
  protected final String TEXT_725 = " = ";
  protected final String TEXT_726 = ";" + NL + "\t\t";
  protected final String TEXT_727 = " = null;";
  protected final String TEXT_728 = NL + "\t\tboolean isSetChange = old";
  protected final String TEXT_729 = " != EVIRTUAL_NO_VALUE;";
  protected final String TEXT_730 = NL + "\t\tboolean old";
  protected final String TEXT_731 = "ESet = (";
  protected final String TEXT_732 = " & ";
  protected final String TEXT_733 = "_ESETFLAG) != 0;" + NL + "\t\t";
  protected final String TEXT_734 = " &= ~";
  protected final String TEXT_735 = "_ESETFLAG;";
  protected final String TEXT_736 = NL + "\t\tboolean old";
  protected final String TEXT_737 = "ESet = ";
  protected final String TEXT_738 = "ESet;" + NL + "\t\t";
  protected final String TEXT_739 = "ESet = false;";
  protected final String TEXT_740 = NL + "\t\tif (eNotificationRequired())" + NL + "\t\t{" + NL + "\t\t\t";
  protected final String TEXT_741 = " notification = new ";
  protected final String TEXT_742 = "(this, ";
  protected final String TEXT_743 = ".UNSET, ";
  protected final String TEXT_744 = ", ";
  protected final String TEXT_745 = "isSetChange ? old";
  protected final String TEXT_746 = " : null";
  protected final String TEXT_747 = "old";
  protected final String TEXT_748 = ", null, ";
  protected final String TEXT_749 = "isSetChange";
  protected final String TEXT_750 = "old";
  protected final String TEXT_751 = "ESet";
  protected final String TEXT_752 = ");" + NL + "\t\t\tif (msgs == null) msgs = notification; else msgs.add(notification);" + NL + "\t\t}" + NL + "\t\treturn msgs;";
  protected final String TEXT_753 = NL + "\t\t// TODO: implement this method to unset the contained '";
  protected final String TEXT_754 = "' ";
  protected final String TEXT_755 = NL + "\t\t// -> this method is automatically invoked to keep the containment relationship in synch" + NL + "\t\t// -> do not modify other features" + NL + "\t\t// -> return msgs, after adding any generated Notification to it (if it is null, a NotificationChain object must be created first)" + NL + "\t\t// Ensure that you remove @generated or mark it @generated NOT" + NL + "\t\tthrow new UnsupportedOperationException();";
  protected final String TEXT_756 = NL + "\t}" + NL;
  protected final String TEXT_757 = NL + "\t/**" + NL + "\t * Unsets the value of the '{@link ";
  protected final String TEXT_758 = "#";
  protected final String TEXT_759 = " <em>";
  protected final String TEXT_760 = "</em>}' ";
  protected final String TEXT_761 = ".";
  protected final String TEXT_762 = NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->";
  protected final String TEXT_763 = NL + "\t * @see #isSet";
  protected final String TEXT_764 = "()";
  protected final String TEXT_765 = NL + "\t * @see #";
  protected final String TEXT_766 = "()";
  protected final String TEXT_767 = NL + "\t * @see #set";
  protected final String TEXT_768 = "(";
  protected final String TEXT_769 = ")";
  protected final String TEXT_770 = NL + "\t * @generated" + NL + "\t */";
  protected final String TEXT_771 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */";
  protected final String TEXT_772 = NL + "\tvoid unset";
  protected final String TEXT_773 = "();" + NL;
  protected final String TEXT_774 = NL + "\tpublic void unset";
  protected final String TEXT_775 = "()" + NL + "\t{";
  protected final String TEXT_776 = NL + "\t\teUnset(";
  protected final String TEXT_777 = ");";
  protected final String TEXT_778 = NL + "\t\t";
  protected final String TEXT_779 = " ";
  protected final String TEXT_780 = " = (";
  protected final String TEXT_781 = ")eVirtualGet(";
  protected final String TEXT_782 = ");";
  protected final String TEXT_783 = NL + "\t\tif (";
  protected final String TEXT_784 = " != null) ((";
  protected final String TEXT_785 = ".Unsettable";
  protected final String TEXT_786 = ")";
  protected final String TEXT_787 = ").unset();";
  protected final String TEXT_788 = NL + "\t\t";
  protected final String TEXT_789 = " ";
  protected final String TEXT_790 = " = (";
  protected final String TEXT_791 = ")eVirtualGet(";
  protected final String TEXT_792 = ");";
  protected final String TEXT_793 = NL + "\t\tif (";
  protected final String TEXT_794 = " != null)" + NL + "\t\t{" + NL + "\t\t\t";
  protected final String TEXT_795 = " msgs = null;";
  protected final String TEXT_796 = NL + "\t\t\tmsgs = ((";
  protected final String TEXT_797 = ")";
  protected final String TEXT_798 = ").eInverseRemove(this, EOPPOSITE_FEATURE_BASE - ";
  protected final String TEXT_799 = ", null, msgs);";
  protected final String TEXT_800 = NL + "\t\t\tmsgs = ((";
  protected final String TEXT_801 = ")";
  protected final String TEXT_802 = ").eInverseRemove(this, ";
  protected final String TEXT_803 = ", ";
  protected final String TEXT_804 = ".class, msgs);";
  protected final String TEXT_805 = NL + "\t\t\tmsgs = basicUnset";
  protected final String TEXT_806 = "(msgs);" + NL + "\t\t\tif (msgs != null) msgs.dispatch();" + NL + "\t\t}" + NL + "\t\telse" + NL + "\t\t{";
  protected final String TEXT_807 = NL + "\t\t\tboolean old";
  protected final String TEXT_808 = "ESet = eVirtualIsSet(";
  protected final String TEXT_809 = ");";
  protected final String TEXT_810 = NL + "\t\t\tboolean old";
  protected final String TEXT_811 = "ESet = (";
  protected final String TEXT_812 = " & ";
  protected final String TEXT_813 = "_ESETFLAG) != 0;";
  protected final String TEXT_814 = NL + "\t\t\t";
  protected final String TEXT_815 = " &= ~";
  protected final String TEXT_816 = "_ESETFLAG;";
  protected final String TEXT_817 = NL + "\t\t\tboolean old";
  protected final String TEXT_818 = "ESet = ";
  protected final String TEXT_819 = "ESet;";
  protected final String TEXT_820 = NL + "\t\t\t";
  protected final String TEXT_821 = "ESet = false;";
  protected final String TEXT_822 = NL + "\t\t\tif (eNotificationRequired())" + NL + "\t\t\t\teNotify(new ";
  protected final String TEXT_823 = "(this, ";
  protected final String TEXT_824 = ".UNSET, ";
  protected final String TEXT_825 = ", null, null, old";
  protected final String TEXT_826 = "ESet));";
  protected final String TEXT_827 = NL + "\t\t}";
  protected final String TEXT_828 = NL + "\t\t";
  protected final String TEXT_829 = " old";
  protected final String TEXT_830 = " = (";
  protected final String TEXT_831 = " & ";
  protected final String TEXT_832 = "_EFLAG) != 0;";
  protected final String TEXT_833 = NL + "\t\tObject old";
  protected final String TEXT_834 = " = eVirtualUnset(";
  protected final String TEXT_835 = ");";
  protected final String TEXT_836 = NL + "\t\t";
  protected final String TEXT_837 = " old";
  protected final String TEXT_838 = " = ";
  protected final String TEXT_839 = ";";
  protected final String TEXT_840 = NL + "\t\tboolean isSetChange = old";
  protected final String TEXT_841 = " != EVIRTUAL_NO_VALUE;";
  protected final String TEXT_842 = NL + "\t\tboolean old";
  protected final String TEXT_843 = "ESet = (";
  protected final String TEXT_844 = " & ";
  protected final String TEXT_845 = "_ESETFLAG) != 0;";
  protected final String TEXT_846 = NL + "\t\tboolean old";
  protected final String TEXT_847 = "ESet = ";
  protected final String TEXT_848 = "ESet;";
  protected final String TEXT_849 = NL + "\t\t";
  protected final String TEXT_850 = " = null;";
  protected final String TEXT_851 = NL + "\t\t";
  protected final String TEXT_852 = " &= ~";
  protected final String TEXT_853 = "_ESETFLAG;";
  protected final String TEXT_854 = NL + "\t\t";
  protected final String TEXT_855 = "ESet = false;";
  protected final String TEXT_856 = NL + "\t\tif (eNotificationRequired())" + NL + "\t\t\teNotify(new ";
  protected final String TEXT_857 = "(this, ";
  protected final String TEXT_858 = ".UNSET, ";
  protected final String TEXT_859 = ", ";
  protected final String TEXT_860 = "isSetChange ? old";
  protected final String TEXT_861 = " : null";
  protected final String TEXT_862 = "old";
  protected final String TEXT_863 = ", null, ";
  protected final String TEXT_864 = "isSetChange";
  protected final String TEXT_865 = "old";
  protected final String TEXT_866 = "ESet";
  protected final String TEXT_867 = "));";
  protected final String TEXT_868 = NL + "\t\tif (";
  protected final String TEXT_869 = ") ";
  protected final String TEXT_870 = " |= ";
  protected final String TEXT_871 = "_EFLAG; else ";
  protected final String TEXT_872 = " &= ~";
  protected final String TEXT_873 = "_EFLAG;";
  protected final String TEXT_874 = NL + "\t\t";
  protected final String TEXT_875 = " = ";
  protected final String TEXT_876 = ";";
  protected final String TEXT_877 = NL + "\t\t";
  protected final String TEXT_878 = " &= ~";
  protected final String TEXT_879 = "_ESETFLAG;";
  protected final String TEXT_880 = NL + "\t\t";
  protected final String TEXT_881 = "ESet = false;";
  protected final String TEXT_882 = NL + "\t\tif (eNotificationRequired())" + NL + "\t\t\teNotify(new ";
  protected final String TEXT_883 = "(this, ";
  protected final String TEXT_884 = ".UNSET, ";
  protected final String TEXT_885 = ", ";
  protected final String TEXT_886 = "isSetChange ? old";
  protected final String TEXT_887 = " : ";
  protected final String TEXT_888 = "old";
  protected final String TEXT_889 = ", ";
  protected final String TEXT_890 = ", ";
  protected final String TEXT_891 = "isSetChange";
  protected final String TEXT_892 = "old";
  protected final String TEXT_893 = "ESet";
  protected final String TEXT_894 = "));";
  protected final String TEXT_895 = NL + "\t\t((";
  protected final String TEXT_896 = ".Internal)((";
  protected final String TEXT_897 = ".Internal.Wrapper)get";
  protected final String TEXT_898 = "()).featureMap()).clear(";
  protected final String TEXT_899 = ");";
  protected final String TEXT_900 = NL + "\t\t((";
  protected final String TEXT_901 = ".Internal)get";
  protected final String TEXT_902 = "()).clear(";
  protected final String TEXT_903 = ");";
  protected final String TEXT_904 = NL + "\t\t// TODO: implement this method to unset the '";
  protected final String TEXT_905 = "' ";
  protected final String TEXT_906 = NL + "\t\t// Ensure that you remove @generated or mark it @generated NOT" + NL + "\t\tthrow new UnsupportedOperationException();";
  protected final String TEXT_907 = NL + "\t}" + NL;
  protected final String TEXT_908 = NL + "\t/**" + NL + "\t * Returns whether the value of the '{@link ";
  protected final String TEXT_909 = "#";
  protected final String TEXT_910 = " <em>";
  protected final String TEXT_911 = "</em>}' ";
  protected final String TEXT_912 = " is set.";
  protected final String TEXT_913 = NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @return whether the value of the '<em>";
  protected final String TEXT_914 = "</em>' ";
  protected final String TEXT_915 = " is set.";
  protected final String TEXT_916 = NL + "\t * @see #unset";
  protected final String TEXT_917 = "()";
  protected final String TEXT_918 = NL + "\t * @see #";
  protected final String TEXT_919 = "()";
  protected final String TEXT_920 = NL + "\t * @see #set";
  protected final String TEXT_921 = "(";
  protected final String TEXT_922 = ")";
  protected final String TEXT_923 = NL + "\t * @generated" + NL + "\t */";
  protected final String TEXT_924 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */";
  protected final String TEXT_925 = NL + "\tboolean isSet";
  protected final String TEXT_926 = "();" + NL;
  protected final String TEXT_927 = NL + "\tpublic boolean isSet";
  protected final String TEXT_928 = "()" + NL + "\t{";
  protected final String TEXT_929 = NL + "\t\treturn eIsSet(";
  protected final String TEXT_930 = ");";
  protected final String TEXT_931 = NL + "\t\t";
  protected final String TEXT_932 = " ";
  protected final String TEXT_933 = " = (";
  protected final String TEXT_934 = ")eVirtualGet(";
  protected final String TEXT_935 = ");";
  protected final String TEXT_936 = NL + "\t\treturn ";
  protected final String TEXT_937 = " != null && ((";
  protected final String TEXT_938 = ".Unsettable";
  protected final String TEXT_939 = ")";
  protected final String TEXT_940 = ").isSet();";
  protected final String TEXT_941 = NL + "\t\treturn eVirtualIsSet(";
  protected final String TEXT_942 = ");";
  protected final String TEXT_943 = NL + "\t\treturn (";
  protected final String TEXT_944 = " & ";
  protected final String TEXT_945 = "_ESETFLAG) != 0;";
  protected final String TEXT_946 = NL + "\t\treturn ";
  protected final String TEXT_947 = "ESet;";
  protected final String TEXT_948 = NL + "\t\treturn !((";
  protected final String TEXT_949 = ".Internal)((";
  protected final String TEXT_950 = ".Internal.Wrapper)get";
  protected final String TEXT_951 = "()).featureMap()).isEmpty(";
  protected final String TEXT_952 = ");";
  protected final String TEXT_953 = NL + "\t\treturn !((";
  protected final String TEXT_954 = ".Internal)get";
  protected final String TEXT_955 = "()).isEmpty(";
  protected final String TEXT_956 = ");";
  protected final String TEXT_957 = NL + "\t\t// TODO: implement this method to return whether the '";
  protected final String TEXT_958 = "' ";
  protected final String TEXT_959 = " is set" + NL + "\t\t// Ensure that you remove @generated or mark it @generated NOT" + NL + "\t\tthrow new UnsupportedOperationException();";
  protected final String TEXT_960 = NL + "\t}" + NL;
  protected final String TEXT_961 = NL + "\t/**";
  protected final String TEXT_962 = NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->";
  protected final String TEXT_963 = NL + "\t * <!-- begin-model-doc -->";
  protected final String TEXT_964 = NL + "\t * ";
  protected final String TEXT_965 = NL + "\t * @param ";
  protected final String TEXT_966 = NL + "\t *   ";
  protected final String TEXT_967 = NL + "\t * @param ";
  protected final String TEXT_968 = " ";
  protected final String TEXT_969 = NL + "\t * <!-- end-model-doc -->";
  protected final String TEXT_970 = NL + "\t * @model ";
  protected final String TEXT_971 = NL + "\t *        ";
  protected final String TEXT_972 = NL + "\t * @model";
  protected final String TEXT_973 = NL + "\t * @generated" + NL + "\t */";
  protected final String TEXT_974 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */";
  protected final String TEXT_975 = NL + "\t";
  protected final String TEXT_976 = " ";
  protected final String TEXT_977 = "(";
  protected final String TEXT_978 = ")";
  protected final String TEXT_979 = ";" + NL;
  protected final String TEXT_980 = NL + "\tpublic ";
  protected final String TEXT_981 = " ";
  protected final String TEXT_982 = "(";
  protected final String TEXT_983 = ")";
  protected final String TEXT_984 = NL + "\t{";
  protected final String TEXT_985 = NL + "\t\t";
  protected final String TEXT_986 = NL + "\t\t// TODO: implement this method" + NL + "\t\t// -> specify the condition that violates the invariant" + NL + "\t\t// -> verify the details of the diagnostic, including severity and message" + NL + "\t\t// Ensure that you remove @generated or mark it @generated NOT" + NL + "\t\tif (false)" + NL + "\t\t{" + NL + "\t\t\tif (";
  protected final String TEXT_987 = " != null)" + NL + "\t\t\t{" + NL + "\t\t\t\t";
  protected final String TEXT_988 = ".add" + NL + "\t\t\t\t\t(new ";
  protected final String TEXT_989 = NL + "\t\t\t\t\t\t(";
  protected final String TEXT_990 = ".ERROR," + NL + "\t\t\t\t\t\t ";
  protected final String TEXT_991 = ".DIAGNOSTIC_SOURCE," + NL + "\t\t\t\t\t\t ";
  protected final String TEXT_992 = ".";
  protected final String TEXT_993 = "," + NL + "\t\t\t\t\t\t ";
  protected final String TEXT_994 = ".INSTANCE.getString(\"_UI_GenericInvariant_diagnostic\", new Object[] { \"";
  protected final String TEXT_995 = "\", ";
  protected final String TEXT_996 = ".getObjectLabel(this, ";
  protected final String TEXT_997 = ") }),";
  protected final String TEXT_998 = NL + "\t\t\t\t\t\t new Object [] { this }));" + NL + "\t\t\t}" + NL + "\t\t\treturn false;" + NL + "\t\t}" + NL + "\t\treturn true;";
  protected final String TEXT_999 = NL + "\t\t// TODO: implement this method" + NL + "\t\t// Ensure that you remove @generated or mark it @generated NOT" + NL + "\t\tthrow new UnsupportedOperationException();";
  protected final String TEXT_1000 = NL + "\t}" + NL;
  protected final String TEXT_1001 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */";
  protected final String TEXT_1002 = NL + "\t@SuppressWarnings(\"unchecked\")";
  protected final String TEXT_1003 = NL + "\t@Override";
  protected final String TEXT_1004 = NL + "\tpublic ";
  protected final String TEXT_1005 = " eInverseAdd(";
  protected final String TEXT_1006 = " otherEnd, int featureID, ";
  protected final String TEXT_1007 = " msgs)" + NL + "\t{" + NL + "\t\tswitch (featureID)" + NL + "\t\t{";
  protected final String TEXT_1008 = NL + "\t\t\tcase ";
  protected final String TEXT_1009 = ":";
  protected final String TEXT_1010 = NL + "\t\t\t\treturn ((";
  protected final String TEXT_1011 = "(";
  protected final String TEXT_1012 = ".InternalMapView";
  protected final String TEXT_1013 = ")";
  protected final String TEXT_1014 = "()).eMap()).basicAdd(otherEnd, msgs);";
  protected final String TEXT_1015 = NL + "\t\t\t\treturn (";
  protected final String TEXT_1016 = "()).basicAdd(otherEnd, msgs);";
  protected final String TEXT_1017 = NL + "\t\t\t\tif (eInternalContainer() != null)" + NL + "\t\t\t\t\tmsgs = eBasicRemoveFromContainer(msgs);";
  protected final String TEXT_1018 = NL + "\t\t\t\treturn basicSet";
  protected final String TEXT_1019 = "((";
  protected final String TEXT_1020 = ")otherEnd, msgs);";
  protected final String TEXT_1021 = NL + "\t\t\t\treturn eBasicSetContainer(otherEnd, ";
  protected final String TEXT_1022 = ", msgs);";
  protected final String TEXT_1023 = NL + "\t\t\t\t";
  protected final String TEXT_1024 = " ";
  protected final String TEXT_1025 = " = (";
  protected final String TEXT_1026 = ")eVirtualGet(";
  protected final String TEXT_1027 = ");";
  protected final String TEXT_1028 = NL + "\t\t\t\tif (";
  protected final String TEXT_1029 = " != null)";
  protected final String TEXT_1030 = NL + "\t\t\t\t\tmsgs = ((";
  protected final String TEXT_1031 = ")";
  protected final String TEXT_1032 = ").eInverseRemove(this, EOPPOSITE_FEATURE_BASE - ";
  protected final String TEXT_1033 = ", null, msgs);";
  protected final String TEXT_1034 = NL + "\t\t\t\t\tmsgs = ((";
  protected final String TEXT_1035 = ")";
  protected final String TEXT_1036 = ").eInverseRemove(this, ";
  protected final String TEXT_1037 = ", ";
  protected final String TEXT_1038 = ".class, msgs);";
  protected final String TEXT_1039 = NL + "\t\t\t\treturn basicSet";
  protected final String TEXT_1040 = "((";
  protected final String TEXT_1041 = ")otherEnd, msgs);";
  protected final String TEXT_1042 = NL + "\t\t}";
  protected final String TEXT_1043 = NL + "\t\treturn super.eInverseAdd(otherEnd, featureID, msgs);";
  protected final String TEXT_1044 = NL + "\t\treturn eDynamicInverseAdd(otherEnd, featureID, msgs);";
  protected final String TEXT_1045 = NL + "\t}" + NL;
  protected final String TEXT_1046 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */";
  protected final String TEXT_1047 = NL + "\t@Override";
  protected final String TEXT_1048 = NL + "\tpublic ";
  protected final String TEXT_1049 = " eInverseRemove(";
  protected final String TEXT_1050 = " otherEnd, int featureID, ";
  protected final String TEXT_1051 = " msgs)" + NL + "\t{" + NL + "\t\tswitch (featureID)" + NL + "\t\t{";
  protected final String TEXT_1052 = NL + "\t\t\tcase ";
  protected final String TEXT_1053 = ":";
  protected final String TEXT_1054 = NL + "\t\t\t\treturn ((";
  protected final String TEXT_1055 = ")((";
  protected final String TEXT_1056 = ".InternalMapView";
  protected final String TEXT_1057 = ")";
  protected final String TEXT_1058 = "()).eMap()).basicRemove(otherEnd, msgs);";
  protected final String TEXT_1059 = NL + "\t\t\t\treturn ((";
  protected final String TEXT_1060 = ")((";
  protected final String TEXT_1061 = ".Internal.Wrapper)";
  protected final String TEXT_1062 = "()).featureMap()).basicRemove(otherEnd, msgs);";
  protected final String TEXT_1063 = NL + "\t\t\t\treturn ((";
  protected final String TEXT_1064 = ")";
  protected final String TEXT_1065 = "()).basicRemove(otherEnd, msgs);";
  protected final String TEXT_1066 = NL + "\t\t\t\treturn eBasicSetContainer(null, ";
  protected final String TEXT_1067 = ", msgs);";
  protected final String TEXT_1068 = NL + "\t\t\t\treturn basicUnset";
  protected final String TEXT_1069 = "(msgs);";
  protected final String TEXT_1070 = NL + "\t\t\t\treturn basicSet";
  protected final String TEXT_1071 = "(null, msgs);";
  protected final String TEXT_1072 = NL + "\t\t}";
  protected final String TEXT_1073 = NL + "\t\treturn super.eInverseRemove(otherEnd, featureID, msgs);";
  protected final String TEXT_1074 = NL + "\t\treturn eDynamicInverseRemove(otherEnd, featureID, msgs);";
  protected final String TEXT_1075 = NL + "\t}" + NL;
  protected final String TEXT_1076 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */";
  protected final String TEXT_1077 = NL + "\t@Override";
  protected final String TEXT_1078 = NL + "\tpublic ";
  protected final String TEXT_1079 = " eBasicRemoveFromContainerFeature(";
  protected final String TEXT_1080 = " msgs)" + NL + "\t{" + NL + "\t\tswitch (eContainerFeatureID)" + NL + "\t\t{";
  protected final String TEXT_1081 = NL + "\t\t\tcase ";
  protected final String TEXT_1082 = ":" + NL + "\t\t\t\treturn eInternalContainer().eInverseRemove(this, ";
  protected final String TEXT_1083 = ", ";
  protected final String TEXT_1084 = ".class, msgs);";
  protected final String TEXT_1085 = NL + "\t\t}";
  protected final String TEXT_1086 = NL + "\t\treturn super.eBasicRemoveFromContainerFeature(msgs);";
  protected final String TEXT_1087 = NL + "\t\treturn eDynamicBasicRemoveFromContainer(msgs);";
  protected final String TEXT_1088 = NL + "\t}" + NL;
  protected final String TEXT_1089 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */";
  protected final String TEXT_1090 = NL + "\t@Override";
  protected final String TEXT_1091 = NL + "\tpublic Object eGet(int featureID, boolean resolve, boolean coreType)" + NL + "\t{" + NL + "\t\tswitch (featureID)" + NL + "\t\t{";
  protected final String TEXT_1092 = NL + "\t\t\tcase ";
  protected final String TEXT_1093 = ":";
  protected final String TEXT_1094 = NL + "\t\t\t\treturn ";
  protected final String TEXT_1095 = "() ? Boolean.TRUE : Boolean.FALSE;";
  protected final String TEXT_1096 = NL + "\t\t\t\treturn new ";
  protected final String TEXT_1097 = "(";
  protected final String TEXT_1098 = "());";
  protected final String TEXT_1099 = NL + "\t\t\t\tif (resolve) return ";
  protected final String TEXT_1100 = "();" + NL + "\t\t\t\treturn basicGet";
  protected final String TEXT_1101 = "();";
  protected final String TEXT_1102 = NL + "\t\t\t\tif (coreType) return ((";
  protected final String TEXT_1103 = ".InternalMapView";
  protected final String TEXT_1104 = ")";
  protected final String TEXT_1105 = "()).eMap();" + NL + "\t\t\t\telse return ";
  protected final String TEXT_1106 = "();";
  protected final String TEXT_1107 = NL + "\t\t\t\tif (coreType) return ";
  protected final String TEXT_1108 = "();" + NL + "\t\t\t\telse return ";
  protected final String TEXT_1109 = "().map();";
  protected final String TEXT_1110 = NL + "\t\t\t\tif (coreType) return ((";
  protected final String TEXT_1111 = ".Internal.Wrapper)";
  protected final String TEXT_1112 = "()).featureMap();" + NL + "\t\t\t\treturn ";
  protected final String TEXT_1113 = "();";
  protected final String TEXT_1114 = NL + "\t\t\t\tif (coreType) return ";
  protected final String TEXT_1115 = "();" + NL + "\t\t\t\treturn ((";
  protected final String TEXT_1116 = ".Internal)";
  protected final String TEXT_1117 = "()).getWrapper();";
  protected final String TEXT_1118 = NL + "\t\t\t\treturn ";
  protected final String TEXT_1119 = "();";
  protected final String TEXT_1120 = NL + "\t\t}";
  protected final String TEXT_1121 = NL + "\t\treturn super.eGet(featureID, resolve, coreType);";
  protected final String TEXT_1122 = NL + "\t\treturn eDynamicGet(featureID, resolve, coreType);";
  protected final String TEXT_1123 = NL + "\t}" + NL;
  protected final String TEXT_1124 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */";
  protected final String TEXT_1125 = NL + "\t@SuppressWarnings(\"unchecked\")";
  protected final String TEXT_1126 = NL + "\t@Override";
  protected final String TEXT_1127 = NL + "\tpublic void eSet(int featureID, Object newValue)" + NL + "\t{" + NL + "\t\tswitch (featureID)" + NL + "\t\t{";
  protected final String TEXT_1128 = NL + "\t\t\tcase ";
  protected final String TEXT_1129 = ":";
  protected final String TEXT_1130 = NL + "\t\t\t\t((";
  protected final String TEXT_1131 = ".Internal)((";
  protected final String TEXT_1132 = ".Internal.Wrapper)";
  protected final String TEXT_1133 = "()).featureMap()).set(newValue);";
  protected final String TEXT_1134 = NL + "\t\t\t\t((";
  protected final String TEXT_1135 = ".Internal)";
  protected final String TEXT_1136 = "()).set(newValue);";
  protected final String TEXT_1137 = NL + "\t\t\t\t((";
  protected final String TEXT_1138 = ".Setting)((";
  protected final String TEXT_1139 = ".InternalMapView";
  protected final String TEXT_1140 = ")";
  protected final String TEXT_1141 = "()).eMap()).set(newValue);";
  protected final String TEXT_1142 = NL + "\t\t\t\t((";
  protected final String TEXT_1143 = ".Setting)";
  protected final String TEXT_1144 = "()).set(newValue);";
  protected final String TEXT_1145 = NL + "\t\t\t\t";
  protected final String TEXT_1146 = "().clear();" + NL + "\t\t\t\t";
  protected final String TEXT_1147 = "().addAll((";
  protected final String TEXT_1148 = "<? extends ";
  protected final String TEXT_1149 = ">";
  protected final String TEXT_1150 = ")newValue);";
  protected final String TEXT_1151 = NL + "\t\t\t\tset";
  protected final String TEXT_1152 = "(((";
  protected final String TEXT_1153 = ")newValue).";
  protected final String TEXT_1154 = "());";
  protected final String TEXT_1155 = NL + "\t\t\t\tset";
  protected final String TEXT_1156 = "(";
  protected final String TEXT_1157 = "(";
  protected final String TEXT_1158 = ")";
  protected final String TEXT_1159 = "newValue);";
  protected final String TEXT_1160 = NL + "\t\t\t\treturn;";
  protected final String TEXT_1161 = NL + "\t\t}";
  protected final String TEXT_1162 = NL + "\t\tsuper.eSet(featureID, newValue);";
  protected final String TEXT_1163 = NL + "\t\teDynamicSet(featureID, newValue);";
  protected final String TEXT_1164 = NL + "\t}" + NL;
  protected final String TEXT_1165 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */";
  protected final String TEXT_1166 = NL + "\t@Override";
  protected final String TEXT_1167 = NL + "\tpublic void eUnset(int featureID)" + NL + "\t{" + NL + "\t\tswitch (featureID)" + NL + "\t\t{";
  protected final String TEXT_1168 = NL + "\t\t\tcase ";
  protected final String TEXT_1169 = ":";
  protected final String TEXT_1170 = NL + "\t\t\t\t((";
  protected final String TEXT_1171 = ".Internal.Wrapper)";
  protected final String TEXT_1172 = "()).featureMap().clear();";
  protected final String TEXT_1173 = NL + "\t\t\t\t";
  protected final String TEXT_1174 = "().clear();";
  protected final String TEXT_1175 = NL + "\t\t\t\tunset";
  protected final String TEXT_1176 = "();";
  protected final String TEXT_1177 = NL + "\t\t\t\tset";
  protected final String TEXT_1178 = "((";
  protected final String TEXT_1179 = ")null);";
  protected final String TEXT_1180 = NL + "\t\t\t\tset";
  protected final String TEXT_1181 = "(";
  protected final String TEXT_1182 = ");";
  protected final String TEXT_1183 = NL + "\t\t\t\treturn;";
  protected final String TEXT_1184 = NL + "\t\t}";
  protected final String TEXT_1185 = NL + "\t\tsuper.eUnset(featureID);";
  protected final String TEXT_1186 = NL + "\t\teDynamicUnset(featureID);";
  protected final String TEXT_1187 = NL + "\t}" + NL;
  protected final String TEXT_1188 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */";
  protected final String TEXT_1189 = NL + "\t@Override";
  protected final String TEXT_1190 = NL + "\tpublic boolean eIsSet(int featureID)" + NL + "\t{" + NL + "\t\tswitch (featureID)" + NL + "\t\t{";
  protected final String TEXT_1191 = NL + "\t\t\tcase ";
  protected final String TEXT_1192 = ":";
  protected final String TEXT_1193 = NL + "\t\t\t\treturn !((";
  protected final String TEXT_1194 = ".Internal.Wrapper)";
  protected final String TEXT_1195 = "()).featureMap().isEmpty();";
  protected final String TEXT_1196 = NL + "\t\t\t\treturn ";
  protected final String TEXT_1197 = " != null && !";
  protected final String TEXT_1198 = ".featureMap().isEmpty();";
  protected final String TEXT_1199 = NL + "\t\t\t\treturn ";
  protected final String TEXT_1200 = " != null && !";
  protected final String TEXT_1201 = ".isEmpty();";
  protected final String TEXT_1202 = NL + "\t\t\t\t";
  protected final String TEXT_1203 = " ";
  protected final String TEXT_1204 = " = (";
  protected final String TEXT_1205 = ")eVirtualGet(";
  protected final String TEXT_1206 = ");" + NL + "\t\t\t\treturn ";
  protected final String TEXT_1207 = " != null && !";
  protected final String TEXT_1208 = ".isEmpty();";
  protected final String TEXT_1209 = NL + "\t\t\t\treturn !";
  protected final String TEXT_1210 = "().isEmpty();";
  protected final String TEXT_1211 = NL + "\t\t\t\treturn isSet";
  protected final String TEXT_1212 = "();";
  protected final String TEXT_1213 = NL + "\t\t\t\treturn ";
  protected final String TEXT_1214 = " != null;";
  protected final String TEXT_1215 = NL + "\t\t\t\treturn eVirtualGet(";
  protected final String TEXT_1216 = ") != null;";
  protected final String TEXT_1217 = NL + "\t\t\t\treturn basicGet";
  protected final String TEXT_1218 = "() != null;";
  protected final String TEXT_1219 = NL + "\t\t\t\treturn ";
  protected final String TEXT_1220 = " != null;";
  protected final String TEXT_1221 = NL + "\t\t\t\treturn eVirtualGet(";
  protected final String TEXT_1222 = ") != null;";
  protected final String TEXT_1223 = NL + "\t\t\t\treturn ";
  protected final String TEXT_1224 = "() != null;";
  protected final String TEXT_1225 = NL + "\t\t\t\treturn ((";
  protected final String TEXT_1226 = " & ";
  protected final String TEXT_1227 = "_EFLAG) != 0) != ";
  protected final String TEXT_1228 = ";";
  protected final String TEXT_1229 = NL + "\t\t\t\treturn ";
  protected final String TEXT_1230 = " != ";
  protected final String TEXT_1231 = ";";
  protected final String TEXT_1232 = NL + "\t\t\t\treturn eVirtualGet(";
  protected final String TEXT_1233 = ", ";
  protected final String TEXT_1234 = ") != ";
  protected final String TEXT_1235 = ";";
  protected final String TEXT_1236 = NL + "\t\t\t\treturn ";
  protected final String TEXT_1237 = "() != ";
  protected final String TEXT_1238 = ";";
  protected final String TEXT_1239 = NL + "\t\t\t\treturn ";
  protected final String TEXT_1240 = " == null ? ";
  protected final String TEXT_1241 = " != null : !";
  protected final String TEXT_1242 = ".equals(";
  protected final String TEXT_1243 = ");";
  protected final String TEXT_1244 = NL + "\t\t\t\t";
  protected final String TEXT_1245 = " ";
  protected final String TEXT_1246 = " = (";
  protected final String TEXT_1247 = ")eVirtualGet(";
  protected final String TEXT_1248 = ", ";
  protected final String TEXT_1249 = ");" + NL + "\t\t\t\treturn ";
  protected final String TEXT_1250 = " == null ? ";
  protected final String TEXT_1251 = " != null : !";
  protected final String TEXT_1252 = ".equals(";
  protected final String TEXT_1253 = ");";
  protected final String TEXT_1254 = NL + "\t\t\t\treturn ";
  protected final String TEXT_1255 = " == null ? ";
  protected final String TEXT_1256 = "() != null : !";
  protected final String TEXT_1257 = ".equals(";
  protected final String TEXT_1258 = "());";
  protected final String TEXT_1259 = NL + "\t\t}";
  protected final String TEXT_1260 = NL + "\t\treturn super.eIsSet(featureID);";
  protected final String TEXT_1261 = NL + "\t\treturn eDynamicIsSet(featureID);";
  protected final String TEXT_1262 = NL + "\t}" + NL;
  protected final String TEXT_1263 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */";
  protected final String TEXT_1264 = NL + "\t@Override";
  protected final String TEXT_1265 = NL + "\tpublic int eBaseStructuralFeatureID(int derivedFeatureID, Class";
  protected final String TEXT_1266 = " baseClass)" + NL + "\t{";
  protected final String TEXT_1267 = NL + "\t\tif (baseClass == ";
  protected final String TEXT_1268 = ".class)" + NL + "\t\t{" + NL + "\t\t\tswitch (derivedFeatureID)" + NL + "\t\t\t{";
  protected final String TEXT_1269 = NL + "\t\t\t\tcase ";
  protected final String TEXT_1270 = ": return ";
  protected final String TEXT_1271 = ";";
  protected final String TEXT_1272 = NL + "\t\t\t\tdefault: return -1;" + NL + "\t\t\t}" + NL + "\t\t}";
  protected final String TEXT_1273 = NL + "\t\treturn super.eBaseStructuralFeatureID(derivedFeatureID, baseClass);" + NL + "\t}" + NL + "" + NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */";
  protected final String TEXT_1274 = NL + "\t@Override";
  protected final String TEXT_1275 = NL + "\tpublic int eDerivedStructuralFeatureID(int baseFeatureID, Class";
  protected final String TEXT_1276 = " baseClass)" + NL + "\t{";
  protected final String TEXT_1277 = NL + "\t\tif (baseClass == ";
  protected final String TEXT_1278 = ".class)" + NL + "\t\t{" + NL + "\t\t\tswitch (baseFeatureID)" + NL + "\t\t\t{";
  protected final String TEXT_1279 = NL + "\t\t\t\tcase ";
  protected final String TEXT_1280 = ": return ";
  protected final String TEXT_1281 = ";";
  protected final String TEXT_1282 = NL + "\t\t\t\tdefault: return -1;" + NL + "\t\t\t}" + NL + "\t\t}";
  protected final String TEXT_1283 = NL + "\t\treturn super.eDerivedStructuralFeatureID(baseFeatureID, baseClass);" + NL + "\t}" + NL;
  protected final String TEXT_1284 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */";
  protected final String TEXT_1285 = NL + "\t@Override";
  protected final String TEXT_1286 = NL + "\tprotected Object[] eVirtualValues()" + NL + "\t{" + NL + "\t\treturn ";
  protected final String TEXT_1287 = ";" + NL + "\t}" + NL + "" + NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */";
  protected final String TEXT_1288 = NL + "\t@Override";
  protected final String TEXT_1289 = NL + "\tprotected void eSetVirtualValues(Object[] newValues)" + NL + "\t{" + NL + "\t\t";
  protected final String TEXT_1290 = " = newValues;" + NL + "\t}" + NL;
  protected final String TEXT_1291 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */";
  protected final String TEXT_1292 = NL + "\t@Override";
  protected final String TEXT_1293 = NL + "\tprotected int eVirtualIndexBits(int offset)" + NL + "\t{" + NL + "\t\tswitch (offset)" + NL + "\t\t{";
  protected final String TEXT_1294 = NL + "\t\t\tcase ";
  protected final String TEXT_1295 = " :" + NL + "\t\t\t\treturn ";
  protected final String TEXT_1296 = ";";
  protected final String TEXT_1297 = NL + "\t\t\tdefault :" + NL + "\t\t\t\tthrow new IndexOutOfBoundsException();" + NL + "\t\t}" + NL + "\t}" + NL + "" + NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */";
  protected final String TEXT_1298 = NL + "\t@Override";
  protected final String TEXT_1299 = NL + "\tprotected void eSetVirtualIndexBits(int offset, int newIndexBits)" + NL + "\t{" + NL + "\t\tswitch (offset)" + NL + "\t\t{";
  protected final String TEXT_1300 = NL + "\t\t\tcase ";
  protected final String TEXT_1301 = " :" + NL + "\t\t\t\t";
  protected final String TEXT_1302 = " = newIndexBits;" + NL + "\t\t\t\tbreak;";
  protected final String TEXT_1303 = NL + "\t\t\tdefault :" + NL + "\t\t\t\tthrow new IndexOutOfBoundsException();" + NL + "\t\t}" + NL + "\t}" + NL;
  protected final String TEXT_1304 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */";
  protected final String TEXT_1305 = NL + "\t@Override";
  protected final String TEXT_1306 = NL + "\tpublic String toString()" + NL + "\t{" + NL + "\t\tif (eIsProxy()) return super.toString();" + NL + "" + NL + "\t\tStringBuffer result = new StringBuffer(super.toString());";
  protected final String TEXT_1307 = NL + "\t\tresult.append(\" (";
  protected final String TEXT_1308 = ": \");";
  protected final String TEXT_1309 = NL + "\t\tresult.append(\", ";
  protected final String TEXT_1310 = ": \");";
  protected final String TEXT_1311 = NL + "\t\tif (eVirtualIsSet(";
  protected final String TEXT_1312 = ")) result.append(eVirtualGet(";
  protected final String TEXT_1313 = ")); else result.append(\"<unset>\");";
  protected final String TEXT_1314 = NL + "\t\tif (";
  protected final String TEXT_1315 = "(";
  protected final String TEXT_1316 = " & ";
  protected final String TEXT_1317 = "_ESETFLAG) != 0";
  protected final String TEXT_1318 = "ESet";
  protected final String TEXT_1319 = ") result.append((";
  protected final String TEXT_1320 = " & ";
  protected final String TEXT_1321 = "_EFLAG) != 0); else result.append(\"<unset>\");";
  protected final String TEXT_1322 = NL + "\t\tif (";
  protected final String TEXT_1323 = "(";
  protected final String TEXT_1324 = " & ";
  protected final String TEXT_1325 = "_ESETFLAG) != 0";
  protected final String TEXT_1326 = "ESet";
  protected final String TEXT_1327 = ") result.append(";
  protected final String TEXT_1328 = "); else result.append(\"<unset>\");";
  protected final String TEXT_1329 = NL + "\t\tresult.append(eVirtualGet(";
  protected final String TEXT_1330 = ", ";
  protected final String TEXT_1331 = "));";
  protected final String TEXT_1332 = NL + "\t\tresult.append((";
  protected final String TEXT_1333 = " & ";
  protected final String TEXT_1334 = "_EFLAG) != 0);";
  protected final String TEXT_1335 = NL + "\t\tresult.append(";
  protected final String TEXT_1336 = ");";
  protected final String TEXT_1337 = NL + "\t\tresult.append(')');" + NL + "\t\treturn result.toString();" + NL + "\t}" + NL;
  protected final String TEXT_1338 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */" + NL + "\tprotected int hash = -1;" + NL + "" + NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */" + NL + "\tpublic int getHash()" + NL + "\t{" + NL + "\t\tif (hash == -1)" + NL + "\t\t{" + NL + "\t\t\t";
  protected final String TEXT_1339 = " theKey = getKey();" + NL + "\t\t\thash = (theKey == null ? 0 : theKey.hashCode());" + NL + "\t\t}" + NL + "\t\treturn hash;" + NL + "\t}" + NL + "" + NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */" + NL + "\tpublic void setHash(int hash)" + NL + "\t{" + NL + "\t\tthis.hash = hash;" + NL + "\t}" + NL + "" + NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */" + NL + "\tpublic ";
  protected final String TEXT_1340 = " getKey()" + NL + "\t{";
  protected final String TEXT_1341 = NL + "\t\treturn new ";
  protected final String TEXT_1342 = "(getTypedKey());";
  protected final String TEXT_1343 = NL + "\t\treturn getTypedKey();";
  protected final String TEXT_1344 = NL + "\t}" + NL + "" + NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */" + NL + "\tpublic void setKey(";
  protected final String TEXT_1345 = " key)" + NL + "\t{";
  protected final String TEXT_1346 = NL + "\t\tgetTypedKey().addAll(";
  protected final String TEXT_1347 = "(";
  protected final String TEXT_1348 = ")";
  protected final String TEXT_1349 = "key);";
  protected final String TEXT_1350 = NL + "\t\tsetTypedKey(key);";
  protected final String TEXT_1351 = NL + "\t\tsetTypedKey(((";
  protected final String TEXT_1352 = ")key).";
  protected final String TEXT_1353 = "());";
  protected final String TEXT_1354 = NL + "\t\tsetTypedKey((";
  protected final String TEXT_1355 = ")key);";
  protected final String TEXT_1356 = NL + "\t}" + NL + "" + NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */" + NL + "\tpublic ";
  protected final String TEXT_1357 = " getValue()" + NL + "\t{";
  protected final String TEXT_1358 = NL + "\t\treturn new ";
  protected final String TEXT_1359 = "(getTypedValue());";
  protected final String TEXT_1360 = NL + "\t\treturn getTypedValue();";
  protected final String TEXT_1361 = NL + "\t}" + NL + "" + NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */" + NL + "\tpublic ";
  protected final String TEXT_1362 = " setValue(";
  protected final String TEXT_1363 = " value)" + NL + "\t{" + NL + "\t\t";
  protected final String TEXT_1364 = " oldValue = getValue();";
  protected final String TEXT_1365 = NL + "\t\tgetTypedValue().clear();" + NL + "\t\tgetTypedValue().addAll(";
  protected final String TEXT_1366 = "(";
  protected final String TEXT_1367 = ")";
  protected final String TEXT_1368 = "value);";
  protected final String TEXT_1369 = NL + "\t\tsetTypedValue(value);";
  protected final String TEXT_1370 = NL + "\t\tsetTypedValue(((";
  protected final String TEXT_1371 = ")value).";
  protected final String TEXT_1372 = "());";
  protected final String TEXT_1373 = NL + "\t\tsetTypedValue((";
  protected final String TEXT_1374 = ")value);";
  protected final String TEXT_1375 = NL + "\t\treturn oldValue;" + NL + "\t}" + NL + "" + NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */";
  protected final String TEXT_1376 = NL + "\t@SuppressWarnings(\"unchecked\")";
  protected final String TEXT_1377 = NL + "\tpublic ";
  protected final String TEXT_1378 = " getEMap()" + NL + "\t{" + NL + "\t\t";
  protected final String TEXT_1379 = " container = eContainer();" + NL + "\t\treturn container == null ? null : (";
  protected final String TEXT_1380 = ")container.eGet(eContainmentFeature());" + NL + "\t}" + NL;
  protected final String TEXT_1381 = NL + "} //";
  protected final String TEXT_1382 = NL;

  public String generate(Object argument)
  {
    final StringBuffer stringBuffer = new StringBuffer();
    
/**
 * <copyright>
 *
 * Copyright (c) 2002-2007 IBM Corporation and others.
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
    for (GenFeature genFeature : genClass.getGenFeatures()) {
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
    for (GenFeature genFeature : genClass.getImplementedGenFeatures()) {
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
    { List<String> eVirtualIndexBitFields = genClass.getEVirtualIndexBitFields(new ArrayList<String>());
    if (!eVirtualIndexBitFields.isEmpty()) {
    for (String eVirtualIndexBitField : eVirtualIndexBitFields) {
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
    for (GenFeature genFeature : genClass.getDeclaredFieldGenFeatures()) {
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
    if (genModel.useGenerics() && genFeature.isListDataType() && genFeature.isSetDefaultValue()) {
    stringBuffer.append(TEXT_79);
    }
    stringBuffer.append(TEXT_80);
    stringBuffer.append(genFeature.getImportedType());
    stringBuffer.append(TEXT_81);
    stringBuffer.append(genFeature.getEDefault());
    if ("".equals(staticDefaultValue)) {
    stringBuffer.append(TEXT_82);
    stringBuffer.append(genFeature.getEcoreFeature().getDefaultValueLiteral());
    stringBuffer.append(TEXT_83);
    } else {
    stringBuffer.append(TEXT_84);
    stringBuffer.append(staticDefaultValue);
    stringBuffer.append(TEXT_85);
    stringBuffer.append(genModel.getNonNLS(staticDefaultValue));
    }
    stringBuffer.append(TEXT_86);
    }
    if (genClass.isField(genFeature)) {
    if (genClass.isFlag(genFeature)) {
    if (genClass.getFlagIndex(genFeature) > 31 && genClass.getFlagIndex(genFeature) % 32 == 0) {
    stringBuffer.append(TEXT_87);
    stringBuffer.append(genClass.getFlagsField(genFeature));
    stringBuffer.append(TEXT_88);
    }
    stringBuffer.append(TEXT_89);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_90);
    stringBuffer.append(genFeature.getFormattedName());
    stringBuffer.append(TEXT_91);
    stringBuffer.append(genFeature.getFeatureKind());
    stringBuffer.append(TEXT_92);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_93);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_94);
    stringBuffer.append("<< " + genClass.getFlagIndex(genFeature) % 32 );
    stringBuffer.append(TEXT_95);
    } else {
    stringBuffer.append(TEXT_96);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_97);
    stringBuffer.append(genFeature.getFormattedName());
    stringBuffer.append(TEXT_98);
    stringBuffer.append(genFeature.getFeatureKind());
    stringBuffer.append(TEXT_99);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_100);
    stringBuffer.append(genFeature.getImportedType());
    stringBuffer.append(TEXT_101);
    stringBuffer.append(genFeature.getSafeName());
    if (genFeature.hasEDefault()) {
    stringBuffer.append(TEXT_102);
    stringBuffer.append(genFeature.getEDefault());
    }
    stringBuffer.append(TEXT_103);
    }
    }
    }
    if (genClass.isESetField(genFeature)) {
    if (genClass.isESetFlag(genFeature)) {
    if (genClass.getESetFlagIndex(genFeature) > 31 && genClass.getESetFlagIndex(genFeature) % 32 == 0) {
    stringBuffer.append(TEXT_104);
    stringBuffer.append(genClass.getESetFlagsField(genFeature));
    stringBuffer.append(TEXT_105);
    }
    stringBuffer.append(TEXT_106);
    stringBuffer.append(genFeature.getFormattedName());
    stringBuffer.append(TEXT_107);
    stringBuffer.append(genFeature.getFeatureKind());
    stringBuffer.append(TEXT_108);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_109);
    stringBuffer.append("<< " + genClass.getESetFlagIndex(genFeature) % 32 );
    stringBuffer.append(TEXT_110);
    } else {
    stringBuffer.append(TEXT_111);
    stringBuffer.append(genFeature.getFormattedName());
    stringBuffer.append(TEXT_112);
    stringBuffer.append(genFeature.getFeatureKind());
    stringBuffer.append(TEXT_113);
    stringBuffer.append(genFeature.getUncapName());
    stringBuffer.append(TEXT_114);
    }
    }
    }
    //Class/declaredFieldGenFeature.override.javajetinc
    }
    if (isImplementation) {
    stringBuffer.append(TEXT_115);
    stringBuffer.append(genClass.getClassName());
    stringBuffer.append(TEXT_116);
    for (GenFeature genFeature : genClass.getFlagGenFeatures("true")) {
    stringBuffer.append(TEXT_117);
    stringBuffer.append(genClass.getFlagsField(genFeature));
    stringBuffer.append(TEXT_118);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_119);
    }
    stringBuffer.append(TEXT_120);
    if (genModel.useClassOverrideAnnotation()) {
    stringBuffer.append(TEXT_121);
    }
    stringBuffer.append(TEXT_122);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.EClass"));
    stringBuffer.append(TEXT_123);
    stringBuffer.append(genClass.getQualifiedClassifierAccessor());
    stringBuffer.append(TEXT_124);
    }
    if (isImplementation && genModel.getFeatureDelegation() == GenDelegationKind.REFLECTIVE_LITERAL && (genClass.getClassExtendsGenClass() == null || genClass.getClassExtendsGenClass().getGenModel().getFeatureDelegation() != GenDelegationKind.REFLECTIVE_LITERAL)) {
    stringBuffer.append(TEXT_125);
    if (genModel.useClassOverrideAnnotation()) {
    stringBuffer.append(TEXT_126);
    }
    stringBuffer.append(TEXT_127);
    stringBuffer.append(genClass.getClassExtendsGenClass() == null ? 0 : genClass.getClassExtendsGenClass().getAllGenFeatures().size());
    stringBuffer.append(TEXT_128);
    }
    //Class/reflectiveDelegation.override.javajetinc
    new Runnable() { public void run() {
    for (GenFeature genFeature : (isImplementation ? genClass.getImplementedGenFeatures() : genClass.getDeclaredGenFeatures())) {
    if (genModel.isArrayAccessors() && genFeature.isListType() && !genFeature.isFeatureMapType() && !genFeature.isMapType()) {
    stringBuffer.append(TEXT_129);
    if (!isImplementation) {
    stringBuffer.append(TEXT_130);
    stringBuffer.append(genFeature.getListItemType());
    stringBuffer.append(TEXT_131);
    stringBuffer.append(genFeature.getGetArrayAccessor());
    stringBuffer.append(TEXT_132);
    } else {
    stringBuffer.append(TEXT_133);
    stringBuffer.append(genFeature.getListItemType());
    stringBuffer.append(TEXT_134);
    stringBuffer.append(genFeature.getGetArrayAccessor());
    stringBuffer.append(TEXT_135);
    if (genFeature.isVolatile()) {
    stringBuffer.append(TEXT_136);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.util.BasicEList"));
    stringBuffer.append(TEXT_137);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.util.BasicEList"));
    stringBuffer.append(TEXT_138);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_139);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_140);
    } else {
    stringBuffer.append(TEXT_141);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_142);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_143);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_144);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.util.BasicEList"));
    stringBuffer.append(TEXT_145);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.util.BasicEList"));
    stringBuffer.append(TEXT_146);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_147);
    }
    stringBuffer.append(TEXT_148);
    stringBuffer.append(genFeature.getListItemType());
    stringBuffer.append(TEXT_149);
    }
    stringBuffer.append(TEXT_150);
    if (!isImplementation) {
    stringBuffer.append(TEXT_151);
    stringBuffer.append(genFeature.getListItemType());
    stringBuffer.append(TEXT_152);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_153);
    } else {
    stringBuffer.append(TEXT_154);
    stringBuffer.append(genFeature.getListItemType());
    stringBuffer.append(TEXT_155);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_156);
    stringBuffer.append(genFeature.getListItemType());
    stringBuffer.append(TEXT_157);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_158);
    }
    stringBuffer.append(TEXT_159);
    if (!isImplementation) {
    stringBuffer.append(TEXT_160);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_161);
    } else {
    stringBuffer.append(TEXT_162);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_163);
    if (genFeature.isVolatile()) {
    stringBuffer.append(TEXT_164);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_165);
    } else {
    stringBuffer.append(TEXT_166);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_167);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_168);
    }
    stringBuffer.append(TEXT_169);
    }
    stringBuffer.append(TEXT_170);
    if (!isImplementation) {
    stringBuffer.append(TEXT_171);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_172);
    stringBuffer.append(genFeature.getListItemType());
    stringBuffer.append(TEXT_173);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_174);
    } else {
    stringBuffer.append(TEXT_175);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_176);
    stringBuffer.append(genFeature.getListItemType());
    stringBuffer.append(TEXT_177);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_178);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.util.BasicEList"));
    stringBuffer.append(TEXT_179);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_180);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_181);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_182);
    }
    stringBuffer.append(TEXT_183);
    if (!isImplementation) {
    stringBuffer.append(TEXT_184);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_185);
    stringBuffer.append(genFeature.getListItemType());
    stringBuffer.append(TEXT_186);
    } else {
    stringBuffer.append(TEXT_187);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_188);
    stringBuffer.append(genFeature.getListItemType());
    stringBuffer.append(TEXT_189);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_190);
    }
    }
    if (genFeature.isGet() && (isImplementation || !genFeature.isSuppressedGetVisibility())) {
    if (isInterface) {
    stringBuffer.append(TEXT_191);
    stringBuffer.append(genFeature.getFormattedName());
    stringBuffer.append(TEXT_192);
    stringBuffer.append(genFeature.getFeatureKind());
    stringBuffer.append(TEXT_193);
    if (genFeature.isListType()) {
    if (genFeature.isMapType()) { GenFeature keyFeature = genFeature.getMapEntryTypeGenClass().getMapEntryKeyFeature(); GenFeature valueFeature = genFeature.getMapEntryTypeGenClass().getMapEntryValueFeature(); 
    stringBuffer.append(TEXT_194);
    if (keyFeature.isListType()) {
    stringBuffer.append(TEXT_195);
    stringBuffer.append(keyFeature.getQualifiedListItemType());
    stringBuffer.append(TEXT_196);
    } else {
    stringBuffer.append(TEXT_197);
    stringBuffer.append(keyFeature.getType());
    stringBuffer.append(TEXT_198);
    }
    stringBuffer.append(TEXT_199);
    if (valueFeature.isListType()) {
    stringBuffer.append(TEXT_200);
    stringBuffer.append(valueFeature.getQualifiedListItemType());
    stringBuffer.append(TEXT_201);
    } else {
    stringBuffer.append(TEXT_202);
    stringBuffer.append(valueFeature.getType());
    stringBuffer.append(TEXT_203);
    }
    stringBuffer.append(TEXT_204);
    } else if (!genFeature.isWrappedFeatureMapType() && !(genModel.isSuppressEMFMetaData() && "org.eclipse.emf.ecore.EObject".equals(genFeature.getQualifiedListItemType()))) {
    stringBuffer.append(TEXT_205);
    stringBuffer.append(genFeature.getQualifiedListItemType());
    stringBuffer.append(TEXT_206);
    }
    } else if (genFeature.isSetDefaultValue()) {
    stringBuffer.append(TEXT_207);
    stringBuffer.append(genFeature.getDefaultValue());
    stringBuffer.append(TEXT_208);
    }
    if (genFeature.getTypeGenEnum() != null) {
    stringBuffer.append(TEXT_209);
    stringBuffer.append(genFeature.getTypeGenEnum().getQualifiedName());
    stringBuffer.append(TEXT_210);
    }
    if (genFeature.isBidirectional() && !genFeature.getReverse().getGenClass().isMapEntry()) { GenFeature reverseGenFeature = genFeature.getReverse(); 
    if (!reverseGenFeature.isSuppressedGetVisibility()) {
    stringBuffer.append(TEXT_211);
    stringBuffer.append(reverseGenFeature.getGenClass().getQualifiedInterfaceName());
    stringBuffer.append(TEXT_212);
    stringBuffer.append(reverseGenFeature.getGetAccessor());
    stringBuffer.append(TEXT_213);
    stringBuffer.append(reverseGenFeature.getFormattedName());
    stringBuffer.append(TEXT_214);
    }
    }
    stringBuffer.append(TEXT_215);
    if (!genFeature.hasDocumentation()) {
    stringBuffer.append(TEXT_216);
    stringBuffer.append(genFeature.getFormattedName());
    stringBuffer.append(TEXT_217);
    stringBuffer.append(genFeature.getFeatureKind());
    stringBuffer.append(TEXT_218);
    }
    stringBuffer.append(TEXT_219);
    if (genFeature.hasDocumentation()) {
    stringBuffer.append(TEXT_220);
    stringBuffer.append(genFeature.getDocumentation(genModel.getIndentation(stringBuffer)));
    stringBuffer.append(TEXT_221);
    }
    stringBuffer.append(TEXT_222);
    stringBuffer.append(genFeature.getFormattedName());
    stringBuffer.append(TEXT_223);
    stringBuffer.append(genFeature.getFeatureKind());
    stringBuffer.append(TEXT_224);
    if (genFeature.getTypeGenEnum() != null) {
    stringBuffer.append(TEXT_225);
    stringBuffer.append(genFeature.getTypeGenEnum().getQualifiedName());
    }
    if (genFeature.isUnsettable()) {
    if (!genFeature.isSuppressedIsSetVisibility()) {
    stringBuffer.append(TEXT_226);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_227);
    }
    if (genFeature.isChangeable() && !genFeature.isSuppressedUnsetVisibility()) {
    stringBuffer.append(TEXT_228);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_229);
    }
    }
    if (genFeature.isChangeable() && !genFeature.isListType() && !genFeature.isSuppressedSetVisibility()) {
    stringBuffer.append(TEXT_230);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_231);
    stringBuffer.append(genFeature.getRawImportedBoundType());
    stringBuffer.append(TEXT_232);
    }
    if (!genModel.isSuppressEMFMetaData()) {
    stringBuffer.append(TEXT_233);
    stringBuffer.append(genPackage.getQualifiedPackageInterfaceName());
    stringBuffer.append(TEXT_234);
    stringBuffer.append(genFeature.getFeatureAccessorName());
    stringBuffer.append(TEXT_235);
    }
    if (genFeature.isBidirectional() && !genFeature.getReverse().getGenClass().isMapEntry()) { GenFeature reverseGenFeature = genFeature.getReverse(); 
    if (!reverseGenFeature.isSuppressedGetVisibility()) {
    stringBuffer.append(TEXT_236);
    stringBuffer.append(reverseGenFeature.getGenClass().getQualifiedInterfaceName());
    stringBuffer.append(TEXT_237);
    stringBuffer.append(reverseGenFeature.getGetAccessor());
    }
    }
    if (!genModel.isSuppressEMFModelTags()) { boolean first = true; for (StringTokenizer stringTokenizer = new StringTokenizer(genFeature.getModelInfo(), "\n\r"); stringTokenizer.hasMoreTokens(); ) { String modelInfo = stringTokenizer.nextToken(); if (first) { first = false;
    stringBuffer.append(TEXT_238);
    stringBuffer.append(modelInfo);
    } else {
    stringBuffer.append(TEXT_239);
    stringBuffer.append(modelInfo);
    }} if (first) {
    stringBuffer.append(TEXT_240);
    }}
    stringBuffer.append(TEXT_241);
    //Class/getGenFeature.javadoc.override.javajetinc
    } else {
    stringBuffer.append(TEXT_242);
    if (genModel.getComplianceLevel().getValue() >= GenJDKLevel.JDK50) { //Class/getGenFeature.annotations.insert.javajetinc
    }
    }
    if (!isImplementation) {
    stringBuffer.append(TEXT_243);
    stringBuffer.append(genFeature.getImportedType());
    stringBuffer.append(TEXT_244);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_245);
    } else {
    if (genModel.useGenerics() && ((genFeature.isContainer() || genFeature.isResolveProxies()) && !genFeature.isListType() && !genModel.isReflectiveDelegation() && genFeature.isUncheckedCast() || genFeature.isListDataType() && genFeature.hasDelegateFeature())) {
    stringBuffer.append(TEXT_246);
    }
    stringBuffer.append(TEXT_247);
    stringBuffer.append(genFeature.getImportedType());
    stringBuffer.append(TEXT_248);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_249);
    if (genModel.isReflectiveDelegation()) {
    stringBuffer.append(TEXT_250);
    if (genFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_251);
    }
    stringBuffer.append(TEXT_252);
    stringBuffer.append(genFeature.getObjectType());
    stringBuffer.append(TEXT_253);
    stringBuffer.append(genFeature.getQualifiedFeatureAccessor());
    stringBuffer.append(TEXT_254);
    if (genFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_255);
    stringBuffer.append(genFeature.getPrimitiveValueFunction());
    stringBuffer.append(TEXT_256);
    }
    stringBuffer.append(TEXT_257);
    } else if (!genFeature.isVolatile()) {
    if (genFeature.isListType()) {
    if (genModel.isVirtualDelegation()) {
    stringBuffer.append(TEXT_258);
    stringBuffer.append(genFeature.getImportedType());
    stringBuffer.append(TEXT_259);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_260);
    stringBuffer.append(genFeature.getImportedType());
    stringBuffer.append(TEXT_261);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(TEXT_262);
    }
    stringBuffer.append(TEXT_263);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_264);
    if (genModel.isVirtualDelegation()) {
    stringBuffer.append(TEXT_265);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(TEXT_266);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_267);
    stringBuffer.append(genClass.getListConstructor(genFeature));
    stringBuffer.append(TEXT_268);
    } else {
    stringBuffer.append(TEXT_269);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_270);
    stringBuffer.append(genClass.getListConstructor(genFeature));
    stringBuffer.append(TEXT_271);
    }
    stringBuffer.append(TEXT_272);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(genFeature.isMapType() && genFeature.isEffectiveSuppressEMFTypes() ? ".map()" : "");
    stringBuffer.append(TEXT_273);
    } else if (genFeature.isContainer()) {
    stringBuffer.append(TEXT_274);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(TEXT_275);
    stringBuffer.append(genFeature.getImportedType());
    stringBuffer.append(TEXT_276);
    } else {
    if (genFeature.isResolveProxies()) {
    if (genModel.isVirtualDelegation()) {
    stringBuffer.append(TEXT_277);
    stringBuffer.append(genFeature.getImportedType());
    stringBuffer.append(TEXT_278);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_279);
    stringBuffer.append(genFeature.getImportedType());
    stringBuffer.append(TEXT_280);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    if (genFeature.hasEDefault()) {
    stringBuffer.append(TEXT_281);
    stringBuffer.append(genFeature.getEDefault());
    }
    stringBuffer.append(TEXT_282);
    }
    stringBuffer.append(TEXT_283);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_284);
    stringBuffer.append(genFeature.getSafeNameAsEObject());
    stringBuffer.append(TEXT_285);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.InternalEObject"));
    stringBuffer.append(TEXT_286);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_287);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.InternalEObject"));
    stringBuffer.append(TEXT_288);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_289);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_290);
    stringBuffer.append(genFeature.getNonEObjectInternalTypeCast());
    stringBuffer.append(TEXT_291);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_292);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_293);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_294);
    if (genFeature.isEffectiveContains()) {
    stringBuffer.append(TEXT_295);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.InternalEObject"));
    stringBuffer.append(TEXT_296);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_297);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.InternalEObject"));
    stringBuffer.append(TEXT_298);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_299);
    if (!genFeature.isBidirectional()) {
    stringBuffer.append(TEXT_300);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.notify.NotificationChain"));
    stringBuffer.append(TEXT_301);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_302);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(TEXT_303);
    } else { GenFeature reverseFeature = genFeature.getReverse(); GenClass targetClass = reverseFeature.getGenClass();
    stringBuffer.append(TEXT_304);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.notify.NotificationChain"));
    stringBuffer.append(TEXT_305);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_306);
    stringBuffer.append(targetClass.getQualifiedFeatureID(reverseFeature));
    stringBuffer.append(TEXT_307);
    stringBuffer.append(targetClass.getImportedInterfaceName());
    stringBuffer.append(TEXT_308);
    }
    stringBuffer.append(TEXT_309);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_310);
    if (!genFeature.isBidirectional()) {
    stringBuffer.append(TEXT_311);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_312);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(TEXT_313);
    } else { GenFeature reverseFeature = genFeature.getReverse(); GenClass targetClass = reverseFeature.getGenClass();
    stringBuffer.append(TEXT_314);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_315);
    stringBuffer.append(targetClass.getQualifiedFeatureID(reverseFeature));
    stringBuffer.append(TEXT_316);
    stringBuffer.append(targetClass.getImportedInterfaceName());
    stringBuffer.append(TEXT_317);
    }
    stringBuffer.append(TEXT_318);
    } else if (genModel.isVirtualDelegation()) {
    stringBuffer.append(TEXT_319);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(TEXT_320);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_321);
    }
    if (!genModel.isSuppressNotification()) {
    stringBuffer.append(TEXT_322);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.impl.ENotificationImpl"));
    stringBuffer.append(TEXT_323);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.notify.Notification"));
    stringBuffer.append(TEXT_324);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(TEXT_325);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_326);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_327);
    }
    stringBuffer.append(TEXT_328);
    }
    if (!genFeature.isResolveProxies() && genModel.isVirtualDelegation() && !genFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_329);
    stringBuffer.append(genFeature.getImportedType());
    stringBuffer.append(TEXT_330);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    if (genFeature.hasEDefault()) {
    stringBuffer.append(TEXT_331);
    stringBuffer.append(genFeature.getEDefault());
    }
    stringBuffer.append(TEXT_332);
    } else if (genClass.isFlag(genFeature)) {
    stringBuffer.append(TEXT_333);
    stringBuffer.append(genClass.getFlagsField(genFeature));
    stringBuffer.append(TEXT_334);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_335);
    } else {
    stringBuffer.append(TEXT_336);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_337);
    }
    }
    } else {//volatile
    if (genFeature.isResolveProxies() && !genFeature.isListType()) {
    stringBuffer.append(TEXT_338);
    stringBuffer.append(genFeature.getImportedType());
    stringBuffer.append(TEXT_339);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_340);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_341);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_342);
    stringBuffer.append(genFeature.getSafeNameAsEObject());
    stringBuffer.append(TEXT_343);
    stringBuffer.append(genFeature.getNonEObjectInternalTypeCast());
    stringBuffer.append(TEXT_344);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.InternalEObject"));
    stringBuffer.append(TEXT_345);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_346);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_347);
    } else if (genFeature.hasDelegateFeature()) { GenFeature delegateFeature = genFeature.getDelegateFeature();
    if (genFeature.isFeatureMapType()) {
    String featureMapEntryTemplateArgument = genModel.getComplianceLevel().getValue() >= GenJDKLevel.JDK50 ? "<" + genModel.getImportedName("org.eclipse.emf.ecore.util.FeatureMap") + ".Entry>" : "";
    if (delegateFeature.isWrappedFeatureMapType()) {
    stringBuffer.append(TEXT_348);
    stringBuffer.append(genFeature.getImportedEffectiveFeatureMapWrapperClass());
    stringBuffer.append(TEXT_349);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.FeatureMap"));
    stringBuffer.append(TEXT_350);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.FeatureMap"));
    stringBuffer.append(TEXT_351);
    stringBuffer.append(delegateFeature.getAccessorName());
    stringBuffer.append(TEXT_352);
    stringBuffer.append(featureMapEntryTemplateArgument);
    stringBuffer.append(TEXT_353);
    stringBuffer.append(genFeature.getQualifiedFeatureAccessor());
    stringBuffer.append(TEXT_354);
    } else {
    stringBuffer.append(TEXT_355);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.FeatureMap"));
    stringBuffer.append(TEXT_356);
    stringBuffer.append(delegateFeature.getAccessorName());
    stringBuffer.append(TEXT_357);
    stringBuffer.append(featureMapEntryTemplateArgument);
    stringBuffer.append(TEXT_358);
    stringBuffer.append(genFeature.getQualifiedFeatureAccessor());
    stringBuffer.append(TEXT_359);
    }
    } else if (genFeature.isListType()) {
    if (delegateFeature.isWrappedFeatureMapType()) {
    stringBuffer.append(TEXT_360);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.FeatureMap"));
    stringBuffer.append(TEXT_361);
    stringBuffer.append(delegateFeature.getAccessorName());
    stringBuffer.append(TEXT_362);
    stringBuffer.append(genFeature.getQualifiedFeatureAccessor());
    stringBuffer.append(TEXT_363);
    } else {
    stringBuffer.append(TEXT_364);
    stringBuffer.append(delegateFeature.getAccessorName());
    stringBuffer.append(TEXT_365);
    stringBuffer.append(genFeature.getQualifiedFeatureAccessor());
    stringBuffer.append(TEXT_366);
    }
    } else {
    if (delegateFeature.isWrappedFeatureMapType()) {
    stringBuffer.append(TEXT_367);
    if (genFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_368);
    }
    if (genFeature.getTypeGenDataType() == null || !genFeature.getTypeGenDataType().isObjectType()) {
    stringBuffer.append(TEXT_369);
    stringBuffer.append(genFeature.getObjectType());
    stringBuffer.append(TEXT_370);
    }
    stringBuffer.append(TEXT_371);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.FeatureMap"));
    stringBuffer.append(TEXT_372);
    stringBuffer.append(delegateFeature.getAccessorName());
    stringBuffer.append(TEXT_373);
    stringBuffer.append(genFeature.getQualifiedFeatureAccessor());
    stringBuffer.append(TEXT_374);
    if (genFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_375);
    stringBuffer.append(genFeature.getPrimitiveValueFunction());
    stringBuffer.append(TEXT_376);
    }
    stringBuffer.append(TEXT_377);
    } else {
    stringBuffer.append(TEXT_378);
    if (genFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_379);
    }
    if (genFeature.getTypeGenDataType() == null || !genFeature.getTypeGenDataType().isObjectType()) {
    stringBuffer.append(TEXT_380);
    stringBuffer.append(genFeature.getObjectType());
    stringBuffer.append(TEXT_381);
    }
    stringBuffer.append(TEXT_382);
    stringBuffer.append(delegateFeature.getAccessorName());
    stringBuffer.append(TEXT_383);
    stringBuffer.append(genFeature.getQualifiedFeatureAccessor());
    stringBuffer.append(TEXT_384);
    if (genFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_385);
    stringBuffer.append(genFeature.getPrimitiveValueFunction());
    stringBuffer.append(TEXT_386);
    }
    stringBuffer.append(TEXT_387);
    }
    }
    } else {
    stringBuffer.append(TEXT_388);
    stringBuffer.append(genFeature.getFormattedName());
    stringBuffer.append(TEXT_389);
    stringBuffer.append(genFeature.getFeatureKind());
    stringBuffer.append(TEXT_390);
    if (genFeature.isListType()) {
    stringBuffer.append(TEXT_391);
    if (genFeature.isMapType()) {
    stringBuffer.append(TEXT_392);
    } else if (genFeature.isFeatureMapType()) {
    stringBuffer.append(TEXT_393);
    } else {
    stringBuffer.append(TEXT_394);
    }
    stringBuffer.append(TEXT_395);
    }
    stringBuffer.append(TEXT_396);
    //Class/getGenFeature.todo.override.javajetinc
    }
    }
    stringBuffer.append(TEXT_397);
    }
    //Class/getGenFeature.override.javajetinc
    }
    if (isImplementation && !genModel.isReflectiveDelegation() && genFeature.isBasicGet()) {
    stringBuffer.append(TEXT_398);
    if (genModel.getComplianceLevel().getValue() >= GenJDKLevel.JDK50) { //Class/basicGetGenFeature.annotations.insert.javajetinc
    }
    stringBuffer.append(TEXT_399);
    stringBuffer.append(genFeature.getImportedType());
    stringBuffer.append(TEXT_400);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_401);
    if (genFeature.isContainer()) {
    stringBuffer.append(TEXT_402);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(TEXT_403);
    stringBuffer.append(genFeature.getImportedType());
    stringBuffer.append(TEXT_404);
    } else if (!genFeature.isVolatile()) {
    if (genModel.isVirtualDelegation()) {
    stringBuffer.append(TEXT_405);
    stringBuffer.append(genFeature.getImportedType());
    stringBuffer.append(TEXT_406);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(TEXT_407);
    } else {
    stringBuffer.append(TEXT_408);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_409);
    }
    } else if (genFeature.hasDelegateFeature()) { GenFeature delegateFeature = genFeature.getDelegateFeature();
    if (delegateFeature.isWrappedFeatureMapType()) {
    stringBuffer.append(TEXT_410);
    stringBuffer.append(genFeature.getImportedType());
    stringBuffer.append(TEXT_411);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.FeatureMap"));
    stringBuffer.append(TEXT_412);
    stringBuffer.append(delegateFeature.getAccessorName());
    stringBuffer.append(TEXT_413);
    stringBuffer.append(genFeature.getQualifiedFeatureAccessor());
    stringBuffer.append(TEXT_414);
    } else {
    stringBuffer.append(TEXT_415);
    stringBuffer.append(genFeature.getImportedType());
    stringBuffer.append(TEXT_416);
    stringBuffer.append(delegateFeature.getAccessorName());
    stringBuffer.append(TEXT_417);
    stringBuffer.append(genFeature.getQualifiedFeatureAccessor());
    stringBuffer.append(TEXT_418);
    }
    } else {
    stringBuffer.append(TEXT_419);
    stringBuffer.append(genFeature.getFormattedName());
    stringBuffer.append(TEXT_420);
    stringBuffer.append(genFeature.getFeatureKind());
    stringBuffer.append(TEXT_421);
    //Class/basicGetGenFeature.todo.override.javajetinc
    }
    stringBuffer.append(TEXT_422);
    //Class/basicGetGenFeature.override.javajetinc
    }
    if (isImplementation && !genModel.isReflectiveDelegation() && genFeature.isBasicSet()) {
    stringBuffer.append(TEXT_423);
    if (genModel.getComplianceLevel().getValue() >= GenJDKLevel.JDK50) { //Class/basicSetGenFeature.annotations.insert.javajetinc
    }
    stringBuffer.append(TEXT_424);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.notify.NotificationChain"));
    stringBuffer.append(TEXT_425);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_426);
    stringBuffer.append(genFeature.getImportedInternalType());
    stringBuffer.append(TEXT_427);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_428);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.notify.NotificationChain"));
    stringBuffer.append(TEXT_429);
    if (genFeature.isContainer()) {
    stringBuffer.append(TEXT_430);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.InternalEObject"));
    stringBuffer.append(TEXT_431);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_432);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(TEXT_433);
    stringBuffer.append(TEXT_434);
    } else if (!genFeature.isVolatile()) {
    if (genModel.isVirtualDelegation()) {
    stringBuffer.append(TEXT_435);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_436);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(TEXT_437);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_438);
    } else {
    stringBuffer.append(TEXT_439);
    stringBuffer.append(genFeature.getImportedType());
    stringBuffer.append(TEXT_440);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_441);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_442);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_443);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_444);
    }
    if (genFeature.isUnsettable()) {
    if (genModel.isVirtualDelegation()) {
    stringBuffer.append(TEXT_445);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_446);
    } else if (genClass.isESetFlag(genFeature)) {
    stringBuffer.append(TEXT_447);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_448);
    stringBuffer.append(genClass.getESetFlagsField(genFeature));
    stringBuffer.append(TEXT_449);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_450);
    stringBuffer.append(genClass.getESetFlagsField(genFeature));
    stringBuffer.append(TEXT_451);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_452);
    } else {
    stringBuffer.append(TEXT_453);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_454);
    stringBuffer.append(genFeature.getUncapName());
    stringBuffer.append(TEXT_455);
    stringBuffer.append(genFeature.getUncapName());
    stringBuffer.append(TEXT_456);
    }
    }
    if (!genModel.isSuppressNotification()) {
    stringBuffer.append(TEXT_457);
    if (genFeature.isUnsettable()) {
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
    } else {
    stringBuffer.append(TEXT_464);
    stringBuffer.append(genFeature.getCapName());
    }
    stringBuffer.append(TEXT_465);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_466);
    if (genModel.isVirtualDelegation()) {
    stringBuffer.append(TEXT_467);
    } else {
    stringBuffer.append(TEXT_468);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_469);
    }
    stringBuffer.append(TEXT_470);
    } else {
    stringBuffer.append(TEXT_471);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.impl.ENotificationImpl"));
    stringBuffer.append(TEXT_472);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.impl.ENotificationImpl"));
    stringBuffer.append(TEXT_473);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.notify.Notification"));
    stringBuffer.append(TEXT_474);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(TEXT_475);
    if (genModel.isVirtualDelegation()) {
    stringBuffer.append(TEXT_476);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_477);
    stringBuffer.append(genFeature.getCapName());
    } else {
    stringBuffer.append(TEXT_478);
    stringBuffer.append(genFeature.getCapName());
    }
    stringBuffer.append(TEXT_479);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_480);
    }
    stringBuffer.append(TEXT_481);
    }
    stringBuffer.append(TEXT_482);
    } else if (genFeature.hasDelegateFeature()) { GenFeature delegateFeature = genFeature.getDelegateFeature();
    if (delegateFeature.isWrappedFeatureMapType()) {
    stringBuffer.append(TEXT_483);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.FeatureMap"));
    stringBuffer.append(TEXT_484);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.FeatureMap"));
    stringBuffer.append(TEXT_485);
    stringBuffer.append(delegateFeature.getAccessorName());
    stringBuffer.append(TEXT_486);
    stringBuffer.append(genFeature.getQualifiedFeatureAccessor());
    stringBuffer.append(TEXT_487);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_488);
    } else {
    stringBuffer.append(TEXT_489);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.FeatureMap"));
    stringBuffer.append(TEXT_490);
    stringBuffer.append(delegateFeature.getAccessorName());
    stringBuffer.append(TEXT_491);
    stringBuffer.append(genFeature.getQualifiedFeatureAccessor());
    stringBuffer.append(TEXT_492);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_493);
    }
    } else {
    stringBuffer.append(TEXT_494);
    stringBuffer.append(genFeature.getFormattedName());
    stringBuffer.append(TEXT_495);
    stringBuffer.append(genFeature.getFeatureKind());
    stringBuffer.append(TEXT_496);
    //Class/basicSetGenFeature.todo.override.javajetinc
    }
    stringBuffer.append(TEXT_497);
    //Class/basicSetGenFeature.override.javajetinc
    }
    if (genFeature.isSet() && (isImplementation || !genFeature.isSuppressedSetVisibility())) {
    if (isInterface) { 
    stringBuffer.append(TEXT_498);
    stringBuffer.append(genClass.getQualifiedInterfaceName());
    stringBuffer.append(TEXT_499);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_500);
    stringBuffer.append(genFeature.getFormattedName());
    stringBuffer.append(TEXT_501);
    stringBuffer.append(genFeature.getFeatureKind());
    stringBuffer.append(TEXT_502);
    stringBuffer.append(TEXT_503);
    stringBuffer.append(genFeature.getFormattedName());
    stringBuffer.append(TEXT_504);
    stringBuffer.append(genFeature.getFeatureKind());
    stringBuffer.append(TEXT_505);
    if (genFeature.isEnumType()) {
    stringBuffer.append(TEXT_506);
    stringBuffer.append(genFeature.getTypeGenEnum().getQualifiedName());
    }
    if (genFeature.isUnsettable()) {
    if (!genFeature.isSuppressedIsSetVisibility()) {
    stringBuffer.append(TEXT_507);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_508);
    }
    if (!genFeature.isSuppressedUnsetVisibility()) {
    stringBuffer.append(TEXT_509);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_510);
    }
    }
    stringBuffer.append(TEXT_511);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_512);
    //Class/setGenFeature.javadoc.override.javajetinc
    } else {
    stringBuffer.append(TEXT_513);
    if (genModel.getComplianceLevel().getValue() >= GenJDKLevel.JDK50) { //Class/setGenFeature.annotations.insert.javajetinc
    }
    }
    if (!isImplementation) { 
    stringBuffer.append(TEXT_514);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_515);
    stringBuffer.append(genFeature.getImportedType());
    stringBuffer.append(TEXT_516);
    } else {
    stringBuffer.append(TEXT_517);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_518);
    stringBuffer.append(genFeature.getImportedType());
    stringBuffer.append(TEXT_519);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_520);
    if (genModel.isReflectiveDelegation()) {
    stringBuffer.append(TEXT_521);
    stringBuffer.append(genFeature.getQualifiedFeatureAccessor());
    stringBuffer.append(TEXT_522);
    if (genFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_523);
    stringBuffer.append(genFeature.getObjectType());
    stringBuffer.append(TEXT_524);
    }
    stringBuffer.append(TEXT_525);
    stringBuffer.append(genFeature.getCapName());
    if (genFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_526);
    }
    stringBuffer.append(TEXT_527);
    } else if (!genFeature.isVolatile()) {
    if (genFeature.isContainer()) { GenFeature reverseFeature = genFeature.getReverse(); GenClass targetClass = reverseFeature.getGenClass();
    stringBuffer.append(TEXT_528);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_529);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(TEXT_530);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_531);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.EcoreUtil"));
    stringBuffer.append(TEXT_532);
    stringBuffer.append(genFeature.getEObjectCast());
    stringBuffer.append(TEXT_533);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_534);
    stringBuffer.append(genModel.getImportedName("java.lang.IllegalArgumentException"));
    stringBuffer.append(TEXT_535);
    stringBuffer.append(genModel.getNonNLS());
    stringBuffer.append(TEXT_536);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.notify.NotificationChain"));
    stringBuffer.append(TEXT_537);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_538);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.InternalEObject"));
    stringBuffer.append(TEXT_539);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_540);
    stringBuffer.append(targetClass.getQualifiedFeatureID(reverseFeature));
    stringBuffer.append(TEXT_541);
    stringBuffer.append(targetClass.getImportedInterfaceName());
    stringBuffer.append(TEXT_542);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_543);
    stringBuffer.append(genFeature.getInternalTypeCast());
    stringBuffer.append(TEXT_544);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_545);
    if (!genModel.isSuppressNotification()) {
    stringBuffer.append(TEXT_546);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.impl.ENotificationImpl"));
    stringBuffer.append(TEXT_547);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.notify.Notification"));
    stringBuffer.append(TEXT_548);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(TEXT_549);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_550);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_551);
    }
    } else if (genFeature.isBidirectional() || genFeature.isEffectiveContains()) {
    if (genModel.isVirtualDelegation()) {
    stringBuffer.append(TEXT_552);
    stringBuffer.append(genFeature.getImportedType());
    stringBuffer.append(TEXT_553);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_554);
    stringBuffer.append(genFeature.getImportedType());
    stringBuffer.append(TEXT_555);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(TEXT_556);
    }
    stringBuffer.append(TEXT_557);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_558);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_559);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.notify.NotificationChain"));
    stringBuffer.append(TEXT_560);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_561);
    if (!genFeature.isBidirectional()) {
    stringBuffer.append(TEXT_562);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.InternalEObject"));
    stringBuffer.append(TEXT_563);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_564);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(TEXT_565);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_566);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.InternalEObject"));
    stringBuffer.append(TEXT_567);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_568);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(TEXT_569);
    } else { GenFeature reverseFeature = genFeature.getReverse(); GenClass targetClass = reverseFeature.getGenClass();
    stringBuffer.append(TEXT_570);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.InternalEObject"));
    stringBuffer.append(TEXT_571);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_572);
    stringBuffer.append(targetClass.getQualifiedFeatureID(reverseFeature));
    stringBuffer.append(TEXT_573);
    stringBuffer.append(targetClass.getImportedInterfaceName());
    stringBuffer.append(TEXT_574);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_575);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.InternalEObject"));
    stringBuffer.append(TEXT_576);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_577);
    stringBuffer.append(targetClass.getQualifiedFeatureID(reverseFeature));
    stringBuffer.append(TEXT_578);
    stringBuffer.append(targetClass.getImportedInterfaceName());
    stringBuffer.append(TEXT_579);
    }
    stringBuffer.append(TEXT_580);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_581);
    stringBuffer.append(genFeature.getInternalTypeCast());
    stringBuffer.append(TEXT_582);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_583);
    if (genFeature.isUnsettable()) {
    stringBuffer.append(TEXT_584);
    if (genModel.isVirtualDelegation()) {
    stringBuffer.append(TEXT_585);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_586);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(TEXT_587);
    } else if (genClass.isESetFlag(genFeature)) {
    if (!genModel.isSuppressNotification()) {
    stringBuffer.append(TEXT_588);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_589);
    stringBuffer.append(genClass.getESetFlagsField(genFeature));
    stringBuffer.append(TEXT_590);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_591);
    }
    stringBuffer.append(TEXT_592);
    stringBuffer.append(genClass.getESetFlagsField(genFeature));
    stringBuffer.append(TEXT_593);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_594);
    } else {
    if (!genModel.isSuppressNotification()) {
    stringBuffer.append(TEXT_595);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_596);
    stringBuffer.append(genFeature.getUncapName());
    stringBuffer.append(TEXT_597);
    }
    stringBuffer.append(TEXT_598);
    stringBuffer.append(genFeature.getUncapName());
    stringBuffer.append(TEXT_599);
    }
    if (!genModel.isSuppressNotification()) {
    stringBuffer.append(TEXT_600);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.impl.ENotificationImpl"));
    stringBuffer.append(TEXT_601);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.notify.Notification"));
    stringBuffer.append(TEXT_602);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(TEXT_603);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_604);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_605);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_606);
    }
    stringBuffer.append(TEXT_607);
    } else {
    if (!genModel.isSuppressNotification()) {
    stringBuffer.append(TEXT_608);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.impl.ENotificationImpl"));
    stringBuffer.append(TEXT_609);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.notify.Notification"));
    stringBuffer.append(TEXT_610);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(TEXT_611);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_612);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_613);
    }
    }
    } else {
    if (genClass.isFlag(genFeature)) {
    if (!genModel.isSuppressNotification()) {
    stringBuffer.append(TEXT_614);
    stringBuffer.append(genFeature.getImportedType());
    stringBuffer.append(TEXT_615);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_616);
    stringBuffer.append(genClass.getFlagsField(genFeature));
    stringBuffer.append(TEXT_617);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_618);
    }
    stringBuffer.append(TEXT_619);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_620);
    stringBuffer.append(genClass.getFlagsField(genFeature));
    stringBuffer.append(TEXT_621);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_622);
    stringBuffer.append(genClass.getFlagsField(genFeature));
    stringBuffer.append(TEXT_623);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_624);
    } else {
    if (!genModel.isVirtualDelegation() || genFeature.isPrimitiveType()) {
    if (!genModel.isSuppressNotification()) {
    stringBuffer.append(TEXT_625);
    stringBuffer.append(genFeature.getImportedType());
    stringBuffer.append(TEXT_626);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_627);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_628);
    }
    }
    if (genFeature.isEnumType()) {
    if (genModel.isVirtualDelegation()) {
    stringBuffer.append(TEXT_629);
    stringBuffer.append(genFeature.getImportedType());
    stringBuffer.append(TEXT_630);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_631);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_632);
    stringBuffer.append(genFeature.getEDefault());
    stringBuffer.append(TEXT_633);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_634);
    } else {
    stringBuffer.append(TEXT_635);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_636);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_637);
    stringBuffer.append(genFeature.getEDefault());
    stringBuffer.append(TEXT_638);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_639);
    }
    } else {
    if (genModel.isVirtualDelegation() && !genFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_640);
    stringBuffer.append(genFeature.getImportedType());
    stringBuffer.append(TEXT_641);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_642);
    stringBuffer.append(genFeature.getInternalTypeCast());
    stringBuffer.append(TEXT_643);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_644);
    } else {
    stringBuffer.append(TEXT_645);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_646);
    stringBuffer.append(genFeature.getInternalTypeCast());
    stringBuffer.append(TEXT_647);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_648);
    }
    }
    if (genModel.isVirtualDelegation() && !genFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_649);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_650);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(TEXT_651);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_652);
    }
    }
    if (genFeature.isUnsettable()) {
    if (genModel.isVirtualDelegation() && !genFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_653);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_654);
    } else if (genClass.isESetFlag(genFeature)) {
    if (!genModel.isSuppressNotification()) {
    stringBuffer.append(TEXT_655);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_656);
    stringBuffer.append(genClass.getESetFlagsField(genFeature));
    stringBuffer.append(TEXT_657);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_658);
    }
    stringBuffer.append(TEXT_659);
    stringBuffer.append(genClass.getESetFlagsField(genFeature));
    stringBuffer.append(TEXT_660);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_661);
    } else {
    if (!genModel.isSuppressNotification()) {
    stringBuffer.append(TEXT_662);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_663);
    stringBuffer.append(genFeature.getUncapName());
    stringBuffer.append(TEXT_664);
    }
    stringBuffer.append(TEXT_665);
    stringBuffer.append(genFeature.getUncapName());
    stringBuffer.append(TEXT_666);
    }
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
    stringBuffer.append(genFeature.getEDefault());
    stringBuffer.append(TEXT_672);
    stringBuffer.append(genFeature.getCapName());
    } else {
    stringBuffer.append(TEXT_673);
    stringBuffer.append(genFeature.getCapName());
    }
    stringBuffer.append(TEXT_674);
    if (genClass.isFlag(genFeature)) {
    stringBuffer.append(TEXT_675);
    stringBuffer.append(genFeature.getCapName());
    } else {
    stringBuffer.append(genFeature.getSafeName());
    }
    stringBuffer.append(TEXT_676);
    if (genModel.isVirtualDelegation() && !genFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_677);
    } else {
    stringBuffer.append(TEXT_678);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_679);
    }
    stringBuffer.append(TEXT_680);
    }
    } else {
    if (!genModel.isSuppressNotification()) {
    stringBuffer.append(TEXT_681);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.impl.ENotificationImpl"));
    stringBuffer.append(TEXT_682);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.notify.Notification"));
    stringBuffer.append(TEXT_683);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(TEXT_684);
    if (genModel.isVirtualDelegation() && !genFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_685);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_686);
    stringBuffer.append(genFeature.getEDefault());
    stringBuffer.append(TEXT_687);
    stringBuffer.append(genFeature.getCapName());
    } else {
    stringBuffer.append(TEXT_688);
    stringBuffer.append(genFeature.getCapName());
    }
    stringBuffer.append(TEXT_689);
    if (genClass.isFlag(genFeature)) {
    stringBuffer.append(TEXT_690);
    stringBuffer.append(genFeature.getCapName());
    } else {
    stringBuffer.append(genFeature.getSafeName());
    }
    stringBuffer.append(TEXT_691);
    }
    }
    }
    } else if (genFeature.hasDelegateFeature()) { GenFeature delegateFeature = genFeature.getDelegateFeature();
    if (delegateFeature.isWrappedFeatureMapType()) {
    stringBuffer.append(TEXT_692);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.FeatureMap"));
    stringBuffer.append(TEXT_693);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.FeatureMap"));
    stringBuffer.append(TEXT_694);
    stringBuffer.append(delegateFeature.getAccessorName());
    stringBuffer.append(TEXT_695);
    stringBuffer.append(genFeature.getQualifiedFeatureAccessor());
    stringBuffer.append(TEXT_696);
    if (genFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_697);
    stringBuffer.append(genFeature.getObjectType());
    stringBuffer.append(TEXT_698);
    }
    stringBuffer.append(TEXT_699);
    stringBuffer.append(genFeature.getCapName());
    if (genFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_700);
    }
    stringBuffer.append(TEXT_701);
    } else {
    stringBuffer.append(TEXT_702);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.FeatureMap"));
    stringBuffer.append(TEXT_703);
    stringBuffer.append(delegateFeature.getAccessorName());
    stringBuffer.append(TEXT_704);
    stringBuffer.append(genFeature.getQualifiedFeatureAccessor());
    stringBuffer.append(TEXT_705);
    if (genFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_706);
    stringBuffer.append(genFeature.getObjectType());
    stringBuffer.append(TEXT_707);
    }
    stringBuffer.append(TEXT_708);
    stringBuffer.append(genFeature.getCapName());
    if (genFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_709);
    }
    stringBuffer.append(TEXT_710);
    }
    } else {
    stringBuffer.append(TEXT_711);
    stringBuffer.append(genFeature.getFormattedName());
    stringBuffer.append(TEXT_712);
    stringBuffer.append(genFeature.getFeatureKind());
    stringBuffer.append(TEXT_713);
    //Class/setGenFeature.todo.override.javajetinc
    }
    stringBuffer.append(TEXT_714);
    }
    //Class/setGenFeature.override.javajetinc
    }
    if (isImplementation && !genModel.isReflectiveDelegation() && genFeature.isBasicUnset()) {
    stringBuffer.append(TEXT_715);
    if (genModel.getComplianceLevel().getValue() >= GenJDKLevel.JDK50) { //Class/basicUnsetGenFeature.annotations.insert.javajetinc
    }
    stringBuffer.append(TEXT_716);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.notify.NotificationChain"));
    stringBuffer.append(TEXT_717);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_718);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.notify.NotificationChain"));
    stringBuffer.append(TEXT_719);
    if (!genFeature.isVolatile()) {
    if (genModel.isVirtualDelegation()) {
    stringBuffer.append(TEXT_720);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_721);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(TEXT_722);
    } else {
    stringBuffer.append(TEXT_723);
    stringBuffer.append(genFeature.getImportedType());
    stringBuffer.append(TEXT_724);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_725);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_726);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_727);
    }
    if (genModel.isVirtualDelegation()) {
    stringBuffer.append(TEXT_728);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_729);
    } else if (genClass.isESetFlag(genFeature)) {
    stringBuffer.append(TEXT_730);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_731);
    stringBuffer.append(genClass.getESetFlagsField(genFeature));
    stringBuffer.append(TEXT_732);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_733);
    stringBuffer.append(genClass.getESetFlagsField(genFeature));
    stringBuffer.append(TEXT_734);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_735);
    } else {
    stringBuffer.append(TEXT_736);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_737);
    stringBuffer.append(genFeature.getUncapName());
    stringBuffer.append(TEXT_738);
    stringBuffer.append(genFeature.getUncapName());
    stringBuffer.append(TEXT_739);
    }
    if (!genModel.isSuppressNotification()) {
    stringBuffer.append(TEXT_740);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.impl.ENotificationImpl"));
    stringBuffer.append(TEXT_741);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.impl.ENotificationImpl"));
    stringBuffer.append(TEXT_742);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.notify.Notification"));
    stringBuffer.append(TEXT_743);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(TEXT_744);
    if (genModel.isVirtualDelegation()) {
    stringBuffer.append(TEXT_745);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_746);
    } else {
    stringBuffer.append(TEXT_747);
    stringBuffer.append(genFeature.getCapName());
    }
    stringBuffer.append(TEXT_748);
    if (genModel.isVirtualDelegation()) {
    stringBuffer.append(TEXT_749);
    } else {
    stringBuffer.append(TEXT_750);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_751);
    }
    stringBuffer.append(TEXT_752);
    }
    } else {
    stringBuffer.append(TEXT_753);
    stringBuffer.append(genFeature.getFormattedName());
    stringBuffer.append(TEXT_754);
    stringBuffer.append(genFeature.getFeatureKind());
    stringBuffer.append(TEXT_755);
    //Class/basicUnsetGenFeature.todo.override.javajetinc
    }
    stringBuffer.append(TEXT_756);
    //Class.basicUnsetGenFeature.override.javajetinc
    }
    if (genFeature.isUnset() && (isImplementation || !genFeature.isSuppressedUnsetVisibility())) {
    if (isInterface) {
    stringBuffer.append(TEXT_757);
    stringBuffer.append(genClass.getQualifiedInterfaceName());
    stringBuffer.append(TEXT_758);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_759);
    stringBuffer.append(genFeature.getFormattedName());
    stringBuffer.append(TEXT_760);
    stringBuffer.append(genFeature.getFeatureKind());
    stringBuffer.append(TEXT_761);
    stringBuffer.append(TEXT_762);
    if (!genFeature.isSuppressedIsSetVisibility()) {
    stringBuffer.append(TEXT_763);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_764);
    }
    stringBuffer.append(TEXT_765);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_766);
    if (!genFeature.isListType() && !genFeature.isSuppressedSetVisibility()) {
    stringBuffer.append(TEXT_767);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_768);
    stringBuffer.append(genFeature.getRawImportedBoundType());
    stringBuffer.append(TEXT_769);
    }
    stringBuffer.append(TEXT_770);
    //Class/unsetGenFeature.javadoc.override.javajetinc
    } else {
    stringBuffer.append(TEXT_771);
    if (genModel.getComplianceLevel().getValue() >= GenJDKLevel.JDK50) { //Class/unsetGenFeature.annotations.insert.javajetinc
    }
    }
    if (!isImplementation) {
    stringBuffer.append(TEXT_772);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_773);
    } else {
    stringBuffer.append(TEXT_774);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_775);
    if (genModel.isReflectiveDelegation()) {
    stringBuffer.append(TEXT_776);
    stringBuffer.append(genFeature.getQualifiedFeatureAccessor());
    stringBuffer.append(TEXT_777);
    } else if (!genFeature.isVolatile()) {
    if (genFeature.isListType()) {
    if (genModel.isVirtualDelegation()) {
    stringBuffer.append(TEXT_778);
    stringBuffer.append(genFeature.getImportedType());
    stringBuffer.append(TEXT_779);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_780);
    stringBuffer.append(genFeature.getImportedType());
    stringBuffer.append(TEXT_781);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(TEXT_782);
    }
    stringBuffer.append(TEXT_783);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_784);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.InternalEList"));
    stringBuffer.append(TEXT_785);
    stringBuffer.append(singleWildcard);
    stringBuffer.append(TEXT_786);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_787);
    } else if (genFeature.isBidirectional() || genFeature.isEffectiveContains()) {
    if (genModel.isVirtualDelegation()) {
    stringBuffer.append(TEXT_788);
    stringBuffer.append(genFeature.getImportedType());
    stringBuffer.append(TEXT_789);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_790);
    stringBuffer.append(genFeature.getImportedType());
    stringBuffer.append(TEXT_791);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(TEXT_792);
    }
    stringBuffer.append(TEXT_793);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_794);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.notify.NotificationChain"));
    stringBuffer.append(TEXT_795);
    if (!genFeature.isBidirectional()) {
    stringBuffer.append(TEXT_796);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.InternalEObject"));
    stringBuffer.append(TEXT_797);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_798);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(TEXT_799);
    } else { GenFeature reverseFeature = genFeature.getReverse(); GenClass targetClass = reverseFeature.getGenClass();
    stringBuffer.append(TEXT_800);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.InternalEObject"));
    stringBuffer.append(TEXT_801);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_802);
    stringBuffer.append(targetClass.getQualifiedFeatureID(reverseFeature));
    stringBuffer.append(TEXT_803);
    stringBuffer.append(targetClass.getImportedInterfaceName());
    stringBuffer.append(TEXT_804);
    }
    stringBuffer.append(TEXT_805);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_806);
    if (genModel.isVirtualDelegation()) {
    stringBuffer.append(TEXT_807);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_808);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(TEXT_809);
    } else if (genClass.isESetFlag(genFeature)) {
    if (!genModel.isSuppressNotification()) {
    stringBuffer.append(TEXT_810);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_811);
    stringBuffer.append(genClass.getESetFlagsField(genFeature));
    stringBuffer.append(TEXT_812);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_813);
    }
    stringBuffer.append(TEXT_814);
    stringBuffer.append(genClass.getESetFlagsField(genFeature));
    stringBuffer.append(TEXT_815);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_816);
    } else {
    if (!genModel.isSuppressNotification()) {
    stringBuffer.append(TEXT_817);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_818);
    stringBuffer.append(genFeature.getUncapName());
    stringBuffer.append(TEXT_819);
    }
    stringBuffer.append(TEXT_820);
    stringBuffer.append(genFeature.getUncapName());
    stringBuffer.append(TEXT_821);
    }
    if (!genModel.isSuppressNotification()) {
    stringBuffer.append(TEXT_822);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.impl.ENotificationImpl"));
    stringBuffer.append(TEXT_823);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.notify.Notification"));
    stringBuffer.append(TEXT_824);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(TEXT_825);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_826);
    }
    stringBuffer.append(TEXT_827);
    } else {
    if (genClass.isFlag(genFeature)) {
    if (!genModel.isSuppressNotification()) {
    stringBuffer.append(TEXT_828);
    stringBuffer.append(genFeature.getImportedType());
    stringBuffer.append(TEXT_829);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_830);
    stringBuffer.append(genClass.getFlagsField(genFeature));
    stringBuffer.append(TEXT_831);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_832);
    }
    } else if (genModel.isVirtualDelegation() && !genFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_833);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_834);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(TEXT_835);
    } else {
    if (!genModel.isSuppressNotification()) {
    stringBuffer.append(TEXT_836);
    stringBuffer.append(genFeature.getImportedType());
    stringBuffer.append(TEXT_837);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_838);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_839);
    }
    }
    if (genModel.isVirtualDelegation() && !genFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_840);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_841);
    } else if (genClass.isESetFlag(genFeature)) {
    stringBuffer.append(TEXT_842);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_843);
    stringBuffer.append(genClass.getESetFlagsField(genFeature));
    stringBuffer.append(TEXT_844);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_845);
    } else {
    if (!genModel.isSuppressNotification()) {
    stringBuffer.append(TEXT_846);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_847);
    stringBuffer.append(genFeature.getUncapName());
    stringBuffer.append(TEXT_848);
    }
    }
    if (genFeature.isReferenceType()) {
    stringBuffer.append(TEXT_849);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_850);
    if (!genModel.isVirtualDelegation()) {
    if (genClass.isESetFlag(genFeature)) {
    stringBuffer.append(TEXT_851);
    stringBuffer.append(genClass.getESetFlagsField(genFeature));
    stringBuffer.append(TEXT_852);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_853);
    } else {
    stringBuffer.append(TEXT_854);
    stringBuffer.append(genFeature.getUncapName());
    stringBuffer.append(TEXT_855);
    }
    }
    if (!genModel.isSuppressNotification()) {
    stringBuffer.append(TEXT_856);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.impl.ENotificationImpl"));
    stringBuffer.append(TEXT_857);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.notify.Notification"));
    stringBuffer.append(TEXT_858);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(TEXT_859);
    if (genModel.isVirtualDelegation()) {
    stringBuffer.append(TEXT_860);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_861);
    } else {
    stringBuffer.append(TEXT_862);
    stringBuffer.append(genFeature.getCapName());
    }
    stringBuffer.append(TEXT_863);
    if (genModel.isVirtualDelegation()) {
    stringBuffer.append(TEXT_864);
    } else {
    stringBuffer.append(TEXT_865);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_866);
    }
    stringBuffer.append(TEXT_867);
    }
    } else {
    if (genClass.isFlag(genFeature)) {
    stringBuffer.append(TEXT_868);
    stringBuffer.append(genFeature.getEDefault());
    stringBuffer.append(TEXT_869);
    stringBuffer.append(genClass.getFlagsField(genFeature));
    stringBuffer.append(TEXT_870);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_871);
    stringBuffer.append(genClass.getFlagsField(genFeature));
    stringBuffer.append(TEXT_872);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_873);
    } else if (!genModel.isVirtualDelegation() || genFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_874);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_875);
    stringBuffer.append(genFeature.getEDefault());
    stringBuffer.append(TEXT_876);
    }
    if (!genModel.isVirtualDelegation() || genFeature.isPrimitiveType()) {
    if (genClass.isESetFlag(genFeature)) {
    stringBuffer.append(TEXT_877);
    stringBuffer.append(genClass.getESetFlagsField(genFeature));
    stringBuffer.append(TEXT_878);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_879);
    } else {
    stringBuffer.append(TEXT_880);
    stringBuffer.append(genFeature.getUncapName());
    stringBuffer.append(TEXT_881);
    }
    }
    if (!genModel.isSuppressNotification()) {
    stringBuffer.append(TEXT_882);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.impl.ENotificationImpl"));
    stringBuffer.append(TEXT_883);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.notify.Notification"));
    stringBuffer.append(TEXT_884);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(TEXT_885);
    if (genModel.isVirtualDelegation() && !genFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_886);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_887);
    stringBuffer.append(genFeature.getEDefault());
    } else {
    stringBuffer.append(TEXT_888);
    stringBuffer.append(genFeature.getCapName());
    }
    stringBuffer.append(TEXT_889);
    stringBuffer.append(genFeature.getEDefault());
    stringBuffer.append(TEXT_890);
    if (genModel.isVirtualDelegation() && !genFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_891);
    } else {
    stringBuffer.append(TEXT_892);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_893);
    }
    stringBuffer.append(TEXT_894);
    }
    }
    }
    } else if (genFeature.hasDelegateFeature()) { GenFeature delegateFeature = genFeature.getDelegateFeature();
    if (delegateFeature.isWrappedFeatureMapType()) {
    stringBuffer.append(TEXT_895);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.FeatureMap"));
    stringBuffer.append(TEXT_896);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.FeatureMap"));
    stringBuffer.append(TEXT_897);
    stringBuffer.append(delegateFeature.getAccessorName());
    stringBuffer.append(TEXT_898);
    stringBuffer.append(genFeature.getQualifiedFeatureAccessor());
    stringBuffer.append(TEXT_899);
    } else {
    stringBuffer.append(TEXT_900);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.FeatureMap"));
    stringBuffer.append(TEXT_901);
    stringBuffer.append(delegateFeature.getAccessorName());
    stringBuffer.append(TEXT_902);
    stringBuffer.append(genFeature.getQualifiedFeatureAccessor());
    stringBuffer.append(TEXT_903);
    }
    } else {
    stringBuffer.append(TEXT_904);
    stringBuffer.append(genFeature.getFormattedName());
    stringBuffer.append(TEXT_905);
    stringBuffer.append(genFeature.getFeatureKind());
    stringBuffer.append(TEXT_906);
    //Class/unsetGenFeature.todo.override.javajetinc
    }
    stringBuffer.append(TEXT_907);
    }
    //Class/unsetGenFeature.override.javajetinc
    }
    if (genFeature.isIsSet() && (isImplementation || !genFeature.isSuppressedIsSetVisibility())) {
    if (isInterface) {
    stringBuffer.append(TEXT_908);
    stringBuffer.append(genClass.getQualifiedInterfaceName());
    stringBuffer.append(TEXT_909);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_910);
    stringBuffer.append(genFeature.getFormattedName());
    stringBuffer.append(TEXT_911);
    stringBuffer.append(genFeature.getFeatureKind());
    stringBuffer.append(TEXT_912);
    stringBuffer.append(TEXT_913);
    stringBuffer.append(genFeature.getFormattedName());
    stringBuffer.append(TEXT_914);
    stringBuffer.append(genFeature.getFeatureKind());
    stringBuffer.append(TEXT_915);
    if (genFeature.isChangeable() && !genFeature.isSuppressedUnsetVisibility()) {
    stringBuffer.append(TEXT_916);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_917);
    }
    stringBuffer.append(TEXT_918);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_919);
    if (!genFeature.isListType() && genFeature.isChangeable() && !genFeature.isSuppressedSetVisibility()) {
    stringBuffer.append(TEXT_920);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_921);
    stringBuffer.append(genFeature.getRawImportedBoundType());
    stringBuffer.append(TEXT_922);
    }
    stringBuffer.append(TEXT_923);
    //Class/isSetGenFeature.javadoc.override.javajetinc
    } else {
    stringBuffer.append(TEXT_924);
    if (genModel.getComplianceLevel().getValue() >= GenJDKLevel.JDK50) { //Class/isSetGenFeature.annotations.insert.javajetinc
    }
    }
    if (!isImplementation) {
    stringBuffer.append(TEXT_925);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_926);
    } else {
    stringBuffer.append(TEXT_927);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_928);
    if (genModel.isReflectiveDelegation()) {
    stringBuffer.append(TEXT_929);
    stringBuffer.append(genFeature.getQualifiedFeatureAccessor());
    stringBuffer.append(TEXT_930);
    } else if (!genFeature.isVolatile()) {
    if (genFeature.isListType()) {
    if (genModel.isVirtualDelegation()) {
    stringBuffer.append(TEXT_931);
    stringBuffer.append(genFeature.getImportedType());
    stringBuffer.append(TEXT_932);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_933);
    stringBuffer.append(genFeature.getImportedType());
    stringBuffer.append(TEXT_934);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(TEXT_935);
    }
    stringBuffer.append(TEXT_936);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_937);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.InternalEList"));
    stringBuffer.append(TEXT_938);
    stringBuffer.append(singleWildcard);
    stringBuffer.append(TEXT_939);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_940);
    } else {
    if (genModel.isVirtualDelegation() && !genFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_941);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(TEXT_942);
    } else if (genClass.isESetFlag(genFeature)) {
    stringBuffer.append(TEXT_943);
    stringBuffer.append(genClass.getESetFlagsField(genFeature));
    stringBuffer.append(TEXT_944);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_945);
    } else {
    stringBuffer.append(TEXT_946);
    stringBuffer.append(genFeature.getUncapName());
    stringBuffer.append(TEXT_947);
    }
    }
    } else if (genFeature.hasDelegateFeature()) { GenFeature delegateFeature = genFeature.getDelegateFeature();
    if (delegateFeature.isWrappedFeatureMapType()) {
    stringBuffer.append(TEXT_948);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.FeatureMap"));
    stringBuffer.append(TEXT_949);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.FeatureMap"));
    stringBuffer.append(TEXT_950);
    stringBuffer.append(delegateFeature.getAccessorName());
    stringBuffer.append(TEXT_951);
    stringBuffer.append(genFeature.getQualifiedFeatureAccessor());
    stringBuffer.append(TEXT_952);
    } else {
    stringBuffer.append(TEXT_953);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.FeatureMap"));
    stringBuffer.append(TEXT_954);
    stringBuffer.append(delegateFeature.getAccessorName());
    stringBuffer.append(TEXT_955);
    stringBuffer.append(genFeature.getQualifiedFeatureAccessor());
    stringBuffer.append(TEXT_956);
    }
    } else {
    stringBuffer.append(TEXT_957);
    stringBuffer.append(genFeature.getFormattedName());
    stringBuffer.append(TEXT_958);
    stringBuffer.append(genFeature.getFeatureKind());
    stringBuffer.append(TEXT_959);
    //Class/isSetGenFeature.todo.override.javajetinc
    }
    stringBuffer.append(TEXT_960);
    }
    //Class/isSetGenFeature.override.javajetinc
    }
    //Class/genFeature.override.javajetinc
    }//for
    }}.run();
    for (GenOperation genOperation : (isImplementation ? genClass.getImplementedGenOperations() : genClass.getDeclaredGenOperations())) {
    if (isInterface) {
    stringBuffer.append(TEXT_961);
    stringBuffer.append(TEXT_962);
    if (genOperation.hasDocumentation() || genOperation.hasParameterDocumentation()) {
    stringBuffer.append(TEXT_963);
    if (genOperation.hasDocumentation()) {
    stringBuffer.append(TEXT_964);
    stringBuffer.append(genOperation.getDocumentation(genModel.getIndentation(stringBuffer)));
    }
    for (GenParameter genParameter : genOperation.getGenParameters()) {
    if (genParameter.hasDocumentation()) { String documentation = genParameter.getDocumentation("");
    if (documentation.contains("\n") || documentation.contains("\r")) {
    stringBuffer.append(TEXT_965);
    stringBuffer.append(genParameter.getName());
    stringBuffer.append(TEXT_966);
    stringBuffer.append(genParameter.getDocumentation(genModel.getIndentation(stringBuffer)));
    } else {
    stringBuffer.append(TEXT_967);
    stringBuffer.append(genParameter.getName());
    stringBuffer.append(TEXT_968);
    stringBuffer.append(genParameter.getDocumentation(genModel.getIndentation(stringBuffer)));
    }
    }
    }
    stringBuffer.append(TEXT_969);
    }
    if (!genModel.isSuppressEMFModelTags()) { boolean first = true; for (StringTokenizer stringTokenizer = new StringTokenizer(genOperation.getModelInfo(), "\n\r"); stringTokenizer.hasMoreTokens(); ) { String modelInfo = stringTokenizer.nextToken(); if (first) { first = false;
    stringBuffer.append(TEXT_970);
    stringBuffer.append(modelInfo);
    } else {
    stringBuffer.append(TEXT_971);
    stringBuffer.append(modelInfo);
    }} if (first) {
    stringBuffer.append(TEXT_972);
    }}
    stringBuffer.append(TEXT_973);
    //Class/genOperation.javadoc.override.javajetinc
    } else {
    stringBuffer.append(TEXT_974);
    if (genModel.getComplianceLevel().getValue() >= GenJDKLevel.JDK50) { //Class/genOperation.annotations.insert.javajetinc
    }
    }
    if (!isImplementation) {
    stringBuffer.append(TEXT_975);
    stringBuffer.append(genOperation.getTypeParameters());
    stringBuffer.append(genOperation.getImportedType());
    stringBuffer.append(TEXT_976);
    stringBuffer.append(genOperation.getName());
    stringBuffer.append(TEXT_977);
    stringBuffer.append(genOperation.getParameters());
    stringBuffer.append(TEXT_978);
    stringBuffer.append(genOperation.getThrows());
    stringBuffer.append(TEXT_979);
    } else {
    stringBuffer.append(TEXT_980);
    stringBuffer.append(genOperation.getTypeParameters());
    stringBuffer.append(genOperation.getImportedType());
    stringBuffer.append(TEXT_981);
    stringBuffer.append(genOperation.getName());
    stringBuffer.append(TEXT_982);
    stringBuffer.append(genOperation.getParameters());
    stringBuffer.append(TEXT_983);
    stringBuffer.append(genOperation.getThrows());
    stringBuffer.append(TEXT_984);
    if (genOperation.hasBody()) {
    stringBuffer.append(TEXT_985);
    stringBuffer.append(genOperation.getBody(genModel.getIndentation(stringBuffer)));
    } else if (genOperation.isInvariant()) {GenClass opClass = genOperation.getGenClass(); String diagnostics = genOperation.getGenParameters().get(0).getName(); String context = genOperation.getGenParameters().get(1).getName();
    stringBuffer.append(TEXT_986);
    stringBuffer.append(diagnostics);
    stringBuffer.append(TEXT_987);
    stringBuffer.append(diagnostics);
    stringBuffer.append(TEXT_988);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.util.BasicDiagnostic"));
    stringBuffer.append(TEXT_989);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.util.Diagnostic"));
    stringBuffer.append(TEXT_990);
    stringBuffer.append(opClass.getGenPackage().getImportedValidatorClassName());
    stringBuffer.append(TEXT_991);
    stringBuffer.append(opClass.getGenPackage().getImportedValidatorClassName());
    stringBuffer.append(TEXT_992);
    stringBuffer.append(opClass.getOperationID(genOperation));
    stringBuffer.append(TEXT_993);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.plugin.EcorePlugin"));
    stringBuffer.append(TEXT_994);
    stringBuffer.append(genOperation.getName());
    stringBuffer.append(TEXT_995);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.EObjectValidator"));
    stringBuffer.append(TEXT_996);
    stringBuffer.append(context);
    stringBuffer.append(TEXT_997);
    stringBuffer.append(genModel.getNonNLS());
    stringBuffer.append(genModel.getNonNLS(2));
    stringBuffer.append(TEXT_998);
    } else {
    stringBuffer.append(TEXT_999);
    //Class/implementedGenOperation.todo.override.javajetinc
    }
    stringBuffer.append(TEXT_1000);
    }
    //Class/implementedGenOperation.override.javajetinc
    }//for
    if (isImplementation && !genModel.isReflectiveDelegation() && genClass.implementsAny(genClass.getEInverseAddGenFeatures())) {
    stringBuffer.append(TEXT_1001);
    if (genModel.useGenerics()) {
    for (GenFeature genFeature : genClass.getEInverseAddGenFeatures()) {
    if (genFeature.isUncheckedCast()) {
    stringBuffer.append(TEXT_1002);
    break; }
    }
    }
    if (genModel.useClassOverrideAnnotation()) {
    stringBuffer.append(TEXT_1003);
    }
    stringBuffer.append(TEXT_1004);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.notify.NotificationChain"));
    stringBuffer.append(TEXT_1005);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.InternalEObject"));
    stringBuffer.append(TEXT_1006);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.notify.NotificationChain"));
    stringBuffer.append(TEXT_1007);
    for (GenFeature genFeature : genClass.getEInverseAddGenFeatures()) {
    stringBuffer.append(TEXT_1008);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(TEXT_1009);
    if (genFeature.isListType()) { String cast = "("  + genModel.getImportedName("org.eclipse.emf.ecore.util.InternalEList") + (!genModel.useGenerics() ? ")" : "<" + genModel.getImportedName("org.eclipse.emf.ecore.InternalEObject") + ">)(" + genModel.getImportedName("org.eclipse.emf.ecore.util.InternalEList") + "<?>)");
    if (genFeature.isMapType() && genFeature.isEffectiveSuppressEMFTypes()) {
    stringBuffer.append(TEXT_1010);
    stringBuffer.append(cast);
    stringBuffer.append(TEXT_1011);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.util.EMap"));
    stringBuffer.append(TEXT_1012);
    stringBuffer.append(genFeature.getImportedMapTemplateArguments());
    stringBuffer.append(TEXT_1013);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_1014);
    } else {
    stringBuffer.append(TEXT_1015);
    stringBuffer.append(cast);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_1016);
    }
    } else if (genFeature.isContainer()) {
    stringBuffer.append(TEXT_1017);
    if (genFeature.isBasicSet()) {
    stringBuffer.append(TEXT_1018);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_1019);
    stringBuffer.append(genFeature.getImportedType());
    stringBuffer.append(TEXT_1020);
    } else {
    stringBuffer.append(TEXT_1021);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(TEXT_1022);
    }
    } else {
    if (genClass.getImplementingGenModel(genFeature).isVirtualDelegation()) {
    stringBuffer.append(TEXT_1023);
    stringBuffer.append(genFeature.getImportedType());
    stringBuffer.append(TEXT_1024);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_1025);
    stringBuffer.append(genFeature.getImportedType());
    stringBuffer.append(TEXT_1026);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(TEXT_1027);
    }
    stringBuffer.append(TEXT_1028);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_1029);
    if (genFeature.isEffectiveContains()) {
    stringBuffer.append(TEXT_1030);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.InternalEObject"));
    stringBuffer.append(TEXT_1031);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_1032);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(TEXT_1033);
    } else { GenFeature reverseFeature = genFeature.getReverse(); GenClass targetClass = reverseFeature.getGenClass();
    stringBuffer.append(TEXT_1034);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.InternalEObject"));
    stringBuffer.append(TEXT_1035);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_1036);
    stringBuffer.append(targetClass.getQualifiedFeatureID(reverseFeature));
    stringBuffer.append(TEXT_1037);
    stringBuffer.append(targetClass.getImportedInterfaceName());
    stringBuffer.append(TEXT_1038);
    }
    stringBuffer.append(TEXT_1039);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_1040);
    stringBuffer.append(genFeature.getImportedType());
    stringBuffer.append(TEXT_1041);
    }
    }
    stringBuffer.append(TEXT_1042);
    if (genModel.isMinimalReflectiveMethods()) {
    stringBuffer.append(TEXT_1043);
    } else {
    stringBuffer.append(TEXT_1044);
    }
    stringBuffer.append(TEXT_1045);
    }
    if (isImplementation && !genModel.isReflectiveDelegation() && genClass.implementsAny(genClass.getEInverseRemoveGenFeatures())) {
    stringBuffer.append(TEXT_1046);
    if (genModel.useClassOverrideAnnotation()) {
    stringBuffer.append(TEXT_1047);
    }
    stringBuffer.append(TEXT_1048);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.notify.NotificationChain"));
    stringBuffer.append(TEXT_1049);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.InternalEObject"));
    stringBuffer.append(TEXT_1050);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.notify.NotificationChain"));
    stringBuffer.append(TEXT_1051);
    for (GenFeature genFeature : genClass.getEInverseRemoveGenFeatures()) {
    stringBuffer.append(TEXT_1052);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(TEXT_1053);
    if (genFeature.isListType()) {
    if (genFeature.isMapType() && genFeature.isEffectiveSuppressEMFTypes()) {
    stringBuffer.append(TEXT_1054);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.InternalEList"));
    stringBuffer.append(singleWildcard);
    stringBuffer.append(TEXT_1055);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.util.EMap"));
    stringBuffer.append(TEXT_1056);
    stringBuffer.append(genFeature.getImportedMapTemplateArguments());
    stringBuffer.append(TEXT_1057);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_1058);
    } else if (genFeature.isWrappedFeatureMapType()) {
    stringBuffer.append(TEXT_1059);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.InternalEList"));
    stringBuffer.append(singleWildcard);
    stringBuffer.append(TEXT_1060);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.FeatureMap"));
    stringBuffer.append(TEXT_1061);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_1062);
    } else {
    stringBuffer.append(TEXT_1063);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.InternalEList"));
    stringBuffer.append(singleWildcard);
    stringBuffer.append(TEXT_1064);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_1065);
    }
    } else if (genFeature.isContainer() && !genFeature.isBasicSet()) {
    stringBuffer.append(TEXT_1066);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(TEXT_1067);
    } else if (genFeature.isUnsettable()) {
    stringBuffer.append(TEXT_1068);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_1069);
    } else {
    stringBuffer.append(TEXT_1070);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_1071);
    }
    }
    stringBuffer.append(TEXT_1072);
    if (genModel.isMinimalReflectiveMethods()) {
    stringBuffer.append(TEXT_1073);
    } else {
    stringBuffer.append(TEXT_1074);
    }
    stringBuffer.append(TEXT_1075);
    }
    if (isImplementation && !genModel.isReflectiveDelegation() && genClass.implementsAny(genClass.getEBasicRemoveFromContainerGenFeatures())) {
    stringBuffer.append(TEXT_1076);
    if (genModel.useClassOverrideAnnotation()) {
    stringBuffer.append(TEXT_1077);
    }
    stringBuffer.append(TEXT_1078);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.notify.NotificationChain"));
    stringBuffer.append(TEXT_1079);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.notify.NotificationChain"));
    stringBuffer.append(TEXT_1080);
    for (GenFeature genFeature : genClass.getEBasicRemoveFromContainerGenFeatures()) {
    GenFeature reverseFeature = genFeature.getReverse(); GenClass targetClass = reverseFeature.getGenClass();
    stringBuffer.append(TEXT_1081);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(TEXT_1082);
    stringBuffer.append(targetClass.getQualifiedFeatureID(reverseFeature));
    stringBuffer.append(TEXT_1083);
    stringBuffer.append(targetClass.getImportedInterfaceName());
    stringBuffer.append(TEXT_1084);
    }
    stringBuffer.append(TEXT_1085);
    if (genModel.isMinimalReflectiveMethods()) {
    stringBuffer.append(TEXT_1086);
    } else {
    stringBuffer.append(TEXT_1087);
    }
    stringBuffer.append(TEXT_1088);
    }
    if (isImplementation && !genModel.isReflectiveDelegation() && genClass.implementsAny(genClass.getEGetGenFeatures())) {
    stringBuffer.append(TEXT_1089);
    if (genModel.useClassOverrideAnnotation()) {
    stringBuffer.append(TEXT_1090);
    }
    stringBuffer.append(TEXT_1091);
    for (GenFeature genFeature : genClass.getEGetGenFeatures()) {
    stringBuffer.append(TEXT_1092);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(TEXT_1093);
    if (genFeature.isPrimitiveType()) {
    if (genFeature.isBooleanType()) {
    stringBuffer.append(TEXT_1094);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_1095);
    } else {
    stringBuffer.append(TEXT_1096);
    stringBuffer.append(genFeature.getObjectType());
    stringBuffer.append(TEXT_1097);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_1098);
    }
    } else if (genFeature.isResolveProxies() && !genFeature.isListType()) {
    stringBuffer.append(TEXT_1099);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_1100);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_1101);
    } else if (genFeature.isMapType()) {
    if (genFeature.isEffectiveSuppressEMFTypes()) {
    stringBuffer.append(TEXT_1102);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.util.EMap"));
    stringBuffer.append(TEXT_1103);
    stringBuffer.append(genFeature.getImportedMapTemplateArguments());
    stringBuffer.append(TEXT_1104);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_1105);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_1106);
    } else {
    stringBuffer.append(TEXT_1107);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_1108);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_1109);
    }
    } else if (genFeature.isWrappedFeatureMapType()) {
    stringBuffer.append(TEXT_1110);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.FeatureMap"));
    stringBuffer.append(TEXT_1111);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_1112);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_1113);
    } else if (genFeature.isFeatureMapType()) {
    stringBuffer.append(TEXT_1114);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_1115);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.FeatureMap"));
    stringBuffer.append(TEXT_1116);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_1117);
    } else {
    stringBuffer.append(TEXT_1118);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_1119);
    }
    }
    stringBuffer.append(TEXT_1120);
    if (genModel.isMinimalReflectiveMethods()) {
    stringBuffer.append(TEXT_1121);
    } else {
    stringBuffer.append(TEXT_1122);
    }
    stringBuffer.append(TEXT_1123);
    }
    if (isImplementation && !genModel.isReflectiveDelegation() && genClass.implementsAny(genClass.getESetGenFeatures())) {
    stringBuffer.append(TEXT_1124);
    if (genModel.useGenerics()) {
    for (GenFeature genFeature : genClass.getESetGenFeatures()) {
    if (genFeature.isUncheckedCast() && !genFeature.isFeatureMapType() && !genFeature.isMapType()) {
    stringBuffer.append(TEXT_1125);
    break; }
    }
    }
    if (genModel.useClassOverrideAnnotation()) {
    stringBuffer.append(TEXT_1126);
    }
    stringBuffer.append(TEXT_1127);
    for (GenFeature genFeature : genClass.getESetGenFeatures()) {
    stringBuffer.append(TEXT_1128);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(TEXT_1129);
    if (genFeature.isListType()) {
    if (genFeature.isWrappedFeatureMapType()) {
    stringBuffer.append(TEXT_1130);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.FeatureMap"));
    stringBuffer.append(TEXT_1131);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.FeatureMap"));
    stringBuffer.append(TEXT_1132);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_1133);
    } else if (genFeature.isFeatureMapType()) {
    stringBuffer.append(TEXT_1134);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.FeatureMap"));
    stringBuffer.append(TEXT_1135);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_1136);
    } else if (genFeature.isMapType()) {
    if (genFeature.isEffectiveSuppressEMFTypes()) {
    stringBuffer.append(TEXT_1137);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.EStructuralFeature"));
    stringBuffer.append(TEXT_1138);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.util.EMap"));
    stringBuffer.append(TEXT_1139);
    stringBuffer.append(genFeature.getImportedMapTemplateArguments());
    stringBuffer.append(TEXT_1140);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_1141);
    } else {
    stringBuffer.append(TEXT_1142);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.EStructuralFeature"));
    stringBuffer.append(TEXT_1143);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_1144);
    }
    } else {
    stringBuffer.append(TEXT_1145);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_1146);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_1147);
    stringBuffer.append(genModel.getImportedName("java.util.Collection"));
    if (genModel.getComplianceLevel().getValue() >= GenJDKLevel.JDK50) {
    stringBuffer.append(TEXT_1148);
    stringBuffer.append(genFeature.getListItemType());
    stringBuffer.append(TEXT_1149);
    }
    stringBuffer.append(TEXT_1150);
    }
    } else if (genFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_1151);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_1152);
    stringBuffer.append(genFeature.getObjectType());
    stringBuffer.append(TEXT_1153);
    stringBuffer.append(genFeature.getPrimitiveValueFunction());
    stringBuffer.append(TEXT_1154);
    } else {
    stringBuffer.append(TEXT_1155);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_1156);
    if (genFeature.getTypeGenDataType() == null || !genFeature.getTypeGenDataType().isObjectType() || !genFeature.getRawType().equals(genFeature.getType())) {
    stringBuffer.append(TEXT_1157);
    stringBuffer.append(genFeature.getImportedType());
    stringBuffer.append(TEXT_1158);
    }
    stringBuffer.append(TEXT_1159);
    }
    stringBuffer.append(TEXT_1160);
    }
    stringBuffer.append(TEXT_1161);
    if (genModel.isMinimalReflectiveMethods()) {
    stringBuffer.append(TEXT_1162);
    } else {
    stringBuffer.append(TEXT_1163);
    }
    stringBuffer.append(TEXT_1164);
    }
    if (isImplementation && !genModel.isReflectiveDelegation() && genClass.implementsAny(genClass.getEUnsetGenFeatures())) {
    stringBuffer.append(TEXT_1165);
    if (genModel.useClassOverrideAnnotation()) {
    stringBuffer.append(TEXT_1166);
    }
    stringBuffer.append(TEXT_1167);
    for (GenFeature genFeature : genClass.getEUnsetGenFeatures()) {
    stringBuffer.append(TEXT_1168);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(TEXT_1169);
    if (genFeature.isListType() && !genFeature.isUnsettable()) {
    if (genFeature.isWrappedFeatureMapType()) {
    stringBuffer.append(TEXT_1170);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.FeatureMap"));
    stringBuffer.append(TEXT_1171);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_1172);
    } else {
    stringBuffer.append(TEXT_1173);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_1174);
    }
    } else if (genFeature.isUnsettable()) {
    stringBuffer.append(TEXT_1175);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_1176);
    } else if (!genFeature.hasEDefault()) {
    stringBuffer.append(TEXT_1177);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_1178);
    stringBuffer.append(genFeature.getImportedType());
    stringBuffer.append(TEXT_1179);
    } else {
    stringBuffer.append(TEXT_1180);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_1181);
    stringBuffer.append(genFeature.getEDefault());
    stringBuffer.append(TEXT_1182);
    }
    stringBuffer.append(TEXT_1183);
    }
    stringBuffer.append(TEXT_1184);
    if (genModel.isMinimalReflectiveMethods()) {
    stringBuffer.append(TEXT_1185);
    } else {
    stringBuffer.append(TEXT_1186);
    }
    stringBuffer.append(TEXT_1187);
    //Class/eUnset.override.javajetinc
    }
    if (isImplementation && !genModel.isReflectiveDelegation() && genClass.implementsAny(genClass.getEIsSetGenFeatures())) {
    stringBuffer.append(TEXT_1188);
    if (genModel.useClassOverrideAnnotation()) {
    stringBuffer.append(TEXT_1189);
    }
    stringBuffer.append(TEXT_1190);
    for (GenFeature genFeature : genClass.getEIsSetGenFeatures()) {
    stringBuffer.append(TEXT_1191);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(TEXT_1192);
    if (genFeature.isListType() && !genFeature.isUnsettable()) {
    if (genFeature.isWrappedFeatureMapType()) {
    if (genFeature.isVolatile()) {
    stringBuffer.append(TEXT_1193);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.FeatureMap"));
    stringBuffer.append(TEXT_1194);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_1195);
    } else {
    stringBuffer.append(TEXT_1196);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_1197);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_1198);
    }
    } else {
    if (genClass.isField(genFeature)) {
    stringBuffer.append(TEXT_1199);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_1200);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_1201);
    } else {
    if (genFeature.isField() && genClass.getImplementingGenModel(genFeature).isVirtualDelegation()) {
    stringBuffer.append(TEXT_1202);
    stringBuffer.append(genFeature.getImportedType());
    stringBuffer.append(TEXT_1203);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_1204);
    stringBuffer.append(genFeature.getImportedType());
    stringBuffer.append(TEXT_1205);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(TEXT_1206);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_1207);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_1208);
    } else {
    stringBuffer.append(TEXT_1209);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_1210);
    }
    }
    }
    } else if (genFeature.isUnsettable()) {
    stringBuffer.append(TEXT_1211);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_1212);
    } else if (genFeature.isResolveProxies()) {
    if (genClass.isField(genFeature)) {
    stringBuffer.append(TEXT_1213);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_1214);
    } else {
    if (genFeature.isField() && genClass.getImplementingGenModel(genFeature).isVirtualDelegation()) {
    stringBuffer.append(TEXT_1215);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(TEXT_1216);
    } else {
    stringBuffer.append(TEXT_1217);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_1218);
    }
    }
    } else if (!genFeature.hasEDefault()) {
    if (genClass.isField(genFeature)) {
    stringBuffer.append(TEXT_1219);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_1220);
    } else {
    if (genFeature.isField() && genClass.getImplementingGenModel(genFeature).isVirtualDelegation()) {
    stringBuffer.append(TEXT_1221);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(TEXT_1222);
    } else {
    stringBuffer.append(TEXT_1223);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_1224);
    }
    }
    } else if (genFeature.isPrimitiveType() || genFeature.isEnumType()) {
    if (genClass.isField(genFeature)) {
    if (genClass.isFlag(genFeature)) {
    stringBuffer.append(TEXT_1225);
    stringBuffer.append(genClass.getFlagsField(genFeature));
    stringBuffer.append(TEXT_1226);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_1227);
    stringBuffer.append(genFeature.getEDefault());
    stringBuffer.append(TEXT_1228);
    } else {
    stringBuffer.append(TEXT_1229);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_1230);
    stringBuffer.append(genFeature.getEDefault());
    stringBuffer.append(TEXT_1231);
    }
    } else {
    if (genFeature.isEnumType() && genFeature.isField() && genClass.getImplementingGenModel(genFeature).isVirtualDelegation()) {
    stringBuffer.append(TEXT_1232);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(TEXT_1233);
    stringBuffer.append(genFeature.getEDefault());
    stringBuffer.append(TEXT_1234);
    stringBuffer.append(genFeature.getEDefault());
    stringBuffer.append(TEXT_1235);
    } else {
    stringBuffer.append(TEXT_1236);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_1237);
    stringBuffer.append(genFeature.getEDefault());
    stringBuffer.append(TEXT_1238);
    }
    }
    } else {//datatype
    if (genClass.isField(genFeature)) {
    stringBuffer.append(TEXT_1239);
    stringBuffer.append(genFeature.getEDefault());
    stringBuffer.append(TEXT_1240);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_1241);
    stringBuffer.append(genFeature.getEDefault());
    stringBuffer.append(TEXT_1242);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_1243);
    } else {
    if (genFeature.isField() && genClass.getImplementingGenModel(genFeature).isVirtualDelegation()) {
    stringBuffer.append(TEXT_1244);
    stringBuffer.append(genFeature.getImportedType());
    stringBuffer.append(TEXT_1245);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_1246);
    stringBuffer.append(genFeature.getImportedType());
    stringBuffer.append(TEXT_1247);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(TEXT_1248);
    stringBuffer.append(genFeature.getEDefault());
    stringBuffer.append(TEXT_1249);
    stringBuffer.append(genFeature.getEDefault());
    stringBuffer.append(TEXT_1250);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_1251);
    stringBuffer.append(genFeature.getEDefault());
    stringBuffer.append(TEXT_1252);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_1253);
    } else {
    stringBuffer.append(TEXT_1254);
    stringBuffer.append(genFeature.getEDefault());
    stringBuffer.append(TEXT_1255);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_1256);
    stringBuffer.append(genFeature.getEDefault());
    stringBuffer.append(TEXT_1257);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_1258);
    }
    }
    }
    }
    stringBuffer.append(TEXT_1259);
    if (genModel.isMinimalReflectiveMethods()) {
    stringBuffer.append(TEXT_1260);
    } else {
    stringBuffer.append(TEXT_1261);
    }
    stringBuffer.append(TEXT_1262);
    //Class/eIsSet.override.javajetinc
    }
    if (isImplementation && !genClass.getMixinGenFeatures().isEmpty()) {
    stringBuffer.append(TEXT_1263);
    if (genModel.useClassOverrideAnnotation()) {
    stringBuffer.append(TEXT_1264);
    }
    stringBuffer.append(TEXT_1265);
    stringBuffer.append(singleWildcard);
    stringBuffer.append(TEXT_1266);
    for (GenClass mixinGenClass : genClass.getMixinGenClasses()) {
    stringBuffer.append(TEXT_1267);
    stringBuffer.append(mixinGenClass.getImportedInterfaceName());
    stringBuffer.append(TEXT_1268);
    for (GenFeature genFeature : mixinGenClass.getGenFeatures()) {
    stringBuffer.append(TEXT_1269);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(TEXT_1270);
    stringBuffer.append(mixinGenClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(TEXT_1271);
    }
    stringBuffer.append(TEXT_1272);
    }
    stringBuffer.append(TEXT_1273);
    if (genModel.useClassOverrideAnnotation()) {
    stringBuffer.append(TEXT_1274);
    }
    stringBuffer.append(TEXT_1275);
    stringBuffer.append(singleWildcard);
    stringBuffer.append(TEXT_1276);
    for (GenClass mixinGenClass : genClass.getMixinGenClasses()) {
    stringBuffer.append(TEXT_1277);
    stringBuffer.append(mixinGenClass.getImportedInterfaceName());
    stringBuffer.append(TEXT_1278);
    for (GenFeature genFeature : mixinGenClass.getGenFeatures()) {
    stringBuffer.append(TEXT_1279);
    stringBuffer.append(mixinGenClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(TEXT_1280);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(TEXT_1281);
    }
    stringBuffer.append(TEXT_1282);
    }
    stringBuffer.append(TEXT_1283);
    }
    if (isImplementation && genModel.isVirtualDelegation()) { String eVirtualValuesField = genClass.getEVirtualValuesField();
    if (eVirtualValuesField != null) {
    stringBuffer.append(TEXT_1284);
    if (genModel.useClassOverrideAnnotation()) {
    stringBuffer.append(TEXT_1285);
    }
    stringBuffer.append(TEXT_1286);
    stringBuffer.append(eVirtualValuesField);
    stringBuffer.append(TEXT_1287);
    if (genModel.useClassOverrideAnnotation()) {
    stringBuffer.append(TEXT_1288);
    }
    stringBuffer.append(TEXT_1289);
    stringBuffer.append(eVirtualValuesField);
    stringBuffer.append(TEXT_1290);
    }
    { List<String> eVirtualIndexBitFields = genClass.getEVirtualIndexBitFields(new ArrayList<String>());
    if (!eVirtualIndexBitFields.isEmpty()) { List<String> allEVirtualIndexBitFields = genClass.getAllEVirtualIndexBitFields(new ArrayList<String>());
    stringBuffer.append(TEXT_1291);
    if (genModel.useClassOverrideAnnotation()) {
    stringBuffer.append(TEXT_1292);
    }
    stringBuffer.append(TEXT_1293);
    for (int i = 0; i < allEVirtualIndexBitFields.size(); i++) {
    stringBuffer.append(TEXT_1294);
    stringBuffer.append(i);
    stringBuffer.append(TEXT_1295);
    stringBuffer.append(allEVirtualIndexBitFields.get(i));
    stringBuffer.append(TEXT_1296);
    }
    stringBuffer.append(TEXT_1297);
    if (genModel.useClassOverrideAnnotation()) {
    stringBuffer.append(TEXT_1298);
    }
    stringBuffer.append(TEXT_1299);
    for (int i = 0; i < allEVirtualIndexBitFields.size(); i++) {
    stringBuffer.append(TEXT_1300);
    stringBuffer.append(i);
    stringBuffer.append(TEXT_1301);
    stringBuffer.append(allEVirtualIndexBitFields.get(i));
    stringBuffer.append(TEXT_1302);
    }
    stringBuffer.append(TEXT_1303);
    }
    }
    }
    if (isImplementation && !genModel.isReflectiveDelegation() && !genClass.getToStringGenFeatures().isEmpty()) {
    stringBuffer.append(TEXT_1304);
    if (genModel.useClassOverrideAnnotation()) {
    stringBuffer.append(TEXT_1305);
    }
    stringBuffer.append(TEXT_1306);
    { boolean first = true;
    for (GenFeature genFeature : genClass.getToStringGenFeatures()) {
    if (first) { first = false;
    stringBuffer.append(TEXT_1307);
    stringBuffer.append(genFeature.getName());
    stringBuffer.append(TEXT_1308);
    stringBuffer.append(genModel.getNonNLS());
    } else {
    stringBuffer.append(TEXT_1309);
    stringBuffer.append(genFeature.getName());
    stringBuffer.append(TEXT_1310);
    stringBuffer.append(genModel.getNonNLS());
    }
    if (genFeature.isUnsettable() && !genFeature.isListType()) {
    if (genModel.isVirtualDelegation() && !genFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_1311);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(TEXT_1312);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(TEXT_1313);
    stringBuffer.append(genModel.getNonNLS());
    } else {
    if (genClass.isFlag(genFeature)) {
    stringBuffer.append(TEXT_1314);
    if (genClass.isESetFlag(genFeature)) {
    stringBuffer.append(TEXT_1315);
    stringBuffer.append(genClass.getESetFlagsField(genFeature));
    stringBuffer.append(TEXT_1316);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_1317);
    } else {
    stringBuffer.append(genFeature.getUncapName());
    stringBuffer.append(TEXT_1318);
    }
    stringBuffer.append(TEXT_1319);
    stringBuffer.append(genClass.getFlagsField(genFeature));
    stringBuffer.append(TEXT_1320);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_1321);
    stringBuffer.append(genModel.getNonNLS());
    } else {
    stringBuffer.append(TEXT_1322);
    if (genClass.isESetFlag(genFeature)) {
    stringBuffer.append(TEXT_1323);
    stringBuffer.append(genClass.getESetFlagsField(genFeature));
    stringBuffer.append(TEXT_1324);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_1325);
    } else {
    stringBuffer.append(genFeature.getUncapName());
    stringBuffer.append(TEXT_1326);
    }
    stringBuffer.append(TEXT_1327);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_1328);
    stringBuffer.append(genModel.getNonNLS());
    }
    }
    } else {
    if (genModel.isVirtualDelegation() && !genFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_1329);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    if (!genFeature.isListType() && !genFeature.isReferenceType()){
    stringBuffer.append(TEXT_1330);
    stringBuffer.append(genFeature.getEDefault());
    }
    stringBuffer.append(TEXT_1331);
    } else {
    if (genClass.isFlag(genFeature)) {
    stringBuffer.append(TEXT_1332);
    stringBuffer.append(genClass.getFlagsField(genFeature));
    stringBuffer.append(TEXT_1333);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_1334);
    } else {
    stringBuffer.append(TEXT_1335);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_1336);
    }
    }
    }
    }
    }
    stringBuffer.append(TEXT_1337);
    }
    if (isImplementation && genClass.isMapEntry()) { GenFeature keyFeature = genClass.getMapEntryKeyFeature(); GenFeature valueFeature = genClass.getMapEntryValueFeature();
    String objectType = genModel.getImportedName("java.lang.Object");
    String keyType = genModel.getComplianceLevel().getValue() >= GenJDKLevel.JDK50 ? keyFeature.getObjectType() : objectType;
    String valueType = genModel.getComplianceLevel().getValue() >= GenJDKLevel.JDK50 ? valueFeature.getObjectType() : objectType;
    String eMapType = genModel.getImportedName("org.eclipse.emf.common.util.EMap") + (genModel.getComplianceLevel().getValue() >= GenJDKLevel.JDK50 ? "<" + keyType + ", " + valueType + ">" : "");
    stringBuffer.append(TEXT_1338);
    stringBuffer.append(objectType);
    stringBuffer.append(TEXT_1339);
    stringBuffer.append(keyType);
    stringBuffer.append(TEXT_1340);
    if (keyFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_1341);
    stringBuffer.append(keyFeature.getObjectType());
    stringBuffer.append(TEXT_1342);
    } else {
    stringBuffer.append(TEXT_1343);
    }
    stringBuffer.append(TEXT_1344);
    stringBuffer.append(keyType);
    stringBuffer.append(TEXT_1345);
    if (keyFeature.isListType()) {
    stringBuffer.append(TEXT_1346);
    if (!genModel.useGenerics()) {
    stringBuffer.append(TEXT_1347);
    stringBuffer.append(genModel.getImportedName("java.util.Collection"));
    stringBuffer.append(TEXT_1348);
    }
    stringBuffer.append(TEXT_1349);
    } else if (genModel.getComplianceLevel().getValue() >= GenJDKLevel.JDK50) {
    stringBuffer.append(TEXT_1350);
    } else if (keyFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_1351);
    stringBuffer.append(keyFeature.getObjectType());
    stringBuffer.append(TEXT_1352);
    stringBuffer.append(keyFeature.getPrimitiveValueFunction());
    stringBuffer.append(TEXT_1353);
    } else {
    stringBuffer.append(TEXT_1354);
    stringBuffer.append(keyFeature.getImportedType());
    stringBuffer.append(TEXT_1355);
    }
    stringBuffer.append(TEXT_1356);
    stringBuffer.append(valueType);
    stringBuffer.append(TEXT_1357);
    if (valueFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_1358);
    stringBuffer.append(valueFeature.getObjectType());
    stringBuffer.append(TEXT_1359);
    } else {
    stringBuffer.append(TEXT_1360);
    }
    stringBuffer.append(TEXT_1361);
    stringBuffer.append(valueType);
    stringBuffer.append(TEXT_1362);
    stringBuffer.append(valueType);
    stringBuffer.append(TEXT_1363);
    stringBuffer.append(valueType);
    stringBuffer.append(TEXT_1364);
    if (valueFeature.isListType()) {
    stringBuffer.append(TEXT_1365);
    if (!genModel.useGenerics()) {
    stringBuffer.append(TEXT_1366);
    stringBuffer.append(genModel.getImportedName("java.util.Collection"));
    stringBuffer.append(TEXT_1367);
    }
    stringBuffer.append(TEXT_1368);
    } else if (genModel.getComplianceLevel().getValue() >= GenJDKLevel.JDK50) {
    stringBuffer.append(TEXT_1369);
    } else if (valueFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_1370);
    stringBuffer.append(valueFeature.getObjectType());
    stringBuffer.append(TEXT_1371);
    stringBuffer.append(valueFeature.getPrimitiveValueFunction());
    stringBuffer.append(TEXT_1372);
    } else {
    stringBuffer.append(TEXT_1373);
    stringBuffer.append(valueFeature.getImportedType());
    stringBuffer.append(TEXT_1374);
    }
    stringBuffer.append(TEXT_1375);
    if (genModel.useGenerics()) {
    stringBuffer.append(TEXT_1376);
    }
    stringBuffer.append(TEXT_1377);
    stringBuffer.append(eMapType);
    stringBuffer.append(TEXT_1378);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.EObject"));
    stringBuffer.append(TEXT_1379);
    stringBuffer.append(eMapType);
    stringBuffer.append(TEXT_1380);
    }
    stringBuffer.append(TEXT_1381);
    stringBuffer.append(isInterface ? " " + genClass.getInterfaceName() : genClass.getClassName());
    // TODO fix the space above
    genModel.emitSortedImports();
    stringBuffer.append(TEXT_1382);
    return stringBuffer.toString();
  }
}
