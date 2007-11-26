/**
 * <copyright>
 *
 * Copyright (c) 2002-2007 IBM Corporation and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: 
 *   IBM - Initial API and implementation
 *
 * </copyright>
 *
 * $Id: XSDConstants.java,v 1.11 2007/11/26 19:24:39 emerks Exp $
 */
package org.eclipse.xsd.util;


import java.util.HashSet;
import java.util.Set;

import org.w3c.dom.Attr;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.xml.type.util.XMLTypeUtil;

import org.eclipse.xsd.XSDSimpleTypeDefinition;
import org.eclipse.xsd.XSDTypeDefinition;


/**
 * The <b>Constants</b> for XML and XML Schema and related <b>DOM Utilities</b>.
 */
public class XSDConstants
{
  /**
   * The value <code>"http://www.w3.org/TR/xmlschema-1/"</code>.
   */
  public static final String PART1 = "http://www.w3.org/TR/xmlschema-1/";

  /**
   * The value <code>"http://www.w3.org/TR/xmlschema-2/"</code>.
   */
  public static final String PART2 = "http://www.w3.org/TR/xmlschema-2/";

  /**
   * The value <code>"http://www.w3.org/XML/1998/namespace"</code>.
   */
  public static final String XML_NAMESPACE_URI_1998 = "http://www.w3.org/XML/1998/namespace";

  /**
   * The value <code>"http://www.w3.org/2000/xmlns/"</code>.
   */
  public static final String XMLNS_URI_2000 = "http://www.w3.org/2000/xmlns/";

  /**
   * The value <code>http://www.w3.org/1999/XMLSchema"</code>.
   */
  public static final String SCHEMA_FOR_SCHEMA_URI_1999 = "http://www.w3.org/1999/XMLSchema";

  /**
   * The value <code>"http://www.w3.org/2000/10/XMLSchema"</code>.
   */
  public static final String SCHEMA_FOR_SCHEMA_URI_2000_10 = "http://www.w3.org/2000/10/XMLSchema";

  /**
   * The value <code>"http://www.w3.org/2001/XMLSchema"</code>.
   */
  public static final String SCHEMA_FOR_SCHEMA_URI_2001 = "http://www.w3.org/2001/XMLSchema";

  /**
   * The value <code>"http://www.w3.org/2001/XMLSchema-instance"</code>.
   */
  public static final String SCHEMA_INSTANCE_URI_2001 = "http://www.w3.org/2001/XMLSchema-instance";

  /**
   * Returns whether the given namespace is (one of) the XML namespace(s).
   * @param namespace a namespace.
   * @return whether the given namespace is (one of) the XML namespace(s).
   */
  public static boolean isXMLNamespace(String namespace)
  {
    return 
      XML_NAMESPACE_URI_1998.equals(namespace);
  }

  /**
   * Returns whether the given namespace is (one of) the XMLNS namespace(s).
   * @param namespace a namespace.
   * @return whether the given namespace is (one of) the XMLNS namespace(s).
   */
  public static boolean isXMLNSNamespace(String namespace)
  {
    return 
      XMLNS_URI_2000.equals(namespace);
  }

  /**
   * Returns whether the given namespace is one of the XML Schema for Schema namespaces.
   * @param namespace a namespace.
   * @return whether the given namespace is one of the XML Schema for Schema namespaces.
   */
  public static boolean isSchemaForSchemaNamespace(String namespace)
  {
    return 
      SCHEMA_FOR_SCHEMA_URI_2001.equals(namespace) ||
        SCHEMA_FOR_SCHEMA_URI_2000_10.equals(namespace) ||
        SCHEMA_FOR_SCHEMA_URI_1999.equals(namespace);
  }

  /**
   * Returns whether the given namespace is (one of) the XML Schema Instance namespace(s).
   * @param namespace a namespace.
   * @return whether the given namespace is (one of) the XML Schema Instance namespace(s).
   */
  public static boolean isSchemaInstanceNamespace(String namespace)
  {
    return 
      SCHEMA_INSTANCE_URI_2001.equals(namespace);
  }

  //
  // all
  // annotation
  // any
  // anyAttribute
  // appinfo
  // attribute
  // attributeGroup
  // choice
  // complexContent
  // complexType
  // documentation
  // element
  // enumeration
  // extension
  // field
  // fractionDigits
  // group
  // import
  // include
  // key
  // keyref
  // length
  // list
  // maxExclusive
  // maxInclusive
  // maxLength
  // minExclusive
  // minInclusive
  // minLength
  // notation
  // pattern
  // redefine
  // restriction
  // schema
  // selector
  // sequence
  // simpleContent
  // simpleType
  // totalDigits
  // union
  // unique
  // whiteSpace
  // (
  // for i in $(cat elements); do 
  // j=$(echo $i | tr '[:lower:]' '[:upper:]'); 
  // echo '  /**';
  // echo '   * The value <code>"'$i'"</code>.';
  // echo '   * @see #'$j'_ELEMENT';
  // echo '   * @see #ELEMENT_TAGS';
  // echo '   * @see #nodeType(String)';
  // echo '   */';
  // echo '  public final static String '$j'_ELEMENT_TAG = "'$i'";';
  // echo '';
  // done; 
  // k=0;
  // for i in $(cat elements); do 
  // j=$(echo $i | tr '[:lower:]' '[:upper:]'); 
  // echo '  /**';
  // echo '   * The value <code>'$k'</code>.';
  // echo '   * @see #'$j'_ELEMENT_TAG';
  // echo '   * @see #ELEMENT_TAGS';
  // echo '   * @see #nodeType(String)';
  // echo '   */';
  // echo '  public final static int '$j'_ELEMENT = '$k';';
  // echo '';
  // k=$(($k+1));
  // done;
  // echo '  /**';
  // echo '   * The sorted strings representing all XML Schema element tags.';
  // echo '   * @see #nodeType(String)';
  // echo '   * @see #nodeType(Node)';
  // echo '   */';
  // echo '  public static final String [] ELEMENT_TAGS =';
  // echo '    new String []';
  // echo '    {';
  // for i in $(cat elements); do 
  // j=$(echo $i | tr '[:lower:]' '[:upper:]'); 
  // echo '      '$j'_ELEMENT_TAG,';
  // done;
  // echo '    };';
  // )

  /**
   * The value <code>"all"</code>.
   * @see #ALL_ELEMENT
   * @see #ELEMENT_TAGS
   * @see #nodeType(String)
   */
  public final static String ALL_ELEMENT_TAG = "all";

  /**
   * The value <code>"annotation"</code>.
   * @see #ANNOTATION_ELEMENT
   * @see #ELEMENT_TAGS
   * @see #nodeType(String)
   */
  public final static String ANNOTATION_ELEMENT_TAG = "annotation";

  /**
   * The value <code>"any"</code>.
   * @see #ANY_ELEMENT
   * @see #ELEMENT_TAGS
   * @see #nodeType(String)
   */
  public final static String ANY_ELEMENT_TAG = "any";

