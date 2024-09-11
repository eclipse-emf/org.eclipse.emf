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


import java.io.BufferedInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.Writer;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.AbstractMap;
import java.util.AbstractSet;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Stack;
import java.util.TreeSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.eclipse.emf.codegen.util.CodeGenUtil;
import org.eclipse.emf.common.CommonPlugin;
import org.eclipse.emf.common.EMFPlugin;
import org.eclipse.emf.common.util.CommonUtil;
import org.eclipse.emf.common.util.Diagnostic;
import org.eclipse.emf.common.util.URI;


public class JETCompiler implements JETParseEventListener, JETParseEventListener.CommentListener
{
  private static final Pattern LINE_PATTERN = Pattern.compile("([^\n\r]*)\r?\n");

  protected final static char[] NULL_CHAR_ARRAY = {};

  protected String[] templateURIPath;

  protected String templateURI;

  protected JETParser parser;

  protected JETSkeleton skeleton;

  protected JETReader reader;

  protected PrintWriter writer;

  protected List<JETGenerator> generators = new ArrayList<JETGenerator>(100);

  protected List<JETConstantDataGenerator> constants = new ArrayList<JETConstantDataGenerator>(100);

  protected Map<char[], JETConstantDataGenerator> constantDictionary = new AbstractMap<char[], JETConstantDataGenerator>()
    {
      private final Map<String, JETConstantDataGenerator> delegate = new HashMap<String, JETConstantDataGenerator>(100, 100);

      @Override
      public JETConstantDataGenerator put(char[] key, JETConstantDataGenerator value)
      {
        return delegate.put(new String(key), value);
      }

      @Override
      public JETConstantDataGenerator get(Object key)
      {
        return delegate.get(key instanceof char[] ? new String((char[])key) : key);
      }

      @Override
      public int size()
      {
        return delegate.size();
      }

      @Override
      public Set<Map.Entry<char[], JETConstantDataGenerator>> entrySet()
      {
        return new AbstractSet<Map.Entry<char[], JETConstantDataGenerator>>()
          {
            @Override
            public Iterator<Map.Entry<char[], JETConstantDataGenerator>> iterator()
            {
              return new Iterator<Map.Entry<char[], JETConstantDataGenerator>>()
                {
                  private final Iterator<Map.Entry<String, JETConstantDataGenerator>> delegateIterator = delegate.entrySet().iterator();

                  public boolean hasNext()
                  {
                    return delegateIterator.hasNext();
                  }

                  public void remove()
                  {
                    delegateIterator.remove();
                  }

                  public Entry<char[], JETConstantDataGenerator> next()
                  {
                    final Entry<String, JETConstantDataGenerator> next = delegateIterator.next();
                    return new Map.Entry<char[], JETConstantDataGenerator>()
                      {
                        public char[] getKey()
                        {
                          return next.getKey().toCharArray();
                        }

                        public JETConstantDataGenerator getValue()
                        {
                          return next.getValue();
                        }

                        public JETConstantDataGenerator setValue(JETConstantDataGenerator value)
                        {
                          return next.setValue(value);
                        }

                        @Override
                        public boolean equals(Object obj)
                        {
                          if (obj instanceof Map.Entry)
                          {
                            char[] key = getKey();
                            String keyValue = key == null ? null : new String(key);
                            Map.Entry<?, ?> entry = (Map.Entry<?, ?>)obj;
                            Object otherKey = entry.getKey();
                            Object otherKeyValue = otherKey instanceof char[] ? new String((char[])otherKey) : null;
                            return (keyValue == null ? otherKeyValue == null : keyValue.equals(otherKeyValue))
                                && (getValue() == null ? entry.getValue() == null : getValue().equals(entry.getValue()));
                          }
                          else
                          {
                            return false;
                          }
                        }

                        @Override
                        public int hashCode()
                        {
                          return next.hashCode();
                        }
                      };
                  }
                };
            }

            @Override
            public int size()
            {
              return delegate.size();
            }
          };
      }
    };

  protected long constantCount = 0;

  /**
   * If true, the newline immediately preceding a scriptlet or directive (though not a successful include directive),
   * along with any intervening spaces, will be stripped from the character data.
   */
  protected boolean fNoNewLineForScriptlets = true;

  protected boolean fUseStaticFinalConstants = true;

  /**
   * @since 2.19
   */
  protected boolean useMinimizedConstants;

  /**
   * @since 2.19
   */
  protected final Map<String, String> constantSubstitutitons = new HashMap<String, String>();

  /**
   * If fNoNewLineForScriptlets is true, the trailing newline/space sequence is stripped from each character
   * data segment, and stored in this field. Depending on what follows, it may then be discarded or handled as its
   * own character data segment.
   */
  protected char[] fSavedLine;

  /**
   * @since 2.19
   */
  protected JETLiteralItem savedLineLiteralItem;

  /**
   * The depth of the current section, where 0 is outside of any sections. A section is delimited by start and
   * end directives, and must be preceded by an include directive with fail="alternative".
   */
  protected int sectionDepth;

  /**
   * Whether content is currently being skipped. This is set according to skipSections, as sections are started and ended.
   */
  protected boolean skipping;

