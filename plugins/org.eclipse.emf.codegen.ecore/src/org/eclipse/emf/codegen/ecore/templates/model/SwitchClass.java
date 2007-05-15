package org.eclipse.emf.codegen.ecore.templates.model;

import java.util.*;
import org.eclipse.emf.codegen.ecore.genmodel.*;

public class SwitchClass
{
  protected static String nl;
  public static synchronized SwitchClass create(String lineSeparator)
  {
    nl = lineSeparator;
    SwitchClass result = new SwitchClass();
    nl = null;
    return result;
  }

  public final String NL = nl == null ? (System.getProperties().getProperty("line.separator")) : nl;
  protected final String TEXT_1 = "";
  protected final String TEXT_2 = "/**";
  protected final String TEXT_3 = NL + " * ";
  protected final String TEXT_4 = NL + " * <copyright>" + NL + " * </copyright>";
  protected final String TEXT_5 = NL + " *" + NL + " * ";
  protected final String TEXT_6 = "Id";
  protected final String TEXT_7 = NL + " */" + NL + "package ";
  protected final String TEXT_8 = ";" + NL;
  protected final String TEXT_9 = NL + NL + "/**" + NL + " * <!-- begin-user-doc -->" + NL + " * The <b>Switch</b> for the model's inheritance hierarchy." + NL + " * It supports the call {@link #doSwitch(EObject) doSwitch(object)}" + NL + " * to invoke the <code>caseXXX</code> method for each class of the model," + NL + " * starting with the actual class of the object" + NL + " * and proceeding up the inheritance hierarchy" + NL + " * until a non-null result is returned," + NL + " * which is the result of the switch." + NL + " * <!-- end-user-doc -->" + NL + " * @see ";
  protected final String TEXT_10 = NL + " * @generated" + NL + " */" + NL + "public class ";
  protected final String TEXT_11 = NL + "{";
  protected final String TEXT_12 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */" + NL + "\tpublic static final ";
  protected final String TEXT_13 = " copyright = ";
  protected final String TEXT_14 = ";";
  protected final String TEXT_15 = NL;
  protected final String TEXT_16 = NL + "\t/**" + NL + "\t * The cached model package" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */" + NL + "\tprotected static ";
  protected final String TEXT_17 = " modelPackage;" + NL + "" + NL + "\t/**" + NL + "\t * Creates an instance of the switch." + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */" + NL + "\tpublic ";
  protected final String TEXT_18 = "()" + NL + "\t{" + NL + "\t\tif (modelPackage == null)" + NL + "\t\t{" + NL + "\t\t\tmodelPackage = ";
  protected final String TEXT_19 = ".eINSTANCE;" + NL + "\t\t}" + NL + "\t}" + NL + "" + NL + "\t/**" + NL + "\t * Calls <code>caseXXX</code> for each class of the model until one returns a non null result; it yields that result." + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @return the first non-null result returned by a <code>caseXXX</code> call." + NL + "\t * @generated" + NL + "\t */" + NL + "\tpublic ";
  protected final String TEXT_20 = " doSwitch(EObject theEObject)" + NL + "\t{" + NL + "\t\treturn doSwitch(theEObject.eClass(), theEObject);" + NL + "\t}" + NL + "" + NL + "\t/**" + NL + "\t * Calls <code>caseXXX</code> for each class of the model until one returns a non null result; it yields that result." + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @return the first non-null result returned by a <code>caseXXX</code> call." + NL + "\t * @generated" + NL + "\t */" + NL + "\tprotected ";
  protected final String TEXT_21 = " doSwitch(EClass theEClass, EObject theEObject)" + NL + "\t{" + NL + "\t\tif (theEClass.eContainer() == modelPackage)" + NL + "\t\t{" + NL + "\t\t\treturn doSwitch(theEClass.getClassifierID(), theEObject);" + NL + "\t\t}" + NL + "\t\telse" + NL + "\t\t{" + NL + "\t\t\t";
  protected final String TEXT_22 = "<";
  protected final String TEXT_23 = ">";
  protected final String TEXT_24 = " eSuperTypes = theEClass.getESuperTypes();" + NL + "\t\t\treturn" + NL + "\t\t\t\teSuperTypes.isEmpty() ?" + NL + "\t\t\t\t\tdefaultCase(theEObject) :" + NL + "\t\t\t\t\tdoSwitch(";
  protected final String TEXT_25 = "(EClass)";
  protected final String TEXT_26 = "eSuperTypes.get(0), theEObject);" + NL + "\t\t}" + NL + "\t}" + NL + "" + NL + "\t/**" + NL + "\t * Calls <code>caseXXX</code> for each class of the model until one returns a non null result; it yields that result." + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @return the first non-null result returned by a <code>caseXXX</code> call." + NL + "\t * @generated" + NL + "\t */" + NL + "\tprotected ";
  protected final String TEXT_27 = " doSwitch(int classifierID, EObject theEObject)" + NL + "\t{" + NL + "\t\tswitch (classifierID)" + NL + "\t\t{";
  protected final String TEXT_28 = NL + "\t\t\tcase ";
  protected final String TEXT_29 = ".";
  protected final String TEXT_30 = ":" + NL + "\t\t\t{" + NL + "\t\t\t\t";
  protected final String TEXT_31 = "@SuppressWarnings(\"unchecked\") ";
  protected final String TEXT_32 = " ";
  protected final String TEXT_33 = " = (";
  protected final String TEXT_34 = ")theEObject;" + NL + "\t\t\t\t";
  protected final String TEXT_35 = " ";
  protected final String TEXT_36 = " = case";
  protected final String TEXT_37 = "(";
  protected final String TEXT_38 = ");";
  protected final String TEXT_39 = NL + "\t\t\t\tif (";
  protected final String TEXT_40 = " == null) ";
  protected final String TEXT_41 = " = case";
  protected final String TEXT_42 = "(";
  protected final String TEXT_43 = "(";
  protected final String TEXT_44 = ")";
  protected final String TEXT_45 = ");";
  protected final String TEXT_46 = NL + "\t\t\t\tif (";
  protected final String TEXT_47 = " == null) ";
  protected final String TEXT_48 = " = defaultCase(theEObject);" + NL + "\t\t\t\treturn ";
  protected final String TEXT_49 = ";" + NL + "\t\t\t}";
  protected final String TEXT_50 = NL + "\t\t\tdefault: return defaultCase(theEObject);" + NL + "\t\t}" + NL + "\t}";
  protected final String TEXT_51 = NL + NL + "\t/**" + NL + "\t * Returns the result of interpretting the object as an instance of '<em>";
  protected final String TEXT_52 = "</em>'." + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * This implementation returns null;" + NL + "\t * returning a non-null result will terminate the switch." + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @param object the target of the switch." + NL + "\t * @return the result of interpretting the object as an instance of '<em>";
  protected final String TEXT_53 = "</em>'." + NL + "\t * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)" + NL + "\t * @generated" + NL + "\t */" + NL + "\tpublic ";
  protected final String TEXT_54 = " case";
  protected final String TEXT_55 = "(";
  protected final String TEXT_56 = " object)" + NL + "\t{" + NL + "\t\treturn null;" + NL + "\t}";
  protected final String TEXT_57 = NL + NL + "\t/**" + NL + "\t * Returns the result of interpretting the object as an instance of '<em>EObject</em>'." + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * This implementation returns null;" + NL + "\t * returning a non-null result will terminate the switch, but this is the last case anyway." + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @param object the target of the switch." + NL + "\t * @return the result of interpretting the object as an instance of '<em>EObject</em>'." + NL + "\t * @see #doSwitch(org.eclipse.emf.ecore.EObject)" + NL + "\t * @generated" + NL + "\t */" + NL + "\tpublic ";
  protected final String TEXT_58 = " defaultCase(EObject object)" + NL + "\t{" + NL + "\t\treturn null;" + NL + "\t}" + NL + "" + NL + "} //";
  protected final String TEXT_59 = NL;

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