  /**
   * The value <code>"anyAttribute"</code>.
   * @see #ANYATTRIBUTE_ELEMENT
   * @see #ELEMENT_TAGS
   * @see #nodeType(String)
   */
  public final static String ANYATTRIBUTE_ELEMENT_TAG = "anyAttribute";

  /**
   * The value <code>"appinfo"</code>.
   * @see #APPINFO_ELEMENT
   * @see #ELEMENT_TAGS
   * @see #nodeType(String)
   */
  public final static String APPINFO_ELEMENT_TAG = "appinfo";

  /**
   * The value <code>"attribute"</code>.
   * @see #ATTRIBUTE_ELEMENT
   * @see #ELEMENT_TAGS
   * @see #nodeType(String)
   */
  public final static String ATTRIBUTE_ELEMENT_TAG = "attribute";

  /**
   * The value <code>"attributeGroup"</code>.
   * @see #ATTRIBUTEGROUP_ELEMENT
   * @see #ELEMENT_TAGS
   * @see #nodeType(String)
   */
  public final static String ATTRIBUTEGROUP_ELEMENT_TAG = "attributeGroup";

  /**
   * The value <code>"choice"</code>.
   * @see #CHOICE_ELEMENT
   * @see #ELEMENT_TAGS
   * @see #nodeType(String)
   */
  public final static String CHOICE_ELEMENT_TAG = "choice";

  /**
   * The value <code>"complexContent"</code>.
   * @see #COMPLEXCONTENT_ELEMENT
   * @see #ELEMENT_TAGS
   * @see #nodeType(String)
   */
  public final static String COMPLEXCONTENT_ELEMENT_TAG = "complexContent";

  /**
   * The value <code>"complexType"</code>.
   * @see #COMPLEXTYPE_ELEMENT
   * @see #ELEMENT_TAGS
   * @see #nodeType(String)
   */
  public final static String COMPLEXTYPE_ELEMENT_TAG = "complexType";

  /**
   * The value <code>"documentation"</code>.
   * @see #DOCUMENTATION_ELEMENT
   * @see #ELEMENT_TAGS
   * @see #nodeType(String)
   */
  public final static String DOCUMENTATION_ELEMENT_TAG = "documentation";

  /**
   * The value <code>"element"</code>.
   * @see #ELEMENT_ELEMENT
   * @see #ELEMENT_TAGS
   * @see #nodeType(String)
   */
  public final static String ELEMENT_ELEMENT_TAG = "element";

  /**
   * The value <code>"enumeration"</code>.
   * @see #ENUMERATION_ELEMENT
   * @see #ELEMENT_TAGS
   * @see #nodeType(String)
   */
  public final static String ENUMERATION_ELEMENT_TAG = "enumeration";

  /**
   * The value <code>"extension"</code>.
   * @see #EXTENSION_ELEMENT
   * @see #ELEMENT_TAGS
   * @see #nodeType(String)
   */
  public final static String EXTENSION_ELEMENT_TAG = "extension";

  /**
   * The value <code>"field"</code>.
   * @see #FIELD_ELEMENT
   * @see #ELEMENT_TAGS
   * @see #nodeType(String)
   */
  public final static String FIELD_ELEMENT_TAG = "field";

  /**
   * The value <code>"fractionDigits"</code>.
   * @see #FRACTIONDIGITS_ELEMENT
   * @see #ELEMENT_TAGS
   * @see #nodeType(String)
   */
  public final static String FRACTIONDIGITS_ELEMENT_TAG = "fractionDigits";

  /**
   * The value <code>"group"</code>.
   * @see #GROUP_ELEMENT
   * @see #ELEMENT_TAGS
   * @see #nodeType(String)
   */
  public final static String GROUP_ELEMENT_TAG = "group";

  /**
   * The value <code>"import"</code>.
   * @see #IMPORT_ELEMENT
   * @see #ELEMENT_TAGS
   * @see #nodeType(String)
   */
  public final static String IMPORT_ELEMENT_TAG = "import";

  /**
   * The value <code>"include"</code>.
   * @see #INCLUDE_ELEMENT
   * @see #ELEMENT_TAGS
   * @see #nodeType(String)
   */
  public final static String INCLUDE_ELEMENT_TAG = "include";

  /**
   * The value <code>"key"</code>.
   * @see #KEY_ELEMENT
   * @see #ELEMENT_TAGS
   * @see #nodeType(String)
   */
  public final static String KEY_ELEMENT_TAG = "key";

  /**
   * The value <code>"keyref"</code>.
   * @see #KEYREF_ELEMENT
   * @see #ELEMENT_TAGS
   * @see #nodeType(String)
   */
  public final static String KEYREF_ELEMENT_TAG = "keyref";

  /**
   * The value <code>"length"</code>.
   * @see #LENGTH_ELEMENT
   * @see #ELEMENT_TAGS
   * @see #nodeType(String)
   */
  public final static String LENGTH_ELEMENT_TAG = "length";

  /**
   * The value <code>"list"</code>.
   * @see #LIST_ELEMENT
   * @see #ELEMENT_TAGS
   * @see #nodeType(String)
   */
  public final static String LIST_ELEMENT_TAG = "list";

  /**
   * The value <code>"maxExclusive"</code>.
   * @see #MAXEXCLUSIVE_ELEMENT
   * @see #ELEMENT_TAGS
   * @see #nodeType(String)
   */
  public final static String MAXEXCLUSIVE_ELEMENT_TAG = "maxExclusive";

  /**
   * The value <code>"maxInclusive"</code>.
   * @see #MAXINCLUSIVE_ELEMENT
   * @see #ELEMENT_TAGS
   * @see #nodeType(String)
   */
  public final static String MAXINCLUSIVE_ELEMENT_TAG = "maxInclusive";

  /**
   * The value <code>"maxLength"</code>.
   * @see #MAXLENGTH_ELEMENT
   * @see #ELEMENT_TAGS
   * @see #nodeType(String)
   */
  public final static String MAXLENGTH_ELEMENT_TAG = "maxLength";

  /**
   * The value <code>"minExclusive"</code>.
   * @see #MINEXCLUSIVE_ELEMENT
   * @see #ELEMENT_TAGS
   * @see #nodeType(String)
   */
  public final static String MINEXCLUSIVE_ELEMENT_TAG = "minExclusive";

  /**
   * The value <code>"minInclusive"</code>.
   * @see #MININCLUSIVE_ELEMENT
   * @see #ELEMENT_TAGS
   * @see #nodeType(String)
   */
  public final static String MININCLUSIVE_ELEMENT_TAG = "minInclusive";

  /**
   * The value <code>"minLength"</code>.
   * @see #MINLENGTH_ELEMENT
   * @see #ELEMENT_TAGS
   * @see #nodeType(String)
   */
  public final static String MINLENGTH_ELEMENT_TAG = "minLength";