  /**
   * A stack of sections and whether to start skipping, one from each include with alternative encountered.
   */
  protected Stack<SkipSection> skipSections = new Stack<SkipSection>();

  /**
   * A skip section entry, records the depth of the section and whether to start skipping there.
   */
  static class SkipSection
  {
    int depth;

    boolean skip;

    JETDirectiveItem jetDirectiveItem;

    SkipSection(int depth, boolean skip, JETDirectiveItem jetDirectiveItem)
    {
      this.depth = depth;
      this.skip = skip;
      this.jetDirectiveItem = jetDirectiveItem;
    }
  }

  protected static final String CONSTANT_PREFIX = "TEXT_";

  /**
   * @since 2.19
   */
  protected JETInputStreamHandler inputStreamHandler;

  /**
   * @since 2.19
   */
  protected final List<JETItem> jetItems = new ArrayList<JETItem>();

  /**
   * @since 2.19
   */
  protected JETLiteralItem currentLiteralItem;

  /**
   * @since 2.19
   */
  protected JETProblemListener problemListener;

  /**
   * @since 2.19
   */
  protected JETMark pageStart;

  public JETCompiler(String templateURI) throws JETException
  {
    this(templateURI, "UTF8");
  }

  public JETCompiler(String templateURI, String encoding) throws JETException
  {
    this(templateURI, openStream(templateURI, null), encoding);
  }

  public JETCompiler(String templateURI, InputStream inputStream, String encoding) throws JETException
  {
    super();

    this.templateURI = templateURI;
    this.reader = new JETReader(templateURI, inputStream, encoding);
    this.problemListener = new JETProblemListener();
  }

  public JETCompiler(String[] templateURIPath, String relativeTemplateURI) throws JETException
  {
    this(templateURIPath, relativeTemplateURI, "UTF8");
  }

  public JETCompiler(String[] templateURIPath, String relativeTemplateURI, String encoding) throws JETException
  {
    this(templateURIPath, relativeTemplateURI, encoding, null, new JETProblemListener());
  }

  /**
   * @since 2.19
   */
  public JETCompiler(String[] templateURIPath, String relativeTemplateURI, String encoding, JETInputStreamHandler inputStreamHandler, JETProblemListener problemListener)
    throws JETException
  {
    super();

    this.templateURIPath = templateURIPath;
    this.templateURI = relativeTemplateURI;
    String[] actualTemplateURI = findLocation(templateURIPath, 0, relativeTemplateURI);
    this.inputStreamHandler = inputStreamHandler;
    this.reader = new JETReader(actualTemplateURI[1], relativeTemplateURI, openStream(actualTemplateURI[0], inputStreamHandler), encoding, problemListener);
    this.problemListener = problemListener;
  }

  /**
   * @since 2.19
   */
  public String getLineSeparator()
  {
    if (reader != null)
    {
      char[] stream = reader.current.stream;
      for (int i = 0; i < stream.length; ++i)
      {
        if (stream[i] == '\n')
        {
          if (i == 0 || stream[i - 1] != '\r')
          {
            return new String("\n");
          }
          else
          {
            return new String("\r\n");
          }
        }
      }
    }

    return System.getProperty("line.separator");
  }

  public String getResolvedTemplateURI()
  {
    return reader.getFile(0);
  }

