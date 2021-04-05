/**
 * Copyright (c) 2002-2006 IBM Corporation and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 *
 * Contributors:
 *   IBM - Initial API and implementation
 */
package org.eclipse.emf.codegen.jet;


import java.util.Iterator;
import java.util.List;
import java.util.StringTokenizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.eclipse.jdt.core.jdom.DOMFactory;
import org.eclipse.jdt.core.jdom.IDOMCompilationUnit;
import org.eclipse.jdt.core.jdom.IDOMImport;
import org.eclipse.jdt.core.jdom.IDOMMethod;
import org.eclipse.jdt.core.jdom.IDOMNode;


/**
 */
@SuppressWarnings("deprecation")
public class JETSkeleton
{
  private static final Pattern BUILDER_NAME_PATTERN = Pattern.compile("[^\n\r]*\\b+(\\p{Alpha}\\p{Alnum}*)+[ \t]*=[^\n\r]+");

  protected final String NL;

  protected final String SKELETON_COMPILATION_UNIT;

  protected final String STATIC_NL_DECLARATION;

  protected final String CREATE_METHOD_DECLARATION_HEAD;

  protected final String CREATE_METHOD_DECLARATION_MIDDLE;

  protected final String CREATE_METHOD_DECLARATION_MIDDLE2;

  protected final String CREATE_METHOD_DECLARATION_TAIL;

  protected final String NL_DECLARATION;

  protected final String NL_DECLARATION_TAIL;

  protected final String STRING_BUFFER_DECLARATION;

  protected final String STRING_BUFFER_RETURN;

  protected DOMFactory jdomFactory = new DOMFactory();

  protected IDOMCompilationUnit compilationUnit;

  protected String nlString;

  /**
   * @since 2.19
   */
  protected String builder;

  /**
   * @since 2.19
   */
  protected String builderName;

  /**
   */
  public JETSkeleton()
  {
    this(System.getProperties().getProperty("line.separator"), null);
  }

  /**
   * @since 2.19
   */
  public JETSkeleton(String lineSeparator, String builder)
  {
    NL = lineSeparator == null ? "System.getProperties().getProperty(\"line.separator\")" : lineSeparator;

    this.builder = builder == null ? "StringBuffer stringBuffer = new StringBuffer();" : builder.contains(";") ? builder.trim() : builder.trim() + ";";
    this.builderName = getBuilderName(this.builder);
    if (this.builderName == null)
    {
      this.builder = "StringBuffer stringBuffer = new StringBuffer();";
      this.builderName = "stringBuffer";
    }

    SKELETON_COMPILATION_UNIT = "public class CLASS" + NL + "{" + NL + "  public String generate(Object argument)" + NL + "  {" + NL + "    return \"\";" + NL + "  }" + NL + "}"
        + NL;

    STATIC_NL_DECLARATION = "  protected static String nl;" + NL;

    CREATE_METHOD_DECLARATION_HEAD = "  public static synchronized ";

    CREATE_METHOD_DECLARATION_MIDDLE = " create(String lineSeparator)" + NL + "  {" + NL + "    nl = lineSeparator;" + NL + "    ";

    CREATE_METHOD_DECLARATION_MIDDLE2 = " result = new ";

    CREATE_METHOD_DECLARATION_TAIL = "();" + NL + "    nl = null;" + NL + "    return result;" + NL + "  }" + NL + NL;

    NL_DECLARATION = "  public final String NL = nl == null ? (";

    NL_DECLARATION_TAIL = ") : nl;" + NL;

    STRING_BUFFER_DECLARATION = "    final " + this.builder + NL;

    STRING_BUFFER_RETURN = "    return " + this.builderName + ".toString();" + NL;

    compilationUnit = jdomFactory.createCompilationUnit(SKELETON_COMPILATION_UNIT, "CLASS");

    nlString = "System.getProperties().getProperty(\"line.separator\")";
  }

  /**
   * @since 2.19
   */
  public static String getBuilderName(String builder)
  {
    if (builder == null)
    {
      return null;
    }
    else
    {
      Matcher matcher = BUILDER_NAME_PATTERN.matcher(builder);
      return matcher.matches() ? matcher.group(1) : null;
    }
  }

