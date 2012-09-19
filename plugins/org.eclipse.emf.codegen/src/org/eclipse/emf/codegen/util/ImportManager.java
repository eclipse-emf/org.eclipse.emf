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
package org.eclipse.emf.codegen.util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.eclipse.core.runtime.NullProgressMonitor;

import org.eclipse.emf.common.EMFPlugin;
import org.eclipse.jdt.core.dom.ASTParser;
import org.eclipse.jdt.core.dom.CompilationUnit;
import org.eclipse.jdt.core.dom.ImportDeclaration;

/**
 * A manager for import declarations in generated code.
 * 
 * <p>An instance of <code>ImportManager</code> should be created for each compilation unit being generated. It
 * maintains the list of imports to be added to the compilation unit and associates registered <b>short names</b> with
 * their corresponding <b>qualified names</b>.
 * 
 * <p>Common usage of <code>ImportManager</code> is very simple. This example assumes that a <code>StringBuilder</code>
 * is being used to accumulate the text for a generated source file:
 * 
 * <pre>
 * StringBuilder result = new StringBuilder();
 * ImportManager importManager = new ImportManager("com.example.target", "Target");
 * ...
 * importManager.markImportLocation(result);
 * ...
 * result.append(importManager.getImportedName("com.example.lib.Example", true));
 * ...
 * result.append(importManager.getImportedName("java.lang.String", true));
 * ...
 * result.append(importManager.getImportedName("java.util.Arrays", true));
 * ...
 * result.append(importManager.getImportedName("org.test.Example", true));
 * ...
 * result.append(importManager.getImportedName("org.test.Target", true));
 * ...
 * result.append(importManager.getImportedName("com.example.target.Helper", true));
 * ...
 * importManager.emitSortedImports();</pre>
 * 
 * <p>The constructor is passed the package name and short name for the compilation unit being generated. The point
 * where the imports should be inserted is marked by calling <code>markImportLocation</code>. Then,
 * <code>getImportedName()</code> is called each time a type name is needed, to determine the correct name to use.
 * Passing <code>true</code> as the second argument instructs the import manager to automatically import the specified
 * type, if possible.
 * 
 * <p>In this case, the following names would be returned:
 * 
 * <ul>
 * <li><code>Example</code>
 * <li><code>String</code>
 * <li><code>Arrays</code>
 * <li><code>org.test.Example</code>
 * <li><code>org.test.Target</code>
 * <li><code>Helper</code>
 * </ul>
 * 
 * Note that <code>org.test.Example</code> cannot be shortened because the short name <code>Example</code> is already
 * taken. Similarly, <code>Target</code> is already taken by the compilation unit, itself. <code>Helper</code> and
 * <code>String</code> are shortened, but they don't actually require imports.
 * 
 * <p>Finally, the needed import declarations are inserted by calling <code>emitSortedImports()</code>. In this case,
 * only two import declarations are produced:
 * 
 * <pre>
 * import com.example.lib.Example;
 *
 * import java.util.Arrays;</pre>
 * 
 * <p>In addition to auto-importing, <code>ImportManager</code> supports explicit pre-registration of individual and
 * wildcard imports via <code>addImport()</code>.
 * 
 * <p><a name="inner_types">
 * <code>ImportManager</code> provides special handling for inner types, which are specified using <code>$</code>
 * instead of dot in the qualified name. Note that this means that <code>ImportManager</code> does <em>not</em> support
 * the use of <code>$</code> as a character within a class name, although this is allowed by the Java language. For
 * example:
 * 
 * <pre>
 * result.append(importManager.getImportedName("com.example.lib.Outer$Inner", true));</pre>
 * 
 * <p>This imports <code>com.example.lib.Outer</code> and returns <code>Outer.Inner</code>. Multiple levels of nested
 * classes are supported, but <code>$</code> <em>must</em> be used as the separator between all of them. A qualified
 * name with any dots following the first <code>$</code> is not allowed.
 * 
 * <p>Note that it is also possible, instead, to use the simple dot notation for an Outer class, in which case the
 * containing class names will be treated as part of the package name and a more specific import will result. For
 * example:
 * 
 * <pre>
 * result.append(importManager.getImportedName("com.example.lib.Outer.Inner", true));</pre>
 * 
 * <p>This imports <code>com.example.lib.Outer.Inner</code> and returns <code>Inner</code>. Later, if the <code>$</code>
 * notation is used, it can match an import of this type:
 * 
 * <pre>
 * result.append(importManager.getImportedName("com.example.lib.Outer$Inner", false));</pre>
 * 
 * <p>This will use the existing import and return <code>Inner</code>. If, however, the second argument had been
 * <code>true</code> it would have imported <code>Outer</code> before getting the chance to consider <code>Inner</code>.
 *
 * <p><code>ImportManager</code> also handles array types, by always stripping off the indices (the square brackets)
 * when adding imports and preserving them when forming an imported name.
 * 
 * @since 2.1
 */