  /**
   * The value <code>"notation"</code>.
   * @see #NOTATION_ELEMENT
   * @see #ELEMENT_TAGS
   * @see #nodeType(String)
   */
  public final static String NOTATION_ELEMENT_TAG = "notation";

  /**
   * The value <code>"pattern"</code>.
   * @see #PATTERN_ELEMENT
   * @see #ELEMENT_TAGS
   * @see #nodeType(String)
   */
  public final static String PATTERN_ELEMENT_TAG = "pattern";

  /**
   * The value <code>"redefine"</code>.
   * @see #REDEFINE_ELEMENT
   * @see #ELEMENT_TAGS
   * @see #nodeType(String)
   */
  public final static String REDEFINE_ELEMENT_TAG = "redefine";

  /**
   * The value <code>"restriction"</code>.
   * @see #RESTRICTION_ELEMENT
   * @see #ELEMENT_TAGS
   * @see #nodeType(String)
   */
  public final static String RESTRICTION_ELEMENT_TAG = "restriction";

  /**
   * The value <code>"schema"</code>.
   * @see #SCHEMA_ELEMENT
   * @see #ELEMENT_TAGS
   * @see #nodeType(String)
   */
  public final static String SCHEMA_ELEMENT_TAG = "schema";

  /**
   * The value <code>"selector"</code>.
   * @see #SELECTOR_ELEMENT
   * @see #ELEMENT_TAGS
   * @see #nodeType(String)
   */
  public final static String SELECTOR_ELEMENT_TAG = "selector";

  /**
   * The value <code>"sequence"</code>.
   * @see #SEQUENCE_ELEMENT
   * @see #ELEMENT_TAGS
   * @see #nodeType(String)
   */
  public final static String SEQUENCE_ELEMENT_TAG = "sequence";

  /**
   * The value <code>"simpleContent"</code>.
   * @see #SIMPLECONTENT_ELEMENT
   * @see #ELEMENT_TAGS
   * @see #nodeType(String)
   */
  public final static String SIMPLECONTENT_ELEMENT_TAG = "simpleContent";

  /**
   * The value <code>"simpleType"</code>.
   * @see #SIMPLETYPE_ELEMENT
   * @see #ELEMENT_TAGS
   * @see #nodeType(String)
   */
  public final static String SIMPLETYPE_ELEMENT_TAG = "simpleType";

  /**
   * The value <code>"totalDigits"</code>.
   * @see #TOTALDIGITS_ELEMENT
   * @see #ELEMENT_TAGS
   * @see #nodeType(String)
   */
  public final static String TOTALDIGITS_ELEMENT_TAG = "totalDigits";

  /**
   * The value <code>"union"</code>.
   * @see #UNION_ELEMENT
   * @see #ELEMENT_TAGS
   * @see #nodeType(String)
   */
  public final static String UNION_ELEMENT_TAG = "union";

  /**
   * The value <code>"unique"</code>.
   * @see #UNIQUE_ELEMENT
   * @see #ELEMENT_TAGS
   * @see #nodeType(String)
   */
  public final static String UNIQUE_ELEMENT_TAG = "unique";

  /**
   * The value <code>"whiteSpace"</code>.
   * @see #WHITESPACE_ELEMENT
   * @see #ELEMENT_TAGS
   * @see #nodeType(String)
   */
  public final static String WHITESPACE_ELEMENT_TAG = "whiteSpace";

  /**
   * The value <code>0</code>.
   * @see #ALL_ELEMENT_TAG
   * @see #ELEMENT_TAGS
   * @see #nodeType(String)
   */
  public final static int ALL_ELEMENT = 0;

  /**
   * The value <code>1</code>.
   * @see #ANNOTATION_ELEMENT_TAG
   * @see #ELEMENT_TAGS
   * @see #nodeType(String)
   */
  public final static int ANNOTATION_ELEMENT = 1;

  /**
   * The value <code>2</code>.
   * @see #ANY_ELEMENT_TAG
   * @see #ELEMENT_TAGS
   * @see #nodeType(String)
   */
  public final static int ANY_ELEMENT = 2;

  /**
   * The value <code>3</code>.
   * @see #ANYATTRIBUTE_ELEMENT_TAG
   * @see #ELEMENT_TAGS
   * @see #nodeType(String)
   */
  public final static int ANYATTRIBUTE_ELEMENT = 3;

  /**
   * The value <code>4</code>.
   * @see #APPINFO_ELEMENT_TAG
   * @see #ELEMENT_TAGS
   * @see #nodeType(String)
   */
  public final static int APPINFO_ELEMENT = 4;

  /**
   * The value <code>5</code>.
   * @see #ATTRIBUTE_ELEMENT_TAG
   * @see #ELEMENT_TAGS
   * @see #nodeType(String)
   */
  public final static int ATTRIBUTE_ELEMENT = 5;

  /**
   * The value <code>6</code>.
   * @see #ATTRIBUTEGROUP_ELEMENT_TAG
   * @see #ELEMENT_TAGS
   * @see #nodeType(String)
   */
  public final static int ATTRIBUTEGROUP_ELEMENT = 6;

  /**
   * The value <code>7</code>.
   * @see #CHOICE_ELEMENT_TAG
   * @see #ELEMENT_TAGS
   * @see #nodeType(String)
   */
  public final static int CHOICE_ELEMENT = 7;

  /**
   * The value <code>8</code>.
   * @see #COMPLEXCONTENT_ELEMENT_TAG
   * @see #ELEMENT_TAGS
   * @see #nodeType(String)
   */
  public final static int COMPLEXCONTENT_ELEMENT = 8;

  /**
   * The value <code>9</code>.
   * @see #COMPLEXTYPE_ELEMENT_TAG
   * @see #ELEMENT_TAGS
   * @see #nodeType(String)
   */
  public final static int COMPLEXTYPE_ELEMENT = 9;

  /**
   * The value <code>10</code>.
   * @see #DOCUMENTATION_ELEMENT_TAG
   * @see #ELEMENT_TAGS
   * @see #nodeType(String)
   */
  public final static int DOCUMENTATION_ELEMENT = 10;

  /**
   * The value <code>11</code>.
   * @see #ELEMENT_ELEMENT_TAG
   * @see #ELEMENT_TAGS
   * @see #nodeType(String)
   */
  public final static int ELEMENT_ELEMENT = 11;

  /**
   * The value <code>12</code>.
   * @see #ENUMERATION_ELEMENT_TAG
   * @see #ELEMENT_TAGS
   * @see #nodeType(String)
   */
  public final static int ENUMERATION_ELEMENT = 12;

  /**
   * The value <code>13</code>.
   * @see #EXTENSION_ELEMENT_TAG
   * @see #ELEMENT_TAGS
   * @see #nodeType(String)
   */
  public final static int EXTENSION_ELEMENT = 13;

  /**
   * The value <code>14</code>.
   * @see #FIELD_ELEMENT_TAG
   * @see #ELEMENT_TAGS
   * @see #nodeType(String)
   */
  public final static int FIELD_ELEMENT = 14;