  public void handleDirective(String directive, JETMark start, JETMark stop, Map<String, String> attributes) throws JETException
  {
    JETDirectiveItem jetDirectiveItem = (JETDirectiveItem)reader.popItem();
    jetItems.add(jetDirectiveItem);

    Map<String, JETAttributeItem> duplicates = new HashMap<String, JETAttributeItem>();
    Set<JETAttributeItem> reportedProblems = new HashSet<JETAttributeItem>();
    for (JETAttributeItem jetAttributeItem : jetDirectiveItem.getAttributes().getAttributes())
    {
      JETAttributeItem otherJETAttributeItem = duplicates.put(jetAttributeItem.getNameToken().getToken(), jetAttributeItem);
      if (otherJETAttributeItem != null)
      {
        reportedProblems.add(otherJETAttributeItem);
        reportedProblems.add(jetAttributeItem);
      }
    }

    for (JETAttributeItem jetAttributeItem : reportedProblems)
    {
      problemListener.handleProblem(
        jetAttributeItem.getStart(),
        jetAttributeItem.getStop(),
        Diagnostic.WARNING,
        null,
        JETProblemListener.BAD_ATTRIBUTE_DUPLICATE,
        jetAttributeItem.getNameToken().getToken(),
        jetAttributeItem.getStart().format("jet.mark.file.line.column"));
    }

    if (directive.equals("include"))
    {
      String fileURI = attributes.get("file");
      if (fileURI != null)
      {
        String currentURI = start.getFile();
        String[] resolvedFileURI = resolveLocation(templateURIPath, currentURI, fileURI);
        if (resolvedFileURI[0].equals(currentURI))
        {
          boolean loop = true;
          if (templateURIPath != null)
          {
            String baseURI = start.getBaseURI();
            if (baseURI != null)
            {
              for (int i = 0; i < templateURIPath.length; ++i)
              {
                if (baseURI.equals(templateURIPath[i]))
                {
                  resolvedFileURI = resolveLocation(templateURIPath, i + 1, currentURI, fileURI);
                  loop = false;
                }
              }
            }
          }
          if (loop)
          {
            // Break the cycle.
            //
            return;
          }
        }

        jetDirectiveItem.setData(JETDirectiveItem.RESOLVED_INCLUDE, resolvedFileURI[1]);

        try
        {
          BufferedInputStream bufferedInputStream = new BufferedInputStream(openStream(resolvedFileURI[1], inputStreamHandler));
          reader.stackStream(resolvedFileURI[2], resolvedFileURI[0], bufferedInputStream, null);

          JETMark includeStart = reader.mark();
          jetDirectiveItem.setData(JETDirectiveItem.RESOLVED_INCLUDE_FILE_ID, includeStart.getFileId());
          jetDirectiveItem.setData(JETDirectiveItem.RESOLVED_INCLUDE_START, new JETItem(includeStart, includeStart));
          jetDirectiveItem.setData(JETDirectiveItem.RESOLVED_INCLUDE_SUCCESS, Boolean.TRUE);

          jetDirectiveItem.setSkipped(skipping);

          // The include succeeded, so if there is an alternative and we're not skipping, we need to start.
          //
          if ("alternative".equals(attributes.get("fail")))
          {
            skipSections.push(new SkipSection(sectionDepth + 1, !skipping, jetDirectiveItem));
          }

          // If a newline from the previous character data remains, leave it around to be processed as if it appeared in the included file.
          //
          if (fSavedLine != null)
          {
            return;
          }
        }
        catch (JETException exception)
        {
          jetDirectiveItem.setData(JETDirectiveItem.RESOLVED_INCLUDE_SUCCESS, Boolean.FALSE);

          // The include failed, so if there is an alternative, we don't skip it.
          //
          String failType = attributes.get("fail");
          if ("alternative".equals(failType))
          {
            skipSections.push(new SkipSection(sectionDepth + 1, false, jetDirectiveItem));
          }
          else if (!"silent".equals(failType))
          {
            problemListener.handleProblem(start, stop, Diagnostic.ERROR, null, JETProblemListener.FILE_CANNOT_READ, resolvedFileURI[1], start.format("jet.mark.file.line.column"));
          }
        }
      }
      else
      {
        problemListener.handleProblem(start, stop, Diagnostic.ERROR, null, JETProblemListener.MISSING_ATTRIBUTE, "file", start.format("jet.mark.file.line.column"));
      }

      for (Map.Entry<String, String> entry : attributes.entrySet())
      {
        String key = entry.getKey();
        JETTokenItem attribute = jetDirectiveItem.getAttribute(key).getNameToken();

        if (!key.equals("file") && !key.equals("fail"))
        {
          problemListener.handleProblem(
            attribute.getStart(),
            attribute.getStop(),
            Diagnostic.WARNING,
            null,
            JETProblemListener.BAD_ATTRIBUTE,
            key,
            attribute.getStart().format("jet.mark.file.line.column"));
        }
      }
    }
    else if (directive.equals("start"))
    {
      sectionDepth++;

      // A section is not allowed without a preceding include with alternative.
      //
      SkipSection skipSection = skipSections.isEmpty() ? null : (SkipSection)skipSections.peek();
      if (skipSection == null || skipSection.depth != sectionDepth)
      {
        problemListener.handleProblem(start, stop, Diagnostic.ERROR, null, JETProblemListener.SECTION_NO_INCLUDE, start.format("jet.mark.file.line.column"));
        sectionDepth--;
      }
      else
      {
        if (skipSection.skip)
        {
          skipping = true;
          jetDirectiveItem.setSkipped(true);
        }
        else
        {
          jetDirectiveItem.setSkipped(skipping);
        }

        skipSection.jetDirectiveItem.setData(JETDirectiveItem.SECTION_START, jetDirectiveItem);
        jetDirectiveItem.setData(JETDirectiveItem.SECTION_OWNER, skipSection.jetDirectiveItem);
      }

      for (Map.Entry<String, String> entry : attributes.entrySet())
      {
        String key = entry.getKey();
        JETTokenItem attribute = jetDirectiveItem.getAttribute(key).getNameToken();
        problemListener.handleProblem(
          attribute.getStart(),
          attribute.getStop(),
          Diagnostic.WARNING,
          null,
          JETProblemListener.BAD_ATTRIBUTE,
          key,
          attribute.getStart().format("jet.mark.file.line.column"));
      }
    }
    else if (directive.equals("end"))
    {
      if (sectionDepth == 0)
      {
        problemListener.handleProblem(start, stop, Diagnostic.ERROR, null, JETProblemListener.UNMATCHED_DIRECTIVE, "start", "end", start.format("jet.mark.file.line.column"));
      }
      else
      {
        sectionDepth--;

        // This pop is safe because a section couldn't have been started without an include that pushed.
        //
        SkipSection skipSection = skipSections.pop();
        if (skipSection.skip)
        {
          jetDirectiveItem.setSkipped(true);
          skipping = false;
        }
        else
        {
          jetDirectiveItem.setSkipped(skipping);
        }

        skipSection.jetDirectiveItem.setData(JETDirectiveItem.SECTION_END, jetDirectiveItem);

        JETDirectiveItem startDirectiveItem = (JETDirectiveItem)skipSection.jetDirectiveItem.getData(JETDirectiveItem.SECTION_START);
        startDirectiveItem.setData(JETDirectiveItem.SECTION_OTHER_END, jetDirectiveItem);
        jetDirectiveItem.setData(JETDirectiveItem.SECTION_OTHER_END, startDirectiveItem);
        jetDirectiveItem.setData(JETDirectiveItem.SECTION_OWNER, skipSection.jetDirectiveItem);

        for (Map.Entry<String, String> entry : attributes.entrySet())
        {
          String key = entry.getKey();
          JETTokenItem attribute = jetDirectiveItem.getAttribute(key).getNameToken();
          problemListener.handleProblem(
            attribute.getStart(),
            attribute.getStop(),
            Diagnostic.WARNING,
            null,
            JETProblemListener.BAD_ATTRIBUTE,
            key,
            attribute.getStart().format("jet.mark.file.line.column"));
        }
      }
    }
    else if (directive.equals("jet"))
    {
      if (skeleton != null)
      {
        jetDirectiveItem.setData(JETDirectiveItem.SKELETON, skeleton);
        jetDirectiveItem.setData(JETDirectiveItem.DUPLICATE_JET_DIRECTIVE, Boolean.TRUE);
        jetDirectiveItem.setSkipped(true);
      }
      else
      {
        String lineSeparator = getLineSeparator();
        String builder = attributes.get("builder");
        skeleton = new JETSkeleton(lineSeparator, builder);

        // Process this first.
        //
        String skeletonURI = attributes.get("skeleton");
        if (skeletonURI != null)
        {
          JETTokenItem skeletonItem = jetDirectiveItem.getAttribute("skeleton").getValueToken();
          try
          {
            String skeletonLocation = resolveLocation(templateURIPath, templateURI, skeletonURI)[1];
            jetDirectiveItem.setData(JETDirectiveItem.RESOLVED_SKELETON, skeletonLocation);

            BufferedInputStream bufferedInputStream = new BufferedInputStream(openStream(skeletonLocation, inputStreamHandler));
            byte[] input = new byte [bufferedInputStream.available()];
            bufferedInputStream.read(input);
            bufferedInputStream.close();
            String skeletonEncoding = attributes.get("skeletonEncoding");
            skeleton.setCompilationUnitContents(skeletonEncoding == null ? new String(input) : new String(input, skeletonEncoding));

            if (!skeleton.isWellFormed())
            {
              problemListener.handleProblem(
                skeletonItem.getStart(),
                skeletonItem.getStop(),
                Diagnostic.ERROR,
                null,
                JETProblemListener.BAD_SKELETON,
                skeletonItem.getStart().format("jet.mark.file.line.column"));
              skeleton = new JETSkeleton(lineSeparator, builder);
            }
          }
          catch (IOException exception)
          {
            problemListener.handleProblem(
              skeletonItem.getStart(),
              skeletonItem.getStop(),
              Diagnostic.ERROR,
              null,
              JETProblemListener.BAD_SKELETON,
              skeletonItem.getStart().format("jet.mark.file.line.column"));
          }
          catch (JETException exception)
          {
            problemListener.handleProblem(
              skeletonItem.getStart(),
              skeletonItem.getStop(),
              Diagnostic.ERROR,
              exception,
              JETProblemListener.BAD_SKELETON,
              skeletonItem.getStart().format("jet.mark.file.line.column"));
          }
        }

        jetDirectiveItem.setData(JETDirectiveItem.SKELETON, skeleton);

        for (Map.Entry<String, String> entry : attributes.entrySet())
        {
          String key = entry.getKey();

          // Ignore this now
          //
          if (key.equals("skeleton"))
          {
            // Ignore
          }
          else if (key.equals("package"))
          {
            String name = entry.getValue();
            Diagnostic diagnostic = CodeGenUtil.validatePackageName(name, reader.getResolvedURI(0));
            if (diagnostic.getSeverity() != Diagnostic.OK)
            {
              JETAttributeItem attribute = jetDirectiveItem.getAttribute("package");
              JETItem valueItem = attribute.getValueToken().getValueItem();

              problemListener.handleProblem(
                valueItem.getStart(),
                valueItem.getStop(),
                diagnostic.getSeverity(),
                null,
                JETProblemListener.BAD_ATTRIBUTE_VALUE,
                "package",
                valueItem.getText(),
                diagnostic.getMessage(),
                valueItem.getStart().format("jet.mark.file.line.column"));

              StringBuilder validQualifiedName = new StringBuilder();
              for (String component : name.split("\\."))
              {
                String validJavaIdentifier = CodeGenUtil.validJavaIdentifier(component);
                if (validJavaIdentifier.length() == 0 || validJavaIdentifier.equals("_"))
                {
                  validJavaIdentifier = "__";
                }
                if (validQualifiedName.length() != 0)
                {
                  validQualifiedName.append('.');
                }
                validQualifiedName.append(validJavaIdentifier);
              }
              name = validQualifiedName.toString();
            }

            skeleton.setPackageName(name);
          }
          else if (key.equals("imports"))
          {
            skeleton.addImports(entry.getValue());
          }
          else if (key.equals("class"))
          {
            String name = entry.getValue();
            Diagnostic diagnostic = CodeGenUtil.validateClassName(name, reader.getResolvedURI(0));
            if (diagnostic.getSeverity() != Diagnostic.OK)
            {
              JETAttributeItem attribute = jetDirectiveItem.getAttribute("class");
              JETItem valueItem = attribute.getValueToken().getValueItem();

              problemListener.handleProblem(
                valueItem.getStart(),
                valueItem.getStop(),
                diagnostic.getSeverity(),
                null,
                JETProblemListener.BAD_ATTRIBUTE_VALUE,
                "class",
                valueItem.getText(),
                diagnostic.getMessage(),
                valueItem.getStart().format("jet.mark.file.line.column"));

              name = CodeGenUtil.validJavaIdentifier(name);
              if (name.length() == 0 || name.equals("_"))
              {
                name = "__";
              }
            }

            skeleton.setClassName(name);
          }
          else if (key.equals("nlString"))
          {
            skeleton.setNLString(entry.getValue());
          }
          else if (key.equals("startTag"))
          {
            parser.setStartTag(entry.getValue());
          }
          else if (key.equals("minimize"))
          {
            if (constants.isEmpty())
            {
              useMinimizedConstants = "true".equals(entry.getValue());
            }
          }
          else if (key.equals("endTag"))
          {
            parser.setEndTag(entry.getValue());
          }
          else if (key.equals("builder"))
          {
            if (JETSkeleton.getBuilderName(entry.getValue()) == null)
            {
              JETValueItem valueItem = jetDirectiveItem.getAttribute(key).getValueToken().getValueItem();
              problemListener.handleProblem(
                valueItem.getStart(),
                valueItem.getStop(),
                Diagnostic.ERROR,
                null,
                JETProblemListener.BAD_BUILDER,
                valueItem.getStart().format("jet.mark.file.line.column"));
            }
          }
          else if (key.equals("version"))
          {
            // Ignore the version
          }
          else
          {
            JETTokenItem attribute = jetDirectiveItem.getAttribute(key).getNameToken();
            problemListener.handleProblem(
              attribute.getStart(),
              attribute.getStop(),
              Diagnostic.WARNING,
              null,
              JETProblemListener.BAD_ATTRIBUTE,
              key,
              attribute.getStart().format("jet.mark.file.line.column"));
          }
        }

        handleNewSkeleton();
      }
    }

    fSavedLine = null;
  }

