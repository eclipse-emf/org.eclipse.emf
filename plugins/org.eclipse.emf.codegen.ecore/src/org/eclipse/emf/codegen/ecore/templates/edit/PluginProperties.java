package org.eclipse.emf.codegen.ecore.templates.edit;

import java.util.*;
import org.eclipse.emf.codegen.ecore.genmodel.*;

public class PluginProperties
{
  protected final String NL = System.getProperties().getProperty("line.separator");
  protected final String TEXT_1 = "";
  protected final String TEXT_2 = "/**" + NL + " * <copyright>" + NL + " * </copyright>" + NL + " *" + NL + " * ";
  protected final String TEXT_3 = "Id";
  protected final String TEXT_4 = NL + " */" + NL + "" + NL + "# ====================================================================" + NL + "# To code developer:" + NL + "#   Do NOT change the properties between this line and the" + NL + "#   \"%%% END OF TRANSLATED PROPERTIES %%%\" line." + NL + "#   Make a new property name, append to the end of the file and change" + NL + "#   the code to use the new property." + NL + "# ====================================================================" + NL + "" + NL + "# ====================================================================" + NL + "# %%% END OF TRANSLATED PROPERTIES %%%" + NL + "# ====================================================================" + NL + "" + NL + "pluginName = ";
  protected final String TEXT_5 = " Edit Support" + NL + "providerName = www.example.org" + NL;
  protected final String TEXT_6 = NL + "_UI_CreateChild_text = {0}" + NL + "_UI_CreateChild_text2 = {1} {0}" + NL + "_UI_CreateChild_tooltip = Create New {0} Under {1} Feature" + NL + "_UI_CreateChild_description = Create a new child of type {0} for the {1} feature of the selected {2}." + NL + "_UI_CreateSibling_description = Create a new sibling of type {0} for the selected {2}, under the {1} feature of their parent." + NL;
  protected final String TEXT_7 = NL + "_UI_PropertyDescriptor_description = The {0} of the {1}" + NL;
  protected final String TEXT_8 = NL + "_UI_";
  protected final String TEXT_9 = "_type = ";
  protected final String TEXT_10 = NL + "_UI_Unknown_type = Object" + NL;
  protected final String TEXT_11 = NL + "_UI_";
  protected final String TEXT_12 = "_";
  protected final String TEXT_13 = "_feature = ";
  protected final String TEXT_14 = NL + "_UI_Unknown_feature = Unspecified" + NL;
  protected final String TEXT_15 = NL + "_UI_";
  protected final String TEXT_16 = "_";
  protected final String TEXT_17 = "_literal = ";
  protected final String TEXT_18 = NL;

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

    GenModel genModel = (GenModel)argument;
    stringBuffer.append(TEXT_1);
    stringBuffer.append(TEXT_2);
    stringBuffer.append("$");
    stringBuffer.append(TEXT_3);
    stringBuffer.append("$");
    stringBuffer.append(TEXT_4);
    stringBuffer.append(genModel.getModelName());
    stringBuffer.append(TEXT_5);
    if (genModel.isCreationCommands()) {
    stringBuffer.append(TEXT_6);
    }
    stringBuffer.append(TEXT_7);
    for (Iterator i=genModel.getAllGenAndUsedGenPackagesWithClassifiers().iterator(); i.hasNext();) { GenPackage genPackage = (GenPackage)i.next();
    if (genPackage.getGenModel() == genModel || !genPackage.getGenModel().hasEditSupport()) { 
    for (Iterator j=genPackage.getGenClasses().iterator(); j.hasNext();) { GenClass genClass = (GenClass)j.next();
    stringBuffer.append(TEXT_8);
    stringBuffer.append(genClass.getName());
    stringBuffer.append(TEXT_9);
    stringBuffer.append(genClass.getFormattedName());
    }
    }
    }
    stringBuffer.append(TEXT_10);
    for (Iterator i=genModel.getAllGenAndUsedGenPackagesWithClassifiers().iterator(); i.hasNext();) { GenPackage genPackage = (GenPackage)i.next();
    if (genPackage.getGenModel() == genModel || !genPackage.getGenModel().hasEditSupport()) {
    for (Iterator j=genPackage.getAllGenFeatures().iterator(); j.hasNext();) { GenFeature genFeature = (GenFeature)j.next(); GenClass genClass = genFeature.getGenClass();
    if (genModel.isCreationCommands() || genFeature.isProperty()) {
    stringBuffer.append(TEXT_11);
    stringBuffer.append(genClass.getName());
    stringBuffer.append(TEXT_12);
    stringBuffer.append(genFeature.getName());
    stringBuffer.append(TEXT_13);
    stringBuffer.append(genFeature.getFormattedName());
    }
    }
    }
    }
    stringBuffer.append(TEXT_14);
    for (Iterator i=genModel.getAllGenAndUsedGenPackagesWithClassifiers().iterator(); i.hasNext();) { GenPackage genPackage = (GenPackage)i.next();
    if (genPackage.getGenModel() == genModel || !genPackage.getGenModel().hasEditSupport()) {
    for (Iterator j=genPackage.getGenEnums().iterator(); j.hasNext();) { GenEnum genEnum = (GenEnum)j.next();
    for (Iterator k=genEnum.getGenEnumLiterals().iterator(); k.hasNext();) { GenEnumLiteral genEnumLiteral = (GenEnumLiteral)k.next();
    stringBuffer.append(TEXT_15);
    stringBuffer.append(genEnum.getName());
    stringBuffer.append(TEXT_16);
    stringBuffer.append(genEnumLiteral.getName());
    stringBuffer.append(TEXT_17);
    stringBuffer.append(genEnumLiteral.getName());
    }
    }
    }
    }
    stringBuffer.append(TEXT_18);
    return stringBuffer.toString();
  }
}