    GenPackage genPackage = (GenPackage)argument; GenModel genModel=genPackage.getGenModel();
    stringBuffer.append(TEXT_1);
    stringBuffer.append(TEXT_2);
    {GenBase copyrightHolder = argument instanceof GenBase ? (GenBase)argument : argument instanceof Object[] && ((Object[])argument)[0] instanceof GenBase ? (GenBase)((Object[])argument)[0] : null;
    if (copyrightHolder != null && copyrightHolder.hasCopyright()) {
    stringBuffer.append(TEXT_3);
    stringBuffer.append(copyrightHolder.getCopyright(copyrightHolder.getGenModel().getIndentation(stringBuffer)));
    } else {
    stringBuffer.append(TEXT_4);
    }}
    stringBuffer.append(TEXT_5);
    stringBuffer.append("$");
    stringBuffer.append(TEXT_6);
    stringBuffer.append("$");
    stringBuffer.append(TEXT_7);
    stringBuffer.append(genPackage.getUtilitiesPackageName());
    stringBuffer.append(TEXT_8);
    genModel.addImport("org.eclipse.emf.ecore.EClass");
    genModel.addImport("org.eclipse.emf.ecore.EObject");
    if (!genPackage.hasJavaLangConflict() && !genPackage.getUtilitiesPackageName().equals(genPackage.getInterfacePackageName())) genModel.addImport(genPackage.getInterfacePackageName() + ".*");
    
String templateParameterName = null;
if (genModel.useGenerics())
{
  Set<String> usedNames = new HashSet<String>();
  for (GenEnum genEnum : genPackage.getGenEnums())
  {
    usedNames.add(genEnum.getName());
  }
  for (GenClass genClass : genPackage.getGenClasses())
  {
    if (!genClass.isDynamic() && !genClass.isExternalInterface())
    {
      usedNames.add(genClass.getName());
    }
    for (GenTypeParameter genTypeParameter : genClass.getGenTypeParameters())
    {
      usedNames.add(genTypeParameter.getName());
    }
  }
  templateParameterName = "T";
  for (int i = 1; usedNames.contains(templateParameterName); ++i)
  {
    templateParameterName = "T" + i;
  }
}

