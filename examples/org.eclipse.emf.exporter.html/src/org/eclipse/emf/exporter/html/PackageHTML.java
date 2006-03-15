package org.eclipse.emf.exporter.html;

import java.util.*;
import org.eclipse.emf.ecore.*;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.codegen.ecore.genmodel.GenPackage;

public class PackageHTML
{
  protected static String nl;
  public static synchronized PackageHTML create(String lineSeparator)
  {
    nl = lineSeparator;
    PackageHTML result = new PackageHTML();
    nl = null;
    return result;
  }

  protected final String NL = nl == null ? (System.getProperties().getProperty("line.separator")) : nl;
  protected final String TEXT_1 = "<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Strict//EN\" \"http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd\">" + NL + "<html xmlns=\"http://www.w3.org/1999/xhtml\">" + NL + "\t<head>" + NL + "\t\t<meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\" />" + NL + "\t\t<title>";
  protected final String TEXT_2 = "</title>" + NL + "\t\t" + NL + "\t\t<style>" + NL + "\t\t\tbody {font-family: arial, helvetica, geneva; font-size: 10pt; clip:   rect(   ); margin-top: 5mm; margin-left: 3mm}" + NL + "\t\t\th1 {font-family: arial, helvetica, geneva; font-size: 22px; font-weight: bold; color:white; background-color:#595791}" + NL + "\t\t\th2 {font-family: arial, helvetica, geneva; font-size: 16pt; font-weight: bold ; line-height: 20px; text-decoration: underline}" + NL + "\t\t\th3 {font-family: arial; font-size: 12pt}" + NL + "\t\t\tp, table, td, th {font-family: arial, helvetica, geneva; font-size: 10pt}" + NL + "\t\t\tcode {font-family: \"Courier New\", Courier, mono; font-size: 10pt}" + NL + "\t\t\tli {font-family: arial, helvetica, geneva; font-size: 10pt}" + NL + "\t\t</style>\t\t" + NL + "\t</head>" + NL + "" + NL + "\t<body lang=\"EN-US\">" + NL + "\t\t<h1><i>";
  protected final String TEXT_3 = "</i></h1>" + NL + "\t\t\t<table border=\"1\" cellpadding=\"2\" cellspacing=\"0\" summary=\"Package details.\">" + NL + "\t\t\t\t<tr>" + NL + "\t\t\t\t\t<td>Ecore URI</td>" + NL + "\t\t\t\t\t<td>";
  protected final String TEXT_4 = "</td>" + NL + "\t\t\t\t</tr>" + NL + "\t\t\t\t<tr>" + NL + "\t\t\t\t\t<td>GenModel URI</td>" + NL + "\t\t\t\t\t<td>";
  protected final String TEXT_5 = "</td>" + NL + "\t\t\t\t</tr>" + NL + "\t\t\t</table>" + NL;
  protected final String TEXT_6 = NL + "\t\t<h1>Classes</h1>";
  protected final String TEXT_7 = NL + "\t\t\t<h2><a name=\"";
  protected final String TEXT_8 = "\">";
  protected final String TEXT_9 = "</a></h2>" + NL + "\t\t\t\t<h3>Details</h3>" + NL + "\t\t\t\t\t<table border=\"1\" cellpadding=\"2\" cellspacing=\"0\" summary=\"Package details.\">" + NL + "\t\t\t\t\t\t<tr>" + NL + "\t\t\t\t\t\t\t<td>Is Abstract</td>" + NL + "\t\t\t\t\t\t\t<td>";
  protected final String TEXT_10 = "</td>" + NL + "\t\t\t\t\t\t</tr>" + NL + "\t\t\t\t\t\t<tr>" + NL + "\t\t\t\t\t\t\t<td>Is Interface</td>" + NL + "\t\t\t\t\t\t\t<td>";
  protected final String TEXT_11 = "</td>" + NL + "\t\t\t\t\t\t</tr>" + NL + "\t\t\t\t\t</table>" + NL + "\t\t";
  protected final String TEXT_12 = NL + "\t\t\t\t<h3>Super Classes</h3>" + NL + "\t\t\t\t\t<ul>";
  protected final String TEXT_13 = NL + "\t\t\t\t\t\t<li><tt>";
  protected final String TEXT_14 = "</tt></li>";
  protected final String TEXT_15 = NL + "\t\t\t\t\t</ul>" + NL + "\t\t";
  protected final String TEXT_16 = NL + "\t\t";
  protected final String TEXT_17 = NL + "\t\t\t\t<h3>Attributes</h3>" + NL + "\t\t\t\t\t<ul>";
  protected final String TEXT_18 = NL + "\t\t\t\t\t\t<li><tt>";
  protected final String TEXT_19 = "*";
  protected final String TEXT_20 = "</tt> ";
  protected final String TEXT_21 = "</li>";
  protected final String TEXT_22 = NL + "\t\t\t\t\t</ul>" + NL + "\t\t";
  protected final String TEXT_23 = NL + "\t\t";
  protected final String TEXT_24 = NL + "\t\t\t\t<h3>Operations</h3>" + NL + "\t\t\t\t\t<ul>";
  protected final String TEXT_25 = NL + "\t\t\t\t\t\t<li><tt>";
  protected final String TEXT_26 = "*";
  protected final String TEXT_27 = "</tt> ";
  protected final String TEXT_28 = " (";
  protected final String TEXT_29 = "<tt>";
  protected final String TEXT_30 = "*";
  protected final String TEXT_31 = "</tt>";
  protected final String TEXT_32 = ", ";
  protected final String TEXT_33 = ")</li>";
  protected final String TEXT_34 = NL + "\t\t\t\t\t</ul>" + NL + "\t\t";
  protected final String TEXT_35 = NL + "\t\t";
  protected final String TEXT_36 = NL + "\t\t\t\t<h3>References</h3>" + NL + "\t\t\t\t\t<ul>";
  protected final String TEXT_37 = NL + "\t\t\t\t\t\t<li><tt>";
  protected final String TEXT_38 = "*";
  protected final String TEXT_39 = "</tt> ";
  protected final String TEXT_40 = "</li>";
  protected final String TEXT_41 = NL + "\t\t\t\t\t</ul>" + NL + "\t\t";
  protected final String TEXT_42 = NL + "\t";
  protected final String TEXT_43 = NL;
  protected final String TEXT_44 = "\t\t" + NL + "\t\t<h1>Enumerations</h1>";
  protected final String TEXT_45 = NL + "\t\t\t<h2><a name=\"";
  protected final String TEXT_46 = "\">";
  protected final String TEXT_47 = "</a></h2>";
  protected final String TEXT_48 = NL + "\t\t\t\t<h3>Literals</h3>" + NL + "\t\t\t\t\t<ul>";
  protected final String TEXT_49 = NL + "\t\t\t\t\t\t<li>";
  protected final String TEXT_50 = "</li>";
  protected final String TEXT_51 = NL + "\t\t\t\t\t</ul>";
  protected final String TEXT_52 = NL;
  protected final String TEXT_53 = "\t\t" + NL + "\t\t<h1>Data Types</h1>";
  protected final String TEXT_54 = NL + "\t\t\t<h2><a name=\"";
  protected final String TEXT_55 = "\">";
  protected final String TEXT_56 = "</a></h2>" + NL + "\t\t\t\t<h3>Details</h3>" + NL + "\t\t\t\t\t<table border=\"1\" cellpadding=\"2\" cellspacing=\"0\" summary=\"Package details.\">" + NL + "\t\t\t\t\t\t<tr>" + NL + "\t\t\t\t\t\t\t<td>Instance Class</td>" + NL + "\t\t\t\t\t\t\t<td>";
  protected final String TEXT_57 = "</td>" + NL + "\t\t\t\t\t\t</tr>" + NL + "\t\t\t\t\t\t<tr>" + NL + "\t\t\t\t\t\t\t<td>Is Serializable</td>" + NL + "\t\t\t\t\t\t\t<td>";
  protected final String TEXT_58 = "</td>" + NL + "\t\t\t\t\t\t</tr>" + NL + "\t\t\t\t\t</table>";
  protected final String TEXT_59 = NL + "\t</body>" + NL + "</html>";
  protected final String TEXT_60 = NL;

