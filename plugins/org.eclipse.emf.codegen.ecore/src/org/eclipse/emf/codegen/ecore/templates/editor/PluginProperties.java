package org.eclipse.emf.codegen.ecore.templates.editor;

import java.util.Iterator;

import org.eclipse.emf.codegen.ecore.genmodel.GenModel;
import org.eclipse.emf.codegen.ecore.genmodel.GenPackage;

public class PluginProperties
{
  protected final String NL = System.getProperties().getProperty("line.separator");
  protected final String TEXT_1 = "";
  protected final String TEXT_2 = "/**" + NL + " * <copyright>" + NL + " * </copyright>" + NL + " *" + NL + " * ";
  protected final String TEXT_3 = "W%" + NL + " * @version ";
  protected final String TEXT_4 = "I% ";
  protected final String TEXT_5 = "H%" + NL + " */" + NL + "" + NL + "# ====================================================================" + NL + "# To code developer:" + NL + "#   Do NOT change the properties between this line and the" + NL + "#   \"%%% END OF TRANSLATED PROPERTIES %%%\" line." + NL + "#   Make a new property name, append to the end of the file and change" + NL + "#   the code to use the new property." + NL + "# ====================================================================" + NL + "" + NL + "# ====================================================================" + NL + "# %%% END OF TRANSLATED PROPERTIES %%%" + NL + "# ====================================================================" + NL + "" + NL + "pluginName = ";
  protected final String TEXT_6 = " Editor" + NL + "providerName = www.example.org" + NL;
  protected final String TEXT_7 = NL + "_UI_";
  protected final String TEXT_8 = "_menu = &";
  protected final String TEXT_9 = " Editor";
  protected final String TEXT_10 = NL;
  protected final String TEXT_11 = NL + "_UI_CreateChild_menu_item = &New Child" + NL + "_UI_CreateSibling_menu_item = N&ew Sibling" + NL;
  protected final String TEXT_12 = NL + "_UI_SelectionPage_label = Selection" + NL + "_UI_ParentPage_label = Parent" + NL + "_UI_ListPage_label = List" + NL + "_UI_TreePage_label = Tree" + NL + "_UI_TablePage_label = Table" + NL + "_UI_TableTreePage_label = TableTree" + NL + "_UI_ObjectColumn_label = Object" + NL + "_UI_SelfColumn_label = Self" + NL + "" + NL + "_UI_NoObjectSelected = Selected Nothing" + NL + "_UI_SingleObjectSelected = Selected Object: {0}" + NL + "_UI_MultiObjectSelected = Selected {0} Objects" + NL + "" + NL + "_UI_OpenEditorError_label = Open Editor" + NL + "" + NL + "_UI_Wizard_category = Example EMF Model Creation Wizards" + NL;
  protected final String TEXT_13 = NL + "_UI_";
  protected final String TEXT_14 = "_label = ";
  protected final String TEXT_15 = " Model" + NL + "_UI_";
  protected final String TEXT_16 = "_description = Create a new ";
  protected final String TEXT_17 = " model" + NL + "" + NL + "_UI_";
  protected final String TEXT_18 = "_label = ";
  protected final String TEXT_19 = " Model Editor" + NL + "" + NL + "_UI_";
  protected final String TEXT_20 = "FilenameDefaultBase = My" + NL + "_UI_";
  protected final String TEXT_21 = "FilenameExtension = ";
  protected final String TEXT_22 = NL;
  protected final String TEXT_23 = NL + "_UI_Wizard_label = New" + NL + "" + NL + "_WARN_FilenameExtension = The filename must end in \".{0}\"" + NL + "" + NL + "_UI_ModelObject = Model Object" + NL + "_UI_XMLEncoding = XML Encoding" + NL + "_UI_XMLEncodingChoices = ";
  protected final String TEXT_24 = NL + "_UI_Wizard_initial_object_description = Select a model object to create" + NL + "" + NL + "_UI_FileConflict_label = File Conflict" + NL + "_WARN_FileConflict = There are unsaved changes that conflict with changes made outside the editor.  Do you wish to discard this editor's changes?" + NL;
  protected final String TEXT_25 = NL;

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
 *
 * $Id: PluginProperties.java,v 1.1 2004/03/06 17:31:31 marcelop Exp $
 */

    GenModel genModel = (GenModel)argument;
    stringBuffer.append(TEXT_1);
    stringBuffer.append(TEXT_2);
    stringBuffer.append("%");
    stringBuffer.append(TEXT_3);
    stringBuffer.append("%");
    stringBuffer.append(TEXT_4);
    stringBuffer.append("%");
    stringBuffer.append(TEXT_5);
    stringBuffer.append(genModel.getModelName());
    stringBuffer.append(TEXT_6);
    for (Iterator i = genModel.getAllGenPackagesWithClassifiers().iterator(); i.hasNext(); ) { GenPackage genPackage = (GenPackage)i.next();
    stringBuffer.append(TEXT_7);
    stringBuffer.append(genPackage.getEditorClassName());
    stringBuffer.append(TEXT_8);
    stringBuffer.append(genPackage.getPrefix());
    stringBuffer.append(TEXT_9);
    }
    stringBuffer.append(TEXT_10);
    if (genModel.isCreationCommands()) {
    stringBuffer.append(TEXT_11);
    }
    stringBuffer.append(TEXT_12);
    for (Iterator i = genModel.getAllGenPackagesWithClassifiers().iterator(); i.hasNext(); ) { GenPackage genPackage = (GenPackage)i.next();
    stringBuffer.append(TEXT_13);
    stringBuffer.append(genPackage.getModelWizardClassName());
    stringBuffer.append(TEXT_14);
    stringBuffer.append(genPackage.getPrefix());
    stringBuffer.append(TEXT_15);
    stringBuffer.append(genPackage.getModelWizardClassName());
    stringBuffer.append(TEXT_16);
    stringBuffer.append(genPackage.getPrefix());
    stringBuffer.append(TEXT_17);
    stringBuffer.append(genPackage.getEditorClassName());
    stringBuffer.append(TEXT_18);
    stringBuffer.append(genPackage.getPrefix());
    stringBuffer.append(TEXT_19);
    stringBuffer.append(genPackage.getEditorClassName());
    stringBuffer.append(TEXT_20);
    stringBuffer.append(genPackage.getEditorClassName());
    stringBuffer.append(TEXT_21);
    stringBuffer.append(genPackage.getPrefix().toLowerCase());
    stringBuffer.append(TEXT_22);
    }
    stringBuffer.append(TEXT_23);
    stringBuffer.append(genModel.getXMLEncodingChoices());
    stringBuffer.append(TEXT_24);
    stringBuffer.append(TEXT_25);
    return stringBuffer.toString();
  }
}
