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
 *
 * $Id: JETSkeleton.java,v 1.10 2007/05/15 22:32:34 emerks Exp $
 */
package org.eclipse.emf.codegen.jet;


import java.util.Iterator;
import java.util.List;
import java.util.StringTokenizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.eclipse.jdt.core.jdom.DOMFactory;
import org.eclipse.jdt.core.jdom.IDOMCompilationUnit;
import org.eclipse.jdt.core.jdom.IDOMMethod;
import org.eclipse.jdt.core.jdom.IDOMNode;


/**
 */
@SuppressWarnings("deprecation")
public class JETSkeleton 
{
  protected final String NL = System.getProperties().getProperty("line.separator");

  protected final String SKELETON_COMPILATION_UNIT = 
    "public class CLASS" + NL + "{" + NL + "  public String generate(Object argument)" + NL + "  {" + NL + "    return \"\";" + NL + "  }" + NL + "}" + NL;

  protected final String STATIC_NL_DECLARATION = "  protected static String nl;" + NL;
  protected final String CREATE_METHOD_DECLARATION_HEAD = "  public static synchronized ";
  protected final String CREATE_METHOD_DECLARATION_MIDDLE = " create(String lineSeparator)" + NL + "  {" + NL + "    nl = lineSeparator;" + NL + "    ";
  protected final String CREATE_METHOD_DECLARATION_MIDDLE2 = " result = new ";
  protected final String CREATE_METHOD_DECLARATION_TAIL = "();" + NL + "    nl = null;" + NL + "    return result;" + NL + "  }" + NL + NL;
  
  protected final String NL_DECLARATION = "  public final String NL = nl == null ? (";
  protected final String NL_DECLARATION_TAIL = ") : nl;" + NL;
  protected final String STRING_BUFFER_DECLARATION = "    final StringBuffer stringBuffer = new StringBuffer();" + NL;
  protected final String STRING_BUFFER_RETURN = "    return stringBuffer.toString();" + NL;

  protected DOMFactory jdomFactory = new DOMFactory();
  protected IDOMCompilationUnit compilationUnit;
  protected String nlString = "System.getProperties().getProperty(\"line.separator\")";

  /**
   */
  public JETSkeleton() 
  {
    compilationUnit = jdomFactory.createCompilationUnit(SKELETON_COMPILATION_UNIT, "CLASS");
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
        insertionNode.insertSibling
          (jdomFactory.createMethod
              (CREATE_METHOD_DECLARATION_HEAD + name + 
                  CREATE_METHOD_DECLARATION_MIDDLE + name + 
                  CREATE_METHOD_DECLARATION_MIDDLE2 + name + 
                  CREATE_METHOD_DECLARATION_TAIL));
        insertionNode.insertSibling(jdomFactory.createField(NL_DECLARATION + getNLString() + NL_DECLARATION_TAIL));
        for (Iterator<String> i = constants.iterator(); i.hasNext(); )
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
      StringBuffer body = new StringBuffer();
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
        return value.replaceAll(nl, NL);
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
        for (StringTokenizer stringTokenizer = new StringTokenizer(importList, " \t\n\r"); stringTokenizer.hasMoreTokens(); )
        {
          String token = stringTokenizer.nextToken();
          String newImport = "import " + token + ";" + NL;
          if (!stringTokenizer.hasMoreTokens())
          {
            newImport += NL;
          }
          node.insertSibling(jdomFactory.createImport(newImport));
        }
        return;
      }
    }
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
}
