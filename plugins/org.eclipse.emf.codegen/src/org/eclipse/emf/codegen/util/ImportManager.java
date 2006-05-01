/**
 * <copyright>
 *
 * Copyright (c) 2005 IBM Corporation and others.
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
 * ImportManager.java,v 1.3 2005/10/27 15:24:24 davidms Exp
 */
package org.eclipse.emf.codegen.util;

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.emf.common.EMFPlugin;
import org.eclipse.jdt.core.dom.AST;
import org.eclipse.jdt.core.dom.ASTParser;
import org.eclipse.jdt.core.dom.CompilationUnit;
import org.eclipse.jdt.core.dom.ImportDeclaration;

public class ImportManager
{
  protected SortedSet imports = new TreeSet();
  protected HashMap shortNameToImportMap = new HashMap();
  protected HashSet javaLangImports = null;
  protected HashSet importedPackages;

  public ImportManager(String compilationUnitPackage)
  {
    importedPackages = new HashSet();
    importedPackages.add(compilationUnitPackage);
  }
  
  public Collection getImports()
  {
    return imports;
  }

  public String getImportedName(String qualifiedName)
  {
    String indices = "";
    int firstBracket = qualifiedName.indexOf("[");
    if (firstBracket != -1) 
    {
      indices = qualifiedName.substring(firstBracket);
      qualifiedName = qualifiedName.substring(0, firstBracket);
    }

    String baseName = qualifiedName.substring(qualifiedName.lastIndexOf(".") + 1);

    String shortName = baseName;
    int firstDollar = shortName.indexOf("$");
    if (firstDollar != -1) 
    {
      shortName = shortName.substring(0, firstDollar);
    }

    String registeredName = (String)shortNameToImportMap.get(shortName);
    if (registeredName == null)
    {
      registeredName = "java.lang." + shortName;
      if (qualifiedName.equals(registeredName))
      {
        if (javaLangImports != null && javaLangImports.contains(shortName))
        {
          imports.add(qualifiedName);
        }
        return shortName + indices;
      }
      else
      {
        return qualifiedName + indices;
      }
    }
    else
    {
      if (qualifiedName.startsWith(registeredName))
      {
        if (qualifiedName.length () == registeredName.length())
        {
          return baseName.replace('$', '.') + indices;
        }
        else
        {
          char character = qualifiedName.charAt(registeredName.length());
          if (character == '.' || character == '$')
          {
            return baseName.replace('$', '.') + indices;
          }
        }
      }
      return qualifiedName.replace('$', '.') + indices;
    }
  }

  public void addImport(String packageName, String shortName)
  {
    int firstBracket = shortName.indexOf("[");
    if (firstBracket != -1) shortName = shortName.substring(0, firstBracket);
    basicAdd(packageName, shortName, packageName + "." + shortName);
  }

  public void addImport(String qualifiedName)
  {
    int firstBracket = qualifiedName.indexOf("[");
    if (firstBracket != -1) qualifiedName = qualifiedName.substring(0, firstBracket);

    int lastDot = qualifiedName.lastIndexOf(".");
    String shortName = qualifiedName.substring(lastDot + 1);
    int firstDollar = shortName.indexOf("$");
    if (firstDollar != -1) 
    {
      shortName = shortName.substring(0, firstDollar);
    }

    String packageName = lastDot == -1 ? null : qualifiedName.substring(0, lastDot);
    basicAdd(packageName, shortName, qualifiedName);
  }

  public void addMasterImport(String packageName, String shortName)
  {
    shortNameToImportMap.put(shortName, packageName + "." + shortName);
  }

  public void addJavaLangImports(List javaLangClassNames)
  {
    if (!javaLangClassNames.isEmpty())
    {
      javaLangImports = new HashSet();
      javaLangImports.addAll(javaLangClassNames);
    }
  }

  public boolean hasImport(String shortName)
  {
    return shortNameToImportMap.containsKey(shortName);
  }

  public void addCompilationUnitImports(String compilationUnitContents)
  {   
    if (EMFPlugin.IS_ECLIPSE_RUNNING)
    {
      EclipseHelper.addCompilationUnitImports(importedPackages, shortNameToImportMap, compilationUnitContents);
    }
    else
    {
      Pattern importPattern = Pattern.compile("import\\s+([^\\s;]*);\\s*", Pattern.MULTILINE | Pattern.DOTALL);
      Matcher matcher = importPattern.matcher(compilationUnitContents);
      while (matcher.find())
      {
        String qualifiedName = matcher.group(1);
        int lastDot = qualifiedName.lastIndexOf(".");
        String shortName = qualifiedName.substring(lastDot + 1);
        if (shortName.equals("*"))
        {
          String packageName = qualifiedName.substring(0, lastDot);
          importedPackages.add(packageName);
        }
        else
        {
          shortNameToImportMap.put(shortName, qualifiedName);
        }
      }
    }
  }
  
  private static class EclipseHelper
  {
    public static void addCompilationUnitImports(Set importedPackages, Map shortNameToImportMap, String compilationUnitContents)
    {   
      ASTParser parser = ASTParser.newParser(AST.JLS3);
      parser.setSource(compilationUnitContents.toCharArray());
      CompilationUnit compilationUnit = (CompilationUnit)parser.createAST(new NullProgressMonitor());
      for (Iterator i = compilationUnit.imports().iterator(); i.hasNext();)
      {
        ImportDeclaration importDeclaration = (ImportDeclaration)i.next();
        String qualifiedName = importDeclaration.getName().getFullyQualifiedName();
        int lastDot = qualifiedName.lastIndexOf(".");
        String shortName = qualifiedName.substring(lastDot + 1);
        if (shortName.equals("*"))
        {
          String packageName = qualifiedName.substring(0, lastDot);
          importedPackages.add(packageName);
        }
        else
        {
          shortNameToImportMap.put(shortName, qualifiedName);
        }
      }
    }
  }

  public void addPseudoImport(String qualifiedName)
  {
    int lastDot = qualifiedName.lastIndexOf(".");
    String shortName = qualifiedName.substring(lastDot + 1);
    if (shortName.equals("*"))
    {
      String packageName = qualifiedName.substring(0, lastDot);
      importedPackages.add(packageName);
    }
    else
    {
      shortNameToImportMap.put(shortName, qualifiedName);
    }
  }

  private void basicAdd(String packageName, String shortName, String qualifiedName)
  {
    if (shortName.equals("*"))
    {
      importedPackages.add(packageName);
      imports.add(qualifiedName);
    }
    else if (!shortNameToImportMap.containsKey(shortName) && (!CodeGenUtil.isJavaDefaultType(shortName)))
    {
      shortNameToImportMap.put(shortName, qualifiedName);

      if (!importedPackages.contains(packageName))
      {
        imports.add(qualifiedName);
      }
    }
  }

  public String computeSortedImports()
  {
    String NL = System.getProperties().getProperty("line.separator");
    StringBuffer imports = new StringBuffer();

    String previousPackageName = null;
    for (Iterator iter = getImports().iterator(); iter.hasNext(); )
    {
      String importName = (String)iter.next();
      int index = importName.lastIndexOf(".");
      if (index != -1)
      {
        String packageName = importName.substring(0, index);
        if (previousPackageName != null && !previousPackageName.equals(packageName))
        {
          imports.append(NL);
        }
        previousPackageName = packageName;
      }
      imports.append(NL + "import " + importName + ";");
    }

    return imports.toString();
  }  
}