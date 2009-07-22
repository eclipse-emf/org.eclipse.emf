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
  protected final String TEXT_17 = "</td>" + NL + "\t\t\t\t</tr>" + NL + "\t\t";
  protected final String TEXT_18 = NL + "\t\t\t</table>" + NL + "" + NL + "\t\t\t<p><input type=\"checkbox\" onclick=\"javascript:selectAllChechboxes(document.packform.detailCheckbox, this.checked)\"/>expand all details</p>" + NL;
  protected final String TEXT_19 = NL + "\t\t<h1>Classes</h1>";
  protected final String TEXT_20 = NL + "\t\t\t<h2>";
  protected final String TEXT_21 = "</h2>" + NL + "\t\t";
  protected final String TEXT_22 = NL + "\t\t\t\t<h3>Documentation</h3>" + NL + "\t\t\t\t\t";
  protected final String TEXT_23 = NL + "\t\t";
  protected final String TEXT_24 = NL + "\t\t\t\t<h3>Details</h3>" + NL + "\t\t\t\t<dt>" + NL + "\t\t\t\t\t<dd><table class=\"details\">";
  protected final String TEXT_25 = NL + "\t\t\t\t\t\t<tr class=\"";
  protected final String TEXT_26 = "\"><td class=\"left\">constraints</td><td class=\"right\">";
  protected final String TEXT_27 = "</td></tr>";
  protected final String TEXT_28 = NL + "\t\t\t\t\t\t<tr class=\"";
  protected final String TEXT_29 = "\"><td class=\"left\">";
  protected final String TEXT_30 = "</td><td class=\"right\"><tt>";
  protected final String TEXT_31 = "</tt></td></tr>";
  protected final String TEXT_32 = NL + "\t\t\t\t\t</table></dd>" + NL + "\t\t\t\t</dt>" + NL + "\t\t";
  protected final String TEXT_33 = NL + "\t\t\t\t<h3>Super Classes</h3>" + NL + "\t\t\t\t\t<ul>";
  protected final String TEXT_34 = NL + "\t\t\t\t\t\t<li><tt>";
  protected final String TEXT_35 = "</tt></li>";
  protected final String TEXT_36 = NL + "\t\t\t\t\t</ul>" + NL + "\t\t";
  protected final String TEXT_37 = NL + "\t\t";
  protected final String TEXT_38 = NL + "\t\t\t\t<h3>Attributes</h3>" + NL + "\t\t\t\t\t<ul>";
  protected final String TEXT_39 = NL + "\t\t\t\t\t\t<li>" + NL + "\t\t\t\t\t\t\t<input type=\"checkbox\" name=\"detailCheckbox\" value=\"detail";
  protected final String TEXT_40 = "\" onclick=\"javascript:openSection(this.value, this.checked)\"/> ";
  protected final String TEXT_41 = NL + "\t\t\t\t\t\t\t<div id=\"detail";
  protected final String TEXT_42 = "\" name=\"detail";
  protected final String TEXT_43 = "\" style=\"overflow:hidden;display:none\">" + NL + "\t\t\t\t\t\t\t\t<table class=\"details\">";
  protected final String TEXT_44 = NL + "\t\t\t\t\t\t\t\t\t<tr class=\"documentation\"><td class=\"left\" colspan=\"2\">";
  protected final String TEXT_45 = "</td></tr>";
  protected final String TEXT_46 = NL + "\t\t\t\t\t\t\t\t\t<tr class=\"";
  protected final String TEXT_47 = "\"><td class=\"left\">constraints</td><td class=\"right\">";
  protected final String TEXT_48 = "</td></tr>";
  protected final String TEXT_49 = NL + "\t\t\t\t\t\t\t\t\t<tr class=\"";
  protected final String TEXT_50 = "\"><td class=\"left\">";
  protected final String TEXT_51 = "</td><td class=\"right\"><tt>";
  protected final String TEXT_52 = "</tt></td></tr>";
  protected final String TEXT_53 = NL + "\t\t\t\t\t\t\t\t</table><br />" + NL + "\t\t\t\t\t\t\t</div>" + NL + "\t\t\t\t\t\t</li>";
  protected final String TEXT_54 = NL + "\t\t\t\t\t</ul>" + NL + "\t\t";
  protected final String TEXT_55 = NL + "\t\t";
  protected final String TEXT_56 = NL + "\t\t\t\t<h3>References</h3>" + NL + "\t\t\t\t\t<ul>";
  protected final String TEXT_57 = NL + "\t\t\t\t\t\t<li>" + NL + "\t\t\t\t\t\t\t<input type=\"checkbox\" name=\"detailCheckbox\" value=\"detail";
  protected final String TEXT_58 = "\" onclick=\"javascript:openSection(this.value, this.checked)\"/> ";
  protected final String TEXT_59 = NL + "\t\t\t\t\t\t\t<div id=\"detail";
  protected final String TEXT_60 = "\" name=\"detail";
  protected final String TEXT_61 = "\" style=\"overflow:hidden;display:none\">" + NL + "\t\t\t\t\t\t\t\t<table class=\"details\">";
  protected final String TEXT_62 = NL + "\t\t\t\t\t\t\t\t\t<tr class=\"documentation\"><td class=\"left\" colspan=\"2\">";
  protected final String TEXT_63 = "</td></tr>";
  protected final String TEXT_64 = NL + "\t\t\t\t\t\t\t\t\t<tr class=\"";
  protected final String TEXT_65 = "\"><td class=\"left\">constraints</td><td class=\"right\">";
  protected final String TEXT_66 = "</td></tr>";
  protected final String TEXT_67 = NL + "\t\t\t\t\t\t\t\t\t<tr class=\"";
  protected final String TEXT_68 = "\"><td class=\"left\">opposite</td><td class=\"right\">";
  protected final String TEXT_69 = "</td></tr> ";
  protected final String TEXT_70 = NL + "\t\t\t\t\t\t\t\t\t<tr class=\"";
  protected final String TEXT_71 = "\"><td class=\"left\">key(s)</td><td class=\"right\">";
  protected final String TEXT_72 = "</td></tr> ";
  protected final String TEXT_73 = NL + "\t\t\t\t\t\t\t\t\t<tr class=\"";
  protected final String TEXT_74 = "\"><td class=\"left\">";
  protected final String TEXT_75 = "</td><td class=\"right\"><tt>";
  protected final String TEXT_76 = "</tt></td></tr>";
  protected final String TEXT_77 = NL + "\t\t\t\t\t\t\t\t</table><br />" + NL + "\t\t\t\t\t\t\t</div>" + NL + "\t\t\t\t\t\t</li>";
  protected final String TEXT_78 = NL + "\t\t\t\t\t</ul>" + NL + "\t\t";
  protected final String TEXT_79 = NL + "\t\t";
  protected final String TEXT_80 = NL + "\t\t\t\t<h3>Operations</h3>" + NL + "\t\t\t\t\t<ul>";
  protected final String TEXT_81 = NL + "\t\t\t\t\t\t<li>" + NL + "\t\t\t\t\t\t\t<input type=\"checkbox\" name=\"detailCheckbox\" value=\"detail";
  protected final String TEXT_82 = "\" onclick=\"javascript:openSection(this.value, this.checked)\"/> ";
  protected final String TEXT_83 = NL + "\t\t\t\t\t\t\t<div id=\"detail";
  protected final String TEXT_84 = "\" name=\"detail";
  protected final String TEXT_85 = "\" style=\"overflow:hidden;display:none\">" + NL + "\t\t\t\t\t\t\t\t<table class=\"details\">";
  protected final String TEXT_86 = NL + "\t\t\t\t\t\t\t\t\t<tr class=\"documentation\"><td class=\"left\" colspan=\"2\">";
  protected final String TEXT_87 = "</td></tr>";
  protected final String TEXT_88 = NL + "\t\t\t\t\t\t\t\t\t<tr class=\"";
  protected final String TEXT_89 = "\"><td class=\"left\">constraints</td><td class=\"right\">";
  protected final String TEXT_90 = "</td></tr>";
  protected final String TEXT_91 = NL + "\t\t\t\t\t\t\t\t\t<tr class=\"";
  protected final String TEXT_92 = "\"><td class=\"left\">";
  protected final String TEXT_93 = "</td><td class=\"right\"><tt>";
  protected final String TEXT_94 = "</tt></td></tr>";
  protected final String TEXT_95 = NL + "\t\t\t\t\t\t\t\t</table><br />" + NL + "\t\t\t\t\t\t\t</div>" + NL + "\t\t\t\t\t\t</li>";
  protected final String TEXT_96 = NL + "\t\t\t\t\t</ul>" + NL + "\t\t";
  protected final String TEXT_97 = NL + "\t";
  protected final String TEXT_98 = NL;
  protected final String TEXT_99 = "\t\t" + NL + "\t\t<h1>Enumerations</h1>";
  protected final String TEXT_100 = NL + "\t\t\t<h2><a name=\"";
  protected final String TEXT_101 = "\">";
  protected final String TEXT_102 = "</a></h2>";
  protected final String TEXT_103 = NL + "\t\t";
  protected final String TEXT_104 = NL + "\t\t\t\t<h3>Documentation</h3>" + NL + "\t\t\t\t\t";
  protected final String TEXT_105 = NL + "\t\t";
  protected final String TEXT_106 = NL + "\t\t\t\t<h3>Literals</h3>" + NL + "\t\t\t\t\t<ul>";
  protected final String TEXT_107 = NL + "\t\t\t\t\t\t<li>" + NL + "\t\t\t\t\t\t\t<input type=\"checkbox\" name=\"detailCheckbox\" value=\"detail";
  protected final String TEXT_108 = "\" onclick=\"javascript:openSection(this.value, this.checked)\"/> ";
  protected final String TEXT_109 = NL + "\t\t\t\t\t\t\t<div id=\"detail";
  protected final String TEXT_110 = "\" name=\"detail";
  protected final String TEXT_111 = "\" style=\"overflow:hidden;display:none\">" + NL + "\t\t\t\t\t\t\t\t<table class=\"details\">";
  protected final String TEXT_112 = NL + "\t\t\t\t\t\t\t\t\t<tr class=\"documentation\"><td class=\"left\" colspan=\"2\">";
  protected final String TEXT_113 = "</td></tr>";
  protected final String TEXT_114 = NL + "\t\t\t\t\t\t\t\t\t<tr class=\"";
  protected final String TEXT_115 = "\"><td class=\"left\">";
  protected final String TEXT_116 = "</td><td class=\"right\"><tt>";
  protected final String TEXT_117 = "</tt></td></tr>";
  protected final String TEXT_118 = NL + "\t\t\t\t\t\t\t\t</table><br />" + NL + "\t\t\t\t\t\t\t</div>" + NL + "\t\t\t\t\t\t</li>";
  protected final String TEXT_119 = NL + "\t\t\t\t\t</ul>";
  protected final String TEXT_120 = NL;
  protected final String TEXT_121 = "\t\t" + NL + "\t\t<h1>Data Types</h1>";
  protected final String TEXT_122 = NL + "\t\t\t<h2>";
  protected final String TEXT_123 = "</h2>" + NL + "\t\t";
  protected final String TEXT_124 = NL + "\t\t\t\t<h3>Documentation</h3>" + NL + "\t\t\t\t\t";
  protected final String TEXT_125 = NL + "\t\t";
  protected final String TEXT_126 = NL + "\t\t\t\t<h3>Details</h3>" + NL + "\t\t\t\t<dt>" + NL + "\t\t\t\t\t<dd>" + NL + "\t\t\t\t\t\t<table class=\"details\">";
  protected final String TEXT_127 = NL + "\t\t\t\t\t\t\t<tr class=\"";
  protected final String TEXT_128 = "\"><td class=\"left\">";
  protected final String TEXT_129 = "</td><td class=\"right\"><tt>";
  protected final String TEXT_130 = "</tt></td></tr>";
  protected final String TEXT_131 = NL + "\t\t\t\t\t\t</table>" + NL + "\t\t\t\t\t</dd>";
  protected final String TEXT_132 = NL + "\t\t\t\t</dt>";
  protected final String TEXT_133 = NL + "\t</form></body>" + NL + "</html>";
  protected final String TEXT_134 = NL;

  public String generate(Object argument)
  {
    final StringBuffer stringBuffer = new StringBuffer();
    
/**
 * <copyright>
 *
 * Copyright (c) 2006-2009 IBM Corporation and others.
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
    stringBuffer.append(TEXT_17);
    }
    stringBuffer.append(TEXT_18);
    int detailsCounter=0; Collection<EClass> eClasses = EcoreUtil.getObjectsByType(ePackage.getEClassifiers(), EcorePackage.Literals.ECLASS);
    if (!eClasses.isEmpty()) {
    stringBuffer.append(TEXT_19);
    for (EClass eClass : eClasses) { 
    stringBuffer.append(TEXT_20);
    stringBuffer.append(htmlExporter.computeClassifierLabel(eClass));
    stringBuffer.append(TEXT_21);
    String classDocumentation = EcoreUtil.getDocumentation(eClass); if (classDocumentation != null) {
    stringBuffer.append(TEXT_22);
    stringBuffer.append(htmlExporter.getLongText(classDocumentation));
    stringBuffer.append(TEXT_23);
    }
    stringBuffer.append(TEXT_24);
    int row=0; String classConstraints = htmlExporter.computeConstraints(eClass); if (classConstraints != null) {
    stringBuffer.append(TEXT_25);
    stringBuffer.append((++row)%2 == 0 ? "even" : "odd");
    stringBuffer.append(TEXT_26);
    stringBuffer.append(classConstraints);
    stringBuffer.append(TEXT_27);
    } for (EStructuralFeature detail : htmlExporter.getDetails(eClass)) {
    stringBuffer.append(TEXT_28);
    stringBuffer.append((++row)%2 == 0 ? "even" : "odd");
    stringBuffer.append(TEXT_29);
    stringBuffer.append(detail.getName());
    stringBuffer.append(TEXT_30);
    stringBuffer.append(HTMLExporter.escape(eClass.eGet(detail).toString()));
    stringBuffer.append(TEXT_31);
    }
    stringBuffer.append(TEXT_32);
    if (!eClass.getEGenericSuperTypes().isEmpty()) {
    stringBuffer.append(TEXT_33);
    for (EGenericType eSuperType : eClass.getEGenericSuperTypes()) {
    stringBuffer.append(TEXT_34);
    stringBuffer.append(htmlExporter.computeLabel(eSuperType));
    stringBuffer.append(TEXT_35);
    }
    stringBuffer.append(TEXT_36);
    }
    stringBuffer.append(TEXT_37);
    if (!eClass.getEAttributes().isEmpty()) {
    stringBuffer.append(TEXT_38);
    for (EAttribute eAttribute : eClass.getEAttributes()) {
    stringBuffer.append(TEXT_39);
    stringBuffer.append(++detailsCounter);
    stringBuffer.append(TEXT_40);
    stringBuffer.append(htmlExporter.computeTypedElementLabel(eAttribute));
    stringBuffer.append(TEXT_41);
    stringBuffer.append(detailsCounter);
    stringBuffer.append(TEXT_42);
    stringBuffer.append(detailsCounter);
    stringBuffer.append(TEXT_43);
    String attributeDocumentation = EcoreUtil.getDocumentation(eAttribute); if (attributeDocumentation != null) {
    stringBuffer.append(TEXT_44);
    stringBuffer.append(attributeDocumentation);
    stringBuffer.append(TEXT_45);
    } row=0; String attributeConstraints = htmlExporter.computeConstraints(eAttribute); if (attributeConstraints != null) {
    stringBuffer.append(TEXT_46);
    stringBuffer.append((++row)%2 == 0 ? "even" : "odd");
    stringBuffer.append(TEXT_47);
    stringBuffer.append(attributeConstraints);
    stringBuffer.append(TEXT_48);
    } for (EStructuralFeature detail : htmlExporter.getDetails(eAttribute)) {
    stringBuffer.append(TEXT_49);
    stringBuffer.append((++row)%2 == 0 ? "even" : "odd");
    stringBuffer.append(TEXT_50);
    stringBuffer.append(detail.getName());
    stringBuffer.append(TEXT_51);
    stringBuffer.append(eAttribute.eGet(detail));
    stringBuffer.append(TEXT_52);
    }
    stringBuffer.append(TEXT_53);
    }
    stringBuffer.append(TEXT_54);
    }
    stringBuffer.append(TEXT_55);
    if (!eClass.getEReferences().isEmpty()) {
    stringBuffer.append(TEXT_56);
    for (EReference eReference : eClass.getEReferences()) {
    stringBuffer.append(TEXT_57);
    stringBuffer.append(++detailsCounter);
    stringBuffer.append(TEXT_58);
    stringBuffer.append(htmlExporter.computeTypedElementLabel(eReference));
    stringBuffer.append(TEXT_59);
    stringBuffer.append(detailsCounter);
    stringBuffer.append(TEXT_60);
    stringBuffer.append(detailsCounter);
    stringBuffer.append(TEXT_61);
    String referenceDocumentation = EcoreUtil.getDocumentation(eReference); if (referenceDocumentation != null) {
    stringBuffer.append(TEXT_62);
    stringBuffer.append(referenceDocumentation);
    stringBuffer.append(TEXT_63);
    } row=0; String referenceConstraints = htmlExporter.computeConstraints(eReference); if (referenceConstraints != null) {
    stringBuffer.append(TEXT_64);
    stringBuffer.append((++row)%2 == 0 ? "even" : "odd");
    stringBuffer.append(TEXT_65);
    stringBuffer.append(referenceConstraints);
    stringBuffer.append(TEXT_66);
    } if (eReference.getEOpposite() != null) {
    stringBuffer.append(TEXT_67);
    stringBuffer.append((++row)%2 == 0 ? "even" : "odd");
    stringBuffer.append(TEXT_68);
    stringBuffer.append(htmlExporter.computeLabel(eReference.getEOpposite()));
    stringBuffer.append(TEXT_69);
    } if (!eReference.getEKeys().isEmpty()) {
    stringBuffer.append(TEXT_70);
    stringBuffer.append((++row)%2 == 0 ? "even" : "odd");
    stringBuffer.append(TEXT_71);
    stringBuffer.append(htmlExporter.computeEKeys(eReference));
    stringBuffer.append(TEXT_72);
    } for (EStructuralFeature detail : htmlExporter.getDetails(eReference)) {
    stringBuffer.append(TEXT_73);
    stringBuffer.append((++row)%2 == 0 ? "even" : "odd");
    stringBuffer.append(TEXT_74);
    stringBuffer.append(detail.getName());
    stringBuffer.append(TEXT_75);
    stringBuffer.append(eReference.eGet(detail));
    stringBuffer.append(TEXT_76);
    }
    stringBuffer.append(TEXT_77);
    }
    stringBuffer.append(TEXT_78);
    }
    stringBuffer.append(TEXT_79);
    if (!eClass.getEOperations().isEmpty()) {
    stringBuffer.append(TEXT_80);
    for (EOperation eOperation : eClass.getEOperations()) {
    stringBuffer.append(TEXT_81);
    stringBuffer.append(++detailsCounter);
    stringBuffer.append(TEXT_82);
    stringBuffer.append(htmlExporter.computeLabel(eOperation));
    stringBuffer.append(TEXT_83);
    stringBuffer.append(detailsCounter);
    stringBuffer.append(TEXT_84);
    stringBuffer.append(detailsCounter);
    stringBuffer.append(TEXT_85);
    String operationDocumentation = EcoreUtil.getDocumentation(eOperation); if (operationDocumentation != null) {
    stringBuffer.append(TEXT_86);
    stringBuffer.append(operationDocumentation);
    stringBuffer.append(TEXT_87);
    } row=0; String operationConstraints = htmlExporter.computeConstraints(eOperation); if (operationConstraints != null) {
    stringBuffer.append(TEXT_88);
    stringBuffer.append((++row)%2 == 0 ? "even" : "odd");
    stringBuffer.append(TEXT_89);
    stringBuffer.append(operationConstraints);
    stringBuffer.append(TEXT_90);
    } for (EStructuralFeature detail : htmlExporter.getDetails(eOperation)) {
    stringBuffer.append(TEXT_91);
    stringBuffer.append((++row)%2 == 0 ? "even" : "odd");
    stringBuffer.append(TEXT_92);
    stringBuffer.append(detail.getName());
    stringBuffer.append(TEXT_93);
    stringBuffer.append(eOperation.eGet(detail));
    stringBuffer.append(TEXT_94);
    }
    stringBuffer.append(TEXT_95);
    }
    stringBuffer.append(TEXT_96);
    }
    stringBuffer.append(TEXT_97);
    }
    }
    stringBuffer.append(TEXT_98);
    Collection<EEnum> eEnums = EcoreUtil.getObjectsByType(ePackage.getEClassifiers(), EcorePackage.Literals.EENUM);
    if (!eEnums.isEmpty()) {
    stringBuffer.append(TEXT_99);
    for (EEnum eEnum : eEnums) {
    stringBuffer.append(TEXT_100);
    stringBuffer.append(eEnum.getName());
    stringBuffer.append(TEXT_101);
    stringBuffer.append(eEnum.getName());
    stringBuffer.append(TEXT_102);
    if (!eEnum.getELiterals().isEmpty()) {
    stringBuffer.append(TEXT_103);
    String enumDocumentation = EcoreUtil.getDocumentation(eEnum); if (enumDocumentation != null) {
    stringBuffer.append(TEXT_104);
    stringBuffer.append(htmlExporter.getLongText(enumDocumentation));
    stringBuffer.append(TEXT_105);
    }
    stringBuffer.append(TEXT_106);
    for (EEnumLiteral eEnumLiteral : eEnum.getELiterals()) {
    stringBuffer.append(TEXT_107);
    stringBuffer.append(++detailsCounter);
    stringBuffer.append(TEXT_108);
    stringBuffer.append(htmlExporter.computeLabel(eEnumLiteral));
    stringBuffer.append(TEXT_109);
    stringBuffer.append(detailsCounter);
    stringBuffer.append(TEXT_110);
    stringBuffer.append(detailsCounter);
    stringBuffer.append(TEXT_111);
    String enumLiteralDocumentation = EcoreUtil.getDocumentation(eEnumLiteral); if (enumLiteralDocumentation != null) {
    stringBuffer.append(TEXT_112);
    stringBuffer.append(enumLiteralDocumentation);
    stringBuffer.append(TEXT_113);
    } int row = 0; for (EStructuralFeature detail : htmlExporter.getDetails(eEnumLiteral)) {
    stringBuffer.append(TEXT_114);
    stringBuffer.append((++row)%2 == 0 ? "even" : "odd");
    stringBuffer.append(TEXT_115);
    stringBuffer.append(detail.getName());
    stringBuffer.append(TEXT_116);
    stringBuffer.append(eEnumLiteral.eGet(detail));
    stringBuffer.append(TEXT_117);
    }
    stringBuffer.append(TEXT_118);
    }
    stringBuffer.append(TEXT_119);
    }
    }
    }
    stringBuffer.append(TEXT_120);
    Collection<EDataType> eDataTypes = EcoreUtil.getObjectsByType(ePackage.getEClassifiers(), EcorePackage.Literals.EDATA_TYPE); eDataTypes.removeAll(eEnums);
    if (!eDataTypes.isEmpty()) {
    stringBuffer.append(TEXT_121);
    for (EDataType eDataType : eDataTypes) {
    stringBuffer.append(TEXT_122);
    stringBuffer.append(htmlExporter.computeClassifierLabel(eDataType));
    stringBuffer.append(TEXT_123);
    String dataTypeDocumentation = EcoreUtil.getDocumentation(eDataType); if (dataTypeDocumentation != null) {
    stringBuffer.append(TEXT_124);
    stringBuffer.append(htmlExporter.getLongText(dataTypeDocumentation));
    stringBuffer.append(TEXT_125);
    }
    stringBuffer.append(TEXT_126);
    int row = 0; for (EStructuralFeature detail : htmlExporter.getDetails(eDataType)) {
    stringBuffer.append(TEXT_127);
    stringBuffer.append((++row)%2 == 0 ? "even" : "odd");
    stringBuffer.append(TEXT_128);
    stringBuffer.append(detail.getName());
    stringBuffer.append(TEXT_129);
    stringBuffer.append(HTMLExporter.escape(eDataType.eGet(detail).toString()));
    stringBuffer.append(TEXT_130);
    }
    stringBuffer.append(TEXT_131);
    }
    stringBuffer.append(TEXT_132);
    }
    stringBuffer.append(TEXT_133);
    stringBuffer.append(TEXT_134);
    return stringBuffer.toString();
  }
}
