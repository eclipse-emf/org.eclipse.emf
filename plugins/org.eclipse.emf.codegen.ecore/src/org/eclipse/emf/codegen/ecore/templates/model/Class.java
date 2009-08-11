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
  protected final String TEXT_62 = NL + "\t/**" + NL + "\t * The cached value of the '{@link #";
  protected final String TEXT_63 = "() <em>";
  protected final String TEXT_64 = "</em>}' ";
  protected final String TEXT_65 = "." + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @see #";
  protected final String TEXT_66 = "()" + NL + "\t * @generated" + NL + "\t * @ordered" + NL + "\t */" + NL + "\tprotected ";
  protected final String TEXT_67 = " ";
  protected final String TEXT_68 = ";" + NL;
  protected final String TEXT_69 = NL + "\t/**" + NL + "\t * The empty value for the '{@link #";
  protected final String TEXT_70 = "() <em>";
  protected final String TEXT_71 = "</em>}' array accessor." + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @see #";
  protected final String TEXT_72 = "()" + NL + "\t * @generated" + NL + "\t * @ordered" + NL + "\t */";
  protected final String TEXT_73 = NL + "\t@SuppressWarnings(\"unchecked\")";
  protected final String TEXT_74 = NL + "\tprotected static final ";
  protected final String TEXT_75 = "[] ";
  protected final String TEXT_76 = "_EEMPTY_ARRAY = new ";
  protected final String TEXT_77 = " [0]";
  protected final String TEXT_78 = ";" + NL;
  protected final String TEXT_79 = NL + "\t/**" + NL + "\t * The default value of the '{@link #";
  protected final String TEXT_80 = "() <em>";
  protected final String TEXT_81 = "</em>}' ";
  protected final String TEXT_82 = "." + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @see #";
  protected final String TEXT_83 = "()" + NL + "\t * @generated" + NL + "\t * @ordered" + NL + "\t */";
  protected final String TEXT_84 = NL + "\t@SuppressWarnings(\"unchecked\")";
  protected final String TEXT_85 = NL + "\tprotected static final ";
  protected final String TEXT_86 = " ";
  protected final String TEXT_87 = "; // TODO The default value literal \"";
  protected final String TEXT_88 = "\" is not valid.";
  protected final String TEXT_89 = " = ";
  protected final String TEXT_90 = ";";
  protected final String TEXT_91 = NL;
  protected final String TEXT_92 = NL + "\t/**" + NL + "\t * An additional set of bit flags representing the values of boolean attributes and whether unsettable features have been set." + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t * @ordered" + NL + "\t */" + NL + "\tprotected int ";
  protected final String TEXT_93 = " = 0;" + NL;
  protected final String TEXT_94 = NL + "\t/**" + NL + "\t * The offset of the flags representing the value of the '{@link #";
  protected final String TEXT_95 = "() <em>";
  protected final String TEXT_96 = "</em>}' ";
  protected final String TEXT_97 = "." + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t * @ordered" + NL + "\t */" + NL + "\tprotected static final int ";
  protected final String TEXT_98 = "_EFLAG_OFFSET = ";
  protected final String TEXT_99 = ";" + NL + "" + NL + "\t/**" + NL + "\t * The flags representing the default value of the '{@link #";
  protected final String TEXT_100 = "() <em>";
  protected final String TEXT_101 = "</em>}' ";
  protected final String TEXT_102 = "." + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t * @ordered" + NL + "\t */" + NL + "\tprotected static final int ";
  protected final String TEXT_103 = "_EFLAG_DEFAULT = ";
  protected final String TEXT_104 = ".ordinal()";
  protected final String TEXT_105 = ".VALUES.indexOf(";
  protected final String TEXT_106 = ")";
  protected final String TEXT_107 = " << ";
  protected final String TEXT_108 = "_EFLAG_OFFSET;" + NL + "" + NL + "\t/**" + NL + "\t * The array of enumeration values for '{@link ";
  protected final String TEXT_109 = " ";
  protected final String TEXT_110 = "}'" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t * @ordered" + NL + "\t */" + NL + "\tprivate static final ";
  protected final String TEXT_111 = "[] ";
  protected final String TEXT_112 = "_EFLAG_VALUES = ";
  protected final String TEXT_113 = ".values()";
  protected final String TEXT_114 = "(";
  protected final String TEXT_115 = "[])";
  protected final String TEXT_116 = ".VALUES.toArray(new ";
  protected final String TEXT_117 = "[";
  protected final String TEXT_118 = ".VALUES.size()])";
  protected final String TEXT_119 = ";" + NL;
  protected final String TEXT_120 = NL + "\t/**" + NL + "\t * The flag";
  protected final String TEXT_121 = " representing the value of the '{@link #";
  protected final String TEXT_122 = "() <em>";
  protected final String TEXT_123 = "</em>}' ";
  protected final String TEXT_124 = "." + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @see #";
  protected final String TEXT_125 = "()" + NL + "\t * @generated" + NL + "\t * @ordered" + NL + "\t */" + NL + "\tprotected static final int ";
  protected final String TEXT_126 = "_EFLAG = ";
  protected final String TEXT_127 = " << ";
  protected final String TEXT_128 = "_EFLAG_OFFSET";
  protected final String TEXT_129 = ";" + NL;
  protected final String TEXT_130 = NL + "\t/**" + NL + "\t * The cached value of the '{@link #";
  protected final String TEXT_131 = "() <em>";
  protected final String TEXT_132 = "</em>}' ";
  protected final String TEXT_133 = "." + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @see #";
  protected final String TEXT_134 = "()" + NL + "\t * @generated" + NL + "\t * @ordered" + NL + "\t */" + NL + "\tprotected ";
  protected final String TEXT_135 = " ";
  protected final String TEXT_136 = " = ";
  protected final String TEXT_137 = ";" + NL;
  protected final String TEXT_138 = NL + "\t/**" + NL + "\t * An additional set of bit flags representing the values of boolean attributes and whether unsettable features have been set." + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t * @ordered" + NL + "\t */" + NL + "\tprotected int ";
  protected final String TEXT_139 = " = 0;" + NL;
  protected final String TEXT_140 = NL + "\t/**" + NL + "\t * The flag representing whether the ";
  protected final String TEXT_141 = " ";
  protected final String TEXT_142 = " has been set." + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t * @ordered" + NL + "\t */" + NL + "\tprotected static final int ";
  protected final String TEXT_143 = "_ESETFLAG = 1 << ";
  protected final String TEXT_144 = ";" + NL;
  protected final String TEXT_145 = NL + "\t/**" + NL + "\t * This is true if the ";
  protected final String TEXT_146 = " ";
  protected final String TEXT_147 = " has been set." + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t * @ordered" + NL + "\t */" + NL + "\tprotected boolean ";
  protected final String TEXT_148 = "ESet;" + NL;
  protected final String TEXT_149 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */" + NL + "\tprivate static final int ";
  protected final String TEXT_150 = " = ";
  protected final String TEXT_151 = ".getFeatureID(";
  protected final String TEXT_152 = ") - ";
  protected final String TEXT_153 = ";" + NL;
  protected final String TEXT_154 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */" + NL + "\tprivate static final int ";
  protected final String TEXT_155 = " = ";
  protected final String TEXT_156 = ".getFeatureID(";
  protected final String TEXT_157 = ") - ";
  protected final String TEXT_158 = ";" + NL;
  protected final String TEXT_159 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */" + NL + "\t";
  protected final String TEXT_160 = "public";
  protected final String TEXT_161 = "protected";
  protected final String TEXT_162 = " ";
  protected final String TEXT_163 = "()" + NL + "\t{" + NL + "\t\tsuper();";
  protected final String TEXT_164 = NL + "\t\t";
  protected final String TEXT_165 = " |= ";
  protected final String TEXT_166 = "_EFLAG";
  protected final String TEXT_167 = "_DEFAULT";
  protected final String TEXT_168 = ";";
  protected final String TEXT_169 = NL + "\t}" + NL + "" + NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */";
  protected final String TEXT_170 = NL + "\t@Override";
  protected final String TEXT_171 = NL + "\tprotected ";
  protected final String TEXT_172 = " eStaticClass()" + NL + "\t{" + NL + "\t\treturn ";
  protected final String TEXT_173 = ";" + NL + "\t}" + NL;
  protected final String TEXT_174 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */";
  protected final String TEXT_175 = NL + "\t@Override";
  protected final String TEXT_176 = NL + "\tprotected int eStaticFeatureCount()" + NL + "\t{" + NL + "\t\treturn ";
  protected final String TEXT_177 = ";" + NL + "\t}" + NL;
  protected final String TEXT_178 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */";
  protected final String TEXT_179 = NL + "\t";
  protected final String TEXT_180 = "[] ";
  protected final String TEXT_181 = "();" + NL;
  protected final String TEXT_182 = NL + "\tpublic ";
  protected final String TEXT_183 = "[] ";
  protected final String TEXT_184 = "()" + NL + "\t{";
  protected final String TEXT_185 = NL + "\t\t";
  protected final String TEXT_186 = " list = (";
  protected final String TEXT_187 = ")";
  protected final String TEXT_188 = "();" + NL + "\t\tif (list.isEmpty()) return ";
  protected final String TEXT_189 = "(";
  protected final String TEXT_190 = "[])";
  protected final String TEXT_191 = "_EEMPTY_ARRAY;";
  protected final String TEXT_192 = NL + "\t\tif (";
  protected final String TEXT_193 = " == null || ";
  protected final String TEXT_194 = ".isEmpty()) return ";
  protected final String TEXT_195 = "(";
  protected final String TEXT_196 = "[])";
  protected final String TEXT_197 = "_EEMPTY_ARRAY;" + NL + "\t\t";
  protected final String TEXT_198 = " list = (";
  protected final String TEXT_199 = ")";
  protected final String TEXT_200 = ";";
  protected final String TEXT_201 = NL + "\t\tlist.shrink();" + NL + "\t\treturn (";
  protected final String TEXT_202 = "[])list.data();" + NL + "\t}" + NL;
  protected final String TEXT_203 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */";
  protected final String TEXT_204 = NL + "\t";
  protected final String TEXT_205 = " get";
  protected final String TEXT_206 = "(int index);" + NL;
  protected final String TEXT_207 = NL + "\tpublic ";
  protected final String TEXT_208 = " get";
  protected final String TEXT_209 = "(int index)" + NL + "\t{" + NL + "\t\treturn ";
  protected final String TEXT_210 = "(";
  protected final String TEXT_211 = ")";
  protected final String TEXT_212 = "().get(index);" + NL + "\t}" + NL;
  protected final String TEXT_213 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */";
  protected final String TEXT_214 = NL + "\tint get";
  protected final String TEXT_215 = "Length();" + NL;
  protected final String TEXT_216 = NL + "\tpublic int get";
  protected final String TEXT_217 = "Length()" + NL + "\t{";
  protected final String TEXT_218 = NL + "\t\treturn ";
  protected final String TEXT_219 = "().size();";
  protected final String TEXT_220 = NL + "\t\treturn ";
  protected final String TEXT_221 = " == null ? 0 : ";
  protected final String TEXT_222 = ".size();";
  protected final String TEXT_223 = NL + "\t}" + NL;
  protected final String TEXT_224 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */";
  protected final String TEXT_225 = NL + "\tvoid set";
  protected final String TEXT_226 = "(";
  protected final String TEXT_227 = "[] new";
  protected final String TEXT_228 = ");" + NL;
  protected final String TEXT_229 = NL + "\tpublic void set";
  protected final String TEXT_230 = "(";
  protected final String TEXT_231 = "[] new";
  protected final String TEXT_232 = ")" + NL + "\t{" + NL + "\t\t((";
  protected final String TEXT_233 = ")";
  protected final String TEXT_234 = "()).setData(new";
  protected final String TEXT_235 = ".length, new";
  protected final String TEXT_236 = ");" + NL + "\t}" + NL;
  protected final String TEXT_237 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */";
  protected final String TEXT_238 = NL + "\tvoid set";
  protected final String TEXT_239 = "(int index, ";
  protected final String TEXT_240 = " element);" + NL;
  protected final String TEXT_241 = NL + "\tpublic void set";
  protected final String TEXT_242 = "(int index, ";
  protected final String TEXT_243 = " element)" + NL + "\t{" + NL + "\t\t";
  protected final String TEXT_244 = "().set(index, element);" + NL + "\t}" + NL;
  protected final String TEXT_245 = NL + "\t/**" + NL + "\t * Returns the value of the '<em><b>";
  protected final String TEXT_246 = "</b></em>' ";
  protected final String TEXT_247 = ".";
  protected final String TEXT_248 = NL + "\t * The key is of type ";
  protected final String TEXT_249 = "list of {@link ";
  protected final String TEXT_250 = "}";
  protected final String TEXT_251 = "{@link ";
  protected final String TEXT_252 = "}";
  protected final String TEXT_253 = "," + NL + "\t * and the value is of type ";
  protected final String TEXT_254 = "list of {@link ";
  protected final String TEXT_255 = "}";
  protected final String TEXT_256 = "{@link ";
  protected final String TEXT_257 = "}";
  protected final String TEXT_258 = ",";
  protected final String TEXT_259 = NL + "\t * The list contents are of type {@link ";
  protected final String TEXT_260 = "}";
  protected final String TEXT_261 = ".";
  protected final String TEXT_262 = NL + "\t * The default value is <code>";
  protected final String TEXT_263 = "</code>.";
  protected final String TEXT_264 = NL + "\t * The literals are from the enumeration {@link ";
  protected final String TEXT_265 = "}.";
  protected final String TEXT_266 = NL + "\t * It is bidirectional and its opposite is '{@link ";
  protected final String TEXT_267 = "#";
  protected final String TEXT_268 = " <em>";
  protected final String TEXT_269 = "</em>}'.";
  protected final String TEXT_270 = NL + "\t * <!-- begin-user-doc -->";
  protected final String TEXT_271 = NL + "\t * <p>" + NL + "\t * If the meaning of the '<em>";
  protected final String TEXT_272 = "</em>' ";
  protected final String TEXT_273 = " isn't clear," + NL + "\t * there really should be more of a description here..." + NL + "\t * </p>";
  protected final String TEXT_274 = NL + "\t * <!-- end-user-doc -->";
  protected final String TEXT_275 = NL + "\t * <!-- begin-model-doc -->" + NL + "\t * ";
  protected final String TEXT_276 = NL + "\t * <!-- end-model-doc -->";
  protected final String TEXT_277 = NL + "\t * @return the value of the '<em>";
  protected final String TEXT_278 = "</em>' ";
  protected final String TEXT_279 = ".";
  protected final String TEXT_280 = NL + "\t * @see ";
  protected final String TEXT_281 = NL + "\t * @see #isSet";
  protected final String TEXT_282 = "()";
  protected final String TEXT_283 = NL + "\t * @see #unset";
  protected final String TEXT_284 = "()";
  protected final String TEXT_285 = NL + "\t * @see #set";
  protected final String TEXT_286 = "(";
  protected final String TEXT_287 = ")";
  protected final String TEXT_288 = NL + "\t * @see ";
  protected final String TEXT_289 = "#get";
  protected final String TEXT_290 = "()";
  protected final String TEXT_291 = NL + "\t * @see ";
  protected final String TEXT_292 = "#";
  protected final String TEXT_293 = NL + "\t * @model ";
  protected final String TEXT_294 = NL + "\t *        ";
  protected final String TEXT_295 = NL + "\t * @model";
  protected final String TEXT_296 = NL + "\t * @generated" + NL + "\t */";
  protected final String TEXT_297 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */";
  protected final String TEXT_298 = NL + "\t";
  protected final String TEXT_299 = " ";
  protected final String TEXT_300 = "();" + NL;
  protected final String TEXT_301 = NL + "\t@SuppressWarnings(\"unchecked\")";
  protected final String TEXT_302 = NL + "\tpublic ";
  protected final String TEXT_303 = " ";
  protected final String TEXT_304 = "_";
  protected final String TEXT_305 = "()" + NL + "\t{";
  protected final String TEXT_306 = NL + "\t\treturn ";
  protected final String TEXT_307 = "(";
  protected final String TEXT_308 = "(";
  protected final String TEXT_309 = ")eDynamicGet(";
  protected final String TEXT_310 = ", ";
  protected final String TEXT_311 = ", true, ";
  protected final String TEXT_312 = ")";
  protected final String TEXT_313 = ").";
  protected final String TEXT_314 = "()";
  protected final String TEXT_315 = ";";
  protected final String TEXT_316 = NL + "\t\treturn ";
  protected final String TEXT_317 = "(";
  protected final String TEXT_318 = "(";
  protected final String TEXT_319 = ")eGet(";
  protected final String TEXT_320 = ", true)";
  protected final String TEXT_321 = ").";
  protected final String TEXT_322 = "()";
  protected final String TEXT_323 = ";";
  protected final String TEXT_324 = NL + "\t\t";
  protected final String TEXT_325 = " ";
  protected final String TEXT_326 = " = (";
  protected final String TEXT_327 = ")eVirtualGet(";
  protected final String TEXT_328 = ");";
  protected final String TEXT_329 = NL + "\t\tif (";
  protected final String TEXT_330 = " == null)" + NL + "\t\t{";
  protected final String TEXT_331 = NL + "\t\t\teVirtualSet(";
  protected final String TEXT_332 = ", ";
  protected final String TEXT_333 = " = new ";
  protected final String TEXT_334 = ");";
  protected final String TEXT_335 = NL + "\t\t\t";
  protected final String TEXT_336 = " = new ";
  protected final String TEXT_337 = ";";
  protected final String TEXT_338 = NL + "\t\t}" + NL + "\t\treturn ";
  protected final String TEXT_339 = ";";
  protected final String TEXT_340 = NL + "\t\tif (eContainerFeatureID() != ";
  protected final String TEXT_341 = ") return null;" + NL + "\t\treturn (";
  protected final String TEXT_342 = ")eContainer();";
  protected final String TEXT_343 = NL + "\t\t";
  protected final String TEXT_344 = " ";
  protected final String TEXT_345 = " = (";
  protected final String TEXT_346 = ")eVirtualGet(";
  protected final String TEXT_347 = ", ";
  protected final String TEXT_348 = ");";
  protected final String TEXT_349 = NL + "\t\tif (";
  protected final String TEXT_350 = " != null && ";
  protected final String TEXT_351 = ".eIsProxy())" + NL + "\t\t{" + NL + "\t\t\t";
  protected final String TEXT_352 = " old";
  protected final String TEXT_353 = " = (";
  protected final String TEXT_354 = ")";
  protected final String TEXT_355 = ";" + NL + "\t\t\t";
  protected final String TEXT_356 = " = ";
  protected final String TEXT_357 = "eResolveProxy(old";
  protected final String TEXT_358 = ");" + NL + "\t\t\tif (";
  protected final String TEXT_359 = " != old";
  protected final String TEXT_360 = ")" + NL + "\t\t\t{";
  protected final String TEXT_361 = NL + "\t\t\t\t";
  protected final String TEXT_362 = " new";
  protected final String TEXT_363 = " = (";
  protected final String TEXT_364 = ")";
  protected final String TEXT_365 = ";";
  protected final String TEXT_366 = NL + "\t\t\t\t";
  protected final String TEXT_367 = " msgs = old";
  protected final String TEXT_368 = ".eInverseRemove(this, EOPPOSITE_FEATURE_BASE - ";
  protected final String TEXT_369 = ", null, null);";
  protected final String TEXT_370 = NL + "\t\t\t\t";
  protected final String TEXT_371 = " msgs =  old";
  protected final String TEXT_372 = ".eInverseRemove(this, ";
  protected final String TEXT_373 = ", ";
  protected final String TEXT_374 = ".class, null);";
  protected final String TEXT_375 = NL + "\t\t\t\tif (new";
  protected final String TEXT_376 = ".eInternalContainer() == null)" + NL + "\t\t\t\t{";
  protected final String TEXT_377 = NL + "\t\t\t\t\tmsgs = new";
  protected final String TEXT_378 = ".eInverseAdd(this, EOPPOSITE_FEATURE_BASE - ";
  protected final String TEXT_379 = ", null, msgs);";
  protected final String TEXT_380 = NL + "\t\t\t\t\tmsgs =  new";
  protected final String TEXT_381 = ".eInverseAdd(this, ";
  protected final String TEXT_382 = ", ";
  protected final String TEXT_383 = ".class, msgs);";
  protected final String TEXT_384 = NL + "\t\t\t\t}" + NL + "\t\t\t\tif (msgs != null) msgs.dispatch();";
  protected final String TEXT_385 = NL + "\t\t\t\teVirtualSet(";
  protected final String TEXT_386 = ", ";
  protected final String TEXT_387 = ");";
  protected final String TEXT_388 = NL + "\t\t\t\tif (eNotificationRequired())" + NL + "\t\t\t\t\teNotify(new ";
  protected final String TEXT_389 = "(this, ";
  protected final String TEXT_390 = ".RESOLVE, ";
  protected final String TEXT_391 = ", old";
  protected final String TEXT_392 = ", ";
  protected final String TEXT_393 = "));";
  protected final String TEXT_394 = NL + "\t\t\t}" + NL + "\t\t}";
  protected final String TEXT_395 = NL + "\t\treturn (";
  protected final String TEXT_396 = ")eVirtualGet(";
  protected final String TEXT_397 = ", ";
  protected final String TEXT_398 = ");";
  protected final String TEXT_399 = NL + "\t\treturn (";
  protected final String TEXT_400 = " & ";
  protected final String TEXT_401 = "_EFLAG) != 0;";
  protected final String TEXT_402 = NL + "\t\treturn ";
  protected final String TEXT_403 = "_EFLAG_VALUES[(";
  protected final String TEXT_404 = " & ";
  protected final String TEXT_405 = "_EFLAG) >>> ";
  protected final String TEXT_406 = "_EFLAG_OFFSET];";
  protected final String TEXT_407 = NL + "\t\treturn ";
  protected final String TEXT_408 = ";";
  protected final String TEXT_409 = NL + "\t\t";
  protected final String TEXT_410 = " ";
  protected final String TEXT_411 = " = basicGet";
  protected final String TEXT_412 = "();" + NL + "\t\treturn ";
  protected final String TEXT_413 = " != null && ";
  protected final String TEXT_414 = ".eIsProxy() ? ";
  protected final String TEXT_415 = "eResolveProxy((";
  protected final String TEXT_416 = ")";
  protected final String TEXT_417 = ") : ";
  protected final String TEXT_418 = ";";
  protected final String TEXT_419 = NL + "\t\treturn new ";
  protected final String TEXT_420 = "((";
  protected final String TEXT_421 = ".Internal)((";
  protected final String TEXT_422 = ".Internal.Wrapper)get";
  protected final String TEXT_423 = "()).featureMap().";
  protected final String TEXT_424 = "list(";
  protected final String TEXT_425 = "));";
  protected final String TEXT_426 = NL + "\t\treturn (";
  protected final String TEXT_427 = ")get";
  protected final String TEXT_428 = "().";
  protected final String TEXT_429 = "list(";
  protected final String TEXT_430 = ");";
  protected final String TEXT_431 = NL + "\t\treturn ((";
  protected final String TEXT_432 = ".Internal.Wrapper)get";
  protected final String TEXT_433 = "()).featureMap().list(";
  protected final String TEXT_434 = ");";
  protected final String TEXT_435 = NL + "\t\treturn get";
  protected final String TEXT_436 = "().list(";
  protected final String TEXT_437 = ");";
  protected final String TEXT_438 = NL + "\t\treturn ";
  protected final String TEXT_439 = "(";
  protected final String TEXT_440 = "(";
  protected final String TEXT_441 = ")";
  protected final String TEXT_442 = "((";
  protected final String TEXT_443 = ".Internal.Wrapper)get";
  protected final String TEXT_444 = "()).featureMap().get(";
  protected final String TEXT_445 = ", true)";
  protected final String TEXT_446 = ").";
  protected final String TEXT_447 = "()";
  protected final String TEXT_448 = ";";
  protected final String TEXT_449 = NL + "\t\treturn ";
  protected final String TEXT_450 = "(";
  protected final String TEXT_451 = "(";
  protected final String TEXT_452 = ")";
  protected final String TEXT_453 = "get";
  protected final String TEXT_454 = "().get(";
  protected final String TEXT_455 = ", true)";
  protected final String TEXT_456 = ").";
  protected final String TEXT_457 = "()";
  protected final String TEXT_458 = ";";
  protected final String TEXT_459 = NL + "\t\t";
  protected final String TEXT_460 = NL + "\t\t// TODO: implement this method to return the '";
  protected final String TEXT_461 = "' ";
  protected final String TEXT_462 = NL + "\t\t// Ensure that you remove @generated or mark it @generated NOT";
  protected final String TEXT_463 = NL + "\t\t// The list is expected to implement org.eclipse.emf.ecore.util.InternalEList and org.eclipse.emf.ecore.EStructuralFeature.Setting" + NL + "\t\t// so it's likely that an appropriate subclass of org.eclipse.emf.ecore.util.";
  protected final String TEXT_464 = "EcoreEMap";
  protected final String TEXT_465 = "BasicFeatureMap";
  protected final String TEXT_466 = "EcoreEList";
  protected final String TEXT_467 = " should be used.";
  protected final String TEXT_468 = NL + "\t\tthrow new UnsupportedOperationException();";
  protected final String TEXT_469 = NL + "\t}" + NL;
  protected final String TEXT_470 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */";
  protected final String TEXT_471 = NL + "\tpublic ";
  protected final String TEXT_472 = " basicGet";
  protected final String TEXT_473 = "()" + NL + "\t{";
  protected final String TEXT_474 = NL + "\t\treturn (";
  protected final String TEXT_475 = ")eDynamicGet(";
  protected final String TEXT_476 = ", ";
  protected final String TEXT_477 = ", false, ";
  protected final String TEXT_478 = ");";
  protected final String TEXT_479 = NL + "\t\tif (eContainerFeatureID() != ";
  protected final String TEXT_480 = ") return null;" + NL + "\t\treturn (";
  protected final String TEXT_481 = ")eInternalContainer();";
  protected final String TEXT_482 = NL + "\t\treturn (";
  protected final String TEXT_483 = ")eVirtualGet(";
  protected final String TEXT_484 = ");";
  protected final String TEXT_485 = NL + "\t\treturn ";
  protected final String TEXT_486 = ";";
  protected final String TEXT_487 = NL + "\t\treturn (";
  protected final String TEXT_488 = ")((";
  protected final String TEXT_489 = ".Internal.Wrapper)get";
  protected final String TEXT_490 = "()).featureMap().get(";
  protected final String TEXT_491 = ", false);";
  protected final String TEXT_492 = NL + "\t\treturn (";
  protected final String TEXT_493 = ")get";
  protected final String TEXT_494 = "().get(";
  protected final String TEXT_495 = ", false);";
  protected final String TEXT_496 = NL + "\t\t// TODO: implement this method to return the '";
  protected final String TEXT_497 = "' ";
  protected final String TEXT_498 = NL + "\t\t// -> do not perform proxy resolution" + NL + "\t\t// Ensure that you remove @generated or mark it @generated NOT" + NL + "\t\tthrow new UnsupportedOperationException();";
  protected final String TEXT_499 = NL + "\t}" + NL;
  protected final String TEXT_500 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */";
  protected final String TEXT_501 = NL + "\tpublic ";
  protected final String TEXT_502 = " basicSet";
  protected final String TEXT_503 = "(";
  protected final String TEXT_504 = " new";
  protected final String TEXT_505 = ", ";
  protected final String TEXT_506 = " msgs)" + NL + "\t{";
  protected final String TEXT_507 = NL + "\t\tmsgs = eBasicSetContainer((";
  protected final String TEXT_508 = ")new";
  protected final String TEXT_509 = ", ";
  protected final String TEXT_510 = ", msgs);";
  protected final String TEXT_511 = NL + "\t\treturn msgs;";
  protected final String TEXT_512 = NL + "\t\tmsgs = eDynamicInverseAdd((";
  protected final String TEXT_513 = ")new";
  protected final String TEXT_514 = ", ";
  protected final String TEXT_515 = ", msgs);";
  protected final String TEXT_516 = NL + "\t\treturn msgs;";
  protected final String TEXT_517 = NL + "\t\tObject old";
  protected final String TEXT_518 = " = eVirtualSet(";
  protected final String TEXT_519 = ", new";
  protected final String TEXT_520 = ");";
  protected final String TEXT_521 = NL + "\t\t";
  protected final String TEXT_522 = " old";
  protected final String TEXT_523 = " = ";
  protected final String TEXT_524 = ";" + NL + "\t\t";
  protected final String TEXT_525 = " = new";
  protected final String TEXT_526 = ";";
  protected final String TEXT_527 = NL + "\t\tboolean isSetChange = old";
  protected final String TEXT_528 = " == EVIRTUAL_NO_VALUE;";
  protected final String TEXT_529 = NL + "\t\tboolean old";
  protected final String TEXT_530 = "ESet = (";
  protected final String TEXT_531 = " & ";
  protected final String TEXT_532 = "_ESETFLAG) != 0;";
  protected final String TEXT_533 = NL + "\t\t";
  protected final String TEXT_534 = " |= ";
  protected final String TEXT_535 = "_ESETFLAG;";
  protected final String TEXT_536 = NL + "\t\tboolean old";
  protected final String TEXT_537 = "ESet = ";
  protected final String TEXT_538 = "ESet;";
  protected final String TEXT_539 = NL + "\t\t";
  protected final String TEXT_540 = "ESet = true;";
  protected final String TEXT_541 = NL + "\t\tif (eNotificationRequired())" + NL + "\t\t{";
  protected final String TEXT_542 = NL + "\t\t\t";
  protected final String TEXT_543 = " notification = new ";
  protected final String TEXT_544 = "(this, ";
  protected final String TEXT_545 = ".SET, ";
  protected final String TEXT_546 = ", ";
  protected final String TEXT_547 = "isSetChange ? null : old";
  protected final String TEXT_548 = "old";
  protected final String TEXT_549 = ", new";
  protected final String TEXT_550 = ", ";
  protected final String TEXT_551 = "isSetChange";
  protected final String TEXT_552 = "!old";
  protected final String TEXT_553 = "ESet";
  protected final String TEXT_554 = ");";
  protected final String TEXT_555 = NL + "\t\t\t";
  protected final String TEXT_556 = " notification = new ";
  protected final String TEXT_557 = "(this, ";
  protected final String TEXT_558 = ".SET, ";
  protected final String TEXT_559 = ", ";
  protected final String TEXT_560 = "old";
  protected final String TEXT_561 = " == EVIRTUAL_NO_VALUE ? null : old";
  protected final String TEXT_562 = "old";
  protected final String TEXT_563 = ", new";
  protected final String TEXT_564 = ");";
  protected final String TEXT_565 = NL + "\t\t\tif (msgs == null) msgs = notification; else msgs.add(notification);" + NL + "\t\t}";
  protected final String TEXT_566 = NL + "\t\treturn msgs;";
  protected final String TEXT_567 = NL + "\t\treturn ((";
  protected final String TEXT_568 = ".Internal)((";
  protected final String TEXT_569 = ".Internal.Wrapper)get";
  protected final String TEXT_570 = "()).featureMap()).basicAdd(";
  protected final String TEXT_571 = ", new";
  protected final String TEXT_572 = ", msgs);";
  protected final String TEXT_573 = NL + "\t\treturn ((";
  protected final String TEXT_574 = ".Internal)get";
  protected final String TEXT_575 = "()).basicAdd(";
  protected final String TEXT_576 = ", new";
  protected final String TEXT_577 = ", msgs);";
  protected final String TEXT_578 = NL + "\t\t// TODO: implement this method to set the contained '";
  protected final String TEXT_579 = "' ";
  protected final String TEXT_580 = NL + "\t\t// -> this method is automatically invoked to keep the containment relationship in synch" + NL + "\t\t// -> do not modify other features" + NL + "\t\t// -> return msgs, after adding any generated Notification to it (if it is null, a NotificationChain object must be created first)" + NL + "\t\t// Ensure that you remove @generated or mark it @generated NOT" + NL + "\t\tthrow new UnsupportedOperationException();";
  protected final String TEXT_581 = NL + "\t}" + NL;
  protected final String TEXT_582 = NL + "\t/**" + NL + "\t * Sets the value of the '{@link ";
  protected final String TEXT_583 = "#";
  protected final String TEXT_584 = " <em>";
  protected final String TEXT_585 = "</em>}' ";
  protected final String TEXT_586 = ".";
  protected final String TEXT_587 = NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @param value the new value of the '<em>";
  protected final String TEXT_588 = "</em>' ";
  protected final String TEXT_589 = ".";
  protected final String TEXT_590 = NL + "\t * @see ";
  protected final String TEXT_591 = NL + "\t * @see #isSet";
  protected final String TEXT_592 = "()";
  protected final String TEXT_593 = NL + "\t * @see #unset";
  protected final String TEXT_594 = "()";
  protected final String TEXT_595 = NL + "\t * @see #";
  protected final String TEXT_596 = "()" + NL + "\t * @generated" + NL + "\t */";
  protected final String TEXT_597 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */";
  protected final String TEXT_598 = NL + "\tvoid set";
  protected final String TEXT_599 = "(";
  protected final String TEXT_600 = " value);" + NL;
  protected final String TEXT_601 = NL + "\tpublic void set";
  protected final String TEXT_602 = "_";
  protected final String TEXT_603 = "(";
  protected final String TEXT_604 = " ";
  protected final String TEXT_605 = ")" + NL + "\t{";
  protected final String TEXT_606 = NL + "\t\teDynamicSet(";
  protected final String TEXT_607 = ", ";
  protected final String TEXT_608 = ", ";
  protected final String TEXT_609 = "new ";
  protected final String TEXT_610 = "(";
  protected final String TEXT_611 = "new";
  protected final String TEXT_612 = ")";
  protected final String TEXT_613 = ");";
  protected final String TEXT_614 = NL + "\t\teSet(";
  protected final String TEXT_615 = ", ";
  protected final String TEXT_616 = "new ";
  protected final String TEXT_617 = "(";
  protected final String TEXT_618 = "new";
  protected final String TEXT_619 = ")";
  protected final String TEXT_620 = ");";
  protected final String TEXT_621 = NL + "\t\tif (new";
  protected final String TEXT_622 = " != eInternalContainer() || (eContainerFeatureID() != ";
  protected final String TEXT_623 = " && new";
  protected final String TEXT_624 = " != null))" + NL + "\t\t{" + NL + "\t\t\tif (";
  protected final String TEXT_625 = ".isAncestor(this, ";
  protected final String TEXT_626 = "new";
  protected final String TEXT_627 = "))" + NL + "\t\t\t\tthrow new ";
  protected final String TEXT_628 = "(\"Recursive containment not allowed for \" + toString());";
  protected final String TEXT_629 = NL + "\t\t\t";
  protected final String TEXT_630 = " msgs = null;" + NL + "\t\t\tif (eInternalContainer() != null)" + NL + "\t\t\t\tmsgs = eBasicRemoveFromContainer(msgs);" + NL + "\t\t\tif (new";
  protected final String TEXT_631 = " != null)" + NL + "\t\t\t\tmsgs = ((";
  protected final String TEXT_632 = ")new";
  protected final String TEXT_633 = ").eInverseAdd(this, ";
  protected final String TEXT_634 = ", ";
  protected final String TEXT_635 = ".class, msgs);" + NL + "\t\t\tmsgs = basicSet";
  protected final String TEXT_636 = "(";
  protected final String TEXT_637 = "new";
  protected final String TEXT_638 = ", msgs);" + NL + "\t\t\tif (msgs != null) msgs.dispatch();" + NL + "\t\t}";
  protected final String TEXT_639 = NL + "\t\telse if (eNotificationRequired())" + NL + "\t\t\teNotify(new ";
  protected final String TEXT_640 = "(this, ";
  protected final String TEXT_641 = ".SET, ";
  protected final String TEXT_642 = ", new";
  protected final String TEXT_643 = ", new";
  protected final String TEXT_644 = "));";
  protected final String TEXT_645 = NL + "\t\t";
  protected final String TEXT_646 = " ";
  protected final String TEXT_647 = " = (";
  protected final String TEXT_648 = ")eVirtualGet(";
  protected final String TEXT_649 = ");";
  protected final String TEXT_650 = NL + "\t\tif (new";
  protected final String TEXT_651 = " != ";
  protected final String TEXT_652 = ")" + NL + "\t\t{" + NL + "\t\t\t";
  protected final String TEXT_653 = " msgs = null;" + NL + "\t\t\tif (";
  protected final String TEXT_654 = " != null)";
  protected final String TEXT_655 = NL + "\t\t\t\tmsgs = ((";
  protected final String TEXT_656 = ")";
  protected final String TEXT_657 = ").eInverseRemove(this, EOPPOSITE_FEATURE_BASE - ";
  protected final String TEXT_658 = ", null, msgs);" + NL + "\t\t\tif (new";
  protected final String TEXT_659 = " != null)" + NL + "\t\t\t\tmsgs = ((";
  protected final String TEXT_660 = ")new";
  protected final String TEXT_661 = ").eInverseAdd(this, EOPPOSITE_FEATURE_BASE - ";
  protected final String TEXT_662 = ", null, msgs);";
  protected final String TEXT_663 = NL + "\t\t\t\tmsgs = ((";
  protected final String TEXT_664 = ")";
  protected final String TEXT_665 = ").eInverseRemove(this, ";
  protected final String TEXT_666 = ", ";
  protected final String TEXT_667 = ".class, msgs);" + NL + "\t\t\tif (new";
  protected final String TEXT_668 = " != null)" + NL + "\t\t\t\tmsgs = ((";
  protected final String TEXT_669 = ")new";
  protected final String TEXT_670 = ").eInverseAdd(this, ";
  protected final String TEXT_671 = ", ";
  protected final String TEXT_672 = ".class, msgs);";
  protected final String TEXT_673 = NL + "\t\t\tmsgs = basicSet";
  protected final String TEXT_674 = "(";
  protected final String TEXT_675 = "new";
  protected final String TEXT_676 = ", msgs);" + NL + "\t\t\tif (msgs != null) msgs.dispatch();" + NL + "\t\t}";
  protected final String TEXT_677 = NL + "\t\telse" + NL + "\t\t{";
  protected final String TEXT_678 = NL + "\t\t\tboolean old";
  protected final String TEXT_679 = "ESet = eVirtualIsSet(";
  protected final String TEXT_680 = ");";
  protected final String TEXT_681 = NL + "\t\t\tboolean old";
  protected final String TEXT_682 = "ESet = (";
  protected final String TEXT_683 = " & ";
  protected final String TEXT_684 = "_ESETFLAG) != 0;";
  protected final String TEXT_685 = NL + "\t\t\t";
  protected final String TEXT_686 = " |= ";
  protected final String TEXT_687 = "_ESETFLAG;";
  protected final String TEXT_688 = NL + "\t\t\tboolean old";
  protected final String TEXT_689 = "ESet = ";
  protected final String TEXT_690 = "ESet;";
  protected final String TEXT_691 = NL + "\t\t\t";
  protected final String TEXT_692 = "ESet = true;";
  protected final String TEXT_693 = NL + "\t\t\tif (eNotificationRequired())" + NL + "\t\t\t\teNotify(new ";
  protected final String TEXT_694 = "(this, ";
  protected final String TEXT_695 = ".SET, ";
  protected final String TEXT_696 = ", new";
  protected final String TEXT_697 = ", new";
  protected final String TEXT_698 = ", !old";
  protected final String TEXT_699 = "ESet));";
  protected final String TEXT_700 = NL + "\t\t}";
  protected final String TEXT_701 = NL + "\t\telse if (eNotificationRequired())" + NL + "\t\t\teNotify(new ";
  protected final String TEXT_702 = "(this, ";
  protected final String TEXT_703 = ".SET, ";
  protected final String TEXT_704 = ", new";
  protected final String TEXT_705 = ", new";
  protected final String TEXT_706 = "));";
  protected final String TEXT_707 = NL + "\t\t";
  protected final String TEXT_708 = " old";
  protected final String TEXT_709 = " = (";
  protected final String TEXT_710 = " & ";
  protected final String TEXT_711 = "_EFLAG) != 0;";
  protected final String TEXT_712 = NL + "\t\t";
  protected final String TEXT_713 = " old";
  protected final String TEXT_714 = " = ";
  protected final String TEXT_715 = "_EFLAG_VALUES[(";
  protected final String TEXT_716 = " & ";
  protected final String TEXT_717 = "_EFLAG) >>> ";
  protected final String TEXT_718 = "_EFLAG_OFFSET];";
  protected final String TEXT_719 = NL + "\t\tif (new";
  protected final String TEXT_720 = ") ";
  protected final String TEXT_721 = " |= ";
  protected final String TEXT_722 = "_EFLAG; else ";
  protected final String TEXT_723 = " &= ~";
  protected final String TEXT_724 = "_EFLAG;";
  protected final String TEXT_725 = NL + "\t\tif (new";
  protected final String TEXT_726 = " == null) new";
  protected final String TEXT_727 = " = ";
  protected final String TEXT_728 = "_EDEFAULT;" + NL + "\t\t";
  protected final String TEXT_729 = " = ";
  protected final String TEXT_730 = " & ~";
  protected final String TEXT_731 = "_EFLAG | ";
  protected final String TEXT_732 = "new";
  protected final String TEXT_733 = ".ordinal()";
  protected final String TEXT_734 = ".VALUES.indexOf(new";
  protected final String TEXT_735 = ")";
  protected final String TEXT_736 = " << ";
  protected final String TEXT_737 = "_EFLAG_OFFSET;";
  protected final String TEXT_738 = NL + "\t\t";
  protected final String TEXT_739 = " old";
  protected final String TEXT_740 = " = ";
  protected final String TEXT_741 = ";";
  protected final String TEXT_742 = NL + "\t\t";
  protected final String TEXT_743 = " ";
  protected final String TEXT_744 = " = new";
  protected final String TEXT_745 = " == null ? ";
  protected final String TEXT_746 = " : new";
  protected final String TEXT_747 = ";";
  protected final String TEXT_748 = NL + "\t\t";
  protected final String TEXT_749 = " = new";
  protected final String TEXT_750 = " == null ? ";
  protected final String TEXT_751 = " : new";
  protected final String TEXT_752 = ";";
  protected final String TEXT_753 = NL + "\t\t";
  protected final String TEXT_754 = " ";
  protected final String TEXT_755 = " = ";
  protected final String TEXT_756 = "new";
  protected final String TEXT_757 = ";";
  protected final String TEXT_758 = NL + "\t\t";
  protected final String TEXT_759 = " = ";
  protected final String TEXT_760 = "new";
  protected final String TEXT_761 = ";";
  protected final String TEXT_762 = NL + "\t\tObject old";
  protected final String TEXT_763 = " = eVirtualSet(";
  protected final String TEXT_764 = ", ";
  protected final String TEXT_765 = ");";
  protected final String TEXT_766 = NL + "\t\tboolean isSetChange = old";
  protected final String TEXT_767 = " == EVIRTUAL_NO_VALUE;";
  protected final String TEXT_768 = NL + "\t\tboolean old";
  protected final String TEXT_769 = "ESet = (";
  protected final String TEXT_770 = " & ";
  protected final String TEXT_771 = "_ESETFLAG) != 0;";
  protected final String TEXT_772 = NL + "\t\t";
  protected final String TEXT_773 = " |= ";
  protected final String TEXT_774 = "_ESETFLAG;";
  protected final String TEXT_775 = NL + "\t\tboolean old";
  protected final String TEXT_776 = "ESet = ";
  protected final String TEXT_777 = "ESet;";
  protected final String TEXT_778 = NL + "\t\t";
  protected final String TEXT_779 = "ESet = true;";
  protected final String TEXT_780 = NL + "\t\tif (eNotificationRequired())" + NL + "\t\t\teNotify(new ";
  protected final String TEXT_781 = "(this, ";
  protected final String TEXT_782 = ".SET, ";
  protected final String TEXT_783 = ", ";
  protected final String TEXT_784 = "isSetChange ? ";
  protected final String TEXT_785 = " : old";
  protected final String TEXT_786 = "old";
  protected final String TEXT_787 = ", ";
  protected final String TEXT_788 = "new";
  protected final String TEXT_789 = ", ";
  protected final String TEXT_790 = "isSetChange";
  protected final String TEXT_791 = "!old";
  protected final String TEXT_792 = "ESet";
  protected final String TEXT_793 = "));";
  protected final String TEXT_794 = NL + "\t\tif (eNotificationRequired())" + NL + "\t\t\teNotify(new ";
  protected final String TEXT_795 = "(this, ";
  protected final String TEXT_796 = ".SET, ";
  protected final String TEXT_797 = ", ";
  protected final String TEXT_798 = "old";
  protected final String TEXT_799 = " == EVIRTUAL_NO_VALUE ? ";
  protected final String TEXT_800 = " : old";
  protected final String TEXT_801 = "old";
  protected final String TEXT_802 = ", ";
  protected final String TEXT_803 = "new";
  protected final String TEXT_804 = "));";
  protected final String TEXT_805 = NL + "\t\t((";
  protected final String TEXT_806 = ".Internal)((";
  protected final String TEXT_807 = ".Internal.Wrapper)get";
  protected final String TEXT_808 = "()).featureMap()).set(";
  protected final String TEXT_809 = ", ";
  protected final String TEXT_810 = "new ";
  protected final String TEXT_811 = "(";
  protected final String TEXT_812 = "new";
  protected final String TEXT_813 = ")";
  protected final String TEXT_814 = ");";
  protected final String TEXT_815 = NL + "\t\t((";
  protected final String TEXT_816 = ".Internal)get";
  protected final String TEXT_817 = "()).set(";
  protected final String TEXT_818 = ", ";
  protected final String TEXT_819 = "new ";
  protected final String TEXT_820 = "(";
  protected final String TEXT_821 = "new";
  protected final String TEXT_822 = ")";
  protected final String TEXT_823 = ");";
  protected final String TEXT_824 = NL + "\t\t";
  protected final String TEXT_825 = NL + "\t\t// TODO: implement this method to set the '";
  protected final String TEXT_826 = "' ";
  protected final String TEXT_827 = NL + "\t\t// Ensure that you remove @generated or mark it @generated NOT" + NL + "\t\tthrow new UnsupportedOperationException();";
  protected final String TEXT_828 = NL + "\t}" + NL;
  protected final String TEXT_829 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */";
  protected final String TEXT_830 = NL + "\tpublic ";
  protected final String TEXT_831 = " basicUnset";
  protected final String TEXT_832 = "(";
  protected final String TEXT_833 = " msgs)" + NL + "\t{";
  protected final String TEXT_834 = NL + "\t\treturn eDynamicInverseRemove((";
  protected final String TEXT_835 = ")";
  protected final String TEXT_836 = "basicGet";
  protected final String TEXT_837 = "(), ";
  protected final String TEXT_838 = ", msgs);";
  protected final String TEXT_839 = "Object old";
  protected final String TEXT_840 = " = ";
  protected final String TEXT_841 = "eVirtualUnset(";
  protected final String TEXT_842 = ");";
  protected final String TEXT_843 = NL + "\t\t";
  protected final String TEXT_844 = " old";
  protected final String TEXT_845 = " = ";
  protected final String TEXT_846 = ";";
  protected final String TEXT_847 = NL + "\t\t";
  protected final String TEXT_848 = " = null;";
  protected final String TEXT_849 = NL + "\t\tboolean isSetChange = old";
  protected final String TEXT_850 = " != EVIRTUAL_NO_VALUE;";
  protected final String TEXT_851 = NL + "\t\tboolean old";
  protected final String TEXT_852 = "ESet = (";
  protected final String TEXT_853 = " & ";
  protected final String TEXT_854 = "_ESETFLAG) != 0;";
  protected final String TEXT_855 = NL + "\t\t";
  protected final String TEXT_856 = " &= ~";
  protected final String TEXT_857 = "_ESETFLAG;";
  protected final String TEXT_858 = NL + "\t\tboolean old";
  protected final String TEXT_859 = "ESet = ";
  protected final String TEXT_860 = "ESet;";
  protected final String TEXT_861 = NL + "\t\t";
  protected final String TEXT_862 = "ESet = false;";
  protected final String TEXT_863 = NL + "\t\tif (eNotificationRequired())" + NL + "\t\t{" + NL + "\t\t\t";
  protected final String TEXT_864 = " notification = new ";
  protected final String TEXT_865 = "(this, ";
  protected final String TEXT_866 = ".UNSET, ";
  protected final String TEXT_867 = ", ";
  protected final String TEXT_868 = "isSetChange ? old";
  protected final String TEXT_869 = " : null";
  protected final String TEXT_870 = "old";
  protected final String TEXT_871 = ", null, ";
  protected final String TEXT_872 = "isSetChange";
  protected final String TEXT_873 = "old";
  protected final String TEXT_874 = "ESet";
  protected final String TEXT_875 = ");" + NL + "\t\t\tif (msgs == null) msgs = notification; else msgs.add(notification);" + NL + "\t\t}" + NL + "\t\treturn msgs;";
  protected final String TEXT_876 = NL + "\t\t// TODO: implement this method to unset the contained '";
  protected final String TEXT_877 = "' ";
  protected final String TEXT_878 = NL + "\t\t// -> this method is automatically invoked to keep the containment relationship in synch" + NL + "\t\t// -> do not modify other features" + NL + "\t\t// -> return msgs, after adding any generated Notification to it (if it is null, a NotificationChain object must be created first)" + NL + "\t\t// Ensure that you remove @generated or mark it @generated NOT" + NL + "\t\tthrow new UnsupportedOperationException();";
  protected final String TEXT_879 = NL + "\t}" + NL;
  protected final String TEXT_880 = NL + "\t/**" + NL + "\t * Unsets the value of the '{@link ";
  protected final String TEXT_881 = "#";
  protected final String TEXT_882 = " <em>";
  protected final String TEXT_883 = "</em>}' ";
  protected final String TEXT_884 = ".";
  protected final String TEXT_885 = NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->";
  protected final String TEXT_886 = NL + "\t * @see #isSet";
  protected final String TEXT_887 = "()";
  protected final String TEXT_888 = NL + "\t * @see #";
  protected final String TEXT_889 = "()";
  protected final String TEXT_890 = NL + "\t * @see #set";
  protected final String TEXT_891 = "(";
  protected final String TEXT_892 = ")";
  protected final String TEXT_893 = NL + "\t * @generated" + NL + "\t */";
  protected final String TEXT_894 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */";
  protected final String TEXT_895 = NL + "\tvoid unset";
  protected final String TEXT_896 = "();" + NL;
  protected final String TEXT_897 = NL + "\tpublic void unset";
  protected final String TEXT_898 = "_";
  protected final String TEXT_899 = "()" + NL + "\t{";
  protected final String TEXT_900 = NL + "\t\teDynamicUnset(";
  protected final String TEXT_901 = ", ";
  protected final String TEXT_902 = ");";
  protected final String TEXT_903 = NL + "\t\teUnset(";
  protected final String TEXT_904 = ");";
  protected final String TEXT_905 = NL + "\t\t";
  protected final String TEXT_906 = " ";
  protected final String TEXT_907 = " = (";
  protected final String TEXT_908 = ")eVirtualGet(";
  protected final String TEXT_909 = ");";
  protected final String TEXT_910 = NL + "\t\tif (";
  protected final String TEXT_911 = " != null) ((";
  protected final String TEXT_912 = ".Unsettable";
  protected final String TEXT_913 = ")";
  protected final String TEXT_914 = ").unset();";
  protected final String TEXT_915 = NL + "\t\t";
  protected final String TEXT_916 = " ";
  protected final String TEXT_917 = " = (";
  protected final String TEXT_918 = ")eVirtualGet(";
  protected final String TEXT_919 = ");";
  protected final String TEXT_920 = NL + "\t\tif (";
  protected final String TEXT_921 = " != null)" + NL + "\t\t{" + NL + "\t\t\t";
  protected final String TEXT_922 = " msgs = null;";
  protected final String TEXT_923 = NL + "\t\t\tmsgs = ((";
  protected final String TEXT_924 = ")";
  protected final String TEXT_925 = ").eInverseRemove(this, EOPPOSITE_FEATURE_BASE - ";
  protected final String TEXT_926 = ", null, msgs);";
  protected final String TEXT_927 = NL + "\t\t\tmsgs = ((";
  protected final String TEXT_928 = ")";
  protected final String TEXT_929 = ").eInverseRemove(this, ";
  protected final String TEXT_930 = ", ";
  protected final String TEXT_931 = ".class, msgs);";
  protected final String TEXT_932 = NL + "\t\t\tmsgs = basicUnset";
  protected final String TEXT_933 = "(msgs);" + NL + "\t\t\tif (msgs != null) msgs.dispatch();" + NL + "\t\t}" + NL + "\t\telse" + NL + "\t\t{";
  protected final String TEXT_934 = NL + "\t\t\tboolean old";
  protected final String TEXT_935 = "ESet = eVirtualIsSet(";
  protected final String TEXT_936 = ");";
  protected final String TEXT_937 = NL + "\t\t\tboolean old";
  protected final String TEXT_938 = "ESet = (";
  protected final String TEXT_939 = " & ";
  protected final String TEXT_940 = "_ESETFLAG) != 0;";
  protected final String TEXT_941 = NL + "\t\t\t";
  protected final String TEXT_942 = " &= ~";
  protected final String TEXT_943 = "_ESETFLAG;";
  protected final String TEXT_944 = NL + "\t\t\tboolean old";
  protected final String TEXT_945 = "ESet = ";
  protected final String TEXT_946 = "ESet;";
  protected final String TEXT_947 = NL + "\t\t\t";
  protected final String TEXT_948 = "ESet = false;";
  protected final String TEXT_949 = NL + "\t\t\tif (eNotificationRequired())" + NL + "\t\t\t\teNotify(new ";
  protected final String TEXT_950 = "(this, ";
  protected final String TEXT_951 = ".UNSET, ";
  protected final String TEXT_952 = ", null, null, old";
  protected final String TEXT_953 = "ESet));";
  protected final String TEXT_954 = NL + "\t\t}";
  protected final String TEXT_955 = NL + "\t\t";
  protected final String TEXT_956 = " old";
  protected final String TEXT_957 = " = (";
  protected final String TEXT_958 = " & ";
  protected final String TEXT_959 = "_EFLAG) != 0;";
  protected final String TEXT_960 = NL + "\t\t";
  protected final String TEXT_961 = " old";
  protected final String TEXT_962 = " = ";
  protected final String TEXT_963 = "_EFLAG_VALUES[(";
  protected final String TEXT_964 = " & ";
  protected final String TEXT_965 = "_EFLAG) >>> ";
  protected final String TEXT_966 = "_EFLAG_OFFSET];";
  protected final String TEXT_967 = NL + "\t\tObject old";
  protected final String TEXT_968 = " = eVirtualUnset(";
  protected final String TEXT_969 = ");";
  protected final String TEXT_970 = NL + "\t\t";
  protected final String TEXT_971 = " old";
  protected final String TEXT_972 = " = ";
  protected final String TEXT_973 = ";";
  protected final String TEXT_974 = NL + "\t\tboolean isSetChange = old";
  protected final String TEXT_975 = " != EVIRTUAL_NO_VALUE;";
  protected final String TEXT_976 = NL + "\t\tboolean old";
  protected final String TEXT_977 = "ESet = (";
  protected final String TEXT_978 = " & ";
  protected final String TEXT_979 = "_ESETFLAG) != 0;";
  protected final String TEXT_980 = NL + "\t\tboolean old";
  protected final String TEXT_981 = "ESet = ";
  protected final String TEXT_982 = "ESet;";
  protected final String TEXT_983 = NL + "\t\t";
  protected final String TEXT_984 = " = null;";
  protected final String TEXT_985 = NL + "\t\t";
  protected final String TEXT_986 = " &= ~";
  protected final String TEXT_987 = "_ESETFLAG;";
  protected final String TEXT_988 = NL + "\t\t";
  protected final String TEXT_989 = "ESet = false;";
  protected final String TEXT_990 = NL + "\t\tif (eNotificationRequired())" + NL + "\t\t\teNotify(new ";
  protected final String TEXT_991 = "(this, ";
  protected final String TEXT_992 = ".UNSET, ";
  protected final String TEXT_993 = ", ";
  protected final String TEXT_994 = "isSetChange ? old";
  protected final String TEXT_995 = " : null";
  protected final String TEXT_996 = "old";
  protected final String TEXT_997 = ", null, ";
  protected final String TEXT_998 = "isSetChange";
  protected final String TEXT_999 = "old";
  protected final String TEXT_1000 = "ESet";
  protected final String TEXT_1001 = "));";
  protected final String TEXT_1002 = NL + "\t\tif (";
  protected final String TEXT_1003 = ") ";
  protected final String TEXT_1004 = " |= ";
  protected final String TEXT_1005 = "_EFLAG; else ";
  protected final String TEXT_1006 = " &= ~";
  protected final String TEXT_1007 = "_EFLAG;";
  protected final String TEXT_1008 = NL + "\t\t";
  protected final String TEXT_1009 = " = ";
  protected final String TEXT_1010 = " & ~";
  protected final String TEXT_1011 = "_EFLAG | ";
  protected final String TEXT_1012 = "_EFLAG_DEFAULT;";
  protected final String TEXT_1013 = NL + "\t\t";
  protected final String TEXT_1014 = " = ";
  protected final String TEXT_1015 = ";";
  protected final String TEXT_1016 = NL + "\t\t";
  protected final String TEXT_1017 = " &= ~";
  protected final String TEXT_1018 = "_ESETFLAG;";
  protected final String TEXT_1019 = NL + "\t\t";
  protected final String TEXT_1020 = "ESet = false;";
  protected final String TEXT_1021 = NL + "\t\tif (eNotificationRequired())" + NL + "\t\t\teNotify(new ";
  protected final String TEXT_1022 = "(this, ";
  protected final String TEXT_1023 = ".UNSET, ";
  protected final String TEXT_1024 = ", ";
  protected final String TEXT_1025 = "isSetChange ? old";
  protected final String TEXT_1026 = " : ";
  protected final String TEXT_1027 = "old";
  protected final String TEXT_1028 = ", ";
  protected final String TEXT_1029 = ", ";
  protected final String TEXT_1030 = "isSetChange";
  protected final String TEXT_1031 = "old";
  protected final String TEXT_1032 = "ESet";
  protected final String TEXT_1033 = "));";
  protected final String TEXT_1034 = NL + "\t\t((";
  protected final String TEXT_1035 = ".Internal)((";
  protected final String TEXT_1036 = ".Internal.Wrapper)get";
  protected final String TEXT_1037 = "()).featureMap()).clear(";
  protected final String TEXT_1038 = ");";
  protected final String TEXT_1039 = NL + "\t\t((";
  protected final String TEXT_1040 = ".Internal)get";
  protected final String TEXT_1041 = "()).clear(";
  protected final String TEXT_1042 = ");";
  protected final String TEXT_1043 = NL + "\t\t";
  protected final String TEXT_1044 = NL + "\t\t// TODO: implement this method to unset the '";
  protected final String TEXT_1045 = "' ";
  protected final String TEXT_1046 = NL + "\t\t// Ensure that you remove @generated or mark it @generated NOT" + NL + "\t\tthrow new UnsupportedOperationException();";
  protected final String TEXT_1047 = NL + "\t}" + NL;
  protected final String TEXT_1048 = NL + "\t/**" + NL + "\t * Returns whether the value of the '{@link ";
  protected final String TEXT_1049 = "#";
  protected final String TEXT_1050 = " <em>";
  protected final String TEXT_1051 = "</em>}' ";
  protected final String TEXT_1052 = " is set.";
  protected final String TEXT_1053 = NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @return whether the value of the '<em>";
  protected final String TEXT_1054 = "</em>' ";
  protected final String TEXT_1055 = " is set.";
  protected final String TEXT_1056 = NL + "\t * @see #unset";
  protected final String TEXT_1057 = "()";
  protected final String TEXT_1058 = NL + "\t * @see #";
  protected final String TEXT_1059 = "()";
  protected final String TEXT_1060 = NL + "\t * @see #set";
  protected final String TEXT_1061 = "(";
  protected final String TEXT_1062 = ")";
  protected final String TEXT_1063 = NL + "\t * @generated" + NL + "\t */";
  protected final String TEXT_1064 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */";
  protected final String TEXT_1065 = NL + "\tboolean isSet";
  protected final String TEXT_1066 = "();" + NL;
  protected final String TEXT_1067 = NL + "\tpublic boolean isSet";
  protected final String TEXT_1068 = "_";
  protected final String TEXT_1069 = "()" + NL + "\t{";
  protected final String TEXT_1070 = NL + "\t\treturn eDynamicIsSet(";
  protected final String TEXT_1071 = ", ";
  protected final String TEXT_1072 = ");";
  protected final String TEXT_1073 = NL + "\t\treturn eIsSet(";
  protected final String TEXT_1074 = ");";
  protected final String TEXT_1075 = NL + "\t\t";
  protected final String TEXT_1076 = " ";
  protected final String TEXT_1077 = " = (";
  protected final String TEXT_1078 = ")eVirtualGet(";
  protected final String TEXT_1079 = ");";
  protected final String TEXT_1080 = NL + "\t\treturn ";
  protected final String TEXT_1081 = " != null && ((";
  protected final String TEXT_1082 = ".Unsettable";
  protected final String TEXT_1083 = ")";
  protected final String TEXT_1084 = ").isSet();";
  protected final String TEXT_1085 = NL + "\t\treturn eVirtualIsSet(";
  protected final String TEXT_1086 = ");";
  protected final String TEXT_1087 = NL + "\t\treturn (";
  protected final String TEXT_1088 = " & ";
  protected final String TEXT_1089 = "_ESETFLAG) != 0;";
  protected final String TEXT_1090 = NL + "\t\treturn ";
  protected final String TEXT_1091 = "ESet;";
  protected final String TEXT_1092 = NL + "\t\treturn !((";
  protected final String TEXT_1093 = ".Internal)((";
  protected final String TEXT_1094 = ".Internal.Wrapper)get";
  protected final String TEXT_1095 = "()).featureMap()).isEmpty(";
  protected final String TEXT_1096 = ");";
  protected final String TEXT_1097 = NL + "\t\treturn !((";
  protected final String TEXT_1098 = ".Internal)get";
  protected final String TEXT_1099 = "()).isEmpty(";
  protected final String TEXT_1100 = ");";
  protected final String TEXT_1101 = NL + "\t\t";
  protected final String TEXT_1102 = NL + "\t\t// TODO: implement this method to return whether the '";
  protected final String TEXT_1103 = "' ";
  protected final String TEXT_1104 = " is set" + NL + "\t\t// Ensure that you remove @generated or mark it @generated NOT" + NL + "\t\tthrow new UnsupportedOperationException();";
  protected final String TEXT_1105 = NL + "\t}" + NL;
  protected final String TEXT_1106 = NL + "\t/**";
  protected final String TEXT_1107 = NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->";
  protected final String TEXT_1108 = NL + "\t * <!-- begin-model-doc -->";
  protected final String TEXT_1109 = NL + "\t * ";
  protected final String TEXT_1110 = NL + "\t * @param ";
  protected final String TEXT_1111 = NL + "\t *   ";
  protected final String TEXT_1112 = NL + "\t * @param ";
  protected final String TEXT_1113 = " ";
  protected final String TEXT_1114 = NL + "\t * <!-- end-model-doc -->";
  protected final String TEXT_1115 = NL + "\t * @model ";
  protected final String TEXT_1116 = NL + "\t *        ";
  protected final String TEXT_1117 = NL + "\t * @model";
  protected final String TEXT_1118 = NL + "\t * @generated" + NL + "\t */";
  protected final String TEXT_1119 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */";
  protected final String TEXT_1120 = NL + "\t";
  protected final String TEXT_1121 = " ";
  protected final String TEXT_1122 = "(";
  protected final String TEXT_1123 = ")";
  protected final String TEXT_1124 = ";" + NL;
  protected final String TEXT_1125 = NL + "\tpublic ";
  protected final String TEXT_1126 = " ";
  protected final String TEXT_1127 = "(";
  protected final String TEXT_1128 = ")";
  protected final String TEXT_1129 = NL + "\t{";
  protected final String TEXT_1130 = NL + "\t\t";
  protected final String TEXT_1131 = NL + "\t\t// TODO: implement this method" + NL + "\t\t// -> specify the condition that violates the invariant" + NL + "\t\t// -> verify the details of the diagnostic, including severity and message" + NL + "\t\t// Ensure that you remove @generated or mark it @generated NOT" + NL + "\t\tif (false)" + NL + "\t\t{" + NL + "\t\t\tif (";
  protected final String TEXT_1132 = " != null)" + NL + "\t\t\t{" + NL + "\t\t\t\t";
  protected final String TEXT_1133 = ".add" + NL + "\t\t\t\t\t(new ";
  protected final String TEXT_1134 = NL + "\t\t\t\t\t\t(";
  protected final String TEXT_1135 = ".ERROR," + NL + "\t\t\t\t\t\t ";
  protected final String TEXT_1136 = ".DIAGNOSTIC_SOURCE," + NL + "\t\t\t\t\t\t ";
  protected final String TEXT_1137 = ".";
  protected final String TEXT_1138 = "," + NL + "\t\t\t\t\t\t ";
  protected final String TEXT_1139 = ".INSTANCE.getString(\"_UI_GenericInvariant_diagnostic\", new Object[] { \"";
  protected final String TEXT_1140 = "\", ";
  protected final String TEXT_1141 = ".getObjectLabel(this, ";
  protected final String TEXT_1142 = ") }),";
  protected final String TEXT_1143 = NL + "\t\t\t\t\t\t new Object [] { this }));" + NL + "\t\t\t}" + NL + "\t\t\treturn false;" + NL + "\t\t}" + NL + "\t\treturn true;";
  protected final String TEXT_1144 = NL + "\t\t// TODO: implement this method" + NL + "\t\t// Ensure that you remove @generated or mark it @generated NOT" + NL + "\t\tthrow new UnsupportedOperationException();";
  protected final String TEXT_1145 = NL + "\t}" + NL;
  protected final String TEXT_1146 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */";
  protected final String TEXT_1147 = NL + "\t@SuppressWarnings(\"unchecked\")";
  protected final String TEXT_1148 = NL + "\t@Override";
  protected final String TEXT_1149 = NL + "\tpublic ";
  protected final String TEXT_1150 = " eInverseAdd(";
  protected final String TEXT_1151 = " otherEnd, int featureID, ";
  protected final String TEXT_1152 = " msgs)" + NL + "\t{" + NL + "\t\tswitch (featureID";
  protected final String TEXT_1153 = ")" + NL + "\t\t{";
  protected final String TEXT_1154 = NL + "\t\t\tcase ";
  protected final String TEXT_1155 = ":";
  protected final String TEXT_1156 = NL + "\t\t\t\treturn ((";
  protected final String TEXT_1157 = "(";
  protected final String TEXT_1158 = ".InternalMapView";
  protected final String TEXT_1159 = ")";
  protected final String TEXT_1160 = "()).eMap()).basicAdd(otherEnd, msgs);";
  protected final String TEXT_1161 = NL + "\t\t\t\treturn (";
  protected final String TEXT_1162 = "()).basicAdd(otherEnd, msgs);";
  protected final String TEXT_1163 = NL + "\t\t\t\tif (eInternalContainer() != null)" + NL + "\t\t\t\t\tmsgs = eBasicRemoveFromContainer(msgs);";
  protected final String TEXT_1164 = NL + "\t\t\t\treturn basicSet";
  protected final String TEXT_1165 = "((";
  protected final String TEXT_1166 = ")otherEnd, msgs);";
  protected final String TEXT_1167 = NL + "\t\t\t\treturn eBasicSetContainer(otherEnd, ";
  protected final String TEXT_1168 = ", msgs);";
  protected final String TEXT_1169 = NL + "\t\t\t\t";
  protected final String TEXT_1170 = " ";
  protected final String TEXT_1171 = " = (";
  protected final String TEXT_1172 = ")eVirtualGet(";
  protected final String TEXT_1173 = ");";
  protected final String TEXT_1174 = NL + "\t\t\t\t";
  protected final String TEXT_1175 = " ";
  protected final String TEXT_1176 = " = ";
  protected final String TEXT_1177 = "basicGet";
  protected final String TEXT_1178 = "();";
  protected final String TEXT_1179 = NL + "\t\t\t\tif (";
  protected final String TEXT_1180 = " != null)";
  protected final String TEXT_1181 = NL + "\t\t\t\t\tmsgs = ((";
  protected final String TEXT_1182 = ")";
  protected final String TEXT_1183 = ").eInverseRemove(this, EOPPOSITE_FEATURE_BASE - ";
  protected final String TEXT_1184 = ", null, msgs);";
  protected final String TEXT_1185 = NL + "\t\t\t\t\tmsgs = ((";
  protected final String TEXT_1186 = ")";
  protected final String TEXT_1187 = ").eInverseRemove(this, ";
  protected final String TEXT_1188 = ", ";
  protected final String TEXT_1189 = ".class, msgs);";
  protected final String TEXT_1190 = NL + "\t\t\t\treturn basicSet";
  protected final String TEXT_1191 = "((";
  protected final String TEXT_1192 = ")otherEnd, msgs);";
  protected final String TEXT_1193 = NL + "\t\t}";
  protected final String TEXT_1194 = NL + "\t\treturn super.eInverseAdd(otherEnd, featureID, msgs);";
  protected final String TEXT_1195 = NL + "\t\treturn eDynamicInverseAdd(otherEnd, featureID, msgs);";
  protected final String TEXT_1196 = NL + "\t}" + NL;
  protected final String TEXT_1197 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */";
  protected final String TEXT_1198 = NL + "\t@Override";
  protected final String TEXT_1199 = NL + "\tpublic ";
  protected final String TEXT_1200 = " eInverseRemove(";
  protected final String TEXT_1201 = " otherEnd, int featureID, ";
  protected final String TEXT_1202 = " msgs)" + NL + "\t{" + NL + "\t\tswitch (featureID";
  protected final String TEXT_1203 = ")" + NL + "\t\t{";
  protected final String TEXT_1204 = NL + "\t\t\tcase ";
  protected final String TEXT_1205 = ":";
  protected final String TEXT_1206 = NL + "\t\t\t\treturn ((";
  protected final String TEXT_1207 = ")((";
  protected final String TEXT_1208 = ".InternalMapView";
  protected final String TEXT_1209 = ")";
  protected final String TEXT_1210 = "()).eMap()).basicRemove(otherEnd, msgs);";
  protected final String TEXT_1211 = NL + "\t\t\t\treturn ((";
  protected final String TEXT_1212 = ")((";
  protected final String TEXT_1213 = ".Internal.Wrapper)";
  protected final String TEXT_1214 = "()).featureMap()).basicRemove(otherEnd, msgs);";
  protected final String TEXT_1215 = NL + "\t\t\t\treturn ((";
  protected final String TEXT_1216 = ")";
  protected final String TEXT_1217 = "()).basicRemove(otherEnd, msgs);";
  protected final String TEXT_1218 = NL + "\t\t\t\treturn eBasicSetContainer(null, ";
  protected final String TEXT_1219 = ", msgs);";
  protected final String TEXT_1220 = NL + "\t\t\t\treturn basicUnset";
  protected final String TEXT_1221 = "(msgs);";
  protected final String TEXT_1222 = NL + "\t\t\t\treturn basicSet";
  protected final String TEXT_1223 = "(null, msgs);";
  protected final String TEXT_1224 = NL + "\t\t}";
  protected final String TEXT_1225 = NL + "\t\treturn super.eInverseRemove(otherEnd, featureID, msgs);";
  protected final String TEXT_1226 = NL + "\t\treturn eDynamicInverseRemove(otherEnd, featureID, msgs);";
  protected final String TEXT_1227 = NL + "\t}" + NL;
  protected final String TEXT_1228 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */";
  protected final String TEXT_1229 = NL + "\t@Override";
  protected final String TEXT_1230 = NL + "\tpublic ";
  protected final String TEXT_1231 = " eBasicRemoveFromContainerFeature(";
  protected final String TEXT_1232 = " msgs)" + NL + "\t{" + NL + "\t\tswitch (eContainerFeatureID()";
  protected final String TEXT_1233 = ")" + NL + "\t\t{";
  protected final String TEXT_1234 = NL + "\t\t\tcase ";
  protected final String TEXT_1235 = ":" + NL + "\t\t\t\treturn eInternalContainer().eInverseRemove(this, ";
  protected final String TEXT_1236 = ", ";
  protected final String TEXT_1237 = ".class, msgs);";
  protected final String TEXT_1238 = NL + "\t\t}";
  protected final String TEXT_1239 = NL + "\t\treturn super.eBasicRemoveFromContainerFeature(msgs);";
  protected final String TEXT_1240 = NL + "\t\treturn eDynamicBasicRemoveFromContainer(msgs);";
  protected final String TEXT_1241 = NL + "\t}" + NL;
  protected final String TEXT_1242 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */";
  protected final String TEXT_1243 = NL + "\t@Override";
  protected final String TEXT_1244 = NL + "\tpublic Object eGet(int featureID, boolean resolve, boolean coreType)" + NL + "\t{" + NL + "\t\tswitch (featureID";
  protected final String TEXT_1245 = ")" + NL + "\t\t{";
  protected final String TEXT_1246 = NL + "\t\t\tcase ";
  protected final String TEXT_1247 = ":";
  protected final String TEXT_1248 = NL + "\t\t\t\treturn ";
  protected final String TEXT_1249 = "();";
  protected final String TEXT_1250 = NL + "\t\t\t\treturn ";
  protected final String TEXT_1251 = "() ? Boolean.TRUE : Boolean.FALSE;";
  protected final String TEXT_1252 = NL + "\t\t\t\treturn new ";
  protected final String TEXT_1253 = "(";
  protected final String TEXT_1254 = "());";
  protected final String TEXT_1255 = NL + "\t\t\t\tif (resolve) return ";
  protected final String TEXT_1256 = "();" + NL + "\t\t\t\treturn basicGet";
  protected final String TEXT_1257 = "();";
  protected final String TEXT_1258 = NL + "\t\t\t\tif (coreType) return ((";
  protected final String TEXT_1259 = ".InternalMapView";
  protected final String TEXT_1260 = ")";
  protected final String TEXT_1261 = "()).eMap();" + NL + "\t\t\t\telse return ";
  protected final String TEXT_1262 = "();";
  protected final String TEXT_1263 = NL + "\t\t\t\tif (coreType) return ";
  protected final String TEXT_1264 = "();" + NL + "\t\t\t\telse return ";
  protected final String TEXT_1265 = "().map();";
  protected final String TEXT_1266 = NL + "\t\t\t\tif (coreType) return ((";
  protected final String TEXT_1267 = ".Internal.Wrapper)";
  protected final String TEXT_1268 = "()).featureMap();" + NL + "\t\t\t\treturn ";
  protected final String TEXT_1269 = "();";
  protected final String TEXT_1270 = NL + "\t\t\t\tif (coreType) return ";
  protected final String TEXT_1271 = "();" + NL + "\t\t\t\treturn ((";
  protected final String TEXT_1272 = ".Internal)";
  protected final String TEXT_1273 = "()).getWrapper();";
  protected final String TEXT_1274 = NL + "\t\t\t\treturn ";
  protected final String TEXT_1275 = "();";
  protected final String TEXT_1276 = NL + "\t\t}";
  protected final String TEXT_1277 = NL + "\t\treturn super.eGet(featureID, resolve, coreType);";
  protected final String TEXT_1278 = NL + "\t\treturn eDynamicGet(featureID, resolve, coreType);";
  protected final String TEXT_1279 = NL + "\t}" + NL;
  protected final String TEXT_1280 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */";
  protected final String TEXT_1281 = NL + "\t@SuppressWarnings(\"unchecked\")";
  protected final String TEXT_1282 = NL + "\t@Override";
  protected final String TEXT_1283 = NL + "\tpublic void eSet(int featureID, Object newValue)" + NL + "\t{" + NL + "\t\tswitch (featureID";
  protected final String TEXT_1284 = ")" + NL + "\t\t{";
  protected final String TEXT_1285 = NL + "\t\t\tcase ";
  protected final String TEXT_1286 = ":";
  protected final String TEXT_1287 = NL + "\t\t\t\t((";
  protected final String TEXT_1288 = ".Internal)((";
  protected final String TEXT_1289 = ".Internal.Wrapper)";
  protected final String TEXT_1290 = "()).featureMap()).set(newValue);";
  protected final String TEXT_1291 = NL + "\t\t\t\t((";
  protected final String TEXT_1292 = ".Internal)";
  protected final String TEXT_1293 = "()).set(newValue);";
  protected final String TEXT_1294 = NL + "\t\t\t\t((";
  protected final String TEXT_1295 = ".Setting)((";
  protected final String TEXT_1296 = ".InternalMapView";
  protected final String TEXT_1297 = ")";
  protected final String TEXT_1298 = "()).eMap()).set(newValue);";
  protected final String TEXT_1299 = NL + "\t\t\t\t((";
  protected final String TEXT_1300 = ".Setting)";
  protected final String TEXT_1301 = "()).set(newValue);";
  protected final String TEXT_1302 = NL + "\t\t\t\t";
  protected final String TEXT_1303 = "().clear();" + NL + "\t\t\t\t";
  protected final String TEXT_1304 = "().addAll((";
  protected final String TEXT_1305 = "<? extends ";
  protected final String TEXT_1306 = ">";
  protected final String TEXT_1307 = ")newValue);";
  protected final String TEXT_1308 = NL + "\t\t\t\tset";
  protected final String TEXT_1309 = "(((";
  protected final String TEXT_1310 = ")newValue).";
  protected final String TEXT_1311 = "());";
  protected final String TEXT_1312 = NL + "\t\t\t\tset";
  protected final String TEXT_1313 = "(";
  protected final String TEXT_1314 = "(";
  protected final String TEXT_1315 = ")";
  protected final String TEXT_1316 = "newValue);";
  protected final String TEXT_1317 = NL + "\t\t\t\treturn;";
  protected final String TEXT_1318 = NL + "\t\t}";
  protected final String TEXT_1319 = NL + "\t\tsuper.eSet(featureID, newValue);";
  protected final String TEXT_1320 = NL + "\t\teDynamicSet(featureID, newValue);";
  protected final String TEXT_1321 = NL + "\t}" + NL;
  protected final String TEXT_1322 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */";
  protected final String TEXT_1323 = NL + "\t@Override";
  protected final String TEXT_1324 = NL + "\tpublic void eUnset(int featureID)" + NL + "\t{" + NL + "\t\tswitch (featureID";
  protected final String TEXT_1325 = ")" + NL + "\t\t{";
  protected final String TEXT_1326 = NL + "\t\t\tcase ";
  protected final String TEXT_1327 = ":";
  protected final String TEXT_1328 = NL + "\t\t\t\t((";
  protected final String TEXT_1329 = ".Internal.Wrapper)";
  protected final String TEXT_1330 = "()).featureMap().clear();";
  protected final String TEXT_1331 = NL + "\t\t\t\t";
  protected final String TEXT_1332 = "().clear();";
  protected final String TEXT_1333 = NL + "\t\t\t\tunset";
  protected final String TEXT_1334 = "();";
  protected final String TEXT_1335 = NL + "\t\t\t\tset";
  protected final String TEXT_1336 = "((";
  protected final String TEXT_1337 = ")null);";
  protected final String TEXT_1338 = NL + "\t\t\t\tset";
  protected final String TEXT_1339 = "(";
  protected final String TEXT_1340 = ");";
  protected final String TEXT_1341 = NL + "\t\t\t\treturn;";
  protected final String TEXT_1342 = NL + "\t\t}";
  protected final String TEXT_1343 = NL + "\t\tsuper.eUnset(featureID);";
  protected final String TEXT_1344 = NL + "\t\teDynamicUnset(featureID);";
  protected final String TEXT_1345 = NL + "\t}" + NL;
  protected final String TEXT_1346 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */";
  protected final String TEXT_1347 = NL + "\t@SuppressWarnings(\"unchecked\")";
  protected final String TEXT_1348 = NL + "\t@Override";
  protected final String TEXT_1349 = NL + "\tpublic boolean eIsSet(int featureID)" + NL + "\t{" + NL + "\t\tswitch (featureID";
  protected final String TEXT_1350 = ")" + NL + "\t\t{";
  protected final String TEXT_1351 = NL + "\t\t\tcase ";
  protected final String TEXT_1352 = ":";
  protected final String TEXT_1353 = NL + "\t\t\t\treturn !((";
  protected final String TEXT_1354 = ".Internal.Wrapper)";
  protected final String TEXT_1355 = "()).featureMap().isEmpty();";
  protected final String TEXT_1356 = NL + "\t\t\t\treturn ";
  protected final String TEXT_1357 = " != null && !";
  protected final String TEXT_1358 = ".featureMap().isEmpty();";
  protected final String TEXT_1359 = NL + "\t\t\t\treturn ";
  protected final String TEXT_1360 = " != null && !";
  protected final String TEXT_1361 = ".isEmpty();";
  protected final String TEXT_1362 = NL + "\t\t\t\t";
  protected final String TEXT_1363 = " ";
  protected final String TEXT_1364 = " = (";
  protected final String TEXT_1365 = ")eVirtualGet(";
  protected final String TEXT_1366 = ");" + NL + "\t\t\t\treturn ";
  protected final String TEXT_1367 = " != null && !";
  protected final String TEXT_1368 = ".isEmpty();";
  protected final String TEXT_1369 = NL + "\t\t\t\treturn !";
  protected final String TEXT_1370 = "().isEmpty();";
  protected final String TEXT_1371 = NL + "\t\t\t\treturn isSet";
  protected final String TEXT_1372 = "();";
  protected final String TEXT_1373 = NL + "\t\t\t\treturn ";
  protected final String TEXT_1374 = " != null;";
  protected final String TEXT_1375 = NL + "\t\t\t\treturn eVirtualGet(";
  protected final String TEXT_1376 = ") != null;";
  protected final String TEXT_1377 = NL + "\t\t\t\treturn basicGet";
  protected final String TEXT_1378 = "() != null;";
  protected final String TEXT_1379 = NL + "\t\t\t\treturn ";
  protected final String TEXT_1380 = " != null;";
  protected final String TEXT_1381 = NL + "\t\t\t\treturn eVirtualGet(";
  protected final String TEXT_1382 = ") != null;";
  protected final String TEXT_1383 = NL + "\t\t\t\treturn ";
  protected final String TEXT_1384 = "() != null;";
  protected final String TEXT_1385 = NL + "\t\t\t\treturn ((";
  protected final String TEXT_1386 = " & ";
  protected final String TEXT_1387 = "_EFLAG) != 0) != ";
  protected final String TEXT_1388 = ";";
  protected final String TEXT_1389 = NL + "\t\t\t\treturn (";
  protected final String TEXT_1390 = " & ";
  protected final String TEXT_1391 = "_EFLAG) != ";
  protected final String TEXT_1392 = "_EFLAG_DEFAULT;";
  protected final String TEXT_1393 = NL + "\t\t\t\treturn ";
  protected final String TEXT_1394 = " != ";
  protected final String TEXT_1395 = ";";
  protected final String TEXT_1396 = NL + "\t\t\t\treturn eVirtualGet(";
  protected final String TEXT_1397 = ", ";
  protected final String TEXT_1398 = ") != ";
  protected final String TEXT_1399 = ";";
  protected final String TEXT_1400 = NL + "\t\t\t\treturn ";
  protected final String TEXT_1401 = "() != ";
  protected final String TEXT_1402 = ";";
  protected final String TEXT_1403 = NL + "\t\t\t\treturn ";
  protected final String TEXT_1404 = " == null ? ";
  protected final String TEXT_1405 = " != null : !";
  protected final String TEXT_1406 = ".equals(";
  protected final String TEXT_1407 = ");";
  protected final String TEXT_1408 = NL + "\t\t\t\t";
  protected final String TEXT_1409 = " ";
  protected final String TEXT_1410 = " = (";
  protected final String TEXT_1411 = ")eVirtualGet(";
  protected final String TEXT_1412 = ", ";
  protected final String TEXT_1413 = ");" + NL + "\t\t\t\treturn ";
  protected final String TEXT_1414 = " == null ? ";
  protected final String TEXT_1415 = " != null : !";
  protected final String TEXT_1416 = ".equals(";
  protected final String TEXT_1417 = ");";
  protected final String TEXT_1418 = NL + "\t\t\t\treturn ";
  protected final String TEXT_1419 = " == null ? ";
  protected final String TEXT_1420 = "() != null : !";
  protected final String TEXT_1421 = ".equals(";
  protected final String TEXT_1422 = "());";
  protected final String TEXT_1423 = NL + "\t\t}";
  protected final String TEXT_1424 = NL + "\t\treturn super.eIsSet(featureID);";
  protected final String TEXT_1425 = NL + "\t\treturn eDynamicIsSet(featureID);";
  protected final String TEXT_1426 = NL + "\t}" + NL;
  protected final String TEXT_1427 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */";
  protected final String TEXT_1428 = NL + "\t@Override";
  protected final String TEXT_1429 = NL + "\tpublic int eBaseStructuralFeatureID(int derivedFeatureID, Class";
  protected final String TEXT_1430 = " baseClass)" + NL + "\t{";
  protected final String TEXT_1431 = NL + "\t\tif (baseClass == ";
  protected final String TEXT_1432 = ".class)" + NL + "\t\t{" + NL + "\t\t\tswitch (derivedFeatureID";
  protected final String TEXT_1433 = ")" + NL + "\t\t\t{";
  protected final String TEXT_1434 = NL + "\t\t\t\tcase ";
  protected final String TEXT_1435 = ": return ";
  protected final String TEXT_1436 = ";";
  protected final String TEXT_1437 = NL + "\t\t\t\tdefault: return -1;" + NL + "\t\t\t}" + NL + "\t\t}";
  protected final String TEXT_1438 = NL + "\t\treturn super.eBaseStructuralFeatureID(derivedFeatureID, baseClass);" + NL + "\t}";
  protected final String TEXT_1439 = NL + NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */";
  protected final String TEXT_1440 = NL + "\t@Override";
  protected final String TEXT_1441 = NL + "\tpublic int eDerivedStructuralFeatureID(int baseFeatureID, Class";
  protected final String TEXT_1442 = " baseClass)" + NL + "\t{";
  protected final String TEXT_1443 = NL + "\t\tif (baseClass == ";
  protected final String TEXT_1444 = ".class)" + NL + "\t\t{" + NL + "\t\t\tswitch (baseFeatureID)" + NL + "\t\t\t{";
  protected final String TEXT_1445 = NL + "\t\t\t\tcase ";
  protected final String TEXT_1446 = ": return ";
  protected final String TEXT_1447 = ";";
  protected final String TEXT_1448 = NL + "\t\t\t\tdefault: return -1;" + NL + "\t\t\t}" + NL + "\t\t}";
  protected final String TEXT_1449 = NL + "\t\tif (baseClass == ";
  protected final String TEXT_1450 = ".class)" + NL + "\t\t{" + NL + "\t\t\tswitch (baseFeatureID";
  protected final String TEXT_1451 = ")" + NL + "\t\t\t{";
  protected final String TEXT_1452 = NL + "\t\t\t\tcase ";
  protected final String TEXT_1453 = ": return ";
  protected final String TEXT_1454 = ";";
  protected final String TEXT_1455 = NL + "\t\t\t\tdefault: return -1;" + NL + "\t\t\t}" + NL + "\t\t}";
  protected final String TEXT_1456 = NL + "\t\treturn super.eDerivedStructuralFeatureID(baseFeatureID, baseClass);" + NL + "\t}" + NL;
  protected final String TEXT_1457 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */";
  protected final String TEXT_1458 = NL + "\t@Override";
  protected final String TEXT_1459 = NL + "\tprotected Object[] eVirtualValues()" + NL + "\t{" + NL + "\t\treturn ";
  protected final String TEXT_1460 = ";" + NL + "\t}" + NL + "" + NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */";
  protected final String TEXT_1461 = NL + "\t@Override";
  protected final String TEXT_1462 = NL + "\tprotected void eSetVirtualValues(Object[] newValues)" + NL + "\t{" + NL + "\t\t";
  protected final String TEXT_1463 = " = newValues;" + NL + "\t}" + NL;
  protected final String TEXT_1464 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */";
  protected final String TEXT_1465 = NL + "\t@Override";
  protected final String TEXT_1466 = NL + "\tprotected int eVirtualIndexBits(int offset)" + NL + "\t{" + NL + "\t\tswitch (offset)" + NL + "\t\t{";
  protected final String TEXT_1467 = NL + "\t\t\tcase ";
  protected final String TEXT_1468 = " :" + NL + "\t\t\t\treturn ";
  protected final String TEXT_1469 = ";";
  protected final String TEXT_1470 = NL + "\t\t\tdefault :" + NL + "\t\t\t\tthrow new IndexOutOfBoundsException();" + NL + "\t\t}" + NL + "\t}" + NL + "" + NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */";
  protected final String TEXT_1471 = NL + "\t@Override";
  protected final String TEXT_1472 = NL + "\tprotected void eSetVirtualIndexBits(int offset, int newIndexBits)" + NL + "\t{" + NL + "\t\tswitch (offset)" + NL + "\t\t{";
  protected final String TEXT_1473 = NL + "\t\t\tcase ";
  protected final String TEXT_1474 = " :" + NL + "\t\t\t\t";
  protected final String TEXT_1475 = " = newIndexBits;" + NL + "\t\t\t\tbreak;";
  protected final String TEXT_1476 = NL + "\t\t\tdefault :" + NL + "\t\t\t\tthrow new IndexOutOfBoundsException();" + NL + "\t\t}" + NL + "\t}" + NL;
  protected final String TEXT_1477 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */";
  protected final String TEXT_1478 = NL + "\t@Override";
  protected final String TEXT_1479 = NL + "\tpublic String toString()" + NL + "\t{" + NL + "\t\tif (eIsProxy()) return super.toString();" + NL + "" + NL + "\t\tStringBuffer result = new StringBuffer(super.toString());";
  protected final String TEXT_1480 = NL + "\t\tresult.append(\" (";
  protected final String TEXT_1481 = ": \");";
  protected final String TEXT_1482 = NL + "\t\tresult.append(\", ";
  protected final String TEXT_1483 = ": \");";
  protected final String TEXT_1484 = NL + "\t\tif (eVirtualIsSet(";
  protected final String TEXT_1485 = ")) result.append(eVirtualGet(";
  protected final String TEXT_1486 = ")); else result.append(\"<unset>\");";
  protected final String TEXT_1487 = NL + "\t\tif (";
  protected final String TEXT_1488 = "(";
  protected final String TEXT_1489 = " & ";
  protected final String TEXT_1490 = "_ESETFLAG) != 0";
  protected final String TEXT_1491 = "ESet";
  protected final String TEXT_1492 = ") result.append((";
  protected final String TEXT_1493 = " & ";
  protected final String TEXT_1494 = "_EFLAG) != 0); else result.append(\"<unset>\");";
  protected final String TEXT_1495 = NL + "\t\tif (";
  protected final String TEXT_1496 = "(";
  protected final String TEXT_1497 = " & ";
  protected final String TEXT_1498 = "_ESETFLAG) != 0";
  protected final String TEXT_1499 = "ESet";
  protected final String TEXT_1500 = ") result.append(";
  protected final String TEXT_1501 = "_EFLAG_VALUES[(";
  protected final String TEXT_1502 = " & ";
  protected final String TEXT_1503 = "_EFLAG) >>> ";
  protected final String TEXT_1504 = "_EFLAG_OFFSET]); else result.append(\"<unset>\");";
  protected final String TEXT_1505 = NL + "\t\tif (";
  protected final String TEXT_1506 = "(";
  protected final String TEXT_1507 = " & ";
  protected final String TEXT_1508 = "_ESETFLAG) != 0";
  protected final String TEXT_1509 = "ESet";
  protected final String TEXT_1510 = ") result.append(";
  protected final String TEXT_1511 = "); else result.append(\"<unset>\");";
  protected final String TEXT_1512 = NL + "\t\tresult.append(eVirtualGet(";
  protected final String TEXT_1513 = ", ";
  protected final String TEXT_1514 = "));";
  protected final String TEXT_1515 = NL + "\t\tresult.append((";
  protected final String TEXT_1516 = " & ";
  protected final String TEXT_1517 = "_EFLAG) != 0);";
  protected final String TEXT_1518 = NL + "\t\tresult.append(";
  protected final String TEXT_1519 = "_EFLAG_VALUES[(";
  protected final String TEXT_1520 = " & ";
  protected final String TEXT_1521 = "_EFLAG) >>> ";
  protected final String TEXT_1522 = "_EFLAG_OFFSET]);";
  protected final String TEXT_1523 = NL + "\t\tresult.append(";
  protected final String TEXT_1524 = ");";
  protected final String TEXT_1525 = NL + "\t\tresult.append(')');" + NL + "\t\treturn result.toString();" + NL + "\t}" + NL;
  protected final String TEXT_1526 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */" + NL + "\tprotected int hash = -1;" + NL + "" + NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */" + NL + "\tpublic int getHash()" + NL + "\t{" + NL + "\t\tif (hash == -1)" + NL + "\t\t{" + NL + "\t\t\t";
  protected final String TEXT_1527 = " theKey = getKey();" + NL + "\t\t\thash = (theKey == null ? 0 : theKey.hashCode());" + NL + "\t\t}" + NL + "\t\treturn hash;" + NL + "\t}" + NL + "" + NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */" + NL + "\tpublic void setHash(int hash)" + NL + "\t{" + NL + "\t\tthis.hash = hash;" + NL + "\t}" + NL + "" + NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */" + NL + "\tpublic ";
  protected final String TEXT_1528 = " getKey()" + NL + "\t{";
  protected final String TEXT_1529 = NL + "\t\treturn new ";
  protected final String TEXT_1530 = "(getTypedKey());";
  protected final String TEXT_1531 = NL + "\t\treturn getTypedKey();";
  protected final String TEXT_1532 = NL + "\t}" + NL + "" + NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */" + NL + "\tpublic void setKey(";
  protected final String TEXT_1533 = " key)" + NL + "\t{";
  protected final String TEXT_1534 = NL + "\t\tgetTypedKey().addAll(";
  protected final String TEXT_1535 = "(";
  protected final String TEXT_1536 = ")";
  protected final String TEXT_1537 = "key);";
  protected final String TEXT_1538 = NL + "\t\tsetTypedKey(key);";
  protected final String TEXT_1539 = NL + "\t\tsetTypedKey(((";
  protected final String TEXT_1540 = ")key).";
  protected final String TEXT_1541 = "());";
  protected final String TEXT_1542 = NL + "\t\tsetTypedKey((";
  protected final String TEXT_1543 = ")key);";
  protected final String TEXT_1544 = NL + "\t}" + NL + "" + NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */" + NL + "\tpublic ";
  protected final String TEXT_1545 = " getValue()" + NL + "\t{";
  protected final String TEXT_1546 = NL + "\t\treturn new ";
  protected final String TEXT_1547 = "(getTypedValue());";
  protected final String TEXT_1548 = NL + "\t\treturn getTypedValue();";
  protected final String TEXT_1549 = NL + "\t}" + NL + "" + NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */" + NL + "\tpublic ";
  protected final String TEXT_1550 = " setValue(";
  protected final String TEXT_1551 = " value)" + NL + "\t{" + NL + "\t\t";
  protected final String TEXT_1552 = " oldValue = getValue();";
  protected final String TEXT_1553 = NL + "\t\tgetTypedValue().clear();" + NL + "\t\tgetTypedValue().addAll(";
  protected final String TEXT_1554 = "(";
  protected final String TEXT_1555 = ")";
  protected final String TEXT_1556 = "value);";
  protected final String TEXT_1557 = NL + "\t\tsetTypedValue(value);";
  protected final String TEXT_1558 = NL + "\t\tsetTypedValue(((";
  protected final String TEXT_1559 = ")value).";
  protected final String TEXT_1560 = "());";
  protected final String TEXT_1561 = NL + "\t\tsetTypedValue((";
  protected final String TEXT_1562 = ")value);";
  protected final String TEXT_1563 = NL + "\t\treturn oldValue;" + NL + "\t}" + NL + "" + NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */";
  protected final String TEXT_1564 = NL + "\t@SuppressWarnings(\"unchecked\")";
  protected final String TEXT_1565 = NL + "\tpublic ";
  protected final String TEXT_1566 = " getEMap()" + NL + "\t{" + NL + "\t\t";
  protected final String TEXT_1567 = " container = eContainer();" + NL + "\t\treturn container == null ? null : (";
  protected final String TEXT_1568 = ")container.eGet(eContainmentFeature());" + NL + "\t}" + NL;
  protected final String TEXT_1569 = NL + "} //";
  protected final String TEXT_1570 = NL;

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
    final boolean isJDK50 = genModel.getComplianceLevel().getValue() >= GenJDKLevel.JDK50;
    final boolean isInterface = Boolean.TRUE.equals(((Object[])argument)[1]); final boolean isImplementation = Boolean.TRUE.equals(((Object[])argument)[2]);
    final String publicStaticFinalFlag = isImplementation ? "public static final " : "";
    final String singleWildcard = isJDK50 ? "<?>" : "";
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
    if (genFeature.isListType() || genFeature.isReferenceType()) {
    if (genClass.isField(genFeature)) {
    stringBuffer.append(TEXT_62);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_63);
    stringBuffer.append(genFeature.getFormattedName());
    stringBuffer.append(TEXT_64);
    stringBuffer.append(genFeature.getFeatureKind());
    stringBuffer.append(TEXT_65);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_66);
    stringBuffer.append(genFeature.getImportedInternalType(genClass));
    stringBuffer.append(TEXT_67);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_68);
    }
    if (genModel.isArrayAccessors() && genFeature.isListType() && !genFeature.isFeatureMapType() && !genFeature.isMapType()) { String rawListItemType = genFeature.getRawListItemType(); int index = rawListItemType.indexOf('['); String head = rawListItemType; String tail = ""; if (index != -1) { head = rawListItemType.substring(0, index); tail = rawListItemType.substring(index); } 
    stringBuffer.append(TEXT_69);
    stringBuffer.append(genFeature.getGetArrayAccessor());
    stringBuffer.append(TEXT_70);
    stringBuffer.append(genFeature.getFormattedName());
    stringBuffer.append(TEXT_71);
    stringBuffer.append(genFeature.getGetArrayAccessor());
    stringBuffer.append(TEXT_72);
    if (genFeature.getQualifiedListItemType(genClass).contains("<")) {
    stringBuffer.append(TEXT_73);
    }
    stringBuffer.append(TEXT_74);
    stringBuffer.append(rawListItemType);
    stringBuffer.append(TEXT_75);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_76);
    stringBuffer.append(head);
    stringBuffer.append(TEXT_77);
    stringBuffer.append(tail);
    stringBuffer.append(TEXT_78);
    }
    } else {
    if (genFeature.hasEDefault() && (!genFeature.isVolatile() || !genModel.isReflectiveDelegation() && (!genFeature.hasDelegateFeature() || !genFeature.isUnsettable()))) { String staticDefaultValue = genFeature.getStaticDefaultValue();
    stringBuffer.append(TEXT_79);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_80);
    stringBuffer.append(genFeature.getFormattedName());
    stringBuffer.append(TEXT_81);
    stringBuffer.append(genFeature.getFeatureKind());
    stringBuffer.append(TEXT_82);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_83);
    if (genModel.useGenerics() && genFeature.isListDataType() && genFeature.isSetDefaultValue()) {
    stringBuffer.append(TEXT_84);
    }
    stringBuffer.append(TEXT_85);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_86);
    stringBuffer.append(genFeature.getEDefault());
    if ("".equals(staticDefaultValue)) {
    stringBuffer.append(TEXT_87);
    stringBuffer.append(genFeature.getEcoreFeature().getDefaultValueLiteral());
    stringBuffer.append(TEXT_88);
    } else {
    stringBuffer.append(TEXT_89);
    stringBuffer.append(staticDefaultValue);
    stringBuffer.append(TEXT_90);
    stringBuffer.append(genModel.getNonNLS(staticDefaultValue));
    }
    stringBuffer.append(TEXT_91);
    }
    if (genClass.isField(genFeature)) {
    if (genClass.isFlag(genFeature)) { int flagIndex = genClass.getFlagIndex(genFeature);
    if (flagIndex > 31 && flagIndex % 32 == 0) {
    stringBuffer.append(TEXT_92);
    stringBuffer.append(genClass.getFlagsField(genFeature));
    stringBuffer.append(TEXT_93);
    }
    if (genFeature.isEnumType()) {
    stringBuffer.append(TEXT_94);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_95);
    stringBuffer.append(genFeature.getFormattedName());
    stringBuffer.append(TEXT_96);
    stringBuffer.append(genFeature.getFeatureKind());
    stringBuffer.append(TEXT_97);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_98);
    stringBuffer.append(flagIndex % 32);
    stringBuffer.append(TEXT_99);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_100);
    stringBuffer.append(genFeature.getFormattedName());
    stringBuffer.append(TEXT_101);
    stringBuffer.append(genFeature.getFeatureKind());
    stringBuffer.append(TEXT_102);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_103);
    if (isJDK50) {
    stringBuffer.append(genFeature.getEDefault());
    stringBuffer.append(TEXT_104);
    } else {
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_105);
    stringBuffer.append(genFeature.getEDefault());
    stringBuffer.append(TEXT_106);
    }
    stringBuffer.append(TEXT_107);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_108);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_109);
    stringBuffer.append(genFeature.getTypeGenClassifier().getFormattedName());
    stringBuffer.append(TEXT_110);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_111);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_112);
    if (isJDK50) {
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_113);
    } else {
    stringBuffer.append(TEXT_114);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_115);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_116);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_117);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_118);
    }
    stringBuffer.append(TEXT_119);
    }
    stringBuffer.append(TEXT_120);
    stringBuffer.append(genClass.getFlagSize(genFeature) > 1 ? "s" : "");
    stringBuffer.append(TEXT_121);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_122);
    stringBuffer.append(genFeature.getFormattedName());
    stringBuffer.append(TEXT_123);
    stringBuffer.append(genFeature.getFeatureKind());
    stringBuffer.append(TEXT_124);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_125);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_126);
    stringBuffer.append(genClass.getFlagMask(genFeature));
    stringBuffer.append(TEXT_127);
    if (genFeature.isEnumType()) {
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_128);
    } else {
    stringBuffer.append(flagIndex % 32);
    }
    stringBuffer.append(TEXT_129);
    } else {
    stringBuffer.append(TEXT_130);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_131);
    stringBuffer.append(genFeature.getFormattedName());
    stringBuffer.append(TEXT_132);
    stringBuffer.append(genFeature.getFeatureKind());
    stringBuffer.append(TEXT_133);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_134);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_135);
    stringBuffer.append(genFeature.getSafeName());
    if (genFeature.hasEDefault()) {
    stringBuffer.append(TEXT_136);
    stringBuffer.append(genFeature.getEDefault());
    }
    stringBuffer.append(TEXT_137);
    }
    }
    }
    if (genClass.isESetField(genFeature)) {
    if (genClass.isESetFlag(genFeature)) { int flagIndex = genClass.getESetFlagIndex(genFeature);
    if (flagIndex > 31 && flagIndex % 32 == 0) {
    stringBuffer.append(TEXT_138);
    stringBuffer.append(genClass.getESetFlagsField(genFeature));
    stringBuffer.append(TEXT_139);
    }
    stringBuffer.append(TEXT_140);
    stringBuffer.append(genFeature.getFormattedName());
    stringBuffer.append(TEXT_141);
    stringBuffer.append(genFeature.getFeatureKind());
    stringBuffer.append(TEXT_142);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_143);
    stringBuffer.append(flagIndex % 32 );
    stringBuffer.append(TEXT_144);
    } else {
    stringBuffer.append(TEXT_145);
    stringBuffer.append(genFeature.getFormattedName());
    stringBuffer.append(TEXT_146);
    stringBuffer.append(genFeature.getFeatureKind());
    stringBuffer.append(TEXT_147);
    stringBuffer.append(genFeature.getUncapName());
    stringBuffer.append(TEXT_148);
    }
    }
    //Class/declaredFieldGenFeature.override.javajetinc
    }
    }
    if (isImplementation && genClass.hasOffsetCorrection() && !genClass.getImplementedGenFeatures().isEmpty()) {
    stringBuffer.append(TEXT_149);
    stringBuffer.append(genClass.getOffsetCorrectionField(null));
    stringBuffer.append(TEXT_150);
    stringBuffer.append(genClass.getQualifiedClassifierAccessor());
    stringBuffer.append(TEXT_151);
    stringBuffer.append(genClass.getImplementedGenFeatures().get(0).getQualifiedFeatureAccessor());
    stringBuffer.append(TEXT_152);
    stringBuffer.append(genClass.getQualifiedFeatureID(genClass.getImplementedGenFeatures().get(0)));
    stringBuffer.append(TEXT_153);
    }
    if (isImplementation && genClass.hasOffsetCorrection() && !genModel.isReflectiveDelegation()) {
    for (GenFeature genFeature : genClass.getImplementedGenFeatures()) { GenFeature reverseFeature = genFeature.getReverse();
    if (reverseFeature != null && reverseFeature.getGenClass().hasOffsetCorrection()) {
    stringBuffer.append(TEXT_154);
    stringBuffer.append(genClass.getOffsetCorrectionField(genFeature));
    stringBuffer.append(TEXT_155);
    stringBuffer.append(reverseFeature.getGenClass().getQualifiedClassifierAccessor());
    stringBuffer.append(TEXT_156);
    stringBuffer.append(reverseFeature.getQualifiedFeatureAccessor());
    stringBuffer.append(TEXT_157);
    stringBuffer.append(reverseFeature.getGenClass().getQualifiedFeatureID(reverseFeature));
    stringBuffer.append(TEXT_158);
    }
    }
    }
    if (isImplementation) {
    stringBuffer.append(TEXT_159);
    if (genModel.isPublicConstructors()) {
    stringBuffer.append(TEXT_160);
    } else {
    stringBuffer.append(TEXT_161);
    }
    stringBuffer.append(TEXT_162);
    stringBuffer.append(genClass.getClassName());
    stringBuffer.append(TEXT_163);
    for (GenFeature genFeature : genClass.getFlagGenFeaturesWithDefault()) {
    stringBuffer.append(TEXT_164);
    stringBuffer.append(genClass.getFlagsField(genFeature));
    stringBuffer.append(TEXT_165);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_166);
    if (!genFeature.isBooleanType()) {
    stringBuffer.append(TEXT_167);
    }
    stringBuffer.append(TEXT_168);
    }
    stringBuffer.append(TEXT_169);
    if (genModel.useClassOverrideAnnotation()) {
    stringBuffer.append(TEXT_170);
    }
    stringBuffer.append(TEXT_171);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.EClass"));
    stringBuffer.append(TEXT_172);
    stringBuffer.append(genClass.getQualifiedClassifierAccessor());
    stringBuffer.append(TEXT_173);
    }
    if (isImplementation && (genModel.getFeatureDelegation() == GenDelegationKind.REFLECTIVE_LITERAL || genModel.isDynamicDelegation()) && (genClass.getClassExtendsGenClass() == null || (genClass.getClassExtendsGenClass().getGenModel().getFeatureDelegation() != GenDelegationKind.REFLECTIVE_LITERAL && !genClass.getClassExtendsGenClass().getGenModel().isDynamicDelegation()))) {
    stringBuffer.append(TEXT_174);
    if (genModel.useClassOverrideAnnotation()) {
    stringBuffer.append(TEXT_175);
    }
    stringBuffer.append(TEXT_176);
    stringBuffer.append(genClass.getClassExtendsGenClass() == null ? 0 : genClass.getClassExtendsGenClass().getAllGenFeatures().size());
    stringBuffer.append(TEXT_177);
    }
    //Class/reflectiveDelegation.override.javajetinc
    new Runnable() { public void run() {
    for (GenFeature genFeature : (isImplementation ? genClass.getImplementedGenFeatures() : genClass.getDeclaredGenFeatures())) {
    if (genModel.isArrayAccessors() && genFeature.isListType() && !genFeature.isFeatureMapType() && !genFeature.isMapType()) {
    stringBuffer.append(TEXT_178);
    if (!isImplementation) {
    stringBuffer.append(TEXT_179);
    stringBuffer.append(genFeature.getListItemType(genClass));
    stringBuffer.append(TEXT_180);
    stringBuffer.append(genFeature.getGetArrayAccessor());
    stringBuffer.append(TEXT_181);
    } else {
    stringBuffer.append(TEXT_182);
    stringBuffer.append(genFeature.getListItemType(genClass));
    stringBuffer.append(TEXT_183);
    stringBuffer.append(genFeature.getGetArrayAccessor());
    stringBuffer.append(TEXT_184);
    if (genFeature.isVolatile()) {
    stringBuffer.append(TEXT_185);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.util.BasicEList"));
    stringBuffer.append(genFeature.getListTemplateArguments(genClass));
    stringBuffer.append(TEXT_186);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.util.BasicEList"));
    stringBuffer.append(genFeature.getListTemplateArguments(genClass));
    stringBuffer.append(TEXT_187);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_188);
    if (genModel.useGenerics() && !genFeature.getListItemType(genClass).contains("<") && !genFeature.getListItemType(null).equals(genFeature.getListItemType(genClass))) {
    stringBuffer.append(TEXT_189);
    stringBuffer.append(genFeature.getListItemType(genClass));
    stringBuffer.append(TEXT_190);
    }
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_191);
    } else {
    stringBuffer.append(TEXT_192);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_193);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_194);
    if (genModel.useGenerics() && !genFeature.getListItemType(genClass).contains("<") && !genFeature.getListItemType(null).equals(genFeature.getListItemType(genClass))) {
    stringBuffer.append(TEXT_195);
    stringBuffer.append(genFeature.getListItemType(genClass));
    stringBuffer.append(TEXT_196);
    }
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_197);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.util.BasicEList"));
    stringBuffer.append(genFeature.getListTemplateArguments(genClass));
    stringBuffer.append(TEXT_198);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.util.BasicEList"));
    stringBuffer.append(genFeature.getListTemplateArguments(genClass));
    stringBuffer.append(TEXT_199);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_200);
    }
    stringBuffer.append(TEXT_201);
    stringBuffer.append(genFeature.getListItemType(genClass));
    stringBuffer.append(TEXT_202);
    }
    stringBuffer.append(TEXT_203);
    if (!isImplementation) {
    stringBuffer.append(TEXT_204);
    stringBuffer.append(genFeature.getListItemType(genClass));
    stringBuffer.append(TEXT_205);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_206);
    } else {
    stringBuffer.append(TEXT_207);
    stringBuffer.append(genFeature.getListItemType(genClass));
    stringBuffer.append(TEXT_208);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_209);
    if (!genModel.useGenerics()) {
    stringBuffer.append(TEXT_210);
    stringBuffer.append(genFeature.getListItemType(genClass));
    stringBuffer.append(TEXT_211);
    }
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_212);
    }
    stringBuffer.append(TEXT_213);
    if (!isImplementation) {
    stringBuffer.append(TEXT_214);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_215);
    } else {
    stringBuffer.append(TEXT_216);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_217);
    if (genFeature.isVolatile()) {
    stringBuffer.append(TEXT_218);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_219);
    } else {
    stringBuffer.append(TEXT_220);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_221);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_222);
    }
    stringBuffer.append(TEXT_223);
    }
    stringBuffer.append(TEXT_224);
    if (!isImplementation) {
    stringBuffer.append(TEXT_225);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_226);
    stringBuffer.append(genFeature.getListItemType(genClass));
    stringBuffer.append(TEXT_227);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_228);
    } else {
    stringBuffer.append(TEXT_229);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_230);
    stringBuffer.append(genFeature.getListItemType(genClass));
    stringBuffer.append(TEXT_231);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_232);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.util.BasicEList"));
    stringBuffer.append(genFeature.getListTemplateArguments(genClass));
    stringBuffer.append(TEXT_233);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_234);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_235);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_236);
    }
    stringBuffer.append(TEXT_237);
    if (!isImplementation) {
    stringBuffer.append(TEXT_238);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_239);
    stringBuffer.append(genFeature.getListItemType(genClass));
    stringBuffer.append(TEXT_240);
    } else {
    stringBuffer.append(TEXT_241);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_242);
    stringBuffer.append(genFeature.getListItemType(genClass));
    stringBuffer.append(TEXT_243);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_244);
    }
    }
    if (genFeature.isGet() && (isImplementation || !genFeature.isSuppressedGetVisibility())) {
    if (isInterface) {
    stringBuffer.append(TEXT_245);
    stringBuffer.append(genFeature.getFormattedName());
    stringBuffer.append(TEXT_246);
    stringBuffer.append(genFeature.getFeatureKind());
    stringBuffer.append(TEXT_247);
    if (genFeature.isListType()) {
    if (genFeature.isMapType()) { GenFeature keyFeature = genFeature.getMapEntryTypeGenClass().getMapEntryKeyFeature(); GenFeature valueFeature = genFeature.getMapEntryTypeGenClass().getMapEntryValueFeature(); 
    stringBuffer.append(TEXT_248);
    if (keyFeature.isListType()) {
    stringBuffer.append(TEXT_249);
    stringBuffer.append(keyFeature.getQualifiedListItemType(genClass));
    stringBuffer.append(TEXT_250);
    } else {
    stringBuffer.append(TEXT_251);
    stringBuffer.append(keyFeature.getType(genClass));
    stringBuffer.append(TEXT_252);
    }
    stringBuffer.append(TEXT_253);
    if (valueFeature.isListType()) {
    stringBuffer.append(TEXT_254);
    stringBuffer.append(valueFeature.getQualifiedListItemType(genClass));
    stringBuffer.append(TEXT_255);
    } else {
    stringBuffer.append(TEXT_256);
    stringBuffer.append(valueFeature.getType(genClass));
    stringBuffer.append(TEXT_257);
    }
    stringBuffer.append(TEXT_258);
    } else if (!genFeature.isWrappedFeatureMapType() && !(genModel.isSuppressEMFMetaData() && "org.eclipse.emf.ecore.EObject".equals(genFeature.getQualifiedListItemType(genClass)))) {
String typeName = genFeature.getQualifiedListItemType(genClass); String head = typeName; String tail = ""; int index = typeName.indexOf('<'); if (index == -1) { index = typeName.indexOf('['); } 
if (index != -1) { head = typeName.substring(0, index); tail = typeName.substring(index).replaceAll("<", "&lt;"); }

    stringBuffer.append(TEXT_259);
    stringBuffer.append(head);
    stringBuffer.append(TEXT_260);
    stringBuffer.append(tail);
    stringBuffer.append(TEXT_261);
    }
    } else if (genFeature.isSetDefaultValue()) {
    stringBuffer.append(TEXT_262);
    stringBuffer.append(genFeature.getDefaultValue());
    stringBuffer.append(TEXT_263);
    }
    if (genFeature.getTypeGenEnum() != null) {
    stringBuffer.append(TEXT_264);
    stringBuffer.append(genFeature.getTypeGenEnum().getQualifiedName());
    stringBuffer.append(TEXT_265);
    }
    if (genFeature.isBidirectional() && !genFeature.getReverse().getGenClass().isMapEntry()) { GenFeature reverseGenFeature = genFeature.getReverse(); 
    if (!reverseGenFeature.isSuppressedGetVisibility()) {
    stringBuffer.append(TEXT_266);
    stringBuffer.append(reverseGenFeature.getGenClass().getQualifiedInterfaceName());
    stringBuffer.append(TEXT_267);
    stringBuffer.append(reverseGenFeature.getGetAccessor());
    stringBuffer.append(TEXT_268);
    stringBuffer.append(reverseGenFeature.getFormattedName());
    stringBuffer.append(TEXT_269);
    }
    }
    stringBuffer.append(TEXT_270);
    if (!genFeature.hasDocumentation()) {
    stringBuffer.append(TEXT_271);
    stringBuffer.append(genFeature.getFormattedName());
    stringBuffer.append(TEXT_272);
    stringBuffer.append(genFeature.getFeatureKind());
    stringBuffer.append(TEXT_273);
    }
    stringBuffer.append(TEXT_274);
    if (genFeature.hasDocumentation()) {
    stringBuffer.append(TEXT_275);
    stringBuffer.append(genFeature.getDocumentation(genModel.getIndentation(stringBuffer)));
    stringBuffer.append(TEXT_276);
    }
    stringBuffer.append(TEXT_277);
    stringBuffer.append(genFeature.getFormattedName());
    stringBuffer.append(TEXT_278);
    stringBuffer.append(genFeature.getFeatureKind());
    stringBuffer.append(TEXT_279);
    if (genFeature.getTypeGenEnum() != null) {
    stringBuffer.append(TEXT_280);
    stringBuffer.append(genFeature.getTypeGenEnum().getQualifiedName());
    }
    if (genFeature.isUnsettable()) {
    if (!genFeature.isSuppressedIsSetVisibility()) {
    stringBuffer.append(TEXT_281);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_282);
    }
    if (genFeature.isChangeable() && !genFeature.isSuppressedUnsetVisibility()) {
    stringBuffer.append(TEXT_283);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_284);
    }
    }
    if (genFeature.isChangeable() && !genFeature.isListType() && !genFeature.isSuppressedSetVisibility()) {
    stringBuffer.append(TEXT_285);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_286);
    stringBuffer.append(genFeature.getRawImportedBoundType());
    stringBuffer.append(TEXT_287);
    }
    if (!genModel.isSuppressEMFMetaData()) {
    stringBuffer.append(TEXT_288);
    stringBuffer.append(genPackage.getQualifiedPackageInterfaceName());
    stringBuffer.append(TEXT_289);
    stringBuffer.append(genFeature.getFeatureAccessorName());
    stringBuffer.append(TEXT_290);
    }
    if (genFeature.isBidirectional() && !genFeature.getReverse().getGenClass().isMapEntry()) { GenFeature reverseGenFeature = genFeature.getReverse(); 
    if (!reverseGenFeature.isSuppressedGetVisibility()) {
    stringBuffer.append(TEXT_291);
    stringBuffer.append(reverseGenFeature.getGenClass().getQualifiedInterfaceName());
    stringBuffer.append(TEXT_292);
    stringBuffer.append(reverseGenFeature.getGetAccessor());
    }
    }
    if (!genModel.isSuppressEMFModelTags()) { boolean first = true; for (StringTokenizer stringTokenizer = new StringTokenizer(genFeature.getModelInfo(), "\n\r"); stringTokenizer.hasMoreTokens(); ) { String modelInfo = stringTokenizer.nextToken(); if (first) { first = false;
    stringBuffer.append(TEXT_293);
    stringBuffer.append(modelInfo);
    } else {
    stringBuffer.append(TEXT_294);
    stringBuffer.append(modelInfo);
    }} if (first) {
    stringBuffer.append(TEXT_295);
    }}
    stringBuffer.append(TEXT_296);
    //Class/getGenFeature.javadoc.override.javajetinc
    } else {
    stringBuffer.append(TEXT_297);
    if (isJDK50) { //Class/getGenFeature.annotations.insert.javajetinc
    }
    }
    if (!isImplementation) {
    stringBuffer.append(TEXT_298);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_299);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_300);
    } else {
    if (genModel.useGenerics() && ((genFeature.isContainer() || genFeature.isResolveProxies()) && !genFeature.isListType() && !(genModel.isReflectiveDelegation() && genModel.isDynamicDelegation()) && genFeature.isUncheckedCast(genClass) || genFeature.isListType() && !genFeature.isFeatureMapType() && (genModel.isReflectiveDelegation() || genModel.isVirtualDelegation() || genModel.isDynamicDelegation()) || genFeature.isListDataType() && genFeature.hasDelegateFeature())) {
    stringBuffer.append(TEXT_301);
    }
    stringBuffer.append(TEXT_302);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_303);
    stringBuffer.append(genFeature.getGetAccessor());
    if (genClass.hasCollidingGetAccessorOperation(genFeature)) {
    stringBuffer.append(TEXT_304);
    }
    stringBuffer.append(TEXT_305);
    if (genModel.isDynamicDelegation()) {
    stringBuffer.append(TEXT_306);
    if (!isJDK50 && genFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_307);
    }
    stringBuffer.append(TEXT_308);
    stringBuffer.append(genFeature.getObjectType(genClass));
    stringBuffer.append(TEXT_309);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(TEXT_310);
    stringBuffer.append(genFeature.getQualifiedFeatureAccessor());
    stringBuffer.append(TEXT_311);
    stringBuffer.append(!genFeature.isEffectiveSuppressEMFTypes());
    stringBuffer.append(TEXT_312);
    if (!isJDK50 && genFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_313);
    stringBuffer.append(genFeature.getPrimitiveValueFunction());
    stringBuffer.append(TEXT_314);
    }
    stringBuffer.append(TEXT_315);
    } else if (genModel.isReflectiveDelegation()) {
    stringBuffer.append(TEXT_316);
    if (!isJDK50 && genFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_317);
    }
    stringBuffer.append(TEXT_318);
    stringBuffer.append(genFeature.getObjectType(genClass));
    stringBuffer.append(TEXT_319);
    stringBuffer.append(genFeature.getQualifiedFeatureAccessor());
    stringBuffer.append(TEXT_320);
    if (!isJDK50 && genFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_321);
    stringBuffer.append(genFeature.getPrimitiveValueFunction());
    stringBuffer.append(TEXT_322);
    }
    stringBuffer.append(TEXT_323);
    } else if (!genFeature.isVolatile()) {
    if (genFeature.isListType()) {
    if (genModel.isVirtualDelegation()) {
    stringBuffer.append(TEXT_324);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_325);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_326);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_327);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(positiveOffsetCorrection);
    stringBuffer.append(TEXT_328);
    }
    stringBuffer.append(TEXT_329);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_330);
    if (genModel.isVirtualDelegation()) {
    stringBuffer.append(TEXT_331);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(positiveOffsetCorrection);
    stringBuffer.append(TEXT_332);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_333);
    stringBuffer.append(genClass.getListConstructor(genFeature));
    stringBuffer.append(TEXT_334);
    } else {
    stringBuffer.append(TEXT_335);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_336);
    stringBuffer.append(genClass.getListConstructor(genFeature));
    stringBuffer.append(TEXT_337);
    }
    stringBuffer.append(TEXT_338);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(genFeature.isMapType() && genFeature.isEffectiveSuppressEMFTypes() ? ".map()" : "");
    stringBuffer.append(TEXT_339);
    } else if (genFeature.isContainer()) {
    stringBuffer.append(TEXT_340);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(positiveOffsetCorrection);
    stringBuffer.append(TEXT_341);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_342);
    } else {
    if (genFeature.isResolveProxies()) {
    if (genModel.isVirtualDelegation()) {
    stringBuffer.append(TEXT_343);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_344);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_345);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_346);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(positiveOffsetCorrection);
    if (genFeature.hasEDefault()) {
    stringBuffer.append(TEXT_347);
    stringBuffer.append(genFeature.getEDefault());
    }
    stringBuffer.append(TEXT_348);
    }
    stringBuffer.append(TEXT_349);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_350);
    stringBuffer.append(genFeature.getSafeNameAsEObject());
    stringBuffer.append(TEXT_351);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.InternalEObject"));
    stringBuffer.append(TEXT_352);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_353);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.InternalEObject"));
    stringBuffer.append(TEXT_354);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_355);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_356);
    stringBuffer.append(genFeature.getNonEObjectInternalTypeCast(genClass));
    stringBuffer.append(TEXT_357);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_358);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_359);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_360);
    if (genFeature.isEffectiveContains()) {
    stringBuffer.append(TEXT_361);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.InternalEObject"));
    stringBuffer.append(TEXT_362);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_363);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.InternalEObject"));
    stringBuffer.append(TEXT_364);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_365);
    if (!genFeature.isBidirectional()) {
    stringBuffer.append(TEXT_366);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.notify.NotificationChain"));
    stringBuffer.append(TEXT_367);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_368);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(negativeOffsetCorrection);
    stringBuffer.append(TEXT_369);
    } else { GenFeature reverseFeature = genFeature.getReverse(); GenClass targetClass = reverseFeature.getGenClass(); String reverseOffsetCorrection = targetClass.hasOffsetCorrection() ? " + " + genClass.getOffsetCorrectionField(reverseFeature) : "";
    stringBuffer.append(TEXT_370);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.notify.NotificationChain"));
    stringBuffer.append(TEXT_371);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_372);
    stringBuffer.append(targetClass.getQualifiedFeatureID(reverseFeature));
    stringBuffer.append(reverseOffsetCorrection);
    stringBuffer.append(TEXT_373);
    stringBuffer.append(targetClass.getRawImportedInterfaceName());
    stringBuffer.append(TEXT_374);
    }
    stringBuffer.append(TEXT_375);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_376);
    if (!genFeature.isBidirectional()) {
    stringBuffer.append(TEXT_377);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_378);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(negativeOffsetCorrection);
    stringBuffer.append(TEXT_379);
    } else { GenFeature reverseFeature = genFeature.getReverse(); GenClass targetClass = reverseFeature.getGenClass(); String reverseOffsetCorrection = targetClass.hasOffsetCorrection() ? " + " + genClass.getOffsetCorrectionField(reverseFeature) : "";
    stringBuffer.append(TEXT_380);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_381);
    stringBuffer.append(targetClass.getQualifiedFeatureID(reverseFeature));
    stringBuffer.append(reverseOffsetCorrection);
    stringBuffer.append(TEXT_382);
    stringBuffer.append(targetClass.getRawImportedInterfaceName());
    stringBuffer.append(TEXT_383);
    }
    stringBuffer.append(TEXT_384);
    } else if (genModel.isVirtualDelegation()) {
    stringBuffer.append(TEXT_385);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(positiveOffsetCorrection);
    stringBuffer.append(TEXT_386);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_387);
    }
    if (!genModel.isSuppressNotification()) {
    stringBuffer.append(TEXT_388);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.impl.ENotificationImpl"));
    stringBuffer.append(TEXT_389);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.notify.Notification"));
    stringBuffer.append(TEXT_390);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(positiveOffsetCorrection);
    stringBuffer.append(TEXT_391);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_392);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_393);
    }
    stringBuffer.append(TEXT_394);
    }
    if (!genFeature.isResolveProxies() && genModel.isVirtualDelegation() && !genFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_395);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_396);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(positiveOffsetCorrection);
    if (genFeature.hasEDefault()) {
    stringBuffer.append(TEXT_397);
    stringBuffer.append(genFeature.getEDefault());
    }
    stringBuffer.append(TEXT_398);
    } else if (genClass.isFlag(genFeature)) {
    if (genFeature.isBooleanType()) {
    stringBuffer.append(TEXT_399);
    stringBuffer.append(genClass.getFlagsField(genFeature));
    stringBuffer.append(TEXT_400);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_401);
    } else {
    stringBuffer.append(TEXT_402);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_403);
    stringBuffer.append(genClass.getFlagsField(genFeature));
    stringBuffer.append(TEXT_404);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_405);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_406);
    }
    } else {
    stringBuffer.append(TEXT_407);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_408);
    }
    }
    } else {//volatile
    if (genFeature.isResolveProxies() && !genFeature.isListType()) {
    stringBuffer.append(TEXT_409);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_410);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_411);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_412);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_413);
    stringBuffer.append(genFeature.getSafeNameAsEObject());
    stringBuffer.append(TEXT_414);
    stringBuffer.append(genFeature.getNonEObjectInternalTypeCast(genClass));
    stringBuffer.append(TEXT_415);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.InternalEObject"));
    stringBuffer.append(TEXT_416);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_417);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_418);
    } else if (genFeature.hasDelegateFeature()) { GenFeature delegateFeature = genFeature.getDelegateFeature();
    if (genFeature.isFeatureMapType()) {
    String featureMapEntryTemplateArgument = isJDK50 ? "<" + genModel.getImportedName("org.eclipse.emf.ecore.util.FeatureMap") + ".Entry>" : "";
    if (delegateFeature.isWrappedFeatureMapType()) {
    stringBuffer.append(TEXT_419);
    stringBuffer.append(genFeature.getImportedEffectiveFeatureMapWrapperClass());
    stringBuffer.append(TEXT_420);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.FeatureMap"));
    stringBuffer.append(TEXT_421);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.FeatureMap"));
    stringBuffer.append(TEXT_422);
    stringBuffer.append(delegateFeature.getAccessorName());
    stringBuffer.append(TEXT_423);
    stringBuffer.append(featureMapEntryTemplateArgument);
    stringBuffer.append(TEXT_424);
    stringBuffer.append(genFeature.getQualifiedFeatureAccessor());
    stringBuffer.append(TEXT_425);
    } else {
    stringBuffer.append(TEXT_426);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.FeatureMap"));
    stringBuffer.append(TEXT_427);
    stringBuffer.append(delegateFeature.getAccessorName());
    stringBuffer.append(TEXT_428);
    stringBuffer.append(featureMapEntryTemplateArgument);
    stringBuffer.append(TEXT_429);
    stringBuffer.append(genFeature.getQualifiedFeatureAccessor());
    stringBuffer.append(TEXT_430);
    }
    } else if (genFeature.isListType()) {
    if (delegateFeature.isWrappedFeatureMapType()) {
    stringBuffer.append(TEXT_431);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.FeatureMap"));
    stringBuffer.append(TEXT_432);
    stringBuffer.append(delegateFeature.getAccessorName());
    stringBuffer.append(TEXT_433);
    stringBuffer.append(genFeature.getQualifiedFeatureAccessor());
    stringBuffer.append(TEXT_434);
    } else {
    stringBuffer.append(TEXT_435);
    stringBuffer.append(delegateFeature.getAccessorName());
    stringBuffer.append(TEXT_436);
    stringBuffer.append(genFeature.getQualifiedFeatureAccessor());
    stringBuffer.append(TEXT_437);
    }
    } else {
    if (delegateFeature.isWrappedFeatureMapType()) {
    stringBuffer.append(TEXT_438);
    if (!isJDK50 && genFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_439);
    }
    if (genFeature.getTypeGenDataType() == null || !genFeature.getTypeGenDataType().isObjectType()) {
    stringBuffer.append(TEXT_440);
    stringBuffer.append(genFeature.getObjectType(genClass));
    stringBuffer.append(TEXT_441);
    }
    stringBuffer.append(TEXT_442);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.FeatureMap"));
    stringBuffer.append(TEXT_443);
    stringBuffer.append(delegateFeature.getAccessorName());
    stringBuffer.append(TEXT_444);
    stringBuffer.append(genFeature.getQualifiedFeatureAccessor());
    stringBuffer.append(TEXT_445);
    if (!isJDK50 && genFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_446);
    stringBuffer.append(genFeature.getPrimitiveValueFunction());
    stringBuffer.append(TEXT_447);
    }
    stringBuffer.append(TEXT_448);
    } else {
    stringBuffer.append(TEXT_449);
    if (!isJDK50 && genFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_450);
    }
    if (genFeature.getTypeGenDataType() == null || !genFeature.getTypeGenDataType().isObjectType()) {
    stringBuffer.append(TEXT_451);
    stringBuffer.append(genFeature.getObjectType(genClass));
    stringBuffer.append(TEXT_452);
    }
    stringBuffer.append(TEXT_453);
    stringBuffer.append(delegateFeature.getAccessorName());
    stringBuffer.append(TEXT_454);
    stringBuffer.append(genFeature.getQualifiedFeatureAccessor());
    stringBuffer.append(TEXT_455);
    if (!isJDK50 && genFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_456);
    stringBuffer.append(genFeature.getPrimitiveValueFunction());
    stringBuffer.append(TEXT_457);
    }
    stringBuffer.append(TEXT_458);
    }
    }
    } else if (genClass.getGetAccessorOperation(genFeature) != null) {
    stringBuffer.append(TEXT_459);
    stringBuffer.append(genClass.getGetAccessorOperation(genFeature).getBody(genModel.getIndentation(stringBuffer)));
    } else {
    stringBuffer.append(TEXT_460);
    stringBuffer.append(genFeature.getFormattedName());
    stringBuffer.append(TEXT_461);
    stringBuffer.append(genFeature.getFeatureKind());
    stringBuffer.append(TEXT_462);
    if (genFeature.isListType()) {
    stringBuffer.append(TEXT_463);
    if (genFeature.isMapType()) {
    stringBuffer.append(TEXT_464);
    } else if (genFeature.isFeatureMapType()) {
    stringBuffer.append(TEXT_465);
    } else {
    stringBuffer.append(TEXT_466);
    }
    stringBuffer.append(TEXT_467);
    }
    stringBuffer.append(TEXT_468);
    //Class/getGenFeature.todo.override.javajetinc
    }
    }
    stringBuffer.append(TEXT_469);
    }
    //Class/getGenFeature.override.javajetinc
    }
    if (isImplementation && !genModel.isReflectiveDelegation() && genFeature.isBasicGet()) {
    stringBuffer.append(TEXT_470);
    if (isJDK50) { //Class/basicGetGenFeature.annotations.insert.javajetinc
    }
    stringBuffer.append(TEXT_471);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_472);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_473);
    if (genModel.isDynamicDelegation()) {
    stringBuffer.append(TEXT_474);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_475);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(TEXT_476);
    stringBuffer.append(genFeature.getQualifiedFeatureAccessor());
    stringBuffer.append(TEXT_477);
    stringBuffer.append(!genFeature.isEffectiveSuppressEMFTypes());
    stringBuffer.append(TEXT_478);
    } else if (genFeature.isContainer()) {
    stringBuffer.append(TEXT_479);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(positiveOffsetCorrection);
    stringBuffer.append(TEXT_480);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_481);
    } else if (!genFeature.isVolatile()) {
    if (genModel.isVirtualDelegation()) {
    stringBuffer.append(TEXT_482);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_483);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(positiveOffsetCorrection);
    stringBuffer.append(TEXT_484);
    } else {
    stringBuffer.append(TEXT_485);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_486);
    }
    } else if (genFeature.hasDelegateFeature()) { GenFeature delegateFeature = genFeature.getDelegateFeature();
    if (delegateFeature.isWrappedFeatureMapType()) {
    stringBuffer.append(TEXT_487);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_488);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.FeatureMap"));
    stringBuffer.append(TEXT_489);
    stringBuffer.append(delegateFeature.getAccessorName());
    stringBuffer.append(TEXT_490);
    stringBuffer.append(genFeature.getQualifiedFeatureAccessor());
    stringBuffer.append(TEXT_491);
    } else {
    stringBuffer.append(TEXT_492);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_493);
    stringBuffer.append(delegateFeature.getAccessorName());
    stringBuffer.append(TEXT_494);
    stringBuffer.append(genFeature.getQualifiedFeatureAccessor());
    stringBuffer.append(TEXT_495);
    }
    } else {
    stringBuffer.append(TEXT_496);
    stringBuffer.append(genFeature.getFormattedName());
    stringBuffer.append(TEXT_497);
    stringBuffer.append(genFeature.getFeatureKind());
    stringBuffer.append(TEXT_498);
    //Class/basicGetGenFeature.todo.override.javajetinc
    }
    stringBuffer.append(TEXT_499);
    //Class/basicGetGenFeature.override.javajetinc
    }
    if (isImplementation && !genModel.isReflectiveDelegation() && genFeature.isBasicSet()) {
    stringBuffer.append(TEXT_500);
    if (isJDK50) { //Class/basicSetGenFeature.annotations.insert.javajetinc
    }
    stringBuffer.append(TEXT_501);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.notify.NotificationChain"));
    stringBuffer.append(TEXT_502);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_503);
    stringBuffer.append(genFeature.getImportedInternalType(genClass));
    stringBuffer.append(TEXT_504);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_505);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.notify.NotificationChain"));
    stringBuffer.append(TEXT_506);
    if (genFeature.isContainer()) {
    stringBuffer.append(TEXT_507);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.InternalEObject"));
    stringBuffer.append(TEXT_508);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_509);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(positiveOffsetCorrection);
    stringBuffer.append(TEXT_510);
    stringBuffer.append(TEXT_511);
    } else if (genModel.isDynamicDelegation()) {
    stringBuffer.append(TEXT_512);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.InternalEObject"));
    stringBuffer.append(TEXT_513);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_514);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(positiveOffsetCorrection);
    stringBuffer.append(TEXT_515);
    stringBuffer.append(TEXT_516);
    } else if (!genFeature.isVolatile()) {
    if (genModel.isVirtualDelegation()) {
    stringBuffer.append(TEXT_517);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_518);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(positiveOffsetCorrection);
    stringBuffer.append(TEXT_519);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_520);
    } else {
    stringBuffer.append(TEXT_521);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_522);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_523);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_524);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_525);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_526);
    }
    if (genFeature.isUnsettable()) {
    if (genModel.isVirtualDelegation()) {
    if (!genModel.isSuppressNotification()) {
    stringBuffer.append(TEXT_527);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_528);
    }
    } else if (genClass.isESetFlag(genFeature)) {
    stringBuffer.append(TEXT_529);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_530);
    stringBuffer.append(genClass.getESetFlagsField(genFeature));
    stringBuffer.append(TEXT_531);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_532);
    if (!genModel.isSuppressNotification()) {
    stringBuffer.append(TEXT_533);
    stringBuffer.append(genClass.getESetFlagsField(genFeature));
    stringBuffer.append(TEXT_534);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_535);
    }
    } else {
    if (!genModel.isSuppressNotification()) {
    stringBuffer.append(TEXT_536);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_537);
    stringBuffer.append(genFeature.getUncapName());
    stringBuffer.append(TEXT_538);
    }
    stringBuffer.append(TEXT_539);
    stringBuffer.append(genFeature.getUncapName());
    stringBuffer.append(TEXT_540);
    }
    }
    if (!genModel.isSuppressNotification()) {
    stringBuffer.append(TEXT_541);
    if (genFeature.isUnsettable()) {
    stringBuffer.append(TEXT_542);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.impl.ENotificationImpl"));
    stringBuffer.append(TEXT_543);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.impl.ENotificationImpl"));
    stringBuffer.append(TEXT_544);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.notify.Notification"));
    stringBuffer.append(TEXT_545);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(positiveOffsetCorrection);
    stringBuffer.append(TEXT_546);
    if (genModel.isVirtualDelegation()) {
    stringBuffer.append(TEXT_547);
    stringBuffer.append(genFeature.getCapName());
    } else {
    stringBuffer.append(TEXT_548);
    stringBuffer.append(genFeature.getCapName());
    }
    stringBuffer.append(TEXT_549);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_550);
    if (genModel.isVirtualDelegation()) {
    stringBuffer.append(TEXT_551);
    } else {
    stringBuffer.append(TEXT_552);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_553);
    }
    stringBuffer.append(TEXT_554);
    } else {
    stringBuffer.append(TEXT_555);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.impl.ENotificationImpl"));
    stringBuffer.append(TEXT_556);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.impl.ENotificationImpl"));
    stringBuffer.append(TEXT_557);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.notify.Notification"));
    stringBuffer.append(TEXT_558);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(positiveOffsetCorrection);
    stringBuffer.append(TEXT_559);
    if (genModel.isVirtualDelegation()) {
    stringBuffer.append(TEXT_560);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_561);
    stringBuffer.append(genFeature.getCapName());
    } else {
    stringBuffer.append(TEXT_562);
    stringBuffer.append(genFeature.getCapName());
    }
    stringBuffer.append(TEXT_563);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_564);
    }
    stringBuffer.append(TEXT_565);
    }
    stringBuffer.append(TEXT_566);
    } else if (genFeature.hasDelegateFeature()) { GenFeature delegateFeature = genFeature.getDelegateFeature();
    if (delegateFeature.isWrappedFeatureMapType()) {
    stringBuffer.append(TEXT_567);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.FeatureMap"));
    stringBuffer.append(TEXT_568);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.FeatureMap"));
    stringBuffer.append(TEXT_569);
    stringBuffer.append(delegateFeature.getAccessorName());
    stringBuffer.append(TEXT_570);
    stringBuffer.append(genFeature.getQualifiedFeatureAccessor());
    stringBuffer.append(TEXT_571);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_572);
    } else {
    stringBuffer.append(TEXT_573);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.FeatureMap"));
    stringBuffer.append(TEXT_574);
    stringBuffer.append(delegateFeature.getAccessorName());
    stringBuffer.append(TEXT_575);
    stringBuffer.append(genFeature.getQualifiedFeatureAccessor());
    stringBuffer.append(TEXT_576);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_577);
    }
    } else {
    stringBuffer.append(TEXT_578);
    stringBuffer.append(genFeature.getFormattedName());
    stringBuffer.append(TEXT_579);
    stringBuffer.append(genFeature.getFeatureKind());
    stringBuffer.append(TEXT_580);
    //Class/basicSetGenFeature.todo.override.javajetinc
    }
    stringBuffer.append(TEXT_581);
    //Class/basicSetGenFeature.override.javajetinc
    }
    if (genFeature.isSet() && (isImplementation || !genFeature.isSuppressedSetVisibility())) {
    if (isInterface) { 
    stringBuffer.append(TEXT_582);
    stringBuffer.append(genClass.getQualifiedInterfaceName());
    stringBuffer.append(TEXT_583);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_584);
    stringBuffer.append(genFeature.getFormattedName());
    stringBuffer.append(TEXT_585);
    stringBuffer.append(genFeature.getFeatureKind());
    stringBuffer.append(TEXT_586);
    stringBuffer.append(TEXT_587);
    stringBuffer.append(genFeature.getFormattedName());
    stringBuffer.append(TEXT_588);
    stringBuffer.append(genFeature.getFeatureKind());
    stringBuffer.append(TEXT_589);
    if (genFeature.isEnumType()) {
    stringBuffer.append(TEXT_590);
    stringBuffer.append(genFeature.getTypeGenEnum().getQualifiedName());
    }
    if (genFeature.isUnsettable()) {
    if (!genFeature.isSuppressedIsSetVisibility()) {
    stringBuffer.append(TEXT_591);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_592);
    }
    if (!genFeature.isSuppressedUnsetVisibility()) {
    stringBuffer.append(TEXT_593);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_594);
    }
    }
    stringBuffer.append(TEXT_595);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_596);
    //Class/setGenFeature.javadoc.override.javajetinc
    } else {
    stringBuffer.append(TEXT_597);
    if (isJDK50) { //Class/setGenFeature.annotations.insert.javajetinc
    }
    }
    if (!isImplementation) { 
    stringBuffer.append(TEXT_598);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_599);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_600);
    } else { GenOperation setAccessorOperation = genClass.getSetAccessorOperation(genFeature);
    stringBuffer.append(TEXT_601);
    stringBuffer.append(genFeature.getAccessorName());
    if (genClass.hasCollidingSetAccessorOperation(genFeature)) {
    stringBuffer.append(TEXT_602);
    }
    stringBuffer.append(TEXT_603);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_604);
    stringBuffer.append(setAccessorOperation == null ? "new" + genFeature.getCapName() : setAccessorOperation.getGenParameters().get(0).getName());
    stringBuffer.append(TEXT_605);
    if (genModel.isDynamicDelegation()) {
    stringBuffer.append(TEXT_606);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(TEXT_607);
    stringBuffer.append(genFeature.getQualifiedFeatureAccessor());
    stringBuffer.append(TEXT_608);
    if (!isJDK50 && genFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_609);
    stringBuffer.append(genFeature.getObjectType(genClass));
    stringBuffer.append(TEXT_610);
    }
    stringBuffer.append(TEXT_611);
    stringBuffer.append(genFeature.getCapName());
    if (!isJDK50 && genFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_612);
    }
    stringBuffer.append(TEXT_613);
    } else if (genModel.isReflectiveDelegation()) {
    stringBuffer.append(TEXT_614);
    stringBuffer.append(genFeature.getQualifiedFeatureAccessor());
    stringBuffer.append(TEXT_615);
    if (!isJDK50 && genFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_616);
    stringBuffer.append(genFeature.getObjectType(genClass));
    stringBuffer.append(TEXT_617);
    }
    stringBuffer.append(TEXT_618);
    stringBuffer.append(genFeature.getCapName());
    if (!isJDK50 && genFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_619);
    }
    stringBuffer.append(TEXT_620);
    } else if (!genFeature.isVolatile()) {
    if (genFeature.isContainer()) { GenFeature reverseFeature = genFeature.getReverse(); GenClass targetClass = reverseFeature.getGenClass(); String reverseOffsetCorrection = targetClass.hasOffsetCorrection() ? " + " + genClass.getOffsetCorrectionField(reverseFeature) : "";
    stringBuffer.append(TEXT_621);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_622);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(positiveOffsetCorrection);
    stringBuffer.append(TEXT_623);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_624);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.EcoreUtil"));
    stringBuffer.append(TEXT_625);
    stringBuffer.append(genFeature.getEObjectCast());
    stringBuffer.append(TEXT_626);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_627);
    stringBuffer.append(genModel.getImportedName("java.lang.IllegalArgumentException"));
    stringBuffer.append(TEXT_628);
    stringBuffer.append(genModel.getNonNLS());
    stringBuffer.append(TEXT_629);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.notify.NotificationChain"));
    stringBuffer.append(TEXT_630);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_631);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.InternalEObject"));
    stringBuffer.append(TEXT_632);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_633);
    stringBuffer.append(targetClass.getQualifiedFeatureID(reverseFeature));
    stringBuffer.append(reverseOffsetCorrection);
    stringBuffer.append(TEXT_634);
    stringBuffer.append(targetClass.getRawImportedInterfaceName());
    stringBuffer.append(TEXT_635);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_636);
    stringBuffer.append(genFeature.getInternalTypeCast());
    stringBuffer.append(TEXT_637);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_638);
    if (!genModel.isSuppressNotification()) {
    stringBuffer.append(TEXT_639);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.impl.ENotificationImpl"));
    stringBuffer.append(TEXT_640);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.notify.Notification"));
    stringBuffer.append(TEXT_641);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(positiveOffsetCorrection);
    stringBuffer.append(TEXT_642);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_643);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_644);
    }
    } else if (genFeature.isBidirectional() || genFeature.isEffectiveContains()) {
    if (genModel.isVirtualDelegation()) {
    stringBuffer.append(TEXT_645);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_646);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_647);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_648);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(positiveOffsetCorrection);
    stringBuffer.append(TEXT_649);
    }
    stringBuffer.append(TEXT_650);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_651);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_652);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.notify.NotificationChain"));
    stringBuffer.append(TEXT_653);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_654);
    if (!genFeature.isBidirectional()) {
    stringBuffer.append(TEXT_655);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.InternalEObject"));
    stringBuffer.append(TEXT_656);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_657);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(negativeOffsetCorrection);
    stringBuffer.append(TEXT_658);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_659);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.InternalEObject"));
    stringBuffer.append(TEXT_660);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_661);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(negativeOffsetCorrection);
    stringBuffer.append(TEXT_662);
    } else { GenFeature reverseFeature = genFeature.getReverse(); GenClass targetClass = reverseFeature.getGenClass(); String reverseOffsetCorrection = targetClass.hasOffsetCorrection() ? " + " + genClass.getOffsetCorrectionField(reverseFeature) : "";
    stringBuffer.append(TEXT_663);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.InternalEObject"));
    stringBuffer.append(TEXT_664);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_665);
    stringBuffer.append(targetClass.getQualifiedFeatureID(reverseFeature));
    stringBuffer.append(reverseOffsetCorrection);
    stringBuffer.append(TEXT_666);
    stringBuffer.append(targetClass.getRawImportedInterfaceName());
    stringBuffer.append(TEXT_667);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_668);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.InternalEObject"));
    stringBuffer.append(TEXT_669);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_670);
    stringBuffer.append(targetClass.getQualifiedFeatureID(reverseFeature));
    stringBuffer.append(reverseOffsetCorrection);
    stringBuffer.append(TEXT_671);
    stringBuffer.append(targetClass.getRawImportedInterfaceName());
    stringBuffer.append(TEXT_672);
    }
    stringBuffer.append(TEXT_673);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_674);
    stringBuffer.append(genFeature.getInternalTypeCast());
    stringBuffer.append(TEXT_675);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_676);
    if (genFeature.isUnsettable()) {
    stringBuffer.append(TEXT_677);
    if (genModel.isVirtualDelegation()) {
    stringBuffer.append(TEXT_678);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_679);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(positiveOffsetCorrection);
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
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_697);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_698);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_699);
    }
    stringBuffer.append(TEXT_700);
    } else {
    if (!genModel.isSuppressNotification()) {
    stringBuffer.append(TEXT_701);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.impl.ENotificationImpl"));
    stringBuffer.append(TEXT_702);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.notify.Notification"));
    stringBuffer.append(TEXT_703);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(positiveOffsetCorrection);
    stringBuffer.append(TEXT_704);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_705);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_706);
    }
    }
    } else {
    if (genClass.isFlag(genFeature)) {
    if (!genModel.isSuppressNotification()) {
    if (genFeature.isBooleanType()) {
    stringBuffer.append(TEXT_707);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_708);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_709);
    stringBuffer.append(genClass.getFlagsField(genFeature));
    stringBuffer.append(TEXT_710);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_711);
    } else {
    stringBuffer.append(TEXT_712);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_713);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_714);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_715);
    stringBuffer.append(genClass.getFlagsField(genFeature));
    stringBuffer.append(TEXT_716);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_717);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_718);
    }
    }
    if (genFeature.isBooleanType()) {
    stringBuffer.append(TEXT_719);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_720);
    stringBuffer.append(genClass.getFlagsField(genFeature));
    stringBuffer.append(TEXT_721);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_722);
    stringBuffer.append(genClass.getFlagsField(genFeature));
    stringBuffer.append(TEXT_723);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_724);
    } else {
    stringBuffer.append(TEXT_725);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_726);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_727);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_728);
    stringBuffer.append(genClass.getFlagsField(genFeature));
    stringBuffer.append(TEXT_729);
    stringBuffer.append(genClass.getFlagsField(genFeature));
    stringBuffer.append(TEXT_730);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_731);
    if (isJDK50) {
    stringBuffer.append(TEXT_732);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_733);
    } else {
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_734);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_735);
    }
    stringBuffer.append(TEXT_736);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_737);
    }
    } else {
    if (!genModel.isVirtualDelegation() || genFeature.isPrimitiveType()) {
    if (!genModel.isSuppressNotification()) {
    stringBuffer.append(TEXT_738);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_739);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_740);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_741);
    }
    }
    if (genFeature.isEnumType()) {
    if (genModel.isVirtualDelegation()) {
    stringBuffer.append(TEXT_742);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_743);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_744);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_745);
    stringBuffer.append(genFeature.getEDefault());
    stringBuffer.append(TEXT_746);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_747);
    } else {
    stringBuffer.append(TEXT_748);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_749);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_750);
    stringBuffer.append(genFeature.getEDefault());
    stringBuffer.append(TEXT_751);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_752);
    }
    } else {
    if (genModel.isVirtualDelegation() && !genFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_753);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_754);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_755);
    stringBuffer.append(genFeature.getInternalTypeCast());
    stringBuffer.append(TEXT_756);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_757);
    } else {
    stringBuffer.append(TEXT_758);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_759);
    stringBuffer.append(genFeature.getInternalTypeCast());
    stringBuffer.append(TEXT_760);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_761);
    }
    }
    if (genModel.isVirtualDelegation() && !genFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_762);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_763);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(positiveOffsetCorrection);
    stringBuffer.append(TEXT_764);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_765);
    }
    }
    if (genFeature.isUnsettable()) {
    if (genModel.isVirtualDelegation() && !genFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_766);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_767);
    } else if (genClass.isESetFlag(genFeature)) {
    if (!genModel.isSuppressNotification()) {
    stringBuffer.append(TEXT_768);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_769);
    stringBuffer.append(genClass.getESetFlagsField(genFeature));
    stringBuffer.append(TEXT_770);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_771);
    }
    stringBuffer.append(TEXT_772);
    stringBuffer.append(genClass.getESetFlagsField(genFeature));
    stringBuffer.append(TEXT_773);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_774);
    } else {
    if (!genModel.isSuppressNotification()) {
    stringBuffer.append(TEXT_775);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_776);
    stringBuffer.append(genFeature.getUncapName());
    stringBuffer.append(TEXT_777);
    }
    stringBuffer.append(TEXT_778);
    stringBuffer.append(genFeature.getUncapName());
    stringBuffer.append(TEXT_779);
    }
    if (!genModel.isSuppressNotification()) {
    stringBuffer.append(TEXT_780);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.impl.ENotificationImpl"));
    stringBuffer.append(TEXT_781);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.notify.Notification"));
    stringBuffer.append(TEXT_782);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(positiveOffsetCorrection);
    stringBuffer.append(TEXT_783);
    if (genModel.isVirtualDelegation() && !genFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_784);
    stringBuffer.append(genFeature.getEDefault());
    stringBuffer.append(TEXT_785);
    stringBuffer.append(genFeature.getCapName());
    } else {
    stringBuffer.append(TEXT_786);
    stringBuffer.append(genFeature.getCapName());
    }
    stringBuffer.append(TEXT_787);
    if (genClass.isFlag(genFeature)) {
    stringBuffer.append(TEXT_788);
    stringBuffer.append(genFeature.getCapName());
    } else {
    stringBuffer.append(genFeature.getSafeName());
    }
    stringBuffer.append(TEXT_789);
    if (genModel.isVirtualDelegation() && !genFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_790);
    } else {
    stringBuffer.append(TEXT_791);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_792);
    }
    stringBuffer.append(TEXT_793);
    }
    } else {
    if (!genModel.isSuppressNotification()) {
    stringBuffer.append(TEXT_794);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.impl.ENotificationImpl"));
    stringBuffer.append(TEXT_795);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.notify.Notification"));
    stringBuffer.append(TEXT_796);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(positiveOffsetCorrection);
    stringBuffer.append(TEXT_797);
    if (genModel.isVirtualDelegation() && !genFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_798);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_799);
    stringBuffer.append(genFeature.getEDefault());
    stringBuffer.append(TEXT_800);
    stringBuffer.append(genFeature.getCapName());
    } else {
    stringBuffer.append(TEXT_801);
    stringBuffer.append(genFeature.getCapName());
    }
    stringBuffer.append(TEXT_802);
    if (genClass.isFlag(genFeature)) {
    stringBuffer.append(TEXT_803);
    stringBuffer.append(genFeature.getCapName());
    } else {
    stringBuffer.append(genFeature.getSafeName());
    }
    stringBuffer.append(TEXT_804);
    }
    }
    }
    } else if (genFeature.hasDelegateFeature()) { GenFeature delegateFeature = genFeature.getDelegateFeature();
    if (delegateFeature.isWrappedFeatureMapType()) {
    stringBuffer.append(TEXT_805);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.FeatureMap"));
    stringBuffer.append(TEXT_806);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.FeatureMap"));
    stringBuffer.append(TEXT_807);
    stringBuffer.append(delegateFeature.getAccessorName());
    stringBuffer.append(TEXT_808);
    stringBuffer.append(genFeature.getQualifiedFeatureAccessor());
    stringBuffer.append(TEXT_809);
    if (!isJDK50 && genFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_810);
    stringBuffer.append(genFeature.getObjectType(genClass));
    stringBuffer.append(TEXT_811);
    }
    stringBuffer.append(TEXT_812);
    stringBuffer.append(genFeature.getCapName());
    if (!isJDK50 && genFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_813);
    }
    stringBuffer.append(TEXT_814);
    } else {
    stringBuffer.append(TEXT_815);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.FeatureMap"));
    stringBuffer.append(TEXT_816);
    stringBuffer.append(delegateFeature.getAccessorName());
    stringBuffer.append(TEXT_817);
    stringBuffer.append(genFeature.getQualifiedFeatureAccessor());
    stringBuffer.append(TEXT_818);
    if (!isJDK50 && genFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_819);
    stringBuffer.append(genFeature.getObjectType(genClass));
    stringBuffer.append(TEXT_820);
    }
    stringBuffer.append(TEXT_821);
    stringBuffer.append(genFeature.getCapName());
    if (!isJDK50 && genFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_822);
    }
    stringBuffer.append(TEXT_823);
    }
    } else if (setAccessorOperation != null) {
    stringBuffer.append(TEXT_824);
    stringBuffer.append(setAccessorOperation.getBody(genModel.getIndentation(stringBuffer)));
    } else {
    stringBuffer.append(TEXT_825);
    stringBuffer.append(genFeature.getFormattedName());
    stringBuffer.append(TEXT_826);
    stringBuffer.append(genFeature.getFeatureKind());
    stringBuffer.append(TEXT_827);
    //Class/setGenFeature.todo.override.javajetinc
    }
    stringBuffer.append(TEXT_828);
    }
    //Class/setGenFeature.override.javajetinc
    }
    if (isImplementation && !genModel.isReflectiveDelegation() && genFeature.isBasicUnset()) {
    stringBuffer.append(TEXT_829);
    if (isJDK50) { //Class/basicUnsetGenFeature.annotations.insert.javajetinc
    }
    stringBuffer.append(TEXT_830);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.notify.NotificationChain"));
    stringBuffer.append(TEXT_831);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_832);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.notify.NotificationChain"));
    stringBuffer.append(TEXT_833);
    if (genModel.isDynamicDelegation()) {
    stringBuffer.append(TEXT_834);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.InternalEObject"));
    stringBuffer.append(TEXT_835);
    if (genFeature.isResolveProxies()) {
    stringBuffer.append(TEXT_836);
    stringBuffer.append(genFeature.getAccessorName());
    } else {
    stringBuffer.append(genFeature.getGetAccessor());
    }
    stringBuffer.append(TEXT_837);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(positiveOffsetCorrection);
    stringBuffer.append(TEXT_838);
    } else if (!genFeature.isVolatile()) {
    if (genModel.isVirtualDelegation()) {
    if (!genModel.isSuppressNotification()) {
    stringBuffer.append(TEXT_839);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_840);
    }
    stringBuffer.append(TEXT_841);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(positiveOffsetCorrection);
    stringBuffer.append(TEXT_842);
    } else {
    if (!genModel.isSuppressNotification()) {
    stringBuffer.append(TEXT_843);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_844);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_845);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_846);
    }
    stringBuffer.append(TEXT_847);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_848);
    }
    if (genModel.isVirtualDelegation()) {
    if (!genModel.isSuppressNotification()) {
    stringBuffer.append(TEXT_849);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_850);
    }
    } else if (genClass.isESetFlag(genFeature)) {
    if (!genModel.isSuppressNotification()) {
    stringBuffer.append(TEXT_851);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_852);
    stringBuffer.append(genClass.getESetFlagsField(genFeature));
    stringBuffer.append(TEXT_853);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_854);
    }
    stringBuffer.append(TEXT_855);
    stringBuffer.append(genClass.getESetFlagsField(genFeature));
    stringBuffer.append(TEXT_856);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_857);
    } else {
    if (!genModel.isSuppressNotification()) {
    stringBuffer.append(TEXT_858);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_859);
    stringBuffer.append(genFeature.getUncapName());
    stringBuffer.append(TEXT_860);
    }
    stringBuffer.append(TEXT_861);
    stringBuffer.append(genFeature.getUncapName());
    stringBuffer.append(TEXT_862);
    }
    if (!genModel.isSuppressNotification()) {
    stringBuffer.append(TEXT_863);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.impl.ENotificationImpl"));
    stringBuffer.append(TEXT_864);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.impl.ENotificationImpl"));
    stringBuffer.append(TEXT_865);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.notify.Notification"));
    stringBuffer.append(TEXT_866);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(positiveOffsetCorrection);
    stringBuffer.append(TEXT_867);
    if (genModel.isVirtualDelegation()) {
    stringBuffer.append(TEXT_868);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_869);
    } else {
    stringBuffer.append(TEXT_870);
    stringBuffer.append(genFeature.getCapName());
    }
    stringBuffer.append(TEXT_871);
    if (genModel.isVirtualDelegation()) {
    stringBuffer.append(TEXT_872);
    } else {
    stringBuffer.append(TEXT_873);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_874);
    }
    stringBuffer.append(TEXT_875);
    }
    } else {
    stringBuffer.append(TEXT_876);
    stringBuffer.append(genFeature.getFormattedName());
    stringBuffer.append(TEXT_877);
    stringBuffer.append(genFeature.getFeatureKind());
    stringBuffer.append(TEXT_878);
    //Class/basicUnsetGenFeature.todo.override.javajetinc
    }
    stringBuffer.append(TEXT_879);
    //Class.basicUnsetGenFeature.override.javajetinc
    }
    if (genFeature.isUnset() && (isImplementation || !genFeature.isSuppressedUnsetVisibility())) {
    if (isInterface) {
    stringBuffer.append(TEXT_880);
    stringBuffer.append(genClass.getQualifiedInterfaceName());
    stringBuffer.append(TEXT_881);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_882);
    stringBuffer.append(genFeature.getFormattedName());
    stringBuffer.append(TEXT_883);
    stringBuffer.append(genFeature.getFeatureKind());
    stringBuffer.append(TEXT_884);
    stringBuffer.append(TEXT_885);
    if (!genFeature.isSuppressedIsSetVisibility()) {
    stringBuffer.append(TEXT_886);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_887);
    }
    stringBuffer.append(TEXT_888);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_889);
    if (!genFeature.isListType() && !genFeature.isSuppressedSetVisibility()) {
    stringBuffer.append(TEXT_890);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_891);
    stringBuffer.append(genFeature.getRawImportedBoundType());
    stringBuffer.append(TEXT_892);
    }
    stringBuffer.append(TEXT_893);
    //Class/unsetGenFeature.javadoc.override.javajetinc
    } else {
    stringBuffer.append(TEXT_894);
    if (isJDK50) { //Class/unsetGenFeature.annotations.insert.javajetinc
    }
    }
    if (!isImplementation) {
    stringBuffer.append(TEXT_895);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_896);
    } else {
    stringBuffer.append(TEXT_897);
    stringBuffer.append(genFeature.getAccessorName());
    if (genClass.hasCollidingUnsetAccessorOperation(genFeature)) {
    stringBuffer.append(TEXT_898);
    }
    stringBuffer.append(TEXT_899);
    if (genModel.isDynamicDelegation()) {
    stringBuffer.append(TEXT_900);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(TEXT_901);
    stringBuffer.append(genFeature.getQualifiedFeatureAccessor());
    stringBuffer.append(TEXT_902);
    } else if (genModel.isReflectiveDelegation()) {
    stringBuffer.append(TEXT_903);
    stringBuffer.append(genFeature.getQualifiedFeatureAccessor());
    stringBuffer.append(TEXT_904);
    } else if (!genFeature.isVolatile()) {
    if (genFeature.isListType()) {
    if (genModel.isVirtualDelegation()) {
    stringBuffer.append(TEXT_905);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_906);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_907);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_908);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(positiveOffsetCorrection);
    stringBuffer.append(TEXT_909);
    }
    stringBuffer.append(TEXT_910);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_911);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.InternalEList"));
    stringBuffer.append(TEXT_912);
    stringBuffer.append(singleWildcard);
    stringBuffer.append(TEXT_913);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_914);
    } else if (genFeature.isBidirectional() || genFeature.isEffectiveContains()) {
    if (genModel.isVirtualDelegation()) {
    stringBuffer.append(TEXT_915);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_916);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_917);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_918);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(positiveOffsetCorrection);
    stringBuffer.append(TEXT_919);
    }
    stringBuffer.append(TEXT_920);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_921);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.notify.NotificationChain"));
    stringBuffer.append(TEXT_922);
    if (!genFeature.isBidirectional()) {
    stringBuffer.append(TEXT_923);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.InternalEObject"));
    stringBuffer.append(TEXT_924);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_925);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(negativeOffsetCorrection);
    stringBuffer.append(TEXT_926);
    } else { GenFeature reverseFeature = genFeature.getReverse(); GenClass targetClass = reverseFeature.getGenClass(); String reverseOffsetCorrection = targetClass.hasOffsetCorrection() ? " + " + genClass.getOffsetCorrectionField(reverseFeature) : "";
    stringBuffer.append(TEXT_927);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.InternalEObject"));
    stringBuffer.append(TEXT_928);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_929);
    stringBuffer.append(targetClass.getQualifiedFeatureID(reverseFeature));
    stringBuffer.append(reverseOffsetCorrection);
    stringBuffer.append(TEXT_930);
    stringBuffer.append(targetClass.getRawImportedInterfaceName());
    stringBuffer.append(TEXT_931);
    }
    stringBuffer.append(TEXT_932);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_933);
    if (genModel.isVirtualDelegation()) {
    stringBuffer.append(TEXT_934);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_935);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(positiveOffsetCorrection);
    stringBuffer.append(TEXT_936);
    } else if (genClass.isESetFlag(genFeature)) {
    if (!genModel.isSuppressNotification()) {
    stringBuffer.append(TEXT_937);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_938);
    stringBuffer.append(genClass.getESetFlagsField(genFeature));
    stringBuffer.append(TEXT_939);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_940);
    }
    stringBuffer.append(TEXT_941);
    stringBuffer.append(genClass.getESetFlagsField(genFeature));
    stringBuffer.append(TEXT_942);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_943);
    } else {
    if (!genModel.isSuppressNotification()) {
    stringBuffer.append(TEXT_944);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_945);
    stringBuffer.append(genFeature.getUncapName());
    stringBuffer.append(TEXT_946);
    }
    stringBuffer.append(TEXT_947);
    stringBuffer.append(genFeature.getUncapName());
    stringBuffer.append(TEXT_948);
    }
    if (!genModel.isSuppressNotification()) {
    stringBuffer.append(TEXT_949);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.impl.ENotificationImpl"));
    stringBuffer.append(TEXT_950);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.notify.Notification"));
    stringBuffer.append(TEXT_951);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(positiveOffsetCorrection);
    stringBuffer.append(TEXT_952);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_953);
    }
    stringBuffer.append(TEXT_954);
    } else {
    if (genClass.isFlag(genFeature)) {
    if (!genModel.isSuppressNotification()) {
    if (genFeature.isBooleanType()) {
    stringBuffer.append(TEXT_955);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_956);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_957);
    stringBuffer.append(genClass.getFlagsField(genFeature));
    stringBuffer.append(TEXT_958);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_959);
    } else {
    stringBuffer.append(TEXT_960);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_961);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_962);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_963);
    stringBuffer.append(genClass.getFlagsField(genFeature));
    stringBuffer.append(TEXT_964);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_965);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_966);
    }
    }
    } else if (genModel.isVirtualDelegation() && !genFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_967);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_968);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(positiveOffsetCorrection);
    stringBuffer.append(TEXT_969);
    } else {
    if (!genModel.isSuppressNotification()) {
    stringBuffer.append(TEXT_970);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_971);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_972);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_973);
    }
    }
    if (!genModel.isSuppressNotification()) {
    if (genModel.isVirtualDelegation() && !genFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_974);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_975);
    } else if (genClass.isESetFlag(genFeature)) {
    stringBuffer.append(TEXT_976);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_977);
    stringBuffer.append(genClass.getESetFlagsField(genFeature));
    stringBuffer.append(TEXT_978);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_979);
    } else {
    stringBuffer.append(TEXT_980);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_981);
    stringBuffer.append(genFeature.getUncapName());
    stringBuffer.append(TEXT_982);
    }
    }
    if (genFeature.isReferenceType()) {
    stringBuffer.append(TEXT_983);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_984);
    if (!genModel.isVirtualDelegation()) {
    if (genClass.isESetFlag(genFeature)) {
    stringBuffer.append(TEXT_985);
    stringBuffer.append(genClass.getESetFlagsField(genFeature));
    stringBuffer.append(TEXT_986);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_987);
    } else {
    stringBuffer.append(TEXT_988);
    stringBuffer.append(genFeature.getUncapName());
    stringBuffer.append(TEXT_989);
    }
    }
    if (!genModel.isSuppressNotification()) {
    stringBuffer.append(TEXT_990);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.impl.ENotificationImpl"));
    stringBuffer.append(TEXT_991);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.notify.Notification"));
    stringBuffer.append(TEXT_992);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(positiveOffsetCorrection);
    stringBuffer.append(TEXT_993);
    if (genModel.isVirtualDelegation()) {
    stringBuffer.append(TEXT_994);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_995);
    } else {
    stringBuffer.append(TEXT_996);
    stringBuffer.append(genFeature.getCapName());
    }
    stringBuffer.append(TEXT_997);
    if (genModel.isVirtualDelegation()) {
    stringBuffer.append(TEXT_998);
    } else {
    stringBuffer.append(TEXT_999);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_1000);
    }
    stringBuffer.append(TEXT_1001);
    }
    } else {
    if (genClass.isFlag(genFeature)) {
    if (genFeature.isBooleanType()) {
    stringBuffer.append(TEXT_1002);
    stringBuffer.append(genFeature.getEDefault());
    stringBuffer.append(TEXT_1003);
    stringBuffer.append(genClass.getFlagsField(genFeature));
    stringBuffer.append(TEXT_1004);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_1005);
    stringBuffer.append(genClass.getFlagsField(genFeature));
    stringBuffer.append(TEXT_1006);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_1007);
    } else {
    stringBuffer.append(TEXT_1008);
    stringBuffer.append(genClass.getFlagsField(genFeature));
    stringBuffer.append(TEXT_1009);
    stringBuffer.append(genClass.getFlagsField(genFeature));
    stringBuffer.append(TEXT_1010);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_1011);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_1012);
    }
    } else if (!genModel.isVirtualDelegation() || genFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_1013);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_1014);
    stringBuffer.append(genFeature.getEDefault());
    stringBuffer.append(TEXT_1015);
    }
    if (!genModel.isVirtualDelegation() || genFeature.isPrimitiveType()) {
    if (genClass.isESetFlag(genFeature)) {
    stringBuffer.append(TEXT_1016);
    stringBuffer.append(genClass.getESetFlagsField(genFeature));
    stringBuffer.append(TEXT_1017);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_1018);
    } else {
    stringBuffer.append(TEXT_1019);
    stringBuffer.append(genFeature.getUncapName());
    stringBuffer.append(TEXT_1020);
    }
    }
    if (!genModel.isSuppressNotification()) {
    stringBuffer.append(TEXT_1021);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.impl.ENotificationImpl"));
    stringBuffer.append(TEXT_1022);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.notify.Notification"));
    stringBuffer.append(TEXT_1023);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(positiveOffsetCorrection);
    stringBuffer.append(TEXT_1024);
    if (genModel.isVirtualDelegation() && !genFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_1025);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_1026);
    stringBuffer.append(genFeature.getEDefault());
    } else {
    stringBuffer.append(TEXT_1027);
    stringBuffer.append(genFeature.getCapName());
    }
    stringBuffer.append(TEXT_1028);
    stringBuffer.append(genFeature.getEDefault());
    stringBuffer.append(TEXT_1029);
    if (genModel.isVirtualDelegation() && !genFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_1030);
    } else {
    stringBuffer.append(TEXT_1031);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_1032);
    }
    stringBuffer.append(TEXT_1033);
    }
    }
    }
    } else if (genFeature.hasDelegateFeature()) { GenFeature delegateFeature = genFeature.getDelegateFeature();
    if (delegateFeature.isWrappedFeatureMapType()) {
    stringBuffer.append(TEXT_1034);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.FeatureMap"));
    stringBuffer.append(TEXT_1035);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.FeatureMap"));
    stringBuffer.append(TEXT_1036);
    stringBuffer.append(delegateFeature.getAccessorName());
    stringBuffer.append(TEXT_1037);
    stringBuffer.append(genFeature.getQualifiedFeatureAccessor());
    stringBuffer.append(TEXT_1038);
    } else {
    stringBuffer.append(TEXT_1039);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.FeatureMap"));
    stringBuffer.append(TEXT_1040);
    stringBuffer.append(delegateFeature.getAccessorName());
    stringBuffer.append(TEXT_1041);
    stringBuffer.append(genFeature.getQualifiedFeatureAccessor());
    stringBuffer.append(TEXT_1042);
    }
    } else if (genClass.getUnsetAccessorOperation(genFeature) != null) {
    stringBuffer.append(TEXT_1043);
    stringBuffer.append(genClass.getUnsetAccessorOperation(genFeature).getBody(genModel.getIndentation(stringBuffer)));
    } else {
    stringBuffer.append(TEXT_1044);
    stringBuffer.append(genFeature.getFormattedName());
    stringBuffer.append(TEXT_1045);
    stringBuffer.append(genFeature.getFeatureKind());
    stringBuffer.append(TEXT_1046);
    //Class/unsetGenFeature.todo.override.javajetinc
    }
    stringBuffer.append(TEXT_1047);
    }
    //Class/unsetGenFeature.override.javajetinc
    }
    if (genFeature.isIsSet() && (isImplementation || !genFeature.isSuppressedIsSetVisibility())) {
    if (isInterface) {
    stringBuffer.append(TEXT_1048);
    stringBuffer.append(genClass.getQualifiedInterfaceName());
    stringBuffer.append(TEXT_1049);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_1050);
    stringBuffer.append(genFeature.getFormattedName());
    stringBuffer.append(TEXT_1051);
    stringBuffer.append(genFeature.getFeatureKind());
    stringBuffer.append(TEXT_1052);
    stringBuffer.append(TEXT_1053);
    stringBuffer.append(genFeature.getFormattedName());
    stringBuffer.append(TEXT_1054);
    stringBuffer.append(genFeature.getFeatureKind());
    stringBuffer.append(TEXT_1055);
    if (genFeature.isChangeable() && !genFeature.isSuppressedUnsetVisibility()) {
    stringBuffer.append(TEXT_1056);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_1057);
    }
    stringBuffer.append(TEXT_1058);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_1059);
    if (!genFeature.isListType() && genFeature.isChangeable() && !genFeature.isSuppressedSetVisibility()) {
    stringBuffer.append(TEXT_1060);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_1061);
    stringBuffer.append(genFeature.getRawImportedBoundType());
    stringBuffer.append(TEXT_1062);
    }
    stringBuffer.append(TEXT_1063);
    //Class/isSetGenFeature.javadoc.override.javajetinc
    } else {
    stringBuffer.append(TEXT_1064);
    if (isJDK50) { //Class/isSetGenFeature.annotations.insert.javajetinc
    }
    }
    if (!isImplementation) {
    stringBuffer.append(TEXT_1065);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_1066);
    } else {
    stringBuffer.append(TEXT_1067);
    stringBuffer.append(genFeature.getAccessorName());
    if (genClass.hasCollidingIsSetAccessorOperation(genFeature)) {
    stringBuffer.append(TEXT_1068);
    }
    stringBuffer.append(TEXT_1069);
    if (genModel.isDynamicDelegation()) {
    stringBuffer.append(TEXT_1070);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(TEXT_1071);
    stringBuffer.append(genFeature.getQualifiedFeatureAccessor());
    stringBuffer.append(TEXT_1072);
    } else if (genModel.isReflectiveDelegation()) {
    stringBuffer.append(TEXT_1073);
    stringBuffer.append(genFeature.getQualifiedFeatureAccessor());
    stringBuffer.append(TEXT_1074);
    } else if (!genFeature.isVolatile()) {
    if (genFeature.isListType()) {
    if (genModel.isVirtualDelegation()) {
    stringBuffer.append(TEXT_1075);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_1076);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_1077);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_1078);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(positiveOffsetCorrection);
    stringBuffer.append(TEXT_1079);
    }
    stringBuffer.append(TEXT_1080);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_1081);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.InternalEList"));
    stringBuffer.append(TEXT_1082);
    stringBuffer.append(singleWildcard);
    stringBuffer.append(TEXT_1083);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_1084);
    } else {
    if (genModel.isVirtualDelegation() && !genFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_1085);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(positiveOffsetCorrection);
    stringBuffer.append(TEXT_1086);
    } else if (genClass.isESetFlag(genFeature)) {
    stringBuffer.append(TEXT_1087);
    stringBuffer.append(genClass.getESetFlagsField(genFeature));
    stringBuffer.append(TEXT_1088);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_1089);
    } else {
    stringBuffer.append(TEXT_1090);
    stringBuffer.append(genFeature.getUncapName());
    stringBuffer.append(TEXT_1091);
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
    } else if (genClass.getIsSetAccessorOperation(genFeature) != null) {
    stringBuffer.append(TEXT_1101);
    stringBuffer.append(genClass.getIsSetAccessorOperation(genFeature).getBody(genModel.getIndentation(stringBuffer)));
    } else {
    stringBuffer.append(TEXT_1102);
    stringBuffer.append(genFeature.getFormattedName());
    stringBuffer.append(TEXT_1103);
    stringBuffer.append(genFeature.getFeatureKind());
    stringBuffer.append(TEXT_1104);
    //Class/isSetGenFeature.todo.override.javajetinc
    }
    stringBuffer.append(TEXT_1105);
    }
    //Class/isSetGenFeature.override.javajetinc
    }
    //Class/genFeature.override.javajetinc
    }//for
    }}.run();
    for (GenOperation genOperation : (isImplementation ? genClass.getImplementedGenOperations() : genClass.getDeclaredGenOperations())) {
    if (isInterface) {
    stringBuffer.append(TEXT_1106);
    stringBuffer.append(TEXT_1107);
    if (genOperation.hasDocumentation() || genOperation.hasParameterDocumentation()) {
    stringBuffer.append(TEXT_1108);
    if (genOperation.hasDocumentation()) {
    stringBuffer.append(TEXT_1109);
    stringBuffer.append(genOperation.getDocumentation(genModel.getIndentation(stringBuffer)));
    }
    for (GenParameter genParameter : genOperation.getGenParameters()) {
    if (genParameter.hasDocumentation()) { String documentation = genParameter.getDocumentation("");
    if (documentation.contains("\n") || documentation.contains("\r")) {
    stringBuffer.append(TEXT_1110);
    stringBuffer.append(genParameter.getName());
    stringBuffer.append(TEXT_1111);
    stringBuffer.append(genParameter.getDocumentation(genModel.getIndentation(stringBuffer)));
    } else {
    stringBuffer.append(TEXT_1112);
    stringBuffer.append(genParameter.getName());
    stringBuffer.append(TEXT_1113);
    stringBuffer.append(genParameter.getDocumentation(genModel.getIndentation(stringBuffer)));
    }
    }
    }
    stringBuffer.append(TEXT_1114);
    }
    if (!genModel.isSuppressEMFModelTags()) { boolean first = true; for (StringTokenizer stringTokenizer = new StringTokenizer(genOperation.getModelInfo(), "\n\r"); stringTokenizer.hasMoreTokens(); ) { String modelInfo = stringTokenizer.nextToken(); if (first) { first = false;
    stringBuffer.append(TEXT_1115);
    stringBuffer.append(modelInfo);
    } else {
    stringBuffer.append(TEXT_1116);
    stringBuffer.append(modelInfo);
    }} if (first) {
    stringBuffer.append(TEXT_1117);
    }}
    stringBuffer.append(TEXT_1118);
    //Class/genOperation.javadoc.override.javajetinc
    } else {
    stringBuffer.append(TEXT_1119);
    if (isJDK50) { //Class/genOperation.annotations.insert.javajetinc
    }
    }
    if (!isImplementation) {
    stringBuffer.append(TEXT_1120);
    stringBuffer.append(genOperation.getTypeParameters(genClass));
    stringBuffer.append(genOperation.getImportedType(genClass));
    stringBuffer.append(TEXT_1121);
    stringBuffer.append(genOperation.getName());
    stringBuffer.append(TEXT_1122);
    stringBuffer.append(genOperation.getParameters(genClass));
    stringBuffer.append(TEXT_1123);
    stringBuffer.append(genOperation.getThrows(genClass));
    stringBuffer.append(TEXT_1124);
    } else {
    stringBuffer.append(TEXT_1125);
    stringBuffer.append(genOperation.getTypeParameters(genClass));
    stringBuffer.append(genOperation.getImportedType(genClass));
    stringBuffer.append(TEXT_1126);
    stringBuffer.append(genOperation.getName());
    stringBuffer.append(TEXT_1127);
    stringBuffer.append(genOperation.getParameters(genClass));
    stringBuffer.append(TEXT_1128);
    stringBuffer.append(genOperation.getThrows(genClass));
    stringBuffer.append(TEXT_1129);
    if (genOperation.hasBody()) {
    stringBuffer.append(TEXT_1130);
    stringBuffer.append(genOperation.getBody(genModel.getIndentation(stringBuffer)));
    } else if (genOperation.isInvariant()) {GenClass opClass = genOperation.getGenClass(); String diagnostics = genOperation.getGenParameters().get(0).getName(); String context = genOperation.getGenParameters().get(1).getName();
    stringBuffer.append(TEXT_1131);
    stringBuffer.append(diagnostics);
    stringBuffer.append(TEXT_1132);
    stringBuffer.append(diagnostics);
    stringBuffer.append(TEXT_1133);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.util.BasicDiagnostic"));
    stringBuffer.append(TEXT_1134);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.util.Diagnostic"));
    stringBuffer.append(TEXT_1135);
    stringBuffer.append(opClass.getGenPackage().getImportedValidatorClassName());
    stringBuffer.append(TEXT_1136);
    stringBuffer.append(opClass.getGenPackage().getImportedValidatorClassName());
    stringBuffer.append(TEXT_1137);
    stringBuffer.append(opClass.getOperationID(genOperation));
    stringBuffer.append(TEXT_1138);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.plugin.EcorePlugin"));
    stringBuffer.append(TEXT_1139);
    stringBuffer.append(genOperation.getName());
    stringBuffer.append(TEXT_1140);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.EObjectValidator"));
    stringBuffer.append(TEXT_1141);
    stringBuffer.append(context);
    stringBuffer.append(TEXT_1142);
    stringBuffer.append(genModel.getNonNLS());
    stringBuffer.append(genModel.getNonNLS(2));
    stringBuffer.append(TEXT_1143);
    } else {
    stringBuffer.append(TEXT_1144);
    //Class/implementedGenOperation.todo.override.javajetinc
    }
    stringBuffer.append(TEXT_1145);
    }
    //Class/implementedGenOperation.override.javajetinc
    }//for
    if (isImplementation && !genModel.isReflectiveDelegation() && genClass.implementsAny(genClass.getEInverseAddGenFeatures())) {
    stringBuffer.append(TEXT_1146);
    if (genModel.useGenerics()) {
    for (GenFeature genFeature : genClass.getEInverseAddGenFeatures()) {
    if (genFeature.isUncheckedCast(genClass)) {
    stringBuffer.append(TEXT_1147);
    break; }
    }
    }
    if (genModel.useClassOverrideAnnotation()) {
    stringBuffer.append(TEXT_1148);
    }
    stringBuffer.append(TEXT_1149);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.notify.NotificationChain"));
    stringBuffer.append(TEXT_1150);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.InternalEObject"));
    stringBuffer.append(TEXT_1151);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.notify.NotificationChain"));
    stringBuffer.append(TEXT_1152);
    stringBuffer.append(negativeOffsetCorrection);
    stringBuffer.append(TEXT_1153);
    for (GenFeature genFeature : genClass.getEInverseAddGenFeatures()) {
    stringBuffer.append(TEXT_1154);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(TEXT_1155);
    if (genFeature.isListType()) { String cast = "("  + genModel.getImportedName("org.eclipse.emf.ecore.util.InternalEList") + (!genModel.useGenerics() ? ")" : "<" + genModel.getImportedName("org.eclipse.emf.ecore.InternalEObject") + ">)(" + genModel.getImportedName("org.eclipse.emf.ecore.util.InternalEList") + "<?>)");
    if (genFeature.isMapType() && genFeature.isEffectiveSuppressEMFTypes()) {
    stringBuffer.append(TEXT_1156);
    stringBuffer.append(cast);
    stringBuffer.append(TEXT_1157);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.util.EMap"));
    stringBuffer.append(TEXT_1158);
    stringBuffer.append(genFeature.getImportedMapTemplateArguments(genClass));
    stringBuffer.append(TEXT_1159);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_1160);
    } else {
    stringBuffer.append(TEXT_1161);
    stringBuffer.append(cast);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_1162);
    }
    } else if (genFeature.isContainer()) {
    stringBuffer.append(TEXT_1163);
    if (genFeature.isBasicSet()) {
    stringBuffer.append(TEXT_1164);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_1165);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_1166);
    } else {
    stringBuffer.append(TEXT_1167);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(positiveOffsetCorrection);
    stringBuffer.append(TEXT_1168);
    }
    } else {
    if (genClass.getImplementingGenModel(genFeature).isVirtualDelegation()) {
    stringBuffer.append(TEXT_1169);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_1170);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_1171);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_1172);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(positiveOffsetCorrection);
    stringBuffer.append(TEXT_1173);
    } else if (genFeature.isVolatile() || genClass.getImplementingGenModel(genFeature).isDynamicDelegation()) {
    stringBuffer.append(TEXT_1174);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_1175);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_1176);
    if (genFeature.isResolveProxies()) {
    stringBuffer.append(TEXT_1177);
    stringBuffer.append(genFeature.getAccessorName());
    } else {
    stringBuffer.append(genFeature.getGetAccessor());
    }
    stringBuffer.append(TEXT_1178);
    }
    stringBuffer.append(TEXT_1179);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_1180);
    if (genFeature.isEffectiveContains()) {
    stringBuffer.append(TEXT_1181);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.InternalEObject"));
    stringBuffer.append(TEXT_1182);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_1183);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(negativeOffsetCorrection);
    stringBuffer.append(TEXT_1184);
    } else { GenFeature reverseFeature = genFeature.getReverse(); GenClass targetClass = reverseFeature.getGenClass(); String reverseOffsetCorrection = targetClass.hasOffsetCorrection() ? " + " + genClass.getOffsetCorrectionField(reverseFeature) : "";
    stringBuffer.append(TEXT_1185);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.InternalEObject"));
    stringBuffer.append(TEXT_1186);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_1187);
    stringBuffer.append(targetClass.getQualifiedFeatureID(reverseFeature));
    stringBuffer.append(reverseOffsetCorrection);
    stringBuffer.append(TEXT_1188);
    stringBuffer.append(targetClass.getRawImportedInterfaceName());
    stringBuffer.append(TEXT_1189);
    }
    stringBuffer.append(TEXT_1190);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_1191);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_1192);
    }
    }
    stringBuffer.append(TEXT_1193);
    if (genModel.isMinimalReflectiveMethods()) {
    stringBuffer.append(TEXT_1194);
    } else {
    stringBuffer.append(TEXT_1195);
    }
    stringBuffer.append(TEXT_1196);
    }
    if (isImplementation && !genModel.isReflectiveDelegation() && genClass.implementsAny(genClass.getEInverseRemoveGenFeatures())) {
    stringBuffer.append(TEXT_1197);
    if (genModel.useClassOverrideAnnotation()) {
    stringBuffer.append(TEXT_1198);
    }
    stringBuffer.append(TEXT_1199);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.notify.NotificationChain"));
    stringBuffer.append(TEXT_1200);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.InternalEObject"));
    stringBuffer.append(TEXT_1201);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.notify.NotificationChain"));
    stringBuffer.append(TEXT_1202);
    stringBuffer.append(negativeOffsetCorrection);
    stringBuffer.append(TEXT_1203);
    for (GenFeature genFeature : genClass.getEInverseRemoveGenFeatures()) {
    stringBuffer.append(TEXT_1204);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(TEXT_1205);
    if (genFeature.isListType()) {
    if (genFeature.isMapType() && genFeature.isEffectiveSuppressEMFTypes()) {
    stringBuffer.append(TEXT_1206);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.InternalEList"));
    stringBuffer.append(singleWildcard);
    stringBuffer.append(TEXT_1207);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.util.EMap"));
    stringBuffer.append(TEXT_1208);
    stringBuffer.append(genFeature.getImportedMapTemplateArguments(genClass));
    stringBuffer.append(TEXT_1209);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_1210);
    } else if (genFeature.isWrappedFeatureMapType()) {
    stringBuffer.append(TEXT_1211);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.InternalEList"));
    stringBuffer.append(singleWildcard);
    stringBuffer.append(TEXT_1212);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.FeatureMap"));
    stringBuffer.append(TEXT_1213);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_1214);
    } else {
    stringBuffer.append(TEXT_1215);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.InternalEList"));
    stringBuffer.append(singleWildcard);
    stringBuffer.append(TEXT_1216);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_1217);
    }
    } else if (genFeature.isContainer() && !genFeature.isBasicSet()) {
    stringBuffer.append(TEXT_1218);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(positiveOffsetCorrection);
    stringBuffer.append(TEXT_1219);
    } else if (genFeature.isUnsettable()) {
    stringBuffer.append(TEXT_1220);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_1221);
    } else {
    stringBuffer.append(TEXT_1222);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_1223);
    }
    }
    stringBuffer.append(TEXT_1224);
    if (genModel.isMinimalReflectiveMethods()) {
    stringBuffer.append(TEXT_1225);
    } else {
    stringBuffer.append(TEXT_1226);
    }
    stringBuffer.append(TEXT_1227);
    }
    if (isImplementation && !genModel.isReflectiveDelegation() && genClass.implementsAny(genClass.getEBasicRemoveFromContainerGenFeatures())) {
    stringBuffer.append(TEXT_1228);
    if (genModel.useClassOverrideAnnotation()) {
    stringBuffer.append(TEXT_1229);
    }
    stringBuffer.append(TEXT_1230);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.notify.NotificationChain"));
    stringBuffer.append(TEXT_1231);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.notify.NotificationChain"));
    stringBuffer.append(TEXT_1232);
    stringBuffer.append(negativeOffsetCorrection);
    stringBuffer.append(TEXT_1233);
    for (GenFeature genFeature : genClass.getEBasicRemoveFromContainerGenFeatures()) {
    GenFeature reverseFeature = genFeature.getReverse(); GenClass targetClass = reverseFeature.getGenClass(); String reverseOffsetCorrection = targetClass.hasOffsetCorrection() ? " + " + genClass.getOffsetCorrectionField(reverseFeature) : "";
    stringBuffer.append(TEXT_1234);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(TEXT_1235);
    stringBuffer.append(targetClass.getQualifiedFeatureID(reverseFeature));
    stringBuffer.append(reverseOffsetCorrection);
    stringBuffer.append(TEXT_1236);
    stringBuffer.append(targetClass.getRawImportedInterfaceName());
    stringBuffer.append(TEXT_1237);
    }
    stringBuffer.append(TEXT_1238);
    if (genModel.isMinimalReflectiveMethods()) {
    stringBuffer.append(TEXT_1239);
    } else {
    stringBuffer.append(TEXT_1240);
    }
    stringBuffer.append(TEXT_1241);
    }
    if (isImplementation && !genModel.isReflectiveDelegation() && genClass.implementsAny(genClass.getEGetGenFeatures())) {
    stringBuffer.append(TEXT_1242);
    if (genModel.useClassOverrideAnnotation()) {
    stringBuffer.append(TEXT_1243);
    }
    stringBuffer.append(TEXT_1244);
    stringBuffer.append(negativeOffsetCorrection);
    stringBuffer.append(TEXT_1245);
    for (GenFeature genFeature : genClass.getEGetGenFeatures()) {
    stringBuffer.append(TEXT_1246);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(TEXT_1247);
    if (genFeature.isPrimitiveType()) {
    if (isJDK50) {
    stringBuffer.append(TEXT_1248);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_1249);
    } else if (genFeature.isBooleanType()) {
    stringBuffer.append(TEXT_1250);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_1251);
    } else {
    stringBuffer.append(TEXT_1252);
    stringBuffer.append(genFeature.getObjectType(genClass));
    stringBuffer.append(TEXT_1253);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_1254);
    }
    } else if (genFeature.isResolveProxies() && !genFeature.isListType()) {
    stringBuffer.append(TEXT_1255);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_1256);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_1257);
    } else if (genFeature.isMapType()) {
    if (genFeature.isEffectiveSuppressEMFTypes()) {
    stringBuffer.append(TEXT_1258);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.util.EMap"));
    stringBuffer.append(TEXT_1259);
    stringBuffer.append(genFeature.getImportedMapTemplateArguments(genClass));
    stringBuffer.append(TEXT_1260);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_1261);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_1262);
    } else {
    stringBuffer.append(TEXT_1263);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_1264);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_1265);
    }
    } else if (genFeature.isWrappedFeatureMapType()) {
    stringBuffer.append(TEXT_1266);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.FeatureMap"));
    stringBuffer.append(TEXT_1267);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_1268);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_1269);
    } else if (genFeature.isFeatureMapType()) {
    stringBuffer.append(TEXT_1270);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_1271);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.FeatureMap"));
    stringBuffer.append(TEXT_1272);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_1273);
    } else {
    stringBuffer.append(TEXT_1274);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_1275);
    }
    }
    stringBuffer.append(TEXT_1276);
    if (genModel.isMinimalReflectiveMethods()) {
    stringBuffer.append(TEXT_1277);
    } else {
    stringBuffer.append(TEXT_1278);
    }
    stringBuffer.append(TEXT_1279);
    }
    if (isImplementation && !genModel.isReflectiveDelegation() && genClass.implementsAny(genClass.getESetGenFeatures())) {
    stringBuffer.append(TEXT_1280);
    if (genModel.useGenerics()) {
    for (GenFeature genFeature : genClass.getESetGenFeatures()) {
    if (genFeature.isUncheckedCast(genClass) && !genFeature.isFeatureMapType() && !genFeature.isMapType()) {
    stringBuffer.append(TEXT_1281);
    break; }
    }
    }
    if (genModel.useClassOverrideAnnotation()) {
    stringBuffer.append(TEXT_1282);
    }
    stringBuffer.append(TEXT_1283);
    stringBuffer.append(negativeOffsetCorrection);
    stringBuffer.append(TEXT_1284);
    for (GenFeature genFeature : genClass.getESetGenFeatures()) {
    stringBuffer.append(TEXT_1285);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(TEXT_1286);
    if (genFeature.isListType()) {
    if (genFeature.isWrappedFeatureMapType()) {
    stringBuffer.append(TEXT_1287);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.FeatureMap"));
    stringBuffer.append(TEXT_1288);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.FeatureMap"));
    stringBuffer.append(TEXT_1289);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_1290);
    } else if (genFeature.isFeatureMapType()) {
    stringBuffer.append(TEXT_1291);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.FeatureMap"));
    stringBuffer.append(TEXT_1292);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_1293);
    } else if (genFeature.isMapType()) {
    if (genFeature.isEffectiveSuppressEMFTypes()) {
    stringBuffer.append(TEXT_1294);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.EStructuralFeature"));
    stringBuffer.append(TEXT_1295);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.util.EMap"));
    stringBuffer.append(TEXT_1296);
    stringBuffer.append(genFeature.getImportedMapTemplateArguments(genClass));
    stringBuffer.append(TEXT_1297);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_1298);
    } else {
    stringBuffer.append(TEXT_1299);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.EStructuralFeature"));
    stringBuffer.append(TEXT_1300);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_1301);
    }
    } else {
    stringBuffer.append(TEXT_1302);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_1303);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_1304);
    stringBuffer.append(genModel.getImportedName("java.util.Collection"));
    if (isJDK50) {
    stringBuffer.append(TEXT_1305);
    stringBuffer.append(genFeature.getListItemType(genClass));
    stringBuffer.append(TEXT_1306);
    }
    stringBuffer.append(TEXT_1307);
    }
    } else if (!isJDK50 && genFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_1308);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_1309);
    stringBuffer.append(genFeature.getObjectType(genClass));
    stringBuffer.append(TEXT_1310);
    stringBuffer.append(genFeature.getPrimitiveValueFunction());
    stringBuffer.append(TEXT_1311);
    } else {
    stringBuffer.append(TEXT_1312);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_1313);
    if (genFeature.getTypeGenDataType() == null || !genFeature.getTypeGenDataType().isObjectType() || !genFeature.getRawType().equals(genFeature.getType(genClass))) {
    stringBuffer.append(TEXT_1314);
    stringBuffer.append(genFeature.getObjectType(genClass));
    stringBuffer.append(TEXT_1315);
    }
    stringBuffer.append(TEXT_1316);
    }
    stringBuffer.append(TEXT_1317);
    }
    stringBuffer.append(TEXT_1318);
    if (genModel.isMinimalReflectiveMethods()) {
    stringBuffer.append(TEXT_1319);
    } else {
    stringBuffer.append(TEXT_1320);
    }
    stringBuffer.append(TEXT_1321);
    }
    if (isImplementation && !genModel.isReflectiveDelegation() && genClass.implementsAny(genClass.getEUnsetGenFeatures())) {
    stringBuffer.append(TEXT_1322);
    if (genModel.useClassOverrideAnnotation()) {
    stringBuffer.append(TEXT_1323);
    }
    stringBuffer.append(TEXT_1324);
    stringBuffer.append(negativeOffsetCorrection);
    stringBuffer.append(TEXT_1325);
    for (GenFeature genFeature : genClass.getEUnsetGenFeatures()) {
    stringBuffer.append(TEXT_1326);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(TEXT_1327);
    if (genFeature.isListType() && !genFeature.isUnsettable()) {
    if (genFeature.isWrappedFeatureMapType()) {
    stringBuffer.append(TEXT_1328);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.FeatureMap"));
    stringBuffer.append(TEXT_1329);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_1330);
    } else {
    stringBuffer.append(TEXT_1331);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_1332);
    }
    } else if (genFeature.isUnsettable()) {
    stringBuffer.append(TEXT_1333);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_1334);
    } else if (!genFeature.hasEDefault()) {
    stringBuffer.append(TEXT_1335);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_1336);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_1337);
    } else {
    stringBuffer.append(TEXT_1338);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_1339);
    stringBuffer.append(genFeature.getEDefault());
    stringBuffer.append(TEXT_1340);
    }
    stringBuffer.append(TEXT_1341);
    }
    stringBuffer.append(TEXT_1342);
    if (genModel.isMinimalReflectiveMethods()) {
    stringBuffer.append(TEXT_1343);
    } else {
    stringBuffer.append(TEXT_1344);
    }
    stringBuffer.append(TEXT_1345);
    //Class/eUnset.override.javajetinc
    }
    if (isImplementation && !genModel.isReflectiveDelegation() && genClass.implementsAny(genClass.getEIsSetGenFeatures())) {
    stringBuffer.append(TEXT_1346);
    if (genModel.useGenerics()) {
    for (GenFeature genFeature : genClass.getEIsSetGenFeatures()) {
    if (genFeature.isListType() && !genFeature.isUnsettable() && !genFeature.isWrappedFeatureMapType() && !genClass.isField(genFeature) && genFeature.isField() && genClass.getImplementingGenModel(genFeature).isVirtualDelegation()) {
    stringBuffer.append(TEXT_1347);
    break; }
    }
    }
    if (genModel.useClassOverrideAnnotation()) {
    stringBuffer.append(TEXT_1348);
    }
    stringBuffer.append(TEXT_1349);
    stringBuffer.append(negativeOffsetCorrection);
    stringBuffer.append(TEXT_1350);
    for (GenFeature genFeature : genClass.getEIsSetGenFeatures()) { String safeNameAccessor = genFeature.getSafeName(); if ("featureID".equals(safeNameAccessor)) { safeNameAccessor = "this." + safeNameAccessor; }
    stringBuffer.append(TEXT_1351);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(TEXT_1352);
    if (genFeature.isListType() && !genFeature.isUnsettable()) {
    if (genFeature.isWrappedFeatureMapType()) {
    if (genFeature.isVolatile()) {
    stringBuffer.append(TEXT_1353);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.FeatureMap"));
    stringBuffer.append(TEXT_1354);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_1355);
    } else {
    stringBuffer.append(TEXT_1356);
    stringBuffer.append(safeNameAccessor);
    stringBuffer.append(TEXT_1357);
    stringBuffer.append(safeNameAccessor);
    stringBuffer.append(TEXT_1358);
    }
    } else {
    if (genClass.isField(genFeature)) {
    stringBuffer.append(TEXT_1359);
    stringBuffer.append(safeNameAccessor);
    stringBuffer.append(TEXT_1360);
    stringBuffer.append(safeNameAccessor);
    stringBuffer.append(TEXT_1361);
    } else {
    if (genFeature.isField() && genClass.getImplementingGenModel(genFeature).isVirtualDelegation()) {
    stringBuffer.append(TEXT_1362);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_1363);
    stringBuffer.append(safeNameAccessor);
    stringBuffer.append(TEXT_1364);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_1365);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(positiveOffsetCorrection);
    stringBuffer.append(TEXT_1366);
    stringBuffer.append(safeNameAccessor);
    stringBuffer.append(TEXT_1367);
    stringBuffer.append(safeNameAccessor);
    stringBuffer.append(TEXT_1368);
    } else {
    stringBuffer.append(TEXT_1369);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_1370);
    }
    }
    }
    } else if (genFeature.isUnsettable()) {
    stringBuffer.append(TEXT_1371);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_1372);
    } else if (genFeature.isResolveProxies()) {
    if (genClass.isField(genFeature)) {
    stringBuffer.append(TEXT_1373);
    stringBuffer.append(safeNameAccessor);
    stringBuffer.append(TEXT_1374);
    } else {
    if (genFeature.isField() && genClass.getImplementingGenModel(genFeature).isVirtualDelegation()) {
    stringBuffer.append(TEXT_1375);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(positiveOffsetCorrection);
    stringBuffer.append(TEXT_1376);
    } else {
    stringBuffer.append(TEXT_1377);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_1378);
    }
    }
    } else if (!genFeature.hasEDefault()) {
    if (genClass.isField(genFeature)) {
    stringBuffer.append(TEXT_1379);
    stringBuffer.append(safeNameAccessor);
    stringBuffer.append(TEXT_1380);
    } else {
    if (genFeature.isField() && genClass.getImplementingGenModel(genFeature).isVirtualDelegation()) {
    stringBuffer.append(TEXT_1381);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(positiveOffsetCorrection);
    stringBuffer.append(TEXT_1382);
    } else {
    stringBuffer.append(TEXT_1383);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_1384);
    }
    }
    } else if (genFeature.isPrimitiveType() || genFeature.isEnumType()) {
    if (genClass.isField(genFeature)) {
    if (genClass.isFlag(genFeature)) {
    if (genFeature.isBooleanType()) {
    stringBuffer.append(TEXT_1385);
    stringBuffer.append(genClass.getFlagsField(genFeature));
    stringBuffer.append(TEXT_1386);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_1387);
    stringBuffer.append(genFeature.getEDefault());
    stringBuffer.append(TEXT_1388);
    } else {
    stringBuffer.append(TEXT_1389);
    stringBuffer.append(genClass.getFlagsField(genFeature));
    stringBuffer.append(TEXT_1390);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_1391);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_1392);
    }
    } else {
    stringBuffer.append(TEXT_1393);
    stringBuffer.append(safeNameAccessor);
    stringBuffer.append(TEXT_1394);
    stringBuffer.append(genFeature.getEDefault());
    stringBuffer.append(TEXT_1395);
    }
    } else {
    if (genFeature.isEnumType() && genFeature.isField() && genClass.getImplementingGenModel(genFeature).isVirtualDelegation()) {
    stringBuffer.append(TEXT_1396);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(positiveOffsetCorrection);
    stringBuffer.append(TEXT_1397);
    stringBuffer.append(genFeature.getEDefault());
    stringBuffer.append(TEXT_1398);
    stringBuffer.append(genFeature.getEDefault());
    stringBuffer.append(TEXT_1399);
    } else {
    stringBuffer.append(TEXT_1400);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_1401);
    stringBuffer.append(genFeature.getEDefault());
    stringBuffer.append(TEXT_1402);
    }
    }
    } else {//datatype
    if (genClass.isField(genFeature)) {
    stringBuffer.append(TEXT_1403);
    stringBuffer.append(genFeature.getEDefault());
    stringBuffer.append(TEXT_1404);
    stringBuffer.append(safeNameAccessor);
    stringBuffer.append(TEXT_1405);
    stringBuffer.append(genFeature.getEDefault());
    stringBuffer.append(TEXT_1406);
    stringBuffer.append(safeNameAccessor);
    stringBuffer.append(TEXT_1407);
    } else {
    if (genFeature.isField() && genClass.getImplementingGenModel(genFeature).isVirtualDelegation()) {
    stringBuffer.append(TEXT_1408);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_1409);
    stringBuffer.append(safeNameAccessor);
    stringBuffer.append(TEXT_1410);
    stringBuffer.append(genFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_1411);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(positiveOffsetCorrection);
    stringBuffer.append(TEXT_1412);
    stringBuffer.append(genFeature.getEDefault());
    stringBuffer.append(TEXT_1413);
    stringBuffer.append(genFeature.getEDefault());
    stringBuffer.append(TEXT_1414);
    stringBuffer.append(safeNameAccessor);
    stringBuffer.append(TEXT_1415);
    stringBuffer.append(genFeature.getEDefault());
    stringBuffer.append(TEXT_1416);
    stringBuffer.append(safeNameAccessor);
    stringBuffer.append(TEXT_1417);
    } else {
    stringBuffer.append(TEXT_1418);
    stringBuffer.append(genFeature.getEDefault());
    stringBuffer.append(TEXT_1419);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_1420);
    stringBuffer.append(genFeature.getEDefault());
    stringBuffer.append(TEXT_1421);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_1422);
    }
    }
    }
    }
    stringBuffer.append(TEXT_1423);
    if (genModel.isMinimalReflectiveMethods()) {
    stringBuffer.append(TEXT_1424);
    } else {
    stringBuffer.append(TEXT_1425);
    }
    stringBuffer.append(TEXT_1426);
    //Class/eIsSet.override.javajetinc
    }
    if (isImplementation && (!genClass.getMixinGenFeatures().isEmpty() || genClass.hasOffsetCorrection() && !genClass.getGenFeatures().isEmpty())) {
    if (!genClass.getMixinGenFeatures().isEmpty()) {
    stringBuffer.append(TEXT_1427);
    if (genModel.useClassOverrideAnnotation()) {
    stringBuffer.append(TEXT_1428);
    }
    stringBuffer.append(TEXT_1429);
    stringBuffer.append(singleWildcard);
    stringBuffer.append(TEXT_1430);
    for (GenClass mixinGenClass : genClass.getMixinGenClasses()) {
    stringBuffer.append(TEXT_1431);
    stringBuffer.append(mixinGenClass.getRawImportedInterfaceName());
    stringBuffer.append(TEXT_1432);
    stringBuffer.append(negativeOffsetCorrection);
    stringBuffer.append(TEXT_1433);
    for (GenFeature genFeature : mixinGenClass.getGenFeatures()) {
    stringBuffer.append(TEXT_1434);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(TEXT_1435);
    stringBuffer.append(mixinGenClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(TEXT_1436);
    }
    stringBuffer.append(TEXT_1437);
    }
    stringBuffer.append(TEXT_1438);
    }
    stringBuffer.append(TEXT_1439);
    if (genModel.useClassOverrideAnnotation()) {
    stringBuffer.append(TEXT_1440);
    }
    stringBuffer.append(TEXT_1441);
    stringBuffer.append(singleWildcard);
    stringBuffer.append(TEXT_1442);
    for (GenClass mixinGenClass : genClass.getMixinGenClasses()) {
    stringBuffer.append(TEXT_1443);
    stringBuffer.append(mixinGenClass.getRawImportedInterfaceName());
    stringBuffer.append(TEXT_1444);
    for (GenFeature genFeature : mixinGenClass.getGenFeatures()) {
    stringBuffer.append(TEXT_1445);
    stringBuffer.append(mixinGenClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(TEXT_1446);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(positiveOffsetCorrection);
    stringBuffer.append(TEXT_1447);
    }
    stringBuffer.append(TEXT_1448);
    }
    if (genClass.hasOffsetCorrection() && !genClass.getGenFeatures().isEmpty()) {
    stringBuffer.append(TEXT_1449);
    stringBuffer.append(genClass.getRawImportedInterfaceName());
    stringBuffer.append(TEXT_1450);
    stringBuffer.append(negativeOffsetCorrection);
    stringBuffer.append(TEXT_1451);
    for (GenFeature genFeature : genClass.getGenFeatures()) {
    stringBuffer.append(TEXT_1452);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(TEXT_1453);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(positiveOffsetCorrection);
    stringBuffer.append(TEXT_1454);
    }
    stringBuffer.append(TEXT_1455);
    }
    stringBuffer.append(TEXT_1456);
    }
    if (isImplementation && genModel.isVirtualDelegation()) { String eVirtualValuesField = genClass.getEVirtualValuesField();
    if (eVirtualValuesField != null) {
    stringBuffer.append(TEXT_1457);
    if (genModel.useClassOverrideAnnotation()) {
    stringBuffer.append(TEXT_1458);
    }
    stringBuffer.append(TEXT_1459);
    stringBuffer.append(eVirtualValuesField);
    stringBuffer.append(TEXT_1460);
    if (genModel.useClassOverrideAnnotation()) {
    stringBuffer.append(TEXT_1461);
    }
    stringBuffer.append(TEXT_1462);
    stringBuffer.append(eVirtualValuesField);
    stringBuffer.append(TEXT_1463);
    }
    { List<String> eVirtualIndexBitFields = genClass.getEVirtualIndexBitFields(new ArrayList<String>());
    if (!eVirtualIndexBitFields.isEmpty()) { List<String> allEVirtualIndexBitFields = genClass.getAllEVirtualIndexBitFields(new ArrayList<String>());
    stringBuffer.append(TEXT_1464);
    if (genModel.useClassOverrideAnnotation()) {
    stringBuffer.append(TEXT_1465);
    }
    stringBuffer.append(TEXT_1466);
    for (int i = 0; i < allEVirtualIndexBitFields.size(); i++) {
    stringBuffer.append(TEXT_1467);
    stringBuffer.append(i);
    stringBuffer.append(TEXT_1468);
    stringBuffer.append(allEVirtualIndexBitFields.get(i));
    stringBuffer.append(TEXT_1469);
    }
    stringBuffer.append(TEXT_1470);
    if (genModel.useClassOverrideAnnotation()) {
    stringBuffer.append(TEXT_1471);
    }
    stringBuffer.append(TEXT_1472);
    for (int i = 0; i < allEVirtualIndexBitFields.size(); i++) {
    stringBuffer.append(TEXT_1473);
    stringBuffer.append(i);
    stringBuffer.append(TEXT_1474);
    stringBuffer.append(allEVirtualIndexBitFields.get(i));
    stringBuffer.append(TEXT_1475);
    }
    stringBuffer.append(TEXT_1476);
    }
    }
    }
    if (!genClass.hasImplementedToStringGenOperation() && isImplementation && !genModel.isReflectiveDelegation() && !genModel.isDynamicDelegation() && !genClass.getToStringGenFeatures().isEmpty()) {
    stringBuffer.append(TEXT_1477);
    if (genModel.useClassOverrideAnnotation()) {
    stringBuffer.append(TEXT_1478);
    }
    stringBuffer.append(TEXT_1479);
    { boolean first = true;
    for (GenFeature genFeature : genClass.getToStringGenFeatures()) {
    if (first) { first = false;
    stringBuffer.append(TEXT_1480);
    stringBuffer.append(genFeature.getName());
    stringBuffer.append(TEXT_1481);
    stringBuffer.append(genModel.getNonNLS());
    } else {
    stringBuffer.append(TEXT_1482);
    stringBuffer.append(genFeature.getName());
    stringBuffer.append(TEXT_1483);
    stringBuffer.append(genModel.getNonNLS());
    }
    if (genFeature.isUnsettable() && !genFeature.isListType()) {
    if (genModel.isVirtualDelegation() && !genFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_1484);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(positiveOffsetCorrection);
    stringBuffer.append(TEXT_1485);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(positiveOffsetCorrection);
    stringBuffer.append(TEXT_1486);
    stringBuffer.append(genModel.getNonNLS());
    } else {
    if (genClass.isFlag(genFeature)) {
    if (genFeature.isBooleanType()) {
    stringBuffer.append(TEXT_1487);
    if (genClass.isESetFlag(genFeature)) {
    stringBuffer.append(TEXT_1488);
    stringBuffer.append(genClass.getESetFlagsField(genFeature));
    stringBuffer.append(TEXT_1489);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_1490);
    } else {
    stringBuffer.append(genFeature.getUncapName());
    stringBuffer.append(TEXT_1491);
    }
    stringBuffer.append(TEXT_1492);
    stringBuffer.append(genClass.getFlagsField(genFeature));
    stringBuffer.append(TEXT_1493);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_1494);
    stringBuffer.append(genModel.getNonNLS());
    } else {
    stringBuffer.append(TEXT_1495);
    if (genClass.isESetFlag(genFeature)) {
    stringBuffer.append(TEXT_1496);
    stringBuffer.append(genClass.getESetFlagsField(genFeature));
    stringBuffer.append(TEXT_1497);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_1498);
    } else {
    stringBuffer.append(genFeature.getUncapName());
    stringBuffer.append(TEXT_1499);
    }
    stringBuffer.append(TEXT_1500);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_1501);
    stringBuffer.append(genClass.getFlagsField(genFeature));
    stringBuffer.append(TEXT_1502);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_1503);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_1504);
    stringBuffer.append(genModel.getNonNLS());
    }
    } else {
    stringBuffer.append(TEXT_1505);
    if (genClass.isESetFlag(genFeature)) {
    stringBuffer.append(TEXT_1506);
    stringBuffer.append(genClass.getESetFlagsField(genFeature));
    stringBuffer.append(TEXT_1507);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_1508);
    } else {
    stringBuffer.append(genFeature.getUncapName());
    stringBuffer.append(TEXT_1509);
    }
    stringBuffer.append(TEXT_1510);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_1511);
    stringBuffer.append(genModel.getNonNLS());
    }
    }
    } else {
    if (genModel.isVirtualDelegation() && !genFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_1512);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(positiveOffsetCorrection);
    if (!genFeature.isListType() && !genFeature.isReferenceType()){
    stringBuffer.append(TEXT_1513);
    stringBuffer.append(genFeature.getEDefault());
    }
    stringBuffer.append(TEXT_1514);
    } else {
    if (genClass.isFlag(genFeature)) {
    if (genFeature.isBooleanType()) {
    stringBuffer.append(TEXT_1515);
    stringBuffer.append(genClass.getFlagsField(genFeature));
    stringBuffer.append(TEXT_1516);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_1517);
    } else {
    stringBuffer.append(TEXT_1518);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_1519);
    stringBuffer.append(genClass.getFlagsField(genFeature));
    stringBuffer.append(TEXT_1520);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_1521);
    stringBuffer.append(genFeature.getUpperName());
    stringBuffer.append(TEXT_1522);
    }
    } else {
    stringBuffer.append(TEXT_1523);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_1524);
    }
    }
    }
    }
    }
    stringBuffer.append(TEXT_1525);
    }
    if (isImplementation && genClass.isMapEntry()) { GenFeature keyFeature = genClass.getMapEntryKeyFeature(); GenFeature valueFeature = genClass.getMapEntryValueFeature();
    String objectType = genModel.getImportedName("java.lang.Object");
    String keyType = isJDK50 ? keyFeature.getObjectType(genClass) : objectType;
    String valueType = isJDK50 ? valueFeature.getObjectType(genClass) : objectType;
    String eMapType = genModel.getImportedName("org.eclipse.emf.common.util.EMap") + (isJDK50 ? "<" + keyType + ", " + valueType + ">" : "");
    stringBuffer.append(TEXT_1526);
    stringBuffer.append(objectType);
    stringBuffer.append(TEXT_1527);
    stringBuffer.append(keyType);
    stringBuffer.append(TEXT_1528);
    if (!isJDK50 && keyFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_1529);
    stringBuffer.append(keyFeature.getObjectType(genClass));
    stringBuffer.append(TEXT_1530);
    } else {
    stringBuffer.append(TEXT_1531);
    }
    stringBuffer.append(TEXT_1532);
    stringBuffer.append(keyType);
    stringBuffer.append(TEXT_1533);
    if (keyFeature.isListType()) {
    stringBuffer.append(TEXT_1534);
    if (!genModel.useGenerics()) {
    stringBuffer.append(TEXT_1535);
    stringBuffer.append(genModel.getImportedName("java.util.Collection"));
    stringBuffer.append(TEXT_1536);
    }
    stringBuffer.append(TEXT_1537);
    } else if (isJDK50) {
    stringBuffer.append(TEXT_1538);
    } else if (keyFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_1539);
    stringBuffer.append(keyFeature.getObjectType(genClass));
    stringBuffer.append(TEXT_1540);
    stringBuffer.append(keyFeature.getPrimitiveValueFunction());
    stringBuffer.append(TEXT_1541);
    } else {
    stringBuffer.append(TEXT_1542);
    stringBuffer.append(keyFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_1543);
    }
    stringBuffer.append(TEXT_1544);
    stringBuffer.append(valueType);
    stringBuffer.append(TEXT_1545);
    if (!isJDK50 && valueFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_1546);
    stringBuffer.append(valueFeature.getObjectType(genClass));
    stringBuffer.append(TEXT_1547);
    } else {
    stringBuffer.append(TEXT_1548);
    }
    stringBuffer.append(TEXT_1549);
    stringBuffer.append(valueType);
    stringBuffer.append(TEXT_1550);
    stringBuffer.append(valueType);
    stringBuffer.append(TEXT_1551);
    stringBuffer.append(valueType);
    stringBuffer.append(TEXT_1552);
    if (valueFeature.isListType()) {
    stringBuffer.append(TEXT_1553);
    if (!genModel.useGenerics()) {
    stringBuffer.append(TEXT_1554);
    stringBuffer.append(genModel.getImportedName("java.util.Collection"));
    stringBuffer.append(TEXT_1555);
    }
    stringBuffer.append(TEXT_1556);
    } else if (isJDK50) {
    stringBuffer.append(TEXT_1557);
    } else if (valueFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_1558);
    stringBuffer.append(valueFeature.getObjectType(genClass));
    stringBuffer.append(TEXT_1559);
    stringBuffer.append(valueFeature.getPrimitiveValueFunction());
    stringBuffer.append(TEXT_1560);
    } else {
    stringBuffer.append(TEXT_1561);
    stringBuffer.append(valueFeature.getImportedType(genClass));
    stringBuffer.append(TEXT_1562);
    }
    stringBuffer.append(TEXT_1563);
    if (genModel.useGenerics()) {
    stringBuffer.append(TEXT_1564);
    }
    stringBuffer.append(TEXT_1565);
    stringBuffer.append(eMapType);
    stringBuffer.append(TEXT_1566);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.EObject"));
    stringBuffer.append(TEXT_1567);
    stringBuffer.append(eMapType);
    stringBuffer.append(TEXT_1568);
    }
    stringBuffer.append(TEXT_1569);
    stringBuffer.append(isInterface ? " " + genClass.getInterfaceName() : genClass.getClassName());
    // TODO fix the space above
    genModel.emitSortedImports();
    stringBuffer.append(TEXT_1570);
    return stringBuffer.toString();
  }
}
