/**
 * <copyright>
 *
 * Copyright (c) 2002-2004 IBM Corporation and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Common Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/cpl-v10.html
 * 
 * Contributors: 
 *   IBM - Initial API and implementation
 *
 * </copyright>
 *
 * $Id: JETCompiler.java,v 1.4 2004/06/20 15:36:37 emerks Exp $
 */
package org.eclipse.emf.codegen.jet;


import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Platform;

import org.eclipse.emf.codegen.CodeGenPlugin;


public class JETCompiler implements JETParseEventListener 
{
  protected final static char [] NULL_CHAR_ARRAY = {};

  protected String [] templateURIPath;
  protected String templateURI;
  protected JETParser parser;
  protected JETSkeleton skeleton;
  protected JETReader reader;
  protected PrintWriter writer;
  protected List generators = new ArrayList(100);
  protected List constants = new ArrayList(100);
  protected Map constantDictionary = new HashMap(100, 100);
  protected long constantCount  = 0;
  protected boolean fNoNewLineForScriptlets = true;
  protected boolean fUseStaticFinalConstants = true;
  protected char[]  fSavedLine = null;

  protected static final String CONSTANT_PREFIX = "TEXT_";

  public JETCompiler(String templateURI) throws JETException 
  {
    this(templateURI, "UTF8");
  }

  public JETCompiler(String templateURI, String encoding) throws JETException 
  {
    this(templateURI, openStream(templateURI), encoding);
  }

  public JETCompiler(String templateURI, InputStream inputStream, String encoding) throws JETException 
  {
    super();

    this.templateURI = templateURI;
    this.reader = new JETReader(templateURI, inputStream, encoding);
  }

  public JETCompiler(String [] templateURIPath, String relativeTemplateURI) throws JETException 
  {
    this(templateURIPath, relativeTemplateURI, "UTF8");
  }

  public JETCompiler(String [] templateURIPath, String relativeTemplateURI, String encoding) throws JETException 
  {
    super();

    this.templateURIPath = templateURIPath;
    this.templateURI = relativeTemplateURI;
    String actualTemplateURI = find(templateURIPath, relativeTemplateURI);
    this.reader = new JETReader(relativeTemplateURI, openStream(actualTemplateURI), encoding);
  }

  public String getResolvedTemplateURI()
  {
    return reader.getFile(0);
  }

  public void handleDirective(String directive, JETMark start, JETMark stop, Map attributes) throws JETException 
  {
    // This will drop the trailing newline.
    //
    fSavedLine = null;
    if (directive.equals("include"))
    {
      String fileURI = (String)attributes.get("file");
      if (fileURI != null)
      {
        String currentURI = start.getFile();
        String [] resolvedFileURI = resolveLocation(templateURIPath, currentURI, fileURI);
        try
        {
          BufferedInputStream bufferedInputStream = new BufferedInputStream(openStream(resolvedFileURI[1]));
          reader.stackStream(resolvedFileURI[0], bufferedInputStream, null);
        }
        catch (JETException exception)
        {
          throw 
            new JETException
              (CodeGenPlugin.getPlugin().getString
                ("jet.error.file.cannot.read", 
                 new Object [] { resolvedFileURI[1], start.format("jet.mark.file.line.column") }),
               exception);

        }
      }
      else
      {
        throw 
          new JETException
            (CodeGenPlugin.getPlugin().getString
              ("jet.error.missing.attribute", 
               new Object [] { "href", start.format("jet.mark.file.line.column") }));
      }
    }
    else if (directive.equals("jet"))
    {
      if (skeleton != null)
      {
        // Multiple jet directives.
      }
      else
      {
        skeleton = new JETSkeleton();
        // Process this first.
        //
        String skeletonURI = (String)attributes.get("skeleton");
        if (skeletonURI != null)
        {
          try
          {
            BufferedInputStream bufferedInputStream = 
              new BufferedInputStream(openStream(resolveLocation(templateURIPath, templateURI, skeletonURI)[1]));
            byte [] input = new byte [bufferedInputStream.available()];
            bufferedInputStream.read(input);
            bufferedInputStream.close();
            skeleton.setCompilationUnitContents(new String(input));
          }
          catch (IOException exception)
          {
            throw new JETException(exception);
          }
        }

        for (Iterator entries = attributes.entrySet().iterator(); entries.hasNext(); )
        {
          Map.Entry entry = (Map.Entry)entries.next();

          // Ignore this now
          //
          if (entry.getKey().equals("skeleton"))
          {
          }
          else if (entry.getKey().equals("package"))
          {
            skeleton.setPackageName((String)entry.getValue());
          }
          else if (entry.getKey().equals("imports"))
          {
            skeleton.addImports((String)entry.getValue());
          }
          else if (entry.getKey().equals("class"))
          {
            skeleton.setClassName((String)entry.getValue());
          }
          else if (entry.getKey().equals("nlString"))
          {
            skeleton.setNLString((String)entry.getValue());
          }
          else if (entry.getKey().equals("startTag"))
          {
            parser.setStartTag((String)entry.getValue());
          }
          else if (entry.getKey().equals("endTag"))
          {
            parser.setEndTag((String)entry.getValue());
          }
          else if (entry.getKey().equals("version"))
          {
            // Ignore the version
          }

    /*
          if ( attr.equals("package") )
                  skeleton.setPackageName((String) attributes.get(attr));

          else if ( attr.equals("imports") )
                  skeleton.setImportsList((String) attributes.get(attr));

          else if ( attr.equals("class") )
                  skeleton.setClassName((String) attributes.get(attr));

          else if ( attr.equals("extends") )
                  skeleton.setExtendsName((String) attributes.get(attr));

          else if ( attr.equals("implements") )
                  skeleton.setImplementsList((String) attributes.get(attr));

          else if ( attr.equals("methodname") || attr.equals("methodName"))
                  skeleton.setMethodName((String) attributes.get(attr));

          else if ( attr.equals("throws") )
                  skeleton.setThrowsList((String) attributes.get(attr));

          else if ( attr.equals("parameters") )
                  skeleton.setParametersList((String) attributes.get(attr));

          else if ( attr.equals("writer") )
                  skeleton.setWriterName((String) attributes.get(attr));

    */
          else
            throw 
              new JETException
                (CodeGenPlugin.getPlugin().getString
                  ("jet.error.bad.attribute", 
                   new Object [] { entry.getKey(), start.format("jet.mark.file.line.column") }));
        }

        handleNewSkeleton();
      }
    }
  }

