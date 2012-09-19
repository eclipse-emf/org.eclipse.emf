/**
 * Copyright (c) 2005-2009 IBM Corporation and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: 
 *   IBM - Initial API and implementation
 */
package org.eclipse.emf.test.tools;

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
import org.eclipse.jdt.core.dom.ASTParser;
import org.eclipse.jdt.core.dom.CompilationUnit;
import org.eclipse.jdt.core.dom.ImportDeclaration;

import org.eclipse.emf.codegen.util.CodeGenUtil;
import org.eclipse.emf.common.EMFPlugin;

/**
 * The ImportManager implementation from EMF 2.4.
 */
class OldImportManager
{
  protected SortedSet<String> imports = new TreeSet<String>();
  protected HashMap<String, String> shortNameToImportMap = new HashMap<String, String>();
  protected HashSet<String> javaLangImports = null;
  protected HashSet<String> importedPackages;

  public OldImportManager(String compilationUnitPackage)
  {
    importedPackages = new HashSet<String>();
    importedPackages.add(compilationUnitPackage);
  }

  public Collection<String> getImports()
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

    String registeredName = shortNameToImportMap.get(shortName);
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

  public void addJavaLangImports(List<String> javaLangClassNames)
  {
    if (!javaLangClassNames.isEmpty())
    {
      javaLangImports = new HashSet<String>();
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
    public static void addCompilationUnitImports
      (Set<String> importedPackages, Map<String, String> shortNameToImportMap, String compilationUnitContents)
    {   
      ASTParser parser = CodeGenUtil.EclipseUtil.newASTParser();
      parser.setSource(compilationUnitContents.toCharArray());
      CompilationUnit compilationUnit = (CompilationUnit)parser.createAST(new NullProgressMonitor());
      for (Iterator<?> i = compilationUnit.imports().iterator(); i.hasNext();)
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
    for (String importName : getImports())
    {
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

/**
 * Extension of the old ImportManager implementation that adds new APIs moved from GenModel in EMF 2.5.
 * This class would exhibit exactly the same behavior as the new ImportManager, but for numerous bugs in the old implementation.
 */
public class ImportManager extends OldImportManager
{
  private String lineDelimiter;
  private StringBuilder importStringBuilder;
  private StringBuffer importStringBuffer;
  private int importInsertionPoint;

  public ImportManager(String compilationUnitPackage, String compilationUnitShortName)
  {
    this(compilationUnitPackage);
    addMasterImport(compilationUnitPackage, compilationUnitShortName);
  }

  public ImportManager(String compilationUnitPackage)
  {
    super(compilationUnitPackage);
  }

  public String getImportedName(String qualifiedName, boolean autoImport)
  {
    int index = qualifiedName.indexOf("<");
    if (index >= 0)
    {
      String baseName = qualifiedName.substring(0, index);
      StringBuilder result = new StringBuilder(getImportedName(baseName, autoImport));
      result.append("<");
      for (int start = ++index,  end = qualifiedName.lastIndexOf(">") +  1; index < end; ++index)
      {
        char character = qualifiedName.charAt(index);
        switch (character)
        {
          case ' ': 
          case ',': 
          case '<': 
          case '>': 
          case '&': 
          {
            if (start != index)
            {
              String segment = qualifiedName.substring(start, index);
              result.append(getImportedName(segment, autoImport));
            }
            result.append(character);
            start = index + 1;
            break;
          }
          default:
          {
            break;
          }
        }
      }
      return result.toString();
    }
   
    index = qualifiedName.indexOf("$");
    String baseName = index == -1 ? qualifiedName : qualifiedName.substring(0, index);
    if (baseName.contains("."))
    {
      addImport(index == -1 ? qualifiedName : qualifiedName.substring(0, index));
      return super.getImportedName(qualifiedName);
    }
    else
    {
      return qualifiedName;
    }
  }

  public String getLineDelimiter()
  {
    return lineDelimiter == null ? System.getProperty("line.separator") : lineDelimiter;
  }

  public void setLineDelimiter(String lineDelimiter)
  {
    this.lineDelimiter = lineDelimiter;
  }

  public void markImportLocation(StringBuilder stringBuilder)
  {
    importStringBuffer = null;
    importStringBuilder = stringBuilder;
    importInsertionPoint = stringBuilder.length();
    addCompilationUnitImports(stringBuilder.toString());
  }

  public void markImportLocation(StringBuffer stringBuffer)
  {
    importStringBuilder = null;
    importStringBuffer = stringBuffer;
    importInsertionPoint = stringBuffer.length();
    addCompilationUnitImports(stringBuffer.toString());
  }

  public void emitSortedImports()
  {
    if (importStringBuilder != null)
    {
      importStringBuilder.insert(importInsertionPoint, computeSortedImports());
    }
    else if (importStringBuffer != null)
    {
      importStringBuffer.insert(importInsertionPoint, computeSortedImports());
    }
  }
}