  /**
   * The value <code>15</code>.
   * @see #FRACTIONDIGITS_ELEMENT_TAG
   * @see #ELEMENT_TAGS
   * @see #nodeType(String)
   */
  public final static int FRACTIONDIGITS_ELEMENT = 15;

  /**
   * The value <code>16</code>.
   * @see #GROUP_ELEMENT_TAG
   * @see #ELEMENT_TAGS
   * @see #nodeType(String)
   */
  public final static int GROUP_ELEMENT = 16;

  /**
   * The value <code>17</code>.
   * @see #IMPORT_ELEMENT_TAG
   * @see #ELEMENT_TAGS
   * @see #nodeType(String)
   */
  public final static int IMPORT_ELEMENT = 17;

  /**
   * The value <code>18</code>.
   * @see #INCLUDE_ELEMENT_TAG
   * @see #ELEMENT_TAGS
   * @see #nodeType(String)
   */
  public final static int INCLUDE_ELEMENT = 18;

  /**
   * The value <code>19</code>.
   * @see #KEY_ELEMENT_TAG
   * @see #ELEMENT_TAGS
   * @see #nodeType(String)
   */
  public final static int KEY_ELEMENT = 19;

  /**
   * The value <code>20</code>.
   * @see #KEYREF_ELEMENT_TAG
   * @see #ELEMENT_TAGS
   * @see #nodeType(String)
   */
  public final static int KEYREF_ELEMENT = 20;

  /**
   * The value <code>21</code>.
   * @see #LENGTH_ELEMENT_TAG
   * @see #ELEMENT_TAGS
   * @see #nodeType(String)
   */
  public final static int LENGTH_ELEMENT = 21;

  /**
   * The value <code>22</code>.
   * @see #LIST_ELEMENT_TAG
   * @see #ELEMENT_TAGS
   * @see #nodeType(String)
   */
  public final static int LIST_ELEMENT = 22;

  /**
   * The value <code>23</code>.
   * @see #MAXEXCLUSIVE_ELEMENT_TAG
   * @see #ELEMENT_TAGS
   * @see #nodeType(String)
   */
  public final static int MAXEXCLUSIVE_ELEMENT = 23;

  /**
   * The value <code>24</code>.
   * @see #MAXINCLUSIVE_ELEMENT_TAG
   * @see #ELEMENT_TAGS
   * @see #nodeType(String)
   */
  public final static int MAXINCLUSIVE_ELEMENT = 24;

  /**
   * The value <code>25</code>.
   * @see #MAXLENGTH_ELEMENT_TAG
   * @see #ELEMENT_TAGS
   * @see #nodeType(String)
   */
  public final static int MAXLENGTH_ELEMENT = 25;

  /**
   * The value <code>26</code>.
   * @see #MINEXCLUSIVE_ELEMENT_TAG
   * @see #ELEMENT_TAGS
   * @see #nodeType(String)
   */
  public final static int MINEXCLUSIVE_ELEMENT = 26;

  /**
   * The value <code>27</code>.
   * @see #MININCLUSIVE_ELEMENT_TAG
   * @see #ELEMENT_TAGS
   * @see #nodeType(String)
   */
  public final static int MININCLUSIVE_ELEMENT = 27;

  /**
   * The value <code>28</code>.
   * @see #MINLENGTH_ELEMENT_TAG
   * @see #ELEMENT_TAGS
   * @see #nodeType(String)
   */
  public final static int MINLENGTH_ELEMENT = 28;

  /**
   * The value <code>29</code>.
   * @see #NOTATION_ELEMENT_TAG
   * @see #ELEMENT_TAGS
   * @see #nodeType(String)
   */
  public final static int NOTATION_ELEMENT = 29;

  /**
   * The value <code>30</code>.
   * @see #PATTERN_ELEMENT_TAG
   * @see #ELEMENT_TAGS
   * @see #nodeType(String)
   */
  public final static int PATTERN_ELEMENT = 30;

  /**
   * The value <code>31</code>.
   * @see #REDEFINE_ELEMENT_TAG
   * @see #ELEMENT_TAGS
   * @see #nodeType(String)
   */
  public final static int REDEFINE_ELEMENT = 31;

  /**
   * The value <code>32</code>.
   * @see #RESTRICTION_ELEMENT_TAG
   * @see #ELEMENT_TAGS
   * @see #nodeType(String)
   */
  public final static int RESTRICTION_ELEMENT = 32;

  /**
   * The value <code>33</code>.
   * @see #SCHEMA_ELEMENT_TAG
   * @see #ELEMENT_TAGS
   * @see #nodeType(String)
   */
  public final static int SCHEMA_ELEMENT = 33;

  /**
   * The value <code>34</code>.
   * @see #SELECTOR_ELEMENT_TAG
   * @see #ELEMENT_TAGS
   * @see #nodeType(String)
   */
  public final static int SELECTOR_ELEMENT = 34;

  /**
   * The value <code>35</code>.
   * @see #SEQUENCE_ELEMENT_TAG
   * @see #ELEMENT_TAGS
   * @see #nodeType(String)
   */
  public final static int SEQUENCE_ELEMENT = 35;

  /**
   * The value <code>36</code>.
   * @see #SIMPLECONTENT_ELEMENT_TAG
   * @see #ELEMENT_TAGS
   * @see #nodeType(String)
   */
  public final static int SIMPLECONTENT_ELEMENT = 36;

  /**
   * The value <code>37</code>.
   * @see #SIMPLETYPE_ELEMENT_TAG
   * @see #ELEMENT_TAGS
   * @see #nodeType(String)
   */
  public final static int SIMPLETYPE_ELEMENT = 37;

  /**
   * The value <code>38</code>.
   * @see #TOTALDIGITS_ELEMENT_TAG
   * @see #ELEMENT_TAGS
   * @see #nodeType(String)
   */
  public final static int TOTALDIGITS_ELEMENT = 38;

  /**
   * The value <code>39</code>.
   * @see #UNION_ELEMENT_TAG
   * @see #ELEMENT_TAGS
   * @see #nodeType(String)
   */
  public final static int UNION_ELEMENT = 39;

  /**
   * The value <code>40</code>.
   * @see #UNIQUE_ELEMENT_TAG
   * @see #ELEMENT_TAGS
   * @see #nodeType(String)
   */
  public final static int UNIQUE_ELEMENT = 40;

  /**
   * The value <code>41</code>.
   * @see #WHITESPACE_ELEMENT_TAG
   * @see #ELEMENT_TAGS
   * @see #nodeType(String)
   */
  public final static int WHITESPACE_ELEMENT = 41;

