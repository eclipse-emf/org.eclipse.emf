/**
 * <copyright>
 *
 * Copyright (c) 2002-2005 IBM Corporation and others.
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
 * $Id: JETCompiler.java,v 1.11 2005/03/31 19:55:10 davidms Exp $
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
import java.util.Stack;

import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Platform;

import org.eclipse.emf.codegen.CodeGenPlugin;


public class JETCompiler implements JETParseEventListener
{
  protected final static char[] NULL_CHAR_ARRAY = {};

  protected String[] templateURIPath;

  protected String templateURI;

  protected JETParser parser;

  protected JETSkeleton skeleton;

  protected JETReader reader;

  protected PrintWriter writer;

  protected List generators = new ArrayList(100);

  protected List constants = new ArrayList(100);

  protected Map constantDictionary = new HashMap(100, 100);

  protected long constantCount = 0;

  /**
   * If true, the newline immediately preceding a scriptlet or directive (though not a successful include directive),
   * along with any intervening spaces, will be stripped from the character data.
   */
  protected boolean fNoNewLineForScriptlets = true;

  protected boolean fUseStaticFinalConstants = true;

  /**
   * If fNoNewLineForScriptlets is true, the trailing newline/space sequence is stripped from each character
   * data segment, and stored in this field. Depending on what follows, it may then be discarded or handled as its
   * own character data segment.
   */
  protected char[] fSavedLine = null;

  /**
   * The depth of the current section, where 0 is outside of any sections. A section is delimited by start and
   * end directives, and must be preceded by an include directive with fail="alternative".
   */
  protected int sectionDepth = 0;

  /**
   * Whether content is currently being skipped. This is set according to skipSections, as sections are started and ended. 
   */
  protected boolean skipping = false;

  /**
   * A stack of sections and whether to start skipping, one from each include with alternative encountered.
   */
  protected Stack skipSections = new Stack();

  /**
   * A skip section entry, records the depth of the section and whether to start skipping there. 
   */
  static class SkipSection
  {
    int depth;
    boolean skip;

    SkipSection(int depth, boolean skip)
    {
      this.depth = depth;
      this.skip = skip;
    }
  }

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

  public JETCompiler(String[] templateURIPath, String relativeTemplateURI) throws JETException
  {
    this(templateURIPath, relativeTemplateURI, "UTF8");
  }

  public JETCompiler(String[] templateURIPath, String relativeTemplateURI, String encoding) throws JETException
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
    if (directive.equals("include"))
    {
      String fileURI = (String)attributes.get("file");
      if (fileURI != null)
      {
        String currentURI = start.getFile();
        String[] resolvedFileURI = resolveLocation(templateURIPath, currentURI, fileURI);
        try
        {
          BufferedInputStream bufferedInputStream = new BufferedInputStream(openStream(resolvedFileURI[1]));
          reader.stackStream(resolvedFileURI[0], bufferedInputStream, null);

          // The include succeeded, so if there is an alternative and we're not skippping, we need to start.
          //
          if ("alternative".equals((String)attributes.get("fail")))
          {
            skipSections.push(new SkipSection(sectionDepth + 1, !skipping));
          }

          // If a newline from the previous character data remains, add a generator for it.
          //
          if (fSavedLine != null)
          {
            addCharDataGenerator(fSavedLine);
          }
        }
        catch (JETException exception)
        {
          // The include failed, so if there is an alternative, we don't skip it.
          //
          String failType = (String)attributes.get("fail");
          if ("alternative".equals(failType))
          {
            skipSections.push(new SkipSection(sectionDepth + 1, false));
          }
          else if (!"silent".equals(failType))
          {
            throw 
              new JETException
                (CodeGenPlugin.getPlugin().getString
                  ("jet.error.file.cannot.read", 
                   new Object [] { resolvedFileURI[1], start.format("jet.mark.file.line.column") }),
                 exception);
          }
        }
      }
      else
      {
        throw 
          new JETException
            (CodeGenPlugin.getPlugin().getString
              ("jet.error.missing.attribute", 
          new Object []{ "file", start.format("jet.mark.file.line.column") }));
      }
    }
    else if (directive.equals("start"))
    {
      sectionDepth++;
      
      // A section is not allowed without a preceeding include with alternative.
      //
      SkipSection skipSection = skipSections.isEmpty() ? null : (SkipSection)skipSections.peek();
      if (skipSection == null || skipSection.depth != sectionDepth)
      {
        throw new JETException
          (CodeGenPlugin.getPlugin().getString
            ("jet.error.section.noinclude",
             new Object[] { start.format("jet.mark.file.line.column") }));
      }
      else if (skipSection.skip)
      {
        skipping = true;
      }
    }
    else if (directive.equals("end"))
    {
      if (sectionDepth == 0)
      {
        throw
          new JETException
            (CodeGenPlugin.getPlugin().getString
              ("jet.error.unmatched.directive",
               new Object[] { "start", "end", start.format("jet.mark.file.line.column") }));
      }
      sectionDepth--;

      // This pop is safe because a section couldn't have been started without an include that pushed.
      //
      if (((SkipSection)skipSections.pop()).skip)
      {
        skipping = false;
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
            byte[] input = new byte [bufferedInputStream.available()];
            bufferedInputStream.read(input);
            bufferedInputStream.close();
            skeleton.setCompilationUnitContents(new String(input));
          }
          catch (IOException exception)
          {
            throw new JETException(exception);
          }
        }

        for (Iterator entries = attributes.entrySet().iterator(); entries.hasNext();)
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
              new Object []{ entry.getKey(), start.format("jet.mark.file.line.column") }));
        }

        handleNewSkeleton();
      }
    }

    fSavedLine = null;
  }

  protected void handleNewSkeleton()
  {
  }

  public void handleExpression(JETMark start, JETMark stop, Map attributes) throws JETException
  {
    if (skipping) return;

    JETGenerator gen = new JETExpressionGenerator(reader.getChars(start, stop));
    addGenerator(gen);
  }

  public void handleScriptlet(JETMark start, JETMark stop, Map attributes) throws JETException
  {
    if (skipping) return;

    fSavedLine = null;
    JETGenerator gen = new JETScriptletGenerator(reader.getChars(start, stop));
    addGenerator(gen);
  }

  public void handleCharData(char[] chars) throws JETException
  {
    if (skipping) return;

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
    // If a newline from the previous character data remains, add a generator for it.
    //
    if (fSavedLine != null)
    {
      addCharDataGenerator(fSavedLine);
      fSavedLine = null;
    }
    generators.add(gen);
  }

  public void addCharDataGenerator(char[] chars) throws JETException
  {
    // An expression with more that 931 "+" will break Sun and IBM javac compilers.
    //
    if (chars.length > 500)
    {
      int nl = 0;
      int lf = 0;

      int start = 0;
      LOOP:
      for (int i = 0; i < chars.length; ++i)
      {
        switch (chars[i])
        {
          case '\n':
          {
            ++nl;
            break;
          }
          case '\r':
          {
            ++lf;
            break;
          }
          default:
          {
            continue;
          }
        }

        if (lf > 400 || nl > 400)
        {
          for (++i; i < chars.length; ++i)
          {
            switch (chars[i])
            {
              case '\n':
              case '\r':
              {
                continue;
              }
              default:
              {
                int size = i - start;
                char [] block = new char [size];
                System.arraycopy(chars, start, block, 0, size);
                doAddCharDataGenerator(block);
                start = i;
                nl = 0;
                lf = 0;
                continue LOOP;
              }
            }
          }
        }
      }
      if (start != 0)
      {
        int size = chars.length - start;
        char [] block = new char [size];
        System.arraycopy(chars, start, block, 0, size);
        doAddCharDataGenerator(block);
        return;
      }
    }

    doAddCharDataGenerator(chars);
  }

  public void doAddCharDataGenerator(char[] chars) throws JETException
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
    else if (chars.length >= 1 && 
          (chars[0] == '\n' || chars[0] == '\r'))
    {
      chars = new String(chars, 1, chars.length - 1).toCharArray();
    }
    return chars;
  }

  protected char[] stripLastNewLineWithBlanks(char[] chars)
  {
    int i = chars.length - 1;
    while (i > 0 && chars[i] == ' ')
    {
      --i;
    }
    if (chars[i] == '\n')
    {
      if (i > 0 && chars[i - 1] == '\r')
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
        chars = new String(chars, 0, i).toCharArray();
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
    if (sectionDepth > 0)
    {
      throw
        new JETException
          (CodeGenPlugin.getPlugin().getString
            ("jet.error.unmatched.directive",
             new Object[] { "end", "start", reader.mark().format("jet.mark.file.line.column") }));
    }

    if (skeleton == null)
    {
      throw
        new JETException
          (CodeGenPlugin.getPlugin().getString
            ("jet.error.missing.jet.directive",
        new Object []{ reader.mark().format("jet.mark.file.line.column") }));
    }

    // If a newline from the previous character data remains, add a generator for it.
    //
    if (fSavedLine != null)
    {
      addCharDataGenerator(fSavedLine);
    }

    List generatedConstants = new ArrayList(constants.size());
    for (Iterator i = constants.iterator(); i.hasNext();)
    {
      generatedConstants.add(((JETConstantDataGenerator)(i.next())).generateConstant());
    }
    skeleton.setConstants(generatedConstants);

    List generatedBody = new ArrayList(generators.size());
    for (Iterator i = generators.iterator(); i.hasNext();)
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
    directive.getDirectives().add("start");
    directive.getDirectives().add("end");

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
    writer = new PrintWriter(oStream);
    endPageProcessing();
    writer.close();
  }

  public JETSkeleton getSkeleton()
  {
    return skeleton;
  }

  protected static String[] resolveLocation(String[] templateURIPath, String baseLocationURI, String locationURI)
  {
    String[] result = new String []{ locationURI, locationURI };
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

  public static String find(String[] locationURIPath, String relativeLocationURI)
  {
    String result = null;
    for (int i = 0; i < locationURIPath.length; ++i)
    {
      result = locationURIPath[i];

      if (result != null)
      {
        try
        {
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
