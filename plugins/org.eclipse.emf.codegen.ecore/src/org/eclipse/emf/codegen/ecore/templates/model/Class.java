package org.eclipse.emf.codegen.ecore.templates.model;

import java.util.*;
import org.eclipse.emf.codegen.ecore.genmodel.*;
import org.eclipse.emf.codegen.util.CodeGenUtil;
import org.eclipse.emf.codegen.ecore.genmodel.util.GenModelUtil;

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
  protected final String TEXT_16 = NL + " * <p>" + NL + " * The following features are supported:" + NL + " * </p>" + NL + " * <ul>";
  protected final String TEXT_17 = NL + " *   <li>{@link ";
  protected final String TEXT_18 = "#";
  protected final String TEXT_19 = " <em>";
  protected final String TEXT_20 = "</em>}</li>";
  protected final String TEXT_21 = NL + " * </ul>";
  protected final String TEXT_22 = NL + " *";
  protected final String TEXT_23 = NL + " * @see ";
  protected final String TEXT_24 = "#get";
  protected final String TEXT_25 = "()";
  protected final String TEXT_26 = NL + " * @model ";
  protected final String TEXT_27 = NL + " *        ";
  protected final String TEXT_28 = NL + " * @model";
  protected final String TEXT_29 = NL + " * @extends ";
  protected final String TEXT_30 = NL + " * ";
  protected final String TEXT_31 = NL + " * @generated" + NL + " */";
  protected final String TEXT_32 = NL + "/**" + NL + " * <!-- begin-user-doc -->" + NL + " * An implementation of the model object '<em><b>";
  protected final String TEXT_33 = "</b></em>'." + NL + " * <!-- end-user-doc -->";
  protected final String TEXT_34 = NL + " * <p>" + NL + " * The following features are implemented:" + NL + " * </p>" + NL + " * <ul>";
  protected final String TEXT_35 = NL + " *   <li>{@link ";
  protected final String TEXT_36 = "#";
  protected final String TEXT_37 = " <em>";
  protected final String TEXT_38 = "</em>}</li>";
  protected final String TEXT_39 = NL + " * </ul>";
  protected final String TEXT_40 = NL + " *";
  protected final String TEXT_41 = NL + " * ";
  protected final String TEXT_42 = NL + " * @generated" + NL + " */";
  protected final String TEXT_43 = NL + "@Deprecated";
  protected final String TEXT_44 = NL + "@SuppressWarnings(\"deprecation\")";
  protected final String TEXT_45 = NL + "public";
  protected final String TEXT_46 = " abstract";
  protected final String TEXT_47 = " class ";
  protected final String TEXT_48 = NL + "public interface ";
  protected final String TEXT_49 = NL + "{";
  protected final String TEXT_50 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */" + NL + "\t";
  protected final String TEXT_51 = " copyright = ";
  protected final String TEXT_52 = ";";
  protected final String TEXT_53 = NL;
  protected final String TEXT_54 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */" + NL + "\tpublic static final ";
  protected final String TEXT_55 = " mofDriverNumber = \"";
  protected final String TEXT_56 = "\";";
  protected final String TEXT_57 = NL;
  protected final String TEXT_58 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */" + NL + "\tprivate static final long serialVersionUID = 1L;" + NL;
  protected final String TEXT_59 = NL + "\t/**" + NL + "\t * An array of objects representing the values of non-primitive features." + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */";
  protected final String TEXT_60 = NL + "\t@";
  protected final String TEXT_61 = NL + "\tprotected Object[] ";
  protected final String TEXT_62 = ";" + NL;
  protected final String TEXT_63 = NL + "\t/**" + NL + "\t * A bit field representing the indices of non-primitive feature values." + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */";
  protected final String TEXT_64 = NL + "\t@";
  protected final String TEXT_65 = NL + "\tprotected int ";
  protected final String TEXT_66 = ";" + NL;
  protected final String TEXT_67 = NL + "\t/**" + NL + "\t * A set of bit flags representing the values of boolean attributes and whether unsettable features have been set." + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t * @ordered" + NL + "\t */";
  protected final String TEXT_68 = NL + "\t@";
  protected final String TEXT_69 = NL + "\tprotected int ";
  protected final String TEXT_70 = " = 0;" + NL;
  protected final String TEXT_71 = NL + "\t/**" + NL + "\t * The empty value for the '{@link #";
  protected final String TEXT_72 = "() <em>";
  protected final String TEXT_73 = "</em>}' array accessor." + NL + "\t * This is specialized for the more specific element type known in this context." + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @see #";
  protected final String TEXT_74 = "()";
  protected final String TEXT_75 = NL + "\t * ";
  protected final String TEXT_76 = NL + "\t * @generated" + NL + "\t * @ordered" + NL + "\t */";
  protected final String TEXT_77 = NL + "\t@Deprecated";
  protected final String TEXT_78 = NL + "\t@SuppressWarnings(\"rawtypes\")";
  protected final String TEXT_79 = NL + "\tprotected static final ";
  protected final String TEXT_80 = "[] ";
  protected final String TEXT_81 = "_EEMPTY_ARRAY = new ";
  protected final String TEXT_82 = " [0]";
  protected final String TEXT_83 = ";" + NL;
  protected final String TEXT_84 = NL + "\t/**" + NL + "\t * The cached setting delegate for the '{@link #";
  protected final String TEXT_85 = "() <em>";
  protected final String TEXT_86 = "</em>}' ";
  protected final String TEXT_87 = "." + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @see #";
  protected final String TEXT_88 = "()";
  protected final String TEXT_89 = NL + "\t * ";
  protected final String TEXT_90 = NL + "\t * @generated" + NL + "\t * @ordered" + NL + "\t */";
  protected final String TEXT_91 = NL + "\t@";
  protected final String TEXT_92 = NL + "\t@Deprecated";
  protected final String TEXT_93 = NL + "\tprotected ";
  protected final String TEXT_94 = ".Internal.SettingDelegate ";
  protected final String TEXT_95 = "__ESETTING_DELEGATE = ((";
  protected final String TEXT_96 = ".Internal)";
  protected final String TEXT_97 = ").getSettingDelegate();" + NL;
  protected final String TEXT_98 = NL + "\t/**" + NL + "\t * The cached value of the '{@link #";
  protected final String TEXT_99 = "() <em>";
  protected final String TEXT_100 = "</em>}' ";
  protected final String TEXT_101 = "." + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @see #";
  protected final String TEXT_102 = "()";
  protected final String TEXT_103 = NL + "\t * ";
  protected final String TEXT_104 = NL + "\t * @generated" + NL + "\t * @ordered" + NL + "\t */";
  protected final String TEXT_105 = NL + "\t@";
  protected final String TEXT_106 = NL + "\t@Deprecated";
  protected final String TEXT_107 = NL + "\tprotected ";
  protected final String TEXT_108 = " ";
  protected final String TEXT_109 = ";" + NL;
  protected final String TEXT_110 = NL + "\t/**" + NL + "\t * The empty value for the '{@link #";
  protected final String TEXT_111 = "() <em>";
  protected final String TEXT_112 = "</em>}' array accessor." + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @see #";
  protected final String TEXT_113 = "()";
  protected final String TEXT_114 = NL + "\t * ";
  protected final String TEXT_115 = NL + "\t * @generated" + NL + "\t * @ordered" + NL + "\t */";
  protected final String TEXT_116 = NL + "\t@SuppressWarnings(\"rawtypes\")";
  protected final String TEXT_117 = NL + "\t@Deprecated";
  protected final String TEXT_118 = NL + "\tprotected static final ";
  protected final String TEXT_119 = "[] ";
  protected final String TEXT_120 = "_EEMPTY_ARRAY = new ";
  protected final String TEXT_121 = " [0]";
  protected final String TEXT_122 = ";" + NL;
  protected final String TEXT_123 = NL + "\t/**" + NL + "\t * The default value of the '{@link #";
  protected final String TEXT_124 = "() <em>";
  protected final String TEXT_125 = "</em>}' ";
  protected final String TEXT_126 = "." + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @see #";
  protected final String TEXT_127 = "()";
  protected final String TEXT_128 = NL + "\t * ";
  protected final String TEXT_129 = NL + "\t * @generated" + NL + "\t * @ordered" + NL + "\t */";
  protected final String TEXT_130 = NL + "\t@SuppressWarnings(\"unchecked\")";
  protected final String TEXT_131 = NL + "\t@Deprecated";
  protected final String TEXT_132 = NL + "\tprotected static final ";
  protected final String TEXT_133 = " ";
  protected final String TEXT_134 = "; // TODO The default value literal \"";
  protected final String TEXT_135 = "\" is not valid.";
  protected final String TEXT_136 = " = ";
  protected final String TEXT_137 = ";";
  protected final String TEXT_138 = NL;
  protected final String TEXT_139 = NL + "\t/**" + NL + "\t * An additional set of bit flags representing the values of boolean attributes and whether unsettable features have been set." + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t * @ordered" + NL + "\t */";
  protected final String TEXT_140 = NL + "\t@";
  protected final String TEXT_141 = NL + "\tprotected int ";
  protected final String TEXT_142 = " = 0;" + NL;
  protected final String TEXT_143 = NL + "\t/**" + NL + "\t * The offset of the flags representing the value of the '{@link #";
  protected final String TEXT_144 = "() <em>";
  protected final String TEXT_145 = "</em>}' ";
  protected final String TEXT_146 = "." + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->";
  protected final String TEXT_147 = NL + "\t * ";
  protected final String TEXT_148 = NL + "\t * @generated" + NL + "\t * @ordered" + NL + "\t */";
  protected final String TEXT_149 = NL + "\t@Deprecated";
  protected final String TEXT_150 = NL + "\tprotected static final int ";
  protected final String TEXT_151 = "_EFLAG_OFFSET = ";
  protected final String TEXT_152 = ";" + NL + "" + NL + "\t/**" + NL + "\t * The flags representing the default value of the '{@link #";
  protected final String TEXT_153 = "() <em>";
  protected final String TEXT_154 = "</em>}' ";
  protected final String TEXT_155 = "." + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->";
  protected final String TEXT_156 = NL + "\t * ";
  protected final String TEXT_157 = NL + "\t * @generated" + NL + "\t * @ordered" + NL + "\t */";
  protected final String TEXT_158 = NL + "\t@Deprecated";
  protected final String TEXT_159 = NL + "\tprotected static final int ";
  protected final String TEXT_160 = "_EFLAG_DEFAULT = ";
  protected final String TEXT_161 = ".ordinal()";
  protected final String TEXT_162 = ".VALUES.indexOf(";
  protected final String TEXT_163 = ")";
  protected final String TEXT_164 = " << ";
  protected final String TEXT_165 = "_EFLAG_OFFSET;" + NL + "" + NL + "\t/**" + NL + "\t * The array of enumeration values for '{@link ";
  protected final String TEXT_166 = " ";
  protected final String TEXT_167 = "}'" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->";
  protected final String TEXT_168 = NL + "\t * ";
  protected final String TEXT_169 = NL + "\t * @generated" + NL + "\t * @ordered" + NL + "\t */";
  protected final String TEXT_170 = NL + "\t@Deprecated";
  protected final String TEXT_171 = NL + "\tprivate static final ";
  protected final String TEXT_172 = "[] ";
  protected final String TEXT_173 = "_EFLAG_VALUES = ";
  protected final String TEXT_174 = ".values()";
  protected final String TEXT_175 = "(";
  protected final String TEXT_176 = "[])";
  protected final String TEXT_177 = ".VALUES.toArray(new ";
  protected final String TEXT_178 = "[";
  protected final String TEXT_179 = ".VALUES.size()])";
  protected final String TEXT_180 = ";" + NL;
  protected final String TEXT_181 = NL + "\t/**" + NL + "\t * The flag";
  protected final String TEXT_182 = " representing the value of the '{@link #";
  protected final String TEXT_183 = "() <em>";
  protected final String TEXT_184 = "</em>}' ";
  protected final String TEXT_185 = "." + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @see #";
  protected final String TEXT_186 = "()";
  protected final String TEXT_187 = NL + "\t * ";
  protected final String TEXT_188 = NL + "\t * @generated" + NL + "\t * @ordered" + NL + "\t */";
  protected final String TEXT_189 = NL + "\t@Deprecated";
  protected final String TEXT_190 = NL + "\tprotected static final int ";
  protected final String TEXT_191 = "_EFLAG = ";
  protected final String TEXT_192 = " << ";
  protected final String TEXT_193 = "_EFLAG_OFFSET";
  protected final String TEXT_194 = ";" + NL;
  protected final String TEXT_195 = NL + "\t/**" + NL + "\t * The cached value of the '{@link #";
  protected final String TEXT_196 = "() <em>";
  protected final String TEXT_197 = "</em>}' ";
  protected final String TEXT_198 = "." + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @see #";
  protected final String TEXT_199 = "()";
  protected final String TEXT_200 = NL + "\t * ";
  protected final String TEXT_201 = NL + "\t * @generated" + NL + "\t * @ordered" + NL + "\t */";
  protected final String TEXT_202 = NL + "\t@";
  protected final String TEXT_203 = NL + "\t@Deprecated";
  protected final String TEXT_204 = NL + "\tprotected ";
  protected final String TEXT_205 = " ";
  protected final String TEXT_206 = " = ";
  protected final String TEXT_207 = ";" + NL;
  protected final String TEXT_208 = NL + "\t/**" + NL + "\t * An additional set of bit flags representing the values of boolean attributes and whether unsettable features have been set." + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t * @ordered" + NL + "\t */";
  protected final String TEXT_209 = NL + "\t@";
  protected final String TEXT_210 = NL + "\tprotected int ";
  protected final String TEXT_211 = " = 0;" + NL;
  protected final String TEXT_212 = NL + "\t/**" + NL + "\t * The flag representing whether the ";
  protected final String TEXT_213 = " ";
  protected final String TEXT_214 = " has been set." + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->";
  protected final String TEXT_215 = NL + "\t * ";
  protected final String TEXT_216 = NL + "\t * @generated" + NL + "\t * @ordered" + NL + "\t */";
  protected final String TEXT_217 = NL + "\t * ";
  protected final String TEXT_218 = NL + "\tprotected static final int ";
  protected final String TEXT_219 = "_ESETFLAG = 1 << ";
  protected final String TEXT_220 = ";" + NL;
  protected final String TEXT_221 = NL + "\t/**" + NL + "\t * This is true if the ";
  protected final String TEXT_222 = " ";
  protected final String TEXT_223 = " has been set." + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->";
  protected final String TEXT_224 = NL + "\t * ";
  protected final String TEXT_225 = NL + "\t * @generated" + NL + "\t * @ordered" + NL + "\t */";
  protected final String TEXT_226 = NL + "\t@";
  protected final String TEXT_227 = NL + "\t@Deprecated";
  protected final String TEXT_228 = NL + "\tprotected boolean ";
  protected final String TEXT_229 = "ESet;" + NL;
  protected final String TEXT_230 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */" + NL + "\tprivate static final int ";
  protected final String TEXT_231 = " = ";
  protected final String TEXT_232 = ".getFeatureID(";
  protected final String TEXT_233 = ") - ";
  protected final String TEXT_234 = ";" + NL;
  protected final String TEXT_235 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->";
  protected final String TEXT_236 = NL + "\t * ";
  protected final String TEXT_237 = NL + "\t * @generated" + NL + "\t */";
  protected final String TEXT_238 = NL + "\t@Deprecated";
  protected final String TEXT_239 = NL + "\tprivate static final int ";
  protected final String TEXT_240 = " = ";
  protected final String TEXT_241 = ".getFeatureID(";
  protected final String TEXT_242 = ") - ";
  protected final String TEXT_243 = ";" + NL;
  protected final String TEXT_244 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */" + NL + "\tprivate static final int EOPERATION_OFFSET_CORRECTION = ";
  protected final String TEXT_245 = ".getOperationID(";
  protected final String TEXT_246 = ") - ";
  protected final String TEXT_247 = ";" + NL;
  protected final String TEXT_248 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */" + NL + "\t";
  protected final String TEXT_249 = "public";
  protected final String TEXT_250 = "protected";
  protected final String TEXT_251 = " ";
  protected final String TEXT_252 = "()" + NL + "\t{" + NL + "\t\tsuper();";
  protected final String TEXT_253 = NL + "\t\t";
  protected final String TEXT_254 = " |= ";
  protected final String TEXT_255 = "_EFLAG";
  protected final String TEXT_256 = "_DEFAULT";
  protected final String TEXT_257 = ";";
  protected final String TEXT_258 = NL + "\t}" + NL + "" + NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */";
  protected final String TEXT_259 = NL + "\t@Override";
  protected final String TEXT_260 = NL + "\tprotected ";
  protected final String TEXT_261 = " eStaticClass()" + NL + "\t{" + NL + "\t\treturn ";
  protected final String TEXT_262 = ";" + NL + "\t}" + NL;
  protected final String TEXT_263 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */" + NL + "\tprotected static final int ESTATIC_FEATURE_COUNT = ";
  protected final String TEXT_264 = ";" + NL;
  protected final String TEXT_265 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */";
  protected final String TEXT_266 = NL + "\t@Override";
  protected final String TEXT_267 = NL + "\tprotected int eStaticFeatureCount()" + NL + "\t{" + NL + "\t\treturn ";
  protected final String TEXT_268 = ";" + NL + "\t}" + NL;
  protected final String TEXT_269 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * This is specialized for the more specific element type known in this context." + NL + "\t * @see #";
  protected final String TEXT_270 = "()";
  protected final String TEXT_271 = NL + "\t * ";
  protected final String TEXT_272 = NL + "\t * @generated" + NL + "\t */";
  protected final String TEXT_273 = NL + "\t@SuppressWarnings(\"unchecked\")";
  protected final String TEXT_274 = NL + "\t@Deprecated";
  protected final String TEXT_275 = NL + "\t@Override";
  protected final String TEXT_276 = NL + "\tpublic ";
  protected final String TEXT_277 = "[] ";
  protected final String TEXT_278 = "()" + NL + "\t{";
  protected final String TEXT_279 = NL + "\t\t";
  protected final String TEXT_280 = " list = (";
  protected final String TEXT_281 = ")";
  protected final String TEXT_282 = "();" + NL + "\t\tif (list.isEmpty()) return ";
  protected final String TEXT_283 = "_EEMPTY_ARRAY;";
  protected final String TEXT_284 = NL + "\t\tif (";
  protected final String TEXT_285 = " == null || ";
  protected final String TEXT_286 = ".isEmpty()) return ";
  protected final String TEXT_287 = "_EEMPTY_ARRAY;" + NL + "\t\t";
  protected final String TEXT_288 = " list = (";
  protected final String TEXT_289 = ")";
  protected final String TEXT_290 = ";";
  protected final String TEXT_291 = NL + "\t\tlist.shrink();" + NL + "\t\treturn (";
  protected final String TEXT_292 = "[])list.data();" + NL + "\t}" + NL;
  protected final String TEXT_293 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * This is specialized for the more specific element type known in this context.";
  protected final String TEXT_294 = NL + "\t * ";
  protected final String TEXT_295 = NL + "\t * @generated" + NL + "\t */";
  protected final String TEXT_296 = NL + "\t@SuppressWarnings(\"unchecked\")";
  protected final String TEXT_297 = NL + "\t@Deprecated";
  protected final String TEXT_298 = NL + "\t@Override";
  protected final String TEXT_299 = NL + "\tpublic ";
  protected final String TEXT_300 = " ";
  protected final String TEXT_301 = "_";
  protected final String TEXT_302 = "()" + NL + "\t{";
  protected final String TEXT_303 = NL + "\t\t";
  protected final String TEXT_304 = " ";
  protected final String TEXT_305 = " = (";
  protected final String TEXT_306 = ")eVirtualGet(";
  protected final String TEXT_307 = ");";
  protected final String TEXT_308 = NL + "\t\tif (";
  protected final String TEXT_309 = " == null)" + NL + "\t\t{";
  protected final String TEXT_310 = NL + "\t\t\teVirtualSet(";
  protected final String TEXT_311 = ", ";
  protected final String TEXT_312 = " = new ";
  protected final String TEXT_313 = ");";
  protected final String TEXT_314 = NL + "\t\t\t";
  protected final String TEXT_315 = " = new ";
  protected final String TEXT_316 = ";";
  protected final String TEXT_317 = NL + "\t\t}" + NL + "\t\treturn ";
  protected final String TEXT_318 = ";" + NL + "\t}" + NL;
  protected final String TEXT_319 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * This is specialized for the more specific type known in this context.";
  protected final String TEXT_320 = NL + "\t * ";
  protected final String TEXT_321 = NL + "\t * @generated" + NL + "\t */";
  protected final String TEXT_322 = NL + "\t@Deprecated";
  protected final String TEXT_323 = NL + "\t@Override";
  protected final String TEXT_324 = NL + "\tpublic ";
  protected final String TEXT_325 = " basicSet";
  protected final String TEXT_326 = "(";
  protected final String TEXT_327 = " new";
  protected final String TEXT_328 = ", ";
  protected final String TEXT_329 = " msgs)" + NL + "\t{" + NL + "\t\treturn super.basicSet";
  protected final String TEXT_330 = "(new";
  protected final String TEXT_331 = ", msgs);" + NL + "\t}" + NL;
  protected final String TEXT_332 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * This is specialized for the more specific type known in this context.";
  protected final String TEXT_333 = NL + "\t * ";
  protected final String TEXT_334 = NL + "\t * @generated" + NL + "\t */";
  protected final String TEXT_335 = NL + "\t@Deprecated";
  protected final String TEXT_336 = NL + "\t@Override";
  protected final String TEXT_337 = NL + "\tpublic void set";
  protected final String TEXT_338 = "_";
  protected final String TEXT_339 = "(";
  protected final String TEXT_340 = " ";
  protected final String TEXT_341 = ")" + NL + "\t{" + NL + "\t\tsuper.set";
  protected final String TEXT_342 = "_";
  protected final String TEXT_343 = "(";
  protected final String TEXT_344 = ");" + NL + "\t}" + NL;
  protected final String TEXT_345 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->";
  protected final String TEXT_346 = NL + "\t * ";
  protected final String TEXT_347 = NL + "\t * @generated" + NL + "\t */";
  protected final String TEXT_348 = NL + "\t";
  protected final String TEXT_349 = "[] ";
  protected final String TEXT_350 = "();" + NL;
  protected final String TEXT_351 = NL + "\t@SuppressWarnings(\"unchecked\")";
  protected final String TEXT_352 = NL + "\t@Deprecated";
  protected final String TEXT_353 = NL + "\tpublic ";
  protected final String TEXT_354 = "[] ";
  protected final String TEXT_355 = "()" + NL + "\t{";
  protected final String TEXT_356 = NL + "\t\t";
  protected final String TEXT_357 = " list = (";
  protected final String TEXT_358 = ")";
  protected final String TEXT_359 = "();" + NL + "\t\tif (list.isEmpty()) return ";
  protected final String TEXT_360 = "_EEMPTY_ARRAY;";
  protected final String TEXT_361 = NL + "\t\tif (";
  protected final String TEXT_362 = " == null || ";
  protected final String TEXT_363 = ".isEmpty()) return ";
  protected final String TEXT_364 = "_EEMPTY_ARRAY;" + NL + "\t\t";
  protected final String TEXT_365 = " list = (";
  protected final String TEXT_366 = ")";
  protected final String TEXT_367 = ";";
  protected final String TEXT_368 = NL + "\t\tlist.shrink();" + NL + "\t\treturn (";
  protected final String TEXT_369 = "[])list.data();" + NL + "\t}" + NL;
  protected final String TEXT_370 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->";
  protected final String TEXT_371 = NL + "\t * ";
  protected final String TEXT_372 = NL + "\t * @generated" + NL + "\t */";
  protected final String TEXT_373 = NL + "\t@Deprecated";
  protected final String TEXT_374 = NL + "\t";
  protected final String TEXT_375 = " get";
  protected final String TEXT_376 = "(int index);" + NL;
  protected final String TEXT_377 = NL + "\tpublic ";
  protected final String TEXT_378 = " get";
  protected final String TEXT_379 = "(int index)" + NL + "\t{" + NL + "\t\treturn ";
  protected final String TEXT_380 = "(";
  protected final String TEXT_381 = ")";
  protected final String TEXT_382 = "().get(index);" + NL + "\t}" + NL;
  protected final String TEXT_383 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->";
  protected final String TEXT_384 = NL + "\t * ";
  protected final String TEXT_385 = NL + "\t * @generated" + NL + "\t */";
  protected final String TEXT_386 = NL + "\t@Deprecated";
  protected final String TEXT_387 = NL + "\tint get";
  protected final String TEXT_388 = "Length();" + NL;
  protected final String TEXT_389 = NL + "\tpublic int get";
  protected final String TEXT_390 = "Length()" + NL + "\t{";
  protected final String TEXT_391 = NL + "\t\treturn ";
  protected final String TEXT_392 = "().size();";
  protected final String TEXT_393 = NL + "\t\treturn ";
  protected final String TEXT_394 = " == null ? 0 : ";
  protected final String TEXT_395 = ".size();";
  protected final String TEXT_396 = NL + "\t}" + NL;
  protected final String TEXT_397 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->";
  protected final String TEXT_398 = NL + "\t * ";
  protected final String TEXT_399 = NL + "\t * @generated" + NL + "\t */";
  protected final String TEXT_400 = NL + "\t@Deprecated";
  protected final String TEXT_401 = NL + "\tvoid set";
  protected final String TEXT_402 = "(";
  protected final String TEXT_403 = "[] new";
  protected final String TEXT_404 = ");" + NL;
  protected final String TEXT_405 = NL + "\tpublic void set";
  protected final String TEXT_406 = "(";
  protected final String TEXT_407 = "[] new";
  protected final String TEXT_408 = ")" + NL + "\t{" + NL + "\t\t((";
  protected final String TEXT_409 = ")";
  protected final String TEXT_410 = "()).setData(new";
  protected final String TEXT_411 = ".length, new";
  protected final String TEXT_412 = ");" + NL + "\t}" + NL;
  protected final String TEXT_413 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->";
  protected final String TEXT_414 = NL + "\t * ";
  protected final String TEXT_415 = NL + "\t * @generated" + NL + "\t */";
  protected final String TEXT_416 = NL + "\t@Deprecated";
  protected final String TEXT_417 = NL + "\tvoid set";
  protected final String TEXT_418 = "(int index, ";
  protected final String TEXT_419 = " element);" + NL;
  protected final String TEXT_420 = NL + "\tpublic void set";
  protected final String TEXT_421 = "(int index, ";
  protected final String TEXT_422 = " element)" + NL + "\t{" + NL + "\t\t";
  protected final String TEXT_423 = "().set(index, element);" + NL + "\t}" + NL;
  protected final String TEXT_424 = NL + "\t/**" + NL + "\t * Returns the value of the '<em><b>";
  protected final String TEXT_425 = "</b></em>' ";
  protected final String TEXT_426 = ".";
  protected final String TEXT_427 = NL + "\t * The key is of type ";
  protected final String TEXT_428 = "list of {@link ";
  protected final String TEXT_429 = "}";
  protected final String TEXT_430 = "{@link ";
  protected final String TEXT_431 = "}";
  protected final String TEXT_432 = "," + NL + "\t * and the value is of type ";
  protected final String TEXT_433 = "list of {@link ";
  protected final String TEXT_434 = "}";
  protected final String TEXT_435 = "{@link ";
  protected final String TEXT_436 = "}";
  protected final String TEXT_437 = ",";
  protected final String TEXT_438 = NL + "\t * The list contents are of type {@link ";
  protected final String TEXT_439 = "}";
  protected final String TEXT_440 = ".";
  protected final String TEXT_441 = NL + "\t * The default value is <code>";
  protected final String TEXT_442 = "</code>.";
  protected final String TEXT_443 = NL + "\t * The literals are from the enumeration {@link ";
  protected final String TEXT_444 = "}.";
  protected final String TEXT_445 = NL + "\t * It is bidirectional and its opposite is '{@link ";
  protected final String TEXT_446 = "#";
  protected final String TEXT_447 = " <em>";
  protected final String TEXT_448 = "</em>}'.";
  protected final String TEXT_449 = NL + "\t * <!-- begin-user-doc -->";
  protected final String TEXT_450 = NL + "\t * <p>" + NL + "\t * If the meaning of the '<em>";
  protected final String TEXT_451 = "</em>' ";
  protected final String TEXT_452 = " isn't clear," + NL + "\t * there really should be more of a description here..." + NL + "\t * </p>";
  protected final String TEXT_453 = NL + "\t * <!-- end-user-doc -->";
  protected final String TEXT_454 = NL + "\t * <!-- begin-model-doc -->" + NL + "\t * ";
  protected final String TEXT_455 = NL + "\t * <!-- end-model-doc -->";
  protected final String TEXT_456 = NL + "\t * @return the value of the '<em>";
  protected final String TEXT_457 = "</em>' ";
  protected final String TEXT_458 = ".";
  protected final String TEXT_459 = NL + "\t * @see ";
  protected final String TEXT_460 = NL + "\t * @see #isSet";
  protected final String TEXT_461 = "()";
  protected final String TEXT_462 = NL + "\t * @see #unset";
  protected final String TEXT_463 = "()";
  protected final String TEXT_464 = NL + "\t * @see #set";
  protected final String TEXT_465 = "(";
  protected final String TEXT_466 = ")";
  protected final String TEXT_467 = NL + "\t * @see ";
  protected final String TEXT_468 = "#get";
  protected final String TEXT_469 = "()";
  protected final String TEXT_470 = NL + "\t * @see ";
  protected final String TEXT_471 = "#";
  protected final String TEXT_472 = NL + "\t * @model ";
  protected final String TEXT_473 = NL + "\t *        ";
  protected final String TEXT_474 = NL + "\t * @model";
  protected final String TEXT_475 = NL + "\t * @generated" + NL + "\t */";
  protected final String TEXT_476 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->";
  protected final String TEXT_477 = NL + "\t * ";
  protected final String TEXT_478 = NL + "\t * @generated" + NL + "\t */";
  protected final String TEXT_479 = NL + "\t@Deprecated";
  protected final String TEXT_480 = NL + "\t";
  protected final String TEXT_481 = " ";
  protected final String TEXT_482 = "();" + NL;
  protected final String TEXT_483 = NL + "\t@SuppressWarnings(\"unchecked\")";
  protected final String TEXT_484 = NL + "\tpublic ";
  protected final String TEXT_485 = " ";
  protected final String TEXT_486 = "_";
  protected final String TEXT_487 = "()" + NL + "\t{";
  protected final String TEXT_488 = NL + "\t\treturn ";
  protected final String TEXT_489 = "(";
  protected final String TEXT_490 = "(";
  protected final String TEXT_491 = ")eDynamicGet(";
  protected final String TEXT_492 = " - ESTATIC_FEATURE_COUNT";
  protected final String TEXT_493 = ", ";
  protected final String TEXT_494 = ", true, ";
  protected final String TEXT_495 = ")";
  protected final String TEXT_496 = ").";
  protected final String TEXT_497 = "()";
  protected final String TEXT_498 = ";";
  protected final String TEXT_499 = NL + "\t\t";
  protected final String TEXT_500 = NL + "\t\treturn ";
  protected final String TEXT_501 = "(";
  protected final String TEXT_502 = "(";
  protected final String TEXT_503 = ")eGet(";
  protected final String TEXT_504 = ", true)";
  protected final String TEXT_505 = ").";
  protected final String TEXT_506 = "()";
  protected final String TEXT_507 = ";";
  protected final String TEXT_508 = NL + "\t\treturn ";
  protected final String TEXT_509 = "(";
  protected final String TEXT_510 = "(";
  protected final String TEXT_511 = ")";
  protected final String TEXT_512 = "__ESETTING_DELEGATE.dynamicGet(this, null, 0, true, false)";
  protected final String TEXT_513 = ").";
  protected final String TEXT_514 = "()";
  protected final String TEXT_515 = ";";
  protected final String TEXT_516 = NL + "\t\t";
  protected final String TEXT_517 = " ";
  protected final String TEXT_518 = " = (";
  protected final String TEXT_519 = ")eVirtualGet(";
  protected final String TEXT_520 = ");";
  protected final String TEXT_521 = NL + "\t\tif (";
  protected final String TEXT_522 = " == null)" + NL + "\t\t{";
  protected final String TEXT_523 = NL + "\t\t\teVirtualSet(";
  protected final String TEXT_524 = ", ";
  protected final String TEXT_525 = " = new ";
  protected final String TEXT_526 = ");";
  protected final String TEXT_527 = NL + "\t\t\t";
  protected final String TEXT_528 = " = new ";
  protected final String TEXT_529 = ";";
  protected final String TEXT_530 = NL + "\t\t}" + NL + "\t\treturn ";
  protected final String TEXT_531 = ";";
  protected final String TEXT_532 = NL + "\t\tif (eContainerFeatureID() != ";
  protected final String TEXT_533 = ") return null;" + NL + "\t\treturn (";
  protected final String TEXT_534 = ")";
  protected final String TEXT_535 = "eContainer";
  protected final String TEXT_536 = "eInternalContainer";
  protected final String TEXT_537 = "();";
  protected final String TEXT_538 = NL + "\t\t";
  protected final String TEXT_539 = " ";
  protected final String TEXT_540 = " = (";
  protected final String TEXT_541 = ")eVirtualGet(";
  protected final String TEXT_542 = ", ";
  protected final String TEXT_543 = ");";
  protected final String TEXT_544 = NL + "\t\tif (";
  protected final String TEXT_545 = " != null && ";
  protected final String TEXT_546 = ".eIsProxy())" + NL + "\t\t{" + NL + "\t\t\t";
  protected final String TEXT_547 = " old";
  protected final String TEXT_548 = " = (";
  protected final String TEXT_549 = ")";
  protected final String TEXT_550 = ";" + NL + "\t\t\t";
  protected final String TEXT_551 = " = ";
  protected final String TEXT_552 = "eResolveProxy(old";
  protected final String TEXT_553 = ");" + NL + "\t\t\tif (";
  protected final String TEXT_554 = " != old";
  protected final String TEXT_555 = ")" + NL + "\t\t\t{";
  protected final String TEXT_556 = NL + "\t\t\t\t";
  protected final String TEXT_557 = " new";
  protected final String TEXT_558 = " = (";
  protected final String TEXT_559 = ")";
  protected final String TEXT_560 = ";";
  protected final String TEXT_561 = NL + "\t\t\t\t";
  protected final String TEXT_562 = " msgs = old";
  protected final String TEXT_563 = ".eInverseRemove(this, EOPPOSITE_FEATURE_BASE - ";
  protected final String TEXT_564 = ", null, null);";
  protected final String TEXT_565 = NL + "\t\t\t\t";
  protected final String TEXT_566 = " msgs =  old";
  protected final String TEXT_567 = ".eInverseRemove(this, ";
  protected final String TEXT_568 = ", ";
  protected final String TEXT_569 = ".class, null);";
  protected final String TEXT_570 = NL + "\t\t\t\tif (new";
  protected final String TEXT_571 = ".eInternalContainer() == null)" + NL + "\t\t\t\t{";
  protected final String TEXT_572 = NL + "\t\t\t\t\tmsgs = new";
  protected final String TEXT_573 = ".eInverseAdd(this, EOPPOSITE_FEATURE_BASE - ";
  protected final String TEXT_574 = ", null, msgs);";
  protected final String TEXT_575 = NL + "\t\t\t\t\tmsgs =  new";
  protected final String TEXT_576 = ".eInverseAdd(this, ";
  protected final String TEXT_577 = ", ";
  protected final String TEXT_578 = ".class, msgs);";
  protected final String TEXT_579 = NL + "\t\t\t\t}" + NL + "\t\t\t\tif (msgs != null) msgs.dispatch();";
  protected final String TEXT_580 = NL + "\t\t\t\teVirtualSet(";
  protected final String TEXT_581 = ", ";
  protected final String TEXT_582 = ");";
  protected final String TEXT_583 = NL + "\t\t\t\tif (eNotificationRequired())" + NL + "\t\t\t\t\teNotify(new ";
  protected final String TEXT_584 = "(this, ";
  protected final String TEXT_585 = ".RESOLVE, ";
  protected final String TEXT_586 = ", old";
  protected final String TEXT_587 = ", ";
  protected final String TEXT_588 = "));";
  protected final String TEXT_589 = NL + "\t\t\t}" + NL + "\t\t}";
  protected final String TEXT_590 = NL + "\t\treturn (";
  protected final String TEXT_591 = ")eVirtualGet(";
  protected final String TEXT_592 = ", ";
  protected final String TEXT_593 = ");";
  protected final String TEXT_594 = NL + "\t\treturn (";
  protected final String TEXT_595 = " & ";
  protected final String TEXT_596 = "_EFLAG) != 0;";
  protected final String TEXT_597 = NL + "\t\treturn ";
  protected final String TEXT_598 = "_EFLAG_VALUES[(";
  protected final String TEXT_599 = " & ";
  protected final String TEXT_600 = "_EFLAG) >>> ";
  protected final String TEXT_601 = "_EFLAG_OFFSET];";
  protected final String TEXT_602 = NL + "\t\treturn ";
  protected final String TEXT_603 = ";";
  protected final String TEXT_604 = NL + "\t\t";
  protected final String TEXT_605 = " ";
  protected final String TEXT_606 = " = basicGet";
  protected final String TEXT_607 = "();" + NL + "\t\treturn ";
  protected final String TEXT_608 = " != null && ";
  protected final String TEXT_609 = ".eIsProxy() ? ";
  protected final String TEXT_610 = "eResolveProxy((";
  protected final String TEXT_611 = ")";
  protected final String TEXT_612 = ") : ";
  protected final String TEXT_613 = ";";
  protected final String TEXT_614 = NL + "\t\treturn new ";
  protected final String TEXT_615 = "((";
  protected final String TEXT_616 = ".Internal)((";
  protected final String TEXT_617 = ".Internal.Wrapper)get";
  protected final String TEXT_618 = "()).featureMap().";
  protected final String TEXT_619 = "list(";
  protected final String TEXT_620 = "));";
  protected final String TEXT_621 = NL + "\t\treturn (";
  protected final String TEXT_622 = ")get";
  protected final String TEXT_623 = "().";
  protected final String TEXT_624 = "list(";
  protected final String TEXT_625 = ");";
  protected final String TEXT_626 = NL + "\t\treturn ((";
  protected final String TEXT_627 = ".Internal.Wrapper)get";
  protected final String TEXT_628 = "()).featureMap().list(";
  protected final String TEXT_629 = ");";
  protected final String TEXT_630 = NL + "\t\treturn get";
  protected final String TEXT_631 = "().list(";
  protected final String TEXT_632 = ");";
  protected final String TEXT_633 = NL + "\t\treturn ";
  protected final String TEXT_634 = "(";
  protected final String TEXT_635 = "(";
  protected final String TEXT_636 = ")";
  protected final String TEXT_637 = "((";
  protected final String TEXT_638 = ".Internal.Wrapper)get";
  protected final String TEXT_639 = "()).featureMap().get(";
  protected final String TEXT_640 = ", true)";
  protected final String TEXT_641 = ").";
  protected final String TEXT_642 = "()";
  protected final String TEXT_643 = ";";
  protected final String TEXT_644 = NL + "\t\treturn ";
  protected final String TEXT_645 = "(";
  protected final String TEXT_646 = "(";
  protected final String TEXT_647 = ")";
  protected final String TEXT_648 = "get";
  protected final String TEXT_649 = "().get(";
  protected final String TEXT_650 = ", true)";
  protected final String TEXT_651 = ").";
  protected final String TEXT_652 = "()";
  protected final String TEXT_653 = ";";
  protected final String TEXT_654 = NL + "\t\t";
  protected final String TEXT_655 = NL + "\t\t";
  protected final String TEXT_656 = NL + "\t\t// TODO: implement this method to return the '";
  protected final String TEXT_657 = "' ";
  protected final String TEXT_658 = NL + "\t\t// Ensure that you remove @generated or mark it @generated NOT";
  protected final String TEXT_659 = NL + "\t\t// The list is expected to implement org.eclipse.emf.ecore.util.InternalEList and org.eclipse.emf.ecore.EStructuralFeature.Setting" + NL + "\t\t// so it's likely that an appropriate subclass of org.eclipse.emf.ecore.util.";
  protected final String TEXT_660 = "EcoreEMap";
  protected final String TEXT_661 = "BasicFeatureMap";
  protected final String TEXT_662 = "EcoreEList";
  protected final String TEXT_663 = " should be used.";
  protected final String TEXT_664 = NL + "\t\tthrow new UnsupportedOperationException();";
  protected final String TEXT_665 = NL + "\t}" + NL;
  protected final String TEXT_666 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->";
  protected final String TEXT_667 = NL + "\t * ";
  protected final String TEXT_668 = NL + "\t * @generated" + NL + "\t */";
  protected final String TEXT_669 = NL + "\t@Deprecated";
  protected final String TEXT_670 = NL + "\tpublic ";
  protected final String TEXT_671 = " basicGet";
  protected final String TEXT_672 = "()" + NL + "\t{";
  protected final String TEXT_673 = NL + "\t\treturn (";
  protected final String TEXT_674 = ")eDynamicGet(";
  protected final String TEXT_675 = " - ESTATIC_FEATURE_COUNT";
  protected final String TEXT_676 = ", ";
  protected final String TEXT_677 = ", false, ";
  protected final String TEXT_678 = ");";
  protected final String TEXT_679 = NL + "\t\treturn ";
  protected final String TEXT_680 = "(";
  protected final String TEXT_681 = "(";
  protected final String TEXT_682 = ")";
  protected final String TEXT_683 = "__ESETTING_DELEGATE.dynamicGet(this, null, 0, false, false)";
  protected final String TEXT_684 = ").";
  protected final String TEXT_685 = "()";
  protected final String TEXT_686 = ";";
  protected final String TEXT_687 = NL + "\t\tif (eContainerFeatureID() != ";
  protected final String TEXT_688 = ") return null;" + NL + "\t\treturn (";
  protected final String TEXT_689 = ")eInternalContainer();";
  protected final String TEXT_690 = NL + "\t\treturn (";
  protected final String TEXT_691 = ")eVirtualGet(";
  protected final String TEXT_692 = ");";
  protected final String TEXT_693 = NL + "\t\treturn ";
  protected final String TEXT_694 = ";";
  protected final String TEXT_695 = NL + "\t\treturn (";
  protected final String TEXT_696 = ")((";
  protected final String TEXT_697 = ".Internal.Wrapper)get";
  protected final String TEXT_698 = "()).featureMap().get(";
  protected final String TEXT_699 = ", false);";
  protected final String TEXT_700 = NL + "\t\treturn (";
  protected final String TEXT_701 = ")get";
  protected final String TEXT_702 = "().get(";
  protected final String TEXT_703 = ", false);";
  protected final String TEXT_704 = NL + "\t\t";
  protected final String TEXT_705 = NL + "\t\t// TODO: implement this method to return the '";
  protected final String TEXT_706 = "' ";
  protected final String TEXT_707 = NL + "\t\t// -> do not perform proxy resolution" + NL + "\t\t// Ensure that you remove @generated or mark it @generated NOT" + NL + "\t\tthrow new UnsupportedOperationException();";
  protected final String TEXT_708 = NL + "\t}" + NL;
  protected final String TEXT_709 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->";
  protected final String TEXT_710 = NL + "\t * ";
  protected final String TEXT_711 = NL + "\t * @generated" + NL + "\t */";
  protected final String TEXT_712 = NL + "\t@Deprecated";
  protected final String TEXT_713 = NL + "\tpublic ";
  protected final String TEXT_714 = " basicSet";
  protected final String TEXT_715 = "(";
  protected final String TEXT_716 = " new";
  protected final String TEXT_717 = ", ";
  protected final String TEXT_718 = " msgs)" + NL + "\t{";
  protected final String TEXT_719 = NL + "\t\tmsgs = eBasicSetContainer((";
  protected final String TEXT_720 = ")new";
  protected final String TEXT_721 = ", ";
  protected final String TEXT_722 = ", msgs);";
  protected final String TEXT_723 = NL + "\t\treturn msgs;";
  protected final String TEXT_724 = NL + "\t\tmsgs = eDynamicInverseAdd((";
  protected final String TEXT_725 = ")new";
  protected final String TEXT_726 = ", ";
  protected final String TEXT_727 = ", msgs);";
  protected final String TEXT_728 = NL + "\t\treturn msgs;";
  protected final String TEXT_729 = NL + "\t\tObject old";
  protected final String TEXT_730 = " = eVirtualSet(";
  protected final String TEXT_731 = ", new";
  protected final String TEXT_732 = ");";
  protected final String TEXT_733 = NL + "\t\t";
  protected final String TEXT_734 = " old";
  protected final String TEXT_735 = " = ";
  protected final String TEXT_736 = ";" + NL + "\t\t";
  protected final String TEXT_737 = " = new";
  protected final String TEXT_738 = ";";
  protected final String TEXT_739 = NL + "\t\tboolean isSetChange = old";
  protected final String TEXT_740 = " == EVIRTUAL_NO_VALUE;";
  protected final String TEXT_741 = NL + "\t\tboolean old";
  protected final String TEXT_742 = "ESet = (";
  protected final String TEXT_743 = " & ";
  protected final String TEXT_744 = "_ESETFLAG) != 0;";
  protected final String TEXT_745 = NL + "\t\t";
  protected final String TEXT_746 = " |= ";
  protected final String TEXT_747 = "_ESETFLAG;";
  protected final String TEXT_748 = NL + "\t\tboolean old";
  protected final String TEXT_749 = "ESet = ";
  protected final String TEXT_750 = "ESet;";
  protected final String TEXT_751 = NL + "\t\t";
  protected final String TEXT_752 = "ESet = true;";
  protected final String TEXT_753 = NL + "\t\tif (eNotificationRequired())" + NL + "\t\t{";
  protected final String TEXT_754 = NL + "\t\t\t";
  protected final String TEXT_755 = " notification = new ";
  protected final String TEXT_756 = "(this, ";
  protected final String TEXT_757 = ".SET, ";
  protected final String TEXT_758 = ", ";
  protected final String TEXT_759 = "isSetChange ? null : old";
  protected final String TEXT_760 = "old";
  protected final String TEXT_761 = ", new";
  protected final String TEXT_762 = ", ";
  protected final String TEXT_763 = "isSetChange";
  protected final String TEXT_764 = "!old";
  protected final String TEXT_765 = "ESet";
  protected final String TEXT_766 = ");";
  protected final String TEXT_767 = NL + "\t\t\t";
  protected final String TEXT_768 = " notification = new ";
  protected final String TEXT_769 = "(this, ";
  protected final String TEXT_770 = ".SET, ";
  protected final String TEXT_771 = ", ";
  protected final String TEXT_772 = "old";
  protected final String TEXT_773 = " == EVIRTUAL_NO_VALUE ? null : old";
  protected final String TEXT_774 = "old";
  protected final String TEXT_775 = ", new";
  protected final String TEXT_776 = ");";
  protected final String TEXT_777 = NL + "\t\t\tif (msgs == null) msgs = notification; else msgs.add(notification);" + NL + "\t\t}";
  protected final String TEXT_778 = NL + "\t\treturn msgs;";
  protected final String TEXT_779 = NL + "\t\treturn ((";
  protected final String TEXT_780 = ".Internal)((";
  protected final String TEXT_781 = ".Internal.Wrapper)get";
  protected final String TEXT_782 = "()).featureMap()).basicAdd(";
  protected final String TEXT_783 = ", new";
  protected final String TEXT_784 = ", msgs);";
  protected final String TEXT_785 = NL + "\t\treturn ((";
  protected final String TEXT_786 = ".Internal)get";
  protected final String TEXT_787 = "()).basicAdd(";
  protected final String TEXT_788 = ", new";
  protected final String TEXT_789 = ", msgs);";
  protected final String TEXT_790 = NL + "\t\t// TODO: implement this method to set the contained '";
  protected final String TEXT_791 = "' ";
  protected final String TEXT_792 = NL + "\t\t// -> this method is automatically invoked to keep the containment relationship in synch" + NL + "\t\t// -> do not modify other features" + NL + "\t\t// -> return msgs, after adding any generated Notification to it (if it is null, a NotificationChain object must be created first)" + NL + "\t\t// Ensure that you remove @generated or mark it @generated NOT" + NL + "\t\tthrow new UnsupportedOperationException();";
  protected final String TEXT_793 = NL + "\t}" + NL;
  protected final String TEXT_794 = NL + "\t/**" + NL + "\t * Sets the value of the '{@link ";
  protected final String TEXT_795 = "#";
  protected final String TEXT_796 = " <em>";
  protected final String TEXT_797 = "</em>}' ";
  protected final String TEXT_798 = ".";
  protected final String TEXT_799 = NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @param value the new value of the '<em>";
  protected final String TEXT_800 = "</em>' ";
  protected final String TEXT_801 = ".";
  protected final String TEXT_802 = NL + "\t * @see ";
  protected final String TEXT_803 = NL + "\t * @see #isSet";
  protected final String TEXT_804 = "()";
  protected final String TEXT_805 = NL + "\t * @see #unset";
  protected final String TEXT_806 = "()";
  protected final String TEXT_807 = NL + "\t * @see #";
  protected final String TEXT_808 = "()";
  protected final String TEXT_809 = NL + "\t * ";
  protected final String TEXT_810 = NL + "\t * @generated" + NL + "\t */";
  protected final String TEXT_811 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->";
  protected final String TEXT_812 = NL + "\t * ";
  protected final String TEXT_813 = NL + "\t * @generated" + NL + "\t */";
  protected final String TEXT_814 = NL + "\t@Deprecated";
  protected final String TEXT_815 = NL + "\tvoid set";
  protected final String TEXT_816 = "(";
  protected final String TEXT_817 = " value);" + NL;
  protected final String TEXT_818 = NL + "\tpublic void set";
  protected final String TEXT_819 = "_";
  protected final String TEXT_820 = "(";
  protected final String TEXT_821 = " ";
  protected final String TEXT_822 = ")" + NL + "\t{";
  protected final String TEXT_823 = NL + "\t\teDynamicSet(";
  protected final String TEXT_824 = " - ESTATIC_FEATURE_COUNT";
  protected final String TEXT_825 = ", ";
  protected final String TEXT_826 = ", ";
  protected final String TEXT_827 = "new ";
  protected final String TEXT_828 = "(";
  protected final String TEXT_829 = "new";
  protected final String TEXT_830 = ")";
  protected final String TEXT_831 = ");";
  protected final String TEXT_832 = NL + "\t\teSet(";
  protected final String TEXT_833 = ", ";
  protected final String TEXT_834 = "new ";
  protected final String TEXT_835 = "(";
  protected final String TEXT_836 = "new";
  protected final String TEXT_837 = ")";
  protected final String TEXT_838 = ");";
  protected final String TEXT_839 = NL + "\t\t";
  protected final String TEXT_840 = "__ESETTING_DELEGATE.dynamicSet(this, null, 0, ";
  protected final String TEXT_841 = "new ";
  protected final String TEXT_842 = "(";
  protected final String TEXT_843 = "new";
  protected final String TEXT_844 = ")";
  protected final String TEXT_845 = ");";
  protected final String TEXT_846 = NL + "\t\tif (new";
  protected final String TEXT_847 = " != eInternalContainer() || (eContainerFeatureID() != ";
  protected final String TEXT_848 = " && new";
  protected final String TEXT_849 = " != null))" + NL + "\t\t{" + NL + "\t\t\tif (";
  protected final String TEXT_850 = ".isAncestor(this, ";
  protected final String TEXT_851 = "new";
  protected final String TEXT_852 = "))" + NL + "\t\t\t\tthrow new ";
  protected final String TEXT_853 = "(\"Recursive containment not allowed for \" + toString());";
  protected final String TEXT_854 = NL + "\t\t\t";
  protected final String TEXT_855 = " msgs = null;" + NL + "\t\t\tif (eInternalContainer() != null)" + NL + "\t\t\t\tmsgs = eBasicRemoveFromContainer(msgs);" + NL + "\t\t\tif (new";
  protected final String TEXT_856 = " != null)" + NL + "\t\t\t\tmsgs = ((";
  protected final String TEXT_857 = ")new";
  protected final String TEXT_858 = ").eInverseAdd(this, ";
  protected final String TEXT_859 = ", ";
  protected final String TEXT_860 = ".class, msgs);" + NL + "\t\t\tmsgs = basicSet";
  protected final String TEXT_861 = "(";
  protected final String TEXT_862 = "new";
  protected final String TEXT_863 = ", msgs);" + NL + "\t\t\tif (msgs != null) msgs.dispatch();" + NL + "\t\t}";
  protected final String TEXT_864 = NL + "\t\telse if (eNotificationRequired())" + NL + "\t\t\teNotify(new ";
  protected final String TEXT_865 = "(this, ";
  protected final String TEXT_866 = ".SET, ";
  protected final String TEXT_867 = ", new";
  protected final String TEXT_868 = ", new";
  protected final String TEXT_869 = "));";
  protected final String TEXT_870 = NL + "\t\t";
  protected final String TEXT_871 = " ";
  protected final String TEXT_872 = " = (";
  protected final String TEXT_873 = ")eVirtualGet(";
  protected final String TEXT_874 = ");";
  protected final String TEXT_875 = NL + "\t\tif (new";
  protected final String TEXT_876 = " != ";
  protected final String TEXT_877 = ")" + NL + "\t\t{" + NL + "\t\t\t";
  protected final String TEXT_878 = " msgs = null;" + NL + "\t\t\tif (";
  protected final String TEXT_879 = " != null)";
  protected final String TEXT_880 = NL + "\t\t\t\tmsgs = ((";
  protected final String TEXT_881 = ")";
  protected final String TEXT_882 = ").eInverseRemove(this, EOPPOSITE_FEATURE_BASE - ";
  protected final String TEXT_883 = ", null, msgs);" + NL + "\t\t\tif (new";
  protected final String TEXT_884 = " != null)" + NL + "\t\t\t\tmsgs = ((";
  protected final String TEXT_885 = ")new";
  protected final String TEXT_886 = ").eInverseAdd(this, EOPPOSITE_FEATURE_BASE - ";
  protected final String TEXT_887 = ", null, msgs);";
  protected final String TEXT_888 = NL + "\t\t\t\tmsgs = ((";
  protected final String TEXT_889 = ")";
  protected final String TEXT_890 = ").eInverseRemove(this, ";
  protected final String TEXT_891 = ", ";
  protected final String TEXT_892 = ".class, msgs);" + NL + "\t\t\tif (new";
  protected final String TEXT_893 = " != null)" + NL + "\t\t\t\tmsgs = ((";
  protected final String TEXT_894 = ")new";
  protected final String TEXT_895 = ").eInverseAdd(this, ";
  protected final String TEXT_896 = ", ";
  protected final String TEXT_897 = ".class, msgs);";
  protected final String TEXT_898 = NL + "\t\t\tmsgs = basicSet";
  protected final String TEXT_899 = "(";
  protected final String TEXT_900 = "new";
  protected final String TEXT_901 = ", msgs);" + NL + "\t\t\tif (msgs != null) msgs.dispatch();" + NL + "\t\t}";
  protected final String TEXT_902 = NL + "\t\telse" + NL + "\t\t{";
  protected final String TEXT_903 = NL + "\t\t\tboolean old";
  protected final String TEXT_904 = "ESet = eVirtualIsSet(";
  protected final String TEXT_905 = ");";
  protected final String TEXT_906 = NL + "\t\t\tboolean old";
  protected final String TEXT_907 = "ESet = (";
  protected final String TEXT_908 = " & ";
  protected final String TEXT_909 = "_ESETFLAG) != 0;";
  protected final String TEXT_910 = NL + "\t\t\t";
  protected final String TEXT_911 = " |= ";
  protected final String TEXT_912 = "_ESETFLAG;";
  protected final String TEXT_913 = NL + "\t\t\tboolean old";
  protected final String TEXT_914 = "ESet = ";
  protected final String TEXT_915 = "ESet;";
  protected final String TEXT_916 = NL + "\t\t\t";
  protected final String TEXT_917 = "ESet = true;";
  protected final String TEXT_918 = NL + "\t\t\tif (eNotificationRequired())" + NL + "\t\t\t\teNotify(new ";
  protected final String TEXT_919 = "(this, ";
  protected final String TEXT_920 = ".SET, ";
  protected final String TEXT_921 = ", new";
  protected final String TEXT_922 = ", new";
  protected final String TEXT_923 = ", !old";
  protected final String TEXT_924 = "ESet));";
  protected final String TEXT_925 = NL + "\t\t}";
  protected final String TEXT_926 = NL + "\t\telse if (eNotificationRequired())" + NL + "\t\t\teNotify(new ";
  protected final String TEXT_927 = "(this, ";
  protected final String TEXT_928 = ".SET, ";
  protected final String TEXT_929 = ", new";
  protected final String TEXT_930 = ", new";
  protected final String TEXT_931 = "));";
  protected final String TEXT_932 = NL + "\t\t";
  protected final String TEXT_933 = " old";
  protected final String TEXT_934 = " = (";
  protected final String TEXT_935 = " & ";
  protected final String TEXT_936 = "_EFLAG) != 0;";
  protected final String TEXT_937 = NL + "\t\t";
  protected final String TEXT_938 = " old";
  protected final String TEXT_939 = " = ";
  protected final String TEXT_940 = "_EFLAG_VALUES[(";
  protected final String TEXT_941 = " & ";
  protected final String TEXT_942 = "_EFLAG) >>> ";
  protected final String TEXT_943 = "_EFLAG_OFFSET];";
  protected final String TEXT_944 = NL + "\t\tif (new";
  protected final String TEXT_945 = ") ";
  protected final String TEXT_946 = " |= ";
  protected final String TEXT_947 = "_EFLAG; else ";
  protected final String TEXT_948 = " &= ~";
  protected final String TEXT_949 = "_EFLAG;";
  protected final String TEXT_950 = NL + "\t\tif (new";
  protected final String TEXT_951 = " == null) new";
  protected final String TEXT_952 = " = ";
  protected final String TEXT_953 = "_EDEFAULT;" + NL + "\t\t";
  protected final String TEXT_954 = " = ";
  protected final String TEXT_955 = " & ~";
  protected final String TEXT_956 = "_EFLAG | ";
  protected final String TEXT_957 = "new";
  protected final String TEXT_958 = ".ordinal()";
  protected final String TEXT_959 = ".VALUES.indexOf(new";
  protected final String TEXT_960 = ")";
  protected final String TEXT_961 = " << ";
  protected final String TEXT_962 = "_EFLAG_OFFSET;";
  protected final String TEXT_963 = NL + "\t\t";
  protected final String TEXT_964 = " old";
  protected final String TEXT_965 = " = ";
  protected final String TEXT_966 = ";";
  protected final String TEXT_967 = NL + "\t\t";
  protected final String TEXT_968 = " ";
  protected final String TEXT_969 = " = new";
  protected final String TEXT_970 = " == null ? ";
  protected final String TEXT_971 = " : new";
  protected final String TEXT_972 = ";";
  protected final String TEXT_973 = NL + "\t\t";
  protected final String TEXT_974 = " = new";
  protected final String TEXT_975 = " == null ? ";
  protected final String TEXT_976 = " : new";
  protected final String TEXT_977 = ";";
  protected final String TEXT_978 = NL + "\t\t";
  protected final String TEXT_979 = " ";
  protected final String TEXT_980 = " = ";
  protected final String TEXT_981 = "new";
  protected final String TEXT_982 = ";";
  protected final String TEXT_983 = NL + "\t\t";
  protected final String TEXT_984 = " = ";
  protected final String TEXT_985 = "new";
  protected final String TEXT_986 = ";";
  protected final String TEXT_987 = NL + "\t\tObject old";
  protected final String TEXT_988 = " = eVirtualSet(";
  protected final String TEXT_989 = ", ";
  protected final String TEXT_990 = ");";
  protected final String TEXT_991 = NL + "\t\tboolean isSetChange = old";
  protected final String TEXT_992 = " == EVIRTUAL_NO_VALUE;";
  protected final String TEXT_993 = NL + "\t\tboolean old";
  protected final String TEXT_994 = "ESet = (";
  protected final String TEXT_995 = " & ";
  protected final String TEXT_996 = "_ESETFLAG) != 0;";
  protected final String TEXT_997 = NL + "\t\t";
  protected final String TEXT_998 = " |= ";
  protected final String TEXT_999 = "_ESETFLAG;";
  protected final String TEXT_1000 = NL + "\t\tboolean old";
  protected final String TEXT_1001 = "ESet = ";
  protected final String TEXT_1002 = "ESet;";
  protected final String TEXT_1003 = NL + "\t\t";
  protected final String TEXT_1004 = "ESet = true;";
  protected final String TEXT_1005 = NL + "\t\tif (eNotificationRequired())" + NL + "\t\t\teNotify(new ";
  protected final String TEXT_1006 = "(this, ";
  protected final String TEXT_1007 = ".SET, ";
  protected final String TEXT_1008 = ", ";
  protected final String TEXT_1009 = "isSetChange ? ";
  protected final String TEXT_1010 = " : old";
  protected final String TEXT_1011 = "old";
  protected final String TEXT_1012 = ", ";
  protected final String TEXT_1013 = "new";
  protected final String TEXT_1014 = ", ";
  protected final String TEXT_1015 = "isSetChange";
  protected final String TEXT_1016 = "!old";
  protected final String TEXT_1017 = "ESet";
  protected final String TEXT_1018 = "));";
  protected final String TEXT_1019 = NL + "\t\tif (eNotificationRequired())" + NL + "\t\t\teNotify(new ";
  protected final String TEXT_1020 = "(this, ";
  protected final String TEXT_1021 = ".SET, ";
  protected final String TEXT_1022 = ", ";
  protected final String TEXT_1023 = "old";
  protected final String TEXT_1024 = " == EVIRTUAL_NO_VALUE ? ";
  protected final String TEXT_1025 = " : old";
  protected final String TEXT_1026 = "old";
  protected final String TEXT_1027 = ", ";
  protected final String TEXT_1028 = "new";
  protected final String TEXT_1029 = "));";
  protected final String TEXT_1030 = NL + "\t\t((";
  protected final String TEXT_1031 = ".Internal)((";
  protected final String TEXT_1032 = ".Internal.Wrapper)get";
  protected final String TEXT_1033 = "()).featureMap()).set(";
  protected final String TEXT_1034 = ", ";
  protected final String TEXT_1035 = "new ";
  protected final String TEXT_1036 = "(";
  protected final String TEXT_1037 = "new";
  protected final String TEXT_1038 = ")";
  protected final String TEXT_1039 = ");";
  protected final String TEXT_1040 = NL + "\t\t((";
  protected final String TEXT_1041 = ".Internal)get";
  protected final String TEXT_1042 = "()).set(";
  protected final String TEXT_1043 = ", ";
  protected final String TEXT_1044 = "new ";
  protected final String TEXT_1045 = "(";
  protected final String TEXT_1046 = "new";
  protected final String TEXT_1047 = ")";
  protected final String TEXT_1048 = ");";
  protected final String TEXT_1049 = NL + "\t\t";
  protected final String TEXT_1050 = NL + "\t\t// TODO: implement this method to set the '";
  protected final String TEXT_1051 = "' ";
  protected final String TEXT_1052 = NL + "\t\t// Ensure that you remove @generated or mark it @generated NOT" + NL + "\t\tthrow new UnsupportedOperationException();";
  protected final String TEXT_1053 = NL + "\t}" + NL;
  protected final String TEXT_1054 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->";
  protected final String TEXT_1055 = NL + "\t * ";
  protected final String TEXT_1056 = NL + "\t * @generated" + NL + "\t */";
  protected final String TEXT_1057 = NL + "\t@Deprecated";
  protected final String TEXT_1058 = NL + "\tpublic ";
  protected final String TEXT_1059 = " basicUnset";
  protected final String TEXT_1060 = "(";
  protected final String TEXT_1061 = " msgs)" + NL + "\t{";
  protected final String TEXT_1062 = NL + "\t\treturn eDynamicInverseRemove((";
  protected final String TEXT_1063 = ")";
  protected final String TEXT_1064 = "basicGet";
  protected final String TEXT_1065 = "(), ";
  protected final String TEXT_1066 = ", msgs);";
  protected final String TEXT_1067 = "Object old";
  protected final String TEXT_1068 = " = ";
  protected final String TEXT_1069 = "eVirtualUnset(";
  protected final String TEXT_1070 = ");";
  protected final String TEXT_1071 = NL + "\t\t";
  protected final String TEXT_1072 = " old";
  protected final String TEXT_1073 = " = ";
  protected final String TEXT_1074 = ";";
  protected final String TEXT_1075 = NL + "\t\t";
  protected final String TEXT_1076 = " = null;";
  protected final String TEXT_1077 = NL + "\t\tboolean isSetChange = old";
  protected final String TEXT_1078 = " != EVIRTUAL_NO_VALUE;";
  protected final String TEXT_1079 = NL + "\t\tboolean old";
  protected final String TEXT_1080 = "ESet = (";
  protected final String TEXT_1081 = " & ";
  protected final String TEXT_1082 = "_ESETFLAG) != 0;";
  protected final String TEXT_1083 = NL + "\t\t";
  protected final String TEXT_1084 = " &= ~";
  protected final String TEXT_1085 = "_ESETFLAG;";
  protected final String TEXT_1086 = NL + "\t\tboolean old";
  protected final String TEXT_1087 = "ESet = ";
  protected final String TEXT_1088 = "ESet;";
  protected final String TEXT_1089 = NL + "\t\t";
  protected final String TEXT_1090 = "ESet = false;";
  protected final String TEXT_1091 = NL + "\t\tif (eNotificationRequired())" + NL + "\t\t{" + NL + "\t\t\t";
  protected final String TEXT_1092 = " notification = new ";
  protected final String TEXT_1093 = "(this, ";
  protected final String TEXT_1094 = ".UNSET, ";
  protected final String TEXT_1095 = ", ";
  protected final String TEXT_1096 = "isSetChange ? old";
  protected final String TEXT_1097 = " : null";
  protected final String TEXT_1098 = "old";
  protected final String TEXT_1099 = ", null, ";
  protected final String TEXT_1100 = "isSetChange";
  protected final String TEXT_1101 = "old";
  protected final String TEXT_1102 = "ESet";
  protected final String TEXT_1103 = ");" + NL + "\t\t\tif (msgs == null) msgs = notification; else msgs.add(notification);" + NL + "\t\t}" + NL + "\t\treturn msgs;";
  protected final String TEXT_1104 = NL + "\t\t// TODO: implement this method to unset the contained '";
  protected final String TEXT_1105 = "' ";
  protected final String TEXT_1106 = NL + "\t\t// -> this method is automatically invoked to keep the containment relationship in synch" + NL + "\t\t// -> do not modify other features" + NL + "\t\t// -> return msgs, after adding any generated Notification to it (if it is null, a NotificationChain object must be created first)" + NL + "\t\t// Ensure that you remove @generated or mark it @generated NOT" + NL + "\t\tthrow new UnsupportedOperationException();";
  protected final String TEXT_1107 = NL + "\t}" + NL;
  protected final String TEXT_1108 = NL + "\t/**" + NL + "\t * Unsets the value of the '{@link ";
  protected final String TEXT_1109 = "#";
  protected final String TEXT_1110 = " <em>";
  protected final String TEXT_1111 = "</em>}' ";
  protected final String TEXT_1112 = ".";
  protected final String TEXT_1113 = NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->";
  protected final String TEXT_1114 = NL + "\t * @see #isSet";
  protected final String TEXT_1115 = "()";
  protected final String TEXT_1116 = NL + "\t * @see #";
  protected final String TEXT_1117 = "()";
  protected final String TEXT_1118 = NL + "\t * @see #set";
  protected final String TEXT_1119 = "(";
  protected final String TEXT_1120 = ")";
  protected final String TEXT_1121 = NL + "\t * ";
  protected final String TEXT_1122 = NL + "\t * @generated" + NL + "\t */";
  protected final String TEXT_1123 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->";
  protected final String TEXT_1124 = NL + "\t * ";
  protected final String TEXT_1125 = NL + "\t * @generated" + NL + "\t */";
  protected final String TEXT_1126 = NL + "\t@Deprecated";
  protected final String TEXT_1127 = NL + "\tvoid unset";
  protected final String TEXT_1128 = "();" + NL;
  protected final String TEXT_1129 = NL + "\tpublic void unset";
  protected final String TEXT_1130 = "_";
  protected final String TEXT_1131 = "()" + NL + "\t{";
  protected final String TEXT_1132 = NL + "\t\teDynamicUnset(";
  protected final String TEXT_1133 = " - ESTATIC_FEATURE_COUNT";
  protected final String TEXT_1134 = ", ";
  protected final String TEXT_1135 = ");";
  protected final String TEXT_1136 = NL + "\t\teUnset(";
  protected final String TEXT_1137 = ");";
  protected final String TEXT_1138 = NL + "\t\t";
  protected final String TEXT_1139 = "__ESETTING_DELEGATE.dynamicUnset(this, null, 0);";
  protected final String TEXT_1140 = NL + "\t\t";
  protected final String TEXT_1141 = " ";
  protected final String TEXT_1142 = " = (";
  protected final String TEXT_1143 = ")eVirtualGet(";
  protected final String TEXT_1144 = ");";
  protected final String TEXT_1145 = NL + "\t\tif (";
  protected final String TEXT_1146 = " != null) ((";
  protected final String TEXT_1147 = ".Unsettable";
  protected final String TEXT_1148 = ")";
  protected final String TEXT_1149 = ").unset();";
  protected final String TEXT_1150 = NL + "\t\t";
  protected final String TEXT_1151 = " ";
  protected final String TEXT_1152 = " = (";
  protected final String TEXT_1153 = ")eVirtualGet(";
  protected final String TEXT_1154 = ");";
  protected final String TEXT_1155 = NL + "\t\tif (";
  protected final String TEXT_1156 = " != null)" + NL + "\t\t{" + NL + "\t\t\t";
  protected final String TEXT_1157 = " msgs = null;";
  protected final String TEXT_1158 = NL + "\t\t\tmsgs = ((";
  protected final String TEXT_1159 = ")";
  protected final String TEXT_1160 = ").eInverseRemove(this, EOPPOSITE_FEATURE_BASE - ";
  protected final String TEXT_1161 = ", null, msgs);";
  protected final String TEXT_1162 = NL + "\t\t\tmsgs = ((";
  protected final String TEXT_1163 = ")";
  protected final String TEXT_1164 = ").eInverseRemove(this, ";
  protected final String TEXT_1165 = ", ";
  protected final String TEXT_1166 = ".class, msgs);";
  protected final String TEXT_1167 = NL + "\t\t\tmsgs = basicUnset";
  protected final String TEXT_1168 = "(msgs);" + NL + "\t\t\tif (msgs != null) msgs.dispatch();" + NL + "\t\t}" + NL + "\t\telse" + NL + "\t\t{";
  protected final String TEXT_1169 = NL + "\t\t\tboolean old";
  protected final String TEXT_1170 = "ESet = eVirtualIsSet(";
  protected final String TEXT_1171 = ");";
  protected final String TEXT_1172 = NL + "\t\t\tboolean old";
  protected final String TEXT_1173 = "ESet = (";
  protected final String TEXT_1174 = " & ";
  protected final String TEXT_1175 = "_ESETFLAG) != 0;";
  protected final String TEXT_1176 = NL + "\t\t\t";
  protected final String TEXT_1177 = " &= ~";
  protected final String TEXT_1178 = "_ESETFLAG;";
  protected final String TEXT_1179 = NL + "\t\t\tboolean old";
  protected final String TEXT_1180 = "ESet = ";
  protected final String TEXT_1181 = "ESet;";
  protected final String TEXT_1182 = NL + "\t\t\t";
  protected final String TEXT_1183 = "ESet = false;";
  protected final String TEXT_1184 = NL + "\t\t\tif (eNotificationRequired())" + NL + "\t\t\t\teNotify(new ";
  protected final String TEXT_1185 = "(this, ";
  protected final String TEXT_1186 = ".UNSET, ";
  protected final String TEXT_1187 = ", null, null, old";
  protected final String TEXT_1188 = "ESet));";
  protected final String TEXT_1189 = NL + "\t\t}";
  protected final String TEXT_1190 = NL + "\t\t";
  protected final String TEXT_1191 = " old";
  protected final String TEXT_1192 = " = (";
  protected final String TEXT_1193 = " & ";
  protected final String TEXT_1194 = "_EFLAG) != 0;";
  protected final String TEXT_1195 = NL + "\t\t";
  protected final String TEXT_1196 = " old";
  protected final String TEXT_1197 = " = ";
  protected final String TEXT_1198 = "_EFLAG_VALUES[(";
  protected final String TEXT_1199 = " & ";
  protected final String TEXT_1200 = "_EFLAG) >>> ";
  protected final String TEXT_1201 = "_EFLAG_OFFSET];";
  protected final String TEXT_1202 = NL + "\t\tObject old";
  protected final String TEXT_1203 = " = eVirtualUnset(";
  protected final String TEXT_1204 = ");";
  protected final String TEXT_1205 = NL + "\t\t";
  protected final String TEXT_1206 = " old";
  protected final String TEXT_1207 = " = ";
  protected final String TEXT_1208 = ";";
  protected final String TEXT_1209 = NL + "\t\tboolean isSetChange = old";
  protected final String TEXT_1210 = " != EVIRTUAL_NO_VALUE;";
  protected final String TEXT_1211 = NL + "\t\tboolean old";
  protected final String TEXT_1212 = "ESet = (";
  protected final String TEXT_1213 = " & ";
  protected final String TEXT_1214 = "_ESETFLAG) != 0;";
  protected final String TEXT_1215 = NL + "\t\tboolean old";
  protected final String TEXT_1216 = "ESet = ";
  protected final String TEXT_1217 = "ESet;";
  protected final String TEXT_1218 = NL + "\t\t";
  protected final String TEXT_1219 = " = null;";
  protected final String TEXT_1220 = NL + "\t\t";
  protected final String TEXT_1221 = " &= ~";
  protected final String TEXT_1222 = "_ESETFLAG;";
  protected final String TEXT_1223 = NL + "\t\t";
  protected final String TEXT_1224 = "ESet = false;";
  protected final String TEXT_1225 = NL + "\t\tif (eNotificationRequired())" + NL + "\t\t\teNotify(new ";
  protected final String TEXT_1226 = "(this, ";
  protected final String TEXT_1227 = ".UNSET, ";
  protected final String TEXT_1228 = ", ";
  protected final String TEXT_1229 = "isSetChange ? old";
  protected final String TEXT_1230 = " : null";
  protected final String TEXT_1231 = "old";
  protected final String TEXT_1232 = ", null, ";
  protected final String TEXT_1233 = "isSetChange";
  protected final String TEXT_1234 = "old";
  protected final String TEXT_1235 = "ESet";
  protected final String TEXT_1236 = "));";
  protected final String TEXT_1237 = NL + "\t\tif (";
  protected final String TEXT_1238 = ") ";
  protected final String TEXT_1239 = " |= ";
  protected final String TEXT_1240 = "_EFLAG; else ";
  protected final String TEXT_1241 = " &= ~";
  protected final String TEXT_1242 = "_EFLAG;";
  protected final String TEXT_1243 = NL + "\t\t";
  protected final String TEXT_1244 = " = ";
  protected final String TEXT_1245 = " & ~";
  protected final String TEXT_1246 = "_EFLAG | ";
  protected final String TEXT_1247 = "_EFLAG_DEFAULT;";
  protected final String TEXT_1248 = NL + "\t\t";
  protected final String TEXT_1249 = " = ";
  protected final String TEXT_1250 = ";";
  protected final String TEXT_1251 = NL + "\t\t";
  protected final String TEXT_1252 = " &= ~";
  protected final String TEXT_1253 = "_ESETFLAG;";
  protected final String TEXT_1254 = NL + "\t\t";
  protected final String TEXT_1255 = "ESet = false;";
  protected final String TEXT_1256 = NL + "\t\tif (eNotificationRequired())" + NL + "\t\t\teNotify(new ";
  protected final String TEXT_1257 = "(this, ";
  protected final String TEXT_1258 = ".UNSET, ";
  protected final String TEXT_1259 = ", ";
  protected final String TEXT_1260 = "isSetChange ? old";
  protected final String TEXT_1261 = " : ";
  protected final String TEXT_1262 = "old";
  protected final String TEXT_1263 = ", ";
  protected final String TEXT_1264 = ", ";
  protected final String TEXT_1265 = "isSetChange";
  protected final String TEXT_1266 = "old";
  protected final String TEXT_1267 = "ESet";
  protected final String TEXT_1268 = "));";
  protected final String TEXT_1269 = NL + "\t\t((";
  protected final String TEXT_1270 = ".Internal)((";
  protected final String TEXT_1271 = ".Internal.Wrapper)get";
  protected final String TEXT_1272 = "()).featureMap()).clear(";
  protected final String TEXT_1273 = ");";
  protected final String TEXT_1274 = NL + "\t\t((";
  protected final String TEXT_1275 = ".Internal)get";
  protected final String TEXT_1276 = "()).clear(";
  protected final String TEXT_1277 = ");";
  protected final String TEXT_1278 = NL + "\t\t";
  protected final String TEXT_1279 = NL + "\t\t// TODO: implement this method to unset the '";
  protected final String TEXT_1280 = "' ";
  protected final String TEXT_1281 = NL + "\t\t// Ensure that you remove @generated or mark it @generated NOT" + NL + "\t\tthrow new UnsupportedOperationException();";
  protected final String TEXT_1282 = NL + "\t}" + NL;
  protected final String TEXT_1283 = NL + "\t/**" + NL + "\t * Returns whether the value of the '{@link ";
  protected final String TEXT_1284 = "#";
  protected final String TEXT_1285 = " <em>";
  protected final String TEXT_1286 = "</em>}' ";
  protected final String TEXT_1287 = " is set.";
  protected final String TEXT_1288 = NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @return whether the value of the '<em>";
  protected final String TEXT_1289 = "</em>' ";
  protected final String TEXT_1290 = " is set.";
  protected final String TEXT_1291 = NL + "\t * @see #unset";
  protected final String TEXT_1292 = "()";
  protected final String TEXT_1293 = NL + "\t * @see #";
  protected final String TEXT_1294 = "()";
  protected final String TEXT_1295 = NL + "\t * @see #set";
  protected final String TEXT_1296 = "(";
  protected final String TEXT_1297 = ")";
  protected final String TEXT_1298 = NL + "\t * ";
  protected final String TEXT_1299 = NL + "\t * @generated" + NL + "\t */";
  protected final String TEXT_1300 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->";
  protected final String TEXT_1301 = NL + "\t * ";
  protected final String TEXT_1302 = NL + "\t * @generated" + NL + "\t */";
  protected final String TEXT_1303 = NL + "\t@Deprecated";
  protected final String TEXT_1304 = NL + "\tboolean isSet";
  protected final String TEXT_1305 = "();" + NL;
  protected final String TEXT_1306 = NL + "\tpublic boolean isSet";
  protected final String TEXT_1307 = "_";
  protected final String TEXT_1308 = "()" + NL + "\t{";
  protected final String TEXT_1309 = NL + "\t\treturn eDynamicIsSet(";
  protected final String TEXT_1310 = " - ESTATIC_FEATURE_COUNT";
  protected final String TEXT_1311 = ", ";
  protected final String TEXT_1312 = ");";
  protected final String TEXT_1313 = NL + "\t\treturn eIsSet(";
  protected final String TEXT_1314 = ");";
  protected final String TEXT_1315 = NL + "\t\treturn ";
  protected final String TEXT_1316 = "__ESETTING_DELEGATE.dynamicIsSet(this, null, 0);";
  protected final String TEXT_1317 = NL + "\t\t";
  protected final String TEXT_1318 = " ";
  protected final String TEXT_1319 = " = (";
  protected final String TEXT_1320 = ")eVirtualGet(";
  protected final String TEXT_1321 = ");";
  protected final String TEXT_1322 = NL + "\t\treturn ";
  protected final String TEXT_1323 = " != null && ((";
  protected final String TEXT_1324 = ".Unsettable";
  protected final String TEXT_1325 = ")";
  protected final String TEXT_1326 = ").isSet();";
  protected final String TEXT_1327 = NL + "\t\treturn eVirtualIsSet(";
  protected final String TEXT_1328 = ");";
  protected final String TEXT_1329 = NL + "\t\treturn (";
  protected final String TEXT_1330 = " & ";
  protected final String TEXT_1331 = "_ESETFLAG) != 0;";
  protected final String TEXT_1332 = NL + "\t\treturn ";
  protected final String TEXT_1333 = "ESet;";
  protected final String TEXT_1334 = NL + "\t\treturn !((";
  protected final String TEXT_1335 = ".Internal)((";
  protected final String TEXT_1336 = ".Internal.Wrapper)get";
  protected final String TEXT_1337 = "()).featureMap()).isEmpty(";
  protected final String TEXT_1338 = ");";
  protected final String TEXT_1339 = NL + "\t\treturn !((";
  protected final String TEXT_1340 = ".Internal)get";
  protected final String TEXT_1341 = "()).isEmpty(";
  protected final String TEXT_1342 = ");";
  protected final String TEXT_1343 = NL + "\t\t";
  protected final String TEXT_1344 = NL + "\t\t// TODO: implement this method to return whether the '";
  protected final String TEXT_1345 = "' ";
  protected final String TEXT_1346 = " is set" + NL + "\t\t// Ensure that you remove @generated or mark it @generated NOT" + NL + "\t\tthrow new UnsupportedOperationException();";
  protected final String TEXT_1347 = NL + "\t}" + NL;
  protected final String TEXT_1348 = NL + "\t/**" + NL + "\t * The cached validation expression for the '{@link #";
  protected final String TEXT_1349 = "(";
  protected final String TEXT_1350 = ") <em>";
  protected final String TEXT_1351 = "</em>}' invariant operation." + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @see #";
  protected final String TEXT_1352 = "(";
  protected final String TEXT_1353 = ")";
  protected final String TEXT_1354 = NL + "\t * ";
  protected final String TEXT_1355 = NL + "\t * @generated" + NL + "\t * @ordered" + NL + "\t */";
  protected final String TEXT_1356 = NL + "\t@Deprecated";
  protected final String TEXT_1357 = NL + "\tprotected static final ";
  protected final String TEXT_1358 = " ";
  protected final String TEXT_1359 = "__EEXPRESSION = \"";
  protected final String TEXT_1360 = "\";";
  protected final String TEXT_1361 = NL;
  protected final String TEXT_1362 = NL + "\t/**" + NL + "\t * The cached invocation delegate for the '{@link #";
  protected final String TEXT_1363 = "(";
  protected final String TEXT_1364 = ") <em>";
  protected final String TEXT_1365 = "</em>}' operation." + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @see #";
  protected final String TEXT_1366 = "(";
  protected final String TEXT_1367 = ")";
  protected final String TEXT_1368 = NL + "\t * ";
  protected final String TEXT_1369 = NL + "\t * @generated" + NL + "\t * @ordered" + NL + "\t */";
  protected final String TEXT_1370 = NL + "\t@Deprecated";
  protected final String TEXT_1371 = NL + "\tprotected static final ";
  protected final String TEXT_1372 = ".Internal.InvocationDelegate ";
  protected final String TEXT_1373 = "__EINVOCATION_DELEGATE = ((";
  protected final String TEXT_1374 = ".Internal)";
  protected final String TEXT_1375 = ").getInvocationDelegate();" + NL;
  protected final String TEXT_1376 = NL + "\t/**";
  protected final String TEXT_1377 = NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->";
  protected final String TEXT_1378 = NL + "\t * <!-- begin-model-doc -->";
  protected final String TEXT_1379 = NL + "\t * ";
  protected final String TEXT_1380 = NL + "\t * @param ";
  protected final String TEXT_1381 = NL + "\t *   ";
  protected final String TEXT_1382 = NL + "\t * @param ";
  protected final String TEXT_1383 = " ";
  protected final String TEXT_1384 = NL + "\t * <!-- end-model-doc -->";
  protected final String TEXT_1385 = NL + "\t * @model ";
  protected final String TEXT_1386 = NL + "\t *        ";
  protected final String TEXT_1387 = NL + "\t * @model";
  protected final String TEXT_1388 = NL + "\t * @generated" + NL + "\t */";
  protected final String TEXT_1389 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->";
  protected final String TEXT_1390 = NL + "\t * ";
  protected final String TEXT_1391 = NL + "\t * @generated" + NL + "\t */";
  protected final String TEXT_1392 = NL + "\t@Deprecated";
  protected final String TEXT_1393 = NL + "\t";
  protected final String TEXT_1394 = " ";
  protected final String TEXT_1395 = "(";
  protected final String TEXT_1396 = ")";
  protected final String TEXT_1397 = ";" + NL;
  protected final String TEXT_1398 = NL + "\t@SuppressWarnings(\"unchecked\")";
  protected final String TEXT_1399 = NL + "\tpublic ";
  protected final String TEXT_1400 = " ";
  protected final String TEXT_1401 = "(";
  protected final String TEXT_1402 = ")";
  protected final String TEXT_1403 = NL + "\t{";
  protected final String TEXT_1404 = NL + "\t\t";
  protected final String TEXT_1405 = NL + "\t\treturn" + NL + "\t\t\t";
  protected final String TEXT_1406 = ".validate" + NL + "\t\t\t\t(";
  protected final String TEXT_1407 = "," + NL + "\t\t\t\t this," + NL + "\t\t\t\t ";
  protected final String TEXT_1408 = "," + NL + "\t\t\t\t ";
  protected final String TEXT_1409 = "," + NL + "\t\t\t\t \"";
  protected final String TEXT_1410 = "\",";
  protected final String TEXT_1411 = NL + "\t\t\t\t ";
  protected final String TEXT_1412 = "," + NL + "\t\t\t\t ";
  protected final String TEXT_1413 = "__EEXPRESSION," + NL + "\t\t\t\t ";
  protected final String TEXT_1414 = ".ERROR," + NL + "\t\t\t\t ";
  protected final String TEXT_1415 = ".DIAGNOSTIC_SOURCE," + NL + "\t\t\t\t ";
  protected final String TEXT_1416 = ".";
  protected final String TEXT_1417 = ");";
  protected final String TEXT_1418 = NL + "\t\t// TODO: implement this method" + NL + "\t\t// -> specify the condition that violates the invariant" + NL + "\t\t// -> verify the details of the diagnostic, including severity and message" + NL + "\t\t// Ensure that you remove @generated or mark it @generated NOT" + NL + "\t\tif (false)" + NL + "\t\t{" + NL + "\t\t\tif (";
  protected final String TEXT_1419 = " != null)" + NL + "\t\t\t{" + NL + "\t\t\t\t";
  protected final String TEXT_1420 = ".add" + NL + "\t\t\t\t\t(new ";
  protected final String TEXT_1421 = NL + "\t\t\t\t\t\t(";
  protected final String TEXT_1422 = ".ERROR," + NL + "\t\t\t\t\t\t ";
  protected final String TEXT_1423 = ".DIAGNOSTIC_SOURCE," + NL + "\t\t\t\t\t\t ";
  protected final String TEXT_1424 = ".";
  protected final String TEXT_1425 = "," + NL + "\t\t\t\t\t\t ";
  protected final String TEXT_1426 = ".INSTANCE.getString(\"_UI_GenericInvariant_diagnostic\", new Object[] { \"";
  protected final String TEXT_1427 = "\", ";
  protected final String TEXT_1428 = ".getObjectLabel(this, ";
  protected final String TEXT_1429 = ") }),";
  protected final String TEXT_1430 = NL + "\t\t\t\t\t\t new Object [] { this }));" + NL + "\t\t\t}" + NL + "\t\t\treturn false;" + NL + "\t\t}" + NL + "\t\treturn true;";
  protected final String TEXT_1431 = NL + "\t\ttry" + NL + "\t\t{";
  protected final String TEXT_1432 = NL + "\t\t\t";
  protected final String TEXT_1433 = "__EINVOCATION_DELEGATE.dynamicInvoke(this, ";
  protected final String TEXT_1434 = "new ";
  protected final String TEXT_1435 = ".UnmodifiableEList<Object>(";
  protected final String TEXT_1436 = ", ";
  protected final String TEXT_1437 = ")";
  protected final String TEXT_1438 = "null";
  protected final String TEXT_1439 = ");";
  protected final String TEXT_1440 = NL + "\t\t\treturn ";
  protected final String TEXT_1441 = "(";
  protected final String TEXT_1442 = "(";
  protected final String TEXT_1443 = ")";
  protected final String TEXT_1444 = "__EINVOCATION_DELEGATE.dynamicInvoke(this, ";
  protected final String TEXT_1445 = "new ";
  protected final String TEXT_1446 = ".UnmodifiableEList<Object>(";
  protected final String TEXT_1447 = ", ";
  protected final String TEXT_1448 = ")";
  protected final String TEXT_1449 = "null";
  protected final String TEXT_1450 = ")";
  protected final String TEXT_1451 = ").";
  protected final String TEXT_1452 = "()";
  protected final String TEXT_1453 = ";";
  protected final String TEXT_1454 = NL + "\t\t}" + NL + "\t\tcatch (";
  protected final String TEXT_1455 = " ite)" + NL + "\t\t{" + NL + "\t\t\tthrow new ";
  protected final String TEXT_1456 = "(ite);" + NL + "\t\t}";
  protected final String TEXT_1457 = NL + "\t\t// TODO: implement this method" + NL + "\t\t// Ensure that you remove @generated or mark it @generated NOT" + NL + "\t\tthrow new UnsupportedOperationException();";
  protected final String TEXT_1458 = NL + "\t}" + NL;
  protected final String TEXT_1459 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */";
  protected final String TEXT_1460 = NL + "\t@SuppressWarnings(\"unchecked\")";
  protected final String TEXT_1461 = NL + "\t@Override";
  protected final String TEXT_1462 = NL + "\tpublic ";
  protected final String TEXT_1463 = " eInverseAdd(";
  protected final String TEXT_1464 = " otherEnd, int featureID, ";
  protected final String TEXT_1465 = " msgs)" + NL + "\t{" + NL + "\t\tswitch (featureID";
  protected final String TEXT_1466 = ")" + NL + "\t\t{";
  protected final String TEXT_1467 = NL + "\t\t\tcase ";
  protected final String TEXT_1468 = ":";
  protected final String TEXT_1469 = NL + "\t\t\t\treturn ((";
  protected final String TEXT_1470 = "(";
  protected final String TEXT_1471 = ".InternalMapView";
  protected final String TEXT_1472 = ")";
  protected final String TEXT_1473 = "()).eMap()).basicAdd(otherEnd, msgs);";
  protected final String TEXT_1474 = NL + "\t\t\t\treturn (";
  protected final String TEXT_1475 = "()).basicAdd(otherEnd, msgs);";
  protected final String TEXT_1476 = NL + "\t\t\t\tif (eInternalContainer() != null)" + NL + "\t\t\t\t\tmsgs = eBasicRemoveFromContainer(msgs);";
  protected final String TEXT_1477 = NL + "\t\t\t\treturn basicSet";
  protected final String TEXT_1478 = "((";
  protected final String TEXT_1479 = ")otherEnd, msgs);";
  protected final String TEXT_1480 = NL + "\t\t\t\treturn eBasicSetContainer(otherEnd, ";
  protected final String TEXT_1481 = ", msgs);";
  protected final String TEXT_1482 = NL + "\t\t\t\t";
  protected final String TEXT_1483 = " ";
  protected final String TEXT_1484 = " = (";
  protected final String TEXT_1485 = ")eVirtualGet(";
  protected final String TEXT_1486 = ");";
  protected final String TEXT_1487 = NL + "\t\t\t\t";
  protected final String TEXT_1488 = " ";
  protected final String TEXT_1489 = " = ";
  protected final String TEXT_1490 = "basicGet";
  protected final String TEXT_1491 = "();";
  protected final String TEXT_1492 = NL + "\t\t\t\tif (";
  protected final String TEXT_1493 = " != null)";
  protected final String TEXT_1494 = NL + "\t\t\t\t\tmsgs = ((";
  protected final String TEXT_1495 = ")";
  protected final String TEXT_1496 = ").eInverseRemove(this, EOPPOSITE_FEATURE_BASE - ";
  protected final String TEXT_1497 = ", null, msgs);";
  protected final String TEXT_1498 = NL + "\t\t\t\t\tmsgs = ((";
  protected final String TEXT_1499 = ")";
  protected final String TEXT_1500 = ").eInverseRemove(this, ";
  protected final String TEXT_1501 = ", ";
  protected final String TEXT_1502 = ".class, msgs);";
  protected final String TEXT_1503 = NL + "\t\t\t\treturn basicSet";
  protected final String TEXT_1504 = "((";
  protected final String TEXT_1505 = ")otherEnd, msgs);";
  protected final String TEXT_1506 = NL + "\t\t}";
  protected final String TEXT_1507 = NL + "\t\treturn super.eInverseAdd(otherEnd, featureID, msgs);";
  protected final String TEXT_1508 = NL + "\t\treturn eDynamicInverseAdd(otherEnd, featureID, msgs);";
  protected final String TEXT_1509 = NL + "\t}" + NL;
  protected final String TEXT_1510 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */";
  protected final String TEXT_1511 = NL + "\t@Override";
  protected final String TEXT_1512 = NL + "\tpublic ";
  protected final String TEXT_1513 = " eInverseRemove(";
  protected final String TEXT_1514 = " otherEnd, int featureID, ";
  protected final String TEXT_1515 = " msgs)" + NL + "\t{" + NL + "\t\tswitch (featureID";
  protected final String TEXT_1516 = ")" + NL + "\t\t{";
  protected final String TEXT_1517 = NL + "\t\t\tcase ";
  protected final String TEXT_1518 = ":";
  protected final String TEXT_1519 = NL + "\t\t\t\treturn ((";
  protected final String TEXT_1520 = ")((";
  protected final String TEXT_1521 = ".InternalMapView";
  protected final String TEXT_1522 = ")";
  protected final String TEXT_1523 = "()).eMap()).basicRemove(otherEnd, msgs);";
  protected final String TEXT_1524 = NL + "\t\t\t\treturn ((";
  protected final String TEXT_1525 = ")((";
  protected final String TEXT_1526 = ".Internal.Wrapper)";
  protected final String TEXT_1527 = "()).featureMap()).basicRemove(otherEnd, msgs);";
  protected final String TEXT_1528 = NL + "\t\t\t\treturn ((";
  protected final String TEXT_1529 = ")";
  protected final String TEXT_1530 = "()).basicRemove(otherEnd, msgs);";
  protected final String TEXT_1531 = NL + "\t\t\t\treturn eBasicSetContainer(null, ";
  protected final String TEXT_1532 = ", msgs);";
  protected final String TEXT_1533 = NL + "\t\t\t\treturn basicUnset";
  protected final String TEXT_1534 = "(msgs);";
  protected final String TEXT_1535 = NL + "\t\t\t\treturn basicSet";
  protected final String TEXT_1536 = "(null, msgs);";
  protected final String TEXT_1537 = NL + "\t\t}";
  protected final String TEXT_1538 = NL + "\t\treturn super.eInverseRemove(otherEnd, featureID, msgs);";
  protected final String TEXT_1539 = NL + "\t\treturn eDynamicInverseRemove(otherEnd, featureID, msgs);";
  protected final String TEXT_1540 = NL + "\t}" + NL;
  protected final String TEXT_1541 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */";
  protected final String TEXT_1542 = NL + "\t@Override";
  protected final String TEXT_1543 = NL + "\tpublic ";
  protected final String TEXT_1544 = " eBasicRemoveFromContainerFeature(";
  protected final String TEXT_1545 = " msgs)" + NL + "\t{" + NL + "\t\tswitch (eContainerFeatureID()";
  protected final String TEXT_1546 = ")" + NL + "\t\t{";
  protected final String TEXT_1547 = NL + "\t\t\tcase ";
  protected final String TEXT_1548 = ":" + NL + "\t\t\t\treturn eInternalContainer().eInverseRemove(this, ";
  protected final String TEXT_1549 = ", ";
  protected final String TEXT_1550 = ".class, msgs);";
  protected final String TEXT_1551 = NL + "\t\t}";
  protected final String TEXT_1552 = NL + "\t\treturn super.eBasicRemoveFromContainerFeature(msgs);";
  protected final String TEXT_1553 = NL + "\t\treturn eDynamicBasicRemoveFromContainer(msgs);";
  protected final String TEXT_1554 = NL + "\t}" + NL;
  protected final String TEXT_1555 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */";
  protected final String TEXT_1556 = NL + "\t@Override";
  protected final String TEXT_1557 = NL + "\tpublic Object eGet(int featureID, boolean resolve, boolean coreType)" + NL + "\t{" + NL + "\t\tswitch (featureID";
  protected final String TEXT_1558 = ")" + NL + "\t\t{";
  protected final String TEXT_1559 = NL + "\t\t\tcase ";
  protected final String TEXT_1560 = ":";
  protected final String TEXT_1561 = NL + "\t\t\t\treturn ";
  protected final String TEXT_1562 = "();";
  protected final String TEXT_1563 = NL + "\t\t\t\treturn ";
  protected final String TEXT_1564 = "() ? Boolean.TRUE : Boolean.FALSE;";
  protected final String TEXT_1565 = NL + "\t\t\t\treturn new ";
  protected final String TEXT_1566 = "(";
  protected final String TEXT_1567 = "());";
  protected final String TEXT_1568 = NL + "\t\t\t\tif (resolve) return ";
  protected final String TEXT_1569 = "();" + NL + "\t\t\t\treturn basicGet";
  protected final String TEXT_1570 = "();";
  protected final String TEXT_1571 = NL + "\t\t\t\tif (coreType) return ((";
  protected final String TEXT_1572 = ".InternalMapView";
  protected final String TEXT_1573 = ")";
  protected final String TEXT_1574 = "()).eMap();" + NL + "\t\t\t\telse return ";
  protected final String TEXT_1575 = "();";
  protected final String TEXT_1576 = NL + "\t\t\t\tif (coreType) return ";
  protected final String TEXT_1577 = "();" + NL + "\t\t\t\telse return ";
  protected final String TEXT_1578 = "().map();";
  protected final String TEXT_1579 = NL + "\t\t\t\tif (coreType) return ((";
  protected final String TEXT_1580 = ".Internal.Wrapper)";
  protected final String TEXT_1581 = "()).featureMap();" + NL + "\t\t\t\treturn ";
  protected final String TEXT_1582 = "();";
  protected final String TEXT_1583 = NL + "\t\t\t\tif (coreType) return ";
  protected final String TEXT_1584 = "();" + NL + "\t\t\t\treturn ((";
  protected final String TEXT_1585 = ".Internal)";
  protected final String TEXT_1586 = "()).getWrapper();";
  protected final String TEXT_1587 = NL + "\t\t\t\treturn ";
  protected final String TEXT_1588 = "();";
  protected final String TEXT_1589 = NL + "\t\t}";
  protected final String TEXT_1590 = NL + "\t\treturn super.eGet(featureID, resolve, coreType);";
  protected final String TEXT_1591 = NL + "\t\treturn eDynamicGet(featureID, resolve, coreType);";
  protected final String TEXT_1592 = NL + "\t}" + NL;
  protected final String TEXT_1593 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */";
  protected final String TEXT_1594 = NL + "\t@SuppressWarnings(\"unchecked\")";
  protected final String TEXT_1595 = NL + "\t@Override";
  protected final String TEXT_1596 = NL + "\tpublic void eSet(int featureID, Object newValue)" + NL + "\t{" + NL + "\t\tswitch (featureID";
  protected final String TEXT_1597 = ")" + NL + "\t\t{";
  protected final String TEXT_1598 = NL + "\t\t\tcase ";
  protected final String TEXT_1599 = ":";
  protected final String TEXT_1600 = NL + "\t\t\t\t((";
  protected final String TEXT_1601 = ".Internal)((";
  protected final String TEXT_1602 = ".Internal.Wrapper)";
  protected final String TEXT_1603 = "()).featureMap()).set(newValue);";
  protected final String TEXT_1604 = NL + "\t\t\t\t((";
  protected final String TEXT_1605 = ".Internal)";
  protected final String TEXT_1606 = "()).set(newValue);";
  protected final String TEXT_1607 = NL + "\t\t\t\t((";
  protected final String TEXT_1608 = ".Setting)((";
  protected final String TEXT_1609 = ".InternalMapView";
  protected final String TEXT_1610 = ")";
  protected final String TEXT_1611 = "()).eMap()).set(newValue);";
  protected final String TEXT_1612 = NL + "\t\t\t\t((";
  protected final String TEXT_1613 = ".Setting)";
  protected final String TEXT_1614 = "()).set(newValue);";
  protected final String TEXT_1615 = NL + "\t\t\t\t";
  protected final String TEXT_1616 = "().clear();" + NL + "\t\t\t\t";
  protected final String TEXT_1617 = "().addAll((";
  protected final String TEXT_1618 = "<? extends ";
  protected final String TEXT_1619 = ">";
  protected final String TEXT_1620 = ")newValue);";
  protected final String TEXT_1621 = NL + "\t\t\t\tset";
  protected final String TEXT_1622 = "(((";
  protected final String TEXT_1623 = ")newValue).";
  protected final String TEXT_1624 = "());";
  protected final String TEXT_1625 = NL + "\t\t\t\tset";
  protected final String TEXT_1626 = "(";
  protected final String TEXT_1627 = "(";
  protected final String TEXT_1628 = ")";
  protected final String TEXT_1629 = "newValue);";
  protected final String TEXT_1630 = NL + "\t\t\t\treturn;";
  protected final String TEXT_1631 = NL + "\t\t}";
  protected final String TEXT_1632 = NL + "\t\tsuper.eSet(featureID, newValue);";
  protected final String TEXT_1633 = NL + "\t\teDynamicSet(featureID, newValue);";
  protected final String TEXT_1634 = NL + "\t}" + NL;
  protected final String TEXT_1635 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */";
  protected final String TEXT_1636 = NL + "\t@Override";
  protected final String TEXT_1637 = NL + "\tpublic void eUnset(int featureID)" + NL + "\t{" + NL + "\t\tswitch (featureID";
  protected final String TEXT_1638 = ")" + NL + "\t\t{";
  protected final String TEXT_1639 = NL + "\t\t\tcase ";
  protected final String TEXT_1640 = ":";
  protected final String TEXT_1641 = NL + "\t\t\t\t((";
  protected final String TEXT_1642 = ".Internal.Wrapper)";
  protected final String TEXT_1643 = "()).featureMap().clear();";
  protected final String TEXT_1644 = NL + "\t\t\t\t";
  protected final String TEXT_1645 = "().clear();";
  protected final String TEXT_1646 = NL + "\t\t\t\tunset";
  protected final String TEXT_1647 = "();";
  protected final String TEXT_1648 = NL + "\t\t\t\tset";
  protected final String TEXT_1649 = "((";
  protected final String TEXT_1650 = ")null);";
  protected final String TEXT_1651 = NL + "\t\t\t\t";
  protected final String TEXT_1652 = "__ESETTING_DELEGATE.dynamicUnset(this, null, 0);";
  protected final String TEXT_1653 = NL + "\t\t\t\tset";
  protected final String TEXT_1654 = "(";
  protected final String TEXT_1655 = ");";
  protected final String TEXT_1656 = NL + "\t\t\t\treturn;";
  protected final String TEXT_1657 = NL + "\t\t}";
  protected final String TEXT_1658 = NL + "\t\tsuper.eUnset(featureID);";
  protected final String TEXT_1659 = NL + "\t\teDynamicUnset(featureID);";
  protected final String TEXT_1660 = NL + "\t}" + NL;
  protected final String TEXT_1661 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */";
  protected final String TEXT_1662 = NL + "\t@SuppressWarnings(\"unchecked\")";
  protected final String TEXT_1663 = NL + "\t@Override";
  protected final String TEXT_1664 = NL + "\tpublic boolean eIsSet(int featureID)" + NL + "\t{" + NL + "\t\tswitch (featureID";
  protected final String TEXT_1665 = ")" + NL + "\t\t{";
  protected final String TEXT_1666 = NL + "\t\t\tcase ";
  protected final String TEXT_1667 = ":";
  protected final String TEXT_1668 = NL + "\t\t\t\treturn isSet";
  protected final String TEXT_1669 = "();";
  protected final String TEXT_1670 = NL + "\t\t\t\treturn ";
  protected final String TEXT_1671 = "__ESETTING_DELEGATE.dynamicIsSet(this, null, 0);";
  protected final String TEXT_1672 = NL + "\t\t\t\treturn !((";
  protected final String TEXT_1673 = ".Internal.Wrapper)";
  protected final String TEXT_1674 = "()).featureMap().isEmpty();";
  protected final String TEXT_1675 = NL + "\t\t\t\treturn ";
  protected final String TEXT_1676 = " != null && !";
  protected final String TEXT_1677 = ".featureMap().isEmpty();";
  protected final String TEXT_1678 = NL + "\t\t\t\treturn ";
  protected final String TEXT_1679 = " != null && !";
  protected final String TEXT_1680 = ".isEmpty();";
  protected final String TEXT_1681 = NL + "\t\t\t\t";
  protected final String TEXT_1682 = " ";
  protected final String TEXT_1683 = " = (";
  protected final String TEXT_1684 = ")eVirtualGet(";
  protected final String TEXT_1685 = ");" + NL + "\t\t\t\treturn ";
  protected final String TEXT_1686 = " != null && !";
  protected final String TEXT_1687 = ".isEmpty();";
  protected final String TEXT_1688 = NL + "\t\t\t\treturn !";
  protected final String TEXT_1689 = "().isEmpty();";
  protected final String TEXT_1690 = NL + "\t\t\t\treturn isSet";
  protected final String TEXT_1691 = "();";
  protected final String TEXT_1692 = NL + "\t\t\t\treturn ";
  protected final String TEXT_1693 = " != null;";
  protected final String TEXT_1694 = NL + "\t\t\t\treturn eVirtualGet(";
  protected final String TEXT_1695 = ") != null;";
  protected final String TEXT_1696 = NL + "\t\t\t\treturn basicGet";
  protected final String TEXT_1697 = "() != null;";
  protected final String TEXT_1698 = NL + "\t\t\t\treturn ";
  protected final String TEXT_1699 = " != null;";
  protected final String TEXT_1700 = NL + "\t\t\t\treturn eVirtualGet(";
  protected final String TEXT_1701 = ") != null;";
  protected final String TEXT_1702 = NL + "\t\t\t\treturn ";
  protected final String TEXT_1703 = "() != null;";
  protected final String TEXT_1704 = NL + "\t\t\t\treturn ((";
  protected final String TEXT_1705 = " & ";
  protected final String TEXT_1706 = "_EFLAG) != 0) != ";
  protected final String TEXT_1707 = ";";
  protected final String TEXT_1708 = NL + "\t\t\t\treturn (";
  protected final String TEXT_1709 = " & ";
  protected final String TEXT_1710 = "_EFLAG) != ";
  protected final String TEXT_1711 = "_EFLAG_DEFAULT;";
  protected final String TEXT_1712 = NL + "\t\t\t\treturn ";
  protected final String TEXT_1713 = " != ";
  protected final String TEXT_1714 = ";";
  protected final String TEXT_1715 = NL + "\t\t\t\treturn eVirtualGet(";
  protected final String TEXT_1716 = ", ";
  protected final String TEXT_1717 = ") != ";
  protected final String TEXT_1718 = ";";
  protected final String TEXT_1719 = NL + "\t\t\t\treturn ";
  protected final String TEXT_1720 = "() != ";
  protected final String TEXT_1721 = ";";
  protected final String TEXT_1722 = NL + "\t\t\t\treturn ";
  protected final String TEXT_1723 = " == null ? ";
  protected final String TEXT_1724 = " != null : !";
  protected final String TEXT_1725 = ".equals(";
  protected final String TEXT_1726 = ");";
  protected final String TEXT_1727 = NL + "\t\t\t\t";
  protected final String TEXT_1728 = " ";
  protected final String TEXT_1729 = " = (";
  protected final String TEXT_1730 = ")eVirtualGet(";
  protected final String TEXT_1731 = ", ";
  protected final String TEXT_1732 = ");" + NL + "\t\t\t\treturn ";
  protected final String TEXT_1733 = " == null ? ";
  protected final String TEXT_1734 = " != null : !";
  protected final String TEXT_1735 = ".equals(";
  protected final String TEXT_1736 = ");";
  protected final String TEXT_1737 = NL + "\t\t\t\treturn ";
  protected final String TEXT_1738 = " == null ? ";
  protected final String TEXT_1739 = "() != null : !";
  protected final String TEXT_1740 = ".equals(";
  protected final String TEXT_1741 = "());";
  protected final String TEXT_1742 = NL + "\t\t}";
  protected final String TEXT_1743 = NL + "\t\treturn super.eIsSet(featureID);";
  protected final String TEXT_1744 = NL + "\t\treturn eDynamicIsSet(featureID);";
  protected final String TEXT_1745 = NL + "\t}" + NL;
  protected final String TEXT_1746 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */";
  protected final String TEXT_1747 = NL + "\t@Override";
  protected final String TEXT_1748 = NL + "\tpublic int eBaseStructuralFeatureID(int derivedFeatureID, Class";
  protected final String TEXT_1749 = " baseClass)" + NL + "\t{";
  protected final String TEXT_1750 = NL + "\t\tif (baseClass == ";
  protected final String TEXT_1751 = ".class)" + NL + "\t\t{" + NL + "\t\t\tswitch (derivedFeatureID";
  protected final String TEXT_1752 = ")" + NL + "\t\t\t{";
  protected final String TEXT_1753 = NL + "\t\t\t\tcase ";
  protected final String TEXT_1754 = ": return ";
  protected final String TEXT_1755 = ";";
  protected final String TEXT_1756 = NL + "\t\t\t\tdefault: return -1;" + NL + "\t\t\t}" + NL + "\t\t}";
  protected final String TEXT_1757 = NL + "\t\treturn super.eBaseStructuralFeatureID(derivedFeatureID, baseClass);" + NL + "\t}";
  protected final String TEXT_1758 = NL + NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */";
  protected final String TEXT_1759 = NL + "\t@Override";
  protected final String TEXT_1760 = NL + "\tpublic int eDerivedStructuralFeatureID(int baseFeatureID, Class";
  protected final String TEXT_1761 = " baseClass)" + NL + "\t{";
  protected final String TEXT_1762 = NL + "\t\tif (baseClass == ";
  protected final String TEXT_1763 = ".class)" + NL + "\t\t{" + NL + "\t\t\tswitch (baseFeatureID)" + NL + "\t\t\t{";
  protected final String TEXT_1764 = NL + "\t\t\t\tcase ";
  protected final String TEXT_1765 = ": return ";
  protected final String TEXT_1766 = ";";
  protected final String TEXT_1767 = NL + "\t\t\t\tdefault: return -1;" + NL + "\t\t\t}" + NL + "\t\t}";
  protected final String TEXT_1768 = NL + "\t\tif (baseClass == ";
  protected final String TEXT_1769 = ".class)" + NL + "\t\t{" + NL + "\t\t\tswitch (baseFeatureID";
  protected final String TEXT_1770 = ")" + NL + "\t\t\t{";
  protected final String TEXT_1771 = NL + "\t\t\t\tcase ";
  protected final String TEXT_1772 = ": return ";
  protected final String TEXT_1773 = ";";
  protected final String TEXT_1774 = NL + "\t\t\t\tdefault: return -1;" + NL + "\t\t\t}" + NL + "\t\t}";
  protected final String TEXT_1775 = NL + "\t\treturn super.eDerivedStructuralFeatureID(baseFeatureID, baseClass);" + NL + "\t}" + NL;
  protected final String TEXT_1776 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */";
  protected final String TEXT_1777 = NL + "\t@Override";
  protected final String TEXT_1778 = NL + "\tpublic int eDerivedOperationID(int baseOperationID, Class";
  protected final String TEXT_1779 = " baseClass)" + NL + "\t{";
  protected final String TEXT_1780 = NL + "\t\tif (baseClass == ";
  protected final String TEXT_1781 = ".class)" + NL + "\t\t{" + NL + "\t\t\tswitch (baseOperationID)" + NL + "\t\t\t{";
  protected final String TEXT_1782 = NL + "\t\t\t\tcase ";
  protected final String TEXT_1783 = ": return ";
  protected final String TEXT_1784 = ";";
  protected final String TEXT_1785 = NL + "\t\t\t\tdefault: return super.eDerivedOperationID(baseOperationID, baseClass);" + NL + "\t\t\t}" + NL + "\t\t}";
  protected final String TEXT_1786 = NL + "\t\tif (baseClass == ";
  protected final String TEXT_1787 = ".class)" + NL + "\t\t{" + NL + "\t\t\tswitch (baseOperationID)" + NL + "\t\t\t{";
  protected final String TEXT_1788 = NL + "\t\t\t\tcase ";
  protected final String TEXT_1789 = ": return ";
  protected final String TEXT_1790 = ";";
  protected final String TEXT_1791 = NL + "\t\t\t\tdefault: return -1;" + NL + "\t\t\t}" + NL + "\t\t}";
  protected final String TEXT_1792 = NL + "\t\tif (baseClass == ";
  protected final String TEXT_1793 = ".class)" + NL + "\t\t{" + NL + "\t\t\tswitch (baseOperationID";
  protected final String TEXT_1794 = ")" + NL + "\t\t\t{";
  protected final String TEXT_1795 = NL + "\t\t\t\tcase ";
  protected final String TEXT_1796 = ": return ";
  protected final String TEXT_1797 = ";";
  protected final String TEXT_1798 = NL + "\t\t\t\tdefault: return -1;" + NL + "\t\t\t}" + NL + "\t\t}";
  protected final String TEXT_1799 = NL + "\t\treturn super.eDerivedOperationID(baseOperationID, baseClass);" + NL + "\t}" + NL;
  protected final String TEXT_1800 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */";
  protected final String TEXT_1801 = NL + "\t@Override";
  protected final String TEXT_1802 = NL + "\tprotected Object[] eVirtualValues()" + NL + "\t{" + NL + "\t\treturn ";
  protected final String TEXT_1803 = ";" + NL + "\t}" + NL + "" + NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */";
  protected final String TEXT_1804 = NL + "\t@Override";
  protected final String TEXT_1805 = NL + "\tprotected void eSetVirtualValues(Object[] newValues)" + NL + "\t{" + NL + "\t\t";
  protected final String TEXT_1806 = " = newValues;" + NL + "\t}" + NL;
  protected final String TEXT_1807 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */";
  protected final String TEXT_1808 = NL + "\t@Override";
  protected final String TEXT_1809 = NL + "\tprotected int eVirtualIndexBits(int offset)" + NL + "\t{" + NL + "\t\tswitch (offset)" + NL + "\t\t{";
  protected final String TEXT_1810 = NL + "\t\t\tcase ";
  protected final String TEXT_1811 = " :" + NL + "\t\t\t\treturn ";
  protected final String TEXT_1812 = ";";
  protected final String TEXT_1813 = NL + "\t\t\tdefault :" + NL + "\t\t\t\tthrow new IndexOutOfBoundsException();" + NL + "\t\t}" + NL + "\t}" + NL + "" + NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */";
  protected final String TEXT_1814 = NL + "\t@Override";
  protected final String TEXT_1815 = NL + "\tprotected void eSetVirtualIndexBits(int offset, int newIndexBits)" + NL + "\t{" + NL + "\t\tswitch (offset)" + NL + "\t\t{";
  protected final String TEXT_1816 = NL + "\t\t\tcase ";
  protected final String TEXT_1817 = " :" + NL + "\t\t\t\t";
  protected final String TEXT_1818 = " = newIndexBits;" + NL + "\t\t\t\tbreak;";
  protected final String TEXT_1819 = NL + "\t\t\tdefault :" + NL + "\t\t\t\tthrow new IndexOutOfBoundsException();" + NL + "\t\t}" + NL + "\t}" + NL;
  protected final String TEXT_1820 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */";
  protected final String TEXT_1821 = NL + "\t@Override";
  protected final String TEXT_1822 = NL + "\t@SuppressWarnings(";
  protected final String TEXT_1823 = "\"unchecked\"";
  protected final String TEXT_1824 = "{\"rawtypes\", \"unchecked\" }";
  protected final String TEXT_1825 = ")";
  protected final String TEXT_1826 = NL + "\tpublic Object eInvoke(int operationID, ";
  protected final String TEXT_1827 = " arguments) throws ";
  protected final String TEXT_1828 = NL + "\t{" + NL + "\t\tswitch (operationID";
  protected final String TEXT_1829 = ")" + NL + "\t\t{";
  protected final String TEXT_1830 = NL + "\t\t\tcase ";
  protected final String TEXT_1831 = ":";
  protected final String TEXT_1832 = NL + "\t\t\t\ttry" + NL + "\t\t\t\t{";
  protected final String TEXT_1833 = NL + "\t\t\t\t";
  protected final String TEXT_1834 = "(";
  protected final String TEXT_1835 = "(";
  protected final String TEXT_1836 = "(";
  protected final String TEXT_1837 = ")";
  protected final String TEXT_1838 = "arguments.get(";
  protected final String TEXT_1839 = ")";
  protected final String TEXT_1840 = ").";
  protected final String TEXT_1841 = "()";
  protected final String TEXT_1842 = ", ";
  protected final String TEXT_1843 = ");" + NL + "\t\t\t\t";
  protected final String TEXT_1844 = "return null;";
  protected final String TEXT_1845 = NL + "\t\t\t\t";
  protected final String TEXT_1846 = "return ";
  protected final String TEXT_1847 = "new ";
  protected final String TEXT_1848 = "(";
  protected final String TEXT_1849 = "(";
  protected final String TEXT_1850 = "(";
  protected final String TEXT_1851 = "(";
  protected final String TEXT_1852 = ")";
  protected final String TEXT_1853 = "arguments.get(";
  protected final String TEXT_1854 = ")";
  protected final String TEXT_1855 = ").";
  protected final String TEXT_1856 = "()";
  protected final String TEXT_1857 = ", ";
  protected final String TEXT_1858 = ")";
  protected final String TEXT_1859 = ")";
  protected final String TEXT_1860 = ";";
  protected final String TEXT_1861 = NL + "\t\t\t\t}" + NL + "\t\t\t\tcatch (";
  protected final String TEXT_1862 = " throwable)" + NL + "\t\t\t\t{" + NL + "\t\t\t\t\tthrow new ";
  protected final String TEXT_1863 = "(throwable);" + NL + "\t\t\t\t}";
  protected final String TEXT_1864 = NL + "\t\t}";
  protected final String TEXT_1865 = NL + "\t\treturn super.eInvoke(operationID, arguments);";
  protected final String TEXT_1866 = NL + "\t\treturn eDynamicInvoke(operationID, arguments);";
  protected final String TEXT_1867 = NL + "\t}" + NL;
  protected final String TEXT_1868 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */";
  protected final String TEXT_1869 = NL + "\t@Override";
  protected final String TEXT_1870 = NL + "\tpublic String toString()" + NL + "\t{" + NL + "\t\tif (eIsProxy()) return super.toString();" + NL + "" + NL + "\t\t";
  protected final String TEXT_1871 = " result = new ";
  protected final String TEXT_1872 = "(super.toString());";
  protected final String TEXT_1873 = NL + "\t\tresult.append(\" (";
  protected final String TEXT_1874 = ": \");";
  protected final String TEXT_1875 = NL + "\t\tresult.append(\", ";
  protected final String TEXT_1876 = ": \");";
  protected final String TEXT_1877 = NL + "\t\tif (eVirtualIsSet(";
  protected final String TEXT_1878 = ")) result.append(eVirtualGet(";
  protected final String TEXT_1879 = ")); else result.append(\"<unset>\");";
  protected final String TEXT_1880 = NL + "\t\tif (";
  protected final String TEXT_1881 = "(";
  protected final String TEXT_1882 = " & ";
  protected final String TEXT_1883 = "_ESETFLAG) != 0";
  protected final String TEXT_1884 = "ESet";
  protected final String TEXT_1885 = ") result.append((";
  protected final String TEXT_1886 = " & ";
  protected final String TEXT_1887 = "_EFLAG) != 0); else result.append(\"<unset>\");";
  protected final String TEXT_1888 = NL + "\t\tif (";
  protected final String TEXT_1889 = "(";
  protected final String TEXT_1890 = " & ";
  protected final String TEXT_1891 = "_ESETFLAG) != 0";
  protected final String TEXT_1892 = "ESet";
  protected final String TEXT_1893 = ") result.append(";
  protected final String TEXT_1894 = "_EFLAG_VALUES[(";
  protected final String TEXT_1895 = " & ";
  protected final String TEXT_1896 = "_EFLAG) >>> ";
  protected final String TEXT_1897 = "_EFLAG_OFFSET]); else result.append(\"<unset>\");";
  protected final String TEXT_1898 = NL + "\t\tif (";
  protected final String TEXT_1899 = "(";
  protected final String TEXT_1900 = " & ";
  protected final String TEXT_1901 = "_ESETFLAG) != 0";
  protected final String TEXT_1902 = "ESet";
  protected final String TEXT_1903 = ") result.append(";
  protected final String TEXT_1904 = "); else result.append(\"<unset>\");";
  protected final String TEXT_1905 = NL + "\t\tresult.append(eVirtualGet(";
  protected final String TEXT_1906 = ", ";
  protected final String TEXT_1907 = "));";
  protected final String TEXT_1908 = NL + "\t\tresult.append((";
  protected final String TEXT_1909 = " & ";
  protected final String TEXT_1910 = "_EFLAG) != 0);";
  protected final String TEXT_1911 = NL + "\t\tresult.append(";
  protected final String TEXT_1912 = "_EFLAG_VALUES[(";
  protected final String TEXT_1913 = " & ";
  protected final String TEXT_1914 = "_EFLAG) >>> ";
  protected final String TEXT_1915 = "_EFLAG_OFFSET]);";
  protected final String TEXT_1916 = NL + "\t\tresult.append(";
  protected final String TEXT_1917 = ");";
  protected final String TEXT_1918 = NL + "\t\tresult.append(')');" + NL + "\t\treturn result.toString();" + NL + "\t}" + NL;
  protected final String TEXT_1919 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */";
  protected final String TEXT_1920 = NL + "\t@";
  protected final String TEXT_1921 = NL + "\tprotected int hash = -1;" + NL + "" + NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */" + NL + "\tpublic int getHash()" + NL + "\t{" + NL + "\t\tif (hash == -1)" + NL + "\t\t{" + NL + "\t\t\t";
  protected final String TEXT_1922 = " theKey = getKey();" + NL + "\t\t\thash = (theKey == null ? 0 : theKey.hashCode());" + NL + "\t\t}" + NL + "\t\treturn hash;" + NL + "\t}" + NL + "" + NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */" + NL + "\tpublic void setHash(int hash)" + NL + "\t{" + NL + "\t\tthis.hash = hash;" + NL + "\t}" + NL + "" + NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */" + NL + "\tpublic ";
  protected final String TEXT_1923 = " getKey()" + NL + "\t{";
  protected final String TEXT_1924 = NL + "\t\treturn new ";
  protected final String TEXT_1925 = "(getTypedKey());";
  protected final String TEXT_1926 = NL + "\t\treturn getTypedKey();";
  protected final String TEXT_1927 = NL + "\t}" + NL + "" + NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */" + NL + "\tpublic void setKey(";
  protected final String TEXT_1928 = " key)" + NL + "\t{";
  protected final String TEXT_1929 = NL + "\t\tgetTypedKey().addAll(";
  protected final String TEXT_1930 = "(";
  protected final String TEXT_1931 = ")";
  protected final String TEXT_1932 = "key);";
  protected final String TEXT_1933 = NL + "\t\tsetTypedKey(key);";
  protected final String TEXT_1934 = NL + "\t\tsetTypedKey(((";
  protected final String TEXT_1935 = ")key).";
  protected final String TEXT_1936 = "());";
  protected final String TEXT_1937 = NL + "\t\tsetTypedKey((";
  protected final String TEXT_1938 = ")key);";
  protected final String TEXT_1939 = NL + "\t}" + NL + "" + NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */" + NL + "\tpublic ";
  protected final String TEXT_1940 = " getValue()" + NL + "\t{";
  protected final String TEXT_1941 = NL + "\t\treturn new ";
  protected final String TEXT_1942 = "(getTypedValue());";
  protected final String TEXT_1943 = NL + "\t\treturn getTypedValue();";
  protected final String TEXT_1944 = NL + "\t}" + NL + "" + NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */" + NL + "\tpublic ";
  protected final String TEXT_1945 = " setValue(";
  protected final String TEXT_1946 = " value)" + NL + "\t{" + NL + "\t\t";
  protected final String TEXT_1947 = " oldValue = getValue();";
  protected final String TEXT_1948 = NL + "\t\tgetTypedValue().clear();" + NL + "\t\tgetTypedValue().addAll(";
  protected final String TEXT_1949 = "(";
  protected final String TEXT_1950 = ")";
  protected final String TEXT_1951 = "value);";
  protected final String TEXT_1952 = NL + "\t\tsetTypedValue(value);";
  protected final String TEXT_1953 = NL + "\t\tsetTypedValue(((";
  protected final String TEXT_1954 = ")value).";
  protected final String TEXT_1955 = "());";
  protected final String TEXT_1956 = NL + "\t\tsetTypedValue((";
  protected final String TEXT_1957 = ")value);";
  protected final String TEXT_1958 = NL + "\t\treturn oldValue;" + NL + "\t}" + NL + "" + NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */";
  protected final String TEXT_1959 = NL + "\t@SuppressWarnings(\"unchecked\")";
  protected final String TEXT_1960 = NL + "\tpublic ";
  protected final String TEXT_1961 = " getEMap()" + NL + "\t{" + NL + "\t\t";
  protected final String TEXT_1962 = " container = eContainer();" + NL + "\t\treturn container == null ? null : (";
  protected final String TEXT_1963 = ")container.eGet(eContainmentFeature());" + NL + "\t}" + NL;
  protected final String TEXT_1964 = NL + "} //";
  protected final String TEXT_1965 = NL;

  public String generate(Object argument)
  {
    final StringBuffer stringBuffer = new StringBuffer();
    
/**
 * Copyright (c) 2002-2011 IBM Corporation and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *   IBM - Initial API and implementation
 */

    final GenClass genClass = (GenClass)((Object[])argument)[0]; final GenPackage genPackage = genClass.getGenPackage(); final GenModel genModel=genPackage.getGenModel();
    final boolean isJDK50 = genModel.getComplianceLevel().getValue() >= GenJDKLevel.JDK50;
    final boolean isInterface = Boolean.TRUE.equals(((Object[])argument)[1]); final boolean isImplementation = Boolean.TRUE.equals(((Object[])argument)[2]);
    final boolean isGWT = genModel.getRuntimePlatform() == GenRuntimePlatform.GWT;
    final String publicStaticFinalFlag = isImplementation ? "public static final " : "";
    final String singleWildcard = isJDK50 ? "<?>" : "";
    final String negativeOffsetCorrection = genClass.hasOffsetCorrection() ? " - " + genClass.getOffsetCorrectionField(null) : "";
    final String positiveOffsetCorrection = genClass.hasOffsetCorrection() ? " + " + genClass.getOffsetCorrectionField(null) : "";
    final String negativeOperationOffsetCorrection = genClass.hasOffsetCorrection() ? " - EOPERATION_OFFSET_CORRECTION" : "";
    final String positiveOperationOffsetCorrection = genClass.hasOffsetCorrection() ? " + EOPERATION_OFFSET_CORRECTION" : "";
    stringBuffer.append(TEXT_1);
    stringBuffer.append(TEXT_2);
    {GenBase copyrightHolder = argument instanceof GenBase ? (GenBase)argument : argument instanceof Object[] && ((Object[])argument)[0] instanceof GenBase ? (GenBase)((Object[])argument)[0] : null;
    if (copyrightHolder != null && copyrightHolder.hasCopyright()) {
    stringBuffer.append(TEXT_3);
    stringBuffer.append(copyrightHolder.getCopyright(copyrightHolder.getGenModel().getIndentation(stringBuffer)));
    }}
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
    if (isImplementation) { genClass.addClassPsuedoImports(); }
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
    if (genClass.hasImplicitAPITags(true)) {
    stringBuffer.append(TEXT_30);
    stringBuffer.append(genClass.getImplicitAPITags(genModel.getIndentation(stringBuffer), true));
    }
    stringBuffer.append(TEXT_31);
    //Class/interface.javadoc.override.javajetinc
    } else {
    stringBuffer.append(TEXT_32);
    stringBuffer.append(genClass.getFormattedName());
    stringBuffer.append(TEXT_33);
    if (!genClass.getImplementedGenFeatures().isEmpty()) {
    stringBuffer.append(TEXT_34);
    for (GenFeature genFeature : genClass.getImplementedGenFeatures()) {
    stringBuffer.append(TEXT_35);
    stringBuffer.append(genClass.getQualifiedClassName());
    stringBuffer.append(TEXT_36);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_37);
    stringBuffer.append(genFeature.getFormattedName());
    stringBuffer.append(TEXT_38);
    }
    stringBuffer.append(TEXT_39);
    }
    stringBuffer.append(TEXT_40);
    if (genClass.hasImplicitAPITags()) {
    stringBuffer.append(TEXT_41);
    stringBuffer.append(genClass.getImplicitAPITags(genModel.getIndentation(stringBuffer)));
    }
    stringBuffer.append(TEXT_42);
    }
    if (isJDK50 && genClass.hasImplicitAPIDeprecatedTag()) {
    stringBuffer.append(TEXT_43);
    }
    if (isImplementation) {
    if (isJDK50 && !genClass.hasAPIDeprecatedTag() && GenModelUtil.hasImplicitAPIDeprecatedTag(genClass.getEGetGenFeatures(), genClass.getEIsSetGenFeatures(), genClass.getESetGenFeatures(), genClass.getEUnsetGenFeatures(), genClass.getEInverseAddGenFeatures(), genClass.getEInverseRemoveGenFeatures(), genClass.getEBasicRemoveFromContainerGenFeatures(), genClass.getToStringGenFeatures())) {
    stringBuffer.append(TEXT_44);
    }
    stringBuffer.append(TEXT_45);
    if (genClass.isAbstract()) {
    stringBuffer.append(TEXT_46);
    }
    stringBuffer.append(TEXT_47);
    stringBuffer.append(genClass.getClassName());
    stringBuffer.append(genClass.getTypeParameters().trim());
    stringBuffer.append(genClass.getClassExtends());
    stringBuffer.append(genClass.getClassImplements());
    } else {
    stringBuffer.append(TEXT_48);
    stringBuffer.append(genClass.getInterfaceName());
    stringBuffer.append(genClass.getTypeParameters().trim());
    stringBuffer.append(genClass.getInterfaceExtends());
    }
    stringBuffer.append(TEXT_49);
    if (genModel.hasCopyrightField()) {
    stringBuffer.append(TEXT_50);
    stringBuffer.append(publicStaticFinalFlag);
    stringBuffer.append(genModel.getImportedName("java.lang.String"));
    stringBuffer.append(TEXT_51);
    stringBuffer.append(genModel.getCopyrightFieldLiteral());
    stringBuffer.append(TEXT_52);
    stringBuffer.append(genModel.getNonNLS());
    stringBuffer.append(TEXT_53);
    }
    if (isImplementation && genModel.getDriverNumber() != null) {
    stringBuffer.append(TEXT_54);
    stringBuffer.append(genModel.getImportedName("java.lang.String"));
    stringBuffer.append(TEXT_55);
    stringBuffer.append(genModel.getDriverNumber());
    stringBuffer.append(TEXT_56);
    stringBuffer.append(genModel.getNonNLS());
    stringBuffer.append(TEXT_57);
    }
    if (isImplementation && genClass.isJavaIOSerializable()) {
    stringBuffer.append(TEXT_58);
    }
    if (isImplementation && genModel.isVirtualDelegation()) { String eVirtualValuesField = genClass.getEVirtualValuesField();
    if (eVirtualValuesField != null) {
    stringBuffer.append(TEXT_59);
    if (isGWT) {
    stringBuffer.append(TEXT_60);
    stringBuffer.append(genModel.getImportedName("com.google.gwt.user.client.rpc.GwtTransient"));
    }
    stringBuffer.append(TEXT_61);
    stringBuffer.append(eVirtualValuesField);
    stringBuffer.append(TEXT_62);
    }
    { List<String> eVirtualIndexBitFields = genClass.getEVirtualIndexBitFields(new ArrayList<String>());
    if (!eVirtualIndexBitFields.isEmpty()) {
    for (String eVirtualIndexBitField : eVirtualIndexBitFields) {
    stringBuffer.append(TEXT_63);
    if (isGWT) {
    stringBuffer.append(TEXT_64);
    stringBuffer.append(genModel.getImportedName("com.google.gwt.user.client.rpc.GwtTransient"));
    }
    stringBuffer.append(TEXT_65);
    stringBuffer.append(eVirtualIndexBitField);
    stringBuffer.append(TEXT_66);
    }
    }
    }
    }
    if (isImplementation && genClass.isModelRoot() && genModel.isBooleanFlagsEnabled() && genModel.getBooleanFlagsReservedBits() == -1) {
    stringBuffer.append(TEXT_67);
    if (isGWT) {
    stringBuffer.append(TEXT_68);
    stringBuffer.append(genModel.getImportedName("com.google.gwt.user.client.rpc.GwtTransient"));
    }
    stringBuffer.append(TEXT_69);
    stringBuffer.append(genModel.getBooleanFlagsField());
    stringBuffer.append(TEXT_70);
    }
    if (isImplementation && !genModel.isReflectiveDelegation()) {
    for (GenFeature genFeature : genClass.getReifiedGenFeatures()) {
    if (genModel.isArrayAccessors() && genFeature.isListType() && !genFeature.isFeatureMapType() && !genFeature.isMapType()) { String rawListItemType = genFeature.getRawListItemType(genClass); int index = rawListItemType.indexOf('['); String head = rawListItemType; String tail = ""; if (index != -1) { head = rawListItemType.substring(0, index); tail = rawListItemType.substring(index); } 
    stringBuffer.append(TEXT_71);
    stringBuffer.append(genFeature.getGetArrayAccessor());
    stringBuffer.append(TEXT_72);
    stringBuffer.append(genFeature.getFormattedName());
    stringBuffer.append(TEXT_73);
    stringBuffer.append(genFeature.getGetArrayAccessor());
    stringBuffer.append(TEXT_74);
    if (genFeature.hasAPITags()) {
    stringBuffer.append(TEXT_75);
    stringBuffer.append(genFeature.getAPITags(genModel.getIndentation(stringBuffer)));
    }
    stringBuffer.append(TEXT_76);
    if (isJDK50 && genFeature.hasAPIDeprecatedTag()) {
    stringBuffer.append(TEXT_77);
    }
    if (genFeature.getQualifiedListItemType(genClass).contains("<") || genFeature.getArrayItemType(genClass).contains("<")) {
    stringBuffer.append(TEXT_78);
    }
    stringBuffer.append(TEXT_79);
    stringBuffer.append(rawListItemType);
    stringBuffer.append(TEXT_80);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_81);
    stringBuffer.append(head);
    stringBuffer.append(TEXT_82);
    stringBuffer.append(tail);
    stringBuffer.append(TEXT_83);
    }
    }
    for (GenFeature genFeature : genClass.getDeclaredFieldGenFeatures()) {
    if (genFeature.hasSettingDelegate()) {
    stringBuffer.append(TEXT_84);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_85);
    stringBuffer.append(genFeature.getFormattedName());
    stringBuffer.append(TEXT_86);
    stringBuffer.append(genFeature.getFeatureKind());
    stringBuffer.append(TEXT_87);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_88);
    if (genFeature.hasAPITags()) {
    stringBuffer.append(TEXT_89);
    stringBuffer.append(genFeature.getAPITags(genModel.getIndentation(stringBuffer)));
    }
    stringBuffer.append(TEXT_90);
    if (isGWT) {
    stringBuffer.append(TEXT_91);
    stringBuffer.append(genModel.getImportedName("com.google.gwt.user.client.rpc.GwtTransient"));
    }
    if (isJDK50 && genFeature.hasAPIDeprecatedTag()) {
    stringBuffer.append(TEXT_92);
    }
    stringBuffer.append(TEXT_93);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.EStructuralFeature"));
    stringBuffer.append(TEXT_94);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_95);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.EStructuralFeature"));
    stringBuffer.append(TEXT_96);
    stringBuffer.append(genFeature.getQualifiedFeatureAccessor());
    stringBuffer.append(TEXT_97);
    } else if (genFeature.isListType() || genFeature.isReferenceType()) {
    if (genClass.isField(genFeature)) {
    stringBuffer.append(TEXT_98);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_99);
    stringBuffer.append(genFeature.getFormattedName());
    stringBuffer.append(TEXT_100);
    stringBuffer.append(genFeature.getFeatureKind());
    stringBuffer.append(TEXT_101);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_102);
    if (genFeature.hasAPITags()) {
    stringBuffer.append(TEXT_103);
    stringBuffer.append(genFeature.getAPITags(genModel.getIndentation(stringBuffer)));
    }
    stringBuffer.append(TEXT_104);
    if (isGWT) {
    stringBuffer.append(TEXT_105);
    stringBuffer.append(genModel.getImportedName("com.google.gwt.user.client.rpc.GwtTransient"));
    }
    if (isJDK50 && genFeature.hasAPIDeprecatedTag()) {
    stringBuffer.append(TEXT_106);
    }
    stringBuffer.append(TEXT_107);
    stringBuffer.append(genFeature.getImportedInternalType(genClass));
    stringBuffer.append(TEXT_108);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_109);
    }
    if (genModel.isArrayAccessors() && genFeature.isListType() && !genFeature.isFeatureMapType() && !genFeature.isMapType()) { String rawListItemType = genFeature.getRawListItemType(genClass); int index = rawListItemType.indexOf('['); String head = rawListItemType; String tail = ""; if (index != -1) { head = rawListItemType.substring(0, index); tail = rawListItemType.substring(index); } 
    stringBuffer.append(TEXT_110);
    stringBuffer.append(genFeature.getGetArrayAccessor());
    stringBuffer.append(TEXT_111);
    stringBuffer.append(genFeature.getFormattedName());
    stringBuffer.append(TEXT_112);
    stringBuffer.append(genFeature.getGetArrayAccessor());
    stringBuffer.append(TEXT_113);
    if (genFeature.hasAPITags()) {
    stringBuffer.append(TEXT_114);
    stringBuffer.append(genFeature.getAPITags(genModel.getIndentation(stringBuffer)));
    }
    stringBuffer.append(TEXT_115);
    if (genFeature.getQualifiedListItemType(genClass).contains("<") || genFeature.getArrayItemType(genClass).contains("<")) {
    stringBuffer.append(TEXT_116);
    }
    if (isJDK50 && genFeature.hasAPIDeprecatedTag()) {
    stringBuffer.append(TEXT_117);
    }
    stringBuffer.append(TEXT_118);
    stringBuffer.append(rawListItemType);
    stringBuffer.append(TEXT_119);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_120);
    stringBuffer.append(head);
    stringBuffer.append(TEXT_121);
    stringBuffer.append(tail);
    stringBuffer.append(TEXT_122);
    }
    } else {
    if (genFeature.hasEDefault() && (!genFeature.isVolatile() || !genModel.isReflectiveDelegation() && (!genFeature.hasDelegateFeature() || !genFeature.isUnsettable()))) { String staticDefaultValue = genFeature.getStaticDefaultValue();
    stringBuffer.append(TEXT_123);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_124);
    stringBuffer.append(genFeature.getFormattedName());
    stringBuffer.append(TEXT_125);
    stringBuffer.append(genFeature.getFeatureKind());
    stringBuffer.append(TEXT_126);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_127);
    if (genFeature.hasAPITags()) {
    stringBuffer.append(TEXT_128);
    stringBuffer.append(genFeature.getAPITags(genModel.getIndentation(stringBuffer)));
    }
    stringBuffer.append(TEXT_129);
    if (genModel.useGenerics() && genFeature.isListDataType() && genFeature.isSetDefaultValue()) {
    stringBuffer.append(TEXT_130);
    }
    if (isJDK50 && genFeature.hasAPIDeprecatedTag()) {
    stringBuffer.append(TEXT_131);
    }
    stringBuffer.append(TEXT_132);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_133);
    stringBuffer.append(genFeature.getEDefault());
    if ("".equals(staticDefaultValue)) {
    stringBuffer.append(TEXT_134);
    stringBuffer.append(genFeature.getEcoreFeature().getDefaultValueLiteral());
    stringBuffer.append(TEXT_135);
    } else {
    stringBuffer.append(TEXT_136);
    stringBuffer.append(staticDefaultValue);
    stringBuffer.append(TEXT_137);
    stringBuffer.append(genModel.getNonNLS(staticDefaultValue));
    }
    stringBuffer.append(TEXT_138);
    }
    if (genClass.isField(genFeature)) {
    if (genClass.isFlag(genFeature)) { int flagIndex = genClass.getFlagIndex(genFeature);
    if (flagIndex > 31 && flagIndex % 32 == 0) {
    stringBuffer.append(TEXT_139);
    if (isGWT) {
    stringBuffer.append(TEXT_140);
    stringBuffer.append(genModel.getImportedName("com.google.gwt.user.client.rpc.GwtTransient"));
    }
    stringBuffer.append(TEXT_141);
    stringBuffer.append(genClass.getFlagsField(genFeature));
    stringBuffer.append(TEXT_142);
    }
    if (genFeature.isEnumType()) {
    stringBuffer.append(TEXT_143);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_144);
    stringBuffer.append(genFeature.getFormattedName());
    stringBuffer.append(TEXT_145);
    stringBuffer.append(genFeature.getFeatureKind());
    stringBuffer.append(TEXT_146);
    if (genFeature.hasAPITags()) {
    stringBuffer.append(TEXT_147);
    stringBuffer.append(genFeature.getAPITags(genModel.getIndentation(stringBuffer)));
    }
    stringBuffer.append(TEXT_148);
    if (isJDK50 && genFeature.hasAPIDeprecatedTag()) {
    stringBuffer.append(TEXT_149);
    }
    stringBuffer.append(TEXT_150);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_151);
    stringBuffer.append(flagIndex % 32);
    stringBuffer.append(TEXT_152);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_153);
    stringBuffer.append(genFeature.getFormattedName());
    stringBuffer.append(TEXT_154);
    stringBuffer.append(genFeature.getFeatureKind());
    stringBuffer.append(TEXT_155);
    if (genFeature.hasAPITags()) {
    stringBuffer.append(TEXT_156);
    stringBuffer.append(genFeature.getAPITags(genModel.getIndentation(stringBuffer)));
    }
    stringBuffer.append(TEXT_157);
    if (isJDK50 && genFeature.hasAPIDeprecatedTag()) {
    stringBuffer.append(TEXT_158);
    }
    stringBuffer.append(TEXT_159);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_160);
    if (isJDK50) {
    stringBuffer.append(genFeature.getEDefault());
    stringBuffer.append(TEXT_161);
    } else {
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_162);
    stringBuffer.append(genFeature.getEDefault());
    stringBuffer.append(TEXT_163);
    }
    stringBuffer.append(TEXT_164);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_165);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_166);
    stringBuffer.append(genFeature.getTypeGenClassifier().getFormattedName());
    stringBuffer.append(TEXT_167);
    if (genFeature.hasAPITags()) {
    stringBuffer.append(TEXT_168);
    stringBuffer.append(genFeature.getAPITags(genModel.getIndentation(stringBuffer)));
    }
    stringBuffer.append(TEXT_169);
    if (isJDK50 && genFeature.hasAPIDeprecatedTag()) {
    stringBuffer.append(TEXT_170);
    }
    stringBuffer.append(TEXT_171);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_172);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_173);
    if (isJDK50) {
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_174);
    } else {
    stringBuffer.append(TEXT_175);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_176);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_177);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_178);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_179);
    }
    stringBuffer.append(TEXT_180);
    }
    stringBuffer.append(TEXT_181);
    stringBuffer.append(genClass.getFlagSize(genFeature) > 1 ? "s" : "");
    stringBuffer.append(TEXT_182);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_183);
    stringBuffer.append(genFeature.getFormattedName());
    stringBuffer.append(TEXT_184);
    stringBuffer.append(genFeature.getFeatureKind());
    stringBuffer.append(TEXT_185);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_186);
    if (genFeature.hasAPITags()) {
    stringBuffer.append(TEXT_187);
    stringBuffer.append(genFeature.getAPITags(genModel.getIndentation(stringBuffer)));
    }
    stringBuffer.append(TEXT_188);
    if (isJDK50 && genFeature.hasAPIDeprecatedTag()) {
    stringBuffer.append(TEXT_189);
    }
    stringBuffer.append(TEXT_190);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_191);
    stringBuffer.append(genClass.getFlagMask(genFeature));
    stringBuffer.append(TEXT_192);
    if (genFeature.isEnumType()) {
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_193);
    } else {
    stringBuffer.append(flagIndex % 32);
    }
    stringBuffer.append(TEXT_194);
    } else {
    stringBuffer.append(TEXT_195);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_196);
    stringBuffer.append(genFeature.getFormattedName());
    stringBuffer.append(TEXT_197);
    stringBuffer.append(genFeature.getFeatureKind());
    stringBuffer.append(TEXT_198);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_199);
    if (genFeature.hasAPITags()) {
    stringBuffer.append(TEXT_200);
    stringBuffer.append(genFeature.getAPITags(genModel.getIndentation(stringBuffer)));
    }
    stringBuffer.append(TEXT_201);
    if (isGWT) {
    stringBuffer.append(TEXT_202);
    stringBuffer.append(genModel.getImportedName("com.google.gwt.user.client.rpc.GwtTransient"));
    }
    if (isJDK50 && genFeature.hasAPIDeprecatedTag()) {
    stringBuffer.append(TEXT_203);
    }
    stringBuffer.append(TEXT_204);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_205);
    stringBuffer.append(genFeature.getSafeName());
    if (genFeature.hasEDefault()) {
    stringBuffer.append(TEXT_206);
    stringBuffer.append(genFeature.getEDefault());
    }
    stringBuffer.append(TEXT_207);
    }
    }
    }
    if (genClass.isESetField(genFeature)) {
    if (genClass.isESetFlag(genFeature)) { int flagIndex = genClass.getESetFlagIndex(genFeature);
    if (flagIndex > 31 && flagIndex % 32 == 0) {
    stringBuffer.append(TEXT_208);
    if (isGWT) {
    stringBuffer.append(TEXT_209);
    stringBuffer.append(genModel.getImportedName("com.google.gwt.user.client.rpc.GwtTransient"));
    }
    stringBuffer.append(TEXT_210);
    stringBuffer.append(genClass.getESetFlagsField(genFeature));
    stringBuffer.append(TEXT_211);
    }
    stringBuffer.append(TEXT_212);
    stringBuffer.append(genFeature.getFormattedName());
    stringBuffer.append(TEXT_213);
    stringBuffer.append(genFeature.getFeatureKind());
    stringBuffer.append(TEXT_214);
    if (genFeature.hasAPITags()) {
    stringBuffer.append(TEXT_215);
    stringBuffer.append(genFeature.getAPITags(genModel.getIndentation(stringBuffer)));
    }
    stringBuffer.append(TEXT_216);
    if (genFeature.hasAPITags()) {
    stringBuffer.append(TEXT_217);
    stringBuffer.append(genFeature.getAPITags(genModel.getIndentation(stringBuffer)));
    }
    stringBuffer.append(TEXT_218);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_219);
    stringBuffer.append(flagIndex % 32 );
    stringBuffer.append(TEXT_220);
    } else {
    stringBuffer.append(TEXT_221);
    stringBuffer.append(genFeature.getFormattedName());
    stringBuffer.append(TEXT_222);
    stringBuffer.append(genFeature.getFeatureKind());
    stringBuffer.append(TEXT_223);
    if (genFeature.hasAPITags()) {
    stringBuffer.append(TEXT_224);
    stringBuffer.append(genFeature.getAPITags(genModel.getIndentation(stringBuffer)));
    }
    stringBuffer.append(TEXT_225);
    if (isGWT) {
    stringBuffer.append(TEXT_226);
    stringBuffer.append(genModel.getImportedName("com.google.gwt.user.client.rpc.GwtTransient"));
    }
    if (isJDK50 && genFeature.hasAPIDeprecatedTag()) {
    stringBuffer.append(TEXT_227);
    }
    stringBuffer.append(TEXT_228);
    stringBuffer.append(genFeature.getUncapName());
    stringBuffer.append(TEXT_229);
    }
    }
    //Class/declaredFieldGenFeature.override.javajetinc
    }
    }
    if (isImplementation && genClass.hasOffsetCorrection() && !genClass.getImplementedGenFeatures().isEmpty()) {
    stringBuffer.append(TEXT_230);
    stringBuffer.append(genClass.getOffsetCorrectionField(null));
    stringBuffer.append(TEXT_231);
    stringBuffer.append(genClass.getQualifiedClassifierAccessor());
    stringBuffer.append(TEXT_232);
    stringBuffer.append(genClass.getImplementedGenFeatures().get(0).getQualifiedFeatureAccessor());
    stringBuffer.append(TEXT_233);
    stringBuffer.append(genClass.getQualifiedFeatureID(genClass.getImplementedGenFeatures().get(0)));
    stringBuffer.append(TEXT_234);
    }
    if (isImplementation && !genModel.isReflectiveDelegation()) {
    for (GenFeature genFeature : genClass.getImplementedGenFeatures()) { GenFeature reverseFeature = genFeature.getReverse();
    if (reverseFeature != null && reverseFeature.getGenClass().hasOffsetCorrection()) {
    stringBuffer.append(TEXT_235);
    if (genFeature.hasAPITags()) {
    stringBuffer.append(TEXT_236);
    stringBuffer.append(genFeature.getAPITags(genModel.getIndentation(stringBuffer)));
    }
    stringBuffer.append(TEXT_237);
    if (isJDK50 && genFeature.hasAPIDeprecatedTag()) {
    stringBuffer.append(TEXT_238);
    }
    stringBuffer.append(TEXT_239);
    stringBuffer.append(genClass.getOffsetCorrectionField(genFeature));
    stringBuffer.append(TEXT_240);
    stringBuffer.append(reverseFeature.getGenClass().getQualifiedClassifierAccessor());
    stringBuffer.append(TEXT_241);
    stringBuffer.append(reverseFeature.getQualifiedFeatureAccessor());
    stringBuffer.append(TEXT_242);
    stringBuffer.append(reverseFeature.getGenClass().getQualifiedFeatureID(reverseFeature));
    stringBuffer.append(TEXT_243);
    }
    }
    }
    if (genModel.isOperationReflection() && isImplementation && genClass.hasOffsetCorrection() && !genClass.getImplementedGenOperations().isEmpty()) {
    stringBuffer.append(TEXT_244);
    stringBuffer.append(genClass.getQualifiedClassifierAccessor());
    stringBuffer.append(TEXT_245);
    stringBuffer.append(genClass.getImplementedGenOperations().get(0).getQualifiedOperationAccessor());
    stringBuffer.append(TEXT_246);
    stringBuffer.append(genClass.getQualifiedOperationID(genClass.getImplementedGenOperations().get(0)));
    stringBuffer.append(TEXT_247);
    }
    if (isImplementation) {
    stringBuffer.append(TEXT_248);
    if (genModel.isPublicConstructors()) {
    stringBuffer.append(TEXT_249);
    } else {
    stringBuffer.append(TEXT_250);
    }
    stringBuffer.append(TEXT_251);
    stringBuffer.append(genClass.getClassName());
    stringBuffer.append(TEXT_252);
    for (GenFeature genFeature : genClass.getFlagGenFeaturesWithDefault()) {
    stringBuffer.append(TEXT_253);
    stringBuffer.append(genClass.getFlagsField(genFeature));
    stringBuffer.append(TEXT_254);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_255);
    if (!genFeature.isBooleanType()) {
    stringBuffer.append(TEXT_256);
    }
    stringBuffer.append(TEXT_257);
    }
    stringBuffer.append(TEXT_258);
    if (genModel.useClassOverrideAnnotation()) {
    stringBuffer.append(TEXT_259);
    }
    stringBuffer.append(TEXT_260);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.EClass"));
    stringBuffer.append(TEXT_261);
    stringBuffer.append(genClass.getQualifiedClassifierAccessor());
    stringBuffer.append(TEXT_262);
    }
    if (isImplementation && (genModel.getFeatureDelegation() == GenDelegationKind.REFLECTIVE_LITERAL || genModel.isDynamicDelegation()) && (genClass.getClassExtendsGenClass() == null || (genClass.getClassExtendsGenClass().getGenModel().getFeatureDelegation() != GenDelegationKind.REFLECTIVE_LITERAL && !genClass.getClassExtendsGenClass().getGenModel().isDynamicDelegation()))) {
    if (genClass.hasStaticFeatures()) {
    stringBuffer.append(TEXT_263);
    stringBuffer.append(genClass.getClassExtendsGenClass() == null ? 0 : genClass.getClassExtendsGenClass().getAllGenFeatures().size());
    stringBuffer.append(TEXT_264);
    }
    stringBuffer.append(TEXT_265);
    if (genModel.useClassOverrideAnnotation()) {
    stringBuffer.append(TEXT_266);
    }
    stringBuffer.append(TEXT_267);
    stringBuffer.append(genClass.getClassExtendsGenClass() == null ? "0" : genClass.hasStaticFeatures() ? "ESTATIC_FEATURE_COUNT" : Integer.toString(genClass.getClassExtendsGenClass().getAllGenFeatures().size()));
    stringBuffer.append(TEXT_268);
    }
    //Class/reflectiveDelegation.override.javajetinc
    if (isImplementation) {
    new Runnable() { public void run() { GenClass classExtendsGenClass = genClass.getClassExtendsGenClass(); List<GenFeature> classExtendsAllGenFeatures = classExtendsGenClass == null? Collections.<GenFeature>emptyList() : classExtendsGenClass.getAllGenFeatures();
    for (GenFeature genFeature : genClass.getReifiedGenFeatures()) {
    if (genModel.isArrayAccessors() && genFeature.isListType() && !genFeature.isFeatureMapType() && !genFeature.isMapType()) { String arrayElementType = genFeature.getArrayItemType(genClass);
    stringBuffer.append(TEXT_269);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_270);
    if (genFeature.hasAPITags()) {
    stringBuffer.append(TEXT_271);
    stringBuffer.append(genFeature.getAPITags(genModel.getIndentation(stringBuffer)));
    }
    stringBuffer.append(TEXT_272);
    if (genModel.useGenerics() && CodeGenUtil.isUncheckedCast(arrayElementType)) {
    stringBuffer.append(TEXT_273);
    }
    if (isJDK50 && genFeature.hasAPIDeprecatedTag()) {
    stringBuffer.append(TEXT_274);
    }
    if (classExtendsAllGenFeatures.contains(genFeature)) {
    stringBuffer.append(TEXT_275);
    }
    stringBuffer.append(TEXT_276);
    stringBuffer.append(arrayElementType);
    stringBuffer.append(TEXT_277);
    stringBuffer.append(genFeature.getGetArrayAccessor());
    stringBuffer.append(TEXT_278);
    if (genFeature.isVolatile()) {
    stringBuffer.append(TEXT_279);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.util.BasicEList"));
    stringBuffer.append(genFeature.getListTemplateArguments(genClass));
    stringBuffer.append(TEXT_280);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.util.BasicEList"));
    stringBuffer.append(genFeature.getListTemplateArguments(genClass));
    stringBuffer.append(TEXT_281);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_282);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_283);
    } else {
    stringBuffer.append(TEXT_284);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_285);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_286);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_287);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.util.BasicEList"));
    stringBuffer.append(genFeature.getListTemplateArguments(genClass));
    stringBuffer.append(TEXT_288);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.util.BasicEList"));
    stringBuffer.append(genFeature.getListTemplateArguments(genClass));
    stringBuffer.append(TEXT_289);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_290);
    }
    stringBuffer.append(TEXT_291);
    stringBuffer.append(arrayElementType);
    stringBuffer.append(TEXT_292);
    }
    if (genFeature.isGet() && genFeature.isListType()) {
    stringBuffer.append(TEXT_293);
    if (genFeature.hasAPITags()) {
    stringBuffer.append(TEXT_294);
    stringBuffer.append(genFeature.getAPITags(genModel.getIndentation(stringBuffer)));
    }
    stringBuffer.append(TEXT_295);
    if (isJDK50) { //Class/getGenFeature.annotations.insert.javajetinc
    }
    if (genFeature.isListType() && genModel.isVirtualDelegation()) {
    stringBuffer.append(TEXT_296);
    }
    if (isJDK50 && genFeature.hasAPIDeprecatedTag()) {
    stringBuffer.append(TEXT_297);
    }
    if (classExtendsAllGenFeatures.contains(genFeature)) {
    stringBuffer.append(TEXT_298);
    }
    stringBuffer.append(TEXT_299);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_300);
    stringBuffer.append(genFeature.getGetAccessor());
    if (genClass.hasCollidingGetAccessorOperation(genFeature)) {
    stringBuffer.append(TEXT_301);
    }
    stringBuffer.append(TEXT_302);
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
    stringBuffer.append(TEXT_307);
    }
    stringBuffer.append(TEXT_308);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_309);
    if (genModel.isVirtualDelegation()) {
    stringBuffer.append(TEXT_310);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(positiveOffsetCorrection);
    stringBuffer.append(TEXT_311);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_312);
    stringBuffer.append(genClass.getListConstructor(genFeature));
    stringBuffer.append(TEXT_313);
    } else {
    stringBuffer.append(TEXT_314);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_315);
    stringBuffer.append(genClass.getListConstructor(genFeature));
    stringBuffer.append(TEXT_316);
    }
    stringBuffer.append(TEXT_317);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(genFeature.isMapType() && genFeature.isEffectiveSuppressEMFTypes() ? ".map()" : "");
    stringBuffer.append(TEXT_318);
    }
    if (!genModel.isReflectiveDelegation() && genFeature.isBasicSet()) {
    stringBuffer.append(TEXT_319);
    if (genFeature.hasAPITags()) {
    stringBuffer.append(TEXT_320);
    stringBuffer.append(genFeature.getAPITags(genModel.getIndentation(stringBuffer)));
    }
    stringBuffer.append(TEXT_321);
    if (isJDK50) { //Class/basicSetGenFeature.annotations.insert.javajetinc
    }
    if (isJDK50 && genFeature.hasAPIDeprecatedTag()) {
    stringBuffer.append(TEXT_322);
    }
    if (classExtendsAllGenFeatures.contains(genFeature)) {
    stringBuffer.append(TEXT_323);
    }
    stringBuffer.append(TEXT_324);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.notify.NotificationChain"));
    stringBuffer.append(TEXT_325);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_326);
    stringBuffer.append(genFeature.getImportedInternalType(genClass));
    stringBuffer.append(TEXT_327);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_328);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.notify.NotificationChain"));
    stringBuffer.append(TEXT_329);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_330);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_331);
    }
    if (genFeature.isSet() && !(!genModel.isReflectiveDelegation() && genFeature.isBasicSet())) {
    stringBuffer.append(TEXT_332);
    if (genFeature.hasAPITags()) {
    stringBuffer.append(TEXT_333);
    stringBuffer.append(genFeature.getAPITags(genModel.getIndentation(stringBuffer)));
    }
    stringBuffer.append(TEXT_334);
    if (isJDK50) { //Class/setGenFeature.annotations.insert.javajetinc
    }
    { GenOperation setAccessorOperation = genClass.getSetAccessorOperation(genFeature);
    if (isJDK50 && genFeature.hasAPIDeprecatedTag()) {
    stringBuffer.append(TEXT_335);
    }
    if (classExtendsAllGenFeatures.contains(genFeature)) {
    stringBuffer.append(TEXT_336);
    }
    stringBuffer.append(TEXT_337);
    stringBuffer.append(genFeature.getAccessorName());
    if (genClass.hasCollidingSetAccessorOperation(genFeature)) {
    stringBuffer.append(TEXT_338);
    }
    stringBuffer.append(TEXT_339);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_340);
    stringBuffer.append(setAccessorOperation == null ? "new" + genFeature.getCapName() : setAccessorOperation.getGenParameters().get(0).getName());
    stringBuffer.append(TEXT_341);
    stringBuffer.append(genFeature.getAccessorName());
    if (genClass.hasCollidingSetAccessorOperation(genFeature)) {
    stringBuffer.append(TEXT_342);
    }
    stringBuffer.append(TEXT_343);
    stringBuffer.append(setAccessorOperation == null ? "new" + genFeature.getCapName() : setAccessorOperation.getGenParameters().get(0).getName());
    stringBuffer.append(TEXT_344);
    }
    }
    //Class/genFeatureReified.override.javajetinc
    }}}.run();}
    new Runnable() { public void run() {
    for (GenFeature genFeature : (isImplementation ? genClass.getImplementedGenFeatures() : genClass.getDeclaredGenFeatures())) {
    if (genModel.isArrayAccessors() && genFeature.isListType() && !genFeature.isFeatureMapType() && !genFeature.isMapType()) { String arrayElementType = genFeature.getArrayItemType(genClass);
    stringBuffer.append(TEXT_345);
    if (genFeature.hasAPITags()) {
    stringBuffer.append(TEXT_346);
    stringBuffer.append(genFeature.getAPITags(genModel.getIndentation(stringBuffer)));
    }
    stringBuffer.append(TEXT_347);
    if (!isImplementation) {
    stringBuffer.append(TEXT_348);
    stringBuffer.append(arrayElementType);
    stringBuffer.append(TEXT_349);
    stringBuffer.append(genFeature.getGetArrayAccessor());
    stringBuffer.append(TEXT_350);
    } else {
    if (genModel.useGenerics() && CodeGenUtil.isUncheckedCast(arrayElementType)) {
    stringBuffer.append(TEXT_351);
    }
    if (isJDK50 && genFeature.hasAPIDeprecatedTag()) {
    stringBuffer.append(TEXT_352);
    }
    stringBuffer.append(TEXT_353);
    stringBuffer.append(arrayElementType);
    stringBuffer.append(TEXT_354);
    stringBuffer.append(genFeature.getGetArrayAccessor());
    stringBuffer.append(TEXT_355);
    if (genFeature.isVolatile()) {
    stringBuffer.append(TEXT_356);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.util.BasicEList"));
    stringBuffer.append(genFeature.getListTemplateArguments(genClass));
    stringBuffer.append(TEXT_357);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.util.BasicEList"));
    stringBuffer.append(genFeature.getListTemplateArguments(genClass));
    stringBuffer.append(TEXT_358);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_359);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_360);
    } else {
    stringBuffer.append(TEXT_361);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_362);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_363);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_364);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.util.BasicEList"));
    stringBuffer.append(genFeature.getListTemplateArguments(genClass));
    stringBuffer.append(TEXT_365);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.util.BasicEList"));
    stringBuffer.append(genFeature.getListTemplateArguments(genClass));
    stringBuffer.append(TEXT_366);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_367);
    }
    stringBuffer.append(TEXT_368);
    stringBuffer.append(arrayElementType);
    stringBuffer.append(TEXT_369);
    }
    stringBuffer.append(TEXT_370);
    if (genFeature.hasAPITags()) {
    stringBuffer.append(TEXT_371);
    stringBuffer.append(genFeature.getAPITags(genModel.getIndentation(stringBuffer)));
    }
    stringBuffer.append(TEXT_372);
    if (isJDK50 && genFeature.hasAPIDeprecatedTag()) {
    stringBuffer.append(TEXT_373);
    }
    if (!isImplementation) {
    stringBuffer.append(TEXT_374);
    stringBuffer.append(genFeature.getListItemType(genClass));
    stringBuffer.append(TEXT_375);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_376);
    } else {
    stringBuffer.append(TEXT_377);
    stringBuffer.append(genFeature.getListItemType(genClass));
    stringBuffer.append(TEXT_378);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_379);
    if (!genModel.useGenerics()) {
    stringBuffer.append(TEXT_380);
    stringBuffer.append(genFeature.getListItemType(genClass));
    stringBuffer.append(TEXT_381);
    }
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_382);
    }
    stringBuffer.append(TEXT_383);
    if (genFeature.hasAPITags()) {
    stringBuffer.append(TEXT_384);
    stringBuffer.append(genFeature.getAPITags(genModel.getIndentation(stringBuffer)));
    }
    stringBuffer.append(TEXT_385);
    if (isJDK50 && genFeature.hasAPIDeprecatedTag()) {
    stringBuffer.append(TEXT_386);
    }
    if (!isImplementation) {
    stringBuffer.append(TEXT_387);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_388);
    } else {
    stringBuffer.append(TEXT_389);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_390);
    if (genFeature.isVolatile()) {
    stringBuffer.append(TEXT_391);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_392);
    } else {
    stringBuffer.append(TEXT_393);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_394);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_395);
    }
    stringBuffer.append(TEXT_396);
    }
    stringBuffer.append(TEXT_397);
    if (genFeature.hasAPITags()) {
    stringBuffer.append(TEXT_398);
    stringBuffer.append(genFeature.getAPITags(genModel.getIndentation(stringBuffer)));
    }
    stringBuffer.append(TEXT_399);
    if (isJDK50 && genFeature.hasAPIDeprecatedTag()) {
    stringBuffer.append(TEXT_400);
    }
    if (!isImplementation) {
    stringBuffer.append(TEXT_401);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_402);
    stringBuffer.append(genFeature.getListItemType(genClass));
    stringBuffer.append(TEXT_403);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_404);
    } else {
    stringBuffer.append(TEXT_405);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_406);
    stringBuffer.append(genFeature.getListItemType(genClass));
    stringBuffer.append(TEXT_407);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_408);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.util.BasicEList"));
    stringBuffer.append(genFeature.getListTemplateArguments(genClass));
    stringBuffer.append(TEXT_409);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_410);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_411);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_412);
    }
    stringBuffer.append(TEXT_413);
    if (genFeature.hasAPITags()) {
    stringBuffer.append(TEXT_414);
    stringBuffer.append(genFeature.getAPITags(genModel.getIndentation(stringBuffer)));
    }
    stringBuffer.append(TEXT_415);
    if (isJDK50 && genFeature.hasAPIDeprecatedTag()) {
    stringBuffer.append(TEXT_416);
    }
    if (!isImplementation) {
    stringBuffer.append(TEXT_417);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_418);
    stringBuffer.append(genFeature.getListItemType(genClass));
    stringBuffer.append(TEXT_419);
    } else {
    stringBuffer.append(TEXT_420);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_421);
    stringBuffer.append(genFeature.getListItemType(genClass));
    stringBuffer.append(TEXT_422);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_423);
    }
    }
    if (genFeature.isGet() && (isImplementation || !genFeature.isSuppressedGetVisibility())) {
    if (isInterface) {
    stringBuffer.append(TEXT_424);
    stringBuffer.append(genFeature.getFormattedName());
    stringBuffer.append(TEXT_425);
    stringBuffer.append(genFeature.getFeatureKind());
    stringBuffer.append(TEXT_426);
    if (genFeature.isListType() && genFeature.getEcoreFeature().getEGenericType().getETypeParameter() == null) {
    if (genFeature.isMapType()) { GenFeature keyFeature = genFeature.getMapEntryTypeGenClass().getMapEntryKeyFeature(); GenFeature valueFeature = genFeature.getMapEntryTypeGenClass().getMapEntryValueFeature(); 
    stringBuffer.append(TEXT_427);
    if (keyFeature.isListType()) {
    stringBuffer.append(TEXT_428);
    stringBuffer.append(keyFeature.getQualifiedListItemType(genClass));
    stringBuffer.append(TEXT_429);
    } else {
    stringBuffer.append(TEXT_430);
    stringBuffer.append(keyFeature.getType(genClass));
    stringBuffer.append(TEXT_431);
    }
    stringBuffer.append(TEXT_432);
    if (valueFeature.isListType()) {
    stringBuffer.append(TEXT_433);
    stringBuffer.append(valueFeature.getQualifiedListItemType(genClass));
    stringBuffer.append(TEXT_434);
    } else {
    stringBuffer.append(TEXT_435);
    stringBuffer.append(valueFeature.getType(genClass));
    stringBuffer.append(TEXT_436);
    }
    stringBuffer.append(TEXT_437);
    } else if (!genFeature.isWrappedFeatureMapType() && !(genModel.isSuppressEMFMetaData() && "org.eclipse.emf.ecore.EObject".equals(genFeature.getQualifiedListItemType(genClass)))) {
String typeName = genFeature.getQualifiedListItemType(genClass); String head = typeName; String tail = ""; int index = typeName.indexOf('<'); if (index == -1) { index = typeName.indexOf('['); } 
if (index != -1) { head = typeName.substring(0, index); tail = "<code>" + CodeGenUtil.xmlEscapeEncode(typeName.substring(index)) + "</code>"; }

    stringBuffer.append(TEXT_438);
    stringBuffer.append(head);
    stringBuffer.append(TEXT_439);
    stringBuffer.append(tail);
    stringBuffer.append(TEXT_440);
    }
    } else if (genFeature.isSetDefaultValue()) {
    stringBuffer.append(TEXT_441);
    stringBuffer.append(genFeature.getDefaultValue());
    stringBuffer.append(TEXT_442);
    }
    if (genFeature.getTypeGenEnum() != null) {
    stringBuffer.append(TEXT_443);
    stringBuffer.append(genFeature.getTypeGenEnum().getQualifiedName());
    stringBuffer.append(TEXT_444);
    }
    if (genFeature.isBidirectional() && !genFeature.getReverse().getGenClass().isMapEntry()) { GenFeature reverseGenFeature = genFeature.getReverse(); 
    if (!reverseGenFeature.isSuppressedGetVisibility()) {
    stringBuffer.append(TEXT_445);
    stringBuffer.append(reverseGenFeature.getGenClass().getQualifiedInterfaceName());
    stringBuffer.append(TEXT_446);
    stringBuffer.append(reverseGenFeature.getGetAccessor());
    stringBuffer.append(TEXT_447);
    stringBuffer.append(reverseGenFeature.getFormattedName());
    stringBuffer.append(TEXT_448);
    }
    }
    stringBuffer.append(TEXT_449);
    if (!genFeature.hasDocumentation()) {
    stringBuffer.append(TEXT_450);
    stringBuffer.append(genFeature.getFormattedName());
    stringBuffer.append(TEXT_451);
    stringBuffer.append(genFeature.getFeatureKind());
    stringBuffer.append(TEXT_452);
    }
    stringBuffer.append(TEXT_453);
    if (genFeature.hasDocumentation()) {
    stringBuffer.append(TEXT_454);
    stringBuffer.append(genFeature.getDocumentation(genModel.getIndentation(stringBuffer)));
    stringBuffer.append(TEXT_455);
    }
    stringBuffer.append(TEXT_456);
    stringBuffer.append(genFeature.getFormattedName());
    stringBuffer.append(TEXT_457);
    stringBuffer.append(genFeature.getFeatureKind());
    stringBuffer.append(TEXT_458);
    if (genFeature.getTypeGenEnum() != null) {
    stringBuffer.append(TEXT_459);
    stringBuffer.append(genFeature.getTypeGenEnum().getQualifiedName());
    }
    if (genFeature.isUnsettable()) {
    if (!genFeature.isSuppressedIsSetVisibility()) {
    stringBuffer.append(TEXT_460);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_461);
    }
    if (genFeature.isChangeable() && !genFeature.isSuppressedUnsetVisibility()) {
    stringBuffer.append(TEXT_462);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_463);
    }
    }
    if (genFeature.isChangeable() && !genFeature.isListType() && !genFeature.isSuppressedSetVisibility()) {
    stringBuffer.append(TEXT_464);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_465);
    stringBuffer.append(genFeature.getRawImportedBoundType());
    stringBuffer.append(TEXT_466);
    }
    if (!genModel.isSuppressEMFMetaData()) {
    stringBuffer.append(TEXT_467);
    stringBuffer.append(genPackage.getQualifiedPackageInterfaceName());
    stringBuffer.append(TEXT_468);
    stringBuffer.append(genFeature.getFeatureAccessorName());
    stringBuffer.append(TEXT_469);
    }
    if (genFeature.isBidirectional() && !genFeature.getReverse().getGenClass().isMapEntry()) { GenFeature reverseGenFeature = genFeature.getReverse(); 
    if (!reverseGenFeature.isSuppressedGetVisibility()) {
    stringBuffer.append(TEXT_470);
    stringBuffer.append(reverseGenFeature.getGenClass().getQualifiedInterfaceName());
    stringBuffer.append(TEXT_471);
    stringBuffer.append(reverseGenFeature.getGetAccessor());
    }
    }
    if (!genModel.isSuppressEMFModelTags()) { boolean first = true; for (StringTokenizer stringTokenizer = new StringTokenizer(genFeature.getModelInfo(), "\n\r"); stringTokenizer.hasMoreTokens(); ) { String modelInfo = stringTokenizer.nextToken(); if (first) { first = false;
    stringBuffer.append(TEXT_472);
    stringBuffer.append(modelInfo);
    } else {
    stringBuffer.append(TEXT_473);
    stringBuffer.append(modelInfo);
    }} if (first) {
    stringBuffer.append(TEXT_474);
    }}
    stringBuffer.append(TEXT_475);
    //Class/getGenFeature.javadoc.override.javajetinc
    } else {
    stringBuffer.append(TEXT_476);
    if (genFeature.hasAPITags()) {
    stringBuffer.append(TEXT_477);
    stringBuffer.append(genFeature.getAPITags(genModel.getIndentation(stringBuffer)));
    }
    stringBuffer.append(TEXT_478);
    if (isJDK50) { //Class/getGenFeature.annotations.insert.javajetinc
    }
    }
    if (isJDK50 && genFeature.hasAPIDeprecatedTag()) {
    stringBuffer.append(TEXT_479);
    }
    if (!isImplementation) {
    stringBuffer.append(TEXT_480);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_481);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_482);
    } else {
    if (genModel.useGenerics() && ((genFeature.isContainer() || genFeature.isResolveProxies()) && !genFeature.isListType() && genFeature.isUncheckedCast(genClass) || genFeature.isListType() && !genFeature.isFeatureMapType() && (genModel.isReflectiveDelegation() || genModel.isVirtualDelegation() || (genModel.isDynamicDelegation() && !genFeature.isVolatile())) || genFeature.isListDataType() && genFeature.hasDelegateFeature() || genFeature.isListType() && genFeature.hasSettingDelegate())) {
    stringBuffer.append(TEXT_483);
    }
    stringBuffer.append(TEXT_484);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_485);
    stringBuffer.append(genFeature.getGetAccessor());
    if (genClass.hasCollidingGetAccessorOperation(genFeature)) {
    stringBuffer.append(TEXT_486);
    }
    stringBuffer.append(TEXT_487);
    if (genModel.isDynamicDelegation() && !genFeature.isVolatile()) {
    stringBuffer.append(TEXT_488);
    if (!isJDK50 && genFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_489);
    }
    stringBuffer.append(TEXT_490);
    stringBuffer.append(genFeature.getObjectType(genClass));
    stringBuffer.append(TEXT_491);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    if (genClass.hasStaticFeatures()){
    stringBuffer.append(TEXT_492);
    }
    stringBuffer.append(TEXT_493);
    stringBuffer.append(genFeature.getQualifiedFeatureAccessor());
    stringBuffer.append(TEXT_494);
    stringBuffer.append(!genFeature.isEffectiveSuppressEMFTypes());
    stringBuffer.append(TEXT_495);
    if (!isJDK50 && genFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_496);
    stringBuffer.append(genFeature.getPrimitiveValueFunction());
    stringBuffer.append(TEXT_497);
    }
    stringBuffer.append(TEXT_498);
    } else if (genModel.isReflectiveDelegation()) {
    if (genFeature.hasGetterBody()) {
    stringBuffer.append(TEXT_499);
    stringBuffer.append(genFeature.getGetterBody(genModel.getIndentation(stringBuffer)));
    } else {
    stringBuffer.append(TEXT_500);
    if (!isJDK50 && genFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_501);
    }
    stringBuffer.append(TEXT_502);
    stringBuffer.append(genFeature.getObjectType(genClass));
    stringBuffer.append(TEXT_503);
    stringBuffer.append(genFeature.getQualifiedFeatureAccessor());
    stringBuffer.append(TEXT_504);
    if (!isJDK50 && genFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_505);
    stringBuffer.append(genFeature.getPrimitiveValueFunction());
    stringBuffer.append(TEXT_506);
    }
    stringBuffer.append(TEXT_507);
    }
    } else if (genFeature.hasSettingDelegate()) {
    stringBuffer.append(TEXT_508);
    if (!isJDK50 && genFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_509);
    }
    stringBuffer.append(TEXT_510);
    stringBuffer.append(genFeature.getObjectType(genClass));
    stringBuffer.append(TEXT_511);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_512);
    if (!isJDK50 && genFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_513);
    stringBuffer.append(genFeature.getPrimitiveValueFunction());
    stringBuffer.append(TEXT_514);
    }
    stringBuffer.append(TEXT_515);
    } else if (!genFeature.isVolatile()) {
    if (genFeature.isListType()) {
    if (genModel.isVirtualDelegation()) {
    stringBuffer.append(TEXT_516);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_517);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_518);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_519);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(positiveOffsetCorrection);
    stringBuffer.append(TEXT_520);
    }
    stringBuffer.append(TEXT_521);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_522);
    if (genModel.isVirtualDelegation()) {
    stringBuffer.append(TEXT_523);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(positiveOffsetCorrection);
    stringBuffer.append(TEXT_524);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_525);
    stringBuffer.append(genClass.getListConstructor(genFeature));
    stringBuffer.append(TEXT_526);
    } else {
    stringBuffer.append(TEXT_527);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_528);
    stringBuffer.append(genClass.getListConstructor(genFeature));
    stringBuffer.append(TEXT_529);
    }
    stringBuffer.append(TEXT_530);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(genFeature.isMapType() && genFeature.isEffectiveSuppressEMFTypes() ? ".map()" : "");
    stringBuffer.append(TEXT_531);
    } else if (genFeature.isContainer()) {
    stringBuffer.append(TEXT_532);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(positiveOffsetCorrection);
    stringBuffer.append(TEXT_533);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_534);
    if (genFeature.isResolveProxies()) {
    stringBuffer.append(TEXT_535);
    } else {
    stringBuffer.append(TEXT_536);
    }
    stringBuffer.append(TEXT_537);
    } else {
    if (genFeature.isResolveProxies()) {
    if (genModel.isVirtualDelegation()) {
    stringBuffer.append(TEXT_538);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_539);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_540);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_541);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(positiveOffsetCorrection);
    if (genFeature.hasEDefault()) {
    stringBuffer.append(TEXT_542);
    stringBuffer.append(genFeature.getEDefault());
    }
    stringBuffer.append(TEXT_543);
    }
    stringBuffer.append(TEXT_544);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_545);
    stringBuffer.append(genFeature.getSafeNameAsEObject());
    stringBuffer.append(TEXT_546);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.InternalEObject"));
    stringBuffer.append(TEXT_547);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_548);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.InternalEObject"));
    stringBuffer.append(TEXT_549);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_550);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_551);
    stringBuffer.append(genFeature.getNonEObjectInternalTypeCast(genClass));
    stringBuffer.append(TEXT_552);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_553);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_554);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_555);
    if (genFeature.isEffectiveContains()) {
    stringBuffer.append(TEXT_556);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.InternalEObject"));
    stringBuffer.append(TEXT_557);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_558);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.InternalEObject"));
    stringBuffer.append(TEXT_559);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_560);
    if (!genFeature.isBidirectional()) {
    stringBuffer.append(TEXT_561);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.notify.NotificationChain"));
    stringBuffer.append(TEXT_562);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_563);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(negativeOffsetCorrection);
    stringBuffer.append(TEXT_564);
    } else { GenFeature reverseFeature = genFeature.getReverse(); GenClass targetClass = reverseFeature.getGenClass(); String reverseOffsetCorrection = targetClass.hasOffsetCorrection() ? " + " + genClass.getOffsetCorrectionField(genFeature) : "";
    stringBuffer.append(TEXT_565);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.notify.NotificationChain"));
    stringBuffer.append(TEXT_566);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_567);
    stringBuffer.append(targetClass.getQualifiedFeatureID(reverseFeature));
    stringBuffer.append(reverseOffsetCorrection);
    stringBuffer.append(TEXT_568);
    stringBuffer.append(targetClass.getRawImportedInterfaceName());
    stringBuffer.append(TEXT_569);
    }
    stringBuffer.append(TEXT_570);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_571);
    if (!genFeature.isBidirectional()) {
    stringBuffer.append(TEXT_572);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_573);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(negativeOffsetCorrection);
    stringBuffer.append(TEXT_574);
    } else { GenFeature reverseFeature = genFeature.getReverse(); GenClass targetClass = reverseFeature.getGenClass(); String reverseOffsetCorrection = targetClass.hasOffsetCorrection() ? " + " + genClass.getOffsetCorrectionField(genFeature) : "";
    stringBuffer.append(TEXT_575);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_576);
    stringBuffer.append(targetClass.getQualifiedFeatureID(reverseFeature));
    stringBuffer.append(reverseOffsetCorrection);
    stringBuffer.append(TEXT_577);
    stringBuffer.append(targetClass.getRawImportedInterfaceName());
    stringBuffer.append(TEXT_578);
    }
    stringBuffer.append(TEXT_579);
    } else if (genModel.isVirtualDelegation()) {
    stringBuffer.append(TEXT_580);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(positiveOffsetCorrection);
    stringBuffer.append(TEXT_581);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_582);
    }
    if (!genModel.isSuppressNotification()) {
    stringBuffer.append(TEXT_583);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.impl.ENotificationImpl"));
    stringBuffer.append(TEXT_584);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.notify.Notification"));
    stringBuffer.append(TEXT_585);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(positiveOffsetCorrection);
    stringBuffer.append(TEXT_586);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_587);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_588);
    }
    stringBuffer.append(TEXT_589);
    }
    if (!genFeature.isResolveProxies() && genModel.isVirtualDelegation() && !genFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_590);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_591);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(positiveOffsetCorrection);
    if (genFeature.hasEDefault()) {
    stringBuffer.append(TEXT_592);
    stringBuffer.append(genFeature.getEDefault());
    }
    stringBuffer.append(TEXT_593);
    } else if (genClass.isFlag(genFeature)) {
    if (genFeature.isBooleanType()) {
    stringBuffer.append(TEXT_594);
    stringBuffer.append(genClass.getFlagsField(genFeature));
    stringBuffer.append(TEXT_595);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_596);
    } else {
    stringBuffer.append(TEXT_597);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_598);
    stringBuffer.append(genClass.getFlagsField(genFeature));
    stringBuffer.append(TEXT_599);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_600);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_601);
    }
    } else {
    stringBuffer.append(TEXT_602);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_603);
    }
    }
    } else {//volatile
    if (genFeature.isResolveProxies() && !genFeature.isListType()) {
    stringBuffer.append(TEXT_604);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_605);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_606);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_607);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_608);
    stringBuffer.append(genFeature.getSafeNameAsEObject());
    stringBuffer.append(TEXT_609);
    stringBuffer.append(genFeature.getNonEObjectInternalTypeCast(genClass));
    stringBuffer.append(TEXT_610);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.InternalEObject"));
    stringBuffer.append(TEXT_611);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_612);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_613);
    } else if (genFeature.hasDelegateFeature()) { GenFeature delegateFeature = genFeature.getDelegateFeature();
    if (genFeature.isFeatureMapType()) {
    String featureMapEntryTemplateArgument = isJDK50 ? "<" + genModel.getImportedName("org.eclipse.emf.ecore.util.FeatureMap") + ".Entry>" : "";
    if (delegateFeature.isWrappedFeatureMapType()) {
    stringBuffer.append(TEXT_614);
    stringBuffer.append(genFeature.getImportedEffectiveFeatureMapWrapperClass());
    stringBuffer.append(TEXT_615);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.FeatureMap"));
    stringBuffer.append(TEXT_616);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.FeatureMap"));
    stringBuffer.append(TEXT_617);
    stringBuffer.append(delegateFeature.getAccessorName());
    stringBuffer.append(TEXT_618);
    stringBuffer.append(featureMapEntryTemplateArgument);
    stringBuffer.append(TEXT_619);
    stringBuffer.append(genFeature.getQualifiedFeatureAccessor());
    stringBuffer.append(TEXT_620);
    } else {
    stringBuffer.append(TEXT_621);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.FeatureMap"));
    stringBuffer.append(TEXT_622);
    stringBuffer.append(delegateFeature.getAccessorName());
    stringBuffer.append(TEXT_623);
    stringBuffer.append(featureMapEntryTemplateArgument);
    stringBuffer.append(TEXT_624);
    stringBuffer.append(genFeature.getQualifiedFeatureAccessor());
    stringBuffer.append(TEXT_625);
    }
    } else if (genFeature.isListType()) {
    if (delegateFeature.isWrappedFeatureMapType()) {
    stringBuffer.append(TEXT_626);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.FeatureMap"));
    stringBuffer.append(TEXT_627);
    stringBuffer.append(delegateFeature.getAccessorName());
    stringBuffer.append(TEXT_628);
    stringBuffer.append(genFeature.getQualifiedFeatureAccessor());
    stringBuffer.append(TEXT_629);
    } else {
    stringBuffer.append(TEXT_630);
    stringBuffer.append(delegateFeature.getAccessorName());
    stringBuffer.append(TEXT_631);
    stringBuffer.append(genFeature.getQualifiedFeatureAccessor());
    stringBuffer.append(TEXT_632);
    }
    } else {
    if (delegateFeature.isWrappedFeatureMapType()) {
    stringBuffer.append(TEXT_633);
    if (!isJDK50 && genFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_634);
    }
    if (genFeature.getTypeGenDataType() == null || !genFeature.getTypeGenDataType().isObjectType()) {
    stringBuffer.append(TEXT_635);
    stringBuffer.append(genFeature.getObjectType(genClass));
    stringBuffer.append(TEXT_636);
    }
    stringBuffer.append(TEXT_637);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.FeatureMap"));
    stringBuffer.append(TEXT_638);
    stringBuffer.append(delegateFeature.getAccessorName());
    stringBuffer.append(TEXT_639);
    stringBuffer.append(genFeature.getQualifiedFeatureAccessor());
    stringBuffer.append(TEXT_640);
    if (!isJDK50 && genFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_641);
    stringBuffer.append(genFeature.getPrimitiveValueFunction());
    stringBuffer.append(TEXT_642);
    }
    stringBuffer.append(TEXT_643);
    } else {
    stringBuffer.append(TEXT_644);
    if (!isJDK50 && genFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_645);
    }
    if (genFeature.getTypeGenDataType() == null || !genFeature.getTypeGenDataType().isObjectType()) {
    stringBuffer.append(TEXT_646);
    stringBuffer.append(genFeature.getObjectType(genClass));
    stringBuffer.append(TEXT_647);
    }
    stringBuffer.append(TEXT_648);
    stringBuffer.append(delegateFeature.getAccessorName());
    stringBuffer.append(TEXT_649);
    stringBuffer.append(genFeature.getQualifiedFeatureAccessor());
    stringBuffer.append(TEXT_650);
    if (!isJDK50 && genFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_651);
    stringBuffer.append(genFeature.getPrimitiveValueFunction());
    stringBuffer.append(TEXT_652);
    }
    stringBuffer.append(TEXT_653);
    }
    }
    } else if (genClass.getGetAccessorOperation(genFeature) != null) {
    stringBuffer.append(TEXT_654);
    stringBuffer.append(genClass.getGetAccessorOperation(genFeature).getBody(genModel.getIndentation(stringBuffer)));
    } else if (genFeature.hasGetterBody()) {
    stringBuffer.append(TEXT_655);
    stringBuffer.append(genFeature.getGetterBody(genModel.getIndentation(stringBuffer)));
    } else {
    stringBuffer.append(TEXT_656);
    stringBuffer.append(genFeature.getFormattedName());
    stringBuffer.append(TEXT_657);
    stringBuffer.append(genFeature.getFeatureKind());
    stringBuffer.append(TEXT_658);
    if (genFeature.isListType()) {
    stringBuffer.append(TEXT_659);
    if (genFeature.isMapType()) {
    stringBuffer.append(TEXT_660);
    } else if (genFeature.isFeatureMapType()) {
    stringBuffer.append(TEXT_661);
    } else {
    stringBuffer.append(TEXT_662);
    }
    stringBuffer.append(TEXT_663);
    }
    stringBuffer.append(TEXT_664);
    //Class/getGenFeature.todo.override.javajetinc
    }
    }
    stringBuffer.append(TEXT_665);
    }
    //Class/getGenFeature.override.javajetinc
    }
    if (isImplementation && !genModel.isReflectiveDelegation() && genFeature.isBasicGet()) {
    stringBuffer.append(TEXT_666);
    if (genFeature.hasAPITags()) {
    stringBuffer.append(TEXT_667);
    stringBuffer.append(genFeature.getAPITags(genModel.getIndentation(stringBuffer)));
    }
    stringBuffer.append(TEXT_668);
    if (isJDK50) { //Class/basicGetGenFeature.annotations.insert.javajetinc
    }
    if (isJDK50 && genFeature.hasAPIDeprecatedTag()) {
    stringBuffer.append(TEXT_669);
    }
    stringBuffer.append(TEXT_670);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_671);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_672);
    if (genModel.isDynamicDelegation() && !genFeature.isVolatile()) {
    stringBuffer.append(TEXT_673);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_674);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    if (genClass.hasStaticFeatures()){
    stringBuffer.append(TEXT_675);
    }
    stringBuffer.append(TEXT_676);
    stringBuffer.append(genFeature.getQualifiedFeatureAccessor());
    stringBuffer.append(TEXT_677);
    stringBuffer.append(!genFeature.isEffectiveSuppressEMFTypes());
    stringBuffer.append(TEXT_678);
    } else if (genFeature.hasSettingDelegate()) {
    stringBuffer.append(TEXT_679);
    if (!isJDK50 && genFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_680);
    }
    stringBuffer.append(TEXT_681);
    stringBuffer.append(genFeature.getObjectType(genClass));
    stringBuffer.append(TEXT_682);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_683);
    if (!isJDK50 && genFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_684);
    stringBuffer.append(genFeature.getPrimitiveValueFunction());
    stringBuffer.append(TEXT_685);
    }
    stringBuffer.append(TEXT_686);
    } else if (genFeature.isContainer()) {
    stringBuffer.append(TEXT_687);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(positiveOffsetCorrection);
    stringBuffer.append(TEXT_688);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_689);
    } else if (!genFeature.isVolatile()) {
    if (genModel.isVirtualDelegation()) {
    stringBuffer.append(TEXT_690);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_691);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(positiveOffsetCorrection);
    stringBuffer.append(TEXT_692);
    } else {
    stringBuffer.append(TEXT_693);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_694);
    }
    } else if (genFeature.hasDelegateFeature()) { GenFeature delegateFeature = genFeature.getDelegateFeature();
    if (delegateFeature.isWrappedFeatureMapType()) {
    stringBuffer.append(TEXT_695);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_696);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.FeatureMap"));
    stringBuffer.append(TEXT_697);
    stringBuffer.append(delegateFeature.getAccessorName());
    stringBuffer.append(TEXT_698);
    stringBuffer.append(genFeature.getQualifiedFeatureAccessor());
    stringBuffer.append(TEXT_699);
    } else {
    stringBuffer.append(TEXT_700);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_701);
    stringBuffer.append(delegateFeature.getAccessorName());
    stringBuffer.append(TEXT_702);
    stringBuffer.append(genFeature.getQualifiedFeatureAccessor());
    stringBuffer.append(TEXT_703);
    }
    } else if (genFeature.hasGetterBody()) {
    stringBuffer.append(TEXT_704);
    stringBuffer.append(genFeature.getGetterBody(genModel.getIndentation(stringBuffer)));
    } else {
    stringBuffer.append(TEXT_705);
    stringBuffer.append(genFeature.getFormattedName());
    stringBuffer.append(TEXT_706);
    stringBuffer.append(genFeature.getFeatureKind());
    stringBuffer.append(TEXT_707);
    //Class/basicGetGenFeature.todo.override.javajetinc
    }
    stringBuffer.append(TEXT_708);
    //Class/basicGetGenFeature.override.javajetinc
    }
    if (isImplementation && !genModel.isReflectiveDelegation() && genFeature.isBasicSet()) {
    stringBuffer.append(TEXT_709);
    if (genFeature.hasAPITags()) {
    stringBuffer.append(TEXT_710);
    stringBuffer.append(genFeature.getAPITags(genModel.getIndentation(stringBuffer)));
    }
    stringBuffer.append(TEXT_711);
    if (isJDK50) { //Class/basicSetGenFeature.annotations.insert.javajetinc
    }
    if (isJDK50 && genFeature.hasAPIDeprecatedTag()) {
    stringBuffer.append(TEXT_712);
    }
    stringBuffer.append(TEXT_713);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.notify.NotificationChain"));
    stringBuffer.append(TEXT_714);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_715);
    stringBuffer.append(genFeature.getImportedInternalType(genClass));
    stringBuffer.append(TEXT_716);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_717);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.notify.NotificationChain"));
    stringBuffer.append(TEXT_718);
    if (genFeature.isContainer()) {
    stringBuffer.append(TEXT_719);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.InternalEObject"));
    stringBuffer.append(TEXT_720);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_721);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(positiveOffsetCorrection);
    stringBuffer.append(TEXT_722);
    stringBuffer.append(TEXT_723);
    } else if (genModel.isDynamicDelegation()) {
    stringBuffer.append(TEXT_724);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.InternalEObject"));
    stringBuffer.append(TEXT_725);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_726);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(positiveOffsetCorrection);
    stringBuffer.append(TEXT_727);
    stringBuffer.append(TEXT_728);
    } else if (!genFeature.isVolatile()) {
    if (genModel.isVirtualDelegation()) {
    stringBuffer.append(TEXT_729);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_730);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(positiveOffsetCorrection);
    stringBuffer.append(TEXT_731);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_732);
    } else {
    stringBuffer.append(TEXT_733);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_734);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_735);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_736);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_737);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_738);
    }
    if (genFeature.isUnsettable()) {
    if (genModel.isVirtualDelegation()) {
    if (!genModel.isSuppressNotification()) {
    stringBuffer.append(TEXT_739);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_740);
    }
    } else if (genClass.isESetFlag(genFeature)) {
    stringBuffer.append(TEXT_741);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_742);
    stringBuffer.append(genClass.getESetFlagsField(genFeature));
    stringBuffer.append(TEXT_743);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_744);
    if (!genModel.isSuppressNotification()) {
    stringBuffer.append(TEXT_745);
    stringBuffer.append(genClass.getESetFlagsField(genFeature));
    stringBuffer.append(TEXT_746);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_747);
    }
    } else {
    if (!genModel.isSuppressNotification()) {
    stringBuffer.append(TEXT_748);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_749);
    stringBuffer.append(genFeature.getUncapName());
    stringBuffer.append(TEXT_750);
    }
    stringBuffer.append(TEXT_751);
    stringBuffer.append(genFeature.getUncapName());
    stringBuffer.append(TEXT_752);
    }
    }
    if (!genModel.isSuppressNotification()) {
    stringBuffer.append(TEXT_753);
    if (genFeature.isUnsettable()) {
    stringBuffer.append(TEXT_754);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.impl.ENotificationImpl"));
    stringBuffer.append(TEXT_755);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.impl.ENotificationImpl"));
    stringBuffer.append(TEXT_756);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.notify.Notification"));
    stringBuffer.append(TEXT_757);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(positiveOffsetCorrection);
    stringBuffer.append(TEXT_758);
    if (genModel.isVirtualDelegation()) {
    stringBuffer.append(TEXT_759);
    stringBuffer.append(genFeature.getCapName());
    } else {
    stringBuffer.append(TEXT_760);
    stringBuffer.append(genFeature.getCapName());
    }
    stringBuffer.append(TEXT_761);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_762);
    if (genModel.isVirtualDelegation()) {
    stringBuffer.append(TEXT_763);
    } else {
    stringBuffer.append(TEXT_764);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_765);
    }
    stringBuffer.append(TEXT_766);
    } else {
    stringBuffer.append(TEXT_767);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.impl.ENotificationImpl"));
    stringBuffer.append(TEXT_768);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.impl.ENotificationImpl"));
    stringBuffer.append(TEXT_769);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.notify.Notification"));
    stringBuffer.append(TEXT_770);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(positiveOffsetCorrection);
    stringBuffer.append(TEXT_771);
    if (genModel.isVirtualDelegation()) {
    stringBuffer.append(TEXT_772);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_773);
    stringBuffer.append(genFeature.getCapName());
    } else {
    stringBuffer.append(TEXT_774);
    stringBuffer.append(genFeature.getCapName());
    }
    stringBuffer.append(TEXT_775);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_776);
    }
    stringBuffer.append(TEXT_777);
    }
    stringBuffer.append(TEXT_778);
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
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_784);
    } else {
    stringBuffer.append(TEXT_785);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.FeatureMap"));
    stringBuffer.append(TEXT_786);
    stringBuffer.append(delegateFeature.getAccessorName());
    stringBuffer.append(TEXT_787);
    stringBuffer.append(genFeature.getQualifiedFeatureAccessor());
    stringBuffer.append(TEXT_788);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_789);
    }
    } else {
    stringBuffer.append(TEXT_790);
    stringBuffer.append(genFeature.getFormattedName());
    stringBuffer.append(TEXT_791);
    stringBuffer.append(genFeature.getFeatureKind());
    stringBuffer.append(TEXT_792);
    //Class/basicSetGenFeature.todo.override.javajetinc
    }
    stringBuffer.append(TEXT_793);
    //Class/basicSetGenFeature.override.javajetinc
    }
    if (genFeature.isSet() && (isImplementation || !genFeature.isSuppressedSetVisibility())) {
    if (isInterface) { 
    stringBuffer.append(TEXT_794);
    stringBuffer.append(genClass.getQualifiedInterfaceName());
    stringBuffer.append(TEXT_795);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_796);
    stringBuffer.append(genFeature.getFormattedName());
    stringBuffer.append(TEXT_797);
    stringBuffer.append(genFeature.getFeatureKind());
    stringBuffer.append(TEXT_798);
    stringBuffer.append(TEXT_799);
    stringBuffer.append(genFeature.getFormattedName());
    stringBuffer.append(TEXT_800);
    stringBuffer.append(genFeature.getFeatureKind());
    stringBuffer.append(TEXT_801);
    if (genFeature.isEnumType()) {
    stringBuffer.append(TEXT_802);
    stringBuffer.append(genFeature.getTypeGenEnum().getQualifiedName());
    }
    if (genFeature.isUnsettable()) {
    if (!genFeature.isSuppressedIsSetVisibility()) {
    stringBuffer.append(TEXT_803);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_804);
    }
    if (!genFeature.isSuppressedUnsetVisibility()) {
    stringBuffer.append(TEXT_805);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_806);
    }
    }
    stringBuffer.append(TEXT_807);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_808);
    if (genFeature.hasAPITags()) {
    stringBuffer.append(TEXT_809);
    stringBuffer.append(genFeature.getAPITags(genModel.getIndentation(stringBuffer)));
    }
    stringBuffer.append(TEXT_810);
    //Class/setGenFeature.javadoc.override.javajetinc
    } else {
    stringBuffer.append(TEXT_811);
    if (genFeature.hasAPITags()) {
    stringBuffer.append(TEXT_812);
    stringBuffer.append(genFeature.getAPITags(genModel.getIndentation(stringBuffer)));
    }
    stringBuffer.append(TEXT_813);
    if (isJDK50) { //Class/setGenFeature.annotations.insert.javajetinc
    }
    }
    if (isJDK50 && genFeature.hasAPIDeprecatedTag()) {
    stringBuffer.append(TEXT_814);
    }
    if (!isImplementation) { 
    stringBuffer.append(TEXT_815);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_816);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_817);
    } else { GenOperation setAccessorOperation = genClass.getSetAccessorOperation(genFeature);
    stringBuffer.append(TEXT_818);
    stringBuffer.append(genFeature.getAccessorName());
    if (genClass.hasCollidingSetAccessorOperation(genFeature)) {
    stringBuffer.append(TEXT_819);
    }
    stringBuffer.append(TEXT_820);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_821);
    stringBuffer.append(setAccessorOperation == null ? "new" + genFeature.getCapName() : setAccessorOperation.getGenParameters().get(0).getName());
    stringBuffer.append(TEXT_822);
    if (genModel.isDynamicDelegation() && !genFeature.isVolatile()) {
    stringBuffer.append(TEXT_823);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    if (genClass.hasStaticFeatures()){
    stringBuffer.append(TEXT_824);
    }
    stringBuffer.append(TEXT_825);
    stringBuffer.append(genFeature.getQualifiedFeatureAccessor());
    stringBuffer.append(TEXT_826);
    if (!isJDK50 && genFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_827);
    stringBuffer.append(genFeature.getObjectType(genClass));
    stringBuffer.append(TEXT_828);
    }
    stringBuffer.append(TEXT_829);
    stringBuffer.append(genFeature.getCapName());
    if (!isJDK50 && genFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_830);
    }
    stringBuffer.append(TEXT_831);
    } else if (genModel.isReflectiveDelegation()) {
    stringBuffer.append(TEXT_832);
    stringBuffer.append(genFeature.getQualifiedFeatureAccessor());
    stringBuffer.append(TEXT_833);
    if (!isJDK50 && genFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_834);
    stringBuffer.append(genFeature.getObjectType(genClass));
    stringBuffer.append(TEXT_835);
    }
    stringBuffer.append(TEXT_836);
    stringBuffer.append(genFeature.getCapName());
    if (!isJDK50 && genFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_837);
    }
    stringBuffer.append(TEXT_838);
    } else if (genFeature.hasSettingDelegate()) {
    stringBuffer.append(TEXT_839);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_840);
    if (!isJDK50 && genFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_841);
    stringBuffer.append(genFeature.getObjectType(genClass));
    stringBuffer.append(TEXT_842);
    }
    stringBuffer.append(TEXT_843);
    stringBuffer.append(genFeature.getCapName());
    if (!isJDK50 && genFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_844);
    }
    stringBuffer.append(TEXT_845);
    } else if (!genFeature.isVolatile()) {
    if (genFeature.isContainer()) { GenFeature reverseFeature = genFeature.getReverse(); GenClass targetClass = reverseFeature.getGenClass(); String reverseOffsetCorrection = targetClass.hasOffsetCorrection() ? " + " + genClass.getOffsetCorrectionField(genFeature) : "";
    stringBuffer.append(TEXT_846);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_847);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(positiveOffsetCorrection);
    stringBuffer.append(TEXT_848);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_849);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.EcoreUtil"));
    stringBuffer.append(TEXT_850);
    stringBuffer.append(genFeature.getEObjectCast());
    stringBuffer.append(TEXT_851);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_852);
    stringBuffer.append(genModel.getImportedName("java.lang.IllegalArgumentException"));
    stringBuffer.append(TEXT_853);
    stringBuffer.append(genModel.getNonNLS());
    stringBuffer.append(TEXT_854);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.notify.NotificationChain"));
    stringBuffer.append(TEXT_855);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_856);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.InternalEObject"));
    stringBuffer.append(TEXT_857);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_858);
    stringBuffer.append(targetClass.getQualifiedFeatureID(reverseFeature));
    stringBuffer.append(reverseOffsetCorrection);
    stringBuffer.append(TEXT_859);
    stringBuffer.append(targetClass.getRawImportedInterfaceName());
    stringBuffer.append(TEXT_860);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_861);
    stringBuffer.append(genFeature.getInternalTypeCast());
    stringBuffer.append(TEXT_862);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_863);
    if (!genModel.isSuppressNotification()) {
    stringBuffer.append(TEXT_864);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.impl.ENotificationImpl"));
    stringBuffer.append(TEXT_865);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.notify.Notification"));
    stringBuffer.append(TEXT_866);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(positiveOffsetCorrection);
    stringBuffer.append(TEXT_867);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_868);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_869);
    }
    } else if (genFeature.isBidirectional() || genFeature.isEffectiveContains()) {
    if (genModel.isVirtualDelegation()) {
    stringBuffer.append(TEXT_870);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_871);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_872);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_873);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(positiveOffsetCorrection);
    stringBuffer.append(TEXT_874);
    }
    stringBuffer.append(TEXT_875);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_876);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_877);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.notify.NotificationChain"));
    stringBuffer.append(TEXT_878);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_879);
    if (!genFeature.isBidirectional()) {
    stringBuffer.append(TEXT_880);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.InternalEObject"));
    stringBuffer.append(TEXT_881);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_882);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(negativeOffsetCorrection);
    stringBuffer.append(TEXT_883);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_884);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.InternalEObject"));
    stringBuffer.append(TEXT_885);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_886);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(negativeOffsetCorrection);
    stringBuffer.append(TEXT_887);
    } else { GenFeature reverseFeature = genFeature.getReverse(); GenClass targetClass = reverseFeature.getGenClass(); String reverseOffsetCorrection = targetClass.hasOffsetCorrection() ? " + " + genClass.getOffsetCorrectionField(genFeature) : "";
    stringBuffer.append(TEXT_888);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.InternalEObject"));
    stringBuffer.append(TEXT_889);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_890);
    stringBuffer.append(targetClass.getQualifiedFeatureID(reverseFeature));
    stringBuffer.append(reverseOffsetCorrection);
    stringBuffer.append(TEXT_891);
    stringBuffer.append(targetClass.getRawImportedInterfaceName());
    stringBuffer.append(TEXT_892);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_893);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.InternalEObject"));
    stringBuffer.append(TEXT_894);
    stringBuffer.append(genFeature.getCapName());
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
    stringBuffer.append(genFeature.getInternalTypeCast());
    stringBuffer.append(TEXT_900);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_901);
    if (genFeature.isUnsettable()) {
    stringBuffer.append(TEXT_902);
    if (genModel.isVirtualDelegation()) {
    stringBuffer.append(TEXT_903);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_904);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(positiveOffsetCorrection);
    stringBuffer.append(TEXT_905);
    } else if (genClass.isESetFlag(genFeature)) {
    if (!genModel.isSuppressNotification()) {
    stringBuffer.append(TEXT_906);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_907);
    stringBuffer.append(genClass.getESetFlagsField(genFeature));
    stringBuffer.append(TEXT_908);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_909);
    }
    stringBuffer.append(TEXT_910);
    stringBuffer.append(genClass.getESetFlagsField(genFeature));
    stringBuffer.append(TEXT_911);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_912);
    } else {
    if (!genModel.isSuppressNotification()) {
    stringBuffer.append(TEXT_913);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_914);
    stringBuffer.append(genFeature.getUncapName());
    stringBuffer.append(TEXT_915);
    }
    stringBuffer.append(TEXT_916);
    stringBuffer.append(genFeature.getUncapName());
    stringBuffer.append(TEXT_917);
    }
    if (!genModel.isSuppressNotification()) {
    stringBuffer.append(TEXT_918);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.impl.ENotificationImpl"));
    stringBuffer.append(TEXT_919);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.notify.Notification"));
    stringBuffer.append(TEXT_920);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(positiveOffsetCorrection);
    stringBuffer.append(TEXT_921);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_922);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_923);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_924);
    }
    stringBuffer.append(TEXT_925);
    } else {
    if (!genModel.isSuppressNotification()) {
    stringBuffer.append(TEXT_926);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.impl.ENotificationImpl"));
    stringBuffer.append(TEXT_927);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.notify.Notification"));
    stringBuffer.append(TEXT_928);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(positiveOffsetCorrection);
    stringBuffer.append(TEXT_929);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_930);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_931);
    }
    }
    } else {
    if (genClass.isFlag(genFeature)) {
    if (!genModel.isSuppressNotification()) {
    if (genFeature.isBooleanType()) {
    stringBuffer.append(TEXT_932);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_933);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_934);
    stringBuffer.append(genClass.getFlagsField(genFeature));
    stringBuffer.append(TEXT_935);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_936);
    } else {
    stringBuffer.append(TEXT_937);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_938);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_939);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_940);
    stringBuffer.append(genClass.getFlagsField(genFeature));
    stringBuffer.append(TEXT_941);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_942);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_943);
    }
    }
    if (genFeature.isBooleanType()) {
    stringBuffer.append(TEXT_944);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_945);
    stringBuffer.append(genClass.getFlagsField(genFeature));
    stringBuffer.append(TEXT_946);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_947);
    stringBuffer.append(genClass.getFlagsField(genFeature));
    stringBuffer.append(TEXT_948);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_949);
    } else {
    stringBuffer.append(TEXT_950);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_951);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_952);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_953);
    stringBuffer.append(genClass.getFlagsField(genFeature));
    stringBuffer.append(TEXT_954);
    stringBuffer.append(genClass.getFlagsField(genFeature));
    stringBuffer.append(TEXT_955);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_956);
    if (isJDK50) {
    stringBuffer.append(TEXT_957);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_958);
    } else {
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_959);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_960);
    }
    stringBuffer.append(TEXT_961);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_962);
    }
    } else {
    if (!genModel.isVirtualDelegation() || genFeature.isPrimitiveType()) {
    if (!genModel.isSuppressNotification()) {
    stringBuffer.append(TEXT_963);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_964);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_965);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_966);
    }
    }
    if (genFeature.isEnumType()) {
    if (genModel.isVirtualDelegation()) {
    stringBuffer.append(TEXT_967);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_968);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_969);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_970);
    stringBuffer.append(genFeature.getEDefault());
    stringBuffer.append(TEXT_971);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_972);
    } else {
    stringBuffer.append(TEXT_973);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_974);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_975);
    stringBuffer.append(genFeature.getEDefault());
    stringBuffer.append(TEXT_976);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_977);
    }
    } else {
    if (genModel.isVirtualDelegation() && !genFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_978);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_979);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_980);
    stringBuffer.append(genFeature.getInternalTypeCast());
    stringBuffer.append(TEXT_981);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_982);
    } else {
    stringBuffer.append(TEXT_983);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_984);
    stringBuffer.append(genFeature.getInternalTypeCast());
    stringBuffer.append(TEXT_985);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_986);
    }
    }
    if (genModel.isVirtualDelegation() && !genFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_987);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_988);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(positiveOffsetCorrection);
    stringBuffer.append(TEXT_989);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_990);
    }
    }
    if (genFeature.isUnsettable()) {
    if (genModel.isVirtualDelegation() && !genFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_991);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_992);
    } else if (genClass.isESetFlag(genFeature)) {
    if (!genModel.isSuppressNotification()) {
    stringBuffer.append(TEXT_993);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_994);
    stringBuffer.append(genClass.getESetFlagsField(genFeature));
    stringBuffer.append(TEXT_995);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_996);
    }
    stringBuffer.append(TEXT_997);
    stringBuffer.append(genClass.getESetFlagsField(genFeature));
    stringBuffer.append(TEXT_998);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_999);
    } else {
    if (!genModel.isSuppressNotification()) {
    stringBuffer.append(TEXT_1000);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_1001);
    stringBuffer.append(genFeature.getUncapName());
    stringBuffer.append(TEXT_1002);
    }
    stringBuffer.append(TEXT_1003);
    stringBuffer.append(genFeature.getUncapName());
    stringBuffer.append(TEXT_1004);
    }
    if (!genModel.isSuppressNotification()) {
    stringBuffer.append(TEXT_1005);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.impl.ENotificationImpl"));
    stringBuffer.append(TEXT_1006);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.notify.Notification"));
    stringBuffer.append(TEXT_1007);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(positiveOffsetCorrection);
    stringBuffer.append(TEXT_1008);
    if (genModel.isVirtualDelegation() && !genFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_1009);
    stringBuffer.append(genFeature.getEDefault());
    stringBuffer.append(TEXT_1010);
    stringBuffer.append(genFeature.getCapName());
    } else {
    stringBuffer.append(TEXT_1011);
    stringBuffer.append(genFeature.getCapName());
    }
    stringBuffer.append(TEXT_1012);
    if (genClass.isFlag(genFeature)) {
    stringBuffer.append(TEXT_1013);
    stringBuffer.append(genFeature.getCapName());
    } else {
    stringBuffer.append(genFeature.getSafeName());
    }
    stringBuffer.append(TEXT_1014);
    if (genModel.isVirtualDelegation() && !genFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_1015);
    } else {
    stringBuffer.append(TEXT_1016);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_1017);
    }
    stringBuffer.append(TEXT_1018);
    }
    } else {
    if (!genModel.isSuppressNotification()) {
    stringBuffer.append(TEXT_1019);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.impl.ENotificationImpl"));
    stringBuffer.append(TEXT_1020);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.notify.Notification"));
    stringBuffer.append(TEXT_1021);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(positiveOffsetCorrection);
    stringBuffer.append(TEXT_1022);
    if (genModel.isVirtualDelegation() && !genFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_1023);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_1024);
    stringBuffer.append(genFeature.getEDefault());
    stringBuffer.append(TEXT_1025);
    stringBuffer.append(genFeature.getCapName());
    } else {
    stringBuffer.append(TEXT_1026);
    stringBuffer.append(genFeature.getCapName());
    }
    stringBuffer.append(TEXT_1027);
    if (genClass.isFlag(genFeature)) {
    stringBuffer.append(TEXT_1028);
    stringBuffer.append(genFeature.getCapName());
    } else {
    stringBuffer.append(genFeature.getSafeName());
    }
    stringBuffer.append(TEXT_1029);
    }
    }
    }
    } else if (genFeature.hasDelegateFeature()) { GenFeature delegateFeature = genFeature.getDelegateFeature();
    if (delegateFeature.isWrappedFeatureMapType()) {
    stringBuffer.append(TEXT_1030);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.FeatureMap"));
    stringBuffer.append(TEXT_1031);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.FeatureMap"));
    stringBuffer.append(TEXT_1032);
    stringBuffer.append(delegateFeature.getAccessorName());
    stringBuffer.append(TEXT_1033);
    stringBuffer.append(genFeature.getQualifiedFeatureAccessor());
    stringBuffer.append(TEXT_1034);
    if (!isJDK50 && genFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_1035);
    stringBuffer.append(genFeature.getObjectType(genClass));
    stringBuffer.append(TEXT_1036);
    }
    stringBuffer.append(TEXT_1037);
    stringBuffer.append(genFeature.getCapName());
    if (!isJDK50 && genFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_1038);
    }
    stringBuffer.append(TEXT_1039);
    } else {
    stringBuffer.append(TEXT_1040);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.FeatureMap"));
    stringBuffer.append(TEXT_1041);
    stringBuffer.append(delegateFeature.getAccessorName());
    stringBuffer.append(TEXT_1042);
    stringBuffer.append(genFeature.getQualifiedFeatureAccessor());
    stringBuffer.append(TEXT_1043);
    if (!isJDK50 && genFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_1044);
    stringBuffer.append(genFeature.getObjectType(genClass));
    stringBuffer.append(TEXT_1045);
    }
    stringBuffer.append(TEXT_1046);
    stringBuffer.append(genFeature.getCapName());
    if (!isJDK50 && genFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_1047);
    }
    stringBuffer.append(TEXT_1048);
    }
    } else if (setAccessorOperation != null) {
    stringBuffer.append(TEXT_1049);
    stringBuffer.append(setAccessorOperation.getBody(genModel.getIndentation(stringBuffer)));
    } else {
    stringBuffer.append(TEXT_1050);
    stringBuffer.append(genFeature.getFormattedName());
    stringBuffer.append(TEXT_1051);
    stringBuffer.append(genFeature.getFeatureKind());
    stringBuffer.append(TEXT_1052);
    //Class/setGenFeature.todo.override.javajetinc
    }
    stringBuffer.append(TEXT_1053);
    }
    //Class/setGenFeature.override.javajetinc
    }
    if (isImplementation && !genModel.isReflectiveDelegation() && genFeature.isBasicUnset()) {
    stringBuffer.append(TEXT_1054);
    if (genFeature.hasAPITags()) {
    stringBuffer.append(TEXT_1055);
    stringBuffer.append(genFeature.getAPITags(genModel.getIndentation(stringBuffer)));
    }
    stringBuffer.append(TEXT_1056);
    if (isJDK50) { //Class/basicUnsetGenFeature.annotations.insert.javajetinc
    }
    if (isJDK50 && genFeature.hasAPIDeprecatedTag()) {
    stringBuffer.append(TEXT_1057);
    }
    stringBuffer.append(TEXT_1058);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.notify.NotificationChain"));
    stringBuffer.append(TEXT_1059);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_1060);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.notify.NotificationChain"));
    stringBuffer.append(TEXT_1061);
    if (genModel.isDynamicDelegation()) {
    stringBuffer.append(TEXT_1062);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.InternalEObject"));
    stringBuffer.append(TEXT_1063);
    if (genFeature.isResolveProxies()) {
    stringBuffer.append(TEXT_1064);
    stringBuffer.append(genFeature.getAccessorName());
    } else {
    stringBuffer.append(genFeature.getGetAccessor());
    }
    stringBuffer.append(TEXT_1065);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(positiveOffsetCorrection);
    stringBuffer.append(TEXT_1066);
    } else if (!genFeature.isVolatile()) {
    if (genModel.isVirtualDelegation()) {
    if (!genModel.isSuppressNotification()) {
    stringBuffer.append(TEXT_1067);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_1068);
    }
    stringBuffer.append(TEXT_1069);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(positiveOffsetCorrection);
    stringBuffer.append(TEXT_1070);
    } else {
    if (!genModel.isSuppressNotification()) {
    stringBuffer.append(TEXT_1071);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_1072);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_1073);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_1074);
    }
    stringBuffer.append(TEXT_1075);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_1076);
    }
    if (genModel.isVirtualDelegation()) {
    if (!genModel.isSuppressNotification()) {
    stringBuffer.append(TEXT_1077);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_1078);
    }
    } else if (genClass.isESetFlag(genFeature)) {
    if (!genModel.isSuppressNotification()) {
    stringBuffer.append(TEXT_1079);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_1080);
    stringBuffer.append(genClass.getESetFlagsField(genFeature));
    stringBuffer.append(TEXT_1081);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_1082);
    }
    stringBuffer.append(TEXT_1083);
    stringBuffer.append(genClass.getESetFlagsField(genFeature));
    stringBuffer.append(TEXT_1084);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_1085);
    } else {
    if (!genModel.isSuppressNotification()) {
    stringBuffer.append(TEXT_1086);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_1087);
    stringBuffer.append(genFeature.getUncapName());
    stringBuffer.append(TEXT_1088);
    }
    stringBuffer.append(TEXT_1089);
    stringBuffer.append(genFeature.getUncapName());
    stringBuffer.append(TEXT_1090);
    }
    if (!genModel.isSuppressNotification()) {
    stringBuffer.append(TEXT_1091);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.impl.ENotificationImpl"));
    stringBuffer.append(TEXT_1092);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.impl.ENotificationImpl"));
    stringBuffer.append(TEXT_1093);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.notify.Notification"));
    stringBuffer.append(TEXT_1094);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(positiveOffsetCorrection);
    stringBuffer.append(TEXT_1095);
    if (genModel.isVirtualDelegation()) {
    stringBuffer.append(TEXT_1096);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_1097);
    } else {
    stringBuffer.append(TEXT_1098);
    stringBuffer.append(genFeature.getCapName());
    }
    stringBuffer.append(TEXT_1099);
    if (genModel.isVirtualDelegation()) {
    stringBuffer.append(TEXT_1100);
    } else {
    stringBuffer.append(TEXT_1101);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_1102);
    }
    stringBuffer.append(TEXT_1103);
    }
    } else {
    stringBuffer.append(TEXT_1104);
    stringBuffer.append(genFeature.getFormattedName());
    stringBuffer.append(TEXT_1105);
    stringBuffer.append(genFeature.getFeatureKind());
    stringBuffer.append(TEXT_1106);
    //Class/basicUnsetGenFeature.todo.override.javajetinc
    }
    stringBuffer.append(TEXT_1107);
    //Class.basicUnsetGenFeature.override.javajetinc
    }
    if (genFeature.isUnset() && (isImplementation || !genFeature.isSuppressedUnsetVisibility())) {
    if (isInterface) {
    stringBuffer.append(TEXT_1108);
    stringBuffer.append(genClass.getQualifiedInterfaceName());
    stringBuffer.append(TEXT_1109);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_1110);
    stringBuffer.append(genFeature.getFormattedName());
    stringBuffer.append(TEXT_1111);
    stringBuffer.append(genFeature.getFeatureKind());
    stringBuffer.append(TEXT_1112);
    stringBuffer.append(TEXT_1113);
    if (!genFeature.isSuppressedIsSetVisibility()) {
    stringBuffer.append(TEXT_1114);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_1115);
    }
    stringBuffer.append(TEXT_1116);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_1117);
    if (!genFeature.isListType() && !genFeature.isSuppressedSetVisibility()) {
    stringBuffer.append(TEXT_1118);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_1119);
    stringBuffer.append(genFeature.getRawImportedBoundType());
    stringBuffer.append(TEXT_1120);
    }
    if (genFeature.hasAPITags()) {
    stringBuffer.append(TEXT_1121);
    stringBuffer.append(genFeature.getAPITags(genModel.getIndentation(stringBuffer)));
    }
    stringBuffer.append(TEXT_1122);
    //Class/unsetGenFeature.javadoc.override.javajetinc
    } else {
    stringBuffer.append(TEXT_1123);
    if (genFeature.hasAPITags()) {
    stringBuffer.append(TEXT_1124);
    stringBuffer.append(genFeature.getAPITags(genModel.getIndentation(stringBuffer)));
    }
    stringBuffer.append(TEXT_1125);
    if (isJDK50) { //Class/unsetGenFeature.annotations.insert.javajetinc
    }
    }
    if (isJDK50 && genFeature.hasAPIDeprecatedTag()) {
    stringBuffer.append(TEXT_1126);
    }
    if (!isImplementation) {
    stringBuffer.append(TEXT_1127);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_1128);
    } else {
    stringBuffer.append(TEXT_1129);
    stringBuffer.append(genFeature.getAccessorName());
    if (genClass.hasCollidingUnsetAccessorOperation(genFeature)) {
    stringBuffer.append(TEXT_1130);
    }
    stringBuffer.append(TEXT_1131);
    if (genModel.isDynamicDelegation() && !genFeature.isVolatile()) {
    stringBuffer.append(TEXT_1132);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    if (genClass.hasStaticFeatures()){
    stringBuffer.append(TEXT_1133);
    }
    stringBuffer.append(TEXT_1134);
    stringBuffer.append(genFeature.getQualifiedFeatureAccessor());
    stringBuffer.append(TEXT_1135);
    } else if (genModel.isReflectiveDelegation()) {
    stringBuffer.append(TEXT_1136);
    stringBuffer.append(genFeature.getQualifiedFeatureAccessor());
    stringBuffer.append(TEXT_1137);
    } else if (genFeature.hasSettingDelegate()) {
    stringBuffer.append(TEXT_1138);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_1139);
    } else if (!genFeature.isVolatile()) {
    if (genFeature.isListType()) {
    if (genModel.isVirtualDelegation()) {
    stringBuffer.append(TEXT_1140);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_1141);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_1142);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_1143);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(positiveOffsetCorrection);
    stringBuffer.append(TEXT_1144);
    }
    stringBuffer.append(TEXT_1145);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_1146);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.InternalEList"));
    stringBuffer.append(TEXT_1147);
    stringBuffer.append(singleWildcard);
    stringBuffer.append(TEXT_1148);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_1149);
    } else if (genFeature.isBidirectional() || genFeature.isEffectiveContains()) {
    if (genModel.isVirtualDelegation()) {
    stringBuffer.append(TEXT_1150);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_1151);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_1152);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_1153);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(positiveOffsetCorrection);
    stringBuffer.append(TEXT_1154);
    }
    stringBuffer.append(TEXT_1155);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_1156);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.notify.NotificationChain"));
    stringBuffer.append(TEXT_1157);
    if (!genFeature.isBidirectional()) {
    stringBuffer.append(TEXT_1158);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.InternalEObject"));
    stringBuffer.append(TEXT_1159);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_1160);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(negativeOffsetCorrection);
    stringBuffer.append(TEXT_1161);
    } else { GenFeature reverseFeature = genFeature.getReverse(); GenClass targetClass = reverseFeature.getGenClass(); String reverseOffsetCorrection = targetClass.hasOffsetCorrection() ? " + " + genClass.getOffsetCorrectionField(genFeature) : "";
    stringBuffer.append(TEXT_1162);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.InternalEObject"));
    stringBuffer.append(TEXT_1163);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_1164);
    stringBuffer.append(targetClass.getQualifiedFeatureID(reverseFeature));
    stringBuffer.append(reverseOffsetCorrection);
    stringBuffer.append(TEXT_1165);
    stringBuffer.append(targetClass.getRawImportedInterfaceName());
    stringBuffer.append(TEXT_1166);
    }
    stringBuffer.append(TEXT_1167);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_1168);
    if (genModel.isVirtualDelegation()) {
    stringBuffer.append(TEXT_1169);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_1170);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(positiveOffsetCorrection);
    stringBuffer.append(TEXT_1171);
    } else if (genClass.isESetFlag(genFeature)) {
    if (!genModel.isSuppressNotification()) {
    stringBuffer.append(TEXT_1172);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_1173);
    stringBuffer.append(genClass.getESetFlagsField(genFeature));
    stringBuffer.append(TEXT_1174);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_1175);
    }
    stringBuffer.append(TEXT_1176);
    stringBuffer.append(genClass.getESetFlagsField(genFeature));
    stringBuffer.append(TEXT_1177);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_1178);
    } else {
    if (!genModel.isSuppressNotification()) {
    stringBuffer.append(TEXT_1179);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_1180);
    stringBuffer.append(genFeature.getUncapName());
    stringBuffer.append(TEXT_1181);
    }
    stringBuffer.append(TEXT_1182);
    stringBuffer.append(genFeature.getUncapName());
    stringBuffer.append(TEXT_1183);
    }
    if (!genModel.isSuppressNotification()) {
    stringBuffer.append(TEXT_1184);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.impl.ENotificationImpl"));
    stringBuffer.append(TEXT_1185);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.notify.Notification"));
    stringBuffer.append(TEXT_1186);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(positiveOffsetCorrection);
    stringBuffer.append(TEXT_1187);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_1188);
    }
    stringBuffer.append(TEXT_1189);
    } else {
    if (genClass.isFlag(genFeature)) {
    if (!genModel.isSuppressNotification()) {
    if (genFeature.isBooleanType()) {
    stringBuffer.append(TEXT_1190);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_1191);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_1192);
    stringBuffer.append(genClass.getFlagsField(genFeature));
    stringBuffer.append(TEXT_1193);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_1194);
    } else {
    stringBuffer.append(TEXT_1195);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_1196);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_1197);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_1198);
    stringBuffer.append(genClass.getFlagsField(genFeature));
    stringBuffer.append(TEXT_1199);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_1200);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_1201);
    }
    }
    } else if (genModel.isVirtualDelegation() && !genFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_1202);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_1203);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(positiveOffsetCorrection);
    stringBuffer.append(TEXT_1204);
    } else {
    if (!genModel.isSuppressNotification()) {
    stringBuffer.append(TEXT_1205);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_1206);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_1207);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_1208);
    }
    }
    if (!genModel.isSuppressNotification()) {
    if (genModel.isVirtualDelegation() && !genFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_1209);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_1210);
    } else if (genClass.isESetFlag(genFeature)) {
    stringBuffer.append(TEXT_1211);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_1212);
    stringBuffer.append(genClass.getESetFlagsField(genFeature));
    stringBuffer.append(TEXT_1213);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_1214);
    } else {
    stringBuffer.append(TEXT_1215);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_1216);
    stringBuffer.append(genFeature.getUncapName());
    stringBuffer.append(TEXT_1217);
    }
    }
    if (genFeature.isReferenceType()) {
    stringBuffer.append(TEXT_1218);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_1219);
    if (!genModel.isVirtualDelegation()) {
    if (genClass.isESetFlag(genFeature)) {
    stringBuffer.append(TEXT_1220);
    stringBuffer.append(genClass.getESetFlagsField(genFeature));
    stringBuffer.append(TEXT_1221);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_1222);
    } else {
    stringBuffer.append(TEXT_1223);
    stringBuffer.append(genFeature.getUncapName());
    stringBuffer.append(TEXT_1224);
    }
    }
    if (!genModel.isSuppressNotification()) {
    stringBuffer.append(TEXT_1225);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.impl.ENotificationImpl"));
    stringBuffer.append(TEXT_1226);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.notify.Notification"));
    stringBuffer.append(TEXT_1227);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(positiveOffsetCorrection);
    stringBuffer.append(TEXT_1228);
    if (genModel.isVirtualDelegation()) {
    stringBuffer.append(TEXT_1229);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_1230);
    } else {
    stringBuffer.append(TEXT_1231);
    stringBuffer.append(genFeature.getCapName());
    }
    stringBuffer.append(TEXT_1232);
    if (genModel.isVirtualDelegation()) {
    stringBuffer.append(TEXT_1233);
    } else {
    stringBuffer.append(TEXT_1234);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_1235);
    }
    stringBuffer.append(TEXT_1236);
    }
    } else {
    if (genClass.isFlag(genFeature)) {
    if (genFeature.isBooleanType()) {
    stringBuffer.append(TEXT_1237);
    stringBuffer.append(genFeature.getEDefault());
    stringBuffer.append(TEXT_1238);
    stringBuffer.append(genClass.getFlagsField(genFeature));
    stringBuffer.append(TEXT_1239);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_1240);
    stringBuffer.append(genClass.getFlagsField(genFeature));
    stringBuffer.append(TEXT_1241);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_1242);
    } else {
    stringBuffer.append(TEXT_1243);
    stringBuffer.append(genClass.getFlagsField(genFeature));
    stringBuffer.append(TEXT_1244);
    stringBuffer.append(genClass.getFlagsField(genFeature));
    stringBuffer.append(TEXT_1245);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_1246);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_1247);
    }
    } else if (!genModel.isVirtualDelegation() || genFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_1248);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_1249);
    stringBuffer.append(genFeature.getEDefault());
    stringBuffer.append(TEXT_1250);
    }
    if (!genModel.isVirtualDelegation() || genFeature.isPrimitiveType()) {
    if (genClass.isESetFlag(genFeature)) {
    stringBuffer.append(TEXT_1251);
    stringBuffer.append(genClass.getESetFlagsField(genFeature));
    stringBuffer.append(TEXT_1252);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_1253);
    } else {
    stringBuffer.append(TEXT_1254);
    stringBuffer.append(genFeature.getUncapName());
    stringBuffer.append(TEXT_1255);
    }
    }
    if (!genModel.isSuppressNotification()) {
    stringBuffer.append(TEXT_1256);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.impl.ENotificationImpl"));
    stringBuffer.append(TEXT_1257);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.notify.Notification"));
    stringBuffer.append(TEXT_1258);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(positiveOffsetCorrection);
    stringBuffer.append(TEXT_1259);
    if (genModel.isVirtualDelegation() && !genFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_1260);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_1261);
    stringBuffer.append(genFeature.getEDefault());
    } else {
    stringBuffer.append(TEXT_1262);
    stringBuffer.append(genFeature.getCapName());
    }
    stringBuffer.append(TEXT_1263);
    stringBuffer.append(genFeature.getEDefault());
    stringBuffer.append(TEXT_1264);
    if (genModel.isVirtualDelegation() && !genFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_1265);
    } else {
    stringBuffer.append(TEXT_1266);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_1267);
    }
    stringBuffer.append(TEXT_1268);
    }
    }
    }
    } else if (genFeature.hasDelegateFeature()) { GenFeature delegateFeature = genFeature.getDelegateFeature();
    if (delegateFeature.isWrappedFeatureMapType()) {
    stringBuffer.append(TEXT_1269);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.FeatureMap"));
    stringBuffer.append(TEXT_1270);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.FeatureMap"));
    stringBuffer.append(TEXT_1271);
    stringBuffer.append(delegateFeature.getAccessorName());
    stringBuffer.append(TEXT_1272);
    stringBuffer.append(genFeature.getQualifiedFeatureAccessor());
    stringBuffer.append(TEXT_1273);
    } else {
    stringBuffer.append(TEXT_1274);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.FeatureMap"));
    stringBuffer.append(TEXT_1275);
    stringBuffer.append(delegateFeature.getAccessorName());
    stringBuffer.append(TEXT_1276);
    stringBuffer.append(genFeature.getQualifiedFeatureAccessor());
    stringBuffer.append(TEXT_1277);
    }
    } else if (genClass.getUnsetAccessorOperation(genFeature) != null) {
    stringBuffer.append(TEXT_1278);
    stringBuffer.append(genClass.getUnsetAccessorOperation(genFeature).getBody(genModel.getIndentation(stringBuffer)));
    } else {
    stringBuffer.append(TEXT_1279);
    stringBuffer.append(genFeature.getFormattedName());
    stringBuffer.append(TEXT_1280);
    stringBuffer.append(genFeature.getFeatureKind());
    stringBuffer.append(TEXT_1281);
    //Class/unsetGenFeature.todo.override.javajetinc
    }
    stringBuffer.append(TEXT_1282);
    }
    //Class/unsetGenFeature.override.javajetinc
    }
    if (genFeature.isIsSet() && (isImplementation || !genFeature.isSuppressedIsSetVisibility())) {
    if (isInterface) {
    stringBuffer.append(TEXT_1283);
    stringBuffer.append(genClass.getQualifiedInterfaceName());
    stringBuffer.append(TEXT_1284);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_1285);
    stringBuffer.append(genFeature.getFormattedName());
    stringBuffer.append(TEXT_1286);
    stringBuffer.append(genFeature.getFeatureKind());
    stringBuffer.append(TEXT_1287);
    stringBuffer.append(TEXT_1288);
    stringBuffer.append(genFeature.getFormattedName());
    stringBuffer.append(TEXT_1289);
    stringBuffer.append(genFeature.getFeatureKind());
    stringBuffer.append(TEXT_1290);
    if (genFeature.isChangeable() && !genFeature.isSuppressedUnsetVisibility()) {
    stringBuffer.append(TEXT_1291);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_1292);
    }
    stringBuffer.append(TEXT_1293);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_1294);
    if (!genFeature.isListType() && genFeature.isChangeable() && !genFeature.isSuppressedSetVisibility()) {
    stringBuffer.append(TEXT_1295);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_1296);
    stringBuffer.append(genFeature.getRawImportedBoundType());
    stringBuffer.append(TEXT_1297);
    }
    if (genFeature.hasAPITags()) {
    stringBuffer.append(TEXT_1298);
    stringBuffer.append(genFeature.getAPITags(genModel.getIndentation(stringBuffer)));
    }
    stringBuffer.append(TEXT_1299);
    //Class/isSetGenFeature.javadoc.override.javajetinc
    } else {
    stringBuffer.append(TEXT_1300);
    if (genFeature.hasAPITags()) {
    stringBuffer.append(TEXT_1301);
    stringBuffer.append(genFeature.getAPITags(genModel.getIndentation(stringBuffer)));
    }
    stringBuffer.append(TEXT_1302);
    if (isJDK50) { //Class/isSetGenFeature.annotations.insert.javajetinc
    }
    }
    if (isJDK50 && genFeature.hasAPIDeprecatedTag()) {
    stringBuffer.append(TEXT_1303);
    }
    if (!isImplementation) {
    stringBuffer.append(TEXT_1304);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_1305);
    } else {
    stringBuffer.append(TEXT_1306);
    stringBuffer.append(genFeature.getAccessorName());
    if (genClass.hasCollidingIsSetAccessorOperation(genFeature)) {
    stringBuffer.append(TEXT_1307);
    }
    stringBuffer.append(TEXT_1308);
    if (genModel.isDynamicDelegation() && !genFeature.isVolatile()) {
    stringBuffer.append(TEXT_1309);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    if (genClass.hasStaticFeatures()){
    stringBuffer.append(TEXT_1310);
    }
    stringBuffer.append(TEXT_1311);
    stringBuffer.append(genFeature.getQualifiedFeatureAccessor());
    stringBuffer.append(TEXT_1312);
    } else if (genModel.isReflectiveDelegation()) {
    stringBuffer.append(TEXT_1313);
    stringBuffer.append(genFeature.getQualifiedFeatureAccessor());
    stringBuffer.append(TEXT_1314);
    } else if (genFeature.hasSettingDelegate()) {
    stringBuffer.append(TEXT_1315);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_1316);
    } else if (!genFeature.isVolatile()) {
    if (genFeature.isListType()) {
    if (genModel.isVirtualDelegation()) {
    stringBuffer.append(TEXT_1317);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_1318);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_1319);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_1320);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(positiveOffsetCorrection);
    stringBuffer.append(TEXT_1321);
    }
    stringBuffer.append(TEXT_1322);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_1323);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.InternalEList"));
    stringBuffer.append(TEXT_1324);
    stringBuffer.append(singleWildcard);
    stringBuffer.append(TEXT_1325);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_1326);
    } else {
    if (genModel.isVirtualDelegation() && !genFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_1327);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(positiveOffsetCorrection);
    stringBuffer.append(TEXT_1328);
    } else if (genClass.isESetFlag(genFeature)) {
    stringBuffer.append(TEXT_1329);
    stringBuffer.append(genClass.getESetFlagsField(genFeature));
    stringBuffer.append(TEXT_1330);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_1331);
    } else {
    stringBuffer.append(TEXT_1332);
    stringBuffer.append(genFeature.getUncapName());
    stringBuffer.append(TEXT_1333);
    }
    }
    } else if (genFeature.hasDelegateFeature()) { GenFeature delegateFeature = genFeature.getDelegateFeature();
    if (delegateFeature.isWrappedFeatureMapType()) {
    stringBuffer.append(TEXT_1334);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.FeatureMap"));
    stringBuffer.append(TEXT_1335);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.FeatureMap"));
    stringBuffer.append(TEXT_1336);
    stringBuffer.append(delegateFeature.getAccessorName());
    stringBuffer.append(TEXT_1337);
    stringBuffer.append(genFeature.getQualifiedFeatureAccessor());
    stringBuffer.append(TEXT_1338);
    } else {
    stringBuffer.append(TEXT_1339);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.FeatureMap"));
    stringBuffer.append(TEXT_1340);
    stringBuffer.append(delegateFeature.getAccessorName());
    stringBuffer.append(TEXT_1341);
    stringBuffer.append(genFeature.getQualifiedFeatureAccessor());
    stringBuffer.append(TEXT_1342);
    }
    } else if (genClass.getIsSetAccessorOperation(genFeature) != null) {
    stringBuffer.append(TEXT_1343);
    stringBuffer.append(genClass.getIsSetAccessorOperation(genFeature).getBody(genModel.getIndentation(stringBuffer)));
    } else {
    stringBuffer.append(TEXT_1344);
    stringBuffer.append(genFeature.getFormattedName());
    stringBuffer.append(TEXT_1345);
    stringBuffer.append(genFeature.getFeatureKind());
    stringBuffer.append(TEXT_1346);
    //Class/isSetGenFeature.todo.override.javajetinc
    }
    stringBuffer.append(TEXT_1347);
    }
    //Class/isSetGenFeature.override.javajetinc
    }
    //Class/genFeature.override.javajetinc
    }//for
    }}.run();
    for (GenOperation genOperation : (isImplementation ? genClass.getImplementedGenOperations() : genClass.getDeclaredGenOperations())) {
    if (isImplementation) {
    if (genOperation.isInvariant() && genOperation.hasInvariantExpression()) {
    stringBuffer.append(TEXT_1348);
    stringBuffer.append(genOperation.getName());
    stringBuffer.append(TEXT_1349);
    stringBuffer.append(genOperation.getParameterTypes(", "));
    stringBuffer.append(TEXT_1350);
    stringBuffer.append(genOperation.getFormattedName());
    stringBuffer.append(TEXT_1351);
    stringBuffer.append(genOperation.getName());
    stringBuffer.append(TEXT_1352);
    stringBuffer.append(genOperation.getParameterTypes(", "));
    stringBuffer.append(TEXT_1353);
    if (genOperation.hasAPITags()) {
    stringBuffer.append(TEXT_1354);
    stringBuffer.append(genOperation.getAPITags(genModel.getIndentation(stringBuffer)));
    }
    stringBuffer.append(TEXT_1355);
    if (isJDK50 && genOperation.hasAPIDeprecatedTag()) {
    stringBuffer.append(TEXT_1356);
    }
    stringBuffer.append(TEXT_1357);
    stringBuffer.append(genModel.getImportedName("java.lang.String"));
    stringBuffer.append(TEXT_1358);
    stringBuffer.append(CodeGenUtil.upperName(genClass.getUniqueName(genOperation), genModel.getLocale()));
    stringBuffer.append(TEXT_1359);
    stringBuffer.append(genOperation.getInvariantExpression("\t\t"));
    stringBuffer.append(TEXT_1360);
    stringBuffer.append(genModel.getNonNLS());
    stringBuffer.append(TEXT_1361);
    } else if (genOperation.hasInvocationDelegate()) {
    stringBuffer.append(TEXT_1362);
    stringBuffer.append(genOperation.getName());
    stringBuffer.append(TEXT_1363);
    stringBuffer.append(genOperation.getParameterTypes(", "));
    stringBuffer.append(TEXT_1364);
    stringBuffer.append(genOperation.getFormattedName());
    stringBuffer.append(TEXT_1365);
    stringBuffer.append(genOperation.getName());
    stringBuffer.append(TEXT_1366);
    stringBuffer.append(genOperation.getParameterTypes(", "));
    stringBuffer.append(TEXT_1367);
    if (genOperation.hasAPITags()) {
    stringBuffer.append(TEXT_1368);
    stringBuffer.append(genOperation.getAPITags(genModel.getIndentation(stringBuffer)));
    }
    stringBuffer.append(TEXT_1369);
    if (isJDK50 && genOperation.hasAPIDeprecatedTag()) {
    stringBuffer.append(TEXT_1370);
    }
    stringBuffer.append(TEXT_1371);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.EOperation"));
    stringBuffer.append(TEXT_1372);
    stringBuffer.append(CodeGenUtil.upperName(genClass.getUniqueName(genOperation), genModel.getLocale()));
    stringBuffer.append(TEXT_1373);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.EOperation"));
    stringBuffer.append(TEXT_1374);
    stringBuffer.append(genOperation.getQualifiedOperationAccessor());
    stringBuffer.append(TEXT_1375);
    }
    }
    if (isInterface) {
    stringBuffer.append(TEXT_1376);
    stringBuffer.append(TEXT_1377);
    if (genOperation.hasDocumentation() || genOperation.hasParameterDocumentation()) {
    stringBuffer.append(TEXT_1378);
    if (genOperation.hasDocumentation()) {
    stringBuffer.append(TEXT_1379);
    stringBuffer.append(genOperation.getDocumentation(genModel.getIndentation(stringBuffer)));
    }
    for (GenParameter genParameter : genOperation.getGenParameters()) {
    if (genParameter.hasDocumentation()) { String documentation = genParameter.getDocumentation("");
    if (documentation.contains("\n") || documentation.contains("\r")) {
    stringBuffer.append(TEXT_1380);
    stringBuffer.append(genParameter.getName());
    stringBuffer.append(TEXT_1381);
    stringBuffer.append(genParameter.getDocumentation(genModel.getIndentation(stringBuffer)));
    } else {
    stringBuffer.append(TEXT_1382);
    stringBuffer.append(genParameter.getName());
    stringBuffer.append(TEXT_1383);
    stringBuffer.append(genParameter.getDocumentation(genModel.getIndentation(stringBuffer)));
    }
    }
    }
    stringBuffer.append(TEXT_1384);
    }
    if (!genModel.isSuppressEMFModelTags()) { boolean first = true; for (StringTokenizer stringTokenizer = new StringTokenizer(genOperation.getModelInfo(), "\n\r"); stringTokenizer.hasMoreTokens(); ) { String modelInfo = stringTokenizer.nextToken(); if (first) { first = false;
    stringBuffer.append(TEXT_1385);
    stringBuffer.append(modelInfo);
    } else {
    stringBuffer.append(TEXT_1386);
    stringBuffer.append(modelInfo);
    }} if (first) {
    stringBuffer.append(TEXT_1387);
    }}
    stringBuffer.append(TEXT_1388);
    //Class/genOperation.javadoc.override.javajetinc
    } else {
    stringBuffer.append(TEXT_1389);
    if (genOperation.hasAPITags()) {
    stringBuffer.append(TEXT_1390);
    stringBuffer.append(genOperation.getAPITags(genModel.getIndentation(stringBuffer)));
    }
    stringBuffer.append(TEXT_1391);
    if (isJDK50) { //Class/genOperation.annotations.insert.javajetinc
    }
    }
    if (isJDK50 && genOperation.hasAPIDeprecatedTag()) {
    stringBuffer.append(TEXT_1392);
    }
    if (!isImplementation) {
    stringBuffer.append(TEXT_1393);
    stringBuffer.append(genOperation.getTypeParameters(genClass));
    stringBuffer.append(genOperation.getImportedType(genClass));
    stringBuffer.append(TEXT_1394);
    stringBuffer.append(genOperation.getName());
    stringBuffer.append(TEXT_1395);
    stringBuffer.append(genOperation.getParameters(genClass));
    stringBuffer.append(TEXT_1396);
    stringBuffer.append(genOperation.getThrows(genClass));
    stringBuffer.append(TEXT_1397);
    } else {
    if (genModel.useGenerics() && !genOperation.hasBody() && !genOperation.isInvariant() && genOperation.hasInvocationDelegate() && genOperation.isUncheckedCast(genClass)) {
    stringBuffer.append(TEXT_1398);
    }
    stringBuffer.append(TEXT_1399);
    stringBuffer.append(genOperation.getTypeParameters(genClass));
    stringBuffer.append(genOperation.getImportedType(genClass));
    stringBuffer.append(TEXT_1400);
    stringBuffer.append(genOperation.getName());
    stringBuffer.append(TEXT_1401);
    stringBuffer.append(genOperation.getParameters(isImplementation, genClass));
    stringBuffer.append(TEXT_1402);
    stringBuffer.append(genOperation.getThrows(genClass));
    stringBuffer.append(TEXT_1403);
    if (genOperation.hasBody()) {
    stringBuffer.append(TEXT_1404);
    stringBuffer.append(genOperation.getBody(genModel.getIndentation(stringBuffer)));
    } else if (genOperation.isInvariant()) {GenClass opClass = genOperation.getGenClass(); String diagnostics = genOperation.getGenParameters().get(0).getName(); String context = genOperation.getGenParameters().get(1).getName();
    if (genOperation.hasInvariantExpression()) {
    stringBuffer.append(TEXT_1405);
    stringBuffer.append(opClass.getGenPackage().getImportedValidatorClassName());
    stringBuffer.append(TEXT_1406);
    stringBuffer.append(genClass.getQualifiedClassifierAccessor());
    stringBuffer.append(TEXT_1407);
    stringBuffer.append(diagnostics);
    stringBuffer.append(TEXT_1408);
    stringBuffer.append(context);
    stringBuffer.append(TEXT_1409);
    stringBuffer.append(genOperation.getValidationDelegate());
    stringBuffer.append(TEXT_1410);
    stringBuffer.append(genModel.getNonNLS());
    stringBuffer.append(TEXT_1411);
    stringBuffer.append(genOperation.getQualifiedOperationAccessor());
    stringBuffer.append(TEXT_1412);
    stringBuffer.append(CodeGenUtil.upperName(genClass.getUniqueName(genOperation), genModel.getLocale()));
    stringBuffer.append(TEXT_1413);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.util.Diagnostic"));
    stringBuffer.append(TEXT_1414);
    stringBuffer.append(opClass.getGenPackage().getImportedValidatorClassName());
    stringBuffer.append(TEXT_1415);
    stringBuffer.append(opClass.getGenPackage().getImportedValidatorClassName());
    stringBuffer.append(TEXT_1416);
    stringBuffer.append(opClass.getOperationID(genOperation));
    stringBuffer.append(TEXT_1417);
    } else {
    stringBuffer.append(TEXT_1418);
    stringBuffer.append(diagnostics);
    stringBuffer.append(TEXT_1419);
    stringBuffer.append(diagnostics);
    stringBuffer.append(TEXT_1420);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.util.BasicDiagnostic"));
    stringBuffer.append(TEXT_1421);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.util.Diagnostic"));
    stringBuffer.append(TEXT_1422);
    stringBuffer.append(opClass.getGenPackage().getImportedValidatorClassName());
    stringBuffer.append(TEXT_1423);
    stringBuffer.append(opClass.getGenPackage().getImportedValidatorClassName());
    stringBuffer.append(TEXT_1424);
    stringBuffer.append(opClass.getOperationID(genOperation));
    stringBuffer.append(TEXT_1425);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.plugin.EcorePlugin"));
    stringBuffer.append(TEXT_1426);
    stringBuffer.append(genOperation.getName());
    stringBuffer.append(TEXT_1427);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.EObjectValidator"));
    stringBuffer.append(TEXT_1428);
    stringBuffer.append(context);
    stringBuffer.append(TEXT_1429);
    stringBuffer.append(genModel.getNonNLS());
    stringBuffer.append(genModel.getNonNLS(2));
    stringBuffer.append(TEXT_1430);
    }
    } else if (genOperation.hasInvocationDelegate()) { int size = genOperation.getGenParameters().size();
    stringBuffer.append(TEXT_1431);
    if (genOperation.isVoid()) {
    stringBuffer.append(TEXT_1432);
    stringBuffer.append(CodeGenUtil.upperName(genClass.getUniqueName(genOperation), genModel.getLocale()));
    stringBuffer.append(TEXT_1433);
    if (size > 0) {
    stringBuffer.append(TEXT_1434);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.util.BasicEList"));
    stringBuffer.append(TEXT_1435);
    stringBuffer.append(size);
    stringBuffer.append(TEXT_1436);
    stringBuffer.append(genOperation.getParametersArray(genClass));
    stringBuffer.append(TEXT_1437);
    } else {
    stringBuffer.append(TEXT_1438);
    }
    stringBuffer.append(TEXT_1439);
    } else {
    stringBuffer.append(TEXT_1440);
    if (!isJDK50 && genOperation.isPrimitiveType()) {
    stringBuffer.append(TEXT_1441);
    }
    stringBuffer.append(TEXT_1442);
    stringBuffer.append(genOperation.getObjectType(genClass));
    stringBuffer.append(TEXT_1443);
    stringBuffer.append(CodeGenUtil.upperName(genClass.getUniqueName(genOperation), genModel.getLocale()));
    stringBuffer.append(TEXT_1444);
    if (size > 0) {
    stringBuffer.append(TEXT_1445);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.util.BasicEList"));
    stringBuffer.append(TEXT_1446);
    stringBuffer.append(size);
    stringBuffer.append(TEXT_1447);
    stringBuffer.append(genOperation.getParametersArray(genClass));
    stringBuffer.append(TEXT_1448);
    } else {
    stringBuffer.append(TEXT_1449);
    }
    stringBuffer.append(TEXT_1450);
    if (!isJDK50 && genOperation.isPrimitiveType()) {
    stringBuffer.append(TEXT_1451);
    stringBuffer.append(genOperation.getPrimitiveValueFunction());
    stringBuffer.append(TEXT_1452);
    }
    stringBuffer.append(TEXT_1453);
    }
    stringBuffer.append(TEXT_1454);
    stringBuffer.append(genModel.getImportedName(isGWT ? "org.eclipse.emf.common.util.InvocationTargetException" : "java.lang.reflect.InvocationTargetException"));
    stringBuffer.append(TEXT_1455);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.util.WrappedException"));
    stringBuffer.append(TEXT_1456);
    } else {
    stringBuffer.append(TEXT_1457);
    //Class/implementedGenOperation.todo.override.javajetinc
    }
    stringBuffer.append(TEXT_1458);
    }
    //Class/implementedGenOperation.override.javajetinc
    }//for
    if (isImplementation && !genModel.isReflectiveDelegation() && genClass.implementsAny(genClass.getEInverseAddGenFeatures())) {
    stringBuffer.append(TEXT_1459);
    if (genModel.useGenerics()) {
    for (GenFeature genFeature : genClass.getEInverseAddGenFeatures()) {
    if (genFeature.isUncheckedCast(genClass)) {
    stringBuffer.append(TEXT_1460);
    break; }
    }
    }
    if (genModel.useClassOverrideAnnotation()) {
    stringBuffer.append(TEXT_1461);
    }
    stringBuffer.append(TEXT_1462);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.notify.NotificationChain"));
    stringBuffer.append(TEXT_1463);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.InternalEObject"));
    stringBuffer.append(TEXT_1464);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.notify.NotificationChain"));
    stringBuffer.append(TEXT_1465);
    stringBuffer.append(negativeOffsetCorrection);
    stringBuffer.append(TEXT_1466);
    for (GenFeature genFeature : genClass.getEInverseAddGenFeatures()) {
    stringBuffer.append(TEXT_1467);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(TEXT_1468);
    if (genFeature.isListType()) { String cast = "("  + genModel.getImportedName("org.eclipse.emf.ecore.util.InternalEList") + (!genModel.useGenerics() ? ")" : "<" + genModel.getImportedName("org.eclipse.emf.ecore.InternalEObject") + ">)(" + genModel.getImportedName("org.eclipse.emf.ecore.util.InternalEList") + "<?>)");
    if (genFeature.isMapType() && genFeature.isEffectiveSuppressEMFTypes()) {
    stringBuffer.append(TEXT_1469);
    stringBuffer.append(cast);
    stringBuffer.append(TEXT_1470);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.util.EMap"));
    stringBuffer.append(TEXT_1471);
    stringBuffer.append(genFeature.getImportedMapTemplateArguments(genClass));
    stringBuffer.append(TEXT_1472);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_1473);
    } else {
    stringBuffer.append(TEXT_1474);
    stringBuffer.append(cast);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_1475);
    }
    } else if (genFeature.isContainer()) {
    stringBuffer.append(TEXT_1476);
    if (genFeature.isBasicSet()) {
    stringBuffer.append(TEXT_1477);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_1478);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_1479);
    } else {
    stringBuffer.append(TEXT_1480);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(positiveOffsetCorrection);
    stringBuffer.append(TEXT_1481);
    }
    } else {
    if (genClass.getImplementingGenModel(genFeature).isVirtualDelegation()) {
    stringBuffer.append(TEXT_1482);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_1483);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_1484);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_1485);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(positiveOffsetCorrection);
    stringBuffer.append(TEXT_1486);
    } else if (genFeature.isVolatile() || genClass.getImplementingGenModel(genFeature).isDynamicDelegation()) {
    stringBuffer.append(TEXT_1487);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_1488);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_1489);
    if (genFeature.isResolveProxies()) {
    stringBuffer.append(TEXT_1490);
    stringBuffer.append(genFeature.getAccessorName());
    } else {
    stringBuffer.append(genFeature.getGetAccessor());
    }
    stringBuffer.append(TEXT_1491);
    }
    stringBuffer.append(TEXT_1492);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_1493);
    if (genFeature.isEffectiveContains()) {
    stringBuffer.append(TEXT_1494);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.InternalEObject"));
    stringBuffer.append(TEXT_1495);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_1496);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(negativeOffsetCorrection);
    stringBuffer.append(TEXT_1497);
    } else { GenFeature reverseFeature = genFeature.getReverse(); GenClass targetClass = reverseFeature.getGenClass(); String reverseOffsetCorrection = targetClass.hasOffsetCorrection() ? " + " + genClass.getOffsetCorrectionField(genFeature) : "";
    stringBuffer.append(TEXT_1498);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.InternalEObject"));
    stringBuffer.append(TEXT_1499);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_1500);
    stringBuffer.append(targetClass.getQualifiedFeatureID(reverseFeature));
    stringBuffer.append(reverseOffsetCorrection);
    stringBuffer.append(TEXT_1501);
    stringBuffer.append(targetClass.getRawImportedInterfaceName());
    stringBuffer.append(TEXT_1502);
    }
    stringBuffer.append(TEXT_1503);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_1504);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_1505);
    }
    }
    stringBuffer.append(TEXT_1506);
    if (genModel.isMinimalReflectiveMethods()) {
    stringBuffer.append(TEXT_1507);
    } else {
    stringBuffer.append(TEXT_1508);
    }
    stringBuffer.append(TEXT_1509);
    }
    if (isImplementation && !genModel.isReflectiveDelegation() && genClass.implementsAny(genClass.getEInverseRemoveGenFeatures())) {
    stringBuffer.append(TEXT_1510);
    if (genModel.useClassOverrideAnnotation()) {
    stringBuffer.append(TEXT_1511);
    }
    stringBuffer.append(TEXT_1512);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.notify.NotificationChain"));
    stringBuffer.append(TEXT_1513);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.InternalEObject"));
    stringBuffer.append(TEXT_1514);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.notify.NotificationChain"));
    stringBuffer.append(TEXT_1515);
    stringBuffer.append(negativeOffsetCorrection);
    stringBuffer.append(TEXT_1516);
    for (GenFeature genFeature : genClass.getEInverseRemoveGenFeatures()) {
    stringBuffer.append(TEXT_1517);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(TEXT_1518);
    if (genFeature.isListType()) {
    if (genFeature.isMapType() && genFeature.isEffectiveSuppressEMFTypes()) {
    stringBuffer.append(TEXT_1519);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.InternalEList"));
    stringBuffer.append(singleWildcard);
    stringBuffer.append(TEXT_1520);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.util.EMap"));
    stringBuffer.append(TEXT_1521);
    stringBuffer.append(genFeature.getImportedMapTemplateArguments(genClass));
    stringBuffer.append(TEXT_1522);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_1523);
    } else if (genFeature.isWrappedFeatureMapType()) {
    stringBuffer.append(TEXT_1524);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.InternalEList"));
    stringBuffer.append(singleWildcard);
    stringBuffer.append(TEXT_1525);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.FeatureMap"));
    stringBuffer.append(TEXT_1526);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_1527);
    } else {
    stringBuffer.append(TEXT_1528);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.InternalEList"));
    stringBuffer.append(singleWildcard);
    stringBuffer.append(TEXT_1529);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_1530);
    }
    } else if (genFeature.isContainer() && !genFeature.isBasicSet()) {
    stringBuffer.append(TEXT_1531);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(positiveOffsetCorrection);
    stringBuffer.append(TEXT_1532);
    } else if (genFeature.isUnsettable()) {
    stringBuffer.append(TEXT_1533);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_1534);
    } else {
    stringBuffer.append(TEXT_1535);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_1536);
    }
    }
    stringBuffer.append(TEXT_1537);
    if (genModel.isMinimalReflectiveMethods()) {
    stringBuffer.append(TEXT_1538);
    } else {
    stringBuffer.append(TEXT_1539);
    }
    stringBuffer.append(TEXT_1540);
    }
    if (isImplementation && !genModel.isReflectiveDelegation() && genClass.implementsAny(genClass.getEBasicRemoveFromContainerGenFeatures())) {
    stringBuffer.append(TEXT_1541);
    if (genModel.useClassOverrideAnnotation()) {
    stringBuffer.append(TEXT_1542);
    }
    stringBuffer.append(TEXT_1543);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.notify.NotificationChain"));
    stringBuffer.append(TEXT_1544);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.notify.NotificationChain"));
    stringBuffer.append(TEXT_1545);
    stringBuffer.append(negativeOffsetCorrection);
    stringBuffer.append(TEXT_1546);
    for (GenFeature genFeature : genClass.getEBasicRemoveFromContainerGenFeatures()) {
    GenFeature reverseFeature = genFeature.getReverse(); GenClass targetClass = reverseFeature.getGenClass(); String reverseOffsetCorrection = targetClass.hasOffsetCorrection() ? " + " + genClass.getOffsetCorrectionField(genFeature) : "";
    stringBuffer.append(TEXT_1547);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(TEXT_1548);
    stringBuffer.append(targetClass.getQualifiedFeatureID(reverseFeature));
    stringBuffer.append(reverseOffsetCorrection);
    stringBuffer.append(TEXT_1549);
    stringBuffer.append(targetClass.getRawImportedInterfaceName());
    stringBuffer.append(TEXT_1550);
    }
    stringBuffer.append(TEXT_1551);
    if (genModel.isMinimalReflectiveMethods()) {
    stringBuffer.append(TEXT_1552);
    } else {
    stringBuffer.append(TEXT_1553);
    }
    stringBuffer.append(TEXT_1554);
    }
    if (isImplementation && !genModel.isReflectiveDelegation() && genClass.implementsAny(genClass.getEGetGenFeatures())) {
    stringBuffer.append(TEXT_1555);
    if (genModel.useClassOverrideAnnotation()) {
    stringBuffer.append(TEXT_1556);
    }
    stringBuffer.append(TEXT_1557);
    stringBuffer.append(negativeOffsetCorrection);
    stringBuffer.append(TEXT_1558);
    for (GenFeature genFeature : genClass.getEGetGenFeatures()) {
    stringBuffer.append(TEXT_1559);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(TEXT_1560);
    if (genFeature.isPrimitiveType()) {
    if (isJDK50) {
    stringBuffer.append(TEXT_1561);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_1562);
    } else if (genFeature.isBooleanType()) {
    stringBuffer.append(TEXT_1563);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_1564);
    } else {
    stringBuffer.append(TEXT_1565);
    stringBuffer.append(genFeature.getObjectType(genClass));
    stringBuffer.append(TEXT_1566);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_1567);
    }
    } else if (genFeature.isResolveProxies() && !genFeature.isListType()) {
    stringBuffer.append(TEXT_1568);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_1569);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_1570);
    } else if (genFeature.isMapType()) {
    if (genFeature.isEffectiveSuppressEMFTypes()) {
    stringBuffer.append(TEXT_1571);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.util.EMap"));
    stringBuffer.append(TEXT_1572);
    stringBuffer.append(genFeature.getImportedMapTemplateArguments(genClass));
    stringBuffer.append(TEXT_1573);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_1574);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_1575);
    } else {
    stringBuffer.append(TEXT_1576);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_1577);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_1578);
    }
    } else if (genFeature.isWrappedFeatureMapType()) {
    stringBuffer.append(TEXT_1579);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.FeatureMap"));
    stringBuffer.append(TEXT_1580);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_1581);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_1582);
    } else if (genFeature.isFeatureMapType()) {
    stringBuffer.append(TEXT_1583);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_1584);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.FeatureMap"));
    stringBuffer.append(TEXT_1585);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_1586);
    } else {
    stringBuffer.append(TEXT_1587);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_1588);
    }
    }
    stringBuffer.append(TEXT_1589);
    if (genModel.isMinimalReflectiveMethods()) {
    stringBuffer.append(TEXT_1590);
    } else {
    stringBuffer.append(TEXT_1591);
    }
    stringBuffer.append(TEXT_1592);
    }
    if (isImplementation && !genModel.isReflectiveDelegation() && genClass.implementsAny(genClass.getESetGenFeatures())) {
    stringBuffer.append(TEXT_1593);
    if (genModel.useGenerics()) {
    for (GenFeature genFeature : genClass.getESetGenFeatures()) {
    if (genFeature.isUncheckedCast(genClass) && !genFeature.isFeatureMapType() && !genFeature.isMapType()) {
    stringBuffer.append(TEXT_1594);
    break; }
    }
    }
    if (genModel.useClassOverrideAnnotation()) {
    stringBuffer.append(TEXT_1595);
    }
    stringBuffer.append(TEXT_1596);
    stringBuffer.append(negativeOffsetCorrection);
    stringBuffer.append(TEXT_1597);
    for (GenFeature genFeature : genClass.getESetGenFeatures()) {
    stringBuffer.append(TEXT_1598);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(TEXT_1599);
    if (genFeature.isListType()) {
    if (genFeature.isWrappedFeatureMapType()) {
    stringBuffer.append(TEXT_1600);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.FeatureMap"));
    stringBuffer.append(TEXT_1601);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.FeatureMap"));
    stringBuffer.append(TEXT_1602);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_1603);
    } else if (genFeature.isFeatureMapType()) {
    stringBuffer.append(TEXT_1604);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.FeatureMap"));
    stringBuffer.append(TEXT_1605);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_1606);
    } else if (genFeature.isMapType()) {
    if (genFeature.isEffectiveSuppressEMFTypes()) {
    stringBuffer.append(TEXT_1607);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.EStructuralFeature"));
    stringBuffer.append(TEXT_1608);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.util.EMap"));
    stringBuffer.append(TEXT_1609);
    stringBuffer.append(genFeature.getImportedMapTemplateArguments(genClass));
    stringBuffer.append(TEXT_1610);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_1611);
    } else {
    stringBuffer.append(TEXT_1612);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.EStructuralFeature"));
    stringBuffer.append(TEXT_1613);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_1614);
    }
    } else {
    stringBuffer.append(TEXT_1615);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_1616);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_1617);
    stringBuffer.append(genModel.getImportedName("java.util.Collection"));
    if (isJDK50) {
    stringBuffer.append(TEXT_1618);
    stringBuffer.append(genFeature.getListItemType(genClass));
    stringBuffer.append(TEXT_1619);
    }
    stringBuffer.append(TEXT_1620);
    }
    } else if (!isJDK50 && genFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_1621);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_1622);
    stringBuffer.append(genFeature.getObjectType(genClass));
    stringBuffer.append(TEXT_1623);
    stringBuffer.append(genFeature.getPrimitiveValueFunction());
    stringBuffer.append(TEXT_1624);
    } else {
    stringBuffer.append(TEXT_1625);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_1626);
    if (genFeature.getTypeGenDataType() == null || !genFeature.getTypeGenDataType().isObjectType() || !genFeature.getRawType().equals(genFeature.getType(genClass))) {
    stringBuffer.append(TEXT_1627);
    stringBuffer.append(genFeature.getObjectType(genClass));
    stringBuffer.append(TEXT_1628);
    }
    stringBuffer.append(TEXT_1629);
    }
    stringBuffer.append(TEXT_1630);
    }
    stringBuffer.append(TEXT_1631);
    if (genModel.isMinimalReflectiveMethods()) {
    stringBuffer.append(TEXT_1632);
    } else {
    stringBuffer.append(TEXT_1633);
    }
    stringBuffer.append(TEXT_1634);
    }
    if (isImplementation && !genModel.isReflectiveDelegation() && genClass.implementsAny(genClass.getEUnsetGenFeatures())) {
    stringBuffer.append(TEXT_1635);
    if (genModel.useClassOverrideAnnotation()) {
    stringBuffer.append(TEXT_1636);
    }
    stringBuffer.append(TEXT_1637);
    stringBuffer.append(negativeOffsetCorrection);
    stringBuffer.append(TEXT_1638);
    for (GenFeature genFeature : genClass.getEUnsetGenFeatures()) {
    stringBuffer.append(TEXT_1639);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(TEXT_1640);
    if (genFeature.isListType() && !genFeature.isUnsettable()) {
    if (genFeature.isWrappedFeatureMapType()) {
    stringBuffer.append(TEXT_1641);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.FeatureMap"));
    stringBuffer.append(TEXT_1642);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_1643);
    } else {
    stringBuffer.append(TEXT_1644);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_1645);
    }
    } else if (genFeature.isUnsettable()) {
    stringBuffer.append(TEXT_1646);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_1647);
    } else if (!genFeature.hasEDefault()) {
    stringBuffer.append(TEXT_1648);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_1649);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_1650);
    } else if (genFeature.hasSettingDelegate()) {
    stringBuffer.append(TEXT_1651);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_1652);
    } else {
    stringBuffer.append(TEXT_1653);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_1654);
    stringBuffer.append(genFeature.getEDefault());
    stringBuffer.append(TEXT_1655);
    }
    stringBuffer.append(TEXT_1656);
    }
    stringBuffer.append(TEXT_1657);
    if (genModel.isMinimalReflectiveMethods()) {
    stringBuffer.append(TEXT_1658);
    } else {
    stringBuffer.append(TEXT_1659);
    }
    stringBuffer.append(TEXT_1660);
    //Class/eUnset.override.javajetinc
    }
    if (isImplementation && !genModel.isReflectiveDelegation() && genClass.implementsAny(genClass.getEIsSetGenFeatures())) {
    stringBuffer.append(TEXT_1661);
    if (genModel.useGenerics()) {
    for (GenFeature genFeature : genClass.getEIsSetGenFeatures()) {
    if (genFeature.isListType() && !genFeature.isUnsettable() && !genFeature.isWrappedFeatureMapType() && !genClass.isField(genFeature) && genFeature.isField() && genClass.getImplementingGenModel(genFeature).isVirtualDelegation()) {
    stringBuffer.append(TEXT_1662);
    break; }
    }
    }
    if (genModel.useClassOverrideAnnotation()) {
    stringBuffer.append(TEXT_1663);
    }
    stringBuffer.append(TEXT_1664);
    stringBuffer.append(negativeOffsetCorrection);
    stringBuffer.append(TEXT_1665);
    for (GenFeature genFeature : genClass.getEIsSetGenFeatures()) { String safeNameAccessor = genFeature.getSafeName(); if ("featureID".equals(safeNameAccessor)) { safeNameAccessor = "this." + safeNameAccessor; }
    stringBuffer.append(TEXT_1666);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(TEXT_1667);
    if (genFeature.hasSettingDelegate()) {
    if (genFeature.isUnsettable()) {
    stringBuffer.append(TEXT_1668);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_1669);
    } else {
    stringBuffer.append(TEXT_1670);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_1671);
    }
    } else if (genFeature.isListType() && !genFeature.isUnsettable()) {
    if (genFeature.isWrappedFeatureMapType()) {
    if (genFeature.isVolatile()) {
    stringBuffer.append(TEXT_1672);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.FeatureMap"));
    stringBuffer.append(TEXT_1673);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_1674);
    } else {
    stringBuffer.append(TEXT_1675);
    stringBuffer.append(safeNameAccessor);
    stringBuffer.append(TEXT_1676);
    stringBuffer.append(safeNameAccessor);
    stringBuffer.append(TEXT_1677);
    }
    } else {
    if (genClass.isField(genFeature)) {
    stringBuffer.append(TEXT_1678);
    stringBuffer.append(safeNameAccessor);
    stringBuffer.append(TEXT_1679);
    stringBuffer.append(safeNameAccessor);
    stringBuffer.append(TEXT_1680);
    } else {
    if (genFeature.isField() && genClass.getImplementingGenModel(genFeature).isVirtualDelegation()) {
    stringBuffer.append(TEXT_1681);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_1682);
    stringBuffer.append(safeNameAccessor);
    stringBuffer.append(TEXT_1683);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_1684);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(positiveOffsetCorrection);
    stringBuffer.append(TEXT_1685);
    stringBuffer.append(safeNameAccessor);
    stringBuffer.append(TEXT_1686);
    stringBuffer.append(safeNameAccessor);
    stringBuffer.append(TEXT_1687);
    } else {
    stringBuffer.append(TEXT_1688);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_1689);
    }
    }
    }
    } else if (genFeature.isUnsettable()) {
    stringBuffer.append(TEXT_1690);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_1691);
    } else if (genFeature.isResolveProxies()) {
    if (genClass.isField(genFeature)) {
    stringBuffer.append(TEXT_1692);
    stringBuffer.append(safeNameAccessor);
    stringBuffer.append(TEXT_1693);
    } else {
    if (genFeature.isField() && genClass.getImplementingGenModel(genFeature).isVirtualDelegation()) {
    stringBuffer.append(TEXT_1694);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(positiveOffsetCorrection);
    stringBuffer.append(TEXT_1695);
    } else {
    stringBuffer.append(TEXT_1696);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_1697);
    }
    }
    } else if (!genFeature.hasEDefault()) {
    if (genClass.isField(genFeature)) {
    stringBuffer.append(TEXT_1698);
    stringBuffer.append(safeNameAccessor);
    stringBuffer.append(TEXT_1699);
    } else {
    if (genFeature.isField() && genClass.getImplementingGenModel(genFeature).isVirtualDelegation()) {
    stringBuffer.append(TEXT_1700);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(positiveOffsetCorrection);
    stringBuffer.append(TEXT_1701);
    } else {
    stringBuffer.append(TEXT_1702);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_1703);
    }
    }
    } else if (genFeature.isPrimitiveType() || genFeature.isEnumType()) {
    if (genClass.isField(genFeature)) {
    if (genClass.isFlag(genFeature)) {
    if (genFeature.isBooleanType()) {
    stringBuffer.append(TEXT_1704);
    stringBuffer.append(genClass.getFlagsField(genFeature));
    stringBuffer.append(TEXT_1705);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_1706);
    stringBuffer.append(genFeature.getEDefault());
    stringBuffer.append(TEXT_1707);
    } else {
    stringBuffer.append(TEXT_1708);
    stringBuffer.append(genClass.getFlagsField(genFeature));
    stringBuffer.append(TEXT_1709);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_1710);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_1711);
    }
    } else {
    stringBuffer.append(TEXT_1712);
    stringBuffer.append(safeNameAccessor);
    stringBuffer.append(TEXT_1713);
    stringBuffer.append(genFeature.getEDefault());
    stringBuffer.append(TEXT_1714);
    }
    } else {
    if (genFeature.isEnumType() && genFeature.isField() && genClass.getImplementingGenModel(genFeature).isVirtualDelegation()) {
    stringBuffer.append(TEXT_1715);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(positiveOffsetCorrection);
    stringBuffer.append(TEXT_1716);
    stringBuffer.append(genFeature.getEDefault());
    stringBuffer.append(TEXT_1717);
    stringBuffer.append(genFeature.getEDefault());
    stringBuffer.append(TEXT_1718);
    } else {
    stringBuffer.append(TEXT_1719);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_1720);
    stringBuffer.append(genFeature.getEDefault());
    stringBuffer.append(TEXT_1721);
    }
    }
    } else {//datatype
    if (genClass.isField(genFeature)) {
    stringBuffer.append(TEXT_1722);
    stringBuffer.append(genFeature.getEDefault());
    stringBuffer.append(TEXT_1723);
    stringBuffer.append(safeNameAccessor);
    stringBuffer.append(TEXT_1724);
    stringBuffer.append(genFeature.getEDefault());
    stringBuffer.append(TEXT_1725);
    stringBuffer.append(safeNameAccessor);
    stringBuffer.append(TEXT_1726);
    } else {
    if (genFeature.isField() && genClass.getImplementingGenModel(genFeature).isVirtualDelegation()) {
    stringBuffer.append(TEXT_1727);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_1728);
    stringBuffer.append(safeNameAccessor);
    stringBuffer.append(TEXT_1729);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_1730);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(positiveOffsetCorrection);
    stringBuffer.append(TEXT_1731);
    stringBuffer.append(genFeature.getEDefault());
    stringBuffer.append(TEXT_1732);
    stringBuffer.append(genFeature.getEDefault());
    stringBuffer.append(TEXT_1733);
    stringBuffer.append(safeNameAccessor);
    stringBuffer.append(TEXT_1734);
    stringBuffer.append(genFeature.getEDefault());
    stringBuffer.append(TEXT_1735);
    stringBuffer.append(safeNameAccessor);
    stringBuffer.append(TEXT_1736);
    } else {
    stringBuffer.append(TEXT_1737);
    stringBuffer.append(genFeature.getEDefault());
    stringBuffer.append(TEXT_1738);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_1739);
    stringBuffer.append(genFeature.getEDefault());
    stringBuffer.append(TEXT_1740);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_1741);
    }
    }
    }
    }
    stringBuffer.append(TEXT_1742);
    if (genModel.isMinimalReflectiveMethods()) {
    stringBuffer.append(TEXT_1743);
    } else {
    stringBuffer.append(TEXT_1744);
    }
    stringBuffer.append(TEXT_1745);
    //Class/eIsSet.override.javajetinc
    }
    if (isImplementation && (!genClass.getMixinGenFeatures().isEmpty() || genClass.hasOffsetCorrection() && !genClass.getGenFeatures().isEmpty())) {
    if (!genClass.getMixinGenFeatures().isEmpty()) {
    stringBuffer.append(TEXT_1746);
    if (genModel.useClassOverrideAnnotation()) {
    stringBuffer.append(TEXT_1747);
    }
    stringBuffer.append(TEXT_1748);
    stringBuffer.append(singleWildcard);
    stringBuffer.append(TEXT_1749);
    for (GenClass mixinGenClass : genClass.getMixinGenClasses()) {
    stringBuffer.append(TEXT_1750);
    stringBuffer.append(mixinGenClass.getRawImportedInterfaceName());
    stringBuffer.append(TEXT_1751);
    stringBuffer.append(negativeOffsetCorrection);
    stringBuffer.append(TEXT_1752);
    for (GenFeature genFeature : mixinGenClass.getGenFeatures()) {
    stringBuffer.append(TEXT_1753);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(TEXT_1754);
    stringBuffer.append(mixinGenClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(TEXT_1755);
    }
    stringBuffer.append(TEXT_1756);
    }
    stringBuffer.append(TEXT_1757);
    }
    stringBuffer.append(TEXT_1758);
    if (genModel.useClassOverrideAnnotation()) {
    stringBuffer.append(TEXT_1759);
    }
    stringBuffer.append(TEXT_1760);
    stringBuffer.append(singleWildcard);
    stringBuffer.append(TEXT_1761);
    for (GenClass mixinGenClass : genClass.getMixinGenClasses()) {
    stringBuffer.append(TEXT_1762);
    stringBuffer.append(mixinGenClass.getRawImportedInterfaceName());
    stringBuffer.append(TEXT_1763);
    for (GenFeature genFeature : mixinGenClass.getGenFeatures()) {
    stringBuffer.append(TEXT_1764);
    stringBuffer.append(mixinGenClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(TEXT_1765);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(positiveOffsetCorrection);
    stringBuffer.append(TEXT_1766);
    }
    stringBuffer.append(TEXT_1767);
    }
    if (genClass.hasOffsetCorrection() && !genClass.getGenFeatures().isEmpty()) {
    stringBuffer.append(TEXT_1768);
    stringBuffer.append(genClass.getRawImportedInterfaceName());
    stringBuffer.append(TEXT_1769);
    stringBuffer.append(negativeOffsetCorrection);
    stringBuffer.append(TEXT_1770);
    for (GenFeature genFeature : genClass.getGenFeatures()) {
    stringBuffer.append(TEXT_1771);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(TEXT_1772);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(positiveOffsetCorrection);
    stringBuffer.append(TEXT_1773);
    }
    stringBuffer.append(TEXT_1774);
    }
    stringBuffer.append(TEXT_1775);
    }
    if (genModel.isOperationReflection() && isImplementation && (!genClass.getMixinGenOperations().isEmpty() || !genClass.getOverrideGenOperations(genClass.getExtendedGenOperations(), genClass.getImplementedGenOperations()).isEmpty() || genClass.hasOffsetCorrection() && !genClass.getGenOperations().isEmpty())) {
    stringBuffer.append(TEXT_1776);
    if (genModel.useClassOverrideAnnotation()) {
    stringBuffer.append(TEXT_1777);
    }
    stringBuffer.append(TEXT_1778);
    stringBuffer.append(singleWildcard);
    stringBuffer.append(TEXT_1779);
    for (GenClass extendedGenClass : genClass.getExtendedGenClasses()) { List<GenOperation> extendedImplementedGenOperations = extendedGenClass.getImplementedGenOperations(); List<GenOperation> implementedGenOperations = genClass.getImplementedGenOperations();
    if (!genClass.getOverrideGenOperations(extendedImplementedGenOperations, implementedGenOperations).isEmpty()) {
    stringBuffer.append(TEXT_1780);
    stringBuffer.append(extendedGenClass.getRawImportedInterfaceName());
    stringBuffer.append(TEXT_1781);
    for (GenOperation genOperation : extendedImplementedGenOperations) { GenOperation overrideGenOperation = genClass.getOverrideGenOperation(genOperation);
    if (implementedGenOperations.contains(overrideGenOperation)) {
    stringBuffer.append(TEXT_1782);
    stringBuffer.append(extendedGenClass.getQualifiedOperationID(genOperation));
    stringBuffer.append(TEXT_1783);
    stringBuffer.append(genClass.getQualifiedOperationID(overrideGenOperation));
    stringBuffer.append(positiveOperationOffsetCorrection);
    stringBuffer.append(TEXT_1784);
    }
    }
    stringBuffer.append(TEXT_1785);
    }
    }
    for (GenClass mixinGenClass : genClass.getMixinGenClasses()) {
    stringBuffer.append(TEXT_1786);
    stringBuffer.append(mixinGenClass.getRawImportedInterfaceName());
    stringBuffer.append(TEXT_1787);
    for (GenOperation genOperation : mixinGenClass.getGenOperations()) { GenOperation overrideGenOperation = genClass.getOverrideGenOperation(genOperation);
    stringBuffer.append(TEXT_1788);
    stringBuffer.append(mixinGenClass.getQualifiedOperationID(genOperation));
    stringBuffer.append(TEXT_1789);
    stringBuffer.append(genClass.getQualifiedOperationID(overrideGenOperation != null ? overrideGenOperation : genOperation));
    stringBuffer.append(positiveOperationOffsetCorrection);
    stringBuffer.append(TEXT_1790);
    }
    stringBuffer.append(TEXT_1791);
    }
    if (genClass.hasOffsetCorrection() && !genClass.getGenOperations().isEmpty()) {
    stringBuffer.append(TEXT_1792);
    stringBuffer.append(genClass.getRawImportedInterfaceName());
    stringBuffer.append(TEXT_1793);
    stringBuffer.append(negativeOperationOffsetCorrection);
    stringBuffer.append(TEXT_1794);
    for (GenOperation genOperation : genClass.getGenOperations()) {
    stringBuffer.append(TEXT_1795);
    stringBuffer.append(genClass.getQualifiedOperationID(genOperation));
    stringBuffer.append(TEXT_1796);
    stringBuffer.append(genClass.getQualifiedOperationID(genOperation));
    stringBuffer.append(positiveOperationOffsetCorrection);
    stringBuffer.append(TEXT_1797);
    }
    stringBuffer.append(TEXT_1798);
    }
    stringBuffer.append(TEXT_1799);
    }
    if (isImplementation && genModel.isVirtualDelegation()) { String eVirtualValuesField = genClass.getEVirtualValuesField();
    if (eVirtualValuesField != null) {
    stringBuffer.append(TEXT_1800);
    if (genModel.useClassOverrideAnnotation()) {
    stringBuffer.append(TEXT_1801);
    }
    stringBuffer.append(TEXT_1802);
    stringBuffer.append(eVirtualValuesField);
    stringBuffer.append(TEXT_1803);
    if (genModel.useClassOverrideAnnotation()) {
    stringBuffer.append(TEXT_1804);
    }
    stringBuffer.append(TEXT_1805);
    stringBuffer.append(eVirtualValuesField);
    stringBuffer.append(TEXT_1806);
    }
    { List<String> eVirtualIndexBitFields = genClass.getEVirtualIndexBitFields(new ArrayList<String>());
    if (!eVirtualIndexBitFields.isEmpty()) { List<String> allEVirtualIndexBitFields = genClass.getAllEVirtualIndexBitFields(new ArrayList<String>());
    stringBuffer.append(TEXT_1807);
    if (genModel.useClassOverrideAnnotation()) {
    stringBuffer.append(TEXT_1808);
    }
    stringBuffer.append(TEXT_1809);
    for (int i = 0; i < allEVirtualIndexBitFields.size(); i++) {
    stringBuffer.append(TEXT_1810);
    stringBuffer.append(i);
    stringBuffer.append(TEXT_1811);
    stringBuffer.append(allEVirtualIndexBitFields.get(i));
    stringBuffer.append(TEXT_1812);
    }
    stringBuffer.append(TEXT_1813);
    if (genModel.useClassOverrideAnnotation()) {
    stringBuffer.append(TEXT_1814);
    }
    stringBuffer.append(TEXT_1815);
    for (int i = 0; i < allEVirtualIndexBitFields.size(); i++) {
    stringBuffer.append(TEXT_1816);
    stringBuffer.append(i);
    stringBuffer.append(TEXT_1817);
    stringBuffer.append(allEVirtualIndexBitFields.get(i));
    stringBuffer.append(TEXT_1818);
    }
    stringBuffer.append(TEXT_1819);
    }
    }
    }
    if (genModel.isOperationReflection() && isImplementation && !genClass.getImplementedGenOperations().isEmpty()) {
    stringBuffer.append(TEXT_1820);
    if (genModel.useClassOverrideAnnotation()) {
    stringBuffer.append(TEXT_1821);
    }
    if (genModel.useGenerics()) {
    boolean isUnchecked = false; boolean isRaw = false; LOOP: for (GenOperation genOperation : (genModel.isMinimalReflectiveMethods() ? genClass.getImplementedGenOperations() : genClass.getAllGenOperations())) { for (GenParameter genParameter : genOperation.getGenParameters()) { if (genParameter.isUncheckedCast()) { if (genParameter.getTypeGenDataType() == null || !genParameter.getTypeGenDataType().isObjectType()) { isUnchecked = true; } if (genParameter.usesOperationTypeParameters() && !genParameter.getEcoreParameter().getEGenericType().getETypeArguments().isEmpty()) { isRaw = true; break LOOP; }}}}
    if (isUnchecked) {
    stringBuffer.append(TEXT_1822);
    if (!isRaw) {
    stringBuffer.append(TEXT_1823);
    } else {
    stringBuffer.append(TEXT_1824);
    }
    stringBuffer.append(TEXT_1825);
    }
    }
    stringBuffer.append(TEXT_1826);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.util.EList"));
    stringBuffer.append(singleWildcard);
    stringBuffer.append(TEXT_1827);
    stringBuffer.append(genModel.getImportedName(isGWT ? "org.eclipse.emf.common.util.InvocationTargetException" : "java.lang.reflect.InvocationTargetException"));
    stringBuffer.append(TEXT_1828);
    stringBuffer.append(negativeOperationOffsetCorrection);
    stringBuffer.append(TEXT_1829);
    for (GenOperation genOperation : (genModel.isMinimalReflectiveMethods() ? genClass.getImplementedGenOperations() : genClass.getAllGenOperations())) { List<GenParameter> genParameters = genOperation.getGenParameters(); int size = genParameters.size();  boolean hasCheckedException = genOperation.hasCheckedException(); String indent = hasCheckedException ? "\t" : ""; GenOperation overrideGenOperation = genClass.getOverrideGenOperation(genOperation);
    stringBuffer.append(TEXT_1830);
    stringBuffer.append(genClass.getQualifiedOperationID(overrideGenOperation != null ? overrideGenOperation : genOperation));
    stringBuffer.append(TEXT_1831);
    if (hasCheckedException) {
    stringBuffer.append(TEXT_1832);
    /*}*/}
    if (genOperation.isVoid()) {
    stringBuffer.append(TEXT_1833);
    stringBuffer.append(indent);
    stringBuffer.append(genOperation.getName());
    stringBuffer.append(TEXT_1834);
    for (int i = 0; i < size; i++) { GenParameter genParameter = genParameters.get(i);
    if (!isJDK50 && genParameter.isPrimitiveType()) {
    stringBuffer.append(TEXT_1835);
    }
    if (genParameter.getTypeGenDataType() == null || !genParameter.getTypeGenDataType().isObjectType() || !genParameter.usesOperationTypeParameters() && !genParameter.getRawType().equals(genParameter.getType(genClass))) {
    stringBuffer.append(TEXT_1836);
    stringBuffer.append(genParameter.usesOperationTypeParameters() ? genParameter.getRawImportedType() : genParameter.getObjectType(genClass));
    stringBuffer.append(TEXT_1837);
    }
    stringBuffer.append(TEXT_1838);
    stringBuffer.append(i);
    stringBuffer.append(TEXT_1839);
    if (!isJDK50 && genParameter.isPrimitiveType()) {
    stringBuffer.append(TEXT_1840);
    stringBuffer.append(genParameter.getPrimitiveValueFunction());
    stringBuffer.append(TEXT_1841);
    }
    if (i < (size - 1)) {
    stringBuffer.append(TEXT_1842);
    }
    }
    stringBuffer.append(TEXT_1843);
    stringBuffer.append(indent);
    stringBuffer.append(TEXT_1844);
    } else {
    stringBuffer.append(TEXT_1845);
    stringBuffer.append(indent);
    stringBuffer.append(TEXT_1846);
    if (!isJDK50 && genOperation.isPrimitiveType()) {
    stringBuffer.append(TEXT_1847);
    stringBuffer.append(genOperation.getObjectType(genClass));
    stringBuffer.append(TEXT_1848);
    }
    stringBuffer.append(genOperation.getName());
    stringBuffer.append(TEXT_1849);
    for (int i = 0; i < size; i++) { GenParameter genParameter = genParameters.get(i);
    if (!isJDK50 && genParameter.isPrimitiveType()) {
    stringBuffer.append(TEXT_1850);
    }
    if (genParameter.getTypeGenDataType() == null || !genParameter.getTypeGenDataType().isObjectType() || !genParameter.usesOperationTypeParameters() && !genParameter.getRawType().equals(genParameter.getType(genClass))) {
    stringBuffer.append(TEXT_1851);
    stringBuffer.append(genParameter.usesOperationTypeParameters() ? genParameter.getRawImportedType() : genParameter.getObjectType(genClass));
    stringBuffer.append(TEXT_1852);
    }
    stringBuffer.append(TEXT_1853);
    stringBuffer.append(i);
    stringBuffer.append(TEXT_1854);
    if (!isJDK50 && genParameter.isPrimitiveType()) {
    stringBuffer.append(TEXT_1855);
    stringBuffer.append(genParameter.getPrimitiveValueFunction());
    stringBuffer.append(TEXT_1856);
    }
    if (i < (size - 1)) {
    stringBuffer.append(TEXT_1857);
    }
    }
    stringBuffer.append(TEXT_1858);
    if (!isJDK50 && genOperation.isPrimitiveType()) {
    stringBuffer.append(TEXT_1859);
    }
    stringBuffer.append(TEXT_1860);
    }
    if (hasCheckedException) {/*{*/
    stringBuffer.append(TEXT_1861);
    stringBuffer.append(genModel.getImportedName("java.lang.Throwable"));
    stringBuffer.append(TEXT_1862);
    stringBuffer.append(genModel.getImportedName(isGWT ? "org.eclipse.emf.common.util.InvocationTargetException" : "java.lang.reflect.InvocationTargetException"));
    stringBuffer.append(TEXT_1863);
    }
    }
    stringBuffer.append(TEXT_1864);
    if (genModel.isMinimalReflectiveMethods()) {
    stringBuffer.append(TEXT_1865);
    } else {
    stringBuffer.append(TEXT_1866);
    }
    stringBuffer.append(TEXT_1867);
    }
    if (!genClass.hasImplementedToStringGenOperation() && isImplementation && !genModel.isReflectiveDelegation() && !genModel.isDynamicDelegation() && !genClass.getToStringGenFeatures().isEmpty()) {
    stringBuffer.append(TEXT_1868);
    if (genModel.useClassOverrideAnnotation()) {
    stringBuffer.append(TEXT_1869);
    }
    stringBuffer.append(TEXT_1870);
    stringBuffer.append(genModel.useGenerics() ? "StringBuilder" : "StringBuffer");
    stringBuffer.append(TEXT_1871);
    stringBuffer.append(genModel.useGenerics() ? "StringBuilder" : "StringBuffer");
    stringBuffer.append(TEXT_1872);
    { boolean first = true;
    for (GenFeature genFeature : genClass.getToStringGenFeatures()) {
    if (first) { first = false;
    stringBuffer.append(TEXT_1873);
    stringBuffer.append(genFeature.getName());
    stringBuffer.append(TEXT_1874);
    stringBuffer.append(genModel.getNonNLS());
    } else {
    stringBuffer.append(TEXT_1875);
    stringBuffer.append(genFeature.getName());
    stringBuffer.append(TEXT_1876);
    stringBuffer.append(genModel.getNonNLS());
    }
    if (genFeature.isUnsettable() && !genFeature.isListType()) {
    if (genModel.isVirtualDelegation() && !genFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_1877);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(positiveOffsetCorrection);
    stringBuffer.append(TEXT_1878);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(positiveOffsetCorrection);
    stringBuffer.append(TEXT_1879);
    stringBuffer.append(genModel.getNonNLS());
    } else {
    if (genClass.isFlag(genFeature)) {
    if (genFeature.isBooleanType()) {
    stringBuffer.append(TEXT_1880);
    if (genClass.isESetFlag(genFeature)) {
    stringBuffer.append(TEXT_1881);
    stringBuffer.append(genClass.getESetFlagsField(genFeature));
    stringBuffer.append(TEXT_1882);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_1883);
    } else {
    stringBuffer.append(genFeature.getUncapName());
    stringBuffer.append(TEXT_1884);
    }
    stringBuffer.append(TEXT_1885);
    stringBuffer.append(genClass.getFlagsField(genFeature));
    stringBuffer.append(TEXT_1886);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_1887);
    stringBuffer.append(genModel.getNonNLS());
    } else {
    stringBuffer.append(TEXT_1888);
    if (genClass.isESetFlag(genFeature)) {
    stringBuffer.append(TEXT_1889);
    stringBuffer.append(genClass.getESetFlagsField(genFeature));
    stringBuffer.append(TEXT_1890);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_1891);
    } else {
    stringBuffer.append(genFeature.getUncapName());
    stringBuffer.append(TEXT_1892);
    }
    stringBuffer.append(TEXT_1893);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_1894);
    stringBuffer.append(genClass.getFlagsField(genFeature));
    stringBuffer.append(TEXT_1895);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_1896);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_1897);
    stringBuffer.append(genModel.getNonNLS());
    }
    } else {
    stringBuffer.append(TEXT_1898);
    if (genClass.isESetFlag(genFeature)) {
    stringBuffer.append(TEXT_1899);
    stringBuffer.append(genClass.getESetFlagsField(genFeature));
    stringBuffer.append(TEXT_1900);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_1901);
    } else {
    stringBuffer.append(genFeature.getUncapName());
    stringBuffer.append(TEXT_1902);
    }
    stringBuffer.append(TEXT_1903);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_1904);
    stringBuffer.append(genModel.getNonNLS());
    }
    }
    } else {
    if (genModel.isVirtualDelegation() && !genFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_1905);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(positiveOffsetCorrection);
    if (!genFeature.isListType() && !genFeature.isReferenceType()){
    stringBuffer.append(TEXT_1906);
    stringBuffer.append(genFeature.getEDefault());
    }
    stringBuffer.append(TEXT_1907);
    } else {
    if (genClass.isFlag(genFeature)) {
    if (genFeature.isBooleanType()) {
    stringBuffer.append(TEXT_1908);
    stringBuffer.append(genClass.getFlagsField(genFeature));
    stringBuffer.append(TEXT_1909);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_1910);
    } else {
    stringBuffer.append(TEXT_1911);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_1912);
    stringBuffer.append(genClass.getFlagsField(genFeature));
    stringBuffer.append(TEXT_1913);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_1914);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_1915);
    }
    } else {
    stringBuffer.append(TEXT_1916);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_1917);
    }
    }
    }
    }
    }
    stringBuffer.append(TEXT_1918);
    }
    if (isImplementation && genClass.isMapEntry()) { GenFeature keyFeature = genClass.getMapEntryKeyFeature(); GenFeature valueFeature = genClass.getMapEntryValueFeature();
    String objectType = genModel.getImportedName("java.lang.Object");
    String keyType = isJDK50 ? keyFeature.getObjectType(genClass) : objectType;
    String valueType = isJDK50 ? valueFeature.getObjectType(genClass) : objectType;
    String eMapType = genModel.getImportedName("org.eclipse.emf.common.util.EMap") + (isJDK50 ? "<" + keyType + ", " + valueType + ">" : "");
    stringBuffer.append(TEXT_1919);
    if (isGWT) {
    stringBuffer.append(TEXT_1920);
    stringBuffer.append(genModel.getImportedName("com.google.gwt.user.client.rpc.GwtTransient"));
    }
    stringBuffer.append(TEXT_1921);
    stringBuffer.append(objectType);
    stringBuffer.append(TEXT_1922);
    stringBuffer.append(keyType);
    stringBuffer.append(TEXT_1923);
    if (!isJDK50 && keyFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_1924);
    stringBuffer.append(keyFeature.getObjectType(genClass));
    stringBuffer.append(TEXT_1925);
    } else {
    stringBuffer.append(TEXT_1926);
    }
    stringBuffer.append(TEXT_1927);
    stringBuffer.append(keyType);
    stringBuffer.append(TEXT_1928);
    if (keyFeature.isListType()) {
    stringBuffer.append(TEXT_1929);
    if (!genModel.useGenerics()) {
    stringBuffer.append(TEXT_1930);
    stringBuffer.append(genModel.getImportedName("java.util.Collection"));
    stringBuffer.append(TEXT_1931);
    }
    stringBuffer.append(TEXT_1932);
    } else if (isJDK50) {
    stringBuffer.append(TEXT_1933);
    } else if (keyFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_1934);
    stringBuffer.append(keyFeature.getObjectType(genClass));
    stringBuffer.append(TEXT_1935);
    stringBuffer.append(keyFeature.getPrimitiveValueFunction());
    stringBuffer.append(TEXT_1936);
    } else {
    stringBuffer.append(TEXT_1937);
    stringBuffer.append(keyFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_1938);
    }
    stringBuffer.append(TEXT_1939);
    stringBuffer.append(valueType);
    stringBuffer.append(TEXT_1940);
    if (!isJDK50 && valueFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_1941);
    stringBuffer.append(valueFeature.getObjectType(genClass));
    stringBuffer.append(TEXT_1942);
    } else {
    stringBuffer.append(TEXT_1943);
    }
    stringBuffer.append(TEXT_1944);
    stringBuffer.append(valueType);
    stringBuffer.append(TEXT_1945);
    stringBuffer.append(valueType);
    stringBuffer.append(TEXT_1946);
    stringBuffer.append(valueType);
    stringBuffer.append(TEXT_1947);
    if (valueFeature.isListType()) {
    stringBuffer.append(TEXT_1948);
    if (!genModel.useGenerics()) {
    stringBuffer.append(TEXT_1949);
    stringBuffer.append(genModel.getImportedName("java.util.Collection"));
    stringBuffer.append(TEXT_1950);
    }
    stringBuffer.append(TEXT_1951);
    } else if (isJDK50) {
    stringBuffer.append(TEXT_1952);
    } else if (valueFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_1953);
    stringBuffer.append(valueFeature.getObjectType(genClass));
    stringBuffer.append(TEXT_1954);
    stringBuffer.append(valueFeature.getPrimitiveValueFunction());
    stringBuffer.append(TEXT_1955);
    } else {
    stringBuffer.append(TEXT_1956);
    stringBuffer.append(valueFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_1957);
    }
    stringBuffer.append(TEXT_1958);
    if (genModel.useGenerics()) {
    stringBuffer.append(TEXT_1959);
    }
    stringBuffer.append(TEXT_1960);
    stringBuffer.append(eMapType);
    stringBuffer.append(TEXT_1961);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.EObject"));
    stringBuffer.append(TEXT_1962);
    stringBuffer.append(eMapType);
    stringBuffer.append(TEXT_1963);
    }
    stringBuffer.append(TEXT_1964);
    stringBuffer.append(isInterface ? " " + genClass.getInterfaceName() : genClass.getClassName());
    // TODO fix the space above
    genModel.emitSortedImports();
    stringBuffer.append(TEXT_1965);
    return stringBuffer.toString();
  }
}