  protected void handleNewSkeleton()
  {
    // Do nothing
  }

  /**
   * @since 2.19
   */
  public void handleComment(JETMark start, JETMark stop) throws JETException
  {
    JETCommentItem jetCommentItem = new JETCommentItem(start, stop);
    jetItems.add(jetCommentItem);
    if (skipping)
    {
      jetCommentItem.setSkipped(true);
    }
    else
    {
      fSavedLine = null;
    }
  }

  public void handleExpression(JETMark start, JETMark stop, Map<String, String> attributes) throws JETException
  {
    JETExpressionItem jetExpressionItem = new JETExpressionItem(start, stop);
    jetItems.add(jetExpressionItem);
    if (skipping)
    {
      jetExpressionItem.setSkipped(true);
    }
    else
    {
      JETGenerator gen = new JETExpressionGenerator(reader.getChars(start, stop), start, stop, jetExpressionItem);
      addGenerator(gen);
    }
  }

  public void handleScriptlet(JETMark start, JETMark stop, Map<String, String> attributes) throws JETException
  {
    JETScriptletItem jetScriptletItem = new JETScriptletItem(start, stop);
    jetItems.add(jetScriptletItem);

    if (skipping)
    {
      jetScriptletItem.setSkipped(true);
    }
    else
    {
      fSavedLine = null;
      JETGenerator gen = new JETScriptletGenerator(reader.getChars(start, stop), start, stop, jetScriptletItem);
      addGenerator(gen);
    }
  }

