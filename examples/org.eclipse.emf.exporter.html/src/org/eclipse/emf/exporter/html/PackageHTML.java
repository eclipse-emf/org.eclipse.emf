package org.eclipse.emf.exporter.html;

import java.util.*;
import org.eclipse.emf.common.util.URI;
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

  public final String NL = nl == null ? (System.getProperties().getProperty("line.separator")) : nl;
  protected final String TEXT_1 = "<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Strict//EN\" \"http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd\">" + NL + "<html xmlns=\"http://www.w3.org/1999/xhtml\">" + NL + "\t<head>" + NL + "\t\t<meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\" />" + NL + "\t\t<title>";
  protected final String TEXT_2 = "</title>" + NL + "\t\t" + NL + "\t\t<style>" + NL + "\t\t<!--" + NL + "\t\t\tbody {font-size: 10pt; clip: rect(); margin-top: 5mm; margin-left: 3mm}" + NL + "\t\t\th1 {font-size: 22px; font-weight: bold; color:white; background-color:#595791}" + NL + "\t\t\th2 {font-size: 16pt; font-weight: bold ; line-height: 20px; background-color:#FFFFCF}" + NL + "\t\t\th3 {font-size: 12pt}" + NL + "\t\t\tp, table, td, th {font-size: 10pt}" + NL + "\t\t\tcode {font-size: 10pt}" + NL + "\t\t\tli {font-size: 10pt; line-height: 20px}" + NL + "\t\t\t" + NL + "\t\t\ttable.details {font: 11px/24px Verdana, Arial, Helvetica, sans-serif; border-collapse: collapse; width: 480px;}" + NL + "\t\t\ttable.details td.left  {order-bottom: 1px solid #CCC;\tpadding: 0 0.5em; vertical-align: text-top;}" + NL + "\t\t\ttable.details td.right {order-bottom: 1px solid #CCC;\tpadding: 0 0.5em; text-align: right; vertical-align: text-top;}" + NL + "\t\t\ttable.details tr.documentation {background: #f9fff9;}" + NL + "\t\t\ttable.details tr.odd           {background: #e3f0f7;}" + NL + "\t\t\ttable.details tr.even          {background: #f7f7f7;}" + NL + "\t\t-->" + NL + "\t\t</style>" + NL + "\t\t" + NL + "\t\t<script language=\"JavaScript\" type=\"text/javascript\">" + NL + "\t\t<!--" + NL + "\t\t\tfunction openSection(sectionId, open)" + NL + "\t\t\t{" + NL + "\t\t\t\tvar section = document.getElementById(sectionId);" + NL + "\t\t\t\tsection.style.display = open ? 'block' : 'none';" + NL + "\t\t\t}" + NL + "\t\t\t" + NL + "\t\t\tfunction selectAllChechboxes(elements, select)" + NL + "\t\t\t{" + NL + "\t\t\t\tfor (var i = 0; i < elements.length; i++)" + NL + "\t\t\t\t{" + NL + "\t\t\t\t\telements[i].checked = select;" + NL + "\t\t\t\t\topenSection(elements[i].value, select);" + NL + "\t\t\t\t}" + NL + "\t\t\t}" + NL + "\t\t// -->" + NL + "\t\t</script>" + NL + "\t</head>" + NL + "" + NL + "\t<body lang=\"EN-US\"><form name=\"packform\" method=\"post\">" + NL + "\t\t<h1>Package: <i>";
  protected final String TEXT_3 = "</i></h1>" + NL + "\t\t\t<table border=\"1\" cellpadding=\"2\" cellspacing=\"0\" summary=\"Package details.\">" + NL + "\t\t\t";
  protected final String TEXT_4 = NL + "\t\t\t\t<tr>" + NL + "\t\t\t\t\t<td>Super Package</td>" + NL + "\t\t\t";
  protected final String TEXT_5 = NL + "\t\t\t\t\t<td><a href=\"";
  protected final String TEXT_6 = "\">";
  protected final String TEXT_7 = "</a></td>" + NL + "\t\t\t";
  protected final String TEXT_8 = NL + "\t\t\t\t\t<td>";
  protected final String TEXT_9 = "</td>" + NL + "\t\t\t";
  protected final String TEXT_10 = NL + "\t\t\t\t</tr>" + NL + "\t\t\t";
  protected final String TEXT_11 = NL + "\t\t\t\t<tr>" + NL + "\t\t\t\t\t<td>Ecore URI</td>" + NL + "\t\t\t\t\t<td>";
  protected final String TEXT_12 = "</td>" + NL + "\t\t\t\t</tr>" + NL + "\t\t\t\t<tr>" + NL + "\t\t\t\t\t<td>GenModel URI</td>" + NL + "\t\t\t\t\t<td>";
  protected final String TEXT_13 = "</td>" + NL + "\t\t\t\t</tr>" + NL + "\t\t\t\t<tr>" + NL + "\t\t\t\t\t<td>Namespace URI</td>" + NL + "\t\t\t\t\t<td>";
  protected final String TEXT_14 = "</td>" + NL + "\t\t\t\t</tr>" + NL + "\t\t\t\t<tr>" + NL + "\t\t\t\t\t<td>Namespace Prefix</td>" + NL + "\t\t\t\t\t<td>";
  protected final String TEXT_15 = "</td>" + NL + "\t\t\t\t</tr>" + NL + "\t\t";
  protected final String TEXT_16 = NL + "\t\t\t\t<tr>" + NL + "\t\t\t\t\t<td>Documentation</td>" + NL + "\t\t\t\t\t<td>";
  protected final String TEXT_17 = NL + "\t\t\t</table>" + NL + "" + NL + "\t\t\t<p><input type=\"checkbox\" onclick=\"javascript:selectAllChechboxes(document.packform.detailCheckbox, this.checked)\"/>expand all details</p>" + NL;
  protected final String TEXT_18 = NL + "\t\t<h1>Classes</h1>";
  protected final String TEXT_19 = NL + "\t\t\t<h2>";
  protected final String TEXT_20 = "</h2>" + NL + "\t\t";
  protected final String TEXT_21 = NL + "\t\t\t\t<h3>Documentation</h3>" + NL + "\t\t\t\t\t";
  protected final String TEXT_22 = NL + "\t\t";
  protected final String TEXT_23 = NL + "\t\t\t\t<h3>Details</h3>" + NL + "\t\t\t\t<dt>" + NL + "\t\t\t\t\t<dd><table class=\"details\">";
  protected final String TEXT_24 = NL + "\t\t\t\t\t\t<tr class=\"";
  protected final String TEXT_25 = "\"><td class=\"left\">constraints</td><td class=\"right\">";
  protected final String TEXT_26 = "</td></tr>";
  protected final String TEXT_27 = "\"><td class=\"left\">";
  protected final String TEXT_28 = "</td><td class=\"right\"><tt>";
  protected final String TEXT_29 = "</tt></td></tr>";
  protected final String TEXT_30 = NL + "\t\t\t\t\t</table></dd>" + NL + "\t\t\t\t</dt>" + NL + "\t\t";
  protected final String TEXT_31 = NL + "\t\t\t\t<h3>Super Classes</h3>" + NL + "\t\t\t\t\t<ul>";
  protected final String TEXT_32 = NL + "\t\t\t\t\t\t<li><tt>";
  protected final String TEXT_33 = "</tt></li>";
  protected final String TEXT_34 = NL + "\t\t\t\t\t</ul>" + NL + "\t\t";
  protected final String TEXT_35 = NL + "\t\t\t\t<h3>Attributes</h3>" + NL + "\t\t\t\t\t<ul>";
  protected final String TEXT_36 = NL + "\t\t\t\t\t\t<li>" + NL + "\t\t\t\t\t\t\t<input type=\"checkbox\" name=\"detailCheckbox\" value=\"detail";
  protected final String TEXT_37 = "\" onclick=\"javascript:openSection(this.value, this.checked)\"/> ";
  protected final String TEXT_38 = NL + "\t\t\t\t\t\t\t<div id=\"detail";
  protected final String TEXT_39 = "\" name=\"detail";
  protected final String TEXT_40 = "\" style=\"overflow:hidden;display:none\">" + NL + "\t\t\t\t\t\t\t\t<table class=\"details\">";
  protected final String TEXT_41 = NL + "\t\t\t\t\t\t\t\t\t<tr class=\"documentation\"><td class=\"left\" colspan=\"2\">";
  protected final String TEXT_42 = NL + "\t\t\t\t\t\t\t\t\t<tr class=\"";
  protected final String TEXT_43 = NL + "\t\t\t\t\t\t\t\t</table><br />" + NL + "\t\t\t\t\t\t\t</div>" + NL + "\t\t\t\t\t\t</li>";
  protected final String TEXT_44 = NL + "\t\t\t\t<h3>References</h3>" + NL + "\t\t\t\t\t<ul>";
  protected final String TEXT_45 = "\"><td class=\"left\">opposite</td><td class=\"right\">";
  protected final String TEXT_46 = "</td></tr> ";
  protected final String TEXT_47 = "\"><td class=\"left\">key(s)</td><td class=\"right\">";
  protected final String TEXT_48 = NL + "\t\t\t\t<h3>Operations</h3>" + NL + "\t\t\t\t\t<ul>";
  protected final String TEXT_49 = NL + "\t";
  protected final String TEXT_50 = NL;
  protected final String TEXT_51 = "\t\t" + NL + "\t\t<h1>Enumerations</h1>";
  protected final String TEXT_52 = NL + "\t\t\t<h2><a name=\"";
  protected final String TEXT_53 = "</a></h2>";
  protected final String TEXT_54 = NL + "\t\t\t\t<h3>Literals</h3>" + NL + "\t\t\t\t\t<ul>";
  protected final String TEXT_55 = NL + "\t\t\t\t\t</ul>";
  protected final String TEXT_56 = "\t\t" + NL + "\t\t<h1>Data Types</h1>";
  protected final String TEXT_57 = NL + "\t\t\t\t<h3>Details</h3>" + NL + "\t\t\t\t<dt>" + NL + "\t\t\t\t\t<dd>" + NL + "\t\t\t\t\t\t<table class=\"details\">";
  protected final String TEXT_58 = NL + "\t\t\t\t\t\t\t<tr class=\"";
  protected final String TEXT_59 = NL + "\t\t\t\t\t\t</table>" + NL + "\t\t\t\t\t</dd>";
  protected final String TEXT_60 = NL + "\t\t\t\t</dt>";
  protected final String TEXT_61 = NL + "\t</form></body>" + NL + "</html>";

  public String generate(Object argument)
  {
    final StringBuffer stringBuffer = new StringBuffer();
    
/**
 * Copyright (c) 2006-2009 IBM Corporation and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 * 
 * Contributors: 
 *   IBM - Initial API and implementation
 */

    HTMLExporter htmlExporter = (HTMLExporter)argument;
    GenPackage genPackage = htmlExporter.getCurrentGenPackage();
    EPackage ePackage = genPackage.getEcorePackage();
    stringBuffer.append(TEXT_1);
    stringBuffer.append(genPackage.getQualifiedPackageName());
    stringBuffer.append(TEXT_2);
    stringBuffer.append(genPackage.getQualifiedPackageName());
    stringBuffer.append(TEXT_3);
    GenPackage superGenPackage = genPackage.getSuperGenPackage(); if (superGenPackage != null) { URI packageArtifactURI = htmlExporter.getPackageArtifacttURI(superGenPackage.getEcorePackage());
    stringBuffer.append(TEXT_4);
    if (packageArtifactURI != null) {
    stringBuffer.append(TEXT_5);
    stringBuffer.append(packageArtifactURI.toString());
    stringBuffer.append(TEXT_6);
    stringBuffer.append(superGenPackage.getQualifiedPackageName());
    stringBuffer.append(TEXT_7);
    } else {
    stringBuffer.append(TEXT_8);
    stringBuffer.append(superGenPackage.getQualifiedPackageName());
    stringBuffer.append(TEXT_9);
    }
    stringBuffer.append(TEXT_10);
    }
    stringBuffer.append(TEXT_11);
    stringBuffer.append(ePackage.eResource().getURI().toString());
    stringBuffer.append(TEXT_12);
    stringBuffer.append(genPackage.getGenModel().eResource().getURI().toString());
    stringBuffer.append(TEXT_13);
    stringBuffer.append(ePackage.getNsURI());
    stringBuffer.append(TEXT_14);
    stringBuffer.append(ePackage.getNsPrefix());
    stringBuffer.append(TEXT_15);
    String packageDocumentation = EcoreUtil.getDocumentation(ePackage); if (packageDocumentation != null) {
    stringBuffer.append(TEXT_16);
    stringBuffer.append(htmlExporter.getLongText(packageDocumentation));
    stringBuffer.append(TEXT_15);
    }
    stringBuffer.append(TEXT_17);
    int detailsCounter=0; Collection<EClass> eClasses = EcoreUtil.getObjectsByType(ePackage.getEClassifiers(), EcorePackage.Literals.ECLASS);
    if (!eClasses.isEmpty()) {
    stringBuffer.append(TEXT_18);
    for (EClass eClass : eClasses) { 
    stringBuffer.append(TEXT_19);
    stringBuffer.append(htmlExporter.computeClassifierLabel(eClass));
    stringBuffer.append(TEXT_20);
    String classDocumentation = EcoreUtil.getDocumentation(eClass); if (classDocumentation != null) {
    stringBuffer.append(TEXT_21);
    stringBuffer.append(htmlExporter.getLongText(classDocumentation));
    stringBuffer.append(TEXT_22);
    }
    stringBuffer.append(TEXT_23);
    int row=0; String classConstraints = htmlExporter.computeConstraints(eClass); if (classConstraints != null) {
    stringBuffer.append(TEXT_24);
    stringBuffer.append((++row)%2 == 0 ? "even" : "odd");
    stringBuffer.append(TEXT_25);
    stringBuffer.append(classConstraints);
    stringBuffer.append(TEXT_26);
    } for (EStructuralFeature detail : htmlExporter.getDetails(eClass)) {
    stringBuffer.append(TEXT_24);
    stringBuffer.append((++row)%2 == 0 ? "even" : "odd");
    stringBuffer.append(TEXT_27);
    stringBuffer.append(detail.getName());
    stringBuffer.append(TEXT_28);
    stringBuffer.append(HTMLExporter.escape(eClass.eGet(detail).toString()));
    stringBuffer.append(TEXT_29);
    }
    stringBuffer.append(TEXT_30);
    if (!eClass.getEGenericSuperTypes().isEmpty()) {
    stringBuffer.append(TEXT_31);
    for (EGenericType eSuperType : eClass.getEGenericSuperTypes()) {
    stringBuffer.append(TEXT_32);
    stringBuffer.append(htmlExporter.computeLabel(eSuperType));
    stringBuffer.append(TEXT_33);
    }
    stringBuffer.append(TEXT_34);
    }
    stringBuffer.append(TEXT_22);
    if (!eClass.getEAttributes().isEmpty()) {
    stringBuffer.append(TEXT_35);
    for (EAttribute eAttribute : eClass.getEAttributes()) {
    stringBuffer.append(TEXT_36);
    stringBuffer.append(++detailsCounter);
    stringBuffer.append(TEXT_37);
    stringBuffer.append(htmlExporter.computeTypedElementLabel(eAttribute));
    stringBuffer.append(TEXT_38);
    stringBuffer.append(detailsCounter);
    stringBuffer.append(TEXT_39);
    stringBuffer.append(detailsCounter);
    stringBuffer.append(TEXT_40);
    String attributeDocumentation = EcoreUtil.getDocumentation(eAttribute); if (attributeDocumentation != null) {
    stringBuffer.append(TEXT_41);
    stringBuffer.append(attributeDocumentation);
    stringBuffer.append(TEXT_26);
    } row=0; String attributeConstraints = htmlExporter.computeConstraints(eAttribute); if (attributeConstraints != null) {
    stringBuffer.append(TEXT_42);
    stringBuffer.append((++row)%2 == 0 ? "even" : "odd");
    stringBuffer.append(TEXT_25);
    stringBuffer.append(attributeConstraints);
    stringBuffer.append(TEXT_26);
    } for (EStructuralFeature detail : htmlExporter.getDetails(eAttribute)) {
    stringBuffer.append(TEXT_42);
    stringBuffer.append((++row)%2 == 0 ? "even" : "odd");
    stringBuffer.append(TEXT_27);
    stringBuffer.append(detail.getName());
    stringBuffer.append(TEXT_28);
    stringBuffer.append(eAttribute.eGet(detail));
    stringBuffer.append(TEXT_29);
    }
    stringBuffer.append(TEXT_43);
    }
    stringBuffer.append(TEXT_34);
    }
    stringBuffer.append(TEXT_22);
    if (!eClass.getEReferences().isEmpty()) {
    stringBuffer.append(TEXT_44);
    for (EReference eReference : eClass.getEReferences()) {
    stringBuffer.append(TEXT_36);
    stringBuffer.append(++detailsCounter);
    stringBuffer.append(TEXT_37);
    stringBuffer.append(htmlExporter.computeTypedElementLabel(eReference));
    stringBuffer.append(TEXT_38);
    stringBuffer.append(detailsCounter);
    stringBuffer.append(TEXT_39);
    stringBuffer.append(detailsCounter);
    stringBuffer.append(TEXT_40);
    String referenceDocumentation = EcoreUtil.getDocumentation(eReference); if (referenceDocumentation != null) {
    stringBuffer.append(TEXT_41);
    stringBuffer.append(referenceDocumentation);
    stringBuffer.append(TEXT_26);
    } row=0; String referenceConstraints = htmlExporter.computeConstraints(eReference); if (referenceConstraints != null) {
    stringBuffer.append(TEXT_42);
    stringBuffer.append((++row)%2 == 0 ? "even" : "odd");
    stringBuffer.append(TEXT_25);
    stringBuffer.append(referenceConstraints);
    stringBuffer.append(TEXT_26);
    } if (eReference.getEOpposite() != null) {
    stringBuffer.append(TEXT_42);
    stringBuffer.append((++row)%2 == 0 ? "even" : "odd");
    stringBuffer.append(TEXT_45);
    stringBuffer.append(htmlExporter.computeLabel(eReference.getEOpposite()));
    stringBuffer.append(TEXT_46);
    } if (!eReference.getEKeys().isEmpty()) {
    stringBuffer.append(TEXT_42);
    stringBuffer.append((++row)%2 == 0 ? "even" : "odd");
    stringBuffer.append(TEXT_47);
    stringBuffer.append(htmlExporter.computeEKeys(eReference));
    stringBuffer.append(TEXT_46);
    } for (EStructuralFeature detail : htmlExporter.getDetails(eReference)) {
    stringBuffer.append(TEXT_42);
    stringBuffer.append((++row)%2 == 0 ? "even" : "odd");
    stringBuffer.append(TEXT_27);
    stringBuffer.append(detail.getName());
    stringBuffer.append(TEXT_28);
    stringBuffer.append(eReference.eGet(detail));
    stringBuffer.append(TEXT_29);
    }
    stringBuffer.append(TEXT_43);
    }
    stringBuffer.append(TEXT_34);
    }
    stringBuffer.append(TEXT_22);
    if (!eClass.getEOperations().isEmpty()) {
    stringBuffer.append(TEXT_48);
    for (EOperation eOperation : eClass.getEOperations()) {
    stringBuffer.append(TEXT_36);
    stringBuffer.append(++detailsCounter);
    stringBuffer.append(TEXT_37);
    stringBuffer.append(htmlExporter.computeLabel(eOperation));
    stringBuffer.append(TEXT_38);
    stringBuffer.append(detailsCounter);
    stringBuffer.append(TEXT_39);
    stringBuffer.append(detailsCounter);
    stringBuffer.append(TEXT_40);
    String operationDocumentation = EcoreUtil.getDocumentation(eOperation); if (operationDocumentation != null) {
    stringBuffer.append(TEXT_41);
    stringBuffer.append(operationDocumentation);
    stringBuffer.append(TEXT_26);
    } row=0; String operationConstraints = htmlExporter.computeConstraints(eOperation); if (operationConstraints != null) {
    stringBuffer.append(TEXT_42);
    stringBuffer.append((++row)%2 == 0 ? "even" : "odd");
    stringBuffer.append(TEXT_25);
    stringBuffer.append(operationConstraints);
    stringBuffer.append(TEXT_26);
    } for (EStructuralFeature detail : htmlExporter.getDetails(eOperation)) {
    stringBuffer.append(TEXT_42);
    stringBuffer.append((++row)%2 == 0 ? "even" : "odd");
    stringBuffer.append(TEXT_27);
    stringBuffer.append(detail.getName());
    stringBuffer.append(TEXT_28);
    stringBuffer.append(eOperation.eGet(detail));
    stringBuffer.append(TEXT_29);
    }
    stringBuffer.append(TEXT_43);
    }
    stringBuffer.append(TEXT_34);
    }
    stringBuffer.append(TEXT_49);
    }
    }
    stringBuffer.append(TEXT_50);
    Collection<EEnum> eEnums = EcoreUtil.getObjectsByType(ePackage.getEClassifiers(), EcorePackage.Literals.EENUM);
    if (!eEnums.isEmpty()) {
    stringBuffer.append(TEXT_51);
    for (EEnum eEnum : eEnums) {
    stringBuffer.append(TEXT_52);
    stringBuffer.append(eEnum.getName());
    stringBuffer.append(TEXT_6);
    stringBuffer.append(eEnum.getName());
    stringBuffer.append(TEXT_53);
    if (!eEnum.getELiterals().isEmpty()) {
    stringBuffer.append(TEXT_22);
    String enumDocumentation = EcoreUtil.getDocumentation(eEnum); if (enumDocumentation != null) {
    stringBuffer.append(TEXT_21);
    stringBuffer.append(htmlExporter.getLongText(enumDocumentation));
    stringBuffer.append(TEXT_22);
    }
    stringBuffer.append(TEXT_54);
    for (EEnumLiteral eEnumLiteral : eEnum.getELiterals()) {
    stringBuffer.append(TEXT_36);
    stringBuffer.append(++detailsCounter);
    stringBuffer.append(TEXT_37);
    stringBuffer.append(htmlExporter.computeLabel(eEnumLiteral));
    stringBuffer.append(TEXT_38);
    stringBuffer.append(detailsCounter);
    stringBuffer.append(TEXT_39);
    stringBuffer.append(detailsCounter);
    stringBuffer.append(TEXT_40);
    String enumLiteralDocumentation = EcoreUtil.getDocumentation(eEnumLiteral); if (enumLiteralDocumentation != null) {
    stringBuffer.append(TEXT_41);
    stringBuffer.append(enumLiteralDocumentation);
    stringBuffer.append(TEXT_26);
    } int row = 0; for (EStructuralFeature detail : htmlExporter.getDetails(eEnumLiteral)) {
    stringBuffer.append(TEXT_42);
    stringBuffer.append((++row)%2 == 0 ? "even" : "odd");
    stringBuffer.append(TEXT_27);
    stringBuffer.append(detail.getName());
    stringBuffer.append(TEXT_28);
    stringBuffer.append(eEnumLiteral.eGet(detail));
    stringBuffer.append(TEXT_29);
    }
    stringBuffer.append(TEXT_43);
    }
    stringBuffer.append(TEXT_55);
    }
    }
    }
    stringBuffer.append(TEXT_50);
    Collection<EDataType> eDataTypes = EcoreUtil.getObjectsByType(ePackage.getEClassifiers(), EcorePackage.Literals.EDATA_TYPE); eDataTypes.removeAll(eEnums);
    if (!eDataTypes.isEmpty()) {
    stringBuffer.append(TEXT_56);
    for (EDataType eDataType : eDataTypes) {
    stringBuffer.append(TEXT_19);
    stringBuffer.append(htmlExporter.computeClassifierLabel(eDataType));
    stringBuffer.append(TEXT_20);
    String dataTypeDocumentation = EcoreUtil.getDocumentation(eDataType); if (dataTypeDocumentation != null) {
    stringBuffer.append(TEXT_21);
    stringBuffer.append(htmlExporter.getLongText(dataTypeDocumentation));
    stringBuffer.append(TEXT_22);
    }
    stringBuffer.append(TEXT_57);
    int row = 0; for (EStructuralFeature detail : htmlExporter.getDetails(eDataType)) {
    stringBuffer.append(TEXT_58);
    stringBuffer.append((++row)%2 == 0 ? "even" : "odd");
    stringBuffer.append(TEXT_27);
    stringBuffer.append(detail.getName());
    stringBuffer.append(TEXT_28);
    stringBuffer.append(HTMLExporter.escape(eDataType.eGet(detail).toString()));
    stringBuffer.append(TEXT_29);
    }
    stringBuffer.append(TEXT_59);
    }
    stringBuffer.append(TEXT_60);
    }
    stringBuffer.append(TEXT_61);
    stringBuffer.append(TEXT_50);
    return stringBuffer.toString();
  }
}