  /**
   * The sorted strings representing all 
   * <a href="http://www.w3.org/TR/xmlschema-2/#schema">XML Schema</a> element tags.
   * @see #nodeType(String)
   * @see #nodeType(Node)
   */
  public static final String [] ELEMENT_TAGS =
    new String []
    {
      ALL_ELEMENT_TAG,
      ANNOTATION_ELEMENT_TAG,
      ANY_ELEMENT_TAG,
      ANYATTRIBUTE_ELEMENT_TAG,
      APPINFO_ELEMENT_TAG,
      ATTRIBUTE_ELEMENT_TAG,
      ATTRIBUTEGROUP_ELEMENT_TAG,
      CHOICE_ELEMENT_TAG,
      COMPLEXCONTENT_ELEMENT_TAG,
      COMPLEXTYPE_ELEMENT_TAG,
      DOCUMENTATION_ELEMENT_TAG,
      ELEMENT_ELEMENT_TAG,
      ENUMERATION_ELEMENT_TAG,
      EXTENSION_ELEMENT_TAG,
      FIELD_ELEMENT_TAG,
      FRACTIONDIGITS_ELEMENT_TAG,
      GROUP_ELEMENT_TAG,
      IMPORT_ELEMENT_TAG,
      INCLUDE_ELEMENT_TAG,
      KEY_ELEMENT_TAG,
      KEYREF_ELEMENT_TAG,
      LENGTH_ELEMENT_TAG,
      LIST_ELEMENT_TAG,
      MAXEXCLUSIVE_ELEMENT_TAG,
      MAXINCLUSIVE_ELEMENT_TAG,
      MAXLENGTH_ELEMENT_TAG,
      MINEXCLUSIVE_ELEMENT_TAG,
      MININCLUSIVE_ELEMENT_TAG,
      MINLENGTH_ELEMENT_TAG,
      NOTATION_ELEMENT_TAG,
      PATTERN_ELEMENT_TAG,
      REDEFINE_ELEMENT_TAG,
      RESTRICTION_ELEMENT_TAG,
      SCHEMA_ELEMENT_TAG,
      SELECTOR_ELEMENT_TAG,
      SEQUENCE_ELEMENT_TAG,
      SIMPLECONTENT_ELEMENT_TAG,
      SIMPLETYPE_ELEMENT_TAG,
      TOTALDIGITS_ELEMENT_TAG,
      UNION_ELEMENT_TAG,
      UNIQUE_ELEMENT_TAG,
      WHITESPACE_ELEMENT_TAG
    };

  /**
   * Returns the index of the element type, 
   * if it is an <a href="http://www.w3.org/TR/xmlschema-2/#schema">XML Schema</a> element tag, or -1.
   * @return the index of the element type.
   * @see #ELEMENT_TAGS
   * @see #nodeType(Node)
   */
  public static final int nodeType(String localName)
  {
    for (int i = 0; i < ELEMENT_TAGS.length; ++i)
    {
      if (localName.equals(ELEMENT_TAGS[i]))
      {
        return i;
      }
    }
    return -1;
  }

  /**
   * Returns the index of the node's element type, 
   * if it is an <a href="http://www.w3.org/TR/xmlschema-2/#schema">XML Schema</a> element tag, or -1.
   * @return the index of the node's element type.
   * @see #ELEMENT_TAGS
   * @see #nodeType(String)
   */
  public static final int nodeType(Node node)
  {
    return 
      isSchemaForSchemaNamespace(node.getNamespaceURI()) ?
        nodeType(node.getLocalName()) :
        -1;
  }

  /**
   * Returns the URI representation of the node's namespace URI and local name,
   * i.e., <code><namespace-uri>#<local-name></code>.
   * @return the URI representation of the node's namespace URI and local name.
   */
  public static final String uri(Node node)
  {
    String namespaceURI = node.getNamespaceURI();
    if (namespaceURI == null)
    {
      namespaceURI = "";
    }
    return  namespaceURI + "#" + node.getLocalName();
  }

  // abstract
  // attributeFormDefault
  // base
  // block
  // blockDefault
  // default
  // elementFormDefault
  // final
  // finalDefault
  // fixed
  // form
  // id
  // itemType
  // maxOccurs
  // memberTypes
  // minOccurs
  // mixed
  // name
  // namespace
  // nillable
  // processContents
  // public
  // ref
  // refer
  // schemaLocation
  // source
  // substitutionGroup
  // system
  // targetNamespace
  // type
  // use
  // value
  // version
  // xpath
  // (
  // for i in $(cat attributes); do
  // j=$(echo $i | tr '[:lower:]' '[:upper:]');
  // echo '  /**';
  // echo '   * The value <code>"'$i'"</code>; an attribute on an {@link #ELEMENT_TAGS XML Schema element}.';
  // echo '   * @see #ELEMENT_TAGS';
  // echo '   */';
  // echo '  public final static String '$j'_ATTRIBUTE = "'$i'";';
  // echo '';
  // done;
  // )

  /**
   * The value <code>"abstract"</code>; an attribute on an {@link #ELEMENT_TAGS XML Schema element}.
   * @see #ELEMENT_TAGS
   */
  public final static String ABSTRACT_ATTRIBUTE = "abstract";

  /**
   * The value <code>"attributeFormDefault"</code>; an attribute on an {@link #ELEMENT_TAGS XML Schema element}.
   * @see #ELEMENT_TAGS
   */
  public final static String ATTRIBUTEFORMDEFAULT_ATTRIBUTE = "attributeFormDefault";

  /**
   * The value <code>"base"</code>; an attribute on an {@link #ELEMENT_TAGS XML Schema element}.
   * @see #ELEMENT_TAGS
   */
  public final static String BASE_ATTRIBUTE = "base";

  /**
   * The value <code>"block"</code>; an attribute on an {@link #ELEMENT_TAGS XML Schema element}.
   * @see #ELEMENT_TAGS
   */
  public final static String BLOCK_ATTRIBUTE = "block";

  /**
   * The value <code>"blockDefault"</code>; an attribute on an {@link #ELEMENT_TAGS XML Schema element}.
   * @see #ELEMENT_TAGS
   */
  public final static String BLOCKDEFAULT_ATTRIBUTE = "blockDefault";

  /**
   * The value <code>"defRef"</code>; an attribute on an {@link #ELEMENT_TAGS XML Schema element}.
   * @see #ELEMENT_TAGS
   */
  public final static String DEFREF_ATTRIBUTE = "defRef";

  /**
   * The value <code>"default"</code>; an attribute on an {@link #ELEMENT_TAGS XML Schema element}.
   * @see #ELEMENT_TAGS
   */
  public final static String DEFAULT_ATTRIBUTE = "default";

  /**
   * The value <code>"elementFormDefault"</code>; an attribute on an {@link #ELEMENT_TAGS XML Schema element}.
   * @see #ELEMENT_TAGS
   */
  public final static String ELEMENTFORMDEFAULT_ATTRIBUTE = "elementFormDefault";

  /**
   * The value <code>"final"</code>; an attribute on an {@link #ELEMENT_TAGS XML Schema element}.
   * @see #ELEMENT_TAGS
   */
  public final static String FINAL_ATTRIBUTE = "final";