  /**
   * @since 2.19
   */
  public String getBuilder()
  {
    return builder;
  }

  /**
   * @since 2.19
   */
  public String getBuilderName()
  {
    return builderName;
  }

  public String getCompilationUnitContents()
  {
    return compilationUnit.getContents();
  }

  public IDOMCompilationUnit getCompilationUnit()
  {
    return compilationUnit;
  }

  public void setCompilationUnitContents(String contents)
  {
    compilationUnit = jdomFactory.createCompilationUnit(convertLineFeed(contents), "CLASS");
  }

  /**
   * @since 2.19
   */
  public boolean isWellFormed()
  {
    return getClassName() != null && getLastMethod() != null;
  }

  public String getNLString()
  {
    return nlString;
  }

  public void setNLString(String nlString)
  {
    this.nlString = nlString;
  }

  public String getPackageName()
  {
    for (IDOMNode node = compilationUnit.getFirstChild(); node != null; node = node.getNextNode())
    {
      if (node.getNodeType() == IDOMNode.PACKAGE)
      {
        return node.getName();
      }
    }

    return "";
  }

  public void setPackageName(String packageName)
  {
    for (IDOMNode node = compilationUnit.getFirstChild(); node != null; node = node.getNextNode())
    {
      if (node.getNodeType() == IDOMNode.PACKAGE)
      {
        node.setName(packageName);
        return;
      }
    }

    compilationUnit.getFirstChild().insertSibling(jdomFactory.createPackage("package " + packageName + ";" + NL + NL));
  }

  public void setConstants(List<String> constants)
  {
    for (IDOMNode node = compilationUnit.getFirstChild(); node != null; node = node.getNextNode())
    {
      if (node.getNodeType() == IDOMNode.TYPE)
      {
        String name = node.getName();
        IDOMNode insertionNode = node.getFirstChild();
        insertionNode.insertSibling(jdomFactory.createField(STATIC_NL_DECLARATION));
        insertionNode.insertSibling(
          jdomFactory.createMethod(
            CREATE_METHOD_DECLARATION_HEAD + name + CREATE_METHOD_DECLARATION_MIDDLE + name + CREATE_METHOD_DECLARATION_MIDDLE2 + name + CREATE_METHOD_DECLARATION_TAIL));
        insertionNode.insertSibling(jdomFactory.createField(NL_DECLARATION + getNLString() + NL_DECLARATION_TAIL));
        for (Iterator<String> i = constants.iterator(); i.hasNext();)
        {
          String constant = "  " + i.next() + NL;
          if (!i.hasNext())
          {
            constant += NL;
          }
          insertionNode.insertSibling(jdomFactory.createField(constant));
        }
        break;
      }
    }
  }

  public void setBody(List<String> lines)
  {
    IDOMMethod method = getLastMethod();
    if (method != null)
    {
      StringBuilder body = new StringBuilder();
      body.append(NL + "  {" + NL);
      body.append(STRING_BUFFER_DECLARATION);
      for (String line : lines)
      {
        body.append("    ");
        body.append(convertLineFeed(line));
        body.append(NL);
      }
      body.append(STRING_BUFFER_RETURN);
      body.append("  }" + NL);

      method.setBody(body.toString());
    }
  }

  protected static final Pattern NL_PATTERN = Pattern.compile("([\\n][\\r]?|[\\r][\\n]?)", Pattern.MULTILINE);

  public String convertLineFeed(String value)
  {
    Matcher matcher = NL_PATTERN.matcher(value);
    if (matcher.find())
    {
      String nl = matcher.group(1);
      if (!NL.equals(nl))
      {
        return value.replace(nl, NL);
      }
    }

    return value;
  }

  public String getMethodName()
  {
    IDOMMethod method = getLastMethod();
    if (method != null)
    {
      return method.getName();
    }
    else
    {
      return "";
    }
  }

