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
  protected final String TEXT_213 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */";
  protected final String TEXT_214 = NL + "\t@Override";
  protected final String TEXT_215 = NL + "\tprotected int eStaticFeatureCount()" + NL + "\t{" + NL + "\t\treturn ";
  protected final String TEXT_216 = ";" + NL + "\t}" + NL;
  protected final String TEXT_217 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * This is specialized for the more specific element type known in this context." + NL + "\t * @generated" + NL + "\t */";
  protected final String TEXT_218 = NL + "\t@SuppressWarnings(\"unchecked\")";
  protected final String TEXT_219 = NL + "\t@Override";
  protected final String TEXT_220 = NL + "\tpublic ";
  protected final String TEXT_221 = "[] ";
  protected final String TEXT_222 = "()" + NL + "\t{";
  protected final String TEXT_223 = NL + "\t\t";
  protected final String TEXT_224 = " list = (";
  protected final String TEXT_225 = ")";
  protected final String TEXT_226 = "();" + NL + "\t\tif (list.isEmpty()) return ";
  protected final String TEXT_227 = "_EEMPTY_ARRAY;";
  protected final String TEXT_228 = NL + "\t\tif (";
  protected final String TEXT_229 = " == null || ";
  protected final String TEXT_230 = ".isEmpty()) return ";
  protected final String TEXT_231 = "_EEMPTY_ARRAY;" + NL + "\t\t";
  protected final String TEXT_232 = " list = (";
  protected final String TEXT_233 = ")";
  protected final String TEXT_234 = ";";
  protected final String TEXT_235 = NL + "\t\tlist.shrink();" + NL + "\t\treturn (";
  protected final String TEXT_236 = "[])list.data();" + NL + "\t}" + NL;
  protected final String TEXT_237 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * This is specialized for the more specific element type known in this context." + NL + "\t * @generated" + NL + "\t */";
  protected final String TEXT_238 = NL + "\t@SuppressWarnings(\"unchecked\")";
  protected final String TEXT_239 = NL + "\t@Override";
  protected final String TEXT_240 = NL + "\tpublic ";
  protected final String TEXT_241 = " ";
  protected final String TEXT_242 = "_";
  protected final String TEXT_243 = "()" + NL + "\t{";
  protected final String TEXT_244 = NL + "\t\t";
  protected final String TEXT_245 = " ";
  protected final String TEXT_246 = " = (";
  protected final String TEXT_247 = ")eVirtualGet(";
  protected final String TEXT_248 = ");";
  protected final String TEXT_249 = NL + "\t\tif (";
  protected final String TEXT_250 = " == null)" + NL + "\t\t{";
  protected final String TEXT_251 = NL + "\t\t\teVirtualSet(";
  protected final String TEXT_252 = ", ";
  protected final String TEXT_253 = " = new ";
  protected final String TEXT_254 = ");";
  protected final String TEXT_255 = NL + "\t\t\t";
  protected final String TEXT_256 = " = new ";
  protected final String TEXT_257 = ";";
  protected final String TEXT_258 = NL + "\t\t}" + NL + "\t\treturn ";
  protected final String TEXT_259 = ";" + NL + "\t}" + NL;
  protected final String TEXT_260 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * This is specialized for the more specific type known in this context." + NL + "\t * @generated" + NL + "\t */";
  protected final String TEXT_261 = NL + "\t@Override";
  protected final String TEXT_262 = NL + "\tpublic ";
  protected final String TEXT_263 = " basicSet";
  protected final String TEXT_264 = "(";
  protected final String TEXT_265 = " new";
  protected final String TEXT_266 = ", ";
  protected final String TEXT_267 = " msgs)" + NL + "\t{" + NL + "\t\treturn super.basicSet";
  protected final String TEXT_268 = "(new";
  protected final String TEXT_269 = ", msgs);" + NL + "\t}" + NL;
  protected final String TEXT_270 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * This is specialized for the more specific type known in this context." + NL + "\t * @generated" + NL + "\t */";
  protected final String TEXT_271 = NL + "\t@Override";
  protected final String TEXT_272 = NL + "\tpublic void set";
  protected final String TEXT_273 = "_";
  protected final String TEXT_274 = "(";
  protected final String TEXT_275 = " ";
  protected final String TEXT_276 = ")" + NL + "\t{" + NL + "\t\tsuper.set";
  protected final String TEXT_277 = "_";
  protected final String TEXT_278 = "(";
  protected final String TEXT_279 = ");" + NL + "\t}" + NL;
  protected final String TEXT_280 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */";
  protected final String TEXT_281 = NL + "\t";
  protected final String TEXT_282 = "[] ";
  protected final String TEXT_283 = "();" + NL;
  protected final String TEXT_284 = NL + "\t@SuppressWarnings(\"unchecked\")";
  protected final String TEXT_285 = NL + "\tpublic ";
  protected final String TEXT_286 = "[] ";
  protected final String TEXT_287 = "()" + NL + "\t{";
  protected final String TEXT_288 = NL + "\t\t";
  protected final String TEXT_289 = " list = (";
  protected final String TEXT_290 = ")";
  protected final String TEXT_291 = "();" + NL + "\t\tif (list.isEmpty()) return ";
  protected final String TEXT_292 = "_EEMPTY_ARRAY;";
  protected final String TEXT_293 = NL + "\t\tif (";
  protected final String TEXT_294 = " == null || ";
  protected final String TEXT_295 = ".isEmpty()) return ";
  protected final String TEXT_296 = "_EEMPTY_ARRAY;" + NL + "\t\t";
  protected final String TEXT_297 = " list = (";
  protected final String TEXT_298 = ")";
  protected final String TEXT_299 = ";";
  protected final String TEXT_300 = NL + "\t\tlist.shrink();" + NL + "\t\treturn (";
  protected final String TEXT_301 = "[])list.data();" + NL + "\t}" + NL;
  protected final String TEXT_302 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */";
  protected final String TEXT_303 = NL + "\t";
  protected final String TEXT_304 = " get";
  protected final String TEXT_305 = "(int index);" + NL;
  protected final String TEXT_306 = NL + "\tpublic ";
  protected final String TEXT_307 = " get";
  protected final String TEXT_308 = "(int index)" + NL + "\t{" + NL + "\t\treturn ";
  protected final String TEXT_309 = "(";
  protected final String TEXT_310 = ")";
  protected final String TEXT_311 = "().get(index);" + NL + "\t}" + NL;
  protected final String TEXT_312 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */";
  protected final String TEXT_313 = NL + "\tint get";
  protected final String TEXT_314 = "Length();" + NL;
  protected final String TEXT_315 = NL + "\tpublic int get";
  protected final String TEXT_316 = "Length()" + NL + "\t{";
  protected final String TEXT_317 = NL + "\t\treturn ";
  protected final String TEXT_318 = "().size();";
  protected final String TEXT_319 = NL + "\t\treturn ";
  protected final String TEXT_320 = " == null ? 0 : ";
  protected final String TEXT_321 = ".size();";
  protected final String TEXT_322 = NL + "\t}" + NL;
  protected final String TEXT_323 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */";
  protected final String TEXT_324 = NL + "\tvoid set";
  protected final String TEXT_325 = "(";
  protected final String TEXT_326 = "[] new";
  protected final String TEXT_327 = ");" + NL;
  protected final String TEXT_328 = NL + "\tpublic void set";
  protected final String TEXT_329 = "(";
  protected final String TEXT_330 = "[] new";
  protected final String TEXT_331 = ")" + NL + "\t{" + NL + "\t\t((";
  protected final String TEXT_332 = ")";
  protected final String TEXT_333 = "()).setData(new";
  protected final String TEXT_334 = ".length, new";
  protected final String TEXT_335 = ");" + NL + "\t}" + NL;
  protected final String TEXT_336 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */";
  protected final String TEXT_337 = NL + "\tvoid set";
  protected final String TEXT_338 = "(int index, ";
  protected final String TEXT_339 = " element);" + NL;
  protected final String TEXT_340 = NL + "\tpublic void set";
  protected final String TEXT_341 = "(int index, ";
  protected final String TEXT_342 = " element)" + NL + "\t{" + NL + "\t\t";
  protected final String TEXT_343 = "().set(index, element);" + NL + "\t}" + NL;
  protected final String TEXT_344 = NL + "\t/**" + NL + "\t * Returns the value of the '<em><b>";
  protected final String TEXT_345 = "</b></em>' ";
  protected final String TEXT_346 = ".";
  protected final String TEXT_347 = NL + "\t * The key is of type ";
  protected final String TEXT_348 = "list of {@link ";
  protected final String TEXT_349 = "}";
  protected final String TEXT_350 = "{@link ";
  protected final String TEXT_351 = "}";
  protected final String TEXT_352 = "," + NL + "\t * and the value is of type ";
  protected final String TEXT_353 = "list of {@link ";
  protected final String TEXT_354 = "}";
  protected final String TEXT_355 = "{@link ";
  protected final String TEXT_356 = "}";
  protected final String TEXT_357 = ",";
  protected final String TEXT_358 = NL + "\t * The list contents are of type {@link ";
  protected final String TEXT_359 = "}";
  protected final String TEXT_360 = ".";
  protected final String TEXT_361 = NL + "\t * The default value is <code>";
  protected final String TEXT_362 = "</code>.";
  protected final String TEXT_363 = NL + "\t * The literals are from the enumeration {@link ";
  protected final String TEXT_364 = "}.";
  protected final String TEXT_365 = NL + "\t * It is bidirectional and its opposite is '{@link ";
  protected final String TEXT_366 = "#";
  protected final String TEXT_367 = " <em>";
  protected final String TEXT_368 = "</em>}'.";
  protected final String TEXT_369 = NL + "\t * <!-- begin-user-doc -->";
  protected final String TEXT_370 = NL + "\t * <p>" + NL + "\t * If the meaning of the '<em>";
  protected final String TEXT_371 = "</em>' ";
  protected final String TEXT_372 = " isn't clear," + NL + "\t * there really should be more of a description here..." + NL + "\t * </p>";
  protected final String TEXT_373 = NL + "\t * <!-- end-user-doc -->";
  protected final String TEXT_374 = NL + "\t * <!-- begin-model-doc -->" + NL + "\t * ";
  protected final String TEXT_375 = NL + "\t * <!-- end-model-doc -->";
  protected final String TEXT_376 = NL + "\t * @return the value of the '<em>";
  protected final String TEXT_377 = "</em>' ";
  protected final String TEXT_378 = ".";
  protected final String TEXT_379 = NL + "\t * @see ";
  protected final String TEXT_380 = NL + "\t * @see #isSet";
  protected final String TEXT_381 = "()";
  protected final String TEXT_382 = NL + "\t * @see #unset";
  protected final String TEXT_383 = "()";
  protected final String TEXT_384 = NL + "\t * @see #set";
  protected final String TEXT_385 = "(";
  protected final String TEXT_386 = ")";
  protected final String TEXT_387 = NL + "\t * @see ";
  protected final String TEXT_388 = "#get";
  protected final String TEXT_389 = "()";
  protected final String TEXT_390 = NL + "\t * @see ";
  protected final String TEXT_391 = "#";
  protected final String TEXT_392 = NL + "\t * @model ";
  protected final String TEXT_393 = NL + "\t *        ";
  protected final String TEXT_394 = NL + "\t * @model";
  protected final String TEXT_395 = NL + "\t * @generated" + NL + "\t */";
  protected final String TEXT_396 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */";
  protected final String TEXT_397 = NL + "\t";
  protected final String TEXT_398 = " ";
  protected final String TEXT_399 = "();" + NL;
  protected final String TEXT_400 = NL + "\t@SuppressWarnings(\"unchecked\")";
  protected final String TEXT_401 = NL + "\tpublic ";
  protected final String TEXT_402 = " ";
  protected final String TEXT_403 = "_";
  protected final String TEXT_404 = "()" + NL + "\t{";
  protected final String TEXT_405 = NL + "\t\treturn ";
  protected final String TEXT_406 = "(";
  protected final String TEXT_407 = "(";
  protected final String TEXT_408 = ")eDynamicGet(";
  protected final String TEXT_409 = ", ";
  protected final String TEXT_410 = ", true, ";
  protected final String TEXT_411 = ")";
  protected final String TEXT_412 = ").";
  protected final String TEXT_413 = "()";
  protected final String TEXT_414 = ";";
  protected final String TEXT_415 = NL + "\t\t";
  protected final String TEXT_416 = NL + "\t\treturn ";
  protected final String TEXT_417 = "(";
  protected final String TEXT_418 = "(";
  protected final String TEXT_419 = ")eGet(";
  protected final String TEXT_420 = ", true)";
  protected final String TEXT_421 = ").";
  protected final String TEXT_422 = "()";
  protected final String TEXT_423 = ";";
  protected final String TEXT_424 = NL + "\t\treturn ";
  protected final String TEXT_425 = "(";
  protected final String TEXT_426 = "(";
  protected final String TEXT_427 = ")";
  protected final String TEXT_428 = "__ESETTING_DELEGATE.dynamicGet(this, null, 0, true, false)";
  protected final String TEXT_429 = ").";
  protected final String TEXT_430 = "()";
  protected final String TEXT_431 = ";";
  protected final String TEXT_432 = NL + "\t\t";
  protected final String TEXT_433 = " ";
  protected final String TEXT_434 = " = (";
  protected final String TEXT_435 = ")eVirtualGet(";
  protected final String TEXT_436 = ");";
  protected final String TEXT_437 = NL + "\t\tif (";
  protected final String TEXT_438 = " == null)" + NL + "\t\t{";
  protected final String TEXT_439 = NL + "\t\t\teVirtualSet(";
  protected final String TEXT_440 = ", ";
  protected final String TEXT_441 = " = new ";
  protected final String TEXT_442 = ");";
  protected final String TEXT_443 = NL + "\t\t\t";
  protected final String TEXT_444 = " = new ";
  protected final String TEXT_445 = ";";
  protected final String TEXT_446 = NL + "\t\t}" + NL + "\t\treturn ";
  protected final String TEXT_447 = ";";
  protected final String TEXT_448 = NL + "\t\tif (eContainerFeatureID() != ";
  protected final String TEXT_449 = ") return null;" + NL + "\t\treturn (";
  protected final String TEXT_450 = ")";
  protected final String TEXT_451 = "eContainer";
  protected final String TEXT_452 = "eInternalContainer";
  protected final String TEXT_453 = "();";
  protected final String TEXT_454 = NL + "\t\t";
  protected final String TEXT_455 = " ";
  protected final String TEXT_456 = " = (";
  protected final String TEXT_457 = ")eVirtualGet(";
  protected final String TEXT_458 = ", ";
  protected final String TEXT_459 = ");";
  protected final String TEXT_460 = NL + "\t\tif (";
  protected final String TEXT_461 = " != null && ";
  protected final String TEXT_462 = ".eIsProxy())" + NL + "\t\t{" + NL + "\t\t\t";
  protected final String TEXT_463 = " old";
  protected final String TEXT_464 = " = (";
  protected final String TEXT_465 = ")";
  protected final String TEXT_466 = ";" + NL + "\t\t\t";
  protected final String TEXT_467 = " = ";
  protected final String TEXT_468 = "eResolveProxy(old";
  protected final String TEXT_469 = ");" + NL + "\t\t\tif (";
  protected final String TEXT_470 = " != old";
  protected final String TEXT_471 = ")" + NL + "\t\t\t{";
  protected final String TEXT_472 = NL + "\t\t\t\t";
  protected final String TEXT_473 = " new";
  protected final String TEXT_474 = " = (";
  protected final String TEXT_475 = ")";
  protected final String TEXT_476 = ";";
  protected final String TEXT_477 = NL + "\t\t\t\t";
  protected final String TEXT_478 = " msgs = old";
  protected final String TEXT_479 = ".eInverseRemove(this, EOPPOSITE_FEATURE_BASE - ";
  protected final String TEXT_480 = ", null, null);";
  protected final String TEXT_481 = NL + "\t\t\t\t";
  protected final String TEXT_482 = " msgs =  old";
  protected final String TEXT_483 = ".eInverseRemove(this, ";
  protected final String TEXT_484 = ", ";
  protected final String TEXT_485 = ".class, null);";
  protected final String TEXT_486 = NL + "\t\t\t\tif (new";
  protected final String TEXT_487 = ".eInternalContainer() == null)" + NL + "\t\t\t\t{";
  protected final String TEXT_488 = NL + "\t\t\t\t\tmsgs = new";
  protected final String TEXT_489 = ".eInverseAdd(this, EOPPOSITE_FEATURE_BASE - ";
  protected final String TEXT_490 = ", null, msgs);";
  protected final String TEXT_491 = NL + "\t\t\t\t\tmsgs =  new";
  protected final String TEXT_492 = ".eInverseAdd(this, ";
  protected final String TEXT_493 = ", ";
  protected final String TEXT_494 = ".class, msgs);";
  protected final String TEXT_495 = NL + "\t\t\t\t}" + NL + "\t\t\t\tif (msgs != null) msgs.dispatch();";
  protected final String TEXT_496 = NL + "\t\t\t\teVirtualSet(";
  protected final String TEXT_497 = ", ";
  protected final String TEXT_498 = ");";
  protected final String TEXT_499 = NL + "\t\t\t\tif (eNotificationRequired())" + NL + "\t\t\t\t\teNotify(new ";
  protected final String TEXT_500 = "(this, ";
  protected final String TEXT_501 = ".RESOLVE, ";
  protected final String TEXT_502 = ", old";
  protected final String TEXT_503 = ", ";
  protected final String TEXT_504 = "));";
  protected final String TEXT_505 = NL + "\t\t\t}" + NL + "\t\t}";
  protected final String TEXT_506 = NL + "\t\treturn (";
  protected final String TEXT_507 = ")eVirtualGet(";
  protected final String TEXT_508 = ", ";
  protected final String TEXT_509 = ");";
  protected final String TEXT_510 = NL + "\t\treturn (";
  protected final String TEXT_511 = " & ";
  protected final String TEXT_512 = "_EFLAG) != 0;";
  protected final String TEXT_513 = NL + "\t\treturn ";
  protected final String TEXT_514 = "_EFLAG_VALUES[(";
  protected final String TEXT_515 = " & ";
  protected final String TEXT_516 = "_EFLAG) >>> ";
  protected final String TEXT_517 = "_EFLAG_OFFSET];";
  protected final String TEXT_518 = NL + "\t\treturn ";
  protected final String TEXT_519 = ";";
  protected final String TEXT_520 = NL + "\t\t";
  protected final String TEXT_521 = " ";
  protected final String TEXT_522 = " = basicGet";
  protected final String TEXT_523 = "();" + NL + "\t\treturn ";
  protected final String TEXT_524 = " != null && ";
  protected final String TEXT_525 = ".eIsProxy() ? ";
  protected final String TEXT_526 = "eResolveProxy((";
  protected final String TEXT_527 = ")";
  protected final String TEXT_528 = ") : ";
  protected final String TEXT_529 = ";";
  protected final String TEXT_530 = NL + "\t\treturn new ";
  protected final String TEXT_531 = "((";
  protected final String TEXT_532 = ".Internal)((";
  protected final String TEXT_533 = ".Internal.Wrapper)get";
  protected final String TEXT_534 = "()).featureMap().";
  protected final String TEXT_535 = "list(";
  protected final String TEXT_536 = "));";
  protected final String TEXT_537 = NL + "\t\treturn (";
  protected final String TEXT_538 = ")get";
  protected final String TEXT_539 = "().";
  protected final String TEXT_540 = "list(";
  protected final String TEXT_541 = ");";
  protected final String TEXT_542 = NL + "\t\treturn ((";
  protected final String TEXT_543 = ".Internal.Wrapper)get";
  protected final String TEXT_544 = "()).featureMap().list(";
  protected final String TEXT_545 = ");";
  protected final String TEXT_546 = NL + "\t\treturn get";
  protected final String TEXT_547 = "().list(";
  protected final String TEXT_548 = ");";
  protected final String TEXT_549 = NL + "\t\treturn ";
  protected final String TEXT_550 = "(";
  protected final String TEXT_551 = "(";
  protected final String TEXT_552 = ")";
  protected final String TEXT_553 = "((";
  protected final String TEXT_554 = ".Internal.Wrapper)get";
  protected final String TEXT_555 = "()).featureMap().get(";
  protected final String TEXT_556 = ", true)";
  protected final String TEXT_557 = ").";
  protected final String TEXT_558 = "()";
  protected final String TEXT_559 = ";";
  protected final String TEXT_560 = NL + "\t\treturn ";
  protected final String TEXT_561 = "(";
  protected final String TEXT_562 = "(";
  protected final String TEXT_563 = ")";
  protected final String TEXT_564 = "get";
  protected final String TEXT_565 = "().get(";
  protected final String TEXT_566 = ", true)";
  protected final String TEXT_567 = ").";
  protected final String TEXT_568 = "()";
  protected final String TEXT_569 = ";";
  protected final String TEXT_570 = NL + "\t\t";
  protected final String TEXT_571 = NL + "\t\t";
  protected final String TEXT_572 = NL + "\t\t// TODO: implement this method to return the '";
  protected final String TEXT_573 = "' ";
  protected final String TEXT_574 = NL + "\t\t// Ensure that you remove @generated or mark it @generated NOT";
  protected final String TEXT_575 = NL + "\t\t// The list is expected to implement org.eclipse.emf.ecore.util.InternalEList and org.eclipse.emf.ecore.EStructuralFeature.Setting" + NL + "\t\t// so it's likely that an appropriate subclass of org.eclipse.emf.ecore.util.";
  protected final String TEXT_576 = "EcoreEMap";
  protected final String TEXT_577 = "BasicFeatureMap";
  protected final String TEXT_578 = "EcoreEList";
  protected final String TEXT_579 = " should be used.";
  protected final String TEXT_580 = NL + "\t\tthrow new UnsupportedOperationException();";
  protected final String TEXT_581 = NL + "\t}" + NL;
  protected final String TEXT_582 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */";
  protected final String TEXT_583 = NL + "\tpublic ";
  protected final String TEXT_584 = " basicGet";
  protected final String TEXT_585 = "()" + NL + "\t{";
  protected final String TEXT_586 = NL + "\t\treturn (";
  protected final String TEXT_587 = ")eDynamicGet(";
  protected final String TEXT_588 = ", ";
  protected final String TEXT_589 = ", false, ";
  protected final String TEXT_590 = ");";
  protected final String TEXT_591 = NL + "\t\treturn ";
  protected final String TEXT_592 = "(";
  protected final String TEXT_593 = "(";
  protected final String TEXT_594 = ")";
  protected final String TEXT_595 = "__ESETTING_DELEGATE.dynamicGet(this, null, 0, false, false)";
  protected final String TEXT_596 = ").";
  protected final String TEXT_597 = "()";
  protected final String TEXT_598 = ";";
  protected final String TEXT_599 = NL + "\t\tif (eContainerFeatureID() != ";
  protected final String TEXT_600 = ") return null;" + NL + "\t\treturn (";
  protected final String TEXT_601 = ")eInternalContainer();";
  protected final String TEXT_602 = NL + "\t\treturn (";
  protected final String TEXT_603 = ")eVirtualGet(";
  protected final String TEXT_604 = ");";
  protected final String TEXT_605 = NL + "\t\treturn ";
  protected final String TEXT_606 = ";";
  protected final String TEXT_607 = NL + "\t\treturn (";
  protected final String TEXT_608 = ")((";
  protected final String TEXT_609 = ".Internal.Wrapper)get";
  protected final String TEXT_610 = "()).featureMap().get(";
  protected final String TEXT_611 = ", false);";
  protected final String TEXT_612 = NL + "\t\treturn (";
  protected final String TEXT_613 = ")get";
  protected final String TEXT_614 = "().get(";
  protected final String TEXT_615 = ", false);";
  protected final String TEXT_616 = NL + "\t\t";
  protected final String TEXT_617 = NL + "\t\t// TODO: implement this method to return the '";
  protected final String TEXT_618 = "' ";
  protected final String TEXT_619 = NL + "\t\t// -> do not perform proxy resolution" + NL + "\t\t// Ensure that you remove @generated or mark it @generated NOT" + NL + "\t\tthrow new UnsupportedOperationException();";
  protected final String TEXT_620 = NL + "\t}" + NL;
  protected final String TEXT_621 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */";
  protected final String TEXT_622 = NL + "\tpublic ";
  protected final String TEXT_623 = " basicSet";
  protected final String TEXT_624 = "(";
  protected final String TEXT_625 = " new";
  protected final String TEXT_626 = ", ";
  protected final String TEXT_627 = " msgs)" + NL + "\t{";
  protected final String TEXT_628 = NL + "\t\tmsgs = eBasicSetContainer((";
  protected final String TEXT_629 = ")new";
  protected final String TEXT_630 = ", ";
  protected final String TEXT_631 = ", msgs);";
  protected final String TEXT_632 = NL + "\t\treturn msgs;";
  protected final String TEXT_633 = NL + "\t\tmsgs = eDynamicInverseAdd((";
  protected final String TEXT_634 = ")new";
  protected final String TEXT_635 = ", ";
  protected final String TEXT_636 = ", msgs);";
  protected final String TEXT_637 = NL + "\t\treturn msgs;";
  protected final String TEXT_638 = NL + "\t\tObject old";
  protected final String TEXT_639 = " = eVirtualSet(";
  protected final String TEXT_640 = ", new";
  protected final String TEXT_641 = ");";
  protected final String TEXT_642 = NL + "\t\t";
  protected final String TEXT_643 = " old";
  protected final String TEXT_644 = " = ";
  protected final String TEXT_645 = ";" + NL + "\t\t";
  protected final String TEXT_646 = " = new";
  protected final String TEXT_647 = ";";
  protected final String TEXT_648 = NL + "\t\tboolean isSetChange = old";
  protected final String TEXT_649 = " == EVIRTUAL_NO_VALUE;";
  protected final String TEXT_650 = NL + "\t\tboolean old";
  protected final String TEXT_651 = "ESet = (";
  protected final String TEXT_652 = " & ";
  protected final String TEXT_653 = "_ESETFLAG) != 0;";
  protected final String TEXT_654 = NL + "\t\t";
  protected final String TEXT_655 = " |= ";
  protected final String TEXT_656 = "_ESETFLAG;";
  protected final String TEXT_657 = NL + "\t\tboolean old";
  protected final String TEXT_658 = "ESet = ";
  protected final String TEXT_659 = "ESet;";
  protected final String TEXT_660 = NL + "\t\t";
  protected final String TEXT_661 = "ESet = true;";
  protected final String TEXT_662 = NL + "\t\tif (eNotificationRequired())" + NL + "\t\t{";
  protected final String TEXT_663 = NL + "\t\t\t";
  protected final String TEXT_664 = " notification = new ";
  protected final String TEXT_665 = "(this, ";
  protected final String TEXT_666 = ".SET, ";
  protected final String TEXT_667 = ", ";
  protected final String TEXT_668 = "isSetChange ? null : old";
  protected final String TEXT_669 = "old";
  protected final String TEXT_670 = ", new";
  protected final String TEXT_671 = ", ";
  protected final String TEXT_672 = "isSetChange";
  protected final String TEXT_673 = "!old";
  protected final String TEXT_674 = "ESet";
  protected final String TEXT_675 = ");";
  protected final String TEXT_676 = NL + "\t\t\t";
  protected final String TEXT_677 = " notification = new ";
  protected final String TEXT_678 = "(this, ";
  protected final String TEXT_679 = ".SET, ";
  protected final String TEXT_680 = ", ";
  protected final String TEXT_681 = "old";
  protected final String TEXT_682 = " == EVIRTUAL_NO_VALUE ? null : old";
  protected final String TEXT_683 = "old";
  protected final String TEXT_684 = ", new";
  protected final String TEXT_685 = ");";
  protected final String TEXT_686 = NL + "\t\t\tif (msgs == null) msgs = notification; else msgs.add(notification);" + NL + "\t\t}";
  protected final String TEXT_687 = NL + "\t\treturn msgs;";
  protected final String TEXT_688 = NL + "\t\treturn ((";
  protected final String TEXT_689 = ".Internal)((";
  protected final String TEXT_690 = ".Internal.Wrapper)get";
  protected final String TEXT_691 = "()).featureMap()).basicAdd(";
  protected final String TEXT_692 = ", new";
  protected final String TEXT_693 = ", msgs);";
  protected final String TEXT_694 = NL + "\t\treturn ((";
  protected final String TEXT_695 = ".Internal)get";
  protected final String TEXT_696 = "()).basicAdd(";
  protected final String TEXT_697 = ", new";
  protected final String TEXT_698 = ", msgs);";
  protected final String TEXT_699 = NL + "\t\t// TODO: implement this method to set the contained '";
  protected final String TEXT_700 = "' ";
  protected final String TEXT_701 = NL + "\t\t// -> this method is automatically invoked to keep the containment relationship in synch" + NL + "\t\t// -> do not modify other features" + NL + "\t\t// -> return msgs, after adding any generated Notification to it (if it is null, a NotificationChain object must be created first)" + NL + "\t\t// Ensure that you remove @generated or mark it @generated NOT" + NL + "\t\tthrow new UnsupportedOperationException();";
  protected final String TEXT_702 = NL + "\t}" + NL;
  protected final String TEXT_703 = NL + "\t/**" + NL + "\t * Sets the value of the '{@link ";
  protected final String TEXT_704 = "#";
  protected final String TEXT_705 = " <em>";
  protected final String TEXT_706 = "</em>}' ";
  protected final String TEXT_707 = ".";
  protected final String TEXT_708 = NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @param value the new value of the '<em>";
  protected final String TEXT_709 = "</em>' ";
  protected final String TEXT_710 = ".";
  protected final String TEXT_711 = NL + "\t * @see ";
  protected final String TEXT_712 = NL + "\t * @see #isSet";
  protected final String TEXT_713 = "()";
  protected final String TEXT_714 = NL + "\t * @see #unset";
  protected final String TEXT_715 = "()";
  protected final String TEXT_716 = NL + "\t * @see #";
  protected final String TEXT_717 = "()" + NL + "\t * @generated" + NL + "\t */";
  protected final String TEXT_718 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */";
  protected final String TEXT_719 = NL + "\tvoid set";
  protected final String TEXT_720 = "(";
  protected final String TEXT_721 = " value);" + NL;
  protected final String TEXT_722 = NL + "\tpublic void set";
  protected final String TEXT_723 = "_";
  protected final String TEXT_724 = "(";
  protected final String TEXT_725 = " ";
  protected final String TEXT_726 = ")" + NL + "\t{";
  protected final String TEXT_727 = NL + "\t\teDynamicSet(";
  protected final String TEXT_728 = ", ";
  protected final String TEXT_729 = ", ";
  protected final String TEXT_730 = "new ";
  protected final String TEXT_731 = "(";
  protected final String TEXT_732 = "new";
  protected final String TEXT_733 = ")";
  protected final String TEXT_734 = ");";
  protected final String TEXT_735 = NL + "\t\teSet(";
  protected final String TEXT_736 = ", ";
  protected final String TEXT_737 = "new ";
  protected final String TEXT_738 = "(";
  protected final String TEXT_739 = "new";
  protected final String TEXT_740 = ")";
  protected final String TEXT_741 = ");";
  protected final String TEXT_742 = NL + "\t\t";
  protected final String TEXT_743 = "__ESETTING_DELEGATE.dynamicSet(this, null, 0, ";
  protected final String TEXT_744 = "new ";
  protected final String TEXT_745 = "(";
  protected final String TEXT_746 = "new";
  protected final String TEXT_747 = ")";
  protected final String TEXT_748 = ");";
  protected final String TEXT_749 = NL + "\t\tif (new";
  protected final String TEXT_750 = " != eInternalContainer() || (eContainerFeatureID() != ";
  protected final String TEXT_751 = " && new";
  protected final String TEXT_752 = " != null))" + NL + "\t\t{" + NL + "\t\t\tif (";
  protected final String TEXT_753 = ".isAncestor(this, ";
  protected final String TEXT_754 = "new";
  protected final String TEXT_755 = "))" + NL + "\t\t\t\tthrow new ";
  protected final String TEXT_756 = "(\"Recursive containment not allowed for \" + toString());";
  protected final String TEXT_757 = NL + "\t\t\t";
  protected final String TEXT_758 = " msgs = null;" + NL + "\t\t\tif (eInternalContainer() != null)" + NL + "\t\t\t\tmsgs = eBasicRemoveFromContainer(msgs);" + NL + "\t\t\tif (new";
  protected final String TEXT_759 = " != null)" + NL + "\t\t\t\tmsgs = ((";
  protected final String TEXT_760 = ")new";
  protected final String TEXT_761 = ").eInverseAdd(this, ";
  protected final String TEXT_762 = ", ";
  protected final String TEXT_763 = ".class, msgs);" + NL + "\t\t\tmsgs = basicSet";
  protected final String TEXT_764 = "(";
  protected final String TEXT_765 = "new";
  protected final String TEXT_766 = ", msgs);" + NL + "\t\t\tif (msgs != null) msgs.dispatch();" + NL + "\t\t}";
  protected final String TEXT_767 = NL + "\t\telse if (eNotificationRequired())" + NL + "\t\t\teNotify(new ";
  protected final String TEXT_768 = "(this, ";
  protected final String TEXT_769 = ".SET, ";
  protected final String TEXT_770 = ", new";
  protected final String TEXT_771 = ", new";
  protected final String TEXT_772 = "));";
  protected final String TEXT_773 = NL + "\t\t";
  protected final String TEXT_774 = " ";
  protected final String TEXT_775 = " = (";
  protected final String TEXT_776 = ")eVirtualGet(";
  protected final String TEXT_777 = ");";
  protected final String TEXT_778 = NL + "\t\tif (new";
  protected final String TEXT_779 = " != ";
  protected final String TEXT_780 = ")" + NL + "\t\t{" + NL + "\t\t\t";
  protected final String TEXT_781 = " msgs = null;" + NL + "\t\t\tif (";
  protected final String TEXT_782 = " != null)";
  protected final String TEXT_783 = NL + "\t\t\t\tmsgs = ((";
  protected final String TEXT_784 = ")";
  protected final String TEXT_785 = ").eInverseRemove(this, EOPPOSITE_FEATURE_BASE - ";
  protected final String TEXT_786 = ", null, msgs);" + NL + "\t\t\tif (new";
  protected final String TEXT_787 = " != null)" + NL + "\t\t\t\tmsgs = ((";
  protected final String TEXT_788 = ")new";
  protected final String TEXT_789 = ").eInverseAdd(this, EOPPOSITE_FEATURE_BASE - ";
  protected final String TEXT_790 = ", null, msgs);";
  protected final String TEXT_791 = NL + "\t\t\t\tmsgs = ((";
  protected final String TEXT_792 = ")";
  protected final String TEXT_793 = ").eInverseRemove(this, ";
  protected final String TEXT_794 = ", ";
  protected final String TEXT_795 = ".class, msgs);" + NL + "\t\t\tif (new";
  protected final String TEXT_796 = " != null)" + NL + "\t\t\t\tmsgs = ((";
  protected final String TEXT_797 = ")new";
  protected final String TEXT_798 = ").eInverseAdd(this, ";
  protected final String TEXT_799 = ", ";
  protected final String TEXT_800 = ".class, msgs);";
  protected final String TEXT_801 = NL + "\t\t\tmsgs = basicSet";
  protected final String TEXT_802 = "(";
  protected final String TEXT_803 = "new";
  protected final String TEXT_804 = ", msgs);" + NL + "\t\t\tif (msgs != null) msgs.dispatch();" + NL + "\t\t}";
  protected final String TEXT_805 = NL + "\t\telse" + NL + "\t\t{";
  protected final String TEXT_806 = NL + "\t\t\tboolean old";
  protected final String TEXT_807 = "ESet = eVirtualIsSet(";
  protected final String TEXT_808 = ");";
  protected final String TEXT_809 = NL + "\t\t\tboolean old";
  protected final String TEXT_810 = "ESet = (";
  protected final String TEXT_811 = " & ";
  protected final String TEXT_812 = "_ESETFLAG) != 0;";
  protected final String TEXT_813 = NL + "\t\t\t";
  protected final String TEXT_814 = " |= ";
  protected final String TEXT_815 = "_ESETFLAG;";
  protected final String TEXT_816 = NL + "\t\t\tboolean old";
  protected final String TEXT_817 = "ESet = ";
  protected final String TEXT_818 = "ESet;";
  protected final String TEXT_819 = NL + "\t\t\t";
  protected final String TEXT_820 = "ESet = true;";
  protected final String TEXT_821 = NL + "\t\t\tif (eNotificationRequired())" + NL + "\t\t\t\teNotify(new ";
  protected final String TEXT_822 = "(this, ";
  protected final String TEXT_823 = ".SET, ";
  protected final String TEXT_824 = ", new";
  protected final String TEXT_825 = ", new";
  protected final String TEXT_826 = ", !old";
  protected final String TEXT_827 = "ESet));";
  protected final String TEXT_828 = NL + "\t\t}";
  protected final String TEXT_829 = NL + "\t\telse if (eNotificationRequired())" + NL + "\t\t\teNotify(new ";
  protected final String TEXT_830 = "(this, ";
  protected final String TEXT_831 = ".SET, ";
  protected final String TEXT_832 = ", new";
  protected final String TEXT_833 = ", new";
  protected final String TEXT_834 = "));";
  protected final String TEXT_835 = NL + "\t\t";
  protected final String TEXT_836 = " old";
  protected final String TEXT_837 = " = (";
  protected final String TEXT_838 = " & ";
  protected final String TEXT_839 = "_EFLAG) != 0;";
  protected final String TEXT_840 = NL + "\t\t";
  protected final String TEXT_841 = " old";
  protected final String TEXT_842 = " = ";
  protected final String TEXT_843 = "_EFLAG_VALUES[(";
  protected final String TEXT_844 = " & ";
  protected final String TEXT_845 = "_EFLAG) >>> ";
  protected final String TEXT_846 = "_EFLAG_OFFSET];";
  protected final String TEXT_847 = NL + "\t\tif (new";
  protected final String TEXT_848 = ") ";
  protected final String TEXT_849 = " |= ";
  protected final String TEXT_850 = "_EFLAG; else ";
  protected final String TEXT_851 = " &= ~";
  protected final String TEXT_852 = "_EFLAG;";
  protected final String TEXT_853 = NL + "\t\tif (new";
  protected final String TEXT_854 = " == null) new";
  protected final String TEXT_855 = " = ";
  protected final String TEXT_856 = "_EDEFAULT;" + NL + "\t\t";
  protected final String TEXT_857 = " = ";
  protected final String TEXT_858 = " & ~";
  protected final String TEXT_859 = "_EFLAG | ";
  protected final String TEXT_860 = "new";
  protected final String TEXT_861 = ".ordinal()";
  protected final String TEXT_862 = ".VALUES.indexOf(new";
  protected final String TEXT_863 = ")";
  protected final String TEXT_864 = " << ";
  protected final String TEXT_865 = "_EFLAG_OFFSET;";
  protected final String TEXT_866 = NL + "\t\t";
  protected final String TEXT_867 = " old";
  protected final String TEXT_868 = " = ";
  protected final String TEXT_869 = ";";
  protected final String TEXT_870 = NL + "\t\t";
  protected final String TEXT_871 = " ";
  protected final String TEXT_872 = " = new";
  protected final String TEXT_873 = " == null ? ";
  protected final String TEXT_874 = " : new";
  protected final String TEXT_875 = ";";
  protected final String TEXT_876 = NL + "\t\t";
  protected final String TEXT_877 = " = new";
  protected final String TEXT_878 = " == null ? ";
  protected final String TEXT_879 = " : new";
  protected final String TEXT_880 = ";";
  protected final String TEXT_881 = NL + "\t\t";
  protected final String TEXT_882 = " ";
  protected final String TEXT_883 = " = ";
  protected final String TEXT_884 = "new";
  protected final String TEXT_885 = ";";
  protected final String TEXT_886 = NL + "\t\t";
  protected final String TEXT_887 = " = ";
  protected final String TEXT_888 = "new";
  protected final String TEXT_889 = ";";
  protected final String TEXT_890 = NL + "\t\tObject old";
  protected final String TEXT_891 = " = eVirtualSet(";
  protected final String TEXT_892 = ", ";
  protected final String TEXT_893 = ");";
  protected final String TEXT_894 = NL + "\t\tboolean isSetChange = old";
  protected final String TEXT_895 = " == EVIRTUAL_NO_VALUE;";
  protected final String TEXT_896 = NL + "\t\tboolean old";
  protected final String TEXT_897 = "ESet = (";
  protected final String TEXT_898 = " & ";
  protected final String TEXT_899 = "_ESETFLAG) != 0;";
  protected final String TEXT_900 = NL + "\t\t";
  protected final String TEXT_901 = " |= ";
  protected final String TEXT_902 = "_ESETFLAG;";
  protected final String TEXT_903 = NL + "\t\tboolean old";
  protected final String TEXT_904 = "ESet = ";
  protected final String TEXT_905 = "ESet;";
  protected final String TEXT_906 = NL + "\t\t";
  protected final String TEXT_907 = "ESet = true;";
  protected final String TEXT_908 = NL + "\t\tif (eNotificationRequired())" + NL + "\t\t\teNotify(new ";
  protected final String TEXT_909 = "(this, ";
  protected final String TEXT_910 = ".SET, ";
  protected final String TEXT_911 = ", ";
  protected final String TEXT_912 = "isSetChange ? ";
  protected final String TEXT_913 = " : old";
  protected final String TEXT_914 = "old";
  protected final String TEXT_915 = ", ";
  protected final String TEXT_916 = "new";
  protected final String TEXT_917 = ", ";
  protected final String TEXT_918 = "isSetChange";
  protected final String TEXT_919 = "!old";
  protected final String TEXT_920 = "ESet";
  protected final String TEXT_921 = "));";
  protected final String TEXT_922 = NL + "\t\tif (eNotificationRequired())" + NL + "\t\t\teNotify(new ";
  protected final String TEXT_923 = "(this, ";
  protected final String TEXT_924 = ".SET, ";
  protected final String TEXT_925 = ", ";
  protected final String TEXT_926 = "old";
  protected final String TEXT_927 = " == EVIRTUAL_NO_VALUE ? ";
  protected final String TEXT_928 = " : old";
  protected final String TEXT_929 = "old";
  protected final String TEXT_930 = ", ";
  protected final String TEXT_931 = "new";
  protected final String TEXT_932 = "));";
  protected final String TEXT_933 = NL + "\t\t((";
  protected final String TEXT_934 = ".Internal)((";
  protected final String TEXT_935 = ".Internal.Wrapper)get";
  protected final String TEXT_936 = "()).featureMap()).set(";
  protected final String TEXT_937 = ", ";
  protected final String TEXT_938 = "new ";
  protected final String TEXT_939 = "(";
  protected final String TEXT_940 = "new";
  protected final String TEXT_941 = ")";
  protected final String TEXT_942 = ");";
  protected final String TEXT_943 = NL + "\t\t((";
  protected final String TEXT_944 = ".Internal)get";
  protected final String TEXT_945 = "()).set(";
  protected final String TEXT_946 = ", ";
  protected final String TEXT_947 = "new ";
  protected final String TEXT_948 = "(";
  protected final String TEXT_949 = "new";
  protected final String TEXT_950 = ")";
  protected final String TEXT_951 = ");";
  protected final String TEXT_952 = NL + "\t\t";
  protected final String TEXT_953 = NL + "\t\t// TODO: implement this method to set the '";
  protected final String TEXT_954 = "' ";
  protected final String TEXT_955 = NL + "\t\t// Ensure that you remove @generated or mark it @generated NOT" + NL + "\t\tthrow new UnsupportedOperationException();";
  protected final String TEXT_956 = NL + "\t}" + NL;
  protected final String TEXT_957 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */";
  protected final String TEXT_958 = NL + "\tpublic ";
  protected final String TEXT_959 = " basicUnset";
  protected final String TEXT_960 = "(";
  protected final String TEXT_961 = " msgs)" + NL + "\t{";
  protected final String TEXT_962 = NL + "\t\treturn eDynamicInverseRemove((";
  protected final String TEXT_963 = ")";
  protected final String TEXT_964 = "basicGet";
  protected final String TEXT_965 = "(), ";
  protected final String TEXT_966 = ", msgs);";
  protected final String TEXT_967 = "Object old";
  protected final String TEXT_968 = " = ";
  protected final String TEXT_969 = "eVirtualUnset(";
  protected final String TEXT_970 = ");";
  protected final String TEXT_971 = NL + "\t\t";
  protected final String TEXT_972 = " old";
  protected final String TEXT_973 = " = ";
  protected final String TEXT_974 = ";";
  protected final String TEXT_975 = NL + "\t\t";
  protected final String TEXT_976 = " = null;";
  protected final String TEXT_977 = NL + "\t\tboolean isSetChange = old";
  protected final String TEXT_978 = " != EVIRTUAL_NO_VALUE;";
  protected final String TEXT_979 = NL + "\t\tboolean old";
  protected final String TEXT_980 = "ESet = (";
  protected final String TEXT_981 = " & ";
  protected final String TEXT_982 = "_ESETFLAG) != 0;";
  protected final String TEXT_983 = NL + "\t\t";
  protected final String TEXT_984 = " &= ~";
  protected final String TEXT_985 = "_ESETFLAG;";
  protected final String TEXT_986 = NL + "\t\tboolean old";
  protected final String TEXT_987 = "ESet = ";
  protected final String TEXT_988 = "ESet;";
  protected final String TEXT_989 = NL + "\t\t";
  protected final String TEXT_990 = "ESet = false;";
  protected final String TEXT_991 = NL + "\t\tif (eNotificationRequired())" + NL + "\t\t{" + NL + "\t\t\t";
  protected final String TEXT_992 = " notification = new ";
  protected final String TEXT_993 = "(this, ";
  protected final String TEXT_994 = ".UNSET, ";
  protected final String TEXT_995 = ", ";
  protected final String TEXT_996 = "isSetChange ? old";
  protected final String TEXT_997 = " : null";
  protected final String TEXT_998 = "old";
  protected final String TEXT_999 = ", null, ";
  protected final String TEXT_1000 = "isSetChange";
  protected final String TEXT_1001 = "old";
  protected final String TEXT_1002 = "ESet";
  protected final String TEXT_1003 = ");" + NL + "\t\t\tif (msgs == null) msgs = notification; else msgs.add(notification);" + NL + "\t\t}" + NL + "\t\treturn msgs;";
  protected final String TEXT_1004 = NL + "\t\t// TODO: implement this method to unset the contained '";
  protected final String TEXT_1005 = "' ";
  protected final String TEXT_1006 = NL + "\t\t// -> this method is automatically invoked to keep the containment relationship in synch" + NL + "\t\t// -> do not modify other features" + NL + "\t\t// -> return msgs, after adding any generated Notification to it (if it is null, a NotificationChain object must be created first)" + NL + "\t\t// Ensure that you remove @generated or mark it @generated NOT" + NL + "\t\tthrow new UnsupportedOperationException();";
  protected final String TEXT_1007 = NL + "\t}" + NL;
  protected final String TEXT_1008 = NL + "\t/**" + NL + "\t * Unsets the value of the '{@link ";
  protected final String TEXT_1009 = "#";
  protected final String TEXT_1010 = " <em>";
  protected final String TEXT_1011 = "</em>}' ";
  protected final String TEXT_1012 = ".";
  protected final String TEXT_1013 = NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->";
  protected final String TEXT_1014 = NL + "\t * @see #isSet";
  protected final String TEXT_1015 = "()";
  protected final String TEXT_1016 = NL + "\t * @see #";
  protected final String TEXT_1017 = "()";
  protected final String TEXT_1018 = NL + "\t * @see #set";
  protected final String TEXT_1019 = "(";
  protected final String TEXT_1020 = ")";
  protected final String TEXT_1021 = NL + "\t * @generated" + NL + "\t */";
  protected final String TEXT_1022 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */";
  protected final String TEXT_1023 = NL + "\tvoid unset";
  protected final String TEXT_1024 = "();" + NL;
  protected final String TEXT_1025 = NL + "\tpublic void unset";
  protected final String TEXT_1026 = "_";
  protected final String TEXT_1027 = "()" + NL + "\t{";
  protected final String TEXT_1028 = NL + "\t\teDynamicUnset(";
  protected final String TEXT_1029 = ", ";
  protected final String TEXT_1030 = ");";
  protected final String TEXT_1031 = NL + "\t\teUnset(";
  protected final String TEXT_1032 = ");";
  protected final String TEXT_1033 = NL + "\t\t";
  protected final String TEXT_1034 = "__ESETTING_DELEGATE.dynamicUnset(this, null, 0);";
  protected final String TEXT_1035 = NL + "\t\t";
  protected final String TEXT_1036 = " ";
  protected final String TEXT_1037 = " = (";
  protected final String TEXT_1038 = ")eVirtualGet(";
  protected final String TEXT_1039 = ");";
  protected final String TEXT_1040 = NL + "\t\tif (";
  protected final String TEXT_1041 = " != null) ((";
  protected final String TEXT_1042 = ".Unsettable";
  protected final String TEXT_1043 = ")";
  protected final String TEXT_1044 = ").unset();";
  protected final String TEXT_1045 = NL + "\t\t";
  protected final String TEXT_1046 = " ";
  protected final String TEXT_1047 = " = (";
  protected final String TEXT_1048 = ")eVirtualGet(";
  protected final String TEXT_1049 = ");";
  protected final String TEXT_1050 = NL + "\t\tif (";
  protected final String TEXT_1051 = " != null)" + NL + "\t\t{" + NL + "\t\t\t";
  protected final String TEXT_1052 = " msgs = null;";
  protected final String TEXT_1053 = NL + "\t\t\tmsgs = ((";
  protected final String TEXT_1054 = ")";
  protected final String TEXT_1055 = ").eInverseRemove(this, EOPPOSITE_FEATURE_BASE - ";
  protected final String TEXT_1056 = ", null, msgs);";
  protected final String TEXT_1057 = NL + "\t\t\tmsgs = ((";
  protected final String TEXT_1058 = ")";
  protected final String TEXT_1059 = ").eInverseRemove(this, ";
  protected final String TEXT_1060 = ", ";
  protected final String TEXT_1061 = ".class, msgs);";
  protected final String TEXT_1062 = NL + "\t\t\tmsgs = basicUnset";
  protected final String TEXT_1063 = "(msgs);" + NL + "\t\t\tif (msgs != null) msgs.dispatch();" + NL + "\t\t}" + NL + "\t\telse" + NL + "\t\t{";
  protected final String TEXT_1064 = NL + "\t\t\tboolean old";
  protected final String TEXT_1065 = "ESet = eVirtualIsSet(";
  protected final String TEXT_1066 = ");";
  protected final String TEXT_1067 = NL + "\t\t\tboolean old";
  protected final String TEXT_1068 = "ESet = (";
  protected final String TEXT_1069 = " & ";
  protected final String TEXT_1070 = "_ESETFLAG) != 0;";
  protected final String TEXT_1071 = NL + "\t\t\t";
  protected final String TEXT_1072 = " &= ~";
  protected final String TEXT_1073 = "_ESETFLAG;";
  protected final String TEXT_1074 = NL + "\t\t\tboolean old";
  protected final String TEXT_1075 = "ESet = ";
  protected final String TEXT_1076 = "ESet;";
  protected final String TEXT_1077 = NL + "\t\t\t";
  protected final String TEXT_1078 = "ESet = false;";
  protected final String TEXT_1079 = NL + "\t\t\tif (eNotificationRequired())" + NL + "\t\t\t\teNotify(new ";
  protected final String TEXT_1080 = "(this, ";
  protected final String TEXT_1081 = ".UNSET, ";
  protected final String TEXT_1082 = ", null, null, old";
  protected final String TEXT_1083 = "ESet));";
  protected final String TEXT_1084 = NL + "\t\t}";
  protected final String TEXT_1085 = NL + "\t\t";
  protected final String TEXT_1086 = " old";
  protected final String TEXT_1087 = " = (";
  protected final String TEXT_1088 = " & ";
  protected final String TEXT_1089 = "_EFLAG) != 0;";
  protected final String TEXT_1090 = NL + "\t\t";
  protected final String TEXT_1091 = " old";
  protected final String TEXT_1092 = " = ";
  protected final String TEXT_1093 = "_EFLAG_VALUES[(";
  protected final String TEXT_1094 = " & ";
  protected final String TEXT_1095 = "_EFLAG) >>> ";
  protected final String TEXT_1096 = "_EFLAG_OFFSET];";
  protected final String TEXT_1097 = NL + "\t\tObject old";
  protected final String TEXT_1098 = " = eVirtualUnset(";
  protected final String TEXT_1099 = ");";
  protected final String TEXT_1100 = NL + "\t\t";
  protected final String TEXT_1101 = " old";
  protected final String TEXT_1102 = " = ";
  protected final String TEXT_1103 = ";";
  protected final String TEXT_1104 = NL + "\t\tboolean isSetChange = old";
  protected final String TEXT_1105 = " != EVIRTUAL_NO_VALUE;";
  protected final String TEXT_1106 = NL + "\t\tboolean old";
  protected final String TEXT_1107 = "ESet = (";
  protected final String TEXT_1108 = " & ";
  protected final String TEXT_1109 = "_ESETFLAG) != 0;";
  protected final String TEXT_1110 = NL + "\t\tboolean old";
  protected final String TEXT_1111 = "ESet = ";
  protected final String TEXT_1112 = "ESet;";
  protected final String TEXT_1113 = NL + "\t\t";
  protected final String TEXT_1114 = " = null;";
  protected final String TEXT_1115 = NL + "\t\t";
  protected final String TEXT_1116 = " &= ~";
  protected final String TEXT_1117 = "_ESETFLAG;";
  protected final String TEXT_1118 = NL + "\t\t";
  protected final String TEXT_1119 = "ESet = false;";
  protected final String TEXT_1120 = NL + "\t\tif (eNotificationRequired())" + NL + "\t\t\teNotify(new ";
  protected final String TEXT_1121 = "(this, ";
  protected final String TEXT_1122 = ".UNSET, ";
  protected final String TEXT_1123 = ", ";
  protected final String TEXT_1124 = "isSetChange ? old";
  protected final String TEXT_1125 = " : null";
  protected final String TEXT_1126 = "old";
  protected final String TEXT_1127 = ", null, ";
  protected final String TEXT_1128 = "isSetChange";
  protected final String TEXT_1129 = "old";
  protected final String TEXT_1130 = "ESet";
  protected final String TEXT_1131 = "));";
  protected final String TEXT_1132 = NL + "\t\tif (";
  protected final String TEXT_1133 = ") ";
  protected final String TEXT_1134 = " |= ";
  protected final String TEXT_1135 = "_EFLAG; else ";
  protected final String TEXT_1136 = " &= ~";
  protected final String TEXT_1137 = "_EFLAG;";
  protected final String TEXT_1138 = NL + "\t\t";
  protected final String TEXT_1139 = " = ";
  protected final String TEXT_1140 = " & ~";
  protected final String TEXT_1141 = "_EFLAG | ";
  protected final String TEXT_1142 = "_EFLAG_DEFAULT;";
  protected final String TEXT_1143 = NL + "\t\t";
  protected final String TEXT_1144 = " = ";
  protected final String TEXT_1145 = ";";
  protected final String TEXT_1146 = NL + "\t\t";
  protected final String TEXT_1147 = " &= ~";
  protected final String TEXT_1148 = "_ESETFLAG;";
  protected final String TEXT_1149 = NL + "\t\t";
  protected final String TEXT_1150 = "ESet = false;";
  protected final String TEXT_1151 = NL + "\t\tif (eNotificationRequired())" + NL + "\t\t\teNotify(new ";
  protected final String TEXT_1152 = "(this, ";
  protected final String TEXT_1153 = ".UNSET, ";
  protected final String TEXT_1154 = ", ";
  protected final String TEXT_1155 = "isSetChange ? old";
  protected final String TEXT_1156 = " : ";
  protected final String TEXT_1157 = "old";
  protected final String TEXT_1158 = ", ";
  protected final String TEXT_1159 = ", ";
  protected final String TEXT_1160 = "isSetChange";
  protected final String TEXT_1161 = "old";
  protected final String TEXT_1162 = "ESet";
  protected final String TEXT_1163 = "));";
  protected final String TEXT_1164 = NL + "\t\t((";
  protected final String TEXT_1165 = ".Internal)((";
  protected final String TEXT_1166 = ".Internal.Wrapper)get";
  protected final String TEXT_1167 = "()).featureMap()).clear(";
  protected final String TEXT_1168 = ");";
  protected final String TEXT_1169 = NL + "\t\t((";
  protected final String TEXT_1170 = ".Internal)get";
  protected final String TEXT_1171 = "()).clear(";
  protected final String TEXT_1172 = ");";
  protected final String TEXT_1173 = NL + "\t\t";
  protected final String TEXT_1174 = NL + "\t\t// TODO: implement this method to unset the '";
  protected final String TEXT_1175 = "' ";
  protected final String TEXT_1176 = NL + "\t\t// Ensure that you remove @generated or mark it @generated NOT" + NL + "\t\tthrow new UnsupportedOperationException();";
  protected final String TEXT_1177 = NL + "\t}" + NL;
  protected final String TEXT_1178 = NL + "\t/**" + NL + "\t * Returns whether the value of the '{@link ";
  protected final String TEXT_1179 = "#";
  protected final String TEXT_1180 = " <em>";
  protected final String TEXT_1181 = "</em>}' ";
  protected final String TEXT_1182 = " is set.";
  protected final String TEXT_1183 = NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @return whether the value of the '<em>";
  protected final String TEXT_1184 = "</em>' ";
  protected final String TEXT_1185 = " is set.";
  protected final String TEXT_1186 = NL + "\t * @see #unset";
  protected final String TEXT_1187 = "()";
  protected final String TEXT_1188 = NL + "\t * @see #";
  protected final String TEXT_1189 = "()";
  protected final String TEXT_1190 = NL + "\t * @see #set";
  protected final String TEXT_1191 = "(";
  protected final String TEXT_1192 = ")";
  protected final String TEXT_1193 = NL + "\t * @generated" + NL + "\t */";
  protected final String TEXT_1194 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */";
  protected final String TEXT_1195 = NL + "\tboolean isSet";
  protected final String TEXT_1196 = "();" + NL;
  protected final String TEXT_1197 = NL + "\tpublic boolean isSet";
  protected final String TEXT_1198 = "_";
  protected final String TEXT_1199 = "()" + NL + "\t{";
  protected final String TEXT_1200 = NL + "\t\treturn eDynamicIsSet(";
  protected final String TEXT_1201 = ", ";
  protected final String TEXT_1202 = ");";
  protected final String TEXT_1203 = NL + "\t\treturn eIsSet(";
  protected final String TEXT_1204 = ");";
  protected final String TEXT_1205 = NL + "\t\treturn ";
  protected final String TEXT_1206 = "__ESETTING_DELEGATE.dynamicIsSet(this, null, 0);";
  protected final String TEXT_1207 = NL + "\t\t";
  protected final String TEXT_1208 = " ";
  protected final String TEXT_1209 = " = (";
  protected final String TEXT_1210 = ")eVirtualGet(";
  protected final String TEXT_1211 = ");";
  protected final String TEXT_1212 = NL + "\t\treturn ";
  protected final String TEXT_1213 = " != null && ((";
  protected final String TEXT_1214 = ".Unsettable";
  protected final String TEXT_1215 = ")";
  protected final String TEXT_1216 = ").isSet();";
  protected final String TEXT_1217 = NL + "\t\treturn eVirtualIsSet(";
  protected final String TEXT_1218 = ");";
  protected final String TEXT_1219 = NL + "\t\treturn (";
  protected final String TEXT_1220 = " & ";
  protected final String TEXT_1221 = "_ESETFLAG) != 0;";
  protected final String TEXT_1222 = NL + "\t\treturn ";
  protected final String TEXT_1223 = "ESet;";
  protected final String TEXT_1224 = NL + "\t\treturn !((";
  protected final String TEXT_1225 = ".Internal)((";
  protected final String TEXT_1226 = ".Internal.Wrapper)get";
  protected final String TEXT_1227 = "()).featureMap()).isEmpty(";
  protected final String TEXT_1228 = ");";
  protected final String TEXT_1229 = NL + "\t\treturn !((";
  protected final String TEXT_1230 = ".Internal)get";
  protected final String TEXT_1231 = "()).isEmpty(";
  protected final String TEXT_1232 = ");";
  protected final String TEXT_1233 = NL + "\t\t";
  protected final String TEXT_1234 = NL + "\t\t// TODO: implement this method to return whether the '";
  protected final String TEXT_1235 = "' ";
  protected final String TEXT_1236 = " is set" + NL + "\t\t// Ensure that you remove @generated or mark it @generated NOT" + NL + "\t\tthrow new UnsupportedOperationException();";
  protected final String TEXT_1237 = NL + "\t}" + NL;
  protected final String TEXT_1238 = NL + "\t/**" + NL + "\t * The cached validation expression for the '{@link #";
  protected final String TEXT_1239 = "(";
  protected final String TEXT_1240 = ") <em>";
  protected final String TEXT_1241 = "</em>}' invariant operation." + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @see #";
  protected final String TEXT_1242 = "(";
  protected final String TEXT_1243 = ")" + NL + "\t * @generated" + NL + "\t * @ordered" + NL + "\t */" + NL + "\tprotected static final ";
  protected final String TEXT_1244 = " ";
  protected final String TEXT_1245 = "__EEXPRESSION = \"";
  protected final String TEXT_1246 = "\";";
  protected final String TEXT_1247 = NL;
  protected final String TEXT_1248 = NL + "\t/**" + NL + "\t * The cached invocation delegate for the '{@link #";
  protected final String TEXT_1249 = "(";
  protected final String TEXT_1250 = ") <em>";
  protected final String TEXT_1251 = "</em>}' operation." + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @see #";
  protected final String TEXT_1252 = "(";
  protected final String TEXT_1253 = ")" + NL + "\t * @generated" + NL + "\t * @ordered" + NL + "\t */" + NL + "\tprotected static final ";
  protected final String TEXT_1254 = ".Internal.InvocationDelegate ";
  protected final String TEXT_1255 = "__EINVOCATION_DELEGATE = ((";
  protected final String TEXT_1256 = ".Internal)";
  protected final String TEXT_1257 = ").getInvocationDelegate();" + NL;
  protected final String TEXT_1258 = NL + "\t/**";
  protected final String TEXT_1259 = NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->";
  protected final String TEXT_1260 = NL + "\t * <!-- begin-model-doc -->";
  protected final String TEXT_1261 = NL + "\t * ";
  protected final String TEXT_1262 = NL + "\t * @param ";
  protected final String TEXT_1263 = NL + "\t *   ";
  protected final String TEXT_1264 = NL + "\t * @param ";
  protected final String TEXT_1265 = " ";
  protected final String TEXT_1266 = NL + "\t * <!-- end-model-doc -->";
  protected final String TEXT_1267 = NL + "\t * @model ";
  protected final String TEXT_1268 = NL + "\t *        ";
  protected final String TEXT_1269 = NL + "\t * @model";
  protected final String TEXT_1270 = NL + "\t * @generated" + NL + "\t */";
  protected final String TEXT_1271 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */";
  protected final String TEXT_1272 = NL + "\t";
  protected final String TEXT_1273 = " ";
  protected final String TEXT_1274 = "(";
  protected final String TEXT_1275 = ")";
  protected final String TEXT_1276 = ";" + NL;
  protected final String TEXT_1277 = NL + "\t@SuppressWarnings(\"unchecked\")";
  protected final String TEXT_1278 = NL + "\tpublic ";
  protected final String TEXT_1279 = " ";
  protected final String TEXT_1280 = "(";
  protected final String TEXT_1281 = ")";
  protected final String TEXT_1282 = NL + "\t{";
  protected final String TEXT_1283 = NL + "\t\t";
  protected final String TEXT_1284 = NL + "\t\treturn" + NL + "\t\t\t";
  protected final String TEXT_1285 = ".validate" + NL + "\t\t\t\t(";
  protected final String TEXT_1286 = "," + NL + "\t\t\t\t this," + NL + "\t\t\t\t ";
  protected final String TEXT_1287 = "," + NL + "\t\t\t\t ";
  protected final String TEXT_1288 = "," + NL + "\t\t\t\t \"";
  protected final String TEXT_1289 = "\",";
  protected final String TEXT_1290 = NL + "\t\t\t\t ";
  protected final String TEXT_1291 = "," + NL + "\t\t\t\t ";
  protected final String TEXT_1292 = "__EEXPRESSION," + NL + "\t\t\t\t ";
  protected final String TEXT_1293 = ".ERROR," + NL + "\t\t\t\t ";
  protected final String TEXT_1294 = ".DIAGNOSTIC_SOURCE," + NL + "\t\t\t\t ";
  protected final String TEXT_1295 = ".";
  protected final String TEXT_1296 = ");";
  protected final String TEXT_1297 = NL + "\t\t// TODO: implement this method" + NL + "\t\t// -> specify the condition that violates the invariant" + NL + "\t\t// -> verify the details of the diagnostic, including severity and message" + NL + "\t\t// Ensure that you remove @generated or mark it @generated NOT" + NL + "\t\tif (false)" + NL + "\t\t{" + NL + "\t\t\tif (";
  protected final String TEXT_1298 = " != null)" + NL + "\t\t\t{" + NL + "\t\t\t\t";
  protected final String TEXT_1299 = ".add" + NL + "\t\t\t\t\t(new ";
  protected final String TEXT_1300 = NL + "\t\t\t\t\t\t(";
  protected final String TEXT_1301 = ".ERROR," + NL + "\t\t\t\t\t\t ";
  protected final String TEXT_1302 = ".DIAGNOSTIC_SOURCE," + NL + "\t\t\t\t\t\t ";
  protected final String TEXT_1303 = ".";
  protected final String TEXT_1304 = "," + NL + "\t\t\t\t\t\t ";
  protected final String TEXT_1305 = ".INSTANCE.getString(\"_UI_GenericInvariant_diagnostic\", new Object[] { \"";
  protected final String TEXT_1306 = "\", ";
  protected final String TEXT_1307 = ".getObjectLabel(this, ";
  protected final String TEXT_1308 = ") }),";
  protected final String TEXT_1309 = NL + "\t\t\t\t\t\t new Object [] { this }));" + NL + "\t\t\t}" + NL + "\t\t\treturn false;" + NL + "\t\t}" + NL + "\t\treturn true;";
  protected final String TEXT_1310 = NL + "\t\ttry" + NL + "\t\t{";
  protected final String TEXT_1311 = NL + "\t\t\t";
  protected final String TEXT_1312 = "__EINVOCATION_DELEGATE.dynamicInvoke(this, ";
  protected final String TEXT_1313 = "new ";
  protected final String TEXT_1314 = ".UnmodifiableEList<Object>(";
  protected final String TEXT_1315 = ", ";
  protected final String TEXT_1316 = ")";
  protected final String TEXT_1317 = "null";
  protected final String TEXT_1318 = ");";
  protected final String TEXT_1319 = NL + "\t\t\treturn ";
  protected final String TEXT_1320 = "(";
  protected final String TEXT_1321 = "(";
  protected final String TEXT_1322 = ")";
  protected final String TEXT_1323 = "__EINVOCATION_DELEGATE.dynamicInvoke(this, ";
  protected final String TEXT_1324 = "new ";
  protected final String TEXT_1325 = ".UnmodifiableEList<Object>(";
  protected final String TEXT_1326 = ", ";
  protected final String TEXT_1327 = ")";
  protected final String TEXT_1328 = "null";
  protected final String TEXT_1329 = ")";
  protected final String TEXT_1330 = ").";
  protected final String TEXT_1331 = "()";
  protected final String TEXT_1332 = ";";
  protected final String TEXT_1333 = NL + "\t\t}" + NL + "\t\tcatch (";
  protected final String TEXT_1334 = " ite)" + NL + "\t\t{" + NL + "\t\t\tthrow new ";
  protected final String TEXT_1335 = "(ite);" + NL + "\t\t}";
  protected final String TEXT_1336 = NL + "\t\t// TODO: implement this method" + NL + "\t\t// Ensure that you remove @generated or mark it @generated NOT" + NL + "\t\tthrow new UnsupportedOperationException();";
  protected final String TEXT_1337 = NL + "\t}" + NL;
  protected final String TEXT_1338 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */";
  protected final String TEXT_1339 = NL + "\t@SuppressWarnings(\"unchecked\")";
  protected final String TEXT_1340 = NL + "\t@Override";
  protected final String TEXT_1341 = NL + "\tpublic ";
  protected final String TEXT_1342 = " eInverseAdd(";
  protected final String TEXT_1343 = " otherEnd, int featureID, ";
  protected final String TEXT_1344 = " msgs)" + NL + "\t{" + NL + "\t\tswitch (featureID";
  protected final String TEXT_1345 = ")" + NL + "\t\t{";
  protected final String TEXT_1346 = NL + "\t\t\tcase ";
  protected final String TEXT_1347 = ":";
  protected final String TEXT_1348 = NL + "\t\t\t\treturn ((";
  protected final String TEXT_1349 = "(";
  protected final String TEXT_1350 = ".InternalMapView";
  protected final String TEXT_1351 = ")";
  protected final String TEXT_1352 = "()).eMap()).basicAdd(otherEnd, msgs);";
  protected final String TEXT_1353 = NL + "\t\t\t\treturn (";
  protected final String TEXT_1354 = "()).basicAdd(otherEnd, msgs);";
  protected final String TEXT_1355 = NL + "\t\t\t\tif (eInternalContainer() != null)" + NL + "\t\t\t\t\tmsgs = eBasicRemoveFromContainer(msgs);";
  protected final String TEXT_1356 = NL + "\t\t\t\treturn basicSet";
  protected final String TEXT_1357 = "((";
  protected final String TEXT_1358 = ")otherEnd, msgs);";
  protected final String TEXT_1359 = NL + "\t\t\t\treturn eBasicSetContainer(otherEnd, ";
  protected final String TEXT_1360 = ", msgs);";
  protected final String TEXT_1361 = NL + "\t\t\t\t";
  protected final String TEXT_1362 = " ";
  protected final String TEXT_1363 = " = (";
  protected final String TEXT_1364 = ")eVirtualGet(";
  protected final String TEXT_1365 = ");";
  protected final String TEXT_1366 = NL + "\t\t\t\t";
  protected final String TEXT_1367 = " ";
  protected final String TEXT_1368 = " = ";
  protected final String TEXT_1369 = "basicGet";
  protected final String TEXT_1370 = "();";
  protected final String TEXT_1371 = NL + "\t\t\t\tif (";
  protected final String TEXT_1372 = " != null)";
  protected final String TEXT_1373 = NL + "\t\t\t\t\tmsgs = ((";
  protected final String TEXT_1374 = ")";
  protected final String TEXT_1375 = ").eInverseRemove(this, EOPPOSITE_FEATURE_BASE - ";
  protected final String TEXT_1376 = ", null, msgs);";
  protected final String TEXT_1377 = NL + "\t\t\t\t\tmsgs = ((";
  protected final String TEXT_1378 = ")";
  protected final String TEXT_1379 = ").eInverseRemove(this, ";
  protected final String TEXT_1380 = ", ";
  protected final String TEXT_1381 = ".class, msgs);";
  protected final String TEXT_1382 = NL + "\t\t\t\treturn basicSet";
  protected final String TEXT_1383 = "((";
  protected final String TEXT_1384 = ")otherEnd, msgs);";
  protected final String TEXT_1385 = NL + "\t\t}";
  protected final String TEXT_1386 = NL + "\t\treturn super.eInverseAdd(otherEnd, featureID, msgs);";
  protected final String TEXT_1387 = NL + "\t\treturn eDynamicInverseAdd(otherEnd, featureID, msgs);";
  protected final String TEXT_1388 = NL + "\t}" + NL;
  protected final String TEXT_1389 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */";
  protected final String TEXT_1390 = NL + "\t@Override";
  protected final String TEXT_1391 = NL + "\tpublic ";
  protected final String TEXT_1392 = " eInverseRemove(";
  protected final String TEXT_1393 = " otherEnd, int featureID, ";
  protected final String TEXT_1394 = " msgs)" + NL + "\t{" + NL + "\t\tswitch (featureID";
  protected final String TEXT_1395 = ")" + NL + "\t\t{";
  protected final String TEXT_1396 = NL + "\t\t\tcase ";
  protected final String TEXT_1397 = ":";
  protected final String TEXT_1398 = NL + "\t\t\t\treturn ((";
  protected final String TEXT_1399 = ")((";
  protected final String TEXT_1400 = ".InternalMapView";
  protected final String TEXT_1401 = ")";
  protected final String TEXT_1402 = "()).eMap()).basicRemove(otherEnd, msgs);";
  protected final String TEXT_1403 = NL + "\t\t\t\treturn ((";
  protected final String TEXT_1404 = ")((";
  protected final String TEXT_1405 = ".Internal.Wrapper)";
  protected final String TEXT_1406 = "()).featureMap()).basicRemove(otherEnd, msgs);";
  protected final String TEXT_1407 = NL + "\t\t\t\treturn ((";
  protected final String TEXT_1408 = ")";
  protected final String TEXT_1409 = "()).basicRemove(otherEnd, msgs);";
  protected final String TEXT_1410 = NL + "\t\t\t\treturn eBasicSetContainer(null, ";
  protected final String TEXT_1411 = ", msgs);";
  protected final String TEXT_1412 = NL + "\t\t\t\treturn basicUnset";
  protected final String TEXT_1413 = "(msgs);";
  protected final String TEXT_1414 = NL + "\t\t\t\treturn basicSet";
  protected final String TEXT_1415 = "(null, msgs);";
  protected final String TEXT_1416 = NL + "\t\t}";
  protected final String TEXT_1417 = NL + "\t\treturn super.eInverseRemove(otherEnd, featureID, msgs);";
  protected final String TEXT_1418 = NL + "\t\treturn eDynamicInverseRemove(otherEnd, featureID, msgs);";
  protected final String TEXT_1419 = NL + "\t}" + NL;
  protected final String TEXT_1420 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */";
  protected final String TEXT_1421 = NL + "\t@Override";
  protected final String TEXT_1422 = NL + "\tpublic ";
  protected final String TEXT_1423 = " eBasicRemoveFromContainerFeature(";
  protected final String TEXT_1424 = " msgs)" + NL + "\t{" + NL + "\t\tswitch (eContainerFeatureID()";
  protected final String TEXT_1425 = ")" + NL + "\t\t{";
  protected final String TEXT_1426 = NL + "\t\t\tcase ";
  protected final String TEXT_1427 = ":" + NL + "\t\t\t\treturn eInternalContainer().eInverseRemove(this, ";
  protected final String TEXT_1428 = ", ";
  protected final String TEXT_1429 = ".class, msgs);";
  protected final String TEXT_1430 = NL + "\t\t}";
  protected final String TEXT_1431 = NL + "\t\treturn super.eBasicRemoveFromContainerFeature(msgs);";
  protected final String TEXT_1432 = NL + "\t\treturn eDynamicBasicRemoveFromContainer(msgs);";
  protected final String TEXT_1433 = NL + "\t}" + NL;
  protected final String TEXT_1434 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */";
  protected final String TEXT_1435 = NL + "\t@Override";
  protected final String TEXT_1436 = NL + "\tpublic Object eGet(int featureID, boolean resolve, boolean coreType)" + NL + "\t{" + NL + "\t\tswitch (featureID";
  protected final String TEXT_1437 = ")" + NL + "\t\t{";
  protected final String TEXT_1438 = NL + "\t\t\tcase ";
  protected final String TEXT_1439 = ":";
  protected final String TEXT_1440 = NL + "\t\t\t\treturn ";
  protected final String TEXT_1441 = "();";
  protected final String TEXT_1442 = NL + "\t\t\t\treturn ";
  protected final String TEXT_1443 = "() ? Boolean.TRUE : Boolean.FALSE;";
  protected final String TEXT_1444 = NL + "\t\t\t\treturn new ";
  protected final String TEXT_1445 = "(";
  protected final String TEXT_1446 = "());";
  protected final String TEXT_1447 = NL + "\t\t\t\tif (resolve) return ";
  protected final String TEXT_1448 = "();" + NL + "\t\t\t\treturn basicGet";
  protected final String TEXT_1449 = "();";
  protected final String TEXT_1450 = NL + "\t\t\t\tif (coreType) return ((";
  protected final String TEXT_1451 = ".InternalMapView";
  protected final String TEXT_1452 = ")";
  protected final String TEXT_1453 = "()).eMap();" + NL + "\t\t\t\telse return ";
  protected final String TEXT_1454 = "();";
  protected final String TEXT_1455 = NL + "\t\t\t\tif (coreType) return ";
  protected final String TEXT_1456 = "();" + NL + "\t\t\t\telse return ";
  protected final String TEXT_1457 = "().map();";
  protected final String TEXT_1458 = NL + "\t\t\t\tif (coreType) return ((";
  protected final String TEXT_1459 = ".Internal.Wrapper)";
  protected final String TEXT_1460 = "()).featureMap();" + NL + "\t\t\t\treturn ";
  protected final String TEXT_1461 = "();";
  protected final String TEXT_1462 = NL + "\t\t\t\tif (coreType) return ";
  protected final String TEXT_1463 = "();" + NL + "\t\t\t\treturn ((";
  protected final String TEXT_1464 = ".Internal)";
  protected final String TEXT_1465 = "()).getWrapper();";
  protected final String TEXT_1466 = NL + "\t\t\t\treturn ";
  protected final String TEXT_1467 = "();";
  protected final String TEXT_1468 = NL + "\t\t}";
  protected final String TEXT_1469 = NL + "\t\treturn super.eGet(featureID, resolve, coreType);";
  protected final String TEXT_1470 = NL + "\t\treturn eDynamicGet(featureID, resolve, coreType);";
  protected final String TEXT_1471 = NL + "\t}" + NL;
  protected final String TEXT_1472 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */";
  protected final String TEXT_1473 = NL + "\t@SuppressWarnings(\"unchecked\")";
  protected final String TEXT_1474 = NL + "\t@Override";
  protected final String TEXT_1475 = NL + "\tpublic void eSet(int featureID, Object newValue)" + NL + "\t{" + NL + "\t\tswitch (featureID";
  protected final String TEXT_1476 = ")" + NL + "\t\t{";
  protected final String TEXT_1477 = NL + "\t\t\tcase ";
  protected final String TEXT_1478 = ":";
  protected final String TEXT_1479 = NL + "\t\t\t\t((";
  protected final String TEXT_1480 = ".Internal)((";
  protected final String TEXT_1481 = ".Internal.Wrapper)";
  protected final String TEXT_1482 = "()).featureMap()).set(newValue);";
  protected final String TEXT_1483 = NL + "\t\t\t\t((";
  protected final String TEXT_1484 = ".Internal)";
  protected final String TEXT_1485 = "()).set(newValue);";
  protected final String TEXT_1486 = NL + "\t\t\t\t((";
  protected final String TEXT_1487 = ".Setting)((";
  protected final String TEXT_1488 = ".InternalMapView";
  protected final String TEXT_1489 = ")";
  protected final String TEXT_1490 = "()).eMap()).set(newValue);";
  protected final String TEXT_1491 = NL + "\t\t\t\t((";
  protected final String TEXT_1492 = ".Setting)";
  protected final String TEXT_1493 = "()).set(newValue);";
  protected final String TEXT_1494 = NL + "\t\t\t\t";
  protected final String TEXT_1495 = "().clear();" + NL + "\t\t\t\t";
  protected final String TEXT_1496 = "().addAll((";
  protected final String TEXT_1497 = "<? extends ";
  protected final String TEXT_1498 = ">";
  protected final String TEXT_1499 = ")newValue);";
  protected final String TEXT_1500 = NL + "\t\t\t\tset";
  protected final String TEXT_1501 = "(((";
  protected final String TEXT_1502 = ")newValue).";
  protected final String TEXT_1503 = "());";
  protected final String TEXT_1504 = NL + "\t\t\t\tset";
  protected final String TEXT_1505 = "(";
  protected final String TEXT_1506 = "(";
  protected final String TEXT_1507 = ")";
  protected final String TEXT_1508 = "newValue);";
  protected final String TEXT_1509 = NL + "\t\t\t\treturn;";
  protected final String TEXT_1510 = NL + "\t\t}";
  protected final String TEXT_1511 = NL + "\t\tsuper.eSet(featureID, newValue);";
  protected final String TEXT_1512 = NL + "\t\teDynamicSet(featureID, newValue);";
  protected final String TEXT_1513 = NL + "\t}" + NL;
  protected final String TEXT_1514 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */";
  protected final String TEXT_1515 = NL + "\t@Override";
  protected final String TEXT_1516 = NL + "\tpublic void eUnset(int featureID)" + NL + "\t{" + NL + "\t\tswitch (featureID";
  protected final String TEXT_1517 = ")" + NL + "\t\t{";
  protected final String TEXT_1518 = NL + "\t\t\tcase ";
  protected final String TEXT_1519 = ":";
  protected final String TEXT_1520 = NL + "\t\t\t\t((";
  protected final String TEXT_1521 = ".Internal.Wrapper)";
  protected final String TEXT_1522 = "()).featureMap().clear();";
  protected final String TEXT_1523 = NL + "\t\t\t\t";
  protected final String TEXT_1524 = "().clear();";
  protected final String TEXT_1525 = NL + "\t\t\t\tunset";
  protected final String TEXT_1526 = "();";
  protected final String TEXT_1527 = NL + "\t\t\t\tset";
  protected final String TEXT_1528 = "((";
  protected final String TEXT_1529 = ")null);";
  protected final String TEXT_1530 = NL + "\t\t\t\t";
  protected final String TEXT_1531 = "__ESETTING_DELEGATE.dynamicUnset(this, null, 0);";
  protected final String TEXT_1532 = NL + "\t\t\t\tset";
  protected final String TEXT_1533 = "(";
  protected final String TEXT_1534 = ");";
  protected final String TEXT_1535 = NL + "\t\t\t\treturn;";
  protected final String TEXT_1536 = NL + "\t\t}";
  protected final String TEXT_1537 = NL + "\t\tsuper.eUnset(featureID);";
  protected final String TEXT_1538 = NL + "\t\teDynamicUnset(featureID);";
  protected final String TEXT_1539 = NL + "\t}" + NL;
  protected final String TEXT_1540 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */";
  protected final String TEXT_1541 = NL + "\t@SuppressWarnings(\"unchecked\")";
  protected final String TEXT_1542 = NL + "\t@Override";
  protected final String TEXT_1543 = NL + "\tpublic boolean eIsSet(int featureID)" + NL + "\t{" + NL + "\t\tswitch (featureID";
  protected final String TEXT_1544 = ")" + NL + "\t\t{";
  protected final String TEXT_1545 = NL + "\t\t\tcase ";
  protected final String TEXT_1546 = ":";
  protected final String TEXT_1547 = NL + "\t\t\t\treturn isSet";
  protected final String TEXT_1548 = "();";
  protected final String TEXT_1549 = NL + "\t\t\t\treturn ";
  protected final String TEXT_1550 = "__ESETTING_DELEGATE.dynamicIsSet(this, null, 0);";
  protected final String TEXT_1551 = NL + "\t\t\t\treturn !((";
  protected final String TEXT_1552 = ".Internal.Wrapper)";
  protected final String TEXT_1553 = "()).featureMap().isEmpty();";
  protected final String TEXT_1554 = NL + "\t\t\t\treturn ";
  protected final String TEXT_1555 = " != null && !";
  protected final String TEXT_1556 = ".featureMap().isEmpty();";
  protected final String TEXT_1557 = NL + "\t\t\t\treturn ";
  protected final String TEXT_1558 = " != null && !";
  protected final String TEXT_1559 = ".isEmpty();";
  protected final String TEXT_1560 = NL + "\t\t\t\t";
  protected final String TEXT_1561 = " ";
  protected final String TEXT_1562 = " = (";
  protected final String TEXT_1563 = ")eVirtualGet(";
  protected final String TEXT_1564 = ");" + NL + "\t\t\t\treturn ";
  protected final String TEXT_1565 = " != null && !";
  protected final String TEXT_1566 = ".isEmpty();";
  protected final String TEXT_1567 = NL + "\t\t\t\treturn !";
  protected final String TEXT_1568 = "().isEmpty();";
  protected final String TEXT_1569 = NL + "\t\t\t\treturn isSet";
  protected final String TEXT_1570 = "();";
  protected final String TEXT_1571 = NL + "\t\t\t\treturn ";
  protected final String TEXT_1572 = " != null;";
  protected final String TEXT_1573 = NL + "\t\t\t\treturn eVirtualGet(";
  protected final String TEXT_1574 = ") != null;";
  protected final String TEXT_1575 = NL + "\t\t\t\treturn basicGet";
  protected final String TEXT_1576 = "() != null;";
  protected final String TEXT_1577 = NL + "\t\t\t\treturn ";
  protected final String TEXT_1578 = " != null;";
  protected final String TEXT_1579 = NL + "\t\t\t\treturn eVirtualGet(";
  protected final String TEXT_1580 = ") != null;";
  protected final String TEXT_1581 = NL + "\t\t\t\treturn ";
  protected final String TEXT_1582 = "() != null;";
  protected final String TEXT_1583 = NL + "\t\t\t\treturn ((";
  protected final String TEXT_1584 = " & ";
  protected final String TEXT_1585 = "_EFLAG) != 0) != ";
  protected final String TEXT_1586 = ";";
  protected final String TEXT_1587 = NL + "\t\t\t\treturn (";
  protected final String TEXT_1588 = " & ";
  protected final String TEXT_1589 = "_EFLAG) != ";
  protected final String TEXT_1590 = "_EFLAG_DEFAULT;";
  protected final String TEXT_1591 = NL + "\t\t\t\treturn ";
  protected final String TEXT_1592 = " != ";
  protected final String TEXT_1593 = ";";
  protected final String TEXT_1594 = NL + "\t\t\t\treturn eVirtualGet(";
  protected final String TEXT_1595 = ", ";
  protected final String TEXT_1596 = ") != ";
  protected final String TEXT_1597 = ";";
  protected final String TEXT_1598 = NL + "\t\t\t\treturn ";
  protected final String TEXT_1599 = "() != ";
  protected final String TEXT_1600 = ";";
  protected final String TEXT_1601 = NL + "\t\t\t\treturn ";
  protected final String TEXT_1602 = " == null ? ";
  protected final String TEXT_1603 = " != null : !";
  protected final String TEXT_1604 = ".equals(";
  protected final String TEXT_1605 = ");";
  protected final String TEXT_1606 = NL + "\t\t\t\t";
  protected final String TEXT_1607 = " ";
  protected final String TEXT_1608 = " = (";
  protected final String TEXT_1609 = ")eVirtualGet(";
  protected final String TEXT_1610 = ", ";
  protected final String TEXT_1611 = ");" + NL + "\t\t\t\treturn ";
  protected final String TEXT_1612 = " == null ? ";
  protected final String TEXT_1613 = " != null : !";
  protected final String TEXT_1614 = ".equals(";
  protected final String TEXT_1615 = ");";
  protected final String TEXT_1616 = NL + "\t\t\t\treturn ";
  protected final String TEXT_1617 = " == null ? ";
  protected final String TEXT_1618 = "() != null : !";
  protected final String TEXT_1619 = ".equals(";
  protected final String TEXT_1620 = "());";
  protected final String TEXT_1621 = NL + "\t\t}";
  protected final String TEXT_1622 = NL + "\t\treturn super.eIsSet(featureID);";
  protected final String TEXT_1623 = NL + "\t\treturn eDynamicIsSet(featureID);";
  protected final String TEXT_1624 = NL + "\t}" + NL;
  protected final String TEXT_1625 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */";
  protected final String TEXT_1626 = NL + "\t@Override";
  protected final String TEXT_1627 = NL + "\tpublic int eBaseStructuralFeatureID(int derivedFeatureID, Class";
  protected final String TEXT_1628 = " baseClass)" + NL + "\t{";
  protected final String TEXT_1629 = NL + "\t\tif (baseClass == ";
  protected final String TEXT_1630 = ".class)" + NL + "\t\t{" + NL + "\t\t\tswitch (derivedFeatureID";
  protected final String TEXT_1631 = ")" + NL + "\t\t\t{";
  protected final String TEXT_1632 = NL + "\t\t\t\tcase ";
  protected final String TEXT_1633 = ": return ";
  protected final String TEXT_1634 = ";";
  protected final String TEXT_1635 = NL + "\t\t\t\tdefault: return -1;" + NL + "\t\t\t}" + NL + "\t\t}";
  protected final String TEXT_1636 = NL + "\t\treturn super.eBaseStructuralFeatureID(derivedFeatureID, baseClass);" + NL + "\t}";
  protected final String TEXT_1637 = NL + NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */";
  protected final String TEXT_1638 = NL + "\t@Override";
  protected final String TEXT_1639 = NL + "\tpublic int eDerivedStructuralFeatureID(int baseFeatureID, Class";
  protected final String TEXT_1640 = " baseClass)" + NL + "\t{";
  protected final String TEXT_1641 = NL + "\t\tif (baseClass == ";
  protected final String TEXT_1642 = ".class)" + NL + "\t\t{" + NL + "\t\t\tswitch (baseFeatureID)" + NL + "\t\t\t{";
  protected final String TEXT_1643 = NL + "\t\t\t\tcase ";
  protected final String TEXT_1644 = ": return ";
  protected final String TEXT_1645 = ";";
  protected final String TEXT_1646 = NL + "\t\t\t\tdefault: return -1;" + NL + "\t\t\t}" + NL + "\t\t}";
  protected final String TEXT_1647 = NL + "\t\tif (baseClass == ";
  protected final String TEXT_1648 = ".class)" + NL + "\t\t{" + NL + "\t\t\tswitch (baseFeatureID";
  protected final String TEXT_1649 = ")" + NL + "\t\t\t{";
  protected final String TEXT_1650 = NL + "\t\t\t\tcase ";
  protected final String TEXT_1651 = ": return ";
  protected final String TEXT_1652 = ";";
  protected final String TEXT_1653 = NL + "\t\t\t\tdefault: return -1;" + NL + "\t\t\t}" + NL + "\t\t}";
  protected final String TEXT_1654 = NL + "\t\treturn super.eDerivedStructuralFeatureID(baseFeatureID, baseClass);" + NL + "\t}" + NL;
  protected final String TEXT_1655 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */";
  protected final String TEXT_1656 = NL + "\t@Override";
  protected final String TEXT_1657 = NL + "\tpublic int eDerivedOperationID(int baseOperationID, Class";
  protected final String TEXT_1658 = " baseClass)" + NL + "\t{";
  protected final String TEXT_1659 = NL + "\t\tif (baseClass == ";
  protected final String TEXT_1660 = ".class)" + NL + "\t\t{" + NL + "\t\t\tswitch (baseOperationID)" + NL + "\t\t\t{";
  protected final String TEXT_1661 = NL + "\t\t\t\tcase ";
  protected final String TEXT_1662 = ": return ";
  protected final String TEXT_1663 = ";";
  protected final String TEXT_1664 = NL + "\t\t\t\tdefault: return super.eDerivedOperationID(baseOperationID, baseClass);" + NL + "\t\t\t}" + NL + "\t\t}";
  protected final String TEXT_1665 = NL + "\t\tif (baseClass == ";
  protected final String TEXT_1666 = ".class)" + NL + "\t\t{" + NL + "\t\t\tswitch (baseOperationID)" + NL + "\t\t\t{";
  protected final String TEXT_1667 = NL + "\t\t\t\tcase ";
  protected final String TEXT_1668 = ": return ";
  protected final String TEXT_1669 = ";";
  protected final String TEXT_1670 = NL + "\t\t\t\tdefault: return -1;" + NL + "\t\t\t}" + NL + "\t\t}";
  protected final String TEXT_1671 = NL + "\t\tif (baseClass == ";
  protected final String TEXT_1672 = ".class)" + NL + "\t\t{" + NL + "\t\t\tswitch (baseOperationID";
  protected final String TEXT_1673 = ")" + NL + "\t\t\t{";
  protected final String TEXT_1674 = NL + "\t\t\t\tcase ";
  protected final String TEXT_1675 = ": return ";
  protected final String TEXT_1676 = ";";
  protected final String TEXT_1677 = NL + "\t\t\t\tdefault: return -1;" + NL + "\t\t\t}" + NL + "\t\t}";
  protected final String TEXT_1678 = NL + "\t\treturn super.eDerivedOperationID(baseOperationID, baseClass);" + NL + "\t}" + NL;
  protected final String TEXT_1679 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */";
  protected final String TEXT_1680 = NL + "\t@Override";
  protected final String TEXT_1681 = NL + "\tprotected Object[] eVirtualValues()" + NL + "\t{" + NL + "\t\treturn ";
  protected final String TEXT_1682 = ";" + NL + "\t}" + NL + "" + NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */";
  protected final String TEXT_1683 = NL + "\t@Override";
  protected final String TEXT_1684 = NL + "\tprotected void eSetVirtualValues(Object[] newValues)" + NL + "\t{" + NL + "\t\t";
  protected final String TEXT_1685 = " = newValues;" + NL + "\t}" + NL;
  protected final String TEXT_1686 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */";
  protected final String TEXT_1687 = NL + "\t@Override";
  protected final String TEXT_1688 = NL + "\tprotected int eVirtualIndexBits(int offset)" + NL + "\t{" + NL + "\t\tswitch (offset)" + NL + "\t\t{";
  protected final String TEXT_1689 = NL + "\t\t\tcase ";
  protected final String TEXT_1690 = " :" + NL + "\t\t\t\treturn ";
  protected final String TEXT_1691 = ";";
  protected final String TEXT_1692 = NL + "\t\t\tdefault :" + NL + "\t\t\t\tthrow new IndexOutOfBoundsException();" + NL + "\t\t}" + NL + "\t}" + NL + "" + NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */";
  protected final String TEXT_1693 = NL + "\t@Override";
  protected final String TEXT_1694 = NL + "\tprotected void eSetVirtualIndexBits(int offset, int newIndexBits)" + NL + "\t{" + NL + "\t\tswitch (offset)" + NL + "\t\t{";
  protected final String TEXT_1695 = NL + "\t\t\tcase ";
  protected final String TEXT_1696 = " :" + NL + "\t\t\t\t";
  protected final String TEXT_1697 = " = newIndexBits;" + NL + "\t\t\t\tbreak;";
  protected final String TEXT_1698 = NL + "\t\t\tdefault :" + NL + "\t\t\t\tthrow new IndexOutOfBoundsException();" + NL + "\t\t}" + NL + "\t}" + NL;
  protected final String TEXT_1699 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */";
  protected final String TEXT_1700 = NL + "\t@Override";
  protected final String TEXT_1701 = NL + "\t@SuppressWarnings(";
  protected final String TEXT_1702 = "\"unchecked\"";
  protected final String TEXT_1703 = "{\"rawtypes\", \"unchecked\" }";
  protected final String TEXT_1704 = ")";
  protected final String TEXT_1705 = NL + "\tpublic Object eInvoke(int operationID, ";
  protected final String TEXT_1706 = " arguments) throws ";
  protected final String TEXT_1707 = NL + "\t{" + NL + "\t\tswitch (operationID";
  protected final String TEXT_1708 = ")" + NL + "\t\t{";
  protected final String TEXT_1709 = NL + "\t\t\tcase ";
  protected final String TEXT_1710 = ":";
  protected final String TEXT_1711 = NL + "\t\t\t\ttry" + NL + "\t\t\t\t{";
  protected final String TEXT_1712 = NL + "\t\t\t\t";
  protected final String TEXT_1713 = "(";
  protected final String TEXT_1714 = "(";
  protected final String TEXT_1715 = "(";
  protected final String TEXT_1716 = ")";
  protected final String TEXT_1717 = "arguments.get(";
  protected final String TEXT_1718 = ")";
  protected final String TEXT_1719 = ").";
  protected final String TEXT_1720 = "()";
  protected final String TEXT_1721 = ", ";
  protected final String TEXT_1722 = ");" + NL + "\t\t\t\t";
  protected final String TEXT_1723 = "return null;";
  protected final String TEXT_1724 = NL + "\t\t\t\t";
  protected final String TEXT_1725 = "return ";
  protected final String TEXT_1726 = "new ";
  protected final String TEXT_1727 = "(";
  protected final String TEXT_1728 = "(";
  protected final String TEXT_1729 = "(";
  protected final String TEXT_1730 = "(";
  protected final String TEXT_1731 = ")";
  protected final String TEXT_1732 = "arguments.get(";
  protected final String TEXT_1733 = ")";
  protected final String TEXT_1734 = ").";
  protected final String TEXT_1735 = "()";
  protected final String TEXT_1736 = ", ";
  protected final String TEXT_1737 = ")";
  protected final String TEXT_1738 = ")";
  protected final String TEXT_1739 = ";";
  protected final String TEXT_1740 = NL + "\t\t\t\t}" + NL + "\t\t\t\tcatch (";
  protected final String TEXT_1741 = " throwable)" + NL + "\t\t\t\t{" + NL + "\t\t\t\t\tthrow new ";
  protected final String TEXT_1742 = "(throwable);" + NL + "\t\t\t\t}";
  protected final String TEXT_1743 = NL + "\t\t}";
  protected final String TEXT_1744 = NL + "\t\treturn super.eInvoke(operationID, arguments);";
  protected final String TEXT_1745 = NL + "\t\treturn eDynamicInvoke(operationID, arguments);";
  protected final String TEXT_1746 = NL + "\t}" + NL;
  protected final String TEXT_1747 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */";
  protected final String TEXT_1748 = NL + "\t@Override";
  protected final String TEXT_1749 = NL + "\tpublic String toString()" + NL + "\t{" + NL + "\t\tif (eIsProxy()) return super.toString();" + NL + "" + NL + "\t\tStringBuffer result = new StringBuffer(super.toString());";
  protected final String TEXT_1750 = NL + "\t\tresult.append(\" (";
  protected final String TEXT_1751 = ": \");";
  protected final String TEXT_1752 = NL + "\t\tresult.append(\", ";
  protected final String TEXT_1753 = ": \");";
  protected final String TEXT_1754 = NL + "\t\tif (eVirtualIsSet(";
  protected final String TEXT_1755 = ")) result.append(eVirtualGet(";
  protected final String TEXT_1756 = ")); else result.append(\"<unset>\");";
  protected final String TEXT_1757 = NL + "\t\tif (";
  protected final String TEXT_1758 = "(";
  protected final String TEXT_1759 = " & ";
  protected final String TEXT_1760 = "_ESETFLAG) != 0";
  protected final String TEXT_1761 = "ESet";
  protected final String TEXT_1762 = ") result.append((";
  protected final String TEXT_1763 = " & ";
  protected final String TEXT_1764 = "_EFLAG) != 0); else result.append(\"<unset>\");";
  protected final String TEXT_1765 = NL + "\t\tif (";
  protected final String TEXT_1766 = "(";
  protected final String TEXT_1767 = " & ";
  protected final String TEXT_1768 = "_ESETFLAG) != 0";
  protected final String TEXT_1769 = "ESet";
  protected final String TEXT_1770 = ") result.append(";
  protected final String TEXT_1771 = "_EFLAG_VALUES[(";
  protected final String TEXT_1772 = " & ";
  protected final String TEXT_1773 = "_EFLAG) >>> ";
  protected final String TEXT_1774 = "_EFLAG_OFFSET]); else result.append(\"<unset>\");";
  protected final String TEXT_1775 = NL + "\t\tif (";
  protected final String TEXT_1776 = "(";
  protected final String TEXT_1777 = " & ";
  protected final String TEXT_1778 = "_ESETFLAG) != 0";
  protected final String TEXT_1779 = "ESet";
  protected final String TEXT_1780 = ") result.append(";
  protected final String TEXT_1781 = "); else result.append(\"<unset>\");";
  protected final String TEXT_1782 = NL + "\t\tresult.append(eVirtualGet(";
  protected final String TEXT_1783 = ", ";
  protected final String TEXT_1784 = "));";
  protected final String TEXT_1785 = NL + "\t\tresult.append((";
  protected final String TEXT_1786 = " & ";
  protected final String TEXT_1787 = "_EFLAG) != 0);";
  protected final String TEXT_1788 = NL + "\t\tresult.append(";
  protected final String TEXT_1789 = "_EFLAG_VALUES[(";
  protected final String TEXT_1790 = " & ";
  protected final String TEXT_1791 = "_EFLAG) >>> ";
  protected final String TEXT_1792 = "_EFLAG_OFFSET]);";
  protected final String TEXT_1793 = NL + "\t\tresult.append(";
  protected final String TEXT_1794 = ");";
  protected final String TEXT_1795 = NL + "\t\tresult.append(')');" + NL + "\t\treturn result.toString();" + NL + "\t}" + NL;
  protected final String TEXT_1796 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */";
  protected final String TEXT_1797 = NL + "\t@";
  protected final String TEXT_1798 = NL + "\tprotected int hash = -1;" + NL + "" + NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */" + NL + "\tpublic int getHash()" + NL + "\t{" + NL + "\t\tif (hash == -1)" + NL + "\t\t{" + NL + "\t\t\t";
  protected final String TEXT_1799 = " theKey = getKey();" + NL + "\t\t\thash = (theKey == null ? 0 : theKey.hashCode());" + NL + "\t\t}" + NL + "\t\treturn hash;" + NL + "\t}" + NL + "" + NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */" + NL + "\tpublic void setHash(int hash)" + NL + "\t{" + NL + "\t\tthis.hash = hash;" + NL + "\t}" + NL + "" + NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */" + NL + "\tpublic ";
  protected final String TEXT_1800 = " getKey()" + NL + "\t{";
  protected final String TEXT_1801 = NL + "\t\treturn new ";
  protected final String TEXT_1802 = "(getTypedKey());";
  protected final String TEXT_1803 = NL + "\t\treturn getTypedKey();";
  protected final String TEXT_1804 = NL + "\t}" + NL + "" + NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */" + NL + "\tpublic void setKey(";
  protected final String TEXT_1805 = " key)" + NL + "\t{";
  protected final String TEXT_1806 = NL + "\t\tgetTypedKey().addAll(";
  protected final String TEXT_1807 = "(";
  protected final String TEXT_1808 = ")";
  protected final String TEXT_1809 = "key);";
  protected final String TEXT_1810 = NL + "\t\tsetTypedKey(key);";
  protected final String TEXT_1811 = NL + "\t\tsetTypedKey(((";
  protected final String TEXT_1812 = ")key).";
  protected final String TEXT_1813 = "());";
  protected final String TEXT_1814 = NL + "\t\tsetTypedKey((";
  protected final String TEXT_1815 = ")key);";
  protected final String TEXT_1816 = NL + "\t}" + NL + "" + NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */" + NL + "\tpublic ";
  protected final String TEXT_1817 = " getValue()" + NL + "\t{";
  protected final String TEXT_1818 = NL + "\t\treturn new ";
  protected final String TEXT_1819 = "(getTypedValue());";
  protected final String TEXT_1820 = NL + "\t\treturn getTypedValue();";
  protected final String TEXT_1821 = NL + "\t}" + NL + "" + NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */" + NL + "\tpublic ";
  protected final String TEXT_1822 = " setValue(";
  protected final String TEXT_1823 = " value)" + NL + "\t{" + NL + "\t\t";
  protected final String TEXT_1824 = " oldValue = getValue();";
  protected final String TEXT_1825 = NL + "\t\tgetTypedValue().clear();" + NL + "\t\tgetTypedValue().addAll(";
  protected final String TEXT_1826 = "(";
  protected final String TEXT_1827 = ")";
  protected final String TEXT_1828 = "value);";
  protected final String TEXT_1829 = NL + "\t\tsetTypedValue(value);";
  protected final String TEXT_1830 = NL + "\t\tsetTypedValue(((";
  protected final String TEXT_1831 = ")value).";
  protected final String TEXT_1832 = "());";
  protected final String TEXT_1833 = NL + "\t\tsetTypedValue((";
  protected final String TEXT_1834 = ")value);";
  protected final String TEXT_1835 = NL + "\t\treturn oldValue;" + NL + "\t}" + NL + "" + NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */";
  protected final String TEXT_1836 = NL + "\t@SuppressWarnings(\"unchecked\")";
  protected final String TEXT_1837 = NL + "\tpublic ";
  protected final String TEXT_1838 = " getEMap()" + NL + "\t{" + NL + "\t\t";
  protected final String TEXT_1839 = " container = eContainer();" + NL + "\t\treturn container == null ? null : (";
  protected final String TEXT_1840 = ")container.eGet(eContainmentFeature());" + NL + "\t}" + NL;
  protected final String TEXT_1841 = NL + "} //";
  protected final String TEXT_1842 = NL;

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
    stringBuffer.append(TEXT_213);
    if (genModel.useClassOverrideAnnotation()) {
    stringBuffer.append(TEXT_214);
    }
    stringBuffer.append(TEXT_215);
    stringBuffer.append(genClass.getClassExtendsGenClass() == null ? 0 : genClass.getClassExtendsGenClass().getAllGenFeatures().size());
    stringBuffer.append(TEXT_216);
    }
    //Class/reflectiveDelegation.override.javajetinc
    if (isImplementation) {
    new Runnable() { public void run() { GenClass classExtendsGenClass = genClass.getClassExtendsGenClass(); List<GenFeature> classExtendsAllGenFeatures = classExtendsGenClass == null? Collections.<GenFeature>emptyList() : classExtendsGenClass.getAllGenFeatures();
    for (GenFeature genFeature : genClass.getReifiedGenFeatures()) {
    if (genModel.isArrayAccessors() && genFeature.isListType() && !genFeature.isFeatureMapType() && !genFeature.isMapType()) { String arrayElementType = genFeature.getArrayItemType(genClass);
    stringBuffer.append(TEXT_217);
    if (genModel.useGenerics() && CodeGenUtil.isUncheckedCast(arrayElementType)) {
    stringBuffer.append(TEXT_218);
    }
    if (classExtendsAllGenFeatures.contains(genFeature)) {
    stringBuffer.append(TEXT_219);
    }
    stringBuffer.append(TEXT_220);
    stringBuffer.append(arrayElementType);
    stringBuffer.append(TEXT_221);
    stringBuffer.append(genFeature.getGetArrayAccessor());
    stringBuffer.append(TEXT_222);
    if (genFeature.isVolatile()) {
    stringBuffer.append(TEXT_223);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.util.BasicEList"));
    stringBuffer.append(genFeature.getListTemplateArguments(genClass));
    stringBuffer.append(TEXT_224);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.util.BasicEList"));
    stringBuffer.append(genFeature.getListTemplateArguments(genClass));
    stringBuffer.append(TEXT_225);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_226);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_227);
    } else {
    stringBuffer.append(TEXT_228);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_229);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_230);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_231);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.util.BasicEList"));
    stringBuffer.append(genFeature.getListTemplateArguments(genClass));
    stringBuffer.append(TEXT_232);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.util.BasicEList"));
    stringBuffer.append(genFeature.getListTemplateArguments(genClass));
    stringBuffer.append(TEXT_233);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_234);
    }
    stringBuffer.append(TEXT_235);
    stringBuffer.append(arrayElementType);
    stringBuffer.append(TEXT_236);
    }
    if (genFeature.isGet() && genFeature.isListType()) {
    stringBuffer.append(TEXT_237);
    if (isJDK50) { //Class/getGenFeature.annotations.insert.javajetinc
    }
    if (genFeature.isListType() && genModel.isVirtualDelegation()) {
    stringBuffer.append(TEXT_238);
    }
    if (classExtendsAllGenFeatures.contains(genFeature)) {
    stringBuffer.append(TEXT_239);
    }
    stringBuffer.append(TEXT_240);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_241);
    stringBuffer.append(genFeature.getGetAccessor());
    if (genClass.hasCollidingGetAccessorOperation(genFeature)) {
    stringBuffer.append(TEXT_242);
    }
    stringBuffer.append(TEXT_243);
    if (genModel.isVirtualDelegation()) {
    stringBuffer.append(TEXT_244);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_245);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_246);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_247);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(positiveOffsetCorrection);
    stringBuffer.append(TEXT_248);
    }
    stringBuffer.append(TEXT_249);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_250);
    if (genModel.isVirtualDelegation()) {
    stringBuffer.append(TEXT_251);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(positiveOffsetCorrection);
    stringBuffer.append(TEXT_252);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_253);
    stringBuffer.append(genClass.getListConstructor(genFeature));
    stringBuffer.append(TEXT_254);
    } else {
    stringBuffer.append(TEXT_255);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_256);
    stringBuffer.append(genClass.getListConstructor(genFeature));
    stringBuffer.append(TEXT_257);
    }
    stringBuffer.append(TEXT_258);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(genFeature.isMapType() && genFeature.isEffectiveSuppressEMFTypes() ? ".map()" : "");
    stringBuffer.append(TEXT_259);
    }
    if (!genModel.isReflectiveDelegation() && genFeature.isBasicSet()) {
    stringBuffer.append(TEXT_260);
    if (isJDK50) { //Class/basicSetGenFeature.annotations.insert.javajetinc
    }
    if (classExtendsAllGenFeatures.contains(genFeature)) {
    stringBuffer.append(TEXT_261);
    }
    stringBuffer.append(TEXT_262);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.notify.NotificationChain"));
    stringBuffer.append(TEXT_263);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_264);
    stringBuffer.append(genFeature.getImportedInternalType(genClass));
    stringBuffer.append(TEXT_265);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_266);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.notify.NotificationChain"));
    stringBuffer.append(TEXT_267);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_268);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_269);
    }
    if (genFeature.isSet() && !(!genModel.isReflectiveDelegation() && genFeature.isBasicSet())) {
    stringBuffer.append(TEXT_270);
    if (isJDK50) { //Class/setGenFeature.annotations.insert.javajetinc
    }
    { GenOperation setAccessorOperation = genClass.getSetAccessorOperation(genFeature);
    if (classExtendsAllGenFeatures.contains(genFeature)) {
    stringBuffer.append(TEXT_271);
    }
    stringBuffer.append(TEXT_272);
    stringBuffer.append(genFeature.getAccessorName());
    if (genClass.hasCollidingSetAccessorOperation(genFeature)) {
    stringBuffer.append(TEXT_273);
    }
    stringBuffer.append(TEXT_274);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_275);
    stringBuffer.append(setAccessorOperation == null ? "new" + genFeature.getCapName() : setAccessorOperation.getGenParameters().get(0).getName());
    stringBuffer.append(TEXT_276);
    stringBuffer.append(genFeature.getAccessorName());
    if (genClass.hasCollidingSetAccessorOperation(genFeature)) {
    stringBuffer.append(TEXT_277);
    }
    stringBuffer.append(TEXT_278);
    stringBuffer.append(setAccessorOperation == null ? "new" + genFeature.getCapName() : setAccessorOperation.getGenParameters().get(0).getName());
    stringBuffer.append(TEXT_279);
    }
    }
    //Class/genFeatureReified.override.javajetinc
    }}}.run();}
    new Runnable() { public void run() {
    for (GenFeature genFeature : (isImplementation ? genClass.getImplementedGenFeatures() : genClass.getDeclaredGenFeatures())) {
    if (genModel.isArrayAccessors() && genFeature.isListType() && !genFeature.isFeatureMapType() && !genFeature.isMapType()) { String arrayElementType = genFeature.getArrayItemType(genClass);
    stringBuffer.append(TEXT_280);
    if (!isImplementation) {
    stringBuffer.append(TEXT_281);
    stringBuffer.append(arrayElementType);
    stringBuffer.append(TEXT_282);
    stringBuffer.append(genFeature.getGetArrayAccessor());
    stringBuffer.append(TEXT_283);
    } else {
    if (genModel.useGenerics() && CodeGenUtil.isUncheckedCast(arrayElementType)) {
    stringBuffer.append(TEXT_284);
    }
    stringBuffer.append(TEXT_285);
    stringBuffer.append(arrayElementType);
    stringBuffer.append(TEXT_286);
    stringBuffer.append(genFeature.getGetArrayAccessor());
    stringBuffer.append(TEXT_287);
    if (genFeature.isVolatile()) {
    stringBuffer.append(TEXT_288);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.util.BasicEList"));
    stringBuffer.append(genFeature.getListTemplateArguments(genClass));
    stringBuffer.append(TEXT_289);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.util.BasicEList"));
    stringBuffer.append(genFeature.getListTemplateArguments(genClass));
    stringBuffer.append(TEXT_290);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_291);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_292);
    } else {
    stringBuffer.append(TEXT_293);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_294);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_295);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_296);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.util.BasicEList"));
    stringBuffer.append(genFeature.getListTemplateArguments(genClass));
    stringBuffer.append(TEXT_297);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.util.BasicEList"));
    stringBuffer.append(genFeature.getListTemplateArguments(genClass));
    stringBuffer.append(TEXT_298);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_299);
    }
    stringBuffer.append(TEXT_300);
    stringBuffer.append(arrayElementType);
    stringBuffer.append(TEXT_301);
    }
    stringBuffer.append(TEXT_302);
    if (!isImplementation) {
    stringBuffer.append(TEXT_303);
    stringBuffer.append(genFeature.getListItemType(genClass));
    stringBuffer.append(TEXT_304);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_305);
    } else {
    stringBuffer.append(TEXT_306);
    stringBuffer.append(genFeature.getListItemType(genClass));
    stringBuffer.append(TEXT_307);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_308);
    if (!genModel.useGenerics()) {
    stringBuffer.append(TEXT_309);
    stringBuffer.append(genFeature.getListItemType(genClass));
    stringBuffer.append(TEXT_310);
    }
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_311);
    }
    stringBuffer.append(TEXT_312);
    if (!isImplementation) {
    stringBuffer.append(TEXT_313);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_314);
    } else {
    stringBuffer.append(TEXT_315);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_316);
    if (genFeature.isVolatile()) {
    stringBuffer.append(TEXT_317);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_318);
    } else {
    stringBuffer.append(TEXT_319);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_320);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_321);
    }
    stringBuffer.append(TEXT_322);
    }
    stringBuffer.append(TEXT_323);
    if (!isImplementation) {
    stringBuffer.append(TEXT_324);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_325);
    stringBuffer.append(genFeature.getListItemType(genClass));
    stringBuffer.append(TEXT_326);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_327);
    } else {
    stringBuffer.append(TEXT_328);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_329);
    stringBuffer.append(genFeature.getListItemType(genClass));
    stringBuffer.append(TEXT_330);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_331);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.util.BasicEList"));
    stringBuffer.append(genFeature.getListTemplateArguments(genClass));
    stringBuffer.append(TEXT_332);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_333);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_334);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_335);
    }
    stringBuffer.append(TEXT_336);
    if (!isImplementation) {
    stringBuffer.append(TEXT_337);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_338);
    stringBuffer.append(genFeature.getListItemType(genClass));
    stringBuffer.append(TEXT_339);
    } else {
    stringBuffer.append(TEXT_340);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_341);
    stringBuffer.append(genFeature.getListItemType(genClass));
    stringBuffer.append(TEXT_342);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_343);
    }
    }
    if (genFeature.isGet() && (isImplementation || !genFeature.isSuppressedGetVisibility())) {
    if (isInterface) {
    stringBuffer.append(TEXT_344);
    stringBuffer.append(genFeature.getFormattedName());
    stringBuffer.append(TEXT_345);
    stringBuffer.append(genFeature.getFeatureKind());
    stringBuffer.append(TEXT_346);
    if (genFeature.isListType() && genFeature.getEcoreFeature().getEGenericType().getETypeParameter() == null) {
    if (genFeature.isMapType()) { GenFeature keyFeature = genFeature.getMapEntryTypeGenClass().getMapEntryKeyFeature(); GenFeature valueFeature = genFeature.getMapEntryTypeGenClass().getMapEntryValueFeature(); 
    stringBuffer.append(TEXT_347);
    if (keyFeature.isListType()) {
    stringBuffer.append(TEXT_348);
    stringBuffer.append(keyFeature.getQualifiedListItemType(genClass));
    stringBuffer.append(TEXT_349);
    } else {
    stringBuffer.append(TEXT_350);
    stringBuffer.append(keyFeature.getType(genClass));
    stringBuffer.append(TEXT_351);
    }
    stringBuffer.append(TEXT_352);
    if (valueFeature.isListType()) {
    stringBuffer.append(TEXT_353);
    stringBuffer.append(valueFeature.getQualifiedListItemType(genClass));
    stringBuffer.append(TEXT_354);
    } else {
    stringBuffer.append(TEXT_355);
    stringBuffer.append(valueFeature.getType(genClass));
    stringBuffer.append(TEXT_356);
    }
    stringBuffer.append(TEXT_357);
    } else if (!genFeature.isWrappedFeatureMapType() && !(genModel.isSuppressEMFMetaData() && "org.eclipse.emf.ecore.EObject".equals(genFeature.getQualifiedListItemType(genClass)))) {
String typeName = genFeature.getQualifiedListItemType(genClass); String head = typeName; String tail = ""; int index = typeName.indexOf('<'); if (index == -1) { index = typeName.indexOf('['); } 
if (index != -1) { head = typeName.substring(0, index); tail = typeName.substring(index).replaceAll("<", "&lt;"); }

    stringBuffer.append(TEXT_358);
    stringBuffer.append(head);
    stringBuffer.append(TEXT_359);
    stringBuffer.append(tail);
    stringBuffer.append(TEXT_360);
    }
    } else if (genFeature.isSetDefaultValue()) {
    stringBuffer.append(TEXT_361);
    stringBuffer.append(genFeature.getDefaultValue());
    stringBuffer.append(TEXT_362);
    }
    if (genFeature.getTypeGenEnum() != null) {
    stringBuffer.append(TEXT_363);
    stringBuffer.append(genFeature.getTypeGenEnum().getQualifiedName());
    stringBuffer.append(TEXT_364);
    }
    if (genFeature.isBidirectional() && !genFeature.getReverse().getGenClass().isMapEntry()) { GenFeature reverseGenFeature = genFeature.getReverse(); 
    if (!reverseGenFeature.isSuppressedGetVisibility()) {
    stringBuffer.append(TEXT_365);
    stringBuffer.append(reverseGenFeature.getGenClass().getQualifiedInterfaceName());
    stringBuffer.append(TEXT_366);
    stringBuffer.append(reverseGenFeature.getGetAccessor());
    stringBuffer.append(TEXT_367);
    stringBuffer.append(reverseGenFeature.getFormattedName());
    stringBuffer.append(TEXT_368);
    }
    }
    stringBuffer.append(TEXT_369);
    if (!genFeature.hasDocumentation()) {
    stringBuffer.append(TEXT_370);
    stringBuffer.append(genFeature.getFormattedName());
    stringBuffer.append(TEXT_371);
    stringBuffer.append(genFeature.getFeatureKind());
    stringBuffer.append(TEXT_372);
    }
    stringBuffer.append(TEXT_373);
    if (genFeature.hasDocumentation()) {
    stringBuffer.append(TEXT_374);
    stringBuffer.append(genFeature.getDocumentation(genModel.getIndentation(stringBuffer)));
    stringBuffer.append(TEXT_375);
    }
    stringBuffer.append(TEXT_376);
    stringBuffer.append(genFeature.getFormattedName());
    stringBuffer.append(TEXT_377);
    stringBuffer.append(genFeature.getFeatureKind());
    stringBuffer.append(TEXT_378);
    if (genFeature.getTypeGenEnum() != null) {
    stringBuffer.append(TEXT_379);
    stringBuffer.append(genFeature.getTypeGenEnum().getQualifiedName());
    }
    if (genFeature.isUnsettable()) {
    if (!genFeature.isSuppressedIsSetVisibility()) {
    stringBuffer.append(TEXT_380);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_381);
    }
    if (genFeature.isChangeable() && !genFeature.isSuppressedUnsetVisibility()) {
    stringBuffer.append(TEXT_382);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_383);
    }
    }
    if (genFeature.isChangeable() && !genFeature.isListType() && !genFeature.isSuppressedSetVisibility()) {
    stringBuffer.append(TEXT_384);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_385);
    stringBuffer.append(genFeature.getRawImportedBoundType());
    stringBuffer.append(TEXT_386);
    }
    if (!genModel.isSuppressEMFMetaData()) {
    stringBuffer.append(TEXT_387);
    stringBuffer.append(genPackage.getQualifiedPackageInterfaceName());
    stringBuffer.append(TEXT_388);
    stringBuffer.append(genFeature.getFeatureAccessorName());
    stringBuffer.append(TEXT_389);
    }
    if (genFeature.isBidirectional() && !genFeature.getReverse().getGenClass().isMapEntry()) { GenFeature reverseGenFeature = genFeature.getReverse(); 
    if (!reverseGenFeature.isSuppressedGetVisibility()) {
    stringBuffer.append(TEXT_390);
    stringBuffer.append(reverseGenFeature.getGenClass().getQualifiedInterfaceName());
    stringBuffer.append(TEXT_391);
    stringBuffer.append(reverseGenFeature.getGetAccessor());
    }
    }
    if (!genModel.isSuppressEMFModelTags()) { boolean first = true; for (StringTokenizer stringTokenizer = new StringTokenizer(genFeature.getModelInfo(), "\n\r"); stringTokenizer.hasMoreTokens(); ) { String modelInfo = stringTokenizer.nextToken(); if (first) { first = false;
    stringBuffer.append(TEXT_392);
    stringBuffer.append(modelInfo);
    } else {
    stringBuffer.append(TEXT_393);
    stringBuffer.append(modelInfo);
    }} if (first) {
    stringBuffer.append(TEXT_394);
    }}
    stringBuffer.append(TEXT_395);
    //Class/getGenFeature.javadoc.override.javajetinc
    } else {
    stringBuffer.append(TEXT_396);
    if (isJDK50) { //Class/getGenFeature.annotations.insert.javajetinc
    }
    }
    if (!isImplementation) {
    stringBuffer.append(TEXT_397);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_398);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_399);
    } else {
    if (genModel.useGenerics() && ((genFeature.isContainer() || genFeature.isResolveProxies()) && !genFeature.isListType() && !(genModel.isReflectiveDelegation() && genModel.isDynamicDelegation()) && genFeature.isUncheckedCast(genClass) || genFeature.isListType() && !genFeature.isFeatureMapType() && (genModel.isReflectiveDelegation() || genModel.isVirtualDelegation() || genModel.isDynamicDelegation()) || genFeature.isListDataType() && genFeature.hasDelegateFeature() || genFeature.isListType() && genFeature.hasSettingDelegate())) {
    stringBuffer.append(TEXT_400);
    }
    stringBuffer.append(TEXT_401);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_402);
    stringBuffer.append(genFeature.getGetAccessor());
    if (genClass.hasCollidingGetAccessorOperation(genFeature)) {
    stringBuffer.append(TEXT_403);
    }
    stringBuffer.append(TEXT_404);
    if (genModel.isDynamicDelegation()) {
    stringBuffer.append(TEXT_405);
    if (!isJDK50 && genFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_406);
    }
    stringBuffer.append(TEXT_407);
    stringBuffer.append(genFeature.getObjectType(genClass));
    stringBuffer.append(TEXT_408);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(TEXT_409);
    stringBuffer.append(genFeature.getQualifiedFeatureAccessor());
    stringBuffer.append(TEXT_410);
    stringBuffer.append(!genFeature.isEffectiveSuppressEMFTypes());
    stringBuffer.append(TEXT_411);
    if (!isJDK50 && genFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_412);
    stringBuffer.append(genFeature.getPrimitiveValueFunction());
    stringBuffer.append(TEXT_413);
    }
    stringBuffer.append(TEXT_414);
    } else if (genModel.isReflectiveDelegation()) {
    if (genFeature.hasGetterBody()) {
    stringBuffer.append(TEXT_415);
    stringBuffer.append(genFeature.getGetterBody(genModel.getIndentation(stringBuffer)));
    } else {
    stringBuffer.append(TEXT_416);
    if (!isJDK50 && genFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_417);
    }
    stringBuffer.append(TEXT_418);
    stringBuffer.append(genFeature.getObjectType(genClass));
    stringBuffer.append(TEXT_419);
    stringBuffer.append(genFeature.getQualifiedFeatureAccessor());
    stringBuffer.append(TEXT_420);
    if (!isJDK50 && genFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_421);
    stringBuffer.append(genFeature.getPrimitiveValueFunction());
    stringBuffer.append(TEXT_422);
    }
    stringBuffer.append(TEXT_423);
    }
    } else if (genFeature.hasSettingDelegate()) {
    stringBuffer.append(TEXT_424);
    if (!isJDK50 && genFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_425);
    }
    stringBuffer.append(TEXT_426);
    stringBuffer.append(genFeature.getObjectType(genClass));
    stringBuffer.append(TEXT_427);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_428);
    if (!isJDK50 && genFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_429);
    stringBuffer.append(genFeature.getPrimitiveValueFunction());
    stringBuffer.append(TEXT_430);
    }
    stringBuffer.append(TEXT_431);
    } else if (!genFeature.isVolatile()) {
    if (genFeature.isListType()) {
    if (genModel.isVirtualDelegation()) {
    stringBuffer.append(TEXT_432);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_433);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_434);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_435);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(positiveOffsetCorrection);
    stringBuffer.append(TEXT_436);
    }
    stringBuffer.append(TEXT_437);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_438);
    if (genModel.isVirtualDelegation()) {
    stringBuffer.append(TEXT_439);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(positiveOffsetCorrection);
    stringBuffer.append(TEXT_440);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_441);
    stringBuffer.append(genClass.getListConstructor(genFeature));
    stringBuffer.append(TEXT_442);
    } else {
    stringBuffer.append(TEXT_443);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_444);
    stringBuffer.append(genClass.getListConstructor(genFeature));
    stringBuffer.append(TEXT_445);
    }
    stringBuffer.append(TEXT_446);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(genFeature.isMapType() && genFeature.isEffectiveSuppressEMFTypes() ? ".map()" : "");
    stringBuffer.append(TEXT_447);
    } else if (genFeature.isContainer()) {
    stringBuffer.append(TEXT_448);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(positiveOffsetCorrection);
    stringBuffer.append(TEXT_449);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_450);
    if (genFeature.isResolveProxies()) {
    stringBuffer.append(TEXT_451);
    } else {
    stringBuffer.append(TEXT_452);
    }
    stringBuffer.append(TEXT_453);
    } else {
    if (genFeature.isResolveProxies()) {
    if (genModel.isVirtualDelegation()) {
    stringBuffer.append(TEXT_454);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_455);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_456);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_457);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(positiveOffsetCorrection);
    if (genFeature.hasEDefault()) {
    stringBuffer.append(TEXT_458);
    stringBuffer.append(genFeature.getEDefault());
    }
    stringBuffer.append(TEXT_459);
    }
    stringBuffer.append(TEXT_460);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_461);
    stringBuffer.append(genFeature.getSafeNameAsEObject());
    stringBuffer.append(TEXT_462);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.InternalEObject"));
    stringBuffer.append(TEXT_463);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_464);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.InternalEObject"));
    stringBuffer.append(TEXT_465);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_466);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_467);
    stringBuffer.append(genFeature.getNonEObjectInternalTypeCast(genClass));
    stringBuffer.append(TEXT_468);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_469);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_470);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_471);
    if (genFeature.isEffectiveContains()) {
    stringBuffer.append(TEXT_472);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.InternalEObject"));
    stringBuffer.append(TEXT_473);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_474);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.InternalEObject"));
    stringBuffer.append(TEXT_475);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_476);
    if (!genFeature.isBidirectional()) {
    stringBuffer.append(TEXT_477);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.notify.NotificationChain"));
    stringBuffer.append(TEXT_478);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_479);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(negativeOffsetCorrection);
    stringBuffer.append(TEXT_480);
    } else { GenFeature reverseFeature = genFeature.getReverse(); GenClass targetClass = reverseFeature.getGenClass(); String reverseOffsetCorrection = targetClass.hasOffsetCorrection() ? " + " + genClass.getOffsetCorrectionField(genFeature) : "";
    stringBuffer.append(TEXT_481);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.notify.NotificationChain"));
    stringBuffer.append(TEXT_482);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_483);
    stringBuffer.append(targetClass.getQualifiedFeatureID(reverseFeature));
    stringBuffer.append(reverseOffsetCorrection);
    stringBuffer.append(TEXT_484);
    stringBuffer.append(targetClass.getRawImportedInterfaceName());
    stringBuffer.append(TEXT_485);
    }
    stringBuffer.append(TEXT_486);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_487);
    if (!genFeature.isBidirectional()) {
    stringBuffer.append(TEXT_488);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_489);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(negativeOffsetCorrection);
    stringBuffer.append(TEXT_490);
    } else { GenFeature reverseFeature = genFeature.getReverse(); GenClass targetClass = reverseFeature.getGenClass(); String reverseOffsetCorrection = targetClass.hasOffsetCorrection() ? " + " + genClass.getOffsetCorrectionField(genFeature) : "";
    stringBuffer.append(TEXT_491);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_492);
    stringBuffer.append(targetClass.getQualifiedFeatureID(reverseFeature));
    stringBuffer.append(reverseOffsetCorrection);
    stringBuffer.append(TEXT_493);
    stringBuffer.append(targetClass.getRawImportedInterfaceName());
    stringBuffer.append(TEXT_494);
    }
    stringBuffer.append(TEXT_495);
    } else if (genModel.isVirtualDelegation()) {
    stringBuffer.append(TEXT_496);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(positiveOffsetCorrection);
    stringBuffer.append(TEXT_497);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_498);
    }
    if (!genModel.isSuppressNotification()) {
    stringBuffer.append(TEXT_499);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.impl.ENotificationImpl"));
    stringBuffer.append(TEXT_500);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.notify.Notification"));
    stringBuffer.append(TEXT_501);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(positiveOffsetCorrection);
    stringBuffer.append(TEXT_502);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_503);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_504);
    }
    stringBuffer.append(TEXT_505);
    }
    if (!genFeature.isResolveProxies() && genModel.isVirtualDelegation() && !genFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_506);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_507);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(positiveOffsetCorrection);
    if (genFeature.hasEDefault()) {
    stringBuffer.append(TEXT_508);
    stringBuffer.append(genFeature.getEDefault());
    }
    stringBuffer.append(TEXT_509);
    } else if (genClass.isFlag(genFeature)) {
    if (genFeature.isBooleanType()) {
    stringBuffer.append(TEXT_510);
    stringBuffer.append(genClass.getFlagsField(genFeature));
    stringBuffer.append(TEXT_511);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_512);
    } else {
    stringBuffer.append(TEXT_513);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_514);
    stringBuffer.append(genClass.getFlagsField(genFeature));
    stringBuffer.append(TEXT_515);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_516);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_517);
    }
    } else {
    stringBuffer.append(TEXT_518);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_519);
    }
    }
    } else {//volatile
    if (genFeature.isResolveProxies() && !genFeature.isListType()) {
    stringBuffer.append(TEXT_520);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_521);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_522);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_523);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_524);
    stringBuffer.append(genFeature.getSafeNameAsEObject());
    stringBuffer.append(TEXT_525);
    stringBuffer.append(genFeature.getNonEObjectInternalTypeCast(genClass));
    stringBuffer.append(TEXT_526);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.InternalEObject"));
    stringBuffer.append(TEXT_527);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_528);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_529);
    } else if (genFeature.hasDelegateFeature()) { GenFeature delegateFeature = genFeature.getDelegateFeature();
    if (genFeature.isFeatureMapType()) {
    String featureMapEntryTemplateArgument = isJDK50 ? "<" + genModel.getImportedName("org.eclipse.emf.ecore.util.FeatureMap") + ".Entry>" : "";
    if (delegateFeature.isWrappedFeatureMapType()) {
    stringBuffer.append(TEXT_530);
    stringBuffer.append(genFeature.getImportedEffectiveFeatureMapWrapperClass());
    stringBuffer.append(TEXT_531);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.FeatureMap"));
    stringBuffer.append(TEXT_532);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.FeatureMap"));
    stringBuffer.append(TEXT_533);
    stringBuffer.append(delegateFeature.getAccessorName());
    stringBuffer.append(TEXT_534);
    stringBuffer.append(featureMapEntryTemplateArgument);
    stringBuffer.append(TEXT_535);
    stringBuffer.append(genFeature.getQualifiedFeatureAccessor());
    stringBuffer.append(TEXT_536);
    } else {
    stringBuffer.append(TEXT_537);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.FeatureMap"));
    stringBuffer.append(TEXT_538);
    stringBuffer.append(delegateFeature.getAccessorName());
    stringBuffer.append(TEXT_539);
    stringBuffer.append(featureMapEntryTemplateArgument);
    stringBuffer.append(TEXT_540);
    stringBuffer.append(genFeature.getQualifiedFeatureAccessor());
    stringBuffer.append(TEXT_541);
    }
    } else if (genFeature.isListType()) {
    if (delegateFeature.isWrappedFeatureMapType()) {
    stringBuffer.append(TEXT_542);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.FeatureMap"));
    stringBuffer.append(TEXT_543);
    stringBuffer.append(delegateFeature.getAccessorName());
    stringBuffer.append(TEXT_544);
    stringBuffer.append(genFeature.getQualifiedFeatureAccessor());
    stringBuffer.append(TEXT_545);
    } else {
    stringBuffer.append(TEXT_546);
    stringBuffer.append(delegateFeature.getAccessorName());
    stringBuffer.append(TEXT_547);
    stringBuffer.append(genFeature.getQualifiedFeatureAccessor());
    stringBuffer.append(TEXT_548);
    }
    } else {
    if (delegateFeature.isWrappedFeatureMapType()) {
    stringBuffer.append(TEXT_549);
    if (!isJDK50 && genFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_550);
    }
    if (genFeature.getTypeGenDataType() == null || !genFeature.getTypeGenDataType().isObjectType()) {
    stringBuffer.append(TEXT_551);
    stringBuffer.append(genFeature.getObjectType(genClass));
    stringBuffer.append(TEXT_552);
    }
    stringBuffer.append(TEXT_553);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.FeatureMap"));
    stringBuffer.append(TEXT_554);
    stringBuffer.append(delegateFeature.getAccessorName());
    stringBuffer.append(TEXT_555);
    stringBuffer.append(genFeature.getQualifiedFeatureAccessor());
    stringBuffer.append(TEXT_556);
    if (!isJDK50 && genFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_557);
    stringBuffer.append(genFeature.getPrimitiveValueFunction());
    stringBuffer.append(TEXT_558);
    }
    stringBuffer.append(TEXT_559);
    } else {
    stringBuffer.append(TEXT_560);
    if (!isJDK50 && genFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_561);
    }
    if (genFeature.getTypeGenDataType() == null || !genFeature.getTypeGenDataType().isObjectType()) {
    stringBuffer.append(TEXT_562);
    stringBuffer.append(genFeature.getObjectType(genClass));
    stringBuffer.append(TEXT_563);
    }
    stringBuffer.append(TEXT_564);
    stringBuffer.append(delegateFeature.getAccessorName());
    stringBuffer.append(TEXT_565);
    stringBuffer.append(genFeature.getQualifiedFeatureAccessor());
    stringBuffer.append(TEXT_566);
    if (!isJDK50 && genFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_567);
    stringBuffer.append(genFeature.getPrimitiveValueFunction());
    stringBuffer.append(TEXT_568);
    }
    stringBuffer.append(TEXT_569);
    }
    }
    } else if (genClass.getGetAccessorOperation(genFeature) != null) {
    stringBuffer.append(TEXT_570);
    stringBuffer.append(genClass.getGetAccessorOperation(genFeature).getBody(genModel.getIndentation(stringBuffer)));
    } else if (genFeature.hasGetterBody()) {
    stringBuffer.append(TEXT_571);
    stringBuffer.append(genFeature.getGetterBody(genModel.getIndentation(stringBuffer)));
    } else {
    stringBuffer.append(TEXT_572);
    stringBuffer.append(genFeature.getFormattedName());
    stringBuffer.append(TEXT_573);
    stringBuffer.append(genFeature.getFeatureKind());
    stringBuffer.append(TEXT_574);
    if (genFeature.isListType()) {
    stringBuffer.append(TEXT_575);
    if (genFeature.isMapType()) {
    stringBuffer.append(TEXT_576);
    } else if (genFeature.isFeatureMapType()) {
    stringBuffer.append(TEXT_577);
    } else {
    stringBuffer.append(TEXT_578);
    }
    stringBuffer.append(TEXT_579);
    }
    stringBuffer.append(TEXT_580);
    //Class/getGenFeature.todo.override.javajetinc
    }
    }
    stringBuffer.append(TEXT_581);
    }
    //Class/getGenFeature.override.javajetinc
    }
    if (isImplementation && !genModel.isReflectiveDelegation() && genFeature.isBasicGet()) {
    stringBuffer.append(TEXT_582);
    if (isJDK50) { //Class/basicGetGenFeature.annotations.insert.javajetinc
    }
    stringBuffer.append(TEXT_583);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_584);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_585);
    if (genModel.isDynamicDelegation()) {
    stringBuffer.append(TEXT_586);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_587);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(TEXT_588);
    stringBuffer.append(genFeature.getQualifiedFeatureAccessor());
    stringBuffer.append(TEXT_589);
    stringBuffer.append(!genFeature.isEffectiveSuppressEMFTypes());
    stringBuffer.append(TEXT_590);
    } else if (genFeature.hasSettingDelegate()) {
    stringBuffer.append(TEXT_591);
    if (!isJDK50 && genFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_592);
    }
    stringBuffer.append(TEXT_593);
    stringBuffer.append(genFeature.getObjectType(genClass));
    stringBuffer.append(TEXT_594);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_595);
    if (!isJDK50 && genFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_596);
    stringBuffer.append(genFeature.getPrimitiveValueFunction());
    stringBuffer.append(TEXT_597);
    }
    stringBuffer.append(TEXT_598);
    } else if (genFeature.isContainer()) {
    stringBuffer.append(TEXT_599);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(positiveOffsetCorrection);
    stringBuffer.append(TEXT_600);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_601);
    } else if (!genFeature.isVolatile()) {
    if (genModel.isVirtualDelegation()) {
    stringBuffer.append(TEXT_602);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_603);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(positiveOffsetCorrection);
    stringBuffer.append(TEXT_604);
    } else {
    stringBuffer.append(TEXT_605);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_606);
    }
    } else if (genFeature.hasDelegateFeature()) { GenFeature delegateFeature = genFeature.getDelegateFeature();
    if (delegateFeature.isWrappedFeatureMapType()) {
    stringBuffer.append(TEXT_607);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_608);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.FeatureMap"));
    stringBuffer.append(TEXT_609);
    stringBuffer.append(delegateFeature.getAccessorName());
    stringBuffer.append(TEXT_610);
    stringBuffer.append(genFeature.getQualifiedFeatureAccessor());
    stringBuffer.append(TEXT_611);
    } else {
    stringBuffer.append(TEXT_612);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_613);
    stringBuffer.append(delegateFeature.getAccessorName());
    stringBuffer.append(TEXT_614);
    stringBuffer.append(genFeature.getQualifiedFeatureAccessor());
    stringBuffer.append(TEXT_615);
    }
    } else if (genFeature.hasGetterBody()) {
    stringBuffer.append(TEXT_616);
    stringBuffer.append(genFeature.getGetterBody(genModel.getIndentation(stringBuffer)));
    } else {
    stringBuffer.append(TEXT_617);
    stringBuffer.append(genFeature.getFormattedName());
    stringBuffer.append(TEXT_618);
    stringBuffer.append(genFeature.getFeatureKind());
    stringBuffer.append(TEXT_619);
    //Class/basicGetGenFeature.todo.override.javajetinc
    }
    stringBuffer.append(TEXT_620);
    //Class/basicGetGenFeature.override.javajetinc
    }
    if (isImplementation && !genModel.isReflectiveDelegation() && genFeature.isBasicSet()) {
    stringBuffer.append(TEXT_621);
    if (isJDK50) { //Class/basicSetGenFeature.annotations.insert.javajetinc
    }
    stringBuffer.append(TEXT_622);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.notify.NotificationChain"));
    stringBuffer.append(TEXT_623);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_624);
    stringBuffer.append(genFeature.getImportedInternalType(genClass));
    stringBuffer.append(TEXT_625);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_626);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.notify.NotificationChain"));
    stringBuffer.append(TEXT_627);
    if (genFeature.isContainer()) {
    stringBuffer.append(TEXT_628);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.InternalEObject"));
    stringBuffer.append(TEXT_629);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_630);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(positiveOffsetCorrection);
    stringBuffer.append(TEXT_631);
    stringBuffer.append(TEXT_632);
    } else if (genModel.isDynamicDelegation()) {
    stringBuffer.append(TEXT_633);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.InternalEObject"));
    stringBuffer.append(TEXT_634);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_635);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(positiveOffsetCorrection);
    stringBuffer.append(TEXT_636);
    stringBuffer.append(TEXT_637);
    } else if (!genFeature.isVolatile()) {
    if (genModel.isVirtualDelegation()) {
    stringBuffer.append(TEXT_638);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_639);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(positiveOffsetCorrection);
    stringBuffer.append(TEXT_640);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_641);
    } else {
    stringBuffer.append(TEXT_642);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_643);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_644);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_645);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_646);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_647);
    }
    if (genFeature.isUnsettable()) {
    if (genModel.isVirtualDelegation()) {
    if (!genModel.isSuppressNotification()) {
    stringBuffer.append(TEXT_648);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_649);
    }
    } else if (genClass.isESetFlag(genFeature)) {
    stringBuffer.append(TEXT_650);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_651);
    stringBuffer.append(genClass.getESetFlagsField(genFeature));
    stringBuffer.append(TEXT_652);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_653);
    if (!genModel.isSuppressNotification()) {
    stringBuffer.append(TEXT_654);
    stringBuffer.append(genClass.getESetFlagsField(genFeature));
    stringBuffer.append(TEXT_655);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_656);
    }
    } else {
    if (!genModel.isSuppressNotification()) {
    stringBuffer.append(TEXT_657);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_658);
    stringBuffer.append(genFeature.getUncapName());
    stringBuffer.append(TEXT_659);
    }
    stringBuffer.append(TEXT_660);
    stringBuffer.append(genFeature.getUncapName());
    stringBuffer.append(TEXT_661);
    }
    }
    if (!genModel.isSuppressNotification()) {
    stringBuffer.append(TEXT_662);
    if (genFeature.isUnsettable()) {
    stringBuffer.append(TEXT_663);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.impl.ENotificationImpl"));
    stringBuffer.append(TEXT_664);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.impl.ENotificationImpl"));
    stringBuffer.append(TEXT_665);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.notify.Notification"));
    stringBuffer.append(TEXT_666);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(positiveOffsetCorrection);
    stringBuffer.append(TEXT_667);
    if (genModel.isVirtualDelegation()) {
    stringBuffer.append(TEXT_668);
    stringBuffer.append(genFeature.getCapName());
    } else {
    stringBuffer.append(TEXT_669);
    stringBuffer.append(genFeature.getCapName());
    }
    stringBuffer.append(TEXT_670);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_671);
    if (genModel.isVirtualDelegation()) {
    stringBuffer.append(TEXT_672);
    } else {
    stringBuffer.append(TEXT_673);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_674);
    }
    stringBuffer.append(TEXT_675);
    } else {
    stringBuffer.append(TEXT_676);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.impl.ENotificationImpl"));
    stringBuffer.append(TEXT_677);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.impl.ENotificationImpl"));
    stringBuffer.append(TEXT_678);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.notify.Notification"));
    stringBuffer.append(TEXT_679);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(positiveOffsetCorrection);
    stringBuffer.append(TEXT_680);
    if (genModel.isVirtualDelegation()) {
    stringBuffer.append(TEXT_681);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_682);
    stringBuffer.append(genFeature.getCapName());
    } else {
    stringBuffer.append(TEXT_683);
    stringBuffer.append(genFeature.getCapName());
    }
    stringBuffer.append(TEXT_684);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_685);
    }
    stringBuffer.append(TEXT_686);
    }
    stringBuffer.append(TEXT_687);
    } else if (genFeature.hasDelegateFeature()) { GenFeature delegateFeature = genFeature.getDelegateFeature();
    if (delegateFeature.isWrappedFeatureMapType()) {
    stringBuffer.append(TEXT_688);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.FeatureMap"));
    stringBuffer.append(TEXT_689);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.FeatureMap"));
    stringBuffer.append(TEXT_690);
    stringBuffer.append(delegateFeature.getAccessorName());
    stringBuffer.append(TEXT_691);
    stringBuffer.append(genFeature.getQualifiedFeatureAccessor());
    stringBuffer.append(TEXT_692);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_693);
    } else {
    stringBuffer.append(TEXT_694);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.FeatureMap"));
    stringBuffer.append(TEXT_695);
    stringBuffer.append(delegateFeature.getAccessorName());
    stringBuffer.append(TEXT_696);
    stringBuffer.append(genFeature.getQualifiedFeatureAccessor());
    stringBuffer.append(TEXT_697);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_698);
    }
    } else {
    stringBuffer.append(TEXT_699);
    stringBuffer.append(genFeature.getFormattedName());
    stringBuffer.append(TEXT_700);
    stringBuffer.append(genFeature.getFeatureKind());
    stringBuffer.append(TEXT_701);
    //Class/basicSetGenFeature.todo.override.javajetinc
    }
    stringBuffer.append(TEXT_702);
    //Class/basicSetGenFeature.override.javajetinc
    }
    if (genFeature.isSet() && (isImplementation || !genFeature.isSuppressedSetVisibility())) {
    if (isInterface) { 
    stringBuffer.append(TEXT_703);
    stringBuffer.append(genClass.getQualifiedInterfaceName());
    stringBuffer.append(TEXT_704);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_705);
    stringBuffer.append(genFeature.getFormattedName());
    stringBuffer.append(TEXT_706);
    stringBuffer.append(genFeature.getFeatureKind());
    stringBuffer.append(TEXT_707);
    stringBuffer.append(TEXT_708);
    stringBuffer.append(genFeature.getFormattedName());
    stringBuffer.append(TEXT_709);
    stringBuffer.append(genFeature.getFeatureKind());
    stringBuffer.append(TEXT_710);
    if (genFeature.isEnumType()) {
    stringBuffer.append(TEXT_711);
    stringBuffer.append(genFeature.getTypeGenEnum().getQualifiedName());
    }
    if (genFeature.isUnsettable()) {
    if (!genFeature.isSuppressedIsSetVisibility()) {
    stringBuffer.append(TEXT_712);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_713);
    }
    if (!genFeature.isSuppressedUnsetVisibility()) {
    stringBuffer.append(TEXT_714);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_715);
    }
    }
    stringBuffer.append(TEXT_716);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_717);
    //Class/setGenFeature.javadoc.override.javajetinc
    } else {
    stringBuffer.append(TEXT_718);
    if (isJDK50) { //Class/setGenFeature.annotations.insert.javajetinc
    }
    }
    if (!isImplementation) { 
    stringBuffer.append(TEXT_719);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_720);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_721);
    } else { GenOperation setAccessorOperation = genClass.getSetAccessorOperation(genFeature);
    stringBuffer.append(TEXT_722);
    stringBuffer.append(genFeature.getAccessorName());
    if (genClass.hasCollidingSetAccessorOperation(genFeature)) {
    stringBuffer.append(TEXT_723);
    }
    stringBuffer.append(TEXT_724);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_725);
    stringBuffer.append(setAccessorOperation == null ? "new" + genFeature.getCapName() : setAccessorOperation.getGenParameters().get(0).getName());
    stringBuffer.append(TEXT_726);
    if (genModel.isDynamicDelegation()) {
    stringBuffer.append(TEXT_727);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(TEXT_728);
    stringBuffer.append(genFeature.getQualifiedFeatureAccessor());
    stringBuffer.append(TEXT_729);
    if (!isJDK50 && genFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_730);
    stringBuffer.append(genFeature.getObjectType(genClass));
    stringBuffer.append(TEXT_731);
    }
    stringBuffer.append(TEXT_732);
    stringBuffer.append(genFeature.getCapName());
    if (!isJDK50 && genFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_733);
    }
    stringBuffer.append(TEXT_734);
    } else if (genModel.isReflectiveDelegation()) {
    stringBuffer.append(TEXT_735);
    stringBuffer.append(genFeature.getQualifiedFeatureAccessor());
    stringBuffer.append(TEXT_736);
    if (!isJDK50 && genFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_737);
    stringBuffer.append(genFeature.getObjectType(genClass));
    stringBuffer.append(TEXT_738);
    }
    stringBuffer.append(TEXT_739);
    stringBuffer.append(genFeature.getCapName());
    if (!isJDK50 && genFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_740);
    }
    stringBuffer.append(TEXT_741);
    } else if (genFeature.hasSettingDelegate()) {
    stringBuffer.append(TEXT_742);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_743);
    if (!isJDK50 && genFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_744);
    stringBuffer.append(genFeature.getObjectType(genClass));
    stringBuffer.append(TEXT_745);
    }
    stringBuffer.append(TEXT_746);
    stringBuffer.append(genFeature.getCapName());
    if (!isJDK50 && genFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_747);
    }
    stringBuffer.append(TEXT_748);
    } else if (!genFeature.isVolatile()) {
    if (genFeature.isContainer()) { GenFeature reverseFeature = genFeature.getReverse(); GenClass targetClass = reverseFeature.getGenClass(); String reverseOffsetCorrection = targetClass.hasOffsetCorrection() ? " + " + genClass.getOffsetCorrectionField(genFeature) : "";
    stringBuffer.append(TEXT_749);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_750);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(positiveOffsetCorrection);
    stringBuffer.append(TEXT_751);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_752);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.EcoreUtil"));
    stringBuffer.append(TEXT_753);
    stringBuffer.append(genFeature.getEObjectCast());
    stringBuffer.append(TEXT_754);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_755);
    stringBuffer.append(genModel.getImportedName("java.lang.IllegalArgumentException"));
    stringBuffer.append(TEXT_756);
    stringBuffer.append(genModel.getNonNLS());
    stringBuffer.append(TEXT_757);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.notify.NotificationChain"));
    stringBuffer.append(TEXT_758);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_759);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.InternalEObject"));
    stringBuffer.append(TEXT_760);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_761);
    stringBuffer.append(targetClass.getQualifiedFeatureID(reverseFeature));
    stringBuffer.append(reverseOffsetCorrection);
    stringBuffer.append(TEXT_762);
    stringBuffer.append(targetClass.getRawImportedInterfaceName());
    stringBuffer.append(TEXT_763);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_764);
    stringBuffer.append(genFeature.getInternalTypeCast());
    stringBuffer.append(TEXT_765);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_766);
    if (!genModel.isSuppressNotification()) {
    stringBuffer.append(TEXT_767);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.impl.ENotificationImpl"));
    stringBuffer.append(TEXT_768);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.notify.Notification"));
    stringBuffer.append(TEXT_769);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(positiveOffsetCorrection);
    stringBuffer.append(TEXT_770);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_771);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_772);
    }
    } else if (genFeature.isBidirectional() || genFeature.isEffectiveContains()) {
    if (genModel.isVirtualDelegation()) {
    stringBuffer.append(TEXT_773);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_774);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_775);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_776);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(positiveOffsetCorrection);
    stringBuffer.append(TEXT_777);
    }
    stringBuffer.append(TEXT_778);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_779);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_780);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.notify.NotificationChain"));
    stringBuffer.append(TEXT_781);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_782);
    if (!genFeature.isBidirectional()) {
    stringBuffer.append(TEXT_783);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.InternalEObject"));
    stringBuffer.append(TEXT_784);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_785);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(negativeOffsetCorrection);
    stringBuffer.append(TEXT_786);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_787);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.InternalEObject"));
    stringBuffer.append(TEXT_788);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_789);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(negativeOffsetCorrection);
    stringBuffer.append(TEXT_790);
    } else { GenFeature reverseFeature = genFeature.getReverse(); GenClass targetClass = reverseFeature.getGenClass(); String reverseOffsetCorrection = targetClass.hasOffsetCorrection() ? " + " + genClass.getOffsetCorrectionField(genFeature) : "";
    stringBuffer.append(TEXT_791);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.InternalEObject"));
    stringBuffer.append(TEXT_792);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_793);
    stringBuffer.append(targetClass.getQualifiedFeatureID(reverseFeature));
    stringBuffer.append(reverseOffsetCorrection);
    stringBuffer.append(TEXT_794);
    stringBuffer.append(targetClass.getRawImportedInterfaceName());
    stringBuffer.append(TEXT_795);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_796);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.InternalEObject"));
    stringBuffer.append(TEXT_797);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_798);
    stringBuffer.append(targetClass.getQualifiedFeatureID(reverseFeature));
    stringBuffer.append(reverseOffsetCorrection);
    stringBuffer.append(TEXT_799);
    stringBuffer.append(targetClass.getRawImportedInterfaceName());
    stringBuffer.append(TEXT_800);
    }
    stringBuffer.append(TEXT_801);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_802);
    stringBuffer.append(genFeature.getInternalTypeCast());
    stringBuffer.append(TEXT_803);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_804);
    if (genFeature.isUnsettable()) {
    stringBuffer.append(TEXT_805);
    if (genModel.isVirtualDelegation()) {
    stringBuffer.append(TEXT_806);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_807);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(positiveOffsetCorrection);
    stringBuffer.append(TEXT_808);
    } else if (genClass.isESetFlag(genFeature)) {
    if (!genModel.isSuppressNotification()) {
    stringBuffer.append(TEXT_809);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_810);
    stringBuffer.append(genClass.getESetFlagsField(genFeature));
    stringBuffer.append(TEXT_811);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_812);
    }
    stringBuffer.append(TEXT_813);
    stringBuffer.append(genClass.getESetFlagsField(genFeature));
    stringBuffer.append(TEXT_814);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_815);
    } else {
    if (!genModel.isSuppressNotification()) {
    stringBuffer.append(TEXT_816);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_817);
    stringBuffer.append(genFeature.getUncapName());
    stringBuffer.append(TEXT_818);
    }
    stringBuffer.append(TEXT_819);
    stringBuffer.append(genFeature.getUncapName());
    stringBuffer.append(TEXT_820);
    }
    if (!genModel.isSuppressNotification()) {
    stringBuffer.append(TEXT_821);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.impl.ENotificationImpl"));
    stringBuffer.append(TEXT_822);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.notify.Notification"));
    stringBuffer.append(TEXT_823);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(positiveOffsetCorrection);
    stringBuffer.append(TEXT_824);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_825);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_826);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_827);
    }
    stringBuffer.append(TEXT_828);
    } else {
    if (!genModel.isSuppressNotification()) {
    stringBuffer.append(TEXT_829);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.impl.ENotificationImpl"));
    stringBuffer.append(TEXT_830);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.notify.Notification"));
    stringBuffer.append(TEXT_831);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(positiveOffsetCorrection);
    stringBuffer.append(TEXT_832);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_833);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_834);
    }
    }
    } else {
    if (genClass.isFlag(genFeature)) {
    if (!genModel.isSuppressNotification()) {
    if (genFeature.isBooleanType()) {
    stringBuffer.append(TEXT_835);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_836);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_837);
    stringBuffer.append(genClass.getFlagsField(genFeature));
    stringBuffer.append(TEXT_838);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_839);
    } else {
    stringBuffer.append(TEXT_840);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_841);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_842);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_843);
    stringBuffer.append(genClass.getFlagsField(genFeature));
    stringBuffer.append(TEXT_844);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_845);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_846);
    }
    }
    if (genFeature.isBooleanType()) {
    stringBuffer.append(TEXT_847);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_848);
    stringBuffer.append(genClass.getFlagsField(genFeature));
    stringBuffer.append(TEXT_849);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_850);
    stringBuffer.append(genClass.getFlagsField(genFeature));
    stringBuffer.append(TEXT_851);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_852);
    } else {
    stringBuffer.append(TEXT_853);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_854);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_855);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_856);
    stringBuffer.append(genClass.getFlagsField(genFeature));
    stringBuffer.append(TEXT_857);
    stringBuffer.append(genClass.getFlagsField(genFeature));
    stringBuffer.append(TEXT_858);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_859);
    if (isJDK50) {
    stringBuffer.append(TEXT_860);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_861);
    } else {
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_862);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_863);
    }
    stringBuffer.append(TEXT_864);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_865);
    }
    } else {
    if (!genModel.isVirtualDelegation() || genFeature.isPrimitiveType()) {
    if (!genModel.isSuppressNotification()) {
    stringBuffer.append(TEXT_866);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_867);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_868);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_869);
    }
    }
    if (genFeature.isEnumType()) {
    if (genModel.isVirtualDelegation()) {
    stringBuffer.append(TEXT_870);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_871);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_872);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_873);
    stringBuffer.append(genFeature.getEDefault());
    stringBuffer.append(TEXT_874);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_875);
    } else {
    stringBuffer.append(TEXT_876);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_877);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_878);
    stringBuffer.append(genFeature.getEDefault());
    stringBuffer.append(TEXT_879);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_880);
    }
    } else {
    if (genModel.isVirtualDelegation() && !genFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_881);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_882);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_883);
    stringBuffer.append(genFeature.getInternalTypeCast());
    stringBuffer.append(TEXT_884);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_885);
    } else {
    stringBuffer.append(TEXT_886);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_887);
    stringBuffer.append(genFeature.getInternalTypeCast());
    stringBuffer.append(TEXT_888);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_889);
    }
    }
    if (genModel.isVirtualDelegation() && !genFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_890);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_891);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(positiveOffsetCorrection);
    stringBuffer.append(TEXT_892);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_893);
    }
    }
    if (genFeature.isUnsettable()) {
    if (genModel.isVirtualDelegation() && !genFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_894);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_895);
    } else if (genClass.isESetFlag(genFeature)) {
    if (!genModel.isSuppressNotification()) {
    stringBuffer.append(TEXT_896);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_897);
    stringBuffer.append(genClass.getESetFlagsField(genFeature));
    stringBuffer.append(TEXT_898);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_899);
    }
    stringBuffer.append(TEXT_900);
    stringBuffer.append(genClass.getESetFlagsField(genFeature));
    stringBuffer.append(TEXT_901);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_902);
    } else {
    if (!genModel.isSuppressNotification()) {
    stringBuffer.append(TEXT_903);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_904);
    stringBuffer.append(genFeature.getUncapName());
    stringBuffer.append(TEXT_905);
    }
    stringBuffer.append(TEXT_906);
    stringBuffer.append(genFeature.getUncapName());
    stringBuffer.append(TEXT_907);
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
    stringBuffer.append(genFeature.getEDefault());
    stringBuffer.append(TEXT_913);
    stringBuffer.append(genFeature.getCapName());
    } else {
    stringBuffer.append(TEXT_914);
    stringBuffer.append(genFeature.getCapName());
    }
    stringBuffer.append(TEXT_915);
    if (genClass.isFlag(genFeature)) {
    stringBuffer.append(TEXT_916);
    stringBuffer.append(genFeature.getCapName());
    } else {
    stringBuffer.append(genFeature.getSafeName());
    }
    stringBuffer.append(TEXT_917);
    if (genModel.isVirtualDelegation() && !genFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_918);
    } else {
    stringBuffer.append(TEXT_919);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_920);
    }
    stringBuffer.append(TEXT_921);
    }
    } else {
    if (!genModel.isSuppressNotification()) {
    stringBuffer.append(TEXT_922);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.impl.ENotificationImpl"));
    stringBuffer.append(TEXT_923);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.notify.Notification"));
    stringBuffer.append(TEXT_924);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(positiveOffsetCorrection);
    stringBuffer.append(TEXT_925);
    if (genModel.isVirtualDelegation() && !genFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_926);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_927);
    stringBuffer.append(genFeature.getEDefault());
    stringBuffer.append(TEXT_928);
    stringBuffer.append(genFeature.getCapName());
    } else {
    stringBuffer.append(TEXT_929);
    stringBuffer.append(genFeature.getCapName());
    }
    stringBuffer.append(TEXT_930);
    if (genClass.isFlag(genFeature)) {
    stringBuffer.append(TEXT_931);
    stringBuffer.append(genFeature.getCapName());
    } else {
    stringBuffer.append(genFeature.getSafeName());
    }
    stringBuffer.append(TEXT_932);
    }
    }
    }
    } else if (genFeature.hasDelegateFeature()) { GenFeature delegateFeature = genFeature.getDelegateFeature();
    if (delegateFeature.isWrappedFeatureMapType()) {
    stringBuffer.append(TEXT_933);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.FeatureMap"));
    stringBuffer.append(TEXT_934);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.FeatureMap"));
    stringBuffer.append(TEXT_935);
    stringBuffer.append(delegateFeature.getAccessorName());
    stringBuffer.append(TEXT_936);
    stringBuffer.append(genFeature.getQualifiedFeatureAccessor());
    stringBuffer.append(TEXT_937);
    if (!isJDK50 && genFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_938);
    stringBuffer.append(genFeature.getObjectType(genClass));
    stringBuffer.append(TEXT_939);
    }
    stringBuffer.append(TEXT_940);
    stringBuffer.append(genFeature.getCapName());
    if (!isJDK50 && genFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_941);
    }
    stringBuffer.append(TEXT_942);
    } else {
    stringBuffer.append(TEXT_943);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.FeatureMap"));
    stringBuffer.append(TEXT_944);
    stringBuffer.append(delegateFeature.getAccessorName());
    stringBuffer.append(TEXT_945);
    stringBuffer.append(genFeature.getQualifiedFeatureAccessor());
    stringBuffer.append(TEXT_946);
    if (!isJDK50 && genFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_947);
    stringBuffer.append(genFeature.getObjectType(genClass));
    stringBuffer.append(TEXT_948);
    }
    stringBuffer.append(TEXT_949);
    stringBuffer.append(genFeature.getCapName());
    if (!isJDK50 && genFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_950);
    }
    stringBuffer.append(TEXT_951);
    }
    } else if (setAccessorOperation != null) {
    stringBuffer.append(TEXT_952);
    stringBuffer.append(setAccessorOperation.getBody(genModel.getIndentation(stringBuffer)));
    } else {
    stringBuffer.append(TEXT_953);
    stringBuffer.append(genFeature.getFormattedName());
    stringBuffer.append(TEXT_954);
    stringBuffer.append(genFeature.getFeatureKind());
    stringBuffer.append(TEXT_955);
    //Class/setGenFeature.todo.override.javajetinc
    }
    stringBuffer.append(TEXT_956);
    }
    //Class/setGenFeature.override.javajetinc
    }
    if (isImplementation && !genModel.isReflectiveDelegation() && genFeature.isBasicUnset()) {
    stringBuffer.append(TEXT_957);
    if (isJDK50) { //Class/basicUnsetGenFeature.annotations.insert.javajetinc
    }
    stringBuffer.append(TEXT_958);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.notify.NotificationChain"));
    stringBuffer.append(TEXT_959);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_960);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.notify.NotificationChain"));
    stringBuffer.append(TEXT_961);
    if (genModel.isDynamicDelegation()) {
    stringBuffer.append(TEXT_962);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.InternalEObject"));
    stringBuffer.append(TEXT_963);
    if (genFeature.isResolveProxies()) {
    stringBuffer.append(TEXT_964);
    stringBuffer.append(genFeature.getAccessorName());
    } else {
    stringBuffer.append(genFeature.getGetAccessor());
    }
    stringBuffer.append(TEXT_965);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(positiveOffsetCorrection);
    stringBuffer.append(TEXT_966);
    } else if (!genFeature.isVolatile()) {
    if (genModel.isVirtualDelegation()) {
    if (!genModel.isSuppressNotification()) {
    stringBuffer.append(TEXT_967);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_968);
    }
    stringBuffer.append(TEXT_969);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(positiveOffsetCorrection);
    stringBuffer.append(TEXT_970);
    } else {
    if (!genModel.isSuppressNotification()) {
    stringBuffer.append(TEXT_971);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_972);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_973);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_974);
    }
    stringBuffer.append(TEXT_975);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_976);
    }
    if (genModel.isVirtualDelegation()) {
    if (!genModel.isSuppressNotification()) {
    stringBuffer.append(TEXT_977);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_978);
    }
    } else if (genClass.isESetFlag(genFeature)) {
    if (!genModel.isSuppressNotification()) {
    stringBuffer.append(TEXT_979);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_980);
    stringBuffer.append(genClass.getESetFlagsField(genFeature));
    stringBuffer.append(TEXT_981);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_982);
    }
    stringBuffer.append(TEXT_983);
    stringBuffer.append(genClass.getESetFlagsField(genFeature));
    stringBuffer.append(TEXT_984);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_985);
    } else {
    if (!genModel.isSuppressNotification()) {
    stringBuffer.append(TEXT_986);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_987);
    stringBuffer.append(genFeature.getUncapName());
    stringBuffer.append(TEXT_988);
    }
    stringBuffer.append(TEXT_989);
    stringBuffer.append(genFeature.getUncapName());
    stringBuffer.append(TEXT_990);
    }
    if (!genModel.isSuppressNotification()) {
    stringBuffer.append(TEXT_991);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.impl.ENotificationImpl"));
    stringBuffer.append(TEXT_992);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.impl.ENotificationImpl"));
    stringBuffer.append(TEXT_993);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.notify.Notification"));
    stringBuffer.append(TEXT_994);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(positiveOffsetCorrection);
    stringBuffer.append(TEXT_995);
    if (genModel.isVirtualDelegation()) {
    stringBuffer.append(TEXT_996);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_997);
    } else {
    stringBuffer.append(TEXT_998);
    stringBuffer.append(genFeature.getCapName());
    }
    stringBuffer.append(TEXT_999);
    if (genModel.isVirtualDelegation()) {
    stringBuffer.append(TEXT_1000);
    } else {
    stringBuffer.append(TEXT_1001);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_1002);
    }
    stringBuffer.append(TEXT_1003);
    }
    } else {
    stringBuffer.append(TEXT_1004);
    stringBuffer.append(genFeature.getFormattedName());
    stringBuffer.append(TEXT_1005);
    stringBuffer.append(genFeature.getFeatureKind());
    stringBuffer.append(TEXT_1006);
    //Class/basicUnsetGenFeature.todo.override.javajetinc
    }
    stringBuffer.append(TEXT_1007);
    //Class.basicUnsetGenFeature.override.javajetinc
    }
    if (genFeature.isUnset() && (isImplementation || !genFeature.isSuppressedUnsetVisibility())) {
    if (isInterface) {
    stringBuffer.append(TEXT_1008);
    stringBuffer.append(genClass.getQualifiedInterfaceName());
    stringBuffer.append(TEXT_1009);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_1010);
    stringBuffer.append(genFeature.getFormattedName());
    stringBuffer.append(TEXT_1011);
    stringBuffer.append(genFeature.getFeatureKind());
    stringBuffer.append(TEXT_1012);
    stringBuffer.append(TEXT_1013);
    if (!genFeature.isSuppressedIsSetVisibility()) {
    stringBuffer.append(TEXT_1014);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_1015);
    }
    stringBuffer.append(TEXT_1016);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_1017);
    if (!genFeature.isListType() && !genFeature.isSuppressedSetVisibility()) {
    stringBuffer.append(TEXT_1018);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_1019);
    stringBuffer.append(genFeature.getRawImportedBoundType());
    stringBuffer.append(TEXT_1020);
    }
    stringBuffer.append(TEXT_1021);
    //Class/unsetGenFeature.javadoc.override.javajetinc
    } else {
    stringBuffer.append(TEXT_1022);
    if (isJDK50) { //Class/unsetGenFeature.annotations.insert.javajetinc
    }
    }
    if (!isImplementation) {
    stringBuffer.append(TEXT_1023);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_1024);
    } else {
    stringBuffer.append(TEXT_1025);
    stringBuffer.append(genFeature.getAccessorName());
    if (genClass.hasCollidingUnsetAccessorOperation(genFeature)) {
    stringBuffer.append(TEXT_1026);
    }
    stringBuffer.append(TEXT_1027);
    if (genModel.isDynamicDelegation()) {
    stringBuffer.append(TEXT_1028);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(TEXT_1029);
    stringBuffer.append(genFeature.getQualifiedFeatureAccessor());
    stringBuffer.append(TEXT_1030);
    } else if (genModel.isReflectiveDelegation()) {
    stringBuffer.append(TEXT_1031);
    stringBuffer.append(genFeature.getQualifiedFeatureAccessor());
    stringBuffer.append(TEXT_1032);
    } else if (genFeature.hasSettingDelegate()) {
    stringBuffer.append(TEXT_1033);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_1034);
    } else if (!genFeature.isVolatile()) {
    if (genFeature.isListType()) {
    if (genModel.isVirtualDelegation()) {
    stringBuffer.append(TEXT_1035);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_1036);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_1037);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_1038);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(positiveOffsetCorrection);
    stringBuffer.append(TEXT_1039);
    }
    stringBuffer.append(TEXT_1040);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_1041);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.InternalEList"));
    stringBuffer.append(TEXT_1042);
    stringBuffer.append(singleWildcard);
    stringBuffer.append(TEXT_1043);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_1044);
    } else if (genFeature.isBidirectional() || genFeature.isEffectiveContains()) {
    if (genModel.isVirtualDelegation()) {
    stringBuffer.append(TEXT_1045);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_1046);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_1047);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_1048);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(positiveOffsetCorrection);
    stringBuffer.append(TEXT_1049);
    }
    stringBuffer.append(TEXT_1050);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_1051);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.notify.NotificationChain"));
    stringBuffer.append(TEXT_1052);
    if (!genFeature.isBidirectional()) {
    stringBuffer.append(TEXT_1053);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.InternalEObject"));
    stringBuffer.append(TEXT_1054);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_1055);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(negativeOffsetCorrection);
    stringBuffer.append(TEXT_1056);
    } else { GenFeature reverseFeature = genFeature.getReverse(); GenClass targetClass = reverseFeature.getGenClass(); String reverseOffsetCorrection = targetClass.hasOffsetCorrection() ? " + " + genClass.getOffsetCorrectionField(genFeature) : "";
    stringBuffer.append(TEXT_1057);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.InternalEObject"));
    stringBuffer.append(TEXT_1058);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_1059);
    stringBuffer.append(targetClass.getQualifiedFeatureID(reverseFeature));
    stringBuffer.append(reverseOffsetCorrection);
    stringBuffer.append(TEXT_1060);
    stringBuffer.append(targetClass.getRawImportedInterfaceName());
    stringBuffer.append(TEXT_1061);
    }
    stringBuffer.append(TEXT_1062);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_1063);
    if (genModel.isVirtualDelegation()) {
    stringBuffer.append(TEXT_1064);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_1065);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(positiveOffsetCorrection);
    stringBuffer.append(TEXT_1066);
    } else if (genClass.isESetFlag(genFeature)) {
    if (!genModel.isSuppressNotification()) {
    stringBuffer.append(TEXT_1067);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_1068);
    stringBuffer.append(genClass.getESetFlagsField(genFeature));
    stringBuffer.append(TEXT_1069);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_1070);
    }
    stringBuffer.append(TEXT_1071);
    stringBuffer.append(genClass.getESetFlagsField(genFeature));
    stringBuffer.append(TEXT_1072);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_1073);
    } else {
    if (!genModel.isSuppressNotification()) {
    stringBuffer.append(TEXT_1074);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_1075);
    stringBuffer.append(genFeature.getUncapName());
    stringBuffer.append(TEXT_1076);
    }
    stringBuffer.append(TEXT_1077);
    stringBuffer.append(genFeature.getUncapName());
    stringBuffer.append(TEXT_1078);
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
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_1083);
    }
    stringBuffer.append(TEXT_1084);
    } else {
    if (genClass.isFlag(genFeature)) {
    if (!genModel.isSuppressNotification()) {
    if (genFeature.isBooleanType()) {
    stringBuffer.append(TEXT_1085);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_1086);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_1087);
    stringBuffer.append(genClass.getFlagsField(genFeature));
    stringBuffer.append(TEXT_1088);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_1089);
    } else {
    stringBuffer.append(TEXT_1090);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_1091);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_1092);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_1093);
    stringBuffer.append(genClass.getFlagsField(genFeature));
    stringBuffer.append(TEXT_1094);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_1095);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_1096);
    }
    }
    } else if (genModel.isVirtualDelegation() && !genFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_1097);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_1098);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(positiveOffsetCorrection);
    stringBuffer.append(TEXT_1099);
    } else {
    if (!genModel.isSuppressNotification()) {
    stringBuffer.append(TEXT_1100);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_1101);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_1102);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_1103);
    }
    }
    if (!genModel.isSuppressNotification()) {
    if (genModel.isVirtualDelegation() && !genFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_1104);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_1105);
    } else if (genClass.isESetFlag(genFeature)) {
    stringBuffer.append(TEXT_1106);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_1107);
    stringBuffer.append(genClass.getESetFlagsField(genFeature));
    stringBuffer.append(TEXT_1108);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_1109);
    } else {
    stringBuffer.append(TEXT_1110);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_1111);
    stringBuffer.append(genFeature.getUncapName());
    stringBuffer.append(TEXT_1112);
    }
    }
    if (genFeature.isReferenceType()) {
    stringBuffer.append(TEXT_1113);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_1114);
    if (!genModel.isVirtualDelegation()) {
    if (genClass.isESetFlag(genFeature)) {
    stringBuffer.append(TEXT_1115);
    stringBuffer.append(genClass.getESetFlagsField(genFeature));
    stringBuffer.append(TEXT_1116);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_1117);
    } else {
    stringBuffer.append(TEXT_1118);
    stringBuffer.append(genFeature.getUncapName());
    stringBuffer.append(TEXT_1119);
    }
    }
    if (!genModel.isSuppressNotification()) {
    stringBuffer.append(TEXT_1120);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.impl.ENotificationImpl"));
    stringBuffer.append(TEXT_1121);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.notify.Notification"));
    stringBuffer.append(TEXT_1122);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(positiveOffsetCorrection);
    stringBuffer.append(TEXT_1123);
    if (genModel.isVirtualDelegation()) {
    stringBuffer.append(TEXT_1124);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_1125);
    } else {
    stringBuffer.append(TEXT_1126);
    stringBuffer.append(genFeature.getCapName());
    }
    stringBuffer.append(TEXT_1127);
    if (genModel.isVirtualDelegation()) {
    stringBuffer.append(TEXT_1128);
    } else {
    stringBuffer.append(TEXT_1129);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_1130);
    }
    stringBuffer.append(TEXT_1131);
    }
    } else {
    if (genClass.isFlag(genFeature)) {
    if (genFeature.isBooleanType()) {
    stringBuffer.append(TEXT_1132);
    stringBuffer.append(genFeature.getEDefault());
    stringBuffer.append(TEXT_1133);
    stringBuffer.append(genClass.getFlagsField(genFeature));
    stringBuffer.append(TEXT_1134);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_1135);
    stringBuffer.append(genClass.getFlagsField(genFeature));
    stringBuffer.append(TEXT_1136);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_1137);
    } else {
    stringBuffer.append(TEXT_1138);
    stringBuffer.append(genClass.getFlagsField(genFeature));
    stringBuffer.append(TEXT_1139);
    stringBuffer.append(genClass.getFlagsField(genFeature));
    stringBuffer.append(TEXT_1140);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_1141);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_1142);
    }
    } else if (!genModel.isVirtualDelegation() || genFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_1143);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_1144);
    stringBuffer.append(genFeature.getEDefault());
    stringBuffer.append(TEXT_1145);
    }
    if (!genModel.isVirtualDelegation() || genFeature.isPrimitiveType()) {
    if (genClass.isESetFlag(genFeature)) {
    stringBuffer.append(TEXT_1146);
    stringBuffer.append(genClass.getESetFlagsField(genFeature));
    stringBuffer.append(TEXT_1147);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_1148);
    } else {
    stringBuffer.append(TEXT_1149);
    stringBuffer.append(genFeature.getUncapName());
    stringBuffer.append(TEXT_1150);
    }
    }
    if (!genModel.isSuppressNotification()) {
    stringBuffer.append(TEXT_1151);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.impl.ENotificationImpl"));
    stringBuffer.append(TEXT_1152);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.notify.Notification"));
    stringBuffer.append(TEXT_1153);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(positiveOffsetCorrection);
    stringBuffer.append(TEXT_1154);
    if (genModel.isVirtualDelegation() && !genFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_1155);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_1156);
    stringBuffer.append(genFeature.getEDefault());
    } else {
    stringBuffer.append(TEXT_1157);
    stringBuffer.append(genFeature.getCapName());
    }
    stringBuffer.append(TEXT_1158);
    stringBuffer.append(genFeature.getEDefault());
    stringBuffer.append(TEXT_1159);
    if (genModel.isVirtualDelegation() && !genFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_1160);
    } else {
    stringBuffer.append(TEXT_1161);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_1162);
    }
    stringBuffer.append(TEXT_1163);
    }
    }
    }
    } else if (genFeature.hasDelegateFeature()) { GenFeature delegateFeature = genFeature.getDelegateFeature();
    if (delegateFeature.isWrappedFeatureMapType()) {
    stringBuffer.append(TEXT_1164);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.FeatureMap"));
    stringBuffer.append(TEXT_1165);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.FeatureMap"));
    stringBuffer.append(TEXT_1166);
    stringBuffer.append(delegateFeature.getAccessorName());
    stringBuffer.append(TEXT_1167);
    stringBuffer.append(genFeature.getQualifiedFeatureAccessor());
    stringBuffer.append(TEXT_1168);
    } else {
    stringBuffer.append(TEXT_1169);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.FeatureMap"));
    stringBuffer.append(TEXT_1170);
    stringBuffer.append(delegateFeature.getAccessorName());
    stringBuffer.append(TEXT_1171);
    stringBuffer.append(genFeature.getQualifiedFeatureAccessor());
    stringBuffer.append(TEXT_1172);
    }
    } else if (genClass.getUnsetAccessorOperation(genFeature) != null) {
    stringBuffer.append(TEXT_1173);
    stringBuffer.append(genClass.getUnsetAccessorOperation(genFeature).getBody(genModel.getIndentation(stringBuffer)));
    } else {
    stringBuffer.append(TEXT_1174);
    stringBuffer.append(genFeature.getFormattedName());
    stringBuffer.append(TEXT_1175);
    stringBuffer.append(genFeature.getFeatureKind());
    stringBuffer.append(TEXT_1176);
    //Class/unsetGenFeature.todo.override.javajetinc
    }
    stringBuffer.append(TEXT_1177);
    }
    //Class/unsetGenFeature.override.javajetinc
    }
    if (genFeature.isIsSet() && (isImplementation || !genFeature.isSuppressedIsSetVisibility())) {
    if (isInterface) {
    stringBuffer.append(TEXT_1178);
    stringBuffer.append(genClass.getQualifiedInterfaceName());
    stringBuffer.append(TEXT_1179);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_1180);
    stringBuffer.append(genFeature.getFormattedName());
    stringBuffer.append(TEXT_1181);
    stringBuffer.append(genFeature.getFeatureKind());
    stringBuffer.append(TEXT_1182);
    stringBuffer.append(TEXT_1183);
    stringBuffer.append(genFeature.getFormattedName());
    stringBuffer.append(TEXT_1184);
    stringBuffer.append(genFeature.getFeatureKind());
    stringBuffer.append(TEXT_1185);
    if (genFeature.isChangeable() && !genFeature.isSuppressedUnsetVisibility()) {
    stringBuffer.append(TEXT_1186);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_1187);
    }
    stringBuffer.append(TEXT_1188);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_1189);
    if (!genFeature.isListType() && genFeature.isChangeable() && !genFeature.isSuppressedSetVisibility()) {
    stringBuffer.append(TEXT_1190);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_1191);
    stringBuffer.append(genFeature.getRawImportedBoundType());
    stringBuffer.append(TEXT_1192);
    }
    stringBuffer.append(TEXT_1193);
    //Class/isSetGenFeature.javadoc.override.javajetinc
    } else {
    stringBuffer.append(TEXT_1194);
    if (isJDK50) { //Class/isSetGenFeature.annotations.insert.javajetinc
    }
    }
    if (!isImplementation) {
    stringBuffer.append(TEXT_1195);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_1196);
    } else {
    stringBuffer.append(TEXT_1197);
    stringBuffer.append(genFeature.getAccessorName());
    if (genClass.hasCollidingIsSetAccessorOperation(genFeature)) {
    stringBuffer.append(TEXT_1198);
    }
    stringBuffer.append(TEXT_1199);
    if (genModel.isDynamicDelegation()) {
    stringBuffer.append(TEXT_1200);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(TEXT_1201);
    stringBuffer.append(genFeature.getQualifiedFeatureAccessor());
    stringBuffer.append(TEXT_1202);
    } else if (genModel.isReflectiveDelegation()) {
    stringBuffer.append(TEXT_1203);
    stringBuffer.append(genFeature.getQualifiedFeatureAccessor());
    stringBuffer.append(TEXT_1204);
    } else if (genFeature.hasSettingDelegate()) {
    stringBuffer.append(TEXT_1205);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_1206);
    } else if (!genFeature.isVolatile()) {
    if (genFeature.isListType()) {
    if (genModel.isVirtualDelegation()) {
    stringBuffer.append(TEXT_1207);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_1208);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_1209);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_1210);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(positiveOffsetCorrection);
    stringBuffer.append(TEXT_1211);
    }
    stringBuffer.append(TEXT_1212);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_1213);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.InternalEList"));
    stringBuffer.append(TEXT_1214);
    stringBuffer.append(singleWildcard);
    stringBuffer.append(TEXT_1215);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_1216);
    } else {
    if (genModel.isVirtualDelegation() && !genFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_1217);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(positiveOffsetCorrection);
    stringBuffer.append(TEXT_1218);
    } else if (genClass.isESetFlag(genFeature)) {
    stringBuffer.append(TEXT_1219);
    stringBuffer.append(genClass.getESetFlagsField(genFeature));
    stringBuffer.append(TEXT_1220);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_1221);
    } else {
    stringBuffer.append(TEXT_1222);
    stringBuffer.append(genFeature.getUncapName());
    stringBuffer.append(TEXT_1223);
    }
    }
    } else if (genFeature.hasDelegateFeature()) { GenFeature delegateFeature = genFeature.getDelegateFeature();
    if (delegateFeature.isWrappedFeatureMapType()) {
    stringBuffer.append(TEXT_1224);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.FeatureMap"));
    stringBuffer.append(TEXT_1225);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.FeatureMap"));
    stringBuffer.append(TEXT_1226);
    stringBuffer.append(delegateFeature.getAccessorName());
    stringBuffer.append(TEXT_1227);
    stringBuffer.append(genFeature.getQualifiedFeatureAccessor());
    stringBuffer.append(TEXT_1228);
    } else {
    stringBuffer.append(TEXT_1229);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.FeatureMap"));
    stringBuffer.append(TEXT_1230);
    stringBuffer.append(delegateFeature.getAccessorName());
    stringBuffer.append(TEXT_1231);
    stringBuffer.append(genFeature.getQualifiedFeatureAccessor());
    stringBuffer.append(TEXT_1232);
    }
    } else if (genClass.getIsSetAccessorOperation(genFeature) != null) {
    stringBuffer.append(TEXT_1233);
    stringBuffer.append(genClass.getIsSetAccessorOperation(genFeature).getBody(genModel.getIndentation(stringBuffer)));
    } else {
    stringBuffer.append(TEXT_1234);
    stringBuffer.append(genFeature.getFormattedName());
    stringBuffer.append(TEXT_1235);
    stringBuffer.append(genFeature.getFeatureKind());
    stringBuffer.append(TEXT_1236);
    //Class/isSetGenFeature.todo.override.javajetinc
    }
    stringBuffer.append(TEXT_1237);
    }
    //Class/isSetGenFeature.override.javajetinc
    }
    //Class/genFeature.override.javajetinc
    }//for
    }}.run();
    for (GenOperation genOperation : (isImplementation ? genClass.getImplementedGenOperations() : genClass.getDeclaredGenOperations())) {
    if (isImplementation) {
    if (genOperation.isInvariant() && genOperation.hasInvariantExpression()) {
    stringBuffer.append(TEXT_1238);
    stringBuffer.append(genOperation.getName());
    stringBuffer.append(TEXT_1239);
    stringBuffer.append(genOperation.getParameterTypes(", "));
    stringBuffer.append(TEXT_1240);
    stringBuffer.append(genOperation.getFormattedName());
    stringBuffer.append(TEXT_1241);
    stringBuffer.append(genOperation.getName());
    stringBuffer.append(TEXT_1242);
    stringBuffer.append(genOperation.getParameterTypes(", "));
    stringBuffer.append(TEXT_1243);
    stringBuffer.append(genModel.getImportedName("java.lang.String"));
    stringBuffer.append(TEXT_1244);
    stringBuffer.append(CodeGenUtil.upperName(genClass.getUniqueName(genOperation), genModel.getLocale()));
    stringBuffer.append(TEXT_1245);
    stringBuffer.append(genOperation.getInvariantExpression("\t\t"));
    stringBuffer.append(TEXT_1246);
    stringBuffer.append(genModel.getNonNLS());
    stringBuffer.append(TEXT_1247);
    } else if (genOperation.hasInvocationDelegate()) {
    stringBuffer.append(TEXT_1248);
    stringBuffer.append(genOperation.getName());
    stringBuffer.append(TEXT_1249);
    stringBuffer.append(genOperation.getParameterTypes(", "));
    stringBuffer.append(TEXT_1250);
    stringBuffer.append(genOperation.getFormattedName());
    stringBuffer.append(TEXT_1251);
    stringBuffer.append(genOperation.getName());
    stringBuffer.append(TEXT_1252);
    stringBuffer.append(genOperation.getParameterTypes(", "));
    stringBuffer.append(TEXT_1253);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.EOperation"));
    stringBuffer.append(TEXT_1254);
    stringBuffer.append(CodeGenUtil.upperName(genClass.getUniqueName(genOperation), genModel.getLocale()));
    stringBuffer.append(TEXT_1255);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.EOperation"));
    stringBuffer.append(TEXT_1256);
    stringBuffer.append(genOperation.getQualifiedOperationAccessor());
    stringBuffer.append(TEXT_1257);
    }
    }
    if (isInterface) {
    stringBuffer.append(TEXT_1258);
    stringBuffer.append(TEXT_1259);
    if (genOperation.hasDocumentation() || genOperation.hasParameterDocumentation()) {
    stringBuffer.append(TEXT_1260);
    if (genOperation.hasDocumentation()) {
    stringBuffer.append(TEXT_1261);
    stringBuffer.append(genOperation.getDocumentation(genModel.getIndentation(stringBuffer)));
    }
    for (GenParameter genParameter : genOperation.getGenParameters()) {
    if (genParameter.hasDocumentation()) { String documentation = genParameter.getDocumentation("");
    if (documentation.contains("\n") || documentation.contains("\r")) {
    stringBuffer.append(TEXT_1262);
    stringBuffer.append(genParameter.getName());
    stringBuffer.append(TEXT_1263);
    stringBuffer.append(genParameter.getDocumentation(genModel.getIndentation(stringBuffer)));
    } else {
    stringBuffer.append(TEXT_1264);
    stringBuffer.append(genParameter.getName());
    stringBuffer.append(TEXT_1265);
    stringBuffer.append(genParameter.getDocumentation(genModel.getIndentation(stringBuffer)));
    }
    }
    }
    stringBuffer.append(TEXT_1266);
    }
    if (!genModel.isSuppressEMFModelTags()) { boolean first = true; for (StringTokenizer stringTokenizer = new StringTokenizer(genOperation.getModelInfo(), "\n\r"); stringTokenizer.hasMoreTokens(); ) { String modelInfo = stringTokenizer.nextToken(); if (first) { first = false;
    stringBuffer.append(TEXT_1267);
    stringBuffer.append(modelInfo);
    } else {
    stringBuffer.append(TEXT_1268);
    stringBuffer.append(modelInfo);
    }} if (first) {
    stringBuffer.append(TEXT_1269);
    }}
    stringBuffer.append(TEXT_1270);
    //Class/genOperation.javadoc.override.javajetinc
    } else {
    stringBuffer.append(TEXT_1271);
    if (isJDK50) { //Class/genOperation.annotations.insert.javajetinc
    }
    }
    if (!isImplementation) {
    stringBuffer.append(TEXT_1272);
    stringBuffer.append(genOperation.getTypeParameters(genClass));
    stringBuffer.append(genOperation.getImportedType(genClass));
    stringBuffer.append(TEXT_1273);
    stringBuffer.append(genOperation.getName());
    stringBuffer.append(TEXT_1274);
    stringBuffer.append(genOperation.getParameters(genClass));
    stringBuffer.append(TEXT_1275);
    stringBuffer.append(genOperation.getThrows(genClass));
    stringBuffer.append(TEXT_1276);
    } else {
    if (genModel.useGenerics() && !genOperation.hasBody() && !genOperation.isInvariant() && genOperation.hasInvocationDelegate() && genOperation.isUncheckedCast(genClass)) {
    stringBuffer.append(TEXT_1277);
    }
    stringBuffer.append(TEXT_1278);
    stringBuffer.append(genOperation.getTypeParameters(genClass));
    stringBuffer.append(genOperation.getImportedType(genClass));
    stringBuffer.append(TEXT_1279);
    stringBuffer.append(genOperation.getName());
    stringBuffer.append(TEXT_1280);
    stringBuffer.append(genOperation.getParameters(isImplementation, genClass));
    stringBuffer.append(TEXT_1281);
    stringBuffer.append(genOperation.getThrows(genClass));
    stringBuffer.append(TEXT_1282);
    if (genOperation.hasBody()) {
    stringBuffer.append(TEXT_1283);
    stringBuffer.append(genOperation.getBody(genModel.getIndentation(stringBuffer)));
    } else if (genOperation.isInvariant()) {GenClass opClass = genOperation.getGenClass(); String diagnostics = genOperation.getGenParameters().get(0).getName(); String context = genOperation.getGenParameters().get(1).getName();
    if (genOperation.hasInvariantExpression()) {
    stringBuffer.append(TEXT_1284);
    stringBuffer.append(opClass.getGenPackage().getImportedValidatorClassName());
    stringBuffer.append(TEXT_1285);
    stringBuffer.append(genClass.getQualifiedClassifierAccessor());
    stringBuffer.append(TEXT_1286);
    stringBuffer.append(diagnostics);
    stringBuffer.append(TEXT_1287);
    stringBuffer.append(context);
    stringBuffer.append(TEXT_1288);
    stringBuffer.append(genOperation.getValidationDelegate());
    stringBuffer.append(TEXT_1289);
    stringBuffer.append(genModel.getNonNLS());
    stringBuffer.append(TEXT_1290);
    stringBuffer.append(genOperation.getQualifiedOperationAccessor());
    stringBuffer.append(TEXT_1291);
    stringBuffer.append(CodeGenUtil.upperName(genClass.getUniqueName(genOperation), genModel.getLocale()));
    stringBuffer.append(TEXT_1292);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.util.Diagnostic"));
    stringBuffer.append(TEXT_1293);
    stringBuffer.append(opClass.getGenPackage().getImportedValidatorClassName());
    stringBuffer.append(TEXT_1294);
    stringBuffer.append(opClass.getGenPackage().getImportedValidatorClassName());
    stringBuffer.append(TEXT_1295);
    stringBuffer.append(opClass.getOperationID(genOperation));
    stringBuffer.append(TEXT_1296);
    } else {
    stringBuffer.append(TEXT_1297);
    stringBuffer.append(diagnostics);
    stringBuffer.append(TEXT_1298);
    stringBuffer.append(diagnostics);
    stringBuffer.append(TEXT_1299);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.util.BasicDiagnostic"));
    stringBuffer.append(TEXT_1300);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.util.Diagnostic"));
    stringBuffer.append(TEXT_1301);
    stringBuffer.append(opClass.getGenPackage().getImportedValidatorClassName());
    stringBuffer.append(TEXT_1302);
    stringBuffer.append(opClass.getGenPackage().getImportedValidatorClassName());
    stringBuffer.append(TEXT_1303);
    stringBuffer.append(opClass.getOperationID(genOperation));
    stringBuffer.append(TEXT_1304);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.plugin.EcorePlugin"));
    stringBuffer.append(TEXT_1305);
    stringBuffer.append(genOperation.getName());
    stringBuffer.append(TEXT_1306);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.EObjectValidator"));
    stringBuffer.append(TEXT_1307);
    stringBuffer.append(context);
    stringBuffer.append(TEXT_1308);
    stringBuffer.append(genModel.getNonNLS());
    stringBuffer.append(genModel.getNonNLS(2));
    stringBuffer.append(TEXT_1309);
    }
    } else if (genOperation.hasInvocationDelegate()) { int size = genOperation.getGenParameters().size();
    stringBuffer.append(TEXT_1310);
    if (genOperation.isVoid()) {
    stringBuffer.append(TEXT_1311);
    stringBuffer.append(CodeGenUtil.upperName(genClass.getUniqueName(genOperation), genModel.getLocale()));
    stringBuffer.append(TEXT_1312);
    if (size > 0) {
    stringBuffer.append(TEXT_1313);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.util.BasicEList"));
    stringBuffer.append(TEXT_1314);
    stringBuffer.append(size);
    stringBuffer.append(TEXT_1315);
    stringBuffer.append(genOperation.getParametersArray(genClass));
    stringBuffer.append(TEXT_1316);
    } else {
    stringBuffer.append(TEXT_1317);
    }
    stringBuffer.append(TEXT_1318);
    } else {
    stringBuffer.append(TEXT_1319);
    if (!isJDK50 && genOperation.isPrimitiveType()) {
    stringBuffer.append(TEXT_1320);
    }
    stringBuffer.append(TEXT_1321);
    stringBuffer.append(genOperation.getObjectType(genClass));
    stringBuffer.append(TEXT_1322);
    stringBuffer.append(CodeGenUtil.upperName(genClass.getUniqueName(genOperation), genModel.getLocale()));
    stringBuffer.append(TEXT_1323);
    if (size > 0) {
    stringBuffer.append(TEXT_1324);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.util.BasicEList"));
    stringBuffer.append(TEXT_1325);
    stringBuffer.append(size);
    stringBuffer.append(TEXT_1326);
    stringBuffer.append(genOperation.getParametersArray(genClass));
    stringBuffer.append(TEXT_1327);
    } else {
    stringBuffer.append(TEXT_1328);
    }
    stringBuffer.append(TEXT_1329);
    if (!isJDK50 && genOperation.isPrimitiveType()) {
    stringBuffer.append(TEXT_1330);
    stringBuffer.append(genOperation.getPrimitiveValueFunction());
    stringBuffer.append(TEXT_1331);
    }
    stringBuffer.append(TEXT_1332);
    }
    stringBuffer.append(TEXT_1333);
    stringBuffer.append(genModel.getImportedName(isGWT ? "org.eclipse.emf.common.util.InvocationTargetException" : "java.lang.reflect.InvocationTargetException"));
    stringBuffer.append(TEXT_1334);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.util.WrappedException"));
    stringBuffer.append(TEXT_1335);
    } else {
    stringBuffer.append(TEXT_1336);
    //Class/implementedGenOperation.todo.override.javajetinc
    }
    stringBuffer.append(TEXT_1337);
    }
    //Class/implementedGenOperation.override.javajetinc
    }//for
    if (isImplementation && !genModel.isReflectiveDelegation() && genClass.implementsAny(genClass.getEInverseAddGenFeatures())) {
    stringBuffer.append(TEXT_1338);
    if (genModel.useGenerics()) {
    for (GenFeature genFeature : genClass.getEInverseAddGenFeatures()) {
    if (genFeature.isUncheckedCast(genClass)) {
    stringBuffer.append(TEXT_1339);
    break; }
    }
    }
    if (genModel.useClassOverrideAnnotation()) {
    stringBuffer.append(TEXT_1340);
    }
    stringBuffer.append(TEXT_1341);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.notify.NotificationChain"));
    stringBuffer.append(TEXT_1342);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.InternalEObject"));
    stringBuffer.append(TEXT_1343);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.notify.NotificationChain"));
    stringBuffer.append(TEXT_1344);
    stringBuffer.append(negativeOffsetCorrection);
    stringBuffer.append(TEXT_1345);
    for (GenFeature genFeature : genClass.getEInverseAddGenFeatures()) {
    stringBuffer.append(TEXT_1346);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(TEXT_1347);
    if (genFeature.isListType()) { String cast = "("  + genModel.getImportedName("org.eclipse.emf.ecore.util.InternalEList") + (!genModel.useGenerics() ? ")" : "<" + genModel.getImportedName("org.eclipse.emf.ecore.InternalEObject") + ">)(" + genModel.getImportedName("org.eclipse.emf.ecore.util.InternalEList") + "<?>)");
    if (genFeature.isMapType() && genFeature.isEffectiveSuppressEMFTypes()) {
    stringBuffer.append(TEXT_1348);
    stringBuffer.append(cast);
    stringBuffer.append(TEXT_1349);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.util.EMap"));
    stringBuffer.append(TEXT_1350);
    stringBuffer.append(genFeature.getImportedMapTemplateArguments(genClass));
    stringBuffer.append(TEXT_1351);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_1352);
    } else {
    stringBuffer.append(TEXT_1353);
    stringBuffer.append(cast);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_1354);
    }
    } else if (genFeature.isContainer()) {
    stringBuffer.append(TEXT_1355);
    if (genFeature.isBasicSet()) {
    stringBuffer.append(TEXT_1356);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_1357);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_1358);
    } else {
    stringBuffer.append(TEXT_1359);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(positiveOffsetCorrection);
    stringBuffer.append(TEXT_1360);
    }
    } else {
    if (genClass.getImplementingGenModel(genFeature).isVirtualDelegation()) {
    stringBuffer.append(TEXT_1361);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_1362);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_1363);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_1364);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(positiveOffsetCorrection);
    stringBuffer.append(TEXT_1365);
    } else if (genFeature.isVolatile() || genClass.getImplementingGenModel(genFeature).isDynamicDelegation()) {
    stringBuffer.append(TEXT_1366);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_1367);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_1368);
    if (genFeature.isResolveProxies()) {
    stringBuffer.append(TEXT_1369);
    stringBuffer.append(genFeature.getAccessorName());
    } else {
    stringBuffer.append(genFeature.getGetAccessor());
    }
    stringBuffer.append(TEXT_1370);
    }
    stringBuffer.append(TEXT_1371);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_1372);
    if (genFeature.isEffectiveContains()) {
    stringBuffer.append(TEXT_1373);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.InternalEObject"));
    stringBuffer.append(TEXT_1374);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_1375);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(negativeOffsetCorrection);
    stringBuffer.append(TEXT_1376);
    } else { GenFeature reverseFeature = genFeature.getReverse(); GenClass targetClass = reverseFeature.getGenClass(); String reverseOffsetCorrection = targetClass.hasOffsetCorrection() ? " + " + genClass.getOffsetCorrectionField(genFeature) : "";
    stringBuffer.append(TEXT_1377);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.InternalEObject"));
    stringBuffer.append(TEXT_1378);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_1379);
    stringBuffer.append(targetClass.getQualifiedFeatureID(reverseFeature));
    stringBuffer.append(reverseOffsetCorrection);
    stringBuffer.append(TEXT_1380);
    stringBuffer.append(targetClass.getRawImportedInterfaceName());
    stringBuffer.append(TEXT_1381);
    }
    stringBuffer.append(TEXT_1382);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_1383);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_1384);
    }
    }
    stringBuffer.append(TEXT_1385);
    if (genModel.isMinimalReflectiveMethods()) {
    stringBuffer.append(TEXT_1386);
    } else {
    stringBuffer.append(TEXT_1387);
    }
    stringBuffer.append(TEXT_1388);
    }
    if (isImplementation && !genModel.isReflectiveDelegation() && genClass.implementsAny(genClass.getEInverseRemoveGenFeatures())) {
    stringBuffer.append(TEXT_1389);
    if (genModel.useClassOverrideAnnotation()) {
    stringBuffer.append(TEXT_1390);
    }
    stringBuffer.append(TEXT_1391);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.notify.NotificationChain"));
    stringBuffer.append(TEXT_1392);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.InternalEObject"));
    stringBuffer.append(TEXT_1393);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.notify.NotificationChain"));
    stringBuffer.append(TEXT_1394);
    stringBuffer.append(negativeOffsetCorrection);
    stringBuffer.append(TEXT_1395);
    for (GenFeature genFeature : genClass.getEInverseRemoveGenFeatures()) {
    stringBuffer.append(TEXT_1396);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(TEXT_1397);
    if (genFeature.isListType()) {
    if (genFeature.isMapType() && genFeature.isEffectiveSuppressEMFTypes()) {
    stringBuffer.append(TEXT_1398);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.InternalEList"));
    stringBuffer.append(singleWildcard);
    stringBuffer.append(TEXT_1399);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.util.EMap"));
    stringBuffer.append(TEXT_1400);
    stringBuffer.append(genFeature.getImportedMapTemplateArguments(genClass));
    stringBuffer.append(TEXT_1401);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_1402);
    } else if (genFeature.isWrappedFeatureMapType()) {
    stringBuffer.append(TEXT_1403);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.InternalEList"));
    stringBuffer.append(singleWildcard);
    stringBuffer.append(TEXT_1404);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.FeatureMap"));
    stringBuffer.append(TEXT_1405);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_1406);
    } else {
    stringBuffer.append(TEXT_1407);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.InternalEList"));
    stringBuffer.append(singleWildcard);
    stringBuffer.append(TEXT_1408);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_1409);
    }
    } else if (genFeature.isContainer() && !genFeature.isBasicSet()) {
    stringBuffer.append(TEXT_1410);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(positiveOffsetCorrection);
    stringBuffer.append(TEXT_1411);
    } else if (genFeature.isUnsettable()) {
    stringBuffer.append(TEXT_1412);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_1413);
    } else {
    stringBuffer.append(TEXT_1414);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_1415);
    }
    }
    stringBuffer.append(TEXT_1416);
    if (genModel.isMinimalReflectiveMethods()) {
    stringBuffer.append(TEXT_1417);
    } else {
    stringBuffer.append(TEXT_1418);
    }
    stringBuffer.append(TEXT_1419);
    }
    if (isImplementation && !genModel.isReflectiveDelegation() && genClass.implementsAny(genClass.getEBasicRemoveFromContainerGenFeatures())) {
    stringBuffer.append(TEXT_1420);
    if (genModel.useClassOverrideAnnotation()) {
    stringBuffer.append(TEXT_1421);
    }
    stringBuffer.append(TEXT_1422);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.notify.NotificationChain"));
    stringBuffer.append(TEXT_1423);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.notify.NotificationChain"));
    stringBuffer.append(TEXT_1424);
    stringBuffer.append(negativeOffsetCorrection);
    stringBuffer.append(TEXT_1425);
    for (GenFeature genFeature : genClass.getEBasicRemoveFromContainerGenFeatures()) {
    GenFeature reverseFeature = genFeature.getReverse(); GenClass targetClass = reverseFeature.getGenClass(); String reverseOffsetCorrection = targetClass.hasOffsetCorrection() ? " + " + genClass.getOffsetCorrectionField(genFeature) : "";
    stringBuffer.append(TEXT_1426);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(TEXT_1427);
    stringBuffer.append(targetClass.getQualifiedFeatureID(reverseFeature));
    stringBuffer.append(reverseOffsetCorrection);
    stringBuffer.append(TEXT_1428);
    stringBuffer.append(targetClass.getRawImportedInterfaceName());
    stringBuffer.append(TEXT_1429);
    }
    stringBuffer.append(TEXT_1430);
    if (genModel.isMinimalReflectiveMethods()) {
    stringBuffer.append(TEXT_1431);
    } else {
    stringBuffer.append(TEXT_1432);
    }
    stringBuffer.append(TEXT_1433);
    }
    if (isImplementation && !genModel.isReflectiveDelegation() && genClass.implementsAny(genClass.getEGetGenFeatures())) {
    stringBuffer.append(TEXT_1434);
    if (genModel.useClassOverrideAnnotation()) {
    stringBuffer.append(TEXT_1435);
    }
    stringBuffer.append(TEXT_1436);
    stringBuffer.append(negativeOffsetCorrection);
    stringBuffer.append(TEXT_1437);
    for (GenFeature genFeature : genClass.getEGetGenFeatures()) {
    stringBuffer.append(TEXT_1438);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(TEXT_1439);
    if (genFeature.isPrimitiveType()) {
    if (isJDK50) {
    stringBuffer.append(TEXT_1440);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_1441);
    } else if (genFeature.isBooleanType()) {
    stringBuffer.append(TEXT_1442);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_1443);
    } else {
    stringBuffer.append(TEXT_1444);
    stringBuffer.append(genFeature.getObjectType(genClass));
    stringBuffer.append(TEXT_1445);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_1446);
    }
    } else if (genFeature.isResolveProxies() && !genFeature.isListType()) {
    stringBuffer.append(TEXT_1447);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_1448);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_1449);
    } else if (genFeature.isMapType()) {
    if (genFeature.isEffectiveSuppressEMFTypes()) {
    stringBuffer.append(TEXT_1450);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.util.EMap"));
    stringBuffer.append(TEXT_1451);
    stringBuffer.append(genFeature.getImportedMapTemplateArguments(genClass));
    stringBuffer.append(TEXT_1452);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_1453);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_1454);
    } else {
    stringBuffer.append(TEXT_1455);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_1456);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_1457);
    }
    } else if (genFeature.isWrappedFeatureMapType()) {
    stringBuffer.append(TEXT_1458);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.FeatureMap"));
    stringBuffer.append(TEXT_1459);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_1460);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_1461);
    } else if (genFeature.isFeatureMapType()) {
    stringBuffer.append(TEXT_1462);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_1463);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.FeatureMap"));
    stringBuffer.append(TEXT_1464);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_1465);
    } else {
    stringBuffer.append(TEXT_1466);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_1467);
    }
    }
    stringBuffer.append(TEXT_1468);
    if (genModel.isMinimalReflectiveMethods()) {
    stringBuffer.append(TEXT_1469);
    } else {
    stringBuffer.append(TEXT_1470);
    }
    stringBuffer.append(TEXT_1471);
    }
    if (isImplementation && !genModel.isReflectiveDelegation() && genClass.implementsAny(genClass.getESetGenFeatures())) {
    stringBuffer.append(TEXT_1472);
    if (genModel.useGenerics()) {
    for (GenFeature genFeature : genClass.getESetGenFeatures()) {
    if (genFeature.isUncheckedCast(genClass) && !genFeature.isFeatureMapType() && !genFeature.isMapType()) {
    stringBuffer.append(TEXT_1473);
    break; }
    }
    }
    if (genModel.useClassOverrideAnnotation()) {
    stringBuffer.append(TEXT_1474);
    }
    stringBuffer.append(TEXT_1475);
    stringBuffer.append(negativeOffsetCorrection);
    stringBuffer.append(TEXT_1476);
    for (GenFeature genFeature : genClass.getESetGenFeatures()) {
    stringBuffer.append(TEXT_1477);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(TEXT_1478);
    if (genFeature.isListType()) {
    if (genFeature.isWrappedFeatureMapType()) {
    stringBuffer.append(TEXT_1479);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.FeatureMap"));
    stringBuffer.append(TEXT_1480);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.FeatureMap"));
    stringBuffer.append(TEXT_1481);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_1482);
    } else if (genFeature.isFeatureMapType()) {
    stringBuffer.append(TEXT_1483);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.FeatureMap"));
    stringBuffer.append(TEXT_1484);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_1485);
    } else if (genFeature.isMapType()) {
    if (genFeature.isEffectiveSuppressEMFTypes()) {
    stringBuffer.append(TEXT_1486);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.EStructuralFeature"));
    stringBuffer.append(TEXT_1487);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.util.EMap"));
    stringBuffer.append(TEXT_1488);
    stringBuffer.append(genFeature.getImportedMapTemplateArguments(genClass));
    stringBuffer.append(TEXT_1489);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_1490);
    } else {
    stringBuffer.append(TEXT_1491);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.EStructuralFeature"));
    stringBuffer.append(TEXT_1492);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_1493);
    }
    } else {
    stringBuffer.append(TEXT_1494);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_1495);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_1496);
    stringBuffer.append(genModel.getImportedName("java.util.Collection"));
    if (isJDK50) {
    stringBuffer.append(TEXT_1497);
    stringBuffer.append(genFeature.getListItemType(genClass));
    stringBuffer.append(TEXT_1498);
    }
    stringBuffer.append(TEXT_1499);
    }
    } else if (!isJDK50 && genFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_1500);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_1501);
    stringBuffer.append(genFeature.getObjectType(genClass));
    stringBuffer.append(TEXT_1502);
    stringBuffer.append(genFeature.getPrimitiveValueFunction());
    stringBuffer.append(TEXT_1503);
    } else {
    stringBuffer.append(TEXT_1504);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_1505);
    if (genFeature.getTypeGenDataType() == null || !genFeature.getTypeGenDataType().isObjectType() || !genFeature.getRawType().equals(genFeature.getType(genClass))) {
    stringBuffer.append(TEXT_1506);
    stringBuffer.append(genFeature.getObjectType(genClass));
    stringBuffer.append(TEXT_1507);
    }
    stringBuffer.append(TEXT_1508);
    }
    stringBuffer.append(TEXT_1509);
    }
    stringBuffer.append(TEXT_1510);
    if (genModel.isMinimalReflectiveMethods()) {
    stringBuffer.append(TEXT_1511);
    } else {
    stringBuffer.append(TEXT_1512);
    }
    stringBuffer.append(TEXT_1513);
    }
    if (isImplementation && !genModel.isReflectiveDelegation() && genClass.implementsAny(genClass.getEUnsetGenFeatures())) {
    stringBuffer.append(TEXT_1514);
    if (genModel.useClassOverrideAnnotation()) {
    stringBuffer.append(TEXT_1515);
    }
    stringBuffer.append(TEXT_1516);
    stringBuffer.append(negativeOffsetCorrection);
    stringBuffer.append(TEXT_1517);
    for (GenFeature genFeature : genClass.getEUnsetGenFeatures()) {
    stringBuffer.append(TEXT_1518);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(TEXT_1519);
    if (genFeature.isListType() && !genFeature.isUnsettable()) {
    if (genFeature.isWrappedFeatureMapType()) {
    stringBuffer.append(TEXT_1520);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.FeatureMap"));
    stringBuffer.append(TEXT_1521);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_1522);
    } else {
    stringBuffer.append(TEXT_1523);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_1524);
    }
    } else if (genFeature.isUnsettable()) {
    stringBuffer.append(TEXT_1525);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_1526);
    } else if (!genFeature.hasEDefault()) {
    stringBuffer.append(TEXT_1527);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_1528);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_1529);
    } else if (genFeature.hasSettingDelegate()) {
    stringBuffer.append(TEXT_1530);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_1531);
    } else {
    stringBuffer.append(TEXT_1532);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_1533);
    stringBuffer.append(genFeature.getEDefault());
    stringBuffer.append(TEXT_1534);
    }
    stringBuffer.append(TEXT_1535);
    }
    stringBuffer.append(TEXT_1536);
    if (genModel.isMinimalReflectiveMethods()) {
    stringBuffer.append(TEXT_1537);
    } else {
    stringBuffer.append(TEXT_1538);
    }
    stringBuffer.append(TEXT_1539);
    //Class/eUnset.override.javajetinc
    }
    if (isImplementation && !genModel.isReflectiveDelegation() && genClass.implementsAny(genClass.getEIsSetGenFeatures())) {
    stringBuffer.append(TEXT_1540);
    if (genModel.useGenerics()) {
    for (GenFeature genFeature : genClass.getEIsSetGenFeatures()) {
    if (genFeature.isListType() && !genFeature.isUnsettable() && !genFeature.isWrappedFeatureMapType() && !genClass.isField(genFeature) && genFeature.isField() && genClass.getImplementingGenModel(genFeature).isVirtualDelegation()) {
    stringBuffer.append(TEXT_1541);
    break; }
    }
    }
    if (genModel.useClassOverrideAnnotation()) {
    stringBuffer.append(TEXT_1542);
    }
    stringBuffer.append(TEXT_1543);
    stringBuffer.append(negativeOffsetCorrection);
    stringBuffer.append(TEXT_1544);
    for (GenFeature genFeature : genClass.getEIsSetGenFeatures()) { String safeNameAccessor = genFeature.getSafeName(); if ("featureID".equals(safeNameAccessor)) { safeNameAccessor = "this." + safeNameAccessor; }
    stringBuffer.append(TEXT_1545);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(TEXT_1546);
    if (genFeature.hasSettingDelegate()) {
    if (genFeature.isUnsettable()) {
    stringBuffer.append(TEXT_1547);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_1548);
    } else {
    stringBuffer.append(TEXT_1549);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_1550);
    }
    } else if (genFeature.isListType() && !genFeature.isUnsettable()) {
    if (genFeature.isWrappedFeatureMapType()) {
    if (genFeature.isVolatile()) {
    stringBuffer.append(TEXT_1551);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.FeatureMap"));
    stringBuffer.append(TEXT_1552);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_1553);
    } else {
    stringBuffer.append(TEXT_1554);
    stringBuffer.append(safeNameAccessor);
    stringBuffer.append(TEXT_1555);
    stringBuffer.append(safeNameAccessor);
    stringBuffer.append(TEXT_1556);
    }
    } else {
    if (genClass.isField(genFeature)) {
    stringBuffer.append(TEXT_1557);
    stringBuffer.append(safeNameAccessor);
    stringBuffer.append(TEXT_1558);
    stringBuffer.append(safeNameAccessor);
    stringBuffer.append(TEXT_1559);
    } else {
    if (genFeature.isField() && genClass.getImplementingGenModel(genFeature).isVirtualDelegation()) {
    stringBuffer.append(TEXT_1560);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_1561);
    stringBuffer.append(safeNameAccessor);
    stringBuffer.append(TEXT_1562);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_1563);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(positiveOffsetCorrection);
    stringBuffer.append(TEXT_1564);
    stringBuffer.append(safeNameAccessor);
    stringBuffer.append(TEXT_1565);
    stringBuffer.append(safeNameAccessor);
    stringBuffer.append(TEXT_1566);
    } else {
    stringBuffer.append(TEXT_1567);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_1568);
    }
    }
    }
    } else if (genFeature.isUnsettable()) {
    stringBuffer.append(TEXT_1569);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_1570);
    } else if (genFeature.isResolveProxies()) {
    if (genClass.isField(genFeature)) {
    stringBuffer.append(TEXT_1571);
    stringBuffer.append(safeNameAccessor);
    stringBuffer.append(TEXT_1572);
    } else {
    if (genFeature.isField() && genClass.getImplementingGenModel(genFeature).isVirtualDelegation()) {
    stringBuffer.append(TEXT_1573);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(positiveOffsetCorrection);
    stringBuffer.append(TEXT_1574);
    } else {
    stringBuffer.append(TEXT_1575);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_1576);
    }
    }
    } else if (!genFeature.hasEDefault()) {
    if (genClass.isField(genFeature)) {
    stringBuffer.append(TEXT_1577);
    stringBuffer.append(safeNameAccessor);
    stringBuffer.append(TEXT_1578);
    } else {
    if (genFeature.isField() && genClass.getImplementingGenModel(genFeature).isVirtualDelegation()) {
    stringBuffer.append(TEXT_1579);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(positiveOffsetCorrection);
    stringBuffer.append(TEXT_1580);
    } else {
    stringBuffer.append(TEXT_1581);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_1582);
    }
    }
    } else if (genFeature.isPrimitiveType() || genFeature.isEnumType()) {
    if (genClass.isField(genFeature)) {
    if (genClass.isFlag(genFeature)) {
    if (genFeature.isBooleanType()) {
    stringBuffer.append(TEXT_1583);
    stringBuffer.append(genClass.getFlagsField(genFeature));
    stringBuffer.append(TEXT_1584);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_1585);
    stringBuffer.append(genFeature.getEDefault());
    stringBuffer.append(TEXT_1586);
    } else {
    stringBuffer.append(TEXT_1587);
    stringBuffer.append(genClass.getFlagsField(genFeature));
    stringBuffer.append(TEXT_1588);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_1589);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_1590);
    }
    } else {
    stringBuffer.append(TEXT_1591);
    stringBuffer.append(safeNameAccessor);
    stringBuffer.append(TEXT_1592);
    stringBuffer.append(genFeature.getEDefault());
    stringBuffer.append(TEXT_1593);
    }
    } else {
    if (genFeature.isEnumType() && genFeature.isField() && genClass.getImplementingGenModel(genFeature).isVirtualDelegation()) {
    stringBuffer.append(TEXT_1594);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(positiveOffsetCorrection);
    stringBuffer.append(TEXT_1595);
    stringBuffer.append(genFeature.getEDefault());
    stringBuffer.append(TEXT_1596);
    stringBuffer.append(genFeature.getEDefault());
    stringBuffer.append(TEXT_1597);
    } else {
    stringBuffer.append(TEXT_1598);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_1599);
    stringBuffer.append(genFeature.getEDefault());
    stringBuffer.append(TEXT_1600);
    }
    }
    } else {//datatype
    if (genClass.isField(genFeature)) {
    stringBuffer.append(TEXT_1601);
    stringBuffer.append(genFeature.getEDefault());
    stringBuffer.append(TEXT_1602);
    stringBuffer.append(safeNameAccessor);
    stringBuffer.append(TEXT_1603);
    stringBuffer.append(genFeature.getEDefault());
    stringBuffer.append(TEXT_1604);
    stringBuffer.append(safeNameAccessor);
    stringBuffer.append(TEXT_1605);
    } else {
    if (genFeature.isField() && genClass.getImplementingGenModel(genFeature).isVirtualDelegation()) {
    stringBuffer.append(TEXT_1606);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_1607);
    stringBuffer.append(safeNameAccessor);
    stringBuffer.append(TEXT_1608);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_1609);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(positiveOffsetCorrection);
    stringBuffer.append(TEXT_1610);
    stringBuffer.append(genFeature.getEDefault());
    stringBuffer.append(TEXT_1611);
    stringBuffer.append(genFeature.getEDefault());
    stringBuffer.append(TEXT_1612);
    stringBuffer.append(safeNameAccessor);
    stringBuffer.append(TEXT_1613);
    stringBuffer.append(genFeature.getEDefault());
    stringBuffer.append(TEXT_1614);
    stringBuffer.append(safeNameAccessor);
    stringBuffer.append(TEXT_1615);
    } else {
    stringBuffer.append(TEXT_1616);
    stringBuffer.append(genFeature.getEDefault());
    stringBuffer.append(TEXT_1617);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_1618);
    stringBuffer.append(genFeature.getEDefault());
    stringBuffer.append(TEXT_1619);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_1620);
    }
    }
    }
    }
    stringBuffer.append(TEXT_1621);
    if (genModel.isMinimalReflectiveMethods()) {
    stringBuffer.append(TEXT_1622);
    } else {
    stringBuffer.append(TEXT_1623);
    }
    stringBuffer.append(TEXT_1624);
    //Class/eIsSet.override.javajetinc
    }
    if (isImplementation && (!genClass.getMixinGenFeatures().isEmpty() || genClass.hasOffsetCorrection() && !genClass.getGenFeatures().isEmpty())) {
    if (!genClass.getMixinGenFeatures().isEmpty()) {
    stringBuffer.append(TEXT_1625);
    if (genModel.useClassOverrideAnnotation()) {
    stringBuffer.append(TEXT_1626);
    }
    stringBuffer.append(TEXT_1627);
    stringBuffer.append(singleWildcard);
    stringBuffer.append(TEXT_1628);
    for (GenClass mixinGenClass : genClass.getMixinGenClasses()) {
    stringBuffer.append(TEXT_1629);
    stringBuffer.append(mixinGenClass.getRawImportedInterfaceName());
    stringBuffer.append(TEXT_1630);
    stringBuffer.append(negativeOffsetCorrection);
    stringBuffer.append(TEXT_1631);
    for (GenFeature genFeature : mixinGenClass.getGenFeatures()) {
    stringBuffer.append(TEXT_1632);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(TEXT_1633);
    stringBuffer.append(mixinGenClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(TEXT_1634);
    }
    stringBuffer.append(TEXT_1635);
    }
    stringBuffer.append(TEXT_1636);
    }
    stringBuffer.append(TEXT_1637);
    if (genModel.useClassOverrideAnnotation()) {
    stringBuffer.append(TEXT_1638);
    }
    stringBuffer.append(TEXT_1639);
    stringBuffer.append(singleWildcard);
    stringBuffer.append(TEXT_1640);
    for (GenClass mixinGenClass : genClass.getMixinGenClasses()) {
    stringBuffer.append(TEXT_1641);
    stringBuffer.append(mixinGenClass.getRawImportedInterfaceName());
    stringBuffer.append(TEXT_1642);
    for (GenFeature genFeature : mixinGenClass.getGenFeatures()) {
    stringBuffer.append(TEXT_1643);
    stringBuffer.append(mixinGenClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(TEXT_1644);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(positiveOffsetCorrection);
    stringBuffer.append(TEXT_1645);
    }
    stringBuffer.append(TEXT_1646);
    }
    if (genClass.hasOffsetCorrection() && !genClass.getGenFeatures().isEmpty()) {
    stringBuffer.append(TEXT_1647);
    stringBuffer.append(genClass.getRawImportedInterfaceName());
    stringBuffer.append(TEXT_1648);
    stringBuffer.append(negativeOffsetCorrection);
    stringBuffer.append(TEXT_1649);
    for (GenFeature genFeature : genClass.getGenFeatures()) {
    stringBuffer.append(TEXT_1650);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(TEXT_1651);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(positiveOffsetCorrection);
    stringBuffer.append(TEXT_1652);
    }
    stringBuffer.append(TEXT_1653);
    }
    stringBuffer.append(TEXT_1654);
    }
    if (genModel.isOperationReflection() && isImplementation && (!genClass.getMixinGenOperations().isEmpty() || !genClass.getOverrideGenOperations(genClass.getExtendedGenOperations(), genClass.getImplementedGenOperations()).isEmpty() || genClass.hasOffsetCorrection() && !genClass.getGenOperations().isEmpty())) {
    stringBuffer.append(TEXT_1655);
    if (genModel.useClassOverrideAnnotation()) {
    stringBuffer.append(TEXT_1656);
    }
    stringBuffer.append(TEXT_1657);
    stringBuffer.append(singleWildcard);
    stringBuffer.append(TEXT_1658);
    for (GenClass extendedGenClass : genClass.getExtendedGenClasses()) { List<GenOperation> extendedImplementedGenOperations = extendedGenClass.getImplementedGenOperations(); List<GenOperation> implementedGenOperations = genClass.getImplementedGenOperations();
    if (!genClass.getOverrideGenOperations(extendedImplementedGenOperations, implementedGenOperations).isEmpty()) {
    stringBuffer.append(TEXT_1659);
    stringBuffer.append(extendedGenClass.getRawImportedInterfaceName());
    stringBuffer.append(TEXT_1660);
    for (GenOperation genOperation : extendedImplementedGenOperations) { GenOperation overrideGenOperation = genClass.getOverrideGenOperation(genOperation);
    if (implementedGenOperations.contains(overrideGenOperation)) {
    stringBuffer.append(TEXT_1661);
    stringBuffer.append(extendedGenClass.getQualifiedOperationID(genOperation));
    stringBuffer.append(TEXT_1662);
    stringBuffer.append(genClass.getQualifiedOperationID(overrideGenOperation));
    stringBuffer.append(positiveOperationOffsetCorrection);
    stringBuffer.append(TEXT_1663);
    }
    }
    stringBuffer.append(TEXT_1664);
    }
    }
    for (GenClass mixinGenClass : genClass.getMixinGenClasses()) {
    stringBuffer.append(TEXT_1665);
    stringBuffer.append(mixinGenClass.getRawImportedInterfaceName());
    stringBuffer.append(TEXT_1666);
    for (GenOperation genOperation : mixinGenClass.getGenOperations()) { GenOperation overrideGenOperation = genClass.getOverrideGenOperation(genOperation);
    stringBuffer.append(TEXT_1667);
    stringBuffer.append(mixinGenClass.getQualifiedOperationID(genOperation));
    stringBuffer.append(TEXT_1668);
    stringBuffer.append(genClass.getQualifiedOperationID(overrideGenOperation != null ? overrideGenOperation : genOperation));
    stringBuffer.append(positiveOperationOffsetCorrection);
    stringBuffer.append(TEXT_1669);
    }
    stringBuffer.append(TEXT_1670);
    }
    if (genClass.hasOffsetCorrection() && !genClass.getGenOperations().isEmpty()) {
    stringBuffer.append(TEXT_1671);
    stringBuffer.append(genClass.getRawImportedInterfaceName());
    stringBuffer.append(TEXT_1672);
    stringBuffer.append(negativeOperationOffsetCorrection);
    stringBuffer.append(TEXT_1673);
    for (GenOperation genOperation : genClass.getGenOperations()) {
    stringBuffer.append(TEXT_1674);
    stringBuffer.append(genClass.getQualifiedOperationID(genOperation));
    stringBuffer.append(TEXT_1675);
    stringBuffer.append(genClass.getQualifiedOperationID(genOperation));
    stringBuffer.append(positiveOperationOffsetCorrection);
    stringBuffer.append(TEXT_1676);
    }
    stringBuffer.append(TEXT_1677);
    }
    stringBuffer.append(TEXT_1678);
    }
    if (isImplementation && genModel.isVirtualDelegation()) { String eVirtualValuesField = genClass.getEVirtualValuesField();
    if (eVirtualValuesField != null) {
    stringBuffer.append(TEXT_1679);
    if (genModel.useClassOverrideAnnotation()) {
    stringBuffer.append(TEXT_1680);
    }
    stringBuffer.append(TEXT_1681);
    stringBuffer.append(eVirtualValuesField);
    stringBuffer.append(TEXT_1682);
    if (genModel.useClassOverrideAnnotation()) {
    stringBuffer.append(TEXT_1683);
    }
    stringBuffer.append(TEXT_1684);
    stringBuffer.append(eVirtualValuesField);
    stringBuffer.append(TEXT_1685);
    }
    { List<String> eVirtualIndexBitFields = genClass.getEVirtualIndexBitFields(new ArrayList<String>());
    if (!eVirtualIndexBitFields.isEmpty()) { List<String> allEVirtualIndexBitFields = genClass.getAllEVirtualIndexBitFields(new ArrayList<String>());
    stringBuffer.append(TEXT_1686);
    if (genModel.useClassOverrideAnnotation()) {
    stringBuffer.append(TEXT_1687);
    }
    stringBuffer.append(TEXT_1688);
    for (int i = 0; i < allEVirtualIndexBitFields.size(); i++) {
    stringBuffer.append(TEXT_1689);
    stringBuffer.append(i);
    stringBuffer.append(TEXT_1690);
    stringBuffer.append(allEVirtualIndexBitFields.get(i));
    stringBuffer.append(TEXT_1691);
    }
    stringBuffer.append(TEXT_1692);
    if (genModel.useClassOverrideAnnotation()) {
    stringBuffer.append(TEXT_1693);
    }
    stringBuffer.append(TEXT_1694);
    for (int i = 0; i < allEVirtualIndexBitFields.size(); i++) {
    stringBuffer.append(TEXT_1695);
    stringBuffer.append(i);
    stringBuffer.append(TEXT_1696);
    stringBuffer.append(allEVirtualIndexBitFields.get(i));
    stringBuffer.append(TEXT_1697);
    }
    stringBuffer.append(TEXT_1698);
    }
    }
    }
    if (genModel.isOperationReflection() && isImplementation && !genClass.getImplementedGenOperations().isEmpty()) {
    stringBuffer.append(TEXT_1699);
    if (genModel.useClassOverrideAnnotation()) {
    stringBuffer.append(TEXT_1700);
    }
    if (genModel.useGenerics()) {
    boolean isUnchecked = false; boolean isRaw = false; LOOP: for (GenOperation genOperation : (genModel.isMinimalReflectiveMethods() ? genClass.getImplementedGenOperations() : genClass.getAllGenOperations())) { for (GenParameter genParameter : genOperation.getGenParameters()) { if (genParameter.isUncheckedCast()) { if (genParameter.getTypeGenDataType() == null || !genParameter.getTypeGenDataType().isObjectType()) { isUnchecked = true; } if (genParameter.usesOperationTypeParameters() && !genParameter.getEcoreParameter().getEGenericType().getETypeArguments().isEmpty()) { isRaw = true; break LOOP; }}}}
    if (isUnchecked) {
    stringBuffer.append(TEXT_1701);
    if (!isRaw) {
    stringBuffer.append(TEXT_1702);
    } else {
    stringBuffer.append(TEXT_1703);
    }
    stringBuffer.append(TEXT_1704);
    }
    }
    stringBuffer.append(TEXT_1705);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.util.EList"));
    stringBuffer.append(singleWildcard);
    stringBuffer.append(TEXT_1706);
    stringBuffer.append(genModel.getImportedName(isGWT ? "org.eclipse.emf.common.util.InvocationTargetException" : "java.lang.reflect.InvocationTargetException"));
    stringBuffer.append(TEXT_1707);
    stringBuffer.append(negativeOperationOffsetCorrection);
    stringBuffer.append(TEXT_1708);
    for (GenOperation genOperation : (genModel.isMinimalReflectiveMethods() ? genClass.getImplementedGenOperations() : genClass.getAllGenOperations())) { List<GenParameter> genParameters = genOperation.getGenParameters(); int size = genParameters.size();  boolean hasCheckedException = genOperation.hasCheckedException(); String indent = hasCheckedException ? "\t" : "";
    stringBuffer.append(TEXT_1709);
    stringBuffer.append(genClass.getQualifiedOperationID(genOperation));
    stringBuffer.append(TEXT_1710);
    if (hasCheckedException) {
    stringBuffer.append(TEXT_1711);
    /*}*/}
    if (genOperation.isVoid()) {
    stringBuffer.append(TEXT_1712);
    stringBuffer.append(indent);
    stringBuffer.append(genOperation.getName());
    stringBuffer.append(TEXT_1713);
    for (int i = 0; i < size; i++) { GenParameter genParameter = genParameters.get(i);
    if (!isJDK50 && genParameter.isPrimitiveType()) {
    stringBuffer.append(TEXT_1714);
    }
    if (genParameter.getTypeGenDataType() == null || !genParameter.getTypeGenDataType().isObjectType() || !genParameter.usesOperationTypeParameters() && !genParameter.getRawType().equals(genParameter.getType(genClass))) {
    stringBuffer.append(TEXT_1715);
    stringBuffer.append(genParameter.usesOperationTypeParameters() ? genParameter.getRawImportedType() : genParameter.getObjectType(genClass));
    stringBuffer.append(TEXT_1716);
    }
    stringBuffer.append(TEXT_1717);
    stringBuffer.append(i);
    stringBuffer.append(TEXT_1718);
    if (!isJDK50 && genParameter.isPrimitiveType()) {
    stringBuffer.append(TEXT_1719);
    stringBuffer.append(genParameter.getPrimitiveValueFunction());
    stringBuffer.append(TEXT_1720);
    }
    if (i < (size - 1)) {
    stringBuffer.append(TEXT_1721);
    }
    }
    stringBuffer.append(TEXT_1722);
    stringBuffer.append(indent);
    stringBuffer.append(TEXT_1723);
    } else {
    stringBuffer.append(TEXT_1724);
    stringBuffer.append(indent);
    stringBuffer.append(TEXT_1725);
    if (!isJDK50 && genOperation.isPrimitiveType()) {
    stringBuffer.append(TEXT_1726);
    stringBuffer.append(genOperation.getObjectType(genClass));
    stringBuffer.append(TEXT_1727);
    }
    stringBuffer.append(genOperation.getName());
    stringBuffer.append(TEXT_1728);
    for (int i = 0; i < size; i++) { GenParameter genParameter = genParameters.get(i);
    if (!isJDK50 && genParameter.isPrimitiveType()) {
    stringBuffer.append(TEXT_1729);
    }
    if (genParameter.getTypeGenDataType() == null || !genParameter.getTypeGenDataType().isObjectType() || !genParameter.usesOperationTypeParameters() && !genParameter.getRawType().equals(genParameter.getType(genClass))) {
    stringBuffer.append(TEXT_1730);
    stringBuffer.append(genParameter.usesOperationTypeParameters() ? genParameter.getRawImportedType() : genParameter.getObjectType(genClass));
    stringBuffer.append(TEXT_1731);
    }
    stringBuffer.append(TEXT_1732);
    stringBuffer.append(i);
    stringBuffer.append(TEXT_1733);
    if (!isJDK50 && genParameter.isPrimitiveType()) {
    stringBuffer.append(TEXT_1734);
    stringBuffer.append(genParameter.getPrimitiveValueFunction());
    stringBuffer.append(TEXT_1735);
    }
    if (i < (size - 1)) {
    stringBuffer.append(TEXT_1736);
    }
    }
    stringBuffer.append(TEXT_1737);
    if (!isJDK50 && genOperation.isPrimitiveType()) {
    stringBuffer.append(TEXT_1738);
    }
    stringBuffer.append(TEXT_1739);
    }
    if (hasCheckedException) {/*{*/
    stringBuffer.append(TEXT_1740);
    stringBuffer.append(genModel.getImportedName("java.lang.Throwable"));
    stringBuffer.append(TEXT_1741);
    stringBuffer.append(genModel.getImportedName(isGWT ? "org.eclipse.emf.common.util.InvocationTargetException" : "java.lang.reflect.InvocationTargetException"));
    stringBuffer.append(TEXT_1742);
    }
    }
    stringBuffer.append(TEXT_1743);
    if (genModel.isMinimalReflectiveMethods()) {
    stringBuffer.append(TEXT_1744);
    } else {
    stringBuffer.append(TEXT_1745);
    }
    stringBuffer.append(TEXT_1746);
    }
    if (!genClass.hasImplementedToStringGenOperation() && isImplementation && !genModel.isReflectiveDelegation() && !genModel.isDynamicDelegation() && !genClass.getToStringGenFeatures().isEmpty()) {
    stringBuffer.append(TEXT_1747);
    if (genModel.useClassOverrideAnnotation()) {
    stringBuffer.append(TEXT_1748);
    }
    stringBuffer.append(TEXT_1749);
    { boolean first = true;
    for (GenFeature genFeature : genClass.getToStringGenFeatures()) {
    if (first) { first = false;
    stringBuffer.append(TEXT_1750);
    stringBuffer.append(genFeature.getName());
    stringBuffer.append(TEXT_1751);
    stringBuffer.append(genModel.getNonNLS());
    } else {
    stringBuffer.append(TEXT_1752);
    stringBuffer.append(genFeature.getName());
    stringBuffer.append(TEXT_1753);
    stringBuffer.append(genModel.getNonNLS());
    }
    if (genFeature.isUnsettable() && !genFeature.isListType()) {
    if (genModel.isVirtualDelegation() && !genFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_1754);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(positiveOffsetCorrection);
    stringBuffer.append(TEXT_1755);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(positiveOffsetCorrection);
    stringBuffer.append(TEXT_1756);
    stringBuffer.append(genModel.getNonNLS());
    } else {
    if (genClass.isFlag(genFeature)) {
    if (genFeature.isBooleanType()) {
    stringBuffer.append(TEXT_1757);
    if (genClass.isESetFlag(genFeature)) {
    stringBuffer.append(TEXT_1758);
    stringBuffer.append(genClass.getESetFlagsField(genFeature));
    stringBuffer.append(TEXT_1759);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_1760);
    } else {
    stringBuffer.append(genFeature.getUncapName());
    stringBuffer.append(TEXT_1761);
    }
    stringBuffer.append(TEXT_1762);
    stringBuffer.append(genClass.getFlagsField(genFeature));
    stringBuffer.append(TEXT_1763);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_1764);
    stringBuffer.append(genModel.getNonNLS());
    } else {
    stringBuffer.append(TEXT_1765);
    if (genClass.isESetFlag(genFeature)) {
    stringBuffer.append(TEXT_1766);
    stringBuffer.append(genClass.getESetFlagsField(genFeature));
    stringBuffer.append(TEXT_1767);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_1768);
    } else {
    stringBuffer.append(genFeature.getUncapName());
    stringBuffer.append(TEXT_1769);
    }
    stringBuffer.append(TEXT_1770);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_1771);
    stringBuffer.append(genClass.getFlagsField(genFeature));
    stringBuffer.append(TEXT_1772);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_1773);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_1774);
    stringBuffer.append(genModel.getNonNLS());
    }
    } else {
    stringBuffer.append(TEXT_1775);
    if (genClass.isESetFlag(genFeature)) {
    stringBuffer.append(TEXT_1776);
    stringBuffer.append(genClass.getESetFlagsField(genFeature));
    stringBuffer.append(TEXT_1777);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_1778);
    } else {
    stringBuffer.append(genFeature.getUncapName());
    stringBuffer.append(TEXT_1779);
    }
    stringBuffer.append(TEXT_1780);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_1781);
    stringBuffer.append(genModel.getNonNLS());
    }
    }
    } else {
    if (genModel.isVirtualDelegation() && !genFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_1782);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(positiveOffsetCorrection);
    if (!genFeature.isListType() && !genFeature.isReferenceType()){
    stringBuffer.append(TEXT_1783);
    stringBuffer.append(genFeature.getEDefault());
    }
    stringBuffer.append(TEXT_1784);
    } else {
    if (genClass.isFlag(genFeature)) {
    if (genFeature.isBooleanType()) {
    stringBuffer.append(TEXT_1785);
    stringBuffer.append(genClass.getFlagsField(genFeature));
    stringBuffer.append(TEXT_1786);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_1787);
    } else {
    stringBuffer.append(TEXT_1788);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_1789);
    stringBuffer.append(genClass.getFlagsField(genFeature));
    stringBuffer.append(TEXT_1790);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_1791);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_1792);
    }
    } else {
    stringBuffer.append(TEXT_1793);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_1794);
    }
    }
    }
    }
    }
    stringBuffer.append(TEXT_1795);
    }
    if (isImplementation && genClass.isMapEntry()) { GenFeature keyFeature = genClass.getMapEntryKeyFeature(); GenFeature valueFeature = genClass.getMapEntryValueFeature();
    String objectType = genModel.getImportedName("java.lang.Object");
    String keyType = isJDK50 ? keyFeature.getObjectType(genClass) : objectType;
    String valueType = isJDK50 ? valueFeature.getObjectType(genClass) : objectType;
    String eMapType = genModel.getImportedName("org.eclipse.emf.common.util.EMap") + (isJDK50 ? "<" + keyType + ", " + valueType + ">" : "");
    stringBuffer.append(TEXT_1796);
    if (isGWT) {
    stringBuffer.append(TEXT_1797);
    stringBuffer.append(genModel.getImportedName("com.google.gwt.user.client.rpc.GwtTransient"));
    }
    stringBuffer.append(TEXT_1798);
    stringBuffer.append(objectType);
    stringBuffer.append(TEXT_1799);
    stringBuffer.append(keyType);
    stringBuffer.append(TEXT_1800);
    if (!isJDK50 && keyFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_1801);
    stringBuffer.append(keyFeature.getObjectType(genClass));
    stringBuffer.append(TEXT_1802);
    } else {
    stringBuffer.append(TEXT_1803);
    }
    stringBuffer.append(TEXT_1804);
    stringBuffer.append(keyType);
    stringBuffer.append(TEXT_1805);
    if (keyFeature.isListType()) {
    stringBuffer.append(TEXT_1806);
    if (!genModel.useGenerics()) {
    stringBuffer.append(TEXT_1807);
    stringBuffer.append(genModel.getImportedName("java.util.Collection"));
    stringBuffer.append(TEXT_1808);
    }
    stringBuffer.append(TEXT_1809);
    } else if (isJDK50) {
    stringBuffer.append(TEXT_1810);
    } else if (keyFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_1811);
    stringBuffer.append(keyFeature.getObjectType(genClass));
    stringBuffer.append(TEXT_1812);
    stringBuffer.append(keyFeature.getPrimitiveValueFunction());
    stringBuffer.append(TEXT_1813);
    } else {
    stringBuffer.append(TEXT_1814);
    stringBuffer.append(keyFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_1815);
    }
    stringBuffer.append(TEXT_1816);
    stringBuffer.append(valueType);
    stringBuffer.append(TEXT_1817);
    if (!isJDK50 && valueFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_1818);
    stringBuffer.append(valueFeature.getObjectType(genClass));
    stringBuffer.append(TEXT_1819);
    } else {
    stringBuffer.append(TEXT_1820);
    }
    stringBuffer.append(TEXT_1821);
    stringBuffer.append(valueType);
    stringBuffer.append(TEXT_1822);
    stringBuffer.append(valueType);
    stringBuffer.append(TEXT_1823);
    stringBuffer.append(valueType);
    stringBuffer.append(TEXT_1824);
    if (valueFeature.isListType()) {
    stringBuffer.append(TEXT_1825);
    if (!genModel.useGenerics()) {
    stringBuffer.append(TEXT_1826);
    stringBuffer.append(genModel.getImportedName("java.util.Collection"));
    stringBuffer.append(TEXT_1827);
    }
    stringBuffer.append(TEXT_1828);
    } else if (isJDK50) {
    stringBuffer.append(TEXT_1829);
    } else if (valueFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_1830);
    stringBuffer.append(valueFeature.getObjectType(genClass));
    stringBuffer.append(TEXT_1831);
    stringBuffer.append(valueFeature.getPrimitiveValueFunction());
    stringBuffer.append(TEXT_1832);
    } else {
    stringBuffer.append(TEXT_1833);
    stringBuffer.append(valueFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_1834);
    }
    stringBuffer.append(TEXT_1835);
    if (genModel.useGenerics()) {
    stringBuffer.append(TEXT_1836);
    }
    stringBuffer.append(TEXT_1837);
    stringBuffer.append(eMapType);
    stringBuffer.append(TEXT_1838);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.EObject"));
    stringBuffer.append(TEXT_1839);
    stringBuffer.append(eMapType);
    stringBuffer.append(TEXT_1840);
    }
    stringBuffer.append(TEXT_1841);
    stringBuffer.append(isInterface ? " " + genClass.getInterfaceName() : genClass.getClassName());
    // TODO fix the space above
    genModel.emitSortedImports();
    stringBuffer.append(TEXT_1842);
    return stringBuffer.toString();
  }
}
