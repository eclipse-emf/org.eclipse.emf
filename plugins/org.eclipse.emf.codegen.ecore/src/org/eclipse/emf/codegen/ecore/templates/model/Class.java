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
  protected final String TEXT_95 = NL + "\t/**" + NL + "\t * The offset of the flags representing the value of the '{@link #";
  protected final String TEXT_96 = "() <em>";
  protected final String TEXT_97 = "</em>}' ";
  protected final String TEXT_98 = "." + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t * @ordered" + NL + "\t */" + NL + "\tprotected static final int ";
  protected final String TEXT_99 = "_EFLAG_OFFSET = ";
  protected final String TEXT_100 = ";" + NL + "" + NL + "\t/**" + NL + "\t * The flags representing the default value of the '{@link #";
  protected final String TEXT_101 = "() <em>";
  protected final String TEXT_102 = "</em>}' ";
  protected final String TEXT_103 = "." + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t * @ordered" + NL + "\t */" + NL + "\tprotected static final int ";
  protected final String TEXT_104 = "_EFLAG_DEFAULT = ";
  protected final String TEXT_105 = ".ordinal()";
  protected final String TEXT_106 = ".VALUES.indexOf(";
  protected final String TEXT_107 = ")";
  protected final String TEXT_108 = " << ";
  protected final String TEXT_109 = "_EFLAG_OFFSET;" + NL + "" + NL + "\t/**" + NL + "\t * The array of enumeration values for '{@link ";
  protected final String TEXT_110 = " ";
  protected final String TEXT_111 = "}'" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t * @ordered" + NL + "\t */" + NL + "\tprivate static final ";
  protected final String TEXT_112 = "[] ";
  protected final String TEXT_113 = "_EFLAG_VALUES = ";
  protected final String TEXT_114 = ".values()";
  protected final String TEXT_115 = "(";
  protected final String TEXT_116 = "[])";
  protected final String TEXT_117 = ".VALUES.toArray(new ";
  protected final String TEXT_118 = "[";
  protected final String TEXT_119 = ".VALUES.size()])";
  protected final String TEXT_120 = ";" + NL;
  protected final String TEXT_121 = NL + "\t/**" + NL + "\t * The flag";
  protected final String TEXT_122 = " representing the value of the '{@link #";
  protected final String TEXT_123 = "() <em>";
  protected final String TEXT_124 = "</em>}' ";
  protected final String TEXT_125 = "." + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @see #";
  protected final String TEXT_126 = "()" + NL + "\t * @generated" + NL + "\t * @ordered" + NL + "\t */" + NL + "\tprotected static final int ";
  protected final String TEXT_127 = "_EFLAG = ";
  protected final String TEXT_128 = " << ";
  protected final String TEXT_129 = "_EFLAG_OFFSET";
  protected final String TEXT_130 = ";" + NL;
  protected final String TEXT_131 = NL + "\t/**" + NL + "\t * The cached value of the '{@link #";
  protected final String TEXT_132 = "() <em>";
  protected final String TEXT_133 = "</em>}' ";
  protected final String TEXT_134 = "." + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @see #";
  protected final String TEXT_135 = "()" + NL + "\t * @generated" + NL + "\t * @ordered" + NL + "\t */" + NL + "\tprotected ";
  protected final String TEXT_136 = " ";
  protected final String TEXT_137 = " = ";
  protected final String TEXT_138 = ";" + NL;
  protected final String TEXT_139 = NL + "\t/**" + NL + "\t * An additional set of bit flags representing the values of boolean attributes and whether unsettable features have been set." + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t * @ordered" + NL + "\t */" + NL + "\tprotected int ";
  protected final String TEXT_140 = " = 0;" + NL;
  protected final String TEXT_141 = NL + "\t/**" + NL + "\t * The flag representing whether the ";
  protected final String TEXT_142 = " ";
  protected final String TEXT_143 = " has been set." + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t * @ordered" + NL + "\t */" + NL + "\tprotected static final int ";
  protected final String TEXT_144 = "_ESETFLAG = 1 << ";
  protected final String TEXT_145 = ";" + NL;
  protected final String TEXT_146 = NL + "\t/**" + NL + "\t * This is true if the ";
  protected final String TEXT_147 = " ";
  protected final String TEXT_148 = " has been set." + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t * @ordered" + NL + "\t */" + NL + "\tprotected boolean ";
  protected final String TEXT_149 = "ESet;" + NL;
  protected final String TEXT_150 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */" + NL + "\tprivate static final int ";
  protected final String TEXT_151 = " = ";
  protected final String TEXT_152 = ".getFeatureID(";
  protected final String TEXT_153 = ") - ";
  protected final String TEXT_154 = ";" + NL;
  protected final String TEXT_155 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */" + NL + "\tprivate static final int ";
  protected final String TEXT_156 = " = ";
  protected final String TEXT_157 = ".getFeatureID(";
  protected final String TEXT_158 = ") - ";
  protected final String TEXT_159 = ";" + NL;
  protected final String TEXT_160 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */" + NL + "\t";
  protected final String TEXT_161 = "public";
  protected final String TEXT_162 = "protected";
  protected final String TEXT_163 = " ";
  protected final String TEXT_164 = "()" + NL + "\t{" + NL + "\t\tsuper();";
  protected final String TEXT_165 = NL + "\t\t";
  protected final String TEXT_166 = " |= ";
  protected final String TEXT_167 = "_EFLAG";
  protected final String TEXT_168 = "_DEFAULT";
  protected final String TEXT_169 = ";";
  protected final String TEXT_170 = NL + "\t}" + NL + "" + NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */";
  protected final String TEXT_171 = NL + "\t@Override";
  protected final String TEXT_172 = NL + "\tprotected ";
  protected final String TEXT_173 = " eStaticClass()" + NL + "\t{" + NL + "\t\treturn ";
  protected final String TEXT_174 = ";" + NL + "\t}" + NL;
  protected final String TEXT_175 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */";
  protected final String TEXT_176 = NL + "\t@Override";
  protected final String TEXT_177 = NL + "\tprotected int eStaticFeatureCount()" + NL + "\t{" + NL + "\t\treturn ";
  protected final String TEXT_178 = ";" + NL + "\t}" + NL;
  protected final String TEXT_179 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */";
  protected final String TEXT_180 = NL + "\t";
  protected final String TEXT_181 = "[] ";
  protected final String TEXT_182 = "();" + NL;
  protected final String TEXT_183 = NL + "\t@SuppressWarnings(\"unchecked\")";
  protected final String TEXT_184 = NL + "\tpublic ";
  protected final String TEXT_185 = "[] ";
  protected final String TEXT_186 = "()" + NL + "\t{";
  protected final String TEXT_187 = NL + "\t\t";
  protected final String TEXT_188 = " list = (";
  protected final String TEXT_189 = ")";
  protected final String TEXT_190 = "();" + NL + "\t\tif (list.isEmpty()) return ";
  protected final String TEXT_191 = "(";
  protected final String TEXT_192 = "[])";
  protected final String TEXT_193 = "_EEMPTY_ARRAY;";
  protected final String TEXT_194 = NL + "\t\tif (";
  protected final String TEXT_195 = " == null || ";
  protected final String TEXT_196 = ".isEmpty()) return ";
  protected final String TEXT_197 = "(";
  protected final String TEXT_198 = "[])";
  protected final String TEXT_199 = "_EEMPTY_ARRAY;" + NL + "\t\t";
  protected final String TEXT_200 = " list = (";
  protected final String TEXT_201 = ")";
  protected final String TEXT_202 = ";";
  protected final String TEXT_203 = NL + "\t\tlist.shrink();" + NL + "\t\treturn (";
  protected final String TEXT_204 = "[])list.data();" + NL + "\t}" + NL;
  protected final String TEXT_205 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */";
  protected final String TEXT_206 = NL + "\t";
  protected final String TEXT_207 = " get";
  protected final String TEXT_208 = "(int index);" + NL;
  protected final String TEXT_209 = NL + "\tpublic ";
  protected final String TEXT_210 = " get";
  protected final String TEXT_211 = "(int index)" + NL + "\t{" + NL + "\t\treturn ";
  protected final String TEXT_212 = "(";
  protected final String TEXT_213 = ")";
  protected final String TEXT_214 = "().get(index);" + NL + "\t}" + NL;
  protected final String TEXT_215 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */";
  protected final String TEXT_216 = NL + "\tint get";
  protected final String TEXT_217 = "Length();" + NL;
  protected final String TEXT_218 = NL + "\tpublic int get";
  protected final String TEXT_219 = "Length()" + NL + "\t{";
  protected final String TEXT_220 = NL + "\t\treturn ";
  protected final String TEXT_221 = "().size();";
  protected final String TEXT_222 = NL + "\t\treturn ";
  protected final String TEXT_223 = " == null ? 0 : ";
  protected final String TEXT_224 = ".size();";
  protected final String TEXT_225 = NL + "\t}" + NL;
  protected final String TEXT_226 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */";
  protected final String TEXT_227 = NL + "\tvoid set";
  protected final String TEXT_228 = "(";
  protected final String TEXT_229 = "[] new";
  protected final String TEXT_230 = ");" + NL;
  protected final String TEXT_231 = NL + "\tpublic void set";
  protected final String TEXT_232 = "(";
  protected final String TEXT_233 = "[] new";
  protected final String TEXT_234 = ")" + NL + "\t{" + NL + "\t\t((";
  protected final String TEXT_235 = ")";
  protected final String TEXT_236 = "()).setData(new";
  protected final String TEXT_237 = ".length, new";
  protected final String TEXT_238 = ");" + NL + "\t}" + NL;
  protected final String TEXT_239 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */";
  protected final String TEXT_240 = NL + "\tvoid set";
  protected final String TEXT_241 = "(int index, ";
  protected final String TEXT_242 = " element);" + NL;
  protected final String TEXT_243 = NL + "\tpublic void set";
  protected final String TEXT_244 = "(int index, ";
  protected final String TEXT_245 = " element)" + NL + "\t{" + NL + "\t\t";
  protected final String TEXT_246 = "().set(index, element);" + NL + "\t}" + NL;
  protected final String TEXT_247 = NL + "\t/**" + NL + "\t * Returns the value of the '<em><b>";
  protected final String TEXT_248 = "</b></em>' ";
  protected final String TEXT_249 = ".";
  protected final String TEXT_250 = NL + "\t * The key is of type ";
  protected final String TEXT_251 = "list of {@link ";
  protected final String TEXT_252 = "}";
  protected final String TEXT_253 = "{@link ";
  protected final String TEXT_254 = "}";
  protected final String TEXT_255 = "," + NL + "\t * and the value is of type ";
  protected final String TEXT_256 = "list of {@link ";
  protected final String TEXT_257 = "}";
  protected final String TEXT_258 = "{@link ";
  protected final String TEXT_259 = "}";
  protected final String TEXT_260 = ",";
  protected final String TEXT_261 = NL + "\t * The list contents are of type {@link ";
  protected final String TEXT_262 = "}";
  protected final String TEXT_263 = ".";
  protected final String TEXT_264 = NL + "\t * The default value is <code>";
  protected final String TEXT_265 = "</code>.";
  protected final String TEXT_266 = NL + "\t * The literals are from the enumeration {@link ";
  protected final String TEXT_267 = "}.";
  protected final String TEXT_268 = NL + "\t * It is bidirectional and its opposite is '{@link ";
  protected final String TEXT_269 = "#";
  protected final String TEXT_270 = " <em>";
  protected final String TEXT_271 = "</em>}'.";
  protected final String TEXT_272 = NL + "\t * <!-- begin-user-doc -->";
  protected final String TEXT_273 = NL + "\t * <p>" + NL + "\t * If the meaning of the '<em>";
  protected final String TEXT_274 = "</em>' ";
  protected final String TEXT_275 = " isn't clear," + NL + "\t * there really should be more of a description here..." + NL + "\t * </p>";
  protected final String TEXT_276 = NL + "\t * <!-- end-user-doc -->";
  protected final String TEXT_277 = NL + "\t * <!-- begin-model-doc -->" + NL + "\t * ";
  protected final String TEXT_278 = NL + "\t * <!-- end-model-doc -->";
  protected final String TEXT_279 = NL + "\t * @return the value of the '<em>";
  protected final String TEXT_280 = "</em>' ";
  protected final String TEXT_281 = ".";
  protected final String TEXT_282 = NL + "\t * @see ";
  protected final String TEXT_283 = NL + "\t * @see #isSet";
  protected final String TEXT_284 = "()";
  protected final String TEXT_285 = NL + "\t * @see #unset";
  protected final String TEXT_286 = "()";
  protected final String TEXT_287 = NL + "\t * @see #set";
  protected final String TEXT_288 = "(";
  protected final String TEXT_289 = ")";
  protected final String TEXT_290 = NL + "\t * @see ";
  protected final String TEXT_291 = "#get";
  protected final String TEXT_292 = "()";
  protected final String TEXT_293 = NL + "\t * @see ";
  protected final String TEXT_294 = "#";
  protected final String TEXT_295 = NL + "\t * @model ";
  protected final String TEXT_296 = NL + "\t *        ";
  protected final String TEXT_297 = NL + "\t * @model";
  protected final String TEXT_298 = NL + "\t * @generated" + NL + "\t */";
  protected final String TEXT_299 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */";
  protected final String TEXT_300 = NL + "\t";
  protected final String TEXT_301 = " ";
  protected final String TEXT_302 = "();" + NL;
  protected final String TEXT_303 = NL + "\t@SuppressWarnings(\"unchecked\")";
  protected final String TEXT_304 = NL + "\tpublic ";
  protected final String TEXT_305 = " ";
  protected final String TEXT_306 = "_";
  protected final String TEXT_307 = "()" + NL + "\t{";
  protected final String TEXT_308 = NL + "\t\treturn ";
  protected final String TEXT_309 = "(";
  protected final String TEXT_310 = "(";
  protected final String TEXT_311 = ")eGet(";
  protected final String TEXT_312 = ", true)";
  protected final String TEXT_313 = ").";
  protected final String TEXT_314 = "()";
  protected final String TEXT_315 = ";";
  protected final String TEXT_316 = NL + "\t\t";
  protected final String TEXT_317 = " ";
  protected final String TEXT_318 = " = (";
  protected final String TEXT_319 = ")eVirtualGet(";
  protected final String TEXT_320 = ");";
  protected final String TEXT_321 = NL + "\t\tif (";
  protected final String TEXT_322 = " == null)" + NL + "\t\t{";
  protected final String TEXT_323 = NL + "\t\t\teVirtualSet(";
  protected final String TEXT_324 = ", ";
  protected final String TEXT_325 = " = new ";
  protected final String TEXT_326 = ");";
  protected final String TEXT_327 = NL + "\t\t\t";
  protected final String TEXT_328 = " = new ";
  protected final String TEXT_329 = ";";
  protected final String TEXT_330 = NL + "\t\t}" + NL + "\t\treturn ";
  protected final String TEXT_331 = ";";
  protected final String TEXT_332 = NL + "\t\tif (eContainerFeatureID != ";
  protected final String TEXT_333 = ") return null;" + NL + "\t\treturn (";
  protected final String TEXT_334 = ")eContainer();";
  protected final String TEXT_335 = NL + "\t\t";
  protected final String TEXT_336 = " ";
  protected final String TEXT_337 = " = (";
  protected final String TEXT_338 = ")eVirtualGet(";
  protected final String TEXT_339 = ", ";
  protected final String TEXT_340 = ");";
  protected final String TEXT_341 = NL + "\t\tif (";
  protected final String TEXT_342 = " != null && ";
  protected final String TEXT_343 = ".eIsProxy())" + NL + "\t\t{" + NL + "\t\t\t";
  protected final String TEXT_344 = " old";
  protected final String TEXT_345 = " = (";
  protected final String TEXT_346 = ")";
  protected final String TEXT_347 = ";" + NL + "\t\t\t";
  protected final String TEXT_348 = " = ";
  protected final String TEXT_349 = "eResolveProxy(old";
  protected final String TEXT_350 = ");" + NL + "\t\t\tif (";
  protected final String TEXT_351 = " != old";
  protected final String TEXT_352 = ")" + NL + "\t\t\t{";
  protected final String TEXT_353 = NL + "\t\t\t\t";
  protected final String TEXT_354 = " new";
  protected final String TEXT_355 = " = (";
  protected final String TEXT_356 = ")";
  protected final String TEXT_357 = ";";
  protected final String TEXT_358 = NL + "\t\t\t\t";
  protected final String TEXT_359 = " msgs = old";
  protected final String TEXT_360 = ".eInverseRemove(this, EOPPOSITE_FEATURE_BASE - ";
  protected final String TEXT_361 = ", null, null);";
  protected final String TEXT_362 = NL + "\t\t\t\t";
  protected final String TEXT_363 = " msgs =  old";
  protected final String TEXT_364 = ".eInverseRemove(this, ";
  protected final String TEXT_365 = ", ";
  protected final String TEXT_366 = ".class, null);";
  protected final String TEXT_367 = NL + "\t\t\t\tif (new";
  protected final String TEXT_368 = ".eInternalContainer() == null)" + NL + "\t\t\t\t{";
  protected final String TEXT_369 = NL + "\t\t\t\t\tmsgs = new";
  protected final String TEXT_370 = ".eInverseAdd(this, EOPPOSITE_FEATURE_BASE - ";
  protected final String TEXT_371 = ", null, msgs);";
  protected final String TEXT_372 = NL + "\t\t\t\t\tmsgs =  new";
  protected final String TEXT_373 = ".eInverseAdd(this, ";
  protected final String TEXT_374 = ", ";
  protected final String TEXT_375 = ".class, msgs);";
  protected final String TEXT_376 = NL + "\t\t\t\t}" + NL + "\t\t\t\tif (msgs != null) msgs.dispatch();";
  protected final String TEXT_377 = NL + "\t\t\t\teVirtualSet(";
  protected final String TEXT_378 = ", ";
  protected final String TEXT_379 = ");";
  protected final String TEXT_380 = NL + "\t\t\t\tif (eNotificationRequired())" + NL + "\t\t\t\t\teNotify(new ";
  protected final String TEXT_381 = "(this, ";
  protected final String TEXT_382 = ".RESOLVE, ";
  protected final String TEXT_383 = ", old";
  protected final String TEXT_384 = ", ";
  protected final String TEXT_385 = "));";
  protected final String TEXT_386 = NL + "\t\t\t}" + NL + "\t\t}";
  protected final String TEXT_387 = NL + "\t\treturn (";
  protected final String TEXT_388 = ")eVirtualGet(";
  protected final String TEXT_389 = ", ";
  protected final String TEXT_390 = ");";
  protected final String TEXT_391 = NL + "\t\treturn (";
  protected final String TEXT_392 = " & ";
  protected final String TEXT_393 = "_EFLAG) != 0;";
  protected final String TEXT_394 = NL + "\t\treturn ";
  protected final String TEXT_395 = "_EFLAG_VALUES[(";
  protected final String TEXT_396 = " & ";
  protected final String TEXT_397 = "_EFLAG) >>> ";
  protected final String TEXT_398 = "_EFLAG_OFFSET];";
  protected final String TEXT_399 = NL + "\t\treturn ";
  protected final String TEXT_400 = ";";
  protected final String TEXT_401 = NL + "\t\t";
  protected final String TEXT_402 = " ";
  protected final String TEXT_403 = " = basicGet";
  protected final String TEXT_404 = "();" + NL + "\t\treturn ";
  protected final String TEXT_405 = " != null && ";
  protected final String TEXT_406 = ".eIsProxy() ? ";
  protected final String TEXT_407 = "eResolveProxy((";
  protected final String TEXT_408 = ")";
  protected final String TEXT_409 = ") : ";
  protected final String TEXT_410 = ";";
  protected final String TEXT_411 = NL + "\t\treturn new ";
  protected final String TEXT_412 = "((";
  protected final String TEXT_413 = ".Internal)((";
  protected final String TEXT_414 = ".Internal.Wrapper)get";
  protected final String TEXT_415 = "()).featureMap().";
  protected final String TEXT_416 = "list(";
  protected final String TEXT_417 = "));";
  protected final String TEXT_418 = NL + "\t\treturn (";
  protected final String TEXT_419 = ")get";
  protected final String TEXT_420 = "().";
  protected final String TEXT_421 = "list(";
  protected final String TEXT_422 = ");";
  protected final String TEXT_423 = NL + "\t\treturn ((";
  protected final String TEXT_424 = ".Internal.Wrapper)get";
  protected final String TEXT_425 = "()).featureMap().list(";
  protected final String TEXT_426 = ");";
  protected final String TEXT_427 = NL + "\t\treturn get";
  protected final String TEXT_428 = "().list(";
  protected final String TEXT_429 = ");";
  protected final String TEXT_430 = NL + "\t\treturn ";
  protected final String TEXT_431 = "(";
  protected final String TEXT_432 = "(";
  protected final String TEXT_433 = ")";
  protected final String TEXT_434 = "((";
  protected final String TEXT_435 = ".Internal.Wrapper)get";
  protected final String TEXT_436 = "()).featureMap().get(";
  protected final String TEXT_437 = ", true)";
  protected final String TEXT_438 = ").";
  protected final String TEXT_439 = "()";
  protected final String TEXT_440 = ";";
  protected final String TEXT_441 = NL + "\t\treturn ";
  protected final String TEXT_442 = "(";
  protected final String TEXT_443 = "(";
  protected final String TEXT_444 = ")";
  protected final String TEXT_445 = "get";
  protected final String TEXT_446 = "().get(";
  protected final String TEXT_447 = ", true)";
  protected final String TEXT_448 = ").";
  protected final String TEXT_449 = "()";
  protected final String TEXT_450 = ";";
  protected final String TEXT_451 = NL + "\t\t";
  protected final String TEXT_452 = NL + "\t\t// TODO: implement this method to return the '";
  protected final String TEXT_453 = "' ";
  protected final String TEXT_454 = NL + "\t\t// Ensure that you remove @generated or mark it @generated NOT";
  protected final String TEXT_455 = NL + "\t\t// The list is expected to implement org.eclipse.emf.ecore.util.InternalEList and org.eclipse.emf.ecore.EStructuralFeature.Setting" + NL + "\t\t// so it's likely that an appropriate subclass of org.eclipse.emf.ecore.util.";
  protected final String TEXT_456 = "EcoreEMap";
  protected final String TEXT_457 = "BasicFeatureMap";
  protected final String TEXT_458 = "EcoreEList";
  protected final String TEXT_459 = " should be used.";
  protected final String TEXT_460 = NL + "\t\tthrow new UnsupportedOperationException();";
  protected final String TEXT_461 = NL + "\t}" + NL;
  protected final String TEXT_462 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */";
  protected final String TEXT_463 = NL + "\tpublic ";
  protected final String TEXT_464 = " basicGet";
  protected final String TEXT_465 = "()" + NL + "\t{";
  protected final String TEXT_466 = NL + "\t\tif (eContainerFeatureID != ";
  protected final String TEXT_467 = ") return null;" + NL + "\t\treturn (";
  protected final String TEXT_468 = ")eInternalContainer();";
  protected final String TEXT_469 = NL + "\t\treturn (";
  protected final String TEXT_470 = ")eVirtualGet(";
  protected final String TEXT_471 = ");";
  protected final String TEXT_472 = NL + "\t\treturn ";
  protected final String TEXT_473 = ";";
  protected final String TEXT_474 = NL + "\t\treturn (";
  protected final String TEXT_475 = ")((";
  protected final String TEXT_476 = ".Internal.Wrapper)get";
  protected final String TEXT_477 = "()).featureMap().get(";
  protected final String TEXT_478 = ", false);";
  protected final String TEXT_479 = NL + "\t\treturn (";
  protected final String TEXT_480 = ")get";
  protected final String TEXT_481 = "().get(";
  protected final String TEXT_482 = ", false);";
  protected final String TEXT_483 = NL + "\t\t// TODO: implement this method to return the '";
  protected final String TEXT_484 = "' ";
  protected final String TEXT_485 = NL + "\t\t// -> do not perform proxy resolution" + NL + "\t\t// Ensure that you remove @generated or mark it @generated NOT" + NL + "\t\tthrow new UnsupportedOperationException();";
  protected final String TEXT_486 = NL + "\t}" + NL;
  protected final String TEXT_487 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */";
  protected final String TEXT_488 = NL + "\tpublic ";
  protected final String TEXT_489 = " basicSet";
  protected final String TEXT_490 = "(";
  protected final String TEXT_491 = " new";
  protected final String TEXT_492 = ", ";
  protected final String TEXT_493 = " msgs)" + NL + "\t{";
  protected final String TEXT_494 = NL + "\t\tmsgs = eBasicSetContainer((";
  protected final String TEXT_495 = ")new";
  protected final String TEXT_496 = ", ";
  protected final String TEXT_497 = ", msgs);";
  protected final String TEXT_498 = NL + "\t\treturn msgs;";
  protected final String TEXT_499 = NL + "\t\tObject old";
  protected final String TEXT_500 = " = eVirtualSet(";
  protected final String TEXT_501 = ", new";
  protected final String TEXT_502 = ");";
  protected final String TEXT_503 = NL + "\t\t";
  protected final String TEXT_504 = " old";
  protected final String TEXT_505 = " = ";
  protected final String TEXT_506 = ";" + NL + "\t\t";
  protected final String TEXT_507 = " = new";
  protected final String TEXT_508 = ";";
  protected final String TEXT_509 = NL + "\t\tboolean isSetChange = old";
  protected final String TEXT_510 = " == EVIRTUAL_NO_VALUE;";
  protected final String TEXT_511 = NL + "\t\tboolean old";
  protected final String TEXT_512 = "ESet = (";
  protected final String TEXT_513 = " & ";
  protected final String TEXT_514 = "_ESETFLAG) != 0;";
  protected final String TEXT_515 = NL + "\t\t";
  protected final String TEXT_516 = " |= ";
  protected final String TEXT_517 = "_ESETFLAG;";
  protected final String TEXT_518 = NL + "\t\tboolean old";
  protected final String TEXT_519 = "ESet = ";
  protected final String TEXT_520 = "ESet;";
  protected final String TEXT_521 = NL + "\t\t";
  protected final String TEXT_522 = "ESet = true;";
  protected final String TEXT_523 = NL + "\t\tif (eNotificationRequired())" + NL + "\t\t{";
  protected final String TEXT_524 = NL + "\t\t\t";
  protected final String TEXT_525 = " notification = new ";
  protected final String TEXT_526 = "(this, ";
  protected final String TEXT_527 = ".SET, ";
  protected final String TEXT_528 = ", ";
  protected final String TEXT_529 = "isSetChange ? null : old";
  protected final String TEXT_530 = "old";
  protected final String TEXT_531 = ", new";
  protected final String TEXT_532 = ", ";
  protected final String TEXT_533 = "isSetChange";
  protected final String TEXT_534 = "!old";
  protected final String TEXT_535 = "ESet";
  protected final String TEXT_536 = ");";
  protected final String TEXT_537 = NL + "\t\t\t";
  protected final String TEXT_538 = " notification = new ";
  protected final String TEXT_539 = "(this, ";
  protected final String TEXT_540 = ".SET, ";
  protected final String TEXT_541 = ", ";
  protected final String TEXT_542 = "old";
  protected final String TEXT_543 = " == EVIRTUAL_NO_VALUE ? null : old";
  protected final String TEXT_544 = "old";
  protected final String TEXT_545 = ", new";
  protected final String TEXT_546 = ");";
  protected final String TEXT_547 = NL + "\t\t\tif (msgs == null) msgs = notification; else msgs.add(notification);" + NL + "\t\t}";
  protected final String TEXT_548 = NL + "\t\treturn msgs;";
  protected final String TEXT_549 = NL + "\t\treturn ((";
  protected final String TEXT_550 = ".Internal)((";
  protected final String TEXT_551 = ".Internal.Wrapper)get";
  protected final String TEXT_552 = "()).featureMap()).basicAdd(";
  protected final String TEXT_553 = ", new";
  protected final String TEXT_554 = ", msgs);";
  protected final String TEXT_555 = NL + "\t\treturn ((";
  protected final String TEXT_556 = ".Internal)get";
  protected final String TEXT_557 = "()).basicAdd(";
  protected final String TEXT_558 = ", new";
  protected final String TEXT_559 = ", msgs);";
  protected final String TEXT_560 = NL + "\t\t// TODO: implement this method to set the contained '";
  protected final String TEXT_561 = "' ";
  protected final String TEXT_562 = NL + "\t\t// -> this method is automatically invoked to keep the containment relationship in synch" + NL + "\t\t// -> do not modify other features" + NL + "\t\t// -> return msgs, after adding any generated Notification to it (if it is null, a NotificationChain object must be created first)" + NL + "\t\t// Ensure that you remove @generated or mark it @generated NOT" + NL + "\t\tthrow new UnsupportedOperationException();";
  protected final String TEXT_563 = NL + "\t}" + NL;
  protected final String TEXT_564 = NL + "\t/**" + NL + "\t * Sets the value of the '{@link ";
  protected final String TEXT_565 = "#";
  protected final String TEXT_566 = " <em>";
  protected final String TEXT_567 = "</em>}' ";
  protected final String TEXT_568 = ".";
  protected final String TEXT_569 = NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @param value the new value of the '<em>";
  protected final String TEXT_570 = "</em>' ";
  protected final String TEXT_571 = ".";
  protected final String TEXT_572 = NL + "\t * @see ";
  protected final String TEXT_573 = NL + "\t * @see #isSet";
  protected final String TEXT_574 = "()";
  protected final String TEXT_575 = NL + "\t * @see #unset";
  protected final String TEXT_576 = "()";
  protected final String TEXT_577 = NL + "\t * @see #";
  protected final String TEXT_578 = "()" + NL + "\t * @generated" + NL + "\t */";
  protected final String TEXT_579 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */";
  protected final String TEXT_580 = NL + "\tvoid set";
  protected final String TEXT_581 = "(";
  protected final String TEXT_582 = " value);" + NL;
  protected final String TEXT_583 = NL + "\tpublic void set";
  protected final String TEXT_584 = "_";
  protected final String TEXT_585 = "(";
  protected final String TEXT_586 = " ";
  protected final String TEXT_587 = ")" + NL + "\t{";
  protected final String TEXT_588 = NL + "\t\teSet(";
  protected final String TEXT_589 = ", ";
  protected final String TEXT_590 = "new ";
  protected final String TEXT_591 = "(";
  protected final String TEXT_592 = "new";
  protected final String TEXT_593 = ")";
  protected final String TEXT_594 = ");";
  protected final String TEXT_595 = NL + "\t\tif (new";
  protected final String TEXT_596 = " != eInternalContainer() || (eContainerFeatureID != ";
  protected final String TEXT_597 = " && new";
  protected final String TEXT_598 = " != null))" + NL + "\t\t{" + NL + "\t\t\tif (";
  protected final String TEXT_599 = ".isAncestor(this, ";
  protected final String TEXT_600 = "new";
  protected final String TEXT_601 = "))" + NL + "\t\t\t\tthrow new ";
  protected final String TEXT_602 = "(\"Recursive containment not allowed for \" + toString());";
  protected final String TEXT_603 = NL + "\t\t\t";
  protected final String TEXT_604 = " msgs = null;" + NL + "\t\t\tif (eInternalContainer() != null)" + NL + "\t\t\t\tmsgs = eBasicRemoveFromContainer(msgs);" + NL + "\t\t\tif (new";
  protected final String TEXT_605 = " != null)" + NL + "\t\t\t\tmsgs = ((";
  protected final String TEXT_606 = ")new";
  protected final String TEXT_607 = ").eInverseAdd(this, ";
  protected final String TEXT_608 = ", ";
  protected final String TEXT_609 = ".class, msgs);" + NL + "\t\t\tmsgs = basicSet";
  protected final String TEXT_610 = "(";
  protected final String TEXT_611 = "new";
  protected final String TEXT_612 = ", msgs);" + NL + "\t\t\tif (msgs != null) msgs.dispatch();" + NL + "\t\t}";
  protected final String TEXT_613 = NL + "\t\telse if (eNotificationRequired())" + NL + "\t\t\teNotify(new ";
  protected final String TEXT_614 = "(this, ";
  protected final String TEXT_615 = ".SET, ";
  protected final String TEXT_616 = ", new";
  protected final String TEXT_617 = ", new";
  protected final String TEXT_618 = "));";
  protected final String TEXT_619 = NL + "\t\t";
  protected final String TEXT_620 = " ";
  protected final String TEXT_621 = " = (";
  protected final String TEXT_622 = ")eVirtualGet(";
  protected final String TEXT_623 = ");";
  protected final String TEXT_624 = NL + "\t\tif (new";
  protected final String TEXT_625 = " != ";
  protected final String TEXT_626 = ")" + NL + "\t\t{" + NL + "\t\t\t";
  protected final String TEXT_627 = " msgs = null;" + NL + "\t\t\tif (";
  protected final String TEXT_628 = " != null)";
  protected final String TEXT_629 = NL + "\t\t\t\tmsgs = ((";
  protected final String TEXT_630 = ")";
  protected final String TEXT_631 = ").eInverseRemove(this, EOPPOSITE_FEATURE_BASE - ";
  protected final String TEXT_632 = ", null, msgs);" + NL + "\t\t\tif (new";
  protected final String TEXT_633 = " != null)" + NL + "\t\t\t\tmsgs = ((";
  protected final String TEXT_634 = ")new";
  protected final String TEXT_635 = ").eInverseAdd(this, EOPPOSITE_FEATURE_BASE - ";
  protected final String TEXT_636 = ", null, msgs);";
  protected final String TEXT_637 = NL + "\t\t\t\tmsgs = ((";
  protected final String TEXT_638 = ")";
  protected final String TEXT_639 = ").eInverseRemove(this, ";
  protected final String TEXT_640 = ", ";
  protected final String TEXT_641 = ".class, msgs);" + NL + "\t\t\tif (new";
  protected final String TEXT_642 = " != null)" + NL + "\t\t\t\tmsgs = ((";
  protected final String TEXT_643 = ")new";
  protected final String TEXT_644 = ").eInverseAdd(this, ";
  protected final String TEXT_645 = ", ";
  protected final String TEXT_646 = ".class, msgs);";
  protected final String TEXT_647 = NL + "\t\t\tmsgs = basicSet";
  protected final String TEXT_648 = "(";
  protected final String TEXT_649 = "new";
  protected final String TEXT_650 = ", msgs);" + NL + "\t\t\tif (msgs != null) msgs.dispatch();" + NL + "\t\t}";
  protected final String TEXT_651 = NL + "\t\telse" + NL + "\t\t{";
  protected final String TEXT_652 = NL + "\t\t\tboolean old";
  protected final String TEXT_653 = "ESet = eVirtualIsSet(";
  protected final String TEXT_654 = ");";
  protected final String TEXT_655 = NL + "\t\t\tboolean old";
  protected final String TEXT_656 = "ESet = (";
  protected final String TEXT_657 = " & ";
  protected final String TEXT_658 = "_ESETFLAG) != 0;";
  protected final String TEXT_659 = NL + "\t\t\t";
  protected final String TEXT_660 = " |= ";
  protected final String TEXT_661 = "_ESETFLAG;";
  protected final String TEXT_662 = NL + "\t\t\tboolean old";
  protected final String TEXT_663 = "ESet = ";
  protected final String TEXT_664 = "ESet;";
  protected final String TEXT_665 = NL + "\t\t\t";
  protected final String TEXT_666 = "ESet = true;";
  protected final String TEXT_667 = NL + "\t\t\tif (eNotificationRequired())" + NL + "\t\t\t\teNotify(new ";
  protected final String TEXT_668 = "(this, ";
  protected final String TEXT_669 = ".SET, ";
  protected final String TEXT_670 = ", new";
  protected final String TEXT_671 = ", new";
  protected final String TEXT_672 = ", !old";
  protected final String TEXT_673 = "ESet));";
  protected final String TEXT_674 = NL + "\t\t}";
  protected final String TEXT_675 = NL + "\t\telse if (eNotificationRequired())" + NL + "\t\t\teNotify(new ";
  protected final String TEXT_676 = "(this, ";
  protected final String TEXT_677 = ".SET, ";
  protected final String TEXT_678 = ", new";
  protected final String TEXT_679 = ", new";
  protected final String TEXT_680 = "));";
  protected final String TEXT_681 = NL + "\t\t";
  protected final String TEXT_682 = " old";
  protected final String TEXT_683 = " = (";
  protected final String TEXT_684 = " & ";
  protected final String TEXT_685 = "_EFLAG) != 0;";
  protected final String TEXT_686 = NL + "\t\t";
  protected final String TEXT_687 = " old";
  protected final String TEXT_688 = " = ";
  protected final String TEXT_689 = "_EFLAG_VALUES[(";
  protected final String TEXT_690 = " & ";
  protected final String TEXT_691 = "_EFLAG) >>> ";
  protected final String TEXT_692 = "_EFLAG_OFFSET];";
  protected final String TEXT_693 = NL + "\t\tif (new";
  protected final String TEXT_694 = ") ";
  protected final String TEXT_695 = " |= ";
  protected final String TEXT_696 = "_EFLAG; else ";
  protected final String TEXT_697 = " &= ~";
  protected final String TEXT_698 = "_EFLAG;";
  protected final String TEXT_699 = NL + "\t\tif (new";
  protected final String TEXT_700 = " == null) new";
  protected final String TEXT_701 = " = ";
  protected final String TEXT_702 = "_EDEFAULT;" + NL + "\t\t";
  protected final String TEXT_703 = " = ";
  protected final String TEXT_704 = " & ~";
  protected final String TEXT_705 = "_EFLAG | ";
  protected final String TEXT_706 = "new";
  protected final String TEXT_707 = ".ordinal()";
  protected final String TEXT_708 = ".VALUES.indexOf(new";
  protected final String TEXT_709 = ")";
  protected final String TEXT_710 = " << ";
  protected final String TEXT_711 = "_EFLAG_OFFSET;";
  protected final String TEXT_712 = NL + "\t\t";
  protected final String TEXT_713 = " old";
  protected final String TEXT_714 = " = ";
  protected final String TEXT_715 = ";";
  protected final String TEXT_716 = NL + "\t\t";
  protected final String TEXT_717 = " ";
  protected final String TEXT_718 = " = new";
  protected final String TEXT_719 = " == null ? ";
  protected final String TEXT_720 = " : new";
  protected final String TEXT_721 = ";";
  protected final String TEXT_722 = NL + "\t\t";
  protected final String TEXT_723 = " = new";
  protected final String TEXT_724 = " == null ? ";
  protected final String TEXT_725 = " : new";
  protected final String TEXT_726 = ";";
  protected final String TEXT_727 = NL + "\t\t";
  protected final String TEXT_728 = " ";
  protected final String TEXT_729 = " = ";
  protected final String TEXT_730 = "new";
  protected final String TEXT_731 = ";";
  protected final String TEXT_732 = NL + "\t\t";
  protected final String TEXT_733 = " = ";
  protected final String TEXT_734 = "new";
  protected final String TEXT_735 = ";";
  protected final String TEXT_736 = NL + "\t\tObject old";
  protected final String TEXT_737 = " = eVirtualSet(";
  protected final String TEXT_738 = ", ";
  protected final String TEXT_739 = ");";
  protected final String TEXT_740 = NL + "\t\tboolean isSetChange = old";
  protected final String TEXT_741 = " == EVIRTUAL_NO_VALUE;";
  protected final String TEXT_742 = NL + "\t\tboolean old";
  protected final String TEXT_743 = "ESet = (";
  protected final String TEXT_744 = " & ";
  protected final String TEXT_745 = "_ESETFLAG) != 0;";
  protected final String TEXT_746 = NL + "\t\t";
  protected final String TEXT_747 = " |= ";
  protected final String TEXT_748 = "_ESETFLAG;";
  protected final String TEXT_749 = NL + "\t\tboolean old";
  protected final String TEXT_750 = "ESet = ";
  protected final String TEXT_751 = "ESet;";
  protected final String TEXT_752 = NL + "\t\t";
  protected final String TEXT_753 = "ESet = true;";
  protected final String TEXT_754 = NL + "\t\tif (eNotificationRequired())" + NL + "\t\t\teNotify(new ";
  protected final String TEXT_755 = "(this, ";
  protected final String TEXT_756 = ".SET, ";
  protected final String TEXT_757 = ", ";
  protected final String TEXT_758 = "isSetChange ? ";
  protected final String TEXT_759 = " : old";
  protected final String TEXT_760 = "old";
  protected final String TEXT_761 = ", ";
  protected final String TEXT_762 = "new";
  protected final String TEXT_763 = ", ";
  protected final String TEXT_764 = "isSetChange";
  protected final String TEXT_765 = "!old";
  protected final String TEXT_766 = "ESet";
  protected final String TEXT_767 = "));";
  protected final String TEXT_768 = NL + "\t\tif (eNotificationRequired())" + NL + "\t\t\teNotify(new ";
  protected final String TEXT_769 = "(this, ";
  protected final String TEXT_770 = ".SET, ";
  protected final String TEXT_771 = ", ";
  protected final String TEXT_772 = "old";
  protected final String TEXT_773 = " == EVIRTUAL_NO_VALUE ? ";
  protected final String TEXT_774 = " : old";
  protected final String TEXT_775 = "old";
  protected final String TEXT_776 = ", ";
  protected final String TEXT_777 = "new";
  protected final String TEXT_778 = "));";
  protected final String TEXT_779 = NL + "\t\t((";
  protected final String TEXT_780 = ".Internal)((";
  protected final String TEXT_781 = ".Internal.Wrapper)get";
  protected final String TEXT_782 = "()).featureMap()).set(";
  protected final String TEXT_783 = ", ";
  protected final String TEXT_784 = "new ";
  protected final String TEXT_785 = "(";
  protected final String TEXT_786 = "new";
  protected final String TEXT_787 = ")";
  protected final String TEXT_788 = ");";
  protected final String TEXT_789 = NL + "\t\t((";
  protected final String TEXT_790 = ".Internal)get";
  protected final String TEXT_791 = "()).set(";
  protected final String TEXT_792 = ", ";
  protected final String TEXT_793 = "new ";
  protected final String TEXT_794 = "(";
  protected final String TEXT_795 = "new";
  protected final String TEXT_796 = ")";
  protected final String TEXT_797 = ");";
  protected final String TEXT_798 = NL + "\t\t";
  protected final String TEXT_799 = NL + "\t\t// TODO: implement this method to set the '";
  protected final String TEXT_800 = "' ";
  protected final String TEXT_801 = NL + "\t\t// Ensure that you remove @generated or mark it @generated NOT" + NL + "\t\tthrow new UnsupportedOperationException();";
  protected final String TEXT_802 = NL + "\t}" + NL;
  protected final String TEXT_803 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */";
  protected final String TEXT_804 = NL + "\tpublic ";
  protected final String TEXT_805 = " basicUnset";
  protected final String TEXT_806 = "(";
  protected final String TEXT_807 = " msgs)" + NL + "\t{";
  protected final String TEXT_808 = "Object old";
  protected final String TEXT_809 = " = ";
  protected final String TEXT_810 = "eVirtualUnset(";
  protected final String TEXT_811 = ");";
  protected final String TEXT_812 = NL + "\t\t";
  protected final String TEXT_813 = " old";
  protected final String TEXT_814 = " = ";
  protected final String TEXT_815 = ";";
  protected final String TEXT_816 = NL + "\t\t";
  protected final String TEXT_817 = " = null;";
  protected final String TEXT_818 = NL + "\t\tboolean isSetChange = old";
  protected final String TEXT_819 = " != EVIRTUAL_NO_VALUE;";
  protected final String TEXT_820 = NL + "\t\tboolean old";
  protected final String TEXT_821 = "ESet = (";
  protected final String TEXT_822 = " & ";
  protected final String TEXT_823 = "_ESETFLAG) != 0;";
  protected final String TEXT_824 = NL + "\t\t";
  protected final String TEXT_825 = " &= ~";
  protected final String TEXT_826 = "_ESETFLAG;";
  protected final String TEXT_827 = NL + "\t\tboolean old";
  protected final String TEXT_828 = "ESet = ";
  protected final String TEXT_829 = "ESet;";
  protected final String TEXT_830 = NL + "\t\t";
  protected final String TEXT_831 = "ESet = false;";
  protected final String TEXT_832 = NL + "\t\tif (eNotificationRequired())" + NL + "\t\t{" + NL + "\t\t\t";
  protected final String TEXT_833 = " notification = new ";
  protected final String TEXT_834 = "(this, ";
  protected final String TEXT_835 = ".UNSET, ";
  protected final String TEXT_836 = ", ";
  protected final String TEXT_837 = "isSetChange ? old";
  protected final String TEXT_838 = " : null";
  protected final String TEXT_839 = "old";
  protected final String TEXT_840 = ", null, ";
  protected final String TEXT_841 = "isSetChange";
  protected final String TEXT_842 = "old";
  protected final String TEXT_843 = "ESet";
  protected final String TEXT_844 = ");" + NL + "\t\t\tif (msgs == null) msgs = notification; else msgs.add(notification);" + NL + "\t\t}" + NL + "\t\treturn msgs;";
  protected final String TEXT_845 = NL + "\t\t// TODO: implement this method to unset the contained '";
  protected final String TEXT_846 = "' ";
  protected final String TEXT_847 = NL + "\t\t// -> this method is automatically invoked to keep the containment relationship in synch" + NL + "\t\t// -> do not modify other features" + NL + "\t\t// -> return msgs, after adding any generated Notification to it (if it is null, a NotificationChain object must be created first)" + NL + "\t\t// Ensure that you remove @generated or mark it @generated NOT" + NL + "\t\tthrow new UnsupportedOperationException();";
  protected final String TEXT_848 = NL + "\t}" + NL;
  protected final String TEXT_849 = NL + "\t/**" + NL + "\t * Unsets the value of the '{@link ";
  protected final String TEXT_850 = "#";
  protected final String TEXT_851 = " <em>";
  protected final String TEXT_852 = "</em>}' ";
  protected final String TEXT_853 = ".";
  protected final String TEXT_854 = NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->";
  protected final String TEXT_855 = NL + "\t * @see #isSet";
  protected final String TEXT_856 = "()";
  protected final String TEXT_857 = NL + "\t * @see #";
  protected final String TEXT_858 = "()";
  protected final String TEXT_859 = NL + "\t * @see #set";
  protected final String TEXT_860 = "(";
  protected final String TEXT_861 = ")";
  protected final String TEXT_862 = NL + "\t * @generated" + NL + "\t */";
  protected final String TEXT_863 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */";
  protected final String TEXT_864 = NL + "\tvoid unset";
  protected final String TEXT_865 = "();" + NL;
  protected final String TEXT_866 = NL + "\tpublic void unset";
  protected final String TEXT_867 = "_";
  protected final String TEXT_868 = "()" + NL + "\t{";
  protected final String TEXT_869 = NL + "\t\teUnset(";
  protected final String TEXT_870 = ");";
  protected final String TEXT_871 = NL + "\t\t";
  protected final String TEXT_872 = " ";
  protected final String TEXT_873 = " = (";
  protected final String TEXT_874 = ")eVirtualGet(";
  protected final String TEXT_875 = ");";
  protected final String TEXT_876 = NL + "\t\tif (";
  protected final String TEXT_877 = " != null) ((";
  protected final String TEXT_878 = ".Unsettable";
  protected final String TEXT_879 = ")";
  protected final String TEXT_880 = ").unset();";
  protected final String TEXT_881 = NL + "\t\t";
  protected final String TEXT_882 = " ";
  protected final String TEXT_883 = " = (";
  protected final String TEXT_884 = ")eVirtualGet(";
  protected final String TEXT_885 = ");";
  protected final String TEXT_886 = NL + "\t\tif (";
  protected final String TEXT_887 = " != null)" + NL + "\t\t{" + NL + "\t\t\t";
  protected final String TEXT_888 = " msgs = null;";
  protected final String TEXT_889 = NL + "\t\t\tmsgs = ((";
  protected final String TEXT_890 = ")";
  protected final String TEXT_891 = ").eInverseRemove(this, EOPPOSITE_FEATURE_BASE - ";
  protected final String TEXT_892 = ", null, msgs);";
  protected final String TEXT_893 = NL + "\t\t\tmsgs = ((";
  protected final String TEXT_894 = ")";
  protected final String TEXT_895 = ").eInverseRemove(this, ";
  protected final String TEXT_896 = ", ";
  protected final String TEXT_897 = ".class, msgs);";
  protected final String TEXT_898 = NL + "\t\t\tmsgs = basicUnset";
  protected final String TEXT_899 = "(msgs);" + NL + "\t\t\tif (msgs != null) msgs.dispatch();" + NL + "\t\t}" + NL + "\t\telse" + NL + "\t\t{";
  protected final String TEXT_900 = NL + "\t\t\tboolean old";
  protected final String TEXT_901 = "ESet = eVirtualIsSet(";
  protected final String TEXT_902 = ");";
  protected final String TEXT_903 = NL + "\t\t\tboolean old";
  protected final String TEXT_904 = "ESet = (";
  protected final String TEXT_905 = " & ";
  protected final String TEXT_906 = "_ESETFLAG) != 0;";
  protected final String TEXT_907 = NL + "\t\t\t";
  protected final String TEXT_908 = " &= ~";
  protected final String TEXT_909 = "_ESETFLAG;";
  protected final String TEXT_910 = NL + "\t\t\tboolean old";
  protected final String TEXT_911 = "ESet = ";
  protected final String TEXT_912 = "ESet;";
  protected final String TEXT_913 = NL + "\t\t\t";
  protected final String TEXT_914 = "ESet = false;";
  protected final String TEXT_915 = NL + "\t\t\tif (eNotificationRequired())" + NL + "\t\t\t\teNotify(new ";
  protected final String TEXT_916 = "(this, ";
  protected final String TEXT_917 = ".UNSET, ";
  protected final String TEXT_918 = ", null, null, old";
  protected final String TEXT_919 = "ESet));";
  protected final String TEXT_920 = NL + "\t\t}";
  protected final String TEXT_921 = NL + "\t\t";
  protected final String TEXT_922 = " old";
  protected final String TEXT_923 = " = (";
  protected final String TEXT_924 = " & ";
  protected final String TEXT_925 = "_EFLAG) != 0;";
  protected final String TEXT_926 = NL + "\t\t";
  protected final String TEXT_927 = " old";
  protected final String TEXT_928 = " = ";
  protected final String TEXT_929 = "_EFLAG_VALUES[(";
  protected final String TEXT_930 = " & ";
  protected final String TEXT_931 = "_EFLAG) >>> ";
  protected final String TEXT_932 = "_EFLAG_OFFSET];";
  protected final String TEXT_933 = NL + "\t\tObject old";
  protected final String TEXT_934 = " = eVirtualUnset(";
  protected final String TEXT_935 = ");";
  protected final String TEXT_936 = NL + "\t\t";
  protected final String TEXT_937 = " old";
  protected final String TEXT_938 = " = ";
  protected final String TEXT_939 = ";";
  protected final String TEXT_940 = NL + "\t\tboolean isSetChange = old";
  protected final String TEXT_941 = " != EVIRTUAL_NO_VALUE;";
  protected final String TEXT_942 = NL + "\t\tboolean old";
  protected final String TEXT_943 = "ESet = (";
  protected final String TEXT_944 = " & ";
  protected final String TEXT_945 = "_ESETFLAG) != 0;";
  protected final String TEXT_946 = NL + "\t\tboolean old";
  protected final String TEXT_947 = "ESet = ";
  protected final String TEXT_948 = "ESet;";
  protected final String TEXT_949 = NL + "\t\t";
  protected final String TEXT_950 = " = null;";
  protected final String TEXT_951 = NL + "\t\t";
  protected final String TEXT_952 = " &= ~";
  protected final String TEXT_953 = "_ESETFLAG;";
  protected final String TEXT_954 = NL + "\t\t";
  protected final String TEXT_955 = "ESet = false;";
  protected final String TEXT_956 = NL + "\t\tif (eNotificationRequired())" + NL + "\t\t\teNotify(new ";
  protected final String TEXT_957 = "(this, ";
  protected final String TEXT_958 = ".UNSET, ";
  protected final String TEXT_959 = ", ";
  protected final String TEXT_960 = "isSetChange ? old";
  protected final String TEXT_961 = " : null";
  protected final String TEXT_962 = "old";
  protected final String TEXT_963 = ", null, ";
  protected final String TEXT_964 = "isSetChange";
  protected final String TEXT_965 = "old";
  protected final String TEXT_966 = "ESet";
  protected final String TEXT_967 = "));";
  protected final String TEXT_968 = NL + "\t\tif (";
  protected final String TEXT_969 = ") ";
  protected final String TEXT_970 = " |= ";
  protected final String TEXT_971 = "_EFLAG; else ";
  protected final String TEXT_972 = " &= ~";
  protected final String TEXT_973 = "_EFLAG;";
  protected final String TEXT_974 = NL + "\t\t";
  protected final String TEXT_975 = " = ";
  protected final String TEXT_976 = " & ~";
  protected final String TEXT_977 = "_EFLAG | ";
  protected final String TEXT_978 = "_EFLAG_DEFAULT;";
  protected final String TEXT_979 = NL + "\t\t";
  protected final String TEXT_980 = " = ";
  protected final String TEXT_981 = ";";
  protected final String TEXT_982 = NL + "\t\t";
  protected final String TEXT_983 = " &= ~";
  protected final String TEXT_984 = "_ESETFLAG;";
  protected final String TEXT_985 = NL + "\t\t";
  protected final String TEXT_986 = "ESet = false;";
  protected final String TEXT_987 = NL + "\t\tif (eNotificationRequired())" + NL + "\t\t\teNotify(new ";
  protected final String TEXT_988 = "(this, ";
  protected final String TEXT_989 = ".UNSET, ";
  protected final String TEXT_990 = ", ";
  protected final String TEXT_991 = "isSetChange ? old";
  protected final String TEXT_992 = " : ";
  protected final String TEXT_993 = "old";
  protected final String TEXT_994 = ", ";
  protected final String TEXT_995 = ", ";
  protected final String TEXT_996 = "isSetChange";
  protected final String TEXT_997 = "old";
  protected final String TEXT_998 = "ESet";
  protected final String TEXT_999 = "));";
  protected final String TEXT_1000 = NL + "\t\t((";
  protected final String TEXT_1001 = ".Internal)((";
  protected final String TEXT_1002 = ".Internal.Wrapper)get";
  protected final String TEXT_1003 = "()).featureMap()).clear(";
  protected final String TEXT_1004 = ");";
  protected final String TEXT_1005 = NL + "\t\t((";
  protected final String TEXT_1006 = ".Internal)get";
  protected final String TEXT_1007 = "()).clear(";
  protected final String TEXT_1008 = ");";
  protected final String TEXT_1009 = NL + "\t\t";
  protected final String TEXT_1010 = NL + "\t\t// TODO: implement this method to unset the '";
  protected final String TEXT_1011 = "' ";
  protected final String TEXT_1012 = NL + "\t\t// Ensure that you remove @generated or mark it @generated NOT" + NL + "\t\tthrow new UnsupportedOperationException();";
  protected final String TEXT_1013 = NL + "\t}" + NL;
  protected final String TEXT_1014 = NL + "\t/**" + NL + "\t * Returns whether the value of the '{@link ";
  protected final String TEXT_1015 = "#";
  protected final String TEXT_1016 = " <em>";
  protected final String TEXT_1017 = "</em>}' ";
  protected final String TEXT_1018 = " is set.";
  protected final String TEXT_1019 = NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @return whether the value of the '<em>";
  protected final String TEXT_1020 = "</em>' ";
  protected final String TEXT_1021 = " is set.";
  protected final String TEXT_1022 = NL + "\t * @see #unset";
  protected final String TEXT_1023 = "()";
  protected final String TEXT_1024 = NL + "\t * @see #";
  protected final String TEXT_1025 = "()";
  protected final String TEXT_1026 = NL + "\t * @see #set";
  protected final String TEXT_1027 = "(";
  protected final String TEXT_1028 = ")";
  protected final String TEXT_1029 = NL + "\t * @generated" + NL + "\t */";
  protected final String TEXT_1030 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */";
  protected final String TEXT_1031 = NL + "\tboolean isSet";
  protected final String TEXT_1032 = "();" + NL;
  protected final String TEXT_1033 = NL + "\tpublic boolean isSet";
  protected final String TEXT_1034 = "_";
  protected final String TEXT_1035 = "()" + NL + "\t{";
  protected final String TEXT_1036 = NL + "\t\treturn eIsSet(";
  protected final String TEXT_1037 = ");";
  protected final String TEXT_1038 = NL + "\t\t";
  protected final String TEXT_1039 = " ";
  protected final String TEXT_1040 = " = (";
  protected final String TEXT_1041 = ")eVirtualGet(";
  protected final String TEXT_1042 = ");";
  protected final String TEXT_1043 = NL + "\t\treturn ";
  protected final String TEXT_1044 = " != null && ((";
  protected final String TEXT_1045 = ".Unsettable";
  protected final String TEXT_1046 = ")";
  protected final String TEXT_1047 = ").isSet();";
  protected final String TEXT_1048 = NL + "\t\treturn eVirtualIsSet(";
  protected final String TEXT_1049 = ");";
  protected final String TEXT_1050 = NL + "\t\treturn (";
  protected final String TEXT_1051 = " & ";
  protected final String TEXT_1052 = "_ESETFLAG) != 0;";
  protected final String TEXT_1053 = NL + "\t\treturn ";
  protected final String TEXT_1054 = "ESet;";
  protected final String TEXT_1055 = NL + "\t\treturn !((";
  protected final String TEXT_1056 = ".Internal)((";
  protected final String TEXT_1057 = ".Internal.Wrapper)get";
  protected final String TEXT_1058 = "()).featureMap()).isEmpty(";
  protected final String TEXT_1059 = ");";
  protected final String TEXT_1060 = NL + "\t\treturn !((";
  protected final String TEXT_1061 = ".Internal)get";
  protected final String TEXT_1062 = "()).isEmpty(";
  protected final String TEXT_1063 = ");";
  protected final String TEXT_1064 = NL + "\t\t";
  protected final String TEXT_1065 = NL + "\t\t// TODO: implement this method to return whether the '";
  protected final String TEXT_1066 = "' ";
  protected final String TEXT_1067 = " is set" + NL + "\t\t// Ensure that you remove @generated or mark it @generated NOT" + NL + "\t\tthrow new UnsupportedOperationException();";
  protected final String TEXT_1068 = NL + "\t}" + NL;
  protected final String TEXT_1069 = NL + "\t/**";
  protected final String TEXT_1070 = NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->";
  protected final String TEXT_1071 = NL + "\t * <!-- begin-model-doc -->";
  protected final String TEXT_1072 = NL + "\t * ";
  protected final String TEXT_1073 = NL + "\t * @param ";
  protected final String TEXT_1074 = NL + "\t *   ";
  protected final String TEXT_1075 = NL + "\t * @param ";
  protected final String TEXT_1076 = " ";
  protected final String TEXT_1077 = NL + "\t * <!-- end-model-doc -->";
  protected final String TEXT_1078 = NL + "\t * @model ";
  protected final String TEXT_1079 = NL + "\t *        ";
  protected final String TEXT_1080 = NL + "\t * @model";
  protected final String TEXT_1081 = NL + "\t * @generated" + NL + "\t */";
  protected final String TEXT_1082 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */";
  protected final String TEXT_1083 = NL + "\t";
  protected final String TEXT_1084 = " ";
  protected final String TEXT_1085 = "(";
  protected final String TEXT_1086 = ")";
  protected final String TEXT_1087 = ";" + NL;
  protected final String TEXT_1088 = NL + "\tpublic ";
  protected final String TEXT_1089 = " ";
  protected final String TEXT_1090 = "(";
  protected final String TEXT_1091 = ")";
  protected final String TEXT_1092 = NL + "\t{";
  protected final String TEXT_1093 = NL + "\t\t";
  protected final String TEXT_1094 = NL + "\t\t// TODO: implement this method" + NL + "\t\t// -> specify the condition that violates the invariant" + NL + "\t\t// -> verify the details of the diagnostic, including severity and message" + NL + "\t\t// Ensure that you remove @generated or mark it @generated NOT" + NL + "\t\tif (false)" + NL + "\t\t{" + NL + "\t\t\tif (";
  protected final String TEXT_1095 = " != null)" + NL + "\t\t\t{" + NL + "\t\t\t\t";
  protected final String TEXT_1096 = ".add" + NL + "\t\t\t\t\t(new ";
  protected final String TEXT_1097 = NL + "\t\t\t\t\t\t(";
  protected final String TEXT_1098 = ".ERROR," + NL + "\t\t\t\t\t\t ";
  protected final String TEXT_1099 = ".DIAGNOSTIC_SOURCE," + NL + "\t\t\t\t\t\t ";
  protected final String TEXT_1100 = ".";
  protected final String TEXT_1101 = "," + NL + "\t\t\t\t\t\t ";
  protected final String TEXT_1102 = ".INSTANCE.getString(\"_UI_GenericInvariant_diagnostic\", new Object[] { \"";
  protected final String TEXT_1103 = "\", ";
  protected final String TEXT_1104 = ".getObjectLabel(this, ";
  protected final String TEXT_1105 = ") }),";
  protected final String TEXT_1106 = NL + "\t\t\t\t\t\t new Object [] { this }));" + NL + "\t\t\t}" + NL + "\t\t\treturn false;" + NL + "\t\t}" + NL + "\t\treturn true;";
  protected final String TEXT_1107 = NL + "\t\t// TODO: implement this method" + NL + "\t\t// Ensure that you remove @generated or mark it @generated NOT" + NL + "\t\tthrow new UnsupportedOperationException();";
  protected final String TEXT_1108 = NL + "\t}" + NL;
  protected final String TEXT_1109 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */";
  protected final String TEXT_1110 = NL + "\t@SuppressWarnings(\"unchecked\")";
  protected final String TEXT_1111 = NL + "\t@Override";
  protected final String TEXT_1112 = NL + "\tpublic ";
  protected final String TEXT_1113 = " eInverseAdd(";
  protected final String TEXT_1114 = " otherEnd, int featureID, ";
  protected final String TEXT_1115 = " msgs)" + NL + "\t{" + NL + "\t\tswitch (featureID";
  protected final String TEXT_1116 = ")" + NL + "\t\t{";
  protected final String TEXT_1117 = NL + "\t\t\tcase ";
  protected final String TEXT_1118 = ":";
  protected final String TEXT_1119 = NL + "\t\t\t\treturn ((";
  protected final String TEXT_1120 = "(";
  protected final String TEXT_1121 = ".InternalMapView";
  protected final String TEXT_1122 = ")";
  protected final String TEXT_1123 = "()).eMap()).basicAdd(otherEnd, msgs);";
  protected final String TEXT_1124 = NL + "\t\t\t\treturn (";
  protected final String TEXT_1125 = "()).basicAdd(otherEnd, msgs);";
  protected final String TEXT_1126 = NL + "\t\t\t\tif (eInternalContainer() != null)" + NL + "\t\t\t\t\tmsgs = eBasicRemoveFromContainer(msgs);";
  protected final String TEXT_1127 = NL + "\t\t\t\treturn basicSet";
  protected final String TEXT_1128 = "((";
  protected final String TEXT_1129 = ")otherEnd, msgs);";
  protected final String TEXT_1130 = NL + "\t\t\t\treturn eBasicSetContainer(otherEnd, ";
  protected final String TEXT_1131 = ", msgs);";
  protected final String TEXT_1132 = NL + "\t\t\t\t";
  protected final String TEXT_1133 = " ";
  protected final String TEXT_1134 = " = (";
  protected final String TEXT_1135 = ")eVirtualGet(";
  protected final String TEXT_1136 = ");";
  protected final String TEXT_1137 = NL + "\t\t\t\t";
  protected final String TEXT_1138 = " ";
  protected final String TEXT_1139 = " = ";
  protected final String TEXT_1140 = "basicGet";
  protected final String TEXT_1141 = "();";
  protected final String TEXT_1142 = NL + "\t\t\t\tif (";
  protected final String TEXT_1143 = " != null)";
  protected final String TEXT_1144 = NL + "\t\t\t\t\tmsgs = ((";
  protected final String TEXT_1145 = ")";
  protected final String TEXT_1146 = ").eInverseRemove(this, EOPPOSITE_FEATURE_BASE - ";
  protected final String TEXT_1147 = ", null, msgs);";
  protected final String TEXT_1148 = NL + "\t\t\t\t\tmsgs = ((";
  protected final String TEXT_1149 = ")";
  protected final String TEXT_1150 = ").eInverseRemove(this, ";
  protected final String TEXT_1151 = ", ";
  protected final String TEXT_1152 = ".class, msgs);";
  protected final String TEXT_1153 = NL + "\t\t\t\treturn basicSet";
  protected final String TEXT_1154 = "((";
  protected final String TEXT_1155 = ")otherEnd, msgs);";
  protected final String TEXT_1156 = NL + "\t\t}";
  protected final String TEXT_1157 = NL + "\t\treturn super.eInverseAdd(otherEnd, featureID, msgs);";
  protected final String TEXT_1158 = NL + "\t\treturn eDynamicInverseAdd(otherEnd, featureID, msgs);";
  protected final String TEXT_1159 = NL + "\t}" + NL;
  protected final String TEXT_1160 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */";
  protected final String TEXT_1161 = NL + "\t@Override";
  protected final String TEXT_1162 = NL + "\tpublic ";
  protected final String TEXT_1163 = " eInverseRemove(";
  protected final String TEXT_1164 = " otherEnd, int featureID, ";
  protected final String TEXT_1165 = " msgs)" + NL + "\t{" + NL + "\t\tswitch (featureID";
  protected final String TEXT_1166 = ")" + NL + "\t\t{";
  protected final String TEXT_1167 = NL + "\t\t\tcase ";
  protected final String TEXT_1168 = ":";
  protected final String TEXT_1169 = NL + "\t\t\t\treturn ((";
  protected final String TEXT_1170 = ")((";
  protected final String TEXT_1171 = ".InternalMapView";
  protected final String TEXT_1172 = ")";
  protected final String TEXT_1173 = "()).eMap()).basicRemove(otherEnd, msgs);";
  protected final String TEXT_1174 = NL + "\t\t\t\treturn ((";
  protected final String TEXT_1175 = ")((";
  protected final String TEXT_1176 = ".Internal.Wrapper)";
  protected final String TEXT_1177 = "()).featureMap()).basicRemove(otherEnd, msgs);";
  protected final String TEXT_1178 = NL + "\t\t\t\treturn ((";
  protected final String TEXT_1179 = ")";
  protected final String TEXT_1180 = "()).basicRemove(otherEnd, msgs);";
  protected final String TEXT_1181 = NL + "\t\t\t\treturn eBasicSetContainer(null, ";
  protected final String TEXT_1182 = ", msgs);";
  protected final String TEXT_1183 = NL + "\t\t\t\treturn basicUnset";
  protected final String TEXT_1184 = "(msgs);";
  protected final String TEXT_1185 = NL + "\t\t\t\treturn basicSet";
  protected final String TEXT_1186 = "(null, msgs);";
  protected final String TEXT_1187 = NL + "\t\t}";
  protected final String TEXT_1188 = NL + "\t\treturn super.eInverseRemove(otherEnd, featureID, msgs);";
  protected final String TEXT_1189 = NL + "\t\treturn eDynamicInverseRemove(otherEnd, featureID, msgs);";
  protected final String TEXT_1190 = NL + "\t}" + NL;
  protected final String TEXT_1191 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */";
  protected final String TEXT_1192 = NL + "\t@Override";
  protected final String TEXT_1193 = NL + "\tpublic ";
  protected final String TEXT_1194 = " eBasicRemoveFromContainerFeature(";
  protected final String TEXT_1195 = " msgs)" + NL + "\t{" + NL + "\t\tswitch (eContainerFeatureID";
  protected final String TEXT_1196 = ")" + NL + "\t\t{";
  protected final String TEXT_1197 = NL + "\t\t\tcase ";
  protected final String TEXT_1198 = ":" + NL + "\t\t\t\treturn eInternalContainer().eInverseRemove(this, ";
  protected final String TEXT_1199 = ", ";
  protected final String TEXT_1200 = ".class, msgs);";
  protected final String TEXT_1201 = NL + "\t\t}";
  protected final String TEXT_1202 = NL + "\t\treturn super.eBasicRemoveFromContainerFeature(msgs);";
  protected final String TEXT_1203 = NL + "\t\treturn eDynamicBasicRemoveFromContainer(msgs);";
  protected final String TEXT_1204 = NL + "\t}" + NL;
  protected final String TEXT_1205 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */";
  protected final String TEXT_1206 = NL + "\t@Override";
  protected final String TEXT_1207 = NL + "\tpublic Object eGet(int featureID, boolean resolve, boolean coreType)" + NL + "\t{" + NL + "\t\tswitch (featureID";
  protected final String TEXT_1208 = ")" + NL + "\t\t{";
  protected final String TEXT_1209 = NL + "\t\t\tcase ";
  protected final String TEXT_1210 = ":";
  protected final String TEXT_1211 = NL + "\t\t\t\treturn ";
  protected final String TEXT_1212 = "() ? Boolean.TRUE : Boolean.FALSE;";
  protected final String TEXT_1213 = NL + "\t\t\t\treturn new ";
  protected final String TEXT_1214 = "(";
  protected final String TEXT_1215 = "());";
  protected final String TEXT_1216 = NL + "\t\t\t\tif (resolve) return ";
  protected final String TEXT_1217 = "();" + NL + "\t\t\t\treturn basicGet";
  protected final String TEXT_1218 = "();";
  protected final String TEXT_1219 = NL + "\t\t\t\tif (coreType) return ((";
  protected final String TEXT_1220 = ".InternalMapView";
  protected final String TEXT_1221 = ")";
  protected final String TEXT_1222 = "()).eMap();" + NL + "\t\t\t\telse return ";
  protected final String TEXT_1223 = "();";
  protected final String TEXT_1224 = NL + "\t\t\t\tif (coreType) return ";
  protected final String TEXT_1225 = "();" + NL + "\t\t\t\telse return ";
  protected final String TEXT_1226 = "().map();";
  protected final String TEXT_1227 = NL + "\t\t\t\tif (coreType) return ((";
  protected final String TEXT_1228 = ".Internal.Wrapper)";
  protected final String TEXT_1229 = "()).featureMap();" + NL + "\t\t\t\treturn ";
  protected final String TEXT_1230 = "();";
  protected final String TEXT_1231 = NL + "\t\t\t\tif (coreType) return ";
  protected final String TEXT_1232 = "();" + NL + "\t\t\t\treturn ((";
  protected final String TEXT_1233 = ".Internal)";
  protected final String TEXT_1234 = "()).getWrapper();";
  protected final String TEXT_1235 = NL + "\t\t\t\treturn ";
  protected final String TEXT_1236 = "();";
  protected final String TEXT_1237 = NL + "\t\t}";
  protected final String TEXT_1238 = NL + "\t\treturn super.eGet(featureID, resolve, coreType);";
  protected final String TEXT_1239 = NL + "\t\treturn eDynamicGet(featureID, resolve, coreType);";
  protected final String TEXT_1240 = NL + "\t}" + NL;
  protected final String TEXT_1241 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */";
  protected final String TEXT_1242 = NL + "\t@SuppressWarnings(\"unchecked\")";
  protected final String TEXT_1243 = NL + "\t@Override";
  protected final String TEXT_1244 = NL + "\tpublic void eSet(int featureID, Object newValue)" + NL + "\t{" + NL + "\t\tswitch (featureID";
  protected final String TEXT_1245 = ")" + NL + "\t\t{";
  protected final String TEXT_1246 = NL + "\t\t\tcase ";
  protected final String TEXT_1247 = ":";
  protected final String TEXT_1248 = NL + "\t\t\t\t((";
  protected final String TEXT_1249 = ".Internal)((";
  protected final String TEXT_1250 = ".Internal.Wrapper)";
  protected final String TEXT_1251 = "()).featureMap()).set(newValue);";
  protected final String TEXT_1252 = NL + "\t\t\t\t((";
  protected final String TEXT_1253 = ".Internal)";
  protected final String TEXT_1254 = "()).set(newValue);";
  protected final String TEXT_1255 = NL + "\t\t\t\t((";
  protected final String TEXT_1256 = ".Setting)((";
  protected final String TEXT_1257 = ".InternalMapView";
  protected final String TEXT_1258 = ")";
  protected final String TEXT_1259 = "()).eMap()).set(newValue);";
  protected final String TEXT_1260 = NL + "\t\t\t\t((";
  protected final String TEXT_1261 = ".Setting)";
  protected final String TEXT_1262 = "()).set(newValue);";
  protected final String TEXT_1263 = NL + "\t\t\t\t";
  protected final String TEXT_1264 = "().clear();" + NL + "\t\t\t\t";
  protected final String TEXT_1265 = "().addAll((";
  protected final String TEXT_1266 = "<? extends ";
  protected final String TEXT_1267 = ">";
  protected final String TEXT_1268 = ")newValue);";
  protected final String TEXT_1269 = NL + "\t\t\t\tset";
  protected final String TEXT_1270 = "(((";
  protected final String TEXT_1271 = ")newValue).";
  protected final String TEXT_1272 = "());";
  protected final String TEXT_1273 = NL + "\t\t\t\tset";
  protected final String TEXT_1274 = "(";
  protected final String TEXT_1275 = "(";
  protected final String TEXT_1276 = ")";
  protected final String TEXT_1277 = "newValue);";
  protected final String TEXT_1278 = NL + "\t\t\t\treturn;";
  protected final String TEXT_1279 = NL + "\t\t}";
  protected final String TEXT_1280 = NL + "\t\tsuper.eSet(featureID, newValue);";
  protected final String TEXT_1281 = NL + "\t\teDynamicSet(featureID, newValue);";
  protected final String TEXT_1282 = NL + "\t}" + NL;
  protected final String TEXT_1283 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */";
  protected final String TEXT_1284 = NL + "\t@Override";
  protected final String TEXT_1285 = NL + "\tpublic void eUnset(int featureID)" + NL + "\t{" + NL + "\t\tswitch (featureID";
  protected final String TEXT_1286 = ")" + NL + "\t\t{";
  protected final String TEXT_1287 = NL + "\t\t\tcase ";
  protected final String TEXT_1288 = ":";
  protected final String TEXT_1289 = NL + "\t\t\t\t((";
  protected final String TEXT_1290 = ".Internal.Wrapper)";
  protected final String TEXT_1291 = "()).featureMap().clear();";
  protected final String TEXT_1292 = NL + "\t\t\t\t";
  protected final String TEXT_1293 = "().clear();";
  protected final String TEXT_1294 = NL + "\t\t\t\tunset";
  protected final String TEXT_1295 = "();";
  protected final String TEXT_1296 = NL + "\t\t\t\tset";
  protected final String TEXT_1297 = "((";
  protected final String TEXT_1298 = ")null);";
  protected final String TEXT_1299 = NL + "\t\t\t\tset";
  protected final String TEXT_1300 = "(";
  protected final String TEXT_1301 = ");";
  protected final String TEXT_1302 = NL + "\t\t\t\treturn;";
  protected final String TEXT_1303 = NL + "\t\t}";
  protected final String TEXT_1304 = NL + "\t\tsuper.eUnset(featureID);";
  protected final String TEXT_1305 = NL + "\t\teDynamicUnset(featureID);";
  protected final String TEXT_1306 = NL + "\t}" + NL;
  protected final String TEXT_1307 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */";
  protected final String TEXT_1308 = NL + "\t@SuppressWarnings(\"unchecked\")";
  protected final String TEXT_1309 = NL + "\t@Override";
  protected final String TEXT_1310 = NL + "\tpublic boolean eIsSet(int featureID)" + NL + "\t{" + NL + "\t\tswitch (featureID";
  protected final String TEXT_1311 = ")" + NL + "\t\t{";
  protected final String TEXT_1312 = NL + "\t\t\tcase ";
  protected final String TEXT_1313 = ":";
  protected final String TEXT_1314 = NL + "\t\t\t\treturn !((";
  protected final String TEXT_1315 = ".Internal.Wrapper)";
  protected final String TEXT_1316 = "()).featureMap().isEmpty();";
  protected final String TEXT_1317 = NL + "\t\t\t\treturn ";
  protected final String TEXT_1318 = " != null && !";
  protected final String TEXT_1319 = ".featureMap().isEmpty();";
  protected final String TEXT_1320 = NL + "\t\t\t\treturn ";
  protected final String TEXT_1321 = " != null && !";
  protected final String TEXT_1322 = ".isEmpty();";
  protected final String TEXT_1323 = NL + "\t\t\t\t";
  protected final String TEXT_1324 = " ";
  protected final String TEXT_1325 = " = (";
  protected final String TEXT_1326 = ")eVirtualGet(";
  protected final String TEXT_1327 = ");" + NL + "\t\t\t\treturn ";
  protected final String TEXT_1328 = " != null && !";
  protected final String TEXT_1329 = ".isEmpty();";
  protected final String TEXT_1330 = NL + "\t\t\t\treturn !";
  protected final String TEXT_1331 = "().isEmpty();";
  protected final String TEXT_1332 = NL + "\t\t\t\treturn isSet";
  protected final String TEXT_1333 = "();";
  protected final String TEXT_1334 = NL + "\t\t\t\treturn ";
  protected final String TEXT_1335 = " != null;";
  protected final String TEXT_1336 = NL + "\t\t\t\treturn eVirtualGet(";
  protected final String TEXT_1337 = ") != null;";
  protected final String TEXT_1338 = NL + "\t\t\t\treturn basicGet";
  protected final String TEXT_1339 = "() != null;";
  protected final String TEXT_1340 = NL + "\t\t\t\treturn ";
  protected final String TEXT_1341 = " != null;";
  protected final String TEXT_1342 = NL + "\t\t\t\treturn eVirtualGet(";
  protected final String TEXT_1343 = ") != null;";
  protected final String TEXT_1344 = NL + "\t\t\t\treturn ";
  protected final String TEXT_1345 = "() != null;";
  protected final String TEXT_1346 = NL + "\t\t\t\treturn ((";
  protected final String TEXT_1347 = " & ";
  protected final String TEXT_1348 = "_EFLAG) != 0) != ";
  protected final String TEXT_1349 = ";";
  protected final String TEXT_1350 = NL + "\t\t\t\treturn (";
  protected final String TEXT_1351 = " & ";
  protected final String TEXT_1352 = "_EFLAG) != ";
  protected final String TEXT_1353 = "_EFLAG_DEFAULT;";
  protected final String TEXT_1354 = NL + "\t\t\t\treturn ";
  protected final String TEXT_1355 = " != ";
  protected final String TEXT_1356 = ";";
  protected final String TEXT_1357 = NL + "\t\t\t\treturn eVirtualGet(";
  protected final String TEXT_1358 = ", ";
  protected final String TEXT_1359 = ") != ";
  protected final String TEXT_1360 = ";";
  protected final String TEXT_1361 = NL + "\t\t\t\treturn ";
  protected final String TEXT_1362 = "() != ";
  protected final String TEXT_1363 = ";";
  protected final String TEXT_1364 = NL + "\t\t\t\treturn ";
  protected final String TEXT_1365 = " == null ? ";
  protected final String TEXT_1366 = " != null : !";
  protected final String TEXT_1367 = ".equals(";
  protected final String TEXT_1368 = ");";
  protected final String TEXT_1369 = NL + "\t\t\t\t";
  protected final String TEXT_1370 = " ";
  protected final String TEXT_1371 = " = (";
  protected final String TEXT_1372 = ")eVirtualGet(";
  protected final String TEXT_1373 = ", ";
  protected final String TEXT_1374 = ");" + NL + "\t\t\t\treturn ";
  protected final String TEXT_1375 = " == null ? ";
  protected final String TEXT_1376 = " != null : !";
  protected final String TEXT_1377 = ".equals(";
  protected final String TEXT_1378 = ");";
  protected final String TEXT_1379 = NL + "\t\t\t\treturn ";
  protected final String TEXT_1380 = " == null ? ";
  protected final String TEXT_1381 = "() != null : !";
  protected final String TEXT_1382 = ".equals(";
  protected final String TEXT_1383 = "());";
  protected final String TEXT_1384 = NL + "\t\t}";
  protected final String TEXT_1385 = NL + "\t\treturn super.eIsSet(featureID);";
  protected final String TEXT_1386 = NL + "\t\treturn eDynamicIsSet(featureID);";
  protected final String TEXT_1387 = NL + "\t}" + NL;
  protected final String TEXT_1388 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */";
  protected final String TEXT_1389 = NL + "\t@Override";
  protected final String TEXT_1390 = NL + "\tpublic int eBaseStructuralFeatureID(int derivedFeatureID, Class";
  protected final String TEXT_1391 = " baseClass)" + NL + "\t{";
  protected final String TEXT_1392 = NL + "\t\tif (baseClass == ";
  protected final String TEXT_1393 = ".class)" + NL + "\t\t{" + NL + "\t\t\tswitch (derivedFeatureID";
  protected final String TEXT_1394 = ")" + NL + "\t\t\t{";
  protected final String TEXT_1395 = NL + "\t\t\t\tcase ";
  protected final String TEXT_1396 = ": return ";
  protected final String TEXT_1397 = ";";
  protected final String TEXT_1398 = NL + "\t\t\t\tdefault: return -1;" + NL + "\t\t\t}" + NL + "\t\t}";
  protected final String TEXT_1399 = NL + "\t\treturn super.eBaseStructuralFeatureID(derivedFeatureID, baseClass);" + NL + "\t}";
  protected final String TEXT_1400 = NL + NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */";
  protected final String TEXT_1401 = NL + "\t@Override";
  protected final String TEXT_1402 = NL + "\tpublic int eDerivedStructuralFeatureID(int baseFeatureID, Class";
  protected final String TEXT_1403 = " baseClass)" + NL + "\t{";
  protected final String TEXT_1404 = NL + "\t\tif (baseClass == ";
  protected final String TEXT_1405 = ".class)" + NL + "\t\t{" + NL + "\t\t\tswitch (baseFeatureID)" + NL + "\t\t\t{";
  protected final String TEXT_1406 = NL + "\t\t\t\tcase ";
  protected final String TEXT_1407 = ": return ";
  protected final String TEXT_1408 = ";";
  protected final String TEXT_1409 = NL + "\t\t\t\tdefault: return -1;" + NL + "\t\t\t}" + NL + "\t\t}";
  protected final String TEXT_1410 = NL + "\t\tif (baseClass == ";
  protected final String TEXT_1411 = ".class)" + NL + "\t\t{" + NL + "\t\t\tswitch (baseFeatureID";
  protected final String TEXT_1412 = ")" + NL + "\t\t\t{";
  protected final String TEXT_1413 = NL + "\t\t\t\tcase ";
  protected final String TEXT_1414 = ": return ";
  protected final String TEXT_1415 = ";";
  protected final String TEXT_1416 = NL + "\t\t\t\tdefault: return -1;" + NL + "\t\t\t}" + NL + "\t\t}";
  protected final String TEXT_1417 = NL + "\t\treturn super.eDerivedStructuralFeatureID(baseFeatureID, baseClass);" + NL + "\t}" + NL;
  protected final String TEXT_1418 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */";
  protected final String TEXT_1419 = NL + "\t@Override";
  protected final String TEXT_1420 = NL + "\tprotected Object[] eVirtualValues()" + NL + "\t{" + NL + "\t\treturn ";
  protected final String TEXT_1421 = ";" + NL + "\t}" + NL + "" + NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */";
  protected final String TEXT_1422 = NL + "\t@Override";
  protected final String TEXT_1423 = NL + "\tprotected void eSetVirtualValues(Object[] newValues)" + NL + "\t{" + NL + "\t\t";
  protected final String TEXT_1424 = " = newValues;" + NL + "\t}" + NL;
  protected final String TEXT_1425 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */";
  protected final String TEXT_1426 = NL + "\t@Override";
  protected final String TEXT_1427 = NL + "\tprotected int eVirtualIndexBits(int offset)" + NL + "\t{" + NL + "\t\tswitch (offset)" + NL + "\t\t{";
  protected final String TEXT_1428 = NL + "\t\t\tcase ";
  protected final String TEXT_1429 = " :" + NL + "\t\t\t\treturn ";
  protected final String TEXT_1430 = ";";
  protected final String TEXT_1431 = NL + "\t\t\tdefault :" + NL + "\t\t\t\tthrow new IndexOutOfBoundsException();" + NL + "\t\t}" + NL + "\t}" + NL + "" + NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */";
  protected final String TEXT_1432 = NL + "\t@Override";
  protected final String TEXT_1433 = NL + "\tprotected void eSetVirtualIndexBits(int offset, int newIndexBits)" + NL + "\t{" + NL + "\t\tswitch (offset)" + NL + "\t\t{";
  protected final String TEXT_1434 = NL + "\t\t\tcase ";
  protected final String TEXT_1435 = " :" + NL + "\t\t\t\t";
  protected final String TEXT_1436 = " = newIndexBits;" + NL + "\t\t\t\tbreak;";
  protected final String TEXT_1437 = NL + "\t\t\tdefault :" + NL + "\t\t\t\tthrow new IndexOutOfBoundsException();" + NL + "\t\t}" + NL + "\t}" + NL;
  protected final String TEXT_1438 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */";
  protected final String TEXT_1439 = NL + "\t@Override";
  protected final String TEXT_1440 = NL + "\tpublic String toString()" + NL + "\t{" + NL + "\t\tif (eIsProxy()) return super.toString();" + NL + "" + NL + "\t\tStringBuffer result = new StringBuffer(super.toString());";
  protected final String TEXT_1441 = NL + "\t\tresult.append(\" (";
  protected final String TEXT_1442 = ": \");";
  protected final String TEXT_1443 = NL + "\t\tresult.append(\", ";
  protected final String TEXT_1444 = ": \");";
  protected final String TEXT_1445 = NL + "\t\tif (eVirtualIsSet(";
  protected final String TEXT_1446 = ")) result.append(eVirtualGet(";
  protected final String TEXT_1447 = ")); else result.append(\"<unset>\");";
  protected final String TEXT_1448 = NL + "\t\tif (";
  protected final String TEXT_1449 = "(";
  protected final String TEXT_1450 = " & ";
  protected final String TEXT_1451 = "_ESETFLAG) != 0";
  protected final String TEXT_1452 = "ESet";
  protected final String TEXT_1453 = ") result.append((";
  protected final String TEXT_1454 = " & ";
  protected final String TEXT_1455 = "_EFLAG) != 0); else result.append(\"<unset>\");";
  protected final String TEXT_1456 = NL + "\t\tif (";
  protected final String TEXT_1457 = "(";
  protected final String TEXT_1458 = " & ";
  protected final String TEXT_1459 = "_ESETFLAG) != 0";
  protected final String TEXT_1460 = "ESet";
  protected final String TEXT_1461 = ") result.append(";
  protected final String TEXT_1462 = "_EFLAG_VALUES[(";
  protected final String TEXT_1463 = " & ";
  protected final String TEXT_1464 = "_EFLAG) >>> ";
  protected final String TEXT_1465 = "_EFLAG_OFFSET]); else result.append(\"<unset>\");";
  protected final String TEXT_1466 = NL + "\t\tif (";
  protected final String TEXT_1467 = "(";
  protected final String TEXT_1468 = " & ";
  protected final String TEXT_1469 = "_ESETFLAG) != 0";
  protected final String TEXT_1470 = "ESet";
  protected final String TEXT_1471 = ") result.append(";
  protected final String TEXT_1472 = "); else result.append(\"<unset>\");";
  protected final String TEXT_1473 = NL + "\t\tresult.append(eVirtualGet(";
  protected final String TEXT_1474 = ", ";
  protected final String TEXT_1475 = "));";
  protected final String TEXT_1476 = NL + "\t\tresult.append((";
  protected final String TEXT_1477 = " & ";
  protected final String TEXT_1478 = "_EFLAG) != 0);";
  protected final String TEXT_1479 = NL + "\t\tresult.append(";
  protected final String TEXT_1480 = "_EFLAG_VALUES[(";
  protected final String TEXT_1481 = " & ";
  protected final String TEXT_1482 = "_EFLAG) >>> ";
  protected final String TEXT_1483 = "_EFLAG_OFFSET]);";
  protected final String TEXT_1484 = NL + "\t\tresult.append(";
  protected final String TEXT_1485 = ");";
  protected final String TEXT_1486 = NL + "\t\tresult.append(')');" + NL + "\t\treturn result.toString();" + NL + "\t}" + NL;
  protected final String TEXT_1487 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */" + NL + "\tprotected int hash = -1;" + NL + "" + NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */" + NL + "\tpublic int getHash()" + NL + "\t{" + NL + "\t\tif (hash == -1)" + NL + "\t\t{" + NL + "\t\t\t";
  protected final String TEXT_1488 = " theKey = getKey();" + NL + "\t\t\thash = (theKey == null ? 0 : theKey.hashCode());" + NL + "\t\t}" + NL + "\t\treturn hash;" + NL + "\t}" + NL + "" + NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */" + NL + "\tpublic void setHash(int hash)" + NL + "\t{" + NL + "\t\tthis.hash = hash;" + NL + "\t}" + NL + "" + NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */" + NL + "\tpublic ";
  protected final String TEXT_1489 = " getKey()" + NL + "\t{";
  protected final String TEXT_1490 = NL + "\t\treturn new ";
  protected final String TEXT_1491 = "(getTypedKey());";
  protected final String TEXT_1492 = NL + "\t\treturn getTypedKey();";
  protected final String TEXT_1493 = NL + "\t}" + NL + "" + NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */" + NL + "\tpublic void setKey(";
  protected final String TEXT_1494 = " key)" + NL + "\t{";
  protected final String TEXT_1495 = NL + "\t\tgetTypedKey().addAll(";
  protected final String TEXT_1496 = "(";
  protected final String TEXT_1497 = ")";
  protected final String TEXT_1498 = "key);";
  protected final String TEXT_1499 = NL + "\t\tsetTypedKey(key);";
  protected final String TEXT_1500 = NL + "\t\tsetTypedKey(((";
  protected final String TEXT_1501 = ")key).";
  protected final String TEXT_1502 = "());";
  protected final String TEXT_1503 = NL + "\t\tsetTypedKey((";
  protected final String TEXT_1504 = ")key);";
  protected final String TEXT_1505 = NL + "\t}" + NL + "" + NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */" + NL + "\tpublic ";
  protected final String TEXT_1506 = " getValue()" + NL + "\t{";
  protected final String TEXT_1507 = NL + "\t\treturn new ";
  protected final String TEXT_1508 = "(getTypedValue());";
  protected final String TEXT_1509 = NL + "\t\treturn getTypedValue();";
  protected final String TEXT_1510 = NL + "\t}" + NL + "" + NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */" + NL + "\tpublic ";
  protected final String TEXT_1511 = " setValue(";
  protected final String TEXT_1512 = " value)" + NL + "\t{" + NL + "\t\t";
  protected final String TEXT_1513 = " oldValue = getValue();";
  protected final String TEXT_1514 = NL + "\t\tgetTypedValue().clear();" + NL + "\t\tgetTypedValue().addAll(";
  protected final String TEXT_1515 = "(";
  protected final String TEXT_1516 = ")";
  protected final String TEXT_1517 = "value);";
  protected final String TEXT_1518 = NL + "\t\tsetTypedValue(value);";
  protected final String TEXT_1519 = NL + "\t\tsetTypedValue(((";
  protected final String TEXT_1520 = ")value).";
  protected final String TEXT_1521 = "());";
  protected final String TEXT_1522 = NL + "\t\tsetTypedValue((";
  protected final String TEXT_1523 = ")value);";
  protected final String TEXT_1524 = NL + "\t\treturn oldValue;" + NL + "\t}" + NL + "" + NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */";
  protected final String TEXT_1525 = NL + "\t@SuppressWarnings(\"unchecked\")";
  protected final String TEXT_1526 = NL + "\tpublic ";
  protected final String TEXT_1527 = " getEMap()" + NL + "\t{" + NL + "\t\t";
  protected final String TEXT_1528 = " container = eContainer();" + NL + "\t\treturn container == null ? null : (";
  protected final String TEXT_1529 = ")container.eGet(eContainmentFeature());" + NL + "\t}" + NL;
  protected final String TEXT_1530 = NL + "} //";
  protected final String TEXT_1531 = NL;

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
    if (genClass.isFlag(genFeature)) { int flagIndex = genClass.getFlagIndex(genFeature);
    if (flagIndex > 31 && flagIndex % 32 == 0) {
    stringBuffer.append(TEXT_93);
    stringBuffer.append(genClass.getFlagsField(genFeature));
    stringBuffer.append(TEXT_94);
    }
    if (genFeature.isEnumType()) {
    stringBuffer.append(TEXT_95);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_96);
    stringBuffer.append(genFeature.getFormattedName());
    stringBuffer.append(TEXT_97);
    stringBuffer.append(genFeature.getFeatureKind());
    stringBuffer.append(TEXT_98);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_99);
    stringBuffer.append(flagIndex % 32);
    stringBuffer.append(TEXT_100);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_101);
    stringBuffer.append(genFeature.getFormattedName());
    stringBuffer.append(TEXT_102);
    stringBuffer.append(genFeature.getFeatureKind());
    stringBuffer.append(TEXT_103);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_104);
    if (genModel.getComplianceLevel().getValue() >= GenJDKLevel.JDK50) {
    stringBuffer.append(genFeature.getEDefault());
    stringBuffer.append(TEXT_105);
    } else {
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_106);
    stringBuffer.append(genFeature.getEDefault());
    stringBuffer.append(TEXT_107);
    }
    stringBuffer.append(TEXT_108);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_109);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_110);
    stringBuffer.append(genFeature.getTypeGenClassifier().getFormattedName());
    stringBuffer.append(TEXT_111);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_112);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_113);
    if (genModel.getComplianceLevel().getValue() >= GenJDKLevel.JDK50) {
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_114);
    } else {
    stringBuffer.append(TEXT_115);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_116);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_117);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_118);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_119);
    }
    stringBuffer.append(TEXT_120);
    }
    stringBuffer.append(TEXT_121);
    stringBuffer.append(genClass.getFlagSize(genFeature) > 1 ? "s" : "");
    stringBuffer.append(TEXT_122);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_123);
    stringBuffer.append(genFeature.getFormattedName());
    stringBuffer.append(TEXT_124);
    stringBuffer.append(genFeature.getFeatureKind());
    stringBuffer.append(TEXT_125);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_126);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_127);
    stringBuffer.append(genClass.getFlagMask(genFeature));
    stringBuffer.append(TEXT_128);
    if (genFeature.isEnumType()) {
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_129);
    } else {
    stringBuffer.append(flagIndex % 32);
    }
    stringBuffer.append(TEXT_130);
    } else {
    stringBuffer.append(TEXT_131);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_132);
    stringBuffer.append(genFeature.getFormattedName());
    stringBuffer.append(TEXT_133);
    stringBuffer.append(genFeature.getFeatureKind());
    stringBuffer.append(TEXT_134);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_135);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_136);
    stringBuffer.append(genFeature.getSafeName());
    if (genFeature.hasEDefault()) {
    stringBuffer.append(TEXT_137);
    stringBuffer.append(genFeature.getEDefault());
    }
    stringBuffer.append(TEXT_138);
    }
    }
    }
    if (genClass.isESetField(genFeature)) {
    if (genClass.isESetFlag(genFeature)) { int flagIndex = genClass.getESetFlagIndex(genFeature);
    if (flagIndex > 31 && flagIndex % 32 == 0) {
    stringBuffer.append(TEXT_139);
    stringBuffer.append(genClass.getESetFlagsField(genFeature));
    stringBuffer.append(TEXT_140);
    }
    stringBuffer.append(TEXT_141);
    stringBuffer.append(genFeature.getFormattedName());
    stringBuffer.append(TEXT_142);
    stringBuffer.append(genFeature.getFeatureKind());
    stringBuffer.append(TEXT_143);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_144);
    stringBuffer.append(flagIndex % 32 );
    stringBuffer.append(TEXT_145);
    } else {
    stringBuffer.append(TEXT_146);
    stringBuffer.append(genFeature.getFormattedName());
    stringBuffer.append(TEXT_147);
    stringBuffer.append(genFeature.getFeatureKind());
    stringBuffer.append(TEXT_148);
    stringBuffer.append(genFeature.getUncapName());
    stringBuffer.append(TEXT_149);
    }
    }
    //Class/declaredFieldGenFeature.override.javajetinc
    }
    }
    if (isImplementation && genClass.hasOffsetCorrection() && !genClass.getImplementedGenFeatures().isEmpty()) {
    stringBuffer.append(TEXT_150);
    stringBuffer.append(genClass.getOffsetCorrectionField(null));
    stringBuffer.append(TEXT_151);
    stringBuffer.append(genClass.getQualifiedClassifierAccessor());
    stringBuffer.append(TEXT_152);
    stringBuffer.append(genClass.getImplementedGenFeatures().get(0).getQualifiedFeatureAccessor());
    stringBuffer.append(TEXT_153);
    stringBuffer.append(genClass.getQualifiedFeatureID(genClass.getImplementedGenFeatures().get(0)));
    stringBuffer.append(TEXT_154);
    }
    if (isImplementation && genClass.hasOffsetCorrection() && !genModel.isReflectiveDelegation()) {
    for (GenFeature genFeature : genClass.getImplementedGenFeatures()) { GenFeature reverseFeature = genFeature.getReverse();
    if (reverseFeature != null && reverseFeature.getGenClass().hasOffsetCorrection()) {
    stringBuffer.append(TEXT_155);
    stringBuffer.append(genClass.getOffsetCorrectionField(genFeature));
    stringBuffer.append(TEXT_156);
    stringBuffer.append(reverseFeature.getGenClass().getQualifiedClassifierAccessor());
    stringBuffer.append(TEXT_157);
    stringBuffer.append(reverseFeature.getQualifiedFeatureAccessor());
    stringBuffer.append(TEXT_158);
    stringBuffer.append(reverseFeature.getGenClass().getQualifiedFeatureID(reverseFeature));
    stringBuffer.append(TEXT_159);
    }
    }
    }
    if (isImplementation) {
    stringBuffer.append(TEXT_160);
    if (genModel.isPublicConstructors()) {
    stringBuffer.append(TEXT_161);
    } else {
    stringBuffer.append(TEXT_162);
    }
    stringBuffer.append(TEXT_163);
    stringBuffer.append(genClass.getClassName());
    stringBuffer.append(TEXT_164);
    for (GenFeature genFeature : genClass.getFlagGenFeaturesWithDefault()) {
    stringBuffer.append(TEXT_165);
    stringBuffer.append(genClass.getFlagsField(genFeature));
    stringBuffer.append(TEXT_166);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_167);
    if (!genFeature.isBooleanType()) {
    stringBuffer.append(TEXT_168);
    }
    stringBuffer.append(TEXT_169);
    }
    stringBuffer.append(TEXT_170);
    if (genModel.useClassOverrideAnnotation()) {
    stringBuffer.append(TEXT_171);
    }
    stringBuffer.append(TEXT_172);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.EClass"));
    stringBuffer.append(TEXT_173);
    stringBuffer.append(genClass.getQualifiedClassifierAccessor());
    stringBuffer.append(TEXT_174);
    }
    if (isImplementation && genModel.getFeatureDelegation() == GenDelegationKind.REFLECTIVE_LITERAL && (genClass.getClassExtendsGenClass() == null || genClass.getClassExtendsGenClass().getGenModel().getFeatureDelegation() != GenDelegationKind.REFLECTIVE_LITERAL)) {
    stringBuffer.append(TEXT_175);
    if (genModel.useClassOverrideAnnotation()) {
    stringBuffer.append(TEXT_176);
    }
    stringBuffer.append(TEXT_177);
    stringBuffer.append(genClass.getClassExtendsGenClass() == null ? 0 : genClass.getClassExtendsGenClass().getAllGenFeatures().size());
    stringBuffer.append(TEXT_178);
    }
    //Class/reflectiveDelegation.override.javajetinc
    new Runnable() { public void run() {
    for (GenFeature genFeature : (isImplementation ? genClass.getImplementedGenFeatures() : genClass.getDeclaredGenFeatures())) {
    if (genModel.isArrayAccessors() && genFeature.isListType() && !genFeature.isFeatureMapType() && !genFeature.isMapType()) {
    stringBuffer.append(TEXT_179);
    if (!isImplementation) {
    stringBuffer.append(TEXT_180);
    stringBuffer.append(genFeature.getListItemType(genClass));
    stringBuffer.append(TEXT_181);
    stringBuffer.append(genFeature.getGetArrayAccessor());
    stringBuffer.append(TEXT_182);
    } else {
    if (genModel.useGenerics()) {
    stringBuffer.append(TEXT_183);
    }
    stringBuffer.append(TEXT_184);
    stringBuffer.append(genFeature.getListItemType(genClass));
    stringBuffer.append(TEXT_185);
    stringBuffer.append(genFeature.getGetArrayAccessor());
    stringBuffer.append(TEXT_186);
    if (genFeature.isVolatile()) {
    stringBuffer.append(TEXT_187);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.util.BasicEList"));
    stringBuffer.append(genFeature.getListTemplateArguments(genClass));
    stringBuffer.append(TEXT_188);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.util.BasicEList"));
    stringBuffer.append(genFeature.getListTemplateArguments(genClass));
    stringBuffer.append(TEXT_189);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_190);
    if (genModel.useGenerics() && !genFeature.getListItemType(genClass).contains("<") && !genFeature.getListItemType(null).equals(genFeature.getListItemType(genClass))) {
    stringBuffer.append(TEXT_191);
    stringBuffer.append(genFeature.getListItemType(genClass));
    stringBuffer.append(TEXT_192);
    }
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_193);
    } else {
    stringBuffer.append(TEXT_194);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_195);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_196);
    if (genModel.useGenerics() && !genFeature.getListItemType(genClass).contains("<") && !genFeature.getListItemType(null).equals(genFeature.getListItemType(genClass))) {
    stringBuffer.append(TEXT_197);
    stringBuffer.append(genFeature.getListItemType(genClass));
    stringBuffer.append(TEXT_198);
    }
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_199);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.util.BasicEList"));
    stringBuffer.append(genFeature.getListTemplateArguments(genClass));
    stringBuffer.append(TEXT_200);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.util.BasicEList"));
    stringBuffer.append(genFeature.getListTemplateArguments(genClass));
    stringBuffer.append(TEXT_201);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_202);
    }
    stringBuffer.append(TEXT_203);
    stringBuffer.append(genFeature.getListItemType(genClass));
    stringBuffer.append(TEXT_204);
    }
    stringBuffer.append(TEXT_205);
    if (!isImplementation) {
    stringBuffer.append(TEXT_206);
    stringBuffer.append(genFeature.getListItemType(genClass));
    stringBuffer.append(TEXT_207);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_208);
    } else {
    stringBuffer.append(TEXT_209);
    stringBuffer.append(genFeature.getListItemType(genClass));
    stringBuffer.append(TEXT_210);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_211);
    if (!genModel.useGenerics()) {
    stringBuffer.append(TEXT_212);
    stringBuffer.append(genFeature.getListItemType(genClass));
    stringBuffer.append(TEXT_213);
    }
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_214);
    }
    stringBuffer.append(TEXT_215);
    if (!isImplementation) {
    stringBuffer.append(TEXT_216);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_217);
    } else {
    stringBuffer.append(TEXT_218);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_219);
    if (genFeature.isVolatile()) {
    stringBuffer.append(TEXT_220);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_221);
    } else {
    stringBuffer.append(TEXT_222);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_223);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_224);
    }
    stringBuffer.append(TEXT_225);
    }
    stringBuffer.append(TEXT_226);
    if (!isImplementation) {
    stringBuffer.append(TEXT_227);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_228);
    stringBuffer.append(genFeature.getListItemType(genClass));
    stringBuffer.append(TEXT_229);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_230);
    } else {
    stringBuffer.append(TEXT_231);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_232);
    stringBuffer.append(genFeature.getListItemType(genClass));
    stringBuffer.append(TEXT_233);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_234);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.util.BasicEList"));
    stringBuffer.append(genFeature.getListTemplateArguments(genClass));
    stringBuffer.append(TEXT_235);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_236);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_237);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_238);
    }
    stringBuffer.append(TEXT_239);
    if (!isImplementation) {
    stringBuffer.append(TEXT_240);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_241);
    stringBuffer.append(genFeature.getListItemType(genClass));
    stringBuffer.append(TEXT_242);
    } else {
    stringBuffer.append(TEXT_243);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_244);
    stringBuffer.append(genFeature.getListItemType(genClass));
    stringBuffer.append(TEXT_245);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_246);
    }
    }
    if (genFeature.isGet() && (isImplementation || !genFeature.isSuppressedGetVisibility())) {
    if (isInterface) {
    stringBuffer.append(TEXT_247);
    stringBuffer.append(genFeature.getFormattedName());
    stringBuffer.append(TEXT_248);
    stringBuffer.append(genFeature.getFeatureKind());
    stringBuffer.append(TEXT_249);
    if (genFeature.isListType()) {
    if (genFeature.isMapType()) { GenFeature keyFeature = genFeature.getMapEntryTypeGenClass().getMapEntryKeyFeature(); GenFeature valueFeature = genFeature.getMapEntryTypeGenClass().getMapEntryValueFeature(); 
    stringBuffer.append(TEXT_250);
    if (keyFeature.isListType()) {
    stringBuffer.append(TEXT_251);
    stringBuffer.append(keyFeature.getQualifiedListItemType(genClass));
    stringBuffer.append(TEXT_252);
    } else {
    stringBuffer.append(TEXT_253);
    stringBuffer.append(keyFeature.getType(genClass));
    stringBuffer.append(TEXT_254);
    }
    stringBuffer.append(TEXT_255);
    if (valueFeature.isListType()) {
    stringBuffer.append(TEXT_256);
    stringBuffer.append(valueFeature.getQualifiedListItemType(genClass));
    stringBuffer.append(TEXT_257);
    } else {
    stringBuffer.append(TEXT_258);
    stringBuffer.append(valueFeature.getType(genClass));
    stringBuffer.append(TEXT_259);
    }
    stringBuffer.append(TEXT_260);
    } else if (!genFeature.isWrappedFeatureMapType() && !(genModel.isSuppressEMFMetaData() && "org.eclipse.emf.ecore.EObject".equals(genFeature.getQualifiedListItemType(genClass)))) {
String typeName = genFeature.getQualifiedListItemType(genClass); String head = typeName; String tail = ""; int index = typeName.indexOf('<'); if (index == -1) { index = typeName.indexOf('['); } 
if (index != -1) { head = typeName.substring(0, index); tail = typeName.substring(index).replaceAll("<", "&lt;"); }

    stringBuffer.append(TEXT_261);
    stringBuffer.append(head);
    stringBuffer.append(TEXT_262);
    stringBuffer.append(tail);
    stringBuffer.append(TEXT_263);
    }
    } else if (genFeature.isSetDefaultValue()) {
    stringBuffer.append(TEXT_264);
    stringBuffer.append(genFeature.getDefaultValue());
    stringBuffer.append(TEXT_265);
    }
    if (genFeature.getTypeGenEnum() != null) {
    stringBuffer.append(TEXT_266);
    stringBuffer.append(genFeature.getTypeGenEnum().getQualifiedName());
    stringBuffer.append(TEXT_267);
    }
    if (genFeature.isBidirectional() && !genFeature.getReverse().getGenClass().isMapEntry()) { GenFeature reverseGenFeature = genFeature.getReverse(); 
    if (!reverseGenFeature.isSuppressedGetVisibility()) {
    stringBuffer.append(TEXT_268);
    stringBuffer.append(reverseGenFeature.getGenClass().getQualifiedInterfaceName());
    stringBuffer.append(TEXT_269);
    stringBuffer.append(reverseGenFeature.getGetAccessor());
    stringBuffer.append(TEXT_270);
    stringBuffer.append(reverseGenFeature.getFormattedName());
    stringBuffer.append(TEXT_271);
    }
    }
    stringBuffer.append(TEXT_272);
    if (!genFeature.hasDocumentation()) {
    stringBuffer.append(TEXT_273);
    stringBuffer.append(genFeature.getFormattedName());
    stringBuffer.append(TEXT_274);
    stringBuffer.append(genFeature.getFeatureKind());
    stringBuffer.append(TEXT_275);
    }
    stringBuffer.append(TEXT_276);
    if (genFeature.hasDocumentation()) {
    stringBuffer.append(TEXT_277);
    stringBuffer.append(genFeature.getDocumentation(genModel.getIndentation(stringBuffer)));
    stringBuffer.append(TEXT_278);
    }
    stringBuffer.append(TEXT_279);
    stringBuffer.append(genFeature.getFormattedName());
    stringBuffer.append(TEXT_280);
    stringBuffer.append(genFeature.getFeatureKind());
    stringBuffer.append(TEXT_281);
    if (genFeature.getTypeGenEnum() != null) {
    stringBuffer.append(TEXT_282);
    stringBuffer.append(genFeature.getTypeGenEnum().getQualifiedName());
    }
    if (genFeature.isUnsettable()) {
    if (!genFeature.isSuppressedIsSetVisibility()) {
    stringBuffer.append(TEXT_283);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_284);
    }
    if (genFeature.isChangeable() && !genFeature.isSuppressedUnsetVisibility()) {
    stringBuffer.append(TEXT_285);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_286);
    }
    }
    if (genFeature.isChangeable() && !genFeature.isListType() && !genFeature.isSuppressedSetVisibility()) {
    stringBuffer.append(TEXT_287);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_288);
    stringBuffer.append(genFeature.getRawImportedBoundType());
    stringBuffer.append(TEXT_289);
    }
    if (!genModel.isSuppressEMFMetaData()) {
    stringBuffer.append(TEXT_290);
    stringBuffer.append(genPackage.getQualifiedPackageInterfaceName());
    stringBuffer.append(TEXT_291);
    stringBuffer.append(genFeature.getFeatureAccessorName());
    stringBuffer.append(TEXT_292);
    }
    if (genFeature.isBidirectional() && !genFeature.getReverse().getGenClass().isMapEntry()) { GenFeature reverseGenFeature = genFeature.getReverse(); 
    if (!reverseGenFeature.isSuppressedGetVisibility()) {
    stringBuffer.append(TEXT_293);
    stringBuffer.append(reverseGenFeature.getGenClass().getQualifiedInterfaceName());
    stringBuffer.append(TEXT_294);
    stringBuffer.append(reverseGenFeature.getGetAccessor());
    }
    }
    if (!genModel.isSuppressEMFModelTags()) { boolean first = true; for (StringTokenizer stringTokenizer = new StringTokenizer(genFeature.getModelInfo(), "\n\r"); stringTokenizer.hasMoreTokens(); ) { String modelInfo = stringTokenizer.nextToken(); if (first) { first = false;
    stringBuffer.append(TEXT_295);
    stringBuffer.append(modelInfo);
    } else {
    stringBuffer.append(TEXT_296);
    stringBuffer.append(modelInfo);
    }} if (first) {
    stringBuffer.append(TEXT_297);
    }}
    stringBuffer.append(TEXT_298);
    //Class/getGenFeature.javadoc.override.javajetinc
    } else {
    stringBuffer.append(TEXT_299);
    if (genModel.getComplianceLevel().getValue() >= GenJDKLevel.JDK50) { //Class/getGenFeature.annotations.insert.javajetinc
    }
    }
    if (!isImplementation) {
    stringBuffer.append(TEXT_300);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_301);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_302);
    } else {
    if (genModel.useGenerics() && ((genFeature.isContainer() || genFeature.isResolveProxies()) && !genFeature.isListType() && !genModel.isReflectiveDelegation() && genFeature.isUncheckedCast(genClass) || genFeature.isListType() && (genModel.isReflectiveDelegation() || genModel.isVirtualDelegation()) || genFeature.isListDataType() && genFeature.hasDelegateFeature())) {
    stringBuffer.append(TEXT_303);
    }
    stringBuffer.append(TEXT_304);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_305);
    stringBuffer.append(genFeature.getGetAccessor());
    if (genClass.hasCollidingGetAccessorOperation(genFeature)) {
    stringBuffer.append(TEXT_306);
    }
    stringBuffer.append(TEXT_307);
    if (genModel.isReflectiveDelegation()) {
    stringBuffer.append(TEXT_308);
    if (genFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_309);
    }
    stringBuffer.append(TEXT_310);
    stringBuffer.append(genFeature.getObjectType(genClass));
    stringBuffer.append(TEXT_311);
    stringBuffer.append(genFeature.getQualifiedFeatureAccessor());
    stringBuffer.append(TEXT_312);
    if (genFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_313);
    stringBuffer.append(genFeature.getPrimitiveValueFunction());
    stringBuffer.append(TEXT_314);
    }
    stringBuffer.append(TEXT_315);
    } else if (!genFeature.isVolatile()) {
    if (genFeature.isListType()) {
    if (genModel.isVirtualDelegation()) {
    stringBuffer.append(TEXT_316);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_317);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_318);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_319);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(positiveOffsetCorrection);
    stringBuffer.append(TEXT_320);
    }
    stringBuffer.append(TEXT_321);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_322);
    if (genModel.isVirtualDelegation()) {
    stringBuffer.append(TEXT_323);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(positiveOffsetCorrection);
    stringBuffer.append(TEXT_324);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_325);
    stringBuffer.append(genClass.getListConstructor(genFeature));
    stringBuffer.append(TEXT_326);
    } else {
    stringBuffer.append(TEXT_327);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_328);
    stringBuffer.append(genClass.getListConstructor(genFeature));
    stringBuffer.append(TEXT_329);
    }
    stringBuffer.append(TEXT_330);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(genFeature.isMapType() && genFeature.isEffectiveSuppressEMFTypes() ? ".map()" : "");
    stringBuffer.append(TEXT_331);
    } else if (genFeature.isContainer()) {
    stringBuffer.append(TEXT_332);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(positiveOffsetCorrection);
    stringBuffer.append(TEXT_333);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_334);
    } else {
    if (genFeature.isResolveProxies()) {
    if (genModel.isVirtualDelegation()) {
    stringBuffer.append(TEXT_335);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_336);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_337);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_338);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(positiveOffsetCorrection);
    if (genFeature.hasEDefault()) {
    stringBuffer.append(TEXT_339);
    stringBuffer.append(genFeature.getEDefault());
    }
    stringBuffer.append(TEXT_340);
    }
    stringBuffer.append(TEXT_341);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_342);
    stringBuffer.append(genFeature.getSafeNameAsEObject());
    stringBuffer.append(TEXT_343);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.InternalEObject"));
    stringBuffer.append(TEXT_344);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_345);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.InternalEObject"));
    stringBuffer.append(TEXT_346);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_347);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_348);
    stringBuffer.append(genFeature.getNonEObjectInternalTypeCast(genClass));
    stringBuffer.append(TEXT_349);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_350);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_351);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_352);
    if (genFeature.isEffectiveContains()) {
    stringBuffer.append(TEXT_353);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.InternalEObject"));
    stringBuffer.append(TEXT_354);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_355);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.InternalEObject"));
    stringBuffer.append(TEXT_356);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_357);
    if (!genFeature.isBidirectional()) {
    stringBuffer.append(TEXT_358);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.notify.NotificationChain"));
    stringBuffer.append(TEXT_359);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_360);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(negativeOffsetCorrection);
    stringBuffer.append(TEXT_361);
    } else { GenFeature reverseFeature = genFeature.getReverse(); GenClass targetClass = reverseFeature.getGenClass(); String reverseOffsetCorrection = targetClass.hasOffsetCorrection() ? " + " + genClass.getOffsetCorrectionField(reverseFeature) : "";
    stringBuffer.append(TEXT_362);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.notify.NotificationChain"));
    stringBuffer.append(TEXT_363);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_364);
    stringBuffer.append(targetClass.getQualifiedFeatureID(reverseFeature));
    stringBuffer.append(reverseOffsetCorrection);
    stringBuffer.append(TEXT_365);
    stringBuffer.append(targetClass.getRawImportedInterfaceName());
    stringBuffer.append(TEXT_366);
    }
    stringBuffer.append(TEXT_367);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_368);
    if (!genFeature.isBidirectional()) {
    stringBuffer.append(TEXT_369);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_370);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(negativeOffsetCorrection);
    stringBuffer.append(TEXT_371);
    } else { GenFeature reverseFeature = genFeature.getReverse(); GenClass targetClass = reverseFeature.getGenClass(); String reverseOffsetCorrection = targetClass.hasOffsetCorrection() ? " + " + genClass.getOffsetCorrectionField(reverseFeature) : "";
    stringBuffer.append(TEXT_372);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_373);
    stringBuffer.append(targetClass.getQualifiedFeatureID(reverseFeature));
    stringBuffer.append(reverseOffsetCorrection);
    stringBuffer.append(TEXT_374);
    stringBuffer.append(targetClass.getRawImportedInterfaceName());
    stringBuffer.append(TEXT_375);
    }
    stringBuffer.append(TEXT_376);
    } else if (genModel.isVirtualDelegation()) {
    stringBuffer.append(TEXT_377);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(positiveOffsetCorrection);
    stringBuffer.append(TEXT_378);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_379);
    }
    if (!genModel.isSuppressNotification()) {
    stringBuffer.append(TEXT_380);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.impl.ENotificationImpl"));
    stringBuffer.append(TEXT_381);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.notify.Notification"));
    stringBuffer.append(TEXT_382);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(positiveOffsetCorrection);
    stringBuffer.append(TEXT_383);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_384);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_385);
    }
    stringBuffer.append(TEXT_386);
    }
    if (!genFeature.isResolveProxies() && genModel.isVirtualDelegation() && !genFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_387);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_388);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(positiveOffsetCorrection);
    if (genFeature.hasEDefault()) {
    stringBuffer.append(TEXT_389);
    stringBuffer.append(genFeature.getEDefault());
    }
    stringBuffer.append(TEXT_390);
    } else if (genClass.isFlag(genFeature)) {
    if (genFeature.isBooleanType()) {
    stringBuffer.append(TEXT_391);
    stringBuffer.append(genClass.getFlagsField(genFeature));
    stringBuffer.append(TEXT_392);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_393);
    } else {
    stringBuffer.append(TEXT_394);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_395);
    stringBuffer.append(genClass.getFlagsField(genFeature));
    stringBuffer.append(TEXT_396);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_397);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_398);
    }
    } else {
    stringBuffer.append(TEXT_399);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_400);
    }
    }
    } else {//volatile
    if (genFeature.isResolveProxies() && !genFeature.isListType()) {
    stringBuffer.append(TEXT_401);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_402);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_403);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_404);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_405);
    stringBuffer.append(genFeature.getSafeNameAsEObject());
    stringBuffer.append(TEXT_406);
    stringBuffer.append(genFeature.getNonEObjectInternalTypeCast(genClass));
    stringBuffer.append(TEXT_407);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.InternalEObject"));
    stringBuffer.append(TEXT_408);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_409);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_410);
    } else if (genFeature.hasDelegateFeature()) { GenFeature delegateFeature = genFeature.getDelegateFeature();
    if (genFeature.isFeatureMapType()) {
    String featureMapEntryTemplateArgument = genModel.getComplianceLevel().getValue() >= GenJDKLevel.JDK50 ? "<" + genModel.getImportedName("org.eclipse.emf.ecore.util.FeatureMap") + ".Entry>" : "";
    if (delegateFeature.isWrappedFeatureMapType()) {
    stringBuffer.append(TEXT_411);
    stringBuffer.append(genFeature.getImportedEffectiveFeatureMapWrapperClass());
    stringBuffer.append(TEXT_412);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.FeatureMap"));
    stringBuffer.append(TEXT_413);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.FeatureMap"));
    stringBuffer.append(TEXT_414);
    stringBuffer.append(delegateFeature.getAccessorName());
    stringBuffer.append(TEXT_415);
    stringBuffer.append(featureMapEntryTemplateArgument);
    stringBuffer.append(TEXT_416);
    stringBuffer.append(genFeature.getQualifiedFeatureAccessor());
    stringBuffer.append(TEXT_417);
    } else {
    stringBuffer.append(TEXT_418);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.FeatureMap"));
    stringBuffer.append(TEXT_419);
    stringBuffer.append(delegateFeature.getAccessorName());
    stringBuffer.append(TEXT_420);
    stringBuffer.append(featureMapEntryTemplateArgument);
    stringBuffer.append(TEXT_421);
    stringBuffer.append(genFeature.getQualifiedFeatureAccessor());
    stringBuffer.append(TEXT_422);
    }
    } else if (genFeature.isListType()) {
    if (delegateFeature.isWrappedFeatureMapType()) {
    stringBuffer.append(TEXT_423);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.FeatureMap"));
    stringBuffer.append(TEXT_424);
    stringBuffer.append(delegateFeature.getAccessorName());
    stringBuffer.append(TEXT_425);
    stringBuffer.append(genFeature.getQualifiedFeatureAccessor());
    stringBuffer.append(TEXT_426);
    } else {
    stringBuffer.append(TEXT_427);
    stringBuffer.append(delegateFeature.getAccessorName());
    stringBuffer.append(TEXT_428);
    stringBuffer.append(genFeature.getQualifiedFeatureAccessor());
    stringBuffer.append(TEXT_429);
    }
    } else {
    if (delegateFeature.isWrappedFeatureMapType()) {
    stringBuffer.append(TEXT_430);
    if (genFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_431);
    }
    if (genFeature.getTypeGenDataType() == null || !genFeature.getTypeGenDataType().isObjectType()) {
    stringBuffer.append(TEXT_432);
    stringBuffer.append(genFeature.getObjectType(genClass));
    stringBuffer.append(TEXT_433);
    }
    stringBuffer.append(TEXT_434);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.FeatureMap"));
    stringBuffer.append(TEXT_435);
    stringBuffer.append(delegateFeature.getAccessorName());
    stringBuffer.append(TEXT_436);
    stringBuffer.append(genFeature.getQualifiedFeatureAccessor());
    stringBuffer.append(TEXT_437);
    if (genFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_438);
    stringBuffer.append(genFeature.getPrimitiveValueFunction());
    stringBuffer.append(TEXT_439);
    }
    stringBuffer.append(TEXT_440);
    } else {
    stringBuffer.append(TEXT_441);
    if (genFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_442);
    }
    if (genFeature.getTypeGenDataType() == null || !genFeature.getTypeGenDataType().isObjectType()) {
    stringBuffer.append(TEXT_443);
    stringBuffer.append(genFeature.getObjectType(genClass));
    stringBuffer.append(TEXT_444);
    }
    stringBuffer.append(TEXT_445);
    stringBuffer.append(delegateFeature.getAccessorName());
    stringBuffer.append(TEXT_446);
    stringBuffer.append(genFeature.getQualifiedFeatureAccessor());
    stringBuffer.append(TEXT_447);
    if (genFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_448);
    stringBuffer.append(genFeature.getPrimitiveValueFunction());
    stringBuffer.append(TEXT_449);
    }
    stringBuffer.append(TEXT_450);
    }
    }
    } else if (genClass.getGetAccessorOperation(genFeature) != null) {
    stringBuffer.append(TEXT_451);
    stringBuffer.append(genClass.getGetAccessorOperation(genFeature).getBody(genModel.getIndentation(stringBuffer)));
    } else {
    stringBuffer.append(TEXT_452);
    stringBuffer.append(genFeature.getFormattedName());
    stringBuffer.append(TEXT_453);
    stringBuffer.append(genFeature.getFeatureKind());
    stringBuffer.append(TEXT_454);
    if (genFeature.isListType()) {
    stringBuffer.append(TEXT_455);
    if (genFeature.isMapType()) {
    stringBuffer.append(TEXT_456);
    } else if (genFeature.isFeatureMapType()) {
    stringBuffer.append(TEXT_457);
    } else {
    stringBuffer.append(TEXT_458);
    }
    stringBuffer.append(TEXT_459);
    }
    stringBuffer.append(TEXT_460);
    //Class/getGenFeature.todo.override.javajetinc
    }
    }
    stringBuffer.append(TEXT_461);
    }
    //Class/getGenFeature.override.javajetinc
    }
    if (isImplementation && !genModel.isReflectiveDelegation() && genFeature.isBasicGet()) {
    stringBuffer.append(TEXT_462);
    if (genModel.getComplianceLevel().getValue() >= GenJDKLevel.JDK50) { //Class/basicGetGenFeature.annotations.insert.javajetinc
    }
    stringBuffer.append(TEXT_463);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_464);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_465);
    if (genFeature.isContainer()) {
    stringBuffer.append(TEXT_466);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(positiveOffsetCorrection);
    stringBuffer.append(TEXT_467);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_468);
    } else if (!genFeature.isVolatile()) {
    if (genModel.isVirtualDelegation()) {
    stringBuffer.append(TEXT_469);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_470);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(positiveOffsetCorrection);
    stringBuffer.append(TEXT_471);
    } else {
    stringBuffer.append(TEXT_472);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_473);
    }
    } else if (genFeature.hasDelegateFeature()) { GenFeature delegateFeature = genFeature.getDelegateFeature();
    if (delegateFeature.isWrappedFeatureMapType()) {
    stringBuffer.append(TEXT_474);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_475);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.FeatureMap"));
    stringBuffer.append(TEXT_476);
    stringBuffer.append(delegateFeature.getAccessorName());
    stringBuffer.append(TEXT_477);
    stringBuffer.append(genFeature.getQualifiedFeatureAccessor());
    stringBuffer.append(TEXT_478);
    } else {
    stringBuffer.append(TEXT_479);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_480);
    stringBuffer.append(delegateFeature.getAccessorName());
    stringBuffer.append(TEXT_481);
    stringBuffer.append(genFeature.getQualifiedFeatureAccessor());
    stringBuffer.append(TEXT_482);
    }
    } else {
    stringBuffer.append(TEXT_483);
    stringBuffer.append(genFeature.getFormattedName());
    stringBuffer.append(TEXT_484);
    stringBuffer.append(genFeature.getFeatureKind());
    stringBuffer.append(TEXT_485);
    //Class/basicGetGenFeature.todo.override.javajetinc
    }
    stringBuffer.append(TEXT_486);
    //Class/basicGetGenFeature.override.javajetinc
    }
    if (isImplementation && !genModel.isReflectiveDelegation() && genFeature.isBasicSet()) {
    stringBuffer.append(TEXT_487);
    if (genModel.getComplianceLevel().getValue() >= GenJDKLevel.JDK50) { //Class/basicSetGenFeature.annotations.insert.javajetinc
    }
    stringBuffer.append(TEXT_488);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.notify.NotificationChain"));
    stringBuffer.append(TEXT_489);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_490);
    stringBuffer.append(genFeature.getImportedInternalType(genClass));
    stringBuffer.append(TEXT_491);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_492);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.notify.NotificationChain"));
    stringBuffer.append(TEXT_493);
    if (genFeature.isContainer()) {
    stringBuffer.append(TEXT_494);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.InternalEObject"));
    stringBuffer.append(TEXT_495);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_496);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(positiveOffsetCorrection);
    stringBuffer.append(TEXT_497);
    stringBuffer.append(TEXT_498);
    } else if (!genFeature.isVolatile()) {
    if (genModel.isVirtualDelegation()) {
    stringBuffer.append(TEXT_499);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_500);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(positiveOffsetCorrection);
    stringBuffer.append(TEXT_501);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_502);
    } else {
    stringBuffer.append(TEXT_503);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_504);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_505);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_506);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_507);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_508);
    }
    if (genFeature.isUnsettable()) {
    if (genModel.isVirtualDelegation()) {
    if (!genModel.isSuppressNotification()) {
    stringBuffer.append(TEXT_509);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_510);
    }
    } else if (genClass.isESetFlag(genFeature)) {
    stringBuffer.append(TEXT_511);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_512);
    stringBuffer.append(genClass.getESetFlagsField(genFeature));
    stringBuffer.append(TEXT_513);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_514);
    if (!genModel.isSuppressNotification()) {
    stringBuffer.append(TEXT_515);
    stringBuffer.append(genClass.getESetFlagsField(genFeature));
    stringBuffer.append(TEXT_516);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_517);
    }
    } else {
    if (!genModel.isSuppressNotification()) {
    stringBuffer.append(TEXT_518);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_519);
    stringBuffer.append(genFeature.getUncapName());
    stringBuffer.append(TEXT_520);
    }
    stringBuffer.append(TEXT_521);
    stringBuffer.append(genFeature.getUncapName());
    stringBuffer.append(TEXT_522);
    }
    }
    if (!genModel.isSuppressNotification()) {
    stringBuffer.append(TEXT_523);
    if (genFeature.isUnsettable()) {
    stringBuffer.append(TEXT_524);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.impl.ENotificationImpl"));
    stringBuffer.append(TEXT_525);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.impl.ENotificationImpl"));
    stringBuffer.append(TEXT_526);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.notify.Notification"));
    stringBuffer.append(TEXT_527);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(positiveOffsetCorrection);
    stringBuffer.append(TEXT_528);
    if (genModel.isVirtualDelegation()) {
    stringBuffer.append(TEXT_529);
    stringBuffer.append(genFeature.getCapName());
    } else {
    stringBuffer.append(TEXT_530);
    stringBuffer.append(genFeature.getCapName());
    }
    stringBuffer.append(TEXT_531);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_532);
    if (genModel.isVirtualDelegation()) {
    stringBuffer.append(TEXT_533);
    } else {
    stringBuffer.append(TEXT_534);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_535);
    }
    stringBuffer.append(TEXT_536);
    } else {
    stringBuffer.append(TEXT_537);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.impl.ENotificationImpl"));
    stringBuffer.append(TEXT_538);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.impl.ENotificationImpl"));
    stringBuffer.append(TEXT_539);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.notify.Notification"));
    stringBuffer.append(TEXT_540);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(positiveOffsetCorrection);
    stringBuffer.append(TEXT_541);
    if (genModel.isVirtualDelegation()) {
    stringBuffer.append(TEXT_542);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_543);
    stringBuffer.append(genFeature.getCapName());
    } else {
    stringBuffer.append(TEXT_544);
    stringBuffer.append(genFeature.getCapName());
    }
    stringBuffer.append(TEXT_545);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_546);
    }
    stringBuffer.append(TEXT_547);
    }
    stringBuffer.append(TEXT_548);
    } else if (genFeature.hasDelegateFeature()) { GenFeature delegateFeature = genFeature.getDelegateFeature();
    if (delegateFeature.isWrappedFeatureMapType()) {
    stringBuffer.append(TEXT_549);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.FeatureMap"));
    stringBuffer.append(TEXT_550);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.FeatureMap"));
    stringBuffer.append(TEXT_551);
    stringBuffer.append(delegateFeature.getAccessorName());
    stringBuffer.append(TEXT_552);
    stringBuffer.append(genFeature.getQualifiedFeatureAccessor());
    stringBuffer.append(TEXT_553);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_554);
    } else {
    stringBuffer.append(TEXT_555);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.FeatureMap"));
    stringBuffer.append(TEXT_556);
    stringBuffer.append(delegateFeature.getAccessorName());
    stringBuffer.append(TEXT_557);
    stringBuffer.append(genFeature.getQualifiedFeatureAccessor());
    stringBuffer.append(TEXT_558);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_559);
    }
    } else {
    stringBuffer.append(TEXT_560);
    stringBuffer.append(genFeature.getFormattedName());
    stringBuffer.append(TEXT_561);
    stringBuffer.append(genFeature.getFeatureKind());
    stringBuffer.append(TEXT_562);
    //Class/basicSetGenFeature.todo.override.javajetinc
    }
    stringBuffer.append(TEXT_563);
    //Class/basicSetGenFeature.override.javajetinc
    }
    if (genFeature.isSet() && (isImplementation || !genFeature.isSuppressedSetVisibility())) {
    if (isInterface) { 
    stringBuffer.append(TEXT_564);
    stringBuffer.append(genClass.getQualifiedInterfaceName());
    stringBuffer.append(TEXT_565);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_566);
    stringBuffer.append(genFeature.getFormattedName());
    stringBuffer.append(TEXT_567);
    stringBuffer.append(genFeature.getFeatureKind());
    stringBuffer.append(TEXT_568);
    stringBuffer.append(TEXT_569);
    stringBuffer.append(genFeature.getFormattedName());
    stringBuffer.append(TEXT_570);
    stringBuffer.append(genFeature.getFeatureKind());
    stringBuffer.append(TEXT_571);
    if (genFeature.isEnumType()) {
    stringBuffer.append(TEXT_572);
    stringBuffer.append(genFeature.getTypeGenEnum().getQualifiedName());
    }
    if (genFeature.isUnsettable()) {
    if (!genFeature.isSuppressedIsSetVisibility()) {
    stringBuffer.append(TEXT_573);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_574);
    }
    if (!genFeature.isSuppressedUnsetVisibility()) {
    stringBuffer.append(TEXT_575);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_576);
    }
    }
    stringBuffer.append(TEXT_577);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_578);
    //Class/setGenFeature.javadoc.override.javajetinc
    } else {
    stringBuffer.append(TEXT_579);
    if (genModel.getComplianceLevel().getValue() >= GenJDKLevel.JDK50) { //Class/setGenFeature.annotations.insert.javajetinc
    }
    }
    if (!isImplementation) { 
    stringBuffer.append(TEXT_580);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_581);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_582);
    } else { GenOperation setAccessorOperation = genClass.getSetAccessorOperation(genFeature);
    stringBuffer.append(TEXT_583);
    stringBuffer.append(genFeature.getAccessorName());
    if (genClass.hasCollidingSetAccessorOperation(genFeature)) {
    stringBuffer.append(TEXT_584);
    }
    stringBuffer.append(TEXT_585);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_586);
    stringBuffer.append(setAccessorOperation == null ? "new" + genFeature.getCapName() : setAccessorOperation.getGenParameters().get(0).getName());
    stringBuffer.append(TEXT_587);
    if (genModel.isReflectiveDelegation()) {
    stringBuffer.append(TEXT_588);
    stringBuffer.append(genFeature.getQualifiedFeatureAccessor());
    stringBuffer.append(TEXT_589);
    if (genFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_590);
    stringBuffer.append(genFeature.getObjectType(genClass));
    stringBuffer.append(TEXT_591);
    }
    stringBuffer.append(TEXT_592);
    stringBuffer.append(genFeature.getCapName());
    if (genFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_593);
    }
    stringBuffer.append(TEXT_594);
    } else if (!genFeature.isVolatile()) {
    if (genFeature.isContainer()) { GenFeature reverseFeature = genFeature.getReverse(); GenClass targetClass = reverseFeature.getGenClass(); String reverseOffsetCorrection = targetClass.hasOffsetCorrection() ? " + " + genClass.getOffsetCorrectionField(reverseFeature) : "";
    stringBuffer.append(TEXT_595);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_596);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(positiveOffsetCorrection);
    stringBuffer.append(TEXT_597);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_598);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.EcoreUtil"));
    stringBuffer.append(TEXT_599);
    stringBuffer.append(genFeature.getEObjectCast());
    stringBuffer.append(TEXT_600);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_601);
    stringBuffer.append(genModel.getImportedName("java.lang.IllegalArgumentException"));
    stringBuffer.append(TEXT_602);
    stringBuffer.append(genModel.getNonNLS());
    stringBuffer.append(TEXT_603);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.notify.NotificationChain"));
    stringBuffer.append(TEXT_604);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_605);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.InternalEObject"));
    stringBuffer.append(TEXT_606);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_607);
    stringBuffer.append(targetClass.getQualifiedFeatureID(reverseFeature));
    stringBuffer.append(reverseOffsetCorrection);
    stringBuffer.append(TEXT_608);
    stringBuffer.append(targetClass.getRawImportedInterfaceName());
    stringBuffer.append(TEXT_609);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_610);
    stringBuffer.append(genFeature.getInternalTypeCast());
    stringBuffer.append(TEXT_611);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_612);
    if (!genModel.isSuppressNotification()) {
    stringBuffer.append(TEXT_613);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.impl.ENotificationImpl"));
    stringBuffer.append(TEXT_614);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.notify.Notification"));
    stringBuffer.append(TEXT_615);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(positiveOffsetCorrection);
    stringBuffer.append(TEXT_616);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_617);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_618);
    }
    } else if (genFeature.isBidirectional() || genFeature.isEffectiveContains()) {
    if (genModel.isVirtualDelegation()) {
    stringBuffer.append(TEXT_619);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_620);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_621);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_622);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(positiveOffsetCorrection);
    stringBuffer.append(TEXT_623);
    }
    stringBuffer.append(TEXT_624);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_625);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_626);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.notify.NotificationChain"));
    stringBuffer.append(TEXT_627);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_628);
    if (!genFeature.isBidirectional()) {
    stringBuffer.append(TEXT_629);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.InternalEObject"));
    stringBuffer.append(TEXT_630);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_631);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(negativeOffsetCorrection);
    stringBuffer.append(TEXT_632);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_633);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.InternalEObject"));
    stringBuffer.append(TEXT_634);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_635);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(negativeOffsetCorrection);
    stringBuffer.append(TEXT_636);
    } else { GenFeature reverseFeature = genFeature.getReverse(); GenClass targetClass = reverseFeature.getGenClass(); String reverseOffsetCorrection = targetClass.hasOffsetCorrection() ? " + " + genClass.getOffsetCorrectionField(reverseFeature) : "";
    stringBuffer.append(TEXT_637);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.InternalEObject"));
    stringBuffer.append(TEXT_638);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_639);
    stringBuffer.append(targetClass.getQualifiedFeatureID(reverseFeature));
    stringBuffer.append(reverseOffsetCorrection);
    stringBuffer.append(TEXT_640);
    stringBuffer.append(targetClass.getRawImportedInterfaceName());
    stringBuffer.append(TEXT_641);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_642);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.InternalEObject"));
    stringBuffer.append(TEXT_643);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_644);
    stringBuffer.append(targetClass.getQualifiedFeatureID(reverseFeature));
    stringBuffer.append(reverseOffsetCorrection);
    stringBuffer.append(TEXT_645);
    stringBuffer.append(targetClass.getRawImportedInterfaceName());
    stringBuffer.append(TEXT_646);
    }
    stringBuffer.append(TEXT_647);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_648);
    stringBuffer.append(genFeature.getInternalTypeCast());
    stringBuffer.append(TEXT_649);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_650);
    if (genFeature.isUnsettable()) {
    stringBuffer.append(TEXT_651);
    if (genModel.isVirtualDelegation()) {
    stringBuffer.append(TEXT_652);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_653);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(positiveOffsetCorrection);
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
    stringBuffer.append(positiveOffsetCorrection);
    stringBuffer.append(TEXT_670);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_671);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_672);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_673);
    }
    stringBuffer.append(TEXT_674);
    } else {
    if (!genModel.isSuppressNotification()) {
    stringBuffer.append(TEXT_675);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.impl.ENotificationImpl"));
    stringBuffer.append(TEXT_676);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.notify.Notification"));
    stringBuffer.append(TEXT_677);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(positiveOffsetCorrection);
    stringBuffer.append(TEXT_678);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_679);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_680);
    }
    }
    } else {
    if (genClass.isFlag(genFeature)) {
    if (!genModel.isSuppressNotification()) {
    if (genFeature.isBooleanType()) {
    stringBuffer.append(TEXT_681);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_682);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_683);
    stringBuffer.append(genClass.getFlagsField(genFeature));
    stringBuffer.append(TEXT_684);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_685);
    } else {
    stringBuffer.append(TEXT_686);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_687);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_688);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_689);
    stringBuffer.append(genClass.getFlagsField(genFeature));
    stringBuffer.append(TEXT_690);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_691);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_692);
    }
    }
    if (genFeature.isBooleanType()) {
    stringBuffer.append(TEXT_693);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_694);
    stringBuffer.append(genClass.getFlagsField(genFeature));
    stringBuffer.append(TEXT_695);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_696);
    stringBuffer.append(genClass.getFlagsField(genFeature));
    stringBuffer.append(TEXT_697);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_698);
    } else {
    stringBuffer.append(TEXT_699);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_700);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_701);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_702);
    stringBuffer.append(genClass.getFlagsField(genFeature));
    stringBuffer.append(TEXT_703);
    stringBuffer.append(genClass.getFlagsField(genFeature));
    stringBuffer.append(TEXT_704);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_705);
    if (genModel.getComplianceLevel().getValue() >= GenJDKLevel.JDK50) {
    stringBuffer.append(TEXT_706);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_707);
    } else {
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_708);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_709);
    }
    stringBuffer.append(TEXT_710);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_711);
    }
    } else {
    if (!genModel.isVirtualDelegation() || genFeature.isPrimitiveType()) {
    if (!genModel.isSuppressNotification()) {
    stringBuffer.append(TEXT_712);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_713);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_714);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_715);
    }
    }
    if (genFeature.isEnumType()) {
    if (genModel.isVirtualDelegation()) {
    stringBuffer.append(TEXT_716);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_717);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_718);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_719);
    stringBuffer.append(genFeature.getEDefault());
    stringBuffer.append(TEXT_720);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_721);
    } else {
    stringBuffer.append(TEXT_722);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_723);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_724);
    stringBuffer.append(genFeature.getEDefault());
    stringBuffer.append(TEXT_725);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_726);
    }
    } else {
    if (genModel.isVirtualDelegation() && !genFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_727);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_728);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_729);
    stringBuffer.append(genFeature.getInternalTypeCast());
    stringBuffer.append(TEXT_730);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_731);
    } else {
    stringBuffer.append(TEXT_732);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_733);
    stringBuffer.append(genFeature.getInternalTypeCast());
    stringBuffer.append(TEXT_734);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_735);
    }
    }
    if (genModel.isVirtualDelegation() && !genFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_736);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_737);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(positiveOffsetCorrection);
    stringBuffer.append(TEXT_738);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_739);
    }
    }
    if (genFeature.isUnsettable()) {
    if (genModel.isVirtualDelegation() && !genFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_740);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_741);
    } else if (genClass.isESetFlag(genFeature)) {
    if (!genModel.isSuppressNotification()) {
    stringBuffer.append(TEXT_742);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_743);
    stringBuffer.append(genClass.getESetFlagsField(genFeature));
    stringBuffer.append(TEXT_744);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_745);
    }
    stringBuffer.append(TEXT_746);
    stringBuffer.append(genClass.getESetFlagsField(genFeature));
    stringBuffer.append(TEXT_747);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_748);
    } else {
    if (!genModel.isSuppressNotification()) {
    stringBuffer.append(TEXT_749);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_750);
    stringBuffer.append(genFeature.getUncapName());
    stringBuffer.append(TEXT_751);
    }
    stringBuffer.append(TEXT_752);
    stringBuffer.append(genFeature.getUncapName());
    stringBuffer.append(TEXT_753);
    }
    if (!genModel.isSuppressNotification()) {
    stringBuffer.append(TEXT_754);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.impl.ENotificationImpl"));
    stringBuffer.append(TEXT_755);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.notify.Notification"));
    stringBuffer.append(TEXT_756);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(positiveOffsetCorrection);
    stringBuffer.append(TEXT_757);
    if (genModel.isVirtualDelegation() && !genFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_758);
    stringBuffer.append(genFeature.getEDefault());
    stringBuffer.append(TEXT_759);
    stringBuffer.append(genFeature.getCapName());
    } else {
    stringBuffer.append(TEXT_760);
    stringBuffer.append(genFeature.getCapName());
    }
    stringBuffer.append(TEXT_761);
    if (genClass.isFlag(genFeature)) {
    stringBuffer.append(TEXT_762);
    stringBuffer.append(genFeature.getCapName());
    } else {
    stringBuffer.append(genFeature.getSafeName());
    }
    stringBuffer.append(TEXT_763);
    if (genModel.isVirtualDelegation() && !genFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_764);
    } else {
    stringBuffer.append(TEXT_765);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_766);
    }
    stringBuffer.append(TEXT_767);
    }
    } else {
    if (!genModel.isSuppressNotification()) {
    stringBuffer.append(TEXT_768);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.impl.ENotificationImpl"));
    stringBuffer.append(TEXT_769);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.notify.Notification"));
    stringBuffer.append(TEXT_770);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(positiveOffsetCorrection);
    stringBuffer.append(TEXT_771);
    if (genModel.isVirtualDelegation() && !genFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_772);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_773);
    stringBuffer.append(genFeature.getEDefault());
    stringBuffer.append(TEXT_774);
    stringBuffer.append(genFeature.getCapName());
    } else {
    stringBuffer.append(TEXT_775);
    stringBuffer.append(genFeature.getCapName());
    }
    stringBuffer.append(TEXT_776);
    if (genClass.isFlag(genFeature)) {
    stringBuffer.append(TEXT_777);
    stringBuffer.append(genFeature.getCapName());
    } else {
    stringBuffer.append(genFeature.getSafeName());
    }
    stringBuffer.append(TEXT_778);
    }
    }
    }
    } else if (genFeature.hasDelegateFeature()) { GenFeature delegateFeature = genFeature.getDelegateFeature();
    if (delegateFeature.isWrappedFeatureMapType()) {
    stringBuffer.append(TEXT_779);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.FeatureMap"));
    stringBuffer.append(TEXT_780);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.FeatureMap"));
    stringBuffer.append(TEXT_781);
    stringBuffer.append(delegateFeature.getAccessorName());
    stringBuffer.append(TEXT_782);
    stringBuffer.append(genFeature.getQualifiedFeatureAccessor());
    stringBuffer.append(TEXT_783);
    if (genFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_784);
    stringBuffer.append(genFeature.getObjectType(genClass));
    stringBuffer.append(TEXT_785);
    }
    stringBuffer.append(TEXT_786);
    stringBuffer.append(genFeature.getCapName());
    if (genFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_787);
    }
    stringBuffer.append(TEXT_788);
    } else {
    stringBuffer.append(TEXT_789);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.FeatureMap"));
    stringBuffer.append(TEXT_790);
    stringBuffer.append(delegateFeature.getAccessorName());
    stringBuffer.append(TEXT_791);
    stringBuffer.append(genFeature.getQualifiedFeatureAccessor());
    stringBuffer.append(TEXT_792);
    if (genFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_793);
    stringBuffer.append(genFeature.getObjectType(genClass));
    stringBuffer.append(TEXT_794);
    }
    stringBuffer.append(TEXT_795);
    stringBuffer.append(genFeature.getCapName());
    if (genFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_796);
    }
    stringBuffer.append(TEXT_797);
    }
    } else if (setAccessorOperation != null) {
    stringBuffer.append(TEXT_798);
    stringBuffer.append(setAccessorOperation.getBody(genModel.getIndentation(stringBuffer)));
    } else {
    stringBuffer.append(TEXT_799);
    stringBuffer.append(genFeature.getFormattedName());
    stringBuffer.append(TEXT_800);
    stringBuffer.append(genFeature.getFeatureKind());
    stringBuffer.append(TEXT_801);
    //Class/setGenFeature.todo.override.javajetinc
    }
    stringBuffer.append(TEXT_802);
    }
    //Class/setGenFeature.override.javajetinc
    }
    if (isImplementation && !genModel.isReflectiveDelegation() && genFeature.isBasicUnset()) {
    stringBuffer.append(TEXT_803);
    if (genModel.getComplianceLevel().getValue() >= GenJDKLevel.JDK50) { //Class/basicUnsetGenFeature.annotations.insert.javajetinc
    }
    stringBuffer.append(TEXT_804);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.notify.NotificationChain"));
    stringBuffer.append(TEXT_805);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_806);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.notify.NotificationChain"));
    stringBuffer.append(TEXT_807);
    if (!genFeature.isVolatile()) {
    if (genModel.isVirtualDelegation()) {
    if (!genModel.isSuppressNotification()) {
    stringBuffer.append(TEXT_808);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_809);
    }
    stringBuffer.append(TEXT_810);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(positiveOffsetCorrection);
    stringBuffer.append(TEXT_811);
    } else {
    if (!genModel.isSuppressNotification()) {
    stringBuffer.append(TEXT_812);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_813);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_814);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_815);
    }
    stringBuffer.append(TEXT_816);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_817);
    }
    if (genModel.isVirtualDelegation()) {
    if (!genModel.isSuppressNotification()) {
    stringBuffer.append(TEXT_818);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_819);
    }
    } else if (genClass.isESetFlag(genFeature)) {
    if (!genModel.isSuppressNotification()) {
    stringBuffer.append(TEXT_820);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_821);
    stringBuffer.append(genClass.getESetFlagsField(genFeature));
    stringBuffer.append(TEXT_822);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_823);
    }
    stringBuffer.append(TEXT_824);
    stringBuffer.append(genClass.getESetFlagsField(genFeature));
    stringBuffer.append(TEXT_825);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_826);
    } else {
    if (!genModel.isSuppressNotification()) {
    stringBuffer.append(TEXT_827);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_828);
    stringBuffer.append(genFeature.getUncapName());
    stringBuffer.append(TEXT_829);
    }
    stringBuffer.append(TEXT_830);
    stringBuffer.append(genFeature.getUncapName());
    stringBuffer.append(TEXT_831);
    }
    if (!genModel.isSuppressNotification()) {
    stringBuffer.append(TEXT_832);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.impl.ENotificationImpl"));
    stringBuffer.append(TEXT_833);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.impl.ENotificationImpl"));
    stringBuffer.append(TEXT_834);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.notify.Notification"));
    stringBuffer.append(TEXT_835);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(positiveOffsetCorrection);
    stringBuffer.append(TEXT_836);
    if (genModel.isVirtualDelegation()) {
    stringBuffer.append(TEXT_837);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_838);
    } else {
    stringBuffer.append(TEXT_839);
    stringBuffer.append(genFeature.getCapName());
    }
    stringBuffer.append(TEXT_840);
    if (genModel.isVirtualDelegation()) {
    stringBuffer.append(TEXT_841);
    } else {
    stringBuffer.append(TEXT_842);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_843);
    }
    stringBuffer.append(TEXT_844);
    }
    } else {
    stringBuffer.append(TEXT_845);
    stringBuffer.append(genFeature.getFormattedName());
    stringBuffer.append(TEXT_846);
    stringBuffer.append(genFeature.getFeatureKind());
    stringBuffer.append(TEXT_847);
    //Class/basicUnsetGenFeature.todo.override.javajetinc
    }
    stringBuffer.append(TEXT_848);
    //Class.basicUnsetGenFeature.override.javajetinc
    }
    if (genFeature.isUnset() && (isImplementation || !genFeature.isSuppressedUnsetVisibility())) {
    if (isInterface) {
    stringBuffer.append(TEXT_849);
    stringBuffer.append(genClass.getQualifiedInterfaceName());
    stringBuffer.append(TEXT_850);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_851);
    stringBuffer.append(genFeature.getFormattedName());
    stringBuffer.append(TEXT_852);
    stringBuffer.append(genFeature.getFeatureKind());
    stringBuffer.append(TEXT_853);
    stringBuffer.append(TEXT_854);
    if (!genFeature.isSuppressedIsSetVisibility()) {
    stringBuffer.append(TEXT_855);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_856);
    }
    stringBuffer.append(TEXT_857);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_858);
    if (!genFeature.isListType() && !genFeature.isSuppressedSetVisibility()) {
    stringBuffer.append(TEXT_859);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_860);
    stringBuffer.append(genFeature.getRawImportedBoundType());
    stringBuffer.append(TEXT_861);
    }
    stringBuffer.append(TEXT_862);
    //Class/unsetGenFeature.javadoc.override.javajetinc
    } else {
    stringBuffer.append(TEXT_863);
    if (genModel.getComplianceLevel().getValue() >= GenJDKLevel.JDK50) { //Class/unsetGenFeature.annotations.insert.javajetinc
    }
    }
    if (!isImplementation) {
    stringBuffer.append(TEXT_864);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_865);
    } else {
    stringBuffer.append(TEXT_866);
    stringBuffer.append(genFeature.getAccessorName());
    if (genClass.hasCollidingUnsetAccessorOperation(genFeature)) {
    stringBuffer.append(TEXT_867);
    }
    stringBuffer.append(TEXT_868);
    if (genModel.isReflectiveDelegation()) {
    stringBuffer.append(TEXT_869);
    stringBuffer.append(genFeature.getQualifiedFeatureAccessor());
    stringBuffer.append(TEXT_870);
    } else if (!genFeature.isVolatile()) {
    if (genFeature.isListType()) {
    if (genModel.isVirtualDelegation()) {
    stringBuffer.append(TEXT_871);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_872);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_873);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_874);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(positiveOffsetCorrection);
    stringBuffer.append(TEXT_875);
    }
    stringBuffer.append(TEXT_876);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_877);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.InternalEList"));
    stringBuffer.append(TEXT_878);
    stringBuffer.append(singleWildcard);
    stringBuffer.append(TEXT_879);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_880);
    } else if (genFeature.isBidirectional() || genFeature.isEffectiveContains()) {
    if (genModel.isVirtualDelegation()) {
    stringBuffer.append(TEXT_881);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_882);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_883);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_884);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(positiveOffsetCorrection);
    stringBuffer.append(TEXT_885);
    }
    stringBuffer.append(TEXT_886);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_887);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.notify.NotificationChain"));
    stringBuffer.append(TEXT_888);
    if (!genFeature.isBidirectional()) {
    stringBuffer.append(TEXT_889);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.InternalEObject"));
    stringBuffer.append(TEXT_890);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_891);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(negativeOffsetCorrection);
    stringBuffer.append(TEXT_892);
    } else { GenFeature reverseFeature = genFeature.getReverse(); GenClass targetClass = reverseFeature.getGenClass(); String reverseOffsetCorrection = targetClass.hasOffsetCorrection() ? " + " + genClass.getOffsetCorrectionField(reverseFeature) : "";
    stringBuffer.append(TEXT_893);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.InternalEObject"));
    stringBuffer.append(TEXT_894);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_895);
    stringBuffer.append(targetClass.getQualifiedFeatureID(reverseFeature));
    stringBuffer.append(reverseOffsetCorrection);
    stringBuffer.append(TEXT_896);
    stringBuffer.append(targetClass.getRawImportedInterfaceName());
    stringBuffer.append(TEXT_897);
    }
    stringBuffer.append(TEXT_898);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_899);
    if (genModel.isVirtualDelegation()) {
    stringBuffer.append(TEXT_900);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_901);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(positiveOffsetCorrection);
    stringBuffer.append(TEXT_902);
    } else if (genClass.isESetFlag(genFeature)) {
    if (!genModel.isSuppressNotification()) {
    stringBuffer.append(TEXT_903);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_904);
    stringBuffer.append(genClass.getESetFlagsField(genFeature));
    stringBuffer.append(TEXT_905);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_906);
    }
    stringBuffer.append(TEXT_907);
    stringBuffer.append(genClass.getESetFlagsField(genFeature));
    stringBuffer.append(TEXT_908);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_909);
    } else {
    if (!genModel.isSuppressNotification()) {
    stringBuffer.append(TEXT_910);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_911);
    stringBuffer.append(genFeature.getUncapName());
    stringBuffer.append(TEXT_912);
    }
    stringBuffer.append(TEXT_913);
    stringBuffer.append(genFeature.getUncapName());
    stringBuffer.append(TEXT_914);
    }
    if (!genModel.isSuppressNotification()) {
    stringBuffer.append(TEXT_915);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.impl.ENotificationImpl"));
    stringBuffer.append(TEXT_916);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.notify.Notification"));
    stringBuffer.append(TEXT_917);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(positiveOffsetCorrection);
    stringBuffer.append(TEXT_918);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_919);
    }
    stringBuffer.append(TEXT_920);
    } else {
    if (genClass.isFlag(genFeature)) {
    if (!genModel.isSuppressNotification()) {
    if (genFeature.isBooleanType()) {
    stringBuffer.append(TEXT_921);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_922);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_923);
    stringBuffer.append(genClass.getFlagsField(genFeature));
    stringBuffer.append(TEXT_924);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_925);
    } else {
    stringBuffer.append(TEXT_926);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_927);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_928);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_929);
    stringBuffer.append(genClass.getFlagsField(genFeature));
    stringBuffer.append(TEXT_930);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_931);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_932);
    }
    }
    } else if (genModel.isVirtualDelegation() && !genFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_933);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_934);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(positiveOffsetCorrection);
    stringBuffer.append(TEXT_935);
    } else {
    if (!genModel.isSuppressNotification()) {
    stringBuffer.append(TEXT_936);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_937);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_938);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_939);
    }
    }
    if (!genModel.isSuppressNotification()) {
    if (genModel.isVirtualDelegation() && !genFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_940);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_941);
    } else if (genClass.isESetFlag(genFeature)) {
    stringBuffer.append(TEXT_942);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_943);
    stringBuffer.append(genClass.getESetFlagsField(genFeature));
    stringBuffer.append(TEXT_944);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_945);
    } else {
    stringBuffer.append(TEXT_946);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_947);
    stringBuffer.append(genFeature.getUncapName());
    stringBuffer.append(TEXT_948);
    }
    }
    if (genFeature.isReferenceType()) {
    stringBuffer.append(TEXT_949);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_950);
    if (!genModel.isVirtualDelegation()) {
    if (genClass.isESetFlag(genFeature)) {
    stringBuffer.append(TEXT_951);
    stringBuffer.append(genClass.getESetFlagsField(genFeature));
    stringBuffer.append(TEXT_952);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_953);
    } else {
    stringBuffer.append(TEXT_954);
    stringBuffer.append(genFeature.getUncapName());
    stringBuffer.append(TEXT_955);
    }
    }
    if (!genModel.isSuppressNotification()) {
    stringBuffer.append(TEXT_956);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.impl.ENotificationImpl"));
    stringBuffer.append(TEXT_957);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.notify.Notification"));
    stringBuffer.append(TEXT_958);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(positiveOffsetCorrection);
    stringBuffer.append(TEXT_959);
    if (genModel.isVirtualDelegation()) {
    stringBuffer.append(TEXT_960);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_961);
    } else {
    stringBuffer.append(TEXT_962);
    stringBuffer.append(genFeature.getCapName());
    }
    stringBuffer.append(TEXT_963);
    if (genModel.isVirtualDelegation()) {
    stringBuffer.append(TEXT_964);
    } else {
    stringBuffer.append(TEXT_965);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_966);
    }
    stringBuffer.append(TEXT_967);
    }
    } else {
    if (genClass.isFlag(genFeature)) {
    if (genFeature.isBooleanType()) {
    stringBuffer.append(TEXT_968);
    stringBuffer.append(genFeature.getEDefault());
    stringBuffer.append(TEXT_969);
    stringBuffer.append(genClass.getFlagsField(genFeature));
    stringBuffer.append(TEXT_970);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_971);
    stringBuffer.append(genClass.getFlagsField(genFeature));
    stringBuffer.append(TEXT_972);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_973);
    } else {
    stringBuffer.append(TEXT_974);
    stringBuffer.append(genClass.getFlagsField(genFeature));
    stringBuffer.append(TEXT_975);
    stringBuffer.append(genClass.getFlagsField(genFeature));
    stringBuffer.append(TEXT_976);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_977);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_978);
    }
    } else if (!genModel.isVirtualDelegation() || genFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_979);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_980);
    stringBuffer.append(genFeature.getEDefault());
    stringBuffer.append(TEXT_981);
    }
    if (!genModel.isVirtualDelegation() || genFeature.isPrimitiveType()) {
    if (genClass.isESetFlag(genFeature)) {
    stringBuffer.append(TEXT_982);
    stringBuffer.append(genClass.getESetFlagsField(genFeature));
    stringBuffer.append(TEXT_983);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_984);
    } else {
    stringBuffer.append(TEXT_985);
    stringBuffer.append(genFeature.getUncapName());
    stringBuffer.append(TEXT_986);
    }
    }
    if (!genModel.isSuppressNotification()) {
    stringBuffer.append(TEXT_987);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.impl.ENotificationImpl"));
    stringBuffer.append(TEXT_988);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.notify.Notification"));
    stringBuffer.append(TEXT_989);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(positiveOffsetCorrection);
    stringBuffer.append(TEXT_990);
    if (genModel.isVirtualDelegation() && !genFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_991);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_992);
    stringBuffer.append(genFeature.getEDefault());
    } else {
    stringBuffer.append(TEXT_993);
    stringBuffer.append(genFeature.getCapName());
    }
    stringBuffer.append(TEXT_994);
    stringBuffer.append(genFeature.getEDefault());
    stringBuffer.append(TEXT_995);
    if (genModel.isVirtualDelegation() && !genFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_996);
    } else {
    stringBuffer.append(TEXT_997);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_998);
    }
    stringBuffer.append(TEXT_999);
    }
    }
    }
    } else if (genFeature.hasDelegateFeature()) { GenFeature delegateFeature = genFeature.getDelegateFeature();
    if (delegateFeature.isWrappedFeatureMapType()) {
    stringBuffer.append(TEXT_1000);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.FeatureMap"));
    stringBuffer.append(TEXT_1001);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.FeatureMap"));
    stringBuffer.append(TEXT_1002);
    stringBuffer.append(delegateFeature.getAccessorName());
    stringBuffer.append(TEXT_1003);
    stringBuffer.append(genFeature.getQualifiedFeatureAccessor());
    stringBuffer.append(TEXT_1004);
    } else {
    stringBuffer.append(TEXT_1005);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.FeatureMap"));
    stringBuffer.append(TEXT_1006);
    stringBuffer.append(delegateFeature.getAccessorName());
    stringBuffer.append(TEXT_1007);
    stringBuffer.append(genFeature.getQualifiedFeatureAccessor());
    stringBuffer.append(TEXT_1008);
    }
    } else if (genClass.getUnsetAccessorOperation(genFeature) != null) {
    stringBuffer.append(TEXT_1009);
    stringBuffer.append(genClass.getUnsetAccessorOperation(genFeature).getBody(genModel.getIndentation(stringBuffer)));
    } else {
    stringBuffer.append(TEXT_1010);
    stringBuffer.append(genFeature.getFormattedName());
    stringBuffer.append(TEXT_1011);
    stringBuffer.append(genFeature.getFeatureKind());
    stringBuffer.append(TEXT_1012);
    //Class/unsetGenFeature.todo.override.javajetinc
    }
    stringBuffer.append(TEXT_1013);
    }
    //Class/unsetGenFeature.override.javajetinc
    }
    if (genFeature.isIsSet() && (isImplementation || !genFeature.isSuppressedIsSetVisibility())) {
    if (isInterface) {
    stringBuffer.append(TEXT_1014);
    stringBuffer.append(genClass.getQualifiedInterfaceName());
    stringBuffer.append(TEXT_1015);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_1016);
    stringBuffer.append(genFeature.getFormattedName());
    stringBuffer.append(TEXT_1017);
    stringBuffer.append(genFeature.getFeatureKind());
    stringBuffer.append(TEXT_1018);
    stringBuffer.append(TEXT_1019);
    stringBuffer.append(genFeature.getFormattedName());
    stringBuffer.append(TEXT_1020);
    stringBuffer.append(genFeature.getFeatureKind());
    stringBuffer.append(TEXT_1021);
    if (genFeature.isChangeable() && !genFeature.isSuppressedUnsetVisibility()) {
    stringBuffer.append(TEXT_1022);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_1023);
    }
    stringBuffer.append(TEXT_1024);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_1025);
    if (!genFeature.isListType() && genFeature.isChangeable() && !genFeature.isSuppressedSetVisibility()) {
    stringBuffer.append(TEXT_1026);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_1027);
    stringBuffer.append(genFeature.getRawImportedBoundType());
    stringBuffer.append(TEXT_1028);
    }
    stringBuffer.append(TEXT_1029);
    //Class/isSetGenFeature.javadoc.override.javajetinc
    } else {
    stringBuffer.append(TEXT_1030);
    if (genModel.getComplianceLevel().getValue() >= GenJDKLevel.JDK50) { //Class/isSetGenFeature.annotations.insert.javajetinc
    }
    }
    if (!isImplementation) {
    stringBuffer.append(TEXT_1031);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_1032);
    } else {
    stringBuffer.append(TEXT_1033);
    stringBuffer.append(genFeature.getAccessorName());
    if (genClass.hasCollidingIsSetAccessorOperation(genFeature)) {
    stringBuffer.append(TEXT_1034);
    }
    stringBuffer.append(TEXT_1035);
    if (genModel.isReflectiveDelegation()) {
    stringBuffer.append(TEXT_1036);
    stringBuffer.append(genFeature.getQualifiedFeatureAccessor());
    stringBuffer.append(TEXT_1037);
    } else if (!genFeature.isVolatile()) {
    if (genFeature.isListType()) {
    if (genModel.isVirtualDelegation()) {
    stringBuffer.append(TEXT_1038);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_1039);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_1040);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_1041);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(positiveOffsetCorrection);
    stringBuffer.append(TEXT_1042);
    }
    stringBuffer.append(TEXT_1043);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_1044);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.InternalEList"));
    stringBuffer.append(TEXT_1045);
    stringBuffer.append(singleWildcard);
    stringBuffer.append(TEXT_1046);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_1047);
    } else {
    if (genModel.isVirtualDelegation() && !genFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_1048);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(positiveOffsetCorrection);
    stringBuffer.append(TEXT_1049);
    } else if (genClass.isESetFlag(genFeature)) {
    stringBuffer.append(TEXT_1050);
    stringBuffer.append(genClass.getESetFlagsField(genFeature));
    stringBuffer.append(TEXT_1051);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_1052);
    } else {
    stringBuffer.append(TEXT_1053);
    stringBuffer.append(genFeature.getUncapName());
    stringBuffer.append(TEXT_1054);
    }
    }
    } else if (genFeature.hasDelegateFeature()) { GenFeature delegateFeature = genFeature.getDelegateFeature();
    if (delegateFeature.isWrappedFeatureMapType()) {
    stringBuffer.append(TEXT_1055);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.FeatureMap"));
    stringBuffer.append(TEXT_1056);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.FeatureMap"));
    stringBuffer.append(TEXT_1057);
    stringBuffer.append(delegateFeature.getAccessorName());
    stringBuffer.append(TEXT_1058);
    stringBuffer.append(genFeature.getQualifiedFeatureAccessor());
    stringBuffer.append(TEXT_1059);
    } else {
    stringBuffer.append(TEXT_1060);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.FeatureMap"));
    stringBuffer.append(TEXT_1061);
    stringBuffer.append(delegateFeature.getAccessorName());
    stringBuffer.append(TEXT_1062);
    stringBuffer.append(genFeature.getQualifiedFeatureAccessor());
    stringBuffer.append(TEXT_1063);
    }
    } else if (genClass.getIsSetAccessorOperation(genFeature) != null) {
    stringBuffer.append(TEXT_1064);
    stringBuffer.append(genClass.getIsSetAccessorOperation(genFeature).getBody(genModel.getIndentation(stringBuffer)));
    } else {
    stringBuffer.append(TEXT_1065);
    stringBuffer.append(genFeature.getFormattedName());
    stringBuffer.append(TEXT_1066);
    stringBuffer.append(genFeature.getFeatureKind());
    stringBuffer.append(TEXT_1067);
    //Class/isSetGenFeature.todo.override.javajetinc
    }
    stringBuffer.append(TEXT_1068);
    }
    //Class/isSetGenFeature.override.javajetinc
    }
    //Class/genFeature.override.javajetinc
    }//for
    }}.run();
    for (GenOperation genOperation : (isImplementation ? genClass.getImplementedGenOperations() : genClass.getDeclaredGenOperations())) {
    if (isInterface) {
    stringBuffer.append(TEXT_1069);
    stringBuffer.append(TEXT_1070);
    if (genOperation.hasDocumentation() || genOperation.hasParameterDocumentation()) {
    stringBuffer.append(TEXT_1071);
    if (genOperation.hasDocumentation()) {
    stringBuffer.append(TEXT_1072);
    stringBuffer.append(genOperation.getDocumentation(genModel.getIndentation(stringBuffer)));
    }
    for (GenParameter genParameter : genOperation.getGenParameters()) {
    if (genParameter.hasDocumentation()) { String documentation = genParameter.getDocumentation("");
    if (documentation.contains("\n") || documentation.contains("\r")) {
    stringBuffer.append(TEXT_1073);
    stringBuffer.append(genParameter.getName());
    stringBuffer.append(TEXT_1074);
    stringBuffer.append(genParameter.getDocumentation(genModel.getIndentation(stringBuffer)));
    } else {
    stringBuffer.append(TEXT_1075);
    stringBuffer.append(genParameter.getName());
    stringBuffer.append(TEXT_1076);
    stringBuffer.append(genParameter.getDocumentation(genModel.getIndentation(stringBuffer)));
    }
    }
    }
    stringBuffer.append(TEXT_1077);
    }
    if (!genModel.isSuppressEMFModelTags()) { boolean first = true; for (StringTokenizer stringTokenizer = new StringTokenizer(genOperation.getModelInfo(), "\n\r"); stringTokenizer.hasMoreTokens(); ) { String modelInfo = stringTokenizer.nextToken(); if (first) { first = false;
    stringBuffer.append(TEXT_1078);
    stringBuffer.append(modelInfo);
    } else {
    stringBuffer.append(TEXT_1079);
    stringBuffer.append(modelInfo);
    }} if (first) {
    stringBuffer.append(TEXT_1080);
    }}
    stringBuffer.append(TEXT_1081);
    //Class/genOperation.javadoc.override.javajetinc
    } else {
    stringBuffer.append(TEXT_1082);
    if (genModel.getComplianceLevel().getValue() >= GenJDKLevel.JDK50) { //Class/genOperation.annotations.insert.javajetinc
    }
    }
    if (!isImplementation) {
    stringBuffer.append(TEXT_1083);
    stringBuffer.append(genOperation.getTypeParameters(genClass));
    stringBuffer.append(genOperation.getImportedType(genClass));
    stringBuffer.append(TEXT_1084);
    stringBuffer.append(genOperation.getName());
    stringBuffer.append(TEXT_1085);
    stringBuffer.append(genOperation.getParameters(genClass));
    stringBuffer.append(TEXT_1086);
    stringBuffer.append(genOperation.getThrows(genClass));
    stringBuffer.append(TEXT_1087);
    } else {
    stringBuffer.append(TEXT_1088);
    stringBuffer.append(genOperation.getTypeParameters(genClass));
    stringBuffer.append(genOperation.getImportedType(genClass));
    stringBuffer.append(TEXT_1089);
    stringBuffer.append(genOperation.getName());
    stringBuffer.append(TEXT_1090);
    stringBuffer.append(genOperation.getParameters(genClass));
    stringBuffer.append(TEXT_1091);
    stringBuffer.append(genOperation.getThrows(genClass));
    stringBuffer.append(TEXT_1092);
    if (genOperation.hasBody()) {
    stringBuffer.append(TEXT_1093);
    stringBuffer.append(genOperation.getBody(genModel.getIndentation(stringBuffer)));
    } else if (genOperation.isInvariant()) {GenClass opClass = genOperation.getGenClass(); String diagnostics = genOperation.getGenParameters().get(0).getName(); String context = genOperation.getGenParameters().get(1).getName();
    stringBuffer.append(TEXT_1094);
    stringBuffer.append(diagnostics);
    stringBuffer.append(TEXT_1095);
    stringBuffer.append(diagnostics);
    stringBuffer.append(TEXT_1096);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.util.BasicDiagnostic"));
    stringBuffer.append(TEXT_1097);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.util.Diagnostic"));
    stringBuffer.append(TEXT_1098);
    stringBuffer.append(opClass.getGenPackage().getImportedValidatorClassName());
    stringBuffer.append(TEXT_1099);
    stringBuffer.append(opClass.getGenPackage().getImportedValidatorClassName());
    stringBuffer.append(TEXT_1100);
    stringBuffer.append(opClass.getOperationID(genOperation));
    stringBuffer.append(TEXT_1101);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.plugin.EcorePlugin"));
    stringBuffer.append(TEXT_1102);
    stringBuffer.append(genOperation.getName());
    stringBuffer.append(TEXT_1103);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.EObjectValidator"));
    stringBuffer.append(TEXT_1104);
    stringBuffer.append(context);
    stringBuffer.append(TEXT_1105);
    stringBuffer.append(genModel.getNonNLS());
    stringBuffer.append(genModel.getNonNLS(2));
    stringBuffer.append(TEXT_1106);
    } else {
    stringBuffer.append(TEXT_1107);
    //Class/implementedGenOperation.todo.override.javajetinc
    }
    stringBuffer.append(TEXT_1108);
    }
    //Class/implementedGenOperation.override.javajetinc
    }//for
    if (isImplementation && !genModel.isReflectiveDelegation() && genClass.implementsAny(genClass.getEInverseAddGenFeatures())) {
    stringBuffer.append(TEXT_1109);
    if (genModel.useGenerics()) {
    for (GenFeature genFeature : genClass.getEInverseAddGenFeatures()) {
    if (genFeature.isUncheckedCast(genClass)) {
    stringBuffer.append(TEXT_1110);
    break; }
    }
    }
    if (genModel.useClassOverrideAnnotation()) {
    stringBuffer.append(TEXT_1111);
    }
    stringBuffer.append(TEXT_1112);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.notify.NotificationChain"));
    stringBuffer.append(TEXT_1113);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.InternalEObject"));
    stringBuffer.append(TEXT_1114);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.notify.NotificationChain"));
    stringBuffer.append(TEXT_1115);
    stringBuffer.append(negativeOffsetCorrection);
    stringBuffer.append(TEXT_1116);
    for (GenFeature genFeature : genClass.getEInverseAddGenFeatures()) {
    stringBuffer.append(TEXT_1117);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(TEXT_1118);
    if (genFeature.isListType()) { String cast = "("  + genModel.getImportedName("org.eclipse.emf.ecore.util.InternalEList") + (!genModel.useGenerics() ? ")" : "<" + genModel.getImportedName("org.eclipse.emf.ecore.InternalEObject") + ">)(" + genModel.getImportedName("org.eclipse.emf.ecore.util.InternalEList") + "<?>)");
    if (genFeature.isMapType() && genFeature.isEffectiveSuppressEMFTypes()) {
    stringBuffer.append(TEXT_1119);
    stringBuffer.append(cast);
    stringBuffer.append(TEXT_1120);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.util.EMap"));
    stringBuffer.append(TEXT_1121);
    stringBuffer.append(genFeature.getImportedMapTemplateArguments(genClass));
    stringBuffer.append(TEXT_1122);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_1123);
    } else {
    stringBuffer.append(TEXT_1124);
    stringBuffer.append(cast);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_1125);
    }
    } else if (genFeature.isContainer()) {
    stringBuffer.append(TEXT_1126);
    if (genFeature.isBasicSet()) {
    stringBuffer.append(TEXT_1127);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_1128);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_1129);
    } else {
    stringBuffer.append(TEXT_1130);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(positiveOffsetCorrection);
    stringBuffer.append(TEXT_1131);
    }
    } else {
    if (genClass.getImplementingGenModel(genFeature).isVirtualDelegation()) {
    stringBuffer.append(TEXT_1132);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_1133);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_1134);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_1135);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(positiveOffsetCorrection);
    stringBuffer.append(TEXT_1136);
    } else if (genFeature.isVolatile()) {
    stringBuffer.append(TEXT_1137);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_1138);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_1139);
    if (genFeature.isResolveProxies()) {
    stringBuffer.append(TEXT_1140);
    stringBuffer.append(genFeature.getAccessorName());
    } else {
    stringBuffer.append(genFeature.getGetAccessor());
    }
    stringBuffer.append(TEXT_1141);
    }
    stringBuffer.append(TEXT_1142);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_1143);
    if (genFeature.isEffectiveContains()) {
    stringBuffer.append(TEXT_1144);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.InternalEObject"));
    stringBuffer.append(TEXT_1145);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_1146);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(negativeOffsetCorrection);
    stringBuffer.append(TEXT_1147);
    } else { GenFeature reverseFeature = genFeature.getReverse(); GenClass targetClass = reverseFeature.getGenClass(); String reverseOffsetCorrection = targetClass.hasOffsetCorrection() ? " + " + genClass.getOffsetCorrectionField(reverseFeature) : "";
    stringBuffer.append(TEXT_1148);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.InternalEObject"));
    stringBuffer.append(TEXT_1149);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_1150);
    stringBuffer.append(targetClass.getQualifiedFeatureID(reverseFeature));
    stringBuffer.append(reverseOffsetCorrection);
    stringBuffer.append(TEXT_1151);
    stringBuffer.append(targetClass.getRawImportedInterfaceName());
    stringBuffer.append(TEXT_1152);
    }
    stringBuffer.append(TEXT_1153);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_1154);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_1155);
    }
    }
    stringBuffer.append(TEXT_1156);
    if (genModel.isMinimalReflectiveMethods()) {
    stringBuffer.append(TEXT_1157);
    } else {
    stringBuffer.append(TEXT_1158);
    }
    stringBuffer.append(TEXT_1159);
    }
    if (isImplementation && !genModel.isReflectiveDelegation() && genClass.implementsAny(genClass.getEInverseRemoveGenFeatures())) {
    stringBuffer.append(TEXT_1160);
    if (genModel.useClassOverrideAnnotation()) {
    stringBuffer.append(TEXT_1161);
    }
    stringBuffer.append(TEXT_1162);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.notify.NotificationChain"));
    stringBuffer.append(TEXT_1163);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.InternalEObject"));
    stringBuffer.append(TEXT_1164);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.notify.NotificationChain"));
    stringBuffer.append(TEXT_1165);
    stringBuffer.append(negativeOffsetCorrection);
    stringBuffer.append(TEXT_1166);
    for (GenFeature genFeature : genClass.getEInverseRemoveGenFeatures()) {
    stringBuffer.append(TEXT_1167);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(TEXT_1168);
    if (genFeature.isListType()) {
    if (genFeature.isMapType() && genFeature.isEffectiveSuppressEMFTypes()) {
    stringBuffer.append(TEXT_1169);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.InternalEList"));
    stringBuffer.append(singleWildcard);
    stringBuffer.append(TEXT_1170);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.util.EMap"));
    stringBuffer.append(TEXT_1171);
    stringBuffer.append(genFeature.getImportedMapTemplateArguments(genClass));
    stringBuffer.append(TEXT_1172);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_1173);
    } else if (genFeature.isWrappedFeatureMapType()) {
    stringBuffer.append(TEXT_1174);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.InternalEList"));
    stringBuffer.append(singleWildcard);
    stringBuffer.append(TEXT_1175);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.FeatureMap"));
    stringBuffer.append(TEXT_1176);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_1177);
    } else {
    stringBuffer.append(TEXT_1178);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.InternalEList"));
    stringBuffer.append(singleWildcard);
    stringBuffer.append(TEXT_1179);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_1180);
    }
    } else if (genFeature.isContainer() && !genFeature.isBasicSet()) {
    stringBuffer.append(TEXT_1181);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(positiveOffsetCorrection);
    stringBuffer.append(TEXT_1182);
    } else if (genFeature.isUnsettable()) {
    stringBuffer.append(TEXT_1183);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_1184);
    } else {
    stringBuffer.append(TEXT_1185);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_1186);
    }
    }
    stringBuffer.append(TEXT_1187);
    if (genModel.isMinimalReflectiveMethods()) {
    stringBuffer.append(TEXT_1188);
    } else {
    stringBuffer.append(TEXT_1189);
    }
    stringBuffer.append(TEXT_1190);
    }
    if (isImplementation && !genModel.isReflectiveDelegation() && genClass.implementsAny(genClass.getEBasicRemoveFromContainerGenFeatures())) {
    stringBuffer.append(TEXT_1191);
    if (genModel.useClassOverrideAnnotation()) {
    stringBuffer.append(TEXT_1192);
    }
    stringBuffer.append(TEXT_1193);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.notify.NotificationChain"));
    stringBuffer.append(TEXT_1194);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.notify.NotificationChain"));
    stringBuffer.append(TEXT_1195);
    stringBuffer.append(negativeOffsetCorrection);
    stringBuffer.append(TEXT_1196);
    for (GenFeature genFeature : genClass.getEBasicRemoveFromContainerGenFeatures()) {
    GenFeature reverseFeature = genFeature.getReverse(); GenClass targetClass = reverseFeature.getGenClass(); String reverseOffsetCorrection = targetClass.hasOffsetCorrection() ? " + " + genClass.getOffsetCorrectionField(reverseFeature) : "";
    stringBuffer.append(TEXT_1197);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(TEXT_1198);
    stringBuffer.append(targetClass.getQualifiedFeatureID(reverseFeature));
    stringBuffer.append(reverseOffsetCorrection);
    stringBuffer.append(TEXT_1199);
    stringBuffer.append(targetClass.getRawImportedInterfaceName());
    stringBuffer.append(TEXT_1200);
    }
    stringBuffer.append(TEXT_1201);
    if (genModel.isMinimalReflectiveMethods()) {
    stringBuffer.append(TEXT_1202);
    } else {
    stringBuffer.append(TEXT_1203);
    }
    stringBuffer.append(TEXT_1204);
    }
    if (isImplementation && !genModel.isReflectiveDelegation() && genClass.implementsAny(genClass.getEGetGenFeatures())) {
    stringBuffer.append(TEXT_1205);
    if (genModel.useClassOverrideAnnotation()) {
    stringBuffer.append(TEXT_1206);
    }
    stringBuffer.append(TEXT_1207);
    stringBuffer.append(negativeOffsetCorrection);
    stringBuffer.append(TEXT_1208);
    for (GenFeature genFeature : genClass.getEGetGenFeatures()) {
    stringBuffer.append(TEXT_1209);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(TEXT_1210);
    if (genFeature.isPrimitiveType()) {
    if (genFeature.isBooleanType()) {
    stringBuffer.append(TEXT_1211);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_1212);
    } else {
    stringBuffer.append(TEXT_1213);
    stringBuffer.append(genFeature.getObjectType(genClass));
    stringBuffer.append(TEXT_1214);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_1215);
    }
    } else if (genFeature.isResolveProxies() && !genFeature.isListType()) {
    stringBuffer.append(TEXT_1216);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_1217);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_1218);
    } else if (genFeature.isMapType()) {
    if (genFeature.isEffectiveSuppressEMFTypes()) {
    stringBuffer.append(TEXT_1219);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.util.EMap"));
    stringBuffer.append(TEXT_1220);
    stringBuffer.append(genFeature.getImportedMapTemplateArguments(genClass));
    stringBuffer.append(TEXT_1221);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_1222);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_1223);
    } else {
    stringBuffer.append(TEXT_1224);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_1225);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_1226);
    }
    } else if (genFeature.isWrappedFeatureMapType()) {
    stringBuffer.append(TEXT_1227);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.FeatureMap"));
    stringBuffer.append(TEXT_1228);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_1229);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_1230);
    } else if (genFeature.isFeatureMapType()) {
    stringBuffer.append(TEXT_1231);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_1232);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.FeatureMap"));
    stringBuffer.append(TEXT_1233);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_1234);
    } else {
    stringBuffer.append(TEXT_1235);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_1236);
    }
    }
    stringBuffer.append(TEXT_1237);
    if (genModel.isMinimalReflectiveMethods()) {
    stringBuffer.append(TEXT_1238);
    } else {
    stringBuffer.append(TEXT_1239);
    }
    stringBuffer.append(TEXT_1240);
    }
    if (isImplementation && !genModel.isReflectiveDelegation() && genClass.implementsAny(genClass.getESetGenFeatures())) {
    stringBuffer.append(TEXT_1241);
    if (genModel.useGenerics()) {
    for (GenFeature genFeature : genClass.getESetGenFeatures()) {
    if (genFeature.isUncheckedCast(genClass) && !genFeature.isFeatureMapType() && !genFeature.isMapType()) {
    stringBuffer.append(TEXT_1242);
    break; }
    }
    }
    if (genModel.useClassOverrideAnnotation()) {
    stringBuffer.append(TEXT_1243);
    }
    stringBuffer.append(TEXT_1244);
    stringBuffer.append(negativeOffsetCorrection);
    stringBuffer.append(TEXT_1245);
    for (GenFeature genFeature : genClass.getESetGenFeatures()) {
    stringBuffer.append(TEXT_1246);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(TEXT_1247);
    if (genFeature.isListType()) {
    if (genFeature.isWrappedFeatureMapType()) {
    stringBuffer.append(TEXT_1248);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.FeatureMap"));
    stringBuffer.append(TEXT_1249);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.FeatureMap"));
    stringBuffer.append(TEXT_1250);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_1251);
    } else if (genFeature.isFeatureMapType()) {
    stringBuffer.append(TEXT_1252);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.FeatureMap"));
    stringBuffer.append(TEXT_1253);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_1254);
    } else if (genFeature.isMapType()) {
    if (genFeature.isEffectiveSuppressEMFTypes()) {
    stringBuffer.append(TEXT_1255);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.EStructuralFeature"));
    stringBuffer.append(TEXT_1256);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.util.EMap"));
    stringBuffer.append(TEXT_1257);
    stringBuffer.append(genFeature.getImportedMapTemplateArguments(genClass));
    stringBuffer.append(TEXT_1258);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_1259);
    } else {
    stringBuffer.append(TEXT_1260);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.EStructuralFeature"));
    stringBuffer.append(TEXT_1261);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_1262);
    }
    } else {
    stringBuffer.append(TEXT_1263);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_1264);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_1265);
    stringBuffer.append(genModel.getImportedName("java.util.Collection"));
    if (genModel.getComplianceLevel().getValue() >= GenJDKLevel.JDK50) {
    stringBuffer.append(TEXT_1266);
    stringBuffer.append(genFeature.getListItemType(genClass));
    stringBuffer.append(TEXT_1267);
    }
    stringBuffer.append(TEXT_1268);
    }
    } else if (genFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_1269);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_1270);
    stringBuffer.append(genFeature.getObjectType(genClass));
    stringBuffer.append(TEXT_1271);
    stringBuffer.append(genFeature.getPrimitiveValueFunction());
    stringBuffer.append(TEXT_1272);
    } else {
    stringBuffer.append(TEXT_1273);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_1274);
    if (genFeature.getTypeGenDataType() == null || !genFeature.getTypeGenDataType().isObjectType() || !genFeature.getRawType().equals(genFeature.getType(genClass))) {
    stringBuffer.append(TEXT_1275);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_1276);
    }
    stringBuffer.append(TEXT_1277);
    }
    stringBuffer.append(TEXT_1278);
    }
    stringBuffer.append(TEXT_1279);
    if (genModel.isMinimalReflectiveMethods()) {
    stringBuffer.append(TEXT_1280);
    } else {
    stringBuffer.append(TEXT_1281);
    }
    stringBuffer.append(TEXT_1282);
    }
    if (isImplementation && !genModel.isReflectiveDelegation() && genClass.implementsAny(genClass.getEUnsetGenFeatures())) {
    stringBuffer.append(TEXT_1283);
    if (genModel.useClassOverrideAnnotation()) {
    stringBuffer.append(TEXT_1284);
    }
    stringBuffer.append(TEXT_1285);
    stringBuffer.append(negativeOffsetCorrection);
    stringBuffer.append(TEXT_1286);
    for (GenFeature genFeature : genClass.getEUnsetGenFeatures()) {
    stringBuffer.append(TEXT_1287);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(TEXT_1288);
    if (genFeature.isListType() && !genFeature.isUnsettable()) {
    if (genFeature.isWrappedFeatureMapType()) {
    stringBuffer.append(TEXT_1289);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.FeatureMap"));
    stringBuffer.append(TEXT_1290);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_1291);
    } else {
    stringBuffer.append(TEXT_1292);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_1293);
    }
    } else if (genFeature.isUnsettable()) {
    stringBuffer.append(TEXT_1294);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_1295);
    } else if (!genFeature.hasEDefault()) {
    stringBuffer.append(TEXT_1296);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_1297);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_1298);
    } else {
    stringBuffer.append(TEXT_1299);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_1300);
    stringBuffer.append(genFeature.getEDefault());
    stringBuffer.append(TEXT_1301);
    }
    stringBuffer.append(TEXT_1302);
    }
    stringBuffer.append(TEXT_1303);
    if (genModel.isMinimalReflectiveMethods()) {
    stringBuffer.append(TEXT_1304);
    } else {
    stringBuffer.append(TEXT_1305);
    }
    stringBuffer.append(TEXT_1306);
    //Class/eUnset.override.javajetinc
    }
    if (isImplementation && !genModel.isReflectiveDelegation() && genClass.implementsAny(genClass.getEIsSetGenFeatures())) {
    stringBuffer.append(TEXT_1307);
    if (genModel.useGenerics()) {
    for (GenFeature genFeature : genClass.getEIsSetGenFeatures()) {
    if (genFeature.isListType() && !genFeature.isUnsettable() && !genFeature.isWrappedFeatureMapType() && !genClass.isField(genFeature) && genFeature.isField() && genClass.getImplementingGenModel(genFeature).isVirtualDelegation()) {
    stringBuffer.append(TEXT_1308);
    break; }
    }
    }
    if (genModel.useClassOverrideAnnotation()) {
    stringBuffer.append(TEXT_1309);
    }
    stringBuffer.append(TEXT_1310);
    stringBuffer.append(negativeOffsetCorrection);
    stringBuffer.append(TEXT_1311);
    for (GenFeature genFeature : genClass.getEIsSetGenFeatures()) {
    stringBuffer.append(TEXT_1312);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(TEXT_1313);
    if (genFeature.isListType() && !genFeature.isUnsettable()) {
    if (genFeature.isWrappedFeatureMapType()) {
    if (genFeature.isVolatile()) {
    stringBuffer.append(TEXT_1314);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.FeatureMap"));
    stringBuffer.append(TEXT_1315);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_1316);
    } else {
    stringBuffer.append(TEXT_1317);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_1318);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_1319);
    }
    } else {
    if (genClass.isField(genFeature)) {
    stringBuffer.append(TEXT_1320);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_1321);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_1322);
    } else {
    if (genFeature.isField() && genClass.getImplementingGenModel(genFeature).isVirtualDelegation()) {
    stringBuffer.append(TEXT_1323);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_1324);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_1325);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_1326);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(positiveOffsetCorrection);
    stringBuffer.append(TEXT_1327);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_1328);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_1329);
    } else {
    stringBuffer.append(TEXT_1330);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_1331);
    }
    }
    }
    } else if (genFeature.isUnsettable()) {
    stringBuffer.append(TEXT_1332);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_1333);
    } else if (genFeature.isResolveProxies()) {
    if (genClass.isField(genFeature)) {
    stringBuffer.append(TEXT_1334);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_1335);
    } else {
    if (genFeature.isField() && genClass.getImplementingGenModel(genFeature).isVirtualDelegation()) {
    stringBuffer.append(TEXT_1336);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(positiveOffsetCorrection);
    stringBuffer.append(TEXT_1337);
    } else {
    stringBuffer.append(TEXT_1338);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_1339);
    }
    }
    } else if (!genFeature.hasEDefault()) {
    if (genClass.isField(genFeature)) {
    stringBuffer.append(TEXT_1340);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_1341);
    } else {
    if (genFeature.isField() && genClass.getImplementingGenModel(genFeature).isVirtualDelegation()) {
    stringBuffer.append(TEXT_1342);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(positiveOffsetCorrection);
    stringBuffer.append(TEXT_1343);
    } else {
    stringBuffer.append(TEXT_1344);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_1345);
    }
    }
    } else if (genFeature.isPrimitiveType() || genFeature.isEnumType()) {
    if (genClass.isField(genFeature)) {
    if (genClass.isFlag(genFeature)) {
    if (genFeature.isBooleanType()) {
    stringBuffer.append(TEXT_1346);
    stringBuffer.append(genClass.getFlagsField(genFeature));
    stringBuffer.append(TEXT_1347);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_1348);
    stringBuffer.append(genFeature.getEDefault());
    stringBuffer.append(TEXT_1349);
    } else {
    stringBuffer.append(TEXT_1350);
    stringBuffer.append(genClass.getFlagsField(genFeature));
    stringBuffer.append(TEXT_1351);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_1352);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_1353);
    }
    } else {
    stringBuffer.append(TEXT_1354);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_1355);
    stringBuffer.append(genFeature.getEDefault());
    stringBuffer.append(TEXT_1356);
    }
    } else {
    if (genFeature.isEnumType() && genFeature.isField() && genClass.getImplementingGenModel(genFeature).isVirtualDelegation()) {
    stringBuffer.append(TEXT_1357);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(positiveOffsetCorrection);
    stringBuffer.append(TEXT_1358);
    stringBuffer.append(genFeature.getEDefault());
    stringBuffer.append(TEXT_1359);
    stringBuffer.append(genFeature.getEDefault());
    stringBuffer.append(TEXT_1360);
    } else {
    stringBuffer.append(TEXT_1361);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_1362);
    stringBuffer.append(genFeature.getEDefault());
    stringBuffer.append(TEXT_1363);
    }
    }
    } else {//datatype
    if (genClass.isField(genFeature)) {
    stringBuffer.append(TEXT_1364);
    stringBuffer.append(genFeature.getEDefault());
    stringBuffer.append(TEXT_1365);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_1366);
    stringBuffer.append(genFeature.getEDefault());
    stringBuffer.append(TEXT_1367);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_1368);
    } else {
    if (genFeature.isField() && genClass.getImplementingGenModel(genFeature).isVirtualDelegation()) {
    stringBuffer.append(TEXT_1369);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_1370);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_1371);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_1372);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(positiveOffsetCorrection);
    stringBuffer.append(TEXT_1373);
    stringBuffer.append(genFeature.getEDefault());
    stringBuffer.append(TEXT_1374);
    stringBuffer.append(genFeature.getEDefault());
    stringBuffer.append(TEXT_1375);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_1376);
    stringBuffer.append(genFeature.getEDefault());
    stringBuffer.append(TEXT_1377);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_1378);
    } else {
    stringBuffer.append(TEXT_1379);
    stringBuffer.append(genFeature.getEDefault());
    stringBuffer.append(TEXT_1380);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_1381);
    stringBuffer.append(genFeature.getEDefault());
    stringBuffer.append(TEXT_1382);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_1383);
    }
    }
    }
    }
    stringBuffer.append(TEXT_1384);
    if (genModel.isMinimalReflectiveMethods()) {
    stringBuffer.append(TEXT_1385);
    } else {
    stringBuffer.append(TEXT_1386);
    }
    stringBuffer.append(TEXT_1387);
    //Class/eIsSet.override.javajetinc
    }
    if (isImplementation && (!genClass.getMixinGenFeatures().isEmpty() || genClass.hasOffsetCorrection() && !genClass.getGenFeatures().isEmpty())) {
    if (!genClass.getMixinGenFeatures().isEmpty()) {
    stringBuffer.append(TEXT_1388);
    if (genModel.useClassOverrideAnnotation()) {
    stringBuffer.append(TEXT_1389);
    }
    stringBuffer.append(TEXT_1390);
    stringBuffer.append(singleWildcard);
    stringBuffer.append(TEXT_1391);
    for (GenClass mixinGenClass : genClass.getMixinGenClasses()) {
    stringBuffer.append(TEXT_1392);
    stringBuffer.append(mixinGenClass.getRawImportedInterfaceName());
    stringBuffer.append(TEXT_1393);
    stringBuffer.append(negativeOffsetCorrection);
    stringBuffer.append(TEXT_1394);
    for (GenFeature genFeature : mixinGenClass.getGenFeatures()) {
    stringBuffer.append(TEXT_1395);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(TEXT_1396);
    stringBuffer.append(mixinGenClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(TEXT_1397);
    }
    stringBuffer.append(TEXT_1398);
    }
    stringBuffer.append(TEXT_1399);
    }
    stringBuffer.append(TEXT_1400);
    if (genModel.useClassOverrideAnnotation()) {
    stringBuffer.append(TEXT_1401);
    }
    stringBuffer.append(TEXT_1402);
    stringBuffer.append(singleWildcard);
    stringBuffer.append(TEXT_1403);
    for (GenClass mixinGenClass : genClass.getMixinGenClasses()) {
    stringBuffer.append(TEXT_1404);
    stringBuffer.append(mixinGenClass.getRawImportedInterfaceName());
    stringBuffer.append(TEXT_1405);
    for (GenFeature genFeature : mixinGenClass.getGenFeatures()) {
    stringBuffer.append(TEXT_1406);
    stringBuffer.append(mixinGenClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(TEXT_1407);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(positiveOffsetCorrection);
    stringBuffer.append(TEXT_1408);
    }
    stringBuffer.append(TEXT_1409);
    }
    if (genClass.hasOffsetCorrection() && !genClass.getGenFeatures().isEmpty()) {
    stringBuffer.append(TEXT_1410);
    stringBuffer.append(genClass.getRawImportedInterfaceName());
    stringBuffer.append(TEXT_1411);
    stringBuffer.append(negativeOffsetCorrection);
    stringBuffer.append(TEXT_1412);
    for (GenFeature genFeature : genClass.getGenFeatures()) {
    stringBuffer.append(TEXT_1413);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(TEXT_1414);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(positiveOffsetCorrection);
    stringBuffer.append(TEXT_1415);
    }
    stringBuffer.append(TEXT_1416);
    }
    stringBuffer.append(TEXT_1417);
    }
    if (isImplementation && genModel.isVirtualDelegation()) { String eVirtualValuesField = genClass.getEVirtualValuesField();
    if (eVirtualValuesField != null) {
    stringBuffer.append(TEXT_1418);
    if (genModel.useClassOverrideAnnotation()) {
    stringBuffer.append(TEXT_1419);
    }
    stringBuffer.append(TEXT_1420);
    stringBuffer.append(eVirtualValuesField);
    stringBuffer.append(TEXT_1421);
    if (genModel.useClassOverrideAnnotation()) {
    stringBuffer.append(TEXT_1422);
    }
    stringBuffer.append(TEXT_1423);
    stringBuffer.append(eVirtualValuesField);
    stringBuffer.append(TEXT_1424);
    }
    { List<String> eVirtualIndexBitFields = genClass.getEVirtualIndexBitFields(new ArrayList<String>());
    if (!eVirtualIndexBitFields.isEmpty()) { List<String> allEVirtualIndexBitFields = genClass.getAllEVirtualIndexBitFields(new ArrayList<String>());
    stringBuffer.append(TEXT_1425);
    if (genModel.useClassOverrideAnnotation()) {
    stringBuffer.append(TEXT_1426);
    }
    stringBuffer.append(TEXT_1427);
    for (int i = 0; i < allEVirtualIndexBitFields.size(); i++) {
    stringBuffer.append(TEXT_1428);
    stringBuffer.append(i);
    stringBuffer.append(TEXT_1429);
    stringBuffer.append(allEVirtualIndexBitFields.get(i));
    stringBuffer.append(TEXT_1430);
    }
    stringBuffer.append(TEXT_1431);
    if (genModel.useClassOverrideAnnotation()) {
    stringBuffer.append(TEXT_1432);
    }
    stringBuffer.append(TEXT_1433);
    for (int i = 0; i < allEVirtualIndexBitFields.size(); i++) {
    stringBuffer.append(TEXT_1434);
    stringBuffer.append(i);
    stringBuffer.append(TEXT_1435);
    stringBuffer.append(allEVirtualIndexBitFields.get(i));
    stringBuffer.append(TEXT_1436);
    }
    stringBuffer.append(TEXT_1437);
    }
    }
    }
    if (!genClass.hasImplementedToStringGenOperation() && isImplementation && !genModel.isReflectiveDelegation() && !genClass.getToStringGenFeatures().isEmpty()) {
    stringBuffer.append(TEXT_1438);
    if (genModel.useClassOverrideAnnotation()) {
    stringBuffer.append(TEXT_1439);
    }
    stringBuffer.append(TEXT_1440);
    { boolean first = true;
    for (GenFeature genFeature : genClass.getToStringGenFeatures()) {
    if (first) { first = false;
    stringBuffer.append(TEXT_1441);
    stringBuffer.append(genFeature.getName());
    stringBuffer.append(TEXT_1442);
    stringBuffer.append(genModel.getNonNLS());
    } else {
    stringBuffer.append(TEXT_1443);
    stringBuffer.append(genFeature.getName());
    stringBuffer.append(TEXT_1444);
    stringBuffer.append(genModel.getNonNLS());
    }
    if (genFeature.isUnsettable() && !genFeature.isListType()) {
    if (genModel.isVirtualDelegation() && !genFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_1445);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(positiveOffsetCorrection);
    stringBuffer.append(TEXT_1446);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(positiveOffsetCorrection);
    stringBuffer.append(TEXT_1447);
    stringBuffer.append(genModel.getNonNLS());
    } else {
    if (genClass.isFlag(genFeature)) {
    if (genFeature.isBooleanType()) {
    stringBuffer.append(TEXT_1448);
    if (genClass.isESetFlag(genFeature)) {
    stringBuffer.append(TEXT_1449);
    stringBuffer.append(genClass.getESetFlagsField(genFeature));
    stringBuffer.append(TEXT_1450);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_1451);
    } else {
    stringBuffer.append(genFeature.getUncapName());
    stringBuffer.append(TEXT_1452);
    }
    stringBuffer.append(TEXT_1453);
    stringBuffer.append(genClass.getFlagsField(genFeature));
    stringBuffer.append(TEXT_1454);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_1455);
    stringBuffer.append(genModel.getNonNLS());
    } else {
    stringBuffer.append(TEXT_1456);
    if (genClass.isESetFlag(genFeature)) {
    stringBuffer.append(TEXT_1457);
    stringBuffer.append(genClass.getESetFlagsField(genFeature));
    stringBuffer.append(TEXT_1458);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_1459);
    } else {
    stringBuffer.append(genFeature.getUncapName());
    stringBuffer.append(TEXT_1460);
    }
    stringBuffer.append(TEXT_1461);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_1462);
    stringBuffer.append(genClass.getFlagsField(genFeature));
    stringBuffer.append(TEXT_1463);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_1464);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_1465);
    stringBuffer.append(genModel.getNonNLS());
    }
    } else {
    stringBuffer.append(TEXT_1466);
    if (genClass.isESetFlag(genFeature)) {
    stringBuffer.append(TEXT_1467);
    stringBuffer.append(genClass.getESetFlagsField(genFeature));
    stringBuffer.append(TEXT_1468);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_1469);
    } else {
    stringBuffer.append(genFeature.getUncapName());
    stringBuffer.append(TEXT_1470);
    }
    stringBuffer.append(TEXT_1471);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_1472);
    stringBuffer.append(genModel.getNonNLS());
    }
    }
    } else {
    if (genModel.isVirtualDelegation() && !genFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_1473);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(positiveOffsetCorrection);
    if (!genFeature.isListType() && !genFeature.isReferenceType()){
    stringBuffer.append(TEXT_1474);
    stringBuffer.append(genFeature.getEDefault());
    }
    stringBuffer.append(TEXT_1475);
    } else {
    if (genClass.isFlag(genFeature)) {
    if (genFeature.isBooleanType()) {
    stringBuffer.append(TEXT_1476);
    stringBuffer.append(genClass.getFlagsField(genFeature));
    stringBuffer.append(TEXT_1477);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_1478);
    } else {
    stringBuffer.append(TEXT_1479);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_1480);
    stringBuffer.append(genClass.getFlagsField(genFeature));
    stringBuffer.append(TEXT_1481);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_1482);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_1483);
    }
    } else {
    stringBuffer.append(TEXT_1484);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_1485);
    }
    }
    }
    }
    }
    stringBuffer.append(TEXT_1486);
    }
    if (isImplementation && genClass.isMapEntry()) { GenFeature keyFeature = genClass.getMapEntryKeyFeature(); GenFeature valueFeature = genClass.getMapEntryValueFeature();
    String objectType = genModel.getImportedName("java.lang.Object");
    String keyType = genModel.getComplianceLevel().getValue() >= GenJDKLevel.JDK50 ? keyFeature.getObjectType(genClass) : objectType;
    String valueType = genModel.getComplianceLevel().getValue() >= GenJDKLevel.JDK50 ? valueFeature.getObjectType(genClass) : objectType;
    String eMapType = genModel.getImportedName("org.eclipse.emf.common.util.EMap") + (genModel.getComplianceLevel().getValue() >= GenJDKLevel.JDK50 ? "<" + keyType + ", " + valueType + ">" : "");
    stringBuffer.append(TEXT_1487);
    stringBuffer.append(objectType);
    stringBuffer.append(TEXT_1488);
    stringBuffer.append(keyType);
    stringBuffer.append(TEXT_1489);
    if (keyFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_1490);
    stringBuffer.append(keyFeature.getObjectType(genClass));
    stringBuffer.append(TEXT_1491);
    } else {
    stringBuffer.append(TEXT_1492);
    }
    stringBuffer.append(TEXT_1493);
    stringBuffer.append(keyType);
    stringBuffer.append(TEXT_1494);
    if (keyFeature.isListType()) {
    stringBuffer.append(TEXT_1495);
    if (!genModel.useGenerics()) {
    stringBuffer.append(TEXT_1496);
    stringBuffer.append(genModel.getImportedName("java.util.Collection"));
    stringBuffer.append(TEXT_1497);
    }
    stringBuffer.append(TEXT_1498);
    } else if (genModel.getComplianceLevel().getValue() >= GenJDKLevel.JDK50) {
    stringBuffer.append(TEXT_1499);
    } else if (keyFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_1500);
    stringBuffer.append(keyFeature.getObjectType(genClass));
    stringBuffer.append(TEXT_1501);
    stringBuffer.append(keyFeature.getPrimitiveValueFunction());
    stringBuffer.append(TEXT_1502);
    } else {
    stringBuffer.append(TEXT_1503);
    stringBuffer.append(keyFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_1504);
    }
    stringBuffer.append(TEXT_1505);
    stringBuffer.append(valueType);
    stringBuffer.append(TEXT_1506);
    if (valueFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_1507);
    stringBuffer.append(valueFeature.getObjectType(genClass));
    stringBuffer.append(TEXT_1508);
    } else {
    stringBuffer.append(TEXT_1509);
    }
    stringBuffer.append(TEXT_1510);
    stringBuffer.append(valueType);
    stringBuffer.append(TEXT_1511);
    stringBuffer.append(valueType);
    stringBuffer.append(TEXT_1512);
    stringBuffer.append(valueType);
    stringBuffer.append(TEXT_1513);
    if (valueFeature.isListType()) {
    stringBuffer.append(TEXT_1514);
    if (!genModel.useGenerics()) {
    stringBuffer.append(TEXT_1515);
    stringBuffer.append(genModel.getImportedName("java.util.Collection"));
    stringBuffer.append(TEXT_1516);
    }
    stringBuffer.append(TEXT_1517);
    } else if (genModel.getComplianceLevel().getValue() >= GenJDKLevel.JDK50) {
    stringBuffer.append(TEXT_1518);
    } else if (valueFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_1519);
    stringBuffer.append(valueFeature.getObjectType(genClass));
    stringBuffer.append(TEXT_1520);
    stringBuffer.append(valueFeature.getPrimitiveValueFunction());
    stringBuffer.append(TEXT_1521);
    } else {
    stringBuffer.append(TEXT_1522);
    stringBuffer.append(valueFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_1523);
    }
    stringBuffer.append(TEXT_1524);
    if (genModel.useGenerics()) {
    stringBuffer.append(TEXT_1525);
    }
    stringBuffer.append(TEXT_1526);
    stringBuffer.append(eMapType);
    stringBuffer.append(TEXT_1527);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.EObject"));
    stringBuffer.append(TEXT_1528);
    stringBuffer.append(eMapType);
    stringBuffer.append(TEXT_1529);
    }
    stringBuffer.append(TEXT_1530);
    stringBuffer.append(isInterface ? " " + genClass.getInterfaceName() : genClass.getClassName());
    // TODO fix the space above
    genModel.emitSortedImports();
    stringBuffer.append(TEXT_1531);
    return stringBuffer.toString();
  }
}