  public void handleCharData(char[] chars) throws JETException
  {
    currentLiteralItem = (JETLiteralItem)reader.popItem();
    List<JETLiteralItem> explode = currentLiteralItem.explode();
    jetItems.addAll(explode);

    if (skipping)
    {
      currentLiteralItem.setSkipped(true);
    }
    else
    {
      if (fSavedLine != null)
      {
        JETLiteralItem oldCurrentLiteralItem = currentLiteralItem;
        currentLiteralItem = savedLineLiteralItem;
        addCharDataGenerator(fSavedLine);
        currentLiteralItem = oldCurrentLiteralItem;
        fSavedLine = null;
        savedLineLiteralItem = null;
      }

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
      LOOP: for (int i = 0; i < chars.length; ++i)
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
                char[] block = new char [size];
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
        char[] block = new char [size];
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
      JETConstantDataGenerator generator = constantDictionary.get(chars);
      if (generator == null)
      {
        if (constantCount == 0)
        {
          chars = stripFirstNewLineWithBlanks(chars);
        }

        ++constantCount;
        String label = CONSTANT_PREFIX + constantCount;

        generator = useMinimizedConstants ? new JETExtendedConstantDataGenerator(chars, label, constantSubstitutitons) : new JETConstantDataGenerator(chars, label);
        generator.setLiteralItem(currentLiteralItem);
        constantDictionary.put(chars, generator);
        constants.add(generator);
      }
      else
      {
        generator.copy().setLiteralItem(currentLiteralItem);
      }

      generators.add(generator);
    }
    else
    {
      JETCharDataGenerator generator = new JETCharDataGenerator(chars);
      generator.setLiteralItem(currentLiteralItem);
      generators.add(generator);
    }
  }

