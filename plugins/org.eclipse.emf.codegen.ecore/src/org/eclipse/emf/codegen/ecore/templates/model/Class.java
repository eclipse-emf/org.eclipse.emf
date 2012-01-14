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
  protected final String TEXT_379 = ")eContainer();";
  protected final String TEXT_380 = NL + "\t\t";
  protected final String TEXT_381 = " ";
  protected final String TEXT_382 = " = (";
  protected final String TEXT_383 = ")eVirtualGet(";
  protected final String TEXT_384 = ", ";
  protected final String TEXT_385 = ");";
  protected final String TEXT_386 = NL + "\t\tif (";
  protected final String TEXT_387 = " != null && ";
  protected final String TEXT_388 = ".eIsProxy())" + NL + "\t\t{" + NL + "\t\t\t";
  protected final String TEXT_389 = " old";
  protected final String TEXT_390 = " = (";
  protected final String TEXT_391 = ")";
  protected final String TEXT_392 = ";" + NL + "\t\t\t";
  protected final String TEXT_393 = " = ";
  protected final String TEXT_394 = "eResolveProxy(old";
  protected final String TEXT_395 = ");" + NL + "\t\t\tif (";
  protected final String TEXT_396 = " != old";
  protected final String TEXT_397 = ")" + NL + "\t\t\t{";
  protected final String TEXT_398 = NL + "\t\t\t\t";
  protected final String TEXT_399 = " new";
  protected final String TEXT_400 = " = (";
  protected final String TEXT_401 = ")";
  protected final String TEXT_402 = ";";
  protected final String TEXT_403 = NL + "\t\t\t\t";
  protected final String TEXT_404 = " msgs = old";
  protected final String TEXT_405 = ".eInverseRemove(this, EOPPOSITE_FEATURE_BASE - ";
  protected final String TEXT_406 = ", null, null);";
  protected final String TEXT_407 = NL + "\t\t\t\t";
  protected final String TEXT_408 = " msgs =  old";
  protected final String TEXT_409 = ".eInverseRemove(this, ";
  protected final String TEXT_410 = ", ";
  protected final String TEXT_411 = ".class, null);";
  protected final String TEXT_412 = NL + "\t\t\t\tif (new";
  protected final String TEXT_413 = ".eInternalContainer() == null)" + NL + "\t\t\t\t{";
  protected final String TEXT_414 = NL + "\t\t\t\t\tmsgs = new";
  protected final String TEXT_415 = ".eInverseAdd(this, EOPPOSITE_FEATURE_BASE - ";
  protected final String TEXT_416 = ", null, msgs);";
  protected final String TEXT_417 = NL + "\t\t\t\t\tmsgs =  new";
  protected final String TEXT_418 = ".eInverseAdd(this, ";
  protected final String TEXT_419 = ", ";
  protected final String TEXT_420 = ".class, msgs);";
  protected final String TEXT_421 = NL + "\t\t\t\t}" + NL + "\t\t\t\tif (msgs != null) msgs.dispatch();";
  protected final String TEXT_422 = NL + "\t\t\t\teVirtualSet(";
  protected final String TEXT_423 = ", ";
  protected final String TEXT_424 = ");";
  protected final String TEXT_425 = NL + "\t\t\t\tif (eNotificationRequired())" + NL + "\t\t\t\t\teNotify(new ";
  protected final String TEXT_426 = "(this, ";
  protected final String TEXT_427 = ".RESOLVE, ";
  protected final String TEXT_428 = ", old";
  protected final String TEXT_429 = ", ";
  protected final String TEXT_430 = "));";
  protected final String TEXT_431 = NL + "\t\t\t}" + NL + "\t\t}";
  protected final String TEXT_432 = NL + "\t\treturn (";
  protected final String TEXT_433 = ")eVirtualGet(";
  protected final String TEXT_434 = ", ";
  protected final String TEXT_435 = ");";
  protected final String TEXT_436 = NL + "\t\treturn (";
  protected final String TEXT_437 = " & ";
  protected final String TEXT_438 = "_EFLAG) != 0;";
  protected final String TEXT_439 = NL + "\t\treturn ";
  protected final String TEXT_440 = "_EFLAG_VALUES[(";
  protected final String TEXT_441 = " & ";
  protected final String TEXT_442 = "_EFLAG) >>> ";
  protected final String TEXT_443 = "_EFLAG_OFFSET];";
  protected final String TEXT_444 = NL + "\t\treturn ";
  protected final String TEXT_445 = ";";
  protected final String TEXT_446 = NL + "\t\t";
  protected final String TEXT_447 = " ";
  protected final String TEXT_448 = " = basicGet";
  protected final String TEXT_449 = "();" + NL + "\t\treturn ";
  protected final String TEXT_450 = " != null && ";
  protected final String TEXT_451 = ".eIsProxy() ? ";
  protected final String TEXT_452 = "eResolveProxy((";
  protected final String TEXT_453 = ")";
  protected final String TEXT_454 = ") : ";
  protected final String TEXT_455 = ";";
  protected final String TEXT_456 = NL + "\t\treturn new ";
  protected final String TEXT_457 = "((";
  protected final String TEXT_458 = ".Internal)((";
  protected final String TEXT_459 = ".Internal.Wrapper)get";
  protected final String TEXT_460 = "()).featureMap().";
  protected final String TEXT_461 = "list(";
  protected final String TEXT_462 = "));";
  protected final String TEXT_463 = NL + "\t\treturn (";
  protected final String TEXT_464 = ")get";
  protected final String TEXT_465 = "().";
  protected final String TEXT_466 = "list(";
  protected final String TEXT_467 = ");";
  protected final String TEXT_468 = NL + "\t\treturn ((";
  protected final String TEXT_469 = ".Internal.Wrapper)get";
  protected final String TEXT_470 = "()).featureMap().list(";
  protected final String TEXT_471 = ");";
  protected final String TEXT_472 = NL + "\t\treturn get";
  protected final String TEXT_473 = "().list(";
  protected final String TEXT_474 = ");";
  protected final String TEXT_475 = NL + "\t\treturn ";
  protected final String TEXT_476 = "(";
  protected final String TEXT_477 = "(";
  protected final String TEXT_478 = ")";
  protected final String TEXT_479 = "((";
  protected final String TEXT_480 = ".Internal.Wrapper)get";
  protected final String TEXT_481 = "()).featureMap().get(";
  protected final String TEXT_482 = ", true)";
  protected final String TEXT_483 = ").";
  protected final String TEXT_484 = "()";
  protected final String TEXT_485 = ";";
  protected final String TEXT_486 = NL + "\t\treturn ";
  protected final String TEXT_487 = "(";
  protected final String TEXT_488 = "(";
  protected final String TEXT_489 = ")";
  protected final String TEXT_490 = "get";
  protected final String TEXT_491 = "().get(";
  protected final String TEXT_492 = ", true)";
  protected final String TEXT_493 = ").";
  protected final String TEXT_494 = "()";
  protected final String TEXT_495 = ";";
  protected final String TEXT_496 = NL + "\t\t";
  protected final String TEXT_497 = NL + "\t\t";
  protected final String TEXT_498 = NL + "\t\t// TODO: implement this method to return the '";
  protected final String TEXT_499 = "' ";
  protected final String TEXT_500 = NL + "\t\t// Ensure that you remove @generated or mark it @generated NOT";
  protected final String TEXT_501 = NL + "\t\t// The list is expected to implement org.eclipse.emf.ecore.util.InternalEList and org.eclipse.emf.ecore.EStructuralFeature.Setting" + NL + "\t\t// so it's likely that an appropriate subclass of org.eclipse.emf.ecore.util.";
  protected final String TEXT_502 = "EcoreEMap";
  protected final String TEXT_503 = "BasicFeatureMap";
  protected final String TEXT_504 = "EcoreEList";
  protected final String TEXT_505 = " should be used.";
  protected final String TEXT_506 = NL + "\t\tthrow new UnsupportedOperationException();";
  protected final String TEXT_507 = NL + "\t}" + NL;
  protected final String TEXT_508 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */";
  protected final String TEXT_509 = NL + "\tpublic ";
  protected final String TEXT_510 = " basicGet";
  protected final String TEXT_511 = "()" + NL + "\t{";
  protected final String TEXT_512 = NL + "\t\treturn (";
  protected final String TEXT_513 = ")eDynamicGet(";
  protected final String TEXT_514 = ", ";
  protected final String TEXT_515 = ", false, ";
  protected final String TEXT_516 = ");";
  protected final String TEXT_517 = NL + "\t\treturn ";
  protected final String TEXT_518 = "(";
  protected final String TEXT_519 = "(";
  protected final String TEXT_520 = ")";
  protected final String TEXT_521 = "__ESETTING_DELEGATE.dynamicGet(this, null, 0, false, false)";
  protected final String TEXT_522 = ").";
  protected final String TEXT_523 = "()";
  protected final String TEXT_524 = ";";
  protected final String TEXT_525 = NL + "\t\tif (eContainerFeatureID() != ";
  protected final String TEXT_526 = ") return null;" + NL + "\t\treturn (";
  protected final String TEXT_527 = ")eInternalContainer();";
  protected final String TEXT_528 = NL + "\t\treturn (";
  protected final String TEXT_529 = ")eVirtualGet(";
  protected final String TEXT_530 = ");";
  protected final String TEXT_531 = NL + "\t\treturn ";
  protected final String TEXT_532 = ";";
  protected final String TEXT_533 = NL + "\t\treturn (";
  protected final String TEXT_534 = ")((";
  protected final String TEXT_535 = ".Internal.Wrapper)get";
  protected final String TEXT_536 = "()).featureMap().get(";
  protected final String TEXT_537 = ", false);";
  protected final String TEXT_538 = NL + "\t\treturn (";
  protected final String TEXT_539 = ")get";
  protected final String TEXT_540 = "().get(";
  protected final String TEXT_541 = ", false);";
  protected final String TEXT_542 = NL + "\t\t// TODO: implement this method to return the '";
  protected final String TEXT_543 = "' ";
  protected final String TEXT_544 = NL + "\t\t// -> do not perform proxy resolution" + NL + "\t\t// Ensure that you remove @generated or mark it @generated NOT" + NL + "\t\tthrow new UnsupportedOperationException();";
  protected final String TEXT_545 = NL + "\t}" + NL;
  protected final String TEXT_546 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */";
  protected final String TEXT_547 = NL + "\tpublic ";
  protected final String TEXT_548 = " basicSet";
  protected final String TEXT_549 = "(";
  protected final String TEXT_550 = " new";
  protected final String TEXT_551 = ", ";
  protected final String TEXT_552 = " msgs)" + NL + "\t{";
  protected final String TEXT_553 = NL + "\t\tmsgs = eBasicSetContainer((";
  protected final String TEXT_554 = ")new";
  protected final String TEXT_555 = ", ";
  protected final String TEXT_556 = ", msgs);";
  protected final String TEXT_557 = NL + "\t\treturn msgs;";
  protected final String TEXT_558 = NL + "\t\tmsgs = eDynamicInverseAdd((";
  protected final String TEXT_559 = ")new";
  protected final String TEXT_560 = ", ";
  protected final String TEXT_561 = ", msgs);";
  protected final String TEXT_562 = NL + "\t\treturn msgs;";
  protected final String TEXT_563 = NL + "\t\tObject old";
  protected final String TEXT_564 = " = eVirtualSet(";
  protected final String TEXT_565 = ", new";
  protected final String TEXT_566 = ");";
  protected final String TEXT_567 = NL + "\t\t";
  protected final String TEXT_568 = " old";
  protected final String TEXT_569 = " = ";
  protected final String TEXT_570 = ";" + NL + "\t\t";
  protected final String TEXT_571 = " = new";
  protected final String TEXT_572 = ";";
  protected final String TEXT_573 = NL + "\t\tboolean isSetChange = old";
  protected final String TEXT_574 = " == EVIRTUAL_NO_VALUE;";
  protected final String TEXT_575 = NL + "\t\tboolean old";
  protected final String TEXT_576 = "ESet = (";
  protected final String TEXT_577 = " & ";
  protected final String TEXT_578 = "_ESETFLAG) != 0;";
  protected final String TEXT_579 = NL + "\t\t";
  protected final String TEXT_580 = " |= ";
  protected final String TEXT_581 = "_ESETFLAG;";
  protected final String TEXT_582 = NL + "\t\tboolean old";
  protected final String TEXT_583 = "ESet = ";
  protected final String TEXT_584 = "ESet;";
  protected final String TEXT_585 = NL + "\t\t";
  protected final String TEXT_586 = "ESet = true;";
  protected final String TEXT_587 = NL + "\t\tif (eNotificationRequired())" + NL + "\t\t{";
  protected final String TEXT_588 = NL + "\t\t\t";
  protected final String TEXT_589 = " notification = new ";
  protected final String TEXT_590 = "(this, ";
  protected final String TEXT_591 = ".SET, ";
  protected final String TEXT_592 = ", ";
  protected final String TEXT_593 = "isSetChange ? null : old";
  protected final String TEXT_594 = "old";
  protected final String TEXT_595 = ", new";
  protected final String TEXT_596 = ", ";
  protected final String TEXT_597 = "isSetChange";
  protected final String TEXT_598 = "!old";
  protected final String TEXT_599 = "ESet";
  protected final String TEXT_600 = ");";
  protected final String TEXT_601 = NL + "\t\t\t";
  protected final String TEXT_602 = " notification = new ";
  protected final String TEXT_603 = "(this, ";
  protected final String TEXT_604 = ".SET, ";
  protected final String TEXT_605 = ", ";
  protected final String TEXT_606 = "old";
  protected final String TEXT_607 = " == EVIRTUAL_NO_VALUE ? null : old";
  protected final String TEXT_608 = "old";
  protected final String TEXT_609 = ", new";
  protected final String TEXT_610 = ");";
  protected final String TEXT_611 = NL + "\t\t\tif (msgs == null) msgs = notification; else msgs.add(notification);" + NL + "\t\t}";
  protected final String TEXT_612 = NL + "\t\treturn msgs;";
  protected final String TEXT_613 = NL + "\t\treturn ((";
  protected final String TEXT_614 = ".Internal)((";
  protected final String TEXT_615 = ".Internal.Wrapper)get";
  protected final String TEXT_616 = "()).featureMap()).basicAdd(";
  protected final String TEXT_617 = ", new";
  protected final String TEXT_618 = ", msgs);";
  protected final String TEXT_619 = NL + "\t\treturn ((";
  protected final String TEXT_620 = ".Internal)get";
  protected final String TEXT_621 = "()).basicAdd(";
  protected final String TEXT_622 = ", new";
  protected final String TEXT_623 = ", msgs);";
  protected final String TEXT_624 = NL + "\t\t// TODO: implement this method to set the contained '";
  protected final String TEXT_625 = "' ";
  protected final String TEXT_626 = NL + "\t\t// -> this method is automatically invoked to keep the containment relationship in synch" + NL + "\t\t// -> do not modify other features" + NL + "\t\t// -> return msgs, after adding any generated Notification to it (if it is null, a NotificationChain object must be created first)" + NL + "\t\t// Ensure that you remove @generated or mark it @generated NOT" + NL + "\t\tthrow new UnsupportedOperationException();";
  protected final String TEXT_627 = NL + "\t}" + NL;
  protected final String TEXT_628 = NL + "\t/**" + NL + "\t * Sets the value of the '{@link ";
  protected final String TEXT_629 = "#";
  protected final String TEXT_630 = " <em>";
  protected final String TEXT_631 = "</em>}' ";
  protected final String TEXT_632 = ".";
  protected final String TEXT_633 = NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @param value the new value of the '<em>";
  protected final String TEXT_634 = "</em>' ";
  protected final String TEXT_635 = ".";
  protected final String TEXT_636 = NL + "\t * @see ";
  protected final String TEXT_637 = NL + "\t * @see #isSet";
  protected final String TEXT_638 = "()";
  protected final String TEXT_639 = NL + "\t * @see #unset";
  protected final String TEXT_640 = "()";
  protected final String TEXT_641 = NL + "\t * @see #";
  protected final String TEXT_642 = "()" + NL + "\t * @generated" + NL + "\t */";
  protected final String TEXT_643 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */";
  protected final String TEXT_644 = NL + "\tvoid set";
  protected final String TEXT_645 = "(";
  protected final String TEXT_646 = " value);" + NL;
  protected final String TEXT_647 = NL + "\tpublic void set";
  protected final String TEXT_648 = "_";
  protected final String TEXT_649 = "(";
  protected final String TEXT_650 = " ";
  protected final String TEXT_651 = ")" + NL + "\t{";
  protected final String TEXT_652 = NL + "\t\teDynamicSet(";
  protected final String TEXT_653 = ", ";
  protected final String TEXT_654 = ", ";
  protected final String TEXT_655 = "new ";
  protected final String TEXT_656 = "(";
  protected final String TEXT_657 = "new";
  protected final String TEXT_658 = ")";
  protected final String TEXT_659 = ");";
  protected final String TEXT_660 = NL + "\t\teSet(";
  protected final String TEXT_661 = ", ";
  protected final String TEXT_662 = "new ";
  protected final String TEXT_663 = "(";
  protected final String TEXT_664 = "new";
  protected final String TEXT_665 = ")";
  protected final String TEXT_666 = ");";
  protected final String TEXT_667 = NL + "\t\t";
  protected final String TEXT_668 = "__ESETTING_DELEGATE.dynamicSet(this, null, 0, ";
  protected final String TEXT_669 = "new ";
  protected final String TEXT_670 = "(";
  protected final String TEXT_671 = "new";
  protected final String TEXT_672 = ")";
  protected final String TEXT_673 = ");";
  protected final String TEXT_674 = NL + "\t\tif (new";
  protected final String TEXT_675 = " != eInternalContainer() || (eContainerFeatureID() != ";
  protected final String TEXT_676 = " && new";
  protected final String TEXT_677 = " != null))" + NL + "\t\t{" + NL + "\t\t\tif (";
  protected final String TEXT_678 = ".isAncestor(this, ";
  protected final String TEXT_679 = "new";
  protected final String TEXT_680 = "))" + NL + "\t\t\t\tthrow new ";
  protected final String TEXT_681 = "(\"Recursive containment not allowed for \" + toString());";
  protected final String TEXT_682 = NL + "\t\t\t";
  protected final String TEXT_683 = " msgs = null;" + NL + "\t\t\tif (eInternalContainer() != null)" + NL + "\t\t\t\tmsgs = eBasicRemoveFromContainer(msgs);" + NL + "\t\t\tif (new";
  protected final String TEXT_684 = " != null)" + NL + "\t\t\t\tmsgs = ((";
  protected final String TEXT_685 = ")new";
  protected final String TEXT_686 = ").eInverseAdd(this, ";
  protected final String TEXT_687 = ", ";
  protected final String TEXT_688 = ".class, msgs);" + NL + "\t\t\tmsgs = basicSet";
  protected final String TEXT_689 = "(";
  protected final String TEXT_690 = "new";
  protected final String TEXT_691 = ", msgs);" + NL + "\t\t\tif (msgs != null) msgs.dispatch();" + NL + "\t\t}";
  protected final String TEXT_692 = NL + "\t\telse if (eNotificationRequired())" + NL + "\t\t\teNotify(new ";
  protected final String TEXT_693 = "(this, ";
  protected final String TEXT_694 = ".SET, ";
  protected final String TEXT_695 = ", new";
  protected final String TEXT_696 = ", new";
  protected final String TEXT_697 = "));";
  protected final String TEXT_698 = NL + "\t\t";
  protected final String TEXT_699 = " ";
  protected final String TEXT_700 = " = (";
  protected final String TEXT_701 = ")eVirtualGet(";
  protected final String TEXT_702 = ");";
  protected final String TEXT_703 = NL + "\t\tif (new";
  protected final String TEXT_704 = " != ";
  protected final String TEXT_705 = ")" + NL + "\t\t{" + NL + "\t\t\t";
  protected final String TEXT_706 = " msgs = null;" + NL + "\t\t\tif (";
  protected final String TEXT_707 = " != null)";
  protected final String TEXT_708 = NL + "\t\t\t\tmsgs = ((";
  protected final String TEXT_709 = ")";
  protected final String TEXT_710 = ").eInverseRemove(this, EOPPOSITE_FEATURE_BASE - ";
  protected final String TEXT_711 = ", null, msgs);" + NL + "\t\t\tif (new";
  protected final String TEXT_712 = " != null)" + NL + "\t\t\t\tmsgs = ((";
  protected final String TEXT_713 = ")new";
  protected final String TEXT_714 = ").eInverseAdd(this, EOPPOSITE_FEATURE_BASE - ";
  protected final String TEXT_715 = ", null, msgs);";
  protected final String TEXT_716 = NL + "\t\t\t\tmsgs = ((";
  protected final String TEXT_717 = ")";
  protected final String TEXT_718 = ").eInverseRemove(this, ";
  protected final String TEXT_719 = ", ";
  protected final String TEXT_720 = ".class, msgs);" + NL + "\t\t\tif (new";
  protected final String TEXT_721 = " != null)" + NL + "\t\t\t\tmsgs = ((";
  protected final String TEXT_722 = ")new";
  protected final String TEXT_723 = ").eInverseAdd(this, ";
  protected final String TEXT_724 = ", ";
  protected final String TEXT_725 = ".class, msgs);";
  protected final String TEXT_726 = NL + "\t\t\tmsgs = basicSet";
  protected final String TEXT_727 = "(";
  protected final String TEXT_728 = "new";
  protected final String TEXT_729 = ", msgs);" + NL + "\t\t\tif (msgs != null) msgs.dispatch();" + NL + "\t\t}";
  protected final String TEXT_730 = NL + "\t\telse" + NL + "\t\t{";
  protected final String TEXT_731 = NL + "\t\t\tboolean old";
  protected final String TEXT_732 = "ESet = eVirtualIsSet(";
  protected final String TEXT_733 = ");";
  protected final String TEXT_734 = NL + "\t\t\tboolean old";
  protected final String TEXT_735 = "ESet = (";
  protected final String TEXT_736 = " & ";
  protected final String TEXT_737 = "_ESETFLAG) != 0;";
  protected final String TEXT_738 = NL + "\t\t\t";
  protected final String TEXT_739 = " |= ";
  protected final String TEXT_740 = "_ESETFLAG;";
  protected final String TEXT_741 = NL + "\t\t\tboolean old";
  protected final String TEXT_742 = "ESet = ";
  protected final String TEXT_743 = "ESet;";
  protected final String TEXT_744 = NL + "\t\t\t";
  protected final String TEXT_745 = "ESet = true;";
  protected final String TEXT_746 = NL + "\t\t\tif (eNotificationRequired())" + NL + "\t\t\t\teNotify(new ";
  protected final String TEXT_747 = "(this, ";
  protected final String TEXT_748 = ".SET, ";
  protected final String TEXT_749 = ", new";
  protected final String TEXT_750 = ", new";
  protected final String TEXT_751 = ", !old";
  protected final String TEXT_752 = "ESet));";
  protected final String TEXT_753 = NL + "\t\t}";
  protected final String TEXT_754 = NL + "\t\telse if (eNotificationRequired())" + NL + "\t\t\teNotify(new ";
  protected final String TEXT_755 = "(this, ";
  protected final String TEXT_756 = ".SET, ";
  protected final String TEXT_757 = ", new";
  protected final String TEXT_758 = ", new";
  protected final String TEXT_759 = "));";
  protected final String TEXT_760 = NL + "\t\t";
  protected final String TEXT_761 = " old";
  protected final String TEXT_762 = " = (";
  protected final String TEXT_763 = " & ";
  protected final String TEXT_764 = "_EFLAG) != 0;";
  protected final String TEXT_765 = NL + "\t\t";
  protected final String TEXT_766 = " old";
  protected final String TEXT_767 = " = ";
  protected final String TEXT_768 = "_EFLAG_VALUES[(";
  protected final String TEXT_769 = " & ";
  protected final String TEXT_770 = "_EFLAG) >>> ";
  protected final String TEXT_771 = "_EFLAG_OFFSET];";
  protected final String TEXT_772 = NL + "\t\tif (new";
  protected final String TEXT_773 = ") ";
  protected final String TEXT_774 = " |= ";
  protected final String TEXT_775 = "_EFLAG; else ";
  protected final String TEXT_776 = " &= ~";
  protected final String TEXT_777 = "_EFLAG;";
  protected final String TEXT_778 = NL + "\t\tif (new";
  protected final String TEXT_779 = " == null) new";
  protected final String TEXT_780 = " = ";
  protected final String TEXT_781 = "_EDEFAULT;" + NL + "\t\t";
  protected final String TEXT_782 = " = ";
  protected final String TEXT_783 = " & ~";
  protected final String TEXT_784 = "_EFLAG | ";
  protected final String TEXT_785 = "new";
  protected final String TEXT_786 = ".ordinal()";
  protected final String TEXT_787 = ".VALUES.indexOf(new";
  protected final String TEXT_788 = ")";
  protected final String TEXT_789 = " << ";
  protected final String TEXT_790 = "_EFLAG_OFFSET;";
  protected final String TEXT_791 = NL + "\t\t";
  protected final String TEXT_792 = " old";
  protected final String TEXT_793 = " = ";
  protected final String TEXT_794 = ";";
  protected final String TEXT_795 = NL + "\t\t";
  protected final String TEXT_796 = " ";
  protected final String TEXT_797 = " = new";
  protected final String TEXT_798 = " == null ? ";
  protected final String TEXT_799 = " : new";
  protected final String TEXT_800 = ";";
  protected final String TEXT_801 = NL + "\t\t";
  protected final String TEXT_802 = " = new";
  protected final String TEXT_803 = " == null ? ";
  protected final String TEXT_804 = " : new";
  protected final String TEXT_805 = ";";
  protected final String TEXT_806 = NL + "\t\t";
  protected final String TEXT_807 = " ";
  protected final String TEXT_808 = " = ";
  protected final String TEXT_809 = "new";
  protected final String TEXT_810 = ";";
  protected final String TEXT_811 = NL + "\t\t";
  protected final String TEXT_812 = " = ";
  protected final String TEXT_813 = "new";
  protected final String TEXT_814 = ";";
  protected final String TEXT_815 = NL + "\t\tObject old";
  protected final String TEXT_816 = " = eVirtualSet(";
  protected final String TEXT_817 = ", ";
  protected final String TEXT_818 = ");";
  protected final String TEXT_819 = NL + "\t\tboolean isSetChange = old";
  protected final String TEXT_820 = " == EVIRTUAL_NO_VALUE;";
  protected final String TEXT_821 = NL + "\t\tboolean old";
  protected final String TEXT_822 = "ESet = (";
  protected final String TEXT_823 = " & ";
  protected final String TEXT_824 = "_ESETFLAG) != 0;";
  protected final String TEXT_825 = NL + "\t\t";
  protected final String TEXT_826 = " |= ";
  protected final String TEXT_827 = "_ESETFLAG;";
  protected final String TEXT_828 = NL + "\t\tboolean old";
  protected final String TEXT_829 = "ESet = ";
  protected final String TEXT_830 = "ESet;";
  protected final String TEXT_831 = NL + "\t\t";
  protected final String TEXT_832 = "ESet = true;";
  protected final String TEXT_833 = NL + "\t\tif (eNotificationRequired())" + NL + "\t\t\teNotify(new ";
  protected final String TEXT_834 = "(this, ";
  protected final String TEXT_835 = ".SET, ";
  protected final String TEXT_836 = ", ";
  protected final String TEXT_837 = "isSetChange ? ";
  protected final String TEXT_838 = " : old";
  protected final String TEXT_839 = "old";
  protected final String TEXT_840 = ", ";
  protected final String TEXT_841 = "new";
  protected final String TEXT_842 = ", ";
  protected final String TEXT_843 = "isSetChange";
  protected final String TEXT_844 = "!old";
  protected final String TEXT_845 = "ESet";
  protected final String TEXT_846 = "));";
  protected final String TEXT_847 = NL + "\t\tif (eNotificationRequired())" + NL + "\t\t\teNotify(new ";
  protected final String TEXT_848 = "(this, ";
  protected final String TEXT_849 = ".SET, ";
  protected final String TEXT_850 = ", ";
  protected final String TEXT_851 = "old";
  protected final String TEXT_852 = " == EVIRTUAL_NO_VALUE ? ";
  protected final String TEXT_853 = " : old";
  protected final String TEXT_854 = "old";
  protected final String TEXT_855 = ", ";
  protected final String TEXT_856 = "new";
  protected final String TEXT_857 = "));";
  protected final String TEXT_858 = NL + "\t\t((";
  protected final String TEXT_859 = ".Internal)((";
  protected final String TEXT_860 = ".Internal.Wrapper)get";
  protected final String TEXT_861 = "()).featureMap()).set(";
  protected final String TEXT_862 = ", ";
  protected final String TEXT_863 = "new ";
  protected final String TEXT_864 = "(";
  protected final String TEXT_865 = "new";
  protected final String TEXT_866 = ")";
  protected final String TEXT_867 = ");";
  protected final String TEXT_868 = NL + "\t\t((";
  protected final String TEXT_869 = ".Internal)get";
  protected final String TEXT_870 = "()).set(";
  protected final String TEXT_871 = ", ";
  protected final String TEXT_872 = "new ";
  protected final String TEXT_873 = "(";
  protected final String TEXT_874 = "new";
  protected final String TEXT_875 = ")";
  protected final String TEXT_876 = ");";
  protected final String TEXT_877 = NL + "\t\t";
  protected final String TEXT_878 = NL + "\t\t// TODO: implement this method to set the '";
  protected final String TEXT_879 = "' ";
  protected final String TEXT_880 = NL + "\t\t// Ensure that you remove @generated or mark it @generated NOT" + NL + "\t\tthrow new UnsupportedOperationException();";
  protected final String TEXT_881 = NL + "\t}" + NL;
  protected final String TEXT_882 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */";
  protected final String TEXT_883 = NL + "\tpublic ";
  protected final String TEXT_884 = " basicUnset";
  protected final String TEXT_885 = "(";
  protected final String TEXT_886 = " msgs)" + NL + "\t{";
  protected final String TEXT_887 = NL + "\t\treturn eDynamicInverseRemove((";
  protected final String TEXT_888 = ")";
  protected final String TEXT_889 = "basicGet";
  protected final String TEXT_890 = "(), ";
  protected final String TEXT_891 = ", msgs);";
  protected final String TEXT_892 = "Object old";
  protected final String TEXT_893 = " = ";
  protected final String TEXT_894 = "eVirtualUnset(";
  protected final String TEXT_895 = ");";
  protected final String TEXT_896 = NL + "\t\t";
  protected final String TEXT_897 = " old";
  protected final String TEXT_898 = " = ";
  protected final String TEXT_899 = ";";
  protected final String TEXT_900 = NL + "\t\t";
  protected final String TEXT_901 = " = null;";
  protected final String TEXT_902 = NL + "\t\tboolean isSetChange = old";
  protected final String TEXT_903 = " != EVIRTUAL_NO_VALUE;";
  protected final String TEXT_904 = NL + "\t\tboolean old";
  protected final String TEXT_905 = "ESet = (";
  protected final String TEXT_906 = " & ";
  protected final String TEXT_907 = "_ESETFLAG) != 0;";
  protected final String TEXT_908 = NL + "\t\t";
  protected final String TEXT_909 = " &= ~";
  protected final String TEXT_910 = "_ESETFLAG;";
  protected final String TEXT_911 = NL + "\t\tboolean old";
  protected final String TEXT_912 = "ESet = ";
  protected final String TEXT_913 = "ESet;";
  protected final String TEXT_914 = NL + "\t\t";
  protected final String TEXT_915 = "ESet = false;";
  protected final String TEXT_916 = NL + "\t\tif (eNotificationRequired())" + NL + "\t\t{" + NL + "\t\t\t";
  protected final String TEXT_917 = " notification = new ";
  protected final String TEXT_918 = "(this, ";
  protected final String TEXT_919 = ".UNSET, ";
  protected final String TEXT_920 = ", ";
  protected final String TEXT_921 = "isSetChange ? old";
  protected final String TEXT_922 = " : null";
  protected final String TEXT_923 = "old";
  protected final String TEXT_924 = ", null, ";
  protected final String TEXT_925 = "isSetChange";
  protected final String TEXT_926 = "old";
  protected final String TEXT_927 = "ESet";
  protected final String TEXT_928 = ");" + NL + "\t\t\tif (msgs == null) msgs = notification; else msgs.add(notification);" + NL + "\t\t}" + NL + "\t\treturn msgs;";
  protected final String TEXT_929 = NL + "\t\t// TODO: implement this method to unset the contained '";
  protected final String TEXT_930 = "' ";
  protected final String TEXT_931 = NL + "\t\t// -> this method is automatically invoked to keep the containment relationship in synch" + NL + "\t\t// -> do not modify other features" + NL + "\t\t// -> return msgs, after adding any generated Notification to it (if it is null, a NotificationChain object must be created first)" + NL + "\t\t// Ensure that you remove @generated or mark it @generated NOT" + NL + "\t\tthrow new UnsupportedOperationException();";
  protected final String TEXT_932 = NL + "\t}" + NL;
  protected final String TEXT_933 = NL + "\t/**" + NL + "\t * Unsets the value of the '{@link ";
  protected final String TEXT_934 = "#";
  protected final String TEXT_935 = " <em>";
  protected final String TEXT_936 = "</em>}' ";
  protected final String TEXT_937 = ".";
  protected final String TEXT_938 = NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->";
  protected final String TEXT_939 = NL + "\t * @see #isSet";
  protected final String TEXT_940 = "()";
  protected final String TEXT_941 = NL + "\t * @see #";
  protected final String TEXT_942 = "()";
  protected final String TEXT_943 = NL + "\t * @see #set";
  protected final String TEXT_944 = "(";
  protected final String TEXT_945 = ")";
  protected final String TEXT_946 = NL + "\t * @generated" + NL + "\t */";
  protected final String TEXT_947 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */";
  protected final String TEXT_948 = NL + "\tvoid unset";
  protected final String TEXT_949 = "();" + NL;
  protected final String TEXT_950 = NL + "\tpublic void unset";
  protected final String TEXT_951 = "_";
  protected final String TEXT_952 = "()" + NL + "\t{";
  protected final String TEXT_953 = NL + "\t\teDynamicUnset(";
  protected final String TEXT_954 = ", ";
  protected final String TEXT_955 = ");";
  protected final String TEXT_956 = NL + "\t\teUnset(";
  protected final String TEXT_957 = ");";
  protected final String TEXT_958 = NL + "\t\t";
  protected final String TEXT_959 = "__ESETTING_DELEGATE.dynamicUnset(this, null, 0);";
  protected final String TEXT_960 = NL + "\t\t";
  protected final String TEXT_961 = " ";
  protected final String TEXT_962 = " = (";
  protected final String TEXT_963 = ")eVirtualGet(";
  protected final String TEXT_964 = ");";
  protected final String TEXT_965 = NL + "\t\tif (";
  protected final String TEXT_966 = " != null) ((";
  protected final String TEXT_967 = ".Unsettable";
  protected final String TEXT_968 = ")";
  protected final String TEXT_969 = ").unset();";
  protected final String TEXT_970 = NL + "\t\t";
  protected final String TEXT_971 = " ";
  protected final String TEXT_972 = " = (";
  protected final String TEXT_973 = ")eVirtualGet(";
  protected final String TEXT_974 = ");";
  protected final String TEXT_975 = NL + "\t\tif (";
  protected final String TEXT_976 = " != null)" + NL + "\t\t{" + NL + "\t\t\t";
  protected final String TEXT_977 = " msgs = null;";
  protected final String TEXT_978 = NL + "\t\t\tmsgs = ((";
  protected final String TEXT_979 = ")";
  protected final String TEXT_980 = ").eInverseRemove(this, EOPPOSITE_FEATURE_BASE - ";
  protected final String TEXT_981 = ", null, msgs);";
  protected final String TEXT_982 = NL + "\t\t\tmsgs = ((";
  protected final String TEXT_983 = ")";
  protected final String TEXT_984 = ").eInverseRemove(this, ";
  protected final String TEXT_985 = ", ";
  protected final String TEXT_986 = ".class, msgs);";
  protected final String TEXT_987 = NL + "\t\t\tmsgs = basicUnset";
  protected final String TEXT_988 = "(msgs);" + NL + "\t\t\tif (msgs != null) msgs.dispatch();" + NL + "\t\t}" + NL + "\t\telse" + NL + "\t\t{";
  protected final String TEXT_989 = NL + "\t\t\tboolean old";
  protected final String TEXT_990 = "ESet = eVirtualIsSet(";
  protected final String TEXT_991 = ");";
  protected final String TEXT_992 = NL + "\t\t\tboolean old";
  protected final String TEXT_993 = "ESet = (";
  protected final String TEXT_994 = " & ";
  protected final String TEXT_995 = "_ESETFLAG) != 0;";
  protected final String TEXT_996 = NL + "\t\t\t";
  protected final String TEXT_997 = " &= ~";
  protected final String TEXT_998 = "_ESETFLAG;";
  protected final String TEXT_999 = NL + "\t\t\tboolean old";
  protected final String TEXT_1000 = "ESet = ";
  protected final String TEXT_1001 = "ESet;";
  protected final String TEXT_1002 = NL + "\t\t\t";
  protected final String TEXT_1003 = "ESet = false;";
  protected final String TEXT_1004 = NL + "\t\t\tif (eNotificationRequired())" + NL + "\t\t\t\teNotify(new ";
  protected final String TEXT_1005 = "(this, ";
  protected final String TEXT_1006 = ".UNSET, ";
  protected final String TEXT_1007 = ", null, null, old";
  protected final String TEXT_1008 = "ESet));";
  protected final String TEXT_1009 = NL + "\t\t}";
  protected final String TEXT_1010 = NL + "\t\t";
  protected final String TEXT_1011 = " old";
  protected final String TEXT_1012 = " = (";
  protected final String TEXT_1013 = " & ";
  protected final String TEXT_1014 = "_EFLAG) != 0;";
  protected final String TEXT_1015 = NL + "\t\t";
  protected final String TEXT_1016 = " old";
  protected final String TEXT_1017 = " = ";
  protected final String TEXT_1018 = "_EFLAG_VALUES[(";
  protected final String TEXT_1019 = " & ";
  protected final String TEXT_1020 = "_EFLAG) >>> ";
  protected final String TEXT_1021 = "_EFLAG_OFFSET];";
  protected final String TEXT_1022 = NL + "\t\tObject old";
  protected final String TEXT_1023 = " = eVirtualUnset(";
  protected final String TEXT_1024 = ");";
  protected final String TEXT_1025 = NL + "\t\t";
  protected final String TEXT_1026 = " old";
  protected final String TEXT_1027 = " = ";
  protected final String TEXT_1028 = ";";
  protected final String TEXT_1029 = NL + "\t\tboolean isSetChange = old";
  protected final String TEXT_1030 = " != EVIRTUAL_NO_VALUE;";
  protected final String TEXT_1031 = NL + "\t\tboolean old";
  protected final String TEXT_1032 = "ESet = (";
  protected final String TEXT_1033 = " & ";
  protected final String TEXT_1034 = "_ESETFLAG) != 0;";
  protected final String TEXT_1035 = NL + "\t\tboolean old";
  protected final String TEXT_1036 = "ESet = ";
  protected final String TEXT_1037 = "ESet;";
  protected final String TEXT_1038 = NL + "\t\t";
  protected final String TEXT_1039 = " = null;";
  protected final String TEXT_1040 = NL + "\t\t";
  protected final String TEXT_1041 = " &= ~";
  protected final String TEXT_1042 = "_ESETFLAG;";
  protected final String TEXT_1043 = NL + "\t\t";
  protected final String TEXT_1044 = "ESet = false;";
  protected final String TEXT_1045 = NL + "\t\tif (eNotificationRequired())" + NL + "\t\t\teNotify(new ";
  protected final String TEXT_1046 = "(this, ";
  protected final String TEXT_1047 = ".UNSET, ";
  protected final String TEXT_1048 = ", ";
  protected final String TEXT_1049 = "isSetChange ? old";
  protected final String TEXT_1050 = " : null";
  protected final String TEXT_1051 = "old";
  protected final String TEXT_1052 = ", null, ";
  protected final String TEXT_1053 = "isSetChange";
  protected final String TEXT_1054 = "old";
  protected final String TEXT_1055 = "ESet";
  protected final String TEXT_1056 = "));";
  protected final String TEXT_1057 = NL + "\t\tif (";
  protected final String TEXT_1058 = ") ";
  protected final String TEXT_1059 = " |= ";
  protected final String TEXT_1060 = "_EFLAG; else ";
  protected final String TEXT_1061 = " &= ~";
  protected final String TEXT_1062 = "_EFLAG;";
  protected final String TEXT_1063 = NL + "\t\t";
  protected final String TEXT_1064 = " = ";
  protected final String TEXT_1065 = " & ~";
  protected final String TEXT_1066 = "_EFLAG | ";
  protected final String TEXT_1067 = "_EFLAG_DEFAULT;";
  protected final String TEXT_1068 = NL + "\t\t";
  protected final String TEXT_1069 = " = ";
  protected final String TEXT_1070 = ";";
  protected final String TEXT_1071 = NL + "\t\t";
  protected final String TEXT_1072 = " &= ~";
  protected final String TEXT_1073 = "_ESETFLAG;";
  protected final String TEXT_1074 = NL + "\t\t";
  protected final String TEXT_1075 = "ESet = false;";
  protected final String TEXT_1076 = NL + "\t\tif (eNotificationRequired())" + NL + "\t\t\teNotify(new ";
  protected final String TEXT_1077 = "(this, ";
  protected final String TEXT_1078 = ".UNSET, ";
  protected final String TEXT_1079 = ", ";
  protected final String TEXT_1080 = "isSetChange ? old";
  protected final String TEXT_1081 = " : ";
  protected final String TEXT_1082 = "old";
  protected final String TEXT_1083 = ", ";
  protected final String TEXT_1084 = ", ";
  protected final String TEXT_1085 = "isSetChange";
  protected final String TEXT_1086 = "old";
  protected final String TEXT_1087 = "ESet";
  protected final String TEXT_1088 = "));";
  protected final String TEXT_1089 = NL + "\t\t((";
  protected final String TEXT_1090 = ".Internal)((";
  protected final String TEXT_1091 = ".Internal.Wrapper)get";
  protected final String TEXT_1092 = "()).featureMap()).clear(";
  protected final String TEXT_1093 = ");";
  protected final String TEXT_1094 = NL + "\t\t((";
  protected final String TEXT_1095 = ".Internal)get";
  protected final String TEXT_1096 = "()).clear(";
  protected final String TEXT_1097 = ");";
  protected final String TEXT_1098 = NL + "\t\t";
  protected final String TEXT_1099 = NL + "\t\t// TODO: implement this method to unset the '";
  protected final String TEXT_1100 = "' ";
  protected final String TEXT_1101 = NL + "\t\t// Ensure that you remove @generated or mark it @generated NOT" + NL + "\t\tthrow new UnsupportedOperationException();";
  protected final String TEXT_1102 = NL + "\t}" + NL;
  protected final String TEXT_1103 = NL + "\t/**" + NL + "\t * Returns whether the value of the '{@link ";
  protected final String TEXT_1104 = "#";
  protected final String TEXT_1105 = " <em>";
  protected final String TEXT_1106 = "</em>}' ";
  protected final String TEXT_1107 = " is set.";
  protected final String TEXT_1108 = NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @return whether the value of the '<em>";
  protected final String TEXT_1109 = "</em>' ";
  protected final String TEXT_1110 = " is set.";
  protected final String TEXT_1111 = NL + "\t * @see #unset";
  protected final String TEXT_1112 = "()";
  protected final String TEXT_1113 = NL + "\t * @see #";
  protected final String TEXT_1114 = "()";
  protected final String TEXT_1115 = NL + "\t * @see #set";
  protected final String TEXT_1116 = "(";
  protected final String TEXT_1117 = ")";
  protected final String TEXT_1118 = NL + "\t * @generated" + NL + "\t */";
  protected final String TEXT_1119 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */";
  protected final String TEXT_1120 = NL + "\tboolean isSet";
  protected final String TEXT_1121 = "();" + NL;
  protected final String TEXT_1122 = NL + "\tpublic boolean isSet";
  protected final String TEXT_1123 = "_";
  protected final String TEXT_1124 = "()" + NL + "\t{";
  protected final String TEXT_1125 = NL + "\t\treturn eDynamicIsSet(";
  protected final String TEXT_1126 = ", ";
  protected final String TEXT_1127 = ");";
  protected final String TEXT_1128 = NL + "\t\treturn eIsSet(";
  protected final String TEXT_1129 = ");";
  protected final String TEXT_1130 = NL + "\t\treturn ";
  protected final String TEXT_1131 = "__ESETTING_DELEGATE.dynamicIsSet(this, null, 0);";
  protected final String TEXT_1132 = NL + "\t\t";
  protected final String TEXT_1133 = " ";
  protected final String TEXT_1134 = " = (";
  protected final String TEXT_1135 = ")eVirtualGet(";
  protected final String TEXT_1136 = ");";
  protected final String TEXT_1137 = NL + "\t\treturn ";
  protected final String TEXT_1138 = " != null && ((";
  protected final String TEXT_1139 = ".Unsettable";
  protected final String TEXT_1140 = ")";
  protected final String TEXT_1141 = ").isSet();";
  protected final String TEXT_1142 = NL + "\t\treturn eVirtualIsSet(";
  protected final String TEXT_1143 = ");";
  protected final String TEXT_1144 = NL + "\t\treturn (";
  protected final String TEXT_1145 = " & ";
  protected final String TEXT_1146 = "_ESETFLAG) != 0;";
  protected final String TEXT_1147 = NL + "\t\treturn ";
  protected final String TEXT_1148 = "ESet;";
  protected final String TEXT_1149 = NL + "\t\treturn !((";
  protected final String TEXT_1150 = ".Internal)((";
  protected final String TEXT_1151 = ".Internal.Wrapper)get";
  protected final String TEXT_1152 = "()).featureMap()).isEmpty(";
  protected final String TEXT_1153 = ");";
  protected final String TEXT_1154 = NL + "\t\treturn !((";
  protected final String TEXT_1155 = ".Internal)get";
  protected final String TEXT_1156 = "()).isEmpty(";
  protected final String TEXT_1157 = ");";
  protected final String TEXT_1158 = NL + "\t\t";
  protected final String TEXT_1159 = NL + "\t\t// TODO: implement this method to return whether the '";
  protected final String TEXT_1160 = "' ";
  protected final String TEXT_1161 = " is set" + NL + "\t\t// Ensure that you remove @generated or mark it @generated NOT" + NL + "\t\tthrow new UnsupportedOperationException();";
  protected final String TEXT_1162 = NL + "\t}" + NL;
  protected final String TEXT_1163 = NL + "\t/**" + NL + "\t * The cached validation expression for the '{@link #";
  protected final String TEXT_1164 = "(";
  protected final String TEXT_1165 = ") <em>";
  protected final String TEXT_1166 = "</em>}' invariant operation." + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @see #";
  protected final String TEXT_1167 = "(";
  protected final String TEXT_1168 = ")" + NL + "\t * @generated" + NL + "\t * @ordered" + NL + "\t */" + NL + "\tprotected static final ";
  protected final String TEXT_1169 = " ";
  protected final String TEXT_1170 = "__EEXPRESSION = \"";
  protected final String TEXT_1171 = "\";";
  protected final String TEXT_1172 = NL;
  protected final String TEXT_1173 = NL + "\t/**" + NL + "\t * The cached invocation delegate for the '{@link #";
  protected final String TEXT_1174 = "(";
  protected final String TEXT_1175 = ") <em>";
  protected final String TEXT_1176 = "</em>}' operation." + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @see #";
  protected final String TEXT_1177 = "(";
  protected final String TEXT_1178 = ")" + NL + "\t * @generated" + NL + "\t * @ordered" + NL + "\t */" + NL + "\tprotected static final ";
  protected final String TEXT_1179 = ".Internal.InvocationDelegate ";
  protected final String TEXT_1180 = "__EINVOCATION_DELEGATE = ((";
  protected final String TEXT_1181 = ".Internal)";
  protected final String TEXT_1182 = ").getInvocationDelegate();" + NL;
  protected final String TEXT_1183 = NL + "\t/**";
  protected final String TEXT_1184 = NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->";
  protected final String TEXT_1185 = NL + "\t * <!-- begin-model-doc -->";
  protected final String TEXT_1186 = NL + "\t * ";
  protected final String TEXT_1187 = NL + "\t * @param ";
  protected final String TEXT_1188 = NL + "\t *   ";
  protected final String TEXT_1189 = NL + "\t * @param ";
  protected final String TEXT_1190 = " ";
  protected final String TEXT_1191 = NL + "\t * <!-- end-model-doc -->";
  protected final String TEXT_1192 = NL + "\t * @model ";
  protected final String TEXT_1193 = NL + "\t *        ";
  protected final String TEXT_1194 = NL + "\t * @model";
  protected final String TEXT_1195 = NL + "\t * @generated" + NL + "\t */";
  protected final String TEXT_1196 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */";
  protected final String TEXT_1197 = NL + "\t";
  protected final String TEXT_1198 = " ";
  protected final String TEXT_1199 = "(";
  protected final String TEXT_1200 = ")";
  protected final String TEXT_1201 = ";" + NL;
  protected final String TEXT_1202 = NL + "\t@SuppressWarnings(\"unchecked\")";
  protected final String TEXT_1203 = NL + "\tpublic ";
  protected final String TEXT_1204 = " ";
  protected final String TEXT_1205 = "(";
  protected final String TEXT_1206 = ")";
  protected final String TEXT_1207 = NL + "\t{";
  protected final String TEXT_1208 = NL + "\t\t";
  protected final String TEXT_1209 = NL + "\t\treturn" + NL + "\t\t\t";
  protected final String TEXT_1210 = ".validate" + NL + "\t\t\t\t(";
  protected final String TEXT_1211 = "," + NL + "\t\t\t\t this," + NL + "\t\t\t\t ";
  protected final String TEXT_1212 = "," + NL + "\t\t\t\t ";
  protected final String TEXT_1213 = "," + NL + "\t\t\t\t \"";
  protected final String TEXT_1214 = "\",";
  protected final String TEXT_1215 = NL + "\t\t\t\t ";
  protected final String TEXT_1216 = "," + NL + "\t\t\t\t ";
  protected final String TEXT_1217 = "__EEXPRESSION," + NL + "\t\t\t\t ";
  protected final String TEXT_1218 = ".ERROR," + NL + "\t\t\t\t ";
  protected final String TEXT_1219 = ".DIAGNOSTIC_SOURCE," + NL + "\t\t\t\t ";
  protected final String TEXT_1220 = ".";
  protected final String TEXT_1221 = ");";
  protected final String TEXT_1222 = NL + "\t\t// TODO: implement this method" + NL + "\t\t// -> specify the condition that violates the invariant" + NL + "\t\t// -> verify the details of the diagnostic, including severity and message" + NL + "\t\t// Ensure that you remove @generated or mark it @generated NOT" + NL + "\t\tif (false)" + NL + "\t\t{" + NL + "\t\t\tif (";
  protected final String TEXT_1223 = " != null)" + NL + "\t\t\t{" + NL + "\t\t\t\t";
  protected final String TEXT_1224 = ".add" + NL + "\t\t\t\t\t(new ";
  protected final String TEXT_1225 = NL + "\t\t\t\t\t\t(";
  protected final String TEXT_1226 = ".ERROR," + NL + "\t\t\t\t\t\t ";
  protected final String TEXT_1227 = ".DIAGNOSTIC_SOURCE," + NL + "\t\t\t\t\t\t ";
  protected final String TEXT_1228 = ".";
  protected final String TEXT_1229 = "," + NL + "\t\t\t\t\t\t ";
  protected final String TEXT_1230 = ".INSTANCE.getString(\"_UI_GenericInvariant_diagnostic\", new Object[] { \"";
  protected final String TEXT_1231 = "\", ";
  protected final String TEXT_1232 = ".getObjectLabel(this, ";
  protected final String TEXT_1233 = ") }),";
  protected final String TEXT_1234 = NL + "\t\t\t\t\t\t new Object [] { this }));" + NL + "\t\t\t}" + NL + "\t\t\treturn false;" + NL + "\t\t}" + NL + "\t\treturn true;";
  protected final String TEXT_1235 = NL + "\t\ttry" + NL + "\t\t{";
  protected final String TEXT_1236 = NL + "\t\t\t";
  protected final String TEXT_1237 = "__EINVOCATION_DELEGATE.dynamicInvoke(this, ";
  protected final String TEXT_1238 = "new ";
  protected final String TEXT_1239 = ".UnmodifiableEList<Object>(";
  protected final String TEXT_1240 = ", ";
  protected final String TEXT_1241 = ")";
  protected final String TEXT_1242 = "null";
  protected final String TEXT_1243 = ");";
  protected final String TEXT_1244 = NL + "\t\t\treturn ";
  protected final String TEXT_1245 = "(";
  protected final String TEXT_1246 = "(";
  protected final String TEXT_1247 = ")";
  protected final String TEXT_1248 = "__EINVOCATION_DELEGATE.dynamicInvoke(this, ";
  protected final String TEXT_1249 = "new ";
  protected final String TEXT_1250 = ".UnmodifiableEList<Object>(";
  protected final String TEXT_1251 = ", ";
  protected final String TEXT_1252 = ")";
  protected final String TEXT_1253 = "null";
  protected final String TEXT_1254 = ")";
  protected final String TEXT_1255 = ").";
  protected final String TEXT_1256 = "()";
  protected final String TEXT_1257 = ";";
  protected final String TEXT_1258 = NL + "\t\t}" + NL + "\t\tcatch (";
  protected final String TEXT_1259 = " ite)" + NL + "\t\t{" + NL + "\t\t\tthrow new ";
  protected final String TEXT_1260 = "(ite);" + NL + "\t\t}";
  protected final String TEXT_1261 = NL + "\t\t// TODO: implement this method" + NL + "\t\t// Ensure that you remove @generated or mark it @generated NOT" + NL + "\t\tthrow new UnsupportedOperationException();";
  protected final String TEXT_1262 = NL + "\t}" + NL;
  protected final String TEXT_1263 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */";
  protected final String TEXT_1264 = NL + "\t@SuppressWarnings(\"unchecked\")";
  protected final String TEXT_1265 = NL + "\t@Override";
  protected final String TEXT_1266 = NL + "\tpublic ";
  protected final String TEXT_1267 = " eInverseAdd(";
  protected final String TEXT_1268 = " otherEnd, int featureID, ";
  protected final String TEXT_1269 = " msgs)" + NL + "\t{" + NL + "\t\tswitch (featureID";
  protected final String TEXT_1270 = ")" + NL + "\t\t{";
  protected final String TEXT_1271 = NL + "\t\t\tcase ";
  protected final String TEXT_1272 = ":";
  protected final String TEXT_1273 = NL + "\t\t\t\treturn ((";
  protected final String TEXT_1274 = "(";
  protected final String TEXT_1275 = ".InternalMapView";
  protected final String TEXT_1276 = ")";
  protected final String TEXT_1277 = "()).eMap()).basicAdd(otherEnd, msgs);";
  protected final String TEXT_1278 = NL + "\t\t\t\treturn (";
  protected final String TEXT_1279 = "()).basicAdd(otherEnd, msgs);";
  protected final String TEXT_1280 = NL + "\t\t\t\tif (eInternalContainer() != null)" + NL + "\t\t\t\t\tmsgs = eBasicRemoveFromContainer(msgs);";
  protected final String TEXT_1281 = NL + "\t\t\t\treturn basicSet";
  protected final String TEXT_1282 = "((";
  protected final String TEXT_1283 = ")otherEnd, msgs);";
  protected final String TEXT_1284 = NL + "\t\t\t\treturn eBasicSetContainer(otherEnd, ";
  protected final String TEXT_1285 = ", msgs);";
  protected final String TEXT_1286 = NL + "\t\t\t\t";
  protected final String TEXT_1287 = " ";
  protected final String TEXT_1288 = " = (";
  protected final String TEXT_1289 = ")eVirtualGet(";
  protected final String TEXT_1290 = ");";
  protected final String TEXT_1291 = NL + "\t\t\t\t";
  protected final String TEXT_1292 = " ";
  protected final String TEXT_1293 = " = ";
  protected final String TEXT_1294 = "basicGet";
  protected final String TEXT_1295 = "();";
  protected final String TEXT_1296 = NL + "\t\t\t\tif (";
  protected final String TEXT_1297 = " != null)";
  protected final String TEXT_1298 = NL + "\t\t\t\t\tmsgs = ((";
  protected final String TEXT_1299 = ")";
  protected final String TEXT_1300 = ").eInverseRemove(this, EOPPOSITE_FEATURE_BASE - ";
  protected final String TEXT_1301 = ", null, msgs);";
  protected final String TEXT_1302 = NL + "\t\t\t\t\tmsgs = ((";
  protected final String TEXT_1303 = ")";
  protected final String TEXT_1304 = ").eInverseRemove(this, ";
  protected final String TEXT_1305 = ", ";
  protected final String TEXT_1306 = ".class, msgs);";
  protected final String TEXT_1307 = NL + "\t\t\t\treturn basicSet";
  protected final String TEXT_1308 = "((";
  protected final String TEXT_1309 = ")otherEnd, msgs);";
  protected final String TEXT_1310 = NL + "\t\t}";
  protected final String TEXT_1311 = NL + "\t\treturn super.eInverseAdd(otherEnd, featureID, msgs);";
  protected final String TEXT_1312 = NL + "\t\treturn eDynamicInverseAdd(otherEnd, featureID, msgs);";
  protected final String TEXT_1313 = NL + "\t}" + NL;
  protected final String TEXT_1314 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */";
  protected final String TEXT_1315 = NL + "\t@Override";
  protected final String TEXT_1316 = NL + "\tpublic ";
  protected final String TEXT_1317 = " eInverseRemove(";
  protected final String TEXT_1318 = " otherEnd, int featureID, ";
  protected final String TEXT_1319 = " msgs)" + NL + "\t{" + NL + "\t\tswitch (featureID";
  protected final String TEXT_1320 = ")" + NL + "\t\t{";
  protected final String TEXT_1321 = NL + "\t\t\tcase ";
  protected final String TEXT_1322 = ":";
  protected final String TEXT_1323 = NL + "\t\t\t\treturn ((";
  protected final String TEXT_1324 = ")((";
  protected final String TEXT_1325 = ".InternalMapView";
  protected final String TEXT_1326 = ")";
  protected final String TEXT_1327 = "()).eMap()).basicRemove(otherEnd, msgs);";
  protected final String TEXT_1328 = NL + "\t\t\t\treturn ((";
  protected final String TEXT_1329 = ")((";
  protected final String TEXT_1330 = ".Internal.Wrapper)";
  protected final String TEXT_1331 = "()).featureMap()).basicRemove(otherEnd, msgs);";
  protected final String TEXT_1332 = NL + "\t\t\t\treturn ((";
  protected final String TEXT_1333 = ")";
  protected final String TEXT_1334 = "()).basicRemove(otherEnd, msgs);";
  protected final String TEXT_1335 = NL + "\t\t\t\treturn eBasicSetContainer(null, ";
  protected final String TEXT_1336 = ", msgs);";
  protected final String TEXT_1337 = NL + "\t\t\t\treturn basicUnset";
  protected final String TEXT_1338 = "(msgs);";
  protected final String TEXT_1339 = NL + "\t\t\t\treturn basicSet";
  protected final String TEXT_1340 = "(null, msgs);";
  protected final String TEXT_1341 = NL + "\t\t}";
  protected final String TEXT_1342 = NL + "\t\treturn super.eInverseRemove(otherEnd, featureID, msgs);";
  protected final String TEXT_1343 = NL + "\t\treturn eDynamicInverseRemove(otherEnd, featureID, msgs);";
  protected final String TEXT_1344 = NL + "\t}" + NL;
  protected final String TEXT_1345 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */";
  protected final String TEXT_1346 = NL + "\t@Override";
  protected final String TEXT_1347 = NL + "\tpublic ";
  protected final String TEXT_1348 = " eBasicRemoveFromContainerFeature(";
  protected final String TEXT_1349 = " msgs)" + NL + "\t{" + NL + "\t\tswitch (eContainerFeatureID()";
  protected final String TEXT_1350 = ")" + NL + "\t\t{";
  protected final String TEXT_1351 = NL + "\t\t\tcase ";
  protected final String TEXT_1352 = ":" + NL + "\t\t\t\treturn eInternalContainer().eInverseRemove(this, ";
  protected final String TEXT_1353 = ", ";
  protected final String TEXT_1354 = ".class, msgs);";
  protected final String TEXT_1355 = NL + "\t\t}";
  protected final String TEXT_1356 = NL + "\t\treturn super.eBasicRemoveFromContainerFeature(msgs);";
  protected final String TEXT_1357 = NL + "\t\treturn eDynamicBasicRemoveFromContainer(msgs);";
  protected final String TEXT_1358 = NL + "\t}" + NL;
  protected final String TEXT_1359 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */";
  protected final String TEXT_1360 = NL + "\t@Override";
  protected final String TEXT_1361 = NL + "\tpublic Object eGet(int featureID, boolean resolve, boolean coreType)" + NL + "\t{" + NL + "\t\tswitch (featureID";
  protected final String TEXT_1362 = ")" + NL + "\t\t{";
  protected final String TEXT_1363 = NL + "\t\t\tcase ";
  protected final String TEXT_1364 = ":";
  protected final String TEXT_1365 = NL + "\t\t\t\treturn ";
  protected final String TEXT_1366 = "();";
  protected final String TEXT_1367 = NL + "\t\t\t\treturn ";
  protected final String TEXT_1368 = "() ? Boolean.TRUE : Boolean.FALSE;";
  protected final String TEXT_1369 = NL + "\t\t\t\treturn new ";
  protected final String TEXT_1370 = "(";
  protected final String TEXT_1371 = "());";
  protected final String TEXT_1372 = NL + "\t\t\t\tif (resolve) return ";
  protected final String TEXT_1373 = "();" + NL + "\t\t\t\treturn basicGet";
  protected final String TEXT_1374 = "();";
  protected final String TEXT_1375 = NL + "\t\t\t\tif (coreType) return ((";
  protected final String TEXT_1376 = ".InternalMapView";
  protected final String TEXT_1377 = ")";
  protected final String TEXT_1378 = "()).eMap();" + NL + "\t\t\t\telse return ";
  protected final String TEXT_1379 = "();";
  protected final String TEXT_1380 = NL + "\t\t\t\tif (coreType) return ";
  protected final String TEXT_1381 = "();" + NL + "\t\t\t\telse return ";
  protected final String TEXT_1382 = "().map();";
  protected final String TEXT_1383 = NL + "\t\t\t\tif (coreType) return ((";
  protected final String TEXT_1384 = ".Internal.Wrapper)";
  protected final String TEXT_1385 = "()).featureMap();" + NL + "\t\t\t\treturn ";
  protected final String TEXT_1386 = "();";
  protected final String TEXT_1387 = NL + "\t\t\t\tif (coreType) return ";
  protected final String TEXT_1388 = "();" + NL + "\t\t\t\treturn ((";
  protected final String TEXT_1389 = ".Internal)";
  protected final String TEXT_1390 = "()).getWrapper();";
  protected final String TEXT_1391 = NL + "\t\t\t\treturn ";
  protected final String TEXT_1392 = "();";
  protected final String TEXT_1393 = NL + "\t\t}";
  protected final String TEXT_1394 = NL + "\t\treturn super.eGet(featureID, resolve, coreType);";
  protected final String TEXT_1395 = NL + "\t\treturn eDynamicGet(featureID, resolve, coreType);";
  protected final String TEXT_1396 = NL + "\t}" + NL;
  protected final String TEXT_1397 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */";
  protected final String TEXT_1398 = NL + "\t@SuppressWarnings(\"unchecked\")";
  protected final String TEXT_1399 = NL + "\t@Override";
  protected final String TEXT_1400 = NL + "\tpublic void eSet(int featureID, Object newValue)" + NL + "\t{" + NL + "\t\tswitch (featureID";
  protected final String TEXT_1401 = ")" + NL + "\t\t{";
  protected final String TEXT_1402 = NL + "\t\t\tcase ";
  protected final String TEXT_1403 = ":";
  protected final String TEXT_1404 = NL + "\t\t\t\t((";
  protected final String TEXT_1405 = ".Internal)((";
  protected final String TEXT_1406 = ".Internal.Wrapper)";
  protected final String TEXT_1407 = "()).featureMap()).set(newValue);";
  protected final String TEXT_1408 = NL + "\t\t\t\t((";
  protected final String TEXT_1409 = ".Internal)";
  protected final String TEXT_1410 = "()).set(newValue);";
  protected final String TEXT_1411 = NL + "\t\t\t\t((";
  protected final String TEXT_1412 = ".Setting)((";
  protected final String TEXT_1413 = ".InternalMapView";
  protected final String TEXT_1414 = ")";
  protected final String TEXT_1415 = "()).eMap()).set(newValue);";
  protected final String TEXT_1416 = NL + "\t\t\t\t((";
  protected final String TEXT_1417 = ".Setting)";
  protected final String TEXT_1418 = "()).set(newValue);";
  protected final String TEXT_1419 = NL + "\t\t\t\t";
  protected final String TEXT_1420 = "().clear();" + NL + "\t\t\t\t";
  protected final String TEXT_1421 = "().addAll((";
  protected final String TEXT_1422 = "<? extends ";
  protected final String TEXT_1423 = ">";
  protected final String TEXT_1424 = ")newValue);";
  protected final String TEXT_1425 = NL + "\t\t\t\tset";
  protected final String TEXT_1426 = "(((";
  protected final String TEXT_1427 = ")newValue).";
  protected final String TEXT_1428 = "());";
  protected final String TEXT_1429 = NL + "\t\t\t\tset";
  protected final String TEXT_1430 = "(";
  protected final String TEXT_1431 = "(";
  protected final String TEXT_1432 = ")";
  protected final String TEXT_1433 = "newValue);";
  protected final String TEXT_1434 = NL + "\t\t\t\treturn;";
  protected final String TEXT_1435 = NL + "\t\t}";
  protected final String TEXT_1436 = NL + "\t\tsuper.eSet(featureID, newValue);";
  protected final String TEXT_1437 = NL + "\t\teDynamicSet(featureID, newValue);";
  protected final String TEXT_1438 = NL + "\t}" + NL;
  protected final String TEXT_1439 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */";
  protected final String TEXT_1440 = NL + "\t@Override";
  protected final String TEXT_1441 = NL + "\tpublic void eUnset(int featureID)" + NL + "\t{" + NL + "\t\tswitch (featureID";
  protected final String TEXT_1442 = ")" + NL + "\t\t{";
  protected final String TEXT_1443 = NL + "\t\t\tcase ";
  protected final String TEXT_1444 = ":";
  protected final String TEXT_1445 = NL + "\t\t\t\t((";
  protected final String TEXT_1446 = ".Internal.Wrapper)";
  protected final String TEXT_1447 = "()).featureMap().clear();";
  protected final String TEXT_1448 = NL + "\t\t\t\t";
  protected final String TEXT_1449 = "().clear();";
  protected final String TEXT_1450 = NL + "\t\t\t\tunset";
  protected final String TEXT_1451 = "();";
  protected final String TEXT_1452 = NL + "\t\t\t\tset";
  protected final String TEXT_1453 = "((";
  protected final String TEXT_1454 = ")null);";
  protected final String TEXT_1455 = NL + "\t\t\t\tset";
  protected final String TEXT_1456 = "(";
  protected final String TEXT_1457 = ");";
  protected final String TEXT_1458 = NL + "\t\t\t\treturn;";
  protected final String TEXT_1459 = NL + "\t\t}";
  protected final String TEXT_1460 = NL + "\t\tsuper.eUnset(featureID);";
  protected final String TEXT_1461 = NL + "\t\teDynamicUnset(featureID);";
  protected final String TEXT_1462 = NL + "\t}" + NL;
  protected final String TEXT_1463 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */";
  protected final String TEXT_1464 = NL + "\t@SuppressWarnings(\"unchecked\")";
  protected final String TEXT_1465 = NL + "\t@Override";
  protected final String TEXT_1466 = NL + "\tpublic boolean eIsSet(int featureID)" + NL + "\t{" + NL + "\t\tswitch (featureID";
  protected final String TEXT_1467 = ")" + NL + "\t\t{";
  protected final String TEXT_1468 = NL + "\t\t\tcase ";
  protected final String TEXT_1469 = ":";
  protected final String TEXT_1470 = NL + "\t\t\t\treturn isSet";
  protected final String TEXT_1471 = "();";
  protected final String TEXT_1472 = NL + "\t\t\t\treturn ";
  protected final String TEXT_1473 = "__ESETTING_DELEGATE.dynamicIsSet(this, null, 0);";
  protected final String TEXT_1474 = NL + "\t\t\t\treturn !((";
  protected final String TEXT_1475 = ".Internal.Wrapper)";
  protected final String TEXT_1476 = "()).featureMap().isEmpty();";
  protected final String TEXT_1477 = NL + "\t\t\t\treturn ";
  protected final String TEXT_1478 = " != null && !";
  protected final String TEXT_1479 = ".featureMap().isEmpty();";
  protected final String TEXT_1480 = NL + "\t\t\t\treturn ";
  protected final String TEXT_1481 = " != null && !";
  protected final String TEXT_1482 = ".isEmpty();";
  protected final String TEXT_1483 = NL + "\t\t\t\t";
  protected final String TEXT_1484 = " ";
  protected final String TEXT_1485 = " = (";
  protected final String TEXT_1486 = ")eVirtualGet(";
  protected final String TEXT_1487 = ");" + NL + "\t\t\t\treturn ";
  protected final String TEXT_1488 = " != null && !";
  protected final String TEXT_1489 = ".isEmpty();";
  protected final String TEXT_1490 = NL + "\t\t\t\treturn !";
  protected final String TEXT_1491 = "().isEmpty();";
  protected final String TEXT_1492 = NL + "\t\t\t\treturn isSet";
  protected final String TEXT_1493 = "();";
  protected final String TEXT_1494 = NL + "\t\t\t\treturn ";
  protected final String TEXT_1495 = " != null;";
  protected final String TEXT_1496 = NL + "\t\t\t\treturn eVirtualGet(";
  protected final String TEXT_1497 = ") != null;";
  protected final String TEXT_1498 = NL + "\t\t\t\treturn basicGet";
  protected final String TEXT_1499 = "() != null;";
  protected final String TEXT_1500 = NL + "\t\t\t\treturn ";
  protected final String TEXT_1501 = " != null;";
  protected final String TEXT_1502 = NL + "\t\t\t\treturn eVirtualGet(";
  protected final String TEXT_1503 = ") != null;";
  protected final String TEXT_1504 = NL + "\t\t\t\treturn ";
  protected final String TEXT_1505 = "() != null;";
  protected final String TEXT_1506 = NL + "\t\t\t\treturn ((";
  protected final String TEXT_1507 = " & ";
  protected final String TEXT_1508 = "_EFLAG) != 0) != ";
  protected final String TEXT_1509 = ";";
  protected final String TEXT_1510 = NL + "\t\t\t\treturn (";
  protected final String TEXT_1511 = " & ";
  protected final String TEXT_1512 = "_EFLAG) != ";
  protected final String TEXT_1513 = "_EFLAG_DEFAULT;";
  protected final String TEXT_1514 = NL + "\t\t\t\treturn ";
  protected final String TEXT_1515 = " != ";
  protected final String TEXT_1516 = ";";
  protected final String TEXT_1517 = NL + "\t\t\t\treturn eVirtualGet(";
  protected final String TEXT_1518 = ", ";
  protected final String TEXT_1519 = ") != ";
  protected final String TEXT_1520 = ";";
  protected final String TEXT_1521 = NL + "\t\t\t\treturn ";
  protected final String TEXT_1522 = "() != ";
  protected final String TEXT_1523 = ";";
  protected final String TEXT_1524 = NL + "\t\t\t\treturn ";
  protected final String TEXT_1525 = " == null ? ";
  protected final String TEXT_1526 = " != null : !";
  protected final String TEXT_1527 = ".equals(";
  protected final String TEXT_1528 = ");";
  protected final String TEXT_1529 = NL + "\t\t\t\t";
  protected final String TEXT_1530 = " ";
  protected final String TEXT_1531 = " = (";
  protected final String TEXT_1532 = ")eVirtualGet(";
  protected final String TEXT_1533 = ", ";
  protected final String TEXT_1534 = ");" + NL + "\t\t\t\treturn ";
  protected final String TEXT_1535 = " == null ? ";
  protected final String TEXT_1536 = " != null : !";
  protected final String TEXT_1537 = ".equals(";
  protected final String TEXT_1538 = ");";
  protected final String TEXT_1539 = NL + "\t\t\t\treturn ";
  protected final String TEXT_1540 = " == null ? ";
  protected final String TEXT_1541 = "() != null : !";
  protected final String TEXT_1542 = ".equals(";
  protected final String TEXT_1543 = "());";
  protected final String TEXT_1544 = NL + "\t\t}";
  protected final String TEXT_1545 = NL + "\t\treturn super.eIsSet(featureID);";
  protected final String TEXT_1546 = NL + "\t\treturn eDynamicIsSet(featureID);";
  protected final String TEXT_1547 = NL + "\t}" + NL;
  protected final String TEXT_1548 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */";
  protected final String TEXT_1549 = NL + "\t@Override";
  protected final String TEXT_1550 = NL + "\tpublic int eBaseStructuralFeatureID(int derivedFeatureID, Class";
  protected final String TEXT_1551 = " baseClass)" + NL + "\t{";
  protected final String TEXT_1552 = NL + "\t\tif (baseClass == ";
  protected final String TEXT_1553 = ".class)" + NL + "\t\t{" + NL + "\t\t\tswitch (derivedFeatureID";
  protected final String TEXT_1554 = ")" + NL + "\t\t\t{";
  protected final String TEXT_1555 = NL + "\t\t\t\tcase ";
  protected final String TEXT_1556 = ": return ";
  protected final String TEXT_1557 = ";";
  protected final String TEXT_1558 = NL + "\t\t\t\tdefault: return -1;" + NL + "\t\t\t}" + NL + "\t\t}";
  protected final String TEXT_1559 = NL + "\t\treturn super.eBaseStructuralFeatureID(derivedFeatureID, baseClass);" + NL + "\t}";
  protected final String TEXT_1560 = NL + NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */";
  protected final String TEXT_1561 = NL + "\t@Override";
  protected final String TEXT_1562 = NL + "\tpublic int eDerivedStructuralFeatureID(int baseFeatureID, Class";
  protected final String TEXT_1563 = " baseClass)" + NL + "\t{";
  protected final String TEXT_1564 = NL + "\t\tif (baseClass == ";
  protected final String TEXT_1565 = ".class)" + NL + "\t\t{" + NL + "\t\t\tswitch (baseFeatureID)" + NL + "\t\t\t{";
  protected final String TEXT_1566 = NL + "\t\t\t\tcase ";
  protected final String TEXT_1567 = ": return ";
  protected final String TEXT_1568 = ";";
  protected final String TEXT_1569 = NL + "\t\t\t\tdefault: return -1;" + NL + "\t\t\t}" + NL + "\t\t}";
  protected final String TEXT_1570 = NL + "\t\tif (baseClass == ";
  protected final String TEXT_1571 = ".class)" + NL + "\t\t{" + NL + "\t\t\tswitch (baseFeatureID";
  protected final String TEXT_1572 = ")" + NL + "\t\t\t{";
  protected final String TEXT_1573 = NL + "\t\t\t\tcase ";
  protected final String TEXT_1574 = ": return ";
  protected final String TEXT_1575 = ";";
  protected final String TEXT_1576 = NL + "\t\t\t\tdefault: return -1;" + NL + "\t\t\t}" + NL + "\t\t}";
  protected final String TEXT_1577 = NL + "\t\treturn super.eDerivedStructuralFeatureID(baseFeatureID, baseClass);" + NL + "\t}" + NL;
  protected final String TEXT_1578 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */";
  protected final String TEXT_1579 = NL + "\t@Override";
  protected final String TEXT_1580 = NL + "\tpublic int eDerivedOperationID(int baseOperationID, Class";
  protected final String TEXT_1581 = " baseClass)" + NL + "\t{";
  protected final String TEXT_1582 = NL + "\t\tif (baseClass == ";
  protected final String TEXT_1583 = ".class)" + NL + "\t\t{" + NL + "\t\t\tswitch (baseOperationID)" + NL + "\t\t\t{";
  protected final String TEXT_1584 = NL + "\t\t\t\tcase ";
  protected final String TEXT_1585 = ": return ";
  protected final String TEXT_1586 = ";";
  protected final String TEXT_1587 = NL + "\t\t\t\tdefault: return super.eDerivedOperationID(baseOperationID, baseClass);" + NL + "\t\t\t}" + NL + "\t\t}";
  protected final String TEXT_1588 = NL + "\t\tif (baseClass == ";
  protected final String TEXT_1589 = ".class)" + NL + "\t\t{" + NL + "\t\t\tswitch (baseOperationID)" + NL + "\t\t\t{";
  protected final String TEXT_1590 = NL + "\t\t\t\tcase ";
  protected final String TEXT_1591 = ": return ";
  protected final String TEXT_1592 = ";";
  protected final String TEXT_1593 = NL + "\t\t\t\tdefault: return -1;" + NL + "\t\t\t}" + NL + "\t\t}";
  protected final String TEXT_1594 = NL + "\t\tif (baseClass == ";
  protected final String TEXT_1595 = ".class)" + NL + "\t\t{" + NL + "\t\t\tswitch (baseOperationID";
  protected final String TEXT_1596 = ")" + NL + "\t\t\t{";
  protected final String TEXT_1597 = NL + "\t\t\t\tcase ";
  protected final String TEXT_1598 = ": return ";
  protected final String TEXT_1599 = ";";
  protected final String TEXT_1600 = NL + "\t\t\t\tdefault: return -1;" + NL + "\t\t\t}" + NL + "\t\t}";
  protected final String TEXT_1601 = NL + "\t\treturn super.eDerivedOperationID(baseOperationID, baseClass);" + NL + "\t}" + NL;
  protected final String TEXT_1602 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */";
  protected final String TEXT_1603 = NL + "\t@Override";
  protected final String TEXT_1604 = NL + "\tprotected Object[] eVirtualValues()" + NL + "\t{" + NL + "\t\treturn ";
  protected final String TEXT_1605 = ";" + NL + "\t}" + NL + "" + NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */";
  protected final String TEXT_1606 = NL + "\t@Override";
  protected final String TEXT_1607 = NL + "\tprotected void eSetVirtualValues(Object[] newValues)" + NL + "\t{" + NL + "\t\t";
  protected final String TEXT_1608 = " = newValues;" + NL + "\t}" + NL;
  protected final String TEXT_1609 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */";
  protected final String TEXT_1610 = NL + "\t@Override";
  protected final String TEXT_1611 = NL + "\tprotected int eVirtualIndexBits(int offset)" + NL + "\t{" + NL + "\t\tswitch (offset)" + NL + "\t\t{";
  protected final String TEXT_1612 = NL + "\t\t\tcase ";
  protected final String TEXT_1613 = " :" + NL + "\t\t\t\treturn ";
  protected final String TEXT_1614 = ";";
  protected final String TEXT_1615 = NL + "\t\t\tdefault :" + NL + "\t\t\t\tthrow new IndexOutOfBoundsException();" + NL + "\t\t}" + NL + "\t}" + NL + "" + NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */";
  protected final String TEXT_1616 = NL + "\t@Override";
  protected final String TEXT_1617 = NL + "\tprotected void eSetVirtualIndexBits(int offset, int newIndexBits)" + NL + "\t{" + NL + "\t\tswitch (offset)" + NL + "\t\t{";
  protected final String TEXT_1618 = NL + "\t\t\tcase ";
  protected final String TEXT_1619 = " :" + NL + "\t\t\t\t";
  protected final String TEXT_1620 = " = newIndexBits;" + NL + "\t\t\t\tbreak;";
  protected final String TEXT_1621 = NL + "\t\t\tdefault :" + NL + "\t\t\t\tthrow new IndexOutOfBoundsException();" + NL + "\t\t}" + NL + "\t}" + NL;
  protected final String TEXT_1622 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */";
  protected final String TEXT_1623 = NL + "\t@Override";
  protected final String TEXT_1624 = NL + "\t@SuppressWarnings(";
  protected final String TEXT_1625 = "\"unchecked\"";
  protected final String TEXT_1626 = "{\"rawtypes\", \"unchecked\" }";
  protected final String TEXT_1627 = ")";
  protected final String TEXT_1628 = NL + "\tpublic Object eInvoke(int operationID, ";
  protected final String TEXT_1629 = " arguments) throws ";
  protected final String TEXT_1630 = NL + "\t{" + NL + "\t\tswitch (operationID";
  protected final String TEXT_1631 = ")" + NL + "\t\t{";
  protected final String TEXT_1632 = NL + "\t\t\tcase ";
  protected final String TEXT_1633 = ":";
  protected final String TEXT_1634 = NL + "\t\t\t\t";
  protected final String TEXT_1635 = "(";
  protected final String TEXT_1636 = "(";
  protected final String TEXT_1637 = "(";
  protected final String TEXT_1638 = ")";
  protected final String TEXT_1639 = "arguments.get(";
  protected final String TEXT_1640 = ")";
  protected final String TEXT_1641 = ").";
  protected final String TEXT_1642 = "()";
  protected final String TEXT_1643 = ", ";
  protected final String TEXT_1644 = ");" + NL + "\t\t\t\treturn null;";
  protected final String TEXT_1645 = NL + "\t\t\t\treturn ";
  protected final String TEXT_1646 = "new ";
  protected final String TEXT_1647 = "(";
  protected final String TEXT_1648 = "(";
  protected final String TEXT_1649 = "(";
  protected final String TEXT_1650 = "(";
  protected final String TEXT_1651 = ")";
  protected final String TEXT_1652 = "arguments.get(";
  protected final String TEXT_1653 = ")";
  protected final String TEXT_1654 = ").";
  protected final String TEXT_1655 = "()";
  protected final String TEXT_1656 = ", ";
  protected final String TEXT_1657 = ")";
  protected final String TEXT_1658 = ")";
  protected final String TEXT_1659 = ";";
  protected final String TEXT_1660 = NL + "\t\t}";
  protected final String TEXT_1661 = NL + "\t\treturn super.eInvoke(operationID, arguments);";
  protected final String TEXT_1662 = NL + "\t\treturn eDynamicInvoke(operationID, arguments);";
  protected final String TEXT_1663 = NL + "\t}" + NL;
  protected final String TEXT_1664 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */";
  protected final String TEXT_1665 = NL + "\t@Override";
  protected final String TEXT_1666 = NL + "\tpublic String toString()" + NL + "\t{" + NL + "\t\tif (eIsProxy()) return super.toString();" + NL + "" + NL + "\t\tStringBuffer result = new StringBuffer(super.toString());";
  protected final String TEXT_1667 = NL + "\t\tresult.append(\" (";
  protected final String TEXT_1668 = ": \");";
  protected final String TEXT_1669 = NL + "\t\tresult.append(\", ";
  protected final String TEXT_1670 = ": \");";
  protected final String TEXT_1671 = NL + "\t\tif (eVirtualIsSet(";
  protected final String TEXT_1672 = ")) result.append(eVirtualGet(";
  protected final String TEXT_1673 = ")); else result.append(\"<unset>\");";
  protected final String TEXT_1674 = NL + "\t\tif (";
  protected final String TEXT_1675 = "(";
  protected final String TEXT_1676 = " & ";
  protected final String TEXT_1677 = "_ESETFLAG) != 0";
  protected final String TEXT_1678 = "ESet";
  protected final String TEXT_1679 = ") result.append((";
  protected final String TEXT_1680 = " & ";
  protected final String TEXT_1681 = "_EFLAG) != 0); else result.append(\"<unset>\");";
  protected final String TEXT_1682 = NL + "\t\tif (";
  protected final String TEXT_1683 = "(";
  protected final String TEXT_1684 = " & ";
  protected final String TEXT_1685 = "_ESETFLAG) != 0";
  protected final String TEXT_1686 = "ESet";
  protected final String TEXT_1687 = ") result.append(";
  protected final String TEXT_1688 = "_EFLAG_VALUES[(";
  protected final String TEXT_1689 = " & ";
  protected final String TEXT_1690 = "_EFLAG) >>> ";
  protected final String TEXT_1691 = "_EFLAG_OFFSET]); else result.append(\"<unset>\");";
  protected final String TEXT_1692 = NL + "\t\tif (";
  protected final String TEXT_1693 = "(";
  protected final String TEXT_1694 = " & ";
  protected final String TEXT_1695 = "_ESETFLAG) != 0";
  protected final String TEXT_1696 = "ESet";
  protected final String TEXT_1697 = ") result.append(";
  protected final String TEXT_1698 = "); else result.append(\"<unset>\");";
  protected final String TEXT_1699 = NL + "\t\tresult.append(eVirtualGet(";
  protected final String TEXT_1700 = ", ";
  protected final String TEXT_1701 = "));";
  protected final String TEXT_1702 = NL + "\t\tresult.append((";
  protected final String TEXT_1703 = " & ";
  protected final String TEXT_1704 = "_EFLAG) != 0);";
  protected final String TEXT_1705 = NL + "\t\tresult.append(";
  protected final String TEXT_1706 = "_EFLAG_VALUES[(";
  protected final String TEXT_1707 = " & ";
  protected final String TEXT_1708 = "_EFLAG) >>> ";
  protected final String TEXT_1709 = "_EFLAG_OFFSET]);";
  protected final String TEXT_1710 = NL + "\t\tresult.append(";
  protected final String TEXT_1711 = ");";
  protected final String TEXT_1712 = NL + "\t\tresult.append(')');" + NL + "\t\treturn result.toString();" + NL + "\t}" + NL;
  protected final String TEXT_1713 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */";
  protected final String TEXT_1714 = NL + "\t@";
  protected final String TEXT_1715 = NL + "\tprotected int hash = -1;" + NL + "" + NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */" + NL + "\tpublic int getHash()" + NL + "\t{" + NL + "\t\tif (hash == -1)" + NL + "\t\t{" + NL + "\t\t\t";
  protected final String TEXT_1716 = " theKey = getKey();" + NL + "\t\t\thash = (theKey == null ? 0 : theKey.hashCode());" + NL + "\t\t}" + NL + "\t\treturn hash;" + NL + "\t}" + NL + "" + NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */" + NL + "\tpublic void setHash(int hash)" + NL + "\t{" + NL + "\t\tthis.hash = hash;" + NL + "\t}" + NL + "" + NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */" + NL + "\tpublic ";
  protected final String TEXT_1717 = " getKey()" + NL + "\t{";
  protected final String TEXT_1718 = NL + "\t\treturn new ";
  protected final String TEXT_1719 = "(getTypedKey());";
  protected final String TEXT_1720 = NL + "\t\treturn getTypedKey();";
  protected final String TEXT_1721 = NL + "\t}" + NL + "" + NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */" + NL + "\tpublic void setKey(";
  protected final String TEXT_1722 = " key)" + NL + "\t{";
  protected final String TEXT_1723 = NL + "\t\tgetTypedKey().addAll(";
  protected final String TEXT_1724 = "(";
  protected final String TEXT_1725 = ")";
  protected final String TEXT_1726 = "key);";
  protected final String TEXT_1727 = NL + "\t\tsetTypedKey(key);";
  protected final String TEXT_1728 = NL + "\t\tsetTypedKey(((";
  protected final String TEXT_1729 = ")key).";
  protected final String TEXT_1730 = "());";
  protected final String TEXT_1731 = NL + "\t\tsetTypedKey((";
  protected final String TEXT_1732 = ")key);";
  protected final String TEXT_1733 = NL + "\t}" + NL + "" + NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */" + NL + "\tpublic ";
  protected final String TEXT_1734 = " getValue()" + NL + "\t{";
  protected final String TEXT_1735 = NL + "\t\treturn new ";
  protected final String TEXT_1736 = "(getTypedValue());";
  protected final String TEXT_1737 = NL + "\t\treturn getTypedValue();";
  protected final String TEXT_1738 = NL + "\t}" + NL + "" + NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */" + NL + "\tpublic ";
  protected final String TEXT_1739 = " setValue(";
  protected final String TEXT_1740 = " value)" + NL + "\t{" + NL + "\t\t";
  protected final String TEXT_1741 = " oldValue = getValue();";
  protected final String TEXT_1742 = NL + "\t\tgetTypedValue().clear();" + NL + "\t\tgetTypedValue().addAll(";
  protected final String TEXT_1743 = "(";
  protected final String TEXT_1744 = ")";
  protected final String TEXT_1745 = "value);";
  protected final String TEXT_1746 = NL + "\t\tsetTypedValue(value);";
  protected final String TEXT_1747 = NL + "\t\tsetTypedValue(((";
  protected final String TEXT_1748 = ")value).";
  protected final String TEXT_1749 = "());";
  protected final String TEXT_1750 = NL + "\t\tsetTypedValue((";
  protected final String TEXT_1751 = ")value);";
  protected final String TEXT_1752 = NL + "\t\treturn oldValue;" + NL + "\t}" + NL + "" + NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */";
  protected final String TEXT_1753 = NL + "\t@SuppressWarnings(\"unchecked\")";
  protected final String TEXT_1754 = NL + "\tpublic ";
  protected final String TEXT_1755 = " getEMap()" + NL + "\t{" + NL + "\t\t";
  protected final String TEXT_1756 = " container = eContainer();" + NL + "\t\treturn container == null ? null : (";
  protected final String TEXT_1757 = ")container.eGet(eContainmentFeature());" + NL + "\t}" + NL;
  protected final String TEXT_1758 = NL + "} //";
  protected final String TEXT_1759 = NL;

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
    } else {
    if (genFeature.isResolveProxies()) {
    if (genModel.isVirtualDelegation()) {
    stringBuffer.append(TEXT_380);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_381);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_382);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_383);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(positiveOffsetCorrection);
    if (genFeature.hasEDefault()) {
    stringBuffer.append(TEXT_384);
    stringBuffer.append(genFeature.getEDefault());
    }
    stringBuffer.append(TEXT_385);
    }
    stringBuffer.append(TEXT_386);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_387);
    stringBuffer.append(genFeature.getSafeNameAsEObject());
    stringBuffer.append(TEXT_388);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.InternalEObject"));
    stringBuffer.append(TEXT_389);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_390);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.InternalEObject"));
    stringBuffer.append(TEXT_391);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_392);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_393);
    stringBuffer.append(genFeature.getNonEObjectInternalTypeCast(genClass));
    stringBuffer.append(TEXT_394);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_395);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_396);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_397);
    if (genFeature.isEffectiveContains()) {
    stringBuffer.append(TEXT_398);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.InternalEObject"));
    stringBuffer.append(TEXT_399);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_400);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.InternalEObject"));
    stringBuffer.append(TEXT_401);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_402);
    if (!genFeature.isBidirectional()) {
    stringBuffer.append(TEXT_403);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.notify.NotificationChain"));
    stringBuffer.append(TEXT_404);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_405);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(negativeOffsetCorrection);
    stringBuffer.append(TEXT_406);
    } else { GenFeature reverseFeature = genFeature.getReverse(); GenClass targetClass = reverseFeature.getGenClass(); String reverseOffsetCorrection = targetClass.hasOffsetCorrection() ? " + " + genClass.getOffsetCorrectionField(genFeature) : "";
    stringBuffer.append(TEXT_407);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.notify.NotificationChain"));
    stringBuffer.append(TEXT_408);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_409);
    stringBuffer.append(targetClass.getQualifiedFeatureID(reverseFeature));
    stringBuffer.append(reverseOffsetCorrection);
    stringBuffer.append(TEXT_410);
    stringBuffer.append(targetClass.getRawImportedInterfaceName());
    stringBuffer.append(TEXT_411);
    }
    stringBuffer.append(TEXT_412);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_413);
    if (!genFeature.isBidirectional()) {
    stringBuffer.append(TEXT_414);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_415);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(negativeOffsetCorrection);
    stringBuffer.append(TEXT_416);
    } else { GenFeature reverseFeature = genFeature.getReverse(); GenClass targetClass = reverseFeature.getGenClass(); String reverseOffsetCorrection = targetClass.hasOffsetCorrection() ? " + " + genClass.getOffsetCorrectionField(genFeature) : "";
    stringBuffer.append(TEXT_417);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_418);
    stringBuffer.append(targetClass.getQualifiedFeatureID(reverseFeature));
    stringBuffer.append(reverseOffsetCorrection);
    stringBuffer.append(TEXT_419);
    stringBuffer.append(targetClass.getRawImportedInterfaceName());
    stringBuffer.append(TEXT_420);
    }
    stringBuffer.append(TEXT_421);
    } else if (genModel.isVirtualDelegation()) {
    stringBuffer.append(TEXT_422);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(positiveOffsetCorrection);
    stringBuffer.append(TEXT_423);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_424);
    }
    if (!genModel.isSuppressNotification()) {
    stringBuffer.append(TEXT_425);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.impl.ENotificationImpl"));
    stringBuffer.append(TEXT_426);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.notify.Notification"));
    stringBuffer.append(TEXT_427);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(positiveOffsetCorrection);
    stringBuffer.append(TEXT_428);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_429);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_430);
    }
    stringBuffer.append(TEXT_431);
    }
    if (!genFeature.isResolveProxies() && genModel.isVirtualDelegation() && !genFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_432);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_433);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(positiveOffsetCorrection);
    if (genFeature.hasEDefault()) {
    stringBuffer.append(TEXT_434);
    stringBuffer.append(genFeature.getEDefault());
    }
    stringBuffer.append(TEXT_435);
    } else if (genClass.isFlag(genFeature)) {
    if (genFeature.isBooleanType()) {
    stringBuffer.append(TEXT_436);
    stringBuffer.append(genClass.getFlagsField(genFeature));
    stringBuffer.append(TEXT_437);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_438);
    } else {
    stringBuffer.append(TEXT_439);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_440);
    stringBuffer.append(genClass.getFlagsField(genFeature));
    stringBuffer.append(TEXT_441);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_442);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_443);
    }
    } else {
    stringBuffer.append(TEXT_444);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_445);
    }
    }
    } else {//volatile
    if (genFeature.isResolveProxies() && !genFeature.isListType()) {
    stringBuffer.append(TEXT_446);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_447);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_448);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_449);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_450);
    stringBuffer.append(genFeature.getSafeNameAsEObject());
    stringBuffer.append(TEXT_451);
    stringBuffer.append(genFeature.getNonEObjectInternalTypeCast(genClass));
    stringBuffer.append(TEXT_452);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.InternalEObject"));
    stringBuffer.append(TEXT_453);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_454);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_455);
    } else if (genFeature.hasDelegateFeature()) { GenFeature delegateFeature = genFeature.getDelegateFeature();
    if (genFeature.isFeatureMapType()) {
    String featureMapEntryTemplateArgument = isJDK50 ? "<" + genModel.getImportedName("org.eclipse.emf.ecore.util.FeatureMap") + ".Entry>" : "";
    if (delegateFeature.isWrappedFeatureMapType()) {
    stringBuffer.append(TEXT_456);
    stringBuffer.append(genFeature.getImportedEffectiveFeatureMapWrapperClass());
    stringBuffer.append(TEXT_457);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.FeatureMap"));
    stringBuffer.append(TEXT_458);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.FeatureMap"));
    stringBuffer.append(TEXT_459);
    stringBuffer.append(delegateFeature.getAccessorName());
    stringBuffer.append(TEXT_460);
    stringBuffer.append(featureMapEntryTemplateArgument);
    stringBuffer.append(TEXT_461);
    stringBuffer.append(genFeature.getQualifiedFeatureAccessor());
    stringBuffer.append(TEXT_462);
    } else {
    stringBuffer.append(TEXT_463);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.FeatureMap"));
    stringBuffer.append(TEXT_464);
    stringBuffer.append(delegateFeature.getAccessorName());
    stringBuffer.append(TEXT_465);
    stringBuffer.append(featureMapEntryTemplateArgument);
    stringBuffer.append(TEXT_466);
    stringBuffer.append(genFeature.getQualifiedFeatureAccessor());
    stringBuffer.append(TEXT_467);
    }
    } else if (genFeature.isListType()) {
    if (delegateFeature.isWrappedFeatureMapType()) {
    stringBuffer.append(TEXT_468);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.FeatureMap"));
    stringBuffer.append(TEXT_469);
    stringBuffer.append(delegateFeature.getAccessorName());
    stringBuffer.append(TEXT_470);
    stringBuffer.append(genFeature.getQualifiedFeatureAccessor());
    stringBuffer.append(TEXT_471);
    } else {
    stringBuffer.append(TEXT_472);
    stringBuffer.append(delegateFeature.getAccessorName());
    stringBuffer.append(TEXT_473);
    stringBuffer.append(genFeature.getQualifiedFeatureAccessor());
    stringBuffer.append(TEXT_474);
    }
    } else {
    if (delegateFeature.isWrappedFeatureMapType()) {
    stringBuffer.append(TEXT_475);
    if (!isJDK50 && genFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_476);
    }
    if (genFeature.getTypeGenDataType() == null || !genFeature.getTypeGenDataType().isObjectType()) {
    stringBuffer.append(TEXT_477);
    stringBuffer.append(genFeature.getObjectType(genClass));
    stringBuffer.append(TEXT_478);
    }
    stringBuffer.append(TEXT_479);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.FeatureMap"));
    stringBuffer.append(TEXT_480);
    stringBuffer.append(delegateFeature.getAccessorName());
    stringBuffer.append(TEXT_481);
    stringBuffer.append(genFeature.getQualifiedFeatureAccessor());
    stringBuffer.append(TEXT_482);
    if (!isJDK50 && genFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_483);
    stringBuffer.append(genFeature.getPrimitiveValueFunction());
    stringBuffer.append(TEXT_484);
    }
    stringBuffer.append(TEXT_485);
    } else {
    stringBuffer.append(TEXT_486);
    if (!isJDK50 && genFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_487);
    }
    if (genFeature.getTypeGenDataType() == null || !genFeature.getTypeGenDataType().isObjectType()) {
    stringBuffer.append(TEXT_488);
    stringBuffer.append(genFeature.getObjectType(genClass));
    stringBuffer.append(TEXT_489);
    }
    stringBuffer.append(TEXT_490);
    stringBuffer.append(delegateFeature.getAccessorName());
    stringBuffer.append(TEXT_491);
    stringBuffer.append(genFeature.getQualifiedFeatureAccessor());
    stringBuffer.append(TEXT_492);
    if (!isJDK50 && genFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_493);
    stringBuffer.append(genFeature.getPrimitiveValueFunction());
    stringBuffer.append(TEXT_494);
    }
    stringBuffer.append(TEXT_495);
    }
    }
    } else if (genClass.getGetAccessorOperation(genFeature) != null) {
    stringBuffer.append(TEXT_496);
    stringBuffer.append(genClass.getGetAccessorOperation(genFeature).getBody(genModel.getIndentation(stringBuffer)));
    } else if (genFeature.hasGetterBody()) {
    stringBuffer.append(TEXT_497);
    stringBuffer.append(genFeature.getGetterBody(genModel.getIndentation(stringBuffer)));
    } else {
    stringBuffer.append(TEXT_498);
    stringBuffer.append(genFeature.getFormattedName());
    stringBuffer.append(TEXT_499);
    stringBuffer.append(genFeature.getFeatureKind());
    stringBuffer.append(TEXT_500);
    if (genFeature.isListType()) {
    stringBuffer.append(TEXT_501);
    if (genFeature.isMapType()) {
    stringBuffer.append(TEXT_502);
    } else if (genFeature.isFeatureMapType()) {
    stringBuffer.append(TEXT_503);
    } else {
    stringBuffer.append(TEXT_504);
    }
    stringBuffer.append(TEXT_505);
    }
    stringBuffer.append(TEXT_506);
    //Class/getGenFeature.todo.override.javajetinc
    }
    }
    stringBuffer.append(TEXT_507);
    }
    //Class/getGenFeature.override.javajetinc
    }
    if (isImplementation && !genModel.isReflectiveDelegation() && genFeature.isBasicGet()) {
    stringBuffer.append(TEXT_508);
    if (isJDK50) { //Class/basicGetGenFeature.annotations.insert.javajetinc
    }
    stringBuffer.append(TEXT_509);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_510);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_511);
    if (genModel.isDynamicDelegation()) {
    stringBuffer.append(TEXT_512);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_513);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(TEXT_514);
    stringBuffer.append(genFeature.getQualifiedFeatureAccessor());
    stringBuffer.append(TEXT_515);
    stringBuffer.append(!genFeature.isEffectiveSuppressEMFTypes());
    stringBuffer.append(TEXT_516);
    } else if (genFeature.hasSettingDelegate()) {
    stringBuffer.append(TEXT_517);
    if (!isJDK50 && genFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_518);
    }
    stringBuffer.append(TEXT_519);
    stringBuffer.append(genFeature.getObjectType(genClass));
    stringBuffer.append(TEXT_520);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_521);
    if (!isJDK50 && genFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_522);
    stringBuffer.append(genFeature.getPrimitiveValueFunction());
    stringBuffer.append(TEXT_523);
    }
    stringBuffer.append(TEXT_524);
    } else if (genFeature.isContainer()) {
    stringBuffer.append(TEXT_525);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(positiveOffsetCorrection);
    stringBuffer.append(TEXT_526);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_527);
    } else if (!genFeature.isVolatile()) {
    if (genModel.isVirtualDelegation()) {
    stringBuffer.append(TEXT_528);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_529);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(positiveOffsetCorrection);
    stringBuffer.append(TEXT_530);
    } else {
    stringBuffer.append(TEXT_531);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_532);
    }
    } else if (genFeature.hasDelegateFeature()) { GenFeature delegateFeature = genFeature.getDelegateFeature();
    if (delegateFeature.isWrappedFeatureMapType()) {
    stringBuffer.append(TEXT_533);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_534);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.FeatureMap"));
    stringBuffer.append(TEXT_535);
    stringBuffer.append(delegateFeature.getAccessorName());
    stringBuffer.append(TEXT_536);
    stringBuffer.append(genFeature.getQualifiedFeatureAccessor());
    stringBuffer.append(TEXT_537);
    } else {
    stringBuffer.append(TEXT_538);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_539);
    stringBuffer.append(delegateFeature.getAccessorName());
    stringBuffer.append(TEXT_540);
    stringBuffer.append(genFeature.getQualifiedFeatureAccessor());
    stringBuffer.append(TEXT_541);
    }
    } else {
    stringBuffer.append(TEXT_542);
    stringBuffer.append(genFeature.getFormattedName());
    stringBuffer.append(TEXT_543);
    stringBuffer.append(genFeature.getFeatureKind());
    stringBuffer.append(TEXT_544);
    //Class/basicGetGenFeature.todo.override.javajetinc
    }
    stringBuffer.append(TEXT_545);
    //Class/basicGetGenFeature.override.javajetinc
    }
    if (isImplementation && !genModel.isReflectiveDelegation() && genFeature.isBasicSet()) {
    stringBuffer.append(TEXT_546);
    if (isJDK50) { //Class/basicSetGenFeature.annotations.insert.javajetinc
    }
    stringBuffer.append(TEXT_547);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.notify.NotificationChain"));
    stringBuffer.append(TEXT_548);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_549);
    stringBuffer.append(genFeature.getImportedInternalType(genClass));
    stringBuffer.append(TEXT_550);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_551);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.notify.NotificationChain"));
    stringBuffer.append(TEXT_552);
    if (genFeature.isContainer()) {
    stringBuffer.append(TEXT_553);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.InternalEObject"));
    stringBuffer.append(TEXT_554);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_555);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(positiveOffsetCorrection);
    stringBuffer.append(TEXT_556);
    stringBuffer.append(TEXT_557);
    } else if (genModel.isDynamicDelegation()) {
    stringBuffer.append(TEXT_558);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.InternalEObject"));
    stringBuffer.append(TEXT_559);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_560);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(positiveOffsetCorrection);
    stringBuffer.append(TEXT_561);
    stringBuffer.append(TEXT_562);
    } else if (!genFeature.isVolatile()) {
    if (genModel.isVirtualDelegation()) {
    stringBuffer.append(TEXT_563);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_564);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(positiveOffsetCorrection);
    stringBuffer.append(TEXT_565);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_566);
    } else {
    stringBuffer.append(TEXT_567);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_568);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_569);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_570);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_571);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_572);
    }
    if (genFeature.isUnsettable()) {
    if (genModel.isVirtualDelegation()) {
    if (!genModel.isSuppressNotification()) {
    stringBuffer.append(TEXT_573);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_574);
    }
    } else if (genClass.isESetFlag(genFeature)) {
    stringBuffer.append(TEXT_575);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_576);
    stringBuffer.append(genClass.getESetFlagsField(genFeature));
    stringBuffer.append(TEXT_577);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_578);
    if (!genModel.isSuppressNotification()) {
    stringBuffer.append(TEXT_579);
    stringBuffer.append(genClass.getESetFlagsField(genFeature));
    stringBuffer.append(TEXT_580);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_581);
    }
    } else {
    if (!genModel.isSuppressNotification()) {
    stringBuffer.append(TEXT_582);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_583);
    stringBuffer.append(genFeature.getUncapName());
    stringBuffer.append(TEXT_584);
    }
    stringBuffer.append(TEXT_585);
    stringBuffer.append(genFeature.getUncapName());
    stringBuffer.append(TEXT_586);
    }
    }
    if (!genModel.isSuppressNotification()) {
    stringBuffer.append(TEXT_587);
    if (genFeature.isUnsettable()) {
    stringBuffer.append(TEXT_588);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.impl.ENotificationImpl"));
    stringBuffer.append(TEXT_589);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.impl.ENotificationImpl"));
    stringBuffer.append(TEXT_590);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.notify.Notification"));
    stringBuffer.append(TEXT_591);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(positiveOffsetCorrection);
    stringBuffer.append(TEXT_592);
    if (genModel.isVirtualDelegation()) {
    stringBuffer.append(TEXT_593);
    stringBuffer.append(genFeature.getCapName());
    } else {
    stringBuffer.append(TEXT_594);
    stringBuffer.append(genFeature.getCapName());
    }
    stringBuffer.append(TEXT_595);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_596);
    if (genModel.isVirtualDelegation()) {
    stringBuffer.append(TEXT_597);
    } else {
    stringBuffer.append(TEXT_598);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_599);
    }
    stringBuffer.append(TEXT_600);
    } else {
    stringBuffer.append(TEXT_601);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.impl.ENotificationImpl"));
    stringBuffer.append(TEXT_602);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.impl.ENotificationImpl"));
    stringBuffer.append(TEXT_603);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.notify.Notification"));
    stringBuffer.append(TEXT_604);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(positiveOffsetCorrection);
    stringBuffer.append(TEXT_605);
    if (genModel.isVirtualDelegation()) {
    stringBuffer.append(TEXT_606);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_607);
    stringBuffer.append(genFeature.getCapName());
    } else {
    stringBuffer.append(TEXT_608);
    stringBuffer.append(genFeature.getCapName());
    }
    stringBuffer.append(TEXT_609);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_610);
    }
    stringBuffer.append(TEXT_611);
    }
    stringBuffer.append(TEXT_612);
    } else if (genFeature.hasDelegateFeature()) { GenFeature delegateFeature = genFeature.getDelegateFeature();
    if (delegateFeature.isWrappedFeatureMapType()) {
    stringBuffer.append(TEXT_613);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.FeatureMap"));
    stringBuffer.append(TEXT_614);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.FeatureMap"));
    stringBuffer.append(TEXT_615);
    stringBuffer.append(delegateFeature.getAccessorName());
    stringBuffer.append(TEXT_616);
    stringBuffer.append(genFeature.getQualifiedFeatureAccessor());
    stringBuffer.append(TEXT_617);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_618);
    } else {
    stringBuffer.append(TEXT_619);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.FeatureMap"));
    stringBuffer.append(TEXT_620);
    stringBuffer.append(delegateFeature.getAccessorName());
    stringBuffer.append(TEXT_621);
    stringBuffer.append(genFeature.getQualifiedFeatureAccessor());
    stringBuffer.append(TEXT_622);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_623);
    }
    } else {
    stringBuffer.append(TEXT_624);
    stringBuffer.append(genFeature.getFormattedName());
    stringBuffer.append(TEXT_625);
    stringBuffer.append(genFeature.getFeatureKind());
    stringBuffer.append(TEXT_626);
    //Class/basicSetGenFeature.todo.override.javajetinc
    }
    stringBuffer.append(TEXT_627);
    //Class/basicSetGenFeature.override.javajetinc
    }
    if (genFeature.isSet() && (isImplementation || !genFeature.isSuppressedSetVisibility())) {
    if (isInterface) { 
    stringBuffer.append(TEXT_628);
    stringBuffer.append(genClass.getQualifiedInterfaceName());
    stringBuffer.append(TEXT_629);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_630);
    stringBuffer.append(genFeature.getFormattedName());
    stringBuffer.append(TEXT_631);
    stringBuffer.append(genFeature.getFeatureKind());
    stringBuffer.append(TEXT_632);
    stringBuffer.append(TEXT_633);
    stringBuffer.append(genFeature.getFormattedName());
    stringBuffer.append(TEXT_634);
    stringBuffer.append(genFeature.getFeatureKind());
    stringBuffer.append(TEXT_635);
    if (genFeature.isEnumType()) {
    stringBuffer.append(TEXT_636);
    stringBuffer.append(genFeature.getTypeGenEnum().getQualifiedName());
    }
    if (genFeature.isUnsettable()) {
    if (!genFeature.isSuppressedIsSetVisibility()) {
    stringBuffer.append(TEXT_637);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_638);
    }
    if (!genFeature.isSuppressedUnsetVisibility()) {
    stringBuffer.append(TEXT_639);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_640);
    }
    }
    stringBuffer.append(TEXT_641);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_642);
    //Class/setGenFeature.javadoc.override.javajetinc
    } else {
    stringBuffer.append(TEXT_643);
    if (isJDK50) { //Class/setGenFeature.annotations.insert.javajetinc
    }
    }
    if (!isImplementation) { 
    stringBuffer.append(TEXT_644);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_645);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_646);
    } else { GenOperation setAccessorOperation = genClass.getSetAccessorOperation(genFeature);
    stringBuffer.append(TEXT_647);
    stringBuffer.append(genFeature.getAccessorName());
    if (genClass.hasCollidingSetAccessorOperation(genFeature)) {
    stringBuffer.append(TEXT_648);
    }
    stringBuffer.append(TEXT_649);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_650);
    stringBuffer.append(setAccessorOperation == null ? "new" + genFeature.getCapName() : setAccessorOperation.getGenParameters().get(0).getName());
    stringBuffer.append(TEXT_651);
    if (genModel.isDynamicDelegation()) {
    stringBuffer.append(TEXT_652);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(TEXT_653);
    stringBuffer.append(genFeature.getQualifiedFeatureAccessor());
    stringBuffer.append(TEXT_654);
    if (!isJDK50 && genFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_655);
    stringBuffer.append(genFeature.getObjectType(genClass));
    stringBuffer.append(TEXT_656);
    }
    stringBuffer.append(TEXT_657);
    stringBuffer.append(genFeature.getCapName());
    if (!isJDK50 && genFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_658);
    }
    stringBuffer.append(TEXT_659);
    } else if (genModel.isReflectiveDelegation()) {
    stringBuffer.append(TEXT_660);
    stringBuffer.append(genFeature.getQualifiedFeatureAccessor());
    stringBuffer.append(TEXT_661);
    if (!isJDK50 && genFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_662);
    stringBuffer.append(genFeature.getObjectType(genClass));
    stringBuffer.append(TEXT_663);
    }
    stringBuffer.append(TEXT_664);
    stringBuffer.append(genFeature.getCapName());
    if (!isJDK50 && genFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_665);
    }
    stringBuffer.append(TEXT_666);
    } else if (genFeature.hasSettingDelegate()) {
    stringBuffer.append(TEXT_667);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_668);
    if (!isJDK50 && genFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_669);
    stringBuffer.append(genFeature.getObjectType(genClass));
    stringBuffer.append(TEXT_670);
    }
    stringBuffer.append(TEXT_671);
    stringBuffer.append(genFeature.getCapName());
    if (!isJDK50 && genFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_672);
    }
    stringBuffer.append(TEXT_673);
    } else if (!genFeature.isVolatile()) {
    if (genFeature.isContainer()) { GenFeature reverseFeature = genFeature.getReverse(); GenClass targetClass = reverseFeature.getGenClass(); String reverseOffsetCorrection = targetClass.hasOffsetCorrection() ? " + " + genClass.getOffsetCorrectionField(genFeature) : "";
    stringBuffer.append(TEXT_674);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_675);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(positiveOffsetCorrection);
    stringBuffer.append(TEXT_676);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_677);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.EcoreUtil"));
    stringBuffer.append(TEXT_678);
    stringBuffer.append(genFeature.getEObjectCast());
    stringBuffer.append(TEXT_679);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_680);
    stringBuffer.append(genModel.getImportedName("java.lang.IllegalArgumentException"));
    stringBuffer.append(TEXT_681);
    stringBuffer.append(genModel.getNonNLS());
    stringBuffer.append(TEXT_682);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.notify.NotificationChain"));
    stringBuffer.append(TEXT_683);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_684);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.InternalEObject"));
    stringBuffer.append(TEXT_685);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_686);
    stringBuffer.append(targetClass.getQualifiedFeatureID(reverseFeature));
    stringBuffer.append(reverseOffsetCorrection);
    stringBuffer.append(TEXT_687);
    stringBuffer.append(targetClass.getRawImportedInterfaceName());
    stringBuffer.append(TEXT_688);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_689);
    stringBuffer.append(genFeature.getInternalTypeCast());
    stringBuffer.append(TEXT_690);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_691);
    if (!genModel.isSuppressNotification()) {
    stringBuffer.append(TEXT_692);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.impl.ENotificationImpl"));
    stringBuffer.append(TEXT_693);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.notify.Notification"));
    stringBuffer.append(TEXT_694);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(positiveOffsetCorrection);
    stringBuffer.append(TEXT_695);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_696);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_697);
    }
    } else if (genFeature.isBidirectional() || genFeature.isEffectiveContains()) {
    if (genModel.isVirtualDelegation()) {
    stringBuffer.append(TEXT_698);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_699);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_700);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_701);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(positiveOffsetCorrection);
    stringBuffer.append(TEXT_702);
    }
    stringBuffer.append(TEXT_703);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_704);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_705);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.notify.NotificationChain"));
    stringBuffer.append(TEXT_706);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_707);
    if (!genFeature.isBidirectional()) {
    stringBuffer.append(TEXT_708);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.InternalEObject"));
    stringBuffer.append(TEXT_709);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_710);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(negativeOffsetCorrection);
    stringBuffer.append(TEXT_711);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_712);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.InternalEObject"));
    stringBuffer.append(TEXT_713);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_714);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(negativeOffsetCorrection);
    stringBuffer.append(TEXT_715);
    } else { GenFeature reverseFeature = genFeature.getReverse(); GenClass targetClass = reverseFeature.getGenClass(); String reverseOffsetCorrection = targetClass.hasOffsetCorrection() ? " + " + genClass.getOffsetCorrectionField(genFeature) : "";
    stringBuffer.append(TEXT_716);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.InternalEObject"));
    stringBuffer.append(TEXT_717);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_718);
    stringBuffer.append(targetClass.getQualifiedFeatureID(reverseFeature));
    stringBuffer.append(reverseOffsetCorrection);
    stringBuffer.append(TEXT_719);
    stringBuffer.append(targetClass.getRawImportedInterfaceName());
    stringBuffer.append(TEXT_720);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_721);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.InternalEObject"));
    stringBuffer.append(TEXT_722);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_723);
    stringBuffer.append(targetClass.getQualifiedFeatureID(reverseFeature));
    stringBuffer.append(reverseOffsetCorrection);
    stringBuffer.append(TEXT_724);
    stringBuffer.append(targetClass.getRawImportedInterfaceName());
    stringBuffer.append(TEXT_725);
    }
    stringBuffer.append(TEXT_726);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_727);
    stringBuffer.append(genFeature.getInternalTypeCast());
    stringBuffer.append(TEXT_728);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_729);
    if (genFeature.isUnsettable()) {
    stringBuffer.append(TEXT_730);
    if (genModel.isVirtualDelegation()) {
    stringBuffer.append(TEXT_731);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_732);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(positiveOffsetCorrection);
    stringBuffer.append(TEXT_733);
    } else if (genClass.isESetFlag(genFeature)) {
    if (!genModel.isSuppressNotification()) {
    stringBuffer.append(TEXT_734);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_735);
    stringBuffer.append(genClass.getESetFlagsField(genFeature));
    stringBuffer.append(TEXT_736);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_737);
    }
    stringBuffer.append(TEXT_738);
    stringBuffer.append(genClass.getESetFlagsField(genFeature));
    stringBuffer.append(TEXT_739);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_740);
    } else {
    if (!genModel.isSuppressNotification()) {
    stringBuffer.append(TEXT_741);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_742);
    stringBuffer.append(genFeature.getUncapName());
    stringBuffer.append(TEXT_743);
    }
    stringBuffer.append(TEXT_744);
    stringBuffer.append(genFeature.getUncapName());
    stringBuffer.append(TEXT_745);
    }
    if (!genModel.isSuppressNotification()) {
    stringBuffer.append(TEXT_746);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.impl.ENotificationImpl"));
    stringBuffer.append(TEXT_747);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.notify.Notification"));
    stringBuffer.append(TEXT_748);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(positiveOffsetCorrection);
    stringBuffer.append(TEXT_749);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_750);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_751);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_752);
    }
    stringBuffer.append(TEXT_753);
    } else {
    if (!genModel.isSuppressNotification()) {
    stringBuffer.append(TEXT_754);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.impl.ENotificationImpl"));
    stringBuffer.append(TEXT_755);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.notify.Notification"));
    stringBuffer.append(TEXT_756);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(positiveOffsetCorrection);
    stringBuffer.append(TEXT_757);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_758);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_759);
    }
    }
    } else {
    if (genClass.isFlag(genFeature)) {
    if (!genModel.isSuppressNotification()) {
    if (genFeature.isBooleanType()) {
    stringBuffer.append(TEXT_760);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_761);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_762);
    stringBuffer.append(genClass.getFlagsField(genFeature));
    stringBuffer.append(TEXT_763);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_764);
    } else {
    stringBuffer.append(TEXT_765);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_766);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_767);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_768);
    stringBuffer.append(genClass.getFlagsField(genFeature));
    stringBuffer.append(TEXT_769);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_770);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_771);
    }
    }
    if (genFeature.isBooleanType()) {
    stringBuffer.append(TEXT_772);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_773);
    stringBuffer.append(genClass.getFlagsField(genFeature));
    stringBuffer.append(TEXT_774);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_775);
    stringBuffer.append(genClass.getFlagsField(genFeature));
    stringBuffer.append(TEXT_776);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_777);
    } else {
    stringBuffer.append(TEXT_778);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_779);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_780);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_781);
    stringBuffer.append(genClass.getFlagsField(genFeature));
    stringBuffer.append(TEXT_782);
    stringBuffer.append(genClass.getFlagsField(genFeature));
    stringBuffer.append(TEXT_783);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_784);
    if (isJDK50) {
    stringBuffer.append(TEXT_785);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_786);
    } else {
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_787);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_788);
    }
    stringBuffer.append(TEXT_789);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_790);
    }
    } else {
    if (!genModel.isVirtualDelegation() || genFeature.isPrimitiveType()) {
    if (!genModel.isSuppressNotification()) {
    stringBuffer.append(TEXT_791);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_792);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_793);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_794);
    }
    }
    if (genFeature.isEnumType()) {
    if (genModel.isVirtualDelegation()) {
    stringBuffer.append(TEXT_795);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_796);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_797);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_798);
    stringBuffer.append(genFeature.getEDefault());
    stringBuffer.append(TEXT_799);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_800);
    } else {
    stringBuffer.append(TEXT_801);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_802);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_803);
    stringBuffer.append(genFeature.getEDefault());
    stringBuffer.append(TEXT_804);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_805);
    }
    } else {
    if (genModel.isVirtualDelegation() && !genFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_806);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_807);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_808);
    stringBuffer.append(genFeature.getInternalTypeCast());
    stringBuffer.append(TEXT_809);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_810);
    } else {
    stringBuffer.append(TEXT_811);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_812);
    stringBuffer.append(genFeature.getInternalTypeCast());
    stringBuffer.append(TEXT_813);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_814);
    }
    }
    if (genModel.isVirtualDelegation() && !genFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_815);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_816);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(positiveOffsetCorrection);
    stringBuffer.append(TEXT_817);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_818);
    }
    }
    if (genFeature.isUnsettable()) {
    if (genModel.isVirtualDelegation() && !genFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_819);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_820);
    } else if (genClass.isESetFlag(genFeature)) {
    if (!genModel.isSuppressNotification()) {
    stringBuffer.append(TEXT_821);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_822);
    stringBuffer.append(genClass.getESetFlagsField(genFeature));
    stringBuffer.append(TEXT_823);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_824);
    }
    stringBuffer.append(TEXT_825);
    stringBuffer.append(genClass.getESetFlagsField(genFeature));
    stringBuffer.append(TEXT_826);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_827);
    } else {
    if (!genModel.isSuppressNotification()) {
    stringBuffer.append(TEXT_828);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_829);
    stringBuffer.append(genFeature.getUncapName());
    stringBuffer.append(TEXT_830);
    }
    stringBuffer.append(TEXT_831);
    stringBuffer.append(genFeature.getUncapName());
    stringBuffer.append(TEXT_832);
    }
    if (!genModel.isSuppressNotification()) {
    stringBuffer.append(TEXT_833);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.impl.ENotificationImpl"));
    stringBuffer.append(TEXT_834);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.notify.Notification"));
    stringBuffer.append(TEXT_835);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(positiveOffsetCorrection);
    stringBuffer.append(TEXT_836);
    if (genModel.isVirtualDelegation() && !genFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_837);
    stringBuffer.append(genFeature.getEDefault());
    stringBuffer.append(TEXT_838);
    stringBuffer.append(genFeature.getCapName());
    } else {
    stringBuffer.append(TEXT_839);
    stringBuffer.append(genFeature.getCapName());
    }
    stringBuffer.append(TEXT_840);
    if (genClass.isFlag(genFeature)) {
    stringBuffer.append(TEXT_841);
    stringBuffer.append(genFeature.getCapName());
    } else {
    stringBuffer.append(genFeature.getSafeName());
    }
    stringBuffer.append(TEXT_842);
    if (genModel.isVirtualDelegation() && !genFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_843);
    } else {
    stringBuffer.append(TEXT_844);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_845);
    }
    stringBuffer.append(TEXT_846);
    }
    } else {
    if (!genModel.isSuppressNotification()) {
    stringBuffer.append(TEXT_847);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.impl.ENotificationImpl"));
    stringBuffer.append(TEXT_848);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.notify.Notification"));
    stringBuffer.append(TEXT_849);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(positiveOffsetCorrection);
    stringBuffer.append(TEXT_850);
    if (genModel.isVirtualDelegation() && !genFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_851);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_852);
    stringBuffer.append(genFeature.getEDefault());
    stringBuffer.append(TEXT_853);
    stringBuffer.append(genFeature.getCapName());
    } else {
    stringBuffer.append(TEXT_854);
    stringBuffer.append(genFeature.getCapName());
    }
    stringBuffer.append(TEXT_855);
    if (genClass.isFlag(genFeature)) {
    stringBuffer.append(TEXT_856);
    stringBuffer.append(genFeature.getCapName());
    } else {
    stringBuffer.append(genFeature.getSafeName());
    }
    stringBuffer.append(TEXT_857);
    }
    }
    }
    } else if (genFeature.hasDelegateFeature()) { GenFeature delegateFeature = genFeature.getDelegateFeature();
    if (delegateFeature.isWrappedFeatureMapType()) {
    stringBuffer.append(TEXT_858);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.FeatureMap"));
    stringBuffer.append(TEXT_859);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.FeatureMap"));
    stringBuffer.append(TEXT_860);
    stringBuffer.append(delegateFeature.getAccessorName());
    stringBuffer.append(TEXT_861);
    stringBuffer.append(genFeature.getQualifiedFeatureAccessor());
    stringBuffer.append(TEXT_862);
    if (!isJDK50 && genFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_863);
    stringBuffer.append(genFeature.getObjectType(genClass));
    stringBuffer.append(TEXT_864);
    }
    stringBuffer.append(TEXT_865);
    stringBuffer.append(genFeature.getCapName());
    if (!isJDK50 && genFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_866);
    }
    stringBuffer.append(TEXT_867);
    } else {
    stringBuffer.append(TEXT_868);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.FeatureMap"));
    stringBuffer.append(TEXT_869);
    stringBuffer.append(delegateFeature.getAccessorName());
    stringBuffer.append(TEXT_870);
    stringBuffer.append(genFeature.getQualifiedFeatureAccessor());
    stringBuffer.append(TEXT_871);
    if (!isJDK50 && genFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_872);
    stringBuffer.append(genFeature.getObjectType(genClass));
    stringBuffer.append(TEXT_873);
    }
    stringBuffer.append(TEXT_874);
    stringBuffer.append(genFeature.getCapName());
    if (!isJDK50 && genFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_875);
    }
    stringBuffer.append(TEXT_876);
    }
    } else if (setAccessorOperation != null) {
    stringBuffer.append(TEXT_877);
    stringBuffer.append(setAccessorOperation.getBody(genModel.getIndentation(stringBuffer)));
    } else {
    stringBuffer.append(TEXT_878);
    stringBuffer.append(genFeature.getFormattedName());
    stringBuffer.append(TEXT_879);
    stringBuffer.append(genFeature.getFeatureKind());
    stringBuffer.append(TEXT_880);
    //Class/setGenFeature.todo.override.javajetinc
    }
    stringBuffer.append(TEXT_881);
    }
    //Class/setGenFeature.override.javajetinc
    }
    if (isImplementation && !genModel.isReflectiveDelegation() && genFeature.isBasicUnset()) {
    stringBuffer.append(TEXT_882);
    if (isJDK50) { //Class/basicUnsetGenFeature.annotations.insert.javajetinc
    }
    stringBuffer.append(TEXT_883);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.notify.NotificationChain"));
    stringBuffer.append(TEXT_884);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_885);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.notify.NotificationChain"));
    stringBuffer.append(TEXT_886);
    if (genModel.isDynamicDelegation()) {
    stringBuffer.append(TEXT_887);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.InternalEObject"));
    stringBuffer.append(TEXT_888);
    if (genFeature.isResolveProxies()) {
    stringBuffer.append(TEXT_889);
    stringBuffer.append(genFeature.getAccessorName());
    } else {
    stringBuffer.append(genFeature.getGetAccessor());
    }
    stringBuffer.append(TEXT_890);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(positiveOffsetCorrection);
    stringBuffer.append(TEXT_891);
    } else if (!genFeature.isVolatile()) {
    if (genModel.isVirtualDelegation()) {
    if (!genModel.isSuppressNotification()) {
    stringBuffer.append(TEXT_892);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_893);
    }
    stringBuffer.append(TEXT_894);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(positiveOffsetCorrection);
    stringBuffer.append(TEXT_895);
    } else {
    if (!genModel.isSuppressNotification()) {
    stringBuffer.append(TEXT_896);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_897);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_898);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_899);
    }
    stringBuffer.append(TEXT_900);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_901);
    }
    if (genModel.isVirtualDelegation()) {
    if (!genModel.isSuppressNotification()) {
    stringBuffer.append(TEXT_902);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_903);
    }
    } else if (genClass.isESetFlag(genFeature)) {
    if (!genModel.isSuppressNotification()) {
    stringBuffer.append(TEXT_904);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_905);
    stringBuffer.append(genClass.getESetFlagsField(genFeature));
    stringBuffer.append(TEXT_906);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_907);
    }
    stringBuffer.append(TEXT_908);
    stringBuffer.append(genClass.getESetFlagsField(genFeature));
    stringBuffer.append(TEXT_909);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_910);
    } else {
    if (!genModel.isSuppressNotification()) {
    stringBuffer.append(TEXT_911);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_912);
    stringBuffer.append(genFeature.getUncapName());
    stringBuffer.append(TEXT_913);
    }
    stringBuffer.append(TEXT_914);
    stringBuffer.append(genFeature.getUncapName());
    stringBuffer.append(TEXT_915);
    }
    if (!genModel.isSuppressNotification()) {
    stringBuffer.append(TEXT_916);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.impl.ENotificationImpl"));
    stringBuffer.append(TEXT_917);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.impl.ENotificationImpl"));
    stringBuffer.append(TEXT_918);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.notify.Notification"));
    stringBuffer.append(TEXT_919);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(positiveOffsetCorrection);
    stringBuffer.append(TEXT_920);
    if (genModel.isVirtualDelegation()) {
    stringBuffer.append(TEXT_921);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_922);
    } else {
    stringBuffer.append(TEXT_923);
    stringBuffer.append(genFeature.getCapName());
    }
    stringBuffer.append(TEXT_924);
    if (genModel.isVirtualDelegation()) {
    stringBuffer.append(TEXT_925);
    } else {
    stringBuffer.append(TEXT_926);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_927);
    }
    stringBuffer.append(TEXT_928);
    }
    } else {
    stringBuffer.append(TEXT_929);
    stringBuffer.append(genFeature.getFormattedName());
    stringBuffer.append(TEXT_930);
    stringBuffer.append(genFeature.getFeatureKind());
    stringBuffer.append(TEXT_931);
    //Class/basicUnsetGenFeature.todo.override.javajetinc
    }
    stringBuffer.append(TEXT_932);
    //Class.basicUnsetGenFeature.override.javajetinc
    }
    if (genFeature.isUnset() && (isImplementation || !genFeature.isSuppressedUnsetVisibility())) {
    if (isInterface) {
    stringBuffer.append(TEXT_933);
    stringBuffer.append(genClass.getQualifiedInterfaceName());
    stringBuffer.append(TEXT_934);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_935);
    stringBuffer.append(genFeature.getFormattedName());
    stringBuffer.append(TEXT_936);
    stringBuffer.append(genFeature.getFeatureKind());
    stringBuffer.append(TEXT_937);
    stringBuffer.append(TEXT_938);
    if (!genFeature.isSuppressedIsSetVisibility()) {
    stringBuffer.append(TEXT_939);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_940);
    }
    stringBuffer.append(TEXT_941);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_942);
    if (!genFeature.isListType() && !genFeature.isSuppressedSetVisibility()) {
    stringBuffer.append(TEXT_943);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_944);
    stringBuffer.append(genFeature.getRawImportedBoundType());
    stringBuffer.append(TEXT_945);
    }
    stringBuffer.append(TEXT_946);
    //Class/unsetGenFeature.javadoc.override.javajetinc
    } else {
    stringBuffer.append(TEXT_947);
    if (isJDK50) { //Class/unsetGenFeature.annotations.insert.javajetinc
    }
    }
    if (!isImplementation) {
    stringBuffer.append(TEXT_948);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_949);
    } else {
    stringBuffer.append(TEXT_950);
    stringBuffer.append(genFeature.getAccessorName());
    if (genClass.hasCollidingUnsetAccessorOperation(genFeature)) {
    stringBuffer.append(TEXT_951);
    }
    stringBuffer.append(TEXT_952);
    if (genModel.isDynamicDelegation()) {
    stringBuffer.append(TEXT_953);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(TEXT_954);
    stringBuffer.append(genFeature.getQualifiedFeatureAccessor());
    stringBuffer.append(TEXT_955);
    } else if (genModel.isReflectiveDelegation()) {
    stringBuffer.append(TEXT_956);
    stringBuffer.append(genFeature.getQualifiedFeatureAccessor());
    stringBuffer.append(TEXT_957);
    } else if (genFeature.hasSettingDelegate()) {
    stringBuffer.append(TEXT_958);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_959);
    } else if (!genFeature.isVolatile()) {
    if (genFeature.isListType()) {
    if (genModel.isVirtualDelegation()) {
    stringBuffer.append(TEXT_960);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_961);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_962);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_963);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(positiveOffsetCorrection);
    stringBuffer.append(TEXT_964);
    }
    stringBuffer.append(TEXT_965);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_966);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.InternalEList"));
    stringBuffer.append(TEXT_967);
    stringBuffer.append(singleWildcard);
    stringBuffer.append(TEXT_968);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_969);
    } else if (genFeature.isBidirectional() || genFeature.isEffectiveContains()) {
    if (genModel.isVirtualDelegation()) {
    stringBuffer.append(TEXT_970);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_971);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_972);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_973);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(positiveOffsetCorrection);
    stringBuffer.append(TEXT_974);
    }
    stringBuffer.append(TEXT_975);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_976);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.notify.NotificationChain"));
    stringBuffer.append(TEXT_977);
    if (!genFeature.isBidirectional()) {
    stringBuffer.append(TEXT_978);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.InternalEObject"));
    stringBuffer.append(TEXT_979);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_980);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(negativeOffsetCorrection);
    stringBuffer.append(TEXT_981);
    } else { GenFeature reverseFeature = genFeature.getReverse(); GenClass targetClass = reverseFeature.getGenClass(); String reverseOffsetCorrection = targetClass.hasOffsetCorrection() ? " + " + genClass.getOffsetCorrectionField(genFeature) : "";
    stringBuffer.append(TEXT_982);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.InternalEObject"));
    stringBuffer.append(TEXT_983);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_984);
    stringBuffer.append(targetClass.getQualifiedFeatureID(reverseFeature));
    stringBuffer.append(reverseOffsetCorrection);
    stringBuffer.append(TEXT_985);
    stringBuffer.append(targetClass.getRawImportedInterfaceName());
    stringBuffer.append(TEXT_986);
    }
    stringBuffer.append(TEXT_987);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_988);
    if (genModel.isVirtualDelegation()) {
    stringBuffer.append(TEXT_989);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_990);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(positiveOffsetCorrection);
    stringBuffer.append(TEXT_991);
    } else if (genClass.isESetFlag(genFeature)) {
    if (!genModel.isSuppressNotification()) {
    stringBuffer.append(TEXT_992);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_993);
    stringBuffer.append(genClass.getESetFlagsField(genFeature));
    stringBuffer.append(TEXT_994);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_995);
    }
    stringBuffer.append(TEXT_996);
    stringBuffer.append(genClass.getESetFlagsField(genFeature));
    stringBuffer.append(TEXT_997);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_998);
    } else {
    if (!genModel.isSuppressNotification()) {
    stringBuffer.append(TEXT_999);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_1000);
    stringBuffer.append(genFeature.getUncapName());
    stringBuffer.append(TEXT_1001);
    }
    stringBuffer.append(TEXT_1002);
    stringBuffer.append(genFeature.getUncapName());
    stringBuffer.append(TEXT_1003);
    }
    if (!genModel.isSuppressNotification()) {
    stringBuffer.append(TEXT_1004);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.impl.ENotificationImpl"));
    stringBuffer.append(TEXT_1005);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.notify.Notification"));
    stringBuffer.append(TEXT_1006);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(positiveOffsetCorrection);
    stringBuffer.append(TEXT_1007);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_1008);
    }
    stringBuffer.append(TEXT_1009);
    } else {
    if (genClass.isFlag(genFeature)) {
    if (!genModel.isSuppressNotification()) {
    if (genFeature.isBooleanType()) {
    stringBuffer.append(TEXT_1010);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_1011);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_1012);
    stringBuffer.append(genClass.getFlagsField(genFeature));
    stringBuffer.append(TEXT_1013);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_1014);
    } else {
    stringBuffer.append(TEXT_1015);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_1016);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_1017);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_1018);
    stringBuffer.append(genClass.getFlagsField(genFeature));
    stringBuffer.append(TEXT_1019);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_1020);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_1021);
    }
    }
    } else if (genModel.isVirtualDelegation() && !genFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_1022);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_1023);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(positiveOffsetCorrection);
    stringBuffer.append(TEXT_1024);
    } else {
    if (!genModel.isSuppressNotification()) {
    stringBuffer.append(TEXT_1025);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_1026);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_1027);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_1028);
    }
    }
    if (!genModel.isSuppressNotification()) {
    if (genModel.isVirtualDelegation() && !genFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_1029);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_1030);
    } else if (genClass.isESetFlag(genFeature)) {
    stringBuffer.append(TEXT_1031);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_1032);
    stringBuffer.append(genClass.getESetFlagsField(genFeature));
    stringBuffer.append(TEXT_1033);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_1034);
    } else {
    stringBuffer.append(TEXT_1035);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_1036);
    stringBuffer.append(genFeature.getUncapName());
    stringBuffer.append(TEXT_1037);
    }
    }
    if (genFeature.isReferenceType()) {
    stringBuffer.append(TEXT_1038);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_1039);
    if (!genModel.isVirtualDelegation()) {
    if (genClass.isESetFlag(genFeature)) {
    stringBuffer.append(TEXT_1040);
    stringBuffer.append(genClass.getESetFlagsField(genFeature));
    stringBuffer.append(TEXT_1041);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_1042);
    } else {
    stringBuffer.append(TEXT_1043);
    stringBuffer.append(genFeature.getUncapName());
    stringBuffer.append(TEXT_1044);
    }
    }
    if (!genModel.isSuppressNotification()) {
    stringBuffer.append(TEXT_1045);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.impl.ENotificationImpl"));
    stringBuffer.append(TEXT_1046);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.notify.Notification"));
    stringBuffer.append(TEXT_1047);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(positiveOffsetCorrection);
    stringBuffer.append(TEXT_1048);
    if (genModel.isVirtualDelegation()) {
    stringBuffer.append(TEXT_1049);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_1050);
    } else {
    stringBuffer.append(TEXT_1051);
    stringBuffer.append(genFeature.getCapName());
    }
    stringBuffer.append(TEXT_1052);
    if (genModel.isVirtualDelegation()) {
    stringBuffer.append(TEXT_1053);
    } else {
    stringBuffer.append(TEXT_1054);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_1055);
    }
    stringBuffer.append(TEXT_1056);
    }
    } else {
    if (genClass.isFlag(genFeature)) {
    if (genFeature.isBooleanType()) {
    stringBuffer.append(TEXT_1057);
    stringBuffer.append(genFeature.getEDefault());
    stringBuffer.append(TEXT_1058);
    stringBuffer.append(genClass.getFlagsField(genFeature));
    stringBuffer.append(TEXT_1059);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_1060);
    stringBuffer.append(genClass.getFlagsField(genFeature));
    stringBuffer.append(TEXT_1061);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_1062);
    } else {
    stringBuffer.append(TEXT_1063);
    stringBuffer.append(genClass.getFlagsField(genFeature));
    stringBuffer.append(TEXT_1064);
    stringBuffer.append(genClass.getFlagsField(genFeature));
    stringBuffer.append(TEXT_1065);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_1066);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_1067);
    }
    } else if (!genModel.isVirtualDelegation() || genFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_1068);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_1069);
    stringBuffer.append(genFeature.getEDefault());
    stringBuffer.append(TEXT_1070);
    }
    if (!genModel.isVirtualDelegation() || genFeature.isPrimitiveType()) {
    if (genClass.isESetFlag(genFeature)) {
    stringBuffer.append(TEXT_1071);
    stringBuffer.append(genClass.getESetFlagsField(genFeature));
    stringBuffer.append(TEXT_1072);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_1073);
    } else {
    stringBuffer.append(TEXT_1074);
    stringBuffer.append(genFeature.getUncapName());
    stringBuffer.append(TEXT_1075);
    }
    }
    if (!genModel.isSuppressNotification()) {
    stringBuffer.append(TEXT_1076);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.impl.ENotificationImpl"));
    stringBuffer.append(TEXT_1077);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.notify.Notification"));
    stringBuffer.append(TEXT_1078);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(positiveOffsetCorrection);
    stringBuffer.append(TEXT_1079);
    if (genModel.isVirtualDelegation() && !genFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_1080);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_1081);
    stringBuffer.append(genFeature.getEDefault());
    } else {
    stringBuffer.append(TEXT_1082);
    stringBuffer.append(genFeature.getCapName());
    }
    stringBuffer.append(TEXT_1083);
    stringBuffer.append(genFeature.getEDefault());
    stringBuffer.append(TEXT_1084);
    if (genModel.isVirtualDelegation() && !genFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_1085);
    } else {
    stringBuffer.append(TEXT_1086);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_1087);
    }
    stringBuffer.append(TEXT_1088);
    }
    }
    }
    } else if (genFeature.hasDelegateFeature()) { GenFeature delegateFeature = genFeature.getDelegateFeature();
    if (delegateFeature.isWrappedFeatureMapType()) {
    stringBuffer.append(TEXT_1089);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.FeatureMap"));
    stringBuffer.append(TEXT_1090);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.FeatureMap"));
    stringBuffer.append(TEXT_1091);
    stringBuffer.append(delegateFeature.getAccessorName());
    stringBuffer.append(TEXT_1092);
    stringBuffer.append(genFeature.getQualifiedFeatureAccessor());
    stringBuffer.append(TEXT_1093);
    } else {
    stringBuffer.append(TEXT_1094);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.FeatureMap"));
    stringBuffer.append(TEXT_1095);
    stringBuffer.append(delegateFeature.getAccessorName());
    stringBuffer.append(TEXT_1096);
    stringBuffer.append(genFeature.getQualifiedFeatureAccessor());
    stringBuffer.append(TEXT_1097);
    }
    } else if (genClass.getUnsetAccessorOperation(genFeature) != null) {
    stringBuffer.append(TEXT_1098);
    stringBuffer.append(genClass.getUnsetAccessorOperation(genFeature).getBody(genModel.getIndentation(stringBuffer)));
    } else {
    stringBuffer.append(TEXT_1099);
    stringBuffer.append(genFeature.getFormattedName());
    stringBuffer.append(TEXT_1100);
    stringBuffer.append(genFeature.getFeatureKind());
    stringBuffer.append(TEXT_1101);
    //Class/unsetGenFeature.todo.override.javajetinc
    }
    stringBuffer.append(TEXT_1102);
    }
    //Class/unsetGenFeature.override.javajetinc
    }
    if (genFeature.isIsSet() && (isImplementation || !genFeature.isSuppressedIsSetVisibility())) {
    if (isInterface) {
    stringBuffer.append(TEXT_1103);
    stringBuffer.append(genClass.getQualifiedInterfaceName());
    stringBuffer.append(TEXT_1104);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_1105);
    stringBuffer.append(genFeature.getFormattedName());
    stringBuffer.append(TEXT_1106);
    stringBuffer.append(genFeature.getFeatureKind());
    stringBuffer.append(TEXT_1107);
    stringBuffer.append(TEXT_1108);
    stringBuffer.append(genFeature.getFormattedName());
    stringBuffer.append(TEXT_1109);
    stringBuffer.append(genFeature.getFeatureKind());
    stringBuffer.append(TEXT_1110);
    if (genFeature.isChangeable() && !genFeature.isSuppressedUnsetVisibility()) {
    stringBuffer.append(TEXT_1111);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_1112);
    }
    stringBuffer.append(TEXT_1113);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_1114);
    if (!genFeature.isListType() && genFeature.isChangeable() && !genFeature.isSuppressedSetVisibility()) {
    stringBuffer.append(TEXT_1115);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_1116);
    stringBuffer.append(genFeature.getRawImportedBoundType());
    stringBuffer.append(TEXT_1117);
    }
    stringBuffer.append(TEXT_1118);
    //Class/isSetGenFeature.javadoc.override.javajetinc
    } else {
    stringBuffer.append(TEXT_1119);
    if (isJDK50) { //Class/isSetGenFeature.annotations.insert.javajetinc
    }
    }
    if (!isImplementation) {
    stringBuffer.append(TEXT_1120);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_1121);
    } else {
    stringBuffer.append(TEXT_1122);
    stringBuffer.append(genFeature.getAccessorName());
    if (genClass.hasCollidingIsSetAccessorOperation(genFeature)) {
    stringBuffer.append(TEXT_1123);
    }
    stringBuffer.append(TEXT_1124);
    if (genModel.isDynamicDelegation()) {
    stringBuffer.append(TEXT_1125);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(TEXT_1126);
    stringBuffer.append(genFeature.getQualifiedFeatureAccessor());
    stringBuffer.append(TEXT_1127);
    } else if (genModel.isReflectiveDelegation()) {
    stringBuffer.append(TEXT_1128);
    stringBuffer.append(genFeature.getQualifiedFeatureAccessor());
    stringBuffer.append(TEXT_1129);
    } else if (genFeature.hasSettingDelegate()) {
    stringBuffer.append(TEXT_1130);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_1131);
    } else if (!genFeature.isVolatile()) {
    if (genFeature.isListType()) {
    if (genModel.isVirtualDelegation()) {
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
    }
    stringBuffer.append(TEXT_1137);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_1138);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.InternalEList"));
    stringBuffer.append(TEXT_1139);
    stringBuffer.append(singleWildcard);
    stringBuffer.append(TEXT_1140);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_1141);
    } else {
    if (genModel.isVirtualDelegation() && !genFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_1142);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(positiveOffsetCorrection);
    stringBuffer.append(TEXT_1143);
    } else if (genClass.isESetFlag(genFeature)) {
    stringBuffer.append(TEXT_1144);
    stringBuffer.append(genClass.getESetFlagsField(genFeature));
    stringBuffer.append(TEXT_1145);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_1146);
    } else {
    stringBuffer.append(TEXT_1147);
    stringBuffer.append(genFeature.getUncapName());
    stringBuffer.append(TEXT_1148);
    }
    }
    } else if (genFeature.hasDelegateFeature()) { GenFeature delegateFeature = genFeature.getDelegateFeature();
    if (delegateFeature.isWrappedFeatureMapType()) {
    stringBuffer.append(TEXT_1149);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.FeatureMap"));
    stringBuffer.append(TEXT_1150);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.FeatureMap"));
    stringBuffer.append(TEXT_1151);
    stringBuffer.append(delegateFeature.getAccessorName());
    stringBuffer.append(TEXT_1152);
    stringBuffer.append(genFeature.getQualifiedFeatureAccessor());
    stringBuffer.append(TEXT_1153);
    } else {
    stringBuffer.append(TEXT_1154);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.FeatureMap"));
    stringBuffer.append(TEXT_1155);
    stringBuffer.append(delegateFeature.getAccessorName());
    stringBuffer.append(TEXT_1156);
    stringBuffer.append(genFeature.getQualifiedFeatureAccessor());
    stringBuffer.append(TEXT_1157);
    }
    } else if (genClass.getIsSetAccessorOperation(genFeature) != null) {
    stringBuffer.append(TEXT_1158);
    stringBuffer.append(genClass.getIsSetAccessorOperation(genFeature).getBody(genModel.getIndentation(stringBuffer)));
    } else {
    stringBuffer.append(TEXT_1159);
    stringBuffer.append(genFeature.getFormattedName());
    stringBuffer.append(TEXT_1160);
    stringBuffer.append(genFeature.getFeatureKind());
    stringBuffer.append(TEXT_1161);
    //Class/isSetGenFeature.todo.override.javajetinc
    }
    stringBuffer.append(TEXT_1162);
    }
    //Class/isSetGenFeature.override.javajetinc
    }
    //Class/genFeature.override.javajetinc
    }//for
    }}.run();
    for (GenOperation genOperation : (isImplementation ? genClass.getImplementedGenOperations() : genClass.getDeclaredGenOperations())) {
    if (isImplementation) {
    if (genOperation.isInvariant() && genOperation.hasInvariantExpression()) {
    stringBuffer.append(TEXT_1163);
    stringBuffer.append(genOperation.getName());
    stringBuffer.append(TEXT_1164);
    stringBuffer.append(genOperation.getParameterTypes(", "));
    stringBuffer.append(TEXT_1165);
    stringBuffer.append(genOperation.getFormattedName());
    stringBuffer.append(TEXT_1166);
    stringBuffer.append(genOperation.getName());
    stringBuffer.append(TEXT_1167);
    stringBuffer.append(genOperation.getParameterTypes(", "));
    stringBuffer.append(TEXT_1168);
    stringBuffer.append(genModel.getImportedName("java.lang.String"));
    stringBuffer.append(TEXT_1169);
    stringBuffer.append(CodeGenUtil.upperName(genClass.getUniqueName(genOperation), genModel.getLocale()));
    stringBuffer.append(TEXT_1170);
    stringBuffer.append(genOperation.getInvariantExpression("\t\t"));
    stringBuffer.append(TEXT_1171);
    stringBuffer.append(genModel.getNonNLS());
    stringBuffer.append(TEXT_1172);
    } else if (genOperation.hasInvocationDelegate()) {
    stringBuffer.append(TEXT_1173);
    stringBuffer.append(genOperation.getName());
    stringBuffer.append(TEXT_1174);
    stringBuffer.append(genOperation.getParameterTypes(", "));
    stringBuffer.append(TEXT_1175);
    stringBuffer.append(genOperation.getFormattedName());
    stringBuffer.append(TEXT_1176);
    stringBuffer.append(genOperation.getName());
    stringBuffer.append(TEXT_1177);
    stringBuffer.append(genOperation.getParameterTypes(", "));
    stringBuffer.append(TEXT_1178);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.EOperation"));
    stringBuffer.append(TEXT_1179);
    stringBuffer.append(CodeGenUtil.upperName(genClass.getUniqueName(genOperation), genModel.getLocale()));
    stringBuffer.append(TEXT_1180);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.EOperation"));
    stringBuffer.append(TEXT_1181);
    stringBuffer.append(genOperation.getQualifiedOperationAccessor());
    stringBuffer.append(TEXT_1182);
    }
    }
    if (isInterface) {
    stringBuffer.append(TEXT_1183);
    stringBuffer.append(TEXT_1184);
    if (genOperation.hasDocumentation() || genOperation.hasParameterDocumentation()) {
    stringBuffer.append(TEXT_1185);
    if (genOperation.hasDocumentation()) {
    stringBuffer.append(TEXT_1186);
    stringBuffer.append(genOperation.getDocumentation(genModel.getIndentation(stringBuffer)));
    }
    for (GenParameter genParameter : genOperation.getGenParameters()) {
    if (genParameter.hasDocumentation()) { String documentation = genParameter.getDocumentation("");
    if (documentation.contains("\n") || documentation.contains("\r")) {
    stringBuffer.append(TEXT_1187);
    stringBuffer.append(genParameter.getName());
    stringBuffer.append(TEXT_1188);
    stringBuffer.append(genParameter.getDocumentation(genModel.getIndentation(stringBuffer)));
    } else {
    stringBuffer.append(TEXT_1189);
    stringBuffer.append(genParameter.getName());
    stringBuffer.append(TEXT_1190);
    stringBuffer.append(genParameter.getDocumentation(genModel.getIndentation(stringBuffer)));
    }
    }
    }
    stringBuffer.append(TEXT_1191);
    }
    if (!genModel.isSuppressEMFModelTags()) { boolean first = true; for (StringTokenizer stringTokenizer = new StringTokenizer(genOperation.getModelInfo(), "\n\r"); stringTokenizer.hasMoreTokens(); ) { String modelInfo = stringTokenizer.nextToken(); if (first) { first = false;
    stringBuffer.append(TEXT_1192);
    stringBuffer.append(modelInfo);
    } else {
    stringBuffer.append(TEXT_1193);
    stringBuffer.append(modelInfo);
    }} if (first) {
    stringBuffer.append(TEXT_1194);
    }}
    stringBuffer.append(TEXT_1195);
    //Class/genOperation.javadoc.override.javajetinc
    } else {
    stringBuffer.append(TEXT_1196);
    if (isJDK50) { //Class/genOperation.annotations.insert.javajetinc
    }
    }
    if (!isImplementation) {
    stringBuffer.append(TEXT_1197);
    stringBuffer.append(genOperation.getTypeParameters(genClass));
    stringBuffer.append(genOperation.getImportedType(genClass));
    stringBuffer.append(TEXT_1198);
    stringBuffer.append(genOperation.getName());
    stringBuffer.append(TEXT_1199);
    stringBuffer.append(genOperation.getParameters(genClass));
    stringBuffer.append(TEXT_1200);
    stringBuffer.append(genOperation.getThrows(genClass));
    stringBuffer.append(TEXT_1201);
    } else {
    if (genModel.useGenerics() && !genOperation.hasBody() && !genOperation.isInvariant() && genOperation.hasInvocationDelegate() && genOperation.isUncheckedCast(genClass)) {
    stringBuffer.append(TEXT_1202);
    }
    stringBuffer.append(TEXT_1203);
    stringBuffer.append(genOperation.getTypeParameters(genClass));
    stringBuffer.append(genOperation.getImportedType(genClass));
    stringBuffer.append(TEXT_1204);
    stringBuffer.append(genOperation.getName());
    stringBuffer.append(TEXT_1205);
    stringBuffer.append(genOperation.getParameters(genClass));
    stringBuffer.append(TEXT_1206);
    stringBuffer.append(genOperation.getThrows(genClass));
    stringBuffer.append(TEXT_1207);
    if (genOperation.hasBody()) {
    stringBuffer.append(TEXT_1208);
    stringBuffer.append(genOperation.getBody(genModel.getIndentation(stringBuffer)));
    } else if (genOperation.isInvariant()) {GenClass opClass = genOperation.getGenClass(); String diagnostics = genOperation.getGenParameters().get(0).getName(); String context = genOperation.getGenParameters().get(1).getName();
    if (genOperation.hasInvariantExpression()) {
    stringBuffer.append(TEXT_1209);
    stringBuffer.append(opClass.getGenPackage().getImportedValidatorClassName());
    stringBuffer.append(TEXT_1210);
    stringBuffer.append(genClass.getQualifiedClassifierAccessor());
    stringBuffer.append(TEXT_1211);
    stringBuffer.append(diagnostics);
    stringBuffer.append(TEXT_1212);
    stringBuffer.append(context);
    stringBuffer.append(TEXT_1213);
    stringBuffer.append(genOperation.getValidationDelegate());
    stringBuffer.append(TEXT_1214);
    stringBuffer.append(genModel.getNonNLS());
    stringBuffer.append(TEXT_1215);
    stringBuffer.append(genOperation.getQualifiedOperationAccessor());
    stringBuffer.append(TEXT_1216);
    stringBuffer.append(CodeGenUtil.upperName(genClass.getUniqueName(genOperation), genModel.getLocale()));
    stringBuffer.append(TEXT_1217);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.util.Diagnostic"));
    stringBuffer.append(TEXT_1218);
    stringBuffer.append(opClass.getGenPackage().getImportedValidatorClassName());
    stringBuffer.append(TEXT_1219);
    stringBuffer.append(opClass.getGenPackage().getImportedValidatorClassName());
    stringBuffer.append(TEXT_1220);
    stringBuffer.append(opClass.getOperationID(genOperation));
    stringBuffer.append(TEXT_1221);
    } else {
    stringBuffer.append(TEXT_1222);
    stringBuffer.append(diagnostics);
    stringBuffer.append(TEXT_1223);
    stringBuffer.append(diagnostics);
    stringBuffer.append(TEXT_1224);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.util.BasicDiagnostic"));
    stringBuffer.append(TEXT_1225);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.util.Diagnostic"));
    stringBuffer.append(TEXT_1226);
    stringBuffer.append(opClass.getGenPackage().getImportedValidatorClassName());
    stringBuffer.append(TEXT_1227);
    stringBuffer.append(opClass.getGenPackage().getImportedValidatorClassName());
    stringBuffer.append(TEXT_1228);
    stringBuffer.append(opClass.getOperationID(genOperation));
    stringBuffer.append(TEXT_1229);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.plugin.EcorePlugin"));
    stringBuffer.append(TEXT_1230);
    stringBuffer.append(genOperation.getName());
    stringBuffer.append(TEXT_1231);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.EObjectValidator"));
    stringBuffer.append(TEXT_1232);
    stringBuffer.append(context);
    stringBuffer.append(TEXT_1233);
    stringBuffer.append(genModel.getNonNLS());
    stringBuffer.append(genModel.getNonNLS(2));
    stringBuffer.append(TEXT_1234);
    }
    } else if (genOperation.hasInvocationDelegate()) { int size = genOperation.getGenParameters().size();
    stringBuffer.append(TEXT_1235);
    if (genOperation.isVoid()) {
    stringBuffer.append(TEXT_1236);
    stringBuffer.append(CodeGenUtil.upperName(genClass.getUniqueName(genOperation), genModel.getLocale()));
    stringBuffer.append(TEXT_1237);
    if (size > 0) {
    stringBuffer.append(TEXT_1238);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.util.BasicEList"));
    stringBuffer.append(TEXT_1239);
    stringBuffer.append(size);
    stringBuffer.append(TEXT_1240);
    stringBuffer.append(genOperation.getParametersArray(genClass));
    stringBuffer.append(TEXT_1241);
    } else {
    stringBuffer.append(TEXT_1242);
    }
    stringBuffer.append(TEXT_1243);
    } else {
    stringBuffer.append(TEXT_1244);
    if (!isJDK50 && genOperation.isPrimitiveType()) {
    stringBuffer.append(TEXT_1245);
    }
    stringBuffer.append(TEXT_1246);
    stringBuffer.append(genOperation.getObjectType(genClass));
    stringBuffer.append(TEXT_1247);
    stringBuffer.append(CodeGenUtil.upperName(genClass.getUniqueName(genOperation), genModel.getLocale()));
    stringBuffer.append(TEXT_1248);
    if (size > 0) {
    stringBuffer.append(TEXT_1249);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.util.BasicEList"));
    stringBuffer.append(TEXT_1250);
    stringBuffer.append(size);
    stringBuffer.append(TEXT_1251);
    stringBuffer.append(genOperation.getParametersArray(genClass));
    stringBuffer.append(TEXT_1252);
    } else {
    stringBuffer.append(TEXT_1253);
    }
    stringBuffer.append(TEXT_1254);
    if (!isJDK50 && genOperation.isPrimitiveType()) {
    stringBuffer.append(TEXT_1255);
    stringBuffer.append(genOperation.getPrimitiveValueFunction());
    stringBuffer.append(TEXT_1256);
    }
    stringBuffer.append(TEXT_1257);
    }
    stringBuffer.append(TEXT_1258);
    stringBuffer.append(genModel.getImportedName(isGWT ? "org.eclipse.emf.common.util.InvocationTargetException" : "java.lang.reflect.InvocationTargetException"));
    stringBuffer.append(TEXT_1259);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.util.WrappedException"));
    stringBuffer.append(TEXT_1260);
    } else {
    stringBuffer.append(TEXT_1261);
    //Class/implementedGenOperation.todo.override.javajetinc
    }
    stringBuffer.append(TEXT_1262);
    }
    //Class/implementedGenOperation.override.javajetinc
    }//for
    if (isImplementation && !genModel.isReflectiveDelegation() && genClass.implementsAny(genClass.getEInverseAddGenFeatures())) {
    stringBuffer.append(TEXT_1263);
    if (genModel.useGenerics()) {
    for (GenFeature genFeature : genClass.getEInverseAddGenFeatures()) {
    if (genFeature.isUncheckedCast(genClass)) {
    stringBuffer.append(TEXT_1264);
    break; }
    }
    }
    if (genModel.useClassOverrideAnnotation()) {
    stringBuffer.append(TEXT_1265);
    }
    stringBuffer.append(TEXT_1266);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.notify.NotificationChain"));
    stringBuffer.append(TEXT_1267);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.InternalEObject"));
    stringBuffer.append(TEXT_1268);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.notify.NotificationChain"));
    stringBuffer.append(TEXT_1269);
    stringBuffer.append(negativeOffsetCorrection);
    stringBuffer.append(TEXT_1270);
    for (GenFeature genFeature : genClass.getEInverseAddGenFeatures()) {
    stringBuffer.append(TEXT_1271);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(TEXT_1272);
    if (genFeature.isListType()) { String cast = "("  + genModel.getImportedName("org.eclipse.emf.ecore.util.InternalEList") + (!genModel.useGenerics() ? ")" : "<" + genModel.getImportedName("org.eclipse.emf.ecore.InternalEObject") + ">)(" + genModel.getImportedName("org.eclipse.emf.ecore.util.InternalEList") + "<?>)");
    if (genFeature.isMapType() && genFeature.isEffectiveSuppressEMFTypes()) {
    stringBuffer.append(TEXT_1273);
    stringBuffer.append(cast);
    stringBuffer.append(TEXT_1274);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.util.EMap"));
    stringBuffer.append(TEXT_1275);
    stringBuffer.append(genFeature.getImportedMapTemplateArguments(genClass));
    stringBuffer.append(TEXT_1276);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_1277);
    } else {
    stringBuffer.append(TEXT_1278);
    stringBuffer.append(cast);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_1279);
    }
    } else if (genFeature.isContainer()) {
    stringBuffer.append(TEXT_1280);
    if (genFeature.isBasicSet()) {
    stringBuffer.append(TEXT_1281);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_1282);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_1283);
    } else {
    stringBuffer.append(TEXT_1284);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(positiveOffsetCorrection);
    stringBuffer.append(TEXT_1285);
    }
    } else {
    if (genClass.getImplementingGenModel(genFeature).isVirtualDelegation()) {
    stringBuffer.append(TEXT_1286);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_1287);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_1288);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_1289);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(positiveOffsetCorrection);
    stringBuffer.append(TEXT_1290);
    } else if (genFeature.isVolatile() || genClass.getImplementingGenModel(genFeature).isDynamicDelegation()) {
    stringBuffer.append(TEXT_1291);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_1292);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_1293);
    if (genFeature.isResolveProxies()) {
    stringBuffer.append(TEXT_1294);
    stringBuffer.append(genFeature.getAccessorName());
    } else {
    stringBuffer.append(genFeature.getGetAccessor());
    }
    stringBuffer.append(TEXT_1295);
    }
    stringBuffer.append(TEXT_1296);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_1297);
    if (genFeature.isEffectiveContains()) {
    stringBuffer.append(TEXT_1298);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.InternalEObject"));
    stringBuffer.append(TEXT_1299);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_1300);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(negativeOffsetCorrection);
    stringBuffer.append(TEXT_1301);
    } else { GenFeature reverseFeature = genFeature.getReverse(); GenClass targetClass = reverseFeature.getGenClass(); String reverseOffsetCorrection = targetClass.hasOffsetCorrection() ? " + " + genClass.getOffsetCorrectionField(genFeature) : "";
    stringBuffer.append(TEXT_1302);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.InternalEObject"));
    stringBuffer.append(TEXT_1303);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_1304);
    stringBuffer.append(targetClass.getQualifiedFeatureID(reverseFeature));
    stringBuffer.append(reverseOffsetCorrection);
    stringBuffer.append(TEXT_1305);
    stringBuffer.append(targetClass.getRawImportedInterfaceName());
    stringBuffer.append(TEXT_1306);
    }
    stringBuffer.append(TEXT_1307);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_1308);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_1309);
    }
    }
    stringBuffer.append(TEXT_1310);
    if (genModel.isMinimalReflectiveMethods()) {
    stringBuffer.append(TEXT_1311);
    } else {
    stringBuffer.append(TEXT_1312);
    }
    stringBuffer.append(TEXT_1313);
    }
    if (isImplementation && !genModel.isReflectiveDelegation() && genClass.implementsAny(genClass.getEInverseRemoveGenFeatures())) {
    stringBuffer.append(TEXT_1314);
    if (genModel.useClassOverrideAnnotation()) {
    stringBuffer.append(TEXT_1315);
    }
    stringBuffer.append(TEXT_1316);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.notify.NotificationChain"));
    stringBuffer.append(TEXT_1317);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.InternalEObject"));
    stringBuffer.append(TEXT_1318);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.notify.NotificationChain"));
    stringBuffer.append(TEXT_1319);
    stringBuffer.append(negativeOffsetCorrection);
    stringBuffer.append(TEXT_1320);
    for (GenFeature genFeature : genClass.getEInverseRemoveGenFeatures()) {
    stringBuffer.append(TEXT_1321);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(TEXT_1322);
    if (genFeature.isListType()) {
    if (genFeature.isMapType() && genFeature.isEffectiveSuppressEMFTypes()) {
    stringBuffer.append(TEXT_1323);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.InternalEList"));
    stringBuffer.append(singleWildcard);
    stringBuffer.append(TEXT_1324);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.util.EMap"));
    stringBuffer.append(TEXT_1325);
    stringBuffer.append(genFeature.getImportedMapTemplateArguments(genClass));
    stringBuffer.append(TEXT_1326);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_1327);
    } else if (genFeature.isWrappedFeatureMapType()) {
    stringBuffer.append(TEXT_1328);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.InternalEList"));
    stringBuffer.append(singleWildcard);
    stringBuffer.append(TEXT_1329);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.FeatureMap"));
    stringBuffer.append(TEXT_1330);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_1331);
    } else {
    stringBuffer.append(TEXT_1332);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.InternalEList"));
    stringBuffer.append(singleWildcard);
    stringBuffer.append(TEXT_1333);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_1334);
    }
    } else if (genFeature.isContainer() && !genFeature.isBasicSet()) {
    stringBuffer.append(TEXT_1335);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(positiveOffsetCorrection);
    stringBuffer.append(TEXT_1336);
    } else if (genFeature.isUnsettable()) {
    stringBuffer.append(TEXT_1337);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_1338);
    } else {
    stringBuffer.append(TEXT_1339);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_1340);
    }
    }
    stringBuffer.append(TEXT_1341);
    if (genModel.isMinimalReflectiveMethods()) {
    stringBuffer.append(TEXT_1342);
    } else {
    stringBuffer.append(TEXT_1343);
    }
    stringBuffer.append(TEXT_1344);
    }
    if (isImplementation && !genModel.isReflectiveDelegation() && genClass.implementsAny(genClass.getEBasicRemoveFromContainerGenFeatures())) {
    stringBuffer.append(TEXT_1345);
    if (genModel.useClassOverrideAnnotation()) {
    stringBuffer.append(TEXT_1346);
    }
    stringBuffer.append(TEXT_1347);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.notify.NotificationChain"));
    stringBuffer.append(TEXT_1348);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.notify.NotificationChain"));
    stringBuffer.append(TEXT_1349);
    stringBuffer.append(negativeOffsetCorrection);
    stringBuffer.append(TEXT_1350);
    for (GenFeature genFeature : genClass.getEBasicRemoveFromContainerGenFeatures()) {
    GenFeature reverseFeature = genFeature.getReverse(); GenClass targetClass = reverseFeature.getGenClass(); String reverseOffsetCorrection = targetClass.hasOffsetCorrection() ? " + " + genClass.getOffsetCorrectionField(genFeature) : "";
    stringBuffer.append(TEXT_1351);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(TEXT_1352);
    stringBuffer.append(targetClass.getQualifiedFeatureID(reverseFeature));
    stringBuffer.append(reverseOffsetCorrection);
    stringBuffer.append(TEXT_1353);
    stringBuffer.append(targetClass.getRawImportedInterfaceName());
    stringBuffer.append(TEXT_1354);
    }
    stringBuffer.append(TEXT_1355);
    if (genModel.isMinimalReflectiveMethods()) {
    stringBuffer.append(TEXT_1356);
    } else {
    stringBuffer.append(TEXT_1357);
    }
    stringBuffer.append(TEXT_1358);
    }
    if (isImplementation && !genModel.isReflectiveDelegation() && genClass.implementsAny(genClass.getEGetGenFeatures())) {
    stringBuffer.append(TEXT_1359);
    if (genModel.useClassOverrideAnnotation()) {
    stringBuffer.append(TEXT_1360);
    }
    stringBuffer.append(TEXT_1361);
    stringBuffer.append(negativeOffsetCorrection);
    stringBuffer.append(TEXT_1362);
    for (GenFeature genFeature : genClass.getEGetGenFeatures()) {
    stringBuffer.append(TEXT_1363);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(TEXT_1364);
    if (genFeature.isPrimitiveType()) {
    if (isJDK50) {
    stringBuffer.append(TEXT_1365);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_1366);
    } else if (genFeature.isBooleanType()) {
    stringBuffer.append(TEXT_1367);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_1368);
    } else {
    stringBuffer.append(TEXT_1369);
    stringBuffer.append(genFeature.getObjectType(genClass));
    stringBuffer.append(TEXT_1370);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_1371);
    }
    } else if (genFeature.isResolveProxies() && !genFeature.isListType()) {
    stringBuffer.append(TEXT_1372);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_1373);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_1374);
    } else if (genFeature.isMapType()) {
    if (genFeature.isEffectiveSuppressEMFTypes()) {
    stringBuffer.append(TEXT_1375);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.util.EMap"));
    stringBuffer.append(TEXT_1376);
    stringBuffer.append(genFeature.getImportedMapTemplateArguments(genClass));
    stringBuffer.append(TEXT_1377);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_1378);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_1379);
    } else {
    stringBuffer.append(TEXT_1380);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_1381);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_1382);
    }
    } else if (genFeature.isWrappedFeatureMapType()) {
    stringBuffer.append(TEXT_1383);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.FeatureMap"));
    stringBuffer.append(TEXT_1384);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_1385);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_1386);
    } else if (genFeature.isFeatureMapType()) {
    stringBuffer.append(TEXT_1387);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_1388);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.FeatureMap"));
    stringBuffer.append(TEXT_1389);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_1390);
    } else {
    stringBuffer.append(TEXT_1391);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_1392);
    }
    }
    stringBuffer.append(TEXT_1393);
    if (genModel.isMinimalReflectiveMethods()) {
    stringBuffer.append(TEXT_1394);
    } else {
    stringBuffer.append(TEXT_1395);
    }
    stringBuffer.append(TEXT_1396);
    }
    if (isImplementation && !genModel.isReflectiveDelegation() && genClass.implementsAny(genClass.getESetGenFeatures())) {
    stringBuffer.append(TEXT_1397);
    if (genModel.useGenerics()) {
    for (GenFeature genFeature : genClass.getESetGenFeatures()) {
    if (genFeature.isUncheckedCast(genClass) && !genFeature.isFeatureMapType() && !genFeature.isMapType()) {
    stringBuffer.append(TEXT_1398);
    break; }
    }
    }
    if (genModel.useClassOverrideAnnotation()) {
    stringBuffer.append(TEXT_1399);
    }
    stringBuffer.append(TEXT_1400);
    stringBuffer.append(negativeOffsetCorrection);
    stringBuffer.append(TEXT_1401);
    for (GenFeature genFeature : genClass.getESetGenFeatures()) {
    stringBuffer.append(TEXT_1402);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(TEXT_1403);
    if (genFeature.isListType()) {
    if (genFeature.isWrappedFeatureMapType()) {
    stringBuffer.append(TEXT_1404);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.FeatureMap"));
    stringBuffer.append(TEXT_1405);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.FeatureMap"));
    stringBuffer.append(TEXT_1406);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_1407);
    } else if (genFeature.isFeatureMapType()) {
    stringBuffer.append(TEXT_1408);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.FeatureMap"));
    stringBuffer.append(TEXT_1409);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_1410);
    } else if (genFeature.isMapType()) {
    if (genFeature.isEffectiveSuppressEMFTypes()) {
    stringBuffer.append(TEXT_1411);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.EStructuralFeature"));
    stringBuffer.append(TEXT_1412);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.util.EMap"));
    stringBuffer.append(TEXT_1413);
    stringBuffer.append(genFeature.getImportedMapTemplateArguments(genClass));
    stringBuffer.append(TEXT_1414);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_1415);
    } else {
    stringBuffer.append(TEXT_1416);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.EStructuralFeature"));
    stringBuffer.append(TEXT_1417);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_1418);
    }
    } else {
    stringBuffer.append(TEXT_1419);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_1420);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_1421);
    stringBuffer.append(genModel.getImportedName("java.util.Collection"));
    if (isJDK50) {
    stringBuffer.append(TEXT_1422);
    stringBuffer.append(genFeature.getListItemType(genClass));
    stringBuffer.append(TEXT_1423);
    }
    stringBuffer.append(TEXT_1424);
    }
    } else if (!isJDK50 && genFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_1425);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_1426);
    stringBuffer.append(genFeature.getObjectType(genClass));
    stringBuffer.append(TEXT_1427);
    stringBuffer.append(genFeature.getPrimitiveValueFunction());
    stringBuffer.append(TEXT_1428);
    } else {
    stringBuffer.append(TEXT_1429);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_1430);
    if (genFeature.getTypeGenDataType() == null || !genFeature.getTypeGenDataType().isObjectType() || !genFeature.getRawType().equals(genFeature.getType(genClass))) {
    stringBuffer.append(TEXT_1431);
    stringBuffer.append(genFeature.getObjectType(genClass));
    stringBuffer.append(TEXT_1432);
    }
    stringBuffer.append(TEXT_1433);
    }
    stringBuffer.append(TEXT_1434);
    }
    stringBuffer.append(TEXT_1435);
    if (genModel.isMinimalReflectiveMethods()) {
    stringBuffer.append(TEXT_1436);
    } else {
    stringBuffer.append(TEXT_1437);
    }
    stringBuffer.append(TEXT_1438);
    }
    if (isImplementation && !genModel.isReflectiveDelegation() && genClass.implementsAny(genClass.getEUnsetGenFeatures())) {
    stringBuffer.append(TEXT_1439);
    if (genModel.useClassOverrideAnnotation()) {
    stringBuffer.append(TEXT_1440);
    }
    stringBuffer.append(TEXT_1441);
    stringBuffer.append(negativeOffsetCorrection);
    stringBuffer.append(TEXT_1442);
    for (GenFeature genFeature : genClass.getEUnsetGenFeatures()) {
    stringBuffer.append(TEXT_1443);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(TEXT_1444);
    if (genFeature.isListType() && !genFeature.isUnsettable()) {
    if (genFeature.isWrappedFeatureMapType()) {
    stringBuffer.append(TEXT_1445);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.FeatureMap"));
    stringBuffer.append(TEXT_1446);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_1447);
    } else {
    stringBuffer.append(TEXT_1448);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_1449);
    }
    } else if (genFeature.isUnsettable()) {
    stringBuffer.append(TEXT_1450);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_1451);
    } else if (!genFeature.hasEDefault()) {
    stringBuffer.append(TEXT_1452);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_1453);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_1454);
    } else {
    stringBuffer.append(TEXT_1455);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_1456);
    stringBuffer.append(genFeature.getEDefault());
    stringBuffer.append(TEXT_1457);
    }
    stringBuffer.append(TEXT_1458);
    }
    stringBuffer.append(TEXT_1459);
    if (genModel.isMinimalReflectiveMethods()) {
    stringBuffer.append(TEXT_1460);
    } else {
    stringBuffer.append(TEXT_1461);
    }
    stringBuffer.append(TEXT_1462);
    //Class/eUnset.override.javajetinc
    }
    if (isImplementation && !genModel.isReflectiveDelegation() && genClass.implementsAny(genClass.getEIsSetGenFeatures())) {
    stringBuffer.append(TEXT_1463);
    if (genModel.useGenerics()) {
    for (GenFeature genFeature : genClass.getEIsSetGenFeatures()) {
    if (genFeature.isListType() && !genFeature.isUnsettable() && !genFeature.isWrappedFeatureMapType() && !genClass.isField(genFeature) && genFeature.isField() && genClass.getImplementingGenModel(genFeature).isVirtualDelegation()) {
    stringBuffer.append(TEXT_1464);
    break; }
    }
    }
    if (genModel.useClassOverrideAnnotation()) {
    stringBuffer.append(TEXT_1465);
    }
    stringBuffer.append(TEXT_1466);
    stringBuffer.append(negativeOffsetCorrection);
    stringBuffer.append(TEXT_1467);
    for (GenFeature genFeature : genClass.getEIsSetGenFeatures()) { String safeNameAccessor = genFeature.getSafeName(); if ("featureID".equals(safeNameAccessor)) { safeNameAccessor = "this." + safeNameAccessor; }
    stringBuffer.append(TEXT_1468);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(TEXT_1469);
    if (genFeature.hasSettingDelegate()) {
    if (genFeature.isUnsettable()) {
    stringBuffer.append(TEXT_1470);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_1471);
    } else {
    stringBuffer.append(TEXT_1472);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_1473);
    }
    } else if (genFeature.isListType() && !genFeature.isUnsettable()) {
    if (genFeature.isWrappedFeatureMapType()) {
    if (genFeature.isVolatile()) {
    stringBuffer.append(TEXT_1474);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.FeatureMap"));
    stringBuffer.append(TEXT_1475);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_1476);
    } else {
    stringBuffer.append(TEXT_1477);
    stringBuffer.append(safeNameAccessor);
    stringBuffer.append(TEXT_1478);
    stringBuffer.append(safeNameAccessor);
    stringBuffer.append(TEXT_1479);
    }
    } else {
    if (genClass.isField(genFeature)) {
    stringBuffer.append(TEXT_1480);
    stringBuffer.append(safeNameAccessor);
    stringBuffer.append(TEXT_1481);
    stringBuffer.append(safeNameAccessor);
    stringBuffer.append(TEXT_1482);
    } else {
    if (genFeature.isField() && genClass.getImplementingGenModel(genFeature).isVirtualDelegation()) {
    stringBuffer.append(TEXT_1483);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_1484);
    stringBuffer.append(safeNameAccessor);
    stringBuffer.append(TEXT_1485);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_1486);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(positiveOffsetCorrection);
    stringBuffer.append(TEXT_1487);
    stringBuffer.append(safeNameAccessor);
    stringBuffer.append(TEXT_1488);
    stringBuffer.append(safeNameAccessor);
    stringBuffer.append(TEXT_1489);
    } else {
    stringBuffer.append(TEXT_1490);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_1491);
    }
    }
    }
    } else if (genFeature.isUnsettable()) {
    stringBuffer.append(TEXT_1492);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_1493);
    } else if (genFeature.isResolveProxies()) {
    if (genClass.isField(genFeature)) {
    stringBuffer.append(TEXT_1494);
    stringBuffer.append(safeNameAccessor);
    stringBuffer.append(TEXT_1495);
    } else {
    if (genFeature.isField() && genClass.getImplementingGenModel(genFeature).isVirtualDelegation()) {
    stringBuffer.append(TEXT_1496);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(positiveOffsetCorrection);
    stringBuffer.append(TEXT_1497);
    } else {
    stringBuffer.append(TEXT_1498);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_1499);
    }
    }
    } else if (!genFeature.hasEDefault()) {
    if (genClass.isField(genFeature)) {
    stringBuffer.append(TEXT_1500);
    stringBuffer.append(safeNameAccessor);
    stringBuffer.append(TEXT_1501);
    } else {
    if (genFeature.isField() && genClass.getImplementingGenModel(genFeature).isVirtualDelegation()) {
    stringBuffer.append(TEXT_1502);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(positiveOffsetCorrection);
    stringBuffer.append(TEXT_1503);
    } else {
    stringBuffer.append(TEXT_1504);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_1505);
    }
    }
    } else if (genFeature.isPrimitiveType() || genFeature.isEnumType()) {
    if (genClass.isField(genFeature)) {
    if (genClass.isFlag(genFeature)) {
    if (genFeature.isBooleanType()) {
    stringBuffer.append(TEXT_1506);
    stringBuffer.append(genClass.getFlagsField(genFeature));
    stringBuffer.append(TEXT_1507);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_1508);
    stringBuffer.append(genFeature.getEDefault());
    stringBuffer.append(TEXT_1509);
    } else {
    stringBuffer.append(TEXT_1510);
    stringBuffer.append(genClass.getFlagsField(genFeature));
    stringBuffer.append(TEXT_1511);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_1512);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_1513);
    }
    } else {
    stringBuffer.append(TEXT_1514);
    stringBuffer.append(safeNameAccessor);
    stringBuffer.append(TEXT_1515);
    stringBuffer.append(genFeature.getEDefault());
    stringBuffer.append(TEXT_1516);
    }
    } else {
    if (genFeature.isEnumType() && genFeature.isField() && genClass.getImplementingGenModel(genFeature).isVirtualDelegation()) {
    stringBuffer.append(TEXT_1517);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(positiveOffsetCorrection);
    stringBuffer.append(TEXT_1518);
    stringBuffer.append(genFeature.getEDefault());
    stringBuffer.append(TEXT_1519);
    stringBuffer.append(genFeature.getEDefault());
    stringBuffer.append(TEXT_1520);
    } else {
    stringBuffer.append(TEXT_1521);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_1522);
    stringBuffer.append(genFeature.getEDefault());
    stringBuffer.append(TEXT_1523);
    }
    }
    } else {//datatype
    if (genClass.isField(genFeature)) {
    stringBuffer.append(TEXT_1524);
    stringBuffer.append(genFeature.getEDefault());
    stringBuffer.append(TEXT_1525);
    stringBuffer.append(safeNameAccessor);
    stringBuffer.append(TEXT_1526);
    stringBuffer.append(genFeature.getEDefault());
    stringBuffer.append(TEXT_1527);
    stringBuffer.append(safeNameAccessor);
    stringBuffer.append(TEXT_1528);
    } else {
    if (genFeature.isField() && genClass.getImplementingGenModel(genFeature).isVirtualDelegation()) {
    stringBuffer.append(TEXT_1529);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_1530);
    stringBuffer.append(safeNameAccessor);
    stringBuffer.append(TEXT_1531);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_1532);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(positiveOffsetCorrection);
    stringBuffer.append(TEXT_1533);
    stringBuffer.append(genFeature.getEDefault());
    stringBuffer.append(TEXT_1534);
    stringBuffer.append(genFeature.getEDefault());
    stringBuffer.append(TEXT_1535);
    stringBuffer.append(safeNameAccessor);
    stringBuffer.append(TEXT_1536);
    stringBuffer.append(genFeature.getEDefault());
    stringBuffer.append(TEXT_1537);
    stringBuffer.append(safeNameAccessor);
    stringBuffer.append(TEXT_1538);
    } else {
    stringBuffer.append(TEXT_1539);
    stringBuffer.append(genFeature.getEDefault());
    stringBuffer.append(TEXT_1540);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_1541);
    stringBuffer.append(genFeature.getEDefault());
    stringBuffer.append(TEXT_1542);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_1543);
    }
    }
    }
    }
    stringBuffer.append(TEXT_1544);
    if (genModel.isMinimalReflectiveMethods()) {
    stringBuffer.append(TEXT_1545);
    } else {
    stringBuffer.append(TEXT_1546);
    }
    stringBuffer.append(TEXT_1547);
    //Class/eIsSet.override.javajetinc
    }
    if (isImplementation && (!genClass.getMixinGenFeatures().isEmpty() || genClass.hasOffsetCorrection() && !genClass.getGenFeatures().isEmpty())) {
    if (!genClass.getMixinGenFeatures().isEmpty()) {
    stringBuffer.append(TEXT_1548);
    if (genModel.useClassOverrideAnnotation()) {
    stringBuffer.append(TEXT_1549);
    }
    stringBuffer.append(TEXT_1550);
    stringBuffer.append(singleWildcard);
    stringBuffer.append(TEXT_1551);
    for (GenClass mixinGenClass : genClass.getMixinGenClasses()) {
    stringBuffer.append(TEXT_1552);
    stringBuffer.append(mixinGenClass.getRawImportedInterfaceName());
    stringBuffer.append(TEXT_1553);
    stringBuffer.append(negativeOffsetCorrection);
    stringBuffer.append(TEXT_1554);
    for (GenFeature genFeature : mixinGenClass.getGenFeatures()) {
    stringBuffer.append(TEXT_1555);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(TEXT_1556);
    stringBuffer.append(mixinGenClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(TEXT_1557);
    }
    stringBuffer.append(TEXT_1558);
    }
    stringBuffer.append(TEXT_1559);
    }
    stringBuffer.append(TEXT_1560);
    if (genModel.useClassOverrideAnnotation()) {
    stringBuffer.append(TEXT_1561);
    }
    stringBuffer.append(TEXT_1562);
    stringBuffer.append(singleWildcard);
    stringBuffer.append(TEXT_1563);
    for (GenClass mixinGenClass : genClass.getMixinGenClasses()) {
    stringBuffer.append(TEXT_1564);
    stringBuffer.append(mixinGenClass.getRawImportedInterfaceName());
    stringBuffer.append(TEXT_1565);
    for (GenFeature genFeature : mixinGenClass.getGenFeatures()) {
    stringBuffer.append(TEXT_1566);
    stringBuffer.append(mixinGenClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(TEXT_1567);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(positiveOffsetCorrection);
    stringBuffer.append(TEXT_1568);
    }
    stringBuffer.append(TEXT_1569);
    }
    if (genClass.hasOffsetCorrection() && !genClass.getGenFeatures().isEmpty()) {
    stringBuffer.append(TEXT_1570);
    stringBuffer.append(genClass.getRawImportedInterfaceName());
    stringBuffer.append(TEXT_1571);
    stringBuffer.append(negativeOffsetCorrection);
    stringBuffer.append(TEXT_1572);
    for (GenFeature genFeature : genClass.getGenFeatures()) {
    stringBuffer.append(TEXT_1573);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(TEXT_1574);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(positiveOffsetCorrection);
    stringBuffer.append(TEXT_1575);
    }
    stringBuffer.append(TEXT_1576);
    }
    stringBuffer.append(TEXT_1577);
    }
    if (genModel.isOperationReflection() && isImplementation && (!genClass.getMixinGenOperations().isEmpty() || !genClass.getOverrideGenOperations(genClass.getExtendedGenOperations(), genClass.getImplementedGenOperations()).isEmpty() || genClass.hasOffsetCorrection() && !genClass.getGenOperations().isEmpty())) {
    stringBuffer.append(TEXT_1578);
    if (genModel.useClassOverrideAnnotation()) {
    stringBuffer.append(TEXT_1579);
    }
    stringBuffer.append(TEXT_1580);
    stringBuffer.append(singleWildcard);
    stringBuffer.append(TEXT_1581);
    for (GenClass extendedGenClass : genClass.getExtendedGenClasses()) { List<GenOperation> extendedImplementedGenOperations = extendedGenClass.getImplementedGenOperations(); List<GenOperation> implementedGenOperations = genClass.getImplementedGenOperations();
    if (!genClass.getOverrideGenOperations(extendedImplementedGenOperations, implementedGenOperations).isEmpty()) {
    stringBuffer.append(TEXT_1582);
    stringBuffer.append(extendedGenClass.getRawImportedInterfaceName());
    stringBuffer.append(TEXT_1583);
    for (GenOperation genOperation : extendedImplementedGenOperations) { GenOperation overrideGenOperation = genClass.getOverrideGenOperation(genOperation);
    if (implementedGenOperations.contains(overrideGenOperation)) {
    stringBuffer.append(TEXT_1584);
    stringBuffer.append(extendedGenClass.getQualifiedOperationID(genOperation));
    stringBuffer.append(TEXT_1585);
    stringBuffer.append(genClass.getQualifiedOperationID(overrideGenOperation));
    stringBuffer.append(positiveOperationOffsetCorrection);
    stringBuffer.append(TEXT_1586);
    }
    }
    stringBuffer.append(TEXT_1587);
    }
    }
    for (GenClass mixinGenClass : genClass.getMixinGenClasses()) {
    stringBuffer.append(TEXT_1588);
    stringBuffer.append(mixinGenClass.getRawImportedInterfaceName());
    stringBuffer.append(TEXT_1589);
    for (GenOperation genOperation : mixinGenClass.getGenOperations()) { GenOperation overrideGenOperation = genClass.getOverrideGenOperation(genOperation);
    stringBuffer.append(TEXT_1590);
    stringBuffer.append(mixinGenClass.getQualifiedOperationID(genOperation));
    stringBuffer.append(TEXT_1591);
    stringBuffer.append(genClass.getQualifiedOperationID(overrideGenOperation != null ? overrideGenOperation : genOperation));
    stringBuffer.append(positiveOperationOffsetCorrection);
    stringBuffer.append(TEXT_1592);
    }
    stringBuffer.append(TEXT_1593);
    }
    if (genClass.hasOffsetCorrection() && !genClass.getGenOperations().isEmpty()) {
    stringBuffer.append(TEXT_1594);
    stringBuffer.append(genClass.getRawImportedInterfaceName());
    stringBuffer.append(TEXT_1595);
    stringBuffer.append(negativeOperationOffsetCorrection);
    stringBuffer.append(TEXT_1596);
    for (GenOperation genOperation : genClass.getGenOperations()) {
    stringBuffer.append(TEXT_1597);
    stringBuffer.append(genClass.getQualifiedOperationID(genOperation));
    stringBuffer.append(TEXT_1598);
    stringBuffer.append(genClass.getQualifiedOperationID(genOperation));
    stringBuffer.append(positiveOperationOffsetCorrection);
    stringBuffer.append(TEXT_1599);
    }
    stringBuffer.append(TEXT_1600);
    }
    stringBuffer.append(TEXT_1601);
    }
    if (isImplementation && genModel.isVirtualDelegation()) { String eVirtualValuesField = genClass.getEVirtualValuesField();
    if (eVirtualValuesField != null) {
    stringBuffer.append(TEXT_1602);
    if (genModel.useClassOverrideAnnotation()) {
    stringBuffer.append(TEXT_1603);
    }
    stringBuffer.append(TEXT_1604);
    stringBuffer.append(eVirtualValuesField);
    stringBuffer.append(TEXT_1605);
    if (genModel.useClassOverrideAnnotation()) {
    stringBuffer.append(TEXT_1606);
    }
    stringBuffer.append(TEXT_1607);
    stringBuffer.append(eVirtualValuesField);
    stringBuffer.append(TEXT_1608);
    }
    { List<String> eVirtualIndexBitFields = genClass.getEVirtualIndexBitFields(new ArrayList<String>());
    if (!eVirtualIndexBitFields.isEmpty()) { List<String> allEVirtualIndexBitFields = genClass.getAllEVirtualIndexBitFields(new ArrayList<String>());
    stringBuffer.append(TEXT_1609);
    if (genModel.useClassOverrideAnnotation()) {
    stringBuffer.append(TEXT_1610);
    }
    stringBuffer.append(TEXT_1611);
    for (int i = 0; i < allEVirtualIndexBitFields.size(); i++) {
    stringBuffer.append(TEXT_1612);
    stringBuffer.append(i);
    stringBuffer.append(TEXT_1613);
    stringBuffer.append(allEVirtualIndexBitFields.get(i));
    stringBuffer.append(TEXT_1614);
    }
    stringBuffer.append(TEXT_1615);
    if (genModel.useClassOverrideAnnotation()) {
    stringBuffer.append(TEXT_1616);
    }
    stringBuffer.append(TEXT_1617);
    for (int i = 0; i < allEVirtualIndexBitFields.size(); i++) {
    stringBuffer.append(TEXT_1618);
    stringBuffer.append(i);
    stringBuffer.append(TEXT_1619);
    stringBuffer.append(allEVirtualIndexBitFields.get(i));
    stringBuffer.append(TEXT_1620);
    }
    stringBuffer.append(TEXT_1621);
    }
    }
    }
    if (genModel.isOperationReflection() && isImplementation && !genClass.getImplementedGenOperations().isEmpty()) {
    stringBuffer.append(TEXT_1622);
    if (genModel.useClassOverrideAnnotation()) {
    stringBuffer.append(TEXT_1623);
    }
    if (genModel.useGenerics()) {
    boolean isUnchecked = false; boolean isRaw = false; LOOP: for (GenOperation genOperation : (genModel.isMinimalReflectiveMethods() ? genClass.getImplementedGenOperations() : genClass.getAllGenOperations())) { for (GenParameter genParameter : genOperation.getGenParameters()) { if (genParameter.isUncheckedCast()) { if (genParameter.getTypeGenDataType() == null || !genParameter.getTypeGenDataType().isObjectType()) { isUnchecked = true; } if (genParameter.usesOperationTypeParameters() && !genParameter.getEcoreParameter().getEGenericType().getETypeArguments().isEmpty()) { isRaw = true; break LOOP; }}}}
    if (isUnchecked) {
    stringBuffer.append(TEXT_1624);
    if (!isRaw) {
    stringBuffer.append(TEXT_1625);
    } else {
    stringBuffer.append(TEXT_1626);
    }
    stringBuffer.append(TEXT_1627);
    }
    }
    stringBuffer.append(TEXT_1628);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.util.EList"));
    stringBuffer.append(singleWildcard);
    stringBuffer.append(TEXT_1629);
    stringBuffer.append(genModel.getImportedName(isGWT ? "org.eclipse.emf.common.util.InvocationTargetException" : "java.lang.reflect.InvocationTargetException"));
    stringBuffer.append(TEXT_1630);
    stringBuffer.append(negativeOperationOffsetCorrection);
    stringBuffer.append(TEXT_1631);
    for (GenOperation genOperation : (genModel.isMinimalReflectiveMethods() ? genClass.getImplementedGenOperations() : genClass.getAllGenOperations())) { List<GenParameter> genParameters = genOperation.getGenParameters(); int size = genParameters.size();
    stringBuffer.append(TEXT_1632);
    stringBuffer.append(genClass.getQualifiedOperationID(genOperation));
    stringBuffer.append(TEXT_1633);
    if (genOperation.isVoid()) {
    stringBuffer.append(TEXT_1634);
    stringBuffer.append(genOperation.getName());
    stringBuffer.append(TEXT_1635);
    for (int i = 0; i < size; i++) { GenParameter genParameter = genParameters.get(i);
    if (!isJDK50 && genParameter.isPrimitiveType()) {
    stringBuffer.append(TEXT_1636);
    }
    if (genParameter.getTypeGenDataType() == null || !genParameter.getTypeGenDataType().isObjectType() || !genParameter.usesOperationTypeParameters() && !genParameter.getRawType().equals(genParameter.getType(genClass))) {
    stringBuffer.append(TEXT_1637);
    stringBuffer.append(genParameter.usesOperationTypeParameters() ? genParameter.getRawImportedType() : genParameter.getObjectType(genClass));
    stringBuffer.append(TEXT_1638);
    }
    stringBuffer.append(TEXT_1639);
    stringBuffer.append(i);
    stringBuffer.append(TEXT_1640);
    if (!isJDK50 && genParameter.isPrimitiveType()) {
    stringBuffer.append(TEXT_1641);
    stringBuffer.append(genParameter.getPrimitiveValueFunction());
    stringBuffer.append(TEXT_1642);
    }
    if (i < (size - 1)) {
    stringBuffer.append(TEXT_1643);
    }
    }
    stringBuffer.append(TEXT_1644);
    } else {
    stringBuffer.append(TEXT_1645);
    if (!isJDK50 && genOperation.isPrimitiveType()) {
    stringBuffer.append(TEXT_1646);
    stringBuffer.append(genOperation.getObjectType(genClass));
    stringBuffer.append(TEXT_1647);
    }
    stringBuffer.append(genOperation.getName());
    stringBuffer.append(TEXT_1648);
    for (int i = 0; i < size; i++) { GenParameter genParameter = genParameters.get(i);
    if (!isJDK50 && genParameter.isPrimitiveType()) {
    stringBuffer.append(TEXT_1649);
    }
    if (genParameter.getTypeGenDataType() == null || !genParameter.getTypeGenDataType().isObjectType() || !genParameter.usesOperationTypeParameters() && !genParameter.getRawType().equals(genParameter.getType(genClass))) {
    stringBuffer.append(TEXT_1650);
    stringBuffer.append(genParameter.usesOperationTypeParameters() ? genParameter.getRawImportedType() : genParameter.getObjectType(genClass));
    stringBuffer.append(TEXT_1651);
    }
    stringBuffer.append(TEXT_1652);
    stringBuffer.append(i);
    stringBuffer.append(TEXT_1653);
    if (!isJDK50 && genParameter.isPrimitiveType()) {
    stringBuffer.append(TEXT_1654);
    stringBuffer.append(genParameter.getPrimitiveValueFunction());
    stringBuffer.append(TEXT_1655);
    }
    if (i < (size - 1)) {
    stringBuffer.append(TEXT_1656);
    }
    }
    stringBuffer.append(TEXT_1657);
    if (!isJDK50 && genOperation.isPrimitiveType()) {
    stringBuffer.append(TEXT_1658);
    }
    stringBuffer.append(TEXT_1659);
    }
    }
    stringBuffer.append(TEXT_1660);
    if (genModel.isMinimalReflectiveMethods()) {
    stringBuffer.append(TEXT_1661);
    } else {
    stringBuffer.append(TEXT_1662);
    }
    stringBuffer.append(TEXT_1663);
    }
    if (!genClass.hasImplementedToStringGenOperation() && isImplementation && !genModel.isReflectiveDelegation() && !genModel.isDynamicDelegation() && !genClass.getToStringGenFeatures().isEmpty()) {
    stringBuffer.append(TEXT_1664);
    if (genModel.useClassOverrideAnnotation()) {
    stringBuffer.append(TEXT_1665);
    }
    stringBuffer.append(TEXT_1666);
    { boolean first = true;
    for (GenFeature genFeature : genClass.getToStringGenFeatures()) {
    if (first) { first = false;
    stringBuffer.append(TEXT_1667);
    stringBuffer.append(genFeature.getName());
    stringBuffer.append(TEXT_1668);
    stringBuffer.append(genModel.getNonNLS());
    } else {
    stringBuffer.append(TEXT_1669);
    stringBuffer.append(genFeature.getName());
    stringBuffer.append(TEXT_1670);
    stringBuffer.append(genModel.getNonNLS());
    }
    if (genFeature.isUnsettable() && !genFeature.isListType()) {
    if (genModel.isVirtualDelegation() && !genFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_1671);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(positiveOffsetCorrection);
    stringBuffer.append(TEXT_1672);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(positiveOffsetCorrection);
    stringBuffer.append(TEXT_1673);
    stringBuffer.append(genModel.getNonNLS());
    } else {
    if (genClass.isFlag(genFeature)) {
    if (genFeature.isBooleanType()) {
    stringBuffer.append(TEXT_1674);
    if (genClass.isESetFlag(genFeature)) {
    stringBuffer.append(TEXT_1675);
    stringBuffer.append(genClass.getESetFlagsField(genFeature));
    stringBuffer.append(TEXT_1676);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_1677);
    } else {
    stringBuffer.append(genFeature.getUncapName());
    stringBuffer.append(TEXT_1678);
    }
    stringBuffer.append(TEXT_1679);
    stringBuffer.append(genClass.getFlagsField(genFeature));
    stringBuffer.append(TEXT_1680);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_1681);
    stringBuffer.append(genModel.getNonNLS());
    } else {
    stringBuffer.append(TEXT_1682);
    if (genClass.isESetFlag(genFeature)) {
    stringBuffer.append(TEXT_1683);
    stringBuffer.append(genClass.getESetFlagsField(genFeature));
    stringBuffer.append(TEXT_1684);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_1685);
    } else {
    stringBuffer.append(genFeature.getUncapName());
    stringBuffer.append(TEXT_1686);
    }
    stringBuffer.append(TEXT_1687);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_1688);
    stringBuffer.append(genClass.getFlagsField(genFeature));
    stringBuffer.append(TEXT_1689);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_1690);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_1691);
    stringBuffer.append(genModel.getNonNLS());
    }
    } else {
    stringBuffer.append(TEXT_1692);
    if (genClass.isESetFlag(genFeature)) {
    stringBuffer.append(TEXT_1693);
    stringBuffer.append(genClass.getESetFlagsField(genFeature));
    stringBuffer.append(TEXT_1694);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_1695);
    } else {
    stringBuffer.append(genFeature.getUncapName());
    stringBuffer.append(TEXT_1696);
    }
    stringBuffer.append(TEXT_1697);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_1698);
    stringBuffer.append(genModel.getNonNLS());
    }
    }
    } else {
    if (genModel.isVirtualDelegation() && !genFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_1699);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(positiveOffsetCorrection);
    if (!genFeature.isListType() && !genFeature.isReferenceType()){
    stringBuffer.append(TEXT_1700);
    stringBuffer.append(genFeature.getEDefault());
    }
    stringBuffer.append(TEXT_1701);
    } else {
    if (genClass.isFlag(genFeature)) {
    if (genFeature.isBooleanType()) {
    stringBuffer.append(TEXT_1702);
    stringBuffer.append(genClass.getFlagsField(genFeature));
    stringBuffer.append(TEXT_1703);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_1704);
    } else {
    stringBuffer.append(TEXT_1705);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_1706);
    stringBuffer.append(genClass.getFlagsField(genFeature));
    stringBuffer.append(TEXT_1707);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_1708);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_1709);
    }
    } else {
    stringBuffer.append(TEXT_1710);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_1711);
    }
    }
    }
    }
    }
    stringBuffer.append(TEXT_1712);
    }
    if (isImplementation && genClass.isMapEntry()) { GenFeature keyFeature = genClass.getMapEntryKeyFeature(); GenFeature valueFeature = genClass.getMapEntryValueFeature();
    String objectType = genModel.getImportedName("java.lang.Object");
    String keyType = isJDK50 ? keyFeature.getObjectType(genClass) : objectType;
    String valueType = isJDK50 ? valueFeature.getObjectType(genClass) : objectType;
    String eMapType = genModel.getImportedName("org.eclipse.emf.common.util.EMap") + (isJDK50 ? "<" + keyType + ", " + valueType + ">" : "");
    stringBuffer.append(TEXT_1713);
    if (isGWT) {
    stringBuffer.append(TEXT_1714);
    stringBuffer.append(genModel.getImportedName("com.google.gwt.user.client.rpc.GwtTransient"));
    }
    stringBuffer.append(TEXT_1715);
    stringBuffer.append(objectType);
    stringBuffer.append(TEXT_1716);
    stringBuffer.append(keyType);
    stringBuffer.append(TEXT_1717);
    if (!isJDK50 && keyFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_1718);
    stringBuffer.append(keyFeature.getObjectType(genClass));
    stringBuffer.append(TEXT_1719);
    } else {
    stringBuffer.append(TEXT_1720);
    }
    stringBuffer.append(TEXT_1721);
    stringBuffer.append(keyType);
    stringBuffer.append(TEXT_1722);
    if (keyFeature.isListType()) {
    stringBuffer.append(TEXT_1723);
    if (!genModel.useGenerics()) {
    stringBuffer.append(TEXT_1724);
    stringBuffer.append(genModel.getImportedName("java.util.Collection"));
    stringBuffer.append(TEXT_1725);
    }
    stringBuffer.append(TEXT_1726);
    } else if (isJDK50) {
    stringBuffer.append(TEXT_1727);
    } else if (keyFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_1728);
    stringBuffer.append(keyFeature.getObjectType(genClass));
    stringBuffer.append(TEXT_1729);
    stringBuffer.append(keyFeature.getPrimitiveValueFunction());
    stringBuffer.append(TEXT_1730);
    } else {
    stringBuffer.append(TEXT_1731);
    stringBuffer.append(keyFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_1732);
    }
    stringBuffer.append(TEXT_1733);
    stringBuffer.append(valueType);
    stringBuffer.append(TEXT_1734);
    if (!isJDK50 && valueFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_1735);
    stringBuffer.append(valueFeature.getObjectType(genClass));
    stringBuffer.append(TEXT_1736);
    } else {
    stringBuffer.append(TEXT_1737);
    }
    stringBuffer.append(TEXT_1738);
    stringBuffer.append(valueType);
    stringBuffer.append(TEXT_1739);
    stringBuffer.append(valueType);
    stringBuffer.append(TEXT_1740);
    stringBuffer.append(valueType);
    stringBuffer.append(TEXT_1741);
    if (valueFeature.isListType()) {
    stringBuffer.append(TEXT_1742);
    if (!genModel.useGenerics()) {
    stringBuffer.append(TEXT_1743);
    stringBuffer.append(genModel.getImportedName("java.util.Collection"));
    stringBuffer.append(TEXT_1744);
    }
    stringBuffer.append(TEXT_1745);
    } else if (isJDK50) {
    stringBuffer.append(TEXT_1746);
    } else if (valueFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_1747);
    stringBuffer.append(valueFeature.getObjectType(genClass));
    stringBuffer.append(TEXT_1748);
    stringBuffer.append(valueFeature.getPrimitiveValueFunction());
    stringBuffer.append(TEXT_1749);
    } else {
    stringBuffer.append(TEXT_1750);
    stringBuffer.append(valueFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_1751);
    }
    stringBuffer.append(TEXT_1752);
    if (genModel.useGenerics()) {
    stringBuffer.append(TEXT_1753);
    }
    stringBuffer.append(TEXT_1754);
    stringBuffer.append(eMapType);
    stringBuffer.append(TEXT_1755);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.EObject"));
    stringBuffer.append(TEXT_1756);
    stringBuffer.append(eMapType);
    stringBuffer.append(TEXT_1757);
    }
    stringBuffer.append(TEXT_1758);
    stringBuffer.append(isInterface ? " " + genClass.getInterfaceName() : genClass.getClassName());
    // TODO fix the space above
    genModel.emitSortedImports();
    stringBuffer.append(TEXT_1759);
    return stringBuffer.toString();
  }
}
