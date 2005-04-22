package org.eclipse.emf.codegen.ecore.templates.edit;

import java.util.*;
import org.eclipse.emf.codegen.ecore.genmodel.*;

public class ItemProvider
{
  protected final String NL = System.getProperties().getProperty("line.separator");
  protected final String TEXT_1 = "";
  protected final String TEXT_2 = "/**" + NL + " * <copyright>" + NL + " * </copyright>" + NL + " *" + NL + " * ";
  protected final String TEXT_3 = "Id";
  protected final String TEXT_4 = NL + " */" + NL + "package ";
  protected final String TEXT_5 = ";" + NL + NL;
  protected final String TEXT_6 = NL + NL + "/**" + NL + " * This is the item provider adapter for a {@link ";
  protected final String TEXT_7 = "} object." + NL + " * <!-- begin-user-doc -->" + NL + " * <!-- end-user-doc -->" + NL + " * @generated" + NL + " */" + NL + "public class ";
  protected final String TEXT_8 = NL + "\textends ";
  protected final String TEXT_9 = NL + "\timplements" + NL + "\t\tIEditingDomainItemProvider," + NL + "\t\tIStructuredItemContentProvider," + NL + "\t\tITreeItemContentProvider," + NL + "\t\tIItemLabelProvider," + NL + "\t\tIItemPropertySource" + NL + "{";
  protected final String TEXT_10 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */" + NL + "\tpublic static final ";
  protected final String TEXT_11 = " copyright = \"";
  protected final String TEXT_12 = "\";";
  protected final String TEXT_13 = NL;
  protected final String TEXT_14 = NL + "\t/**" + NL + "\t * This constructs an instance from a factory and a notifier." + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */" + NL + "\tpublic ";
  protected final String TEXT_15 = "(AdapterFactory adapterFactory)" + NL + "\t{" + NL + "\t\tsuper(adapterFactory);" + NL + "\t}" + NL + "" + NL + "\t/**" + NL + "\t * This returns the property descriptors for the adapted class." + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */" + NL + "\tpublic List getPropertyDescriptors(Object object)" + NL + "\t{" + NL + "\t\tif (itemPropertyDescriptors == null)" + NL + "\t\t{" + NL + "\t\t\tsuper.getPropertyDescriptors(object);" + NL;
  protected final String TEXT_16 = NL + "\t\t\tadd";
  protected final String TEXT_17 = "PropertyDescriptor(object);";
  protected final String TEXT_18 = NL + "\t\t}" + NL + "\t\treturn itemPropertyDescriptors;" + NL + "\t}" + NL;
  protected final String TEXT_19 = NL + "\t/**" + NL + "\t * This adds a property descriptor for the ";
  protected final String TEXT_20 = " feature." + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */" + NL + "\tprotected void add";
  protected final String TEXT_21 = "PropertyDescriptor(Object object)" + NL + "\t{" + NL + "\t\titemPropertyDescriptors.add" + NL + "\t\t\t(createItemPropertyDescriptor" + NL + "\t\t\t\t(((";
  protected final String TEXT_22 = ")adapterFactory).getRootAdapterFactory()," + NL + "\t\t\t\t getResourceLocator()," + NL + "\t\t\t\t getString(\"_UI_";
  protected final String TEXT_23 = "_";
  protected final String TEXT_24 = "_feature\"),";
  protected final String TEXT_25 = NL + "\t\t\t\t getString(\"_UI_PropertyDescriptor_description\", \"_UI_";
  protected final String TEXT_26 = "_";
  protected final String TEXT_27 = "_feature\", \"_UI_";
  protected final String TEXT_28 = "_type\"),";
  protected final String TEXT_29 = NL + "\t\t\t\t ";
  protected final String TEXT_30 = ".eINSTANCE.get";
  protected final String TEXT_31 = "()," + NL + "\t\t\t\t ";
  protected final String TEXT_32 = ",";
  protected final String TEXT_33 = NL + "\t\t\t\t null,";
  protected final String TEXT_34 = NL + "\t\t\t\t ";
  protected final String TEXT_35 = ".";
  protected final String TEXT_36 = ",";
  protected final String TEXT_37 = NL + "\t\t\t\t null,";
  protected final String TEXT_38 = NL + "\t\t\t\t getString(\"";
  protected final String TEXT_39 = "\"),";
  protected final String TEXT_40 = NL + "\t\t\t\t null));";
  protected final String TEXT_41 = NL + "\t\t\t\t new String[] {";
  protected final String TEXT_42 = NL + "\t\t\t\t\t\"";
  protected final String TEXT_43 = "\"";
  protected final String TEXT_44 = ",";
  protected final String TEXT_45 = NL + "\t\t\t\t }));";
  protected final String TEXT_46 = NL + "\t}" + NL;
  protected final String TEXT_47 = NL + "\t/**" + NL + "\t * This specifies how to implement {@link #getChildren} and is used to deduce an appropriate feature for an" + NL + "\t * {@link org.eclipse.emf.edit.command.AddCommand}, {@link org.eclipse.emf.edit.command.RemoveCommand} or" + NL + "\t * {@link org.eclipse.emf.edit.command.MoveCommand} in {@link #createCommand}." + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */" + NL + "\tpublic ";
  protected final String TEXT_48 = " getChildrenFeatures(Object object)" + NL + "\t{" + NL + "\t\tif (childrenFeatures == null)" + NL + "\t\t{" + NL + "\t\t\tsuper.getChildrenFeatures(object);";
  protected final String TEXT_49 = NL + "\t\t\tchildrenFeatures.add(";
  protected final String TEXT_50 = ".eINSTANCE.get";
  protected final String TEXT_51 = "());";
  protected final String TEXT_52 = NL + "\t\t}" + NL + "\t\treturn childrenFeatures;" + NL + "\t}" + NL;
  protected final String TEXT_53 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */" + NL + "\tprotected ";
  protected final String TEXT_54 = " getChildFeature(Object object, Object child)" + NL + "\t{" + NL + "\t\t// Check the type of the specified child object and return the proper feature to use for" + NL + "\t\t// adding (see {@link AddCommand}) it as a child." + NL + "" + NL + "\t\treturn super.getChildFeature(object, child);" + NL + "\t}" + NL;
  protected final String TEXT_55 = NL + "\t/**" + NL + "\t * This returns ";
  protected final String TEXT_56 = ".gif." + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */" + NL + "\tpublic Object getImage(Object object)" + NL + "\t{" + NL + "\t\treturn getResourceLocator().getImage(\"full/obj16/";
  protected final String TEXT_57 = "\");";
  protected final String TEXT_58 = NL + "\t}" + NL;
  protected final String TEXT_59 = NL + "\t/**" + NL + "\t * This returns the label text for the adapted class." + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */" + NL + "\tpublic String getText(Object object)" + NL + "\t{";
  protected final String TEXT_60 = NL + "\t\t";
  protected final String TEXT_61 = " ";
  protected final String TEXT_62 = " = (";
  protected final String TEXT_63 = ")object;" + NL + "\t\treturn \"\" + ";
  protected final String TEXT_64 = ".getKey() + \" -> \" + ";
  protected final String TEXT_65 = ".getValue();";
  protected final String TEXT_66 = NL + "\t\t";
  protected final String TEXT_67 = " ";
  protected final String TEXT_68 = " = (";
  protected final String TEXT_69 = ")object;" + NL + "\t\treturn getString(\"_UI_";
  protected final String TEXT_70 = "_type\") + \" \" + ";
  protected final String TEXT_71 = ".";
  protected final String TEXT_72 = "();";
  protected final String TEXT_73 = NL + "\t\tString label = ((";
  protected final String TEXT_74 = ")object).";
  protected final String TEXT_75 = "();";
  protected final String TEXT_76 = NL + "\t\t";
  protected final String TEXT_77 = " labelValue = ((";
  protected final String TEXT_78 = ")object).";
  protected final String TEXT_79 = "();" + NL + "\t\tString label = labelValue == null ? null : labelValue.toString();";
  protected final String TEXT_80 = NL + "\t\treturn label == null || label.length() == 0 ?" + NL + "\t\t\tgetString(\"_UI_";
  protected final String TEXT_81 = "_type\") :";
  protected final String TEXT_82 = NL + "\t\t\tgetString(\"_UI_";
  protected final String TEXT_83 = "_type\") + \" \" + label;";
  protected final String TEXT_84 = NL + "\t\treturn getString(\"_UI_";
  protected final String TEXT_85 = "_type\");";
  protected final String TEXT_86 = NL + "\t}" + NL + "" + NL + "\t/**" + NL + "\t * This handles model notifications by calling {@link #updateChildren} to update any cached" + NL + "\t * children and by creating a viewer notification, which it passes to {@link #fireNotifyChanged}." + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */" + NL + "\tpublic void notifyChanged(Notification notification)" + NL + "\t{" + NL + "\t\tupdateChildren(notification);";
  protected final String TEXT_87 = NL + NL + "\t\tswitch (notification.getFeatureID(";
  protected final String TEXT_88 = ".class))" + NL + "\t\t{";
  protected final String TEXT_89 = NL + "\t\t\tcase ";
  protected final String TEXT_90 = ":";
  protected final String TEXT_91 = NL + "\t\t\t\tfireNotifyChanged(new ";
  protected final String TEXT_92 = "(notification, notification.getNotifier(), false, true));" + NL + "\t\t\t\treturn;";
  protected final String TEXT_93 = NL + "\t\t\tcase ";
  protected final String TEXT_94 = ":";
  protected final String TEXT_95 = NL + "\t\t\t\tfireNotifyChanged(new ";
  protected final String TEXT_96 = "(notification, notification.getNotifier(), true, false));" + NL + "\t\t\t\treturn;";
  protected final String TEXT_97 = NL + "\t\t\tcase ";
  protected final String TEXT_98 = ":";
  protected final String TEXT_99 = NL + "\t\t\t\tfireNotifyChanged(new ";
  protected final String TEXT_100 = "(notification, notification.getNotifier(), true, true));" + NL + "\t\t\t\treturn;";
  protected final String TEXT_101 = NL + "\t\t}";
  protected final String TEXT_102 = NL + "\t\tsuper.notifyChanged(notification);" + NL + "\t}" + NL;
  protected final String TEXT_103 = NL + "\t/**" + NL + "\t * This adds to the collection of {@link org.eclipse.emf.edit.command.CommandParameter}s" + NL + "\t * describing all of the children that can be created under this object." + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */" + NL + "\tprotected void collectNewChildDescriptors(";
  protected final String TEXT_104 = " newChildDescriptors, Object object)" + NL + "\t{" + NL + "\t\tsuper.collectNewChildDescriptors(newChildDescriptors, object);";
  protected final String TEXT_105 = NL + NL + "\t\tnewChildDescriptors.add" + NL + "\t\t\t(createChildParameter" + NL + "\t\t\t\t(";
  protected final String TEXT_106 = ".eINSTANCE.get";
  protected final String TEXT_107 = "(),";
  protected final String TEXT_108 = NL + "\t\t\t\t ";
  protected final String TEXT_109 = ".eINSTANCE.create(";
  protected final String TEXT_110 = ".eINSTANCE.get";
  protected final String TEXT_111 = "())));";
  protected final String TEXT_112 = NL + "\t\t\t\t ";
  protected final String TEXT_113 = ".eINSTANCE.create";
  protected final String TEXT_114 = "()));";
  protected final String TEXT_115 = NL + NL + "\t\tnewChildDescriptors.add" + NL + "\t\t\t(createChildParameter" + NL + "\t\t\t\t(";
  protected final String TEXT_116 = ".eINSTANCE.get";
  protected final String TEXT_117 = "()," + NL + "\t\t\t\t ";
  protected final String TEXT_118 = ".createEntry" + NL + "\t\t\t\t\t(";
  protected final String TEXT_119 = ".eINSTANCE.get";
  protected final String TEXT_120 = "(),";
  protected final String TEXT_121 = NL + "\t\t\t\t\t ";
  protected final String TEXT_122 = ".eINSTANCE.create(";
  protected final String TEXT_123 = ".eINSTANCE.get";
  protected final String TEXT_124 = "()))));";
  protected final String TEXT_125 = NL + "\t\t\t\t\t ";
  protected final String TEXT_126 = ".eINSTANCE.create";
  protected final String TEXT_127 = "())));";
  protected final String TEXT_128 = NL + NL + "\t\tnewChildDescriptors.add" + NL + "\t\t\t(createChildParameter" + NL + "\t\t\t\t(";
  protected final String TEXT_129 = ".eINSTANCE.get";
  protected final String TEXT_130 = "()," + NL + "\t\t\t\t ";
  protected final String TEXT_131 = ".createEntry" + NL + "\t\t\t\t\t(";
  protected final String TEXT_132 = ".eINSTANCE.get";
  protected final String TEXT_133 = "(),";
  protected final String TEXT_134 = NL + "\t\t\t\t\t ";
  protected final String TEXT_135 = ")));";
  protected final String TEXT_136 = NL + "\t\t\t\t\t ";
  protected final String TEXT_137 = ")));";
  protected final String TEXT_138 = NL + "\t\t\t\t\t ";
  protected final String TEXT_139 = ".eINSTANCE.createFromString(";
  protected final String TEXT_140 = ".eINSTANCE.get";
  protected final String TEXT_141 = "(), ";
  protected final String TEXT_142 = "))));";
  protected final String TEXT_143 = " // TODO: ensure this is a valid literal value";
  protected final String TEXT_144 = NL + NL + "\t\tnewChildDescriptors.add" + NL + "\t\t\t(createChildParameter" + NL + "\t\t\t\t(";
  protected final String TEXT_145 = ".eINSTANCE.get";
  protected final String TEXT_146 = "(),";
  protected final String TEXT_147 = NL + "\t\t\t\t ";
  protected final String TEXT_148 = ".eINSTANCE.create(";
  protected final String TEXT_149 = ".eINSTANCE.get";
  protected final String TEXT_150 = "())));";
  protected final String TEXT_151 = NL + "\t\t\t\t ";
  protected final String TEXT_152 = ".eINSTANCE.create";
  protected final String TEXT_153 = "()));";
  protected final String TEXT_154 = NL + NL + "\t\tnewChildDescriptors.add" + NL + "\t\t\t(createChildParameter" + NL + "\t\t\t\t(";
  protected final String TEXT_155 = ".eINSTANCE.get";
  protected final String TEXT_156 = "(),";
  protected final String TEXT_157 = NL + "\t\t\t\t ";
  protected final String TEXT_158 = "));";
  protected final String TEXT_159 = NL + "\t\t\t\t ";
  protected final String TEXT_160 = "));";
  protected final String TEXT_161 = NL + "\t\t\t\t ";
  protected final String TEXT_162 = ".eINSTANCE.createFromString(";
  protected final String TEXT_163 = ".eINSTANCE.get";
  protected final String TEXT_164 = "(), ";
  protected final String TEXT_165 = ")));";
  protected final String TEXT_166 = " // TODO: ensure this is a valid literal value";
  protected final String TEXT_167 = NL + "\t}" + NL;
  protected final String TEXT_168 = NL + "\t/**" + NL + "\t * This returns the label text for {@link org.eclipse.emf.edit.command.CreateChildCommand}." + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */" + NL + "\tpublic String getCreateChildText(Object owner, Object feature, Object child, ";
  protected final String TEXT_169 = " selection)" + NL + "\t{" + NL + "\t\tObject childFeature = feature;" + NL + "\t\tObject childObject = child;" + NL;
  protected final String TEXT_170 = NL + "\t\tif (childFeature instanceof ";
  protected final String TEXT_171 = " && ";
  protected final String TEXT_172 = ".isFeatureMap((EStructuralFeature)childFeature))" + NL + "\t\t{" + NL + "\t\t\t";
  protected final String TEXT_173 = ".Entry entry = (FeatureMap.Entry)childObject;" + NL + "\t\t\tchildFeature = entry.getEStructuralFeature();" + NL + "\t\t\tchildObject = entry.getValue();" + NL + "\t\t}" + NL;
  protected final String TEXT_174 = NL + "\t\tboolean qualify =";
  protected final String TEXT_175 = NL + "\t\t\tchildFeature == ";
  protected final String TEXT_176 = ".eINSTANCE.get";
  protected final String TEXT_177 = "()";
  protected final String TEXT_178 = NL + NL + "\t\tif (qualify)" + NL + "\t\t{" + NL + "\t\t\treturn getString" + NL + "\t\t\t\t(\"_UI_CreateChild_text2\",";
  protected final String TEXT_179 = NL + "\t\t\t\t new Object[] { getTypeText(childObject), getFeatureText(childFeature), getTypeText(owner) });" + NL + "\t\t}" + NL + "\t\treturn super.getCreateChildText(owner, feature, child, selection);" + NL + "\t}" + NL;
  protected final String TEXT_180 = NL + "\t/**" + NL + "\t * This returns the icon image for {@link org.eclipse.emf.edit.command.CreateChildCommand}." + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */" + NL + "\tpublic Object getCreateChildImage(Object owner, Object feature, Object child, Collection selection)" + NL + "\t{" + NL + "\t\tif (feature instanceof ";
  protected final String TEXT_181 = " && ";
  protected final String TEXT_182 = ".isFeatureMap((";
  protected final String TEXT_183 = ")feature))" + NL + "\t\t{" + NL + "\t\t\t";
  protected final String TEXT_184 = ".Entry entry = (";
  protected final String TEXT_185 = ".Entry)child;" + NL + "\t\t\tfeature = entry.getEStructuralFeature();" + NL + "\t\t\tchild = entry.getValue();        " + NL + "\t\t}" + NL + "" + NL + "\t\tif (feature instanceof ";
  protected final String TEXT_186 = " && child instanceof ";
  protected final String TEXT_187 = ")" + NL + "\t\t{" + NL + "\t\t\tString name = \"full/obj16/\" + ((EObject)child).eClass().getName();";
  protected final String TEXT_188 = NL + NL + "\t\t\ttry" + NL + "\t\t\t{" + NL + "\t\t\t\treturn getResourceLocator().getImage(name);" + NL + "\t\t\t}" + NL + "\t\t\tcatch (Exception e)" + NL + "\t\t\t{" + NL + "\t\t\t\t";
  protected final String TEXT_189 = ".INSTANCE.log(e);" + NL + "\t\t\t}" + NL + "\t\t}" + NL + "" + NL + "\t\treturn super.getCreateChildImage(owner, feature, child, selection);" + NL + "\t}" + NL;
  protected final String TEXT_190 = NL + "\t/**" + NL + "\t * Return the resource locator for this item provider's resources." + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */" + NL + "\tpublic ResourceLocator getResourceLocator()" + NL + "\t{" + NL + "\t\treturn ";
  protected final String TEXT_191 = ".INSTANCE;" + NL + "\t}" + NL;
  protected final String TEXT_192 = NL + "}";
  protected final String TEXT_193 = NL;

  public String generate(Object argument)
  {
    StringBuffer stringBuffer = new StringBuffer();
    
/**
 * <copyright>
 *
 * Copyright (c) 2002-2005 IBM Corporation and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Common Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/cpl-v10.html
 *
 * Contributors:
 *   IBM - Initial API and implementation
 *
 * </copyright>
 */

    GenClass genClass = (GenClass)argument; GenPackage genPackage = genClass.getGenPackage(); GenModel genModel=genPackage.getGenModel();
    stringBuffer.append(TEXT_1);
    stringBuffer.append(TEXT_2);
    stringBuffer.append("$");
    stringBuffer.append(TEXT_3);
    stringBuffer.append("$");
    stringBuffer.append(TEXT_4);
    stringBuffer.append(genPackage.getProviderPackageName());
    stringBuffer.append(TEXT_5);
    genModel.addImport("org.eclipse.emf.common.notify.AdapterFactory");
    genModel.addImport("org.eclipse.emf.common.notify.Notification");
    genModel.addImport("org.eclipse.emf.common.util.ResourceLocator");
    genModel.addImport("org.eclipse.emf.edit.provider.IEditingDomainItemProvider");
    genModel.addImport("org.eclipse.emf.edit.provider.IItemLabelProvider");
    genModel.addImport("org.eclipse.emf.edit.provider.IItemPropertySource");
    genModel.addImport("org.eclipse.emf.edit.provider.IStructuredItemContentProvider");
    genModel.addImport("org.eclipse.emf.edit.provider.ITreeItemContentProvider");
    genModel.addImport("java.util.List");
    genModel.markImportLocation(stringBuffer);
    stringBuffer.append(TEXT_6);
    stringBuffer.append(genClass.getQualifiedInterfaceName());
    stringBuffer.append(TEXT_7);
    stringBuffer.append(genClass.getProviderClassName());
    stringBuffer.append(TEXT_8);
    stringBuffer.append(genClass.getProviderBaseClassName() != null ? genClass.getProviderBaseClassName() : genModel.getImportedName("org.eclipse.emf.edit.provider.ItemProviderAdapter"));
    stringBuffer.append(TEXT_9);
    if (genModel.getCopyrightText() != null) {
    stringBuffer.append(TEXT_10);
    stringBuffer.append(genModel.getImportedName("java.lang.String"));
    stringBuffer.append(TEXT_11);
    stringBuffer.append(genModel.getCopyrightText());
    stringBuffer.append(TEXT_12);
    stringBuffer.append(genModel.getNonNLS());
    stringBuffer.append(TEXT_13);
    }
    stringBuffer.append(TEXT_14);
    stringBuffer.append(genClass.getProviderClassName());
    stringBuffer.append(TEXT_15);
    for (Iterator i=genClass.getPropertyFeatures().iterator(); i.hasNext();) { GenFeature genFeature = (GenFeature)i.next();
    stringBuffer.append(TEXT_16);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_17);
    }
    stringBuffer.append(TEXT_18);
    for (Iterator i=genClass.getPropertyFeatures().iterator(); i.hasNext();) { GenFeature genFeature = (GenFeature)i.next();
    stringBuffer.append(TEXT_19);
    stringBuffer.append(genFeature.getFormattedName());
    stringBuffer.append(TEXT_20);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_21);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.edit.provider.ComposeableAdapterFactory"));
    stringBuffer.append(TEXT_22);
    stringBuffer.append(genFeature.getGenClass().getName());
    stringBuffer.append(TEXT_23);
    stringBuffer.append(genFeature.getName());
    stringBuffer.append(TEXT_24);
    stringBuffer.append(genModel.getNonNLS());
    stringBuffer.append(TEXT_25);
    stringBuffer.append(genFeature.getGenClass().getName());
    stringBuffer.append(TEXT_26);
    stringBuffer.append(genFeature.getName());
    stringBuffer.append(TEXT_27);
    stringBuffer.append(genFeature.getGenClass().getName());
    stringBuffer.append(TEXT_28);
    stringBuffer.append(genModel.getNonNLS());
    stringBuffer.append(genModel.getNonNLS(2));
    stringBuffer.append(genModel.getNonNLS(3));
    stringBuffer.append(TEXT_29);
    stringBuffer.append(genFeature.getGenPackage().getImportedPackageInterfaceName());
    stringBuffer.append(TEXT_30);
    stringBuffer.append(genFeature.getFeatureAccessorName());
    stringBuffer.append(TEXT_31);
    stringBuffer.append(genFeature.getProperty() == GenPropertyKind.EDITABLE_LITERAL ? "true" : "false");
    stringBuffer.append(TEXT_32);
    if (genFeature.isReferenceType()) {
    stringBuffer.append(TEXT_33);
    } else {
    stringBuffer.append(TEXT_34);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.edit.provider.ItemPropertyDescriptor"));
    stringBuffer.append(TEXT_35);
    stringBuffer.append(genFeature.getPropertyImageName());
    stringBuffer.append(TEXT_36);
    }
    if (genFeature.getCategory() == null || genFeature.getCategory().length() == 0) {
    stringBuffer.append(TEXT_37);
    } else {
    stringBuffer.append(TEXT_38);
    stringBuffer.append(genFeature.getCategory());
    stringBuffer.append(TEXT_39);
    stringBuffer.append(genModel.getNonNLS());
    }
    if (genFeature.getFilterFlags().isEmpty()) {
    stringBuffer.append(TEXT_40);
    } else {
    stringBuffer.append(TEXT_41);
    for (Iterator j = genFeature.getFilterFlags().iterator(); j.hasNext();) { String filterFlag = (String)j.next();
    if (filterFlag != null && filterFlag.length() > 0) {
    stringBuffer.append(TEXT_42);
    stringBuffer.append(filterFlag);
    stringBuffer.append(TEXT_43);
    if (j.hasNext()) {
    stringBuffer.append(TEXT_44);
    }
    stringBuffer.append(genModel.getNonNLS());
    }
    }
    stringBuffer.append(TEXT_45);
    }
    stringBuffer.append(TEXT_46);
    }
    if (!genClass.getChildrenFeatures().isEmpty()) {
    stringBuffer.append(TEXT_47);
    stringBuffer.append(genModel.getImportedName("java.util.Collection"));
    stringBuffer.append(TEXT_48);
    for (Iterator i=genClass.getChildrenFeatures().iterator(); i.hasNext();) { GenFeature genFeature = (GenFeature)i.next();
    stringBuffer.append(TEXT_49);
    stringBuffer.append(genFeature.getGenPackage().getImportedPackageInterfaceName());
    stringBuffer.append(TEXT_50);
    stringBuffer.append(genFeature.getFeatureAccessorName());
    stringBuffer.append(TEXT_51);
    }
    stringBuffer.append(TEXT_52);
    if (genClass.getChildrenFeatures().size() > 1) {
    stringBuffer.append(TEXT_53);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.EStructuralFeature"));
    stringBuffer.append(TEXT_54);
    }
    }
    if (genClass.isImage()) {
    stringBuffer.append(TEXT_55);
    stringBuffer.append(genClass.getName());
    stringBuffer.append(TEXT_56);
    stringBuffer.append(genClass.getName());
    stringBuffer.append(TEXT_57);
    stringBuffer.append(genModel.getNonNLS());
    stringBuffer.append(TEXT_58);
    }
    stringBuffer.append(TEXT_59);
    if (genClass.isMapEntry()) {
    stringBuffer.append(TEXT_60);
    stringBuffer.append(genClass.getImportedInterfaceName());
    stringBuffer.append(TEXT_61);
    stringBuffer.append(genClass.getSafeUncapName());
    stringBuffer.append(TEXT_62);
    stringBuffer.append(genClass.getImportedInterfaceName());
    stringBuffer.append(TEXT_63);
    stringBuffer.append(genClass.getSafeUncapName());
    stringBuffer.append(TEXT_64);
    stringBuffer.append(genClass.getSafeUncapName());
    stringBuffer.append(TEXT_65);
    stringBuffer.append(genModel.getNonNLS());
    stringBuffer.append(genModel.getNonNLS(2));
    } else if (genClass.getLabelFeature() != null) { GenFeature labelFeature = genClass.getLabelFeature();
    if (labelFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_66);
    stringBuffer.append(genClass.getImportedInterfaceName());
    stringBuffer.append(TEXT_67);
    stringBuffer.append(genClass.getSafeUncapName());
    stringBuffer.append(TEXT_68);
    stringBuffer.append(genClass.getImportedInterfaceName());
    stringBuffer.append(TEXT_69);
    stringBuffer.append(genClass.getName());
    stringBuffer.append(TEXT_70);
    stringBuffer.append(genClass.getSafeUncapName());
    stringBuffer.append(TEXT_71);
    stringBuffer.append(genClass.getLabelFeature().getGetAccessor());
    stringBuffer.append(TEXT_72);
    stringBuffer.append(genModel.getNonNLS());
    stringBuffer.append(genModel.getNonNLS(2));
    } else {
    if (labelFeature.isStringType()) {
    stringBuffer.append(TEXT_73);
    stringBuffer.append(genClass.getImportedInterfaceName());
    stringBuffer.append(TEXT_74);
    stringBuffer.append(labelFeature.getGetAccessor());
    stringBuffer.append(TEXT_75);
    } else {
    stringBuffer.append(TEXT_76);
    stringBuffer.append(labelFeature.getImportedType());
    stringBuffer.append(TEXT_77);
    stringBuffer.append(genClass.getImportedInterfaceName());
    stringBuffer.append(TEXT_78);
    stringBuffer.append(labelFeature.getGetAccessor());
    stringBuffer.append(TEXT_79);
    }
    stringBuffer.append(TEXT_80);
    stringBuffer.append(genClass.getName());
    stringBuffer.append(TEXT_81);
    stringBuffer.append(genModel.getNonNLS());
    stringBuffer.append(TEXT_82);
    stringBuffer.append(genClass.getName());
    stringBuffer.append(TEXT_83);
    stringBuffer.append(genModel.getNonNLS());
    stringBuffer.append(genModel.getNonNLS(2));
    }
    } else {
    stringBuffer.append(TEXT_84);
    stringBuffer.append(genClass.getName());
    stringBuffer.append(TEXT_85);
    stringBuffer.append(genModel.getNonNLS());
    }
    stringBuffer.append(TEXT_86);
    if (!genClass.getLabelNotifyFeatures().isEmpty() || !genClass.getContentNotifyFeatures().isEmpty() || !genClass.getLabelAndContentNotifyFeatures().isEmpty()) {
    stringBuffer.append(TEXT_87);
    stringBuffer.append(genClass.getImportedInterfaceName());
    stringBuffer.append(TEXT_88);
    if (!genClass.getLabelNotifyFeatures().isEmpty()) {
    for (Iterator i=genClass.getLabelNotifyFeatures().iterator(); i.hasNext();) { GenFeature genFeature = (GenFeature)i.next();
    stringBuffer.append(TEXT_89);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(TEXT_90);
    }
    stringBuffer.append(TEXT_91);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.edit.provider.ViewerNotification"));
    stringBuffer.append(TEXT_92);
    }
    if (!genClass.getContentNotifyFeatures().isEmpty()) {
    for (Iterator i=genClass.getContentNotifyFeatures().iterator(); i.hasNext();) { GenFeature genFeature = (GenFeature)i.next();
    stringBuffer.append(TEXT_93);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(TEXT_94);
    }
    stringBuffer.append(TEXT_95);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.edit.provider.ViewerNotification"));
    stringBuffer.append(TEXT_96);
    }
    if (!genClass.getLabelAndContentNotifyFeatures().isEmpty()) {
    for (Iterator i=genClass.getLabelAndContentNotifyFeatures().iterator(); i.hasNext();) { GenFeature genFeature = (GenFeature)i.next();
    stringBuffer.append(TEXT_97);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(TEXT_98);
    }
    stringBuffer.append(TEXT_99);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.edit.provider.ViewerNotification"));
    stringBuffer.append(TEXT_100);
    }
    stringBuffer.append(TEXT_101);
    }
    stringBuffer.append(TEXT_102);
    if (genModel.isCreationCommands()) {
    stringBuffer.append(TEXT_103);
    stringBuffer.append(genModel.getImportedName("java.util.Collection"));
    stringBuffer.append(TEXT_104);
    for (Iterator i=genClass.getCrossPackageCreateChildFeatures().iterator(); i.hasNext();) { GenFeature createFeature = (GenFeature)i.next();
    for (Iterator c=genClass.getCrossPackageChildrenClasses(createFeature).iterator(); c.hasNext();) { GenClass createClass = (GenClass)c.next();
    stringBuffer.append(TEXT_105);
    stringBuffer.append(createFeature.getGenPackage().getImportedPackageInterfaceName());
    stringBuffer.append(TEXT_106);
    stringBuffer.append(createFeature.getFeatureAccessorName());
    stringBuffer.append(TEXT_107);
    if (createClass.isMapEntry()) { 
    stringBuffer.append(TEXT_108);
    stringBuffer.append(createClass.getGenPackage().getImportedFactoryInterfaceName());
    stringBuffer.append(TEXT_109);
    stringBuffer.append(createClass.getGenPackage().getImportedPackageInterfaceName());
    stringBuffer.append(TEXT_110);
    stringBuffer.append(createClass.getName());
    stringBuffer.append(TEXT_111);
    } else {
    stringBuffer.append(TEXT_112);
    stringBuffer.append(createClass.getGenPackage().getImportedFactoryInterfaceName());
    stringBuffer.append(TEXT_113);
    stringBuffer.append(createClass.getName());
    stringBuffer.append(TEXT_114);
    }
    }
    }
    for (Iterator i=genClass.getCreateChildFeatures().iterator(); i.hasNext();) { GenFeature createFeature = (GenFeature)i.next();
    if (createFeature.isFeatureMapType()) {
    for (Iterator d=createFeature.getDelegatedFeatures().iterator(); d.hasNext();) { GenFeature delegatedFeature = (GenFeature)d.next();
    if (delegatedFeature.isReferenceType()) {
    for (Iterator c=genClass.getChildrenClasses(delegatedFeature).iterator(); c.hasNext();) { GenClass createClass = (GenClass)c.next();
    stringBuffer.append(TEXT_115);
    stringBuffer.append(createFeature.getGenPackage().getImportedPackageInterfaceName());
    stringBuffer.append(TEXT_116);
    stringBuffer.append(createFeature.getFeatureAccessorName());
    stringBuffer.append(TEXT_117);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.FeatureMapUtil"));
    stringBuffer.append(TEXT_118);
    stringBuffer.append(delegatedFeature.getGenPackage().getImportedPackageInterfaceName());
    stringBuffer.append(TEXT_119);
    stringBuffer.append(delegatedFeature.getFeatureAccessorName());
    stringBuffer.append(TEXT_120);
    if (createClass.isMapEntry()) { 
    stringBuffer.append(TEXT_121);
    stringBuffer.append(createClass.getGenPackage().getImportedFactoryInterfaceName());
    stringBuffer.append(TEXT_122);
    stringBuffer.append(createClass.getGenPackage().getImportedPackageInterfaceName());
    stringBuffer.append(TEXT_123);
    stringBuffer.append(createClass.getName());
    stringBuffer.append(TEXT_124);
    } else {
    stringBuffer.append(TEXT_125);
    stringBuffer.append(createClass.getGenPackage().getImportedFactoryInterfaceName());
    stringBuffer.append(TEXT_126);
    stringBuffer.append(createClass.getName());
    stringBuffer.append(TEXT_127);
    }
    }
    } else {
    stringBuffer.append(TEXT_128);
    stringBuffer.append(createFeature.getGenPackage().getImportedPackageInterfaceName());
    stringBuffer.append(TEXT_129);
    stringBuffer.append(createFeature.getFeatureAccessorName());
    stringBuffer.append(TEXT_130);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.FeatureMapUtil"));
    stringBuffer.append(TEXT_131);
    stringBuffer.append(delegatedFeature.getGenPackage().getImportedPackageInterfaceName());
    stringBuffer.append(TEXT_132);
    stringBuffer.append(delegatedFeature.getFeatureAccessorName());
    stringBuffer.append(TEXT_133);
    if (delegatedFeature.isEnumBasedType()) {
    stringBuffer.append(TEXT_134);
    stringBuffer.append(delegatedFeature.getGenEnumType().getStaticValue(delegatedFeature.getEcoreFeature().getDefaultValueLiteral()));
    stringBuffer.append(TEXT_135);
    } else if (delegatedFeature.isStringBasedType()) {
    stringBuffer.append(TEXT_136);
    stringBuffer.append(delegatedFeature.getCreateChildValueLiteral());
    stringBuffer.append(TEXT_137);
    stringBuffer.append(genModel.getNonNLS());
    } else { String literal = delegatedFeature.getCreateChildValueLiteral(); GenDataType createDataType = delegatedFeature.getGenDataTypeType();
    stringBuffer.append(TEXT_138);
    stringBuffer.append(createDataType.getGenPackage().getImportedFactoryInterfaceName());
    stringBuffer.append(TEXT_139);
    stringBuffer.append(createDataType.getGenPackage().getImportedPackageInterfaceName());
    stringBuffer.append(TEXT_140);
    stringBuffer.append(createDataType.getClassifierAccessorName());
    stringBuffer.append(TEXT_141);
    stringBuffer.append(literal);
    stringBuffer.append(TEXT_142);
    if (literal != null) {
    stringBuffer.append(genModel.getNonNLS());
    } else {
    stringBuffer.append(TEXT_143);
    }
    }
    }
    }
    } else if (createFeature.isReferenceType()) {
    for (Iterator c=genClass.getChildrenClasses(createFeature).iterator(); c.hasNext();) { GenClass createClass = (GenClass)c.next();
    stringBuffer.append(TEXT_144);
    stringBuffer.append(createFeature.getGenPackage().getImportedPackageInterfaceName());
    stringBuffer.append(TEXT_145);
    stringBuffer.append(createFeature.getFeatureAccessorName());
    stringBuffer.append(TEXT_146);
    if (createClass.isMapEntry()) { 
    stringBuffer.append(TEXT_147);
    stringBuffer.append(createClass.getGenPackage().getImportedFactoryInterfaceName());
    stringBuffer.append(TEXT_148);
    stringBuffer.append(createClass.getGenPackage().getImportedPackageInterfaceName());
    stringBuffer.append(TEXT_149);
    stringBuffer.append(createClass.getName());
    stringBuffer.append(TEXT_150);
    } else {
    stringBuffer.append(TEXT_151);
    stringBuffer.append(createClass.getGenPackage().getImportedFactoryInterfaceName());
    stringBuffer.append(TEXT_152);
    stringBuffer.append(createClass.getName());
    stringBuffer.append(TEXT_153);
    }
    }
    } else {
    stringBuffer.append(TEXT_154);
    stringBuffer.append(createFeature.getGenPackage().getImportedPackageInterfaceName());
    stringBuffer.append(TEXT_155);
    stringBuffer.append(createFeature.getFeatureAccessorName());
    stringBuffer.append(TEXT_156);
    if (createFeature.isEnumBasedType()) {
    stringBuffer.append(TEXT_157);
    stringBuffer.append(createFeature.getGenEnumType().getStaticValue(createFeature.getEcoreFeature().getDefaultValueLiteral()));
    stringBuffer.append(TEXT_158);
    } else if (createFeature.isStringBasedType()) {
    stringBuffer.append(TEXT_159);
    stringBuffer.append(createFeature.getCreateChildValueLiteral());
    stringBuffer.append(TEXT_160);
    stringBuffer.append(genModel.getNonNLS());
    } else { String literal = createFeature.getCreateChildValueLiteral(); GenDataType createDataType = createFeature.getGenDataTypeType();
    stringBuffer.append(TEXT_161);
    stringBuffer.append(createDataType.getGenPackage().getImportedFactoryInterfaceName());
    stringBuffer.append(TEXT_162);
    stringBuffer.append(createDataType.getGenPackage().getImportedPackageInterfaceName());
    stringBuffer.append(TEXT_163);
    stringBuffer.append(createDataType.getClassifierAccessorName());
    stringBuffer.append(TEXT_164);
    stringBuffer.append(literal);
    stringBuffer.append(TEXT_165);
    if (literal != null) {
    stringBuffer.append(genModel.getNonNLS());
    } else {
    stringBuffer.append(TEXT_166);
    }
    }
    }
    }
    stringBuffer.append(TEXT_167);
    if (!genClass.getSharedClassCreateChildFeatures().isEmpty()) {
    stringBuffer.append(TEXT_168);
    stringBuffer.append(genModel.getImportedName("java.util.Collection"));
    stringBuffer.append(TEXT_169);
    if (genClass.hasFeatureMapCreateChildFeatures()) {
    stringBuffer.append(TEXT_170);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.EStructuralFeature"));
    stringBuffer.append(TEXT_171);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.FeatureMapUtil"));
    stringBuffer.append(TEXT_172);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.FeatureMap"));
    stringBuffer.append(TEXT_173);
    }
    stringBuffer.append(TEXT_174);
    for (Iterator i = genClass.getSharedClassCreateChildFeatures().iterator(); i.hasNext();) { GenFeature createFeature = (GenFeature)i.next();
    stringBuffer.append(TEXT_175);
    stringBuffer.append(createFeature.getGenPackage().getImportedPackageInterfaceName());
    stringBuffer.append(TEXT_176);
    stringBuffer.append(createFeature.getFeatureAccessorName());
    stringBuffer.append(TEXT_177);
    stringBuffer.append(i.hasNext() ? " ||" : ";");
    }
    stringBuffer.append(TEXT_178);
    stringBuffer.append(genModel.getNonNLS());
    stringBuffer.append(TEXT_179);
    }
    if (!genModel.isCreationIcons() && genClass.isModelRoot()) {
    stringBuffer.append(TEXT_180);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.EStructuralFeature"));
    stringBuffer.append(TEXT_181);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.FeatureMapUtil"));
    stringBuffer.append(TEXT_182);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.EStructuralFeature"));
    stringBuffer.append(TEXT_183);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.FeatureMap"));
    stringBuffer.append(TEXT_184);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.util.FeatureMap"));
    stringBuffer.append(TEXT_185);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.EReference"));
    stringBuffer.append(TEXT_186);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.EObject"));
    stringBuffer.append(TEXT_187);
    stringBuffer.append(genModel.getNonNLS());
    stringBuffer.append(TEXT_188);
    stringBuffer.append(genClass.getGenPackage().getImportedEditPluginClassName());
    stringBuffer.append(TEXT_189);
    }
    }
    stringBuffer.append(TEXT_190);
    stringBuffer.append(genPackage.getImportedEditPluginClassName());
    stringBuffer.append(TEXT_191);
    stringBuffer.append(TEXT_192);
    genModel.emitSortedImports();
    stringBuffer.append(TEXT_193);
    return stringBuffer.toString();
  }
}
