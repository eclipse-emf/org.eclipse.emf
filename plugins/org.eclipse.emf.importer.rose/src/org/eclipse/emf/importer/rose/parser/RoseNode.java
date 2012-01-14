/**
 * Copyright (c) 2002-2006 IBM Corporation and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: 
 *   IBM - Initial API and implementation
 */
package org.eclipse.emf.importer.rose.parser;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.eclipse.emf.importer.rose.builder.RoseStrings;


/**
 * Is used to build a tree for Rose file.
 */
public class RoseNode
{
  public final static int STRING = 0;
  public final static int STRING_SEQ = 1;
  public final static int OBJECT = 2;
  public final static int LIST = 3;
  public final static int VALUE = 4;

  protected String key;
  protected String value;
  protected RoseNode parent;
  protected List<RoseNode> nodes = new ArrayList<RoseNode>();
  protected int type;
  protected String id;
  protected boolean commit = true;
  protected Object node;

  public RoseNode(String key, String value, int type)
  {
    this.key = key;
    this.value = value;
    this.type = type;
  }

  public void setParent(RoseNode parent)
  {
    this.parent = parent;
  }

  public RoseNode getParent()
  {
    return parent;
  }

  public RoseNode getRoot()
  {
    RoseNode result = this;
    for (RoseNode root = this.getParent(); root != null; root = root.getParent())
    {
      result = root;
    }
    return result;
  }

  public String getKey()
  {
    return key;
  }

  public String getValue()
  {
    return value;
  }

  public String getAllValues()
  {
    return getAllValues(false);
  }

  public String getAllValues(boolean preserveSpace)
  {
    if (type == STRING_SEQ)
    {
      StringBuffer temp = new StringBuffer();
      for (int i = 0; i < nodes.size(); i++)
      {
        RoseNode n = nodes.get(i);
        temp.append(n.getValue());
        if (preserveSpace && i < nodes.size() - 1)
        {
          temp.append('\n');
        }
      }
      return temp.toString();
    }
    else
    {
      return preserveSpace ? Util.trimQuotes(value) : value;
    }
  }

  public String getFilteredValues(String filter)
  {
    if (type == STRING_SEQ)
    {
      StringBuffer temp = new StringBuffer();
      for (int i = 0; i < nodes.size(); i++)
      {
        RoseNode n = nodes.get(i);
        if (i == 0 && Util.getWord(n.getValue(), 1).equals(filter))
        {
          continue;
        }
        temp.append(n.getValue());
        if (i < nodes.size() - 1)
        {
          temp.append('\n');
        }
      }
      return temp.toString();
    }
    else
    {
      String val = Util.trimQuotes(value);
      return Util.getWord(val, 1).equals(filter) ? "" : val;
    }
  }

  public void setValue(String value)
  {
    this.value = value;
  }

  public boolean getCommit()
  {
    return commit;
  }

  public void setCommit(boolean b)
  {
    commit = b;
  }

  public String getId()
  {
    return id;
  }

  public String getAtId()
  {
    int ind = value.lastIndexOf("@");
    return ind != -1 ? value.substring(ind + 1) : null;
  }

  public void setId(String id)
  {
    this.id = id;
  }

  public int getRoseNodeType()
  {
    return type;
  }

  public List<RoseNode> getNodes()
  {
    return nodes;
  }

  public void addNode(RoseNode n)
  {
    n.parent = this;
    nodes.add(n);
  }

  public void deleteNode(RoseNode n)
  {
    if (n != null)
    {
      nodes.remove(n);
    }
  }

  public RoseNode findNodeWithKey(String key)
  {
    for (int i = 0; i < nodes.size(); i++)
    {
      RoseNode node = nodes.get(i);
      if (key.equals(node.getKey()))
      {
        return node;
      }
    }
    return null;
  }

  public RoseNode findNodeWithValue(String value)
  {
    for (int i = 0; i < nodes.size(); i++)
    {
      RoseNode node = nodes.get(i);
      if (value.equals(node.getValue()))
      {
        return node;
      }
    }
    return null;
  }

  public RoseNode findNodeWithWord(String w)
  {
    for (int i = 0; i < nodes.size(); i++)
    {
      RoseNode node = nodes.get(i);
      String value = node.getValue();
      StringTokenizer st = new StringTokenizer(value);
      while (st.hasMoreTokens())
      {
        String tok = st.nextToken();
        if (tok.equals(w))
        {
          return node;
        }
      }
    }
    return null;
  }

