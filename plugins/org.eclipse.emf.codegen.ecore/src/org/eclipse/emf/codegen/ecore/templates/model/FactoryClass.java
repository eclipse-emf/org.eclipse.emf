package org.eclipse.emf.codegen.ecore.templates.model;

import java.util.*;
import org.eclipse.emf.codegen.ecore.genmodel.*;
import org.eclipse.emf.codegen.ecore.genmodel.util.GenModelUtil;

public class FactoryClass
{
  protected static String nl;
  public static synchronized FactoryClass create(String lineSeparator)
  {
    nl = lineSeparator;
    FactoryClass result = new FactoryClass();
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
  protected final String TEXT_11 = NL + "/**" + NL + " * <!-- begin-user-doc -->" + NL + " * The <b>Factory</b> for the model." + NL + " * It provides a create method for each non-abstract class of the model." + NL + " * <!-- end-user-doc -->";
  protected final String TEXT_12 = NL + " * @see ";
  protected final String TEXT_13 = NL + " * ";
  protected final String TEXT_14 = NL + " * @generated" + NL + " */";
  protected final String TEXT_15 = NL + "/**" + NL + " * <!-- begin-user-doc -->" + NL + " * An implementation of the model <b>Factory</b>." + NL + " * <!-- end-user-doc -->";
  protected final String TEXT_16 = NL + " * ";
  protected final String TEXT_17 = NL + " * @generated" + NL + " */";
  protected final String TEXT_18 = NL + "@Deprecated";
  protected final String TEXT_19 = NL + "@SuppressWarnings(\"deprecation\")";
  protected final String TEXT_20 = NL + "public class ";
  protected final String TEXT_21 = " extends ";
  protected final String TEXT_22 = " implements ";
  protected final String TEXT_23 = NL + "public interface ";
  protected final String TEXT_24 = " extends ";
  protected final String TEXT_25 = NL + "{";
  protected final String TEXT_26 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */" + NL + "\t";
  protected final String TEXT_27 = " copyright = ";
  protected final String TEXT_28 = ";";
  protected final String TEXT_29 = NL;
  protected final String TEXT_30 = NL + "\t/**" + NL + "\t * The singleton instance of the factory." + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */" + NL + "\t";
  protected final String TEXT_31 = " eINSTANCE = init();" + NL;
  protected final String TEXT_32 = NL + "\t/**" + NL + "\t * The singleton instance of the factory." + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */" + NL + "\t";
  protected final String TEXT_33 = " INSTANCE = ";
  protected final String TEXT_34 = ".eINSTANCE;" + NL;
  protected final String TEXT_35 = NL + "\t/**" + NL + "\t * The singleton instance of the factory." + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */" + NL + "\t";
  protected final String TEXT_36 = " eINSTANCE = ";
  protected final String TEXT_37 = ".init();" + NL;
  protected final String TEXT_38 = NL + "\t/**" + NL + "\t * Creates the default factory implementation." + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */";
  protected final String TEXT_39 = NL + "\tpublic static ";
  protected final String TEXT_40 = " init()" + NL + "\t{" + NL + "\t\ttry" + NL + "\t\t{" + NL + "\t\t\t";
  protected final String TEXT_41 = " the";
  protected final String TEXT_42 = " = (";
  protected final String TEXT_43 = ")";
  protected final String TEXT_44 = ".Registry.INSTANCE.getEFactory(";
  protected final String TEXT_45 = ".eNS_URI);" + NL + "\t\t\tif (the";
  protected final String TEXT_46 = " != null)" + NL + "\t\t\t{" + NL + "\t\t\t\treturn the";
  protected final String TEXT_47 = ";" + NL + "\t\t\t}" + NL + "\t\t}" + NL + "\t\tcatch (Exception exception)" + NL + "\t\t{" + NL + "\t\t\t";
  protected final String TEXT_48 = ".INSTANCE.log(exception);" + NL + "\t\t}" + NL + "\t\treturn new ";
  protected final String TEXT_49 = "();" + NL + "\t}" + NL + "" + NL + "\t/**" + NL + "\t * Creates an instance of the factory." + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */" + NL + "\tpublic ";
  protected final String TEXT_50 = "()" + NL + "\t{" + NL + "\t\tsuper();" + NL + "\t}" + NL + "" + NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */";
  protected final String TEXT_51 = NL + "\t@Override";
  protected final String TEXT_52 = NL + "\tpublic EObject create(EClass eClass)" + NL + "\t{" + NL + "\t\tswitch (eClass.getClassifierID())" + NL + "\t\t{";
  protected final String TEXT_53 = NL + "\t\t\tcase ";
  protected final String TEXT_54 = ".";
  protected final String TEXT_55 = ": return ";
  protected final String TEXT_56 = "create";
  protected final String TEXT_57 = "();";
  protected final String TEXT_58 = NL + "\t\t\tdefault:" + NL + "\t\t\t\tthrow new IllegalArgumentException(\"The class '\" + eClass.getName() + \"' is not a valid classifier\");";
  protected final String TEXT_59 = NL + "\t\t}" + NL + "\t}" + NL;
  protected final String TEXT_60 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */";
  protected final String TEXT_61 = NL + "\t@Override";
  protected final String TEXT_62 = NL + "\tpublic Object createFromString(";
  protected final String TEXT_63 = " eDataType, String initialValue)" + NL + "\t{" + NL + "\t\tswitch (eDataType.getClassifierID())" + NL + "\t\t{";
  protected final String TEXT_64 = NL + "\t\t\tcase ";
  protected final String TEXT_65 = ".";
  protected final String TEXT_66 = ":" + NL + "\t\t\t\treturn create";
  protected final String TEXT_67 = "FromString(eDataType, initialValue);";
  protected final String TEXT_68 = NL + "\t\t\tdefault:" + NL + "\t\t\t\tthrow new IllegalArgumentException(\"The datatype '\" + eDataType.getName() + \"' is not a valid classifier\");";
  protected final String TEXT_69 = NL + "\t\t}" + NL + "\t}" + NL + "" + NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */";
  protected final String TEXT_70 = NL + "\t@Override";
  protected final String TEXT_71 = NL + "\tpublic String convertToString(";
  protected final String TEXT_72 = " eDataType, Object instanceValue)" + NL + "\t{" + NL + "\t\tswitch (eDataType.getClassifierID())" + NL + "\t\t{";
  protected final String TEXT_73 = NL + "\t\t\tcase ";
  protected final String TEXT_74 = ".";
  protected final String TEXT_75 = ":" + NL + "\t\t\t\treturn convert";
  protected final String TEXT_76 = "ToString(eDataType, instanceValue);";
  protected final String TEXT_77 = NL + "\t\t\tdefault:" + NL + "\t\t\t\tthrow new IllegalArgumentException(\"The datatype '\" + eDataType.getName() + \"' is not a valid classifier\");";
  protected final String TEXT_78 = NL + "\t\t}" + NL + "\t}" + NL;
  protected final String TEXT_79 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->";
  protected final String TEXT_80 = NL + "\t * ";
  protected final String TEXT_81 = NL + "\t * @generated" + NL + "\t */";
  protected final String TEXT_82 = NL + "\t@Deprecated";
  protected final String TEXT_83 = NL + "\tpublic ";
  protected final String TEXT_84 = " create";
  protected final String TEXT_85 = "()" + NL + "\t{";
  protected final String TEXT_86 = NL + "\t\t";
  protected final String TEXT_87 = " ";
  protected final String TEXT_88 = " = ";
  protected final String TEXT_89 = "super.create(";
  protected final String TEXT_90 = ");";
  protected final String TEXT_91 = NL + "\t\t";
  protected final String TEXT_92 = " ";
  protected final String TEXT_93 = " = new ";
  protected final String TEXT_94 = "()";
  protected final String TEXT_95 = "{}";
  protected final String TEXT_96 = ";";
  protected final String TEXT_97 = NL + "\t\treturn ";
  protected final String TEXT_98 = ";" + NL + "\t}" + NL;
  protected final String TEXT_99 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->";
  protected final String TEXT_100 = NL + "\t * ";
  protected final String TEXT_101 = NL + "\t * @generated" + NL + "\t */";
  protected final String TEXT_102 = NL + "\t@SuppressWarnings(\"unchecked\")";
  protected final String TEXT_103 = NL + "\t@Deprecated";
  protected final String TEXT_104 = NL + "\tpublic ";
  protected final String TEXT_105 = " create";
  protected final String TEXT_106 = "(";
  protected final String TEXT_107 = "final ";
  protected final String TEXT_108 = "String ";
  protected final String TEXT_109 = "it";
  protected final String TEXT_110 = "literal";
  protected final String TEXT_111 = ")" + NL + "\t{";
  protected final String TEXT_112 = NL + "\t\t";
  protected final String TEXT_113 = NL + "\t\t";
  protected final String TEXT_114 = " result = ";
  protected final String TEXT_115 = ".get(literal);" + NL + "\t\tif (result == null) throw new IllegalArgumentException(\"The value '\" + literal + \"' is not a valid enumerator of '\" + ";
  protected final String TEXT_116 = ".getName() + \"'\");";
  protected final String TEXT_117 = NL + "\t\treturn result;";
  protected final String TEXT_118 = NL + "\t\treturn new ";
  protected final String TEXT_119 = "(create";
  protected final String TEXT_120 = "(literal));";
  protected final String TEXT_121 = NL + "\t\treturn create";
  protected final String TEXT_122 = "(literal);";
  protected final String TEXT_123 = NL + "\t\treturn new ";
  protected final String TEXT_124 = "(";
  protected final String TEXT_125 = ".create";
  protected final String TEXT_126 = "(literal));";
  protected final String TEXT_127 = NL + "\t\treturn ";
  protected final String TEXT_128 = ".create";
  protected final String TEXT_129 = "(literal);";
  protected final String TEXT_130 = NL + "\t\treturn ";
  protected final String TEXT_131 = "(";
  protected final String TEXT_132 = ")";
  protected final String TEXT_133 = ".createFromString(";
  protected final String TEXT_134 = ", literal);";
  protected final String TEXT_135 = NL + "\t\tif (literal == null) return null;" + NL + "\t\t";
  protected final String TEXT_136 = " result = new ";
  protected final String TEXT_137 = "<";
  protected final String TEXT_138 = ">";
  protected final String TEXT_139 = "();";
  protected final String TEXT_140 = NL + "\t\tfor (";
  protected final String TEXT_141 = " stringTokenizer = new ";
  protected final String TEXT_142 = "(literal); stringTokenizer.hasMoreTokens(); )";
  protected final String TEXT_143 = NL + "\t\tfor (String item : split(literal))";
  protected final String TEXT_144 = NL + "\t\t{";
  protected final String TEXT_145 = NL + "\t\t\tString item = stringTokenizer.nextToken();";
  protected final String TEXT_146 = NL + "\t\t\tresult.add(create";
  protected final String TEXT_147 = "(item));";
  protected final String TEXT_148 = NL + "\t\t\tresult.add(create";
  protected final String TEXT_149 = "FromString(";
  protected final String TEXT_150 = ", item));";
  protected final String TEXT_151 = NL + "\t\t\tresult.add(";
  protected final String TEXT_152 = ".create";
  protected final String TEXT_153 = "(item));";
  protected final String TEXT_154 = NL + "\t\t\tresult.add(";
  protected final String TEXT_155 = ".createFromString(";
  protected final String TEXT_156 = ", item));";
  protected final String TEXT_157 = NL + "\t\t}" + NL + "\t\treturn result;";
  protected final String TEXT_158 = NL + "\t\tif (literal == null) return ";
  protected final String TEXT_159 = ";" + NL + "\t\t";
  protected final String TEXT_160 = " result = ";
  protected final String TEXT_161 = ";" + NL + "\t\tRuntimeException exception = null;";
  protected final String TEXT_162 = NL + "\t\ttry" + NL + "\t\t{";
  protected final String TEXT_163 = NL + "\t\t\tresult = create";
  protected final String TEXT_164 = "(literal);";
  protected final String TEXT_165 = NL + "\t\t\tresult = (";
  protected final String TEXT_166 = ")create";
  protected final String TEXT_167 = "FromString(";
  protected final String TEXT_168 = ", literal);";
  protected final String TEXT_169 = NL + "\t\t\tresult = ";
  protected final String TEXT_170 = ".create";
  protected final String TEXT_171 = "(literal);";
  protected final String TEXT_172 = NL + "\t\t\tresult = (";
  protected final String TEXT_173 = ")";
  protected final String TEXT_174 = ".createFromString(";
  protected final String TEXT_175 = ", literal);";
  protected final String TEXT_176 = NL + "\t\t\tif (";
  protected final String TEXT_177 = "result != null && ";
  protected final String TEXT_178 = ".INSTANCE.validate(";
  protected final String TEXT_179 = ", ";
  protected final String TEXT_180 = "new ";
  protected final String TEXT_181 = "(result)";
  protected final String TEXT_182 = "result";
  protected final String TEXT_183 = ", null, null))" + NL + "\t\t\t{" + NL + "\t\t\t\treturn result;" + NL + "\t\t\t}" + NL + "\t\t}" + NL + "\t\tcatch (RuntimeException e)" + NL + "\t\t{" + NL + "\t\t\texception = e;" + NL + "\t\t}";
  protected final String TEXT_184 = NL + "\t\tif (";
  protected final String TEXT_185 = "result != null || ";
  protected final String TEXT_186 = "exception == null) return result;" + NL + "    " + NL + "\t\tthrow exception;";
  protected final String TEXT_187 = NL + "\t\treturn (";
  protected final String TEXT_188 = ")super.createFromString(literal);";
  protected final String TEXT_189 = NL + "\t\t// TODO: implement this method" + NL + "\t\t// Ensure that you remove @generated or mark it @generated NOT" + NL + "\t\tthrow new ";
  protected final String TEXT_190 = "();";
  protected final String TEXT_191 = NL + "\t\treturn ((";
  protected final String TEXT_192 = ")super.createFromString(";
  protected final String TEXT_193 = ", literal)).";
  protected final String TEXT_194 = "();";
  protected final String TEXT_195 = NL + "\t\treturn ";
  protected final String TEXT_196 = "(";
  protected final String TEXT_197 = ")";
  protected final String TEXT_198 = "super.createFromString(";
  protected final String TEXT_199 = ", literal);";
  protected final String TEXT_200 = NL + "\t}" + NL;
  protected final String TEXT_201 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->";
  protected final String TEXT_202 = NL + "\t * ";
  protected final String TEXT_203 = NL + "\t * @generated" + NL + "\t */";
  protected final String TEXT_204 = NL + "\t@SuppressWarnings(\"unchecked\")";
  protected final String TEXT_205 = NL + "\t@Deprecated";
  protected final String TEXT_206 = NL + "\tpublic ";
  protected final String TEXT_207 = " create";
  protected final String TEXT_208 = "FromString(";
  protected final String TEXT_209 = " eDataType, String initialValue)" + NL + "\t{";
  protected final String TEXT_210 = NL + "\t\treturn create";
  protected final String TEXT_211 = "(initialValue);";
  protected final String TEXT_212 = NL + "\t\t";
  protected final String TEXT_213 = " result = ";
  protected final String TEXT_214 = ".get(initialValue);" + NL + "\t\tif (result == null) throw new IllegalArgumentException(\"The value '\" + initialValue + \"' is not a valid enumerator of '\" + eDataType.getName() + \"'\");";
  protected final String TEXT_215 = NL + "\t\treturn result;";
  protected final String TEXT_216 = NL + "\t\treturn ";
  protected final String TEXT_217 = "(";
  protected final String TEXT_218 = ")";
  protected final String TEXT_219 = "create";
  protected final String TEXT_220 = "FromString(";
  protected final String TEXT_221 = ", initialValue);";
  protected final String TEXT_222 = NL + "\t\treturn ";
  protected final String TEXT_223 = "(";
  protected final String TEXT_224 = ")";
  protected final String TEXT_225 = ".createFromString(";
  protected final String TEXT_226 = ", initialValue);";
  protected final String TEXT_227 = NL + "\t\treturn create";
  protected final String TEXT_228 = "(initialValue);";
  protected final String TEXT_229 = NL + "\t\tif (initialValue == null) return null;" + NL + "\t\t";
  protected final String TEXT_230 = " result = new ";
  protected final String TEXT_231 = "<";
  protected final String TEXT_232 = ">";
  protected final String TEXT_233 = "();";
  protected final String TEXT_234 = NL + "\t\tfor (";
  protected final String TEXT_235 = " stringTokenizer = new ";
  protected final String TEXT_236 = "(initialValue); stringTokenizer.hasMoreTokens(); )";
  protected final String TEXT_237 = NL + "\t\tfor (String item : split(initialValue))";
  protected final String TEXT_238 = NL + "\t\t{";
  protected final String TEXT_239 = NL + "\t\t\tString item = stringTokenizer.nextToken();";
  protected final String TEXT_240 = NL + "\t\t\tresult.add(create";
  protected final String TEXT_241 = "FromString(";
  protected final String TEXT_242 = ", item));";
  protected final String TEXT_243 = NL + "\t\t\tresult.add(";
  protected final String TEXT_244 = "(";
  protected final String TEXT_245 = ")";
  protected final String TEXT_246 = ".createFromString(";
  protected final String TEXT_247 = ", item));";
  protected final String TEXT_248 = NL + "\t\t}" + NL + "\t\treturn result;";
  protected final String TEXT_249 = NL + "\t\treturn new ";
  protected final String TEXT_250 = "(create";
  protected final String TEXT_251 = "(initialValue));";
  protected final String TEXT_252 = NL + "\t\treturn create";
  protected final String TEXT_253 = "(initialValue);";
  protected final String TEXT_254 = NL + "\t\tif (initialValue == null) return null;" + NL + "\t\t";
  protected final String TEXT_255 = " result = null;" + NL + "\t\tRuntimeException exception = null;";
  protected final String TEXT_256 = NL + "\t\ttry" + NL + "\t\t{";
  protected final String TEXT_257 = NL + "\t\t\tresult = ";
  protected final String TEXT_258 = "(";
  protected final String TEXT_259 = ")";
  protected final String TEXT_260 = "create";
  protected final String TEXT_261 = "FromString(";
  protected final String TEXT_262 = ", initialValue);";
  protected final String TEXT_263 = NL + "\t\t\tresult = ";
  protected final String TEXT_264 = "(";
  protected final String TEXT_265 = ")";
  protected final String TEXT_266 = ".createFromString(";
  protected final String TEXT_267 = ", initialValue);";
  protected final String TEXT_268 = NL + "\t\t\tif (result != null && ";
  protected final String TEXT_269 = ".INSTANCE.validate(eDataType, result, null, null))" + NL + "\t\t\t{" + NL + "\t\t\t\treturn result;" + NL + "\t\t\t}" + NL + "\t\t}" + NL + "\t\tcatch (RuntimeException e)" + NL + "\t\t{" + NL + "\t\t\texception = e;" + NL + "\t\t}";
  protected final String TEXT_270 = NL + "\t\tif (result != null || exception == null) return result;" + NL + "    " + NL + "\t\tthrow exception;";
  protected final String TEXT_271 = NL + "\t\treturn create";
  protected final String TEXT_272 = "(initialValue);";
  protected final String TEXT_273 = NL + "\t\treturn ";
  protected final String TEXT_274 = "(";
  protected final String TEXT_275 = ")";
  protected final String TEXT_276 = "super.createFromString(initialValue);";
  protected final String TEXT_277 = NL + "\t\t// TODO: implement this method" + NL + "\t\t// Ensure that you remove @generated or mark it @generated NOT" + NL + "\t\tthrow new ";
  protected final String TEXT_278 = "();";
  protected final String TEXT_279 = NL + "\t\treturn ";
  protected final String TEXT_280 = "(";
  protected final String TEXT_281 = ")";
  protected final String TEXT_282 = "super.createFromString(eDataType, initialValue);";
  protected final String TEXT_283 = NL + "\t}" + NL;
  protected final String TEXT_284 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->";
  protected final String TEXT_285 = NL + "\t * ";
  protected final String TEXT_286 = NL + "\t * @generated" + NL + "\t */";
  protected final String TEXT_287 = NL + "\t@Deprecated";
  protected final String TEXT_288 = NL + "\tpublic String convert";
  protected final String TEXT_289 = "(";
  protected final String TEXT_290 = "final ";
  protected final String TEXT_291 = " ";
  protected final String TEXT_292 = "it";
  protected final String TEXT_293 = "instanceValue";
  protected final String TEXT_294 = ")" + NL + "\t{";
  protected final String TEXT_295 = NL + "\t\t";
  protected final String TEXT_296 = NL + "\t\treturn instanceValue == null ? null : instanceValue.toString();";
  protected final String TEXT_297 = NL + "\t\treturn instanceValue == null ? null : convert";
  protected final String TEXT_298 = "(instanceValue";
  protected final String TEXT_299 = ".";
  protected final String TEXT_300 = "()";
  protected final String TEXT_301 = ");";
  protected final String TEXT_302 = NL + "\t\treturn convert";
  protected final String TEXT_303 = "(instanceValue);";
  protected final String TEXT_304 = NL + "\t\treturn ";
  protected final String TEXT_305 = ".convert";
  protected final String TEXT_306 = "(instanceValue);";
  protected final String TEXT_307 = NL + "\t\treturn ";
  protected final String TEXT_308 = ".convertToString(";
  protected final String TEXT_309 = ", instanceValue);";
  protected final String TEXT_310 = NL + "\t\tif (instanceValue == null) return null;" + NL + "\t\tif (instanceValue.isEmpty()) return \"\";" + NL + "\t\t";
  protected final String TEXT_311 = " result = new ";
  protected final String TEXT_312 = "();";
  protected final String TEXT_313 = NL + "\t\tfor (";
  protected final String TEXT_314 = " i = instanceValue.iterator(); i.hasNext(); )";
  protected final String TEXT_315 = NL + "\t\tfor (";
  protected final String TEXT_316 = " item : instanceValue)";
  protected final String TEXT_317 = NL + "\t\t{";
  protected final String TEXT_318 = NL + "\t\t\tresult.append(convert";
  protected final String TEXT_319 = "((";
  protected final String TEXT_320 = ")";
  protected final String TEXT_321 = "));";
  protected final String TEXT_322 = NL + "\t\t\tresult.append(convert";
  protected final String TEXT_323 = "ToString(";
  protected final String TEXT_324 = ", ";
  protected final String TEXT_325 = "));";
  protected final String TEXT_326 = NL + "\t\t\tresult.append(";
  protected final String TEXT_327 = ".convert";
  protected final String TEXT_328 = "((";
  protected final String TEXT_329 = ")";
  protected final String TEXT_330 = "));";
  protected final String TEXT_331 = NL + "\t\t\tresult.append(";
  protected final String TEXT_332 = ".convertToString(";
  protected final String TEXT_333 = ", ";
  protected final String TEXT_334 = "));";
  protected final String TEXT_335 = NL + "\t\t\tresult.append(' ');" + NL + "\t\t}" + NL + "\t\treturn result.substring(0, result.length() - 1);";
  protected final String TEXT_336 = NL + "\t\tif (instanceValue == null) return null;";
  protected final String TEXT_337 = NL + "\t\tif (";
  protected final String TEXT_338 = ".isInstance(instanceValue))" + NL + "\t\t{" + NL + "\t\t\ttry" + NL + "\t\t\t{";
  protected final String TEXT_339 = NL + "\t\t\t\tString value = convert";
  protected final String TEXT_340 = "(instanceValue);";
  protected final String TEXT_341 = NL + "\t\t\t\tString value = convert";
  protected final String TEXT_342 = "(((";
  protected final String TEXT_343 = ")instanceValue).";
  protected final String TEXT_344 = "());";
  protected final String TEXT_345 = NL + "\t\t\t\tString value = convert";
  protected final String TEXT_346 = "((";
  protected final String TEXT_347 = ")instanceValue);";
  protected final String TEXT_348 = NL + "\t\t\t\tString value = convert";
  protected final String TEXT_349 = "ToString(";
  protected final String TEXT_350 = ", instanceValue);";
  protected final String TEXT_351 = NL + "\t\t\t\tString value = ";
  protected final String TEXT_352 = ".convert";
  protected final String TEXT_353 = "((";
  protected final String TEXT_354 = ")instanceValue);";
  protected final String TEXT_355 = NL + "\t\t\t\tString value = ";
  protected final String TEXT_356 = ".convertToString(";
  protected final String TEXT_357 = ", instanceValue);";
  protected final String TEXT_358 = NL + "\t\t\t\tif (value != null) return value;" + NL + "\t\t\t}" + NL + "\t\t\tcatch (Exception e)" + NL + "\t\t\t{" + NL + "\t\t\t\t// Keep trying other member types until all have failed." + NL + "\t\t\t}" + NL + "\t\t}";
  protected final String TEXT_359 = NL + "\t\ttry" + NL + "\t\t{";
  protected final String TEXT_360 = NL + "\t\t\tString value = convert";
  protected final String TEXT_361 = "(instanceValue);";
  protected final String TEXT_362 = NL + "\t\t\tString value = convert";
  protected final String TEXT_363 = "ToString(";
  protected final String TEXT_364 = ", ";
  protected final String TEXT_365 = "new ";
  protected final String TEXT_366 = "(instanceValue)";
  protected final String TEXT_367 = "instanceValue";
  protected final String TEXT_368 = ");";
  protected final String TEXT_369 = NL + "\t\t\tString value = ";
  protected final String TEXT_370 = ".convert";
  protected final String TEXT_371 = "(instanceValue);";
  protected final String TEXT_372 = NL + "\t\t\tString value = ";
  protected final String TEXT_373 = ".convertToString(";
  protected final String TEXT_374 = ", ";
  protected final String TEXT_375 = "new ";
  protected final String TEXT_376 = "(instanceValue)";
  protected final String TEXT_377 = "instanceValue";
  protected final String TEXT_378 = ");";
  protected final String TEXT_379 = NL + "\t\t\tif (value != null) return value;" + NL + "\t\t}" + NL + "\t\tcatch (Exception e)" + NL + "\t\t{" + NL + "\t\t\t// Keep trying other member types until all have failed." + NL + "\t\t}";
  protected final String TEXT_380 = NL + "\t\tthrow new IllegalArgumentException(\"Invalid value: '\"+instanceValue+\"' for datatype :\"+";
  protected final String TEXT_381 = ".getName());";
  protected final String TEXT_382 = NL + "\t\treturn super.convertToString(instanceValue);";
  protected final String TEXT_383 = NL + "\t\t// TODO: implement this method" + NL + "\t\t// Ensure that you remove @generated or mark it @generated NOT" + NL + "\t\tthrow new ";
  protected final String TEXT_384 = "();";
  protected final String TEXT_385 = NL + "\t\treturn super.convertToString(";
  protected final String TEXT_386 = ", new ";
  protected final String TEXT_387 = "(instanceValue));";
  protected final String TEXT_388 = NL + "\t\treturn super.convertToString(";
  protected final String TEXT_389 = ", instanceValue);";
  protected final String TEXT_390 = NL + "\t}" + NL;
  protected final String TEXT_391 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->";
  protected final String TEXT_392 = NL + "\t * ";
  protected final String TEXT_393 = NL + "\t * @generated" + NL + "\t */";
  protected final String TEXT_394 = NL + "\t@SuppressWarnings(\"unchecked\")";
  protected final String TEXT_395 = NL + "\t@Deprecated";
  protected final String TEXT_396 = NL + "\tpublic String convert";
  protected final String TEXT_397 = "ToString(";
  protected final String TEXT_398 = " eDataType, Object instanceValue)" + NL + "\t{";
  protected final String TEXT_399 = NL + "\t\treturn convert";
  protected final String TEXT_400 = "((";
  protected final String TEXT_401 = ")instanceValue);";
  protected final String TEXT_402 = NL + "\t\treturn instanceValue == null ? null : instanceValue.toString();";
  protected final String TEXT_403 = NL + "\t\treturn convert";
  protected final String TEXT_404 = "ToString(";
  protected final String TEXT_405 = ", instanceValue);";
  protected final String TEXT_406 = NL + "\t\treturn ";
  protected final String TEXT_407 = ".convertToString(";
  protected final String TEXT_408 = ", instanceValue);";
  protected final String TEXT_409 = NL + "\t\treturn convert";
  protected final String TEXT_410 = "((";
  protected final String TEXT_411 = ")instanceValue);";
  protected final String TEXT_412 = NL + "\t\tif (instanceValue == null) return null;" + NL + "\t\t";
  protected final String TEXT_413 = " list = (";
  protected final String TEXT_414 = ")instanceValue;" + NL + "\t\tif (list.isEmpty()) return \"\";" + NL + "\t\t";
  protected final String TEXT_415 = " result = new ";
  protected final String TEXT_416 = "();";
  protected final String TEXT_417 = NL + "\t\tfor (";
  protected final String TEXT_418 = " i = list.iterator(); i.hasNext(); )";
  protected final String TEXT_419 = NL + "\t\tfor (";
  protected final String TEXT_420 = " item : list)";
  protected final String TEXT_421 = NL + "\t\t{";
  protected final String TEXT_422 = NL + "\t\t\tresult.append(convert";
  protected final String TEXT_423 = "ToString(";
  protected final String TEXT_424 = ", ";
  protected final String TEXT_425 = "));";
  protected final String TEXT_426 = NL + "\t\t\tresult.append(";
  protected final String TEXT_427 = ".convertToString(";
  protected final String TEXT_428 = ", ";
  protected final String TEXT_429 = "));";
  protected final String TEXT_430 = NL + "\t\t\tresult.append(' ');" + NL + "\t\t}" + NL + "\t\treturn result.substring(0, result.length() - 1);";
  protected final String TEXT_431 = NL + "\t\treturn instanceValue == null ? null : convert";
  protected final String TEXT_432 = "(((";
  protected final String TEXT_433 = ")instanceValue)";
  protected final String TEXT_434 = ".";
  protected final String TEXT_435 = "()";
  protected final String TEXT_436 = ");";
  protected final String TEXT_437 = NL + "\t\treturn convert";
  protected final String TEXT_438 = "(instanceValue);";
  protected final String TEXT_439 = NL + "\t\tif (instanceValue == null) return null;";
  protected final String TEXT_440 = NL + "\t\tif (";
  protected final String TEXT_441 = ".isInstance(instanceValue))" + NL + "\t\t{" + NL + "\t\t\ttry" + NL + "\t\t\t{";
  protected final String TEXT_442 = NL + "\t\t\t\tString value = convert";
  protected final String TEXT_443 = "ToString(";
  protected final String TEXT_444 = ", instanceValue);";
  protected final String TEXT_445 = NL + "\t\t\t\tString value = ";
  protected final String TEXT_446 = ".convertToString(";
  protected final String TEXT_447 = ", instanceValue);";
  protected final String TEXT_448 = NL + "\t\t\t\tif (value != null) return value;" + NL + "\t\t\t}" + NL + "\t\t\tcatch (Exception e)" + NL + "\t\t\t{" + NL + "\t\t\t\t// Keep trying other member types until all have failed." + NL + "\t\t\t}" + NL + "\t\t}";
  protected final String TEXT_449 = NL + "\t\tthrow new IllegalArgumentException(\"Invalid value: '\"+instanceValue+\"' for datatype :\"+eDataType.getName());";
  protected final String TEXT_450 = NL + "\t\treturn instanceValue == null ? null : convert";
  protected final String TEXT_451 = "(";
  protected final String TEXT_452 = "(";
  protected final String TEXT_453 = "(";
  protected final String TEXT_454 = ")instanceValue";
  protected final String TEXT_455 = ").";
  protected final String TEXT_456 = "()";
  protected final String TEXT_457 = ");";
  protected final String TEXT_458 = NL + "\t\treturn convert";
  protected final String TEXT_459 = "((";
  protected final String TEXT_460 = ")instanceValue);";
  protected final String TEXT_461 = NL + "\t\treturn super.convertToString(instanceValue);";
  protected final String TEXT_462 = NL + "\t\t// TODO: implement this method" + NL + "\t\t// Ensure that you remove @generated or mark it @generated NOT" + NL + "\t\tthrow new ";
  protected final String TEXT_463 = "();";
  protected final String TEXT_464 = NL + "\t\treturn super.convertToString(eDataType, instanceValue);";
  protected final String TEXT_465 = NL + "\t}" + NL;
  protected final String TEXT_466 = NL + "\t/**" + NL + "\t * Returns a new object of class '<em>";
  protected final String TEXT_467 = "</em>'." + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @return a new object of class '<em>";
  protected final String TEXT_468 = "</em>'.";
  protected final String TEXT_469 = NL + "\t * ";
  protected final String TEXT_470 = NL + "\t * @generated" + NL + "\t */";
  protected final String TEXT_471 = NL + "\t@Deprecated";
  protected final String TEXT_472 = NL + "\t";
  protected final String TEXT_473 = " create";
  protected final String TEXT_474 = "();" + NL;
  protected final String TEXT_475 = NL + "\t/**" + NL + "\t * Returns an instance of data type '<em>";
  protected final String TEXT_476 = "</em>' corresponding the given literal." + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @param literal a literal of the data type." + NL + "\t * @return a new instance value of the data type.";
  protected final String TEXT_477 = NL + "\t * ";
  protected final String TEXT_478 = NL + "\t * @generated" + NL + "\t */";
  protected final String TEXT_479 = NL + "\t@Deprecated";
  protected final String TEXT_480 = NL + "\t";
  protected final String TEXT_481 = " create";
  protected final String TEXT_482 = "(String literal);" + NL + "" + NL + "\t/**" + NL + "\t * Returns a literal representation of an instance of data type '<em>";
  protected final String TEXT_483 = "</em>'." + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @param instanceValue an instance value of the data type." + NL + "\t * @return a literal representation of the instance value.";
  protected final String TEXT_484 = NL + "\t * ";
  protected final String TEXT_485 = NL + "\t * @generated" + NL + "\t */";
  protected final String TEXT_486 = NL + "\t@Deprecated";
  protected final String TEXT_487 = NL + "\tString convert";
  protected final String TEXT_488 = "(";
  protected final String TEXT_489 = " instanceValue);" + NL;
  protected final String TEXT_490 = NL + "\t/**" + NL + "\t * Returns the package supported by this factory." + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @return the package supported by this factory." + NL + "\t * @generated" + NL + "\t */" + NL + "\t";
  protected final String TEXT_491 = " get";
  protected final String TEXT_492 = "();" + NL;
  protected final String TEXT_493 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */" + NL + "\tpublic ";
  protected final String TEXT_494 = " get";
  protected final String TEXT_495 = "()" + NL + "\t{" + NL + "\t\treturn (";
  protected final String TEXT_496 = ")getEPackage();" + NL + "\t}" + NL + "" + NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @deprecated" + NL + "\t * @generated" + NL + "\t */";
  protected final String TEXT_497 = NL + "\t@Deprecated";
  protected final String TEXT_498 = NL + "\tpublic static ";
  protected final String TEXT_499 = " getPackage()" + NL + "\t{" + NL + "\t\treturn ";
  protected final String TEXT_500 = ".eINSTANCE;" + NL + "\t}" + NL;
  protected final String TEXT_501 = NL + "} //";
  protected final String TEXT_502 = NL;

  public String generate(Object argument)
  {
    final StringBuffer stringBuffer = new StringBuffer();
    
/**
 * Copyright (c) 2002-2010 IBM Corporation and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *   IBM - Initial API and implementation
 */

    GenPackage genPackage = (GenPackage)((Object[])argument)[0]; GenModel genModel=genPackage.getGenModel(); /* Trick to import java.util.* without warnings */Iterator.class.getName();
    final boolean isJDK50 = genModel.getComplianceLevel().getValue() >= GenJDKLevel.JDK50;
    boolean isInterface = Boolean.TRUE.equals(((Object[])argument)[1]); boolean isImplementation = Boolean.TRUE.equals(((Object[])argument)[2]);
    String publicStaticFinalFlag = isImplementation ? "public static final " : "";
    stringBuffer.append(TEXT_1);
    stringBuffer.append(TEXT_2);
    {GenBase copyrightHolder = argument instanceof GenBase ? (GenBase)argument : argument instanceof Object[] && ((Object[])argument)[0] instanceof GenBase ? (GenBase)((Object[])argument)[0] : null;
    if (copyrightHolder != null && copyrightHolder.hasCopyright()) {
    stringBuffer.append(TEXT_3);
    stringBuffer.append(copyrightHolder.getCopyright(copyrightHolder.getGenModel().getIndentation(stringBuffer)));
    }}
    stringBuffer.append(TEXT_4);
    if (isInterface || genModel.isSuppressInterfaces()) {
    stringBuffer.append(TEXT_5);
    stringBuffer.append(genPackage.getReflectionPackageName());
    stringBuffer.append(TEXT_6);
    } else {
    stringBuffer.append(TEXT_7);
    stringBuffer.append(genPackage.getClassPackageName());
    stringBuffer.append(TEXT_8);
    }
    stringBuffer.append(TEXT_9);
    if (isImplementation) {
    genModel.addPseudoImport("org.eclipse.emf.ecore.impl.MinimalEObjectImpl.Container");
    genModel.addPseudoImport("org.eclipse.emf.ecore.impl.MinimalEObjectImpl.Container.Dynamic");
    genModel.addImport("org.eclipse.emf.ecore.EClass");
    genModel.addImport("org.eclipse.emf.ecore.EObject");
    if (!genPackage.hasJavaLangConflict() && !genPackage.hasInterfaceImplConflict() && !genPackage.getClassPackageName().equals(genPackage.getInterfacePackageName())) genModel.addImport(genPackage.getInterfacePackageName() + ".*");
    }
    genModel.markImportLocation(stringBuffer);
    stringBuffer.append(TEXT_10);
    if (isInterface) {
    stringBuffer.append(TEXT_11);
    if (!genModel.isSuppressEMFMetaData()) {
    stringBuffer.append(TEXT_12);
    stringBuffer.append(genPackage.getQualifiedPackageInterfaceName());
    }
    if (genPackage.hasAPITags()) {
    stringBuffer.append(TEXT_13);
    stringBuffer.append(genPackage.getAPITags(genModel.getIndentation(stringBuffer)));
    }
    stringBuffer.append(TEXT_14);
    } else {
    stringBuffer.append(TEXT_15);
    if (genPackage.hasAPITags()) {
    stringBuffer.append(TEXT_16);
    stringBuffer.append(genPackage.getAPITags(genModel.getIndentation(stringBuffer)));
    }
    stringBuffer.append(TEXT_17);
    }
    if (isJDK50 && genPackage.hasAPIDeprecatedTag()) {
    stringBuffer.append(TEXT_18);
    }
    if (isImplementation) {
    if (isJDK50 && GenModelUtil.hasAPIDeprecatedTag(genPackage.getGenClassifiers()) && !genPackage.hasAPIDeprecatedTag()) {
    stringBuffer.append(TEXT_19);
    }
    stringBuffer.append(TEXT_20);
    stringBuffer.append(genPackage.getFactoryClassName());
    stringBuffer.append(TEXT_21);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.impl.EFactoryImpl"));
    if (!genModel.isSuppressInterfaces()) {
    stringBuffer.append(TEXT_22);
    stringBuffer.append(genPackage.getImportedFactoryInterfaceName());
    }
    } else {
    stringBuffer.append(TEXT_23);
    stringBuffer.append(genPackage.getFactoryInterfaceName());
    if (!genModel.isSuppressEMFMetaData()) {
    stringBuffer.append(TEXT_24);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.EFactory"));
    }
    }
    stringBuffer.append(TEXT_25);
    if (genModel.hasCopyrightField()) {
    stringBuffer.append(TEXT_26);
    stringBuffer.append(publicStaticFinalFlag);
    stringBuffer.append(genModel.getImportedName("java.lang.String"));
    stringBuffer.append(TEXT_27);
    stringBuffer.append(genModel.getCopyrightFieldLiteral());
    stringBuffer.append(TEXT_28);
    stringBuffer.append(genModel.getNonNLS());
    stringBuffer.append(TEXT_29);
    }
    if (isImplementation && (genModel.isSuppressEMFMetaData() || genModel.isSuppressInterfaces())) {
    stringBuffer.append(TEXT_30);
    stringBuffer.append(publicStaticFinalFlag);
    stringBuffer.append(genPackage.getFactoryClassName());
    stringBuffer.append(TEXT_31);
    }
    if (isInterface && genModel.isSuppressEMFMetaData()) {
    stringBuffer.append(TEXT_32);
    stringBuffer.append(publicStaticFinalFlag);
    stringBuffer.append(genPackage.getFactoryInterfaceName());
    stringBuffer.append(TEXT_33);
    stringBuffer.append(genPackage.getQualifiedFactoryClassName());
    stringBuffer.append(TEXT_34);
    } else if (isInterface && !genModel.isSuppressInterfaces()) {
    stringBuffer.append(TEXT_35);
    stringBuffer.append(publicStaticFinalFlag);
    stringBuffer.append(genPackage.getFactoryInterfaceName());
    stringBuffer.append(TEXT_36);
    stringBuffer.append(genPackage.getQualifiedFactoryClassName());
    stringBuffer.append(TEXT_37);
    }
    if (isImplementation) {
    stringBuffer.append(TEXT_38);
    String factoryType = genModel.isSuppressEMFMetaData() ? genPackage.getFactoryClassName() : genPackage.getImportedFactoryInterfaceName();
    stringBuffer.append(TEXT_39);
    stringBuffer.append(factoryType);
    stringBuffer.append(TEXT_40);
    stringBuffer.append(factoryType);
    stringBuffer.append(TEXT_41);
    stringBuffer.append(genPackage.getFactoryName());
    stringBuffer.append(TEXT_42);
    stringBuffer.append(factoryType);
    stringBuffer.append(TEXT_43);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.EPackage"));
    stringBuffer.append(TEXT_44);
    stringBuffer.append(genPackage.getPackageInterfaceName());
    stringBuffer.append(TEXT_45);
    stringBuffer.append(genPackage.getFactoryName());
    stringBuffer.append(TEXT_46);
    stringBuffer.append(genPackage.getFactoryName());
    stringBuffer.append(TEXT_47);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.plugin.EcorePlugin"));
    stringBuffer.append(TEXT_48);
    stringBuffer.append(genPackage.getImportedFactoryClassName());
    stringBuffer.append(TEXT_49);
    stringBuffer.append(genPackage.getFactoryClassName());
    stringBuffer.append(TEXT_50);
    if (genModel.useClassOverrideAnnotation()) {
    stringBuffer.append(TEXT_51);
    }
    stringBuffer.append(TEXT_52);
    for (GenClass genClass : genPackage.getGenClasses()) {
    if (!genClass.isAbstract()) {
    stringBuffer.append(TEXT_53);
    stringBuffer.append(genPackage.getImportedPackageInterfaceName());
    stringBuffer.append(TEXT_54);
    stringBuffer.append(genClass.getClassifierID());
    stringBuffer.append(TEXT_55);
    stringBuffer.append(!genClass.isEObjectExtension() ? "(EObject)" : "" );
    stringBuffer.append(TEXT_56);
    stringBuffer.append(genClass.getName());
    stringBuffer.append(TEXT_57);
    }
    }
    stringBuffer.append(TEXT_58);
    stringBuffer.append(genModel.getNonNLS());
    stringBuffer.append(genModel.getNonNLS(2));
    stringBuffer.append(TEXT_59);
    if (!genPackage.getAllGenDataTypes().isEmpty()) {
    stringBuffer.append(TEXT_60);
    if (genModel.useClassOverrideAnnotation()) {
    stringBuffer.append(TEXT_61);
    }
    stringBuffer.append(TEXT_62);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.EDataType"));
    stringBuffer.append(TEXT_63);
    for (GenDataType genDataType : genPackage.getAllGenDataTypes()) {
    if (genDataType.isSerializable()) {
    stringBuffer.append(TEXT_64);
    stringBuffer.append(genPackage.getImportedPackageInterfaceName());
    stringBuffer.append(TEXT_65);
    stringBuffer.append(genDataType.getClassifierID());
    stringBuffer.append(TEXT_66);
    stringBuffer.append(genDataType.getName());
    stringBuffer.append(TEXT_67);
    }
    }
    stringBuffer.append(TEXT_68);
    stringBuffer.append(genModel.getNonNLS());
    stringBuffer.append(genModel.getNonNLS(2));
    stringBuffer.append(TEXT_69);
    if (genModel.useClassOverrideAnnotation()) {
    stringBuffer.append(TEXT_70);
    }
    stringBuffer.append(TEXT_71);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.EDataType"));
    stringBuffer.append(TEXT_72);
    for (GenDataType genDataType : genPackage.getAllGenDataTypes()) {
    if (genDataType.isSerializable()) {
    stringBuffer.append(TEXT_73);
    stringBuffer.append(genPackage.getImportedPackageInterfaceName());
    stringBuffer.append(TEXT_74);
    stringBuffer.append(genDataType.getClassifierID());
    stringBuffer.append(TEXT_75);
    stringBuffer.append(genDataType.getName());
    stringBuffer.append(TEXT_76);
    }
    }
    stringBuffer.append(TEXT_77);
    stringBuffer.append(genModel.getNonNLS());
    stringBuffer.append(genModel.getNonNLS(2));
    stringBuffer.append(TEXT_78);
    }
    for (GenClass genClass : genPackage.getGenClasses()) {
    if (!genClass.isAbstract()) {
    stringBuffer.append(TEXT_79);
    if (genClass.hasAPITags()) {
    stringBuffer.append(TEXT_80);
    stringBuffer.append(genClass.getAPITags(genModel.getIndentation(stringBuffer)));
    }
    stringBuffer.append(TEXT_81);
    if (isJDK50 && genClass.hasAPIDeprecatedTag()) {
    stringBuffer.append(TEXT_82);
    }
    stringBuffer.append(TEXT_83);
    stringBuffer.append(genClass.getTypeParameters());
    stringBuffer.append(genClass.getImportedInterfaceName());
    stringBuffer.append(genClass.getInterfaceTypeArguments());
    stringBuffer.append(TEXT_84);
    stringBuffer.append(genClass.getName());
    stringBuffer.append(TEXT_85);
    if (genClass.isDynamic()) {
    stringBuffer.append(TEXT_86);
    stringBuffer.append(genClass.getImportedInterfaceName());
    stringBuffer.append(genClass.getInterfaceTypeArguments());
    stringBuffer.append(TEXT_87);
    stringBuffer.append(genClass.getSafeUncapName());
    stringBuffer.append(TEXT_88);
    stringBuffer.append(genClass.getCastFromEObject());
    stringBuffer.append(TEXT_89);
    stringBuffer.append(genClass.getQualifiedClassifierAccessor());
    stringBuffer.append(TEXT_90);
    } else {
    stringBuffer.append(TEXT_91);
    stringBuffer.append(genClass.getImportedClassName());
    stringBuffer.append(genClass.getClassTypeArguments());
    stringBuffer.append(TEXT_92);
    stringBuffer.append(genClass.getSafeUncapName());
    stringBuffer.append(TEXT_93);
    stringBuffer.append(genClass.getImportedClassName());
    stringBuffer.append(genClass.getClassTypeArguments());
    stringBuffer.append(TEXT_94);
    if (genModel.isSuppressInterfaces() && !genPackage.getReflectionPackageName().equals(genPackage.getInterfacePackageName())) {
    stringBuffer.append(TEXT_95);
    }
    stringBuffer.append(TEXT_96);
    }
    stringBuffer.append(TEXT_97);
    stringBuffer.append(genClass.getSafeUncapName());
    stringBuffer.append(TEXT_98);
    }
    }
    for (GenDataType genDataType : genPackage.getAllGenDataTypes()) {
    if (genDataType.isSerializable()) {
    if (genPackage.isDataTypeConverters() || genDataType.hasCreatorBody()) { String eDataType = genDataType.getQualifiedClassifierAccessor();
    stringBuffer.append(TEXT_99);
    if (genDataType.hasAPITags()) {
    stringBuffer.append(TEXT_100);
    stringBuffer.append(genDataType.getAPITags(genModel.getIndentation(stringBuffer)));
    }
    stringBuffer.append(TEXT_101);
    if (genModel.useGenerics() && genDataType.isUncheckedCast() && !genDataType.hasCreatorBody()) {
    stringBuffer.append(TEXT_102);
    }
    if (isJDK50 && genDataType.hasAPIDeprecatedTag()) {
    stringBuffer.append(TEXT_103);
    }
    stringBuffer.append(TEXT_104);
    stringBuffer.append(genDataType.getImportedParameterizedInstanceClassName());
    stringBuffer.append(TEXT_105);
    stringBuffer.append(genDataType.getName());
    stringBuffer.append(TEXT_106);
    if (genDataType.hasCreatorBody()) {
    stringBuffer.append(TEXT_107);
    }
    stringBuffer.append(TEXT_108);
    if (genDataType.hasCreatorBody()) {
    stringBuffer.append(TEXT_109);
    } else {
    stringBuffer.append(TEXT_110);
    }
    stringBuffer.append(TEXT_111);
    if (genDataType.hasCreatorBody()) {
    stringBuffer.append(TEXT_112);
    stringBuffer.append(genDataType.getCreatorBody(genModel.getIndentation(stringBuffer)));
    } else if (genDataType instanceof GenEnum) {
    stringBuffer.append(TEXT_113);
    stringBuffer.append(genDataType.getImportedInstanceClassName());
    stringBuffer.append(TEXT_114);
    stringBuffer.append(genDataType.getImportedInstanceClassName());
    stringBuffer.append(TEXT_115);
    stringBuffer.append(eDataType);
    stringBuffer.append(TEXT_116);
    stringBuffer.append(genModel.getNonNLS());
    stringBuffer.append(genModel.getNonNLS(2));
    stringBuffer.append(genModel.getNonNLS(3));
    stringBuffer.append(TEXT_117);
    } else if (genDataType.getBaseType() != null) { GenDataType genBaseType = genDataType.getBaseType(); boolean isPrimitiveConversion = !genDataType.isPrimitiveType() && genBaseType.isPrimitiveType();
    if (genBaseType.getGenPackage() == genPackage) {
    if (isPrimitiveConversion && !isJDK50) {
    stringBuffer.append(TEXT_118);
    stringBuffer.append(genDataType.getObjectInstanceClassName());
    stringBuffer.append(TEXT_119);
    stringBuffer.append(genBaseType.getName());
    stringBuffer.append(TEXT_120);
    } else {
    stringBuffer.append(TEXT_121);
    stringBuffer.append(genBaseType.getName());
    stringBuffer.append(TEXT_122);
    }
    } else if (genBaseType.getGenPackage().isDataTypeConverters()) {
    if (isPrimitiveConversion && !isJDK50) {
    stringBuffer.append(TEXT_123);
    stringBuffer.append(genDataType.getObjectInstanceClassName());
    stringBuffer.append(TEXT_124);
    stringBuffer.append(genBaseType.getGenPackage().getQualifiedEFactoryInternalInstanceAccessor());
    stringBuffer.append(TEXT_125);
    stringBuffer.append(genBaseType.getName());
    stringBuffer.append(TEXT_126);
    } else {
    stringBuffer.append(TEXT_127);
    stringBuffer.append(genBaseType.getGenPackage().getQualifiedEFactoryInternalInstanceAccessor());
    stringBuffer.append(TEXT_128);
    stringBuffer.append(genBaseType.getName());
    stringBuffer.append(TEXT_129);
    }
    } else {
    stringBuffer.append(TEXT_130);
    if (!genDataType.isObjectType()) {
    stringBuffer.append(TEXT_131);
    stringBuffer.append(genDataType.getObjectInstanceClassName());
    stringBuffer.append(TEXT_132);
    }
    stringBuffer.append(genBaseType.getGenPackage().getQualifiedEFactoryInternalInstanceAccessor());
    stringBuffer.append(TEXT_133);
    stringBuffer.append(genBaseType.getQualifiedClassifierAccessor());
    stringBuffer.append(TEXT_134);
    }
    } else if (genDataType.getItemType() != null) { GenDataType genItemType = genDataType.getItemType(); 
    stringBuffer.append(TEXT_135);
    stringBuffer.append(genDataType.getImportedParameterizedInstanceClassName());
    stringBuffer.append(TEXT_136);
    stringBuffer.append(genModel.getImportedName("java.util.ArrayList"));
    if (genModel.useGenerics()) {
    stringBuffer.append(TEXT_137);
    stringBuffer.append(genItemType.getObjectType().getImportedParameterizedInstanceClassName());
    stringBuffer.append(TEXT_138);
    }
    stringBuffer.append(TEXT_139);
    if (genModel.getRuntimeVersion().getValue() < GenRuntimeVersion.EMF26_VALUE) {
    stringBuffer.append(TEXT_140);
    stringBuffer.append(genModel.getImportedName("java.util.StringTokenizer"));
    stringBuffer.append(TEXT_141);
    stringBuffer.append(genModel.getImportedName("java.util.StringTokenizer"));
    stringBuffer.append(TEXT_142);
    } else {
    stringBuffer.append(TEXT_143);
    }
    stringBuffer.append(TEXT_144);
    if (genModel.getRuntimeVersion().getValue() < GenRuntimeVersion.EMF26_VALUE) {
    stringBuffer.append(TEXT_145);
    }
    if (genItemType.getGenPackage() == genPackage) {
    if (genPackage.isDataTypeConverters()) { genItemType = genItemType.getObjectType();
    stringBuffer.append(TEXT_146);
    stringBuffer.append(genItemType.getName());
    stringBuffer.append(TEXT_147);
    } else {
    stringBuffer.append(TEXT_148);
    stringBuffer.append(genItemType.getName());
    stringBuffer.append(TEXT_149);
    stringBuffer.append(genItemType.getQualifiedClassifierAccessor());
    stringBuffer.append(TEXT_150);
    }
    } else {
    if (genItemType.getGenPackage().isDataTypeConverters()) { genItemType = genItemType.getObjectType();
    stringBuffer.append(TEXT_151);
    stringBuffer.append(genItemType.getGenPackage().getQualifiedEFactoryInternalInstanceAccessor());
    stringBuffer.append(TEXT_152);
    stringBuffer.append(genItemType.getName());
    stringBuffer.append(TEXT_153);
    } else {
    stringBuffer.append(TEXT_154);
    stringBuffer.append(genItemType.getGenPackage().getQualifiedEFactoryInternalInstanceAccessor());
    stringBuffer.append(TEXT_155);
    stringBuffer.append(genItemType.getQualifiedClassifierAccessor());
    stringBuffer.append(TEXT_156);
    }
    }
    stringBuffer.append(TEXT_157);
    } else if (!genDataType.getMemberTypes().isEmpty()) {
    stringBuffer.append(TEXT_158);
    stringBuffer.append(genDataType.getStaticValue(null));
    stringBuffer.append(TEXT_159);
    stringBuffer.append(genDataType.getImportedInstanceClassName());
    stringBuffer.append(TEXT_160);
    stringBuffer.append(genDataType.getStaticValue(null));
    stringBuffer.append(TEXT_161);
    for (GenDataType genMemberType : genDataType.getMemberTypes()) {
    stringBuffer.append(TEXT_162);
    if (genMemberType.getGenPackage() == genPackage) {
    if (genPackage.isDataTypeConverters()) { if (!genDataType.isPrimitiveType()) genMemberType = genMemberType.getObjectType();
    stringBuffer.append(TEXT_163);
    stringBuffer.append(genMemberType.getName());
    stringBuffer.append(TEXT_164);
    } else {
    stringBuffer.append(TEXT_165);
    stringBuffer.append(genDataType.getObjectInstanceClassName());
    stringBuffer.append(TEXT_166);
    stringBuffer.append(genMemberType.getName());
    stringBuffer.append(TEXT_167);
    stringBuffer.append(genMemberType.getQualifiedClassifierAccessor());
    stringBuffer.append(TEXT_168);
    }
    } else {
    if (genPackage.isDataTypeConverters()) { if (!genDataType.isPrimitiveType()) genMemberType = genMemberType.getObjectType();
    stringBuffer.append(TEXT_169);
    stringBuffer.append(genMemberType.getGenPackage().getQualifiedEFactoryInternalInstanceAccessor());
    stringBuffer.append(TEXT_170);
    stringBuffer.append(genMemberType.getName());
    stringBuffer.append(TEXT_171);
    } else {
    stringBuffer.append(TEXT_172);
    stringBuffer.append(genDataType.getObjectInstanceClassName());
    stringBuffer.append(TEXT_173);
    stringBuffer.append(genMemberType.getGenPackage().getQualifiedEFactoryInternalInstanceAccessor());
    stringBuffer.append(TEXT_174);
    stringBuffer.append(genMemberType.getQualifiedClassifierAccessor());
    stringBuffer.append(TEXT_175);
    }
    }
    stringBuffer.append(TEXT_176);
    if (!genDataType.isPrimitiveType()) {
    stringBuffer.append(TEXT_177);
    }
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.Diagnostician"));
    stringBuffer.append(TEXT_178);
    stringBuffer.append(eDataType);
    stringBuffer.append(TEXT_179);
    if (genDataType.isPrimitiveType() && !isJDK50) {
    stringBuffer.append(TEXT_180);
    stringBuffer.append(genDataType.getObjectInstanceClassName());
    stringBuffer.append(TEXT_181);
    } else {
    stringBuffer.append(TEXT_182);
    }
    stringBuffer.append(TEXT_183);
    }
    stringBuffer.append(TEXT_184);
    if (!genDataType.isPrimitiveType()) {
    stringBuffer.append(TEXT_185);
    }
    stringBuffer.append(TEXT_186);
    } else if (!genDataType.hasConversionDelegate() && genModel.useGenerics() && (genDataType.isArrayType() || !genDataType.getEcoreDataType().getETypeParameters().isEmpty() || genDataType.getEcoreDataType().getInstanceTypeName().contains("<"))) {
    stringBuffer.append(TEXT_187);
    stringBuffer.append(genDataType.getImportedParameterizedObjectInstanceClassName());
    stringBuffer.append(TEXT_188);
    } else if (!genDataType.hasConversionDelegate() && genDataType.isArrayType()) {
    stringBuffer.append(TEXT_189);
    stringBuffer.append(genModel.getImportedName("java.lang.UnsupportedOperationException"));
    stringBuffer.append(TEXT_190);
    } else if (genDataType.isPrimitiveType() && !isJDK50) {
    stringBuffer.append(TEXT_191);
    stringBuffer.append(genDataType.getObjectInstanceClassName());
    stringBuffer.append(TEXT_192);
    stringBuffer.append(eDataType);
    stringBuffer.append(TEXT_193);
    stringBuffer.append(genDataType.getPrimitiveValueFunction());
    stringBuffer.append(TEXT_194);
    } else {
    stringBuffer.append(TEXT_195);
    if (!genDataType.isObjectType()) {
    stringBuffer.append(TEXT_196);
    stringBuffer.append(genDataType.getImportedParameterizedObjectInstanceClassName());
    stringBuffer.append(TEXT_197);
    }
    stringBuffer.append(TEXT_198);
    stringBuffer.append(eDataType);
    stringBuffer.append(TEXT_199);
    }
    stringBuffer.append(TEXT_200);
    }
    stringBuffer.append(TEXT_201);
    if (genDataType.hasAPITags()) {
    stringBuffer.append(TEXT_202);
    stringBuffer.append(genDataType.getAPITags(genModel.getIndentation(stringBuffer)));
    }
    stringBuffer.append(TEXT_203);
    if (!genPackage.isDataTypeConverters() && genModel.useGenerics() && genDataType.isUncheckedCast() && !genDataType.hasCreatorBody()) {
    stringBuffer.append(TEXT_204);
    }
    if (isJDK50 && genDataType.hasAPIDeprecatedTag()) {
    stringBuffer.append(TEXT_205);
    }
    stringBuffer.append(TEXT_206);
    stringBuffer.append(genDataType.getImportedParameterizedObjectInstanceClassName());
    stringBuffer.append(TEXT_207);
    stringBuffer.append(genDataType.getName());
    stringBuffer.append(TEXT_208);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.EDataType"));
    stringBuffer.append(TEXT_209);
    if (genDataType instanceof GenEnum) {
    if (genPackage.isDataTypeConverters() || genDataType.hasCreatorBody()) {
    stringBuffer.append(TEXT_210);
    stringBuffer.append(genDataType.getName());
    stringBuffer.append(TEXT_211);
    } else {
    stringBuffer.append(TEXT_212);
    stringBuffer.append(((GenEnum)genDataType).getImportedInstanceClassName());
    stringBuffer.append(TEXT_213);
    stringBuffer.append(((GenEnum)genDataType).getImportedInstanceClassName());
    stringBuffer.append(TEXT_214);
    stringBuffer.append(genModel.getNonNLS());
    stringBuffer.append(genModel.getNonNLS(2));
    stringBuffer.append(genModel.getNonNLS(3));
    stringBuffer.append(TEXT_215);
    }
    } else if (genDataType.getBaseType() != null) { GenDataType genBaseType = genDataType.getBaseType(); 
    if (genBaseType.getGenPackage() == genPackage) {
    stringBuffer.append(TEXT_216);
    if (!genDataType.getObjectInstanceClassName().equals(genBaseType.getObjectInstanceClassName())) {
    stringBuffer.append(TEXT_217);
    stringBuffer.append(genDataType.getObjectInstanceClassName());
    stringBuffer.append(TEXT_218);
    }
    stringBuffer.append(TEXT_219);
    stringBuffer.append(genBaseType.getName());
    stringBuffer.append(TEXT_220);
    stringBuffer.append(genBaseType.getQualifiedClassifierAccessor());
    stringBuffer.append(TEXT_221);
    } else {
    stringBuffer.append(TEXT_222);
    if (!genDataType.isObjectType()) {
    stringBuffer.append(TEXT_223);
    stringBuffer.append(genDataType.getObjectInstanceClassName());
    stringBuffer.append(TEXT_224);
    }
    stringBuffer.append(genBaseType.getGenPackage().getQualifiedEFactoryInternalInstanceAccessor());
    stringBuffer.append(TEXT_225);
    stringBuffer.append(genBaseType.getQualifiedClassifierAccessor());
    stringBuffer.append(TEXT_226);
    }
    } else if (genDataType.getItemType() != null) { GenDataType genItemType = genDataType.getItemType(); 
    if (genPackage.isDataTypeConverters()) {
    stringBuffer.append(TEXT_227);
    stringBuffer.append(genDataType.getName());
    stringBuffer.append(TEXT_228);
    } else {
    stringBuffer.append(TEXT_229);
    stringBuffer.append(genDataType.getImportedParameterizedObjectInstanceClassName());
    stringBuffer.append(TEXT_230);
    stringBuffer.append(genModel.getImportedName("java.util.ArrayList"));
    if (genModel.useGenerics()) {
    stringBuffer.append(TEXT_231);
    stringBuffer.append(genItemType.getObjectType().getImportedParameterizedInstanceClassName());
    stringBuffer.append(TEXT_232);
    }
    stringBuffer.append(TEXT_233);
    if (genModel.getRuntimeVersion().getValue() < GenRuntimeVersion.EMF26_VALUE) {
    stringBuffer.append(TEXT_234);
    stringBuffer.append(genModel.getImportedName("java.util.StringTokenizer"));
    stringBuffer.append(TEXT_235);
    stringBuffer.append(genModel.getImportedName("java.util.StringTokenizer"));
    stringBuffer.append(TEXT_236);
    } else {
    stringBuffer.append(TEXT_237);
    }
    stringBuffer.append(TEXT_238);
    if (genModel.getRuntimeVersion().getValue() < GenRuntimeVersion.EMF26_VALUE) {
    stringBuffer.append(TEXT_239);
    }
    if (genItemType.getGenPackage() == genPackage) {
    stringBuffer.append(TEXT_240);
    stringBuffer.append(genItemType.getName());
    stringBuffer.append(TEXT_241);
    stringBuffer.append(genItemType.getQualifiedClassifierAccessor());
    stringBuffer.append(TEXT_242);
    } else {
    stringBuffer.append(TEXT_243);
    if (!genItemType.isObjectType()) {
    stringBuffer.append(TEXT_244);
    stringBuffer.append(genItemType.getObjectInstanceClassName());
    stringBuffer.append(TEXT_245);
    }
    stringBuffer.append(genItemType.getGenPackage().getQualifiedEFactoryInternalInstanceAccessor());
    stringBuffer.append(TEXT_246);
    stringBuffer.append(genItemType.getQualifiedClassifierAccessor());
    stringBuffer.append(TEXT_247);
    }
    stringBuffer.append(TEXT_248);
    }
    } else if (!genDataType.getMemberTypes().isEmpty()) {
    if (genPackage.isDataTypeConverters()) {
    if (genDataType.isPrimitiveType() && !isJDK50) {
    stringBuffer.append(TEXT_249);
    stringBuffer.append(genDataType.getObjectInstanceClassName());
    stringBuffer.append(TEXT_250);
    stringBuffer.append(genDataType.getName());
    stringBuffer.append(TEXT_251);
    } else {
    stringBuffer.append(TEXT_252);
    stringBuffer.append(genDataType.getName());
    stringBuffer.append(TEXT_253);
    }
    } else {
    stringBuffer.append(TEXT_254);
    stringBuffer.append(genDataType.getObjectInstanceClassName());
    stringBuffer.append(TEXT_255);
    for (GenDataType genMemberType : genDataType.getMemberTypes()) {
    stringBuffer.append(TEXT_256);
    if (genMemberType.getGenPackage() == genPackage) {
    stringBuffer.append(TEXT_257);
    if (!genDataType.isObjectType() && !genDataType.getObjectInstanceClassName().equals(genMemberType.getObjectInstanceClassName())) {
    stringBuffer.append(TEXT_258);
    stringBuffer.append(genDataType.getObjectInstanceClassName());
    stringBuffer.append(TEXT_259);
    }
    stringBuffer.append(TEXT_260);
    stringBuffer.append(genMemberType.getName());
    stringBuffer.append(TEXT_261);
    stringBuffer.append(genMemberType.getQualifiedClassifierAccessor());
    stringBuffer.append(TEXT_262);
    } else {
    stringBuffer.append(TEXT_263);
    if (!genDataType.isObjectType()) {
    stringBuffer.append(TEXT_264);
    stringBuffer.append(genDataType.getObjectInstanceClassName());
    stringBuffer.append(TEXT_265);
    }
    stringBuffer.append(genMemberType.getGenPackage().getQualifiedEFactoryInternalInstanceAccessor());
    stringBuffer.append(TEXT_266);
    stringBuffer.append(genMemberType.getQualifiedClassifierAccessor());
    stringBuffer.append(TEXT_267);
    }
    stringBuffer.append(TEXT_268);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.Diagnostician"));
    stringBuffer.append(TEXT_269);
    }
    stringBuffer.append(TEXT_270);
    }
    } else if (genPackage.isDataTypeConverters() || genDataType.hasCreatorBody()) {
    stringBuffer.append(TEXT_271);
    stringBuffer.append(genDataType.getName());
    stringBuffer.append(TEXT_272);
    } else if (!genDataType.hasConversionDelegate() && genModel.useGenerics() && (genDataType.isArrayType() || !genDataType.getEcoreDataType().getETypeParameters().isEmpty() || genDataType.getEcoreDataType().getInstanceTypeName().contains("<"))) {
    stringBuffer.append(TEXT_273);
    if (!genDataType.isObjectType()) {
    stringBuffer.append(TEXT_274);
    stringBuffer.append(genDataType.getImportedParameterizedObjectInstanceClassName());
    stringBuffer.append(TEXT_275);
    }
    stringBuffer.append(TEXT_276);
    } else if (!genDataType.hasConversionDelegate() && genDataType.isArrayType()) {
    stringBuffer.append(TEXT_277);
    stringBuffer.append(genModel.getImportedName("java.lang.UnsupportedOperationException"));
    stringBuffer.append(TEXT_278);
    } else {
    stringBuffer.append(TEXT_279);
    if (!genDataType.isObjectType()) {
    stringBuffer.append(TEXT_280);
    stringBuffer.append(genDataType.getImportedParameterizedObjectInstanceClassName());
    stringBuffer.append(TEXT_281);
    }
    stringBuffer.append(TEXT_282);
    }
    stringBuffer.append(TEXT_283);
    if (genPackage.isDataTypeConverters() || genDataType.hasConverterBody()) { String eDataType = genDataType.getQualifiedClassifierAccessor();
    stringBuffer.append(TEXT_284);
    if (genDataType.hasAPITags()) {
    stringBuffer.append(TEXT_285);
    stringBuffer.append(genDataType.getAPITags(genModel.getIndentation(stringBuffer)));
    }
    stringBuffer.append(TEXT_286);
    if (isJDK50 && genDataType.hasAPIDeprecatedTag()) {
    stringBuffer.append(TEXT_287);
    }
    stringBuffer.append(TEXT_288);
    stringBuffer.append(genDataType.getName());
    stringBuffer.append(TEXT_289);
    if (genDataType.hasCreatorBody()) {
    stringBuffer.append(TEXT_290);
    }
    stringBuffer.append(genDataType.getImportedBoundedWildcardInstanceClassName());
    stringBuffer.append(TEXT_291);
    if (genDataType.hasConverterBody()) {
    stringBuffer.append(TEXT_292);
    } else {
    stringBuffer.append(TEXT_293);
    }
    stringBuffer.append(TEXT_294);
    if (genDataType.hasConverterBody()) {
    stringBuffer.append(TEXT_295);
    stringBuffer.append(genDataType.getConverterBody(genModel.getIndentation(stringBuffer)));
    } else if (genDataType instanceof GenEnum) {
    stringBuffer.append(TEXT_296);
    } else if (genDataType.getBaseType() != null) { GenDataType genBaseType = genDataType.getBaseType(); boolean isPrimitiveConversion = !genDataType.isPrimitiveType() && genBaseType.isPrimitiveType();
    if (genBaseType.getGenPackage() == genPackage) {
    if (isPrimitiveConversion) {
    stringBuffer.append(TEXT_297);
    stringBuffer.append(genBaseType.getName());
    stringBuffer.append(TEXT_298);
    if (!isJDK50) {
    stringBuffer.append(TEXT_299);
    stringBuffer.append(genBaseType.getPrimitiveValueFunction());
    stringBuffer.append(TEXT_300);
    }
    stringBuffer.append(TEXT_301);
    } else {
    stringBuffer.append(TEXT_302);
    stringBuffer.append(genBaseType.getName());
    stringBuffer.append(TEXT_303);
    }
    } else if (genBaseType.getGenPackage().isDataTypeConverters()) {
    stringBuffer.append(TEXT_304);
    stringBuffer.append(genBaseType.getGenPackage().getQualifiedFactoryInstanceAccessor());
    stringBuffer.append(TEXT_305);
    stringBuffer.append(genBaseType.getName());
    stringBuffer.append(TEXT_306);
    } else {
    stringBuffer.append(TEXT_307);
    stringBuffer.append(genBaseType.getGenPackage().getQualifiedEFactoryInternalInstanceAccessor());
    stringBuffer.append(TEXT_308);
    stringBuffer.append(genBaseType.getQualifiedClassifierAccessor());
    stringBuffer.append(TEXT_309);
    }
    } else if (genDataType.getItemType() != null) { GenDataType genItemType = genDataType.getItemType(); 
    stringBuffer.append(TEXT_310);
    stringBuffer.append(genModel.getImportedName("java.lang.StringBuffer"));
    stringBuffer.append(TEXT_311);
    stringBuffer.append(genModel.getImportedName("java.lang.StringBuffer"));
    stringBuffer.append(TEXT_312);
    String item; if (!genModel.useGenerics()) { item = "i.next()"; 
    stringBuffer.append(TEXT_313);
    stringBuffer.append(genModel.getImportedName("java.util.Iterator"));
    stringBuffer.append(TEXT_314);
    } else { item = "item";
    stringBuffer.append(TEXT_315);
    stringBuffer.append(genModel.getImportedName("java.lang.Object"));
    stringBuffer.append(TEXT_316);
    }
    stringBuffer.append(TEXT_317);
    if (genItemType.getGenPackage() == genPackage) {
    if (genPackage.isDataTypeConverters()) { genItemType = genItemType.getObjectType();
    stringBuffer.append(TEXT_318);
    stringBuffer.append(genItemType.getName());
    stringBuffer.append(TEXT_319);
    stringBuffer.append(genItemType.getObjectInstanceClassName());
    stringBuffer.append(TEXT_320);
    stringBuffer.append(item);
    stringBuffer.append(TEXT_321);
    } else {
    stringBuffer.append(TEXT_322);
    stringBuffer.append(genItemType.getName());
    stringBuffer.append(TEXT_323);
    stringBuffer.append(genItemType.getQualifiedClassifierAccessor());
    stringBuffer.append(TEXT_324);
    stringBuffer.append(item);
    stringBuffer.append(TEXT_325);
    }
    } else {
    if (genItemType.getGenPackage().isDataTypeConverters()) { genItemType = genItemType.getObjectType();
    stringBuffer.append(TEXT_326);
    stringBuffer.append(genItemType.getGenPackage().getQualifiedEFactoryInternalInstanceAccessor());
    stringBuffer.append(TEXT_327);
    stringBuffer.append(genItemType.getName());
    stringBuffer.append(TEXT_328);
    stringBuffer.append(genItemType.getObjectInstanceClassName());
    stringBuffer.append(TEXT_329);
    stringBuffer.append(item);
    stringBuffer.append(TEXT_330);
    } else {
    stringBuffer.append(TEXT_331);
    stringBuffer.append(genItemType.getGenPackage().getQualifiedEFactoryInternalInstanceAccessor());
    stringBuffer.append(TEXT_332);
    stringBuffer.append(genItemType.getQualifiedClassifierAccessor());
    stringBuffer.append(TEXT_333);
    stringBuffer.append(item);
    stringBuffer.append(TEXT_334);
    }
    }
    stringBuffer.append(TEXT_335);
    } else if (!genDataType.getMemberTypes().isEmpty()) {
    if (!genDataType.isPrimitiveType()) {
    stringBuffer.append(TEXT_336);
    for (GenDataType genMemberType : genDataType.getMemberTypes()) {
    stringBuffer.append(TEXT_337);
    stringBuffer.append(genMemberType.getQualifiedClassifierAccessor());
    stringBuffer.append(TEXT_338);
    if (genMemberType.getGenPackage() == genPackage) {
    if (genPackage.isDataTypeConverters()) {
    if (genMemberType.getQualifiedInstanceClassName().equals(genDataType.getQualifiedInstanceClassName())) {
    stringBuffer.append(TEXT_339);
    stringBuffer.append(genMemberType.getName());
    stringBuffer.append(TEXT_340);
    } else if (genMemberType.isPrimitiveType() && !isJDK50) {
    stringBuffer.append(TEXT_341);
    stringBuffer.append(genMemberType.getName());
    stringBuffer.append(TEXT_342);
    stringBuffer.append(genMemberType.getObjectType().getImportedInstanceClassName());
    stringBuffer.append(TEXT_343);
    stringBuffer.append(genMemberType.getPrimitiveValueFunction());
    stringBuffer.append(TEXT_344);
    } else {
    stringBuffer.append(TEXT_345);
    stringBuffer.append(genMemberType.getName());
    stringBuffer.append(TEXT_346);
    stringBuffer.append(genMemberType.getObjectType().getImportedBoundedWildcardInstanceClassName());
    stringBuffer.append(TEXT_347);
    }
    } else {
    stringBuffer.append(TEXT_348);
    stringBuffer.append(genMemberType.getName());
    stringBuffer.append(TEXT_349);
    stringBuffer.append(genMemberType.getQualifiedClassifierAccessor());
    stringBuffer.append(TEXT_350);
    }
    } else {
    if (genMemberType.getGenPackage().isDataTypeConverters()) { genMemberType = genMemberType.getObjectType();
    stringBuffer.append(TEXT_351);
    stringBuffer.append(genMemberType.getGenPackage().getQualifiedEFactoryInternalInstanceAccessor());
    stringBuffer.append(TEXT_352);
    stringBuffer.append(genMemberType.getName());
    stringBuffer.append(TEXT_353);
    stringBuffer.append(genMemberType.getObjectInstanceClassName());
    stringBuffer.append(TEXT_354);
    } else {
    stringBuffer.append(TEXT_355);
    stringBuffer.append(genMemberType.getGenPackage().getQualifiedEFactoryInternalInstanceAccessor());
    stringBuffer.append(TEXT_356);
    stringBuffer.append(genMemberType.getQualifiedClassifierAccessor());
    stringBuffer.append(TEXT_357);
    }
    }
    stringBuffer.append(TEXT_358);
    }
    } else {
    for (GenDataType genMemberType : genDataType.getMemberTypes()) {
    stringBuffer.append(TEXT_359);
    if (genMemberType.getGenPackage() == genPackage) {
    if (genPackage.isDataTypeConverters()) {
    stringBuffer.append(TEXT_360);
    stringBuffer.append(genMemberType.getName());
    stringBuffer.append(TEXT_361);
    } else {
    stringBuffer.append(TEXT_362);
    stringBuffer.append(genMemberType.getName());
    stringBuffer.append(TEXT_363);
    stringBuffer.append(genMemberType.getQualifiedClassifierAccessor());
    stringBuffer.append(TEXT_364);
    if (!isJDK50) {
    stringBuffer.append(TEXT_365);
    stringBuffer.append(genMemberType.getObjectInstanceClassName());
    stringBuffer.append(TEXT_366);
    } else {
    stringBuffer.append(TEXT_367);
    }
    stringBuffer.append(TEXT_368);
    }
    } else {
    if (genMemberType.getGenPackage().isDataTypeConverters()) {
    stringBuffer.append(TEXT_369);
    stringBuffer.append(genMemberType.getGenPackage().getQualifiedEFactoryInternalInstanceAccessor());
    stringBuffer.append(TEXT_370);
    stringBuffer.append(genMemberType.getName());
    stringBuffer.append(TEXT_371);
    } else {
    stringBuffer.append(TEXT_372);
    stringBuffer.append(genMemberType.getGenPackage().getQualifiedEFactoryInternalInstanceAccessor());
    stringBuffer.append(TEXT_373);
    stringBuffer.append(genMemberType.getQualifiedClassifierAccessor());
    stringBuffer.append(TEXT_374);
    if (!isJDK50) {
    stringBuffer.append(TEXT_375);
    stringBuffer.append(genMemberType.getObjectInstanceClassName());
    stringBuffer.append(TEXT_376);
    } else {
    stringBuffer.append(TEXT_377);
    }
    stringBuffer.append(TEXT_378);
    }
    }
    stringBuffer.append(TEXT_379);
    }
    }
    stringBuffer.append(TEXT_380);
    stringBuffer.append(eDataType);
    stringBuffer.append(TEXT_381);
    } else if (!genDataType.hasConversionDelegate() && genModel.useGenerics() && (genDataType.isArrayType() || !genDataType.getEcoreDataType().getETypeParameters().isEmpty() || genDataType.getEcoreDataType().getInstanceTypeName().contains("<"))) {
    stringBuffer.append(TEXT_382);
    } else if (!genDataType.hasConversionDelegate() && genDataType.isArrayType()) {
    stringBuffer.append(TEXT_383);
    stringBuffer.append(genModel.getImportedName("java.lang.UnsupportedOperationException"));
    stringBuffer.append(TEXT_384);
    } else if (genDataType.isPrimitiveType() && !isJDK50) {
    stringBuffer.append(TEXT_385);
    stringBuffer.append(eDataType);
    stringBuffer.append(TEXT_386);
    stringBuffer.append(genDataType.getObjectInstanceClassName());
    stringBuffer.append(TEXT_387);
    } else {
    stringBuffer.append(TEXT_388);
    stringBuffer.append(eDataType);
    stringBuffer.append(TEXT_389);
    }
    stringBuffer.append(TEXT_390);
    }
    stringBuffer.append(TEXT_391);
    if (genDataType.hasAPITags()) {
    stringBuffer.append(TEXT_392);
    stringBuffer.append(genDataType.getAPITags(genModel.getIndentation(stringBuffer)));
    }
    stringBuffer.append(TEXT_393);
    if (genModel.useGenerics() && (genDataType.getItemType() != null || genDataType.isUncheckedCast()) && (genPackage.isDataTypeConverters() || genDataType.hasCreatorBody())) {
    stringBuffer.append(TEXT_394);
    }
    if (isJDK50 && genDataType.hasAPIDeprecatedTag()) {
    stringBuffer.append(TEXT_395);
    }
    stringBuffer.append(TEXT_396);
    stringBuffer.append(genDataType.getName());
    stringBuffer.append(TEXT_397);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.EDataType"));
    stringBuffer.append(TEXT_398);
    if (genDataType instanceof GenEnum) {
    if (genPackage.isDataTypeConverters() || genDataType.hasConverterBody()) {
    stringBuffer.append(TEXT_399);
    stringBuffer.append(genDataType.getName());
    stringBuffer.append(TEXT_400);
    stringBuffer.append(genDataType.getImportedInstanceClassName());
    stringBuffer.append(TEXT_401);
    } else {
    stringBuffer.append(TEXT_402);
    }
    } else if (genDataType.getBaseType() != null) { GenDataType genBaseType = genDataType.getBaseType(); 
    if (genBaseType.getGenPackage() == genPackage) {
    stringBuffer.append(TEXT_403);
    stringBuffer.append(genBaseType.getName());
    stringBuffer.append(TEXT_404);
    stringBuffer.append(genBaseType.getQualifiedClassifierAccessor());
    stringBuffer.append(TEXT_405);
    } else {
    stringBuffer.append(TEXT_406);
    stringBuffer.append(genBaseType.getGenPackage().getQualifiedEFactoryInternalInstanceAccessor());
    stringBuffer.append(TEXT_407);
    stringBuffer.append(genBaseType.getQualifiedClassifierAccessor());
    stringBuffer.append(TEXT_408);
    }
    } else if (genDataType.getItemType() != null) { GenDataType genItemType = genDataType.getItemType(); 
    if (genPackage.isDataTypeConverters() || genDataType.hasCreatorBody()) {
    stringBuffer.append(TEXT_409);
    stringBuffer.append(genDataType.getName());
    stringBuffer.append(TEXT_410);
    stringBuffer.append(genDataType.getImportedBoundedWildcardInstanceClassName());
    stringBuffer.append(TEXT_411);
    } else { final String singleWildcard = genModel.useGenerics() ? "<?>" : "";
    stringBuffer.append(TEXT_412);
    stringBuffer.append(genModel.getImportedName("java.util.List"));
    stringBuffer.append(singleWildcard);
    stringBuffer.append(TEXT_413);
    stringBuffer.append(genModel.getImportedName("java.util.List"));
    stringBuffer.append(singleWildcard);
    stringBuffer.append(TEXT_414);
    stringBuffer.append(genModel.getImportedName("java.lang.StringBuffer"));
    stringBuffer.append(TEXT_415);
    stringBuffer.append(genModel.getImportedName("java.lang.StringBuffer"));
    stringBuffer.append(TEXT_416);
    String item; if (!genModel.useGenerics()) { item = "i.next()"; 
    stringBuffer.append(TEXT_417);
    stringBuffer.append(genModel.getImportedName("java.util.Iterator"));
    stringBuffer.append(TEXT_418);
    } else { item = "item";
    stringBuffer.append(TEXT_419);
    stringBuffer.append(genModel.getImportedName("java.lang.Object"));
    stringBuffer.append(TEXT_420);
    }
    stringBuffer.append(TEXT_421);
    if (genItemType.getGenPackage() == genPackage) {
    stringBuffer.append(TEXT_422);
    stringBuffer.append(genItemType.getName());
    stringBuffer.append(TEXT_423);
    stringBuffer.append(genItemType.getQualifiedClassifierAccessor());
    stringBuffer.append(TEXT_424);
    stringBuffer.append(item);
    stringBuffer.append(TEXT_425);
    } else {
    stringBuffer.append(TEXT_426);
    stringBuffer.append(genItemType.getGenPackage().getQualifiedEFactoryInternalInstanceAccessor());
    stringBuffer.append(TEXT_427);
    stringBuffer.append(genItemType.getQualifiedClassifierAccessor());
    stringBuffer.append(TEXT_428);
    stringBuffer.append(item);
    stringBuffer.append(TEXT_429);
    }
    stringBuffer.append(TEXT_430);
    }
    } else if (!genDataType.getMemberTypes().isEmpty()) {
    if (genPackage.isDataTypeConverters() || genDataType.hasConverterBody()) {
    if (genDataType.isPrimitiveType()) {
    stringBuffer.append(TEXT_431);
    stringBuffer.append(genDataType.getName());
    stringBuffer.append(TEXT_432);
    stringBuffer.append(genDataType.getObjectInstanceClassName());
    stringBuffer.append(TEXT_433);
    if (!isJDK50) {
    stringBuffer.append(TEXT_434);
    stringBuffer.append(genDataType.getPrimitiveValueFunction());
    stringBuffer.append(TEXT_435);
    }
    stringBuffer.append(TEXT_436);
    } else {
    stringBuffer.append(TEXT_437);
    stringBuffer.append(genDataType.getName());
    stringBuffer.append(TEXT_438);
    }
    } else {
    stringBuffer.append(TEXT_439);
    for (GenDataType genMemberType : genDataType.getMemberTypes()) {
    stringBuffer.append(TEXT_440);
    stringBuffer.append(genMemberType.getQualifiedClassifierAccessor());
    stringBuffer.append(TEXT_441);
    if (genMemberType.getGenPackage() == genPackage) {
    stringBuffer.append(TEXT_442);
    stringBuffer.append(genMemberType.getName());
    stringBuffer.append(TEXT_443);
    stringBuffer.append(genMemberType.getQualifiedClassifierAccessor());
    stringBuffer.append(TEXT_444);
    } else {
    stringBuffer.append(TEXT_445);
    stringBuffer.append(genMemberType.getGenPackage().getQualifiedEFactoryInternalInstanceAccessor());
    stringBuffer.append(TEXT_446);
    stringBuffer.append(genMemberType.getQualifiedClassifierAccessor());
    stringBuffer.append(TEXT_447);
    }
    stringBuffer.append(TEXT_448);
    }
    stringBuffer.append(TEXT_449);
    }
    } else if (genPackage.isDataTypeConverters() || genDataType.hasConverterBody()) {
    if (genDataType.isPrimitiveType()) {
    stringBuffer.append(TEXT_450);
    stringBuffer.append(genDataType.getName());
    stringBuffer.append(TEXT_451);
    if (!isJDK50) {
    stringBuffer.append(TEXT_452);
    }
    stringBuffer.append(TEXT_453);
    stringBuffer.append(genDataType.getObjectInstanceClassName());
    stringBuffer.append(TEXT_454);
    if (!isJDK50) {
    stringBuffer.append(TEXT_455);
    stringBuffer.append(genDataType.getPrimitiveValueFunction());
    stringBuffer.append(TEXT_456);
    }
    stringBuffer.append(TEXT_457);
    } else {
    stringBuffer.append(TEXT_458);
    stringBuffer.append(genDataType.getName());
    stringBuffer.append(TEXT_459);
    stringBuffer.append(genDataType.getImportedBoundedWildcardInstanceClassName());
    stringBuffer.append(TEXT_460);
    }
    } else if (!genDataType.hasConversionDelegate() && genModel.useGenerics() && (genDataType.isArrayType() || !genDataType.getEcoreDataType().getETypeParameters().isEmpty() || genDataType.getEcoreDataType().getInstanceTypeName().contains("<"))) {
    stringBuffer.append(TEXT_461);
    } else if (!genDataType.hasConversionDelegate() && genDataType.isArrayType()) {
    stringBuffer.append(TEXT_462);
    stringBuffer.append(genModel.getImportedName("java.lang.UnsupportedOperationException"));
    stringBuffer.append(TEXT_463);
    } else {
    stringBuffer.append(TEXT_464);
    }
    stringBuffer.append(TEXT_465);
    }
    }
    } else {
    for (GenClass genClass : genPackage.getGenClasses()) {
    if (genClass.hasFactoryInterfaceCreateMethod()) {
    stringBuffer.append(TEXT_466);
    stringBuffer.append(genClass.getFormattedName());
    stringBuffer.append(TEXT_467);
    stringBuffer.append(genClass.getFormattedName());
    stringBuffer.append(TEXT_468);
    if (genClass.hasAPITags()) {
    stringBuffer.append(TEXT_469);
    stringBuffer.append(genClass.getAPITags(genModel.getIndentation(stringBuffer)));
    }
    stringBuffer.append(TEXT_470);
    if (isJDK50 && genClass.hasAPIDeprecatedTag()) {
    stringBuffer.append(TEXT_471);
    }
    stringBuffer.append(TEXT_472);
    stringBuffer.append(genClass.getTypeParameters());
    stringBuffer.append(genClass.getImportedInterfaceName());
    stringBuffer.append(genClass.getInterfaceTypeArguments());
    stringBuffer.append(TEXT_473);
    stringBuffer.append(genClass.getName());
    stringBuffer.append(TEXT_474);
    }
    }
    if (genPackage.isDataTypeConverters()) {
    for (GenDataType genDataType : genPackage.getAllGenDataTypes()) {
    if (genDataType.isSerializable()) {
    stringBuffer.append(TEXT_475);
    stringBuffer.append(genDataType.getFormattedName());
    stringBuffer.append(TEXT_476);
    if (genDataType.hasAPITags()) {
    stringBuffer.append(TEXT_477);
    stringBuffer.append(genDataType.getAPITags(genModel.getIndentation(stringBuffer)));
    }
    stringBuffer.append(TEXT_478);
    if (isJDK50 && genDataType.hasAPIDeprecatedTag()) {
    stringBuffer.append(TEXT_479);
    }
    stringBuffer.append(TEXT_480);
    stringBuffer.append(genDataType.getImportedParameterizedInstanceClassName());
    stringBuffer.append(TEXT_481);
    stringBuffer.append(genDataType.getName());
    stringBuffer.append(TEXT_482);
    stringBuffer.append(genDataType.getFormattedName());
    stringBuffer.append(TEXT_483);
    if (genDataType.hasAPITags()) {
    stringBuffer.append(TEXT_484);
    stringBuffer.append(genDataType.getAPITags(genModel.getIndentation(stringBuffer)));
    }
    stringBuffer.append(TEXT_485);
    if (isJDK50 && genDataType.hasAPIDeprecatedTag()) {
    stringBuffer.append(TEXT_486);
    }
    stringBuffer.append(TEXT_487);
    stringBuffer.append(genDataType.getName());
    stringBuffer.append(TEXT_488);
    stringBuffer.append(genDataType.getImportedBoundedWildcardInstanceClassName());
    stringBuffer.append(TEXT_489);
    }
    }
    }
    }
    if (!isImplementation && !genModel.isSuppressEMFMetaData()) {
    stringBuffer.append(TEXT_490);
    stringBuffer.append(genPackage.getPackageInterfaceName());
    stringBuffer.append(TEXT_491);
    stringBuffer.append(genPackage.getBasicPackageName());
    stringBuffer.append(TEXT_492);
    } else if (isImplementation) {
    stringBuffer.append(TEXT_493);
    stringBuffer.append(genPackage.getImportedPackageInterfaceName());
    stringBuffer.append(TEXT_494);
    stringBuffer.append(genPackage.getBasicPackageName());
    stringBuffer.append(TEXT_495);
    stringBuffer.append(genPackage.getImportedPackageInterfaceName());
    stringBuffer.append(TEXT_496);
    if (genModel.useClassOverrideAnnotation()) {
    stringBuffer.append(TEXT_497);
    }
    stringBuffer.append(TEXT_498);
    stringBuffer.append(genPackage.getImportedPackageInterfaceName());
    stringBuffer.append(TEXT_499);
    stringBuffer.append(genPackage.getImportedPackageInterfaceName());
    stringBuffer.append(TEXT_500);
    }
    stringBuffer.append(TEXT_501);
    stringBuffer.append(isInterface ? genPackage.getFactoryInterfaceName() : genPackage.getFactoryClassName());
    genModel.emitSortedImports();
    stringBuffer.append(TEXT_502);
    return stringBuffer.toString();
  }
}