public class ImportManager
{
  /**
   * The set of imports to be added to the compilation unit.
   */
  protected SortedSet<String> imports = new TreeSet<String>();

  /**
   * The mapping from short names to qualified names for explicit and implicit imports.
   */
  protected HashMap<String, String> shortNameToImportMap = new HashMap<String, String>();

  /**
   * The set of packages that have been imported with wildcards.
   * This can also include containing classes, when inner classes are registered using dot separators.
   */
  protected HashSet<String> importedPackages;

  /*
   * The line delimiter string to use, or {@link System#getProperty(String) System.getProperty}<code>("line.separator")</code>
   * if null.
   * @since 2.5
   */
  private String lineDelimiter;

  /*
   * The string builder into which imports should be inserted.
   * @since 2.5
   */
  private StringBuilder importStringBuilder;

  /*
   * The string buffer into which imports should be inserted.
   * @since 2.5
   */
  private StringBuffer importStringBuffer;

  /*
   * The point in the string builder or buffer at which imports should be inserted.
   * @since 2.5
   */
  private int importInsertionPoint;

  /**
   * The set of short names from <code>java.lang</code> for which explicit import declarations are desired.
   */
  protected HashSet<String> javaLangImports = null;

  /**
   * Creates an import manager for the given compilation unit package and short name.
   * This is the preferred constructor form, as it automatically adds the {@link #addMasterImport(String, String) master import}
   * for the compilation unit.
   * @since 2.5
   * @see ImportManager#addMasterImport(String, String)
   */
  public ImportManager(String compilationUnitPackage, String compilationUnitShortName)
  {
    this(compilationUnitPackage);
    addMasterImport(compilationUnitPackage, compilationUnitShortName);
  }

  /**
   * Creates an import manager for a compilation unit in the given package.
   * Note that the {@link #ImportManager(String, String) two-argument form} is preferred.
   * @see #ImportManager(String, String)
   */
  public ImportManager(String compilationUnitPackage)
  {
    importedPackages = new HashSet<String>();
    importedPackages.add(collapse(compilationUnitPackage));
  }

  /*
   * Removes the whitespace from the given string if there is any.
   * The string is returned unchanged otherwise.
   */
  private String collapse(String s)
  {
    char[] src = s.toCharArray();
    char[] result = null;
    int srcLength = src.length;
    int resultLenth = -1;

    for (int i = 0; i < srcLength; i++)
    {
      if (Character.isWhitespace(src[i]))
      {
        if (result == null)
        {
          result = new char[srcLength];
          System.arraycopy(src, 0, result, 0, i);
          resultLenth = i;
        }
      }
      else if (result != null)
      {
        result[resultLenth++] = src[i];
      }
    }
    return result != null ? new String(result, 0, resultLenth) : s;
  }

  /*
   * Normalizes the given qualified name, package name, or short name.
   * This must be done, at some point, on any name that is recorded.
   */
  private String normalize(String name)
  {
    int j = name.indexOf('[');  
    return collapse(j == -1 ? name : name.substring(0, j));
  }