  public String generate(Object argument)
  {
    final StringBuffer stringBuffer = new StringBuffer();
    
/**
 * <copyright>
 *
 * Copyright (c) 2006 IBM Corporation and others.
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

    HTMLExporter htmlExporter = (HTMLExporter)argument;
    GenPackage genPackage = htmlExporter.getCurrentGenPackage();
    EPackage ePackage = genPackage.getEcorePackage();
    stringBuffer.append(TEXT_1);
    stringBuffer.append(genPackage.getQualifiedPackageName());
    stringBuffer.append(TEXT_2);
    stringBuffer.append(genPackage.getQualifiedPackageName());
    stringBuffer.append(TEXT_3);
    stringBuffer.append(ePackage.eResource().getURI().toString());
    stringBuffer.append(TEXT_4);
    stringBuffer.append(genPackage.getGenModel().eResource().getURI().toString());
    stringBuffer.append(TEXT_5);
    Collection eClasses = EcoreUtil.getObjectsByType(ePackage.getEClassifiers(), EcorePackage.Literals.ECLASS);
    if (!eClasses.isEmpty()) {
    stringBuffer.append(TEXT_6);
    for (Iterator eClassIterator = eClasses.iterator(); eClassIterator.hasNext();) { EClass eClass = (EClass)eClassIterator.next();
    stringBuffer.append(TEXT_7);
    stringBuffer.append(eClass.getName());
    stringBuffer.append(TEXT_8);
    stringBuffer.append(eClass.getName());
    stringBuffer.append(TEXT_9);
    stringBuffer.append(eClass.isAbstract());
    stringBuffer.append(TEXT_10);
    stringBuffer.append(eClass.isInterface());
    stringBuffer.append(TEXT_11);
    if (!eClass.getESuperTypes().isEmpty()) {
    stringBuffer.append(TEXT_12);
    for (Iterator eSuperClassIterator = eClass.getESuperTypes().iterator(); eSuperClassIterator.hasNext();) { EClass eSuperClass = (EClass)eSuperClassIterator.next();
    stringBuffer.append(TEXT_13);
    stringBuffer.append(htmlExporter.computeClassifierLabel(eSuperClass));
    stringBuffer.append(TEXT_14);
    }
    stringBuffer.append(TEXT_15);
    }
    stringBuffer.append(TEXT_16);
    if (!eClass.getEAttributes().isEmpty()) {
    stringBuffer.append(TEXT_17);
    for (Iterator eAttributeIterator = eClass.getEAttributes().iterator(); eAttributeIterator.hasNext();) { EAttribute eAttribute = (EAttribute)eAttributeIterator.next();
    stringBuffer.append(TEXT_18);
    stringBuffer.append(htmlExporter.computeClassifierLabel(eAttribute.getEType()));
    if (eAttribute.isMany()){
    stringBuffer.append(TEXT_19);
    }
    stringBuffer.append(TEXT_20);
    stringBuffer.append(eAttribute.getName());
    stringBuffer.append(TEXT_21);
    }
    stringBuffer.append(TEXT_22);
    }
    stringBuffer.append(TEXT_23);
    if (!eClass.getEOperations().isEmpty()) {
    stringBuffer.append(TEXT_24);
    for (Iterator eOperationIterator = eClass.getEOperations().iterator(); eOperationIterator.hasNext();) { EOperation eOperation = (EOperation)eOperationIterator.next();
    stringBuffer.append(TEXT_25);
    stringBuffer.append(htmlExporter.computeClassifierLabel(eOperation.getEType(), "void"));
    if (eOperation.isMany()){
    stringBuffer.append(TEXT_26);
    }
    stringBuffer.append(TEXT_27);
    stringBuffer.append(eOperation.getName());
    stringBuffer.append(TEXT_28);
    for (Iterator eParameterIterator = eOperation.getEParameters().iterator(); eParameterIterator.hasNext();) { EParameter eParameter = (EParameter)eParameterIterator.next();
    stringBuffer.append(TEXT_29);
    stringBuffer.append(htmlExporter.computeClassifierLabel(eParameter.getEType()));
    if (eParameter.isMany()){
    stringBuffer.append(TEXT_30);
    }
    stringBuffer.append(TEXT_31);
    if (eParameterIterator.hasNext()) {
    stringBuffer.append(TEXT_32);
    }}
    stringBuffer.append(TEXT_33);
    }
    stringBuffer.append(TEXT_34);
    }
    stringBuffer.append(TEXT_35);
    if (!eClass.getEReferences().isEmpty()) {
    stringBuffer.append(TEXT_36);
    for (Iterator eReferenceIterator = eClass.getEReferences().iterator(); eReferenceIterator.hasNext();) { EReference eReference = (EReference)eReferenceIterator.next();
    stringBuffer.append(TEXT_37);
    stringBuffer.append(htmlExporter.computeClassifierLabel(eReference.getEType()));
    if (eReference.isMany()){
    stringBuffer.append(TEXT_38);
    }
    stringBuffer.append(TEXT_39);
    stringBuffer.append(eReference.getName());
    stringBuffer.append(TEXT_40);
    }
    stringBuffer.append(TEXT_41);
    }
    stringBuffer.append(TEXT_42);
    }
    }
    stringBuffer.append(TEXT_43);
    Collection eEnums = EcoreUtil.getObjectsByType(ePackage.getEClassifiers(), EcorePackage.Literals.EENUM);
    if (!eEnums.isEmpty()) {
    stringBuffer.append(TEXT_44);
    for (Iterator eEnumIterator = eEnums.iterator(); eEnumIterator.hasNext();) { EEnum eEnum = (EEnum)eEnumIterator.next();
    stringBuffer.append(TEXT_45);
    stringBuffer.append(eEnum.getName());
    stringBuffer.append(TEXT_46);
    stringBuffer.append(eEnum.getName());
    stringBuffer.append(TEXT_47);
    if (!eEnum.getELiterals().isEmpty()) {
    stringBuffer.append(TEXT_48);
    for (Iterator eEnumLiteralIterator = eEnum.getELiterals().iterator(); eEnumLiteralIterator.hasNext();) { EEnumLiteral eEnumLiteral = (EEnumLiteral)eEnumLiteralIterator.next();
    stringBuffer.append(TEXT_49);
    stringBuffer.append(eEnumLiteral.getName());
    stringBuffer.append(TEXT_50);
    }
    stringBuffer.append(TEXT_51);
    }
    }
    }
    stringBuffer.append(TEXT_52);
    Collection eDataTypes = EcoreUtil.getObjectsByType(ePackage.getEClassifiers(), EcorePackage.Literals.EDATA_TYPE); eDataTypes.removeAll(eEnums);
    if (!eDataTypes.isEmpty()) {
    stringBuffer.append(TEXT_53);
    for (Iterator eDataTypeIterator = eDataTypes.iterator(); eDataTypeIterator.hasNext();) { EDataType eDataType = (EDataType)eDataTypeIterator.next();
    stringBuffer.append(TEXT_54);
    stringBuffer.append(eDataType.getName());
    stringBuffer.append(TEXT_55);
    stringBuffer.append(eDataType.getName());
    stringBuffer.append(TEXT_56);
    stringBuffer.append(eDataType.getInstanceClass().getName());
    stringBuffer.append(TEXT_57);
    stringBuffer.append(eDataType.isSerializable());
    stringBuffer.append(TEXT_58);
    }
    }
    stringBuffer.append(TEXT_59);
    stringBuffer.append(TEXT_60);
    return stringBuffer.toString();
  }
}