  /**
   * The value <code>"finalDefault"</code>; an attribute on an {@link #ELEMENT_TAGS XML Schema element}.
   * @see #ELEMENT_TAGS
   */
  public final static String FINALDEFAULT_ATTRIBUTE = "finalDefault";

  /**
   * The value <code>"fixed"</code>; an attribute on an {@link #ELEMENT_TAGS XML Schema element}.
   * @see #ELEMENT_TAGS
   */
  public final static String FIXED_ATTRIBUTE = "fixed";

  /**
   * The value <code>"form"</code>; an attribute on an {@link #ELEMENT_TAGS XML Schema element}.
   * @see #ELEMENT_TAGS
   */
  public final static String FORM_ATTRIBUTE = "form";

  /**
   * The value <code>"id"</code>; an attribute on an {@link #ELEMENT_TAGS XML Schema element}.
   * @see #ELEMENT_TAGS
   */
  public final static String ID_ATTRIBUTE = "id";

  /**
   * The value <code>"itemType"</code>; an attribute on an {@link #ELEMENT_TAGS XML Schema element}.
   * @see #ELEMENT_TAGS
   */
  public final static String ITEMTYPE_ATTRIBUTE = "itemType";

  /**
   * The value <code>"maxOccurs"</code>; an attribute on an {@link #ELEMENT_TAGS XML Schema element}.
   * @see #ELEMENT_TAGS
   */
  public final static String MAXOCCURS_ATTRIBUTE = "maxOccurs";

  /**
   * The value <code>"memberTypes"</code>; an attribute on an {@link #ELEMENT_TAGS XML Schema element}.
   * @see #ELEMENT_TAGS
   */
  public final static String MEMBERTYPES_ATTRIBUTE = "memberTypes";

  /**
   * The value <code>"minOccurs"</code>; an attribute on an {@link #ELEMENT_TAGS XML Schema element}.
   * @see #ELEMENT_TAGS
   */
  public final static String MINOCCURS_ATTRIBUTE = "minOccurs";

  /**
   * The value <code>"mixed"</code>; an attribute on an {@link #ELEMENT_TAGS XML Schema element}.
   * @see #ELEMENT_TAGS
   */
  public final static String MIXED_ATTRIBUTE = "mixed";

  /**
   * The value <code>"name"</code>; an attribute on an {@link #ELEMENT_TAGS XML Schema element}.
   * @see #ELEMENT_TAGS
   */
  public final static String NAME_ATTRIBUTE = "name";

  /**
   * The value <code>"namespace"</code>; an attribute on an {@link #ELEMENT_TAGS XML Schema element}.
   * @see #ELEMENT_TAGS
   */
  public final static String NAMESPACE_ATTRIBUTE = "namespace";

  /**
   * The value <code>"nillable"</code>; an attribute on an {@link #ELEMENT_TAGS XML Schema element}.
   * @see #ELEMENT_TAGS
   */
  public final static String NILLABLE_ATTRIBUTE = "nillable";

  /**
   * The value <code>"occurs"</code>; an attribute on an {@link #ELEMENT_TAGS XML Schema element}.
   * @see #ELEMENT_TAGS
   */
  public final static String OCCURS_ATTRIBUTE = "occurs";

  /**
   * The value <code>"processContents"</code>; an attribute on an {@link #ELEMENT_TAGS XML Schema element}.
   * @see #ELEMENT_TAGS
   */
  public final static String PROCESSCONTENTS_ATTRIBUTE = "processContents";

  /**
   * The value <code>"public"</code>; an attribute on an {@link #ELEMENT_TAGS XML Schema element}.
   * @see #ELEMENT_TAGS
   */
  public final static String PUBLIC_ATTRIBUTE = "public";

  /**
   * The value <code>"ref"</code>; an attribute on an {@link #ELEMENT_TAGS XML Schema element}.
   * @see #ELEMENT_TAGS
   */
  public final static String REF_ATTRIBUTE = "ref";

  /**
   * The value <code>"refer"</code>; an attribute on an {@link #ELEMENT_TAGS XML Schema element}.
   * @see #ELEMENT_TAGS
   */
  public final static String REFER_ATTRIBUTE = "refer";

  /**
   * The value <code>"schemaLocation"</code>; an attribute on an {@link #ELEMENT_TAGS XML Schema element}.
   * @see #ELEMENT_TAGS
   */
  public final static String SCHEMALOCATION_ATTRIBUTE = "schemaLocation";

  /**
   * The value <code>"source"</code>; an attribute on an {@link #ELEMENT_TAGS XML Schema element}.
   * @see #ELEMENT_TAGS
   */
  public final static String SOURCE_ATTRIBUTE = "source";

  /**
   * The value <code>"substitutionGroup"</code>; an attribute on an {@link #ELEMENT_TAGS XML Schema element}.
   * @see #ELEMENT_TAGS
   */
  public final static String SUBSTITUTIONGROUP_ATTRIBUTE = "substitutionGroup";

  /**
   * The value <code>"system"</code>; an attribute on an {@link #ELEMENT_TAGS XML Schema element}.
   * @see #ELEMENT_TAGS
   */
  public final static String SYSTEM_ATTRIBUTE = "system";

  /**
   * The value <code>"targetNamespace"</code>; an attribute on an {@link #ELEMENT_TAGS XML Schema element}.
   * @see #ELEMENT_TAGS
   */
  public final static String TARGETNAMESPACE_ATTRIBUTE = "targetNamespace";

  /**
   * The value <code>"type"</code>; an attribute on an {@link #ELEMENT_TAGS XML Schema element}.
   * @see #ELEMENT_TAGS
   */
  public final static String TYPE_ATTRIBUTE = "type";

  /**
   * The value <code>"use"</code>; an attribute on an {@link #ELEMENT_TAGS XML Schema element}.
   * @see #ELEMENT_TAGS
   */
  public final static String USE_ATTRIBUTE = "use";

  /**
   * The value <code>"value"</code>; an attribute on an {@link #ELEMENT_TAGS XML Schema element}.
   * @see #ELEMENT_TAGS
   */
  public final static String VALUE_ATTRIBUTE = "value";

  /**
   * The value <code>"version"</code>; an attribute on an {@link #ELEMENT_TAGS XML Schema element}.
   * @see #ELEMENT_TAGS
   */
  public final static String VERSION_ATTRIBUTE = "version";

  /**
   * The value <code>"xpath"</code>; an attribute on an {@link #ELEMENT_TAGS XML Schema element}.
   * @see #ELEMENT_TAGS
   */
  public final static String XPATH_ATTRIBUTE = "xpath";

  /**
   * The value <code>"http://www.w3.org/2001/XMLSchema-hasFacetAndProperty"</code>.
   */
  public static final String SCHEMA_HAS_FACET_AND_PROPERTY_URI_2001 = "http://www.w3.org/2001/XMLSchema-hasFacetAndProperty";

