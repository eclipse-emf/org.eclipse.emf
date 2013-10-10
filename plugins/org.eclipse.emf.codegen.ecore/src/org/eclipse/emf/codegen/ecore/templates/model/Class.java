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
  protected final String TEXT_66 = NL + "\t/**" + NL + "\t * The empty value for the '{@link #";
  protected final String TEXT_67 = "() <em>";
  protected final String TEXT_68 = "</em>}' array accessor." + NL + "\t * This is specialized for the more specific element type known in this context." + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @see #";
  protected final String TEXT_69 = "()" + NL + "\t * @generated" + NL + "\t * @ordered" + NL + "\t */";
  protected final String TEXT_70 = NL + "\t@SuppressWarnings(\"rawtypes\")";
  protected final String TEXT_71 = NL + "\tprotected static final ";
  protected final String TEXT_72 = "[] ";
  protected final String TEXT_73 = "_EEMPTY_ARRAY = new ";
  protected final String TEXT_74 = " [0]";
  protected final String TEXT_75 = ";" + NL;
  protected final String TEXT_76 = NL + "\t/**" + NL + "\t * The cached setting delegate for the '{@link #";
  protected final String TEXT_77 = "() <em>";
  protected final String TEXT_78 = "</em>}' ";
  protected final String TEXT_79 = "." + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @see #";
  protected final String TEXT_80 = "()" + NL + "\t * @generated" + NL + "\t * @ordered" + NL + "\t */";
  protected final String TEXT_81 = NL + "\t@";
  protected final String TEXT_82 = NL + "\tprotected ";
  protected final String TEXT_83 = ".Internal.SettingDelegate ";
  protected final String TEXT_84 = "__ESETTING_DELEGATE = ((";
  protected final String TEXT_85 = ".Internal)";
  protected final String TEXT_86 = ").getSettingDelegate();" + NL;
  protected final String TEXT_87 = NL + "\t/**" + NL + "\t * The cached value of the '{@link #";
  protected final String TEXT_88 = "() <em>";
  protected final String TEXT_89 = "</em>}' ";
  protected final String TEXT_90 = "." + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @see #";
  protected final String TEXT_91 = "()" + NL + "\t * @generated" + NL + "\t * @ordered" + NL + "\t */";
  protected final String TEXT_92 = NL + "\t@";
  protected final String TEXT_93 = NL + "\tprotected ";
  protected final String TEXT_94 = " ";
  protected final String TEXT_95 = ";" + NL;
  protected final String TEXT_96 = NL + "\t/**" + NL + "\t * The empty value for the '{@link #";
  protected final String TEXT_97 = "() <em>";
  protected final String TEXT_98 = "</em>}' array accessor." + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @see #";
  protected final String TEXT_99 = "()" + NL + "\t * @generated" + NL + "\t * @ordered" + NL + "\t */";
  protected final String TEXT_100 = NL + "\t@SuppressWarnings(\"rawtypes\")";
  protected final String TEXT_101 = NL + "\tprotected static final ";
  protected final String TEXT_102 = "[] ";
  protected final String TEXT_103 = "_EEMPTY_ARRAY = new ";
  protected final String TEXT_104 = " [0]";
  protected final String TEXT_105 = ";" + NL;
  protected final String TEXT_106 = NL + "\t/**" + NL + "\t * The default value of the '{@link #";
  protected final String TEXT_107 = "() <em>";
  protected final String TEXT_108 = "</em>}' ";
  protected final String TEXT_109 = "." + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @see #";
  protected final String TEXT_110 = "()" + NL + "\t * @generated" + NL + "\t * @ordered" + NL + "\t */";
  protected final String TEXT_111 = NL + "\t@SuppressWarnings(\"unchecked\")";
  protected final String TEXT_112 = NL + "\tprotected static final ";
  protected final String TEXT_113 = " ";
  protected final String TEXT_114 = "; // TODO The default value literal \"";
  protected final String TEXT_115 = "\" is not valid.";
  protected final String TEXT_116 = " = ";
  protected final String TEXT_117 = ";";
  protected final String TEXT_118 = NL;
  protected final String TEXT_119 = NL + "\t/**" + NL + "\t * An additional set of bit flags representing the values of boolean attributes and whether unsettable features have been set." + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t * @ordered" + NL + "\t */";
  protected final String TEXT_120 = NL + "\t@";
  protected final String TEXT_121 = NL + "\tprotected int ";
  protected final String TEXT_122 = " = 0;" + NL;
  protected final String TEXT_123 = NL + "\t/**" + NL + "\t * The offset of the flags representing the value of the '{@link #";
  protected final String TEXT_124 = "() <em>";
  protected final String TEXT_125 = "</em>}' ";
  protected final String TEXT_126 = "." + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t * @ordered" + NL + "\t */" + NL + "\tprotected static final int ";
  protected final String TEXT_127 = "_EFLAG_OFFSET = ";
  protected final String TEXT_128 = ";" + NL + "" + NL + "\t/**" + NL + "\t * The flags representing the default value of the '{@link #";
  protected final String TEXT_129 = "() <em>";
  protected final String TEXT_130 = "</em>}' ";
  protected final String TEXT_131 = "." + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t * @ordered" + NL + "\t */" + NL + "\tprotected static final int ";
  protected final String TEXT_132 = "_EFLAG_DEFAULT = ";
  protected final String TEXT_133 = ".ordinal()";
  protected final String TEXT_134 = ".VALUES.indexOf(";
  protected final String TEXT_135 = ")";
  protected final String TEXT_136 = " << ";
  protected final String TEXT_137 = "_EFLAG_OFFSET;" + NL + "" + NL + "\t/**" + NL + "\t * The array of enumeration values for '{@link ";
  protected final String TEXT_138 = " ";
  protected final String TEXT_139 = "}'" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t * @ordered" + NL + "\t */" + NL + "\tprivate static final ";
  protected final String TEXT_140 = "[] ";
  protected final String TEXT_141 = "_EFLAG_VALUES = ";
  protected final String TEXT_142 = ".values()";
  protected final String TEXT_143 = "(";
  protected final String TEXT_144 = "[])";
  protected final String TEXT_145 = ".VALUES.toArray(new ";
  protected final String TEXT_146 = "[";
  protected final String TEXT_147 = ".VALUES.size()])";
  protected final String TEXT_148 = ";" + NL;
  protected final String TEXT_149 = NL + "\t/**" + NL + "\t * The flag";
  protected final String TEXT_150 = " representing the value of the '{@link #";
  protected final String TEXT_151 = "() <em>";
  protected final String TEXT_152 = "</em>}' ";
  protected final String TEXT_153 = "." + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @see #";
  protected final String TEXT_154 = "()" + NL + "\t * @generated" + NL + "\t * @ordered" + NL + "\t */" + NL + "\tprotected static final int ";
  protected final String TEXT_155 = "_EFLAG = ";
  protected final String TEXT_156 = " << ";
  protected final String TEXT_157 = "_EFLAG_OFFSET";
  protected final String TEXT_158 = ";" + NL;
  protected final String TEXT_159 = NL + "\t/**" + NL + "\t * The cached value of the '{@link #";
  protected final String TEXT_160 = "() <em>";
  protected final String TEXT_161 = "</em>}' ";
  protected final String TEXT_162 = "." + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @see #";
  protected final String TEXT_163 = "()" + NL + "\t * @generated" + NL + "\t * @ordered" + NL + "\t */";
  protected final String TEXT_164 = NL + "\t@";
  protected final String TEXT_165 = NL + "\tprotected ";
  protected final String TEXT_166 = " ";
  protected final String TEXT_167 = " = ";
  protected final String TEXT_168 = ";" + NL;
  protected final String TEXT_169 = NL + "\t/**" + NL + "\t * An additional set of bit flags representing the values of boolean attributes and whether unsettable features have been set." + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t * @ordered" + NL + "\t */";
  protected final String TEXT_170 = NL + "\t@";
  protected final String TEXT_171 = NL + "\tprotected int ";
  protected final String TEXT_172 = " = 0;" + NL;
  protected final String TEXT_173 = NL + "\t/**" + NL + "\t * The flag representing whether the ";
  protected final String TEXT_174 = " ";
  protected final String TEXT_175 = " has been set." + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t * @ordered" + NL + "\t */" + NL + "\tprotected static final int ";
  protected final String TEXT_176 = "_ESETFLAG = 1 << ";
  protected final String TEXT_177 = ";" + NL;
  protected final String TEXT_178 = NL + "\t/**" + NL + "\t * This is true if the ";
  protected final String TEXT_179 = " ";
  protected final String TEXT_180 = " has been set." + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t * @ordered" + NL + "\t */";
  protected final String TEXT_181 = NL + "\t@";
  protected final String TEXT_182 = NL + "\tprotected boolean ";
  protected final String TEXT_183 = "ESet;" + NL;
  protected final String TEXT_184 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */" + NL + "\tprivate static final int ";
  protected final String TEXT_185 = " = ";
  protected final String TEXT_186 = ".getFeatureID(";
  protected final String TEXT_187 = ") - ";
  protected final String TEXT_188 = ";" + NL;
  protected final String TEXT_189 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */" + NL + "\tprivate static final int ";
  protected final String TEXT_190 = " = ";
  protected final String TEXT_191 = ".getFeatureID(";
  protected final String TEXT_192 = ") - ";
  protected final String TEXT_193 = ";" + NL;
  protected final String TEXT_194 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */" + NL + "\tprivate static final int \"EOPERATION_OFFSET_CORRECTION\" = ";
  protected final String TEXT_195 = ".getOperationID(";
  protected final String TEXT_196 = ") - ";
  protected final String TEXT_197 = ";" + NL;
  protected final String TEXT_198 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */" + NL + "\t";
  protected final String TEXT_199 = "public";
  protected final String TEXT_200 = "protected";
  protected final String TEXT_201 = " ";
  protected final String TEXT_202 = "()" + NL + "\t{" + NL + "\t\tsuper();";
  protected final String TEXT_203 = NL + "\t\t";
  protected final String TEXT_204 = " |= ";
  protected final String TEXT_205 = "_EFLAG";
  protected final String TEXT_206 = "_DEFAULT";
  protected final String TEXT_207 = ";";
  protected final String TEXT_208 = NL + "\t}" + NL + "" + NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */";
  protected final String TEXT_209 = NL + "\t@Override";
  protected final String TEXT_210 = NL + "\tprotected ";
  protected final String TEXT_211 = " eStaticClass()" + NL + "\t{" + NL + "\t\treturn ";
  protected final String TEXT_212 = ";" + NL + "\t}" + NL;
  protected final String TEXT_213 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */" + NL + "\tprotected static final int ESTATIC_FEATURE_COUNT = ";
  protected final String TEXT_214 = ";" + NL;
  protected final String TEXT_215 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */";
  protected final String TEXT_216 = NL + "\t@Override";
  protected final String TEXT_217 = NL + "\tprotected int eStaticFeatureCount()" + NL + "\t{" + NL + "\t\treturn ";
  protected final String TEXT_218 = ";" + NL + "\t}" + NL;
  protected final String TEXT_219 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * This is specialized for the more specific element type known in this context." + NL + "\t * @generated" + NL + "\t */";
  protected final String TEXT_220 = NL + "\t@SuppressWarnings(\"unchecked\")";
  protected final String TEXT_221 = NL + "\t@Override";
  protected final String TEXT_222 = NL + "\tpublic ";
  protected final String TEXT_223 = "[] ";
  protected final String TEXT_224 = "()" + NL + "\t{";
  protected final String TEXT_225 = NL + "\t\t";
  protected final String TEXT_226 = " list = (";
  protected final String TEXT_227 = ")";
  protected final String TEXT_228 = "();" + NL + "\t\tif (list.isEmpty()) return ";
  protected final String TEXT_229 = "_EEMPTY_ARRAY;";
  protected final String TEXT_230 = NL + "\t\tif (";
  protected final String TEXT_231 = " == null || ";
  protected final String TEXT_232 = ".isEmpty()) return ";
  protected final String TEXT_233 = "_EEMPTY_ARRAY;" + NL + "\t\t";
  protected final String TEXT_234 = " list = (";
  protected final String TEXT_235 = ")";
  protected final String TEXT_236 = ";";
  protected final String TEXT_237 = NL + "\t\tlist.shrink();" + NL + "\t\treturn (";
  protected final String TEXT_238 = "[])list.data();" + NL + "\t}" + NL;
  protected final String TEXT_239 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * This is specialized for the more specific element type known in this context." + NL + "\t * @generated" + NL + "\t */";
  protected final String TEXT_240 = NL + "\t@SuppressWarnings(\"unchecked\")";
  protected final String TEXT_241 = NL + "\t@Override";
  protected final String TEXT_242 = NL + "\tpublic ";
  protected final String TEXT_243 = " ";
  protected final String TEXT_244 = "_";
  protected final String TEXT_245 = "()" + NL + "\t{";
  protected final String TEXT_246 = NL + "\t\t";
  protected final String TEXT_247 = " ";
  protected final String TEXT_248 = " = (";
  protected final String TEXT_249 = ")eVirtualGet(";
  protected final String TEXT_250 = ");";
  protected final String TEXT_251 = NL + "\t\tif (";
  protected final String TEXT_252 = " == null)" + NL + "\t\t{";
  protected final String TEXT_253 = NL + "\t\t\teVirtualSet(";
  protected final String TEXT_254 = ", ";
  protected final String TEXT_255 = " = new ";
  protected final String TEXT_256 = ");";
  protected final String TEXT_257 = NL + "\t\t\t";
  protected final String TEXT_258 = " = new ";
  protected final String TEXT_259 = ";";
  protected final String TEXT_260 = NL + "\t\t}" + NL + "\t\treturn ";
  protected final String TEXT_261 = ";" + NL + "\t}" + NL;
  protected final String TEXT_262 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * This is specialized for the more specific type known in this context." + NL + "\t * @generated" + NL + "\t */";
  protected final String TEXT_263 = NL + "\t@Override";
  protected final String TEXT_264 = NL + "\tpublic ";
  protected final String TEXT_265 = " basicSet";
  protected final String TEXT_266 = "(";
  protected final String TEXT_267 = " new";
  protected final String TEXT_268 = ", ";
  protected final String TEXT_269 = " msgs)" + NL + "\t{" + NL + "\t\treturn super.basicSet";
  protected final String TEXT_270 = "(new";
  protected final String TEXT_271 = ", msgs);" + NL + "\t}" + NL;
  protected final String TEXT_272 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * This is specialized for the more specific type known in this context." + NL + "\t * @generated" + NL + "\t */";
  protected final String TEXT_273 = NL + "\t@Override";
  protected final String TEXT_274 = NL + "\tpublic void set";
  protected final String TEXT_275 = "_";
  protected final String TEXT_276 = "(";
  protected final String TEXT_277 = " ";
  protected final String TEXT_278 = ")" + NL + "\t{" + NL + "\t\tsuper.set";
  protected final String TEXT_279 = "_";
  protected final String TEXT_280 = "(";
  protected final String TEXT_281 = ");" + NL + "\t}" + NL;
  protected final String TEXT_282 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */";
  protected final String TEXT_283 = NL + "\t";
  protected final String TEXT_284 = "[] ";
  protected final String TEXT_285 = "();" + NL;
  protected final String TEXT_286 = NL + "\t@SuppressWarnings(\"unchecked\")";
  protected final String TEXT_287 = NL + "\tpublic ";
  protected final String TEXT_288 = "[] ";
  protected final String TEXT_289 = "()" + NL + "\t{";
  protected final String TEXT_290 = NL + "\t\t";
  protected final String TEXT_291 = " list = (";
  protected final String TEXT_292 = ")";
  protected final String TEXT_293 = "();" + NL + "\t\tif (list.isEmpty()) return ";
  protected final String TEXT_294 = "_EEMPTY_ARRAY;";
  protected final String TEXT_295 = NL + "\t\tif (";
  protected final String TEXT_296 = " == null || ";
  protected final String TEXT_297 = ".isEmpty()) return ";
  protected final String TEXT_298 = "_EEMPTY_ARRAY;" + NL + "\t\t";
  protected final String TEXT_299 = " list = (";
  protected final String TEXT_300 = ")";
  protected final String TEXT_301 = ";";
  protected final String TEXT_302 = NL + "\t\tlist.shrink();" + NL + "\t\treturn (";
  protected final String TEXT_303 = "[])list.data();" + NL + "\t}" + NL;
  protected final String TEXT_304 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */";
  protected final String TEXT_305 = NL + "\t";
  protected final String TEXT_306 = " get";
  protected final String TEXT_307 = "(int index);" + NL;
  protected final String TEXT_308 = NL + "\tpublic ";
  protected final String TEXT_309 = " get";
  protected final String TEXT_310 = "(int index)" + NL + "\t{" + NL + "\t\treturn ";
  protected final String TEXT_311 = "(";
  protected final String TEXT_312 = ")";
  protected final String TEXT_313 = "().get(index);" + NL + "\t}" + NL;
  protected final String TEXT_314 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */";
  protected final String TEXT_315 = NL + "\tint get";
  protected final String TEXT_316 = "Length();" + NL;
  protected final String TEXT_317 = NL + "\tpublic int get";
  protected final String TEXT_318 = "Length()" + NL + "\t{";
  protected final String TEXT_319 = NL + "\t\treturn ";
  protected final String TEXT_320 = "().size();";
  protected final String TEXT_321 = NL + "\t\treturn ";
  protected final String TEXT_322 = " == null ? 0 : ";
  protected final String TEXT_323 = ".size();";
  protected final String TEXT_324 = NL + "\t}" + NL;
  protected final String TEXT_325 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */";
  protected final String TEXT_326 = NL + "\tvoid set";
  protected final String TEXT_327 = "(";
  protected final String TEXT_328 = "[] new";
  protected final String TEXT_329 = ");" + NL;
  protected final String TEXT_330 = NL + "\tpublic void set";
  protected final String TEXT_331 = "(";
  protected final String TEXT_332 = "[] new";
  protected final String TEXT_333 = ")" + NL + "\t{" + NL + "\t\t((";
  protected final String TEXT_334 = ")";
  protected final String TEXT_335 = "()).setData(new";
  protected final String TEXT_336 = ".length, new";
  protected final String TEXT_337 = ");" + NL + "\t}" + NL;
  protected final String TEXT_338 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */";
  protected final String TEXT_339 = NL + "\tvoid set";
  protected final String TEXT_340 = "(int index, ";
  protected final String TEXT_341 = " element);" + NL;
  protected final String TEXT_342 = NL + "\tpublic void set";
  protected final String TEXT_343 = "(int index, ";
  protected final String TEXT_344 = " element)" + NL + "\t{" + NL + "\t\t";
  protected final String TEXT_345 = "().set(index, element);" + NL + "\t}" + NL;
  protected final String TEXT_346 = NL + "\t/**" + NL + "\t * Returns the value of the '<em><b>";
  protected final String TEXT_347 = "</b></em>' ";
  protected final String TEXT_348 = ".";
  protected final String TEXT_349 = NL + "\t * The key is of type ";
  protected final String TEXT_350 = "list of {@link ";
  protected final String TEXT_351 = "}";
  protected final String TEXT_352 = "{@link ";
  protected final String TEXT_353 = "}";
  protected final String TEXT_354 = "," + NL + "\t * and the value is of type ";
  protected final String TEXT_355 = "list of {@link ";
  protected final String TEXT_356 = "}";
  protected final String TEXT_357 = "{@link ";
  protected final String TEXT_358 = "}";
  protected final String TEXT_359 = ",";
  protected final String TEXT_360 = NL + "\t * The list contents are of type {@link ";
  protected final String TEXT_361 = "}";
  protected final String TEXT_362 = ".";
  protected final String TEXT_363 = NL + "\t * The default value is <code>";
  protected final String TEXT_364 = "</code>.";
  protected final String TEXT_365 = NL + "\t * The literals are from the enumeration {@link ";
  protected final String TEXT_366 = "}.";
  protected final String TEXT_367 = NL + "\t * It is bidirectional and its opposite is '{@link ";
  protected final String TEXT_368 = "#";
  protected final String TEXT_369 = " <em>";
  protected final String TEXT_370 = "</em>}'.";
  protected final String TEXT_371 = NL + "\t * <!-- begin-user-doc -->";
  protected final String TEXT_372 = NL + "\t * <p>" + NL + "\t * If the meaning of the '<em>";
  protected final String TEXT_373 = "</em>' ";
  protected final String TEXT_374 = " isn't clear," + NL + "\t * there really should be more of a description here..." + NL + "\t * </p>";
  protected final String TEXT_375 = NL + "\t * <!-- end-user-doc -->";
  protected final String TEXT_376 = NL + "\t * <!-- begin-model-doc -->" + NL + "\t * ";
  protected final String TEXT_377 = NL + "\t * <!-- end-model-doc -->";
  protected final String TEXT_378 = NL + "\t * @return the value of the '<em>";
  protected final String TEXT_379 = "</em>' ";
  protected final String TEXT_380 = ".";
  protected final String TEXT_381 = NL + "\t * @see ";
  protected final String TEXT_382 = NL + "\t * @see #isSet";
  protected final String TEXT_383 = "()";
  protected final String TEXT_384 = NL + "\t * @see #unset";
  protected final String TEXT_385 = "()";
  protected final String TEXT_386 = NL + "\t * @see #set";
  protected final String TEXT_387 = "(";
  protected final String TEXT_388 = ")";
  protected final String TEXT_389 = NL + "\t * @see ";
  protected final String TEXT_390 = "#get";
  protected final String TEXT_391 = "()";
  protected final String TEXT_392 = NL + "\t * @see ";
  protected final String TEXT_393 = "#";
  protected final String TEXT_394 = NL + "\t * @model ";
  protected final String TEXT_395 = NL + "\t *        ";
  protected final String TEXT_396 = NL + "\t * @model";
  protected final String TEXT_397 = NL + "\t * @generated" + NL + "\t */";
  protected final String TEXT_398 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */";
  protected final String TEXT_399 = NL + "\t";
  protected final String TEXT_400 = " ";
  protected final String TEXT_401 = "();" + NL;
  protected final String TEXT_402 = NL + "\t@SuppressWarnings(\"unchecked\")";
  protected final String TEXT_403 = NL + "\tpublic ";
  protected final String TEXT_404 = " ";
  protected final String TEXT_405 = "_";
  protected final String TEXT_406 = "()" + NL + "\t{";
  protected final String TEXT_407 = NL + "\t\treturn ";
  protected final String TEXT_408 = "(";
  protected final String TEXT_409 = "(";
  protected final String TEXT_410 = ")eDynamicGet(";
  protected final String TEXT_411 = " - ESTATIC_FEATURE_COUNT";
  protected final String TEXT_412 = ", ";
  protected final String TEXT_413 = ", true, ";
  protected final String TEXT_414 = ")";
  protected final String TEXT_415 = ").";
  protected final String TEXT_416 = "()";
  protected final String TEXT_417 = ";";
  protected final String TEXT_418 = NL + "\t\t";
  protected final String TEXT_419 = NL + "\t\treturn ";
  protected final String TEXT_420 = "(";
  protected final String TEXT_421 = "(";
  protected final String TEXT_422 = ")eGet(";
  protected final String TEXT_423 = ", true)";
  protected final String TEXT_424 = ").";
  protected final String TEXT_425 = "()";
  protected final String TEXT_426 = ";";
  protected final String TEXT_427 = NL + "\t\treturn ";
  protected final String TEXT_428 = "(";
  protected final String TEXT_429 = "(";
  protected final String TEXT_430 = ")";
  protected final String TEXT_431 = "__ESETTING_DELEGATE.dynamicGet(this, null, 0, true, false)";
  protected final String TEXT_432 = ").";
  protected final String TEXT_433 = "()";
  protected final String TEXT_434 = ";";
  protected final String TEXT_435 = NL + "\t\t";
  protected final String TEXT_436 = " ";
  protected final String TEXT_437 = " = (";
  protected final String TEXT_438 = ")eVirtualGet(";
  protected final String TEXT_439 = ");";
  protected final String TEXT_440 = NL + "\t\tif (";
  protected final String TEXT_441 = " == null)" + NL + "\t\t{";
  protected final String TEXT_442 = NL + "\t\t\teVirtualSet(";
  protected final String TEXT_443 = ", ";
  protected final String TEXT_444 = " = new ";
  protected final String TEXT_445 = ");";
  protected final String TEXT_446 = NL + "\t\t\t";
  protected final String TEXT_447 = " = new ";
  protected final String TEXT_448 = ";";
  protected final String TEXT_449 = NL + "\t\t}" + NL + "\t\treturn ";
  protected final String TEXT_450 = ";";
  protected final String TEXT_451 = NL + "\t\tif (eContainerFeatureID() != ";
  protected final String TEXT_452 = ") return null;" + NL + "\t\treturn (";
  protected final String TEXT_453 = ")";
  protected final String TEXT_454 = "eContainer";
  protected final String TEXT_455 = "eInternalContainer";
  protected final String TEXT_456 = "();";
  protected final String TEXT_457 = NL + "\t\t";
  protected final String TEXT_458 = " ";
  protected final String TEXT_459 = " = (";
  protected final String TEXT_460 = ")eVirtualGet(";
  protected final String TEXT_461 = ", ";
  protected final String TEXT_462 = ");";
  protected final String TEXT_463 = NL + "\t\tif (";
  protected final String TEXT_464 = " != null && ";
  protected final String TEXT_465 = ".eIsProxy())" + NL + "\t\t{" + NL + "\t\t\t";
  protected final String TEXT_466 = " old";
  protected final String TEXT_467 = " = (";
  protected final String TEXT_468 = ")";
  protected final String TEXT_469 = ";" + NL + "\t\t\t";
  protected final String TEXT_470 = " = ";
  protected final String TEXT_471 = "eResolveProxy(old";
  protected final String TEXT_472 = ");" + NL + "\t\t\tif (";
  protected final String TEXT_473 = " != old";
  protected final String TEXT_474 = ")" + NL + "\t\t\t{";
  protected final String TEXT_475 = NL + "\t\t\t\t";
  protected final String TEXT_476 = " new";
  protected final String TEXT_477 = " = (";
  protected final String TEXT_478 = ")";
  protected final String TEXT_479 = ";";
  protected final String TEXT_480 = NL + "\t\t\t\t";
  protected final String TEXT_481 = " msgs = old";
  protected final String TEXT_482 = ".eInverseRemove(this, EOPPOSITE_FEATURE_BASE - ";
  protected final String TEXT_483 = ", null, null);";
  protected final String TEXT_484 = NL + "\t\t\t\t";
  protected final String TEXT_485 = " msgs =  old";
  protected final String TEXT_486 = ".eInverseRemove(this, ";
  protected final String TEXT_487 = ", ";
  protected final String TEXT_488 = ".class, null);";
  protected final String TEXT_489 = NL + "\t\t\t\tif (new";
  protected final String TEXT_490 = ".eInternalContainer() == null)" + NL + "\t\t\t\t{";
  protected final String TEXT_491 = NL + "\t\t\t\t\tmsgs = new";
  protected final String TEXT_492 = ".eInverseAdd(this, EOPPOSITE_FEATURE_BASE - ";
  protected final String TEXT_493 = ", null, msgs);";
  protected final String TEXT_494 = NL + "\t\t\t\t\tmsgs =  new";
  protected final String TEXT_495 = ".eInverseAdd(this, ";
  protected final String TEXT_496 = ", ";
  protected final String TEXT_497 = ".class, msgs);";
  protected final String TEXT_498 = NL + "\t\t\t\t}" + NL + "\t\t\t\tif (msgs != null) msgs.dispatch();";
  protected final String TEXT_499 = NL + "\t\t\t\teVirtualSet(";
  protected final String TEXT_500 = ", ";
  protected final String TEXT_501 = ");";
  protected final String TEXT_502 = NL + "\t\t\t\tif (eNotificationRequired())" + NL + "\t\t\t\t\teNotify(new ";
  protected final String TEXT_503 = "(this, ";
  protected final String TEXT_504 = ".RESOLVE, ";
  protected final String TEXT_505 = ", old";
  protected final String TEXT_506 = ", ";
  protected final String TEXT_507 = "));";
  protected final String TEXT_508 = NL + "\t\t\t}" + NL + "\t\t}";
  protected final String TEXT_509 = NL + "\t\treturn (";
  protected final String TEXT_510 = ")eVirtualGet(";
  protected final String TEXT_511 = ", ";
  protected final String TEXT_512 = ");";
  protected final String TEXT_513 = NL + "\t\treturn (";
  protected final String TEXT_514 = " & ";
  protected final String TEXT_515 = "_EFLAG) != 0;";
  protected final String TEXT_516 = NL + "\t\treturn ";
  protected final String TEXT_517 = "_EFLAG_VALUES[(";
  protected final String TEXT_518 = " & ";
  protected final String TEXT_519 = "_EFLAG) >>> ";
  protected final String TEXT_520 = "_EFLAG_OFFSET];";
  protected final String TEXT_521 = NL + "\t\treturn ";
  protected final String TEXT_522 = ";";
  protected final String TEXT_523 = NL + "\t\t";
  protected final String TEXT_524 = " ";
  protected final String TEXT_525 = " = basicGet";
  protected final String TEXT_526 = "();" + NL + "\t\treturn ";
  protected final String TEXT_527 = " != null && ";
  protected final String TEXT_528 = ".eIsProxy() ? ";
  protected final String TEXT_529 = "eResolveProxy((";
  protected final String TEXT_530 = ")";
  protected final String TEXT_531 = ") : ";
  protected final String TEXT_532 = ";";
  protected final String TEXT_533 = NL + "\t\treturn new ";
  protected final String TEXT_534 = "((";
  protected final String TEXT_535 = ".Internal)((";
  protected final String TEXT_536 = ".Internal.Wrapper)get";
  protected final String TEXT_537 = "()).featureMap().";
  protected final String TEXT_538 = "list(";
  protected final String TEXT_539 = "));";
  protected final String TEXT_540 = NL + "\t\treturn (";
  protected final String TEXT_541 = ")get";
  protected final String TEXT_542 = "().";
  protected final String TEXT_543 = "list(";
  protected final String TEXT_544 = ");";
  protected final String TEXT_545 = NL + "\t\treturn ((";
  protected final String TEXT_546 = ".Internal.Wrapper)get";
  protected final String TEXT_547 = "()).featureMap().list(";
  protected final String TEXT_548 = ");";
  protected final String TEXT_549 = NL + "\t\treturn get";
  protected final String TEXT_550 = "().list(";
  protected final String TEXT_551 = ");";
  protected final String TEXT_552 = NL + "\t\treturn ";
  protected final String TEXT_553 = "(";
  protected final String TEXT_554 = "(";
  protected final String TEXT_555 = ")";
  protected final String TEXT_556 = "((";
  protected final String TEXT_557 = ".Internal.Wrapper)get";
  protected final String TEXT_558 = "()).featureMap().get(";
  protected final String TEXT_559 = ", true)";
  protected final String TEXT_560 = ").";
  protected final String TEXT_561 = "()";
  protected final String TEXT_562 = ";";
  protected final String TEXT_563 = NL + "\t\treturn ";
  protected final String TEXT_564 = "(";
  protected final String TEXT_565 = "(";
  protected final String TEXT_566 = ")";
  protected final String TEXT_567 = "get";
  protected final String TEXT_568 = "().get(";
  protected final String TEXT_569 = ", true)";
  protected final String TEXT_570 = ").";
  protected final String TEXT_571 = "()";
  protected final String TEXT_572 = ";";
  protected final String TEXT_573 = NL + "\t\t";
  protected final String TEXT_574 = NL + "\t\t";
  protected final String TEXT_575 = NL + "\t\t// TODO: implement this method to return the '";
  protected final String TEXT_576 = "' ";
  protected final String TEXT_577 = NL + "\t\t// Ensure that you remove @generated or mark it @generated NOT";
  protected final String TEXT_578 = NL + "\t\t// The list is expected to implement org.eclipse.emf.ecore.util.InternalEList and org.eclipse.emf.ecore.EStructuralFeature.Setting" + NL + "\t\t// so it's likely that an appropriate subclass of org.eclipse.emf.ecore.util.";
  protected final String TEXT_579 = "EcoreEMap";
  protected final String TEXT_580 = "BasicFeatureMap";
  protected final String TEXT_581 = "EcoreEList";
  protected final String TEXT_582 = " should be used.";
  protected final String TEXT_583 = NL + "\t\tthrow new UnsupportedOperationException();";
  protected final String TEXT_584 = NL + "\t}" + NL;
  protected final String TEXT_585 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */";
  protected final String TEXT_586 = NL + "\tpublic ";
  protected final String TEXT_587 = " basicGet";
  protected final String TEXT_588 = "()" + NL + "\t{";
  protected final String TEXT_589 = NL + "\t\treturn (";
  protected final String TEXT_590 = ")eDynamicGet(";
  protected final String TEXT_591 = " - ESTATIC_FEATURE_COUNT";
  protected final String TEXT_592 = ", ";
  protected final String TEXT_593 = ", false, ";
  protected final String TEXT_594 = ");";
  protected final String TEXT_595 = NL + "\t\treturn ";
  protected final String TEXT_596 = "(";
  protected final String TEXT_597 = "(";
  protected final String TEXT_598 = ")";
  protected final String TEXT_599 = "__ESETTING_DELEGATE.dynamicGet(this, null, 0, false, false)";
  protected final String TEXT_600 = ").";
  protected final String TEXT_601 = "()";
  protected final String TEXT_602 = ";";
  protected final String TEXT_603 = NL + "\t\tif (eContainerFeatureID() != ";
  protected final String TEXT_604 = ") return null;" + NL + "\t\treturn (";
  protected final String TEXT_605 = ")eInternalContainer();";
  protected final String TEXT_606 = NL + "\t\treturn (";
  protected final String TEXT_607 = ")eVirtualGet(";
  protected final String TEXT_608 = ");";
  protected final String TEXT_609 = NL + "\t\treturn ";
  protected final String TEXT_610 = ";";
  protected final String TEXT_611 = NL + "\t\treturn (";
  protected final String TEXT_612 = ")((";
  protected final String TEXT_613 = ".Internal.Wrapper)get";
  protected final String TEXT_614 = "()).featureMap().get(";
  protected final String TEXT_615 = ", false);";
  protected final String TEXT_616 = NL + "\t\treturn (";
  protected final String TEXT_617 = ")get";
  protected final String TEXT_618 = "().get(";
  protected final String TEXT_619 = ", false);";
  protected final String TEXT_620 = NL + "\t\t";
  protected final String TEXT_621 = NL + "\t\t// TODO: implement this method to return the '";
  protected final String TEXT_622 = "' ";
  protected final String TEXT_623 = NL + "\t\t// -> do not perform proxy resolution" + NL + "\t\t// Ensure that you remove @generated or mark it @generated NOT" + NL + "\t\tthrow new UnsupportedOperationException();";
  protected final String TEXT_624 = NL + "\t}" + NL;
  protected final String TEXT_625 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */";
  protected final String TEXT_626 = NL + "\tpublic ";
  protected final String TEXT_627 = " basicSet";
  protected final String TEXT_628 = "(";
  protected final String TEXT_629 = " new";
  protected final String TEXT_630 = ", ";
  protected final String TEXT_631 = " msgs)" + NL + "\t{";
  protected final String TEXT_632 = NL + "\t\tmsgs = eBasicSetContainer((";
  protected final String TEXT_633 = ")new";
  protected final String TEXT_634 = ", ";
  protected final String TEXT_635 = ", msgs);";
  protected final String TEXT_636 = NL + "\t\treturn msgs;";
  protected final String TEXT_637 = NL + "\t\tmsgs = eDynamicInverseAdd((";
  protected final String TEXT_638 = ")new";
  protected final String TEXT_639 = ", ";
  protected final String TEXT_640 = ", msgs);";
  protected final String TEXT_641 = NL + "\t\treturn msgs;";
  protected final String TEXT_642 = NL + "\t\tObject old";
  protected final String TEXT_643 = " = eVirtualSet(";
  protected final String TEXT_644 = ", new";
  protected final String TEXT_645 = ");";
  protected final String TEXT_646 = NL + "\t\t";
  protected final String TEXT_647 = " old";
  protected final String TEXT_648 = " = ";
  protected final String TEXT_649 = ";" + NL + "\t\t";
  protected final String TEXT_650 = " = new";
  protected final String TEXT_651 = ";";
  protected final String TEXT_652 = NL + "\t\tboolean isSetChange = old";
  protected final String TEXT_653 = " == EVIRTUAL_NO_VALUE;";
  protected final String TEXT_654 = NL + "\t\tboolean old";
  protected final String TEXT_655 = "ESet = (";
  protected final String TEXT_656 = " & ";
  protected final String TEXT_657 = "_ESETFLAG) != 0;";
  protected final String TEXT_658 = NL + "\t\t";
  protected final String TEXT_659 = " |= ";
  protected final String TEXT_660 = "_ESETFLAG;";
  protected final String TEXT_661 = NL + "\t\tboolean old";
  protected final String TEXT_662 = "ESet = ";
  protected final String TEXT_663 = "ESet;";
  protected final String TEXT_664 = NL + "\t\t";
  protected final String TEXT_665 = "ESet = true;";
  protected final String TEXT_666 = NL + "\t\tif (eNotificationRequired())" + NL + "\t\t{";
  protected final String TEXT_667 = NL + "\t\t\t";
  protected final String TEXT_668 = " notification = new ";
  protected final String TEXT_669 = "(this, ";
  protected final String TEXT_670 = ".SET, ";
  protected final String TEXT_671 = ", ";
  protected final String TEXT_672 = "isSetChange ? null : old";
  protected final String TEXT_673 = "old";
  protected final String TEXT_674 = ", new";
  protected final String TEXT_675 = ", ";
  protected final String TEXT_676 = "isSetChange";
  protected final String TEXT_677 = "!old";
  protected final String TEXT_678 = "ESet";
  protected final String TEXT_679 = ");";
  protected final String TEXT_680 = NL + "\t\t\t";
  protected final String TEXT_681 = " notification = new ";
  protected final String TEXT_682 = "(this, ";
  protected final String TEXT_683 = ".SET, ";
  protected final String TEXT_684 = ", ";
  protected final String TEXT_685 = "old";
  protected final String TEXT_686 = " == EVIRTUAL_NO_VALUE ? null : old";
  protected final String TEXT_687 = "old";
  protected final String TEXT_688 = ", new";
  protected final String TEXT_689 = ");";
  protected final String TEXT_690 = NL + "\t\t\tif (msgs == null) msgs = notification; else msgs.add(notification);" + NL + "\t\t}";
  protected final String TEXT_691 = NL + "\t\treturn msgs;";
  protected final String TEXT_692 = NL + "\t\treturn ((";
  protected final String TEXT_693 = ".Internal)((";
  protected final String TEXT_694 = ".Internal.Wrapper)get";
  protected final String TEXT_695 = "()).featureMap()).basicAdd(";
  protected final String TEXT_696 = ", new";
  protected final String TEXT_697 = ", msgs);";
  protected final String TEXT_698 = NL + "\t\treturn ((";
  protected final String TEXT_699 = ".Internal)get";
  protected final String TEXT_700 = "()).basicAdd(";
  protected final String TEXT_701 = ", new";
  protected final String TEXT_702 = ", msgs);";
  protected final String TEXT_703 = NL + "\t\t// TODO: implement this method to set the contained '";
  protected final String TEXT_704 = "' ";
  protected final String TEXT_705 = NL + "\t\t// -> this method is automatically invoked to keep the containment relationship in synch" + NL + "\t\t// -> do not modify other features" + NL + "\t\t// -> return msgs, after adding any generated Notification to it (if it is null, a NotificationChain object must be created first)" + NL + "\t\t// Ensure that you remove @generated or mark it @generated NOT" + NL + "\t\tthrow new UnsupportedOperationException();";
  protected final String TEXT_706 = NL + "\t}" + NL;
  protected final String TEXT_707 = NL + "\t/**" + NL + "\t * Sets the value of the '{@link ";
  protected final String TEXT_708 = "#";
  protected final String TEXT_709 = " <em>";
  protected final String TEXT_710 = "</em>}' ";
  protected final String TEXT_711 = ".";
  protected final String TEXT_712 = NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @param value the new value of the '<em>";
  protected final String TEXT_713 = "</em>' ";
  protected final String TEXT_714 = ".";
  protected final String TEXT_715 = NL + "\t * @see ";
  protected final String TEXT_716 = NL + "\t * @see #isSet";
  protected final String TEXT_717 = "()";
  protected final String TEXT_718 = NL + "\t * @see #unset";
  protected final String TEXT_719 = "()";
  protected final String TEXT_720 = NL + "\t * @see #";
  protected final String TEXT_721 = "()" + NL + "\t * @generated" + NL + "\t */";
  protected final String TEXT_722 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */";
  protected final String TEXT_723 = NL + "\tvoid set";
  protected final String TEXT_724 = "(";
  protected final String TEXT_725 = " value);" + NL;
  protected final String TEXT_726 = NL + "\tpublic void set";
  protected final String TEXT_727 = "_";
  protected final String TEXT_728 = "(";
  protected final String TEXT_729 = " ";
  protected final String TEXT_730 = ")" + NL + "\t{";
  protected final String TEXT_731 = NL + "\t\teDynamicSet(";
  protected final String TEXT_732 = " - ESTATIC_FEATURE_COUNT";
  protected final String TEXT_733 = ", ";
  protected final String TEXT_734 = ", ";
  protected final String TEXT_735 = "new ";
  protected final String TEXT_736 = "(";
  protected final String TEXT_737 = "new";
  protected final String TEXT_738 = ")";
  protected final String TEXT_739 = ");";
  protected final String TEXT_740 = NL + "\t\teSet(";
  protected final String TEXT_741 = ", ";
  protected final String TEXT_742 = "new ";
  protected final String TEXT_743 = "(";
  protected final String TEXT_744 = "new";
  protected final String TEXT_745 = ")";
  protected final String TEXT_746 = ");";
  protected final String TEXT_747 = NL + "\t\t";
  protected final String TEXT_748 = "__ESETTING_DELEGATE.dynamicSet(this, null, 0, ";
  protected final String TEXT_749 = "new ";
  protected final String TEXT_750 = "(";
  protected final String TEXT_751 = "new";
  protected final String TEXT_752 = ")";
  protected final String TEXT_753 = ");";
  protected final String TEXT_754 = NL + "\t\tif (new";
  protected final String TEXT_755 = " != eInternalContainer() || (eContainerFeatureID() != ";
  protected final String TEXT_756 = " && new";
  protected final String TEXT_757 = " != null))" + NL + "\t\t{" + NL + "\t\t\tif (";
  protected final String TEXT_758 = ".isAncestor(this, ";
  protected final String TEXT_759 = "new";
  protected final String TEXT_760 = "))" + NL + "\t\t\t\tthrow new ";
  protected final String TEXT_761 = "(\"Recursive containment not allowed for \" + toString());";
  protected final String TEXT_762 = NL + "\t\t\t";
  protected final String TEXT_763 = " msgs = null;" + NL + "\t\t\tif (eInternalContainer() != null)" + NL + "\t\t\t\tmsgs = eBasicRemoveFromContainer(msgs);" + NL + "\t\t\tif (new";
  protected final String TEXT_764 = " != null)" + NL + "\t\t\t\tmsgs = ((";
  protected final String TEXT_765 = ")new";
  protected final String TEXT_766 = ").eInverseAdd(this, ";
  protected final String TEXT_767 = ", ";
  protected final String TEXT_768 = ".class, msgs);" + NL + "\t\t\tmsgs = basicSet";
  protected final String TEXT_769 = "(";
  protected final String TEXT_770 = "new";
  protected final String TEXT_771 = ", msgs);" + NL + "\t\t\tif (msgs != null) msgs.dispatch();" + NL + "\t\t}";
  protected final String TEXT_772 = NL + "\t\telse if (eNotificationRequired())" + NL + "\t\t\teNotify(new ";
  protected final String TEXT_773 = "(this, ";
  protected final String TEXT_774 = ".SET, ";
  protected final String TEXT_775 = ", new";
  protected final String TEXT_776 = ", new";
  protected final String TEXT_777 = "));";
  protected final String TEXT_778 = NL + "\t\t";
  protected final String TEXT_779 = " ";
  protected final String TEXT_780 = " = (";
  protected final String TEXT_781 = ")eVirtualGet(";
  protected final String TEXT_782 = ");";
  protected final String TEXT_783 = NL + "\t\tif (new";
  protected final String TEXT_784 = " != ";
  protected final String TEXT_785 = ")" + NL + "\t\t{" + NL + "\t\t\t";
  protected final String TEXT_786 = " msgs = null;" + NL + "\t\t\tif (";
  protected final String TEXT_787 = " != null)";
  protected final String TEXT_788 = NL + "\t\t\t\tmsgs = ((";
  protected final String TEXT_789 = ")";
  protected final String TEXT_790 = ").eInverseRemove(this, EOPPOSITE_FEATURE_BASE - ";
  protected final String TEXT_791 = ", null, msgs);" + NL + "\t\t\tif (new";
  protected final String TEXT_792 = " != null)" + NL + "\t\t\t\tmsgs = ((";
  protected final String TEXT_793 = ")new";
  protected final String TEXT_794 = ").eInverseAdd(this, EOPPOSITE_FEATURE_BASE - ";
  protected final String TEXT_795 = ", null, msgs);";
  protected final String TEXT_796 = NL + "\t\t\t\tmsgs = ((";
  protected final String TEXT_797 = ")";
  protected final String TEXT_798 = ").eInverseRemove(this, ";
  protected final String TEXT_799 = ", ";
  protected final String TEXT_800 = ".class, msgs);" + NL + "\t\t\tif (new";
  protected final String TEXT_801 = " != null)" + NL + "\t\t\t\tmsgs = ((";
  protected final String TEXT_802 = ")new";
  protected final String TEXT_803 = ").eInverseAdd(this, ";
  protected final String TEXT_804 = ", ";
  protected final String TEXT_805 = ".class, msgs);";
  protected final String TEXT_806 = NL + "\t\t\tmsgs = basicSet";
  protected final String TEXT_807 = "(";
  protected final String TEXT_808 = "new";
  protected final String TEXT_809 = ", msgs);" + NL + "\t\t\tif (msgs != null) msgs.dispatch();" + NL + "\t\t}";
  protected final String TEXT_810 = NL + "\t\telse" + NL + "\t\t{";
  protected final String TEXT_811 = NL + "\t\t\tboolean old";
  protected final String TEXT_812 = "ESet = eVirtualIsSet(";
  protected final String TEXT_813 = ");";
  protected final String TEXT_814 = NL + "\t\t\tboolean old";
  protected final String TEXT_815 = "ESet = (";
  protected final String TEXT_816 = " & ";
  protected final String TEXT_817 = "_ESETFLAG) != 0;";
  protected final String TEXT_818 = NL + "\t\t\t";
  protected final String TEXT_819 = " |= ";
  protected final String TEXT_820 = "_ESETFLAG;";
  protected final String TEXT_821 = NL + "\t\t\tboolean old";
  protected final String TEXT_822 = "ESet = ";
  protected final String TEXT_823 = "ESet;";
  protected final String TEXT_824 = NL + "\t\t\t";
  protected final String TEXT_825 = "ESet = true;";
  protected final String TEXT_826 = NL + "\t\t\tif (eNotificationRequired())" + NL + "\t\t\t\teNotify(new ";
  protected final String TEXT_827 = "(this, ";
  protected final String TEXT_828 = ".SET, ";
  protected final String TEXT_829 = ", new";
  protected final String TEXT_830 = ", new";
  protected final String TEXT_831 = ", !old";
  protected final String TEXT_832 = "ESet));";
  protected final String TEXT_833 = NL + "\t\t}";
  protected final String TEXT_834 = NL + "\t\telse if (eNotificationRequired())" + NL + "\t\t\teNotify(new ";
  protected final String TEXT_835 = "(this, ";
  protected final String TEXT_836 = ".SET, ";
  protected final String TEXT_837 = ", new";
  protected final String TEXT_838 = ", new";
  protected final String TEXT_839 = "));";
  protected final String TEXT_840 = NL + "\t\t";
  protected final String TEXT_841 = " old";
  protected final String TEXT_842 = " = (";
  protected final String TEXT_843 = " & ";
  protected final String TEXT_844 = "_EFLAG) != 0;";
  protected final String TEXT_845 = NL + "\t\t";
  protected final String TEXT_846 = " old";
  protected final String TEXT_847 = " = ";
  protected final String TEXT_848 = "_EFLAG_VALUES[(";
  protected final String TEXT_849 = " & ";
  protected final String TEXT_850 = "_EFLAG) >>> ";
  protected final String TEXT_851 = "_EFLAG_OFFSET];";
  protected final String TEXT_852 = NL + "\t\tif (new";
  protected final String TEXT_853 = ") ";
  protected final String TEXT_854 = " |= ";
  protected final String TEXT_855 = "_EFLAG; else ";
  protected final String TEXT_856 = " &= ~";
  protected final String TEXT_857 = "_EFLAG;";
  protected final String TEXT_858 = NL + "\t\tif (new";
  protected final String TEXT_859 = " == null) new";
  protected final String TEXT_860 = " = ";
  protected final String TEXT_861 = "_EDEFAULT;" + NL + "\t\t";
  protected final String TEXT_862 = " = ";
  protected final String TEXT_863 = " & ~";
  protected final String TEXT_864 = "_EFLAG | ";
  protected final String TEXT_865 = "new";
  protected final String TEXT_866 = ".ordinal()";
  protected final String TEXT_867 = ".VALUES.indexOf(new";
  protected final String TEXT_868 = ")";
  protected final String TEXT_869 = " << ";
  protected final String TEXT_870 = "_EFLAG_OFFSET;";
  protected final String TEXT_871 = NL + "\t\t";
  protected final String TEXT_872 = " old";
  protected final String TEXT_873 = " = ";
  protected final String TEXT_874 = ";";
  protected final String TEXT_875 = NL + "\t\t";
  protected final String TEXT_876 = " ";
  protected final String TEXT_877 = " = new";
  protected final String TEXT_878 = " == null ? ";
  protected final String TEXT_879 = " : new";
  protected final String TEXT_880 = ";";
  protected final String TEXT_881 = NL + "\t\t";
  protected final String TEXT_882 = " = new";
  protected final String TEXT_883 = " == null ? ";
  protected final String TEXT_884 = " : new";
  protected final String TEXT_885 = ";";
  protected final String TEXT_886 = NL + "\t\t";
  protected final String TEXT_887 = " ";
  protected final String TEXT_888 = " = ";
  protected final String TEXT_889 = "new";
  protected final String TEXT_890 = ";";
  protected final String TEXT_891 = NL + "\t\t";
  protected final String TEXT_892 = " = ";
  protected final String TEXT_893 = "new";
  protected final String TEXT_894 = ";";
  protected final String TEXT_895 = NL + "\t\tObject old";
  protected final String TEXT_896 = " = eVirtualSet(";
  protected final String TEXT_897 = ", ";
  protected final String TEXT_898 = ");";
  protected final String TEXT_899 = NL + "\t\tboolean isSetChange = old";
  protected final String TEXT_900 = " == EVIRTUAL_NO_VALUE;";
  protected final String TEXT_901 = NL + "\t\tboolean old";
  protected final String TEXT_902 = "ESet = (";
  protected final String TEXT_903 = " & ";
  protected final String TEXT_904 = "_ESETFLAG) != 0;";
  protected final String TEXT_905 = NL + "\t\t";
  protected final String TEXT_906 = " |= ";
  protected final String TEXT_907 = "_ESETFLAG;";
  protected final String TEXT_908 = NL + "\t\tboolean old";
  protected final String TEXT_909 = "ESet = ";
  protected final String TEXT_910 = "ESet;";
  protected final String TEXT_911 = NL + "\t\t";
  protected final String TEXT_912 = "ESet = true;";
  protected final String TEXT_913 = NL + "\t\tif (eNotificationRequired())" + NL + "\t\t\teNotify(new ";
  protected final String TEXT_914 = "(this, ";
  protected final String TEXT_915 = ".SET, ";
  protected final String TEXT_916 = ", ";
  protected final String TEXT_917 = "isSetChange ? ";
  protected final String TEXT_918 = " : old";
  protected final String TEXT_919 = "old";
  protected final String TEXT_920 = ", ";
  protected final String TEXT_921 = "new";
  protected final String TEXT_922 = ", ";
  protected final String TEXT_923 = "isSetChange";
  protected final String TEXT_924 = "!old";
  protected final String TEXT_925 = "ESet";
  protected final String TEXT_926 = "));";
  protected final String TEXT_927 = NL + "\t\tif (eNotificationRequired())" + NL + "\t\t\teNotify(new ";
  protected final String TEXT_928 = "(this, ";
  protected final String TEXT_929 = ".SET, ";
  protected final String TEXT_930 = ", ";
  protected final String TEXT_931 = "old";
  protected final String TEXT_932 = " == EVIRTUAL_NO_VALUE ? ";
  protected final String TEXT_933 = " : old";
  protected final String TEXT_934 = "old";
  protected final String TEXT_935 = ", ";
  protected final String TEXT_936 = "new";
  protected final String TEXT_937 = "));";
  protected final String TEXT_938 = NL + "\t\t((";
  protected final String TEXT_939 = ".Internal)((";
  protected final String TEXT_940 = ".Internal.Wrapper)get";
  protected final String TEXT_941 = "()).featureMap()).set(";
  protected final String TEXT_942 = ", ";
  protected final String TEXT_943 = "new ";
  protected final String TEXT_944 = "(";
  protected final String TEXT_945 = "new";
  protected final String TEXT_946 = ")";
  protected final String TEXT_947 = ");";
  protected final String TEXT_948 = NL + "\t\t((";
  protected final String TEXT_949 = ".Internal)get";
  protected final String TEXT_950 = "()).set(";
  protected final String TEXT_951 = ", ";
  protected final String TEXT_952 = "new ";
  protected final String TEXT_953 = "(";
  protected final String TEXT_954 = "new";
  protected final String TEXT_955 = ")";
  protected final String TEXT_956 = ");";
  protected final String TEXT_957 = NL + "\t\t";
  protected final String TEXT_958 = NL + "\t\t// TODO: implement this method to set the '";
  protected final String TEXT_959 = "' ";
  protected final String TEXT_960 = NL + "\t\t// Ensure that you remove @generated or mark it @generated NOT" + NL + "\t\tthrow new UnsupportedOperationException();";
  protected final String TEXT_961 = NL + "\t}" + NL;
  protected final String TEXT_962 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */";
  protected final String TEXT_963 = NL + "\tpublic ";
  protected final String TEXT_964 = " basicUnset";
  protected final String TEXT_965 = "(";
  protected final String TEXT_966 = " msgs)" + NL + "\t{";
  protected final String TEXT_967 = NL + "\t\treturn eDynamicInverseRemove((";
  protected final String TEXT_968 = ")";
  protected final String TEXT_969 = "basicGet";
  protected final String TEXT_970 = "(), ";
  protected final String TEXT_971 = ", msgs);";
  protected final String TEXT_972 = "Object old";
  protected final String TEXT_973 = " = ";
  protected final String TEXT_974 = "eVirtualUnset(";
  protected final String TEXT_975 = ");";
  protected final String TEXT_976 = NL + "\t\t";
  protected final String TEXT_977 = " old";
  protected final String TEXT_978 = " = ";
  protected final String TEXT_979 = ";";
  protected final String TEXT_980 = NL + "\t\t";
  protected final String TEXT_981 = " = null;";
  protected final String TEXT_982 = NL + "\t\tboolean isSetChange = old";
  protected final String TEXT_983 = " != EVIRTUAL_NO_VALUE;";
  protected final String TEXT_984 = NL + "\t\tboolean old";
  protected final String TEXT_985 = "ESet = (";
  protected final String TEXT_986 = " & ";
  protected final String TEXT_987 = "_ESETFLAG) != 0;";
  protected final String TEXT_988 = NL + "\t\t";
  protected final String TEXT_989 = " &= ~";
  protected final String TEXT_990 = "_ESETFLAG;";
  protected final String TEXT_991 = NL + "\t\tboolean old";
  protected final String TEXT_992 = "ESet = ";
  protected final String TEXT_993 = "ESet;";
  protected final String TEXT_994 = NL + "\t\t";
  protected final String TEXT_995 = "ESet = false;";
  protected final String TEXT_996 = NL + "\t\tif (eNotificationRequired())" + NL + "\t\t{" + NL + "\t\t\t";
  protected final String TEXT_997 = " notification = new ";
  protected final String TEXT_998 = "(this, ";
  protected final String TEXT_999 = ".UNSET, ";
  protected final String TEXT_1000 = ", ";
  protected final String TEXT_1001 = "isSetChange ? old";
  protected final String TEXT_1002 = " : null";
  protected final String TEXT_1003 = "old";
  protected final String TEXT_1004 = ", null, ";
  protected final String TEXT_1005 = "isSetChange";
  protected final String TEXT_1006 = "old";
  protected final String TEXT_1007 = "ESet";
  protected final String TEXT_1008 = ");" + NL + "\t\t\tif (msgs == null) msgs = notification; else msgs.add(notification);" + NL + "\t\t}" + NL + "\t\treturn msgs;";
  protected final String TEXT_1009 = NL + "\t\t// TODO: implement this method to unset the contained '";
  protected final String TEXT_1010 = "' ";
  protected final String TEXT_1011 = NL + "\t\t// -> this method is automatically invoked to keep the containment relationship in synch" + NL + "\t\t// -> do not modify other features" + NL + "\t\t// -> return msgs, after adding any generated Notification to it (if it is null, a NotificationChain object must be created first)" + NL + "\t\t// Ensure that you remove @generated or mark it @generated NOT" + NL + "\t\tthrow new UnsupportedOperationException();";
  protected final String TEXT_1012 = NL + "\t}" + NL;
  protected final String TEXT_1013 = NL + "\t/**" + NL + "\t * Unsets the value of the '{@link ";
  protected final String TEXT_1014 = "#";
  protected final String TEXT_1015 = " <em>";
  protected final String TEXT_1016 = "</em>}' ";
  protected final String TEXT_1017 = ".";
  protected final String TEXT_1018 = NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->";
  protected final String TEXT_1019 = NL + "\t * @see #isSet";
  protected final String TEXT_1020 = "()";
  protected final String TEXT_1021 = NL + "\t * @see #";
  protected final String TEXT_1022 = "()";
  protected final String TEXT_1023 = NL + "\t * @see #set";
  protected final String TEXT_1024 = "(";
  protected final String TEXT_1025 = ")";
  protected final String TEXT_1026 = NL + "\t * @generated" + NL + "\t */";
  protected final String TEXT_1027 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */";
  protected final String TEXT_1028 = NL + "\tvoid unset";
  protected final String TEXT_1029 = "();" + NL;
  protected final String TEXT_1030 = NL + "\tpublic void unset";
  protected final String TEXT_1031 = "_";
  protected final String TEXT_1032 = "()" + NL + "\t{";
  protected final String TEXT_1033 = NL + "\t\teDynamicUnset(";
  protected final String TEXT_1034 = " - ESTATIC_FEATURE_COUNT";
  protected final String TEXT_1035 = ", ";
  protected final String TEXT_1036 = ");";
  protected final String TEXT_1037 = NL + "\t\teUnset(";
  protected final String TEXT_1038 = ");";
  protected final String TEXT_1039 = NL + "\t\t";
  protected final String TEXT_1040 = "__ESETTING_DELEGATE.dynamicUnset(this, null, 0);";
  protected final String TEXT_1041 = NL + "\t\t";
  protected final String TEXT_1042 = " ";
  protected final String TEXT_1043 = " = (";
  protected final String TEXT_1044 = ")eVirtualGet(";
  protected final String TEXT_1045 = ");";
  protected final String TEXT_1046 = NL + "\t\tif (";
  protected final String TEXT_1047 = " != null) ((";
  protected final String TEXT_1048 = ".Unsettable";
  protected final String TEXT_1049 = ")";
  protected final String TEXT_1050 = ").unset();";
  protected final String TEXT_1051 = NL + "\t\t";
  protected final String TEXT_1052 = " ";
  protected final String TEXT_1053 = " = (";
  protected final String TEXT_1054 = ")eVirtualGet(";
  protected final String TEXT_1055 = ");";
  protected final String TEXT_1056 = NL + "\t\tif (";
  protected final String TEXT_1057 = " != null)" + NL + "\t\t{" + NL + "\t\t\t";
  protected final String TEXT_1058 = " msgs = null;";
  protected final String TEXT_1059 = NL + "\t\t\tmsgs = ((";
  protected final String TEXT_1060 = ")";
  protected final String TEXT_1061 = ").eInverseRemove(this, EOPPOSITE_FEATURE_BASE - ";
  protected final String TEXT_1062 = ", null, msgs);";
  protected final String TEXT_1063 = NL + "\t\t\tmsgs = ((";
  protected final String TEXT_1064 = ")";
  protected final String TEXT_1065 = ").eInverseRemove(this, ";
  protected final String TEXT_1066 = ", ";
  protected final String TEXT_1067 = ".class, msgs);";
  protected final String TEXT_1068 = NL + "\t\t\tmsgs = basicUnset";
  protected final String TEXT_1069 = "(msgs);" + NL + "\t\t\tif (msgs != null) msgs.dispatch();" + NL + "\t\t}" + NL + "\t\telse" + NL + "\t\t{";
  protected final String TEXT_1070 = NL + "\t\t\tboolean old";
  protected final String TEXT_1071 = "ESet = eVirtualIsSet(";
  protected final String TEXT_1072 = ");";
  protected final String TEXT_1073 = NL + "\t\t\tboolean old";
  protected final String TEXT_1074 = "ESet = (";
  protected final String TEXT_1075 = " & ";
  protected final String TEXT_1076 = "_ESETFLAG) != 0;";
  protected final String TEXT_1077 = NL + "\t\t\t";
  protected final String TEXT_1078 = " &= ~";
  protected final String TEXT_1079 = "_ESETFLAG;";
  protected final String TEXT_1080 = NL + "\t\t\tboolean old";
  protected final String TEXT_1081 = "ESet = ";
  protected final String TEXT_1082 = "ESet;";
  protected final String TEXT_1083 = NL + "\t\t\t";
  protected final String TEXT_1084 = "ESet = false;";
  protected final String TEXT_1085 = NL + "\t\t\tif (eNotificationRequired())" + NL + "\t\t\t\teNotify(new ";
  protected final String TEXT_1086 = "(this, ";
  protected final String TEXT_1087 = ".UNSET, ";
  protected final String TEXT_1088 = ", null, null, old";
  protected final String TEXT_1089 = "ESet));";
  protected final String TEXT_1090 = NL + "\t\t}";
  protected final String TEXT_1091 = NL + "\t\t";
  protected final String TEXT_1092 = " old";
  protected final String TEXT_1093 = " = (";
  protected final String TEXT_1094 = " & ";
  protected final String TEXT_1095 = "_EFLAG) != 0;";
  protected final String TEXT_1096 = NL + "\t\t";
  protected final String TEXT_1097 = " old";
  protected final String TEXT_1098 = " = ";
  protected final String TEXT_1099 = "_EFLAG_VALUES[(";
  protected final String TEXT_1100 = " & ";
  protected final String TEXT_1101 = "_EFLAG) >>> ";
  protected final String TEXT_1102 = "_EFLAG_OFFSET];";
  protected final String TEXT_1103 = NL + "\t\tObject old";
  protected final String TEXT_1104 = " = eVirtualUnset(";
  protected final String TEXT_1105 = ");";
  protected final String TEXT_1106 = NL + "\t\t";
  protected final String TEXT_1107 = " old";
  protected final String TEXT_1108 = " = ";
  protected final String TEXT_1109 = ";";
  protected final String TEXT_1110 = NL + "\t\tboolean isSetChange = old";
  protected final String TEXT_1111 = " != EVIRTUAL_NO_VALUE;";
  protected final String TEXT_1112 = NL + "\t\tboolean old";
  protected final String TEXT_1113 = "ESet = (";
  protected final String TEXT_1114 = " & ";
  protected final String TEXT_1115 = "_ESETFLAG) != 0;";
  protected final String TEXT_1116 = NL + "\t\tboolean old";
  protected final String TEXT_1117 = "ESet = ";
  protected final String TEXT_1118 = "ESet;";
  protected final String TEXT_1119 = NL + "\t\t";
  protected final String TEXT_1120 = " = null;";
  protected final String TEXT_1121 = NL + "\t\t";
  protected final String TEXT_1122 = " &= ~";
  protected final String TEXT_1123 = "_ESETFLAG;";
  protected final String TEXT_1124 = NL + "\t\t";
  protected final String TEXT_1125 = "ESet = false;";
  protected final String TEXT_1126 = NL + "\t\tif (eNotificationRequired())" + NL + "\t\t\teNotify(new ";
  protected final String TEXT_1127 = "(this, ";
  protected final String TEXT_1128 = ".UNSET, ";
  protected final String TEXT_1129 = ", ";
  protected final String TEXT_1130 = "isSetChange ? old";
  protected final String TEXT_1131 = " : null";
  protected final String TEXT_1132 = "old";
  protected final String TEXT_1133 = ", null, ";
  protected final String TEXT_1134 = "isSetChange";
  protected final String TEXT_1135 = "old";
  protected final String TEXT_1136 = "ESet";
  protected final String TEXT_1137 = "));";
  protected final String TEXT_1138 = NL + "\t\tif (";
  protected final String TEXT_1139 = ") ";
  protected final String TEXT_1140 = " |= ";
  protected final String TEXT_1141 = "_EFLAG; else ";
  protected final String TEXT_1142 = " &= ~";
  protected final String TEXT_1143 = "_EFLAG;";
  protected final String TEXT_1144 = NL + "\t\t";
  protected final String TEXT_1145 = " = ";
  protected final String TEXT_1146 = " & ~";
  protected final String TEXT_1147 = "_EFLAG | ";
  protected final String TEXT_1148 = "_EFLAG_DEFAULT;";
  protected final String TEXT_1149 = NL + "\t\t";
  protected final String TEXT_1150 = " = ";
  protected final String TEXT_1151 = ";";
  protected final String TEXT_1152 = NL + "\t\t";
  protected final String TEXT_1153 = " &= ~";
  protected final String TEXT_1154 = "_ESETFLAG;";
  protected final String TEXT_1155 = NL + "\t\t";
  protected final String TEXT_1156 = "ESet = false;";
  protected final String TEXT_1157 = NL + "\t\tif (eNotificationRequired())" + NL + "\t\t\teNotify(new ";
  protected final String TEXT_1158 = "(this, ";
  protected final String TEXT_1159 = ".UNSET, ";
  protected final String TEXT_1160 = ", ";
  protected final String TEXT_1161 = "isSetChange ? old";
  protected final String TEXT_1162 = " : ";
  protected final String TEXT_1163 = "old";
  protected final String TEXT_1164 = ", ";
  protected final String TEXT_1165 = ", ";
  protected final String TEXT_1166 = "isSetChange";
  protected final String TEXT_1167 = "old";
  protected final String TEXT_1168 = "ESet";
  protected final String TEXT_1169 = "));";
  protected final String TEXT_1170 = NL + "\t\t((";
  protected final String TEXT_1171 = ".Internal)((";
  protected final String TEXT_1172 = ".Internal.Wrapper)get";
  protected final String TEXT_1173 = "()).featureMap()).clear(";
  protected final String TEXT_1174 = ");";
  protected final String TEXT_1175 = NL + "\t\t((";
  protected final String TEXT_1176 = ".Internal)get";
  protected final String TEXT_1177 = "()).clear(";
  protected final String TEXT_1178 = ");";
  protected final String TEXT_1179 = NL + "\t\t";
  protected final String TEXT_1180 = NL + "\t\t// TODO: implement this method to unset the '";
  protected final String TEXT_1181 = "' ";
  protected final String TEXT_1182 = NL + "\t\t// Ensure that you remove @generated or mark it @generated NOT" + NL + "\t\tthrow new UnsupportedOperationException();";
  protected final String TEXT_1183 = NL + "\t}" + NL;
  protected final String TEXT_1184 = NL + "\t/**" + NL + "\t * Returns whether the value of the '{@link ";
  protected final String TEXT_1185 = "#";
  protected final String TEXT_1186 = " <em>";
  protected final String TEXT_1187 = "</em>}' ";
  protected final String TEXT_1188 = " is set.";
  protected final String TEXT_1189 = NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @return whether the value of the '<em>";
  protected final String TEXT_1190 = "</em>' ";
  protected final String TEXT_1191 = " is set.";
  protected final String TEXT_1192 = NL + "\t * @see #unset";
  protected final String TEXT_1193 = "()";
  protected final String TEXT_1194 = NL + "\t * @see #";
  protected final String TEXT_1195 = "()";
  protected final String TEXT_1196 = NL + "\t * @see #set";
  protected final String TEXT_1197 = "(";
  protected final String TEXT_1198 = ")";
  protected final String TEXT_1199 = NL + "\t * @generated" + NL + "\t */";
  protected final String TEXT_1200 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */";
  protected final String TEXT_1201 = NL + "\tboolean isSet";
  protected final String TEXT_1202 = "();" + NL;
  protected final String TEXT_1203 = NL + "\tpublic boolean isSet";
  protected final String TEXT_1204 = "_";
  protected final String TEXT_1205 = "()" + NL + "\t{";
  protected final String TEXT_1206 = NL + "\t\treturn eDynamicIsSet(";
  protected final String TEXT_1207 = " - ESTATIC_FEATURE_COUNT";
  protected final String TEXT_1208 = ", ";
  protected final String TEXT_1209 = ");";
  protected final String TEXT_1210 = NL + "\t\treturn eIsSet(";
  protected final String TEXT_1211 = ");";
  protected final String TEXT_1212 = NL + "\t\treturn ";
  protected final String TEXT_1213 = "__ESETTING_DELEGATE.dynamicIsSet(this, null, 0);";
  protected final String TEXT_1214 = NL + "\t\t";
  protected final String TEXT_1215 = " ";
  protected final String TEXT_1216 = " = (";
  protected final String TEXT_1217 = ")eVirtualGet(";
  protected final String TEXT_1218 = ");";
  protected final String TEXT_1219 = NL + "\t\treturn ";
  protected final String TEXT_1220 = " != null && ((";
  protected final String TEXT_1221 = ".Unsettable";
  protected final String TEXT_1222 = ")";
  protected final String TEXT_1223 = ").isSet();";
  protected final String TEXT_1224 = NL + "\t\treturn eVirtualIsSet(";
  protected final String TEXT_1225 = ");";
  protected final String TEXT_1226 = NL + "\t\treturn (";
  protected final String TEXT_1227 = " & ";
  protected final String TEXT_1228 = "_ESETFLAG) != 0;";
  protected final String TEXT_1229 = NL + "\t\treturn ";
  protected final String TEXT_1230 = "ESet;";
  protected final String TEXT_1231 = NL + "\t\treturn !((";
  protected final String TEXT_1232 = ".Internal)((";
  protected final String TEXT_1233 = ".Internal.Wrapper)get";
  protected final String TEXT_1234 = "()).featureMap()).isEmpty(";
  protected final String TEXT_1235 = ");";
  protected final String TEXT_1236 = NL + "\t\treturn !((";
  protected final String TEXT_1237 = ".Internal)get";
  protected final String TEXT_1238 = "()).isEmpty(";
  protected final String TEXT_1239 = ");";
  protected final String TEXT_1240 = NL + "\t\t";
  protected final String TEXT_1241 = NL + "\t\t// TODO: implement this method to return whether the '";
  protected final String TEXT_1242 = "' ";
  protected final String TEXT_1243 = " is set" + NL + "\t\t// Ensure that you remove @generated or mark it @generated NOT" + NL + "\t\tthrow new UnsupportedOperationException();";
  protected final String TEXT_1244 = NL + "\t}" + NL;
  protected final String TEXT_1245 = NL + "\t/**" + NL + "\t * The cached validation expression for the '{@link #";
  protected final String TEXT_1246 = "(";
  protected final String TEXT_1247 = ") <em>";
  protected final String TEXT_1248 = "</em>}' invariant operation." + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @see #";
  protected final String TEXT_1249 = "(";
  protected final String TEXT_1250 = ")" + NL + "\t * @generated" + NL + "\t * @ordered" + NL + "\t */" + NL + "\tprotected static final ";
  protected final String TEXT_1251 = " ";
  protected final String TEXT_1252 = "__EEXPRESSION = \"";
  protected final String TEXT_1253 = "\";";
  protected final String TEXT_1254 = NL;
  protected final String TEXT_1255 = NL + "\t/**" + NL + "\t * The cached invocation delegate for the '{@link #";
  protected final String TEXT_1256 = "(";
  protected final String TEXT_1257 = ") <em>";
  protected final String TEXT_1258 = "</em>}' operation." + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @see #";
  protected final String TEXT_1259 = "(";
  protected final String TEXT_1260 = ")" + NL + "\t * @generated" + NL + "\t * @ordered" + NL + "\t */" + NL + "\tprotected static final ";
  protected final String TEXT_1261 = ".Internal.InvocationDelegate ";
  protected final String TEXT_1262 = "__EINVOCATION_DELEGATE = ((";
  protected final String TEXT_1263 = ".Internal)";
  protected final String TEXT_1264 = ").getInvocationDelegate();" + NL;
  protected final String TEXT_1265 = NL + "\t/**";
  protected final String TEXT_1266 = NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->";
  protected final String TEXT_1267 = NL + "\t * <!-- begin-model-doc -->";
  protected final String TEXT_1268 = NL + "\t * ";
  protected final String TEXT_1269 = NL + "\t * @param ";
  protected final String TEXT_1270 = NL + "\t *   ";
  protected final String TEXT_1271 = NL + "\t * @param ";
  protected final String TEXT_1272 = " ";
  protected final String TEXT_1273 = NL + "\t * <!-- end-model-doc -->";
  protected final String TEXT_1274 = NL + "\t * @model ";
  protected final String TEXT_1275 = NL + "\t *        ";
  protected final String TEXT_1276 = NL + "\t * @model";
  protected final String TEXT_1277 = NL + "\t * @generated" + NL + "\t */";
  protected final String TEXT_1278 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */";
  protected final String TEXT_1279 = NL + "\t";
  protected final String TEXT_1280 = " ";
  protected final String TEXT_1281 = "(";
  protected final String TEXT_1282 = ")";
  protected final String TEXT_1283 = ";" + NL;
  protected final String TEXT_1284 = NL + "\t@SuppressWarnings(\"unchecked\")";
  protected final String TEXT_1285 = NL + "\tpublic ";
  protected final String TEXT_1286 = " ";
  protected final String TEXT_1287 = "(";
  protected final String TEXT_1288 = ")";
  protected final String TEXT_1289 = NL + "\t{";
  protected final String TEXT_1290 = NL + "\t\t";
  protected final String TEXT_1291 = NL + "\t\treturn" + NL + "\t\t\t";
  protected final String TEXT_1292 = ".validate" + NL + "\t\t\t\t(";
  protected final String TEXT_1293 = "," + NL + "\t\t\t\t this," + NL + "\t\t\t\t ";
  protected final String TEXT_1294 = "," + NL + "\t\t\t\t ";
  protected final String TEXT_1295 = "," + NL + "\t\t\t\t \"";
  protected final String TEXT_1296 = "\",";
  protected final String TEXT_1297 = NL + "\t\t\t\t ";
  protected final String TEXT_1298 = "," + NL + "\t\t\t\t ";
  protected final String TEXT_1299 = "__EEXPRESSION," + NL + "\t\t\t\t ";
  protected final String TEXT_1300 = ".ERROR," + NL + "\t\t\t\t ";
  protected final String TEXT_1301 = ".DIAGNOSTIC_SOURCE," + NL + "\t\t\t\t ";
  protected final String TEXT_1302 = ".";
  protected final String TEXT_1303 = ");";
  protected final String TEXT_1304 = NL + "\t\t// TODO: implement this method" + NL + "\t\t// -> specify the condition that violates the invariant" + NL + "\t\t// -> verify the details of the diagnostic, including severity and message" + NL + "\t\t// Ensure that you remove @generated or mark it @generated NOT" + NL + "\t\tif (false)" + NL + "\t\t{" + NL + "\t\t\tif (";
  protected final String TEXT_1305 = " != null)" + NL + "\t\t\t{" + NL + "\t\t\t\t";
  protected final String TEXT_1306 = ".add" + NL + "\t\t\t\t\t(new ";
  protected final String TEXT_1307 = NL + "\t\t\t\t\t\t(";
  protected final String TEXT_1308 = ".ERROR," + NL + "\t\t\t\t\t\t ";
  protected final String TEXT_1309 = ".DIAGNOSTIC_SOURCE," + NL + "\t\t\t\t\t\t ";
  protected final String TEXT_1310 = ".";
  protected final String TEXT_1311 = "," + NL + "\t\t\t\t\t\t ";
  protected final String TEXT_1312 = ".INSTANCE.getString(\"_UI_GenericInvariant_diagnostic\", new Object[] { \"";
  protected final String TEXT_1313 = "\", ";
  protected final String TEXT_1314 = ".getObjectLabel(this, ";
  protected final String TEXT_1315 = ") }),";
  protected final String TEXT_1316 = NL + "\t\t\t\t\t\t new Object [] { this }));" + NL + "\t\t\t}" + NL + "\t\t\treturn false;" + NL + "\t\t}" + NL + "\t\treturn true;";
  protected final String TEXT_1317 = NL + "\t\ttry" + NL + "\t\t{";
  protected final String TEXT_1318 = NL + "\t\t\t";
  protected final String TEXT_1319 = "__EINVOCATION_DELEGATE.dynamicInvoke(this, ";
  protected final String TEXT_1320 = "new ";
  protected final String TEXT_1321 = ".UnmodifiableEList<Object>(";
  protected final String TEXT_1322 = ", ";
  protected final String TEXT_1323 = ")";
  protected final String TEXT_1324 = "null";
  protected final String TEXT_1325 = ");";
  protected final String TEXT_1326 = NL + "\t\t\treturn ";
  protected final String TEXT_1327 = "(";
  protected final String TEXT_1328 = "(";
  protected final String TEXT_1329 = ")";
  protected final String TEXT_1330 = "__EINVOCATION_DELEGATE.dynamicInvoke(this, ";
  protected final String TEXT_1331 = "new ";
  protected final String TEXT_1332 = ".UnmodifiableEList<Object>(";
  protected final String TEXT_1333 = ", ";
  protected final String TEXT_1334 = ")";
  protected final String TEXT_1335 = "null";
  protected final String TEXT_1336 = ")";
  protected final String TEXT_1337 = ").";
  protected final String TEXT_1338 = "()";
  protected final String TEXT_1339 = ";";
  protected final String TEXT_1340 = NL + "\t\t}" + NL + "\t\tcatch (";
  protected final String TEXT_1341 = " ite)" + NL + "\t\t{" + NL + "\t\t\tthrow new ";
  protected final String TEXT_1342 = "(ite);" + NL + "\t\t}";
  protected final String TEXT_1343 = NL + "\t\t// TODO: implement this method" + NL + "\t\t// Ensure that you remove @generated or mark it @generated NOT" + NL + "\t\tthrow new UnsupportedOperationException();";
  protected final String TEXT_1344 = NL + "\t}" + NL;
  protected final String TEXT_1345 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */";
  protected final String TEXT_1346 = NL + "\t@SuppressWarnings(\"unchecked\")";
  protected final String TEXT_1347 = NL + "\t@Override";
  protected final String TEXT_1348 = NL + "\tpublic ";
  protected final String TEXT_1349 = " eInverseAdd(";
  protected final String TEXT_1350 = " otherEnd, int featureID, ";
  protected final String TEXT_1351 = " msgs)" + NL + "\t{" + NL + "\t\tswitch (featureID";
  protected final String TEXT_1352 = ")" + NL + "\t\t{";
  protected final String TEXT_1353 = NL + "\t\t\tcase ";
  protected final String TEXT_1354 = ":";
  protected final String TEXT_1355 = NL + "\t\t\t\treturn ((";
  protected final String TEXT_1356 = "(";
  protected final String TEXT_1357 = ".InternalMapView";
  protected final String TEXT_1358 = ")";
  protected final String TEXT_1359 = "()).eMap()).basicAdd(otherEnd, msgs);";
  protected final String TEXT_1360 = NL + "\t\t\t\treturn (";
  protected final String TEXT_1361 = "()).basicAdd(otherEnd, msgs);";
  protected final String TEXT_1362 = NL + "\t\t\t\tif (eInternalContainer() != null)" + NL + "\t\t\t\t\tmsgs = eBasicRemoveFromContainer(msgs);";
  protected final String TEXT_1363 = NL + "\t\t\t\treturn basicSet";
  protected final String TEXT_1364 = "((";
  protected final String TEXT_1365 = ")otherEnd, msgs);";
  protected final String TEXT_1366 = NL + "\t\t\t\treturn eBasicSetContainer(otherEnd, ";
  protected final String TEXT_1367 = ", msgs);";
  protected final String TEXT_1368 = NL + "\t\t\t\t";
  protected final String TEXT_1369 = " ";
  protected final String TEXT_1370 = " = (";
  protected final String TEXT_1371 = ")eVirtualGet(";
  protected final String TEXT_1372 = ");";
  protected final String TEXT_1373 = NL + "\t\t\t\t";
  protected final String TEXT_1374 = " ";
  protected final String TEXT_1375 = " = ";
  protected final String TEXT_1376 = "basicGet";
  protected final String TEXT_1377 = "();";
  protected final String TEXT_1378 = NL + "\t\t\t\tif (";
  protected final String TEXT_1379 = " != null)";
  protected final String TEXT_1380 = NL + "\t\t\t\t\tmsgs = ((";
  protected final String TEXT_1381 = ")";
  protected final String TEXT_1382 = ").eInverseRemove(this, EOPPOSITE_FEATURE_BASE - ";
  protected final String TEXT_1383 = ", null, msgs);";
  protected final String TEXT_1384 = NL + "\t\t\t\t\tmsgs = ((";
  protected final String TEXT_1385 = ")";
  protected final String TEXT_1386 = ").eInverseRemove(this, ";
  protected final String TEXT_1387 = ", ";
  protected final String TEXT_1388 = ".class, msgs);";
  protected final String TEXT_1389 = NL + "\t\t\t\treturn basicSet";
  protected final String TEXT_1390 = "((";
  protected final String TEXT_1391 = ")otherEnd, msgs);";
  protected final String TEXT_1392 = NL + "\t\t}";
  protected final String TEXT_1393 = NL + "\t\treturn super.eInverseAdd(otherEnd, featureID, msgs);";
  protected final String TEXT_1394 = NL + "\t\treturn eDynamicInverseAdd(otherEnd, featureID, msgs);";
  protected final String TEXT_1395 = NL + "\t}" + NL;
  protected final String TEXT_1396 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */";
  protected final String TEXT_1397 = NL + "\t@Override";
  protected final String TEXT_1398 = NL + "\tpublic ";
  protected final String TEXT_1399 = " eInverseRemove(";
  protected final String TEXT_1400 = " otherEnd, int featureID, ";
  protected final String TEXT_1401 = " msgs)" + NL + "\t{" + NL + "\t\tswitch (featureID";
  protected final String TEXT_1402 = ")" + NL + "\t\t{";
  protected final String TEXT_1403 = NL + "\t\t\tcase ";
  protected final String TEXT_1404 = ":";
  protected final String TEXT_1405 = NL + "\t\t\t\treturn ((";
  protected final String TEXT_1406 = ")((";
  protected final String TEXT_1407 = ".InternalMapView";
  protected final String TEXT_1408 = ")";
  protected final String TEXT_1409 = "()).eMap()).basicRemove(otherEnd, msgs);";
  protected final String TEXT_1410 = NL + "\t\t\t\treturn ((";
  protected final String TEXT_1411 = ")((";
  protected final String TEXT_1412 = ".Internal.Wrapper)";
  protected final String TEXT_1413 = "()).featureMap()).basicRemove(otherEnd, msgs);";
  protected final String TEXT_1414 = NL + "\t\t\t\treturn ((";
  protected final String TEXT_1415 = ")";
  protected final String TEXT_1416 = "()).basicRemove(otherEnd, msgs);";
  protected final String TEXT_1417 = NL + "\t\t\t\treturn eBasicSetContainer(null, ";
  protected final String TEXT_1418 = ", msgs);";
  protected final String TEXT_1419 = NL + "\t\t\t\treturn basicUnset";
  protected final String TEXT_1420 = "(msgs);";
  protected final String TEXT_1421 = NL + "\t\t\t\treturn basicSet";
  protected final String TEXT_1422 = "(null, msgs);";
  protected final String TEXT_1423 = NL + "\t\t}";
  protected final String TEXT_1424 = NL + "\t\treturn super.eInverseRemove(otherEnd, featureID, msgs);";
  protected final String TEXT_1425 = NL + "\t\treturn eDynamicInverseRemove(otherEnd, featureID, msgs);";
  protected final String TEXT_1426 = NL + "\t}" + NL;
  protected final String TEXT_1427 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */";
  protected final String TEXT_1428 = NL + "\t@Override";
  protected final String TEXT_1429 = NL + "\tpublic ";
  protected final String TEXT_1430 = " eBasicRemoveFromContainerFeature(";
  protected final String TEXT_1431 = " msgs)" + NL + "\t{" + NL + "\t\tswitch (eContainerFeatureID()";
  protected final String TEXT_1432 = ")" + NL + "\t\t{";
  protected final String TEXT_1433 = NL + "\t\t\tcase ";
  protected final String TEXT_1434 = ":" + NL + "\t\t\t\treturn eInternalContainer().eInverseRemove(this, ";
  protected final String TEXT_1435 = ", ";
  protected final String TEXT_1436 = ".class, msgs);";
  protected final String TEXT_1437 = NL + "\t\t}";
  protected final String TEXT_1438 = NL + "\t\treturn super.eBasicRemoveFromContainerFeature(msgs);";
  protected final String TEXT_1439 = NL + "\t\treturn eDynamicBasicRemoveFromContainer(msgs);";
  protected final String TEXT_1440 = NL + "\t}" + NL;
  protected final String TEXT_1441 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */";
  protected final String TEXT_1442 = NL + "\t@Override";
  protected final String TEXT_1443 = NL + "\tpublic Object eGet(int featureID, boolean resolve, boolean coreType)" + NL + "\t{" + NL + "\t\tswitch (featureID";
  protected final String TEXT_1444 = ")" + NL + "\t\t{";
  protected final String TEXT_1445 = NL + "\t\t\tcase ";
  protected final String TEXT_1446 = ":";
  protected final String TEXT_1447 = NL + "\t\t\t\treturn ";
  protected final String TEXT_1448 = "();";
  protected final String TEXT_1449 = NL + "\t\t\t\treturn ";
  protected final String TEXT_1450 = "() ? Boolean.TRUE : Boolean.FALSE;";
  protected final String TEXT_1451 = NL + "\t\t\t\treturn new ";
  protected final String TEXT_1452 = "(";
  protected final String TEXT_1453 = "());";
  protected final String TEXT_1454 = NL + "\t\t\t\tif (resolve) return ";
  protected final String TEXT_1455 = "();" + NL + "\t\t\t\treturn basicGet";
  protected final String TEXT_1456 = "();";
  protected final String TEXT_1457 = NL + "\t\t\t\tif (coreType) return ((";
  protected final String TEXT_1458 = ".InternalMapView";
  protected final String TEXT_1459 = ")";
  protected final String TEXT_1460 = "()).eMap();" + NL + "\t\t\t\telse return ";
  protected final String TEXT_1461 = "();";
  protected final String TEXT_1462 = NL + "\t\t\t\tif (coreType) return ";
  protected final String TEXT_1463 = "();" + NL + "\t\t\t\telse return ";
  protected final String TEXT_1464 = "().map();";
  protected final String TEXT_1465 = NL + "\t\t\t\tif (coreType) return ((";
  protected final String TEXT_1466 = ".Internal.Wrapper)";
  protected final String TEXT_1467 = "()).featureMap();" + NL + "\t\t\t\treturn ";
  protected final String TEXT_1468 = "();";
  protected final String TEXT_1469 = NL + "\t\t\t\tif (coreType) return ";
  protected final String TEXT_1470 = "();" + NL + "\t\t\t\treturn ((";
  protected final String TEXT_1471 = ".Internal)";
  protected final String TEXT_1472 = "()).getWrapper();";
  protected final String TEXT_1473 = NL + "\t\t\t\treturn ";
  protected final String TEXT_1474 = "();";
  protected final String TEXT_1475 = NL + "\t\t}";
  protected final String TEXT_1476 = NL + "\t\treturn super.eGet(featureID, resolve, coreType);";
  protected final String TEXT_1477 = NL + "\t\treturn eDynamicGet(featureID, resolve, coreType);";
  protected final String TEXT_1478 = NL + "\t}" + NL;
  protected final String TEXT_1479 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */";
  protected final String TEXT_1480 = NL + "\t@SuppressWarnings(\"unchecked\")";
  protected final String TEXT_1481 = NL + "\t@Override";
  protected final String TEXT_1482 = NL + "\tpublic void eSet(int featureID, Object newValue)" + NL + "\t{" + NL + "\t\tswitch (featureID";
  protected final String TEXT_1483 = ")" + NL + "\t\t{";
  protected final String TEXT_1484 = NL + "\t\t\tcase ";
  protected final String TEXT_1485 = ":";
  protected final String TEXT_1486 = NL + "\t\t\t\t((";
  protected final String TEXT_1487 = ".Internal)((";
  protected final String TEXT_1488 = ".Internal.Wrapper)";
  protected final String TEXT_1489 = "()).featureMap()).set(newValue);";
  protected final String TEXT_1490 = NL + "\t\t\t\t((";
  protected final String TEXT_1491 = ".Internal)";
  protected final String TEXT_1492 = "()).set(newValue);";
  protected final String TEXT_1493 = NL + "\t\t\t\t((";
  protected final String TEXT_1494 = ".Setting)((";
  protected final String TEXT_1495 = ".InternalMapView";
  protected final String TEXT_1496 = ")";
  protected final String TEXT_1497 = "()).eMap()).set(newValue);";
  protected final String TEXT_1498 = NL + "\t\t\t\t((";
  protected final String TEXT_1499 = ".Setting)";
  protected final String TEXT_1500 = "()).set(newValue);";
  protected final String TEXT_1501 = NL + "\t\t\t\t";
  protected final String TEXT_1502 = "().clear();" + NL + "\t\t\t\t";
  protected final String TEXT_1503 = "().addAll((";
  protected final String TEXT_1504 = "<? extends ";
  protected final String TEXT_1505 = ">";
  protected final String TEXT_1506 = ")newValue);";
  protected final String TEXT_1507 = NL + "\t\t\t\tset";
  protected final String TEXT_1508 = "(((";
  protected final String TEXT_1509 = ")newValue).";
  protected final String TEXT_1510 = "());";
  protected final String TEXT_1511 = NL + "\t\t\t\tset";
  protected final String TEXT_1512 = "(";
  protected final String TEXT_1513 = "(";
  protected final String TEXT_1514 = ")";
  protected final String TEXT_1515 = "newValue);";
  protected final String TEXT_1516 = NL + "\t\t\t\treturn;";
  protected final String TEXT_1517 = NL + "\t\t}";
  protected final String TEXT_1518 = NL + "\t\tsuper.eSet(featureID, newValue);";
  protected final String TEXT_1519 = NL + "\t\teDynamicSet(featureID, newValue);";
  protected final String TEXT_1520 = NL + "\t}" + NL;
  protected final String TEXT_1521 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */";
  protected final String TEXT_1522 = NL + "\t@Override";
  protected final String TEXT_1523 = NL + "\tpublic void eUnset(int featureID)" + NL + "\t{" + NL + "\t\tswitch (featureID";
  protected final String TEXT_1524 = ")" + NL + "\t\t{";
  protected final String TEXT_1525 = NL + "\t\t\tcase ";
  protected final String TEXT_1526 = ":";
  protected final String TEXT_1527 = NL + "\t\t\t\t((";
  protected final String TEXT_1528 = ".Internal.Wrapper)";
  protected final String TEXT_1529 = "()).featureMap().clear();";
  protected final String TEXT_1530 = NL + "\t\t\t\t";
  protected final String TEXT_1531 = "().clear();";
  protected final String TEXT_1532 = NL + "\t\t\t\tunset";
  protected final String TEXT_1533 = "();";
  protected final String TEXT_1534 = NL + "\t\t\t\tset";
  protected final String TEXT_1535 = "((";
  protected final String TEXT_1536 = ")null);";
  protected final String TEXT_1537 = NL + "\t\t\t\t";
  protected final String TEXT_1538 = "__ESETTING_DELEGATE.dynamicUnset(this, null, 0);";
  protected final String TEXT_1539 = NL + "\t\t\t\tset";
  protected final String TEXT_1540 = "(";
  protected final String TEXT_1541 = ");";
  protected final String TEXT_1542 = NL + "\t\t\t\treturn;";
  protected final String TEXT_1543 = NL + "\t\t}";
  protected final String TEXT_1544 = NL + "\t\tsuper.eUnset(featureID);";
  protected final String TEXT_1545 = NL + "\t\teDynamicUnset(featureID);";
  protected final String TEXT_1546 = NL + "\t}" + NL;
  protected final String TEXT_1547 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */";
  protected final String TEXT_1548 = NL + "\t@SuppressWarnings(\"unchecked\")";
  protected final String TEXT_1549 = NL + "\t@Override";
  protected final String TEXT_1550 = NL + "\tpublic boolean eIsSet(int featureID)" + NL + "\t{" + NL + "\t\tswitch (featureID";
  protected final String TEXT_1551 = ")" + NL + "\t\t{";
  protected final String TEXT_1552 = NL + "\t\t\tcase ";
  protected final String TEXT_1553 = ":";
  protected final String TEXT_1554 = NL + "\t\t\t\treturn isSet";
  protected final String TEXT_1555 = "();";
  protected final String TEXT_1556 = NL + "\t\t\t\treturn ";
  protected final String TEXT_1557 = "__ESETTING_DELEGATE.dynamicIsSet(this, null, 0);";
  protected final String TEXT_1558 = NL + "\t\t\t\treturn !((";
  protected final String TEXT_1559 = ".Internal.Wrapper)";
  protected final String TEXT_1560 = "()).featureMap().isEmpty();";
  protected final String TEXT_1561 = NL + "\t\t\t\treturn ";
  protected final String TEXT_1562 = " != null && !";
  protected final String TEXT_1563 = ".featureMap().isEmpty();";
  protected final String TEXT_1564 = NL + "\t\t\t\treturn ";
  protected final String TEXT_1565 = " != null && !";
  protected final String TEXT_1566 = ".isEmpty();";
  protected final String TEXT_1567 = NL + "\t\t\t\t";
  protected final String TEXT_1568 = " ";
  protected final String TEXT_1569 = " = (";
  protected final String TEXT_1570 = ")eVirtualGet(";
  protected final String TEXT_1571 = ");" + NL + "\t\t\t\treturn ";
  protected final String TEXT_1572 = " != null && !";
  protected final String TEXT_1573 = ".isEmpty();";
  protected final String TEXT_1574 = NL + "\t\t\t\treturn !";
  protected final String TEXT_1575 = "().isEmpty();";
  protected final String TEXT_1576 = NL + "\t\t\t\treturn isSet";
  protected final String TEXT_1577 = "();";
  protected final String TEXT_1578 = NL + "\t\t\t\treturn ";
  protected final String TEXT_1579 = " != null;";
  protected final String TEXT_1580 = NL + "\t\t\t\treturn eVirtualGet(";
  protected final String TEXT_1581 = ") != null;";
  protected final String TEXT_1582 = NL + "\t\t\t\treturn basicGet";
  protected final String TEXT_1583 = "() != null;";
  protected final String TEXT_1584 = NL + "\t\t\t\treturn ";
  protected final String TEXT_1585 = " != null;";
  protected final String TEXT_1586 = NL + "\t\t\t\treturn eVirtualGet(";
  protected final String TEXT_1587 = ") != null;";
  protected final String TEXT_1588 = NL + "\t\t\t\treturn ";
  protected final String TEXT_1589 = "() != null;";
  protected final String TEXT_1590 = NL + "\t\t\t\treturn ((";
  protected final String TEXT_1591 = " & ";
  protected final String TEXT_1592 = "_EFLAG) != 0) != ";
  protected final String TEXT_1593 = ";";
  protected final String TEXT_1594 = NL + "\t\t\t\treturn (";
  protected final String TEXT_1595 = " & ";
  protected final String TEXT_1596 = "_EFLAG) != ";
  protected final String TEXT_1597 = "_EFLAG_DEFAULT;";
  protected final String TEXT_1598 = NL + "\t\t\t\treturn ";
  protected final String TEXT_1599 = " != ";
  protected final String TEXT_1600 = ";";
  protected final String TEXT_1601 = NL + "\t\t\t\treturn eVirtualGet(";
  protected final String TEXT_1602 = ", ";
  protected final String TEXT_1603 = ") != ";
  protected final String TEXT_1604 = ";";
  protected final String TEXT_1605 = NL + "\t\t\t\treturn ";
  protected final String TEXT_1606 = "() != ";
  protected final String TEXT_1607 = ";";
  protected final String TEXT_1608 = NL + "\t\t\t\treturn ";
  protected final String TEXT_1609 = " == null ? ";
  protected final String TEXT_1610 = " != null : !";
  protected final String TEXT_1611 = ".equals(";
  protected final String TEXT_1612 = ");";
  protected final String TEXT_1613 = NL + "\t\t\t\t";
  protected final String TEXT_1614 = " ";
  protected final String TEXT_1615 = " = (";
  protected final String TEXT_1616 = ")eVirtualGet(";
  protected final String TEXT_1617 = ", ";
  protected final String TEXT_1618 = ");" + NL + "\t\t\t\treturn ";
  protected final String TEXT_1619 = " == null ? ";
  protected final String TEXT_1620 = " != null : !";
  protected final String TEXT_1621 = ".equals(";
  protected final String TEXT_1622 = ");";
  protected final String TEXT_1623 = NL + "\t\t\t\treturn ";
  protected final String TEXT_1624 = " == null ? ";
  protected final String TEXT_1625 = "() != null : !";
  protected final String TEXT_1626 = ".equals(";
  protected final String TEXT_1627 = "());";
  protected final String TEXT_1628 = NL + "\t\t}";
  protected final String TEXT_1629 = NL + "\t\treturn super.eIsSet(featureID);";
  protected final String TEXT_1630 = NL + "\t\treturn eDynamicIsSet(featureID);";
  protected final String TEXT_1631 = NL + "\t}" + NL;
  protected final String TEXT_1632 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */";
  protected final String TEXT_1633 = NL + "\t@Override";
  protected final String TEXT_1634 = NL + "\tpublic int eBaseStructuralFeatureID(int derivedFeatureID, Class";
  protected final String TEXT_1635 = " baseClass)" + NL + "\t{";
  protected final String TEXT_1636 = NL + "\t\tif (baseClass == ";
  protected final String TEXT_1637 = ".class)" + NL + "\t\t{" + NL + "\t\t\tswitch (derivedFeatureID";
  protected final String TEXT_1638 = ")" + NL + "\t\t\t{";
  protected final String TEXT_1639 = NL + "\t\t\t\tcase ";
  protected final String TEXT_1640 = ": return ";
  protected final String TEXT_1641 = ";";
  protected final String TEXT_1642 = NL + "\t\t\t\tdefault: return -1;" + NL + "\t\t\t}" + NL + "\t\t}";
  protected final String TEXT_1643 = NL + "\t\treturn super.eBaseStructuralFeatureID(derivedFeatureID, baseClass);" + NL + "\t}";
  protected final String TEXT_1644 = NL + NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */";
  protected final String TEXT_1645 = NL + "\t@Override";
  protected final String TEXT_1646 = NL + "\tpublic int eDerivedStructuralFeatureID(int baseFeatureID, Class";
  protected final String TEXT_1647 = " baseClass)" + NL + "\t{";
  protected final String TEXT_1648 = NL + "\t\tif (baseClass == ";
  protected final String TEXT_1649 = ".class)" + NL + "\t\t{" + NL + "\t\t\tswitch (baseFeatureID)" + NL + "\t\t\t{";
  protected final String TEXT_1650 = NL + "\t\t\t\tcase ";
  protected final String TEXT_1651 = ": return ";
  protected final String TEXT_1652 = ";";
  protected final String TEXT_1653 = NL + "\t\t\t\tdefault: return -1;" + NL + "\t\t\t}" + NL + "\t\t}";
  protected final String TEXT_1654 = NL + "\t\tif (baseClass == ";
  protected final String TEXT_1655 = ".class)" + NL + "\t\t{" + NL + "\t\t\tswitch (baseFeatureID";
  protected final String TEXT_1656 = ")" + NL + "\t\t\t{";
  protected final String TEXT_1657 = NL + "\t\t\t\tcase ";
  protected final String TEXT_1658 = ": return ";
  protected final String TEXT_1659 = ";";
  protected final String TEXT_1660 = NL + "\t\t\t\tdefault: return -1;" + NL + "\t\t\t}" + NL + "\t\t}";
  protected final String TEXT_1661 = NL + "\t\treturn super.eDerivedStructuralFeatureID(baseFeatureID, baseClass);" + NL + "\t}" + NL;
  protected final String TEXT_1662 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */";
  protected final String TEXT_1663 = NL + "\t@Override";
  protected final String TEXT_1664 = NL + "\tpublic int eDerivedOperationID(int baseOperationID, Class";
  protected final String TEXT_1665 = " baseClass)" + NL + "\t{";
  protected final String TEXT_1666 = NL + "\t\tif (baseClass == ";
  protected final String TEXT_1667 = ".class)" + NL + "\t\t{" + NL + "\t\t\tswitch (baseOperationID)" + NL + "\t\t\t{";
  protected final String TEXT_1668 = NL + "\t\t\t\tcase ";
  protected final String TEXT_1669 = ": return ";
  protected final String TEXT_1670 = ";";
  protected final String TEXT_1671 = NL + "\t\t\t\tdefault: return super.eDerivedOperationID(baseOperationID, baseClass);" + NL + "\t\t\t}" + NL + "\t\t}";
  protected final String TEXT_1672 = NL + "\t\tif (baseClass == ";
  protected final String TEXT_1673 = ".class)" + NL + "\t\t{" + NL + "\t\t\tswitch (baseOperationID)" + NL + "\t\t\t{";
  protected final String TEXT_1674 = NL + "\t\t\t\tcase ";
  protected final String TEXT_1675 = ": return ";
  protected final String TEXT_1676 = ";";
  protected final String TEXT_1677 = NL + "\t\t\t\tdefault: return -1;" + NL + "\t\t\t}" + NL + "\t\t}";
  protected final String TEXT_1678 = NL + "\t\tif (baseClass == ";
  protected final String TEXT_1679 = ".class)" + NL + "\t\t{" + NL + "\t\t\tswitch (baseOperationID";
  protected final String TEXT_1680 = ")" + NL + "\t\t\t{";
  protected final String TEXT_1681 = NL + "\t\t\t\tcase ";
  protected final String TEXT_1682 = ": return ";
  protected final String TEXT_1683 = ";";
  protected final String TEXT_1684 = NL + "\t\t\t\tdefault: return -1;" + NL + "\t\t\t}" + NL + "\t\t}";
  protected final String TEXT_1685 = NL + "\t\treturn super.eDerivedOperationID(baseOperationID, baseClass);" + NL + "\t}" + NL;
  protected final String TEXT_1686 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */";
  protected final String TEXT_1687 = NL + "\t@Override";
  protected final String TEXT_1688 = NL + "\tprotected Object[] eVirtualValues()" + NL + "\t{" + NL + "\t\treturn ";
  protected final String TEXT_1689 = ";" + NL + "\t}" + NL + "" + NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */";
  protected final String TEXT_1690 = NL + "\t@Override";
  protected final String TEXT_1691 = NL + "\tprotected void eSetVirtualValues(Object[] newValues)" + NL + "\t{" + NL + "\t\t";
  protected final String TEXT_1692 = " = newValues;" + NL + "\t}" + NL;
  protected final String TEXT_1693 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */";
  protected final String TEXT_1694 = NL + "\t@Override";
  protected final String TEXT_1695 = NL + "\tprotected int eVirtualIndexBits(int offset)" + NL + "\t{" + NL + "\t\tswitch (offset)" + NL + "\t\t{";
  protected final String TEXT_1696 = NL + "\t\t\tcase ";
  protected final String TEXT_1697 = " :" + NL + "\t\t\t\treturn ";
  protected final String TEXT_1698 = ";";
  protected final String TEXT_1699 = NL + "\t\t\tdefault :" + NL + "\t\t\t\tthrow new IndexOutOfBoundsException();" + NL + "\t\t}" + NL + "\t}" + NL + "" + NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */";
  protected final String TEXT_1700 = NL + "\t@Override";
  protected final String TEXT_1701 = NL + "\tprotected void eSetVirtualIndexBits(int offset, int newIndexBits)" + NL + "\t{" + NL + "\t\tswitch (offset)" + NL + "\t\t{";
  protected final String TEXT_1702 = NL + "\t\t\tcase ";
  protected final String TEXT_1703 = " :" + NL + "\t\t\t\t";
  protected final String TEXT_1704 = " = newIndexBits;" + NL + "\t\t\t\tbreak;";
  protected final String TEXT_1705 = NL + "\t\t\tdefault :" + NL + "\t\t\t\tthrow new IndexOutOfBoundsException();" + NL + "\t\t}" + NL + "\t}" + NL;
  protected final String TEXT_1706 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */";
  protected final String TEXT_1707 = NL + "\t@Override";
  protected final String TEXT_1708 = NL + "\t@SuppressWarnings(";
  protected final String TEXT_1709 = "\"unchecked\"";
  protected final String TEXT_1710 = "{\"rawtypes\", \"unchecked\" }";
  protected final String TEXT_1711 = ")";
  protected final String TEXT_1712 = NL + "\tpublic Object eInvoke(int operationID, ";
  protected final String TEXT_1713 = " arguments) throws ";
  protected final String TEXT_1714 = NL + "\t{" + NL + "\t\tswitch (operationID";
  protected final String TEXT_1715 = ")" + NL + "\t\t{";
  protected final String TEXT_1716 = NL + "\t\t\tcase ";
  protected final String TEXT_1717 = ":";
  protected final String TEXT_1718 = NL + "\t\t\t\ttry" + NL + "\t\t\t\t{";
  protected final String TEXT_1719 = NL + "\t\t\t\t";
  protected final String TEXT_1720 = "(";
  protected final String TEXT_1721 = "(";
  protected final String TEXT_1722 = "(";
  protected final String TEXT_1723 = ")";
  protected final String TEXT_1724 = "arguments.get(";
  protected final String TEXT_1725 = ")";
  protected final String TEXT_1726 = ").";
  protected final String TEXT_1727 = "()";
  protected final String TEXT_1728 = ", ";
  protected final String TEXT_1729 = ");" + NL + "\t\t\t\t";
  protected final String TEXT_1730 = "return null;";
  protected final String TEXT_1731 = NL + "\t\t\t\t";
  protected final String TEXT_1732 = "return ";
  protected final String TEXT_1733 = "new ";
  protected final String TEXT_1734 = "(";
  protected final String TEXT_1735 = "(";
  protected final String TEXT_1736 = "(";
  protected final String TEXT_1737 = "(";
  protected final String TEXT_1738 = ")";
  protected final String TEXT_1739 = "arguments.get(";
  protected final String TEXT_1740 = ")";
  protected final String TEXT_1741 = ").";
  protected final String TEXT_1742 = "()";
  protected final String TEXT_1743 = ", ";
  protected final String TEXT_1744 = ")";
  protected final String TEXT_1745 = ")";
  protected final String TEXT_1746 = ";";
  protected final String TEXT_1747 = NL + "\t\t\t\t}" + NL + "\t\t\t\tcatch (";
  protected final String TEXT_1748 = " throwable)" + NL + "\t\t\t\t{" + NL + "\t\t\t\t\tthrow new ";
  protected final String TEXT_1749 = "(throwable);" + NL + "\t\t\t\t}";
  protected final String TEXT_1750 = NL + "\t\t}";
  protected final String TEXT_1751 = NL + "\t\treturn super.eInvoke(operationID, arguments);";
  protected final String TEXT_1752 = NL + "\t\treturn eDynamicInvoke(operationID, arguments);";
  protected final String TEXT_1753 = NL + "\t}" + NL;
  protected final String TEXT_1754 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */";
  protected final String TEXT_1755 = NL + "\t@Override";
  protected final String TEXT_1756 = NL + "\tpublic String toString()" + NL + "\t{" + NL + "\t\tif (eIsProxy()) return super.toString();" + NL + "" + NL + "\t\tStringBuffer result = new StringBuffer(super.toString());";
  protected final String TEXT_1757 = NL + "\t\tresult.append(\" (";
  protected final String TEXT_1758 = ": \");";
  protected final String TEXT_1759 = NL + "\t\tresult.append(\", ";
  protected final String TEXT_1760 = ": \");";
  protected final String TEXT_1761 = NL + "\t\tif (eVirtualIsSet(";
  protected final String TEXT_1762 = ")) result.append(eVirtualGet(";
  protected final String TEXT_1763 = ")); else result.append(\"<unset>\");";
  protected final String TEXT_1764 = NL + "\t\tif (";
  protected final String TEXT_1765 = "(";
  protected final String TEXT_1766 = " & ";
  protected final String TEXT_1767 = "_ESETFLAG) != 0";
  protected final String TEXT_1768 = "ESet";
  protected final String TEXT_1769 = ") result.append((";
  protected final String TEXT_1770 = " & ";
  protected final String TEXT_1771 = "_EFLAG) != 0); else result.append(\"<unset>\");";
  protected final String TEXT_1772 = NL + "\t\tif (";
  protected final String TEXT_1773 = "(";
  protected final String TEXT_1774 = " & ";
  protected final String TEXT_1775 = "_ESETFLAG) != 0";
  protected final String TEXT_1776 = "ESet";
  protected final String TEXT_1777 = ") result.append(";
  protected final String TEXT_1778 = "_EFLAG_VALUES[(";
  protected final String TEXT_1779 = " & ";
  protected final String TEXT_1780 = "_EFLAG) >>> ";
  protected final String TEXT_1781 = "_EFLAG_OFFSET]); else result.append(\"<unset>\");";
  protected final String TEXT_1782 = NL + "\t\tif (";
  protected final String TEXT_1783 = "(";
  protected final String TEXT_1784 = " & ";
  protected final String TEXT_1785 = "_ESETFLAG) != 0";
  protected final String TEXT_1786 = "ESet";
  protected final String TEXT_1787 = ") result.append(";
  protected final String TEXT_1788 = "); else result.append(\"<unset>\");";
  protected final String TEXT_1789 = NL + "\t\tresult.append(eVirtualGet(";
  protected final String TEXT_1790 = ", ";
  protected final String TEXT_1791 = "));";
  protected final String TEXT_1792 = NL + "\t\tresult.append((";
  protected final String TEXT_1793 = " & ";
  protected final String TEXT_1794 = "_EFLAG) != 0);";
  protected final String TEXT_1795 = NL + "\t\tresult.append(";
  protected final String TEXT_1796 = "_EFLAG_VALUES[(";
  protected final String TEXT_1797 = " & ";
  protected final String TEXT_1798 = "_EFLAG) >>> ";
  protected final String TEXT_1799 = "_EFLAG_OFFSET]);";
  protected final String TEXT_1800 = NL + "\t\tresult.append(";
  protected final String TEXT_1801 = ");";
  protected final String TEXT_1802 = NL + "\t\tresult.append(')');" + NL + "\t\treturn result.toString();" + NL + "\t}" + NL;
  protected final String TEXT_1803 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */";
  protected final String TEXT_1804 = NL + "\t@";
  protected final String TEXT_1805 = NL + "\tprotected int hash = -1;" + NL + "" + NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */" + NL + "\tpublic int getHash()" + NL + "\t{" + NL + "\t\tif (hash == -1)" + NL + "\t\t{" + NL + "\t\t\t";
  protected final String TEXT_1806 = " theKey = getKey();" + NL + "\t\t\thash = (theKey == null ? 0 : theKey.hashCode());" + NL + "\t\t}" + NL + "\t\treturn hash;" + NL + "\t}" + NL + "" + NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */" + NL + "\tpublic void setHash(int hash)" + NL + "\t{" + NL + "\t\tthis.hash = hash;" + NL + "\t}" + NL + "" + NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */" + NL + "\tpublic ";
  protected final String TEXT_1807 = " getKey()" + NL + "\t{";
  protected final String TEXT_1808 = NL + "\t\treturn new ";
  protected final String TEXT_1809 = "(getTypedKey());";
  protected final String TEXT_1810 = NL + "\t\treturn getTypedKey();";
  protected final String TEXT_1811 = NL + "\t}" + NL + "" + NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */" + NL + "\tpublic void setKey(";
  protected final String TEXT_1812 = " key)" + NL + "\t{";
  protected final String TEXT_1813 = NL + "\t\tgetTypedKey().addAll(";
  protected final String TEXT_1814 = "(";
  protected final String TEXT_1815 = ")";
  protected final String TEXT_1816 = "key);";
  protected final String TEXT_1817 = NL + "\t\tsetTypedKey(key);";
  protected final String TEXT_1818 = NL + "\t\tsetTypedKey(((";
  protected final String TEXT_1819 = ")key).";
  protected final String TEXT_1820 = "());";
  protected final String TEXT_1821 = NL + "\t\tsetTypedKey((";
  protected final String TEXT_1822 = ")key);";
  protected final String TEXT_1823 = NL + "\t}" + NL + "" + NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */" + NL + "\tpublic ";
  protected final String TEXT_1824 = " getValue()" + NL + "\t{";
  protected final String TEXT_1825 = NL + "\t\treturn new ";
  protected final String TEXT_1826 = "(getTypedValue());";
  protected final String TEXT_1827 = NL + "\t\treturn getTypedValue();";
  protected final String TEXT_1828 = NL + "\t}" + NL + "" + NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */" + NL + "\tpublic ";
  protected final String TEXT_1829 = " setValue(";
  protected final String TEXT_1830 = " value)" + NL + "\t{" + NL + "\t\t";
  protected final String TEXT_1831 = " oldValue = getValue();";
  protected final String TEXT_1832 = NL + "\t\tgetTypedValue().clear();" + NL + "\t\tgetTypedValue().addAll(";
  protected final String TEXT_1833 = "(";
  protected final String TEXT_1834 = ")";
  protected final String TEXT_1835 = "value);";
  protected final String TEXT_1836 = NL + "\t\tsetTypedValue(value);";
  protected final String TEXT_1837 = NL + "\t\tsetTypedValue(((";
  protected final String TEXT_1838 = ")value).";
  protected final String TEXT_1839 = "());";
  protected final String TEXT_1840 = NL + "\t\tsetTypedValue((";
  protected final String TEXT_1841 = ")value);";
  protected final String TEXT_1842 = NL + "\t\treturn oldValue;" + NL + "\t}" + NL + "" + NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */";
  protected final String TEXT_1843 = NL + "\t@SuppressWarnings(\"unchecked\")";
  protected final String TEXT_1844 = NL + "\tpublic ";
  protected final String TEXT_1845 = " getEMap()" + NL + "\t{" + NL + "\t\t";
  protected final String TEXT_1846 = " container = eContainer();" + NL + "\t\treturn container == null ? null : (";
  protected final String TEXT_1847 = ")container.eGet(eContainmentFeature());" + NL + "\t}" + NL;
  protected final String TEXT_1848 = NL + "} //";
  protected final String TEXT_1849 = NL;

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
    for (GenFeature genFeature : genClass.getReifiedGenFeatures()) {
    if (genModel.isArrayAccessors() && genFeature.isListType() && !genFeature.isFeatureMapType() && !genFeature.isMapType()) { String rawListItemType = genFeature.getRawListItemType(genClass); int index = rawListItemType.indexOf('['); String head = rawListItemType; String tail = ""; if (index != -1) { head = rawListItemType.substring(0, index); tail = rawListItemType.substring(index); } 
    stringBuffer.append(TEXT_66);
    stringBuffer.append(genFeature.getGetArrayAccessor());
    stringBuffer.append(TEXT_67);
    stringBuffer.append(genFeature.getFormattedName());
    stringBuffer.append(TEXT_68);
    stringBuffer.append(genFeature.getGetArrayAccessor());
    stringBuffer.append(TEXT_69);
    if (genFeature.getQualifiedListItemType(genClass).contains("<") || genFeature.getArrayItemType(genClass).contains("<")) {
    stringBuffer.append(TEXT_70);
    }
    stringBuffer.append(TEXT_71);
    stringBuffer.append(rawListItemType);
    stringBuffer.append(TEXT_72);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_73);
    stringBuffer.append(head);
    stringBuffer.append(TEXT_74);
    stringBuffer.append(tail);
    stringBuffer.append(TEXT_75);
    }
    }
    for (GenFeature genFeature : genClass.getDeclaredFieldGenFeatures()) {
    if (genFeature.hasSettingDelegate()) {
    stringBuffer.append(TEXT_76);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_77);
    stringBuffer.append(genFeature.getFormattedName());
    stringBuffer.append(TEXT_78);
    stringBuffer.append(genFeature.getFeatureKind());
    stringBuffer.append(TEXT_79);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_80);
    if (isGWT) {
    stringBuffer.append(TEXT_81);
    stringBuffer.append(genModel.getImportedName("com.google.gwt.user.client.rpc.GwtTransient"));
    }
    stringBuffer.append(TEXT_82);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.EStructuralFeature"));
    stringBuffer.append(TEXT_83);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_84);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.EStructuralFeature"));
    stringBuffer.append(TEXT_85);
    stringBuffer.append(genFeature.getQualifiedFeatureAccessor());
    stringBuffer.append(TEXT_86);
    } else if (genFeature.isListType() || genFeature.isReferenceType()) {
    if (genClass.isField(genFeature)) {
    stringBuffer.append(TEXT_87);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_88);
    stringBuffer.append(genFeature.getFormattedName());
    stringBuffer.append(TEXT_89);
    stringBuffer.append(genFeature.getFeatureKind());
    stringBuffer.append(TEXT_90);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_91);
    if (isGWT) {
    stringBuffer.append(TEXT_92);
    stringBuffer.append(genModel.getImportedName("com.google.gwt.user.client.rpc.GwtTransient"));
    }
    stringBuffer.append(TEXT_93);
    stringBuffer.append(genFeature.getImportedInternalType(genClass));
    stringBuffer.append(TEXT_94);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_95);
    }
    if (genModel.isArrayAccessors() && genFeature.isListType() && !genFeature.isFeatureMapType() && !genFeature.isMapType()) { String rawListItemType = genFeature.getRawListItemType(genClass); int index = rawListItemType.indexOf('['); String head = rawListItemType; String tail = ""; if (index != -1) { head = rawListItemType.substring(0, index); tail = rawListItemType.substring(index); } 
    stringBuffer.append(TEXT_96);
    stringBuffer.append(genFeature.getGetArrayAccessor());
    stringBuffer.append(TEXT_97);
    stringBuffer.append(genFeature.getFormattedName());
    stringBuffer.append(TEXT_98);
    stringBuffer.append(genFeature.getGetArrayAccessor());
    stringBuffer.append(TEXT_99);
    if (genFeature.getQualifiedListItemType(genClass).contains("<") || genFeature.getArrayItemType(genClass).contains("<")) {
    stringBuffer.append(TEXT_100);
    }
    stringBuffer.append(TEXT_101);
    stringBuffer.append(rawListItemType);
    stringBuffer.append(TEXT_102);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_103);
    stringBuffer.append(head);
    stringBuffer.append(TEXT_104);
    stringBuffer.append(tail);
    stringBuffer.append(TEXT_105);
    }
    } else {
    if (genFeature.hasEDefault() && (!genFeature.isVolatile() || !genModel.isReflectiveDelegation() && (!genFeature.hasDelegateFeature() || !genFeature.isUnsettable()))) { String staticDefaultValue = genFeature.getStaticDefaultValue();
    stringBuffer.append(TEXT_106);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_107);
    stringBuffer.append(genFeature.getFormattedName());
    stringBuffer.append(TEXT_108);
    stringBuffer.append(genFeature.getFeatureKind());
    stringBuffer.append(TEXT_109);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_110);
    if (genModel.useGenerics() && genFeature.isListDataType() && genFeature.isSetDefaultValue()) {
    stringBuffer.append(TEXT_111);
    }
    stringBuffer.append(TEXT_112);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_113);
    stringBuffer.append(genFeature.getEDefault());
    if ("".equals(staticDefaultValue)) {
    stringBuffer.append(TEXT_114);
    stringBuffer.append(genFeature.getEcoreFeature().getDefaultValueLiteral());
    stringBuffer.append(TEXT_115);
    } else {
    stringBuffer.append(TEXT_116);
    stringBuffer.append(staticDefaultValue);
    stringBuffer.append(TEXT_117);
    stringBuffer.append(genModel.getNonNLS(staticDefaultValue));
    }
    stringBuffer.append(TEXT_118);
    }
    if (genClass.isField(genFeature)) {
    if (genClass.isFlag(genFeature)) { int flagIndex = genClass.getFlagIndex(genFeature);
    if (flagIndex > 31 && flagIndex % 32 == 0) {
    stringBuffer.append(TEXT_119);
    if (isGWT) {
    stringBuffer.append(TEXT_120);
    stringBuffer.append(genModel.getImportedName("com.google.gwt.user.client.rpc.GwtTransient"));
    }
    stringBuffer.append(TEXT_121);
    stringBuffer.append(genClass.getFlagsField(genFeature));
    stringBuffer.append(TEXT_122);
    }
    if (genFeature.isEnumType()) {
    stringBuffer.append(TEXT_123);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_124);
    stringBuffer.append(genFeature.getFormattedName());
    stringBuffer.append(TEXT_125);
    stringBuffer.append(genFeature.getFeatureKind());
    stringBuffer.append(TEXT_126);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_127);
    stringBuffer.append(flagIndex % 32);
    stringBuffer.append(TEXT_128);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_129);
    stringBuffer.append(genFeature.getFormattedName());
    stringBuffer.append(TEXT_130);
    stringBuffer.append(genFeature.getFeatureKind());
    stringBuffer.append(TEXT_131);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_132);
    if (isJDK50) {
    stringBuffer.append(genFeature.getEDefault());
    stringBuffer.append(TEXT_133);
    } else {
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_134);
    stringBuffer.append(genFeature.getEDefault());
    stringBuffer.append(TEXT_135);
    }
    stringBuffer.append(TEXT_136);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_137);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_138);
    stringBuffer.append(genFeature.getTypeGenClassifier().getFormattedName());
    stringBuffer.append(TEXT_139);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_140);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_141);
    if (isJDK50) {
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_142);
    } else {
    stringBuffer.append(TEXT_143);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_144);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_145);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_146);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_147);
    }
    stringBuffer.append(TEXT_148);
    }
    stringBuffer.append(TEXT_149);
    stringBuffer.append(genClass.getFlagSize(genFeature) > 1 ? "s" : "");
    stringBuffer.append(TEXT_150);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_151);
    stringBuffer.append(genFeature.getFormattedName());
    stringBuffer.append(TEXT_152);
    stringBuffer.append(genFeature.getFeatureKind());
    stringBuffer.append(TEXT_153);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_154);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_155);
    stringBuffer.append(genClass.getFlagMask(genFeature));
    stringBuffer.append(TEXT_156);
    if (genFeature.isEnumType()) {
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_157);
    } else {
    stringBuffer.append(flagIndex % 32);
    }
    stringBuffer.append(TEXT_158);
    } else {
    stringBuffer.append(TEXT_159);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_160);
    stringBuffer.append(genFeature.getFormattedName());
    stringBuffer.append(TEXT_161);
    stringBuffer.append(genFeature.getFeatureKind());
    stringBuffer.append(TEXT_162);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_163);
    if (isGWT) {
    stringBuffer.append(TEXT_164);
    stringBuffer.append(genModel.getImportedName("com.google.gwt.user.client.rpc.GwtTransient"));
    }
    stringBuffer.append(TEXT_165);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_166);
    stringBuffer.append(genFeature.getSafeName());
    if (genFeature.hasEDefault()) {
    stringBuffer.append(TEXT_167);
    stringBuffer.append(genFeature.getEDefault());
    }
    stringBuffer.append(TEXT_168);
    }
    }
    }
    if (genClass.isESetField(genFeature)) {
    if (genClass.isESetFlag(genFeature)) { int flagIndex = genClass.getESetFlagIndex(genFeature);
    if (flagIndex > 31 && flagIndex % 32 == 0) {
    stringBuffer.append(TEXT_169);
    if (isGWT) {
    stringBuffer.append(TEXT_170);
    stringBuffer.append(genModel.getImportedName("com.google.gwt.user.client.rpc.GwtTransient"));
    }
    stringBuffer.append(TEXT_171);
    stringBuffer.append(genClass.getESetFlagsField(genFeature));
    stringBuffer.append(TEXT_172);
    }
    stringBuffer.append(TEXT_173);
    stringBuffer.append(genFeature.getFormattedName());
    stringBuffer.append(TEXT_174);
    stringBuffer.append(genFeature.getFeatureKind());
    stringBuffer.append(TEXT_175);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_176);
    stringBuffer.append(flagIndex % 32 );
    stringBuffer.append(TEXT_177);
    } else {
    stringBuffer.append(TEXT_178);
    stringBuffer.append(genFeature.getFormattedName());
    stringBuffer.append(TEXT_179);
    stringBuffer.append(genFeature.getFeatureKind());
    stringBuffer.append(TEXT_180);
    if (isGWT) {
    stringBuffer.append(TEXT_181);
    stringBuffer.append(genModel.getImportedName("com.google.gwt.user.client.rpc.GwtTransient"));
    }
    stringBuffer.append(TEXT_182);
    stringBuffer.append(genFeature.getUncapName());
    stringBuffer.append(TEXT_183);
    }
    }
    //Class/declaredFieldGenFeature.override.javajetinc
    }
    }
    if (isImplementation && genClass.hasOffsetCorrection() && !genClass.getImplementedGenFeatures().isEmpty()) {
    stringBuffer.append(TEXT_184);
    stringBuffer.append(genClass.getOffsetCorrectionField(null));
    stringBuffer.append(TEXT_185);
    stringBuffer.append(genClass.getQualifiedClassifierAccessor());
    stringBuffer.append(TEXT_186);
    stringBuffer.append(genClass.getImplementedGenFeatures().get(0).getQualifiedFeatureAccessor());
    stringBuffer.append(TEXT_187);
    stringBuffer.append(genClass.getQualifiedFeatureID(genClass.getImplementedGenFeatures().get(0)));
    stringBuffer.append(TEXT_188);
    }
    if (isImplementation && !genModel.isReflectiveDelegation()) {
    for (GenFeature genFeature : genClass.getImplementedGenFeatures()) { GenFeature reverseFeature = genFeature.getReverse();
    if (reverseFeature != null && reverseFeature.getGenClass().hasOffsetCorrection()) {
    stringBuffer.append(TEXT_189);
    stringBuffer.append(genClass.getOffsetCorrectionField(genFeature));
    stringBuffer.append(TEXT_190);
    stringBuffer.append(reverseFeature.getGenClass().getQualifiedClassifierAccessor());
    stringBuffer.append(TEXT_191);
    stringBuffer.append(reverseFeature.getQualifiedFeatureAccessor());
    stringBuffer.append(TEXT_192);
    stringBuffer.append(reverseFeature.getGenClass().getQualifiedFeatureID(reverseFeature));
    stringBuffer.append(TEXT_193);
    }
    }
    }
    if (genModel.isOperationReflection() && isImplementation && genClass.hasOffsetCorrection() && !genClass.getImplementedGenOperations().isEmpty()) {
    stringBuffer.append(TEXT_194);
    stringBuffer.append(genClass.getQualifiedClassifierAccessor());
    stringBuffer.append(TEXT_195);
    stringBuffer.append(genClass.getImplementedGenOperations().get(0).getQualifiedOperationAccessor());
    stringBuffer.append(TEXT_196);
    stringBuffer.append(genClass.getQualifiedOperationID(genClass.getImplementedGenOperations().get(0)));
    stringBuffer.append(TEXT_197);
    }
    if (isImplementation) {
    stringBuffer.append(TEXT_198);
    if (genModel.isPublicConstructors()) {
    stringBuffer.append(TEXT_199);
    } else {
    stringBuffer.append(TEXT_200);
    }
    stringBuffer.append(TEXT_201);
    stringBuffer.append(genClass.getClassName());
    stringBuffer.append(TEXT_202);
    for (GenFeature genFeature : genClass.getFlagGenFeaturesWithDefault()) {
    stringBuffer.append(TEXT_203);
    stringBuffer.append(genClass.getFlagsField(genFeature));
    stringBuffer.append(TEXT_204);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_205);
    if (!genFeature.isBooleanType()) {
    stringBuffer.append(TEXT_206);
    }
    stringBuffer.append(TEXT_207);
    }
    stringBuffer.append(TEXT_208);
    if (genModel.useClassOverrideAnnotation()) {
    stringBuffer.append(TEXT_209);
    }
    stringBuffer.append(TEXT_210);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.EClass"));
    stringBuffer.append(TEXT_211);
    stringBuffer.append(genClass.getQualifiedClassifierAccessor());
    stringBuffer.append(TEXT_212);
    }
    if (isImplementation && (genModel.getFeatureDelegation() == GenDelegationKind.REFLECTIVE_LITERAL || genModel.isDynamicDelegation()) && (genClass.getClassExtendsGenClass() == null || (genClass.getClassExtendsGenClass().getGenModel().getFeatureDelegation() != GenDelegationKind.REFLECTIVE_LITERAL && !genClass.getClassExtendsGenClass().getGenModel().isDynamicDelegation()))) {
    if (genClass.hasStaticFeatures()) {
    stringBuffer.append(TEXT_213);
    stringBuffer.append(genClass.getClassExtendsGenClass() == null ? 0 : genClass.getClassExtendsGenClass().getAllGenFeatures().size());
    stringBuffer.append(TEXT_214);
    }
    stringBuffer.append(TEXT_215);
    if (genModel.useClassOverrideAnnotation()) {
    stringBuffer.append(TEXT_216);
    }
    stringBuffer.append(TEXT_217);
    stringBuffer.append(genClass.getClassExtendsGenClass() == null ? "0" : genClass.hasStaticFeatures() ? "ESTATIC_FEATURE_COUNT" : Integer.toString(genClass.getClassExtendsGenClass().getAllGenFeatures().size()));
    stringBuffer.append(TEXT_218);
    }
    //Class/reflectiveDelegation.override.javajetinc
    if (isImplementation) {
    new Runnable() { public void run() { GenClass classExtendsGenClass = genClass.getClassExtendsGenClass(); List<GenFeature> classExtendsAllGenFeatures = classExtendsGenClass == null? Collections.<GenFeature>emptyList() : classExtendsGenClass.getAllGenFeatures();
    for (GenFeature genFeature : genClass.getReifiedGenFeatures()) {
    if (genModel.isArrayAccessors() && genFeature.isListType() && !genFeature.isFeatureMapType() && !genFeature.isMapType()) { String arrayElementType = genFeature.getArrayItemType(genClass);
    stringBuffer.append(TEXT_219);
    if (genModel.useGenerics() && CodeGenUtil.isUncheckedCast(arrayElementType)) {
    stringBuffer.append(TEXT_220);
    }
    if (classExtendsAllGenFeatures.contains(genFeature)) {
    stringBuffer.append(TEXT_221);
    }
    stringBuffer.append(TEXT_222);
    stringBuffer.append(arrayElementType);
    stringBuffer.append(TEXT_223);
    stringBuffer.append(genFeature.getGetArrayAccessor());
    stringBuffer.append(TEXT_224);
    if (genFeature.isVolatile()) {
    stringBuffer.append(TEXT_225);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.util.BasicEList"));
    stringBuffer.append(genFeature.getListTemplateArguments(genClass));
    stringBuffer.append(TEXT_226);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.util.BasicEList"));
    stringBuffer.append(genFeature.getListTemplateArguments(genClass));
    stringBuffer.append(TEXT_227);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_228);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_229);
    } else {
    stringBuffer.append(TEXT_230);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_231);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_232);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_233);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.util.BasicEList"));
    stringBuffer.append(genFeature.getListTemplateArguments(genClass));
    stringBuffer.append(TEXT_234);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.util.BasicEList"));
    stringBuffer.append(genFeature.getListTemplateArguments(genClass));
    stringBuffer.append(TEXT_235);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_236);
    }
    stringBuffer.append(TEXT_237);
    stringBuffer.append(arrayElementType);
    stringBuffer.append(TEXT_238);
    }
    if (genFeature.isGet() && genFeature.isListType()) {
    stringBuffer.append(TEXT_239);
    if (isJDK50) { //Class/getGenFeature.annotations.insert.javajetinc
    }
    if (genFeature.isListType() && genModel.isVirtualDelegation()) {
    stringBuffer.append(TEXT_240);
    }
    if (classExtendsAllGenFeatures.contains(genFeature)) {
    stringBuffer.append(TEXT_241);
    }
    stringBuffer.append(TEXT_242);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_243);
    stringBuffer.append(genFeature.getGetAccessor());
    if (genClass.hasCollidingGetAccessorOperation(genFeature)) {
    stringBuffer.append(TEXT_244);
    }
    stringBuffer.append(TEXT_245);
    if (genModel.isVirtualDelegation()) {
    stringBuffer.append(TEXT_246);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_247);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_248);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_249);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(positiveOffsetCorrection);
    stringBuffer.append(TEXT_250);
    }
    stringBuffer.append(TEXT_251);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_252);
    if (genModel.isVirtualDelegation()) {
    stringBuffer.append(TEXT_253);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(positiveOffsetCorrection);
    stringBuffer.append(TEXT_254);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_255);
    stringBuffer.append(genClass.getListConstructor(genFeature));
    stringBuffer.append(TEXT_256);
    } else {
    stringBuffer.append(TEXT_257);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_258);
    stringBuffer.append(genClass.getListConstructor(genFeature));
    stringBuffer.append(TEXT_259);
    }
    stringBuffer.append(TEXT_260);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(genFeature.isMapType() && genFeature.isEffectiveSuppressEMFTypes() ? ".map()" : "");
    stringBuffer.append(TEXT_261);
    }
    if (!genModel.isReflectiveDelegation() && genFeature.isBasicSet()) {
    stringBuffer.append(TEXT_262);
    if (isJDK50) { //Class/basicSetGenFeature.annotations.insert.javajetinc
    }
    if (classExtendsAllGenFeatures.contains(genFeature)) {
    stringBuffer.append(TEXT_263);
    }
    stringBuffer.append(TEXT_264);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.notify.NotificationChain"));
    stringBuffer.append(TEXT_265);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_266);
    stringBuffer.append(genFeature.getImportedInternalType(genClass));
    stringBuffer.append(TEXT_267);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_268);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.notify.NotificationChain"));
    stringBuffer.append(TEXT_269);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_270);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_271);
    }
    if (genFeature.isSet() && !(!genModel.isReflectiveDelegation() && genFeature.isBasicSet())) {
    stringBuffer.append(TEXT_272);
    if (isJDK50) { //Class/setGenFeature.annotations.insert.javajetinc
    }
    { GenOperation setAccessorOperation = genClass.getSetAccessorOperation(genFeature);
    if (classExtendsAllGenFeatures.contains(genFeature)) {
    stringBuffer.append(TEXT_273);
    }
    stringBuffer.append(TEXT_274);
    stringBuffer.append(genFeature.getAccessorName());
    if (genClass.hasCollidingSetAccessorOperation(genFeature)) {
    stringBuffer.append(TEXT_275);
    }
    stringBuffer.append(TEXT_276);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_277);
    stringBuffer.append(setAccessorOperation == null ? "new" + genFeature.getCapName() : setAccessorOperation.getGenParameters().get(0).getName());
    stringBuffer.append(TEXT_278);
    stringBuffer.append(genFeature.getAccessorName());
    if (genClass.hasCollidingSetAccessorOperation(genFeature)) {
    stringBuffer.append(TEXT_279);
    }
    stringBuffer.append(TEXT_280);
    stringBuffer.append(setAccessorOperation == null ? "new" + genFeature.getCapName() : setAccessorOperation.getGenParameters().get(0).getName());
    stringBuffer.append(TEXT_281);
    }
    }
    //Class/genFeatureReified.override.javajetinc
    }}}.run();}
    new Runnable() { public void run() {
    for (GenFeature genFeature : (isImplementation ? genClass.getImplementedGenFeatures() : genClass.getDeclaredGenFeatures())) {
    if (genModel.isArrayAccessors() && genFeature.isListType() && !genFeature.isFeatureMapType() && !genFeature.isMapType()) { String arrayElementType = genFeature.getArrayItemType(genClass);
    stringBuffer.append(TEXT_282);
    if (!isImplementation) {
    stringBuffer.append(TEXT_283);
    stringBuffer.append(arrayElementType);
    stringBuffer.append(TEXT_284);
    stringBuffer.append(genFeature.getGetArrayAccessor());
    stringBuffer.append(TEXT_285);
    } else {
    if (genModel.useGenerics() && CodeGenUtil.isUncheckedCast(arrayElementType)) {
    stringBuffer.append(TEXT_286);
    }
    stringBuffer.append(TEXT_287);
    stringBuffer.append(arrayElementType);
    stringBuffer.append(TEXT_288);
    stringBuffer.append(genFeature.getGetArrayAccessor());
    stringBuffer.append(TEXT_289);
    if (genFeature.isVolatile()) {
    stringBuffer.append(TEXT_290);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.util.BasicEList"));
    stringBuffer.append(genFeature.getListTemplateArguments(genClass));
    stringBuffer.append(TEXT_291);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.util.BasicEList"));
    stringBuffer.append(genFeature.getListTemplateArguments(genClass));
    stringBuffer.append(TEXT_292);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_293);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_294);
    } else {
    stringBuffer.append(TEXT_295);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_296);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_297);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_298);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.util.BasicEList"));
    stringBuffer.append(genFeature.getListTemplateArguments(genClass));
    stringBuffer.append(TEXT_299);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.util.BasicEList"));
    stringBuffer.append(genFeature.getListTemplateArguments(genClass));
    stringBuffer.append(TEXT_300);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_301);
    }
    stringBuffer.append(TEXT_302);
    stringBuffer.append(arrayElementType);
    stringBuffer.append(TEXT_303);
    }
    stringBuffer.append(TEXT_304);
    if (!isImplementation) {
    stringBuffer.append(TEXT_305);
    stringBuffer.append(genFeature.getListItemType(genClass));
    stringBuffer.append(TEXT_306);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_307);
    } else {
    stringBuffer.append(TEXT_308);
    stringBuffer.append(genFeature.getListItemType(genClass));
    stringBuffer.append(TEXT_309);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_310);
    if (!genModel.useGenerics()) {
    stringBuffer.append(TEXT_311);
    stringBuffer.append(genFeature.getListItemType(genClass));
    stringBuffer.append(TEXT_312);
    }
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_313);
    }
    stringBuffer.append(TEXT_314);
    if (!isImplementation) {
    stringBuffer.append(TEXT_315);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_316);
    } else {
    stringBuffer.append(TEXT_317);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_318);
    if (genFeature.isVolatile()) {
    stringBuffer.append(TEXT_319);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_320);
    } else {
    stringBuffer.append(TEXT_321);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_322);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_323);
    }
    stringBuffer.append(TEXT_324);
    }
    stringBuffer.append(TEXT_325);
    if (!isImplementation) {
    stringBuffer.append(TEXT_326);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_327);
    stringBuffer.append(genFeature.getListItemType(genClass));
    stringBuffer.append(TEXT_328);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_329);
    } else {
    stringBuffer.append(TEXT_330);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_331);
    stringBuffer.append(genFeature.getListItemType(genClass));
    stringBuffer.append(TEXT_332);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_333);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.util.BasicEList"));
    stringBuffer.append(genFeature.getListTemplateArguments(genClass));
    stringBuffer.append(TEXT_334);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_335);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_336);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_337);
    }
    stringBuffer.append(TEXT_338);
    if (!isImplementation) {
    stringBuffer.append(TEXT_339);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_340);
    stringBuffer.append(genFeature.getListItemType(genClass));
    stringBuffer.append(TEXT_341);
    } else {
    stringBuffer.append(TEXT_342);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_343);
    stringBuffer.append(genFeature.getListItemType(genClass));
    stringBuffer.append(TEXT_344);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_345);
    }
    }
    if (genFeature.isGet() && (isImplementation || !genFeature.isSuppressedGetVisibility())) {
    if (isInterface) {
    stringBuffer.append(TEXT_346);
    stringBuffer.append(genFeature.getFormattedName());
    stringBuffer.append(TEXT_347);
    stringBuffer.append(genFeature.getFeatureKind());
    stringBuffer.append(TEXT_348);
    if (genFeature.isListType() && genFeature.getEcoreFeature().getEGenericType().getETypeParameter() == null) {
    if (genFeature.isMapType()) { GenFeature keyFeature = genFeature.getMapEntryTypeGenClass().getMapEntryKeyFeature(); GenFeature valueFeature = genFeature.getMapEntryTypeGenClass().getMapEntryValueFeature(); 
    stringBuffer.append(TEXT_349);
    if (keyFeature.isListType()) {
    stringBuffer.append(TEXT_350);
    stringBuffer.append(keyFeature.getQualifiedListItemType(genClass));
    stringBuffer.append(TEXT_351);
    } else {
    stringBuffer.append(TEXT_352);
    stringBuffer.append(keyFeature.getType(genClass));
    stringBuffer.append(TEXT_353);
    }
    stringBuffer.append(TEXT_354);
    if (valueFeature.isListType()) {
    stringBuffer.append(TEXT_355);
    stringBuffer.append(valueFeature.getQualifiedListItemType(genClass));
    stringBuffer.append(TEXT_356);
    } else {
    stringBuffer.append(TEXT_357);
    stringBuffer.append(valueFeature.getType(genClass));
    stringBuffer.append(TEXT_358);
    }
    stringBuffer.append(TEXT_359);
    } else if (!genFeature.isWrappedFeatureMapType() && !(genModel.isSuppressEMFMetaData() && "org.eclipse.emf.ecore.EObject".equals(genFeature.getQualifiedListItemType(genClass)))) {
String typeName = genFeature.getQualifiedListItemType(genClass); String head = typeName; String tail = ""; int index = typeName.indexOf('<'); if (index == -1) { index = typeName.indexOf('['); } 
if (index != -1) { head = typeName.substring(0, index); tail = typeName.substring(index).replaceAll("<", "&lt;"); }

    stringBuffer.append(TEXT_360);
    stringBuffer.append(head);
    stringBuffer.append(TEXT_361);
    stringBuffer.append(tail);
    stringBuffer.append(TEXT_362);
    }
    } else if (genFeature.isSetDefaultValue()) {
    stringBuffer.append(TEXT_363);
    stringBuffer.append(genFeature.getDefaultValue());
    stringBuffer.append(TEXT_364);
    }
    if (genFeature.getTypeGenEnum() != null) {
    stringBuffer.append(TEXT_365);
    stringBuffer.append(genFeature.getTypeGenEnum().getQualifiedName());
    stringBuffer.append(TEXT_366);
    }
    if (genFeature.isBidirectional() && !genFeature.getReverse().getGenClass().isMapEntry()) { GenFeature reverseGenFeature = genFeature.getReverse(); 
    if (!reverseGenFeature.isSuppressedGetVisibility()) {
    stringBuffer.append(TEXT_367);
    stringBuffer.append(reverseGenFeature.getGenClass().getQualifiedInterfaceName());
    stringBuffer.append(TEXT_368);
    stringBuffer.append(reverseGenFeature.getGetAccessor());
    stringBuffer.append(TEXT_369);
    stringBuffer.append(reverseGenFeature.getFormattedName());
    stringBuffer.append(TEXT_370);
    }
    }
    stringBuffer.append(TEXT_371);
    if (!genFeature.hasDocumentation()) {
    stringBuffer.append(TEXT_372);
    stringBuffer.append(genFeature.getFormattedName());
    stringBuffer.append(TEXT_373);
    stringBuffer.append(genFeature.getFeatureKind());
    stringBuffer.append(TEXT_374);
    }
    stringBuffer.append(TEXT_375);
    if (genFeature.hasDocumentation()) {
    stringBuffer.append(TEXT_376);
    stringBuffer.append(genFeature.getDocumentation(genModel.getIndentation(stringBuffer)));
    stringBuffer.append(TEXT_377);
    }
    stringBuffer.append(TEXT_378);
    stringBuffer.append(genFeature.getFormattedName());
    stringBuffer.append(TEXT_379);
    stringBuffer.append(genFeature.getFeatureKind());
    stringBuffer.append(TEXT_380);
    if (genFeature.getTypeGenEnum() != null) {
    stringBuffer.append(TEXT_381);
    stringBuffer.append(genFeature.getTypeGenEnum().getQualifiedName());
    }
    if (genFeature.isUnsettable()) {
    if (!genFeature.isSuppressedIsSetVisibility()) {
    stringBuffer.append(TEXT_382);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_383);
    }
    if (genFeature.isChangeable() && !genFeature.isSuppressedUnsetVisibility()) {
    stringBuffer.append(TEXT_384);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_385);
    }
    }
    if (genFeature.isChangeable() && !genFeature.isListType() && !genFeature.isSuppressedSetVisibility()) {
    stringBuffer.append(TEXT_386);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_387);
    stringBuffer.append(genFeature.getRawImportedBoundType());
    stringBuffer.append(TEXT_388);
    }
    if (!genModel.isSuppressEMFMetaData()) {
    stringBuffer.append(TEXT_389);
    stringBuffer.append(genPackage.getQualifiedPackageInterfaceName());
    stringBuffer.append(TEXT_390);
    stringBuffer.append(genFeature.getFeatureAccessorName());
    stringBuffer.append(TEXT_391);
    }
    if (genFeature.isBidirectional() && !genFeature.getReverse().getGenClass().isMapEntry()) { GenFeature reverseGenFeature = genFeature.getReverse(); 
    if (!reverseGenFeature.isSuppressedGetVisibility()) {
    stringBuffer.append(TEXT_392);
    stringBuffer.append(reverseGenFeature.getGenClass().getQualifiedInterfaceName());
    stringBuffer.append(TEXT_393);
    stringBuffer.append(reverseGenFeature.getGetAccessor());
    }
    }
    if (!genModel.isSuppressEMFModelTags()) { boolean first = true; for (StringTokenizer stringTokenizer = new StringTokenizer(genFeature.getModelInfo(), "\n\r"); stringTokenizer.hasMoreTokens(); ) { String modelInfo = stringTokenizer.nextToken(); if (first) { first = false;
    stringBuffer.append(TEXT_394);
    stringBuffer.append(modelInfo);
    } else {
    stringBuffer.append(TEXT_395);
    stringBuffer.append(modelInfo);
    }} if (first) {
    stringBuffer.append(TEXT_396);
    }}
    stringBuffer.append(TEXT_397);
    //Class/getGenFeature.javadoc.override.javajetinc
    } else {
    stringBuffer.append(TEXT_398);
    if (isJDK50) { //Class/getGenFeature.annotations.insert.javajetinc
    }
    }
    if (!isImplementation) {
    stringBuffer.append(TEXT_399);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_400);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_401);
    } else {
    if (genModel.useGenerics() && ((genFeature.isContainer() || genFeature.isResolveProxies()) && !genFeature.isListType() && !(genModel.isReflectiveDelegation() && genModel.isDynamicDelegation()) && genFeature.isUncheckedCast(genClass) || genFeature.isListType() && !genFeature.isFeatureMapType() && (genModel.isReflectiveDelegation() || genModel.isVirtualDelegation() || genModel.isDynamicDelegation()) || genFeature.isListDataType() && genFeature.hasDelegateFeature() || genFeature.isListType() && genFeature.hasSettingDelegate())) {
    stringBuffer.append(TEXT_402);
    }
    stringBuffer.append(TEXT_403);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_404);
    stringBuffer.append(genFeature.getGetAccessor());
    if (genClass.hasCollidingGetAccessorOperation(genFeature)) {
    stringBuffer.append(TEXT_405);
    }
    stringBuffer.append(TEXT_406);
    if (genModel.isDynamicDelegation()) {
    stringBuffer.append(TEXT_407);
    if (!isJDK50 && genFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_408);
    }
    stringBuffer.append(TEXT_409);
    stringBuffer.append(genFeature.getObjectType(genClass));
    stringBuffer.append(TEXT_410);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    if (genClass.hasStaticFeatures()){
    stringBuffer.append(TEXT_411);
    }
    stringBuffer.append(TEXT_412);
    stringBuffer.append(genFeature.getQualifiedFeatureAccessor());
    stringBuffer.append(TEXT_413);
    stringBuffer.append(!genFeature.isEffectiveSuppressEMFTypes());
    stringBuffer.append(TEXT_414);
    if (!isJDK50 && genFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_415);
    stringBuffer.append(genFeature.getPrimitiveValueFunction());
    stringBuffer.append(TEXT_416);
    }
    stringBuffer.append(TEXT_417);
    } else if (genModel.isReflectiveDelegation()) {
    if (genFeature.hasGetterBody()) {
    stringBuffer.append(TEXT_418);
    stringBuffer.append(genFeature.getGetterBody(genModel.getIndentation(stringBuffer)));
    } else {
    stringBuffer.append(TEXT_419);
    if (!isJDK50 && genFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_420);
    }
    stringBuffer.append(TEXT_421);
    stringBuffer.append(genFeature.getObjectType(genClass));
    stringBuffer.append(TEXT_422);
    stringBuffer.append(genFeature.getQualifiedFeatureAccessor());
    stringBuffer.append(TEXT_423);
    if (!isJDK50 && genFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_424);
    stringBuffer.append(genFeature.getPrimitiveValueFunction());
    stringBuffer.append(TEXT_425);
    }
    stringBuffer.append(TEXT_426);
    }
    } else if (genFeature.hasSettingDelegate()) {
    stringBuffer.append(TEXT_427);
    if (!isJDK50 && genFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_428);
    }
    stringBuffer.append(TEXT_429);
    stringBuffer.append(genFeature.getObjectType(genClass));
    stringBuffer.append(TEXT_430);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_431);
    if (!isJDK50 && genFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_432);
    stringBuffer.append(genFeature.getPrimitiveValueFunction());
    stringBuffer.append(TEXT_433);
    }
    stringBuffer.append(TEXT_434);
    } else if (!genFeature.isVolatile()) {
    if (genFeature.isListType()) {
    if (genModel.isVirtualDelegation()) {
    stringBuffer.append(TEXT_435);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_436);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_437);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_438);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(positiveOffsetCorrection);
    stringBuffer.append(TEXT_439);
    }
    stringBuffer.append(TEXT_440);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_441);
    if (genModel.isVirtualDelegation()) {
    stringBuffer.append(TEXT_442);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(positiveOffsetCorrection);
    stringBuffer.append(TEXT_443);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_444);
    stringBuffer.append(genClass.getListConstructor(genFeature));
    stringBuffer.append(TEXT_445);
    } else {
    stringBuffer.append(TEXT_446);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_447);
    stringBuffer.append(genClass.getListConstructor(genFeature));
    stringBuffer.append(TEXT_448);
    }
    stringBuffer.append(TEXT_449);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(genFeature.isMapType() && genFeature.isEffectiveSuppressEMFTypes() ? ".map()" : "");
    stringBuffer.append(TEXT_450);
    } else if (genFeature.isContainer()) {
    stringBuffer.append(TEXT_451);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(positiveOffsetCorrection);
    stringBuffer.append(TEXT_452);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_453);
    if (genFeature.isResolveProxies()) {
    stringBuffer.append(TEXT_454);
    } else {
    stringBuffer.append(TEXT_455);
    }
    stringBuffer.append(TEXT_456);
    } else {
    if (genFeature.isResolveProxies()) {
    if (genModel.isVirtualDelegation()) {
    stringBuffer.append(TEXT_457);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_458);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_459);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_460);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(positiveOffsetCorrection);
    if (genFeature.hasEDefault()) {
    stringBuffer.append(TEXT_461);
    stringBuffer.append(genFeature.getEDefault());
    }
    stringBuffer.append(TEXT_462);
    }
    stringBuffer.append(TEXT_463);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_464);
    stringBuffer.append(genFeature.getSafeNameAsEObject());
    stringBuffer.append(TEXT_465);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.InternalEObject"));
    stringBuffer.append(TEXT_466);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_467);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.InternalEObject"));
    stringBuffer.append(TEXT_468);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_469);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_470);
    stringBuffer.append(genFeature.getNonEObjectInternalTypeCast(genClass));
    stringBuffer.append(TEXT_471);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_472);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_473);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_474);
    if (genFeature.isEffectiveContains()) {
    stringBuffer.append(TEXT_475);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.InternalEObject"));
    stringBuffer.append(TEXT_476);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_477);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.InternalEObject"));
    stringBuffer.append(TEXT_478);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_479);
    if (!genFeature.isBidirectional()) {
    stringBuffer.append(TEXT_480);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.notify.NotificationChain"));
    stringBuffer.append(TEXT_481);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_482);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(negativeOffsetCorrection);
    stringBuffer.append(TEXT_483);
    } else { GenFeature reverseFeature = genFeature.getReverse(); GenClass targetClass = reverseFeature.getGenClass(); String reverseOffsetCorrection = targetClass.hasOffsetCorrection() ? " + " + genClass.getOffsetCorrectionField(genFeature) : "";
    stringBuffer.append(TEXT_484);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.notify.NotificationChain"));
    stringBuffer.append(TEXT_485);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_486);
    stringBuffer.append(targetClass.getQualifiedFeatureID(reverseFeature));
    stringBuffer.append(reverseOffsetCorrection);
    stringBuffer.append(TEXT_487);
    stringBuffer.append(targetClass.getRawImportedInterfaceName());
    stringBuffer.append(TEXT_488);
    }
    stringBuffer.append(TEXT_489);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_490);
    if (!genFeature.isBidirectional()) {
    stringBuffer.append(TEXT_491);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_492);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(negativeOffsetCorrection);
    stringBuffer.append(TEXT_493);
    } else { GenFeature reverseFeature = genFeature.getReverse(); GenClass targetClass = reverseFeature.getGenClass(); String reverseOffsetCorrection = targetClass.hasOffsetCorrection() ? " + " + genClass.getOffsetCorrectionField(genFeature) : "";
    stringBuffer.append(TEXT_494);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_495);
    stringBuffer.append(targetClass.getQualifiedFeatureID(reverseFeature));
    stringBuffer.append(reverseOffsetCorrection);
    stringBuffer.append(TEXT_496);
    stringBuffer.append(targetClass.getRawImportedInterfaceName());
    stringBuffer.append(TEXT_497);
    }
    stringBuffer.append(TEXT_498);
    } else if (genModel.isVirtualDelegation()) {
    stringBuffer.append(TEXT_499);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(positiveOffsetCorrection);
    stringBuffer.append(TEXT_500);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_501);
    }
    if (!genModel.isSuppressNotification()) {
    stringBuffer.append(TEXT_502);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.impl.ENotificationImpl"));
    stringBuffer.append(TEXT_503);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.notify.Notification"));
    stringBuffer.append(TEXT_504);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(positiveOffsetCorrection);
    stringBuffer.append(TEXT_505);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_506);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_507);
    }
    stringBuffer.append(TEXT_508);
    }
    if (!genFeature.isResolveProxies() && genModel.isVirtualDelegation() && !genFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_509);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_510);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(positiveOffsetCorrection);
    if (genFeature.hasEDefault()) {
    stringBuffer.append(TEXT_511);
    stringBuffer.append(genFeature.getEDefault());
    }
    stringBuffer.append(TEXT_512);
    } else if (genClass.isFlag(genFeature)) {
    if (genFeature.isBooleanType()) {
    stringBuffer.append(TEXT_513);
    stringBuffer.append(genClass.getFlagsField(genFeature));
    stringBuffer.append(TEXT_514);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_515);
    } else {
    stringBuffer.append(TEXT_516);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_517);
    stringBuffer.append(genClass.getFlagsField(genFeature));
    stringBuffer.append(TEXT_518);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_519);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_520);
    }
    } else {
    stringBuffer.append(TEXT_521);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_522);
    }
    }
    } else {//volatile
    if (genFeature.isResolveProxies() && !genFeature.isListType()) {
    stringBuffer.append(TEXT_523);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_524);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_525);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_526);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_527);
    stringBuffer.append(genFeature.getSafeNameAsEObject());
    stringBuffer.append(TEXT_528);
    stringBuffer.append(genFeature.getNonEObjectInternalTypeCast(genClass));
    stringBuffer.append(TEXT_529);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.InternalEObject"));
    stringBuffer.append(TEXT_530);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_531);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_532);
    } else if (genFeature.hasDelegateFeature()) { GenFeature delegateFeature = genFeature.getDelegateFeature();
    if (genFeature.isFeatureMapType()) {
    String featureMapEntryTemplateArgument = isJDK50 ? "<" + genModel.getImportedName("org.eclipse.emf.ecore.util.FeatureMap") + ".Entry>" : "";
    if (delegateFeature.isWrappedFeatureMapType()) {
    stringBuffer.append(TEXT_533);
    stringBuffer.append(genFeature.getImportedEffectiveFeatureMapWrapperClass());
    stringBuffer.append(TEXT_534);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.FeatureMap"));
    stringBuffer.append(TEXT_535);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.FeatureMap"));
    stringBuffer.append(TEXT_536);
    stringBuffer.append(delegateFeature.getAccessorName());
    stringBuffer.append(TEXT_537);
    stringBuffer.append(featureMapEntryTemplateArgument);
    stringBuffer.append(TEXT_538);
    stringBuffer.append(genFeature.getQualifiedFeatureAccessor());
    stringBuffer.append(TEXT_539);
    } else {
    stringBuffer.append(TEXT_540);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.FeatureMap"));
    stringBuffer.append(TEXT_541);
    stringBuffer.append(delegateFeature.getAccessorName());
    stringBuffer.append(TEXT_542);
    stringBuffer.append(featureMapEntryTemplateArgument);
    stringBuffer.append(TEXT_543);
    stringBuffer.append(genFeature.getQualifiedFeatureAccessor());
    stringBuffer.append(TEXT_544);
    }
    } else if (genFeature.isListType()) {
    if (delegateFeature.isWrappedFeatureMapType()) {
    stringBuffer.append(TEXT_545);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.FeatureMap"));
    stringBuffer.append(TEXT_546);
    stringBuffer.append(delegateFeature.getAccessorName());
    stringBuffer.append(TEXT_547);
    stringBuffer.append(genFeature.getQualifiedFeatureAccessor());
    stringBuffer.append(TEXT_548);
    } else {
    stringBuffer.append(TEXT_549);
    stringBuffer.append(delegateFeature.getAccessorName());
    stringBuffer.append(TEXT_550);
    stringBuffer.append(genFeature.getQualifiedFeatureAccessor());
    stringBuffer.append(TEXT_551);
    }
    } else {
    if (delegateFeature.isWrappedFeatureMapType()) {
    stringBuffer.append(TEXT_552);
    if (!isJDK50 && genFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_553);
    }
    if (genFeature.getTypeGenDataType() == null || !genFeature.getTypeGenDataType().isObjectType()) {
    stringBuffer.append(TEXT_554);
    stringBuffer.append(genFeature.getObjectType(genClass));
    stringBuffer.append(TEXT_555);
    }
    stringBuffer.append(TEXT_556);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.FeatureMap"));
    stringBuffer.append(TEXT_557);
    stringBuffer.append(delegateFeature.getAccessorName());
    stringBuffer.append(TEXT_558);
    stringBuffer.append(genFeature.getQualifiedFeatureAccessor());
    stringBuffer.append(TEXT_559);
    if (!isJDK50 && genFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_560);
    stringBuffer.append(genFeature.getPrimitiveValueFunction());
    stringBuffer.append(TEXT_561);
    }
    stringBuffer.append(TEXT_562);
    } else {
    stringBuffer.append(TEXT_563);
    if (!isJDK50 && genFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_564);
    }
    if (genFeature.getTypeGenDataType() == null || !genFeature.getTypeGenDataType().isObjectType()) {
    stringBuffer.append(TEXT_565);
    stringBuffer.append(genFeature.getObjectType(genClass));
    stringBuffer.append(TEXT_566);
    }
    stringBuffer.append(TEXT_567);
    stringBuffer.append(delegateFeature.getAccessorName());
    stringBuffer.append(TEXT_568);
    stringBuffer.append(genFeature.getQualifiedFeatureAccessor());
    stringBuffer.append(TEXT_569);
    if (!isJDK50 && genFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_570);
    stringBuffer.append(genFeature.getPrimitiveValueFunction());
    stringBuffer.append(TEXT_571);
    }
    stringBuffer.append(TEXT_572);
    }
    }
    } else if (genClass.getGetAccessorOperation(genFeature) != null) {
    stringBuffer.append(TEXT_573);
    stringBuffer.append(genClass.getGetAccessorOperation(genFeature).getBody(genModel.getIndentation(stringBuffer)));
    } else if (genFeature.hasGetterBody()) {
    stringBuffer.append(TEXT_574);
    stringBuffer.append(genFeature.getGetterBody(genModel.getIndentation(stringBuffer)));
    } else {
    stringBuffer.append(TEXT_575);
    stringBuffer.append(genFeature.getFormattedName());
    stringBuffer.append(TEXT_576);
    stringBuffer.append(genFeature.getFeatureKind());
    stringBuffer.append(TEXT_577);
    if (genFeature.isListType()) {
    stringBuffer.append(TEXT_578);
    if (genFeature.isMapType()) {
    stringBuffer.append(TEXT_579);
    } else if (genFeature.isFeatureMapType()) {
    stringBuffer.append(TEXT_580);
    } else {
    stringBuffer.append(TEXT_581);
    }
    stringBuffer.append(TEXT_582);
    }
    stringBuffer.append(TEXT_583);
    //Class/getGenFeature.todo.override.javajetinc
    }
    }
    stringBuffer.append(TEXT_584);
    }
    //Class/getGenFeature.override.javajetinc
    }
    if (isImplementation && !genModel.isReflectiveDelegation() && genFeature.isBasicGet()) {
    stringBuffer.append(TEXT_585);
    if (isJDK50) { //Class/basicGetGenFeature.annotations.insert.javajetinc
    }
    stringBuffer.append(TEXT_586);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_587);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_588);
    if (genModel.isDynamicDelegation()) {
    stringBuffer.append(TEXT_589);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_590);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    if (genClass.hasStaticFeatures()){
    stringBuffer.append(TEXT_591);
    }
    stringBuffer.append(TEXT_592);
    stringBuffer.append(genFeature.getQualifiedFeatureAccessor());
    stringBuffer.append(TEXT_593);
    stringBuffer.append(!genFeature.isEffectiveSuppressEMFTypes());
    stringBuffer.append(TEXT_594);
    } else if (genFeature.hasSettingDelegate()) {
    stringBuffer.append(TEXT_595);
    if (!isJDK50 && genFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_596);
    }
    stringBuffer.append(TEXT_597);
    stringBuffer.append(genFeature.getObjectType(genClass));
    stringBuffer.append(TEXT_598);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_599);
    if (!isJDK50 && genFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_600);
    stringBuffer.append(genFeature.getPrimitiveValueFunction());
    stringBuffer.append(TEXT_601);
    }
    stringBuffer.append(TEXT_602);
    } else if (genFeature.isContainer()) {
    stringBuffer.append(TEXT_603);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(positiveOffsetCorrection);
    stringBuffer.append(TEXT_604);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_605);
    } else if (!genFeature.isVolatile()) {
    if (genModel.isVirtualDelegation()) {
    stringBuffer.append(TEXT_606);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_607);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(positiveOffsetCorrection);
    stringBuffer.append(TEXT_608);
    } else {
    stringBuffer.append(TEXT_609);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_610);
    }
    } else if (genFeature.hasDelegateFeature()) { GenFeature delegateFeature = genFeature.getDelegateFeature();
    if (delegateFeature.isWrappedFeatureMapType()) {
    stringBuffer.append(TEXT_611);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_612);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.FeatureMap"));
    stringBuffer.append(TEXT_613);
    stringBuffer.append(delegateFeature.getAccessorName());
    stringBuffer.append(TEXT_614);
    stringBuffer.append(genFeature.getQualifiedFeatureAccessor());
    stringBuffer.append(TEXT_615);
    } else {
    stringBuffer.append(TEXT_616);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_617);
    stringBuffer.append(delegateFeature.getAccessorName());
    stringBuffer.append(TEXT_618);
    stringBuffer.append(genFeature.getQualifiedFeatureAccessor());
    stringBuffer.append(TEXT_619);
    }
    } else if (genFeature.hasGetterBody()) {
    stringBuffer.append(TEXT_620);
    stringBuffer.append(genFeature.getGetterBody(genModel.getIndentation(stringBuffer)));
    } else {
    stringBuffer.append(TEXT_621);
    stringBuffer.append(genFeature.getFormattedName());
    stringBuffer.append(TEXT_622);
    stringBuffer.append(genFeature.getFeatureKind());
    stringBuffer.append(TEXT_623);
    //Class/basicGetGenFeature.todo.override.javajetinc
    }
    stringBuffer.append(TEXT_624);
    //Class/basicGetGenFeature.override.javajetinc
    }
    if (isImplementation && !genModel.isReflectiveDelegation() && genFeature.isBasicSet()) {
    stringBuffer.append(TEXT_625);
    if (isJDK50) { //Class/basicSetGenFeature.annotations.insert.javajetinc
    }
    stringBuffer.append(TEXT_626);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.notify.NotificationChain"));
    stringBuffer.append(TEXT_627);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_628);
    stringBuffer.append(genFeature.getImportedInternalType(genClass));
    stringBuffer.append(TEXT_629);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_630);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.notify.NotificationChain"));
    stringBuffer.append(TEXT_631);
    if (genFeature.isContainer()) {
    stringBuffer.append(TEXT_632);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.InternalEObject"));
    stringBuffer.append(TEXT_633);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_634);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(positiveOffsetCorrection);
    stringBuffer.append(TEXT_635);
    stringBuffer.append(TEXT_636);
    } else if (genModel.isDynamicDelegation()) {
    stringBuffer.append(TEXT_637);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.InternalEObject"));
    stringBuffer.append(TEXT_638);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_639);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(positiveOffsetCorrection);
    stringBuffer.append(TEXT_640);
    stringBuffer.append(TEXT_641);
    } else if (!genFeature.isVolatile()) {
    if (genModel.isVirtualDelegation()) {
    stringBuffer.append(TEXT_642);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_643);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(positiveOffsetCorrection);
    stringBuffer.append(TEXT_644);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_645);
    } else {
    stringBuffer.append(TEXT_646);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_647);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_648);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_649);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_650);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_651);
    }
    if (genFeature.isUnsettable()) {
    if (genModel.isVirtualDelegation()) {
    if (!genModel.isSuppressNotification()) {
    stringBuffer.append(TEXT_652);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_653);
    }
    } else if (genClass.isESetFlag(genFeature)) {
    stringBuffer.append(TEXT_654);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_655);
    stringBuffer.append(genClass.getESetFlagsField(genFeature));
    stringBuffer.append(TEXT_656);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_657);
    if (!genModel.isSuppressNotification()) {
    stringBuffer.append(TEXT_658);
    stringBuffer.append(genClass.getESetFlagsField(genFeature));
    stringBuffer.append(TEXT_659);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_660);
    }
    } else {
    if (!genModel.isSuppressNotification()) {
    stringBuffer.append(TEXT_661);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_662);
    stringBuffer.append(genFeature.getUncapName());
    stringBuffer.append(TEXT_663);
    }
    stringBuffer.append(TEXT_664);
    stringBuffer.append(genFeature.getUncapName());
    stringBuffer.append(TEXT_665);
    }
    }
    if (!genModel.isSuppressNotification()) {
    stringBuffer.append(TEXT_666);
    if (genFeature.isUnsettable()) {
    stringBuffer.append(TEXT_667);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.impl.ENotificationImpl"));
    stringBuffer.append(TEXT_668);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.impl.ENotificationImpl"));
    stringBuffer.append(TEXT_669);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.notify.Notification"));
    stringBuffer.append(TEXT_670);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(positiveOffsetCorrection);
    stringBuffer.append(TEXT_671);
    if (genModel.isVirtualDelegation()) {
    stringBuffer.append(TEXT_672);
    stringBuffer.append(genFeature.getCapName());
    } else {
    stringBuffer.append(TEXT_673);
    stringBuffer.append(genFeature.getCapName());
    }
    stringBuffer.append(TEXT_674);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_675);
    if (genModel.isVirtualDelegation()) {
    stringBuffer.append(TEXT_676);
    } else {
    stringBuffer.append(TEXT_677);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_678);
    }
    stringBuffer.append(TEXT_679);
    } else {
    stringBuffer.append(TEXT_680);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.impl.ENotificationImpl"));
    stringBuffer.append(TEXT_681);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.impl.ENotificationImpl"));
    stringBuffer.append(TEXT_682);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.notify.Notification"));
    stringBuffer.append(TEXT_683);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(positiveOffsetCorrection);
    stringBuffer.append(TEXT_684);
    if (genModel.isVirtualDelegation()) {
    stringBuffer.append(TEXT_685);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_686);
    stringBuffer.append(genFeature.getCapName());
    } else {
    stringBuffer.append(TEXT_687);
    stringBuffer.append(genFeature.getCapName());
    }
    stringBuffer.append(TEXT_688);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_689);
    }
    stringBuffer.append(TEXT_690);
    }
    stringBuffer.append(TEXT_691);
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
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_697);
    } else {
    stringBuffer.append(TEXT_698);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.FeatureMap"));
    stringBuffer.append(TEXT_699);
    stringBuffer.append(delegateFeature.getAccessorName());
    stringBuffer.append(TEXT_700);
    stringBuffer.append(genFeature.getQualifiedFeatureAccessor());
    stringBuffer.append(TEXT_701);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_702);
    }
    } else {
    stringBuffer.append(TEXT_703);
    stringBuffer.append(genFeature.getFormattedName());
    stringBuffer.append(TEXT_704);
    stringBuffer.append(genFeature.getFeatureKind());
    stringBuffer.append(TEXT_705);
    //Class/basicSetGenFeature.todo.override.javajetinc
    }
    stringBuffer.append(TEXT_706);
    //Class/basicSetGenFeature.override.javajetinc
    }
    if (genFeature.isSet() && (isImplementation || !genFeature.isSuppressedSetVisibility())) {
    if (isInterface) { 
    stringBuffer.append(TEXT_707);
    stringBuffer.append(genClass.getQualifiedInterfaceName());
    stringBuffer.append(TEXT_708);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_709);
    stringBuffer.append(genFeature.getFormattedName());
    stringBuffer.append(TEXT_710);
    stringBuffer.append(genFeature.getFeatureKind());
    stringBuffer.append(TEXT_711);
    stringBuffer.append(TEXT_712);
    stringBuffer.append(genFeature.getFormattedName());
    stringBuffer.append(TEXT_713);
    stringBuffer.append(genFeature.getFeatureKind());
    stringBuffer.append(TEXT_714);
    if (genFeature.isEnumType()) {
    stringBuffer.append(TEXT_715);
    stringBuffer.append(genFeature.getTypeGenEnum().getQualifiedName());
    }
    if (genFeature.isUnsettable()) {
    if (!genFeature.isSuppressedIsSetVisibility()) {
    stringBuffer.append(TEXT_716);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_717);
    }
    if (!genFeature.isSuppressedUnsetVisibility()) {
    stringBuffer.append(TEXT_718);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_719);
    }
    }
    stringBuffer.append(TEXT_720);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_721);
    //Class/setGenFeature.javadoc.override.javajetinc
    } else {
    stringBuffer.append(TEXT_722);
    if (isJDK50) { //Class/setGenFeature.annotations.insert.javajetinc
    }
    }
    if (!isImplementation) { 
    stringBuffer.append(TEXT_723);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_724);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_725);
    } else { GenOperation setAccessorOperation = genClass.getSetAccessorOperation(genFeature);
    stringBuffer.append(TEXT_726);
    stringBuffer.append(genFeature.getAccessorName());
    if (genClass.hasCollidingSetAccessorOperation(genFeature)) {
    stringBuffer.append(TEXT_727);
    }
    stringBuffer.append(TEXT_728);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_729);
    stringBuffer.append(setAccessorOperation == null ? "new" + genFeature.getCapName() : setAccessorOperation.getGenParameters().get(0).getName());
    stringBuffer.append(TEXT_730);
    if (genModel.isDynamicDelegation()) {
    stringBuffer.append(TEXT_731);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    if (genClass.hasStaticFeatures()){
    stringBuffer.append(TEXT_732);
    }
    stringBuffer.append(TEXT_733);
    stringBuffer.append(genFeature.getQualifiedFeatureAccessor());
    stringBuffer.append(TEXT_734);
    if (!isJDK50 && genFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_735);
    stringBuffer.append(genFeature.getObjectType(genClass));
    stringBuffer.append(TEXT_736);
    }
    stringBuffer.append(TEXT_737);
    stringBuffer.append(genFeature.getCapName());
    if (!isJDK50 && genFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_738);
    }
    stringBuffer.append(TEXT_739);
    } else if (genModel.isReflectiveDelegation()) {
    stringBuffer.append(TEXT_740);
    stringBuffer.append(genFeature.getQualifiedFeatureAccessor());
    stringBuffer.append(TEXT_741);
    if (!isJDK50 && genFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_742);
    stringBuffer.append(genFeature.getObjectType(genClass));
    stringBuffer.append(TEXT_743);
    }
    stringBuffer.append(TEXT_744);
    stringBuffer.append(genFeature.getCapName());
    if (!isJDK50 && genFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_745);
    }
    stringBuffer.append(TEXT_746);
    } else if (genFeature.hasSettingDelegate()) {
    stringBuffer.append(TEXT_747);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_748);
    if (!isJDK50 && genFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_749);
    stringBuffer.append(genFeature.getObjectType(genClass));
    stringBuffer.append(TEXT_750);
    }
    stringBuffer.append(TEXT_751);
    stringBuffer.append(genFeature.getCapName());
    if (!isJDK50 && genFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_752);
    }
    stringBuffer.append(TEXT_753);
    } else if (!genFeature.isVolatile()) {
    if (genFeature.isContainer()) { GenFeature reverseFeature = genFeature.getReverse(); GenClass targetClass = reverseFeature.getGenClass(); String reverseOffsetCorrection = targetClass.hasOffsetCorrection() ? " + " + genClass.getOffsetCorrectionField(genFeature) : "";
    stringBuffer.append(TEXT_754);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_755);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(positiveOffsetCorrection);
    stringBuffer.append(TEXT_756);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_757);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.EcoreUtil"));
    stringBuffer.append(TEXT_758);
    stringBuffer.append(genFeature.getEObjectCast());
    stringBuffer.append(TEXT_759);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_760);
    stringBuffer.append(genModel.getImportedName("java.lang.IllegalArgumentException"));
    stringBuffer.append(TEXT_761);
    stringBuffer.append(genModel.getNonNLS());
    stringBuffer.append(TEXT_762);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.notify.NotificationChain"));
    stringBuffer.append(TEXT_763);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_764);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.InternalEObject"));
    stringBuffer.append(TEXT_765);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_766);
    stringBuffer.append(targetClass.getQualifiedFeatureID(reverseFeature));
    stringBuffer.append(reverseOffsetCorrection);
    stringBuffer.append(TEXT_767);
    stringBuffer.append(targetClass.getRawImportedInterfaceName());
    stringBuffer.append(TEXT_768);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_769);
    stringBuffer.append(genFeature.getInternalTypeCast());
    stringBuffer.append(TEXT_770);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_771);
    if (!genModel.isSuppressNotification()) {
    stringBuffer.append(TEXT_772);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.impl.ENotificationImpl"));
    stringBuffer.append(TEXT_773);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.notify.Notification"));
    stringBuffer.append(TEXT_774);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(positiveOffsetCorrection);
    stringBuffer.append(TEXT_775);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_776);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_777);
    }
    } else if (genFeature.isBidirectional() || genFeature.isEffectiveContains()) {
    if (genModel.isVirtualDelegation()) {
    stringBuffer.append(TEXT_778);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_779);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_780);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_781);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(positiveOffsetCorrection);
    stringBuffer.append(TEXT_782);
    }
    stringBuffer.append(TEXT_783);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_784);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_785);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.notify.NotificationChain"));
    stringBuffer.append(TEXT_786);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_787);
    if (!genFeature.isBidirectional()) {
    stringBuffer.append(TEXT_788);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.InternalEObject"));
    stringBuffer.append(TEXT_789);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_790);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(negativeOffsetCorrection);
    stringBuffer.append(TEXT_791);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_792);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.InternalEObject"));
    stringBuffer.append(TEXT_793);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_794);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(negativeOffsetCorrection);
    stringBuffer.append(TEXT_795);
    } else { GenFeature reverseFeature = genFeature.getReverse(); GenClass targetClass = reverseFeature.getGenClass(); String reverseOffsetCorrection = targetClass.hasOffsetCorrection() ? " + " + genClass.getOffsetCorrectionField(genFeature) : "";
    stringBuffer.append(TEXT_796);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.InternalEObject"));
    stringBuffer.append(TEXT_797);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_798);
    stringBuffer.append(targetClass.getQualifiedFeatureID(reverseFeature));
    stringBuffer.append(reverseOffsetCorrection);
    stringBuffer.append(TEXT_799);
    stringBuffer.append(targetClass.getRawImportedInterfaceName());
    stringBuffer.append(TEXT_800);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_801);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.InternalEObject"));
    stringBuffer.append(TEXT_802);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_803);
    stringBuffer.append(targetClass.getQualifiedFeatureID(reverseFeature));
    stringBuffer.append(reverseOffsetCorrection);
    stringBuffer.append(TEXT_804);
    stringBuffer.append(targetClass.getRawImportedInterfaceName());
    stringBuffer.append(TEXT_805);
    }
    stringBuffer.append(TEXT_806);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_807);
    stringBuffer.append(genFeature.getInternalTypeCast());
    stringBuffer.append(TEXT_808);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_809);
    if (genFeature.isUnsettable()) {
    stringBuffer.append(TEXT_810);
    if (genModel.isVirtualDelegation()) {
    stringBuffer.append(TEXT_811);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_812);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(positiveOffsetCorrection);
    stringBuffer.append(TEXT_813);
    } else if (genClass.isESetFlag(genFeature)) {
    if (!genModel.isSuppressNotification()) {
    stringBuffer.append(TEXT_814);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_815);
    stringBuffer.append(genClass.getESetFlagsField(genFeature));
    stringBuffer.append(TEXT_816);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_817);
    }
    stringBuffer.append(TEXT_818);
    stringBuffer.append(genClass.getESetFlagsField(genFeature));
    stringBuffer.append(TEXT_819);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_820);
    } else {
    if (!genModel.isSuppressNotification()) {
    stringBuffer.append(TEXT_821);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_822);
    stringBuffer.append(genFeature.getUncapName());
    stringBuffer.append(TEXT_823);
    }
    stringBuffer.append(TEXT_824);
    stringBuffer.append(genFeature.getUncapName());
    stringBuffer.append(TEXT_825);
    }
    if (!genModel.isSuppressNotification()) {
    stringBuffer.append(TEXT_826);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.impl.ENotificationImpl"));
    stringBuffer.append(TEXT_827);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.notify.Notification"));
    stringBuffer.append(TEXT_828);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(positiveOffsetCorrection);
    stringBuffer.append(TEXT_829);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_830);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_831);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_832);
    }
    stringBuffer.append(TEXT_833);
    } else {
    if (!genModel.isSuppressNotification()) {
    stringBuffer.append(TEXT_834);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.impl.ENotificationImpl"));
    stringBuffer.append(TEXT_835);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.notify.Notification"));
    stringBuffer.append(TEXT_836);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(positiveOffsetCorrection);
    stringBuffer.append(TEXT_837);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_838);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_839);
    }
    }
    } else {
    if (genClass.isFlag(genFeature)) {
    if (!genModel.isSuppressNotification()) {
    if (genFeature.isBooleanType()) {
    stringBuffer.append(TEXT_840);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_841);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_842);
    stringBuffer.append(genClass.getFlagsField(genFeature));
    stringBuffer.append(TEXT_843);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_844);
    } else {
    stringBuffer.append(TEXT_845);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_846);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_847);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_848);
    stringBuffer.append(genClass.getFlagsField(genFeature));
    stringBuffer.append(TEXT_849);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_850);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_851);
    }
    }
    if (genFeature.isBooleanType()) {
    stringBuffer.append(TEXT_852);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_853);
    stringBuffer.append(genClass.getFlagsField(genFeature));
    stringBuffer.append(TEXT_854);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_855);
    stringBuffer.append(genClass.getFlagsField(genFeature));
    stringBuffer.append(TEXT_856);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_857);
    } else {
    stringBuffer.append(TEXT_858);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_859);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_860);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_861);
    stringBuffer.append(genClass.getFlagsField(genFeature));
    stringBuffer.append(TEXT_862);
    stringBuffer.append(genClass.getFlagsField(genFeature));
    stringBuffer.append(TEXT_863);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_864);
    if (isJDK50) {
    stringBuffer.append(TEXT_865);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_866);
    } else {
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_867);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_868);
    }
    stringBuffer.append(TEXT_869);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_870);
    }
    } else {
    if (!genModel.isVirtualDelegation() || genFeature.isPrimitiveType()) {
    if (!genModel.isSuppressNotification()) {
    stringBuffer.append(TEXT_871);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_872);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_873);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_874);
    }
    }
    if (genFeature.isEnumType()) {
    if (genModel.isVirtualDelegation()) {
    stringBuffer.append(TEXT_875);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_876);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_877);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_878);
    stringBuffer.append(genFeature.getEDefault());
    stringBuffer.append(TEXT_879);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_880);
    } else {
    stringBuffer.append(TEXT_881);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_882);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_883);
    stringBuffer.append(genFeature.getEDefault());
    stringBuffer.append(TEXT_884);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_885);
    }
    } else {
    if (genModel.isVirtualDelegation() && !genFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_886);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_887);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_888);
    stringBuffer.append(genFeature.getInternalTypeCast());
    stringBuffer.append(TEXT_889);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_890);
    } else {
    stringBuffer.append(TEXT_891);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_892);
    stringBuffer.append(genFeature.getInternalTypeCast());
    stringBuffer.append(TEXT_893);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_894);
    }
    }
    if (genModel.isVirtualDelegation() && !genFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_895);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_896);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(positiveOffsetCorrection);
    stringBuffer.append(TEXT_897);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_898);
    }
    }
    if (genFeature.isUnsettable()) {
    if (genModel.isVirtualDelegation() && !genFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_899);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_900);
    } else if (genClass.isESetFlag(genFeature)) {
    if (!genModel.isSuppressNotification()) {
    stringBuffer.append(TEXT_901);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_902);
    stringBuffer.append(genClass.getESetFlagsField(genFeature));
    stringBuffer.append(TEXT_903);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_904);
    }
    stringBuffer.append(TEXT_905);
    stringBuffer.append(genClass.getESetFlagsField(genFeature));
    stringBuffer.append(TEXT_906);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_907);
    } else {
    if (!genModel.isSuppressNotification()) {
    stringBuffer.append(TEXT_908);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_909);
    stringBuffer.append(genFeature.getUncapName());
    stringBuffer.append(TEXT_910);
    }
    stringBuffer.append(TEXT_911);
    stringBuffer.append(genFeature.getUncapName());
    stringBuffer.append(TEXT_912);
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
    stringBuffer.append(genFeature.getEDefault());
    stringBuffer.append(TEXT_918);
    stringBuffer.append(genFeature.getCapName());
    } else {
    stringBuffer.append(TEXT_919);
    stringBuffer.append(genFeature.getCapName());
    }
    stringBuffer.append(TEXT_920);
    if (genClass.isFlag(genFeature)) {
    stringBuffer.append(TEXT_921);
    stringBuffer.append(genFeature.getCapName());
    } else {
    stringBuffer.append(genFeature.getSafeName());
    }
    stringBuffer.append(TEXT_922);
    if (genModel.isVirtualDelegation() && !genFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_923);
    } else {
    stringBuffer.append(TEXT_924);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_925);
    }
    stringBuffer.append(TEXT_926);
    }
    } else {
    if (!genModel.isSuppressNotification()) {
    stringBuffer.append(TEXT_927);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.impl.ENotificationImpl"));
    stringBuffer.append(TEXT_928);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.notify.Notification"));
    stringBuffer.append(TEXT_929);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(positiveOffsetCorrection);
    stringBuffer.append(TEXT_930);
    if (genModel.isVirtualDelegation() && !genFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_931);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_932);
    stringBuffer.append(genFeature.getEDefault());
    stringBuffer.append(TEXT_933);
    stringBuffer.append(genFeature.getCapName());
    } else {
    stringBuffer.append(TEXT_934);
    stringBuffer.append(genFeature.getCapName());
    }
    stringBuffer.append(TEXT_935);
    if (genClass.isFlag(genFeature)) {
    stringBuffer.append(TEXT_936);
    stringBuffer.append(genFeature.getCapName());
    } else {
    stringBuffer.append(genFeature.getSafeName());
    }
    stringBuffer.append(TEXT_937);
    }
    }
    }
    } else if (genFeature.hasDelegateFeature()) { GenFeature delegateFeature = genFeature.getDelegateFeature();
    if (delegateFeature.isWrappedFeatureMapType()) {
    stringBuffer.append(TEXT_938);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.FeatureMap"));
    stringBuffer.append(TEXT_939);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.FeatureMap"));
    stringBuffer.append(TEXT_940);
    stringBuffer.append(delegateFeature.getAccessorName());
    stringBuffer.append(TEXT_941);
    stringBuffer.append(genFeature.getQualifiedFeatureAccessor());
    stringBuffer.append(TEXT_942);
    if (!isJDK50 && genFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_943);
    stringBuffer.append(genFeature.getObjectType(genClass));
    stringBuffer.append(TEXT_944);
    }
    stringBuffer.append(TEXT_945);
    stringBuffer.append(genFeature.getCapName());
    if (!isJDK50 && genFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_946);
    }
    stringBuffer.append(TEXT_947);
    } else {
    stringBuffer.append(TEXT_948);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.FeatureMap"));
    stringBuffer.append(TEXT_949);
    stringBuffer.append(delegateFeature.getAccessorName());
    stringBuffer.append(TEXT_950);
    stringBuffer.append(genFeature.getQualifiedFeatureAccessor());
    stringBuffer.append(TEXT_951);
    if (!isJDK50 && genFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_952);
    stringBuffer.append(genFeature.getObjectType(genClass));
    stringBuffer.append(TEXT_953);
    }
    stringBuffer.append(TEXT_954);
    stringBuffer.append(genFeature.getCapName());
    if (!isJDK50 && genFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_955);
    }
    stringBuffer.append(TEXT_956);
    }
    } else if (setAccessorOperation != null) {
    stringBuffer.append(TEXT_957);
    stringBuffer.append(setAccessorOperation.getBody(genModel.getIndentation(stringBuffer)));
    } else {
    stringBuffer.append(TEXT_958);
    stringBuffer.append(genFeature.getFormattedName());
    stringBuffer.append(TEXT_959);
    stringBuffer.append(genFeature.getFeatureKind());
    stringBuffer.append(TEXT_960);
    //Class/setGenFeature.todo.override.javajetinc
    }
    stringBuffer.append(TEXT_961);
    }
    //Class/setGenFeature.override.javajetinc
    }
    if (isImplementation && !genModel.isReflectiveDelegation() && genFeature.isBasicUnset()) {
    stringBuffer.append(TEXT_962);
    if (isJDK50) { //Class/basicUnsetGenFeature.annotations.insert.javajetinc
    }
    stringBuffer.append(TEXT_963);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.notify.NotificationChain"));
    stringBuffer.append(TEXT_964);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_965);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.notify.NotificationChain"));
    stringBuffer.append(TEXT_966);
    if (genModel.isDynamicDelegation()) {
    stringBuffer.append(TEXT_967);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.InternalEObject"));
    stringBuffer.append(TEXT_968);
    if (genFeature.isResolveProxies()) {
    stringBuffer.append(TEXT_969);
    stringBuffer.append(genFeature.getAccessorName());
    } else {
    stringBuffer.append(genFeature.getGetAccessor());
    }
    stringBuffer.append(TEXT_970);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(positiveOffsetCorrection);
    stringBuffer.append(TEXT_971);
    } else if (!genFeature.isVolatile()) {
    if (genModel.isVirtualDelegation()) {
    if (!genModel.isSuppressNotification()) {
    stringBuffer.append(TEXT_972);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_973);
    }
    stringBuffer.append(TEXT_974);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(positiveOffsetCorrection);
    stringBuffer.append(TEXT_975);
    } else {
    if (!genModel.isSuppressNotification()) {
    stringBuffer.append(TEXT_976);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_977);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_978);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_979);
    }
    stringBuffer.append(TEXT_980);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_981);
    }
    if (genModel.isVirtualDelegation()) {
    if (!genModel.isSuppressNotification()) {
    stringBuffer.append(TEXT_982);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_983);
    }
    } else if (genClass.isESetFlag(genFeature)) {
    if (!genModel.isSuppressNotification()) {
    stringBuffer.append(TEXT_984);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_985);
    stringBuffer.append(genClass.getESetFlagsField(genFeature));
    stringBuffer.append(TEXT_986);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_987);
    }
    stringBuffer.append(TEXT_988);
    stringBuffer.append(genClass.getESetFlagsField(genFeature));
    stringBuffer.append(TEXT_989);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_990);
    } else {
    if (!genModel.isSuppressNotification()) {
    stringBuffer.append(TEXT_991);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_992);
    stringBuffer.append(genFeature.getUncapName());
    stringBuffer.append(TEXT_993);
    }
    stringBuffer.append(TEXT_994);
    stringBuffer.append(genFeature.getUncapName());
    stringBuffer.append(TEXT_995);
    }
    if (!genModel.isSuppressNotification()) {
    stringBuffer.append(TEXT_996);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.impl.ENotificationImpl"));
    stringBuffer.append(TEXT_997);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.impl.ENotificationImpl"));
    stringBuffer.append(TEXT_998);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.notify.Notification"));
    stringBuffer.append(TEXT_999);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(positiveOffsetCorrection);
    stringBuffer.append(TEXT_1000);
    if (genModel.isVirtualDelegation()) {
    stringBuffer.append(TEXT_1001);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_1002);
    } else {
    stringBuffer.append(TEXT_1003);
    stringBuffer.append(genFeature.getCapName());
    }
    stringBuffer.append(TEXT_1004);
    if (genModel.isVirtualDelegation()) {
    stringBuffer.append(TEXT_1005);
    } else {
    stringBuffer.append(TEXT_1006);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_1007);
    }
    stringBuffer.append(TEXT_1008);
    }
    } else {
    stringBuffer.append(TEXT_1009);
    stringBuffer.append(genFeature.getFormattedName());
    stringBuffer.append(TEXT_1010);
    stringBuffer.append(genFeature.getFeatureKind());
    stringBuffer.append(TEXT_1011);
    //Class/basicUnsetGenFeature.todo.override.javajetinc
    }
    stringBuffer.append(TEXT_1012);
    //Class.basicUnsetGenFeature.override.javajetinc
    }
    if (genFeature.isUnset() && (isImplementation || !genFeature.isSuppressedUnsetVisibility())) {
    if (isInterface) {
    stringBuffer.append(TEXT_1013);
    stringBuffer.append(genClass.getQualifiedInterfaceName());
    stringBuffer.append(TEXT_1014);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_1015);
    stringBuffer.append(genFeature.getFormattedName());
    stringBuffer.append(TEXT_1016);
    stringBuffer.append(genFeature.getFeatureKind());
    stringBuffer.append(TEXT_1017);
    stringBuffer.append(TEXT_1018);
    if (!genFeature.isSuppressedIsSetVisibility()) {
    stringBuffer.append(TEXT_1019);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_1020);
    }
    stringBuffer.append(TEXT_1021);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_1022);
    if (!genFeature.isListType() && !genFeature.isSuppressedSetVisibility()) {
    stringBuffer.append(TEXT_1023);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_1024);
    stringBuffer.append(genFeature.getRawImportedBoundType());
    stringBuffer.append(TEXT_1025);
    }
    stringBuffer.append(TEXT_1026);
    //Class/unsetGenFeature.javadoc.override.javajetinc
    } else {
    stringBuffer.append(TEXT_1027);
    if (isJDK50) { //Class/unsetGenFeature.annotations.insert.javajetinc
    }
    }
    if (!isImplementation) {
    stringBuffer.append(TEXT_1028);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_1029);
    } else {
    stringBuffer.append(TEXT_1030);
    stringBuffer.append(genFeature.getAccessorName());
    if (genClass.hasCollidingUnsetAccessorOperation(genFeature)) {
    stringBuffer.append(TEXT_1031);
    }
    stringBuffer.append(TEXT_1032);
    if (genModel.isDynamicDelegation()) {
    stringBuffer.append(TEXT_1033);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    if (genClass.hasStaticFeatures()){
    stringBuffer.append(TEXT_1034);
    }
    stringBuffer.append(TEXT_1035);
    stringBuffer.append(genFeature.getQualifiedFeatureAccessor());
    stringBuffer.append(TEXT_1036);
    } else if (genModel.isReflectiveDelegation()) {
    stringBuffer.append(TEXT_1037);
    stringBuffer.append(genFeature.getQualifiedFeatureAccessor());
    stringBuffer.append(TEXT_1038);
    } else if (genFeature.hasSettingDelegate()) {
    stringBuffer.append(TEXT_1039);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_1040);
    } else if (!genFeature.isVolatile()) {
    if (genFeature.isListType()) {
    if (genModel.isVirtualDelegation()) {
    stringBuffer.append(TEXT_1041);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_1042);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_1043);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_1044);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(positiveOffsetCorrection);
    stringBuffer.append(TEXT_1045);
    }
    stringBuffer.append(TEXT_1046);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_1047);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.InternalEList"));
    stringBuffer.append(TEXT_1048);
    stringBuffer.append(singleWildcard);
    stringBuffer.append(TEXT_1049);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_1050);
    } else if (genFeature.isBidirectional() || genFeature.isEffectiveContains()) {
    if (genModel.isVirtualDelegation()) {
    stringBuffer.append(TEXT_1051);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_1052);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_1053);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_1054);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(positiveOffsetCorrection);
    stringBuffer.append(TEXT_1055);
    }
    stringBuffer.append(TEXT_1056);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_1057);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.notify.NotificationChain"));
    stringBuffer.append(TEXT_1058);
    if (!genFeature.isBidirectional()) {
    stringBuffer.append(TEXT_1059);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.InternalEObject"));
    stringBuffer.append(TEXT_1060);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_1061);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(negativeOffsetCorrection);
    stringBuffer.append(TEXT_1062);
    } else { GenFeature reverseFeature = genFeature.getReverse(); GenClass targetClass = reverseFeature.getGenClass(); String reverseOffsetCorrection = targetClass.hasOffsetCorrection() ? " + " + genClass.getOffsetCorrectionField(genFeature) : "";
    stringBuffer.append(TEXT_1063);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.InternalEObject"));
    stringBuffer.append(TEXT_1064);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_1065);
    stringBuffer.append(targetClass.getQualifiedFeatureID(reverseFeature));
    stringBuffer.append(reverseOffsetCorrection);
    stringBuffer.append(TEXT_1066);
    stringBuffer.append(targetClass.getRawImportedInterfaceName());
    stringBuffer.append(TEXT_1067);
    }
    stringBuffer.append(TEXT_1068);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_1069);
    if (genModel.isVirtualDelegation()) {
    stringBuffer.append(TEXT_1070);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_1071);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(positiveOffsetCorrection);
    stringBuffer.append(TEXT_1072);
    } else if (genClass.isESetFlag(genFeature)) {
    if (!genModel.isSuppressNotification()) {
    stringBuffer.append(TEXT_1073);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_1074);
    stringBuffer.append(genClass.getESetFlagsField(genFeature));
    stringBuffer.append(TEXT_1075);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_1076);
    }
    stringBuffer.append(TEXT_1077);
    stringBuffer.append(genClass.getESetFlagsField(genFeature));
    stringBuffer.append(TEXT_1078);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_1079);
    } else {
    if (!genModel.isSuppressNotification()) {
    stringBuffer.append(TEXT_1080);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_1081);
    stringBuffer.append(genFeature.getUncapName());
    stringBuffer.append(TEXT_1082);
    }
    stringBuffer.append(TEXT_1083);
    stringBuffer.append(genFeature.getUncapName());
    stringBuffer.append(TEXT_1084);
    }
    if (!genModel.isSuppressNotification()) {
    stringBuffer.append(TEXT_1085);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.impl.ENotificationImpl"));
    stringBuffer.append(TEXT_1086);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.notify.Notification"));
    stringBuffer.append(TEXT_1087);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(positiveOffsetCorrection);
    stringBuffer.append(TEXT_1088);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_1089);
    }
    stringBuffer.append(TEXT_1090);
    } else {
    if (genClass.isFlag(genFeature)) {
    if (!genModel.isSuppressNotification()) {
    if (genFeature.isBooleanType()) {
    stringBuffer.append(TEXT_1091);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_1092);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_1093);
    stringBuffer.append(genClass.getFlagsField(genFeature));
    stringBuffer.append(TEXT_1094);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_1095);
    } else {
    stringBuffer.append(TEXT_1096);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_1097);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_1098);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_1099);
    stringBuffer.append(genClass.getFlagsField(genFeature));
    stringBuffer.append(TEXT_1100);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_1101);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_1102);
    }
    }
    } else if (genModel.isVirtualDelegation() && !genFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_1103);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_1104);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(positiveOffsetCorrection);
    stringBuffer.append(TEXT_1105);
    } else {
    if (!genModel.isSuppressNotification()) {
    stringBuffer.append(TEXT_1106);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_1107);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_1108);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_1109);
    }
    }
    if (!genModel.isSuppressNotification()) {
    if (genModel.isVirtualDelegation() && !genFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_1110);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_1111);
    } else if (genClass.isESetFlag(genFeature)) {
    stringBuffer.append(TEXT_1112);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_1113);
    stringBuffer.append(genClass.getESetFlagsField(genFeature));
    stringBuffer.append(TEXT_1114);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_1115);
    } else {
    stringBuffer.append(TEXT_1116);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_1117);
    stringBuffer.append(genFeature.getUncapName());
    stringBuffer.append(TEXT_1118);
    }
    }
    if (genFeature.isReferenceType()) {
    stringBuffer.append(TEXT_1119);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_1120);
    if (!genModel.isVirtualDelegation()) {
    if (genClass.isESetFlag(genFeature)) {
    stringBuffer.append(TEXT_1121);
    stringBuffer.append(genClass.getESetFlagsField(genFeature));
    stringBuffer.append(TEXT_1122);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_1123);
    } else {
    stringBuffer.append(TEXT_1124);
    stringBuffer.append(genFeature.getUncapName());
    stringBuffer.append(TEXT_1125);
    }
    }
    if (!genModel.isSuppressNotification()) {
    stringBuffer.append(TEXT_1126);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.impl.ENotificationImpl"));
    stringBuffer.append(TEXT_1127);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.notify.Notification"));
    stringBuffer.append(TEXT_1128);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(positiveOffsetCorrection);
    stringBuffer.append(TEXT_1129);
    if (genModel.isVirtualDelegation()) {
    stringBuffer.append(TEXT_1130);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_1131);
    } else {
    stringBuffer.append(TEXT_1132);
    stringBuffer.append(genFeature.getCapName());
    }
    stringBuffer.append(TEXT_1133);
    if (genModel.isVirtualDelegation()) {
    stringBuffer.append(TEXT_1134);
    } else {
    stringBuffer.append(TEXT_1135);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_1136);
    }
    stringBuffer.append(TEXT_1137);
    }
    } else {
    if (genClass.isFlag(genFeature)) {
    if (genFeature.isBooleanType()) {
    stringBuffer.append(TEXT_1138);
    stringBuffer.append(genFeature.getEDefault());
    stringBuffer.append(TEXT_1139);
    stringBuffer.append(genClass.getFlagsField(genFeature));
    stringBuffer.append(TEXT_1140);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_1141);
    stringBuffer.append(genClass.getFlagsField(genFeature));
    stringBuffer.append(TEXT_1142);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_1143);
    } else {
    stringBuffer.append(TEXT_1144);
    stringBuffer.append(genClass.getFlagsField(genFeature));
    stringBuffer.append(TEXT_1145);
    stringBuffer.append(genClass.getFlagsField(genFeature));
    stringBuffer.append(TEXT_1146);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_1147);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_1148);
    }
    } else if (!genModel.isVirtualDelegation() || genFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_1149);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_1150);
    stringBuffer.append(genFeature.getEDefault());
    stringBuffer.append(TEXT_1151);
    }
    if (!genModel.isVirtualDelegation() || genFeature.isPrimitiveType()) {
    if (genClass.isESetFlag(genFeature)) {
    stringBuffer.append(TEXT_1152);
    stringBuffer.append(genClass.getESetFlagsField(genFeature));
    stringBuffer.append(TEXT_1153);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_1154);
    } else {
    stringBuffer.append(TEXT_1155);
    stringBuffer.append(genFeature.getUncapName());
    stringBuffer.append(TEXT_1156);
    }
    }
    if (!genModel.isSuppressNotification()) {
    stringBuffer.append(TEXT_1157);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.impl.ENotificationImpl"));
    stringBuffer.append(TEXT_1158);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.notify.Notification"));
    stringBuffer.append(TEXT_1159);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(positiveOffsetCorrection);
    stringBuffer.append(TEXT_1160);
    if (genModel.isVirtualDelegation() && !genFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_1161);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_1162);
    stringBuffer.append(genFeature.getEDefault());
    } else {
    stringBuffer.append(TEXT_1163);
    stringBuffer.append(genFeature.getCapName());
    }
    stringBuffer.append(TEXT_1164);
    stringBuffer.append(genFeature.getEDefault());
    stringBuffer.append(TEXT_1165);
    if (genModel.isVirtualDelegation() && !genFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_1166);
    } else {
    stringBuffer.append(TEXT_1167);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_1168);
    }
    stringBuffer.append(TEXT_1169);
    }
    }
    }
    } else if (genFeature.hasDelegateFeature()) { GenFeature delegateFeature = genFeature.getDelegateFeature();
    if (delegateFeature.isWrappedFeatureMapType()) {
    stringBuffer.append(TEXT_1170);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.FeatureMap"));
    stringBuffer.append(TEXT_1171);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.FeatureMap"));
    stringBuffer.append(TEXT_1172);
    stringBuffer.append(delegateFeature.getAccessorName());
    stringBuffer.append(TEXT_1173);
    stringBuffer.append(genFeature.getQualifiedFeatureAccessor());
    stringBuffer.append(TEXT_1174);
    } else {
    stringBuffer.append(TEXT_1175);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.FeatureMap"));
    stringBuffer.append(TEXT_1176);
    stringBuffer.append(delegateFeature.getAccessorName());
    stringBuffer.append(TEXT_1177);
    stringBuffer.append(genFeature.getQualifiedFeatureAccessor());
    stringBuffer.append(TEXT_1178);
    }
    } else if (genClass.getUnsetAccessorOperation(genFeature) != null) {
    stringBuffer.append(TEXT_1179);
    stringBuffer.append(genClass.getUnsetAccessorOperation(genFeature).getBody(genModel.getIndentation(stringBuffer)));
    } else {
    stringBuffer.append(TEXT_1180);
    stringBuffer.append(genFeature.getFormattedName());
    stringBuffer.append(TEXT_1181);
    stringBuffer.append(genFeature.getFeatureKind());
    stringBuffer.append(TEXT_1182);
    //Class/unsetGenFeature.todo.override.javajetinc
    }
    stringBuffer.append(TEXT_1183);
    }
    //Class/unsetGenFeature.override.javajetinc
    }
    if (genFeature.isIsSet() && (isImplementation || !genFeature.isSuppressedIsSetVisibility())) {
    if (isInterface) {
    stringBuffer.append(TEXT_1184);
    stringBuffer.append(genClass.getQualifiedInterfaceName());
    stringBuffer.append(TEXT_1185);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_1186);
    stringBuffer.append(genFeature.getFormattedName());
    stringBuffer.append(TEXT_1187);
    stringBuffer.append(genFeature.getFeatureKind());
    stringBuffer.append(TEXT_1188);
    stringBuffer.append(TEXT_1189);
    stringBuffer.append(genFeature.getFormattedName());
    stringBuffer.append(TEXT_1190);
    stringBuffer.append(genFeature.getFeatureKind());
    stringBuffer.append(TEXT_1191);
    if (genFeature.isChangeable() && !genFeature.isSuppressedUnsetVisibility()) {
    stringBuffer.append(TEXT_1192);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_1193);
    }
    stringBuffer.append(TEXT_1194);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_1195);
    if (!genFeature.isListType() && genFeature.isChangeable() && !genFeature.isSuppressedSetVisibility()) {
    stringBuffer.append(TEXT_1196);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_1197);
    stringBuffer.append(genFeature.getRawImportedBoundType());
    stringBuffer.append(TEXT_1198);
    }
    stringBuffer.append(TEXT_1199);
    //Class/isSetGenFeature.javadoc.override.javajetinc
    } else {
    stringBuffer.append(TEXT_1200);
    if (isJDK50) { //Class/isSetGenFeature.annotations.insert.javajetinc
    }
    }
    if (!isImplementation) {
    stringBuffer.append(TEXT_1201);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_1202);
    } else {
    stringBuffer.append(TEXT_1203);
    stringBuffer.append(genFeature.getAccessorName());
    if (genClass.hasCollidingIsSetAccessorOperation(genFeature)) {
    stringBuffer.append(TEXT_1204);
    }
    stringBuffer.append(TEXT_1205);
    if (genModel.isDynamicDelegation()) {
    stringBuffer.append(TEXT_1206);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    if (genClass.hasStaticFeatures()){
    stringBuffer.append(TEXT_1207);
    }
    stringBuffer.append(TEXT_1208);
    stringBuffer.append(genFeature.getQualifiedFeatureAccessor());
    stringBuffer.append(TEXT_1209);
    } else if (genModel.isReflectiveDelegation()) {
    stringBuffer.append(TEXT_1210);
    stringBuffer.append(genFeature.getQualifiedFeatureAccessor());
    stringBuffer.append(TEXT_1211);
    } else if (genFeature.hasSettingDelegate()) {
    stringBuffer.append(TEXT_1212);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_1213);
    } else if (!genFeature.isVolatile()) {
    if (genFeature.isListType()) {
    if (genModel.isVirtualDelegation()) {
    stringBuffer.append(TEXT_1214);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_1215);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_1216);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_1217);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(positiveOffsetCorrection);
    stringBuffer.append(TEXT_1218);
    }
    stringBuffer.append(TEXT_1219);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_1220);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.InternalEList"));
    stringBuffer.append(TEXT_1221);
    stringBuffer.append(singleWildcard);
    stringBuffer.append(TEXT_1222);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_1223);
    } else {
    if (genModel.isVirtualDelegation() && !genFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_1224);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(positiveOffsetCorrection);
    stringBuffer.append(TEXT_1225);
    } else if (genClass.isESetFlag(genFeature)) {
    stringBuffer.append(TEXT_1226);
    stringBuffer.append(genClass.getESetFlagsField(genFeature));
    stringBuffer.append(TEXT_1227);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_1228);
    } else {
    stringBuffer.append(TEXT_1229);
    stringBuffer.append(genFeature.getUncapName());
    stringBuffer.append(TEXT_1230);
    }
    }
    } else if (genFeature.hasDelegateFeature()) { GenFeature delegateFeature = genFeature.getDelegateFeature();
    if (delegateFeature.isWrappedFeatureMapType()) {
    stringBuffer.append(TEXT_1231);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.FeatureMap"));
    stringBuffer.append(TEXT_1232);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.FeatureMap"));
    stringBuffer.append(TEXT_1233);
    stringBuffer.append(delegateFeature.getAccessorName());
    stringBuffer.append(TEXT_1234);
    stringBuffer.append(genFeature.getQualifiedFeatureAccessor());
    stringBuffer.append(TEXT_1235);
    } else {
    stringBuffer.append(TEXT_1236);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.FeatureMap"));
    stringBuffer.append(TEXT_1237);
    stringBuffer.append(delegateFeature.getAccessorName());
    stringBuffer.append(TEXT_1238);
    stringBuffer.append(genFeature.getQualifiedFeatureAccessor());
    stringBuffer.append(TEXT_1239);
    }
    } else if (genClass.getIsSetAccessorOperation(genFeature) != null) {
    stringBuffer.append(TEXT_1240);
    stringBuffer.append(genClass.getIsSetAccessorOperation(genFeature).getBody(genModel.getIndentation(stringBuffer)));
    } else {
    stringBuffer.append(TEXT_1241);
    stringBuffer.append(genFeature.getFormattedName());
    stringBuffer.append(TEXT_1242);
    stringBuffer.append(genFeature.getFeatureKind());
    stringBuffer.append(TEXT_1243);
    //Class/isSetGenFeature.todo.override.javajetinc
    }
    stringBuffer.append(TEXT_1244);
    }
    //Class/isSetGenFeature.override.javajetinc
    }
    //Class/genFeature.override.javajetinc
    }//for
    }}.run();
    for (GenOperation genOperation : (isImplementation ? genClass.getImplementedGenOperations() : genClass.getDeclaredGenOperations())) {
    if (isImplementation) {
    if (genOperation.isInvariant() && genOperation.hasInvariantExpression()) {
    stringBuffer.append(TEXT_1245);
    stringBuffer.append(genOperation.getName());
    stringBuffer.append(TEXT_1246);
    stringBuffer.append(genOperation.getParameterTypes(", "));
    stringBuffer.append(TEXT_1247);
    stringBuffer.append(genOperation.getFormattedName());
    stringBuffer.append(TEXT_1248);
    stringBuffer.append(genOperation.getName());
    stringBuffer.append(TEXT_1249);
    stringBuffer.append(genOperation.getParameterTypes(", "));
    stringBuffer.append(TEXT_1250);
    stringBuffer.append(genModel.getImportedName("java.lang.String"));
    stringBuffer.append(TEXT_1251);
    stringBuffer.append(CodeGenUtil.upperName(genClass.getUniqueName(genOperation), genModel.getLocale()));
    stringBuffer.append(TEXT_1252);
    stringBuffer.append(genOperation.getInvariantExpression("\t\t"));
    stringBuffer.append(TEXT_1253);
    stringBuffer.append(genModel.getNonNLS());
    stringBuffer.append(TEXT_1254);
    } else if (genOperation.hasInvocationDelegate()) {
    stringBuffer.append(TEXT_1255);
    stringBuffer.append(genOperation.getName());
    stringBuffer.append(TEXT_1256);
    stringBuffer.append(genOperation.getParameterTypes(", "));
    stringBuffer.append(TEXT_1257);
    stringBuffer.append(genOperation.getFormattedName());
    stringBuffer.append(TEXT_1258);
    stringBuffer.append(genOperation.getName());
    stringBuffer.append(TEXT_1259);
    stringBuffer.append(genOperation.getParameterTypes(", "));
    stringBuffer.append(TEXT_1260);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.EOperation"));
    stringBuffer.append(TEXT_1261);
    stringBuffer.append(CodeGenUtil.upperName(genClass.getUniqueName(genOperation), genModel.getLocale()));
    stringBuffer.append(TEXT_1262);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.EOperation"));
    stringBuffer.append(TEXT_1263);
    stringBuffer.append(genOperation.getQualifiedOperationAccessor());
    stringBuffer.append(TEXT_1264);
    }
    }
    if (isInterface) {
    stringBuffer.append(TEXT_1265);
    stringBuffer.append(TEXT_1266);
    if (genOperation.hasDocumentation() || genOperation.hasParameterDocumentation()) {
    stringBuffer.append(TEXT_1267);
    if (genOperation.hasDocumentation()) {
    stringBuffer.append(TEXT_1268);
    stringBuffer.append(genOperation.getDocumentation(genModel.getIndentation(stringBuffer)));
    }
    for (GenParameter genParameter : genOperation.getGenParameters()) {
    if (genParameter.hasDocumentation()) { String documentation = genParameter.getDocumentation("");
    if (documentation.contains("\n") || documentation.contains("\r")) {
    stringBuffer.append(TEXT_1269);
    stringBuffer.append(genParameter.getName());
    stringBuffer.append(TEXT_1270);
    stringBuffer.append(genParameter.getDocumentation(genModel.getIndentation(stringBuffer)));
    } else {
    stringBuffer.append(TEXT_1271);
    stringBuffer.append(genParameter.getName());
    stringBuffer.append(TEXT_1272);
    stringBuffer.append(genParameter.getDocumentation(genModel.getIndentation(stringBuffer)));
    }
    }
    }
    stringBuffer.append(TEXT_1273);
    }
    if (!genModel.isSuppressEMFModelTags()) { boolean first = true; for (StringTokenizer stringTokenizer = new StringTokenizer(genOperation.getModelInfo(), "\n\r"); stringTokenizer.hasMoreTokens(); ) { String modelInfo = stringTokenizer.nextToken(); if (first) { first = false;
    stringBuffer.append(TEXT_1274);
    stringBuffer.append(modelInfo);
    } else {
    stringBuffer.append(TEXT_1275);
    stringBuffer.append(modelInfo);
    }} if (first) {
    stringBuffer.append(TEXT_1276);
    }}
    stringBuffer.append(TEXT_1277);
    //Class/genOperation.javadoc.override.javajetinc
    } else {
    stringBuffer.append(TEXT_1278);
    if (isJDK50) { //Class/genOperation.annotations.insert.javajetinc
    }
    }
    if (!isImplementation) {
    stringBuffer.append(TEXT_1279);
    stringBuffer.append(genOperation.getTypeParameters(genClass));
    stringBuffer.append(genOperation.getImportedType(genClass));
    stringBuffer.append(TEXT_1280);
    stringBuffer.append(genOperation.getName());
    stringBuffer.append(TEXT_1281);
    stringBuffer.append(genOperation.getParameters(genClass));
    stringBuffer.append(TEXT_1282);
    stringBuffer.append(genOperation.getThrows(genClass));
    stringBuffer.append(TEXT_1283);
    } else {
    if (genModel.useGenerics() && !genOperation.hasBody() && !genOperation.isInvariant() && genOperation.hasInvocationDelegate() && genOperation.isUncheckedCast(genClass)) {
    stringBuffer.append(TEXT_1284);
    }
    stringBuffer.append(TEXT_1285);
    stringBuffer.append(genOperation.getTypeParameters(genClass));
    stringBuffer.append(genOperation.getImportedType(genClass));
    stringBuffer.append(TEXT_1286);
    stringBuffer.append(genOperation.getName());
    stringBuffer.append(TEXT_1287);
    stringBuffer.append(genOperation.getParameters(isImplementation, genClass));
    stringBuffer.append(TEXT_1288);
    stringBuffer.append(genOperation.getThrows(genClass));
    stringBuffer.append(TEXT_1289);
    if (genOperation.hasBody()) {
    stringBuffer.append(TEXT_1290);
    stringBuffer.append(genOperation.getBody(genModel.getIndentation(stringBuffer)));
    } else if (genOperation.isInvariant()) {GenClass opClass = genOperation.getGenClass(); String diagnostics = genOperation.getGenParameters().get(0).getName(); String context = genOperation.getGenParameters().get(1).getName();
    if (genOperation.hasInvariantExpression()) {
    stringBuffer.append(TEXT_1291);
    stringBuffer.append(opClass.getGenPackage().getImportedValidatorClassName());
    stringBuffer.append(TEXT_1292);
    stringBuffer.append(genClass.getQualifiedClassifierAccessor());
    stringBuffer.append(TEXT_1293);
    stringBuffer.append(diagnostics);
    stringBuffer.append(TEXT_1294);
    stringBuffer.append(context);
    stringBuffer.append(TEXT_1295);
    stringBuffer.append(genOperation.getValidationDelegate());
    stringBuffer.append(TEXT_1296);
    stringBuffer.append(genModel.getNonNLS());
    stringBuffer.append(TEXT_1297);
    stringBuffer.append(genOperation.getQualifiedOperationAccessor());
    stringBuffer.append(TEXT_1298);
    stringBuffer.append(CodeGenUtil.upperName(genClass.getUniqueName(genOperation), genModel.getLocale()));
    stringBuffer.append(TEXT_1299);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.util.Diagnostic"));
    stringBuffer.append(TEXT_1300);
    stringBuffer.append(opClass.getGenPackage().getImportedValidatorClassName());
    stringBuffer.append(TEXT_1301);
    stringBuffer.append(opClass.getGenPackage().getImportedValidatorClassName());
    stringBuffer.append(TEXT_1302);
    stringBuffer.append(opClass.getOperationID(genOperation));
    stringBuffer.append(TEXT_1303);
    } else {
    stringBuffer.append(TEXT_1304);
    stringBuffer.append(diagnostics);
    stringBuffer.append(TEXT_1305);
    stringBuffer.append(diagnostics);
    stringBuffer.append(TEXT_1306);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.util.BasicDiagnostic"));
    stringBuffer.append(TEXT_1307);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.util.Diagnostic"));
    stringBuffer.append(TEXT_1308);
    stringBuffer.append(opClass.getGenPackage().getImportedValidatorClassName());
    stringBuffer.append(TEXT_1309);
    stringBuffer.append(opClass.getGenPackage().getImportedValidatorClassName());
    stringBuffer.append(TEXT_1310);
    stringBuffer.append(opClass.getOperationID(genOperation));
    stringBuffer.append(TEXT_1311);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.plugin.EcorePlugin"));
    stringBuffer.append(TEXT_1312);
    stringBuffer.append(genOperation.getName());
    stringBuffer.append(TEXT_1313);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.EObjectValidator"));
    stringBuffer.append(TEXT_1314);
    stringBuffer.append(context);
    stringBuffer.append(TEXT_1315);
    stringBuffer.append(genModel.getNonNLS());
    stringBuffer.append(genModel.getNonNLS(2));
    stringBuffer.append(TEXT_1316);
    }
    } else if (genOperation.hasInvocationDelegate()) { int size = genOperation.getGenParameters().size();
    stringBuffer.append(TEXT_1317);
    if (genOperation.isVoid()) {
    stringBuffer.append(TEXT_1318);
    stringBuffer.append(CodeGenUtil.upperName(genClass.getUniqueName(genOperation), genModel.getLocale()));
    stringBuffer.append(TEXT_1319);
    if (size > 0) {
    stringBuffer.append(TEXT_1320);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.util.BasicEList"));
    stringBuffer.append(TEXT_1321);
    stringBuffer.append(size);
    stringBuffer.append(TEXT_1322);
    stringBuffer.append(genOperation.getParametersArray(genClass));
    stringBuffer.append(TEXT_1323);
    } else {
    stringBuffer.append(TEXT_1324);
    }
    stringBuffer.append(TEXT_1325);
    } else {
    stringBuffer.append(TEXT_1326);
    if (!isJDK50 && genOperation.isPrimitiveType()) {
    stringBuffer.append(TEXT_1327);
    }
    stringBuffer.append(TEXT_1328);
    stringBuffer.append(genOperation.getObjectType(genClass));
    stringBuffer.append(TEXT_1329);
    stringBuffer.append(CodeGenUtil.upperName(genClass.getUniqueName(genOperation), genModel.getLocale()));
    stringBuffer.append(TEXT_1330);
    if (size > 0) {
    stringBuffer.append(TEXT_1331);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.util.BasicEList"));
    stringBuffer.append(TEXT_1332);
    stringBuffer.append(size);
    stringBuffer.append(TEXT_1333);
    stringBuffer.append(genOperation.getParametersArray(genClass));
    stringBuffer.append(TEXT_1334);
    } else {
    stringBuffer.append(TEXT_1335);
    }
    stringBuffer.append(TEXT_1336);
    if (!isJDK50 && genOperation.isPrimitiveType()) {
    stringBuffer.append(TEXT_1337);
    stringBuffer.append(genOperation.getPrimitiveValueFunction());
    stringBuffer.append(TEXT_1338);
    }
    stringBuffer.append(TEXT_1339);
    }
    stringBuffer.append(TEXT_1340);
    stringBuffer.append(genModel.getImportedName(isGWT ? "org.eclipse.emf.common.util.InvocationTargetException" : "java.lang.reflect.InvocationTargetException"));
    stringBuffer.append(TEXT_1341);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.util.WrappedException"));
    stringBuffer.append(TEXT_1342);
    } else {
    stringBuffer.append(TEXT_1343);
    //Class/implementedGenOperation.todo.override.javajetinc
    }
    stringBuffer.append(TEXT_1344);
    }
    //Class/implementedGenOperation.override.javajetinc
    }//for
    if (isImplementation && !genModel.isReflectiveDelegation() && genClass.implementsAny(genClass.getEInverseAddGenFeatures())) {
    stringBuffer.append(TEXT_1345);
    if (genModel.useGenerics()) {
    for (GenFeature genFeature : genClass.getEInverseAddGenFeatures()) {
    if (genFeature.isUncheckedCast(genClass)) {
    stringBuffer.append(TEXT_1346);
    break; }
    }
    }
    if (genModel.useClassOverrideAnnotation()) {
    stringBuffer.append(TEXT_1347);
    }
    stringBuffer.append(TEXT_1348);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.notify.NotificationChain"));
    stringBuffer.append(TEXT_1349);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.InternalEObject"));
    stringBuffer.append(TEXT_1350);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.notify.NotificationChain"));
    stringBuffer.append(TEXT_1351);
    stringBuffer.append(negativeOffsetCorrection);
    stringBuffer.append(TEXT_1352);
    for (GenFeature genFeature : genClass.getEInverseAddGenFeatures()) {
    stringBuffer.append(TEXT_1353);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(TEXT_1354);
    if (genFeature.isListType()) { String cast = "("  + genModel.getImportedName("org.eclipse.emf.ecore.util.InternalEList") + (!genModel.useGenerics() ? ")" : "<" + genModel.getImportedName("org.eclipse.emf.ecore.InternalEObject") + ">)(" + genModel.getImportedName("org.eclipse.emf.ecore.util.InternalEList") + "<?>)");
    if (genFeature.isMapType() && genFeature.isEffectiveSuppressEMFTypes()) {
    stringBuffer.append(TEXT_1355);
    stringBuffer.append(cast);
    stringBuffer.append(TEXT_1356);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.util.EMap"));
    stringBuffer.append(TEXT_1357);
    stringBuffer.append(genFeature.getImportedMapTemplateArguments(genClass));
    stringBuffer.append(TEXT_1358);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_1359);
    } else {
    stringBuffer.append(TEXT_1360);
    stringBuffer.append(cast);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_1361);
    }
    } else if (genFeature.isContainer()) {
    stringBuffer.append(TEXT_1362);
    if (genFeature.isBasicSet()) {
    stringBuffer.append(TEXT_1363);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_1364);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_1365);
    } else {
    stringBuffer.append(TEXT_1366);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(positiveOffsetCorrection);
    stringBuffer.append(TEXT_1367);
    }
    } else {
    if (genClass.getImplementingGenModel(genFeature).isVirtualDelegation()) {
    stringBuffer.append(TEXT_1368);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_1369);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_1370);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_1371);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(positiveOffsetCorrection);
    stringBuffer.append(TEXT_1372);
    } else if (genFeature.isVolatile() || genClass.getImplementingGenModel(genFeature).isDynamicDelegation()) {
    stringBuffer.append(TEXT_1373);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_1374);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_1375);
    if (genFeature.isResolveProxies()) {
    stringBuffer.append(TEXT_1376);
    stringBuffer.append(genFeature.getAccessorName());
    } else {
    stringBuffer.append(genFeature.getGetAccessor());
    }
    stringBuffer.append(TEXT_1377);
    }
    stringBuffer.append(TEXT_1378);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_1379);
    if (genFeature.isEffectiveContains()) {
    stringBuffer.append(TEXT_1380);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.InternalEObject"));
    stringBuffer.append(TEXT_1381);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_1382);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(negativeOffsetCorrection);
    stringBuffer.append(TEXT_1383);
    } else { GenFeature reverseFeature = genFeature.getReverse(); GenClass targetClass = reverseFeature.getGenClass(); String reverseOffsetCorrection = targetClass.hasOffsetCorrection() ? " + " + genClass.getOffsetCorrectionField(genFeature) : "";
    stringBuffer.append(TEXT_1384);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.InternalEObject"));
    stringBuffer.append(TEXT_1385);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_1386);
    stringBuffer.append(targetClass.getQualifiedFeatureID(reverseFeature));
    stringBuffer.append(reverseOffsetCorrection);
    stringBuffer.append(TEXT_1387);
    stringBuffer.append(targetClass.getRawImportedInterfaceName());
    stringBuffer.append(TEXT_1388);
    }
    stringBuffer.append(TEXT_1389);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_1390);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_1391);
    }
    }
    stringBuffer.append(TEXT_1392);
    if (genModel.isMinimalReflectiveMethods()) {
    stringBuffer.append(TEXT_1393);
    } else {
    stringBuffer.append(TEXT_1394);
    }
    stringBuffer.append(TEXT_1395);
    }
    if (isImplementation && !genModel.isReflectiveDelegation() && genClass.implementsAny(genClass.getEInverseRemoveGenFeatures())) {
    stringBuffer.append(TEXT_1396);
    if (genModel.useClassOverrideAnnotation()) {
    stringBuffer.append(TEXT_1397);
    }
    stringBuffer.append(TEXT_1398);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.notify.NotificationChain"));
    stringBuffer.append(TEXT_1399);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.InternalEObject"));
    stringBuffer.append(TEXT_1400);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.notify.NotificationChain"));
    stringBuffer.append(TEXT_1401);
    stringBuffer.append(negativeOffsetCorrection);
    stringBuffer.append(TEXT_1402);
    for (GenFeature genFeature : genClass.getEInverseRemoveGenFeatures()) {
    stringBuffer.append(TEXT_1403);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(TEXT_1404);
    if (genFeature.isListType()) {
    if (genFeature.isMapType() && genFeature.isEffectiveSuppressEMFTypes()) {
    stringBuffer.append(TEXT_1405);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.InternalEList"));
    stringBuffer.append(singleWildcard);
    stringBuffer.append(TEXT_1406);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.util.EMap"));
    stringBuffer.append(TEXT_1407);
    stringBuffer.append(genFeature.getImportedMapTemplateArguments(genClass));
    stringBuffer.append(TEXT_1408);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_1409);
    } else if (genFeature.isWrappedFeatureMapType()) {
    stringBuffer.append(TEXT_1410);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.InternalEList"));
    stringBuffer.append(singleWildcard);
    stringBuffer.append(TEXT_1411);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.FeatureMap"));
    stringBuffer.append(TEXT_1412);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_1413);
    } else {
    stringBuffer.append(TEXT_1414);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.InternalEList"));
    stringBuffer.append(singleWildcard);
    stringBuffer.append(TEXT_1415);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_1416);
    }
    } else if (genFeature.isContainer() && !genFeature.isBasicSet()) {
    stringBuffer.append(TEXT_1417);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(positiveOffsetCorrection);
    stringBuffer.append(TEXT_1418);
    } else if (genFeature.isUnsettable()) {
    stringBuffer.append(TEXT_1419);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_1420);
    } else {
    stringBuffer.append(TEXT_1421);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_1422);
    }
    }
    stringBuffer.append(TEXT_1423);
    if (genModel.isMinimalReflectiveMethods()) {
    stringBuffer.append(TEXT_1424);
    } else {
    stringBuffer.append(TEXT_1425);
    }
    stringBuffer.append(TEXT_1426);
    }
    if (isImplementation && !genModel.isReflectiveDelegation() && genClass.implementsAny(genClass.getEBasicRemoveFromContainerGenFeatures())) {
    stringBuffer.append(TEXT_1427);
    if (genModel.useClassOverrideAnnotation()) {
    stringBuffer.append(TEXT_1428);
    }
    stringBuffer.append(TEXT_1429);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.notify.NotificationChain"));
    stringBuffer.append(TEXT_1430);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.notify.NotificationChain"));
    stringBuffer.append(TEXT_1431);
    stringBuffer.append(negativeOffsetCorrection);
    stringBuffer.append(TEXT_1432);
    for (GenFeature genFeature : genClass.getEBasicRemoveFromContainerGenFeatures()) {
    GenFeature reverseFeature = genFeature.getReverse(); GenClass targetClass = reverseFeature.getGenClass(); String reverseOffsetCorrection = targetClass.hasOffsetCorrection() ? " + " + genClass.getOffsetCorrectionField(genFeature) : "";
    stringBuffer.append(TEXT_1433);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(TEXT_1434);
    stringBuffer.append(targetClass.getQualifiedFeatureID(reverseFeature));
    stringBuffer.append(reverseOffsetCorrection);
    stringBuffer.append(TEXT_1435);
    stringBuffer.append(targetClass.getRawImportedInterfaceName());
    stringBuffer.append(TEXT_1436);
    }
    stringBuffer.append(TEXT_1437);
    if (genModel.isMinimalReflectiveMethods()) {
    stringBuffer.append(TEXT_1438);
    } else {
    stringBuffer.append(TEXT_1439);
    }
    stringBuffer.append(TEXT_1440);
    }
    if (isImplementation && !genModel.isReflectiveDelegation() && genClass.implementsAny(genClass.getEGetGenFeatures())) {
    stringBuffer.append(TEXT_1441);
    if (genModel.useClassOverrideAnnotation()) {
    stringBuffer.append(TEXT_1442);
    }
    stringBuffer.append(TEXT_1443);
    stringBuffer.append(negativeOffsetCorrection);
    stringBuffer.append(TEXT_1444);
    for (GenFeature genFeature : genClass.getEGetGenFeatures()) {
    stringBuffer.append(TEXT_1445);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(TEXT_1446);
    if (genFeature.isPrimitiveType()) {
    if (isJDK50) {
    stringBuffer.append(TEXT_1447);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_1448);
    } else if (genFeature.isBooleanType()) {
    stringBuffer.append(TEXT_1449);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_1450);
    } else {
    stringBuffer.append(TEXT_1451);
    stringBuffer.append(genFeature.getObjectType(genClass));
    stringBuffer.append(TEXT_1452);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_1453);
    }
    } else if (genFeature.isResolveProxies() && !genFeature.isListType()) {
    stringBuffer.append(TEXT_1454);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_1455);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_1456);
    } else if (genFeature.isMapType()) {
    if (genFeature.isEffectiveSuppressEMFTypes()) {
    stringBuffer.append(TEXT_1457);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.util.EMap"));
    stringBuffer.append(TEXT_1458);
    stringBuffer.append(genFeature.getImportedMapTemplateArguments(genClass));
    stringBuffer.append(TEXT_1459);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_1460);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_1461);
    } else {
    stringBuffer.append(TEXT_1462);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_1463);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_1464);
    }
    } else if (genFeature.isWrappedFeatureMapType()) {
    stringBuffer.append(TEXT_1465);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.FeatureMap"));
    stringBuffer.append(TEXT_1466);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_1467);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_1468);
    } else if (genFeature.isFeatureMapType()) {
    stringBuffer.append(TEXT_1469);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_1470);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.FeatureMap"));
    stringBuffer.append(TEXT_1471);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_1472);
    } else {
    stringBuffer.append(TEXT_1473);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_1474);
    }
    }
    stringBuffer.append(TEXT_1475);
    if (genModel.isMinimalReflectiveMethods()) {
    stringBuffer.append(TEXT_1476);
    } else {
    stringBuffer.append(TEXT_1477);
    }
    stringBuffer.append(TEXT_1478);
    }
    if (isImplementation && !genModel.isReflectiveDelegation() && genClass.implementsAny(genClass.getESetGenFeatures())) {
    stringBuffer.append(TEXT_1479);
    if (genModel.useGenerics()) {
    for (GenFeature genFeature : genClass.getESetGenFeatures()) {
    if (genFeature.isUncheckedCast(genClass) && !genFeature.isFeatureMapType() && !genFeature.isMapType()) {
    stringBuffer.append(TEXT_1480);
    break; }
    }
    }
    if (genModel.useClassOverrideAnnotation()) {
    stringBuffer.append(TEXT_1481);
    }
    stringBuffer.append(TEXT_1482);
    stringBuffer.append(negativeOffsetCorrection);
    stringBuffer.append(TEXT_1483);
    for (GenFeature genFeature : genClass.getESetGenFeatures()) {
    stringBuffer.append(TEXT_1484);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(TEXT_1485);
    if (genFeature.isListType()) {
    if (genFeature.isWrappedFeatureMapType()) {
    stringBuffer.append(TEXT_1486);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.FeatureMap"));
    stringBuffer.append(TEXT_1487);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.FeatureMap"));
    stringBuffer.append(TEXT_1488);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_1489);
    } else if (genFeature.isFeatureMapType()) {
    stringBuffer.append(TEXT_1490);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.FeatureMap"));
    stringBuffer.append(TEXT_1491);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_1492);
    } else if (genFeature.isMapType()) {
    if (genFeature.isEffectiveSuppressEMFTypes()) {
    stringBuffer.append(TEXT_1493);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.EStructuralFeature"));
    stringBuffer.append(TEXT_1494);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.util.EMap"));
    stringBuffer.append(TEXT_1495);
    stringBuffer.append(genFeature.getImportedMapTemplateArguments(genClass));
    stringBuffer.append(TEXT_1496);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_1497);
    } else {
    stringBuffer.append(TEXT_1498);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.EStructuralFeature"));
    stringBuffer.append(TEXT_1499);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_1500);
    }
    } else {
    stringBuffer.append(TEXT_1501);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_1502);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_1503);
    stringBuffer.append(genModel.getImportedName("java.util.Collection"));
    if (isJDK50) {
    stringBuffer.append(TEXT_1504);
    stringBuffer.append(genFeature.getListItemType(genClass));
    stringBuffer.append(TEXT_1505);
    }
    stringBuffer.append(TEXT_1506);
    }
    } else if (!isJDK50 && genFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_1507);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_1508);
    stringBuffer.append(genFeature.getObjectType(genClass));
    stringBuffer.append(TEXT_1509);
    stringBuffer.append(genFeature.getPrimitiveValueFunction());
    stringBuffer.append(TEXT_1510);
    } else {
    stringBuffer.append(TEXT_1511);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_1512);
    if (genFeature.getTypeGenDataType() == null || !genFeature.getTypeGenDataType().isObjectType() || !genFeature.getRawType().equals(genFeature.getType(genClass))) {
    stringBuffer.append(TEXT_1513);
    stringBuffer.append(genFeature.getObjectType(genClass));
    stringBuffer.append(TEXT_1514);
    }
    stringBuffer.append(TEXT_1515);
    }
    stringBuffer.append(TEXT_1516);
    }
    stringBuffer.append(TEXT_1517);
    if (genModel.isMinimalReflectiveMethods()) {
    stringBuffer.append(TEXT_1518);
    } else {
    stringBuffer.append(TEXT_1519);
    }
    stringBuffer.append(TEXT_1520);
    }
    if (isImplementation && !genModel.isReflectiveDelegation() && genClass.implementsAny(genClass.getEUnsetGenFeatures())) {
    stringBuffer.append(TEXT_1521);
    if (genModel.useClassOverrideAnnotation()) {
    stringBuffer.append(TEXT_1522);
    }
    stringBuffer.append(TEXT_1523);
    stringBuffer.append(negativeOffsetCorrection);
    stringBuffer.append(TEXT_1524);
    for (GenFeature genFeature : genClass.getEUnsetGenFeatures()) {
    stringBuffer.append(TEXT_1525);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(TEXT_1526);
    if (genFeature.isListType() && !genFeature.isUnsettable()) {
    if (genFeature.isWrappedFeatureMapType()) {
    stringBuffer.append(TEXT_1527);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.FeatureMap"));
    stringBuffer.append(TEXT_1528);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_1529);
    } else {
    stringBuffer.append(TEXT_1530);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_1531);
    }
    } else if (genFeature.isUnsettable()) {
    stringBuffer.append(TEXT_1532);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_1533);
    } else if (!genFeature.hasEDefault()) {
    stringBuffer.append(TEXT_1534);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_1535);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_1536);
    } else if (genFeature.hasSettingDelegate()) {
    stringBuffer.append(TEXT_1537);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_1538);
    } else {
    stringBuffer.append(TEXT_1539);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_1540);
    stringBuffer.append(genFeature.getEDefault());
    stringBuffer.append(TEXT_1541);
    }
    stringBuffer.append(TEXT_1542);
    }
    stringBuffer.append(TEXT_1543);
    if (genModel.isMinimalReflectiveMethods()) {
    stringBuffer.append(TEXT_1544);
    } else {
    stringBuffer.append(TEXT_1545);
    }
    stringBuffer.append(TEXT_1546);
    //Class/eUnset.override.javajetinc
    }
    if (isImplementation && !genModel.isReflectiveDelegation() && genClass.implementsAny(genClass.getEIsSetGenFeatures())) {
    stringBuffer.append(TEXT_1547);
    if (genModel.useGenerics()) {
    for (GenFeature genFeature : genClass.getEIsSetGenFeatures()) {
    if (genFeature.isListType() && !genFeature.isUnsettable() && !genFeature.isWrappedFeatureMapType() && !genClass.isField(genFeature) && genFeature.isField() && genClass.getImplementingGenModel(genFeature).isVirtualDelegation()) {
    stringBuffer.append(TEXT_1548);
    break; }
    }
    }
    if (genModel.useClassOverrideAnnotation()) {
    stringBuffer.append(TEXT_1549);
    }
    stringBuffer.append(TEXT_1550);
    stringBuffer.append(negativeOffsetCorrection);
    stringBuffer.append(TEXT_1551);
    for (GenFeature genFeature : genClass.getEIsSetGenFeatures()) { String safeNameAccessor = genFeature.getSafeName(); if ("featureID".equals(safeNameAccessor)) { safeNameAccessor = "this." + safeNameAccessor; }
    stringBuffer.append(TEXT_1552);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(TEXT_1553);
    if (genFeature.hasSettingDelegate()) {
    if (genFeature.isUnsettable()) {
    stringBuffer.append(TEXT_1554);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_1555);
    } else {
    stringBuffer.append(TEXT_1556);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_1557);
    }
    } else if (genFeature.isListType() && !genFeature.isUnsettable()) {
    if (genFeature.isWrappedFeatureMapType()) {
    if (genFeature.isVolatile()) {
    stringBuffer.append(TEXT_1558);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.FeatureMap"));
    stringBuffer.append(TEXT_1559);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_1560);
    } else {
    stringBuffer.append(TEXT_1561);
    stringBuffer.append(safeNameAccessor);
    stringBuffer.append(TEXT_1562);
    stringBuffer.append(safeNameAccessor);
    stringBuffer.append(TEXT_1563);
    }
    } else {
    if (genClass.isField(genFeature)) {
    stringBuffer.append(TEXT_1564);
    stringBuffer.append(safeNameAccessor);
    stringBuffer.append(TEXT_1565);
    stringBuffer.append(safeNameAccessor);
    stringBuffer.append(TEXT_1566);
    } else {
    if (genFeature.isField() && genClass.getImplementingGenModel(genFeature).isVirtualDelegation()) {
    stringBuffer.append(TEXT_1567);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_1568);
    stringBuffer.append(safeNameAccessor);
    stringBuffer.append(TEXT_1569);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_1570);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(positiveOffsetCorrection);
    stringBuffer.append(TEXT_1571);
    stringBuffer.append(safeNameAccessor);
    stringBuffer.append(TEXT_1572);
    stringBuffer.append(safeNameAccessor);
    stringBuffer.append(TEXT_1573);
    } else {
    stringBuffer.append(TEXT_1574);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_1575);
    }
    }
    }
    } else if (genFeature.isUnsettable()) {
    stringBuffer.append(TEXT_1576);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_1577);
    } else if (genFeature.isResolveProxies()) {
    if (genClass.isField(genFeature)) {
    stringBuffer.append(TEXT_1578);
    stringBuffer.append(safeNameAccessor);
    stringBuffer.append(TEXT_1579);
    } else {
    if (genFeature.isField() && genClass.getImplementingGenModel(genFeature).isVirtualDelegation()) {
    stringBuffer.append(TEXT_1580);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(positiveOffsetCorrection);
    stringBuffer.append(TEXT_1581);
    } else {
    stringBuffer.append(TEXT_1582);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_1583);
    }
    }
    } else if (!genFeature.hasEDefault()) {
    if (genClass.isField(genFeature)) {
    stringBuffer.append(TEXT_1584);
    stringBuffer.append(safeNameAccessor);
    stringBuffer.append(TEXT_1585);
    } else {
    if (genFeature.isField() && genClass.getImplementingGenModel(genFeature).isVirtualDelegation()) {
    stringBuffer.append(TEXT_1586);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(positiveOffsetCorrection);
    stringBuffer.append(TEXT_1587);
    } else {
    stringBuffer.append(TEXT_1588);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_1589);
    }
    }
    } else if (genFeature.isPrimitiveType() || genFeature.isEnumType()) {
    if (genClass.isField(genFeature)) {
    if (genClass.isFlag(genFeature)) {
    if (genFeature.isBooleanType()) {
    stringBuffer.append(TEXT_1590);
    stringBuffer.append(genClass.getFlagsField(genFeature));
    stringBuffer.append(TEXT_1591);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_1592);
    stringBuffer.append(genFeature.getEDefault());
    stringBuffer.append(TEXT_1593);
    } else {
    stringBuffer.append(TEXT_1594);
    stringBuffer.append(genClass.getFlagsField(genFeature));
    stringBuffer.append(TEXT_1595);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_1596);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_1597);
    }
    } else {
    stringBuffer.append(TEXT_1598);
    stringBuffer.append(safeNameAccessor);
    stringBuffer.append(TEXT_1599);
    stringBuffer.append(genFeature.getEDefault());
    stringBuffer.append(TEXT_1600);
    }
    } else {
    if (genFeature.isEnumType() && genFeature.isField() && genClass.getImplementingGenModel(genFeature).isVirtualDelegation()) {
    stringBuffer.append(TEXT_1601);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(positiveOffsetCorrection);
    stringBuffer.append(TEXT_1602);
    stringBuffer.append(genFeature.getEDefault());
    stringBuffer.append(TEXT_1603);
    stringBuffer.append(genFeature.getEDefault());
    stringBuffer.append(TEXT_1604);
    } else {
    stringBuffer.append(TEXT_1605);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_1606);
    stringBuffer.append(genFeature.getEDefault());
    stringBuffer.append(TEXT_1607);
    }
    }
    } else {//datatype
    if (genClass.isField(genFeature)) {
    stringBuffer.append(TEXT_1608);
    stringBuffer.append(genFeature.getEDefault());
    stringBuffer.append(TEXT_1609);
    stringBuffer.append(safeNameAccessor);
    stringBuffer.append(TEXT_1610);
    stringBuffer.append(genFeature.getEDefault());
    stringBuffer.append(TEXT_1611);
    stringBuffer.append(safeNameAccessor);
    stringBuffer.append(TEXT_1612);
    } else {
    if (genFeature.isField() && genClass.getImplementingGenModel(genFeature).isVirtualDelegation()) {
    stringBuffer.append(TEXT_1613);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_1614);
    stringBuffer.append(safeNameAccessor);
    stringBuffer.append(TEXT_1615);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_1616);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(positiveOffsetCorrection);
    stringBuffer.append(TEXT_1617);
    stringBuffer.append(genFeature.getEDefault());
    stringBuffer.append(TEXT_1618);
    stringBuffer.append(genFeature.getEDefault());
    stringBuffer.append(TEXT_1619);
    stringBuffer.append(safeNameAccessor);
    stringBuffer.append(TEXT_1620);
    stringBuffer.append(genFeature.getEDefault());
    stringBuffer.append(TEXT_1621);
    stringBuffer.append(safeNameAccessor);
    stringBuffer.append(TEXT_1622);
    } else {
    stringBuffer.append(TEXT_1623);
    stringBuffer.append(genFeature.getEDefault());
    stringBuffer.append(TEXT_1624);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_1625);
    stringBuffer.append(genFeature.getEDefault());
    stringBuffer.append(TEXT_1626);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_1627);
    }
    }
    }
    }
    stringBuffer.append(TEXT_1628);
    if (genModel.isMinimalReflectiveMethods()) {
    stringBuffer.append(TEXT_1629);
    } else {
    stringBuffer.append(TEXT_1630);
    }
    stringBuffer.append(TEXT_1631);
    //Class/eIsSet.override.javajetinc
    }
    if (isImplementation && (!genClass.getMixinGenFeatures().isEmpty() || genClass.hasOffsetCorrection() && !genClass.getGenFeatures().isEmpty())) {
    if (!genClass.getMixinGenFeatures().isEmpty()) {
    stringBuffer.append(TEXT_1632);
    if (genModel.useClassOverrideAnnotation()) {
    stringBuffer.append(TEXT_1633);
    }
    stringBuffer.append(TEXT_1634);
    stringBuffer.append(singleWildcard);
    stringBuffer.append(TEXT_1635);
    for (GenClass mixinGenClass : genClass.getMixinGenClasses()) {
    stringBuffer.append(TEXT_1636);
    stringBuffer.append(mixinGenClass.getRawImportedInterfaceName());
    stringBuffer.append(TEXT_1637);
    stringBuffer.append(negativeOffsetCorrection);
    stringBuffer.append(TEXT_1638);
    for (GenFeature genFeature : mixinGenClass.getGenFeatures()) {
    stringBuffer.append(TEXT_1639);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(TEXT_1640);
    stringBuffer.append(mixinGenClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(TEXT_1641);
    }
    stringBuffer.append(TEXT_1642);
    }
    stringBuffer.append(TEXT_1643);
    }
    stringBuffer.append(TEXT_1644);
    if (genModel.useClassOverrideAnnotation()) {
    stringBuffer.append(TEXT_1645);
    }
    stringBuffer.append(TEXT_1646);
    stringBuffer.append(singleWildcard);
    stringBuffer.append(TEXT_1647);
    for (GenClass mixinGenClass : genClass.getMixinGenClasses()) {
    stringBuffer.append(TEXT_1648);
    stringBuffer.append(mixinGenClass.getRawImportedInterfaceName());
    stringBuffer.append(TEXT_1649);
    for (GenFeature genFeature : mixinGenClass.getGenFeatures()) {
    stringBuffer.append(TEXT_1650);
    stringBuffer.append(mixinGenClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(TEXT_1651);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(positiveOffsetCorrection);
    stringBuffer.append(TEXT_1652);
    }
    stringBuffer.append(TEXT_1653);
    }
    if (genClass.hasOffsetCorrection() && !genClass.getGenFeatures().isEmpty()) {
    stringBuffer.append(TEXT_1654);
    stringBuffer.append(genClass.getRawImportedInterfaceName());
    stringBuffer.append(TEXT_1655);
    stringBuffer.append(negativeOffsetCorrection);
    stringBuffer.append(TEXT_1656);
    for (GenFeature genFeature : genClass.getGenFeatures()) {
    stringBuffer.append(TEXT_1657);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(TEXT_1658);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(positiveOffsetCorrection);
    stringBuffer.append(TEXT_1659);
    }
    stringBuffer.append(TEXT_1660);
    }
    stringBuffer.append(TEXT_1661);
    }
    if (genModel.isOperationReflection() && isImplementation && (!genClass.getMixinGenOperations().isEmpty() || !genClass.getOverrideGenOperations(genClass.getExtendedGenOperations(), genClass.getImplementedGenOperations()).isEmpty() || genClass.hasOffsetCorrection() && !genClass.getGenOperations().isEmpty())) {
    stringBuffer.append(TEXT_1662);
    if (genModel.useClassOverrideAnnotation()) {
    stringBuffer.append(TEXT_1663);
    }
    stringBuffer.append(TEXT_1664);
    stringBuffer.append(singleWildcard);
    stringBuffer.append(TEXT_1665);
    for (GenClass extendedGenClass : genClass.getExtendedGenClasses()) { List<GenOperation> extendedImplementedGenOperations = extendedGenClass.getImplementedGenOperations(); List<GenOperation> implementedGenOperations = genClass.getImplementedGenOperations();
    if (!genClass.getOverrideGenOperations(extendedImplementedGenOperations, implementedGenOperations).isEmpty()) {
    stringBuffer.append(TEXT_1666);
    stringBuffer.append(extendedGenClass.getRawImportedInterfaceName());
    stringBuffer.append(TEXT_1667);
    for (GenOperation genOperation : extendedImplementedGenOperations) { GenOperation overrideGenOperation = genClass.getOverrideGenOperation(genOperation);
    if (implementedGenOperations.contains(overrideGenOperation)) {
    stringBuffer.append(TEXT_1668);
    stringBuffer.append(extendedGenClass.getQualifiedOperationID(genOperation));
    stringBuffer.append(TEXT_1669);
    stringBuffer.append(genClass.getQualifiedOperationID(overrideGenOperation));
    stringBuffer.append(positiveOperationOffsetCorrection);
    stringBuffer.append(TEXT_1670);
    }
    }
    stringBuffer.append(TEXT_1671);
    }
    }
    for (GenClass mixinGenClass : genClass.getMixinGenClasses()) {
    stringBuffer.append(TEXT_1672);
    stringBuffer.append(mixinGenClass.getRawImportedInterfaceName());
    stringBuffer.append(TEXT_1673);
    for (GenOperation genOperation : mixinGenClass.getGenOperations()) { GenOperation overrideGenOperation = genClass.getOverrideGenOperation(genOperation);
    stringBuffer.append(TEXT_1674);
    stringBuffer.append(mixinGenClass.getQualifiedOperationID(genOperation));
    stringBuffer.append(TEXT_1675);
    stringBuffer.append(genClass.getQualifiedOperationID(overrideGenOperation != null ? overrideGenOperation : genOperation));
    stringBuffer.append(positiveOperationOffsetCorrection);
    stringBuffer.append(TEXT_1676);
    }
    stringBuffer.append(TEXT_1677);
    }
    if (genClass.hasOffsetCorrection() && !genClass.getGenOperations().isEmpty()) {
    stringBuffer.append(TEXT_1678);
    stringBuffer.append(genClass.getRawImportedInterfaceName());
    stringBuffer.append(TEXT_1679);
    stringBuffer.append(negativeOperationOffsetCorrection);
    stringBuffer.append(TEXT_1680);
    for (GenOperation genOperation : genClass.getGenOperations()) {
    stringBuffer.append(TEXT_1681);
    stringBuffer.append(genClass.getQualifiedOperationID(genOperation));
    stringBuffer.append(TEXT_1682);
    stringBuffer.append(genClass.getQualifiedOperationID(genOperation));
    stringBuffer.append(positiveOperationOffsetCorrection);
    stringBuffer.append(TEXT_1683);
    }
    stringBuffer.append(TEXT_1684);
    }
    stringBuffer.append(TEXT_1685);
    }
    if (isImplementation && genModel.isVirtualDelegation()) { String eVirtualValuesField = genClass.getEVirtualValuesField();
    if (eVirtualValuesField != null) {
    stringBuffer.append(TEXT_1686);
    if (genModel.useClassOverrideAnnotation()) {
    stringBuffer.append(TEXT_1687);
    }
    stringBuffer.append(TEXT_1688);
    stringBuffer.append(eVirtualValuesField);
    stringBuffer.append(TEXT_1689);
    if (genModel.useClassOverrideAnnotation()) {
    stringBuffer.append(TEXT_1690);
    }
    stringBuffer.append(TEXT_1691);
    stringBuffer.append(eVirtualValuesField);
    stringBuffer.append(TEXT_1692);
    }
    { List<String> eVirtualIndexBitFields = genClass.getEVirtualIndexBitFields(new ArrayList<String>());
    if (!eVirtualIndexBitFields.isEmpty()) { List<String> allEVirtualIndexBitFields = genClass.getAllEVirtualIndexBitFields(new ArrayList<String>());
    stringBuffer.append(TEXT_1693);
    if (genModel.useClassOverrideAnnotation()) {
    stringBuffer.append(TEXT_1694);
    }
    stringBuffer.append(TEXT_1695);
    for (int i = 0; i < allEVirtualIndexBitFields.size(); i++) {
    stringBuffer.append(TEXT_1696);
    stringBuffer.append(i);
    stringBuffer.append(TEXT_1697);
    stringBuffer.append(allEVirtualIndexBitFields.get(i));
    stringBuffer.append(TEXT_1698);
    }
    stringBuffer.append(TEXT_1699);
    if (genModel.useClassOverrideAnnotation()) {
    stringBuffer.append(TEXT_1700);
    }
    stringBuffer.append(TEXT_1701);
    for (int i = 0; i < allEVirtualIndexBitFields.size(); i++) {
    stringBuffer.append(TEXT_1702);
    stringBuffer.append(i);
    stringBuffer.append(TEXT_1703);
    stringBuffer.append(allEVirtualIndexBitFields.get(i));
    stringBuffer.append(TEXT_1704);
    }
    stringBuffer.append(TEXT_1705);
    }
    }
    }
    if (genModel.isOperationReflection() && isImplementation && !genClass.getImplementedGenOperations().isEmpty()) {
    stringBuffer.append(TEXT_1706);
    if (genModel.useClassOverrideAnnotation()) {
    stringBuffer.append(TEXT_1707);
    }
    if (genModel.useGenerics()) {
    boolean isUnchecked = false; boolean isRaw = false; LOOP: for (GenOperation genOperation : (genModel.isMinimalReflectiveMethods() ? genClass.getImplementedGenOperations() : genClass.getAllGenOperations())) { for (GenParameter genParameter : genOperation.getGenParameters()) { if (genParameter.isUncheckedCast()) { if (genParameter.getTypeGenDataType() == null || !genParameter.getTypeGenDataType().isObjectType()) { isUnchecked = true; } if (genParameter.usesOperationTypeParameters() && !genParameter.getEcoreParameter().getEGenericType().getETypeArguments().isEmpty()) { isRaw = true; break LOOP; }}}}
    if (isUnchecked) {
    stringBuffer.append(TEXT_1708);
    if (!isRaw) {
    stringBuffer.append(TEXT_1709);
    } else {
    stringBuffer.append(TEXT_1710);
    }
    stringBuffer.append(TEXT_1711);
    }
    }
    stringBuffer.append(TEXT_1712);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.util.EList"));
    stringBuffer.append(singleWildcard);
    stringBuffer.append(TEXT_1713);
    stringBuffer.append(genModel.getImportedName(isGWT ? "org.eclipse.emf.common.util.InvocationTargetException" : "java.lang.reflect.InvocationTargetException"));
    stringBuffer.append(TEXT_1714);
    stringBuffer.append(negativeOperationOffsetCorrection);
    stringBuffer.append(TEXT_1715);
    for (GenOperation genOperation : (genModel.isMinimalReflectiveMethods() ? genClass.getImplementedGenOperations() : genClass.getAllGenOperations())) { List<GenParameter> genParameters = genOperation.getGenParameters(); int size = genParameters.size();  boolean hasCheckedException = genOperation.hasCheckedException(); String indent = hasCheckedException ? "\t" : "";
    stringBuffer.append(TEXT_1716);
    stringBuffer.append(genClass.getQualifiedOperationID(genOperation));
    stringBuffer.append(TEXT_1717);
    if (hasCheckedException) {
    stringBuffer.append(TEXT_1718);
    /*}*/}
    if (genOperation.isVoid()) {
    stringBuffer.append(TEXT_1719);
    stringBuffer.append(indent);
    stringBuffer.append(genOperation.getName());
    stringBuffer.append(TEXT_1720);
    for (int i = 0; i < size; i++) { GenParameter genParameter = genParameters.get(i);
    if (!isJDK50 && genParameter.isPrimitiveType()) {
    stringBuffer.append(TEXT_1721);
    }
    if (genParameter.getTypeGenDataType() == null || !genParameter.getTypeGenDataType().isObjectType() || !genParameter.usesOperationTypeParameters() && !genParameter.getRawType().equals(genParameter.getType(genClass))) {
    stringBuffer.append(TEXT_1722);
    stringBuffer.append(genParameter.usesOperationTypeParameters() ? genParameter.getRawImportedType() : genParameter.getObjectType(genClass));
    stringBuffer.append(TEXT_1723);
    }
    stringBuffer.append(TEXT_1724);
    stringBuffer.append(i);
    stringBuffer.append(TEXT_1725);
    if (!isJDK50 && genParameter.isPrimitiveType()) {
    stringBuffer.append(TEXT_1726);
    stringBuffer.append(genParameter.getPrimitiveValueFunction());
    stringBuffer.append(TEXT_1727);
    }
    if (i < (size - 1)) {
    stringBuffer.append(TEXT_1728);
    }
    }
    stringBuffer.append(TEXT_1729);
    stringBuffer.append(indent);
    stringBuffer.append(TEXT_1730);
    } else {
    stringBuffer.append(TEXT_1731);
    stringBuffer.append(indent);
    stringBuffer.append(TEXT_1732);
    if (!isJDK50 && genOperation.isPrimitiveType()) {
    stringBuffer.append(TEXT_1733);
    stringBuffer.append(genOperation.getObjectType(genClass));
    stringBuffer.append(TEXT_1734);
    }
    stringBuffer.append(genOperation.getName());
    stringBuffer.append(TEXT_1735);
    for (int i = 0; i < size; i++) { GenParameter genParameter = genParameters.get(i);
    if (!isJDK50 && genParameter.isPrimitiveType()) {
    stringBuffer.append(TEXT_1736);
    }
    if (genParameter.getTypeGenDataType() == null || !genParameter.getTypeGenDataType().isObjectType() || !genParameter.usesOperationTypeParameters() && !genParameter.getRawType().equals(genParameter.getType(genClass))) {
    stringBuffer.append(TEXT_1737);
    stringBuffer.append(genParameter.usesOperationTypeParameters() ? genParameter.getRawImportedType() : genParameter.getObjectType(genClass));
    stringBuffer.append(TEXT_1738);
    }
    stringBuffer.append(TEXT_1739);
    stringBuffer.append(i);
    stringBuffer.append(TEXT_1740);
    if (!isJDK50 && genParameter.isPrimitiveType()) {
    stringBuffer.append(TEXT_1741);
    stringBuffer.append(genParameter.getPrimitiveValueFunction());
    stringBuffer.append(TEXT_1742);
    }
    if (i < (size - 1)) {
    stringBuffer.append(TEXT_1743);
    }
    }
    stringBuffer.append(TEXT_1744);
    if (!isJDK50 && genOperation.isPrimitiveType()) {
    stringBuffer.append(TEXT_1745);
    }
    stringBuffer.append(TEXT_1746);
    }
    if (hasCheckedException) {/*{*/
    stringBuffer.append(TEXT_1747);
    stringBuffer.append(genModel.getImportedName("java.lang.Throwable"));
    stringBuffer.append(TEXT_1748);
    stringBuffer.append(genModel.getImportedName(isGWT ? "org.eclipse.emf.common.util.InvocationTargetException" : "java.lang.reflect.InvocationTargetException"));
    stringBuffer.append(TEXT_1749);
    }
    }
    stringBuffer.append(TEXT_1750);
    if (genModel.isMinimalReflectiveMethods()) {
    stringBuffer.append(TEXT_1751);
    } else {
    stringBuffer.append(TEXT_1752);
    }
    stringBuffer.append(TEXT_1753);
    }
    if (!genClass.hasImplementedToStringGenOperation() && isImplementation && !genModel.isReflectiveDelegation() && !genModel.isDynamicDelegation() && !genClass.getToStringGenFeatures().isEmpty()) {
    stringBuffer.append(TEXT_1754);
    if (genModel.useClassOverrideAnnotation()) {
    stringBuffer.append(TEXT_1755);
    }
    stringBuffer.append(TEXT_1756);
    { boolean first = true;
    for (GenFeature genFeature : genClass.getToStringGenFeatures()) {
    if (first) { first = false;
    stringBuffer.append(TEXT_1757);
    stringBuffer.append(genFeature.getName());
    stringBuffer.append(TEXT_1758);
    stringBuffer.append(genModel.getNonNLS());
    } else {
    stringBuffer.append(TEXT_1759);
    stringBuffer.append(genFeature.getName());
    stringBuffer.append(TEXT_1760);
    stringBuffer.append(genModel.getNonNLS());
    }
    if (genFeature.isUnsettable() && !genFeature.isListType()) {
    if (genModel.isVirtualDelegation() && !genFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_1761);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(positiveOffsetCorrection);
    stringBuffer.append(TEXT_1762);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(positiveOffsetCorrection);
    stringBuffer.append(TEXT_1763);
    stringBuffer.append(genModel.getNonNLS());
    } else {
    if (genClass.isFlag(genFeature)) {
    if (genFeature.isBooleanType()) {
    stringBuffer.append(TEXT_1764);
    if (genClass.isESetFlag(genFeature)) {
    stringBuffer.append(TEXT_1765);
    stringBuffer.append(genClass.getESetFlagsField(genFeature));
    stringBuffer.append(TEXT_1766);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_1767);
    } else {
    stringBuffer.append(genFeature.getUncapName());
    stringBuffer.append(TEXT_1768);
    }
    stringBuffer.append(TEXT_1769);
    stringBuffer.append(genClass.getFlagsField(genFeature));
    stringBuffer.append(TEXT_1770);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_1771);
    stringBuffer.append(genModel.getNonNLS());
    } else {
    stringBuffer.append(TEXT_1772);
    if (genClass.isESetFlag(genFeature)) {
    stringBuffer.append(TEXT_1773);
    stringBuffer.append(genClass.getESetFlagsField(genFeature));
    stringBuffer.append(TEXT_1774);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_1775);
    } else {
    stringBuffer.append(genFeature.getUncapName());
    stringBuffer.append(TEXT_1776);
    }
    stringBuffer.append(TEXT_1777);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_1778);
    stringBuffer.append(genClass.getFlagsField(genFeature));
    stringBuffer.append(TEXT_1779);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_1780);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_1781);
    stringBuffer.append(genModel.getNonNLS());
    }
    } else {
    stringBuffer.append(TEXT_1782);
    if (genClass.isESetFlag(genFeature)) {
    stringBuffer.append(TEXT_1783);
    stringBuffer.append(genClass.getESetFlagsField(genFeature));
    stringBuffer.append(TEXT_1784);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_1785);
    } else {
    stringBuffer.append(genFeature.getUncapName());
    stringBuffer.append(TEXT_1786);
    }
    stringBuffer.append(TEXT_1787);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_1788);
    stringBuffer.append(genModel.getNonNLS());
    }
    }
    } else {
    if (genModel.isVirtualDelegation() && !genFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_1789);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(positiveOffsetCorrection);
    if (!genFeature.isListType() && !genFeature.isReferenceType()){
    stringBuffer.append(TEXT_1790);
    stringBuffer.append(genFeature.getEDefault());
    }
    stringBuffer.append(TEXT_1791);
    } else {
    if (genClass.isFlag(genFeature)) {
    if (genFeature.isBooleanType()) {
    stringBuffer.append(TEXT_1792);
    stringBuffer.append(genClass.getFlagsField(genFeature));
    stringBuffer.append(TEXT_1793);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_1794);
    } else {
    stringBuffer.append(TEXT_1795);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_1796);
    stringBuffer.append(genClass.getFlagsField(genFeature));
    stringBuffer.append(TEXT_1797);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_1798);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_1799);
    }
    } else {
    stringBuffer.append(TEXT_1800);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_1801);
    }
    }
    }
    }
    }
    stringBuffer.append(TEXT_1802);
    }
    if (isImplementation && genClass.isMapEntry()) { GenFeature keyFeature = genClass.getMapEntryKeyFeature(); GenFeature valueFeature = genClass.getMapEntryValueFeature();
    String objectType = genModel.getImportedName("java.lang.Object");
    String keyType = isJDK50 ? keyFeature.getObjectType(genClass) : objectType;
    String valueType = isJDK50 ? valueFeature.getObjectType(genClass) : objectType;
    String eMapType = genModel.getImportedName("org.eclipse.emf.common.util.EMap") + (isJDK50 ? "<" + keyType + ", " + valueType + ">" : "");
    stringBuffer.append(TEXT_1803);
    if (isGWT) {
    stringBuffer.append(TEXT_1804);
    stringBuffer.append(genModel.getImportedName("com.google.gwt.user.client.rpc.GwtTransient"));
    }
    stringBuffer.append(TEXT_1805);
    stringBuffer.append(objectType);
    stringBuffer.append(TEXT_1806);
    stringBuffer.append(keyType);
    stringBuffer.append(TEXT_1807);
    if (!isJDK50 && keyFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_1808);
    stringBuffer.append(keyFeature.getObjectType(genClass));
    stringBuffer.append(TEXT_1809);
    } else {
    stringBuffer.append(TEXT_1810);
    }
    stringBuffer.append(TEXT_1811);
    stringBuffer.append(keyType);
    stringBuffer.append(TEXT_1812);
    if (keyFeature.isListType()) {
    stringBuffer.append(TEXT_1813);
    if (!genModel.useGenerics()) {
    stringBuffer.append(TEXT_1814);
    stringBuffer.append(genModel.getImportedName("java.util.Collection"));
    stringBuffer.append(TEXT_1815);
    }
    stringBuffer.append(TEXT_1816);
    } else if (isJDK50) {
    stringBuffer.append(TEXT_1817);
    } else if (keyFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_1818);
    stringBuffer.append(keyFeature.getObjectType(genClass));
    stringBuffer.append(TEXT_1819);
    stringBuffer.append(keyFeature.getPrimitiveValueFunction());
    stringBuffer.append(TEXT_1820);
    } else {
    stringBuffer.append(TEXT_1821);
    stringBuffer.append(keyFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_1822);
    }
    stringBuffer.append(TEXT_1823);
    stringBuffer.append(valueType);
    stringBuffer.append(TEXT_1824);
    if (!isJDK50 && valueFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_1825);
    stringBuffer.append(valueFeature.getObjectType(genClass));
    stringBuffer.append(TEXT_1826);
    } else {
    stringBuffer.append(TEXT_1827);
    }
    stringBuffer.append(TEXT_1828);
    stringBuffer.append(valueType);
    stringBuffer.append(TEXT_1829);
    stringBuffer.append(valueType);
    stringBuffer.append(TEXT_1830);
    stringBuffer.append(valueType);
    stringBuffer.append(TEXT_1831);
    if (valueFeature.isListType()) {
    stringBuffer.append(TEXT_1832);
    if (!genModel.useGenerics()) {
    stringBuffer.append(TEXT_1833);
    stringBuffer.append(genModel.getImportedName("java.util.Collection"));
    stringBuffer.append(TEXT_1834);
    }
    stringBuffer.append(TEXT_1835);
    } else if (isJDK50) {
    stringBuffer.append(TEXT_1836);
    } else if (valueFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_1837);
    stringBuffer.append(valueFeature.getObjectType(genClass));
    stringBuffer.append(TEXT_1838);
    stringBuffer.append(valueFeature.getPrimitiveValueFunction());
    stringBuffer.append(TEXT_1839);
    } else {
    stringBuffer.append(TEXT_1840);
    stringBuffer.append(valueFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_1841);
    }
    stringBuffer.append(TEXT_1842);
    if (genModel.useGenerics()) {
    stringBuffer.append(TEXT_1843);
    }
    stringBuffer.append(TEXT_1844);
    stringBuffer.append(eMapType);
    stringBuffer.append(TEXT_1845);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.EObject"));
    stringBuffer.append(TEXT_1846);
    stringBuffer.append(eMapType);
    stringBuffer.append(TEXT_1847);
    }
    stringBuffer.append(TEXT_1848);
    stringBuffer.append(isInterface ? " " + genClass.getInterfaceName() : genClass.getClassName());
    // TODO fix the space above
    genModel.emitSortedImports();
    stringBuffer.append(TEXT_1849);
    return stringBuffer.toString();
  }
}
