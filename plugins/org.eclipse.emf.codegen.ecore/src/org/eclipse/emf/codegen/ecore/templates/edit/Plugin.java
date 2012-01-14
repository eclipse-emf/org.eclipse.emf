package org.eclipse.emf.codegen.ecore.templates.edit;

import java.util.*;
import org.eclipse.emf.codegen.ecore.genmodel.*;

public class Plugin
{
  protected static String nl;
  public static synchronized Plugin create(String lineSeparator)
  {
    nl = lineSeparator;
    Plugin result = new Plugin();
    nl = null;
    return result;
  }

  public final String NL = nl == null ? (System.getProperties().getProperty("line.separator")) : nl;
  protected final String TEXT_1 = "";
  protected final String TEXT_2 = "/**";
  protected final String TEXT_3 = NL + " * ";
  protected final String TEXT_4 = NL + " */" + NL + "package ";
  protected final String TEXT_5 = ";" + NL;
  protected final String TEXT_6 = NL + NL + "/**" + NL + " * This is the central singleton for the ";
  protected final String TEXT_7 = " edit plugin." + NL + " * <!-- begin-user-doc -->" + NL + " * <!-- end-user-doc -->" + NL + " * @generated" + NL + " */" + NL + "public final class ";
  protected final String TEXT_8 = " extends EMFPlugin" + NL + "{";
  protected final String TEXT_9 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */" + NL + "\tpublic static final ";
  protected final String TEXT_10 = " copyright = ";
  protected final String TEXT_11 = ";";
  protected final String TEXT_12 = NL;
  protected final String TEXT_13 = NL + "\t/**" + NL + "\t * Keep track of the singleton." + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */" + NL + "\tpublic static final ";
  protected final String TEXT_14 = " INSTANCE = new ";
  protected final String TEXT_15 = "();" + NL;
  protected final String TEXT_16 = NL + "\t/**" + NL + "\t * Keep track of the singleton." + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */" + NL + "\tprivate static Implementation plugin;" + NL;
  protected final String TEXT_17 = NL + "\t/**" + NL + "\t * Create the instance." + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */" + NL + "\tpublic ";
  protected final String TEXT_18 = "()" + NL + "\t{" + NL + "\t\tsuper" + NL + "\t\t  (new ResourceLocator [] " + NL + "\t\t   {";
  protected final String TEXT_19 = NL + "\t\t     ";
  protected final String TEXT_20 = ".INSTANCE,";
  protected final String TEXT_21 = NL + "\t\t   });" + NL + "\t}" + NL + "" + NL + "\t/**" + NL + "\t * Returns the singleton instance of the Eclipse plugin." + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @return the singleton instance." + NL + "\t * @generated" + NL + "\t */";
  protected final String TEXT_22 = NL + "\t@Override";
  protected final String TEXT_23 = NL + "\tpublic ResourceLocator getPluginResourceLocator()" + NL + "\t{";
  protected final String TEXT_24 = NL + "\t\treturn null;";
  protected final String TEXT_25 = NL + "\t\treturn plugin;";
  protected final String TEXT_26 = NL + "\t}" + NL;
  protected final String TEXT_27 = NL + "\t/**" + NL + "\t * Returns the singleton instance of the Eclipse plugin." + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @return the singleton instance." + NL + "\t * @generated" + NL + "\t */" + NL + "\tpublic static Implementation getPlugin()" + NL + "\t{" + NL + "\t\treturn plugin;" + NL + "\t}" + NL + "" + NL + "\t/**" + NL + "\t * The actual implementation of the Eclipse <b>Plugin</b>." + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */" + NL + "\tpublic static class Implementation extends EclipsePlugin" + NL + "\t{" + NL + "\t\t/**" + NL + "\t\t * Creates an instance." + NL + "\t\t * <!-- begin-user-doc -->" + NL + "\t\t * <!-- end-user-doc -->";
  protected final String TEXT_28 = NL + "\t\t * @param descriptor the description of the plugin.";
  protected final String TEXT_29 = NL + "\t\t * @generated" + NL + "\t\t */" + NL + "\t\tpublic Implementation(";
  protected final String TEXT_30 = " descriptor";
  protected final String TEXT_31 = ")" + NL + "\t\t{" + NL + "\t\t\tsuper(";
  protected final String TEXT_32 = "descriptor";
  protected final String TEXT_33 = ");" + NL + "" + NL + "\t\t\t// Remember the static instance." + NL + "\t\t\t//" + NL + "\t\t\tplugin = this;" + NL + "\t\t}" + NL + "\t}" + NL;
  protected final String TEXT_34 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */" + NL + "\tprotected static final ";
  protected final String TEXT_35 = " PROPERTIES = ";
  protected final String TEXT_36 = ".create(";
  protected final String TEXT_37 = ".class);" + NL + "" + NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */" + NL + "\t@Override" + NL + "\tpublic String getString(String key, boolean translate)" + NL + "\t{";
  protected final String TEXT_38 = NL + "\t\t";
  protected final String TEXT_39 = "else ";
  protected final String TEXT_40 = "if (\"_UI_";
  protected final String TEXT_41 = "_type\".equals(key)) return PROPERTIES.";
  protected final String TEXT_42 = "Type();";
  protected final String TEXT_43 = NL + "\t\t";
  protected final String TEXT_44 = "else ";
  protected final String TEXT_45 = " if (\"_UI_Unknown_type\".equals(key)) return PROPERTIES.unknownType();" + NL + "\t\telse if (\"_UI_Unknown_datatype\".equals(key)) return PROPERTIES.unknownDatatype();";
  protected final String TEXT_46 = NL + "\t\telse if (\"_UI_";
  protected final String TEXT_47 = "_";
  protected final String TEXT_48 = "_feature\".equals(key)) return PROPERTIES.";
  protected final String TEXT_49 = "_";
  protected final String TEXT_50 = "Feature();";
  protected final String TEXT_51 = NL + "\t\telse if (\"_UI_";
  protected final String TEXT_52 = "_";
  protected final String TEXT_53 = "_description\".equals(key)) return PROPERTIES.";
  protected final String TEXT_54 = "_";
  protected final String TEXT_55 = "Description();";
  protected final String TEXT_56 = NL + "\t\telse if (\"_UI_Unknown_feature\".equals(key)) return PROPERTIES.unknownFeature();";
  protected final String TEXT_57 = NL + "\t\telse if (\"_UI_";
  protected final String TEXT_58 = "_";
  protected final String TEXT_59 = "_literal\".equals(key)) return PROPERTIES.";
  protected final String TEXT_60 = "_";
  protected final String TEXT_61 = "Literal();";
  protected final String TEXT_62 = NL;
  protected final String TEXT_63 = " = ";
  protected final String TEXT_64 = NL + "\t\telse if (\"";
  protected final String TEXT_65 = "\".equals(key)) return PROPERTIES.";
  protected final String TEXT_66 = "();";
  protected final String TEXT_67 = NL + "\t\telse return key;" + NL + "\t}" + NL + "" + NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */" + NL + "\t@Override" + NL + "\tpublic String getString(String key, Object [] substitutions, boolean translate)" + NL + "\t{";
  protected final String TEXT_68 = NL + "\t\tif (\"_UI_CreateChild_text\".equals(key)) return PROPERTIES.createChildText(substitutions[0]);" + NL + "\t\telse if (\"_UI_CreateChild_text2\".equals(key)) return PROPERTIES.createChildText2(substitutions[0], substitutions[1]);" + NL + "\t\telse if (\"_UI_CreateChild_text3\".equals(key)) return PROPERTIES.createChildText3(substitutions[1]);" + NL + "\t\telse if (\"_UI_CreateChild_tooltip\".equals(key)) return PROPERTIES.createChildTooltip(substitutions[0], substitutions[1]);" + NL + "\t\telse if (\"_UI_CreateChild_description\".equals(key)) return PROPERTIES.createChildDescripition(substitutions[0], substitutions[1], substitutions[2]);" + NL + "\t\telse if (\"_UI_CreateSibling_description\".equals(key)) return PROPERTIES.createSiblingDescription(substitutions[0], substitutions[1], substitutions[2]);";
  protected final String TEXT_69 = NL + "\t\t";
  protected final String TEXT_70 = "else ";
  protected final String TEXT_71 = "if (\"_UI_PropertyDescriptor_description\".equals(key)) return PROPERTIES.propertyDescriptorDescription(substitutions[0], substitutions[1]);" + NL + "\t\telse return key;" + NL + "\t}" + NL + "" + NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */" + NL + "\tprotected static final ";
  protected final String TEXT_72 = " IMAGES = ";
  protected final String TEXT_73 = ".create(";
  protected final String TEXT_74 = ".class);" + NL + "" + NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */" + NL + "\t@Override" + NL + "\tpublic Object getImage(String key)" + NL + "\t{";
  protected final String TEXT_75 = NL + "\t\t";
  protected final String TEXT_76 = "else ";
  protected final String TEXT_77 = "if (\"";
  protected final String TEXT_78 = "\".equals(key)) return IMAGES.";
  protected final String TEXT_79 = "();";
  protected final String TEXT_80 = NL + "\t\t";
  protected final String TEXT_81 = "else ";
  protected final String TEXT_82 = "return key;" + NL + "\t}" + NL;
  protected final String TEXT_83 = NL + "}";
  protected final String TEXT_84 = NL;

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

    GenModel genModel = (GenModel)argument; /* Trick to import java.util.* without warnings */Iterator.class.getName();
    stringBuffer.append(TEXT_1);
    stringBuffer.append(TEXT_2);
    {GenBase copyrightHolder = argument instanceof GenBase ? (GenBase)argument : argument instanceof Object[] && ((Object[])argument)[0] instanceof GenBase ? (GenBase)((Object[])argument)[0] : null;
    if (copyrightHolder != null && copyrightHolder.hasCopyright()) {
    stringBuffer.append(TEXT_3);
    stringBuffer.append(copyrightHolder.getCopyright(copyrightHolder.getGenModel().getIndentation(stringBuffer)));
    }}
    stringBuffer.append(TEXT_4);
    stringBuffer.append(genModel.getEditPluginPackageName());
    stringBuffer.append(TEXT_5);
    genModel.addImport("org.eclipse.emf.common.EMFPlugin");
    genModel.addImport("org.eclipse.emf.common.util.ResourceLocator");
    genModel.markImportLocation(stringBuffer);
    stringBuffer.append(TEXT_6);
    stringBuffer.append(genModel.getModelName());
    stringBuffer.append(TEXT_7);
    stringBuffer.append(genModel.getEditPluginClassName());
    stringBuffer.append(TEXT_8);
    if (genModel.hasCopyrightField()) {
    stringBuffer.append(TEXT_9);
    stringBuffer.append(genModel.getImportedName("java.lang.String"));
    stringBuffer.append(TEXT_10);
    stringBuffer.append(genModel.getCopyrightFieldLiteral());
    stringBuffer.append(TEXT_11);
    stringBuffer.append(genModel.getNonNLS());
    stringBuffer.append(TEXT_12);
    }
    stringBuffer.append(TEXT_13);
    stringBuffer.append(genModel.getEditPluginClassName());
    stringBuffer.append(TEXT_14);
    stringBuffer.append(genModel.getEditPluginClassName());
    stringBuffer.append(TEXT_15);
    if (genModel.getRuntimePlatform() != GenRuntimePlatform.GWT) {
    stringBuffer.append(TEXT_16);
    }
    stringBuffer.append(TEXT_17);
    stringBuffer.append(genModel.getEditPluginClassName());
    stringBuffer.append(TEXT_18);
    for (String pluginClassName : genModel.getEditResourceDelegateImportedPluginClassNames()) {
    stringBuffer.append(TEXT_19);
    stringBuffer.append(pluginClassName);
    stringBuffer.append(TEXT_20);
    }
    stringBuffer.append(TEXT_21);
    if (genModel.useClassOverrideAnnotation()) {
    stringBuffer.append(TEXT_22);
    }
    stringBuffer.append(TEXT_23);
    if (genModel.getRuntimePlatform() == GenRuntimePlatform.GWT) {
    stringBuffer.append(TEXT_24);
    } else {
    stringBuffer.append(TEXT_25);
    }
    stringBuffer.append(TEXT_26);
    if (genModel.getRuntimePlatform() != GenRuntimePlatform.GWT) {
    stringBuffer.append(TEXT_27);
    if (genModel.needsRuntimeCompatibility()) {
    stringBuffer.append(TEXT_28);
    }
    stringBuffer.append(TEXT_29);
    if (genModel.needsRuntimeCompatibility()) {
    stringBuffer.append(genModel.getImportedName("org.eclipse.core.runtime.IPluginDescriptor"));
    stringBuffer.append(TEXT_30);
    }
    stringBuffer.append(TEXT_31);
    if (genModel.needsRuntimeCompatibility()) {
    stringBuffer.append(TEXT_32);
    }
    stringBuffer.append(TEXT_33);
    } else {
    stringBuffer.append(TEXT_34);
    stringBuffer.append(genModel.getImportedName(genModel.getQualifiedEditPluginClassName() + "Properties"));
    stringBuffer.append(TEXT_35);
    stringBuffer.append(genModel.getImportedName("com.google.gwt.core.client.GWT"));
    stringBuffer.append(TEXT_36);
    stringBuffer.append(genModel.getImportedName(genModel.getQualifiedEditPluginClassName() + "Properties"));
    stringBuffer.append(TEXT_37);
    boolean first = true; for (GenPackage genPackage : genModel.getAllGenAndUsedGenPackagesWithClassifiers()) {
    if (genPackage.getGenModel() == genModel || !genPackage.getGenModel().hasEditSupport()) { 
    for (GenClass genClass : genPackage.getGenClasses()) {
    stringBuffer.append(TEXT_38);
    if (first) { first = false; } else {
    stringBuffer.append(TEXT_39);
    }
    stringBuffer.append(TEXT_40);
    stringBuffer.append(genClass.getName());
    stringBuffer.append(TEXT_41);
    stringBuffer.append(genClass.getUncapName());
    stringBuffer.append(TEXT_42);
    }
    }
    }
    stringBuffer.append(TEXT_43);
    if (first) { first = false; } else {
    stringBuffer.append(TEXT_44);
    }
    stringBuffer.append(TEXT_45);
    for (GenFeature genFeature : genModel.getFilteredAllGenFeatures()) { String description = genFeature.getPropertyDescription();
    stringBuffer.append(TEXT_46);
    stringBuffer.append(genFeature.getGenClass().getName());
    stringBuffer.append(TEXT_47);
    stringBuffer.append(genFeature.getName());
    stringBuffer.append(TEXT_48);
    stringBuffer.append(genFeature.getGenClass().getUncapName());
    stringBuffer.append(TEXT_49);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_50);
    if (description != null && description.length() > 0) {
    stringBuffer.append(TEXT_51);
    stringBuffer.append(genFeature.getGenClass().getName());
    stringBuffer.append(TEXT_52);
    stringBuffer.append(genFeature.getName());
    stringBuffer.append(TEXT_53);
    stringBuffer.append(genFeature.getGenClass().getUncapName());
    stringBuffer.append(TEXT_54);
    stringBuffer.append(genFeature.getCapName());
    stringBuffer.append(TEXT_55);
    }
    }
    stringBuffer.append(TEXT_56);
    for (GenPackage genPackage : genModel.getAllGenAndUsedGenPackagesWithClassifiers()) {
    if (genPackage.getGenModel() == genModel || !genPackage.getGenModel().hasEditSupport()) {
    for (GenEnum genEnum : genPackage.getGenEnums()) {
    for (GenEnumLiteral genEnumLiteral : genEnum.getGenEnumLiterals()) {
    stringBuffer.append(TEXT_57);
    stringBuffer.append(genEnum.getName());
    stringBuffer.append(TEXT_58);
    stringBuffer.append(genEnumLiteral.getName());
    stringBuffer.append(TEXT_59);
    stringBuffer.append(genEnum.getSafeUncapName());
    stringBuffer.append(TEXT_60);
    stringBuffer.append(genEnumLiteral.getName());
    stringBuffer.append(TEXT_61);
    }
    }
    }
    }
    for (String category : genModel.getPropertyCategories()) {
    stringBuffer.append(TEXT_62);
    stringBuffer.append(genModel.getPropertyCategoryKey(category));
    stringBuffer.append(TEXT_63);
    stringBuffer.append(category);
    stringBuffer.append(TEXT_64);
    stringBuffer.append(genModel.getPropertyCategoryKey(category));
    stringBuffer.append(TEXT_65);
    stringBuffer.append(genModel.getPropertyCategoryKey(category));
    stringBuffer.append(TEXT_66);
    }
    stringBuffer.append(TEXT_67);
    if (genModel.isCreationCommands()) {
    stringBuffer.append(TEXT_68);
    }
    stringBuffer.append(TEXT_69);
    if (!genModel.isCreationCommands()) {
    stringBuffer.append(TEXT_70);
    }
    stringBuffer.append(TEXT_71);
    stringBuffer.append(genModel.getImportedName(genModel.getQualifiedEditPluginClassName() + "Images"));
    stringBuffer.append(TEXT_72);
    stringBuffer.append(genModel.getImportedName("com.google.gwt.core.client.GWT"));
    stringBuffer.append(TEXT_73);
    stringBuffer.append(genModel.getImportedName(genModel.getQualifiedEditPluginClassName() + "Images"));
    stringBuffer.append(TEXT_74);
    first = true; for (GenPackage genPackage : genModel.getAllGenAndUsedGenPackagesWithClassifiers()) {
    for (GenClass genClass : genPackage.getGenClasses()) {
    if (genClass.isImage()) { String image = genClass.getItemIconFileName(); image = image.substring(image.lastIndexOf("/icons/") + 7, image.length() - 4); 
    stringBuffer.append(TEXT_75);
    if (first) { first = false; } else {
    stringBuffer.append(TEXT_76);
    }
    stringBuffer.append(TEXT_77);
    stringBuffer.append(image);
    stringBuffer.append(TEXT_78);
    stringBuffer.append(genClass.getItemIconAccessorName());
    stringBuffer.append(TEXT_79);
    }
    }
    }
    stringBuffer.append(TEXT_80);
    if (first) { first = false; } else {
    stringBuffer.append(TEXT_81);
    }
    stringBuffer.append(TEXT_82);
    }
    stringBuffer.append(TEXT_83);
    genModel.emitSortedImports();
    stringBuffer.append(TEXT_84);
    return stringBuffer.toString();
  }
}