  public String getRoseId()
  {
    RoseNode node = findNodeWithKey("quid");
    return node != null ? node.getValue() : null;
  }

  public String getRoseRefId()
  {
    RoseNode node = findNodeWithKey("quidu");
    return node != null ? node.getValue() : null;
  }

  public String getRoseSupplier()
  {
    RoseNode node = findNodeWithKey("supplier");
    return node != null ? Util.trimQuotes(node.getValue()) : null;
  }

  public void setNode(Object node)
  {
    this.node = node;
  }

  public Object getNode()
  {
    return node;
  }

  public String getType()
  {
    String attributeValue = getAttributeValue(RoseStrings.TYPE);
    return attributeValue;
  }

  public String getStereotype()
  {
    String attributeValue = getAttributeValue(RoseStrings.STEREOTYPE);
    return attributeValue;
  }

  public boolean isDerived()
  {
    String attributeValue = getAttributeValue(RoseStrings.DERIVED);
    return "true".equalsIgnoreCase(attributeValue);
  }

  public boolean isAbstract()
  {
    String attributeValue = getAttributeValue(RoseStrings.ABSTRACT);
    return "true".equalsIgnoreCase(attributeValue);
  }

  public boolean isNavigable()
  {
    String attributeValue = getAttributeValue(RoseStrings.IS_NAVIGABLE);
    return "true".equalsIgnoreCase(attributeValue);
  }

  public boolean isAggregate()
  {
    String attributeValue = getAttributeValue(RoseStrings.IS_AGGREGATE);
    return "true".equalsIgnoreCase(attributeValue);
  }

  public String getContainment()
  {
    String attributeValue = getAttributeValue(RoseStrings.CONTAINMENT);
    return attributeValue;
  }

  public String getConstraints()
  {
    String attributeValue = getAttributeValue(RoseStrings.CONSTRAINTS);
    return attributeValue;
  }

  public String getResult()
  {
    String attributeValue = getAttributeValue(RoseStrings.RESULT);
    return attributeValue;
  }

  public String getExceptions()
  {
    String attributeValue = getAttributeValue(RoseStrings.EXCEPTIONS);
    return attributeValue;
  }

  public String getSemantics()
  {
    RoseNode semantics = findNodeWithKey(RoseStrings.SEMANTICS);
    return semantics == null ? null : semantics.getAttributeValue(RoseStrings.PDL);
  }

  public String getInitV()
  {
    String attributeValue = getAttributeValue(RoseStrings.INITV);
    return attributeValue;
  }

  public String getDocumentation()
  {
    String attributeValue = getAttributeValue(RoseStrings.DOCUMENTATION);
    return attributeValue;
  }

  public boolean isLoaded()
  {
    String attributeValue = getAttributeValue(RoseStrings.IS_LOADED);
    return attributeValue == null || "true".equalsIgnoreCase(attributeValue);
  }

  protected String getAttributeValue(String key)
  {
    for (RoseNode roseNode : getNodes())
    {
      if (roseNode.getRoseNodeType() == RoseNode.STRING)
      {
        String nodeKey = roseNode.getKey();
        String nodeValue = roseNode.getValue();
        if (nodeKey.equals(key))
        {
          nodeValue = dequote(nodeValue);
          return nodeValue;
        }
      }
      else if (roseNode.getRoseNodeType() == RoseNode.STRING_SEQ && roseNode.getKey().equals(key))
      {
        String separator = System.getProperty("line.separator");
        List<RoseNode> subNodes = roseNode.getNodes();
        StringBuffer result = new StringBuffer();
        for (RoseNode subNode : subNodes)
        {
          if (subNode.getRoseNodeType() == RoseNode.STRING)
          {
            if (subNode.getValue().equals(""))
            {
              result.append(separator);
            }
            else
            {
              if (result.length() != 0)
              {
                result.append(separator);
              }
              result.append(subNode.getValue());
            }
          }
        }
        return result.toString();
      }
    }

    return null;
  }

  public String getUML2MOFCorbaType()
  {
    String attributeValue = getAttributeValue("MOF", "uml2mof.corbaType");
    return attributeValue;
  }

  public boolean isTransient()
  {
    String attributeValue = getAttributeValue("Ecore", "isTransient");
    return "true".equalsIgnoreCase(attributeValue);
  }

  public boolean isVolatile()
  {
    String attributeValue = getAttributeValue("Ecore", "isVolatile");
    return "true".equalsIgnoreCase(attributeValue);
  }

