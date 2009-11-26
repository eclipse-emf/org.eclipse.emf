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
  protected final String TEXT_4 = NL + " * <copyright>" + NL + " * </copyright>" + NL + " *" + NL + " * ";
  protected final String TEXT_5 = "Id";
  protected final String TEXT_6 = NL + " */";
  protected final String TEXT_7 = NL + "package ";
  protected final String TEXT_8 = ";";
  protected final String TEXT_9 = NL + "package ";
  protected final String TEXT_10 = ";";
  protected final String TEXT_11 = NL;
  protected final String TEXT_12 = NL;
  protected final String TEXT_13 = NL + "/**" + NL + " * <!-- begin-user-doc -->" + NL + " * A representation of the model object '<em><b>";
  protected final String TEXT_14 = "</b></em>'." + NL + " * <!-- end-user-doc -->";
  protected final String TEXT_15 = NL + " *" + NL + " * <!-- begin-model-doc -->" + NL + " * ";
  protected final String TEXT_16 = NL + " * <!-- end-model-doc -->";
  protected final String TEXT_17 = NL + " *";
  protected final String TEXT_18 = NL + " * <p>" + NL + " * The following features are supported:" + NL + " * <ul>";
  protected final String TEXT_19 = NL + " *   <li>{@link ";
  protected final String TEXT_20 = "#";
  protected final String TEXT_21 = " <em>";
  protected final String TEXT_22 = "</em>}</li>";
  protected final String TEXT_23 = NL + " * </ul>" + NL + " * </p>";
  protected final String TEXT_24 = NL + " *";
  protected final String TEXT_25 = NL + " * @see ";
  protected final String TEXT_26 = "#get";
  protected final String TEXT_27 = "()";
  protected final String TEXT_28 = NL + " * @model ";
  protected final String TEXT_29 = NL + " *        ";
  protected final String TEXT_30 = NL + " * @model";
  protected final String TEXT_31 = NL + " * @extends ";
  protected final String TEXT_32 = NL + " * @generated" + NL + " */";
  protected final String TEXT_33 = NL + "/**" + NL + " * <!-- begin-user-doc -->" + NL + " * An implementation of the model object '<em><b>";
  protected final String TEXT_34 = "</b></em>'." + NL + " * <!-- end-user-doc -->" + NL + " * <p>";
  protected final String TEXT_35 = NL + " * The following features are implemented:" + NL + " * <ul>";
  protected final String TEXT_36 = NL + " *   <li>{@link ";
  protected final String TEXT_37 = "#";
  protected final String TEXT_38 = " <em>";
  protected final String TEXT_39 = "</em>}</li>";
  protected final String TEXT_40 = NL + " * </ul>";
  protected final String TEXT_41 = NL + " * </p>" + NL + " *" + NL + " * @generated" + NL + " */";
  protected final String TEXT_42 = NL + "public";
  protected final String TEXT_43 = " abstract";
  protected final String TEXT_44 = " class ";
  protected final String TEXT_45 = NL + "public interface ";
  protected final String TEXT_46 = NL + "{";
  protected final String TEXT_47 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */" + NL + "\t";
  protected final String TEXT_48 = " copyright = ";
  protected final String TEXT_49 = ";";
  protected final String TEXT_50 = NL;
  protected final String TEXT_51 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */" + NL + "\tpublic static final ";
  protected final String TEXT_52 = " mofDriverNumber = \"";
  protected final String TEXT_53 = "\";";
  protected final String TEXT_54 = NL;
  protected final String TEXT_55 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */" + NL + "\tprivate static final long serialVersionUID = 1L;" + NL;
  protected final String TEXT_56 = NL + "\t/**" + NL + "\t * An array of objects representing the values of non-primitive features." + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */" + NL + "\tprotected Object[] ";
  protected final String TEXT_57 = ";" + NL;
  protected final String TEXT_58 = NL + "\t/**" + NL + "\t * A bit field representing the indices of non-primitive feature values." + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */" + NL + "\tprotected int ";
  protected final String TEXT_59 = ";" + NL;
  protected final String TEXT_60 = NL + "\t/**" + NL + "\t * A set of bit flags representing the values of boolean attributes and whether unsettable features have been set." + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t * @ordered" + NL + "\t */" + NL + "\tprotected int ";
  protected final String TEXT_61 = " = 0;" + NL;
  protected final String TEXT_62 = NL + "\t/**" + NL + "\t * The cached setting delegate for the '{@link #";
  protected final String TEXT_63 = "() <em>";
  protected final String TEXT_64 = "</em>}' ";
  protected final String TEXT_65 = "." + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @see #";
  protected final String TEXT_66 = "()" + NL + "\t * @generated" + NL + "\t * @ordered" + NL + "\t */" + NL + "\tprotected ";
  protected final String TEXT_67 = ".Internal.SettingDelegate ";
  protected final String TEXT_68 = "__ESETTING_DELEGATE = ((";
  protected final String TEXT_69 = ".Internal)";
  protected final String TEXT_70 = ").getSettingDelegate();" + NL;
  protected final String TEXT_71 = NL + "\t/**" + NL + "\t * The cached value of the '{@link #";
  protected final String TEXT_72 = "() <em>";
  protected final String TEXT_73 = "</em>}' ";
  protected final String TEXT_74 = "." + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @see #";
  protected final String TEXT_75 = "()" + NL + "\t * @generated" + NL + "\t * @ordered" + NL + "\t */" + NL + "\tprotected ";
  protected final String TEXT_76 = " ";
  protected final String TEXT_77 = ";" + NL;
  protected final String TEXT_78 = NL + "\t/**" + NL + "\t * The empty value for the '{@link #";
  protected final String TEXT_79 = "() <em>";
  protected final String TEXT_80 = "</em>}' array accessor." + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @see #";
  protected final String TEXT_81 = "()" + NL + "\t * @generated" + NL + "\t * @ordered" + NL + "\t */";
  protected final String TEXT_82 = NL + "\t@SuppressWarnings(\"unchecked\")";
  protected final String TEXT_83 = NL + "\tprotected static final ";
  protected final String TEXT_84 = "[] ";
  protected final String TEXT_85 = "_EEMPTY_ARRAY = new ";
  protected final String TEXT_86 = " [0]";
  protected final String TEXT_87 = ";" + NL;
  protected final String TEXT_88 = NL + "\t/**" + NL + "\t * The default value of the '{@link #";
  protected final String TEXT_89 = "() <em>";
  protected final String TEXT_90 = "</em>}' ";
  protected final String TEXT_91 = "." + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @see #";
  protected final String TEXT_92 = "()" + NL + "\t * @generated" + NL + "\t * @ordered" + NL + "\t */";
  protected final String TEXT_93 = NL + "\t@SuppressWarnings(\"unchecked\")";
  protected final String TEXT_94 = NL + "\tprotected static final ";
  protected final String TEXT_95 = " ";
  protected final String TEXT_96 = "; // TODO The default value literal \"";
  protected final String TEXT_97 = "\" is not valid.";
  protected final String TEXT_98 = " = ";
  protected final String TEXT_99 = ";";
  protected final String TEXT_100 = NL;
  protected final String TEXT_101 = NL + "\t/**" + NL + "\t * An additional set of bit flags representing the values of boolean attributes and whether unsettable features have been set." + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t * @ordered" + NL + "\t */" + NL + "\tprotected int ";
  protected final String TEXT_102 = " = 0;" + NL;
  protected final String TEXT_103 = NL + "\t/**" + NL + "\t * The offset of the flags representing the value of the '{@link #";
  protected final String TEXT_104 = "() <em>";
  protected final String TEXT_105 = "</em>}' ";
  protected final String TEXT_106 = "." + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t * @ordered" + NL + "\t */" + NL + "\tprotected static final int ";
  protected final String TEXT_107 = "_EFLAG_OFFSET = ";
  protected final String TEXT_108 = ";" + NL + "" + NL + "\t/**" + NL + "\t * The flags representing the default value of the '{@link #";
  protected final String TEXT_109 = "() <em>";
  protected final String TEXT_110 = "</em>}' ";
  protected final String TEXT_111 = "." + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t * @ordered" + NL + "\t */" + NL + "\tprotected static final int ";
  protected final String TEXT_112 = "_EFLAG_DEFAULT = ";
  protected final String TEXT_113 = ".ordinal()";
  protected final String TEXT_114 = ".VALUES.indexOf(";
  protected final String TEXT_115 = ")";
  protected final String TEXT_116 = " << ";
  protected final String TEXT_117 = "_EFLAG_OFFSET;" + NL + "" + NL + "\t/**" + NL + "\t * The array of enumeration values for '{@link ";
  protected final String TEXT_118 = " ";
  protected final String TEXT_119 = "}'" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t * @ordered" + NL + "\t */" + NL + "\tprivate static final ";
  protected final String TEXT_120 = "[] ";
  protected final String TEXT_121 = "_EFLAG_VALUES = ";
  protected final String TEXT_122 = ".values()";
  protected final String TEXT_123 = "(";
  protected final String TEXT_124 = "[])";
  protected final String TEXT_125 = ".VALUES.toArray(new ";
  protected final String TEXT_126 = "[";
  protected final String TEXT_127 = ".VALUES.size()])";
  protected final String TEXT_128 = ";" + NL;
  protected final String TEXT_129 = NL + "\t/**" + NL + "\t * The flag";
  protected final String TEXT_130 = " representing the value of the '{@link #";
  protected final String TEXT_131 = "() <em>";
  protected final String TEXT_132 = "</em>}' ";
  protected final String TEXT_133 = "." + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @see #";
  protected final String TEXT_134 = "()" + NL + "\t * @generated" + NL + "\t * @ordered" + NL + "\t */" + NL + "\tprotected static final int ";
  protected final String TEXT_135 = "_EFLAG = ";
  protected final String TEXT_136 = " << ";
  protected final String TEXT_137 = "_EFLAG_OFFSET";
  protected final String TEXT_138 = ";" + NL;
  protected final String TEXT_139 = NL + "\t/**" + NL + "\t * The cached value of the '{@link #";
  protected final String TEXT_140 = "() <em>";
  protected final String TEXT_141 = "</em>}' ";
  protected final String TEXT_142 = "." + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @see #";
  protected final String TEXT_143 = "()" + NL + "\t * @generated" + NL + "\t * @ordered" + NL + "\t */" + NL + "\tprotected ";
  protected final String TEXT_144 = " ";
  protected final String TEXT_145 = " = ";
  protected final String TEXT_146 = ";" + NL;
  protected final String TEXT_147 = NL + "\t/**" + NL + "\t * An additional set of bit flags representing the values of boolean attributes and whether unsettable features have been set." + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t * @ordered" + NL + "\t */" + NL + "\tprotected int ";
  protected final String TEXT_148 = " = 0;" + NL;
  protected final String TEXT_149 = NL + "\t/**" + NL + "\t * The flag representing whether the ";
  protected final String TEXT_150 = " ";
  protected final String TEXT_151 = " has been set." + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t * @ordered" + NL + "\t */" + NL + "\tprotected static final int ";
  protected final String TEXT_152 = "_ESETFLAG = 1 << ";
  protected final String TEXT_153 = ";" + NL;
  protected final String TEXT_154 = NL + "\t/**" + NL + "\t * This is true if the ";
  protected final String TEXT_155 = " ";
  protected final String TEXT_156 = " has been set." + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t * @ordered" + NL + "\t */" + NL + "\tprotected boolean ";
  protected final String TEXT_157 = "ESet;" + NL;
  protected final String TEXT_158 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */" + NL + "\tprivate static final int ";
  protected final String TEXT_159 = " = ";
  protected final String TEXT_160 = ".getFeatureID(";
  protected final String TEXT_161 = ") - ";
  protected final String TEXT_162 = ";" + NL;
  protected final String TEXT_163 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */" + NL + "\tprivate static final int ";
  protected final String TEXT_164 = " = ";
  protected final String TEXT_165 = ".getFeatureID(";
  protected final String TEXT_166 = ") - ";
  protected final String TEXT_167 = ";" + NL;
  protected final String TEXT_168 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */" + NL + "\tprivate static final int \"EOPERATION_OFFSET_CORRECTION\" = ";
  protected final String TEXT_169 = ".getOperationID(";
  protected final String TEXT_170 = ") - ";
  protected final String TEXT_171 = ";" + NL;
  protected final String TEXT_172 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */" + NL + "\t";
  protected final String TEXT_173 = "public";
  protected final String TEXT_174 = "protected";
  protected final String TEXT_175 = " ";
  protected final String TEXT_176 = "()" + NL + "\t{" + NL + "\t\tsuper();";
  protected final String TEXT_177 = NL + "\t\t";
  protected final String TEXT_178 = " |= ";
  protected final String TEXT_179 = "_EFLAG";
  protected final String TEXT_180 = "_DEFAULT";
  protected final String TEXT_181 = ";";
  protected final String TEXT_182 = NL + "\t}" + NL + "" + NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */";
  protected final String TEXT_183 = NL + "\t@Override";
  protected final String TEXT_184 = NL + "\tprotected ";
  protected final String TEXT_185 = " eStaticClass()" + NL + "\t{" + NL + "\t\treturn ";
  protected final String TEXT_186 = ";" + NL + "\t}" + NL;
  protected final String TEXT_187 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */";
  protected final String TEXT_188 = NL + "\t@Override";
  protected final String TEXT_189 = NL + "\tprotected int eStaticFeatureCount()" + NL + "\t{" + NL + "\t\treturn ";
  protected final String TEXT_190 = ";" + NL + "\t}" + NL;
  protected final String TEXT_191 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */";
  protected final String TEXT_192 = NL + "\t";
  protected final String TEXT_193 = "[] ";
  protected final String TEXT_194 = "();" + NL;
  protected final String TEXT_195 = NL + "\tpublic ";
  protected final String TEXT_196 = "[] ";
  protected final String TEXT_197 = "()" + NL + "\t{";
  protected final String TEXT_198 = NL + "\t\t";
  protected final String TEXT_199 = " list = (";
  protected final String TEXT_200 = ")";
  protected final String TEXT_201 = "();" + NL + "\t\tif (list.isEmpty()) return ";
  protected final String TEXT_202 = "(";
  protected final String TEXT_203 = "[])";
  protected final String TEXT_204 = "_EEMPTY_ARRAY;";
  protected final String TEXT_205 = NL + "\t\tif (";
  protected final String TEXT_206 = " == null || ";
  protected final String TEXT_207 = ".isEmpty()) return ";
  protected final String TEXT_208 = "(";
  protected final String TEXT_209 = "[])";
  protected final String TEXT_210 = "_EEMPTY_ARRAY;" + NL + "\t\t";
  protected final String TEXT_211 = " list = (";
  protected final String TEXT_212 = ")";
  protected final String TEXT_213 = ";";
  protected final String TEXT_214 = NL + "\t\tlist.shrink();" + NL + "\t\treturn (";
  protected final String TEXT_215 = "[])list.data();" + NL + "\t}" + NL;
  protected final String TEXT_216 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */";
  protected final String TEXT_217 = NL + "\t";
  protected final String TEXT_218 = " get";
  protected final String TEXT_219 = "(int index);" + NL;
  protected final String TEXT_220 = NL + "\tpublic ";
  protected final String TEXT_221 = " get";
  protected final String TEXT_222 = "(int index)" + NL + "\t{" + NL + "\t\treturn ";
  protected final String TEXT_223 = "(";
  protected final String TEXT_224 = ")";
  protected final String TEXT_225 = "().get(index);" + NL + "\t}" + NL;
  protected final String TEXT_226 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */";
  protected final String TEXT_227 = NL + "\tint get";
  protected final String TEXT_228 = "Length();" + NL;
  protected final String TEXT_229 = NL + "\tpublic int get";
  protected final String TEXT_230 = "Length()" + NL + "\t{";
  protected final String TEXT_231 = NL + "\t\treturn ";
  protected final String TEXT_232 = "().size();";
  protected final String TEXT_233 = NL + "\t\treturn ";
  protected final String TEXT_234 = " == null ? 0 : ";
  protected final String TEXT_235 = ".size();";
  protected final String TEXT_236 = NL + "\t}" + NL;
  protected final String TEXT_237 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */";
  protected final String TEXT_238 = NL + "\tvoid set";
  protected final String TEXT_239 = "(";
  protected final String TEXT_240 = "[] new";
  protected final String TEXT_241 = ");" + NL;
  protected final String TEXT_242 = NL + "\tpublic void set";
  protected final String TEXT_243 = "(";
  protected final String TEXT_244 = "[] new";
  protected final String TEXT_245 = ")" + NL + "\t{" + NL + "\t\t((";
  protected final String TEXT_246 = ")";
  protected final String TEXT_247 = "()).setData(new";
  protected final String TEXT_248 = ".length, new";
  protected final String TEXT_249 = ");" + NL + "\t}" + NL;
  protected final String TEXT_250 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */";
  protected final String TEXT_251 = NL + "\tvoid set";
  protected final String TEXT_252 = "(int index, ";
  protected final String TEXT_253 = " element);" + NL;
  protected final String TEXT_254 = NL + "\tpublic void set";
  protected final String TEXT_255 = "(int index, ";
  protected final String TEXT_256 = " element)" + NL + "\t{" + NL + "\t\t";
  protected final String TEXT_257 = "().set(index, element);" + NL + "\t}" + NL;
  protected final String TEXT_258 = NL + "\t/**" + NL + "\t * Returns the value of the '<em><b>";
  protected final String TEXT_259 = "</b></em>' ";
  protected final String TEXT_260 = ".";
  protected final String TEXT_261 = NL + "\t * The key is of type ";
  protected final String TEXT_262 = "list of {@link ";
  protected final String TEXT_263 = "}";
  protected final String TEXT_264 = "{@link ";
  protected final String TEXT_265 = "}";
  protected final String TEXT_266 = "," + NL + "\t * and the value is of type ";
  protected final String TEXT_267 = "list of {@link ";
  protected final String TEXT_268 = "}";
  protected final String TEXT_269 = "{@link ";
  protected final String TEXT_270 = "}";
  protected final String TEXT_271 = ",";
  protected final String TEXT_272 = NL + "\t * The list contents are of type {@link ";
  protected final String TEXT_273 = "}";
  protected final String TEXT_274 = ".";
  protected final String TEXT_275 = NL + "\t * The default value is <code>";
  protected final String TEXT_276 = "</code>.";
  protected final String TEXT_277 = NL + "\t * The literals are from the enumeration {@link ";
  protected final String TEXT_278 = "}.";
  protected final String TEXT_279 = NL + "\t * It is bidirectional and its opposite is '{@link ";
  protected final String TEXT_280 = "#";
  protected final String TEXT_281 = " <em>";
  protected final String TEXT_282 = "</em>}'.";
  protected final String TEXT_283 = NL + "\t * <!-- begin-user-doc -->";
  protected final String TEXT_284 = NL + "\t * <p>" + NL + "\t * If the meaning of the '<em>";
  protected final String TEXT_285 = "</em>' ";
  protected final String TEXT_286 = " isn't clear," + NL + "\t * there really should be more of a description here..." + NL + "\t * </p>";
  protected final String TEXT_287 = NL + "\t * <!-- end-user-doc -->";
  protected final String TEXT_288 = NL + "\t * <!-- begin-model-doc -->" + NL + "\t * ";
  protected final String TEXT_289 = NL + "\t * <!-- end-model-doc -->";
  protected final String TEXT_290 = NL + "\t * @return the value of the '<em>";
  protected final String TEXT_291 = "</em>' ";
  protected final String TEXT_292 = ".";
  protected final String TEXT_293 = NL + "\t * @see ";
  protected final String TEXT_294 = NL + "\t * @see #isSet";
  protected final String TEXT_295 = "()";
  protected final String TEXT_296 = NL + "\t * @see #unset";
  protected final String TEXT_297 = "()";
  protected final String TEXT_298 = NL + "\t * @see #set";
  protected final String TEXT_299 = "(";
  protected final String TEXT_300 = ")";
  protected final String TEXT_301 = NL + "\t * @see ";
  protected final String TEXT_302 = "#get";
  protected final String TEXT_303 = "()";
  protected final String TEXT_304 = NL + "\t * @see ";
  protected final String TEXT_305 = "#";
  protected final String TEXT_306 = NL + "\t * @model ";
  protected final String TEXT_307 = NL + "\t *        ";
  protected final String TEXT_308 = NL + "\t * @model";
  protected final String TEXT_309 = NL + "\t * @generated" + NL + "\t */";
  protected final String TEXT_310 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */";
  protected final String TEXT_311 = NL + "\t";
  protected final String TEXT_312 = " ";
  protected final String TEXT_313 = "();" + NL;
  protected final String TEXT_314 = NL + "\t@SuppressWarnings(\"unchecked\")";
  protected final String TEXT_315 = NL + "\tpublic ";
  protected final String TEXT_316 = " ";
  protected final String TEXT_317 = "_";
  protected final String TEXT_318 = "()" + NL + "\t{";
  protected final String TEXT_319 = NL + "\t\treturn ";
  protected final String TEXT_320 = "(";
  protected final String TEXT_321 = "(";
  protected final String TEXT_322 = ")eDynamicGet(";
  protected final String TEXT_323 = ", ";
  protected final String TEXT_324 = ", true, ";
  protected final String TEXT_325 = ")";
  protected final String TEXT_326 = ").";
  protected final String TEXT_327 = "()";
  protected final String TEXT_328 = ";";
  protected final String TEXT_329 = NL + "\t\treturn ";
  protected final String TEXT_330 = "(";
  protected final String TEXT_331 = "(";
  protected final String TEXT_332 = ")eGet(";
  protected final String TEXT_333 = ", true)";
  protected final String TEXT_334 = ").";
  protected final String TEXT_335 = "()";
  protected final String TEXT_336 = ";";
  protected final String TEXT_337 = NL + "\t\treturn ";
  protected final String TEXT_338 = "(";
  protected final String TEXT_339 = "(";
  protected final String TEXT_340 = ")";
  protected final String TEXT_341 = "__ESETTING_DELEGATE.dynamicGet(this, null, 0, true, false)";
  protected final String TEXT_342 = ").";
  protected final String TEXT_343 = "()";
  protected final String TEXT_344 = ";";
  protected final String TEXT_345 = NL + "\t\t";
  protected final String TEXT_346 = " ";
  protected final String TEXT_347 = " = (";
  protected final String TEXT_348 = ")eVirtualGet(";
  protected final String TEXT_349 = ");";
  protected final String TEXT_350 = NL + "\t\tif (";
  protected final String TEXT_351 = " == null)" + NL + "\t\t{";
  protected final String TEXT_352 = NL + "\t\t\teVirtualSet(";
  protected final String TEXT_353 = ", ";
  protected final String TEXT_354 = " = new ";
  protected final String TEXT_355 = ");";
  protected final String TEXT_356 = NL + "\t\t\t";
  protected final String TEXT_357 = " = new ";
  protected final String TEXT_358 = ";";
  protected final String TEXT_359 = NL + "\t\t}" + NL + "\t\treturn ";
  protected final String TEXT_360 = ";";
  protected final String TEXT_361 = NL + "\t\tif (eContainerFeatureID() != ";
  protected final String TEXT_362 = ") return null;" + NL + "\t\treturn (";
  protected final String TEXT_363 = ")eContainer();";
  protected final String TEXT_364 = NL + "\t\t";
  protected final String TEXT_365 = " ";
  protected final String TEXT_366 = " = (";
  protected final String TEXT_367 = ")eVirtualGet(";
  protected final String TEXT_368 = ", ";
  protected final String TEXT_369 = ");";
  protected final String TEXT_370 = NL + "\t\tif (";
  protected final String TEXT_371 = " != null && ";
  protected final String TEXT_372 = ".eIsProxy())" + NL + "\t\t{" + NL + "\t\t\t";
  protected final String TEXT_373 = " old";
  protected final String TEXT_374 = " = (";
  protected final String TEXT_375 = ")";
  protected final String TEXT_376 = ";" + NL + "\t\t\t";
  protected final String TEXT_377 = " = ";
  protected final String TEXT_378 = "eResolveProxy(old";
  protected final String TEXT_379 = ");" + NL + "\t\t\tif (";
  protected final String TEXT_380 = " != old";
  protected final String TEXT_381 = ")" + NL + "\t\t\t{";
  protected final String TEXT_382 = NL + "\t\t\t\t";
  protected final String TEXT_383 = " new";
  protected final String TEXT_384 = " = (";
  protected final String TEXT_385 = ")";
  protected final String TEXT_386 = ";";
  protected final String TEXT_387 = NL + "\t\t\t\t";
  protected final String TEXT_388 = " msgs = old";
  protected final String TEXT_389 = ".eInverseRemove(this, EOPPOSITE_FEATURE_BASE - ";
  protected final String TEXT_390 = ", null, null);";
  protected final String TEXT_391 = NL + "\t\t\t\t";
  protected final String TEXT_392 = " msgs =  old";
  protected final String TEXT_393 = ".eInverseRemove(this, ";
  protected final String TEXT_394 = ", ";
  protected final String TEXT_395 = ".class, null);";
  protected final String TEXT_396 = NL + "\t\t\t\tif (new";
  protected final String TEXT_397 = ".eInternalContainer() == null)" + NL + "\t\t\t\t{";
  protected final String TEXT_398 = NL + "\t\t\t\t\tmsgs = new";
  protected final String TEXT_399 = ".eInverseAdd(this, EOPPOSITE_FEATURE_BASE - ";
  protected final String TEXT_400 = ", null, msgs);";
  protected final String TEXT_401 = NL + "\t\t\t\t\tmsgs =  new";
  protected final String TEXT_402 = ".eInverseAdd(this, ";
  protected final String TEXT_403 = ", ";
  protected final String TEXT_404 = ".class, msgs);";
  protected final String TEXT_405 = NL + "\t\t\t\t}" + NL + "\t\t\t\tif (msgs != null) msgs.dispatch();";
  protected final String TEXT_406 = NL + "\t\t\t\teVirtualSet(";
  protected final String TEXT_407 = ", ";
  protected final String TEXT_408 = ");";
  protected final String TEXT_409 = NL + "\t\t\t\tif (eNotificationRequired())" + NL + "\t\t\t\t\teNotify(new ";
  protected final String TEXT_410 = "(this, ";
  protected final String TEXT_411 = ".RESOLVE, ";
  protected final String TEXT_412 = ", old";
  protected final String TEXT_413 = ", ";
  protected final String TEXT_414 = "));";
  protected final String TEXT_415 = NL + "\t\t\t}" + NL + "\t\t}";
  protected final String TEXT_416 = NL + "\t\treturn (";
  protected final String TEXT_417 = ")eVirtualGet(";
  protected final String TEXT_418 = ", ";
  protected final String TEXT_419 = ");";
  protected final String TEXT_420 = NL + "\t\treturn (";
  protected final String TEXT_421 = " & ";
  protected final String TEXT_422 = "_EFLAG) != 0;";
  protected final String TEXT_423 = NL + "\t\treturn ";
  protected final String TEXT_424 = "_EFLAG_VALUES[(";
  protected final String TEXT_425 = " & ";
  protected final String TEXT_426 = "_EFLAG) >>> ";
  protected final String TEXT_427 = "_EFLAG_OFFSET];";
  protected final String TEXT_428 = NL + "\t\treturn ";
  protected final String TEXT_429 = ";";
  protected final String TEXT_430 = NL + "\t\t";
  protected final String TEXT_431 = " ";
  protected final String TEXT_432 = " = basicGet";
  protected final String TEXT_433 = "();" + NL + "\t\treturn ";
  protected final String TEXT_434 = " != null && ";
  protected final String TEXT_435 = ".eIsProxy() ? ";
  protected final String TEXT_436 = "eResolveProxy((";
  protected final String TEXT_437 = ")";
  protected final String TEXT_438 = ") : ";
  protected final String TEXT_439 = ";";
  protected final String TEXT_440 = NL + "\t\treturn new ";
  protected final String TEXT_441 = "((";
  protected final String TEXT_442 = ".Internal)((";
  protected final String TEXT_443 = ".Internal.Wrapper)get";
  protected final String TEXT_444 = "()).featureMap().";
  protected final String TEXT_445 = "list(";
  protected final String TEXT_446 = "));";
  protected final String TEXT_447 = NL + "\t\treturn (";
  protected final String TEXT_448 = ")get";
  protected final String TEXT_449 = "().";
  protected final String TEXT_450 = "list(";
  protected final String TEXT_451 = ");";
  protected final String TEXT_452 = NL + "\t\treturn ((";
  protected final String TEXT_453 = ".Internal.Wrapper)get";
  protected final String TEXT_454 = "()).featureMap().list(";
  protected final String TEXT_455 = ");";
  protected final String TEXT_456 = NL + "\t\treturn get";
  protected final String TEXT_457 = "().list(";
  protected final String TEXT_458 = ");";
  protected final String TEXT_459 = NL + "\t\treturn ";
  protected final String TEXT_460 = "(";
  protected final String TEXT_461 = "(";
  protected final String TEXT_462 = ")";
  protected final String TEXT_463 = "((";
  protected final String TEXT_464 = ".Internal.Wrapper)get";
  protected final String TEXT_465 = "()).featureMap().get(";
  protected final String TEXT_466 = ", true)";
  protected final String TEXT_467 = ").";
  protected final String TEXT_468 = "()";
  protected final String TEXT_469 = ";";
  protected final String TEXT_470 = NL + "\t\treturn ";
  protected final String TEXT_471 = "(";
  protected final String TEXT_472 = "(";
  protected final String TEXT_473 = ")";
  protected final String TEXT_474 = "get";
  protected final String TEXT_475 = "().get(";
  protected final String TEXT_476 = ", true)";
  protected final String TEXT_477 = ").";
  protected final String TEXT_478 = "()";
  protected final String TEXT_479 = ";";
  protected final String TEXT_480 = NL + "\t\t";
  protected final String TEXT_481 = NL + "\t\t// TODO: implement this method to return the '";
  protected final String TEXT_482 = "' ";
  protected final String TEXT_483 = NL + "\t\t// Ensure that you remove @generated or mark it @generated NOT";
  protected final String TEXT_484 = NL + "\t\t// The list is expected to implement org.eclipse.emf.ecore.util.InternalEList and org.eclipse.emf.ecore.EStructuralFeature.Setting" + NL + "\t\t// so it's likely that an appropriate subclass of org.eclipse.emf.ecore.util.";
  protected final String TEXT_485 = "EcoreEMap";
  protected final String TEXT_486 = "BasicFeatureMap";
  protected final String TEXT_487 = "EcoreEList";
  protected final String TEXT_488 = " should be used.";
  protected final String TEXT_489 = NL + "\t\tthrow new UnsupportedOperationException();";
  protected final String TEXT_490 = NL + "\t}" + NL;
  protected final String TEXT_491 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */";
  protected final String TEXT_492 = NL + "\tpublic ";
  protected final String TEXT_493 = " basicGet";
  protected final String TEXT_494 = "()" + NL + "\t{";
  protected final String TEXT_495 = NL + "\t\treturn (";
  protected final String TEXT_496 = ")eDynamicGet(";
  protected final String TEXT_497 = ", ";
  protected final String TEXT_498 = ", false, ";
  protected final String TEXT_499 = ");";
  protected final String TEXT_500 = NL + "\t\treturn ";
  protected final String TEXT_501 = "(";
  protected final String TEXT_502 = "(";
  protected final String TEXT_503 = ")";
  protected final String TEXT_504 = "__ESETTING_DELEGATE.dynamicGet(this, null, 0, false, false)";
  protected final String TEXT_505 = ").";
  protected final String TEXT_506 = "()";
  protected final String TEXT_507 = ";";
  protected final String TEXT_508 = NL + "\t\tif (eContainerFeatureID() != ";
  protected final String TEXT_509 = ") return null;" + NL + "\t\treturn (";
  protected final String TEXT_510 = ")eInternalContainer();";
  protected final String TEXT_511 = NL + "\t\treturn (";
  protected final String TEXT_512 = ")eVirtualGet(";
  protected final String TEXT_513 = ");";
  protected final String TEXT_514 = NL + "\t\treturn ";
  protected final String TEXT_515 = ";";
  protected final String TEXT_516 = NL + "\t\treturn (";
  protected final String TEXT_517 = ")((";
  protected final String TEXT_518 = ".Internal.Wrapper)get";
  protected final String TEXT_519 = "()).featureMap().get(";
  protected final String TEXT_520 = ", false);";
  protected final String TEXT_521 = NL + "\t\treturn (";
  protected final String TEXT_522 = ")get";
  protected final String TEXT_523 = "().get(";
  protected final String TEXT_524 = ", false);";
  protected final String TEXT_525 = NL + "\t\t// TODO: implement this method to return the '";
  protected final String TEXT_526 = "' ";
  protected final String TEXT_527 = NL + "\t\t// -> do not perform proxy resolution" + NL + "\t\t// Ensure that you remove @generated or mark it @generated NOT" + NL + "\t\tthrow new UnsupportedOperationException();";
  protected final String TEXT_528 = NL + "\t}" + NL;
  protected final String TEXT_529 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */";
  protected final String TEXT_530 = NL + "\tpublic ";
  protected final String TEXT_531 = " basicSet";
  protected final String TEXT_532 = "(";
  protected final String TEXT_533 = " new";
  protected final String TEXT_534 = ", ";
  protected final String TEXT_535 = " msgs)" + NL + "\t{";
  protected final String TEXT_536 = NL + "\t\tmsgs = eBasicSetContainer((";
  protected final String TEXT_537 = ")new";
  protected final String TEXT_538 = ", ";
  protected final String TEXT_539 = ", msgs);";
  protected final String TEXT_540 = NL + "\t\treturn msgs;";
  protected final String TEXT_541 = NL + "\t\tmsgs = eDynamicInverseAdd((";
  protected final String TEXT_542 = ")new";
  protected final String TEXT_543 = ", ";
  protected final String TEXT_544 = ", msgs);";
  protected final String TEXT_545 = NL + "\t\treturn msgs;";
  protected final String TEXT_546 = NL + "\t\tObject old";
  protected final String TEXT_547 = " = eVirtualSet(";
  protected final String TEXT_548 = ", new";
  protected final String TEXT_549 = ");";
  protected final String TEXT_550 = NL + "\t\t";
  protected final String TEXT_551 = " old";
  protected final String TEXT_552 = " = ";
  protected final String TEXT_553 = ";" + NL + "\t\t";
  protected final String TEXT_554 = " = new";
  protected final String TEXT_555 = ";";
  protected final String TEXT_556 = NL + "\t\tboolean isSetChange = old";
  protected final String TEXT_557 = " == EVIRTUAL_NO_VALUE;";
  protected final String TEXT_558 = NL + "\t\tboolean old";
  protected final String TEXT_559 = "ESet = (";
  protected final String TEXT_560 = " & ";
  protected final String TEXT_561 = "_ESETFLAG) != 0;";
  protected final String TEXT_562 = NL + "\t\t";
  protected final String TEXT_563 = " |= ";
  protected final String TEXT_564 = "_ESETFLAG;";
  protected final String TEXT_565 = NL + "\t\tboolean old";
  protected final String TEXT_566 = "ESet = ";
  protected final String TEXT_567 = "ESet;";
  protected final String TEXT_568 = NL + "\t\t";
  protected final String TEXT_569 = "ESet = true;";
  protected final String TEXT_570 = NL + "\t\tif (eNotificationRequired())" + NL + "\t\t{";
  protected final String TEXT_571 = NL + "\t\t\t";
  protected final String TEXT_572 = " notification = new ";
  protected final String TEXT_573 = "(this, ";
  protected final String TEXT_574 = ".SET, ";
  protected final String TEXT_575 = ", ";
  protected final String TEXT_576 = "isSetChange ? null : old";
  protected final String TEXT_577 = "old";
  protected final String TEXT_578 = ", new";
  protected final String TEXT_579 = ", ";
  protected final String TEXT_580 = "isSetChange";
  protected final String TEXT_581 = "!old";
  protected final String TEXT_582 = "ESet";
  protected final String TEXT_583 = ");";
  protected final String TEXT_584 = NL + "\t\t\t";
  protected final String TEXT_585 = " notification = new ";
  protected final String TEXT_586 = "(this, ";
  protected final String TEXT_587 = ".SET, ";
  protected final String TEXT_588 = ", ";
  protected final String TEXT_589 = "old";
  protected final String TEXT_590 = " == EVIRTUAL_NO_VALUE ? null : old";
  protected final String TEXT_591 = "old";
  protected final String TEXT_592 = ", new";
  protected final String TEXT_593 = ");";
  protected final String TEXT_594 = NL + "\t\t\tif (msgs == null) msgs = notification; else msgs.add(notification);" + NL + "\t\t}";
  protected final String TEXT_595 = NL + "\t\treturn msgs;";
  protected final String TEXT_596 = NL + "\t\treturn ((";
  protected final String TEXT_597 = ".Internal)((";
  protected final String TEXT_598 = ".Internal.Wrapper)get";
  protected final String TEXT_599 = "()).featureMap()).basicAdd(";
  protected final String TEXT_600 = ", new";
  protected final String TEXT_601 = ", msgs);";
  protected final String TEXT_602 = NL + "\t\treturn ((";
  protected final String TEXT_603 = ".Internal)get";
  protected final String TEXT_604 = "()).basicAdd(";
  protected final String TEXT_605 = ", new";
  protected final String TEXT_606 = ", msgs);";
  protected final String TEXT_607 = NL + "\t\t// TODO: implement this method to set the contained '";
  protected final String TEXT_608 = "' ";
  protected final String TEXT_609 = NL + "\t\t// -> this method is automatically invoked to keep the containment relationship in synch" + NL + "\t\t// -> do not modify other features" + NL + "\t\t// -> return msgs, after adding any generated Notification to it (if it is null, a NotificationChain object must be created first)" + NL + "\t\t// Ensure that you remove @generated or mark it @generated NOT" + NL + "\t\tthrow new UnsupportedOperationException();";
  protected final String TEXT_610 = NL + "\t}" + NL;
  protected final String TEXT_611 = NL + "\t/**" + NL + "\t * Sets the value of the '{@link ";
  protected final String TEXT_612 = "#";
  protected final String TEXT_613 = " <em>";
  protected final String TEXT_614 = "</em>}' ";
  protected final String TEXT_615 = ".";
  protected final String TEXT_616 = NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @param value the new value of the '<em>";
  protected final String TEXT_617 = "</em>' ";
  protected final String TEXT_618 = ".";
  protected final String TEXT_619 = NL + "\t * @see ";
  protected final String TEXT_620 = NL + "\t * @see #isSet";
  protected final String TEXT_621 = "()";
  protected final String TEXT_622 = NL + "\t * @see #unset";
  protected final String TEXT_623 = "()";
  protected final String TEXT_624 = NL + "\t * @see #";
  protected final String TEXT_625 = "()" + NL + "\t * @generated" + NL + "\t */";
  protected final String TEXT_626 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */";
  protected final String TEXT_627 = NL + "\tvoid set";
  protected final String TEXT_628 = "(";
  protected final String TEXT_629 = " value);" + NL;
  protected final String TEXT_630 = NL + "\tpublic void set";
  protected final String TEXT_631 = "_";
  protected final String TEXT_632 = "(";
  protected final String TEXT_633 = " ";
  protected final String TEXT_634 = ")" + NL + "\t{";
  protected final String TEXT_635 = NL + "\t\teDynamicSet(";
  protected final String TEXT_636 = ", ";
  protected final String TEXT_637 = ", ";
  protected final String TEXT_638 = "new ";
  protected final String TEXT_639 = "(";
  protected final String TEXT_640 = "new";
  protected final String TEXT_641 = ")";
  protected final String TEXT_642 = ");";
  protected final String TEXT_643 = NL + "\t\teSet(";
  protected final String TEXT_644 = ", ";
  protected final String TEXT_645 = "new ";
  protected final String TEXT_646 = "(";
  protected final String TEXT_647 = "new";
  protected final String TEXT_648 = ")";
  protected final String TEXT_649 = ");";
  protected final String TEXT_650 = NL + "\t\t";
  protected final String TEXT_651 = "__ESETTING_DELEGATE.dynamicSet(this, null, 0, ";
  protected final String TEXT_652 = "new ";
  protected final String TEXT_653 = "(";
  protected final String TEXT_654 = "new";
  protected final String TEXT_655 = ")";
  protected final String TEXT_656 = ");";
  protected final String TEXT_657 = NL + "\t\tif (new";
  protected final String TEXT_658 = " != eInternalContainer() || (eContainerFeatureID() != ";
  protected final String TEXT_659 = " && new";
  protected final String TEXT_660 = " != null))" + NL + "\t\t{" + NL + "\t\t\tif (";
  protected final String TEXT_661 = ".isAncestor(this, ";
  protected final String TEXT_662 = "new";
  protected final String TEXT_663 = "))" + NL + "\t\t\t\tthrow new ";
  protected final String TEXT_664 = "(\"Recursive containment not allowed for \" + toString());";
  protected final String TEXT_665 = NL + "\t\t\t";
  protected final String TEXT_666 = " msgs = null;" + NL + "\t\t\tif (eInternalContainer() != null)" + NL + "\t\t\t\tmsgs = eBasicRemoveFromContainer(msgs);" + NL + "\t\t\tif (new";
  protected final String TEXT_667 = " != null)" + NL + "\t\t\t\tmsgs = ((";
  protected final String TEXT_668 = ")new";
  protected final String TEXT_669 = ").eInverseAdd(this, ";
  protected final String TEXT_670 = ", ";
  protected final String TEXT_671 = ".class, msgs);" + NL + "\t\t\tmsgs = basicSet";
  protected final String TEXT_672 = "(";
  protected final String TEXT_673 = "new";
  protected final String TEXT_674 = ", msgs);" + NL + "\t\t\tif (msgs != null) msgs.dispatch();" + NL + "\t\t}";
  protected final String TEXT_675 = NL + "\t\telse if (eNotificationRequired())" + NL + "\t\t\teNotify(new ";
  protected final String TEXT_676 = "(this, ";
  protected final String TEXT_677 = ".SET, ";
  protected final String TEXT_678 = ", new";
  protected final String TEXT_679 = ", new";
  protected final String TEXT_680 = "));";
  protected final String TEXT_681 = NL + "\t\t";
  protected final String TEXT_682 = " ";
  protected final String TEXT_683 = " = (";
  protected final String TEXT_684 = ")eVirtualGet(";
  protected final String TEXT_685 = ");";
  protected final String TEXT_686 = NL + "\t\tif (new";
  protected final String TEXT_687 = " != ";
  protected final String TEXT_688 = ")" + NL + "\t\t{" + NL + "\t\t\t";
  protected final String TEXT_689 = " msgs = null;" + NL + "\t\t\tif (";
  protected final String TEXT_690 = " != null)";
  protected final String TEXT_691 = NL + "\t\t\t\tmsgs = ((";
  protected final String TEXT_692 = ")";
  protected final String TEXT_693 = ").eInverseRemove(this, EOPPOSITE_FEATURE_BASE - ";
  protected final String TEXT_694 = ", null, msgs);" + NL + "\t\t\tif (new";
  protected final String TEXT_695 = " != null)" + NL + "\t\t\t\tmsgs = ((";
  protected final String TEXT_696 = ")new";
  protected final String TEXT_697 = ").eInverseAdd(this, EOPPOSITE_FEATURE_BASE - ";
  protected final String TEXT_698 = ", null, msgs);";
  protected final String TEXT_699 = NL + "\t\t\t\tmsgs = ((";
  protected final String TEXT_700 = ")";
  protected final String TEXT_701 = ").eInverseRemove(this, ";
  protected final String TEXT_702 = ", ";
  protected final String TEXT_703 = ".class, msgs);" + NL + "\t\t\tif (new";
  protected final String TEXT_704 = " != null)" + NL + "\t\t\t\tmsgs = ((";
  protected final String TEXT_705 = ")new";
  protected final String TEXT_706 = ").eInverseAdd(this, ";
  protected final String TEXT_707 = ", ";
  protected final String TEXT_708 = ".class, msgs);";
  protected final String TEXT_709 = NL + "\t\t\tmsgs = basicSet";
  protected final String TEXT_710 = "(";
  protected final String TEXT_711 = "new";
  protected final String TEXT_712 = ", msgs);" + NL + "\t\t\tif (msgs != null) msgs.dispatch();" + NL + "\t\t}";
  protected final String TEXT_713 = NL + "\t\telse" + NL + "\t\t{";
  protected final String TEXT_714 = NL + "\t\t\tboolean old";
  protected final String TEXT_715 = "ESet = eVirtualIsSet(";
  protected final String TEXT_716 = ");";
  protected final String TEXT_717 = NL + "\t\t\tboolean old";
  protected final String TEXT_718 = "ESet = (";
  protected final String TEXT_719 = " & ";
  protected final String TEXT_720 = "_ESETFLAG) != 0;";
  protected final String TEXT_721 = NL + "\t\t\t";
  protected final String TEXT_722 = " |= ";
  protected final String TEXT_723 = "_ESETFLAG;";
  protected final String TEXT_724 = NL + "\t\t\tboolean old";
  protected final String TEXT_725 = "ESet = ";
  protected final String TEXT_726 = "ESet;";
  protected final String TEXT_727 = NL + "\t\t\t";
  protected final String TEXT_728 = "ESet = true;";
  protected final String TEXT_729 = NL + "\t\t\tif (eNotificationRequired())" + NL + "\t\t\t\teNotify(new ";
  protected final String TEXT_730 = "(this, ";
  protected final String TEXT_731 = ".SET, ";
  protected final String TEXT_732 = ", new";
  protected final String TEXT_733 = ", new";
  protected final String TEXT_734 = ", !old";
  protected final String TEXT_735 = "ESet));";
  protected final String TEXT_736 = NL + "\t\t}";
  protected final String TEXT_737 = NL + "\t\telse if (eNotificationRequired())" + NL + "\t\t\teNotify(new ";
  protected final String TEXT_738 = "(this, ";
  protected final String TEXT_739 = ".SET, ";
  protected final String TEXT_740 = ", new";
  protected final String TEXT_741 = ", new";
  protected final String TEXT_742 = "));";
  protected final String TEXT_743 = NL + "\t\t";
  protected final String TEXT_744 = " old";
  protected final String TEXT_745 = " = (";
  protected final String TEXT_746 = " & ";
  protected final String TEXT_747 = "_EFLAG) != 0;";
  protected final String TEXT_748 = NL + "\t\t";
  protected final String TEXT_749 = " old";
  protected final String TEXT_750 = " = ";
  protected final String TEXT_751 = "_EFLAG_VALUES[(";
  protected final String TEXT_752 = " & ";
  protected final String TEXT_753 = "_EFLAG) >>> ";
  protected final String TEXT_754 = "_EFLAG_OFFSET];";
  protected final String TEXT_755 = NL + "\t\tif (new";
  protected final String TEXT_756 = ") ";
  protected final String TEXT_757 = " |= ";
  protected final String TEXT_758 = "_EFLAG; else ";
  protected final String TEXT_759 = " &= ~";
  protected final String TEXT_760 = "_EFLAG;";
  protected final String TEXT_761 = NL + "\t\tif (new";
  protected final String TEXT_762 = " == null) new";
  protected final String TEXT_763 = " = ";
  protected final String TEXT_764 = "_EDEFAULT;" + NL + "\t\t";
  protected final String TEXT_765 = " = ";
  protected final String TEXT_766 = " & ~";
  protected final String TEXT_767 = "_EFLAG | ";
  protected final String TEXT_768 = "new";
  protected final String TEXT_769 = ".ordinal()";
  protected final String TEXT_770 = ".VALUES.indexOf(new";
  protected final String TEXT_771 = ")";
  protected final String TEXT_772 = " << ";
  protected final String TEXT_773 = "_EFLAG_OFFSET;";
  protected final String TEXT_774 = NL + "\t\t";
  protected final String TEXT_775 = " old";
  protected final String TEXT_776 = " = ";
  protected final String TEXT_777 = ";";
  protected final String TEXT_778 = NL + "\t\t";
  protected final String TEXT_779 = " ";
  protected final String TEXT_780 = " = new";
  protected final String TEXT_781 = " == null ? ";
  protected final String TEXT_782 = " : new";
  protected final String TEXT_783 = ";";
  protected final String TEXT_784 = NL + "\t\t";
  protected final String TEXT_785 = " = new";
  protected final String TEXT_786 = " == null ? ";
  protected final String TEXT_787 = " : new";
  protected final String TEXT_788 = ";";
  protected final String TEXT_789 = NL + "\t\t";
  protected final String TEXT_790 = " ";
  protected final String TEXT_791 = " = ";
  protected final String TEXT_792 = "new";
  protected final String TEXT_793 = ";";
  protected final String TEXT_794 = NL + "\t\t";
  protected final String TEXT_795 = " = ";
  protected final String TEXT_796 = "new";
  protected final String TEXT_797 = ";";
  protected final String TEXT_798 = NL + "\t\tObject old";
  protected final String TEXT_799 = " = eVirtualSet(";
  protected final String TEXT_800 = ", ";
  protected final String TEXT_801 = ");";
  protected final String TEXT_802 = NL + "\t\tboolean isSetChange = old";
  protected final String TEXT_803 = " == EVIRTUAL_NO_VALUE;";
  protected final String TEXT_804 = NL + "\t\tboolean old";
  protected final String TEXT_805 = "ESet = (";
  protected final String TEXT_806 = " & ";
  protected final String TEXT_807 = "_ESETFLAG) != 0;";
  protected final String TEXT_808 = NL + "\t\t";
  protected final String TEXT_809 = " |= ";
  protected final String TEXT_810 = "_ESETFLAG;";
  protected final String TEXT_811 = NL + "\t\tboolean old";
  protected final String TEXT_812 = "ESet = ";
  protected final String TEXT_813 = "ESet;";
  protected final String TEXT_814 = NL + "\t\t";
  protected final String TEXT_815 = "ESet = true;";
  protected final String TEXT_816 = NL + "\t\tif (eNotificationRequired())" + NL + "\t\t\teNotify(new ";
  protected final String TEXT_817 = "(this, ";
  protected final String TEXT_818 = ".SET, ";
  protected final String TEXT_819 = ", ";
  protected final String TEXT_820 = "isSetChange ? ";
  protected final String TEXT_821 = " : old";
  protected final String TEXT_822 = "old";
  protected final String TEXT_823 = ", ";
  protected final String TEXT_824 = "new";
  protected final String TEXT_825 = ", ";
  protected final String TEXT_826 = "isSetChange";
  protected final String TEXT_827 = "!old";
  protected final String TEXT_828 = "ESet";
  protected final String TEXT_829 = "));";
  protected final String TEXT_830 = NL + "\t\tif (eNotificationRequired())" + NL + "\t\t\teNotify(new ";
  protected final String TEXT_831 = "(this, ";
  protected final String TEXT_832 = ".SET, ";
  protected final String TEXT_833 = ", ";
  protected final String TEXT_834 = "old";
  protected final String TEXT_835 = " == EVIRTUAL_NO_VALUE ? ";
  protected final String TEXT_836 = " : old";
  protected final String TEXT_837 = "old";
  protected final String TEXT_838 = ", ";
  protected final String TEXT_839 = "new";
  protected final String TEXT_840 = "));";
  protected final String TEXT_841 = NL + "\t\t((";
  protected final String TEXT_842 = ".Internal)((";
  protected final String TEXT_843 = ".Internal.Wrapper)get";
  protected final String TEXT_844 = "()).featureMap()).set(";
  protected final String TEXT_845 = ", ";
  protected final String TEXT_846 = "new ";
  protected final String TEXT_847 = "(";
  protected final String TEXT_848 = "new";
  protected final String TEXT_849 = ")";
  protected final String TEXT_850 = ");";
  protected final String TEXT_851 = NL + "\t\t((";
  protected final String TEXT_852 = ".Internal)get";
  protected final String TEXT_853 = "()).set(";
  protected final String TEXT_854 = ", ";
  protected final String TEXT_855 = "new ";
  protected final String TEXT_856 = "(";
  protected final String TEXT_857 = "new";
  protected final String TEXT_858 = ")";
  protected final String TEXT_859 = ");";
  protected final String TEXT_860 = NL + "\t\t";
  protected final String TEXT_861 = NL + "\t\t// TODO: implement this method to set the '";
  protected final String TEXT_862 = "' ";
  protected final String TEXT_863 = NL + "\t\t// Ensure that you remove @generated or mark it @generated NOT" + NL + "\t\tthrow new UnsupportedOperationException();";
  protected final String TEXT_864 = NL + "\t}" + NL;
  protected final String TEXT_865 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */";
  protected final String TEXT_866 = NL + "\tpublic ";
  protected final String TEXT_867 = " basicUnset";
  protected final String TEXT_868 = "(";
  protected final String TEXT_869 = " msgs)" + NL + "\t{";
  protected final String TEXT_870 = NL + "\t\treturn eDynamicInverseRemove((";
  protected final String TEXT_871 = ")";
  protected final String TEXT_872 = "basicGet";
  protected final String TEXT_873 = "(), ";
  protected final String TEXT_874 = ", msgs);";
  protected final String TEXT_875 = "Object old";
  protected final String TEXT_876 = " = ";
  protected final String TEXT_877 = "eVirtualUnset(";
  protected final String TEXT_878 = ");";
  protected final String TEXT_879 = NL + "\t\t";
  protected final String TEXT_880 = " old";
  protected final String TEXT_881 = " = ";
  protected final String TEXT_882 = ";";
  protected final String TEXT_883 = NL + "\t\t";
  protected final String TEXT_884 = " = null;";
  protected final String TEXT_885 = NL + "\t\tboolean isSetChange = old";
  protected final String TEXT_886 = " != EVIRTUAL_NO_VALUE;";
  protected final String TEXT_887 = NL + "\t\tboolean old";
  protected final String TEXT_888 = "ESet = (";
  protected final String TEXT_889 = " & ";
  protected final String TEXT_890 = "_ESETFLAG) != 0;";
  protected final String TEXT_891 = NL + "\t\t";
  protected final String TEXT_892 = " &= ~";
  protected final String TEXT_893 = "_ESETFLAG;";
  protected final String TEXT_894 = NL + "\t\tboolean old";
  protected final String TEXT_895 = "ESet = ";
  protected final String TEXT_896 = "ESet;";
  protected final String TEXT_897 = NL + "\t\t";
  protected final String TEXT_898 = "ESet = false;";
  protected final String TEXT_899 = NL + "\t\tif (eNotificationRequired())" + NL + "\t\t{" + NL + "\t\t\t";
  protected final String TEXT_900 = " notification = new ";
  protected final String TEXT_901 = "(this, ";
  protected final String TEXT_902 = ".UNSET, ";
  protected final String TEXT_903 = ", ";
  protected final String TEXT_904 = "isSetChange ? old";
  protected final String TEXT_905 = " : null";
  protected final String TEXT_906 = "old";
  protected final String TEXT_907 = ", null, ";
  protected final String TEXT_908 = "isSetChange";
  protected final String TEXT_909 = "old";
  protected final String TEXT_910 = "ESet";
  protected final String TEXT_911 = ");" + NL + "\t\t\tif (msgs == null) msgs = notification; else msgs.add(notification);" + NL + "\t\t}" + NL + "\t\treturn msgs;";
  protected final String TEXT_912 = NL + "\t\t// TODO: implement this method to unset the contained '";
  protected final String TEXT_913 = "' ";
  protected final String TEXT_914 = NL + "\t\t// -> this method is automatically invoked to keep the containment relationship in synch" + NL + "\t\t// -> do not modify other features" + NL + "\t\t// -> return msgs, after adding any generated Notification to it (if it is null, a NotificationChain object must be created first)" + NL + "\t\t// Ensure that you remove @generated or mark it @generated NOT" + NL + "\t\tthrow new UnsupportedOperationException();";
  protected final String TEXT_915 = NL + "\t}" + NL;
  protected final String TEXT_916 = NL + "\t/**" + NL + "\t * Unsets the value of the '{@link ";
  protected final String TEXT_917 = "#";
  protected final String TEXT_918 = " <em>";
  protected final String TEXT_919 = "</em>}' ";
  protected final String TEXT_920 = ".";
  protected final String TEXT_921 = NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->";
  protected final String TEXT_922 = NL + "\t * @see #isSet";
  protected final String TEXT_923 = "()";
  protected final String TEXT_924 = NL + "\t * @see #";
  protected final String TEXT_925 = "()";
  protected final String TEXT_926 = NL + "\t * @see #set";
  protected final String TEXT_927 = "(";
  protected final String TEXT_928 = ")";
  protected final String TEXT_929 = NL + "\t * @generated" + NL + "\t */";
  protected final String TEXT_930 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */";
  protected final String TEXT_931 = NL + "\tvoid unset";
  protected final String TEXT_932 = "();" + NL;
  protected final String TEXT_933 = NL + "\tpublic void unset";
  protected final String TEXT_934 = "_";
  protected final String TEXT_935 = "()" + NL + "\t{";
  protected final String TEXT_936 = NL + "\t\teDynamicUnset(";
  protected final String TEXT_937 = ", ";
  protected final String TEXT_938 = ");";
  protected final String TEXT_939 = NL + "\t\teUnset(";
  protected final String TEXT_940 = ");";
  protected final String TEXT_941 = NL + "\t\t";
  protected final String TEXT_942 = "__ESETTING_DELEGATE.dynamicUnset(this, null, 0);";
  protected final String TEXT_943 = NL + "\t\t";
  protected final String TEXT_944 = " ";
  protected final String TEXT_945 = " = (";
  protected final String TEXT_946 = ")eVirtualGet(";
  protected final String TEXT_947 = ");";
  protected final String TEXT_948 = NL + "\t\tif (";
  protected final String TEXT_949 = " != null) ((";
  protected final String TEXT_950 = ".Unsettable";
  protected final String TEXT_951 = ")";
  protected final String TEXT_952 = ").unset();";
  protected final String TEXT_953 = NL + "\t\t";
  protected final String TEXT_954 = " ";
  protected final String TEXT_955 = " = (";
  protected final String TEXT_956 = ")eVirtualGet(";
  protected final String TEXT_957 = ");";
  protected final String TEXT_958 = NL + "\t\tif (";
  protected final String TEXT_959 = " != null)" + NL + "\t\t{" + NL + "\t\t\t";
  protected final String TEXT_960 = " msgs = null;";
  protected final String TEXT_961 = NL + "\t\t\tmsgs = ((";
  protected final String TEXT_962 = ")";
  protected final String TEXT_963 = ").eInverseRemove(this, EOPPOSITE_FEATURE_BASE - ";
  protected final String TEXT_964 = ", null, msgs);";
  protected final String TEXT_965 = NL + "\t\t\tmsgs = ((";
  protected final String TEXT_966 = ")";
  protected final String TEXT_967 = ").eInverseRemove(this, ";
  protected final String TEXT_968 = ", ";
  protected final String TEXT_969 = ".class, msgs);";
  protected final String TEXT_970 = NL + "\t\t\tmsgs = basicUnset";
  protected final String TEXT_971 = "(msgs);" + NL + "\t\t\tif (msgs != null) msgs.dispatch();" + NL + "\t\t}" + NL + "\t\telse" + NL + "\t\t{";
  protected final String TEXT_972 = NL + "\t\t\tboolean old";
  protected final String TEXT_973 = "ESet = eVirtualIsSet(";
  protected final String TEXT_974 = ");";
  protected final String TEXT_975 = NL + "\t\t\tboolean old";
  protected final String TEXT_976 = "ESet = (";
  protected final String TEXT_977 = " & ";
  protected final String TEXT_978 = "_ESETFLAG) != 0;";
  protected final String TEXT_979 = NL + "\t\t\t";
  protected final String TEXT_980 = " &= ~";
  protected final String TEXT_981 = "_ESETFLAG;";
  protected final String TEXT_982 = NL + "\t\t\tboolean old";
  protected final String TEXT_983 = "ESet = ";
  protected final String TEXT_984 = "ESet;";
  protected final String TEXT_985 = NL + "\t\t\t";
  protected final String TEXT_986 = "ESet = false;";
  protected final String TEXT_987 = NL + "\t\t\tif (eNotificationRequired())" + NL + "\t\t\t\teNotify(new ";
  protected final String TEXT_988 = "(this, ";
  protected final String TEXT_989 = ".UNSET, ";
  protected final String TEXT_990 = ", null, null, old";
  protected final String TEXT_991 = "ESet));";
  protected final String TEXT_992 = NL + "\t\t}";
  protected final String TEXT_993 = NL + "\t\t";
  protected final String TEXT_994 = " old";
  protected final String TEXT_995 = " = (";
  protected final String TEXT_996 = " & ";
  protected final String TEXT_997 = "_EFLAG) != 0;";
  protected final String TEXT_998 = NL + "\t\t";
  protected final String TEXT_999 = " old";
  protected final String TEXT_1000 = " = ";
  protected final String TEXT_1001 = "_EFLAG_VALUES[(";
  protected final String TEXT_1002 = " & ";
  protected final String TEXT_1003 = "_EFLAG) >>> ";
  protected final String TEXT_1004 = "_EFLAG_OFFSET];";
  protected final String TEXT_1005 = NL + "\t\tObject old";
  protected final String TEXT_1006 = " = eVirtualUnset(";
  protected final String TEXT_1007 = ");";
  protected final String TEXT_1008 = NL + "\t\t";
  protected final String TEXT_1009 = " old";
  protected final String TEXT_1010 = " = ";
  protected final String TEXT_1011 = ";";
  protected final String TEXT_1012 = NL + "\t\tboolean isSetChange = old";
  protected final String TEXT_1013 = " != EVIRTUAL_NO_VALUE;";
  protected final String TEXT_1014 = NL + "\t\tboolean old";
  protected final String TEXT_1015 = "ESet = (";
  protected final String TEXT_1016 = " & ";
  protected final String TEXT_1017 = "_ESETFLAG) != 0;";
  protected final String TEXT_1018 = NL + "\t\tboolean old";
  protected final String TEXT_1019 = "ESet = ";
  protected final String TEXT_1020 = "ESet;";
  protected final String TEXT_1021 = NL + "\t\t";
  protected final String TEXT_1022 = " = null;";
  protected final String TEXT_1023 = NL + "\t\t";
  protected final String TEXT_1024 = " &= ~";
  protected final String TEXT_1025 = "_ESETFLAG;";
  protected final String TEXT_1026 = NL + "\t\t";
  protected final String TEXT_1027 = "ESet = false;";
  protected final String TEXT_1028 = NL + "\t\tif (eNotificationRequired())" + NL + "\t\t\teNotify(new ";
  protected final String TEXT_1029 = "(this, ";
  protected final String TEXT_1030 = ".UNSET, ";
  protected final String TEXT_1031 = ", ";
  protected final String TEXT_1032 = "isSetChange ? old";
  protected final String TEXT_1033 = " : null";
  protected final String TEXT_1034 = "old";
  protected final String TEXT_1035 = ", null, ";
  protected final String TEXT_1036 = "isSetChange";
  protected final String TEXT_1037 = "old";
  protected final String TEXT_1038 = "ESet";
  protected final String TEXT_1039 = "));";
  protected final String TEXT_1040 = NL + "\t\tif (";
  protected final String TEXT_1041 = ") ";
  protected final String TEXT_1042 = " |= ";
  protected final String TEXT_1043 = "_EFLAG; else ";
  protected final String TEXT_1044 = " &= ~";
  protected final String TEXT_1045 = "_EFLAG;";
  protected final String TEXT_1046 = NL + "\t\t";
  protected final String TEXT_1047 = " = ";
  protected final String TEXT_1048 = " & ~";
  protected final String TEXT_1049 = "_EFLAG | ";
  protected final String TEXT_1050 = "_EFLAG_DEFAULT;";
  protected final String TEXT_1051 = NL + "\t\t";
  protected final String TEXT_1052 = " = ";
  protected final String TEXT_1053 = ";";
  protected final String TEXT_1054 = NL + "\t\t";
  protected final String TEXT_1055 = " &= ~";
  protected final String TEXT_1056 = "_ESETFLAG;";
  protected final String TEXT_1057 = NL + "\t\t";
  protected final String TEXT_1058 = "ESet = false;";
  protected final String TEXT_1059 = NL + "\t\tif (eNotificationRequired())" + NL + "\t\t\teNotify(new ";
  protected final String TEXT_1060 = "(this, ";
  protected final String TEXT_1061 = ".UNSET, ";
  protected final String TEXT_1062 = ", ";
  protected final String TEXT_1063 = "isSetChange ? old";
  protected final String TEXT_1064 = " : ";
  protected final String TEXT_1065 = "old";
  protected final String TEXT_1066 = ", ";
  protected final String TEXT_1067 = ", ";
  protected final String TEXT_1068 = "isSetChange";
  protected final String TEXT_1069 = "old";
  protected final String TEXT_1070 = "ESet";
  protected final String TEXT_1071 = "));";
  protected final String TEXT_1072 = NL + "\t\t((";
  protected final String TEXT_1073 = ".Internal)((";
  protected final String TEXT_1074 = ".Internal.Wrapper)get";
  protected final String TEXT_1075 = "()).featureMap()).clear(";
  protected final String TEXT_1076 = ");";
  protected final String TEXT_1077 = NL + "\t\t((";
  protected final String TEXT_1078 = ".Internal)get";
  protected final String TEXT_1079 = "()).clear(";
  protected final String TEXT_1080 = ");";
  protected final String TEXT_1081 = NL + "\t\t";
  protected final String TEXT_1082 = NL + "\t\t// TODO: implement this method to unset the '";
  protected final String TEXT_1083 = "' ";
  protected final String TEXT_1084 = NL + "\t\t// Ensure that you remove @generated or mark it @generated NOT" + NL + "\t\tthrow new UnsupportedOperationException();";
  protected final String TEXT_1085 = NL + "\t}" + NL;
  protected final String TEXT_1086 = NL + "\t/**" + NL + "\t * Returns whether the value of the '{@link ";
  protected final String TEXT_1087 = "#";
  protected final String TEXT_1088 = " <em>";
  protected final String TEXT_1089 = "</em>}' ";
  protected final String TEXT_1090 = " is set.";
  protected final String TEXT_1091 = NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @return whether the value of the '<em>";
  protected final String TEXT_1092 = "</em>' ";
  protected final String TEXT_1093 = " is set.";
  protected final String TEXT_1094 = NL + "\t * @see #unset";
  protected final String TEXT_1095 = "()";
  protected final String TEXT_1096 = NL + "\t * @see #";
  protected final String TEXT_1097 = "()";
  protected final String TEXT_1098 = NL + "\t * @see #set";
  protected final String TEXT_1099 = "(";
  protected final String TEXT_1100 = ")";
  protected final String TEXT_1101 = NL + "\t * @generated" + NL + "\t */";
  protected final String TEXT_1102 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */";
  protected final String TEXT_1103 = NL + "\tboolean isSet";
  protected final String TEXT_1104 = "();" + NL;
  protected final String TEXT_1105 = NL + "\tpublic boolean isSet";
  protected final String TEXT_1106 = "_";
  protected final String TEXT_1107 = "()" + NL + "\t{";
  protected final String TEXT_1108 = NL + "\t\treturn eDynamicIsSet(";
  protected final String TEXT_1109 = ", ";
  protected final String TEXT_1110 = ");";
  protected final String TEXT_1111 = NL + "\t\treturn eIsSet(";
  protected final String TEXT_1112 = ");";
  protected final String TEXT_1113 = NL + "\t\treturn ";
  protected final String TEXT_1114 = "__ESETTING_DELEGATE.dynamicIsSet(this, null, 0);";
  protected final String TEXT_1115 = NL + "\t\t";
  protected final String TEXT_1116 = " ";
  protected final String TEXT_1117 = " = (";
  protected final String TEXT_1118 = ")eVirtualGet(";
  protected final String TEXT_1119 = ");";
  protected final String TEXT_1120 = NL + "\t\treturn ";
  protected final String TEXT_1121 = " != null && ((";
  protected final String TEXT_1122 = ".Unsettable";
  protected final String TEXT_1123 = ")";
  protected final String TEXT_1124 = ").isSet();";
  protected final String TEXT_1125 = NL + "\t\treturn eVirtualIsSet(";
  protected final String TEXT_1126 = ");";
  protected final String TEXT_1127 = NL + "\t\treturn (";
  protected final String TEXT_1128 = " & ";
  protected final String TEXT_1129 = "_ESETFLAG) != 0;";
  protected final String TEXT_1130 = NL + "\t\treturn ";
  protected final String TEXT_1131 = "ESet;";
  protected final String TEXT_1132 = NL + "\t\treturn !((";
  protected final String TEXT_1133 = ".Internal)((";
  protected final String TEXT_1134 = ".Internal.Wrapper)get";
  protected final String TEXT_1135 = "()).featureMap()).isEmpty(";
  protected final String TEXT_1136 = ");";
  protected final String TEXT_1137 = NL + "\t\treturn !((";
  protected final String TEXT_1138 = ".Internal)get";
  protected final String TEXT_1139 = "()).isEmpty(";
  protected final String TEXT_1140 = ");";
  protected final String TEXT_1141 = NL + "\t\t";
  protected final String TEXT_1142 = NL + "\t\t// TODO: implement this method to return whether the '";
  protected final String TEXT_1143 = "' ";
  protected final String TEXT_1144 = " is set" + NL + "\t\t// Ensure that you remove @generated or mark it @generated NOT" + NL + "\t\tthrow new UnsupportedOperationException();";
  protected final String TEXT_1145 = NL + "\t}" + NL;
  protected final String TEXT_1146 = NL + "\t/**" + NL + "\t * The cached validation expression for the '{@link #";
  protected final String TEXT_1147 = "(";
  protected final String TEXT_1148 = ") <em>";
  protected final String TEXT_1149 = "</em>}' invariant operation." + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @see #";
  protected final String TEXT_1150 = "(";
  protected final String TEXT_1151 = ")" + NL + "\t * @generated" + NL + "\t * @ordered" + NL + "\t */" + NL + "\tprotected static final ";
  protected final String TEXT_1152 = " ";
  protected final String TEXT_1153 = "__EEXPRESSION = \"";
  protected final String TEXT_1154 = "\";";
  protected final String TEXT_1155 = NL;
  protected final String TEXT_1156 = NL + "\t/**" + NL + "\t * The cached invocation delegate for the '{@link #";
  protected final String TEXT_1157 = "(";
  protected final String TEXT_1158 = ") <em>";
  protected final String TEXT_1159 = "</em>}' operation." + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @see #";
  protected final String TEXT_1160 = "(";
  protected final String TEXT_1161 = ")" + NL + "\t * @generated" + NL + "\t * @ordered" + NL + "\t */" + NL + "\tprotected static final ";
  protected final String TEXT_1162 = ".Internal.InvocationDelegate ";
  protected final String TEXT_1163 = "__EINVOCATION_DELEGATE = ((";
  protected final String TEXT_1164 = ".Internal)";
  protected final String TEXT_1165 = ").getInvocationDelegate();" + NL;
  protected final String TEXT_1166 = NL + "\t/**";
  protected final String TEXT_1167 = NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->";
  protected final String TEXT_1168 = NL + "\t * <!-- begin-model-doc -->";
  protected final String TEXT_1169 = NL + "\t * ";
  protected final String TEXT_1170 = NL + "\t * @param ";
  protected final String TEXT_1171 = NL + "\t *   ";
  protected final String TEXT_1172 = NL + "\t * @param ";
  protected final String TEXT_1173 = " ";
  protected final String TEXT_1174 = NL + "\t * <!-- end-model-doc -->";
  protected final String TEXT_1175 = NL + "\t * @model ";
  protected final String TEXT_1176 = NL + "\t *        ";
  protected final String TEXT_1177 = NL + "\t * @model";
  protected final String TEXT_1178 = NL + "\t * @generated" + NL + "\t */";
  protected final String TEXT_1179 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */";
  protected final String TEXT_1180 = NL + "\t";
  protected final String TEXT_1181 = " ";
  protected final String TEXT_1182 = "(";
  protected final String TEXT_1183 = ")";
  protected final String TEXT_1184 = ";" + NL;
  protected final String TEXT_1185 = NL + "\tpublic ";
  protected final String TEXT_1186 = " ";
  protected final String TEXT_1187 = "(";
  protected final String TEXT_1188 = ")";
  protected final String TEXT_1189 = NL + "\t{";
  protected final String TEXT_1190 = NL + "\t\t";
  protected final String TEXT_1191 = NL + "\t\treturn" + NL + "\t\t\t";
  protected final String TEXT_1192 = ".validate" + NL + "\t\t\t\t(";
  protected final String TEXT_1193 = "," + NL + "\t\t\t\t this," + NL + "\t\t\t\t ";
  protected final String TEXT_1194 = "," + NL + "\t\t\t\t ";
  protected final String TEXT_1195 = "," + NL + "\t\t\t\t \"";
  protected final String TEXT_1196 = "\",";
  protected final String TEXT_1197 = NL + "\t\t\t\t ";
  protected final String TEXT_1198 = "," + NL + "\t\t\t\t ";
  protected final String TEXT_1199 = "__EEXPRESSION," + NL + "\t\t\t\t ";
  protected final String TEXT_1200 = ".ERROR," + NL + "\t\t\t\t ";
  protected final String TEXT_1201 = ".DIAGNOSTIC_SOURCE," + NL + "\t\t\t\t ";
  protected final String TEXT_1202 = ".";
  protected final String TEXT_1203 = ");";
  protected final String TEXT_1204 = NL + "\t\t// TODO: implement this method" + NL + "\t\t// -> specify the condition that violates the invariant" + NL + "\t\t// -> verify the details of the diagnostic, including severity and message" + NL + "\t\t// Ensure that you remove @generated or mark it @generated NOT" + NL + "\t\tif (false)" + NL + "\t\t{" + NL + "\t\t\tif (";
  protected final String TEXT_1205 = " != null)" + NL + "\t\t\t{" + NL + "\t\t\t\t";
  protected final String TEXT_1206 = ".add" + NL + "\t\t\t\t\t(new ";
  protected final String TEXT_1207 = NL + "\t\t\t\t\t\t(";
  protected final String TEXT_1208 = ".ERROR," + NL + "\t\t\t\t\t\t ";
  protected final String TEXT_1209 = ".DIAGNOSTIC_SOURCE," + NL + "\t\t\t\t\t\t ";
  protected final String TEXT_1210 = ".";
  protected final String TEXT_1211 = "," + NL + "\t\t\t\t\t\t ";
  protected final String TEXT_1212 = ".INSTANCE.getString(\"_UI_GenericInvariant_diagnostic\", new Object[] { \"";
  protected final String TEXT_1213 = "\", ";
  protected final String TEXT_1214 = ".getObjectLabel(this, ";
  protected final String TEXT_1215 = ") }),";
  protected final String TEXT_1216 = NL + "\t\t\t\t\t\t new Object [] { this }));" + NL + "\t\t\t}" + NL + "\t\t\treturn false;" + NL + "\t\t}" + NL + "\t\treturn true;";
  protected final String TEXT_1217 = NL + "\t\ttry" + NL + "\t\t{";
  protected final String TEXT_1218 = NL + "\t\t\t";
  protected final String TEXT_1219 = "__EINVOCATION_DELEGATE.dynamicInvoke(this, ";
  protected final String TEXT_1220 = "new ";
  protected final String TEXT_1221 = ".UnmodifiableEList<Object>(";
  protected final String TEXT_1222 = ", ";
  protected final String TEXT_1223 = ")";
  protected final String TEXT_1224 = "null";
  protected final String TEXT_1225 = ");";
  protected final String TEXT_1226 = NL + "\t\t\treturn ";
  protected final String TEXT_1227 = "(";
  protected final String TEXT_1228 = "(";
  protected final String TEXT_1229 = ")";
  protected final String TEXT_1230 = "__EINVOCATION_DELEGATE.dynamicInvoke(this, ";
  protected final String TEXT_1231 = "new ";
  protected final String TEXT_1232 = ".UnmodifiableEList<Object>(";
  protected final String TEXT_1233 = ", ";
  protected final String TEXT_1234 = ")";
  protected final String TEXT_1235 = "null";
  protected final String TEXT_1236 = ")";
  protected final String TEXT_1237 = ").";
  protected final String TEXT_1238 = "()";
  protected final String TEXT_1239 = ";";
  protected final String TEXT_1240 = NL + "\t\t}" + NL + "\t\tcatch (";
  protected final String TEXT_1241 = " ite)" + NL + "\t\t{" + NL + "\t\t\tthrow new ";
  protected final String TEXT_1242 = "(ite);" + NL + "\t\t}";
  protected final String TEXT_1243 = NL + "\t\t// TODO: implement this method" + NL + "\t\t// Ensure that you remove @generated or mark it @generated NOT" + NL + "\t\tthrow new UnsupportedOperationException();";
  protected final String TEXT_1244 = NL + "\t}" + NL;
  protected final String TEXT_1245 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */";
  protected final String TEXT_1246 = NL + "\t@SuppressWarnings(\"unchecked\")";
  protected final String TEXT_1247 = NL + "\t@Override";
  protected final String TEXT_1248 = NL + "\tpublic ";
  protected final String TEXT_1249 = " eInverseAdd(";
  protected final String TEXT_1250 = " otherEnd, int featureID, ";
  protected final String TEXT_1251 = " msgs)" + NL + "\t{" + NL + "\t\tswitch (featureID";
  protected final String TEXT_1252 = ")" + NL + "\t\t{";
  protected final String TEXT_1253 = NL + "\t\t\tcase ";
  protected final String TEXT_1254 = ":";
  protected final String TEXT_1255 = NL + "\t\t\t\treturn ((";
  protected final String TEXT_1256 = "(";
  protected final String TEXT_1257 = ".InternalMapView";
  protected final String TEXT_1258 = ")";
  protected final String TEXT_1259 = "()).eMap()).basicAdd(otherEnd, msgs);";
  protected final String TEXT_1260 = NL + "\t\t\t\treturn (";
  protected final String TEXT_1261 = "()).basicAdd(otherEnd, msgs);";
  protected final String TEXT_1262 = NL + "\t\t\t\tif (eInternalContainer() != null)" + NL + "\t\t\t\t\tmsgs = eBasicRemoveFromContainer(msgs);";
  protected final String TEXT_1263 = NL + "\t\t\t\treturn basicSet";
  protected final String TEXT_1264 = "((";
  protected final String TEXT_1265 = ")otherEnd, msgs);";
  protected final String TEXT_1266 = NL + "\t\t\t\treturn eBasicSetContainer(otherEnd, ";
  protected final String TEXT_1267 = ", msgs);";
  protected final String TEXT_1268 = NL + "\t\t\t\t";
  protected final String TEXT_1269 = " ";
  protected final String TEXT_1270 = " = (";
  protected final String TEXT_1271 = ")eVirtualGet(";
  protected final String TEXT_1272 = ");";
  protected final String TEXT_1273 = NL + "\t\t\t\t";
  protected final String TEXT_1274 = " ";
  protected final String TEXT_1275 = " = ";
  protected final String TEXT_1276 = "basicGet";
  protected final String TEXT_1277 = "();";
  protected final String TEXT_1278 = NL + "\t\t\t\tif (";
  protected final String TEXT_1279 = " != null)";
  protected final String TEXT_1280 = NL + "\t\t\t\t\tmsgs = ((";
  protected final String TEXT_1281 = ")";
  protected final String TEXT_1282 = ").eInverseRemove(this, EOPPOSITE_FEATURE_BASE - ";
  protected final String TEXT_1283 = ", null, msgs);";
  protected final String TEXT_1284 = NL + "\t\t\t\t\tmsgs = ((";
  protected final String TEXT_1285 = ")";
  protected final String TEXT_1286 = ").eInverseRemove(this, ";
  protected final String TEXT_1287 = ", ";
  protected final String TEXT_1288 = ".class, msgs);";
  protected final String TEXT_1289 = NL + "\t\t\t\treturn basicSet";
  protected final String TEXT_1290 = "((";
  protected final String TEXT_1291 = ")otherEnd, msgs);";
  protected final String TEXT_1292 = NL + "\t\t}";
  protected final String TEXT_1293 = NL + "\t\treturn super.eInverseAdd(otherEnd, featureID, msgs);";
  protected final String TEXT_1294 = NL + "\t\treturn eDynamicInverseAdd(otherEnd, featureID, msgs);";
  protected final String TEXT_1295 = NL + "\t}" + NL;
  protected final String TEXT_1296 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */";
  protected final String TEXT_1297 = NL + "\t@Override";
  protected final String TEXT_1298 = NL + "\tpublic ";
  protected final String TEXT_1299 = " eInverseRemove(";
  protected final String TEXT_1300 = " otherEnd, int featureID, ";
  protected final String TEXT_1301 = " msgs)" + NL + "\t{" + NL + "\t\tswitch (featureID";
  protected final String TEXT_1302 = ")" + NL + "\t\t{";
  protected final String TEXT_1303 = NL + "\t\t\tcase ";
  protected final String TEXT_1304 = ":";
  protected final String TEXT_1305 = NL + "\t\t\t\treturn ((";
  protected final String TEXT_1306 = ")((";
  protected final String TEXT_1307 = ".InternalMapView";
  protected final String TEXT_1308 = ")";
  protected final String TEXT_1309 = "()).eMap()).basicRemove(otherEnd, msgs);";
  protected final String TEXT_1310 = NL + "\t\t\t\treturn ((";
  protected final String TEXT_1311 = ")((";
  protected final String TEXT_1312 = ".Internal.Wrapper)";
  protected final String TEXT_1313 = "()).featureMap()).basicRemove(otherEnd, msgs);";
  protected final String TEXT_1314 = NL + "\t\t\t\treturn ((";
  protected final String TEXT_1315 = ")";
  protected final String TEXT_1316 = "()).basicRemove(otherEnd, msgs);";
  protected final String TEXT_1317 = NL + "\t\t\t\treturn eBasicSetContainer(null, ";
  protected final String TEXT_1318 = ", msgs);";
  protected final String TEXT_1319 = NL + "\t\t\t\treturn basicUnset";
  protected final String TEXT_1320 = "(msgs);";
  protected final String TEXT_1321 = NL + "\t\t\t\treturn basicSet";
  protected final String TEXT_1322 = "(null, msgs);";
  protected final String TEXT_1323 = NL + "\t\t}";
  protected final String TEXT_1324 = NL + "\t\treturn super.eInverseRemove(otherEnd, featureID, msgs);";
  protected final String TEXT_1325 = NL + "\t\treturn eDynamicInverseRemove(otherEnd, featureID, msgs);";
  protected final String TEXT_1326 = NL + "\t}" + NL;
  protected final String TEXT_1327 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */";
  protected final String TEXT_1328 = NL + "\t@Override";
  protected final String TEXT_1329 = NL + "\tpublic ";
  protected final String TEXT_1330 = " eBasicRemoveFromContainerFeature(";
  protected final String TEXT_1331 = " msgs)" + NL + "\t{" + NL + "\t\tswitch (eContainerFeatureID()";
  protected final String TEXT_1332 = ")" + NL + "\t\t{";
  protected final String TEXT_1333 = NL + "\t\t\tcase ";
  protected final String TEXT_1334 = ":" + NL + "\t\t\t\treturn eInternalContainer().eInverseRemove(this, ";
  protected final String TEXT_1335 = ", ";
  protected final String TEXT_1336 = ".class, msgs);";
  protected final String TEXT_1337 = NL + "\t\t}";
  protected final String TEXT_1338 = NL + "\t\treturn super.eBasicRemoveFromContainerFeature(msgs);";
  protected final String TEXT_1339 = NL + "\t\treturn eDynamicBasicRemoveFromContainer(msgs);";
  protected final String TEXT_1340 = NL + "\t}" + NL;
  protected final String TEXT_1341 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */";
  protected final String TEXT_1342 = NL + "\t@Override";
  protected final String TEXT_1343 = NL + "\tpublic Object eGet(int featureID, boolean resolve, boolean coreType)" + NL + "\t{" + NL + "\t\tswitch (featureID";
  protected final String TEXT_1344 = ")" + NL + "\t\t{";
  protected final String TEXT_1345 = NL + "\t\t\tcase ";
  protected final String TEXT_1346 = ":";
  protected final String TEXT_1347 = NL + "\t\t\t\treturn ";
  protected final String TEXT_1348 = "();";
  protected final String TEXT_1349 = NL + "\t\t\t\treturn ";
  protected final String TEXT_1350 = "() ? Boolean.TRUE : Boolean.FALSE;";
  protected final String TEXT_1351 = NL + "\t\t\t\treturn new ";
  protected final String TEXT_1352 = "(";
  protected final String TEXT_1353 = "());";
  protected final String TEXT_1354 = NL + "\t\t\t\tif (resolve) return ";
  protected final String TEXT_1355 = "();" + NL + "\t\t\t\treturn basicGet";
  protected final String TEXT_1356 = "();";
  protected final String TEXT_1357 = NL + "\t\t\t\tif (coreType) return ((";
  protected final String TEXT_1358 = ".InternalMapView";
  protected final String TEXT_1359 = ")";
  protected final String TEXT_1360 = "()).eMap();" + NL + "\t\t\t\telse return ";
  protected final String TEXT_1361 = "();";
  protected final String TEXT_1362 = NL + "\t\t\t\tif (coreType) return ";
  protected final String TEXT_1363 = "();" + NL + "\t\t\t\telse return ";
  protected final String TEXT_1364 = "().map();";
  protected final String TEXT_1365 = NL + "\t\t\t\tif (coreType) return ((";
  protected final String TEXT_1366 = ".Internal.Wrapper)";
  protected final String TEXT_1367 = "()).featureMap();" + NL + "\t\t\t\treturn ";
  protected final String TEXT_1368 = "();";
  protected final String TEXT_1369 = NL + "\t\t\t\tif (coreType) return ";
  protected final String TEXT_1370 = "();" + NL + "\t\t\t\treturn ((";
  protected final String TEXT_1371 = ".Internal)";
  protected final String TEXT_1372 = "()).getWrapper();";
  protected final String TEXT_1373 = NL + "\t\t\t\treturn ";
  protected final String TEXT_1374 = "();";
  protected final String TEXT_1375 = NL + "\t\t}";
  protected final String TEXT_1376 = NL + "\t\treturn super.eGet(featureID, resolve, coreType);";
  protected final String TEXT_1377 = NL + "\t\treturn eDynamicGet(featureID, resolve, coreType);";
  protected final String TEXT_1378 = NL + "\t}" + NL;
  protected final String TEXT_1379 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */";
  protected final String TEXT_1380 = NL + "\t@SuppressWarnings(\"unchecked\")";
  protected final String TEXT_1381 = NL + "\t@Override";
  protected final String TEXT_1382 = NL + "\tpublic void eSet(int featureID, Object newValue)" + NL + "\t{" + NL + "\t\tswitch (featureID";
  protected final String TEXT_1383 = ")" + NL + "\t\t{";
  protected final String TEXT_1384 = NL + "\t\t\tcase ";
  protected final String TEXT_1385 = ":";
  protected final String TEXT_1386 = NL + "\t\t\t\t((";
  protected final String TEXT_1387 = ".Internal)((";
  protected final String TEXT_1388 = ".Internal.Wrapper)";
  protected final String TEXT_1389 = "()).featureMap()).set(newValue);";
  protected final String TEXT_1390 = NL + "\t\t\t\t((";
  protected final String TEXT_1391 = ".Internal)";
  protected final String TEXT_1392 = "()).set(newValue);";
  protected final String TEXT_1393 = NL + "\t\t\t\t((";
  protected final String TEXT_1394 = ".Setting)((";
  protected final String TEXT_1395 = ".InternalMapView";
  protected final String TEXT_1396 = ")";
  protected final String TEXT_1397 = "()).eMap()).set(newValue);";
  protected final String TEXT_1398 = NL + "\t\t\t\t((";
  protected final String TEXT_1399 = ".Setting)";
  protected final String TEXT_1400 = "()).set(newValue);";
  protected final String TEXT_1401 = NL + "\t\t\t\t";
  protected final String TEXT_1402 = "().clear();" + NL + "\t\t\t\t";
  protected final String TEXT_1403 = "().addAll((";
  protected final String TEXT_1404 = "<? extends ";
  protected final String TEXT_1405 = ">";
  protected final String TEXT_1406 = ")newValue);";
  protected final String TEXT_1407 = NL + "\t\t\t\tset";
  protected final String TEXT_1408 = "(((";
  protected final String TEXT_1409 = ")newValue).";
  protected final String TEXT_1410 = "());";
  protected final String TEXT_1411 = NL + "\t\t\t\tset";
  protected final String TEXT_1412 = "(";
  protected final String TEXT_1413 = "(";
  protected final String TEXT_1414 = ")";
  protected final String TEXT_1415 = "newValue);";
  protected final String TEXT_1416 = NL + "\t\t\t\treturn;";
  protected final String TEXT_1417 = NL + "\t\t}";
  protected final String TEXT_1418 = NL + "\t\tsuper.eSet(featureID, newValue);";
  protected final String TEXT_1419 = NL + "\t\teDynamicSet(featureID, newValue);";
  protected final String TEXT_1420 = NL + "\t}" + NL;
  protected final String TEXT_1421 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */";
  protected final String TEXT_1422 = NL + "\t@Override";
  protected final String TEXT_1423 = NL + "\tpublic void eUnset(int featureID)" + NL + "\t{" + NL + "\t\tswitch (featureID";
  protected final String TEXT_1424 = ")" + NL + "\t\t{";
  protected final String TEXT_1425 = NL + "\t\t\tcase ";
  protected final String TEXT_1426 = ":";
  protected final String TEXT_1427 = NL + "\t\t\t\t((";
  protected final String TEXT_1428 = ".Internal.Wrapper)";
  protected final String TEXT_1429 = "()).featureMap().clear();";
  protected final String TEXT_1430 = NL + "\t\t\t\t";
  protected final String TEXT_1431 = "().clear();";
  protected final String TEXT_1432 = NL + "\t\t\t\tunset";
  protected final String TEXT_1433 = "();";
  protected final String TEXT_1434 = NL + "\t\t\t\tset";
  protected final String TEXT_1435 = "((";
  protected final String TEXT_1436 = ")null);";
  protected final String TEXT_1437 = NL + "\t\t\t\tset";
  protected final String TEXT_1438 = "(";
  protected final String TEXT_1439 = ");";
  protected final String TEXT_1440 = NL + "\t\t\t\treturn;";
  protected final String TEXT_1441 = NL + "\t\t}";
  protected final String TEXT_1442 = NL + "\t\tsuper.eUnset(featureID);";
  protected final String TEXT_1443 = NL + "\t\teDynamicUnset(featureID);";
  protected final String TEXT_1444 = NL + "\t}" + NL;
  protected final String TEXT_1445 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */";
  protected final String TEXT_1446 = NL + "\t@SuppressWarnings(\"unchecked\")";
  protected final String TEXT_1447 = NL + "\t@Override";
  protected final String TEXT_1448 = NL + "\tpublic boolean eIsSet(int featureID)" + NL + "\t{" + NL + "\t\tswitch (featureID";
  protected final String TEXT_1449 = ")" + NL + "\t\t{";
  protected final String TEXT_1450 = NL + "\t\t\tcase ";
  protected final String TEXT_1451 = ":";
  protected final String TEXT_1452 = NL + "\t\t\t\treturn isSet";
  protected final String TEXT_1453 = "();";
  protected final String TEXT_1454 = NL + "\t\t\t\treturn ";
  protected final String TEXT_1455 = "__ESETTING_DELEGATE.dynamicIsSet(this, null, 0);";
  protected final String TEXT_1456 = NL + "\t\t\t\treturn !((";
  protected final String TEXT_1457 = ".Internal.Wrapper)";
  protected final String TEXT_1458 = "()).featureMap().isEmpty();";
  protected final String TEXT_1459 = NL + "\t\t\t\treturn ";
  protected final String TEXT_1460 = " != null && !";
  protected final String TEXT_1461 = ".featureMap().isEmpty();";
  protected final String TEXT_1462 = NL + "\t\t\t\treturn ";
  protected final String TEXT_1463 = " != null && !";
  protected final String TEXT_1464 = ".isEmpty();";
  protected final String TEXT_1465 = NL + "\t\t\t\t";
  protected final String TEXT_1466 = " ";
  protected final String TEXT_1467 = " = (";
  protected final String TEXT_1468 = ")eVirtualGet(";
  protected final String TEXT_1469 = ");" + NL + "\t\t\t\treturn ";
  protected final String TEXT_1470 = " != null && !";
  protected final String TEXT_1471 = ".isEmpty();";
  protected final String TEXT_1472 = NL + "\t\t\t\treturn !";
  protected final String TEXT_1473 = "().isEmpty();";
  protected final String TEXT_1474 = NL + "\t\t\t\treturn isSet";
  protected final String TEXT_1475 = "();";
  protected final String TEXT_1476 = NL + "\t\t\t\treturn ";
  protected final String TEXT_1477 = " != null;";
  protected final String TEXT_1478 = NL + "\t\t\t\treturn eVirtualGet(";
  protected final String TEXT_1479 = ") != null;";
  protected final String TEXT_1480 = NL + "\t\t\t\treturn basicGet";
  protected final String TEXT_1481 = "() != null;";
  protected final String TEXT_1482 = NL + "\t\t\t\treturn ";
  protected final String TEXT_1483 = " != null;";
  protected final String TEXT_1484 = NL + "\t\t\t\treturn eVirtualGet(";
  protected final String TEXT_1485 = ") != null;";
  protected final String TEXT_1486 = NL + "\t\t\t\treturn ";
  protected final String TEXT_1487 = "() != null;";
  protected final String TEXT_1488 = NL + "\t\t\t\treturn ((";
  protected final String TEXT_1489 = " & ";
  protected final String TEXT_1490 = "_EFLAG) != 0) != ";
  protected final String TEXT_1491 = ";";
  protected final String TEXT_1492 = NL + "\t\t\t\treturn (";
  protected final String TEXT_1493 = " & ";
  protected final String TEXT_1494 = "_EFLAG) != ";
  protected final String TEXT_1495 = "_EFLAG_DEFAULT;";
  protected final String TEXT_1496 = NL + "\t\t\t\treturn ";
  protected final String TEXT_1497 = " != ";
  protected final String TEXT_1498 = ";";
  protected final String TEXT_1499 = NL + "\t\t\t\treturn eVirtualGet(";
  protected final String TEXT_1500 = ", ";
  protected final String TEXT_1501 = ") != ";
  protected final String TEXT_1502 = ";";
  protected final String TEXT_1503 = NL + "\t\t\t\treturn ";
  protected final String TEXT_1504 = "() != ";
  protected final String TEXT_1505 = ";";
  protected final String TEXT_1506 = NL + "\t\t\t\treturn ";
  protected final String TEXT_1507 = " == null ? ";
  protected final String TEXT_1508 = " != null : !";
  protected final String TEXT_1509 = ".equals(";
  protected final String TEXT_1510 = ");";
  protected final String TEXT_1511 = NL + "\t\t\t\t";
  protected final String TEXT_1512 = " ";
  protected final String TEXT_1513 = " = (";
  protected final String TEXT_1514 = ")eVirtualGet(";
  protected final String TEXT_1515 = ", ";
  protected final String TEXT_1516 = ");" + NL + "\t\t\t\treturn ";
  protected final String TEXT_1517 = " == null ? ";
  protected final String TEXT_1518 = " != null : !";
  protected final String TEXT_1519 = ".equals(";
  protected final String TEXT_1520 = ");";
  protected final String TEXT_1521 = NL + "\t\t\t\treturn ";
  protected final String TEXT_1522 = " == null ? ";
  protected final String TEXT_1523 = "() != null : !";
  protected final String TEXT_1524 = ".equals(";
  protected final String TEXT_1525 = "());";
  protected final String TEXT_1526 = NL + "\t\t}";
  protected final String TEXT_1527 = NL + "\t\treturn super.eIsSet(featureID);";
  protected final String TEXT_1528 = NL + "\t\treturn eDynamicIsSet(featureID);";
  protected final String TEXT_1529 = NL + "\t}" + NL;
  protected final String TEXT_1530 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */";
  protected final String TEXT_1531 = NL + "\t@Override";
  protected final String TEXT_1532 = NL + "\tpublic int eBaseStructuralFeatureID(int derivedFeatureID, Class";
  protected final String TEXT_1533 = " baseClass)" + NL + "\t{";
  protected final String TEXT_1534 = NL + "\t\tif (baseClass == ";
  protected final String TEXT_1535 = ".class)" + NL + "\t\t{" + NL + "\t\t\tswitch (derivedFeatureID";
  protected final String TEXT_1536 = ")" + NL + "\t\t\t{";
  protected final String TEXT_1537 = NL + "\t\t\t\tcase ";
  protected final String TEXT_1538 = ": return ";
  protected final String TEXT_1539 = ";";
  protected final String TEXT_1540 = NL + "\t\t\t\tdefault: return -1;" + NL + "\t\t\t}" + NL + "\t\t}";
  protected final String TEXT_1541 = NL + "\t\treturn super.eBaseStructuralFeatureID(derivedFeatureID, baseClass);" + NL + "\t}";
  protected final String TEXT_1542 = NL + NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */";
  protected final String TEXT_1543 = NL + "\t@Override";
  protected final String TEXT_1544 = NL + "\tpublic int eDerivedStructuralFeatureID(int baseFeatureID, Class";
  protected final String TEXT_1545 = " baseClass)" + NL + "\t{";
  protected final String TEXT_1546 = NL + "\t\tif (baseClass == ";
  protected final String TEXT_1547 = ".class)" + NL + "\t\t{" + NL + "\t\t\tswitch (baseFeatureID)" + NL + "\t\t\t{";
  protected final String TEXT_1548 = NL + "\t\t\t\tcase ";
  protected final String TEXT_1549 = ": return ";
  protected final String TEXT_1550 = ";";
  protected final String TEXT_1551 = NL + "\t\t\t\tdefault: return -1;" + NL + "\t\t\t}" + NL + "\t\t}";
  protected final String TEXT_1552 = NL + "\t\tif (baseClass == ";
  protected final String TEXT_1553 = ".class)" + NL + "\t\t{" + NL + "\t\t\tswitch (baseFeatureID";
  protected final String TEXT_1554 = ")" + NL + "\t\t\t{";
  protected final String TEXT_1555 = NL + "\t\t\t\tcase ";
  protected final String TEXT_1556 = ": return ";
  protected final String TEXT_1557 = ";";
  protected final String TEXT_1558 = NL + "\t\t\t\tdefault: return -1;" + NL + "\t\t\t}" + NL + "\t\t}";
  protected final String TEXT_1559 = NL + "\t\treturn super.eDerivedStructuralFeatureID(baseFeatureID, baseClass);" + NL + "\t}" + NL;
  protected final String TEXT_1560 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */";
  protected final String TEXT_1561 = NL + "\t@Override";
  protected final String TEXT_1562 = NL + "\tpublic int eDerivedOperationID(int baseOperationID, Class";
  protected final String TEXT_1563 = " baseClass)" + NL + "\t{";
  protected final String TEXT_1564 = NL + "\t\tif (baseClass == ";
  protected final String TEXT_1565 = ".class)" + NL + "\t\t{" + NL + "\t\t\tswitch (baseOperationID)" + NL + "\t\t\t{";
  protected final String TEXT_1566 = NL + "\t\t\t\tcase ";
  protected final String TEXT_1567 = ": return ";
  protected final String TEXT_1568 = ";";
  protected final String TEXT_1569 = NL + "\t\t\t\tdefault: return super.eDerivedOperationID(baseOperationID, baseClass);" + NL + "\t\t\t}" + NL + "\t\t}";
  protected final String TEXT_1570 = NL + "\t\tif (baseClass == ";
  protected final String TEXT_1571 = ".class)" + NL + "\t\t{" + NL + "\t\t\tswitch (baseOperationID)" + NL + "\t\t\t{";
  protected final String TEXT_1572 = NL + "\t\t\t\tcase ";
  protected final String TEXT_1573 = ": return ";
  protected final String TEXT_1574 = ";";
  protected final String TEXT_1575 = NL + "\t\t\t\tdefault: return -1;" + NL + "\t\t\t}" + NL + "\t\t}";
  protected final String TEXT_1576 = NL + "\t\tif (baseClass == ";
  protected final String TEXT_1577 = ".class)" + NL + "\t\t{" + NL + "\t\t\tswitch (baseOperationID";
  protected final String TEXT_1578 = ")" + NL + "\t\t\t{";
  protected final String TEXT_1579 = NL + "\t\t\t\tcase ";
  protected final String TEXT_1580 = ": return ";
  protected final String TEXT_1581 = ";";
  protected final String TEXT_1582 = NL + "\t\t\t\tdefault: return -1;" + NL + "\t\t\t}" + NL + "\t\t}";
  protected final String TEXT_1583 = NL + "\t\treturn super.eDerivedOperationID(baseOperationID, baseClass);" + NL + "\t}" + NL;
  protected final String TEXT_1584 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */";
  protected final String TEXT_1585 = NL + "\t@Override";
  protected final String TEXT_1586 = NL + "\tprotected Object[] eVirtualValues()" + NL + "\t{" + NL + "\t\treturn ";
  protected final String TEXT_1587 = ";" + NL + "\t}" + NL + "" + NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */";
  protected final String TEXT_1588 = NL + "\t@Override";
  protected final String TEXT_1589 = NL + "\tprotected void eSetVirtualValues(Object[] newValues)" + NL + "\t{" + NL + "\t\t";
  protected final String TEXT_1590 = " = newValues;" + NL + "\t}" + NL;
  protected final String TEXT_1591 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */";
  protected final String TEXT_1592 = NL + "\t@Override";
  protected final String TEXT_1593 = NL + "\tprotected int eVirtualIndexBits(int offset)" + NL + "\t{" + NL + "\t\tswitch (offset)" + NL + "\t\t{";
  protected final String TEXT_1594 = NL + "\t\t\tcase ";
  protected final String TEXT_1595 = " :" + NL + "\t\t\t\treturn ";
  protected final String TEXT_1596 = ";";
  protected final String TEXT_1597 = NL + "\t\t\tdefault :" + NL + "\t\t\t\tthrow new IndexOutOfBoundsException();" + NL + "\t\t}" + NL + "\t}" + NL + "" + NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */";
  protected final String TEXT_1598 = NL + "\t@Override";
  protected final String TEXT_1599 = NL + "\tprotected void eSetVirtualIndexBits(int offset, int newIndexBits)" + NL + "\t{" + NL + "\t\tswitch (offset)" + NL + "\t\t{";
  protected final String TEXT_1600 = NL + "\t\t\tcase ";
  protected final String TEXT_1601 = " :" + NL + "\t\t\t\t";
  protected final String TEXT_1602 = " = newIndexBits;" + NL + "\t\t\t\tbreak;";
  protected final String TEXT_1603 = NL + "\t\t\tdefault :" + NL + "\t\t\t\tthrow new IndexOutOfBoundsException();" + NL + "\t\t}" + NL + "\t}" + NL;
  protected final String TEXT_1604 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */";
  protected final String TEXT_1605 = NL + "\t@Override";
  protected final String TEXT_1606 = NL + "\t@SuppressWarnings(\"unchecked\")";
  protected final String TEXT_1607 = NL + "\tpublic Object eInvoke(int operationID, ";
  protected final String TEXT_1608 = " arguments) throws ";
  protected final String TEXT_1609 = NL + "\t{" + NL + "\t\tswitch (operationID";
  protected final String TEXT_1610 = ")" + NL + "\t\t{";
  protected final String TEXT_1611 = NL + "\t\t\tcase ";
  protected final String TEXT_1612 = ":";
  protected final String TEXT_1613 = NL + "\t\t\t\t";
  protected final String TEXT_1614 = "(";
  protected final String TEXT_1615 = "(";
  protected final String TEXT_1616 = "(";
  protected final String TEXT_1617 = ")";
  protected final String TEXT_1618 = "arguments.get(";
  protected final String TEXT_1619 = ")";
  protected final String TEXT_1620 = ").";
  protected final String TEXT_1621 = "()";
  protected final String TEXT_1622 = ", ";
  protected final String TEXT_1623 = ");" + NL + "\t\t\t\treturn null;";
  protected final String TEXT_1624 = NL + "\t\t\t\treturn ";
  protected final String TEXT_1625 = "new ";
  protected final String TEXT_1626 = "(";
  protected final String TEXT_1627 = "(";
  protected final String TEXT_1628 = "(";
  protected final String TEXT_1629 = "(";
  protected final String TEXT_1630 = ")";
  protected final String TEXT_1631 = "arguments.get(";
  protected final String TEXT_1632 = ")";
  protected final String TEXT_1633 = ").";
  protected final String TEXT_1634 = "()";
  protected final String TEXT_1635 = ", ";
  protected final String TEXT_1636 = ")";
  protected final String TEXT_1637 = ")";
  protected final String TEXT_1638 = ";";
  protected final String TEXT_1639 = NL + "\t\t}";
  protected final String TEXT_1640 = NL + "\t\treturn super.eInvoke(operationID, arguments);";
  protected final String TEXT_1641 = NL + "\t\treturn eDynamicInvoke(operationID, arguments);";
  protected final String TEXT_1642 = NL + "\t}" + NL;
  protected final String TEXT_1643 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */";
  protected final String TEXT_1644 = NL + "\t@Override";
  protected final String TEXT_1645 = NL + "\tpublic String toString()" + NL + "\t{" + NL + "\t\tif (eIsProxy()) return super.toString();" + NL + "" + NL + "\t\tStringBuffer result = new StringBuffer(super.toString());";
  protected final String TEXT_1646 = NL + "\t\tresult.append(\" (";
  protected final String TEXT_1647 = ": \");";
  protected final String TEXT_1648 = NL + "\t\tresult.append(\", ";
  protected final String TEXT_1649 = ": \");";
  protected final String TEXT_1650 = NL + "\t\tif (eVirtualIsSet(";
  protected final String TEXT_1651 = ")) result.append(eVirtualGet(";
  protected final String TEXT_1652 = ")); else result.append(\"<unset>\");";
  protected final String TEXT_1653 = NL + "\t\tif (";
  protected final String TEXT_1654 = "(";
  protected final String TEXT_1655 = " & ";
  protected final String TEXT_1656 = "_ESETFLAG) != 0";
  protected final String TEXT_1657 = "ESet";
  protected final String TEXT_1658 = ") result.append((";
  protected final String TEXT_1659 = " & ";
  protected final String TEXT_1660 = "_EFLAG) != 0); else result.append(\"<unset>\");";
  protected final String TEXT_1661 = NL + "\t\tif (";
  protected final String TEXT_1662 = "(";
  protected final String TEXT_1663 = " & ";
  protected final String TEXT_1664 = "_ESETFLAG) != 0";
  protected final String TEXT_1665 = "ESet";
  protected final String TEXT_1666 = ") result.append(";
  protected final String TEXT_1667 = "_EFLAG_VALUES[(";
  protected final String TEXT_1668 = " & ";
  protected final String TEXT_1669 = "_EFLAG) >>> ";
  protected final String TEXT_1670 = "_EFLAG_OFFSET]); else result.append(\"<unset>\");";
  protected final String TEXT_1671 = NL + "\t\tif (";
  protected final String TEXT_1672 = "(";
  protected final String TEXT_1673 = " & ";
  protected final String TEXT_1674 = "_ESETFLAG) != 0";
  protected final String TEXT_1675 = "ESet";
  protected final String TEXT_1676 = ") result.append(";
  protected final String TEXT_1677 = "); else result.append(\"<unset>\");";
  protected final String TEXT_1678 = NL + "\t\tresult.append(eVirtualGet(";
  protected final String TEXT_1679 = ", ";
  protected final String TEXT_1680 = "));";
  protected final String TEXT_1681 = NL + "\t\tresult.append((";
  protected final String TEXT_1682 = " & ";
  protected final String TEXT_1683 = "_EFLAG) != 0);";
  protected final String TEXT_1684 = NL + "\t\tresult.append(";
  protected final String TEXT_1685 = "_EFLAG_VALUES[(";
  protected final String TEXT_1686 = " & ";
  protected final String TEXT_1687 = "_EFLAG) >>> ";
  protected final String TEXT_1688 = "_EFLAG_OFFSET]);";
  protected final String TEXT_1689 = NL + "\t\tresult.append(";
  protected final String TEXT_1690 = ");";
  protected final String TEXT_1691 = NL + "\t\tresult.append(')');" + NL + "\t\treturn result.toString();" + NL + "\t}" + NL;
  protected final String TEXT_1692 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */" + NL + "\tprotected int hash = -1;" + NL + "" + NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */" + NL + "\tpublic int getHash()" + NL + "\t{" + NL + "\t\tif (hash == -1)" + NL + "\t\t{" + NL + "\t\t\t";
  protected final String TEXT_1693 = " theKey = getKey();" + NL + "\t\t\thash = (theKey == null ? 0 : theKey.hashCode());" + NL + "\t\t}" + NL + "\t\treturn hash;" + NL + "\t}" + NL + "" + NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */" + NL + "\tpublic void setHash(int hash)" + NL + "\t{" + NL + "\t\tthis.hash = hash;" + NL + "\t}" + NL + "" + NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */" + NL + "\tpublic ";
  protected final String TEXT_1694 = " getKey()" + NL + "\t{";
  protected final String TEXT_1695 = NL + "\t\treturn new ";
  protected final String TEXT_1696 = "(getTypedKey());";
  protected final String TEXT_1697 = NL + "\t\treturn getTypedKey();";
  protected final String TEXT_1698 = NL + "\t}" + NL + "" + NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */" + NL + "\tpublic void setKey(";
  protected final String TEXT_1699 = " key)" + NL + "\t{";
  protected final String TEXT_1700 = NL + "\t\tgetTypedKey().addAll(";
  protected final String TEXT_1701 = "(";
  protected final String TEXT_1702 = ")";
  protected final String TEXT_1703 = "key);";
  protected final String TEXT_1704 = NL + "\t\tsetTypedKey(key);";
  protected final String TEXT_1705 = NL + "\t\tsetTypedKey(((";
  protected final String TEXT_1706 = ")key).";
  protected final String TEXT_1707 = "());";
  protected final String TEXT_1708 = NL + "\t\tsetTypedKey((";
  protected final String TEXT_1709 = ")key);";
  protected final String TEXT_1710 = NL + "\t}" + NL + "" + NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */" + NL + "\tpublic ";
  protected final String TEXT_1711 = " getValue()" + NL + "\t{";
  protected final String TEXT_1712 = NL + "\t\treturn new ";
  protected final String TEXT_1713 = "(getTypedValue());";
  protected final String TEXT_1714 = NL + "\t\treturn getTypedValue();";
  protected final String TEXT_1715 = NL + "\t}" + NL + "" + NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */" + NL + "\tpublic ";
  protected final String TEXT_1716 = " setValue(";
  protected final String TEXT_1717 = " value)" + NL + "\t{" + NL + "\t\t";
  protected final String TEXT_1718 = " oldValue = getValue();";
  protected final String TEXT_1719 = NL + "\t\tgetTypedValue().clear();" + NL + "\t\tgetTypedValue().addAll(";
  protected final String TEXT_1720 = "(";
  protected final String TEXT_1721 = ")";
  protected final String TEXT_1722 = "value);";
  protected final String TEXT_1723 = NL + "\t\tsetTypedValue(value);";
  protected final String TEXT_1724 = NL + "\t\tsetTypedValue(((";
  protected final String TEXT_1725 = ")value).";
  protected final String TEXT_1726 = "());";
  protected final String TEXT_1727 = NL + "\t\tsetTypedValue((";
  protected final String TEXT_1728 = ")value);";
  protected final String TEXT_1729 = NL + "\t\treturn oldValue;" + NL + "\t}" + NL + "" + NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */";
  protected final String TEXT_1730 = NL + "\t@SuppressWarnings(\"unchecked\")";
  protected final String TEXT_1731 = NL + "\tpublic ";
  protected final String TEXT_1732 = " getEMap()" + NL + "\t{" + NL + "\t\t";
  protected final String TEXT_1733 = " container = eContainer();" + NL + "\t\treturn container == null ? null : (";
  protected final String TEXT_1734 = ")container.eGet(eContainmentFeature());" + NL + "\t}" + NL;
  protected final String TEXT_1735 = NL + "} //";
  protected final String TEXT_1736 = NL;

  public String generate(Object argument)
  {
    final StringBuffer stringBuffer = new StringBuffer();
    
/**
 * <copyright>
 *
 * Copyright (c) 2002-2009 IBM Corporation and others.
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
    final boolean isJDK50 = genModel.getComplianceLevel().getValue() >= GenJDKLevel.JDK50;
    final boolean isInterface = Boolean.TRUE.equals(((Object[])argument)[1]); final boolean isImplementation = Boolean.TRUE.equals(((Object[])argument)[2]);
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
    } else {
    stringBuffer.append(TEXT_4);
    stringBuffer.append("$");
    stringBuffer.append(TEXT_5);
    stringBuffer.append("$");
    }}
    stringBuffer.append(TEXT_6);
    if (isInterface) {
    stringBuffer.append(TEXT_7);
    stringBuffer.append(genPackage.getInterfacePackageName());
    stringBuffer.append(TEXT_8);
    } else {
    stringBuffer.append(TEXT_9);
    stringBuffer.append(genPackage.getClassPackageName());
    stringBuffer.append(TEXT_10);
    }
    stringBuffer.append(TEXT_11);
    genModel.markImportLocation(stringBuffer, genPackage);
    if (isImplementation) { genClass.addClassPsuedoImports(); }
    stringBuffer.append(TEXT_12);
    if (isInterface) {
    stringBuffer.append(TEXT_13);
    stringBuffer.append(genClass.getFormattedName());
    stringBuffer.append(TEXT_14);
    if (genClass.hasDocumentation()) {
    stringBuffer.append(TEXT_15);
    stringBuffer.append(genClass.getDocumentation(genModel.getIndentation(stringBuffer)));
    stringBuffer.append(TEXT_16);
    }
    stringBuffer.append(TEXT_17);
    if (!genClass.getGenFeatures().isEmpty()) {
    stringBuffer.append(TEXT_18);
    for (GenFeature genFeature : genClass.getGenFeatures()) {
    if (!genFeature.isSuppressedGetVisibility()) {
    stringBuffer.append(TEXT_19);
    stringBuffer.append(genClass.getQualifiedInterfaceName());
    stringBuffer.append(TEXT_20);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_21);
    stringBuffer.append(genFeature.getFormattedName());
    stringBuffer.append(TEXT_22);
    }
    }
    stringBuffer.append(TEXT_23);
    }
    stringBuffer.append(TEXT_24);
    if (!genModel.isSuppressEMFMetaData()) {
    stringBuffer.append(TEXT_25);
    stringBuffer.append(genPackage.getQualifiedPackageInterfaceName());
    stringBuffer.append(TEXT_26);
    stringBuffer.append(genClass.getClassifierAccessorName());
    stringBuffer.append(TEXT_27);
    }
    if (!genModel.isSuppressEMFModelTags()) { boolean first = true; for (StringTokenizer stringTokenizer = new StringTokenizer(genClass.getModelInfo(), "\n\r"); stringTokenizer.hasMoreTokens(); ) { String modelInfo = stringTokenizer.nextToken(); if (first) { first = false;
    stringBuffer.append(TEXT_28);
    stringBuffer.append(modelInfo);
    } else {
    stringBuffer.append(TEXT_29);
    stringBuffer.append(modelInfo);
    }} if (first) {
    stringBuffer.append(TEXT_30);
    }}
    if (genClass.needsRootExtendsInterfaceExtendsTag()) {
    stringBuffer.append(TEXT_31);
    stringBuffer.append(genModel.getImportedName(genModel.getRootExtendsInterface()));
    }
    stringBuffer.append(TEXT_32);
    } else {
    stringBuffer.append(TEXT_33);
    stringBuffer.append(genClass.getFormattedName());
    stringBuffer.append(TEXT_34);
    if (!genClass.getImplementedGenFeatures().isEmpty()) {
    stringBuffer.append(TEXT_35);
    for (GenFeature genFeature : genClass.getImplementedGenFeatures()) {
    stringBuffer.append(TEXT_36);
    stringBuffer.append(genClass.getQualifiedClassName());
    stringBuffer.append(TEXT_37);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_38);
    stringBuffer.append(genFeature.getFormattedName());
    stringBuffer.append(TEXT_39);
    }
    stringBuffer.append(TEXT_40);
    }
    stringBuffer.append(TEXT_41);
    }
    if (isImplementation) {
    stringBuffer.append(TEXT_42);
    if (genClass.isAbstract()) {
    stringBuffer.append(TEXT_43);
    }
    stringBuffer.append(TEXT_44);
    stringBuffer.append(genClass.getClassName());
    stringBuffer.append(genClass.getTypeParameters().trim());
    stringBuffer.append(genClass.getClassExtends());
    stringBuffer.append(genClass.getClassImplements());
    } else {
    stringBuffer.append(TEXT_45);
    stringBuffer.append(genClass.getInterfaceName());
    stringBuffer.append(genClass.getTypeParameters().trim());
    stringBuffer.append(genClass.getInterfaceExtends());
    }
    stringBuffer.append(TEXT_46);
    if (genModel.hasCopyrightField()) {
    stringBuffer.append(TEXT_47);
    stringBuffer.append(publicStaticFinalFlag);
    stringBuffer.append(genModel.getImportedName("java.lang.String"));
    stringBuffer.append(TEXT_48);
    stringBuffer.append(genModel.getCopyrightFieldLiteral());
    stringBuffer.append(TEXT_49);
    stringBuffer.append(genModel.getNonNLS());
    stringBuffer.append(TEXT_50);
    }
    if (isImplementation && genModel.getDriverNumber() != null) {
    stringBuffer.append(TEXT_51);
    stringBuffer.append(genModel.getImportedName("java.lang.String"));
    stringBuffer.append(TEXT_52);
    stringBuffer.append(genModel.getDriverNumber());
    stringBuffer.append(TEXT_53);
    stringBuffer.append(genModel.getNonNLS());
    stringBuffer.append(TEXT_54);
    }
    if (isImplementation && genClass.isJavaIOSerializable()) {
    stringBuffer.append(TEXT_55);
    }
    if (isImplementation && genModel.isVirtualDelegation()) { String eVirtualValuesField = genClass.getEVirtualValuesField();
    if (eVirtualValuesField != null) {
    stringBuffer.append(TEXT_56);
    stringBuffer.append(eVirtualValuesField);
    stringBuffer.append(TEXT_57);
    }
    { List<String> eVirtualIndexBitFields = genClass.getEVirtualIndexBitFields(new ArrayList<String>());
    if (!eVirtualIndexBitFields.isEmpty()) {
    for (String eVirtualIndexBitField : eVirtualIndexBitFields) {
    stringBuffer.append(TEXT_58);
    stringBuffer.append(eVirtualIndexBitField);
    stringBuffer.append(TEXT_59);
    }
    }
    }
    }
    if (isImplementation && genClass.isModelRoot() && genModel.isBooleanFlagsEnabled() && genModel.getBooleanFlagsReservedBits() == -1) {
    stringBuffer.append(TEXT_60);
    stringBuffer.append(genModel.getBooleanFlagsField());
    stringBuffer.append(TEXT_61);
    }
    if (isImplementation && !genModel.isReflectiveDelegation()) {
    for (GenFeature genFeature : genClass.getDeclaredFieldGenFeatures()) {
    if (genFeature.hasSettingDelegate()) {
    stringBuffer.append(TEXT_62);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_63);
    stringBuffer.append(genFeature.getFormattedName());
    stringBuffer.append(TEXT_64);
    stringBuffer.append(genFeature.getFeatureKind());
    stringBuffer.append(TEXT_65);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_66);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.EStructuralFeature"));
    stringBuffer.append(TEXT_67);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_68);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.EStructuralFeature"));
    stringBuffer.append(TEXT_69);
    stringBuffer.append(genFeature.getQualifiedFeatureAccessor());
    stringBuffer.append(TEXT_70);
    } else if (genFeature.isListType() || genFeature.isReferenceType()) {
    if (genClass.isField(genFeature)) {
    stringBuffer.append(TEXT_71);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_72);
    stringBuffer.append(genFeature.getFormattedName());
    stringBuffer.append(TEXT_73);
    stringBuffer.append(genFeature.getFeatureKind());
    stringBuffer.append(TEXT_74);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_75);
    stringBuffer.append(genFeature.getImportedInternalType(genClass));
    stringBuffer.append(TEXT_76);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_77);
    }
    if (genModel.isArrayAccessors() && genFeature.isListType() && !genFeature.isFeatureMapType() && !genFeature.isMapType()) { String rawListItemType = genFeature.getRawListItemType(); int index = rawListItemType.indexOf('['); String head = rawListItemType; String tail = ""; if (index != -1) { head = rawListItemType.substring(0, index); tail = rawListItemType.substring(index); } 
    stringBuffer.append(TEXT_78);
    stringBuffer.append(genFeature.getGetArrayAccessor());
    stringBuffer.append(TEXT_79);
    stringBuffer.append(genFeature.getFormattedName());
    stringBuffer.append(TEXT_80);
    stringBuffer.append(genFeature.getGetArrayAccessor());
    stringBuffer.append(TEXT_81);
    if (genFeature.getQualifiedListItemType(genClass).contains("<")) {
    stringBuffer.append(TEXT_82);
    }
    stringBuffer.append(TEXT_83);
    stringBuffer.append(rawListItemType);
    stringBuffer.append(TEXT_84);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_85);
    stringBuffer.append(head);
    stringBuffer.append(TEXT_86);
    stringBuffer.append(tail);
    stringBuffer.append(TEXT_87);
    }
    } else {
    if (genFeature.hasEDefault() && (!genFeature.isVolatile() || !genModel.isReflectiveDelegation() && (!genFeature.hasDelegateFeature() || !genFeature.isUnsettable()))) { String staticDefaultValue = genFeature.getStaticDefaultValue();
    stringBuffer.append(TEXT_88);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_89);
    stringBuffer.append(genFeature.getFormattedName());
    stringBuffer.append(TEXT_90);
    stringBuffer.append(genFeature.getFeatureKind());
    stringBuffer.append(TEXT_91);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_92);
    if (genModel.useGenerics() && genFeature.isListDataType() && genFeature.isSetDefaultValue()) {
    stringBuffer.append(TEXT_93);
    }
    stringBuffer.append(TEXT_94);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_95);
    stringBuffer.append(genFeature.getEDefault());
    if ("".equals(staticDefaultValue)) {
    stringBuffer.append(TEXT_96);
    stringBuffer.append(genFeature.getEcoreFeature().getDefaultValueLiteral());
    stringBuffer.append(TEXT_97);
    } else {
    stringBuffer.append(TEXT_98);
    stringBuffer.append(staticDefaultValue);
    stringBuffer.append(TEXT_99);
    stringBuffer.append(genModel.getNonNLS(staticDefaultValue));
    }
    stringBuffer.append(TEXT_100);
    }
    if (genClass.isField(genFeature)) {
    if (genClass.isFlag(genFeature)) { int flagIndex = genClass.getFlagIndex(genFeature);
    if (flagIndex > 31 && flagIndex % 32 == 0) {
    stringBuffer.append(TEXT_101);
    stringBuffer.append(genClass.getFlagsField(genFeature));
    stringBuffer.append(TEXT_102);
    }
    if (genFeature.isEnumType()) {
    stringBuffer.append(TEXT_103);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_104);
    stringBuffer.append(genFeature.getFormattedName());
    stringBuffer.append(TEXT_105);
    stringBuffer.append(genFeature.getFeatureKind());
    stringBuffer.append(TEXT_106);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_107);
    stringBuffer.append(flagIndex % 32);
    stringBuffer.append(TEXT_108);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_109);
    stringBuffer.append(genFeature.getFormattedName());
    stringBuffer.append(TEXT_110);
    stringBuffer.append(genFeature.getFeatureKind());
    stringBuffer.append(TEXT_111);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_112);
    if (isJDK50) {
    stringBuffer.append(genFeature.getEDefault());
    stringBuffer.append(TEXT_113);
    } else {
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_114);
    stringBuffer.append(genFeature.getEDefault());
    stringBuffer.append(TEXT_115);
    }
    stringBuffer.append(TEXT_116);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_117);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_118);
    stringBuffer.append(genFeature.getTypeGenClassifier().getFormattedName());
    stringBuffer.append(TEXT_119);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_120);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_121);
    if (isJDK50) {
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_122);
    } else {
    stringBuffer.append(TEXT_123);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_124);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_125);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_126);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_127);
    }
    stringBuffer.append(TEXT_128);
    }
    stringBuffer.append(TEXT_129);
    stringBuffer.append(genClass.getFlagSize(genFeature) > 1 ? "s" : "");
    stringBuffer.append(TEXT_130);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_131);
    stringBuffer.append(genFeature.getFormattedName());
    stringBuffer.append(TEXT_132);
    stringBuffer.append(genFeature.getFeatureKind());
    stringBuffer.append(TEXT_133);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_134);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_135);
    stringBuffer.append(genClass.getFlagMask(genFeature));
    stringBuffer.append(TEXT_136);
    if (genFeature.isEnumType()) {
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_137);
    } else {
    stringBuffer.append(flagIndex % 32);
    }
    stringBuffer.append(TEXT_138);
    } else {
    stringBuffer.append(TEXT_139);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_140);
    stringBuffer.append(genFeature.getFormattedName());
    stringBuffer.append(TEXT_141);
    stringBuffer.append(genFeature.getFeatureKind());
    stringBuffer.append(TEXT_142);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_143);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_144);
    stringBuffer.append(genFeature.getSafeName());
    if (genFeature.hasEDefault()) {
    stringBuffer.append(TEXT_145);
    stringBuffer.append(genFeature.getEDefault());
    }
    stringBuffer.append(TEXT_146);
    }
    }
    }
    if (genClass.isESetField(genFeature)) {
    if (genClass.isESetFlag(genFeature)) { int flagIndex = genClass.getESetFlagIndex(genFeature);
    if (flagIndex > 31 && flagIndex % 32 == 0) {
    stringBuffer.append(TEXT_147);
    stringBuffer.append(genClass.getESetFlagsField(genFeature));
    stringBuffer.append(TEXT_148);
    }
    stringBuffer.append(TEXT_149);
    stringBuffer.append(genFeature.getFormattedName());
    stringBuffer.append(TEXT_150);
    stringBuffer.append(genFeature.getFeatureKind());
    stringBuffer.append(TEXT_151);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_152);
    stringBuffer.append(flagIndex % 32 );
    stringBuffer.append(TEXT_153);
    } else {
    stringBuffer.append(TEXT_154);
    stringBuffer.append(genFeature.getFormattedName());
    stringBuffer.append(TEXT_155);
    stringBuffer.append(genFeature.getFeatureKind());
    stringBuffer.append(TEXT_156);
    stringBuffer.append(genFeature.getUncapName());
    stringBuffer.append(TEXT_157);
    }
    }
    //Class/declaredFieldGenFeature.override.javajetinc
    }
    }
    if (isImplementation && genClass.hasOffsetCorrection() && !genClass.getImplementedGenFeatures().isEmpty()) {
    stringBuffer.append(TEXT_158);
    stringBuffer.append(genClass.getOffsetCorrectionField(null));
    stringBuffer.append(TEXT_159);
    stringBuffer.append(genClass.getQualifiedClassifierAccessor());
    stringBuffer.append(TEXT_160);
    stringBuffer.append(genClass.getImplementedGenFeatures().get(0).getQualifiedFeatureAccessor());
    stringBuffer.append(TEXT_161);
    stringBuffer.append(genClass.getQualifiedFeatureID(genClass.getImplementedGenFeatures().get(0)));
    stringBuffer.append(TEXT_162);
    }
    if (isImplementation && !genModel.isReflectiveDelegation()) {
    for (GenFeature genFeature : genClass.getImplementedGenFeatures()) { GenFeature reverseFeature = genFeature.getReverse();
    if (reverseFeature != null && reverseFeature.getGenClass().hasOffsetCorrection()) {
    stringBuffer.append(TEXT_163);
    stringBuffer.append(genClass.getOffsetCorrectionField(genFeature));
    stringBuffer.append(TEXT_164);
    stringBuffer.append(reverseFeature.getGenClass().getQualifiedClassifierAccessor());
    stringBuffer.append(TEXT_165);
    stringBuffer.append(reverseFeature.getQualifiedFeatureAccessor());
    stringBuffer.append(TEXT_166);
    stringBuffer.append(reverseFeature.getGenClass().getQualifiedFeatureID(reverseFeature));
    stringBuffer.append(TEXT_167);
    }
    }
    }
    if (genModel.isOperationReflection() && isImplementation && genClass.hasOffsetCorrection() && !genClass.getImplementedGenOperations().isEmpty()) {
    stringBuffer.append(TEXT_168);
    stringBuffer.append(genClass.getQualifiedClassifierAccessor());
    stringBuffer.append(TEXT_169);
    stringBuffer.append(genClass.getImplementedGenOperations().get(0).getQualifiedOperationAccessor());
    stringBuffer.append(TEXT_170);
    stringBuffer.append(genClass.getQualifiedOperationID(genClass.getImplementedGenOperations().get(0)));
    stringBuffer.append(TEXT_171);
    }
    if (isImplementation) {
    stringBuffer.append(TEXT_172);
    if (genModel.isPublicConstructors()) {
    stringBuffer.append(TEXT_173);
    } else {
    stringBuffer.append(TEXT_174);
    }
    stringBuffer.append(TEXT_175);
    stringBuffer.append(genClass.getClassName());
    stringBuffer.append(TEXT_176);
    for (GenFeature genFeature : genClass.getFlagGenFeaturesWithDefault()) {
    stringBuffer.append(TEXT_177);
    stringBuffer.append(genClass.getFlagsField(genFeature));
    stringBuffer.append(TEXT_178);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_179);
    if (!genFeature.isBooleanType()) {
    stringBuffer.append(TEXT_180);
    }
    stringBuffer.append(TEXT_181);
    }
    stringBuffer.append(TEXT_182);
    if (genModel.useClassOverrideAnnotation()) {
    stringBuffer.append(TEXT_183);
    }
    stringBuffer.append(TEXT_184);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.EClass"));
    stringBuffer.append(TEXT_185);
    stringBuffer.append(genClass.getQualifiedClassifierAccessor());
    stringBuffer.append(TEXT_186);
    }
    if (isImplementation && (genModel.getFeatureDelegation() == GenDelegationKind.REFLECTIVE_LITERAL || genModel.isDynamicDelegation()) && (genClass.getClassExtendsGenClass() == null || (genClass.getClassExtendsGenClass().getGenModel().getFeatureDelegation() != GenDelegationKind.REFLECTIVE_LITERAL && !genClass.getClassExtendsGenClass().getGenModel().isDynamicDelegation()))) {
    stringBuffer.append(TEXT_187);
    if (genModel.useClassOverrideAnnotation()) {
    stringBuffer.append(TEXT_188);
    }
    stringBuffer.append(TEXT_189);
    stringBuffer.append(genClass.getClassExtendsGenClass() == null ? 0 : genClass.getClassExtendsGenClass().getAllGenFeatures().size());
    stringBuffer.append(TEXT_190);
    }
    //Class/reflectiveDelegation.override.javajetinc
    new Runnable() { public void run() {
    for (GenFeature genFeature : (isImplementation ? genClass.getImplementedGenFeatures() : genClass.getDeclaredGenFeatures())) {
    if (genModel.isArrayAccessors() && genFeature.isListType() && !genFeature.isFeatureMapType() && !genFeature.isMapType()) {
    stringBuffer.append(TEXT_191);
    if (!isImplementation) {
    stringBuffer.append(TEXT_192);
    stringBuffer.append(genFeature.getListItemType(genClass));
    stringBuffer.append(TEXT_193);
    stringBuffer.append(genFeature.getGetArrayAccessor());
    stringBuffer.append(TEXT_194);
    } else {
    stringBuffer.append(TEXT_195);
    stringBuffer.append(genFeature.getListItemType(genClass));
    stringBuffer.append(TEXT_196);
    stringBuffer.append(genFeature.getGetArrayAccessor());
    stringBuffer.append(TEXT_197);
    if (genFeature.isVolatile()) {
    stringBuffer.append(TEXT_198);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.util.BasicEList"));
    stringBuffer.append(genFeature.getListTemplateArguments(genClass));
    stringBuffer.append(TEXT_199);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.util.BasicEList"));
    stringBuffer.append(genFeature.getListTemplateArguments(genClass));
    stringBuffer.append(TEXT_200);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_201);
    if (genModel.useGenerics() && !genFeature.getListItemType(genClass).contains("<") && !genFeature.getListItemType(null).equals(genFeature.getListItemType(genClass))) {
    stringBuffer.append(TEXT_202);
    stringBuffer.append(genFeature.getListItemType(genClass));
    stringBuffer.append(TEXT_203);
    }
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_204);
    } else {
    stringBuffer.append(TEXT_205);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_206);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_207);
    if (genModel.useGenerics() && !genFeature.getListItemType(genClass).contains("<") && !genFeature.getListItemType(null).equals(genFeature.getListItemType(genClass))) {
    stringBuffer.append(TEXT_208);
    stringBuffer.append(genFeature.getListItemType(genClass));
    stringBuffer.append(TEXT_209);
    }
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_210);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.util.BasicEList"));
    stringBuffer.append(genFeature.getListTemplateArguments(genClass));
    stringBuffer.append(TEXT_211);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.util.BasicEList"));
    stringBuffer.append(genFeature.getListTemplateArguments(genClass));
    stringBuffer.append(TEXT_212);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_213);
    }
    stringBuffer.append(TEXT_214);
    stringBuffer.append(genFeature.getListItemType(genClass));
    stringBuffer.append(TEXT_215);
    }
    stringBuffer.append(TEXT_216);
    if (!isImplementation) {
    stringBuffer.append(TEXT_217);
    stringBuffer.append(genFeature.getListItemType(genClass));
    stringBuffer.append(TEXT_218);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_219);
    } else {
    stringBuffer.append(TEXT_220);
    stringBuffer.append(genFeature.getListItemType(genClass));
    stringBuffer.append(TEXT_221);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_222);
    if (!genModel.useGenerics()) {
    stringBuffer.append(TEXT_223);
    stringBuffer.append(genFeature.getListItemType(genClass));
    stringBuffer.append(TEXT_224);
    }
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_225);
    }
    stringBuffer.append(TEXT_226);
    if (!isImplementation) {
    stringBuffer.append(TEXT_227);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_228);
    } else {
    stringBuffer.append(TEXT_229);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_230);
    if (genFeature.isVolatile()) {
    stringBuffer.append(TEXT_231);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_232);
    } else {
    stringBuffer.append(TEXT_233);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_234);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_235);
    }
    stringBuffer.append(TEXT_236);
    }
    stringBuffer.append(TEXT_237);
    if (!isImplementation) {
    stringBuffer.append(TEXT_238);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_239);
    stringBuffer.append(genFeature.getListItemType(genClass));
    stringBuffer.append(TEXT_240);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_241);
    } else {
    stringBuffer.append(TEXT_242);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_243);
    stringBuffer.append(genFeature.getListItemType(genClass));
    stringBuffer.append(TEXT_244);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_245);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.util.BasicEList"));
    stringBuffer.append(genFeature.getListTemplateArguments(genClass));
    stringBuffer.append(TEXT_246);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_247);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_248);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_249);
    }
    stringBuffer.append(TEXT_250);
    if (!isImplementation) {
    stringBuffer.append(TEXT_251);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_252);
    stringBuffer.append(genFeature.getListItemType(genClass));
    stringBuffer.append(TEXT_253);
    } else {
    stringBuffer.append(TEXT_254);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_255);
    stringBuffer.append(genFeature.getListItemType(genClass));
    stringBuffer.append(TEXT_256);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_257);
    }
    }
    if (genFeature.isGet() && (isImplementation || !genFeature.isSuppressedGetVisibility())) {
    if (isInterface) {
    stringBuffer.append(TEXT_258);
    stringBuffer.append(genFeature.getFormattedName());
    stringBuffer.append(TEXT_259);
    stringBuffer.append(genFeature.getFeatureKind());
    stringBuffer.append(TEXT_260);
    if (genFeature.isListType()) {
    if (genFeature.isMapType()) { GenFeature keyFeature = genFeature.getMapEntryTypeGenClass().getMapEntryKeyFeature(); GenFeature valueFeature = genFeature.getMapEntryTypeGenClass().getMapEntryValueFeature(); 
    stringBuffer.append(TEXT_261);
    if (keyFeature.isListType()) {
    stringBuffer.append(TEXT_262);
    stringBuffer.append(keyFeature.getQualifiedListItemType(genClass));
    stringBuffer.append(TEXT_263);
    } else {
    stringBuffer.append(TEXT_264);
    stringBuffer.append(keyFeature.getType(genClass));
    stringBuffer.append(TEXT_265);
    }
    stringBuffer.append(TEXT_266);
    if (valueFeature.isListType()) {
    stringBuffer.append(TEXT_267);
    stringBuffer.append(valueFeature.getQualifiedListItemType(genClass));
    stringBuffer.append(TEXT_268);
    } else {
    stringBuffer.append(TEXT_269);
    stringBuffer.append(valueFeature.getType(genClass));
    stringBuffer.append(TEXT_270);
    }
    stringBuffer.append(TEXT_271);
    } else if (!genFeature.isWrappedFeatureMapType() && !(genModel.isSuppressEMFMetaData() && "org.eclipse.emf.ecore.EObject".equals(genFeature.getQualifiedListItemType(genClass)))) {
String typeName = genFeature.getQualifiedListItemType(genClass); String head = typeName; String tail = ""; int index = typeName.indexOf('<'); if (index == -1) { index = typeName.indexOf('['); } 
if (index != -1) { head = typeName.substring(0, index); tail = typeName.substring(index).replaceAll("<", "&lt;"); }

    stringBuffer.append(TEXT_272);
    stringBuffer.append(head);
    stringBuffer.append(TEXT_273);
    stringBuffer.append(tail);
    stringBuffer.append(TEXT_274);
    }
    } else if (genFeature.isSetDefaultValue()) {
    stringBuffer.append(TEXT_275);
    stringBuffer.append(genFeature.getDefaultValue());
    stringBuffer.append(TEXT_276);
    }
    if (genFeature.getTypeGenEnum() != null) {
    stringBuffer.append(TEXT_277);
    stringBuffer.append(genFeature.getTypeGenEnum().getQualifiedName());
    stringBuffer.append(TEXT_278);
    }
    if (genFeature.isBidirectional() && !genFeature.getReverse().getGenClass().isMapEntry()) { GenFeature reverseGenFeature = genFeature.getReverse(); 
    if (!reverseGenFeature.isSuppressedGetVisibility()) {
    stringBuffer.append(TEXT_279);
    stringBuffer.append(reverseGenFeature.getGenClass().getQualifiedInterfaceName());
    stringBuffer.append(TEXT_280);
    stringBuffer.append(reverseGenFeature.getGetAccessor());
    stringBuffer.append(TEXT_281);
    stringBuffer.append(reverseGenFeature.getFormattedName());
    stringBuffer.append(TEXT_282);
    }
    }
    stringBuffer.append(TEXT_283);
    if (!genFeature.hasDocumentation()) {
    stringBuffer.append(TEXT_284);
    stringBuffer.append(genFeature.getFormattedName());
    stringBuffer.append(TEXT_285);
    stringBuffer.append(genFeature.getFeatureKind());
    stringBuffer.append(TEXT_286);
    }
    stringBuffer.append(TEXT_287);
    if (genFeature.hasDocumentation()) {
    stringBuffer.append(TEXT_288);
    stringBuffer.append(genFeature.getDocumentation(genModel.getIndentation(stringBuffer)));
    stringBuffer.append(TEXT_289);
    }
    stringBuffer.append(TEXT_290);
    stringBuffer.append(genFeature.getFormattedName());
    stringBuffer.append(TEXT_291);
    stringBuffer.append(genFeature.getFeatureKind());
    stringBuffer.append(TEXT_292);
    if (genFeature.getTypeGenEnum() != null) {
    stringBuffer.append(TEXT_293);
    stringBuffer.append(genFeature.getTypeGenEnum().getQualifiedName());
    }
    if (genFeature.isUnsettable()) {
    if (!genFeature.isSuppressedIsSetVisibility()) {
    stringBuffer.append(TEXT_294);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_295);
    }
    if (genFeature.isChangeable() && !genFeature.isSuppressedUnsetVisibility()) {
    stringBuffer.append(TEXT_296);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_297);
    }
    }
    if (genFeature.isChangeable() && !genFeature.isListType() && !genFeature.isSuppressedSetVisibility()) {
    stringBuffer.append(TEXT_298);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_299);
    stringBuffer.append(genFeature.getRawImportedBoundType());
    stringBuffer.append(TEXT_300);
    }
    if (!genModel.isSuppressEMFMetaData()) {
    stringBuffer.append(TEXT_301);
    stringBuffer.append(genPackage.getQualifiedPackageInterfaceName());
    stringBuffer.append(TEXT_302);
    stringBuffer.append(genFeature.getFeatureAccessorName());
    stringBuffer.append(TEXT_303);
    }
    if (genFeature.isBidirectional() && !genFeature.getReverse().getGenClass().isMapEntry()) { GenFeature reverseGenFeature = genFeature.getReverse(); 
    if (!reverseGenFeature.isSuppressedGetVisibility()) {
    stringBuffer.append(TEXT_304);
    stringBuffer.append(reverseGenFeature.getGenClass().getQualifiedInterfaceName());
    stringBuffer.append(TEXT_305);
    stringBuffer.append(reverseGenFeature.getGetAccessor());
    }
    }
    if (!genModel.isSuppressEMFModelTags()) { boolean first = true; for (StringTokenizer stringTokenizer = new StringTokenizer(genFeature.getModelInfo(), "\n\r"); stringTokenizer.hasMoreTokens(); ) { String modelInfo = stringTokenizer.nextToken(); if (first) { first = false;
    stringBuffer.append(TEXT_306);
    stringBuffer.append(modelInfo);
    } else {
    stringBuffer.append(TEXT_307);
    stringBuffer.append(modelInfo);
    }} if (first) {
    stringBuffer.append(TEXT_308);
    }}
    stringBuffer.append(TEXT_309);
    //Class/getGenFeature.javadoc.override.javajetinc
    } else {
    stringBuffer.append(TEXT_310);
    if (isJDK50) { //Class/getGenFeature.annotations.insert.javajetinc
    }
    }
    if (!isImplementation) {
    stringBuffer.append(TEXT_311);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_312);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_313);
    } else {
    if (genModel.useGenerics() && ((genFeature.isContainer() || genFeature.isResolveProxies()) && !genFeature.isListType() && !(genModel.isReflectiveDelegation() && genModel.isDynamicDelegation()) && genFeature.isUncheckedCast(genClass) || genFeature.isListType() && !genFeature.isFeatureMapType() && (genModel.isReflectiveDelegation() || genModel.isVirtualDelegation() || genModel.isDynamicDelegation()) || genFeature.isListDataType() && genFeature.hasDelegateFeature() || genFeature.isListType() && genFeature.hasSettingDelegate())) {
    stringBuffer.append(TEXT_314);
    }
    stringBuffer.append(TEXT_315);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_316);
    stringBuffer.append(genFeature.getGetAccessor());
    if (genClass.hasCollidingGetAccessorOperation(genFeature)) {
    stringBuffer.append(TEXT_317);
    }
    stringBuffer.append(TEXT_318);
    if (genModel.isDynamicDelegation()) {
    stringBuffer.append(TEXT_319);
    if (!isJDK50 && genFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_320);
    }
    stringBuffer.append(TEXT_321);
    stringBuffer.append(genFeature.getObjectType(genClass));
    stringBuffer.append(TEXT_322);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(TEXT_323);
    stringBuffer.append(genFeature.getQualifiedFeatureAccessor());
    stringBuffer.append(TEXT_324);
    stringBuffer.append(!genFeature.isEffectiveSuppressEMFTypes());
    stringBuffer.append(TEXT_325);
    if (!isJDK50 && genFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_326);
    stringBuffer.append(genFeature.getPrimitiveValueFunction());
    stringBuffer.append(TEXT_327);
    }
    stringBuffer.append(TEXT_328);
    } else if (genModel.isReflectiveDelegation()) {
    stringBuffer.append(TEXT_329);
    if (!isJDK50 && genFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_330);
    }
    stringBuffer.append(TEXT_331);
    stringBuffer.append(genFeature.getObjectType(genClass));
    stringBuffer.append(TEXT_332);
    stringBuffer.append(genFeature.getQualifiedFeatureAccessor());
    stringBuffer.append(TEXT_333);
    if (!isJDK50 && genFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_334);
    stringBuffer.append(genFeature.getPrimitiveValueFunction());
    stringBuffer.append(TEXT_335);
    }
    stringBuffer.append(TEXT_336);
    } else if (genFeature.hasSettingDelegate()) {
    stringBuffer.append(TEXT_337);
    if (!isJDK50 && genFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_338);
    }
    stringBuffer.append(TEXT_339);
    stringBuffer.append(genFeature.getObjectType(genClass));
    stringBuffer.append(TEXT_340);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_341);
    if (!isJDK50 && genFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_342);
    stringBuffer.append(genFeature.getPrimitiveValueFunction());
    stringBuffer.append(TEXT_343);
    }
    stringBuffer.append(TEXT_344);
    } else if (!genFeature.isVolatile()) {
    if (genFeature.isListType()) {
    if (genModel.isVirtualDelegation()) {
    stringBuffer.append(TEXT_345);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_346);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_347);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_348);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(positiveOffsetCorrection);
    stringBuffer.append(TEXT_349);
    }
    stringBuffer.append(TEXT_350);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_351);
    if (genModel.isVirtualDelegation()) {
    stringBuffer.append(TEXT_352);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(positiveOffsetCorrection);
    stringBuffer.append(TEXT_353);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_354);
    stringBuffer.append(genClass.getListConstructor(genFeature));
    stringBuffer.append(TEXT_355);
    } else {
    stringBuffer.append(TEXT_356);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_357);
    stringBuffer.append(genClass.getListConstructor(genFeature));
    stringBuffer.append(TEXT_358);
    }
    stringBuffer.append(TEXT_359);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(genFeature.isMapType() && genFeature.isEffectiveSuppressEMFTypes() ? ".map()" : "");
    stringBuffer.append(TEXT_360);
    } else if (genFeature.isContainer()) {
    stringBuffer.append(TEXT_361);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(positiveOffsetCorrection);
    stringBuffer.append(TEXT_362);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_363);
    } else {
    if (genFeature.isResolveProxies()) {
    if (genModel.isVirtualDelegation()) {
    stringBuffer.append(TEXT_364);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_365);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_366);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_367);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(positiveOffsetCorrection);
    if (genFeature.hasEDefault()) {
    stringBuffer.append(TEXT_368);
    stringBuffer.append(genFeature.getEDefault());
    }
    stringBuffer.append(TEXT_369);
    }
    stringBuffer.append(TEXT_370);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_371);
    stringBuffer.append(genFeature.getSafeNameAsEObject());
    stringBuffer.append(TEXT_372);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.InternalEObject"));
    stringBuffer.append(TEXT_373);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_374);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.InternalEObject"));
    stringBuffer.append(TEXT_375);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_376);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_377);
    stringBuffer.append(genFeature.getNonEObjectInternalTypeCast(genClass));
    stringBuffer.append(TEXT_378);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_379);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_380);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_381);
    if (genFeature.isEffectiveContains()) {
    stringBuffer.append(TEXT_382);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.InternalEObject"));
    stringBuffer.append(TEXT_383);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_384);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.InternalEObject"));
    stringBuffer.append(TEXT_385);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_386);
    if (!genFeature.isBidirectional()) {
    stringBuffer.append(TEXT_387);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.notify.NotificationChain"));
    stringBuffer.append(TEXT_388);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_389);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(negativeOffsetCorrection);
    stringBuffer.append(TEXT_390);
    } else { GenFeature reverseFeature = genFeature.getReverse(); GenClass targetClass = reverseFeature.getGenClass(); String reverseOffsetCorrection = targetClass.hasOffsetCorrection() ? " + " + genClass.getOffsetCorrectionField(genFeature) : "";
    stringBuffer.append(TEXT_391);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.notify.NotificationChain"));
    stringBuffer.append(TEXT_392);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_393);
    stringBuffer.append(targetClass.getQualifiedFeatureID(reverseFeature));
    stringBuffer.append(reverseOffsetCorrection);
    stringBuffer.append(TEXT_394);
    stringBuffer.append(targetClass.getRawImportedInterfaceName());
    stringBuffer.append(TEXT_395);
    }
    stringBuffer.append(TEXT_396);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_397);
    if (!genFeature.isBidirectional()) {
    stringBuffer.append(TEXT_398);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_399);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(negativeOffsetCorrection);
    stringBuffer.append(TEXT_400);
    } else { GenFeature reverseFeature = genFeature.getReverse(); GenClass targetClass = reverseFeature.getGenClass(); String reverseOffsetCorrection = targetClass.hasOffsetCorrection() ? " + " + genClass.getOffsetCorrectionField(genFeature) : "";
    stringBuffer.append(TEXT_401);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_402);
    stringBuffer.append(targetClass.getQualifiedFeatureID(reverseFeature));
    stringBuffer.append(reverseOffsetCorrection);
    stringBuffer.append(TEXT_403);
    stringBuffer.append(targetClass.getRawImportedInterfaceName());
    stringBuffer.append(TEXT_404);
    }
    stringBuffer.append(TEXT_405);
    } else if (genModel.isVirtualDelegation()) {
    stringBuffer.append(TEXT_406);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(positiveOffsetCorrection);
    stringBuffer.append(TEXT_407);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_408);
    }
    if (!genModel.isSuppressNotification()) {
    stringBuffer.append(TEXT_409);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.impl.ENotificationImpl"));
    stringBuffer.append(TEXT_410);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.notify.Notification"));
    stringBuffer.append(TEXT_411);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(positiveOffsetCorrection);
    stringBuffer.append(TEXT_412);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_413);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_414);
    }
    stringBuffer.append(TEXT_415);
    }
    if (!genFeature.isResolveProxies() && genModel.isVirtualDelegation() && !genFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_416);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_417);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(positiveOffsetCorrection);
    if (genFeature.hasEDefault()) {
    stringBuffer.append(TEXT_418);
    stringBuffer.append(genFeature.getEDefault());
    }
    stringBuffer.append(TEXT_419);
    } else if (genClass.isFlag(genFeature)) {
    if (genFeature.isBooleanType()) {
    stringBuffer.append(TEXT_420);
    stringBuffer.append(genClass.getFlagsField(genFeature));
    stringBuffer.append(TEXT_421);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_422);
    } else {
    stringBuffer.append(TEXT_423);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_424);
    stringBuffer.append(genClass.getFlagsField(genFeature));
    stringBuffer.append(TEXT_425);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_426);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_427);
    }
    } else {
    stringBuffer.append(TEXT_428);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_429);
    }
    }
    } else {//volatile
    if (genFeature.isResolveProxies() && !genFeature.isListType()) {
    stringBuffer.append(TEXT_430);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_431);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_432);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_433);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_434);
    stringBuffer.append(genFeature.getSafeNameAsEObject());
    stringBuffer.append(TEXT_435);
    stringBuffer.append(genFeature.getNonEObjectInternalTypeCast(genClass));
    stringBuffer.append(TEXT_436);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.InternalEObject"));
    stringBuffer.append(TEXT_437);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_438);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_439);
    } else if (genFeature.hasDelegateFeature()) { GenFeature delegateFeature = genFeature.getDelegateFeature();
    if (genFeature.isFeatureMapType()) {
    String featureMapEntryTemplateArgument = isJDK50 ? "<" + genModel.getImportedName("org.eclipse.emf.ecore.util.FeatureMap") + ".Entry>" : "";
    if (delegateFeature.isWrappedFeatureMapType()) {
    stringBuffer.append(TEXT_440);
    stringBuffer.append(genFeature.getImportedEffectiveFeatureMapWrapperClass());
    stringBuffer.append(TEXT_441);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.FeatureMap"));
    stringBuffer.append(TEXT_442);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.FeatureMap"));
    stringBuffer.append(TEXT_443);
    stringBuffer.append(delegateFeature.getAccessorName());
    stringBuffer.append(TEXT_444);
    stringBuffer.append(featureMapEntryTemplateArgument);
    stringBuffer.append(TEXT_445);
    stringBuffer.append(genFeature.getQualifiedFeatureAccessor());
    stringBuffer.append(TEXT_446);
    } else {
    stringBuffer.append(TEXT_447);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.FeatureMap"));
    stringBuffer.append(TEXT_448);
    stringBuffer.append(delegateFeature.getAccessorName());
    stringBuffer.append(TEXT_449);
    stringBuffer.append(featureMapEntryTemplateArgument);
    stringBuffer.append(TEXT_450);
    stringBuffer.append(genFeature.getQualifiedFeatureAccessor());
    stringBuffer.append(TEXT_451);
    }
    } else if (genFeature.isListType()) {
    if (delegateFeature.isWrappedFeatureMapType()) {
    stringBuffer.append(TEXT_452);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.FeatureMap"));
    stringBuffer.append(TEXT_453);
    stringBuffer.append(delegateFeature.getAccessorName());
    stringBuffer.append(TEXT_454);
    stringBuffer.append(genFeature.getQualifiedFeatureAccessor());
    stringBuffer.append(TEXT_455);
    } else {
    stringBuffer.append(TEXT_456);
    stringBuffer.append(delegateFeature.getAccessorName());
    stringBuffer.append(TEXT_457);
    stringBuffer.append(genFeature.getQualifiedFeatureAccessor());
    stringBuffer.append(TEXT_458);
    }
    } else {
    if (delegateFeature.isWrappedFeatureMapType()) {
    stringBuffer.append(TEXT_459);
    if (!isJDK50 && genFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_460);
    }
    if (genFeature.getTypeGenDataType() == null || !genFeature.getTypeGenDataType().isObjectType()) {
    stringBuffer.append(TEXT_461);
    stringBuffer.append(genFeature.getObjectType(genClass));
    stringBuffer.append(TEXT_462);
    }
    stringBuffer.append(TEXT_463);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.FeatureMap"));
    stringBuffer.append(TEXT_464);
    stringBuffer.append(delegateFeature.getAccessorName());
    stringBuffer.append(TEXT_465);
    stringBuffer.append(genFeature.getQualifiedFeatureAccessor());
    stringBuffer.append(TEXT_466);
    if (!isJDK50 && genFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_467);
    stringBuffer.append(genFeature.getPrimitiveValueFunction());
    stringBuffer.append(TEXT_468);
    }
    stringBuffer.append(TEXT_469);
    } else {
    stringBuffer.append(TEXT_470);
    if (!isJDK50 && genFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_471);
    }
    if (genFeature.getTypeGenDataType() == null || !genFeature.getTypeGenDataType().isObjectType()) {
    stringBuffer.append(TEXT_472);
    stringBuffer.append(genFeature.getObjectType(genClass));
    stringBuffer.append(TEXT_473);
    }
    stringBuffer.append(TEXT_474);
    stringBuffer.append(delegateFeature.getAccessorName());
    stringBuffer.append(TEXT_475);
    stringBuffer.append(genFeature.getQualifiedFeatureAccessor());
    stringBuffer.append(TEXT_476);
    if (!isJDK50 && genFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_477);
    stringBuffer.append(genFeature.getPrimitiveValueFunction());
    stringBuffer.append(TEXT_478);
    }
    stringBuffer.append(TEXT_479);
    }
    }
    } else if (genClass.getGetAccessorOperation(genFeature) != null) {
    stringBuffer.append(TEXT_480);
    stringBuffer.append(genClass.getGetAccessorOperation(genFeature).getBody(genModel.getIndentation(stringBuffer)));
    } else {
    stringBuffer.append(TEXT_481);
    stringBuffer.append(genFeature.getFormattedName());
    stringBuffer.append(TEXT_482);
    stringBuffer.append(genFeature.getFeatureKind());
    stringBuffer.append(TEXT_483);
    if (genFeature.isListType()) {
    stringBuffer.append(TEXT_484);
    if (genFeature.isMapType()) {
    stringBuffer.append(TEXT_485);
    } else if (genFeature.isFeatureMapType()) {
    stringBuffer.append(TEXT_486);
    } else {
    stringBuffer.append(TEXT_487);
    }
    stringBuffer.append(TEXT_488);
    }
    stringBuffer.append(TEXT_489);
    //Class/getGenFeature.todo.override.javajetinc
    }
    }
    stringBuffer.append(TEXT_490);
    }
    //Class/getGenFeature.override.javajetinc
    }
    if (isImplementation && !genModel.isReflectiveDelegation() && genFeature.isBasicGet()) {
    stringBuffer.append(TEXT_491);
    if (isJDK50) { //Class/basicGetGenFeature.annotations.insert.javajetinc
    }
    stringBuffer.append(TEXT_492);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_493);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_494);
    if (genModel.isDynamicDelegation()) {
    stringBuffer.append(TEXT_495);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_496);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(TEXT_497);
    stringBuffer.append(genFeature.getQualifiedFeatureAccessor());
    stringBuffer.append(TEXT_498);
    stringBuffer.append(!genFeature.isEffectiveSuppressEMFTypes());
    stringBuffer.append(TEXT_499);
    } else if (genFeature.hasSettingDelegate()) {
    stringBuffer.append(TEXT_500);
    if (!isJDK50 && genFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_501);
    }
    stringBuffer.append(TEXT_502);
    stringBuffer.append(genFeature.getObjectType(genClass));
    stringBuffer.append(TEXT_503);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_504);
    if (!isJDK50 && genFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_505);
    stringBuffer.append(genFeature.getPrimitiveValueFunction());
    stringBuffer.append(TEXT_506);
    }
    stringBuffer.append(TEXT_507);
    } else if (genFeature.isContainer()) {
    stringBuffer.append(TEXT_508);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(positiveOffsetCorrection);
    stringBuffer.append(TEXT_509);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_510);
    } else if (!genFeature.isVolatile()) {
    if (genModel.isVirtualDelegation()) {
    stringBuffer.append(TEXT_511);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_512);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(positiveOffsetCorrection);
    stringBuffer.append(TEXT_513);
    } else {
    stringBuffer.append(TEXT_514);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_515);
    }
    } else if (genFeature.hasDelegateFeature()) { GenFeature delegateFeature = genFeature.getDelegateFeature();
    if (delegateFeature.isWrappedFeatureMapType()) {
    stringBuffer.append(TEXT_516);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_517);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.FeatureMap"));
    stringBuffer.append(TEXT_518);
    stringBuffer.append(delegateFeature.getAccessorName());
    stringBuffer.append(TEXT_519);
    stringBuffer.append(genFeature.getQualifiedFeatureAccessor());
    stringBuffer.append(TEXT_520);
    } else {
    stringBuffer.append(TEXT_521);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_522);
    stringBuffer.append(delegateFeature.getAccessorName());
    stringBuffer.append(TEXT_523);
    stringBuffer.append(genFeature.getQualifiedFeatureAccessor());
    stringBuffer.append(TEXT_524);
    }
    } else {
    stringBuffer.append(TEXT_525);
    stringBuffer.append(genFeature.getFormattedName());
    stringBuffer.append(TEXT_526);
    stringBuffer.append(genFeature.getFeatureKind());
    stringBuffer.append(TEXT_527);
    //Class/basicGetGenFeature.todo.override.javajetinc
    }
    stringBuffer.append(TEXT_528);
    //Class/basicGetGenFeature.override.javajetinc
    }
    if (isImplementation && !genModel.isReflectiveDelegation() && genFeature.isBasicSet()) {
    stringBuffer.append(TEXT_529);
    if (isJDK50) { //Class/basicSetGenFeature.annotations.insert.javajetinc
    }
    stringBuffer.append(TEXT_530);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.notify.NotificationChain"));
    stringBuffer.append(TEXT_531);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_532);
    stringBuffer.append(genFeature.getImportedInternalType(genClass));
    stringBuffer.append(TEXT_533);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_534);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.notify.NotificationChain"));
    stringBuffer.append(TEXT_535);
    if (genFeature.isContainer()) {
    stringBuffer.append(TEXT_536);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.InternalEObject"));
    stringBuffer.append(TEXT_537);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_538);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(positiveOffsetCorrection);
    stringBuffer.append(TEXT_539);
    stringBuffer.append(TEXT_540);
    } else if (genModel.isDynamicDelegation()) {
    stringBuffer.append(TEXT_541);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.InternalEObject"));
    stringBuffer.append(TEXT_542);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_543);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(positiveOffsetCorrection);
    stringBuffer.append(TEXT_544);
    stringBuffer.append(TEXT_545);
    } else if (!genFeature.isVolatile()) {
    if (genModel.isVirtualDelegation()) {
    stringBuffer.append(TEXT_546);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_547);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(positiveOffsetCorrection);
    stringBuffer.append(TEXT_548);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_549);
    } else {
    stringBuffer.append(TEXT_550);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_551);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_552);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_553);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_554);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_555);
    }
    if (genFeature.isUnsettable()) {
    if (genModel.isVirtualDelegation()) {
    if (!genModel.isSuppressNotification()) {
    stringBuffer.append(TEXT_556);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_557);
    }
    } else if (genClass.isESetFlag(genFeature)) {
    stringBuffer.append(TEXT_558);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_559);
    stringBuffer.append(genClass.getESetFlagsField(genFeature));
    stringBuffer.append(TEXT_560);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_561);
    if (!genModel.isSuppressNotification()) {
    stringBuffer.append(TEXT_562);
    stringBuffer.append(genClass.getESetFlagsField(genFeature));
    stringBuffer.append(TEXT_563);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_564);
    }
    } else {
    if (!genModel.isSuppressNotification()) {
    stringBuffer.append(TEXT_565);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_566);
    stringBuffer.append(genFeature.getUncapName());
    stringBuffer.append(TEXT_567);
    }
    stringBuffer.append(TEXT_568);
    stringBuffer.append(genFeature.getUncapName());
    stringBuffer.append(TEXT_569);
    }
    }
    if (!genModel.isSuppressNotification()) {
    stringBuffer.append(TEXT_570);
    if (genFeature.isUnsettable()) {
    stringBuffer.append(TEXT_571);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.impl.ENotificationImpl"));
    stringBuffer.append(TEXT_572);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.impl.ENotificationImpl"));
    stringBuffer.append(TEXT_573);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.notify.Notification"));
    stringBuffer.append(TEXT_574);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(positiveOffsetCorrection);
    stringBuffer.append(TEXT_575);
    if (genModel.isVirtualDelegation()) {
    stringBuffer.append(TEXT_576);
    stringBuffer.append(genFeature.getCapName());
    } else {
    stringBuffer.append(TEXT_577);
    stringBuffer.append(genFeature.getCapName());
    }
    stringBuffer.append(TEXT_578);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_579);
    if (genModel.isVirtualDelegation()) {
    stringBuffer.append(TEXT_580);
    } else {
    stringBuffer.append(TEXT_581);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_582);
    }
    stringBuffer.append(TEXT_583);
    } else {
    stringBuffer.append(TEXT_584);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.impl.ENotificationImpl"));
    stringBuffer.append(TEXT_585);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.impl.ENotificationImpl"));
    stringBuffer.append(TEXT_586);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.notify.Notification"));
    stringBuffer.append(TEXT_587);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(positiveOffsetCorrection);
    stringBuffer.append(TEXT_588);
    if (genModel.isVirtualDelegation()) {
    stringBuffer.append(TEXT_589);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_590);
    stringBuffer.append(genFeature.getCapName());
    } else {
    stringBuffer.append(TEXT_591);
    stringBuffer.append(genFeature.getCapName());
    }
    stringBuffer.append(TEXT_592);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_593);
    }
    stringBuffer.append(TEXT_594);
    }
    stringBuffer.append(TEXT_595);
    } else if (genFeature.hasDelegateFeature()) { GenFeature delegateFeature = genFeature.getDelegateFeature();
    if (delegateFeature.isWrappedFeatureMapType()) {
    stringBuffer.append(TEXT_596);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.FeatureMap"));
    stringBuffer.append(TEXT_597);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.FeatureMap"));
    stringBuffer.append(TEXT_598);
    stringBuffer.append(delegateFeature.getAccessorName());
    stringBuffer.append(TEXT_599);
    stringBuffer.append(genFeature.getQualifiedFeatureAccessor());
    stringBuffer.append(TEXT_600);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_601);
    } else {
    stringBuffer.append(TEXT_602);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.FeatureMap"));
    stringBuffer.append(TEXT_603);
    stringBuffer.append(delegateFeature.getAccessorName());
    stringBuffer.append(TEXT_604);
    stringBuffer.append(genFeature.getQualifiedFeatureAccessor());
    stringBuffer.append(TEXT_605);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_606);
    }
    } else {
    stringBuffer.append(TEXT_607);
    stringBuffer.append(genFeature.getFormattedName());
    stringBuffer.append(TEXT_608);
    stringBuffer.append(genFeature.getFeatureKind());
    stringBuffer.append(TEXT_609);
    //Class/basicSetGenFeature.todo.override.javajetinc
    }
    stringBuffer.append(TEXT_610);
    //Class/basicSetGenFeature.override.javajetinc
    }
    if (genFeature.isSet() && (isImplementation || !genFeature.isSuppressedSetVisibility())) {
    if (isInterface) { 
    stringBuffer.append(TEXT_611);
    stringBuffer.append(genClass.getQualifiedInterfaceName());
    stringBuffer.append(TEXT_612);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_613);
    stringBuffer.append(genFeature.getFormattedName());
    stringBuffer.append(TEXT_614);
    stringBuffer.append(genFeature.getFeatureKind());
    stringBuffer.append(TEXT_615);
    stringBuffer.append(TEXT_616);
    stringBuffer.append(genFeature.getFormattedName());
    stringBuffer.append(TEXT_617);
    stringBuffer.append(genFeature.getFeatureKind());
    stringBuffer.append(TEXT_618);
    if (genFeature.isEnumType()) {
    stringBuffer.append(TEXT_619);
    stringBuffer.append(genFeature.getTypeGenEnum().getQualifiedName());
    }
    if (genFeature.isUnsettable()) {
    if (!genFeature.isSuppressedIsSetVisibility()) {
    stringBuffer.append(TEXT_620);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_621);
    }
    if (!genFeature.isSuppressedUnsetVisibility()) {
    stringBuffer.append(TEXT_622);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_623);
    }
    }
    stringBuffer.append(TEXT_624);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_625);
    //Class/setGenFeature.javadoc.override.javajetinc
    } else {
    stringBuffer.append(TEXT_626);
    if (isJDK50) { //Class/setGenFeature.annotations.insert.javajetinc
    }
    }
    if (!isImplementation) { 
    stringBuffer.append(TEXT_627);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_628);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_629);
    } else { GenOperation setAccessorOperation = genClass.getSetAccessorOperation(genFeature);
    stringBuffer.append(TEXT_630);
    stringBuffer.append(genFeature.getAccessorName());
    if (genClass.hasCollidingSetAccessorOperation(genFeature)) {
    stringBuffer.append(TEXT_631);
    }
    stringBuffer.append(TEXT_632);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_633);
    stringBuffer.append(setAccessorOperation == null ? "new" + genFeature.getCapName() : setAccessorOperation.getGenParameters().get(0).getName());
    stringBuffer.append(TEXT_634);
    if (genModel.isDynamicDelegation()) {
    stringBuffer.append(TEXT_635);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(TEXT_636);
    stringBuffer.append(genFeature.getQualifiedFeatureAccessor());
    stringBuffer.append(TEXT_637);
    if (!isJDK50 && genFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_638);
    stringBuffer.append(genFeature.getObjectType(genClass));
    stringBuffer.append(TEXT_639);
    }
    stringBuffer.append(TEXT_640);
    stringBuffer.append(genFeature.getCapName());
    if (!isJDK50 && genFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_641);
    }
    stringBuffer.append(TEXT_642);
    } else if (genModel.isReflectiveDelegation()) {
    stringBuffer.append(TEXT_643);
    stringBuffer.append(genFeature.getQualifiedFeatureAccessor());
    stringBuffer.append(TEXT_644);
    if (!isJDK50 && genFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_645);
    stringBuffer.append(genFeature.getObjectType(genClass));
    stringBuffer.append(TEXT_646);
    }
    stringBuffer.append(TEXT_647);
    stringBuffer.append(genFeature.getCapName());
    if (!isJDK50 && genFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_648);
    }
    stringBuffer.append(TEXT_649);
    } else if (genFeature.hasSettingDelegate()) {
    stringBuffer.append(TEXT_650);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_651);
    if (!isJDK50 && genFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_652);
    stringBuffer.append(genFeature.getObjectType(genClass));
    stringBuffer.append(TEXT_653);
    }
    stringBuffer.append(TEXT_654);
    stringBuffer.append(genFeature.getCapName());
    if (!isJDK50 && genFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_655);
    }
    stringBuffer.append(TEXT_656);
    } else if (!genFeature.isVolatile()) {
    if (genFeature.isContainer()) { GenFeature reverseFeature = genFeature.getReverse(); GenClass targetClass = reverseFeature.getGenClass(); String reverseOffsetCorrection = targetClass.hasOffsetCorrection() ? " + " + genClass.getOffsetCorrectionField(genFeature) : "";
    stringBuffer.append(TEXT_657);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_658);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(positiveOffsetCorrection);
    stringBuffer.append(TEXT_659);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_660);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.EcoreUtil"));
    stringBuffer.append(TEXT_661);
    stringBuffer.append(genFeature.getEObjectCast());
    stringBuffer.append(TEXT_662);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_663);
    stringBuffer.append(genModel.getImportedName("java.lang.IllegalArgumentException"));
    stringBuffer.append(TEXT_664);
    stringBuffer.append(genModel.getNonNLS());
    stringBuffer.append(TEXT_665);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.notify.NotificationChain"));
    stringBuffer.append(TEXT_666);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_667);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.InternalEObject"));
    stringBuffer.append(TEXT_668);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_669);
    stringBuffer.append(targetClass.getQualifiedFeatureID(reverseFeature));
    stringBuffer.append(reverseOffsetCorrection);
    stringBuffer.append(TEXT_670);
    stringBuffer.append(targetClass.getRawImportedInterfaceName());
    stringBuffer.append(TEXT_671);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_672);
    stringBuffer.append(genFeature.getInternalTypeCast());
    stringBuffer.append(TEXT_673);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_674);
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
    } else if (genFeature.isBidirectional() || genFeature.isEffectiveContains()) {
    if (genModel.isVirtualDelegation()) {
    stringBuffer.append(TEXT_681);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_682);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_683);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_684);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(positiveOffsetCorrection);
    stringBuffer.append(TEXT_685);
    }
    stringBuffer.append(TEXT_686);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_687);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_688);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.notify.NotificationChain"));
    stringBuffer.append(TEXT_689);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_690);
    if (!genFeature.isBidirectional()) {
    stringBuffer.append(TEXT_691);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.InternalEObject"));
    stringBuffer.append(TEXT_692);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_693);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(negativeOffsetCorrection);
    stringBuffer.append(TEXT_694);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_695);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.InternalEObject"));
    stringBuffer.append(TEXT_696);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_697);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(negativeOffsetCorrection);
    stringBuffer.append(TEXT_698);
    } else { GenFeature reverseFeature = genFeature.getReverse(); GenClass targetClass = reverseFeature.getGenClass(); String reverseOffsetCorrection = targetClass.hasOffsetCorrection() ? " + " + genClass.getOffsetCorrectionField(genFeature) : "";
    stringBuffer.append(TEXT_699);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.InternalEObject"));
    stringBuffer.append(TEXT_700);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_701);
    stringBuffer.append(targetClass.getQualifiedFeatureID(reverseFeature));
    stringBuffer.append(reverseOffsetCorrection);
    stringBuffer.append(TEXT_702);
    stringBuffer.append(targetClass.getRawImportedInterfaceName());
    stringBuffer.append(TEXT_703);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_704);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.InternalEObject"));
    stringBuffer.append(TEXT_705);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_706);
    stringBuffer.append(targetClass.getQualifiedFeatureID(reverseFeature));
    stringBuffer.append(reverseOffsetCorrection);
    stringBuffer.append(TEXT_707);
    stringBuffer.append(targetClass.getRawImportedInterfaceName());
    stringBuffer.append(TEXT_708);
    }
    stringBuffer.append(TEXT_709);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_710);
    stringBuffer.append(genFeature.getInternalTypeCast());
    stringBuffer.append(TEXT_711);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_712);
    if (genFeature.isUnsettable()) {
    stringBuffer.append(TEXT_713);
    if (genModel.isVirtualDelegation()) {
    stringBuffer.append(TEXT_714);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_715);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(positiveOffsetCorrection);
    stringBuffer.append(TEXT_716);
    } else if (genClass.isESetFlag(genFeature)) {
    if (!genModel.isSuppressNotification()) {
    stringBuffer.append(TEXT_717);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_718);
    stringBuffer.append(genClass.getESetFlagsField(genFeature));
    stringBuffer.append(TEXT_719);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_720);
    }
    stringBuffer.append(TEXT_721);
    stringBuffer.append(genClass.getESetFlagsField(genFeature));
    stringBuffer.append(TEXT_722);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_723);
    } else {
    if (!genModel.isSuppressNotification()) {
    stringBuffer.append(TEXT_724);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_725);
    stringBuffer.append(genFeature.getUncapName());
    stringBuffer.append(TEXT_726);
    }
    stringBuffer.append(TEXT_727);
    stringBuffer.append(genFeature.getUncapName());
    stringBuffer.append(TEXT_728);
    }
    if (!genModel.isSuppressNotification()) {
    stringBuffer.append(TEXT_729);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.impl.ENotificationImpl"));
    stringBuffer.append(TEXT_730);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.notify.Notification"));
    stringBuffer.append(TEXT_731);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(positiveOffsetCorrection);
    stringBuffer.append(TEXT_732);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_733);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_734);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_735);
    }
    stringBuffer.append(TEXT_736);
    } else {
    if (!genModel.isSuppressNotification()) {
    stringBuffer.append(TEXT_737);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.impl.ENotificationImpl"));
    stringBuffer.append(TEXT_738);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.notify.Notification"));
    stringBuffer.append(TEXT_739);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(positiveOffsetCorrection);
    stringBuffer.append(TEXT_740);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_741);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_742);
    }
    }
    } else {
    if (genClass.isFlag(genFeature)) {
    if (!genModel.isSuppressNotification()) {
    if (genFeature.isBooleanType()) {
    stringBuffer.append(TEXT_743);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_744);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_745);
    stringBuffer.append(genClass.getFlagsField(genFeature));
    stringBuffer.append(TEXT_746);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_747);
    } else {
    stringBuffer.append(TEXT_748);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_749);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_750);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_751);
    stringBuffer.append(genClass.getFlagsField(genFeature));
    stringBuffer.append(TEXT_752);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_753);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_754);
    }
    }
    if (genFeature.isBooleanType()) {
    stringBuffer.append(TEXT_755);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_756);
    stringBuffer.append(genClass.getFlagsField(genFeature));
    stringBuffer.append(TEXT_757);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_758);
    stringBuffer.append(genClass.getFlagsField(genFeature));
    stringBuffer.append(TEXT_759);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_760);
    } else {
    stringBuffer.append(TEXT_761);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_762);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_763);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_764);
    stringBuffer.append(genClass.getFlagsField(genFeature));
    stringBuffer.append(TEXT_765);
    stringBuffer.append(genClass.getFlagsField(genFeature));
    stringBuffer.append(TEXT_766);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_767);
    if (isJDK50) {
    stringBuffer.append(TEXT_768);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_769);
    } else {
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_770);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_771);
    }
    stringBuffer.append(TEXT_772);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_773);
    }
    } else {
    if (!genModel.isVirtualDelegation() || genFeature.isPrimitiveType()) {
    if (!genModel.isSuppressNotification()) {
    stringBuffer.append(TEXT_774);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_775);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_776);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_777);
    }
    }
    if (genFeature.isEnumType()) {
    if (genModel.isVirtualDelegation()) {
    stringBuffer.append(TEXT_778);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_779);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_780);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_781);
    stringBuffer.append(genFeature.getEDefault());
    stringBuffer.append(TEXT_782);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_783);
    } else {
    stringBuffer.append(TEXT_784);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_785);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_786);
    stringBuffer.append(genFeature.getEDefault());
    stringBuffer.append(TEXT_787);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_788);
    }
    } else {
    if (genModel.isVirtualDelegation() && !genFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_789);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_790);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_791);
    stringBuffer.append(genFeature.getInternalTypeCast());
    stringBuffer.append(TEXT_792);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_793);
    } else {
    stringBuffer.append(TEXT_794);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_795);
    stringBuffer.append(genFeature.getInternalTypeCast());
    stringBuffer.append(TEXT_796);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_797);
    }
    }
    if (genModel.isVirtualDelegation() && !genFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_798);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_799);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(positiveOffsetCorrection);
    stringBuffer.append(TEXT_800);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_801);
    }
    }
    if (genFeature.isUnsettable()) {
    if (genModel.isVirtualDelegation() && !genFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_802);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_803);
    } else if (genClass.isESetFlag(genFeature)) {
    if (!genModel.isSuppressNotification()) {
    stringBuffer.append(TEXT_804);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_805);
    stringBuffer.append(genClass.getESetFlagsField(genFeature));
    stringBuffer.append(TEXT_806);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_807);
    }
    stringBuffer.append(TEXT_808);
    stringBuffer.append(genClass.getESetFlagsField(genFeature));
    stringBuffer.append(TEXT_809);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_810);
    } else {
    if (!genModel.isSuppressNotification()) {
    stringBuffer.append(TEXT_811);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_812);
    stringBuffer.append(genFeature.getUncapName());
    stringBuffer.append(TEXT_813);
    }
    stringBuffer.append(TEXT_814);
    stringBuffer.append(genFeature.getUncapName());
    stringBuffer.append(TEXT_815);
    }
    if (!genModel.isSuppressNotification()) {
    stringBuffer.append(TEXT_816);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.impl.ENotificationImpl"));
    stringBuffer.append(TEXT_817);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.notify.Notification"));
    stringBuffer.append(TEXT_818);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(positiveOffsetCorrection);
    stringBuffer.append(TEXT_819);
    if (genModel.isVirtualDelegation() && !genFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_820);
    stringBuffer.append(genFeature.getEDefault());
    stringBuffer.append(TEXT_821);
    stringBuffer.append(genFeature.getCapName());
    } else {
    stringBuffer.append(TEXT_822);
    stringBuffer.append(genFeature.getCapName());
    }
    stringBuffer.append(TEXT_823);
    if (genClass.isFlag(genFeature)) {
    stringBuffer.append(TEXT_824);
    stringBuffer.append(genFeature.getCapName());
    } else {
    stringBuffer.append(genFeature.getSafeName());
    }
    stringBuffer.append(TEXT_825);
    if (genModel.isVirtualDelegation() && !genFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_826);
    } else {
    stringBuffer.append(TEXT_827);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_828);
    }
    stringBuffer.append(TEXT_829);
    }
    } else {
    if (!genModel.isSuppressNotification()) {
    stringBuffer.append(TEXT_830);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.impl.ENotificationImpl"));
    stringBuffer.append(TEXT_831);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.notify.Notification"));
    stringBuffer.append(TEXT_832);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(positiveOffsetCorrection);
    stringBuffer.append(TEXT_833);
    if (genModel.isVirtualDelegation() && !genFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_834);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_835);
    stringBuffer.append(genFeature.getEDefault());
    stringBuffer.append(TEXT_836);
    stringBuffer.append(genFeature.getCapName());
    } else {
    stringBuffer.append(TEXT_837);
    stringBuffer.append(genFeature.getCapName());
    }
    stringBuffer.append(TEXT_838);
    if (genClass.isFlag(genFeature)) {
    stringBuffer.append(TEXT_839);
    stringBuffer.append(genFeature.getCapName());
    } else {
    stringBuffer.append(genFeature.getSafeName());
    }
    stringBuffer.append(TEXT_840);
    }
    }
    }
    } else if (genFeature.hasDelegateFeature()) { GenFeature delegateFeature = genFeature.getDelegateFeature();
    if (delegateFeature.isWrappedFeatureMapType()) {
    stringBuffer.append(TEXT_841);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.FeatureMap"));
    stringBuffer.append(TEXT_842);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.FeatureMap"));
    stringBuffer.append(TEXT_843);
    stringBuffer.append(delegateFeature.getAccessorName());
    stringBuffer.append(TEXT_844);
    stringBuffer.append(genFeature.getQualifiedFeatureAccessor());
    stringBuffer.append(TEXT_845);
    if (!isJDK50 && genFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_846);
    stringBuffer.append(genFeature.getObjectType(genClass));
    stringBuffer.append(TEXT_847);
    }
    stringBuffer.append(TEXT_848);
    stringBuffer.append(genFeature.getCapName());
    if (!isJDK50 && genFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_849);
    }
    stringBuffer.append(TEXT_850);
    } else {
    stringBuffer.append(TEXT_851);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.FeatureMap"));
    stringBuffer.append(TEXT_852);
    stringBuffer.append(delegateFeature.getAccessorName());
    stringBuffer.append(TEXT_853);
    stringBuffer.append(genFeature.getQualifiedFeatureAccessor());
    stringBuffer.append(TEXT_854);
    if (!isJDK50 && genFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_855);
    stringBuffer.append(genFeature.getObjectType(genClass));
    stringBuffer.append(TEXT_856);
    }
    stringBuffer.append(TEXT_857);
    stringBuffer.append(genFeature.getCapName());
    if (!isJDK50 && genFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_858);
    }
    stringBuffer.append(TEXT_859);
    }
    } else if (setAccessorOperation != null) {
    stringBuffer.append(TEXT_860);
    stringBuffer.append(setAccessorOperation.getBody(genModel.getIndentation(stringBuffer)));
    } else {
    stringBuffer.append(TEXT_861);
    stringBuffer.append(genFeature.getFormattedName());
    stringBuffer.append(TEXT_862);
    stringBuffer.append(genFeature.getFeatureKind());
    stringBuffer.append(TEXT_863);
    //Class/setGenFeature.todo.override.javajetinc
    }
    stringBuffer.append(TEXT_864);
    }
    //Class/setGenFeature.override.javajetinc
    }
    if (isImplementation && !genModel.isReflectiveDelegation() && genFeature.isBasicUnset()) {
    stringBuffer.append(TEXT_865);
    if (isJDK50) { //Class/basicUnsetGenFeature.annotations.insert.javajetinc
    }
    stringBuffer.append(TEXT_866);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.notify.NotificationChain"));
    stringBuffer.append(TEXT_867);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_868);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.notify.NotificationChain"));
    stringBuffer.append(TEXT_869);
    if (genModel.isDynamicDelegation()) {
    stringBuffer.append(TEXT_870);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.InternalEObject"));
    stringBuffer.append(TEXT_871);
    if (genFeature.isResolveProxies()) {
    stringBuffer.append(TEXT_872);
    stringBuffer.append(genFeature.getAccessorName());
    } else {
    stringBuffer.append(genFeature.getGetAccessor());
    }
    stringBuffer.append(TEXT_873);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(positiveOffsetCorrection);
    stringBuffer.append(TEXT_874);
    } else if (!genFeature.isVolatile()) {
    if (genModel.isVirtualDelegation()) {
    if (!genModel.isSuppressNotification()) {
    stringBuffer.append(TEXT_875);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_876);
    }
    stringBuffer.append(TEXT_877);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(positiveOffsetCorrection);
    stringBuffer.append(TEXT_878);
    } else {
    if (!genModel.isSuppressNotification()) {
    stringBuffer.append(TEXT_879);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_880);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_881);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_882);
    }
    stringBuffer.append(TEXT_883);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_884);
    }
    if (genModel.isVirtualDelegation()) {
    if (!genModel.isSuppressNotification()) {
    stringBuffer.append(TEXT_885);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_886);
    }
    } else if (genClass.isESetFlag(genFeature)) {
    if (!genModel.isSuppressNotification()) {
    stringBuffer.append(TEXT_887);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_888);
    stringBuffer.append(genClass.getESetFlagsField(genFeature));
    stringBuffer.append(TEXT_889);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_890);
    }
    stringBuffer.append(TEXT_891);
    stringBuffer.append(genClass.getESetFlagsField(genFeature));
    stringBuffer.append(TEXT_892);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_893);
    } else {
    if (!genModel.isSuppressNotification()) {
    stringBuffer.append(TEXT_894);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_895);
    stringBuffer.append(genFeature.getUncapName());
    stringBuffer.append(TEXT_896);
    }
    stringBuffer.append(TEXT_897);
    stringBuffer.append(genFeature.getUncapName());
    stringBuffer.append(TEXT_898);
    }
    if (!genModel.isSuppressNotification()) {
    stringBuffer.append(TEXT_899);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.impl.ENotificationImpl"));
    stringBuffer.append(TEXT_900);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.impl.ENotificationImpl"));
    stringBuffer.append(TEXT_901);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.notify.Notification"));
    stringBuffer.append(TEXT_902);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(positiveOffsetCorrection);
    stringBuffer.append(TEXT_903);
    if (genModel.isVirtualDelegation()) {
    stringBuffer.append(TEXT_904);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_905);
    } else {
    stringBuffer.append(TEXT_906);
    stringBuffer.append(genFeature.getCapName());
    }
    stringBuffer.append(TEXT_907);
    if (genModel.isVirtualDelegation()) {
    stringBuffer.append(TEXT_908);
    } else {
    stringBuffer.append(TEXT_909);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_910);
    }
    stringBuffer.append(TEXT_911);
    }
    } else {
    stringBuffer.append(TEXT_912);
    stringBuffer.append(genFeature.getFormattedName());
    stringBuffer.append(TEXT_913);
    stringBuffer.append(genFeature.getFeatureKind());
    stringBuffer.append(TEXT_914);
    //Class/basicUnsetGenFeature.todo.override.javajetinc
    }
    stringBuffer.append(TEXT_915);
    //Class.basicUnsetGenFeature.override.javajetinc
    }
    if (genFeature.isUnset() && (isImplementation || !genFeature.isSuppressedUnsetVisibility())) {
    if (isInterface) {
    stringBuffer.append(TEXT_916);
    stringBuffer.append(genClass.getQualifiedInterfaceName());
    stringBuffer.append(TEXT_917);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_918);
    stringBuffer.append(genFeature.getFormattedName());
    stringBuffer.append(TEXT_919);
    stringBuffer.append(genFeature.getFeatureKind());
    stringBuffer.append(TEXT_920);
    stringBuffer.append(TEXT_921);
    if (!genFeature.isSuppressedIsSetVisibility()) {
    stringBuffer.append(TEXT_922);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_923);
    }
    stringBuffer.append(TEXT_924);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_925);
    if (!genFeature.isListType() && !genFeature.isSuppressedSetVisibility()) {
    stringBuffer.append(TEXT_926);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_927);
    stringBuffer.append(genFeature.getRawImportedBoundType());
    stringBuffer.append(TEXT_928);
    }
    stringBuffer.append(TEXT_929);
    //Class/unsetGenFeature.javadoc.override.javajetinc
    } else {
    stringBuffer.append(TEXT_930);
    if (isJDK50) { //Class/unsetGenFeature.annotations.insert.javajetinc
    }
    }
    if (!isImplementation) {
    stringBuffer.append(TEXT_931);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_932);
    } else {
    stringBuffer.append(TEXT_933);
    stringBuffer.append(genFeature.getAccessorName());
    if (genClass.hasCollidingUnsetAccessorOperation(genFeature)) {
    stringBuffer.append(TEXT_934);
    }
    stringBuffer.append(TEXT_935);
    if (genModel.isDynamicDelegation()) {
    stringBuffer.append(TEXT_936);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(TEXT_937);
    stringBuffer.append(genFeature.getQualifiedFeatureAccessor());
    stringBuffer.append(TEXT_938);
    } else if (genModel.isReflectiveDelegation()) {
    stringBuffer.append(TEXT_939);
    stringBuffer.append(genFeature.getQualifiedFeatureAccessor());
    stringBuffer.append(TEXT_940);
    } else if (genFeature.hasSettingDelegate()) {
    stringBuffer.append(TEXT_941);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_942);
    } else if (!genFeature.isVolatile()) {
    if (genFeature.isListType()) {
    if (genModel.isVirtualDelegation()) {
    stringBuffer.append(TEXT_943);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_944);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_945);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_946);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(positiveOffsetCorrection);
    stringBuffer.append(TEXT_947);
    }
    stringBuffer.append(TEXT_948);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_949);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.InternalEList"));
    stringBuffer.append(TEXT_950);
    stringBuffer.append(singleWildcard);
    stringBuffer.append(TEXT_951);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_952);
    } else if (genFeature.isBidirectional() || genFeature.isEffectiveContains()) {
    if (genModel.isVirtualDelegation()) {
    stringBuffer.append(TEXT_953);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_954);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_955);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_956);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(positiveOffsetCorrection);
    stringBuffer.append(TEXT_957);
    }
    stringBuffer.append(TEXT_958);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_959);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.notify.NotificationChain"));
    stringBuffer.append(TEXT_960);
    if (!genFeature.isBidirectional()) {
    stringBuffer.append(TEXT_961);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.InternalEObject"));
    stringBuffer.append(TEXT_962);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_963);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(negativeOffsetCorrection);
    stringBuffer.append(TEXT_964);
    } else { GenFeature reverseFeature = genFeature.getReverse(); GenClass targetClass = reverseFeature.getGenClass(); String reverseOffsetCorrection = targetClass.hasOffsetCorrection() ? " + " + genClass.getOffsetCorrectionField(genFeature) : "";
    stringBuffer.append(TEXT_965);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.InternalEObject"));
    stringBuffer.append(TEXT_966);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_967);
    stringBuffer.append(targetClass.getQualifiedFeatureID(reverseFeature));
    stringBuffer.append(reverseOffsetCorrection);
    stringBuffer.append(TEXT_968);
    stringBuffer.append(targetClass.getRawImportedInterfaceName());
    stringBuffer.append(TEXT_969);
    }
    stringBuffer.append(TEXT_970);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_971);
    if (genModel.isVirtualDelegation()) {
    stringBuffer.append(TEXT_972);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_973);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(positiveOffsetCorrection);
    stringBuffer.append(TEXT_974);
    } else if (genClass.isESetFlag(genFeature)) {
    if (!genModel.isSuppressNotification()) {
    stringBuffer.append(TEXT_975);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_976);
    stringBuffer.append(genClass.getESetFlagsField(genFeature));
    stringBuffer.append(TEXT_977);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_978);
    }
    stringBuffer.append(TEXT_979);
    stringBuffer.append(genClass.getESetFlagsField(genFeature));
    stringBuffer.append(TEXT_980);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_981);
    } else {
    if (!genModel.isSuppressNotification()) {
    stringBuffer.append(TEXT_982);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_983);
    stringBuffer.append(genFeature.getUncapName());
    stringBuffer.append(TEXT_984);
    }
    stringBuffer.append(TEXT_985);
    stringBuffer.append(genFeature.getUncapName());
    stringBuffer.append(TEXT_986);
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
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_991);
    }
    stringBuffer.append(TEXT_992);
    } else {
    if (genClass.isFlag(genFeature)) {
    if (!genModel.isSuppressNotification()) {
    if (genFeature.isBooleanType()) {
    stringBuffer.append(TEXT_993);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_994);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_995);
    stringBuffer.append(genClass.getFlagsField(genFeature));
    stringBuffer.append(TEXT_996);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_997);
    } else {
    stringBuffer.append(TEXT_998);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_999);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_1000);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_1001);
    stringBuffer.append(genClass.getFlagsField(genFeature));
    stringBuffer.append(TEXT_1002);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_1003);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_1004);
    }
    }
    } else if (genModel.isVirtualDelegation() && !genFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_1005);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_1006);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(positiveOffsetCorrection);
    stringBuffer.append(TEXT_1007);
    } else {
    if (!genModel.isSuppressNotification()) {
    stringBuffer.append(TEXT_1008);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_1009);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_1010);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_1011);
    }
    }
    if (!genModel.isSuppressNotification()) {
    if (genModel.isVirtualDelegation() && !genFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_1012);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_1013);
    } else if (genClass.isESetFlag(genFeature)) {
    stringBuffer.append(TEXT_1014);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_1015);
    stringBuffer.append(genClass.getESetFlagsField(genFeature));
    stringBuffer.append(TEXT_1016);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_1017);
    } else {
    stringBuffer.append(TEXT_1018);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_1019);
    stringBuffer.append(genFeature.getUncapName());
    stringBuffer.append(TEXT_1020);
    }
    }
    if (genFeature.isReferenceType()) {
    stringBuffer.append(TEXT_1021);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_1022);
    if (!genModel.isVirtualDelegation()) {
    if (genClass.isESetFlag(genFeature)) {
    stringBuffer.append(TEXT_1023);
    stringBuffer.append(genClass.getESetFlagsField(genFeature));
    stringBuffer.append(TEXT_1024);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_1025);
    } else {
    stringBuffer.append(TEXT_1026);
    stringBuffer.append(genFeature.getUncapName());
    stringBuffer.append(TEXT_1027);
    }
    }
    if (!genModel.isSuppressNotification()) {
    stringBuffer.append(TEXT_1028);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.impl.ENotificationImpl"));
    stringBuffer.append(TEXT_1029);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.notify.Notification"));
    stringBuffer.append(TEXT_1030);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(positiveOffsetCorrection);
    stringBuffer.append(TEXT_1031);
    if (genModel.isVirtualDelegation()) {
    stringBuffer.append(TEXT_1032);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_1033);
    } else {
    stringBuffer.append(TEXT_1034);
    stringBuffer.append(genFeature.getCapName());
    }
    stringBuffer.append(TEXT_1035);
    if (genModel.isVirtualDelegation()) {
    stringBuffer.append(TEXT_1036);
    } else {
    stringBuffer.append(TEXT_1037);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_1038);
    }
    stringBuffer.append(TEXT_1039);
    }
    } else {
    if (genClass.isFlag(genFeature)) {
    if (genFeature.isBooleanType()) {
    stringBuffer.append(TEXT_1040);
    stringBuffer.append(genFeature.getEDefault());
    stringBuffer.append(TEXT_1041);
    stringBuffer.append(genClass.getFlagsField(genFeature));
    stringBuffer.append(TEXT_1042);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_1043);
    stringBuffer.append(genClass.getFlagsField(genFeature));
    stringBuffer.append(TEXT_1044);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_1045);
    } else {
    stringBuffer.append(TEXT_1046);
    stringBuffer.append(genClass.getFlagsField(genFeature));
    stringBuffer.append(TEXT_1047);
    stringBuffer.append(genClass.getFlagsField(genFeature));
    stringBuffer.append(TEXT_1048);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_1049);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_1050);
    }
    } else if (!genModel.isVirtualDelegation() || genFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_1051);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_1052);
    stringBuffer.append(genFeature.getEDefault());
    stringBuffer.append(TEXT_1053);
    }
    if (!genModel.isVirtualDelegation() || genFeature.isPrimitiveType()) {
    if (genClass.isESetFlag(genFeature)) {
    stringBuffer.append(TEXT_1054);
    stringBuffer.append(genClass.getESetFlagsField(genFeature));
    stringBuffer.append(TEXT_1055);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_1056);
    } else {
    stringBuffer.append(TEXT_1057);
    stringBuffer.append(genFeature.getUncapName());
    stringBuffer.append(TEXT_1058);
    }
    }
    if (!genModel.isSuppressNotification()) {
    stringBuffer.append(TEXT_1059);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.impl.ENotificationImpl"));
    stringBuffer.append(TEXT_1060);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.notify.Notification"));
    stringBuffer.append(TEXT_1061);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(positiveOffsetCorrection);
    stringBuffer.append(TEXT_1062);
    if (genModel.isVirtualDelegation() && !genFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_1063);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_1064);
    stringBuffer.append(genFeature.getEDefault());
    } else {
    stringBuffer.append(TEXT_1065);
    stringBuffer.append(genFeature.getCapName());
    }
    stringBuffer.append(TEXT_1066);
    stringBuffer.append(genFeature.getEDefault());
    stringBuffer.append(TEXT_1067);
    if (genModel.isVirtualDelegation() && !genFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_1068);
    } else {
    stringBuffer.append(TEXT_1069);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_1070);
    }
    stringBuffer.append(TEXT_1071);
    }
    }
    }
    } else if (genFeature.hasDelegateFeature()) { GenFeature delegateFeature = genFeature.getDelegateFeature();
    if (delegateFeature.isWrappedFeatureMapType()) {
    stringBuffer.append(TEXT_1072);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.FeatureMap"));
    stringBuffer.append(TEXT_1073);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.FeatureMap"));
    stringBuffer.append(TEXT_1074);
    stringBuffer.append(delegateFeature.getAccessorName());
    stringBuffer.append(TEXT_1075);
    stringBuffer.append(genFeature.getQualifiedFeatureAccessor());
    stringBuffer.append(TEXT_1076);
    } else {
    stringBuffer.append(TEXT_1077);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.FeatureMap"));
    stringBuffer.append(TEXT_1078);
    stringBuffer.append(delegateFeature.getAccessorName());
    stringBuffer.append(TEXT_1079);
    stringBuffer.append(genFeature.getQualifiedFeatureAccessor());
    stringBuffer.append(TEXT_1080);
    }
    } else if (genClass.getUnsetAccessorOperation(genFeature) != null) {
    stringBuffer.append(TEXT_1081);
    stringBuffer.append(genClass.getUnsetAccessorOperation(genFeature).getBody(genModel.getIndentation(stringBuffer)));
    } else {
    stringBuffer.append(TEXT_1082);
    stringBuffer.append(genFeature.getFormattedName());
    stringBuffer.append(TEXT_1083);
    stringBuffer.append(genFeature.getFeatureKind());
    stringBuffer.append(TEXT_1084);
    //Class/unsetGenFeature.todo.override.javajetinc
    }
    stringBuffer.append(TEXT_1085);
    }
    //Class/unsetGenFeature.override.javajetinc
    }
    if (genFeature.isIsSet() && (isImplementation || !genFeature.isSuppressedIsSetVisibility())) {
    if (isInterface) {
    stringBuffer.append(TEXT_1086);
    stringBuffer.append(genClass.getQualifiedInterfaceName());
    stringBuffer.append(TEXT_1087);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_1088);
    stringBuffer.append(genFeature.getFormattedName());
    stringBuffer.append(TEXT_1089);
    stringBuffer.append(genFeature.getFeatureKind());
    stringBuffer.append(TEXT_1090);
    stringBuffer.append(TEXT_1091);
    stringBuffer.append(genFeature.getFormattedName());
    stringBuffer.append(TEXT_1092);
    stringBuffer.append(genFeature.getFeatureKind());
    stringBuffer.append(TEXT_1093);
    if (genFeature.isChangeable() && !genFeature.isSuppressedUnsetVisibility()) {
    stringBuffer.append(TEXT_1094);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_1095);
    }
    stringBuffer.append(TEXT_1096);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_1097);
    if (!genFeature.isListType() && genFeature.isChangeable() && !genFeature.isSuppressedSetVisibility()) {
    stringBuffer.append(TEXT_1098);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_1099);
    stringBuffer.append(genFeature.getRawImportedBoundType());
    stringBuffer.append(TEXT_1100);
    }
    stringBuffer.append(TEXT_1101);
    //Class/isSetGenFeature.javadoc.override.javajetinc
    } else {
    stringBuffer.append(TEXT_1102);
    if (isJDK50) { //Class/isSetGenFeature.annotations.insert.javajetinc
    }
    }
    if (!isImplementation) {
    stringBuffer.append(TEXT_1103);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_1104);
    } else {
    stringBuffer.append(TEXT_1105);
    stringBuffer.append(genFeature.getAccessorName());
    if (genClass.hasCollidingIsSetAccessorOperation(genFeature)) {
    stringBuffer.append(TEXT_1106);
    }
    stringBuffer.append(TEXT_1107);
    if (genModel.isDynamicDelegation()) {
    stringBuffer.append(TEXT_1108);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(TEXT_1109);
    stringBuffer.append(genFeature.getQualifiedFeatureAccessor());
    stringBuffer.append(TEXT_1110);
    } else if (genModel.isReflectiveDelegation()) {
    stringBuffer.append(TEXT_1111);
    stringBuffer.append(genFeature.getQualifiedFeatureAccessor());
    stringBuffer.append(TEXT_1112);
    } else if (genFeature.hasSettingDelegate()) {
    stringBuffer.append(TEXT_1113);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_1114);
    } else if (!genFeature.isVolatile()) {
    if (genFeature.isListType()) {
    if (genModel.isVirtualDelegation()) {
    stringBuffer.append(TEXT_1115);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_1116);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_1117);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_1118);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(positiveOffsetCorrection);
    stringBuffer.append(TEXT_1119);
    }
    stringBuffer.append(TEXT_1120);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_1121);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.InternalEList"));
    stringBuffer.append(TEXT_1122);
    stringBuffer.append(singleWildcard);
    stringBuffer.append(TEXT_1123);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_1124);
    } else {
    if (genModel.isVirtualDelegation() && !genFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_1125);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(positiveOffsetCorrection);
    stringBuffer.append(TEXT_1126);
    } else if (genClass.isESetFlag(genFeature)) {
    stringBuffer.append(TEXT_1127);
    stringBuffer.append(genClass.getESetFlagsField(genFeature));
    stringBuffer.append(TEXT_1128);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_1129);
    } else {
    stringBuffer.append(TEXT_1130);
    stringBuffer.append(genFeature.getUncapName());
    stringBuffer.append(TEXT_1131);
    }
    }
    } else if (genFeature.hasDelegateFeature()) { GenFeature delegateFeature = genFeature.getDelegateFeature();
    if (delegateFeature.isWrappedFeatureMapType()) {
    stringBuffer.append(TEXT_1132);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.FeatureMap"));
    stringBuffer.append(TEXT_1133);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.FeatureMap"));
    stringBuffer.append(TEXT_1134);
    stringBuffer.append(delegateFeature.getAccessorName());
    stringBuffer.append(TEXT_1135);
    stringBuffer.append(genFeature.getQualifiedFeatureAccessor());
    stringBuffer.append(TEXT_1136);
    } else {
    stringBuffer.append(TEXT_1137);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.FeatureMap"));
    stringBuffer.append(TEXT_1138);
    stringBuffer.append(delegateFeature.getAccessorName());
    stringBuffer.append(TEXT_1139);
    stringBuffer.append(genFeature.getQualifiedFeatureAccessor());
    stringBuffer.append(TEXT_1140);
    }
    } else if (genClass.getIsSetAccessorOperation(genFeature) != null) {
    stringBuffer.append(TEXT_1141);
    stringBuffer.append(genClass.getIsSetAccessorOperation(genFeature).getBody(genModel.getIndentation(stringBuffer)));
    } else {
    stringBuffer.append(TEXT_1142);
    stringBuffer.append(genFeature.getFormattedName());
    stringBuffer.append(TEXT_1143);
    stringBuffer.append(genFeature.getFeatureKind());
    stringBuffer.append(TEXT_1144);
    //Class/isSetGenFeature.todo.override.javajetinc
    }
    stringBuffer.append(TEXT_1145);
    }
    //Class/isSetGenFeature.override.javajetinc
    }
    //Class/genFeature.override.javajetinc
    }//for
    }}.run();
    for (GenOperation genOperation : (isImplementation ? genClass.getImplementedGenOperations() : genClass.getDeclaredGenOperations())) {
    if (isImplementation) {
    if (genOperation.isInvariant() && genOperation.hasInvariantExpression()) {
    stringBuffer.append(TEXT_1146);
    stringBuffer.append(genOperation.getName());
    stringBuffer.append(TEXT_1147);
    stringBuffer.append(genOperation.getParameterTypes(", "));
    stringBuffer.append(TEXT_1148);
    stringBuffer.append(genOperation.getFormattedName());
    stringBuffer.append(TEXT_1149);
    stringBuffer.append(genOperation.getName());
    stringBuffer.append(TEXT_1150);
    stringBuffer.append(genOperation.getParameterTypes(", "));
    stringBuffer.append(TEXT_1151);
    stringBuffer.append(genModel.getImportedName("java.lang.String"));
    stringBuffer.append(TEXT_1152);
    stringBuffer.append(CodeGenUtil.upperName(genClass.getUniqueName(genOperation), genModel.getLocale()));
    stringBuffer.append(TEXT_1153);
    stringBuffer.append(genOperation.getInvariantExpression("\t\t"));
    stringBuffer.append(TEXT_1154);
    stringBuffer.append(genModel.getNonNLS());
    stringBuffer.append(TEXT_1155);
    } else if (genOperation.hasInvocationDelegate()) {
    stringBuffer.append(TEXT_1156);
    stringBuffer.append(genOperation.getName());
    stringBuffer.append(TEXT_1157);
    stringBuffer.append(genOperation.getParameterTypes(", "));
    stringBuffer.append(TEXT_1158);
    stringBuffer.append(genOperation.getFormattedName());
    stringBuffer.append(TEXT_1159);
    stringBuffer.append(genOperation.getName());
    stringBuffer.append(TEXT_1160);
    stringBuffer.append(genOperation.getParameterTypes(", "));
    stringBuffer.append(TEXT_1161);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.EOperation"));
    stringBuffer.append(TEXT_1162);
    stringBuffer.append(CodeGenUtil.upperName(genClass.getUniqueName(genOperation), genModel.getLocale()));
    stringBuffer.append(TEXT_1163);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.EOperation"));
    stringBuffer.append(TEXT_1164);
    stringBuffer.append(genOperation.getQualifiedOperationAccessor());
    stringBuffer.append(TEXT_1165);
    }
    }
    if (isInterface) {
    stringBuffer.append(TEXT_1166);
    stringBuffer.append(TEXT_1167);
    if (genOperation.hasDocumentation() || genOperation.hasParameterDocumentation()) {
    stringBuffer.append(TEXT_1168);
    if (genOperation.hasDocumentation()) {
    stringBuffer.append(TEXT_1169);
    stringBuffer.append(genOperation.getDocumentation(genModel.getIndentation(stringBuffer)));
    }
    for (GenParameter genParameter : genOperation.getGenParameters()) {
    if (genParameter.hasDocumentation()) { String documentation = genParameter.getDocumentation("");
    if (documentation.contains("\n") || documentation.contains("\r")) {
    stringBuffer.append(TEXT_1170);
    stringBuffer.append(genParameter.getName());
    stringBuffer.append(TEXT_1171);
    stringBuffer.append(genParameter.getDocumentation(genModel.getIndentation(stringBuffer)));
    } else {
    stringBuffer.append(TEXT_1172);
    stringBuffer.append(genParameter.getName());
    stringBuffer.append(TEXT_1173);
    stringBuffer.append(genParameter.getDocumentation(genModel.getIndentation(stringBuffer)));
    }
    }
    }
    stringBuffer.append(TEXT_1174);
    }
    if (!genModel.isSuppressEMFModelTags()) { boolean first = true; for (StringTokenizer stringTokenizer = new StringTokenizer(genOperation.getModelInfo(), "\n\r"); stringTokenizer.hasMoreTokens(); ) { String modelInfo = stringTokenizer.nextToken(); if (first) { first = false;
    stringBuffer.append(TEXT_1175);
    stringBuffer.append(modelInfo);
    } else {
    stringBuffer.append(TEXT_1176);
    stringBuffer.append(modelInfo);
    }} if (first) {
    stringBuffer.append(TEXT_1177);
    }}
    stringBuffer.append(TEXT_1178);
    //Class/genOperation.javadoc.override.javajetinc
    } else {
    stringBuffer.append(TEXT_1179);
    if (isJDK50) { //Class/genOperation.annotations.insert.javajetinc
    }
    }
    if (!isImplementation) {
    stringBuffer.append(TEXT_1180);
    stringBuffer.append(genOperation.getTypeParameters(genClass));
    stringBuffer.append(genOperation.getImportedType(genClass));
    stringBuffer.append(TEXT_1181);
    stringBuffer.append(genOperation.getName());
    stringBuffer.append(TEXT_1182);
    stringBuffer.append(genOperation.getParameters(genClass));
    stringBuffer.append(TEXT_1183);
    stringBuffer.append(genOperation.getThrows(genClass));
    stringBuffer.append(TEXT_1184);
    } else {
    stringBuffer.append(TEXT_1185);
    stringBuffer.append(genOperation.getTypeParameters(genClass));
    stringBuffer.append(genOperation.getImportedType(genClass));
    stringBuffer.append(TEXT_1186);
    stringBuffer.append(genOperation.getName());
    stringBuffer.append(TEXT_1187);
    stringBuffer.append(genOperation.getParameters(genClass));
    stringBuffer.append(TEXT_1188);
    stringBuffer.append(genOperation.getThrows(genClass));
    stringBuffer.append(TEXT_1189);
    if (genOperation.hasBody()) {
    stringBuffer.append(TEXT_1190);
    stringBuffer.append(genOperation.getBody(genModel.getIndentation(stringBuffer)));
    } else if (genOperation.isInvariant()) {GenClass opClass = genOperation.getGenClass(); String diagnostics = genOperation.getGenParameters().get(0).getName(); String context = genOperation.getGenParameters().get(1).getName();
    if (genOperation.hasInvariantExpression()) {
    stringBuffer.append(TEXT_1191);
    stringBuffer.append(opClass.getGenPackage().getImportedValidatorClassName());
    stringBuffer.append(TEXT_1192);
    stringBuffer.append(genClass.getQualifiedClassifierAccessor());
    stringBuffer.append(TEXT_1193);
    stringBuffer.append(diagnostics);
    stringBuffer.append(TEXT_1194);
    stringBuffer.append(context);
    stringBuffer.append(TEXT_1195);
    stringBuffer.append(genOperation.getValidationDelegate());
    stringBuffer.append(TEXT_1196);
    stringBuffer.append(genModel.getNonNLS());
    stringBuffer.append(TEXT_1197);
    stringBuffer.append(genOperation.getQualifiedOperationAccessor());
    stringBuffer.append(TEXT_1198);
    stringBuffer.append(CodeGenUtil.upperName(genClass.getUniqueName(genOperation), genModel.getLocale()));
    stringBuffer.append(TEXT_1199);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.util.Diagnostic"));
    stringBuffer.append(TEXT_1200);
    stringBuffer.append(opClass.getGenPackage().getImportedValidatorClassName());
    stringBuffer.append(TEXT_1201);
    stringBuffer.append(opClass.getGenPackage().getImportedValidatorClassName());
    stringBuffer.append(TEXT_1202);
    stringBuffer.append(opClass.getOperationID(genOperation));
    stringBuffer.append(TEXT_1203);
    } else {
    stringBuffer.append(TEXT_1204);
    stringBuffer.append(diagnostics);
    stringBuffer.append(TEXT_1205);
    stringBuffer.append(diagnostics);
    stringBuffer.append(TEXT_1206);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.util.BasicDiagnostic"));
    stringBuffer.append(TEXT_1207);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.util.Diagnostic"));
    stringBuffer.append(TEXT_1208);
    stringBuffer.append(opClass.getGenPackage().getImportedValidatorClassName());
    stringBuffer.append(TEXT_1209);
    stringBuffer.append(opClass.getGenPackage().getImportedValidatorClassName());
    stringBuffer.append(TEXT_1210);
    stringBuffer.append(opClass.getOperationID(genOperation));
    stringBuffer.append(TEXT_1211);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.plugin.EcorePlugin"));
    stringBuffer.append(TEXT_1212);
    stringBuffer.append(genOperation.getName());
    stringBuffer.append(TEXT_1213);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.EObjectValidator"));
    stringBuffer.append(TEXT_1214);
    stringBuffer.append(context);
    stringBuffer.append(TEXT_1215);
    stringBuffer.append(genModel.getNonNLS());
    stringBuffer.append(genModel.getNonNLS(2));
    stringBuffer.append(TEXT_1216);
    }
    } else if (genOperation.hasInvocationDelegate()) { int size = genOperation.getGenParameters().size();
    stringBuffer.append(TEXT_1217);
    if (genOperation.isVoid()) {
    stringBuffer.append(TEXT_1218);
    stringBuffer.append(CodeGenUtil.upperName(genClass.getUniqueName(genOperation), genModel.getLocale()));
    stringBuffer.append(TEXT_1219);
    if (size > 0) {
    stringBuffer.append(TEXT_1220);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.util.BasicEList"));
    stringBuffer.append(TEXT_1221);
    stringBuffer.append(size);
    stringBuffer.append(TEXT_1222);
    stringBuffer.append(genOperation.getParametersArray(genClass));
    stringBuffer.append(TEXT_1223);
    } else {
    stringBuffer.append(TEXT_1224);
    }
    stringBuffer.append(TEXT_1225);
    } else {
    stringBuffer.append(TEXT_1226);
    if (!isJDK50 && genOperation.isPrimitiveType()) {
    stringBuffer.append(TEXT_1227);
    }
    stringBuffer.append(TEXT_1228);
    stringBuffer.append(genOperation.getObjectType(genClass));
    stringBuffer.append(TEXT_1229);
    stringBuffer.append(CodeGenUtil.upperName(genClass.getUniqueName(genOperation), genModel.getLocale()));
    stringBuffer.append(TEXT_1230);
    if (size > 0) {
    stringBuffer.append(TEXT_1231);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.util.BasicEList"));
    stringBuffer.append(TEXT_1232);
    stringBuffer.append(size);
    stringBuffer.append(TEXT_1233);
    stringBuffer.append(genOperation.getParametersArray(genClass));
    stringBuffer.append(TEXT_1234);
    } else {
    stringBuffer.append(TEXT_1235);
    }
    stringBuffer.append(TEXT_1236);
    if (!isJDK50 && genOperation.isPrimitiveType()) {
    stringBuffer.append(TEXT_1237);
    stringBuffer.append(genOperation.getPrimitiveValueFunction());
    stringBuffer.append(TEXT_1238);
    }
    stringBuffer.append(TEXT_1239);
    }
    stringBuffer.append(TEXT_1240);
    stringBuffer.append(genModel.getImportedName("java.lang.reflect.InvocationTargetException"));
    stringBuffer.append(TEXT_1241);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.util.WrappedException"));
    stringBuffer.append(TEXT_1242);
    } else {
    stringBuffer.append(TEXT_1243);
    //Class/implementedGenOperation.todo.override.javajetinc
    }
    stringBuffer.append(TEXT_1244);
    }
    //Class/implementedGenOperation.override.javajetinc
    }//for
    if (isImplementation && !genModel.isReflectiveDelegation() && genClass.implementsAny(genClass.getEInverseAddGenFeatures())) {
    stringBuffer.append(TEXT_1245);
    if (genModel.useGenerics()) {
    for (GenFeature genFeature : genClass.getEInverseAddGenFeatures()) {
    if (genFeature.isUncheckedCast(genClass)) {
    stringBuffer.append(TEXT_1246);
    break; }
    }
    }
    if (genModel.useClassOverrideAnnotation()) {
    stringBuffer.append(TEXT_1247);
    }
    stringBuffer.append(TEXT_1248);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.notify.NotificationChain"));
    stringBuffer.append(TEXT_1249);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.InternalEObject"));
    stringBuffer.append(TEXT_1250);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.notify.NotificationChain"));
    stringBuffer.append(TEXT_1251);
    stringBuffer.append(negativeOffsetCorrection);
    stringBuffer.append(TEXT_1252);
    for (GenFeature genFeature : genClass.getEInverseAddGenFeatures()) {
    stringBuffer.append(TEXT_1253);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(TEXT_1254);
    if (genFeature.isListType()) { String cast = "("  + genModel.getImportedName("org.eclipse.emf.ecore.util.InternalEList") + (!genModel.useGenerics() ? ")" : "<" + genModel.getImportedName("org.eclipse.emf.ecore.InternalEObject") + ">)(" + genModel.getImportedName("org.eclipse.emf.ecore.util.InternalEList") + "<?>)");
    if (genFeature.isMapType() && genFeature.isEffectiveSuppressEMFTypes()) {
    stringBuffer.append(TEXT_1255);
    stringBuffer.append(cast);
    stringBuffer.append(TEXT_1256);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.util.EMap"));
    stringBuffer.append(TEXT_1257);
    stringBuffer.append(genFeature.getImportedMapTemplateArguments(genClass));
    stringBuffer.append(TEXT_1258);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_1259);
    } else {
    stringBuffer.append(TEXT_1260);
    stringBuffer.append(cast);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_1261);
    }
    } else if (genFeature.isContainer()) {
    stringBuffer.append(TEXT_1262);
    if (genFeature.isBasicSet()) {
    stringBuffer.append(TEXT_1263);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_1264);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_1265);
    } else {
    stringBuffer.append(TEXT_1266);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(positiveOffsetCorrection);
    stringBuffer.append(TEXT_1267);
    }
    } else {
    if (genClass.getImplementingGenModel(genFeature).isVirtualDelegation()) {
    stringBuffer.append(TEXT_1268);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_1269);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_1270);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_1271);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(positiveOffsetCorrection);
    stringBuffer.append(TEXT_1272);
    } else if (genFeature.isVolatile() || genClass.getImplementingGenModel(genFeature).isDynamicDelegation()) {
    stringBuffer.append(TEXT_1273);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_1274);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_1275);
    if (genFeature.isResolveProxies()) {
    stringBuffer.append(TEXT_1276);
    stringBuffer.append(genFeature.getAccessorName());
    } else {
    stringBuffer.append(genFeature.getGetAccessor());
    }
    stringBuffer.append(TEXT_1277);
    }
    stringBuffer.append(TEXT_1278);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_1279);
    if (genFeature.isEffectiveContains()) {
    stringBuffer.append(TEXT_1280);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.InternalEObject"));
    stringBuffer.append(TEXT_1281);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_1282);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(negativeOffsetCorrection);
    stringBuffer.append(TEXT_1283);
    } else { GenFeature reverseFeature = genFeature.getReverse(); GenClass targetClass = reverseFeature.getGenClass(); String reverseOffsetCorrection = targetClass.hasOffsetCorrection() ? " + " + genClass.getOffsetCorrectionField(genFeature) : "";
    stringBuffer.append(TEXT_1284);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.InternalEObject"));
    stringBuffer.append(TEXT_1285);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_1286);
    stringBuffer.append(targetClass.getQualifiedFeatureID(reverseFeature));
    stringBuffer.append(reverseOffsetCorrection);
    stringBuffer.append(TEXT_1287);
    stringBuffer.append(targetClass.getRawImportedInterfaceName());
    stringBuffer.append(TEXT_1288);
    }
    stringBuffer.append(TEXT_1289);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_1290);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_1291);
    }
    }
    stringBuffer.append(TEXT_1292);
    if (genModel.isMinimalReflectiveMethods()) {
    stringBuffer.append(TEXT_1293);
    } else {
    stringBuffer.append(TEXT_1294);
    }
    stringBuffer.append(TEXT_1295);
    }
    if (isImplementation && !genModel.isReflectiveDelegation() && genClass.implementsAny(genClass.getEInverseRemoveGenFeatures())) {
    stringBuffer.append(TEXT_1296);
    if (genModel.useClassOverrideAnnotation()) {
    stringBuffer.append(TEXT_1297);
    }
    stringBuffer.append(TEXT_1298);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.notify.NotificationChain"));
    stringBuffer.append(TEXT_1299);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.InternalEObject"));
    stringBuffer.append(TEXT_1300);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.notify.NotificationChain"));
    stringBuffer.append(TEXT_1301);
    stringBuffer.append(negativeOffsetCorrection);
    stringBuffer.append(TEXT_1302);
    for (GenFeature genFeature : genClass.getEInverseRemoveGenFeatures()) {
    stringBuffer.append(TEXT_1303);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(TEXT_1304);
    if (genFeature.isListType()) {
    if (genFeature.isMapType() && genFeature.isEffectiveSuppressEMFTypes()) {
    stringBuffer.append(TEXT_1305);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.InternalEList"));
    stringBuffer.append(singleWildcard);
    stringBuffer.append(TEXT_1306);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.util.EMap"));
    stringBuffer.append(TEXT_1307);
    stringBuffer.append(genFeature.getImportedMapTemplateArguments(genClass));
    stringBuffer.append(TEXT_1308);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_1309);
    } else if (genFeature.isWrappedFeatureMapType()) {
    stringBuffer.append(TEXT_1310);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.InternalEList"));
    stringBuffer.append(singleWildcard);
    stringBuffer.append(TEXT_1311);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.FeatureMap"));
    stringBuffer.append(TEXT_1312);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_1313);
    } else {
    stringBuffer.append(TEXT_1314);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.InternalEList"));
    stringBuffer.append(singleWildcard);
    stringBuffer.append(TEXT_1315);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_1316);
    }
    } else if (genFeature.isContainer() && !genFeature.isBasicSet()) {
    stringBuffer.append(TEXT_1317);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(positiveOffsetCorrection);
    stringBuffer.append(TEXT_1318);
    } else if (genFeature.isUnsettable()) {
    stringBuffer.append(TEXT_1319);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_1320);
    } else {
    stringBuffer.append(TEXT_1321);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_1322);
    }
    }
    stringBuffer.append(TEXT_1323);
    if (genModel.isMinimalReflectiveMethods()) {
    stringBuffer.append(TEXT_1324);
    } else {
    stringBuffer.append(TEXT_1325);
    }
    stringBuffer.append(TEXT_1326);
    }
    if (isImplementation && !genModel.isReflectiveDelegation() && genClass.implementsAny(genClass.getEBasicRemoveFromContainerGenFeatures())) {
    stringBuffer.append(TEXT_1327);
    if (genModel.useClassOverrideAnnotation()) {
    stringBuffer.append(TEXT_1328);
    }
    stringBuffer.append(TEXT_1329);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.notify.NotificationChain"));
    stringBuffer.append(TEXT_1330);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.notify.NotificationChain"));
    stringBuffer.append(TEXT_1331);
    stringBuffer.append(negativeOffsetCorrection);
    stringBuffer.append(TEXT_1332);
    for (GenFeature genFeature : genClass.getEBasicRemoveFromContainerGenFeatures()) {
    GenFeature reverseFeature = genFeature.getReverse(); GenClass targetClass = reverseFeature.getGenClass(); String reverseOffsetCorrection = targetClass.hasOffsetCorrection() ? " + " + genClass.getOffsetCorrectionField(genFeature) : "";
    stringBuffer.append(TEXT_1333);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(TEXT_1334);
    stringBuffer.append(targetClass.getQualifiedFeatureID(reverseFeature));
    stringBuffer.append(reverseOffsetCorrection);
    stringBuffer.append(TEXT_1335);
    stringBuffer.append(targetClass.getRawImportedInterfaceName());
    stringBuffer.append(TEXT_1336);
    }
    stringBuffer.append(TEXT_1337);
    if (genModel.isMinimalReflectiveMethods()) {
    stringBuffer.append(TEXT_1338);
    } else {
    stringBuffer.append(TEXT_1339);
    }
    stringBuffer.append(TEXT_1340);
    }
    if (isImplementation && !genModel.isReflectiveDelegation() && genClass.implementsAny(genClass.getEGetGenFeatures())) {
    stringBuffer.append(TEXT_1341);
    if (genModel.useClassOverrideAnnotation()) {
    stringBuffer.append(TEXT_1342);
    }
    stringBuffer.append(TEXT_1343);
    stringBuffer.append(negativeOffsetCorrection);
    stringBuffer.append(TEXT_1344);
    for (GenFeature genFeature : genClass.getEGetGenFeatures()) {
    stringBuffer.append(TEXT_1345);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(TEXT_1346);
    if (genFeature.isPrimitiveType()) {
    if (isJDK50) {
    stringBuffer.append(TEXT_1347);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_1348);
    } else if (genFeature.isBooleanType()) {
    stringBuffer.append(TEXT_1349);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_1350);
    } else {
    stringBuffer.append(TEXT_1351);
    stringBuffer.append(genFeature.getObjectType(genClass));
    stringBuffer.append(TEXT_1352);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_1353);
    }
    } else if (genFeature.isResolveProxies() && !genFeature.isListType()) {
    stringBuffer.append(TEXT_1354);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_1355);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_1356);
    } else if (genFeature.isMapType()) {
    if (genFeature.isEffectiveSuppressEMFTypes()) {
    stringBuffer.append(TEXT_1357);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.util.EMap"));
    stringBuffer.append(TEXT_1358);
    stringBuffer.append(genFeature.getImportedMapTemplateArguments(genClass));
    stringBuffer.append(TEXT_1359);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_1360);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_1361);
    } else {
    stringBuffer.append(TEXT_1362);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_1363);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_1364);
    }
    } else if (genFeature.isWrappedFeatureMapType()) {
    stringBuffer.append(TEXT_1365);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.FeatureMap"));
    stringBuffer.append(TEXT_1366);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_1367);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_1368);
    } else if (genFeature.isFeatureMapType()) {
    stringBuffer.append(TEXT_1369);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_1370);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.FeatureMap"));
    stringBuffer.append(TEXT_1371);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_1372);
    } else {
    stringBuffer.append(TEXT_1373);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_1374);
    }
    }
    stringBuffer.append(TEXT_1375);
    if (genModel.isMinimalReflectiveMethods()) {
    stringBuffer.append(TEXT_1376);
    } else {
    stringBuffer.append(TEXT_1377);
    }
    stringBuffer.append(TEXT_1378);
    }
    if (isImplementation && !genModel.isReflectiveDelegation() && genClass.implementsAny(genClass.getESetGenFeatures())) {
    stringBuffer.append(TEXT_1379);
    if (genModel.useGenerics()) {
    for (GenFeature genFeature : genClass.getESetGenFeatures()) {
    if (genFeature.isUncheckedCast(genClass) && !genFeature.isFeatureMapType() && !genFeature.isMapType()) {
    stringBuffer.append(TEXT_1380);
    break; }
    }
    }
    if (genModel.useClassOverrideAnnotation()) {
    stringBuffer.append(TEXT_1381);
    }
    stringBuffer.append(TEXT_1382);
    stringBuffer.append(negativeOffsetCorrection);
    stringBuffer.append(TEXT_1383);
    for (GenFeature genFeature : genClass.getESetGenFeatures()) {
    stringBuffer.append(TEXT_1384);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(TEXT_1385);
    if (genFeature.isListType()) {
    if (genFeature.isWrappedFeatureMapType()) {
    stringBuffer.append(TEXT_1386);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.FeatureMap"));
    stringBuffer.append(TEXT_1387);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.FeatureMap"));
    stringBuffer.append(TEXT_1388);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_1389);
    } else if (genFeature.isFeatureMapType()) {
    stringBuffer.append(TEXT_1390);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.FeatureMap"));
    stringBuffer.append(TEXT_1391);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_1392);
    } else if (genFeature.isMapType()) {
    if (genFeature.isEffectiveSuppressEMFTypes()) {
    stringBuffer.append(TEXT_1393);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.EStructuralFeature"));
    stringBuffer.append(TEXT_1394);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.util.EMap"));
    stringBuffer.append(TEXT_1395);
    stringBuffer.append(genFeature.getImportedMapTemplateArguments(genClass));
    stringBuffer.append(TEXT_1396);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_1397);
    } else {
    stringBuffer.append(TEXT_1398);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.EStructuralFeature"));
    stringBuffer.append(TEXT_1399);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_1400);
    }
    } else {
    stringBuffer.append(TEXT_1401);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_1402);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_1403);
    stringBuffer.append(genModel.getImportedName("java.util.Collection"));
    if (isJDK50) {
    stringBuffer.append(TEXT_1404);
    stringBuffer.append(genFeature.getListItemType(genClass));
    stringBuffer.append(TEXT_1405);
    }
    stringBuffer.append(TEXT_1406);
    }
    } else if (!isJDK50 && genFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_1407);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_1408);
    stringBuffer.append(genFeature.getObjectType(genClass));
    stringBuffer.append(TEXT_1409);
    stringBuffer.append(genFeature.getPrimitiveValueFunction());
    stringBuffer.append(TEXT_1410);
    } else {
    stringBuffer.append(TEXT_1411);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_1412);
    if (genFeature.getTypeGenDataType() == null || !genFeature.getTypeGenDataType().isObjectType() || !genFeature.getRawType().equals(genFeature.getType(genClass))) {
    stringBuffer.append(TEXT_1413);
    stringBuffer.append(genFeature.getObjectType(genClass));
    stringBuffer.append(TEXT_1414);
    }
    stringBuffer.append(TEXT_1415);
    }
    stringBuffer.append(TEXT_1416);
    }
    stringBuffer.append(TEXT_1417);
    if (genModel.isMinimalReflectiveMethods()) {
    stringBuffer.append(TEXT_1418);
    } else {
    stringBuffer.append(TEXT_1419);
    }
    stringBuffer.append(TEXT_1420);
    }
    if (isImplementation && !genModel.isReflectiveDelegation() && genClass.implementsAny(genClass.getEUnsetGenFeatures())) {
    stringBuffer.append(TEXT_1421);
    if (genModel.useClassOverrideAnnotation()) {
    stringBuffer.append(TEXT_1422);
    }
    stringBuffer.append(TEXT_1423);
    stringBuffer.append(negativeOffsetCorrection);
    stringBuffer.append(TEXT_1424);
    for (GenFeature genFeature : genClass.getEUnsetGenFeatures()) {
    stringBuffer.append(TEXT_1425);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(TEXT_1426);
    if (genFeature.isListType() && !genFeature.isUnsettable()) {
    if (genFeature.isWrappedFeatureMapType()) {
    stringBuffer.append(TEXT_1427);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.FeatureMap"));
    stringBuffer.append(TEXT_1428);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_1429);
    } else {
    stringBuffer.append(TEXT_1430);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_1431);
    }
    } else if (genFeature.isUnsettable()) {
    stringBuffer.append(TEXT_1432);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_1433);
    } else if (!genFeature.hasEDefault()) {
    stringBuffer.append(TEXT_1434);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_1435);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_1436);
    } else {
    stringBuffer.append(TEXT_1437);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_1438);
    stringBuffer.append(genFeature.getEDefault());
    stringBuffer.append(TEXT_1439);
    }
    stringBuffer.append(TEXT_1440);
    }
    stringBuffer.append(TEXT_1441);
    if (genModel.isMinimalReflectiveMethods()) {
    stringBuffer.append(TEXT_1442);
    } else {
    stringBuffer.append(TEXT_1443);
    }
    stringBuffer.append(TEXT_1444);
    //Class/eUnset.override.javajetinc
    }
    if (isImplementation && !genModel.isReflectiveDelegation() && genClass.implementsAny(genClass.getEIsSetGenFeatures())) {
    stringBuffer.append(TEXT_1445);
    if (genModel.useGenerics()) {
    for (GenFeature genFeature : genClass.getEIsSetGenFeatures()) {
    if (genFeature.isListType() && !genFeature.isUnsettable() && !genFeature.isWrappedFeatureMapType() && !genClass.isField(genFeature) && genFeature.isField() && genClass.getImplementingGenModel(genFeature).isVirtualDelegation()) {
    stringBuffer.append(TEXT_1446);
    break; }
    }
    }
    if (genModel.useClassOverrideAnnotation()) {
    stringBuffer.append(TEXT_1447);
    }
    stringBuffer.append(TEXT_1448);
    stringBuffer.append(negativeOffsetCorrection);
    stringBuffer.append(TEXT_1449);
    for (GenFeature genFeature : genClass.getEIsSetGenFeatures()) { String safeNameAccessor = genFeature.getSafeName(); if ("featureID".equals(safeNameAccessor)) { safeNameAccessor = "this." + safeNameAccessor; }
    stringBuffer.append(TEXT_1450);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(TEXT_1451);
    if (genFeature.hasSettingDelegate()) {
    if (genFeature.isUnsettable()) {
    stringBuffer.append(TEXT_1452);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_1453);
    } else {
    stringBuffer.append(TEXT_1454);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_1455);
    }
    } else if (genFeature.isListType() && !genFeature.isUnsettable()) {
    if (genFeature.isWrappedFeatureMapType()) {
    if (genFeature.isVolatile()) {
    stringBuffer.append(TEXT_1456);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.FeatureMap"));
    stringBuffer.append(TEXT_1457);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_1458);
    } else {
    stringBuffer.append(TEXT_1459);
    stringBuffer.append(safeNameAccessor);
    stringBuffer.append(TEXT_1460);
    stringBuffer.append(safeNameAccessor);
    stringBuffer.append(TEXT_1461);
    }
    } else {
    if (genClass.isField(genFeature)) {
    stringBuffer.append(TEXT_1462);
    stringBuffer.append(safeNameAccessor);
    stringBuffer.append(TEXT_1463);
    stringBuffer.append(safeNameAccessor);
    stringBuffer.append(TEXT_1464);
    } else {
    if (genFeature.isField() && genClass.getImplementingGenModel(genFeature).isVirtualDelegation()) {
    stringBuffer.append(TEXT_1465);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_1466);
    stringBuffer.append(safeNameAccessor);
    stringBuffer.append(TEXT_1467);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_1468);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(positiveOffsetCorrection);
    stringBuffer.append(TEXT_1469);
    stringBuffer.append(safeNameAccessor);
    stringBuffer.append(TEXT_1470);
    stringBuffer.append(safeNameAccessor);
    stringBuffer.append(TEXT_1471);
    } else {
    stringBuffer.append(TEXT_1472);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_1473);
    }
    }
    }
    } else if (genFeature.isUnsettable()) {
    stringBuffer.append(TEXT_1474);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_1475);
    } else if (genFeature.isResolveProxies()) {
    if (genClass.isField(genFeature)) {
    stringBuffer.append(TEXT_1476);
    stringBuffer.append(safeNameAccessor);
    stringBuffer.append(TEXT_1477);
    } else {
    if (genFeature.isField() && genClass.getImplementingGenModel(genFeature).isVirtualDelegation()) {
    stringBuffer.append(TEXT_1478);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(positiveOffsetCorrection);
    stringBuffer.append(TEXT_1479);
    } else {
    stringBuffer.append(TEXT_1480);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_1481);
    }
    }
    } else if (!genFeature.hasEDefault()) {
    if (genClass.isField(genFeature)) {
    stringBuffer.append(TEXT_1482);
    stringBuffer.append(safeNameAccessor);
    stringBuffer.append(TEXT_1483);
    } else {
    if (genFeature.isField() && genClass.getImplementingGenModel(genFeature).isVirtualDelegation()) {
    stringBuffer.append(TEXT_1484);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(positiveOffsetCorrection);
    stringBuffer.append(TEXT_1485);
    } else {
    stringBuffer.append(TEXT_1486);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_1487);
    }
    }
    } else if (genFeature.isPrimitiveType() || genFeature.isEnumType()) {
    if (genClass.isField(genFeature)) {
    if (genClass.isFlag(genFeature)) {
    if (genFeature.isBooleanType()) {
    stringBuffer.append(TEXT_1488);
    stringBuffer.append(genClass.getFlagsField(genFeature));
    stringBuffer.append(TEXT_1489);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_1490);
    stringBuffer.append(genFeature.getEDefault());
    stringBuffer.append(TEXT_1491);
    } else {
    stringBuffer.append(TEXT_1492);
    stringBuffer.append(genClass.getFlagsField(genFeature));
    stringBuffer.append(TEXT_1493);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_1494);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_1495);
    }
    } else {
    stringBuffer.append(TEXT_1496);
    stringBuffer.append(safeNameAccessor);
    stringBuffer.append(TEXT_1497);
    stringBuffer.append(genFeature.getEDefault());
    stringBuffer.append(TEXT_1498);
    }
    } else {
    if (genFeature.isEnumType() && genFeature.isField() && genClass.getImplementingGenModel(genFeature).isVirtualDelegation()) {
    stringBuffer.append(TEXT_1499);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(positiveOffsetCorrection);
    stringBuffer.append(TEXT_1500);
    stringBuffer.append(genFeature.getEDefault());
    stringBuffer.append(TEXT_1501);
    stringBuffer.append(genFeature.getEDefault());
    stringBuffer.append(TEXT_1502);
    } else {
    stringBuffer.append(TEXT_1503);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_1504);
    stringBuffer.append(genFeature.getEDefault());
    stringBuffer.append(TEXT_1505);
    }
    }
    } else {//datatype
    if (genClass.isField(genFeature)) {
    stringBuffer.append(TEXT_1506);
    stringBuffer.append(genFeature.getEDefault());
    stringBuffer.append(TEXT_1507);
    stringBuffer.append(safeNameAccessor);
    stringBuffer.append(TEXT_1508);
    stringBuffer.append(genFeature.getEDefault());
    stringBuffer.append(TEXT_1509);
    stringBuffer.append(safeNameAccessor);
    stringBuffer.append(TEXT_1510);
    } else {
    if (genFeature.isField() && genClass.getImplementingGenModel(genFeature).isVirtualDelegation()) {
    stringBuffer.append(TEXT_1511);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_1512);
    stringBuffer.append(safeNameAccessor);
    stringBuffer.append(TEXT_1513);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_1514);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(positiveOffsetCorrection);
    stringBuffer.append(TEXT_1515);
    stringBuffer.append(genFeature.getEDefault());
    stringBuffer.append(TEXT_1516);
    stringBuffer.append(genFeature.getEDefault());
    stringBuffer.append(TEXT_1517);
    stringBuffer.append(safeNameAccessor);
    stringBuffer.append(TEXT_1518);
    stringBuffer.append(genFeature.getEDefault());
    stringBuffer.append(TEXT_1519);
    stringBuffer.append(safeNameAccessor);
    stringBuffer.append(TEXT_1520);
    } else {
    stringBuffer.append(TEXT_1521);
    stringBuffer.append(genFeature.getEDefault());
    stringBuffer.append(TEXT_1522);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_1523);
    stringBuffer.append(genFeature.getEDefault());
    stringBuffer.append(TEXT_1524);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_1525);
    }
    }
    }
    }
    stringBuffer.append(TEXT_1526);
    if (genModel.isMinimalReflectiveMethods()) {
    stringBuffer.append(TEXT_1527);
    } else {
    stringBuffer.append(TEXT_1528);
    }
    stringBuffer.append(TEXT_1529);
    //Class/eIsSet.override.javajetinc
    }
    if (isImplementation && (!genClass.getMixinGenFeatures().isEmpty() || genClass.hasOffsetCorrection() && !genClass.getGenFeatures().isEmpty())) {
    if (!genClass.getMixinGenFeatures().isEmpty()) {
    stringBuffer.append(TEXT_1530);
    if (genModel.useClassOverrideAnnotation()) {
    stringBuffer.append(TEXT_1531);
    }
    stringBuffer.append(TEXT_1532);
    stringBuffer.append(singleWildcard);
    stringBuffer.append(TEXT_1533);
    for (GenClass mixinGenClass : genClass.getMixinGenClasses()) {
    stringBuffer.append(TEXT_1534);
    stringBuffer.append(mixinGenClass.getRawImportedInterfaceName());
    stringBuffer.append(TEXT_1535);
    stringBuffer.append(negativeOffsetCorrection);
    stringBuffer.append(TEXT_1536);
    for (GenFeature genFeature : mixinGenClass.getGenFeatures()) {
    stringBuffer.append(TEXT_1537);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(TEXT_1538);
    stringBuffer.append(mixinGenClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(TEXT_1539);
    }
    stringBuffer.append(TEXT_1540);
    }
    stringBuffer.append(TEXT_1541);
    }
    stringBuffer.append(TEXT_1542);
    if (genModel.useClassOverrideAnnotation()) {
    stringBuffer.append(TEXT_1543);
    }
    stringBuffer.append(TEXT_1544);
    stringBuffer.append(singleWildcard);
    stringBuffer.append(TEXT_1545);
    for (GenClass mixinGenClass : genClass.getMixinGenClasses()) {
    stringBuffer.append(TEXT_1546);
    stringBuffer.append(mixinGenClass.getRawImportedInterfaceName());
    stringBuffer.append(TEXT_1547);
    for (GenFeature genFeature : mixinGenClass.getGenFeatures()) {
    stringBuffer.append(TEXT_1548);
    stringBuffer.append(mixinGenClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(TEXT_1549);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(positiveOffsetCorrection);
    stringBuffer.append(TEXT_1550);
    }
    stringBuffer.append(TEXT_1551);
    }
    if (genClass.hasOffsetCorrection() && !genClass.getGenFeatures().isEmpty()) {
    stringBuffer.append(TEXT_1552);
    stringBuffer.append(genClass.getRawImportedInterfaceName());
    stringBuffer.append(TEXT_1553);
    stringBuffer.append(negativeOffsetCorrection);
    stringBuffer.append(TEXT_1554);
    for (GenFeature genFeature : genClass.getGenFeatures()) {
    stringBuffer.append(TEXT_1555);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(TEXT_1556);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(positiveOffsetCorrection);
    stringBuffer.append(TEXT_1557);
    }
    stringBuffer.append(TEXT_1558);
    }
    stringBuffer.append(TEXT_1559);
    }
    if (genModel.isOperationReflection() && isImplementation && (!genClass.getMixinGenOperations().isEmpty() || !genClass.getOverrideGenOperations(genClass.getExtendedGenOperations(), genClass.getImplementedGenOperations()).isEmpty() || genClass.hasOffsetCorrection() && !genClass.getGenOperations().isEmpty())) {
    stringBuffer.append(TEXT_1560);
    if (genModel.useClassOverrideAnnotation()) {
    stringBuffer.append(TEXT_1561);
    }
    stringBuffer.append(TEXT_1562);
    stringBuffer.append(singleWildcard);
    stringBuffer.append(TEXT_1563);
    for (GenClass extendedGenClass : genClass.getExtendedGenClasses()) { List<GenOperation> extendedImplementedGenOperations = extendedGenClass.getImplementedGenOperations(); List<GenOperation> implementedGenOperations = genClass.getImplementedGenOperations();
    if (!genClass.getOverrideGenOperations(extendedImplementedGenOperations, implementedGenOperations).isEmpty()) {
    stringBuffer.append(TEXT_1564);
    stringBuffer.append(extendedGenClass.getRawImportedInterfaceName());
    stringBuffer.append(TEXT_1565);
    for (GenOperation genOperation : extendedImplementedGenOperations) { GenOperation overrideGenOperation = genClass.getOverrideGenOperation(genOperation);
    if (implementedGenOperations.contains(overrideGenOperation)) {
    stringBuffer.append(TEXT_1566);
    stringBuffer.append(extendedGenClass.getQualifiedOperationID(genOperation));
    stringBuffer.append(TEXT_1567);
    stringBuffer.append(genClass.getQualifiedOperationID(overrideGenOperation));
    stringBuffer.append(positiveOperationOffsetCorrection);
    stringBuffer.append(TEXT_1568);
    }
    }
    stringBuffer.append(TEXT_1569);
    }
    }
    for (GenClass mixinGenClass : genClass.getMixinGenClasses()) {
    stringBuffer.append(TEXT_1570);
    stringBuffer.append(mixinGenClass.getRawImportedInterfaceName());
    stringBuffer.append(TEXT_1571);
    for (GenOperation genOperation : mixinGenClass.getGenOperations()) { GenOperation overrideGenOperation = genClass.getOverrideGenOperation(genOperation);
    stringBuffer.append(TEXT_1572);
    stringBuffer.append(mixinGenClass.getQualifiedOperationID(genOperation));
    stringBuffer.append(TEXT_1573);
    stringBuffer.append(genClass.getQualifiedOperationID(overrideGenOperation != null ? overrideGenOperation : genOperation));
    stringBuffer.append(positiveOperationOffsetCorrection);
    stringBuffer.append(TEXT_1574);
    }
    stringBuffer.append(TEXT_1575);
    }
    if (genClass.hasOffsetCorrection() && !genClass.getGenOperations().isEmpty()) {
    stringBuffer.append(TEXT_1576);
    stringBuffer.append(genClass.getRawImportedInterfaceName());
    stringBuffer.append(TEXT_1577);
    stringBuffer.append(negativeOperationOffsetCorrection);
    stringBuffer.append(TEXT_1578);
    for (GenOperation genOperation : genClass.getGenOperations()) {
    stringBuffer.append(TEXT_1579);
    stringBuffer.append(genClass.getQualifiedOperationID(genOperation));
    stringBuffer.append(TEXT_1580);
    stringBuffer.append(genClass.getQualifiedOperationID(genOperation));
    stringBuffer.append(positiveOperationOffsetCorrection);
    stringBuffer.append(TEXT_1581);
    }
    stringBuffer.append(TEXT_1582);
    }
    stringBuffer.append(TEXT_1583);
    }
    if (isImplementation && genModel.isVirtualDelegation()) { String eVirtualValuesField = genClass.getEVirtualValuesField();
    if (eVirtualValuesField != null) {
    stringBuffer.append(TEXT_1584);
    if (genModel.useClassOverrideAnnotation()) {
    stringBuffer.append(TEXT_1585);
    }
    stringBuffer.append(TEXT_1586);
    stringBuffer.append(eVirtualValuesField);
    stringBuffer.append(TEXT_1587);
    if (genModel.useClassOverrideAnnotation()) {
    stringBuffer.append(TEXT_1588);
    }
    stringBuffer.append(TEXT_1589);
    stringBuffer.append(eVirtualValuesField);
    stringBuffer.append(TEXT_1590);
    }
    { List<String> eVirtualIndexBitFields = genClass.getEVirtualIndexBitFields(new ArrayList<String>());
    if (!eVirtualIndexBitFields.isEmpty()) { List<String> allEVirtualIndexBitFields = genClass.getAllEVirtualIndexBitFields(new ArrayList<String>());
    stringBuffer.append(TEXT_1591);
    if (genModel.useClassOverrideAnnotation()) {
    stringBuffer.append(TEXT_1592);
    }
    stringBuffer.append(TEXT_1593);
    for (int i = 0; i < allEVirtualIndexBitFields.size(); i++) {
    stringBuffer.append(TEXT_1594);
    stringBuffer.append(i);
    stringBuffer.append(TEXT_1595);
    stringBuffer.append(allEVirtualIndexBitFields.get(i));
    stringBuffer.append(TEXT_1596);
    }
    stringBuffer.append(TEXT_1597);
    if (genModel.useClassOverrideAnnotation()) {
    stringBuffer.append(TEXT_1598);
    }
    stringBuffer.append(TEXT_1599);
    for (int i = 0; i < allEVirtualIndexBitFields.size(); i++) {
    stringBuffer.append(TEXT_1600);
    stringBuffer.append(i);
    stringBuffer.append(TEXT_1601);
    stringBuffer.append(allEVirtualIndexBitFields.get(i));
    stringBuffer.append(TEXT_1602);
    }
    stringBuffer.append(TEXT_1603);
    }
    }
    }
    if (genModel.isOperationReflection() && isImplementation && !genClass.getImplementedGenOperations().isEmpty()) {
    stringBuffer.append(TEXT_1604);
    if (genModel.useClassOverrideAnnotation()) {
    stringBuffer.append(TEXT_1605);
    }
    LOOP: for (GenOperation genOperation : (genModel.isMinimalReflectiveMethods() ? genClass.getImplementedGenOperations() : genClass.getAllGenOperations())) {
    for (GenParameter genParameter : genOperation.getGenParameters()) {
    if (genParameter.isUncheckedCast()) {
    stringBuffer.append(TEXT_1606);
    break LOOP;}
    }
    }
    stringBuffer.append(TEXT_1607);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.util.EList"));
    stringBuffer.append(singleWildcard);
    stringBuffer.append(TEXT_1608);
    stringBuffer.append(genModel.getImportedName("java.lang.reflect.InvocationTargetException"));
    stringBuffer.append(TEXT_1609);
    stringBuffer.append(negativeOperationOffsetCorrection);
    stringBuffer.append(TEXT_1610);
    for (GenOperation genOperation : (genModel.isMinimalReflectiveMethods() ? genClass.getImplementedGenOperations() : genClass.getAllGenOperations())) { List<GenParameter> genParameters = genOperation.getGenParameters(); int size = genParameters.size();
    stringBuffer.append(TEXT_1611);
    stringBuffer.append(genClass.getQualifiedOperationID(genOperation));
    stringBuffer.append(TEXT_1612);
    if (genOperation.isVoid()) {
    stringBuffer.append(TEXT_1613);
    stringBuffer.append(genOperation.getName());
    stringBuffer.append(TEXT_1614);
    for (int i = 0; i < size; i++) { GenParameter genParameter = genParameters.get(i);
    if (!isJDK50 && genParameter.isPrimitiveType()) {
    stringBuffer.append(TEXT_1615);
    }
    if (genParameter.getTypeGenDataType() == null || !genParameter.getTypeGenDataType().isObjectType() || !genParameter.getRawType().equals(genParameter.getType(genClass))) {
    stringBuffer.append(TEXT_1616);
    stringBuffer.append(genParameter.getObjectType(genClass));
    stringBuffer.append(TEXT_1617);
    }
    stringBuffer.append(TEXT_1618);
    stringBuffer.append(i);
    stringBuffer.append(TEXT_1619);
    if (!isJDK50 && genParameter.isPrimitiveType()) {
    stringBuffer.append(TEXT_1620);
    stringBuffer.append(genParameter.getPrimitiveValueFunction());
    stringBuffer.append(TEXT_1621);
    }
    if (i < (size - 1)) {
    stringBuffer.append(TEXT_1622);
    }
    }
    stringBuffer.append(TEXT_1623);
    } else {
    stringBuffer.append(TEXT_1624);
    if (!isJDK50 && genOperation.isPrimitiveType()) {
    stringBuffer.append(TEXT_1625);
    stringBuffer.append(genOperation.getObjectType(genClass));
    stringBuffer.append(TEXT_1626);
    }
    stringBuffer.append(genOperation.getName());
    stringBuffer.append(TEXT_1627);
    for (int i = 0; i < size; i++) { GenParameter genParameter = genParameters.get(i);
    if (!isJDK50 && genParameter.isPrimitiveType()) {
    stringBuffer.append(TEXT_1628);
    }
    if (genParameter.getTypeGenDataType() == null || !genParameter.getTypeGenDataType().isObjectType() || !genParameter.getRawType().equals(genParameter.getType(genClass))) {
    stringBuffer.append(TEXT_1629);
    stringBuffer.append(genParameter.getObjectType(genClass));
    stringBuffer.append(TEXT_1630);
    }
    stringBuffer.append(TEXT_1631);
    stringBuffer.append(i);
    stringBuffer.append(TEXT_1632);
    if (!isJDK50 && genParameter.isPrimitiveType()) {
    stringBuffer.append(TEXT_1633);
    stringBuffer.append(genParameter.getPrimitiveValueFunction());
    stringBuffer.append(TEXT_1634);
    }
    if (i < (size - 1)) {
    stringBuffer.append(TEXT_1635);
    }
    }
    stringBuffer.append(TEXT_1636);
    if (!isJDK50 && genOperation.isPrimitiveType()) {
    stringBuffer.append(TEXT_1637);
    }
    stringBuffer.append(TEXT_1638);
    }
    }
    stringBuffer.append(TEXT_1639);
    if (genModel.isMinimalReflectiveMethods()) {
    stringBuffer.append(TEXT_1640);
    } else {
    stringBuffer.append(TEXT_1641);
    }
    stringBuffer.append(TEXT_1642);
    }
    if (!genClass.hasImplementedToStringGenOperation() && isImplementation && !genModel.isReflectiveDelegation() && !genModel.isDynamicDelegation() && !genClass.getToStringGenFeatures().isEmpty()) {
    stringBuffer.append(TEXT_1643);
    if (genModel.useClassOverrideAnnotation()) {
    stringBuffer.append(TEXT_1644);
    }
    stringBuffer.append(TEXT_1645);
    { boolean first = true;
    for (GenFeature genFeature : genClass.getToStringGenFeatures()) {
    if (first) { first = false;
    stringBuffer.append(TEXT_1646);
    stringBuffer.append(genFeature.getName());
    stringBuffer.append(TEXT_1647);
    stringBuffer.append(genModel.getNonNLS());
    } else {
    stringBuffer.append(TEXT_1648);
    stringBuffer.append(genFeature.getName());
    stringBuffer.append(TEXT_1649);
    stringBuffer.append(genModel.getNonNLS());
    }
    if (genFeature.isUnsettable() && !genFeature.isListType()) {
    if (genModel.isVirtualDelegation() && !genFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_1650);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(positiveOffsetCorrection);
    stringBuffer.append(TEXT_1651);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(positiveOffsetCorrection);
    stringBuffer.append(TEXT_1652);
    stringBuffer.append(genModel.getNonNLS());
    } else {
    if (genClass.isFlag(genFeature)) {
    if (genFeature.isBooleanType()) {
    stringBuffer.append(TEXT_1653);
    if (genClass.isESetFlag(genFeature)) {
    stringBuffer.append(TEXT_1654);
    stringBuffer.append(genClass.getESetFlagsField(genFeature));
    stringBuffer.append(TEXT_1655);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_1656);
    } else {
    stringBuffer.append(genFeature.getUncapName());
    stringBuffer.append(TEXT_1657);
    }
    stringBuffer.append(TEXT_1658);
    stringBuffer.append(genClass.getFlagsField(genFeature));
    stringBuffer.append(TEXT_1659);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_1660);
    stringBuffer.append(genModel.getNonNLS());
    } else {
    stringBuffer.append(TEXT_1661);
    if (genClass.isESetFlag(genFeature)) {
    stringBuffer.append(TEXT_1662);
    stringBuffer.append(genClass.getESetFlagsField(genFeature));
    stringBuffer.append(TEXT_1663);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_1664);
    } else {
    stringBuffer.append(genFeature.getUncapName());
    stringBuffer.append(TEXT_1665);
    }
    stringBuffer.append(TEXT_1666);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_1667);
    stringBuffer.append(genClass.getFlagsField(genFeature));
    stringBuffer.append(TEXT_1668);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_1669);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_1670);
    stringBuffer.append(genModel.getNonNLS());
    }
    } else {
    stringBuffer.append(TEXT_1671);
    if (genClass.isESetFlag(genFeature)) {
    stringBuffer.append(TEXT_1672);
    stringBuffer.append(genClass.getESetFlagsField(genFeature));
    stringBuffer.append(TEXT_1673);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_1674);
    } else {
    stringBuffer.append(genFeature.getUncapName());
    stringBuffer.append(TEXT_1675);
    }
    stringBuffer.append(TEXT_1676);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_1677);
    stringBuffer.append(genModel.getNonNLS());
    }
    }
    } else {
    if (genModel.isVirtualDelegation() && !genFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_1678);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(positiveOffsetCorrection);
    if (!genFeature.isListType() && !genFeature.isReferenceType()){
    stringBuffer.append(TEXT_1679);
    stringBuffer.append(genFeature.getEDefault());
    }
    stringBuffer.append(TEXT_1680);
    } else {
    if (genClass.isFlag(genFeature)) {
    if (genFeature.isBooleanType()) {
    stringBuffer.append(TEXT_1681);
    stringBuffer.append(genClass.getFlagsField(genFeature));
    stringBuffer.append(TEXT_1682);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_1683);
    } else {
    stringBuffer.append(TEXT_1684);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_1685);
    stringBuffer.append(genClass.getFlagsField(genFeature));
    stringBuffer.append(TEXT_1686);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_1687);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_1688);
    }
    } else {
    stringBuffer.append(TEXT_1689);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_1690);
    }
    }
    }
    }
    }
    stringBuffer.append(TEXT_1691);
    }
    if (isImplementation && genClass.isMapEntry()) { GenFeature keyFeature = genClass.getMapEntryKeyFeature(); GenFeature valueFeature = genClass.getMapEntryValueFeature();
    String objectType = genModel.getImportedName("java.lang.Object");
    String keyType = isJDK50 ? keyFeature.getObjectType(genClass) : objectType;
    String valueType = isJDK50 ? valueFeature.getObjectType(genClass) : objectType;
    String eMapType = genModel.getImportedName("org.eclipse.emf.common.util.EMap") + (isJDK50 ? "<" + keyType + ", " + valueType + ">" : "");
    stringBuffer.append(TEXT_1692);
    stringBuffer.append(objectType);
    stringBuffer.append(TEXT_1693);
    stringBuffer.append(keyType);
    stringBuffer.append(TEXT_1694);
    if (!isJDK50 && keyFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_1695);
    stringBuffer.append(keyFeature.getObjectType(genClass));
    stringBuffer.append(TEXT_1696);
    } else {
    stringBuffer.append(TEXT_1697);
    }
    stringBuffer.append(TEXT_1698);
    stringBuffer.append(keyType);
    stringBuffer.append(TEXT_1699);
    if (keyFeature.isListType()) {
    stringBuffer.append(TEXT_1700);
    if (!genModel.useGenerics()) {
    stringBuffer.append(TEXT_1701);
    stringBuffer.append(genModel.getImportedName("java.util.Collection"));
    stringBuffer.append(TEXT_1702);
    }
    stringBuffer.append(TEXT_1703);
    } else if (isJDK50) {
    stringBuffer.append(TEXT_1704);
    } else if (keyFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_1705);
    stringBuffer.append(keyFeature.getObjectType(genClass));
    stringBuffer.append(TEXT_1706);
    stringBuffer.append(keyFeature.getPrimitiveValueFunction());
    stringBuffer.append(TEXT_1707);
    } else {
    stringBuffer.append(TEXT_1708);
    stringBuffer.append(keyFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_1709);
    }
    stringBuffer.append(TEXT_1710);
    stringBuffer.append(valueType);
    stringBuffer.append(TEXT_1711);
    if (!isJDK50 && valueFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_1712);
    stringBuffer.append(valueFeature.getObjectType(genClass));
    stringBuffer.append(TEXT_1713);
    } else {
    stringBuffer.append(TEXT_1714);
    }
    stringBuffer.append(TEXT_1715);
    stringBuffer.append(valueType);
    stringBuffer.append(TEXT_1716);
    stringBuffer.append(valueType);
    stringBuffer.append(TEXT_1717);
    stringBuffer.append(valueType);
    stringBuffer.append(TEXT_1718);
    if (valueFeature.isListType()) {
    stringBuffer.append(TEXT_1719);
    if (!genModel.useGenerics()) {
    stringBuffer.append(TEXT_1720);
    stringBuffer.append(genModel.getImportedName("java.util.Collection"));
    stringBuffer.append(TEXT_1721);
    }
    stringBuffer.append(TEXT_1722);
    } else if (isJDK50) {
    stringBuffer.append(TEXT_1723);
    } else if (valueFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_1724);
    stringBuffer.append(valueFeature.getObjectType(genClass));
    stringBuffer.append(TEXT_1725);
    stringBuffer.append(valueFeature.getPrimitiveValueFunction());
    stringBuffer.append(TEXT_1726);
    } else {
    stringBuffer.append(TEXT_1727);
    stringBuffer.append(valueFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_1728);
    }
    stringBuffer.append(TEXT_1729);
    if (genModel.useGenerics()) {
    stringBuffer.append(TEXT_1730);
    }
    stringBuffer.append(TEXT_1731);
    stringBuffer.append(eMapType);
    stringBuffer.append(TEXT_1732);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.EObject"));
    stringBuffer.append(TEXT_1733);
    stringBuffer.append(eMapType);
    stringBuffer.append(TEXT_1734);
    }
    stringBuffer.append(TEXT_1735);
    stringBuffer.append(isInterface ? " " + genClass.getInterfaceName() : genClass.getClassName());
    // TODO fix the space above
    genModel.emitSortedImports();
    stringBuffer.append(TEXT_1736);
    return stringBuffer.toString();
  }
}
