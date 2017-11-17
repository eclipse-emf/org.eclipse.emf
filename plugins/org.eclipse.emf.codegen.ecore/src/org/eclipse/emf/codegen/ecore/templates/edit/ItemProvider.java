package org.eclipse.emf.codegen.ecore.templates.edit;

import java.util.*;
import org.eclipse.emf.codegen.ecore.genmodel.*;

public class ItemProvider
{
  protected static String nl;
  public static synchronized ItemProvider create(String lineSeparator)
  {
    nl = lineSeparator;
    ItemProvider result = new ItemProvider();
    nl = null;
    return result;
  }

  public final String NL = nl == null ? (System.getProperties().getProperty("line.separator")) : nl;
  protected final String TEXT_1 = "";
  protected final String TEXT_2 = "/**";
  protected final String TEXT_3 = NL + " * ";
  protected final String TEXT_4 = NL + " */" + NL + "package ";
  protected final String TEXT_5 = ";" + NL + NL;
  protected final String TEXT_6 = NL + NL + "/**" + NL + " * This is the item provider adapter for a {@link ";
  protected final String TEXT_7 = "} object." + NL + " * <!-- begin-user-doc -->" + NL + " * <!-- end-user-doc -->";
  protected final String TEXT_8 = NL + " * ";
  protected final String TEXT_9 = NL + " * @generated" + NL + " */";
  protected final String TEXT_10 = NL + "@Deprecated";
  protected final String TEXT_11 = NL + "public class ";
  protected final String TEXT_12 = " ";
  protected final String TEXT_13 = "extends ";
  protected final String TEXT_14 = NL + "\textends ";
  protected final String TEXT_15 = NL + "\timplements";
  protected final String TEXT_16 = NL + "\t\t";
  protected final String TEXT_17 = ",";
  protected final String TEXT_18 = NL + "{";
  protected final String TEXT_19 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */" + NL + "\tpublic static final ";
  protected final String TEXT_20 = " copyright = ";
  protected final String TEXT_21 = ";";
  protected final String TEXT_22 = NL;
  protected final String TEXT_23 = NL + "\t/**" + NL + "\t * This constructs an instance from a factory and a notifier." + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */" + NL + "\tpublic ";
  protected final String TEXT_24 = "(AdapterFactory adapterFactory)" + NL + "\t{" + NL + "\t\tsuper(adapterFactory);" + NL + "\t}" + NL + "" + NL + "\t/**" + NL + "\t * This returns the property descriptors for the adapted class." + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */";
  protected final String TEXT_25 = NL + "\t@Override";
  protected final String TEXT_26 = NL + "\tpublic ";
  protected final String TEXT_27 = " getPropertyDescriptors(Object object)" + NL + "\t{" + NL + "\t\tif (itemPropertyDescriptors == null)" + NL + "\t\t{" + NL + "\t\t\tsuper.getPropertyDescriptors(object);" + NL;
  protected final String TEXT_28 = NL + "\t\t\tadd";
  protected final String TEXT_29 = "PropertyDescriptor(object);";
  protected final String TEXT_30 = NL + "\t\t}" + NL + "\t\treturn itemPropertyDescriptors;" + NL + "\t}" + NL;
  protected final String TEXT_31 = NL + "\t/**" + NL + "\t * This adds a property descriptor for the ";
  protected final String TEXT_32 = " feature." + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->";
  protected final String TEXT_33 = NL + "\t * ";
  protected final String TEXT_34 = NL + "\t * @generated" + NL + "\t */";
  protected final String TEXT_35 = NL + "\t@Deprecated";
  protected final String TEXT_36 = NL + "\tprotected void add";
  protected final String TEXT_37 = "PropertyDescriptor(Object object)" + NL + "\t{";
  protected final String TEXT_38 = NL + "\t\titemPropertyDescriptors.add" + NL + "\t\t\t(createItemPropertyDescriptor" + NL + "\t\t\t\t(((";
  protected final String TEXT_39 = ")adapterFactory).getRootAdapterFactory()," + NL + "\t\t\t\t getResourceLocator()," + NL + "\t\t\t\t getString(\"_UI_";
  protected final String TEXT_40 = "_";
  protected final String TEXT_41 = "_feature\"),";
  protected final String TEXT_42 = NL + "\t\t\t\t getString(\"_UI_PropertyDescriptor_description\", \"_UI_";
  protected final String TEXT_43 = "_";
  protected final String TEXT_44 = "_feature\", \"_UI_";
  protected final String TEXT_45 = "_type\"),";
  protected final String TEXT_46 = NL + "\t\t\t\t getString(\"_UI_";
  protected final String TEXT_47 = "_";
  protected final String TEXT_48 = "_description\"),";
  protected final String TEXT_49 = NL + "\t\t\t\t ";
  protected final String TEXT_50 = "," + NL + "\t\t\t\t ";
  protected final String TEXT_51 = "," + NL + "\t\t\t\t ";
  protected final String TEXT_52 = "," + NL + "\t\t\t\t ";
  protected final String TEXT_53 = ",";
  protected final String TEXT_54 = NL + "\t\t\t\t null,";
  protected final String TEXT_55 = NL + "\t\t\t\t ";
  protected final String TEXT_56 = ".";
  protected final String TEXT_57 = ",";
  protected final String TEXT_58 = NL + "\t\t\t\t null,";
  protected final String TEXT_59 = NL + "\t\t\t\t getString(\"";
  protected final String TEXT_60 = "\"),";
  protected final String TEXT_61 = NL + "\t\t\t\t null));";
  protected final String TEXT_62 = NL + "\t\t\t\t new String[] {";
  protected final String TEXT_63 = NL + "\t\t\t\t\t\"";
  protected final String TEXT_64 = "\"";
  protected final String TEXT_65 = ",";
  protected final String TEXT_66 = NL + "\t\t\t\t }));";
  protected final String TEXT_67 = NL + "\t}" + NL;
  protected final String TEXT_68 = NL + "\t/**" + NL + "\t * This specifies how to implement {@link #getChildren} and is used to deduce an appropriate feature for an" + NL + "\t * {@link org.eclipse.emf.edit.command.AddCommand}, {@link org.eclipse.emf.edit.command.RemoveCommand} or" + NL + "\t * {@link org.eclipse.emf.edit.command.MoveCommand} in {@link #createCommand}." + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */";
  protected final String TEXT_69 = NL + "\t@Override";
  protected final String TEXT_70 = NL + "\tpublic ";
  protected final String TEXT_71 = " getChildrenFeatures(Object object)" + NL + "\t{" + NL + "\t\tif (childrenFeatures == null)" + NL + "\t\t{" + NL + "\t\t\tsuper.getChildrenFeatures(object);";
  protected final String TEXT_72 = NL + "\t\t\tchildrenFeatures.add(";
  protected final String TEXT_73 = ");";
  protected final String TEXT_74 = NL + "\t\t}" + NL + "\t\treturn childrenFeatures;" + NL + "\t}" + NL;
  protected final String TEXT_75 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */";
  protected final String TEXT_76 = NL + "\t@Override";
  protected final String TEXT_77 = NL + "\tprotected ";
  protected final String TEXT_78 = " getChildFeature(Object object, Object child)" + NL + "\t{" + NL + "\t\t// Check the type of the specified child object and return the proper feature to use for" + NL + "\t\t// adding (see {@link AddCommand}) it as a child." + NL + "" + NL + "\t\treturn super.getChildFeature(object, child);" + NL + "\t}" + NL;
  protected final String TEXT_79 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */";
  protected final String TEXT_80 = NL + "\t@Override";
  protected final String TEXT_81 = NL + "\tpublic boolean hasChildren(Object object)" + NL + "\t{" + NL + "\t\treturn hasChildren(object, ";
  protected final String TEXT_82 = ");" + NL + "\t}" + NL;
  protected final String TEXT_83 = NL + "\t/**" + NL + "\t * This returns ";
  protected final String TEXT_84 = ".gif." + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */";
  protected final String TEXT_85 = NL + "\t@Override";
  protected final String TEXT_86 = NL + "\tpublic Object getImage(Object object)" + NL + "\t{" + NL + "\t\treturn overlayImage(object, getResourceLocator().getImage(\"full/obj16/";
  protected final String TEXT_87 = "\"));";
  protected final String TEXT_88 = NL + "\t}" + NL;
  protected final String TEXT_89 = NL + "\t/**" + NL + "\t * This returns <code>getImage(object)</code> for the column index <code>0</code> or <code>super.getImage(object)</code> otherwise." + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @see #getText(Object)" + NL + "\t * @see #getColumnText(Object, int)" + NL + "\t * @generated" + NL + "\t */";
  protected final String TEXT_90 = NL + "\t@Override";
  protected final String TEXT_91 = NL + "\tpublic Object getColumnImage(Object object, int columnIndex)" + NL + "\t{" + NL + "\t\t// TODO: implement this method to return appropriate information for each column." + NL + "\t\t// Ensure that you remove @generated or mark it @generated NOT" + NL + "\t\treturn columnIndex == 0 ? getImage(object) : super.getImage(object);" + NL + "\t}" + NL;
  protected final String TEXT_92 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */";
  protected final String TEXT_93 = NL + "\t@Override";
  protected final String TEXT_94 = NL + "\tprotected boolean shouldComposeCreationImage()" + NL + "\t{" + NL + "\t\treturn true;" + NL + "\t}" + NL;
  protected final String TEXT_95 = NL + "\t/**" + NL + "\t * This returns the label text for the adapted class." + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */";
  protected final String TEXT_96 = NL + "\t@Override";
  protected final String TEXT_97 = NL + "\tpublic String getText(Object object)" + NL + "\t{";
  protected final String TEXT_98 = NL + "\t\treturn ((";
  protected final String TEXT_99 = ")getStyledText(object)).getString();";
  protected final String TEXT_100 = NL + "\t\t";
  protected final String TEXT_101 = "<?, ?>";
  protected final String TEXT_102 = " ";
  protected final String TEXT_103 = " = (";
  protected final String TEXT_104 = "<?, ?>";
  protected final String TEXT_105 = ")object;";
  protected final String TEXT_106 = NL + "\t\treturn \"\" + ";
  protected final String TEXT_107 = ".getKey() + \" -> \" + ";
  protected final String TEXT_108 = ".getValue();";
  protected final String TEXT_109 = NL + "\t\tString key = crop(\"\" + ";
  protected final String TEXT_110 = ".getKey());";
  protected final String TEXT_111 = NL + "\t\tString key = \"\" + ";
  protected final String TEXT_112 = ".getKey();";
  protected final String TEXT_113 = NL + "\t\tString value = crop(\"\" + ";
  protected final String TEXT_114 = ".getValue());";
  protected final String TEXT_115 = NL + "\t\tString value = \"\" + ";
  protected final String TEXT_116 = ".getValue();";
  protected final String TEXT_117 = NL + "\t\treturn key + \" -> \" + value;";
  protected final String TEXT_118 = NL + "\t\t";
  protected final String TEXT_119 = " ";
  protected final String TEXT_120 = " = (";
  protected final String TEXT_121 = ")object;" + NL + "\t\treturn getString(\"_UI_";
  protected final String TEXT_122 = "_type\") + \" \" + ";
  protected final String TEXT_123 = ".";
  protected final String TEXT_124 = "();";
  protected final String TEXT_125 = NL + "\t\tString label = crop(((";
  protected final String TEXT_126 = ")object).";
  protected final String TEXT_127 = "());";
  protected final String TEXT_128 = NL + "\t\tString label = ((";
  protected final String TEXT_129 = ")object).";
  protected final String TEXT_130 = "();";
  protected final String TEXT_131 = NL + "\t\t";
  protected final String TEXT_132 = " labelValue = ((";
  protected final String TEXT_133 = ")object).eGet(";
  protected final String TEXT_134 = ");";
  protected final String TEXT_135 = NL + "\t\t";
  protected final String TEXT_136 = " labelValue = ((";
  protected final String TEXT_137 = ")object).";
  protected final String TEXT_138 = "();";
  protected final String TEXT_139 = NL + "\t\tString label = labelValue == null ? null : labelValue.toString();";
  protected final String TEXT_140 = NL + "\t\treturn label == null || label.length() == 0 ?" + NL + "\t\t\tgetString(\"_UI_";
  protected final String TEXT_141 = "_type\") :";
  protected final String TEXT_142 = NL + "\t\t\tgetString(\"_UI_";
  protected final String TEXT_143 = "_type\") + \" \" + label;";
  protected final String TEXT_144 = NL + "\t\treturn getString(\"_UI_";
  protected final String TEXT_145 = "_type\");";
  protected final String TEXT_146 = NL + "\t}" + NL;
  protected final String TEXT_147 = NL + "\t/**" + NL + "\t * This returns <code>getText(object)</code> for the column index <code>0</code> or <code>super.getText(object)</code> otherwise." + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @see #getImage(Object)" + NL + "\t * @see #getColumnImage(Object, int)" + NL + "\t * @generated" + NL + "\t */";
  protected final String TEXT_148 = NL + "\t@Override";
  protected final String TEXT_149 = NL + "\tpublic String getColumnText(Object object, int columnIndex)" + NL + "\t{" + NL + "\t\t// TODO: implement this method to return appropriate information for each column." + NL + "\t\t// Ensure that you remove @generated or mark it @generated NOT" + NL + "\t\treturn columnIndex == 0 ? getText(object) : super.getText(object);" + NL + "\t}" + NL;
  protected final String TEXT_150 = NL + "\t/**" + NL + "\t * This returns the label styled text for the adapted class." + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */";
  protected final String TEXT_151 = NL + "\t@Override";
  protected final String TEXT_152 = NL + "\tpublic Object getStyledText(Object object)" + NL + "\t{";
  protected final String TEXT_153 = NL + "\t\t";
  protected final String TEXT_154 = "<?, ?>";
  protected final String TEXT_155 = " ";
  protected final String TEXT_156 = " = (";
  protected final String TEXT_157 = "<?, ?>";
  protected final String TEXT_158 = ")object;";
  protected final String TEXT_159 = NL + "\t\treturn new ";
  protected final String TEXT_160 = "(\"\" + ";
  protected final String TEXT_161 = ".getKey()).append(\" -> \", ";
  protected final String TEXT_162 = ".QUALIFIER_STYLER).append(\"\" + ";
  protected final String TEXT_163 = ".getValue());";
  protected final String TEXT_164 = NL + "\t\tString key = crop(\"\" + ";
  protected final String TEXT_165 = ".getKey());";
  protected final String TEXT_166 = NL + "\t\tString key = \"\" + ";
  protected final String TEXT_167 = ".getKey();";
  protected final String TEXT_168 = NL + "\t\tString value = crop(\"\" + ";
  protected final String TEXT_169 = ".getValue());";
  protected final String TEXT_170 = NL + "\t\tString value = \"\" + ";
  protected final String TEXT_171 = ".getValue();";
  protected final String TEXT_172 = NL + "\t\treturn new ";
  protected final String TEXT_173 = "(key).append(\" -> \", ";
  protected final String TEXT_174 = ".QUALIFIER_STYLER).append(value);";
  protected final String TEXT_175 = NL + "\t\t";
  protected final String TEXT_176 = " ";
  protected final String TEXT_177 = " = (";
  protected final String TEXT_178 = ")object;" + NL + "\t\treturn new ";
  protected final String TEXT_179 = "(getString(\"_UI_";
  protected final String TEXT_180 = "_type\"), ";
  protected final String TEXT_181 = ".QUALIFIER_STYLER).append(\" \").append(";
  protected final String TEXT_182 = ".toString(";
  protected final String TEXT_183 = ".";
  protected final String TEXT_184 = "()));";
  protected final String TEXT_185 = NL + "\t\tString label = crop(((";
  protected final String TEXT_186 = ")object).";
  protected final String TEXT_187 = "());";
  protected final String TEXT_188 = NL + "\t\tString label = ((";
  protected final String TEXT_189 = ")object).";
  protected final String TEXT_190 = "();";
  protected final String TEXT_191 = NL + "\t\t";
  protected final String TEXT_192 = " labelValue = ((";
  protected final String TEXT_193 = ")object).eGet(";
  protected final String TEXT_194 = ");";
  protected final String TEXT_195 = NL + "\t\t";
  protected final String TEXT_196 = " labelValue = ((";
  protected final String TEXT_197 = ")object).";
  protected final String TEXT_198 = "();";
  protected final String TEXT_199 = NL + "\t\tString label = labelValue == null ? null : labelValue.toString();";
  protected final String TEXT_200 = NL + "    \t";
  protected final String TEXT_201 = " styledLabel = new ";
  protected final String TEXT_202 = "();" + NL + "\t\tif (label == null || label.length() == 0)" + NL + "\t\t{" + NL + "\t\t\tstyledLabel.append(getString(\"_UI_";
  protected final String TEXT_203 = "_type\"), ";
  protected final String TEXT_204 = ".QUALIFIER_STYLER); ";
  protected final String TEXT_205 = NL + "\t\t} else {" + NL + "\t\t\tstyledLabel.append(getString(\"_UI_";
  protected final String TEXT_206 = "_type\"), ";
  protected final String TEXT_207 = ".QUALIFIER_STYLER).append(\" \" + label);";
  protected final String TEXT_208 = NL + "\t\t}" + NL + "\t\treturn styledLabel;";
  protected final String TEXT_209 = NL + "\t\treturn new ";
  protected final String TEXT_210 = "(getString(\"_UI_";
  protected final String TEXT_211 = "_type\"));";
  protected final String TEXT_212 = NL + "\t}";
  protected final String TEXT_213 = NL + NL + "\t/**" + NL + "\t * This handles model notifications by calling {@link #updateChildren} to update any cached" + NL + "\t * children and by creating a viewer notification, which it passes to {@link #fireNotifyChanged}." + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */";
  protected final String TEXT_214 = NL + "\t@Override";
  protected final String TEXT_215 = NL + "\tpublic void notifyChanged(Notification notification)" + NL + "\t{" + NL + "\t\tupdateChildren(notification);";
  protected final String TEXT_216 = NL + NL + "\t\tswitch (notification.getFeatureID(";
  protected final String TEXT_217 = ".class))" + NL + "\t\t{";
  protected final String TEXT_218 = NL + "\t\t\tcase ";
  protected final String TEXT_219 = ":";
  protected final String TEXT_220 = NL + "\t\t\t\tfireNotifyChanged(new ";
  protected final String TEXT_221 = "(notification, notification.getNotifier(), false, true));" + NL + "\t\t\t\treturn;";
  protected final String TEXT_222 = NL + "\t\t\tcase ";
  protected final String TEXT_223 = ":";
  protected final String TEXT_224 = NL + "\t\t\t\tfireNotifyChanged(new ";
  protected final String TEXT_225 = "(notification, notification.getNotifier(), true, false));" + NL + "\t\t\t\treturn;";
  protected final String TEXT_226 = NL + "\t\t\tcase ";
  protected final String TEXT_227 = ":";
  protected final String TEXT_228 = NL + "\t\t\t\tfireNotifyChanged(new ";
  protected final String TEXT_229 = "(notification, notification.getNotifier(), true, true));" + NL + "\t\t\t\treturn;";
  protected final String TEXT_230 = NL + "\t\t}";
  protected final String TEXT_231 = NL + "\t\tsuper.notifyChanged(notification);" + NL + "\t}" + NL;
  protected final String TEXT_232 = NL + "\t/**" + NL + "\t * This adds {@link org.eclipse.emf.edit.command.CommandParameter}s describing the children" + NL + "\t * that can be created under this object." + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */";
  protected final String TEXT_233 = NL + "\t@Override";
  protected final String TEXT_234 = NL + "\tprotected void collectNewChildDescriptors(";
  protected final String TEXT_235 = " newChildDescriptors, Object object)" + NL + "\t{" + NL + "\t\tsuper.collectNewChildDescriptors(newChildDescriptors, object);";
  protected final String TEXT_236 = NL + NL + "\t\tnewChildDescriptors.add" + NL + "\t\t\t(createChildParameter" + NL + "\t\t\t\t(";
  protected final String TEXT_237 = "," + NL + "\t\t\t\t ";
  protected final String TEXT_238 = ".createEntry" + NL + "\t\t\t\t\t(";
  protected final String TEXT_239 = ",";
  protected final String TEXT_240 = NL + "\t\t\t\t\t ";
  protected final String TEXT_241 = ".create(";
  protected final String TEXT_242 = "))));";
  protected final String TEXT_243 = NL + "\t\t\t\t\t ";
  protected final String TEXT_244 = ".create";
  protected final String TEXT_245 = "())));";
  protected final String TEXT_246 = NL + NL + "\t\tnewChildDescriptors.add" + NL + "\t\t\t(createChildParameter" + NL + "\t\t\t\t(";
  protected final String TEXT_247 = "," + NL + "\t\t\t\t ";
  protected final String TEXT_248 = ".createEntry" + NL + "\t\t\t\t\t(";
  protected final String TEXT_249 = ",";
  protected final String TEXT_250 = NL + "\t\t\t\t\t ";
  protected final String TEXT_251 = ")));";
  protected final String TEXT_252 = NL + "\t\t\t\t\t ";
  protected final String TEXT_253 = ")));";
  protected final String TEXT_254 = NL + "\t\t\t\t\t ";
  protected final String TEXT_255 = ".createFromString(";
  protected final String TEXT_256 = ", ";
  protected final String TEXT_257 = "))));";
  protected final String TEXT_258 = " // TODO: ensure this is a valid literal value";
  protected final String TEXT_259 = NL + NL + "\t\tnewChildDescriptors.add" + NL + "\t\t\t(createChildParameter" + NL + "\t\t\t\t(";
  protected final String TEXT_260 = ",";
  protected final String TEXT_261 = NL + "\t\t\t\t ";
  protected final String TEXT_262 = ".create(";
  protected final String TEXT_263 = ")));";
  protected final String TEXT_264 = NL + "\t\t\t\t ";
  protected final String TEXT_265 = ".create";
  protected final String TEXT_266 = "()));";
  protected final String TEXT_267 = NL + NL + "\t\tnewChildDescriptors.add" + NL + "\t\t\t(createChildParameter" + NL + "\t\t\t\t(";
  protected final String TEXT_268 = ",";
  protected final String TEXT_269 = NL + "\t\t\t\t ";
  protected final String TEXT_270 = "));";
  protected final String TEXT_271 = NL + "\t\t\t\t ";
  protected final String TEXT_272 = "));";
  protected final String TEXT_273 = NL + "\t\t\t\t ";
  protected final String TEXT_274 = ".createFromString(";
  protected final String TEXT_275 = ", ";
  protected final String TEXT_276 = ")));";
  protected final String TEXT_277 = " // TODO: ensure this is a valid literal value";
  protected final String TEXT_278 = NL + "\t}" + NL;
  protected final String TEXT_279 = NL + "\t/**" + NL + "\t * This returns the label text for {@link org.eclipse.emf.edit.command.CreateChildCommand}." + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */";
  protected final String TEXT_280 = NL + "\t@Override";
  protected final String TEXT_281 = NL + "\tpublic String getCreateChildText(Object owner, Object feature, Object child, ";
  protected final String TEXT_282 = " selection)" + NL + "\t{" + NL + "\t\tObject childFeature = feature;" + NL + "\t\tObject childObject = child;" + NL;
  protected final String TEXT_283 = NL + "\t\tif (childFeature instanceof ";
  protected final String TEXT_284 = " && ";
  protected final String TEXT_285 = ".isFeatureMap((EStructuralFeature)childFeature))" + NL + "\t\t{" + NL + "\t\t\t";
  protected final String TEXT_286 = ".Entry entry = (FeatureMap.Entry)childObject;" + NL + "\t\t\tchildFeature = entry.getEStructuralFeature();" + NL + "\t\t\tchildObject = entry.getValue();" + NL + "\t\t}" + NL;
  protected final String TEXT_287 = NL + "\t\tboolean qualify =";
  protected final String TEXT_288 = NL + "\t\t\tchildFeature == ";
  protected final String TEXT_289 = NL + NL + "\t\tif (qualify)" + NL + "\t\t{" + NL + "\t\t\treturn getString" + NL + "\t\t\t\t(\"_UI_CreateChild_text2\",";
  protected final String TEXT_290 = NL + "\t\t\t\t new Object[] { getTypeText(childObject), getFeatureText(childFeature), getTypeText(owner) });" + NL + "\t\t}" + NL + "\t\treturn super.getCreateChildText(owner, feature, child, selection);" + NL + "\t}" + NL;
  protected final String TEXT_291 = NL + "\t/**" + NL + "\t * Return the resource locator for this item provider's resources." + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */";
  protected final String TEXT_292 = NL + "\t@Override";
  protected final String TEXT_293 = NL + "\tpublic ";
  protected final String TEXT_294 = " getResourceLocator()" + NL + "\t{";
  protected final String TEXT_295 = NL + "\t\treturn ((";
  protected final String TEXT_296 = ")adapterFactory).getResourceLocator();";
  protected final String TEXT_297 = NL + "\t\treturn ";
  protected final String TEXT_298 = ".INSTANCE;";
  protected final String TEXT_299 = NL + "\t}" + NL;
  protected final String TEXT_300 = NL + "}";
  protected final String TEXT_301 = NL;