  public boolean isChangeable()
  {
    String attributeValue = getAttributeValue("Ecore", "isChangeable");
    return !"false".equalsIgnoreCase(attributeValue);
  }

  public boolean isResolveProxies()
  {
    String attributeValue = getAttributeValue("Ecore", "isResolveProxies");
    return !"false".equalsIgnoreCase(attributeValue);
  }

  public boolean isUnsettable()
  {
    String attributeValue = getAttributeValue("Ecore", "isUnsettable");
    return "true".equalsIgnoreCase(attributeValue);
  }

  public boolean isID()
  {
    String attributeValue = getAttributeValue("Ecore", "isID");
    return "true".equalsIgnoreCase(attributeValue);
  }

  public boolean isUnique()
  {
    String attributeValue = getAttributeValue("Ecore", "isUnique");
    return !"false".equalsIgnoreCase(attributeValue);
  }

  public boolean isOrdered()
  {
    String attributeValue = getAttributeValue("Ecore", "isOrdered");
    return !"false".equalsIgnoreCase(attributeValue);
  }

  public String getBasePackage()
  {
    String attributeValue = getAttributeValue("Ecore", "basePackage");
    return attributeValue;
  }

  public String getPrefix()
  {
    String attributeValue = getAttributeValue("Ecore", "prefix");
    return attributeValue;
  }

  public String getPackageName()
  {
    String attributeValue = getAttributeValue("Ecore", "packageName");
    return attributeValue;
  }

  public String getClassifierName()
  {
    String attributeValue = getAttributeValue("Ecore", "classifierName");
    return attributeValue;
  }

  public String getEcoreConstraints()
  {
    String attributeValue = getAttributeValue("Ecore", "constraints");
    return attributeValue;
  }

  public String getReferenceName()
  {
    String attributeValue = getAttributeValue("Ecore", "referenceName");
    return attributeValue;
  }

  public String getAttributeName()
  {
    String attributeValue = getAttributeValue("Ecore", "attributeName");
    return attributeValue;
  }

  public String getOperationName()
  {
    String attributeValue = getAttributeValue("Ecore", "operationName");
    return attributeValue;
  }

  public String getNsPrefix()
  {
    String attributeValue = getAttributeValue("Ecore", "nsPrefix");
    if (attributeValue == null || attributeValue.length() == 0)
    {
      attributeValue = getAttributeValue("Ecore", "nsName");
    }
    return attributeValue;
  }

  public String getNsURI()
  {
    String attributeValue = getAttributeValue("Ecore", "nsURI");
    return attributeValue;
  }

  public String getXMLName()
  {
    String attributeValue = getAttributeValue("Ecore", "xmlName");
    return attributeValue;
  }

  public String getXMLNamespace()
  {
    String attributeValue = getAttributeValue("Ecore", "xmlNamespace");
    return attributeValue;
  }

  protected static final Pattern FEATURE_VALUE_PATTERN = Pattern.compile("\\s*\\(\\s*\"FeatureKind\"\\s+([0-9]+)\\s*\\)");

  public int getXMLFeatureKind()
  {
    String attributeValue = getAttributeValue("Ecore", "xmlFeatureKind");
    if (attributeValue != null)
    {
      Matcher matcher = FEATURE_VALUE_PATTERN.matcher(attributeValue);
      if (matcher.matches())
      {
        return Integer.parseInt(matcher.group(1));
      }
    }
    return 0;
  }

  protected static final Pattern CONTENT_VALUE_PATTERN = Pattern.compile("\\s*\\(\\s*\"ContentKind\"\\s+([0-9]+)\\s*\\)");

  public int getXMLContentKind()
  {
    String attributeValue = getAttributeValue("Ecore", "xmlContentKind");
    if (attributeValue != null)
    {
      Matcher matcher = CONTENT_VALUE_PATTERN.matcher(attributeValue);
      if (matcher.matches())
      {
        return Integer.parseInt(matcher.group(1));
      }
    }
    return 0;
  }

  public static final int VISIBILITY_UNSPECIFIED = 0;
  public static final int VISIBILITY_NONE = 1;
  public static final int VISIBILITY_READ_ONLY = 2;
  public static final int VISIBILITY_READ_WRITE = 3;
  public static final int VISIBILITY_READ_ONLY_UNSETTABLE = 4;
  public static final int VISIBILITY_READ_WRITE_UNSETTABLE = 5;