  public void addImports(String importList)
  {
    for (IDOMNode node = compilationUnit.getFirstChild(); node != null; node = node.getNextNode())
    {
      if (node.getNodeType() == IDOMNode.TYPE)
      {
        for (StringTokenizer stringTokenizer = new StringTokenizer(importList, " \t\n\r"); stringTokenizer.hasMoreTokens();)
        {
          String token = stringTokenizer.nextToken();
          String newImport = "import " + token + ";" + NL;
          if (!stringTokenizer.hasMoreTokens())
          {
            newImport += NL;
          }
          IDOMImport imports = jdomFactory.createImport(newImport);
          if (imports != null)
          {
            node.insertSibling(imports);
          }
        }
        return;
      }
    }
  }

  /**
   * @since 2.19
   */
  public String getQualifiedClassName()
  {
    String packageName = getPackageName();
    String className = getClassName();
    return packageName.length() == 0 ? className : packageName + '.' + className;
  }

  public String getClassName()
  {
    for (IDOMNode node = compilationUnit.getFirstChild(); node != null; node = node.getNextNode())
    {
      if (node.getNodeType() == IDOMNode.TYPE)
      {
        return node.getName();
      }
    }

    return null;
  }

  public void setClassName(String className)
  {
    for (IDOMNode node = compilationUnit.getFirstChild(); node != null; node = node.getNextNode())
    {
      if (node.getNodeType() == IDOMNode.TYPE)
      {
        node.setName(className);
      }
    }
  }

  /**
   * @since 2.19
   */
  public void setHeader(String header)
  {
    StringBuilder headerContent = new StringBuilder();
    headerContent.append("/*");
    headerContent.append(NL);
    for (String line : header.split("\r?\n"))
    {
      headerContent.append(" * ");
      headerContent.append(line);
      headerContent.append(NL);
    }
    headerContent.append(" */");
    headerContent.append(NL);
    compilationUnit.setHeader(headerContent.toString());
  }

  protected IDOMMethod getLastMethod()
  {
    IDOMMethod method = null;
    for (IDOMNode node = compilationUnit.getFirstChild(); node != null; node = node.getNextNode())
    {
      if (node.getNodeType() == IDOMNode.TYPE)
      {
        for (IDOMNode child = node.getFirstChild(); child != null; child = child.getNextNode())
        {
          if (child.getNodeType() == IDOMNode.METHOD)
          {
            method = (IDOMMethod)child;
          }
        }
      }
    }
    return method;
  }

