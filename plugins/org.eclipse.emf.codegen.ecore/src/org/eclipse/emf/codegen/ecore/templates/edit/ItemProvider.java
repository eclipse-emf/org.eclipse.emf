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
  protected final String TEXT_7 = "} object." + NL + " * <!-- begin-user-doc -->" + NL + " * <!-- end-user-doc -->" + NL + " * @generated" + NL + " */" + NL + "public class ";
  protected final String TEXT_8 = " ";
  protected final String TEXT_9 = "extends ";
  protected final String TEXT_10 = NL + "\textends ";
  protected final String TEXT_11 = NL + "\timplements";
  protected final String TEXT_12 = NL + "\t\t";
  protected final String TEXT_13 = ",";
  protected final String TEXT_14 = NL + "{";
  protected final String TEXT_15 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */" + NL + "\tpublic static final ";
  protected final String TEXT_16 = " copyright = ";
  protected final String TEXT_17 = ";";
  protected final String TEXT_18 = NL;
  protected final String TEXT_19 = NL + "\t/**" + NL + "\t * This constructs an instance from a factory and a notifier." + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */" + NL + "\tpublic ";
  protected final String TEXT_20 = "(AdapterFactory adapterFactory)" + NL + "\t{" + NL + "\t\tsuper(adapterFactory);" + NL + "\t}" + NL + "" + NL + "\t/**" + NL + "\t * This returns the property descriptors for the adapted class." + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */";
  protected final String TEXT_21 = NL + "\t@Override";
  protected final String TEXT_22 = NL + "\tpublic ";
  protected final String TEXT_23 = " getPropertyDescriptors(Object object)" + NL + "\t{" + NL + "\t\tif (itemPropertyDescriptors == null)" + NL + "\t\t{" + NL + "\t\t\tsuper.getPropertyDescriptors(object);" + NL;
  protected final String TEXT_24 = NL + "\t\t\tadd";
  protected final String TEXT_25 = "PropertyDescriptor(object);";
  protected final String TEXT_26 = NL + "\t\t}" + NL + "\t\treturn itemPropertyDescriptors;" + NL + "\t}" + NL;
  protected final String TEXT_27 = NL + "\t/**" + NL + "\t * This adds a property descriptor for the ";
  protected final String TEXT_28 = " feature." + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */" + NL + "\tprotected void add";
  protected final String TEXT_29 = "PropertyDescriptor(Object object)" + NL + "\t{";
  protected final String TEXT_30 = NL + "\t\titemPropertyDescriptors.add" + NL + "\t\t\t(createItemPropertyDescriptor" + NL + "\t\t\t\t(((";
  protected final String TEXT_31 = ")adapterFactory).getRootAdapterFactory()," + NL + "\t\t\t\t getResourceLocator()," + NL + "\t\t\t\t getString(\"_UI_";
  protected final String TEXT_32 = "_";
  protected final String TEXT_33 = "_feature\"),";
  protected final String TEXT_34 = NL + "\t\t\t\t getString(\"_UI_PropertyDescriptor_description\", \"_UI_";
  protected final String TEXT_35 = "_";
  protected final String TEXT_36 = "_feature\", \"_UI_";
  protected final String TEXT_37 = "_type\"),";
  protected final String TEXT_38 = NL + "\t\t\t\t getString(\"_UI_";
  protected final String TEXT_39 = "_";
  protected final String TEXT_40 = "_description\"),";
  protected final String TEXT_41 = NL + "\t\t\t\t ";
  protected final String TEXT_42 = "," + NL + "\t\t\t\t ";
  protected final String TEXT_43 = "," + NL + "\t\t\t\t ";
  protected final String TEXT_44 = "," + NL + "\t\t\t\t ";
  protected final String TEXT_45 = ",";
  protected final String TEXT_46 = NL + "\t\t\t\t null,";
  protected final String TEXT_47 = NL + "\t\t\t\t ";
  protected final String TEXT_48 = ".";
  protected final String TEXT_49 = ",";
  protected final String TEXT_50 = NL + "\t\t\t\t null,";
  protected final String TEXT_51 = NL + "\t\t\t\t getString(\"";
  protected final String TEXT_52 = "\"),";
  protected final String TEXT_53 = NL + "\t\t\t\t null));";
  protected final String TEXT_54 = NL + "\t\t\t\t new String[] {";
  protected final String TEXT_55 = NL + "\t\t\t\t\t\"";
  protected final String TEXT_56 = "\"";
  protected final String TEXT_57 = ",";
  protected final String TEXT_58 = NL + "\t\t\t\t }));";
  protected final String TEXT_59 = NL + "\t}" + NL;
  protected final String TEXT_60 = NL + "\t/**" + NL + "\t * This specifies how to implement {@link #getChildren} and is used to deduce an appropriate feature for an" + NL + "\t * {@link org.eclipse.emf.edit.command.AddCommand}, {@link org.eclipse.emf.edit.command.RemoveCommand} or" + NL + "\t * {@link org.eclipse.emf.edit.command.MoveCommand} in {@link #createCommand}." + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */";
  protected final String TEXT_61 = NL + "\t@Override";
  protected final String TEXT_62 = NL + "\tpublic ";
  protected final String TEXT_63 = " getChildrenFeatures(Object object)" + NL + "\t{" + NL + "\t\tif (childrenFeatures == null)" + NL + "\t\t{" + NL + "\t\t\tsuper.getChildrenFeatures(object);";
  protected final String TEXT_64 = NL + "\t\t\tchildrenFeatures.add(";
  protected final String TEXT_65 = ");";
  protected final String TEXT_66 = NL + "\t\t}" + NL + "\t\treturn childrenFeatures;" + NL + "\t}" + NL;
  protected final String TEXT_67 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */";
  protected final String TEXT_68 = NL + "\t@Override";
  protected final String TEXT_69 = NL + "\tprotected ";
  protected final String TEXT_70 = " getChildFeature(Object object, Object child)" + NL + "\t{" + NL + "\t\t// Check the type of the specified child object and return the proper feature to use for" + NL + "\t\t// adding (see {@link AddCommand}) it as a child." + NL + "" + NL + "\t\treturn super.getChildFeature(object, child);" + NL + "\t}" + NL;
  protected final String TEXT_71 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */";
  protected final String TEXT_72 = NL + "\t@Override";
  protected final String TEXT_73 = NL + "\tpublic boolean hasChildren(Object object)" + NL + "\t{" + NL + "\t\treturn hasChildren(object, ";
  protected final String TEXT_74 = ");" + NL + "\t}" + NL;
  protected final String TEXT_75 = NL + "\t/**" + NL + "\t * This returns ";
  protected final String TEXT_76 = ".gif." + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */";
  protected final String TEXT_77 = NL + "\t@Override";
  protected final String TEXT_78 = NL + "\tpublic Object getImage(Object object)" + NL + "\t{" + NL + "\t\treturn overlayImage(object, getResourceLocator().getImage(\"full/obj16/";
  protected final String TEXT_79 = "\"));";
  protected final String TEXT_80 = NL + "\t}" + NL;
  protected final String TEXT_81 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */";
  protected final String TEXT_82 = NL + "\t@Override";
  protected final String TEXT_83 = NL + "\tprotected boolean shouldComposeCreationImage() " + NL + "\t{" + NL + "\t\treturn true;" + NL + "\t}" + NL;
  protected final String TEXT_84 = NL + "\t/**" + NL + "\t * This returns the label text for the adapted class." + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */";
  protected final String TEXT_85 = NL + "\t@Override";
  protected final String TEXT_86 = NL + "\tpublic String getText(Object object)" + NL + "\t{";
  protected final String TEXT_87 = NL + "\t\treturn ((";
  protected final String TEXT_88 = ")getStyledText(object)).getString();";
  protected final String TEXT_89 = NL + "\t\t";
  protected final String TEXT_90 = "<?, ?>";
  protected final String TEXT_91 = " ";
  protected final String TEXT_92 = " = (";
  protected final String TEXT_93 = "<?, ?>";
  protected final String TEXT_94 = ")object;";
  protected final String TEXT_95 = NL + "\t\treturn \"\" + ";
  protected final String TEXT_96 = ".getKey() + \" -> \" + ";
  protected final String TEXT_97 = ".getValue();";
  protected final String TEXT_98 = NL + "\t\tString key = crop(\"\" + ";
  protected final String TEXT_99 = ".getKey());";
  protected final String TEXT_100 = NL + "\t\tString key = \"\" + ";
  protected final String TEXT_101 = ".getKey();";
  protected final String TEXT_102 = NL + "\t\tString value = crop(\"\" + ";
  protected final String TEXT_103 = ".getValue());";
  protected final String TEXT_104 = NL + "\t\tString value = \"\" + ";
  protected final String TEXT_105 = ".getValue();";
  protected final String TEXT_106 = NL + "\t\treturn key + \" -> \" + value;";
  protected final String TEXT_107 = NL + "\t\t";
  protected final String TEXT_108 = " ";
  protected final String TEXT_109 = " = (";
  protected final String TEXT_110 = ")object;" + NL + "\t\treturn getString(\"_UI_";
  protected final String TEXT_111 = "_type\") + \" \" + ";
  protected final String TEXT_112 = ".";
  protected final String TEXT_113 = "();";
  protected final String TEXT_114 = NL + "\t\tString label = crop(((";
  protected final String TEXT_115 = ")object).";
  protected final String TEXT_116 = "());";
  protected final String TEXT_117 = NL + "\t\tString label = ((";
  protected final String TEXT_118 = ")object).";
  protected final String TEXT_119 = "();";
  protected final String TEXT_120 = NL + "\t\t";
  protected final String TEXT_121 = " labelValue = ((";
  protected final String TEXT_122 = ")object).eGet(";
  protected final String TEXT_123 = ");";
  protected final String TEXT_124 = NL + "\t\t";
  protected final String TEXT_125 = " labelValue = ((";
  protected final String TEXT_126 = ")object).";
  protected final String TEXT_127 = "();";
  protected final String TEXT_128 = NL + "\t\tString label = labelValue == null ? null : labelValue.toString();";
  protected final String TEXT_129 = NL + "\t\treturn label == null || label.length() == 0 ?" + NL + "\t\t\tgetString(\"_UI_";
  protected final String TEXT_130 = "_type\") :";
  protected final String TEXT_131 = NL + "\t\t\tgetString(\"_UI_";
  protected final String TEXT_132 = "_type\") + \" \" + label;";
  protected final String TEXT_133 = NL + "\t\treturn getString(\"_UI_";
  protected final String TEXT_134 = "_type\");";
  protected final String TEXT_135 = NL + "\t}" + NL + "\t";
  protected final String TEXT_136 = NL + "\t/**" + NL + "\t * This returns the label styled text for the adapted class." + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */";
  protected final String TEXT_137 = NL + "\t@Override";
  protected final String TEXT_138 = NL + "\tpublic Object getStyledText(Object object)" + NL + "\t{";
  protected final String TEXT_139 = NL + "\t\t";
  protected final String TEXT_140 = "<?, ?>";
  protected final String TEXT_141 = " ";
  protected final String TEXT_142 = " = (";
  protected final String TEXT_143 = "<?, ?>";
  protected final String TEXT_144 = ")object;";
  protected final String TEXT_145 = NL + "\t\treturn new ";
  protected final String TEXT_146 = "(\"\" + ";
  protected final String TEXT_147 = ".getKey()).append(\" -> \", ";
  protected final String TEXT_148 = ".QUALIFIER_STYLER).append(\"\" + ";
  protected final String TEXT_149 = ".getValue());";
  protected final String TEXT_150 = NL + "\t\tString key = crop(\"\" + ";
  protected final String TEXT_151 = ".getKey());";
  protected final String TEXT_152 = NL + "\t\tString key = \"\" + ";
  protected final String TEXT_153 = ".getKey();";
  protected final String TEXT_154 = NL + "\t\tString value = crop(\"\" + ";
  protected final String TEXT_155 = ".getValue());";
  protected final String TEXT_156 = NL + "\t\tString value = \"\" + ";
  protected final String TEXT_157 = ".getValue();";
  protected final String TEXT_158 = NL + "\t\treturn new ";
  protected final String TEXT_159 = "(key).append(\" -> \", ";
  protected final String TEXT_160 = ".QUALIFIER_STYLER).append(value);";
  protected final String TEXT_161 = NL + "\t\t";
  protected final String TEXT_162 = " ";
  protected final String TEXT_163 = " = (";
  protected final String TEXT_164 = ")object;" + NL + "\t\treturn new ";
  protected final String TEXT_165 = "(getString(\"_UI_";
  protected final String TEXT_166 = "_type\", ";
  protected final String TEXT_167 = ".QUALIFIER_STYLER).append(\" \").append(";
  protected final String TEXT_168 = ".";
  protected final String TEXT_169 = "());";
  protected final String TEXT_170 = NL + "\t\tString label = crop(((";
  protected final String TEXT_171 = ")object).";
  protected final String TEXT_172 = "());";
  protected final String TEXT_173 = NL + "\t\tString label = ((";
  protected final String TEXT_174 = ")object).";
  protected final String TEXT_175 = "();";
  protected final String TEXT_176 = NL + "\t\t";
  protected final String TEXT_177 = " labelValue = ((";
  protected final String TEXT_178 = ")object).eGet(";
  protected final String TEXT_179 = ");";
  protected final String TEXT_180 = NL + "\t\t";
  protected final String TEXT_181 = " labelValue = ((";
  protected final String TEXT_182 = ")object).";
  protected final String TEXT_183 = "();";
  protected final String TEXT_184 = NL + "\t\tString label = labelValue == null ? null : labelValue.toString();";
  protected final String TEXT_185 = NL + "    \t";
  protected final String TEXT_186 = " styledLabel = new ";
  protected final String TEXT_187 = "();" + NL + "\t\tif (label == null || label.length() == 0) " + NL + "\t\t{" + NL + "\t\t\tstyledLabel.append(getString(\"_UI_";
  protected final String TEXT_188 = "_type\"), ";
  protected final String TEXT_189 = ".QUALIFIER_STYLER); ";
  protected final String TEXT_190 = NL + "\t\t} else {" + NL + "\t\t\tstyledLabel.append(getString(\"_UI_";
  protected final String TEXT_191 = "_type\"), ";
  protected final String TEXT_192 = ".QUALIFIER_STYLER).append(\" \" + label);";
  protected final String TEXT_193 = NL + "\t\t}" + NL + "\t\treturn styledLabel;";
  protected final String TEXT_194 = NL + "\t\treturn new ";
  protected final String TEXT_195 = "(getString(\"_UI_";
  protected final String TEXT_196 = "_type\"));";
  protected final String TEXT_197 = NL + "\t}\t";
  protected final String TEXT_198 = NL + NL + "\t/**" + NL + "\t * This handles model notifications by calling {@link #updateChildren} to update any cached" + NL + "\t * children and by creating a viewer notification, which it passes to {@link #fireNotifyChanged}." + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */";
  protected final String TEXT_199 = NL + "\t@Override";
  protected final String TEXT_200 = NL + "\tpublic void notifyChanged(Notification notification)" + NL + "\t{" + NL + "\t\tupdateChildren(notification);";
  protected final String TEXT_201 = NL + NL + "\t\tswitch (notification.getFeatureID(";
  protected final String TEXT_202 = ".class))" + NL + "\t\t{";
  protected final String TEXT_203 = NL + "\t\t\tcase ";
  protected final String TEXT_204 = ":";
  protected final String TEXT_205 = NL + "\t\t\t\tfireNotifyChanged(new ";
  protected final String TEXT_206 = "(notification, notification.getNotifier(), false, true));" + NL + "\t\t\t\treturn;";
  protected final String TEXT_207 = NL + "\t\t\tcase ";
  protected final String TEXT_208 = ":";
  protected final String TEXT_209 = NL + "\t\t\t\tfireNotifyChanged(new ";
  protected final String TEXT_210 = "(notification, notification.getNotifier(), true, false));" + NL + "\t\t\t\treturn;";
  protected final String TEXT_211 = NL + "\t\t\tcase ";
  protected final String TEXT_212 = ":";
  protected final String TEXT_213 = NL + "\t\t\t\tfireNotifyChanged(new ";
  protected final String TEXT_214 = "(notification, notification.getNotifier(), true, true));" + NL + "\t\t\t\treturn;";
  protected final String TEXT_215 = NL + "\t\t}";
  protected final String TEXT_216 = NL + "\t\tsuper.notifyChanged(notification);" + NL + "\t}" + NL;
  protected final String TEXT_217 = NL + "\t/**" + NL + "\t * This adds {@link org.eclipse.emf.edit.command.CommandParameter}s describing the children" + NL + "\t * that can be created under this object." + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */";
  protected final String TEXT_218 = NL + "\t@Override";
  protected final String TEXT_219 = NL + "\tprotected void collectNewChildDescriptors(";
  protected final String TEXT_220 = " newChildDescriptors, Object object)" + NL + "\t{" + NL + "\t\tsuper.collectNewChildDescriptors(newChildDescriptors, object);";
  protected final String TEXT_221 = NL + NL + "\t\tnewChildDescriptors.add" + NL + "\t\t\t(createChildParameter" + NL + "\t\t\t\t(";
  protected final String TEXT_222 = "," + NL + "\t\t\t\t ";
  protected final String TEXT_223 = ".createEntry" + NL + "\t\t\t\t\t(";
  protected final String TEXT_224 = ",";
  protected final String TEXT_225 = NL + "\t\t\t\t\t ";
  protected final String TEXT_226 = ".create(";
  protected final String TEXT_227 = "))));";
  protected final String TEXT_228 = NL + "\t\t\t\t\t ";
  protected final String TEXT_229 = ".create";
  protected final String TEXT_230 = "())));";
  protected final String TEXT_231 = NL + NL + "\t\tnewChildDescriptors.add" + NL + "\t\t\t(createChildParameter" + NL + "\t\t\t\t(";
  protected final String TEXT_232 = "," + NL + "\t\t\t\t ";
  protected final String TEXT_233 = ".createEntry" + NL + "\t\t\t\t\t(";
  protected final String TEXT_234 = ",";
  protected final String TEXT_235 = NL + "\t\t\t\t\t ";
  protected final String TEXT_236 = ")));";
  protected final String TEXT_237 = NL + "\t\t\t\t\t ";
  protected final String TEXT_238 = ")));";
  protected final String TEXT_239 = NL + "\t\t\t\t\t ";
  protected final String TEXT_240 = ".createFromString(";
  protected final String TEXT_241 = ", ";
  protected final String TEXT_242 = "))));";
  protected final String TEXT_243 = " // TODO: ensure this is a valid literal value";
  protected final String TEXT_244 = NL + NL + "\t\tnewChildDescriptors.add" + NL + "\t\t\t(createChildParameter" + NL + "\t\t\t\t(";
  protected final String TEXT_245 = ",";
  protected final String TEXT_246 = NL + "\t\t\t\t ";
  protected final String TEXT_247 = ".create(";
  protected final String TEXT_248 = ")));";
  protected final String TEXT_249 = NL + "\t\t\t\t ";
  protected final String TEXT_250 = ".create";
  protected final String TEXT_251 = "()));";
  protected final String TEXT_252 = NL + NL + "\t\tnewChildDescriptors.add" + NL + "\t\t\t(createChildParameter" + NL + "\t\t\t\t(";
  protected final String TEXT_253 = ",";
  protected final String TEXT_254 = NL + "\t\t\t\t ";
  protected final String TEXT_255 = "));";
  protected final String TEXT_256 = NL + "\t\t\t\t ";
  protected final String TEXT_257 = "));";
  protected final String TEXT_258 = NL + "\t\t\t\t ";
  protected final String TEXT_259 = ".createFromString(";
  protected final String TEXT_260 = ", ";
  protected final String TEXT_261 = ")));";
  protected final String TEXT_262 = " // TODO: ensure this is a valid literal value";
  protected final String TEXT_263 = NL + "\t}" + NL;
  protected final String TEXT_264 = NL + "\t/**" + NL + "\t * This returns the label text for {@link org.eclipse.emf.edit.command.CreateChildCommand}." + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */";
  protected final String TEXT_265 = NL + "\t@Override";
  protected final String TEXT_266 = NL + "\tpublic String getCreateChildText(Object owner, Object feature, Object child, ";
  protected final String TEXT_267 = " selection)" + NL + "\t{" + NL + "\t\tObject childFeature = feature;" + NL + "\t\tObject childObject = child;" + NL;
  protected final String TEXT_268 = NL + "\t\tif (childFeature instanceof ";
  protected final String TEXT_269 = " && ";
  protected final String TEXT_270 = ".isFeatureMap((EStructuralFeature)childFeature))" + NL + "\t\t{" + NL + "\t\t\t";
  protected final String TEXT_271 = ".Entry entry = (FeatureMap.Entry)childObject;" + NL + "\t\t\tchildFeature = entry.getEStructuralFeature();" + NL + "\t\t\tchildObject = entry.getValue();" + NL + "\t\t}" + NL;
  protected final String TEXT_272 = NL + "\t\tboolean qualify =";
  protected final String TEXT_273 = NL + "\t\t\tchildFeature == ";
  protected final String TEXT_274 = NL + NL + "\t\tif (qualify)" + NL + "\t\t{" + NL + "\t\t\treturn getString" + NL + "\t\t\t\t(\"_UI_CreateChild_text2\",";
  protected final String TEXT_275 = NL + "\t\t\t\t new Object[] { getTypeText(childObject), getFeatureText(childFeature), getTypeText(owner) });" + NL + "\t\t}" + NL + "\t\treturn super.getCreateChildText(owner, feature, child, selection);" + NL + "\t}" + NL;
  protected final String TEXT_276 = NL + "\t/**" + NL + "\t * Return the resource locator for this item provider's resources." + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */";
  protected final String TEXT_277 = NL + "\t@Override";
  protected final String TEXT_278 = NL + "\tpublic ";
  protected final String TEXT_279 = " getResourceLocator()" + NL + "\t{";
  protected final String TEXT_280 = NL + "\t\treturn ((";
  protected final String TEXT_281 = ")adapterFactory).getResourceLocator();";
  protected final String TEXT_282 = NL + "\t\treturn ";
  protected final String TEXT_283 = ".INSTANCE;";
  protected final String TEXT_284 = NL + "\t}" + NL;
  protected final String TEXT_285 = NL + "}";
  protected final String TEXT_286 = NL;

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
    genModel.addImport("org.eclipse.emf.edit.provider.IEditingDomainItemProvider");
    genModel.addImport("org.eclipse.emf.edit.provider.IItemLabelProvider");
    genModel.addImport("org.eclipse.emf.edit.provider.IItemPropertySource");
    genModel.addImport("org.eclipse.emf.edit.provider.IStructuredItemContentProvider");
    genModel.addImport("org.eclipse.emf.edit.provider.ITreeItemContentProvider");
    String _List = genModel.getImportedName(genModel.useGenerics() ? "java.util.List<org.eclipse.emf.edit.provider.IItemPropertyDescriptor>" : "java.util.List");
    genModel.markImportLocation(stringBuffer);
    stringBuffer.append(TEXT_6);
    stringBuffer.append(genClass.getQualifiedInterfaceName());
    stringBuffer.append(TEXT_7);
    stringBuffer.append(genClass.getProviderClassName());
    stringBuffer.append(TEXT_8);
    if (genClass.getProviderImplementsClassNames().isEmpty()) {
    stringBuffer.append(TEXT_9);
    stringBuffer.append(genClass.getProviderBaseClassName() != null ? genClass.getProviderBaseClassName() : genModel.getImportedName("org.eclipse.emf.edit.provider.ItemProviderAdapter"));
    }
    if (!genClass.getProviderImplementsClassNames().isEmpty()) {
    stringBuffer.append(TEXT_10);
    stringBuffer.append(genClass.getProviderBaseClassName() != null ? genClass.getProviderBaseClassName() : genModel.getImportedName("org.eclipse.emf.edit.provider.ItemProviderAdapter"));
    stringBuffer.append(TEXT_11);
    for (Iterator<String> i = genClass.getProviderImplementsClassNames().iterator(); i.hasNext(); ) {
    stringBuffer.append(TEXT_12);
    stringBuffer.append(genModel.getImportedName(i.next()));
    if (i.hasNext()){
    stringBuffer.append(TEXT_13);
    }
    }
    }
    stringBuffer.append(TEXT_14);
    if (genModel.hasCopyrightField()) {
    stringBuffer.append(TEXT_15);
    stringBuffer.append(genModel.getImportedName("java.lang.String"));
    stringBuffer.append(TEXT_16);
    stringBuffer.append(genModel.getCopyrightFieldLiteral());
    stringBuffer.append(TEXT_17);
    stringBuffer.append(genModel.getNonNLS());
    stringBuffer.append(TEXT_18);
    }
    stringBuffer.append(TEXT_19);
    stringBuffer.append(genClass.getProviderClassName());
    stringBuffer.append(TEXT_20);
    if (genModel.useClassOverrideAnnotation()) {
    stringBuffer.append(TEXT_21);
    }
    stringBuffer.append(TEXT_22);
    stringBuffer.append(_List);
    stringBuffer.append(TEXT_23);
    for (GenFeature genFeature : genClass.getPropertyFeatures()) { 
    stringBuffer.append(TEXT_24);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_25);
    }
    stringBuffer.append(TEXT_26);
    for (GenFeature genFeature : genClass.getPropertyFeatures()) { 
    stringBuffer.append(TEXT_27);
    stringBuffer.append(genFeature.getFormattedName());
    stringBuffer.append(TEXT_28);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_29);
    stringBuffer.append(TEXT_30);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.edit.provider.ComposeableAdapterFactory"));
    stringBuffer.append(TEXT_31);
    stringBuffer.append(genFeature.getGenClass().getName());
    stringBuffer.append(TEXT_32);
    stringBuffer.append(genFeature.getName());
    stringBuffer.append(TEXT_33);
    stringBuffer.append(genModel.getNonNLS());
    if (genFeature.getPropertyDescription() == null || genFeature.getPropertyDescription().length() == 0) {
    stringBuffer.append(TEXT_34);
    stringBuffer.append(genFeature.getGenClass().getName());
    stringBuffer.append(TEXT_35);
    stringBuffer.append(genFeature.getName());
    stringBuffer.append(TEXT_36);
    stringBuffer.append(genFeature.getGenClass().getName());
    stringBuffer.append(TEXT_37);
    stringBuffer.append(genModel.getNonNLS());
    stringBuffer.append(genModel.getNonNLS(2));
    stringBuffer.append(genModel.getNonNLS(3));
    } else {
    stringBuffer.append(TEXT_38);
    stringBuffer.append(genFeature.getGenClass().getName());
    stringBuffer.append(TEXT_39);
    stringBuffer.append(genFeature.getName());
    stringBuffer.append(TEXT_40);
    stringBuffer.append(genModel.getNonNLS());
    }
    stringBuffer.append(TEXT_41);
    stringBuffer.append(genFeature.getQualifiedFeatureAccessor());
    stringBuffer.append(TEXT_42);
    stringBuffer.append(genFeature.getProperty() == GenPropertyKind.EDITABLE_LITERAL ? "true" : "false");
    stringBuffer.append(TEXT_43);
    stringBuffer.append(genFeature.isPropertyMultiLine() ? "true" : "false");
    stringBuffer.append(TEXT_44);
    stringBuffer.append(genFeature.isPropertySortChoices() ? "true" : "false");
    stringBuffer.append(TEXT_45);
    if (genFeature.isReferenceType()) {
    stringBuffer.append(TEXT_46);
    } else {
    stringBuffer.append(TEXT_47);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.edit.provider.ItemPropertyDescriptor"));
    stringBuffer.append(TEXT_48);
    stringBuffer.append(genFeature.getPropertyImageName());
    stringBuffer.append(TEXT_49);
    }
    if (genFeature.getPropertyCategory() == null || genFeature.getPropertyCategory().length() == 0) {
    stringBuffer.append(TEXT_50);
    } else {
    stringBuffer.append(TEXT_51);
    stringBuffer.append(genModel.getPropertyCategoryKey(genFeature.getPropertyCategory()));
    stringBuffer.append(TEXT_52);
    stringBuffer.append(genModel.getNonNLS());
    }
    if (genFeature.getPropertyFilterFlags().isEmpty()) {
    stringBuffer.append(TEXT_53);
    } else {
    stringBuffer.append(TEXT_54);
    for (Iterator<String> j = genFeature.getPropertyFilterFlags().iterator(); j.hasNext();) { String filterFlag = j.next();
    if (filterFlag != null && filterFlag.length() > 0) {
    stringBuffer.append(TEXT_55);
    stringBuffer.append(filterFlag);
    stringBuffer.append(TEXT_56);
    if (j.hasNext()) {
    stringBuffer.append(TEXT_57);
    }
    stringBuffer.append(genModel.getNonNLS());
    }
    }
    stringBuffer.append(TEXT_58);
    }
    //ItemProvider/addPropertyDescriptor.override.javajetinc
    stringBuffer.append(TEXT_59);
    }
    if (!genClass.getChildrenFeatures().isEmpty()) {
    stringBuffer.append(TEXT_60);
    if (genModel.useClassOverrideAnnotation()) {
    stringBuffer.append(TEXT_61);
    }
    stringBuffer.append(TEXT_62);
    stringBuffer.append(genModel.getImportedName(genModel.useGenerics() ? "java.util.Collection<? extends org.eclipse.emf.ecore.EStructuralFeature>" : "java.util.Collection"));
    stringBuffer.append(TEXT_63);
    for (GenFeature genFeature : genClass.getChildrenFeatures()) { 
    stringBuffer.append(TEXT_64);
    stringBuffer.append(genFeature.getQualifiedFeatureAccessor());
    stringBuffer.append(TEXT_65);
    }
    stringBuffer.append(TEXT_66);
    if (!genClass.getChildrenFeatures().isEmpty()) {
    stringBuffer.append(TEXT_67);
    if (genModel.useClassOverrideAnnotation()) {
    stringBuffer.append(TEXT_68);
    }
    stringBuffer.append(TEXT_69);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.EStructuralFeature"));
    stringBuffer.append(TEXT_70);
    }
    }
    if (genClass.needsHasChildrenMethodOverride()) {
    stringBuffer.append(TEXT_71);
    if (genModel.useClassOverrideAnnotation()) {
    stringBuffer.append(TEXT_72);
    }
    stringBuffer.append(TEXT_73);
    stringBuffer.append(genModel.isOptimizedHasChildren());
    stringBuffer.append(TEXT_74);
    }
    if (genClass.isImage()) {
    stringBuffer.append(TEXT_75);
    stringBuffer.append(genClass.getName());
    stringBuffer.append(TEXT_76);
    if (genModel.useClassOverrideAnnotation()) {
    stringBuffer.append(TEXT_77);
    }
    stringBuffer.append(TEXT_78);
    stringBuffer.append(genClass.getName());
    stringBuffer.append(TEXT_79);
    stringBuffer.append(genModel.getNonNLS());
    stringBuffer.append(TEXT_80);
    }
    if (genModel.getRuntimeVersion().getValue() >= GenRuntimeVersion.EMF26_VALUE && !genModel.isCreationIcons()) {
    stringBuffer.append(TEXT_81);
    if (genModel.useClassOverrideAnnotation()) {
    stringBuffer.append(TEXT_82);
    }
    stringBuffer.append(TEXT_83);
    }
    stringBuffer.append(TEXT_84);
    if (genModel.useClassOverrideAnnotation()) {
    stringBuffer.append(TEXT_85);
    }
    stringBuffer.append(TEXT_86);
    if (genModel.isStyleProviders()) {
    stringBuffer.append(TEXT_87);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.edit.provider.StyledString"));
    stringBuffer.append(TEXT_88);
    } else {
    if (genClass.isMapEntry()) {
    stringBuffer.append(TEXT_89);
    stringBuffer.append(genClass.getImportedInterfaceName());
    if (genModel.useGenerics()) {
    stringBuffer.append(TEXT_90);
    }
    stringBuffer.append(TEXT_91);
    stringBuffer.append(genClass.getSafeUncapName());
    stringBuffer.append(TEXT_92);
    stringBuffer.append(genClass.getImportedInterfaceName());
    if (genModel.useGenerics()) {
    stringBuffer.append(TEXT_93);
    }
    stringBuffer.append(TEXT_94);
    if (!genClass.getMapEntryKeyFeature().isPropertyMultiLine() && !genClass.getMapEntryValueFeature().isPropertyMultiLine()) {
    stringBuffer.append(TEXT_95);
    stringBuffer.append(genClass.getSafeUncapName());
    stringBuffer.append(TEXT_96);
    stringBuffer.append(genClass.getSafeUncapName());
    stringBuffer.append(TEXT_97);
    stringBuffer.append(genModel.getNonNLS());
    stringBuffer.append(genModel.getNonNLS(2));
    } else {
    if (genClass.getMapEntryKeyFeature().isPropertyMultiLine()) {
    stringBuffer.append(TEXT_98);
    stringBuffer.append(genClass.getSafeUncapName());
    stringBuffer.append(TEXT_99);
    stringBuffer.append(genModel.getNonNLS());
    } else {
    stringBuffer.append(TEXT_100);
    stringBuffer.append(genClass.getSafeUncapName());
    stringBuffer.append(TEXT_101);
    stringBuffer.append(genModel.getNonNLS());
    }
    if (genClass.getMapEntryValueFeature().isPropertyMultiLine()) {
    stringBuffer.append(TEXT_102);
    stringBuffer.append(genClass.getSafeUncapName());
    stringBuffer.append(TEXT_103);
    stringBuffer.append(genModel.getNonNLS());
    } else {
    stringBuffer.append(TEXT_104);
    stringBuffer.append(genClass.getSafeUncapName());
    stringBuffer.append(TEXT_105);
    stringBuffer.append(genModel.getNonNLS());
    }
    stringBuffer.append(TEXT_106);
    stringBuffer.append(genModel.getNonNLS());
    }
    } else if (genClass.getLabelFeature() != null) { GenFeature labelFeature = genClass.getLabelFeature();
    if (labelFeature.isPrimitiveType() && !labelFeature.getGenClass().isDynamic() && !labelFeature.isSuppressedGetVisibility()) {
    stringBuffer.append(TEXT_107);
    stringBuffer.append(genClass.getImportedInterfaceName());
    stringBuffer.append(genClass.getInterfaceWildTypeArguments());
    stringBuffer.append(TEXT_108);
    stringBuffer.append(genClass.getSafeUncapName());
    stringBuffer.append(TEXT_109);
    stringBuffer.append(genClass.getImportedInterfaceName());
    stringBuffer.append(genClass.getInterfaceWildTypeArguments());
    stringBuffer.append(TEXT_110);
    stringBuffer.append(genClass.getName());
    stringBuffer.append(TEXT_111);
    stringBuffer.append(genClass.getSafeUncapName());
    stringBuffer.append(TEXT_112);
    stringBuffer.append(genClass.getLabelFeature().getGetAccessor());
    stringBuffer.append(TEXT_113);
    stringBuffer.append(genModel.getNonNLS());
    stringBuffer.append(genModel.getNonNLS(2));
    } else {
    if (labelFeature.isStringType() && !labelFeature.getGenClass().isDynamic() && !labelFeature.isSuppressedGetVisibility()) {
    if (labelFeature.isPropertyMultiLine()) {
    stringBuffer.append(TEXT_114);
    stringBuffer.append(genClass.getImportedInterfaceName());
    stringBuffer.append(genClass.getInterfaceWildTypeArguments());
    stringBuffer.append(TEXT_115);
    stringBuffer.append(labelFeature.getGetAccessor());
    stringBuffer.append(TEXT_116);
    } else {
    stringBuffer.append(TEXT_117);
    stringBuffer.append(genClass.getImportedInterfaceName());
    stringBuffer.append(genClass.getInterfaceWildTypeArguments());
    stringBuffer.append(TEXT_118);
    stringBuffer.append(labelFeature.getGetAccessor());
    stringBuffer.append(TEXT_119);
    }
    } else {
    if (labelFeature.isSuppressedGetVisibility() || labelFeature.getGenClass().isDynamic()) {
    stringBuffer.append(TEXT_120);
    stringBuffer.append(genModel.getImportedName("java.lang.Object"));
    stringBuffer.append(TEXT_121);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.EObject"));
    stringBuffer.append(TEXT_122);
    stringBuffer.append(labelFeature.getQualifiedFeatureAccessor());
    stringBuffer.append(TEXT_123);
    } else {
    stringBuffer.append(TEXT_124);
    stringBuffer.append(labelFeature.getRawImportedType());
    stringBuffer.append(TEXT_125);
    stringBuffer.append(genClass.getImportedInterfaceName());
    stringBuffer.append(genClass.getInterfaceWildTypeArguments());
    stringBuffer.append(TEXT_126);
    stringBuffer.append(labelFeature.getGetAccessor());
    stringBuffer.append(TEXT_127);
    }
    stringBuffer.append(TEXT_128);
    }
    stringBuffer.append(TEXT_129);
    stringBuffer.append(genClass.getName());
    stringBuffer.append(TEXT_130);
    stringBuffer.append(genModel.getNonNLS());
    stringBuffer.append(TEXT_131);
    stringBuffer.append(genClass.getName());
    stringBuffer.append(TEXT_132);
    stringBuffer.append(genModel.getNonNLS());
    stringBuffer.append(genModel.getNonNLS(2));
    }
    } else {
    stringBuffer.append(TEXT_133);
    stringBuffer.append(genClass.getName());
    stringBuffer.append(TEXT_134);
    stringBuffer.append(genModel.getNonNLS());
    }
    }
    //ItemProvider/getText.override.javajetinc
    stringBuffer.append(TEXT_135);
    if (genModel.isStyleProviders()) {
    stringBuffer.append(TEXT_136);
    if (genModel.useClassOverrideAnnotation()) {
    stringBuffer.append(TEXT_137);
    }
    stringBuffer.append(TEXT_138);
    if (genClass.isMapEntry()) {
    stringBuffer.append(TEXT_139);
    stringBuffer.append(genClass.getImportedInterfaceName());
    if (genModel.useGenerics()) {
    stringBuffer.append(TEXT_140);
    }
    stringBuffer.append(TEXT_141);
    stringBuffer.append(genClass.getSafeUncapName());
    stringBuffer.append(TEXT_142);
    stringBuffer.append(genClass.getImportedInterfaceName());
    if (genModel.useGenerics()) {
    stringBuffer.append(TEXT_143);
    }
    stringBuffer.append(TEXT_144);
    if (!genClass.getMapEntryKeyFeature().isPropertyMultiLine() && !genClass.getMapEntryValueFeature().isPropertyMultiLine()) {
    stringBuffer.append(TEXT_145);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.edit.provider.StyledString"));
    stringBuffer.append(TEXT_146);
    stringBuffer.append(genClass.getSafeUncapName());
    stringBuffer.append(TEXT_147);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.edit.provider.StyledString$Style"));
    stringBuffer.append(TEXT_148);
    stringBuffer.append(genClass.getSafeUncapName());
    stringBuffer.append(TEXT_149);
    stringBuffer.append(genModel.getNonNLS());
    stringBuffer.append(genModel.getNonNLS(2));
    } else {
    if (genClass.getMapEntryKeyFeature().isPropertyMultiLine()) {
    stringBuffer.append(TEXT_150);
    stringBuffer.append(genClass.getSafeUncapName());
    stringBuffer.append(TEXT_151);
    stringBuffer.append(genModel.getNonNLS());
    } else {
    stringBuffer.append(TEXT_152);
    stringBuffer.append(genClass.getSafeUncapName());
    stringBuffer.append(TEXT_153);
    stringBuffer.append(genModel.getNonNLS());
    }
    if (genClass.getMapEntryValueFeature().isPropertyMultiLine()) {
    stringBuffer.append(TEXT_154);
    stringBuffer.append(genClass.getSafeUncapName());
    stringBuffer.append(TEXT_155);
    stringBuffer.append(genModel.getNonNLS());
    } else {
    stringBuffer.append(TEXT_156);
    stringBuffer.append(genClass.getSafeUncapName());
    stringBuffer.append(TEXT_157);
    stringBuffer.append(genModel.getNonNLS());
    }
    stringBuffer.append(TEXT_158);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.edit.provider.StyledString"));
    stringBuffer.append(TEXT_159);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.edit.provider.StyledString$Style"));
    stringBuffer.append(TEXT_160);
    stringBuffer.append(genModel.getNonNLS());
    }
    } else if (genClass.getLabelFeature() != null) { GenFeature labelFeature = genClass.getLabelFeature();
    if (labelFeature.isPrimitiveType() && !labelFeature.getGenClass().isDynamic() && !labelFeature.isSuppressedGetVisibility()) {
    stringBuffer.append(TEXT_161);
    stringBuffer.append(genClass.getImportedInterfaceName());
    stringBuffer.append(genClass.getInterfaceWildTypeArguments());
    stringBuffer.append(TEXT_162);
    stringBuffer.append(genClass.getSafeUncapName());
    stringBuffer.append(TEXT_163);
    stringBuffer.append(genClass.getImportedInterfaceName());
    stringBuffer.append(genClass.getInterfaceWildTypeArguments());
    stringBuffer.append(TEXT_164);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.edit.provider.StyledString"));
    stringBuffer.append(TEXT_165);
    stringBuffer.append(genClass.getName());
    stringBuffer.append(TEXT_166);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.edit.provider.StyledString$Style"));
    stringBuffer.append(TEXT_167);
    stringBuffer.append(genClass.getSafeUncapName());
    stringBuffer.append(TEXT_168);
    stringBuffer.append(genClass.getLabelFeature().getGetAccessor());
    stringBuffer.append(TEXT_169);
    stringBuffer.append(genModel.getNonNLS());
    stringBuffer.append(genModel.getNonNLS(2));
    } else {
    if (labelFeature.isStringType() && !labelFeature.getGenClass().isDynamic() && !labelFeature.isSuppressedGetVisibility()) {
    if (labelFeature.isPropertyMultiLine()) {
    stringBuffer.append(TEXT_170);
    stringBuffer.append(genClass.getImportedInterfaceName());
    stringBuffer.append(genClass.getInterfaceWildTypeArguments());
    stringBuffer.append(TEXT_171);
    stringBuffer.append(labelFeature.getGetAccessor());
    stringBuffer.append(TEXT_172);
    } else {
    stringBuffer.append(TEXT_173);
    stringBuffer.append(genClass.getImportedInterfaceName());
    stringBuffer.append(genClass.getInterfaceWildTypeArguments());
    stringBuffer.append(TEXT_174);
    stringBuffer.append(labelFeature.getGetAccessor());
    stringBuffer.append(TEXT_175);
    }
    } else {
    if (labelFeature.isSuppressedGetVisibility() || labelFeature.getGenClass().isDynamic()) {
    stringBuffer.append(TEXT_176);
    stringBuffer.append(genModel.getImportedName("java.lang.Object"));
    stringBuffer.append(TEXT_177);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.EObject"));
    stringBuffer.append(TEXT_178);
    stringBuffer.append(labelFeature.getQualifiedFeatureAccessor());
    stringBuffer.append(TEXT_179);
    } else {
    stringBuffer.append(TEXT_180);
    stringBuffer.append(labelFeature.getRawImportedType());
    stringBuffer.append(TEXT_181);
    stringBuffer.append(genClass.getImportedInterfaceName());
    stringBuffer.append(genClass.getInterfaceWildTypeArguments());
    stringBuffer.append(TEXT_182);
    stringBuffer.append(labelFeature.getGetAccessor());
    stringBuffer.append(TEXT_183);
    }
    stringBuffer.append(TEXT_184);
    }
    stringBuffer.append(TEXT_185);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.edit.provider.StyledString"));
    stringBuffer.append(TEXT_186);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.edit.provider.StyledString"));
    stringBuffer.append(TEXT_187);
    stringBuffer.append(genClass.getName());
    stringBuffer.append(TEXT_188);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.edit.provider.StyledString$Style"));
    stringBuffer.append(TEXT_189);
    stringBuffer.append(genModel.getNonNLS());
    stringBuffer.append(TEXT_190);
    stringBuffer.append(genClass.getName());
    stringBuffer.append(TEXT_191);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.edit.provider.StyledString$Style"));
    stringBuffer.append(TEXT_192);
    stringBuffer.append(genModel.getNonNLS());
    stringBuffer.append(genModel.getNonNLS(2));
    stringBuffer.append(TEXT_193);
    }
    } else {
    stringBuffer.append(TEXT_194);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.edit.provider.StyledString"));
    stringBuffer.append(TEXT_195);
    stringBuffer.append(genClass.getName());
    stringBuffer.append(TEXT_196);
    stringBuffer.append(genModel.getNonNLS());
    }
    //ItemProvider/getStyledText.override.javajetinc
    stringBuffer.append(TEXT_197);
    }
    stringBuffer.append(TEXT_198);
    if (genModel.useClassOverrideAnnotation()) {
    stringBuffer.append(TEXT_199);
    }
    stringBuffer.append(TEXT_200);
    if (!genClass.getLabelNotifyFeatures().isEmpty() || !genClass.getContentNotifyFeatures().isEmpty() || !genClass.getLabelAndContentNotifyFeatures().isEmpty()) {
    stringBuffer.append(TEXT_201);
    stringBuffer.append(genClass.getRawImportedInterfaceName());
    stringBuffer.append(TEXT_202);
    if (!genClass.getLabelNotifyFeatures().isEmpty()) {
    for (GenFeature genFeature : genClass.getLabelNotifyFeatures()) { 
    stringBuffer.append(TEXT_203);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(TEXT_204);
    }
    stringBuffer.append(TEXT_205);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.edit.provider.ViewerNotification"));
    stringBuffer.append(TEXT_206);
    }
    if (!genClass.getContentNotifyFeatures().isEmpty()) {
    for (GenFeature genFeature : genClass.getContentNotifyFeatures()) { 
    stringBuffer.append(TEXT_207);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(TEXT_208);
    }
    stringBuffer.append(TEXT_209);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.edit.provider.ViewerNotification"));
    stringBuffer.append(TEXT_210);
    }
    if (!genClass.getLabelAndContentNotifyFeatures().isEmpty()) {
    for (GenFeature genFeature : genClass.getLabelAndContentNotifyFeatures()) { 
    stringBuffer.append(TEXT_211);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(TEXT_212);
    }
    stringBuffer.append(TEXT_213);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.edit.provider.ViewerNotification"));
    stringBuffer.append(TEXT_214);
    }
    stringBuffer.append(TEXT_215);
    }
    stringBuffer.append(TEXT_216);
    if (genModel.isCreationCommands()) {
    stringBuffer.append(TEXT_217);
    if (genModel.useClassOverrideAnnotation()) {
    stringBuffer.append(TEXT_218);
    }
    stringBuffer.append(TEXT_219);
    stringBuffer.append(genModel.getImportedName(genModel.useGenerics() ? "java.util.Collection<java.lang.Object>" : "java.util.Collection"));
    stringBuffer.append(TEXT_220);
    for (GenClass.ChildCreationData childCreationData : genClass.getChildCreationData()) { GenFeature createFeature = childCreationData.createFeature; GenFeature delegatedFeature = childCreationData.delegatedFeature; GenClassifier createClassifier = childCreationData.createClassifier;
    if (createFeature.isFeatureMapType()) {
    if (delegatedFeature.isReferenceType()) { GenClass createClass = (GenClass)createClassifier;
    stringBuffer.append(TEXT_221);
    stringBuffer.append(createFeature.getQualifiedFeatureAccessor());
    stringBuffer.append(TEXT_222);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.FeatureMapUtil"));
    stringBuffer.append(TEXT_223);
    stringBuffer.append(delegatedFeature.getQualifiedFeatureAccessor());
    stringBuffer.append(TEXT_224);
    if (createClass.isMapEntry()) { 
    stringBuffer.append(TEXT_225);
    stringBuffer.append(createClass.getGenPackage().getQualifiedEFactoryInstanceAccessor());
    stringBuffer.append(TEXT_226);
    stringBuffer.append(createClass.getQualifiedClassifierAccessor());
    stringBuffer.append(TEXT_227);
    } else {
    stringBuffer.append(TEXT_228);
    stringBuffer.append(createClass.getGenPackage().getQualifiedFactoryInstanceAccessor());
    stringBuffer.append(TEXT_229);
    stringBuffer.append(createClass.getName());
    stringBuffer.append(TEXT_230);
    }
    //ItemProvider/newChildDescriptorsReferenceDelegatedFeature.override.javajetinc
    } else { GenDataType createDataType = (GenDataType)createClassifier;
    stringBuffer.append(TEXT_231);
    stringBuffer.append(createFeature.getQualifiedFeatureAccessor());
    stringBuffer.append(TEXT_232);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.FeatureMapUtil"));
    stringBuffer.append(TEXT_233);
    stringBuffer.append(delegatedFeature.getQualifiedFeatureAccessor());
    stringBuffer.append(TEXT_234);
    if (delegatedFeature.isEnumBasedType()) {
    stringBuffer.append(TEXT_235);
    stringBuffer.append(delegatedFeature.getTypeGenEnum().getStaticValue(delegatedFeature.getEcoreFeature().getDefaultValueLiteral()));
    stringBuffer.append(TEXT_236);
    } else if (delegatedFeature.isStringBasedType()) {
    stringBuffer.append(TEXT_237);
    stringBuffer.append(delegatedFeature.getCreateChildValueLiteral());
    stringBuffer.append(TEXT_238);
    stringBuffer.append(genModel.getNonNLS());
    } else { String literal = delegatedFeature.getCreateChildValueLiteral();
    stringBuffer.append(TEXT_239);
    stringBuffer.append(createDataType.getGenPackage().getQualifiedEFactoryInstanceAccessor());
    stringBuffer.append(TEXT_240);
    stringBuffer.append(createDataType.getQualifiedClassifierAccessor());
    stringBuffer.append(TEXT_241);
    stringBuffer.append(literal);
    stringBuffer.append(TEXT_242);
    if (literal != null) {
    stringBuffer.append(genModel.getNonNLS());
    } else {
    stringBuffer.append(TEXT_243);
    }
    }
    //ItemProvider/newChildDescriptorsAttributeDelegatedFeature.override.javajetinc
    }
    } else if (createFeature.isReferenceType()) { GenClass createClass = (GenClass)createClassifier;
    stringBuffer.append(TEXT_244);
    stringBuffer.append(createFeature.getQualifiedFeatureAccessor());
    stringBuffer.append(TEXT_245);
    if (createClass.isMapEntry()) { 
    stringBuffer.append(TEXT_246);
    stringBuffer.append(createClass.getGenPackage().getQualifiedEFactoryInstanceAccessor());
    stringBuffer.append(TEXT_247);
    stringBuffer.append(createClass.getQualifiedClassifierAccessor());
    stringBuffer.append(TEXT_248);
    } else {
    stringBuffer.append(TEXT_249);
    stringBuffer.append(createClass.getGenPackage().getQualifiedFactoryInstanceAccessor());
    stringBuffer.append(TEXT_250);
    stringBuffer.append(createClass.getName());
    stringBuffer.append(TEXT_251);
    }
    //ItemProvider/newChildDescriptorsReferenceFeature.override.javajetinc 
    } else { GenDataType createDataType = (GenDataType)createClassifier;
    stringBuffer.append(TEXT_252);
    stringBuffer.append(createFeature.getQualifiedFeatureAccessor());
    stringBuffer.append(TEXT_253);
    if (createFeature.isEnumBasedType()) {
    stringBuffer.append(TEXT_254);
    stringBuffer.append(createFeature.getTypeGenEnum().getStaticValue(createFeature.getEcoreFeature().getDefaultValueLiteral()));
    stringBuffer.append(TEXT_255);
    } else if (createFeature.isStringBasedType()) {
    stringBuffer.append(TEXT_256);
    stringBuffer.append(createFeature.getCreateChildValueLiteral());
    stringBuffer.append(TEXT_257);
    stringBuffer.append(genModel.getNonNLS());
    } else { String literal = createFeature.getCreateChildValueLiteral();
    stringBuffer.append(TEXT_258);
    stringBuffer.append(createDataType.getGenPackage().getQualifiedEFactoryInstanceAccessor());
    stringBuffer.append(TEXT_259);
    stringBuffer.append(createDataType.getQualifiedClassifierAccessor());
    stringBuffer.append(TEXT_260);
    stringBuffer.append(literal);
    stringBuffer.append(TEXT_261);
    if (literal != null) {
    stringBuffer.append(genModel.getNonNLS());
    } else {
    stringBuffer.append(TEXT_262);
    }
    }
    //ItemProvider/newChildDescriptorsAttributeFeature.override.javajetinc
    }
    }
    stringBuffer.append(TEXT_263);
    if (!genClass.getSharedClassCreateChildFeatures().isEmpty()) {
    stringBuffer.append(TEXT_264);
    if (genModel.useClassOverrideAnnotation()) {
    stringBuffer.append(TEXT_265);
    }
    stringBuffer.append(TEXT_266);
    stringBuffer.append(genModel.getImportedName(genModel.useGenerics() ? "java.util.Collection<?>" : "java.util.Collection"));
    stringBuffer.append(TEXT_267);
    if (genClass.hasFeatureMapCreateChildFeatures()) {
    stringBuffer.append(TEXT_268);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.EStructuralFeature"));
    stringBuffer.append(TEXT_269);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.FeatureMapUtil"));
    stringBuffer.append(TEXT_270);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.FeatureMap"));
    stringBuffer.append(TEXT_271);
    }
    stringBuffer.append(TEXT_272);
    for (Iterator<GenFeature> i = genClass.getSharedClassCreateChildFeatures().iterator(); i.hasNext();) { GenFeature createFeature = i.next();
    stringBuffer.append(TEXT_273);
    stringBuffer.append(createFeature.getQualifiedFeatureAccessor());
    stringBuffer.append(i.hasNext() ? " ||" : ";");
    }
    stringBuffer.append(TEXT_274);
    stringBuffer.append(genModel.getNonNLS());
    stringBuffer.append(TEXT_275);
    }
    }
    if (genClass.getProviderExtendsGenClass() == null || genClass.getProviderExtendsGenClass().getGenPackage() != genPackage && (!genPackage.isExtensibleProviderFactory() || genClass.getProviderExtendsGenClass().getGenPackage().isExtensibleProviderFactory() != genPackage.isExtensibleProviderFactory())) {
    stringBuffer.append(TEXT_276);
    if (genModel.useClassOverrideAnnotation()) {
    stringBuffer.append(TEXT_277);
    }
    stringBuffer.append(TEXT_278);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.util.ResourceLocator"));
    stringBuffer.append(TEXT_279);
    if (genPackage.isExtensibleProviderFactory()) {
    stringBuffer.append(TEXT_280);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.edit.provider.IChildCreationExtender"));
    stringBuffer.append(TEXT_281);
    } else {
    stringBuffer.append(TEXT_282);
    stringBuffer.append(genPackage.getImportedEditPluginClassName());
    stringBuffer.append(TEXT_283);
    }
    stringBuffer.append(TEXT_284);
    }
    stringBuffer.append(TEXT_285);
    genModel.emitSortedImports();
    stringBuffer.append(TEXT_286);
    return stringBuffer.toString();
  }
}
