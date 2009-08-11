package org.eclipse.emf.codegen.ecore.templates.model;

import org.eclipse.emf.codegen.ecore.genmodel.*;
import org.eclipse.emf.codegen.ecore.genmodel.impl.*;
import java.util.*;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.util.*;

public class ResourceClass
{
  protected static String nl;
  public static synchronized ResourceClass create(String lineSeparator)
  {
    nl = lineSeparator;
    ResourceClass result = new ResourceClass();
    nl = null;
    return result;
  }

  public final String NL = nl == null ? (System.getProperties().getProperty("line.separator")) : nl;
  protected final String TEXT_1 = "";
  protected final String TEXT_2 = "/**";
  protected final String TEXT_3 = NL + " * ";
  protected final String TEXT_4 = NL + " * <copyright>" + NL + " * </copyright>" + NL + " *" + NL + " * ";
  protected final String TEXT_5 = "Id";
  protected final String TEXT_6 = NL + " */" + NL + "package ";
  protected final String TEXT_7 = ";" + NL;
  protected final String TEXT_8 = NL + NL + "/**" + NL + " * <!-- begin-user-doc -->" + NL + " * The <b>Resource </b> associated with the package." + NL + " * <!-- end-user-doc -->" + NL + " * @see ";
  protected final String TEXT_9 = NL + " * @generated" + NL + " */" + NL + "public class ";
  protected final String TEXT_10 = " extends ";
  protected final String TEXT_11 = NL + "{";
  protected final String TEXT_12 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */" + NL + "\tpublic static final ";
  protected final String TEXT_13 = " copyright = ";
  protected final String TEXT_14 = ";";
  protected final String TEXT_15 = NL;
  protected final String TEXT_16 = NL + "\t/**" + NL + "\t * Creates an instance of the resource." + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @param uri the URI of the new resource." + NL + "\t * @generated" + NL + "\t */" + NL + "\tpublic ";
  protected final String TEXT_17 = "(URI uri)" + NL + "\t{" + NL + "\t\tsuper(uri);";
  protected final String TEXT_18 = NL + "\t}" + NL;
  protected final String TEXT_19 = NL + "\t/**" + NL + "\t * A load option that turns of the use of the generate data converters." + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */" + NL + "\tpublic static final String OPTION_USE_DATA_CONVERTER = \"USE_DATA_CONVERTER\";" + NL + "" + NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */";
  protected final String TEXT_20 = NL + "\t@Override";
  protected final String TEXT_21 = NL + "\tpublic void doLoad(InputStream inputStream, ";
  protected final String TEXT_22 = " options) throws IOException" + NL + "\t{" + NL + "\t\tif (options != null && Boolean.TRUE.equals(options.get(OPTION_USE_DATA_CONVERTER)))" + NL + "\t\t{" + NL + "\t\t  getContents().add" + NL + "\t\t\t (load" + NL + "\t\t\t\t (new InputSource(inputStream), " + NL + "\t\t\t\t  (";
  protected final String TEXT_23 = ")options.get(XMLResource.OPTION_PARSER_FEATURES), " + NL + "\t\t\t\t  (";
  protected final String TEXT_24 = ")options.get(XMLResource.OPTION_PARSER_PROPERTIES), " + NL + "\t\t\t\t  Boolean.TRUE.equals(options.get(XMLResource.OPTION_USE_LEXICAL_HANDLER))).eContainer());" + NL + "\t\t}" + NL + "\t\telse" + NL + "\t\t{  " + NL + "\t\t\tsuper.doLoad(inputStream, options);" + NL + "\t\t}" + NL + "\t}" + NL + "" + NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */";
  protected final String TEXT_25 = NL + "\t@Override";
  protected final String TEXT_26 = NL + "\tpublic void doLoad(InputSource inputSource, ";
  protected final String TEXT_27 = " options) throws IOException" + NL + "\t{" + NL + "\t\tif (options != null && Boolean.TRUE.equals(options.get(OPTION_USE_DATA_CONVERTER)))" + NL + "\t\t{" + NL + "\t\t  getContents().add" + NL + "\t\t\t (load" + NL + "\t\t\t\t (inputSource," + NL + "\t\t\t\t  (";
  protected final String TEXT_28 = ")options.get(XMLResource.OPTION_PARSER_FEATURES), " + NL + "\t\t\t\t  (";
  protected final String TEXT_29 = ")options.get(XMLResource.OPTION_PARSER_PROPERTIES), " + NL + "\t\t\t\t  Boolean.TRUE.equals(options.get(XMLResource.OPTION_USE_LEXICAL_HANDLER))).eContainer());" + NL + "\t\t}" + NL + "\t\telse" + NL + "\t\t{  " + NL + "\t\t\tsuper.doLoad(inputSource, options);" + NL + "\t\t}" + NL + "\t}" + NL + "" + NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */" + NL + "\tprotected static final XMLParserPool parserPool = new XMLParserPoolImpl();" + NL + "" + NL + "\t/**" + NL + "\t * Loads an instance from the input." + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @param inputSource the input from which to load." + NL + "\t * @param features a map of the parser features and their values." + NL + "\t * @param properties a map of a parser properties and their values." + NL + "\t * @param useLexicalHandler whether a lexical handler should be used during loading." + NL + "\t * @return the root object; for the case of a document root, the child of that document root is return." + NL + "\t * @throws ParserConfigurationException" + NL + "\t * @throws SAXException" + NL + "\t * @generated" + NL + "\t */" + NL + "\tpublic static EObject load(InputSource inputSource, ";
  protected final String TEXT_30 = " features, ";
  protected final String TEXT_31 = " properties, boolean useLexicalHandler) throws IOException" + NL + "\t{" + NL + "\t\t";
  protected final String TEXT_32 = " requiredFeatures = new ";
  protected final String TEXT_33 = "();" + NL + "\t\trequiredFeatures.put(\"http://xml.org/sax/features/namespaces\", Boolean.TRUE); " + NL + "\t\tif (features != null)" + NL + "\t\t{" + NL + "\t\t\trequiredFeatures.putAll(features);" + NL + "\t\t}" + NL + "\t\t" + NL + "\t\tif (properties == null)" + NL + "\t\t{" + NL + "\t\t\tproperties = Collections.";
  protected final String TEXT_34 = "emptyMap()";
  protected final String TEXT_35 = "EMPTY_MAP";
  protected final String TEXT_36 = ";" + NL + "\t\t}" + NL + "\t\t" + NL + "\t\tSAXParser saxParser = null;" + NL + "\t\ttry" + NL + "\t\t{" + NL + "\t\t\tsaxParser = parserPool.get(requiredFeatures, properties, useLexicalHandler);" + NL + "\t\t\tfinal FrameFactory.DocumentRootStackFrame documentRoot = FrameFactory.INSTANCE.pushDocumentRoot(null, null);" + NL + "\t\t\tXMLTypeResourceImpl.Handler handler = new XMLTypeResourceImpl.Handler(documentRoot);" + NL + "\t\t\tsaxParser.parse(inputSource, handler);";
  protected final String TEXT_37 = NL + "\t\t\treturn (EObject)((EObject)FrameFactory.INSTANCE.popDocumentRoot(documentRoot)).eContents().get(0);";
  protected final String TEXT_38 = NL + "\t\t\treturn FrameFactory.INSTANCE.popDocumentRoot(documentRoot).eContents().get(0);";
  protected final String TEXT_39 = NL + "\t\t}" + NL + "\t\tcatch (Exception exception)" + NL + "\t\t{" + NL + "\t\t\tthrow new IOWrappedException(exception);" + NL + "\t\t}" + NL + "\t\tfinally" + NL + "\t\t{" + NL + "\t\t\tparserPool.release(saxParser, requiredFeatures, properties, useLexicalHandler);" + NL + "\t\t}" + NL + "\t}" + NL;
  protected final String TEXT_40 = NL + "\t/**" + NL + "\t * <!-- begin-user-doc -->" + NL + "\t * <!-- end-user-doc -->" + NL + "\t * @generated" + NL + "\t */" + NL + "\tpublic final static class FrameFactory" + NL + "\t{" + NL + "\t\t/**" + NL + "\t\t * <!-- begin-user-doc -->" + NL + "\t\t * <!-- end-user-doc -->" + NL + "\t\t * @generated" + NL + "\t\t */" + NL + "\t\tpublic static final FrameFactory INSTANCE = new FrameFactory();" + NL + "\t";
  protected final String TEXT_41 = NL + "\t\t/**" + NL + "\t\t * <!-- begin-user-doc -->" + NL + "\t\t * <!-- end-user-doc -->" + NL + "\t\t * @generated" + NL + "\t\t */" + NL + "\t\tprotected ";
  protected final String TEXT_42 = "StackFrame ";
  protected final String TEXT_43 = ";" + NL;
  protected final String TEXT_44 = NL + "\t\t/**" + NL + "\t\t * <!-- begin-user-doc -->" + NL + "\t\t * <!-- end-user-doc -->" + NL + "\t\t * @generated" + NL + "\t\t */" + NL + "\t\tprotected ";
  protected final String TEXT_45 = " ";
  protected final String TEXT_46 = ";" + NL;
  protected final String TEXT_47 = NL + "\t\t/**" + NL + "\t\t * <!-- begin-user-doc -->" + NL + "\t\t * <!-- end-user-doc -->" + NL + "\t\t * @generated" + NL + "\t\t */" + NL + "\t\tpublic ";
  protected final String TEXT_48 = "StackFrame push";
  protected final String TEXT_49 = "(";
  protected final String TEXT_50 = " previous, Attributes attributes)" + NL + "\t\t{" + NL + "\t\t\t ";
  protected final String TEXT_51 = "StackFrame result";
  protected final String TEXT_52 = " = ";
  protected final String TEXT_53 = " == null ? new ";
  protected final String TEXT_54 = "StackFrame() : ";
  protected final String TEXT_55 = ";" + NL + "\t\t\t ";
  protected final String TEXT_56 = " = null;" + NL + "\t\t\t result";
  protected final String TEXT_57 = ".pushOnto(previous);" + NL + "\t\t\t result";
  protected final String TEXT_58 = ".handleAttributes(attributes);" + NL + "\t\t\t return result";
  protected final String TEXT_59 = ";" + NL + "\t\t}" + NL + "" + NL + "\t\t/**" + NL + "\t\t * <!-- begin-user-doc -->" + NL + "\t\t * <!-- end-user-doc -->" + NL + "\t\t * @generated" + NL + "\t\t */" + NL + "\t\tpublic ";
  protected final String TEXT_60 = " pop";
  protected final String TEXT_61 = "(";
  protected final String TEXT_62 = "StackFrame ";
  protected final String TEXT_63 = ")" + NL + "\t\t{" + NL + "\t\t\t";
  protected final String TEXT_64 = " result";
  protected final String TEXT_65 = "Value = ";
  protected final String TEXT_66 = ".pop";
  protected final String TEXT_67 = "();" + NL + "\t\t\tthis.";
  protected final String TEXT_68 = " = ";
  protected final String TEXT_69 = ";" + NL + "\t\t\treturn result";
  protected final String TEXT_70 = "Value;" + NL + "\t\t}" + NL + "" + NL + "\t\t/**" + NL + "\t\t * <!-- begin-user-doc -->" + NL + "\t\t * <!-- end-user-doc -->" + NL + "\t\t * @generated" + NL + "\t\t */" + NL + "\t\tpublic static class ";
  protected final String TEXT_71 = "StackFrame extends ";
  protected final String TEXT_72 = NL + "\t\t{" + NL + "\t\t\t/**" + NL + "\t\t\t * <!-- begin-user-doc -->" + NL + "\t\t\t * <!-- end-user-doc -->" + NL + "\t\t\t * @generated" + NL + "\t\t\t */" + NL + "\t\t\tprotected ";
  protected final String TEXT_73 = " the";
  protected final String TEXT_74 = ";" + NL + "\t\t";
  protected final String TEXT_75 = NL + "\t\t\t/**" + NL + "\t\t\t * <!-- begin-user-doc -->" + NL + "\t\t\t * <!-- end-user-doc -->" + NL + "\t\t\t * @generated" + NL + "\t\t\t */" + NL + "\t\t\tprotected ";
  protected final String TEXT_76 = ".FrameFactory.";
  protected final String TEXT_77 = "StackFrame ";
  protected final String TEXT_78 = ";" + NL;
  protected final String TEXT_79 = NL + "\t\t\t/**" + NL + "\t\t\t * <!-- begin-user-doc -->" + NL + "\t\t\t * <!-- end-user-doc -->" + NL + "\t\t\t * @generated" + NL + "\t\t\t */" + NL + "\t\t\tprotected ";
  protected final String TEXT_80 = " ";
  protected final String TEXT_81 = ";" + NL + "\t\t";
  protected final String TEXT_82 = NL + "\t\t\t/**" + NL + "\t\t\t * <!-- begin-user-doc -->" + NL + "\t\t\t * <!-- end-user-doc -->" + NL + "\t\t\t * @generated" + NL + "\t\t\t */";
  protected final String TEXT_83 = NL + "\t\t\t@Override";
  protected final String TEXT_84 = NL + "\t\t\tpublic void handleAttributes(Attributes attributes)" + NL + "\t\t\t{";
  protected final String TEXT_85 = NL + "\t\t\t\tString theValue = attributes.getValue(";
  protected final String TEXT_86 = ", \"";
  protected final String TEXT_87 = "\");";
  protected final String TEXT_88 = NL + "\t\t\t\ttheValue = attributes.getValue(";
  protected final String TEXT_89 = ", \"";
  protected final String TEXT_90 = "\");";
  protected final String TEXT_91 = NL + "\t\t\t\tif (theValue != null)" + NL + "\t\t\t\t{";
  protected final String TEXT_92 = NL + "\t\t\t\t\tthe";
  protected final String TEXT_93 = ".set";
  protected final String TEXT_94 = "(";
  protected final String TEXT_95 = ".create";
  protected final String TEXT_96 = "(theValue));";
  protected final String TEXT_97 = NL + "\t\t\t\t\tthe";
  protected final String TEXT_98 = ".set";
  protected final String TEXT_99 = "((";
  protected final String TEXT_100 = ")";
  protected final String TEXT_101 = ".createFromString(";
  protected final String TEXT_102 = ", theValue));";
  protected final String TEXT_103 = NL + "\t\t\t\t}";
  protected final String TEXT_104 = NL + "\t\t\t\t// There are attributes to handle.";
  protected final String TEXT_105 = NL + "\t\t\t}" + NL + "\t\t" + NL + "\t\t\t/**" + NL + "\t\t\t * <!-- begin-user-doc -->" + NL + "\t\t\t * <!-- end-user-doc -->" + NL + "\t\t\t * @generated" + NL + "\t\t\t */";
  protected final String TEXT_106 = NL + "\t\t\t@Override";
  protected final String TEXT_107 = NL + "\t\t\tpublic ";
  protected final String TEXT_108 = " startElement(String namespace, String localName, String qName, Attributes attributes) throws SAXException" + NL + "\t\t\t{";
  protected final String TEXT_109 = NL + "\t\t\t\t";
  protected final String TEXT_110 = "else ";
  protected final String TEXT_111 = "if (\"";
  protected final String TEXT_112 = "\".equals(localName) && ";
  protected final String TEXT_113 = ".equals(namespace))" + NL + "\t\t\t\t{" + NL + "\t\t\t\t\treturn ";
  protected final String TEXT_114 = " = ";
  protected final String TEXT_115 = ".FrameFactory.INSTANCE.push";
  protected final String TEXT_116 = "(this, attributes);" + NL + "\t\t\t\t}";
  protected final String TEXT_117 = NL + "\t\t\t\treturn super.startElement(namespace, localName, qName, attributes);";
  protected final String TEXT_118 = NL + "\t\t\t\telse" + NL + "\t\t\t\t{" + NL + "\t\t\t\t\treturn super.startElement(namespace, localName, qName, attributes);" + NL + "\t\t\t\t}";
  protected final String TEXT_119 = NL + "\t\t\t}" + NL + "" + NL + "\t\t\t/**" + NL + "\t\t\t * <!-- begin-user-doc -->" + NL + "\t\t\t * <!-- end-user-doc -->" + NL + "\t\t\t * @generated" + NL + "\t\t\t */";
  protected final String TEXT_120 = NL + "\t\t\t@Override";
  protected final String TEXT_121 = NL + "\t\t\tpublic void endElement(";
  protected final String TEXT_122 = " child) throws SAXException" + NL + "\t\t\t{";
  protected final String TEXT_123 = NL + "\t\t\t\t";
  protected final String TEXT_124 = "else ";
  protected final String TEXT_125 = "if (child == ";
  protected final String TEXT_126 = ")" + NL + "\t\t\t\t{";
  protected final String TEXT_127 = NL + "\t\t\t\t\tthe";
  protected final String TEXT_128 = ".";
  protected final String TEXT_129 = "().add(";
  protected final String TEXT_130 = ".FrameFactory.INSTANCE.pop";
  protected final String TEXT_131 = "(";
  protected final String TEXT_132 = "));";
  protected final String TEXT_133 = NL + "\t\t\t\t\tthe";
  protected final String TEXT_134 = ".set";
  protected final String TEXT_135 = "(";
  protected final String TEXT_136 = ".FrameFactory.INSTANCE.pop";
  protected final String TEXT_137 = "(";
  protected final String TEXT_138 = "));";
  protected final String TEXT_139 = NL + "\t\t\t\t\t";
  protected final String TEXT_140 = " = null;" + NL + "\t\t\t\t}";
  protected final String TEXT_141 = NL + "\t\t\t\tsuper.endElement(child);";
  protected final String TEXT_142 = NL + "\t\t\t\telse" + NL + "\t\t\t\t{" + NL + "\t\t\t\t\tsuper.endElement(child);" + NL + "\t\t\t\t}";
  protected final String TEXT_143 = NL + "\t\t\t}" + NL + "" + NL + "\t\t\t/**" + NL + "\t\t\t * <!-- begin-user-doc -->" + NL + "\t\t\t * <!-- end-user-doc -->" + NL + "\t\t\t * @generated" + NL + "\t\t\t */";
  protected final String TEXT_144 = NL + "\t\t\t@Override";
  protected final String TEXT_145 = NL + "\t\t\tpublic void create()" + NL + "\t\t\t{" + NL + "\t\t\t\tthe";
  protected final String TEXT_146 = " = ";
  protected final String TEXT_147 = ".create";
  protected final String TEXT_148 = "();" + NL + "\t\t\t}" + NL + "\t\t" + NL + "\t\t\t/**" + NL + "\t\t\t * <!-- begin-user-doc -->" + NL + "\t\t\t * <!-- end-user-doc -->" + NL + "\t\t\t * @generated" + NL + "\t\t\t */" + NL + "\t\t\tprotected ";
  protected final String TEXT_149 = " pop";
  protected final String TEXT_150 = "()" + NL + "\t\t\t{" + NL + "\t\t\t\tpop();" + NL + "\t\t\t\t";
  protected final String TEXT_151 = " result";
  protected final String TEXT_152 = "Value = the";
  protected final String TEXT_153 = ";" + NL + "\t\t\t\tthe";
  protected final String TEXT_154 = " = null;" + NL + "\t\t\t\treturn result";
  protected final String TEXT_155 = "Value;" + NL + "\t\t\t}" + NL + "\t\t" + NL + "\t\t}" + NL;
  protected final String TEXT_156 = NL + "\t\t/**" + NL + "\t\t * <!-- begin-user-doc -->" + NL + "\t\t * <!-- end-user-doc -->" + NL + "\t\t * @generated" + NL + "\t\t */" + NL + "\t\tpublic ";
  protected final String TEXT_157 = " push";
  protected final String TEXT_158 = "(";
  protected final String TEXT_159 = " previous, Attributes attributes)" + NL + "\t\t{" + NL + "\t\t\t ";
  protected final String TEXT_160 = " result";
  protected final String TEXT_161 = " = ";
  protected final String TEXT_162 = " == null ? new ";
  protected final String TEXT_163 = "() : ";
  protected final String TEXT_164 = ";" + NL + "\t\t\t ";
  protected final String TEXT_165 = " = null;" + NL + "\t\t\t result";
  protected final String TEXT_166 = ".pushOnto(previous);" + NL + "\t\t\t result";
  protected final String TEXT_167 = ".handleAttributes(attributes);" + NL + "\t\t\t return result";
  protected final String TEXT_168 = ";" + NL + "\t\t}" + NL + "" + NL + "\t\t/**" + NL + "\t\t * <!-- begin-user-doc -->" + NL + "\t\t * <!-- end-user-doc -->" + NL + "\t\t * @generated" + NL + "\t\t */" + NL + "\t\tpublic ";
  protected final String TEXT_169 = " pop";
  protected final String TEXT_170 = "(";
  protected final String TEXT_171 = " ";
  protected final String TEXT_172 = ")" + NL + "\t\t{";
  protected final String TEXT_173 = NL + "\t\t\t";
  protected final String TEXT_174 = " result";
  protected final String TEXT_175 = "Value = ";
  protected final String TEXT_176 = ".create";
  protected final String TEXT_177 = "(";
  protected final String TEXT_178 = ".popValue());";
  protected final String TEXT_179 = NL + "\t\t\t";
  protected final String TEXT_180 = " result";
  protected final String TEXT_181 = "Value = ((";
  protected final String TEXT_182 = ")";
  protected final String TEXT_183 = ".createFromString(";
  protected final String TEXT_184 = ", ";
  protected final String TEXT_185 = ".popValue())).";
  protected final String TEXT_186 = "();";
  protected final String TEXT_187 = NL + "\t\t\t";
  protected final String TEXT_188 = " result";
  protected final String TEXT_189 = "Value = (";
  protected final String TEXT_190 = ")";
  protected final String TEXT_191 = ".createFromString(";
  protected final String TEXT_192 = ", ";
  protected final String TEXT_193 = ".popValue());";
  protected final String TEXT_194 = NL + "\t\t\tthis.";
  protected final String TEXT_195 = " = ";
  protected final String TEXT_196 = ";" + NL + "\t\t\treturn result";
  protected final String TEXT_197 = "Value;" + NL + "\t\t}" + NL;
  protected final String TEXT_198 = NL + "\t}" + NL;
  protected final String TEXT_199 = NL + "} //";
  protected final String TEXT_200 = NL;

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

    GenPackage genPackage = (GenPackage)argument; GenModel genModel=genPackage.getGenModel(); ExtendedMetaData extendedMetaData= genModel.getExtendedMetaData();
    stringBuffer.append(TEXT_1);
    stringBuffer.append(TEXT_2);
    {GenBase copyrightHolder = argument instanceof GenBase ? (GenBase)argument : argument instanceof Object[] && ((Object[])argument)[0] instanceof GenBase ? (GenBase)((Object[])argument)[0] : null;
    if (copyrightHolder != null && copyrightHolder.hasCopyright()) {
    stringBuffer.append(TEXT_3);
    stringBuffer.append(copyrightHolder.getCopyright(copyrightHolder.getGenModel().getIndentation(stringBuffer)));
    } else {
    stringBuffer.append(TEXT_4);
    stringBuffer.append("$");
    stringBuffer.append(TEXT_5);
    stringBuffer.append("$");
    }}
    stringBuffer.append(TEXT_6);
    stringBuffer.append(genPackage.getUtilitiesPackageName());
    stringBuffer.append(TEXT_7);
    genModel.getImportedName("org.eclipse.emf.common.util.URI");
    genModel.markImportLocation(stringBuffer);
    stringBuffer.append(TEXT_8);
    stringBuffer.append(genPackage.getQualifiedResourceFactoryClassName());
    stringBuffer.append(TEXT_9);
    stringBuffer.append(genPackage.getResourceClassName());
    stringBuffer.append(TEXT_10);
    stringBuffer.append(genPackage.getImportedResourceBaseClassName());
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
    stringBuffer.append(genPackage.getResourceClassName());
    stringBuffer.append(TEXT_17);
    if (genPackage.getResource() == GenResourceKind.XML_LITERAL) { // Do nothing
    }
    stringBuffer.append(TEXT_18);
    if (genPackage.isDataTypeConverters() && (genPackage.hasDocumentRoot() || org.eclipse.emf.ecore.xml.type.XMLTypePackage.eNS_URI.equals(genPackage.getNSURI()))) { boolean isXMLTypePackage = org.eclipse.emf.ecore.xml.type.XMLTypePackage.eNS_URI.equals(genPackage.getNSURI());
    final String _Map = genModel.useGenerics() ? "Map<?, ?>" : "Map";
    final String _MapStringBoolean = genModel.useGenerics() ? "Map<String, Boolean>" : "Map";
    final String _MapStringWildcard = genModel.useGenerics() ? "Map<String, ?>" : "Map";
    if (!isXMLTypePackage) {
    genModel.getImportedName("org.eclipse.emf.ecore.xmi.XMLResource");
    genModel.getImportedName("org.eclipse.emf.ecore.xmi.XMLParserPool");
    genModel.getImportedName("org.eclipse.emf.ecore.xmi.impl.XMLParserPoolImpl");
    genModel.getImportedName("java.io.InputStream");
    genModel.getImportedName("java.io.IOException");
    genModel.getImportedName("org.eclipse.emf.ecore.EObject");
    genModel.getImportedName("java.util.Collections");
    genModel.getImportedName("java.util.HashMap");
    genModel.getImportedName("java.util.Map");
    genModel.getImportedName("org.xml.sax.InputSource");
    genModel.getImportedName("javax.xml.parsers.SAXParser");
    }
    genModel.getImportedName("org.xml.sax.Attributes");
    genModel.getImportedName("org.xml.sax.SAXException");
    String _StackFrame = genModel.getImportedName("org.eclipse.emf.ecore.xml.type.util.XMLTypeResourceImpl")+".StackFrame";
    String _DataFrame = genModel.getImportedName("org.eclipse.emf.ecore.xml.type.util.XMLTypeResourceImpl")+".DataFrame";
    if (!isXMLTypePackage) {
    stringBuffer.append(TEXT_19);
    if (genModel.useClassOverrideAnnotation()) {
    stringBuffer.append(TEXT_20);
    }
    stringBuffer.append(TEXT_21);
    stringBuffer.append(_Map);
    stringBuffer.append(TEXT_22);
    stringBuffer.append(_MapStringBoolean);
    stringBuffer.append(TEXT_23);
    stringBuffer.append(_MapStringWildcard);
    stringBuffer.append(TEXT_24);
    if (genModel.useClassOverrideAnnotation()) {
    stringBuffer.append(TEXT_25);
    }
    stringBuffer.append(TEXT_26);
    stringBuffer.append(_Map);
    stringBuffer.append(TEXT_27);
    stringBuffer.append(_MapStringBoolean);
    stringBuffer.append(TEXT_28);
    stringBuffer.append(_MapStringWildcard);
    stringBuffer.append(TEXT_29);
    stringBuffer.append(_MapStringBoolean);
    stringBuffer.append(TEXT_30);
    stringBuffer.append(_MapStringWildcard);
    stringBuffer.append(TEXT_31);
    stringBuffer.append(_MapStringBoolean);
    stringBuffer.append(TEXT_32);
    stringBuffer.append(_MapStringBoolean.replaceAll("Map","HashMap"));
    stringBuffer.append(TEXT_33);
    if (genModel.useGenerics()) {
    stringBuffer.append(TEXT_34);
    } else {
    stringBuffer.append(TEXT_35);
    }
    stringBuffer.append(TEXT_36);
    if (genModel.isSuppressEMFTypes()) {
    stringBuffer.append(TEXT_37);
    } else {
    stringBuffer.append(TEXT_38);
    }
    stringBuffer.append(TEXT_39);
    }
    stringBuffer.append(TEXT_40);
    for (GenClass genClass : genPackage.getGenClasses()) {
    stringBuffer.append(TEXT_41);
    stringBuffer.append(genClass.getName());
    stringBuffer.append(TEXT_42);
    stringBuffer.append(genClass.getSafeUncapName());
    stringBuffer.append(TEXT_43);
    }
    for (GenDataType genDataType : genPackage.getAllGenDataTypes()) {
    stringBuffer.append(TEXT_44);
    stringBuffer.append(_DataFrame);
    stringBuffer.append(TEXT_45);
    stringBuffer.append(genDataType.getSafeUncapName());
    stringBuffer.append(TEXT_46);
    }
    for (GenClass genClass : genPackage.getGenClasses()) {
    List<EStructuralFeature> attributes = extendedMetaData.getAllAttributes(genClass.getEcoreClass());
    List<EStructuralFeature> elements = extendedMetaData.getAllElements(genClass.getEcoreClass());
    stringBuffer.append(TEXT_47);
    stringBuffer.append(genClass.getName());
    stringBuffer.append(TEXT_48);
    stringBuffer.append(genClass.getName());
    stringBuffer.append(TEXT_49);
    stringBuffer.append(_StackFrame);
    stringBuffer.append(TEXT_50);
    stringBuffer.append(genClass.getName());
    stringBuffer.append(TEXT_51);
    stringBuffer.append(genClass.getName());
    stringBuffer.append(TEXT_52);
    stringBuffer.append(genClass.getSafeUncapName());
    stringBuffer.append(TEXT_53);
    stringBuffer.append(genClass.getName());
    stringBuffer.append(TEXT_54);
    stringBuffer.append(genClass.getSafeUncapName());
    stringBuffer.append(TEXT_55);
    stringBuffer.append(genClass.getSafeUncapName());
    stringBuffer.append(TEXT_56);
    stringBuffer.append(genClass.getName());
    stringBuffer.append(TEXT_57);
    stringBuffer.append(genClass.getName());
    stringBuffer.append(TEXT_58);
    stringBuffer.append(genClass.getName());
    stringBuffer.append(TEXT_59);
    stringBuffer.append(genClass.getImportedInterfaceName());
    stringBuffer.append(TEXT_60);
    stringBuffer.append(genClass.getName());
    stringBuffer.append(TEXT_61);
    stringBuffer.append(genClass.getName());
    stringBuffer.append(TEXT_62);
    stringBuffer.append(genClass.getSafeUncapName());
    stringBuffer.append(TEXT_63);
    stringBuffer.append(genClass.getImportedInterfaceName());
    stringBuffer.append(TEXT_64);
    stringBuffer.append(genClass.getName());
    stringBuffer.append(TEXT_65);
    stringBuffer.append(genClass.getSafeUncapName());
    stringBuffer.append(TEXT_66);
    stringBuffer.append(genClass.getName());
    stringBuffer.append(TEXT_67);
    stringBuffer.append(genClass.getSafeUncapName());
    stringBuffer.append(TEXT_68);
    stringBuffer.append(genClass.getSafeUncapName());
    stringBuffer.append(TEXT_69);
    stringBuffer.append(genClass.getName());
    stringBuffer.append(TEXT_70);
    stringBuffer.append(genClass.getName());
    stringBuffer.append(TEXT_71);
    stringBuffer.append(_StackFrame);
    stringBuffer.append(TEXT_72);
    stringBuffer.append(genClass.getImportedInterfaceName());
    stringBuffer.append(TEXT_73);
    stringBuffer.append(genClass.getName());
    stringBuffer.append(TEXT_74);
    for (GenFeature genFeature : genClass.getAllGenFeatures()) {
    String name = extendedMetaData.getName(genFeature.getEcoreFeature());
    if ((elements.contains(genFeature.getEcoreFeature()) || attributes.contains(genFeature.getEcoreFeature())) && name.indexOf(":") == -1) {
    if (genFeature.isReferenceType()) {
    stringBuffer.append(TEXT_75);
    stringBuffer.append(genFeature.getTypeGenClass().getGenPackage().getImportedResourceClassName());
    stringBuffer.append(TEXT_76);
    stringBuffer.append(genFeature.getTypeGenClass().getName());
    stringBuffer.append(TEXT_77);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_78);
    } else {
    stringBuffer.append(TEXT_79);
    stringBuffer.append(_DataFrame);
    stringBuffer.append(TEXT_80);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_81);
    }
    }
    }
    stringBuffer.append(TEXT_82);
    if (genModel.useClassOverrideAnnotation()) {
    stringBuffer.append(TEXT_83);
    }
    stringBuffer.append(TEXT_84);
    int count = 0; for (GenFeature genFeature : genClass.getAllGenFeatures()) {
    String name = extendedMetaData.getName(genFeature.getEcoreFeature());
    if (attributes.contains(genFeature.getEcoreFeature()) && !genFeature.isDerived() && name.indexOf(":") == -1) {
    String namespace = Literals.toStringLiteral(extendedMetaData.getNamespace(genFeature.getEcoreFeature()), genModel); if ("null".equals(namespace)) namespace = "\"\"";
    if (!genFeature.isReferenceType()) { GenClassifier genClassifier = genFeature.getTypeGenClassifier();
    if (count++ == 0) {
    stringBuffer.append(TEXT_85);
    stringBuffer.append(namespace);
    stringBuffer.append(TEXT_86);
    stringBuffer.append(name);
    stringBuffer.append(TEXT_87);
    } else {
    stringBuffer.append(TEXT_88);
    stringBuffer.append(namespace);
    stringBuffer.append(TEXT_89);
    stringBuffer.append(name);
    stringBuffer.append(TEXT_90);
    }
    stringBuffer.append(TEXT_91);
    if (genClassifier.getGenPackage().isDataTypeConverters()) {
    stringBuffer.append(TEXT_92);
    stringBuffer.append(genClass.getName());
    stringBuffer.append(TEXT_93);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_94);
    stringBuffer.append(genClassifier.getGenPackage().getQualifiedFactoryInstanceAccessor());
    stringBuffer.append(TEXT_95);
    stringBuffer.append(genClassifier.getName());
    stringBuffer.append(TEXT_96);
    } else {
    stringBuffer.append(TEXT_97);
    stringBuffer.append(genClass.getName());
    stringBuffer.append(TEXT_98);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_99);
    stringBuffer.append(genFeature.getImportedType(null));
    stringBuffer.append(TEXT_100);
    stringBuffer.append(genClassifier.getGenPackage().getQualifiedEFactoryInstanceAccessor());
    stringBuffer.append(TEXT_101);
    stringBuffer.append(genClassifier.getQualifiedClassifierAccessor());
    stringBuffer.append(TEXT_102);
    }
    stringBuffer.append(TEXT_103);
    }
    }
    }
    if (count == 0) {
    stringBuffer.append(TEXT_104);
    }
    stringBuffer.append(TEXT_105);
    if (genModel.useClassOverrideAnnotation()) {
    stringBuffer.append(TEXT_106);
    }
    stringBuffer.append(TEXT_107);
    stringBuffer.append(_StackFrame);
    stringBuffer.append(TEXT_108);
    count = 0; for (GenFeature genFeature : genClass.getAllGenFeatures()) {
    String name = extendedMetaData.getName(genFeature.getEcoreFeature());
    if (elements.contains(genFeature.getEcoreFeature()) && name.indexOf(":") == -1) {
    String namespace = Literals.toStringLiteral(extendedMetaData.getNamespace(genFeature.getEcoreFeature()), genModel); if ("null".equals(namespace)) namespace = "\"\"";
    stringBuffer.append(TEXT_109);
    if (count++ != 0) {
    stringBuffer.append(TEXT_110);
    }
    stringBuffer.append(TEXT_111);
    stringBuffer.append(name);
    stringBuffer.append(TEXT_112);
    stringBuffer.append(namespace);
    stringBuffer.append(TEXT_113);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_114);
    stringBuffer.append(genFeature.getTypeGenClassifier().getGenPackage().getImportedResourceClassName());
    stringBuffer.append(TEXT_115);
    stringBuffer.append(genFeature.getTypeGenClassifier().getName());
    stringBuffer.append(TEXT_116);
    }
    }
    if (count == 0) {
    stringBuffer.append(TEXT_117);
    } else {
    stringBuffer.append(TEXT_118);
    }
    stringBuffer.append(TEXT_119);
    if (genModel.useClassOverrideAnnotation()) {
    stringBuffer.append(TEXT_120);
    }
    stringBuffer.append(TEXT_121);
    stringBuffer.append(_StackFrame);
    stringBuffer.append(TEXT_122);
    count = 0; for (GenFeature genFeature : genClass.getAllGenFeatures()) {
    String name = extendedMetaData.getName(genFeature.getEcoreFeature());
    if (elements.contains(genFeature.getEcoreFeature()) && name.indexOf(":") == -1) {
    stringBuffer.append(TEXT_123);
    if (count++ != 0) {
    stringBuffer.append(TEXT_124);
    }
    stringBuffer.append(TEXT_125);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_126);
    if (genFeature.isListType()) {
    stringBuffer.append(TEXT_127);
    stringBuffer.append(genClass.getName());
    stringBuffer.append(TEXT_128);
    stringBuffer.append(genFeature.getGetAccessor());
    stringBuffer.append(TEXT_129);
    stringBuffer.append(genFeature.getTypeGenClassifier().getGenPackage().getImportedResourceClassName());
    stringBuffer.append(TEXT_130);
    stringBuffer.append(genFeature.getTypeGenClassifier().getName());
    stringBuffer.append(TEXT_131);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_132);
    } else {
    stringBuffer.append(TEXT_133);
    stringBuffer.append(genClass.getName());
    stringBuffer.append(TEXT_134);
    stringBuffer.append(genFeature.getAccessorName());
    stringBuffer.append(TEXT_135);
    stringBuffer.append(genFeature.getTypeGenClassifier().getGenPackage().getImportedResourceClassName());
    stringBuffer.append(TEXT_136);
    stringBuffer.append(genFeature.getTypeGenClassifier().getName());
    stringBuffer.append(TEXT_137);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_138);
    }
    stringBuffer.append(TEXT_139);
    stringBuffer.append(genFeature.getSafeName());
    stringBuffer.append(TEXT_140);
    }
    }
    if (count == 0) {
    stringBuffer.append(TEXT_141);
    } else {
    stringBuffer.append(TEXT_142);
    }
    stringBuffer.append(TEXT_143);
    if (genModel.useClassOverrideAnnotation()) {
    stringBuffer.append(TEXT_144);
    }
    stringBuffer.append(TEXT_145);
    stringBuffer.append(genClass.getName());
    stringBuffer.append(TEXT_146);
    stringBuffer.append(genPackage.getQualifiedFactoryInstanceAccessor());
    stringBuffer.append(TEXT_147);
    stringBuffer.append(genClass.getName());
    stringBuffer.append(TEXT_148);
    stringBuffer.append(genClass.getImportedInterfaceName());
    stringBuffer.append(TEXT_149);
    stringBuffer.append(genClass.getName());
    stringBuffer.append(TEXT_150);
    stringBuffer.append(genClass.getImportedInterfaceName());
    stringBuffer.append(TEXT_151);
    stringBuffer.append(genClass.getName());
    stringBuffer.append(TEXT_152);
    stringBuffer.append(genClass.getName());
    stringBuffer.append(TEXT_153);
    stringBuffer.append(genClass.getName());
    stringBuffer.append(TEXT_154);
    stringBuffer.append(genClass.getName());
    stringBuffer.append(TEXT_155);
    }
    for (GenDataType genDataType : genPackage.getAllGenDataTypes()) {
    stringBuffer.append(TEXT_156);
    stringBuffer.append(_DataFrame);
    stringBuffer.append(TEXT_157);
    stringBuffer.append(genDataType.getName());
    stringBuffer.append(TEXT_158);
    stringBuffer.append(_StackFrame);
    stringBuffer.append(TEXT_159);
    stringBuffer.append(_DataFrame);
    stringBuffer.append(TEXT_160);
    stringBuffer.append(genDataType.getName());
    stringBuffer.append(TEXT_161);
    stringBuffer.append(genDataType.getSafeUncapName());
    stringBuffer.append(TEXT_162);
    stringBuffer.append(_DataFrame);
    stringBuffer.append(TEXT_163);
    stringBuffer.append(genDataType.getSafeUncapName());
    stringBuffer.append(TEXT_164);
    stringBuffer.append(genDataType.getSafeUncapName());
    stringBuffer.append(TEXT_165);
    stringBuffer.append(genDataType.getName());
    stringBuffer.append(TEXT_166);
    stringBuffer.append(genDataType.getName());
    stringBuffer.append(TEXT_167);
    stringBuffer.append(genDataType.getName());
    stringBuffer.append(TEXT_168);
    stringBuffer.append(genDataType.getImportedParameterizedInstanceClassName());
    stringBuffer.append(TEXT_169);
    stringBuffer.append(genDataType.getName());
    stringBuffer.append(TEXT_170);
    stringBuffer.append(_DataFrame);
    stringBuffer.append(TEXT_171);
    stringBuffer.append(genDataType.getSafeUncapName());
    stringBuffer.append(TEXT_172);
    if (genDataType.getGenPackage().isDataTypeConverters()) {
    stringBuffer.append(TEXT_173);
    stringBuffer.append(genDataType.getImportedParameterizedInstanceClassName());
    stringBuffer.append(TEXT_174);
    stringBuffer.append(genDataType.getName());
    stringBuffer.append(TEXT_175);
    stringBuffer.append(genDataType.getGenPackage().getQualifiedFactoryInstanceAccessor());
    stringBuffer.append(TEXT_176);
    stringBuffer.append(genDataType.getName());
    stringBuffer.append(TEXT_177);
    stringBuffer.append(genDataType.getSafeUncapName());
    stringBuffer.append(TEXT_178);
    } else if (genDataType.isPrimitiveType() && genModel.getComplianceLevel().getValue() < GenJDKLevel.JDK50) {
    stringBuffer.append(TEXT_179);
    stringBuffer.append(genDataType.getImportedInstanceClassName());
    stringBuffer.append(TEXT_180);
    stringBuffer.append(genDataType.getName());
    stringBuffer.append(TEXT_181);
    stringBuffer.append(genDataType.getObjectInstanceClassName());
    stringBuffer.append(TEXT_182);
    stringBuffer.append(genDataType.getGenPackage().getQualifiedEFactoryInstanceAccessor());
    stringBuffer.append(TEXT_183);
    stringBuffer.append(genDataType.getQualifiedClassifierAccessor());
    stringBuffer.append(TEXT_184);
    stringBuffer.append(genDataType.getSafeUncapName());
    stringBuffer.append(TEXT_185);
    stringBuffer.append(genDataType.getPrimitiveValueFunction());
    stringBuffer.append(TEXT_186);
    } else {
    stringBuffer.append(TEXT_187);
    stringBuffer.append(genDataType.getImportedInstanceClassName());
    stringBuffer.append(TEXT_188);
    stringBuffer.append(genDataType.getName());
    stringBuffer.append(TEXT_189);
    stringBuffer.append(genDataType.getObjectInstanceClassName());
    stringBuffer.append(TEXT_190);
    stringBuffer.append(genDataType.getGenPackage().getQualifiedEFactoryInstanceAccessor());
    stringBuffer.append(TEXT_191);
    stringBuffer.append(genDataType.getQualifiedClassifierAccessor());
    stringBuffer.append(TEXT_192);
    stringBuffer.append(genDataType.getSafeUncapName());
    stringBuffer.append(TEXT_193);
    }
    stringBuffer.append(TEXT_194);
    stringBuffer.append(genDataType.getSafeUncapName());
    stringBuffer.append(TEXT_195);
    stringBuffer.append(genDataType.getSafeUncapName());
    stringBuffer.append(TEXT_196);
    stringBuffer.append(genDataType.getName());
    stringBuffer.append(TEXT_197);
    }
    stringBuffer.append(TEXT_198);
    }
    stringBuffer.append(TEXT_199);
    stringBuffer.append(genPackage.getResourceClassName());
    genModel.emitSortedImports();
    stringBuffer.append(TEXT_200);
    return stringBuffer.toString();
  }
}