  protected void handleNewSkeleton()
  {
  }

  public void handleExpression(JETMark start, JETMark stop, Map attributes) throws JETException
  {
    JETGenerator gen = new JETExpressionGenerator( reader.getChars(start, stop));
    addGenerator(gen);
  }

  public void handleScriptlet(JETMark start, JETMark stop, Map attributes) throws JETException
  {
    fSavedLine = null;       
    JETGenerator gen = new JETScriptletGenerator(reader.getChars(start, stop));
    addGenerator(gen);
  }

  public void handleCharData(char[] chars) throws JETException
  {
    if (fNoNewLineForScriptlets)
    {
      char[] strippedChars = stripLastNewLineWithBlanks(chars);
      if (strippedChars.length > 0)
      {
        addCharDataGenerator(strippedChars);
      }
    }
    else
    {
     addCharDataGenerator(chars);
    }
  }

  public void addGenerator(JETGenerator gen) throws JETException 
  {
    if (fSavedLine != null)
    {
      addCharDataGenerator(fSavedLine);
      fSavedLine = null;
    }
    generators.add(gen);
  }

  public void addCharDataGenerator(char[] chars) throws JETException 
  {
    if (fUseStaticFinalConstants)
    {
      JETConstantDataGenerator gen = (JETConstantDataGenerator)constantDictionary.get(chars);
      if (gen == null)
      {
        if (constantCount == 0)
        {
          chars = stripFirstNewLineWithBlanks(chars);
        }
        ++constantCount;
        String label = CONSTANT_PREFIX + constantCount;
        gen = new JETConstantDataGenerator(chars, label);
        constantDictionary.put(chars, gen);
        constants.add(gen);
      }
      generators.add(gen);
    }
    else
    {
      generators.add(new JETCharDataGenerator(chars));
    }
  }

  protected char[] stripFirstNewLineWithBlanks(char[] chars)
  {
    if (chars.length >=  2 && 
          (chars[0] == '\n' && chars[1] == '\r' || chars[0] == '\r' && chars[1] == '\n'))
    {
      chars = new String(chars, 2, chars.length - 2).toCharArray();
    }
    return chars;
  }

  protected char[] stripLastNewLineWithBlanks(char[] chars)
  {
    int i = chars.length - 1;
    while ( i > 0 && chars[i] == ' ' )
    {
      --i;
    }
    if (chars[i] == '\n')
    {
      if (i > 0 && chars[i-1] == '\r')
      {
        --i;
      }
      fSavedLine = (new String(chars, i, chars.length - i)).toCharArray();
      if (i == 0)
      {
        return NULL_CHAR_ARRAY;
      }
      else
      {
        chars = new String(chars, 0, i+1).toCharArray();
        return chars;
      }
    }
    else
    {
      return chars;
    }
  }

