package org.eclipse.emf.codegen.ecore.templates.model;

import java.util.*;
import org.eclipse.emf.codegen.ecore.genmodel.*;

public class SwitchClass
{
  protected final String NL = System.getProperties().getProperty("line.separator");
  protected final String TEXT_1 = "";
  protected final String TEXT_2 = "/**" + NL + " * <copyright>" + NL + " * </copyright>" + NL + " *" + NL + " * ";
  protected final String TEXT_3 = "Id";
  protected final String TEXT_4 = NL + " */" + NL + "package ";
  protected final String TEXT_5 = ";" + NL;
  protected final String TEXT_6 = NL + NL + "/**" + NL + " * <!-- begin-user-doc -->" + NL + " * The <b>Switch</b> for the model's inheritance hierarchy." + NL + " * It supports the call {@link #doSwitch doSwitch(object)}" + NL + " * to invoke the <code>caseXXX</code> method for each class of the model," + NL + " * starting with the actual class of the object" + NL + " * and proceeding up the inheritance hierarchy" + NL + " * until a non-null result is returned," + NL + " * which is the result of the switch." + NL + " * <!-- end-user-doc -->" + NL + " * @see ";
  protected final String TEXT_7 = NL + " * @generated" + NL + " */" + NL + "public class ";
  protected final String TEXT_8 = NL + "{";
  protected final String TEXT_9 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */" + NL + "\tpublic static final ";
  protected final String TEXT_10 = " copyright = \"";
  protected final String TEXT_11 = "\";";
  protected final String TEXT_12 = NL;
  protected final String TEXT_13 = NL + "\t/**" + NL + "\t * The cached model package" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */" + NL + "\tprotected static ";
  protected final String TEXT_14 = " modelPackage;" + NL + "" + NL + "\t/**" + NL + "\t * Creates an instance of the switch." + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */" + NL + "\tpublic ";
  protected final String TEXT_15 = "()" + NL + "\t{" + NL + "\t\tif (modelPackage == null)" + NL + "\t\t{" + NL + "\t\t\tmodelPackage = ";
  protected final String TEXT_16 = ".eINSTANCE;" + NL + "\t\t}" + NL + "\t}" + NL + "" + NL + "\t/**" + NL + "\t * Calls <code>caseXXX</code> for each class of the model until one returns a non null result; it yields that result." + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @return the first non-null result returned by a <code>caseXXX</code> call." + NL + "\t * @generated" + NL + "\t */" + NL + "\tpublic Object doSwitch(EObject theEObject)" + NL + "\t{" + NL + "\t\tEClass theEClass = theEObject.eClass();" + NL + "\t\tif (theEClass.eContainer() == modelPackage)" + NL + "\t\t{" + NL + "\t\t\tswitch (theEClass.getClassifierID())" + NL + "\t\t\t{";
  protected final String TEXT_17 = NL + "\t\t\t\tcase ";
  protected final String TEXT_18 = ".";
  protected final String TEXT_19 = ":" + NL + "\t\t\t\t{" + NL + "\t\t\t\t\t";
  protected final String TEXT_20 = " ";
  protected final String TEXT_21 = " = (";
  protected final String TEXT_22 = ")theEObject;" + NL + "\t\t\t\t\tObject ";
  protected final String TEXT_23 = " = case";
  protected final String TEXT_24 = "(";
  protected final String TEXT_25 = ");";
  protected final String TEXT_26 = NL + "\t\t\t\t\tif (";
  protected final String TEXT_27 = " == null) ";
  protected final String TEXT_28 = " = case";
  protected final String TEXT_29 = "(";
  protected final String TEXT_30 = ");";
  protected final String TEXT_31 = NL + "\t\t\t\t\tif (";
  protected final String TEXT_32 = " == null) ";
  protected final String TEXT_33 = " = defaultCase(theEObject);" + NL + "\t\t\t\t\treturn ";
  protected final String TEXT_34 = ";" + NL + "\t\t\t\t}";
  protected final String TEXT_35 = NL + "\t\t\t\tdefault: return defaultCase(theEObject);" + NL + "\t\t\t}" + NL + "\t\t}" + NL + "\t\treturn defaultCase(theEObject);" + NL + "\t}";
  protected final String TEXT_36 = NL + NL + "\t/**" + NL + "\t * Returns the result of interpretting the object as an instance of '<em>";
  protected final String TEXT_37 = "</em>'." + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * This implementation returns null;" + NL + "\t * returning a non-null result will terminate the switch." + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @param object the target of the switch." + NL + "\t * @return the result of interpretting the object as an instance of '<em>";
  protected final String TEXT_38 = "</em>'." + NL + "\t * @see #doSwitch(org.eclipse.emf.ecore.EObject) doSwitch(EObject)" + NL + "\t * @generated" + NL + "\t */" + NL + "\tpublic Object case";
  protected final String TEXT_39 = "(";
  protected final String TEXT_40 = " object)" + NL + "\t{" + NL + "\t\treturn null;" + NL + "\t}";
  protected final String TEXT_41 = NL + NL + "\t/**" + NL + "\t * Returns the result of interpretting the object as an instance of '<em>EObject</em>'." + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * This implementation returns null;" + NL + "\t * returning a non-null result will terminate the switch, but this is the last case anyway." + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @param object the target of the switch." + NL + "\t * @return the result of interpretting the object as an instance of '<em>EObject</em>'." + NL + "\t * @see #doSwitch(org.eclipse.emf.ecore.EObject)" + NL + "\t * @generated" + NL + "\t */" + NL + "\tpublic Object defaultCase(EObject object)" + NL + "\t{" + NL + "\t\treturn null;" + NL + "\t}" + NL + "" + NL + "} //";
  protected final String TEXT_42 = NL;

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

    GenPackage genPackage = (GenPackage)argument; GenModel genModel=genPackage.getGenModel();
    stringBuffer.append(TEXT_1);
    stringBuffer.append(TEXT_2);
    stringBuffer.append("$");
    stringBuffer.append(TEXT_3);
    stringBuffer.append("$");
    stringBuffer.append(TEXT_4);
    stringBuffer.append(genPackage.getUtilitiesPackageName());
    stringBuffer.append(TEXT_5);
    genModel.addImport("org.eclipse.emf.ecore.EClass");
    genModel.addImport("org.eclipse.emf.ecore.EObject");
    if (!genPackage.hasJavaLangConflict()) genModel.addImport(genPackage.getInterfacePackageName() + ".*");
    genModel.markImportLocation(stringBuffer);
    stringBuffer.append(TEXT_6);
    stringBuffer.append(genPackage.getQualifiedPackageInterfaceName());
    stringBuffer.append(TEXT_7);
    stringBuffer.append(genPackage.getSwitchClassName());
    stringBuffer.append(TEXT_8);
    if (genModel.getCopyrightText() != null) {
    stringBuffer.append(TEXT_9);
    stringBuffer.append(genModel.getImportedName("java.lang.String"));
    stringBuffer.append(TEXT_10);
    stringBuffer.append(genModel.getCopyrightText());
    stringBuffer.append(TEXT_11);
    stringBuffer.append(genModel.getNonNLS());
    stringBuffer.append(TEXT_12);
    }
    stringBuffer.append(TEXT_13);
    stringBuffer.append(genPackage.getImportedPackageInterfaceName());
    stringBuffer.append(TEXT_14);
    stringBuffer.append(genPackage.getSwitchClassName());
    stringBuffer.append(TEXT_15);
    stringBuffer.append(genPackage.getImportedPackageInterfaceName());
    stringBuffer.append(TEXT_16);
    for (Iterator i=genPackage.getGenClasses().iterator(); i.hasNext();) { GenClass genClass = (GenClass)i.next();
    if (!genClass.isAbstract() && !genClass.isEObject()) { String result = "result".equals(genClass.getSafeUncapName()) ? "theResult" : "result"; 
    stringBuffer.append(TEXT_17);
    stringBuffer.append(genPackage.getImportedPackageInterfaceName());
    stringBuffer.append(TEXT_18);
    stringBuffer.append(genPackage.getClassifierID(genClass));
    stringBuffer.append(TEXT_19);
    stringBuffer.append(genClass.getImportedInterfaceName());
    stringBuffer.append(TEXT_20);
    stringBuffer.append(genClass.getSafeUncapName());
    stringBuffer.append(TEXT_21);
    stringBuffer.append(genClass.getImportedInterfaceName());
    stringBuffer.append(TEXT_22);
    stringBuffer.append(result);
    stringBuffer.append(TEXT_23);
    stringBuffer.append(genPackage.getClassUniqueName(genClass));
    stringBuffer.append(TEXT_24);
    stringBuffer.append(genClass.getSafeUncapName());
    stringBuffer.append(TEXT_25);
    for (Iterator b=genClass.getSwitchGenClasses().iterator(); b.hasNext();) { GenClass baseGenClass = (GenClass)b.next();
    stringBuffer.append(TEXT_26);
    stringBuffer.append(result);
    stringBuffer.append(TEXT_27);
    stringBuffer.append(result);
    stringBuffer.append(TEXT_28);
    stringBuffer.append(genPackage.getClassUniqueName(baseGenClass));
    stringBuffer.append(TEXT_29);
    stringBuffer.append(genClass.getSafeUncapName());
    stringBuffer.append(TEXT_30);
    }
    stringBuffer.append(TEXT_31);
    stringBuffer.append(result);
    stringBuffer.append(TEXT_32);
    stringBuffer.append(result);
    stringBuffer.append(TEXT_33);
    stringBuffer.append(result);
    stringBuffer.append(TEXT_34);
    }
    }
    stringBuffer.append(TEXT_35);
    for (Iterator i=genPackage.getAllSwitchGenClasses().iterator(); i.hasNext();) { GenClass genClass = (GenClass)i.next();
    stringBuffer.append(TEXT_36);
    stringBuffer.append(genClass.getFormattedName());
    stringBuffer.append(TEXT_37);
    stringBuffer.append(genClass.getFormattedName());
    stringBuffer.append(TEXT_38);
    stringBuffer.append(genPackage.getClassUniqueName(genClass));
    stringBuffer.append(TEXT_39);
    stringBuffer.append(genClass.getImportedInterfaceName());
    stringBuffer.append(TEXT_40);
    }
    stringBuffer.append(TEXT_41);
    stringBuffer.append(genPackage.getSwitchClassName());
    genModel.emitSortedImports();
    stringBuffer.append(TEXT_42);
    return stringBuffer.toString();
  }
}
