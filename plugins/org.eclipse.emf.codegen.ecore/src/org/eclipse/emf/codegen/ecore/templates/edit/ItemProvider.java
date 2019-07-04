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
  protected final String TEXT_8 = NL + " * @generated" + NL + " */";
  protected final String TEXT_9 = NL + "@Deprecated";
  protected final String TEXT_10 = NL + "public class ";
  protected final String TEXT_11 = " ";
  protected final String TEXT_12 = "extends ";
  protected final String TEXT_13 = NL + "\textends ";
  protected final String TEXT_14 = NL + "\timplements";
  protected final String TEXT_15 = NL + "\t\t";
  protected final String TEXT_16 = ",";
  protected final String TEXT_17 = NL + "{";
  protected final String TEXT_18 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */" + NL + "\tpublic static final ";
  protected final String TEXT_19 = " copyright = ";
  protected final String TEXT_20 = ";";
  protected final String TEXT_21 = NL;
  protected final String TEXT_22 = NL + "\t/**" + NL + "\t * This constructs an instance from a factory and a notifier." + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */" + NL + "\tpublic ";
  protected final String TEXT_23 = "(AdapterFactory adapterFactory)" + NL + "\t{" + NL + "\t\tsuper(adapterFactory);" + NL + "\t}" + NL + "" + NL + "\t/**" + NL + "\t * This returns the property descriptors for the adapted class." + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */";
  protected final String TEXT_24 = NL + "\t@Override";
  protected final String TEXT_25 = NL + "\tpublic ";
  protected final String TEXT_26 = " getPropertyDescriptors(Object object)" + NL + "\t{" + NL + "\t\tif (itemPropertyDescriptors == null)" + NL + "\t\t{" + NL + "\t\t\tsuper.getPropertyDescriptors(object);" + NL;
  protected final String TEXT_27 = NL + "\t\t\tadd";
  protected final String TEXT_28 = "PropertyDescriptor(object);";
  protected final String TEXT_29 = NL + "\t\t}" + NL + "\t\treturn itemPropertyDescriptors;" + NL + "\t}" + NL;
  protected final String TEXT_30 = NL + "\t/**" + NL + "\t * This adds a property descriptor for the ";
  protected final String TEXT_31 = " feature." + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->";
  protected final String TEXT_32 = NL + "\t * ";
  protected final String TEXT_33 = NL + "\t * @generated" + NL + "\t */";
  protected final String TEXT_34 = NL + "\t@Deprecated";
  protected final String TEXT_35 = NL + "\tprotected void add";
  protected final String TEXT_36 = "PropertyDescriptor(Object object)" + NL + "\t{";
  protected final String TEXT_37 = NL + "\t\titemPropertyDescriptors.add" + NL + "\t\t\t(createItemPropertyDescriptor" + NL + "\t\t\t\t(((";
  protected final String TEXT_38 = ")adapterFactory).getRootAdapterFactory()," + NL + "\t\t\t\t getResourceLocator()," + NL + "\t\t\t\t getString(\"_UI_";
  protected final String TEXT_39 = "_";
  protected final String TEXT_40 = "_feature\"),";
  protected final String TEXT_41 = NL + "\t\t\t\t getString(\"_UI_PropertyDescriptor_description\", \"_UI_";
  protected final String TEXT_42 = "_feature\", \"_UI_";
  protected final String TEXT_43 = "_type\"),";
  protected final String TEXT_44 = NL + "\t\t\t\t getString(\"_UI_";
  protected final String TEXT_45 = "_description\"),";
  protected final String TEXT_46 = NL + "\t\t\t\t ";
  protected final String TEXT_47 = "," + NL + "\t\t\t\t ";
  protected final String TEXT_48 = NL + "\t\t\t\t null,";
  protected final String TEXT_49 = ".";
  protected final String TEXT_50 = NL + "\t\t\t\t getString(\"";
  protected final String TEXT_51 = "\"),";
  protected final String TEXT_52 = NL + "\t\t\t\t null," + NL + "\t\t\t\t ";
  protected final String TEXT_53 = ".createURI(";
  protected final String TEXT_54 = ")));";
  protected final String TEXT_55 = NL + "\t\t\t\t null));";
  protected final String TEXT_56 = NL + "\t\t\t\t new String[] {";
  protected final String TEXT_57 = NL + "\t\t\t\t\t\"";
  protected final String TEXT_58 = "\"";
  protected final String TEXT_59 = NL + "\t\t\t\t }," + NL + "\t\t\t\t ";
  protected final String TEXT_60 = NL + "\t\t\t\t }));";
  protected final String TEXT_61 = NL + "\t}" + NL;
  protected final String TEXT_62 = NL + "\t/**" + NL + "\t * This specifies how to implement {@link #getChildren} and is used to deduce an appropriate feature for an" + NL + "\t * {@link org.eclipse.emf.edit.command.AddCommand}, {@link org.eclipse.emf.edit.command.RemoveCommand} or" + NL + "\t * {@link org.eclipse.emf.edit.command.MoveCommand} in {@link #createCommand}." + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */";
  protected final String TEXT_63 = " getChildrenFeatures(Object object)" + NL + "\t{" + NL + "\t\tif (childrenFeatures == null)" + NL + "\t\t{" + NL + "\t\t\tsuper.getChildrenFeatures(object);";
  protected final String TEXT_64 = NL + "\t\t\tchildrenFeatures.add(";
  protected final String TEXT_65 = ");";
  protected final String TEXT_66 = NL + "\t\t}" + NL + "\t\treturn childrenFeatures;" + NL + "\t}" + NL;
  protected final String TEXT_67 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */";
  protected final String TEXT_68 = NL + "\tprotected ";
  protected final String TEXT_69 = " getChildFeature(Object object, Object child)" + NL + "\t{" + NL + "\t\t// Check the type of the specified child object and return the proper feature to use for" + NL + "\t\t// adding (see {@link AddCommand}) it as a child." + NL + "" + NL + "\t\treturn super.getChildFeature(object, child);" + NL + "\t}" + NL;
  protected final String TEXT_70 = NL + "\tpublic boolean hasChildren(Object object)" + NL + "\t{" + NL + "\t\treturn hasChildren(object, ";
  protected final String TEXT_71 = ");" + NL + "\t}" + NL;
  protected final String TEXT_72 = NL + "\t/**" + NL + "\t * This returns ";
  protected final String TEXT_73 = ".gif." + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */";
  protected final String TEXT_74 = NL + "\tpublic Object getImage(Object object)" + NL + "\t{" + NL + "\t\treturn overlayImage(object, getResourceLocator().getImage(\"full/obj16/";
  protected final String TEXT_75 = "\"));";
  protected final String TEXT_76 = NL + "\t/**" + NL + "\t * This returns <code>getImage(object)</code> for the column index <code>0</code> or <code>super.getImage(object)</code> otherwise." + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @see #getText(Object)" + NL + "\t * @see #getColumnText(Object, int)" + NL + "\t * @generated" + NL + "\t */";
  protected final String TEXT_77 = NL + "\tpublic Object getColumnImage(Object object, int columnIndex)" + NL + "\t{" + NL + "\t\t// TODO: implement this method to return appropriate information for each column." + NL + "\t\t// Ensure that you remove @generated or mark it @generated NOT" + NL + "\t\treturn columnIndex == 0 ? getImage(object) : super.getImage(object);" + NL + "\t}" + NL;
  protected final String TEXT_78 = NL + "\tprotected boolean shouldComposeCreationImage()" + NL + "\t{" + NL + "\t\treturn true;" + NL + "\t}" + NL;
  protected final String TEXT_79 = NL + "\t/**" + NL + "\t * This returns the label text for the adapted class." + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */";
  protected final String TEXT_80 = NL + "\tpublic String getText(Object object)" + NL + "\t{";
  protected final String TEXT_81 = NL + "\t\treturn ((";
  protected final String TEXT_82 = ")getStyledText(object)).getString();";
  protected final String TEXT_83 = "<?, ?>";
  protected final String TEXT_84 = " = (";
  protected final String TEXT_85 = ")object;";
  protected final String TEXT_86 = NL + "\t\treturn \"\" + ";
  protected final String TEXT_87 = ".getKey() + \" -> \" + ";
  protected final String TEXT_88 = ".getValue();";
  protected final String TEXT_89 = NL + "\t\tString key = crop(\"\" + ";
  protected final String TEXT_90 = ".getKey());";
  protected final String TEXT_91 = NL + "\t\tString key = \"\" + ";
  protected final String TEXT_92 = ".getKey();";
  protected final String TEXT_93 = NL + "\t\tString value = crop(\"\" + ";
  protected final String TEXT_94 = ".getValue());";
  protected final String TEXT_95 = NL + "\t\tString value = \"\" + ";
  protected final String TEXT_96 = NL + "\t\treturn key + \" -> \" + value;";
  protected final String TEXT_97 = ")object;" + NL + "\t\treturn getString(\"_UI_";
  protected final String TEXT_98 = "_type\") + \" \" + ";
  protected final String TEXT_99 = "();";
  protected final String TEXT_100 = NL + "\t\tString label = crop(((";
  protected final String TEXT_101 = ")object).";
  protected final String TEXT_102 = "());";
  protected final String TEXT_103 = NL + "\t\tString label = ((";
  protected final String TEXT_104 = " labelValue = ((";
  protected final String TEXT_105 = ")object).eGet(";
  protected final String TEXT_106 = NL + "\t\tString label = labelValue == null ? null : labelValue.toString();";
  protected final String TEXT_107 = NL + "\t\treturn label == null || label.length() == 0 ?" + NL + "\t\t\tgetString(\"_UI_";
  protected final String TEXT_108 = "_type\") :";
  protected final String TEXT_109 = NL + "\t\t\tgetString(\"_UI_";
  protected final String TEXT_110 = "_type\") + \" \" + label;";
  protected final String TEXT_111 = NL + "\t\treturn getString(\"_UI_";
  protected final String TEXT_112 = "_type\");";
  protected final String TEXT_113 = NL + "\t/**" + NL + "\t * This returns <code>getText(object)</code> for the column index <code>0</code> or <code>super.getText(object)</code> otherwise." + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @see #getImage(Object)" + NL + "\t * @see #getColumnImage(Object, int)" + NL + "\t * @generated" + NL + "\t */";
  protected final String TEXT_114 = NL + "\tpublic String getColumnText(Object object, int columnIndex)" + NL + "\t{" + NL + "\t\t// TODO: implement this method to return appropriate information for each column." + NL + "\t\t// Ensure that you remove @generated or mark it @generated NOT" + NL + "\t\treturn columnIndex == 0 ? getText(object) : super.getText(object);" + NL + "\t}" + NL;
  protected final String TEXT_115 = NL + "\t/**" + NL + "\t * This returns the label styled text for the adapted class." + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */";
  protected final String TEXT_116 = NL + "\tpublic Object getStyledText(Object object)" + NL + "\t{";
  protected final String TEXT_117 = NL + "\t\treturn new ";
  protected final String TEXT_118 = "(\"\" + ";
  protected final String TEXT_119 = ".getKey()).append(\" -> \", ";
  protected final String TEXT_120 = ".QUALIFIER_STYLER).append(\"\" + ";
  protected final String TEXT_121 = "(key).append(\" -> \", ";
  protected final String TEXT_122 = ".QUALIFIER_STYLER).append(value);";
  protected final String TEXT_123 = ")object;" + NL + "\t\treturn new ";
  protected final String TEXT_124 = "(getString(\"_UI_";
  protected final String TEXT_125 = "_type\"), ";
  protected final String TEXT_126 = ".QUALIFIER_STYLER).append(\" \").append(";
  protected final String TEXT_127 = ".toString(";
  protected final String TEXT_128 = "()));";
  protected final String TEXT_129 = NL + "    \t";
  protected final String TEXT_130 = " styledLabel = new ";
  protected final String TEXT_131 = "();" + NL + "\t\tif (label == null || label.length() == 0)" + NL + "\t\t{" + NL + "\t\t\tstyledLabel.append(getString(\"_UI_";
  protected final String TEXT_132 = ".QUALIFIER_STYLER); ";
  protected final String TEXT_133 = NL + "\t\t} else {" + NL + "\t\t\tstyledLabel.append(getString(\"_UI_";
  protected final String TEXT_134 = ".QUALIFIER_STYLER).append(\" \" + label);";
  protected final String TEXT_135 = NL + "\t\t}" + NL + "\t\treturn styledLabel;";
  protected final String TEXT_136 = "_type\"));";
  protected final String TEXT_137 = NL + "\t}";
  protected final String TEXT_138 = NL + NL + "\t/**" + NL + "\t * This handles model notifications by calling {@link #updateChildren} to update any cached" + NL + "\t * children and by creating a viewer notification, which it passes to {@link #fireNotifyChanged}." + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */";
  protected final String TEXT_139 = NL + "\tpublic void notifyChanged(Notification notification)" + NL + "\t{" + NL + "\t\tupdateChildren(notification);";
  protected final String TEXT_140 = NL + NL + "\t\tswitch (notification.getFeatureID(";
  protected final String TEXT_141 = ".class))" + NL + "\t\t{";
  protected final String TEXT_142 = NL + "\t\t\tcase ";
  protected final String TEXT_143 = ":";
  protected final String TEXT_144 = NL + "\t\t\t\tfireNotifyChanged(new ";
  protected final String TEXT_145 = "(notification, notification.getNotifier(), false, true));" + NL + "\t\t\t\treturn;";
  protected final String TEXT_146 = "(notification, notification.getNotifier(), true, false));" + NL + "\t\t\t\treturn;";
  protected final String TEXT_147 = "(notification, notification.getNotifier(), true, true));" + NL + "\t\t\t\treturn;";
  protected final String TEXT_148 = NL + "\t\t\tdefault:";
  protected final String TEXT_149 = NL + "\t\t}";
  protected final String TEXT_150 = "\t\tsuper.notifyChanged(notification);";
  protected final String TEXT_151 = "\t\treturn;" + NL + "\t\t\t}";
  protected final String TEXT_152 = NL + "\t/**" + NL + "\t * This adds {@link org.eclipse.emf.edit.command.CommandParameter}s describing the children" + NL + "\t * that can be created under this object." + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */";
  protected final String TEXT_153 = NL + "\tprotected void collectNewChildDescriptors(";
  protected final String TEXT_154 = " newChildDescriptors, Object object)" + NL + "\t{" + NL + "\t\tsuper.collectNewChildDescriptors(newChildDescriptors, object);";
  protected final String TEXT_155 = NL + NL + "\t\tnewChildDescriptors.add" + NL + "\t\t\t(createChildParameter" + NL + "\t\t\t\t(";
  protected final String TEXT_156 = ".createEntry" + NL + "\t\t\t\t\t(";
  protected final String TEXT_157 = NL + "\t\t\t\t\t ";
  protected final String TEXT_158 = ".create(";
  protected final String TEXT_159 = "))));";
  protected final String TEXT_160 = ".create";
  protected final String TEXT_161 = "())));";
  protected final String TEXT_162 = ".createFromString(";
  protected final String TEXT_163 = ", ";
  protected final String TEXT_164 = " // TODO: ensure this is a valid literal value";
  protected final String TEXT_165 = "));";
  protected final String TEXT_166 = NL + "\t/**" + NL + "\t * This returns the label text for {@link org.eclipse.emf.edit.command.CreateChildCommand}." + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */";
  protected final String TEXT_167 = NL + "\tpublic String getCreateChildText(Object owner, Object feature, Object child, ";
  protected final String TEXT_168 = " selection)" + NL + "\t{" + NL + "\t\tObject childFeature = feature;" + NL + "\t\tObject childObject = child;" + NL;
  protected final String TEXT_169 = NL + "\t\tif (childFeature instanceof ";
  protected final String TEXT_170 = " && ";
  protected final String TEXT_171 = ".isFeatureMap((EStructuralFeature)childFeature))" + NL + "\t\t{" + NL + "\t\t\t";
  protected final String TEXT_172 = ".Entry entry = (FeatureMap.Entry)childObject;" + NL + "\t\t\tchildFeature = entry.getEStructuralFeature();" + NL + "\t\t\tchildObject = entry.getValue();" + NL + "\t\t}" + NL;
  protected final String TEXT_173 = NL + "\t\tboolean qualify =";
  protected final String TEXT_174 = NL + "\t\t\tchildFeature == ";
  protected final String TEXT_175 = NL + NL + "\t\tif (qualify)" + NL + "\t\t{" + NL + "\t\t\treturn getString" + NL + "\t\t\t\t(\"_UI_CreateChild_text2\",";
  protected final String TEXT_176 = NL + "\t\t\t\t new Object[] { getTypeText(childObject), getFeatureText(childFeature), getTypeText(owner) });" + NL + "\t\t}" + NL + "\t\treturn super.getCreateChildText(owner, feature, child, selection);" + NL + "\t}" + NL;
  protected final String TEXT_177 = NL + "\t/**" + NL + "\t * Return the resource locator for this item provider's resources." + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */";
  protected final String TEXT_178 = " getResourceLocator()" + NL + "\t{";
  protected final String TEXT_179 = ")adapterFactory).getResourceLocator();";
  protected final String TEXT_180 = NL + "\t\treturn ";
  protected final String TEXT_181 = ".INSTANCE;";
  protected final String TEXT_182 = NL + "}";

  public String generate(Object argument)
  {
    final StringBuffer stringBuffer = new StringBuffer();
    
/**
 * Copyright (c) 2002-2019 IBM Corporation and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 *
 * Contributors:
 *   IBM - Initial API and implementation
 *   Alexander Fedorov <alexander.fedorov@arsysop.ru> - Bug 546714
 */

    GenClass genClass = (GenClass)argument; GenPackage genPackage = genClass.getGenPackage(); GenModel genModel=genPackage.getGenModel();
    final boolean isJDK50 = genModel.getComplianceLevel().getValue() >= GenJDKLevel.JDK50;
    final boolean forceDefaultCase = genModel.isSwitchMissingDefaultCase();String indentDefaultCase = forceDefaultCase ? "\t\t" : "";
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
    stringBuffer.append(TEXT_3);
    stringBuffer.append(genClass.getImplicitAPITags(genModel.getIndentation(stringBuffer)));
    }
    stringBuffer.append(TEXT_8);
    if (isJDK50 && genClass.hasImplicitAPIDeprecatedTag()) {
    stringBuffer.append(TEXT_9);
    }
    stringBuffer.append(TEXT_10);
    stringBuffer.append(genClass.getProviderClassName());
    stringBuffer.append(TEXT_11);
    if (genClass.getProviderImplementsClassNames().isEmpty()) {
    stringBuffer.append(TEXT_12);
    stringBuffer.append(genClass.getProviderBaseClassName() != null ? genClass.getProviderBaseClassName() : genModel.getImportedName("org.eclipse.emf.edit.provider.ItemProviderAdapter"));
    }
    if (!genClass.getProviderImplementsClassNames().isEmpty()) {
    stringBuffer.append(TEXT_13);
    stringBuffer.append(genClass.getProviderBaseClassName() != null ? genClass.getProviderBaseClassName() : genModel.getImportedName("org.eclipse.emf.edit.provider.ItemProviderAdapter"));
    stringBuffer.append(TEXT_14);
    for (Iterator<String> i = genClass.getProviderImplementsClassNames().iterator(); i.hasNext(); ) {
    stringBuffer.append(TEXT_15);
    stringBuffer.append(genModel.getImportedName(i.next()));
    if (i.hasNext()){
    stringBuffer.append(TEXT_16);
    }
    }
    }
    stringBuffer.append(TEXT_17);
    if (genModel.hasCopyrightField()) {
    stringBuffer.append(TEXT_18);
    stringBuffer.append(genModel.getImportedName("java.lang.String"));
    stringBuffer.append(TEXT_19);
    stringBuffer.append(genModel.getCopyrightFieldLiteral());
    stringBuffer.append(TEXT_20);
    stringBuffer.append(genModel.getNonNLS());
    stringBuffer.append(TEXT_21);
    }
    stringBuffer.append(TEXT_22);
    stringBuffer.append(genClass.getProviderClassName());
    stringBuffer.append(TEXT_23);
    if (genModel.useClassOverrideAnnotation()) {
    stringBuffer.append(TEXT_24);
    }
    stringBuffer.append(TEXT_25);
    stringBuffer.append(_List);
    stringBuffer.append(TEXT_26);
    for (GenFeature genFeature : genClass.getPropertyFeatures()) { 
    stringBuffer.append(TEXT_27);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_28);
    }
    stringBuffer.append(TEXT_29);
    for (GenFeature genFeature : genClass.getPropertyFeatures()) { 
    stringBuffer.append(TEXT_30);
    stringBuffer.append(genFeature.getFormattedName());
    stringBuffer.append(TEXT_31);
    if (genFeature.hasAPITags()) {
    stringBuffer.append(TEXT_32);
    stringBuffer.append(genFeature.getAPITags(genModel.getIndentation(stringBuffer)));
    }
    stringBuffer.append(TEXT_33);
    if (isJDK50 && genFeature.hasAPIDeprecatedTag()) {
    stringBuffer.append(TEXT_34);
    }
    stringBuffer.append(TEXT_35);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_36);
    stringBuffer.append(TEXT_37);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.edit.provider.ComposeableAdapterFactory"));
    stringBuffer.append(TEXT_38);
    stringBuffer.append(genFeature.getGenClass().getName());
    stringBuffer.append(TEXT_39);
    stringBuffer.append(genFeature.getName());
    stringBuffer.append(TEXT_40);
    stringBuffer.append(genModel.getNonNLS());
    if (genFeature.getPropertyDescription() == null || genFeature.getPropertyDescription().length() == 0) {
    stringBuffer.append(TEXT_41);
    stringBuffer.append(genFeature.getGenClass().getName());
    stringBuffer.append(TEXT_39);
    stringBuffer.append(genFeature.getName());
    stringBuffer.append(TEXT_42);
    stringBuffer.append(genFeature.getGenClass().getName());
    stringBuffer.append(TEXT_43);
    stringBuffer.append(genModel.getNonNLS());
    stringBuffer.append(genModel.getNonNLS(2));
    stringBuffer.append(genModel.getNonNLS(3));
    } else {
    stringBuffer.append(TEXT_44);
    stringBuffer.append(genFeature.getGenClass().getName());
    stringBuffer.append(TEXT_39);
    stringBuffer.append(genFeature.getName());
    stringBuffer.append(TEXT_45);
    stringBuffer.append(genModel.getNonNLS());
    }
    stringBuffer.append(TEXT_46);
    stringBuffer.append(genFeature.getQualifiedFeatureAccessor());
    stringBuffer.append(TEXT_47);
    stringBuffer.append(genFeature.getProperty() == GenPropertyKind.EDITABLE_LITERAL ? "true" : "false");
    stringBuffer.append(TEXT_47);
    stringBuffer.append(genFeature.isPropertyMultiLine() ? "true" : "false");
    stringBuffer.append(TEXT_47);
    stringBuffer.append(genFeature.isPropertySortChoices() ? "true" : "false");
    stringBuffer.append(TEXT_16);
    if (genFeature.isReferenceType()) {
    stringBuffer.append(TEXT_48);
    } else {
    stringBuffer.append(TEXT_46);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.edit.provider.ItemPropertyDescriptor"));
    stringBuffer.append(TEXT_49);
    stringBuffer.append(genFeature.getPropertyImageName());
    stringBuffer.append(TEXT_16);
    }
    if (genFeature.getPropertyCategory() == null || genFeature.getPropertyCategory().length() == 0) {
    stringBuffer.append(TEXT_48);
    } else {
    stringBuffer.append(TEXT_50);
    stringBuffer.append(genModel.getPropertyCategoryKey(genFeature.getPropertyCategory()));
    stringBuffer.append(TEXT_51);
    stringBuffer.append(genModel.getNonNLS());
    }
    if (genFeature.getPropertyFilterFlags().isEmpty()) {
    if (genModel.getRuntimeVersion().getValue() >= GenRuntimeVersion.EMF214_VALUE && genFeature.getPropertyEditorFactory() != null && genFeature.getPropertyEditorFactory().trim().length() != 0) {
    stringBuffer.append(TEXT_52);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.util.URI"));
    stringBuffer.append(TEXT_53);
    stringBuffer.append(org.eclipse.emf.codegen.ecore.genmodel.impl.Literals.toStringLiteral(genFeature.getPropertyEditorFactory().trim(), genModel));
    stringBuffer.append(TEXT_54);
    stringBuffer.append(genModel.getNonNLS());
    } else {
    stringBuffer.append(TEXT_55);
    }
    } else {
    stringBuffer.append(TEXT_56);
    /*}*/for (Iterator<String> j = genFeature.getPropertyFilterFlags().iterator(); j.hasNext();) { String filterFlag = j.next();
    if (filterFlag != null && filterFlag.length() > 0) {
    stringBuffer.append(TEXT_57);
    stringBuffer.append(filterFlag);
    stringBuffer.append(TEXT_58);
    if (j.hasNext()) {
    stringBuffer.append(TEXT_16);
    }
    stringBuffer.append(genModel.getNonNLS());
    }
    }
    if (genModel.getRuntimeVersion().getValue() >= GenRuntimeVersion.EMF214_VALUE && genFeature.getPropertyEditorFactory() != null && genFeature.getPropertyEditorFactory().trim().length() != 0) { // {
    stringBuffer.append(TEXT_59);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.util.URI"));
    stringBuffer.append(TEXT_53);
    stringBuffer.append(org.eclipse.emf.codegen.ecore.genmodel.impl.Literals.toStringLiteral(genFeature.getPropertyEditorFactory().trim(), genModel));
    stringBuffer.append(TEXT_54);
    } else { // {
    stringBuffer.append(TEXT_60);
    }
    }
    //ItemProvider/addPropertyDescriptor.override.javajetinc
    stringBuffer.append(TEXT_61);
    }
    if (!genClass.getChildrenFeatures().isEmpty()) {
    stringBuffer.append(TEXT_62);
    if (genModel.useClassOverrideAnnotation()) {
    stringBuffer.append(TEXT_24);
    }
    stringBuffer.append(TEXT_25);
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
    stringBuffer.append(TEXT_24);
    }
    stringBuffer.append(TEXT_68);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.EStructuralFeature"));
    stringBuffer.append(TEXT_69);
    }
    }
    if (genClass.needsHasChildrenMethodOverride()) {
    stringBuffer.append(TEXT_67);
    if (genModel.useClassOverrideAnnotation()) {
    stringBuffer.append(TEXT_24);
    }
    stringBuffer.append(TEXT_70);
    stringBuffer.append(genModel.isOptimizedHasChildren());
    stringBuffer.append(TEXT_71);
    }
    if (genClass.isImage()) {
    stringBuffer.append(TEXT_72);
    stringBuffer.append(genClass.getName());
    stringBuffer.append(TEXT_73);
    if (genModel.useClassOverrideAnnotation()) {
    stringBuffer.append(TEXT_24);
    }
    stringBuffer.append(TEXT_74);
    stringBuffer.append(genClass.getName());
    stringBuffer.append(TEXT_75);
    stringBuffer.append(genModel.getNonNLS());
    stringBuffer.append(TEXT_61);
    }
    if (genClass.getProviderImplementsClassNames().contains("org.eclipse.emf.edit.provider.ITableItemLabelProvider")) {
    stringBuffer.append(TEXT_76);
    if (genModel.useClassOverrideAnnotation()) {
    stringBuffer.append(TEXT_24);
    }
    stringBuffer.append(TEXT_77);
    }
    if (genModel.getRuntimeVersion().getValue() >= GenRuntimeVersion.EMF26_VALUE && !genModel.isCreationIcons()) {
    stringBuffer.append(TEXT_67);
    if (genModel.useClassOverrideAnnotation()) {
    stringBuffer.append(TEXT_24);
    }
    stringBuffer.append(TEXT_78);
    }
    stringBuffer.append(TEXT_79);
    if (genModel.useClassOverrideAnnotation()) {
    stringBuffer.append(TEXT_24);
    }
    stringBuffer.append(TEXT_80);
    if (genModel.isStyleProviders()) {
    stringBuffer.append(TEXT_81);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.edit.provider.StyledString"));
    stringBuffer.append(TEXT_82);
    } else {
    if (genClass.isMapEntry()) {
    stringBuffer.append(TEXT_15);
    stringBuffer.append(genClass.getImportedInterfaceName());
    if (genModel.useGenerics()) {
    stringBuffer.append(TEXT_83);
    }
    stringBuffer.append(TEXT_11);
    stringBuffer.append(genClass.getSafeUncapName());
    stringBuffer.append(TEXT_84);
    stringBuffer.append(genClass.getImportedInterfaceName());
    if (genModel.useGenerics()) {
    stringBuffer.append(TEXT_83);
    }
    stringBuffer.append(TEXT_85);
    if (!genClass.getMapEntryKeyFeature().isPropertyMultiLine() && !genClass.getMapEntryValueFeature().isPropertyMultiLine()) {
    stringBuffer.append(TEXT_86);
    stringBuffer.append(genClass.getSafeUncapName());
    stringBuffer.append(TEXT_87);
    stringBuffer.append(genClass.getSafeUncapName());
    stringBuffer.append(TEXT_88);
    stringBuffer.append(genModel.getNonNLS());
    stringBuffer.append(genModel.getNonNLS(2));
    } else {
    if (genClass.getMapEntryKeyFeature().isPropertyMultiLine()) {
    stringBuffer.append(TEXT_89);
    stringBuffer.append(genClass.getSafeUncapName());
    stringBuffer.append(TEXT_90);
    stringBuffer.append(genModel.getNonNLS());
    } else {
    stringBuffer.append(TEXT_91);
    stringBuffer.append(genClass.getSafeUncapName());
    stringBuffer.append(TEXT_92);
    stringBuffer.append(genModel.getNonNLS());
    }
    if (genClass.getMapEntryValueFeature().isPropertyMultiLine()) {
    stringBuffer.append(TEXT_93);
    stringBuffer.append(genClass.getSafeUncapName());
    stringBuffer.append(TEXT_94);
    stringBuffer.append(genModel.getNonNLS());
    } else {
    stringBuffer.append(TEXT_95);
    stringBuffer.append(genClass.getSafeUncapName());
    stringBuffer.append(TEXT_88);
    stringBuffer.append(genModel.getNonNLS());
    }
    stringBuffer.append(TEXT_96);
    stringBuffer.append(genModel.getNonNLS());
    }
    } else if (genClass.getLabelFeature() != null) { GenFeature labelFeature = genClass.getLabelFeature();
    if (labelFeature.isPrimitiveType() && !labelFeature.getGenClass().isDynamic() && !labelFeature.isSuppressedGetVisibility()) {
    stringBuffer.append(TEXT_15);
    stringBuffer.append(genClass.getImportedInterfaceName());
    stringBuffer.append(genClass.getInterfaceWildTypeArguments());
    stringBuffer.append(TEXT_11);
    stringBuffer.append(genClass.getSafeUncapName());
    stringBuffer.append(TEXT_84);
    stringBuffer.append(genClass.getImportedInterfaceName());
    stringBuffer.append(genClass.getInterfaceWildTypeArguments());
    stringBuffer.append(TEXT_97);
    stringBuffer.append(genClass.getName());
    stringBuffer.append(TEXT_98);
    stringBuffer.append(genClass.getSafeUncapName());
    stringBuffer.append(TEXT_49);
    stringBuffer.append(genClass.getLabelFeature().getGetAccessor());
    stringBuffer.append(TEXT_99);
    stringBuffer.append(genModel.getNonNLS());
    stringBuffer.append(genModel.getNonNLS(2));
    } else {
    if (labelFeature.isStringType() && !labelFeature.getGenClass().isDynamic() && !labelFeature.isSuppressedGetVisibility()) {
    if (labelFeature.isPropertyMultiLine()) {
    stringBuffer.append(TEXT_100);
    stringBuffer.append(genClass.getImportedInterfaceName());
    stringBuffer.append(genClass.getInterfaceWildTypeArguments());
    stringBuffer.append(TEXT_101);
    stringBuffer.append(labelFeature.getGetAccessor());
    stringBuffer.append(TEXT_102);
    } else {
    stringBuffer.append(TEXT_103);
    stringBuffer.append(genClass.getImportedInterfaceName());
    stringBuffer.append(genClass.getInterfaceWildTypeArguments());
    stringBuffer.append(TEXT_101);
    stringBuffer.append(labelFeature.getGetAccessor());
    stringBuffer.append(TEXT_99);
    }
    } else {
    if (labelFeature.isSuppressedGetVisibility() || labelFeature.getGenClass().isDynamic()) {
    stringBuffer.append(TEXT_15);
    stringBuffer.append(genModel.getImportedName("java.lang.Object"));
    stringBuffer.append(TEXT_104);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.EObject"));
    stringBuffer.append(TEXT_105);
    stringBuffer.append(labelFeature.getQualifiedFeatureAccessor());
    stringBuffer.append(TEXT_65);
    } else {
    stringBuffer.append(TEXT_15);
    stringBuffer.append(labelFeature.getRawImportedType());
    stringBuffer.append(TEXT_104);
    stringBuffer.append(genClass.getImportedInterfaceName());
    stringBuffer.append(genClass.getInterfaceWildTypeArguments());
    stringBuffer.append(TEXT_101);
    stringBuffer.append(labelFeature.getGetAccessor());
    stringBuffer.append(TEXT_99);
    }
    stringBuffer.append(TEXT_106);
    }
    stringBuffer.append(TEXT_107);
    stringBuffer.append(genClass.getName());
    stringBuffer.append(TEXT_108);
    stringBuffer.append(genModel.getNonNLS());
    stringBuffer.append(TEXT_109);
    stringBuffer.append(genClass.getName());
    stringBuffer.append(TEXT_110);
    stringBuffer.append(genModel.getNonNLS());
    stringBuffer.append(genModel.getNonNLS(2));
    }
    } else {
    stringBuffer.append(TEXT_111);
    stringBuffer.append(genClass.getName());
    stringBuffer.append(TEXT_112);
    stringBuffer.append(genModel.getNonNLS());
    }
    }
    //ItemProvider/getText.override.javajetinc
    stringBuffer.append(TEXT_61);
    if (genClass.getProviderImplementsClassNames().contains("org.eclipse.emf.edit.provider.ITableItemLabelProvider")) {
    stringBuffer.append(TEXT_113);
    if (genModel.useClassOverrideAnnotation()) {
    stringBuffer.append(TEXT_24);
    }
    stringBuffer.append(TEXT_114);
    }
    if (genModel.isStyleProviders()) {
    stringBuffer.append(TEXT_115);
    if (genModel.useClassOverrideAnnotation()) {
    stringBuffer.append(TEXT_24);
    }
    stringBuffer.append(TEXT_116);
    if (genClass.isMapEntry()) {
    stringBuffer.append(TEXT_15);
    stringBuffer.append(genClass.getImportedInterfaceName());
    if (genModel.useGenerics()) {
    stringBuffer.append(TEXT_83);
    }
    stringBuffer.append(TEXT_11);
    stringBuffer.append(genClass.getSafeUncapName());
    stringBuffer.append(TEXT_84);
    stringBuffer.append(genClass.getImportedInterfaceName());
    if (genModel.useGenerics()) {
    stringBuffer.append(TEXT_83);
    }
    stringBuffer.append(TEXT_85);
    if (!genClass.getMapEntryKeyFeature().isPropertyMultiLine() && !genClass.getMapEntryValueFeature().isPropertyMultiLine()) {
    stringBuffer.append(TEXT_117);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.edit.provider.StyledString"));
    stringBuffer.append(TEXT_118);
    stringBuffer.append(genClass.getSafeUncapName());
    stringBuffer.append(TEXT_119);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.edit.provider.StyledString$Style"));
    stringBuffer.append(TEXT_120);
    stringBuffer.append(genClass.getSafeUncapName());
    stringBuffer.append(TEXT_94);
    stringBuffer.append(genModel.getNonNLS());
    stringBuffer.append(genModel.getNonNLS(2));
    } else {
    if (genClass.getMapEntryKeyFeature().isPropertyMultiLine()) {
    stringBuffer.append(TEXT_89);
    stringBuffer.append(genClass.getSafeUncapName());
    stringBuffer.append(TEXT_90);
    stringBuffer.append(genModel.getNonNLS());
    } else {
    stringBuffer.append(TEXT_91);
    stringBuffer.append(genClass.getSafeUncapName());
    stringBuffer.append(TEXT_92);
    stringBuffer.append(genModel.getNonNLS());
    }
    if (genClass.getMapEntryValueFeature().isPropertyMultiLine()) {
    stringBuffer.append(TEXT_93);
    stringBuffer.append(genClass.getSafeUncapName());
    stringBuffer.append(TEXT_94);
    stringBuffer.append(genModel.getNonNLS());
    } else {
    stringBuffer.append(TEXT_95);
    stringBuffer.append(genClass.getSafeUncapName());
    stringBuffer.append(TEXT_88);
    stringBuffer.append(genModel.getNonNLS());
    }
    stringBuffer.append(TEXT_117);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.edit.provider.StyledString"));
    stringBuffer.append(TEXT_121);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.edit.provider.StyledString$Style"));
    stringBuffer.append(TEXT_122);
    stringBuffer.append(genModel.getNonNLS());
    }
    } else if (genClass.getLabelFeature() != null) { GenFeature labelFeature = genClass.getLabelFeature();
    if (labelFeature.isPrimitiveType() && !labelFeature.getGenClass().isDynamic() && !labelFeature.isSuppressedGetVisibility()) {
    stringBuffer.append(TEXT_15);
    stringBuffer.append(genClass.getImportedInterfaceName());
    stringBuffer.append(genClass.getInterfaceWildTypeArguments());
    stringBuffer.append(TEXT_11);
    stringBuffer.append(genClass.getSafeUncapName());
    stringBuffer.append(TEXT_84);
    stringBuffer.append(genClass.getImportedInterfaceName());
    stringBuffer.append(genClass.getInterfaceWildTypeArguments());
    stringBuffer.append(TEXT_123);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.edit.provider.StyledString"));
    stringBuffer.append(TEXT_124);
    stringBuffer.append(genClass.getName());
    stringBuffer.append(TEXT_125);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.edit.provider.StyledString$Style"));
    stringBuffer.append(TEXT_126);
    stringBuffer.append(labelFeature.getObjectType(genClass));
    stringBuffer.append(TEXT_127);
    stringBuffer.append(genClass.getSafeUncapName());
    stringBuffer.append(TEXT_49);
    stringBuffer.append(genClass.getLabelFeature().getGetAccessor());
    stringBuffer.append(TEXT_128);
    stringBuffer.append(genModel.getNonNLS());
    stringBuffer.append(genModel.getNonNLS(2));
    } else {
    if (labelFeature.isStringType() && !labelFeature.getGenClass().isDynamic() && !labelFeature.isSuppressedGetVisibility()) {
    if (labelFeature.isPropertyMultiLine()) {
    stringBuffer.append(TEXT_100);
    stringBuffer.append(genClass.getImportedInterfaceName());
    stringBuffer.append(genClass.getInterfaceWildTypeArguments());
    stringBuffer.append(TEXT_101);
    stringBuffer.append(labelFeature.getGetAccessor());
    stringBuffer.append(TEXT_102);
    } else {
    stringBuffer.append(TEXT_103);
    stringBuffer.append(genClass.getImportedInterfaceName());
    stringBuffer.append(genClass.getInterfaceWildTypeArguments());
    stringBuffer.append(TEXT_101);
    stringBuffer.append(labelFeature.getGetAccessor());
    stringBuffer.append(TEXT_99);
    }
    } else {
    if (labelFeature.isSuppressedGetVisibility() || labelFeature.getGenClass().isDynamic()) {
    stringBuffer.append(TEXT_15);
    stringBuffer.append(genModel.getImportedName("java.lang.Object"));
    stringBuffer.append(TEXT_104);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.EObject"));
    stringBuffer.append(TEXT_105);
    stringBuffer.append(labelFeature.getQualifiedFeatureAccessor());
    stringBuffer.append(TEXT_65);
    } else {
    stringBuffer.append(TEXT_15);
    stringBuffer.append(labelFeature.getRawImportedType());
    stringBuffer.append(TEXT_104);
    stringBuffer.append(genClass.getImportedInterfaceName());
    stringBuffer.append(genClass.getInterfaceWildTypeArguments());
    stringBuffer.append(TEXT_101);
    stringBuffer.append(labelFeature.getGetAccessor());
    stringBuffer.append(TEXT_99);
    }
    stringBuffer.append(TEXT_106);
    }
    stringBuffer.append(TEXT_129);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.edit.provider.StyledString"));
    stringBuffer.append(TEXT_130);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.edit.provider.StyledString"));
    stringBuffer.append(TEXT_131);
    stringBuffer.append(genClass.getName());
    stringBuffer.append(TEXT_125);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.edit.provider.StyledString$Style"));
    stringBuffer.append(TEXT_132);
    stringBuffer.append(genModel.getNonNLS());
    stringBuffer.append(TEXT_133);
    stringBuffer.append(genClass.getName());
    stringBuffer.append(TEXT_125);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.edit.provider.StyledString$Style"));
    stringBuffer.append(TEXT_134);
    stringBuffer.append(genModel.getNonNLS());
    stringBuffer.append(genModel.getNonNLS(2));
    stringBuffer.append(TEXT_135);
    }
    } else {
    stringBuffer.append(TEXT_117);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.edit.provider.StyledString"));
    stringBuffer.append(TEXT_124);
    stringBuffer.append(genClass.getName());
    stringBuffer.append(TEXT_136);
    stringBuffer.append(genModel.getNonNLS());
    }
    //ItemProvider/getStyledText.override.javajetinc
    stringBuffer.append(TEXT_137);
    }
    stringBuffer.append(TEXT_138);
    if (genModel.useClassOverrideAnnotation()) {
    stringBuffer.append(TEXT_24);
    }
    stringBuffer.append(TEXT_139);
    boolean hasSwitch = !genClass.getLabelNotifyFeatures().isEmpty() || !genClass.getContentNotifyFeatures().isEmpty() || !genClass.getLabelAndContentNotifyFeatures().isEmpty(); if (hasSwitch) {
    stringBuffer.append(TEXT_140);
    stringBuffer.append(genClass.getRawImportedInterfaceName());
    stringBuffer.append(TEXT_141);
    if (!genClass.getLabelNotifyFeatures().isEmpty()) {
    for (GenFeature genFeature : genClass.getLabelNotifyFeatures()) { 
    stringBuffer.append(TEXT_142);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(TEXT_143);
    }
    stringBuffer.append(TEXT_144);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.edit.provider.ViewerNotification"));
    stringBuffer.append(TEXT_145);
    }
    if (!genClass.getContentNotifyFeatures().isEmpty()) {
    for (GenFeature genFeature : genClass.getContentNotifyFeatures()) { 
    stringBuffer.append(TEXT_142);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(TEXT_143);
    }
    stringBuffer.append(TEXT_144);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.edit.provider.ViewerNotification"));
    stringBuffer.append(TEXT_146);
    }
    if (!genClass.getLabelAndContentNotifyFeatures().isEmpty()) {
    for (GenFeature genFeature : genClass.getLabelAndContentNotifyFeatures()) { 
    stringBuffer.append(TEXT_142);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(TEXT_143);
    }
    stringBuffer.append(TEXT_144);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.edit.provider.ViewerNotification"));
    stringBuffer.append(TEXT_147);
    }
    if (forceDefaultCase) {
    stringBuffer.append(TEXT_148);
    } else { // {
    stringBuffer.append(TEXT_149);
    } // }
    }
    stringBuffer.append(TEXT_21);
    stringBuffer.append(hasSwitch ? indentDefaultCase : "");
    stringBuffer.append(TEXT_150);
    if (hasSwitch && forceDefaultCase) { // {
    stringBuffer.append(TEXT_21);
    stringBuffer.append(indentDefaultCase);
    stringBuffer.append(TEXT_151);
    }
    stringBuffer.append(TEXT_61);
    if (genModel.isCreationCommands()) {
    stringBuffer.append(TEXT_152);
    if (genModel.useClassOverrideAnnotation()) {
    stringBuffer.append(TEXT_24);
    }
    stringBuffer.append(TEXT_153);
    stringBuffer.append(genModel.getImportedName(genModel.useGenerics() ? "java.util.Collection<java.lang.Object>" : "java.util.Collection"));
    stringBuffer.append(TEXT_154);
    for (GenClass.ChildCreationData childCreationData : genClass.getChildCreationData()) { GenFeature createFeature = childCreationData.createFeature; GenFeature delegatedFeature = childCreationData.delegatedFeature; GenClassifier createClassifier = childCreationData.createClassifier;
    if (createFeature.isFeatureMapType()) {
    if (delegatedFeature.isReferenceType()) { GenClass createClass = (GenClass)createClassifier;
    stringBuffer.append(TEXT_155);
    stringBuffer.append(createFeature.getQualifiedFeatureAccessor());
    stringBuffer.append(TEXT_47);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.FeatureMapUtil"));
    stringBuffer.append(TEXT_156);
    stringBuffer.append(delegatedFeature.getQualifiedFeatureAccessor());
    stringBuffer.append(TEXT_16);
    if (createClass.isMapEntry()) { 
    stringBuffer.append(TEXT_157);
    stringBuffer.append(createClass.getGenPackage().getQualifiedEFactoryInstanceAccessor());
    stringBuffer.append(TEXT_158);
    stringBuffer.append(createClass.getQualifiedClassifierAccessor());
    stringBuffer.append(TEXT_159);
    } else {
    stringBuffer.append(TEXT_157);
    stringBuffer.append(createClass.getGenPackage().getQualifiedFactoryInstanceAccessor());
    stringBuffer.append(TEXT_160);
    stringBuffer.append(createClass.getName());
    stringBuffer.append(TEXT_161);
    }
    //ItemProvider/newChildDescriptorsReferenceDelegatedFeature.override.javajetinc
    } else { GenDataType createDataType = (GenDataType)createClassifier;
    stringBuffer.append(TEXT_155);
    stringBuffer.append(createFeature.getQualifiedFeatureAccessor());
    stringBuffer.append(TEXT_47);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.FeatureMapUtil"));
    stringBuffer.append(TEXT_156);
    stringBuffer.append(delegatedFeature.getQualifiedFeatureAccessor());
    stringBuffer.append(TEXT_16);
    if (delegatedFeature.isEnumBasedType()) {
    stringBuffer.append(TEXT_157);
    stringBuffer.append(delegatedFeature.getTypeGenEnum().getStaticValue(delegatedFeature.getEcoreFeature().getDefaultValueLiteral()));
    stringBuffer.append(TEXT_54);
    } else if (delegatedFeature.isStringBasedType()) {
    stringBuffer.append(TEXT_157);
    stringBuffer.append(delegatedFeature.getCreateChildValueLiteral());
    stringBuffer.append(TEXT_54);
    stringBuffer.append(genModel.getNonNLS());
    } else { String literal = delegatedFeature.getCreateChildValueLiteral();
    stringBuffer.append(TEXT_157);
    stringBuffer.append(createDataType.getGenPackage().getQualifiedEFactoryInstanceAccessor());
    stringBuffer.append(TEXT_162);
    stringBuffer.append(createDataType.getQualifiedClassifierAccessor());
    stringBuffer.append(TEXT_163);
    stringBuffer.append(literal);
    stringBuffer.append(TEXT_159);
    if (literal != null) {
    stringBuffer.append(genModel.getNonNLS());
    } else {
    stringBuffer.append(TEXT_164);
    }
    }
    //ItemProvider/newChildDescriptorsAttributeDelegatedFeature.override.javajetinc
    }
    } else if (createFeature.isReferenceType()) { GenClass createClass = (GenClass)createClassifier;
    stringBuffer.append(TEXT_155);
    stringBuffer.append(createFeature.getQualifiedFeatureAccessor());
    stringBuffer.append(TEXT_16);
    if (createClass.isMapEntry()) { 
    stringBuffer.append(TEXT_46);
    stringBuffer.append(createClass.getGenPackage().getQualifiedEFactoryInstanceAccessor());
    stringBuffer.append(TEXT_158);
    stringBuffer.append(createClass.getQualifiedClassifierAccessor());
    stringBuffer.append(TEXT_54);
    } else {
    stringBuffer.append(TEXT_46);
    stringBuffer.append(createClass.getGenPackage().getQualifiedFactoryInstanceAccessor());
    stringBuffer.append(TEXT_160);
    stringBuffer.append(createClass.getName());
    stringBuffer.append(TEXT_128);
    }
    //ItemProvider/newChildDescriptorsReferenceFeature.override.javajetinc 
    } else { GenDataType createDataType = (GenDataType)createClassifier;
    stringBuffer.append(TEXT_155);
    stringBuffer.append(createFeature.getQualifiedFeatureAccessor());
    stringBuffer.append(TEXT_16);
    if (createFeature.isEnumBasedType()) {
    stringBuffer.append(TEXT_46);
    stringBuffer.append(createFeature.getTypeGenEnum().getStaticValue(createFeature.getEcoreFeature().getDefaultValueLiteral()));
    stringBuffer.append(TEXT_165);
    } else if (createFeature.isStringBasedType()) {
    stringBuffer.append(TEXT_46);
    stringBuffer.append(createFeature.getCreateChildValueLiteral());
    stringBuffer.append(TEXT_165);
    stringBuffer.append(genModel.getNonNLS());
    } else { String literal = createFeature.getCreateChildValueLiteral();
    stringBuffer.append(TEXT_46);
    stringBuffer.append(createDataType.getGenPackage().getQualifiedEFactoryInstanceAccessor());
    stringBuffer.append(TEXT_162);
    stringBuffer.append(createDataType.getQualifiedClassifierAccessor());
    stringBuffer.append(TEXT_163);
    stringBuffer.append(literal);
    stringBuffer.append(TEXT_54);
    if (literal != null) {
    stringBuffer.append(genModel.getNonNLS());
    } else {
    stringBuffer.append(TEXT_164);
    }
    }
    //ItemProvider/newChildDescriptorsAttributeFeature.override.javajetinc
    }
    }
    stringBuffer.append(TEXT_61);
    if (!genClass.getSharedClassCreateChildFeatures().isEmpty()) {
    stringBuffer.append(TEXT_166);
    if (genModel.useClassOverrideAnnotation()) {
    stringBuffer.append(TEXT_24);
    }
    stringBuffer.append(TEXT_167);
    stringBuffer.append(genModel.getImportedName(genModel.useGenerics() ? "java.util.Collection<?>" : "java.util.Collection"));
    stringBuffer.append(TEXT_168);
    if (genClass.hasFeatureMapCreateChildFeatures()) {
    stringBuffer.append(TEXT_169);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.EStructuralFeature"));
    stringBuffer.append(TEXT_170);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.FeatureMapUtil"));
    stringBuffer.append(TEXT_171);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.FeatureMap"));
    stringBuffer.append(TEXT_172);
    }
    stringBuffer.append(TEXT_173);
    for (Iterator<GenFeature> i = genClass.getSharedClassCreateChildFeatures().iterator(); i.hasNext();) { GenFeature createFeature = i.next();
    stringBuffer.append(TEXT_174);
    stringBuffer.append(createFeature.getQualifiedFeatureAccessor());
    stringBuffer.append(i.hasNext() ? " ||" : ";");
    }
    stringBuffer.append(TEXT_175);
    stringBuffer.append(genModel.getNonNLS());
    stringBuffer.append(TEXT_176);
    }
    }
    if (genClass.getProviderExtendsGenClass() == null || genClass.getProviderExtendsGenClass().getGenPackage() != genPackage && (!genPackage.isExtensibleProviderFactory() || genClass.getProviderExtendsGenClass().getGenPackage().isExtensibleProviderFactory() != genPackage.isExtensibleProviderFactory())) {
    stringBuffer.append(TEXT_177);
    if (genModel.useClassOverrideAnnotation()) {
    stringBuffer.append(TEXT_24);
    }
    stringBuffer.append(TEXT_25);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.common.util.ResourceLocator"));
    stringBuffer.append(TEXT_178);
    if (genPackage.isExtensibleProviderFactory()) {
    stringBuffer.append(TEXT_81);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.edit.provider.IChildCreationExtender"));
    stringBuffer.append(TEXT_179);
    } else {
    stringBuffer.append(TEXT_180);
    stringBuffer.append(genPackage.getImportedEditPluginClassName());
    stringBuffer.append(TEXT_181);
    }
    stringBuffer.append(TEXT_61);
    }
    stringBuffer.append(TEXT_182);
    genModel.emitSortedImports();
    stringBuffer.append(TEXT_21);
    return stringBuffer.toString();
  }
}
