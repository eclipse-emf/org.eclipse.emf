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
  protected final String TEXT_6 = NL + NL + "/**" + NL + " * This is the item provider adpater for a {@link ";
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
  protected final String TEXT_21 = "PropertyDescriptor(Object object)" + NL + "\t{" + NL + "\t\titemPropertyDescriptors.add" + NL + "\t\t\t(new ";
  protected final String TEXT_22 = NL + "\t\t\t\t(((";
  protected final String TEXT_23 = ")adapterFactory).getRootAdapterFactory()," + NL + "\t\t\t\t getString(\"_UI_";
  protected final String TEXT_24 = "_";
  protected final String TEXT_25 = "_feature\"),";
  protected final String TEXT_26 = NL + "\t\t\t\t getString(\"_UI_PropertyDescriptor_description\", \"_UI_";
  protected final String TEXT_27 = "_";
  protected final String TEXT_28 = "_feature\", \"_UI_";
  protected final String TEXT_29 = "_type\"),";
  protected final String TEXT_30 = NL + "\t\t\t\t ";
  protected final String TEXT_31 = ".eINSTANCE.get";
  protected final String TEXT_32 = "(),";
  protected final String TEXT_33 = NL + "\t\t\t\t ";
  protected final String TEXT_34 = "));";
  protected final String TEXT_35 = NL + "\t\t\t\t ";
  protected final String TEXT_36 = "," + NL + "\t\t\t\t ";
  protected final String TEXT_37 = ".";
  protected final String TEXT_38 = "));";
  protected final String TEXT_39 = NL + "\t}" + NL;
  protected final String TEXT_40 = NL + "\t/**" + NL + "\t * This specifies how to implement {@link #getChildren} " + NL + "\t * and {@link org.eclipse.emf.edit.command.AddCommand} and {@link org.eclipse.emf.edit.command.RemoveCommand} " + NL + "\t * support in {@link #createCommand}." + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */" + NL + "\tpublic ";
  protected final String TEXT_41 = " getChildrenFeatures(Object object)" + NL + "\t{" + NL + "\t\tif (childrenFeatures == null)" + NL + "\t\t{" + NL + "\t\t\tsuper.getChildrenFeatures(object);";
  protected final String TEXT_42 = NL + "\t\t\tchildrenFeatures.add(";
  protected final String TEXT_43 = ".eINSTANCE.get";
  protected final String TEXT_44 = "());";
  protected final String TEXT_45 = NL + "\t\t}" + NL + "\t\treturn childrenFeatures;" + NL + "\t}" + NL;
  protected final String TEXT_46 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */" + NL + "\tprotected ";
  protected final String TEXT_47 = " getChildFeature(Object object, Object child)" + NL + "\t{" + NL + "\t\t// Check the type of the specified child object and return the proper feature to use for" + NL + "\t\t// adding (see {@link AddCommand}) it as a child." + NL + "" + NL + "\t\treturn super.getChildFeature(object, child);" + NL + "\t}" + NL;
  protected final String TEXT_48 = NL + NL + "\t/**" + NL + "\t * This returns ";
  protected final String TEXT_49 = ".gif." + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */" + NL + "\tpublic Object getImage(Object object)" + NL + "\t{" + NL + "\t\treturn getResourceLocator().getImage(\"full/obj16/";
  protected final String TEXT_50 = "\");";
  protected final String TEXT_51 = NL + "\t}";
  protected final String TEXT_52 = NL + NL + "\t/**" + NL + "\t * This returns the label text for the adapted class." + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */" + NL + "\tpublic String getText(Object object)" + NL + "\t{";
  protected final String TEXT_53 = NL + "\t\t";
  protected final String TEXT_54 = " ";
  protected final String TEXT_55 = " = (";
  protected final String TEXT_56 = ")object;" + NL + "\t\treturn \"\" + ";
  protected final String TEXT_57 = ".getKey() + \" -> \" + ";
  protected final String TEXT_58 = ".getValue();";
  protected final String TEXT_59 = NL + "\t\t";
  protected final String TEXT_60 = " ";
  protected final String TEXT_61 = " = (";
  protected final String TEXT_62 = ")object;" + NL + "\t\treturn getString(\"_UI_";
  protected final String TEXT_63 = "_type\") + \" \" + ";
  protected final String TEXT_64 = ".";
  protected final String TEXT_65 = "();";
  protected final String TEXT_66 = NL + "\t\tString label = ((";
  protected final String TEXT_67 = ")object).";
  protected final String TEXT_68 = "();";
  protected final String TEXT_69 = NL + "\t\t";
  protected final String TEXT_70 = " labelValue = ((";
  protected final String TEXT_71 = ")object).";
  protected final String TEXT_72 = "();" + NL + "\t\tString label = labelValue == null ? null : labelValue.toString();";
  protected final String TEXT_73 = NL + "\t\treturn label == null || label.length() == 0 ?" + NL + "\t\t\tgetString(\"_UI_";
  protected final String TEXT_74 = "_type\") :";
  protected final String TEXT_75 = NL + "\t\t\tgetString(\"_UI_";
  protected final String TEXT_76 = "_type\") + \" \" + label;";
  protected final String TEXT_77 = NL + "\t\treturn getString(\"_UI_";
  protected final String TEXT_78 = "_type\");";
  protected final String TEXT_79 = NL + "\t}" + NL + "" + NL + "\t/**" + NL + "\t * This handles model notifications by calling {@link #updateChildren} to update any cached" + NL + "\t * children and by creating a viewer notification, which it passes to {@link #fireNotifyChanged}." + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */" + NL + "\tpublic void notifyChanged(Notification notification)" + NL + "\t{" + NL + "\t\tupdateChildren(notification);";
  protected final String TEXT_80 = NL + NL + "\t\tswitch (notification.getFeatureID(";
  protected final String TEXT_81 = ".class))" + NL + "\t\t{";
  protected final String TEXT_82 = NL + "\t\t\tcase ";
  protected final String TEXT_83 = ":";
  protected final String TEXT_84 = NL + "\t\t\t\tfireNotifyChanged(new ";
  protected final String TEXT_85 = "(notification, notification.getNotifier()));" + NL + "\t\t\t\treturn;";
  protected final String TEXT_86 = NL + "\t\t\tcase ";
  protected final String TEXT_87 = ":";
  protected final String TEXT_88 = NL + "\t\t\t\tfireNotifyChanged(new ";
  protected final String TEXT_89 = "(notification, notification.getNotifier(), true, false));" + NL + "\t\t\t\treturn;";
  protected final String TEXT_90 = NL + "\t\t\tcase ";
  protected final String TEXT_91 = ":";
  protected final String TEXT_92 = NL + "\t\t\t\tfireNotifyChanged(new ";
  protected final String TEXT_93 = "(notification, notification.getNotifier(), true, true));" + NL + "\t\t\t\treturn;";
  protected final String TEXT_94 = NL + "\t\t}";
  protected final String TEXT_95 = NL + "\t\tsuper.notifyChanged(notification);" + NL + "\t}";
  protected final String TEXT_96 = NL + NL + "\t/**" + NL + "\t * This adds to the collection of {@link org.eclipse.emf.edit.command.CommandParameter}s" + NL + "\t * describing all of the children that can be created under this object." + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */" + NL + "\tprotected void collectNewChildDescriptors(";
  protected final String TEXT_97 = " newChildDescriptors, Object object)" + NL + "\t{" + NL + "\t\tsuper.collectNewChildDescriptors(newChildDescriptors, object);";
  protected final String TEXT_98 = NL + NL + "\t\tnewChildDescriptors.add" + NL + "\t\t\t(createChildParameter" + NL + "\t\t\t\t(";
  protected final String TEXT_99 = ".eINSTANCE.get";
  protected final String TEXT_100 = "(),";
  protected final String TEXT_101 = NL + "\t\t\t\t ";
  protected final String TEXT_102 = ".eINSTANCE.create(";
  protected final String TEXT_103 = ".eINSTANCE.get";
  protected final String TEXT_104 = "())));";
  protected final String TEXT_105 = NL + "\t\t\t\t ";
  protected final String TEXT_106 = ".eINSTANCE.create";
  protected final String TEXT_107 = "()));";
  protected final String TEXT_108 = NL + NL + "\t\tnewChildDescriptors.add" + NL + "\t\t\t(createChildParameter" + NL + "\t\t\t\t(";
  protected final String TEXT_109 = ".eINSTANCE.get";
  protected final String TEXT_110 = "(),";
  protected final String TEXT_111 = NL + "\t\t\t\t ";
  protected final String TEXT_112 = ".eINSTANCE.create(";
  protected final String TEXT_113 = ".eINSTANCE.get";
  protected final String TEXT_114 = "())));";
  protected final String TEXT_115 = NL + "\t\t\t\t ";
  protected final String TEXT_116 = ".eINSTANCE.create";
  protected final String TEXT_117 = "()));";
  protected final String TEXT_118 = NL + "\t}";
  protected final String TEXT_119 = NL + NL + "\t/**" + NL + "\t * This returns the label text for {@link org.eclipse.emf.edit.command.CreateChildCommand}." + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */" + NL + "\tpublic String getCreateChildText(Object owner, Object feature, Object child, ";
  protected final String TEXT_120 = " selection)" + NL + "\t{" + NL + "\t\tboolean qualify =";
  protected final String TEXT_121 = NL + "\t\t\tfeature == ";
  protected final String TEXT_122 = ".eINSTANCE.get";
  protected final String TEXT_123 = "()";
  protected final String TEXT_124 = NL + "\t\treturn getString(" + NL + "\t\t\tqualify ? \"_UI_CreateChild_text2\" : \"_UI_CreateChild_text\",";
  protected final String TEXT_125 = NL + "\t\t\tnew Object[] { getTypeText(child), getFeatureText(feature), getTypeText(owner) });" + NL + "\t}";
  protected final String TEXT_126 = NL + NL + "\t/**" + NL + "\t * Return the resource locator for this item provider's resources." + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */" + NL + "\tpublic ResourceLocator getResourceLocator()" + NL + "\t{" + NL + "\t\treturn ";
  protected final String TEXT_127 = ".INSTANCE;" + NL + "\t}" + NL + "}";
  protected final String TEXT_128 = NL;

  public String generate(Object argument)
  {
    StringBuffer stringBuffer = new StringBuffer();
    
/**
 * <copyright>
 *
 * Copyright (c) 2002-2004 IBM Corporation and others.
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
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.edit.provider.ItemPropertyDescriptor"));
    stringBuffer.append(TEXT_22);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.edit.provider.ComposeableAdapterFactory"));
    stringBuffer.append(TEXT_23);
    stringBuffer.append(genFeature.getGenClass().getName());
    stringBuffer.append(TEXT_24);
    stringBuffer.append(genFeature.getName());
    stringBuffer.append(TEXT_25);
    stringBuffer.append(genModel.getNonNLS());
    stringBuffer.append(TEXT_26);
    stringBuffer.append(genFeature.getGenClass().getName());
    stringBuffer.append(TEXT_27);
    stringBuffer.append(genFeature.getName());
    stringBuffer.append(TEXT_28);
    stringBuffer.append(genFeature.getGenClass().getName());
    stringBuffer.append(TEXT_29);
    stringBuffer.append(genModel.getNonNLS());
    stringBuffer.append(genModel.getNonNLS(2));
    stringBuffer.append(genModel.getNonNLS(3));
    stringBuffer.append(TEXT_30);
    stringBuffer.append(genFeature.getGenPackage().getImportedPackageInterfaceName());
    stringBuffer.append(TEXT_31);
    stringBuffer.append(genFeature.getFeatureAccessorName());
    stringBuffer.append(TEXT_32);
    if (genFeature.isReferenceType()) {
    stringBuffer.append(TEXT_33);
    stringBuffer.append(genFeature.getProperty() == GenPropertyKind.EDITABLE_LITERAL ? "true" : "false");
    stringBuffer.append(TEXT_34);
    } else {
    stringBuffer.append(TEXT_35);
    stringBuffer.append(genFeature.getProperty() == GenPropertyKind.EDITABLE_LITERAL ? "true" : "false");
    stringBuffer.append(TEXT_36);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.edit.provider.ItemPropertyDescriptor"));
    stringBuffer.append(TEXT_37);
    stringBuffer.append(genFeature.getPropertyImageName());
    stringBuffer.append(TEXT_38);
    }
    stringBuffer.append(TEXT_39);
    }
    if (!genClass.getChildrenFeatures().isEmpty()) {
    stringBuffer.append(TEXT_40);
    stringBuffer.append(genModel.getImportedName("java.util.Collection"));
    stringBuffer.append(TEXT_41);
    for (Iterator i=genClass.getChildrenFeatures().iterator(); i.hasNext();) { GenFeature genFeature = (GenFeature)i.next();
    stringBuffer.append(TEXT_42);
    stringBuffer.append(genFeature.getGenPackage().getImportedPackageInterfaceName());
    stringBuffer.append(TEXT_43);
    stringBuffer.append(genFeature.getFeatureAccessorName());
    stringBuffer.append(TEXT_44);
    }
    stringBuffer.append(TEXT_45);
    if (genClass.getChildrenFeatures().size() > 1) {
    stringBuffer.append(TEXT_46);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.EStructuralFeature"));
    stringBuffer.append(TEXT_47);
    }
    }
    if (genClass.isImage()) {
    stringBuffer.append(TEXT_48);
    stringBuffer.append(genClass.getName());
    stringBuffer.append(TEXT_49);
    stringBuffer.append(genClass.getName());
    stringBuffer.append(TEXT_50);
    stringBuffer.append(genModel.getNonNLS());
    stringBuffer.append(TEXT_51);
    }
    stringBuffer.append(TEXT_52);
    if (genClass.isMapEntry()) {
    stringBuffer.append(TEXT_53);
    stringBuffer.append(genClass.getImportedInterfaceName());
    stringBuffer.append(TEXT_54);
    stringBuffer.append(genClass.getSafeUncapName());
    stringBuffer.append(TEXT_55);
    stringBuffer.append(genClass.getImportedInterfaceName());
    stringBuffer.append(TEXT_56);
    stringBuffer.append(genClass.getSafeUncapName());
    stringBuffer.append(TEXT_57);
    stringBuffer.append(genClass.getSafeUncapName());
    stringBuffer.append(TEXT_58);
    stringBuffer.append(genModel.getNonNLS());
    stringBuffer.append(genModel.getNonNLS(2));
    } else if (genClass.getLabelFeature() != null) { GenFeature labelFeature = genClass.getLabelFeature();
    if (labelFeature.isPrimitiveType()) {
    stringBuffer.append(TEXT_59);
    stringBuffer.append(genClass.getImportedInterfaceName());
    stringBuffer.append(TEXT_60);
    stringBuffer.append(genClass.getSafeUncapName());
    stringBuffer.append(TEXT_61);
    stringBuffer.append(genClass.getImportedInterfaceName());
    stringBuffer.append(TEXT_62);
    stringBuffer.append(genClass.getName());
    stringBuffer.append(TEXT_63);
    stringBuffer.append(genClass.getSafeUncapName());
    stringBuffer.append(TEXT_64);
    stringBuffer.append(genClass.getLabelFeature().getGetAccessor());
    stringBuffer.append(TEXT_65);
    stringBuffer.append(genModel.getNonNLS());
    stringBuffer.append(genModel.getNonNLS(2));
    } else {
    if (labelFeature.isStringType()) {
    stringBuffer.append(TEXT_66);
    stringBuffer.append(genClass.getImportedInterfaceName());
    stringBuffer.append(TEXT_67);
    stringBuffer.append(labelFeature.getGetAccessor());
    stringBuffer.append(TEXT_68);
    } else {
    stringBuffer.append(TEXT_69);
    stringBuffer.append(labelFeature.getImportedType());
    stringBuffer.append(TEXT_70);
    stringBuffer.append(genClass.getImportedInterfaceName());
    stringBuffer.append(TEXT_71);
    stringBuffer.append(labelFeature.getGetAccessor());
    stringBuffer.append(TEXT_72);
    }
    stringBuffer.append(TEXT_73);
    stringBuffer.append(genClass.getName());
    stringBuffer.append(TEXT_74);
    stringBuffer.append(genModel.getNonNLS());
    stringBuffer.append(TEXT_75);
    stringBuffer.append(genClass.getName());
    stringBuffer.append(TEXT_76);
    stringBuffer.append(genModel.getNonNLS());
    stringBuffer.append(genModel.getNonNLS(2));
    }
    } else {
    stringBuffer.append(TEXT_77);
    stringBuffer.append(genClass.getName());
    stringBuffer.append(TEXT_78);
    stringBuffer.append(genModel.getNonNLS());
    }
    stringBuffer.append(TEXT_79);
    if (!genClass.getLabelNotifyFeatures().isEmpty() || !genClass.getContentNotifyFeatures().isEmpty() || !genClass.getLabelAndContentNotifyFeatures().isEmpty()) {
    stringBuffer.append(TEXT_80);
    stringBuffer.append(genClass.getImportedInterfaceName());
    stringBuffer.append(TEXT_81);
    if (!genClass.getLabelNotifyFeatures().isEmpty()) {
    for (Iterator i=genClass.getLabelNotifyFeatures().iterator(); i.hasNext();) { GenFeature genFeature = (GenFeature)i.next();
    stringBuffer.append(TEXT_82);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(TEXT_83);
    }
    stringBuffer.append(TEXT_84);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.edit.provider.ViewerNotification"));
    stringBuffer.append(TEXT_85);
    }
    if (!genClass.getContentNotifyFeatures().isEmpty()) {
    for (Iterator i=genClass.getContentNotifyFeatures().iterator(); i.hasNext();) { GenFeature genFeature = (GenFeature)i.next();
    stringBuffer.append(TEXT_86);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(TEXT_87);
    }
    stringBuffer.append(TEXT_88);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.edit.provider.ViewerNotification"));
    stringBuffer.append(TEXT_89);
    }
    if (!genClass.getLabelAndContentNotifyFeatures().isEmpty()) {
    for (Iterator i=genClass.getLabelAndContentNotifyFeatures().iterator(); i.hasNext();) { GenFeature genFeature = (GenFeature)i.next();
    stringBuffer.append(TEXT_90);
    stringBuffer.append(genClass.getQualifiedFeatureID(genFeature));
    stringBuffer.append(TEXT_91);
    }
    stringBuffer.append(TEXT_92);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.edit.provider.ViewerNotification"));
    stringBuffer.append(TEXT_93);
    }
    stringBuffer.append(TEXT_94);
    }
    stringBuffer.append(TEXT_95);
    if (genModel.isCreationCommands()) {
    stringBuffer.append(TEXT_96);
    stringBuffer.append(genModel.getImportedName("java.util.Collection"));
    stringBuffer.append(TEXT_97);
    for (Iterator i=genClass.getCrossPackageCreateChildFeatures().iterator(); i.hasNext();) { GenFeature createFeature = (GenFeature)i.next();
    for (Iterator c=genClass.getCrossPackageChildrenClasses(createFeature).iterator(); c.hasNext();) { GenClass createClass = (GenClass)c.next();
    stringBuffer.append(TEXT_98);
    stringBuffer.append(createFeature.getGenPackage().getImportedPackageInterfaceName());
    stringBuffer.append(TEXT_99);
    stringBuffer.append(createFeature.getFeatureAccessorName());
    stringBuffer.append(TEXT_100);
    if (createClass.isMapEntry()) { 
    stringBuffer.append(TEXT_101);
    stringBuffer.append(createClass.getGenPackage().getImportedFactoryInterfaceName());
    stringBuffer.append(TEXT_102);
    stringBuffer.append(createClass.getGenPackage().getImportedPackageInterfaceName());
    stringBuffer.append(TEXT_103);
    stringBuffer.append(createClass.getName());
    stringBuffer.append(TEXT_104);
    } else {
    stringBuffer.append(TEXT_105);
    stringBuffer.append(createClass.getGenPackage().getImportedFactoryInterfaceName());
    stringBuffer.append(TEXT_106);
    stringBuffer.append(createClass.getName());
    stringBuffer.append(TEXT_107);
    }
    }
    }
    for (Iterator i=genClass.getCreateChildFeatures().iterator(); i.hasNext();) { GenFeature createFeature = (GenFeature)i.next();
    for (Iterator c=genClass.getChildrenClasses(createFeature).iterator(); c.hasNext();) { GenClass createClass = (GenClass)c.next();
    stringBuffer.append(TEXT_108);
    stringBuffer.append(createFeature.getGenPackage().getImportedPackageInterfaceName());
    stringBuffer.append(TEXT_109);
    stringBuffer.append(createFeature.getFeatureAccessorName());
    stringBuffer.append(TEXT_110);
    if (createClass.isMapEntry()) { 
    stringBuffer.append(TEXT_111);
    stringBuffer.append(createClass.getGenPackage().getImportedFactoryInterfaceName());
    stringBuffer.append(TEXT_112);
    stringBuffer.append(createClass.getGenPackage().getImportedPackageInterfaceName());
    stringBuffer.append(TEXT_113);
    stringBuffer.append(createClass.getName());
    stringBuffer.append(TEXT_114);
    } else {
    stringBuffer.append(TEXT_115);
    stringBuffer.append(createClass.getGenPackage().getImportedFactoryInterfaceName());
    stringBuffer.append(TEXT_116);
    stringBuffer.append(createClass.getName());
    stringBuffer.append(TEXT_117);
    }
    }
    }
    stringBuffer.append(TEXT_118);
    if (!genClass.getSharedClassCreateChildFeatures().isEmpty()) {
    stringBuffer.append(TEXT_119);
    stringBuffer.append(genModel.getImportedName("java.util.Collection"));
    stringBuffer.append(TEXT_120);
    for (Iterator i = genClass.getSharedClassCreateChildFeatures().iterator(); i.hasNext();) { GenFeature createFeature = (GenFeature)i.next();
    stringBuffer.append(TEXT_121);
    stringBuffer.append(createFeature.getGenPackage().getImportedPackageInterfaceName());
    stringBuffer.append(TEXT_122);
    stringBuffer.append(createFeature.getFeatureAccessorName());
    stringBuffer.append(TEXT_123);
    stringBuffer.append(i.hasNext() ? " ||" : ";");
    }
    stringBuffer.append(TEXT_124);
    stringBuffer.append(genModel.getNonNLS());
    stringBuffer.append(genModel.getNonNLS(2));
    stringBuffer.append(TEXT_125);
    }
    }
    stringBuffer.append(TEXT_126);
    stringBuffer.append(genPackage.getImportedEditPluginClassName());
    stringBuffer.append(TEXT_127);
    genModel.emitSortedImports();
    stringBuffer.append(TEXT_128);
    return stringBuffer.toString();
  }
}
