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

  public final String NL = nl == null ? (System.getProperties().getProperty("line.separator")) : nl;
  protected final String TEXT_1 = "";
  protected final String TEXT_2 = "/**";
  protected final String TEXT_3 = NL + " * ";
  protected final String TEXT_4 = NL + " * <copyright>" + NL + " * </copyright>";
  protected final String TEXT_5 = NL + " *" + NL + " * ";
  protected final String TEXT_6 = "Id";
  protected final String TEXT_7 = NL + " */";
  protected final String TEXT_8 = NL + "package ";
  protected final String TEXT_9 = ";";
  protected final String TEXT_10 = NL + "package ";
  protected final String TEXT_11 = ";";
  protected final String TEXT_12 = NL;
  protected final String TEXT_13 = NL;
  protected final String TEXT_14 = NL + "/**" + NL + " * <!-- begin-user-doc -->" + NL + " * A representation of the model object '<em><b>";
  protected final String TEXT_15 = "</b></em>'." + NL + " * <!-- end-user-doc -->";
  protected final String TEXT_16 = NL + " *" + NL + " * <!-- begin-model-doc -->" + NL + " * ";
  protected final String TEXT_17 = NL + " * <!-- end-model-doc -->";
  protected final String TEXT_18 = NL + " *";
  protected final String TEXT_19 = NL + " * <p>" + NL + " * The following features are supported:" + NL + " * <ul>";
  protected final String TEXT_20 = NL + " *   <li>{@link ";
  protected final String TEXT_21 = "#";
  protected final String TEXT_22 = " <em>";
  protected final String TEXT_23 = "</em>}</li>";
  protected final String TEXT_24 = NL + " * </ul>" + NL + " * </p>";
  protected final String TEXT_25 = NL + " *";
  protected final String TEXT_26 = NL + " * @see ";
  protected final String TEXT_27 = "#get";
  protected final String TEXT_28 = "()";
  protected final String TEXT_29 = NL + " * @model ";
  protected final String TEXT_30 = NL + " *        ";
  protected final String TEXT_31 = NL + " * @model";
  protected final String TEXT_32 = NL + " * @extends ";
  protected final String TEXT_33 = NL + " * @generated" + NL + " */";
  protected final String TEXT_34 = NL + "/**" + NL + " * <!-- begin-user-doc -->" + NL + " * An implementation of the model object '<em><b>";
  protected final String TEXT_35 = "</b></em>'." + NL + " * <!-- end-user-doc -->" + NL + " * <p>";
  protected final String TEXT_36 = NL + " * The following features are implemented:" + NL + " * <ul>";
  protected final String TEXT_37 = NL + " *   <li>{@link ";
  protected final String TEXT_38 = "#";
  protected final String TEXT_39 = " <em>";
  protected final String TEXT_40 = "</em>}</li>";
  protected final String TEXT_41 = NL + " * </ul>";
  protected final String TEXT_42 = NL + " * </p>" + NL + " *" + NL + " * @generated" + NL + " */";
  protected final String TEXT_43 = NL + "public";
  protected final String TEXT_44 = " abstract";
  protected final String TEXT_45 = " class ";
  protected final String TEXT_46 = NL + "public interface ";
  protected final String TEXT_47 = NL + "{";
  protected final String TEXT_48 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */" + NL + "\t";
  protected final String TEXT_49 = " copyright = ";
  protected final String TEXT_50 = ";";
  protected final String TEXT_51 = NL;
  protected final String TEXT_52 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */" + NL + "\tpublic static final ";
  protected final String TEXT_53 = " mofDriverNumber = \"";
  protected final String TEXT_54 = "\";";
  protected final String TEXT_55 = NL;
  protected final String TEXT_56 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */" + NL + "\tprivate static final long serialVersionUID = 1L;" + NL;
  protected final String TEXT_57 = NL + "\t/**" + NL + "\t * An array of objects representing the values of non-primitive features." + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */" + NL + "\tprotected Object[] ";
  protected final String TEXT_58 = ";" + NL;
  protected final String TEXT_59 = NL + "\t/**" + NL + "\t * A bit field representing the indices of non-primitive feature values." + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */" + NL + "\tprotected int ";
  protected final String TEXT_60 = ";" + NL;
  protected final String TEXT_61 = NL + "\t/**" + NL + "\t * A set of bit flags representing the values of boolean attributes and whether unsettable features have been set." + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t * @ordered" + NL + "\t */" + NL + "\tprotected int ";
  protected final String TEXT_62 = " = 0;" + NL;
  protected final String TEXT_63 = NL + "\t/**" + NL + "\t * The cached value of the '{@link #";
  protected final String TEXT_64 = "() <em>";
  protected final String TEXT_65 = "</em>}' ";
  protected final String TEXT_66 = "." + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @see #";
  protected final String TEXT_67 = "()" + NL + "\t * @generated" + NL + "\t * @ordered" + NL + "\t */" + NL + "\tprotected ";
  protected final String TEXT_68 = " ";
  protected final String TEXT_69 = ";" + NL;
  protected final String TEXT_70 = NL + "\t/**" + NL + "\t * The empty value for the '{@link #";
  protected final String TEXT_71 = "() <em>";
  protected final String TEXT_72 = "</em>}' array accessor." + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @see #";
  protected final String TEXT_73 = "()" + NL + "\t * @generated" + NL + "\t * @ordered" + NL + "\t */";
  protected final String TEXT_74 = NL + "\t@SuppressWarnings(\"unchecked\")";
  protected final String TEXT_75 = NL + "\tprotected static final ";
  protected final String TEXT_76 = "[] ";
  protected final String TEXT_77 = "_EEMPTY_ARRAY = new ";
  protected final String TEXT_78 = " [0]";
  protected final String TEXT_79 = ";" + NL;
  protected final String TEXT_80 = NL + "\t/**" + NL + "\t * The default value of the '{@link #";
  protected final String TEXT_81 = "() <em>";
  protected final String TEXT_82 = "</em>}' ";
  protected final String TEXT_83 = "." + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @see #";
  protected final String TEXT_84 = "()" + NL + "\t * @generated" + NL + "\t * @ordered" + NL + "\t */";
  protected final String TEXT_85 = NL + "\t@SuppressWarnings(\"unchecked\")";
  protected final String TEXT_86 = NL + "\tprotected static final ";
  protected final String TEXT_87 = " ";
  protected final String TEXT_88 = "; // TODO The default value literal \"";
  protected final String TEXT_89 = "\" is not valid.";
  protected final String TEXT_90 = " = ";
  protected final String TEXT_91 = ";";
  protected final String TEXT_92 = NL;
  protected final String TEXT_93 = NL + "\t/**" + NL + "\t * An additional set of bit flags representing the values of boolean attributes and whether unsettable features have been set." + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t * @ordered" + NL + "\t */" + NL + "\tprotected int ";
  protected final String TEXT_94 = " = 0;" + NL;
  protected final String TEXT_95 = NL + "\t/**" + NL + "\t * The flag representing the value of the '{@link #";
  protected final String TEXT_96 = "() <em>";
  protected final String TEXT_97 = "</em>}' ";
  protected final String TEXT_98 = "." + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @see #";
  protected final String TEXT_99 = "()" + NL + "\t * @generated" + NL + "\t * @ordered" + NL + "\t */" + NL + "\tprotected static final int ";
  protected final String TEXT_100 = "_EFLAG = 1 ";
  protected final String TEXT_101 = ";" + NL;
  protected final String TEXT_102 = NL + "\t/**" + NL + "\t * The cached value of the '{@link #";
  protected final String TEXT_103 = "() <em>";
  protected final String TEXT_104 = "</em>}' ";
  protected final String TEXT_105 = "." + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @see #";
  protected final String TEXT_106 = "()" + NL + "\t * @generated" + NL + "\t * @ordered" + NL + "\t */" + NL + "\tprotected ";
  protected final String TEXT_107 = " ";
  protected final String TEXT_108 = " = ";
  protected final String TEXT_109 = ";" + NL;
  protected final String TEXT_110 = NL + "\t/**" + NL + "\t * An additional set of bit flags representing the values of boolean attributes and whether unsettable features have been set." + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t * @ordered" + NL + "\t */" + NL + "\tprotected int ";
  protected final String TEXT_111 = " = 0;" + NL;
  protected final String TEXT_112 = NL + "\t/**" + NL + "\t * The flag representing whether the ";
  protected final String TEXT_113 = " ";
  protected final String TEXT_114 = " has been set." + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t * @ordered" + NL + "\t */" + NL + "\tprotected static final int ";
  protected final String TEXT_115 = "_ESETFLAG = 1 ";
  protected final String TEXT_116 = ";" + NL;
  protected final String TEXT_117 = NL + "\t/**" + NL + "\t * This is true if the ";
  protected final String TEXT_118 = " ";
  protected final String TEXT_119 = " has been set." + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t * @ordered" + NL + "\t */" + NL + "\tprotected boolean ";
  protected final String TEXT_120 = "ESet;" + NL;
  protected final String TEXT_121 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */" + NL + "\tprivate static final int ";
  protected final String TEXT_122 = " = ";
  protected final String TEXT_123 = ".getFeatureID(";
  protected final String TEXT_124 = ") - ";
  protected final String TEXT_125 = ";" + NL;
  protected final String TEXT_126 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */" + NL + "\tprivate static final int ";
  protected final String TEXT_127 = " = ";
  protected final String TEXT_128 = ".getFeatureID(";
  protected final String TEXT_129 = ") - ";
  protected final String TEXT_130 = ";" + NL;
  protected final String TEXT_131 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */" + NL + "\t";
  protected final String TEXT_132 = "public";
  protected final String TEXT_133 = "protected";
  protected final String TEXT_134 = " ";
  protected final String TEXT_135 = "()" + NL + "\t{" + NL + "\t\tsuper();";
  protected final String TEXT_136 = NL + "\t\t";
  protected final String TEXT_137 = " |= ";
  protected final String TEXT_138 = "_EFLAG;";
  protected final String TEXT_139 = NL + "\t}" + NL + "" + NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */";
  protected final String TEXT_140 = NL + "\t@Override";
  protected final String TEXT_141 = NL + "\tprotected ";
  protected final String TEXT_142 = " eStaticClass()" + NL + "\t{" + NL + "\t\treturn ";
  protected final String TEXT_143 = ";" + NL + "\t}" + NL;
  protected final String TEXT_144 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */";
  protected final String TEXT_145 = NL + "\t@Override";
  protected final String TEXT_146 = NL + "\tprotected int eStaticFeatureCount()" + NL + "\t{" + NL + "\t\treturn ";
  protected final String TEXT_147 = ";" + NL + "\t}" + NL;
  protected final String TEXT_148 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */";
  protected final String TEXT_149 = NL + "\t";
  protected final String TEXT_150 = "[] ";
  protected final String TEXT_151 = "();" + NL;
  protected final String TEXT_152 = NL + "\t@SuppressWarnings(\"unchecked\")";
  protected final String TEXT_153 = NL + "\tpublic ";
  protected final String TEXT_154 = "[] ";
  protected final String TEXT_155 = "()" + NL + "\t{";
  protected final String TEXT_156 = NL + "\t\t";
  protected final String TEXT_157 = " list = (";
  protected final String TEXT_158 = ")";
  protected final String TEXT_159 = "();" + NL + "\t\tif (list.isEmpty()) return ";
  protected final String TEXT_160 = "(";
  protected final String TEXT_161 = "[])";
  protected final String TEXT_162 = "_EEMPTY_ARRAY;";
  protected final String TEXT_163 = NL + "\t\tif (";
  protected final String TEXT_164 = " == null || ";
  protected final String TEXT_165 = ".isEmpty()) return ";
  protected final String TEXT_166 = "(";
  protected final String TEXT_167 = "[])";
  protected final String TEXT_168 = "_EEMPTY_ARRAY;" + NL + "\t\t";
  protected final String TEXT_169 = " list = (";
  protected final String TEXT_170 = ")";
  protected final String TEXT_171 = ";";
  protected final String TEXT_172 = NL + "\t\tlist.shrink();" + NL + "\t\treturn (";
  protected final String TEXT_173 = "[])list.data();" + NL + "\t}" + NL;
  protected final String TEXT_174 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */";
  protected final String TEXT_175 = NL + "\t";
  protected final String TEXT_176 = " get";
  protected final String TEXT_177 = "(int index);" + NL;
  protected final String TEXT_178 = NL + "\tpublic ";
  protected final String TEXT_179 = " get";
  protected final String TEXT_180 = "(int index)" + NL + "\t{" + NL + "\t\treturn ";
  protected final String TEXT_181 = "(";
  protected final String TEXT_182 = ")";
  protected final String TEXT_183 = "().get(index);" + NL + "\t}" + NL;
  protected final String TEXT_184 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */";
  protected final String TEXT_185 = NL + "\tint get";
  protected final String TEXT_186 = "Length();" + NL;
  protected final String TEXT_187 = NL + "\tpublic int get";
  protected final String TEXT_188 = "Length()" + NL + "\t{";
  protected final String TEXT_189 = NL + "\t\treturn ";
  protected final String TEXT_190 = "().size();";
  protected final String TEXT_191 = NL + "\t\treturn ";
  protected final String TEXT_192 = " == null ? 0 : ";
  protected final String TEXT_193 = ".size();";
  protected final String TEXT_194 = NL + "\t}" + NL;
  protected final String TEXT_195 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */";
  protected final String TEXT_196 = NL + "\tvoid set";
  protected final String TEXT_197 = "(";
  protected final String TEXT_198 = "[] new";
  protected final String TEXT_199 = ");" + NL;
  protected final String TEXT_200 = NL + "\tpublic void set";
  protected final String TEXT_201 = "(";
  protected final String TEXT_202 = "[] new";
  protected final String TEXT_203 = ")" + NL + "\t{" + NL + "\t\t((";
  protected final String TEXT_204 = ")";
  protected final String TEXT_205 = "()).setData(new";
  protected final String TEXT_206 = ".length, new";
  protected final String TEXT_207 = ");" + NL + "\t}" + NL;
  protected final String TEXT_208 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */";
  protected final String TEXT_209 = NL + "\tvoid set";
  protected final String TEXT_210 = "(int index, ";
  protected final String TEXT_211 = " element);" + NL;
  protected final String TEXT_212 = NL + "\tpublic void set";
  protected final String TEXT_213 = "(int index, ";
  protected final String TEXT_214 = " element)" + NL + "\t{" + NL + "\t\t";
  protected final String TEXT_215 = "().set(index, element);" + NL + "\t}" + NL;
  protected final String TEXT_216 = NL + "\t/**" + NL + "\t * Returns the value of the '<em><b>";
  protected final String TEXT_217 = "</b></em>' ";
  protected final String TEXT_218 = ".";
  protected final String TEXT_219 = NL + "\t * The key is of type ";
  protected final String TEXT_220 = "list of {@link ";
  protected final String TEXT_221 = "}";
  protected final String TEXT_222 = "{@link ";
  protected final String TEXT_223 = "}";
  protected final String TEXT_224 = "," + NL + "\t * and the value is of type ";
  protected final String TEXT_225 = "list of {@link ";
  protected final String TEXT_226 = "}";
  protected final String TEXT_227 = "{@link ";
  protected final String TEXT_228 = "}";
  protected final String TEXT_229 = ",";
  protected final String TEXT_230 = NL + "\t * The list contents are of type {@link ";
  protected final String TEXT_231 = "}";
  protected final String TEXT_232 = ".";
  protected final String TEXT_233 = NL + "\t * The default value is <code>";
  protected final String TEXT_234 = "</code>.";
  protected final String TEXT_235 = NL + "\t * The literals are from the enumeration {@link ";
  protected final String TEXT_236 = "}.";
  protected final String TEXT_237 = NL + "\t * It is bidirectional and its opposite is '{@link ";
  protected final String TEXT_238 = "#";
  protected final String TEXT_239 = " <em>";
  protected final String TEXT_240 = "</em>}'.";
  protected final String TEXT_241 = NL + "\t * <!-- begin-user-doc -->";
  protected final String TEXT_242 = NL + "\t * <p>" + NL + "\t * If the meaning of the '<em>";
  protected final String TEXT_243 = "</em>' ";
  protected final String TEXT_244 = " isn't clear," + NL + "\t * there really should be more of a description here..." + NL + "\t * </p>";
  protected final String TEXT_245 = NL + "\t * <!-- end-user-doc -->";
  protected final String TEXT_246 = NL + "\t * <!-- begin-model-doc -->" + NL + "\t * ";
  protected final String TEXT_247 = NL + "\t * <!-- end-model-doc -->";
  protected final String TEXT_248 = NL + "\t * @return the value of the '<em>";
  protected final String TEXT_249 = "</em>' ";
  protected final String TEXT_250 = ".";
  protected final String TEXT_251 = NL + "\t * @see ";
  protected final String TEXT_252 = NL + "\t * @see #isSet";
  protected final String TEXT_253 = "()";
  protected final String TEXT_254 = NL + "\t * @see #unset";
  protected final String TEXT_255 = "()";
  protected final String TEXT_256 = NL + "\t * @see #set";
  protected final String TEXT_257 = "(";
  protected final String TEXT_258 = ")";
  protected final String TEXT_259 = NL + "\t * @see ";
  protected final String TEXT_260 = "#get";
  protected final String TEXT_261 = "()";
  protected final String TEXT_262 = NL + "\t * @see ";
  protected final String TEXT_263 = "#";
  protected final String TEXT_264 = NL + "\t * @model ";
  protected final String TEXT_265 = NL + "\t *        ";
  protected final String TEXT_266 = NL + "\t * @model";
  protected final String TEXT_267 = NL + "\t * @generated" + NL + "\t */";
  protected final String TEXT_268 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */";
  protected final String TEXT_269 = NL + "\t";
  protected final String TEXT_270 = " ";
  protected final String TEXT_271 = "();" + NL;
  protected final String TEXT_272 = NL + "\t@SuppressWarnings(\"unchecked\")";
  protected final String TEXT_273 = NL + "\tpublic ";
  protected final String TEXT_274 = " ";
  protected final String TEXT_275 = "_";
  protected final String TEXT_276 = "()" + NL + "\t{";
  protected final String TEXT_277 = NL + "\t\treturn ";
  protected final String TEXT_278 = "(";
  protected final String TEXT_279 = "(";
  protected final String TEXT_280 = ")eGet(";
  protected final String TEXT_281 = ", true)";
  protected final String TEXT_282 = ").";
  protected final String TEXT_283 = "()";
  protected final String TEXT_284 = ";";
  protected final String TEXT_285 = NL + "\t\t";
  protected final String TEXT_286 = " ";
  protected final String TEXT_287 = " = (";
  protected final String TEXT_288 = ")eVirtualGet(";
  protected final String TEXT_289 = ");";
  protected final String TEXT_290 = NL + "\t\tif (";
  protected final String TEXT_291 = " == null)" + NL + "\t\t{";
  protected final String TEXT_292 = NL + "\t\t\teVirtualSet(";
  protected final String TEXT_293 = ", ";
  protected final String TEXT_294 = " = new ";
  protected final String TEXT_295 = ");";
  protected final String TEXT_296 = NL + "\t\t\t";
  protected final String TEXT_297 = " = new ";
  protected final String TEXT_298 = ";";
  protected final String TEXT_299 = NL + "\t\t}" + NL + "\t\treturn ";
  protected final String TEXT_300 = ";";
  protected final String TEXT_301 = NL + "\t\tif (eContainerFeatureID != ";
  protected final String TEXT_302 = ") return null;" + NL + "\t\treturn (";
  protected final String TEXT_303 = ")eContainer();";
  protected final String TEXT_304 = NL + "\t\t";
  protected final String TEXT_305 = " ";
  protected final String TEXT_306 = " = (";
  protected final String TEXT_307 = ")eVirtualGet(";
  protected final String TEXT_308 = ", ";
  protected final String TEXT_309 = ");";
  protected final String TEXT_310 = NL + "\t\tif (";
  protected final String TEXT_311 = " != null && ";
  protected final String TEXT_312 = ".eIsProxy())" + NL + "\t\t{" + NL + "\t\t\t";
  protected final String TEXT_313 = " old";
  protected final String TEXT_314 = " = (";
  protected final String TEXT_315 = ")";
  protected final String TEXT_316 = ";" + NL + "\t\t\t";
  protected final String TEXT_317 = " = ";
  protected final String TEXT_318 = "eResolveProxy(old";
  protected final String TEXT_319 = ");" + NL + "\t\t\tif (";
  protected final String TEXT_320 = " != old";
  protected final String TEXT_321 = ")" + NL + "\t\t\t{";
  protected final String TEXT_322 = NL + "\t\t\t\t";
  protected final String TEXT_323 = " new";
  protected final String TEXT_324 = " = (";
  protected final String TEXT_325 = ")";
  protected final String TEXT_326 = ";";
  protected final String TEXT_327 = NL + "\t\t\t\t";
  protected final String TEXT_328 = " msgs = old";
  protected final String TEXT_329 = ".eInverseRemove(this, EOPPOSITE_FEATURE_BASE - ";
  protected final String TEXT_330 = ", null, null);";
  protected final String TEXT_331 = NL + "\t\t\t\t";
  protected final String TEXT_332 = " msgs =  old";
  protected final String TEXT_333 = ".eInverseRemove(this, ";
  protected final String TEXT_334 = ", ";
  protected final String TEXT_335 = ".class, null);";
  protected final String TEXT_336 = NL + "\t\t\t\tif (new";
  protected final String TEXT_337 = ".eInternalContainer() == null)" + NL + "\t\t\t\t{";
  protected final String TEXT_338 = NL + "\t\t\t\t\tmsgs = new";
  protected final String TEXT_339 = ".eInverseAdd(this, EOPPOSITE_FEATURE_BASE - ";
  protected final String TEXT_340 = ", null, msgs);";
  protected final String TEXT_341 = NL + "\t\t\t\t\tmsgs =  new";
  protected final String TEXT_342 = ".eInverseAdd(this, ";
  protected final String TEXT_343 = ", ";
  protected final String TEXT_344 = ".class, msgs);";
  protected final String TEXT_345 = NL + "\t\t\t\t}" + NL + "\t\t\t\tif (msgs != null) msgs.dispatch();";
  protected final String TEXT_346 = NL + "\t\t\t\teVirtualSet(";
  protected final String TEXT_347 = ", ";
  protected final String TEXT_348 = ");";
  protected final String TEXT_349 = NL + "\t\t\t\tif (eNotificationRequired())" + NL + "\t\t\t\t\teNotify(new ";
  protected final String TEXT_350 = "(this, ";
  protected final String TEXT_351 = ".RESOLVE, ";
  protected final String TEXT_352 = ", old";
  protected final String TEXT_353 = ", ";
  protected final String TEXT_354 = "));";
  protected final String TEXT_355 = NL + "\t\t\t}" + NL + "\t\t}";
  protected final String TEXT_356 = NL + "\t\treturn (";
  protected final String TEXT_357 = ")eVirtualGet(";
  protected final String TEXT_358 = ", ";
  protected final String TEXT_359 = ");";
  protected final String TEXT_360 = NL + "\t\treturn (";
  protected final String TEXT_361 = " & ";
  protected final String TEXT_362 = "_EFLAG) != 0;";
  protected final String TEXT_363 = NL + "\t\treturn ";
  protected final String TEXT_364 = ";";
  protected final String TEXT_365 = NL + "\t\t";
  protected final String TEXT_366 = " ";
  protected final String TEXT_367 = " = basicGet";
  protected final String TEXT_368 = "();" + NL + "\t\treturn ";
  protected final String TEXT_369 = " != null && ";
  protected final String TEXT_370 = ".eIsProxy() ? ";
  protected final String TEXT_371 = "eResolveProxy((";
  protected final String TEXT_372 = ")";
  protected final String TEXT_373 = ") : ";
  protected final String TEXT_374 = ";";
  protected final String TEXT_375 = NL + "\t\treturn new ";
  protected final String TEXT_376 = "((";
  protected final String TEXT_377 = ".Internal)((";
  protected final String TEXT_378 = ".Internal.Wrapper)get";
  protected final String TEXT_379 = "()).featureMap().";
  protected final String TEXT_380 = "list(";
  protected final String TEXT_381 = "));";
  protected final String TEXT_382 = NL + "\t\treturn (";
  protected final String TEXT_383 = ")get";
  protected final String TEXT_384 = "().";
  protected final String TEXT_385 = "list(";
  protected final String TEXT_386 = ");";
  protected final String TEXT_387 = NL + "\t\treturn ((";
  protected final String TEXT_388 = ".Internal.Wrapper)get";
  protected final String TEXT_389 = "()).featureMap().list(";
  protected final String TEXT_390 = ");";
  protected final String TEXT_391 = NL + "\t\treturn get";
  protected final String TEXT_392 = "().list(";
  protected final String TEXT_393 = ");";
  protected final String TEXT_394 = NL + "\t\treturn ";
  protected final String TEXT_395 = "(";
  protected final String TEXT_396 = "(";
  protected final String TEXT_397 = ")";
  protected final String TEXT_398 = "((";
  protected final String TEXT_399 = ".Internal.Wrapper)get";
  protected final String TEXT_400 = "()).featureMap().get(";
  protected final String TEXT_401 = ", true)";
  protected final String TEXT_402 = ").";
  protected final String TEXT_403 = "()";
  protected final String TEXT_404 = ";";
  protected final String TEXT_405 = NL + "\t\treturn ";
  protected final String TEXT_406 = "(";
  protected final String TEXT_407 = "(";
  protected final String TEXT_408 = ")";
  protected final String TEXT_409 = "get";
  protected final String TEXT_410 = "().get(";
  protected final String TEXT_411 = ", true)";
  protected final String TEXT_412 = ").";
  protected final String TEXT_413 = "()";
  protected final String TEXT_414 = ";";
  protected final String TEXT_415 = NL + "\t\t";
  protected final String TEXT_416 = NL + "\t\t// TODO: implement this method to return the '";
  protected final String TEXT_417 = "' ";
  protected final String TEXT_418 = NL + "\t\t// Ensure that you remove @generated or mark it @generated NOT";
  protected final String TEXT_419 = NL + "\t\t// The list is expected to implement org.eclipse.emf.ecore.util.InternalEList and org.eclipse.emf.ecore.EStructuralFeature.Setting" + NL + "\t\t// so it's likely that an appropriate subclass of org.eclipse.emf.ecore.util.";
  protected final String TEXT_420 = "EcoreEMap";
  protected final String TEXT_421 = "BasicFeatureMap";
  protected final String TEXT_422 = "EcoreEList";
  protected final String TEXT_423 = " should be used.";
  protected final String TEXT_424 = NL + "\t\tthrow new UnsupportedOperationException();";
  protected final String TEXT_425 = NL + "\t}" + NL;
  protected final String TEXT_426 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */";
  protected final String TEXT_427 = NL + "\tpublic ";
  protected final String TEXT_428 = " basicGet";
  protected final String TEXT_429 = "()" + NL + "\t{";
  protected final String TEXT_430 = NL + "\t\tif (eContainerFeatureID != ";
  protected final String TEXT_431 = ") return null;" + NL + "\t\treturn (";
  protected final String TEXT_432 = ")eInternalContainer();";
  protected final String TEXT_433 = NL + "\t\treturn (";
  protected final String TEXT_434 = ")eVirtualGet(";
  protected final String TEXT_435 = ");";
  protected final String TEXT_436 = NL + "\t\treturn ";
  protected final String TEXT_437 = ";";
  protected final String TEXT_438 = NL + "\t\treturn (";
  protected final String TEXT_439 = ")((";
  protected final String TEXT_440 = ".Internal.Wrapper)get";
  protected final String TEXT_441 = "()).featureMap().get(";
  protected final String TEXT_442 = ", false);";
  protected final String TEXT_443 = NL + "\t\treturn (";
  protected final String TEXT_444 = ")get";
  protected final String TEXT_445 = "().get(";
  protected final String TEXT_446 = ", false);";
  protected final String TEXT_447 = NL + "\t\t// TODO: implement this method to return the '";
  protected final String TEXT_448 = "' ";
  protected final String TEXT_449 = NL + "\t\t// -> do not perform proxy resolution" + NL + "\t\t// Ensure that you remove @generated or mark it @generated NOT" + NL + "\t\tthrow new UnsupportedOperationException();";
  protected final String TEXT_450 = NL + "\t}" + NL;
  protected final String TEXT_451 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */";
  protected final String TEXT_452 = NL + "\tpublic ";
  protected final String TEXT_453 = " basicSet";
  protected final String TEXT_454 = "(";
  protected final String TEXT_455 = " new";
  protected final String TEXT_456 = ", ";
  protected final String TEXT_457 = " msgs)" + NL + "\t{";
  protected final String TEXT_458 = NL + "\t\tmsgs = eBasicSetContainer((";
  protected final String TEXT_459 = ")new";
  protected final String TEXT_460 = ", ";
  protected final String TEXT_461 = ", msgs);";
  protected final String TEXT_462 = NL + "\t\treturn msgs;";
  protected final String TEXT_463 = NL + "\t\tObject old";
  protected final String TEXT_464 = " = eVirtualSet(";
  protected final String TEXT_465 = ", new";
  protected final String TEXT_466 = ");";
  protected final String TEXT_467 = NL + "\t\t";
  protected final String TEXT_468 = " old";
  protected final String TEXT_469 = " = ";
  protected final String TEXT_470 = ";" + NL + "\t\t";
  protected final String TEXT_471 = " = new";
  protected final String TEXT_472 = ";";
  protected final String TEXT_473 = NL + "\t\tboolean isSetChange = old";
  protected final String TEXT_474 = " == EVIRTUAL_NO_VALUE;";
  protected final String TEXT_475 = NL + "\t\tboolean old";
  protected final String TEXT_476 = "ESet = (";
  protected final String TEXT_477 = " & ";
  protected final String TEXT_478 = "_ESETFLAG) != 0;" + NL + "\t\t";
  protected final String TEXT_479 = " |= ";
  protected final String TEXT_480 = "_ESETFLAG;";
  protected final String TEXT_481 = NL + "\t\tboolean old";
  protected final String TEXT_482 = "ESet = ";
  protected final String TEXT_483 = "ESet;" + NL + "\t\t";
  protected final String TEXT_484 = "ESet = true;";
  protected final String TEXT_485 = NL + "\t\tif (eNotificationRequired())" + NL + "\t\t{";
  protected final String TEXT_486 = NL + "\t\t\t";
  protected final String TEXT_487 = " notification = new ";
  protected final String TEXT_488 = "(this, ";
  protected final String TEXT_489 = ".SET, ";
  protected final String TEXT_490 = ", ";
  protected final String TEXT_491 = "isSetChange ? null : old";
  protected final String TEXT_492 = "old";
  protected final String TEXT_493 = ", new";
  protected final String TEXT_494 = ", ";
  protected final String TEXT_495 = "isSetChange";
  protected final String TEXT_496 = "!old";
  protected final String TEXT_497 = "ESet";
  protected final String TEXT_498 = ");";
  protected final String TEXT_499 = NL + "\t\t\t";
  protected final String TEXT_500 = " notification = new ";
  protected final String TEXT_501 = "(this, ";
  protected final String TEXT_502 = ".SET, ";
  protected final String TEXT_503 = ", ";
  protected final String TEXT_504 = "old";
  protected final String TEXT_505 = " == EVIRTUAL_NO_VALUE ? null : old";
  protected final String TEXT_506 = "old";
  protected final String TEXT_507 = ", new";
  protected final String TEXT_508 = ");";
  protected final String TEXT_509 = NL + "\t\t\tif (msgs == null) msgs = notification; else msgs.add(notification);" + NL + "\t\t}";
  protected final String TEXT_510 = NL + "\t\treturn msgs;";
  protected final String TEXT_511 = NL + "\t\treturn ((";
  protected final String TEXT_512 = ".Internal)((";
  protected final String TEXT_513 = ".Internal.Wrapper)get";
  protected final String TEXT_514 = "()).featureMap()).basicAdd(";
  protected final String TEXT_515 = ", new";
  protected final String TEXT_516 = ", msgs);";
  protected final String TEXT_517 = NL + "\t\treturn ((";
  protected final String TEXT_518 = ".Internal)get";
  protected final String TEXT_519 = "()).basicAdd(";
  protected final String TEXT_520 = ", new";
  protected final String TEXT_521 = ", msgs);";
  protected final String TEXT_522 = NL + "\t\t// TODO: implement this method to set the contained '";
  protected final String TEXT_523 = "' ";
  protected final String TEXT_524 = NL + "\t\t// -> this method is automatically invoked to keep the containment relationship in synch" + NL + "\t\t// -> do not modify other features" + NL + "\t\t// -> return msgs, after adding any generated Notification to it (if it is null, a NotificationChain object must be created first)" + NL + "\t\t// Ensure that you remove @generated or mark it @generated NOT" + NL + "\t\tthrow new UnsupportedOperationException();";
  protected final String TEXT_525 = NL + "\t}" + NL;
  protected final String TEXT_526 = NL + "\t/**" + NL + "\t * Sets the value of the '{@link ";
  protected final String TEXT_527 = "#";
  protected final String TEXT_528 = " <em>";
  protected final String TEXT_529 = "</em>}' ";
  protected final String TEXT_530 = ".";
  protected final String TEXT_531 = NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @param value the new value of the '<em>";
  protected final String TEXT_532 = "</em>' ";
  protected final String TEXT_533 = ".";
  protected final String TEXT_534 = NL + "\t * @see ";
  protected final String TEXT_535 = NL + "\t * @see #isSet";
  protected final String TEXT_536 = "()";
  protected final String TEXT_537 = NL + "\t * @see #unset";
  protected final String TEXT_538 = "()";
  protected final String TEXT_539 = NL + "\t * @see #";
  protected final String TEXT_540 = "()" + NL + "\t * @generated" + NL + "\t */";
  protected final String TEXT_541 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */";
  protected final String TEXT_542 = NL + "\tvoid set";
  protected final String TEXT_543 = "(";
  protected final String TEXT_544 = " value);" + NL;
  protected final String TEXT_545 = NL + "\tpublic void set";
  protected final String TEXT_546 = "_";
  protected final String TEXT_547 = "(";
  protected final String TEXT_548 = " ";
  protected final String TEXT_549 = ")" + NL + "\t{";
  protected final String TEXT_550 = NL + "\t\teSet(";
  protected final String TEXT_551 = ", ";
  protected final String TEXT_552 = "new ";
  protected final String TEXT_553 = "(";
  protected final String TEXT_554 = "new";
  protected final String TEXT_555 = ")";
  protected final String TEXT_556 = ");";
  protected final String TEXT_557 = NL + "\t\tif (new";
  protected final String TEXT_558 = " != eInternalContainer() || (eContainerFeatureID != ";
  protected final String TEXT_559 = " && new";
  protected final String TEXT_560 = " != null))" + NL + "\t\t{" + NL + "\t\t\tif (";
  protected final String TEXT_561 = ".isAncestor(this, ";
  protected final String TEXT_562 = "new";
  protected final String TEXT_563 = "))" + NL + "\t\t\t\tthrow new ";
  protected final String TEXT_564 = "(\"Recursive containment not allowed for \" + toString());";
  protected final String TEXT_565 = NL + "\t\t\t";
  protected final String TEXT_566 = " msgs = null;" + NL + "\t\t\tif (eInternalContainer() != null)" + NL + "\t\t\t\tmsgs = eBasicRemoveFromContainer(msgs);" + NL + "\t\t\tif (new";
  protected final String TEXT_567 = " != null)" + NL + "\t\t\t\tmsgs = ((";
  protected final String TEXT_568 = ")new";
  protected final String TEXT_569 = ").eInverseAdd(this, ";
  protected final String TEXT_570 = ", ";
  protected final String TEXT_571 = ".class, msgs);" + NL + "\t\t\tmsgs = basicSet";
  protected final String TEXT_572 = "(";
  protected final String TEXT_573 = "new";
  protected final String TEXT_574 = ", msgs);" + NL + "\t\t\tif (msgs != null) msgs.dispatch();" + NL + "\t\t}";
  protected final String TEXT_575 = NL + "\t\telse if (eNotificationRequired())" + NL + "\t\t\teNotify(new ";
  protected final String TEXT_576 = "(this, ";
  protected final String TEXT_577 = ".SET, ";
  protected final String TEXT_578 = ", new";
  protected final String TEXT_579 = ", new";
  protected final String TEXT_580 = "));";
  protected final String TEXT_581 = NL + "\t\t";
  protected final String TEXT_582 = " ";
  protected final String TEXT_583 = " = (";
  protected final String TEXT_584 = ")eVirtualGet(";
  protected final String TEXT_585 = ");";
  protected final String TEXT_586 = NL + "\t\tif (new";
  protected final String TEXT_587 = " != ";
  protected final String TEXT_588 = ")" + NL + "\t\t{" + NL + "\t\t\t";
  protected final String TEXT_589 = " msgs = null;" + NL + "\t\t\tif (";
  protected final String TEXT_590 = " != null)";
  protected final String TEXT_591 = NL + "\t\t\t\tmsgs = ((";
  protected final String TEXT_592 = ")";
  protected final String TEXT_593 = ").eInverseRemove(this, EOPPOSITE_FEATURE_BASE - ";
  protected final String TEXT_594 = ", null, msgs);" + NL + "\t\t\tif (new";
  protected final String TEXT_595 = " != null)" + NL + "\t\t\t\tmsgs = ((";
  protected final String TEXT_596 = ")new";
  protected final String TEXT_597 = ").eInverseAdd(this, EOPPOSITE_FEATURE_BASE - ";
  protected final String TEXT_598 = ", null, msgs);";
  protected final String TEXT_599 = NL + "\t\t\t\tmsgs = ((";
  protected final String TEXT_600 = ")";
  protected final String TEXT_601 = ").eInverseRemove(this, ";
  protected final String TEXT_602 = ", ";
  protected final String TEXT_603 = ".class, msgs);" + NL + "\t\t\tif (new";
  protected final String TEXT_604 = " != null)" + NL + "\t\t\t\tmsgs = ((";
  protected final String TEXT_605 = ")new";
  protected final String TEXT_606 = ").eInverseAdd(this, ";
  protected final String TEXT_607 = ", ";
  protected final String TEXT_608 = ".class, msgs);";
  protected final String TEXT_609 = NL + "\t\t\tmsgs = basicSet";
  protected final String TEXT_610 = "(";
  protected final String TEXT_611 = "new";
  protected final String TEXT_612 = ", msgs);" + NL + "\t\t\tif (msgs != null) msgs.dispatch();" + NL + "\t\t}";
  protected final String TEXT_613 = NL + "\t\telse" + NL + "\t\t{";
  protected final String TEXT_614 = NL + "\t\t\tboolean old";
  protected final String TEXT_615 = "ESet = eVirtualIsSet(";
  protected final String TEXT_616 = ");";
  protected final String TEXT_617 = NL + "\t\t\tboolean old";
  protected final String TEXT_618 = "ESet = (";
  protected final String TEXT_619 = " & ";
  protected final String TEXT_620 = "_ESETFLAG) != 0;";
  protected final String TEXT_621 = NL + "\t\t\t";
  protected final String TEXT_622 = " |= ";
  protected final String TEXT_623 = "_ESETFLAG;";
  protected final String TEXT_624 = NL + "\t\t\tboolean old";
  protected final String TEXT_625 = "ESet = ";
  protected final String TEXT_626 = "ESet;";
  protected final String TEXT_627 = NL + "\t\t\t";
  protected final String TEXT_628 = "ESet = true;";
  protected final String TEXT_629 = NL + "\t\t\tif (eNotificationRequired())" + NL + "\t\t\t\teNotify(new ";
  protected final String TEXT_630 = "(this, ";
  protected final String TEXT_631 = ".SET, ";
  protected final String TEXT_632 = ", new";
  protected final String TEXT_633 = ", new";
  protected final String TEXT_634 = ", !old";
  protected final String TEXT_635 = "ESet));";
  protected final String TEXT_636 = NL + "\t\t}";
  protected final String TEXT_637 = NL + "\t\telse if (eNotificationRequired())" + NL + "\t\t\teNotify(new ";
  protected final String TEXT_638 = "(this, ";
  protected final String TEXT_639 = ".SET, ";
  protected final String TEXT_640 = ", new";
  protected final String TEXT_641 = ", new";
  protected final String TEXT_642 = "));";
  protected final String TEXT_643 = NL + "\t\t";
  protected final String TEXT_644 = " old";
  protected final String TEXT_645 = " = (";
  protected final String TEXT_646 = " & ";
  protected final String TEXT_647 = "_EFLAG) != 0;";
  protected final String TEXT_648 = NL + "\t\tif (new";
  protected final String TEXT_649 = ") ";
  protected final String TEXT_650 = " |= ";
  protected final String TEXT_651 = "_EFLAG; else ";
  protected final String TEXT_652 = " &= ~";
  protected final String TEXT_653 = "_EFLAG;";
  protected final String TEXT_654 = NL + "\t\t";
  protected final String TEXT_655 = " old";
  protected final String TEXT_656 = " = ";
  protected final String TEXT_657 = ";";
  protected final String TEXT_658 = NL + "\t\t";
  protected final String TEXT_659 = " ";
  protected final String TEXT_660 = " = new";
  protected final String TEXT_661 = " == null ? ";
  protected final String TEXT_662 = " : new";
  protected final String TEXT_663 = ";";
  protected final String TEXT_664 = NL + "\t\t";
  protected final String TEXT_665 = " = new";
  protected final String TEXT_666 = " == null ? ";
  protected final String TEXT_667 = " : new";
  protected final String TEXT_668 = ";";
  protected final String TEXT_669 = NL + "\t\t";
  protected final String TEXT_670 = " ";
  protected final String TEXT_671 = " = ";
  protected final String TEXT_672 = "new";
  protected final String TEXT_673 = ";";
  protected final String TEXT_674 = NL + "\t\t";
  protected final String TEXT_675 = " = ";
  protected final String TEXT_676 = "new";
  protected final String TEXT_677 = ";";
  protected final String TEXT_678 = NL + "\t\tObject old";
  protected final String TEXT_679 = " = eVirtualSet(";
  protected final String TEXT_680 = ", ";
  protected final String TEXT_681 = ");";
  protected final String TEXT_682 = NL + "\t\tboolean isSetChange = old";
  protected final String TEXT_683 = " == EVIRTUAL_NO_VALUE;";
  protected final String TEXT_684 = NL + "\t\tboolean old";
  protected final String TEXT_685 = "ESet = (";
  protected final String TEXT_686 = " & ";
  protected final String TEXT_687 = "_ESETFLAG) != 0;";
  protected final String TEXT_688 = NL + "\t\t";
  protected final String TEXT_689 = " |= ";
  protected final String TEXT_690 = "_ESETFLAG;";
  protected final String TEXT_691 = NL + "\t\tboolean old";
  protected final String TEXT_692 = "ESet = ";
  protected final String TEXT_693 = "ESet;";
  protected final String TEXT_694 = NL + "\t\t";
  protected final String TEXT_695 = "ESet = true;";
  protected final String TEXT_696 = NL + "\t\tif (eNotificationRequired())" + NL + "\t\t\teNotify(new ";
  protected final String TEXT_697 = "(this, ";
  protected final String TEXT_698 = ".SET, ";
  protected final String TEXT_699 = ", ";
  protected final String TEXT_700 = "isSetChange ? ";
  protected final String TEXT_701 = " : old";
  protected final String TEXT_702 = "old";
  protected final String TEXT_703 = ", ";
  protected final String TEXT_704 = "new";
  protected final String TEXT_705 = ", ";
  protected final String TEXT_706 = "isSetChange";
  protected final String TEXT_707 = "!old";
  protected final String TEXT_708 = "ESet";
  protected final String TEXT_709 = "));";
  protected final String TEXT_710 = NL + "\t\tif (eNotificationRequired())" + NL + "\t\t\teNotify(new ";
  protected final String TEXT_711 = "(this, ";
  protected final String TEXT_712 = ".SET, ";
  protected final String TEXT_713 = ", ";
  protected final String TEXT_714 = "old";
  protected final String TEXT_715 = " == EVIRTUAL_NO_VALUE ? ";
  protected final String TEXT_716 = " : old";
  protected final String TEXT_717 = "old";
  protected final String TEXT_718 = ", ";
  protected final String TEXT_719 = "new";
  protected final String TEXT_720 = "));";
  protected final String TEXT_721 = NL + "\t\t((";
  protected final String TEXT_722 = ".Internal)((";
  protected final String TEXT_723 = ".Internal.Wrapper)get";
  protected final String TEXT_724 = "()).featureMap()).set(";
  protected final String TEXT_725 = ", ";
  protected final String TEXT_726 = "new ";
  protected final String TEXT_727 = "(";
  protected final String TEXT_728 = "new";
  protected final String TEXT_729 = ")";
  protected final String TEXT_730 = ");";
  protected final String TEXT_731 = NL + "\t\t((";
  protected final String TEXT_732 = ".Internal)get";
  protected final String TEXT_733 = "()).set(";
  protected final String TEXT_734 = ", ";
  protected final String TEXT_735 = "new ";
  protected final String TEXT_736 = "(";
  protected final String TEXT_737 = "new";
  protected final String TEXT_738 = ")";
  protected final String TEXT_739 = ");";
  protected final String TEXT_740 = NL + "\t\t";
  protected final String TEXT_741 = NL + "\t\t// TODO: implement this method to set the '";
  protected final String TEXT_742 = "' ";
  protected final String TEXT_743 = NL + "\t\t// Ensure that you remove @generated or mark it @generated NOT" + NL + "\t\tthrow new UnsupportedOperationException();";
  protected final String TEXT_744 = NL + "\t}" + NL;
  protected final String TEXT_745 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */";
  protected final String TEXT_746 = NL + "\tpublic ";
  protected final String TEXT_747 = " basicUnset";
  protected final String TEXT_748 = "(";
  protected final String TEXT_749 = " msgs)" + NL + "\t{";
  protected final String TEXT_750 = NL + "\t\tObject old";
  protected final String TEXT_751 = " = eVirtualUnset(";
  protected final String TEXT_752 = ");";
  protected final String TEXT_753 = NL + "\t\t";
  protected final String TEXT_754 = " old";
  protected final String TEXT_755 = " = ";
  protected final String TEXT_756 = ";" + NL + "\t\t";
  protected final String TEXT_757 = " = null;";
  protected final String TEXT_758 = NL + "\t\tboolean isSetChange = old";
  protected final String TEXT_759 = " != EVIRTUAL_NO_VALUE;";
  protected final String TEXT_760 = NL + "\t\tboolean old";
  protected final String TEXT_761 = "ESet = (";
  protected final String TEXT_762 = " & ";
  protected final String TEXT_763 = "_ESETFLAG) != 0;" + NL + "\t\t";
  protected final String TEXT_764 = " &= ~";
  protected final String TEXT_765 = "_ESETFLAG;";
  protected final String TEXT_766 = NL + "\t\tboolean old";
  protected final String TEXT_767 = "ESet = ";
  protected final String TEXT_768 = "ESet;" + NL + "\t\t";
  protected final String TEXT_769 = "ESet = false;";
  protected final String TEXT_770 = NL + "\t\tif (eNotificationRequired())" + NL + "\t\t{" + NL + "\t\t\t";
  protected final String TEXT_771 = " notification = new ";
  protected final String TEXT_772 = "(this, ";
  protected final String TEXT_773 = ".UNSET, ";
  protected final String TEXT_774 = ", ";
  protected final String TEXT_775 = "isSetChange ? old";
  protected final String TEXT_776 = " : null";
  protected final String TEXT_777 = "old";
  protected final String TEXT_778 = ", null, ";
  protected final String TEXT_779 = "isSetChange";
  protected final String TEXT_780 = "old";
  protected final String TEXT_781 = "ESet";
  protected final String TEXT_782 = ");" + NL + "\t\t\tif (msgs == null) msgs = notification; else msgs.add(notification);" + NL + "\t\t}" + NL + "\t\treturn msgs;";
  protected final String TEXT_783 = NL + "\t\t// TODO: implement this method to unset the contained '";
  protected final String TEXT_784 = "' ";
  protected final String TEXT_785 = NL + "\t\t// -> this method is automatically invoked to keep the containment relationship in synch" + NL + "\t\t// -> do not modify other features" + NL + "\t\t// -> return msgs, after adding any generated Notification to it (if it is null, a NotificationChain object must be created first)" + NL + "\t\t// Ensure that you remove @generated or mark it @generated NOT" + NL + "\t\tthrow new UnsupportedOperationException();";
  protected final String TEXT_786 = NL + "\t}" + NL;
  protected final String TEXT_787 = NL + "\t/**" + NL + "\t * Unsets the value of the '{@link ";
  protected final String TEXT_788 = "#";
  protected final String TEXT_789 = " <em>";
  protected final String TEXT_790 = "</em>}' ";
  protected final String TEXT_791 = ".";
  protected final String TEXT_792 = NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->";
  protected final String TEXT_793 = NL + "\t * @see #isSet";
  protected final String TEXT_794 = "()";
  protected final String TEXT_795 = NL + "\t * @see #";
  protected final String TEXT_796 = "()";
  protected final String TEXT_797 = NL + "\t * @see #set";
  protected final String TEXT_798 = "(";
  protected final String TEXT_799 = ")";
  protected final String TEXT_800 = NL + "\t * @generated" + NL + "\t */";
  protected final String TEXT_801 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */";
  protected final String TEXT_802 = NL + "\tvoid unset";
  protected final String TEXT_803 = "();" + NL;
  protected final String TEXT_804 = NL + "\tpublic void unset";
  protected final String TEXT_805 = "_";
  protected final String TEXT_806 = "()" + NL + "\t{";
  protected final String TEXT_807 = NL + "\t\teUnset(";
  protected final String TEXT_808 = ");";
  protected final String TEXT_809 = NL + "\t\t";
  protected final String TEXT_810 = " ";
  protected final String TEXT_811 = " = (";
  protected final String TEXT_812 = ")eVirtualGet(";
  protected final String TEXT_813 = ");";
  protected final String TEXT_814 = NL + "\t\tif (";
  protected final String TEXT_815 = " != null) ((";
  protected final String TEXT_816 = ".Unsettable";
  protected final String TEXT_817 = ")";
  protected final String TEXT_818 = ").unset();";
  protected final String TEXT_819 = NL + "\t\t";
  protected final String TEXT_820 = " ";
  protected final String TEXT_821 = " = (";
  protected final String TEXT_822 = ")eVirtualGet(";
  protected final String TEXT_823 = ");";
  protected final String TEXT_824 = NL + "\t\tif (";
  protected final String TEXT_825 = " != null)" + NL + "\t\t{" + NL + "\t\t\t";
  protected final String TEXT_826 = " msgs = null;";
  protected final String TEXT_827 = NL + "\t\t\tmsgs = ((";
  protected final String TEXT_828 = ")";
  protected final String TEXT_829 = ").eInverseRemove(this, EOPPOSITE_FEATURE_BASE - ";
  protected final String TEXT_830 = ", null, msgs);";
  protected final String TEXT_831 = NL + "\t\t\tmsgs = ((";
  protected final String TEXT_832 = ")";
  protected final String TEXT_833 = ").eInverseRemove(this, ";
  protected final String TEXT_834 = ", ";
  protected final String TEXT_835 = ".class, msgs);";
  protected final String TEXT_836 = NL + "\t\t\tmsgs = basicUnset";
  protected final String TEXT_837 = "(msgs);" + NL + "\t\t\tif (msgs != null) msgs.dispatch();" + NL + "\t\t}" + NL + "\t\telse" + NL + "\t\t{";
  protected final String TEXT_838 = NL + "\t\t\tboolean old";
  protected final String TEXT_839 = "ESet = eVirtualIsSet(";
  protected final String TEXT_840 = ");";
  protected final String TEXT_841 = NL + "\t\t\tboolean old";
  protected final String TEXT_842 = "ESet = (";
  protected final String TEXT_843 = " & ";
  protected final String TEXT_844 = "_ESETFLAG) != 0;";
  protected final String TEXT_845 = NL + "\t\t\t";
  protected final String TEXT_846 = " &= ~";
  protected final String TEXT_847 = "_ESETFLAG;";
  protected final String TEXT_848 = NL + "\t\t\tboolean old";
  protected final String TEXT_849 = "ESet = ";
  protected final String TEXT_850 = "ESet;";
  protected final String TEXT_851 = NL + "\t\t\t";
  protected final String TEXT_852 = "ESet = false;";
  protected final String TEXT_853 = NL + "\t\t\tif (eNotificationRequired())" + NL + "\t\t\t\teNotify(new ";
  protected final String TEXT_854 = "(this, ";
  protected final String TEXT_855 = ".UNSET, ";
  protected final String TEXT_856 = ", null, null, old";
  protected final String TEXT_857 = "ESet));";
  protected final String TEXT_858 = NL + "\t\t}";
  protected final String TEXT_859 = NL + "\t\t";
  protected final String TEXT_860 = " old";
  protected final String TEXT_861 = " = (";
  protected final String TEXT_862 = " & ";
  protected final String TEXT_863 = "_EFLAG) != 0;";
  protected final String TEXT_864 = NL + "\t\tObject old";
  protected final String TEXT_865 = " = eVirtualUnset(";
  protected final String TEXT_866 = ");";
  protected final String TEXT_867 = NL + "\t\t";
  protected final String TEXT_868 = " old";
  protected final String TEXT_869 = " = ";
  protected final String TEXT_870 = ";";
  protected final String TEXT_871 = NL + "\t\tboolean isSetChange = old";
  protected final String TEXT_872 = " != EVIRTUAL_NO_VALUE;";
  protected final String TEXT_873 = NL + "\t\tboolean old";
  protected final String TEXT_874 = "ESet = (";
  protected final String TEXT_875 = " & ";
  protected final String TEXT_876 = "_ESETFLAG) != 0;";
  protected final String TEXT_877 = NL + "\t\tboolean old";
  protected final String TEXT_878 = "ESet = ";
  protected final String TEXT_879 = "ESet;";
  protected final String TEXT_880 = NL + "\t\t";
  protected final String TEXT_881 = " = null;";
  protected final String TEXT_882 = NL + "\t\t";
  protected final String TEXT_883 = " &= ~";
  protected final String TEXT_884 = "_ESETFLAG;";
  protected final String TEXT_885 = NL + "\t\t";
  protected final String TEXT_886 = "ESet = false;";
  protected final String TEXT_887 = NL + "\t\tif (eNotificationRequired())" + NL + "\t\t\teNotify(new ";
  protected final String TEXT_888 = "(this, ";
  protected final String TEXT_889 = ".UNSET, ";
  protected final String TEXT_890 = ", ";
  protected final String TEXT_891 = "isSetChange ? old";
  protected final String TEXT_892 = " : null";
  protected final String TEXT_893 = "old";
  protected final String TEXT_894 = ", null, ";
  protected final String TEXT_895 = "isSetChange";
  protected final String TEXT_896 = "old";
  protected final String TEXT_897 = "ESet";
  protected final String TEXT_898 = "));";
  protected final String TEXT_899 = NL + "\t\tif (";
  protected final String TEXT_900 = ") ";
  protected final String TEXT_901 = " |= ";
  protected final String TEXT_902 = "_EFLAG; else ";
  protected final String TEXT_903 = " &= ~";
  protected final String TEXT_904 = "_EFLAG;";
  protected final String TEXT_905 = NL + "\t\t";
  protected final String TEXT_906 = " = ";
  protected final String TEXT_907 = ";";
  protected final String TEXT_908 = NL + "\t\t";
  protected final String TEXT_909 = " &= ~";
  protected final String TEXT_910 = "_ESETFLAG;";
  protected final String TEXT_911 = NL + "\t\t";
  protected final String TEXT_912 = "ESet = false;";
  protected final String TEXT_913 = NL + "\t\tif (eNotificationRequired())" + NL + "\t\t\teNotify(new ";
  protected final String TEXT_914 = "(this, ";
  protected final String TEXT_915 = ".UNSET, ";
  protected final String TEXT_916 = ", ";
  protected final String TEXT_917 = "isSetChange ? old";
  protected final String TEXT_918 = " : ";
  protected final String TEXT_919 = "old";
  protected final String TEXT_920 = ", ";
  protected final String TEXT_921 = ", ";
  protected final String TEXT_922 = "isSetChange";
  protected final String TEXT_923 = "old";
  protected final String TEXT_924 = "ESet";
  protected final String TEXT_925 = "));";
  protected final String TEXT_926 = NL + "\t\t((";
  protected final String TEXT_927 = ".Internal)((";
  protected final String TEXT_928 = ".Internal.Wrapper)get";
  protected final String TEXT_929 = "()).featureMap()).clear(";
  protected final String TEXT_930 = ");";
  protected final String TEXT_931 = NL + "\t\t((";
  protected final String TEXT_932 = ".Internal)get";
  protected final String TEXT_933 = "()).clear(";
  protected final String TEXT_934 = ");";
  protected final String TEXT_935 = NL + "\t\t";
  protected final String TEXT_936 = NL + "\t\t// TODO: implement this method to unset the '";
  protected final String TEXT_937 = "' ";
  protected final String TEXT_938 = NL + "\t\t// Ensure that you remove @generated or mark it @generated NOT" + NL + "\t\tthrow new UnsupportedOperationException();";
  protected final String TEXT_939 = NL + "\t}" + NL;
  protected final String TEXT_940 = NL + "\t/**" + NL + "\t * Returns whether the value of the '{@link ";
  protected final String TEXT_941 = "#";
  protected final String TEXT_942 = " <em>";
  protected final String TEXT_943 = "</em>}' ";
  protected final String TEXT_944 = " is set.";
  protected final String TEXT_945 = NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @return whether the value of the '<em>";
  protected final String TEXT_946 = "</em>' ";
  protected final String TEXT_947 = " is set.";
  protected final String TEXT_948 = NL + "\t * @see #unset";
  protected final String TEXT_949 = "()";
  protected final String TEXT_950 = NL + "\t * @see #";
  protected final String TEXT_951 = "()";
  protected final String TEXT_952 = NL + "\t * @see #set";
  protected final String TEXT_953 = "(";
  protected final String TEXT_954 = ")";
  protected final String TEXT_955 = NL + "\t * @generated" + NL + "\t */";
  protected final String TEXT_956 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */";
  protected final String TEXT_957 = NL + "\tboolean isSet";
  protected final String TEXT_958 = "();" + NL;
  protected final String TEXT_959 = NL + "\tpublic boolean isSet";
  protected final String TEXT_960 = "_";
  protected final String TEXT_961 = "()" + NL + "\t{";
  protected final String TEXT_962 = NL + "\t\treturn eIsSet(";
  protected final String TEXT_963 = ");";
  protected final String TEXT_964 = NL + "\t\t";
  protected final String TEXT_965 = " ";
  protected final String TEXT_966 = " = (";
  protected final String TEXT_967 = ")eVirtualGet(";
  protected final String TEXT_968 = ");";
  protected final String TEXT_969 = NL + "\t\treturn ";
  protected final String TEXT_970 = " != null && ((";
  protected final String TEXT_971 = ".Unsettable";
  protected final String TEXT_972 = ")";
  protected final String TEXT_973 = ").isSet();";
  protected final String TEXT_974 = NL + "\t\treturn eVirtualIsSet(";
  protected final String TEXT_975 = ");";
  protected final String TEXT_976 = NL + "\t\treturn (";
  protected final String TEXT_977 = " & ";
  protected final String TEXT_978 = "_ESETFLAG) != 0;";
  protected final String TEXT_979 = NL + "\t\treturn ";
  protected final String TEXT_980 = "ESet;";
  protected final String TEXT_981 = NL + "\t\treturn !((";
  protected final String TEXT_982 = ".Internal)((";
  protected final String TEXT_983 = ".Internal.Wrapper)get";
  protected final String TEXT_984 = "()).featureMap()).isEmpty(";
  protected final String TEXT_985 = ");";
  protected final String TEXT_986 = NL + "\t\treturn !((";
  protected final String TEXT_987 = ".Internal)get";
  protected final String TEXT_988 = "()).isEmpty(";
  protected final String TEXT_989 = ");";
  protected final String TEXT_990 = NL + "\t\t";
  protected final String TEXT_991 = NL + "\t\t// TODO: implement this method to return whether the '";
  protected final String TEXT_992 = "' ";
  protected final String TEXT_993 = " is set" + NL + "\t\t// Ensure that you remove @generated or mark it @generated NOT" + NL + "\t\tthrow new UnsupportedOperationException();";
  protected final String TEXT_994 = NL + "\t}" + NL;
  protected final String TEXT_995 = NL + "\t/**";
  protected final String TEXT_996 = NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->";
  protected final String TEXT_997 = NL + "\t * <!-- begin-model-doc -->";
  protected final String TEXT_998 = NL + "\t * ";
  protected final String TEXT_999 = NL + "\t * @param ";
  protected final String TEXT_1000 = NL + "\t *   ";
  protected final String TEXT_1001 = NL + "\t * @param ";
  protected final String TEXT_1002 = " ";
  protected final String TEXT_1003 = NL + "\t * <!-- end-model-doc -->";
  protected final String TEXT_1004 = NL + "\t * @model ";
  protected final String TEXT_1005 = NL + "\t *        ";
  protected final String TEXT_1006 = NL + "\t * @model";
  protected final String TEXT_1007 = NL + "\t * @generated" + NL + "\t */";
  protected final String TEXT_1008 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */";
  protected final String TEXT_1009 = NL + "\t";
  protected final String TEXT_1010 = " ";
  protected final String TEXT_1011 = "(";
  protected final String TEXT_1012 = ")";
  protected final String TEXT_1013 = ";" + NL;
  protected final String TEXT_1014 = NL + "\tpublic ";
  protected final String TEXT_1015 = " ";
  protected final String TEXT_1016 = "(";
  protected final String TEXT_1017 = ")";
  protected final String TEXT_1018 = NL + "\t{";
  protected final String TEXT_1019 = NL + "\t\t";
  protected final String TEXT_1020 = NL + "\t\t// TODO: implement this method" + NL + "\t\t// -> specify the condition that violates the invariant" + NL + "\t\t// -> verify the details of the diagnostic, including severity and message" + NL + "\t\t// Ensure that you remove @generated or mark it @generated NOT" + NL + "\t\tif (false)" + NL + "\t\t{" + NL + "\t\t\tif (";
  protected final String TEXT_1021 = " != null)" + NL + "\t\t\t{" + NL + "\t\t\t\t";
  protected final String TEXT_1022 = ".add" + NL + "\t\t\t\t\t(new ";
  protected final String TEXT_1023 = NL + "\t\t\t\t\t\t(";
  protected final String TEXT_1024 = ".ERROR," + NL + "\t\t\t\t\t\t ";
  protected final String TEXT_1025 = ".DIAGNOSTIC_SOURCE," + NL + "\t\t\t\t\t\t ";
  protected final String TEXT_1026 = ".";
  protected final String TEXT_1027 = "," + NL + "\t\t\t\t\t\t ";
  protected final String TEXT_1028 = ".INSTANCE.getString(\"_UI_GenericInvariant_diagnostic\", new Object[] { \"";
  protected final String TEXT_1029 = "\", ";
  protected final String TEXT_1030 = ".getObjectLabel(this, ";
  protected final String TEXT_1031 = ") }),";
  protected final String TEXT_1032 = NL + "\t\t\t\t\t\t new Object [] { this }));" + NL + "\t\t\t}" + NL + "\t\t\treturn false;" + NL + "\t\t}" + NL + "\t\treturn true;";
  protected final String TEXT_1033 = NL + "\t\t// TODO: implement this method" + NL + "\t\t// Ensure that you remove @generated or mark it @generated NOT" + NL + "\t\tthrow new UnsupportedOperationException();";
  protected final String TEXT_1034 = NL + "\t}" + NL;
  protected final String TEXT_1035 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */";
  protected final String TEXT_1036 = NL + "\t@SuppressWarnings(\"unchecked\")";
  protected final String TEXT_1037 = NL + "\t@Override";
  protected final String TEXT_1038 = NL + "\tpublic ";
  protected final String TEXT_1039 = " eInverseAdd(";
  protected final String TEXT_1040 = " otherEnd, int featureID, ";
  protected final String TEXT_1041 = " msgs)" + NL + "\t{" + NL + "\t\tswitch (featureID";
  protected final String TEXT_1042 = ")" + NL + "\t\t{";
  protected final String TEXT_1043 = NL + "\t\t\tcase ";
  protected final String TEXT_1044 = ":";
  protected final String TEXT_1045 = NL + "\t\t\t\treturn ((";
  protected final String TEXT_1046 = "(";
  protected final String TEXT_1047 = ".InternalMapView";
  protected final String TEXT_1048 = ")";
  protected final String TEXT_1049 = "()).eMap()).basicAdd(otherEnd, msgs);";
  protected final String TEXT_1050 = NL + "\t\t\t\treturn (";
  protected final String TEXT_1051 = "()).basicAdd(otherEnd, msgs);";
  protected final String TEXT_1052 = NL + "\t\t\t\tif (eInternalContainer() != null)" + NL + "\t\t\t\t\tmsgs = eBasicRemoveFromContainer(msgs);";
  protected final String TEXT_1053 = NL + "\t\t\t\treturn basicSet";
  protected final String TEXT_1054 = "((";
  protected final String TEXT_1055 = ")otherEnd, msgs);";
  protected final String TEXT_1056 = NL + "\t\t\t\treturn eBasicSetContainer(otherEnd, ";
  protected final String TEXT_1057 = ", msgs);";
  protected final String TEXT_1058 = NL + "\t\t\t\t";
  protected final String TEXT_1059 = " ";
  protected final String TEXT_1060 = " = (";
  protected final String TEXT_1061 = ")eVirtualGet(";
  protected final String TEXT_1062 = ");";
  protected final String TEXT_1063 = NL + "\t\t\t\t";
  protected final String TEXT_1064 = " ";
  protected final String TEXT_1065 = " = ";
  protected final String TEXT_1066 = "basicGet";
  protected final String TEXT_1067 = "();";
  protected final String TEXT_1068 = NL + "\t\t\t\tif (";
  protected final String TEXT_1069 = " != null)";
  protected final String TEXT_1070 = NL + "\t\t\t\t\tmsgs = ((";
  protected final String TEXT_1071 = ")";
  protected final String TEXT_1072 = ").eInverseRemove(this, EOPPOSITE_FEATURE_BASE - ";
  protected final String TEXT_1073 = ", null, msgs);";
  protected final String TEXT_1074 = NL + "\t\t\t\t\tmsgs = ((";
  protected final String TEXT_1075 = ")";
  protected final String TEXT_1076 = ").eInverseRemove(this, ";
  protected final String TEXT_1077 = ", ";
  protected final String TEXT_1078 = ".class, msgs);";
  protected final String TEXT_1079 = NL + "\t\t\t\treturn basicSet";
  protected final String TEXT_1080 = "((";
  protected final String TEXT_1081 = ")otherEnd, msgs);";
  protected final String TEXT_1082 = NL + "\t\t}";
  protected final String TEXT_1083 = NL + "\t\treturn super.eInverseAdd(otherEnd, featureID, msgs);";
  protected final String TEXT_1084 = NL + "\t\treturn eDynamicInverseAdd(otherEnd, featureID, msgs);";
  protected final String TEXT_1085 = NL + "\t}" + NL;
  protected final String TEXT_1086 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */";
  protected final String TEXT_1087 = NL + "\t@Override";
  protected final String TEXT_1088 = NL + "\tpublic ";
  protected final String TEXT_1089 = " eInverseRemove(";
  protected final String TEXT_1090 = " otherEnd, int featureID, ";
  protected final String TEXT_1091 = " msgs)" + NL + "\t{" + NL + "\t\tswitch (featureID";
  protected final String TEXT_1092 = ")" + NL + "\t\t{";
  protected final String TEXT_1093 = NL + "\t\t\tcase ";
  protected final String TEXT_1094 = ":";
  protected final String TEXT_1095 = NL + "\t\t\t\treturn ((";
  protected final String TEXT_1096 = ")((";
  protected final String TEXT_1097 = ".InternalMapView";
  protected final String TEXT_1098 = ")";
  protected final String TEXT_1099 = "()).eMap()).basicRemove(otherEnd, msgs);";
  protected final String TEXT_1100 = NL + "\t\t\t\treturn ((";
  protected final String TEXT_1101 = ")((";
  protected final String TEXT_1102 = ".Internal.Wrapper)";
  protected final String TEXT_1103 = "()).featureMap()).basicRemove(otherEnd, msgs);";
  protected final String TEXT_1104 = NL + "\t\t\t\treturn ((";
  protected final String TEXT_1105 = ")";
  protected final String TEXT_1106 = "()).basicRemove(otherEnd, msgs);";
  protected final String TEXT_1107 = NL + "\t\t\t\treturn eBasicSetContainer(null, ";
  protected final String TEXT_1108 = ", msgs);";
  protected final String TEXT_1109 = NL + "\t\t\t\treturn basicUnset";
  protected final String TEXT_1110 = "(msgs);";
  protected final String TEXT_1111 = NL + "\t\t\t\treturn basicSet";
  protected final String TEXT_1112 = "(null, msgs);";
  protected final String TEXT_1113 = NL + "\t\t}";
  protected final String TEXT_1114 = NL + "\t\treturn super.eInverseRemove(otherEnd, featureID, msgs);";
  protected final String TEXT_1115 = NL + "\t\treturn eDynamicInverseRemove(otherEnd, featureID, msgs);";
  protected final String TEXT_1116 = NL + "\t}" + NL;
  protected final String TEXT_1117 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */";
  protected final String TEXT_1118 = NL + "\t@Override";
  protected final String TEXT_1119 = NL + "\tpublic ";
  protected final String TEXT_1120 = " eBasicRemoveFromContainerFeature(";
  protected final String TEXT_1121 = " msgs)" + NL + "\t{" + NL + "\t\tswitch (eContainerFeatureID";
  protected final String TEXT_1122 = ")" + NL + "\t\t{";
  protected final String TEXT_1123 = NL + "\t\t\tcase ";
  protected final String TEXT_1124 = ":" + NL + "\t\t\t\treturn eInternalContainer().eInverseRemove(this, ";
  protected final String TEXT_1125 = ", ";
  protected final String TEXT_1126 = ".class, msgs);";
  protected final String TEXT_1127 = NL + "\t\t}";
  protected final String TEXT_1128 = NL + "\t\treturn super.eBasicRemoveFromContainerFeature(msgs);";
  protected final String TEXT_1129 = NL + "\t\treturn eDynamicBasicRemoveFromContainer(msgs);";
  protected final String TEXT_1130 = NL + "\t}" + NL;
  protected final String TEXT_1131 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */";
  protected final String TEXT_1132 = NL + "\t@Override";
  protected final String TEXT_1133 = NL + "\tpublic Object eGet(int featureID, boolean resolve, boolean coreType)" + NL + "\t{" + NL + "\t\tswitch (featureID";
  protected final String TEXT_1134 = ")" + NL + "\t\t{";
  protected final String TEXT_1135 = NL + "\t\t\tcase ";
  protected final String TEXT_1136 = ":";
  protected final String TEXT_1137 = NL + "\t\t\t\treturn ";
  protected final String TEXT_1138 = "() ? Boolean.TRUE : Boolean.FALSE;";
  protected final String TEXT_1139 = NL + "\t\t\t\treturn new ";
  protected final String TEXT_1140 = "(";
  protected final String TEXT_1141 = "());";
  protected final String TEXT_1142 = NL + "\t\t\t\tif (resolve) return ";
  protected final String TEXT_1143 = "();" + NL + "\t\t\t\treturn basicGet";
  protected final String TEXT_1144 = "();";
  protected final String TEXT_1145 = NL + "\t\t\t\tif (coreType) return ((";
  protected final String TEXT_1146 = ".InternalMapView";
  protected final String TEXT_1147 = ")";
  protected final String TEXT_1148 = "()).eMap();" + NL + "\t\t\t\telse return ";
  protected final String TEXT_1149 = "();";
  protected final String TEXT_1150 = NL + "\t\t\t\tif (coreType) return ";
  protected final String TEXT_1151 = "();" + NL + "\t\t\t\telse return ";
  protected final String TEXT_1152 = "().map();";
  protected final String TEXT_1153 = NL + "\t\t\t\tif (coreType) return ((";
  protected final String TEXT_1154 = ".Internal.Wrapper)";
  protected final String TEXT_1155 = "()).featureMap();" + NL + "\t\t\t\treturn ";
  protected final String TEXT_1156 = "();";
  protected final String TEXT_1157 = NL + "\t\t\t\tif (coreType) return ";
  protected final String TEXT_1158 = "();" + NL + "\t\t\t\treturn ((";
  protected final String TEXT_1159 = ".Internal)";
  protected final String TEXT_1160 = "()).getWrapper();";
  protected final String TEXT_1161 = NL + "\t\t\t\treturn ";
  protected final String TEXT_1162 = "();";
  protected final String TEXT_1163 = NL + "\t\t}";
  protected final String TEXT_1164 = NL + "\t\treturn super.eGet(featureID, resolve, coreType);";
  protected final String TEXT_1165 = NL + "\t\treturn eDynamicGet(featureID, resolve, coreType);";
  protected final String TEXT_1166 = NL + "\t}" + NL;
  protected final String TEXT_1167 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */";
  protected final String TEXT_1168 = NL + "\t@SuppressWarnings(\"unchecked\")";
  protected final String TEXT_1169 = NL + "\t@Override";
  protected final String TEXT_1170 = NL + "\tpublic void eSet(int featureID, Object newValue)" + NL + "\t{" + NL + "\t\tswitch (featureID";
  protected final String TEXT_1171 = ")" + NL + "\t\t{";
  protected final String TEXT_1172 = NL + "\t\t\tcase ";
  protected final String TEXT_1173 = ":";
  protected final String TEXT_1174 = NL + "\t\t\t\t((";
  protected final String TEXT_1175 = ".Internal)((";
  protected final String TEXT_1176 = ".Internal.Wrapper)";
  protected final String TEXT_1177 = "()).featureMap()).set(newValue);";
  protected final String TEXT_1178 = NL + "\t\t\t\t((";
  protected final String TEXT_1179 = ".Internal)";
  protected final String TEXT_1180 = "()).set(newValue);";
  protected final String TEXT_1181 = NL + "\t\t\t\t((";
  protected final String TEXT_1182 = ".Setting)((";
  protected final String TEXT_1183 = ".InternalMapView";
  protected final String TEXT_1184 = ")";
  protected final String TEXT_1185 = "()).eMap()).set(newValue);";
  protected final String TEXT_1186 = NL + "\t\t\t\t((";
  protected final String TEXT_1187 = ".Setting)";
  protected final String TEXT_1188 = "()).set(newValue);";
  protected final String TEXT_1189 = NL + "\t\t\t\t";
  protected final String TEXT_1190 = "().clear();" + NL + "\t\t\t\t";
  protected final String TEXT_1191 = "().addAll((";
  protected final String TEXT_1192 = "<? extends ";
  protected final String TEXT_1193 = ">";
  protected final String TEXT_1194 = ")newValue);";
  protected final String TEXT_1195 = NL + "\t\t\t\tset";
  protected final String TEXT_1196 = "(((";
  protected final String TEXT_1197 = ")newValue).";
  protected final String TEXT_1198 = "());";
  protected final String TEXT_1199 = NL + "\t\t\t\tset";
  protected final String TEXT_1200 = "(";
  protected final String TEXT_1201 = "(";
  protected final String TEXT_1202 = ")";
  protected final String TEXT_1203 = "newValue);";
  protected final String TEXT_1204 = NL + "\t\t\t\treturn;";
  protected final String TEXT_1205 = NL + "\t\t}";
  protected final String TEXT_1206 = NL + "\t\tsuper.eSet(featureID, newValue);";
  protected final String TEXT_1207 = NL + "\t\teDynamicSet(featureID, newValue);";
  protected final String TEXT_1208 = NL + "\t}" + NL;
  protected final String TEXT_1209 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */";
  protected final String TEXT_1210 = NL + "\t@Override";
  protected final String TEXT_1211 = NL + "\tpublic void eUnset(int featureID)" + NL + "\t{" + NL + "\t\tswitch (featureID";
  protected final String TEXT_1212 = ")" + NL + "\t\t{";
  protected final String TEXT_1213 = NL + "\t\t\tcase ";
  protected final String TEXT_1214 = ":";
  protected final String TEXT_1215 = NL + "\t\t\t\t((";
  protected final String TEXT_1216 = ".Internal.Wrapper)";
  protected final String TEXT_1217 = "()).featureMap().clear();";
  protected final String TEXT_1218 = NL + "\t\t\t\t";
  protected final String TEXT_1219 = "().clear();";
  protected final String TEXT_1220 = NL + "\t\t\t\tunset";
  protected final String TEXT_1221 = "();";
  protected final String TEXT_1222 = NL + "\t\t\t\tset";
  protected final String TEXT_1223 = "((";
  protected final String TEXT_1224 = ")null);";
  protected final String TEXT_1225 = NL + "\t\t\t\tset";
  protected final String TEXT_1226 = "(";
  protected final String TEXT_1227 = ");";
  protected final String TEXT_1228 = NL + "\t\t\t\treturn;";
  protected final String TEXT_1229 = NL + "\t\t}";
  protected final String TEXT_1230 = NL + "\t\tsuper.eUnset(featureID);";
  protected final String TEXT_1231 = NL + "\t\teDynamicUnset(featureID);";
  protected final String TEXT_1232 = NL + "\t}" + NL;
  protected final String TEXT_1233 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */";
  protected final String TEXT_1234 = NL + "\t@SuppressWarnings(\"unchecked\")";
  protected final String TEXT_1235 = NL + "\t@Override";
  protected final String TEXT_1236 = NL + "\tpublic boolean eIsSet(int featureID)" + NL + "\t{" + NL + "\t\tswitch (featureID";
  protected final String TEXT_1237 = ")" + NL + "\t\t{";
  protected final String TEXT_1238 = NL + "\t\t\tcase ";
  protected final String TEXT_1239 = ":";
  protected final String TEXT_1240 = NL + "\t\t\t\treturn !((";
  protected final String TEXT_1241 = ".Internal.Wrapper)";
  protected final String TEXT_1242 = "()).featureMap().isEmpty();";
  protected final String TEXT_1243 = NL + "\t\t\t\treturn ";
  protected final String TEXT_1244 = " != null && !";
  protected final String TEXT_1245 = ".featureMap().isEmpty();";
  protected final String TEXT_1246 = NL + "\t\t\t\treturn ";
  protected final String TEXT_1247 = " != null && !";
  protected final String TEXT_1248 = ".isEmpty();";
  protected final String TEXT_1249 = NL + "\t\t\t\t";
  protected final String TEXT_1250 = " ";
  protected final String TEXT_1251 = " = (";
  protected final String TEXT_1252 = ")eVirtualGet(";
  protected final String TEXT_1253 = ");" + NL + "\t\t\t\treturn ";
  protected final String TEXT_1254 = " != null && !";
  protected final String TEXT_1255 = ".isEmpty();";
  protected final String TEXT_1256 = NL + "\t\t\t\treturn !";
  protected final String TEXT_1257 = "().isEmpty();";
  protected final String TEXT_1258 = NL + "\t\t\t\treturn isSet";
  protected final String TEXT_1259 = "();";
  protected final String TEXT_1260 = NL + "\t\t\t\treturn ";
  protected final String TEXT_1261 = " != null;";
  protected final String TEXT_1262 = NL + "\t\t\t\treturn eVirtualGet(";
  protected final String TEXT_1263 = ") != null;";
  protected final String TEXT_1264 = NL + "\t\t\t\treturn basicGet";
  protected final String TEXT_1265 = "() != null;";
  protected final String TEXT_1266 = NL + "\t\t\t\treturn ";
  protected final String TEXT_1267 = " != null;";
  protected final String TEXT_1268 = NL + "\t\t\t\treturn eVirtualGet(";
  protected final String TEXT_1269 = ") != null;";
  protected final String TEXT_1270 = NL + "\t\t\t\treturn ";
  protected final String TEXT_1271 = "() != null;";
  protected final String TEXT_1272 = NL + "\t\t\t\treturn ((";
  protected final String TEXT_1273 = " & ";
  protected final String TEXT_1274 = "_EFLAG) != 0) != ";
  protected final String TEXT_1275 = ";";
  protected final String TEXT_1276 = NL + "\t\t\t\treturn ";
  protected final String TEXT_1277 = " != ";
  protected final String TEXT_1278 = ";";
  protected final String TEXT_1279 = NL + "\t\t\t\treturn eVirtualGet(";
  protected final String TEXT_1280 = ", ";
  protected final String TEXT_1281 = ") != ";
  protected final String TEXT_1282 = ";";
  protected final String TEXT_1283 = NL + "\t\t\t\treturn ";
  protected final String TEXT_1284 = "() != ";
  protected final String TEXT_1285 = ";";
  protected final String TEXT_1286 = NL + "\t\t\t\treturn ";
  protected final String TEXT_1287 = " == null ? ";
  protected final String TEXT_1288 = " != null : !";
  protected final String TEXT_1289 = ".equals(";
  protected final String TEXT_1290 = ");";
  protected final String TEXT_1291 = NL + "\t\t\t\t";
  protected final String TEXT_1292 = " ";
  protected final String TEXT_1293 = " = (";
  protected final String TEXT_1294 = ")eVirtualGet(";
  protected final String TEXT_1295 = ", ";
  protected final String TEXT_1296 = ");" + NL + "\t\t\t\treturn ";
  protected final String TEXT_1297 = " == null ? ";
  protected final String TEXT_1298 = " != null : !";
  protected final String TEXT_1299 = ".equals(";
  protected final String TEXT_1300 = ");";
  protected final String TEXT_1301 = NL + "\t\t\t\treturn ";
  protected final String TEXT_1302 = " == null ? ";
  protected final String TEXT_1303 = "() != null : !";
  protected final String TEXT_1304 = ".equals(";
  protected final String TEXT_1305 = "());";
  protected final String TEXT_1306 = NL + "\t\t}";
  protected final String TEXT_1307 = NL + "\t\treturn super.eIsSet(featureID);";
  protected final String TEXT_1308 = NL + "\t\treturn eDynamicIsSet(featureID);";
  protected final String TEXT_1309 = NL + "\t}" + NL;
  protected final String TEXT_1310 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */";
  protected final String TEXT_1311 = NL + "\t@Override";
  protected final String TEXT_1312 = NL + "\tpublic int eBaseStructuralFeatureID(int derivedFeatureID, Class";
  protected final String TEXT_1313 = " baseClass)" + NL + "\t{";
  protected final String TEXT_1314 = NL + "\t\tif (baseClass == ";
  protected final String TEXT_1315 = ".class)" + NL + "\t\t{" + NL + "\t\t\tswitch (derivedFeatureID";
  protected final String TEXT_1316 = ")" + NL + "\t\t\t{";
  protected final String TEXT_1317 = NL + "\t\t\t\tcase ";
  protected final String TEXT_1318 = ": return ";
  protected final String TEXT_1319 = ";";
  protected final String TEXT_1320 = NL + "\t\t\t\tdefault: return -1;" + NL + "\t\t\t}" + NL + "\t\t}";
  protected final String TEXT_1321 = NL + "\t\treturn super.eBaseStructuralFeatureID(derivedFeatureID, baseClass);" + NL + "\t}";
  protected final String TEXT_1322 = NL + NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */";
  protected final String TEXT_1323 = NL + "\t@Override";
  protected final String TEXT_1324 = NL + "\tpublic int eDerivedStructuralFeatureID(int baseFeatureID, Class";
  protected final String TEXT_1325 = " baseClass)" + NL + "\t{";
  protected final String TEXT_1326 = NL + "\t\tif (baseClass == ";
  protected final String TEXT_1327 = ".class)" + NL + "\t\t{" + NL + "\t\t\tswitch (baseFeatureID)" + NL + "\t\t\t{";
  protected final String TEXT_1328 = NL + "\t\t\t\tcase ";
  protected final String TEXT_1329 = ": return ";
  protected final String TEXT_1330 = ";";
  protected final String TEXT_1331 = NL + "\t\t\t\tdefault: return -1;" + NL + "\t\t\t}" + NL + "\t\t}";
  protected final String TEXT_1332 = NL + "\t\tif (baseClass == ";
  protected final String TEXT_1333 = ".class)" + NL + "\t\t{" + NL + "\t\t\tswitch (baseFeatureID";
  protected final String TEXT_1334 = ")" + NL + "\t\t\t{";
  protected final String TEXT_1335 = NL + "\t\t\t\tcase ";
  protected final String TEXT_1336 = ": return ";
  protected final String TEXT_1337 = ";";
  protected final String TEXT_1338 = NL + "\t\t\t\tdefault: return -1;" + NL + "\t\t\t}" + NL + "\t\t}";
  protected final String TEXT_1339 = NL + "\t\treturn super.eDerivedStructuralFeatureID(baseFeatureID, baseClass);" + NL + "\t}" + NL;
  protected final String TEXT_1340 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */";
  protected final String TEXT_1341 = NL + "\t@Override";
  protected final String TEXT_1342 = NL + "\tprotected Object[] eVirtualValues()" + NL + "\t{" + NL + "\t\treturn ";
  protected final String TEXT_1343 = ";" + NL + "\t}" + NL + "" + NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */";
  protected final String TEXT_1344 = NL + "\t@Override";
  protected final String TEXT_1345 = NL + "\tprotected void eSetVirtualValues(Object[] newValues)" + NL + "\t{" + NL + "\t\t";
  protected final String TEXT_1346 = " = newValues;" + NL + "\t}" + NL;
  protected final String TEXT_1347 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */";
  protected final String TEXT_1348 = NL + "\t@Override";
  protected final String TEXT_1349 = NL + "\tprotected int eVirtualIndexBits(int offset)" + NL + "\t{" + NL + "\t\tswitch (offset)" + NL + "\t\t{";
  protected final String TEXT_1350 = NL + "\t\t\tcase ";
  protected final String TEXT_1351 = " :" + NL + "\t\t\t\treturn ";
  protected final String TEXT_1352 = ";";
  protected final String TEXT_1353 = NL + "\t\t\tdefault :" + NL + "\t\t\t\tthrow new IndexOutOfBoundsException();" + NL + "\t\t}" + NL + "\t}" + NL + "" + NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */";
  protected final String TEXT_1354 = NL + "\t@Override";
  protected final String TEXT_1355 = NL + "\tprotected void eSetVirtualIndexBits(int offset, int newIndexBits)" + NL + "\t{" + NL + "\t\tswitch (offset)" + NL + "\t\t{";
  protected final String TEXT_1356 = NL + "\t\t\tcase ";
  protected final String TEXT_1357 = " :" + NL + "\t\t\t\t";
  protected final String TEXT_1358 = " = newIndexBits;" + NL + "\t\t\t\tbreak;";
  protected final String TEXT_1359 = NL + "\t\t\tdefault :" + NL + "\t\t\t\tthrow new IndexOutOfBoundsException();" + NL + "\t\t}" + NL + "\t}" + NL;
  protected final String TEXT_1360 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */";
  protected final String TEXT_1361 = NL + "\t@Override";
  protected final String TEXT_1362 = NL + "\tpublic String toString()" + NL + "\t{" + NL + "\t\tif (eIsProxy()) return super.toString();" + NL + "" + NL + "\t\tStringBuffer result = new StringBuffer(super.toString());";
  protected final String TEXT_1363 = NL + "\t\tresult.append(\" (";
  protected final String TEXT_1364 = ": \");";
  protected final String TEXT_1365 = NL + "\t\tresult.append(\", ";
  protected final String TEXT_1366 = ": \");";
  protected final String TEXT_1367 = NL + "\t\tif (eVirtualIsSet(";
  protected final String TEXT_1368 = ")) result.append(eVirtualGet(";
  protected final String TEXT_1369 = ")); else result.append(\"<unset>\");";
  protected final String TEXT_1370 = NL + "\t\tif (";
  protected final String TEXT_1371 = "(";
  protected final String TEXT_1372 = " & ";
  protected final String TEXT_1373 = "_ESETFLAG) != 0";
  protected final String TEXT_1374 = "ESet";
  protected final String TEXT_1375 = ") result.append((";
  protected final String TEXT_1376 = " & ";
  protected final String TEXT_1377 = "_EFLAG) != 0); else result.append(\"<unset>\");";
  protected final String TEXT_1378 = NL + "\t\tif (";
  protected final String TEXT_1379 = "(";
  protected final String TEXT_1380 = " & ";
  protected final String TEXT_1381 = "_ESETFLAG) != 0";
  protected final String TEXT_1382 = "ESet";
  protected final String TEXT_1383 = ") result.append(";
  protected final String TEXT_1384 = "); else result.append(\"<unset>\");";
  protected final String TEXT_1385 = NL + "\t\tresult.append(eVirtualGet(";
  protected final String TEXT_1386 = ", ";
  protected final String TEXT_1387 = "));";
  protected final String TEXT_1388 = NL + "\t\tresult.append((";
  protected final String TEXT_1389 = " & ";
  protected final String TEXT_1390 = "_EFLAG) != 0);";
  protected final String TEXT_1391 = NL + "\t\tresult.append(";
  protected final String TEXT_1392 = ");";
  protected final String TEXT_1393 = NL + "\t\tresult.append(')');" + NL + "\t\treturn result.toString();" + NL + "\t}" + NL;
  protected final String TEXT_1394 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */" + NL + "\tprotected int hash = -1;" + NL + "" + NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */" + NL + "\tpublic int getHash()" + NL + "\t{" + NL + "\t\tif (hash == -1)" + NL + "\t\t{" + NL + "\t\t\t";
  protected final String TEXT_1395 = " theKey = getKey();" + NL + "\t\t\thash = (theKey == null ? 0 : theKey.hashCode());" + NL + "\t\t}" + NL + "\t\treturn hash;" + NL + "\t}" + NL + "" + NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */" + NL + "\tpublic void setHash(int hash)" + NL + "\t{" + NL + "\t\tthis.hash = hash;" + NL + "\t}" + NL + "" + NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */" + NL + "\tpublic ";
  protected final String TEXT_1396 = " getKey()" + NL + "\t{";
  protected final String TEXT_1397 = NL + "\t\treturn new ";
  protected final String TEXT_1398 = "(getTypedKey());";
  protected final String TEXT_1399 = NL + "\t\treturn getTypedKey();";
  protected final String TEXT_1400 = NL + "\t}" + NL + "" + NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */" + NL + "\tpublic void setKey(";
  protected final String TEXT_1401 = " key)" + NL + "\t{";
  protected final String TEXT_1402 = NL + "\t\tgetTypedKey().addAll(";
  protected final String TEXT_1403 = "(";
  protected final String TEXT_1404 = ")";
  protected final String TEXT_1405 = "key);";
  protected final String TEXT_1406 = NL + "\t\tsetTypedKey(key);";
  protected final String TEXT_1407 = NL + "\t\tsetTypedKey(((";
  protected final String TEXT_1408 = ")key).";
  protected final String TEXT_1409 = "());";
  protected final String TEXT_1410 = NL + "\t\tsetTypedKey((";
  protected final String TEXT_1411 = ")key);";
  protected final String TEXT_1412 = NL + "\t}" + NL + "" + NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */" + NL + "\tpublic ";
  protected final String TEXT_1413 = " getValue()" + NL + "\t{";
  protected final String TEXT_1414 = NL + "\t\treturn new ";
  protected final String TEXT_1415 = "(getTypedValue());";
  protected final String TEXT_1416 = NL + "\t\treturn getTypedValue();";
  protected final String TEXT_1417 = NL + "\t}" + NL + "" + NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */" + NL + "\tpublic ";
  protected final String TEXT_1418 = " setValue(";
  protected final String TEXT_1419 = " value)" + NL + "\t{" + NL + "\t\t";
  protected final String TEXT_1420 = " oldValue = getValue();";
  protected final String TEXT_1421 = NL + "\t\tgetTypedValue().clear();" + NL + "\t\tgetTypedValue().addAll(";
  protected final String TEXT_1422 = "(";
  protected final String TEXT_1423 = ")";
  protected final String TEXT_1424 = "value);";
  protected final String TEXT_1425 = NL + "\t\tsetTypedValue(value);";
  protected final String TEXT_1426 = NL + "\t\tsetTypedValue(((";
  protected final String TEXT_1427 = ")value).";
  protected final String TEXT_1428 = "());";
  protected final String TEXT_1429 = NL + "\t\tsetTypedValue((";
  protected final String TEXT_1430 = ")value);";
  protected final String TEXT_1431 = NL + "\t\treturn oldValue;" + NL + "\t}" + NL + "" + NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */";
  protected final String TEXT_1432 = NL + "\t@SuppressWarnings(\"unchecked\")";
  protected final String TEXT_1433 = NL + "\tpublic ";
  protected final String TEXT_1434 = " getEMap()" + NL + "\t{" + NL + "\t\t";
  protected final String TEXT_1435 = " container = eContainer();" + NL + "\t\treturn container == null ? null : (";
  protected final String TEXT_1436 = ")container.eGet(eContainmentFeature());" + NL + "\t}" + NL;
  protected final String TEXT_1437 = NL + "} //";
  protected final String TEXT_1438 = NL;

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
    final String negativeOffsetCorrection = genClass.hasOffsetCorrection() ? " - " + genClass.getOffsetCorrectionField(null) : "";
    final String positiveOffsetCorrection = genClass.hasOffsetCorrection() ? " + " + genClass.getOffsetCorrectionField(null) : "";
    stringBuffer.append(TEXT_1);
    stringBuffer.append(TEXT_2);
    {GenBase copyrightHolder = argument instanceof GenBase ? (GenBase)argument : argument instanceof Object[] && ((Object[])argument)[0] instanceof GenBase ? (GenBase)((Object[])argument)[0] : null;
    if (copyrightHolder != null && copyrightHolder.hasCopyright()) {
    stringBuffer.append(TEXT_3);
    stringBuffer.append(copyrightHolder.getCopyright(copyrightHolder.getGenModel().getIndentation(stringBuffer)));
    } else {
    stringBuffer.append(TEXT_4);
    }}
    stringBuffer.append(TEXT_5);
    stringBuffer.append("$");
    stringBuffer.append(TEXT_6);
    stringBuffer.append("$");
    stringBuffer.append(TEXT_7);
    if (isInterface) {
    stringBuffer.append(TEXT_8);
    stringBuffer.append(genPackage.getInterfacePackageName());
    stringBuffer.append(TEXT_9);
    } else {
    stringBuffer.append(TEXT_10);
    stringBuffer.append(genPackage.getClassPackageName());
    stringBuffer.append(TEXT_11);
    }
    stringBuffer.append(TEXT_12);
    genModel.markImportLocation(stringBuffer, genPackage);
    stringBuffer.append(TEXT_13);
    if (isInterface) {
    stringBuffer.append(TEXT_14);
    stringBuffer.append(genClass.getFormattedName());
    stringBuffer.append(TEXT_15);
    if (genClass.hasDocumentation()) {
    stringBuffer.append(TEXT_16);
    stringBuffer.append(genClass.getDocumentation(genModel.getIndentation(stringBuffer)));
    stringBuffer.append(TEXT_17);
    }
    stringBuffer.append(TEXT_18);
    if (!genClass.getGenFeatures().isEmpty()) {
    stringBuffer.append(TEXT_19);
    for (GenFeature genFeature : genClass.getGenFeatures()) {
    if (!genFeature.isSuppressedGetVisibility()) {
    stringBuffer.append(TEXT_20);
    stringBuffer.append(genClass.getQualifiedInterfaceName());
    stringBuffer.append(TEXT_21);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_22);
    stringBuffer.append(genFeature.getFormattedName());
    stringBuffer.append(TEXT_23);
    }
    }
    stringBuffer.append(TEXT_24);
    }
    stringBuffer.append(TEXT_25);
    if (!genModel.isSuppressEMFMetaData()) {
    stringBuffer.append(TEXT_26);
    stringBuffer.append(genPackage.getQualifiedPackageInterfaceName());
    stringBuffer.append(TEXT_27);
    stringBuffer.append(genClass.getClassifierAccessorName());
    stringBuffer.append(TEXT_28);
    }
    if (!genModel.isSuppressEMFModelTags()) { boolean first = true; for (StringTokenizer stringTokenizer = new StringTokenizer(genClass.getModelInfo(), "\n\r"); stringTokenizer.hasMoreTokens(); ) { String modelInfo = stringTokenizer.nextToken(); if (first) { first = false;
    stringBuffer.append(TEXT_29);
    stringBuffer.append(modelInfo);
    } else {
    stringBuffer.append(TEXT_30);
    stringBuffer.append(modelInfo);
    }} if (first) {
    stringBuffer.append(TEXT_31);
    }}
    if (genClass.needsRootExtendsInterfaceExtendsTag()) {
    stringBuffer.append(TEXT_32);
    stringBuffer.append(genModel.getImportedName(genModel.getRootExtendsInterface()));
    }
    stringBuffer.append(TEXT_33);
    } else {
    stringBuffer.append(TEXT_34);
    stringBuffer.append(genClass.getFormattedName());
    stringBuffer.append(TEXT_35);
    if (!genClass.getImplementedGenFeatures().isEmpty()) {
    stringBuffer.append(TEXT_36);
    for (GenFeature genFeature : genClass.getImplementedGenFeatures()) {
    stringBuffer.append(TEXT_37);
    stringBuffer.append(genClass.getQualifiedClassName());
    stringBuffer.append(TEXT_38);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_39);
    stringBuffer.append(genFeature.getFormattedName());
    stringBuffer.append(TEXT_40);
    }
    stringBuffer.append(TEXT_41);
    }
    stringBuffer.append(TEXT_42);
    }
    if (isImplementation) {
    stringBuffer.append(TEXT_43);
    if (genClass.isAbstract()) {
    stringBuffer.append(TEXT_44);
    }
    stringBuffer.append(TEXT_45);
    stringBuffer.append(genClass.getClassName());
    stringBuffer.append(genClass.getTypeParameters().trim());
    stringBuffer.append(genClass.getClassExtends());
    stringBuffer.append(genClass.getClassImplements());
    } else {
    stringBuffer.append(TEXT_46);
    stringBuffer.append(genClass.getInterfaceName());
    stringBuffer.append(genClass.getTypeParameters().trim());
    stringBuffer.append(genClass.getInterfaceExtends());
    }
    stringBuffer.append(TEXT_47);
    if (genModel.hasCopyrightField()) {
    stringBuffer.append(TEXT_48);
    stringBuffer.append(publicStaticFinalFlag);
    stringBuffer.append(genModel.getImportedName("java.lang.String"));
    stringBuffer.append(TEXT_49);
    stringBuffer.append(genModel.getCopyrightFieldLiteral());
    stringBuffer.append(TEXT_50);
    stringBuffer.append(genModel.getNonNLS());
    stringBuffer.append(TEXT_51);
    }
    if (isImplementation && genModel.getDriverNumber() != null) {
    stringBuffer.append(TEXT_52);
    stringBuffer.append(genModel.getImportedName("java.lang.String"));
    stringBuffer.append(TEXT_53);
    stringBuffer.append(genModel.getDriverNumber());
    stringBuffer.append(TEXT_54);
    stringBuffer.append(genModel.getNonNLS());
    stringBuffer.append(TEXT_55);
    }
    if (isImplementation && genClass.isJavaIOSerializable()) {
    stringBuffer.append(TEXT_56);
    }
    if (isImplementation && genModel.isVirtualDelegation()) { String eVirtualValuesField = genClass.getEVirtualValuesField();
    if (eVirtualValuesField != null) {
    stringBuffer.append(TEXT_57);
    stringBuffer.append(eVirtualValuesField);
    stringBuffer.append(TEXT_58);
    }
    { List<String> eVirtualIndexBitFields = genClass.getEVirtualIndexBitFields(new ArrayList<String>());
    if (!eVirtualIndexBitFields.isEmpty()) {
    for (String eVirtualIndexBitField : eVirtualIndexBitFields) {
    stringBuffer.append(TEXT_59);
    stringBuffer.append(eVirtualIndexBitField);
    stringBuffer.append(TEXT_60);
    }
    }
    }
    }
    if (isImplementation && genClass.isModelRoot() && genModel.isBooleanFlagsEnabled() && genModel.getBooleanFlagsReservedBits() == -1) {
    stringBuffer.append(TEXT_61);
    stringBuffer.append(genModel.getBooleanFlagsField());
    stringBuffer.append(TEXT_62);
    }
    if (isImplementation && !genModel.isReflectiveDelegation()) {
    for (GenFeature genFeature : genClass.getDeclaredFieldGenFeatures()) {
    if (genFeature.isListType() || genFeature.isReferenceType()) {
    if (genClass.isField(genFeature)) {
    stringBuffer.append(TEXT_63);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_64);
    stringBuffer.append(genFeature.getFormattedName());
    stringBuffer.append(TEXT_65);
    stringBuffer.append(genFeature.getFeatureKind());
    stringBuffer.append(TEXT_66);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_67);
    stringBuffer.append(genFeature.getImportedInternalType(genClass));
    stringBuffer.append(TEXT_68);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_69);
    }
    if (genModel.isArrayAccessors() && genFeature.isListType() && !genFeature.isFeatureMapType() && !genFeature.isMapType()) { String rawListItemType = genFeature.getRawListItemType(); int index = rawListItemType.indexOf('['); String head = rawListItemType; String tail = ""; if (index != -1) { head = rawListItemType.substring(0, index); tail = rawListItemType.substring(index); } 
    stringBuffer.append(TEXT_70);
    stringBuffer.append(genFeature.getGetArrayAccessor());
    stringBuffer.append(TEXT_71);
    stringBuffer.append(genFeature.getFormattedName());
    stringBuffer.append(TEXT_72);
    stringBuffer.append(genFeature.getGetArrayAccessor());
    stringBuffer.append(TEXT_73);
    if (genFeature.getQualifiedListItemType(genClass).contains("<")) {
    stringBuffer.append(TEXT_74);
    }
    stringBuffer.append(TEXT_75);
    stringBuffer.append(rawListItemType);
    stringBuffer.append(TEXT_76);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_77);
    stringBuffer.append(head);
    stringBuffer.append(TEXT_78);
    stringBuffer.append(tail);
    stringBuffer.append(TEXT_79);
    }
    } else {
    if (genFeature.hasEDefault() && (!genFeature.isVolatile() || !genModel.isReflectiveDelegation() && (!genFeature.hasDelegateFeature() || !genFeature.isUnsettable()))) { String staticDefaultValue = genFeature.getStaticDefaultValue();
    stringBuffer.append(TEXT_80);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_81);
    stringBuffer.append(genFeature.getFormattedName());
    stringBuffer.append(TEXT_82);
    stringBuffer.append(genFeature.getFeatureKind());
    stringBuffer.append(TEXT_83);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_84);
    if (genModel.useGenerics() && genFeature.isListDataType() && genFeature.isSetDefaultValue()) {
    stringBuffer.append(TEXT_85);
    }
    stringBuffer.append(TEXT_86);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_87);
    stringBuffer.append(genFeature.getEDefault());
    if ("".equals(staticDefaultValue)) {
    stringBuffer.append(TEXT_88);
    stringBuffer.append(genFeature.getEcoreFeature().getDefaultValueLiteral());
    stringBuffer.append(TEXT_89);
    } else {
    stringBuffer.append(TEXT_90);
    stringBuffer.append(staticDefaultValue);
    stringBuffer.append(TEXT_91);
    stringBuffer.append(genModel.getNonNLS(staticDefaultValue));
    }
    stringBuffer.append(TEXT_92);
    }
    if (genClass.isField(genFeature)) {
    if (genClass.isFlag(genFeature)) {
    if (genClass.getFlagIndex(genFeature) > 31 && genClass.getFlagIndex(genFeature) % 32 == 0) {
    stringBuffer.append(TEXT_93);
    stringBuffer.append(genClass.getFlagsField(genFeature));
    stringBuffer.append(TEXT_94);
    }
    stringBuffer.append(TEXT_95);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_96);
    stringBuffer.append(genFeature.getFormattedName());
    stringBuffer.append(TEXT_97);
    stringBuffer.append(genFeature.getFeatureKind());
    stringBuffer.append(TEXT_98);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_99);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_100);
    stringBuffer.append("<< " + genClass.getFlagIndex(genFeature) % 32 );
    stringBuffer.append(TEXT_101);
    } else {
    stringBuffer.append(TEXT_102);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_103);
    stringBuffer.append(genFeature.getFormattedName());
    stringBuffer.append(TEXT_104);
    stringBuffer.append(genFeature.getFeatureKind());
    stringBuffer.append(TEXT_105);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_106);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_107);
    stringBuffer.append(genFeature.getSafeName());
    if (genFeature.hasEDefault()) {
    stringBuffer.append(TEXT_108);
    stringBuffer.append(genFeature.getEDefault());
    }
    stringBuffer.append(TEXT_109);
    }
    }
    }
    if (genClass.isESetField(genFeature)) {
    if (genClass.isESetFlag(genFeature)) {
    if (genClass.getESetFlagIndex(genFeature) > 31 && genClass.getESetFlagIndex(genFeature) % 32 == 0) {
    stringBuffer.append(TEXT_110);
    stringBuffer.append(genClass.getESetFlagsField(genFeature));
    stringBuffer.append(TEXT_111);
    }
    stringBuffer.append(TEXT_112);
    stringBuffer.append(genFeature.getFormattedName());
    stringBuffer.append(TEXT_113);
    stringBuffer.append(genFeature.getFeatureKind());
    stringBuffer.append(TEXT_114);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_115);
    stringBuffer.append("<< " + genClass.getESetFlagIndex(genFeature) % 32 );
    stringBuffer.append(TEXT_116);
    } else {
    stringBuffer.append(TEXT_117);
    stringBuffer.append(genFeature.getFormattedName());
    stringBuffer.append(TEXT_118);
    stringBuffer.append(genFeature.getFeatureKind());
    stringBuffer.append(TEXT_119);
    stringBuffer.append(genFeature.getUncapName());
    stringBuffer.append(TEXT_120);
    }
    }
    }
    //Class/declaredFieldGenFeature.override.javajetinc
    }
    if (isImplementation && genClass.hasOffsetCorrection() && !genClass.getImplementedGenFeatures().isEmpty()) {
    stringBuffer.append(TEXT_121);
    stringBuffer.append(genClass.getOffsetCorrectionField(null));
    stringBuffer.append(TEXT_122);
    stringBuffer.append(genClass.getQualifiedClassifierAccessor());
    stringBuffer.append(TEXT_123);
    stringBuffer.append(genClass.getImplementedGenFeatures().get(0).getQualifiedFeatureAccessor());
    stringBuffer.append(TEXT_124);
    stringBuffer.append(genClass.getQualifiedFeatureID(genClass.getImplementedGenFeatures().get(0)));
    stringBuffer.append(TEXT_125);
    }
    if (isImplementation && genClass.hasOffsetCorrection() && !genModel.isReflectiveDelegation()) {
    for (GenFeature genFeature : genClass.getImplementedGenFeatures()) { GenFeature reverseFeature = genFeature.getReverse();
    if (reverseFeature != null && reverseFeature.getGenClass().hasOffsetCorrection()) {
    stringBuffer.append(TEXT_126);
    stringBuffer.append(genClass.getOffsetCorrectionField(genFeature));
    stringBuffer.append(TEXT_127);
    stringBuffer.append(reverseFeature.getGenClass().getQualifiedClassifierAccessor());
    stringBuffer.append(TEXT_128);
    stringBuffer.append(reverseFeature.getQualifiedFeatureAccessor());
    stringBuffer.append(TEXT_129);
    stringBuffer.append(reverseFeature.getGenClass().getQualifiedFeatureID(reverseFeature));
    stringBuffer.append(TEXT_130);
    }
    }
    }
    if (isImplementation) {
    stringBuffer.append(TEXT_131);
    if (genModel.isPublicConstructors()) {
    stringBuffer.append(TEXT_132);
    } else {
    stringBuffer.append(TEXT_133);
    }
    stringBuffer.append(TEXT_134);
    stringBuffer.append(genClass.getClassName());
    stringBuffer.append(TEXT_135);
    for (GenFeature genFeature : genClass.getFlagGenFeatures("true")) {
    stringBuffer.append(TEXT_136);
    stringBuffer.append(genClass.getFlagsField(genFeature));
    stringBuffer.append(TEXT_137);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_138);
    }
    stringBuffer.append(TEXT_139);
    if (genModel.useClassOverrideAnnotation()) {
    stringBuffer.append(TEXT_140);
    }
    stringBuffer.append(TEXT_141);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.EClass"));
    stringBuffer.append(TEXT_142);
    stringBuffer.append(genClass.getQualifiedClassifierAccessor());
    stringBuffer.append(TEXT_143);
    }
    if (isImplementation && genModel.getFeatureDelegation() == GenDelegationKind.REFLECTIVE_LITERAL && (genClass.getClassExtendsGenClass() == null || genClass.getClassExtendsGenClass().getGenModel().getFeatureDelegation() != GenDelegationKind.REFLECTIVE_LITERAL)) {
    stringBuffer.append(TEXT_144);
    if (genModel.useClassOverrideAnnotation()) {
    stringBuffer.append(TEXT_145);
    }
    stringBuffer.append(TEXT_146);
    stringBuffer.append(genClass.getClassExtendsGenClass() == null ? 0 : genClass.getClassExtendsGenClass().getAllGenFeatures().size());
    stringBuffer.append(TEXT_147);
    }
    //Class/reflectiveDelegation.override.javajetinc
    new Runnable() { public void run() {
    for (GenFeature genFeature : (isImplementation ? genClass.getImplementedGenFeatures() : genClass.getDeclaredGenFeatures())) {
    if (genModel.isArrayAccessors() && genFeature.isListType() && !genFeature.isFeatureMapType() && !genFeature.isMapType()) {
    stringBuffer.append(TEXT_148);
    if (!isImplementation) {
    stringBuffer.append(TEXT_149);
    stringBuffer.append(genFeature.getListItemType(genClass));
    stringBuffer.append(TEXT_150);
    stringBuffer.append(genFeature.getGetArrayAccessor());
    stringBuffer.append(TEXT_151);
    } else {
    if (genModel.useGenerics()) {
    stringBuffer.append(TEXT_152);
    }
    stringBuffer.append(TEXT_153);
    stringBuffer.append(genFeature.getListItemType(genClass));
    stringBuffer.append(TEXT_154);
    stringBuffer.append(genFeature.getGetArrayAccessor());
    stringBuffer.append(TEXT_155);
    if (genFeature.isVolatile()) {
    stringBuffer.append(TEXT_156);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.util.BasicEList"));
    stringBuffer.append(genFeature.getListTemplateArguments(genClass));
    stringBuffer.append(TEXT_157);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.util.BasicEList"));
    stringBuffer.append(genFeature.getListTemplateArguments(genClass));
    stringBuffer.append(TEXT_158);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_159);
    if (genModel.useGenerics() && !genFeature.getListItemType(genClass).contains("<") && !genFeature.getListItemType(null).equals(genFeature.getListItemType(genClass))) {
    stringBuffer.append(TEXT_160);
    stringBuffer.append(genFeature.getListItemType(genClass));
    stringBuffer.append(TEXT_161);
    }
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_162);
    } else {
    stringBuffer.append(TEXT_163);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_164);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_165);
    if (genModel.useGenerics() && !genFeature.getListItemType(genClass).contains("<") && !genFeature.getListItemType(null).equals(genFeature.getListItemType(genClass))) {
    stringBuffer.append(TEXT_166);
    stringBuffer.append(genFeature.getListItemType(genClass));
    stringBuffer.append(TEXT_167);
    }
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_168);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.util.BasicEList"));
    stringBuffer.append(genFeature.getListTemplateArguments(genClass));
    stringBuffer.append(TEXT_169);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.util.BasicEList"));
    stringBuffer.append(genFeature.getListTemplateArguments(genClass));
    stringBuffer.append(TEXT_170);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_171);
    }
    stringBuffer.append(TEXT_172);
    stringBuffer.append(genFeature.getListItemType(genClass));
    stringBuffer.append(TEXT_173);
    }
    stringBuffer.append(TEXT_174);
    if (!isImplementation) {
    stringBuffer.append(TEXT_175);
    stringBuffer.append(genFeature.getListItemType(genClass));
    stringBuffer.append(TEXT_176);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_177);
    } else {
    stringBuffer.append(TEXT_178);
    stringBuffer.append(genFeature.getListItemType(genClass));
    stringBuffer.append(TEXT_179);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_180);
    if (!genModel.useGenerics()) {
    stringBuffer.append(TEXT_181);
    stringBuffer.append(genFeature.getListItemType(genClass));
    stringBuffer.append(TEXT_182);
    }
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_183);
    }
    stringBuffer.append(TEXT_184);
    if (!isImplementation) {
    stringBuffer.append(TEXT_185);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_186);
    } else {
    stringBuffer.append(TEXT_187);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_188);
    if (genFeature.isVolatile()) {
    stringBuffer.append(TEXT_189);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_190);
    } else {
    stringBuffer.append(TEXT_191);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_192);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_193);
    }
    stringBuffer.append(TEXT_194);
    }
    stringBuffer.append(TEXT_195);
    if (!isImplementation) {
    stringBuffer.append(TEXT_196);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_197);
    stringBuffer.append(genFeature.getListItemType(genClass));
    stringBuffer.append(TEXT_198);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_199);
    } else {
    stringBuffer.append(TEXT_200);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_201);
    stringBuffer.append(genFeature.getListItemType(genClass));
    stringBuffer.append(TEXT_202);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_203);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.util.BasicEList"));
    stringBuffer.append(genFeature.getListTemplateArguments(genClass));
    stringBuffer.append(TEXT_204);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_205);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_206);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_207);
    }
    stringBuffer.append(TEXT_208);
    if (!isImplementation) {
    stringBuffer.append(TEXT_209);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_210);
    stringBuffer.append(genFeature.getListItemType(genClass));
    stringBuffer.append(TEXT_211);
    } else {
    stringBuffer.append(TEXT_212);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_213);
    stringBuffer.append(genFeature.getListItemType(genClass));
    stringBuffer.append(TEXT_214);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_215);
    }
    }
    if (genFeature.isGet() && (isImplementation || !genFeature.isSuppressedGetVisibility())) {
    if (isInterface) {
    stringBuffer.append(TEXT_216);
    stringBuffer.append(genFeature.getFormattedName());
    stringBuffer.append(TEXT_217);
    stringBuffer.append(genFeature.getFeatureKind());
    stringBuffer.append(TEXT_218);
    if (genFeature.isListType()) {
    if (genFeature.isMapType()) { GenFeature keyFeature = genFeature.getMapEntryTypeGenClass().getMapEntryKeyFeature(); GenFeature valueFeature = genFeature.getMapEntryTypeGenClass().getMapEntryValueFeature(); 
    stringBuffer.append(TEXT_219);
    if (keyFeature.isListType()) {
    stringBuffer.append(TEXT_220);
    stringBuffer.append(keyFeature.getQualifiedListItemType(genClass));
    stringBuffer.append(TEXT_221);
    } else {
    stringBuffer.append(TEXT_222);
    stringBuffer.append(keyFeature.getType(genClass));
    stringBuffer.append(TEXT_223);
    }
    stringBuffer.append(TEXT_224);
    if (valueFeature.isListType()) {
    stringBuffer.append(TEXT_225);
    stringBuffer.append(valueFeature.getQualifiedListItemType(genClass));
    stringBuffer.append(TEXT_226);
    } else {
    stringBuffer.append(TEXT_227);
    stringBuffer.append(valueFeature.getType(genClass));
    stringBuffer.append(TEXT_228);
    }
    stringBuffer.append(TEXT_229);
    } else if (!genFeature.isWrappedFeatureMapType() && !(genModel.isSuppressEMFMetaData() && "org.eclipse.emf.ecore.EObject".equals(genFeature.getQualifiedListItemType(genClass)))) {
String typeName = genFeature.getQualifiedListItemType(genClass); String head = typeName; String tail = ""; int index = typeName.indexOf('<'); if (index == -1) { index = typeName.indexOf('['); } 
if (index != -1) { head = typeName.substring(0, index); tail = typeName.substring(index).replaceAll("<", "&lt;"); }

    stringBuffer.append(TEXT_230);
    stringBuffer.append(head);
    stringBuffer.append(TEXT_231);
    stringBuffer.append(tail);
    stringBuffer.append(TEXT_232);
    }
    } else if (genFeature.isSetDefaultValue()) {
    stringBuffer.append(TEXT_233);
    stringBuffer.append(genFeature.getDefaultValue());
    stringBuffer.append(TEXT_234);
    }
    if (genFeature.getTypeGenEnum() != null) {
    stringBuffer.append(TEXT_235);
    stringBuffer.append(genFeature.getTypeGenEnum().getQualifiedName());
    stringBuffer.append(TEXT_236);
    }
    if (genFeature.isBidirectional() && !genFeature.getReverse().getGenClass().isMapEntry()) { GenFeature reverseGenFeature = genFeature.getReverse(); 
    if (!reverseGenFeature.isSuppressedGetVisibility()) {
    stringBuffer.append(TEXT_237);
    stringBuffer.append(reverseGenFeature.getGenClass().getQualifiedInterfaceName());
    stringBuffer.append(TEXT_238);
    stringBuffer.append(reverseGenFeature.getGetAccessor());
    stringBuffer.append(TEXT_239);
    stringBuffer.append(reverseGenFeature.getFormattedName());
    stringBuffer.append(TEXT_240);
    }
    }
    stringBuffer.append(TEXT_241);
    if (!genFeature.hasDocumentation()) {
    stringBuffer.append(TEXT_242);
    stringBuffer.append(genFeature.getFormattedName());
    stringBuffer.append(TEXT_243);
    stringBuffer.append(genFeature.getFeatureKind());
    stringBuffer.append(TEXT_244);
    }
    stringBuffer.append(TEXT_245);
    if (genFeature.hasDocumentation()) {
    stringBuffer.append(TEXT_246);
    stringBuffer.append(genFeature.getDocumentation(genModel.getIndentation(stringBuffer)));
    stringBuffer.append(TEXT_247);
    }
    stringBuffer.append(TEXT_248);
    stringBuffer.append(genFeature.getFormattedName());
    stringBuffer.append(TEXT_249);
    stringBuffer.append(genFeature.getFeatureKind());
    stringBuffer.append(TEXT_250);
    if (genFeature.getTypeGenEnum() != null) {
    stringBuffer.append(TEXT_251);
    stringBuffer.append(genFeature.getTypeGenEnum().getQualifiedName());
    }
    if (genFeature.isUnsettable()) {
    if (!genFeature.isSuppressedIsSetVisibility()) {
    stringBuffer.append(TEXT_252);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_253);
    }
    if (genFeature.isChangeable() && !genFeature.isSuppressedUnsetVisibility()) {
    stringBuffer.append(TEXT_254);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_255);
    }
    }
    if (genFeature.isChangeable() && !genFeature.isListType() && !genFeature.isSuppressedSetVisibility()) {
    stringBuffer.append(TEXT_256);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_257);
    stringBuffer.append(genFeature.getRawImportedBoundType());
    stringBuffer.append(TEXT_258);
    }
    if (!genModel.isSuppressEMFMetaData()) {
    stringBuffer.append(TEXT_259);
    stringBuffer.append(genPackage.getQualifiedPackageInterfaceName());
    stringBuffer.append(TEXT_260);
    stringBuffer.append(genFeature.getFeatureAccessorName());
    stringBuffer.append(TEXT_261);
    }
    if (genFeature.isBidirectional() && !genFeature.getReverse().getGenClass().isMapEntry()) { GenFeature reverseGenFeature = genFeature.getReverse(); 
    if (!reverseGenFeature.isSuppressedGetVisibility()) {
    stringBuffer.append(TEXT_262);
    stringBuffer.append(reverseGenFeature.getGenClass().getQualifiedInterfaceName());
    stringBuffer.append(TEXT_263);
    stringBuffer.append(reverseGenFeature.getGetAccessor());
    }
    }
    if (!genModel.isSuppressEMFModelTags()) { boolean first = true; for (StringTokenizer stringTokenizer = new StringTokenizer(genFeature.getModelInfo(), "\n\r"); stringTokenizer.hasMoreTokens(); ) { String modelInfo = stringTokenizer.nextToken(); if (first) { first = false;
    stringBuffer.append(TEXT_264);
    stringBuffer.append(modelInfo);
    } else {
    stringBuffer.append(TEXT_265);
    stringBuffer.append(modelInfo);
    }} if (first) {
    stringBuffer.append(TEXT_266);
    }}
    stringBuffer.append(TEXT_267);
    //Class/getGenFeature.javadoc.override.javajetinc
    } else {
    stringBuffer.append(TEXT_268);
    if (genModel.getComplianceLevel().getValue() >= GenJDKLevel.JDK50) { //Class/getGenFeature.annotations.insert.javajetinc
    }
    }
    if (!isImplementation) {
    stringBuffer.append(TEXT_269);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_270);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_271);
    } else {
    if (genModel.useGenerics() && ((genFeature.isContainer() || genFeature.isResolveProxies()) && !genFeature.isListType() && !genModel.isReflectiveDelegation() && genFeature.isUncheckedCast(genClass) || genFeature.isListType() && (genModel.isReflectiveDelegation() || genModel.isVirtualDelegation()) || genFeature.isListDataType() && genFeature.hasDelegateFeature())) {
    stringBuffer.append(TEXT_272);
    }
    stringBuffer.append(TEXT_273);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_274);
    stringBuffer.append(genFeature.getGetAccessor());
    if (genClass.hasCollidingGetAccessorOperation(genFeature)) {
    stringBuffer.append(TEXT_275);
    }
    stringBuffer.append(TEXT_276);
    if (genModel.isReflectiveDelegation()) {
    stringBuffer.append(TEXT_277);
    if (genFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_278);
    }
    stringBuffer.append(TEXT_279);
    stringBuffer.append(genFeature.getObjectType(genClass));
    stringBuffer.append(TEXT_280);
    stringBuffer.append(genFeature.getQualifiedFeatureAccessor());
    stringBuffer.append(TEXT_281);
    if (genFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_282);
    stringBuffer.append(genFeature.getPrimitiveValueFunction());
    stringBuffer.append(TEXT_283);
    }
    stringBuffer.append(TEXT_284);
    } else if (!genFeature.isVolatile()) {
    if (genFeature.isListType()) {
    if (genModel.isVirtualDelegation()) {
    stringBuffer.append(TEXT_285);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_286);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_287);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_288);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(positiveOffsetCorrection);
    stringBuffer.append(TEXT_289);
    }
    stringBuffer.append(TEXT_290);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_291);
    if (genModel.isVirtualDelegation()) {
    stringBuffer.append(TEXT_292);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(positiveOffsetCorrection);
    stringBuffer.append(TEXT_293);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_294);
    stringBuffer.append(genClass.getListConstructor(genFeature));
    stringBuffer.append(TEXT_295);
    } else {
    stringBuffer.append(TEXT_296);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_297);
    stringBuffer.append(genClass.getListConstructor(genFeature));
    stringBuffer.append(TEXT_298);
    }
    stringBuffer.append(TEXT_299);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(genFeature.isMapType() && genFeature.isEffectiveSuppressEMFTypes() ? ".map()" : "");
    stringBuffer.append(TEXT_300);
    } else if (genFeature.isContainer()) {
    stringBuffer.append(TEXT_301);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(positiveOffsetCorrection);
    stringBuffer.append(TEXT_302);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_303);
    } else {
    if (genFeature.isResolveProxies()) {
    if (genModel.isVirtualDelegation()) {
    stringBuffer.append(TEXT_304);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_305);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_306);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_307);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(positiveOffsetCorrection);
    if (genFeature.hasEDefault()) {
    stringBuffer.append(TEXT_308);
    stringBuffer.append(genFeature.getEDefault());
    }
    stringBuffer.append(TEXT_309);
    }
    stringBuffer.append(TEXT_310);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_311);
    stringBuffer.append(genFeature.getSafeNameAsEObject());
    stringBuffer.append(TEXT_312);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.InternalEObject"));
    stringBuffer.append(TEXT_313);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_314);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.InternalEObject"));
    stringBuffer.append(TEXT_315);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_316);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_317);
    stringBuffer.append(genFeature.getNonEObjectInternalTypeCast(genClass));
    stringBuffer.append(TEXT_318);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_319);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_320);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_321);
    if (genFeature.isEffectiveContains()) {
    stringBuffer.append(TEXT_322);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.InternalEObject"));
    stringBuffer.append(TEXT_323);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_324);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.InternalEObject"));
    stringBuffer.append(TEXT_325);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_326);
    if (!genFeature.isBidirectional()) {
    stringBuffer.append(TEXT_327);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.notify.NotificationChain"));
    stringBuffer.append(TEXT_328);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_329);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(negativeOffsetCorrection);
    stringBuffer.append(TEXT_330);
    } else { GenFeature reverseFeature = genFeature.getReverse(); GenClass targetClass = reverseFeature.getGenClass(); String reverseOffsetCorrection = targetClass.hasOffsetCorrection() ? " + " + genClass.getOffsetCorrectionField(reverseFeature) : "";
    stringBuffer.append(TEXT_331);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.notify.NotificationChain"));
    stringBuffer.append(TEXT_332);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_333);
    stringBuffer.append(targetClass.getQualifiedFeatureID(reverseFeature));
    stringBuffer.append(reverseOffsetCorrection);
    stringBuffer.append(TEXT_334);
    stringBuffer.append(targetClass.getImportedInterfaceName());
    stringBuffer.append(TEXT_335);
    }
    stringBuffer.append(TEXT_336);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_337);
    if (!genFeature.isBidirectional()) {
    stringBuffer.append(TEXT_338);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_339);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(negativeOffsetCorrection);
    stringBuffer.append(TEXT_340);
    } else { GenFeature reverseFeature = genFeature.getReverse(); GenClass targetClass = reverseFeature.getGenClass(); String reverseOffsetCorrection = targetClass.hasOffsetCorrection() ? " + " + genClass.getOffsetCorrectionField(reverseFeature) : "";
    stringBuffer.append(TEXT_341);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_342);
    stringBuffer.append(targetClass.getQualifiedFeatureID(reverseFeature));
    stringBuffer.append(reverseOffsetCorrection);
    stringBuffer.append(TEXT_343);
    stringBuffer.append(targetClass.getImportedInterfaceName());
    stringBuffer.append(TEXT_344);
    }
    stringBuffer.append(TEXT_345);
    } else if (genModel.isVirtualDelegation()) {
    stringBuffer.append(TEXT_346);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(positiveOffsetCorrection);
    stringBuffer.append(TEXT_347);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_348);
    }
    if (!genModel.isSuppressNotification()) {
    stringBuffer.append(TEXT_349);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.impl.ENotificationImpl"));
    stringBuffer.append(TEXT_350);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.notify.Notification"));
    stringBuffer.append(TEXT_351);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(positiveOffsetCorrection);
    stringBuffer.append(TEXT_352);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_353);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_354);
    }
    stringBuffer.append(TEXT_355);
    }
    if (!genFeature.isResolveProxies() && genModel.isVirtualDelegation() && !genFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_356);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_357);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(positiveOffsetCorrection);
    if (genFeature.hasEDefault()) {
    stringBuffer.append(TEXT_358);
    stringBuffer.append(genFeature.getEDefault());
    }
    stringBuffer.append(TEXT_359);
    } else if (genClass.isFlag(genFeature)) {
    stringBuffer.append(TEXT_360);
    stringBuffer.append(genClass.getFlagsField(genFeature));
    stringBuffer.append(TEXT_361);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_362);
    } else {
    stringBuffer.append(TEXT_363);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_364);
    }
    }
    } else {//volatile
    if (genFeature.isResolveProxies() && !genFeature.isListType()) {
    stringBuffer.append(TEXT_365);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_366);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_367);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_368);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_369);
    stringBuffer.append(genFeature.getSafeNameAsEObject());
    stringBuffer.append(TEXT_370);
    stringBuffer.append(genFeature.getNonEObjectInternalTypeCast(genClass));
    stringBuffer.append(TEXT_371);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.InternalEObject"));
    stringBuffer.append(TEXT_372);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_373);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_374);
    } else if (genFeature.hasDelegateFeature()) { GenFeature delegateFeature = genFeature.getDelegateFeature();
    if (genFeature.isFeatureMapType()) {
    String featureMapEntryTemplateArgument = genModel.getComplianceLevel().getValue() >= GenJDKLevel.JDK50 ? "<" + genModel.getImportedName("org.eclipse.emf.ecore.util.FeatureMap") + ".Entry>" : "";
    if (delegateFeature.isWrappedFeatureMapType()) {
    stringBuffer.append(TEXT_375);
    stringBuffer.append(genFeature.getImportedEffectiveFeatureMapWrapperClass());
    stringBuffer.append(TEXT_376);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.FeatureMap"));
    stringBuffer.append(TEXT_377);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.FeatureMap"));
    stringBuffer.append(TEXT_378);
    stringBuffer.append(delegateFeature.getAccessorName());
    stringBuffer.append(TEXT_379);
    stringBuffer.append(featureMapEntryTemplateArgument);
    stringBuffer.append(TEXT_380);
    stringBuffer.append(genFeature.getQualifiedFeatureAccessor());
    stringBuffer.append(TEXT_381);
    } else {
    stringBuffer.append(TEXT_382);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.FeatureMap"));
    stringBuffer.append(TEXT_383);
    stringBuffer.append(delegateFeature.getAccessorName());
    stringBuffer.append(TEXT_384);
    stringBuffer.append(featureMapEntryTemplateArgument);
    stringBuffer.append(TEXT_385);
    stringBuffer.append(genFeature.getQualifiedFeatureAccessor());
    stringBuffer.append(TEXT_386);
    }
    } else if (genFeature.isListType()) {
    if (delegateFeature.isWrappedFeatureMapType()) {
    stringBuffer.append(TEXT_387);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.FeatureMap"));
    stringBuffer.append(TEXT_388);
    stringBuffer.append(delegateFeature.getAccessorName());
    stringBuffer.append(TEXT_389);
    stringBuffer.append(genFeature.getQualifiedFeatureAccessor());
    stringBuffer.append(TEXT_390);
    } else {
    stringBuffer.append(TEXT_391);
    stringBuffer.append(delegateFeature.getAccessorName());
    stringBuffer.append(TEXT_392);
    stringBuffer.append(genFeature.getQualifiedFeatureAccessor());
    stringBuffer.append(TEXT_393);
    }
    } else {
    if (delegateFeature.isWrappedFeatureMapType()) {
    stringBuffer.append(TEXT_394);
    if (genFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_395);
    }
    if (genFeature.getTypeGenDataType() == null || !genFeature.getTypeGenDataType().isObjectType()) {
    stringBuffer.append(TEXT_396);
    stringBuffer.append(genFeature.getObjectType(genClass));
    stringBuffer.append(TEXT_397);
    }
    stringBuffer.append(TEXT_398);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.FeatureMap"));
    stringBuffer.append(TEXT_399);
    stringBuffer.append(delegateFeature.getAccessorName());
    stringBuffer.append(TEXT_400);
    stringBuffer.append(genFeature.getQualifiedFeatureAccessor());
    stringBuffer.append(TEXT_401);
    if (genFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_402);
    stringBuffer.append(genFeature.getPrimitiveValueFunction());
    stringBuffer.append(TEXT_403);
    }
    stringBuffer.append(TEXT_404);
    } else {
    stringBuffer.append(TEXT_405);
    if (genFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_406);
    }
    if (genFeature.getTypeGenDataType() == null || !genFeature.getTypeGenDataType().isObjectType()) {
    stringBuffer.append(TEXT_407);
    stringBuffer.append(genFeature.getObjectType(genClass));
    stringBuffer.append(TEXT_408);
    }
    stringBuffer.append(TEXT_409);
    stringBuffer.append(delegateFeature.getAccessorName());
    stringBuffer.append(TEXT_410);
    stringBuffer.append(genFeature.getQualifiedFeatureAccessor());
    stringBuffer.append(TEXT_411);
    if (genFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_412);
    stringBuffer.append(genFeature.getPrimitiveValueFunction());
    stringBuffer.append(TEXT_413);
    }
    stringBuffer.append(TEXT_414);
    }
    }
    } else if (genClass.getGetAccessorOperation(genFeature) != null) {
    stringBuffer.append(TEXT_415);
    stringBuffer.append(genClass.getGetAccessorOperation(genFeature).getBody(genModel.getIndentation(stringBuffer)));
    } else {
    stringBuffer.append(TEXT_416);
    stringBuffer.append(genFeature.getFormattedName());
    stringBuffer.append(TEXT_417);
    stringBuffer.append(genFeature.getFeatureKind());
    stringBuffer.append(TEXT_418);
    if (genFeature.isListType()) {
    stringBuffer.append(TEXT_419);
    if (genFeature.isMapType()) {
    stringBuffer.append(TEXT_420);
    } else if (genFeature.isFeatureMapType()) {
    stringBuffer.append(TEXT_421);
    } else {
    stringBuffer.append(TEXT_422);
    }
    stringBuffer.append(TEXT_423);
    }
    stringBuffer.append(TEXT_424);
    //Class/getGenFeature.todo.override.javajetinc
    }
    }
    stringBuffer.append(TEXT_425);
    }
    //Class/getGenFeature.override.javajetinc
    }
    if (isImplementation && !genModel.isReflectiveDelegation() && genFeature.isBasicGet()) {
    stringBuffer.append(TEXT_426);
    if (genModel.getComplianceLevel().getValue() >= GenJDKLevel.JDK50) { //Class/basicGetGenFeature.annotations.insert.javajetinc
    }
    stringBuffer.append(TEXT_427);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_428);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_429);
    if (genFeature.isContainer()) {
    stringBuffer.append(TEXT_430);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(positiveOffsetCorrection);
    stringBuffer.append(TEXT_431);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_432);
    } else if (!genFeature.isVolatile()) {
    if (genModel.isVirtualDelegation()) {
    stringBuffer.append(TEXT_433);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_434);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(positiveOffsetCorrection);
    stringBuffer.append(TEXT_435);
    } else {
    stringBuffer.append(TEXT_436);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_437);
    }
    } else if (genFeature.hasDelegateFeature()) { GenFeature delegateFeature = genFeature.getDelegateFeature();
    if (delegateFeature.isWrappedFeatureMapType()) {
    stringBuffer.append(TEXT_438);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_439);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.FeatureMap"));
    stringBuffer.append(TEXT_440);
    stringBuffer.append(delegateFeature.getAccessorName());
    stringBuffer.append(TEXT_441);
    stringBuffer.append(genFeature.getQualifiedFeatureAccessor());
    stringBuffer.append(TEXT_442);
    } else {
    stringBuffer.append(TEXT_443);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_444);
    stringBuffer.append(delegateFeature.getAccessorName());
    stringBuffer.append(TEXT_445);
    stringBuffer.append(genFeature.getQualifiedFeatureAccessor());
    stringBuffer.append(TEXT_446);
    }
    } else {
    stringBuffer.append(TEXT_447);
    stringBuffer.append(genFeature.getFormattedName());
    stringBuffer.append(TEXT_448);
    stringBuffer.append(genFeature.getFeatureKind());
    stringBuffer.append(TEXT_449);
    //Class/basicGetGenFeature.todo.override.javajetinc
    }
    stringBuffer.append(TEXT_450);
    //Class/basicGetGenFeature.override.javajetinc
    }
    if (isImplementation && !genModel.isReflectiveDelegation() && genFeature.isBasicSet()) {
    stringBuffer.append(TEXT_451);
    if (genModel.getComplianceLevel().getValue() >= GenJDKLevel.JDK50) { //Class/basicSetGenFeature.annotations.insert.javajetinc
    }
    stringBuffer.append(TEXT_452);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.notify.NotificationChain"));
    stringBuffer.append(TEXT_453);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_454);
    stringBuffer.append(genFeature.getImportedInternalType(genClass));
    stringBuffer.append(TEXT_455);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_456);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.notify.NotificationChain"));
    stringBuffer.append(TEXT_457);
    if (genFeature.isContainer()) {
    stringBuffer.append(TEXT_458);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.InternalEObject"));
    stringBuffer.append(TEXT_459);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_460);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(positiveOffsetCorrection);
    stringBuffer.append(TEXT_461);
    stringBuffer.append(TEXT_462);
    } else if (!genFeature.isVolatile()) {
    if (genModel.isVirtualDelegation()) {
    stringBuffer.append(TEXT_463);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_464);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(positiveOffsetCorrection);
    stringBuffer.append(TEXT_465);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_466);
    } else {
    stringBuffer.append(TEXT_467);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_468);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_469);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_470);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_471);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_472);
    }
    if (genFeature.isUnsettable()) {
    if (genModel.isVirtualDelegation()) {
    stringBuffer.append(TEXT_473);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_474);
    } else if (genClass.isESetFlag(genFeature)) {
    stringBuffer.append(TEXT_475);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_476);
    stringBuffer.append(genClass.getESetFlagsField(genFeature));
    stringBuffer.append(TEXT_477);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_478);
    stringBuffer.append(genClass.getESetFlagsField(genFeature));
    stringBuffer.append(TEXT_479);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_480);
    } else {
    stringBuffer.append(TEXT_481);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_482);
    stringBuffer.append(genFeature.getUncapName());
    stringBuffer.append(TEXT_483);
    stringBuffer.append(genFeature.getUncapName());
    stringBuffer.append(TEXT_484);
    }
    }
    if (!genModel.isSuppressNotification()) {
    stringBuffer.append(TEXT_485);
    if (genFeature.isUnsettable()) {
    stringBuffer.append(TEXT_486);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.impl.ENotificationImpl"));
    stringBuffer.append(TEXT_487);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.impl.ENotificationImpl"));
    stringBuffer.append(TEXT_488);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.notify.Notification"));
    stringBuffer.append(TEXT_489);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(positiveOffsetCorrection);
    stringBuffer.append(TEXT_490);
    if (genModel.isVirtualDelegation()) {
    stringBuffer.append(TEXT_491);
    stringBuffer.append(genFeature.getCapName());
    } else {
    stringBuffer.append(TEXT_492);
    stringBuffer.append(genFeature.getCapName());
    }
    stringBuffer.append(TEXT_493);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_494);
    if (genModel.isVirtualDelegation()) {
    stringBuffer.append(TEXT_495);
    } else {
    stringBuffer.append(TEXT_496);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_497);
    }
    stringBuffer.append(TEXT_498);
    } else {
    stringBuffer.append(TEXT_499);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.impl.ENotificationImpl"));
    stringBuffer.append(TEXT_500);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.impl.ENotificationImpl"));
    stringBuffer.append(TEXT_501);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.notify.Notification"));
    stringBuffer.append(TEXT_502);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(positiveOffsetCorrection);
    stringBuffer.append(TEXT_503);
    if (genModel.isVirtualDelegation()) {
    stringBuffer.append(TEXT_504);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_505);
    stringBuffer.append(genFeature.getCapName());
    } else {
    stringBuffer.append(TEXT_506);
    stringBuffer.append(genFeature.getCapName());
    }
    stringBuffer.append(TEXT_507);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_508);
    }
    stringBuffer.append(TEXT_509);
    }
    stringBuffer.append(TEXT_510);
    } else if (genFeature.hasDelegateFeature()) { GenFeature delegateFeature = genFeature.getDelegateFeature();
    if (delegateFeature.isWrappedFeatureMapType()) {
    stringBuffer.append(TEXT_511);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.FeatureMap"));
    stringBuffer.append(TEXT_512);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.FeatureMap"));
    stringBuffer.append(TEXT_513);
    stringBuffer.append(delegateFeature.getAccessorName());
    stringBuffer.append(TEXT_514);
    stringBuffer.append(genFeature.getQualifiedFeatureAccessor());
    stringBuffer.append(TEXT_515);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_516);
    } else {
    stringBuffer.append(TEXT_517);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.FeatureMap"));
    stringBuffer.append(TEXT_518);
    stringBuffer.append(delegateFeature.getAccessorName());
    stringBuffer.append(TEXT_519);
    stringBuffer.append(genFeature.getQualifiedFeatureAccessor());
    stringBuffer.append(TEXT_520);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_521);
    }
    } else {
    stringBuffer.append(TEXT_522);
    stringBuffer.append(genFeature.getFormattedName());
    stringBuffer.append(TEXT_523);
    stringBuffer.append(genFeature.getFeatureKind());
    stringBuffer.append(TEXT_524);
    //Class/basicSetGenFeature.todo.override.javajetinc
    }
    stringBuffer.append(TEXT_525);
    //Class/basicSetGenFeature.override.javajetinc
    }
    if (genFeature.isSet() && (isImplementation || !genFeature.isSuppressedSetVisibility())) {
    if (isInterface) { 
    stringBuffer.append(TEXT_526);
    stringBuffer.append(genClass.getQualifiedInterfaceName());
    stringBuffer.append(TEXT_527);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_528);
    stringBuffer.append(genFeature.getFormattedName());
    stringBuffer.append(TEXT_529);
    stringBuffer.append(genFeature.getFeatureKind());
    stringBuffer.append(TEXT_530);
    stringBuffer.append(TEXT_531);
    stringBuffer.append(genFeature.getFormattedName());
    stringBuffer.append(TEXT_532);
    stringBuffer.append(genFeature.getFeatureKind());
    stringBuffer.append(TEXT_533);
    if (genFeature.isEnumType()) {
    stringBuffer.append(TEXT_534);
    stringBuffer.append(genFeature.getTypeGenEnum().getQualifiedName());
    }
    if (genFeature.isUnsettable()) {
    if (!genFeature.isSuppressedIsSetVisibility()) {
    stringBuffer.append(TEXT_535);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_536);
    }
    if (!genFeature.isSuppressedUnsetVisibility()) {
    stringBuffer.append(TEXT_537);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_538);
    }
    }
    stringBuffer.append(TEXT_539);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_540);
    //Class/setGenFeature.javadoc.override.javajetinc
    } else {
    stringBuffer.append(TEXT_541);
    if (genModel.getComplianceLevel().getValue() >= GenJDKLevel.JDK50) { //Class/setGenFeature.annotations.insert.javajetinc
    }
    }
    if (!isImplementation) { 
    stringBuffer.append(TEXT_542);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_543);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_544);
    } else { GenOperation setAccessorOperation = genClass.getSetAccessorOperation(genFeature);
    stringBuffer.append(TEXT_545);
    stringBuffer.append(genFeature.getAccessorName());
    if (genClass.hasCollidingSetAccessorOperation(genFeature)) {
    stringBuffer.append(TEXT_546);
    }
    stringBuffer.append(TEXT_547);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_548);
    stringBuffer.append(setAccessorOperation == null ? "new" + genFeature.getCapName() : setAccessorOperation.getGenParameters().get(0).getName());
    stringBuffer.append(TEXT_549);
    if (genModel.isReflectiveDelegation()) {
    stringBuffer.append(TEXT_550);
    stringBuffer.append(genFeature.getQualifiedFeatureAccessor());
    stringBuffer.append(TEXT_551);
    if (genFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_552);
    stringBuffer.append(genFeature.getObjectType(genClass));
    stringBuffer.append(TEXT_553);
    }
    stringBuffer.append(TEXT_554);
    stringBuffer.append(genFeature.getCapName());
    if (genFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_555);
    }
    stringBuffer.append(TEXT_556);
    } else if (!genFeature.isVolatile()) {
    if (genFeature.isContainer()) { GenFeature reverseFeature = genFeature.getReverse(); GenClass targetClass = reverseFeature.getGenClass(); String reverseOffsetCorrection = targetClass.hasOffsetCorrection() ? " + " + genClass.getOffsetCorrectionField(reverseFeature) : "";
    stringBuffer.append(TEXT_557);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_558);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(positiveOffsetCorrection);
    stringBuffer.append(TEXT_559);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_560);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.EcoreUtil"));
    stringBuffer.append(TEXT_561);
    stringBuffer.append(genFeature.getEObjectCast());
    stringBuffer.append(TEXT_562);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_563);
    stringBuffer.append(genModel.getImportedName("java.lang.IllegalArgumentException"));
    stringBuffer.append(TEXT_564);
    stringBuffer.append(genModel.getNonNLS());
    stringBuffer.append(TEXT_565);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.notify.NotificationChain"));
    stringBuffer.append(TEXT_566);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_567);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.InternalEObject"));
    stringBuffer.append(TEXT_568);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_569);
    stringBuffer.append(targetClass.getQualifiedFeatureID(reverseFeature));
    stringBuffer.append(reverseOffsetCorrection);
    stringBuffer.append(TEXT_570);
    stringBuffer.append(targetClass.getImportedInterfaceName());
    stringBuffer.append(TEXT_571);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_572);
    stringBuffer.append(genFeature.getInternalTypeCast());
    stringBuffer.append(TEXT_573);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_574);
    if (!genModel.isSuppressNotification()) {
    stringBuffer.append(TEXT_575);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.impl.ENotificationImpl"));
    stringBuffer.append(TEXT_576);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.notify.Notification"));
    stringBuffer.append(TEXT_577);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(positiveOffsetCorrection);
    stringBuffer.append(TEXT_578);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_579);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_580);
    }
    } else if (genFeature.isBidirectional() || genFeature.isEffectiveContains()) {
    if (genModel.isVirtualDelegation()) {
    stringBuffer.append(TEXT_581);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_582);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_583);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_584);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(positiveOffsetCorrection);
    stringBuffer.append(TEXT_585);
    }
    stringBuffer.append(TEXT_586);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_587);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_588);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.notify.NotificationChain"));
    stringBuffer.append(TEXT_589);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_590);
    if (!genFeature.isBidirectional()) {
    stringBuffer.append(TEXT_591);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.InternalEObject"));
    stringBuffer.append(TEXT_592);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_593);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(negativeOffsetCorrection);
    stringBuffer.append(TEXT_594);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_595);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.InternalEObject"));
    stringBuffer.append(TEXT_596);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_597);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(negativeOffsetCorrection);
    stringBuffer.append(TEXT_598);
    } else { GenFeature reverseFeature = genFeature.getReverse(); GenClass targetClass = reverseFeature.getGenClass(); String reverseOffsetCorrection = targetClass.hasOffsetCorrection() ? " + " + genClass.getOffsetCorrectionField(reverseFeature) : "";
    stringBuffer.append(TEXT_599);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.InternalEObject"));
    stringBuffer.append(TEXT_600);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_601);
    stringBuffer.append(targetClass.getQualifiedFeatureID(reverseFeature));
    stringBuffer.append(reverseOffsetCorrection);
    stringBuffer.append(TEXT_602);
    stringBuffer.append(targetClass.getImportedInterfaceName());
    stringBuffer.append(TEXT_603);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_604);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.InternalEObject"));
    stringBuffer.append(TEXT_605);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_606);
    stringBuffer.append(targetClass.getQualifiedFeatureID(reverseFeature));
    stringBuffer.append(reverseOffsetCorrection);
    stringBuffer.append(TEXT_607);
    stringBuffer.append(targetClass.getImportedInterfaceName());
    stringBuffer.append(TEXT_608);
    }
    stringBuffer.append(TEXT_609);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_610);
    stringBuffer.append(genFeature.getInternalTypeCast());
    stringBuffer.append(TEXT_611);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_612);
    if (genFeature.isUnsettable()) {
    stringBuffer.append(TEXT_613);
    if (genModel.isVirtualDelegation()) {
    stringBuffer.append(TEXT_614);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_615);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(positiveOffsetCorrection);
    stringBuffer.append(TEXT_616);
    } else if (genClass.isESetFlag(genFeature)) {
    if (!genModel.isSuppressNotification()) {
    stringBuffer.append(TEXT_617);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_618);
    stringBuffer.append(genClass.getESetFlagsField(genFeature));
    stringBuffer.append(TEXT_619);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_620);
    }
    stringBuffer.append(TEXT_621);
    stringBuffer.append(genClass.getESetFlagsField(genFeature));
    stringBuffer.append(TEXT_622);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_623);
    } else {
    if (!genModel.isSuppressNotification()) {
    stringBuffer.append(TEXT_624);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_625);
    stringBuffer.append(genFeature.getUncapName());
    stringBuffer.append(TEXT_626);
    }
    stringBuffer.append(TEXT_627);
    stringBuffer.append(genFeature.getUncapName());
    stringBuffer.append(TEXT_628);
    }
    if (!genModel.isSuppressNotification()) {
    stringBuffer.append(TEXT_629);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.impl.ENotificationImpl"));
    stringBuffer.append(TEXT_630);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.notify.Notification"));
    stringBuffer.append(TEXT_631);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(positiveOffsetCorrection);
    stringBuffer.append(TEXT_632);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_633);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_634);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_635);
    }
    stringBuffer.append(TEXT_636);
    } else {
    if (!genModel.isSuppressNotification()) {
    stringBuffer.append(TEXT_637);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.impl.ENotificationImpl"));
    stringBuffer.append(TEXT_638);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.notify.Notification"));
    stringBuffer.append(TEXT_639);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(positiveOffsetCorrection);
    stringBuffer.append(TEXT_640);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_641);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_642);
    }
    }
    } else {
    if (genClass.isFlag(genFeature)) {
    if (!genModel.isSuppressNotification()) {
    stringBuffer.append(TEXT_643);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_644);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_645);
    stringBuffer.append(genClass.getFlagsField(genFeature));
    stringBuffer.append(TEXT_646);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_647);
    }
    stringBuffer.append(TEXT_648);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_649);
    stringBuffer.append(genClass.getFlagsField(genFeature));
    stringBuffer.append(TEXT_650);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_651);
    stringBuffer.append(genClass.getFlagsField(genFeature));
    stringBuffer.append(TEXT_652);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_653);
    } else {
    if (!genModel.isVirtualDelegation() || genFeature.isPrimitiveType()) {
    if (!genModel.isSuppressNotification()) {
    stringBuffer.append(TEXT_654);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_655);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_656);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_657);
    }
    }
    if (genFeature.isEnumType()) {
    if (genModel.isVirtualDelegation()) {
    stringBuffer.append(TEXT_658);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_659);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_660);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_661);
    stringBuffer.append(genFeature.getEDefault());
    stringBuffer.append(TEXT_662);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_663);
    } else {
    stringBuffer.append(TEXT_664);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_665);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_666);
    stringBuffer.append(genFeature.getEDefault());
    stringBuffer.append(TEXT_667);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_668);
    }
    } else {
    if (genModel.isVirtualDelegation() && !genFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_669);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_670);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_671);
    stringBuffer.append(genFeature.getInternalTypeCast());
    stringBuffer.append(TEXT_672);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_673);
    } else {
    stringBuffer.append(TEXT_674);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_675);
    stringBuffer.append(genFeature.getInternalTypeCast());
    stringBuffer.append(TEXT_676);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_677);
    }
    }
    if (genModel.isVirtualDelegation() && !genFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_678);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_679);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(positiveOffsetCorrection);
    stringBuffer.append(TEXT_680);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_681);
    }
    }
    if (genFeature.isUnsettable()) {
    if (genModel.isVirtualDelegation() && !genFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_682);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_683);
    } else if (genClass.isESetFlag(genFeature)) {
    if (!genModel.isSuppressNotification()) {
    stringBuffer.append(TEXT_684);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_685);
    stringBuffer.append(genClass.getESetFlagsField(genFeature));
    stringBuffer.append(TEXT_686);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_687);
    }
    stringBuffer.append(TEXT_688);
    stringBuffer.append(genClass.getESetFlagsField(genFeature));
    stringBuffer.append(TEXT_689);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_690);
    } else {
    if (!genModel.isSuppressNotification()) {
    stringBuffer.append(TEXT_691);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_692);
    stringBuffer.append(genFeature.getUncapName());
    stringBuffer.append(TEXT_693);
    }
    stringBuffer.append(TEXT_694);
    stringBuffer.append(genFeature.getUncapName());
    stringBuffer.append(TEXT_695);
    }
    if (!genModel.isSuppressNotification()) {
    stringBuffer.append(TEXT_696);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.impl.ENotificationImpl"));
    stringBuffer.append(TEXT_697);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.notify.Notification"));
    stringBuffer.append(TEXT_698);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(positiveOffsetCorrection);
    stringBuffer.append(TEXT_699);
    if (genModel.isVirtualDelegation() && !genFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_700);
    stringBuffer.append(genFeature.getEDefault());
    stringBuffer.append(TEXT_701);
    stringBuffer.append(genFeature.getCapName());
    } else {
    stringBuffer.append(TEXT_702);
    stringBuffer.append(genFeature.getCapName());
    }
    stringBuffer.append(TEXT_703);
    if (genClass.isFlag(genFeature)) {
    stringBuffer.append(TEXT_704);
    stringBuffer.append(genFeature.getCapName());
    } else {
    stringBuffer.append(genFeature.getSafeName());
    }
    stringBuffer.append(TEXT_705);
    if (genModel.isVirtualDelegation() && !genFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_706);
    } else {
    stringBuffer.append(TEXT_707);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_708);
    }
    stringBuffer.append(TEXT_709);
    }
    } else {
    if (!genModel.isSuppressNotification()) {
    stringBuffer.append(TEXT_710);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.impl.ENotificationImpl"));
    stringBuffer.append(TEXT_711);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.notify.Notification"));
    stringBuffer.append(TEXT_712);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(positiveOffsetCorrection);
    stringBuffer.append(TEXT_713);
    if (genModel.isVirtualDelegation() && !genFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_714);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_715);
    stringBuffer.append(genFeature.getEDefault());
    stringBuffer.append(TEXT_716);
    stringBuffer.append(genFeature.getCapName());
    } else {
    stringBuffer.append(TEXT_717);
    stringBuffer.append(genFeature.getCapName());
    }
    stringBuffer.append(TEXT_718);
    if (genClass.isFlag(genFeature)) {
    stringBuffer.append(TEXT_719);
    stringBuffer.append(genFeature.getCapName());
    } else {
    stringBuffer.append(genFeature.getSafeName());
    }
    stringBuffer.append(TEXT_720);
    }
    }
    }
    } else if (genFeature.hasDelegateFeature()) { GenFeature delegateFeature = genFeature.getDelegateFeature();
    if (delegateFeature.isWrappedFeatureMapType()) {
    stringBuffer.append(TEXT_721);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.FeatureMap"));
    stringBuffer.append(TEXT_722);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.FeatureMap"));
    stringBuffer.append(TEXT_723);
    stringBuffer.append(delegateFeature.getAccessorName());
    stringBuffer.append(TEXT_724);
    stringBuffer.append(genFeature.getQualifiedFeatureAccessor());
    stringBuffer.append(TEXT_725);
    if (genFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_726);
    stringBuffer.append(genFeature.getObjectType(genClass));
    stringBuffer.append(TEXT_727);
    }
    stringBuffer.append(TEXT_728);
    stringBuffer.append(genFeature.getCapName());
    if (genFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_729);
    }
    stringBuffer.append(TEXT_730);
    } else {
    stringBuffer.append(TEXT_731);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.FeatureMap"));
    stringBuffer.append(TEXT_732);
    stringBuffer.append(delegateFeature.getAccessorName());
    stringBuffer.append(TEXT_733);
    stringBuffer.append(genFeature.getQualifiedFeatureAccessor());
    stringBuffer.append(TEXT_734);
    if (genFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_735);
    stringBuffer.append(genFeature.getObjectType(genClass));
    stringBuffer.append(TEXT_736);
    }
    stringBuffer.append(TEXT_737);
    stringBuffer.append(genFeature.getCapName());
    if (genFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_738);
    }
    stringBuffer.append(TEXT_739);
    }
    } else if (setAccessorOperation != null) {
    stringBuffer.append(TEXT_740);
    stringBuffer.append(setAccessorOperation.getBody(genModel.getIndentation(stringBuffer)));
    } else {
    stringBuffer.append(TEXT_741);
    stringBuffer.append(genFeature.getFormattedName());
    stringBuffer.append(TEXT_742);
    stringBuffer.append(genFeature.getFeatureKind());
    stringBuffer.append(TEXT_743);
    //Class/setGenFeature.todo.override.javajetinc
    }
    stringBuffer.append(TEXT_744);
    }
    //Class/setGenFeature.override.javajetinc
    }
    if (isImplementation && !genModel.isReflectiveDelegation() && genFeature.isBasicUnset()) {
    stringBuffer.append(TEXT_745);
    if (genModel.getComplianceLevel().getValue() >= GenJDKLevel.JDK50) { //Class/basicUnsetGenFeature.annotations.insert.javajetinc
    }
    stringBuffer.append(TEXT_746);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.notify.NotificationChain"));
    stringBuffer.append(TEXT_747);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_748);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.notify.NotificationChain"));
    stringBuffer.append(TEXT_749);
    if (!genFeature.isVolatile()) {
    if (genModel.isVirtualDelegation()) {
    stringBuffer.append(TEXT_750);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_751);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(positiveOffsetCorrection);
    stringBuffer.append(TEXT_752);
    } else {
    stringBuffer.append(TEXT_753);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_754);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_755);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_756);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_757);
    }
    if (genModel.isVirtualDelegation()) {
    stringBuffer.append(TEXT_758);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_759);
    } else if (genClass.isESetFlag(genFeature)) {
    stringBuffer.append(TEXT_760);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_761);
    stringBuffer.append(genClass.getESetFlagsField(genFeature));
    stringBuffer.append(TEXT_762);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_763);
    stringBuffer.append(genClass.getESetFlagsField(genFeature));
    stringBuffer.append(TEXT_764);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_765);
    } else {
    stringBuffer.append(TEXT_766);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_767);
    stringBuffer.append(genFeature.getUncapName());
    stringBuffer.append(TEXT_768);
    stringBuffer.append(genFeature.getUncapName());
    stringBuffer.append(TEXT_769);
    }
    if (!genModel.isSuppressNotification()) {
    stringBuffer.append(TEXT_770);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.impl.ENotificationImpl"));
    stringBuffer.append(TEXT_771);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.impl.ENotificationImpl"));
    stringBuffer.append(TEXT_772);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.notify.Notification"));
    stringBuffer.append(TEXT_773);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(positiveOffsetCorrection);
    stringBuffer.append(TEXT_774);
    if (genModel.isVirtualDelegation()) {
    stringBuffer.append(TEXT_775);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_776);
    } else {
    stringBuffer.append(TEXT_777);
    stringBuffer.append(genFeature.getCapName());
    }
    stringBuffer.append(TEXT_778);
    if (genModel.isVirtualDelegation()) {
    stringBuffer.append(TEXT_779);
    } else {
    stringBuffer.append(TEXT_780);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_781);
    }
    stringBuffer.append(TEXT_782);
    }
    } else {
    stringBuffer.append(TEXT_783);
    stringBuffer.append(genFeature.getFormattedName());
    stringBuffer.append(TEXT_784);
    stringBuffer.append(genFeature.getFeatureKind());
    stringBuffer.append(TEXT_785);
    //Class/basicUnsetGenFeature.todo.override.javajetinc
    }
    stringBuffer.append(TEXT_786);
    //Class.basicUnsetGenFeature.override.javajetinc
    }
    if (genFeature.isUnset() && (isImplementation || !genFeature.isSuppressedUnsetVisibility())) {
    if (isInterface) {
    stringBuffer.append(TEXT_787);
    stringBuffer.append(genClass.getQualifiedInterfaceName());
    stringBuffer.append(TEXT_788);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_789);
    stringBuffer.append(genFeature.getFormattedName());
    stringBuffer.append(TEXT_790);
    stringBuffer.append(genFeature.getFeatureKind());
    stringBuffer.append(TEXT_791);
    stringBuffer.append(TEXT_792);
    if (!genFeature.isSuppressedIsSetVisibility()) {
    stringBuffer.append(TEXT_793);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_794);
    }
    stringBuffer.append(TEXT_795);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_796);
    if (!genFeature.isListType() && !genFeature.isSuppressedSetVisibility()) {
    stringBuffer.append(TEXT_797);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_798);
    stringBuffer.append(genFeature.getRawImportedBoundType());
    stringBuffer.append(TEXT_799);
    }
    stringBuffer.append(TEXT_800);
    //Class/unsetGenFeature.javadoc.override.javajetinc
    } else {
    stringBuffer.append(TEXT_801);
    if (genModel.getComplianceLevel().getValue() >= GenJDKLevel.JDK50) { //Class/unsetGenFeature.annotations.insert.javajetinc
    }
    }
    if (!isImplementation) {
    stringBuffer.append(TEXT_802);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_803);
    } else {
    stringBuffer.append(TEXT_804);
    stringBuffer.append(genFeature.getAccessorName());
    if (genClass.hasCollidingUnsetAccessorOperation(genFeature)) {
    stringBuffer.append(TEXT_805);
    }
    stringBuffer.append(TEXT_806);
    if (genModel.isReflectiveDelegation()) {
    stringBuffer.append(TEXT_807);
    stringBuffer.append(genFeature.getQualifiedFeatureAccessor());
    stringBuffer.append(TEXT_808);
    } else if (!genFeature.isVolatile()) {
    if (genFeature.isListType()) {
    if (genModel.isVirtualDelegation()) {
    stringBuffer.append(TEXT_809);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_810);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_811);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_812);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(positiveOffsetCorrection);
    stringBuffer.append(TEXT_813);
    }
    stringBuffer.append(TEXT_814);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_815);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.InternalEList"));
    stringBuffer.append(TEXT_816);
    stringBuffer.append(singleWildcard);
    stringBuffer.append(TEXT_817);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_818);
    } else if (genFeature.isBidirectional() || genFeature.isEffectiveContains()) {
    if (genModel.isVirtualDelegation()) {
    stringBuffer.append(TEXT_819);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_820);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_821);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_822);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(positiveOffsetCorrection);
    stringBuffer.append(TEXT_823);
    }
    stringBuffer.append(TEXT_824);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_825);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.notify.NotificationChain"));
    stringBuffer.append(TEXT_826);
    if (!genFeature.isBidirectional()) {
    stringBuffer.append(TEXT_827);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.InternalEObject"));
    stringBuffer.append(TEXT_828);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_829);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(negativeOffsetCorrection);
    stringBuffer.append(TEXT_830);
    } else { GenFeature reverseFeature = genFeature.getReverse(); GenClass targetClass = reverseFeature.getGenClass(); String reverseOffsetCorrection = targetClass.hasOffsetCorrection() ? " + " + genClass.getOffsetCorrectionField(reverseFeature) : "";
    stringBuffer.append(TEXT_831);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.InternalEObject"));
    stringBuffer.append(TEXT_832);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_833);
    stringBuffer.append(targetClass.getQualifiedFeatureID(reverseFeature));
    stringBuffer.append(reverseOffsetCorrection);
    stringBuffer.append(TEXT_834);
    stringBuffer.append(targetClass.getImportedInterfaceName());
    stringBuffer.append(TEXT_835);
    }
    stringBuffer.append(TEXT_836);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_837);
    if (genModel.isVirtualDelegation()) {
    stringBuffer.append(TEXT_838);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_839);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(positiveOffsetCorrection);
    stringBuffer.append(TEXT_840);
    } else if (genClass.isESetFlag(genFeature)) {
    if (!genModel.isSuppressNotification()) {
    stringBuffer.append(TEXT_841);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_842);
    stringBuffer.append(genClass.getESetFlagsField(genFeature));
    stringBuffer.append(TEXT_843);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_844);
    }
    stringBuffer.append(TEXT_845);
    stringBuffer.append(genClass.getESetFlagsField(genFeature));
    stringBuffer.append(TEXT_846);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_847);
    } else {
    if (!genModel.isSuppressNotification()) {
    stringBuffer.append(TEXT_848);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_849);
    stringBuffer.append(genFeature.getUncapName());
    stringBuffer.append(TEXT_850);
    }
    stringBuffer.append(TEXT_851);
    stringBuffer.append(genFeature.getUncapName());
    stringBuffer.append(TEXT_852);
    }
    if (!genModel.isSuppressNotification()) {
    stringBuffer.append(TEXT_853);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.impl.ENotificationImpl"));
    stringBuffer.append(TEXT_854);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.notify.Notification"));
    stringBuffer.append(TEXT_855);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(positiveOffsetCorrection);
    stringBuffer.append(TEXT_856);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_857);
    }
    stringBuffer.append(TEXT_858);
    } else {
    if (genClass.isFlag(genFeature)) {
    if (!genModel.isSuppressNotification()) {
    stringBuffer.append(TEXT_859);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_860);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_861);
    stringBuffer.append(genClass.getFlagsField(genFeature));
    stringBuffer.append(TEXT_862);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_863);
    }
    } else if (genModel.isVirtualDelegation() && !genFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_864);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_865);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(positiveOffsetCorrection);
    stringBuffer.append(TEXT_866);
    } else {
    if (!genModel.isSuppressNotification()) {
    stringBuffer.append(TEXT_867);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_868);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_869);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_870);
    }
    }
    if (genModel.isVirtualDelegation() && !genFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_871);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_872);
    } else if (genClass.isESetFlag(genFeature)) {
    stringBuffer.append(TEXT_873);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_874);
    stringBuffer.append(genClass.getESetFlagsField(genFeature));
    stringBuffer.append(TEXT_875);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_876);
    } else {
    if (!genModel.isSuppressNotification()) {
    stringBuffer.append(TEXT_877);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_878);
    stringBuffer.append(genFeature.getUncapName());
    stringBuffer.append(TEXT_879);
    }
    }
    if (genFeature.isReferenceType()) {
    stringBuffer.append(TEXT_880);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_881);
    if (!genModel.isVirtualDelegation()) {
    if (genClass.isESetFlag(genFeature)) {
    stringBuffer.append(TEXT_882);
    stringBuffer.append(genClass.getESetFlagsField(genFeature));
    stringBuffer.append(TEXT_883);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_884);
    } else {
    stringBuffer.append(TEXT_885);
    stringBuffer.append(genFeature.getUncapName());
    stringBuffer.append(TEXT_886);
    }
    }
    if (!genModel.isSuppressNotification()) {
    stringBuffer.append(TEXT_887);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.impl.ENotificationImpl"));
    stringBuffer.append(TEXT_888);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.notify.Notification"));
    stringBuffer.append(TEXT_889);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(positiveOffsetCorrection);
    stringBuffer.append(TEXT_890);
    if (genModel.isVirtualDelegation()) {
    stringBuffer.append(TEXT_891);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_892);
    } else {
    stringBuffer.append(TEXT_893);
    stringBuffer.append(genFeature.getCapName());
    }
    stringBuffer.append(TEXT_894);
    if (genModel.isVirtualDelegation()) {
    stringBuffer.append(TEXT_895);
    } else {
    stringBuffer.append(TEXT_896);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_897);
    }
    stringBuffer.append(TEXT_898);
    }
    } else {
    if (genClass.isFlag(genFeature)) {
    stringBuffer.append(TEXT_899);
    stringBuffer.append(genFeature.getEDefault());
    stringBuffer.append(TEXT_900);
    stringBuffer.append(genClass.getFlagsField(genFeature));
    stringBuffer.append(TEXT_901);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_902);
    stringBuffer.append(genClass.getFlagsField(genFeature));
    stringBuffer.append(TEXT_903);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_904);
    } else if (!genModel.isVirtualDelegation() || genFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_905);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_906);
    stringBuffer.append(genFeature.getEDefault());
    stringBuffer.append(TEXT_907);
    }
    if (!genModel.isVirtualDelegation() || genFeature.isPrimitiveType()) {
    if (genClass.isESetFlag(genFeature)) {
    stringBuffer.append(TEXT_908);
    stringBuffer.append(genClass.getESetFlagsField(genFeature));
    stringBuffer.append(TEXT_909);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_910);
    } else {
    stringBuffer.append(TEXT_911);
    stringBuffer.append(genFeature.getUncapName());
    stringBuffer.append(TEXT_912);
    }
    }
    if (!genModel.isSuppressNotification()) {
    stringBuffer.append(TEXT_913);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.impl.ENotificationImpl"));
    stringBuffer.append(TEXT_914);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.notify.Notification"));
    stringBuffer.append(TEXT_915);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(positiveOffsetCorrection);
    stringBuffer.append(TEXT_916);
    if (genModel.isVirtualDelegation() && !genFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_917);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_918);
    stringBuffer.append(genFeature.getEDefault());
    } else {
    stringBuffer.append(TEXT_919);
    stringBuffer.append(genFeature.getCapName());
    }
    stringBuffer.append(TEXT_920);
    stringBuffer.append(genFeature.getEDefault());
    stringBuffer.append(TEXT_921);
    if (genModel.isVirtualDelegation() && !genFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_922);
    } else {
    stringBuffer.append(TEXT_923);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_924);
    }
    stringBuffer.append(TEXT_925);
    }
    }
    }
    } else if (genFeature.hasDelegateFeature()) { GenFeature delegateFeature = genFeature.getDelegateFeature();
    if (delegateFeature.isWrappedFeatureMapType()) {
    stringBuffer.append(TEXT_926);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.FeatureMap"));
    stringBuffer.append(TEXT_927);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.FeatureMap"));
    stringBuffer.append(TEXT_928);
    stringBuffer.append(delegateFeature.getAccessorName());
    stringBuffer.append(TEXT_929);
    stringBuffer.append(genFeature.getQualifiedFeatureAccessor());
    stringBuffer.append(TEXT_930);
    } else {
    stringBuffer.append(TEXT_931);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.FeatureMap"));
    stringBuffer.append(TEXT_932);
    stringBuffer.append(delegateFeature.getAccessorName());
    stringBuffer.append(TEXT_933);
    stringBuffer.append(genFeature.getQualifiedFeatureAccessor());
    stringBuffer.append(TEXT_934);
    }
    } else if (genClass.getUnsetAccessorOperation(genFeature) != null) {
    stringBuffer.append(TEXT_935);
    stringBuffer.append(genClass.getUnsetAccessorOperation(genFeature).getBody(genModel.getIndentation(stringBuffer)));
    } else {
    stringBuffer.append(TEXT_936);
    stringBuffer.append(genFeature.getFormattedName());
    stringBuffer.append(TEXT_937);
    stringBuffer.append(genFeature.getFeatureKind());
    stringBuffer.append(TEXT_938);
    //Class/unsetGenFeature.todo.override.javajetinc
    }
    stringBuffer.append(TEXT_939);
    }
    //Class/unsetGenFeature.override.javajetinc
    }
    if (genFeature.isIsSet() && (isImplementation || !genFeature.isSuppressedIsSetVisibility())) {
    if (isInterface) {
    stringBuffer.append(TEXT_940);
    stringBuffer.append(genClass.getQualifiedInterfaceName());
    stringBuffer.append(TEXT_941);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_942);
    stringBuffer.append(genFeature.getFormattedName());
    stringBuffer.append(TEXT_943);
    stringBuffer.append(genFeature.getFeatureKind());
    stringBuffer.append(TEXT_944);
    stringBuffer.append(TEXT_945);
    stringBuffer.append(genFeature.getFormattedName());
    stringBuffer.append(TEXT_946);
    stringBuffer.append(genFeature.getFeatureKind());
    stringBuffer.append(TEXT_947);
    if (genFeature.isChangeable() && !genFeature.isSuppressedUnsetVisibility()) {
    stringBuffer.append(TEXT_948);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_949);
    }
    stringBuffer.append(TEXT_950);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_951);
    if (!genFeature.isListType() && genFeature.isChangeable() && !genFeature.isSuppressedSetVisibility()) {
    stringBuffer.append(TEXT_952);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_953);
    stringBuffer.append(genFeature.getRawImportedBoundType());
    stringBuffer.append(TEXT_954);
    }
    stringBuffer.append(TEXT_955);
    //Class/isSetGenFeature.javadoc.override.javajetinc
    } else {
    stringBuffer.append(TEXT_956);
    if (genModel.getComplianceLevel().getValue() >= GenJDKLevel.JDK50) { //Class/isSetGenFeature.annotations.insert.javajetinc
    }
    }
    if (!isImplementation) {
    stringBuffer.append(TEXT_957);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_958);
    } else {
    stringBuffer.append(TEXT_959);
    stringBuffer.append(genFeature.getAccessorName());
    if (genClass.hasCollidingIsSetAccessorOperation(genFeature)) {
    stringBuffer.append(TEXT_960);
    }
    stringBuffer.append(TEXT_961);
    if (genModel.isReflectiveDelegation()) {
    stringBuffer.append(TEXT_962);
    stringBuffer.append(genFeature.getQualifiedFeatureAccessor());
    stringBuffer.append(TEXT_963);
    } else if (!genFeature.isVolatile()) {
    if (genFeature.isListType()) {
    if (genModel.isVirtualDelegation()) {
    stringBuffer.append(TEXT_964);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_965);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_966);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_967);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(positiveOffsetCorrection);
    stringBuffer.append(TEXT_968);
    }
    stringBuffer.append(TEXT_969);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_970);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.InternalEList"));
    stringBuffer.append(TEXT_971);
    stringBuffer.append(singleWildcard);
    stringBuffer.append(TEXT_972);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_973);
    } else {
    if (genModel.isVirtualDelegation() && !genFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_974);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(positiveOffsetCorrection);
    stringBuffer.append(TEXT_975);
    } else if (genClass.isESetFlag(genFeature)) {
    stringBuffer.append(TEXT_976);
    stringBuffer.append(genClass.getESetFlagsField(genFeature));
    stringBuffer.append(TEXT_977);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_978);
    } else {
    stringBuffer.append(TEXT_979);
    stringBuffer.append(genFeature.getUncapName());
    stringBuffer.append(TEXT_980);
    }
    }
    } else if (genFeature.hasDelegateFeature()) { GenFeature delegateFeature = genFeature.getDelegateFeature();
    if (delegateFeature.isWrappedFeatureMapType()) {
    stringBuffer.append(TEXT_981);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.FeatureMap"));
    stringBuffer.append(TEXT_982);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.FeatureMap"));
    stringBuffer.append(TEXT_983);
    stringBuffer.append(delegateFeature.getAccessorName());
    stringBuffer.append(TEXT_984);
    stringBuffer.append(genFeature.getQualifiedFeatureAccessor());
    stringBuffer.append(TEXT_985);
    } else {
    stringBuffer.append(TEXT_986);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.FeatureMap"));
    stringBuffer.append(TEXT_987);
    stringBuffer.append(delegateFeature.getAccessorName());
    stringBuffer.append(TEXT_988);
    stringBuffer.append(genFeature.getQualifiedFeatureAccessor());
    stringBuffer.append(TEXT_989);
    }
    } else if (genClass.getIsSetAccessorOperation(genFeature) != null) {
    stringBuffer.append(TEXT_990);
    stringBuffer.append(genClass.getIsSetAccessorOperation(genFeature).getBody(genModel.getIndentation(stringBuffer)));
    } else {
    stringBuffer.append(TEXT_991);
    stringBuffer.append(genFeature.getFormattedName());
    stringBuffer.append(TEXT_992);
    stringBuffer.append(genFeature.getFeatureKind());
    stringBuffer.append(TEXT_993);
    //Class/isSetGenFeature.todo.override.javajetinc
    }
    stringBuffer.append(TEXT_994);
    }
    //Class/isSetGenFeature.override.javajetinc
    }
    //Class/genFeature.override.javajetinc
    }//for
    }}.run();
    for (GenOperation genOperation : (isImplementation ? genClass.getImplementedGenOperations() : genClass.getDeclaredGenOperations())) {
    if (isInterface) {
    stringBuffer.append(TEXT_995);
    stringBuffer.append(TEXT_996);
    if (genOperation.hasDocumentation() || genOperation.hasParameterDocumentation()) {
    stringBuffer.append(TEXT_997);
    if (genOperation.hasDocumentation()) {
    stringBuffer.append(TEXT_998);
    stringBuffer.append(genOperation.getDocumentation(genModel.getIndentation(stringBuffer)));
    }
    for (GenParameter genParameter : genOperation.getGenParameters()) {
    if (genParameter.hasDocumentation()) { String documentation = genParameter.getDocumentation("");
    if (documentation.contains("\n") || documentation.contains("\r")) {
    stringBuffer.append(TEXT_999);
    stringBuffer.append(genParameter.getName());
    stringBuffer.append(TEXT_1000);
    stringBuffer.append(genParameter.getDocumentation(genModel.getIndentation(stringBuffer)));
    } else {
    stringBuffer.append(TEXT_1001);
    stringBuffer.append(genParameter.getName());
    stringBuffer.append(TEXT_1002);
    stringBuffer.append(genParameter.getDocumentation(genModel.getIndentation(stringBuffer)));
    }
    }
    }
    stringBuffer.append(TEXT_1003);
    }
    if (!genModel.isSuppressEMFModelTags()) { boolean first = true; for (StringTokenizer stringTokenizer = new StringTokenizer(genOperation.getModelInfo(), "\n\r"); stringTokenizer.hasMoreTokens(); ) { String modelInfo = stringTokenizer.nextToken(); if (first) { first = false;
    stringBuffer.append(TEXT_1004);
    stringBuffer.append(modelInfo);
    } else {
    stringBuffer.append(TEXT_1005);
    stringBuffer.append(modelInfo);
    }} if (first) {
    stringBuffer.append(TEXT_1006);
    }}
    stringBuffer.append(TEXT_1007);
    //Class/genOperation.javadoc.override.javajetinc
    } else {
    stringBuffer.append(TEXT_1008);
    if (genModel.getComplianceLevel().getValue() >= GenJDKLevel.JDK50) { //Class/genOperation.annotations.insert.javajetinc
    }
    }
    if (!isImplementation) {
    stringBuffer.append(TEXT_1009);
    stringBuffer.append(genOperation.getTypeParameters(genClass));
    stringBuffer.append(genOperation.getImportedType(genClass));
    stringBuffer.append(TEXT_1010);
    stringBuffer.append(genOperation.getName());
    stringBuffer.append(TEXT_1011);
    stringBuffer.append(genOperation.getParameters(genClass));
    stringBuffer.append(TEXT_1012);
    stringBuffer.append(genOperation.getThrows(genClass));
    stringBuffer.append(TEXT_1013);
    } else {
    stringBuffer.append(TEXT_1014);
    stringBuffer.append(genOperation.getTypeParameters(genClass));
    stringBuffer.append(genOperation.getImportedType(genClass));
    stringBuffer.append(TEXT_1015);
    stringBuffer.append(genOperation.getName());
    stringBuffer.append(TEXT_1016);
    stringBuffer.append(genOperation.getParameters(genClass));
    stringBuffer.append(TEXT_1017);
    stringBuffer.append(genOperation.getThrows(genClass));
    stringBuffer.append(TEXT_1018);
    if (genOperation.hasBody()) {
    stringBuffer.append(TEXT_1019);
    stringBuffer.append(genOperation.getBody(genModel.getIndentation(stringBuffer)));
    } else if (genOperation.isInvariant()) {GenClass opClass = genOperation.getGenClass(); String diagnostics = genOperation.getGenParameters().get(0).getName(); String context = genOperation.getGenParameters().get(1).getName();
    stringBuffer.append(TEXT_1020);
    stringBuffer.append(diagnostics);
    stringBuffer.append(TEXT_1021);
    stringBuffer.append(diagnostics);
    stringBuffer.append(TEXT_1022);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.util.BasicDiagnostic"));
    stringBuffer.append(TEXT_1023);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.util.Diagnostic"));
    stringBuffer.append(TEXT_1024);
    stringBuffer.append(opClass.getGenPackage().getImportedValidatorClassName());
    stringBuffer.append(TEXT_1025);
    stringBuffer.append(opClass.getGenPackage().getImportedValidatorClassName());
    stringBuffer.append(TEXT_1026);
    stringBuffer.append(opClass.getOperationID(genOperation));
    stringBuffer.append(TEXT_1027);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.plugin.EcorePlugin"));
    stringBuffer.append(TEXT_1028);
    stringBuffer.append(genOperation.getName());
    stringBuffer.append(TEXT_1029);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.EObjectValidator"));
    stringBuffer.append(TEXT_1030);
    stringBuffer.append(context);
    stringBuffer.append(TEXT_1031);
    stringBuffer.append(genModel.getNonNLS());
    stringBuffer.append(genModel.getNonNLS(2));
    stringBuffer.append(TEXT_1032);
    } else {
    stringBuffer.append(TEXT_1033);
    //Class/implementedGenOperation.todo.override.javajetinc
    }
    stringBuffer.append(TEXT_1034);
    }
    //Class/implementedGenOperation.override.javajetinc
    }//for
    if (isImplementation && !genModel.isReflectiveDelegation() && genClass.implementsAny(genClass.getEInverseAddGenFeatures())) {
    stringBuffer.append(TEXT_1035);
    if (genModel.useGenerics()) {
    for (GenFeature genFeature : genClass.getEInverseAddGenFeatures()) {
    if (genFeature.isUncheckedCast(genClass)) {
    stringBuffer.append(TEXT_1036);
    break; }
    }
    }
    if (genModel.useClassOverrideAnnotation()) {
    stringBuffer.append(TEXT_1037);
    }
    stringBuffer.append(TEXT_1038);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.notify.NotificationChain"));
    stringBuffer.append(TEXT_1039);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.InternalEObject"));
    stringBuffer.append(TEXT_1040);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.notify.NotificationChain"));
    stringBuffer.append(TEXT_1041);
    stringBuffer.append(negativeOffsetCorrection);
    stringBuffer.append(TEXT_1042);
    for (GenFeature genFeature : genClass.getEInverseAddGenFeatures()) {
    stringBuffer.append(TEXT_1043);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(TEXT_1044);
    if (genFeature.isListType()) { String cast = "("  + genModel.getImportedName("org.eclipse.emf.ecore.util.InternalEList") + (!genModel.useGenerics() ? ")" : "<" + genModel.getImportedName("org.eclipse.emf.ecore.InternalEObject") + ">)(" + genModel.getImportedName("org.eclipse.emf.ecore.util.InternalEList") + "<?>)");
    if (genFeature.isMapType() && genFeature.isEffectiveSuppressEMFTypes()) {
    stringBuffer.append(TEXT_1045);
    stringBuffer.append(cast);
    stringBuffer.append(TEXT_1046);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.util.EMap"));
    stringBuffer.append(TEXT_1047);
    stringBuffer.append(genFeature.getImportedMapTemplateArguments(genClass));
    stringBuffer.append(TEXT_1048);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_1049);
    } else {
    stringBuffer.append(TEXT_1050);
    stringBuffer.append(cast);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_1051);
    }
    } else if (genFeature.isContainer()) {
    stringBuffer.append(TEXT_1052);
    if (genFeature.isBasicSet()) {
    stringBuffer.append(TEXT_1053);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_1054);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_1055);
    } else {
    stringBuffer.append(TEXT_1056);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(positiveOffsetCorrection);
    stringBuffer.append(TEXT_1057);
    }
    } else {
    if (genClass.getImplementingGenModel(genFeature).isVirtualDelegation()) {
    stringBuffer.append(TEXT_1058);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_1059);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_1060);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_1061);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(positiveOffsetCorrection);
    stringBuffer.append(TEXT_1062);
    } else if (genFeature.isVolatile()) {
    stringBuffer.append(TEXT_1063);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_1064);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_1065);
    if (genFeature.isResolveProxies()) {
    stringBuffer.append(TEXT_1066);
    stringBuffer.append(genFeature.getAccessorName());
    } else {
    stringBuffer.append(genFeature.getGetAccessor());
    }
    stringBuffer.append(TEXT_1067);
    }
    stringBuffer.append(TEXT_1068);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_1069);
    if (genFeature.isEffectiveContains()) {
    stringBuffer.append(TEXT_1070);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.InternalEObject"));
    stringBuffer.append(TEXT_1071);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_1072);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(negativeOffsetCorrection);
    stringBuffer.append(TEXT_1073);
    } else { GenFeature reverseFeature = genFeature.getReverse(); GenClass targetClass = reverseFeature.getGenClass(); String reverseOffsetCorrection = targetClass.hasOffsetCorrection() ? " + " + genClass.getOffsetCorrectionField(reverseFeature) : "";
    stringBuffer.append(TEXT_1074);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.InternalEObject"));
    stringBuffer.append(TEXT_1075);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_1076);
    stringBuffer.append(targetClass.getQualifiedFeatureID(reverseFeature));
    stringBuffer.append(reverseOffsetCorrection);
    stringBuffer.append(TEXT_1077);
    stringBuffer.append(targetClass.getImportedInterfaceName());
    stringBuffer.append(TEXT_1078);
    }
    stringBuffer.append(TEXT_1079);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_1080);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_1081);
    }
    }
    stringBuffer.append(TEXT_1082);
    if (genModel.isMinimalReflectiveMethods()) {
    stringBuffer.append(TEXT_1083);
    } else {
    stringBuffer.append(TEXT_1084);
    }
    stringBuffer.append(TEXT_1085);
    }
    if (isImplementation && !genModel.isReflectiveDelegation() && genClass.implementsAny(genClass.getEInverseRemoveGenFeatures())) {
    stringBuffer.append(TEXT_1086);
    if (genModel.useClassOverrideAnnotation()) {
    stringBuffer.append(TEXT_1087);
    }
    stringBuffer.append(TEXT_1088);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.notify.NotificationChain"));
    stringBuffer.append(TEXT_1089);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.InternalEObject"));
    stringBuffer.append(TEXT_1090);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.notify.NotificationChain"));
    stringBuffer.append(TEXT_1091);
    stringBuffer.append(negativeOffsetCorrection);
    stringBuffer.append(TEXT_1092);
    for (GenFeature genFeature : genClass.getEInverseRemoveGenFeatures()) {
    stringBuffer.append(TEXT_1093);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(TEXT_1094);
    if (genFeature.isListType()) {
    if (genFeature.isMapType() && genFeature.isEffectiveSuppressEMFTypes()) {
    stringBuffer.append(TEXT_1095);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.InternalEList"));
    stringBuffer.append(singleWildcard);
    stringBuffer.append(TEXT_1096);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.util.EMap"));
    stringBuffer.append(TEXT_1097);
    stringBuffer.append(genFeature.getImportedMapTemplateArguments(genClass));
    stringBuffer.append(TEXT_1098);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_1099);
    } else if (genFeature.isWrappedFeatureMapType()) {
    stringBuffer.append(TEXT_1100);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.InternalEList"));
    stringBuffer.append(singleWildcard);
    stringBuffer.append(TEXT_1101);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.FeatureMap"));
    stringBuffer.append(TEXT_1102);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_1103);
    } else {
    stringBuffer.append(TEXT_1104);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.InternalEList"));
    stringBuffer.append(singleWildcard);
    stringBuffer.append(TEXT_1105);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_1106);
    }
    } else if (genFeature.isContainer() && !genFeature.isBasicSet()) {
    stringBuffer.append(TEXT_1107);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(positiveOffsetCorrection);
    stringBuffer.append(TEXT_1108);
    } else if (genFeature.isUnsettable()) {
    stringBuffer.append(TEXT_1109);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_1110);
    } else {
    stringBuffer.append(TEXT_1111);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_1112);
    }
    }
    stringBuffer.append(TEXT_1113);
    if (genModel.isMinimalReflectiveMethods()) {
    stringBuffer.append(TEXT_1114);
    } else {
    stringBuffer.append(TEXT_1115);
    }
    stringBuffer.append(TEXT_1116);
    }
    if (isImplementation && !genModel.isReflectiveDelegation() && genClass.implementsAny(genClass.getEBasicRemoveFromContainerGenFeatures())) {
    stringBuffer.append(TEXT_1117);
    if (genModel.useClassOverrideAnnotation()) {
    stringBuffer.append(TEXT_1118);
    }
    stringBuffer.append(TEXT_1119);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.notify.NotificationChain"));
    stringBuffer.append(TEXT_1120);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.notify.NotificationChain"));
    stringBuffer.append(TEXT_1121);
    stringBuffer.append(negativeOffsetCorrection);
    stringBuffer.append(TEXT_1122);
    for (GenFeature genFeature : genClass.getEBasicRemoveFromContainerGenFeatures()) {
    GenFeature reverseFeature = genFeature.getReverse(); GenClass targetClass = reverseFeature.getGenClass(); String reverseOffsetCorrection = targetClass.hasOffsetCorrection() ? " + " + genClass.getOffsetCorrectionField(reverseFeature) : "";
    stringBuffer.append(TEXT_1123);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(TEXT_1124);
    stringBuffer.append(targetClass.getQualifiedFeatureID(reverseFeature));
    stringBuffer.append(reverseOffsetCorrection);
    stringBuffer.append(TEXT_1125);
    stringBuffer.append(targetClass.getImportedInterfaceName());
    stringBuffer.append(TEXT_1126);
    }
    stringBuffer.append(TEXT_1127);
    if (genModel.isMinimalReflectiveMethods()) {
    stringBuffer.append(TEXT_1128);
    } else {
    stringBuffer.append(TEXT_1129);
    }
    stringBuffer.append(TEXT_1130);
    }
    if (isImplementation && !genModel.isReflectiveDelegation() && genClass.implementsAny(genClass.getEGetGenFeatures())) {
    stringBuffer.append(TEXT_1131);
    if (genModel.useClassOverrideAnnotation()) {
    stringBuffer.append(TEXT_1132);
    }
    stringBuffer.append(TEXT_1133);
    stringBuffer.append(negativeOffsetCorrection);
    stringBuffer.append(TEXT_1134);
    for (GenFeature genFeature : genClass.getEGetGenFeatures()) {
    stringBuffer.append(TEXT_1135);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(TEXT_1136);
    if (genFeature.isPrimitiveType()) {
    if (genFeature.isBooleanType()) {
    stringBuffer.append(TEXT_1137);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_1138);
    } else {
    stringBuffer.append(TEXT_1139);
    stringBuffer.append(genFeature.getObjectType(genClass));
    stringBuffer.append(TEXT_1140);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_1141);
    }
    } else if (genFeature.isResolveProxies() && !genFeature.isListType()) {
    stringBuffer.append(TEXT_1142);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_1143);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_1144);
    } else if (genFeature.isMapType()) {
    if (genFeature.isEffectiveSuppressEMFTypes()) {
    stringBuffer.append(TEXT_1145);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.util.EMap"));
    stringBuffer.append(TEXT_1146);
    stringBuffer.append(genFeature.getImportedMapTemplateArguments(genClass));
    stringBuffer.append(TEXT_1147);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_1148);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_1149);
    } else {
    stringBuffer.append(TEXT_1150);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_1151);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_1152);
    }
    } else if (genFeature.isWrappedFeatureMapType()) {
    stringBuffer.append(TEXT_1153);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.FeatureMap"));
    stringBuffer.append(TEXT_1154);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_1155);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_1156);
    } else if (genFeature.isFeatureMapType()) {
    stringBuffer.append(TEXT_1157);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_1158);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.FeatureMap"));
    stringBuffer.append(TEXT_1159);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_1160);
    } else {
    stringBuffer.append(TEXT_1161);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_1162);
    }
    }
    stringBuffer.append(TEXT_1163);
    if (genModel.isMinimalReflectiveMethods()) {
    stringBuffer.append(TEXT_1164);
    } else {
    stringBuffer.append(TEXT_1165);
    }
    stringBuffer.append(TEXT_1166);
    }
    if (isImplementation && !genModel.isReflectiveDelegation() && genClass.implementsAny(genClass.getESetGenFeatures())) {
    stringBuffer.append(TEXT_1167);
    if (genModel.useGenerics()) {
    for (GenFeature genFeature : genClass.getESetGenFeatures()) {
    if (genFeature.isUncheckedCast(genClass) && !genFeature.isFeatureMapType() && !genFeature.isMapType()) {
    stringBuffer.append(TEXT_1168);
    break; }
    }
    }
    if (genModel.useClassOverrideAnnotation()) {
    stringBuffer.append(TEXT_1169);
    }
    stringBuffer.append(TEXT_1170);
    stringBuffer.append(negativeOffsetCorrection);
    stringBuffer.append(TEXT_1171);
    for (GenFeature genFeature : genClass.getESetGenFeatures()) {
    stringBuffer.append(TEXT_1172);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(TEXT_1173);
    if (genFeature.isListType()) {
    if (genFeature.isWrappedFeatureMapType()) {
    stringBuffer.append(TEXT_1174);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.FeatureMap"));
    stringBuffer.append(TEXT_1175);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.FeatureMap"));
    stringBuffer.append(TEXT_1176);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_1177);
    } else if (genFeature.isFeatureMapType()) {
    stringBuffer.append(TEXT_1178);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.FeatureMap"));
    stringBuffer.append(TEXT_1179);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_1180);
    } else if (genFeature.isMapType()) {
    if (genFeature.isEffectiveSuppressEMFTypes()) {
    stringBuffer.append(TEXT_1181);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.EStructuralFeature"));
    stringBuffer.append(TEXT_1182);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.util.EMap"));
    stringBuffer.append(TEXT_1183);
    stringBuffer.append(genFeature.getImportedMapTemplateArguments(genClass));
    stringBuffer.append(TEXT_1184);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_1185);
    } else {
    stringBuffer.append(TEXT_1186);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.EStructuralFeature"));
    stringBuffer.append(TEXT_1187);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_1188);
    }
    } else {
    stringBuffer.append(TEXT_1189);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_1190);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_1191);
    stringBuffer.append(genModel.getImportedName("java.util.Collection"));
    if (genModel.getComplianceLevel().getValue() >= GenJDKLevel.JDK50) {
    stringBuffer.append(TEXT_1192);
    stringBuffer.append(genFeature.getListItemType(genClass));
    stringBuffer.append(TEXT_1193);
    }
    stringBuffer.append(TEXT_1194);
    }
    } else if (genFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_1195);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_1196);
    stringBuffer.append(genFeature.getObjectType(genClass));
    stringBuffer.append(TEXT_1197);
    stringBuffer.append(genFeature.getPrimitiveValueFunction());
    stringBuffer.append(TEXT_1198);
    } else {
    stringBuffer.append(TEXT_1199);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_1200);
    if (genFeature.getTypeGenDataType() == null || !genFeature.getTypeGenDataType().isObjectType() || !genFeature.getRawType().equals(genFeature.getType(genClass))) {
    stringBuffer.append(TEXT_1201);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_1202);
    }
    stringBuffer.append(TEXT_1203);
    }
    stringBuffer.append(TEXT_1204);
    }
    stringBuffer.append(TEXT_1205);
    if (genModel.isMinimalReflectiveMethods()) {
    stringBuffer.append(TEXT_1206);
    } else {
    stringBuffer.append(TEXT_1207);
    }
    stringBuffer.append(TEXT_1208);
    }
    if (isImplementation && !genModel.isReflectiveDelegation() && genClass.implementsAny(genClass.getEUnsetGenFeatures())) {
    stringBuffer.append(TEXT_1209);
    if (genModel.useClassOverrideAnnotation()) {
    stringBuffer.append(TEXT_1210);
    }
    stringBuffer.append(TEXT_1211);
    stringBuffer.append(negativeOffsetCorrection);
    stringBuffer.append(TEXT_1212);
    for (GenFeature genFeature : genClass.getEUnsetGenFeatures()) {
    stringBuffer.append(TEXT_1213);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(TEXT_1214);
    if (genFeature.isListType() && !genFeature.isUnsettable()) {
    if (genFeature.isWrappedFeatureMapType()) {
    stringBuffer.append(TEXT_1215);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.FeatureMap"));
    stringBuffer.append(TEXT_1216);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_1217);
    } else {
    stringBuffer.append(TEXT_1218);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_1219);
    }
    } else if (genFeature.isUnsettable()) {
    stringBuffer.append(TEXT_1220);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_1221);
    } else if (!genFeature.hasEDefault()) {
    stringBuffer.append(TEXT_1222);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_1223);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_1224);
    } else {
    stringBuffer.append(TEXT_1225);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_1226);
    stringBuffer.append(genFeature.getEDefault());
    stringBuffer.append(TEXT_1227);
    }
    stringBuffer.append(TEXT_1228);
    }
    stringBuffer.append(TEXT_1229);
    if (genModel.isMinimalReflectiveMethods()) {
    stringBuffer.append(TEXT_1230);
    } else {
    stringBuffer.append(TEXT_1231);
    }
    stringBuffer.append(TEXT_1232);
    //Class/eUnset.override.javajetinc
    }
    if (isImplementation && !genModel.isReflectiveDelegation() && genClass.implementsAny(genClass.getEIsSetGenFeatures())) {
    stringBuffer.append(TEXT_1233);
    if (genModel.useGenerics()) {
    for (GenFeature genFeature : genClass.getEIsSetGenFeatures()) {
    if (genFeature.isListType() && !genFeature.isUnsettable() && !genFeature.isWrappedFeatureMapType() && !genClass.isField(genFeature) && genFeature.isField() && genClass.getImplementingGenModel(genFeature).isVirtualDelegation()) {
    stringBuffer.append(TEXT_1234);
    break; }
    }
    }
    if (genModel.useClassOverrideAnnotation()) {
    stringBuffer.append(TEXT_1235);
    }
    stringBuffer.append(TEXT_1236);
    stringBuffer.append(negativeOffsetCorrection);
    stringBuffer.append(TEXT_1237);
    for (GenFeature genFeature : genClass.getEIsSetGenFeatures()) {
    stringBuffer.append(TEXT_1238);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(TEXT_1239);
    if (genFeature.isListType() && !genFeature.isUnsettable()) {
    if (genFeature.isWrappedFeatureMapType()) {
    if (genFeature.isVolatile()) {
    stringBuffer.append(TEXT_1240);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.FeatureMap"));
    stringBuffer.append(TEXT_1241);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_1242);
    } else {
    stringBuffer.append(TEXT_1243);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_1244);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_1245);
    }
    } else {
    if (genClass.isField(genFeature)) {
    stringBuffer.append(TEXT_1246);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_1247);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_1248);
    } else {
    if (genFeature.isField() && genClass.getImplementingGenModel(genFeature).isVirtualDelegation()) {
    stringBuffer.append(TEXT_1249);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_1250);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_1251);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_1252);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(positiveOffsetCorrection);
    stringBuffer.append(TEXT_1253);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_1254);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_1255);
    } else {
    stringBuffer.append(TEXT_1256);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_1257);
    }
    }
    }
    } else if (genFeature.isUnsettable()) {
    stringBuffer.append(TEXT_1258);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_1259);
    } else if (genFeature.isResolveProxies()) {
    if (genClass.isField(genFeature)) {
    stringBuffer.append(TEXT_1260);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_1261);
    } else {
    if (genFeature.isField() && genClass.getImplementingGenModel(genFeature).isVirtualDelegation()) {
    stringBuffer.append(TEXT_1262);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(positiveOffsetCorrection);
    stringBuffer.append(TEXT_1263);
    } else {
    stringBuffer.append(TEXT_1264);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_1265);
    }
    }
    } else if (!genFeature.hasEDefault()) {
    if (genClass.isField(genFeature)) {
    stringBuffer.append(TEXT_1266);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_1267);
    } else {
    if (genFeature.isField() && genClass.getImplementingGenModel(genFeature).isVirtualDelegation()) {
    stringBuffer.append(TEXT_1268);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(positiveOffsetCorrection);
    stringBuffer.append(TEXT_1269);
    } else {
    stringBuffer.append(TEXT_1270);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_1271);
    }
    }
    } else if (genFeature.isPrimitiveType() || genFeature.isEnumType()) {
    if (genClass.isField(genFeature)) {
    if (genClass.isFlag(genFeature)) {
    stringBuffer.append(TEXT_1272);
    stringBuffer.append(genClass.getFlagsField(genFeature));
    stringBuffer.append(TEXT_1273);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_1274);
    stringBuffer.append(genFeature.getEDefault());
    stringBuffer.append(TEXT_1275);
    } else {
    stringBuffer.append(TEXT_1276);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_1277);
    stringBuffer.append(genFeature.getEDefault());
    stringBuffer.append(TEXT_1278);
    }
    } else {
    if (genFeature.isEnumType() && genFeature.isField() && genClass.getImplementingGenModel(genFeature).isVirtualDelegation()) {
    stringBuffer.append(TEXT_1279);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(positiveOffsetCorrection);
    stringBuffer.append(TEXT_1280);
    stringBuffer.append(genFeature.getEDefault());
    stringBuffer.append(TEXT_1281);
    stringBuffer.append(genFeature.getEDefault());
    stringBuffer.append(TEXT_1282);
    } else {
    stringBuffer.append(TEXT_1283);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_1284);
    stringBuffer.append(genFeature.getEDefault());
    stringBuffer.append(TEXT_1285);
    }
    }
    } else {//datatype
    if (genClass.isField(genFeature)) {
    stringBuffer.append(TEXT_1286);
    stringBuffer.append(genFeature.getEDefault());
    stringBuffer.append(TEXT_1287);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_1288);
    stringBuffer.append(genFeature.getEDefault());
    stringBuffer.append(TEXT_1289);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_1290);
    } else {
    if (genFeature.isField() && genClass.getImplementingGenModel(genFeature).isVirtualDelegation()) {
    stringBuffer.append(TEXT_1291);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_1292);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_1293);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_1294);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(positiveOffsetCorrection);
    stringBuffer.append(TEXT_1295);
    stringBuffer.append(genFeature.getEDefault());
    stringBuffer.append(TEXT_1296);
    stringBuffer.append(genFeature.getEDefault());
    stringBuffer.append(TEXT_1297);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_1298);
    stringBuffer.append(genFeature.getEDefault());
    stringBuffer.append(TEXT_1299);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_1300);
    } else {
    stringBuffer.append(TEXT_1301);
    stringBuffer.append(genFeature.getEDefault());
    stringBuffer.append(TEXT_1302);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_1303);
    stringBuffer.append(genFeature.getEDefault());
    stringBuffer.append(TEXT_1304);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_1305);
    }
    }
    }
    }
    stringBuffer.append(TEXT_1306);
    if (genModel.isMinimalReflectiveMethods()) {
    stringBuffer.append(TEXT_1307);
    } else {
    stringBuffer.append(TEXT_1308);
    }
    stringBuffer.append(TEXT_1309);
    //Class/eIsSet.override.javajetinc
    }
    if (isImplementation && (!genClass.getMixinGenFeatures().isEmpty() || genClass.hasOffsetCorrection() && !genClass.getGenFeatures().isEmpty())) {
    if (!genClass.getMixinGenFeatures().isEmpty()) {
    stringBuffer.append(TEXT_1310);
    if (genModel.useClassOverrideAnnotation()) {
    stringBuffer.append(TEXT_1311);
    }
    stringBuffer.append(TEXT_1312);
    stringBuffer.append(singleWildcard);
    stringBuffer.append(TEXT_1313);
    for (GenClass mixinGenClass : genClass.getMixinGenClasses()) {
    stringBuffer.append(TEXT_1314);
    stringBuffer.append(mixinGenClass.getImportedInterfaceName());
    stringBuffer.append(TEXT_1315);
    stringBuffer.append(negativeOffsetCorrection);
    stringBuffer.append(TEXT_1316);
    for (GenFeature genFeature : mixinGenClass.getGenFeatures()) {
    stringBuffer.append(TEXT_1317);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(TEXT_1318);
    stringBuffer.append(mixinGenClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(TEXT_1319);
    }
    stringBuffer.append(TEXT_1320);
    }
    stringBuffer.append(TEXT_1321);
    }
    stringBuffer.append(TEXT_1322);
    if (genModel.useClassOverrideAnnotation()) {
    stringBuffer.append(TEXT_1323);
    }
    stringBuffer.append(TEXT_1324);
    stringBuffer.append(singleWildcard);
    stringBuffer.append(TEXT_1325);
    for (GenClass mixinGenClass : genClass.getMixinGenClasses()) {
    stringBuffer.append(TEXT_1326);
    stringBuffer.append(mixinGenClass.getImportedInterfaceName());
    stringBuffer.append(TEXT_1327);
    for (GenFeature genFeature : mixinGenClass.getGenFeatures()) {
    stringBuffer.append(TEXT_1328);
    stringBuffer.append(mixinGenClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(TEXT_1329);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(positiveOffsetCorrection);
    stringBuffer.append(TEXT_1330);
    }
    stringBuffer.append(TEXT_1331);
    }
    if (genClass.hasOffsetCorrection() && !genClass.getGenFeatures().isEmpty()) {
    stringBuffer.append(TEXT_1332);
    stringBuffer.append(genClass.getImportedInterfaceName());
    stringBuffer.append(TEXT_1333);
    stringBuffer.append(negativeOffsetCorrection);
    stringBuffer.append(TEXT_1334);
    for (GenFeature genFeature : genClass.getGenFeatures()) {
    stringBuffer.append(TEXT_1335);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(TEXT_1336);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(positiveOffsetCorrection);
    stringBuffer.append(TEXT_1337);
    }
    stringBuffer.append(TEXT_1338);
    }
    stringBuffer.append(TEXT_1339);
    }
    if (isImplementation && genModel.isVirtualDelegation()) { String eVirtualValuesField = genClass.getEVirtualValuesField();
    if (eVirtualValuesField != null) {
    stringBuffer.append(TEXT_1340);
    if (genModel.useClassOverrideAnnotation()) {
    stringBuffer.append(TEXT_1341);
    }
    stringBuffer.append(TEXT_1342);
    stringBuffer.append(eVirtualValuesField);
    stringBuffer.append(TEXT_1343);
    if (genModel.useClassOverrideAnnotation()) {
    stringBuffer.append(TEXT_1344);
    }
    stringBuffer.append(TEXT_1345);
    stringBuffer.append(eVirtualValuesField);
    stringBuffer.append(TEXT_1346);
    }
    { List<String> eVirtualIndexBitFields = genClass.getEVirtualIndexBitFields(new ArrayList<String>());
    if (!eVirtualIndexBitFields.isEmpty()) { List<String> allEVirtualIndexBitFields = genClass.getAllEVirtualIndexBitFields(new ArrayList<String>());
    stringBuffer.append(TEXT_1347);
    if (genModel.useClassOverrideAnnotation()) {
    stringBuffer.append(TEXT_1348);
    }
    stringBuffer.append(TEXT_1349);
    for (int i = 0; i < allEVirtualIndexBitFields.size(); i++) {
    stringBuffer.append(TEXT_1350);
    stringBuffer.append(i);
    stringBuffer.append(TEXT_1351);
    stringBuffer.append(allEVirtualIndexBitFields.get(i));
    stringBuffer.append(TEXT_1352);
    }
    stringBuffer.append(TEXT_1353);
    if (genModel.useClassOverrideAnnotation()) {
    stringBuffer.append(TEXT_1354);
    }
    stringBuffer.append(TEXT_1355);
    for (int i = 0; i < allEVirtualIndexBitFields.size(); i++) {
    stringBuffer.append(TEXT_1356);
    stringBuffer.append(i);
    stringBuffer.append(TEXT_1357);
    stringBuffer.append(allEVirtualIndexBitFields.get(i));
    stringBuffer.append(TEXT_1358);
    }
    stringBuffer.append(TEXT_1359);
    }
    }
    }
    if (isImplementation && !genModel.isReflectiveDelegation() && !genClass.getToStringGenFeatures().isEmpty()) {
    stringBuffer.append(TEXT_1360);
    if (genModel.useClassOverrideAnnotation()) {
    stringBuffer.append(TEXT_1361);
    }
    stringBuffer.append(TEXT_1362);
    { boolean first = true;
    for (GenFeature genFeature : genClass.getToStringGenFeatures()) {
    if (first) { first = false;
    stringBuffer.append(TEXT_1363);
    stringBuffer.append(genFeature.getName());
    stringBuffer.append(TEXT_1364);
    stringBuffer.append(genModel.getNonNLS());
    } else {
    stringBuffer.append(TEXT_1365);
    stringBuffer.append(genFeature.getName());
    stringBuffer.append(TEXT_1366);
    stringBuffer.append(genModel.getNonNLS());
    }
    if (genFeature.isUnsettable() && !genFeature.isListType()) {
    if (genModel.isVirtualDelegation() && !genFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_1367);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(positiveOffsetCorrection);
    stringBuffer.append(TEXT_1368);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(positiveOffsetCorrection);
    stringBuffer.append(TEXT_1369);
    stringBuffer.append(genModel.getNonNLS());
    } else {
    if (genClass.isFlag(genFeature)) {
    stringBuffer.append(TEXT_1370);
    if (genClass.isESetFlag(genFeature)) {
    stringBuffer.append(TEXT_1371);
    stringBuffer.append(genClass.getESetFlagsField(genFeature));
    stringBuffer.append(TEXT_1372);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_1373);
    } else {
    stringBuffer.append(genFeature.getUncapName());
    stringBuffer.append(TEXT_1374);
    }
    stringBuffer.append(TEXT_1375);
    stringBuffer.append(genClass.getFlagsField(genFeature));
    stringBuffer.append(TEXT_1376);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_1377);
    stringBuffer.append(genModel.getNonNLS());
    } else {
    stringBuffer.append(TEXT_1378);
    if (genClass.isESetFlag(genFeature)) {
    stringBuffer.append(TEXT_1379);
    stringBuffer.append(genClass.getESetFlagsField(genFeature));
    stringBuffer.append(TEXT_1380);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_1381);
    } else {
    stringBuffer.append(genFeature.getUncapName());
    stringBuffer.append(TEXT_1382);
    }
    stringBuffer.append(TEXT_1383);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_1384);
    stringBuffer.append(genModel.getNonNLS());
    }
    }
    } else {
    if (genModel.isVirtualDelegation() && !genFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_1385);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(positiveOffsetCorrection);
    if (!genFeature.isListType() && !genFeature.isReferenceType()){
    stringBuffer.append(TEXT_1386);
    stringBuffer.append(genFeature.getEDefault());
    }
    stringBuffer.append(TEXT_1387);
    } else {
    if (genClass.isFlag(genFeature)) {
    stringBuffer.append(TEXT_1388);
    stringBuffer.append(genClass.getFlagsField(genFeature));
    stringBuffer.append(TEXT_1389);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_1390);
    } else {
    stringBuffer.append(TEXT_1391);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_1392);
    }
    }
    }
    }
    }
    stringBuffer.append(TEXT_1393);
    }
    if (isImplementation && genClass.isMapEntry()) { GenFeature keyFeature = genClass.getMapEntryKeyFeature(); GenFeature valueFeature = genClass.getMapEntryValueFeature();
    String objectType = genModel.getImportedName("java.lang.Object");
    String keyType = genModel.getComplianceLevel().getValue() >= GenJDKLevel.JDK50 ? keyFeature.getObjectType(genClass) : objectType;
    String valueType = genModel.getComplianceLevel().getValue() >= GenJDKLevel.JDK50 ? valueFeature.getObjectType(genClass) : objectType;
    String eMapType = genModel.getImportedName("org.eclipse.emf.common.util.EMap") + (genModel.getComplianceLevel().getValue() >= GenJDKLevel.JDK50 ? "<" + keyType + ", " + valueType + ">" : "");
    stringBuffer.append(TEXT_1394);
    stringBuffer.append(objectType);
    stringBuffer.append(TEXT_1395);
    stringBuffer.append(keyType);
    stringBuffer.append(TEXT_1396);
    if (keyFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_1397);
    stringBuffer.append(keyFeature.getObjectType(genClass));
    stringBuffer.append(TEXT_1398);
    } else {
    stringBuffer.append(TEXT_1399);
    }
    stringBuffer.append(TEXT_1400);
    stringBuffer.append(keyType);
    stringBuffer.append(TEXT_1401);
    if (keyFeature.isListType()) {
    stringBuffer.append(TEXT_1402);
    if (!genModel.useGenerics()) {
    stringBuffer.append(TEXT_1403);
    stringBuffer.append(genModel.getImportedName("java.util.Collection"));
    stringBuffer.append(TEXT_1404);
    }
    stringBuffer.append(TEXT_1405);
    } else if (genModel.getComplianceLevel().getValue() >= GenJDKLevel.JDK50) {
    stringBuffer.append(TEXT_1406);
    } else if (keyFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_1407);
    stringBuffer.append(keyFeature.getObjectType(genClass));
    stringBuffer.append(TEXT_1408);
    stringBuffer.append(keyFeature.getPrimitiveValueFunction());
    stringBuffer.append(TEXT_1409);
    } else {
    stringBuffer.append(TEXT_1410);
    stringBuffer.append(keyFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_1411);
    }
    stringBuffer.append(TEXT_1412);
    stringBuffer.append(valueType);
    stringBuffer.append(TEXT_1413);
    if (valueFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_1414);
    stringBuffer.append(valueFeature.getObjectType(genClass));
    stringBuffer.append(TEXT_1415);
    } else {
    stringBuffer.append(TEXT_1416);
    }
    stringBuffer.append(TEXT_1417);
    stringBuffer.append(valueType);
    stringBuffer.append(TEXT_1418);
    stringBuffer.append(valueType);
    stringBuffer.append(TEXT_1419);
    stringBuffer.append(valueType);
    stringBuffer.append(TEXT_1420);
    if (valueFeature.isListType()) {
    stringBuffer.append(TEXT_1421);
    if (!genModel.useGenerics()) {
    stringBuffer.append(TEXT_1422);
    stringBuffer.append(genModel.getImportedName("java.util.Collection"));
    stringBuffer.append(TEXT_1423);
    }
    stringBuffer.append(TEXT_1424);
    } else if (genModel.getComplianceLevel().getValue() >= GenJDKLevel.JDK50) {
    stringBuffer.append(TEXT_1425);
    } else if (valueFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_1426);
    stringBuffer.append(valueFeature.getObjectType(genClass));
    stringBuffer.append(TEXT_1427);
    stringBuffer.append(valueFeature.getPrimitiveValueFunction());
    stringBuffer.append(TEXT_1428);
    } else {
    stringBuffer.append(TEXT_1429);
    stringBuffer.append(valueFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_1430);
    }
    stringBuffer.append(TEXT_1431);
    if (genModel.useGenerics()) {
    stringBuffer.append(TEXT_1432);
    }
    stringBuffer.append(TEXT_1433);
    stringBuffer.append(eMapType);
    stringBuffer.append(TEXT_1434);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.EObject"));
    stringBuffer.append(TEXT_1435);
    stringBuffer.append(eMapType);
    stringBuffer.append(TEXT_1436);
    }
    stringBuffer.append(TEXT_1437);
    stringBuffer.append(isInterface ? " " + genClass.getInterfaceName() : genClass.getClassName());
    // TODO fix the space above
    genModel.emitSortedImports();
    stringBuffer.append(TEXT_1438);
    return stringBuffer.toString();
  }
}
