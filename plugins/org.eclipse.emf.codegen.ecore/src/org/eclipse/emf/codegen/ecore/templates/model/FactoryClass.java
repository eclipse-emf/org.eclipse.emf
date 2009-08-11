package org.eclipse.emf.codegen.ecore.templates.model;

import java.util.*;
import org.eclipse.emf.codegen.ecore.genmodel.*;

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
  protected final String TEXT_4 = NL + " * <copyright>" + NL + " * </copyright>" + NL + " *" + NL + " * ";
  protected final String TEXT_5 = "Id";
  protected final String TEXT_6 = NL + " */";
  protected final String TEXT_7 = NL + "package ";
  protected final String TEXT_8 = ";";
  protected final String TEXT_9 = NL + "package ";
  protected final String TEXT_10 = ";";
  protected final String TEXT_11 = NL;
  protected final String TEXT_12 = NL;
  protected final String TEXT_13 = NL + "/**" + NL + " * <!-- begin-user-doc -->" + NL + " * The <b>Factory</b> for the model." + NL + " * It provides a create method for each non-abstract class of the model." + NL + " * <!-- end-user-doc -->";
  protected final String TEXT_14 = NL + " * @see ";
  protected final String TEXT_15 = NL + " * @generated" + NL + " */";
  protected final String TEXT_16 = NL + "/**" + NL + " * <!-- begin-user-doc -->" + NL + " * An implementation of the model <b>Factory</b>." + NL + " * <!-- end-user-doc -->" + NL + " * @generated" + NL + " */";
  protected final String TEXT_17 = NL + "public class ";
  protected final String TEXT_18 = " extends ";
  protected final String TEXT_19 = " implements ";
  protected final String TEXT_20 = NL + "public interface ";
  protected final String TEXT_21 = " extends ";
  protected final String TEXT_22 = NL + "{";
  protected final String TEXT_23 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */" + NL + "\t";
  protected final String TEXT_24 = " copyright = ";
  protected final String TEXT_25 = ";";
  protected final String TEXT_26 = NL;
  protected final String TEXT_27 = NL + "\t/**" + NL + "\t * The singleton instance of the factory." + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */" + NL + "\t";
  protected final String TEXT_28 = " eINSTANCE = init();" + NL;
  protected final String TEXT_29 = NL + "\t/**" + NL + "\t * The singleton instance of the factory." + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */" + NL + "\t";
  protected final String TEXT_30 = " INSTANCE = ";
  protected final String TEXT_31 = ".eINSTANCE;" + NL;
  protected final String TEXT_32 = NL + "\t/**" + NL + "\t * The singleton instance of the factory." + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */" + NL + "\t";
  protected final String TEXT_33 = " eINSTANCE = ";
  protected final String TEXT_34 = ".init();" + NL;
  protected final String TEXT_35 = NL + "\t/**" + NL + "\t * Creates the default factory implementation." + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */";
  protected final String TEXT_36 = NL + "\tpublic static ";
  protected final String TEXT_37 = " init()" + NL + "\t{" + NL + "\t\ttry" + NL + "\t\t{" + NL + "\t\t\t";
  protected final String TEXT_38 = " the";
  protected final String TEXT_39 = " = (";
  protected final String TEXT_40 = ")";
  protected final String TEXT_41 = ".Registry.INSTANCE.getEFactory(\"";
  protected final String TEXT_42 = "\");";
  protected final String TEXT_43 = " " + NL + "\t\t\tif (the";
  protected final String TEXT_44 = " != null)" + NL + "\t\t\t{" + NL + "\t\t\t\treturn the";
  protected final String TEXT_45 = ";" + NL + "\t\t\t}" + NL + "\t\t}" + NL + "\t\tcatch (Exception exception)" + NL + "\t\t{" + NL + "\t\t\t";
  protected final String TEXT_46 = ".INSTANCE.log(exception);" + NL + "\t\t}" + NL + "\t\treturn new ";
  protected final String TEXT_47 = "();" + NL + "\t}" + NL + "" + NL + "\t/**" + NL + "\t * Creates an instance of the factory." + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */" + NL + "\tpublic ";
  protected final String TEXT_48 = "()" + NL + "\t{" + NL + "\t\tsuper();" + NL + "\t}" + NL + "" + NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */";
  protected final String TEXT_49 = NL + "\t@Override";
  protected final String TEXT_50 = NL + "\tpublic EObject create(EClass eClass)" + NL + "\t{" + NL + "\t\tswitch (eClass.getClassifierID())" + NL + "\t\t{";
  protected final String TEXT_51 = NL + "\t\t\tcase ";
  protected final String TEXT_52 = ".";
  protected final String TEXT_53 = ": return ";
  protected final String TEXT_54 = "create";
  protected final String TEXT_55 = "();";
  protected final String TEXT_56 = NL + "\t\t\tdefault:" + NL + "\t\t\t\tthrow new IllegalArgumentException(\"The class '\" + eClass.getName() + \"' is not a valid classifier\");";
  protected final String TEXT_57 = NL + "\t\t}" + NL + "\t}" + NL;
  protected final String TEXT_58 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */";
  protected final String TEXT_59 = NL + "\t@Override";
  protected final String TEXT_60 = NL + "\tpublic Object createFromString(";
  protected final String TEXT_61 = " eDataType, String initialValue)" + NL + "\t{" + NL + "\t\tswitch (eDataType.getClassifierID())" + NL + "\t\t{";
  protected final String TEXT_62 = NL + "\t\t\tcase ";
  protected final String TEXT_63 = ".";
  protected final String TEXT_64 = ":" + NL + "\t\t\t\treturn create";
  protected final String TEXT_65 = "FromString(eDataType, initialValue);";
  protected final String TEXT_66 = NL + "\t\t\tdefault:" + NL + "\t\t\t\tthrow new IllegalArgumentException(\"The datatype '\" + eDataType.getName() + \"' is not a valid classifier\");";
  protected final String TEXT_67 = NL + "\t\t}" + NL + "\t}" + NL + "" + NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */";
  protected final String TEXT_68 = NL + "\t@Override";
  protected final String TEXT_69 = NL + "\tpublic String convertToString(";
  protected final String TEXT_70 = " eDataType, Object instanceValue)" + NL + "\t{" + NL + "\t\tswitch (eDataType.getClassifierID())" + NL + "\t\t{";
  protected final String TEXT_71 = NL + "\t\t\tcase ";
  protected final String TEXT_72 = ".";
  protected final String TEXT_73 = ":" + NL + "\t\t\t\treturn convert";
  protected final String TEXT_74 = "ToString(eDataType, instanceValue);";
  protected final String TEXT_75 = NL + "\t\t\tdefault:" + NL + "\t\t\t\tthrow new IllegalArgumentException(\"The datatype '\" + eDataType.getName() + \"' is not a valid classifier\");";
  protected final String TEXT_76 = NL + "\t\t}" + NL + "\t}" + NL;
  protected final String TEXT_77 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */" + NL + "\tpublic ";
  protected final String TEXT_78 = " create";
  protected final String TEXT_79 = "()" + NL + "\t{";
  protected final String TEXT_80 = NL + "\t\t";
  protected final String TEXT_81 = " ";
  protected final String TEXT_82 = " = ";
  protected final String TEXT_83 = "super.create(";
  protected final String TEXT_84 = ");";
  protected final String TEXT_85 = NL + "\t\t";
  protected final String TEXT_86 = " ";
  protected final String TEXT_87 = " = new ";
  protected final String TEXT_88 = "()";
  protected final String TEXT_89 = "{}";
  protected final String TEXT_90 = ";";
  protected final String TEXT_91 = NL + "\t\treturn ";
  protected final String TEXT_92 = ";" + NL + "\t}" + NL;
  protected final String TEXT_93 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */";
  protected final String TEXT_94 = NL + "\t@SuppressWarnings(\"unchecked\")";
  protected final String TEXT_95 = NL + "\tpublic ";
  protected final String TEXT_96 = " create";
  protected final String TEXT_97 = "(String literal)" + NL + "\t{";
  protected final String TEXT_98 = NL + "\t\t";
  protected final String TEXT_99 = " result = ";
  protected final String TEXT_100 = ".get(literal);" + NL + "\t\tif (result == null) throw new IllegalArgumentException(\"The value '\" + literal + \"' is not a valid enumerator of '\" + ";
  protected final String TEXT_101 = ".getName() + \"'\");";
  protected final String TEXT_102 = NL + "\t\treturn result;";
  protected final String TEXT_103 = NL + "\t\treturn new ";
  protected final String TEXT_104 = "(create";
  protected final String TEXT_105 = "(literal));";
  protected final String TEXT_106 = NL + "\t\treturn create";
  protected final String TEXT_107 = "(literal);";
  protected final String TEXT_108 = NL + "\t\treturn new ";
  protected final String TEXT_109 = "(";
  protected final String TEXT_110 = ".create";
  protected final String TEXT_111 = "(literal));";
  protected final String TEXT_112 = NL + "\t\treturn ";
  protected final String TEXT_113 = ".create";
  protected final String TEXT_114 = "(literal);";
  protected final String TEXT_115 = NL + "\t\treturn ";
  protected final String TEXT_116 = "(";
  protected final String TEXT_117 = ")";
  protected final String TEXT_118 = ".createFromString(";
  protected final String TEXT_119 = ", literal);";
  protected final String TEXT_120 = NL + "\t\tif (literal == null) return null;" + NL + "\t\t";
  protected final String TEXT_121 = " result = new ";
  protected final String TEXT_122 = "<";
  protected final String TEXT_123 = ">";
  protected final String TEXT_124 = "();" + NL + "\t\tfor (";
  protected final String TEXT_125 = " stringTokenizer = new ";
  protected final String TEXT_126 = "(literal); stringTokenizer.hasMoreTokens(); )" + NL + "\t\t{" + NL + "\t\t\tString item = stringTokenizer.nextToken();";
  protected final String TEXT_127 = NL + "\t\t\tresult.add(create";
  protected final String TEXT_128 = "(item));";
  protected final String TEXT_129 = NL + "\t\t\tresult.add(create";
  protected final String TEXT_130 = "FromString(";
  protected final String TEXT_131 = ", item));";
  protected final String TEXT_132 = NL + "\t\t\tresult.add(";
  protected final String TEXT_133 = ".create";
  protected final String TEXT_134 = "(item));";
  protected final String TEXT_135 = NL + "\t\t\tresult.add(";
  protected final String TEXT_136 = ".createFromString(";
  protected final String TEXT_137 = ", item));";
  protected final String TEXT_138 = NL + "\t\t}" + NL + "\t\treturn result;";
  protected final String TEXT_139 = NL + "\t\tif (literal == null) return ";
  protected final String TEXT_140 = ";" + NL + "\t\t";
  protected final String TEXT_141 = " result = ";
  protected final String TEXT_142 = ";" + NL + "\t\tRuntimeException exception = null;";
  protected final String TEXT_143 = NL + "\t\ttry" + NL + "\t\t{";
  protected final String TEXT_144 = NL + "\t\t\tresult = create";
  protected final String TEXT_145 = "(literal);";
  protected final String TEXT_146 = NL + "\t\t\tresult = (";
  protected final String TEXT_147 = ")create";
  protected final String TEXT_148 = "FromString(";
  protected final String TEXT_149 = ", literal);";
  protected final String TEXT_150 = NL + "\t\t\tresult = ";
  protected final String TEXT_151 = ".create";
  protected final String TEXT_152 = "(literal);";
  protected final String TEXT_153 = NL + "\t\t\tresult = (";
  protected final String TEXT_154 = ")";
  protected final String TEXT_155 = ".createFromString(";
  protected final String TEXT_156 = ", literal);";
  protected final String TEXT_157 = NL + "\t\t\tif (";
  protected final String TEXT_158 = "result != null && ";
  protected final String TEXT_159 = ".INSTANCE.validate(";
  protected final String TEXT_160 = ", ";
  protected final String TEXT_161 = "new ";
  protected final String TEXT_162 = "(result)";
  protected final String TEXT_163 = "result";
  protected final String TEXT_164 = ", null, null))" + NL + "\t\t\t{" + NL + "\t\t\t\treturn result;" + NL + "\t\t\t}" + NL + "\t\t}" + NL + "\t\tcatch (RuntimeException e)" + NL + "\t\t{" + NL + "\t\t\texception = e;" + NL + "\t\t}";
  protected final String TEXT_165 = NL + "\t\tif (";
  protected final String TEXT_166 = "result != null || ";
  protected final String TEXT_167 = "exception == null) return result;" + NL + "    " + NL + "\t\tthrow exception;";
  protected final String TEXT_168 = NL + "\t\treturn (";
  protected final String TEXT_169 = ")super.createFromString(literal);";
  protected final String TEXT_170 = NL + "\t\t// TODO: implement this method" + NL + "\t\t// Ensure that you remove @generated or mark it @generated NOT" + NL + "\t\tthrow new ";
  protected final String TEXT_171 = "();";
  protected final String TEXT_172 = NL + "\t\treturn ((";
  protected final String TEXT_173 = ")super.createFromString(";
  protected final String TEXT_174 = ", literal)).";
  protected final String TEXT_175 = "();";
  protected final String TEXT_176 = NL + "\t\treturn ";
  protected final String TEXT_177 = "(";
  protected final String TEXT_178 = ")";
  protected final String TEXT_179 = "super.createFromString(";
  protected final String TEXT_180 = ", literal);";
  protected final String TEXT_181 = NL + "\t}" + NL;
  protected final String TEXT_182 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */";
  protected final String TEXT_183 = NL + "\t@SuppressWarnings(\"unchecked\")";
  protected final String TEXT_184 = NL + "\tpublic ";
  protected final String TEXT_185 = " create";
  protected final String TEXT_186 = "FromString(";
  protected final String TEXT_187 = " eDataType, String initialValue)" + NL + "\t{";
  protected final String TEXT_188 = NL + "\t\treturn create";
  protected final String TEXT_189 = "(initialValue);";
  protected final String TEXT_190 = NL + "\t\t";
  protected final String TEXT_191 = " result = ";
  protected final String TEXT_192 = ".get(initialValue);" + NL + "\t\tif (result == null) throw new IllegalArgumentException(\"The value '\" + initialValue + \"' is not a valid enumerator of '\" + eDataType.getName() + \"'\");";
  protected final String TEXT_193 = NL + "\t\treturn result;";
  protected final String TEXT_194 = NL + "\t\treturn ";
  protected final String TEXT_195 = "(";
  protected final String TEXT_196 = ")";
  protected final String TEXT_197 = "create";
  protected final String TEXT_198 = "FromString(";
  protected final String TEXT_199 = ", initialValue);";
  protected final String TEXT_200 = NL + "\t\treturn ";
  protected final String TEXT_201 = "(";
  protected final String TEXT_202 = ")";
  protected final String TEXT_203 = ".createFromString(";
  protected final String TEXT_204 = ", initialValue);";
  protected final String TEXT_205 = NL + "\t\treturn create";
  protected final String TEXT_206 = "(initialValue);";
  protected final String TEXT_207 = NL + "\t\tif (initialValue == null) return null;" + NL + "\t\t";
  protected final String TEXT_208 = " result = new ";
  protected final String TEXT_209 = "<";
  protected final String TEXT_210 = ">";
  protected final String TEXT_211 = "();" + NL + "\t\tfor (";
  protected final String TEXT_212 = " stringTokenizer = new ";
  protected final String TEXT_213 = "(initialValue); stringTokenizer.hasMoreTokens(); )" + NL + "\t\t{" + NL + "\t\t\tString item = stringTokenizer.nextToken();";
  protected final String TEXT_214 = NL + "\t\t\tresult.add(create";
  protected final String TEXT_215 = "FromString(";
  protected final String TEXT_216 = ", item));";
  protected final String TEXT_217 = NL + "\t\t\tresult.add(";
  protected final String TEXT_218 = "(";
  protected final String TEXT_219 = ")";
  protected final String TEXT_220 = ".createFromString(";
  protected final String TEXT_221 = ", item));";
  protected final String TEXT_222 = NL + "\t\t}" + NL + "\t\treturn result;";
  protected final String TEXT_223 = NL + "\t\treturn new ";
  protected final String TEXT_224 = "(create";
  protected final String TEXT_225 = "(initialValue));";
  protected final String TEXT_226 = NL + "\t\treturn create";
  protected final String TEXT_227 = "(initialValue);";
  protected final String TEXT_228 = NL + "\t\tif (initialValue == null) return null;" + NL + "\t\t";
  protected final String TEXT_229 = " result = null;" + NL + "\t\tRuntimeException exception = null;";
  protected final String TEXT_230 = NL + "\t\ttry" + NL + "\t\t{";
  protected final String TEXT_231 = NL + "\t\t\tresult = ";
  protected final String TEXT_232 = "(";
  protected final String TEXT_233 = ")";
  protected final String TEXT_234 = "create";
  protected final String TEXT_235 = "FromString(";
  protected final String TEXT_236 = ", initialValue);";
  protected final String TEXT_237 = NL + "\t\t\tresult = ";
  protected final String TEXT_238 = "(";
  protected final String TEXT_239 = ")";
  protected final String TEXT_240 = ".createFromString(";
  protected final String TEXT_241 = ", initialValue);";
  protected final String TEXT_242 = NL + "\t\t\tif (result != null && ";
  protected final String TEXT_243 = ".INSTANCE.validate(eDataType, result, null, null))" + NL + "\t\t\t{" + NL + "\t\t\t\treturn result;" + NL + "\t\t\t}" + NL + "\t\t}" + NL + "\t\tcatch (RuntimeException e)" + NL + "\t\t{" + NL + "\t\t\texception = e;" + NL + "\t\t}";
  protected final String TEXT_244 = NL + "\t\tif (result != null || exception == null) return result;" + NL + "    " + NL + "\t\tthrow exception;";
  protected final String TEXT_245 = NL + "\t\treturn ";
  protected final String TEXT_246 = "(";
  protected final String TEXT_247 = ")";
  protected final String TEXT_248 = "super.createFromString(initialValue);";
  protected final String TEXT_249 = NL + "\t\t// TODO: implement this method" + NL + "\t\t// Ensure that you remove @generated or mark it @generated NOT" + NL + "\t\tthrow new ";
  protected final String TEXT_250 = "();";
  protected final String TEXT_251 = NL + "\t\treturn ";
  protected final String TEXT_252 = "(";
  protected final String TEXT_253 = ")";
  protected final String TEXT_254 = "super.createFromString(eDataType, initialValue);";
  protected final String TEXT_255 = NL + "\t}" + NL;
  protected final String TEXT_256 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */" + NL + "\tpublic String convert";
  protected final String TEXT_257 = "(";
  protected final String TEXT_258 = " instanceValue)" + NL + "\t{";
  protected final String TEXT_259 = NL + "\t\treturn instanceValue == null ? null : instanceValue.toString();";
  protected final String TEXT_260 = NL + "\t\treturn instanceValue == null ? null : convert";
  protected final String TEXT_261 = "(instanceValue";
  protected final String TEXT_262 = ".";
  protected final String TEXT_263 = "()";
  protected final String TEXT_264 = ");";
  protected final String TEXT_265 = NL + "\t\treturn convert";
  protected final String TEXT_266 = "(instanceValue);";
  protected final String TEXT_267 = NL + "\t\treturn ";
  protected final String TEXT_268 = ".convert";
  protected final String TEXT_269 = "(instanceValue);";
  protected final String TEXT_270 = NL + "\t\treturn ";
  protected final String TEXT_271 = ".convertToString(";
  protected final String TEXT_272 = ", instanceValue);";
  protected final String TEXT_273 = NL + "\t\tif (instanceValue == null) return null;" + NL + "\t\tif (instanceValue.isEmpty()) return \"\";" + NL + "\t\t";
  protected final String TEXT_274 = " result = new ";
  protected final String TEXT_275 = "();";
  protected final String TEXT_276 = NL + "\t\tfor (";
  protected final String TEXT_277 = " i = instanceValue.iterator(); i.hasNext(); )";
  protected final String TEXT_278 = NL + "\t\tfor (";
  protected final String TEXT_279 = " item : instanceValue)";
  protected final String TEXT_280 = NL + "\t\t{";
  protected final String TEXT_281 = NL + "\t\t\tresult.append(convert";
  protected final String TEXT_282 = "((";
  protected final String TEXT_283 = ")";
  protected final String TEXT_284 = "));";
  protected final String TEXT_285 = NL + "\t\t\tresult.append(convert";
  protected final String TEXT_286 = "ToString(";
  protected final String TEXT_287 = ", ";
  protected final String TEXT_288 = "));";
  protected final String TEXT_289 = NL + "\t\t\tresult.append(";
  protected final String TEXT_290 = ".convert";
  protected final String TEXT_291 = "((";
  protected final String TEXT_292 = ")";
  protected final String TEXT_293 = "));";
  protected final String TEXT_294 = NL + "\t\t\tresult.append(";
  protected final String TEXT_295 = ".convertToString(";
  protected final String TEXT_296 = ", ";
  protected final String TEXT_297 = "));";
  protected final String TEXT_298 = NL + "\t\t\tresult.append(' ');" + NL + "\t\t}" + NL + "\t\treturn result.substring(0, result.length() - 1);";
  protected final String TEXT_299 = NL + "\t\tif (instanceValue == null) return null;";
  protected final String TEXT_300 = NL + "\t\tif (";
  protected final String TEXT_301 = ".isInstance(instanceValue))" + NL + "\t\t{" + NL + "\t\t\ttry" + NL + "\t\t\t{";
  protected final String TEXT_302 = NL + "\t\t\t\tString value = convert";
  protected final String TEXT_303 = "(instanceValue);";
  protected final String TEXT_304 = NL + "\t\t\t\tString value = convert";
  protected final String TEXT_305 = "(((";
  protected final String TEXT_306 = ")instanceValue).";
  protected final String TEXT_307 = "());";
  protected final String TEXT_308 = NL + "\t\t\t\tString value = convert";
  protected final String TEXT_309 = "((";
  protected final String TEXT_310 = ")instanceValue);";
  protected final String TEXT_311 = NL + "\t\t\t\tString value = convert";
  protected final String TEXT_312 = "ToString(";
  protected final String TEXT_313 = ", instanceValue);";
  protected final String TEXT_314 = NL + "\t\t\t\tString value = ";
  protected final String TEXT_315 = ".convert";
  protected final String TEXT_316 = "((";
  protected final String TEXT_317 = ")instanceValue);";
  protected final String TEXT_318 = NL + "\t\t\t\tString value = ";
  protected final String TEXT_319 = ".convertToString(";
  protected final String TEXT_320 = ", instanceValue);";
  protected final String TEXT_321 = NL + "\t\t\t\tif (value != null) return value;" + NL + "\t\t\t}" + NL + "\t\t\tcatch (Exception e)" + NL + "\t\t\t{" + NL + "\t\t\t\t// Keep trying other member types until all have failed." + NL + "\t\t\t}" + NL + "\t\t}";
  protected final String TEXT_322 = NL + "\t\ttry" + NL + "\t\t{";
  protected final String TEXT_323 = NL + "\t\t\tString value = convert";
  protected final String TEXT_324 = "(instanceValue);";
  protected final String TEXT_325 = NL + "\t\t\tString value = convert";
  protected final String TEXT_326 = "ToString(";
  protected final String TEXT_327 = ", ";
  protected final String TEXT_328 = "new ";
  protected final String TEXT_329 = "(instanceValue)";
  protected final String TEXT_330 = "instanceValue";
  protected final String TEXT_331 = ");";
  protected final String TEXT_332 = NL + "\t\t\tString value = ";
  protected final String TEXT_333 = ".convert";
  protected final String TEXT_334 = "(instanceValue);";
  protected final String TEXT_335 = NL + "\t\t\tString value = ";
  protected final String TEXT_336 = ".convertToString(";
  protected final String TEXT_337 = ", ";
  protected final String TEXT_338 = "new ";
  protected final String TEXT_339 = "(instanceValue)";
  protected final String TEXT_340 = "instanceValue";
  protected final String TEXT_341 = ");";
  protected final String TEXT_342 = NL + "\t\t\tif (value != null) return value;" + NL + "\t\t}" + NL + "\t\tcatch (Exception e)" + NL + "\t\t{" + NL + "\t\t\t// Keep trying other member types until all have failed." + NL + "\t\t}";
  protected final String TEXT_343 = NL + "\t\tthrow new IllegalArgumentException(\"Invalid value: '\"+instanceValue+\"' for datatype :\"+";
  protected final String TEXT_344 = ".getName());";
  protected final String TEXT_345 = NL + "\t\treturn super.convertToString(instanceValue);";
  protected final String TEXT_346 = NL + "\t\t// TODO: implement this method" + NL + "\t\t// Ensure that you remove @generated or mark it @generated NOT" + NL + "\t\tthrow new ";
  protected final String TEXT_347 = "();";
  protected final String TEXT_348 = NL + "\t\treturn super.convertToString(";
  protected final String TEXT_349 = ", new ";
  protected final String TEXT_350 = "(instanceValue));";
  protected final String TEXT_351 = NL + "\t\treturn super.convertToString(";
  protected final String TEXT_352 = ", instanceValue);";
  protected final String TEXT_353 = NL + "\t}" + NL;
  protected final String TEXT_354 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */";
  protected final String TEXT_355 = NL + "\t@SuppressWarnings(\"unchecked\")";
  protected final String TEXT_356 = NL + "\tpublic String convert";
  protected final String TEXT_357 = "ToString(";
  protected final String TEXT_358 = " eDataType, Object instanceValue)" + NL + "\t{";
  protected final String TEXT_359 = NL + "\t\treturn instanceValue == null ? null : instanceValue.toString();";
  protected final String TEXT_360 = NL + "\t\treturn convert";
  protected final String TEXT_361 = "ToString(";
  protected final String TEXT_362 = ", instanceValue);";
  protected final String TEXT_363 = NL + "\t\treturn ";
  protected final String TEXT_364 = ".convertToString(";
  protected final String TEXT_365 = ", instanceValue);";
  protected final String TEXT_366 = NL + "\t\treturn convert";
  protected final String TEXT_367 = "((";
  protected final String TEXT_368 = ")instanceValue);";
  protected final String TEXT_369 = NL + "\t\tif (instanceValue == null) return null;" + NL + "\t\t";
  protected final String TEXT_370 = " list = (";
  protected final String TEXT_371 = ")instanceValue;" + NL + "\t\tif (list.isEmpty()) return \"\";" + NL + "\t\t";
  protected final String TEXT_372 = " result = new ";
  protected final String TEXT_373 = "();";
  protected final String TEXT_374 = NL + "\t\tfor (";
  protected final String TEXT_375 = " i = list.iterator(); i.hasNext(); )";
  protected final String TEXT_376 = NL + "\t\tfor (";
  protected final String TEXT_377 = " item : list)";
  protected final String TEXT_378 = NL + "\t\t{";
  protected final String TEXT_379 = NL + "\t\t\tresult.append(convert";
  protected final String TEXT_380 = "ToString(";
  protected final String TEXT_381 = ", ";
  protected final String TEXT_382 = "));";
  protected final String TEXT_383 = NL + "\t\t\tresult.append(";
  protected final String TEXT_384 = ".convertToString(";
  protected final String TEXT_385 = ", ";
  protected final String TEXT_386 = "));";
  protected final String TEXT_387 = NL + "\t\t\tresult.append(' ');" + NL + "\t\t}" + NL + "\t\treturn result.substring(0, result.length() - 1);";
  protected final String TEXT_388 = NL + "\t\treturn instanceValue == null ? null : convert";
  protected final String TEXT_389 = "(((";
  protected final String TEXT_390 = ")instanceValue)";
  protected final String TEXT_391 = ".";
  protected final String TEXT_392 = "()";
  protected final String TEXT_393 = ");";
  protected final String TEXT_394 = NL + "\t\treturn convert";
  protected final String TEXT_395 = "(instanceValue);";
  protected final String TEXT_396 = NL + "\t\tif (instanceValue == null) return null;";
  protected final String TEXT_397 = NL + "\t\tif (";
  protected final String TEXT_398 = ".isInstance(instanceValue))" + NL + "\t\t{" + NL + "\t\t\ttry" + NL + "\t\t\t{";
  protected final String TEXT_399 = NL + "\t\t\t\tString value = convert";
  protected final String TEXT_400 = "ToString(";
  protected final String TEXT_401 = ", instanceValue);";
  protected final String TEXT_402 = NL + "\t\t\t\tString value = ";
  protected final String TEXT_403 = ".convertToString(";
  protected final String TEXT_404 = ", instanceValue);";
  protected final String TEXT_405 = NL + "\t\t\t\tif (value != null) return value;" + NL + "\t\t\t}" + NL + "\t\t\tcatch (Exception e)" + NL + "\t\t\t{" + NL + "\t\t\t\t// Keep trying other member types until all have failed." + NL + "\t\t\t}" + NL + "\t\t}";
  protected final String TEXT_406 = NL + "\t\tthrow new IllegalArgumentException(\"Invalid value: '\"+instanceValue+\"' for datatype :\"+eDataType.getName());";
  protected final String TEXT_407 = NL + "\t\treturn super.convertToString(instanceValue);";
  protected final String TEXT_408 = NL + "\t\t// TODO: implement this method" + NL + "\t\t// Ensure that you remove @generated or mark it @generated NOT" + NL + "\t\tthrow new ";
  protected final String TEXT_409 = "();";
  protected final String TEXT_410 = NL + "\t\treturn super.convertToString(eDataType, instanceValue);";
  protected final String TEXT_411 = NL + "\t}" + NL;
  protected final String TEXT_412 = NL + "\t/**" + NL + "\t * Returns a new object of class '<em>";
  protected final String TEXT_413 = "</em>'." + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @return a new object of class '<em>";
  protected final String TEXT_414 = "</em>'." + NL + "\t * @generated" + NL + "\t */" + NL + "\t";
  protected final String TEXT_415 = " create";
  protected final String TEXT_416 = "();" + NL;
  protected final String TEXT_417 = NL + "\t/**" + NL + "\t * Returns an instance of data type '<em>";
  protected final String TEXT_418 = "</em>' corresponding the given literal." + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @param literal a literal of the data type." + NL + "\t * @return a new instance value of the data type." + NL + "\t * @generated" + NL + "\t */" + NL + "\t";
  protected final String TEXT_419 = " create";
  protected final String TEXT_420 = "(String literal);" + NL + "" + NL + "\t/**" + NL + "\t * Returns a literal representation of an instance of data type '<em>";
  protected final String TEXT_421 = "</em>'." + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @param instanceValue an instance value of the data type." + NL + "\t * @return a literal representation of the instance value." + NL + "\t * @generated" + NL + "\t */" + NL + "\tString convert";
  protected final String TEXT_422 = "(";
  protected final String TEXT_423 = " instanceValue);" + NL;
  protected final String TEXT_424 = NL + "\t/**" + NL + "\t * Returns the package supported by this factory." + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @return the package supported by this factory." + NL + "\t * @generated" + NL + "\t */" + NL + "\t";
  protected final String TEXT_425 = " get";
  protected final String TEXT_426 = "();" + NL;
  protected final String TEXT_427 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */" + NL + "\tpublic ";
  protected final String TEXT_428 = " get";
  protected final String TEXT_429 = "()" + NL + "\t{" + NL + "\t\treturn (";
  protected final String TEXT_430 = ")getEPackage();" + NL + "\t}" + NL + "" + NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @deprecated" + NL + "\t * @generated" + NL + "\t */";
  protected final String TEXT_431 = NL + "\t@Deprecated";
  protected final String TEXT_432 = NL + "\tpublic static ";
  protected final String TEXT_433 = " getPackage()" + NL + "\t{" + NL + "\t\treturn ";
  protected final String TEXT_434 = ".eINSTANCE;" + NL + "\t}" + NL;
  protected final String TEXT_435 = NL + "} //";
  protected final String TEXT_436 = NL;

  public String generate(Object argument)
  {
    final StringBuffer stringBuffer = new StringBuffer();
    
/**
 * <copyright>
 *
 * Copyright (c) 2002-2006 IBM Corporation and others.
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

    GenPackage genPackage = (GenPackage)((Object[])argument)[0]; GenModel genModel=genPackage.getGenModel(); /* Trick to import java.util.* without warnings */Iterator.class.getName();
    boolean isInterface = Boolean.TRUE.equals(((Object[])argument)[1]); boolean isImplementation = Boolean.TRUE.equals(((Object[])argument)[2]);
    String publicStaticFinalFlag = isImplementation ? "public static final " : "";
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
    if (isInterface || genModel.isSuppressInterfaces()) {
    stringBuffer.append(TEXT_7);
    stringBuffer.append(genPackage.getReflectionPackageName());
    stringBuffer.append(TEXT_8);
    } else {
    stringBuffer.append(TEXT_9);
    stringBuffer.append(genPackage.getClassPackageName());
    stringBuffer.append(TEXT_10);
    }
    stringBuffer.append(TEXT_11);
    if (isImplementation) {
    genModel.addPseudoImport("org.eclipse.emf.ecore.impl.MinimalEObjectImpl.Container");
    genModel.addPseudoImport("org.eclipse.emf.ecore.impl.MinimalEObjectImpl.Container.Dynamic");
    genModel.addImport("org.eclipse.emf.ecore.EClass");
    genModel.addImport("org.eclipse.emf.ecore.EObject");
    if (!genPackage.hasJavaLangConflict() && !genPackage.hasInterfaceImplConflict() && !genPackage.getClassPackageName().equals(genPackage.getInterfacePackageName())) genModel.addImport(genPackage.getInterfacePackageName() + ".*");
    }
    genModel.markImportLocation(stringBuffer);
    stringBuffer.append(TEXT_12);
    if (isInterface) {
    stringBuffer.append(TEXT_13);
    if (!genModel.isSuppressEMFMetaData()) {
    stringBuffer.append(TEXT_14);
    stringBuffer.append(genPackage.getQualifiedPackageInterfaceName());
    }
    stringBuffer.append(TEXT_15);
    } else {
    stringBuffer.append(TEXT_16);
    }
    if (isImplementation) {
    stringBuffer.append(TEXT_17);
    stringBuffer.append(genPackage.getFactoryClassName());
    stringBuffer.append(TEXT_18);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.impl.EFactoryImpl"));
    if (!genModel.isSuppressInterfaces()) {
    stringBuffer.append(TEXT_19);
    stringBuffer.append(genPackage.getImportedFactoryInterfaceName());
    }
    } else {
    stringBuffer.append(TEXT_20);
    stringBuffer.append(genPackage.getFactoryInterfaceName());
    if (!genModel.isSuppressEMFMetaData()) {
    stringBuffer.append(TEXT_21);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.EFactory"));
    }
    }
    stringBuffer.append(TEXT_22);
    if (genModel.hasCopyrightField()) {
    stringBuffer.append(TEXT_23);
    stringBuffer.append(publicStaticFinalFlag);
    stringBuffer.append(genModel.getImportedName("java.lang.String"));
    stringBuffer.append(TEXT_24);
    stringBuffer.append(genModel.getCopyrightFieldLiteral());
    stringBuffer.append(TEXT_25);
    stringBuffer.append(genModel.getNonNLS());
    stringBuffer.append(TEXT_26);
    }
    if (isImplementation && (genModel.isSuppressEMFMetaData() || genModel.isSuppressInterfaces())) {
    stringBuffer.append(TEXT_27);
    stringBuffer.append(publicStaticFinalFlag);
    stringBuffer.append(genPackage.getFactoryClassName());
    stringBuffer.append(TEXT_28);
    }
    if (isInterface && genModel.isSuppressEMFMetaData()) {
    stringBuffer.append(TEXT_29);
    stringBuffer.append(publicStaticFinalFlag);
    stringBuffer.append(genPackage.getFactoryInterfaceName());
    stringBuffer.append(TEXT_30);
    stringBuffer.append(genPackage.getQualifiedFactoryClassName());
    stringBuffer.append(TEXT_31);
    } else if (isInterface && !genModel.isSuppressInterfaces()) {
    stringBuffer.append(TEXT_32);
    stringBuffer.append(publicStaticFinalFlag);
    stringBuffer.append(genPackage.getFactoryInterfaceName());
    stringBuffer.append(TEXT_33);
    stringBuffer.append(genPackage.getQualifiedFactoryClassName());
    stringBuffer.append(TEXT_34);
    }
    if (isImplementation) {
    stringBuffer.append(TEXT_35);
    String factoryType = genModel.isSuppressEMFMetaData() ? genPackage.getFactoryClassName() : genPackage.getImportedFactoryInterfaceName();
    stringBuffer.append(TEXT_36);
    stringBuffer.append(factoryType);
    stringBuffer.append(TEXT_37);
    stringBuffer.append(factoryType);
    stringBuffer.append(TEXT_38);
    stringBuffer.append(genPackage.getFactoryName());
    stringBuffer.append(TEXT_39);
    stringBuffer.append(factoryType);
    stringBuffer.append(TEXT_40);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.EPackage"));
    stringBuffer.append(TEXT_41);
    stringBuffer.append(genPackage.getNSURI());
    stringBuffer.append(TEXT_42);
    stringBuffer.append(genModel.getNonNLS());
    stringBuffer.append(TEXT_43);
    stringBuffer.append(genPackage.getFactoryName());
    stringBuffer.append(TEXT_44);
    stringBuffer.append(genPackage.getFactoryName());
    stringBuffer.append(TEXT_45);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.plugin.EcorePlugin"));
    stringBuffer.append(TEXT_46);
    stringBuffer.append(genPackage.getFactoryClassName());
    stringBuffer.append(TEXT_47);
    stringBuffer.append(genPackage.getFactoryClassName());
    stringBuffer.append(TEXT_48);
    if (genModel.useClassOverrideAnnotation()) {
    stringBuffer.append(TEXT_49);
    }
    stringBuffer.append(TEXT_50);
    for (GenClass genClass : genPackage.getGenClasses()) {
    if (!genClass.isAbstract()) {
    stringBuffer.append(TEXT_51);
    stringBuffer.append(genPackage.getImportedPackageInterfaceName());
    stringBuffer.append(TEXT_52);
    stringBuffer.append(genClass.getClassifierID());
    stringBuffer.append(TEXT_53);
    stringBuffer.append(!genClass.isEObjectExtension() ? "(EObject)" : "" );
    stringBuffer.append(TEXT_54);
    stringBuffer.append(genClass.getName());
    stringBuffer.append(TEXT_55);
    }
    }
    stringBuffer.append(TEXT_56);
    stringBuffer.append(genModel.getNonNLS());
    stringBuffer.append(genModel.getNonNLS(2));
    stringBuffer.append(TEXT_57);
    if (!genPackage.getAllGenDataTypes().isEmpty()) {
    stringBuffer.append(TEXT_58);
    if (genModel.useClassOverrideAnnotation()) {
    stringBuffer.append(TEXT_59);
    }
    stringBuffer.append(TEXT_60);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.EDataType"));
    stringBuffer.append(TEXT_61);
    for (GenDataType genDataType : genPackage.getAllGenDataTypes()) {
    if (genDataType.isSerializable()) {
    stringBuffer.append(TEXT_62);
    stringBuffer.append(genPackage.getImportedPackageInterfaceName());
    stringBuffer.append(TEXT_63);
    stringBuffer.append(genDataType.getClassifierID());
    stringBuffer.append(TEXT_64);
    stringBuffer.append(genDataType.getName());
    stringBuffer.append(TEXT_65);
    }
    }
    stringBuffer.append(TEXT_66);
    stringBuffer.append(genModel.getNonNLS());
    stringBuffer.append(genModel.getNonNLS(2));
    stringBuffer.append(TEXT_67);
    if (genModel.useClassOverrideAnnotation()) {
    stringBuffer.append(TEXT_68);
    }
    stringBuffer.append(TEXT_69);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.EDataType"));
    stringBuffer.append(TEXT_70);
    for (GenDataType genDataType : genPackage.getAllGenDataTypes()) {
    if (genDataType.isSerializable()) {
    stringBuffer.append(TEXT_71);
    stringBuffer.append(genPackage.getImportedPackageInterfaceName());
    stringBuffer.append(TEXT_72);
    stringBuffer.append(genDataType.getClassifierID());
    stringBuffer.append(TEXT_73);
    stringBuffer.append(genDataType.getName());
    stringBuffer.append(TEXT_74);
    }
    }
    stringBuffer.append(TEXT_75);
    stringBuffer.append(genModel.getNonNLS());
    stringBuffer.append(genModel.getNonNLS(2));
    stringBuffer.append(TEXT_76);
    }
    for (GenClass genClass : genPackage.getGenClasses()) {
    if (!genClass.isAbstract()) {
    stringBuffer.append(TEXT_77);
    stringBuffer.append(genClass.getTypeParameters());
    stringBuffer.append(genClass.getImportedInterfaceName());
    stringBuffer.append(genClass.getInterfaceTypeArguments());
    stringBuffer.append(TEXT_78);
    stringBuffer.append(genClass.getName());
    stringBuffer.append(TEXT_79);
    if (genClass.isDynamic()) {
    stringBuffer.append(TEXT_80);
    stringBuffer.append(genClass.getImportedInterfaceName());
    stringBuffer.append(genClass.getInterfaceTypeArguments());
    stringBuffer.append(TEXT_81);
    stringBuffer.append(genClass.getSafeUncapName());
    stringBuffer.append(TEXT_82);
    stringBuffer.append(genClass.getCastFromEObject());
    stringBuffer.append(TEXT_83);
    stringBuffer.append(genClass.getQualifiedClassifierAccessor());
    stringBuffer.append(TEXT_84);
    } else {
    stringBuffer.append(TEXT_85);
    stringBuffer.append(genClass.getImportedClassName());
    stringBuffer.append(genClass.getClassTypeArguments());
    stringBuffer.append(TEXT_86);
    stringBuffer.append(genClass.getSafeUncapName());
    stringBuffer.append(TEXT_87);
    stringBuffer.append(genClass.getImportedClassName());
    stringBuffer.append(genClass.getClassTypeArguments());
    stringBuffer.append(TEXT_88);
    if (genModel.isSuppressInterfaces() && !genPackage.getReflectionPackageName().equals(genPackage.getInterfacePackageName())) {
    stringBuffer.append(TEXT_89);
    }
    stringBuffer.append(TEXT_90);
    }
    stringBuffer.append(TEXT_91);
    stringBuffer.append(genClass.getSafeUncapName());
    stringBuffer.append(TEXT_92);
    }
    }
    for (GenDataType genDataType : genPackage.getAllGenDataTypes()) {
    if (genDataType.isSerializable()) {
    if (genPackage.isDataTypeConverters()) { String eDataType = genDataType.getQualifiedClassifierAccessor();
    stringBuffer.append(TEXT_93);
    if (genModel.useGenerics() && genDataType.isUncheckedCast()) {
    stringBuffer.append(TEXT_94);
    }
    stringBuffer.append(TEXT_95);
    stringBuffer.append(genDataType.getImportedParameterizedInstanceClassName());
    stringBuffer.append(TEXT_96);
    stringBuffer.append(genDataType.getName());
    stringBuffer.append(TEXT_97);
    if (genDataType instanceof GenEnum) {
    stringBuffer.append(TEXT_98);
    stringBuffer.append(genDataType.getImportedInstanceClassName());
    stringBuffer.append(TEXT_99);
    stringBuffer.append(genDataType.getImportedInstanceClassName());
    stringBuffer.append(TEXT_100);
    stringBuffer.append(eDataType);
    stringBuffer.append(TEXT_101);
    stringBuffer.append(genModel.getNonNLS());
    stringBuffer.append(genModel.getNonNLS(2));
    stringBuffer.append(genModel.getNonNLS(3));
    stringBuffer.append(TEXT_102);
    } else if (genDataType.getBaseType() != null) { GenDataType genBaseType = genDataType.getBaseType(); boolean isPrimitiveConversion = !genDataType.isPrimitiveType() && genBaseType.isPrimitiveType();
    if (genBaseType.getGenPackage() == genPackage) {
    if (isPrimitiveConversion && genModel.getComplianceLevel().getValue() < GenJDKLevel.JDK50) {
    stringBuffer.append(TEXT_103);
    stringBuffer.append(genDataType.getObjectInstanceClassName());
    stringBuffer.append(TEXT_104);
    stringBuffer.append(genBaseType.getName());
    stringBuffer.append(TEXT_105);
    } else {
    stringBuffer.append(TEXT_106);
    stringBuffer.append(genBaseType.getName());
    stringBuffer.append(TEXT_107);
    }
    } else if (genBaseType.getGenPackage().isDataTypeConverters()) {
    if (isPrimitiveConversion && genModel.getComplianceLevel().getValue() < GenJDKLevel.JDK50) {
    stringBuffer.append(TEXT_108);
    stringBuffer.append(genDataType.getObjectInstanceClassName());
    stringBuffer.append(TEXT_109);
    stringBuffer.append(genBaseType.getGenPackage().getQualifiedEFactoryInternalInstanceAccessor());
    stringBuffer.append(TEXT_110);
    stringBuffer.append(genBaseType.getName());
    stringBuffer.append(TEXT_111);
    } else {
    stringBuffer.append(TEXT_112);
    stringBuffer.append(genBaseType.getGenPackage().getQualifiedEFactoryInternalInstanceAccessor());
    stringBuffer.append(TEXT_113);
    stringBuffer.append(genBaseType.getName());
    stringBuffer.append(TEXT_114);
    }
    } else {
    stringBuffer.append(TEXT_115);
    if (!genDataType.isObjectType()) {
    stringBuffer.append(TEXT_116);
    stringBuffer.append(genDataType.getObjectInstanceClassName());
    stringBuffer.append(TEXT_117);
    }
    stringBuffer.append(genBaseType.getGenPackage().getQualifiedEFactoryInternalInstanceAccessor());
    stringBuffer.append(TEXT_118);
    stringBuffer.append(genBaseType.getQualifiedClassifierAccessor());
    stringBuffer.append(TEXT_119);
    }
    } else if (genDataType.getItemType() != null) { GenDataType genItemType = genDataType.getItemType(); 
    stringBuffer.append(TEXT_120);
    stringBuffer.append(genDataType.getImportedParameterizedInstanceClassName());
    stringBuffer.append(TEXT_121);
    stringBuffer.append(genModel.getImportedName("java.util.ArrayList"));
    if (genModel.useGenerics()) {
    stringBuffer.append(TEXT_122);
    stringBuffer.append(genItemType.getObjectType().getImportedParameterizedInstanceClassName());
    stringBuffer.append(TEXT_123);
    }
    stringBuffer.append(TEXT_124);
    stringBuffer.append(genModel.getImportedName("java.util.StringTokenizer"));
    stringBuffer.append(TEXT_125);
    stringBuffer.append(genModel.getImportedName("java.util.StringTokenizer"));
    stringBuffer.append(TEXT_126);
    if (genItemType.getGenPackage() == genPackage) {
    if (genPackage.isDataTypeConverters()) { genItemType = genItemType.getObjectType();
    stringBuffer.append(TEXT_127);
    stringBuffer.append(genItemType.getName());
    stringBuffer.append(TEXT_128);
    } else {
    stringBuffer.append(TEXT_129);
    stringBuffer.append(genItemType.getName());
    stringBuffer.append(TEXT_130);
    stringBuffer.append(genItemType.getQualifiedClassifierAccessor());
    stringBuffer.append(TEXT_131);
    }
    } else {
    if (genItemType.getGenPackage().isDataTypeConverters()) { genItemType = genItemType.getObjectType();
    stringBuffer.append(TEXT_132);
    stringBuffer.append(genItemType.getGenPackage().getQualifiedEFactoryInternalInstanceAccessor());
    stringBuffer.append(TEXT_133);
    stringBuffer.append(genItemType.getName());
    stringBuffer.append(TEXT_134);
    } else {
    stringBuffer.append(TEXT_135);
    stringBuffer.append(genItemType.getGenPackage().getQualifiedEFactoryInternalInstanceAccessor());
    stringBuffer.append(TEXT_136);
    stringBuffer.append(genItemType.getQualifiedClassifierAccessor());
    stringBuffer.append(TEXT_137);
    }
    }
    stringBuffer.append(TEXT_138);
    } else if (!genDataType.getMemberTypes().isEmpty()) {
    stringBuffer.append(TEXT_139);
    stringBuffer.append(genDataType.getStaticValue(null));
    stringBuffer.append(TEXT_140);
    stringBuffer.append(genDataType.getImportedInstanceClassName());
    stringBuffer.append(TEXT_141);
    stringBuffer.append(genDataType.getStaticValue(null));
    stringBuffer.append(TEXT_142);
    for (GenDataType genMemberType : genDataType.getMemberTypes()) {
    stringBuffer.append(TEXT_143);
    if (genMemberType.getGenPackage() == genPackage) {
    if (genPackage.isDataTypeConverters()) { if (!genDataType.isPrimitiveType()) genMemberType = genMemberType.getObjectType();
    stringBuffer.append(TEXT_144);
    stringBuffer.append(genMemberType.getName());
    stringBuffer.append(TEXT_145);
    } else {
    stringBuffer.append(TEXT_146);
    stringBuffer.append(genDataType.getObjectInstanceClassName());
    stringBuffer.append(TEXT_147);
    stringBuffer.append(genMemberType.getName());
    stringBuffer.append(TEXT_148);
    stringBuffer.append(genMemberType.getQualifiedClassifierAccessor());
    stringBuffer.append(TEXT_149);
    }
    } else {
    if (genPackage.isDataTypeConverters()) { if (!genDataType.isPrimitiveType()) genMemberType = genMemberType.getObjectType();
    stringBuffer.append(TEXT_150);
    stringBuffer.append(genMemberType.getGenPackage().getQualifiedEFactoryInternalInstanceAccessor());
    stringBuffer.append(TEXT_151);
    stringBuffer.append(genMemberType.getName());
    stringBuffer.append(TEXT_152);
    } else {
    stringBuffer.append(TEXT_153);
    stringBuffer.append(genDataType.getObjectInstanceClassName());
    stringBuffer.append(TEXT_154);
    stringBuffer.append(genMemberType.getGenPackage().getQualifiedEFactoryInternalInstanceAccessor());
    stringBuffer.append(TEXT_155);
    stringBuffer.append(genMemberType.getQualifiedClassifierAccessor());
    stringBuffer.append(TEXT_156);
    }
    }
    stringBuffer.append(TEXT_157);
    if (!genDataType.isPrimitiveType()) {
    stringBuffer.append(TEXT_158);
    }
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.Diagnostician"));
    stringBuffer.append(TEXT_159);
    stringBuffer.append(eDataType);
    stringBuffer.append(TEXT_160);
    if (genDataType.isPrimitiveType() && genModel.getComplianceLevel().getValue() < GenJDKLevel.JDK50) {
    stringBuffer.append(TEXT_161);
    stringBuffer.append(genDataType.getObjectInstanceClassName());
    stringBuffer.append(TEXT_162);
    } else {
    stringBuffer.append(TEXT_163);
    }
    stringBuffer.append(TEXT_164);
    }
    stringBuffer.append(TEXT_165);
    if (!genDataType.isPrimitiveType()) {
    stringBuffer.append(TEXT_166);
    }
    stringBuffer.append(TEXT_167);
    } else if (genModel.useGenerics() && (genDataType.isArrayType() || !genDataType.getEcoreDataType().getETypeParameters().isEmpty() || genDataType.getEcoreDataType().getInstanceTypeName().contains("<"))) {
    stringBuffer.append(TEXT_168);
    stringBuffer.append(genDataType.getImportedParameterizedObjectInstanceClassName());
    stringBuffer.append(TEXT_169);
    } else if (genDataType.isArrayType()) {
    stringBuffer.append(TEXT_170);
    stringBuffer.append(genModel.getImportedName("java.lang.UnsupportedOperationException"));
    stringBuffer.append(TEXT_171);
    } else if (genDataType.isPrimitiveType() && genModel.getComplianceLevel().getValue() < GenJDKLevel.JDK50) {
    stringBuffer.append(TEXT_172);
    stringBuffer.append(genDataType.getObjectInstanceClassName());
    stringBuffer.append(TEXT_173);
    stringBuffer.append(eDataType);
    stringBuffer.append(TEXT_174);
    stringBuffer.append(genDataType.getPrimitiveValueFunction());
    stringBuffer.append(TEXT_175);
    } else {
    stringBuffer.append(TEXT_176);
    if (!genDataType.isObjectType()) {
    stringBuffer.append(TEXT_177);
    stringBuffer.append(genDataType.getObjectInstanceClassName());
    stringBuffer.append(TEXT_178);
    }
    stringBuffer.append(TEXT_179);
    stringBuffer.append(eDataType);
    stringBuffer.append(TEXT_180);
    }
    stringBuffer.append(TEXT_181);
    }
    stringBuffer.append(TEXT_182);
    if (genModel.useGenerics() && genDataType.isUncheckedCast()) {
    stringBuffer.append(TEXT_183);
    }
    stringBuffer.append(TEXT_184);
    stringBuffer.append(genDataType.getImportedParameterizedObjectInstanceClassName());
    stringBuffer.append(TEXT_185);
    stringBuffer.append(genDataType.getName());
    stringBuffer.append(TEXT_186);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.EDataType"));
    stringBuffer.append(TEXT_187);
    if (genDataType instanceof GenEnum) {
    if (genPackage.isDataTypeConverters()) {
    stringBuffer.append(TEXT_188);
    stringBuffer.append(genDataType.getName());
    stringBuffer.append(TEXT_189);
    } else {
    stringBuffer.append(TEXT_190);
    stringBuffer.append(((GenEnum)genDataType).getImportedInstanceClassName());
    stringBuffer.append(TEXT_191);
    stringBuffer.append(((GenEnum)genDataType).getImportedInstanceClassName());
    stringBuffer.append(TEXT_192);
    stringBuffer.append(genModel.getNonNLS());
    stringBuffer.append(genModel.getNonNLS(2));
    stringBuffer.append(genModel.getNonNLS(3));
    stringBuffer.append(TEXT_193);
    }
    } else if (genDataType.getBaseType() != null) { GenDataType genBaseType = genDataType.getBaseType(); 
    if (genBaseType.getGenPackage() == genPackage) {
    stringBuffer.append(TEXT_194);
    if (!genDataType.getObjectInstanceClassName().equals(genBaseType.getObjectInstanceClassName())) {
    stringBuffer.append(TEXT_195);
    stringBuffer.append(genDataType.getObjectInstanceClassName());
    stringBuffer.append(TEXT_196);
    }
    stringBuffer.append(TEXT_197);
    stringBuffer.append(genBaseType.getName());
    stringBuffer.append(TEXT_198);
    stringBuffer.append(genBaseType.getQualifiedClassifierAccessor());
    stringBuffer.append(TEXT_199);
    } else {
    stringBuffer.append(TEXT_200);
    if (!genDataType.isObjectType()) {
    stringBuffer.append(TEXT_201);
    stringBuffer.append(genDataType.getObjectInstanceClassName());
    stringBuffer.append(TEXT_202);
    }
    stringBuffer.append(genBaseType.getGenPackage().getQualifiedEFactoryInternalInstanceAccessor());
    stringBuffer.append(TEXT_203);
    stringBuffer.append(genBaseType.getQualifiedClassifierAccessor());
    stringBuffer.append(TEXT_204);
    }
    } else if (genDataType.getItemType() != null) { GenDataType genItemType = genDataType.getItemType(); 
    if (genPackage.isDataTypeConverters()) {
    stringBuffer.append(TEXT_205);
    stringBuffer.append(genDataType.getName());
    stringBuffer.append(TEXT_206);
    } else {
    stringBuffer.append(TEXT_207);
    stringBuffer.append(genDataType.getImportedParameterizedObjectInstanceClassName());
    stringBuffer.append(TEXT_208);
    stringBuffer.append(genModel.getImportedName("java.util.ArrayList"));
    if (genModel.useGenerics()) {
    stringBuffer.append(TEXT_209);
    stringBuffer.append(genItemType.getObjectType().getImportedParameterizedInstanceClassName());
    stringBuffer.append(TEXT_210);
    }
    stringBuffer.append(TEXT_211);
    stringBuffer.append(genModel.getImportedName("java.util.StringTokenizer"));
    stringBuffer.append(TEXT_212);
    stringBuffer.append(genModel.getImportedName("java.util.StringTokenizer"));
    stringBuffer.append(TEXT_213);
    if (genItemType.getGenPackage() == genPackage) {
    stringBuffer.append(TEXT_214);
    stringBuffer.append(genItemType.getName());
    stringBuffer.append(TEXT_215);
    stringBuffer.append(genItemType.getQualifiedClassifierAccessor());
    stringBuffer.append(TEXT_216);
    } else {
    stringBuffer.append(TEXT_217);
    if (!genItemType.isObjectType()) {
    stringBuffer.append(TEXT_218);
    stringBuffer.append(genItemType.getObjectInstanceClassName());
    stringBuffer.append(TEXT_219);
    }
    stringBuffer.append(genItemType.getGenPackage().getQualifiedEFactoryInternalInstanceAccessor());
    stringBuffer.append(TEXT_220);
    stringBuffer.append(genItemType.getQualifiedClassifierAccessor());
    stringBuffer.append(TEXT_221);
    }
    stringBuffer.append(TEXT_222);
    }
    } else if (!genDataType.getMemberTypes().isEmpty()) {
    if (genPackage.isDataTypeConverters()) {
    if (genDataType.isPrimitiveType() && genModel.getComplianceLevel().getValue() < GenJDKLevel.JDK50) {
    stringBuffer.append(TEXT_223);
    stringBuffer.append(genDataType.getObjectInstanceClassName());
    stringBuffer.append(TEXT_224);
    stringBuffer.append(genDataType.getName());
    stringBuffer.append(TEXT_225);
    } else {
    stringBuffer.append(TEXT_226);
    stringBuffer.append(genDataType.getName());
    stringBuffer.append(TEXT_227);
    }
    } else {
    stringBuffer.append(TEXT_228);
    stringBuffer.append(genDataType.getObjectInstanceClassName());
    stringBuffer.append(TEXT_229);
    for (GenDataType genMemberType : genDataType.getMemberTypes()) {
    stringBuffer.append(TEXT_230);
    if (genMemberType.getGenPackage() == genPackage) {
    stringBuffer.append(TEXT_231);
    if (!genDataType.isObjectType() && !genDataType.getObjectInstanceClassName().equals(genMemberType.getObjectInstanceClassName())) {
    stringBuffer.append(TEXT_232);
    stringBuffer.append(genDataType.getObjectInstanceClassName());
    stringBuffer.append(TEXT_233);
    }
    stringBuffer.append(TEXT_234);
    stringBuffer.append(genMemberType.getName());
    stringBuffer.append(TEXT_235);
    stringBuffer.append(genMemberType.getQualifiedClassifierAccessor());
    stringBuffer.append(TEXT_236);
    } else {
    stringBuffer.append(TEXT_237);
    if (!genDataType.isObjectType()) {
    stringBuffer.append(TEXT_238);
    stringBuffer.append(genDataType.getObjectInstanceClassName());
    stringBuffer.append(TEXT_239);
    }
    stringBuffer.append(genMemberType.getGenPackage().getQualifiedEFactoryInternalInstanceAccessor());
    stringBuffer.append(TEXT_240);
    stringBuffer.append(genMemberType.getQualifiedClassifierAccessor());
    stringBuffer.append(TEXT_241);
    }
    stringBuffer.append(TEXT_242);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.Diagnostician"));
    stringBuffer.append(TEXT_243);
    }
    stringBuffer.append(TEXT_244);
    }
    } else if (genModel.useGenerics() && (genDataType.isArrayType() || !genDataType.getEcoreDataType().getETypeParameters().isEmpty() || genDataType.getEcoreDataType().getInstanceTypeName().contains("<"))) {
    stringBuffer.append(TEXT_245);
    if (!genDataType.isObjectType()) {
    stringBuffer.append(TEXT_246);
    stringBuffer.append(genDataType.getImportedParameterizedObjectInstanceClassName());
    stringBuffer.append(TEXT_247);
    }
    stringBuffer.append(TEXT_248);
    } else if (genDataType.isArrayType()) {
    stringBuffer.append(TEXT_249);
    stringBuffer.append(genModel.getImportedName("java.lang.UnsupportedOperationException"));
    stringBuffer.append(TEXT_250);
    } else {
    stringBuffer.append(TEXT_251);
    if (!genDataType.isObjectType()) {
    stringBuffer.append(TEXT_252);
    stringBuffer.append(genDataType.getObjectInstanceClassName());
    stringBuffer.append(TEXT_253);
    }
    stringBuffer.append(TEXT_254);
    }
    stringBuffer.append(TEXT_255);
    if (genPackage.isDataTypeConverters()) { String eDataType = genDataType.getQualifiedClassifierAccessor();
    stringBuffer.append(TEXT_256);
    stringBuffer.append(genDataType.getName());
    stringBuffer.append(TEXT_257);
    stringBuffer.append(genDataType.getImportedBoundedWildcardInstanceClassName());
    stringBuffer.append(TEXT_258);
    if (genDataType instanceof GenEnum) {
    stringBuffer.append(TEXT_259);
    } else if (genDataType.getBaseType() != null) { GenDataType genBaseType = genDataType.getBaseType(); boolean isPrimitiveConversion = !genDataType.isPrimitiveType() && genBaseType.isPrimitiveType();
    if (genBaseType.getGenPackage() == genPackage) {
    if (isPrimitiveConversion) {
    stringBuffer.append(TEXT_260);
    stringBuffer.append(genBaseType.getName());
    stringBuffer.append(TEXT_261);
    if (genModel.getComplianceLevel().getValue() < GenJDKLevel.JDK50) {
    stringBuffer.append(TEXT_262);
    stringBuffer.append(genBaseType.getPrimitiveValueFunction());
    stringBuffer.append(TEXT_263);
    }
    stringBuffer.append(TEXT_264);
    } else {
    stringBuffer.append(TEXT_265);
    stringBuffer.append(genBaseType.getName());
    stringBuffer.append(TEXT_266);
    }
    } else if (genBaseType.getGenPackage().isDataTypeConverters()) {
    stringBuffer.append(TEXT_267);
    stringBuffer.append(genBaseType.getGenPackage().getQualifiedFactoryInstanceAccessor());
    stringBuffer.append(TEXT_268);
    stringBuffer.append(genBaseType.getName());
    stringBuffer.append(TEXT_269);
    } else {
    stringBuffer.append(TEXT_270);
    stringBuffer.append(genBaseType.getGenPackage().getQualifiedEFactoryInternalInstanceAccessor());
    stringBuffer.append(TEXT_271);
    stringBuffer.append(genBaseType.getQualifiedClassifierAccessor());
    stringBuffer.append(TEXT_272);
    }
    } else if (genDataType.getItemType() != null) { GenDataType genItemType = genDataType.getItemType(); 
    stringBuffer.append(TEXT_273);
    stringBuffer.append(genModel.getImportedName("java.lang.StringBuffer"));
    stringBuffer.append(TEXT_274);
    stringBuffer.append(genModel.getImportedName("java.lang.StringBuffer"));
    stringBuffer.append(TEXT_275);
    String item; if (!genModel.useGenerics()) { item = "i.next()"; 
    stringBuffer.append(TEXT_276);
    stringBuffer.append(genModel.getImportedName("java.util.Iterator"));
    stringBuffer.append(TEXT_277);
    } else { item = "item";
    stringBuffer.append(TEXT_278);
    stringBuffer.append(genModel.getImportedName("java.lang.Object"));
    stringBuffer.append(TEXT_279);
    }
    stringBuffer.append(TEXT_280);
    if (genItemType.getGenPackage() == genPackage) {
    if (genPackage.isDataTypeConverters()) { genItemType = genItemType.getObjectType();
    stringBuffer.append(TEXT_281);
    stringBuffer.append(genItemType.getName());
    stringBuffer.append(TEXT_282);
    stringBuffer.append(genItemType.getObjectInstanceClassName());
    stringBuffer.append(TEXT_283);
    stringBuffer.append(item);
    stringBuffer.append(TEXT_284);
    } else {
    stringBuffer.append(TEXT_285);
    stringBuffer.append(genItemType.getName());
    stringBuffer.append(TEXT_286);
    stringBuffer.append(genItemType.getQualifiedClassifierAccessor());
    stringBuffer.append(TEXT_287);
    stringBuffer.append(item);
    stringBuffer.append(TEXT_288);
    }
    } else {
    if (genItemType.getGenPackage().isDataTypeConverters()) { genItemType = genItemType.getObjectType();
    stringBuffer.append(TEXT_289);
    stringBuffer.append(genItemType.getGenPackage().getQualifiedEFactoryInternalInstanceAccessor());
    stringBuffer.append(TEXT_290);
    stringBuffer.append(genItemType.getName());
    stringBuffer.append(TEXT_291);
    stringBuffer.append(genItemType.getObjectInstanceClassName());
    stringBuffer.append(TEXT_292);
    stringBuffer.append(item);
    stringBuffer.append(TEXT_293);
    } else {
    stringBuffer.append(TEXT_294);
    stringBuffer.append(genItemType.getGenPackage().getQualifiedEFactoryInternalInstanceAccessor());
    stringBuffer.append(TEXT_295);
    stringBuffer.append(genItemType.getQualifiedClassifierAccessor());
    stringBuffer.append(TEXT_296);
    stringBuffer.append(item);
    stringBuffer.append(TEXT_297);
    }
    }
    stringBuffer.append(TEXT_298);
    } else if (!genDataType.getMemberTypes().isEmpty()) {
    if (!genDataType.isPrimitiveType()) {
    stringBuffer.append(TEXT_299);
    for (GenDataType genMemberType : genDataType.getMemberTypes()) {
    stringBuffer.append(TEXT_300);
    stringBuffer.append(genMemberType.getQualifiedClassifierAccessor());
    stringBuffer.append(TEXT_301);
    if (genMemberType.getGenPackage() == genPackage) {
    if (genPackage.isDataTypeConverters()) {
    if (genMemberType.getQualifiedInstanceClassName().equals(genDataType.getQualifiedInstanceClassName())) {
    stringBuffer.append(TEXT_302);
    stringBuffer.append(genMemberType.getName());
    stringBuffer.append(TEXT_303);
    } else if (genMemberType.isPrimitiveType() && genModel.getComplianceLevel().getValue() < GenJDKLevel.JDK50) {
    stringBuffer.append(TEXT_304);
    stringBuffer.append(genMemberType.getName());
    stringBuffer.append(TEXT_305);
    stringBuffer.append(genMemberType.getObjectType().getImportedInstanceClassName());
    stringBuffer.append(TEXT_306);
    stringBuffer.append(genMemberType.getPrimitiveValueFunction());
    stringBuffer.append(TEXT_307);
    } else {
    stringBuffer.append(TEXT_308);
    stringBuffer.append(genMemberType.getName());
    stringBuffer.append(TEXT_309);
    stringBuffer.append(genMemberType.getObjectType().getImportedBoundedWildcardInstanceClassName());
    stringBuffer.append(TEXT_310);
    }
    } else {
    stringBuffer.append(TEXT_311);
    stringBuffer.append(genMemberType.getName());
    stringBuffer.append(TEXT_312);
    stringBuffer.append(genMemberType.getQualifiedClassifierAccessor());
    stringBuffer.append(TEXT_313);
    }
    } else {
    if (genMemberType.getGenPackage().isDataTypeConverters()) { genMemberType = genMemberType.getObjectType();
    stringBuffer.append(TEXT_314);
    stringBuffer.append(genMemberType.getGenPackage().getQualifiedEFactoryInternalInstanceAccessor());
    stringBuffer.append(TEXT_315);
    stringBuffer.append(genMemberType.getName());
    stringBuffer.append(TEXT_316);
    stringBuffer.append(genMemberType.getObjectInstanceClassName());
    stringBuffer.append(TEXT_317);
    } else {
    stringBuffer.append(TEXT_318);
    stringBuffer.append(genMemberType.getGenPackage().getQualifiedEFactoryInternalInstanceAccessor());
    stringBuffer.append(TEXT_319);
    stringBuffer.append(genMemberType.getQualifiedClassifierAccessor());
    stringBuffer.append(TEXT_320);
    }
    }
    stringBuffer.append(TEXT_321);
    }
    } else {
    for (GenDataType genMemberType : genDataType.getMemberTypes()) {
    stringBuffer.append(TEXT_322);
    if (genMemberType.getGenPackage() == genPackage) {
    if (genPackage.isDataTypeConverters()) {
    stringBuffer.append(TEXT_323);
    stringBuffer.append(genMemberType.getName());
    stringBuffer.append(TEXT_324);
    } else {
    stringBuffer.append(TEXT_325);
    stringBuffer.append(genMemberType.getName());
    stringBuffer.append(TEXT_326);
    stringBuffer.append(genMemberType.getQualifiedClassifierAccessor());
    stringBuffer.append(TEXT_327);
    if (genModel.getComplianceLevel().getValue() < GenJDKLevel.JDK50) {
    stringBuffer.append(TEXT_328);
    stringBuffer.append(genMemberType.getObjectInstanceClassName());
    stringBuffer.append(TEXT_329);
    } else {
    stringBuffer.append(TEXT_330);
    }
    stringBuffer.append(TEXT_331);
    }
    } else {
    if (genMemberType.getGenPackage().isDataTypeConverters()) {
    stringBuffer.append(TEXT_332);
    stringBuffer.append(genMemberType.getGenPackage().getQualifiedEFactoryInternalInstanceAccessor());
    stringBuffer.append(TEXT_333);
    stringBuffer.append(genMemberType.getName());
    stringBuffer.append(TEXT_334);
    } else {
    stringBuffer.append(TEXT_335);
    stringBuffer.append(genMemberType.getGenPackage().getQualifiedEFactoryInternalInstanceAccessor());
    stringBuffer.append(TEXT_336);
    stringBuffer.append(genMemberType.getQualifiedClassifierAccessor());
    stringBuffer.append(TEXT_337);
    if (genModel.getComplianceLevel().getValue() < GenJDKLevel.JDK50) {
    stringBuffer.append(TEXT_338);
    stringBuffer.append(genMemberType.getObjectInstanceClassName());
    stringBuffer.append(TEXT_339);
    } else {
    stringBuffer.append(TEXT_340);
    }
    stringBuffer.append(TEXT_341);
    }
    }
    stringBuffer.append(TEXT_342);
    }
    }
    stringBuffer.append(TEXT_343);
    stringBuffer.append(eDataType);
    stringBuffer.append(TEXT_344);
    } else if (genModel.useGenerics() && (genDataType.isArrayType() || !genDataType.getEcoreDataType().getETypeParameters().isEmpty() || genDataType.getEcoreDataType().getInstanceTypeName().contains("<"))) {
    stringBuffer.append(TEXT_345);
    } else if (genDataType.isArrayType()) {
    stringBuffer.append(TEXT_346);
    stringBuffer.append(genModel.getImportedName("java.lang.UnsupportedOperationException"));
    stringBuffer.append(TEXT_347);
    } else if (genDataType.isPrimitiveType() && genModel.getComplianceLevel().getValue() < GenJDKLevel.JDK50) {
    stringBuffer.append(TEXT_348);
    stringBuffer.append(eDataType);
    stringBuffer.append(TEXT_349);
    stringBuffer.append(genDataType.getObjectInstanceClassName());
    stringBuffer.append(TEXT_350);
    } else {
    stringBuffer.append(TEXT_351);
    stringBuffer.append(eDataType);
    stringBuffer.append(TEXT_352);
    }
    stringBuffer.append(TEXT_353);
    }
    stringBuffer.append(TEXT_354);
    if (genModel.useGenerics() && genDataType.getItemType() != null && genPackage.isDataTypeConverters()) {
    stringBuffer.append(TEXT_355);
    }
    stringBuffer.append(TEXT_356);
    stringBuffer.append(genDataType.getName());
    stringBuffer.append(TEXT_357);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.EDataType"));
    stringBuffer.append(TEXT_358);
    if (genDataType instanceof GenEnum) {
    stringBuffer.append(TEXT_359);
    } else if (genDataType.getBaseType() != null) { GenDataType genBaseType = genDataType.getBaseType(); 
    if (genBaseType.getGenPackage() == genPackage) {
    stringBuffer.append(TEXT_360);
    stringBuffer.append(genBaseType.getName());
    stringBuffer.append(TEXT_361);
    stringBuffer.append(genBaseType.getQualifiedClassifierAccessor());
    stringBuffer.append(TEXT_362);
    } else {
    stringBuffer.append(TEXT_363);
    stringBuffer.append(genBaseType.getGenPackage().getQualifiedEFactoryInternalInstanceAccessor());
    stringBuffer.append(TEXT_364);
    stringBuffer.append(genBaseType.getQualifiedClassifierAccessor());
    stringBuffer.append(TEXT_365);
    }
    } else if (genDataType.getItemType() != null) { GenDataType genItemType = genDataType.getItemType(); 
    if (genPackage.isDataTypeConverters()) {
    stringBuffer.append(TEXT_366);
    stringBuffer.append(genDataType.getName());
    stringBuffer.append(TEXT_367);
    stringBuffer.append(genDataType.getImportedBoundedWildcardInstanceClassName());
    stringBuffer.append(TEXT_368);
    } else { final String singleWildcard = genModel.useGenerics() ? "<?>" : "";
    stringBuffer.append(TEXT_369);
    stringBuffer.append(genModel.getImportedName("java.util.List"));
    stringBuffer.append(singleWildcard);
    stringBuffer.append(TEXT_370);
    stringBuffer.append(genModel.getImportedName("java.util.List"));
    stringBuffer.append(singleWildcard);
    stringBuffer.append(TEXT_371);
    stringBuffer.append(genModel.getImportedName("java.lang.StringBuffer"));
    stringBuffer.append(TEXT_372);
    stringBuffer.append(genModel.getImportedName("java.lang.StringBuffer"));
    stringBuffer.append(TEXT_373);
    String item; if (!genModel.useGenerics()) { item = "i.next()"; 
    stringBuffer.append(TEXT_374);
    stringBuffer.append(genModel.getImportedName("java.util.Iterator"));
    stringBuffer.append(TEXT_375);
    } else { item = "item";
    stringBuffer.append(TEXT_376);
    stringBuffer.append(genModel.getImportedName("java.lang.Object"));
    stringBuffer.append(TEXT_377);
    }
    stringBuffer.append(TEXT_378);
    if (genItemType.getGenPackage() == genPackage) {
    stringBuffer.append(TEXT_379);
    stringBuffer.append(genItemType.getName());
    stringBuffer.append(TEXT_380);
    stringBuffer.append(genItemType.getQualifiedClassifierAccessor());
    stringBuffer.append(TEXT_381);
    stringBuffer.append(item);
    stringBuffer.append(TEXT_382);
    } else {
    stringBuffer.append(TEXT_383);
    stringBuffer.append(genItemType.getGenPackage().getQualifiedEFactoryInternalInstanceAccessor());
    stringBuffer.append(TEXT_384);
    stringBuffer.append(genItemType.getQualifiedClassifierAccessor());
    stringBuffer.append(TEXT_385);
    stringBuffer.append(item);
    stringBuffer.append(TEXT_386);
    }
    stringBuffer.append(TEXT_387);
    }
    } else if (!genDataType.getMemberTypes().isEmpty()) {
    if (genPackage.isDataTypeConverters()) {
    if (genDataType.isPrimitiveType()) {
    stringBuffer.append(TEXT_388);
    stringBuffer.append(genDataType.getName());
    stringBuffer.append(TEXT_389);
    stringBuffer.append(genDataType.getObjectInstanceClassName());
    stringBuffer.append(TEXT_390);
    if (genModel.getComplianceLevel().getValue() < GenJDKLevel.JDK50) {
    stringBuffer.append(TEXT_391);
    stringBuffer.append(genDataType.getPrimitiveValueFunction());
    stringBuffer.append(TEXT_392);
    }
    stringBuffer.append(TEXT_393);
    } else {
    stringBuffer.append(TEXT_394);
    stringBuffer.append(genDataType.getName());
    stringBuffer.append(TEXT_395);
    }
    } else {
    stringBuffer.append(TEXT_396);
    for (GenDataType genMemberType : genDataType.getMemberTypes()) {
    stringBuffer.append(TEXT_397);
    stringBuffer.append(genMemberType.getQualifiedClassifierAccessor());
    stringBuffer.append(TEXT_398);
    if (genMemberType.getGenPackage() == genPackage) {
    stringBuffer.append(TEXT_399);
    stringBuffer.append(genMemberType.getName());
    stringBuffer.append(TEXT_400);
    stringBuffer.append(genMemberType.getQualifiedClassifierAccessor());
    stringBuffer.append(TEXT_401);
    } else {
    stringBuffer.append(TEXT_402);
    stringBuffer.append(genMemberType.getGenPackage().getQualifiedEFactoryInternalInstanceAccessor());
    stringBuffer.append(TEXT_403);
    stringBuffer.append(genMemberType.getQualifiedClassifierAccessor());
    stringBuffer.append(TEXT_404);
    }
    stringBuffer.append(TEXT_405);
    }
    stringBuffer.append(TEXT_406);
    }
    } else if (genModel.useGenerics() && (genDataType.isArrayType() || !genDataType.getEcoreDataType().getETypeParameters().isEmpty() || genDataType.getEcoreDataType().getInstanceTypeName().contains("<"))) {
    stringBuffer.append(TEXT_407);
    } else if (genDataType.isArrayType()) {
    stringBuffer.append(TEXT_408);
    stringBuffer.append(genModel.getImportedName("java.lang.UnsupportedOperationException"));
    stringBuffer.append(TEXT_409);
    } else {
    stringBuffer.append(TEXT_410);
    }
    stringBuffer.append(TEXT_411);
    }
    }
    } else {
    for (GenClass genClass : genPackage.getGenClasses()) {
    if (genClass.hasFactoryInterfaceCreateMethod()) {
    stringBuffer.append(TEXT_412);
    stringBuffer.append(genClass.getFormattedName());
    stringBuffer.append(TEXT_413);
    stringBuffer.append(genClass.getFormattedName());
    stringBuffer.append(TEXT_414);
    stringBuffer.append(genClass.getTypeParameters());
    stringBuffer.append(genClass.getImportedInterfaceName());
    stringBuffer.append(genClass.getInterfaceTypeArguments());
    stringBuffer.append(TEXT_415);
    stringBuffer.append(genClass.getName());
    stringBuffer.append(TEXT_416);
    }
    }
    if (genPackage.isDataTypeConverters()) {
    for (GenDataType genDataType : genPackage.getAllGenDataTypes()) {
    if (genDataType.isSerializable()) {
    stringBuffer.append(TEXT_417);
    stringBuffer.append(genDataType.getFormattedName());
    stringBuffer.append(TEXT_418);
    stringBuffer.append(genDataType.getImportedParameterizedInstanceClassName());
    stringBuffer.append(TEXT_419);
    stringBuffer.append(genDataType.getName());
    stringBuffer.append(TEXT_420);
    stringBuffer.append(genDataType.getFormattedName());
    stringBuffer.append(TEXT_421);
    stringBuffer.append(genDataType.getName());
    stringBuffer.append(TEXT_422);
    stringBuffer.append(genDataType.getImportedBoundedWildcardInstanceClassName());
    stringBuffer.append(TEXT_423);
    }
    }
    }
    }
    if (!isImplementation && !genModel.isSuppressEMFMetaData()) {
    stringBuffer.append(TEXT_424);
    stringBuffer.append(genPackage.getPackageInterfaceName());
    stringBuffer.append(TEXT_425);
    stringBuffer.append(genPackage.getBasicPackageName());
    stringBuffer.append(TEXT_426);
    } else if (isImplementation) {
    stringBuffer.append(TEXT_427);
    stringBuffer.append(genPackage.getImportedPackageInterfaceName());
    stringBuffer.append(TEXT_428);
    stringBuffer.append(genPackage.getBasicPackageName());
    stringBuffer.append(TEXT_429);
    stringBuffer.append(genPackage.getImportedPackageInterfaceName());
    stringBuffer.append(TEXT_430);
    if (genModel.useClassOverrideAnnotation()) {
    stringBuffer.append(TEXT_431);
    }
    stringBuffer.append(TEXT_432);
    stringBuffer.append(genPackage.getImportedPackageInterfaceName());
    stringBuffer.append(TEXT_433);
    stringBuffer.append(genPackage.getImportedPackageInterfaceName());
    stringBuffer.append(TEXT_434);
    }
    stringBuffer.append(TEXT_435);
    stringBuffer.append(isInterface ? genPackage.getFactoryInterfaceName() : genPackage.getFactoryClassName());
    genModel.emitSortedImports();
    stringBuffer.append(TEXT_436);
    return stringBuffer.toString();
  }
}