  public String generate(Object argument)
  {
    final StringBuffer stringBuffer = new StringBuffer();
    
/**
 * Copyright (c) 2002-2007 IBM Corporation and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *   IBM - Initial API and implementation
 */

    GenClass genClass = (GenClass)argument; GenPackage genPackage = genClass.getGenPackage(); GenModel genModel=genPackage.getGenModel();
    final boolean isJDK50 = genModel.getComplianceLevel().getValue() >= GenJDKLevel.JDK50;
    stringBuffer.append(TEXT_1);
    stringBuffer.append(TEXT_2);
    {GenBase copyrightHolder = argument instanceof GenBase ? (GenBase)argument : argument instanceof Object[] && ((Object[])argument)[0] instanceof GenBase ? (GenBase)((Object[])argument)[0] : null;
    if (copyrightHolder != null && copyrightHolder.hasCopyright()) {
    stringBuffer.append(TEXT_3);
    stringBuffer.append(copyrightHolder.getCopyright(copyrightHolder.getGenModel().getIndentation(stringBuffer)));
    }}
    stringBuffer.append(TEXT_4);
    stringBuffer.append(genPackage.getProviderPackageName());
    stringBuffer.append(TEXT_5);
    genModel.addImport("org.eclipse.emf.common.notify.AdapterFactory");
    genModel.addImport("org.eclipse.emf.common.notify.Notification");
    String _List = genModel.getImportedName(genModel.useGenerics() ? "java.util.List<org.eclipse.emf.edit.provider.IItemPropertyDescriptor>" : "java.util.List");
    genModel.markImportLocation(stringBuffer);
    stringBuffer.append(TEXT_6);
    stringBuffer.append(genClass.getRawQualifiedInterfaceName());
    stringBuffer.append(TEXT_7);
    if (genClass.hasImplicitAPITags()) {
    stringBuffer.append(TEXT_8);
    stringBuffer.append(genClass.getImplicitAPITags(genModel.getIndentation(stringBuffer)));
    }
    stringBuffer.append(TEXT_9);
    if (isJDK50 && genClass.hasImplicitAPIDeprecatedTag()) {
    stringBuffer.append(TEXT_10);
    }
    stringBuffer.append(TEXT_11);
    stringBuffer.append(genClass.getProviderClassName());
    stringBuffer.append(TEXT_12);
    if (genClass.getProviderImplementsClassNames().isEmpty()) {
    stringBuffer.append(TEXT_13);
    stringBuffer.append(genClass.getProviderBaseClassName() != null ? genClass.getProviderBaseClassName() : genModel.getImportedName("org.eclipse.emf.edit.provider.ItemProviderAdapter"));
    }
    if (!genClass.getProviderImplementsClassNames().isEmpty()) {
    stringBuffer.append(TEXT_14);
    stringBuffer.append(genClass.getProviderBaseClassName() != null ? genClass.getProviderBaseClassName() : genModel.getImportedName("org.eclipse.emf.edit.provider.ItemProviderAdapter"));
    stringBuffer.append(TEXT_15);
    for (Iterator<String> i = genClass.getProviderImplementsClassNames().iterator(); i.hasNext(); ) {
    stringBuffer.append(TEXT_16);
    stringBuffer.append(genModel.getImportedName(i.next()));
    if (i.hasNext()){
    stringBuffer.append(TEXT_17);
    }
    }
    }
    stringBuffer.append(TEXT_18);
    if (genModel.hasCopyrightField()) {
    stringBuffer.append(TEXT_19);
    stringBuffer.append(genModel.getImportedName("java.lang.String"));
    stringBuffer.append(TEXT_20);
    stringBuffer.append(genModel.getCopyrightFieldLiteral());
    stringBuffer.append(TEXT_21);
    stringBuffer.append(genModel.getNonNLS());
    stringBuffer.append(TEXT_22);
    }
    stringBuffer.append(TEXT_23);
    stringBuffer.append(genClass.getProviderClassName());
    stringBuffer.append(TEXT_24);
    if (genModel.useClassOverrideAnnotation()) {
    stringBuffer.append(TEXT_25);
    }
    stringBuffer.append(TEXT_26);
    stringBuffer.append(_List);
    stringBuffer.append(TEXT_27);
    for (GenFeature genFeature : genClass.getPropertyFeatures()) { 
    stringBuffer.append(TEXT_28);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_29);
    }
    stringBuffer.append(TEXT_30);
    for (GenFeature genFeature : genClass.getPropertyFeatures()) { 
    stringBuffer.append(TEXT_31);
    stringBuffer.append(genFeature.getFormattedName());
    stringBuffer.append(TEXT_32);
    if (genFeature.hasAPITags()) {
    stringBuffer.append(TEXT_33);
    stringBuffer.append(genFeature.getAPITags(genModel.getIndentation(stringBuffer)));
    }
    stringBuffer.append(TEXT_34);
    if (isJDK50 && genFeature.hasAPIDeprecatedTag()) {
    stringBuffer.append(TEXT_35);
    }
    stringBuffer.append(TEXT_36);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_37);
    stringBuffer.append(TEXT_38);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.edit.provider.ComposeableAdapterFactory"));
    stringBuffer.append(TEXT_39);
    stringBuffer.append(genFeature.getGenClass().getName());
    stringBuffer.append(TEXT_40);
    stringBuffer.append(genFeature.getName());
    stringBuffer.append(TEXT_41);
    stringBuffer.append(genModel.getNonNLS());
    if (genFeature.getPropertyDescription() == null || genFeature.getPropertyDescription().length() == 0) {
    stringBuffer.append(TEXT_42);
    stringBuffer.append(genFeature.getGenClass().getName());
    stringBuffer.append(TEXT_43);
    stringBuffer.append(genFeature.getName());
    stringBuffer.append(TEXT_44);
    stringBuffer.append(genFeature.getGenClass().getName());
    stringBuffer.append(TEXT_45);
    stringBuffer.append(genModel.getNonNLS());
    stringBuffer.append(genModel.getNonNLS(2));
    stringBuffer.append(genModel.getNonNLS(3));
    } else {
    stringBuffer.append(TEXT_46);
    stringBuffer.append(genFeature.getGenClass().getName());
    stringBuffer.append(TEXT_47);
    stringBuffer.append(genFeature.getName());
    stringBuffer.append(TEXT_48);
    stringBuffer.append(genModel.getNonNLS());
    }
    stringBuffer.append(TEXT_49);
    stringBuffer.append(genFeature.getQualifiedFeatureAccessor());
    stringBuffer.append(TEXT_50);
    stringBuffer.append(genFeature.getProperty() == GenPropertyKind.EDITABLE_LITERAL ? "true" : "false");
    stringBuffer.append(TEXT_51);
    stringBuffer.append(genFeature.isPropertyMultiLine() ? "true" : "false");
    stringBuffer.append(TEXT_52);
    stringBuffer.append(genFeature.isPropertySortChoices() ? "true" : "false");
    stringBuffer.append(TEXT_53);
    if (genFeature.isReferenceType()) {
    stringBuffer.append(TEXT_54);
    } else {
    stringBuffer.append(TEXT_55);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.edit.provider.ItemPropertyDescriptor"));
    stringBuffer.append(TEXT_56);
    stringBuffer.append(genFeature.getPropertyImageName());
    stringBuffer.append(TEXT_57);
    }
    if (genFeature.getPropertyCategory() == null || genFeature.getPropertyCategory().length() == 0) {
    stringBuffer.append(TEXT_58);
    } else {
    stringBuffer.append(TEXT_59);
    stringBuffer.append(genModel.getPropertyCategoryKey(genFeature.getPropertyCategory()));
    stringBuffer.append(TEXT_60);
    stringBuffer.append(genModel.getNonNLS());
    }
    if (genFeature.getPropertyFilterFlags().isEmpty()) {
    stringBuffer.append(TEXT_61);
    } else {
    stringBuffer.append(TEXT_62);
    for (Iterator<String> j = genFeature.getPropertyFilterFlags().iterator(); j.hasNext();) { String filterFlag = j.next();
    if (filterFlag != null && filterFlag.length() > 0) {
    stringBuffer.append(TEXT_63);
    stringBuffer.append(filterFlag);
    stringBuffer.append(TEXT_64);
    if (j.hasNext()) {
    stringBuffer.append(TEXT_65);
    }
    stringBuffer.append(genModel.getNonNLS());
    }
    }
    stringBuffer.append(TEXT_66);
    }
    //ItemProvider/addPropertyDescriptor.override.javajetinc
    stringBuffer.append(TEXT_67);
    }
    if (!genClass.getChildrenFeatures().isEmpty()) {
    stringBuffer.append(TEXT_68);
    if (genModel.useClassOverrideAnnotation()) {
    stringBuffer.append(TEXT_69);
    }
    stringBuffer.append(TEXT_70);
    stringBuffer.append(genModel.getImportedName(genModel.useGenerics() ? "java.util.Collection<? extends org.eclipse.emf.ecore.EStructuralFeature>" : "java.util.Collection"));
    stringBuffer.append(TEXT_71);
    for (GenFeature genFeature : genClass.getChildrenFeatures()) { 
    stringBuffer.append(TEXT_72);
    stringBuffer.append(genFeature.getQualifiedFeatureAccessor());
    stringBuffer.append(TEXT_73);
    }
    stringBuffer.append(TEXT_74);
    if (!genClass.getChildrenFeatures().isEmpty()) {
    stringBuffer.append(TEXT_75);
    if (genModel.useClassOverrideAnnotation()) {
    stringBuffer.append(TEXT_76);
    }
    stringBuffer.append(TEXT_77);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.EStructuralFeature"));
    stringBuffer.append(TEXT_78);
    }
    }
    if (genClass.needsHasChildrenMethodOverride()) {
    stringBuffer.append(TEXT_79);
    if (genModel.useClassOverrideAnnotation()) {
    stringBuffer.append(TEXT_80);
    }
    stringBuffer.append(TEXT_81);
    stringBuffer.append(genModel.isOptimizedHasChildren());
    stringBuffer.append(TEXT_82);
    }
    if (genClass.isImage()) {
    stringBuffer.append(TEXT_83);
    stringBuffer.append(genClass.getName());
    stringBuffer.append(TEXT_84);
    if (genModel.useClassOverrideAnnotation()) {
    stringBuffer.append(TEXT_85);
    }
    stringBuffer.append(TEXT_86);
    stringBuffer.append(genClass.getName());
    stringBuffer.append(TEXT_87);
    stringBuffer.append(genModel.getNonNLS());
    stringBuffer.append(TEXT_88);
    }
    if (genClass.getProviderImplementsClassNames().contains("org.eclipse.emf.edit.provider.ITableItemLabelProvider")) {
    stringBuffer.append(TEXT_89);
    if (genModel.useClassOverrideAnnotation()) {
    stringBuffer.append(TEXT_90);
    }
    stringBuffer.append(TEXT_91);
    }
    if (genModel.getRuntimeVersion().getValue() >= GenRuntimeVersion.EMF26_VALUE && !genModel.isCreationIcons()) {
    stringBuffer.append(TEXT_92);
    if (genModel.useClassOverrideAnnotation()) {
    stringBuffer.append(TEXT_93);
    }
    stringBuffer.append(TEXT_94);
    }
    stringBuffer.append(TEXT_95);
    if (genModel.useClassOverrideAnnotation()) {
    stringBuffer.append(TEXT_96);
    }
    stringBuffer.append(TEXT_97);
    if (genModel.isStyleProviders()) {
    stringBuffer.append(TEXT_98);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.edit.provider.StyledString"));
    stringBuffer.append(TEXT_99);
    } else {
    if (genClass.isMapEntry()) {
    stringBuffer.append(TEXT_100);
    stringBuffer.append(genClass.getImportedInterfaceName());
    if (genModel.useGenerics()) {
    stringBuffer.append(TEXT_101);
    }
    stringBuffer.append(TEXT_102);
    stringBuffer.append(genClass.getSafeUncapName());
    stringBuffer.append(TEXT_103);
    stringBuffer.append(genClass.getImportedInterfaceName());
    if (genModel.useGenerics()) {
    stringBuffer.append(TEXT_104);
    }
    stringBuffer.append(TEXT_105);
    if (!genClass.getMapEntryKeyFeature().isPropertyMultiLine() && !genClass.getMapEntryValueFeature().isPropertyMultiLine()) {
    stringBuffer.append(TEXT_106);
    stringBuffer.append(genClass.getSafeUncapName());
    stringBuffer.append(TEXT_107);
    stringBuffer.append(genClass.getSafeUncapName());
    stringBuffer.append(TEXT_108);
    stringBuffer.append(genModel.getNonNLS());
    stringBuffer.append(genModel.getNonNLS(2));
    } else {
    if (genClass.getMapEntryKeyFeature().isPropertyMultiLine()) {
    stringBuffer.append(TEXT_109);
    stringBuffer.append(genClass.getSafeUncapName());
    stringBuffer.append(TEXT_110);
    stringBuffer.append(genModel.getNonNLS());
    } else {
    stringBuffer.append(TEXT_111);
    stringBuffer.append(genClass.getSafeUncapName());
    stringBuffer.append(TEXT_112);
    stringBuffer.append(genModel.getNonNLS());
    }
    if (genClass.getMapEntryValueFeature().isPropertyMultiLine()) {
    stringBuffer.append(TEXT_113);
    stringBuffer.append(genClass.getSafeUncapName());
    stringBuffer.append(TEXT_114);
    stringBuffer.append(genModel.getNonNLS());
    } else {
    stringBuffer.append(TEXT_115);
    stringBuffer.append(genClass.getSafeUncapName());
    stringBuffer.append(TEXT_116);
    stringBuffer.append(genModel.getNonNLS());
    }
    stringBuffer.append(TEXT_117);
    stringBuffer.append(genModel.getNonNLS());
    }
    } else if (genClass.getLabelFeature() != null) { GenFeature labelFeature = genClass.getLabelFeature();
    if (labelFeature.isPrimitiveType() && !labelFeature.getGenClass().isDynamic() && !labelFeature.isSuppressedGetVisibility()) {
    stringBuffer.append(TEXT_118);
    stringBuffer.append(genClass.getImportedInterfaceName());
    stringBuffer.append(genClass.getInterfaceWildTypeArguments());
    stringBuffer.append(TEXT_119);
    stringBuffer.append(genClass.getSafeUncapName());
    stringBuffer.append(TEXT_120);
    stringBuffer.append(genClass.getImportedInterfaceName());
    stringBuffer.append(genClass.getInterfaceWildTypeArguments());
    stringBuffer.append(TEXT_121);
    stringBuffer.append(genClass.getName());
    stringBuffer.append(TEXT_122);
    stringBuffer.append(genClass.getSafeUncapName());
    stringBuffer.append(TEXT_123);
    stringBuffer.append(genClass.getLabelFeature().getGetAccessor());
    stringBuffer.append(TEXT_124);
    stringBuffer.append(genModel.getNonNLS());
    stringBuffer.append(genModel.getNonNLS(2));
    } else {
    if (labelFeature.isStringType() && !labelFeature.getGenClass().isDynamic() && !labelFeature.isSuppressedGetVisibility()) {
    if (labelFeature.isPropertyMultiLine()) {
    stringBuffer.append(TEXT_125);
    stringBuffer.append(genClass.getImportedInterfaceName());
    stringBuffer.append(genClass.getInterfaceWildTypeArguments());
    stringBuffer.append(TEXT_126);
    stringBuffer.append(labelFeature.getGetAccessor());
    stringBuffer.append(TEXT_127);
    } else {
    stringBuffer.append(TEXT_128);
    stringBuffer.append(genClass.getImportedInterfaceName());
    stringBuffer.append(genClass.getInterfaceWildTypeArguments());
    stringBuffer.append(TEXT_129);
    stringBuffer.append(labelFeature.getGetAccessor());
    stringBuffer.append(TEXT_130);
    }
    } else {
    if (labelFeature.isSuppressedGetVisibility() || labelFeature.getGenClass().isDynamic()) {
    stringBuffer.append(TEXT_131);
    stringBuffer.append(genModel.getImportedName("java.lang.Object"));
    stringBuffer.append(TEXT_132);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.EObject"));
    stringBuffer.append(TEXT_133);
    stringBuffer.append(labelFeature.getQualifiedFeatureAccessor());
    stringBuffer.append(TEXT_134);
    } else {
    stringBuffer.append(TEXT_135);
    stringBuffer.append(labelFeature.getRawImportedType());
    stringBuffer.append(TEXT_136);
    stringBuffer.append(genClass.getImportedInterfaceName());
    stringBuffer.append(genClass.getInterfaceWildTypeArguments());
    stringBuffer.append(TEXT_137);
    stringBuffer.append(labelFeature.getGetAccessor());
    stringBuffer.append(TEXT_138);
    }
    stringBuffer.append(TEXT_139);
    }
    stringBuffer.append(TEXT_140);
    stringBuffer.append(genClass.getName());
    stringBuffer.append(TEXT_141);
    stringBuffer.append(genModel.getNonNLS());
    stringBuffer.append(TEXT_142);
    stringBuffer.append(genClass.getName());
    stringBuffer.append(TEXT_143);
    stringBuffer.append(genModel.getNonNLS());
    stringBuffer.append(genModel.getNonNLS(2));
    }
    } else {
    stringBuffer.append(TEXT_144);
    stringBuffer.append(genClass.getName());
    stringBuffer.append(TEXT_145);
    stringBuffer.append(genModel.getNonNLS());
    }
    }
    //ItemProvider/getText.override.javajetinc
    stringBuffer.append(TEXT_146);
    if (genClass.getProviderImplementsClassNames().contains("org.eclipse.emf.edit.provider.ITableItemLabelProvider")) {
    stringBuffer.append(TEXT_147);
    if (genModel.useClassOverrideAnnotation()) {
    stringBuffer.append(TEXT_148);
    }
    stringBuffer.append(TEXT_149);
    }
    if (genModel.isStyleProviders()) {
    stringBuffer.append(TEXT_150);
    if (genModel.useClassOverrideAnnotation()) {
    stringBuffer.append(TEXT_151);
    }
    stringBuffer.append(TEXT_152);
    if (genClass.isMapEntry()) {
    stringBuffer.append(TEXT_153);
    stringBuffer.append(genClass.getImportedInterfaceName());
    if (genModel.useGenerics()) {
    stringBuffer.append(TEXT_154);
    }
    stringBuffer.append(TEXT_155);
    stringBuffer.append(genClass.getSafeUncapName());
    stringBuffer.append(TEXT_156);
    stringBuffer.append(genClass.getImportedInterfaceName());
    if (genModel.useGenerics()) {
    stringBuffer.append(TEXT_157);
    }
    stringBuffer.append(TEXT_158);
    if (!genClass.getMapEntryKeyFeature().isPropertyMultiLine() && !genClass.getMapEntryValueFeature().isPropertyMultiLine()) {
    stringBuffer.append(TEXT_159);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.edit.provider.StyledString"));
    stringBuffer.append(TEXT_160);
    stringBuffer.append(genClass.getSafeUncapName());
    stringBuffer.append(TEXT_161);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.edit.provider.StyledString$Style"));
    stringBuffer.append(TEXT_162);
    stringBuffer.append(genClass.getSafeUncapName());
    stringBuffer.append(TEXT_163);
    stringBuffer.append(genModel.getNonNLS());
    stringBuffer.append(genModel.getNonNLS(2));
    } else {
    if (genClass.getMapEntryKeyFeature().isPropertyMultiLine()) {
    stringBuffer.append(TEXT_164);
    stringBuffer.append(genClass.getSafeUncapName());
    stringBuffer.append(TEXT_165);
    stringBuffer.append(genModel.getNonNLS());
    } else {
    stringBuffer.append(TEXT_166);
    stringBuffer.append(genClass.getSafeUncapName());
    stringBuffer.append(TEXT_167);
    stringBuffer.append(genModel.getNonNLS());
    }
    if (genClass.getMapEntryValueFeature().isPropertyMultiLine()) {
    stringBuffer.append(TEXT_168);
    stringBuffer.append(genClass.getSafeUncapName());
    stringBuffer.append(TEXT_169);
    stringBuffer.append(genModel.getNonNLS());
    } else {
    stringBuffer.append(TEXT_170);
    stringBuffer.append(genClass.getSafeUncapName());
    stringBuffer.append(TEXT_171);
    stringBuffer.append(genModel.getNonNLS());
    }
    stringBuffer.append(TEXT_172);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.edit.provider.StyledString"));
    stringBuffer.append(TEXT_173);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.edit.provider.StyledString$Style"));
    stringBuffer.append(TEXT_174);
    stringBuffer.append(genModel.getNonNLS());
    }
    } else if (genClass.getLabelFeature() != null) { GenFeature labelFeature = genClass.getLabelFeature();
    if (labelFeature.isPrimitiveType() && !labelFeature.getGenClass().isDynamic() && !labelFeature.isSuppressedGetVisibility()) {
    stringBuffer.append(TEXT_175);
    stringBuffer.append(genClass.getImportedInterfaceName());
    stringBuffer.append(genClass.getInterfaceWildTypeArguments());
    stringBuffer.append(TEXT_176);
    stringBuffer.append(genClass.getSafeUncapName());
    stringBuffer.append(TEXT_177);
    stringBuffer.append(genClass.getImportedInterfaceName());
    stringBuffer.append(genClass.getInterfaceWildTypeArguments());
    stringBuffer.append(TEXT_178);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.edit.provider.StyledString"));
    stringBuffer.append(TEXT_179);
    stringBuffer.append(genClass.getName());
    stringBuffer.append(TEXT_180);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.edit.provider.StyledString$Style"));
    stringBuffer.append(TEXT_181);
    stringBuffer.append(labelFeature.getObjectType(genClass));
    stringBuffer.append(TEXT_182);
    stringBuffer.append(genClass.getSafeUncapName());
    stringBuffer.append(TEXT_183);
    stringBuffer.append(genClass.getLabelFeature().getGetAccessor());
    stringBuffer.append(TEXT_184);
    stringBuffer.append(genModel.getNonNLS());
    stringBuffer.append(genModel.getNonNLS(2));
    } else {
    if (labelFeature.isStringType() && !labelFeature.getGenClass().isDynamic() && !labelFeature.isSuppressedGetVisibility()) {
    if (labelFeature.isPropertyMultiLine()) {
    stringBuffer.append(TEXT_185);
    stringBuffer.append(genClass.getImportedInterfaceName());
    stringBuffer.append(genClass.getInterfaceWildTypeArguments());
    stringBuffer.append(TEXT_186);
    stringBuffer.append(labelFeature.getGetAccessor());
    stringBuffer.append(TEXT_187);
    } else {
    stringBuffer.append(TEXT_188);
    stringBuffer.append(genClass.getImportedInterfaceName());
    stringBuffer.append(genClass.getInterfaceWildTypeArguments());
    stringBuffer.append(TEXT_189);
    stringBuffer.append(labelFeature.getGetAccessor());
    stringBuffer.append(TEXT_190);
    }
    } else {
    if (labelFeature.isSuppressedGetVisibility() || labelFeature.getGenClass().isDynamic()) {
    stringBuffer.append(TEXT_191);
    stringBuffer.append(genModel.getImportedName("java.lang.Object"));
    stringBuffer.append(TEXT_192);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.EObject"));
    stringBuffer.append(TEXT_193);
    stringBuffer.append(labelFeature.getQualifiedFeatureAccessor());
    stringBuffer.append(TEXT_194);
    } else {
    stringBuffer.append(TEXT_195);
    stringBuffer.append(labelFeature.getRawImportedType());
    stringBuffer.append(TEXT_196);
    stringBuffer.append(genClass.getImportedInterfaceName());
    stringBuffer.append(genClass.getInterfaceWildTypeArguments());
    stringBuffer.append(TEXT_197);
    stringBuffer.append(labelFeature.getGetAccessor());
    stringBuffer.append(TEXT_198);
    }
    stringBuffer.append(TEXT_199);
    }
    stringBuffer.append(TEXT_200);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.edit.provider.StyledString"));
    stringBuffer.append(TEXT_201);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.edit.provider.StyledString"));
    stringBuffer.append(TEXT_202);
    stringBuffer.append(genClass.getName());
    stringBuffer.append(TEXT_203);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.edit.provider.StyledString$Style"));
    stringBuffer.append(TEXT_204);
    stringBuffer.append(genModel.getNonNLS());
    stringBuffer.append(TEXT_205);
    stringBuffer.append(genClass.getName());
    stringBuffer.append(TEXT_206);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.edit.provider.StyledString$Style"));
    stringBuffer.append(TEXT_207);
    stringBuffer.append(genModel.getNonNLS());
    stringBuffer.append(genModel.getNonNLS(2));
    stringBuffer.append(TEXT_208);
    }
    } else {
    stringBuffer.append(TEXT_209);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.edit.provider.StyledString"));
    stringBuffer.append(TEXT_210);
    stringBuffer.append(genClass.getName());
    stringBuffer.append(TEXT_211);
    stringBuffer.append(genModel.getNonNLS());
    }
    //ItemProvider/getStyledText.override.javajetinc
    stringBuffer.append(TEXT_212);
    }
    stringBuffer.append(TEXT_213);
    if (genModel.useClassOverrideAnnotation()) {
    stringBuffer.append(TEXT_214);
    }
    stringBuffer.append(TEXT_215);
    if (!genClass.getLabelNotifyFeatures().isEmpty() || !genClass.getContentNotifyFeatures().isEmpty() || !genClass.getLabelAndContentNotifyFeatures().isEmpty()) {
    stringBuffer.append(TEXT_216);
    stringBuffer.append(genClass.getRawImportedInterfaceName());
    stringBuffer.append(TEXT_217);
    if (!genClass.getLabelNotifyFeatures().isEmpty()) {
    for (GenFeature genFeature : genClass.getLabelNotifyFeatures()) { 
    stringBuffer.append(TEXT_218);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(TEXT_219);
    }
    stringBuffer.append(TEXT_220);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.edit.provider.ViewerNotification"));
    stringBuffer.append(TEXT_221);
    }
    if (!genClass.getContentNotifyFeatures().isEmpty()) {
    for (GenFeature genFeature : genClass.getContentNotifyFeatures()) { 
    stringBuffer.append(TEXT_222);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(TEXT_223);
    }
    stringBuffer.append(TEXT_224);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.edit.provider.ViewerNotification"));
    stringBuffer.append(TEXT_225);
    }
    if (!genClass.getLabelAndContentNotifyFeatures().isEmpty()) {
    for (GenFeature genFeature : genClass.getLabelAndContentNotifyFeatures()) { 
    stringBuffer.append(TEXT_226);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(TEXT_227);
    }
    stringBuffer.append(TEXT_228);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.edit.provider.ViewerNotification"));
    stringBuffer.append(TEXT_229);
    }
    stringBuffer.append(TEXT_230);
    }
    stringBuffer.append(TEXT_231);
    if (genModel.isCreationCommands()) {
    stringBuffer.append(TEXT_232);
    if (genModel.useClassOverrideAnnotation()) {
    stringBuffer.append(TEXT_233);
    }
    stringBuffer.append(TEXT_234);
    stringBuffer.append(genModel.getImportedName(genModel.useGenerics() ? "java.util.Collection<java.lang.Object>" : "java.util.Collection"));
    stringBuffer.append(TEXT_235);
    for (GenClass.ChildCreationData childCreationData : genClass.getChildCreationData()) { GenFeature createFeature = childCreationData.createFeature; GenFeature delegatedFeature = childCreationData.delegatedFeature; GenClassifier createClassifier = childCreationData.createClassifier;
    if (createFeature.isFeatureMapType()) {
    if (delegatedFeature.isReferenceType()) { GenClass createClass = (GenClass)createClassifier;
    stringBuffer.append(TEXT_236);
    stringBuffer.append(createFeature.getQualifiedFeatureAccessor());
    stringBuffer.append(TEXT_237);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.FeatureMapUtil"));
    stringBuffer.append(TEXT_238);
    stringBuffer.append(delegatedFeature.getQualifiedFeatureAccessor());
    stringBuffer.append(TEXT_239);
    if (createClass.isMapEntry()) { 
    stringBuffer.append(TEXT_240);
    stringBuffer.append(createClass.getGenPackage().getQualifiedEFactoryInstanceAccessor());
    stringBuffer.append(TEXT_241);
    stringBuffer.append(createClass.getQualifiedClassifierAccessor());
    stringBuffer.append(TEXT_242);
    } else {
    stringBuffer.append(TEXT_243);
    stringBuffer.append(createClass.getGenPackage().getQualifiedFactoryInstanceAccessor());
    stringBuffer.append(TEXT_244);
    stringBuffer.append(createClass.getName());
    stringBuffer.append(TEXT_245);
    }
    //ItemProvider/newChildDescriptorsReferenceDelegatedFeature.override.javajetinc
    } else { GenDataType createDataType = (GenDataType)createClassifier;
    stringBuffer.append(TEXT_246);
    stringBuffer.append(createFeature.getQualifiedFeatureAccessor());
    stringBuffer.append(TEXT_247);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.FeatureMapUtil"));
    stringBuffer.append(TEXT_248);
    stringBuffer.append(delegatedFeature.getQualifiedFeatureAccessor());
    stringBuffer.append(TEXT_249);
    if (delegatedFeature.isEnumBasedType()) {
    stringBuffer.append(TEXT_250);
    stringBuffer.append(delegatedFeature.getTypeGenEnum().getStaticValue(delegatedFeature.getEcoreFeature().getDefaultValueLiteral()));
    stringBuffer.append(TEXT_251);
    } else if (delegatedFeature.isStringBasedType()) {
    stringBuffer.append(TEXT_252);
    stringBuffer.append(delegatedFeature.getCreateChildValueLiteral());
    stringBuffer.append(TEXT_253);
    stringBuffer.append(genModel.getNonNLS());
    } else { String literal = delegatedFeature.getCreateChildValueLiteral();
    stringBuffer.append(TEXT_254);
    stringBuffer.append(createDataType.getGenPackage().getQualifiedEFactoryInstanceAccessor());
    stringBuffer.append(TEXT_255);
    stringBuffer.append(createDataType.getQualifiedClassifierAccessor());
    stringBuffer.append(TEXT_256);
    stringBuffer.append(literal);
    stringBuffer.append(TEXT_257);
    if (literal != null) {
    stringBuffer.append(genModel.getNonNLS());
    } else {
    stringBuffer.append(TEXT_258);
    }
    }
    //ItemProvider/newChildDescriptorsAttributeDelegatedFeature.override.javajetinc
    }
    } else if (createFeature.isReferenceType()) { GenClass createClass = (GenClass)createClassifier;
    stringBuffer.append(TEXT_259);
    stringBuffer.append(createFeature.getQualifiedFeatureAccessor());
    stringBuffer.append(TEXT_260);
    if (createClass.isMapEntry()) { 
    stringBuffer.append(TEXT_261);
    stringBuffer.append(createClass.getGenPackage().getQualifiedEFactoryInstanceAccessor());
    stringBuffer.append(TEXT_262);
    stringBuffer.append(createClass.getQualifiedClassifierAccessor());
    stringBuffer.append(TEXT_263);
    } else {
    stringBuffer.append(TEXT_264);
    stringBuffer.append(createClass.getGenPackage().getQualifiedFactoryInstanceAccessor());
    stringBuffer.append(TEXT_265);
    stringBuffer.append(createClass.getName());
    stringBuffer.append(TEXT_266);
    }
    //ItemProvider/newChildDescriptorsReferenceFeature.override.javajetinc 
    } else { GenDataType createDataType = (GenDataType)createClassifier;
    stringBuffer.append(TEXT_267);
    stringBuffer.append(createFeature.getQualifiedFeatureAccessor());
    stringBuffer.append(TEXT_268);
    if (createFeature.isEnumBasedType()) {
    stringBuffer.append(TEXT_269);
    stringBuffer.append(createFeature.getTypeGenEnum().getStaticValue(createFeature.getEcoreFeature().getDefaultValueLiteral()));
    stringBuffer.append(TEXT_270);
    } else if (createFeature.isStringBasedType()) {
    stringBuffer.append(TEXT_271);
    stringBuffer.append(createFeature.getCreateChildValueLiteral());
    stringBuffer.append(TEXT_272);
    stringBuffer.append(genModel.getNonNLS());
    } else { String literal = createFeature.getCreateChildValueLiteral();
    stringBuffer.append(TEXT_273);
    stringBuffer.append(createDataType.getGenPackage().getQualifiedEFactoryInstanceAccessor());
    stringBuffer.append(TEXT_274);
    stringBuffer.append(createDataType.getQualifiedClassifierAccessor());
    stringBuffer.append(TEXT_275);
    stringBuffer.append(literal);
    stringBuffer.append(TEXT_276);
    if (literal != null) {
    stringBuffer.append(genModel.getNonNLS());
    } else {
    stringBuffer.append(TEXT_277);
    }
    }
    //ItemProvider/newChildDescriptorsAttributeFeature.override.javajetinc
    }
    }
    stringBuffer.append(TEXT_278);
    if (!genClass.getSharedClassCreateChildFeatures().isEmpty()) {
    stringBuffer.append(TEXT_279);
    if (genModel.useClassOverrideAnnotation()) {
    stringBuffer.append(TEXT_280);
    }
    stringBuffer.append(TEXT_281);
    stringBuffer.append(genModel.getImportedName(genModel.useGenerics() ? "java.util.Collection<?>" : "java.util.Collection"));
    stringBuffer.append(TEXT_282);
    if (genClass.hasFeatureMapCreateChildFeatures()) {
    stringBuffer.append(TEXT_283);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.EStructuralFeature"));
    stringBuffer.append(TEXT_284);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.FeatureMapUtil"));
    stringBuffer.append(TEXT_285);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.FeatureMap"));
    stringBuffer.append(TEXT_286);
    }
    stringBuffer.append(TEXT_287);
    for (Iterator<GenFeature> i = genClass.getSharedClassCreateChildFeatures().iterator(); i.hasNext();) { GenFeature createFeature = i.next();
    stringBuffer.append(TEXT_288);
    stringBuffer.append(createFeature.getQualifiedFeatureAccessor());
    stringBuffer.append(i.hasNext() ? " ||" : ";");
    }
    stringBuffer.append(TEXT_289);
    stringBuffer.append(genModel.getNonNLS());
    stringBuffer.append(TEXT_290);
    }
    }
    if (genClass.getProviderExtendsGenClass() == null || genClass.getProviderExtendsGenClass().getGenPackage() != genPackage && (!genPackage.isExtensibleProviderFactory() || genClass.getProviderExtendsGenClass().getGenPackage().isExtensibleProviderFactory() != genPackage.isExtensibleProviderFactory())) {
    stringBuffer.append(TEXT_291);
    if (genModel.useClassOverrideAnnotation()) {
    stringBuffer.append(TEXT_292);
    }
    stringBuffer.append(TEXT_293);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.util.ResourceLocator"));
    stringBuffer.append(TEXT_294);
    if (genPackage.isExtensibleProviderFactory()) {
    stringBuffer.append(TEXT_295);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.edit.provider.IChildCreationExtender"));
    stringBuffer.append(TEXT_296);
    } else {
    stringBuffer.append(TEXT_297);
    stringBuffer.append(genPackage.getImportedEditPluginClassName());
    stringBuffer.append(TEXT_298);
    }
    stringBuffer.append(TEXT_299);
    }
    stringBuffer.append(TEXT_300);
    genModel.emitSortedImports();
    stringBuffer.append(TEXT_301);
    return stringBuffer.toString();
  }
}
