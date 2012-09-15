package org.eclipse.emf.codegen.ecore.templates.model;

import java.util.*;
import org.eclipse.emf.codegen.ecore.genmodel.*;
import org.eclipse.emf.codegen.util.CodeGenUtil;

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
  protected final String TEXT_46 = " copyright = ";
  protected final String TEXT_47 = ";";
  protected final String TEXT_48 = NL;
  protected final String TEXT_49 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */" + NL + "\tpublic static final ";
  protected final String TEXT_50 = " mofDriverNumber = \"";
  protected final String TEXT_51 = "\";";
  protected final String TEXT_52 = NL;
  protected final String TEXT_53 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */" + NL + "\tprivate static final long serialVersionUID = 1L;" + NL;
  protected final String TEXT_54 = NL + "\t/**" + NL + "\t * An array of objects representing the values of non-primitive features." + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */";
  protected final String TEXT_55 = NL + "\t@";
  protected final String TEXT_56 = NL + "\tprotected Object[] ";
  protected final String TEXT_57 = ";" + NL;
  protected final String TEXT_58 = NL + "\t/**" + NL + "\t * A bit field representing the indices of non-primitive feature values." + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */";
  protected final String TEXT_59 = NL + "\t@";
  protected final String TEXT_60 = NL + "\tprotected int ";
  protected final String TEXT_61 = ";" + NL;
  protected final String TEXT_62 = NL + "\t/**" + NL + "\t * A set of bit flags representing the values of boolean attributes and whether unsettable features have been set." + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t * @ordered" + NL + "\t */";
  protected final String TEXT_63 = NL + "\t@";
  protected final String TEXT_64 = NL + "\tprotected int ";
  protected final String TEXT_65 = " = 0;" + NL;
  protected final String TEXT_66 = NL + "\t/**" + NL + "\t * The cached setting delegate for the '{@link #";
  protected final String TEXT_67 = "() <em>";
  protected final String TEXT_68 = "</em>}' ";
  protected final String TEXT_69 = "." + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @see #";
  protected final String TEXT_70 = "()" + NL + "\t * @generated" + NL + "\t * @ordered" + NL + "\t */";
  protected final String TEXT_71 = NL + "\t@";
  protected final String TEXT_72 = NL + "\tprotected ";
  protected final String TEXT_73 = ".Internal.SettingDelegate ";
  protected final String TEXT_74 = "__ESETTING_DELEGATE = ((";
  protected final String TEXT_75 = ".Internal)";
  protected final String TEXT_76 = ").getSettingDelegate();" + NL;
  protected final String TEXT_77 = NL + "\t/**" + NL + "\t * The cached value of the '{@link #";
  protected final String TEXT_78 = "() <em>";
  protected final String TEXT_79 = "</em>}' ";
  protected final String TEXT_80 = "." + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @see #";
  protected final String TEXT_81 = "()" + NL + "\t * @generated" + NL + "\t * @ordered" + NL + "\t */";
  protected final String TEXT_82 = NL + "\t@";
  protected final String TEXT_83 = NL + "\tprotected ";
  protected final String TEXT_84 = " ";
  protected final String TEXT_85 = ";" + NL;
  protected final String TEXT_86 = NL + "\t/**" + NL + "\t * The empty value for the '{@link #";
  protected final String TEXT_87 = "() <em>";
  protected final String TEXT_88 = "</em>}' array accessor." + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @see #";
  protected final String TEXT_89 = "()" + NL + "\t * @generated" + NL + "\t * @ordered" + NL + "\t */";
  protected final String TEXT_90 = NL + "\t@SuppressWarnings(\"unchecked\")";
  protected final String TEXT_91 = NL + "\tprotected static final ";
  protected final String TEXT_92 = "[] ";
  protected final String TEXT_93 = "_EEMPTY_ARRAY = new ";
  protected final String TEXT_94 = " [0]";
  protected final String TEXT_95 = ";" + NL;
  protected final String TEXT_96 = NL + "\t/**" + NL + "\t * The default value of the '{@link #";
  protected final String TEXT_97 = "() <em>";
  protected final String TEXT_98 = "</em>}' ";
  protected final String TEXT_99 = "." + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @see #";
  protected final String TEXT_100 = "()" + NL + "\t * @generated" + NL + "\t * @ordered" + NL + "\t */";
  protected final String TEXT_101 = NL + "\t@SuppressWarnings(\"unchecked\")";
  protected final String TEXT_102 = NL + "\tprotected static final ";
  protected final String TEXT_103 = " ";
  protected final String TEXT_104 = "; // TODO The default value literal \"";
  protected final String TEXT_105 = "\" is not valid.";
  protected final String TEXT_106 = " = ";
  protected final String TEXT_107 = ";";
  protected final String TEXT_108 = NL;
  protected final String TEXT_109 = NL + "\t/**" + NL + "\t * An additional set of bit flags representing the values of boolean attributes and whether unsettable features have been set." + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t * @ordered" + NL + "\t */";
  protected final String TEXT_110 = NL + "\t@";
  protected final String TEXT_111 = NL + "\tprotected int ";
  protected final String TEXT_112 = " = 0;" + NL;
  protected final String TEXT_113 = NL + "\t/**" + NL + "\t * The offset of the flags representing the value of the '{@link #";
  protected final String TEXT_114 = "() <em>";
  protected final String TEXT_115 = "</em>}' ";
  protected final String TEXT_116 = "." + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t * @ordered" + NL + "\t */" + NL + "\tprotected static final int ";
  protected final String TEXT_117 = "_EFLAG_OFFSET = ";
  protected final String TEXT_118 = ";" + NL + "" + NL + "\t/**" + NL + "\t * The flags representing the default value of the '{@link #";
  protected final String TEXT_119 = "() <em>";
  protected final String TEXT_120 = "</em>}' ";
  protected final String TEXT_121 = "." + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t * @ordered" + NL + "\t */" + NL + "\tprotected static final int ";
  protected final String TEXT_122 = "_EFLAG_DEFAULT = ";
  protected final String TEXT_123 = ".ordinal()";
  protected final String TEXT_124 = ".VALUES.indexOf(";
  protected final String TEXT_125 = ")";
  protected final String TEXT_126 = " << ";
  protected final String TEXT_127 = "_EFLAG_OFFSET;" + NL + "" + NL + "\t/**" + NL + "\t * The array of enumeration values for '{@link ";
  protected final String TEXT_128 = " ";
  protected final String TEXT_129 = "}'" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t * @ordered" + NL + "\t */" + NL + "\tprivate static final ";
  protected final String TEXT_130 = "[] ";
  protected final String TEXT_131 = "_EFLAG_VALUES = ";
  protected final String TEXT_132 = ".values()";
  protected final String TEXT_133 = "(";
  protected final String TEXT_134 = "[])";
  protected final String TEXT_135 = ".VALUES.toArray(new ";
  protected final String TEXT_136 = "[";
  protected final String TEXT_137 = ".VALUES.size()])";
  protected final String TEXT_138 = ";" + NL;
  protected final String TEXT_139 = NL + "\t/**" + NL + "\t * The flag";
  protected final String TEXT_140 = " representing the value of the '{@link #";
  protected final String TEXT_141 = "() <em>";
  protected final String TEXT_142 = "</em>}' ";
  protected final String TEXT_143 = "." + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @see #";
  protected final String TEXT_144 = "()" + NL + "\t * @generated" + NL + "\t * @ordered" + NL + "\t */" + NL + "\tprotected static final int ";
  protected final String TEXT_145 = "_EFLAG = ";
  protected final String TEXT_146 = " << ";
  protected final String TEXT_147 = "_EFLAG_OFFSET";
  protected final String TEXT_148 = ";" + NL;
  protected final String TEXT_149 = NL + "\t/**" + NL + "\t * The cached value of the '{@link #";
  protected final String TEXT_150 = "() <em>";
  protected final String TEXT_151 = "</em>}' ";
  protected final String TEXT_152 = "." + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @see #";
  protected final String TEXT_153 = "()" + NL + "\t * @generated" + NL + "\t * @ordered" + NL + "\t */";
  protected final String TEXT_154 = NL + "\t@";
  protected final String TEXT_155 = NL + "\tprotected ";
  protected final String TEXT_156 = " ";
  protected final String TEXT_157 = " = ";
  protected final String TEXT_158 = ";" + NL;
  protected final String TEXT_159 = NL + "\t/**" + NL + "\t * An additional set of bit flags representing the values of boolean attributes and whether unsettable features have been set." + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t * @ordered" + NL + "\t */";
  protected final String TEXT_160 = NL + "\t@";
  protected final String TEXT_161 = NL + "\tprotected int ";
  protected final String TEXT_162 = " = 0;" + NL;
  protected final String TEXT_163 = NL + "\t/**" + NL + "\t * The flag representing whether the ";
  protected final String TEXT_164 = " ";
  protected final String TEXT_165 = " has been set." + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t * @ordered" + NL + "\t */" + NL + "\tprotected static final int ";
  protected final String TEXT_166 = "_ESETFLAG = 1 << ";
  protected final String TEXT_167 = ";" + NL;
  protected final String TEXT_168 = NL + "\t/**" + NL + "\t * This is true if the ";
  protected final String TEXT_169 = " ";
  protected final String TEXT_170 = " has been set." + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t * @ordered" + NL + "\t */";
  protected final String TEXT_171 = NL + "\t@";
  protected final String TEXT_172 = NL + "\tprotected boolean ";
  protected final String TEXT_173 = "ESet;" + NL;
  protected final String TEXT_174 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */" + NL + "\tprivate static final int ";
  protected final String TEXT_175 = " = ";
  protected final String TEXT_176 = ".getFeatureID(";
  protected final String TEXT_177 = ") - ";
  protected final String TEXT_178 = ";" + NL;
  protected final String TEXT_179 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */" + NL + "\tprivate static final int ";
  protected final String TEXT_180 = " = ";
  protected final String TEXT_181 = ".getFeatureID(";
  protected final String TEXT_182 = ") - ";
  protected final String TEXT_183 = ";" + NL;
  protected final String TEXT_184 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */" + NL + "\tprivate static final int \"EOPERATION_OFFSET_CORRECTION\" = ";
  protected final String TEXT_185 = ".getOperationID(";
  protected final String TEXT_186 = ") - ";
  protected final String TEXT_187 = ";" + NL;
  protected final String TEXT_188 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */" + NL + "\t";
  protected final String TEXT_189 = "public";
  protected final String TEXT_190 = "protected";
  protected final String TEXT_191 = " ";
  protected final String TEXT_192 = "()" + NL + "\t{" + NL + "\t\tsuper();";
  protected final String TEXT_193 = NL + "\t\t";
  protected final String TEXT_194 = " |= ";
  protected final String TEXT_195 = "_EFLAG";
  protected final String TEXT_196 = "_DEFAULT";
  protected final String TEXT_197 = ";";
  protected final String TEXT_198 = NL + "\t}" + NL + "" + NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */";
  protected final String TEXT_199 = NL + "\t@Override";
  protected final String TEXT_200 = NL + "\tprotected ";
  protected final String TEXT_201 = " eStaticClass()" + NL + "\t{" + NL + "\t\treturn ";
  protected final String TEXT_202 = ";" + NL + "\t}" + NL;
  protected final String TEXT_203 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */";
  protected final String TEXT_204 = NL + "\t@Override";
  protected final String TEXT_205 = NL + "\tprotected int eStaticFeatureCount()" + NL + "\t{" + NL + "\t\treturn ";
  protected final String TEXT_206 = ";" + NL + "\t}" + NL;
  protected final String TEXT_207 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */";
  protected final String TEXT_208 = NL + "\t";
  protected final String TEXT_209 = "[] ";
  protected final String TEXT_210 = "();" + NL;
  protected final String TEXT_211 = NL + "\tpublic ";
  protected final String TEXT_212 = "[] ";
  protected final String TEXT_213 = "()" + NL + "\t{";
  protected final String TEXT_214 = NL + "\t\t";
  protected final String TEXT_215 = " list = (";
  protected final String TEXT_216 = ")";
  protected final String TEXT_217 = "();" + NL + "\t\tif (list.isEmpty()) return ";
  protected final String TEXT_218 = "(";
  protected final String TEXT_219 = "[])";
  protected final String TEXT_220 = "_EEMPTY_ARRAY;";
  protected final String TEXT_221 = NL + "\t\tif (";
  protected final String TEXT_222 = " == null || ";
  protected final String TEXT_223 = ".isEmpty()) return ";
  protected final String TEXT_224 = "(";
  protected final String TEXT_225 = "[])";
  protected final String TEXT_226 = "_EEMPTY_ARRAY;" + NL + "\t\t";
  protected final String TEXT_227 = " list = (";
  protected final String TEXT_228 = ")";
  protected final String TEXT_229 = ";";
  protected final String TEXT_230 = NL + "\t\tlist.shrink();" + NL + "\t\treturn (";
  protected final String TEXT_231 = "[])list.data();" + NL + "\t}" + NL;
  protected final String TEXT_232 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */";
  protected final String TEXT_233 = NL + "\t";
  protected final String TEXT_234 = " get";
  protected final String TEXT_235 = "(int index);" + NL;
  protected final String TEXT_236 = NL + "\tpublic ";
  protected final String TEXT_237 = " get";
  protected final String TEXT_238 = "(int index)" + NL + "\t{" + NL + "\t\treturn ";
  protected final String TEXT_239 = "(";
  protected final String TEXT_240 = ")";
  protected final String TEXT_241 = "().get(index);" + NL + "\t}" + NL;
  protected final String TEXT_242 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */";
  protected final String TEXT_243 = NL + "\tint get";
  protected final String TEXT_244 = "Length();" + NL;
  protected final String TEXT_245 = NL + "\tpublic int get";
  protected final String TEXT_246 = "Length()" + NL + "\t{";
  protected final String TEXT_247 = NL + "\t\treturn ";
  protected final String TEXT_248 = "().size();";
  protected final String TEXT_249 = NL + "\t\treturn ";
  protected final String TEXT_250 = " == null ? 0 : ";
  protected final String TEXT_251 = ".size();";
  protected final String TEXT_252 = NL + "\t}" + NL;
  protected final String TEXT_253 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */";
  protected final String TEXT_254 = NL + "\tvoid set";
  protected final String TEXT_255 = "(";
  protected final String TEXT_256 = "[] new";
  protected final String TEXT_257 = ");" + NL;
  protected final String TEXT_258 = NL + "\tpublic void set";
  protected final String TEXT_259 = "(";
  protected final String TEXT_260 = "[] new";
  protected final String TEXT_261 = ")" + NL + "\t{" + NL + "\t\t((";
  protected final String TEXT_262 = ")";
  protected final String TEXT_263 = "()).setData(new";
  protected final String TEXT_264 = ".length, new";
  protected final String TEXT_265 = ");" + NL + "\t}" + NL;
  protected final String TEXT_266 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */";
  protected final String TEXT_267 = NL + "\tvoid set";
  protected final String TEXT_268 = "(int index, ";
  protected final String TEXT_269 = " element);" + NL;
  protected final String TEXT_270 = NL + "\tpublic void set";
  protected final String TEXT_271 = "(int index, ";
  protected final String TEXT_272 = " element)" + NL + "\t{" + NL + "\t\t";
  protected final String TEXT_273 = "().set(index, element);" + NL + "\t}" + NL;
  protected final String TEXT_274 = NL + "\t/**" + NL + "\t * Returns the value of the '<em><b>";
  protected final String TEXT_275 = "</b></em>' ";
  protected final String TEXT_276 = ".";
  protected final String TEXT_277 = NL + "\t * The key is of type ";
  protected final String TEXT_278 = "list of {@link ";
  protected final String TEXT_279 = "}";
  protected final String TEXT_280 = "{@link ";
  protected final String TEXT_281 = "}";
  protected final String TEXT_282 = "," + NL + "\t * and the value is of type ";
  protected final String TEXT_283 = "list of {@link ";
  protected final String TEXT_284 = "}";
  protected final String TEXT_285 = "{@link ";
  protected final String TEXT_286 = "}";
  protected final String TEXT_287 = ",";
  protected final String TEXT_288 = NL + "\t * The list contents are of type {@link ";
  protected final String TEXT_289 = "}";
  protected final String TEXT_290 = ".";
  protected final String TEXT_291 = NL + "\t * The default value is <code>";
  protected final String TEXT_292 = "</code>.";
  protected final String TEXT_293 = NL + "\t * The literals are from the enumeration {@link ";
  protected final String TEXT_294 = "}.";
  protected final String TEXT_295 = NL + "\t * It is bidirectional and its opposite is '{@link ";
  protected final String TEXT_296 = "#";
  protected final String TEXT_297 = " <em>";
  protected final String TEXT_298 = "</em>}'.";
  protected final String TEXT_299 = NL + "\t * <!-- begin-user-doc -->";
  protected final String TEXT_300 = NL + "\t * <p>" + NL + "\t * If the meaning of the '<em>";
  protected final String TEXT_301 = "</em>' ";
  protected final String TEXT_302 = " isn't clear," + NL + "\t * there really should be more of a description here..." + NL + "\t * </p>";
  protected final String TEXT_303 = NL + "\t * <!-- end-user-doc -->";
  protected final String TEXT_304 = NL + "\t * <!-- begin-model-doc -->" + NL + "\t * ";
  protected final String TEXT_305 = NL + "\t * <!-- end-model-doc -->";
  protected final String TEXT_306 = NL + "\t * @return the value of the '<em>";
  protected final String TEXT_307 = "</em>' ";
  protected final String TEXT_308 = ".";
  protected final String TEXT_309 = NL + "\t * @see ";
  protected final String TEXT_310 = NL + "\t * @see #isSet";
  protected final String TEXT_311 = "()";
  protected final String TEXT_312 = NL + "\t * @see #unset";
  protected final String TEXT_313 = "()";
  protected final String TEXT_314 = NL + "\t * @see #set";
  protected final String TEXT_315 = "(";
  protected final String TEXT_316 = ")";
  protected final String TEXT_317 = NL + "\t * @see ";
  protected final String TEXT_318 = "#get";
  protected final String TEXT_319 = "()";
  protected final String TEXT_320 = NL + "\t * @see ";
  protected final String TEXT_321 = "#";
  protected final String TEXT_322 = NL + "\t * @model ";
  protected final String TEXT_323 = NL + "\t *        ";
  protected final String TEXT_324 = NL + "\t * @model";
  protected final String TEXT_325 = NL + "\t * @generated" + NL + "\t */";
  protected final String TEXT_326 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */";
  protected final String TEXT_327 = NL + "\t";
  protected final String TEXT_328 = " ";
  protected final String TEXT_329 = "();" + NL;
  protected final String TEXT_330 = NL + "\t@SuppressWarnings(\"unchecked\")";
  protected final String TEXT_331 = NL + "\tpublic ";
  protected final String TEXT_332 = " ";
  protected final String TEXT_333 = "_";
  protected final String TEXT_334 = "()" + NL + "\t{";
  protected final String TEXT_335 = NL + "\t\treturn ";
  protected final String TEXT_336 = "(";
  protected final String TEXT_337 = "(";
  protected final String TEXT_338 = ")eDynamicGet(";
  protected final String TEXT_339 = ", ";
  protected final String TEXT_340 = ", true, ";
  protected final String TEXT_341 = ")";
  protected final String TEXT_342 = ").";
  protected final String TEXT_343 = "()";
  protected final String TEXT_344 = ";";
  protected final String TEXT_345 = NL + "\t\treturn ";
  protected final String TEXT_346 = "(";
  protected final String TEXT_347 = "(";
  protected final String TEXT_348 = ")eGet(";
  protected final String TEXT_349 = ", true)";
  protected final String TEXT_350 = ").";
  protected final String TEXT_351 = "()";
  protected final String TEXT_352 = ";";
  protected final String TEXT_353 = NL + "\t\treturn ";
  protected final String TEXT_354 = "(";
  protected final String TEXT_355 = "(";
  protected final String TEXT_356 = ")";
  protected final String TEXT_357 = "__ESETTING_DELEGATE.dynamicGet(this, null, 0, true, false)";
  protected final String TEXT_358 = ").";
  protected final String TEXT_359 = "()";
  protected final String TEXT_360 = ";";
  protected final String TEXT_361 = NL + "\t\t";
  protected final String TEXT_362 = " ";
  protected final String TEXT_363 = " = (";
  protected final String TEXT_364 = ")eVirtualGet(";
  protected final String TEXT_365 = ");";
  protected final String TEXT_366 = NL + "\t\tif (";
  protected final String TEXT_367 = " == null)" + NL + "\t\t{";
  protected final String TEXT_368 = NL + "\t\t\teVirtualSet(";
  protected final String TEXT_369 = ", ";
  protected final String TEXT_370 = " = new ";
  protected final String TEXT_371 = ");";
  protected final String TEXT_372 = NL + "\t\t\t";
  protected final String TEXT_373 = " = new ";
  protected final String TEXT_374 = ";";
  protected final String TEXT_375 = NL + "\t\t}" + NL + "\t\treturn ";
  protected final String TEXT_376 = ";";
  protected final String TEXT_377 = NL + "\t\tif (eContainerFeatureID() != ";
  protected final String TEXT_378 = ") return null;" + NL + "\t\treturn (";
  protected final String TEXT_379 = ")";
  protected final String TEXT_380 = "eContainer";
  protected final String TEXT_381 = "eInternalContainer";
  protected final String TEXT_382 = "();";
  protected final String TEXT_383 = NL + "\t\t";
  protected final String TEXT_384 = " ";
  protected final String TEXT_385 = " = (";
  protected final String TEXT_386 = ")eVirtualGet(";
  protected final String TEXT_387 = ", ";
  protected final String TEXT_388 = ");";
  protected final String TEXT_389 = NL + "\t\tif (";
  protected final String TEXT_390 = " != null && ";
  protected final String TEXT_391 = ".eIsProxy())" + NL + "\t\t{" + NL + "\t\t\t";
  protected final String TEXT_392 = " old";
  protected final String TEXT_393 = " = (";
  protected final String TEXT_394 = ")";
  protected final String TEXT_395 = ";" + NL + "\t\t\t";
  protected final String TEXT_396 = " = ";
  protected final String TEXT_397 = "eResolveProxy(old";
  protected final String TEXT_398 = ");" + NL + "\t\t\tif (";
  protected final String TEXT_399 = " != old";
  protected final String TEXT_400 = ")" + NL + "\t\t\t{";
  protected final String TEXT_401 = NL + "\t\t\t\t";
  protected final String TEXT_402 = " new";
  protected final String TEXT_403 = " = (";
  protected final String TEXT_404 = ")";
  protected final String TEXT_405 = ";";
  protected final String TEXT_406 = NL + "\t\t\t\t";
  protected final String TEXT_407 = " msgs = old";
  protected final String TEXT_408 = ".eInverseRemove(this, EOPPOSITE_FEATURE_BASE - ";
  protected final String TEXT_409 = ", null, null);";
  protected final String TEXT_410 = NL + "\t\t\t\t";
  protected final String TEXT_411 = " msgs =  old";
  protected final String TEXT_412 = ".eInverseRemove(this, ";
  protected final String TEXT_413 = ", ";
  protected final String TEXT_414 = ".class, null);";
  protected final String TEXT_415 = NL + "\t\t\t\tif (new";
  protected final String TEXT_416 = ".eInternalContainer() == null)" + NL + "\t\t\t\t{";
  protected final String TEXT_417 = NL + "\t\t\t\t\tmsgs = new";
  protected final String TEXT_418 = ".eInverseAdd(this, EOPPOSITE_FEATURE_BASE - ";
  protected final String TEXT_419 = ", null, msgs);";
  protected final String TEXT_420 = NL + "\t\t\t\t\tmsgs =  new";
  protected final String TEXT_421 = ".eInverseAdd(this, ";
  protected final String TEXT_422 = ", ";
  protected final String TEXT_423 = ".class, msgs);";
  protected final String TEXT_424 = NL + "\t\t\t\t}" + NL + "\t\t\t\tif (msgs != null) msgs.dispatch();";
  protected final String TEXT_425 = NL + "\t\t\t\teVirtualSet(";
  protected final String TEXT_426 = ", ";
  protected final String TEXT_427 = ");";
  protected final String TEXT_428 = NL + "\t\t\t\tif (eNotificationRequired())" + NL + "\t\t\t\t\teNotify(new ";
  protected final String TEXT_429 = "(this, ";
  protected final String TEXT_430 = ".RESOLVE, ";
  protected final String TEXT_431 = ", old";
  protected final String TEXT_432 = ", ";
  protected final String TEXT_433 = "));";
  protected final String TEXT_434 = NL + "\t\t\t}" + NL + "\t\t}";
  protected final String TEXT_435 = NL + "\t\treturn (";
  protected final String TEXT_436 = ")eVirtualGet(";
  protected final String TEXT_437 = ", ";
  protected final String TEXT_438 = ");";
  protected final String TEXT_439 = NL + "\t\treturn (";
  protected final String TEXT_440 = " & ";
  protected final String TEXT_441 = "_EFLAG) != 0;";
  protected final String TEXT_442 = NL + "\t\treturn ";
  protected final String TEXT_443 = "_EFLAG_VALUES[(";
  protected final String TEXT_444 = " & ";
  protected final String TEXT_445 = "_EFLAG) >>> ";
  protected final String TEXT_446 = "_EFLAG_OFFSET];";
  protected final String TEXT_447 = NL + "\t\treturn ";
  protected final String TEXT_448 = ";";
  protected final String TEXT_449 = NL + "\t\t";
  protected final String TEXT_450 = " ";
  protected final String TEXT_451 = " = basicGet";
  protected final String TEXT_452 = "();" + NL + "\t\treturn ";
  protected final String TEXT_453 = " != null && ";
  protected final String TEXT_454 = ".eIsProxy() ? ";
  protected final String TEXT_455 = "eResolveProxy((";
  protected final String TEXT_456 = ")";
  protected final String TEXT_457 = ") : ";
  protected final String TEXT_458 = ";";
  protected final String TEXT_459 = NL + "\t\treturn new ";
  protected final String TEXT_460 = "((";
  protected final String TEXT_461 = ".Internal)((";
  protected final String TEXT_462 = ".Internal.Wrapper)get";
  protected final String TEXT_463 = "()).featureMap().";
  protected final String TEXT_464 = "list(";
  protected final String TEXT_465 = "));";
  protected final String TEXT_466 = NL + "\t\treturn (";
  protected final String TEXT_467 = ")get";
  protected final String TEXT_468 = "().";
  protected final String TEXT_469 = "list(";
  protected final String TEXT_470 = ");";
  protected final String TEXT_471 = NL + "\t\treturn ((";
  protected final String TEXT_472 = ".Internal.Wrapper)get";
  protected final String TEXT_473 = "()).featureMap().list(";
  protected final String TEXT_474 = ");";
  protected final String TEXT_475 = NL + "\t\treturn get";
  protected final String TEXT_476 = "().list(";
  protected final String TEXT_477 = ");";
  protected final String TEXT_478 = NL + "\t\treturn ";
  protected final String TEXT_479 = "(";
  protected final String TEXT_480 = "(";
  protected final String TEXT_481 = ")";
  protected final String TEXT_482 = "((";
  protected final String TEXT_483 = ".Internal.Wrapper)get";
  protected final String TEXT_484 = "()).featureMap().get(";
  protected final String TEXT_485 = ", true)";
  protected final String TEXT_486 = ").";
  protected final String TEXT_487 = "()";
  protected final String TEXT_488 = ";";
  protected final String TEXT_489 = NL + "\t\treturn ";
  protected final String TEXT_490 = "(";
  protected final String TEXT_491 = "(";
  protected final String TEXT_492 = ")";
  protected final String TEXT_493 = "get";
  protected final String TEXT_494 = "().get(";
  protected final String TEXT_495 = ", true)";
  protected final String TEXT_496 = ").";
  protected final String TEXT_497 = "()";
  protected final String TEXT_498 = ";";
  protected final String TEXT_499 = NL + "\t\t";
  protected final String TEXT_500 = NL + "\t\t";
  protected final String TEXT_501 = NL + "\t\t// TODO: implement this method to return the '";
  protected final String TEXT_502 = "' ";
  protected final String TEXT_503 = NL + "\t\t// Ensure that you remove @generated or mark it @generated NOT";
  protected final String TEXT_504 = NL + "\t\t// The list is expected to implement org.eclipse.emf.ecore.util.InternalEList and org.eclipse.emf.ecore.EStructuralFeature.Setting" + NL + "\t\t// so it's likely that an appropriate subclass of org.eclipse.emf.ecore.util.";
  protected final String TEXT_505 = "EcoreEMap";
  protected final String TEXT_506 = "BasicFeatureMap";
  protected final String TEXT_507 = "EcoreEList";
  protected final String TEXT_508 = " should be used.";
  protected final String TEXT_509 = NL + "\t\tthrow new UnsupportedOperationException();";
  protected final String TEXT_510 = NL + "\t}" + NL;
  protected final String TEXT_511 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */";
  protected final String TEXT_512 = NL + "\tpublic ";
  protected final String TEXT_513 = " basicGet";
  protected final String TEXT_514 = "()" + NL + "\t{";
  protected final String TEXT_515 = NL + "\t\treturn (";
  protected final String TEXT_516 = ")eDynamicGet(";
  protected final String TEXT_517 = ", ";
  protected final String TEXT_518 = ", false, ";
  protected final String TEXT_519 = ");";
  protected final String TEXT_520 = NL + "\t\treturn ";
  protected final String TEXT_521 = "(";
  protected final String TEXT_522 = "(";
  protected final String TEXT_523 = ")";
  protected final String TEXT_524 = "__ESETTING_DELEGATE.dynamicGet(this, null, 0, false, false)";
  protected final String TEXT_525 = ").";
  protected final String TEXT_526 = "()";
  protected final String TEXT_527 = ";";
  protected final String TEXT_528 = NL + "\t\tif (eContainerFeatureID() != ";
  protected final String TEXT_529 = ") return null;" + NL + "\t\treturn (";
  protected final String TEXT_530 = ")eInternalContainer();";
  protected final String TEXT_531 = NL + "\t\treturn (";
  protected final String TEXT_532 = ")eVirtualGet(";
  protected final String TEXT_533 = ");";
  protected final String TEXT_534 = NL + "\t\treturn ";
  protected final String TEXT_535 = ";";
  protected final String TEXT_536 = NL + "\t\treturn (";
  protected final String TEXT_537 = ")((";
  protected final String TEXT_538 = ".Internal.Wrapper)get";
  protected final String TEXT_539 = "()).featureMap().get(";
  protected final String TEXT_540 = ", false);";
  protected final String TEXT_541 = NL + "\t\treturn (";
  protected final String TEXT_542 = ")get";
  protected final String TEXT_543 = "().get(";
  protected final String TEXT_544 = ", false);";
  protected final String TEXT_545 = NL + "\t\t// TODO: implement this method to return the '";
  protected final String TEXT_546 = "' ";
  protected final String TEXT_547 = NL + "\t\t// -> do not perform proxy resolution" + NL + "\t\t// Ensure that you remove @generated or mark it @generated NOT" + NL + "\t\tthrow new UnsupportedOperationException();";
  protected final String TEXT_548 = NL + "\t}" + NL;
  protected final String TEXT_549 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */";
  protected final String TEXT_550 = NL + "\tpublic ";
  protected final String TEXT_551 = " basicSet";
  protected final String TEXT_552 = "(";
  protected final String TEXT_553 = " new";
  protected final String TEXT_554 = ", ";
  protected final String TEXT_555 = " msgs)" + NL + "\t{";
  protected final String TEXT_556 = NL + "\t\tmsgs = eBasicSetContainer((";
  protected final String TEXT_557 = ")new";
  protected final String TEXT_558 = ", ";
  protected final String TEXT_559 = ", msgs);";
  protected final String TEXT_560 = NL + "\t\treturn msgs;";
  protected final String TEXT_561 = NL + "\t\tmsgs = eDynamicInverseAdd((";
  protected final String TEXT_562 = ")new";
  protected final String TEXT_563 = ", ";
  protected final String TEXT_564 = ", msgs);";
  protected final String TEXT_565 = NL + "\t\treturn msgs;";
  protected final String TEXT_566 = NL + "\t\tObject old";
  protected final String TEXT_567 = " = eVirtualSet(";
  protected final String TEXT_568 = ", new";
  protected final String TEXT_569 = ");";
  protected final String TEXT_570 = NL + "\t\t";
  protected final String TEXT_571 = " old";
  protected final String TEXT_572 = " = ";
  protected final String TEXT_573 = ";" + NL + "\t\t";
  protected final String TEXT_574 = " = new";
  protected final String TEXT_575 = ";";
  protected final String TEXT_576 = NL + "\t\tboolean isSetChange = old";
  protected final String TEXT_577 = " == EVIRTUAL_NO_VALUE;";
  protected final String TEXT_578 = NL + "\t\tboolean old";
  protected final String TEXT_579 = "ESet = (";
  protected final String TEXT_580 = " & ";
  protected final String TEXT_581 = "_ESETFLAG) != 0;";
  protected final String TEXT_582 = NL + "\t\t";
  protected final String TEXT_583 = " |= ";
  protected final String TEXT_584 = "_ESETFLAG;";
  protected final String TEXT_585 = NL + "\t\tboolean old";
  protected final String TEXT_586 = "ESet = ";
  protected final String TEXT_587 = "ESet;";
  protected final String TEXT_588 = NL + "\t\t";
  protected final String TEXT_589 = "ESet = true;";
  protected final String TEXT_590 = NL + "\t\tif (eNotificationRequired())" + NL + "\t\t{";
  protected final String TEXT_591 = NL + "\t\t\t";
  protected final String TEXT_592 = " notification = new ";
  protected final String TEXT_593 = "(this, ";
  protected final String TEXT_594 = ".SET, ";
  protected final String TEXT_595 = ", ";
  protected final String TEXT_596 = "isSetChange ? null : old";
  protected final String TEXT_597 = "old";
  protected final String TEXT_598 = ", new";
  protected final String TEXT_599 = ", ";
  protected final String TEXT_600 = "isSetChange";
  protected final String TEXT_601 = "!old";
  protected final String TEXT_602 = "ESet";
  protected final String TEXT_603 = ");";
  protected final String TEXT_604 = NL + "\t\t\t";
  protected final String TEXT_605 = " notification = new ";
  protected final String TEXT_606 = "(this, ";
  protected final String TEXT_607 = ".SET, ";
  protected final String TEXT_608 = ", ";
  protected final String TEXT_609 = "old";
  protected final String TEXT_610 = " == EVIRTUAL_NO_VALUE ? null : old";
  protected final String TEXT_611 = "old";
  protected final String TEXT_612 = ", new";
  protected final String TEXT_613 = ");";
  protected final String TEXT_614 = NL + "\t\t\tif (msgs == null) msgs = notification; else msgs.add(notification);" + NL + "\t\t}";
  protected final String TEXT_615 = NL + "\t\treturn msgs;";
  protected final String TEXT_616 = NL + "\t\treturn ((";
  protected final String TEXT_617 = ".Internal)((";
  protected final String TEXT_618 = ".Internal.Wrapper)get";
  protected final String TEXT_619 = "()).featureMap()).basicAdd(";
  protected final String TEXT_620 = ", new";
  protected final String TEXT_621 = ", msgs);";
  protected final String TEXT_622 = NL + "\t\treturn ((";
  protected final String TEXT_623 = ".Internal)get";
  protected final String TEXT_624 = "()).basicAdd(";
  protected final String TEXT_625 = ", new";
  protected final String TEXT_626 = ", msgs);";
  protected final String TEXT_627 = NL + "\t\t// TODO: implement this method to set the contained '";
  protected final String TEXT_628 = "' ";
  protected final String TEXT_629 = NL + "\t\t// -> this method is automatically invoked to keep the containment relationship in synch" + NL + "\t\t// -> do not modify other features" + NL + "\t\t// -> return msgs, after adding any generated Notification to it (if it is null, a NotificationChain object must be created first)" + NL + "\t\t// Ensure that you remove @generated or mark it @generated NOT" + NL + "\t\tthrow new UnsupportedOperationException();";
  protected final String TEXT_630 = NL + "\t}" + NL;
  protected final String TEXT_631 = NL + "\t/**" + NL + "\t * Sets the value of the '{@link ";
  protected final String TEXT_632 = "#";
  protected final String TEXT_633 = " <em>";
  protected final String TEXT_634 = "</em>}' ";
  protected final String TEXT_635 = ".";
  protected final String TEXT_636 = NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @param value the new value of the '<em>";
  protected final String TEXT_637 = "</em>' ";
  protected final String TEXT_638 = ".";
  protected final String TEXT_639 = NL + "\t * @see ";
  protected final String TEXT_640 = NL + "\t * @see #isSet";
  protected final String TEXT_641 = "()";
  protected final String TEXT_642 = NL + "\t * @see #unset";
  protected final String TEXT_643 = "()";
  protected final String TEXT_644 = NL + "\t * @see #";
  protected final String TEXT_645 = "()" + NL + "\t * @generated" + NL + "\t */";
  protected final String TEXT_646 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */";
  protected final String TEXT_647 = NL + "\tvoid set";
  protected final String TEXT_648 = "(";
  protected final String TEXT_649 = " value);" + NL;
  protected final String TEXT_650 = NL + "\tpublic void set";
  protected final String TEXT_651 = "_";
  protected final String TEXT_652 = "(";
  protected final String TEXT_653 = " ";
  protected final String TEXT_654 = ")" + NL + "\t{";
  protected final String TEXT_655 = NL + "\t\teDynamicSet(";
  protected final String TEXT_656 = ", ";
  protected final String TEXT_657 = ", ";
  protected final String TEXT_658 = "new ";
  protected final String TEXT_659 = "(";
  protected final String TEXT_660 = "new";
  protected final String TEXT_661 = ")";
  protected final String TEXT_662 = ");";
  protected final String TEXT_663 = NL + "\t\teSet(";
  protected final String TEXT_664 = ", ";
  protected final String TEXT_665 = "new ";
  protected final String TEXT_666 = "(";
  protected final String TEXT_667 = "new";
  protected final String TEXT_668 = ")";
  protected final String TEXT_669 = ");";
  protected final String TEXT_670 = NL + "\t\t";
  protected final String TEXT_671 = "__ESETTING_DELEGATE.dynamicSet(this, null, 0, ";
  protected final String TEXT_672 = "new ";
  protected final String TEXT_673 = "(";
  protected final String TEXT_674 = "new";
  protected final String TEXT_675 = ")";
  protected final String TEXT_676 = ");";
  protected final String TEXT_677 = NL + "\t\tif (new";
  protected final String TEXT_678 = " != eInternalContainer() || (eContainerFeatureID() != ";
  protected final String TEXT_679 = " && new";
  protected final String TEXT_680 = " != null))" + NL + "\t\t{" + NL + "\t\t\tif (";
  protected final String TEXT_681 = ".isAncestor(this, ";
  protected final String TEXT_682 = "new";
  protected final String TEXT_683 = "))" + NL + "\t\t\t\tthrow new ";
  protected final String TEXT_684 = "(\"Recursive containment not allowed for \" + toString());";
  protected final String TEXT_685 = NL + "\t\t\t";
  protected final String TEXT_686 = " msgs = null;" + NL + "\t\t\tif (eInternalContainer() != null)" + NL + "\t\t\t\tmsgs = eBasicRemoveFromContainer(msgs);" + NL + "\t\t\tif (new";
  protected final String TEXT_687 = " != null)" + NL + "\t\t\t\tmsgs = ((";
  protected final String TEXT_688 = ")new";
  protected final String TEXT_689 = ").eInverseAdd(this, ";
  protected final String TEXT_690 = ", ";
  protected final String TEXT_691 = ".class, msgs);" + NL + "\t\t\tmsgs = basicSet";
  protected final String TEXT_692 = "(";
  protected final String TEXT_693 = "new";
  protected final String TEXT_694 = ", msgs);" + NL + "\t\t\tif (msgs != null) msgs.dispatch();" + NL + "\t\t}";
  protected final String TEXT_695 = NL + "\t\telse if (eNotificationRequired())" + NL + "\t\t\teNotify(new ";
  protected final String TEXT_696 = "(this, ";
  protected final String TEXT_697 = ".SET, ";
  protected final String TEXT_698 = ", new";
  protected final String TEXT_699 = ", new";
  protected final String TEXT_700 = "));";
  protected final String TEXT_701 = NL + "\t\t";
  protected final String TEXT_702 = " ";
  protected final String TEXT_703 = " = (";
  protected final String TEXT_704 = ")eVirtualGet(";
  protected final String TEXT_705 = ");";
  protected final String TEXT_706 = NL + "\t\tif (new";
  protected final String TEXT_707 = " != ";
  protected final String TEXT_708 = ")" + NL + "\t\t{" + NL + "\t\t\t";
  protected final String TEXT_709 = " msgs = null;" + NL + "\t\t\tif (";
  protected final String TEXT_710 = " != null)";
  protected final String TEXT_711 = NL + "\t\t\t\tmsgs = ((";
  protected final String TEXT_712 = ")";
  protected final String TEXT_713 = ").eInverseRemove(this, EOPPOSITE_FEATURE_BASE - ";
  protected final String TEXT_714 = ", null, msgs);" + NL + "\t\t\tif (new";
  protected final String TEXT_715 = " != null)" + NL + "\t\t\t\tmsgs = ((";
  protected final String TEXT_716 = ")new";
  protected final String TEXT_717 = ").eInverseAdd(this, EOPPOSITE_FEATURE_BASE - ";
  protected final String TEXT_718 = ", null, msgs);";
  protected final String TEXT_719 = NL + "\t\t\t\tmsgs = ((";
  protected final String TEXT_720 = ")";
  protected final String TEXT_721 = ").eInverseRemove(this, ";
  protected final String TEXT_722 = ", ";
  protected final String TEXT_723 = ".class, msgs);" + NL + "\t\t\tif (new";
  protected final String TEXT_724 = " != null)" + NL + "\t\t\t\tmsgs = ((";
  protected final String TEXT_725 = ")new";
  protected final String TEXT_726 = ").eInverseAdd(this, ";
  protected final String TEXT_727 = ", ";
  protected final String TEXT_728 = ".class, msgs);";
  protected final String TEXT_729 = NL + "\t\t\tmsgs = basicSet";
  protected final String TEXT_730 = "(";
  protected final String TEXT_731 = "new";
  protected final String TEXT_732 = ", msgs);" + NL + "\t\t\tif (msgs != null) msgs.dispatch();" + NL + "\t\t}";
  protected final String TEXT_733 = NL + "\t\telse" + NL + "\t\t{";
  protected final String TEXT_734 = NL + "\t\t\tboolean old";
  protected final String TEXT_735 = "ESet = eVirtualIsSet(";
  protected final String TEXT_736 = ");";
  protected final String TEXT_737 = NL + "\t\t\tboolean old";
  protected final String TEXT_738 = "ESet = (";
  protected final String TEXT_739 = " & ";
  protected final String TEXT_740 = "_ESETFLAG) != 0;";
  protected final String TEXT_741 = NL + "\t\t\t";
  protected final String TEXT_742 = " |= ";
  protected final String TEXT_743 = "_ESETFLAG;";
  protected final String TEXT_744 = NL + "\t\t\tboolean old";
  protected final String TEXT_745 = "ESet = ";
  protected final String TEXT_746 = "ESet;";
  protected final String TEXT_747 = NL + "\t\t\t";
  protected final String TEXT_748 = "ESet = true;";
  protected final String TEXT_749 = NL + "\t\t\tif (eNotificationRequired())" + NL + "\t\t\t\teNotify(new ";
  protected final String TEXT_750 = "(this, ";
  protected final String TEXT_751 = ".SET, ";
  protected final String TEXT_752 = ", new";
  protected final String TEXT_753 = ", new";
  protected final String TEXT_754 = ", !old";
  protected final String TEXT_755 = "ESet));";
  protected final String TEXT_756 = NL + "\t\t}";
  protected final String TEXT_757 = NL + "\t\telse if (eNotificationRequired())" + NL + "\t\t\teNotify(new ";
  protected final String TEXT_758 = "(this, ";
  protected final String TEXT_759 = ".SET, ";
  protected final String TEXT_760 = ", new";
  protected final String TEXT_761 = ", new";
  protected final String TEXT_762 = "));";
  protected final String TEXT_763 = NL + "\t\t";
  protected final String TEXT_764 = " old";
  protected final String TEXT_765 = " = (";
  protected final String TEXT_766 = " & ";
  protected final String TEXT_767 = "_EFLAG) != 0;";
  protected final String TEXT_768 = NL + "\t\t";
  protected final String TEXT_769 = " old";
  protected final String TEXT_770 = " = ";
  protected final String TEXT_771 = "_EFLAG_VALUES[(";
  protected final String TEXT_772 = " & ";
  protected final String TEXT_773 = "_EFLAG) >>> ";
  protected final String TEXT_774 = "_EFLAG_OFFSET];";
  protected final String TEXT_775 = NL + "\t\tif (new";
  protected final String TEXT_776 = ") ";
  protected final String TEXT_777 = " |= ";
  protected final String TEXT_778 = "_EFLAG; else ";
  protected final String TEXT_779 = " &= ~";
  protected final String TEXT_780 = "_EFLAG;";
  protected final String TEXT_781 = NL + "\t\tif (new";
  protected final String TEXT_782 = " == null) new";
  protected final String TEXT_783 = " = ";
  protected final String TEXT_784 = "_EDEFAULT;" + NL + "\t\t";
  protected final String TEXT_785 = " = ";
  protected final String TEXT_786 = " & ~";
  protected final String TEXT_787 = "_EFLAG | ";
  protected final String TEXT_788 = "new";
  protected final String TEXT_789 = ".ordinal()";
  protected final String TEXT_790 = ".VALUES.indexOf(new";
  protected final String TEXT_791 = ")";
  protected final String TEXT_792 = " << ";
  protected final String TEXT_793 = "_EFLAG_OFFSET;";
  protected final String TEXT_794 = NL + "\t\t";
  protected final String TEXT_795 = " old";
  protected final String TEXT_796 = " = ";
  protected final String TEXT_797 = ";";
  protected final String TEXT_798 = NL + "\t\t";
  protected final String TEXT_799 = " ";
  protected final String TEXT_800 = " = new";
  protected final String TEXT_801 = " == null ? ";
  protected final String TEXT_802 = " : new";
  protected final String TEXT_803 = ";";
  protected final String TEXT_804 = NL + "\t\t";
  protected final String TEXT_805 = " = new";
  protected final String TEXT_806 = " == null ? ";
  protected final String TEXT_807 = " : new";
  protected final String TEXT_808 = ";";
  protected final String TEXT_809 = NL + "\t\t";
  protected final String TEXT_810 = " ";
  protected final String TEXT_811 = " = ";
  protected final String TEXT_812 = "new";
  protected final String TEXT_813 = ";";
  protected final String TEXT_814 = NL + "\t\t";
  protected final String TEXT_815 = " = ";
  protected final String TEXT_816 = "new";
  protected final String TEXT_817 = ";";
  protected final String TEXT_818 = NL + "\t\tObject old";
  protected final String TEXT_819 = " = eVirtualSet(";
  protected final String TEXT_820 = ", ";
  protected final String TEXT_821 = ");";
  protected final String TEXT_822 = NL + "\t\tboolean isSetChange = old";
  protected final String TEXT_823 = " == EVIRTUAL_NO_VALUE;";
  protected final String TEXT_824 = NL + "\t\tboolean old";
  protected final String TEXT_825 = "ESet = (";
  protected final String TEXT_826 = " & ";
  protected final String TEXT_827 = "_ESETFLAG) != 0;";
  protected final String TEXT_828 = NL + "\t\t";
  protected final String TEXT_829 = " |= ";
  protected final String TEXT_830 = "_ESETFLAG;";
  protected final String TEXT_831 = NL + "\t\tboolean old";
  protected final String TEXT_832 = "ESet = ";
  protected final String TEXT_833 = "ESet;";
  protected final String TEXT_834 = NL + "\t\t";
  protected final String TEXT_835 = "ESet = true;";
  protected final String TEXT_836 = NL + "\t\tif (eNotificationRequired())" + NL + "\t\t\teNotify(new ";
  protected final String TEXT_837 = "(this, ";
  protected final String TEXT_838 = ".SET, ";
  protected final String TEXT_839 = ", ";
  protected final String TEXT_840 = "isSetChange ? ";
  protected final String TEXT_841 = " : old";
  protected final String TEXT_842 = "old";
  protected final String TEXT_843 = ", ";
  protected final String TEXT_844 = "new";
  protected final String TEXT_845 = ", ";
  protected final String TEXT_846 = "isSetChange";
  protected final String TEXT_847 = "!old";
  protected final String TEXT_848 = "ESet";
  protected final String TEXT_849 = "));";
  protected final String TEXT_850 = NL + "\t\tif (eNotificationRequired())" + NL + "\t\t\teNotify(new ";
  protected final String TEXT_851 = "(this, ";
  protected final String TEXT_852 = ".SET, ";
  protected final String TEXT_853 = ", ";
  protected final String TEXT_854 = "old";
  protected final String TEXT_855 = " == EVIRTUAL_NO_VALUE ? ";
  protected final String TEXT_856 = " : old";
  protected final String TEXT_857 = "old";
  protected final String TEXT_858 = ", ";
  protected final String TEXT_859 = "new";
  protected final String TEXT_860 = "));";
  protected final String TEXT_861 = NL + "\t\t((";
  protected final String TEXT_862 = ".Internal)((";
  protected final String TEXT_863 = ".Internal.Wrapper)get";
  protected final String TEXT_864 = "()).featureMap()).set(";
  protected final String TEXT_865 = ", ";
  protected final String TEXT_866 = "new ";
  protected final String TEXT_867 = "(";
  protected final String TEXT_868 = "new";
  protected final String TEXT_869 = ")";
  protected final String TEXT_870 = ");";
  protected final String TEXT_871 = NL + "\t\t((";
  protected final String TEXT_872 = ".Internal)get";
  protected final String TEXT_873 = "()).set(";
  protected final String TEXT_874 = ", ";
  protected final String TEXT_875 = "new ";
  protected final String TEXT_876 = "(";
  protected final String TEXT_877 = "new";
  protected final String TEXT_878 = ")";
  protected final String TEXT_879 = ");";
  protected final String TEXT_880 = NL + "\t\t";
  protected final String TEXT_881 = NL + "\t\t// TODO: implement this method to set the '";
  protected final String TEXT_882 = "' ";
  protected final String TEXT_883 = NL + "\t\t// Ensure that you remove @generated or mark it @generated NOT" + NL + "\t\tthrow new UnsupportedOperationException();";
  protected final String TEXT_884 = NL + "\t}" + NL;
  protected final String TEXT_885 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */";
  protected final String TEXT_886 = NL + "\tpublic ";
  protected final String TEXT_887 = " basicUnset";
  protected final String TEXT_888 = "(";
  protected final String TEXT_889 = " msgs)" + NL + "\t{";
  protected final String TEXT_890 = NL + "\t\treturn eDynamicInverseRemove((";
  protected final String TEXT_891 = ")";
  protected final String TEXT_892 = "basicGet";
  protected final String TEXT_893 = "(), ";
  protected final String TEXT_894 = ", msgs);";
  protected final String TEXT_895 = "Object old";
  protected final String TEXT_896 = " = ";
  protected final String TEXT_897 = "eVirtualUnset(";
  protected final String TEXT_898 = ");";
  protected final String TEXT_899 = NL + "\t\t";
  protected final String TEXT_900 = " old";
  protected final String TEXT_901 = " = ";
  protected final String TEXT_902 = ";";
  protected final String TEXT_903 = NL + "\t\t";
  protected final String TEXT_904 = " = null;";
  protected final String TEXT_905 = NL + "\t\tboolean isSetChange = old";
  protected final String TEXT_906 = " != EVIRTUAL_NO_VALUE;";
  protected final String TEXT_907 = NL + "\t\tboolean old";
  protected final String TEXT_908 = "ESet = (";
  protected final String TEXT_909 = " & ";
  protected final String TEXT_910 = "_ESETFLAG) != 0;";
  protected final String TEXT_911 = NL + "\t\t";
  protected final String TEXT_912 = " &= ~";
  protected final String TEXT_913 = "_ESETFLAG;";
  protected final String TEXT_914 = NL + "\t\tboolean old";
  protected final String TEXT_915 = "ESet = ";
  protected final String TEXT_916 = "ESet;";
  protected final String TEXT_917 = NL + "\t\t";
  protected final String TEXT_918 = "ESet = false;";
  protected final String TEXT_919 = NL + "\t\tif (eNotificationRequired())" + NL + "\t\t{" + NL + "\t\t\t";
  protected final String TEXT_920 = " notification = new ";
  protected final String TEXT_921 = "(this, ";
  protected final String TEXT_922 = ".UNSET, ";
  protected final String TEXT_923 = ", ";
  protected final String TEXT_924 = "isSetChange ? old";
  protected final String TEXT_925 = " : null";
  protected final String TEXT_926 = "old";
  protected final String TEXT_927 = ", null, ";
  protected final String TEXT_928 = "isSetChange";
  protected final String TEXT_929 = "old";
  protected final String TEXT_930 = "ESet";
  protected final String TEXT_931 = ");" + NL + "\t\t\tif (msgs == null) msgs = notification; else msgs.add(notification);" + NL + "\t\t}" + NL + "\t\treturn msgs;";
  protected final String TEXT_932 = NL + "\t\t// TODO: implement this method to unset the contained '";
  protected final String TEXT_933 = "' ";
  protected final String TEXT_934 = NL + "\t\t// -> this method is automatically invoked to keep the containment relationship in synch" + NL + "\t\t// -> do not modify other features" + NL + "\t\t// -> return msgs, after adding any generated Notification to it (if it is null, a NotificationChain object must be created first)" + NL + "\t\t// Ensure that you remove @generated or mark it @generated NOT" + NL + "\t\tthrow new UnsupportedOperationException();";
  protected final String TEXT_935 = NL + "\t}" + NL;
  protected final String TEXT_936 = NL + "\t/**" + NL + "\t * Unsets the value of the '{@link ";
  protected final String TEXT_937 = "#";
  protected final String TEXT_938 = " <em>";
  protected final String TEXT_939 = "</em>}' ";
  protected final String TEXT_940 = ".";
  protected final String TEXT_941 = NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->";
  protected final String TEXT_942 = NL + "\t * @see #isSet";
  protected final String TEXT_943 = "()";
  protected final String TEXT_944 = NL + "\t * @see #";
  protected final String TEXT_945 = "()";
  protected final String TEXT_946 = NL + "\t * @see #set";
  protected final String TEXT_947 = "(";
  protected final String TEXT_948 = ")";
  protected final String TEXT_949 = NL + "\t * @generated" + NL + "\t */";
  protected final String TEXT_950 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */";
  protected final String TEXT_951 = NL + "\tvoid unset";
  protected final String TEXT_952 = "();" + NL;
  protected final String TEXT_953 = NL + "\tpublic void unset";
  protected final String TEXT_954 = "_";
  protected final String TEXT_955 = "()" + NL + "\t{";
  protected final String TEXT_956 = NL + "\t\teDynamicUnset(";
  protected final String TEXT_957 = ", ";
  protected final String TEXT_958 = ");";
  protected final String TEXT_959 = NL + "\t\teUnset(";
  protected final String TEXT_960 = ");";
  protected final String TEXT_961 = NL + "\t\t";
  protected final String TEXT_962 = "__ESETTING_DELEGATE.dynamicUnset(this, null, 0);";
  protected final String TEXT_963 = NL + "\t\t";
  protected final String TEXT_964 = " ";
  protected final String TEXT_965 = " = (";
  protected final String TEXT_966 = ")eVirtualGet(";
  protected final String TEXT_967 = ");";
  protected final String TEXT_968 = NL + "\t\tif (";
  protected final String TEXT_969 = " != null) ((";
  protected final String TEXT_970 = ".Unsettable";
  protected final String TEXT_971 = ")";
  protected final String TEXT_972 = ").unset();";
  protected final String TEXT_973 = NL + "\t\t";
  protected final String TEXT_974 = " ";
  protected final String TEXT_975 = " = (";
  protected final String TEXT_976 = ")eVirtualGet(";
  protected final String TEXT_977 = ");";
  protected final String TEXT_978 = NL + "\t\tif (";
  protected final String TEXT_979 = " != null)" + NL + "\t\t{" + NL + "\t\t\t";
  protected final String TEXT_980 = " msgs = null;";
  protected final String TEXT_981 = NL + "\t\t\tmsgs = ((";
  protected final String TEXT_982 = ")";
  protected final String TEXT_983 = ").eInverseRemove(this, EOPPOSITE_FEATURE_BASE - ";
  protected final String TEXT_984 = ", null, msgs);";
  protected final String TEXT_985 = NL + "\t\t\tmsgs = ((";
  protected final String TEXT_986 = ")";
  protected final String TEXT_987 = ").eInverseRemove(this, ";
  protected final String TEXT_988 = ", ";
  protected final String TEXT_989 = ".class, msgs);";
  protected final String TEXT_990 = NL + "\t\t\tmsgs = basicUnset";
  protected final String TEXT_991 = "(msgs);" + NL + "\t\t\tif (msgs != null) msgs.dispatch();" + NL + "\t\t}" + NL + "\t\telse" + NL + "\t\t{";
  protected final String TEXT_992 = NL + "\t\t\tboolean old";
  protected final String TEXT_993 = "ESet = eVirtualIsSet(";
  protected final String TEXT_994 = ");";
  protected final String TEXT_995 = NL + "\t\t\tboolean old";
  protected final String TEXT_996 = "ESet = (";
  protected final String TEXT_997 = " & ";
  protected final String TEXT_998 = "_ESETFLAG) != 0;";
  protected final String TEXT_999 = NL + "\t\t\t";
  protected final String TEXT_1000 = " &= ~";
  protected final String TEXT_1001 = "_ESETFLAG;";
  protected final String TEXT_1002 = NL + "\t\t\tboolean old";
  protected final String TEXT_1003 = "ESet = ";
  protected final String TEXT_1004 = "ESet;";
  protected final String TEXT_1005 = NL + "\t\t\t";
  protected final String TEXT_1006 = "ESet = false;";
  protected final String TEXT_1007 = NL + "\t\t\tif (eNotificationRequired())" + NL + "\t\t\t\teNotify(new ";
  protected final String TEXT_1008 = "(this, ";
  protected final String TEXT_1009 = ".UNSET, ";
  protected final String TEXT_1010 = ", null, null, old";
  protected final String TEXT_1011 = "ESet));";
  protected final String TEXT_1012 = NL + "\t\t}";
  protected final String TEXT_1013 = NL + "\t\t";
  protected final String TEXT_1014 = " old";
  protected final String TEXT_1015 = " = (";
  protected final String TEXT_1016 = " & ";
  protected final String TEXT_1017 = "_EFLAG) != 0;";
  protected final String TEXT_1018 = NL + "\t\t";
  protected final String TEXT_1019 = " old";
  protected final String TEXT_1020 = " = ";
  protected final String TEXT_1021 = "_EFLAG_VALUES[(";
  protected final String TEXT_1022 = " & ";
  protected final String TEXT_1023 = "_EFLAG) >>> ";
  protected final String TEXT_1024 = "_EFLAG_OFFSET];";
  protected final String TEXT_1025 = NL + "\t\tObject old";
  protected final String TEXT_1026 = " = eVirtualUnset(";
  protected final String TEXT_1027 = ");";
  protected final String TEXT_1028 = NL + "\t\t";
  protected final String TEXT_1029 = " old";
  protected final String TEXT_1030 = " = ";
  protected final String TEXT_1031 = ";";
  protected final String TEXT_1032 = NL + "\t\tboolean isSetChange = old";
  protected final String TEXT_1033 = " != EVIRTUAL_NO_VALUE;";
  protected final String TEXT_1034 = NL + "\t\tboolean old";
  protected final String TEXT_1035 = "ESet = (";
  protected final String TEXT_1036 = " & ";
  protected final String TEXT_1037 = "_ESETFLAG) != 0;";
  protected final String TEXT_1038 = NL + "\t\tboolean old";
  protected final String TEXT_1039 = "ESet = ";
  protected final String TEXT_1040 = "ESet;";
  protected final String TEXT_1041 = NL + "\t\t";
  protected final String TEXT_1042 = " = null;";
  protected final String TEXT_1043 = NL + "\t\t";
  protected final String TEXT_1044 = " &= ~";
  protected final String TEXT_1045 = "_ESETFLAG;";
  protected final String TEXT_1046 = NL + "\t\t";
  protected final String TEXT_1047 = "ESet = false;";
  protected final String TEXT_1048 = NL + "\t\tif (eNotificationRequired())" + NL + "\t\t\teNotify(new ";
  protected final String TEXT_1049 = "(this, ";
  protected final String TEXT_1050 = ".UNSET, ";
  protected final String TEXT_1051 = ", ";
  protected final String TEXT_1052 = "isSetChange ? old";
  protected final String TEXT_1053 = " : null";
  protected final String TEXT_1054 = "old";
  protected final String TEXT_1055 = ", null, ";
  protected final String TEXT_1056 = "isSetChange";
  protected final String TEXT_1057 = "old";
  protected final String TEXT_1058 = "ESet";
  protected final String TEXT_1059 = "));";
  protected final String TEXT_1060 = NL + "\t\tif (";
  protected final String TEXT_1061 = ") ";
  protected final String TEXT_1062 = " |= ";
  protected final String TEXT_1063 = "_EFLAG; else ";
  protected final String TEXT_1064 = " &= ~";
  protected final String TEXT_1065 = "_EFLAG;";
  protected final String TEXT_1066 = NL + "\t\t";
  protected final String TEXT_1067 = " = ";
  protected final String TEXT_1068 = " & ~";
  protected final String TEXT_1069 = "_EFLAG | ";
  protected final String TEXT_1070 = "_EFLAG_DEFAULT;";
  protected final String TEXT_1071 = NL + "\t\t";
  protected final String TEXT_1072 = " = ";
  protected final String TEXT_1073 = ";";
  protected final String TEXT_1074 = NL + "\t\t";
  protected final String TEXT_1075 = " &= ~";
  protected final String TEXT_1076 = "_ESETFLAG;";
  protected final String TEXT_1077 = NL + "\t\t";
  protected final String TEXT_1078 = "ESet = false;";
  protected final String TEXT_1079 = NL + "\t\tif (eNotificationRequired())" + NL + "\t\t\teNotify(new ";
  protected final String TEXT_1080 = "(this, ";
  protected final String TEXT_1081 = ".UNSET, ";
  protected final String TEXT_1082 = ", ";
  protected final String TEXT_1083 = "isSetChange ? old";
  protected final String TEXT_1084 = " : ";
  protected final String TEXT_1085 = "old";
  protected final String TEXT_1086 = ", ";
  protected final String TEXT_1087 = ", ";
  protected final String TEXT_1088 = "isSetChange";
  protected final String TEXT_1089 = "old";
  protected final String TEXT_1090 = "ESet";
  protected final String TEXT_1091 = "));";
  protected final String TEXT_1092 = NL + "\t\t((";
  protected final String TEXT_1093 = ".Internal)((";
  protected final String TEXT_1094 = ".Internal.Wrapper)get";
  protected final String TEXT_1095 = "()).featureMap()).clear(";
  protected final String TEXT_1096 = ");";
  protected final String TEXT_1097 = NL + "\t\t((";
  protected final String TEXT_1098 = ".Internal)get";
  protected final String TEXT_1099 = "()).clear(";
  protected final String TEXT_1100 = ");";
  protected final String TEXT_1101 = NL + "\t\t";
  protected final String TEXT_1102 = NL + "\t\t// TODO: implement this method to unset the '";
  protected final String TEXT_1103 = "' ";
  protected final String TEXT_1104 = NL + "\t\t// Ensure that you remove @generated or mark it @generated NOT" + NL + "\t\tthrow new UnsupportedOperationException();";
  protected final String TEXT_1105 = NL + "\t}" + NL;
  protected final String TEXT_1106 = NL + "\t/**" + NL + "\t * Returns whether the value of the '{@link ";
  protected final String TEXT_1107 = "#";
  protected final String TEXT_1108 = " <em>";
  protected final String TEXT_1109 = "</em>}' ";
  protected final String TEXT_1110 = " is set.";
  protected final String TEXT_1111 = NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @return whether the value of the '<em>";
  protected final String TEXT_1112 = "</em>' ";
  protected final String TEXT_1113 = " is set.";
  protected final String TEXT_1114 = NL + "\t * @see #unset";
  protected final String TEXT_1115 = "()";
  protected final String TEXT_1116 = NL + "\t * @see #";
  protected final String TEXT_1117 = "()";
  protected final String TEXT_1118 = NL + "\t * @see #set";
  protected final String TEXT_1119 = "(";
  protected final String TEXT_1120 = ")";
  protected final String TEXT_1121 = NL + "\t * @generated" + NL + "\t */";
  protected final String TEXT_1122 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */";
  protected final String TEXT_1123 = NL + "\tboolean isSet";
  protected final String TEXT_1124 = "();" + NL;
  protected final String TEXT_1125 = NL + "\tpublic boolean isSet";
  protected final String TEXT_1126 = "_";
  protected final String TEXT_1127 = "()" + NL + "\t{";
  protected final String TEXT_1128 = NL + "\t\treturn eDynamicIsSet(";
  protected final String TEXT_1129 = ", ";
  protected final String TEXT_1130 = ");";
  protected final String TEXT_1131 = NL + "\t\treturn eIsSet(";
  protected final String TEXT_1132 = ");";
  protected final String TEXT_1133 = NL + "\t\treturn ";
  protected final String TEXT_1134 = "__ESETTING_DELEGATE.dynamicIsSet(this, null, 0);";
  protected final String TEXT_1135 = NL + "\t\t";
  protected final String TEXT_1136 = " ";
  protected final String TEXT_1137 = " = (";
  protected final String TEXT_1138 = ")eVirtualGet(";
  protected final String TEXT_1139 = ");";
  protected final String TEXT_1140 = NL + "\t\treturn ";
  protected final String TEXT_1141 = " != null && ((";
  protected final String TEXT_1142 = ".Unsettable";
  protected final String TEXT_1143 = ")";
  protected final String TEXT_1144 = ").isSet();";
  protected final String TEXT_1145 = NL + "\t\treturn eVirtualIsSet(";
  protected final String TEXT_1146 = ");";
  protected final String TEXT_1147 = NL + "\t\treturn (";
  protected final String TEXT_1148 = " & ";
  protected final String TEXT_1149 = "_ESETFLAG) != 0;";
  protected final String TEXT_1150 = NL + "\t\treturn ";
  protected final String TEXT_1151 = "ESet;";
  protected final String TEXT_1152 = NL + "\t\treturn !((";
  protected final String TEXT_1153 = ".Internal)((";
  protected final String TEXT_1154 = ".Internal.Wrapper)get";
  protected final String TEXT_1155 = "()).featureMap()).isEmpty(";
  protected final String TEXT_1156 = ");";
  protected final String TEXT_1157 = NL + "\t\treturn !((";
  protected final String TEXT_1158 = ".Internal)get";
  protected final String TEXT_1159 = "()).isEmpty(";
  protected final String TEXT_1160 = ");";
  protected final String TEXT_1161 = NL + "\t\t";
  protected final String TEXT_1162 = NL + "\t\t// TODO: implement this method to return whether the '";
  protected final String TEXT_1163 = "' ";
  protected final String TEXT_1164 = " is set" + NL + "\t\t// Ensure that you remove @generated or mark it @generated NOT" + NL + "\t\tthrow new UnsupportedOperationException();";
  protected final String TEXT_1165 = NL + "\t}" + NL;
  protected final String TEXT_1166 = NL + "\t/**" + NL + "\t * The cached validation expression for the '{@link #";
  protected final String TEXT_1167 = "(";
  protected final String TEXT_1168 = ") <em>";
  protected final String TEXT_1169 = "</em>}' invariant operation." + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @see #";
  protected final String TEXT_1170 = "(";
  protected final String TEXT_1171 = ")" + NL + "\t * @generated" + NL + "\t * @ordered" + NL + "\t */" + NL + "\tprotected static final ";
  protected final String TEXT_1172 = " ";
  protected final String TEXT_1173 = "__EEXPRESSION = \"";
  protected final String TEXT_1174 = "\";";
  protected final String TEXT_1175 = NL;
  protected final String TEXT_1176 = NL + "\t/**" + NL + "\t * The cached invocation delegate for the '{@link #";
  protected final String TEXT_1177 = "(";
  protected final String TEXT_1178 = ") <em>";
  protected final String TEXT_1179 = "</em>}' operation." + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @see #";
  protected final String TEXT_1180 = "(";
  protected final String TEXT_1181 = ")" + NL + "\t * @generated" + NL + "\t * @ordered" + NL + "\t */" + NL + "\tprotected static final ";
  protected final String TEXT_1182 = ".Internal.InvocationDelegate ";
  protected final String TEXT_1183 = "__EINVOCATION_DELEGATE = ((";
  protected final String TEXT_1184 = ".Internal)";
  protected final String TEXT_1185 = ").getInvocationDelegate();" + NL;
  protected final String TEXT_1186 = NL + "\t/**";
  protected final String TEXT_1187 = NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->";
  protected final String TEXT_1188 = NL + "\t * <!-- begin-model-doc -->";
  protected final String TEXT_1189 = NL + "\t * ";
  protected final String TEXT_1190 = NL + "\t * @param ";
  protected final String TEXT_1191 = NL + "\t *   ";
  protected final String TEXT_1192 = NL + "\t * @param ";
  protected final String TEXT_1193 = " ";
  protected final String TEXT_1194 = NL + "\t * <!-- end-model-doc -->";
  protected final String TEXT_1195 = NL + "\t * @model ";
  protected final String TEXT_1196 = NL + "\t *        ";
  protected final String TEXT_1197 = NL + "\t * @model";
  protected final String TEXT_1198 = NL + "\t * @generated" + NL + "\t */";
  protected final String TEXT_1199 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */";
  protected final String TEXT_1200 = NL + "\t";
  protected final String TEXT_1201 = " ";
  protected final String TEXT_1202 = "(";
  protected final String TEXT_1203 = ")";
  protected final String TEXT_1204 = ";" + NL;
  protected final String TEXT_1205 = NL + "\t@SuppressWarnings(\"unchecked\")";
  protected final String TEXT_1206 = NL + "\tpublic ";
  protected final String TEXT_1207 = " ";
  protected final String TEXT_1208 = "(";
  protected final String TEXT_1209 = ")";
  protected final String TEXT_1210 = NL + "\t{";
  protected final String TEXT_1211 = NL + "\t\t";
  protected final String TEXT_1212 = NL + "\t\treturn" + NL + "\t\t\t";
  protected final String TEXT_1213 = ".validate" + NL + "\t\t\t\t(";
  protected final String TEXT_1214 = "," + NL + "\t\t\t\t this," + NL + "\t\t\t\t ";
  protected final String TEXT_1215 = "," + NL + "\t\t\t\t ";
  protected final String TEXT_1216 = "," + NL + "\t\t\t\t \"";
  protected final String TEXT_1217 = "\",";
  protected final String TEXT_1218 = NL + "\t\t\t\t ";
  protected final String TEXT_1219 = "," + NL + "\t\t\t\t ";
  protected final String TEXT_1220 = "__EEXPRESSION," + NL + "\t\t\t\t ";
  protected final String TEXT_1221 = ".ERROR," + NL + "\t\t\t\t ";
  protected final String TEXT_1222 = ".DIAGNOSTIC_SOURCE," + NL + "\t\t\t\t ";
  protected final String TEXT_1223 = ".";
  protected final String TEXT_1224 = ");";
  protected final String TEXT_1225 = NL + "\t\t// TODO: implement this method" + NL + "\t\t// -> specify the condition that violates the invariant" + NL + "\t\t// -> verify the details of the diagnostic, including severity and message" + NL + "\t\t// Ensure that you remove @generated or mark it @generated NOT" + NL + "\t\tif (false)" + NL + "\t\t{" + NL + "\t\t\tif (";
  protected final String TEXT_1226 = " != null)" + NL + "\t\t\t{" + NL + "\t\t\t\t";
  protected final String TEXT_1227 = ".add" + NL + "\t\t\t\t\t(new ";
  protected final String TEXT_1228 = NL + "\t\t\t\t\t\t(";
  protected final String TEXT_1229 = ".ERROR," + NL + "\t\t\t\t\t\t ";
  protected final String TEXT_1230 = ".DIAGNOSTIC_SOURCE," + NL + "\t\t\t\t\t\t ";
  protected final String TEXT_1231 = ".";
  protected final String TEXT_1232 = "," + NL + "\t\t\t\t\t\t ";
  protected final String TEXT_1233 = ".INSTANCE.getString(\"_UI_GenericInvariant_diagnostic\", new Object[] { \"";
  protected final String TEXT_1234 = "\", ";
  protected final String TEXT_1235 = ".getObjectLabel(this, ";
  protected final String TEXT_1236 = ") }),";
  protected final String TEXT_1237 = NL + "\t\t\t\t\t\t new Object [] { this }));" + NL + "\t\t\t}" + NL + "\t\t\treturn false;" + NL + "\t\t}" + NL + "\t\treturn true;";
  protected final String TEXT_1238 = NL + "\t\ttry" + NL + "\t\t{";
  protected final String TEXT_1239 = NL + "\t\t\t";
  protected final String TEXT_1240 = "__EINVOCATION_DELEGATE.dynamicInvoke(this, ";
  protected final String TEXT_1241 = "new ";
  protected final String TEXT_1242 = ".UnmodifiableEList<Object>(";
  protected final String TEXT_1243 = ", ";
  protected final String TEXT_1244 = ")";
  protected final String TEXT_1245 = "null";
  protected final String TEXT_1246 = ");";
  protected final String TEXT_1247 = NL + "\t\t\treturn ";
  protected final String TEXT_1248 = "(";
  protected final String TEXT_1249 = "(";
  protected final String TEXT_1250 = ")";
  protected final String TEXT_1251 = "__EINVOCATION_DELEGATE.dynamicInvoke(this, ";
  protected final String TEXT_1252 = "new ";
  protected final String TEXT_1253 = ".UnmodifiableEList<Object>(";
  protected final String TEXT_1254 = ", ";
  protected final String TEXT_1255 = ")";
  protected final String TEXT_1256 = "null";
  protected final String TEXT_1257 = ")";
  protected final String TEXT_1258 = ").";
  protected final String TEXT_1259 = "()";
  protected final String TEXT_1260 = ";";
  protected final String TEXT_1261 = NL + "\t\t}" + NL + "\t\tcatch (";
  protected final String TEXT_1262 = " ite)" + NL + "\t\t{" + NL + "\t\t\tthrow new ";
  protected final String TEXT_1263 = "(ite);" + NL + "\t\t}";
  protected final String TEXT_1264 = NL + "\t\t// TODO: implement this method" + NL + "\t\t// Ensure that you remove @generated or mark it @generated NOT" + NL + "\t\tthrow new UnsupportedOperationException();";
  protected final String TEXT_1265 = NL + "\t}" + NL;
  protected final String TEXT_1266 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */";
  protected final String TEXT_1267 = NL + "\t@SuppressWarnings(\"unchecked\")";
  protected final String TEXT_1268 = NL + "\t@Override";
  protected final String TEXT_1269 = NL + "\tpublic ";
  protected final String TEXT_1270 = " eInverseAdd(";
  protected final String TEXT_1271 = " otherEnd, int featureID, ";
  protected final String TEXT_1272 = " msgs)" + NL + "\t{" + NL + "\t\tswitch (featureID";
  protected final String TEXT_1273 = ")" + NL + "\t\t{";
  protected final String TEXT_1274 = NL + "\t\t\tcase ";
  protected final String TEXT_1275 = ":";
  protected final String TEXT_1276 = NL + "\t\t\t\treturn ((";
  protected final String TEXT_1277 = "(";
  protected final String TEXT_1278 = ".InternalMapView";
  protected final String TEXT_1279 = ")";
  protected final String TEXT_1280 = "()).eMap()).basicAdd(otherEnd, msgs);";
  protected final String TEXT_1281 = NL + "\t\t\t\treturn (";
  protected final String TEXT_1282 = "()).basicAdd(otherEnd, msgs);";
  protected final String TEXT_1283 = NL + "\t\t\t\tif (eInternalContainer() != null)" + NL + "\t\t\t\t\tmsgs = eBasicRemoveFromContainer(msgs);";
  protected final String TEXT_1284 = NL + "\t\t\t\treturn basicSet";
  protected final String TEXT_1285 = "((";
  protected final String TEXT_1286 = ")otherEnd, msgs);";
  protected final String TEXT_1287 = NL + "\t\t\t\treturn eBasicSetContainer(otherEnd, ";
  protected final String TEXT_1288 = ", msgs);";
  protected final String TEXT_1289 = NL + "\t\t\t\t";
  protected final String TEXT_1290 = " ";
  protected final String TEXT_1291 = " = (";
  protected final String TEXT_1292 = ")eVirtualGet(";
  protected final String TEXT_1293 = ");";
  protected final String TEXT_1294 = NL + "\t\t\t\t";
  protected final String TEXT_1295 = " ";
  protected final String TEXT_1296 = " = ";
  protected final String TEXT_1297 = "basicGet";
  protected final String TEXT_1298 = "();";
  protected final String TEXT_1299 = NL + "\t\t\t\tif (";
  protected final String TEXT_1300 = " != null)";
  protected final String TEXT_1301 = NL + "\t\t\t\t\tmsgs = ((";
  protected final String TEXT_1302 = ")";
  protected final String TEXT_1303 = ").eInverseRemove(this, EOPPOSITE_FEATURE_BASE - ";
  protected final String TEXT_1304 = ", null, msgs);";
  protected final String TEXT_1305 = NL + "\t\t\t\t\tmsgs = ((";
  protected final String TEXT_1306 = ")";
  protected final String TEXT_1307 = ").eInverseRemove(this, ";
  protected final String TEXT_1308 = ", ";
  protected final String TEXT_1309 = ".class, msgs);";
  protected final String TEXT_1310 = NL + "\t\t\t\treturn basicSet";
  protected final String TEXT_1311 = "((";
  protected final String TEXT_1312 = ")otherEnd, msgs);";
  protected final String TEXT_1313 = NL + "\t\t}";
  protected final String TEXT_1314 = NL + "\t\treturn super.eInverseAdd(otherEnd, featureID, msgs);";
  protected final String TEXT_1315 = NL + "\t\treturn eDynamicInverseAdd(otherEnd, featureID, msgs);";
  protected final String TEXT_1316 = NL + "\t}" + NL;
  protected final String TEXT_1317 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */";
  protected final String TEXT_1318 = NL + "\t@Override";
  protected final String TEXT_1319 = NL + "\tpublic ";
  protected final String TEXT_1320 = " eInverseRemove(";
  protected final String TEXT_1321 = " otherEnd, int featureID, ";
  protected final String TEXT_1322 = " msgs)" + NL + "\t{" + NL + "\t\tswitch (featureID";
  protected final String TEXT_1323 = ")" + NL + "\t\t{";
  protected final String TEXT_1324 = NL + "\t\t\tcase ";
  protected final String TEXT_1325 = ":";
  protected final String TEXT_1326 = NL + "\t\t\t\treturn ((";
  protected final String TEXT_1327 = ")((";
  protected final String TEXT_1328 = ".InternalMapView";
  protected final String TEXT_1329 = ")";
  protected final String TEXT_1330 = "()).eMap()).basicRemove(otherEnd, msgs);";
  protected final String TEXT_1331 = NL + "\t\t\t\treturn ((";
  protected final String TEXT_1332 = ")((";
  protected final String TEXT_1333 = ".Internal.Wrapper)";
  protected final String TEXT_1334 = "()).featureMap()).basicRemove(otherEnd, msgs);";
  protected final String TEXT_1335 = NL + "\t\t\t\treturn ((";
  protected final String TEXT_1336 = ")";
  protected final String TEXT_1337 = "()).basicRemove(otherEnd, msgs);";
  protected final String TEXT_1338 = NL + "\t\t\t\treturn eBasicSetContainer(null, ";
  protected final String TEXT_1339 = ", msgs);";
  protected final String TEXT_1340 = NL + "\t\t\t\treturn basicUnset";
  protected final String TEXT_1341 = "(msgs);";
  protected final String TEXT_1342 = NL + "\t\t\t\treturn basicSet";
  protected final String TEXT_1343 = "(null, msgs);";
  protected final String TEXT_1344 = NL + "\t\t}";
  protected final String TEXT_1345 = NL + "\t\treturn super.eInverseRemove(otherEnd, featureID, msgs);";
  protected final String TEXT_1346 = NL + "\t\treturn eDynamicInverseRemove(otherEnd, featureID, msgs);";
  protected final String TEXT_1347 = NL + "\t}" + NL;
  protected final String TEXT_1348 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */";
  protected final String TEXT_1349 = NL + "\t@Override";
  protected final String TEXT_1350 = NL + "\tpublic ";
  protected final String TEXT_1351 = " eBasicRemoveFromContainerFeature(";
  protected final String TEXT_1352 = " msgs)" + NL + "\t{" + NL + "\t\tswitch (eContainerFeatureID()";
  protected final String TEXT_1353 = ")" + NL + "\t\t{";
  protected final String TEXT_1354 = NL + "\t\t\tcase ";
  protected final String TEXT_1355 = ":" + NL + "\t\t\t\treturn eInternalContainer().eInverseRemove(this, ";
  protected final String TEXT_1356 = ", ";
  protected final String TEXT_1357 = ".class, msgs);";
  protected final String TEXT_1358 = NL + "\t\t}";
  protected final String TEXT_1359 = NL + "\t\treturn super.eBasicRemoveFromContainerFeature(msgs);";
  protected final String TEXT_1360 = NL + "\t\treturn eDynamicBasicRemoveFromContainer(msgs);";
  protected final String TEXT_1361 = NL + "\t}" + NL;
  protected final String TEXT_1362 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */";
  protected final String TEXT_1363 = NL + "\t@Override";
  protected final String TEXT_1364 = NL + "\tpublic Object eGet(int featureID, boolean resolve, boolean coreType)" + NL + "\t{" + NL + "\t\tswitch (featureID";
  protected final String TEXT_1365 = ")" + NL + "\t\t{";
  protected final String TEXT_1366 = NL + "\t\t\tcase ";
  protected final String TEXT_1367 = ":";
  protected final String TEXT_1368 = NL + "\t\t\t\treturn ";
  protected final String TEXT_1369 = "();";
  protected final String TEXT_1370 = NL + "\t\t\t\treturn ";
  protected final String TEXT_1371 = "() ? Boolean.TRUE : Boolean.FALSE;";
  protected final String TEXT_1372 = NL + "\t\t\t\treturn new ";
  protected final String TEXT_1373 = "(";
  protected final String TEXT_1374 = "());";
  protected final String TEXT_1375 = NL + "\t\t\t\tif (resolve) return ";
  protected final String TEXT_1376 = "();" + NL + "\t\t\t\treturn basicGet";
  protected final String TEXT_1377 = "();";
  protected final String TEXT_1378 = NL + "\t\t\t\tif (coreType) return ((";
  protected final String TEXT_1379 = ".InternalMapView";
  protected final String TEXT_1380 = ")";
  protected final String TEXT_1381 = "()).eMap();" + NL + "\t\t\t\telse return ";
  protected final String TEXT_1382 = "();";
  protected final String TEXT_1383 = NL + "\t\t\t\tif (coreType) return ";
  protected final String TEXT_1384 = "();" + NL + "\t\t\t\telse return ";
  protected final String TEXT_1385 = "().map();";
  protected final String TEXT_1386 = NL + "\t\t\t\tif (coreType) return ((";
  protected final String TEXT_1387 = ".Internal.Wrapper)";
  protected final String TEXT_1388 = "()).featureMap();" + NL + "\t\t\t\treturn ";
  protected final String TEXT_1389 = "();";
  protected final String TEXT_1390 = NL + "\t\t\t\tif (coreType) return ";
  protected final String TEXT_1391 = "();" + NL + "\t\t\t\treturn ((";
  protected final String TEXT_1392 = ".Internal)";
  protected final String TEXT_1393 = "()).getWrapper();";
  protected final String TEXT_1394 = NL + "\t\t\t\treturn ";
  protected final String TEXT_1395 = "();";
  protected final String TEXT_1396 = NL + "\t\t}";
  protected final String TEXT_1397 = NL + "\t\treturn super.eGet(featureID, resolve, coreType);";
  protected final String TEXT_1398 = NL + "\t\treturn eDynamicGet(featureID, resolve, coreType);";
  protected final String TEXT_1399 = NL + "\t}" + NL;
  protected final String TEXT_1400 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */";
  protected final String TEXT_1401 = NL + "\t@SuppressWarnings(\"unchecked\")";
  protected final String TEXT_1402 = NL + "\t@Override";
  protected final String TEXT_1403 = NL + "\tpublic void eSet(int featureID, Object newValue)" + NL + "\t{" + NL + "\t\tswitch (featureID";
  protected final String TEXT_1404 = ")" + NL + "\t\t{";
  protected final String TEXT_1405 = NL + "\t\t\tcase ";
  protected final String TEXT_1406 = ":";
  protected final String TEXT_1407 = NL + "\t\t\t\t((";
  protected final String TEXT_1408 = ".Internal)((";
  protected final String TEXT_1409 = ".Internal.Wrapper)";
  protected final String TEXT_1410 = "()).featureMap()).set(newValue);";
  protected final String TEXT_1411 = NL + "\t\t\t\t((";
  protected final String TEXT_1412 = ".Internal)";
  protected final String TEXT_1413 = "()).set(newValue);";
  protected final String TEXT_1414 = NL + "\t\t\t\t((";
  protected final String TEXT_1415 = ".Setting)((";
  protected final String TEXT_1416 = ".InternalMapView";
  protected final String TEXT_1417 = ")";
  protected final String TEXT_1418 = "()).eMap()).set(newValue);";
  protected final String TEXT_1419 = NL + "\t\t\t\t((";
  protected final String TEXT_1420 = ".Setting)";
  protected final String TEXT_1421 = "()).set(newValue);";
  protected final String TEXT_1422 = NL + "\t\t\t\t";
  protected final String TEXT_1423 = "().clear();" + NL + "\t\t\t\t";
  protected final String TEXT_1424 = "().addAll((";
  protected final String TEXT_1425 = "<? extends ";
  protected final String TEXT_1426 = ">";
  protected final String TEXT_1427 = ")newValue);";
  protected final String TEXT_1428 = NL + "\t\t\t\tset";
  protected final String TEXT_1429 = "(((";
  protected final String TEXT_1430 = ")newValue).";
  protected final String TEXT_1431 = "());";
  protected final String TEXT_1432 = NL + "\t\t\t\tset";
  protected final String TEXT_1433 = "(";
  protected final String TEXT_1434 = "(";
  protected final String TEXT_1435 = ")";
  protected final String TEXT_1436 = "newValue);";
  protected final String TEXT_1437 = NL + "\t\t\t\treturn;";
  protected final String TEXT_1438 = NL + "\t\t}";
  protected final String TEXT_1439 = NL + "\t\tsuper.eSet(featureID, newValue);";
  protected final String TEXT_1440 = NL + "\t\teDynamicSet(featureID, newValue);";
  protected final String TEXT_1441 = NL + "\t}" + NL;
  protected final String TEXT_1442 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */";
  protected final String TEXT_1443 = NL + "\t@Override";
  protected final String TEXT_1444 = NL + "\tpublic void eUnset(int featureID)" + NL + "\t{" + NL + "\t\tswitch (featureID";
  protected final String TEXT_1445 = ")" + NL + "\t\t{";
  protected final String TEXT_1446 = NL + "\t\t\tcase ";
  protected final String TEXT_1447 = ":";
  protected final String TEXT_1448 = NL + "\t\t\t\t((";
  protected final String TEXT_1449 = ".Internal.Wrapper)";
  protected final String TEXT_1450 = "()).featureMap().clear();";
  protected final String TEXT_1451 = NL + "\t\t\t\t";
  protected final String TEXT_1452 = "().clear();";
  protected final String TEXT_1453 = NL + "\t\t\t\tunset";
  protected final String TEXT_1454 = "();";
  protected final String TEXT_1455 = NL + "\t\t\t\tset";
  protected final String TEXT_1456 = "((";
  protected final String TEXT_1457 = ")null);";
  protected final String TEXT_1458 = NL + "\t\t\t\t";
  protected final String TEXT_1459 = "__ESETTING_DELEGATE.dynamicUnset(this, null, 0);";
  protected final String TEXT_1460 = NL + "\t\t\t\tset";
  protected final String TEXT_1461 = "(";
  protected final String TEXT_1462 = ");";
  protected final String TEXT_1463 = NL + "\t\t\t\treturn;";
  protected final String TEXT_1464 = NL + "\t\t}";
  protected final String TEXT_1465 = NL + "\t\tsuper.eUnset(featureID);";
  protected final String TEXT_1466 = NL + "\t\teDynamicUnset(featureID);";
  protected final String TEXT_1467 = NL + "\t}" + NL;
  protected final String TEXT_1468 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */";
  protected final String TEXT_1469 = NL + "\t@SuppressWarnings(\"unchecked\")";
  protected final String TEXT_1470 = NL + "\t@Override";
  protected final String TEXT_1471 = NL + "\tpublic boolean eIsSet(int featureID)" + NL + "\t{" + NL + "\t\tswitch (featureID";
  protected final String TEXT_1472 = ")" + NL + "\t\t{";
  protected final String TEXT_1473 = NL + "\t\t\tcase ";
  protected final String TEXT_1474 = ":";
  protected final String TEXT_1475 = NL + "\t\t\t\treturn isSet";
  protected final String TEXT_1476 = "();";
  protected final String TEXT_1477 = NL + "\t\t\t\treturn ";
  protected final String TEXT_1478 = "__ESETTING_DELEGATE.dynamicIsSet(this, null, 0);";
  protected final String TEXT_1479 = NL + "\t\t\t\treturn !((";
  protected final String TEXT_1480 = ".Internal.Wrapper)";
  protected final String TEXT_1481 = "()).featureMap().isEmpty();";
  protected final String TEXT_1482 = NL + "\t\t\t\treturn ";
  protected final String TEXT_1483 = " != null && !";
  protected final String TEXT_1484 = ".featureMap().isEmpty();";
  protected final String TEXT_1485 = NL + "\t\t\t\treturn ";
  protected final String TEXT_1486 = " != null && !";
  protected final String TEXT_1487 = ".isEmpty();";
  protected final String TEXT_1488 = NL + "\t\t\t\t";
  protected final String TEXT_1489 = " ";
  protected final String TEXT_1490 = " = (";
  protected final String TEXT_1491 = ")eVirtualGet(";
  protected final String TEXT_1492 = ");" + NL + "\t\t\t\treturn ";
  protected final String TEXT_1493 = " != null && !";
  protected final String TEXT_1494 = ".isEmpty();";
  protected final String TEXT_1495 = NL + "\t\t\t\treturn !";
  protected final String TEXT_1496 = "().isEmpty();";
  protected final String TEXT_1497 = NL + "\t\t\t\treturn isSet";
  protected final String TEXT_1498 = "();";
  protected final String TEXT_1499 = NL + "\t\t\t\treturn ";
  protected final String TEXT_1500 = " != null;";
  protected final String TEXT_1501 = NL + "\t\t\t\treturn eVirtualGet(";
  protected final String TEXT_1502 = ") != null;";
  protected final String TEXT_1503 = NL + "\t\t\t\treturn basicGet";
  protected final String TEXT_1504 = "() != null;";
  protected final String TEXT_1505 = NL + "\t\t\t\treturn ";
  protected final String TEXT_1506 = " != null;";
  protected final String TEXT_1507 = NL + "\t\t\t\treturn eVirtualGet(";
  protected final String TEXT_1508 = ") != null;";
  protected final String TEXT_1509 = NL + "\t\t\t\treturn ";
  protected final String TEXT_1510 = "() != null;";
  protected final String TEXT_1511 = NL + "\t\t\t\treturn ((";
  protected final String TEXT_1512 = " & ";
  protected final String TEXT_1513 = "_EFLAG) != 0) != ";
  protected final String TEXT_1514 = ";";
  protected final String TEXT_1515 = NL + "\t\t\t\treturn (";
  protected final String TEXT_1516 = " & ";
  protected final String TEXT_1517 = "_EFLAG) != ";
  protected final String TEXT_1518 = "_EFLAG_DEFAULT;";
  protected final String TEXT_1519 = NL + "\t\t\t\treturn ";
  protected final String TEXT_1520 = " != ";
  protected final String TEXT_1521 = ";";
  protected final String TEXT_1522 = NL + "\t\t\t\treturn eVirtualGet(";
  protected final String TEXT_1523 = ", ";
  protected final String TEXT_1524 = ") != ";
  protected final String TEXT_1525 = ";";
  protected final String TEXT_1526 = NL + "\t\t\t\treturn ";
  protected final String TEXT_1527 = "() != ";
  protected final String TEXT_1528 = ";";
  protected final String TEXT_1529 = NL + "\t\t\t\treturn ";
  protected final String TEXT_1530 = " == null ? ";
  protected final String TEXT_1531 = " != null : !";
  protected final String TEXT_1532 = ".equals(";
  protected final String TEXT_1533 = ");";
  protected final String TEXT_1534 = NL + "\t\t\t\t";
  protected final String TEXT_1535 = " ";
  protected final String TEXT_1536 = " = (";
  protected final String TEXT_1537 = ")eVirtualGet(";
  protected final String TEXT_1538 = ", ";
  protected final String TEXT_1539 = ");" + NL + "\t\t\t\treturn ";
  protected final String TEXT_1540 = " == null ? ";
  protected final String TEXT_1541 = " != null : !";
  protected final String TEXT_1542 = ".equals(";
  protected final String TEXT_1543 = ");";
  protected final String TEXT_1544 = NL + "\t\t\t\treturn ";
  protected final String TEXT_1545 = " == null ? ";
  protected final String TEXT_1546 = "() != null : !";
  protected final String TEXT_1547 = ".equals(";
  protected final String TEXT_1548 = "());";
  protected final String TEXT_1549 = NL + "\t\t}";
  protected final String TEXT_1550 = NL + "\t\treturn super.eIsSet(featureID);";
  protected final String TEXT_1551 = NL + "\t\treturn eDynamicIsSet(featureID);";
  protected final String TEXT_1552 = NL + "\t}" + NL;
  protected final String TEXT_1553 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */";
  protected final String TEXT_1554 = NL + "\t@Override";
  protected final String TEXT_1555 = NL + "\tpublic int eBaseStructuralFeatureID(int derivedFeatureID, Class";
  protected final String TEXT_1556 = " baseClass)" + NL + "\t{";
  protected final String TEXT_1557 = NL + "\t\tif (baseClass == ";
  protected final String TEXT_1558 = ".class)" + NL + "\t\t{" + NL + "\t\t\tswitch (derivedFeatureID";
  protected final String TEXT_1559 = ")" + NL + "\t\t\t{";
  protected final String TEXT_1560 = NL + "\t\t\t\tcase ";
  protected final String TEXT_1561 = ": return ";
  protected final String TEXT_1562 = ";";
  protected final String TEXT_1563 = NL + "\t\t\t\tdefault: return -1;" + NL + "\t\t\t}" + NL + "\t\t}";
  protected final String TEXT_1564 = NL + "\t\treturn super.eBaseStructuralFeatureID(derivedFeatureID, baseClass);" + NL + "\t}";
  protected final String TEXT_1565 = NL + NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */";
  protected final String TEXT_1566 = NL + "\t@Override";
  protected final String TEXT_1567 = NL + "\tpublic int eDerivedStructuralFeatureID(int baseFeatureID, Class";
  protected final String TEXT_1568 = " baseClass)" + NL + "\t{";
  protected final String TEXT_1569 = NL + "\t\tif (baseClass == ";
  protected final String TEXT_1570 = ".class)" + NL + "\t\t{" + NL + "\t\t\tswitch (baseFeatureID)" + NL + "\t\t\t{";
  protected final String TEXT_1571 = NL + "\t\t\t\tcase ";
  protected final String TEXT_1572 = ": return ";
  protected final String TEXT_1573 = ";";
  protected final String TEXT_1574 = NL + "\t\t\t\tdefault: return -1;" + NL + "\t\t\t}" + NL + "\t\t}";
  protected final String TEXT_1575 = NL + "\t\tif (baseClass == ";
  protected final String TEXT_1576 = ".class)" + NL + "\t\t{" + NL + "\t\t\tswitch (baseFeatureID";
  protected final String TEXT_1577 = ")" + NL + "\t\t\t{";
  protected final String TEXT_1578 = NL + "\t\t\t\tcase ";
  protected final String TEXT_1579 = ": return ";
  protected final String TEXT_1580 = ";";
  protected final String TEXT_1581 = NL + "\t\t\t\tdefault: return -1;" + NL + "\t\t\t}" + NL + "\t\t}";
  protected final String TEXT_1582 = NL + "\t\treturn super.eDerivedStructuralFeatureID(baseFeatureID, baseClass);" + NL + "\t}" + NL;
  protected final String TEXT_1583 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */";
  protected final String TEXT_1584 = NL + "\t@Override";
  protected final String TEXT_1585 = NL + "\tpublic int eDerivedOperationID(int baseOperationID, Class";
  protected final String TEXT_1586 = " baseClass)" + NL + "\t{";
  protected final String TEXT_1587 = NL + "\t\tif (baseClass == ";
  protected final String TEXT_1588 = ".class)" + NL + "\t\t{" + NL + "\t\t\tswitch (baseOperationID)" + NL + "\t\t\t{";
  protected final String TEXT_1589 = NL + "\t\t\t\tcase ";
  protected final String TEXT_1590 = ": return ";
  protected final String TEXT_1591 = ";";
  protected final String TEXT_1592 = NL + "\t\t\t\tdefault: return super.eDerivedOperationID(baseOperationID, baseClass);" + NL + "\t\t\t}" + NL + "\t\t}";
  protected final String TEXT_1593 = NL + "\t\tif (baseClass == ";
  protected final String TEXT_1594 = ".class)" + NL + "\t\t{" + NL + "\t\t\tswitch (baseOperationID)" + NL + "\t\t\t{";
  protected final String TEXT_1595 = NL + "\t\t\t\tcase ";
  protected final String TEXT_1596 = ": return ";
  protected final String TEXT_1597 = ";";
  protected final String TEXT_1598 = NL + "\t\t\t\tdefault: return -1;" + NL + "\t\t\t}" + NL + "\t\t}";
  protected final String TEXT_1599 = NL + "\t\tif (baseClass == ";
  protected final String TEXT_1600 = ".class)" + NL + "\t\t{" + NL + "\t\t\tswitch (baseOperationID";
  protected final String TEXT_1601 = ")" + NL + "\t\t\t{";
  protected final String TEXT_1602 = NL + "\t\t\t\tcase ";
  protected final String TEXT_1603 = ": return ";
  protected final String TEXT_1604 = ";";
  protected final String TEXT_1605 = NL + "\t\t\t\tdefault: return -1;" + NL + "\t\t\t}" + NL + "\t\t}";
  protected final String TEXT_1606 = NL + "\t\treturn super.eDerivedOperationID(baseOperationID, baseClass);" + NL + "\t}" + NL;
  protected final String TEXT_1607 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */";
  protected final String TEXT_1608 = NL + "\t@Override";
  protected final String TEXT_1609 = NL + "\tprotected Object[] eVirtualValues()" + NL + "\t{" + NL + "\t\treturn ";
  protected final String TEXT_1610 = ";" + NL + "\t}" + NL + "" + NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */";
  protected final String TEXT_1611 = NL + "\t@Override";
  protected final String TEXT_1612 = NL + "\tprotected void eSetVirtualValues(Object[] newValues)" + NL + "\t{" + NL + "\t\t";
  protected final String TEXT_1613 = " = newValues;" + NL + "\t}" + NL;
  protected final String TEXT_1614 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */";
  protected final String TEXT_1615 = NL + "\t@Override";
  protected final String TEXT_1616 = NL + "\tprotected int eVirtualIndexBits(int offset)" + NL + "\t{" + NL + "\t\tswitch (offset)" + NL + "\t\t{";
  protected final String TEXT_1617 = NL + "\t\t\tcase ";
  protected final String TEXT_1618 = " :" + NL + "\t\t\t\treturn ";
  protected final String TEXT_1619 = ";";
  protected final String TEXT_1620 = NL + "\t\t\tdefault :" + NL + "\t\t\t\tthrow new IndexOutOfBoundsException();" + NL + "\t\t}" + NL + "\t}" + NL + "" + NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */";
  protected final String TEXT_1621 = NL + "\t@Override";
  protected final String TEXT_1622 = NL + "\tprotected void eSetVirtualIndexBits(int offset, int newIndexBits)" + NL + "\t{" + NL + "\t\tswitch (offset)" + NL + "\t\t{";
  protected final String TEXT_1623 = NL + "\t\t\tcase ";
  protected final String TEXT_1624 = " :" + NL + "\t\t\t\t";
  protected final String TEXT_1625 = " = newIndexBits;" + NL + "\t\t\t\tbreak;";
  protected final String TEXT_1626 = NL + "\t\t\tdefault :" + NL + "\t\t\t\tthrow new IndexOutOfBoundsException();" + NL + "\t\t}" + NL + "\t}" + NL;
  protected final String TEXT_1627 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */";
  protected final String TEXT_1628 = NL + "\t@Override";
  protected final String TEXT_1629 = NL + "\t@SuppressWarnings(";
  protected final String TEXT_1630 = "\"unchecked\"";
  protected final String TEXT_1631 = "{\"rawtypes\", \"unchecked\" }";
  protected final String TEXT_1632 = ")";
  protected final String TEXT_1633 = NL + "\tpublic Object eInvoke(int operationID, ";
  protected final String TEXT_1634 = " arguments) throws ";
  protected final String TEXT_1635 = NL + "\t{" + NL + "\t\tswitch (operationID";
  protected final String TEXT_1636 = ")" + NL + "\t\t{";
  protected final String TEXT_1637 = NL + "\t\t\tcase ";
  protected final String TEXT_1638 = ":";
  protected final String TEXT_1639 = NL + "\t\t\t\t";
  protected final String TEXT_1640 = "(";
  protected final String TEXT_1641 = "(";
  protected final String TEXT_1642 = "(";
  protected final String TEXT_1643 = ")";
  protected final String TEXT_1644 = "arguments.get(";
  protected final String TEXT_1645 = ")";
  protected final String TEXT_1646 = ").";
  protected final String TEXT_1647 = "()";
  protected final String TEXT_1648 = ", ";
  protected final String TEXT_1649 = ");" + NL + "\t\t\t\treturn null;";
  protected final String TEXT_1650 = NL + "\t\t\t\treturn ";
  protected final String TEXT_1651 = "new ";
  protected final String TEXT_1652 = "(";
  protected final String TEXT_1653 = "(";
  protected final String TEXT_1654 = "(";
  protected final String TEXT_1655 = "(";
  protected final String TEXT_1656 = ")";
  protected final String TEXT_1657 = "arguments.get(";
  protected final String TEXT_1658 = ")";
  protected final String TEXT_1659 = ").";
  protected final String TEXT_1660 = "()";
  protected final String TEXT_1661 = ", ";
  protected final String TEXT_1662 = ")";
  protected final String TEXT_1663 = ")";
  protected final String TEXT_1664 = ";";
  protected final String TEXT_1665 = NL + "\t\t}";
  protected final String TEXT_1666 = NL + "\t\treturn super.eInvoke(operationID, arguments);";
  protected final String TEXT_1667 = NL + "\t\treturn eDynamicInvoke(operationID, arguments);";
  protected final String TEXT_1668 = NL + "\t}" + NL;
  protected final String TEXT_1669 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */";
  protected final String TEXT_1670 = NL + "\t@Override";
  protected final String TEXT_1671 = NL + "\tpublic String toString()" + NL + "\t{" + NL + "\t\tif (eIsProxy()) return super.toString();" + NL + "" + NL + "\t\tStringBuffer result = new StringBuffer(super.toString());";
  protected final String TEXT_1672 = NL + "\t\tresult.append(\" (";
  protected final String TEXT_1673 = ": \");";
  protected final String TEXT_1674 = NL + "\t\tresult.append(\", ";
  protected final String TEXT_1675 = ": \");";
  protected final String TEXT_1676 = NL + "\t\tif (eVirtualIsSet(";
  protected final String TEXT_1677 = ")) result.append(eVirtualGet(";
  protected final String TEXT_1678 = ")); else result.append(\"<unset>\");";
  protected final String TEXT_1679 = NL + "\t\tif (";
  protected final String TEXT_1680 = "(";
  protected final String TEXT_1681 = " & ";
  protected final String TEXT_1682 = "_ESETFLAG) != 0";
  protected final String TEXT_1683 = "ESet";
  protected final String TEXT_1684 = ") result.append((";
  protected final String TEXT_1685 = " & ";
  protected final String TEXT_1686 = "_EFLAG) != 0); else result.append(\"<unset>\");";
  protected final String TEXT_1687 = NL + "\t\tif (";
  protected final String TEXT_1688 = "(";
  protected final String TEXT_1689 = " & ";
  protected final String TEXT_1690 = "_ESETFLAG) != 0";
  protected final String TEXT_1691 = "ESet";
  protected final String TEXT_1692 = ") result.append(";
  protected final String TEXT_1693 = "_EFLAG_VALUES[(";
  protected final String TEXT_1694 = " & ";
  protected final String TEXT_1695 = "_EFLAG) >>> ";
  protected final String TEXT_1696 = "_EFLAG_OFFSET]); else result.append(\"<unset>\");";
  protected final String TEXT_1697 = NL + "\t\tif (";
  protected final String TEXT_1698 = "(";
  protected final String TEXT_1699 = " & ";
  protected final String TEXT_1700 = "_ESETFLAG) != 0";
  protected final String TEXT_1701 = "ESet";
  protected final String TEXT_1702 = ") result.append(";
  protected final String TEXT_1703 = "); else result.append(\"<unset>\");";
  protected final String TEXT_1704 = NL + "\t\tresult.append(eVirtualGet(";
  protected final String TEXT_1705 = ", ";
  protected final String TEXT_1706 = "));";
  protected final String TEXT_1707 = NL + "\t\tresult.append((";
  protected final String TEXT_1708 = " & ";
  protected final String TEXT_1709 = "_EFLAG) != 0);";
  protected final String TEXT_1710 = NL + "\t\tresult.append(";
  protected final String TEXT_1711 = "_EFLAG_VALUES[(";
  protected final String TEXT_1712 = " & ";
  protected final String TEXT_1713 = "_EFLAG) >>> ";
  protected final String TEXT_1714 = "_EFLAG_OFFSET]);";
  protected final String TEXT_1715 = NL + "\t\tresult.append(";
  protected final String TEXT_1716 = ");";
  protected final String TEXT_1717 = NL + "\t\tresult.append(')');" + NL + "\t\treturn result.toString();" + NL + "\t}" + NL;
  protected final String TEXT_1718 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */";
  protected final String TEXT_1719 = NL + "\t@";
  protected final String TEXT_1720 = NL + "\tprotected int hash = -1;" + NL + "" + NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */" + NL + "\tpublic int getHash()" + NL + "\t{" + NL + "\t\tif (hash == -1)" + NL + "\t\t{" + NL + "\t\t\t";
  protected final String TEXT_1721 = " theKey = getKey();" + NL + "\t\t\thash = (theKey == null ? 0 : theKey.hashCode());" + NL + "\t\t}" + NL + "\t\treturn hash;" + NL + "\t}" + NL + "" + NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */" + NL + "\tpublic void setHash(int hash)" + NL + "\t{" + NL + "\t\tthis.hash = hash;" + NL + "\t}" + NL + "" + NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */" + NL + "\tpublic ";
  protected final String TEXT_1722 = " getKey()" + NL + "\t{";
  protected final String TEXT_1723 = NL + "\t\treturn new ";
  protected final String TEXT_1724 = "(getTypedKey());";
  protected final String TEXT_1725 = NL + "\t\treturn getTypedKey();";
  protected final String TEXT_1726 = NL + "\t}" + NL + "" + NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */" + NL + "\tpublic void setKey(";
  protected final String TEXT_1727 = " key)" + NL + "\t{";
  protected final String TEXT_1728 = NL + "\t\tgetTypedKey().addAll(";
  protected final String TEXT_1729 = "(";
  protected final String TEXT_1730 = ")";
  protected final String TEXT_1731 = "key);";
  protected final String TEXT_1732 = NL + "\t\tsetTypedKey(key);";
  protected final String TEXT_1733 = NL + "\t\tsetTypedKey(((";
  protected final String TEXT_1734 = ")key).";
  protected final String TEXT_1735 = "());";
  protected final String TEXT_1736 = NL + "\t\tsetTypedKey((";
  protected final String TEXT_1737 = ")key);";
  protected final String TEXT_1738 = NL + "\t}" + NL + "" + NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */" + NL + "\tpublic ";
  protected final String TEXT_1739 = " getValue()" + NL + "\t{";
  protected final String TEXT_1740 = NL + "\t\treturn new ";
  protected final String TEXT_1741 = "(getTypedValue());";
  protected final String TEXT_1742 = NL + "\t\treturn getTypedValue();";
  protected final String TEXT_1743 = NL + "\t}" + NL + "" + NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */" + NL + "\tpublic ";
  protected final String TEXT_1744 = " setValue(";
  protected final String TEXT_1745 = " value)" + NL + "\t{" + NL + "\t\t";
  protected final String TEXT_1746 = " oldValue = getValue();";
  protected final String TEXT_1747 = NL + "\t\tgetTypedValue().clear();" + NL + "\t\tgetTypedValue().addAll(";
  protected final String TEXT_1748 = "(";
  protected final String TEXT_1749 = ")";
  protected final String TEXT_1750 = "value);";
  protected final String TEXT_1751 = NL + "\t\tsetTypedValue(value);";
  protected final String TEXT_1752 = NL + "\t\tsetTypedValue(((";
  protected final String TEXT_1753 = ")value).";
  protected final String TEXT_1754 = "());";
  protected final String TEXT_1755 = NL + "\t\tsetTypedValue((";
  protected final String TEXT_1756 = ")value);";
  protected final String TEXT_1757 = NL + "\t\treturn oldValue;" + NL + "\t}" + NL + "" + NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */";
  protected final String TEXT_1758 = NL + "\t@SuppressWarnings(\"unchecked\")";
  protected final String TEXT_1759 = NL + "\tpublic ";
  protected final String TEXT_1760 = " getEMap()" + NL + "\t{" + NL + "\t\t";
  protected final String TEXT_1761 = " container = eContainer();" + NL + "\t\treturn container == null ? null : (";
  protected final String TEXT_1762 = ")container.eGet(eContainmentFeature());" + NL + "\t}" + NL;
  protected final String TEXT_1763 = NL + "} //";
  protected final String TEXT_1764 = NL;

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
    stringBuffer.append(TEXT_30);
    //Class/interface.javadoc.override.javajetinc
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
    if (genModel.hasCopyrightField()) {
    stringBuffer.append(TEXT_45);
    stringBuffer.append(publicStaticFinalFlag);
    stringBuffer.append(genModel.getImportedName("java.lang.String"));
    stringBuffer.append(TEXT_46);
    stringBuffer.append(genModel.getCopyrightFieldLiteral());
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
    if (isGWT) {
    stringBuffer.append(TEXT_55);
    stringBuffer.append(genModel.getImportedName("com.google.gwt.user.client.rpc.GwtTransient"));
    }
    stringBuffer.append(TEXT_56);
    stringBuffer.append(eVirtualValuesField);
    stringBuffer.append(TEXT_57);
    }
    { List<String> eVirtualIndexBitFields = genClass.getEVirtualIndexBitFields(new ArrayList<String>());
    if (!eVirtualIndexBitFields.isEmpty()) {
    for (String eVirtualIndexBitField : eVirtualIndexBitFields) {
    stringBuffer.append(TEXT_58);
    if (isGWT) {
    stringBuffer.append(TEXT_59);
    stringBuffer.append(genModel.getImportedName("com.google.gwt.user.client.rpc.GwtTransient"));
    }
    stringBuffer.append(TEXT_60);
    stringBuffer.append(eVirtualIndexBitField);
    stringBuffer.append(TEXT_61);
    }
    }
    }
    }
    if (isImplementation && genClass.isModelRoot() && genModel.isBooleanFlagsEnabled() && genModel.getBooleanFlagsReservedBits() == -1) {
    stringBuffer.append(TEXT_62);
    if (isGWT) {
    stringBuffer.append(TEXT_63);
    stringBuffer.append(genModel.getImportedName("com.google.gwt.user.client.rpc.GwtTransient"));
    }
    stringBuffer.append(TEXT_64);
    stringBuffer.append(genModel.getBooleanFlagsField());
    stringBuffer.append(TEXT_65);
    }
    if (isImplementation && !genModel.isReflectiveDelegation()) {
    for (GenFeature genFeature : genClass.getDeclaredFieldGenFeatures()) {
    if (genFeature.hasSettingDelegate()) {
    stringBuffer.append(TEXT_66);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_67);
    stringBuffer.append(genFeature.getFormattedName());
    stringBuffer.append(TEXT_68);
    stringBuffer.append(genFeature.getFeatureKind());
    stringBuffer.append(TEXT_69);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_70);
    if (isGWT) {
    stringBuffer.append(TEXT_71);
    stringBuffer.append(genModel.getImportedName("com.google.gwt.user.client.rpc.GwtTransient"));
    }
    stringBuffer.append(TEXT_72);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.EStructuralFeature"));
    stringBuffer.append(TEXT_73);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_74);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.EStructuralFeature"));
    stringBuffer.append(TEXT_75);
    stringBuffer.append(genFeature.getQualifiedFeatureAccessor());
    stringBuffer.append(TEXT_76);
    } else if (genFeature.isListType() || genFeature.isReferenceType()) {
    if (genClass.isField(genFeature)) {
    stringBuffer.append(TEXT_77);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_78);
    stringBuffer.append(genFeature.getFormattedName());
    stringBuffer.append(TEXT_79);
    stringBuffer.append(genFeature.getFeatureKind());
    stringBuffer.append(TEXT_80);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_81);
    if (isGWT) {
    stringBuffer.append(TEXT_82);
    stringBuffer.append(genModel.getImportedName("com.google.gwt.user.client.rpc.GwtTransient"));
    }
    stringBuffer.append(TEXT_83);
    stringBuffer.append(genFeature.getImportedInternalType(genClass));
    stringBuffer.append(TEXT_84);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_85);
    }
    if (genModel.isArrayAccessors() && genFeature.isListType() && !genFeature.isFeatureMapType() && !genFeature.isMapType()) { String rawListItemType = genFeature.getRawListItemType(); int index = rawListItemType.indexOf('['); String head = rawListItemType; String tail = ""; if (index != -1) { head = rawListItemType.substring(0, index); tail = rawListItemType.substring(index); } 
    stringBuffer.append(TEXT_86);
    stringBuffer.append(genFeature.getGetArrayAccessor());
    stringBuffer.append(TEXT_87);
    stringBuffer.append(genFeature.getFormattedName());
    stringBuffer.append(TEXT_88);
    stringBuffer.append(genFeature.getGetArrayAccessor());
    stringBuffer.append(TEXT_89);
    if (genFeature.getQualifiedListItemType(genClass).contains("<")) {
    stringBuffer.append(TEXT_90);
    }
    stringBuffer.append(TEXT_91);
    stringBuffer.append(rawListItemType);
    stringBuffer.append(TEXT_92);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_93);
    stringBuffer.append(head);
    stringBuffer.append(TEXT_94);
    stringBuffer.append(tail);
    stringBuffer.append(TEXT_95);
    }
    } else {
    if (genFeature.hasEDefault() && (!genFeature.isVolatile() || !genModel.isReflectiveDelegation() && (!genFeature.hasDelegateFeature() || !genFeature.isUnsettable()))) { String staticDefaultValue = genFeature.getStaticDefaultValue();
    stringBuffer.append(TEXT_96);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_97);
    stringBuffer.append(genFeature.getFormattedName());
    stringBuffer.append(TEXT_98);
    stringBuffer.append(genFeature.getFeatureKind());
    stringBuffer.append(TEXT_99);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_100);
    if (genModel.useGenerics() && genFeature.isListDataType() && genFeature.isSetDefaultValue()) {
    stringBuffer.append(TEXT_101);
    }
    stringBuffer.append(TEXT_102);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_103);
    stringBuffer.append(genFeature.getEDefault());
    if ("".equals(staticDefaultValue)) {
    stringBuffer.append(TEXT_104);
    stringBuffer.append(genFeature.getEcoreFeature().getDefaultValueLiteral());
    stringBuffer.append(TEXT_105);
    } else {
    stringBuffer.append(TEXT_106);
    stringBuffer.append(staticDefaultValue);
    stringBuffer.append(TEXT_107);
    stringBuffer.append(genModel.getNonNLS(staticDefaultValue));
    }
    stringBuffer.append(TEXT_108);
    }
    if (genClass.isField(genFeature)) {
    if (genClass.isFlag(genFeature)) { int flagIndex = genClass.getFlagIndex(genFeature);
    if (flagIndex > 31 && flagIndex % 32 == 0) {
    stringBuffer.append(TEXT_109);
    if (isGWT) {
    stringBuffer.append(TEXT_110);
    stringBuffer.append(genModel.getImportedName("com.google.gwt.user.client.rpc.GwtTransient"));
    }
    stringBuffer.append(TEXT_111);
    stringBuffer.append(genClass.getFlagsField(genFeature));
    stringBuffer.append(TEXT_112);
    }
    if (genFeature.isEnumType()) {
    stringBuffer.append(TEXT_113);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_114);
    stringBuffer.append(genFeature.getFormattedName());
    stringBuffer.append(TEXT_115);
    stringBuffer.append(genFeature.getFeatureKind());
    stringBuffer.append(TEXT_116);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_117);
    stringBuffer.append(flagIndex % 32);
    stringBuffer.append(TEXT_118);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_119);
    stringBuffer.append(genFeature.getFormattedName());
    stringBuffer.append(TEXT_120);
    stringBuffer.append(genFeature.getFeatureKind());
    stringBuffer.append(TEXT_121);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_122);
    if (isJDK50) {
    stringBuffer.append(genFeature.getEDefault());
    stringBuffer.append(TEXT_123);
    } else {
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_124);
    stringBuffer.append(genFeature.getEDefault());
    stringBuffer.append(TEXT_125);
    }
    stringBuffer.append(TEXT_126);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_127);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_128);
    stringBuffer.append(genFeature.getTypeGenClassifier().getFormattedName());
    stringBuffer.append(TEXT_129);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_130);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_131);
    if (isJDK50) {
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_132);
    } else {
    stringBuffer.append(TEXT_133);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_134);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_135);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_136);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_137);
    }
    stringBuffer.append(TEXT_138);
    }
    stringBuffer.append(TEXT_139);
    stringBuffer.append(genClass.getFlagSize(genFeature) > 1 ? "s" : "");
    stringBuffer.append(TEXT_140);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_141);
    stringBuffer.append(genFeature.getFormattedName());
    stringBuffer.append(TEXT_142);
    stringBuffer.append(genFeature.getFeatureKind());
    stringBuffer.append(TEXT_143);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_144);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_145);
    stringBuffer.append(genClass.getFlagMask(genFeature));
    stringBuffer.append(TEXT_146);
    if (genFeature.isEnumType()) {
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_147);
    } else {
    stringBuffer.append(flagIndex % 32);
    }
    stringBuffer.append(TEXT_148);
    } else {
    stringBuffer.append(TEXT_149);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_150);
    stringBuffer.append(genFeature.getFormattedName());
    stringBuffer.append(TEXT_151);
    stringBuffer.append(genFeature.getFeatureKind());
    stringBuffer.append(TEXT_152);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_153);
    if (isGWT) {
    stringBuffer.append(TEXT_154);
    stringBuffer.append(genModel.getImportedName("com.google.gwt.user.client.rpc.GwtTransient"));
    }
    stringBuffer.append(TEXT_155);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_156);
    stringBuffer.append(genFeature.getSafeName());
    if (genFeature.hasEDefault()) {
    stringBuffer.append(TEXT_157);
    stringBuffer.append(genFeature.getEDefault());
    }
    stringBuffer.append(TEXT_158);
    }
    }
    }
    if (genClass.isESetField(genFeature)) {
    if (genClass.isESetFlag(genFeature)) { int flagIndex = genClass.getESetFlagIndex(genFeature);
    if (flagIndex > 31 && flagIndex % 32 == 0) {
    stringBuffer.append(TEXT_159);
    if (isGWT) {
    stringBuffer.append(TEXT_160);
    stringBuffer.append(genModel.getImportedName("com.google.gwt.user.client.rpc.GwtTransient"));
    }
    stringBuffer.append(TEXT_161);
    stringBuffer.append(genClass.getESetFlagsField(genFeature));
    stringBuffer.append(TEXT_162);
    }
    stringBuffer.append(TEXT_163);
    stringBuffer.append(genFeature.getFormattedName());
    stringBuffer.append(TEXT_164);
    stringBuffer.append(genFeature.getFeatureKind());
    stringBuffer.append(TEXT_165);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_166);
    stringBuffer.append(flagIndex % 32 );
    stringBuffer.append(TEXT_167);
    } else {
    stringBuffer.append(TEXT_168);
    stringBuffer.append(genFeature.getFormattedName());
    stringBuffer.append(TEXT_169);
    stringBuffer.append(genFeature.getFeatureKind());
    stringBuffer.append(TEXT_170);
    if (isGWT) {
    stringBuffer.append(TEXT_171);
    stringBuffer.append(genModel.getImportedName("com.google.gwt.user.client.rpc.GwtTransient"));
    }
    stringBuffer.append(TEXT_172);
    stringBuffer.append(genFeature.getUncapName());
    stringBuffer.append(TEXT_173);
    }
    }
    //Class/declaredFieldGenFeature.override.javajetinc
    }
    }
    if (isImplementation && genClass.hasOffsetCorrection() && !genClass.getImplementedGenFeatures().isEmpty()) {
    stringBuffer.append(TEXT_174);
    stringBuffer.append(genClass.getOffsetCorrectionField(null));
    stringBuffer.append(TEXT_175);
    stringBuffer.append(genClass.getQualifiedClassifierAccessor());
    stringBuffer.append(TEXT_176);
    stringBuffer.append(genClass.getImplementedGenFeatures().get(0).getQualifiedFeatureAccessor());
    stringBuffer.append(TEXT_177);
    stringBuffer.append(genClass.getQualifiedFeatureID(genClass.getImplementedGenFeatures().get(0)));
    stringBuffer.append(TEXT_178);
    }
    if (isImplementation && !genModel.isReflectiveDelegation()) {
    for (GenFeature genFeature : genClass.getImplementedGenFeatures()) { GenFeature reverseFeature = genFeature.getReverse();
    if (reverseFeature != null && reverseFeature.getGenClass().hasOffsetCorrection()) {
    stringBuffer.append(TEXT_179);
    stringBuffer.append(genClass.getOffsetCorrectionField(genFeature));
    stringBuffer.append(TEXT_180);
    stringBuffer.append(reverseFeature.getGenClass().getQualifiedClassifierAccessor());
    stringBuffer.append(TEXT_181);
    stringBuffer.append(reverseFeature.getQualifiedFeatureAccessor());
    stringBuffer.append(TEXT_182);
    stringBuffer.append(reverseFeature.getGenClass().getQualifiedFeatureID(reverseFeature));
    stringBuffer.append(TEXT_183);
    }
    }
    }
    if (genModel.isOperationReflection() && isImplementation && genClass.hasOffsetCorrection() && !genClass.getImplementedGenOperations().isEmpty()) {
    stringBuffer.append(TEXT_184);
    stringBuffer.append(genClass.getQualifiedClassifierAccessor());
    stringBuffer.append(TEXT_185);
    stringBuffer.append(genClass.getImplementedGenOperations().get(0).getQualifiedOperationAccessor());
    stringBuffer.append(TEXT_186);
    stringBuffer.append(genClass.getQualifiedOperationID(genClass.getImplementedGenOperations().get(0)));
    stringBuffer.append(TEXT_187);
    }
    if (isImplementation) {
    stringBuffer.append(TEXT_188);
    if (genModel.isPublicConstructors()) {
    stringBuffer.append(TEXT_189);
    } else {
    stringBuffer.append(TEXT_190);
    }
    stringBuffer.append(TEXT_191);
    stringBuffer.append(genClass.getClassName());
    stringBuffer.append(TEXT_192);
    for (GenFeature genFeature : genClass.getFlagGenFeaturesWithDefault()) {
    stringBuffer.append(TEXT_193);
    stringBuffer.append(genClass.getFlagsField(genFeature));
    stringBuffer.append(TEXT_194);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_195);
    if (!genFeature.isBooleanType()) {
    stringBuffer.append(TEXT_196);
    }
    stringBuffer.append(TEXT_197);
    }
    stringBuffer.append(TEXT_198);
    if (genModel.useClassOverrideAnnotation()) {
    stringBuffer.append(TEXT_199);
    }
    stringBuffer.append(TEXT_200);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.EClass"));
    stringBuffer.append(TEXT_201);
    stringBuffer.append(genClass.getQualifiedClassifierAccessor());
    stringBuffer.append(TEXT_202);
    }
    if (isImplementation && (genModel.getFeatureDelegation() == GenDelegationKind.REFLECTIVE_LITERAL || genModel.isDynamicDelegation()) && (genClass.getClassExtendsGenClass() == null || (genClass.getClassExtendsGenClass().getGenModel().getFeatureDelegation() != GenDelegationKind.REFLECTIVE_LITERAL && !genClass.getClassExtendsGenClass().getGenModel().isDynamicDelegation()))) {
    stringBuffer.append(TEXT_203);
    if (genModel.useClassOverrideAnnotation()) {
    stringBuffer.append(TEXT_204);
    }
    stringBuffer.append(TEXT_205);
    stringBuffer.append(genClass.getClassExtendsGenClass() == null ? 0 : genClass.getClassExtendsGenClass().getAllGenFeatures().size());
    stringBuffer.append(TEXT_206);
    }
    //Class/reflectiveDelegation.override.javajetinc
    new Runnable() { public void run() {
    for (GenFeature genFeature : (isImplementation ? genClass.getImplementedGenFeatures() : genClass.getDeclaredGenFeatures())) {
    if (genModel.isArrayAccessors() && genFeature.isListType() && !genFeature.isFeatureMapType() && !genFeature.isMapType()) {
    stringBuffer.append(TEXT_207);
    if (!isImplementation) {
    stringBuffer.append(TEXT_208);
    stringBuffer.append(genFeature.getListItemType(genClass));
    stringBuffer.append(TEXT_209);
    stringBuffer.append(genFeature.getGetArrayAccessor());
    stringBuffer.append(TEXT_210);
    } else {
    stringBuffer.append(TEXT_211);
    stringBuffer.append(genFeature.getListItemType(genClass));
    stringBuffer.append(TEXT_212);
    stringBuffer.append(genFeature.getGetArrayAccessor());
    stringBuffer.append(TEXT_213);
    if (genFeature.isVolatile()) {
    stringBuffer.append(TEXT_214);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.util.BasicEList"));
    stringBuffer.append(genFeature.getListTemplateArguments(genClass));
    stringBuffer.append(TEXT_215);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.util.BasicEList"));
    stringBuffer.append(genFeature.getListTemplateArguments(genClass));
    stringBuffer.append(TEXT_216);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_217);
    if (genModel.useGenerics() && !genFeature.getListItemType(genClass).contains("<") && !genFeature.getListItemType(null).equals(genFeature.getListItemType(genClass))) {
    stringBuffer.append(TEXT_218);
    stringBuffer.append(genFeature.getListItemType(genClass));
    stringBuffer.append(TEXT_219);
    }
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_220);
    } else {
    stringBuffer.append(TEXT_221);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_222);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_223);
    if (genModel.useGenerics() && !genFeature.getListItemType(genClass).contains("<") && !genFeature.getListItemType(null).equals(genFeature.getListItemType(genClass))) {
    stringBuffer.append(TEXT_224);
    stringBuffer.append(genFeature.getListItemType(genClass));
    stringBuffer.append(TEXT_225);
    }
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_226);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.util.BasicEList"));
    stringBuffer.append(genFeature.getListTemplateArguments(genClass));
    stringBuffer.append(TEXT_227);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.util.BasicEList"));
    stringBuffer.append(genFeature.getListTemplateArguments(genClass));
    stringBuffer.append(TEXT_228);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_229);
    }
    stringBuffer.append(TEXT_230);
    stringBuffer.append(genFeature.getListItemType(genClass));
    stringBuffer.append(TEXT_231);
    }
    stringBuffer.append(TEXT_232);
    if (!isImplementation) {
    stringBuffer.append(TEXT_233);
    stringBuffer.append(genFeature.getListItemType(genClass));
    stringBuffer.append(TEXT_234);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_235);
    } else {
    stringBuffer.append(TEXT_236);
    stringBuffer.append(genFeature.getListItemType(genClass));
    stringBuffer.append(TEXT_237);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_238);
    if (!genModel.useGenerics()) {
    stringBuffer.append(TEXT_239);
    stringBuffer.append(genFeature.getListItemType(genClass));
    stringBuffer.append(TEXT_240);
    }
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_241);
    }
    stringBuffer.append(TEXT_242);
    if (!isImplementation) {
    stringBuffer.append(TEXT_243);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_244);
    } else {
    stringBuffer.append(TEXT_245);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_246);
    if (genFeature.isVolatile()) {
    stringBuffer.append(TEXT_247);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_248);
    } else {
    stringBuffer.append(TEXT_249);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_250);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_251);
    }
    stringBuffer.append(TEXT_252);
    }
    stringBuffer.append(TEXT_253);
    if (!isImplementation) {
    stringBuffer.append(TEXT_254);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_255);
    stringBuffer.append(genFeature.getListItemType(genClass));
    stringBuffer.append(TEXT_256);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_257);
    } else {
    stringBuffer.append(TEXT_258);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_259);
    stringBuffer.append(genFeature.getListItemType(genClass));
    stringBuffer.append(TEXT_260);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_261);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.util.BasicEList"));
    stringBuffer.append(genFeature.getListTemplateArguments(genClass));
    stringBuffer.append(TEXT_262);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_263);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_264);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_265);
    }
    stringBuffer.append(TEXT_266);
    if (!isImplementation) {
    stringBuffer.append(TEXT_267);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_268);
    stringBuffer.append(genFeature.getListItemType(genClass));
    stringBuffer.append(TEXT_269);
    } else {
    stringBuffer.append(TEXT_270);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_271);
    stringBuffer.append(genFeature.getListItemType(genClass));
    stringBuffer.append(TEXT_272);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_273);
    }
    }
    if (genFeature.isGet() && (isImplementation || !genFeature.isSuppressedGetVisibility())) {
    if (isInterface) {
    stringBuffer.append(TEXT_274);
    stringBuffer.append(genFeature.getFormattedName());
    stringBuffer.append(TEXT_275);
    stringBuffer.append(genFeature.getFeatureKind());
    stringBuffer.append(TEXT_276);
    if (genFeature.isListType()) {
    if (genFeature.isMapType()) { GenFeature keyFeature = genFeature.getMapEntryTypeGenClass().getMapEntryKeyFeature(); GenFeature valueFeature = genFeature.getMapEntryTypeGenClass().getMapEntryValueFeature(); 
    stringBuffer.append(TEXT_277);
    if (keyFeature.isListType()) {
    stringBuffer.append(TEXT_278);
    stringBuffer.append(keyFeature.getQualifiedListItemType(genClass));
    stringBuffer.append(TEXT_279);
    } else {
    stringBuffer.append(TEXT_280);
    stringBuffer.append(keyFeature.getType(genClass));
    stringBuffer.append(TEXT_281);
    }
    stringBuffer.append(TEXT_282);
    if (valueFeature.isListType()) {
    stringBuffer.append(TEXT_283);
    stringBuffer.append(valueFeature.getQualifiedListItemType(genClass));
    stringBuffer.append(TEXT_284);
    } else {
    stringBuffer.append(TEXT_285);
    stringBuffer.append(valueFeature.getType(genClass));
    stringBuffer.append(TEXT_286);
    }
    stringBuffer.append(TEXT_287);
    } else if (!genFeature.isWrappedFeatureMapType() && !(genModel.isSuppressEMFMetaData() && "org.eclipse.emf.ecore.EObject".equals(genFeature.getQualifiedListItemType(genClass)))) {
String typeName = genFeature.getQualifiedListItemType(genClass); String head = typeName; String tail = ""; int index = typeName.indexOf('<'); if (index == -1) { index = typeName.indexOf('['); } 
if (index != -1) { head = typeName.substring(0, index); tail = typeName.substring(index).replaceAll("<", "&lt;"); }

    stringBuffer.append(TEXT_288);
    stringBuffer.append(head);
    stringBuffer.append(TEXT_289);
    stringBuffer.append(tail);
    stringBuffer.append(TEXT_290);
    }
    } else if (genFeature.isSetDefaultValue()) {
    stringBuffer.append(TEXT_291);
    stringBuffer.append(genFeature.getDefaultValue());
    stringBuffer.append(TEXT_292);
    }
    if (genFeature.getTypeGenEnum() != null) {
    stringBuffer.append(TEXT_293);
    stringBuffer.append(genFeature.getTypeGenEnum().getQualifiedName());
    stringBuffer.append(TEXT_294);
    }
    if (genFeature.isBidirectional() && !genFeature.getReverse().getGenClass().isMapEntry()) { GenFeature reverseGenFeature = genFeature.getReverse(); 
    if (!reverseGenFeature.isSuppressedGetVisibility()) {
    stringBuffer.append(TEXT_295);
    stringBuffer.append(reverseGenFeature.getGenClass().getQualifiedInterfaceName());
    stringBuffer.append(TEXT_296);
    stringBuffer.append(reverseGenFeature.getGetAccessor());
    stringBuffer.append(TEXT_297);
    stringBuffer.append(reverseGenFeature.getFormattedName());
    stringBuffer.append(TEXT_298);
    }
    }
    stringBuffer.append(TEXT_299);
    if (!genFeature.hasDocumentation()) {
    stringBuffer.append(TEXT_300);
    stringBuffer.append(genFeature.getFormattedName());
    stringBuffer.append(TEXT_301);
    stringBuffer.append(genFeature.getFeatureKind());
    stringBuffer.append(TEXT_302);
    }
    stringBuffer.append(TEXT_303);
    if (genFeature.hasDocumentation()) {
    stringBuffer.append(TEXT_304);
    stringBuffer.append(genFeature.getDocumentation(genModel.getIndentation(stringBuffer)));
    stringBuffer.append(TEXT_305);
    }
    stringBuffer.append(TEXT_306);
    stringBuffer.append(genFeature.getFormattedName());
    stringBuffer.append(TEXT_307);
    stringBuffer.append(genFeature.getFeatureKind());
    stringBuffer.append(TEXT_308);
    if (genFeature.getTypeGenEnum() != null) {
    stringBuffer.append(TEXT_309);
    stringBuffer.append(genFeature.getTypeGenEnum().getQualifiedName());
    }
    if (genFeature.isUnsettable()) {
    if (!genFeature.isSuppressedIsSetVisibility()) {
    stringBuffer.append(TEXT_310);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_311);
    }
    if (genFeature.isChangeable() && !genFeature.isSuppressedUnsetVisibility()) {
    stringBuffer.append(TEXT_312);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_313);
    }
    }
    if (genFeature.isChangeable() && !genFeature.isListType() && !genFeature.isSuppressedSetVisibility()) {
    stringBuffer.append(TEXT_314);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_315);
    stringBuffer.append(genFeature.getRawImportedBoundType());
    stringBuffer.append(TEXT_316);
    }
    if (!genModel.isSuppressEMFMetaData()) {
    stringBuffer.append(TEXT_317);
    stringBuffer.append(genPackage.getQualifiedPackageInterfaceName());
    stringBuffer.append(TEXT_318);
    stringBuffer.append(genFeature.getFeatureAccessorName());
    stringBuffer.append(TEXT_319);
    }
    if (genFeature.isBidirectional() && !genFeature.getReverse().getGenClass().isMapEntry()) { GenFeature reverseGenFeature = genFeature.getReverse(); 
    if (!reverseGenFeature.isSuppressedGetVisibility()) {
    stringBuffer.append(TEXT_320);
    stringBuffer.append(reverseGenFeature.getGenClass().getQualifiedInterfaceName());
    stringBuffer.append(TEXT_321);
    stringBuffer.append(reverseGenFeature.getGetAccessor());
    }
    }
    if (!genModel.isSuppressEMFModelTags()) { boolean first = true; for (StringTokenizer stringTokenizer = new StringTokenizer(genFeature.getModelInfo(), "\n\r"); stringTokenizer.hasMoreTokens(); ) { String modelInfo = stringTokenizer.nextToken(); if (first) { first = false;
    stringBuffer.append(TEXT_322);
    stringBuffer.append(modelInfo);
    } else {
    stringBuffer.append(TEXT_323);
    stringBuffer.append(modelInfo);
    }} if (first) {
    stringBuffer.append(TEXT_324);
    }}
    stringBuffer.append(TEXT_325);
    //Class/getGenFeature.javadoc.override.javajetinc
    } else {
    stringBuffer.append(TEXT_326);
    if (isJDK50) { //Class/getGenFeature.annotations.insert.javajetinc
    }
    }
    if (!isImplementation) {
    stringBuffer.append(TEXT_327);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_328);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_329);
    } else {
    if (genModel.useGenerics() && ((genFeature.isContainer() || genFeature.isResolveProxies()) && !genFeature.isListType() && !(genModel.isReflectiveDelegation() && genModel.isDynamicDelegation()) && genFeature.isUncheckedCast(genClass) || genFeature.isListType() && !genFeature.isFeatureMapType() && (genModel.isReflectiveDelegation() || genModel.isVirtualDelegation() || genModel.isDynamicDelegation()) || genFeature.isListDataType() && genFeature.hasDelegateFeature() || genFeature.isListType() && genFeature.hasSettingDelegate())) {
    stringBuffer.append(TEXT_330);
    }
    stringBuffer.append(TEXT_331);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_332);
    stringBuffer.append(genFeature.getGetAccessor());
    if (genClass.hasCollidingGetAccessorOperation(genFeature)) {
    stringBuffer.append(TEXT_333);
    }
    stringBuffer.append(TEXT_334);
    if (genModel.isDynamicDelegation()) {
    stringBuffer.append(TEXT_335);
    if (!isJDK50 && genFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_336);
    }
    stringBuffer.append(TEXT_337);
    stringBuffer.append(genFeature.getObjectType(genClass));
    stringBuffer.append(TEXT_338);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(TEXT_339);
    stringBuffer.append(genFeature.getQualifiedFeatureAccessor());
    stringBuffer.append(TEXT_340);
    stringBuffer.append(!genFeature.isEffectiveSuppressEMFTypes());
    stringBuffer.append(TEXT_341);
    if (!isJDK50 && genFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_342);
    stringBuffer.append(genFeature.getPrimitiveValueFunction());
    stringBuffer.append(TEXT_343);
    }
    stringBuffer.append(TEXT_344);
    } else if (genModel.isReflectiveDelegation()) {
    stringBuffer.append(TEXT_345);
    if (!isJDK50 && genFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_346);
    }
    stringBuffer.append(TEXT_347);
    stringBuffer.append(genFeature.getObjectType(genClass));
    stringBuffer.append(TEXT_348);
    stringBuffer.append(genFeature.getQualifiedFeatureAccessor());
    stringBuffer.append(TEXT_349);
    if (!isJDK50 && genFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_350);
    stringBuffer.append(genFeature.getPrimitiveValueFunction());
    stringBuffer.append(TEXT_351);
    }
    stringBuffer.append(TEXT_352);
    } else if (genFeature.hasSettingDelegate()) {
    stringBuffer.append(TEXT_353);
    if (!isJDK50 && genFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_354);
    }
    stringBuffer.append(TEXT_355);
    stringBuffer.append(genFeature.getObjectType(genClass));
    stringBuffer.append(TEXT_356);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_357);
    if (!isJDK50 && genFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_358);
    stringBuffer.append(genFeature.getPrimitiveValueFunction());
    stringBuffer.append(TEXT_359);
    }
    stringBuffer.append(TEXT_360);
    } else if (!genFeature.isVolatile()) {
    if (genFeature.isListType()) {
    if (genModel.isVirtualDelegation()) {
    stringBuffer.append(TEXT_361);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_362);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_363);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_364);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(positiveOffsetCorrection);
    stringBuffer.append(TEXT_365);
    }
    stringBuffer.append(TEXT_366);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_367);
    if (genModel.isVirtualDelegation()) {
    stringBuffer.append(TEXT_368);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(positiveOffsetCorrection);
    stringBuffer.append(TEXT_369);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_370);
    stringBuffer.append(genClass.getListConstructor(genFeature));
    stringBuffer.append(TEXT_371);
    } else {
    stringBuffer.append(TEXT_372);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_373);
    stringBuffer.append(genClass.getListConstructor(genFeature));
    stringBuffer.append(TEXT_374);
    }
    stringBuffer.append(TEXT_375);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(genFeature.isMapType() && genFeature.isEffectiveSuppressEMFTypes() ? ".map()" : "");
    stringBuffer.append(TEXT_376);
    } else if (genFeature.isContainer()) {
    stringBuffer.append(TEXT_377);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(positiveOffsetCorrection);
    stringBuffer.append(TEXT_378);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_379);
    if (genFeature.isResolveProxies()) {
    stringBuffer.append(TEXT_380);
    } else {
    stringBuffer.append(TEXT_381);
    }
    stringBuffer.append(TEXT_382);
    } else {
    if (genFeature.isResolveProxies()) {
    if (genModel.isVirtualDelegation()) {
    stringBuffer.append(TEXT_383);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_384);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_385);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_386);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(positiveOffsetCorrection);
    if (genFeature.hasEDefault()) {
    stringBuffer.append(TEXT_387);
    stringBuffer.append(genFeature.getEDefault());
    }
    stringBuffer.append(TEXT_388);
    }
    stringBuffer.append(TEXT_389);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_390);
    stringBuffer.append(genFeature.getSafeNameAsEObject());
    stringBuffer.append(TEXT_391);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.InternalEObject"));
    stringBuffer.append(TEXT_392);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_393);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.InternalEObject"));
    stringBuffer.append(TEXT_394);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_395);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_396);
    stringBuffer.append(genFeature.getNonEObjectInternalTypeCast(genClass));
    stringBuffer.append(TEXT_397);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_398);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_399);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_400);
    if (genFeature.isEffectiveContains()) {
    stringBuffer.append(TEXT_401);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.InternalEObject"));
    stringBuffer.append(TEXT_402);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_403);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.InternalEObject"));
    stringBuffer.append(TEXT_404);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_405);
    if (!genFeature.isBidirectional()) {
    stringBuffer.append(TEXT_406);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.notify.NotificationChain"));
    stringBuffer.append(TEXT_407);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_408);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(negativeOffsetCorrection);
    stringBuffer.append(TEXT_409);
    } else { GenFeature reverseFeature = genFeature.getReverse(); GenClass targetClass = reverseFeature.getGenClass(); String reverseOffsetCorrection = targetClass.hasOffsetCorrection() ? " + " + genClass.getOffsetCorrectionField(genFeature) : "";
    stringBuffer.append(TEXT_410);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.notify.NotificationChain"));
    stringBuffer.append(TEXT_411);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_412);
    stringBuffer.append(targetClass.getQualifiedFeatureID(reverseFeature));
    stringBuffer.append(reverseOffsetCorrection);
    stringBuffer.append(TEXT_413);
    stringBuffer.append(targetClass.getRawImportedInterfaceName());
    stringBuffer.append(TEXT_414);
    }
    stringBuffer.append(TEXT_415);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_416);
    if (!genFeature.isBidirectional()) {
    stringBuffer.append(TEXT_417);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_418);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(negativeOffsetCorrection);
    stringBuffer.append(TEXT_419);
    } else { GenFeature reverseFeature = genFeature.getReverse(); GenClass targetClass = reverseFeature.getGenClass(); String reverseOffsetCorrection = targetClass.hasOffsetCorrection() ? " + " + genClass.getOffsetCorrectionField(genFeature) : "";
    stringBuffer.append(TEXT_420);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_421);
    stringBuffer.append(targetClass.getQualifiedFeatureID(reverseFeature));
    stringBuffer.append(reverseOffsetCorrection);
    stringBuffer.append(TEXT_422);
    stringBuffer.append(targetClass.getRawImportedInterfaceName());
    stringBuffer.append(TEXT_423);
    }
    stringBuffer.append(TEXT_424);
    } else if (genModel.isVirtualDelegation()) {
    stringBuffer.append(TEXT_425);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(positiveOffsetCorrection);
    stringBuffer.append(TEXT_426);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_427);
    }
    if (!genModel.isSuppressNotification()) {
    stringBuffer.append(TEXT_428);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.impl.ENotificationImpl"));
    stringBuffer.append(TEXT_429);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.notify.Notification"));
    stringBuffer.append(TEXT_430);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(positiveOffsetCorrection);
    stringBuffer.append(TEXT_431);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_432);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_433);
    }
    stringBuffer.append(TEXT_434);
    }
    if (!genFeature.isResolveProxies() && genModel.isVirtualDelegation() && !genFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_435);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_436);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(positiveOffsetCorrection);
    if (genFeature.hasEDefault()) {
    stringBuffer.append(TEXT_437);
    stringBuffer.append(genFeature.getEDefault());
    }
    stringBuffer.append(TEXT_438);
    } else if (genClass.isFlag(genFeature)) {
    if (genFeature.isBooleanType()) {
    stringBuffer.append(TEXT_439);
    stringBuffer.append(genClass.getFlagsField(genFeature));
    stringBuffer.append(TEXT_440);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_441);
    } else {
    stringBuffer.append(TEXT_442);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_443);
    stringBuffer.append(genClass.getFlagsField(genFeature));
    stringBuffer.append(TEXT_444);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_445);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_446);
    }
    } else {
    stringBuffer.append(TEXT_447);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_448);
    }
    }
    } else {//volatile
    if (genFeature.isResolveProxies() && !genFeature.isListType()) {
    stringBuffer.append(TEXT_449);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_450);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_451);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_452);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_453);
    stringBuffer.append(genFeature.getSafeNameAsEObject());
    stringBuffer.append(TEXT_454);
    stringBuffer.append(genFeature.getNonEObjectInternalTypeCast(genClass));
    stringBuffer.append(TEXT_455);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.InternalEObject"));
    stringBuffer.append(TEXT_456);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_457);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_458);
    } else if (genFeature.hasDelegateFeature()) { GenFeature delegateFeature = genFeature.getDelegateFeature();
    if (genFeature.isFeatureMapType()) {
    String featureMapEntryTemplateArgument = isJDK50 ? "<" + genModel.getImportedName("org.eclipse.emf.ecore.util.FeatureMap") + ".Entry>" : "";
    if (delegateFeature.isWrappedFeatureMapType()) {
    stringBuffer.append(TEXT_459);
    stringBuffer.append(genFeature.getImportedEffectiveFeatureMapWrapperClass());
    stringBuffer.append(TEXT_460);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.FeatureMap"));
    stringBuffer.append(TEXT_461);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.FeatureMap"));
    stringBuffer.append(TEXT_462);
    stringBuffer.append(delegateFeature.getAccessorName());
    stringBuffer.append(TEXT_463);
    stringBuffer.append(featureMapEntryTemplateArgument);
    stringBuffer.append(TEXT_464);
    stringBuffer.append(genFeature.getQualifiedFeatureAccessor());
    stringBuffer.append(TEXT_465);
    } else {
    stringBuffer.append(TEXT_466);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.FeatureMap"));
    stringBuffer.append(TEXT_467);
    stringBuffer.append(delegateFeature.getAccessorName());
    stringBuffer.append(TEXT_468);
    stringBuffer.append(featureMapEntryTemplateArgument);
    stringBuffer.append(TEXT_469);
    stringBuffer.append(genFeature.getQualifiedFeatureAccessor());
    stringBuffer.append(TEXT_470);
    }
    } else if (genFeature.isListType()) {
    if (delegateFeature.isWrappedFeatureMapType()) {
    stringBuffer.append(TEXT_471);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.FeatureMap"));
    stringBuffer.append(TEXT_472);
    stringBuffer.append(delegateFeature.getAccessorName());
    stringBuffer.append(TEXT_473);
    stringBuffer.append(genFeature.getQualifiedFeatureAccessor());
    stringBuffer.append(TEXT_474);
    } else {
    stringBuffer.append(TEXT_475);
    stringBuffer.append(delegateFeature.getAccessorName());
    stringBuffer.append(TEXT_476);
    stringBuffer.append(genFeature.getQualifiedFeatureAccessor());
    stringBuffer.append(TEXT_477);
    }
    } else {
    if (delegateFeature.isWrappedFeatureMapType()) {
    stringBuffer.append(TEXT_478);
    if (!isJDK50 && genFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_479);
    }
    if (genFeature.getTypeGenDataType() == null || !genFeature.getTypeGenDataType().isObjectType()) {
    stringBuffer.append(TEXT_480);
    stringBuffer.append(genFeature.getObjectType(genClass));
    stringBuffer.append(TEXT_481);
    }
    stringBuffer.append(TEXT_482);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.FeatureMap"));
    stringBuffer.append(TEXT_483);
    stringBuffer.append(delegateFeature.getAccessorName());
    stringBuffer.append(TEXT_484);
    stringBuffer.append(genFeature.getQualifiedFeatureAccessor());
    stringBuffer.append(TEXT_485);
    if (!isJDK50 && genFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_486);
    stringBuffer.append(genFeature.getPrimitiveValueFunction());
    stringBuffer.append(TEXT_487);
    }
    stringBuffer.append(TEXT_488);
    } else {
    stringBuffer.append(TEXT_489);
    if (!isJDK50 && genFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_490);
    }
    if (genFeature.getTypeGenDataType() == null || !genFeature.getTypeGenDataType().isObjectType()) {
    stringBuffer.append(TEXT_491);
    stringBuffer.append(genFeature.getObjectType(genClass));
    stringBuffer.append(TEXT_492);
    }
    stringBuffer.append(TEXT_493);
    stringBuffer.append(delegateFeature.getAccessorName());
    stringBuffer.append(TEXT_494);
    stringBuffer.append(genFeature.getQualifiedFeatureAccessor());
    stringBuffer.append(TEXT_495);
    if (!isJDK50 && genFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_496);
    stringBuffer.append(genFeature.getPrimitiveValueFunction());
    stringBuffer.append(TEXT_497);
    }
    stringBuffer.append(TEXT_498);
    }
    }
    } else if (genClass.getGetAccessorOperation(genFeature) != null) {
    stringBuffer.append(TEXT_499);
    stringBuffer.append(genClass.getGetAccessorOperation(genFeature).getBody(genModel.getIndentation(stringBuffer)));
    } else if (genFeature.hasGetterBody()) {
    stringBuffer.append(TEXT_500);
    stringBuffer.append(genFeature.getGetterBody(genModel.getIndentation(stringBuffer)));
    } else {
    stringBuffer.append(TEXT_501);
    stringBuffer.append(genFeature.getFormattedName());
    stringBuffer.append(TEXT_502);
    stringBuffer.append(genFeature.getFeatureKind());
    stringBuffer.append(TEXT_503);
    if (genFeature.isListType()) {
    stringBuffer.append(TEXT_504);
    if (genFeature.isMapType()) {
    stringBuffer.append(TEXT_505);
    } else if (genFeature.isFeatureMapType()) {
    stringBuffer.append(TEXT_506);
    } else {
    stringBuffer.append(TEXT_507);
    }
    stringBuffer.append(TEXT_508);
    }
    stringBuffer.append(TEXT_509);
    //Class/getGenFeature.todo.override.javajetinc
    }
    }
    stringBuffer.append(TEXT_510);
    }
    //Class/getGenFeature.override.javajetinc
    }
    if (isImplementation && !genModel.isReflectiveDelegation() && genFeature.isBasicGet()) {
    stringBuffer.append(TEXT_511);
    if (isJDK50) { //Class/basicGetGenFeature.annotations.insert.javajetinc
    }
    stringBuffer.append(TEXT_512);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_513);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_514);
    if (genModel.isDynamicDelegation()) {
    stringBuffer.append(TEXT_515);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_516);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(TEXT_517);
    stringBuffer.append(genFeature.getQualifiedFeatureAccessor());
    stringBuffer.append(TEXT_518);
    stringBuffer.append(!genFeature.isEffectiveSuppressEMFTypes());
    stringBuffer.append(TEXT_519);
    } else if (genFeature.hasSettingDelegate()) {
    stringBuffer.append(TEXT_520);
    if (!isJDK50 && genFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_521);
    }
    stringBuffer.append(TEXT_522);
    stringBuffer.append(genFeature.getObjectType(genClass));
    stringBuffer.append(TEXT_523);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_524);
    if (!isJDK50 && genFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_525);
    stringBuffer.append(genFeature.getPrimitiveValueFunction());
    stringBuffer.append(TEXT_526);
    }
    stringBuffer.append(TEXT_527);
    } else if (genFeature.isContainer()) {
    stringBuffer.append(TEXT_528);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(positiveOffsetCorrection);
    stringBuffer.append(TEXT_529);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_530);
    } else if (!genFeature.isVolatile()) {
    if (genModel.isVirtualDelegation()) {
    stringBuffer.append(TEXT_531);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_532);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(positiveOffsetCorrection);
    stringBuffer.append(TEXT_533);
    } else {
    stringBuffer.append(TEXT_534);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_535);
    }
    } else if (genFeature.hasDelegateFeature()) { GenFeature delegateFeature = genFeature.getDelegateFeature();
    if (delegateFeature.isWrappedFeatureMapType()) {
    stringBuffer.append(TEXT_536);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_537);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.FeatureMap"));
    stringBuffer.append(TEXT_538);
    stringBuffer.append(delegateFeature.getAccessorName());
    stringBuffer.append(TEXT_539);
    stringBuffer.append(genFeature.getQualifiedFeatureAccessor());
    stringBuffer.append(TEXT_540);
    } else {
    stringBuffer.append(TEXT_541);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_542);
    stringBuffer.append(delegateFeature.getAccessorName());
    stringBuffer.append(TEXT_543);
    stringBuffer.append(genFeature.getQualifiedFeatureAccessor());
    stringBuffer.append(TEXT_544);
    }
    } else {
    stringBuffer.append(TEXT_545);
    stringBuffer.append(genFeature.getFormattedName());
    stringBuffer.append(TEXT_546);
    stringBuffer.append(genFeature.getFeatureKind());
    stringBuffer.append(TEXT_547);
    //Class/basicGetGenFeature.todo.override.javajetinc
    }
    stringBuffer.append(TEXT_548);
    //Class/basicGetGenFeature.override.javajetinc
    }
    if (isImplementation && !genModel.isReflectiveDelegation() && genFeature.isBasicSet()) {
    stringBuffer.append(TEXT_549);
    if (isJDK50) { //Class/basicSetGenFeature.annotations.insert.javajetinc
    }
    stringBuffer.append(TEXT_550);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.notify.NotificationChain"));
    stringBuffer.append(TEXT_551);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_552);
    stringBuffer.append(genFeature.getImportedInternalType(genClass));
    stringBuffer.append(TEXT_553);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_554);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.notify.NotificationChain"));
    stringBuffer.append(TEXT_555);
    if (genFeature.isContainer()) {
    stringBuffer.append(TEXT_556);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.InternalEObject"));
    stringBuffer.append(TEXT_557);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_558);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(positiveOffsetCorrection);
    stringBuffer.append(TEXT_559);
    stringBuffer.append(TEXT_560);
    } else if (genModel.isDynamicDelegation()) {
    stringBuffer.append(TEXT_561);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.InternalEObject"));
    stringBuffer.append(TEXT_562);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_563);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(positiveOffsetCorrection);
    stringBuffer.append(TEXT_564);
    stringBuffer.append(TEXT_565);
    } else if (!genFeature.isVolatile()) {
    if (genModel.isVirtualDelegation()) {
    stringBuffer.append(TEXT_566);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_567);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(positiveOffsetCorrection);
    stringBuffer.append(TEXT_568);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_569);
    } else {
    stringBuffer.append(TEXT_570);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_571);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_572);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_573);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_574);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_575);
    }
    if (genFeature.isUnsettable()) {
    if (genModel.isVirtualDelegation()) {
    if (!genModel.isSuppressNotification()) {
    stringBuffer.append(TEXT_576);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_577);
    }
    } else if (genClass.isESetFlag(genFeature)) {
    stringBuffer.append(TEXT_578);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_579);
    stringBuffer.append(genClass.getESetFlagsField(genFeature));
    stringBuffer.append(TEXT_580);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_581);
    if (!genModel.isSuppressNotification()) {
    stringBuffer.append(TEXT_582);
    stringBuffer.append(genClass.getESetFlagsField(genFeature));
    stringBuffer.append(TEXT_583);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_584);
    }
    } else {
    if (!genModel.isSuppressNotification()) {
    stringBuffer.append(TEXT_585);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_586);
    stringBuffer.append(genFeature.getUncapName());
    stringBuffer.append(TEXT_587);
    }
    stringBuffer.append(TEXT_588);
    stringBuffer.append(genFeature.getUncapName());
    stringBuffer.append(TEXT_589);
    }
    }
    if (!genModel.isSuppressNotification()) {
    stringBuffer.append(TEXT_590);
    if (genFeature.isUnsettable()) {
    stringBuffer.append(TEXT_591);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.impl.ENotificationImpl"));
    stringBuffer.append(TEXT_592);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.impl.ENotificationImpl"));
    stringBuffer.append(TEXT_593);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.notify.Notification"));
    stringBuffer.append(TEXT_594);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(positiveOffsetCorrection);
    stringBuffer.append(TEXT_595);
    if (genModel.isVirtualDelegation()) {
    stringBuffer.append(TEXT_596);
    stringBuffer.append(genFeature.getCapName());
    } else {
    stringBuffer.append(TEXT_597);
    stringBuffer.append(genFeature.getCapName());
    }
    stringBuffer.append(TEXT_598);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_599);
    if (genModel.isVirtualDelegation()) {
    stringBuffer.append(TEXT_600);
    } else {
    stringBuffer.append(TEXT_601);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_602);
    }
    stringBuffer.append(TEXT_603);
    } else {
    stringBuffer.append(TEXT_604);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.impl.ENotificationImpl"));
    stringBuffer.append(TEXT_605);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.impl.ENotificationImpl"));
    stringBuffer.append(TEXT_606);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.notify.Notification"));
    stringBuffer.append(TEXT_607);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(positiveOffsetCorrection);
    stringBuffer.append(TEXT_608);
    if (genModel.isVirtualDelegation()) {
    stringBuffer.append(TEXT_609);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_610);
    stringBuffer.append(genFeature.getCapName());
    } else {
    stringBuffer.append(TEXT_611);
    stringBuffer.append(genFeature.getCapName());
    }
    stringBuffer.append(TEXT_612);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_613);
    }
    stringBuffer.append(TEXT_614);
    }
    stringBuffer.append(TEXT_615);
    } else if (genFeature.hasDelegateFeature()) { GenFeature delegateFeature = genFeature.getDelegateFeature();
    if (delegateFeature.isWrappedFeatureMapType()) {
    stringBuffer.append(TEXT_616);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.FeatureMap"));
    stringBuffer.append(TEXT_617);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.FeatureMap"));
    stringBuffer.append(TEXT_618);
    stringBuffer.append(delegateFeature.getAccessorName());
    stringBuffer.append(TEXT_619);
    stringBuffer.append(genFeature.getQualifiedFeatureAccessor());
    stringBuffer.append(TEXT_620);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_621);
    } else {
    stringBuffer.append(TEXT_622);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.FeatureMap"));
    stringBuffer.append(TEXT_623);
    stringBuffer.append(delegateFeature.getAccessorName());
    stringBuffer.append(TEXT_624);
    stringBuffer.append(genFeature.getQualifiedFeatureAccessor());
    stringBuffer.append(TEXT_625);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_626);
    }
    } else {
    stringBuffer.append(TEXT_627);
    stringBuffer.append(genFeature.getFormattedName());
    stringBuffer.append(TEXT_628);
    stringBuffer.append(genFeature.getFeatureKind());
    stringBuffer.append(TEXT_629);
    //Class/basicSetGenFeature.todo.override.javajetinc
    }
    stringBuffer.append(TEXT_630);
    //Class/basicSetGenFeature.override.javajetinc
    }
    if (genFeature.isSet() && (isImplementation || !genFeature.isSuppressedSetVisibility())) {
    if (isInterface) { 
    stringBuffer.append(TEXT_631);
    stringBuffer.append(genClass.getQualifiedInterfaceName());
    stringBuffer.append(TEXT_632);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_633);
    stringBuffer.append(genFeature.getFormattedName());
    stringBuffer.append(TEXT_634);
    stringBuffer.append(genFeature.getFeatureKind());
    stringBuffer.append(TEXT_635);
    stringBuffer.append(TEXT_636);
    stringBuffer.append(genFeature.getFormattedName());
    stringBuffer.append(TEXT_637);
    stringBuffer.append(genFeature.getFeatureKind());
    stringBuffer.append(TEXT_638);
    if (genFeature.isEnumType()) {
    stringBuffer.append(TEXT_639);
    stringBuffer.append(genFeature.getTypeGenEnum().getQualifiedName());
    }
    if (genFeature.isUnsettable()) {
    if (!genFeature.isSuppressedIsSetVisibility()) {
    stringBuffer.append(TEXT_640);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_641);
    }
    if (!genFeature.isSuppressedUnsetVisibility()) {
    stringBuffer.append(TEXT_642);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_643);
    }
    }
    stringBuffer.append(TEXT_644);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_645);
    //Class/setGenFeature.javadoc.override.javajetinc
    } else {
    stringBuffer.append(TEXT_646);
    if (isJDK50) { //Class/setGenFeature.annotations.insert.javajetinc
    }
    }
    if (!isImplementation) { 
    stringBuffer.append(TEXT_647);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_648);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_649);
    } else { GenOperation setAccessorOperation = genClass.getSetAccessorOperation(genFeature);
    stringBuffer.append(TEXT_650);
    stringBuffer.append(genFeature.getAccessorName());
    if (genClass.hasCollidingSetAccessorOperation(genFeature)) {
    stringBuffer.append(TEXT_651);
    }
    stringBuffer.append(TEXT_652);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_653);
    stringBuffer.append(setAccessorOperation == null ? "new" + genFeature.getCapName() : setAccessorOperation.getGenParameters().get(0).getName());
    stringBuffer.append(TEXT_654);
    if (genModel.isDynamicDelegation()) {
    stringBuffer.append(TEXT_655);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(TEXT_656);
    stringBuffer.append(genFeature.getQualifiedFeatureAccessor());
    stringBuffer.append(TEXT_657);
    if (!isJDK50 && genFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_658);
    stringBuffer.append(genFeature.getObjectType(genClass));
    stringBuffer.append(TEXT_659);
    }
    stringBuffer.append(TEXT_660);
    stringBuffer.append(genFeature.getCapName());
    if (!isJDK50 && genFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_661);
    }
    stringBuffer.append(TEXT_662);
    } else if (genModel.isReflectiveDelegation()) {
    stringBuffer.append(TEXT_663);
    stringBuffer.append(genFeature.getQualifiedFeatureAccessor());
    stringBuffer.append(TEXT_664);
    if (!isJDK50 && genFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_665);
    stringBuffer.append(genFeature.getObjectType(genClass));
    stringBuffer.append(TEXT_666);
    }
    stringBuffer.append(TEXT_667);
    stringBuffer.append(genFeature.getCapName());
    if (!isJDK50 && genFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_668);
    }
    stringBuffer.append(TEXT_669);
    } else if (genFeature.hasSettingDelegate()) {
    stringBuffer.append(TEXT_670);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_671);
    if (!isJDK50 && genFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_672);
    stringBuffer.append(genFeature.getObjectType(genClass));
    stringBuffer.append(TEXT_673);
    }
    stringBuffer.append(TEXT_674);
    stringBuffer.append(genFeature.getCapName());
    if (!isJDK50 && genFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_675);
    }
    stringBuffer.append(TEXT_676);
    } else if (!genFeature.isVolatile()) {
    if (genFeature.isContainer()) { GenFeature reverseFeature = genFeature.getReverse(); GenClass targetClass = reverseFeature.getGenClass(); String reverseOffsetCorrection = targetClass.hasOffsetCorrection() ? " + " + genClass.getOffsetCorrectionField(genFeature) : "";
    stringBuffer.append(TEXT_677);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_678);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(positiveOffsetCorrection);
    stringBuffer.append(TEXT_679);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_680);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.EcoreUtil"));
    stringBuffer.append(TEXT_681);
    stringBuffer.append(genFeature.getEObjectCast());
    stringBuffer.append(TEXT_682);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_683);
    stringBuffer.append(genModel.getImportedName("java.lang.IllegalArgumentException"));
    stringBuffer.append(TEXT_684);
    stringBuffer.append(genModel.getNonNLS());
    stringBuffer.append(TEXT_685);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.notify.NotificationChain"));
    stringBuffer.append(TEXT_686);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_687);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.InternalEObject"));
    stringBuffer.append(TEXT_688);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_689);
    stringBuffer.append(targetClass.getQualifiedFeatureID(reverseFeature));
    stringBuffer.append(reverseOffsetCorrection);
    stringBuffer.append(TEXT_690);
    stringBuffer.append(targetClass.getRawImportedInterfaceName());
    stringBuffer.append(TEXT_691);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_692);
    stringBuffer.append(genFeature.getInternalTypeCast());
    stringBuffer.append(TEXT_693);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_694);
    if (!genModel.isSuppressNotification()) {
    stringBuffer.append(TEXT_695);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.impl.ENotificationImpl"));
    stringBuffer.append(TEXT_696);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.notify.Notification"));
    stringBuffer.append(TEXT_697);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(positiveOffsetCorrection);
    stringBuffer.append(TEXT_698);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_699);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_700);
    }
    } else if (genFeature.isBidirectional() || genFeature.isEffectiveContains()) {
    if (genModel.isVirtualDelegation()) {
    stringBuffer.append(TEXT_701);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_702);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_703);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_704);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(positiveOffsetCorrection);
    stringBuffer.append(TEXT_705);
    }
    stringBuffer.append(TEXT_706);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_707);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_708);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.notify.NotificationChain"));
    stringBuffer.append(TEXT_709);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_710);
    if (!genFeature.isBidirectional()) {
    stringBuffer.append(TEXT_711);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.InternalEObject"));
    stringBuffer.append(TEXT_712);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_713);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(negativeOffsetCorrection);
    stringBuffer.append(TEXT_714);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_715);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.InternalEObject"));
    stringBuffer.append(TEXT_716);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_717);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(negativeOffsetCorrection);
    stringBuffer.append(TEXT_718);
    } else { GenFeature reverseFeature = genFeature.getReverse(); GenClass targetClass = reverseFeature.getGenClass(); String reverseOffsetCorrection = targetClass.hasOffsetCorrection() ? " + " + genClass.getOffsetCorrectionField(genFeature) : "";
    stringBuffer.append(TEXT_719);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.InternalEObject"));
    stringBuffer.append(TEXT_720);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_721);
    stringBuffer.append(targetClass.getQualifiedFeatureID(reverseFeature));
    stringBuffer.append(reverseOffsetCorrection);
    stringBuffer.append(TEXT_722);
    stringBuffer.append(targetClass.getRawImportedInterfaceName());
    stringBuffer.append(TEXT_723);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_724);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.InternalEObject"));
    stringBuffer.append(TEXT_725);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_726);
    stringBuffer.append(targetClass.getQualifiedFeatureID(reverseFeature));
    stringBuffer.append(reverseOffsetCorrection);
    stringBuffer.append(TEXT_727);
    stringBuffer.append(targetClass.getRawImportedInterfaceName());
    stringBuffer.append(TEXT_728);
    }
    stringBuffer.append(TEXT_729);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_730);
    stringBuffer.append(genFeature.getInternalTypeCast());
    stringBuffer.append(TEXT_731);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_732);
    if (genFeature.isUnsettable()) {
    stringBuffer.append(TEXT_733);
    if (genModel.isVirtualDelegation()) {
    stringBuffer.append(TEXT_734);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_735);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(positiveOffsetCorrection);
    stringBuffer.append(TEXT_736);
    } else if (genClass.isESetFlag(genFeature)) {
    if (!genModel.isSuppressNotification()) {
    stringBuffer.append(TEXT_737);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_738);
    stringBuffer.append(genClass.getESetFlagsField(genFeature));
    stringBuffer.append(TEXT_739);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_740);
    }
    stringBuffer.append(TEXT_741);
    stringBuffer.append(genClass.getESetFlagsField(genFeature));
    stringBuffer.append(TEXT_742);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_743);
    } else {
    if (!genModel.isSuppressNotification()) {
    stringBuffer.append(TEXT_744);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_745);
    stringBuffer.append(genFeature.getUncapName());
    stringBuffer.append(TEXT_746);
    }
    stringBuffer.append(TEXT_747);
    stringBuffer.append(genFeature.getUncapName());
    stringBuffer.append(TEXT_748);
    }
    if (!genModel.isSuppressNotification()) {
    stringBuffer.append(TEXT_749);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.impl.ENotificationImpl"));
    stringBuffer.append(TEXT_750);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.notify.Notification"));
    stringBuffer.append(TEXT_751);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(positiveOffsetCorrection);
    stringBuffer.append(TEXT_752);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_753);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_754);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_755);
    }
    stringBuffer.append(TEXT_756);
    } else {
    if (!genModel.isSuppressNotification()) {
    stringBuffer.append(TEXT_757);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.impl.ENotificationImpl"));
    stringBuffer.append(TEXT_758);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.notify.Notification"));
    stringBuffer.append(TEXT_759);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(positiveOffsetCorrection);
    stringBuffer.append(TEXT_760);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_761);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_762);
    }
    }
    } else {
    if (genClass.isFlag(genFeature)) {
    if (!genModel.isSuppressNotification()) {
    if (genFeature.isBooleanType()) {
    stringBuffer.append(TEXT_763);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_764);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_765);
    stringBuffer.append(genClass.getFlagsField(genFeature));
    stringBuffer.append(TEXT_766);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_767);
    } else {
    stringBuffer.append(TEXT_768);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_769);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_770);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_771);
    stringBuffer.append(genClass.getFlagsField(genFeature));
    stringBuffer.append(TEXT_772);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_773);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_774);
    }
    }
    if (genFeature.isBooleanType()) {
    stringBuffer.append(TEXT_775);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_776);
    stringBuffer.append(genClass.getFlagsField(genFeature));
    stringBuffer.append(TEXT_777);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_778);
    stringBuffer.append(genClass.getFlagsField(genFeature));
    stringBuffer.append(TEXT_779);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_780);
    } else {
    stringBuffer.append(TEXT_781);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_782);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_783);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_784);
    stringBuffer.append(genClass.getFlagsField(genFeature));
    stringBuffer.append(TEXT_785);
    stringBuffer.append(genClass.getFlagsField(genFeature));
    stringBuffer.append(TEXT_786);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_787);
    if (isJDK50) {
    stringBuffer.append(TEXT_788);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_789);
    } else {
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_790);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_791);
    }
    stringBuffer.append(TEXT_792);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_793);
    }
    } else {
    if (!genModel.isVirtualDelegation() || genFeature.isPrimitiveType()) {
    if (!genModel.isSuppressNotification()) {
    stringBuffer.append(TEXT_794);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_795);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_796);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_797);
    }
    }
    if (genFeature.isEnumType()) {
    if (genModel.isVirtualDelegation()) {
    stringBuffer.append(TEXT_798);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_799);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_800);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_801);
    stringBuffer.append(genFeature.getEDefault());
    stringBuffer.append(TEXT_802);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_803);
    } else {
    stringBuffer.append(TEXT_804);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_805);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_806);
    stringBuffer.append(genFeature.getEDefault());
    stringBuffer.append(TEXT_807);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_808);
    }
    } else {
    if (genModel.isVirtualDelegation() && !genFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_809);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_810);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_811);
    stringBuffer.append(genFeature.getInternalTypeCast());
    stringBuffer.append(TEXT_812);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_813);
    } else {
    stringBuffer.append(TEXT_814);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_815);
    stringBuffer.append(genFeature.getInternalTypeCast());
    stringBuffer.append(TEXT_816);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_817);
    }
    }
    if (genModel.isVirtualDelegation() && !genFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_818);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_819);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(positiveOffsetCorrection);
    stringBuffer.append(TEXT_820);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_821);
    }
    }
    if (genFeature.isUnsettable()) {
    if (genModel.isVirtualDelegation() && !genFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_822);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_823);
    } else if (genClass.isESetFlag(genFeature)) {
    if (!genModel.isSuppressNotification()) {
    stringBuffer.append(TEXT_824);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_825);
    stringBuffer.append(genClass.getESetFlagsField(genFeature));
    stringBuffer.append(TEXT_826);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_827);
    }
    stringBuffer.append(TEXT_828);
    stringBuffer.append(genClass.getESetFlagsField(genFeature));
    stringBuffer.append(TEXT_829);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_830);
    } else {
    if (!genModel.isSuppressNotification()) {
    stringBuffer.append(TEXT_831);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_832);
    stringBuffer.append(genFeature.getUncapName());
    stringBuffer.append(TEXT_833);
    }
    stringBuffer.append(TEXT_834);
    stringBuffer.append(genFeature.getUncapName());
    stringBuffer.append(TEXT_835);
    }
    if (!genModel.isSuppressNotification()) {
    stringBuffer.append(TEXT_836);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.impl.ENotificationImpl"));
    stringBuffer.append(TEXT_837);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.notify.Notification"));
    stringBuffer.append(TEXT_838);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(positiveOffsetCorrection);
    stringBuffer.append(TEXT_839);
    if (genModel.isVirtualDelegation() && !genFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_840);
    stringBuffer.append(genFeature.getEDefault());
    stringBuffer.append(TEXT_841);
    stringBuffer.append(genFeature.getCapName());
    } else {
    stringBuffer.append(TEXT_842);
    stringBuffer.append(genFeature.getCapName());
    }
    stringBuffer.append(TEXT_843);
    if (genClass.isFlag(genFeature)) {
    stringBuffer.append(TEXT_844);
    stringBuffer.append(genFeature.getCapName());
    } else {
    stringBuffer.append(genFeature.getSafeName());
    }
    stringBuffer.append(TEXT_845);
    if (genModel.isVirtualDelegation() && !genFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_846);
    } else {
    stringBuffer.append(TEXT_847);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_848);
    }
    stringBuffer.append(TEXT_849);
    }
    } else {
    if (!genModel.isSuppressNotification()) {
    stringBuffer.append(TEXT_850);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.impl.ENotificationImpl"));
    stringBuffer.append(TEXT_851);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.notify.Notification"));
    stringBuffer.append(TEXT_852);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(positiveOffsetCorrection);
    stringBuffer.append(TEXT_853);
    if (genModel.isVirtualDelegation() && !genFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_854);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_855);
    stringBuffer.append(genFeature.getEDefault());
    stringBuffer.append(TEXT_856);
    stringBuffer.append(genFeature.getCapName());
    } else {
    stringBuffer.append(TEXT_857);
    stringBuffer.append(genFeature.getCapName());
    }
    stringBuffer.append(TEXT_858);
    if (genClass.isFlag(genFeature)) {
    stringBuffer.append(TEXT_859);
    stringBuffer.append(genFeature.getCapName());
    } else {
    stringBuffer.append(genFeature.getSafeName());
    }
    stringBuffer.append(TEXT_860);
    }
    }
    }
    } else if (genFeature.hasDelegateFeature()) { GenFeature delegateFeature = genFeature.getDelegateFeature();
    if (delegateFeature.isWrappedFeatureMapType()) {
    stringBuffer.append(TEXT_861);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.FeatureMap"));
    stringBuffer.append(TEXT_862);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.FeatureMap"));
    stringBuffer.append(TEXT_863);
    stringBuffer.append(delegateFeature.getAccessorName());
    stringBuffer.append(TEXT_864);
    stringBuffer.append(genFeature.getQualifiedFeatureAccessor());
    stringBuffer.append(TEXT_865);
    if (!isJDK50 && genFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_866);
    stringBuffer.append(genFeature.getObjectType(genClass));
    stringBuffer.append(TEXT_867);
    }
    stringBuffer.append(TEXT_868);
    stringBuffer.append(genFeature.getCapName());
    if (!isJDK50 && genFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_869);
    }
    stringBuffer.append(TEXT_870);
    } else {
    stringBuffer.append(TEXT_871);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.FeatureMap"));
    stringBuffer.append(TEXT_872);
    stringBuffer.append(delegateFeature.getAccessorName());
    stringBuffer.append(TEXT_873);
    stringBuffer.append(genFeature.getQualifiedFeatureAccessor());
    stringBuffer.append(TEXT_874);
    if (!isJDK50 && genFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_875);
    stringBuffer.append(genFeature.getObjectType(genClass));
    stringBuffer.append(TEXT_876);
    }
    stringBuffer.append(TEXT_877);
    stringBuffer.append(genFeature.getCapName());
    if (!isJDK50 && genFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_878);
    }
    stringBuffer.append(TEXT_879);
    }
    } else if (setAccessorOperation != null) {
    stringBuffer.append(TEXT_880);
    stringBuffer.append(setAccessorOperation.getBody(genModel.getIndentation(stringBuffer)));
    } else {
    stringBuffer.append(TEXT_881);
    stringBuffer.append(genFeature.getFormattedName());
    stringBuffer.append(TEXT_882);
    stringBuffer.append(genFeature.getFeatureKind());
    stringBuffer.append(TEXT_883);
    //Class/setGenFeature.todo.override.javajetinc
    }
    stringBuffer.append(TEXT_884);
    }
    //Class/setGenFeature.override.javajetinc
    }
    if (isImplementation && !genModel.isReflectiveDelegation() && genFeature.isBasicUnset()) {
    stringBuffer.append(TEXT_885);
    if (isJDK50) { //Class/basicUnsetGenFeature.annotations.insert.javajetinc
    }
    stringBuffer.append(TEXT_886);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.notify.NotificationChain"));
    stringBuffer.append(TEXT_887);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_888);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.notify.NotificationChain"));
    stringBuffer.append(TEXT_889);
    if (genModel.isDynamicDelegation()) {
    stringBuffer.append(TEXT_890);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.InternalEObject"));
    stringBuffer.append(TEXT_891);
    if (genFeature.isResolveProxies()) {
    stringBuffer.append(TEXT_892);
    stringBuffer.append(genFeature.getAccessorName());
    } else {
    stringBuffer.append(genFeature.getGetAccessor());
    }
    stringBuffer.append(TEXT_893);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(positiveOffsetCorrection);
    stringBuffer.append(TEXT_894);
    } else if (!genFeature.isVolatile()) {
    if (genModel.isVirtualDelegation()) {
    if (!genModel.isSuppressNotification()) {
    stringBuffer.append(TEXT_895);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_896);
    }
    stringBuffer.append(TEXT_897);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(positiveOffsetCorrection);
    stringBuffer.append(TEXT_898);
    } else {
    if (!genModel.isSuppressNotification()) {
    stringBuffer.append(TEXT_899);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_900);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_901);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_902);
    }
    stringBuffer.append(TEXT_903);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_904);
    }
    if (genModel.isVirtualDelegation()) {
    if (!genModel.isSuppressNotification()) {
    stringBuffer.append(TEXT_905);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_906);
    }
    } else if (genClass.isESetFlag(genFeature)) {
    if (!genModel.isSuppressNotification()) {
    stringBuffer.append(TEXT_907);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_908);
    stringBuffer.append(genClass.getESetFlagsField(genFeature));
    stringBuffer.append(TEXT_909);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_910);
    }
    stringBuffer.append(TEXT_911);
    stringBuffer.append(genClass.getESetFlagsField(genFeature));
    stringBuffer.append(TEXT_912);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_913);
    } else {
    if (!genModel.isSuppressNotification()) {
    stringBuffer.append(TEXT_914);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_915);
    stringBuffer.append(genFeature.getUncapName());
    stringBuffer.append(TEXT_916);
    }
    stringBuffer.append(TEXT_917);
    stringBuffer.append(genFeature.getUncapName());
    stringBuffer.append(TEXT_918);
    }
    if (!genModel.isSuppressNotification()) {
    stringBuffer.append(TEXT_919);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.impl.ENotificationImpl"));
    stringBuffer.append(TEXT_920);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.impl.ENotificationImpl"));
    stringBuffer.append(TEXT_921);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.notify.Notification"));
    stringBuffer.append(TEXT_922);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(positiveOffsetCorrection);
    stringBuffer.append(TEXT_923);
    if (genModel.isVirtualDelegation()) {
    stringBuffer.append(TEXT_924);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_925);
    } else {
    stringBuffer.append(TEXT_926);
    stringBuffer.append(genFeature.getCapName());
    }
    stringBuffer.append(TEXT_927);
    if (genModel.isVirtualDelegation()) {
    stringBuffer.append(TEXT_928);
    } else {
    stringBuffer.append(TEXT_929);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_930);
    }
    stringBuffer.append(TEXT_931);
    }
    } else {
    stringBuffer.append(TEXT_932);
    stringBuffer.append(genFeature.getFormattedName());
    stringBuffer.append(TEXT_933);
    stringBuffer.append(genFeature.getFeatureKind());
    stringBuffer.append(TEXT_934);
    //Class/basicUnsetGenFeature.todo.override.javajetinc
    }
    stringBuffer.append(TEXT_935);
    //Class.basicUnsetGenFeature.override.javajetinc
    }
    if (genFeature.isUnset() && (isImplementation || !genFeature.isSuppressedUnsetVisibility())) {
    if (isInterface) {
    stringBuffer.append(TEXT_936);
    stringBuffer.append(genClass.getQualifiedInterfaceName());
    stringBuffer.append(TEXT_937);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_938);
    stringBuffer.append(genFeature.getFormattedName());
    stringBuffer.append(TEXT_939);
    stringBuffer.append(genFeature.getFeatureKind());
    stringBuffer.append(TEXT_940);
    stringBuffer.append(TEXT_941);
    if (!genFeature.isSuppressedIsSetVisibility()) {
    stringBuffer.append(TEXT_942);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_943);
    }
    stringBuffer.append(TEXT_944);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_945);
    if (!genFeature.isListType() && !genFeature.isSuppressedSetVisibility()) {
    stringBuffer.append(TEXT_946);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_947);
    stringBuffer.append(genFeature.getRawImportedBoundType());
    stringBuffer.append(TEXT_948);
    }
    stringBuffer.append(TEXT_949);
    //Class/unsetGenFeature.javadoc.override.javajetinc
    } else {
    stringBuffer.append(TEXT_950);
    if (isJDK50) { //Class/unsetGenFeature.annotations.insert.javajetinc
    }
    }
    if (!isImplementation) {
    stringBuffer.append(TEXT_951);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_952);
    } else {
    stringBuffer.append(TEXT_953);
    stringBuffer.append(genFeature.getAccessorName());
    if (genClass.hasCollidingUnsetAccessorOperation(genFeature)) {
    stringBuffer.append(TEXT_954);
    }
    stringBuffer.append(TEXT_955);
    if (genModel.isDynamicDelegation()) {
    stringBuffer.append(TEXT_956);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(TEXT_957);
    stringBuffer.append(genFeature.getQualifiedFeatureAccessor());
    stringBuffer.append(TEXT_958);
    } else if (genModel.isReflectiveDelegation()) {
    stringBuffer.append(TEXT_959);
    stringBuffer.append(genFeature.getQualifiedFeatureAccessor());
    stringBuffer.append(TEXT_960);
    } else if (genFeature.hasSettingDelegate()) {
    stringBuffer.append(TEXT_961);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_962);
    } else if (!genFeature.isVolatile()) {
    if (genFeature.isListType()) {
    if (genModel.isVirtualDelegation()) {
    stringBuffer.append(TEXT_963);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_964);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_965);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_966);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(positiveOffsetCorrection);
    stringBuffer.append(TEXT_967);
    }
    stringBuffer.append(TEXT_968);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_969);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.InternalEList"));
    stringBuffer.append(TEXT_970);
    stringBuffer.append(singleWildcard);
    stringBuffer.append(TEXT_971);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_972);
    } else if (genFeature.isBidirectional() || genFeature.isEffectiveContains()) {
    if (genModel.isVirtualDelegation()) {
    stringBuffer.append(TEXT_973);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_974);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_975);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_976);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(positiveOffsetCorrection);
    stringBuffer.append(TEXT_977);
    }
    stringBuffer.append(TEXT_978);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_979);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.notify.NotificationChain"));
    stringBuffer.append(TEXT_980);
    if (!genFeature.isBidirectional()) {
    stringBuffer.append(TEXT_981);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.InternalEObject"));
    stringBuffer.append(TEXT_982);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_983);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(negativeOffsetCorrection);
    stringBuffer.append(TEXT_984);
    } else { GenFeature reverseFeature = genFeature.getReverse(); GenClass targetClass = reverseFeature.getGenClass(); String reverseOffsetCorrection = targetClass.hasOffsetCorrection() ? " + " + genClass.getOffsetCorrectionField(genFeature) : "";
    stringBuffer.append(TEXT_985);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.InternalEObject"));
    stringBuffer.append(TEXT_986);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_987);
    stringBuffer.append(targetClass.getQualifiedFeatureID(reverseFeature));
    stringBuffer.append(reverseOffsetCorrection);
    stringBuffer.append(TEXT_988);
    stringBuffer.append(targetClass.getRawImportedInterfaceName());
    stringBuffer.append(TEXT_989);
    }
    stringBuffer.append(TEXT_990);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_991);
    if (genModel.isVirtualDelegation()) {
    stringBuffer.append(TEXT_992);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_993);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(positiveOffsetCorrection);
    stringBuffer.append(TEXT_994);
    } else if (genClass.isESetFlag(genFeature)) {
    if (!genModel.isSuppressNotification()) {
    stringBuffer.append(TEXT_995);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_996);
    stringBuffer.append(genClass.getESetFlagsField(genFeature));
    stringBuffer.append(TEXT_997);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_998);
    }
    stringBuffer.append(TEXT_999);
    stringBuffer.append(genClass.getESetFlagsField(genFeature));
    stringBuffer.append(TEXT_1000);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_1001);
    } else {
    if (!genModel.isSuppressNotification()) {
    stringBuffer.append(TEXT_1002);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_1003);
    stringBuffer.append(genFeature.getUncapName());
    stringBuffer.append(TEXT_1004);
    }
    stringBuffer.append(TEXT_1005);
    stringBuffer.append(genFeature.getUncapName());
    stringBuffer.append(TEXT_1006);
    }
    if (!genModel.isSuppressNotification()) {
    stringBuffer.append(TEXT_1007);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.impl.ENotificationImpl"));
    stringBuffer.append(TEXT_1008);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.notify.Notification"));
    stringBuffer.append(TEXT_1009);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(positiveOffsetCorrection);
    stringBuffer.append(TEXT_1010);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_1011);
    }
    stringBuffer.append(TEXT_1012);
    } else {
    if (genClass.isFlag(genFeature)) {
    if (!genModel.isSuppressNotification()) {
    if (genFeature.isBooleanType()) {
    stringBuffer.append(TEXT_1013);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_1014);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_1015);
    stringBuffer.append(genClass.getFlagsField(genFeature));
    stringBuffer.append(TEXT_1016);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_1017);
    } else {
    stringBuffer.append(TEXT_1018);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_1019);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_1020);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_1021);
    stringBuffer.append(genClass.getFlagsField(genFeature));
    stringBuffer.append(TEXT_1022);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_1023);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_1024);
    }
    }
    } else if (genModel.isVirtualDelegation() && !genFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_1025);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_1026);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(positiveOffsetCorrection);
    stringBuffer.append(TEXT_1027);
    } else {
    if (!genModel.isSuppressNotification()) {
    stringBuffer.append(TEXT_1028);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_1029);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_1030);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_1031);
    }
    }
    if (!genModel.isSuppressNotification()) {
    if (genModel.isVirtualDelegation() && !genFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_1032);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_1033);
    } else if (genClass.isESetFlag(genFeature)) {
    stringBuffer.append(TEXT_1034);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_1035);
    stringBuffer.append(genClass.getESetFlagsField(genFeature));
    stringBuffer.append(TEXT_1036);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_1037);
    } else {
    stringBuffer.append(TEXT_1038);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_1039);
    stringBuffer.append(genFeature.getUncapName());
    stringBuffer.append(TEXT_1040);
    }
    }
    if (genFeature.isReferenceType()) {
    stringBuffer.append(TEXT_1041);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_1042);
    if (!genModel.isVirtualDelegation()) {
    if (genClass.isESetFlag(genFeature)) {
    stringBuffer.append(TEXT_1043);
    stringBuffer.append(genClass.getESetFlagsField(genFeature));
    stringBuffer.append(TEXT_1044);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_1045);
    } else {
    stringBuffer.append(TEXT_1046);
    stringBuffer.append(genFeature.getUncapName());
    stringBuffer.append(TEXT_1047);
    }
    }
    if (!genModel.isSuppressNotification()) {
    stringBuffer.append(TEXT_1048);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.impl.ENotificationImpl"));
    stringBuffer.append(TEXT_1049);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.notify.Notification"));
    stringBuffer.append(TEXT_1050);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(positiveOffsetCorrection);
    stringBuffer.append(TEXT_1051);
    if (genModel.isVirtualDelegation()) {
    stringBuffer.append(TEXT_1052);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_1053);
    } else {
    stringBuffer.append(TEXT_1054);
    stringBuffer.append(genFeature.getCapName());
    }
    stringBuffer.append(TEXT_1055);
    if (genModel.isVirtualDelegation()) {
    stringBuffer.append(TEXT_1056);
    } else {
    stringBuffer.append(TEXT_1057);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_1058);
    }
    stringBuffer.append(TEXT_1059);
    }
    } else {
    if (genClass.isFlag(genFeature)) {
    if (genFeature.isBooleanType()) {
    stringBuffer.append(TEXT_1060);
    stringBuffer.append(genFeature.getEDefault());
    stringBuffer.append(TEXT_1061);
    stringBuffer.append(genClass.getFlagsField(genFeature));
    stringBuffer.append(TEXT_1062);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_1063);
    stringBuffer.append(genClass.getFlagsField(genFeature));
    stringBuffer.append(TEXT_1064);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_1065);
    } else {
    stringBuffer.append(TEXT_1066);
    stringBuffer.append(genClass.getFlagsField(genFeature));
    stringBuffer.append(TEXT_1067);
    stringBuffer.append(genClass.getFlagsField(genFeature));
    stringBuffer.append(TEXT_1068);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_1069);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_1070);
    }
    } else if (!genModel.isVirtualDelegation() || genFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_1071);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_1072);
    stringBuffer.append(genFeature.getEDefault());
    stringBuffer.append(TEXT_1073);
    }
    if (!genModel.isVirtualDelegation() || genFeature.isPrimitiveType()) {
    if (genClass.isESetFlag(genFeature)) {
    stringBuffer.append(TEXT_1074);
    stringBuffer.append(genClass.getESetFlagsField(genFeature));
    stringBuffer.append(TEXT_1075);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_1076);
    } else {
    stringBuffer.append(TEXT_1077);
    stringBuffer.append(genFeature.getUncapName());
    stringBuffer.append(TEXT_1078);
    }
    }
    if (!genModel.isSuppressNotification()) {
    stringBuffer.append(TEXT_1079);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.impl.ENotificationImpl"));
    stringBuffer.append(TEXT_1080);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.notify.Notification"));
    stringBuffer.append(TEXT_1081);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(positiveOffsetCorrection);
    stringBuffer.append(TEXT_1082);
    if (genModel.isVirtualDelegation() && !genFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_1083);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_1084);
    stringBuffer.append(genFeature.getEDefault());
    } else {
    stringBuffer.append(TEXT_1085);
    stringBuffer.append(genFeature.getCapName());
    }
    stringBuffer.append(TEXT_1086);
    stringBuffer.append(genFeature.getEDefault());
    stringBuffer.append(TEXT_1087);
    if (genModel.isVirtualDelegation() && !genFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_1088);
    } else {
    stringBuffer.append(TEXT_1089);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_1090);
    }
    stringBuffer.append(TEXT_1091);
    }
    }
    }
    } else if (genFeature.hasDelegateFeature()) { GenFeature delegateFeature = genFeature.getDelegateFeature();
    if (delegateFeature.isWrappedFeatureMapType()) {
    stringBuffer.append(TEXT_1092);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.FeatureMap"));
    stringBuffer.append(TEXT_1093);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.FeatureMap"));
    stringBuffer.append(TEXT_1094);
    stringBuffer.append(delegateFeature.getAccessorName());
    stringBuffer.append(TEXT_1095);
    stringBuffer.append(genFeature.getQualifiedFeatureAccessor());
    stringBuffer.append(TEXT_1096);
    } else {
    stringBuffer.append(TEXT_1097);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.FeatureMap"));
    stringBuffer.append(TEXT_1098);
    stringBuffer.append(delegateFeature.getAccessorName());
    stringBuffer.append(TEXT_1099);
    stringBuffer.append(genFeature.getQualifiedFeatureAccessor());
    stringBuffer.append(TEXT_1100);
    }
    } else if (genClass.getUnsetAccessorOperation(genFeature) != null) {
    stringBuffer.append(TEXT_1101);
    stringBuffer.append(genClass.getUnsetAccessorOperation(genFeature).getBody(genModel.getIndentation(stringBuffer)));
    } else {
    stringBuffer.append(TEXT_1102);
    stringBuffer.append(genFeature.getFormattedName());
    stringBuffer.append(TEXT_1103);
    stringBuffer.append(genFeature.getFeatureKind());
    stringBuffer.append(TEXT_1104);
    //Class/unsetGenFeature.todo.override.javajetinc
    }
    stringBuffer.append(TEXT_1105);
    }
    //Class/unsetGenFeature.override.javajetinc
    }
    if (genFeature.isIsSet() && (isImplementation || !genFeature.isSuppressedIsSetVisibility())) {
    if (isInterface) {
    stringBuffer.append(TEXT_1106);
    stringBuffer.append(genClass.getQualifiedInterfaceName());
    stringBuffer.append(TEXT_1107);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_1108);
    stringBuffer.append(genFeature.getFormattedName());
    stringBuffer.append(TEXT_1109);
    stringBuffer.append(genFeature.getFeatureKind());
    stringBuffer.append(TEXT_1110);
    stringBuffer.append(TEXT_1111);
    stringBuffer.append(genFeature.getFormattedName());
    stringBuffer.append(TEXT_1112);
    stringBuffer.append(genFeature.getFeatureKind());
    stringBuffer.append(TEXT_1113);
    if (genFeature.isChangeable() && !genFeature.isSuppressedUnsetVisibility()) {
    stringBuffer.append(TEXT_1114);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_1115);
    }
    stringBuffer.append(TEXT_1116);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_1117);
    if (!genFeature.isListType() && genFeature.isChangeable() && !genFeature.isSuppressedSetVisibility()) {
    stringBuffer.append(TEXT_1118);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_1119);
    stringBuffer.append(genFeature.getRawImportedBoundType());
    stringBuffer.append(TEXT_1120);
    }
    stringBuffer.append(TEXT_1121);
    //Class/isSetGenFeature.javadoc.override.javajetinc
    } else {
    stringBuffer.append(TEXT_1122);
    if (isJDK50) { //Class/isSetGenFeature.annotations.insert.javajetinc
    }
    }
    if (!isImplementation) {
    stringBuffer.append(TEXT_1123);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_1124);
    } else {
    stringBuffer.append(TEXT_1125);
    stringBuffer.append(genFeature.getAccessorName());
    if (genClass.hasCollidingIsSetAccessorOperation(genFeature)) {
    stringBuffer.append(TEXT_1126);
    }
    stringBuffer.append(TEXT_1127);
    if (genModel.isDynamicDelegation()) {
    stringBuffer.append(TEXT_1128);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(TEXT_1129);
    stringBuffer.append(genFeature.getQualifiedFeatureAccessor());
    stringBuffer.append(TEXT_1130);
    } else if (genModel.isReflectiveDelegation()) {
    stringBuffer.append(TEXT_1131);
    stringBuffer.append(genFeature.getQualifiedFeatureAccessor());
    stringBuffer.append(TEXT_1132);
    } else if (genFeature.hasSettingDelegate()) {
    stringBuffer.append(TEXT_1133);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_1134);
    } else if (!genFeature.isVolatile()) {
    if (genFeature.isListType()) {
    if (genModel.isVirtualDelegation()) {
    stringBuffer.append(TEXT_1135);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_1136);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_1137);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_1138);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(positiveOffsetCorrection);
    stringBuffer.append(TEXT_1139);
    }
    stringBuffer.append(TEXT_1140);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_1141);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.InternalEList"));
    stringBuffer.append(TEXT_1142);
    stringBuffer.append(singleWildcard);
    stringBuffer.append(TEXT_1143);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_1144);
    } else {
    if (genModel.isVirtualDelegation() && !genFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_1145);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(positiveOffsetCorrection);
    stringBuffer.append(TEXT_1146);
    } else if (genClass.isESetFlag(genFeature)) {
    stringBuffer.append(TEXT_1147);
    stringBuffer.append(genClass.getESetFlagsField(genFeature));
    stringBuffer.append(TEXT_1148);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_1149);
    } else {
    stringBuffer.append(TEXT_1150);
    stringBuffer.append(genFeature.getUncapName());
    stringBuffer.append(TEXT_1151);
    }
    }
    } else if (genFeature.hasDelegateFeature()) { GenFeature delegateFeature = genFeature.getDelegateFeature();
    if (delegateFeature.isWrappedFeatureMapType()) {
    stringBuffer.append(TEXT_1152);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.FeatureMap"));
    stringBuffer.append(TEXT_1153);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.FeatureMap"));
    stringBuffer.append(TEXT_1154);
    stringBuffer.append(delegateFeature.getAccessorName());
    stringBuffer.append(TEXT_1155);
    stringBuffer.append(genFeature.getQualifiedFeatureAccessor());
    stringBuffer.append(TEXT_1156);
    } else {
    stringBuffer.append(TEXT_1157);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.FeatureMap"));
    stringBuffer.append(TEXT_1158);
    stringBuffer.append(delegateFeature.getAccessorName());
    stringBuffer.append(TEXT_1159);
    stringBuffer.append(genFeature.getQualifiedFeatureAccessor());
    stringBuffer.append(TEXT_1160);
    }
    } else if (genClass.getIsSetAccessorOperation(genFeature) != null) {
    stringBuffer.append(TEXT_1161);
    stringBuffer.append(genClass.getIsSetAccessorOperation(genFeature).getBody(genModel.getIndentation(stringBuffer)));
    } else {
    stringBuffer.append(TEXT_1162);
    stringBuffer.append(genFeature.getFormattedName());
    stringBuffer.append(TEXT_1163);
    stringBuffer.append(genFeature.getFeatureKind());
    stringBuffer.append(TEXT_1164);
    //Class/isSetGenFeature.todo.override.javajetinc
    }
    stringBuffer.append(TEXT_1165);
    }
    //Class/isSetGenFeature.override.javajetinc
    }
    //Class/genFeature.override.javajetinc
    }//for
    }}.run();
    for (GenOperation genOperation : (isImplementation ? genClass.getImplementedGenOperations() : genClass.getDeclaredGenOperations())) {
    if (isImplementation) {
    if (genOperation.isInvariant() && genOperation.hasInvariantExpression()) {
    stringBuffer.append(TEXT_1166);
    stringBuffer.append(genOperation.getName());
    stringBuffer.append(TEXT_1167);
    stringBuffer.append(genOperation.getParameterTypes(", "));
    stringBuffer.append(TEXT_1168);
    stringBuffer.append(genOperation.getFormattedName());
    stringBuffer.append(TEXT_1169);
    stringBuffer.append(genOperation.getName());
    stringBuffer.append(TEXT_1170);
    stringBuffer.append(genOperation.getParameterTypes(", "));
    stringBuffer.append(TEXT_1171);
    stringBuffer.append(genModel.getImportedName("java.lang.String"));
    stringBuffer.append(TEXT_1172);
    stringBuffer.append(CodeGenUtil.upperName(genClass.getUniqueName(genOperation), genModel.getLocale()));
    stringBuffer.append(TEXT_1173);
    stringBuffer.append(genOperation.getInvariantExpression("\t\t"));
    stringBuffer.append(TEXT_1174);
    stringBuffer.append(genModel.getNonNLS());
    stringBuffer.append(TEXT_1175);
    } else if (genOperation.hasInvocationDelegate()) {
    stringBuffer.append(TEXT_1176);
    stringBuffer.append(genOperation.getName());
    stringBuffer.append(TEXT_1177);
    stringBuffer.append(genOperation.getParameterTypes(", "));
    stringBuffer.append(TEXT_1178);
    stringBuffer.append(genOperation.getFormattedName());
    stringBuffer.append(TEXT_1179);
    stringBuffer.append(genOperation.getName());
    stringBuffer.append(TEXT_1180);
    stringBuffer.append(genOperation.getParameterTypes(", "));
    stringBuffer.append(TEXT_1181);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.EOperation"));
    stringBuffer.append(TEXT_1182);
    stringBuffer.append(CodeGenUtil.upperName(genClass.getUniqueName(genOperation), genModel.getLocale()));
    stringBuffer.append(TEXT_1183);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.EOperation"));
    stringBuffer.append(TEXT_1184);
    stringBuffer.append(genOperation.getQualifiedOperationAccessor());
    stringBuffer.append(TEXT_1185);
    }
    }
    if (isInterface) {
    stringBuffer.append(TEXT_1186);
    stringBuffer.append(TEXT_1187);
    if (genOperation.hasDocumentation() || genOperation.hasParameterDocumentation()) {
    stringBuffer.append(TEXT_1188);
    if (genOperation.hasDocumentation()) {
    stringBuffer.append(TEXT_1189);
    stringBuffer.append(genOperation.getDocumentation(genModel.getIndentation(stringBuffer)));
    }
    for (GenParameter genParameter : genOperation.getGenParameters()) {
    if (genParameter.hasDocumentation()) { String documentation = genParameter.getDocumentation("");
    if (documentation.contains("\n") || documentation.contains("\r")) {
    stringBuffer.append(TEXT_1190);
    stringBuffer.append(genParameter.getName());
    stringBuffer.append(TEXT_1191);
    stringBuffer.append(genParameter.getDocumentation(genModel.getIndentation(stringBuffer)));
    } else {
    stringBuffer.append(TEXT_1192);
    stringBuffer.append(genParameter.getName());
    stringBuffer.append(TEXT_1193);
    stringBuffer.append(genParameter.getDocumentation(genModel.getIndentation(stringBuffer)));
    }
    }
    }
    stringBuffer.append(TEXT_1194);
    }
    if (!genModel.isSuppressEMFModelTags()) { boolean first = true; for (StringTokenizer stringTokenizer = new StringTokenizer(genOperation.getModelInfo(), "\n\r"); stringTokenizer.hasMoreTokens(); ) { String modelInfo = stringTokenizer.nextToken(); if (first) { first = false;
    stringBuffer.append(TEXT_1195);
    stringBuffer.append(modelInfo);
    } else {
    stringBuffer.append(TEXT_1196);
    stringBuffer.append(modelInfo);
    }} if (first) {
    stringBuffer.append(TEXT_1197);
    }}
    stringBuffer.append(TEXT_1198);
    //Class/genOperation.javadoc.override.javajetinc
    } else {
    stringBuffer.append(TEXT_1199);
    if (isJDK50) { //Class/genOperation.annotations.insert.javajetinc
    }
    }
    if (!isImplementation) {
    stringBuffer.append(TEXT_1200);
    stringBuffer.append(genOperation.getTypeParameters(genClass));
    stringBuffer.append(genOperation.getImportedType(genClass));
    stringBuffer.append(TEXT_1201);
    stringBuffer.append(genOperation.getName());
    stringBuffer.append(TEXT_1202);
    stringBuffer.append(genOperation.getParameters(genClass));
    stringBuffer.append(TEXT_1203);
    stringBuffer.append(genOperation.getThrows(genClass));
    stringBuffer.append(TEXT_1204);
    } else {
    if (genModel.useGenerics() && !genOperation.hasBody() && !genOperation.isInvariant() && genOperation.hasInvocationDelegate() && genOperation.isUncheckedCast(genClass)) {
    stringBuffer.append(TEXT_1205);
    }
    stringBuffer.append(TEXT_1206);
    stringBuffer.append(genOperation.getTypeParameters(genClass));
    stringBuffer.append(genOperation.getImportedType(genClass));
    stringBuffer.append(TEXT_1207);
    stringBuffer.append(genOperation.getName());
    stringBuffer.append(TEXT_1208);
    stringBuffer.append(genOperation.getParameters(genClass));
    stringBuffer.append(TEXT_1209);
    stringBuffer.append(genOperation.getThrows(genClass));
    stringBuffer.append(TEXT_1210);
    if (genOperation.hasBody()) {
    stringBuffer.append(TEXT_1211);
    stringBuffer.append(genOperation.getBody(genModel.getIndentation(stringBuffer)));
    } else if (genOperation.isInvariant()) {GenClass opClass = genOperation.getGenClass(); String diagnostics = genOperation.getGenParameters().get(0).getName(); String context = genOperation.getGenParameters().get(1).getName();
    if (genOperation.hasInvariantExpression()) {
    stringBuffer.append(TEXT_1212);
    stringBuffer.append(opClass.getGenPackage().getImportedValidatorClassName());
    stringBuffer.append(TEXT_1213);
    stringBuffer.append(genClass.getQualifiedClassifierAccessor());
    stringBuffer.append(TEXT_1214);
    stringBuffer.append(diagnostics);
    stringBuffer.append(TEXT_1215);
    stringBuffer.append(context);
    stringBuffer.append(TEXT_1216);
    stringBuffer.append(genOperation.getValidationDelegate());
    stringBuffer.append(TEXT_1217);
    stringBuffer.append(genModel.getNonNLS());
    stringBuffer.append(TEXT_1218);
    stringBuffer.append(genOperation.getQualifiedOperationAccessor());
    stringBuffer.append(TEXT_1219);
    stringBuffer.append(CodeGenUtil.upperName(genClass.getUniqueName(genOperation), genModel.getLocale()));
    stringBuffer.append(TEXT_1220);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.util.Diagnostic"));
    stringBuffer.append(TEXT_1221);
    stringBuffer.append(opClass.getGenPackage().getImportedValidatorClassName());
    stringBuffer.append(TEXT_1222);
    stringBuffer.append(opClass.getGenPackage().getImportedValidatorClassName());
    stringBuffer.append(TEXT_1223);
    stringBuffer.append(opClass.getOperationID(genOperation));
    stringBuffer.append(TEXT_1224);
    } else {
    stringBuffer.append(TEXT_1225);
    stringBuffer.append(diagnostics);
    stringBuffer.append(TEXT_1226);
    stringBuffer.append(diagnostics);
    stringBuffer.append(TEXT_1227);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.util.BasicDiagnostic"));
    stringBuffer.append(TEXT_1228);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.util.Diagnostic"));
    stringBuffer.append(TEXT_1229);
    stringBuffer.append(opClass.getGenPackage().getImportedValidatorClassName());
    stringBuffer.append(TEXT_1230);
    stringBuffer.append(opClass.getGenPackage().getImportedValidatorClassName());
    stringBuffer.append(TEXT_1231);
    stringBuffer.append(opClass.getOperationID(genOperation));
    stringBuffer.append(TEXT_1232);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.plugin.EcorePlugin"));
    stringBuffer.append(TEXT_1233);
    stringBuffer.append(genOperation.getName());
    stringBuffer.append(TEXT_1234);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.EObjectValidator"));
    stringBuffer.append(TEXT_1235);
    stringBuffer.append(context);
    stringBuffer.append(TEXT_1236);
    stringBuffer.append(genModel.getNonNLS());
    stringBuffer.append(genModel.getNonNLS(2));
    stringBuffer.append(TEXT_1237);
    }
    } else if (genOperation.hasInvocationDelegate()) { int size = genOperation.getGenParameters().size();
    stringBuffer.append(TEXT_1238);
    if (genOperation.isVoid()) {
    stringBuffer.append(TEXT_1239);
    stringBuffer.append(CodeGenUtil.upperName(genClass.getUniqueName(genOperation), genModel.getLocale()));
    stringBuffer.append(TEXT_1240);
    if (size > 0) {
    stringBuffer.append(TEXT_1241);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.util.BasicEList"));
    stringBuffer.append(TEXT_1242);
    stringBuffer.append(size);
    stringBuffer.append(TEXT_1243);
    stringBuffer.append(genOperation.getParametersArray(genClass));
    stringBuffer.append(TEXT_1244);
    } else {
    stringBuffer.append(TEXT_1245);
    }
    stringBuffer.append(TEXT_1246);
    } else {
    stringBuffer.append(TEXT_1247);
    if (!isJDK50 && genOperation.isPrimitiveType()) {
    stringBuffer.append(TEXT_1248);
    }
    stringBuffer.append(TEXT_1249);
    stringBuffer.append(genOperation.getObjectType(genClass));
    stringBuffer.append(TEXT_1250);
    stringBuffer.append(CodeGenUtil.upperName(genClass.getUniqueName(genOperation), genModel.getLocale()));
    stringBuffer.append(TEXT_1251);
    if (size > 0) {
    stringBuffer.append(TEXT_1252);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.util.BasicEList"));
    stringBuffer.append(TEXT_1253);
    stringBuffer.append(size);
    stringBuffer.append(TEXT_1254);
    stringBuffer.append(genOperation.getParametersArray(genClass));
    stringBuffer.append(TEXT_1255);
    } else {
    stringBuffer.append(TEXT_1256);
    }
    stringBuffer.append(TEXT_1257);
    if (!isJDK50 && genOperation.isPrimitiveType()) {
    stringBuffer.append(TEXT_1258);
    stringBuffer.append(genOperation.getPrimitiveValueFunction());
    stringBuffer.append(TEXT_1259);
    }
    stringBuffer.append(TEXT_1260);
    }
    stringBuffer.append(TEXT_1261);
    stringBuffer.append(genModel.getImportedName(isGWT ? "org.eclipse.emf.common.util.InvocationTargetException" : "java.lang.reflect.InvocationTargetException"));
    stringBuffer.append(TEXT_1262);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.util.WrappedException"));
    stringBuffer.append(TEXT_1263);
    } else {
    stringBuffer.append(TEXT_1264);
    //Class/implementedGenOperation.todo.override.javajetinc
    }
    stringBuffer.append(TEXT_1265);
    }
    //Class/implementedGenOperation.override.javajetinc
    }//for
    if (isImplementation && !genModel.isReflectiveDelegation() && genClass.implementsAny(genClass.getEInverseAddGenFeatures())) {
    stringBuffer.append(TEXT_1266);
    if (genModel.useGenerics()) {
    for (GenFeature genFeature : genClass.getEInverseAddGenFeatures()) {
    if (genFeature.isUncheckedCast(genClass)) {
    stringBuffer.append(TEXT_1267);
    break; }
    }
    }
    if (genModel.useClassOverrideAnnotation()) {
    stringBuffer.append(TEXT_1268);
    }
    stringBuffer.append(TEXT_1269);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.notify.NotificationChain"));
    stringBuffer.append(TEXT_1270);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.InternalEObject"));
    stringBuffer.append(TEXT_1271);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.notify.NotificationChain"));
    stringBuffer.append(TEXT_1272);
    stringBuffer.append(negativeOffsetCorrection);
    stringBuffer.append(TEXT_1273);
    for (GenFeature genFeature : genClass.getEInverseAddGenFeatures()) {
    stringBuffer.append(TEXT_1274);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(TEXT_1275);
    if (genFeature.isListType()) { String cast = "("  + genModel.getImportedName("org.eclipse.emf.ecore.util.InternalEList") + (!genModel.useGenerics() ? ")" : "<" + genModel.getImportedName("org.eclipse.emf.ecore.InternalEObject") + ">)(" + genModel.getImportedName("org.eclipse.emf.ecore.util.InternalEList") + "<?>)");
    if (genFeature.isMapType() && genFeature.isEffectiveSuppressEMFTypes()) {
    stringBuffer.append(TEXT_1276);
    stringBuffer.append(cast);
    stringBuffer.append(TEXT_1277);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.util.EMap"));
    stringBuffer.append(TEXT_1278);
    stringBuffer.append(genFeature.getImportedMapTemplateArguments(genClass));
    stringBuffer.append(TEXT_1279);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_1280);
    } else {
    stringBuffer.append(TEXT_1281);
    stringBuffer.append(cast);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_1282);
    }
    } else if (genFeature.isContainer()) {
    stringBuffer.append(TEXT_1283);
    if (genFeature.isBasicSet()) {
    stringBuffer.append(TEXT_1284);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_1285);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_1286);
    } else {
    stringBuffer.append(TEXT_1287);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(positiveOffsetCorrection);
    stringBuffer.append(TEXT_1288);
    }
    } else {
    if (genClass.getImplementingGenModel(genFeature).isVirtualDelegation()) {
    stringBuffer.append(TEXT_1289);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_1290);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_1291);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_1292);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(positiveOffsetCorrection);
    stringBuffer.append(TEXT_1293);
    } else if (genFeature.isVolatile() || genClass.getImplementingGenModel(genFeature).isDynamicDelegation()) {
    stringBuffer.append(TEXT_1294);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_1295);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_1296);
    if (genFeature.isResolveProxies()) {
    stringBuffer.append(TEXT_1297);
    stringBuffer.append(genFeature.getAccessorName());
    } else {
    stringBuffer.append(genFeature.getGetAccessor());
    }
    stringBuffer.append(TEXT_1298);
    }
    stringBuffer.append(TEXT_1299);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_1300);
    if (genFeature.isEffectiveContains()) {
    stringBuffer.append(TEXT_1301);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.InternalEObject"));
    stringBuffer.append(TEXT_1302);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_1303);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(negativeOffsetCorrection);
    stringBuffer.append(TEXT_1304);
    } else { GenFeature reverseFeature = genFeature.getReverse(); GenClass targetClass = reverseFeature.getGenClass(); String reverseOffsetCorrection = targetClass.hasOffsetCorrection() ? " + " + genClass.getOffsetCorrectionField(genFeature) : "";
    stringBuffer.append(TEXT_1305);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.InternalEObject"));
    stringBuffer.append(TEXT_1306);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_1307);
    stringBuffer.append(targetClass.getQualifiedFeatureID(reverseFeature));
    stringBuffer.append(reverseOffsetCorrection);
    stringBuffer.append(TEXT_1308);
    stringBuffer.append(targetClass.getRawImportedInterfaceName());
    stringBuffer.append(TEXT_1309);
    }
    stringBuffer.append(TEXT_1310);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_1311);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_1312);
    }
    }
    stringBuffer.append(TEXT_1313);
    if (genModel.isMinimalReflectiveMethods()) {
    stringBuffer.append(TEXT_1314);
    } else {
    stringBuffer.append(TEXT_1315);
    }
    stringBuffer.append(TEXT_1316);
    }
    if (isImplementation && !genModel.isReflectiveDelegation() && genClass.implementsAny(genClass.getEInverseRemoveGenFeatures())) {
    stringBuffer.append(TEXT_1317);
    if (genModel.useClassOverrideAnnotation()) {
    stringBuffer.append(TEXT_1318);
    }
    stringBuffer.append(TEXT_1319);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.notify.NotificationChain"));
    stringBuffer.append(TEXT_1320);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.InternalEObject"));
    stringBuffer.append(TEXT_1321);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.notify.NotificationChain"));
    stringBuffer.append(TEXT_1322);
    stringBuffer.append(negativeOffsetCorrection);
    stringBuffer.append(TEXT_1323);
    for (GenFeature genFeature : genClass.getEInverseRemoveGenFeatures()) {
    stringBuffer.append(TEXT_1324);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(TEXT_1325);
    if (genFeature.isListType()) {
    if (genFeature.isMapType() && genFeature.isEffectiveSuppressEMFTypes()) {
    stringBuffer.append(TEXT_1326);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.InternalEList"));
    stringBuffer.append(singleWildcard);
    stringBuffer.append(TEXT_1327);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.util.EMap"));
    stringBuffer.append(TEXT_1328);
    stringBuffer.append(genFeature.getImportedMapTemplateArguments(genClass));
    stringBuffer.append(TEXT_1329);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_1330);
    } else if (genFeature.isWrappedFeatureMapType()) {
    stringBuffer.append(TEXT_1331);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.InternalEList"));
    stringBuffer.append(singleWildcard);
    stringBuffer.append(TEXT_1332);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.FeatureMap"));
    stringBuffer.append(TEXT_1333);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_1334);
    } else {
    stringBuffer.append(TEXT_1335);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.InternalEList"));
    stringBuffer.append(singleWildcard);
    stringBuffer.append(TEXT_1336);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_1337);
    }
    } else if (genFeature.isContainer() && !genFeature.isBasicSet()) {
    stringBuffer.append(TEXT_1338);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(positiveOffsetCorrection);
    stringBuffer.append(TEXT_1339);
    } else if (genFeature.isUnsettable()) {
    stringBuffer.append(TEXT_1340);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_1341);
    } else {
    stringBuffer.append(TEXT_1342);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_1343);
    }
    }
    stringBuffer.append(TEXT_1344);
    if (genModel.isMinimalReflectiveMethods()) {
    stringBuffer.append(TEXT_1345);
    } else {
    stringBuffer.append(TEXT_1346);
    }
    stringBuffer.append(TEXT_1347);
    }
    if (isImplementation && !genModel.isReflectiveDelegation() && genClass.implementsAny(genClass.getEBasicRemoveFromContainerGenFeatures())) {
    stringBuffer.append(TEXT_1348);
    if (genModel.useClassOverrideAnnotation()) {
    stringBuffer.append(TEXT_1349);
    }
    stringBuffer.append(TEXT_1350);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.notify.NotificationChain"));
    stringBuffer.append(TEXT_1351);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.notify.NotificationChain"));
    stringBuffer.append(TEXT_1352);
    stringBuffer.append(negativeOffsetCorrection);
    stringBuffer.append(TEXT_1353);
    for (GenFeature genFeature : genClass.getEBasicRemoveFromContainerGenFeatures()) {
    GenFeature reverseFeature = genFeature.getReverse(); GenClass targetClass = reverseFeature.getGenClass(); String reverseOffsetCorrection = targetClass.hasOffsetCorrection() ? " + " + genClass.getOffsetCorrectionField(genFeature) : "";
    stringBuffer.append(TEXT_1354);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(TEXT_1355);
    stringBuffer.append(targetClass.getQualifiedFeatureID(reverseFeature));
    stringBuffer.append(reverseOffsetCorrection);
    stringBuffer.append(TEXT_1356);
    stringBuffer.append(targetClass.getRawImportedInterfaceName());
    stringBuffer.append(TEXT_1357);
    }
    stringBuffer.append(TEXT_1358);
    if (genModel.isMinimalReflectiveMethods()) {
    stringBuffer.append(TEXT_1359);
    } else {
    stringBuffer.append(TEXT_1360);
    }
    stringBuffer.append(TEXT_1361);
    }
    if (isImplementation && !genModel.isReflectiveDelegation() && genClass.implementsAny(genClass.getEGetGenFeatures())) {
    stringBuffer.append(TEXT_1362);
    if (genModel.useClassOverrideAnnotation()) {
    stringBuffer.append(TEXT_1363);
    }
    stringBuffer.append(TEXT_1364);
    stringBuffer.append(negativeOffsetCorrection);
    stringBuffer.append(TEXT_1365);
    for (GenFeature genFeature : genClass.getEGetGenFeatures()) {
    stringBuffer.append(TEXT_1366);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(TEXT_1367);
    if (genFeature.isPrimitiveType()) {
    if (isJDK50) {
    stringBuffer.append(TEXT_1368);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_1369);
    } else if (genFeature.isBooleanType()) {
    stringBuffer.append(TEXT_1370);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_1371);
    } else {
    stringBuffer.append(TEXT_1372);
    stringBuffer.append(genFeature.getObjectType(genClass));
    stringBuffer.append(TEXT_1373);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_1374);
    }
    } else if (genFeature.isResolveProxies() && !genFeature.isListType()) {
    stringBuffer.append(TEXT_1375);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_1376);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_1377);
    } else if (genFeature.isMapType()) {
    if (genFeature.isEffectiveSuppressEMFTypes()) {
    stringBuffer.append(TEXT_1378);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.util.EMap"));
    stringBuffer.append(TEXT_1379);
    stringBuffer.append(genFeature.getImportedMapTemplateArguments(genClass));
    stringBuffer.append(TEXT_1380);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_1381);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_1382);
    } else {
    stringBuffer.append(TEXT_1383);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_1384);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_1385);
    }
    } else if (genFeature.isWrappedFeatureMapType()) {
    stringBuffer.append(TEXT_1386);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.FeatureMap"));
    stringBuffer.append(TEXT_1387);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_1388);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_1389);
    } else if (genFeature.isFeatureMapType()) {
    stringBuffer.append(TEXT_1390);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_1391);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.FeatureMap"));
    stringBuffer.append(TEXT_1392);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_1393);
    } else {
    stringBuffer.append(TEXT_1394);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_1395);
    }
    }
    stringBuffer.append(TEXT_1396);
    if (genModel.isMinimalReflectiveMethods()) {
    stringBuffer.append(TEXT_1397);
    } else {
    stringBuffer.append(TEXT_1398);
    }
    stringBuffer.append(TEXT_1399);
    }
    if (isImplementation && !genModel.isReflectiveDelegation() && genClass.implementsAny(genClass.getESetGenFeatures())) {
    stringBuffer.append(TEXT_1400);
    if (genModel.useGenerics()) {
    for (GenFeature genFeature : genClass.getESetGenFeatures()) {
    if (genFeature.isUncheckedCast(genClass) && !genFeature.isFeatureMapType() && !genFeature.isMapType()) {
    stringBuffer.append(TEXT_1401);
    break; }
    }
    }
    if (genModel.useClassOverrideAnnotation()) {
    stringBuffer.append(TEXT_1402);
    }
    stringBuffer.append(TEXT_1403);
    stringBuffer.append(negativeOffsetCorrection);
    stringBuffer.append(TEXT_1404);
    for (GenFeature genFeature : genClass.getESetGenFeatures()) {
    stringBuffer.append(TEXT_1405);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(TEXT_1406);
    if (genFeature.isListType()) {
    if (genFeature.isWrappedFeatureMapType()) {
    stringBuffer.append(TEXT_1407);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.FeatureMap"));
    stringBuffer.append(TEXT_1408);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.FeatureMap"));
    stringBuffer.append(TEXT_1409);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_1410);
    } else if (genFeature.isFeatureMapType()) {
    stringBuffer.append(TEXT_1411);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.FeatureMap"));
    stringBuffer.append(TEXT_1412);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_1413);
    } else if (genFeature.isMapType()) {
    if (genFeature.isEffectiveSuppressEMFTypes()) {
    stringBuffer.append(TEXT_1414);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.EStructuralFeature"));
    stringBuffer.append(TEXT_1415);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.util.EMap"));
    stringBuffer.append(TEXT_1416);
    stringBuffer.append(genFeature.getImportedMapTemplateArguments(genClass));
    stringBuffer.append(TEXT_1417);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_1418);
    } else {
    stringBuffer.append(TEXT_1419);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.EStructuralFeature"));
    stringBuffer.append(TEXT_1420);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_1421);
    }
    } else {
    stringBuffer.append(TEXT_1422);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_1423);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_1424);
    stringBuffer.append(genModel.getImportedName("java.util.Collection"));
    if (isJDK50) {
    stringBuffer.append(TEXT_1425);
    stringBuffer.append(genFeature.getListItemType(genClass));
    stringBuffer.append(TEXT_1426);
    }
    stringBuffer.append(TEXT_1427);
    }
    } else if (!isJDK50 && genFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_1428);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_1429);
    stringBuffer.append(genFeature.getObjectType(genClass));
    stringBuffer.append(TEXT_1430);
    stringBuffer.append(genFeature.getPrimitiveValueFunction());
    stringBuffer.append(TEXT_1431);
    } else {
    stringBuffer.append(TEXT_1432);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_1433);
    if (genFeature.getTypeGenDataType() == null || !genFeature.getTypeGenDataType().isObjectType() || !genFeature.getRawType().equals(genFeature.getType(genClass))) {
    stringBuffer.append(TEXT_1434);
    stringBuffer.append(genFeature.getObjectType(genClass));
    stringBuffer.append(TEXT_1435);
    }
    stringBuffer.append(TEXT_1436);
    }
    stringBuffer.append(TEXT_1437);
    }
    stringBuffer.append(TEXT_1438);
    if (genModel.isMinimalReflectiveMethods()) {
    stringBuffer.append(TEXT_1439);
    } else {
    stringBuffer.append(TEXT_1440);
    }
    stringBuffer.append(TEXT_1441);
    }
    if (isImplementation && !genModel.isReflectiveDelegation() && genClass.implementsAny(genClass.getEUnsetGenFeatures())) {
    stringBuffer.append(TEXT_1442);
    if (genModel.useClassOverrideAnnotation()) {
    stringBuffer.append(TEXT_1443);
    }
    stringBuffer.append(TEXT_1444);
    stringBuffer.append(negativeOffsetCorrection);
    stringBuffer.append(TEXT_1445);
    for (GenFeature genFeature : genClass.getEUnsetGenFeatures()) {
    stringBuffer.append(TEXT_1446);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(TEXT_1447);
    if (genFeature.isListType() && !genFeature.isUnsettable()) {
    if (genFeature.isWrappedFeatureMapType()) {
    stringBuffer.append(TEXT_1448);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.FeatureMap"));
    stringBuffer.append(TEXT_1449);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_1450);
    } else {
    stringBuffer.append(TEXT_1451);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_1452);
    }
    } else if (genFeature.isUnsettable()) {
    stringBuffer.append(TEXT_1453);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_1454);
    } else if (!genFeature.hasEDefault()) {
    stringBuffer.append(TEXT_1455);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_1456);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_1457);
    } else if (genFeature.hasSettingDelegate()) {
    stringBuffer.append(TEXT_1458);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_1459);
    } else {
    stringBuffer.append(TEXT_1460);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_1461);
    stringBuffer.append(genFeature.getEDefault());
    stringBuffer.append(TEXT_1462);
    }
    stringBuffer.append(TEXT_1463);
    }
    stringBuffer.append(TEXT_1464);
    if (genModel.isMinimalReflectiveMethods()) {
    stringBuffer.append(TEXT_1465);
    } else {
    stringBuffer.append(TEXT_1466);
    }
    stringBuffer.append(TEXT_1467);
    //Class/eUnset.override.javajetinc
    }
    if (isImplementation && !genModel.isReflectiveDelegation() && genClass.implementsAny(genClass.getEIsSetGenFeatures())) {
    stringBuffer.append(TEXT_1468);
    if (genModel.useGenerics()) {
    for (GenFeature genFeature : genClass.getEIsSetGenFeatures()) {
    if (genFeature.isListType() && !genFeature.isUnsettable() && !genFeature.isWrappedFeatureMapType() && !genClass.isField(genFeature) && genFeature.isField() && genClass.getImplementingGenModel(genFeature).isVirtualDelegation()) {
    stringBuffer.append(TEXT_1469);
    break; }
    }
    }
    if (genModel.useClassOverrideAnnotation()) {
    stringBuffer.append(TEXT_1470);
    }
    stringBuffer.append(TEXT_1471);
    stringBuffer.append(negativeOffsetCorrection);
    stringBuffer.append(TEXT_1472);
    for (GenFeature genFeature : genClass.getEIsSetGenFeatures()) { String safeNameAccessor = genFeature.getSafeName(); if ("featureID".equals(safeNameAccessor)) { safeNameAccessor = "this." + safeNameAccessor; }
    stringBuffer.append(TEXT_1473);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(TEXT_1474);
    if (genFeature.hasSettingDelegate()) {
    if (genFeature.isUnsettable()) {
    stringBuffer.append(TEXT_1475);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_1476);
    } else {
    stringBuffer.append(TEXT_1477);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_1478);
    }
    } else if (genFeature.isListType() && !genFeature.isUnsettable()) {
    if (genFeature.isWrappedFeatureMapType()) {
    if (genFeature.isVolatile()) {
    stringBuffer.append(TEXT_1479);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.FeatureMap"));
    stringBuffer.append(TEXT_1480);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_1481);
    } else {
    stringBuffer.append(TEXT_1482);
    stringBuffer.append(safeNameAccessor);
    stringBuffer.append(TEXT_1483);
    stringBuffer.append(safeNameAccessor);
    stringBuffer.append(TEXT_1484);
    }
    } else {
    if (genClass.isField(genFeature)) {
    stringBuffer.append(TEXT_1485);
    stringBuffer.append(safeNameAccessor);
    stringBuffer.append(TEXT_1486);
    stringBuffer.append(safeNameAccessor);
    stringBuffer.append(TEXT_1487);
    } else {
    if (genFeature.isField() && genClass.getImplementingGenModel(genFeature).isVirtualDelegation()) {
    stringBuffer.append(TEXT_1488);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_1489);
    stringBuffer.append(safeNameAccessor);
    stringBuffer.append(TEXT_1490);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_1491);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(positiveOffsetCorrection);
    stringBuffer.append(TEXT_1492);
    stringBuffer.append(safeNameAccessor);
    stringBuffer.append(TEXT_1493);
    stringBuffer.append(safeNameAccessor);
    stringBuffer.append(TEXT_1494);
    } else {
    stringBuffer.append(TEXT_1495);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_1496);
    }
    }
    }
    } else if (genFeature.isUnsettable()) {
    stringBuffer.append(TEXT_1497);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_1498);
    } else if (genFeature.isResolveProxies()) {
    if (genClass.isField(genFeature)) {
    stringBuffer.append(TEXT_1499);
    stringBuffer.append(safeNameAccessor);
    stringBuffer.append(TEXT_1500);
    } else {
    if (genFeature.isField() && genClass.getImplementingGenModel(genFeature).isVirtualDelegation()) {
    stringBuffer.append(TEXT_1501);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(positiveOffsetCorrection);
    stringBuffer.append(TEXT_1502);
    } else {
    stringBuffer.append(TEXT_1503);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_1504);
    }
    }
    } else if (!genFeature.hasEDefault()) {
    if (genClass.isField(genFeature)) {
    stringBuffer.append(TEXT_1505);
    stringBuffer.append(safeNameAccessor);
    stringBuffer.append(TEXT_1506);
    } else {
    if (genFeature.isField() && genClass.getImplementingGenModel(genFeature).isVirtualDelegation()) {
    stringBuffer.append(TEXT_1507);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(positiveOffsetCorrection);
    stringBuffer.append(TEXT_1508);
    } else {
    stringBuffer.append(TEXT_1509);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_1510);
    }
    }
    } else if (genFeature.isPrimitiveType() || genFeature.isEnumType()) {
    if (genClass.isField(genFeature)) {
    if (genClass.isFlag(genFeature)) {
    if (genFeature.isBooleanType()) {
    stringBuffer.append(TEXT_1511);
    stringBuffer.append(genClass.getFlagsField(genFeature));
    stringBuffer.append(TEXT_1512);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_1513);
    stringBuffer.append(genFeature.getEDefault());
    stringBuffer.append(TEXT_1514);
    } else {
    stringBuffer.append(TEXT_1515);
    stringBuffer.append(genClass.getFlagsField(genFeature));
    stringBuffer.append(TEXT_1516);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_1517);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_1518);
    }
    } else {
    stringBuffer.append(TEXT_1519);
    stringBuffer.append(safeNameAccessor);
    stringBuffer.append(TEXT_1520);
    stringBuffer.append(genFeature.getEDefault());
    stringBuffer.append(TEXT_1521);
    }
    } else {
    if (genFeature.isEnumType() && genFeature.isField() && genClass.getImplementingGenModel(genFeature).isVirtualDelegation()) {
    stringBuffer.append(TEXT_1522);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(positiveOffsetCorrection);
    stringBuffer.append(TEXT_1523);
    stringBuffer.append(genFeature.getEDefault());
    stringBuffer.append(TEXT_1524);
    stringBuffer.append(genFeature.getEDefault());
    stringBuffer.append(TEXT_1525);
    } else {
    stringBuffer.append(TEXT_1526);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_1527);
    stringBuffer.append(genFeature.getEDefault());
    stringBuffer.append(TEXT_1528);
    }
    }
    } else {//datatype
    if (genClass.isField(genFeature)) {
    stringBuffer.append(TEXT_1529);
    stringBuffer.append(genFeature.getEDefault());
    stringBuffer.append(TEXT_1530);
    stringBuffer.append(safeNameAccessor);
    stringBuffer.append(TEXT_1531);
    stringBuffer.append(genFeature.getEDefault());
    stringBuffer.append(TEXT_1532);
    stringBuffer.append(safeNameAccessor);
    stringBuffer.append(TEXT_1533);
    } else {
    if (genFeature.isField() && genClass.getImplementingGenModel(genFeature).isVirtualDelegation()) {
    stringBuffer.append(TEXT_1534);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_1535);
    stringBuffer.append(safeNameAccessor);
    stringBuffer.append(TEXT_1536);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_1537);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(positiveOffsetCorrection);
    stringBuffer.append(TEXT_1538);
    stringBuffer.append(genFeature.getEDefault());
    stringBuffer.append(TEXT_1539);
    stringBuffer.append(genFeature.getEDefault());
    stringBuffer.append(TEXT_1540);
    stringBuffer.append(safeNameAccessor);
    stringBuffer.append(TEXT_1541);
    stringBuffer.append(genFeature.getEDefault());
    stringBuffer.append(TEXT_1542);
    stringBuffer.append(safeNameAccessor);
    stringBuffer.append(TEXT_1543);
    } else {
    stringBuffer.append(TEXT_1544);
    stringBuffer.append(genFeature.getEDefault());
    stringBuffer.append(TEXT_1545);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_1546);
    stringBuffer.append(genFeature.getEDefault());
    stringBuffer.append(TEXT_1547);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_1548);
    }
    }
    }
    }
    stringBuffer.append(TEXT_1549);
    if (genModel.isMinimalReflectiveMethods()) {
    stringBuffer.append(TEXT_1550);
    } else {
    stringBuffer.append(TEXT_1551);
    }
    stringBuffer.append(TEXT_1552);
    //Class/eIsSet.override.javajetinc
    }
    if (isImplementation && (!genClass.getMixinGenFeatures().isEmpty() || genClass.hasOffsetCorrection() && !genClass.getGenFeatures().isEmpty())) {
    if (!genClass.getMixinGenFeatures().isEmpty()) {
    stringBuffer.append(TEXT_1553);
    if (genModel.useClassOverrideAnnotation()) {
    stringBuffer.append(TEXT_1554);
    }
    stringBuffer.append(TEXT_1555);
    stringBuffer.append(singleWildcard);
    stringBuffer.append(TEXT_1556);
    for (GenClass mixinGenClass : genClass.getMixinGenClasses()) {
    stringBuffer.append(TEXT_1557);
    stringBuffer.append(mixinGenClass.getRawImportedInterfaceName());
    stringBuffer.append(TEXT_1558);
    stringBuffer.append(negativeOffsetCorrection);
    stringBuffer.append(TEXT_1559);
    for (GenFeature genFeature : mixinGenClass.getGenFeatures()) {
    stringBuffer.append(TEXT_1560);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(TEXT_1561);
    stringBuffer.append(mixinGenClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(TEXT_1562);
    }
    stringBuffer.append(TEXT_1563);
    }
    stringBuffer.append(TEXT_1564);
    }
    stringBuffer.append(TEXT_1565);
    if (genModel.useClassOverrideAnnotation()) {
    stringBuffer.append(TEXT_1566);
    }
    stringBuffer.append(TEXT_1567);
    stringBuffer.append(singleWildcard);
    stringBuffer.append(TEXT_1568);
    for (GenClass mixinGenClass : genClass.getMixinGenClasses()) {
    stringBuffer.append(TEXT_1569);
    stringBuffer.append(mixinGenClass.getRawImportedInterfaceName());
    stringBuffer.append(TEXT_1570);
    for (GenFeature genFeature : mixinGenClass.getGenFeatures()) {
    stringBuffer.append(TEXT_1571);
    stringBuffer.append(mixinGenClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(TEXT_1572);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(positiveOffsetCorrection);
    stringBuffer.append(TEXT_1573);
    }
    stringBuffer.append(TEXT_1574);
    }
    if (genClass.hasOffsetCorrection() && !genClass.getGenFeatures().isEmpty()) {
    stringBuffer.append(TEXT_1575);
    stringBuffer.append(genClass.getRawImportedInterfaceName());
    stringBuffer.append(TEXT_1576);
    stringBuffer.append(negativeOffsetCorrection);
    stringBuffer.append(TEXT_1577);
    for (GenFeature genFeature : genClass.getGenFeatures()) {
    stringBuffer.append(TEXT_1578);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(TEXT_1579);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(positiveOffsetCorrection);
    stringBuffer.append(TEXT_1580);
    }
    stringBuffer.append(TEXT_1581);
    }
    stringBuffer.append(TEXT_1582);
    }
    if (genModel.isOperationReflection() && isImplementation && (!genClass.getMixinGenOperations().isEmpty() || !genClass.getOverrideGenOperations(genClass.getExtendedGenOperations(), genClass.getImplementedGenOperations()).isEmpty() || genClass.hasOffsetCorrection() && !genClass.getGenOperations().isEmpty())) {
    stringBuffer.append(TEXT_1583);
    if (genModel.useClassOverrideAnnotation()) {
    stringBuffer.append(TEXT_1584);
    }
    stringBuffer.append(TEXT_1585);
    stringBuffer.append(singleWildcard);
    stringBuffer.append(TEXT_1586);
    for (GenClass extendedGenClass : genClass.getExtendedGenClasses()) { List<GenOperation> extendedImplementedGenOperations = extendedGenClass.getImplementedGenOperations(); List<GenOperation> implementedGenOperations = genClass.getImplementedGenOperations();
    if (!genClass.getOverrideGenOperations(extendedImplementedGenOperations, implementedGenOperations).isEmpty()) {
    stringBuffer.append(TEXT_1587);
    stringBuffer.append(extendedGenClass.getRawImportedInterfaceName());
    stringBuffer.append(TEXT_1588);
    for (GenOperation genOperation : extendedImplementedGenOperations) { GenOperation overrideGenOperation = genClass.getOverrideGenOperation(genOperation);
    if (implementedGenOperations.contains(overrideGenOperation)) {
    stringBuffer.append(TEXT_1589);
    stringBuffer.append(extendedGenClass.getQualifiedOperationID(genOperation));
    stringBuffer.append(TEXT_1590);
    stringBuffer.append(genClass.getQualifiedOperationID(overrideGenOperation));
    stringBuffer.append(positiveOperationOffsetCorrection);
    stringBuffer.append(TEXT_1591);
    }
    }
    stringBuffer.append(TEXT_1592);
    }
    }
    for (GenClass mixinGenClass : genClass.getMixinGenClasses()) {
    stringBuffer.append(TEXT_1593);
    stringBuffer.append(mixinGenClass.getRawImportedInterfaceName());
    stringBuffer.append(TEXT_1594);
    for (GenOperation genOperation : mixinGenClass.getGenOperations()) { GenOperation overrideGenOperation = genClass.getOverrideGenOperation(genOperation);
    stringBuffer.append(TEXT_1595);
    stringBuffer.append(mixinGenClass.getQualifiedOperationID(genOperation));
    stringBuffer.append(TEXT_1596);
    stringBuffer.append(genClass.getQualifiedOperationID(overrideGenOperation != null ? overrideGenOperation : genOperation));
    stringBuffer.append(positiveOperationOffsetCorrection);
    stringBuffer.append(TEXT_1597);
    }
    stringBuffer.append(TEXT_1598);
    }
    if (genClass.hasOffsetCorrection() && !genClass.getGenOperations().isEmpty()) {
    stringBuffer.append(TEXT_1599);
    stringBuffer.append(genClass.getRawImportedInterfaceName());
    stringBuffer.append(TEXT_1600);
    stringBuffer.append(negativeOperationOffsetCorrection);
    stringBuffer.append(TEXT_1601);
    for (GenOperation genOperation : genClass.getGenOperations()) {
    stringBuffer.append(TEXT_1602);
    stringBuffer.append(genClass.getQualifiedOperationID(genOperation));
    stringBuffer.append(TEXT_1603);
    stringBuffer.append(genClass.getQualifiedOperationID(genOperation));
    stringBuffer.append(positiveOperationOffsetCorrection);
    stringBuffer.append(TEXT_1604);
    }
    stringBuffer.append(TEXT_1605);
    }
    stringBuffer.append(TEXT_1606);
    }
    if (isImplementation && genModel.isVirtualDelegation()) { String eVirtualValuesField = genClass.getEVirtualValuesField();
    if (eVirtualValuesField != null) {
    stringBuffer.append(TEXT_1607);
    if (genModel.useClassOverrideAnnotation()) {
    stringBuffer.append(TEXT_1608);
    }
    stringBuffer.append(TEXT_1609);
    stringBuffer.append(eVirtualValuesField);
    stringBuffer.append(TEXT_1610);
    if (genModel.useClassOverrideAnnotation()) {
    stringBuffer.append(TEXT_1611);
    }
    stringBuffer.append(TEXT_1612);
    stringBuffer.append(eVirtualValuesField);
    stringBuffer.append(TEXT_1613);
    }
    { List<String> eVirtualIndexBitFields = genClass.getEVirtualIndexBitFields(new ArrayList<String>());
    if (!eVirtualIndexBitFields.isEmpty()) { List<String> allEVirtualIndexBitFields = genClass.getAllEVirtualIndexBitFields(new ArrayList<String>());
    stringBuffer.append(TEXT_1614);
    if (genModel.useClassOverrideAnnotation()) {
    stringBuffer.append(TEXT_1615);
    }
    stringBuffer.append(TEXT_1616);
    for (int i = 0; i < allEVirtualIndexBitFields.size(); i++) {
    stringBuffer.append(TEXT_1617);
    stringBuffer.append(i);
    stringBuffer.append(TEXT_1618);
    stringBuffer.append(allEVirtualIndexBitFields.get(i));
    stringBuffer.append(TEXT_1619);
    }
    stringBuffer.append(TEXT_1620);
    if (genModel.useClassOverrideAnnotation()) {
    stringBuffer.append(TEXT_1621);
    }
    stringBuffer.append(TEXT_1622);
    for (int i = 0; i < allEVirtualIndexBitFields.size(); i++) {
    stringBuffer.append(TEXT_1623);
    stringBuffer.append(i);
    stringBuffer.append(TEXT_1624);
    stringBuffer.append(allEVirtualIndexBitFields.get(i));
    stringBuffer.append(TEXT_1625);
    }
    stringBuffer.append(TEXT_1626);
    }
    }
    }
    if (genModel.isOperationReflection() && isImplementation && !genClass.getImplementedGenOperations().isEmpty()) {
    stringBuffer.append(TEXT_1627);
    if (genModel.useClassOverrideAnnotation()) {
    stringBuffer.append(TEXT_1628);
    }
    if (genModel.useGenerics()) {
    boolean isUnchecked = false; boolean isRaw = false; LOOP: for (GenOperation genOperation : (genModel.isMinimalReflectiveMethods() ? genClass.getImplementedGenOperations() : genClass.getAllGenOperations())) { for (GenParameter genParameter : genOperation.getGenParameters()) { if (genParameter.isUncheckedCast()) { if (genParameter.getTypeGenDataType() == null || !genParameter.getTypeGenDataType().isObjectType()) { isUnchecked = true; } if (genParameter.usesOperationTypeParameters() && !genParameter.getEcoreParameter().getEGenericType().getETypeArguments().isEmpty()) { isRaw = true; break LOOP; }}}}
    if (isUnchecked) {
    stringBuffer.append(TEXT_1629);
    if (!isRaw) {
    stringBuffer.append(TEXT_1630);
    } else {
    stringBuffer.append(TEXT_1631);
    }
    stringBuffer.append(TEXT_1632);
    }
    }
    stringBuffer.append(TEXT_1633);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.util.EList"));
    stringBuffer.append(singleWildcard);
    stringBuffer.append(TEXT_1634);
    stringBuffer.append(genModel.getImportedName(isGWT ? "org.eclipse.emf.common.util.InvocationTargetException" : "java.lang.reflect.InvocationTargetException"));
    stringBuffer.append(TEXT_1635);
    stringBuffer.append(negativeOperationOffsetCorrection);
    stringBuffer.append(TEXT_1636);
    for (GenOperation genOperation : (genModel.isMinimalReflectiveMethods() ? genClass.getImplementedGenOperations() : genClass.getAllGenOperations())) { List<GenParameter> genParameters = genOperation.getGenParameters(); int size = genParameters.size();
    stringBuffer.append(TEXT_1637);
    stringBuffer.append(genClass.getQualifiedOperationID(genOperation));
    stringBuffer.append(TEXT_1638);
    if (genOperation.isVoid()) {
    stringBuffer.append(TEXT_1639);
    stringBuffer.append(genOperation.getName());
    stringBuffer.append(TEXT_1640);
    for (int i = 0; i < size; i++) { GenParameter genParameter = genParameters.get(i);
    if (!isJDK50 && genParameter.isPrimitiveType()) {
    stringBuffer.append(TEXT_1641);
    }
    if (genParameter.getTypeGenDataType() == null || !genParameter.getTypeGenDataType().isObjectType() || !genParameter.usesOperationTypeParameters() && !genParameter.getRawType().equals(genParameter.getType(genClass))) {
    stringBuffer.append(TEXT_1642);
    stringBuffer.append(genParameter.usesOperationTypeParameters() ? genParameter.getRawImportedType() : genParameter.getObjectType(genClass));
    stringBuffer.append(TEXT_1643);
    }
    stringBuffer.append(TEXT_1644);
    stringBuffer.append(i);
    stringBuffer.append(TEXT_1645);
    if (!isJDK50 && genParameter.isPrimitiveType()) {
    stringBuffer.append(TEXT_1646);
    stringBuffer.append(genParameter.getPrimitiveValueFunction());
    stringBuffer.append(TEXT_1647);
    }
    if (i < (size - 1)) {
    stringBuffer.append(TEXT_1648);
    }
    }
    stringBuffer.append(TEXT_1649);
    } else {
    stringBuffer.append(TEXT_1650);
    if (!isJDK50 && genOperation.isPrimitiveType()) {
    stringBuffer.append(TEXT_1651);
    stringBuffer.append(genOperation.getObjectType(genClass));
    stringBuffer.append(TEXT_1652);
    }
    stringBuffer.append(genOperation.getName());
    stringBuffer.append(TEXT_1653);
    for (int i = 0; i < size; i++) { GenParameter genParameter = genParameters.get(i);
    if (!isJDK50 && genParameter.isPrimitiveType()) {
    stringBuffer.append(TEXT_1654);
    }
    if (genParameter.getTypeGenDataType() == null || !genParameter.getTypeGenDataType().isObjectType() || !genParameter.usesOperationTypeParameters() && !genParameter.getRawType().equals(genParameter.getType(genClass))) {
    stringBuffer.append(TEXT_1655);
    stringBuffer.append(genParameter.usesOperationTypeParameters() ? genParameter.getRawImportedType() : genParameter.getObjectType(genClass));
    stringBuffer.append(TEXT_1656);
    }
    stringBuffer.append(TEXT_1657);
    stringBuffer.append(i);
    stringBuffer.append(TEXT_1658);
    if (!isJDK50 && genParameter.isPrimitiveType()) {
    stringBuffer.append(TEXT_1659);
    stringBuffer.append(genParameter.getPrimitiveValueFunction());
    stringBuffer.append(TEXT_1660);
    }
    if (i < (size - 1)) {
    stringBuffer.append(TEXT_1661);
    }
    }
    stringBuffer.append(TEXT_1662);
    if (!isJDK50 && genOperation.isPrimitiveType()) {
    stringBuffer.append(TEXT_1663);
    }
    stringBuffer.append(TEXT_1664);
    }
    }
    stringBuffer.append(TEXT_1665);
    if (genModel.isMinimalReflectiveMethods()) {
    stringBuffer.append(TEXT_1666);
    } else {
    stringBuffer.append(TEXT_1667);
    }
    stringBuffer.append(TEXT_1668);
    }
    if (!genClass.hasImplementedToStringGenOperation() && isImplementation && !genModel.isReflectiveDelegation() && !genModel.isDynamicDelegation() && !genClass.getToStringGenFeatures().isEmpty()) {
    stringBuffer.append(TEXT_1669);
    if (genModel.useClassOverrideAnnotation()) {
    stringBuffer.append(TEXT_1670);
    }
    stringBuffer.append(TEXT_1671);
    { boolean first = true;
    for (GenFeature genFeature : genClass.getToStringGenFeatures()) {
    if (first) { first = false;
    stringBuffer.append(TEXT_1672);
    stringBuffer.append(genFeature.getName());
    stringBuffer.append(TEXT_1673);
    stringBuffer.append(genModel.getNonNLS());
    } else {
    stringBuffer.append(TEXT_1674);
    stringBuffer.append(genFeature.getName());
    stringBuffer.append(TEXT_1675);
    stringBuffer.append(genModel.getNonNLS());
    }
    if (genFeature.isUnsettable() && !genFeature.isListType()) {
    if (genModel.isVirtualDelegation() && !genFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_1676);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(positiveOffsetCorrection);
    stringBuffer.append(TEXT_1677);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(positiveOffsetCorrection);
    stringBuffer.append(TEXT_1678);
    stringBuffer.append(genModel.getNonNLS());
    } else {
    if (genClass.isFlag(genFeature)) {
    if (genFeature.isBooleanType()) {
    stringBuffer.append(TEXT_1679);
    if (genClass.isESetFlag(genFeature)) {
    stringBuffer.append(TEXT_1680);
    stringBuffer.append(genClass.getESetFlagsField(genFeature));
    stringBuffer.append(TEXT_1681);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_1682);
    } else {
    stringBuffer.append(genFeature.getUncapName());
    stringBuffer.append(TEXT_1683);
    }
    stringBuffer.append(TEXT_1684);
    stringBuffer.append(genClass.getFlagsField(genFeature));
    stringBuffer.append(TEXT_1685);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_1686);
    stringBuffer.append(genModel.getNonNLS());
    } else {
    stringBuffer.append(TEXT_1687);
    if (genClass.isESetFlag(genFeature)) {
    stringBuffer.append(TEXT_1688);
    stringBuffer.append(genClass.getESetFlagsField(genFeature));
    stringBuffer.append(TEXT_1689);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_1690);
    } else {
    stringBuffer.append(genFeature.getUncapName());
    stringBuffer.append(TEXT_1691);
    }
    stringBuffer.append(TEXT_1692);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_1693);
    stringBuffer.append(genClass.getFlagsField(genFeature));
    stringBuffer.append(TEXT_1694);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_1695);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_1696);
    stringBuffer.append(genModel.getNonNLS());
    }
    } else {
    stringBuffer.append(TEXT_1697);
    if (genClass.isESetFlag(genFeature)) {
    stringBuffer.append(TEXT_1698);
    stringBuffer.append(genClass.getESetFlagsField(genFeature));
    stringBuffer.append(TEXT_1699);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_1700);
    } else {
    stringBuffer.append(genFeature.getUncapName());
    stringBuffer.append(TEXT_1701);
    }
    stringBuffer.append(TEXT_1702);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_1703);
    stringBuffer.append(genModel.getNonNLS());
    }
    }
    } else {
    if (genModel.isVirtualDelegation() && !genFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_1704);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(positiveOffsetCorrection);
    if (!genFeature.isListType() && !genFeature.isReferenceType()){
    stringBuffer.append(TEXT_1705);
    stringBuffer.append(genFeature.getEDefault());
    }
    stringBuffer.append(TEXT_1706);
    } else {
    if (genClass.isFlag(genFeature)) {
    if (genFeature.isBooleanType()) {
    stringBuffer.append(TEXT_1707);
    stringBuffer.append(genClass.getFlagsField(genFeature));
    stringBuffer.append(TEXT_1708);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_1709);
    } else {
    stringBuffer.append(TEXT_1710);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_1711);
    stringBuffer.append(genClass.getFlagsField(genFeature));
    stringBuffer.append(TEXT_1712);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_1713);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_1714);
    }
    } else {
    stringBuffer.append(TEXT_1715);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_1716);
    }
    }
    }
    }
    }
    stringBuffer.append(TEXT_1717);
    }
    if (isImplementation && genClass.isMapEntry()) { GenFeature keyFeature = genClass.getMapEntryKeyFeature(); GenFeature valueFeature = genClass.getMapEntryValueFeature();
    String objectType = genModel.getImportedName("java.lang.Object");
    String keyType = isJDK50 ? keyFeature.getObjectType(genClass) : objectType;
    String valueType = isJDK50 ? valueFeature.getObjectType(genClass) : objectType;
    String eMapType = genModel.getImportedName("org.eclipse.emf.common.util.EMap") + (isJDK50 ? "<" + keyType + ", " + valueType + ">" : "");
    stringBuffer.append(TEXT_1718);
    if (isGWT) {
    stringBuffer.append(TEXT_1719);
    stringBuffer.append(genModel.getImportedName("com.google.gwt.user.client.rpc.GwtTransient"));
    }
    stringBuffer.append(TEXT_1720);
    stringBuffer.append(objectType);
    stringBuffer.append(TEXT_1721);
    stringBuffer.append(keyType);
    stringBuffer.append(TEXT_1722);
    if (!isJDK50 && keyFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_1723);
    stringBuffer.append(keyFeature.getObjectType(genClass));
    stringBuffer.append(TEXT_1724);
    } else {
    stringBuffer.append(TEXT_1725);
    }
    stringBuffer.append(TEXT_1726);
    stringBuffer.append(keyType);
    stringBuffer.append(TEXT_1727);
    if (keyFeature.isListType()) {
    stringBuffer.append(TEXT_1728);
    if (!genModel.useGenerics()) {
    stringBuffer.append(TEXT_1729);
    stringBuffer.append(genModel.getImportedName("java.util.Collection"));
    stringBuffer.append(TEXT_1730);
    }
    stringBuffer.append(TEXT_1731);
    } else if (isJDK50) {
    stringBuffer.append(TEXT_1732);
    } else if (keyFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_1733);
    stringBuffer.append(keyFeature.getObjectType(genClass));
    stringBuffer.append(TEXT_1734);
    stringBuffer.append(keyFeature.getPrimitiveValueFunction());
    stringBuffer.append(TEXT_1735);
    } else {
    stringBuffer.append(TEXT_1736);
    stringBuffer.append(keyFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_1737);
    }
    stringBuffer.append(TEXT_1738);
    stringBuffer.append(valueType);
    stringBuffer.append(TEXT_1739);
    if (!isJDK50 && valueFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_1740);
    stringBuffer.append(valueFeature.getObjectType(genClass));
    stringBuffer.append(TEXT_1741);
    } else {
    stringBuffer.append(TEXT_1742);
    }
    stringBuffer.append(TEXT_1743);
    stringBuffer.append(valueType);
    stringBuffer.append(TEXT_1744);
    stringBuffer.append(valueType);
    stringBuffer.append(TEXT_1745);
    stringBuffer.append(valueType);
    stringBuffer.append(TEXT_1746);
    if (valueFeature.isListType()) {
    stringBuffer.append(TEXT_1747);
    if (!genModel.useGenerics()) {
    stringBuffer.append(TEXT_1748);
    stringBuffer.append(genModel.getImportedName("java.util.Collection"));
    stringBuffer.append(TEXT_1749);
    }
    stringBuffer.append(TEXT_1750);
    } else if (isJDK50) {
    stringBuffer.append(TEXT_1751);
    } else if (valueFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_1752);
    stringBuffer.append(valueFeature.getObjectType(genClass));
    stringBuffer.append(TEXT_1753);
    stringBuffer.append(valueFeature.getPrimitiveValueFunction());
    stringBuffer.append(TEXT_1754);
    } else {
    stringBuffer.append(TEXT_1755);
    stringBuffer.append(valueFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_1756);
    }
    stringBuffer.append(TEXT_1757);
    if (genModel.useGenerics()) {
    stringBuffer.append(TEXT_1758);
    }
    stringBuffer.append(TEXT_1759);
    stringBuffer.append(eMapType);
    stringBuffer.append(TEXT_1760);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.EObject"));
    stringBuffer.append(TEXT_1761);
    stringBuffer.append(eMapType);
    stringBuffer.append(TEXT_1762);
    }
    stringBuffer.append(TEXT_1763);
    stringBuffer.append(isInterface ? " " + genClass.getInterfaceName() : genClass.getClassName());
    // TODO fix the space above
    genModel.emitSortedImports();
    stringBuffer.append(TEXT_1764);
    return stringBuffer.toString();
  }
}
