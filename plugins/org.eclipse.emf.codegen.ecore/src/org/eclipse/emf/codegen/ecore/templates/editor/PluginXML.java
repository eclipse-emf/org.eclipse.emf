package org.eclipse.emf.codegen.ecore.templates.editor;

import java.util.*;
import org.eclipse.emf.codegen.ecore.genmodel.*;

public class PluginXML
{
  protected static String nl;
  public static synchronized PluginXML create(String lineSeparator)
  {
    nl = lineSeparator;
    PluginXML result = new PluginXML();
    nl = null;
    return result;
  }

  public final String NL = nl == null ? (System.getProperties().getProperty("line.separator")) : nl;
  protected final String TEXT_1 = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>" + NL + "<?eclipse version=\"3.0\"?>" + NL;
  protected final String TEXT_2 = NL;
  protected final String TEXT_3 = "<!--";
  protected final String TEXT_4 = NL + " ";
  protected final String TEXT_5 = NL + " <copyright>" + NL + " </copyright>";
  protected final String TEXT_6 = NL;
  protected final String TEXT_7 = NL + " ";
  protected final String TEXT_8 = "Id";
  protected final String TEXT_9 = NL + "-->" + NL + "" + NL + "<plugin";
  protected final String TEXT_10 = ">";
  protected final String TEXT_11 = NL + "    name = \"%pluginName\"" + NL + "    id = \"";
  protected final String TEXT_12 = "\"" + NL + "    version = \"1.0.0\"" + NL + "    provider-name = \"%providerName\"" + NL + "    class = \"";
  protected final String TEXT_13 = "$Implementation\">" + NL + "" + NL + "  <requires>";
  protected final String TEXT_14 = NL + "    <import plugin=\"";
  protected final String TEXT_15 = "\" ";
  protected final String TEXT_16 = "export=\"true\"";
  protected final String TEXT_17 = "/>";
  protected final String TEXT_18 = NL + "  </requires>" + NL + "" + NL + "  <runtime>";
  protected final String TEXT_19 = NL + "    <library name=\"";
  protected final String TEXT_20 = ".jar\">";
  protected final String TEXT_21 = NL + "    <library name=\".\">";
  protected final String TEXT_22 = NL + "      <export name=\"*\"/>" + NL + "    </library>" + NL + "  </runtime>";
  protected final String TEXT_23 = NL;
  protected final String TEXT_24 = NL + "  <extension point=\"org.eclipse.emf.edit.itemProviderAdapterFactories\">" + NL + "    <factory " + NL + "       uri = \"";
  protected final String TEXT_25 = "\" " + NL + "       class = \"";
  protected final String TEXT_26 = "\"" + NL + "       supportedTypes = ";
  protected final String TEXT_27 = NL + "         ";
  protected final String TEXT_28 = " />" + NL + "  </extension>" + NL + "  ";
  protected final String TEXT_29 = NL + NL + "  <extension point=\"org.eclipse.emf.edit.childCreationExtenders\">";
  protected final String TEXT_30 = NL + "    <extender " + NL + "       uri = \"";
  protected final String TEXT_31 = "\" " + NL + "       class = \"";
  protected final String TEXT_32 = "$";
  protected final String TEXT_33 = "\" />";
  protected final String TEXT_34 = NL + "  </extension>";
  protected final String TEXT_35 = NL + "  <extension point=\"org.eclipse.emf.ecore.generated_package\">" + NL + "    <package" + NL + "       uri = \"";
  protected final String TEXT_36 = "\"" + NL + "       class = \"";
  protected final String TEXT_37 = "\"";
  protected final String TEXT_38 = " />";
  protected final String TEXT_39 = NL + "       genModel = \"";
  protected final String TEXT_40 = "\" /> ";
  protected final String TEXT_41 = NL + "  </extension>";
  protected final String TEXT_42 = NL + NL + "  <extension point=\"org.eclipse.emf.ecore.extension_parser\">" + NL + "    <parser" + NL + "       type=\"";
  protected final String TEXT_43 = "\"" + NL + "       class=\"";
  protected final String TEXT_44 = "\" />" + NL + "  </extension>";
  protected final String TEXT_45 = NL;
  protected final String TEXT_46 = NL + "  <extension" + NL + "    point=\"org.eclipse.core.runtime.applications\"" + NL + "    id=\"";
  protected final String TEXT_47 = "Application\">" + NL + "    <application>" + NL + "      <run" + NL + "        class=\"";
  protected final String TEXT_48 = "$Application\">" + NL + "      </run>" + NL + "    </application>" + NL + "  </extension>" + NL + "" + NL + "   <extension" + NL + "    point=\"org.eclipse.ui.perspectives\">" + NL + "    <perspective" + NL + "      name=\"%_UI_Perspective_label\"" + NL + "      class=\"";
  protected final String TEXT_49 = "$Perspective\"" + NL + "      id=\"";
  protected final String TEXT_50 = "Perspective\">" + NL + "    </perspective>" + NL + "  </extension>" + NL + "" + NL + "  <extension" + NL + "    point=\"org.eclipse.ui.commands\">" + NL + "    <command" + NL + "      name=\"%_UI_Menu_OpenURI_label\"" + NL + "      description=\"%_UI_Menu_OpenURI_description\"" + NL + "      categoryId=\"org.eclipse.ui.category.file\"" + NL + "      id=\"";
  protected final String TEXT_51 = "OpenURICommand\" />  " + NL + "    <command" + NL + "      name=\"%_UI_Menu_Open_label\"" + NL + "      description=\"%_UI_Menu_Open_description\"" + NL + "      categoryId=\"org.eclipse.ui.category.file\"" + NL + "      id=\"";
  protected final String TEXT_52 = "OpenCommand\" />  " + NL + "  </extension>" + NL + "  " + NL + "  <extension" + NL + "    point=\"org.eclipse.ui.bindings\">" + NL + "    <key" + NL + "      commandId=\"";
  protected final String TEXT_53 = "OpenURICommand\"" + NL + "      sequence=\"M1+U\"" + NL + "      schemeId=\"org.eclipse.ui.defaultAcceleratorConfiguration\" />" + NL + "    <key" + NL + "      commandId=\"";
  protected final String TEXT_54 = "OpenCommand\"" + NL + "      sequence=\"M1+O\"" + NL + "      schemeId=\"org.eclipse.ui.defaultAcceleratorConfiguration\" />" + NL + "  </extension>" + NL + "" + NL + "  <extension" + NL + "    point=\"org.eclipse.ui.actionSets\">" + NL + "    <actionSet" + NL + "      label=\"%_UI_";
  protected final String TEXT_55 = "_ActionSet_label\"" + NL + "      visible=\"true\"" + NL + "      id=\"";
  protected final String TEXT_56 = "ActionSet\">" + NL + "      <action" + NL + "        label=\"%_UI_Menu_About_label\"" + NL + "        class=\"";
  protected final String TEXT_57 = "$AboutAction\"" + NL + "        menubarPath=\"help/additions\"" + NL + "        id=\"";
  protected final String TEXT_58 = "AboutAction\">" + NL + "      </action>" + NL + "      <action" + NL + "        label=\"%_UI_Menu_OpenURI_label\"" + NL + "        definitionId=\"";
  protected final String TEXT_59 = "OpenURICommand\"" + NL + "        class=\"";
  protected final String TEXT_60 = "$OpenURIAction\"" + NL + "        menubarPath=\"file/additions\"" + NL + "        id=\"";
  protected final String TEXT_61 = "OpenURIAction\">" + NL + "      </action>" + NL + "      <action" + NL + "        label=\"%_UI_Menu_Open_label\"" + NL + "        definitionId=\"";
  protected final String TEXT_62 = "OpenCommand\"" + NL + "        class=\"";
  protected final String TEXT_63 = "$OpenAction\"" + NL + "        menubarPath=\"file/additions\"" + NL + "        id=\"";
  protected final String TEXT_64 = "OpenAction\">" + NL + "      </action>" + NL + "    </actionSet>" + NL + "  </extension>" + NL + "   ";
  protected final String TEXT_65 = NL + NL + "  <extension" + NL + "    point=\"org.eclipse.ui.actionSets\">" + NL + "    <actionSet" + NL + "      label=\"%_UI_";
  protected final String TEXT_66 = "_ActionSet_label\"" + NL + "      visible=\"true\"" + NL + "      id=\"";
  protected final String TEXT_67 = "ActionSet\">" + NL + "      <action" + NL + "        label=\"%_UI_";
  protected final String TEXT_68 = "_label\"" + NL + "        class=\"";
  protected final String TEXT_69 = "$NewAction\"" + NL + "        menubarPath=\"file/new/additions\"" + NL + "        id=\"";
  protected final String TEXT_70 = "NewAction\">" + NL + "      </action>" + NL + "    </actionSet>" + NL + "  </extension>  ";
  protected final String TEXT_71 = NL + NL + "  <extension" + NL + "    point = \"org.eclipse.ui.newWizards\">" + NL + "    <category" + NL + "      id = \"org.eclipse.emf.ecore.Wizard.category.ID\"" + NL + "      name=\"%_UI_Wizard_category\">" + NL + "    </category>" + NL + "    <wizard" + NL + "      id = \"";
  protected final String TEXT_72 = "ID\"" + NL + "      name = \"%_UI_";
  protected final String TEXT_73 = "_label\"" + NL + "      class = \"";
  protected final String TEXT_74 = "\"" + NL + "      category = \"org.eclipse.emf.ecore.Wizard.category.ID\"" + NL + "      icon = \"icons/full/obj16/";
  protected final String TEXT_75 = "ModelFile.gif\">" + NL + "      <description>%_UI_";
  protected final String TEXT_76 = "_description</description>" + NL + "      <selection class = \"org.eclipse.core.resources.IResource\" />" + NL + "    </wizard>" + NL + "  </extension>";
  protected final String TEXT_77 = NL + NL + "  <extension point = \"org.eclipse.ui.editors\">" + NL + "    <editor" + NL + "        id = \"";
  protected final String TEXT_78 = "ID\"" + NL + "        name = \"%_UI_";
  protected final String TEXT_79 = "_label\"" + NL + "        icon = \"icons/full/obj16/";
  protected final String TEXT_80 = "ModelFile.gif\"" + NL + "        extensions = \"";
  protected final String TEXT_81 = "\"" + NL + "        class = \"";
  protected final String TEXT_82 = "\" " + NL + "        contributorClass=\"";
  protected final String TEXT_83 = "\" >" + NL + "    </editor>" + NL + "  </extension>    ";
  protected final String TEXT_84 = NL + "</plugin>";
  protected final String TEXT_85 = NL;