  /*
   * Computes the normalized import name (package name + short name) from the given qualified name.
   */
  private String getImportName(String qualifiedName)
  {
    int j = qualifiedName.indexOf('$');
    if (j == -1)
    {
      j = qualifiedName.indexOf('[');
    }
    return collapse(j == -1 ? qualifiedName : qualifiedName.substring(0, j));
  }

  /*
   * Computes the normalized package name from the given qualified or import name.
   */
  private String getPackageName(String qualifiedName)
  {
    int j = qualifiedName.lastIndexOf('.');
    return j == -1 ? "" : collapse(qualifiedName.substring(0, j));
  }

  /*
   * Computes the normalized short name from the given qualified or import name.
   */
  private String getShortName(String qualifiedName)
  {
    int i = qualifiedName.lastIndexOf('.') + 1;
    int j = qualifiedName.indexOf('$', i);
    if (j == -1)
    {
      j = qualifiedName.indexOf('[', i);
    }
    if (j == -1)
    {
      j = qualifiedName.length();
    }
    return collapse(qualifiedName.substring(i, j));
  }

  /*
   * Computes the raw name, which is suitable for use in code, from the given qualified name.
   * This is exactly the same name, but with <code>$</code> replaced by dot.
   */
  private String getRawName(String qualifiedName)
  {
    return qualifiedName.replace('$', '.');
  }

  /*
   * Computes the base name, which is suitable for use in code, from the given qualified name.
   * The base name is not normalized.
   */
  private String getBaseName(String qualifiedName)
  {
    int i = qualifiedName.lastIndexOf('.') + 1;
    return qualifiedName.substring(i).replace('$', '.');
  }

  /**
   * Returns the equivalent imported short name for the given qualified name, if there is one, or the qualified name
   * itself otherwise. Optionally, the qualified name can be automatically imported if possible.
   * In fact, a parameterized type expression is also allowed, in which case the expression will be parsed to obtain the
   * individual imported names within it. Then the full expression, with the appropriate substitutions, is returned.
   * @param qualifiedName the qualified name or parameterized type expression.
   * @param autoImport whether to try to automatically import types as needed.
   * @return the equivalent type or type expression, using short names wherever possible.
   * @since 2.5
   */
  public String getImportedName(String qualifiedName, boolean autoImport)
  {
    int i = qualifiedName.indexOf('<');
    if (i == -1)
    {
      return basicGetImportedName(qualifiedName, autoImport);
    }

    StringBuilder result = new StringBuilder();
    for (int start = 0,  end = qualifiedName.length(); i < end; i++)
    {
      char c = qualifiedName.charAt(i);
      switch (c)
      {
        case ',':
        case '<':
        case '>':
        case '&':
        {
          if (start != i)
          {
            result.append(basicGetImportedName(qualifiedName.substring(start, i), autoImport));
          }
          result.append(c);
          start = i + 1;
          break;
        }
        case '?':
        {
          int j = i + 1;
          while (j < end && Character.isWhitespace(qualifiedName.charAt(j)))
          {
            j++;
          }
          if (j + 6 < end && "extends".equals(qualifiedName.substring(j, j + 7)))
          {
            result.append(qualifiedName.substring(i, j + 7));
            i = j + 6;
          }
          else if (j + 4 < end && "super".equals(qualifiedName.substring(j, j + 5)))
          {
            result.append(qualifiedName.substring(i, j + 5));
            i = j + 4;
          }
          else
          {
            result.append(c);
          }
          start = i + 1;
        }
        default:
        {
          if (Character.isWhitespace(c) && start == i)
          {
            result.append(c);
            start++;
          }
          break;
        }
      }
    }
    return result.toString();
  }

  /**
   * Returns the equivalent imported short name for the given qualified name, if there is one, or the qualified name
   * itself otherwise. Optionally, the qualified name can be automatically imported if possible.
   * In fact, a parameterized type expression is also allowed, in which case the expression will be parsed to obtain the
   * individual imported names within it. Then the full expression, with the appropriate substitutions, is returned.
   * @param qualifiedName the qualified name or parameterized type expression.
   * @param autoImport whether to try to automatically import types as needed.
   * @return the equivalent type or type expression, using short names wherever possible.
   */
  public String getImportedName(String qualifiedName)
  {
    return getImportedName(qualifiedName, false);
  }

