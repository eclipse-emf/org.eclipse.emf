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
  protected final String TEXT_56 = NL + "\t/**" + NL + "\t * An array of objects representing the values of non-primitive features." + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */";
  protected final String TEXT_57 = NL + "\t@";
  protected final String TEXT_58 = NL + "\tprotected Object[] ";
  protected final String TEXT_59 = ";" + NL;
  protected final String TEXT_60 = NL + "\t/**" + NL + "\t * A bit field representing the indices of non-primitive feature values." + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */";
  protected final String TEXT_61 = NL + "\t@";
  protected final String TEXT_62 = NL + "\tprotected int ";
  protected final String TEXT_63 = ";" + NL;
  protected final String TEXT_64 = NL + "\t/**" + NL + "\t * A set of bit flags representing the values of boolean attributes and whether unsettable features have been set." + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t * @ordered" + NL + "\t */";
  protected final String TEXT_65 = NL + "\t@";
  protected final String TEXT_66 = NL + "\tprotected int ";
  protected final String TEXT_67 = " = 0;" + NL;
  protected final String TEXT_68 = NL + "\t/**" + NL + "\t * The cached setting delegate for the '{@link #";
  protected final String TEXT_69 = "() <em>";
  protected final String TEXT_70 = "</em>}' ";
  protected final String TEXT_71 = "." + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @see #";
  protected final String TEXT_72 = "()" + NL + "\t * @generated" + NL + "\t * @ordered" + NL + "\t */";
  protected final String TEXT_73 = NL + "\t@";
  protected final String TEXT_74 = NL + "\tprotected ";
  protected final String TEXT_75 = ".Internal.SettingDelegate ";
  protected final String TEXT_76 = "__ESETTING_DELEGATE = ((";
  protected final String TEXT_77 = ".Internal)";
  protected final String TEXT_78 = ").getSettingDelegate();" + NL;
  protected final String TEXT_79 = NL + "\t/**" + NL + "\t * The cached value of the '{@link #";
  protected final String TEXT_80 = "() <em>";
  protected final String TEXT_81 = "</em>}' ";
  protected final String TEXT_82 = "." + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @see #";
  protected final String TEXT_83 = "()" + NL + "\t * @generated" + NL + "\t * @ordered" + NL + "\t */";
  protected final String TEXT_84 = NL + "\t@";
  protected final String TEXT_85 = NL + "\tprotected ";
  protected final String TEXT_86 = " ";
  protected final String TEXT_87 = ";" + NL;
  protected final String TEXT_88 = NL + "\t/**" + NL + "\t * The empty value for the '{@link #";
  protected final String TEXT_89 = "() <em>";
  protected final String TEXT_90 = "</em>}' array accessor." + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @see #";
  protected final String TEXT_91 = "()" + NL + "\t * @generated" + NL + "\t * @ordered" + NL + "\t */";
  protected final String TEXT_92 = NL + "\t@SuppressWarnings(\"unchecked\")";
  protected final String TEXT_93 = NL + "\tprotected static final ";
  protected final String TEXT_94 = "[] ";
  protected final String TEXT_95 = "_EEMPTY_ARRAY = new ";
  protected final String TEXT_96 = " [0]";
  protected final String TEXT_97 = ";" + NL;
  protected final String TEXT_98 = NL + "\t/**" + NL + "\t * The default value of the '{@link #";
  protected final String TEXT_99 = "() <em>";
  protected final String TEXT_100 = "</em>}' ";
  protected final String TEXT_101 = "." + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @see #";
  protected final String TEXT_102 = "()" + NL + "\t * @generated" + NL + "\t * @ordered" + NL + "\t */";
  protected final String TEXT_103 = NL + "\t@SuppressWarnings(\"unchecked\")";
  protected final String TEXT_104 = NL + "\tprotected static final ";
  protected final String TEXT_105 = " ";
  protected final String TEXT_106 = "; // TODO The default value literal \"";
  protected final String TEXT_107 = "\" is not valid.";
  protected final String TEXT_108 = " = ";
  protected final String TEXT_109 = ";";
  protected final String TEXT_110 = NL;
  protected final String TEXT_111 = NL + "\t/**" + NL + "\t * An additional set of bit flags representing the values of boolean attributes and whether unsettable features have been set." + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t * @ordered" + NL + "\t */";
  protected final String TEXT_112 = NL + "\t@";
  protected final String TEXT_113 = NL + "\tprotected int ";
  protected final String TEXT_114 = " = 0;" + NL;
  protected final String TEXT_115 = NL + "\t/**" + NL + "\t * The offset of the flags representing the value of the '{@link #";
  protected final String TEXT_116 = "() <em>";
  protected final String TEXT_117 = "</em>}' ";
  protected final String TEXT_118 = "." + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t * @ordered" + NL + "\t */" + NL + "\tprotected static final int ";
  protected final String TEXT_119 = "_EFLAG_OFFSET = ";
  protected final String TEXT_120 = ";" + NL + "" + NL + "\t/**" + NL + "\t * The flags representing the default value of the '{@link #";
  protected final String TEXT_121 = "() <em>";
  protected final String TEXT_122 = "</em>}' ";
  protected final String TEXT_123 = "." + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t * @ordered" + NL + "\t */" + NL + "\tprotected static final int ";
  protected final String TEXT_124 = "_EFLAG_DEFAULT = ";
  protected final String TEXT_125 = ".ordinal()";
  protected final String TEXT_126 = ".VALUES.indexOf(";
  protected final String TEXT_127 = ")";
  protected final String TEXT_128 = " << ";
  protected final String TEXT_129 = "_EFLAG_OFFSET;" + NL + "" + NL + "\t/**" + NL + "\t * The array of enumeration values for '{@link ";
  protected final String TEXT_130 = " ";
  protected final String TEXT_131 = "}'" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t * @ordered" + NL + "\t */" + NL + "\tprivate static final ";
  protected final String TEXT_132 = "[] ";
  protected final String TEXT_133 = "_EFLAG_VALUES = ";
  protected final String TEXT_134 = ".values()";
  protected final String TEXT_135 = "(";
  protected final String TEXT_136 = "[])";
  protected final String TEXT_137 = ".VALUES.toArray(new ";
  protected final String TEXT_138 = "[";
  protected final String TEXT_139 = ".VALUES.size()])";
  protected final String TEXT_140 = ";" + NL;
  protected final String TEXT_141 = NL + "\t/**" + NL + "\t * The flag";
  protected final String TEXT_142 = " representing the value of the '{@link #";
  protected final String TEXT_143 = "() <em>";
  protected final String TEXT_144 = "</em>}' ";
  protected final String TEXT_145 = "." + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @see #";
  protected final String TEXT_146 = "()" + NL + "\t * @generated" + NL + "\t * @ordered" + NL + "\t */" + NL + "\tprotected static final int ";
  protected final String TEXT_147 = "_EFLAG = ";
  protected final String TEXT_148 = " << ";
  protected final String TEXT_149 = "_EFLAG_OFFSET";
  protected final String TEXT_150 = ";" + NL;
  protected final String TEXT_151 = NL + "\t/**" + NL + "\t * The cached value of the '{@link #";
  protected final String TEXT_152 = "() <em>";
  protected final String TEXT_153 = "</em>}' ";
  protected final String TEXT_154 = "." + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @see #";
  protected final String TEXT_155 = "()" + NL + "\t * @generated" + NL + "\t * @ordered" + NL + "\t */";
  protected final String TEXT_156 = NL + "\t@";
  protected final String TEXT_157 = NL + "\tprotected ";
  protected final String TEXT_158 = " ";
  protected final String TEXT_159 = " = ";
  protected final String TEXT_160 = ";" + NL;
  protected final String TEXT_161 = NL + "\t/**" + NL + "\t * An additional set of bit flags representing the values of boolean attributes and whether unsettable features have been set." + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t * @ordered" + NL + "\t */";
  protected final String TEXT_162 = NL + "\t@";
  protected final String TEXT_163 = NL + "\tprotected int ";
  protected final String TEXT_164 = " = 0;" + NL;
  protected final String TEXT_165 = NL + "\t/**" + NL + "\t * The flag representing whether the ";
  protected final String TEXT_166 = " ";
  protected final String TEXT_167 = " has been set." + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t * @ordered" + NL + "\t */" + NL + "\tprotected static final int ";
  protected final String TEXT_168 = "_ESETFLAG = 1 << ";
  protected final String TEXT_169 = ";" + NL;
  protected final String TEXT_170 = NL + "\t/**" + NL + "\t * This is true if the ";
  protected final String TEXT_171 = " ";
  protected final String TEXT_172 = " has been set." + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t * @ordered" + NL + "\t */";
  protected final String TEXT_173 = NL + "\t@";
  protected final String TEXT_174 = NL + "\tprotected boolean ";
  protected final String TEXT_175 = "ESet;" + NL;
  protected final String TEXT_176 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */" + NL + "\tprivate static final int ";
  protected final String TEXT_177 = " = ";
  protected final String TEXT_178 = ".getFeatureID(";
  protected final String TEXT_179 = ") - ";
  protected final String TEXT_180 = ";" + NL;
  protected final String TEXT_181 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */" + NL + "\tprivate static final int ";
  protected final String TEXT_182 = " = ";
  protected final String TEXT_183 = ".getFeatureID(";
  protected final String TEXT_184 = ") - ";
  protected final String TEXT_185 = ";" + NL;
  protected final String TEXT_186 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */" + NL + "\tprivate static final int \"EOPERATION_OFFSET_CORRECTION\" = ";
  protected final String TEXT_187 = ".getOperationID(";
  protected final String TEXT_188 = ") - ";
  protected final String TEXT_189 = ";" + NL;
  protected final String TEXT_190 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */" + NL + "\t";
  protected final String TEXT_191 = "public";
  protected final String TEXT_192 = "protected";
  protected final String TEXT_193 = " ";
  protected final String TEXT_194 = "()" + NL + "\t{" + NL + "\t\tsuper();";
  protected final String TEXT_195 = NL + "\t\t";
  protected final String TEXT_196 = " |= ";
  protected final String TEXT_197 = "_EFLAG";
  protected final String TEXT_198 = "_DEFAULT";
  protected final String TEXT_199 = ";";
  protected final String TEXT_200 = NL + "\t}" + NL + "" + NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */";
  protected final String TEXT_201 = NL + "\t@Override";
  protected final String TEXT_202 = NL + "\tprotected ";
  protected final String TEXT_203 = " eStaticClass()" + NL + "\t{" + NL + "\t\treturn ";
  protected final String TEXT_204 = ";" + NL + "\t}" + NL;
  protected final String TEXT_205 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */";
  protected final String TEXT_206 = NL + "\t@Override";
  protected final String TEXT_207 = NL + "\tprotected int eStaticFeatureCount()" + NL + "\t{" + NL + "\t\treturn ";
  protected final String TEXT_208 = ";" + NL + "\t}" + NL;
  protected final String TEXT_209 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */";
  protected final String TEXT_210 = NL + "\t";
  protected final String TEXT_211 = "[] ";
  protected final String TEXT_212 = "();" + NL;
  protected final String TEXT_213 = NL + "\tpublic ";
  protected final String TEXT_214 = "[] ";
  protected final String TEXT_215 = "()" + NL + "\t{";
  protected final String TEXT_216 = NL + "\t\t";
  protected final String TEXT_217 = " list = (";
  protected final String TEXT_218 = ")";
  protected final String TEXT_219 = "();" + NL + "\t\tif (list.isEmpty()) return ";
  protected final String TEXT_220 = "(";
  protected final String TEXT_221 = "[])";
  protected final String TEXT_222 = "_EEMPTY_ARRAY;";
  protected final String TEXT_223 = NL + "\t\tif (";
  protected final String TEXT_224 = " == null || ";
  protected final String TEXT_225 = ".isEmpty()) return ";
  protected final String TEXT_226 = "(";
  protected final String TEXT_227 = "[])";
  protected final String TEXT_228 = "_EEMPTY_ARRAY;" + NL + "\t\t";
  protected final String TEXT_229 = " list = (";
  protected final String TEXT_230 = ")";
  protected final String TEXT_231 = ";";
  protected final String TEXT_232 = NL + "\t\tlist.shrink();" + NL + "\t\treturn (";
  protected final String TEXT_233 = "[])list.data();" + NL + "\t}" + NL;
  protected final String TEXT_234 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */";
  protected final String TEXT_235 = NL + "\t";
  protected final String TEXT_236 = " get";
  protected final String TEXT_237 = "(int index);" + NL;
  protected final String TEXT_238 = NL + "\tpublic ";
  protected final String TEXT_239 = " get";
  protected final String TEXT_240 = "(int index)" + NL + "\t{" + NL + "\t\treturn ";
  protected final String TEXT_241 = "(";
  protected final String TEXT_242 = ")";
  protected final String TEXT_243 = "().get(index);" + NL + "\t}" + NL;
  protected final String TEXT_244 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */";
  protected final String TEXT_245 = NL + "\tint get";
  protected final String TEXT_246 = "Length();" + NL;
  protected final String TEXT_247 = NL + "\tpublic int get";
  protected final String TEXT_248 = "Length()" + NL + "\t{";
  protected final String TEXT_249 = NL + "\t\treturn ";
  protected final String TEXT_250 = "().size();";
  protected final String TEXT_251 = NL + "\t\treturn ";
  protected final String TEXT_252 = " == null ? 0 : ";
  protected final String TEXT_253 = ".size();";
  protected final String TEXT_254 = NL + "\t}" + NL;
  protected final String TEXT_255 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */";
  protected final String TEXT_256 = NL + "\tvoid set";
  protected final String TEXT_257 = "(";
  protected final String TEXT_258 = "[] new";
  protected final String TEXT_259 = ");" + NL;
  protected final String TEXT_260 = NL + "\tpublic void set";
  protected final String TEXT_261 = "(";
  protected final String TEXT_262 = "[] new";
  protected final String TEXT_263 = ")" + NL + "\t{" + NL + "\t\t((";
  protected final String TEXT_264 = ")";
  protected final String TEXT_265 = "()).setData(new";
  protected final String TEXT_266 = ".length, new";
  protected final String TEXT_267 = ");" + NL + "\t}" + NL;
  protected final String TEXT_268 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */";
  protected final String TEXT_269 = NL + "\tvoid set";
  protected final String TEXT_270 = "(int index, ";
  protected final String TEXT_271 = " element);" + NL;
  protected final String TEXT_272 = NL + "\tpublic void set";
  protected final String TEXT_273 = "(int index, ";
  protected final String TEXT_274 = " element)" + NL + "\t{" + NL + "\t\t";
  protected final String TEXT_275 = "().set(index, element);" + NL + "\t}" + NL;
  protected final String TEXT_276 = NL + "\t/**" + NL + "\t * Returns the value of the '<em><b>";
  protected final String TEXT_277 = "</b></em>' ";
  protected final String TEXT_278 = ".";
  protected final String TEXT_279 = NL + "\t * The key is of type ";
  protected final String TEXT_280 = "list of {@link ";
  protected final String TEXT_281 = "}";
  protected final String TEXT_282 = "{@link ";
  protected final String TEXT_283 = "}";
  protected final String TEXT_284 = "," + NL + "\t * and the value is of type ";
  protected final String TEXT_285 = "list of {@link ";
  protected final String TEXT_286 = "}";
  protected final String TEXT_287 = "{@link ";
  protected final String TEXT_288 = "}";
  protected final String TEXT_289 = ",";
  protected final String TEXT_290 = NL + "\t * The list contents are of type {@link ";
  protected final String TEXT_291 = "}";
  protected final String TEXT_292 = ".";
  protected final String TEXT_293 = NL + "\t * The default value is <code>";
  protected final String TEXT_294 = "</code>.";
  protected final String TEXT_295 = NL + "\t * The literals are from the enumeration {@link ";
  protected final String TEXT_296 = "}.";
  protected final String TEXT_297 = NL + "\t * It is bidirectional and its opposite is '{@link ";
  protected final String TEXT_298 = "#";
  protected final String TEXT_299 = " <em>";
  protected final String TEXT_300 = "</em>}'.";
  protected final String TEXT_301 = NL + "\t * <!-- begin-user-doc -->";
  protected final String TEXT_302 = NL + "\t * <p>" + NL + "\t * If the meaning of the '<em>";
  protected final String TEXT_303 = "</em>' ";
  protected final String TEXT_304 = " isn't clear," + NL + "\t * there really should be more of a description here..." + NL + "\t * </p>";
  protected final String TEXT_305 = NL + "\t * <!-- end-user-doc -->";
  protected final String TEXT_306 = NL + "\t * <!-- begin-model-doc -->" + NL + "\t * ";
  protected final String TEXT_307 = NL + "\t * <!-- end-model-doc -->";
  protected final String TEXT_308 = NL + "\t * @return the value of the '<em>";
  protected final String TEXT_309 = "</em>' ";
  protected final String TEXT_310 = ".";
  protected final String TEXT_311 = NL + "\t * @see ";
  protected final String TEXT_312 = NL + "\t * @see #isSet";
  protected final String TEXT_313 = "()";
  protected final String TEXT_314 = NL + "\t * @see #unset";
  protected final String TEXT_315 = "()";
  protected final String TEXT_316 = NL + "\t * @see #set";
  protected final String TEXT_317 = "(";
  protected final String TEXT_318 = ")";
  protected final String TEXT_319 = NL + "\t * @see ";
  protected final String TEXT_320 = "#get";
  protected final String TEXT_321 = "()";
  protected final String TEXT_322 = NL + "\t * @see ";
  protected final String TEXT_323 = "#";
  protected final String TEXT_324 = NL + "\t * @model ";
  protected final String TEXT_325 = NL + "\t *        ";
  protected final String TEXT_326 = NL + "\t * @model";
  protected final String TEXT_327 = NL + "\t * @generated" + NL + "\t */";
  protected final String TEXT_328 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */";
  protected final String TEXT_329 = NL + "\t";
  protected final String TEXT_330 = " ";
  protected final String TEXT_331 = "();" + NL;
  protected final String TEXT_332 = NL + "\t@SuppressWarnings(\"unchecked\")";
  protected final String TEXT_333 = NL + "\tpublic ";
  protected final String TEXT_334 = " ";
  protected final String TEXT_335 = "_";
  protected final String TEXT_336 = "()" + NL + "\t{";
  protected final String TEXT_337 = NL + "\t\treturn ";
  protected final String TEXT_338 = "(";
  protected final String TEXT_339 = "(";
  protected final String TEXT_340 = ")eDynamicGet(";
  protected final String TEXT_341 = ", ";
  protected final String TEXT_342 = ", true, ";
  protected final String TEXT_343 = ")";
  protected final String TEXT_344 = ").";
  protected final String TEXT_345 = "()";
  protected final String TEXT_346 = ";";
  protected final String TEXT_347 = NL + "\t\treturn ";
  protected final String TEXT_348 = "(";
  protected final String TEXT_349 = "(";
  protected final String TEXT_350 = ")eGet(";
  protected final String TEXT_351 = ", true)";
  protected final String TEXT_352 = ").";
  protected final String TEXT_353 = "()";
  protected final String TEXT_354 = ";";
  protected final String TEXT_355 = NL + "\t\treturn ";
  protected final String TEXT_356 = "(";
  protected final String TEXT_357 = "(";
  protected final String TEXT_358 = ")";
  protected final String TEXT_359 = "__ESETTING_DELEGATE.dynamicGet(this, null, 0, true, false)";
  protected final String TEXT_360 = ").";
  protected final String TEXT_361 = "()";
  protected final String TEXT_362 = ";";
  protected final String TEXT_363 = NL + "\t\t";
  protected final String TEXT_364 = " ";
  protected final String TEXT_365 = " = (";
  protected final String TEXT_366 = ")eVirtualGet(";
  protected final String TEXT_367 = ");";
  protected final String TEXT_368 = NL + "\t\tif (";
  protected final String TEXT_369 = " == null)" + NL + "\t\t{";
  protected final String TEXT_370 = NL + "\t\t\teVirtualSet(";
  protected final String TEXT_371 = ", ";
  protected final String TEXT_372 = " = new ";
  protected final String TEXT_373 = ");";
  protected final String TEXT_374 = NL + "\t\t\t";
  protected final String TEXT_375 = " = new ";
  protected final String TEXT_376 = ";";
  protected final String TEXT_377 = NL + "\t\t}" + NL + "\t\treturn ";
  protected final String TEXT_378 = ";";
  protected final String TEXT_379 = NL + "\t\tif (eContainerFeatureID() != ";
  protected final String TEXT_380 = ") return null;" + NL + "\t\treturn (";
  protected final String TEXT_381 = ")eContainer();";
  protected final String TEXT_382 = NL + "\t\t";
  protected final String TEXT_383 = " ";
  protected final String TEXT_384 = " = (";
  protected final String TEXT_385 = ")eVirtualGet(";
  protected final String TEXT_386 = ", ";
  protected final String TEXT_387 = ");";
  protected final String TEXT_388 = NL + "\t\tif (";
  protected final String TEXT_389 = " != null && ";
  protected final String TEXT_390 = ".eIsProxy())" + NL + "\t\t{" + NL + "\t\t\t";
  protected final String TEXT_391 = " old";
  protected final String TEXT_392 = " = (";
  protected final String TEXT_393 = ")";
  protected final String TEXT_394 = ";" + NL + "\t\t\t";
  protected final String TEXT_395 = " = ";
  protected final String TEXT_396 = "eResolveProxy(old";
  protected final String TEXT_397 = ");" + NL + "\t\t\tif (";
  protected final String TEXT_398 = " != old";
  protected final String TEXT_399 = ")" + NL + "\t\t\t{";
  protected final String TEXT_400 = NL + "\t\t\t\t";
  protected final String TEXT_401 = " new";
  protected final String TEXT_402 = " = (";
  protected final String TEXT_403 = ")";
  protected final String TEXT_404 = ";";
  protected final String TEXT_405 = NL + "\t\t\t\t";
  protected final String TEXT_406 = " msgs = old";
  protected final String TEXT_407 = ".eInverseRemove(this, EOPPOSITE_FEATURE_BASE - ";
  protected final String TEXT_408 = ", null, null);";
  protected final String TEXT_409 = NL + "\t\t\t\t";
  protected final String TEXT_410 = " msgs =  old";
  protected final String TEXT_411 = ".eInverseRemove(this, ";
  protected final String TEXT_412 = ", ";
  protected final String TEXT_413 = ".class, null);";
  protected final String TEXT_414 = NL + "\t\t\t\tif (new";
  protected final String TEXT_415 = ".eInternalContainer() == null)" + NL + "\t\t\t\t{";
  protected final String TEXT_416 = NL + "\t\t\t\t\tmsgs = new";
  protected final String TEXT_417 = ".eInverseAdd(this, EOPPOSITE_FEATURE_BASE - ";
  protected final String TEXT_418 = ", null, msgs);";
  protected final String TEXT_419 = NL + "\t\t\t\t\tmsgs =  new";
  protected final String TEXT_420 = ".eInverseAdd(this, ";
  protected final String TEXT_421 = ", ";
  protected final String TEXT_422 = ".class, msgs);";
  protected final String TEXT_423 = NL + "\t\t\t\t}" + NL + "\t\t\t\tif (msgs != null) msgs.dispatch();";
  protected final String TEXT_424 = NL + "\t\t\t\teVirtualSet(";
  protected final String TEXT_425 = ", ";
  protected final String TEXT_426 = ");";
  protected final String TEXT_427 = NL + "\t\t\t\tif (eNotificationRequired())" + NL + "\t\t\t\t\teNotify(new ";
  protected final String TEXT_428 = "(this, ";
  protected final String TEXT_429 = ".RESOLVE, ";
  protected final String TEXT_430 = ", old";
  protected final String TEXT_431 = ", ";
  protected final String TEXT_432 = "));";
  protected final String TEXT_433 = NL + "\t\t\t}" + NL + "\t\t}";
  protected final String TEXT_434 = NL + "\t\treturn (";
  protected final String TEXT_435 = ")eVirtualGet(";
  protected final String TEXT_436 = ", ";
  protected final String TEXT_437 = ");";
  protected final String TEXT_438 = NL + "\t\treturn (";
  protected final String TEXT_439 = " & ";
  protected final String TEXT_440 = "_EFLAG) != 0;";
  protected final String TEXT_441 = NL + "\t\treturn ";
  protected final String TEXT_442 = "_EFLAG_VALUES[(";
  protected final String TEXT_443 = " & ";
  protected final String TEXT_444 = "_EFLAG) >>> ";
  protected final String TEXT_445 = "_EFLAG_OFFSET];";
  protected final String TEXT_446 = NL + "\t\treturn ";
  protected final String TEXT_447 = ";";
  protected final String TEXT_448 = NL + "\t\t";
  protected final String TEXT_449 = " ";
  protected final String TEXT_450 = " = basicGet";
  protected final String TEXT_451 = "();" + NL + "\t\treturn ";
  protected final String TEXT_452 = " != null && ";
  protected final String TEXT_453 = ".eIsProxy() ? ";
  protected final String TEXT_454 = "eResolveProxy((";
  protected final String TEXT_455 = ")";
  protected final String TEXT_456 = ") : ";
  protected final String TEXT_457 = ";";
  protected final String TEXT_458 = NL + "\t\treturn new ";
  protected final String TEXT_459 = "((";
  protected final String TEXT_460 = ".Internal)((";
  protected final String TEXT_461 = ".Internal.Wrapper)get";
  protected final String TEXT_462 = "()).featureMap().";
  protected final String TEXT_463 = "list(";
  protected final String TEXT_464 = "));";
  protected final String TEXT_465 = NL + "\t\treturn (";
  protected final String TEXT_466 = ")get";
  protected final String TEXT_467 = "().";
  protected final String TEXT_468 = "list(";
  protected final String TEXT_469 = ");";
  protected final String TEXT_470 = NL + "\t\treturn ((";
  protected final String TEXT_471 = ".Internal.Wrapper)get";
  protected final String TEXT_472 = "()).featureMap().list(";
  protected final String TEXT_473 = ");";
  protected final String TEXT_474 = NL + "\t\treturn get";
  protected final String TEXT_475 = "().list(";
  protected final String TEXT_476 = ");";
  protected final String TEXT_477 = NL + "\t\treturn ";
  protected final String TEXT_478 = "(";
  protected final String TEXT_479 = "(";
  protected final String TEXT_480 = ")";
  protected final String TEXT_481 = "((";
  protected final String TEXT_482 = ".Internal.Wrapper)get";
  protected final String TEXT_483 = "()).featureMap().get(";
  protected final String TEXT_484 = ", true)";
  protected final String TEXT_485 = ").";
  protected final String TEXT_486 = "()";
  protected final String TEXT_487 = ";";
  protected final String TEXT_488 = NL + "\t\treturn ";
  protected final String TEXT_489 = "(";
  protected final String TEXT_490 = "(";
  protected final String TEXT_491 = ")";
  protected final String TEXT_492 = "get";
  protected final String TEXT_493 = "().get(";
  protected final String TEXT_494 = ", true)";
  protected final String TEXT_495 = ").";
  protected final String TEXT_496 = "()";
  protected final String TEXT_497 = ";";
  protected final String TEXT_498 = NL + "\t\t";
  protected final String TEXT_499 = NL + "\t\t// TODO: implement this method to return the '";
  protected final String TEXT_500 = "' ";
  protected final String TEXT_501 = NL + "\t\t// Ensure that you remove @generated or mark it @generated NOT";
  protected final String TEXT_502 = NL + "\t\t// The list is expected to implement org.eclipse.emf.ecore.util.InternalEList and org.eclipse.emf.ecore.EStructuralFeature.Setting" + NL + "\t\t// so it's likely that an appropriate subclass of org.eclipse.emf.ecore.util.";
  protected final String TEXT_503 = "EcoreEMap";
  protected final String TEXT_504 = "BasicFeatureMap";
  protected final String TEXT_505 = "EcoreEList";
  protected final String TEXT_506 = " should be used.";
  protected final String TEXT_507 = NL + "\t\tthrow new UnsupportedOperationException();";
  protected final String TEXT_508 = NL + "\t}" + NL;
  protected final String TEXT_509 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */";
  protected final String TEXT_510 = NL + "\tpublic ";
  protected final String TEXT_511 = " basicGet";
  protected final String TEXT_512 = "()" + NL + "\t{";
  protected final String TEXT_513 = NL + "\t\treturn (";
  protected final String TEXT_514 = ")eDynamicGet(";
  protected final String TEXT_515 = ", ";
  protected final String TEXT_516 = ", false, ";
  protected final String TEXT_517 = ");";
  protected final String TEXT_518 = NL + "\t\treturn ";
  protected final String TEXT_519 = "(";
  protected final String TEXT_520 = "(";
  protected final String TEXT_521 = ")";
  protected final String TEXT_522 = "__ESETTING_DELEGATE.dynamicGet(this, null, 0, false, false)";
  protected final String TEXT_523 = ").";
  protected final String TEXT_524 = "()";
  protected final String TEXT_525 = ";";
  protected final String TEXT_526 = NL + "\t\tif (eContainerFeatureID() != ";
  protected final String TEXT_527 = ") return null;" + NL + "\t\treturn (";
  protected final String TEXT_528 = ")eInternalContainer();";
  protected final String TEXT_529 = NL + "\t\treturn (";
  protected final String TEXT_530 = ")eVirtualGet(";
  protected final String TEXT_531 = ");";
  protected final String TEXT_532 = NL + "\t\treturn ";
  protected final String TEXT_533 = ";";
  protected final String TEXT_534 = NL + "\t\treturn (";
  protected final String TEXT_535 = ")((";
  protected final String TEXT_536 = ".Internal.Wrapper)get";
  protected final String TEXT_537 = "()).featureMap().get(";
  protected final String TEXT_538 = ", false);";
  protected final String TEXT_539 = NL + "\t\treturn (";
  protected final String TEXT_540 = ")get";
  protected final String TEXT_541 = "().get(";
  protected final String TEXT_542 = ", false);";
  protected final String TEXT_543 = NL + "\t\t// TODO: implement this method to return the '";
  protected final String TEXT_544 = "' ";
  protected final String TEXT_545 = NL + "\t\t// -> do not perform proxy resolution" + NL + "\t\t// Ensure that you remove @generated or mark it @generated NOT" + NL + "\t\tthrow new UnsupportedOperationException();";
  protected final String TEXT_546 = NL + "\t}" + NL;
  protected final String TEXT_547 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */";
  protected final String TEXT_548 = NL + "\tpublic ";
  protected final String TEXT_549 = " basicSet";
  protected final String TEXT_550 = "(";
  protected final String TEXT_551 = " new";
  protected final String TEXT_552 = ", ";
  protected final String TEXT_553 = " msgs)" + NL + "\t{";
  protected final String TEXT_554 = NL + "\t\tmsgs = eBasicSetContainer((";
  protected final String TEXT_555 = ")new";
  protected final String TEXT_556 = ", ";
  protected final String TEXT_557 = ", msgs);";
  protected final String TEXT_558 = NL + "\t\treturn msgs;";
  protected final String TEXT_559 = NL + "\t\tmsgs = eDynamicInverseAdd((";
  protected final String TEXT_560 = ")new";
  protected final String TEXT_561 = ", ";
  protected final String TEXT_562 = ", msgs);";
  protected final String TEXT_563 = NL + "\t\treturn msgs;";
  protected final String TEXT_564 = NL + "\t\tObject old";
  protected final String TEXT_565 = " = eVirtualSet(";
  protected final String TEXT_566 = ", new";
  protected final String TEXT_567 = ");";
  protected final String TEXT_568 = NL + "\t\t";
  protected final String TEXT_569 = " old";
  protected final String TEXT_570 = " = ";
  protected final String TEXT_571 = ";" + NL + "\t\t";
  protected final String TEXT_572 = " = new";
  protected final String TEXT_573 = ";";
  protected final String TEXT_574 = NL + "\t\tboolean isSetChange = old";
  protected final String TEXT_575 = " == EVIRTUAL_NO_VALUE;";
  protected final String TEXT_576 = NL + "\t\tboolean old";
  protected final String TEXT_577 = "ESet = (";
  protected final String TEXT_578 = " & ";
  protected final String TEXT_579 = "_ESETFLAG) != 0;";
  protected final String TEXT_580 = NL + "\t\t";
  protected final String TEXT_581 = " |= ";
  protected final String TEXT_582 = "_ESETFLAG;";
  protected final String TEXT_583 = NL + "\t\tboolean old";
  protected final String TEXT_584 = "ESet = ";
  protected final String TEXT_585 = "ESet;";
  protected final String TEXT_586 = NL + "\t\t";
  protected final String TEXT_587 = "ESet = true;";
  protected final String TEXT_588 = NL + "\t\tif (eNotificationRequired())" + NL + "\t\t{";
  protected final String TEXT_589 = NL + "\t\t\t";
  protected final String TEXT_590 = " notification = new ";
  protected final String TEXT_591 = "(this, ";
  protected final String TEXT_592 = ".SET, ";
  protected final String TEXT_593 = ", ";
  protected final String TEXT_594 = "isSetChange ? null : old";
  protected final String TEXT_595 = "old";
  protected final String TEXT_596 = ", new";
  protected final String TEXT_597 = ", ";
  protected final String TEXT_598 = "isSetChange";
  protected final String TEXT_599 = "!old";
  protected final String TEXT_600 = "ESet";
  protected final String TEXT_601 = ");";
  protected final String TEXT_602 = NL + "\t\t\t";
  protected final String TEXT_603 = " notification = new ";
  protected final String TEXT_604 = "(this, ";
  protected final String TEXT_605 = ".SET, ";
  protected final String TEXT_606 = ", ";
  protected final String TEXT_607 = "old";
  protected final String TEXT_608 = " == EVIRTUAL_NO_VALUE ? null : old";
  protected final String TEXT_609 = "old";
  protected final String TEXT_610 = ", new";
  protected final String TEXT_611 = ");";
  protected final String TEXT_612 = NL + "\t\t\tif (msgs == null) msgs = notification; else msgs.add(notification);" + NL + "\t\t}";
  protected final String TEXT_613 = NL + "\t\treturn msgs;";
  protected final String TEXT_614 = NL + "\t\treturn ((";
  protected final String TEXT_615 = ".Internal)((";
  protected final String TEXT_616 = ".Internal.Wrapper)get";
  protected final String TEXT_617 = "()).featureMap()).basicAdd(";
  protected final String TEXT_618 = ", new";
  protected final String TEXT_619 = ", msgs);";
  protected final String TEXT_620 = NL + "\t\treturn ((";
  protected final String TEXT_621 = ".Internal)get";
  protected final String TEXT_622 = "()).basicAdd(";
  protected final String TEXT_623 = ", new";
  protected final String TEXT_624 = ", msgs);";
  protected final String TEXT_625 = NL + "\t\t// TODO: implement this method to set the contained '";
  protected final String TEXT_626 = "' ";
  protected final String TEXT_627 = NL + "\t\t// -> this method is automatically invoked to keep the containment relationship in synch" + NL + "\t\t// -> do not modify other features" + NL + "\t\t// -> return msgs, after adding any generated Notification to it (if it is null, a NotificationChain object must be created first)" + NL + "\t\t// Ensure that you remove @generated or mark it @generated NOT" + NL + "\t\tthrow new UnsupportedOperationException();";
  protected final String TEXT_628 = NL + "\t}" + NL;
  protected final String TEXT_629 = NL + "\t/**" + NL + "\t * Sets the value of the '{@link ";
  protected final String TEXT_630 = "#";
  protected final String TEXT_631 = " <em>";
  protected final String TEXT_632 = "</em>}' ";
  protected final String TEXT_633 = ".";
  protected final String TEXT_634 = NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @param value the new value of the '<em>";
  protected final String TEXT_635 = "</em>' ";
  protected final String TEXT_636 = ".";
  protected final String TEXT_637 = NL + "\t * @see ";
  protected final String TEXT_638 = NL + "\t * @see #isSet";
  protected final String TEXT_639 = "()";
  protected final String TEXT_640 = NL + "\t * @see #unset";
  protected final String TEXT_641 = "()";
  protected final String TEXT_642 = NL + "\t * @see #";
  protected final String TEXT_643 = "()" + NL + "\t * @generated" + NL + "\t */";
  protected final String TEXT_644 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */";
  protected final String TEXT_645 = NL + "\tvoid set";
  protected final String TEXT_646 = "(";
  protected final String TEXT_647 = " value);" + NL;
  protected final String TEXT_648 = NL + "\tpublic void set";
  protected final String TEXT_649 = "_";
  protected final String TEXT_650 = "(";
  protected final String TEXT_651 = " ";
  protected final String TEXT_652 = ")" + NL + "\t{";
  protected final String TEXT_653 = NL + "\t\teDynamicSet(";
  protected final String TEXT_654 = ", ";
  protected final String TEXT_655 = ", ";
  protected final String TEXT_656 = "new ";
  protected final String TEXT_657 = "(";
  protected final String TEXT_658 = "new";
  protected final String TEXT_659 = ")";
  protected final String TEXT_660 = ");";
  protected final String TEXT_661 = NL + "\t\teSet(";
  protected final String TEXT_662 = ", ";
  protected final String TEXT_663 = "new ";
  protected final String TEXT_664 = "(";
  protected final String TEXT_665 = "new";
  protected final String TEXT_666 = ")";
  protected final String TEXT_667 = ");";
  protected final String TEXT_668 = NL + "\t\t";
  protected final String TEXT_669 = "__ESETTING_DELEGATE.dynamicSet(this, null, 0, ";
  protected final String TEXT_670 = "new ";
  protected final String TEXT_671 = "(";
  protected final String TEXT_672 = "new";
  protected final String TEXT_673 = ")";
  protected final String TEXT_674 = ");";
  protected final String TEXT_675 = NL + "\t\tif (new";
  protected final String TEXT_676 = " != eInternalContainer() || (eContainerFeatureID() != ";
  protected final String TEXT_677 = " && new";
  protected final String TEXT_678 = " != null))" + NL + "\t\t{" + NL + "\t\t\tif (";
  protected final String TEXT_679 = ".isAncestor(this, ";
  protected final String TEXT_680 = "new";
  protected final String TEXT_681 = "))" + NL + "\t\t\t\tthrow new ";
  protected final String TEXT_682 = "(\"Recursive containment not allowed for \" + toString());";
  protected final String TEXT_683 = NL + "\t\t\t";
  protected final String TEXT_684 = " msgs = null;" + NL + "\t\t\tif (eInternalContainer() != null)" + NL + "\t\t\t\tmsgs = eBasicRemoveFromContainer(msgs);" + NL + "\t\t\tif (new";
  protected final String TEXT_685 = " != null)" + NL + "\t\t\t\tmsgs = ((";
  protected final String TEXT_686 = ")new";
  protected final String TEXT_687 = ").eInverseAdd(this, ";
  protected final String TEXT_688 = ", ";
  protected final String TEXT_689 = ".class, msgs);" + NL + "\t\t\tmsgs = basicSet";
  protected final String TEXT_690 = "(";
  protected final String TEXT_691 = "new";
  protected final String TEXT_692 = ", msgs);" + NL + "\t\t\tif (msgs != null) msgs.dispatch();" + NL + "\t\t}";
  protected final String TEXT_693 = NL + "\t\telse if (eNotificationRequired())" + NL + "\t\t\teNotify(new ";
  protected final String TEXT_694 = "(this, ";
  protected final String TEXT_695 = ".SET, ";
  protected final String TEXT_696 = ", new";
  protected final String TEXT_697 = ", new";
  protected final String TEXT_698 = "));";
  protected final String TEXT_699 = NL + "\t\t";
  protected final String TEXT_700 = " ";
  protected final String TEXT_701 = " = (";
  protected final String TEXT_702 = ")eVirtualGet(";
  protected final String TEXT_703 = ");";
  protected final String TEXT_704 = NL + "\t\tif (new";
  protected final String TEXT_705 = " != ";
  protected final String TEXT_706 = ")" + NL + "\t\t{" + NL + "\t\t\t";
  protected final String TEXT_707 = " msgs = null;" + NL + "\t\t\tif (";
  protected final String TEXT_708 = " != null)";
  protected final String TEXT_709 = NL + "\t\t\t\tmsgs = ((";
  protected final String TEXT_710 = ")";
  protected final String TEXT_711 = ").eInverseRemove(this, EOPPOSITE_FEATURE_BASE - ";
  protected final String TEXT_712 = ", null, msgs);" + NL + "\t\t\tif (new";
  protected final String TEXT_713 = " != null)" + NL + "\t\t\t\tmsgs = ((";
  protected final String TEXT_714 = ")new";
  protected final String TEXT_715 = ").eInverseAdd(this, EOPPOSITE_FEATURE_BASE - ";
  protected final String TEXT_716 = ", null, msgs);";
  protected final String TEXT_717 = NL + "\t\t\t\tmsgs = ((";
  protected final String TEXT_718 = ")";
  protected final String TEXT_719 = ").eInverseRemove(this, ";
  protected final String TEXT_720 = ", ";
  protected final String TEXT_721 = ".class, msgs);" + NL + "\t\t\tif (new";
  protected final String TEXT_722 = " != null)" + NL + "\t\t\t\tmsgs = ((";
  protected final String TEXT_723 = ")new";
  protected final String TEXT_724 = ").eInverseAdd(this, ";
  protected final String TEXT_725 = ", ";
  protected final String TEXT_726 = ".class, msgs);";
  protected final String TEXT_727 = NL + "\t\t\tmsgs = basicSet";
  protected final String TEXT_728 = "(";
  protected final String TEXT_729 = "new";
  protected final String TEXT_730 = ", msgs);" + NL + "\t\t\tif (msgs != null) msgs.dispatch();" + NL + "\t\t}";
  protected final String TEXT_731 = NL + "\t\telse" + NL + "\t\t{";
  protected final String TEXT_732 = NL + "\t\t\tboolean old";
  protected final String TEXT_733 = "ESet = eVirtualIsSet(";
  protected final String TEXT_734 = ");";
  protected final String TEXT_735 = NL + "\t\t\tboolean old";
  protected final String TEXT_736 = "ESet = (";
  protected final String TEXT_737 = " & ";
  protected final String TEXT_738 = "_ESETFLAG) != 0;";
  protected final String TEXT_739 = NL + "\t\t\t";
  protected final String TEXT_740 = " |= ";
  protected final String TEXT_741 = "_ESETFLAG;";
  protected final String TEXT_742 = NL + "\t\t\tboolean old";
  protected final String TEXT_743 = "ESet = ";
  protected final String TEXT_744 = "ESet;";
  protected final String TEXT_745 = NL + "\t\t\t";
  protected final String TEXT_746 = "ESet = true;";
  protected final String TEXT_747 = NL + "\t\t\tif (eNotificationRequired())" + NL + "\t\t\t\teNotify(new ";
  protected final String TEXT_748 = "(this, ";
  protected final String TEXT_749 = ".SET, ";
  protected final String TEXT_750 = ", new";
  protected final String TEXT_751 = ", new";
  protected final String TEXT_752 = ", !old";
  protected final String TEXT_753 = "ESet));";
  protected final String TEXT_754 = NL + "\t\t}";
  protected final String TEXT_755 = NL + "\t\telse if (eNotificationRequired())" + NL + "\t\t\teNotify(new ";
  protected final String TEXT_756 = "(this, ";
  protected final String TEXT_757 = ".SET, ";
  protected final String TEXT_758 = ", new";
  protected final String TEXT_759 = ", new";
  protected final String TEXT_760 = "));";
  protected final String TEXT_761 = NL + "\t\t";
  protected final String TEXT_762 = " old";
  protected final String TEXT_763 = " = (";
  protected final String TEXT_764 = " & ";
  protected final String TEXT_765 = "_EFLAG) != 0;";
  protected final String TEXT_766 = NL + "\t\t";
  protected final String TEXT_767 = " old";
  protected final String TEXT_768 = " = ";
  protected final String TEXT_769 = "_EFLAG_VALUES[(";
  protected final String TEXT_770 = " & ";
  protected final String TEXT_771 = "_EFLAG) >>> ";
  protected final String TEXT_772 = "_EFLAG_OFFSET];";
  protected final String TEXT_773 = NL + "\t\tif (new";
  protected final String TEXT_774 = ") ";
  protected final String TEXT_775 = " |= ";
  protected final String TEXT_776 = "_EFLAG; else ";
  protected final String TEXT_777 = " &= ~";
  protected final String TEXT_778 = "_EFLAG;";
  protected final String TEXT_779 = NL + "\t\tif (new";
  protected final String TEXT_780 = " == null) new";
  protected final String TEXT_781 = " = ";
  protected final String TEXT_782 = "_EDEFAULT;" + NL + "\t\t";
  protected final String TEXT_783 = " = ";
  protected final String TEXT_784 = " & ~";
  protected final String TEXT_785 = "_EFLAG | ";
  protected final String TEXT_786 = "new";
  protected final String TEXT_787 = ".ordinal()";
  protected final String TEXT_788 = ".VALUES.indexOf(new";
  protected final String TEXT_789 = ")";
  protected final String TEXT_790 = " << ";
  protected final String TEXT_791 = "_EFLAG_OFFSET;";
  protected final String TEXT_792 = NL + "\t\t";
  protected final String TEXT_793 = " old";
  protected final String TEXT_794 = " = ";
  protected final String TEXT_795 = ";";
  protected final String TEXT_796 = NL + "\t\t";
  protected final String TEXT_797 = " ";
  protected final String TEXT_798 = " = new";
  protected final String TEXT_799 = " == null ? ";
  protected final String TEXT_800 = " : new";
  protected final String TEXT_801 = ";";
  protected final String TEXT_802 = NL + "\t\t";
  protected final String TEXT_803 = " = new";
  protected final String TEXT_804 = " == null ? ";
  protected final String TEXT_805 = " : new";
  protected final String TEXT_806 = ";";
  protected final String TEXT_807 = NL + "\t\t";
  protected final String TEXT_808 = " ";
  protected final String TEXT_809 = " = ";
  protected final String TEXT_810 = "new";
  protected final String TEXT_811 = ";";
  protected final String TEXT_812 = NL + "\t\t";
  protected final String TEXT_813 = " = ";
  protected final String TEXT_814 = "new";
  protected final String TEXT_815 = ";";
  protected final String TEXT_816 = NL + "\t\tObject old";
  protected final String TEXT_817 = " = eVirtualSet(";
  protected final String TEXT_818 = ", ";
  protected final String TEXT_819 = ");";
  protected final String TEXT_820 = NL + "\t\tboolean isSetChange = old";
  protected final String TEXT_821 = " == EVIRTUAL_NO_VALUE;";
  protected final String TEXT_822 = NL + "\t\tboolean old";
  protected final String TEXT_823 = "ESet = (";
  protected final String TEXT_824 = " & ";
  protected final String TEXT_825 = "_ESETFLAG) != 0;";
  protected final String TEXT_826 = NL + "\t\t";
  protected final String TEXT_827 = " |= ";
  protected final String TEXT_828 = "_ESETFLAG;";
  protected final String TEXT_829 = NL + "\t\tboolean old";
  protected final String TEXT_830 = "ESet = ";
  protected final String TEXT_831 = "ESet;";
  protected final String TEXT_832 = NL + "\t\t";
  protected final String TEXT_833 = "ESet = true;";
  protected final String TEXT_834 = NL + "\t\tif (eNotificationRequired())" + NL + "\t\t\teNotify(new ";
  protected final String TEXT_835 = "(this, ";
  protected final String TEXT_836 = ".SET, ";
  protected final String TEXT_837 = ", ";
  protected final String TEXT_838 = "isSetChange ? ";
  protected final String TEXT_839 = " : old";
  protected final String TEXT_840 = "old";
  protected final String TEXT_841 = ", ";
  protected final String TEXT_842 = "new";
  protected final String TEXT_843 = ", ";
  protected final String TEXT_844 = "isSetChange";
  protected final String TEXT_845 = "!old";
  protected final String TEXT_846 = "ESet";
  protected final String TEXT_847 = "));";
  protected final String TEXT_848 = NL + "\t\tif (eNotificationRequired())" + NL + "\t\t\teNotify(new ";
  protected final String TEXT_849 = "(this, ";
  protected final String TEXT_850 = ".SET, ";
  protected final String TEXT_851 = ", ";
  protected final String TEXT_852 = "old";
  protected final String TEXT_853 = " == EVIRTUAL_NO_VALUE ? ";
  protected final String TEXT_854 = " : old";
  protected final String TEXT_855 = "old";
  protected final String TEXT_856 = ", ";
  protected final String TEXT_857 = "new";
  protected final String TEXT_858 = "));";
  protected final String TEXT_859 = NL + "\t\t((";
  protected final String TEXT_860 = ".Internal)((";
  protected final String TEXT_861 = ".Internal.Wrapper)get";
  protected final String TEXT_862 = "()).featureMap()).set(";
  protected final String TEXT_863 = ", ";
  protected final String TEXT_864 = "new ";
  protected final String TEXT_865 = "(";
  protected final String TEXT_866 = "new";
  protected final String TEXT_867 = ")";
  protected final String TEXT_868 = ");";
  protected final String TEXT_869 = NL + "\t\t((";
  protected final String TEXT_870 = ".Internal)get";
  protected final String TEXT_871 = "()).set(";
  protected final String TEXT_872 = ", ";
  protected final String TEXT_873 = "new ";
  protected final String TEXT_874 = "(";
  protected final String TEXT_875 = "new";
  protected final String TEXT_876 = ")";
  protected final String TEXT_877 = ");";
  protected final String TEXT_878 = NL + "\t\t";
  protected final String TEXT_879 = NL + "\t\t// TODO: implement this method to set the '";
  protected final String TEXT_880 = "' ";
  protected final String TEXT_881 = NL + "\t\t// Ensure that you remove @generated or mark it @generated NOT" + NL + "\t\tthrow new UnsupportedOperationException();";
  protected final String TEXT_882 = NL + "\t}" + NL;
  protected final String TEXT_883 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */";
  protected final String TEXT_884 = NL + "\tpublic ";
  protected final String TEXT_885 = " basicUnset";
  protected final String TEXT_886 = "(";
  protected final String TEXT_887 = " msgs)" + NL + "\t{";
  protected final String TEXT_888 = NL + "\t\treturn eDynamicInverseRemove((";
  protected final String TEXT_889 = ")";
  protected final String TEXT_890 = "basicGet";
  protected final String TEXT_891 = "(), ";
  protected final String TEXT_892 = ", msgs);";
  protected final String TEXT_893 = "Object old";
  protected final String TEXT_894 = " = ";
  protected final String TEXT_895 = "eVirtualUnset(";
  protected final String TEXT_896 = ");";
  protected final String TEXT_897 = NL + "\t\t";
  protected final String TEXT_898 = " old";
  protected final String TEXT_899 = " = ";
  protected final String TEXT_900 = ";";
  protected final String TEXT_901 = NL + "\t\t";
  protected final String TEXT_902 = " = null;";
  protected final String TEXT_903 = NL + "\t\tboolean isSetChange = old";
  protected final String TEXT_904 = " != EVIRTUAL_NO_VALUE;";
  protected final String TEXT_905 = NL + "\t\tboolean old";
  protected final String TEXT_906 = "ESet = (";
  protected final String TEXT_907 = " & ";
  protected final String TEXT_908 = "_ESETFLAG) != 0;";
  protected final String TEXT_909 = NL + "\t\t";
  protected final String TEXT_910 = " &= ~";
  protected final String TEXT_911 = "_ESETFLAG;";
  protected final String TEXT_912 = NL + "\t\tboolean old";
  protected final String TEXT_913 = "ESet = ";
  protected final String TEXT_914 = "ESet;";
  protected final String TEXT_915 = NL + "\t\t";
  protected final String TEXT_916 = "ESet = false;";
  protected final String TEXT_917 = NL + "\t\tif (eNotificationRequired())" + NL + "\t\t{" + NL + "\t\t\t";
  protected final String TEXT_918 = " notification = new ";
  protected final String TEXT_919 = "(this, ";
  protected final String TEXT_920 = ".UNSET, ";
  protected final String TEXT_921 = ", ";
  protected final String TEXT_922 = "isSetChange ? old";
  protected final String TEXT_923 = " : null";
  protected final String TEXT_924 = "old";
  protected final String TEXT_925 = ", null, ";
  protected final String TEXT_926 = "isSetChange";
  protected final String TEXT_927 = "old";
  protected final String TEXT_928 = "ESet";
  protected final String TEXT_929 = ");" + NL + "\t\t\tif (msgs == null) msgs = notification; else msgs.add(notification);" + NL + "\t\t}" + NL + "\t\treturn msgs;";
  protected final String TEXT_930 = NL + "\t\t// TODO: implement this method to unset the contained '";
  protected final String TEXT_931 = "' ";
  protected final String TEXT_932 = NL + "\t\t// -> this method is automatically invoked to keep the containment relationship in synch" + NL + "\t\t// -> do not modify other features" + NL + "\t\t// -> return msgs, after adding any generated Notification to it (if it is null, a NotificationChain object must be created first)" + NL + "\t\t// Ensure that you remove @generated or mark it @generated NOT" + NL + "\t\tthrow new UnsupportedOperationException();";
  protected final String TEXT_933 = NL + "\t}" + NL;
  protected final String TEXT_934 = NL + "\t/**" + NL + "\t * Unsets the value of the '{@link ";
  protected final String TEXT_935 = "#";
  protected final String TEXT_936 = " <em>";
  protected final String TEXT_937 = "</em>}' ";
  protected final String TEXT_938 = ".";
  protected final String TEXT_939 = NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->";
  protected final String TEXT_940 = NL + "\t * @see #isSet";
  protected final String TEXT_941 = "()";
  protected final String TEXT_942 = NL + "\t * @see #";
  protected final String TEXT_943 = "()";
  protected final String TEXT_944 = NL + "\t * @see #set";
  protected final String TEXT_945 = "(";
  protected final String TEXT_946 = ")";
  protected final String TEXT_947 = NL + "\t * @generated" + NL + "\t */";
  protected final String TEXT_948 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */";
  protected final String TEXT_949 = NL + "\tvoid unset";
  protected final String TEXT_950 = "();" + NL;
  protected final String TEXT_951 = NL + "\tpublic void unset";
  protected final String TEXT_952 = "_";
  protected final String TEXT_953 = "()" + NL + "\t{";
  protected final String TEXT_954 = NL + "\t\teDynamicUnset(";
  protected final String TEXT_955 = ", ";
  protected final String TEXT_956 = ");";
  protected final String TEXT_957 = NL + "\t\teUnset(";
  protected final String TEXT_958 = ");";
  protected final String TEXT_959 = NL + "\t\t";
  protected final String TEXT_960 = "__ESETTING_DELEGATE.dynamicUnset(this, null, 0);";
  protected final String TEXT_961 = NL + "\t\t";
  protected final String TEXT_962 = " ";
  protected final String TEXT_963 = " = (";
  protected final String TEXT_964 = ")eVirtualGet(";
  protected final String TEXT_965 = ");";
  protected final String TEXT_966 = NL + "\t\tif (";
  protected final String TEXT_967 = " != null) ((";
  protected final String TEXT_968 = ".Unsettable";
  protected final String TEXT_969 = ")";
  protected final String TEXT_970 = ").unset();";
  protected final String TEXT_971 = NL + "\t\t";
  protected final String TEXT_972 = " ";
  protected final String TEXT_973 = " = (";
  protected final String TEXT_974 = ")eVirtualGet(";
  protected final String TEXT_975 = ");";
  protected final String TEXT_976 = NL + "\t\tif (";
  protected final String TEXT_977 = " != null)" + NL + "\t\t{" + NL + "\t\t\t";
  protected final String TEXT_978 = " msgs = null;";
  protected final String TEXT_979 = NL + "\t\t\tmsgs = ((";
  protected final String TEXT_980 = ")";
  protected final String TEXT_981 = ").eInverseRemove(this, EOPPOSITE_FEATURE_BASE - ";
  protected final String TEXT_982 = ", null, msgs);";
  protected final String TEXT_983 = NL + "\t\t\tmsgs = ((";
  protected final String TEXT_984 = ")";
  protected final String TEXT_985 = ").eInverseRemove(this, ";
  protected final String TEXT_986 = ", ";
  protected final String TEXT_987 = ".class, msgs);";
  protected final String TEXT_988 = NL + "\t\t\tmsgs = basicUnset";
  protected final String TEXT_989 = "(msgs);" + NL + "\t\t\tif (msgs != null) msgs.dispatch();" + NL + "\t\t}" + NL + "\t\telse" + NL + "\t\t{";
  protected final String TEXT_990 = NL + "\t\t\tboolean old";
  protected final String TEXT_991 = "ESet = eVirtualIsSet(";
  protected final String TEXT_992 = ");";
  protected final String TEXT_993 = NL + "\t\t\tboolean old";
  protected final String TEXT_994 = "ESet = (";
  protected final String TEXT_995 = " & ";
  protected final String TEXT_996 = "_ESETFLAG) != 0;";
  protected final String TEXT_997 = NL + "\t\t\t";
  protected final String TEXT_998 = " &= ~";
  protected final String TEXT_999 = "_ESETFLAG;";
  protected final String TEXT_1000 = NL + "\t\t\tboolean old";
  protected final String TEXT_1001 = "ESet = ";
  protected final String TEXT_1002 = "ESet;";
  protected final String TEXT_1003 = NL + "\t\t\t";
  protected final String TEXT_1004 = "ESet = false;";
  protected final String TEXT_1005 = NL + "\t\t\tif (eNotificationRequired())" + NL + "\t\t\t\teNotify(new ";
  protected final String TEXT_1006 = "(this, ";
  protected final String TEXT_1007 = ".UNSET, ";
  protected final String TEXT_1008 = ", null, null, old";
  protected final String TEXT_1009 = "ESet));";
  protected final String TEXT_1010 = NL + "\t\t}";
  protected final String TEXT_1011 = NL + "\t\t";
  protected final String TEXT_1012 = " old";
  protected final String TEXT_1013 = " = (";
  protected final String TEXT_1014 = " & ";
  protected final String TEXT_1015 = "_EFLAG) != 0;";
  protected final String TEXT_1016 = NL + "\t\t";
  protected final String TEXT_1017 = " old";
  protected final String TEXT_1018 = " = ";
  protected final String TEXT_1019 = "_EFLAG_VALUES[(";
  protected final String TEXT_1020 = " & ";
  protected final String TEXT_1021 = "_EFLAG) >>> ";
  protected final String TEXT_1022 = "_EFLAG_OFFSET];";
  protected final String TEXT_1023 = NL + "\t\tObject old";
  protected final String TEXT_1024 = " = eVirtualUnset(";
  protected final String TEXT_1025 = ");";
  protected final String TEXT_1026 = NL + "\t\t";
  protected final String TEXT_1027 = " old";
  protected final String TEXT_1028 = " = ";
  protected final String TEXT_1029 = ";";
  protected final String TEXT_1030 = NL + "\t\tboolean isSetChange = old";
  protected final String TEXT_1031 = " != EVIRTUAL_NO_VALUE;";
  protected final String TEXT_1032 = NL + "\t\tboolean old";
  protected final String TEXT_1033 = "ESet = (";
  protected final String TEXT_1034 = " & ";
  protected final String TEXT_1035 = "_ESETFLAG) != 0;";
  protected final String TEXT_1036 = NL + "\t\tboolean old";
  protected final String TEXT_1037 = "ESet = ";
  protected final String TEXT_1038 = "ESet;";
  protected final String TEXT_1039 = NL + "\t\t";
  protected final String TEXT_1040 = " = null;";
  protected final String TEXT_1041 = NL + "\t\t";
  protected final String TEXT_1042 = " &= ~";
  protected final String TEXT_1043 = "_ESETFLAG;";
  protected final String TEXT_1044 = NL + "\t\t";
  protected final String TEXT_1045 = "ESet = false;";
  protected final String TEXT_1046 = NL + "\t\tif (eNotificationRequired())" + NL + "\t\t\teNotify(new ";
  protected final String TEXT_1047 = "(this, ";
  protected final String TEXT_1048 = ".UNSET, ";
  protected final String TEXT_1049 = ", ";
  protected final String TEXT_1050 = "isSetChange ? old";
  protected final String TEXT_1051 = " : null";
  protected final String TEXT_1052 = "old";
  protected final String TEXT_1053 = ", null, ";
  protected final String TEXT_1054 = "isSetChange";
  protected final String TEXT_1055 = "old";
  protected final String TEXT_1056 = "ESet";
  protected final String TEXT_1057 = "));";
  protected final String TEXT_1058 = NL + "\t\tif (";
  protected final String TEXT_1059 = ") ";
  protected final String TEXT_1060 = " |= ";
  protected final String TEXT_1061 = "_EFLAG; else ";
  protected final String TEXT_1062 = " &= ~";
  protected final String TEXT_1063 = "_EFLAG;";
  protected final String TEXT_1064 = NL + "\t\t";
  protected final String TEXT_1065 = " = ";
  protected final String TEXT_1066 = " & ~";
  protected final String TEXT_1067 = "_EFLAG | ";
  protected final String TEXT_1068 = "_EFLAG_DEFAULT;";
  protected final String TEXT_1069 = NL + "\t\t";
  protected final String TEXT_1070 = " = ";
  protected final String TEXT_1071 = ";";
  protected final String TEXT_1072 = NL + "\t\t";
  protected final String TEXT_1073 = " &= ~";
  protected final String TEXT_1074 = "_ESETFLAG;";
  protected final String TEXT_1075 = NL + "\t\t";
  protected final String TEXT_1076 = "ESet = false;";
  protected final String TEXT_1077 = NL + "\t\tif (eNotificationRequired())" + NL + "\t\t\teNotify(new ";
  protected final String TEXT_1078 = "(this, ";
  protected final String TEXT_1079 = ".UNSET, ";
  protected final String TEXT_1080 = ", ";
  protected final String TEXT_1081 = "isSetChange ? old";
  protected final String TEXT_1082 = " : ";
  protected final String TEXT_1083 = "old";
  protected final String TEXT_1084 = ", ";
  protected final String TEXT_1085 = ", ";
  protected final String TEXT_1086 = "isSetChange";
  protected final String TEXT_1087 = "old";
  protected final String TEXT_1088 = "ESet";
  protected final String TEXT_1089 = "));";
  protected final String TEXT_1090 = NL + "\t\t((";
  protected final String TEXT_1091 = ".Internal)((";
  protected final String TEXT_1092 = ".Internal.Wrapper)get";
  protected final String TEXT_1093 = "()).featureMap()).clear(";
  protected final String TEXT_1094 = ");";
  protected final String TEXT_1095 = NL + "\t\t((";
  protected final String TEXT_1096 = ".Internal)get";
  protected final String TEXT_1097 = "()).clear(";
  protected final String TEXT_1098 = ");";
  protected final String TEXT_1099 = NL + "\t\t";
  protected final String TEXT_1100 = NL + "\t\t// TODO: implement this method to unset the '";
  protected final String TEXT_1101 = "' ";
  protected final String TEXT_1102 = NL + "\t\t// Ensure that you remove @generated or mark it @generated NOT" + NL + "\t\tthrow new UnsupportedOperationException();";
  protected final String TEXT_1103 = NL + "\t}" + NL;
  protected final String TEXT_1104 = NL + "\t/**" + NL + "\t * Returns whether the value of the '{@link ";
  protected final String TEXT_1105 = "#";
  protected final String TEXT_1106 = " <em>";
  protected final String TEXT_1107 = "</em>}' ";
  protected final String TEXT_1108 = " is set.";
  protected final String TEXT_1109 = NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @return whether the value of the '<em>";
  protected final String TEXT_1110 = "</em>' ";
  protected final String TEXT_1111 = " is set.";
  protected final String TEXT_1112 = NL + "\t * @see #unset";
  protected final String TEXT_1113 = "()";
  protected final String TEXT_1114 = NL + "\t * @see #";
  protected final String TEXT_1115 = "()";
  protected final String TEXT_1116 = NL + "\t * @see #set";
  protected final String TEXT_1117 = "(";
  protected final String TEXT_1118 = ")";
  protected final String TEXT_1119 = NL + "\t * @generated" + NL + "\t */";
  protected final String TEXT_1120 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */";
  protected final String TEXT_1121 = NL + "\tboolean isSet";
  protected final String TEXT_1122 = "();" + NL;
  protected final String TEXT_1123 = NL + "\tpublic boolean isSet";
  protected final String TEXT_1124 = "_";
  protected final String TEXT_1125 = "()" + NL + "\t{";
  protected final String TEXT_1126 = NL + "\t\treturn eDynamicIsSet(";
  protected final String TEXT_1127 = ", ";
  protected final String TEXT_1128 = ");";
  protected final String TEXT_1129 = NL + "\t\treturn eIsSet(";
  protected final String TEXT_1130 = ");";
  protected final String TEXT_1131 = NL + "\t\treturn ";
  protected final String TEXT_1132 = "__ESETTING_DELEGATE.dynamicIsSet(this, null, 0);";
  protected final String TEXT_1133 = NL + "\t\t";
  protected final String TEXT_1134 = " ";
  protected final String TEXT_1135 = " = (";
  protected final String TEXT_1136 = ")eVirtualGet(";
  protected final String TEXT_1137 = ");";
  protected final String TEXT_1138 = NL + "\t\treturn ";
  protected final String TEXT_1139 = " != null && ((";
  protected final String TEXT_1140 = ".Unsettable";
  protected final String TEXT_1141 = ")";
  protected final String TEXT_1142 = ").isSet();";
  protected final String TEXT_1143 = NL + "\t\treturn eVirtualIsSet(";
  protected final String TEXT_1144 = ");";
  protected final String TEXT_1145 = NL + "\t\treturn (";
  protected final String TEXT_1146 = " & ";
  protected final String TEXT_1147 = "_ESETFLAG) != 0;";
  protected final String TEXT_1148 = NL + "\t\treturn ";
  protected final String TEXT_1149 = "ESet;";
  protected final String TEXT_1150 = NL + "\t\treturn !((";
  protected final String TEXT_1151 = ".Internal)((";
  protected final String TEXT_1152 = ".Internal.Wrapper)get";
  protected final String TEXT_1153 = "()).featureMap()).isEmpty(";
  protected final String TEXT_1154 = ");";
  protected final String TEXT_1155 = NL + "\t\treturn !((";
  protected final String TEXT_1156 = ".Internal)get";
  protected final String TEXT_1157 = "()).isEmpty(";
  protected final String TEXT_1158 = ");";
  protected final String TEXT_1159 = NL + "\t\t";
  protected final String TEXT_1160 = NL + "\t\t// TODO: implement this method to return whether the '";
  protected final String TEXT_1161 = "' ";
  protected final String TEXT_1162 = " is set" + NL + "\t\t// Ensure that you remove @generated or mark it @generated NOT" + NL + "\t\tthrow new UnsupportedOperationException();";
  protected final String TEXT_1163 = NL + "\t}" + NL;
  protected final String TEXT_1164 = NL + "\t/**" + NL + "\t * The cached validation expression for the '{@link #";
  protected final String TEXT_1165 = "(";
  protected final String TEXT_1166 = ") <em>";
  protected final String TEXT_1167 = "</em>}' invariant operation." + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @see #";
  protected final String TEXT_1168 = "(";
  protected final String TEXT_1169 = ")" + NL + "\t * @generated" + NL + "\t * @ordered" + NL + "\t */" + NL + "\tprotected static final ";
  protected final String TEXT_1170 = " ";
  protected final String TEXT_1171 = "__EEXPRESSION = \"";
  protected final String TEXT_1172 = "\";";
  protected final String TEXT_1173 = NL;
  protected final String TEXT_1174 = NL + "\t/**" + NL + "\t * The cached invocation delegate for the '{@link #";
  protected final String TEXT_1175 = "(";
  protected final String TEXT_1176 = ") <em>";
  protected final String TEXT_1177 = "</em>}' operation." + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @see #";
  protected final String TEXT_1178 = "(";
  protected final String TEXT_1179 = ")" + NL + "\t * @generated" + NL + "\t * @ordered" + NL + "\t */" + NL + "\tprotected static final ";
  protected final String TEXT_1180 = ".Internal.InvocationDelegate ";
  protected final String TEXT_1181 = "__EINVOCATION_DELEGATE = ((";
  protected final String TEXT_1182 = ".Internal)";
  protected final String TEXT_1183 = ").getInvocationDelegate();" + NL;
  protected final String TEXT_1184 = NL + "\t/**";
  protected final String TEXT_1185 = NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->";
  protected final String TEXT_1186 = NL + "\t * <!-- begin-model-doc -->";
  protected final String TEXT_1187 = NL + "\t * ";
  protected final String TEXT_1188 = NL + "\t * @param ";
  protected final String TEXT_1189 = NL + "\t *   ";
  protected final String TEXT_1190 = NL + "\t * @param ";
  protected final String TEXT_1191 = " ";
  protected final String TEXT_1192 = NL + "\t * <!-- end-model-doc -->";
  protected final String TEXT_1193 = NL + "\t * @model ";
  protected final String TEXT_1194 = NL + "\t *        ";
  protected final String TEXT_1195 = NL + "\t * @model";
  protected final String TEXT_1196 = NL + "\t * @generated" + NL + "\t */";
  protected final String TEXT_1197 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */";
  protected final String TEXT_1198 = NL + "\t";
  protected final String TEXT_1199 = " ";
  protected final String TEXT_1200 = "(";
  protected final String TEXT_1201 = ")";
  protected final String TEXT_1202 = ";" + NL;
  protected final String TEXT_1203 = NL + "\t@SuppressWarnings(\"unchecked\")";
  protected final String TEXT_1204 = NL + "\tpublic ";
  protected final String TEXT_1205 = " ";
  protected final String TEXT_1206 = "(";
  protected final String TEXT_1207 = ")";
  protected final String TEXT_1208 = NL + "\t{";
  protected final String TEXT_1209 = NL + "\t\t";
  protected final String TEXT_1210 = NL + "\t\treturn" + NL + "\t\t\t";
  protected final String TEXT_1211 = ".validate" + NL + "\t\t\t\t(";
  protected final String TEXT_1212 = "," + NL + "\t\t\t\t this," + NL + "\t\t\t\t ";
  protected final String TEXT_1213 = "," + NL + "\t\t\t\t ";
  protected final String TEXT_1214 = "," + NL + "\t\t\t\t \"";
  protected final String TEXT_1215 = "\",";
  protected final String TEXT_1216 = NL + "\t\t\t\t ";
  protected final String TEXT_1217 = "," + NL + "\t\t\t\t ";
  protected final String TEXT_1218 = "__EEXPRESSION," + NL + "\t\t\t\t ";
  protected final String TEXT_1219 = ".ERROR," + NL + "\t\t\t\t ";
  protected final String TEXT_1220 = ".DIAGNOSTIC_SOURCE," + NL + "\t\t\t\t ";
  protected final String TEXT_1221 = ".";
  protected final String TEXT_1222 = ");";
  protected final String TEXT_1223 = NL + "\t\t// TODO: implement this method" + NL + "\t\t// -> specify the condition that violates the invariant" + NL + "\t\t// -> verify the details of the diagnostic, including severity and message" + NL + "\t\t// Ensure that you remove @generated or mark it @generated NOT" + NL + "\t\tif (false)" + NL + "\t\t{" + NL + "\t\t\tif (";
  protected final String TEXT_1224 = " != null)" + NL + "\t\t\t{" + NL + "\t\t\t\t";
  protected final String TEXT_1225 = ".add" + NL + "\t\t\t\t\t(new ";
  protected final String TEXT_1226 = NL + "\t\t\t\t\t\t(";
  protected final String TEXT_1227 = ".ERROR," + NL + "\t\t\t\t\t\t ";
  protected final String TEXT_1228 = ".DIAGNOSTIC_SOURCE," + NL + "\t\t\t\t\t\t ";
  protected final String TEXT_1229 = ".";
  protected final String TEXT_1230 = "," + NL + "\t\t\t\t\t\t ";
  protected final String TEXT_1231 = ".INSTANCE.getString(\"_UI_GenericInvariant_diagnostic\", new Object[] { \"";
  protected final String TEXT_1232 = "\", ";
  protected final String TEXT_1233 = ".getObjectLabel(this, ";
  protected final String TEXT_1234 = ") }),";
  protected final String TEXT_1235 = NL + "\t\t\t\t\t\t new Object [] { this }));" + NL + "\t\t\t}" + NL + "\t\t\treturn false;" + NL + "\t\t}" + NL + "\t\treturn true;";
  protected final String TEXT_1236 = NL + "\t\ttry" + NL + "\t\t{";
  protected final String TEXT_1237 = NL + "\t\t\t";
  protected final String TEXT_1238 = "__EINVOCATION_DELEGATE.dynamicInvoke(this, ";
  protected final String TEXT_1239 = "new ";
  protected final String TEXT_1240 = ".UnmodifiableEList<Object>(";
  protected final String TEXT_1241 = ", ";
  protected final String TEXT_1242 = ")";
  protected final String TEXT_1243 = "null";
  protected final String TEXT_1244 = ");";
  protected final String TEXT_1245 = NL + "\t\t\treturn ";
  protected final String TEXT_1246 = "(";
  protected final String TEXT_1247 = "(";
  protected final String TEXT_1248 = ")";
  protected final String TEXT_1249 = "__EINVOCATION_DELEGATE.dynamicInvoke(this, ";
  protected final String TEXT_1250 = "new ";
  protected final String TEXT_1251 = ".UnmodifiableEList<Object>(";
  protected final String TEXT_1252 = ", ";
  protected final String TEXT_1253 = ")";
  protected final String TEXT_1254 = "null";
  protected final String TEXT_1255 = ")";
  protected final String TEXT_1256 = ").";
  protected final String TEXT_1257 = "()";
  protected final String TEXT_1258 = ";";
  protected final String TEXT_1259 = NL + "\t\t}" + NL + "\t\tcatch (";
  protected final String TEXT_1260 = " ite)" + NL + "\t\t{" + NL + "\t\t\tthrow new ";
  protected final String TEXT_1261 = "(ite);" + NL + "\t\t}";
  protected final String TEXT_1262 = NL + "\t\t// TODO: implement this method" + NL + "\t\t// Ensure that you remove @generated or mark it @generated NOT" + NL + "\t\tthrow new UnsupportedOperationException();";
  protected final String TEXT_1263 = NL + "\t}" + NL;
  protected final String TEXT_1264 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */";
  protected final String TEXT_1265 = NL + "\t@SuppressWarnings(\"unchecked\")";
  protected final String TEXT_1266 = NL + "\t@Override";
  protected final String TEXT_1267 = NL + "\tpublic ";
  protected final String TEXT_1268 = " eInverseAdd(";
  protected final String TEXT_1269 = " otherEnd, int featureID, ";
  protected final String TEXT_1270 = " msgs)" + NL + "\t{" + NL + "\t\tswitch (featureID";
  protected final String TEXT_1271 = ")" + NL + "\t\t{";
  protected final String TEXT_1272 = NL + "\t\t\tcase ";
  protected final String TEXT_1273 = ":";
  protected final String TEXT_1274 = NL + "\t\t\t\treturn ((";
  protected final String TEXT_1275 = "(";
  protected final String TEXT_1276 = ".InternalMapView";
  protected final String TEXT_1277 = ")";
  protected final String TEXT_1278 = "()).eMap()).basicAdd(otherEnd, msgs);";
  protected final String TEXT_1279 = NL + "\t\t\t\treturn (";
  protected final String TEXT_1280 = "()).basicAdd(otherEnd, msgs);";
  protected final String TEXT_1281 = NL + "\t\t\t\tif (eInternalContainer() != null)" + NL + "\t\t\t\t\tmsgs = eBasicRemoveFromContainer(msgs);";
  protected final String TEXT_1282 = NL + "\t\t\t\treturn basicSet";
  protected final String TEXT_1283 = "((";
  protected final String TEXT_1284 = ")otherEnd, msgs);";
  protected final String TEXT_1285 = NL + "\t\t\t\treturn eBasicSetContainer(otherEnd, ";
  protected final String TEXT_1286 = ", msgs);";
  protected final String TEXT_1287 = NL + "\t\t\t\t";
  protected final String TEXT_1288 = " ";
  protected final String TEXT_1289 = " = (";
  protected final String TEXT_1290 = ")eVirtualGet(";
  protected final String TEXT_1291 = ");";
  protected final String TEXT_1292 = NL + "\t\t\t\t";
  protected final String TEXT_1293 = " ";
  protected final String TEXT_1294 = " = ";
  protected final String TEXT_1295 = "basicGet";
  protected final String TEXT_1296 = "();";
  protected final String TEXT_1297 = NL + "\t\t\t\tif (";
  protected final String TEXT_1298 = " != null)";
  protected final String TEXT_1299 = NL + "\t\t\t\t\tmsgs = ((";
  protected final String TEXT_1300 = ")";
  protected final String TEXT_1301 = ").eInverseRemove(this, EOPPOSITE_FEATURE_BASE - ";
  protected final String TEXT_1302 = ", null, msgs);";
  protected final String TEXT_1303 = NL + "\t\t\t\t\tmsgs = ((";
  protected final String TEXT_1304 = ")";
  protected final String TEXT_1305 = ").eInverseRemove(this, ";
  protected final String TEXT_1306 = ", ";
  protected final String TEXT_1307 = ".class, msgs);";
  protected final String TEXT_1308 = NL + "\t\t\t\treturn basicSet";
  protected final String TEXT_1309 = "((";
  protected final String TEXT_1310 = ")otherEnd, msgs);";
  protected final String TEXT_1311 = NL + "\t\t}";
  protected final String TEXT_1312 = NL + "\t\treturn super.eInverseAdd(otherEnd, featureID, msgs);";
  protected final String TEXT_1313 = NL + "\t\treturn eDynamicInverseAdd(otherEnd, featureID, msgs);";
  protected final String TEXT_1314 = NL + "\t}" + NL;
  protected final String TEXT_1315 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */";
  protected final String TEXT_1316 = NL + "\t@Override";
  protected final String TEXT_1317 = NL + "\tpublic ";
  protected final String TEXT_1318 = " eInverseRemove(";
  protected final String TEXT_1319 = " otherEnd, int featureID, ";
  protected final String TEXT_1320 = " msgs)" + NL + "\t{" + NL + "\t\tswitch (featureID";
  protected final String TEXT_1321 = ")" + NL + "\t\t{";
  protected final String TEXT_1322 = NL + "\t\t\tcase ";
  protected final String TEXT_1323 = ":";
  protected final String TEXT_1324 = NL + "\t\t\t\treturn ((";
  protected final String TEXT_1325 = ")((";
  protected final String TEXT_1326 = ".InternalMapView";
  protected final String TEXT_1327 = ")";
  protected final String TEXT_1328 = "()).eMap()).basicRemove(otherEnd, msgs);";
  protected final String TEXT_1329 = NL + "\t\t\t\treturn ((";
  protected final String TEXT_1330 = ")((";
  protected final String TEXT_1331 = ".Internal.Wrapper)";
  protected final String TEXT_1332 = "()).featureMap()).basicRemove(otherEnd, msgs);";
  protected final String TEXT_1333 = NL + "\t\t\t\treturn ((";
  protected final String TEXT_1334 = ")";
  protected final String TEXT_1335 = "()).basicRemove(otherEnd, msgs);";
  protected final String TEXT_1336 = NL + "\t\t\t\treturn eBasicSetContainer(null, ";
  protected final String TEXT_1337 = ", msgs);";
  protected final String TEXT_1338 = NL + "\t\t\t\treturn basicUnset";
  protected final String TEXT_1339 = "(msgs);";
  protected final String TEXT_1340 = NL + "\t\t\t\treturn basicSet";
  protected final String TEXT_1341 = "(null, msgs);";
  protected final String TEXT_1342 = NL + "\t\t}";
  protected final String TEXT_1343 = NL + "\t\treturn super.eInverseRemove(otherEnd, featureID, msgs);";
  protected final String TEXT_1344 = NL + "\t\treturn eDynamicInverseRemove(otherEnd, featureID, msgs);";
  protected final String TEXT_1345 = NL + "\t}" + NL;
  protected final String TEXT_1346 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */";
  protected final String TEXT_1347 = NL + "\t@Override";
  protected final String TEXT_1348 = NL + "\tpublic ";
  protected final String TEXT_1349 = " eBasicRemoveFromContainerFeature(";
  protected final String TEXT_1350 = " msgs)" + NL + "\t{" + NL + "\t\tswitch (eContainerFeatureID()";
  protected final String TEXT_1351 = ")" + NL + "\t\t{";
  protected final String TEXT_1352 = NL + "\t\t\tcase ";
  protected final String TEXT_1353 = ":" + NL + "\t\t\t\treturn eInternalContainer().eInverseRemove(this, ";
  protected final String TEXT_1354 = ", ";
  protected final String TEXT_1355 = ".class, msgs);";
  protected final String TEXT_1356 = NL + "\t\t}";
  protected final String TEXT_1357 = NL + "\t\treturn super.eBasicRemoveFromContainerFeature(msgs);";
  protected final String TEXT_1358 = NL + "\t\treturn eDynamicBasicRemoveFromContainer(msgs);";
  protected final String TEXT_1359 = NL + "\t}" + NL;
  protected final String TEXT_1360 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */";
  protected final String TEXT_1361 = NL + "\t@Override";
  protected final String TEXT_1362 = NL + "\tpublic Object eGet(int featureID, boolean resolve, boolean coreType)" + NL + "\t{" + NL + "\t\tswitch (featureID";
  protected final String TEXT_1363 = ")" + NL + "\t\t{";
  protected final String TEXT_1364 = NL + "\t\t\tcase ";
  protected final String TEXT_1365 = ":";
  protected final String TEXT_1366 = NL + "\t\t\t\treturn ";
  protected final String TEXT_1367 = "();";
  protected final String TEXT_1368 = NL + "\t\t\t\treturn ";
  protected final String TEXT_1369 = "() ? Boolean.TRUE : Boolean.FALSE;";
  protected final String TEXT_1370 = NL + "\t\t\t\treturn new ";
  protected final String TEXT_1371 = "(";
  protected final String TEXT_1372 = "());";
  protected final String TEXT_1373 = NL + "\t\t\t\tif (resolve) return ";
  protected final String TEXT_1374 = "();" + NL + "\t\t\t\treturn basicGet";
  protected final String TEXT_1375 = "();";
  protected final String TEXT_1376 = NL + "\t\t\t\tif (coreType) return ((";
  protected final String TEXT_1377 = ".InternalMapView";
  protected final String TEXT_1378 = ")";
  protected final String TEXT_1379 = "()).eMap();" + NL + "\t\t\t\telse return ";
  protected final String TEXT_1380 = "();";
  protected final String TEXT_1381 = NL + "\t\t\t\tif (coreType) return ";
  protected final String TEXT_1382 = "();" + NL + "\t\t\t\telse return ";
  protected final String TEXT_1383 = "().map();";
  protected final String TEXT_1384 = NL + "\t\t\t\tif (coreType) return ((";
  protected final String TEXT_1385 = ".Internal.Wrapper)";
  protected final String TEXT_1386 = "()).featureMap();" + NL + "\t\t\t\treturn ";
  protected final String TEXT_1387 = "();";
  protected final String TEXT_1388 = NL + "\t\t\t\tif (coreType) return ";
  protected final String TEXT_1389 = "();" + NL + "\t\t\t\treturn ((";
  protected final String TEXT_1390 = ".Internal)";
  protected final String TEXT_1391 = "()).getWrapper();";
  protected final String TEXT_1392 = NL + "\t\t\t\treturn ";
  protected final String TEXT_1393 = "();";
  protected final String TEXT_1394 = NL + "\t\t}";
  protected final String TEXT_1395 = NL + "\t\treturn super.eGet(featureID, resolve, coreType);";
  protected final String TEXT_1396 = NL + "\t\treturn eDynamicGet(featureID, resolve, coreType);";
  protected final String TEXT_1397 = NL + "\t}" + NL;
  protected final String TEXT_1398 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */";
  protected final String TEXT_1399 = NL + "\t@SuppressWarnings(\"unchecked\")";
  protected final String TEXT_1400 = NL + "\t@Override";
  protected final String TEXT_1401 = NL + "\tpublic void eSet(int featureID, Object newValue)" + NL + "\t{" + NL + "\t\tswitch (featureID";
  protected final String TEXT_1402 = ")" + NL + "\t\t{";
  protected final String TEXT_1403 = NL + "\t\t\tcase ";
  protected final String TEXT_1404 = ":";
  protected final String TEXT_1405 = NL + "\t\t\t\t((";
  protected final String TEXT_1406 = ".Internal)((";
  protected final String TEXT_1407 = ".Internal.Wrapper)";
  protected final String TEXT_1408 = "()).featureMap()).set(newValue);";
  protected final String TEXT_1409 = NL + "\t\t\t\t((";
  protected final String TEXT_1410 = ".Internal)";
  protected final String TEXT_1411 = "()).set(newValue);";
  protected final String TEXT_1412 = NL + "\t\t\t\t((";
  protected final String TEXT_1413 = ".Setting)((";
  protected final String TEXT_1414 = ".InternalMapView";
  protected final String TEXT_1415 = ")";
  protected final String TEXT_1416 = "()).eMap()).set(newValue);";
  protected final String TEXT_1417 = NL + "\t\t\t\t((";
  protected final String TEXT_1418 = ".Setting)";
  protected final String TEXT_1419 = "()).set(newValue);";
  protected final String TEXT_1420 = NL + "\t\t\t\t";
  protected final String TEXT_1421 = "().clear();" + NL + "\t\t\t\t";
  protected final String TEXT_1422 = "().addAll((";
  protected final String TEXT_1423 = "<? extends ";
  protected final String TEXT_1424 = ">";
  protected final String TEXT_1425 = ")newValue);";
  protected final String TEXT_1426 = NL + "\t\t\t\tset";
  protected final String TEXT_1427 = "(((";
  protected final String TEXT_1428 = ")newValue).";
  protected final String TEXT_1429 = "());";
  protected final String TEXT_1430 = NL + "\t\t\t\tset";
  protected final String TEXT_1431 = "(";
  protected final String TEXT_1432 = "(";
  protected final String TEXT_1433 = ")";
  protected final String TEXT_1434 = "newValue);";
  protected final String TEXT_1435 = NL + "\t\t\t\treturn;";
  protected final String TEXT_1436 = NL + "\t\t}";
  protected final String TEXT_1437 = NL + "\t\tsuper.eSet(featureID, newValue);";
  protected final String TEXT_1438 = NL + "\t\teDynamicSet(featureID, newValue);";
  protected final String TEXT_1439 = NL + "\t}" + NL;
  protected final String TEXT_1440 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */";
  protected final String TEXT_1441 = NL + "\t@Override";
  protected final String TEXT_1442 = NL + "\tpublic void eUnset(int featureID)" + NL + "\t{" + NL + "\t\tswitch (featureID";
  protected final String TEXT_1443 = ")" + NL + "\t\t{";
  protected final String TEXT_1444 = NL + "\t\t\tcase ";
  protected final String TEXT_1445 = ":";
  protected final String TEXT_1446 = NL + "\t\t\t\t((";
  protected final String TEXT_1447 = ".Internal.Wrapper)";
  protected final String TEXT_1448 = "()).featureMap().clear();";
  protected final String TEXT_1449 = NL + "\t\t\t\t";
  protected final String TEXT_1450 = "().clear();";
  protected final String TEXT_1451 = NL + "\t\t\t\tunset";
  protected final String TEXT_1452 = "();";
  protected final String TEXT_1453 = NL + "\t\t\t\tset";
  protected final String TEXT_1454 = "((";
  protected final String TEXT_1455 = ")null);";
  protected final String TEXT_1456 = NL + "\t\t\t\tset";
  protected final String TEXT_1457 = "(";
  protected final String TEXT_1458 = ");";
  protected final String TEXT_1459 = NL + "\t\t\t\treturn;";
  protected final String TEXT_1460 = NL + "\t\t}";
  protected final String TEXT_1461 = NL + "\t\tsuper.eUnset(featureID);";
  protected final String TEXT_1462 = NL + "\t\teDynamicUnset(featureID);";
  protected final String TEXT_1463 = NL + "\t}" + NL;
  protected final String TEXT_1464 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */";
  protected final String TEXT_1465 = NL + "\t@SuppressWarnings(\"unchecked\")";
  protected final String TEXT_1466 = NL + "\t@Override";
  protected final String TEXT_1467 = NL + "\tpublic boolean eIsSet(int featureID)" + NL + "\t{" + NL + "\t\tswitch (featureID";
  protected final String TEXT_1468 = ")" + NL + "\t\t{";
  protected final String TEXT_1469 = NL + "\t\t\tcase ";
  protected final String TEXT_1470 = ":";
  protected final String TEXT_1471 = NL + "\t\t\t\treturn isSet";
  protected final String TEXT_1472 = "();";
  protected final String TEXT_1473 = NL + "\t\t\t\treturn ";
  protected final String TEXT_1474 = "__ESETTING_DELEGATE.dynamicIsSet(this, null, 0);";
  protected final String TEXT_1475 = NL + "\t\t\t\treturn !((";
  protected final String TEXT_1476 = ".Internal.Wrapper)";
  protected final String TEXT_1477 = "()).featureMap().isEmpty();";
  protected final String TEXT_1478 = NL + "\t\t\t\treturn ";
  protected final String TEXT_1479 = " != null && !";
  protected final String TEXT_1480 = ".featureMap().isEmpty();";
  protected final String TEXT_1481 = NL + "\t\t\t\treturn ";
  protected final String TEXT_1482 = " != null && !";
  protected final String TEXT_1483 = ".isEmpty();";
  protected final String TEXT_1484 = NL + "\t\t\t\t";
  protected final String TEXT_1485 = " ";
  protected final String TEXT_1486 = " = (";
  protected final String TEXT_1487 = ")eVirtualGet(";
  protected final String TEXT_1488 = ");" + NL + "\t\t\t\treturn ";
  protected final String TEXT_1489 = " != null && !";
  protected final String TEXT_1490 = ".isEmpty();";
  protected final String TEXT_1491 = NL + "\t\t\t\treturn !";
  protected final String TEXT_1492 = "().isEmpty();";
  protected final String TEXT_1493 = NL + "\t\t\t\treturn isSet";
  protected final String TEXT_1494 = "();";
  protected final String TEXT_1495 = NL + "\t\t\t\treturn ";
  protected final String TEXT_1496 = " != null;";
  protected final String TEXT_1497 = NL + "\t\t\t\treturn eVirtualGet(";
  protected final String TEXT_1498 = ") != null;";
  protected final String TEXT_1499 = NL + "\t\t\t\treturn basicGet";
  protected final String TEXT_1500 = "() != null;";
  protected final String TEXT_1501 = NL + "\t\t\t\treturn ";
  protected final String TEXT_1502 = " != null;";
  protected final String TEXT_1503 = NL + "\t\t\t\treturn eVirtualGet(";
  protected final String TEXT_1504 = ") != null;";
  protected final String TEXT_1505 = NL + "\t\t\t\treturn ";
  protected final String TEXT_1506 = "() != null;";
  protected final String TEXT_1507 = NL + "\t\t\t\treturn ((";
  protected final String TEXT_1508 = " & ";
  protected final String TEXT_1509 = "_EFLAG) != 0) != ";
  protected final String TEXT_1510 = ";";
  protected final String TEXT_1511 = NL + "\t\t\t\treturn (";
  protected final String TEXT_1512 = " & ";
  protected final String TEXT_1513 = "_EFLAG) != ";
  protected final String TEXT_1514 = "_EFLAG_DEFAULT;";
  protected final String TEXT_1515 = NL + "\t\t\t\treturn ";
  protected final String TEXT_1516 = " != ";
  protected final String TEXT_1517 = ";";
  protected final String TEXT_1518 = NL + "\t\t\t\treturn eVirtualGet(";
  protected final String TEXT_1519 = ", ";
  protected final String TEXT_1520 = ") != ";
  protected final String TEXT_1521 = ";";
  protected final String TEXT_1522 = NL + "\t\t\t\treturn ";
  protected final String TEXT_1523 = "() != ";
  protected final String TEXT_1524 = ";";
  protected final String TEXT_1525 = NL + "\t\t\t\treturn ";
  protected final String TEXT_1526 = " == null ? ";
  protected final String TEXT_1527 = " != null : !";
  protected final String TEXT_1528 = ".equals(";
  protected final String TEXT_1529 = ");";
  protected final String TEXT_1530 = NL + "\t\t\t\t";
  protected final String TEXT_1531 = " ";
  protected final String TEXT_1532 = " = (";
  protected final String TEXT_1533 = ")eVirtualGet(";
  protected final String TEXT_1534 = ", ";
  protected final String TEXT_1535 = ");" + NL + "\t\t\t\treturn ";
  protected final String TEXT_1536 = " == null ? ";
  protected final String TEXT_1537 = " != null : !";
  protected final String TEXT_1538 = ".equals(";
  protected final String TEXT_1539 = ");";
  protected final String TEXT_1540 = NL + "\t\t\t\treturn ";
  protected final String TEXT_1541 = " == null ? ";
  protected final String TEXT_1542 = "() != null : !";
  protected final String TEXT_1543 = ".equals(";
  protected final String TEXT_1544 = "());";
  protected final String TEXT_1545 = NL + "\t\t}";
  protected final String TEXT_1546 = NL + "\t\treturn super.eIsSet(featureID);";
  protected final String TEXT_1547 = NL + "\t\treturn eDynamicIsSet(featureID);";
  protected final String TEXT_1548 = NL + "\t}" + NL;
  protected final String TEXT_1549 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */";
  protected final String TEXT_1550 = NL + "\t@Override";
  protected final String TEXT_1551 = NL + "\tpublic int eBaseStructuralFeatureID(int derivedFeatureID, Class";
  protected final String TEXT_1552 = " baseClass)" + NL + "\t{";
  protected final String TEXT_1553 = NL + "\t\tif (baseClass == ";
  protected final String TEXT_1554 = ".class)" + NL + "\t\t{" + NL + "\t\t\tswitch (derivedFeatureID";
  protected final String TEXT_1555 = ")" + NL + "\t\t\t{";
  protected final String TEXT_1556 = NL + "\t\t\t\tcase ";
  protected final String TEXT_1557 = ": return ";
  protected final String TEXT_1558 = ";";
  protected final String TEXT_1559 = NL + "\t\t\t\tdefault: return -1;" + NL + "\t\t\t}" + NL + "\t\t}";
  protected final String TEXT_1560 = NL + "\t\treturn super.eBaseStructuralFeatureID(derivedFeatureID, baseClass);" + NL + "\t}";
  protected final String TEXT_1561 = NL + NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */";
  protected final String TEXT_1562 = NL + "\t@Override";
  protected final String TEXT_1563 = NL + "\tpublic int eDerivedStructuralFeatureID(int baseFeatureID, Class";
  protected final String TEXT_1564 = " baseClass)" + NL + "\t{";
  protected final String TEXT_1565 = NL + "\t\tif (baseClass == ";
  protected final String TEXT_1566 = ".class)" + NL + "\t\t{" + NL + "\t\t\tswitch (baseFeatureID)" + NL + "\t\t\t{";
  protected final String TEXT_1567 = NL + "\t\t\t\tcase ";
  protected final String TEXT_1568 = ": return ";
  protected final String TEXT_1569 = ";";
  protected final String TEXT_1570 = NL + "\t\t\t\tdefault: return -1;" + NL + "\t\t\t}" + NL + "\t\t}";
  protected final String TEXT_1571 = NL + "\t\tif (baseClass == ";
  protected final String TEXT_1572 = ".class)" + NL + "\t\t{" + NL + "\t\t\tswitch (baseFeatureID";
  protected final String TEXT_1573 = ")" + NL + "\t\t\t{";
  protected final String TEXT_1574 = NL + "\t\t\t\tcase ";
  protected final String TEXT_1575 = ": return ";
  protected final String TEXT_1576 = ";";
  protected final String TEXT_1577 = NL + "\t\t\t\tdefault: return -1;" + NL + "\t\t\t}" + NL + "\t\t}";
  protected final String TEXT_1578 = NL + "\t\treturn super.eDerivedStructuralFeatureID(baseFeatureID, baseClass);" + NL + "\t}" + NL;
  protected final String TEXT_1579 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */";
  protected final String TEXT_1580 = NL + "\t@Override";
  protected final String TEXT_1581 = NL + "\tpublic int eDerivedOperationID(int baseOperationID, Class";
  protected final String TEXT_1582 = " baseClass)" + NL + "\t{";
  protected final String TEXT_1583 = NL + "\t\tif (baseClass == ";
  protected final String TEXT_1584 = ".class)" + NL + "\t\t{" + NL + "\t\t\tswitch (baseOperationID)" + NL + "\t\t\t{";
  protected final String TEXT_1585 = NL + "\t\t\t\tcase ";
  protected final String TEXT_1586 = ": return ";
  protected final String TEXT_1587 = ";";
  protected final String TEXT_1588 = NL + "\t\t\t\tdefault: return super.eDerivedOperationID(baseOperationID, baseClass);" + NL + "\t\t\t}" + NL + "\t\t}";
  protected final String TEXT_1589 = NL + "\t\tif (baseClass == ";
  protected final String TEXT_1590 = ".class)" + NL + "\t\t{" + NL + "\t\t\tswitch (baseOperationID)" + NL + "\t\t\t{";
  protected final String TEXT_1591 = NL + "\t\t\t\tcase ";
  protected final String TEXT_1592 = ": return ";
  protected final String TEXT_1593 = ";";
  protected final String TEXT_1594 = NL + "\t\t\t\tdefault: return -1;" + NL + "\t\t\t}" + NL + "\t\t}";
  protected final String TEXT_1595 = NL + "\t\tif (baseClass == ";
  protected final String TEXT_1596 = ".class)" + NL + "\t\t{" + NL + "\t\t\tswitch (baseOperationID";
  protected final String TEXT_1597 = ")" + NL + "\t\t\t{";
  protected final String TEXT_1598 = NL + "\t\t\t\tcase ";
  protected final String TEXT_1599 = ": return ";
  protected final String TEXT_1600 = ";";
  protected final String TEXT_1601 = NL + "\t\t\t\tdefault: return -1;" + NL + "\t\t\t}" + NL + "\t\t}";
  protected final String TEXT_1602 = NL + "\t\treturn super.eDerivedOperationID(baseOperationID, baseClass);" + NL + "\t}" + NL;
  protected final String TEXT_1603 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */";
  protected final String TEXT_1604 = NL + "\t@Override";
  protected final String TEXT_1605 = NL + "\tprotected Object[] eVirtualValues()" + NL + "\t{" + NL + "\t\treturn ";
  protected final String TEXT_1606 = ";" + NL + "\t}" + NL + "" + NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */";
  protected final String TEXT_1607 = NL + "\t@Override";
  protected final String TEXT_1608 = NL + "\tprotected void eSetVirtualValues(Object[] newValues)" + NL + "\t{" + NL + "\t\t";
  protected final String TEXT_1609 = " = newValues;" + NL + "\t}" + NL;
  protected final String TEXT_1610 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */";
  protected final String TEXT_1611 = NL + "\t@Override";
  protected final String TEXT_1612 = NL + "\tprotected int eVirtualIndexBits(int offset)" + NL + "\t{" + NL + "\t\tswitch (offset)" + NL + "\t\t{";
  protected final String TEXT_1613 = NL + "\t\t\tcase ";
  protected final String TEXT_1614 = " :" + NL + "\t\t\t\treturn ";
  protected final String TEXT_1615 = ";";
  protected final String TEXT_1616 = NL + "\t\t\tdefault :" + NL + "\t\t\t\tthrow new IndexOutOfBoundsException();" + NL + "\t\t}" + NL + "\t}" + NL + "" + NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */";
  protected final String TEXT_1617 = NL + "\t@Override";
  protected final String TEXT_1618 = NL + "\tprotected void eSetVirtualIndexBits(int offset, int newIndexBits)" + NL + "\t{" + NL + "\t\tswitch (offset)" + NL + "\t\t{";
  protected final String TEXT_1619 = NL + "\t\t\tcase ";
  protected final String TEXT_1620 = " :" + NL + "\t\t\t\t";
  protected final String TEXT_1621 = " = newIndexBits;" + NL + "\t\t\t\tbreak;";
  protected final String TEXT_1622 = NL + "\t\t\tdefault :" + NL + "\t\t\t\tthrow new IndexOutOfBoundsException();" + NL + "\t\t}" + NL + "\t}" + NL;
  protected final String TEXT_1623 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */";
  protected final String TEXT_1624 = NL + "\t@Override";
  protected final String TEXT_1625 = NL + "\t@SuppressWarnings(\"unchecked\")";
  protected final String TEXT_1626 = NL + "\tpublic Object eInvoke(int operationID, ";
  protected final String TEXT_1627 = " arguments) throws ";
  protected final String TEXT_1628 = NL + "\t{" + NL + "\t\tswitch (operationID";
  protected final String TEXT_1629 = ")" + NL + "\t\t{";
  protected final String TEXT_1630 = NL + "\t\t\tcase ";
  protected final String TEXT_1631 = ":";
  protected final String TEXT_1632 = NL + "\t\t\t\t";
  protected final String TEXT_1633 = "(";
  protected final String TEXT_1634 = "(";
  protected final String TEXT_1635 = "(";
  protected final String TEXT_1636 = ")";
  protected final String TEXT_1637 = "arguments.get(";
  protected final String TEXT_1638 = ")";
  protected final String TEXT_1639 = ").";
  protected final String TEXT_1640 = "()";
  protected final String TEXT_1641 = ", ";
  protected final String TEXT_1642 = ");" + NL + "\t\t\t\treturn null;";
  protected final String TEXT_1643 = NL + "\t\t\t\treturn ";
  protected final String TEXT_1644 = "new ";
  protected final String TEXT_1645 = "(";
  protected final String TEXT_1646 = "(";
  protected final String TEXT_1647 = "(";
  protected final String TEXT_1648 = "(";
  protected final String TEXT_1649 = ")";
  protected final String TEXT_1650 = "arguments.get(";
  protected final String TEXT_1651 = ")";
  protected final String TEXT_1652 = ").";
  protected final String TEXT_1653 = "()";
  protected final String TEXT_1654 = ", ";
  protected final String TEXT_1655 = ")";
  protected final String TEXT_1656 = ")";
  protected final String TEXT_1657 = ";";
  protected final String TEXT_1658 = NL + "\t\t}";
  protected final String TEXT_1659 = NL + "\t\treturn super.eInvoke(operationID, arguments);";
  protected final String TEXT_1660 = NL + "\t\treturn eDynamicInvoke(operationID, arguments);";
  protected final String TEXT_1661 = NL + "\t}" + NL;
  protected final String TEXT_1662 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */";
  protected final String TEXT_1663 = NL + "\t@Override";
  protected final String TEXT_1664 = NL + "\tpublic String toString()" + NL + "\t{" + NL + "\t\tif (eIsProxy()) return super.toString();" + NL + "" + NL + "\t\tStringBuffer result = new StringBuffer(super.toString());";
  protected final String TEXT_1665 = NL + "\t\tresult.append(\" (";
  protected final String TEXT_1666 = ": \");";
  protected final String TEXT_1667 = NL + "\t\tresult.append(\", ";
  protected final String TEXT_1668 = ": \");";
  protected final String TEXT_1669 = NL + "\t\tif (eVirtualIsSet(";
  protected final String TEXT_1670 = ")) result.append(eVirtualGet(";
  protected final String TEXT_1671 = ")); else result.append(\"<unset>\");";
  protected final String TEXT_1672 = NL + "\t\tif (";
  protected final String TEXT_1673 = "(";
  protected final String TEXT_1674 = " & ";
  protected final String TEXT_1675 = "_ESETFLAG) != 0";
  protected final String TEXT_1676 = "ESet";
  protected final String TEXT_1677 = ") result.append((";
  protected final String TEXT_1678 = " & ";
  protected final String TEXT_1679 = "_EFLAG) != 0); else result.append(\"<unset>\");";
  protected final String TEXT_1680 = NL + "\t\tif (";
  protected final String TEXT_1681 = "(";
  protected final String TEXT_1682 = " & ";
  protected final String TEXT_1683 = "_ESETFLAG) != 0";
  protected final String TEXT_1684 = "ESet";
  protected final String TEXT_1685 = ") result.append(";
  protected final String TEXT_1686 = "_EFLAG_VALUES[(";
  protected final String TEXT_1687 = " & ";
  protected final String TEXT_1688 = "_EFLAG) >>> ";
  protected final String TEXT_1689 = "_EFLAG_OFFSET]); else result.append(\"<unset>\");";
  protected final String TEXT_1690 = NL + "\t\tif (";
  protected final String TEXT_1691 = "(";
  protected final String TEXT_1692 = " & ";
  protected final String TEXT_1693 = "_ESETFLAG) != 0";
  protected final String TEXT_1694 = "ESet";
  protected final String TEXT_1695 = ") result.append(";
  protected final String TEXT_1696 = "); else result.append(\"<unset>\");";
  protected final String TEXT_1697 = NL + "\t\tresult.append(eVirtualGet(";
  protected final String TEXT_1698 = ", ";
  protected final String TEXT_1699 = "));";
  protected final String TEXT_1700 = NL + "\t\tresult.append((";
  protected final String TEXT_1701 = " & ";
  protected final String TEXT_1702 = "_EFLAG) != 0);";
  protected final String TEXT_1703 = NL + "\t\tresult.append(";
  protected final String TEXT_1704 = "_EFLAG_VALUES[(";
  protected final String TEXT_1705 = " & ";
  protected final String TEXT_1706 = "_EFLAG) >>> ";
  protected final String TEXT_1707 = "_EFLAG_OFFSET]);";
  protected final String TEXT_1708 = NL + "\t\tresult.append(";
  protected final String TEXT_1709 = ");";
  protected final String TEXT_1710 = NL + "\t\tresult.append(')');" + NL + "\t\treturn result.toString();" + NL + "\t}" + NL;
  protected final String TEXT_1711 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */";
  protected final String TEXT_1712 = NL + "\t@";
  protected final String TEXT_1713 = NL + "\tprotected int hash = -1;" + NL + "" + NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */" + NL + "\tpublic int getHash()" + NL + "\t{" + NL + "\t\tif (hash == -1)" + NL + "\t\t{" + NL + "\t\t\t";
  protected final String TEXT_1714 = " theKey = getKey();" + NL + "\t\t\thash = (theKey == null ? 0 : theKey.hashCode());" + NL + "\t\t}" + NL + "\t\treturn hash;" + NL + "\t}" + NL + "" + NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */" + NL + "\tpublic void setHash(int hash)" + NL + "\t{" + NL + "\t\tthis.hash = hash;" + NL + "\t}" + NL + "" + NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */" + NL + "\tpublic ";
  protected final String TEXT_1715 = " getKey()" + NL + "\t{";
  protected final String TEXT_1716 = NL + "\t\treturn new ";
  protected final String TEXT_1717 = "(getTypedKey());";
  protected final String TEXT_1718 = NL + "\t\treturn getTypedKey();";
  protected final String TEXT_1719 = NL + "\t}" + NL + "" + NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */" + NL + "\tpublic void setKey(";
  protected final String TEXT_1720 = " key)" + NL + "\t{";
  protected final String TEXT_1721 = NL + "\t\tgetTypedKey().addAll(";
  protected final String TEXT_1722 = "(";
  protected final String TEXT_1723 = ")";
  protected final String TEXT_1724 = "key);";
  protected final String TEXT_1725 = NL + "\t\tsetTypedKey(key);";
  protected final String TEXT_1726 = NL + "\t\tsetTypedKey(((";
  protected final String TEXT_1727 = ")key).";
  protected final String TEXT_1728 = "());";
  protected final String TEXT_1729 = NL + "\t\tsetTypedKey((";
  protected final String TEXT_1730 = ")key);";
  protected final String TEXT_1731 = NL + "\t}" + NL + "" + NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */" + NL + "\tpublic ";
  protected final String TEXT_1732 = " getValue()" + NL + "\t{";
  protected final String TEXT_1733 = NL + "\t\treturn new ";
  protected final String TEXT_1734 = "(getTypedValue());";
  protected final String TEXT_1735 = NL + "\t\treturn getTypedValue();";
  protected final String TEXT_1736 = NL + "\t}" + NL + "" + NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */" + NL + "\tpublic ";
  protected final String TEXT_1737 = " setValue(";
  protected final String TEXT_1738 = " value)" + NL + "\t{" + NL + "\t\t";
  protected final String TEXT_1739 = " oldValue = getValue();";
  protected final String TEXT_1740 = NL + "\t\tgetTypedValue().clear();" + NL + "\t\tgetTypedValue().addAll(";
  protected final String TEXT_1741 = "(";
  protected final String TEXT_1742 = ")";
  protected final String TEXT_1743 = "value);";
  protected final String TEXT_1744 = NL + "\t\tsetTypedValue(value);";
  protected final String TEXT_1745 = NL + "\t\tsetTypedValue(((";
  protected final String TEXT_1746 = ")value).";
  protected final String TEXT_1747 = "());";
  protected final String TEXT_1748 = NL + "\t\tsetTypedValue((";
  protected final String TEXT_1749 = ")value);";
  protected final String TEXT_1750 = NL + "\t\treturn oldValue;" + NL + "\t}" + NL + "" + NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */";
  protected final String TEXT_1751 = NL + "\t@SuppressWarnings(\"unchecked\")";
  protected final String TEXT_1752 = NL + "\tpublic ";
  protected final String TEXT_1753 = " getEMap()" + NL + "\t{" + NL + "\t\t";
  protected final String TEXT_1754 = " container = eContainer();" + NL + "\t\treturn container == null ? null : (";
  protected final String TEXT_1755 = ")container.eGet(eContainmentFeature());" + NL + "\t}" + NL;
  protected final String TEXT_1756 = NL + "} //";
  protected final String TEXT_1757 = NL;

  public String generate(Object argument)
  {
    final StringBuffer stringBuffer = new StringBuffer();
    
/**
 * <copyright>
 *
 * Copyright (c) 2002-2010 IBM Corporation and others.
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
    if (isGWT) {
    stringBuffer.append(TEXT_57);
    stringBuffer.append(genModel.getImportedName("com.google.gwt.user.client.rpc.GwtTransient"));
    }
    stringBuffer.append(TEXT_58);
    stringBuffer.append(eVirtualValuesField);
    stringBuffer.append(TEXT_59);
    }
    { List<String> eVirtualIndexBitFields = genClass.getEVirtualIndexBitFields(new ArrayList<String>());
    if (!eVirtualIndexBitFields.isEmpty()) {
    for (String eVirtualIndexBitField : eVirtualIndexBitFields) {
    stringBuffer.append(TEXT_60);
    if (isGWT) {
    stringBuffer.append(TEXT_61);
    stringBuffer.append(genModel.getImportedName("com.google.gwt.user.client.rpc.GwtTransient"));
    }
    stringBuffer.append(TEXT_62);
    stringBuffer.append(eVirtualIndexBitField);
    stringBuffer.append(TEXT_63);
    }
    }
    }
    }
    if (isImplementation && genClass.isModelRoot() && genModel.isBooleanFlagsEnabled() && genModel.getBooleanFlagsReservedBits() == -1) {
    stringBuffer.append(TEXT_64);
    if (isGWT) {
    stringBuffer.append(TEXT_65);
    stringBuffer.append(genModel.getImportedName("com.google.gwt.user.client.rpc.GwtTransient"));
    }
    stringBuffer.append(TEXT_66);
    stringBuffer.append(genModel.getBooleanFlagsField());
    stringBuffer.append(TEXT_67);
    }
    if (isImplementation && !genModel.isReflectiveDelegation()) {
    for (GenFeature genFeature : genClass.getDeclaredFieldGenFeatures()) {
    if (genFeature.hasSettingDelegate()) {
    stringBuffer.append(TEXT_68);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_69);
    stringBuffer.append(genFeature.getFormattedName());
    stringBuffer.append(TEXT_70);
    stringBuffer.append(genFeature.getFeatureKind());
    stringBuffer.append(TEXT_71);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_72);
    if (isGWT) {
    stringBuffer.append(TEXT_73);
    stringBuffer.append(genModel.getImportedName("com.google.gwt.user.client.rpc.GwtTransient"));
    }
    stringBuffer.append(TEXT_74);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.EStructuralFeature"));
    stringBuffer.append(TEXT_75);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_76);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.EStructuralFeature"));
    stringBuffer.append(TEXT_77);
    stringBuffer.append(genFeature.getQualifiedFeatureAccessor());
    stringBuffer.append(TEXT_78);
    } else if (genFeature.isListType() || genFeature.isReferenceType()) {
    if (genClass.isField(genFeature)) {
    stringBuffer.append(TEXT_79);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_80);
    stringBuffer.append(genFeature.getFormattedName());
    stringBuffer.append(TEXT_81);
    stringBuffer.append(genFeature.getFeatureKind());
    stringBuffer.append(TEXT_82);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_83);
    if (isGWT) {
    stringBuffer.append(TEXT_84);
    stringBuffer.append(genModel.getImportedName("com.google.gwt.user.client.rpc.GwtTransient"));
    }
    stringBuffer.append(TEXT_85);
    stringBuffer.append(genFeature.getImportedInternalType(genClass));
    stringBuffer.append(TEXT_86);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_87);
    }
    if (genModel.isArrayAccessors() && genFeature.isListType() && !genFeature.isFeatureMapType() && !genFeature.isMapType()) { String rawListItemType = genFeature.getRawListItemType(); int index = rawListItemType.indexOf('['); String head = rawListItemType; String tail = ""; if (index != -1) { head = rawListItemType.substring(0, index); tail = rawListItemType.substring(index); } 
    stringBuffer.append(TEXT_88);
    stringBuffer.append(genFeature.getGetArrayAccessor());
    stringBuffer.append(TEXT_89);
    stringBuffer.append(genFeature.getFormattedName());
    stringBuffer.append(TEXT_90);
    stringBuffer.append(genFeature.getGetArrayAccessor());
    stringBuffer.append(TEXT_91);
    if (genFeature.getQualifiedListItemType(genClass).contains("<")) {
    stringBuffer.append(TEXT_92);
    }
    stringBuffer.append(TEXT_93);
    stringBuffer.append(rawListItemType);
    stringBuffer.append(TEXT_94);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_95);
    stringBuffer.append(head);
    stringBuffer.append(TEXT_96);
    stringBuffer.append(tail);
    stringBuffer.append(TEXT_97);
    }
    } else {
    if (genFeature.hasEDefault() && (!genFeature.isVolatile() || !genModel.isReflectiveDelegation() && (!genFeature.hasDelegateFeature() || !genFeature.isUnsettable()))) { String staticDefaultValue = genFeature.getStaticDefaultValue();
    stringBuffer.append(TEXT_98);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_99);
    stringBuffer.append(genFeature.getFormattedName());
    stringBuffer.append(TEXT_100);
    stringBuffer.append(genFeature.getFeatureKind());
    stringBuffer.append(TEXT_101);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_102);
    if (genModel.useGenerics() && genFeature.isListDataType() && genFeature.isSetDefaultValue()) {
    stringBuffer.append(TEXT_103);
    }
    stringBuffer.append(TEXT_104);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_105);
    stringBuffer.append(genFeature.getEDefault());
    if ("".equals(staticDefaultValue)) {
    stringBuffer.append(TEXT_106);
    stringBuffer.append(genFeature.getEcoreFeature().getDefaultValueLiteral());
    stringBuffer.append(TEXT_107);
    } else {
    stringBuffer.append(TEXT_108);
    stringBuffer.append(staticDefaultValue);
    stringBuffer.append(TEXT_109);
    stringBuffer.append(genModel.getNonNLS(staticDefaultValue));
    }
    stringBuffer.append(TEXT_110);
    }
    if (genClass.isField(genFeature)) {
    if (genClass.isFlag(genFeature)) { int flagIndex = genClass.getFlagIndex(genFeature);
    if (flagIndex > 31 && flagIndex % 32 == 0) {
    stringBuffer.append(TEXT_111);
    if (isGWT) {
    stringBuffer.append(TEXT_112);
    stringBuffer.append(genModel.getImportedName("com.google.gwt.user.client.rpc.GwtTransient"));
    }
    stringBuffer.append(TEXT_113);
    stringBuffer.append(genClass.getFlagsField(genFeature));
    stringBuffer.append(TEXT_114);
    }
    if (genFeature.isEnumType()) {
    stringBuffer.append(TEXT_115);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_116);
    stringBuffer.append(genFeature.getFormattedName());
    stringBuffer.append(TEXT_117);
    stringBuffer.append(genFeature.getFeatureKind());
    stringBuffer.append(TEXT_118);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_119);
    stringBuffer.append(flagIndex % 32);
    stringBuffer.append(TEXT_120);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_121);
    stringBuffer.append(genFeature.getFormattedName());
    stringBuffer.append(TEXT_122);
    stringBuffer.append(genFeature.getFeatureKind());
    stringBuffer.append(TEXT_123);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_124);
    if (isJDK50) {
    stringBuffer.append(genFeature.getEDefault());
    stringBuffer.append(TEXT_125);
    } else {
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_126);
    stringBuffer.append(genFeature.getEDefault());
    stringBuffer.append(TEXT_127);
    }
    stringBuffer.append(TEXT_128);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_129);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_130);
    stringBuffer.append(genFeature.getTypeGenClassifier().getFormattedName());
    stringBuffer.append(TEXT_131);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_132);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_133);
    if (isJDK50) {
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_134);
    } else {
    stringBuffer.append(TEXT_135);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_136);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_137);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_138);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_139);
    }
    stringBuffer.append(TEXT_140);
    }
    stringBuffer.append(TEXT_141);
    stringBuffer.append(genClass.getFlagSize(genFeature) > 1 ? "s" : "");
    stringBuffer.append(TEXT_142);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_143);
    stringBuffer.append(genFeature.getFormattedName());
    stringBuffer.append(TEXT_144);
    stringBuffer.append(genFeature.getFeatureKind());
    stringBuffer.append(TEXT_145);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_146);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_147);
    stringBuffer.append(genClass.getFlagMask(genFeature));
    stringBuffer.append(TEXT_148);
    if (genFeature.isEnumType()) {
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_149);
    } else {
    stringBuffer.append(flagIndex % 32);
    }
    stringBuffer.append(TEXT_150);
    } else {
    stringBuffer.append(TEXT_151);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_152);
    stringBuffer.append(genFeature.getFormattedName());
    stringBuffer.append(TEXT_153);
    stringBuffer.append(genFeature.getFeatureKind());
    stringBuffer.append(TEXT_154);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_155);
    if (isGWT) {
    stringBuffer.append(TEXT_156);
    stringBuffer.append(genModel.getImportedName("com.google.gwt.user.client.rpc.GwtTransient"));
    }
    stringBuffer.append(TEXT_157);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_158);
    stringBuffer.append(genFeature.getSafeName());
    if (genFeature.hasEDefault()) {
    stringBuffer.append(TEXT_159);
    stringBuffer.append(genFeature.getEDefault());
    }
    stringBuffer.append(TEXT_160);
    }
    }
    }
    if (genClass.isESetField(genFeature)) {
    if (genClass.isESetFlag(genFeature)) { int flagIndex = genClass.getESetFlagIndex(genFeature);
    if (flagIndex > 31 && flagIndex % 32 == 0) {
    stringBuffer.append(TEXT_161);
    if (isGWT) {
    stringBuffer.append(TEXT_162);
    stringBuffer.append(genModel.getImportedName("com.google.gwt.user.client.rpc.GwtTransient"));
    }
    stringBuffer.append(TEXT_163);
    stringBuffer.append(genClass.getESetFlagsField(genFeature));
    stringBuffer.append(TEXT_164);
    }
    stringBuffer.append(TEXT_165);
    stringBuffer.append(genFeature.getFormattedName());
    stringBuffer.append(TEXT_166);
    stringBuffer.append(genFeature.getFeatureKind());
    stringBuffer.append(TEXT_167);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_168);
    stringBuffer.append(flagIndex % 32 );
    stringBuffer.append(TEXT_169);
    } else {
    stringBuffer.append(TEXT_170);
    stringBuffer.append(genFeature.getFormattedName());
    stringBuffer.append(TEXT_171);
    stringBuffer.append(genFeature.getFeatureKind());
    stringBuffer.append(TEXT_172);
    if (isGWT) {
    stringBuffer.append(TEXT_173);
    stringBuffer.append(genModel.getImportedName("com.google.gwt.user.client.rpc.GwtTransient"));
    }
    stringBuffer.append(TEXT_174);
    stringBuffer.append(genFeature.getUncapName());
    stringBuffer.append(TEXT_175);
    }
    }
    //Class/declaredFieldGenFeature.override.javajetinc
    }
    }
    if (isImplementation && genClass.hasOffsetCorrection() && !genClass.getImplementedGenFeatures().isEmpty()) {
    stringBuffer.append(TEXT_176);
    stringBuffer.append(genClass.getOffsetCorrectionField(null));
    stringBuffer.append(TEXT_177);
    stringBuffer.append(genClass.getQualifiedClassifierAccessor());
    stringBuffer.append(TEXT_178);
    stringBuffer.append(genClass.getImplementedGenFeatures().get(0).getQualifiedFeatureAccessor());
    stringBuffer.append(TEXT_179);
    stringBuffer.append(genClass.getQualifiedFeatureID(genClass.getImplementedGenFeatures().get(0)));
    stringBuffer.append(TEXT_180);
    }
    if (isImplementation && !genModel.isReflectiveDelegation()) {
    for (GenFeature genFeature : genClass.getImplementedGenFeatures()) { GenFeature reverseFeature = genFeature.getReverse();
    if (reverseFeature != null && reverseFeature.getGenClass().hasOffsetCorrection()) {
    stringBuffer.append(TEXT_181);
    stringBuffer.append(genClass.getOffsetCorrectionField(genFeature));
    stringBuffer.append(TEXT_182);
    stringBuffer.append(reverseFeature.getGenClass().getQualifiedClassifierAccessor());
    stringBuffer.append(TEXT_183);
    stringBuffer.append(reverseFeature.getQualifiedFeatureAccessor());
    stringBuffer.append(TEXT_184);
    stringBuffer.append(reverseFeature.getGenClass().getQualifiedFeatureID(reverseFeature));
    stringBuffer.append(TEXT_185);
    }
    }
    }
    if (genModel.isOperationReflection() && isImplementation && genClass.hasOffsetCorrection() && !genClass.getImplementedGenOperations().isEmpty()) {
    stringBuffer.append(TEXT_186);
    stringBuffer.append(genClass.getQualifiedClassifierAccessor());
    stringBuffer.append(TEXT_187);
    stringBuffer.append(genClass.getImplementedGenOperations().get(0).getQualifiedOperationAccessor());
    stringBuffer.append(TEXT_188);
    stringBuffer.append(genClass.getQualifiedOperationID(genClass.getImplementedGenOperations().get(0)));
    stringBuffer.append(TEXT_189);
    }
    if (isImplementation) {
    stringBuffer.append(TEXT_190);
    if (genModel.isPublicConstructors()) {
    stringBuffer.append(TEXT_191);
    } else {
    stringBuffer.append(TEXT_192);
    }
    stringBuffer.append(TEXT_193);
    stringBuffer.append(genClass.getClassName());
    stringBuffer.append(TEXT_194);
    for (GenFeature genFeature : genClass.getFlagGenFeaturesWithDefault()) {
    stringBuffer.append(TEXT_195);
    stringBuffer.append(genClass.getFlagsField(genFeature));
    stringBuffer.append(TEXT_196);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_197);
    if (!genFeature.isBooleanType()) {
    stringBuffer.append(TEXT_198);
    }
    stringBuffer.append(TEXT_199);
    }
    stringBuffer.append(TEXT_200);
    if (genModel.useClassOverrideAnnotation()) {
    stringBuffer.append(TEXT_201);
    }
    stringBuffer.append(TEXT_202);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.EClass"));
    stringBuffer.append(TEXT_203);
    stringBuffer.append(genClass.getQualifiedClassifierAccessor());
    stringBuffer.append(TEXT_204);
    }
    if (isImplementation && (genModel.getFeatureDelegation() == GenDelegationKind.REFLECTIVE_LITERAL || genModel.isDynamicDelegation()) && (genClass.getClassExtendsGenClass() == null || (genClass.getClassExtendsGenClass().getGenModel().getFeatureDelegation() != GenDelegationKind.REFLECTIVE_LITERAL && !genClass.getClassExtendsGenClass().getGenModel().isDynamicDelegation()))) {
    stringBuffer.append(TEXT_205);
    if (genModel.useClassOverrideAnnotation()) {
    stringBuffer.append(TEXT_206);
    }
    stringBuffer.append(TEXT_207);
    stringBuffer.append(genClass.getClassExtendsGenClass() == null ? 0 : genClass.getClassExtendsGenClass().getAllGenFeatures().size());
    stringBuffer.append(TEXT_208);
    }
    //Class/reflectiveDelegation.override.javajetinc
    new Runnable() { public void run() {
    for (GenFeature genFeature : (isImplementation ? genClass.getImplementedGenFeatures() : genClass.getDeclaredGenFeatures())) {
    if (genModel.isArrayAccessors() && genFeature.isListType() && !genFeature.isFeatureMapType() && !genFeature.isMapType()) {
    stringBuffer.append(TEXT_209);
    if (!isImplementation) {
    stringBuffer.append(TEXT_210);
    stringBuffer.append(genFeature.getListItemType(genClass));
    stringBuffer.append(TEXT_211);
    stringBuffer.append(genFeature.getGetArrayAccessor());
    stringBuffer.append(TEXT_212);
    } else {
    stringBuffer.append(TEXT_213);
    stringBuffer.append(genFeature.getListItemType(genClass));
    stringBuffer.append(TEXT_214);
    stringBuffer.append(genFeature.getGetArrayAccessor());
    stringBuffer.append(TEXT_215);
    if (genFeature.isVolatile()) {
    stringBuffer.append(TEXT_216);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.util.BasicEList"));
    stringBuffer.append(genFeature.getListTemplateArguments(genClass));
    stringBuffer.append(TEXT_217);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.util.BasicEList"));
    stringBuffer.append(genFeature.getListTemplateArguments(genClass));
    stringBuffer.append(TEXT_218);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_219);
    if (genModel.useGenerics() && !genFeature.getListItemType(genClass).contains("<") && !genFeature.getListItemType(null).equals(genFeature.getListItemType(genClass))) {
    stringBuffer.append(TEXT_220);
    stringBuffer.append(genFeature.getListItemType(genClass));
    stringBuffer.append(TEXT_221);
    }
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_222);
    } else {
    stringBuffer.append(TEXT_223);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_224);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_225);
    if (genModel.useGenerics() && !genFeature.getListItemType(genClass).contains("<") && !genFeature.getListItemType(null).equals(genFeature.getListItemType(genClass))) {
    stringBuffer.append(TEXT_226);
    stringBuffer.append(genFeature.getListItemType(genClass));
    stringBuffer.append(TEXT_227);
    }
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_228);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.util.BasicEList"));
    stringBuffer.append(genFeature.getListTemplateArguments(genClass));
    stringBuffer.append(TEXT_229);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.util.BasicEList"));
    stringBuffer.append(genFeature.getListTemplateArguments(genClass));
    stringBuffer.append(TEXT_230);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_231);
    }
    stringBuffer.append(TEXT_232);
    stringBuffer.append(genFeature.getListItemType(genClass));
    stringBuffer.append(TEXT_233);
    }
    stringBuffer.append(TEXT_234);
    if (!isImplementation) {
    stringBuffer.append(TEXT_235);
    stringBuffer.append(genFeature.getListItemType(genClass));
    stringBuffer.append(TEXT_236);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_237);
    } else {
    stringBuffer.append(TEXT_238);
    stringBuffer.append(genFeature.getListItemType(genClass));
    stringBuffer.append(TEXT_239);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_240);
    if (!genModel.useGenerics()) {
    stringBuffer.append(TEXT_241);
    stringBuffer.append(genFeature.getListItemType(genClass));
    stringBuffer.append(TEXT_242);
    }
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_243);
    }
    stringBuffer.append(TEXT_244);
    if (!isImplementation) {
    stringBuffer.append(TEXT_245);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_246);
    } else {
    stringBuffer.append(TEXT_247);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_248);
    if (genFeature.isVolatile()) {
    stringBuffer.append(TEXT_249);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_250);
    } else {
    stringBuffer.append(TEXT_251);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_252);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_253);
    }
    stringBuffer.append(TEXT_254);
    }
    stringBuffer.append(TEXT_255);
    if (!isImplementation) {
    stringBuffer.append(TEXT_256);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_257);
    stringBuffer.append(genFeature.getListItemType(genClass));
    stringBuffer.append(TEXT_258);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_259);
    } else {
    stringBuffer.append(TEXT_260);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_261);
    stringBuffer.append(genFeature.getListItemType(genClass));
    stringBuffer.append(TEXT_262);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_263);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.util.BasicEList"));
    stringBuffer.append(genFeature.getListTemplateArguments(genClass));
    stringBuffer.append(TEXT_264);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_265);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_266);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_267);
    }
    stringBuffer.append(TEXT_268);
    if (!isImplementation) {
    stringBuffer.append(TEXT_269);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_270);
    stringBuffer.append(genFeature.getListItemType(genClass));
    stringBuffer.append(TEXT_271);
    } else {
    stringBuffer.append(TEXT_272);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_273);
    stringBuffer.append(genFeature.getListItemType(genClass));
    stringBuffer.append(TEXT_274);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_275);
    }
    }
    if (genFeature.isGet() && (isImplementation || !genFeature.isSuppressedGetVisibility())) {
    if (isInterface) {
    stringBuffer.append(TEXT_276);
    stringBuffer.append(genFeature.getFormattedName());
    stringBuffer.append(TEXT_277);
    stringBuffer.append(genFeature.getFeatureKind());
    stringBuffer.append(TEXT_278);
    if (genFeature.isListType()) {
    if (genFeature.isMapType()) { GenFeature keyFeature = genFeature.getMapEntryTypeGenClass().getMapEntryKeyFeature(); GenFeature valueFeature = genFeature.getMapEntryTypeGenClass().getMapEntryValueFeature(); 
    stringBuffer.append(TEXT_279);
    if (keyFeature.isListType()) {
    stringBuffer.append(TEXT_280);
    stringBuffer.append(keyFeature.getQualifiedListItemType(genClass));
    stringBuffer.append(TEXT_281);
    } else {
    stringBuffer.append(TEXT_282);
    stringBuffer.append(keyFeature.getType(genClass));
    stringBuffer.append(TEXT_283);
    }
    stringBuffer.append(TEXT_284);
    if (valueFeature.isListType()) {
    stringBuffer.append(TEXT_285);
    stringBuffer.append(valueFeature.getQualifiedListItemType(genClass));
    stringBuffer.append(TEXT_286);
    } else {
    stringBuffer.append(TEXT_287);
    stringBuffer.append(valueFeature.getType(genClass));
    stringBuffer.append(TEXT_288);
    }
    stringBuffer.append(TEXT_289);
    } else if (!genFeature.isWrappedFeatureMapType() && !(genModel.isSuppressEMFMetaData() && "org.eclipse.emf.ecore.EObject".equals(genFeature.getQualifiedListItemType(genClass)))) {
String typeName = genFeature.getQualifiedListItemType(genClass); String head = typeName; String tail = ""; int index = typeName.indexOf('<'); if (index == -1) { index = typeName.indexOf('['); } 
if (index != -1) { head = typeName.substring(0, index); tail = typeName.substring(index).replaceAll("<", "&lt;"); }

    stringBuffer.append(TEXT_290);
    stringBuffer.append(head);
    stringBuffer.append(TEXT_291);
    stringBuffer.append(tail);
    stringBuffer.append(TEXT_292);
    }
    } else if (genFeature.isSetDefaultValue()) {
    stringBuffer.append(TEXT_293);
    stringBuffer.append(genFeature.getDefaultValue());
    stringBuffer.append(TEXT_294);
    }
    if (genFeature.getTypeGenEnum() != null) {
    stringBuffer.append(TEXT_295);
    stringBuffer.append(genFeature.getTypeGenEnum().getQualifiedName());
    stringBuffer.append(TEXT_296);
    }
    if (genFeature.isBidirectional() && !genFeature.getReverse().getGenClass().isMapEntry()) { GenFeature reverseGenFeature = genFeature.getReverse(); 
    if (!reverseGenFeature.isSuppressedGetVisibility()) {
    stringBuffer.append(TEXT_297);
    stringBuffer.append(reverseGenFeature.getGenClass().getQualifiedInterfaceName());
    stringBuffer.append(TEXT_298);
    stringBuffer.append(reverseGenFeature.getGetAccessor());
    stringBuffer.append(TEXT_299);
    stringBuffer.append(reverseGenFeature.getFormattedName());
    stringBuffer.append(TEXT_300);
    }
    }
    stringBuffer.append(TEXT_301);
    if (!genFeature.hasDocumentation()) {
    stringBuffer.append(TEXT_302);
    stringBuffer.append(genFeature.getFormattedName());
    stringBuffer.append(TEXT_303);
    stringBuffer.append(genFeature.getFeatureKind());
    stringBuffer.append(TEXT_304);
    }
    stringBuffer.append(TEXT_305);
    if (genFeature.hasDocumentation()) {
    stringBuffer.append(TEXT_306);
    stringBuffer.append(genFeature.getDocumentation(genModel.getIndentation(stringBuffer)));
    stringBuffer.append(TEXT_307);
    }
    stringBuffer.append(TEXT_308);
    stringBuffer.append(genFeature.getFormattedName());
    stringBuffer.append(TEXT_309);
    stringBuffer.append(genFeature.getFeatureKind());
    stringBuffer.append(TEXT_310);
    if (genFeature.getTypeGenEnum() != null) {
    stringBuffer.append(TEXT_311);
    stringBuffer.append(genFeature.getTypeGenEnum().getQualifiedName());
    }
    if (genFeature.isUnsettable()) {
    if (!genFeature.isSuppressedIsSetVisibility()) {
    stringBuffer.append(TEXT_312);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_313);
    }
    if (genFeature.isChangeable() && !genFeature.isSuppressedUnsetVisibility()) {
    stringBuffer.append(TEXT_314);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_315);
    }
    }
    if (genFeature.isChangeable() && !genFeature.isListType() && !genFeature.isSuppressedSetVisibility()) {
    stringBuffer.append(TEXT_316);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_317);
    stringBuffer.append(genFeature.getRawImportedBoundType());
    stringBuffer.append(TEXT_318);
    }
    if (!genModel.isSuppressEMFMetaData()) {
    stringBuffer.append(TEXT_319);
    stringBuffer.append(genPackage.getQualifiedPackageInterfaceName());
    stringBuffer.append(TEXT_320);
    stringBuffer.append(genFeature.getFeatureAccessorName());
    stringBuffer.append(TEXT_321);
    }
    if (genFeature.isBidirectional() && !genFeature.getReverse().getGenClass().isMapEntry()) { GenFeature reverseGenFeature = genFeature.getReverse(); 
    if (!reverseGenFeature.isSuppressedGetVisibility()) {
    stringBuffer.append(TEXT_322);
    stringBuffer.append(reverseGenFeature.getGenClass().getQualifiedInterfaceName());
    stringBuffer.append(TEXT_323);
    stringBuffer.append(reverseGenFeature.getGetAccessor());
    }
    }
    if (!genModel.isSuppressEMFModelTags()) { boolean first = true; for (StringTokenizer stringTokenizer = new StringTokenizer(genFeature.getModelInfo(), "\n\r"); stringTokenizer.hasMoreTokens(); ) { String modelInfo = stringTokenizer.nextToken(); if (first) { first = false;
    stringBuffer.append(TEXT_324);
    stringBuffer.append(modelInfo);
    } else {
    stringBuffer.append(TEXT_325);
    stringBuffer.append(modelInfo);
    }} if (first) {
    stringBuffer.append(TEXT_326);
    }}
    stringBuffer.append(TEXT_327);
    //Class/getGenFeature.javadoc.override.javajetinc
    } else {
    stringBuffer.append(TEXT_328);
    if (isJDK50) { //Class/getGenFeature.annotations.insert.javajetinc
    }
    }
    if (!isImplementation) {
    stringBuffer.append(TEXT_329);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_330);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_331);
    } else {
    if (genModel.useGenerics() && ((genFeature.isContainer() || genFeature.isResolveProxies()) && !genFeature.isListType() && !(genModel.isReflectiveDelegation() && genModel.isDynamicDelegation()) && genFeature.isUncheckedCast(genClass) || genFeature.isListType() && !genFeature.isFeatureMapType() && (genModel.isReflectiveDelegation() || genModel.isVirtualDelegation() || genModel.isDynamicDelegation()) || genFeature.isListDataType() && genFeature.hasDelegateFeature() || genFeature.isListType() && genFeature.hasSettingDelegate())) {
    stringBuffer.append(TEXT_332);
    }
    stringBuffer.append(TEXT_333);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_334);
    stringBuffer.append(genFeature.getGetAccessor());
    if (genClass.hasCollidingGetAccessorOperation(genFeature)) {
    stringBuffer.append(TEXT_335);
    }
    stringBuffer.append(TEXT_336);
    if (genModel.isDynamicDelegation()) {
    stringBuffer.append(TEXT_337);
    if (!isJDK50 && genFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_338);
    }
    stringBuffer.append(TEXT_339);
    stringBuffer.append(genFeature.getObjectType(genClass));
    stringBuffer.append(TEXT_340);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(TEXT_341);
    stringBuffer.append(genFeature.getQualifiedFeatureAccessor());
    stringBuffer.append(TEXT_342);
    stringBuffer.append(!genFeature.isEffectiveSuppressEMFTypes());
    stringBuffer.append(TEXT_343);
    if (!isJDK50 && genFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_344);
    stringBuffer.append(genFeature.getPrimitiveValueFunction());
    stringBuffer.append(TEXT_345);
    }
    stringBuffer.append(TEXT_346);
    } else if (genModel.isReflectiveDelegation()) {
    stringBuffer.append(TEXT_347);
    if (!isJDK50 && genFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_348);
    }
    stringBuffer.append(TEXT_349);
    stringBuffer.append(genFeature.getObjectType(genClass));
    stringBuffer.append(TEXT_350);
    stringBuffer.append(genFeature.getQualifiedFeatureAccessor());
    stringBuffer.append(TEXT_351);
    if (!isJDK50 && genFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_352);
    stringBuffer.append(genFeature.getPrimitiveValueFunction());
    stringBuffer.append(TEXT_353);
    }
    stringBuffer.append(TEXT_354);
    } else if (genFeature.hasSettingDelegate()) {
    stringBuffer.append(TEXT_355);
    if (!isJDK50 && genFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_356);
    }
    stringBuffer.append(TEXT_357);
    stringBuffer.append(genFeature.getObjectType(genClass));
    stringBuffer.append(TEXT_358);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_359);
    if (!isJDK50 && genFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_360);
    stringBuffer.append(genFeature.getPrimitiveValueFunction());
    stringBuffer.append(TEXT_361);
    }
    stringBuffer.append(TEXT_362);
    } else if (!genFeature.isVolatile()) {
    if (genFeature.isListType()) {
    if (genModel.isVirtualDelegation()) {
    stringBuffer.append(TEXT_363);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_364);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_365);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_366);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(positiveOffsetCorrection);
    stringBuffer.append(TEXT_367);
    }
    stringBuffer.append(TEXT_368);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_369);
    if (genModel.isVirtualDelegation()) {
    stringBuffer.append(TEXT_370);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(positiveOffsetCorrection);
    stringBuffer.append(TEXT_371);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_372);
    stringBuffer.append(genClass.getListConstructor(genFeature));
    stringBuffer.append(TEXT_373);
    } else {
    stringBuffer.append(TEXT_374);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_375);
    stringBuffer.append(genClass.getListConstructor(genFeature));
    stringBuffer.append(TEXT_376);
    }
    stringBuffer.append(TEXT_377);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(genFeature.isMapType() && genFeature.isEffectiveSuppressEMFTypes() ? ".map()" : "");
    stringBuffer.append(TEXT_378);
    } else if (genFeature.isContainer()) {
    stringBuffer.append(TEXT_379);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(positiveOffsetCorrection);
    stringBuffer.append(TEXT_380);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_381);
    } else {
    if (genFeature.isResolveProxies()) {
    if (genModel.isVirtualDelegation()) {
    stringBuffer.append(TEXT_382);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_383);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_384);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_385);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(positiveOffsetCorrection);
    if (genFeature.hasEDefault()) {
    stringBuffer.append(TEXT_386);
    stringBuffer.append(genFeature.getEDefault());
    }
    stringBuffer.append(TEXT_387);
    }
    stringBuffer.append(TEXT_388);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_389);
    stringBuffer.append(genFeature.getSafeNameAsEObject());
    stringBuffer.append(TEXT_390);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.InternalEObject"));
    stringBuffer.append(TEXT_391);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_392);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.InternalEObject"));
    stringBuffer.append(TEXT_393);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_394);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_395);
    stringBuffer.append(genFeature.getNonEObjectInternalTypeCast(genClass));
    stringBuffer.append(TEXT_396);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_397);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_398);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_399);
    if (genFeature.isEffectiveContains()) {
    stringBuffer.append(TEXT_400);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.InternalEObject"));
    stringBuffer.append(TEXT_401);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_402);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.InternalEObject"));
    stringBuffer.append(TEXT_403);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_404);
    if (!genFeature.isBidirectional()) {
    stringBuffer.append(TEXT_405);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.notify.NotificationChain"));
    stringBuffer.append(TEXT_406);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_407);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(negativeOffsetCorrection);
    stringBuffer.append(TEXT_408);
    } else { GenFeature reverseFeature = genFeature.getReverse(); GenClass targetClass = reverseFeature.getGenClass(); String reverseOffsetCorrection = targetClass.hasOffsetCorrection() ? " + " + genClass.getOffsetCorrectionField(genFeature) : "";
    stringBuffer.append(TEXT_409);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.notify.NotificationChain"));
    stringBuffer.append(TEXT_410);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_411);
    stringBuffer.append(targetClass.getQualifiedFeatureID(reverseFeature));
    stringBuffer.append(reverseOffsetCorrection);
    stringBuffer.append(TEXT_412);
    stringBuffer.append(targetClass.getRawImportedInterfaceName());
    stringBuffer.append(TEXT_413);
    }
    stringBuffer.append(TEXT_414);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_415);
    if (!genFeature.isBidirectional()) {
    stringBuffer.append(TEXT_416);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_417);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(negativeOffsetCorrection);
    stringBuffer.append(TEXT_418);
    } else { GenFeature reverseFeature = genFeature.getReverse(); GenClass targetClass = reverseFeature.getGenClass(); String reverseOffsetCorrection = targetClass.hasOffsetCorrection() ? " + " + genClass.getOffsetCorrectionField(genFeature) : "";
    stringBuffer.append(TEXT_419);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_420);
    stringBuffer.append(targetClass.getQualifiedFeatureID(reverseFeature));
    stringBuffer.append(reverseOffsetCorrection);
    stringBuffer.append(TEXT_421);
    stringBuffer.append(targetClass.getRawImportedInterfaceName());
    stringBuffer.append(TEXT_422);
    }
    stringBuffer.append(TEXT_423);
    } else if (genModel.isVirtualDelegation()) {
    stringBuffer.append(TEXT_424);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(positiveOffsetCorrection);
    stringBuffer.append(TEXT_425);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_426);
    }
    if (!genModel.isSuppressNotification()) {
    stringBuffer.append(TEXT_427);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.impl.ENotificationImpl"));
    stringBuffer.append(TEXT_428);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.notify.Notification"));
    stringBuffer.append(TEXT_429);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(positiveOffsetCorrection);
    stringBuffer.append(TEXT_430);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_431);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_432);
    }
    stringBuffer.append(TEXT_433);
    }
    if (!genFeature.isResolveProxies() && genModel.isVirtualDelegation() && !genFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_434);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_435);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(positiveOffsetCorrection);
    if (genFeature.hasEDefault()) {
    stringBuffer.append(TEXT_436);
    stringBuffer.append(genFeature.getEDefault());
    }
    stringBuffer.append(TEXT_437);
    } else if (genClass.isFlag(genFeature)) {
    if (genFeature.isBooleanType()) {
    stringBuffer.append(TEXT_438);
    stringBuffer.append(genClass.getFlagsField(genFeature));
    stringBuffer.append(TEXT_439);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_440);
    } else {
    stringBuffer.append(TEXT_441);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_442);
    stringBuffer.append(genClass.getFlagsField(genFeature));
    stringBuffer.append(TEXT_443);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_444);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_445);
    }
    } else {
    stringBuffer.append(TEXT_446);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_447);
    }
    }
    } else {//volatile
    if (genFeature.isResolveProxies() && !genFeature.isListType()) {
    stringBuffer.append(TEXT_448);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_449);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_450);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_451);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_452);
    stringBuffer.append(genFeature.getSafeNameAsEObject());
    stringBuffer.append(TEXT_453);
    stringBuffer.append(genFeature.getNonEObjectInternalTypeCast(genClass));
    stringBuffer.append(TEXT_454);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.InternalEObject"));
    stringBuffer.append(TEXT_455);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_456);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_457);
    } else if (genFeature.hasDelegateFeature()) { GenFeature delegateFeature = genFeature.getDelegateFeature();
    if (genFeature.isFeatureMapType()) {
    String featureMapEntryTemplateArgument = isJDK50 ? "<" + genModel.getImportedName("org.eclipse.emf.ecore.util.FeatureMap") + ".Entry>" : "";
    if (delegateFeature.isWrappedFeatureMapType()) {
    stringBuffer.append(TEXT_458);
    stringBuffer.append(genFeature.getImportedEffectiveFeatureMapWrapperClass());
    stringBuffer.append(TEXT_459);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.FeatureMap"));
    stringBuffer.append(TEXT_460);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.FeatureMap"));
    stringBuffer.append(TEXT_461);
    stringBuffer.append(delegateFeature.getAccessorName());
    stringBuffer.append(TEXT_462);
    stringBuffer.append(featureMapEntryTemplateArgument);
    stringBuffer.append(TEXT_463);
    stringBuffer.append(genFeature.getQualifiedFeatureAccessor());
    stringBuffer.append(TEXT_464);
    } else {
    stringBuffer.append(TEXT_465);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.FeatureMap"));
    stringBuffer.append(TEXT_466);
    stringBuffer.append(delegateFeature.getAccessorName());
    stringBuffer.append(TEXT_467);
    stringBuffer.append(featureMapEntryTemplateArgument);
    stringBuffer.append(TEXT_468);
    stringBuffer.append(genFeature.getQualifiedFeatureAccessor());
    stringBuffer.append(TEXT_469);
    }
    } else if (genFeature.isListType()) {
    if (delegateFeature.isWrappedFeatureMapType()) {
    stringBuffer.append(TEXT_470);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.FeatureMap"));
    stringBuffer.append(TEXT_471);
    stringBuffer.append(delegateFeature.getAccessorName());
    stringBuffer.append(TEXT_472);
    stringBuffer.append(genFeature.getQualifiedFeatureAccessor());
    stringBuffer.append(TEXT_473);
    } else {
    stringBuffer.append(TEXT_474);
    stringBuffer.append(delegateFeature.getAccessorName());
    stringBuffer.append(TEXT_475);
    stringBuffer.append(genFeature.getQualifiedFeatureAccessor());
    stringBuffer.append(TEXT_476);
    }
    } else {
    if (delegateFeature.isWrappedFeatureMapType()) {
    stringBuffer.append(TEXT_477);
    if (!isJDK50 && genFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_478);
    }
    if (genFeature.getTypeGenDataType() == null || !genFeature.getTypeGenDataType().isObjectType()) {
    stringBuffer.append(TEXT_479);
    stringBuffer.append(genFeature.getObjectType(genClass));
    stringBuffer.append(TEXT_480);
    }
    stringBuffer.append(TEXT_481);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.FeatureMap"));
    stringBuffer.append(TEXT_482);
    stringBuffer.append(delegateFeature.getAccessorName());
    stringBuffer.append(TEXT_483);
    stringBuffer.append(genFeature.getQualifiedFeatureAccessor());
    stringBuffer.append(TEXT_484);
    if (!isJDK50 && genFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_485);
    stringBuffer.append(genFeature.getPrimitiveValueFunction());
    stringBuffer.append(TEXT_486);
    }
    stringBuffer.append(TEXT_487);
    } else {
    stringBuffer.append(TEXT_488);
    if (!isJDK50 && genFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_489);
    }
    if (genFeature.getTypeGenDataType() == null || !genFeature.getTypeGenDataType().isObjectType()) {
    stringBuffer.append(TEXT_490);
    stringBuffer.append(genFeature.getObjectType(genClass));
    stringBuffer.append(TEXT_491);
    }
    stringBuffer.append(TEXT_492);
    stringBuffer.append(delegateFeature.getAccessorName());
    stringBuffer.append(TEXT_493);
    stringBuffer.append(genFeature.getQualifiedFeatureAccessor());
    stringBuffer.append(TEXT_494);
    if (!isJDK50 && genFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_495);
    stringBuffer.append(genFeature.getPrimitiveValueFunction());
    stringBuffer.append(TEXT_496);
    }
    stringBuffer.append(TEXT_497);
    }
    }
    } else if (genClass.getGetAccessorOperation(genFeature) != null) {
    stringBuffer.append(TEXT_498);
    stringBuffer.append(genClass.getGetAccessorOperation(genFeature).getBody(genModel.getIndentation(stringBuffer)));
    } else {
    stringBuffer.append(TEXT_499);
    stringBuffer.append(genFeature.getFormattedName());
    stringBuffer.append(TEXT_500);
    stringBuffer.append(genFeature.getFeatureKind());
    stringBuffer.append(TEXT_501);
    if (genFeature.isListType()) {
    stringBuffer.append(TEXT_502);
    if (genFeature.isMapType()) {
    stringBuffer.append(TEXT_503);
    } else if (genFeature.isFeatureMapType()) {
    stringBuffer.append(TEXT_504);
    } else {
    stringBuffer.append(TEXT_505);
    }
    stringBuffer.append(TEXT_506);
    }
    stringBuffer.append(TEXT_507);
    //Class/getGenFeature.todo.override.javajetinc
    }
    }
    stringBuffer.append(TEXT_508);
    }
    //Class/getGenFeature.override.javajetinc
    }
    if (isImplementation && !genModel.isReflectiveDelegation() && genFeature.isBasicGet()) {
    stringBuffer.append(TEXT_509);
    if (isJDK50) { //Class/basicGetGenFeature.annotations.insert.javajetinc
    }
    stringBuffer.append(TEXT_510);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_511);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_512);
    if (genModel.isDynamicDelegation()) {
    stringBuffer.append(TEXT_513);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_514);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(TEXT_515);
    stringBuffer.append(genFeature.getQualifiedFeatureAccessor());
    stringBuffer.append(TEXT_516);
    stringBuffer.append(!genFeature.isEffectiveSuppressEMFTypes());
    stringBuffer.append(TEXT_517);
    } else if (genFeature.hasSettingDelegate()) {
    stringBuffer.append(TEXT_518);
    if (!isJDK50 && genFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_519);
    }
    stringBuffer.append(TEXT_520);
    stringBuffer.append(genFeature.getObjectType(genClass));
    stringBuffer.append(TEXT_521);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_522);
    if (!isJDK50 && genFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_523);
    stringBuffer.append(genFeature.getPrimitiveValueFunction());
    stringBuffer.append(TEXT_524);
    }
    stringBuffer.append(TEXT_525);
    } else if (genFeature.isContainer()) {
    stringBuffer.append(TEXT_526);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(positiveOffsetCorrection);
    stringBuffer.append(TEXT_527);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_528);
    } else if (!genFeature.isVolatile()) {
    if (genModel.isVirtualDelegation()) {
    stringBuffer.append(TEXT_529);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_530);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(positiveOffsetCorrection);
    stringBuffer.append(TEXT_531);
    } else {
    stringBuffer.append(TEXT_532);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_533);
    }
    } else if (genFeature.hasDelegateFeature()) { GenFeature delegateFeature = genFeature.getDelegateFeature();
    if (delegateFeature.isWrappedFeatureMapType()) {
    stringBuffer.append(TEXT_534);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_535);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.FeatureMap"));
    stringBuffer.append(TEXT_536);
    stringBuffer.append(delegateFeature.getAccessorName());
    stringBuffer.append(TEXT_537);
    stringBuffer.append(genFeature.getQualifiedFeatureAccessor());
    stringBuffer.append(TEXT_538);
    } else {
    stringBuffer.append(TEXT_539);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_540);
    stringBuffer.append(delegateFeature.getAccessorName());
    stringBuffer.append(TEXT_541);
    stringBuffer.append(genFeature.getQualifiedFeatureAccessor());
    stringBuffer.append(TEXT_542);
    }
    } else {
    stringBuffer.append(TEXT_543);
    stringBuffer.append(genFeature.getFormattedName());
    stringBuffer.append(TEXT_544);
    stringBuffer.append(genFeature.getFeatureKind());
    stringBuffer.append(TEXT_545);
    //Class/basicGetGenFeature.todo.override.javajetinc
    }
    stringBuffer.append(TEXT_546);
    //Class/basicGetGenFeature.override.javajetinc
    }
    if (isImplementation && !genModel.isReflectiveDelegation() && genFeature.isBasicSet()) {
    stringBuffer.append(TEXT_547);
    if (isJDK50) { //Class/basicSetGenFeature.annotations.insert.javajetinc
    }
    stringBuffer.append(TEXT_548);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.notify.NotificationChain"));
    stringBuffer.append(TEXT_549);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_550);
    stringBuffer.append(genFeature.getImportedInternalType(genClass));
    stringBuffer.append(TEXT_551);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_552);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.notify.NotificationChain"));
    stringBuffer.append(TEXT_553);
    if (genFeature.isContainer()) {
    stringBuffer.append(TEXT_554);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.InternalEObject"));
    stringBuffer.append(TEXT_555);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_556);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(positiveOffsetCorrection);
    stringBuffer.append(TEXT_557);
    stringBuffer.append(TEXT_558);
    } else if (genModel.isDynamicDelegation()) {
    stringBuffer.append(TEXT_559);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.InternalEObject"));
    stringBuffer.append(TEXT_560);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_561);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(positiveOffsetCorrection);
    stringBuffer.append(TEXT_562);
    stringBuffer.append(TEXT_563);
    } else if (!genFeature.isVolatile()) {
    if (genModel.isVirtualDelegation()) {
    stringBuffer.append(TEXT_564);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_565);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(positiveOffsetCorrection);
    stringBuffer.append(TEXT_566);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_567);
    } else {
    stringBuffer.append(TEXT_568);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_569);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_570);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_571);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_572);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_573);
    }
    if (genFeature.isUnsettable()) {
    if (genModel.isVirtualDelegation()) {
    if (!genModel.isSuppressNotification()) {
    stringBuffer.append(TEXT_574);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_575);
    }
    } else if (genClass.isESetFlag(genFeature)) {
    stringBuffer.append(TEXT_576);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_577);
    stringBuffer.append(genClass.getESetFlagsField(genFeature));
    stringBuffer.append(TEXT_578);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_579);
    if (!genModel.isSuppressNotification()) {
    stringBuffer.append(TEXT_580);
    stringBuffer.append(genClass.getESetFlagsField(genFeature));
    stringBuffer.append(TEXT_581);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_582);
    }
    } else {
    if (!genModel.isSuppressNotification()) {
    stringBuffer.append(TEXT_583);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_584);
    stringBuffer.append(genFeature.getUncapName());
    stringBuffer.append(TEXT_585);
    }
    stringBuffer.append(TEXT_586);
    stringBuffer.append(genFeature.getUncapName());
    stringBuffer.append(TEXT_587);
    }
    }
    if (!genModel.isSuppressNotification()) {
    stringBuffer.append(TEXT_588);
    if (genFeature.isUnsettable()) {
    stringBuffer.append(TEXT_589);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.impl.ENotificationImpl"));
    stringBuffer.append(TEXT_590);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.impl.ENotificationImpl"));
    stringBuffer.append(TEXT_591);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.notify.Notification"));
    stringBuffer.append(TEXT_592);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(positiveOffsetCorrection);
    stringBuffer.append(TEXT_593);
    if (genModel.isVirtualDelegation()) {
    stringBuffer.append(TEXT_594);
    stringBuffer.append(genFeature.getCapName());
    } else {
    stringBuffer.append(TEXT_595);
    stringBuffer.append(genFeature.getCapName());
    }
    stringBuffer.append(TEXT_596);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_597);
    if (genModel.isVirtualDelegation()) {
    stringBuffer.append(TEXT_598);
    } else {
    stringBuffer.append(TEXT_599);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_600);
    }
    stringBuffer.append(TEXT_601);
    } else {
    stringBuffer.append(TEXT_602);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.impl.ENotificationImpl"));
    stringBuffer.append(TEXT_603);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.impl.ENotificationImpl"));
    stringBuffer.append(TEXT_604);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.notify.Notification"));
    stringBuffer.append(TEXT_605);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(positiveOffsetCorrection);
    stringBuffer.append(TEXT_606);
    if (genModel.isVirtualDelegation()) {
    stringBuffer.append(TEXT_607);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_608);
    stringBuffer.append(genFeature.getCapName());
    } else {
    stringBuffer.append(TEXT_609);
    stringBuffer.append(genFeature.getCapName());
    }
    stringBuffer.append(TEXT_610);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_611);
    }
    stringBuffer.append(TEXT_612);
    }
    stringBuffer.append(TEXT_613);
    } else if (genFeature.hasDelegateFeature()) { GenFeature delegateFeature = genFeature.getDelegateFeature();
    if (delegateFeature.isWrappedFeatureMapType()) {
    stringBuffer.append(TEXT_614);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.FeatureMap"));
    stringBuffer.append(TEXT_615);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.FeatureMap"));
    stringBuffer.append(TEXT_616);
    stringBuffer.append(delegateFeature.getAccessorName());
    stringBuffer.append(TEXT_617);
    stringBuffer.append(genFeature.getQualifiedFeatureAccessor());
    stringBuffer.append(TEXT_618);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_619);
    } else {
    stringBuffer.append(TEXT_620);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.FeatureMap"));
    stringBuffer.append(TEXT_621);
    stringBuffer.append(delegateFeature.getAccessorName());
    stringBuffer.append(TEXT_622);
    stringBuffer.append(genFeature.getQualifiedFeatureAccessor());
    stringBuffer.append(TEXT_623);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_624);
    }
    } else {
    stringBuffer.append(TEXT_625);
    stringBuffer.append(genFeature.getFormattedName());
    stringBuffer.append(TEXT_626);
    stringBuffer.append(genFeature.getFeatureKind());
    stringBuffer.append(TEXT_627);
    //Class/basicSetGenFeature.todo.override.javajetinc
    }
    stringBuffer.append(TEXT_628);
    //Class/basicSetGenFeature.override.javajetinc
    }
    if (genFeature.isSet() && (isImplementation || !genFeature.isSuppressedSetVisibility())) {
    if (isInterface) { 
    stringBuffer.append(TEXT_629);
    stringBuffer.append(genClass.getQualifiedInterfaceName());
    stringBuffer.append(TEXT_630);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_631);
    stringBuffer.append(genFeature.getFormattedName());
    stringBuffer.append(TEXT_632);
    stringBuffer.append(genFeature.getFeatureKind());
    stringBuffer.append(TEXT_633);
    stringBuffer.append(TEXT_634);
    stringBuffer.append(genFeature.getFormattedName());
    stringBuffer.append(TEXT_635);
    stringBuffer.append(genFeature.getFeatureKind());
    stringBuffer.append(TEXT_636);
    if (genFeature.isEnumType()) {
    stringBuffer.append(TEXT_637);
    stringBuffer.append(genFeature.getTypeGenEnum().getQualifiedName());
    }
    if (genFeature.isUnsettable()) {
    if (!genFeature.isSuppressedIsSetVisibility()) {
    stringBuffer.append(TEXT_638);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_639);
    }
    if (!genFeature.isSuppressedUnsetVisibility()) {
    stringBuffer.append(TEXT_640);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_641);
    }
    }
    stringBuffer.append(TEXT_642);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_643);
    //Class/setGenFeature.javadoc.override.javajetinc
    } else {
    stringBuffer.append(TEXT_644);
    if (isJDK50) { //Class/setGenFeature.annotations.insert.javajetinc
    }
    }
    if (!isImplementation) { 
    stringBuffer.append(TEXT_645);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_646);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_647);
    } else { GenOperation setAccessorOperation = genClass.getSetAccessorOperation(genFeature);
    stringBuffer.append(TEXT_648);
    stringBuffer.append(genFeature.getAccessorName());
    if (genClass.hasCollidingSetAccessorOperation(genFeature)) {
    stringBuffer.append(TEXT_649);
    }
    stringBuffer.append(TEXT_650);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_651);
    stringBuffer.append(setAccessorOperation == null ? "new" + genFeature.getCapName() : setAccessorOperation.getGenParameters().get(0).getName());
    stringBuffer.append(TEXT_652);
    if (genModel.isDynamicDelegation()) {
    stringBuffer.append(TEXT_653);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(TEXT_654);
    stringBuffer.append(genFeature.getQualifiedFeatureAccessor());
    stringBuffer.append(TEXT_655);
    if (!isJDK50 && genFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_656);
    stringBuffer.append(genFeature.getObjectType(genClass));
    stringBuffer.append(TEXT_657);
    }
    stringBuffer.append(TEXT_658);
    stringBuffer.append(genFeature.getCapName());
    if (!isJDK50 && genFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_659);
    }
    stringBuffer.append(TEXT_660);
    } else if (genModel.isReflectiveDelegation()) {
    stringBuffer.append(TEXT_661);
    stringBuffer.append(genFeature.getQualifiedFeatureAccessor());
    stringBuffer.append(TEXT_662);
    if (!isJDK50 && genFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_663);
    stringBuffer.append(genFeature.getObjectType(genClass));
    stringBuffer.append(TEXT_664);
    }
    stringBuffer.append(TEXT_665);
    stringBuffer.append(genFeature.getCapName());
    if (!isJDK50 && genFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_666);
    }
    stringBuffer.append(TEXT_667);
    } else if (genFeature.hasSettingDelegate()) {
    stringBuffer.append(TEXT_668);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_669);
    if (!isJDK50 && genFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_670);
    stringBuffer.append(genFeature.getObjectType(genClass));
    stringBuffer.append(TEXT_671);
    }
    stringBuffer.append(TEXT_672);
    stringBuffer.append(genFeature.getCapName());
    if (!isJDK50 && genFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_673);
    }
    stringBuffer.append(TEXT_674);
    } else if (!genFeature.isVolatile()) {
    if (genFeature.isContainer()) { GenFeature reverseFeature = genFeature.getReverse(); GenClass targetClass = reverseFeature.getGenClass(); String reverseOffsetCorrection = targetClass.hasOffsetCorrection() ? " + " + genClass.getOffsetCorrectionField(genFeature) : "";
    stringBuffer.append(TEXT_675);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_676);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(positiveOffsetCorrection);
    stringBuffer.append(TEXT_677);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_678);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.EcoreUtil"));
    stringBuffer.append(TEXT_679);
    stringBuffer.append(genFeature.getEObjectCast());
    stringBuffer.append(TEXT_680);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_681);
    stringBuffer.append(genModel.getImportedName("java.lang.IllegalArgumentException"));
    stringBuffer.append(TEXT_682);
    stringBuffer.append(genModel.getNonNLS());
    stringBuffer.append(TEXT_683);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.notify.NotificationChain"));
    stringBuffer.append(TEXT_684);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_685);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.InternalEObject"));
    stringBuffer.append(TEXT_686);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_687);
    stringBuffer.append(targetClass.getQualifiedFeatureID(reverseFeature));
    stringBuffer.append(reverseOffsetCorrection);
    stringBuffer.append(TEXT_688);
    stringBuffer.append(targetClass.getRawImportedInterfaceName());
    stringBuffer.append(TEXT_689);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_690);
    stringBuffer.append(genFeature.getInternalTypeCast());
    stringBuffer.append(TEXT_691);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_692);
    if (!genModel.isSuppressNotification()) {
    stringBuffer.append(TEXT_693);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.impl.ENotificationImpl"));
    stringBuffer.append(TEXT_694);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.notify.Notification"));
    stringBuffer.append(TEXT_695);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(positiveOffsetCorrection);
    stringBuffer.append(TEXT_696);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_697);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_698);
    }
    } else if (genFeature.isBidirectional() || genFeature.isEffectiveContains()) {
    if (genModel.isVirtualDelegation()) {
    stringBuffer.append(TEXT_699);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_700);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_701);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_702);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(positiveOffsetCorrection);
    stringBuffer.append(TEXT_703);
    }
    stringBuffer.append(TEXT_704);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_705);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_706);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.notify.NotificationChain"));
    stringBuffer.append(TEXT_707);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_708);
    if (!genFeature.isBidirectional()) {
    stringBuffer.append(TEXT_709);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.InternalEObject"));
    stringBuffer.append(TEXT_710);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_711);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(negativeOffsetCorrection);
    stringBuffer.append(TEXT_712);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_713);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.InternalEObject"));
    stringBuffer.append(TEXT_714);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_715);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(negativeOffsetCorrection);
    stringBuffer.append(TEXT_716);
    } else { GenFeature reverseFeature = genFeature.getReverse(); GenClass targetClass = reverseFeature.getGenClass(); String reverseOffsetCorrection = targetClass.hasOffsetCorrection() ? " + " + genClass.getOffsetCorrectionField(genFeature) : "";
    stringBuffer.append(TEXT_717);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.InternalEObject"));
    stringBuffer.append(TEXT_718);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_719);
    stringBuffer.append(targetClass.getQualifiedFeatureID(reverseFeature));
    stringBuffer.append(reverseOffsetCorrection);
    stringBuffer.append(TEXT_720);
    stringBuffer.append(targetClass.getRawImportedInterfaceName());
    stringBuffer.append(TEXT_721);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_722);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.InternalEObject"));
    stringBuffer.append(TEXT_723);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_724);
    stringBuffer.append(targetClass.getQualifiedFeatureID(reverseFeature));
    stringBuffer.append(reverseOffsetCorrection);
    stringBuffer.append(TEXT_725);
    stringBuffer.append(targetClass.getRawImportedInterfaceName());
    stringBuffer.append(TEXT_726);
    }
    stringBuffer.append(TEXT_727);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_728);
    stringBuffer.append(genFeature.getInternalTypeCast());
    stringBuffer.append(TEXT_729);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_730);
    if (genFeature.isUnsettable()) {
    stringBuffer.append(TEXT_731);
    if (genModel.isVirtualDelegation()) {
    stringBuffer.append(TEXT_732);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_733);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(positiveOffsetCorrection);
    stringBuffer.append(TEXT_734);
    } else if (genClass.isESetFlag(genFeature)) {
    if (!genModel.isSuppressNotification()) {
    stringBuffer.append(TEXT_735);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_736);
    stringBuffer.append(genClass.getESetFlagsField(genFeature));
    stringBuffer.append(TEXT_737);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_738);
    }
    stringBuffer.append(TEXT_739);
    stringBuffer.append(genClass.getESetFlagsField(genFeature));
    stringBuffer.append(TEXT_740);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_741);
    } else {
    if (!genModel.isSuppressNotification()) {
    stringBuffer.append(TEXT_742);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_743);
    stringBuffer.append(genFeature.getUncapName());
    stringBuffer.append(TEXT_744);
    }
    stringBuffer.append(TEXT_745);
    stringBuffer.append(genFeature.getUncapName());
    stringBuffer.append(TEXT_746);
    }
    if (!genModel.isSuppressNotification()) {
    stringBuffer.append(TEXT_747);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.impl.ENotificationImpl"));
    stringBuffer.append(TEXT_748);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.notify.Notification"));
    stringBuffer.append(TEXT_749);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(positiveOffsetCorrection);
    stringBuffer.append(TEXT_750);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_751);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_752);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_753);
    }
    stringBuffer.append(TEXT_754);
    } else {
    if (!genModel.isSuppressNotification()) {
    stringBuffer.append(TEXT_755);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.impl.ENotificationImpl"));
    stringBuffer.append(TEXT_756);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.notify.Notification"));
    stringBuffer.append(TEXT_757);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(positiveOffsetCorrection);
    stringBuffer.append(TEXT_758);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_759);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_760);
    }
    }
    } else {
    if (genClass.isFlag(genFeature)) {
    if (!genModel.isSuppressNotification()) {
    if (genFeature.isBooleanType()) {
    stringBuffer.append(TEXT_761);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_762);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_763);
    stringBuffer.append(genClass.getFlagsField(genFeature));
    stringBuffer.append(TEXT_764);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_765);
    } else {
    stringBuffer.append(TEXT_766);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_767);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_768);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_769);
    stringBuffer.append(genClass.getFlagsField(genFeature));
    stringBuffer.append(TEXT_770);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_771);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_772);
    }
    }
    if (genFeature.isBooleanType()) {
    stringBuffer.append(TEXT_773);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_774);
    stringBuffer.append(genClass.getFlagsField(genFeature));
    stringBuffer.append(TEXT_775);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_776);
    stringBuffer.append(genClass.getFlagsField(genFeature));
    stringBuffer.append(TEXT_777);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_778);
    } else {
    stringBuffer.append(TEXT_779);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_780);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_781);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_782);
    stringBuffer.append(genClass.getFlagsField(genFeature));
    stringBuffer.append(TEXT_783);
    stringBuffer.append(genClass.getFlagsField(genFeature));
    stringBuffer.append(TEXT_784);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_785);
    if (isJDK50) {
    stringBuffer.append(TEXT_786);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_787);
    } else {
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_788);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_789);
    }
    stringBuffer.append(TEXT_790);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_791);
    }
    } else {
    if (!genModel.isVirtualDelegation() || genFeature.isPrimitiveType()) {
    if (!genModel.isSuppressNotification()) {
    stringBuffer.append(TEXT_792);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_793);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_794);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_795);
    }
    }
    if (genFeature.isEnumType()) {
    if (genModel.isVirtualDelegation()) {
    stringBuffer.append(TEXT_796);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_797);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_798);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_799);
    stringBuffer.append(genFeature.getEDefault());
    stringBuffer.append(TEXT_800);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_801);
    } else {
    stringBuffer.append(TEXT_802);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_803);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_804);
    stringBuffer.append(genFeature.getEDefault());
    stringBuffer.append(TEXT_805);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_806);
    }
    } else {
    if (genModel.isVirtualDelegation() && !genFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_807);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_808);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_809);
    stringBuffer.append(genFeature.getInternalTypeCast());
    stringBuffer.append(TEXT_810);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_811);
    } else {
    stringBuffer.append(TEXT_812);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_813);
    stringBuffer.append(genFeature.getInternalTypeCast());
    stringBuffer.append(TEXT_814);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_815);
    }
    }
    if (genModel.isVirtualDelegation() && !genFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_816);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_817);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(positiveOffsetCorrection);
    stringBuffer.append(TEXT_818);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_819);
    }
    }
    if (genFeature.isUnsettable()) {
    if (genModel.isVirtualDelegation() && !genFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_820);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_821);
    } else if (genClass.isESetFlag(genFeature)) {
    if (!genModel.isSuppressNotification()) {
    stringBuffer.append(TEXT_822);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_823);
    stringBuffer.append(genClass.getESetFlagsField(genFeature));
    stringBuffer.append(TEXT_824);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_825);
    }
    stringBuffer.append(TEXT_826);
    stringBuffer.append(genClass.getESetFlagsField(genFeature));
    stringBuffer.append(TEXT_827);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_828);
    } else {
    if (!genModel.isSuppressNotification()) {
    stringBuffer.append(TEXT_829);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_830);
    stringBuffer.append(genFeature.getUncapName());
    stringBuffer.append(TEXT_831);
    }
    stringBuffer.append(TEXT_832);
    stringBuffer.append(genFeature.getUncapName());
    stringBuffer.append(TEXT_833);
    }
    if (!genModel.isSuppressNotification()) {
    stringBuffer.append(TEXT_834);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.impl.ENotificationImpl"));
    stringBuffer.append(TEXT_835);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.notify.Notification"));
    stringBuffer.append(TEXT_836);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(positiveOffsetCorrection);
    stringBuffer.append(TEXT_837);
    if (genModel.isVirtualDelegation() && !genFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_838);
    stringBuffer.append(genFeature.getEDefault());
    stringBuffer.append(TEXT_839);
    stringBuffer.append(genFeature.getCapName());
    } else {
    stringBuffer.append(TEXT_840);
    stringBuffer.append(genFeature.getCapName());
    }
    stringBuffer.append(TEXT_841);
    if (genClass.isFlag(genFeature)) {
    stringBuffer.append(TEXT_842);
    stringBuffer.append(genFeature.getCapName());
    } else {
    stringBuffer.append(genFeature.getSafeName());
    }
    stringBuffer.append(TEXT_843);
    if (genModel.isVirtualDelegation() && !genFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_844);
    } else {
    stringBuffer.append(TEXT_845);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_846);
    }
    stringBuffer.append(TEXT_847);
    }
    } else {
    if (!genModel.isSuppressNotification()) {
    stringBuffer.append(TEXT_848);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.impl.ENotificationImpl"));
    stringBuffer.append(TEXT_849);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.notify.Notification"));
    stringBuffer.append(TEXT_850);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(positiveOffsetCorrection);
    stringBuffer.append(TEXT_851);
    if (genModel.isVirtualDelegation() && !genFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_852);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_853);
    stringBuffer.append(genFeature.getEDefault());
    stringBuffer.append(TEXT_854);
    stringBuffer.append(genFeature.getCapName());
    } else {
    stringBuffer.append(TEXT_855);
    stringBuffer.append(genFeature.getCapName());
    }
    stringBuffer.append(TEXT_856);
    if (genClass.isFlag(genFeature)) {
    stringBuffer.append(TEXT_857);
    stringBuffer.append(genFeature.getCapName());
    } else {
    stringBuffer.append(genFeature.getSafeName());
    }
    stringBuffer.append(TEXT_858);
    }
    }
    }
    } else if (genFeature.hasDelegateFeature()) { GenFeature delegateFeature = genFeature.getDelegateFeature();
    if (delegateFeature.isWrappedFeatureMapType()) {
    stringBuffer.append(TEXT_859);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.FeatureMap"));
    stringBuffer.append(TEXT_860);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.FeatureMap"));
    stringBuffer.append(TEXT_861);
    stringBuffer.append(delegateFeature.getAccessorName());
    stringBuffer.append(TEXT_862);
    stringBuffer.append(genFeature.getQualifiedFeatureAccessor());
    stringBuffer.append(TEXT_863);
    if (!isJDK50 && genFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_864);
    stringBuffer.append(genFeature.getObjectType(genClass));
    stringBuffer.append(TEXT_865);
    }
    stringBuffer.append(TEXT_866);
    stringBuffer.append(genFeature.getCapName());
    if (!isJDK50 && genFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_867);
    }
    stringBuffer.append(TEXT_868);
    } else {
    stringBuffer.append(TEXT_869);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.FeatureMap"));
    stringBuffer.append(TEXT_870);
    stringBuffer.append(delegateFeature.getAccessorName());
    stringBuffer.append(TEXT_871);
    stringBuffer.append(genFeature.getQualifiedFeatureAccessor());
    stringBuffer.append(TEXT_872);
    if (!isJDK50 && genFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_873);
    stringBuffer.append(genFeature.getObjectType(genClass));
    stringBuffer.append(TEXT_874);
    }
    stringBuffer.append(TEXT_875);
    stringBuffer.append(genFeature.getCapName());
    if (!isJDK50 && genFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_876);
    }
    stringBuffer.append(TEXT_877);
    }
    } else if (setAccessorOperation != null) {
    stringBuffer.append(TEXT_878);
    stringBuffer.append(setAccessorOperation.getBody(genModel.getIndentation(stringBuffer)));
    } else {
    stringBuffer.append(TEXT_879);
    stringBuffer.append(genFeature.getFormattedName());
    stringBuffer.append(TEXT_880);
    stringBuffer.append(genFeature.getFeatureKind());
    stringBuffer.append(TEXT_881);
    //Class/setGenFeature.todo.override.javajetinc
    }
    stringBuffer.append(TEXT_882);
    }
    //Class/setGenFeature.override.javajetinc
    }
    if (isImplementation && !genModel.isReflectiveDelegation() && genFeature.isBasicUnset()) {
    stringBuffer.append(TEXT_883);
    if (isJDK50) { //Class/basicUnsetGenFeature.annotations.insert.javajetinc
    }
    stringBuffer.append(TEXT_884);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.notify.NotificationChain"));
    stringBuffer.append(TEXT_885);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_886);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.notify.NotificationChain"));
    stringBuffer.append(TEXT_887);
    if (genModel.isDynamicDelegation()) {
    stringBuffer.append(TEXT_888);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.InternalEObject"));
    stringBuffer.append(TEXT_889);
    if (genFeature.isResolveProxies()) {
    stringBuffer.append(TEXT_890);
    stringBuffer.append(genFeature.getAccessorName());
    } else {
    stringBuffer.append(genFeature.getGetAccessor());
    }
    stringBuffer.append(TEXT_891);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(positiveOffsetCorrection);
    stringBuffer.append(TEXT_892);
    } else if (!genFeature.isVolatile()) {
    if (genModel.isVirtualDelegation()) {
    if (!genModel.isSuppressNotification()) {
    stringBuffer.append(TEXT_893);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_894);
    }
    stringBuffer.append(TEXT_895);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(positiveOffsetCorrection);
    stringBuffer.append(TEXT_896);
    } else {
    if (!genModel.isSuppressNotification()) {
    stringBuffer.append(TEXT_897);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_898);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_899);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_900);
    }
    stringBuffer.append(TEXT_901);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_902);
    }
    if (genModel.isVirtualDelegation()) {
    if (!genModel.isSuppressNotification()) {
    stringBuffer.append(TEXT_903);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_904);
    }
    } else if (genClass.isESetFlag(genFeature)) {
    if (!genModel.isSuppressNotification()) {
    stringBuffer.append(TEXT_905);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_906);
    stringBuffer.append(genClass.getESetFlagsField(genFeature));
    stringBuffer.append(TEXT_907);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_908);
    }
    stringBuffer.append(TEXT_909);
    stringBuffer.append(genClass.getESetFlagsField(genFeature));
    stringBuffer.append(TEXT_910);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_911);
    } else {
    if (!genModel.isSuppressNotification()) {
    stringBuffer.append(TEXT_912);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_913);
    stringBuffer.append(genFeature.getUncapName());
    stringBuffer.append(TEXT_914);
    }
    stringBuffer.append(TEXT_915);
    stringBuffer.append(genFeature.getUncapName());
    stringBuffer.append(TEXT_916);
    }
    if (!genModel.isSuppressNotification()) {
    stringBuffer.append(TEXT_917);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.impl.ENotificationImpl"));
    stringBuffer.append(TEXT_918);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.impl.ENotificationImpl"));
    stringBuffer.append(TEXT_919);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.notify.Notification"));
    stringBuffer.append(TEXT_920);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(positiveOffsetCorrection);
    stringBuffer.append(TEXT_921);
    if (genModel.isVirtualDelegation()) {
    stringBuffer.append(TEXT_922);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_923);
    } else {
    stringBuffer.append(TEXT_924);
    stringBuffer.append(genFeature.getCapName());
    }
    stringBuffer.append(TEXT_925);
    if (genModel.isVirtualDelegation()) {
    stringBuffer.append(TEXT_926);
    } else {
    stringBuffer.append(TEXT_927);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_928);
    }
    stringBuffer.append(TEXT_929);
    }
    } else {
    stringBuffer.append(TEXT_930);
    stringBuffer.append(genFeature.getFormattedName());
    stringBuffer.append(TEXT_931);
    stringBuffer.append(genFeature.getFeatureKind());
    stringBuffer.append(TEXT_932);
    //Class/basicUnsetGenFeature.todo.override.javajetinc
    }
    stringBuffer.append(TEXT_933);
    //Class.basicUnsetGenFeature.override.javajetinc
    }
    if (genFeature.isUnset() && (isImplementation || !genFeature.isSuppressedUnsetVisibility())) {
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
    if (!genFeature.isSuppressedIsSetVisibility()) {
    stringBuffer.append(TEXT_940);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_941);
    }
    stringBuffer.append(TEXT_942);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_943);
    if (!genFeature.isListType() && !genFeature.isSuppressedSetVisibility()) {
    stringBuffer.append(TEXT_944);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_945);
    stringBuffer.append(genFeature.getRawImportedBoundType());
    stringBuffer.append(TEXT_946);
    }
    stringBuffer.append(TEXT_947);
    //Class/unsetGenFeature.javadoc.override.javajetinc
    } else {
    stringBuffer.append(TEXT_948);
    if (isJDK50) { //Class/unsetGenFeature.annotations.insert.javajetinc
    }
    }
    if (!isImplementation) {
    stringBuffer.append(TEXT_949);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_950);
    } else {
    stringBuffer.append(TEXT_951);
    stringBuffer.append(genFeature.getAccessorName());
    if (genClass.hasCollidingUnsetAccessorOperation(genFeature)) {
    stringBuffer.append(TEXT_952);
    }
    stringBuffer.append(TEXT_953);
    if (genModel.isDynamicDelegation()) {
    stringBuffer.append(TEXT_954);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(TEXT_955);
    stringBuffer.append(genFeature.getQualifiedFeatureAccessor());
    stringBuffer.append(TEXT_956);
    } else if (genModel.isReflectiveDelegation()) {
    stringBuffer.append(TEXT_957);
    stringBuffer.append(genFeature.getQualifiedFeatureAccessor());
    stringBuffer.append(TEXT_958);
    } else if (genFeature.hasSettingDelegate()) {
    stringBuffer.append(TEXT_959);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_960);
    } else if (!genFeature.isVolatile()) {
    if (genFeature.isListType()) {
    if (genModel.isVirtualDelegation()) {
    stringBuffer.append(TEXT_961);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_962);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_963);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_964);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(positiveOffsetCorrection);
    stringBuffer.append(TEXT_965);
    }
    stringBuffer.append(TEXT_966);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_967);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.InternalEList"));
    stringBuffer.append(TEXT_968);
    stringBuffer.append(singleWildcard);
    stringBuffer.append(TEXT_969);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_970);
    } else if (genFeature.isBidirectional() || genFeature.isEffectiveContains()) {
    if (genModel.isVirtualDelegation()) {
    stringBuffer.append(TEXT_971);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_972);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_973);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_974);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(positiveOffsetCorrection);
    stringBuffer.append(TEXT_975);
    }
    stringBuffer.append(TEXT_976);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_977);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.notify.NotificationChain"));
    stringBuffer.append(TEXT_978);
    if (!genFeature.isBidirectional()) {
    stringBuffer.append(TEXT_979);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.InternalEObject"));
    stringBuffer.append(TEXT_980);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_981);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(negativeOffsetCorrection);
    stringBuffer.append(TEXT_982);
    } else { GenFeature reverseFeature = genFeature.getReverse(); GenClass targetClass = reverseFeature.getGenClass(); String reverseOffsetCorrection = targetClass.hasOffsetCorrection() ? " + " + genClass.getOffsetCorrectionField(genFeature) : "";
    stringBuffer.append(TEXT_983);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.InternalEObject"));
    stringBuffer.append(TEXT_984);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_985);
    stringBuffer.append(targetClass.getQualifiedFeatureID(reverseFeature));
    stringBuffer.append(reverseOffsetCorrection);
    stringBuffer.append(TEXT_986);
    stringBuffer.append(targetClass.getRawImportedInterfaceName());
    stringBuffer.append(TEXT_987);
    }
    stringBuffer.append(TEXT_988);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_989);
    if (genModel.isVirtualDelegation()) {
    stringBuffer.append(TEXT_990);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_991);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(positiveOffsetCorrection);
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
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_1009);
    }
    stringBuffer.append(TEXT_1010);
    } else {
    if (genClass.isFlag(genFeature)) {
    if (!genModel.isSuppressNotification()) {
    if (genFeature.isBooleanType()) {
    stringBuffer.append(TEXT_1011);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_1012);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_1013);
    stringBuffer.append(genClass.getFlagsField(genFeature));
    stringBuffer.append(TEXT_1014);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_1015);
    } else {
    stringBuffer.append(TEXT_1016);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_1017);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_1018);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_1019);
    stringBuffer.append(genClass.getFlagsField(genFeature));
    stringBuffer.append(TEXT_1020);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_1021);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_1022);
    }
    }
    } else if (genModel.isVirtualDelegation() && !genFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_1023);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_1024);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(positiveOffsetCorrection);
    stringBuffer.append(TEXT_1025);
    } else {
    if (!genModel.isSuppressNotification()) {
    stringBuffer.append(TEXT_1026);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_1027);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_1028);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_1029);
    }
    }
    if (!genModel.isSuppressNotification()) {
    if (genModel.isVirtualDelegation() && !genFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_1030);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_1031);
    } else if (genClass.isESetFlag(genFeature)) {
    stringBuffer.append(TEXT_1032);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_1033);
    stringBuffer.append(genClass.getESetFlagsField(genFeature));
    stringBuffer.append(TEXT_1034);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_1035);
    } else {
    stringBuffer.append(TEXT_1036);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_1037);
    stringBuffer.append(genFeature.getUncapName());
    stringBuffer.append(TEXT_1038);
    }
    }
    if (genFeature.isReferenceType()) {
    stringBuffer.append(TEXT_1039);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_1040);
    if (!genModel.isVirtualDelegation()) {
    if (genClass.isESetFlag(genFeature)) {
    stringBuffer.append(TEXT_1041);
    stringBuffer.append(genClass.getESetFlagsField(genFeature));
    stringBuffer.append(TEXT_1042);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_1043);
    } else {
    stringBuffer.append(TEXT_1044);
    stringBuffer.append(genFeature.getUncapName());
    stringBuffer.append(TEXT_1045);
    }
    }
    if (!genModel.isSuppressNotification()) {
    stringBuffer.append(TEXT_1046);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.impl.ENotificationImpl"));
    stringBuffer.append(TEXT_1047);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.notify.Notification"));
    stringBuffer.append(TEXT_1048);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(positiveOffsetCorrection);
    stringBuffer.append(TEXT_1049);
    if (genModel.isVirtualDelegation()) {
    stringBuffer.append(TEXT_1050);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_1051);
    } else {
    stringBuffer.append(TEXT_1052);
    stringBuffer.append(genFeature.getCapName());
    }
    stringBuffer.append(TEXT_1053);
    if (genModel.isVirtualDelegation()) {
    stringBuffer.append(TEXT_1054);
    } else {
    stringBuffer.append(TEXT_1055);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_1056);
    }
    stringBuffer.append(TEXT_1057);
    }
    } else {
    if (genClass.isFlag(genFeature)) {
    if (genFeature.isBooleanType()) {
    stringBuffer.append(TEXT_1058);
    stringBuffer.append(genFeature.getEDefault());
    stringBuffer.append(TEXT_1059);
    stringBuffer.append(genClass.getFlagsField(genFeature));
    stringBuffer.append(TEXT_1060);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_1061);
    stringBuffer.append(genClass.getFlagsField(genFeature));
    stringBuffer.append(TEXT_1062);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_1063);
    } else {
    stringBuffer.append(TEXT_1064);
    stringBuffer.append(genClass.getFlagsField(genFeature));
    stringBuffer.append(TEXT_1065);
    stringBuffer.append(genClass.getFlagsField(genFeature));
    stringBuffer.append(TEXT_1066);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_1067);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_1068);
    }
    } else if (!genModel.isVirtualDelegation() || genFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_1069);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_1070);
    stringBuffer.append(genFeature.getEDefault());
    stringBuffer.append(TEXT_1071);
    }
    if (!genModel.isVirtualDelegation() || genFeature.isPrimitiveType()) {
    if (genClass.isESetFlag(genFeature)) {
    stringBuffer.append(TEXT_1072);
    stringBuffer.append(genClass.getESetFlagsField(genFeature));
    stringBuffer.append(TEXT_1073);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_1074);
    } else {
    stringBuffer.append(TEXT_1075);
    stringBuffer.append(genFeature.getUncapName());
    stringBuffer.append(TEXT_1076);
    }
    }
    if (!genModel.isSuppressNotification()) {
    stringBuffer.append(TEXT_1077);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.impl.ENotificationImpl"));
    stringBuffer.append(TEXT_1078);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.notify.Notification"));
    stringBuffer.append(TEXT_1079);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(positiveOffsetCorrection);
    stringBuffer.append(TEXT_1080);
    if (genModel.isVirtualDelegation() && !genFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_1081);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_1082);
    stringBuffer.append(genFeature.getEDefault());
    } else {
    stringBuffer.append(TEXT_1083);
    stringBuffer.append(genFeature.getCapName());
    }
    stringBuffer.append(TEXT_1084);
    stringBuffer.append(genFeature.getEDefault());
    stringBuffer.append(TEXT_1085);
    if (genModel.isVirtualDelegation() && !genFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_1086);
    } else {
    stringBuffer.append(TEXT_1087);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_1088);
    }
    stringBuffer.append(TEXT_1089);
    }
    }
    }
    } else if (genFeature.hasDelegateFeature()) { GenFeature delegateFeature = genFeature.getDelegateFeature();
    if (delegateFeature.isWrappedFeatureMapType()) {
    stringBuffer.append(TEXT_1090);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.FeatureMap"));
    stringBuffer.append(TEXT_1091);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.FeatureMap"));
    stringBuffer.append(TEXT_1092);
    stringBuffer.append(delegateFeature.getAccessorName());
    stringBuffer.append(TEXT_1093);
    stringBuffer.append(genFeature.getQualifiedFeatureAccessor());
    stringBuffer.append(TEXT_1094);
    } else {
    stringBuffer.append(TEXT_1095);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.FeatureMap"));
    stringBuffer.append(TEXT_1096);
    stringBuffer.append(delegateFeature.getAccessorName());
    stringBuffer.append(TEXT_1097);
    stringBuffer.append(genFeature.getQualifiedFeatureAccessor());
    stringBuffer.append(TEXT_1098);
    }
    } else if (genClass.getUnsetAccessorOperation(genFeature) != null) {
    stringBuffer.append(TEXT_1099);
    stringBuffer.append(genClass.getUnsetAccessorOperation(genFeature).getBody(genModel.getIndentation(stringBuffer)));
    } else {
    stringBuffer.append(TEXT_1100);
    stringBuffer.append(genFeature.getFormattedName());
    stringBuffer.append(TEXT_1101);
    stringBuffer.append(genFeature.getFeatureKind());
    stringBuffer.append(TEXT_1102);
    //Class/unsetGenFeature.todo.override.javajetinc
    }
    stringBuffer.append(TEXT_1103);
    }
    //Class/unsetGenFeature.override.javajetinc
    }
    if (genFeature.isIsSet() && (isImplementation || !genFeature.isSuppressedIsSetVisibility())) {
    if (isInterface) {
    stringBuffer.append(TEXT_1104);
    stringBuffer.append(genClass.getQualifiedInterfaceName());
    stringBuffer.append(TEXT_1105);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_1106);
    stringBuffer.append(genFeature.getFormattedName());
    stringBuffer.append(TEXT_1107);
    stringBuffer.append(genFeature.getFeatureKind());
    stringBuffer.append(TEXT_1108);
    stringBuffer.append(TEXT_1109);
    stringBuffer.append(genFeature.getFormattedName());
    stringBuffer.append(TEXT_1110);
    stringBuffer.append(genFeature.getFeatureKind());
    stringBuffer.append(TEXT_1111);
    if (genFeature.isChangeable() && !genFeature.isSuppressedUnsetVisibility()) {
    stringBuffer.append(TEXT_1112);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_1113);
    }
    stringBuffer.append(TEXT_1114);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_1115);
    if (!genFeature.isListType() && genFeature.isChangeable() && !genFeature.isSuppressedSetVisibility()) {
    stringBuffer.append(TEXT_1116);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_1117);
    stringBuffer.append(genFeature.getRawImportedBoundType());
    stringBuffer.append(TEXT_1118);
    }
    stringBuffer.append(TEXT_1119);
    //Class/isSetGenFeature.javadoc.override.javajetinc
    } else {
    stringBuffer.append(TEXT_1120);
    if (isJDK50) { //Class/isSetGenFeature.annotations.insert.javajetinc
    }
    }
    if (!isImplementation) {
    stringBuffer.append(TEXT_1121);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_1122);
    } else {
    stringBuffer.append(TEXT_1123);
    stringBuffer.append(genFeature.getAccessorName());
    if (genClass.hasCollidingIsSetAccessorOperation(genFeature)) {
    stringBuffer.append(TEXT_1124);
    }
    stringBuffer.append(TEXT_1125);
    if (genModel.isDynamicDelegation()) {
    stringBuffer.append(TEXT_1126);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(TEXT_1127);
    stringBuffer.append(genFeature.getQualifiedFeatureAccessor());
    stringBuffer.append(TEXT_1128);
    } else if (genModel.isReflectiveDelegation()) {
    stringBuffer.append(TEXT_1129);
    stringBuffer.append(genFeature.getQualifiedFeatureAccessor());
    stringBuffer.append(TEXT_1130);
    } else if (genFeature.hasSettingDelegate()) {
    stringBuffer.append(TEXT_1131);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_1132);
    } else if (!genFeature.isVolatile()) {
    if (genFeature.isListType()) {
    if (genModel.isVirtualDelegation()) {
    stringBuffer.append(TEXT_1133);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_1134);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_1135);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_1136);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(positiveOffsetCorrection);
    stringBuffer.append(TEXT_1137);
    }
    stringBuffer.append(TEXT_1138);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_1139);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.InternalEList"));
    stringBuffer.append(TEXT_1140);
    stringBuffer.append(singleWildcard);
    stringBuffer.append(TEXT_1141);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_1142);
    } else {
    if (genModel.isVirtualDelegation() && !genFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_1143);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(positiveOffsetCorrection);
    stringBuffer.append(TEXT_1144);
    } else if (genClass.isESetFlag(genFeature)) {
    stringBuffer.append(TEXT_1145);
    stringBuffer.append(genClass.getESetFlagsField(genFeature));
    stringBuffer.append(TEXT_1146);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_1147);
    } else {
    stringBuffer.append(TEXT_1148);
    stringBuffer.append(genFeature.getUncapName());
    stringBuffer.append(TEXT_1149);
    }
    }
    } else if (genFeature.hasDelegateFeature()) { GenFeature delegateFeature = genFeature.getDelegateFeature();
    if (delegateFeature.isWrappedFeatureMapType()) {
    stringBuffer.append(TEXT_1150);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.FeatureMap"));
    stringBuffer.append(TEXT_1151);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.FeatureMap"));
    stringBuffer.append(TEXT_1152);
    stringBuffer.append(delegateFeature.getAccessorName());
    stringBuffer.append(TEXT_1153);
    stringBuffer.append(genFeature.getQualifiedFeatureAccessor());
    stringBuffer.append(TEXT_1154);
    } else {
    stringBuffer.append(TEXT_1155);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.FeatureMap"));
    stringBuffer.append(TEXT_1156);
    stringBuffer.append(delegateFeature.getAccessorName());
    stringBuffer.append(TEXT_1157);
    stringBuffer.append(genFeature.getQualifiedFeatureAccessor());
    stringBuffer.append(TEXT_1158);
    }
    } else if (genClass.getIsSetAccessorOperation(genFeature) != null) {
    stringBuffer.append(TEXT_1159);
    stringBuffer.append(genClass.getIsSetAccessorOperation(genFeature).getBody(genModel.getIndentation(stringBuffer)));
    } else {
    stringBuffer.append(TEXT_1160);
    stringBuffer.append(genFeature.getFormattedName());
    stringBuffer.append(TEXT_1161);
    stringBuffer.append(genFeature.getFeatureKind());
    stringBuffer.append(TEXT_1162);
    //Class/isSetGenFeature.todo.override.javajetinc
    }
    stringBuffer.append(TEXT_1163);
    }
    //Class/isSetGenFeature.override.javajetinc
    }
    //Class/genFeature.override.javajetinc
    }//for
    }}.run();
    for (GenOperation genOperation : (isImplementation ? genClass.getImplementedGenOperations() : genClass.getDeclaredGenOperations())) {
    if (isImplementation) {
    if (genOperation.isInvariant() && genOperation.hasInvariantExpression()) {
    stringBuffer.append(TEXT_1164);
    stringBuffer.append(genOperation.getName());
    stringBuffer.append(TEXT_1165);
    stringBuffer.append(genOperation.getParameterTypes(", "));
    stringBuffer.append(TEXT_1166);
    stringBuffer.append(genOperation.getFormattedName());
    stringBuffer.append(TEXT_1167);
    stringBuffer.append(genOperation.getName());
    stringBuffer.append(TEXT_1168);
    stringBuffer.append(genOperation.getParameterTypes(", "));
    stringBuffer.append(TEXT_1169);
    stringBuffer.append(genModel.getImportedName("java.lang.String"));
    stringBuffer.append(TEXT_1170);
    stringBuffer.append(CodeGenUtil.upperName(genClass.getUniqueName(genOperation), genModel.getLocale()));
    stringBuffer.append(TEXT_1171);
    stringBuffer.append(genOperation.getInvariantExpression("\t\t"));
    stringBuffer.append(TEXT_1172);
    stringBuffer.append(genModel.getNonNLS());
    stringBuffer.append(TEXT_1173);
    } else if (genOperation.hasInvocationDelegate()) {
    stringBuffer.append(TEXT_1174);
    stringBuffer.append(genOperation.getName());
    stringBuffer.append(TEXT_1175);
    stringBuffer.append(genOperation.getParameterTypes(", "));
    stringBuffer.append(TEXT_1176);
    stringBuffer.append(genOperation.getFormattedName());
    stringBuffer.append(TEXT_1177);
    stringBuffer.append(genOperation.getName());
    stringBuffer.append(TEXT_1178);
    stringBuffer.append(genOperation.getParameterTypes(", "));
    stringBuffer.append(TEXT_1179);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.EOperation"));
    stringBuffer.append(TEXT_1180);
    stringBuffer.append(CodeGenUtil.upperName(genClass.getUniqueName(genOperation), genModel.getLocale()));
    stringBuffer.append(TEXT_1181);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.EOperation"));
    stringBuffer.append(TEXT_1182);
    stringBuffer.append(genOperation.getQualifiedOperationAccessor());
    stringBuffer.append(TEXT_1183);
    }
    }
    if (isInterface) {
    stringBuffer.append(TEXT_1184);
    stringBuffer.append(TEXT_1185);
    if (genOperation.hasDocumentation() || genOperation.hasParameterDocumentation()) {
    stringBuffer.append(TEXT_1186);
    if (genOperation.hasDocumentation()) {
    stringBuffer.append(TEXT_1187);
    stringBuffer.append(genOperation.getDocumentation(genModel.getIndentation(stringBuffer)));
    }
    for (GenParameter genParameter : genOperation.getGenParameters()) {
    if (genParameter.hasDocumentation()) { String documentation = genParameter.getDocumentation("");
    if (documentation.contains("\n") || documentation.contains("\r")) {
    stringBuffer.append(TEXT_1188);
    stringBuffer.append(genParameter.getName());
    stringBuffer.append(TEXT_1189);
    stringBuffer.append(genParameter.getDocumentation(genModel.getIndentation(stringBuffer)));
    } else {
    stringBuffer.append(TEXT_1190);
    stringBuffer.append(genParameter.getName());
    stringBuffer.append(TEXT_1191);
    stringBuffer.append(genParameter.getDocumentation(genModel.getIndentation(stringBuffer)));
    }
    }
    }
    stringBuffer.append(TEXT_1192);
    }
    if (!genModel.isSuppressEMFModelTags()) { boolean first = true; for (StringTokenizer stringTokenizer = new StringTokenizer(genOperation.getModelInfo(), "\n\r"); stringTokenizer.hasMoreTokens(); ) { String modelInfo = stringTokenizer.nextToken(); if (first) { first = false;
    stringBuffer.append(TEXT_1193);
    stringBuffer.append(modelInfo);
    } else {
    stringBuffer.append(TEXT_1194);
    stringBuffer.append(modelInfo);
    }} if (first) {
    stringBuffer.append(TEXT_1195);
    }}
    stringBuffer.append(TEXT_1196);
    //Class/genOperation.javadoc.override.javajetinc
    } else {
    stringBuffer.append(TEXT_1197);
    if (isJDK50) { //Class/genOperation.annotations.insert.javajetinc
    }
    }
    if (!isImplementation) {
    stringBuffer.append(TEXT_1198);
    stringBuffer.append(genOperation.getTypeParameters(genClass));
    stringBuffer.append(genOperation.getImportedType(genClass));
    stringBuffer.append(TEXT_1199);
    stringBuffer.append(genOperation.getName());
    stringBuffer.append(TEXT_1200);
    stringBuffer.append(genOperation.getParameters(genClass));
    stringBuffer.append(TEXT_1201);
    stringBuffer.append(genOperation.getThrows(genClass));
    stringBuffer.append(TEXT_1202);
    } else {
    if (genModel.useGenerics() && !genOperation.hasBody() && !genOperation.isInvariant() && genOperation.hasInvocationDelegate() && genOperation.isUncheckedCast(genClass)) {
    stringBuffer.append(TEXT_1203);
    }
    stringBuffer.append(TEXT_1204);
    stringBuffer.append(genOperation.getTypeParameters(genClass));
    stringBuffer.append(genOperation.getImportedType(genClass));
    stringBuffer.append(TEXT_1205);
    stringBuffer.append(genOperation.getName());
    stringBuffer.append(TEXT_1206);
    stringBuffer.append(genOperation.getParameters(genClass));
    stringBuffer.append(TEXT_1207);
    stringBuffer.append(genOperation.getThrows(genClass));
    stringBuffer.append(TEXT_1208);
    if (genOperation.hasBody()) {
    stringBuffer.append(TEXT_1209);
    stringBuffer.append(genOperation.getBody(genModel.getIndentation(stringBuffer)));
    } else if (genOperation.isInvariant()) {GenClass opClass = genOperation.getGenClass(); String diagnostics = genOperation.getGenParameters().get(0).getName(); String context = genOperation.getGenParameters().get(1).getName();
    if (genOperation.hasInvariantExpression()) {
    stringBuffer.append(TEXT_1210);
    stringBuffer.append(opClass.getGenPackage().getImportedValidatorClassName());
    stringBuffer.append(TEXT_1211);
    stringBuffer.append(genClass.getQualifiedClassifierAccessor());
    stringBuffer.append(TEXT_1212);
    stringBuffer.append(diagnostics);
    stringBuffer.append(TEXT_1213);
    stringBuffer.append(context);
    stringBuffer.append(TEXT_1214);
    stringBuffer.append(genOperation.getValidationDelegate());
    stringBuffer.append(TEXT_1215);
    stringBuffer.append(genModel.getNonNLS());
    stringBuffer.append(TEXT_1216);
    stringBuffer.append(genOperation.getQualifiedOperationAccessor());
    stringBuffer.append(TEXT_1217);
    stringBuffer.append(CodeGenUtil.upperName(genClass.getUniqueName(genOperation), genModel.getLocale()));
    stringBuffer.append(TEXT_1218);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.util.Diagnostic"));
    stringBuffer.append(TEXT_1219);
    stringBuffer.append(opClass.getGenPackage().getImportedValidatorClassName());
    stringBuffer.append(TEXT_1220);
    stringBuffer.append(opClass.getGenPackage().getImportedValidatorClassName());
    stringBuffer.append(TEXT_1221);
    stringBuffer.append(opClass.getOperationID(genOperation));
    stringBuffer.append(TEXT_1222);
    } else {
    stringBuffer.append(TEXT_1223);
    stringBuffer.append(diagnostics);
    stringBuffer.append(TEXT_1224);
    stringBuffer.append(diagnostics);
    stringBuffer.append(TEXT_1225);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.util.BasicDiagnostic"));
    stringBuffer.append(TEXT_1226);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.util.Diagnostic"));
    stringBuffer.append(TEXT_1227);
    stringBuffer.append(opClass.getGenPackage().getImportedValidatorClassName());
    stringBuffer.append(TEXT_1228);
    stringBuffer.append(opClass.getGenPackage().getImportedValidatorClassName());
    stringBuffer.append(TEXT_1229);
    stringBuffer.append(opClass.getOperationID(genOperation));
    stringBuffer.append(TEXT_1230);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.plugin.EcorePlugin"));
    stringBuffer.append(TEXT_1231);
    stringBuffer.append(genOperation.getName());
    stringBuffer.append(TEXT_1232);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.EObjectValidator"));
    stringBuffer.append(TEXT_1233);
    stringBuffer.append(context);
    stringBuffer.append(TEXT_1234);
    stringBuffer.append(genModel.getNonNLS());
    stringBuffer.append(genModel.getNonNLS(2));
    stringBuffer.append(TEXT_1235);
    }
    } else if (genOperation.hasInvocationDelegate()) { int size = genOperation.getGenParameters().size();
    stringBuffer.append(TEXT_1236);
    if (genOperation.isVoid()) {
    stringBuffer.append(TEXT_1237);
    stringBuffer.append(CodeGenUtil.upperName(genClass.getUniqueName(genOperation), genModel.getLocale()));
    stringBuffer.append(TEXT_1238);
    if (size > 0) {
    stringBuffer.append(TEXT_1239);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.util.BasicEList"));
    stringBuffer.append(TEXT_1240);
    stringBuffer.append(size);
    stringBuffer.append(TEXT_1241);
    stringBuffer.append(genOperation.getParametersArray(genClass));
    stringBuffer.append(TEXT_1242);
    } else {
    stringBuffer.append(TEXT_1243);
    }
    stringBuffer.append(TEXT_1244);
    } else {
    stringBuffer.append(TEXT_1245);
    if (!isJDK50 && genOperation.isPrimitiveType()) {
    stringBuffer.append(TEXT_1246);
    }
    stringBuffer.append(TEXT_1247);
    stringBuffer.append(genOperation.getObjectType(genClass));
    stringBuffer.append(TEXT_1248);
    stringBuffer.append(CodeGenUtil.upperName(genClass.getUniqueName(genOperation), genModel.getLocale()));
    stringBuffer.append(TEXT_1249);
    if (size > 0) {
    stringBuffer.append(TEXT_1250);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.util.BasicEList"));
    stringBuffer.append(TEXT_1251);
    stringBuffer.append(size);
    stringBuffer.append(TEXT_1252);
    stringBuffer.append(genOperation.getParametersArray(genClass));
    stringBuffer.append(TEXT_1253);
    } else {
    stringBuffer.append(TEXT_1254);
    }
    stringBuffer.append(TEXT_1255);
    if (!isJDK50 && genOperation.isPrimitiveType()) {
    stringBuffer.append(TEXT_1256);
    stringBuffer.append(genOperation.getPrimitiveValueFunction());
    stringBuffer.append(TEXT_1257);
    }
    stringBuffer.append(TEXT_1258);
    }
    stringBuffer.append(TEXT_1259);
    stringBuffer.append(genModel.getImportedName(isGWT ? "org.eclipse.emf.common.util.InvocationTargetException" : "java.lang.reflect.InvocationTargetException"));
    stringBuffer.append(TEXT_1260);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.util.WrappedException"));
    stringBuffer.append(TEXT_1261);
    } else {
    stringBuffer.append(TEXT_1262);
    //Class/implementedGenOperation.todo.override.javajetinc
    }
    stringBuffer.append(TEXT_1263);
    }
    //Class/implementedGenOperation.override.javajetinc
    }//for
    if (isImplementation && !genModel.isReflectiveDelegation() && genClass.implementsAny(genClass.getEInverseAddGenFeatures())) {
    stringBuffer.append(TEXT_1264);
    if (genModel.useGenerics()) {
    for (GenFeature genFeature : genClass.getEInverseAddGenFeatures()) {
    if (genFeature.isUncheckedCast(genClass)) {
    stringBuffer.append(TEXT_1265);
    break; }
    }
    }
    if (genModel.useClassOverrideAnnotation()) {
    stringBuffer.append(TEXT_1266);
    }
    stringBuffer.append(TEXT_1267);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.notify.NotificationChain"));
    stringBuffer.append(TEXT_1268);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.InternalEObject"));
    stringBuffer.append(TEXT_1269);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.notify.NotificationChain"));
    stringBuffer.append(TEXT_1270);
    stringBuffer.append(negativeOffsetCorrection);
    stringBuffer.append(TEXT_1271);
    for (GenFeature genFeature : genClass.getEInverseAddGenFeatures()) {
    stringBuffer.append(TEXT_1272);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(TEXT_1273);
    if (genFeature.isListType()) { String cast = "("  + genModel.getImportedName("org.eclipse.emf.ecore.util.InternalEList") + (!genModel.useGenerics() ? ")" : "<" + genModel.getImportedName("org.eclipse.emf.ecore.InternalEObject") + ">)(" + genModel.getImportedName("org.eclipse.emf.ecore.util.InternalEList") + "<?>)");
    if (genFeature.isMapType() && genFeature.isEffectiveSuppressEMFTypes()) {
    stringBuffer.append(TEXT_1274);
    stringBuffer.append(cast);
    stringBuffer.append(TEXT_1275);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.util.EMap"));
    stringBuffer.append(TEXT_1276);
    stringBuffer.append(genFeature.getImportedMapTemplateArguments(genClass));
    stringBuffer.append(TEXT_1277);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_1278);
    } else {
    stringBuffer.append(TEXT_1279);
    stringBuffer.append(cast);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_1280);
    }
    } else if (genFeature.isContainer()) {
    stringBuffer.append(TEXT_1281);
    if (genFeature.isBasicSet()) {
    stringBuffer.append(TEXT_1282);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_1283);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_1284);
    } else {
    stringBuffer.append(TEXT_1285);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(positiveOffsetCorrection);
    stringBuffer.append(TEXT_1286);
    }
    } else {
    if (genClass.getImplementingGenModel(genFeature).isVirtualDelegation()) {
    stringBuffer.append(TEXT_1287);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_1288);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_1289);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_1290);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(positiveOffsetCorrection);
    stringBuffer.append(TEXT_1291);
    } else if (genFeature.isVolatile() || genClass.getImplementingGenModel(genFeature).isDynamicDelegation()) {
    stringBuffer.append(TEXT_1292);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_1293);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_1294);
    if (genFeature.isResolveProxies()) {
    stringBuffer.append(TEXT_1295);
    stringBuffer.append(genFeature.getAccessorName());
    } else {
    stringBuffer.append(genFeature.getGetAccessor());
    }
    stringBuffer.append(TEXT_1296);
    }
    stringBuffer.append(TEXT_1297);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_1298);
    if (genFeature.isEffectiveContains()) {
    stringBuffer.append(TEXT_1299);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.InternalEObject"));
    stringBuffer.append(TEXT_1300);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_1301);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(negativeOffsetCorrection);
    stringBuffer.append(TEXT_1302);
    } else { GenFeature reverseFeature = genFeature.getReverse(); GenClass targetClass = reverseFeature.getGenClass(); String reverseOffsetCorrection = targetClass.hasOffsetCorrection() ? " + " + genClass.getOffsetCorrectionField(genFeature) : "";
    stringBuffer.append(TEXT_1303);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.InternalEObject"));
    stringBuffer.append(TEXT_1304);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_1305);
    stringBuffer.append(targetClass.getQualifiedFeatureID(reverseFeature));
    stringBuffer.append(reverseOffsetCorrection);
    stringBuffer.append(TEXT_1306);
    stringBuffer.append(targetClass.getRawImportedInterfaceName());
    stringBuffer.append(TEXT_1307);
    }
    stringBuffer.append(TEXT_1308);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_1309);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_1310);
    }
    }
    stringBuffer.append(TEXT_1311);
    if (genModel.isMinimalReflectiveMethods()) {
    stringBuffer.append(TEXT_1312);
    } else {
    stringBuffer.append(TEXT_1313);
    }
    stringBuffer.append(TEXT_1314);
    }
    if (isImplementation && !genModel.isReflectiveDelegation() && genClass.implementsAny(genClass.getEInverseRemoveGenFeatures())) {
    stringBuffer.append(TEXT_1315);
    if (genModel.useClassOverrideAnnotation()) {
    stringBuffer.append(TEXT_1316);
    }
    stringBuffer.append(TEXT_1317);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.notify.NotificationChain"));
    stringBuffer.append(TEXT_1318);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.InternalEObject"));
    stringBuffer.append(TEXT_1319);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.notify.NotificationChain"));
    stringBuffer.append(TEXT_1320);
    stringBuffer.append(negativeOffsetCorrection);
    stringBuffer.append(TEXT_1321);
    for (GenFeature genFeature : genClass.getEInverseRemoveGenFeatures()) {
    stringBuffer.append(TEXT_1322);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(TEXT_1323);
    if (genFeature.isListType()) {
    if (genFeature.isMapType() && genFeature.isEffectiveSuppressEMFTypes()) {
    stringBuffer.append(TEXT_1324);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.InternalEList"));
    stringBuffer.append(singleWildcard);
    stringBuffer.append(TEXT_1325);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.util.EMap"));
    stringBuffer.append(TEXT_1326);
    stringBuffer.append(genFeature.getImportedMapTemplateArguments(genClass));
    stringBuffer.append(TEXT_1327);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_1328);
    } else if (genFeature.isWrappedFeatureMapType()) {
    stringBuffer.append(TEXT_1329);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.InternalEList"));
    stringBuffer.append(singleWildcard);
    stringBuffer.append(TEXT_1330);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.FeatureMap"));
    stringBuffer.append(TEXT_1331);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_1332);
    } else {
    stringBuffer.append(TEXT_1333);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.InternalEList"));
    stringBuffer.append(singleWildcard);
    stringBuffer.append(TEXT_1334);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_1335);
    }
    } else if (genFeature.isContainer() && !genFeature.isBasicSet()) {
    stringBuffer.append(TEXT_1336);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(positiveOffsetCorrection);
    stringBuffer.append(TEXT_1337);
    } else if (genFeature.isUnsettable()) {
    stringBuffer.append(TEXT_1338);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_1339);
    } else {
    stringBuffer.append(TEXT_1340);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_1341);
    }
    }
    stringBuffer.append(TEXT_1342);
    if (genModel.isMinimalReflectiveMethods()) {
    stringBuffer.append(TEXT_1343);
    } else {
    stringBuffer.append(TEXT_1344);
    }
    stringBuffer.append(TEXT_1345);
    }
    if (isImplementation && !genModel.isReflectiveDelegation() && genClass.implementsAny(genClass.getEBasicRemoveFromContainerGenFeatures())) {
    stringBuffer.append(TEXT_1346);
    if (genModel.useClassOverrideAnnotation()) {
    stringBuffer.append(TEXT_1347);
    }
    stringBuffer.append(TEXT_1348);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.notify.NotificationChain"));
    stringBuffer.append(TEXT_1349);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.notify.NotificationChain"));
    stringBuffer.append(TEXT_1350);
    stringBuffer.append(negativeOffsetCorrection);
    stringBuffer.append(TEXT_1351);
    for (GenFeature genFeature : genClass.getEBasicRemoveFromContainerGenFeatures()) {
    GenFeature reverseFeature = genFeature.getReverse(); GenClass targetClass = reverseFeature.getGenClass(); String reverseOffsetCorrection = targetClass.hasOffsetCorrection() ? " + " + genClass.getOffsetCorrectionField(genFeature) : "";
    stringBuffer.append(TEXT_1352);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(TEXT_1353);
    stringBuffer.append(targetClass.getQualifiedFeatureID(reverseFeature));
    stringBuffer.append(reverseOffsetCorrection);
    stringBuffer.append(TEXT_1354);
    stringBuffer.append(targetClass.getRawImportedInterfaceName());
    stringBuffer.append(TEXT_1355);
    }
    stringBuffer.append(TEXT_1356);
    if (genModel.isMinimalReflectiveMethods()) {
    stringBuffer.append(TEXT_1357);
    } else {
    stringBuffer.append(TEXT_1358);
    }
    stringBuffer.append(TEXT_1359);
    }
    if (isImplementation && !genModel.isReflectiveDelegation() && genClass.implementsAny(genClass.getEGetGenFeatures())) {
    stringBuffer.append(TEXT_1360);
    if (genModel.useClassOverrideAnnotation()) {
    stringBuffer.append(TEXT_1361);
    }
    stringBuffer.append(TEXT_1362);
    stringBuffer.append(negativeOffsetCorrection);
    stringBuffer.append(TEXT_1363);
    for (GenFeature genFeature : genClass.getEGetGenFeatures()) {
    stringBuffer.append(TEXT_1364);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(TEXT_1365);
    if (genFeature.isPrimitiveType()) {
    if (isJDK50) {
    stringBuffer.append(TEXT_1366);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_1367);
    } else if (genFeature.isBooleanType()) {
    stringBuffer.append(TEXT_1368);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_1369);
    } else {
    stringBuffer.append(TEXT_1370);
    stringBuffer.append(genFeature.getObjectType(genClass));
    stringBuffer.append(TEXT_1371);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_1372);
    }
    } else if (genFeature.isResolveProxies() && !genFeature.isListType()) {
    stringBuffer.append(TEXT_1373);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_1374);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_1375);
    } else if (genFeature.isMapType()) {
    if (genFeature.isEffectiveSuppressEMFTypes()) {
    stringBuffer.append(TEXT_1376);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.util.EMap"));
    stringBuffer.append(TEXT_1377);
    stringBuffer.append(genFeature.getImportedMapTemplateArguments(genClass));
    stringBuffer.append(TEXT_1378);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_1379);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_1380);
    } else {
    stringBuffer.append(TEXT_1381);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_1382);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_1383);
    }
    } else if (genFeature.isWrappedFeatureMapType()) {
    stringBuffer.append(TEXT_1384);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.FeatureMap"));
    stringBuffer.append(TEXT_1385);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_1386);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_1387);
    } else if (genFeature.isFeatureMapType()) {
    stringBuffer.append(TEXT_1388);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_1389);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.FeatureMap"));
    stringBuffer.append(TEXT_1390);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_1391);
    } else {
    stringBuffer.append(TEXT_1392);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_1393);
    }
    }
    stringBuffer.append(TEXT_1394);
    if (genModel.isMinimalReflectiveMethods()) {
    stringBuffer.append(TEXT_1395);
    } else {
    stringBuffer.append(TEXT_1396);
    }
    stringBuffer.append(TEXT_1397);
    }
    if (isImplementation && !genModel.isReflectiveDelegation() && genClass.implementsAny(genClass.getESetGenFeatures())) {
    stringBuffer.append(TEXT_1398);
    if (genModel.useGenerics()) {
    for (GenFeature genFeature : genClass.getESetGenFeatures()) {
    if (genFeature.isUncheckedCast(genClass) && !genFeature.isFeatureMapType() && !genFeature.isMapType()) {
    stringBuffer.append(TEXT_1399);
    break; }
    }
    }
    if (genModel.useClassOverrideAnnotation()) {
    stringBuffer.append(TEXT_1400);
    }
    stringBuffer.append(TEXT_1401);
    stringBuffer.append(negativeOffsetCorrection);
    stringBuffer.append(TEXT_1402);
    for (GenFeature genFeature : genClass.getESetGenFeatures()) {
    stringBuffer.append(TEXT_1403);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(TEXT_1404);
    if (genFeature.isListType()) {
    if (genFeature.isWrappedFeatureMapType()) {
    stringBuffer.append(TEXT_1405);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.FeatureMap"));
    stringBuffer.append(TEXT_1406);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.FeatureMap"));
    stringBuffer.append(TEXT_1407);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_1408);
    } else if (genFeature.isFeatureMapType()) {
    stringBuffer.append(TEXT_1409);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.FeatureMap"));
    stringBuffer.append(TEXT_1410);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_1411);
    } else if (genFeature.isMapType()) {
    if (genFeature.isEffectiveSuppressEMFTypes()) {
    stringBuffer.append(TEXT_1412);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.EStructuralFeature"));
    stringBuffer.append(TEXT_1413);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.util.EMap"));
    stringBuffer.append(TEXT_1414);
    stringBuffer.append(genFeature.getImportedMapTemplateArguments(genClass));
    stringBuffer.append(TEXT_1415);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_1416);
    } else {
    stringBuffer.append(TEXT_1417);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.EStructuralFeature"));
    stringBuffer.append(TEXT_1418);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_1419);
    }
    } else {
    stringBuffer.append(TEXT_1420);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_1421);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_1422);
    stringBuffer.append(genModel.getImportedName("java.util.Collection"));
    if (isJDK50) {
    stringBuffer.append(TEXT_1423);
    stringBuffer.append(genFeature.getListItemType(genClass));
    stringBuffer.append(TEXT_1424);
    }
    stringBuffer.append(TEXT_1425);
    }
    } else if (!isJDK50 && genFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_1426);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_1427);
    stringBuffer.append(genFeature.getObjectType(genClass));
    stringBuffer.append(TEXT_1428);
    stringBuffer.append(genFeature.getPrimitiveValueFunction());
    stringBuffer.append(TEXT_1429);
    } else {
    stringBuffer.append(TEXT_1430);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_1431);
    if (genFeature.getTypeGenDataType() == null || !genFeature.getTypeGenDataType().isObjectType() || !genFeature.getRawType().equals(genFeature.getType(genClass))) {
    stringBuffer.append(TEXT_1432);
    stringBuffer.append(genFeature.getObjectType(genClass));
    stringBuffer.append(TEXT_1433);
    }
    stringBuffer.append(TEXT_1434);
    }
    stringBuffer.append(TEXT_1435);
    }
    stringBuffer.append(TEXT_1436);
    if (genModel.isMinimalReflectiveMethods()) {
    stringBuffer.append(TEXT_1437);
    } else {
    stringBuffer.append(TEXT_1438);
    }
    stringBuffer.append(TEXT_1439);
    }
    if (isImplementation && !genModel.isReflectiveDelegation() && genClass.implementsAny(genClass.getEUnsetGenFeatures())) {
    stringBuffer.append(TEXT_1440);
    if (genModel.useClassOverrideAnnotation()) {
    stringBuffer.append(TEXT_1441);
    }
    stringBuffer.append(TEXT_1442);
    stringBuffer.append(negativeOffsetCorrection);
    stringBuffer.append(TEXT_1443);
    for (GenFeature genFeature : genClass.getEUnsetGenFeatures()) {
    stringBuffer.append(TEXT_1444);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(TEXT_1445);
    if (genFeature.isListType() && !genFeature.isUnsettable()) {
    if (genFeature.isWrappedFeatureMapType()) {
    stringBuffer.append(TEXT_1446);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.FeatureMap"));
    stringBuffer.append(TEXT_1447);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_1448);
    } else {
    stringBuffer.append(TEXT_1449);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_1450);
    }
    } else if (genFeature.isUnsettable()) {
    stringBuffer.append(TEXT_1451);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_1452);
    } else if (!genFeature.hasEDefault()) {
    stringBuffer.append(TEXT_1453);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_1454);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_1455);
    } else {
    stringBuffer.append(TEXT_1456);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_1457);
    stringBuffer.append(genFeature.getEDefault());
    stringBuffer.append(TEXT_1458);
    }
    stringBuffer.append(TEXT_1459);
    }
    stringBuffer.append(TEXT_1460);
    if (genModel.isMinimalReflectiveMethods()) {
    stringBuffer.append(TEXT_1461);
    } else {
    stringBuffer.append(TEXT_1462);
    }
    stringBuffer.append(TEXT_1463);
    //Class/eUnset.override.javajetinc
    }
    if (isImplementation && !genModel.isReflectiveDelegation() && genClass.implementsAny(genClass.getEIsSetGenFeatures())) {
    stringBuffer.append(TEXT_1464);
    if (genModel.useGenerics()) {
    for (GenFeature genFeature : genClass.getEIsSetGenFeatures()) {
    if (genFeature.isListType() && !genFeature.isUnsettable() && !genFeature.isWrappedFeatureMapType() && !genClass.isField(genFeature) && genFeature.isField() && genClass.getImplementingGenModel(genFeature).isVirtualDelegation()) {
    stringBuffer.append(TEXT_1465);
    break; }
    }
    }
    if (genModel.useClassOverrideAnnotation()) {
    stringBuffer.append(TEXT_1466);
    }
    stringBuffer.append(TEXT_1467);
    stringBuffer.append(negativeOffsetCorrection);
    stringBuffer.append(TEXT_1468);
    for (GenFeature genFeature : genClass.getEIsSetGenFeatures()) { String safeNameAccessor = genFeature.getSafeName(); if ("featureID".equals(safeNameAccessor)) { safeNameAccessor = "this." + safeNameAccessor; }
    stringBuffer.append(TEXT_1469);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(TEXT_1470);
    if (genFeature.hasSettingDelegate()) {
    if (genFeature.isUnsettable()) {
    stringBuffer.append(TEXT_1471);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_1472);
    } else {
    stringBuffer.append(TEXT_1473);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_1474);
    }
    } else if (genFeature.isListType() && !genFeature.isUnsettable()) {
    if (genFeature.isWrappedFeatureMapType()) {
    if (genFeature.isVolatile()) {
    stringBuffer.append(TEXT_1475);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.FeatureMap"));
    stringBuffer.append(TEXT_1476);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_1477);
    } else {
    stringBuffer.append(TEXT_1478);
    stringBuffer.append(safeNameAccessor);
    stringBuffer.append(TEXT_1479);
    stringBuffer.append(safeNameAccessor);
    stringBuffer.append(TEXT_1480);
    }
    } else {
    if (genClass.isField(genFeature)) {
    stringBuffer.append(TEXT_1481);
    stringBuffer.append(safeNameAccessor);
    stringBuffer.append(TEXT_1482);
    stringBuffer.append(safeNameAccessor);
    stringBuffer.append(TEXT_1483);
    } else {
    if (genFeature.isField() && genClass.getImplementingGenModel(genFeature).isVirtualDelegation()) {
    stringBuffer.append(TEXT_1484);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_1485);
    stringBuffer.append(safeNameAccessor);
    stringBuffer.append(TEXT_1486);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_1487);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(positiveOffsetCorrection);
    stringBuffer.append(TEXT_1488);
    stringBuffer.append(safeNameAccessor);
    stringBuffer.append(TEXT_1489);
    stringBuffer.append(safeNameAccessor);
    stringBuffer.append(TEXT_1490);
    } else {
    stringBuffer.append(TEXT_1491);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_1492);
    }
    }
    }
    } else if (genFeature.isUnsettable()) {
    stringBuffer.append(TEXT_1493);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_1494);
    } else if (genFeature.isResolveProxies()) {
    if (genClass.isField(genFeature)) {
    stringBuffer.append(TEXT_1495);
    stringBuffer.append(safeNameAccessor);
    stringBuffer.append(TEXT_1496);
    } else {
    if (genFeature.isField() && genClass.getImplementingGenModel(genFeature).isVirtualDelegation()) {
    stringBuffer.append(TEXT_1497);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(positiveOffsetCorrection);
    stringBuffer.append(TEXT_1498);
    } else {
    stringBuffer.append(TEXT_1499);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_1500);
    }
    }
    } else if (!genFeature.hasEDefault()) {
    if (genClass.isField(genFeature)) {
    stringBuffer.append(TEXT_1501);
    stringBuffer.append(safeNameAccessor);
    stringBuffer.append(TEXT_1502);
    } else {
    if (genFeature.isField() && genClass.getImplementingGenModel(genFeature).isVirtualDelegation()) {
    stringBuffer.append(TEXT_1503);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(positiveOffsetCorrection);
    stringBuffer.append(TEXT_1504);
    } else {
    stringBuffer.append(TEXT_1505);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_1506);
    }
    }
    } else if (genFeature.isPrimitiveType() || genFeature.isEnumType()) {
    if (genClass.isField(genFeature)) {
    if (genClass.isFlag(genFeature)) {
    if (genFeature.isBooleanType()) {
    stringBuffer.append(TEXT_1507);
    stringBuffer.append(genClass.getFlagsField(genFeature));
    stringBuffer.append(TEXT_1508);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_1509);
    stringBuffer.append(genFeature.getEDefault());
    stringBuffer.append(TEXT_1510);
    } else {
    stringBuffer.append(TEXT_1511);
    stringBuffer.append(genClass.getFlagsField(genFeature));
    stringBuffer.append(TEXT_1512);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_1513);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_1514);
    }
    } else {
    stringBuffer.append(TEXT_1515);
    stringBuffer.append(safeNameAccessor);
    stringBuffer.append(TEXT_1516);
    stringBuffer.append(genFeature.getEDefault());
    stringBuffer.append(TEXT_1517);
    }
    } else {
    if (genFeature.isEnumType() && genFeature.isField() && genClass.getImplementingGenModel(genFeature).isVirtualDelegation()) {
    stringBuffer.append(TEXT_1518);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(positiveOffsetCorrection);
    stringBuffer.append(TEXT_1519);
    stringBuffer.append(genFeature.getEDefault());
    stringBuffer.append(TEXT_1520);
    stringBuffer.append(genFeature.getEDefault());
    stringBuffer.append(TEXT_1521);
    } else {
    stringBuffer.append(TEXT_1522);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_1523);
    stringBuffer.append(genFeature.getEDefault());
    stringBuffer.append(TEXT_1524);
    }
    }
    } else {//datatype
    if (genClass.isField(genFeature)) {
    stringBuffer.append(TEXT_1525);
    stringBuffer.append(genFeature.getEDefault());
    stringBuffer.append(TEXT_1526);
    stringBuffer.append(safeNameAccessor);
    stringBuffer.append(TEXT_1527);
    stringBuffer.append(genFeature.getEDefault());
    stringBuffer.append(TEXT_1528);
    stringBuffer.append(safeNameAccessor);
    stringBuffer.append(TEXT_1529);
    } else {
    if (genFeature.isField() && genClass.getImplementingGenModel(genFeature).isVirtualDelegation()) {
    stringBuffer.append(TEXT_1530);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_1531);
    stringBuffer.append(safeNameAccessor);
    stringBuffer.append(TEXT_1532);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_1533);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(positiveOffsetCorrection);
    stringBuffer.append(TEXT_1534);
    stringBuffer.append(genFeature.getEDefault());
    stringBuffer.append(TEXT_1535);
    stringBuffer.append(genFeature.getEDefault());
    stringBuffer.append(TEXT_1536);
    stringBuffer.append(safeNameAccessor);
    stringBuffer.append(TEXT_1537);
    stringBuffer.append(genFeature.getEDefault());
    stringBuffer.append(TEXT_1538);
    stringBuffer.append(safeNameAccessor);
    stringBuffer.append(TEXT_1539);
    } else {
    stringBuffer.append(TEXT_1540);
    stringBuffer.append(genFeature.getEDefault());
    stringBuffer.append(TEXT_1541);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_1542);
    stringBuffer.append(genFeature.getEDefault());
    stringBuffer.append(TEXT_1543);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_1544);
    }
    }
    }
    }
    stringBuffer.append(TEXT_1545);
    if (genModel.isMinimalReflectiveMethods()) {
    stringBuffer.append(TEXT_1546);
    } else {
    stringBuffer.append(TEXT_1547);
    }
    stringBuffer.append(TEXT_1548);
    //Class/eIsSet.override.javajetinc
    }
    if (isImplementation && (!genClass.getMixinGenFeatures().isEmpty() || genClass.hasOffsetCorrection() && !genClass.getGenFeatures().isEmpty())) {
    if (!genClass.getMixinGenFeatures().isEmpty()) {
    stringBuffer.append(TEXT_1549);
    if (genModel.useClassOverrideAnnotation()) {
    stringBuffer.append(TEXT_1550);
    }
    stringBuffer.append(TEXT_1551);
    stringBuffer.append(singleWildcard);
    stringBuffer.append(TEXT_1552);
    for (GenClass mixinGenClass : genClass.getMixinGenClasses()) {
    stringBuffer.append(TEXT_1553);
    stringBuffer.append(mixinGenClass.getRawImportedInterfaceName());
    stringBuffer.append(TEXT_1554);
    stringBuffer.append(negativeOffsetCorrection);
    stringBuffer.append(TEXT_1555);
    for (GenFeature genFeature : mixinGenClass.getGenFeatures()) {
    stringBuffer.append(TEXT_1556);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(TEXT_1557);
    stringBuffer.append(mixinGenClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(TEXT_1558);
    }
    stringBuffer.append(TEXT_1559);
    }
    stringBuffer.append(TEXT_1560);
    }
    stringBuffer.append(TEXT_1561);
    if (genModel.useClassOverrideAnnotation()) {
    stringBuffer.append(TEXT_1562);
    }
    stringBuffer.append(TEXT_1563);
    stringBuffer.append(singleWildcard);
    stringBuffer.append(TEXT_1564);
    for (GenClass mixinGenClass : genClass.getMixinGenClasses()) {
    stringBuffer.append(TEXT_1565);
    stringBuffer.append(mixinGenClass.getRawImportedInterfaceName());
    stringBuffer.append(TEXT_1566);
    for (GenFeature genFeature : mixinGenClass.getGenFeatures()) {
    stringBuffer.append(TEXT_1567);
    stringBuffer.append(mixinGenClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(TEXT_1568);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(positiveOffsetCorrection);
    stringBuffer.append(TEXT_1569);
    }
    stringBuffer.append(TEXT_1570);
    }
    if (genClass.hasOffsetCorrection() && !genClass.getGenFeatures().isEmpty()) {
    stringBuffer.append(TEXT_1571);
    stringBuffer.append(genClass.getRawImportedInterfaceName());
    stringBuffer.append(TEXT_1572);
    stringBuffer.append(negativeOffsetCorrection);
    stringBuffer.append(TEXT_1573);
    for (GenFeature genFeature : genClass.getGenFeatures()) {
    stringBuffer.append(TEXT_1574);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(TEXT_1575);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(positiveOffsetCorrection);
    stringBuffer.append(TEXT_1576);
    }
    stringBuffer.append(TEXT_1577);
    }
    stringBuffer.append(TEXT_1578);
    }
    if (genModel.isOperationReflection() && isImplementation && (!genClass.getMixinGenOperations().isEmpty() || !genClass.getOverrideGenOperations(genClass.getExtendedGenOperations(), genClass.getImplementedGenOperations()).isEmpty() || genClass.hasOffsetCorrection() && !genClass.getGenOperations().isEmpty())) {
    stringBuffer.append(TEXT_1579);
    if (genModel.useClassOverrideAnnotation()) {
    stringBuffer.append(TEXT_1580);
    }
    stringBuffer.append(TEXT_1581);
    stringBuffer.append(singleWildcard);
    stringBuffer.append(TEXT_1582);
    for (GenClass extendedGenClass : genClass.getExtendedGenClasses()) { List<GenOperation> extendedImplementedGenOperations = extendedGenClass.getImplementedGenOperations(); List<GenOperation> implementedGenOperations = genClass.getImplementedGenOperations();
    if (!genClass.getOverrideGenOperations(extendedImplementedGenOperations, implementedGenOperations).isEmpty()) {
    stringBuffer.append(TEXT_1583);
    stringBuffer.append(extendedGenClass.getRawImportedInterfaceName());
    stringBuffer.append(TEXT_1584);
    for (GenOperation genOperation : extendedImplementedGenOperations) { GenOperation overrideGenOperation = genClass.getOverrideGenOperation(genOperation);
    if (implementedGenOperations.contains(overrideGenOperation)) {
    stringBuffer.append(TEXT_1585);
    stringBuffer.append(extendedGenClass.getQualifiedOperationID(genOperation));
    stringBuffer.append(TEXT_1586);
    stringBuffer.append(genClass.getQualifiedOperationID(overrideGenOperation));
    stringBuffer.append(positiveOperationOffsetCorrection);
    stringBuffer.append(TEXT_1587);
    }
    }
    stringBuffer.append(TEXT_1588);
    }
    }
    for (GenClass mixinGenClass : genClass.getMixinGenClasses()) {
    stringBuffer.append(TEXT_1589);
    stringBuffer.append(mixinGenClass.getRawImportedInterfaceName());
    stringBuffer.append(TEXT_1590);
    for (GenOperation genOperation : mixinGenClass.getGenOperations()) { GenOperation overrideGenOperation = genClass.getOverrideGenOperation(genOperation);
    stringBuffer.append(TEXT_1591);
    stringBuffer.append(mixinGenClass.getQualifiedOperationID(genOperation));
    stringBuffer.append(TEXT_1592);
    stringBuffer.append(genClass.getQualifiedOperationID(overrideGenOperation != null ? overrideGenOperation : genOperation));
    stringBuffer.append(positiveOperationOffsetCorrection);
    stringBuffer.append(TEXT_1593);
    }
    stringBuffer.append(TEXT_1594);
    }
    if (genClass.hasOffsetCorrection() && !genClass.getGenOperations().isEmpty()) {
    stringBuffer.append(TEXT_1595);
    stringBuffer.append(genClass.getRawImportedInterfaceName());
    stringBuffer.append(TEXT_1596);
    stringBuffer.append(negativeOperationOffsetCorrection);
    stringBuffer.append(TEXT_1597);
    for (GenOperation genOperation : genClass.getGenOperations()) {
    stringBuffer.append(TEXT_1598);
    stringBuffer.append(genClass.getQualifiedOperationID(genOperation));
    stringBuffer.append(TEXT_1599);
    stringBuffer.append(genClass.getQualifiedOperationID(genOperation));
    stringBuffer.append(positiveOperationOffsetCorrection);
    stringBuffer.append(TEXT_1600);
    }
    stringBuffer.append(TEXT_1601);
    }
    stringBuffer.append(TEXT_1602);
    }
    if (isImplementation && genModel.isVirtualDelegation()) { String eVirtualValuesField = genClass.getEVirtualValuesField();
    if (eVirtualValuesField != null) {
    stringBuffer.append(TEXT_1603);
    if (genModel.useClassOverrideAnnotation()) {
    stringBuffer.append(TEXT_1604);
    }
    stringBuffer.append(TEXT_1605);
    stringBuffer.append(eVirtualValuesField);
    stringBuffer.append(TEXT_1606);
    if (genModel.useClassOverrideAnnotation()) {
    stringBuffer.append(TEXT_1607);
    }
    stringBuffer.append(TEXT_1608);
    stringBuffer.append(eVirtualValuesField);
    stringBuffer.append(TEXT_1609);
    }
    { List<String> eVirtualIndexBitFields = genClass.getEVirtualIndexBitFields(new ArrayList<String>());
    if (!eVirtualIndexBitFields.isEmpty()) { List<String> allEVirtualIndexBitFields = genClass.getAllEVirtualIndexBitFields(new ArrayList<String>());
    stringBuffer.append(TEXT_1610);
    if (genModel.useClassOverrideAnnotation()) {
    stringBuffer.append(TEXT_1611);
    }
    stringBuffer.append(TEXT_1612);
    for (int i = 0; i < allEVirtualIndexBitFields.size(); i++) {
    stringBuffer.append(TEXT_1613);
    stringBuffer.append(i);
    stringBuffer.append(TEXT_1614);
    stringBuffer.append(allEVirtualIndexBitFields.get(i));
    stringBuffer.append(TEXT_1615);
    }
    stringBuffer.append(TEXT_1616);
    if (genModel.useClassOverrideAnnotation()) {
    stringBuffer.append(TEXT_1617);
    }
    stringBuffer.append(TEXT_1618);
    for (int i = 0; i < allEVirtualIndexBitFields.size(); i++) {
    stringBuffer.append(TEXT_1619);
    stringBuffer.append(i);
    stringBuffer.append(TEXT_1620);
    stringBuffer.append(allEVirtualIndexBitFields.get(i));
    stringBuffer.append(TEXT_1621);
    }
    stringBuffer.append(TEXT_1622);
    }
    }
    }
    if (genModel.isOperationReflection() && isImplementation && !genClass.getImplementedGenOperations().isEmpty()) {
    stringBuffer.append(TEXT_1623);
    if (genModel.useClassOverrideAnnotation()) {
    stringBuffer.append(TEXT_1624);
    }
    if (genModel.useGenerics()) {
    LOOP: for (GenOperation genOperation : (genModel.isMinimalReflectiveMethods() ? genClass.getImplementedGenOperations() : genClass.getAllGenOperations())) {
    for (GenParameter genParameter : genOperation.getGenParameters()) {
    if (genParameter.isUncheckedCast()) {
    stringBuffer.append(TEXT_1625);
    break LOOP;}
    }
    }
    }
    stringBuffer.append(TEXT_1626);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.util.EList"));
    stringBuffer.append(singleWildcard);
    stringBuffer.append(TEXT_1627);
    stringBuffer.append(genModel.getImportedName(isGWT ? "org.eclipse.emf.common.util.InvocationTargetException" : "java.lang.reflect.InvocationTargetException"));
    stringBuffer.append(TEXT_1628);
    stringBuffer.append(negativeOperationOffsetCorrection);
    stringBuffer.append(TEXT_1629);
    for (GenOperation genOperation : (genModel.isMinimalReflectiveMethods() ? genClass.getImplementedGenOperations() : genClass.getAllGenOperations())) { List<GenParameter> genParameters = genOperation.getGenParameters(); int size = genParameters.size();
    stringBuffer.append(TEXT_1630);
    stringBuffer.append(genClass.getQualifiedOperationID(genOperation));
    stringBuffer.append(TEXT_1631);
    if (genOperation.isVoid()) {
    stringBuffer.append(TEXT_1632);
    stringBuffer.append(genOperation.getName());
    stringBuffer.append(TEXT_1633);
    for (int i = 0; i < size; i++) { GenParameter genParameter = genParameters.get(i);
    if (!isJDK50 && genParameter.isPrimitiveType()) {
    stringBuffer.append(TEXT_1634);
    }
    if (genParameter.getTypeGenDataType() == null || !genParameter.getTypeGenDataType().isObjectType() || !genParameter.getRawType().equals(genParameter.getType(genClass))) {
    stringBuffer.append(TEXT_1635);
    stringBuffer.append(genParameter.getObjectType(genClass));
    stringBuffer.append(TEXT_1636);
    }
    stringBuffer.append(TEXT_1637);
    stringBuffer.append(i);
    stringBuffer.append(TEXT_1638);
    if (!isJDK50 && genParameter.isPrimitiveType()) {
    stringBuffer.append(TEXT_1639);
    stringBuffer.append(genParameter.getPrimitiveValueFunction());
    stringBuffer.append(TEXT_1640);
    }
    if (i < (size - 1)) {
    stringBuffer.append(TEXT_1641);
    }
    }
    stringBuffer.append(TEXT_1642);
    } else {
    stringBuffer.append(TEXT_1643);
    if (!isJDK50 && genOperation.isPrimitiveType()) {
    stringBuffer.append(TEXT_1644);
    stringBuffer.append(genOperation.getObjectType(genClass));
    stringBuffer.append(TEXT_1645);
    }
    stringBuffer.append(genOperation.getName());
    stringBuffer.append(TEXT_1646);
    for (int i = 0; i < size; i++) { GenParameter genParameter = genParameters.get(i);
    if (!isJDK50 && genParameter.isPrimitiveType()) {
    stringBuffer.append(TEXT_1647);
    }
    if (genParameter.getTypeGenDataType() == null || !genParameter.getTypeGenDataType().isObjectType() || !genParameter.getRawType().equals(genParameter.getType(genClass))) {
    stringBuffer.append(TEXT_1648);
    stringBuffer.append(genParameter.getObjectType(genClass));
    stringBuffer.append(TEXT_1649);
    }
    stringBuffer.append(TEXT_1650);
    stringBuffer.append(i);
    stringBuffer.append(TEXT_1651);
    if (!isJDK50 && genParameter.isPrimitiveType()) {
    stringBuffer.append(TEXT_1652);
    stringBuffer.append(genParameter.getPrimitiveValueFunction());
    stringBuffer.append(TEXT_1653);
    }
    if (i < (size - 1)) {
    stringBuffer.append(TEXT_1654);
    }
    }
    stringBuffer.append(TEXT_1655);
    if (!isJDK50 && genOperation.isPrimitiveType()) {
    stringBuffer.append(TEXT_1656);
    }
    stringBuffer.append(TEXT_1657);
    }
    }
    stringBuffer.append(TEXT_1658);
    if (genModel.isMinimalReflectiveMethods()) {
    stringBuffer.append(TEXT_1659);
    } else {
    stringBuffer.append(TEXT_1660);
    }
    stringBuffer.append(TEXT_1661);
    }
    if (!genClass.hasImplementedToStringGenOperation() && isImplementation && !genModel.isReflectiveDelegation() && !genModel.isDynamicDelegation() && !genClass.getToStringGenFeatures().isEmpty()) {
    stringBuffer.append(TEXT_1662);
    if (genModel.useClassOverrideAnnotation()) {
    stringBuffer.append(TEXT_1663);
    }
    stringBuffer.append(TEXT_1664);
    { boolean first = true;
    for (GenFeature genFeature : genClass.getToStringGenFeatures()) {
    if (first) { first = false;
    stringBuffer.append(TEXT_1665);
    stringBuffer.append(genFeature.getName());
    stringBuffer.append(TEXT_1666);
    stringBuffer.append(genModel.getNonNLS());
    } else {
    stringBuffer.append(TEXT_1667);
    stringBuffer.append(genFeature.getName());
    stringBuffer.append(TEXT_1668);
    stringBuffer.append(genModel.getNonNLS());
    }
    if (genFeature.isUnsettable() && !genFeature.isListType()) {
    if (genModel.isVirtualDelegation() && !genFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_1669);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(positiveOffsetCorrection);
    stringBuffer.append(TEXT_1670);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(positiveOffsetCorrection);
    stringBuffer.append(TEXT_1671);
    stringBuffer.append(genModel.getNonNLS());
    } else {
    if (genClass.isFlag(genFeature)) {
    if (genFeature.isBooleanType()) {
    stringBuffer.append(TEXT_1672);
    if (genClass.isESetFlag(genFeature)) {
    stringBuffer.append(TEXT_1673);
    stringBuffer.append(genClass.getESetFlagsField(genFeature));
    stringBuffer.append(TEXT_1674);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_1675);
    } else {
    stringBuffer.append(genFeature.getUncapName());
    stringBuffer.append(TEXT_1676);
    }
    stringBuffer.append(TEXT_1677);
    stringBuffer.append(genClass.getFlagsField(genFeature));
    stringBuffer.append(TEXT_1678);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_1679);
    stringBuffer.append(genModel.getNonNLS());
    } else {
    stringBuffer.append(TEXT_1680);
    if (genClass.isESetFlag(genFeature)) {
    stringBuffer.append(TEXT_1681);
    stringBuffer.append(genClass.getESetFlagsField(genFeature));
    stringBuffer.append(TEXT_1682);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_1683);
    } else {
    stringBuffer.append(genFeature.getUncapName());
    stringBuffer.append(TEXT_1684);
    }
    stringBuffer.append(TEXT_1685);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_1686);
    stringBuffer.append(genClass.getFlagsField(genFeature));
    stringBuffer.append(TEXT_1687);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_1688);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_1689);
    stringBuffer.append(genModel.getNonNLS());
    }
    } else {
    stringBuffer.append(TEXT_1690);
    if (genClass.isESetFlag(genFeature)) {
    stringBuffer.append(TEXT_1691);
    stringBuffer.append(genClass.getESetFlagsField(genFeature));
    stringBuffer.append(TEXT_1692);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_1693);
    } else {
    stringBuffer.append(genFeature.getUncapName());
    stringBuffer.append(TEXT_1694);
    }
    stringBuffer.append(TEXT_1695);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_1696);
    stringBuffer.append(genModel.getNonNLS());
    }
    }
    } else {
    if (genModel.isVirtualDelegation() && !genFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_1697);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(positiveOffsetCorrection);
    if (!genFeature.isListType() && !genFeature.isReferenceType()){
    stringBuffer.append(TEXT_1698);
    stringBuffer.append(genFeature.getEDefault());
    }
    stringBuffer.append(TEXT_1699);
    } else {
    if (genClass.isFlag(genFeature)) {
    if (genFeature.isBooleanType()) {
    stringBuffer.append(TEXT_1700);
    stringBuffer.append(genClass.getFlagsField(genFeature));
    stringBuffer.append(TEXT_1701);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_1702);
    } else {
    stringBuffer.append(TEXT_1703);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_1704);
    stringBuffer.append(genClass.getFlagsField(genFeature));
    stringBuffer.append(TEXT_1705);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_1706);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_1707);
    }
    } else {
    stringBuffer.append(TEXT_1708);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_1709);
    }
    }
    }
    }
    }
    stringBuffer.append(TEXT_1710);
    }
    if (isImplementation && genClass.isMapEntry()) { GenFeature keyFeature = genClass.getMapEntryKeyFeature(); GenFeature valueFeature = genClass.getMapEntryValueFeature();
    String objectType = genModel.getImportedName("java.lang.Object");
    String keyType = isJDK50 ? keyFeature.getObjectType(genClass) : objectType;
    String valueType = isJDK50 ? valueFeature.getObjectType(genClass) : objectType;
    String eMapType = genModel.getImportedName("org.eclipse.emf.common.util.EMap") + (isJDK50 ? "<" + keyType + ", " + valueType + ">" : "");
    stringBuffer.append(TEXT_1711);
    if (isGWT) {
    stringBuffer.append(TEXT_1712);
    stringBuffer.append(genModel.getImportedName("com.google.gwt.user.client.rpc.GwtTransient"));
    }
    stringBuffer.append(TEXT_1713);
    stringBuffer.append(objectType);
    stringBuffer.append(TEXT_1714);
    stringBuffer.append(keyType);
    stringBuffer.append(TEXT_1715);
    if (!isJDK50 && keyFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_1716);
    stringBuffer.append(keyFeature.getObjectType(genClass));
    stringBuffer.append(TEXT_1717);
    } else {
    stringBuffer.append(TEXT_1718);
    }
    stringBuffer.append(TEXT_1719);
    stringBuffer.append(keyType);
    stringBuffer.append(TEXT_1720);
    if (keyFeature.isListType()) {
    stringBuffer.append(TEXT_1721);
    if (!genModel.useGenerics()) {
    stringBuffer.append(TEXT_1722);
    stringBuffer.append(genModel.getImportedName("java.util.Collection"));
    stringBuffer.append(TEXT_1723);
    }
    stringBuffer.append(TEXT_1724);
    } else if (isJDK50) {
    stringBuffer.append(TEXT_1725);
    } else if (keyFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_1726);
    stringBuffer.append(keyFeature.getObjectType(genClass));
    stringBuffer.append(TEXT_1727);
    stringBuffer.append(keyFeature.getPrimitiveValueFunction());
    stringBuffer.append(TEXT_1728);
    } else {
    stringBuffer.append(TEXT_1729);
    stringBuffer.append(keyFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_1730);
    }
    stringBuffer.append(TEXT_1731);
    stringBuffer.append(valueType);
    stringBuffer.append(TEXT_1732);
    if (!isJDK50 && valueFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_1733);
    stringBuffer.append(valueFeature.getObjectType(genClass));
    stringBuffer.append(TEXT_1734);
    } else {
    stringBuffer.append(TEXT_1735);
    }
    stringBuffer.append(TEXT_1736);
    stringBuffer.append(valueType);
    stringBuffer.append(TEXT_1737);
    stringBuffer.append(valueType);
    stringBuffer.append(TEXT_1738);
    stringBuffer.append(valueType);
    stringBuffer.append(TEXT_1739);
    if (valueFeature.isListType()) {
    stringBuffer.append(TEXT_1740);
    if (!genModel.useGenerics()) {
    stringBuffer.append(TEXT_1741);
    stringBuffer.append(genModel.getImportedName("java.util.Collection"));
    stringBuffer.append(TEXT_1742);
    }
    stringBuffer.append(TEXT_1743);
    } else if (isJDK50) {
    stringBuffer.append(TEXT_1744);
    } else if (valueFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_1745);
    stringBuffer.append(valueFeature.getObjectType(genClass));
    stringBuffer.append(TEXT_1746);
    stringBuffer.append(valueFeature.getPrimitiveValueFunction());
    stringBuffer.append(TEXT_1747);
    } else {
    stringBuffer.append(TEXT_1748);
    stringBuffer.append(valueFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_1749);
    }
    stringBuffer.append(TEXT_1750);
    if (genModel.useGenerics()) {
    stringBuffer.append(TEXT_1751);
    }
    stringBuffer.append(TEXT_1752);
    stringBuffer.append(eMapType);
    stringBuffer.append(TEXT_1753);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.EObject"));
    stringBuffer.append(TEXT_1754);
    stringBuffer.append(eMapType);
    stringBuffer.append(TEXT_1755);
    }
    stringBuffer.append(TEXT_1756);
    stringBuffer.append(isInterface ? " " + genClass.getInterfaceName() : genClass.getClassName());
    // TODO fix the space above
    genModel.emitSortedImports();
    stringBuffer.append(TEXT_1757);
    return stringBuffer.toString();
  }
}