  public String generate(Object argument)
  {
    final StringBuffer stringBuffer = new StringBuffer();
    
/**
 * <copyright>
 *
 * Copyright (c) 2002-2005 IBM Corporation and others.
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

    GenModel genModel = (GenModel)argument;
    stringBuffer.append(TEXT_1);
    stringBuffer.append(TEXT_2);
    stringBuffer.append(TEXT_3);
    {GenBase copyrightHolder = argument instanceof GenBase ? (GenBase)argument : argument instanceof Object[] && ((Object[])argument)[0] instanceof GenBase ? (GenBase)((Object[])argument)[0] : null;
    if (copyrightHolder != null && copyrightHolder.hasCopyright()) {
    stringBuffer.append(TEXT_4);
    stringBuffer.append(copyrightHolder.getCopyright(copyrightHolder.getGenModel().getIndentation(stringBuffer)));
    } else {
    stringBuffer.append(TEXT_5);
    }}
    stringBuffer.append(TEXT_6);
    stringBuffer.append(TEXT_7);
    stringBuffer.append("$");
    stringBuffer.append(TEXT_8);
    stringBuffer.append("$");
    stringBuffer.append(TEXT_9);
    if (genModel.isBundleManifest()) {
    stringBuffer.append(TEXT_10);
    } else {
    stringBuffer.append(TEXT_11);
    stringBuffer.append(genModel.getEditorPluginID());
    stringBuffer.append(TEXT_12);
    stringBuffer.append(genModel.getQualifiedEditorPluginClassName());
    stringBuffer.append(TEXT_13);
    for (String pluginID : genModel.getEditorRequiredPlugins()) { 
    stringBuffer.append(TEXT_14);
    stringBuffer.append(pluginID);
    stringBuffer.append(TEXT_15);
    if (!pluginID.startsWith("org.eclipse.core.runtime")) {
    stringBuffer.append(TEXT_16);
    }
    stringBuffer.append(TEXT_17);
    }
    stringBuffer.append(TEXT_18);
    if (genModel.isRuntimeJar()) {
    stringBuffer.append(TEXT_19);
    stringBuffer.append(genModel.getEditorPluginID());
    stringBuffer.append(TEXT_20);
    } else {
    stringBuffer.append(TEXT_21);
    }
    stringBuffer.append(TEXT_22);
    }
    stringBuffer.append(TEXT_23);
    if (genModel.sameEditEditorProject()) {
     for (GenPackage genPackage : genModel.getAllGenPackagesWithClassifiers()) {
    if (!genPackage.getGenClasses().isEmpty()) {
    stringBuffer.append(TEXT_24);
    stringBuffer.append(genPackage.getNSURI());
    stringBuffer.append(TEXT_25);
    stringBuffer.append(genPackage.getQualifiedItemProviderAdapterFactoryClassName());
    stringBuffer.append(TEXT_26);
    for (ListIterator<?> j = genPackage.getProviderSupportedTypes().listIterator(); j.hasNext(); ) {
    stringBuffer.append(TEXT_27);
    stringBuffer.append((j.hasPrevious()? " " : "\"") + j.next() + (j.hasNext() ? "" : "\""));
    }
    stringBuffer.append(TEXT_28);
    }
    if (genPackage.isChildCreationExtenders()) { Map<GenPackage, Map<GenClass, List<GenClass.ChildCreationData>>> extendedChildCreationData = genPackage.getExtendedChildCreationData();
    if (!extendedChildCreationData.isEmpty()) {
    stringBuffer.append(TEXT_29);
    for (Map.Entry<GenPackage, Map<GenClass, List<GenClass.ChildCreationData>>> entry : extendedChildCreationData.entrySet()) {
    stringBuffer.append(TEXT_30);
    stringBuffer.append(entry.getKey().getNSURI());
    stringBuffer.append(TEXT_31);
    stringBuffer.append(genPackage.getQualifiedItemProviderAdapterFactoryClassName());
    stringBuffer.append(TEXT_32);
    stringBuffer.append(genPackage.getChildCreationExtenderName(entry.getKey()));
    stringBuffer.append(TEXT_33);
    }
    stringBuffer.append(TEXT_34);
    }
    }
    }
    }
    if (genModel.sameModelEditorProject()) {
     for (GenPackage genPackage : genModel.getAllGenPackagesWithClassifiers()) {
    stringBuffer.append(TEXT_35);
    stringBuffer.append(genPackage.getNSURI());
    stringBuffer.append(TEXT_36);
    stringBuffer.append(genPackage.getQualifiedPackageInterfaceName());
    stringBuffer.append(TEXT_37);
    if (!genModel.hasLocalGenModel()) {
    stringBuffer.append(TEXT_38);
    } else {
    stringBuffer.append(TEXT_39);
    stringBuffer.append(genModel.getRelativeGenModelLocation());
    stringBuffer.append(TEXT_40);
    }
    stringBuffer.append(TEXT_41);
    if (genPackage.getResource() != GenResourceKind.NONE_LITERAL) {
    stringBuffer.append(TEXT_42);
    stringBuffer.append(genPackage.getPrefix().toLowerCase());
    stringBuffer.append(TEXT_43);
    stringBuffer.append(genPackage.getQualifiedResourceFactoryClassName());
    stringBuffer.append(TEXT_44);
    }
    stringBuffer.append(TEXT_45);
    }
    }
    if (genModel.isRichClientPlatform()) {
    stringBuffer.append(TEXT_46);
    stringBuffer.append(genModel.getEditorAdvisorClassName());
    stringBuffer.append(TEXT_47);
    stringBuffer.append(genModel.getQualifiedEditorAdvisorClassName());
    stringBuffer.append(TEXT_48);
    stringBuffer.append(genModel.getQualifiedEditorAdvisorClassName());
    stringBuffer.append(TEXT_49);
    stringBuffer.append(genModel.getQualifiedEditorAdvisorClassName());
    stringBuffer.append(TEXT_50);
    stringBuffer.append(genModel.getQualifiedEditorAdvisorClassName());
    stringBuffer.append(TEXT_51);
    stringBuffer.append(genModel.getQualifiedEditorAdvisorClassName());
    stringBuffer.append(TEXT_52);
    stringBuffer.append(genModel.getQualifiedEditorAdvisorClassName());
    stringBuffer.append(TEXT_53);
    stringBuffer.append(genModel.getQualifiedEditorAdvisorClassName());
    stringBuffer.append(TEXT_54);
    stringBuffer.append(genModel.getEditorAdvisorClassName());
    stringBuffer.append(TEXT_55);
    stringBuffer.append(genModel.getEditorAdvisorClassName());
    stringBuffer.append(TEXT_56);
    stringBuffer.append(genModel.getQualifiedEditorAdvisorClassName());
    stringBuffer.append(TEXT_57);
    stringBuffer.append(genModel.getQualifiedEditorAdvisorClassName());
    stringBuffer.append(TEXT_58);
    stringBuffer.append(genModel.getQualifiedEditorAdvisorClassName());
    stringBuffer.append(TEXT_59);
    stringBuffer.append(genModel.getQualifiedEditorAdvisorClassName());
    stringBuffer.append(TEXT_60);
    stringBuffer.append(genModel.getQualifiedEditorAdvisorClassName());
    stringBuffer.append(TEXT_61);
    stringBuffer.append(genModel.getQualifiedEditorAdvisorClassName());
    stringBuffer.append(TEXT_62);
    stringBuffer.append(genModel.getQualifiedEditorAdvisorClassName());
    stringBuffer.append(TEXT_63);
    stringBuffer.append(genModel.getQualifiedEditorAdvisorClassName());
    stringBuffer.append(TEXT_64);
    }
    for (GenPackage genPackage : genModel.getAllGenPackagesWithClassifiers()) {
    if (genPackage.hasConcreteClasses()){
    if (genPackage.isGenerateModelWizard()) {
    if (genModel.isRichClientPlatform()) {
    stringBuffer.append(TEXT_65);
    stringBuffer.append(genPackage.getModelWizardClassName());
    stringBuffer.append(TEXT_66);
    stringBuffer.append(genPackage.getQualifiedActionBarContributorClassName());
    stringBuffer.append(TEXT_67);
    stringBuffer.append(genPackage.getModelWizardClassName());
    stringBuffer.append(TEXT_68);
    stringBuffer.append(genPackage.getQualifiedActionBarContributorClassName());
    stringBuffer.append(TEXT_69);
    stringBuffer.append(genPackage.getQualifiedActionBarContributorClassName());
    stringBuffer.append(TEXT_70);
    } else {
    stringBuffer.append(TEXT_71);
    stringBuffer.append(genPackage.getQualifiedModelWizardClassName());
    stringBuffer.append(TEXT_72);
    stringBuffer.append(genPackage.getModelWizardClassName());
    stringBuffer.append(TEXT_73);
    stringBuffer.append(genPackage.getQualifiedModelWizardClassName());
    stringBuffer.append(TEXT_74);
    stringBuffer.append(genPackage.getPrefix());
    stringBuffer.append(TEXT_75);
    stringBuffer.append(genPackage.getModelWizardClassName());
    stringBuffer.append(TEXT_76);
    }
    }
    stringBuffer.append(TEXT_77);
    stringBuffer.append(genPackage.getQualifiedEditorClassName());
    stringBuffer.append(TEXT_78);
    stringBuffer.append(genPackage.getEditorClassName());
    stringBuffer.append(TEXT_79);
    stringBuffer.append(genPackage.getPrefix());
    stringBuffer.append(TEXT_80);
    stringBuffer.append(genPackage.getPrefix().toLowerCase());
    stringBuffer.append(TEXT_81);
    stringBuffer.append(genPackage.getQualifiedEditorClassName());
    stringBuffer.append(TEXT_82);
    stringBuffer.append(genPackage.getQualifiedActionBarContributorClassName());
    stringBuffer.append(TEXT_83);
    }
    }
    stringBuffer.append(TEXT_84);
    stringBuffer.append(TEXT_85);
    return stringBuffer.toString();
  }
}