  /**
   * Gets the imported name for a single qualified name, optionally automatically importing if possible.
   */
  protected String basicGetImportedName(String qualifiedName, boolean autoImport)
  {
    int i = qualifiedName.lastIndexOf('.');
    if (i == -1)
    {
      return qualifiedName;
    }

    if (autoImport)
    {
      addImport(qualifiedName);
    }

    // For inner classes, we should try for an import at any level of nested class.
    //
    String rawName = getRawName(qualifiedName);
    char[] qualifiedNameChars = null;
    while (i != -1)
    {
      String baseName = getBaseName(qualifiedName);
      String importName = getImportName(qualifiedName);
      String shortName = getShortName(importName);

      String registeredName = shortNameToImportMap.get(shortName);
      
      if (registeredName == null)
      {
        // If no match, check for implicit java.lang import, but only on the first try (at the compilation unit level).
        // Failing that, check for matching package.
        //
        if (qualifiedNameChars == null && importName.equals("java.lang." + shortName))
        {
          if (javaLangImports != null && javaLangImports.contains(shortName))
          {
            imports.add(importName);
          }
          return baseName;
        }
        else if (importedPackages.contains(getPackageName(importName)) &&  (javaLangImports == null || !javaLangImports.contains(baseName)))
        {
          return baseName;
        }
      }
      else if (importName.equals(registeredName))
      {
        return baseName;
      }

      // Convert the next $ to a dot.
      //
      i = qualifiedName.indexOf('$', i);
      if (i != -1)
      {
        if (qualifiedNameChars == null)
        {
          qualifiedNameChars = qualifiedName.toCharArray();
        }
        qualifiedNameChars[i] = '.';
        qualifiedName = new String(qualifiedNameChars);
      }
    }
    return rawName;
  }

  /**
   * Registers an import for the given package name and short name.
   * Note that the <code>$</code> notation for inner classes is <em>not</em> supported by this method, since the short
   * name is explicitly specified.
   * @param packageName the package name of the type to import
   * @param shortName the short name of the type to import, or <code>"*"</code> for a wildcard import.
   */
  public void addImport(String packageName, String shortName)
  {
    basicAddImport(normalize(packageName), normalize(shortName), null);
  }

  /**
   * Registers an import for the given qualified name.
   * @param qualifiedName the qualified name of the type to import, which may end with <code>".*"</code> for a wildcard import.
   */
  public void addImport(String qualifiedName)
  {
    String importName = getImportName(qualifiedName);
    basicAddImport(getPackageName(importName), getShortName(importName), importName);
  }

  /*
   * Adds an import for the given package name, short name, and import name, which must all have been normalized already.
   * The import name is actually optional: if null, it will be computed from the package name and short name. 
   */
  private void basicAddImport(String packageName, String shortName, String importName)
  {
    if (importName == null)
    {
      importName = new StringBuilder(packageName).append('.').append(shortName).toString();
    }

    if (shortName.equals("*"))
    {
      importedPackages.add(packageName);
      imports.add(importName);
    }
    else if (!shortNameToImportMap.containsKey(shortName) && shouldImport(packageName, shortName, importName))
    {
      shortNameToImportMap.put(shortName, importName);

      if (!importedPackages.contains(packageName))
      {
        imports.add(importName);
      }
    }
  }

  /**
   * Determines whether the given non-wildcard import should be added.
   * By default, this returns false if the short name is a built-in Java language type name.
   * @since 2.8
   */
  protected boolean shouldImport(String packageName, String shortName, String importName)
  {
    return !CodeGenUtil.isJavaDefaultType(shortName);
  }