  /**
   * Returns whether the give namespace is one of the XML Schema <a href="http://www.w3.org/TR/xmlschema-2/#schema">has facet and property</a> namespaces.
   * @param namespace a namespace.
   * @return whether the give namespace is one of the XML Schema has facet and property namespaces.
   */
  public static boolean isSchemaHasFacetAndPropertyNamespace(String namespace)
  {
    return 
      SCHEMA_HAS_FACET_AND_PROPERTY_URI_2001.equals(namespace);
  }

  /**
   * The value <code>"hasFacet"</code>.
   * @see #HFP_HASFACET_ELEMENT
   * @see #HFP_ELEMENT_TAGS
   * @see #hfpNodeType(String)
   */
  public final static String HFP_HASFACET_ELEMENT_TAG = "hasFacet";

  /**
   * The value <code>"hasProperty"</code>.
   * @see #HFP_HASPROPERTY_ELEMENT
   * @see #HFP_ELEMENT_TAGS
   * @see #hfpNodeType(String)
   */
  public final static String HFP_HASPROPERTY_ELEMENT_TAG = "hasProperty";

  /**
   * The value <code>0</code>.
   * @see #HFP_HASFACET_ELEMENT_TAG
   * @see #HFP_ELEMENT_TAGS
   * @see #hfpNodeType(String)
   */
  public final static int HFP_HASFACET_ELEMENT = 0;

  /**
   * The value <code>1</code>.
   * @see #HFP_HASPROPERTY_ELEMENT_TAG
   * @see #HFP_ELEMENT_TAGS
   * @see #hfpNodeType(String)
   */
  public final static int HFP_HASPROPERTY_ELEMENT = 1;

  /**
   * The sorted strings representing all XML Schema 
   * <a href="http://www.w3.org/TR/xmlschema-2/#schema">has facet and property</a> element tags.
   * @see #hfpNodeType(String)
   */
  public static final String [] HFP_ELEMENT_TAGS = 
    new String [] 
    {
      HFP_HASFACET_ELEMENT_TAG,
      HFP_HASPROPERTY_ELEMENT_TAG
    };

  /**
   * Returns the index of the element type, 
   * if it is an XML Schema <a href="http://www.w3.org/TR/xmlschema-2/#schema">has facet and property</a> element tag, or -1.
   * @see #HFP_ELEMENT_TAGS
   * @see #hfpNodeType(Node)
   * @return the index of the element type.
   */
  public static final int hfpNodeType(String localName)
  {
    for (int i = 0; i < HFP_ELEMENT_TAGS.length; ++i)
    {
      if (localName.equals(HFP_ELEMENT_TAGS[i]))
      {
        return i;
      }
    }
    return -1;
  }

  /**
   * Returns the index of the node's element type, 
   * if it is an XML Schema <a href="http://www.w3.org/TR/xmlschema-2/#schema">has facet and property</a> element tag, or -1.
   * @see #isSchemaHasFacetAndPropertyNamespace
   * @see #HFP_ELEMENT_TAGS
   * @see #hfpNodeType(String)
   * @return the index of the node's element type, 
   */
  public static int hfpNodeType(Node node)
  {
    return 
      isSchemaHasFacetAndPropertyNamespace(node.getNamespaceURI()) ?
        hfpNodeType(node.getLocalName()) :
        -1;
  }

  /**
   * The value <code>"name"</code>; an attribute on an XML Schema {@link #HFP_ELEMENT_TAGS has facet and property element}.
   * @see #HFP_ELEMENT_TAGS
   */
  public final static String HFP_NAME_ATTRIBUTE = "name";

  /**
   * The value <code>"value"</code>; an attribute on an XML Schema {@link #HFP_ELEMENT_TAGS has facet and property element}.
   * @see #HFP_ELEMENT_TAGS
   */
  public final static String HFP_VALUE_ATTRIBUTE = "value";


  /**
   * Returns the URI corresponding to the resolved QName of the value of the attribute of the element.
   * @param element an element.
   * @param localNameOfAttribute the name of the attribute whose value to use.
   * @return the URI corresponding to the resolved QName of the value of the attribute of the element.
   * @see #lookupQName(Element, String)
   * @see Element#getAttribute(String)
   */
  public static String lookupQNameForAttribute(Element element, String localNameOfAttribute)
  {
    if (element != null && element.hasAttribute(localNameOfAttribute))
    {
      // Some DOM implementations return a null here.
      //
      String qName = XMLTypeUtil.normalize(element.getAttribute(localNameOfAttribute), true);
      return lookupQName(element, qName);
    }
    else
    {
      return "";
    }
  }

  /**
   * Returns the URI corresponding to the resolved QName.
   * @param element an element.
   * @param qName the QName to resolve in the context of the given element.
   * @return the URI corresponding to the resolved QName.
   */

  public static String lookupQName(Element element, String qName)
  {
    // Some DOM implementations return a null here.
    //
    int index = qName == null ? -1 : qName.lastIndexOf(':');
    String namespaceAttribute = "xmlns";
    if (index != -1)
    {
      @SuppressWarnings("null")
      String prefix = qName.substring(0, index);
      if (prefix.equals("xml"))
      {
        return XSDConstants.XML_NAMESPACE_URI_1998 + "#" + qName.substring(index + 1);
      }
      namespaceAttribute = "xmlns:" + qName.substring(0, index);
    }
    for (Node node = element; node instanceof Element; node = node.getParentNode())
    {
      Element elementNode = (Element)node;
      if (elementNode.hasAttribute(namespaceAttribute))
      {
        String namespace = elementNode.getAttribute(namespaceAttribute);
        if ("".equals(namespace))
        {
          break;
        }
        String localName = qName == null ? "" : qName.substring(index + 1);
        return namespace + "#" + localName;
      }
    }

    return qName == null ? "" : index == -1 ? qName : qName.substring(0, index) + "#" + qName.substring(index + 1);
  }

  /**
   * Returns the QName qualifier than is available at the given node to access the given namespace URI.
   * A null string, indicates that no qualifier is to be used; 
   * a null value indicates that no qualifier is available.
   * @param node the context node at which to lookup a qualifier.
   * @param namespaceURI the namespace to be referenced.
   * @return the QName qualifier than is available at the given node to access the given namespace URI.
   */
  public static String lookupQualifier(Node node, String namespaceURI)
  {
    if (XSDConstants.XML_NAMESPACE_URI_1998.equals(namespaceURI))
    {
      return "xml";
    }

    Element leaf = null;
    for (; node.getNodeType() == Node.ELEMENT_NODE; node = node.getParentNode())
    {
      Element elementNode = (Element)node;
      if (leaf == null)
      {
        leaf = elementNode;
      }
      NamedNodeMap attributes = elementNode.getAttributes();
      LOOP:
      for (int i = 0, size = attributes.getLength(); i < size; ++i)
      {
        Attr attr = (Attr)attributes.item(i);
        String name = attr.getNodeName();
        if (name.startsWith("xmlns"))
        {
          String candidateNamespaceURI = attr.getNodeValue();
          if (candidateNamespaceURI == null || candidateNamespaceURI.length() == 0 ?
                namespaceURI == null || namespaceURI.length() == 0 :
                candidateNamespaceURI.equals(namespaceURI))
          {
            int index = name.indexOf(':');
            String result = index == -1 ? "" : name.substring(index + 1);
            // Verify that there isn't another prefix declaration hiding the one we've found.
            //
            for (Element element = leaf; element != elementNode; element = (Element)element.getParentNode())
            {
              if (element.hasAttribute(name))
              {
                continue LOOP;
              }
            }
            return result;
          }
        }
      }
    }

    return namespaceURI == null || namespaceURI.length() == 0 ? "" : null;
  }