  public void beginPageProcessing()
  {
  }

  public void endPageProcessing() throws JETException
  {
    if (skeleton == null)
    {
      throw
        new JETException
          (CodeGenPlugin.getPlugin().getString
            ("jet.error.missing.jet.directive",
              new Object [] { reader.mark().format("jet.mark.file.line.column") }));
    }

    // Add last line if saved
    //
    if (fSavedLine != null)  
    {
      addCharDataGenerator(fSavedLine);
    }

    List generatedConstants = new ArrayList(constants.size());
    for (Iterator i = constants.iterator(); i.hasNext(); )
    {
      generatedConstants.add(((JETConstantDataGenerator)(i.next())).generateConstant());
    }
    skeleton.setConstants(generatedConstants);

    List generatedBody = new ArrayList(generators.size());
    for (Iterator i = generators.iterator(); i.hasNext(); )
    {
      generatedBody.add(((JETGenerator)(i.next())).generate());
    }
    skeleton.setBody(generatedBody);

    writer.print(skeleton.getCompilationUnitContents());
  }

  public void parse() throws JETException
  {
    // Register our directive.
    //
    JETParser.Directive directive = new JETParser.Directive();
    directive.getDirectives().add("jet");
    directive.getDirectives().add("include");

    JETCoreElement[] coreElements = 
      {
        directive,
        new JETParser.QuoteEscape(), 
        new JETParser.Expression(), 
        new JETParser.Scriptlet()
      };

    Class[] accept = 
      {
        JETParser.Directive.class, 
        JETParser.QuoteEscape.class, 
        JETParser.Expression.class, 
        JETParser.Scriptlet.class
      };

    parse(coreElements, accept);
  }

  protected void parse(JETCoreElement[] coreElements, Class[] accept) throws JETException
  {
    parser = new JETParser(reader, this, coreElements);
    beginPageProcessing();
    parser.parse(null, accept);
  }

  public void generate(OutputStream oStream) throws JETException
  {
    writer =  new PrintWriter(oStream);
    endPageProcessing();
    writer.close();
  }

  public JETSkeleton getSkeleton()
  {
    return skeleton;
  }

  protected static String [] resolveLocation(String [] templateURIPath, String baseLocationURI, String locationURI)
  {
    String [] result = new String [] { locationURI, locationURI };
    try
    {
      String file;
      try
      {
        URL url = new URL(locationURI);
        url = Platform.resolve(url);
        file = url.getFile();
      }
      catch (MalformedURLException exception)
      {
        file = locationURI;
      }

      IPath path = new Path(file);
      if (!path.isAbsolute())
      {
        String resolvedLocation = "";
        int index = baseLocationURI.lastIndexOf("/");
        if (index != -1)
        {
          resolvedLocation = baseLocationURI.substring(0, index + 1);
        }
        resolvedLocation += path;
        result[0] = resolvedLocation;
        if (templateURIPath != null)
        {
          resolvedLocation = find(templateURIPath, resolvedLocation);
        }
        if (resolvedLocation != null)
        {
          result[1] = resolvedLocation;
        }
      }
    }
    catch (IOException exception)
    {
    }

    return result;
  }

  public static String find(String [] locationURIPath, String relativeLocationURI)
  {
    String result = null;
    for (int i = 0; i < locationURIPath.length; ++i)
    {
      try
      {
        result = locationURIPath[i];
        if (!result.endsWith("/"))
        {
          result += "/";
        }
        result += relativeLocationURI;

        InputStream inputStream = openStream(result);
        inputStream.close();
        break;
      }
      catch (JETException exception)
      {
        result = null;
      }
      catch (IOException exception)
      {
        result = null;
      }
    }
    return result;
  }

  public static InputStream openStream(String locationURI) throws JETException
  {
    try
    {
      URL url = null;
      try
      {
        url = new URL(locationURI);
        url = Platform.resolve(url);
      }
      catch (MalformedURLException exception)
      {
        url = new URL("file:" + locationURI);
      }

      BufferedInputStream bufferedInputStream = new BufferedInputStream(url.openStream());
      return bufferedInputStream;
    }
    catch (IOException exception)
    {
      throw new JETException(exception.getLocalizedMessage(), exception);
    }
  }
}
