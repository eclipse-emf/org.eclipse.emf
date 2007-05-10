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
  protected final String TEXT_275 = "()" + NL + "\t{";
  protected final String TEXT_276 = NL + "\t\treturn ";
  protected final String TEXT_277 = "(";
  protected final String TEXT_278 = "(";
  protected final String TEXT_279 = ")eGet(";
  protected final String TEXT_280 = ", true)";
  protected final String TEXT_281 = ").";
  protected final String TEXT_282 = "()";
  protected final String TEXT_283 = ";";
  protected final String TEXT_284 = NL + "\t\t";
  protected final String TEXT_285 = " ";
  protected final String TEXT_286 = " = (";
  protected final String TEXT_287 = ")eVirtualGet(";
  protected final String TEXT_288 = ");";
  protected final String TEXT_289 = NL + "\t\tif (";
  protected final String TEXT_290 = " == null)" + NL + "\t\t{";
  protected final String TEXT_291 = NL + "\t\t\teVirtualSet(";
  protected final String TEXT_292 = ", ";
  protected final String TEXT_293 = " = new ";
  protected final String TEXT_294 = ");";
  protected final String TEXT_295 = NL + "\t\t\t";
  protected final String TEXT_296 = " = new ";
  protected final String TEXT_297 = ";";
  protected final String TEXT_298 = NL + "\t\t}" + NL + "\t\treturn ";
  protected final String TEXT_299 = ";";
  protected final String TEXT_300 = NL + "\t\tif (eContainerFeatureID != ";
  protected final String TEXT_301 = ") return null;" + NL + "\t\treturn (";
  protected final String TEXT_302 = ")eContainer();";
  protected final String TEXT_303 = NL + "\t\t";
  protected final String TEXT_304 = " ";
  protected final String TEXT_305 = " = (";
  protected final String TEXT_306 = ")eVirtualGet(";
  protected final String TEXT_307 = ", ";
  protected final String TEXT_308 = ");";
  protected final String TEXT_309 = NL + "\t\tif (";
  protected final String TEXT_310 = " != null && ";
  protected final String TEXT_311 = ".eIsProxy())" + NL + "\t\t{" + NL + "\t\t\t";
  protected final String TEXT_312 = " old";
  protected final String TEXT_313 = " = (";
  protected final String TEXT_314 = ")";
  protected final String TEXT_315 = ";" + NL + "\t\t\t";
  protected final String TEXT_316 = " = ";
  protected final String TEXT_317 = "eResolveProxy(old";
  protected final String TEXT_318 = ");" + NL + "\t\t\tif (";
  protected final String TEXT_319 = " != old";
  protected final String TEXT_320 = ")" + NL + "\t\t\t{";
  protected final String TEXT_321 = NL + "\t\t\t\t";
  protected final String TEXT_322 = " new";
  protected final String TEXT_323 = " = (";
  protected final String TEXT_324 = ")";
  protected final String TEXT_325 = ";";
  protected final String TEXT_326 = NL + "\t\t\t\t";
  protected final String TEXT_327 = " msgs = old";
  protected final String TEXT_328 = ".eInverseRemove(this, EOPPOSITE_FEATURE_BASE - ";
  protected final String TEXT_329 = ", null, null);";
  protected final String TEXT_330 = NL + "\t\t\t\t";
  protected final String TEXT_331 = " msgs =  old";
  protected final String TEXT_332 = ".eInverseRemove(this, ";
  protected final String TEXT_333 = ", ";
  protected final String TEXT_334 = ".class, null);";
  protected final String TEXT_335 = NL + "\t\t\t\tif (new";
  protected final String TEXT_336 = ".eInternalContainer() == null)" + NL + "\t\t\t\t{";
  protected final String TEXT_337 = NL + "\t\t\t\t\tmsgs = new";
  protected final String TEXT_338 = ".eInverseAdd(this, EOPPOSITE_FEATURE_BASE - ";
  protected final String TEXT_339 = ", null, msgs);";
  protected final String TEXT_340 = NL + "\t\t\t\t\tmsgs =  new";
  protected final String TEXT_341 = ".eInverseAdd(this, ";
  protected final String TEXT_342 = ", ";
  protected final String TEXT_343 = ".class, msgs);";
  protected final String TEXT_344 = NL + "\t\t\t\t}" + NL + "\t\t\t\tif (msgs != null) msgs.dispatch();";
  protected final String TEXT_345 = NL + "\t\t\t\teVirtualSet(";
  protected final String TEXT_346 = ", ";
  protected final String TEXT_347 = ");";
  protected final String TEXT_348 = NL + "\t\t\t\tif (eNotificationRequired())" + NL + "\t\t\t\t\teNotify(new ";
  protected final String TEXT_349 = "(this, ";
  protected final String TEXT_350 = ".RESOLVE, ";
  protected final String TEXT_351 = ", old";
  protected final String TEXT_352 = ", ";
  protected final String TEXT_353 = "));";
  protected final String TEXT_354 = NL + "\t\t\t}" + NL + "\t\t}";
  protected final String TEXT_355 = NL + "\t\treturn (";
  protected final String TEXT_356 = ")eVirtualGet(";
  protected final String TEXT_357 = ", ";
  protected final String TEXT_358 = ");";
  protected final String TEXT_359 = NL + "\t\treturn (";
  protected final String TEXT_360 = " & ";
  protected final String TEXT_361 = "_EFLAG) != 0;";
  protected final String TEXT_362 = NL + "\t\treturn ";
  protected final String TEXT_363 = ";";
  protected final String TEXT_364 = NL + "\t\t";
  protected final String TEXT_365 = " ";
  protected final String TEXT_366 = " = basicGet";
  protected final String TEXT_367 = "();" + NL + "\t\treturn ";
  protected final String TEXT_368 = " != null && ";
  protected final String TEXT_369 = ".eIsProxy() ? ";
  protected final String TEXT_370 = "eResolveProxy((";
  protected final String TEXT_371 = ")";
  protected final String TEXT_372 = ") : ";
  protected final String TEXT_373 = ";";
  protected final String TEXT_374 = NL + "\t\treturn new ";
  protected final String TEXT_375 = "((";
  protected final String TEXT_376 = ".Internal)((";
  protected final String TEXT_377 = ".Internal.Wrapper)get";
  protected final String TEXT_378 = "()).featureMap().";
  protected final String TEXT_379 = "list(";
  protected final String TEXT_380 = "));";
  protected final String TEXT_381 = NL + "\t\treturn (";
  protected final String TEXT_382 = ")get";
  protected final String TEXT_383 = "().";
  protected final String TEXT_384 = "list(";
  protected final String TEXT_385 = ");";
  protected final String TEXT_386 = NL + "\t\treturn ((";
  protected final String TEXT_387 = ".Internal.Wrapper)get";
  protected final String TEXT_388 = "()).featureMap().list(";
  protected final String TEXT_389 = ");";
  protected final String TEXT_390 = NL + "\t\treturn get";
  protected final String TEXT_391 = "().list(";
  protected final String TEXT_392 = ");";
  protected final String TEXT_393 = NL + "\t\treturn ";
  protected final String TEXT_394 = "(";
  protected final String TEXT_395 = "(";
  protected final String TEXT_396 = ")";
  protected final String TEXT_397 = "((";
  protected final String TEXT_398 = ".Internal.Wrapper)get";
  protected final String TEXT_399 = "()).featureMap().get(";
  protected final String TEXT_400 = ", true)";
  protected final String TEXT_401 = ").";
  protected final String TEXT_402 = "()";
  protected final String TEXT_403 = ";";
  protected final String TEXT_404 = NL + "\t\treturn ";
  protected final String TEXT_405 = "(";
  protected final String TEXT_406 = "(";
  protected final String TEXT_407 = ")";
  protected final String TEXT_408 = "get";
  protected final String TEXT_409 = "().get(";
  protected final String TEXT_410 = ", true)";
  protected final String TEXT_411 = ").";
  protected final String TEXT_412 = "()";
  protected final String TEXT_413 = ";";
  protected final String TEXT_414 = NL + "\t\t// TODO: implement this method to return the '";
  protected final String TEXT_415 = "' ";
  protected final String TEXT_416 = NL + "\t\t// Ensure that you remove @generated or mark it @generated NOT";
  protected final String TEXT_417 = NL + "\t\t// The list is expected to implement org.eclipse.emf.ecore.util.InternalEList and org.eclipse.emf.ecore.EStructuralFeature.Setting" + NL + "\t\t// so it's likely that an appropriate subclass of org.eclipse.emf.ecore.util.";
  protected final String TEXT_418 = "EcoreEMap";
  protected final String TEXT_419 = "BasicFeatureMap";
  protected final String TEXT_420 = "EcoreEList";
  protected final String TEXT_421 = " should be used.";
  protected final String TEXT_422 = NL + "\t\tthrow new UnsupportedOperationException();";
  protected final String TEXT_423 = NL + "\t}" + NL;
  protected final String TEXT_424 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */";
  protected final String TEXT_425 = NL + "\tpublic ";
  protected final String TEXT_426 = " basicGet";
  protected final String TEXT_427 = "()" + NL + "\t{";
  protected final String TEXT_428 = NL + "\t\tif (eContainerFeatureID != ";
  protected final String TEXT_429 = ") return null;" + NL + "\t\treturn (";
  protected final String TEXT_430 = ")eInternalContainer();";
  protected final String TEXT_431 = NL + "\t\treturn (";
  protected final String TEXT_432 = ")eVirtualGet(";
  protected final String TEXT_433 = ");";
  protected final String TEXT_434 = NL + "\t\treturn ";
  protected final String TEXT_435 = ";";
  protected final String TEXT_436 = NL + "\t\treturn (";
  protected final String TEXT_437 = ")((";
  protected final String TEXT_438 = ".Internal.Wrapper)get";
  protected final String TEXT_439 = "()).featureMap().get(";
  protected final String TEXT_440 = ", false);";
  protected final String TEXT_441 = NL + "\t\treturn (";
  protected final String TEXT_442 = ")get";
  protected final String TEXT_443 = "().get(";
  protected final String TEXT_444 = ", false);";
  protected final String TEXT_445 = NL + "\t\t// TODO: implement this method to return the '";
  protected final String TEXT_446 = "' ";
  protected final String TEXT_447 = NL + "\t\t// -> do not perform proxy resolution" + NL + "\t\t// Ensure that you remove @generated or mark it @generated NOT" + NL + "\t\tthrow new UnsupportedOperationException();";
  protected final String TEXT_448 = NL + "\t}" + NL;
  protected final String TEXT_449 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */";
  protected final String TEXT_450 = NL + "\tpublic ";
  protected final String TEXT_451 = " basicSet";
  protected final String TEXT_452 = "(";
  protected final String TEXT_453 = " new";
  protected final String TEXT_454 = ", ";
  protected final String TEXT_455 = " msgs)" + NL + "\t{";
  protected final String TEXT_456 = NL + "\t\tmsgs = eBasicSetContainer((";
  protected final String TEXT_457 = ")new";
  protected final String TEXT_458 = ", ";
  protected final String TEXT_459 = ", msgs);";
  protected final String TEXT_460 = NL + "\t\treturn msgs;";
  protected final String TEXT_461 = NL + "\t\tObject old";
  protected final String TEXT_462 = " = eVirtualSet(";
  protected final String TEXT_463 = ", new";
  protected final String TEXT_464 = ");";
  protected final String TEXT_465 = NL + "\t\t";
  protected final String TEXT_466 = " old";
  protected final String TEXT_467 = " = ";
  protected final String TEXT_468 = ";" + NL + "\t\t";
  protected final String TEXT_469 = " = new";
  protected final String TEXT_470 = ";";
  protected final String TEXT_471 = NL + "\t\tboolean isSetChange = old";
  protected final String TEXT_472 = " == EVIRTUAL_NO_VALUE;";
  protected final String TEXT_473 = NL + "\t\tboolean old";
  protected final String TEXT_474 = "ESet = (";
  protected final String TEXT_475 = " & ";
  protected final String TEXT_476 = "_ESETFLAG) != 0;" + NL + "\t\t";
  protected final String TEXT_477 = " |= ";
  protected final String TEXT_478 = "_ESETFLAG;";
  protected final String TEXT_479 = NL + "\t\tboolean old";
  protected final String TEXT_480 = "ESet = ";
  protected final String TEXT_481 = "ESet;" + NL + "\t\t";
  protected final String TEXT_482 = "ESet = true;";
  protected final String TEXT_483 = NL + "\t\tif (eNotificationRequired())" + NL + "\t\t{";
  protected final String TEXT_484 = NL + "\t\t\t";
  protected final String TEXT_485 = " notification = new ";
  protected final String TEXT_486 = "(this, ";
  protected final String TEXT_487 = ".SET, ";
  protected final String TEXT_488 = ", ";
  protected final String TEXT_489 = "isSetChange ? null : old";
  protected final String TEXT_490 = "old";
  protected final String TEXT_491 = ", new";
  protected final String TEXT_492 = ", ";
  protected final String TEXT_493 = "isSetChange";
  protected final String TEXT_494 = "!old";
  protected final String TEXT_495 = "ESet";
  protected final String TEXT_496 = ");";
  protected final String TEXT_497 = NL + "\t\t\t";
  protected final String TEXT_498 = " notification = new ";
  protected final String TEXT_499 = "(this, ";
  protected final String TEXT_500 = ".SET, ";
  protected final String TEXT_501 = ", ";
  protected final String TEXT_502 = "old";
  protected final String TEXT_503 = " == EVIRTUAL_NO_VALUE ? null : old";
  protected final String TEXT_504 = "old";
  protected final String TEXT_505 = ", new";
  protected final String TEXT_506 = ");";
  protected final String TEXT_507 = NL + "\t\t\tif (msgs == null) msgs = notification; else msgs.add(notification);" + NL + "\t\t}";
  protected final String TEXT_508 = NL + "\t\treturn msgs;";
  protected final String TEXT_509 = NL + "\t\treturn ((";
  protected final String TEXT_510 = ".Internal)((";
  protected final String TEXT_511 = ".Internal.Wrapper)get";
  protected final String TEXT_512 = "()).featureMap()).basicAdd(";
  protected final String TEXT_513 = ", new";
  protected final String TEXT_514 = ", msgs);";
  protected final String TEXT_515 = NL + "\t\treturn ((";
  protected final String TEXT_516 = ".Internal)get";
  protected final String TEXT_517 = "()).basicAdd(";
  protected final String TEXT_518 = ", new";
  protected final String TEXT_519 = ", msgs);";
  protected final String TEXT_520 = NL + "\t\t// TODO: implement this method to set the contained '";
  protected final String TEXT_521 = "' ";
  protected final String TEXT_522 = NL + "\t\t// -> this method is automatically invoked to keep the containment relationship in synch" + NL + "\t\t// -> do not modify other features" + NL + "\t\t// -> return msgs, after adding any generated Notification to it (if it is null, a NotificationChain object must be created first)" + NL + "\t\t// Ensure that you remove @generated or mark it @generated NOT" + NL + "\t\tthrow new UnsupportedOperationException();";
  protected final String TEXT_523 = NL + "\t}" + NL;
  protected final String TEXT_524 = NL + "\t/**" + NL + "\t * Sets the value of the '{@link ";
  protected final String TEXT_525 = "#";
  protected final String TEXT_526 = " <em>";
  protected final String TEXT_527 = "</em>}' ";
  protected final String TEXT_528 = ".";
  protected final String TEXT_529 = NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @param value the new value of the '<em>";
  protected final String TEXT_530 = "</em>' ";
  protected final String TEXT_531 = ".";
  protected final String TEXT_532 = NL + "\t * @see ";
  protected final String TEXT_533 = NL + "\t * @see #isSet";
  protected final String TEXT_534 = "()";
  protected final String TEXT_535 = NL + "\t * @see #unset";
  protected final String TEXT_536 = "()";
  protected final String TEXT_537 = NL + "\t * @see #";
  protected final String TEXT_538 = "()" + NL + "\t * @generated" + NL + "\t */";
  protected final String TEXT_539 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */";
  protected final String TEXT_540 = NL + "\tvoid set";
  protected final String TEXT_541 = "(";
  protected final String TEXT_542 = " value);" + NL;
  protected final String TEXT_543 = NL + "\tpublic void set";
  protected final String TEXT_544 = "(";
  protected final String TEXT_545 = " new";
  protected final String TEXT_546 = ")" + NL + "\t{";
  protected final String TEXT_547 = NL + "\t\teSet(";
  protected final String TEXT_548 = ", ";
  protected final String TEXT_549 = "new ";
  protected final String TEXT_550 = "(";
  protected final String TEXT_551 = "new";
  protected final String TEXT_552 = ")";
  protected final String TEXT_553 = ");";
  protected final String TEXT_554 = NL + "\t\tif (new";
  protected final String TEXT_555 = " != eInternalContainer() || (eContainerFeatureID != ";
  protected final String TEXT_556 = " && new";
  protected final String TEXT_557 = " != null))" + NL + "\t\t{" + NL + "\t\t\tif (";
  protected final String TEXT_558 = ".isAncestor(this, ";
  protected final String TEXT_559 = "new";
  protected final String TEXT_560 = "))" + NL + "\t\t\t\tthrow new ";
  protected final String TEXT_561 = "(\"Recursive containment not allowed for \" + toString());";
  protected final String TEXT_562 = NL + "\t\t\t";
  protected final String TEXT_563 = " msgs = null;" + NL + "\t\t\tif (eInternalContainer() != null)" + NL + "\t\t\t\tmsgs = eBasicRemoveFromContainer(msgs);" + NL + "\t\t\tif (new";
  protected final String TEXT_564 = " != null)" + NL + "\t\t\t\tmsgs = ((";
  protected final String TEXT_565 = ")new";
  protected final String TEXT_566 = ").eInverseAdd(this, ";
  protected final String TEXT_567 = ", ";
  protected final String TEXT_568 = ".class, msgs);" + NL + "\t\t\tmsgs = basicSet";
  protected final String TEXT_569 = "(";
  protected final String TEXT_570 = "new";
  protected final String TEXT_571 = ", msgs);" + NL + "\t\t\tif (msgs != null) msgs.dispatch();" + NL + "\t\t}";
  protected final String TEXT_572 = NL + "\t\telse if (eNotificationRequired())" + NL + "\t\t\teNotify(new ";
  protected final String TEXT_573 = "(this, ";
  protected final String TEXT_574 = ".SET, ";
  protected final String TEXT_575 = ", new";
  protected final String TEXT_576 = ", new";
  protected final String TEXT_577 = "));";
  protected final String TEXT_578 = NL + "\t\t";
  protected final String TEXT_579 = " ";
  protected final String TEXT_580 = " = (";
  protected final String TEXT_581 = ")eVirtualGet(";
  protected final String TEXT_582 = ");";
  protected final String TEXT_583 = NL + "\t\tif (new";
  protected final String TEXT_584 = " != ";
  protected final String TEXT_585 = ")" + NL + "\t\t{" + NL + "\t\t\t";
  protected final String TEXT_586 = " msgs = null;" + NL + "\t\t\tif (";
  protected final String TEXT_587 = " != null)";
  protected final String TEXT_588 = NL + "\t\t\t\tmsgs = ((";
  protected final String TEXT_589 = ")";
  protected final String TEXT_590 = ").eInverseRemove(this, EOPPOSITE_FEATURE_BASE - ";
  protected final String TEXT_591 = ", null, msgs);" + NL + "\t\t\tif (new";
  protected final String TEXT_592 = " != null)" + NL + "\t\t\t\tmsgs = ((";
  protected final String TEXT_593 = ")new";
  protected final String TEXT_594 = ").eInverseAdd(this, EOPPOSITE_FEATURE_BASE - ";
  protected final String TEXT_595 = ", null, msgs);";
  protected final String TEXT_596 = NL + "\t\t\t\tmsgs = ((";
  protected final String TEXT_597 = ")";
  protected final String TEXT_598 = ").eInverseRemove(this, ";
  protected final String TEXT_599 = ", ";
  protected final String TEXT_600 = ".class, msgs);" + NL + "\t\t\tif (new";
  protected final String TEXT_601 = " != null)" + NL + "\t\t\t\tmsgs = ((";
  protected final String TEXT_602 = ")new";
  protected final String TEXT_603 = ").eInverseAdd(this, ";
  protected final String TEXT_604 = ", ";
  protected final String TEXT_605 = ".class, msgs);";
  protected final String TEXT_606 = NL + "\t\t\tmsgs = basicSet";
  protected final String TEXT_607 = "(";
  protected final String TEXT_608 = "new";
  protected final String TEXT_609 = ", msgs);" + NL + "\t\t\tif (msgs != null) msgs.dispatch();" + NL + "\t\t}";
  protected final String TEXT_610 = NL + "\t\telse" + NL + "\t\t{";
  protected final String TEXT_611 = NL + "\t\t\tboolean old";
  protected final String TEXT_612 = "ESet = eVirtualIsSet(";
  protected final String TEXT_613 = ");";
  protected final String TEXT_614 = NL + "\t\t\tboolean old";
  protected final String TEXT_615 = "ESet = (";
  protected final String TEXT_616 = " & ";
  protected final String TEXT_617 = "_ESETFLAG) != 0;";
  protected final String TEXT_618 = NL + "\t\t\t";
  protected final String TEXT_619 = " |= ";
  protected final String TEXT_620 = "_ESETFLAG;";
  protected final String TEXT_621 = NL + "\t\t\tboolean old";
  protected final String TEXT_622 = "ESet = ";
  protected final String TEXT_623 = "ESet;";
  protected final String TEXT_624 = NL + "\t\t\t";
  protected final String TEXT_625 = "ESet = true;";
  protected final String TEXT_626 = NL + "\t\t\tif (eNotificationRequired())" + NL + "\t\t\t\teNotify(new ";
  protected final String TEXT_627 = "(this, ";
  protected final String TEXT_628 = ".SET, ";
  protected final String TEXT_629 = ", new";
  protected final String TEXT_630 = ", new";
  protected final String TEXT_631 = ", !old";
  protected final String TEXT_632 = "ESet));";
  protected final String TEXT_633 = NL + "\t\t}";
  protected final String TEXT_634 = NL + "\t\telse if (eNotificationRequired())" + NL + "\t\t\teNotify(new ";
  protected final String TEXT_635 = "(this, ";
  protected final String TEXT_636 = ".SET, ";
  protected final String TEXT_637 = ", new";
  protected final String TEXT_638 = ", new";
  protected final String TEXT_639 = "));";
  protected final String TEXT_640 = NL + "\t\t";
  protected final String TEXT_641 = " old";
  protected final String TEXT_642 = " = (";
  protected final String TEXT_643 = " & ";
  protected final String TEXT_644 = "_EFLAG) != 0;";
  protected final String TEXT_645 = NL + "\t\tif (new";
  protected final String TEXT_646 = ") ";
  protected final String TEXT_647 = " |= ";
  protected final String TEXT_648 = "_EFLAG; else ";
  protected final String TEXT_649 = " &= ~";
  protected final String TEXT_650 = "_EFLAG;";
  protected final String TEXT_651 = NL + "\t\t";
  protected final String TEXT_652 = " old";
  protected final String TEXT_653 = " = ";
  protected final String TEXT_654 = ";";
  protected final String TEXT_655 = NL + "\t\t";
  protected final String TEXT_656 = " ";
  protected final String TEXT_657 = " = new";
  protected final String TEXT_658 = " == null ? ";
  protected final String TEXT_659 = " : new";
  protected final String TEXT_660 = ";";
  protected final String TEXT_661 = NL + "\t\t";
  protected final String TEXT_662 = " = new";
  protected final String TEXT_663 = " == null ? ";
  protected final String TEXT_664 = " : new";
  protected final String TEXT_665 = ";";
  protected final String TEXT_666 = NL + "\t\t";
  protected final String TEXT_667 = " ";
  protected final String TEXT_668 = " = ";
  protected final String TEXT_669 = "new";
  protected final String TEXT_670 = ";";
  protected final String TEXT_671 = NL + "\t\t";
  protected final String TEXT_672 = " = ";
  protected final String TEXT_673 = "new";
  protected final String TEXT_674 = ";";
  protected final String TEXT_675 = NL + "\t\tObject old";
  protected final String TEXT_676 = " = eVirtualSet(";
  protected final String TEXT_677 = ", ";
  protected final String TEXT_678 = ");";
  protected final String TEXT_679 = NL + "\t\tboolean isSetChange = old";
  protected final String TEXT_680 = " == EVIRTUAL_NO_VALUE;";
  protected final String TEXT_681 = NL + "\t\tboolean old";
  protected final String TEXT_682 = "ESet = (";
  protected final String TEXT_683 = " & ";
  protected final String TEXT_684 = "_ESETFLAG) != 0;";
  protected final String TEXT_685 = NL + "\t\t";
  protected final String TEXT_686 = " |= ";
  protected final String TEXT_687 = "_ESETFLAG;";
  protected final String TEXT_688 = NL + "\t\tboolean old";
  protected final String TEXT_689 = "ESet = ";
  protected final String TEXT_690 = "ESet;";
  protected final String TEXT_691 = NL + "\t\t";
  protected final String TEXT_692 = "ESet = true;";
  protected final String TEXT_693 = NL + "\t\tif (eNotificationRequired())" + NL + "\t\t\teNotify(new ";
  protected final String TEXT_694 = "(this, ";
  protected final String TEXT_695 = ".SET, ";
  protected final String TEXT_696 = ", ";
  protected final String TEXT_697 = "isSetChange ? ";
  protected final String TEXT_698 = " : old";
  protected final String TEXT_699 = "old";
  protected final String TEXT_700 = ", ";
  protected final String TEXT_701 = "new";
  protected final String TEXT_702 = ", ";
  protected final String TEXT_703 = "isSetChange";
  protected final String TEXT_704 = "!old";
  protected final String TEXT_705 = "ESet";
  protected final String TEXT_706 = "));";
  protected final String TEXT_707 = NL + "\t\tif (eNotificationRequired())" + NL + "\t\t\teNotify(new ";
  protected final String TEXT_708 = "(this, ";
  protected final String TEXT_709 = ".SET, ";
  protected final String TEXT_710 = ", ";
  protected final String TEXT_711 = "old";
  protected final String TEXT_712 = " == EVIRTUAL_NO_VALUE ? ";
  protected final String TEXT_713 = " : old";
  protected final String TEXT_714 = "old";
  protected final String TEXT_715 = ", ";
  protected final String TEXT_716 = "new";
  protected final String TEXT_717 = "));";
  protected final String TEXT_718 = NL + "\t\t((";
  protected final String TEXT_719 = ".Internal)((";
  protected final String TEXT_720 = ".Internal.Wrapper)get";
  protected final String TEXT_721 = "()).featureMap()).set(";
  protected final String TEXT_722 = ", ";
  protected final String TEXT_723 = "new ";
  protected final String TEXT_724 = "(";
  protected final String TEXT_725 = "new";
  protected final String TEXT_726 = ")";
  protected final String TEXT_727 = ");";
  protected final String TEXT_728 = NL + "\t\t((";
  protected final String TEXT_729 = ".Internal)get";
  protected final String TEXT_730 = "()).set(";
  protected final String TEXT_731 = ", ";
  protected final String TEXT_732 = "new ";
  protected final String TEXT_733 = "(";
  protected final String TEXT_734 = "new";
  protected final String TEXT_735 = ")";
  protected final String TEXT_736 = ");";
  protected final String TEXT_737 = NL + "\t\t// TODO: implement this method to set the '";
  protected final String TEXT_738 = "' ";
  protected final String TEXT_739 = NL + "\t\t// Ensure that you remove @generated or mark it @generated NOT" + NL + "\t\tthrow new UnsupportedOperationException();";
  protected final String TEXT_740 = NL + "\t}" + NL;
  protected final String TEXT_741 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */";
  protected final String TEXT_742 = NL + "\tpublic ";
  protected final String TEXT_743 = " basicUnset";
  protected final String TEXT_744 = "(";
  protected final String TEXT_745 = " msgs)" + NL + "\t{";
  protected final String TEXT_746 = NL + "\t\tObject old";
  protected final String TEXT_747 = " = eVirtualUnset(";
  protected final String TEXT_748 = ");";
  protected final String TEXT_749 = NL + "\t\t";
  protected final String TEXT_750 = " old";
  protected final String TEXT_751 = " = ";
  protected final String TEXT_752 = ";" + NL + "\t\t";
  protected final String TEXT_753 = " = null;";
  protected final String TEXT_754 = NL + "\t\tboolean isSetChange = old";
  protected final String TEXT_755 = " != EVIRTUAL_NO_VALUE;";
  protected final String TEXT_756 = NL + "\t\tboolean old";
  protected final String TEXT_757 = "ESet = (";
  protected final String TEXT_758 = " & ";
  protected final String TEXT_759 = "_ESETFLAG) != 0;" + NL + "\t\t";
  protected final String TEXT_760 = " &= ~";
  protected final String TEXT_761 = "_ESETFLAG;";
  protected final String TEXT_762 = NL + "\t\tboolean old";
  protected final String TEXT_763 = "ESet = ";
  protected final String TEXT_764 = "ESet;" + NL + "\t\t";
  protected final String TEXT_765 = "ESet = false;";
  protected final String TEXT_766 = NL + "\t\tif (eNotificationRequired())" + NL + "\t\t{" + NL + "\t\t\t";
  protected final String TEXT_767 = " notification = new ";
  protected final String TEXT_768 = "(this, ";
  protected final String TEXT_769 = ".UNSET, ";
  protected final String TEXT_770 = ", ";
  protected final String TEXT_771 = "isSetChange ? old";
  protected final String TEXT_772 = " : null";
  protected final String TEXT_773 = "old";
  protected final String TEXT_774 = ", null, ";
  protected final String TEXT_775 = "isSetChange";
  protected final String TEXT_776 = "old";
  protected final String TEXT_777 = "ESet";
  protected final String TEXT_778 = ");" + NL + "\t\t\tif (msgs == null) msgs = notification; else msgs.add(notification);" + NL + "\t\t}" + NL + "\t\treturn msgs;";
  protected final String TEXT_779 = NL + "\t\t// TODO: implement this method to unset the contained '";
  protected final String TEXT_780 = "' ";
  protected final String TEXT_781 = NL + "\t\t// -> this method is automatically invoked to keep the containment relationship in synch" + NL + "\t\t// -> do not modify other features" + NL + "\t\t// -> return msgs, after adding any generated Notification to it (if it is null, a NotificationChain object must be created first)" + NL + "\t\t// Ensure that you remove @generated or mark it @generated NOT" + NL + "\t\tthrow new UnsupportedOperationException();";
  protected final String TEXT_782 = NL + "\t}" + NL;
  protected final String TEXT_783 = NL + "\t/**" + NL + "\t * Unsets the value of the '{@link ";
  protected final String TEXT_784 = "#";
  protected final String TEXT_785 = " <em>";
  protected final String TEXT_786 = "</em>}' ";
  protected final String TEXT_787 = ".";
  protected final String TEXT_788 = NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->";
  protected final String TEXT_789 = NL + "\t * @see #isSet";
  protected final String TEXT_790 = "()";
  protected final String TEXT_791 = NL + "\t * @see #";
  protected final String TEXT_792 = "()";
  protected final String TEXT_793 = NL + "\t * @see #set";
  protected final String TEXT_794 = "(";
  protected final String TEXT_795 = ")";
  protected final String TEXT_796 = NL + "\t * @generated" + NL + "\t */";
  protected final String TEXT_797 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */";
  protected final String TEXT_798 = NL + "\tvoid unset";
  protected final String TEXT_799 = "();" + NL;
  protected final String TEXT_800 = NL + "\tpublic void unset";
  protected final String TEXT_801 = "()" + NL + "\t{";
  protected final String TEXT_802 = NL + "\t\teUnset(";
  protected final String TEXT_803 = ");";
  protected final String TEXT_804 = NL + "\t\t";
  protected final String TEXT_805 = " ";
  protected final String TEXT_806 = " = (";
  protected final String TEXT_807 = ")eVirtualGet(";
  protected final String TEXT_808 = ");";
  protected final String TEXT_809 = NL + "\t\tif (";
  protected final String TEXT_810 = " != null) ((";
  protected final String TEXT_811 = ".Unsettable";
  protected final String TEXT_812 = ")";
  protected final String TEXT_813 = ").unset();";
  protected final String TEXT_814 = NL + "\t\t";
  protected final String TEXT_815 = " ";
  protected final String TEXT_816 = " = (";
  protected final String TEXT_817 = ")eVirtualGet(";
  protected final String TEXT_818 = ");";
  protected final String TEXT_819 = NL + "\t\tif (";
  protected final String TEXT_820 = " != null)" + NL + "\t\t{" + NL + "\t\t\t";
  protected final String TEXT_821 = " msgs = null;";
  protected final String TEXT_822 = NL + "\t\t\tmsgs = ((";
  protected final String TEXT_823 = ")";
  protected final String TEXT_824 = ").eInverseRemove(this, EOPPOSITE_FEATURE_BASE - ";
  protected final String TEXT_825 = ", null, msgs);";
  protected final String TEXT_826 = NL + "\t\t\tmsgs = ((";
  protected final String TEXT_827 = ")";
  protected final String TEXT_828 = ").eInverseRemove(this, ";
  protected final String TEXT_829 = ", ";
  protected final String TEXT_830 = ".class, msgs);";
  protected final String TEXT_831 = NL + "\t\t\tmsgs = basicUnset";
  protected final String TEXT_832 = "(msgs);" + NL + "\t\t\tif (msgs != null) msgs.dispatch();" + NL + "\t\t}" + NL + "\t\telse" + NL + "\t\t{";
  protected final String TEXT_833 = NL + "\t\t\tboolean old";
  protected final String TEXT_834 = "ESet = eVirtualIsSet(";
  protected final String TEXT_835 = ");";
  protected final String TEXT_836 = NL + "\t\t\tboolean old";
  protected final String TEXT_837 = "ESet = (";
  protected final String TEXT_838 = " & ";
  protected final String TEXT_839 = "_ESETFLAG) != 0;";
  protected final String TEXT_840 = NL + "\t\t\t";
  protected final String TEXT_841 = " &= ~";
  protected final String TEXT_842 = "_ESETFLAG;";
  protected final String TEXT_843 = NL + "\t\t\tboolean old";
  protected final String TEXT_844 = "ESet = ";
  protected final String TEXT_845 = "ESet;";
  protected final String TEXT_846 = NL + "\t\t\t";
  protected final String TEXT_847 = "ESet = false;";
  protected final String TEXT_848 = NL + "\t\t\tif (eNotificationRequired())" + NL + "\t\t\t\teNotify(new ";
  protected final String TEXT_849 = "(this, ";
  protected final String TEXT_850 = ".UNSET, ";
  protected final String TEXT_851 = ", null, null, old";
  protected final String TEXT_852 = "ESet));";
  protected final String TEXT_853 = NL + "\t\t}";
  protected final String TEXT_854 = NL + "\t\t";
  protected final String TEXT_855 = " old";
  protected final String TEXT_856 = " = (";
  protected final String TEXT_857 = " & ";
  protected final String TEXT_858 = "_EFLAG) != 0;";
  protected final String TEXT_859 = NL + "\t\tObject old";
  protected final String TEXT_860 = " = eVirtualUnset(";
  protected final String TEXT_861 = ");";
  protected final String TEXT_862 = NL + "\t\t";
  protected final String TEXT_863 = " old";
  protected final String TEXT_864 = " = ";
  protected final String TEXT_865 = ";";
  protected final String TEXT_866 = NL + "\t\tboolean isSetChange = old";
  protected final String TEXT_867 = " != EVIRTUAL_NO_VALUE;";
  protected final String TEXT_868 = NL + "\t\tboolean old";
  protected final String TEXT_869 = "ESet = (";
  protected final String TEXT_870 = " & ";
  protected final String TEXT_871 = "_ESETFLAG) != 0;";
  protected final String TEXT_872 = NL + "\t\tboolean old";
  protected final String TEXT_873 = "ESet = ";
  protected final String TEXT_874 = "ESet;";
  protected final String TEXT_875 = NL + "\t\t";
  protected final String TEXT_876 = " = null;";
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
  protected final String TEXT_887 = " : null";
  protected final String TEXT_888 = "old";
  protected final String TEXT_889 = ", null, ";
  protected final String TEXT_890 = "isSetChange";
  protected final String TEXT_891 = "old";
  protected final String TEXT_892 = "ESet";
  protected final String TEXT_893 = "));";
  protected final String TEXT_894 = NL + "\t\tif (";
  protected final String TEXT_895 = ") ";
  protected final String TEXT_896 = " |= ";
  protected final String TEXT_897 = "_EFLAG; else ";
  protected final String TEXT_898 = " &= ~";
  protected final String TEXT_899 = "_EFLAG;";
  protected final String TEXT_900 = NL + "\t\t";
  protected final String TEXT_901 = " = ";
  protected final String TEXT_902 = ";";
  protected final String TEXT_903 = NL + "\t\t";
  protected final String TEXT_904 = " &= ~";
  protected final String TEXT_905 = "_ESETFLAG;";
  protected final String TEXT_906 = NL + "\t\t";
  protected final String TEXT_907 = "ESet = false;";
  protected final String TEXT_908 = NL + "\t\tif (eNotificationRequired())" + NL + "\t\t\teNotify(new ";
  protected final String TEXT_909 = "(this, ";
  protected final String TEXT_910 = ".UNSET, ";
  protected final String TEXT_911 = ", ";
  protected final String TEXT_912 = "isSetChange ? old";
  protected final String TEXT_913 = " : ";
  protected final String TEXT_914 = "old";
  protected final String TEXT_915 = ", ";
  protected final String TEXT_916 = ", ";
  protected final String TEXT_917 = "isSetChange";
  protected final String TEXT_918 = "old";
  protected final String TEXT_919 = "ESet";
  protected final String TEXT_920 = "));";
  protected final String TEXT_921 = NL + "\t\t((";
  protected final String TEXT_922 = ".Internal)((";
  protected final String TEXT_923 = ".Internal.Wrapper)get";
  protected final String TEXT_924 = "()).featureMap()).clear(";
  protected final String TEXT_925 = ");";
  protected final String TEXT_926 = NL + "\t\t((";
  protected final String TEXT_927 = ".Internal)get";
  protected final String TEXT_928 = "()).clear(";
  protected final String TEXT_929 = ");";
  protected final String TEXT_930 = NL + "\t\t// TODO: implement this method to unset the '";
  protected final String TEXT_931 = "' ";
  protected final String TEXT_932 = NL + "\t\t// Ensure that you remove @generated or mark it @generated NOT" + NL + "\t\tthrow new UnsupportedOperationException();";
  protected final String TEXT_933 = NL + "\t}" + NL;
  protected final String TEXT_934 = NL + "\t/**" + NL + "\t * Returns whether the value of the '{@link ";
  protected final String TEXT_935 = "#";
  protected final String TEXT_936 = " <em>";
  protected final String TEXT_937 = "</em>}' ";
  protected final String TEXT_938 = " is set.";
  protected final String TEXT_939 = NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @return whether the value of the '<em>";
  protected final String TEXT_940 = "</em>' ";
  protected final String TEXT_941 = " is set.";
  protected final String TEXT_942 = NL + "\t * @see #unset";
  protected final String TEXT_943 = "()";
  protected final String TEXT_944 = NL + "\t * @see #";
  protected final String TEXT_945 = "()";
  protected final String TEXT_946 = NL + "\t * @see #set";
  protected final String TEXT_947 = "(";
  protected final String TEXT_948 = ")";
  protected final String TEXT_949 = NL + "\t * @generated" + NL + "\t */";
  protected final String TEXT_950 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */";
  protected final String TEXT_951 = NL + "\tboolean isSet";
  protected final String TEXT_952 = "();" + NL;
  protected final String TEXT_953 = NL + "\tpublic boolean isSet";
  protected final String TEXT_954 = "()" + NL + "\t{";
  protected final String TEXT_955 = NL + "\t\treturn eIsSet(";
  protected final String TEXT_956 = ");";
  protected final String TEXT_957 = NL + "\t\t";
  protected final String TEXT_958 = " ";
  protected final String TEXT_959 = " = (";
  protected final String TEXT_960 = ")eVirtualGet(";
  protected final String TEXT_961 = ");";
  protected final String TEXT_962 = NL + "\t\treturn ";
  protected final String TEXT_963 = " != null && ((";
  protected final String TEXT_964 = ".Unsettable";
  protected final String TEXT_965 = ")";
  protected final String TEXT_966 = ").isSet();";
  protected final String TEXT_967 = NL + "\t\treturn eVirtualIsSet(";
  protected final String TEXT_968 = ");";
  protected final String TEXT_969 = NL + "\t\treturn (";
  protected final String TEXT_970 = " & ";
  protected final String TEXT_971 = "_ESETFLAG) != 0;";
  protected final String TEXT_972 = NL + "\t\treturn ";
  protected final String TEXT_973 = "ESet;";
  protected final String TEXT_974 = NL + "\t\treturn !((";
  protected final String TEXT_975 = ".Internal)((";
  protected final String TEXT_976 = ".Internal.Wrapper)get";
  protected final String TEXT_977 = "()).featureMap()).isEmpty(";
  protected final String TEXT_978 = ");";
  protected final String TEXT_979 = NL + "\t\treturn !((";
  protected final String TEXT_980 = ".Internal)get";
  protected final String TEXT_981 = "()).isEmpty(";
  protected final String TEXT_982 = ");";
  protected final String TEXT_983 = NL + "\t\t// TODO: implement this method to return whether the '";
  protected final String TEXT_984 = "' ";
  protected final String TEXT_985 = " is set" + NL + "\t\t// Ensure that you remove @generated or mark it @generated NOT" + NL + "\t\tthrow new UnsupportedOperationException();";
  protected final String TEXT_986 = NL + "\t}" + NL;
  protected final String TEXT_987 = NL + "\t/**";
  protected final String TEXT_988 = NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->";
  protected final String TEXT_989 = NL + "\t * <!-- begin-model-doc -->";
  protected final String TEXT_990 = NL + "\t * ";
  protected final String TEXT_991 = NL + "\t * @param ";
  protected final String TEXT_992 = NL + "\t *   ";
  protected final String TEXT_993 = NL + "\t * @param ";
  protected final String TEXT_994 = " ";
  protected final String TEXT_995 = NL + "\t * <!-- end-model-doc -->";
  protected final String TEXT_996 = NL + "\t * @model ";
  protected final String TEXT_997 = NL + "\t *        ";
  protected final String TEXT_998 = NL + "\t * @model";
  protected final String TEXT_999 = NL + "\t * @generated" + NL + "\t */";
  protected final String TEXT_1000 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */";
  protected final String TEXT_1001 = NL + "\t";
  protected final String TEXT_1002 = " ";
  protected final String TEXT_1003 = "(";
  protected final String TEXT_1004 = ")";
  protected final String TEXT_1005 = ";" + NL;
  protected final String TEXT_1006 = NL + "\tpublic ";
  protected final String TEXT_1007 = " ";
  protected final String TEXT_1008 = "(";
  protected final String TEXT_1009 = ")";
  protected final String TEXT_1010 = NL + "\t{";
  protected final String TEXT_1011 = NL + "\t\t";
  protected final String TEXT_1012 = NL + "\t\t// TODO: implement this method" + NL + "\t\t// -> specify the condition that violates the invariant" + NL + "\t\t// -> verify the details of the diagnostic, including severity and message" + NL + "\t\t// Ensure that you remove @generated or mark it @generated NOT" + NL + "\t\tif (false)" + NL + "\t\t{" + NL + "\t\t\tif (";
  protected final String TEXT_1013 = " != null)" + NL + "\t\t\t{" + NL + "\t\t\t\t";
  protected final String TEXT_1014 = ".add" + NL + "\t\t\t\t\t(new ";
  protected final String TEXT_1015 = NL + "\t\t\t\t\t\t(";
  protected final String TEXT_1016 = ".ERROR," + NL + "\t\t\t\t\t\t ";
  protected final String TEXT_1017 = ".DIAGNOSTIC_SOURCE," + NL + "\t\t\t\t\t\t ";
  protected final String TEXT_1018 = ".";
  protected final String TEXT_1019 = "," + NL + "\t\t\t\t\t\t ";
  protected final String TEXT_1020 = ".INSTANCE.getString(\"_UI_GenericInvariant_diagnostic\", new Object[] { \"";
  protected final String TEXT_1021 = "\", ";
  protected final String TEXT_1022 = ".getObjectLabel(this, ";
  protected final String TEXT_1023 = ") }),";
  protected final String TEXT_1024 = NL + "\t\t\t\t\t\t new Object [] { this }));" + NL + "\t\t\t}" + NL + "\t\t\treturn false;" + NL + "\t\t}" + NL + "\t\treturn true;";
  protected final String TEXT_1025 = NL + "\t\t// TODO: implement this method" + NL + "\t\t// Ensure that you remove @generated or mark it @generated NOT" + NL + "\t\tthrow new UnsupportedOperationException();";
  protected final String TEXT_1026 = NL + "\t}" + NL;
  protected final String TEXT_1027 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */";
  protected final String TEXT_1028 = NL + "\t@SuppressWarnings(\"unchecked\")";
  protected final String TEXT_1029 = NL + "\t@Override";
  protected final String TEXT_1030 = NL + "\tpublic ";
  protected final String TEXT_1031 = " eInverseAdd(";
  protected final String TEXT_1032 = " otherEnd, int featureID, ";
  protected final String TEXT_1033 = " msgs)" + NL + "\t{" + NL + "\t\tswitch (featureID";
  protected final String TEXT_1034 = ")" + NL + "\t\t{";
  protected final String TEXT_1035 = NL + "\t\t\tcase ";
  protected final String TEXT_1036 = ":";
  protected final String TEXT_1037 = NL + "\t\t\t\treturn ((";
  protected final String TEXT_1038 = "(";
  protected final String TEXT_1039 = ".InternalMapView";
  protected final String TEXT_1040 = ")";
  protected final String TEXT_1041 = "()).eMap()).basicAdd(otherEnd, msgs);";
  protected final String TEXT_1042 = NL + "\t\t\t\treturn (";
  protected final String TEXT_1043 = "()).basicAdd(otherEnd, msgs);";
  protected final String TEXT_1044 = NL + "\t\t\t\tif (eInternalContainer() != null)" + NL + "\t\t\t\t\tmsgs = eBasicRemoveFromContainer(msgs);";
  protected final String TEXT_1045 = NL + "\t\t\t\treturn basicSet";
  protected final String TEXT_1046 = "((";
  protected final String TEXT_1047 = ")otherEnd, msgs);";
  protected final String TEXT_1048 = NL + "\t\t\t\treturn eBasicSetContainer(otherEnd, ";
  protected final String TEXT_1049 = ", msgs);";
  protected final String TEXT_1050 = NL + "\t\t\t\t";
  protected final String TEXT_1051 = " ";
  protected final String TEXT_1052 = " = (";
  protected final String TEXT_1053 = ")eVirtualGet(";
  protected final String TEXT_1054 = ");";
  protected final String TEXT_1055 = NL + "\t\t\t\tif (";
  protected final String TEXT_1056 = " != null)";
  protected final String TEXT_1057 = NL + "\t\t\t\t\tmsgs = ((";
  protected final String TEXT_1058 = ")";
  protected final String TEXT_1059 = ").eInverseRemove(this, EOPPOSITE_FEATURE_BASE - ";
  protected final String TEXT_1060 = ", null, msgs);";
  protected final String TEXT_1061 = NL + "\t\t\t\t\tmsgs = ((";
  protected final String TEXT_1062 = ")";
  protected final String TEXT_1063 = ").eInverseRemove(this, ";
  protected final String TEXT_1064 = ", ";
  protected final String TEXT_1065 = ".class, msgs);";
  protected final String TEXT_1066 = NL + "\t\t\t\treturn basicSet";
  protected final String TEXT_1067 = "((";
  protected final String TEXT_1068 = ")otherEnd, msgs);";
  protected final String TEXT_1069 = NL + "\t\t}";
  protected final String TEXT_1070 = NL + "\t\treturn super.eInverseAdd(otherEnd, featureID, msgs);";
  protected final String TEXT_1071 = NL + "\t\treturn eDynamicInverseAdd(otherEnd, featureID, msgs);";
  protected final String TEXT_1072 = NL + "\t}" + NL;
  protected final String TEXT_1073 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */";
  protected final String TEXT_1074 = NL + "\t@Override";
  protected final String TEXT_1075 = NL + "\tpublic ";
  protected final String TEXT_1076 = " eInverseRemove(";
  protected final String TEXT_1077 = " otherEnd, int featureID, ";
  protected final String TEXT_1078 = " msgs)" + NL + "\t{" + NL + "\t\tswitch (featureID";
  protected final String TEXT_1079 = ")" + NL + "\t\t{";
  protected final String TEXT_1080 = NL + "\t\t\tcase ";
  protected final String TEXT_1081 = ":";
  protected final String TEXT_1082 = NL + "\t\t\t\treturn ((";
  protected final String TEXT_1083 = ")((";
  protected final String TEXT_1084 = ".InternalMapView";
  protected final String TEXT_1085 = ")";
  protected final String TEXT_1086 = "()).eMap()).basicRemove(otherEnd, msgs);";
  protected final String TEXT_1087 = NL + "\t\t\t\treturn ((";
  protected final String TEXT_1088 = ")((";
  protected final String TEXT_1089 = ".Internal.Wrapper)";
  protected final String TEXT_1090 = "()).featureMap()).basicRemove(otherEnd, msgs);";
  protected final String TEXT_1091 = NL + "\t\t\t\treturn ((";
  protected final String TEXT_1092 = ")";
  protected final String TEXT_1093 = "()).basicRemove(otherEnd, msgs);";
  protected final String TEXT_1094 = NL + "\t\t\t\treturn eBasicSetContainer(null, ";
  protected final String TEXT_1095 = ", msgs);";
  protected final String TEXT_1096 = NL + "\t\t\t\treturn basicUnset";
  protected final String TEXT_1097 = "(msgs);";
  protected final String TEXT_1098 = NL + "\t\t\t\treturn basicSet";
  protected final String TEXT_1099 = "(null, msgs);";
  protected final String TEXT_1100 = NL + "\t\t}";
  protected final String TEXT_1101 = NL + "\t\treturn super.eInverseRemove(otherEnd, featureID, msgs);";
  protected final String TEXT_1102 = NL + "\t\treturn eDynamicInverseRemove(otherEnd, featureID, msgs);";
  protected final String TEXT_1103 = NL + "\t}" + NL;
  protected final String TEXT_1104 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */";
  protected final String TEXT_1105 = NL + "\t@Override";
  protected final String TEXT_1106 = NL + "\tpublic ";
  protected final String TEXT_1107 = " eBasicRemoveFromContainerFeature(";
  protected final String TEXT_1108 = " msgs)" + NL + "\t{" + NL + "\t\tswitch (eContainerFeatureID";
  protected final String TEXT_1109 = ")" + NL + "\t\t{";
  protected final String TEXT_1110 = NL + "\t\t\tcase ";
  protected final String TEXT_1111 = ":" + NL + "\t\t\t\treturn eInternalContainer().eInverseRemove(this, ";
  protected final String TEXT_1112 = ", ";
  protected final String TEXT_1113 = ".class, msgs);";
  protected final String TEXT_1114 = NL + "\t\t}";
  protected final String TEXT_1115 = NL + "\t\treturn super.eBasicRemoveFromContainerFeature(msgs);";
  protected final String TEXT_1116 = NL + "\t\treturn eDynamicBasicRemoveFromContainer(msgs);";
  protected final String TEXT_1117 = NL + "\t}" + NL;
  protected final String TEXT_1118 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */";
  protected final String TEXT_1119 = NL + "\t@Override";
  protected final String TEXT_1120 = NL + "\tpublic Object eGet(int featureID, boolean resolve, boolean coreType)" + NL + "\t{" + NL + "\t\tswitch (featureID";
  protected final String TEXT_1121 = ")" + NL + "\t\t{";
  protected final String TEXT_1122 = NL + "\t\t\tcase ";
  protected final String TEXT_1123 = ":";
  protected final String TEXT_1124 = NL + "\t\t\t\treturn ";
  protected final String TEXT_1125 = "() ? Boolean.TRUE : Boolean.FALSE;";
  protected final String TEXT_1126 = NL + "\t\t\t\treturn new ";
  protected final String TEXT_1127 = "(";
  protected final String TEXT_1128 = "());";
  protected final String TEXT_1129 = NL + "\t\t\t\tif (resolve) return ";
  protected final String TEXT_1130 = "();" + NL + "\t\t\t\treturn basicGet";
  protected final String TEXT_1131 = "();";
  protected final String TEXT_1132 = NL + "\t\t\t\tif (coreType) return ((";
  protected final String TEXT_1133 = ".InternalMapView";
  protected final String TEXT_1134 = ")";
  protected final String TEXT_1135 = "()).eMap();" + NL + "\t\t\t\telse return ";
  protected final String TEXT_1136 = "();";
  protected final String TEXT_1137 = NL + "\t\t\t\tif (coreType) return ";
  protected final String TEXT_1138 = "();" + NL + "\t\t\t\telse return ";
  protected final String TEXT_1139 = "().map();";
  protected final String TEXT_1140 = NL + "\t\t\t\tif (coreType) return ((";
  protected final String TEXT_1141 = ".Internal.Wrapper)";
  protected final String TEXT_1142 = "()).featureMap();" + NL + "\t\t\t\treturn ";
  protected final String TEXT_1143 = "();";
  protected final String TEXT_1144 = NL + "\t\t\t\tif (coreType) return ";
  protected final String TEXT_1145 = "();" + NL + "\t\t\t\treturn ((";
  protected final String TEXT_1146 = ".Internal)";
  protected final String TEXT_1147 = "()).getWrapper();";
  protected final String TEXT_1148 = NL + "\t\t\t\treturn ";
  protected final String TEXT_1149 = "();";
  protected final String TEXT_1150 = NL + "\t\t}";
  protected final String TEXT_1151 = NL + "\t\treturn super.eGet(featureID, resolve, coreType);";
  protected final String TEXT_1152 = NL + "\t\treturn eDynamicGet(featureID, resolve, coreType);";
  protected final String TEXT_1153 = NL + "\t}" + NL;
  protected final String TEXT_1154 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */";
  protected final String TEXT_1155 = NL + "\t@SuppressWarnings(\"unchecked\")";
  protected final String TEXT_1156 = NL + "\t@Override";
  protected final String TEXT_1157 = NL + "\tpublic void eSet(int featureID, Object newValue)" + NL + "\t{" + NL + "\t\tswitch (featureID";
  protected final String TEXT_1158 = ")" + NL + "\t\t{";
  protected final String TEXT_1159 = NL + "\t\t\tcase ";
  protected final String TEXT_1160 = ":";
  protected final String TEXT_1161 = NL + "\t\t\t\t((";
  protected final String TEXT_1162 = ".Internal)((";
  protected final String TEXT_1163 = ".Internal.Wrapper)";
  protected final String TEXT_1164 = "()).featureMap()).set(newValue);";
  protected final String TEXT_1165 = NL + "\t\t\t\t((";
  protected final String TEXT_1166 = ".Internal)";
  protected final String TEXT_1167 = "()).set(newValue);";
  protected final String TEXT_1168 = NL + "\t\t\t\t((";
  protected final String TEXT_1169 = ".Setting)((";
  protected final String TEXT_1170 = ".InternalMapView";
  protected final String TEXT_1171 = ")";
  protected final String TEXT_1172 = "()).eMap()).set(newValue);";
  protected final String TEXT_1173 = NL + "\t\t\t\t((";
  protected final String TEXT_1174 = ".Setting)";
  protected final String TEXT_1175 = "()).set(newValue);";
  protected final String TEXT_1176 = NL + "\t\t\t\t";
  protected final String TEXT_1177 = "().clear();" + NL + "\t\t\t\t";
  protected final String TEXT_1178 = "().addAll((";
  protected final String TEXT_1179 = "<? extends ";
  protected final String TEXT_1180 = ">";
  protected final String TEXT_1181 = ")newValue);";
  protected final String TEXT_1182 = NL + "\t\t\t\tset";
  protected final String TEXT_1183 = "(((";
  protected final String TEXT_1184 = ")newValue).";
  protected final String TEXT_1185 = "());";
  protected final String TEXT_1186 = NL + "\t\t\t\tset";
  protected final String TEXT_1187 = "(";
  protected final String TEXT_1188 = "(";
  protected final String TEXT_1189 = ")";
  protected final String TEXT_1190 = "newValue);";
  protected final String TEXT_1191 = NL + "\t\t\t\treturn;";
  protected final String TEXT_1192 = NL + "\t\t}";
  protected final String TEXT_1193 = NL + "\t\tsuper.eSet(featureID, newValue);";
  protected final String TEXT_1194 = NL + "\t\teDynamicSet(featureID, newValue);";
  protected final String TEXT_1195 = NL + "\t}" + NL;
  protected final String TEXT_1196 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */";
  protected final String TEXT_1197 = NL + "\t@Override";
  protected final String TEXT_1198 = NL + "\tpublic void eUnset(int featureID)" + NL + "\t{" + NL + "\t\tswitch (featureID";
  protected final String TEXT_1199 = ")" + NL + "\t\t{";
  protected final String TEXT_1200 = NL + "\t\t\tcase ";
  protected final String TEXT_1201 = ":";
  protected final String TEXT_1202 = NL + "\t\t\t\t((";
  protected final String TEXT_1203 = ".Internal.Wrapper)";
  protected final String TEXT_1204 = "()).featureMap().clear();";
  protected final String TEXT_1205 = NL + "\t\t\t\t";
  protected final String TEXT_1206 = "().clear();";
  protected final String TEXT_1207 = NL + "\t\t\t\tunset";
  protected final String TEXT_1208 = "();";
  protected final String TEXT_1209 = NL + "\t\t\t\tset";
  protected final String TEXT_1210 = "((";
  protected final String TEXT_1211 = ")null);";
  protected final String TEXT_1212 = NL + "\t\t\t\tset";
  protected final String TEXT_1213 = "(";
  protected final String TEXT_1214 = ");";
  protected final String TEXT_1215 = NL + "\t\t\t\treturn;";
  protected final String TEXT_1216 = NL + "\t\t}";
  protected final String TEXT_1217 = NL + "\t\tsuper.eUnset(featureID);";
  protected final String TEXT_1218 = NL + "\t\teDynamicUnset(featureID);";
  protected final String TEXT_1219 = NL + "\t}" + NL;
  protected final String TEXT_1220 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */";
  protected final String TEXT_1221 = NL + "\t@SuppressWarnings(\"unchecked\")";
  protected final String TEXT_1222 = NL + "\t@Override";
  protected final String TEXT_1223 = NL + "\tpublic boolean eIsSet(int featureID)" + NL + "\t{" + NL + "\t\tswitch (featureID";
  protected final String TEXT_1224 = ")" + NL + "\t\t{";
  protected final String TEXT_1225 = NL + "\t\t\tcase ";
  protected final String TEXT_1226 = ":";
  protected final String TEXT_1227 = NL + "\t\t\t\treturn !((";
  protected final String TEXT_1228 = ".Internal.Wrapper)";
  protected final String TEXT_1229 = "()).featureMap().isEmpty();";
  protected final String TEXT_1230 = NL + "\t\t\t\treturn ";
  protected final String TEXT_1231 = " != null && !";
  protected final String TEXT_1232 = ".featureMap().isEmpty();";
  protected final String TEXT_1233 = NL + "\t\t\t\treturn ";
  protected final String TEXT_1234 = " != null && !";
  protected final String TEXT_1235 = ".isEmpty();";
  protected final String TEXT_1236 = NL + "\t\t\t\t";
  protected final String TEXT_1237 = " ";
  protected final String TEXT_1238 = " = (";
  protected final String TEXT_1239 = ")eVirtualGet(";
  protected final String TEXT_1240 = ");" + NL + "\t\t\t\treturn ";
  protected final String TEXT_1241 = " != null && !";
  protected final String TEXT_1242 = ".isEmpty();";
  protected final String TEXT_1243 = NL + "\t\t\t\treturn !";
  protected final String TEXT_1244 = "().isEmpty();";
  protected final String TEXT_1245 = NL + "\t\t\t\treturn isSet";
  protected final String TEXT_1246 = "();";
  protected final String TEXT_1247 = NL + "\t\t\t\treturn ";
  protected final String TEXT_1248 = " != null;";
  protected final String TEXT_1249 = NL + "\t\t\t\treturn eVirtualGet(";
  protected final String TEXT_1250 = ") != null;";
  protected final String TEXT_1251 = NL + "\t\t\t\treturn basicGet";
  protected final String TEXT_1252 = "() != null;";
  protected final String TEXT_1253 = NL + "\t\t\t\treturn ";
  protected final String TEXT_1254 = " != null;";
  protected final String TEXT_1255 = NL + "\t\t\t\treturn eVirtualGet(";
  protected final String TEXT_1256 = ") != null;";
  protected final String TEXT_1257 = NL + "\t\t\t\treturn ";
  protected final String TEXT_1258 = "() != null;";
  protected final String TEXT_1259 = NL + "\t\t\t\treturn ((";
  protected final String TEXT_1260 = " & ";
  protected final String TEXT_1261 = "_EFLAG) != 0) != ";
  protected final String TEXT_1262 = ";";
  protected final String TEXT_1263 = NL + "\t\t\t\treturn ";
  protected final String TEXT_1264 = " != ";
  protected final String TEXT_1265 = ";";
  protected final String TEXT_1266 = NL + "\t\t\t\treturn eVirtualGet(";
  protected final String TEXT_1267 = ", ";
  protected final String TEXT_1268 = ") != ";
  protected final String TEXT_1269 = ";";
  protected final String TEXT_1270 = NL + "\t\t\t\treturn ";
  protected final String TEXT_1271 = "() != ";
  protected final String TEXT_1272 = ";";
  protected final String TEXT_1273 = NL + "\t\t\t\treturn ";
  protected final String TEXT_1274 = " == null ? ";
  protected final String TEXT_1275 = " != null : !";
  protected final String TEXT_1276 = ".equals(";
  protected final String TEXT_1277 = ");";
  protected final String TEXT_1278 = NL + "\t\t\t\t";
  protected final String TEXT_1279 = " ";
  protected final String TEXT_1280 = " = (";
  protected final String TEXT_1281 = ")eVirtualGet(";
  protected final String TEXT_1282 = ", ";
  protected final String TEXT_1283 = ");" + NL + "\t\t\t\treturn ";
  protected final String TEXT_1284 = " == null ? ";
  protected final String TEXT_1285 = " != null : !";
  protected final String TEXT_1286 = ".equals(";
  protected final String TEXT_1287 = ");";
  protected final String TEXT_1288 = NL + "\t\t\t\treturn ";
  protected final String TEXT_1289 = " == null ? ";
  protected final String TEXT_1290 = "() != null : !";
  protected final String TEXT_1291 = ".equals(";
  protected final String TEXT_1292 = "());";
  protected final String TEXT_1293 = NL + "\t\t}";
  protected final String TEXT_1294 = NL + "\t\treturn super.eIsSet(featureID);";
  protected final String TEXT_1295 = NL + "\t\treturn eDynamicIsSet(featureID);";
  protected final String TEXT_1296 = NL + "\t}" + NL;
  protected final String TEXT_1297 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */";
  protected final String TEXT_1298 = NL + "\t@Override";
  protected final String TEXT_1299 = NL + "\tpublic int eBaseStructuralFeatureID(int derivedFeatureID, Class";
  protected final String TEXT_1300 = " baseClass)" + NL + "\t{";
  protected final String TEXT_1301 = NL + "\t\tif (baseClass == ";
  protected final String TEXT_1302 = ".class)" + NL + "\t\t{" + NL + "\t\t\tswitch (derivedFeatureID";
  protected final String TEXT_1303 = ")" + NL + "\t\t\t{";
  protected final String TEXT_1304 = NL + "\t\t\t\tcase ";
  protected final String TEXT_1305 = ": return ";
  protected final String TEXT_1306 = ";";
  protected final String TEXT_1307 = NL + "\t\t\t\tdefault: return -1;" + NL + "\t\t\t}" + NL + "\t\t}";
  protected final String TEXT_1308 = NL + "\t\treturn super.eBaseStructuralFeatureID(derivedFeatureID, baseClass);" + NL + "\t}";
  protected final String TEXT_1309 = NL + NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */";
  protected final String TEXT_1310 = NL + "\t@Override";
  protected final String TEXT_1311 = NL + "\tpublic int eDerivedStructuralFeatureID(int baseFeatureID, Class";
  protected final String TEXT_1312 = " baseClass)" + NL + "\t{";
  protected final String TEXT_1313 = NL + "\t\tif (baseClass == ";
  protected final String TEXT_1314 = ".class)" + NL + "\t\t{" + NL + "\t\t\tswitch (baseFeatureID)" + NL + "\t\t\t{";
  protected final String TEXT_1315 = NL + "\t\t\t\tcase ";
  protected final String TEXT_1316 = ": return ";
  protected final String TEXT_1317 = ";";
  protected final String TEXT_1318 = NL + "\t\t\t\tdefault: return -1;" + NL + "\t\t\t}" + NL + "\t\t}";
  protected final String TEXT_1319 = NL + "\t\tif (baseClass == ";
  protected final String TEXT_1320 = ".class)" + NL + "\t\t{" + NL + "\t\t\tswitch (baseFeatureID";
  protected final String TEXT_1321 = ")" + NL + "\t\t\t{";
  protected final String TEXT_1322 = NL + "\t\t\t\tcase ";
  protected final String TEXT_1323 = ": return ";
  protected final String TEXT_1324 = ";";
  protected final String TEXT_1325 = NL + "\t\t\t\tdefault: return -1;" + NL + "\t\t\t}" + NL + "\t\t}";
  protected final String TEXT_1326 = NL + "\t\treturn super.eDerivedStructuralFeatureID(baseFeatureID, baseClass);" + NL + "\t}" + NL;
  protected final String TEXT_1327 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */";
  protected final String TEXT_1328 = NL + "\t@Override";
  protected final String TEXT_1329 = NL + "\tprotected Object[] eVirtualValues()" + NL + "\t{" + NL + "\t\treturn ";
  protected final String TEXT_1330 = ";" + NL + "\t}" + NL + "" + NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */";
  protected final String TEXT_1331 = NL + "\t@Override";
  protected final String TEXT_1332 = NL + "\tprotected void eSetVirtualValues(Object[] newValues)" + NL + "\t{" + NL + "\t\t";
  protected final String TEXT_1333 = " = newValues;" + NL + "\t}" + NL;
  protected final String TEXT_1334 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */";
  protected final String TEXT_1335 = NL + "\t@Override";
  protected final String TEXT_1336 = NL + "\tprotected int eVirtualIndexBits(int offset)" + NL + "\t{" + NL + "\t\tswitch (offset)" + NL + "\t\t{";
  protected final String TEXT_1337 = NL + "\t\t\tcase ";
  protected final String TEXT_1338 = " :" + NL + "\t\t\t\treturn ";
  protected final String TEXT_1339 = ";";
  protected final String TEXT_1340 = NL + "\t\t\tdefault :" + NL + "\t\t\t\tthrow new IndexOutOfBoundsException();" + NL + "\t\t}" + NL + "\t}" + NL + "" + NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */";
  protected final String TEXT_1341 = NL + "\t@Override";
  protected final String TEXT_1342 = NL + "\tprotected void eSetVirtualIndexBits(int offset, int newIndexBits)" + NL + "\t{" + NL + "\t\tswitch (offset)" + NL + "\t\t{";
  protected final String TEXT_1343 = NL + "\t\t\tcase ";
  protected final String TEXT_1344 = " :" + NL + "\t\t\t\t";
  protected final String TEXT_1345 = " = newIndexBits;" + NL + "\t\t\t\tbreak;";
  protected final String TEXT_1346 = NL + "\t\t\tdefault :" + NL + "\t\t\t\tthrow new IndexOutOfBoundsException();" + NL + "\t\t}" + NL + "\t}" + NL;
  protected final String TEXT_1347 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */";
  protected final String TEXT_1348 = NL + "\t@Override";
  protected final String TEXT_1349 = NL + "\tpublic String toString()" + NL + "\t{" + NL + "\t\tif (eIsProxy()) return super.toString();" + NL + "" + NL + "\t\tStringBuffer result = new StringBuffer(super.toString());";
  protected final String TEXT_1350 = NL + "\t\tresult.append(\" (";
  protected final String TEXT_1351 = ": \");";
  protected final String TEXT_1352 = NL + "\t\tresult.append(\", ";
  protected final String TEXT_1353 = ": \");";
  protected final String TEXT_1354 = NL + "\t\tif (eVirtualIsSet(";
  protected final String TEXT_1355 = ")) result.append(eVirtualGet(";
  protected final String TEXT_1356 = ")); else result.append(\"<unset>\");";
  protected final String TEXT_1357 = NL + "\t\tif (";
  protected final String TEXT_1358 = "(";
  protected final String TEXT_1359 = " & ";
  protected final String TEXT_1360 = "_ESETFLAG) != 0";
  protected final String TEXT_1361 = "ESet";
  protected final String TEXT_1362 = ") result.append((";
  protected final String TEXT_1363 = " & ";
  protected final String TEXT_1364 = "_EFLAG) != 0); else result.append(\"<unset>\");";
  protected final String TEXT_1365 = NL + "\t\tif (";
  protected final String TEXT_1366 = "(";
  protected final String TEXT_1367 = " & ";
  protected final String TEXT_1368 = "_ESETFLAG) != 0";
  protected final String TEXT_1369 = "ESet";
  protected final String TEXT_1370 = ") result.append(";
  protected final String TEXT_1371 = "); else result.append(\"<unset>\");";
  protected final String TEXT_1372 = NL + "\t\tresult.append(eVirtualGet(";
  protected final String TEXT_1373 = ", ";
  protected final String TEXT_1374 = "));";
  protected final String TEXT_1375 = NL + "\t\tresult.append((";
  protected final String TEXT_1376 = " & ";
  protected final String TEXT_1377 = "_EFLAG) != 0);";
  protected final String TEXT_1378 = NL + "\t\tresult.append(";
  protected final String TEXT_1379 = ");";
  protected final String TEXT_1380 = NL + "\t\tresult.append(')');" + NL + "\t\treturn result.toString();" + NL + "\t}" + NL;
  protected final String TEXT_1381 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */" + NL + "\tprotected int hash = -1;" + NL + "" + NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */" + NL + "\tpublic int getHash()" + NL + "\t{" + NL + "\t\tif (hash == -1)" + NL + "\t\t{" + NL + "\t\t\t";
  protected final String TEXT_1382 = " theKey = getKey();" + NL + "\t\t\thash = (theKey == null ? 0 : theKey.hashCode());" + NL + "\t\t}" + NL + "\t\treturn hash;" + NL + "\t}" + NL + "" + NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */" + NL + "\tpublic void setHash(int hash)" + NL + "\t{" + NL + "\t\tthis.hash = hash;" + NL + "\t}" + NL + "" + NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */" + NL + "\tpublic ";
  protected final String TEXT_1383 = " getKey()" + NL + "\t{";
  protected final String TEXT_1384 = NL + "\t\treturn new ";
  protected final String TEXT_1385 = "(getTypedKey());";
  protected final String TEXT_1386 = NL + "\t\treturn getTypedKey();";
  protected final String TEXT_1387 = NL + "\t}" + NL + "" + NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */" + NL + "\tpublic void setKey(";
  protected final String TEXT_1388 = " key)" + NL + "\t{";
  protected final String TEXT_1389 = NL + "\t\tgetTypedKey().addAll(";
  protected final String TEXT_1390 = "(";
  protected final String TEXT_1391 = ")";
  protected final String TEXT_1392 = "key);";
  protected final String TEXT_1393 = NL + "\t\tsetTypedKey(key);";
  protected final String TEXT_1394 = NL + "\t\tsetTypedKey(((";
  protected final String TEXT_1395 = ")key).";
  protected final String TEXT_1396 = "());";
  protected final String TEXT_1397 = NL + "\t\tsetTypedKey((";
  protected final String TEXT_1398 = ")key);";
  protected final String TEXT_1399 = NL + "\t}" + NL + "" + NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */" + NL + "\tpublic ";
  protected final String TEXT_1400 = " getValue()" + NL + "\t{";
  protected final String TEXT_1401 = NL + "\t\treturn new ";
  protected final String TEXT_1402 = "(getTypedValue());";
  protected final String TEXT_1403 = NL + "\t\treturn getTypedValue();";
  protected final String TEXT_1404 = NL + "\t}" + NL + "" + NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */" + NL + "\tpublic ";
  protected final String TEXT_1405 = " setValue(";
  protected final String TEXT_1406 = " value)" + NL + "\t{" + NL + "\t\t";
  protected final String TEXT_1407 = " oldValue = getValue();";
  protected final String TEXT_1408 = NL + "\t\tgetTypedValue().clear();" + NL + "\t\tgetTypedValue().addAll(";
  protected final String TEXT_1409 = "(";
  protected final String TEXT_1410 = ")";
  protected final String TEXT_1411 = "value);";
  protected final String TEXT_1412 = NL + "\t\tsetTypedValue(value);";
  protected final String TEXT_1413 = NL + "\t\tsetTypedValue(((";
  protected final String TEXT_1414 = ")value).";
  protected final String TEXT_1415 = "());";
  protected final String TEXT_1416 = NL + "\t\tsetTypedValue((";
  protected final String TEXT_1417 = ")value);";
  protected final String TEXT_1418 = NL + "\t\treturn oldValue;" + NL + "\t}" + NL + "" + NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */";
  protected final String TEXT_1419 = NL + "\t@SuppressWarnings(\"unchecked\")";
  protected final String TEXT_1420 = NL + "\tpublic ";
  protected final String TEXT_1421 = " getEMap()" + NL + "\t{" + NL + "\t\t";
  protected final String TEXT_1422 = " container = eContainer();" + NL + "\t\treturn container == null ? null : (";
  protected final String TEXT_1423 = ")container.eGet(eContainmentFeature());" + NL + "\t}" + NL;
  protected final String TEXT_1424 = NL + "} //";
  protected final String TEXT_1425 = NL;

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
    if (genModel.useGenerics() && ((genFeature.isContainer() || genFeature.isResolveProxies()) && !genFeature.isListType() && !genModel.isReflectiveDelegation() && genFeature.isUncheckedCast() || genFeature.isListType() && (genModel.isReflectiveDelegation() || genModel.isVirtualDelegation()) || genFeature.isListDataType() && genFeature.hasDelegateFeature())) {
    stringBuffer.append(TEXT_272);
    }
    stringBuffer.append(TEXT_273);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_274);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_275);
    if (genModel.isReflectiveDelegation()) {
    stringBuffer.append(TEXT_276);
    if (genFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_277);
    }
    stringBuffer.append(TEXT_278);
    stringBuffer.append(genFeature.getObjectType(genClass));
    stringBuffer.append(TEXT_279);
    stringBuffer.append(genFeature.getQualifiedFeatureAccessor());
    stringBuffer.append(TEXT_280);
    if (genFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_281);
    stringBuffer.append(genFeature.getPrimitiveValueFunction());
    stringBuffer.append(TEXT_282);
    }
    stringBuffer.append(TEXT_283);
    } else if (!genFeature.isVolatile()) {
    if (genFeature.isListType()) {
    if (genModel.isVirtualDelegation()) {
    stringBuffer.append(TEXT_284);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_285);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_286);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_287);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(positiveOffsetCorrection);
    stringBuffer.append(TEXT_288);
    }
    stringBuffer.append(TEXT_289);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_290);
    if (genModel.isVirtualDelegation()) {
    stringBuffer.append(TEXT_291);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(positiveOffsetCorrection);
    stringBuffer.append(TEXT_292);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_293);
    stringBuffer.append(genClass.getListConstructor(genFeature));
    stringBuffer.append(TEXT_294);
    } else {
    stringBuffer.append(TEXT_295);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_296);
    stringBuffer.append(genClass.getListConstructor(genFeature));
    stringBuffer.append(TEXT_297);
    }
    stringBuffer.append(TEXT_298);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(genFeature.isMapType() && genFeature.isEffectiveSuppressEMFTypes() ? ".map()" : "");
    stringBuffer.append(TEXT_299);
    } else if (genFeature.isContainer()) {
    stringBuffer.append(TEXT_300);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(positiveOffsetCorrection);
    stringBuffer.append(TEXT_301);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_302);
    } else {
    if (genFeature.isResolveProxies()) {
    if (genModel.isVirtualDelegation()) {
    stringBuffer.append(TEXT_303);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_304);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_305);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_306);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(positiveOffsetCorrection);
    if (genFeature.hasEDefault()) {
    stringBuffer.append(TEXT_307);
    stringBuffer.append(genFeature.getEDefault());
    }
    stringBuffer.append(TEXT_308);
    }
    stringBuffer.append(TEXT_309);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_310);
    stringBuffer.append(genFeature.getSafeNameAsEObject());
    stringBuffer.append(TEXT_311);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.InternalEObject"));
    stringBuffer.append(TEXT_312);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_313);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.InternalEObject"));
    stringBuffer.append(TEXT_314);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_315);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_316);
    stringBuffer.append(genFeature.getNonEObjectInternalTypeCast(genClass));
    stringBuffer.append(TEXT_317);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_318);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_319);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_320);
    if (genFeature.isEffectiveContains()) {
    stringBuffer.append(TEXT_321);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.InternalEObject"));
    stringBuffer.append(TEXT_322);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_323);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.InternalEObject"));
    stringBuffer.append(TEXT_324);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_325);
    if (!genFeature.isBidirectional()) {
    stringBuffer.append(TEXT_326);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.notify.NotificationChain"));
    stringBuffer.append(TEXT_327);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_328);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(negativeOffsetCorrection);
    stringBuffer.append(TEXT_329);
    } else { GenFeature reverseFeature = genFeature.getReverse(); GenClass targetClass = reverseFeature.getGenClass(); String reverseOffsetCorrection = targetClass.hasOffsetCorrection() ? " + " + genClass.getOffsetCorrectionField(reverseFeature) : "";
    stringBuffer.append(TEXT_330);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.notify.NotificationChain"));
    stringBuffer.append(TEXT_331);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_332);
    stringBuffer.append(targetClass.getQualifiedFeatureID(reverseFeature));
    stringBuffer.append(reverseOffsetCorrection);
    stringBuffer.append(TEXT_333);
    stringBuffer.append(targetClass.getImportedInterfaceName());
    stringBuffer.append(TEXT_334);
    }
    stringBuffer.append(TEXT_335);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_336);
    if (!genFeature.isBidirectional()) {
    stringBuffer.append(TEXT_337);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_338);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(negativeOffsetCorrection);
    stringBuffer.append(TEXT_339);
    } else { GenFeature reverseFeature = genFeature.getReverse(); GenClass targetClass = reverseFeature.getGenClass(); String reverseOffsetCorrection = targetClass.hasOffsetCorrection() ? " + " + genClass.getOffsetCorrectionField(reverseFeature) : "";
    stringBuffer.append(TEXT_340);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_341);
    stringBuffer.append(targetClass.getQualifiedFeatureID(reverseFeature));
    stringBuffer.append(reverseOffsetCorrection);
    stringBuffer.append(TEXT_342);
    stringBuffer.append(targetClass.getImportedInterfaceName());
    stringBuffer.append(TEXT_343);
    }
    stringBuffer.append(TEXT_344);
    } else if (genModel.isVirtualDelegation()) {
    stringBuffer.append(TEXT_345);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(positiveOffsetCorrection);
    stringBuffer.append(TEXT_346);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_347);
    }
    if (!genModel.isSuppressNotification()) {
    stringBuffer.append(TEXT_348);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.impl.ENotificationImpl"));
    stringBuffer.append(TEXT_349);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.notify.Notification"));
    stringBuffer.append(TEXT_350);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(positiveOffsetCorrection);
    stringBuffer.append(TEXT_351);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_352);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_353);
    }
    stringBuffer.append(TEXT_354);
    }
    if (!genFeature.isResolveProxies() && genModel.isVirtualDelegation() && !genFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_355);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_356);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(positiveOffsetCorrection);
    if (genFeature.hasEDefault()) {
    stringBuffer.append(TEXT_357);
    stringBuffer.append(genFeature.getEDefault());
    }
    stringBuffer.append(TEXT_358);
    } else if (genClass.isFlag(genFeature)) {
    stringBuffer.append(TEXT_359);
    stringBuffer.append(genClass.getFlagsField(genFeature));
    stringBuffer.append(TEXT_360);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_361);
    } else {
    stringBuffer.append(TEXT_362);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_363);
    }
    }
    } else {//volatile
    if (genFeature.isResolveProxies() && !genFeature.isListType()) {
    stringBuffer.append(TEXT_364);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_365);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_366);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_367);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_368);
    stringBuffer.append(genFeature.getSafeNameAsEObject());
    stringBuffer.append(TEXT_369);
    stringBuffer.append(genFeature.getNonEObjectInternalTypeCast(genClass));
    stringBuffer.append(TEXT_370);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.InternalEObject"));
    stringBuffer.append(TEXT_371);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_372);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_373);
    } else if (genFeature.hasDelegateFeature()) { GenFeature delegateFeature = genFeature.getDelegateFeature();
    if (genFeature.isFeatureMapType()) {
    String featureMapEntryTemplateArgument = genModel.getComplianceLevel().getValue() >= GenJDKLevel.JDK50 ? "<" + genModel.getImportedName("org.eclipse.emf.ecore.util.FeatureMap") + ".Entry>" : "";
    if (delegateFeature.isWrappedFeatureMapType()) {
    stringBuffer.append(TEXT_374);
    stringBuffer.append(genFeature.getImportedEffectiveFeatureMapWrapperClass());
    stringBuffer.append(TEXT_375);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.FeatureMap"));
    stringBuffer.append(TEXT_376);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.FeatureMap"));
    stringBuffer.append(TEXT_377);
    stringBuffer.append(delegateFeature.getAccessorName());
    stringBuffer.append(TEXT_378);
    stringBuffer.append(featureMapEntryTemplateArgument);
    stringBuffer.append(TEXT_379);
    stringBuffer.append(genFeature.getQualifiedFeatureAccessor());
    stringBuffer.append(TEXT_380);
    } else {
    stringBuffer.append(TEXT_381);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.FeatureMap"));
    stringBuffer.append(TEXT_382);
    stringBuffer.append(delegateFeature.getAccessorName());
    stringBuffer.append(TEXT_383);
    stringBuffer.append(featureMapEntryTemplateArgument);
    stringBuffer.append(TEXT_384);
    stringBuffer.append(genFeature.getQualifiedFeatureAccessor());
    stringBuffer.append(TEXT_385);
    }
    } else if (genFeature.isListType()) {
    if (delegateFeature.isWrappedFeatureMapType()) {
    stringBuffer.append(TEXT_386);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.FeatureMap"));
    stringBuffer.append(TEXT_387);
    stringBuffer.append(delegateFeature.getAccessorName());
    stringBuffer.append(TEXT_388);
    stringBuffer.append(genFeature.getQualifiedFeatureAccessor());
    stringBuffer.append(TEXT_389);
    } else {
    stringBuffer.append(TEXT_390);
    stringBuffer.append(delegateFeature.getAccessorName());
    stringBuffer.append(TEXT_391);
    stringBuffer.append(genFeature.getQualifiedFeatureAccessor());
    stringBuffer.append(TEXT_392);
    }
    } else {
    if (delegateFeature.isWrappedFeatureMapType()) {
    stringBuffer.append(TEXT_393);
    if (genFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_394);
    }
    if (genFeature.getTypeGenDataType() == null || !genFeature.getTypeGenDataType().isObjectType()) {
    stringBuffer.append(TEXT_395);
    stringBuffer.append(genFeature.getObjectType(genClass));
    stringBuffer.append(TEXT_396);
    }
    stringBuffer.append(TEXT_397);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.FeatureMap"));
    stringBuffer.append(TEXT_398);
    stringBuffer.append(delegateFeature.getAccessorName());
    stringBuffer.append(TEXT_399);
    stringBuffer.append(genFeature.getQualifiedFeatureAccessor());
    stringBuffer.append(TEXT_400);
    if (genFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_401);
    stringBuffer.append(genFeature.getPrimitiveValueFunction());
    stringBuffer.append(TEXT_402);
    }
    stringBuffer.append(TEXT_403);
    } else {
    stringBuffer.append(TEXT_404);
    if (genFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_405);
    }
    if (genFeature.getTypeGenDataType() == null || !genFeature.getTypeGenDataType().isObjectType()) {
    stringBuffer.append(TEXT_406);
    stringBuffer.append(genFeature.getObjectType(genClass));
    stringBuffer.append(TEXT_407);
    }
    stringBuffer.append(TEXT_408);
    stringBuffer.append(delegateFeature.getAccessorName());
    stringBuffer.append(TEXT_409);
    stringBuffer.append(genFeature.getQualifiedFeatureAccessor());
    stringBuffer.append(TEXT_410);
    if (genFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_411);
    stringBuffer.append(genFeature.getPrimitiveValueFunction());
    stringBuffer.append(TEXT_412);
    }
    stringBuffer.append(TEXT_413);
    }
    }
    } else {
    stringBuffer.append(TEXT_414);
    stringBuffer.append(genFeature.getFormattedName());
    stringBuffer.append(TEXT_415);
    stringBuffer.append(genFeature.getFeatureKind());
    stringBuffer.append(TEXT_416);
    if (genFeature.isListType()) {
    stringBuffer.append(TEXT_417);
    if (genFeature.isMapType()) {
    stringBuffer.append(TEXT_418);
    } else if (genFeature.isFeatureMapType()) {
    stringBuffer.append(TEXT_419);
    } else {
    stringBuffer.append(TEXT_420);
    }
    stringBuffer.append(TEXT_421);
    }
    stringBuffer.append(TEXT_422);
    //Class/getGenFeature.todo.override.javajetinc
    }
    }
    stringBuffer.append(TEXT_423);
    }
    //Class/getGenFeature.override.javajetinc
    }
    if (isImplementation && !genModel.isReflectiveDelegation() && genFeature.isBasicGet()) {
    stringBuffer.append(TEXT_424);
    if (genModel.getComplianceLevel().getValue() >= GenJDKLevel.JDK50) { //Class/basicGetGenFeature.annotations.insert.javajetinc
    }
    stringBuffer.append(TEXT_425);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_426);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_427);
    if (genFeature.isContainer()) {
    stringBuffer.append(TEXT_428);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(positiveOffsetCorrection);
    stringBuffer.append(TEXT_429);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_430);
    } else if (!genFeature.isVolatile()) {
    if (genModel.isVirtualDelegation()) {
    stringBuffer.append(TEXT_431);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_432);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(positiveOffsetCorrection);
    stringBuffer.append(TEXT_433);
    } else {
    stringBuffer.append(TEXT_434);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_435);
    }
    } else if (genFeature.hasDelegateFeature()) { GenFeature delegateFeature = genFeature.getDelegateFeature();
    if (delegateFeature.isWrappedFeatureMapType()) {
    stringBuffer.append(TEXT_436);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_437);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.FeatureMap"));
    stringBuffer.append(TEXT_438);
    stringBuffer.append(delegateFeature.getAccessorName());
    stringBuffer.append(TEXT_439);
    stringBuffer.append(genFeature.getQualifiedFeatureAccessor());
    stringBuffer.append(TEXT_440);
    } else {
    stringBuffer.append(TEXT_441);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_442);
    stringBuffer.append(delegateFeature.getAccessorName());
    stringBuffer.append(TEXT_443);
    stringBuffer.append(genFeature.getQualifiedFeatureAccessor());
    stringBuffer.append(TEXT_444);
    }
    } else {
    stringBuffer.append(TEXT_445);
    stringBuffer.append(genFeature.getFormattedName());
    stringBuffer.append(TEXT_446);
    stringBuffer.append(genFeature.getFeatureKind());
    stringBuffer.append(TEXT_447);
    //Class/basicGetGenFeature.todo.override.javajetinc
    }
    stringBuffer.append(TEXT_448);
    //Class/basicGetGenFeature.override.javajetinc
    }
    if (isImplementation && !genModel.isReflectiveDelegation() && genFeature.isBasicSet()) {
    stringBuffer.append(TEXT_449);
    if (genModel.getComplianceLevel().getValue() >= GenJDKLevel.JDK50) { //Class/basicSetGenFeature.annotations.insert.javajetinc
    }
    stringBuffer.append(TEXT_450);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.notify.NotificationChain"));
    stringBuffer.append(TEXT_451);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_452);
    stringBuffer.append(genFeature.getImportedInternalType(genClass));
    stringBuffer.append(TEXT_453);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_454);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.notify.NotificationChain"));
    stringBuffer.append(TEXT_455);
    if (genFeature.isContainer()) {
    stringBuffer.append(TEXT_456);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.InternalEObject"));
    stringBuffer.append(TEXT_457);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_458);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(positiveOffsetCorrection);
    stringBuffer.append(TEXT_459);
    stringBuffer.append(TEXT_460);
    } else if (!genFeature.isVolatile()) {
    if (genModel.isVirtualDelegation()) {
    stringBuffer.append(TEXT_461);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_462);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(positiveOffsetCorrection);
    stringBuffer.append(TEXT_463);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_464);
    } else {
    stringBuffer.append(TEXT_465);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_466);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_467);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_468);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_469);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_470);
    }
    if (genFeature.isUnsettable()) {
    if (genModel.isVirtualDelegation()) {
    stringBuffer.append(TEXT_471);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_472);
    } else if (genClass.isESetFlag(genFeature)) {
    stringBuffer.append(TEXT_473);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_474);
    stringBuffer.append(genClass.getESetFlagsField(genFeature));
    stringBuffer.append(TEXT_475);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_476);
    stringBuffer.append(genClass.getESetFlagsField(genFeature));
    stringBuffer.append(TEXT_477);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_478);
    } else {
    stringBuffer.append(TEXT_479);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_480);
    stringBuffer.append(genFeature.getUncapName());
    stringBuffer.append(TEXT_481);
    stringBuffer.append(genFeature.getUncapName());
    stringBuffer.append(TEXT_482);
    }
    }
    if (!genModel.isSuppressNotification()) {
    stringBuffer.append(TEXT_483);
    if (genFeature.isUnsettable()) {
    stringBuffer.append(TEXT_484);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.impl.ENotificationImpl"));
    stringBuffer.append(TEXT_485);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.impl.ENotificationImpl"));
    stringBuffer.append(TEXT_486);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.notify.Notification"));
    stringBuffer.append(TEXT_487);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(positiveOffsetCorrection);
    stringBuffer.append(TEXT_488);
    if (genModel.isVirtualDelegation()) {
    stringBuffer.append(TEXT_489);
    stringBuffer.append(genFeature.getCapName());
    } else {
    stringBuffer.append(TEXT_490);
    stringBuffer.append(genFeature.getCapName());
    }
    stringBuffer.append(TEXT_491);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_492);
    if (genModel.isVirtualDelegation()) {
    stringBuffer.append(TEXT_493);
    } else {
    stringBuffer.append(TEXT_494);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_495);
    }
    stringBuffer.append(TEXT_496);
    } else {
    stringBuffer.append(TEXT_497);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.impl.ENotificationImpl"));
    stringBuffer.append(TEXT_498);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.impl.ENotificationImpl"));
    stringBuffer.append(TEXT_499);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.notify.Notification"));
    stringBuffer.append(TEXT_500);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(positiveOffsetCorrection);
    stringBuffer.append(TEXT_501);
    if (genModel.isVirtualDelegation()) {
    stringBuffer.append(TEXT_502);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_503);
    stringBuffer.append(genFeature.getCapName());
    } else {
    stringBuffer.append(TEXT_504);
    stringBuffer.append(genFeature.getCapName());
    }
    stringBuffer.append(TEXT_505);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_506);
    }
    stringBuffer.append(TEXT_507);
    }
    stringBuffer.append(TEXT_508);
    } else if (genFeature.hasDelegateFeature()) { GenFeature delegateFeature = genFeature.getDelegateFeature();
    if (delegateFeature.isWrappedFeatureMapType()) {
    stringBuffer.append(TEXT_509);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.FeatureMap"));
    stringBuffer.append(TEXT_510);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.FeatureMap"));
    stringBuffer.append(TEXT_511);
    stringBuffer.append(delegateFeature.getAccessorName());
    stringBuffer.append(TEXT_512);
    stringBuffer.append(genFeature.getQualifiedFeatureAccessor());
    stringBuffer.append(TEXT_513);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_514);
    } else {
    stringBuffer.append(TEXT_515);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.FeatureMap"));
    stringBuffer.append(TEXT_516);
    stringBuffer.append(delegateFeature.getAccessorName());
    stringBuffer.append(TEXT_517);
    stringBuffer.append(genFeature.getQualifiedFeatureAccessor());
    stringBuffer.append(TEXT_518);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_519);
    }
    } else {
    stringBuffer.append(TEXT_520);
    stringBuffer.append(genFeature.getFormattedName());
    stringBuffer.append(TEXT_521);
    stringBuffer.append(genFeature.getFeatureKind());
    stringBuffer.append(TEXT_522);
    //Class/basicSetGenFeature.todo.override.javajetinc
    }
    stringBuffer.append(TEXT_523);
    //Class/basicSetGenFeature.override.javajetinc
    }
    if (genFeature.isSet() && (isImplementation || !genFeature.isSuppressedSetVisibility())) {
    if (isInterface) { 
    stringBuffer.append(TEXT_524);
    stringBuffer.append(genClass.getQualifiedInterfaceName());
    stringBuffer.append(TEXT_525);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_526);
    stringBuffer.append(genFeature.getFormattedName());
    stringBuffer.append(TEXT_527);
    stringBuffer.append(genFeature.getFeatureKind());
    stringBuffer.append(TEXT_528);
    stringBuffer.append(TEXT_529);
    stringBuffer.append(genFeature.getFormattedName());
    stringBuffer.append(TEXT_530);
    stringBuffer.append(genFeature.getFeatureKind());
    stringBuffer.append(TEXT_531);
    if (genFeature.isEnumType()) {
    stringBuffer.append(TEXT_532);
    stringBuffer.append(genFeature.getTypeGenEnum().getQualifiedName());
    }
    if (genFeature.isUnsettable()) {
    if (!genFeature.isSuppressedIsSetVisibility()) {
    stringBuffer.append(TEXT_533);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_534);
    }
    if (!genFeature.isSuppressedUnsetVisibility()) {
    stringBuffer.append(TEXT_535);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_536);
    }
    }
    stringBuffer.append(TEXT_537);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_538);
    //Class/setGenFeature.javadoc.override.javajetinc
    } else {
    stringBuffer.append(TEXT_539);
    if (genModel.getComplianceLevel().getValue() >= GenJDKLevel.JDK50) { //Class/setGenFeature.annotations.insert.javajetinc
    }
    }
    if (!isImplementation) { 
    stringBuffer.append(TEXT_540);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_541);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_542);
    } else {
    stringBuffer.append(TEXT_543);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_544);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_545);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_546);
    if (genModel.isReflectiveDelegation()) {
    stringBuffer.append(TEXT_547);
    stringBuffer.append(genFeature.getQualifiedFeatureAccessor());
    stringBuffer.append(TEXT_548);
    if (genFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_549);
    stringBuffer.append(genFeature.getObjectType(genClass));
    stringBuffer.append(TEXT_550);
    }
    stringBuffer.append(TEXT_551);
    stringBuffer.append(genFeature.getCapName());
    if (genFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_552);
    }
    stringBuffer.append(TEXT_553);
    } else if (!genFeature.isVolatile()) {
    if (genFeature.isContainer()) { GenFeature reverseFeature = genFeature.getReverse(); GenClass targetClass = reverseFeature.getGenClass(); String reverseOffsetCorrection = targetClass.hasOffsetCorrection() ? " + " + genClass.getOffsetCorrectionField(reverseFeature) : "";
    stringBuffer.append(TEXT_554);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_555);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(positiveOffsetCorrection);
    stringBuffer.append(TEXT_556);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_557);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.EcoreUtil"));
    stringBuffer.append(TEXT_558);
    stringBuffer.append(genFeature.getEObjectCast());
    stringBuffer.append(TEXT_559);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_560);
    stringBuffer.append(genModel.getImportedName("java.lang.IllegalArgumentException"));
    stringBuffer.append(TEXT_561);
    stringBuffer.append(genModel.getNonNLS());
    stringBuffer.append(TEXT_562);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.notify.NotificationChain"));
    stringBuffer.append(TEXT_563);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_564);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.InternalEObject"));
    stringBuffer.append(TEXT_565);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_566);
    stringBuffer.append(targetClass.getQualifiedFeatureID(reverseFeature));
    stringBuffer.append(reverseOffsetCorrection);
    stringBuffer.append(TEXT_567);
    stringBuffer.append(targetClass.getImportedInterfaceName());
    stringBuffer.append(TEXT_568);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_569);
    stringBuffer.append(genFeature.getInternalTypeCast());
    stringBuffer.append(TEXT_570);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_571);
    if (!genModel.isSuppressNotification()) {
    stringBuffer.append(TEXT_572);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.impl.ENotificationImpl"));
    stringBuffer.append(TEXT_573);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.notify.Notification"));
    stringBuffer.append(TEXT_574);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(positiveOffsetCorrection);
    stringBuffer.append(TEXT_575);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_576);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_577);
    }
    } else if (genFeature.isBidirectional() || genFeature.isEffectiveContains()) {
    if (genModel.isVirtualDelegation()) {
    stringBuffer.append(TEXT_578);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_579);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_580);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_581);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(positiveOffsetCorrection);
    stringBuffer.append(TEXT_582);
    }
    stringBuffer.append(TEXT_583);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_584);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_585);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.notify.NotificationChain"));
    stringBuffer.append(TEXT_586);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_587);
    if (!genFeature.isBidirectional()) {
    stringBuffer.append(TEXT_588);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.InternalEObject"));
    stringBuffer.append(TEXT_589);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_590);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(negativeOffsetCorrection);
    stringBuffer.append(TEXT_591);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_592);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.InternalEObject"));
    stringBuffer.append(TEXT_593);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_594);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(negativeOffsetCorrection);
    stringBuffer.append(TEXT_595);
    } else { GenFeature reverseFeature = genFeature.getReverse(); GenClass targetClass = reverseFeature.getGenClass(); String reverseOffsetCorrection = targetClass.hasOffsetCorrection() ? " + " + genClass.getOffsetCorrectionField(reverseFeature) : "";
    stringBuffer.append(TEXT_596);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.InternalEObject"));
    stringBuffer.append(TEXT_597);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_598);
    stringBuffer.append(targetClass.getQualifiedFeatureID(reverseFeature));
    stringBuffer.append(reverseOffsetCorrection);
    stringBuffer.append(TEXT_599);
    stringBuffer.append(targetClass.getImportedInterfaceName());
    stringBuffer.append(TEXT_600);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_601);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.InternalEObject"));
    stringBuffer.append(TEXT_602);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_603);
    stringBuffer.append(targetClass.getQualifiedFeatureID(reverseFeature));
    stringBuffer.append(reverseOffsetCorrection);
    stringBuffer.append(TEXT_604);
    stringBuffer.append(targetClass.getImportedInterfaceName());
    stringBuffer.append(TEXT_605);
    }
    stringBuffer.append(TEXT_606);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_607);
    stringBuffer.append(genFeature.getInternalTypeCast());
    stringBuffer.append(TEXT_608);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_609);
    if (genFeature.isUnsettable()) {
    stringBuffer.append(TEXT_610);
    if (genModel.isVirtualDelegation()) {
    stringBuffer.append(TEXT_611);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_612);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(positiveOffsetCorrection);
    stringBuffer.append(TEXT_613);
    } else if (genClass.isESetFlag(genFeature)) {
    if (!genModel.isSuppressNotification()) {
    stringBuffer.append(TEXT_614);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_615);
    stringBuffer.append(genClass.getESetFlagsField(genFeature));
    stringBuffer.append(TEXT_616);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_617);
    }
    stringBuffer.append(TEXT_618);
    stringBuffer.append(genClass.getESetFlagsField(genFeature));
    stringBuffer.append(TEXT_619);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_620);
    } else {
    if (!genModel.isSuppressNotification()) {
    stringBuffer.append(TEXT_621);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_622);
    stringBuffer.append(genFeature.getUncapName());
    stringBuffer.append(TEXT_623);
    }
    stringBuffer.append(TEXT_624);
    stringBuffer.append(genFeature.getUncapName());
    stringBuffer.append(TEXT_625);
    }
    if (!genModel.isSuppressNotification()) {
    stringBuffer.append(TEXT_626);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.impl.ENotificationImpl"));
    stringBuffer.append(TEXT_627);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.notify.Notification"));
    stringBuffer.append(TEXT_628);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(positiveOffsetCorrection);
    stringBuffer.append(TEXT_629);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_630);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_631);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_632);
    }
    stringBuffer.append(TEXT_633);
    } else {
    if (!genModel.isSuppressNotification()) {
    stringBuffer.append(TEXT_634);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.impl.ENotificationImpl"));
    stringBuffer.append(TEXT_635);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.notify.Notification"));
    stringBuffer.append(TEXT_636);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(positiveOffsetCorrection);
    stringBuffer.append(TEXT_637);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_638);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_639);
    }
    }
    } else {
    if (genClass.isFlag(genFeature)) {
    if (!genModel.isSuppressNotification()) {
    stringBuffer.append(TEXT_640);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_641);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_642);
    stringBuffer.append(genClass.getFlagsField(genFeature));
    stringBuffer.append(TEXT_643);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_644);
    }
    stringBuffer.append(TEXT_645);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_646);
    stringBuffer.append(genClass.getFlagsField(genFeature));
    stringBuffer.append(TEXT_647);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_648);
    stringBuffer.append(genClass.getFlagsField(genFeature));
    stringBuffer.append(TEXT_649);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_650);
    } else {
    if (!genModel.isVirtualDelegation() || genFeature.isPrimitiveType()) {
    if (!genModel.isSuppressNotification()) {
    stringBuffer.append(TEXT_651);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_652);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_653);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_654);
    }
    }
    if (genFeature.isEnumType()) {
    if (genModel.isVirtualDelegation()) {
    stringBuffer.append(TEXT_655);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_656);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_657);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_658);
    stringBuffer.append(genFeature.getEDefault());
    stringBuffer.append(TEXT_659);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_660);
    } else {
    stringBuffer.append(TEXT_661);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_662);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_663);
    stringBuffer.append(genFeature.getEDefault());
    stringBuffer.append(TEXT_664);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_665);
    }
    } else {
    if (genModel.isVirtualDelegation() && !genFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_666);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_667);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_668);
    stringBuffer.append(genFeature.getInternalTypeCast());
    stringBuffer.append(TEXT_669);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_670);
    } else {
    stringBuffer.append(TEXT_671);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_672);
    stringBuffer.append(genFeature.getInternalTypeCast());
    stringBuffer.append(TEXT_673);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_674);
    }
    }
    if (genModel.isVirtualDelegation() && !genFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_675);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_676);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(positiveOffsetCorrection);
    stringBuffer.append(TEXT_677);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_678);
    }
    }
    if (genFeature.isUnsettable()) {
    if (genModel.isVirtualDelegation() && !genFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_679);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_680);
    } else if (genClass.isESetFlag(genFeature)) {
    if (!genModel.isSuppressNotification()) {
    stringBuffer.append(TEXT_681);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_682);
    stringBuffer.append(genClass.getESetFlagsField(genFeature));
    stringBuffer.append(TEXT_683);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_684);
    }
    stringBuffer.append(TEXT_685);
    stringBuffer.append(genClass.getESetFlagsField(genFeature));
    stringBuffer.append(TEXT_686);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_687);
    } else {
    if (!genModel.isSuppressNotification()) {
    stringBuffer.append(TEXT_688);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_689);
    stringBuffer.append(genFeature.getUncapName());
    stringBuffer.append(TEXT_690);
    }
    stringBuffer.append(TEXT_691);
    stringBuffer.append(genFeature.getUncapName());
    stringBuffer.append(TEXT_692);
    }
    if (!genModel.isSuppressNotification()) {
    stringBuffer.append(TEXT_693);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.impl.ENotificationImpl"));
    stringBuffer.append(TEXT_694);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.notify.Notification"));
    stringBuffer.append(TEXT_695);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(positiveOffsetCorrection);
    stringBuffer.append(TEXT_696);
    if (genModel.isVirtualDelegation() && !genFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_697);
    stringBuffer.append(genFeature.getEDefault());
    stringBuffer.append(TEXT_698);
    stringBuffer.append(genFeature.getCapName());
    } else {
    stringBuffer.append(TEXT_699);
    stringBuffer.append(genFeature.getCapName());
    }
    stringBuffer.append(TEXT_700);
    if (genClass.isFlag(genFeature)) {
    stringBuffer.append(TEXT_701);
    stringBuffer.append(genFeature.getCapName());
    } else {
    stringBuffer.append(genFeature.getSafeName());
    }
    stringBuffer.append(TEXT_702);
    if (genModel.isVirtualDelegation() && !genFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_703);
    } else {
    stringBuffer.append(TEXT_704);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_705);
    }
    stringBuffer.append(TEXT_706);
    }
    } else {
    if (!genModel.isSuppressNotification()) {
    stringBuffer.append(TEXT_707);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.impl.ENotificationImpl"));
    stringBuffer.append(TEXT_708);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.notify.Notification"));
    stringBuffer.append(TEXT_709);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(positiveOffsetCorrection);
    stringBuffer.append(TEXT_710);
    if (genModel.isVirtualDelegation() && !genFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_711);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_712);
    stringBuffer.append(genFeature.getEDefault());
    stringBuffer.append(TEXT_713);
    stringBuffer.append(genFeature.getCapName());
    } else {
    stringBuffer.append(TEXT_714);
    stringBuffer.append(genFeature.getCapName());
    }
    stringBuffer.append(TEXT_715);
    if (genClass.isFlag(genFeature)) {
    stringBuffer.append(TEXT_716);
    stringBuffer.append(genFeature.getCapName());
    } else {
    stringBuffer.append(genFeature.getSafeName());
    }
    stringBuffer.append(TEXT_717);
    }
    }
    }
    } else if (genFeature.hasDelegateFeature()) { GenFeature delegateFeature = genFeature.getDelegateFeature();
    if (delegateFeature.isWrappedFeatureMapType()) {
    stringBuffer.append(TEXT_718);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.FeatureMap"));
    stringBuffer.append(TEXT_719);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.FeatureMap"));
    stringBuffer.append(TEXT_720);
    stringBuffer.append(delegateFeature.getAccessorName());
    stringBuffer.append(TEXT_721);
    stringBuffer.append(genFeature.getQualifiedFeatureAccessor());
    stringBuffer.append(TEXT_722);
    if (genFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_723);
    stringBuffer.append(genFeature.getObjectType(genClass));
    stringBuffer.append(TEXT_724);
    }
    stringBuffer.append(TEXT_725);
    stringBuffer.append(genFeature.getCapName());
    if (genFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_726);
    }
    stringBuffer.append(TEXT_727);
    } else {
    stringBuffer.append(TEXT_728);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.FeatureMap"));
    stringBuffer.append(TEXT_729);
    stringBuffer.append(delegateFeature.getAccessorName());
    stringBuffer.append(TEXT_730);
    stringBuffer.append(genFeature.getQualifiedFeatureAccessor());
    stringBuffer.append(TEXT_731);
    if (genFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_732);
    stringBuffer.append(genFeature.getObjectType(genClass));
    stringBuffer.append(TEXT_733);
    }
    stringBuffer.append(TEXT_734);
    stringBuffer.append(genFeature.getCapName());
    if (genFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_735);
    }
    stringBuffer.append(TEXT_736);
    }
    } else {
    stringBuffer.append(TEXT_737);
    stringBuffer.append(genFeature.getFormattedName());
    stringBuffer.append(TEXT_738);
    stringBuffer.append(genFeature.getFeatureKind());
    stringBuffer.append(TEXT_739);
    //Class/setGenFeature.todo.override.javajetinc
    }
    stringBuffer.append(TEXT_740);
    }
    //Class/setGenFeature.override.javajetinc
    }
    if (isImplementation && !genModel.isReflectiveDelegation() && genFeature.isBasicUnset()) {
    stringBuffer.append(TEXT_741);
    if (genModel.getComplianceLevel().getValue() >= GenJDKLevel.JDK50) { //Class/basicUnsetGenFeature.annotations.insert.javajetinc
    }
    stringBuffer.append(TEXT_742);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.notify.NotificationChain"));
    stringBuffer.append(TEXT_743);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_744);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.notify.NotificationChain"));
    stringBuffer.append(TEXT_745);
    if (!genFeature.isVolatile()) {
    if (genModel.isVirtualDelegation()) {
    stringBuffer.append(TEXT_746);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_747);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(positiveOffsetCorrection);
    stringBuffer.append(TEXT_748);
    } else {
    stringBuffer.append(TEXT_749);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_750);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_751);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_752);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_753);
    }
    if (genModel.isVirtualDelegation()) {
    stringBuffer.append(TEXT_754);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_755);
    } else if (genClass.isESetFlag(genFeature)) {
    stringBuffer.append(TEXT_756);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_757);
    stringBuffer.append(genClass.getESetFlagsField(genFeature));
    stringBuffer.append(TEXT_758);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_759);
    stringBuffer.append(genClass.getESetFlagsField(genFeature));
    stringBuffer.append(TEXT_760);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_761);
    } else {
    stringBuffer.append(TEXT_762);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_763);
    stringBuffer.append(genFeature.getUncapName());
    stringBuffer.append(TEXT_764);
    stringBuffer.append(genFeature.getUncapName());
    stringBuffer.append(TEXT_765);
    }
    if (!genModel.isSuppressNotification()) {
    stringBuffer.append(TEXT_766);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.impl.ENotificationImpl"));
    stringBuffer.append(TEXT_767);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.impl.ENotificationImpl"));
    stringBuffer.append(TEXT_768);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.notify.Notification"));
    stringBuffer.append(TEXT_769);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(positiveOffsetCorrection);
    stringBuffer.append(TEXT_770);
    if (genModel.isVirtualDelegation()) {
    stringBuffer.append(TEXT_771);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_772);
    } else {
    stringBuffer.append(TEXT_773);
    stringBuffer.append(genFeature.getCapName());
    }
    stringBuffer.append(TEXT_774);
    if (genModel.isVirtualDelegation()) {
    stringBuffer.append(TEXT_775);
    } else {
    stringBuffer.append(TEXT_776);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_777);
    }
    stringBuffer.append(TEXT_778);
    }
    } else {
    stringBuffer.append(TEXT_779);
    stringBuffer.append(genFeature.getFormattedName());
    stringBuffer.append(TEXT_780);
    stringBuffer.append(genFeature.getFeatureKind());
    stringBuffer.append(TEXT_781);
    //Class/basicUnsetGenFeature.todo.override.javajetinc
    }
    stringBuffer.append(TEXT_782);
    //Class.basicUnsetGenFeature.override.javajetinc
    }
    if (genFeature.isUnset() && (isImplementation || !genFeature.isSuppressedUnsetVisibility())) {
    if (isInterface) {
    stringBuffer.append(TEXT_783);
    stringBuffer.append(genClass.getQualifiedInterfaceName());
    stringBuffer.append(TEXT_784);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_785);
    stringBuffer.append(genFeature.getFormattedName());
    stringBuffer.append(TEXT_786);
    stringBuffer.append(genFeature.getFeatureKind());
    stringBuffer.append(TEXT_787);
    stringBuffer.append(TEXT_788);
    if (!genFeature.isSuppressedIsSetVisibility()) {
    stringBuffer.append(TEXT_789);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_790);
    }
    stringBuffer.append(TEXT_791);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_792);
    if (!genFeature.isListType() && !genFeature.isSuppressedSetVisibility()) {
    stringBuffer.append(TEXT_793);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_794);
    stringBuffer.append(genFeature.getRawImportedBoundType());
    stringBuffer.append(TEXT_795);
    }
    stringBuffer.append(TEXT_796);
    //Class/unsetGenFeature.javadoc.override.javajetinc
    } else {
    stringBuffer.append(TEXT_797);
    if (genModel.getComplianceLevel().getValue() >= GenJDKLevel.JDK50) { //Class/unsetGenFeature.annotations.insert.javajetinc
    }
    }
    if (!isImplementation) {
    stringBuffer.append(TEXT_798);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_799);
    } else {
    stringBuffer.append(TEXT_800);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_801);
    if (genModel.isReflectiveDelegation()) {
    stringBuffer.append(TEXT_802);
    stringBuffer.append(genFeature.getQualifiedFeatureAccessor());
    stringBuffer.append(TEXT_803);
    } else if (!genFeature.isVolatile()) {
    if (genFeature.isListType()) {
    if (genModel.isVirtualDelegation()) {
    stringBuffer.append(TEXT_804);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_805);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_806);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_807);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(positiveOffsetCorrection);
    stringBuffer.append(TEXT_808);
    }
    stringBuffer.append(TEXT_809);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_810);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.InternalEList"));
    stringBuffer.append(TEXT_811);
    stringBuffer.append(singleWildcard);
    stringBuffer.append(TEXT_812);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_813);
    } else if (genFeature.isBidirectional() || genFeature.isEffectiveContains()) {
    if (genModel.isVirtualDelegation()) {
    stringBuffer.append(TEXT_814);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_815);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_816);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_817);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(positiveOffsetCorrection);
    stringBuffer.append(TEXT_818);
    }
    stringBuffer.append(TEXT_819);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_820);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.notify.NotificationChain"));
    stringBuffer.append(TEXT_821);
    if (!genFeature.isBidirectional()) {
    stringBuffer.append(TEXT_822);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.InternalEObject"));
    stringBuffer.append(TEXT_823);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_824);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(negativeOffsetCorrection);
    stringBuffer.append(TEXT_825);
    } else { GenFeature reverseFeature = genFeature.getReverse(); GenClass targetClass = reverseFeature.getGenClass(); String reverseOffsetCorrection = targetClass.hasOffsetCorrection() ? " + " + genClass.getOffsetCorrectionField(reverseFeature) : "";
    stringBuffer.append(TEXT_826);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.InternalEObject"));
    stringBuffer.append(TEXT_827);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_828);
    stringBuffer.append(targetClass.getQualifiedFeatureID(reverseFeature));
    stringBuffer.append(reverseOffsetCorrection);
    stringBuffer.append(TEXT_829);
    stringBuffer.append(targetClass.getImportedInterfaceName());
    stringBuffer.append(TEXT_830);
    }
    stringBuffer.append(TEXT_831);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_832);
    if (genModel.isVirtualDelegation()) {
    stringBuffer.append(TEXT_833);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_834);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(positiveOffsetCorrection);
    stringBuffer.append(TEXT_835);
    } else if (genClass.isESetFlag(genFeature)) {
    if (!genModel.isSuppressNotification()) {
    stringBuffer.append(TEXT_836);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_837);
    stringBuffer.append(genClass.getESetFlagsField(genFeature));
    stringBuffer.append(TEXT_838);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_839);
    }
    stringBuffer.append(TEXT_840);
    stringBuffer.append(genClass.getESetFlagsField(genFeature));
    stringBuffer.append(TEXT_841);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_842);
    } else {
    if (!genModel.isSuppressNotification()) {
    stringBuffer.append(TEXT_843);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_844);
    stringBuffer.append(genFeature.getUncapName());
    stringBuffer.append(TEXT_845);
    }
    stringBuffer.append(TEXT_846);
    stringBuffer.append(genFeature.getUncapName());
    stringBuffer.append(TEXT_847);
    }
    if (!genModel.isSuppressNotification()) {
    stringBuffer.append(TEXT_848);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.impl.ENotificationImpl"));
    stringBuffer.append(TEXT_849);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.notify.Notification"));
    stringBuffer.append(TEXT_850);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(positiveOffsetCorrection);
    stringBuffer.append(TEXT_851);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_852);
    }
    stringBuffer.append(TEXT_853);
    } else {
    if (genClass.isFlag(genFeature)) {
    if (!genModel.isSuppressNotification()) {
    stringBuffer.append(TEXT_854);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_855);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_856);
    stringBuffer.append(genClass.getFlagsField(genFeature));
    stringBuffer.append(TEXT_857);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_858);
    }
    } else if (genModel.isVirtualDelegation() && !genFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_859);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_860);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(positiveOffsetCorrection);
    stringBuffer.append(TEXT_861);
    } else {
    if (!genModel.isSuppressNotification()) {
    stringBuffer.append(TEXT_862);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_863);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_864);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_865);
    }
    }
    if (genModel.isVirtualDelegation() && !genFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_866);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_867);
    } else if (genClass.isESetFlag(genFeature)) {
    stringBuffer.append(TEXT_868);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_869);
    stringBuffer.append(genClass.getESetFlagsField(genFeature));
    stringBuffer.append(TEXT_870);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_871);
    } else {
    if (!genModel.isSuppressNotification()) {
    stringBuffer.append(TEXT_872);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_873);
    stringBuffer.append(genFeature.getUncapName());
    stringBuffer.append(TEXT_874);
    }
    }
    if (genFeature.isReferenceType()) {
    stringBuffer.append(TEXT_875);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_876);
    if (!genModel.isVirtualDelegation()) {
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
    stringBuffer.append(positiveOffsetCorrection);
    stringBuffer.append(TEXT_885);
    if (genModel.isVirtualDelegation()) {
    stringBuffer.append(TEXT_886);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_887);
    } else {
    stringBuffer.append(TEXT_888);
    stringBuffer.append(genFeature.getCapName());
    }
    stringBuffer.append(TEXT_889);
    if (genModel.isVirtualDelegation()) {
    stringBuffer.append(TEXT_890);
    } else {
    stringBuffer.append(TEXT_891);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_892);
    }
    stringBuffer.append(TEXT_893);
    }
    } else {
    if (genClass.isFlag(genFeature)) {
    stringBuffer.append(TEXT_894);
    stringBuffer.append(genFeature.getEDefault());
    stringBuffer.append(TEXT_895);
    stringBuffer.append(genClass.getFlagsField(genFeature));
    stringBuffer.append(TEXT_896);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_897);
    stringBuffer.append(genClass.getFlagsField(genFeature));
    stringBuffer.append(TEXT_898);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_899);
    } else if (!genModel.isVirtualDelegation() || genFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_900);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_901);
    stringBuffer.append(genFeature.getEDefault());
    stringBuffer.append(TEXT_902);
    }
    if (!genModel.isVirtualDelegation() || genFeature.isPrimitiveType()) {
    if (genClass.isESetFlag(genFeature)) {
    stringBuffer.append(TEXT_903);
    stringBuffer.append(genClass.getESetFlagsField(genFeature));
    stringBuffer.append(TEXT_904);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_905);
    } else {
    stringBuffer.append(TEXT_906);
    stringBuffer.append(genFeature.getUncapName());
    stringBuffer.append(TEXT_907);
    }
    }
    if (!genModel.isSuppressNotification()) {
    stringBuffer.append(TEXT_908);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.impl.ENotificationImpl"));
    stringBuffer.append(TEXT_909);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.notify.Notification"));
    stringBuffer.append(TEXT_910);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(positiveOffsetCorrection);
    stringBuffer.append(TEXT_911);
    if (genModel.isVirtualDelegation() && !genFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_912);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_913);
    stringBuffer.append(genFeature.getEDefault());
    } else {
    stringBuffer.append(TEXT_914);
    stringBuffer.append(genFeature.getCapName());
    }
    stringBuffer.append(TEXT_915);
    stringBuffer.append(genFeature.getEDefault());
    stringBuffer.append(TEXT_916);
    if (genModel.isVirtualDelegation() && !genFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_917);
    } else {
    stringBuffer.append(TEXT_918);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_919);
    }
    stringBuffer.append(TEXT_920);
    }
    }
    }
    } else if (genFeature.hasDelegateFeature()) { GenFeature delegateFeature = genFeature.getDelegateFeature();
    if (delegateFeature.isWrappedFeatureMapType()) {
    stringBuffer.append(TEXT_921);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.FeatureMap"));
    stringBuffer.append(TEXT_922);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.FeatureMap"));
    stringBuffer.append(TEXT_923);
    stringBuffer.append(delegateFeature.getAccessorName());
    stringBuffer.append(TEXT_924);
    stringBuffer.append(genFeature.getQualifiedFeatureAccessor());
    stringBuffer.append(TEXT_925);
    } else {
    stringBuffer.append(TEXT_926);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.FeatureMap"));
    stringBuffer.append(TEXT_927);
    stringBuffer.append(delegateFeature.getAccessorName());
    stringBuffer.append(TEXT_928);
    stringBuffer.append(genFeature.getQualifiedFeatureAccessor());
    stringBuffer.append(TEXT_929);
    }
    } else {
    stringBuffer.append(TEXT_930);
    stringBuffer.append(genFeature.getFormattedName());
    stringBuffer.append(TEXT_931);
    stringBuffer.append(genFeature.getFeatureKind());
    stringBuffer.append(TEXT_932);
    //Class/unsetGenFeature.todo.override.javajetinc
    }
    stringBuffer.append(TEXT_933);
    }
    //Class/unsetGenFeature.override.javajetinc
    }
    if (genFeature.isIsSet() && (isImplementation || !genFeature.isSuppressedIsSetVisibility())) {
    if (isInterface) {
    stringBuffer.append(TEXT_934);
    stringBuffer.append(genClass.getQualifiedInterfaceName());
    stringBuffer.append(TEXT_935);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_936);
    stringBuffer.append(genFeature.getFormattedName());
    stringBuffer.append(TEXT_937);
    stringBuffer.append(genFeature.getFeatureKind());
    stringBuffer.append(TEXT_938);
    stringBuffer.append(TEXT_939);
    stringBuffer.append(genFeature.getFormattedName());
    stringBuffer.append(TEXT_940);
    stringBuffer.append(genFeature.getFeatureKind());
    stringBuffer.append(TEXT_941);
    if (genFeature.isChangeable() && !genFeature.isSuppressedUnsetVisibility()) {
    stringBuffer.append(TEXT_942);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_943);
    }
    stringBuffer.append(TEXT_944);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_945);
    if (!genFeature.isListType() && genFeature.isChangeable() && !genFeature.isSuppressedSetVisibility()) {
    stringBuffer.append(TEXT_946);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_947);
    stringBuffer.append(genFeature.getRawImportedBoundType());
    stringBuffer.append(TEXT_948);
    }
    stringBuffer.append(TEXT_949);
    //Class/isSetGenFeature.javadoc.override.javajetinc
    } else {
    stringBuffer.append(TEXT_950);
    if (genModel.getComplianceLevel().getValue() >= GenJDKLevel.JDK50) { //Class/isSetGenFeature.annotations.insert.javajetinc
    }
    }
    if (!isImplementation) {
    stringBuffer.append(TEXT_951);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_952);
    } else {
    stringBuffer.append(TEXT_953);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_954);
    if (genModel.isReflectiveDelegation()) {
    stringBuffer.append(TEXT_955);
    stringBuffer.append(genFeature.getQualifiedFeatureAccessor());
    stringBuffer.append(TEXT_956);
    } else if (!genFeature.isVolatile()) {
    if (genFeature.isListType()) {
    if (genModel.isVirtualDelegation()) {
    stringBuffer.append(TEXT_957);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_958);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_959);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_960);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(positiveOffsetCorrection);
    stringBuffer.append(TEXT_961);
    }
    stringBuffer.append(TEXT_962);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_963);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.InternalEList"));
    stringBuffer.append(TEXT_964);
    stringBuffer.append(singleWildcard);
    stringBuffer.append(TEXT_965);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_966);
    } else {
    if (genModel.isVirtualDelegation() && !genFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_967);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(positiveOffsetCorrection);
    stringBuffer.append(TEXT_968);
    } else if (genClass.isESetFlag(genFeature)) {
    stringBuffer.append(TEXT_969);
    stringBuffer.append(genClass.getESetFlagsField(genFeature));
    stringBuffer.append(TEXT_970);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_971);
    } else {
    stringBuffer.append(TEXT_972);
    stringBuffer.append(genFeature.getUncapName());
    stringBuffer.append(TEXT_973);
    }
    }
    } else if (genFeature.hasDelegateFeature()) { GenFeature delegateFeature = genFeature.getDelegateFeature();
    if (delegateFeature.isWrappedFeatureMapType()) {
    stringBuffer.append(TEXT_974);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.FeatureMap"));
    stringBuffer.append(TEXT_975);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.FeatureMap"));
    stringBuffer.append(TEXT_976);
    stringBuffer.append(delegateFeature.getAccessorName());
    stringBuffer.append(TEXT_977);
    stringBuffer.append(genFeature.getQualifiedFeatureAccessor());
    stringBuffer.append(TEXT_978);
    } else {
    stringBuffer.append(TEXT_979);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.FeatureMap"));
    stringBuffer.append(TEXT_980);
    stringBuffer.append(delegateFeature.getAccessorName());
    stringBuffer.append(TEXT_981);
    stringBuffer.append(genFeature.getQualifiedFeatureAccessor());
    stringBuffer.append(TEXT_982);
    }
    } else {
    stringBuffer.append(TEXT_983);
    stringBuffer.append(genFeature.getFormattedName());
    stringBuffer.append(TEXT_984);
    stringBuffer.append(genFeature.getFeatureKind());
    stringBuffer.append(TEXT_985);
    //Class/isSetGenFeature.todo.override.javajetinc
    }
    stringBuffer.append(TEXT_986);
    }
    //Class/isSetGenFeature.override.javajetinc
    }
    //Class/genFeature.override.javajetinc
    }//for
    }}.run();
    for (GenOperation genOperation : (isImplementation ? genClass.getImplementedGenOperations() : genClass.getDeclaredGenOperations())) {
    if (isInterface) {
    stringBuffer.append(TEXT_987);
    stringBuffer.append(TEXT_988);
    if (genOperation.hasDocumentation() || genOperation.hasParameterDocumentation()) {
    stringBuffer.append(TEXT_989);
    if (genOperation.hasDocumentation()) {
    stringBuffer.append(TEXT_990);
    stringBuffer.append(genOperation.getDocumentation(genModel.getIndentation(stringBuffer)));
    }
    for (GenParameter genParameter : genOperation.getGenParameters()) {
    if (genParameter.hasDocumentation()) { String documentation = genParameter.getDocumentation("");
    if (documentation.contains("\n") || documentation.contains("\r")) {
    stringBuffer.append(TEXT_991);
    stringBuffer.append(genParameter.getName());
    stringBuffer.append(TEXT_992);
    stringBuffer.append(genParameter.getDocumentation(genModel.getIndentation(stringBuffer)));
    } else {
    stringBuffer.append(TEXT_993);
    stringBuffer.append(genParameter.getName());
    stringBuffer.append(TEXT_994);
    stringBuffer.append(genParameter.getDocumentation(genModel.getIndentation(stringBuffer)));
    }
    }
    }
    stringBuffer.append(TEXT_995);
    }
    if (!genModel.isSuppressEMFModelTags()) { boolean first = true; for (StringTokenizer stringTokenizer = new StringTokenizer(genOperation.getModelInfo(), "\n\r"); stringTokenizer.hasMoreTokens(); ) { String modelInfo = stringTokenizer.nextToken(); if (first) { first = false;
    stringBuffer.append(TEXT_996);
    stringBuffer.append(modelInfo);
    } else {
    stringBuffer.append(TEXT_997);
    stringBuffer.append(modelInfo);
    }} if (first) {
    stringBuffer.append(TEXT_998);
    }}
    stringBuffer.append(TEXT_999);
    //Class/genOperation.javadoc.override.javajetinc
    } else {
    stringBuffer.append(TEXT_1000);
    if (genModel.getComplianceLevel().getValue() >= GenJDKLevel.JDK50) { //Class/genOperation.annotations.insert.javajetinc
    }
    }
    if (!isImplementation) {
    stringBuffer.append(TEXT_1001);
    stringBuffer.append(genOperation.getTypeParameters(genClass));
    stringBuffer.append(genOperation.getImportedType(genClass));
    stringBuffer.append(TEXT_1002);
    stringBuffer.append(genOperation.getName());
    stringBuffer.append(TEXT_1003);
    stringBuffer.append(genOperation.getParameters(genClass));
    stringBuffer.append(TEXT_1004);
    stringBuffer.append(genOperation.getThrows(genClass));
    stringBuffer.append(TEXT_1005);
    } else {
    stringBuffer.append(TEXT_1006);
    stringBuffer.append(genOperation.getTypeParameters(genClass));
    stringBuffer.append(genOperation.getImportedType(genClass));
    stringBuffer.append(TEXT_1007);
    stringBuffer.append(genOperation.getName());
    stringBuffer.append(TEXT_1008);
    stringBuffer.append(genOperation.getParameters(genClass));
    stringBuffer.append(TEXT_1009);
    stringBuffer.append(genOperation.getThrows(genClass));
    stringBuffer.append(TEXT_1010);
    if (genOperation.hasBody()) {
    stringBuffer.append(TEXT_1011);
    stringBuffer.append(genOperation.getBody(genModel.getIndentation(stringBuffer)));
    } else if (genOperation.isInvariant()) {GenClass opClass = genOperation.getGenClass(); String diagnostics = genOperation.getGenParameters().get(0).getName(); String context = genOperation.getGenParameters().get(1).getName();
    stringBuffer.append(TEXT_1012);
    stringBuffer.append(diagnostics);
    stringBuffer.append(TEXT_1013);
    stringBuffer.append(diagnostics);
    stringBuffer.append(TEXT_1014);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.util.BasicDiagnostic"));
    stringBuffer.append(TEXT_1015);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.util.Diagnostic"));
    stringBuffer.append(TEXT_1016);
    stringBuffer.append(opClass.getGenPackage().getImportedValidatorClassName());
    stringBuffer.append(TEXT_1017);
    stringBuffer.append(opClass.getGenPackage().getImportedValidatorClassName());
    stringBuffer.append(TEXT_1018);
    stringBuffer.append(opClass.getOperationID(genOperation));
    stringBuffer.append(TEXT_1019);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.plugin.EcorePlugin"));
    stringBuffer.append(TEXT_1020);
    stringBuffer.append(genOperation.getName());
    stringBuffer.append(TEXT_1021);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.EObjectValidator"));
    stringBuffer.append(TEXT_1022);
    stringBuffer.append(context);
    stringBuffer.append(TEXT_1023);
    stringBuffer.append(genModel.getNonNLS());
    stringBuffer.append(genModel.getNonNLS(2));
    stringBuffer.append(TEXT_1024);
    } else {
    stringBuffer.append(TEXT_1025);
    //Class/implementedGenOperation.todo.override.javajetinc
    }
    stringBuffer.append(TEXT_1026);
    }
    //Class/implementedGenOperation.override.javajetinc
    }//for
    if (isImplementation && !genModel.isReflectiveDelegation() && genClass.implementsAny(genClass.getEInverseAddGenFeatures())) {
    stringBuffer.append(TEXT_1027);
    if (genModel.useGenerics()) {
    for (GenFeature genFeature : genClass.getEInverseAddGenFeatures()) {
    if (genFeature.isUncheckedCast()) {
    stringBuffer.append(TEXT_1028);
    break; }
    }
    }
    if (genModel.useClassOverrideAnnotation()) {
    stringBuffer.append(TEXT_1029);
    }
    stringBuffer.append(TEXT_1030);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.notify.NotificationChain"));
    stringBuffer.append(TEXT_1031);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.InternalEObject"));
    stringBuffer.append(TEXT_1032);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.notify.NotificationChain"));
    stringBuffer.append(TEXT_1033);
    stringBuffer.append(negativeOffsetCorrection);
    stringBuffer.append(TEXT_1034);
    for (GenFeature genFeature : genClass.getEInverseAddGenFeatures()) {
    stringBuffer.append(TEXT_1035);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(TEXT_1036);
    if (genFeature.isListType()) { String cast = "("  + genModel.getImportedName("org.eclipse.emf.ecore.util.InternalEList") + (!genModel.useGenerics() ? ")" : "<" + genModel.getImportedName("org.eclipse.emf.ecore.InternalEObject") + ">)(" + genModel.getImportedName("org.eclipse.emf.ecore.util.InternalEList") + "<?>)");
    if (genFeature.isMapType() && genFeature.isEffectiveSuppressEMFTypes()) {
    stringBuffer.append(TEXT_1037);
    stringBuffer.append(cast);
    stringBuffer.append(TEXT_1038);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.util.EMap"));
    stringBuffer.append(TEXT_1039);
    stringBuffer.append(genFeature.getImportedMapTemplateArguments(genClass));
    stringBuffer.append(TEXT_1040);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_1041);
    } else {
    stringBuffer.append(TEXT_1042);
    stringBuffer.append(cast);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_1043);
    }
    } else if (genFeature.isContainer()) {
    stringBuffer.append(TEXT_1044);
    if (genFeature.isBasicSet()) {
    stringBuffer.append(TEXT_1045);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_1046);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_1047);
    } else {
    stringBuffer.append(TEXT_1048);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(positiveOffsetCorrection);
    stringBuffer.append(TEXT_1049);
    }
    } else {
    if (genClass.getImplementingGenModel(genFeature).isVirtualDelegation()) {
    stringBuffer.append(TEXT_1050);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_1051);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_1052);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_1053);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(positiveOffsetCorrection);
    stringBuffer.append(TEXT_1054);
    }
    stringBuffer.append(TEXT_1055);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_1056);
    if (genFeature.isEffectiveContains()) {
    stringBuffer.append(TEXT_1057);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.InternalEObject"));
    stringBuffer.append(TEXT_1058);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_1059);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(negativeOffsetCorrection);
    stringBuffer.append(TEXT_1060);
    } else { GenFeature reverseFeature = genFeature.getReverse(); GenClass targetClass = reverseFeature.getGenClass(); String reverseOffsetCorrection = targetClass.hasOffsetCorrection() ? " + " + genClass.getOffsetCorrectionField(reverseFeature) : "";
    stringBuffer.append(TEXT_1061);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.InternalEObject"));
    stringBuffer.append(TEXT_1062);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_1063);
    stringBuffer.append(targetClass.getQualifiedFeatureID(reverseFeature));
    stringBuffer.append(reverseOffsetCorrection);
    stringBuffer.append(TEXT_1064);
    stringBuffer.append(targetClass.getImportedInterfaceName());
    stringBuffer.append(TEXT_1065);
    }
    stringBuffer.append(TEXT_1066);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_1067);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_1068);
    }
    }
    stringBuffer.append(TEXT_1069);
    if (genModel.isMinimalReflectiveMethods()) {
    stringBuffer.append(TEXT_1070);
    } else {
    stringBuffer.append(TEXT_1071);
    }
    stringBuffer.append(TEXT_1072);
    }
    if (isImplementation && !genModel.isReflectiveDelegation() && genClass.implementsAny(genClass.getEInverseRemoveGenFeatures())) {
    stringBuffer.append(TEXT_1073);
    if (genModel.useClassOverrideAnnotation()) {
    stringBuffer.append(TEXT_1074);
    }
    stringBuffer.append(TEXT_1075);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.notify.NotificationChain"));
    stringBuffer.append(TEXT_1076);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.InternalEObject"));
    stringBuffer.append(TEXT_1077);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.notify.NotificationChain"));
    stringBuffer.append(TEXT_1078);
    stringBuffer.append(negativeOffsetCorrection);
    stringBuffer.append(TEXT_1079);
    for (GenFeature genFeature : genClass.getEInverseRemoveGenFeatures()) {
    stringBuffer.append(TEXT_1080);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(TEXT_1081);
    if (genFeature.isListType()) {
    if (genFeature.isMapType() && genFeature.isEffectiveSuppressEMFTypes()) {
    stringBuffer.append(TEXT_1082);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.InternalEList"));
    stringBuffer.append(singleWildcard);
    stringBuffer.append(TEXT_1083);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.util.EMap"));
    stringBuffer.append(TEXT_1084);
    stringBuffer.append(genFeature.getImportedMapTemplateArguments(genClass));
    stringBuffer.append(TEXT_1085);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_1086);
    } else if (genFeature.isWrappedFeatureMapType()) {
    stringBuffer.append(TEXT_1087);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.InternalEList"));
    stringBuffer.append(singleWildcard);
    stringBuffer.append(TEXT_1088);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.FeatureMap"));
    stringBuffer.append(TEXT_1089);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_1090);
    } else {
    stringBuffer.append(TEXT_1091);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.InternalEList"));
    stringBuffer.append(singleWildcard);
    stringBuffer.append(TEXT_1092);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_1093);
    }
    } else if (genFeature.isContainer() && !genFeature.isBasicSet()) {
    stringBuffer.append(TEXT_1094);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(positiveOffsetCorrection);
    stringBuffer.append(TEXT_1095);
    } else if (genFeature.isUnsettable()) {
    stringBuffer.append(TEXT_1096);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_1097);
    } else {
    stringBuffer.append(TEXT_1098);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_1099);
    }
    }
    stringBuffer.append(TEXT_1100);
    if (genModel.isMinimalReflectiveMethods()) {
    stringBuffer.append(TEXT_1101);
    } else {
    stringBuffer.append(TEXT_1102);
    }
    stringBuffer.append(TEXT_1103);
    }
    if (isImplementation && !genModel.isReflectiveDelegation() && genClass.implementsAny(genClass.getEBasicRemoveFromContainerGenFeatures())) {
    stringBuffer.append(TEXT_1104);
    if (genModel.useClassOverrideAnnotation()) {
    stringBuffer.append(TEXT_1105);
    }
    stringBuffer.append(TEXT_1106);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.notify.NotificationChain"));
    stringBuffer.append(TEXT_1107);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.notify.NotificationChain"));
    stringBuffer.append(TEXT_1108);
    stringBuffer.append(negativeOffsetCorrection);
    stringBuffer.append(TEXT_1109);
    for (GenFeature genFeature : genClass.getEBasicRemoveFromContainerGenFeatures()) {
    GenFeature reverseFeature = genFeature.getReverse(); GenClass targetClass = reverseFeature.getGenClass(); String reverseOffsetCorrection = targetClass.hasOffsetCorrection() ? " + " + genClass.getOffsetCorrectionField(reverseFeature) : "";
    stringBuffer.append(TEXT_1110);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(TEXT_1111);
    stringBuffer.append(targetClass.getQualifiedFeatureID(reverseFeature));
    stringBuffer.append(reverseOffsetCorrection);
    stringBuffer.append(TEXT_1112);
    stringBuffer.append(targetClass.getImportedInterfaceName());
    stringBuffer.append(TEXT_1113);
    }
    stringBuffer.append(TEXT_1114);
    if (genModel.isMinimalReflectiveMethods()) {
    stringBuffer.append(TEXT_1115);
    } else {
    stringBuffer.append(TEXT_1116);
    }
    stringBuffer.append(TEXT_1117);
    }
    if (isImplementation && !genModel.isReflectiveDelegation() && genClass.implementsAny(genClass.getEGetGenFeatures())) {
    stringBuffer.append(TEXT_1118);
    if (genModel.useClassOverrideAnnotation()) {
    stringBuffer.append(TEXT_1119);
    }
    stringBuffer.append(TEXT_1120);
    stringBuffer.append(negativeOffsetCorrection);
    stringBuffer.append(TEXT_1121);
    for (GenFeature genFeature : genClass.getEGetGenFeatures()) {
    stringBuffer.append(TEXT_1122);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(TEXT_1123);
    if (genFeature.isPrimitiveType()) {
    if (genFeature.isBooleanType()) {
    stringBuffer.append(TEXT_1124);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_1125);
    } else {
    stringBuffer.append(TEXT_1126);
    stringBuffer.append(genFeature.getObjectType(genClass));
    stringBuffer.append(TEXT_1127);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_1128);
    }
    } else if (genFeature.isResolveProxies() && !genFeature.isListType()) {
    stringBuffer.append(TEXT_1129);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_1130);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_1131);
    } else if (genFeature.isMapType()) {
    if (genFeature.isEffectiveSuppressEMFTypes()) {
    stringBuffer.append(TEXT_1132);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.util.EMap"));
    stringBuffer.append(TEXT_1133);
    stringBuffer.append(genFeature.getImportedMapTemplateArguments(genClass));
    stringBuffer.append(TEXT_1134);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_1135);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_1136);
    } else {
    stringBuffer.append(TEXT_1137);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_1138);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_1139);
    }
    } else if (genFeature.isWrappedFeatureMapType()) {
    stringBuffer.append(TEXT_1140);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.FeatureMap"));
    stringBuffer.append(TEXT_1141);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_1142);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_1143);
    } else if (genFeature.isFeatureMapType()) {
    stringBuffer.append(TEXT_1144);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_1145);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.FeatureMap"));
    stringBuffer.append(TEXT_1146);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_1147);
    } else {
    stringBuffer.append(TEXT_1148);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_1149);
    }
    }
    stringBuffer.append(TEXT_1150);
    if (genModel.isMinimalReflectiveMethods()) {
    stringBuffer.append(TEXT_1151);
    } else {
    stringBuffer.append(TEXT_1152);
    }
    stringBuffer.append(TEXT_1153);
    }
    if (isImplementation && !genModel.isReflectiveDelegation() && genClass.implementsAny(genClass.getESetGenFeatures())) {
    stringBuffer.append(TEXT_1154);
    if (genModel.useGenerics()) {
    for (GenFeature genFeature : genClass.getESetGenFeatures()) {
    if (genFeature.isUncheckedCast() && !genFeature.isFeatureMapType() && !genFeature.isMapType()) {
    stringBuffer.append(TEXT_1155);
    break; }
    }
    }
    if (genModel.useClassOverrideAnnotation()) {
    stringBuffer.append(TEXT_1156);
    }
    stringBuffer.append(TEXT_1157);
    stringBuffer.append(negativeOffsetCorrection);
    stringBuffer.append(TEXT_1158);
    for (GenFeature genFeature : genClass.getESetGenFeatures()) {
    stringBuffer.append(TEXT_1159);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(TEXT_1160);
    if (genFeature.isListType()) {
    if (genFeature.isWrappedFeatureMapType()) {
    stringBuffer.append(TEXT_1161);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.FeatureMap"));
    stringBuffer.append(TEXT_1162);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.FeatureMap"));
    stringBuffer.append(TEXT_1163);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_1164);
    } else if (genFeature.isFeatureMapType()) {
    stringBuffer.append(TEXT_1165);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.FeatureMap"));
    stringBuffer.append(TEXT_1166);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_1167);
    } else if (genFeature.isMapType()) {
    if (genFeature.isEffectiveSuppressEMFTypes()) {
    stringBuffer.append(TEXT_1168);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.EStructuralFeature"));
    stringBuffer.append(TEXT_1169);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.util.EMap"));
    stringBuffer.append(TEXT_1170);
    stringBuffer.append(genFeature.getImportedMapTemplateArguments(genClass));
    stringBuffer.append(TEXT_1171);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_1172);
    } else {
    stringBuffer.append(TEXT_1173);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.EStructuralFeature"));
    stringBuffer.append(TEXT_1174);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_1175);
    }
    } else {
    stringBuffer.append(TEXT_1176);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_1177);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_1178);
    stringBuffer.append(genModel.getImportedName("java.util.Collection"));
    if (genModel.getComplianceLevel().getValue() >= GenJDKLevel.JDK50) {
    stringBuffer.append(TEXT_1179);
    stringBuffer.append(genFeature.getListItemType(genClass));
    stringBuffer.append(TEXT_1180);
    }
    stringBuffer.append(TEXT_1181);
    }
    } else if (genFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_1182);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_1183);
    stringBuffer.append(genFeature.getObjectType(genClass));
    stringBuffer.append(TEXT_1184);
    stringBuffer.append(genFeature.getPrimitiveValueFunction());
    stringBuffer.append(TEXT_1185);
    } else {
    stringBuffer.append(TEXT_1186);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_1187);
    if (genFeature.getTypeGenDataType() == null || !genFeature.getTypeGenDataType().isObjectType() || !genFeature.getRawType().equals(genFeature.getType(genClass))) {
    stringBuffer.append(TEXT_1188);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_1189);
    }
    stringBuffer.append(TEXT_1190);
    }
    stringBuffer.append(TEXT_1191);
    }
    stringBuffer.append(TEXT_1192);
    if (genModel.isMinimalReflectiveMethods()) {
    stringBuffer.append(TEXT_1193);
    } else {
    stringBuffer.append(TEXT_1194);
    }
    stringBuffer.append(TEXT_1195);
    }
    if (isImplementation && !genModel.isReflectiveDelegation() && genClass.implementsAny(genClass.getEUnsetGenFeatures())) {
    stringBuffer.append(TEXT_1196);
    if (genModel.useClassOverrideAnnotation()) {
    stringBuffer.append(TEXT_1197);
    }
    stringBuffer.append(TEXT_1198);
    stringBuffer.append(negativeOffsetCorrection);
    stringBuffer.append(TEXT_1199);
    for (GenFeature genFeature : genClass.getEUnsetGenFeatures()) {
    stringBuffer.append(TEXT_1200);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(TEXT_1201);
    if (genFeature.isListType() && !genFeature.isUnsettable()) {
    if (genFeature.isWrappedFeatureMapType()) {
    stringBuffer.append(TEXT_1202);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.FeatureMap"));
    stringBuffer.append(TEXT_1203);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_1204);
    } else {
    stringBuffer.append(TEXT_1205);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_1206);
    }
    } else if (genFeature.isUnsettable()) {
    stringBuffer.append(TEXT_1207);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_1208);
    } else if (!genFeature.hasEDefault()) {
    stringBuffer.append(TEXT_1209);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_1210);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_1211);
    } else {
    stringBuffer.append(TEXT_1212);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_1213);
    stringBuffer.append(genFeature.getEDefault());
    stringBuffer.append(TEXT_1214);
    }
    stringBuffer.append(TEXT_1215);
    }
    stringBuffer.append(TEXT_1216);
    if (genModel.isMinimalReflectiveMethods()) {
    stringBuffer.append(TEXT_1217);
    } else {
    stringBuffer.append(TEXT_1218);
    }
    stringBuffer.append(TEXT_1219);
    //Class/eUnset.override.javajetinc
    }
    if (isImplementation && !genModel.isReflectiveDelegation() && genClass.implementsAny(genClass.getEIsSetGenFeatures())) {
    stringBuffer.append(TEXT_1220);
    if (genModel.useGenerics()) {
    for (GenFeature genFeature : genClass.getEIsSetGenFeatures()) {
    if (genFeature.isListType() && !genFeature.isUnsettable() && !genFeature.isWrappedFeatureMapType() && !genClass.isField(genFeature) && genFeature.isField() && genClass.getImplementingGenModel(genFeature).isVirtualDelegation()) {
    stringBuffer.append(TEXT_1221);
    break; }
    }
    }
    if (genModel.useClassOverrideAnnotation()) {
    stringBuffer.append(TEXT_1222);
    }
    stringBuffer.append(TEXT_1223);
    stringBuffer.append(negativeOffsetCorrection);
    stringBuffer.append(TEXT_1224);
    for (GenFeature genFeature : genClass.getEIsSetGenFeatures()) {
    stringBuffer.append(TEXT_1225);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(TEXT_1226);
    if (genFeature.isListType() && !genFeature.isUnsettable()) {
    if (genFeature.isWrappedFeatureMapType()) {
    if (genFeature.isVolatile()) {
    stringBuffer.append(TEXT_1227);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.FeatureMap"));
    stringBuffer.append(TEXT_1228);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_1229);
    } else {
    stringBuffer.append(TEXT_1230);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_1231);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_1232);
    }
    } else {
    if (genClass.isField(genFeature)) {
    stringBuffer.append(TEXT_1233);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_1234);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_1235);
    } else {
    if (genFeature.isField() && genClass.getImplementingGenModel(genFeature).isVirtualDelegation()) {
    stringBuffer.append(TEXT_1236);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_1237);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_1238);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_1239);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(positiveOffsetCorrection);
    stringBuffer.append(TEXT_1240);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_1241);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_1242);
    } else {
    stringBuffer.append(TEXT_1243);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_1244);
    }
    }
    }
    } else if (genFeature.isUnsettable()) {
    stringBuffer.append(TEXT_1245);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_1246);
    } else if (genFeature.isResolveProxies()) {
    if (genClass.isField(genFeature)) {
    stringBuffer.append(TEXT_1247);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_1248);
    } else {
    if (genFeature.isField() && genClass.getImplementingGenModel(genFeature).isVirtualDelegation()) {
    stringBuffer.append(TEXT_1249);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(positiveOffsetCorrection);
    stringBuffer.append(TEXT_1250);
    } else {
    stringBuffer.append(TEXT_1251);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_1252);
    }
    }
    } else if (!genFeature.hasEDefault()) {
    if (genClass.isField(genFeature)) {
    stringBuffer.append(TEXT_1253);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_1254);
    } else {
    if (genFeature.isField() && genClass.getImplementingGenModel(genFeature).isVirtualDelegation()) {
    stringBuffer.append(TEXT_1255);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(positiveOffsetCorrection);
    stringBuffer.append(TEXT_1256);
    } else {
    stringBuffer.append(TEXT_1257);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_1258);
    }
    }
    } else if (genFeature.isPrimitiveType() || genFeature.isEnumType()) {
    if (genClass.isField(genFeature)) {
    if (genClass.isFlag(genFeature)) {
    stringBuffer.append(TEXT_1259);
    stringBuffer.append(genClass.getFlagsField(genFeature));
    stringBuffer.append(TEXT_1260);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_1261);
    stringBuffer.append(genFeature.getEDefault());
    stringBuffer.append(TEXT_1262);
    } else {
    stringBuffer.append(TEXT_1263);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_1264);
    stringBuffer.append(genFeature.getEDefault());
    stringBuffer.append(TEXT_1265);
    }
    } else {
    if (genFeature.isEnumType() && genFeature.isField() && genClass.getImplementingGenModel(genFeature).isVirtualDelegation()) {
    stringBuffer.append(TEXT_1266);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(positiveOffsetCorrection);
    stringBuffer.append(TEXT_1267);
    stringBuffer.append(genFeature.getEDefault());
    stringBuffer.append(TEXT_1268);
    stringBuffer.append(genFeature.getEDefault());
    stringBuffer.append(TEXT_1269);
    } else {
    stringBuffer.append(TEXT_1270);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_1271);
    stringBuffer.append(genFeature.getEDefault());
    stringBuffer.append(TEXT_1272);
    }
    }
    } else {//datatype
    if (genClass.isField(genFeature)) {
    stringBuffer.append(TEXT_1273);
    stringBuffer.append(genFeature.getEDefault());
    stringBuffer.append(TEXT_1274);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_1275);
    stringBuffer.append(genFeature.getEDefault());
    stringBuffer.append(TEXT_1276);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_1277);
    } else {
    if (genFeature.isField() && genClass.getImplementingGenModel(genFeature).isVirtualDelegation()) {
    stringBuffer.append(TEXT_1278);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_1279);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_1280);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_1281);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(positiveOffsetCorrection);
    stringBuffer.append(TEXT_1282);
    stringBuffer.append(genFeature.getEDefault());
    stringBuffer.append(TEXT_1283);
    stringBuffer.append(genFeature.getEDefault());
    stringBuffer.append(TEXT_1284);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_1285);
    stringBuffer.append(genFeature.getEDefault());
    stringBuffer.append(TEXT_1286);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_1287);
    } else {
    stringBuffer.append(TEXT_1288);
    stringBuffer.append(genFeature.getEDefault());
    stringBuffer.append(TEXT_1289);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_1290);
    stringBuffer.append(genFeature.getEDefault());
    stringBuffer.append(TEXT_1291);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_1292);
    }
    }
    }
    }
    stringBuffer.append(TEXT_1293);
    if (genModel.isMinimalReflectiveMethods()) {
    stringBuffer.append(TEXT_1294);
    } else {
    stringBuffer.append(TEXT_1295);
    }
    stringBuffer.append(TEXT_1296);
    //Class/eIsSet.override.javajetinc
    }
    if (isImplementation && (!genClass.getMixinGenFeatures().isEmpty() || genClass.hasOffsetCorrection() && !genClass.getGenFeatures().isEmpty())) {
    if (!genClass.getMixinGenFeatures().isEmpty()) {
    stringBuffer.append(TEXT_1297);
    if (genModel.useClassOverrideAnnotation()) {
    stringBuffer.append(TEXT_1298);
    }
    stringBuffer.append(TEXT_1299);
    stringBuffer.append(singleWildcard);
    stringBuffer.append(TEXT_1300);
    for (GenClass mixinGenClass : genClass.getMixinGenClasses()) {
    stringBuffer.append(TEXT_1301);
    stringBuffer.append(mixinGenClass.getImportedInterfaceName());
    stringBuffer.append(TEXT_1302);
    stringBuffer.append(negativeOffsetCorrection);
    stringBuffer.append(TEXT_1303);
    for (GenFeature genFeature : mixinGenClass.getGenFeatures()) {
    stringBuffer.append(TEXT_1304);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(TEXT_1305);
    stringBuffer.append(mixinGenClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(TEXT_1306);
    }
    stringBuffer.append(TEXT_1307);
    }
    stringBuffer.append(TEXT_1308);
    }
    stringBuffer.append(TEXT_1309);
    if (genModel.useClassOverrideAnnotation()) {
    stringBuffer.append(TEXT_1310);
    }
    stringBuffer.append(TEXT_1311);
    stringBuffer.append(singleWildcard);
    stringBuffer.append(TEXT_1312);
    for (GenClass mixinGenClass : genClass.getMixinGenClasses()) {
    stringBuffer.append(TEXT_1313);
    stringBuffer.append(mixinGenClass.getImportedInterfaceName());
    stringBuffer.append(TEXT_1314);
    for (GenFeature genFeature : mixinGenClass.getGenFeatures()) {
    stringBuffer.append(TEXT_1315);
    stringBuffer.append(mixinGenClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(TEXT_1316);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(positiveOffsetCorrection);
    stringBuffer.append(TEXT_1317);
    }
    stringBuffer.append(TEXT_1318);
    }
    if (genClass.hasOffsetCorrection() && !genClass.getGenFeatures().isEmpty()) {
    stringBuffer.append(TEXT_1319);
    stringBuffer.append(genClass.getImportedInterfaceName());
    stringBuffer.append(TEXT_1320);
    stringBuffer.append(negativeOffsetCorrection);
    stringBuffer.append(TEXT_1321);
    for (GenFeature genFeature : genClass.getGenFeatures()) {
    stringBuffer.append(TEXT_1322);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(TEXT_1323);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(positiveOffsetCorrection);
    stringBuffer.append(TEXT_1324);
    }
    stringBuffer.append(TEXT_1325);
    }
    stringBuffer.append(TEXT_1326);
    }
    if (isImplementation && genModel.isVirtualDelegation()) { String eVirtualValuesField = genClass.getEVirtualValuesField();
    if (eVirtualValuesField != null) {
    stringBuffer.append(TEXT_1327);
    if (genModel.useClassOverrideAnnotation()) {
    stringBuffer.append(TEXT_1328);
    }
    stringBuffer.append(TEXT_1329);
    stringBuffer.append(eVirtualValuesField);
    stringBuffer.append(TEXT_1330);
    if (genModel.useClassOverrideAnnotation()) {
    stringBuffer.append(TEXT_1331);
    }
    stringBuffer.append(TEXT_1332);
    stringBuffer.append(eVirtualValuesField);
    stringBuffer.append(TEXT_1333);
    }
    { List<String> eVirtualIndexBitFields = genClass.getEVirtualIndexBitFields(new ArrayList<String>());
    if (!eVirtualIndexBitFields.isEmpty()) { List<String> allEVirtualIndexBitFields = genClass.getAllEVirtualIndexBitFields(new ArrayList<String>());
    stringBuffer.append(TEXT_1334);
    if (genModel.useClassOverrideAnnotation()) {
    stringBuffer.append(TEXT_1335);
    }
    stringBuffer.append(TEXT_1336);
    for (int i = 0; i < allEVirtualIndexBitFields.size(); i++) {
    stringBuffer.append(TEXT_1337);
    stringBuffer.append(i);
    stringBuffer.append(TEXT_1338);
    stringBuffer.append(allEVirtualIndexBitFields.get(i));
    stringBuffer.append(TEXT_1339);
    }
    stringBuffer.append(TEXT_1340);
    if (genModel.useClassOverrideAnnotation()) {
    stringBuffer.append(TEXT_1341);
    }
    stringBuffer.append(TEXT_1342);
    for (int i = 0; i < allEVirtualIndexBitFields.size(); i++) {
    stringBuffer.append(TEXT_1343);
    stringBuffer.append(i);
    stringBuffer.append(TEXT_1344);
    stringBuffer.append(allEVirtualIndexBitFields.get(i));
    stringBuffer.append(TEXT_1345);
    }
    stringBuffer.append(TEXT_1346);
    }
    }
    }
    if (isImplementation && !genModel.isReflectiveDelegation() && !genClass.getToStringGenFeatures().isEmpty()) {
    stringBuffer.append(TEXT_1347);
    if (genModel.useClassOverrideAnnotation()) {
    stringBuffer.append(TEXT_1348);
    }
    stringBuffer.append(TEXT_1349);
    { boolean first = true;
    for (GenFeature genFeature : genClass.getToStringGenFeatures()) {
    if (first) { first = false;
    stringBuffer.append(TEXT_1350);
    stringBuffer.append(genFeature.getName());
    stringBuffer.append(TEXT_1351);
    stringBuffer.append(genModel.getNonNLS());
    } else {
    stringBuffer.append(TEXT_1352);
    stringBuffer.append(genFeature.getName());
    stringBuffer.append(TEXT_1353);
    stringBuffer.append(genModel.getNonNLS());
    }
    if (genFeature.isUnsettable() && !genFeature.isListType()) {
    if (genModel.isVirtualDelegation() && !genFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_1354);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(positiveOffsetCorrection);
    stringBuffer.append(TEXT_1355);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(positiveOffsetCorrection);
    stringBuffer.append(TEXT_1356);
    stringBuffer.append(genModel.getNonNLS());
    } else {
    if (genClass.isFlag(genFeature)) {
    stringBuffer.append(TEXT_1357);
    if (genClass.isESetFlag(genFeature)) {
    stringBuffer.append(TEXT_1358);
    stringBuffer.append(genClass.getESetFlagsField(genFeature));
    stringBuffer.append(TEXT_1359);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_1360);
    } else {
    stringBuffer.append(genFeature.getUncapName());
    stringBuffer.append(TEXT_1361);
    }
    stringBuffer.append(TEXT_1362);
    stringBuffer.append(genClass.getFlagsField(genFeature));
    stringBuffer.append(TEXT_1363);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_1364);
    stringBuffer.append(genModel.getNonNLS());
    } else {
    stringBuffer.append(TEXT_1365);
    if (genClass.isESetFlag(genFeature)) {
    stringBuffer.append(TEXT_1366);
    stringBuffer.append(genClass.getESetFlagsField(genFeature));
    stringBuffer.append(TEXT_1367);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_1368);
    } else {
    stringBuffer.append(genFeature.getUncapName());
    stringBuffer.append(TEXT_1369);
    }
    stringBuffer.append(TEXT_1370);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_1371);
    stringBuffer.append(genModel.getNonNLS());
    }
    }
    } else {
    if (genModel.isVirtualDelegation() && !genFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_1372);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(positiveOffsetCorrection);
    if (!genFeature.isListType() && !genFeature.isReferenceType()){
    stringBuffer.append(TEXT_1373);
    stringBuffer.append(genFeature.getEDefault());
    }
    stringBuffer.append(TEXT_1374);
    } else {
    if (genClass.isFlag(genFeature)) {
    stringBuffer.append(TEXT_1375);
    stringBuffer.append(genClass.getFlagsField(genFeature));
    stringBuffer.append(TEXT_1376);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_1377);
    } else {
    stringBuffer.append(TEXT_1378);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_1379);
    }
    }
    }
    }
    }
    stringBuffer.append(TEXT_1380);
    }
    if (isImplementation && genClass.isMapEntry()) { GenFeature keyFeature = genClass.getMapEntryKeyFeature(); GenFeature valueFeature = genClass.getMapEntryValueFeature();
    String objectType = genModel.getImportedName("java.lang.Object");
    String keyType = genModel.getComplianceLevel().getValue() >= GenJDKLevel.JDK50 ? keyFeature.getObjectType(genClass) : objectType;
    String valueType = genModel.getComplianceLevel().getValue() >= GenJDKLevel.JDK50 ? valueFeature.getObjectType(genClass) : objectType;
    String eMapType = genModel.getImportedName("org.eclipse.emf.common.util.EMap") + (genModel.getComplianceLevel().getValue() >= GenJDKLevel.JDK50 ? "<" + keyType + ", " + valueType + ">" : "");
    stringBuffer.append(TEXT_1381);
    stringBuffer.append(objectType);
    stringBuffer.append(TEXT_1382);
    stringBuffer.append(keyType);
    stringBuffer.append(TEXT_1383);
    if (keyFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_1384);
    stringBuffer.append(keyFeature.getObjectType(genClass));
    stringBuffer.append(TEXT_1385);
    } else {
    stringBuffer.append(TEXT_1386);
    }
    stringBuffer.append(TEXT_1387);
    stringBuffer.append(keyType);
    stringBuffer.append(TEXT_1388);
    if (keyFeature.isListType()) {
    stringBuffer.append(TEXT_1389);
    if (!genModel.useGenerics()) {
    stringBuffer.append(TEXT_1390);
    stringBuffer.append(genModel.getImportedName("java.util.Collection"));
    stringBuffer.append(TEXT_1391);
    }
    stringBuffer.append(TEXT_1392);
    } else if (genModel.getComplianceLevel().getValue() >= GenJDKLevel.JDK50) {
    stringBuffer.append(TEXT_1393);
    } else if (keyFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_1394);
    stringBuffer.append(keyFeature.getObjectType(genClass));
    stringBuffer.append(TEXT_1395);
    stringBuffer.append(keyFeature.getPrimitiveValueFunction());
    stringBuffer.append(TEXT_1396);
    } else {
    stringBuffer.append(TEXT_1397);
    stringBuffer.append(keyFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_1398);
    }
    stringBuffer.append(TEXT_1399);
    stringBuffer.append(valueType);
    stringBuffer.append(TEXT_1400);
    if (valueFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_1401);
    stringBuffer.append(valueFeature.getObjectType(genClass));
    stringBuffer.append(TEXT_1402);
    } else {
    stringBuffer.append(TEXT_1403);
    }
    stringBuffer.append(TEXT_1404);
    stringBuffer.append(valueType);
    stringBuffer.append(TEXT_1405);
    stringBuffer.append(valueType);
    stringBuffer.append(TEXT_1406);
    stringBuffer.append(valueType);
    stringBuffer.append(TEXT_1407);
    if (valueFeature.isListType()) {
    stringBuffer.append(TEXT_1408);
    if (!genModel.useGenerics()) {
    stringBuffer.append(TEXT_1409);
    stringBuffer.append(genModel.getImportedName("java.util.Collection"));
    stringBuffer.append(TEXT_1410);
    }
    stringBuffer.append(TEXT_1411);
    } else if (genModel.getComplianceLevel().getValue() >= GenJDKLevel.JDK50) {
    stringBuffer.append(TEXT_1412);
    } else if (valueFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_1413);
    stringBuffer.append(valueFeature.getObjectType(genClass));
    stringBuffer.append(TEXT_1414);
    stringBuffer.append(valueFeature.getPrimitiveValueFunction());
    stringBuffer.append(TEXT_1415);
    } else {
    stringBuffer.append(TEXT_1416);
    stringBuffer.append(valueFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_1417);
    }
    stringBuffer.append(TEXT_1418);
    if (genModel.useGenerics()) {
    stringBuffer.append(TEXT_1419);
    }
    stringBuffer.append(TEXT_1420);
    stringBuffer.append(eMapType);
    stringBuffer.append(TEXT_1421);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.EObject"));
    stringBuffer.append(TEXT_1422);
    stringBuffer.append(eMapType);
    stringBuffer.append(TEXT_1423);
    }
    stringBuffer.append(TEXT_1424);
    stringBuffer.append(isInterface ? " " + genClass.getInterfaceName() : genClass.getClassName());
    // TODO fix the space above
    genModel.emitSortedImports();
    stringBuffer.append(TEXT_1425);
    return stringBuffer.toString();
  }
}