  /**
   * Resolve the namespace and schema location relative to the base schema location.
   * For example, the expression
   *<pre>
   *  resolveSchemaLocation
   *    ("http://www.example.com/A/a.xsd", 
   *     "http://www.example.com/B", 
   *     "../B/b.xsd");
   *</pre>
   * would yield
   *<pre>
   *  "http://www.example.com/B/b.xsd"
   *</pre>
   * When no namespace schema location is provided, the namespace itself will be used.
   * @param baseSchemaLocationURI the location of the schema containing the reference, i.e., {@link org.eclipse.xsd.XSDSchema#getSchemaLocation}.
   * @param namespaceURI the namespace being resolved, i.e., {@link org.eclipse.xsd.XSDImport#getNamespace}.
   * @param schemaLocationURI the suggested location of the namespace being resolved, i.e., {@link org.eclipse.xsd.XSDImport#getSchemaLocation}.
   * @return the resolved schema location.
   */
  public static String resolveSchemaLocation(String baseSchemaLocationURI, String namespaceURI,  String schemaLocationURI)
  {
    try
    {
      if (schemaLocationURI == null)
      {
        schemaLocationURI = namespaceURI;
      }
      if (schemaLocationURI != null)
      {
        URI baseSchemaLocation = URI.createURI(baseSchemaLocationURI);
        URI schemaLocation = URI.createURI(schemaLocationURI);
        if (baseSchemaLocation.isHierarchical() && !baseSchemaLocation.isRelative() && schemaLocation.isRelative())
        {
          schemaLocation = schemaLocation.resolve(baseSchemaLocation);
        }

        return schemaLocation.toString();
      }
    }
    catch (Exception exception)
    {
      exception.printStackTrace();
    }

    return schemaLocationURI;
  }

  /**
   * Returns whether the type definition is one of the flavours of the ur-type, i.e., 
   * complex <a href="http://www.w3.org/TR/xmlschema-1/#ur-type-itself">anyType</a>,
   * simple <a href="http://www.w3.org/TR/xmlschema-2/#built-in-datatypes">anyType</a>, or
   * <a href="http://www.w3.org/TR/xmlschema-2/#dt-anySimpleType">anySimpleType</a>.
   * @param xsdTypeDefinition a simple or complex type definition.
   * @return whether the type definition is one of the flavours of the ur-type.
   */
  public static boolean isURType(XSDTypeDefinition xsdTypeDefinition)
  {
    String name = xsdTypeDefinition.getName();
    return 
      isSchemaForSchemaNamespace(xsdTypeDefinition.getTargetNamespace()) &&
        ("anyType".equals(name) || "anySimpleType".equals(name));
  }

  /**
   * Returns whether the type definition is one of the flavours of the anyType, i.e., 
   * complex <a href="http://www.w3.org/TR/xmlschema-1/#ur-type-itself">anyType</a> or
   * simple <a href="http://www.w3.org/TR/xmlschema-2/#built-in-datatypes">anyType</a>.
   * @param xsdTypeDefinition a simple or complex type definition.
   * @return whether the type definition is one of the flavours of the anyType.
   */
  public static boolean isAnyType(XSDTypeDefinition xsdTypeDefinition)
  {
    return 
      isSchemaForSchemaNamespace(xsdTypeDefinition.getTargetNamespace()) &&
        "anyType".equals(xsdTypeDefinition.getName());
  }

  /**
   * Returns whether the type definition is the 
   * <a href="http://www.w3.org/TR/xmlschema-2/#dt-anySimpleType">anySimpleType</a>.
   * @param xsdTypeDefinition a simple or complex type definition.
   * @return whether the type definition is the anySimpleType.
   */
  public static boolean isAnySimpleType(XSDTypeDefinition xsdTypeDefinition)
  {
    return 
      isSchemaForSchemaNamespace(xsdTypeDefinition.getTargetNamespace()) &&
        "anySimpleType".equals(xsdTypeDefinition.getName());
  }

  /**
   * Returns whether the type definition has the given namespace and name 
   * or is derived from a type with that namespace and name.
   * @param xsdTypeDefinition a type definition.
   * @return  whether the type definition has the given namespace and name or is derived from a type with that namespace and name.
   * @since 2.4
   */
  public static boolean isOrIsDerivedFrom(XSDTypeDefinition xsdTypeDefinition, String name, String namespace)
  {
    Set<XSDTypeDefinition> visited = new HashSet<XSDTypeDefinition>();
    while (xsdTypeDefinition != null)
    {
      if (xsdTypeDefinition.hasNameAndTargetNamespace(name, namespace))
      {
        return true;
      }

      if (!visited.add(xsdTypeDefinition = xsdTypeDefinition.getBaseType()))
      {
        return false;
      }
    }

    return false;
  }

  /**
   * Returns whether the type definition is a built-in type with the given name 
   * or is derived from a built-in type with that name.
   * @param xsdSimpleTypeDefinition a type definition.
   * @return whether the type definition is a built-in type with the given name 
   * or is derived from a built-in type with that name.
   * @since 2.4
   */
  public static boolean isOrIsDerivedFrom(XSDSimpleTypeDefinition xsdSimpleTypeDefinition, String name)
  {
    Set<XSDTypeDefinition> visited = new HashSet<XSDTypeDefinition>();
    while (xsdSimpleTypeDefinition != null)
    {
      if (isSchemaForSchemaNamespace(xsdSimpleTypeDefinition.getTargetNamespace()) &&
            name.equals(xsdSimpleTypeDefinition.getName()))
      {
        return true;
      }

      if (!visited.add(xsdSimpleTypeDefinition = xsdSimpleTypeDefinition.getBaseTypeDefinition()))
      {
        return false;
      }
    }

    return false;
  }

  /**
   * Returns whether the simple type definition is
   * <a href="http://www.w3.org/TR/xmlschema-2/#dt-ID">ID</a> or a type derived from it.
   * @param xsdSimpleTypeDefinition a simple type definition.
   * @return whether the simple type definition is ID or a type derived from it.
   */
  public static boolean isOrIsDerivedFromID(XSDSimpleTypeDefinition xsdSimpleTypeDefinition)
  {
    return isOrIsDerivedFrom(xsdSimpleTypeDefinition, "ID");
  }
}