    String templateParameters = genModel.useGenerics() ? "<" + templateParameterName + ">" : "";
    String returnType = genModel.useGenerics() ? templateParameterName : genModel.getImportedName("java.lang.Object");
    genModel.markImportLocation(stringBuffer);
    stringBuffer.append(TEXT_9);
    stringBuffer.append(genPackage.getQualifiedPackageInterfaceName());
    stringBuffer.append(TEXT_10);
    stringBuffer.append(genPackage.getSwitchClassName());
    stringBuffer.append(templateParameters);
    stringBuffer.append(TEXT_11);
    if (genModel.hasCopyrightField()) {
    stringBuffer.append(TEXT_12);
    stringBuffer.append(genModel.getImportedName("java.lang.String"));
    stringBuffer.append(TEXT_13);
    stringBuffer.append(genModel.getCopyrightFieldLiteral());
    stringBuffer.append(TEXT_14);
    stringBuffer.append(genModel.getNonNLS());
    stringBuffer.append(TEXT_15);
    }
    stringBuffer.append(TEXT_16);
    stringBuffer.append(genPackage.getImportedPackageInterfaceName());
    stringBuffer.append(TEXT_17);
    stringBuffer.append(genPackage.getSwitchClassName());
    stringBuffer.append(TEXT_18);
    stringBuffer.append(genPackage.getImportedPackageInterfaceName());
    stringBuffer.append(TEXT_19);
    stringBuffer.append(returnType);
    stringBuffer.append(TEXT_20);
    stringBuffer.append(returnType);
    stringBuffer.append(TEXT_21);
    stringBuffer.append(genModel.getImportedName("java.util.List"));
    if (genModel.useGenerics()) {
    stringBuffer.append(TEXT_22);
    stringBuffer.append(genModel.getImportedName("org.eclipse.emf.ecore.EClass"));
    stringBuffer.append(TEXT_23);
    }
    stringBuffer.append(TEXT_24);
    if (!genModel.useGenerics()) {
    stringBuffer.append(TEXT_25);
    }
    stringBuffer.append(TEXT_26);
    stringBuffer.append(returnType);
    stringBuffer.append(TEXT_27);
    for (GenClass genClass : genPackage.getGenClasses()) {
    if (!genClass.isExternalInterface() && !genClass.isEObject() || genClass.isMapEntry()) { String result = "result".equals(genClass.getSafeUncapName()) ? "theResult" : "result"; 
    stringBuffer.append(TEXT_28);
    stringBuffer.append(genPackage.getImportedPackageInterfaceName());
    stringBuffer.append(TEXT_29);
    stringBuffer.append(genPackage.getClassifierID(genClass));
    stringBuffer.append(TEXT_30);
    if (genClass.isUncheckedCast() || genClass.getInterfaceWildTypeArguments().length() > 0) {
    stringBuffer.append(TEXT_31);
    }
    stringBuffer.append(genClass.getImportedInterfaceName());
    stringBuffer.append(genClass.getInterfaceWildTypeArguments());
    stringBuffer.append(TEXT_32);
    stringBuffer.append(genClass.getSafeUncapName());
    stringBuffer.append(TEXT_33);
    stringBuffer.append(genClass.getImportedInterfaceName());
    stringBuffer.append(genClass.getInterfaceWildTypeArguments());
    stringBuffer.append(TEXT_34);
    stringBuffer.append(returnType);
    stringBuffer.append(TEXT_35);
    stringBuffer.append(result);
    stringBuffer.append(TEXT_36);
    stringBuffer.append(genPackage.getClassUniqueName(genClass));
    stringBuffer.append(TEXT_37);
    stringBuffer.append(genClass.getSafeUncapName());
    stringBuffer.append(TEXT_38);
    for (GenClass baseGenClass : genClass.getSwitchGenClasses()) {
    stringBuffer.append(TEXT_39);
    stringBuffer.append(result);
    stringBuffer.append(TEXT_40);
    stringBuffer.append(result);
    stringBuffer.append(TEXT_41);
    stringBuffer.append(genPackage.getClassUniqueName(baseGenClass));
    stringBuffer.append(TEXT_42);
    if (genClass.isMapEntry()) {
    stringBuffer.append(TEXT_43);
    stringBuffer.append(baseGenClass.getImportedInterfaceName());
    stringBuffer.append(baseGenClass.getInterfaceTypeArguments());
    stringBuffer.append(TEXT_44);
    }
    stringBuffer.append(genClass.getSafeUncapName());
    stringBuffer.append(TEXT_45);
    }
    stringBuffer.append(TEXT_46);
    stringBuffer.append(result);
    stringBuffer.append(TEXT_47);
    stringBuffer.append(result);
    stringBuffer.append(TEXT_48);
    stringBuffer.append(result);
    stringBuffer.append(TEXT_49);
    }
    }
    stringBuffer.append(TEXT_50);
    for (GenClass genClass : genPackage.getAllSwitchGenClasses()) {
    stringBuffer.append(TEXT_51);
    stringBuffer.append(genClass.getFormattedName());
    stringBuffer.append(TEXT_52);
    stringBuffer.append(genClass.getFormattedName());
    stringBuffer.append(TEXT_53);
    stringBuffer.append(genClass.getTypeParameters());
    stringBuffer.append(returnType);
    stringBuffer.append(TEXT_54);
    stringBuffer.append(genPackage.getClassUniqueName(genClass));
    stringBuffer.append(TEXT_55);
    stringBuffer.append(genClass.getImportedInterfaceName());
    stringBuffer.append(genClass.getInterfaceTypeArguments());
    stringBuffer.append(TEXT_56);
    }
    stringBuffer.append(TEXT_57);
    stringBuffer.append(returnType);
    stringBuffer.append(TEXT_58);
    stringBuffer.append(genPackage.getSwitchClassName());
    genModel.emitSortedImports();
    stringBuffer.append(TEXT_59);
    return stringBuffer.toString();
  }
}