  /**
   * Registers a pseudo-import for the given qualified name.
   * A psuedo-import reserves the mapping from short name to qualified name, just like an ordinary import, but does not
   * actually include the import declarations among those returned by {@link #computeSortedImports()} or emitted by
   * {@link #emitSortedImports()}.
   * Note that all pseudo-imports must be added before any other ordinary imports.
   * @param qualifiedName the qualified name of the type to import, which may end with <code>".*"</code> for a wildcard import.
   * @see #computeSortedImports()
   * @see #emitSortedImports()
   */
  public void addPseudoImport(String qualifiedName)
  {
    String importName = getImportName(qualifiedName);
    String shortName = getShortName(importName);
    
    if (shortName.equals("*"))
    {
      String packageName = getPackageName(importName);
      importedPackages.add(packageName);
    }
    else
    {
      shortNameToImportMap.put(shortName, importName);
    }
  }

  /**
   * Reserves the import mapping for the given package and short name of the compilation unit.
   * The <code>$</code> notation for inner classes is <em>not</em> supported by this method, since the short name is
   * explicitly specified.
   * Note that a master import must be added before any pseudo- or ordinary imports. However, it need not be done
   * explicitly if the preferred, {@link #ImportManager(String, String) two-argument constructor} form is used.
   * @see #ImportManager(String, String)
   */
  public void addMasterImport(String packageName, String shortName)
  {
    packageName = normalize(packageName);
    shortName = normalize(shortName);
    shortNameToImportMap.put(shortName, new StringBuilder(packageName).append('.').append(shortName).toString());
  }

  /**
   * Returns whether a mapping for the given short name has been reserved.
   */
  public boolean hasImport(String shortName)
  {
    return shortNameToImportMap.containsKey(normalize(shortName));
  }

  /**
   * Returns the list of qualified names for which imports are to be added to the compilation unit. 
   */
  public Collection<String> getImports()
  {
    compactImports();
    return imports;
  }

  /*
   * Removes any explicit imports that are already covered by wildcards.
   */
  private void compactImports()
  {
    for (Iterator<String> i = imports.iterator(); i.hasNext(); )
    {
      String importName = i.next();
      if (!getShortName(importName).equals("*") && importedPackages.contains(getPackageName(importName)))
      {
        i.remove();
      }
    }
  }

  /**
   * Returns the line delimiter to be used in {@link #computeSortedImports()}.
   * By default, this is {@link System#getProperty(String) System.getProperty}<code>("line.separator")</code>.
   * @see #computeSortedImports()
   * @see #setLineDelimiter(String)
   * @since 2.5
   */
  public String getLineDelimiter()
  {
    return lineDelimiter == null ? System.getProperty("line.separator") : lineDelimiter;
  }

  /**
   * Sets the line delimiter to be used in {@link #computeSortedImports()}.
   * @see #computeSortedImports()
   * @see #getLineDelimiter()
   * @since 2.5
   */
  public void setLineDelimiter(String lineDelimiter)
  {
    this.lineDelimiter = lineDelimiter;
  }

  /**
   * Returns the sorted, formatted import declarations that should be added to the compilation unit.
   * Each statement appears on its own line, with an additional {@link #getLineDelimiter() line delimiter} before the
   * first import and between imports from different packages.
   * @see #getLineDelimiter()
   */
  public String computeSortedImports()
  {
    String NL = getLineDelimiter();
    String previousPackageName = null;
    StringBuffer result = new StringBuffer();

    for (String importName : getImports())
    {
      String packageName = getPackageName(importName);
      if (previousPackageName != null && !previousPackageName.equals(packageName))
      {
        result.append(NL);
      }
      previousPackageName = packageName;
      result.append(NL + "import " + importName + ";");
    }
    return result.toString();
  }