  protected static final Pattern VISIBILITY_VALUE_PATTERN = Pattern.compile("\\s*\\(\\s*\"VisibilityKind\"\\s+([0-9]+)\\s*\\)");

  public int getVisibility()
  {
    String attributeValue = getAttributeValue("Ecore", "visibility");
    if (attributeValue != null)
    {
      Matcher matcher = VISIBILITY_VALUE_PATTERN.matcher(attributeValue);
      if (matcher.matches())
      {
        return Integer.parseInt(matcher.group(1));
      }
    }
    return VISIBILITY_UNSPECIFIED;
  }

  public String getAnnotation()
  {
    String attributeValue = getAttributeValue("Ecore", "annotation");
    if ("".equals(attributeValue))
    {
      attributeValue = null;
    }
    return attributeValue;
  }

  /**
   * This provides backwards compatibility for the renaming of "eCore" to
   * "Ecore".
   */
  protected String getAttributeValue(String tool, String name)
  {
    String result = basicGetAttributeValue(tool, name);
    if (result == null && "Ecore".equals(tool))
    {
      result = basicGetAttributeValue("eCore", name);
    }
    return result;
  }

  protected String basicGetAttributeValue(String tool, String name)
  {
    RoseNode attributeListNode = findNodeWithKey(RoseStrings.ATTRIBUTES);

    String value = getAttributeValueInAttributeList(attributeListNode, tool, name);

    if (value == null)
    {
      value = getAttributeValueInAttributeList(getDefaultAttributeList(tool), tool, name);
    }

    return value;
  }

  private String getAttributeValueInAttributeList(RoseNode attributeListNode, String tool, String name)
  {
    if (attributeListNode != null)
    {
      List<RoseNode> attributeNodes = attributeListNode.getNodes();
      if (attributeNodes != null)
      {
        for (RoseNode attributeNode : attributeNodes)
        {
          List<RoseNode> nodes = attributeNode.getNodes();
          if (nodes != null)
          {
            String setName = "";
            String tagName = "";
            String valueName = "";
            for (RoseNode node : nodes)
            {
              if (node.getRoseNodeType() == RoseNode.STRING)
              {
                String stringV = dequote(node.getValue());
                if (node.getKey().equals(RoseStrings.TOOL))
                {
                  setName = stringV;
                }
                else if (node.getKey().equals(RoseStrings.NAME))
                {
                  tagName = stringV;
                }
                else if (node.getKey().equals(RoseStrings.VALUE))
                {
                  valueName = stringV;
                }
              }
              else if (node.getRoseNodeType() == RoseNode.STRING_SEQ)
              {
                List<RoseNode> subSubNodes = node.getNodes();
                String stringV = "";
                for (RoseNode subSubNode : subSubNodes)
                {
                  if (subSubNode.getRoseNodeType() == RoseNode.STRING)
                  {
                    if (stringV.length() > 0)
                    {
                      stringV += " ";
                    }
                    stringV += subSubNode.getValue();
                  }
                }
                if (node.getKey().equals(RoseStrings.TOOL))
                {
                  setName = stringV;
                }
                else if (node.getKey().equals(RoseStrings.NAME))
                {
                  tagName = stringV;
                }
                else if (node.getKey().equals(RoseStrings.VALUE))
                {
                  valueName = stringV;
                }
              }
              else if (node.getRoseNodeType() == RoseNode.VALUE)
              {
                List<RoseNode> stringNodes = node.getNodes();
                if (stringNodes != null && stringNodes.size() == 1)
                {
                  RoseNode stringNode = stringNodes.get(0);
                  String stringV = "";
                  if (stringNode.getRoseNodeType() == RoseNode.STRING)
                  {
                    stringV = stringNode.getValue();
                  }
                  else if (stringNode.getRoseNodeType() == RoseNode.STRING_SEQ)
                  {
                    for (RoseNode subSubNode : stringNode.getNodes())
                    {
                      if (subSubNode.getRoseNodeType() == RoseNode.STRING)
                      {
                        if (stringV.length() > 0)
                        {
                          stringV += " ";
                        }
                        stringV += subSubNode.getValue();
                      }
                    }
                  }
                  stringV = dequote(stringV);
                  if (node.getKey().equals(RoseStrings.TOOL))
                  {
                    setName = stringV;
                  }
                  else if (node.getKey().equals(RoseStrings.NAME))
                  {
                    tagName = stringV;
                  }
                  else if (node.getKey().equals(RoseStrings.VALUE))
                  {
                    valueName = stringV;
                  }
                }
              }
            }
            if (setName.equals(tool) && tagName.equals(name))
            {
              return valueName;
            }
          }
        }
      }
    }
    return null;
  }