  protected char[] stripFirstNewLineWithBlanks(char[] chars)
  {
    if (chars.length >= 2 && (chars[0] == '\n' && chars[1] == '\r' || chars[0] == '\r' && chars[1] == '\n'))
    {
      chars = new String(chars, 2, chars.length - 2).toCharArray();
    }
    else if (chars.length >= 1 && (chars[0] == '\n' || chars[0] == '\r'))
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

      fSavedLine = new String(chars, i, chars.length - i).toCharArray();
      List<JETLiteralItem> explode = currentLiteralItem.explode();
      savedLineLiteralItem = explode.get(explode.size() - 1);

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
    pageStart = reader.mark();
  }

  public void endPageProcessing() throws JETException
  {
    if (sectionDepth > 0)
    {
      SkipSection skipSection = skipSections.peek();
      JETDirectiveItem include = skipSection.jetDirectiveItem;
      JETDirectiveItem startItem = (JETDirectiveItem)include.getData(JETDirectiveItem.SECTION_START);
      problemListener.handleProblem(
        startItem.getStart(),
        startItem.getStop(),
        Diagnostic.ERROR,
        null,
        JETProblemListener.UNMATCHED_DIRECTIVE,
        "end",
        "start",
        startItem.getStart().format("jet.mark.file.line.column"));
    }

    if (skeleton == null)
    {
      if (inputStreamHandler instanceof JETCompilerResultMonitor)
      {
        // Only recover if we are monitoring the result.
        URI uri = URI.createURI(templateURI);
        String fileExtension = uri.fileExtension();
        if (fileExtension == null || !fileExtension.endsWith("jet"))
        {
          problemListener.handleProblem(pageStart, pageStart, Diagnostic.WARNING, null, JETProblemListener.FILE_NOT_A_TEMPLATE, pageStart.format("jet.mark.file.line.column"));
        }
        else
        {
          problemListener.handleProblem(pageStart, pageStart, Diagnostic.ERROR, null, JETProblemListener.MISSING_JET_DIRECTIVE, pageStart.format("jet.mark.file.line.column"));
        }

        skeleton = new JETSkeleton(getLineSeparator(), null);

        String fileName = uri.trimFileExtension().lastSegment();
        String fakeClassName = "__";
        if (fileName != null)
        {
          fakeClassName = CodeGenUtil.validJavaIdentifier(fileName);
        }

        if (fakeClassName.length() == 0 || fakeClassName.equals("_"))
        {
          fakeClassName = "__";
        }
        else
        {
          fakeClassName = CodeGenUtil.capName(fakeClassName);
        }

        skeleton.setClassName(fakeClassName);

        // Create a fake empty jet directive at the start of the page.
        JETSubItem nameItem = new JETSubItem(pageStart, pageStart)
          {
            @Override
            public String getText()
            {
              return "jet";
            }
          };

        JETAttributeListItem attributeList = new JETAttributeListItem(pageStart, pageStart, Collections.<JETAttributeItem> emptyList());
        JETDirectiveItem fakeJETDirective = new JETDirectiveItem(pageStart, pageStart, nameItem, attributeList);
        fakeJETDirective.setData(JETDirectiveItem.MISSING_JET_DIRECTIVE, Boolean.TRUE);
        fakeJETDirective.setData(JETDirectiveItem.SKELETON, skeleton);
        jetItems.add(fakeJETDirective);
      }
      else
      {
        // Without cancel severity we would generate a bogus CLASS.java files.
        problemListener.handleProblem(pageStart, pageStart, Diagnostic.CANCEL, null, JETProblemListener.MISSING_JET_DIRECTIVE, pageStart.format("jet.mark.file.line.column"));
      }
    }

    // If a newline from the previous character data remains, add a generator for it.
    //
    if (fSavedLine != null)
    {
      addCharDataGenerator(fSavedLine);
    }

    JETItem jetItem = jetItems.get(0);
    if (jetItem instanceof JETCommentItem)
    {
      String comment = jetItem.getText();
      int length = comment.length();
      if (length > 4 && comment.charAt(0) == '-' && (comment.charAt(1) == '\r' || comment.charAt(1) == '\n') && comment.charAt(length - 1) == '-'
          && comment.charAt(length - 2) == '\n')
      {
        String subComment = comment.substring(2, length - 2).trim();
        String header = subComment;
        if (header.length() != 0)
        {
          JETCommentItem jetCommentItem = (JETCommentItem)jetItem;
          Matcher matcher = LINE_PATTERN.matcher(comment);
          matcher.find(subComment.charAt(0) == '\n' ? 4 : 3);
          do
          {
            jetCommentItem.addLineItem(matcher.start(1), matcher.end(1));
          }
          while (matcher.find());
          skeleton.setHeader(header);
        }
      }
    }

    List<String> generatedConstants = new ArrayList<String>(constants.size());
    String builderName = skeleton.getBuilderName();
    if (useMinimizedConstants)
    {
      final Set<String> newLines = new TreeSet<String>();
      final Set<String> literals = new TreeSet<String>();

      JETExtendedConstantDataGenerator.Analzyer analyzer = new JETExtendedConstantDataGenerator.Analzyer()
        {
          public void handleNewLine(String newLine)
          {
            newLines.add(newLine);
          }

          public void handleLiteral(String literal)
          {
            literals.add(literal);
          }
        };

      @SuppressWarnings("unchecked")
      List<JETExtendedConstantDataGenerator> extendedConstantDataGenerators = (List<JETExtendedConstantDataGenerator>)(List<?>)constants;
      for (JETExtendedConstantDataGenerator extendedConstantDataGenerator : extendedConstantDataGenerators)
      {
        extendedConstantDataGenerator.analyze(analyzer);
      }

      int literalCount = 0;
      if (!literals.isEmpty())
      {
        for (String literal : literals)
        {
          String label = "_" + ++literalCount;
          constantSubstitutitons.put(literal, label);
          generatedConstants.add(JETConstantDataGenerator.generateConstant(true, label, '"' + literal + '"'));
        }
      }

      newLines.remove("\n");
      constantSubstitutitons.put("\n", "NL");

      if (!newLines.isEmpty())
      {
        int nlCount = 0;
        for (String newLine : newLines)
        {
          String label = "NL_" + ++nlCount;
          constantSubstitutitons.put(newLine, label);
          generatedConstants.add(new JETConstantDataGenerator(newLine.toCharArray(), label).generateConstant());
        }
      }

      final List<String> mappings = new ArrayList<String>();
      JETExtendedConstantDataGenerator.Analzyer countingAnalyzer = new JETExtendedConstantDataGenerator.Analzyer()
        {
          public void handleNewLine(String newLine)
          {
            mappings.add(newLine);
          }

          public void handleLiteral(String literal)
          {
            mappings.add(literal);
          }
        };

      for (JETExtendedConstantDataGenerator extendedConstantDataGenerator : extendedConstantDataGenerators)
      {
        mappings.clear();
        extendedConstantDataGenerator.analyze(countingAnalyzer);
        if (mappings.size() == 1)
        {
          extendedConstantDataGenerator.setLabel(constantSubstitutitons.get(mappings.get(0)));
        }
        else
        {
          String label = "_" + ++literalCount;
          extendedConstantDataGenerator.setLabel(label);
          generatedConstants.add(extendedConstantDataGenerator.generateConstant());
        }
      }
    }
    else
    {
      for (JETConstantDataGenerator jetConstantDataGenerator : constants)
      {
        generatedConstants.add(jetConstantDataGenerator.generateConstant());
      }
    }

    skeleton.setConstants(generatedConstants);

    List<String> generatedBody = new ArrayList<String>(generators.size());
    for (JETGenerator jetGenerator : generators)
    {
      if (jetGenerator instanceof JETGenerator.BuilderSensitive)
      {
        ((JETGenerator.BuilderSensitive)jetGenerator).setBuilderName(builderName);
      }
      generatedBody.add(jetGenerator.generate());
    }

    skeleton.setBody(generatedBody);

    String compilationUnitContents = skeleton.getCompilationUnitContents();
    writer.print(compilationUnitContents);

    if (inputStreamHandler instanceof JETCompilerResultMonitor)
    {
      ((JETCompilerResultMonitor)inputStreamHandler).setResult(getResult());
    }
  }