  /**
   * Registers {@link #addPseudoImport(String) pseudo-imports} for all of the import declarations in the specified
   * compilation unit contents.
   * This uses JDT's Java AST API to parse the code when Eclipse is running, and a simpler, less accurate approach
   * based on regular expressions otherwise. 
   * Note that this must be invoked before any ordinary imports are added.
   * @param compilationUnitContents the contents of a Java source file.
   * @see #addPseudoImport(String)
   */
  public void addCompilationUnitImports(String compilationUnitContents)
  {
    List<String> imports = EMFPlugin.IS_ECLIPSE_RUNNING ?
      EclipseHelper.getCompilationUnitImports(compilationUnitContents) :
      getCompilationUnitImports(compilationUnitContents);

    for (String qualifiedName : imports)
    {
      addPseudoImport(qualifiedName);
    }
  }

  /*
   * Uses a regular expression to try to parse the import statements from the given compilation unit contents.
   */
  private List<String> getCompilationUnitImports(String compilationUnitContents)
  {
    List<String> result = new ArrayList<String>();
    Pattern importPattern = Pattern.compile("import\\s+([^\\s;]*);\\s*", Pattern.MULTILINE | Pattern.DOTALL);
    Matcher matcher = importPattern.matcher(compilationUnitContents);
    while (matcher.find())
    {
      result.add(matcher.group(1));
    }
    return result;
  }

  /*
   * Uses JDT's AST API to parse the import statements from the given compilation unit contents.
   */
  private static class EclipseHelper
  {
    public static List<String> getCompilationUnitImports(String compilationUnitContents)
    {
      List<String> result = new ArrayList<String>();
      ASTParser parser = CodeGenUtil.EclipseUtil.newASTParser();
      parser.setSource(compilationUnitContents.toCharArray());
      CompilationUnit compilationUnit = (CompilationUnit)parser.createAST(new NullProgressMonitor());
      for (Iterator<?> i = compilationUnit.imports().iterator(); i.hasNext();)
      {
        ImportDeclaration importDeclaration = (ImportDeclaration)i.next();
        result.add(importDeclaration.getName().getFullyQualifiedName());
      }
      return result;
    }
  }

  /**
   * Records the given <code>StringBuilder</code> and its current length, so that computed imports can later be
   * {@link #emitSortedImports() emitted}, and {@link #addCompilationUnitImports(String) adds} any import declarations
   * that the builder already contains.
   * @since 2.5
   * @see #emitSortedImports()
   * @see #addCompilationUnitImports(String)
   */
  public void markImportLocation(StringBuilder stringBuilder)
  {
    importStringBuffer = null;
    importStringBuilder = stringBuilder;
    importInsertionPoint = stringBuilder.length();
    addCompilationUnitImports(stringBuilder.toString());
  }

  /**
   * Records the given <code>StringBuffer</code> and its current length, so that computed imports can later be
   * {@link #emitSortedImports() emitted}, and {@link #addCompilationUnitImports(String) adds} any import declarations
   * that the buffer already contains.
   * @since 2.5
   * @see #emitSortedImports()
   * @see #addCompilationUnitImports(String)
   */
  public void markImportLocation(StringBuffer stringBuffer)
  {
    importStringBuilder = null;
    importStringBuffer = stringBuffer;
    importInsertionPoint = stringBuffer.length();
    addCompilationUnitImports(stringBuffer.toString());
  }

  /**
   * Inserts all the {@link #computeSortedImports() computed imports} for the compilation unit into the recorded
   * {@link #markImportLocation(StringBuilder) StringBuilder} or {@link #markImportLocation(StringBuffer) StringBuffer}. 
   * @since 2.5
   * @see #computeSortedImports()
   * @see #markImportLocation(StringBuilder)
   * @see #markImportLocation(StringBuffer)
   */
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

  /**
   * Ensures that explicit import declarations will be added for classes from <code>java.lang</code> with the 
   * specified short names.
   * By default, all the short names of classes in this package are reserved so that the implicit imports are used.
   * By specifying particular classes with this method, those imports, if actually used, will be made explicit.
   * @param javaLangClassNames
   */
  public void addJavaLangImports(List<String> javaLangClassNames)
  {
    if (!javaLangClassNames.isEmpty())
    {
      javaLangImports = new HashSet<String>();
      for (String shortName : javaLangClassNames)
      {
        javaLangImports.add(normalize(shortName));
      }
    }
  }
}