  private RoseNode getDefaultAttributeList(String tool)
  {
    RoseNode attributeListNode = null;
    RoseNode rootNode = getRoot();
    String rootNodeValue = rootNode.getValue();
    String rootObjectType = Util.getType(rootNodeValue);

    if (rootObjectType.equals(RoseStrings.DESIGN))
    {
      String objectType = Util.getType(getValue());
      RoseNode defaultProperties = rootNode.findNodeWithKey(RoseStrings.PROPERTIES);
      if (defaultProperties != null)
      {
        RoseNode attributes = defaultProperties.findNodeWithKey(RoseStrings.ATTRIBUTES);
        if (attributes != null)
        {
          for (RoseNode attribute : attributes.getNodes())
          {
            RoseNode toolNode = attribute.findNodeWithKey(RoseStrings.TOOL);
            if (toolNode != null && Util.trimQuotes(toolNode.getValue()).equals(tool))
            {
              RoseNode nameNode = attribute.findNodeWithKey(RoseStrings.NAME);
              String theName = Util.trimQuotes(nameNode.getValue());
              if (objectType.equals(RoseStrings.CLASSATTRIBUTE) && theName.equals(RoseStrings.DEFAULT_ATTRIBUTE))
              {
                attributeListNode = attribute.findNodeWithKey(RoseStrings.VALUE);
                break;
              }
              else if (objectType.equals(RoseStrings.CLASS) && theName.equals(RoseStrings.DEFAULT_CLASS))
              {
                attributeListNode = attribute.findNodeWithKey(RoseStrings.VALUE);
                break;
              }
              else if (objectType.equals(RoseStrings.CLASS_CATEGORY) && theName.equals(RoseStrings.DEFAULT_CATEGORY))
              {
                attributeListNode = attribute.findNodeWithKey(RoseStrings.VALUE);
                break;
              }
              else if (objectType.equals(RoseStrings.OPERATION) && theName.equals(RoseStrings.DEFAULT_OPERATION))
              {
                attributeListNode = attribute.findNodeWithKey(RoseStrings.VALUE);
                break;
              }
              else if (objectType.equals(RoseStrings.ROLE) && theName.equals(RoseStrings.DEFAULT_ROLE))
              {
                attributeListNode = attribute.findNodeWithKey(RoseStrings.VALUE);
                break;
              }
            }
          }
        }
      }
    }
    return attributeListNode;
  }

  public String getRoleMultiplicity()
  {
    List<RoseNode> nodes = getNodes();
    for (int i = 0; i < nodes.size(); i++)
    {
      RoseNode node = nodes.get(i);
      if (node.getRoseNodeType() == RoseNode.VALUE)
      {
        String objKey = node.getKey();
        String objType = Util.getType(node.getValue());
        if (objKey.equals(RoseStrings.CLIENT_CARDINALITY) && objType.equals(RoseStrings.CARDINALITY))
        {
          List<RoseNode> subNodes = node.getNodes();
          String value = "";
          for (int j = 0; j < subNodes.size(); j++)
          {
            // size should be 1
            RoseNode subNode = subNodes.get(j);
            if (subNode.getRoseNodeType() == RoseNode.STRING)
            {
              value = subNode.getValue();
              value = value.substring(1, value.length() - 1);
            }
            else if (subNode.getRoseNodeType() == RoseNode.STRING_SEQ)
            {
              // could this happen?
              List<RoseNode> subSubNodes = subNode.getNodes();
              for (int k = 0; k < subSubNodes.size(); k++)
              {
                RoseNode subSubNode = subSubNodes.get(k);
                if (subSubNode.getRoseNodeType() == RoseNode.STRING)
                  value = subSubNode.getValue() + "|#" + value;
              }
            }
          }
          return value;
        }
      }
    }
    return null;
  }

  protected static String dequote(String s)
  {
    if (s != null && s.length() >= 2 && s.charAt(0) == '\"' && s.charAt(s.length() - 1) == '\"')
    {
      s = s.substring(1, s.length() - 1);
      for (int i = s.indexOf("\\\\"); i != -1; i = s.indexOf("\\\\"))
      {
        s = s.substring(0, i) + s.substring(i + 1, s.length());
      }
    }
    return s;
  }
}