  /**
   * @since 2.19
   */
  public JETCompilationUnit getResult()
  {
    List<String> templateURIs = new ArrayList<String>(reader.resolvedURIs);
    return skeleton.getCompilerResult(templateURIs, generators, jetItems, problemListener.getProblems());
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

    JETCoreElement[] coreElements = { directive, new JETParser.QuoteEscape(), new JETParser.Comment(), new JETParser.Expression(), new JETParser.Scriptlet() };

    // Given we don't want to filter, it seems pointless to pass in a filter that includes all the core elements.
    // Class<?>[] accept = { JETParser.Directive.class, JETParser.QuoteEscape.class, JETParser.Expression.class, JETParser.Scriptlet.class };
    // parse(coreElements, accept);
    //
    parse(coreElements, null);
  }

  protected void parse(JETCoreElement[] coreElements, Class<?>[] accept) throws JETException
  {
    parser = new JETParser(reader, this, coreElements, problemListener);
    beginPageProcessing();
    parser.parse(null, accept);
  }

  public void generate(OutputStream oStream) throws JETException
  {
    writer = new PrintWriter(oStream);
    endPageProcessing();
    writer.close();
  }

  public void generate(Writer writer) throws JETException
  {
    this.writer = new PrintWriter(writer);
    endPageProcessing();
    this.writer.close();
  }