  /**
   * @since 2.19
   */
  public JETCompilationUnit getCompilerResult(List<String> templateURIs, List<JETGenerator> generators, List<JETItem> jetItems, List<JETException> problems)
  {
    String compilationUnit = getCompilationUnitContents();
    String packageName = getPackageName();
    String className = getClassName();
    String qualifiedName = packageName.length() == 0 ? className : packageName + "." + className;
    JETCompilationUnit result = new JETCompilationUnit(templateURIs, qualifiedName, compilationUnit, jetItems, problems);

    JETItem jetItem = jetItems.get(0);
    if (jetItem instanceof JETCommentItem)
    {
      JETCommentItem jetCommentItem = (JETCommentItem)jetItem;
      List<JETSubItem> lineItems = jetCommentItem.getLineItems();
      int offset = 0;
      for (JETSubItem lineItem : lineItems)
      {
        Matcher matcher = Pattern.compile(" * (" + Pattern.quote(lineItem.getText()) + ")").matcher(compilationUnit);
        if (matcher.find(offset))
        {
          offset = matcher.end() + 1;
          int start = matcher.start(1);
          int end = matcher.end(1);
          result.addRange(lineItem.getStart(), lineItem.getStop(), start, end - start, lineItem);
        }
      }
    }

    JETDirectiveItem jetJETDirectiveItem = result.getJETJETDirectiveItem();
    if (jetJETDirectiveItem != null)
    {
      JETAttributeItem packageAttribute = jetJETDirectiveItem.getAttribute("package");
      if (packageAttribute != null)
      {
        JETTokenItem valueToken = packageAttribute.getValueToken();
        JETValueItem valueItem = valueToken.getValueItem();
        Matcher matcher = Pattern.compile("package (" + Pattern.quote(valueItem.getValue()) + ");").matcher(compilationUnit);
        if (matcher.find())
        {
          int start = matcher.start(1);
          int end = matcher.end(1);
          result.addRange(valueItem.getStart(), valueItem.getStop(), start, end - start, valueItem);
        }
      }

      JETAttributeItem importsAttribute = jetJETDirectiveItem.getAttribute("imports");
      if (importsAttribute != null)
      {
        JETTokenItem valueToken = importsAttribute.getValueToken();
        JETValueItem valueItem = valueToken.getValueItem();
        List<JETValueElementItem> elements = valueItem.getElements();
        for (JETValueElementItem jetValueElementItem : elements)
        {
          Matcher matcher = Pattern.compile("import (" + Pattern.quote(jetValueElementItem.getValue()) + ");").matcher(compilationUnit);
          if (matcher.find())
          {
            int start = matcher.start(1);
            int end = matcher.end(1);
            result.addRange(jetValueElementItem.getStart(), jetValueElementItem.getStop(), start, end - start, valueItem);
          }
        }
      }

      JETAttributeItem classAttribute = jetJETDirectiveItem.getAttribute("class");
      if (classAttribute != null)
      {
        JETTokenItem valueToken = classAttribute.getValueToken();
        JETValueItem valueItem = valueToken.getValueItem();
        Matcher matcher = Pattern.compile("^[ \t]*(?:public|private|protected)?[ \t]*class (" + Pattern.quote(valueItem.getValue()) + ")[ \t{]*$", Pattern.MULTILINE).matcher(
          compilationUnit);
        // }
        if (matcher.find())
        {
          int start = matcher.start(1);
          int end = matcher.end(1);
          result.addRange(valueItem.getStart(), valueItem.getStop(), start, end - start, valueItem);
        }
      }
    }

    int nlDeclarationstart = compilationUnit.indexOf(NL_DECLARATION);
    if (jetJETDirectiveItem != null)
    {
      JETAttributeItem nlStringAttribute = jetJETDirectiveItem.getAttribute("nlString");
      if (nlStringAttribute != null)
      {
        JETTokenItem valueToken = nlStringAttribute.getValueToken();
        JETValueItem valueItem = valueToken.getValueItem();
        result.addRange(valueItem.getStart(), valueItem.getStop(), nlDeclarationstart + NL_DECLARATION.length(), valueItem.getText().length(), valueItem);
      }
    }

    int builderStart = compilationUnit.indexOf(STRING_BUFFER_DECLARATION, nlDeclarationstart);
    int offset = builderStart + STRING_BUFFER_DECLARATION.length();
    if (jetJETDirectiveItem != null)
    {
      JETAttributeItem builderAttribute = jetJETDirectiveItem.getAttribute("builder");
      if (builderAttribute != null)
      {
        JETTokenItem valueToken = builderAttribute.getValueToken();
        JETValueItem valueItem = valueToken.getValueItem();
        result.addRange(valueItem.getStart(), valueItem.getStop(), builderStart + "    final ".length(), valueItem.getText().length(), valueItem);
      }
    }

    for (JETGenerator generator : generators)
    {
      offset += 4; // For the inserted indentation.
      if (generator instanceof JETJavaGenerator)
      {
        JETJavaGenerator javaGenerator = (JETJavaGenerator)generator;
        JETMark start = javaGenerator.getStart();
        JETMark stop = javaGenerator.getStop();
        int relativeJavaOffset = javaGenerator.getRelativeJavaOffset();
        int javaLength = javaGenerator.getJavaLength();

        offset += relativeJavaOffset;
        JETJavaItem javaItem = javaGenerator.getJavaItem();
        result.addRange(start, stop, offset, javaLength, javaItem);
        if (javaItem != null)
        {
          javaItem.setJavaOffset(offset);
          javaItem.setJavaLength(javaLength);
        }

        offset += javaLength;
      }

      offset = compilationUnit.indexOf('\n', offset) + 1;
    }

    return result;
  }
}