  public JETSkeleton getSkeleton()
  {
    return skeleton;
  }

  protected static String[] resolveLocation(String[] templateURIPath, String baseLocationURI, String locationURI)
  {
    return resolveLocation(templateURIPath, 0, baseLocationURI, locationURI);
  }

  protected static String[] resolveLocation(String[] templateURIPath, int start, String baseLocationURI, String locationURI)
  {
    String[] result = new String []{ locationURI, locationURI, null };
    URI uri = URI.createURI(locationURI);
    try
    {
      CommonUtil.newURL(locationURI);
      uri = resolve(uri);
    }
    catch (MalformedURLException exception)
    {
      // Ignore
    }

    if (uri.isRelative() && uri.hasRelativePath())
    {
      String resolvedLocation = "";
      int index = baseLocationURI.lastIndexOf("/");
      if (index != -1)
      {
        resolvedLocation = baseLocationURI.substring(0, index + 1);
      }
      resolvedLocation = resolve(resolvedLocation, uri.toString());
      result[0] = resolvedLocation;
      if (templateURIPath != null)
      {
        String[] location = findLocation(templateURIPath, start, resolvedLocation);
        resolvedLocation = location[0];
        result[2] = location[1];
      }
      if (resolvedLocation != null)
      {
        result[1] = resolvedLocation;
      }
    }

    return result;
  }

  public static String[] findLocation(String[] locationURIPath, int start, String relativeLocationURI)
  {
    return findLocation(locationURIPath, start, relativeLocationURI, null);
  }

  /**
   * @since 2.19
   */
  public static String[] findLocation(String[] locationURIPath, int start, String relativeLocationURI, JETInputStreamHandler inputStreamHandler)
  {
    String[] result = { null, null };
    for (int i = start; i < locationURIPath.length; ++i)
    {
      result[0] = locationURIPath[i];
      result[1] = locationURIPath[i];

      if (result[0] != null)
      {
        try
        {
          if (!result[0].endsWith("/"))
          {
            result[0] += "/";
          }
          result[0] = resolve(result[0], relativeLocationURI);

          InputStream inputStream = openStream(result[0], inputStreamHandler);
          inputStream.close();
          break;
        }
        catch (JETException exception)
        {
          result[0] = null;
        }
        catch (IOException exception)
        {
          result[0] = null;
        }
      }
    }
    return result;
  }

  private static String resolve(String base, String relativePath)
  {
    while (relativePath.startsWith("./"))
    {
      relativePath = relativePath.substring(2);
    }

    while (relativePath.startsWith("../"))
    {
      int index = base.lastIndexOf('/', base.length() - 2);
      if (index == -1)
      {
        base = "";
        relativePath = relativePath.substring(3);
        break;
      }
      else
      {
        base = base.substring(0, index);
        relativePath = relativePath.substring(3);
      }
    }

    return base + relativePath;
  }

  public static String find(String[] locationURIPath, String relativeLocationURI)
  {
    return findLocation(locationURIPath, 0, relativeLocationURI)[0];
  }

  public static InputStream openStream(String locationURI) throws JETException
  {
    return openStream(locationURI, null);
  }

  /**
   * @since 2.19
   */
  public static InputStream openStream(String locationURI, JETInputStreamHandler inputStreamHandler) throws JETException
  {
    if (inputStreamHandler != null)
    {
      return inputStreamHandler.openInputStream(locationURI);
    }

    try
    {
      URI uri = URI.createURI(locationURI);
      URL url;
      try
      {
        uri = resolve(uri);
        url = CommonUtil.newURL(uri.toString());
      }
      catch (MalformedURLException exception)
      {
        url = CommonUtil.newURL("file:" + locationURI);
      }

      URI resolvedURI;
      try
      {
        resolvedURI = URI.createURI(url.toURI().toString());
      }
      catch (URISyntaxException exception)
      {
        resolvedURI = URI.createURI(url.toString());
      }

      if (resolvedURI.isFile())
      {
        String file = resolvedURI.toFileString();
        if (new File(file).isDirectory())
        {
          throw new IOException("Cannot read a folder:" + file);
        }
      }

      BufferedInputStream bufferedInputStream = new BufferedInputStream(url.openStream());
      return bufferedInputStream;
    }
    catch (IOException exception)
    {
      throw new JETException(JETProblemListener.UNKNOWN_PROBLEM, exception.getLocalizedMessage(), exception, null, null, Diagnostic.ERROR);
    }
  }

  private static URI resolve(URI uri)
  {
    if (EMFPlugin.IS_ECLIPSE_RUNNING)
    {
      return JETNature.resolve(uri);
    }
    else
    {
      return CommonPlugin.resolve(uri);
    }
  }

  /**
   * @since 2.19
   */
  public static class JETInputStreamHandler
  {
    public InputStream openInputStream(String locationURI) throws JETException
    {
      return JETCompiler.openStream(locationURI, null);
    }
  }

  /**
   * @since 2.19
   */
  public interface JETCompilerResultMonitor
  {
    public void setResult(JETCompilationUnit result);
  }
}
