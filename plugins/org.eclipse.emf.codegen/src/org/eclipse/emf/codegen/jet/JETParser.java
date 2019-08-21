/**
 * Copyright (c) 2002-2007 IBM Corporation and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 *
 * Contributors:
 *   IBM - Initial API and implementation
 *
 * The Apache Software License, Version 1.1
 *
 * Copyright (c) 1999 The Apache Software Foundation.  All rights
 * reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions
 * are met:
 *
 * 1. Redistributions of source code must retain the above copyright
 *    notice, this list of conditions and the following disclaimer.
 *
 * 2. Redistributions in binary form must reproduce the above copyright
 *    notice, this list of conditions and the following disclaimer in
 *    the documentation and/or other materials provided with the
 *    distribution.
 *
 * 3. The end-user documentation included with the redistribution, if
 *    any, must include the following acknowledgment:
 *       "This product includes software developed by the
 *        Apache Software Foundation (http://www.apache.org/)."
 *    Alternately, this acknowledgment may appear in the software itself,
 *    if and wherever such third-party acknowledgments normally appear.
 *
 * 4. The names "The Jakarta Project", "Tomcat", and "Apache Software
 *    Foundation" must not be used to endorse or promote products derived
 *    from this software without prior written permission. For written
 *    permission, please contact apache@apache.org.
 *
 * 5. Products derived from this software may not be called "Apache"
 *    nor may "Apache" appear in their names without prior written
 *    permission of the Apache Group.
 *
 * THIS SOFTWARE IS PROVIDED ``AS IS'' AND ANY EXPRESSED OR IMPLIED
 * WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES
 * OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 * DISCLAIMED.  IN NO EVENT SHALL THE APACHE SOFTWARE FOUNDATION OR
 * ITS CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL,
 * SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT
 * LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF
 * USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND
 * ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY,
 * OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT
 * OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF
 * SUCH DAMAGE.
 * ====================================================================
 *
 * This software consists of voluntary contributions made by many
 * individuals on behalf of the Apache Software Foundation.  For more
 * information on the Apache Software Foundation, please see
 * <http://www.apache.org/>.
 */
package org.eclipse.emf.codegen.jet;


import java.io.ByteArrayInputStream;
import java.io.CharArrayWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.eclipse.core.runtime.Assert;
import org.eclipse.emf.common.util.Diagnostic;


/**
 * This class and all those in this package is work derived from contributions of multiple authors as listed below.
 * Credit for all that is good is shared, responsibility for any problems lies solely with the latest authors.
 *
 * @author Anil K. Vijendran
 * @author Anselm Baird-Smith
 * @author David Charboneau
 * @author Harish Prabandham
 * @author Kevin Bauer
 * @author Mandar Raje
 * @author Paul R. Hoffman
 * @author Rajiv Mordani
 */
public class JETParser
{
  public interface Action
  {
    void execute() throws JETException;
  }

  public static class DelegatingListener implements JETParseEventListener, JETParseEventListener.CommentListener
  {
    protected JETParseEventListener delegate;

    protected JETParser.Action action;

    public DelegatingListener(JETParseEventListener delegate, JETParser.Action action)
    {
      this.delegate = delegate;
      this.action = action;
    }

    public void doAction() throws JETException
    {
      action.execute();
    }

    public void beginPageProcessing() throws JETException
    {
      delegate.beginPageProcessing();
    }

    public void endPageProcessing() throws JETException
    {
      delegate.endPageProcessing();
    }

    public void handleDirective(String directive, JETMark start, JETMark stop, Map<String, String> attrs) throws JETException
    {
      doAction();
      delegate.handleDirective(directive, start, stop, attrs);
    }

    public void handleScriptlet(JETMark start, JETMark stop, Map<String, String> attrs) throws JETException
    {
      doAction();
      delegate.handleScriptlet(start, stop, attrs);
    }

    public void handleExpression(JETMark start, JETMark stop, Map<String, String> attrs) throws JETException
    {
      doAction();
      delegate.handleExpression(start, stop, attrs);
    }

    public void handleCharData(char[] chars) throws JETException
    {
      delegate.handleCharData(chars);
    }

    /**
     * @since 2.19
     */
    public void handleComment(JETMark start, JETMark stop) throws JETException
    {
      doAction();
      if (delegate instanceof CommentListener)
      {
        ((CommentListener)delegate).handleComment(start, stop);
      }
    }
  }

  /**
   * The input source we read from...
   */
  protected JETReader reader;

  /**
   * The backend that is notified of constructs recognized in the input...
   */
  protected JETParseEventListener listener;

  /**
   * The backend that is notified of recoverable problems.
   *
   * @since 2.19
   */
  protected JETProblemListener problemListener;

  /*
   * Char buffer for HTML data
   */
  protected CharArrayWriter writer;

  /**
   * @since 2.19
   */
  protected JETMark writerStart;

  /**
   * @since 2.19
   */
  protected JETMark writerStop;

  /**
   * @since 2.19
   */
  protected final List<JETMark> writerFileTransitions = new ArrayList<JETMark>();

  protected List<JETCoreElement> coreElements = new ArrayList<JETCoreElement>();

  protected String openDirective = "<%@";

  protected String closeDirective = "%>";

  protected String openScriptlet = "<%";

  protected String closeScriptlet = "%>";

  protected String openExpr = "<%=";

  protected String closeExpr = "%>";

  protected String quotedStartTag = "<\\%";

  protected String quotedEndTag = "%\\>";

  protected String startTag = "<%";

  protected String endTag = "%>";

  /**
   * @since 2.19
   */
  protected String openComment = "<%-";

  /**
   * @since 2.19
   */
  protected String closeComment = "-%>";

  /**
   * @deprecated
   */
  @Deprecated
  public JETParser(JETReader reader, JETParseEventListener parseEventListener, JETCoreElement[] coreElements)
  {
    this(reader, parseEventListener, coreElements, new JETProblemListener());
  }

  /**
   * @since 2.19
   */
  public JETParser(JETReader reader, JETParseEventListener parseEventListener, JETCoreElement[] coreElements, JETProblemListener problemListener)
  {
    this.reader = reader;
    this.problemListener = problemListener;
    this.listener = new DelegatingListener(parseEventListener, new Action()
      {
        public void execute() throws JETException
        {
          JETParser.this.flushCharData();
        }
      });

    this.writer = new CharArrayWriter();

    for (int i = 0; i < coreElements.length; i++)
    {
      this.coreElements.add(coreElements[i]);
    }
  }

  /**
   * @since 2.19
   */
  public JETProblemListener getProblemListener()
  {
    return problemListener;
  }

  public JETReader getReader()
  {
    return reader;
  }

  public void setStartTag(String tag)
  {
    openScriptlet = tag;
    openExpr = tag + "=";
    openDirective = tag + "@";
    openComment = tag + "-";
    quotedStartTag = tag.charAt(0) + "\\" + tag.charAt(1);
    startTag = tag;
    reader.setStartTag(tag);
  }

  public void setEndTag(String tag)
  {
    closeScriptlet = tag;
    closeExpr = tag;
    closeDirective = tag;
    closeComment = "-" + tag;
    quotedEndTag = tag.charAt(0) + "\\" + tag.charAt(1);
    endTag = tag;
    reader.setEndTag(tag);
  }

  /**
   * @since 2.19
   */
  public String getOpenComment()
  {
    return openComment;
  }

  /**
   * @since 2.19
   */
  public String getCloseComment()
  {
    return closeComment;
  }

  public String getOpenScriptlet()
  {
    return openScriptlet;
  }

  public String getCloseScriptlet()
  {
    return closeScriptlet;
  }

  public String getOpenExpr()
  {
    return openExpr;
  }

  public String getCloseExpr()
  {
    return closeExpr;
  }

  public String getOpenDirective()
  {
    return openDirective;
  }

  public String getCloseDirective()
  {
    return closeDirective;
  }

  public String getQuotedStartTag()
  {
    return quotedStartTag;
  }

  public String getQuotedEndTag()
  {
    return quotedEndTag;
  }

  public String getStartTag()
  {
    return startTag;
  }

  public String getEndTag()
  {
    return endTag;
  }

  /**
   * @since 2.19
   */
  public static class Comment implements JETCoreElement
  {
    public boolean accept(JETParseEventListener listener, JETReader reader, JETParser parser) throws JETException
    {
      String close, open;

      if (reader.matches(parser.getOpenComment()))
      {
        open = parser.getOpenComment();
        close = parser.getCloseComment();
      }
      else
      {
        return false;
      }

      reader.advance(open.length());

      JETMark start = reader.mark();
      JETMark stop = reader.skipUntil(close);

      if (stop == null)
      {
        stop = reader.mark();
        parser.getProblemListener().handleProblem(start, start, Diagnostic.ERROR, null, JETProblemListener.UNTERMINATED, open, start.format("jet.mark.file.line.column"));
      }

      if (listener instanceof JETParseEventListener.CommentListener)
      {
        ((JETParseEventListener.CommentListener)listener).handleComment(start, stop);
      }
      return true;
    }
  }

  public static class Scriptlet implements JETCoreElement
  {
    public boolean accept(JETParseEventListener listener, JETReader reader, JETParser parser) throws JETException
    {
      String close, open;

      if (reader.matches(parser.getOpenScriptlet()))
      {
        open = parser.getOpenScriptlet();
        close = parser.getCloseScriptlet();
      }
      else
      {
        return false;
      }

      reader.advance(open.length());

      JETMark start = reader.mark();
      JETMark stop = reader.skipUntil(close);

      if (stop == null)
      {
        stop = reader.mark();
        parser.getProblemListener().handleProblem(start, start, Diagnostic.ERROR, null, JETProblemListener.UNTERMINATED, open, start.format("jet.mark.file.line.column"));
      }

      listener.handleScriptlet(start, stop, null);
      return true;
    }
  }

  public static class Expression implements JETCoreElement
  {
    public boolean accept(JETParseEventListener listener, JETReader reader, JETParser parser) throws JETException
    {
      String close, open;
      Map<String, String> attrs = null;

      if (reader.matches(parser.getOpenExpr()))
      {
        open = parser.getOpenExpr();
        close = parser.getCloseExpr();
      }
      else
      {
        return false;
      }

      reader.advance(open.length());

      JETMark start = reader.mark();
      JETMark stop = reader.skipUntil(close);
      if (stop == null)
      {
        stop = reader.mark();
        parser.getProblemListener().handleProblem(start, start, Diagnostic.ERROR, null, JETProblemListener.UNTERMINATED, open, start.format("jet.mark.file.line.column"));
      }

      listener.handleExpression(start, stop, attrs);
      return true;
    }
  }

  /**
   * Quoting in template text.
   * Entities &apos; and &quote;
   */
  public static class QuoteEscape implements JETCoreElement
  {
    /**
     * constants for escapes
     */
    protected static final String APOS = "&apos;";

    protected static final String QUOTE = "&quote;";

    public boolean accept(JETParseEventListener listener, JETReader reader, JETParser parser) throws JETException
    {
      try
      {
        if (reader.matches(parser.getQuotedEndTag()))
        {
          parser.writerStart = reader.mark();
          reader.advance(parser.getQuotedEndTag().length());
          parser.writer.write(parser.getEndTag());
          parser.writerStop = reader.mark();
          parser.flushCharData();
          return true;
        }
        else if (reader.matches(APOS))
        {
          parser.writerStart = reader.mark();
          reader.advance(APOS.length());
          parser.writer.write("\'");
          parser.flushCharData();
          parser.writerStop = reader.mark();
          return true;
        }
        else if (reader.matches(QUOTE))
        {
          parser.writerStart = reader.mark();
          reader.advance(QUOTE.length());
          parser.writer.write("\"");
          parser.flushCharData();
          parser.writerStop = reader.mark();
          return true;
        }
      }
      catch (IOException exception)
      {
        throw new JETException(exception);
      }

      return false;
    }
  }

  public static class Directive implements JETCoreElement
  {
    protected Collection<String> directives = new ArrayList<String>();

    public boolean accept(JETParseEventListener listener, JETReader reader, JETParser parser) throws JETException
    {
      String open = parser.getOpenDirective();
      if (reader.matches(open))
      {
        JETMark start = reader.mark();
        reader.advance(open.length());
        reader.skipSpaces();

        // Check which directive it is.
        //
        String match = null;
        JETMark directiveNameStart = reader.mark();
        for (String directive : directives)
        {
          if (reader.matches(directive, true))
          {
            match = directive;
            break;
          }
        }

        if (match == null)
        {
          while (Character.isLetterOrDigit(reader.peekChar()))
          {
            reader.nextChar();
          }

          JETMark directiveNameStop = reader.mark();
          char[] badDirectiveName = reader.getChars(directiveNameStart, directiveNameStop);

          parser.getProblemListener().handleProblem(
            directiveNameStart,
            directiveNameStop,
            Diagnostic.WARNING,
            null,
            JETProblemListener.BAD_DIRECTIVE,
            new String(badDirectiveName),
            start.format("jet.mark.file.line.column"));

          // Recover from the unrecognized name.
          match = new String(badDirectiveName);
        }
        else
        {
          reader.advance(match.length());
        }

        JETMark directiveNameStop = reader.mark();
        JETSubItem directiveName = new JETSubItem(directiveNameStart, directiveNameStop);

        // Parse the attr-val pairs.
        //
        Map<String, String> attrs = reader.parseTagAttributes();
        JETAttributeListItem attributeList = (JETAttributeListItem)reader.popItem();

        // Match close.
        reader.skipSpaces();
        String close = parser.getCloseDirective();
        if (!reader.matches(close))
        {
          parser.getProblemListener().handleProblem(start, start, Diagnostic.ERROR, null, JETProblemListener.UNTERMINATED, open, start.format("jet.mark.file.line.column"));
        }
        else
        {
          reader.advance(close.length());
        }

        JETMark stop = reader.mark();
        reader.pushItem(new JETDirectiveItem(start, stop, directiveName, attributeList));
        listener.handleDirective(match, start, stop, attrs);
        return true;
      }
      else
      {
        return false;
      }
    }

    public Collection<String> getDirectives()
    {
      return directives;
    }
  }

  protected void flushCharData() throws JETException
  {
    char[] array = writer.toCharArray();
    // Avoid unnecessary out.write("") statements...
    // Also avoid unnecessary literal items...
    if (array.length != 0)
    {
      JETLiteralItem jetLiteralItem = new JETLiteralItem(writerStart, writerStop, writerFileTransitions);
      reader.pushItem(jetLiteralItem);

      listener.handleCharData(array);
      writer = new CharArrayWriter();
    }

    writerStart = writerStop = null;
    writerFileTransitions.clear();
  }

  public void parse() throws JETException
  {
    parse(null);
  }

  public void parse(String until) throws JETException
  {
    parse(until, null);
  }

  public void parse(String until, Class<?>[] accept) throws JETException
  {
    while (reader.hasMoreInput())
    {
      if (until != null && reader.matches(until))
      {
        return;
      }

      Iterator<JETCoreElement> e = coreElements.iterator();

      if (accept != null)
      {
        List<JETCoreElement> v = new ArrayList<JETCoreElement>();
        while (e.hasNext())
        {
          JETCoreElement c = e.next();
          for (int i = 0; i < accept.length; i++)
          {
            if (c.getClass().equals(accept[i]))
            {
              v.add(c);
              continue;
            }
          }
        }
        e = v.iterator();
      }

      boolean accepted = false;
      while (e.hasNext())
      {
        JETCoreElement c = e.next();
        if (c.accept(listener, reader, this))
        {
          accepted = true;
          break;
        }
      }

      if (!accepted)
      {
        if (writerStart == null)
        {
          writerStart = reader.mark();
        }

        JETMark previousWriterStop = writerStop;
        JETMark newWriterStart = reader.mark();

        String s = reader.nextContent();
        writerStop = reader.mark();

        if (writerStop.getFileId() != writerStart.getFileId())
        {
          Assert.isNotNull(previousWriterStop, "It should not be possible to transition to a new file if we didn't previously have a start/stop markers for the writer");
          if (!previousWriterStop.equals(newWriterStart))
          {
            writerFileTransitions.add(previousWriterStop);
            writerFileTransitions.add(newWriterStart);
          }
        }

        writer.write(s, 0, s.length());
      }
    }

    flushCharData();
  }

  /**
   * @since 2.19
   */
  public static JETDirectiveItem parseDirective(String directive)
  {
    try
    {
      JETReader reader = new JETReader("", new ByteArrayInputStream(directive.getBytes("UTF-8")), "UTF-8");
      JETParseEventListener parseEventListener = new JETParseEventListener()
        {
          public void handleScriptlet(JETMark start, JETMark stop, Map<String, String> attributes) throws JETException
          {
          }

          public void handleExpression(JETMark start, JETMark stop, Map<String, String> attributes) throws JETException
          {
          }

          public void handleDirective(String directive, JETMark start, JETMark stop, Map<String, String> attributes) throws JETException
          {
          }

          public void handleCharData(char[] chars) throws JETException
          {
          }

          public void endPageProcessing() throws JETException
          {
          }

          public void beginPageProcessing() throws JETException
          {
          }
        };

      JETProblemListener problemListener = new JETProblemListener();

      JETParser parser = new JETParser(reader, parseEventListener, new JETCoreElement []{ new Directive() }, problemListener);
      parser.parse(null, null);
      return (JETDirectiveItem)reader.popItem();
    }
    catch (Exception exception)
    {
      return null;
    }
  }

  /**
   * @since 2.19
   */
  public static List<JETRootItem> parseRootItems(String content)
  {
    try
    {
      final JETReader reader = new JETReader("", new ByteArrayInputStream(content.getBytes("UTF-8")), "UTF-8");
      final List<JETRootItem> result = new ArrayList<JETRootItem>();

      class Listener implements JETParseEventListener, JETParseEventListener.CommentListener
      {
        public void handleScriptlet(JETMark start, JETMark stop, Map<String, String> attributes) throws JETException
        {
          JETScriptletItem jetScriptletItem = new JETScriptletItem(start, stop);
          result.add(jetScriptletItem);
        }

        public void handleExpression(JETMark start, JETMark stop, Map<String, String> attributes) throws JETException
        {
          JETExpressionItem jetExpressionItem = new JETExpressionItem(start, stop);
          result.add(jetExpressionItem);
        }

        public void handleDirective(String directive, JETMark start, JETMark stop, Map<String, String> attributes) throws JETException
        {
          handleItem();
        }

        public void handleComment(JETMark start, JETMark stop) throws JETException
        {
          JETCommentItem jetCommentItem = new JETCommentItem(start, stop);
          result.add(jetCommentItem);
        }

        public void handleCharData(char[] chars) throws JETException
        {
          handleItem();
        }

        public void endPageProcessing() throws JETException
        {
        }

        public void beginPageProcessing() throws JETException
        {
        }

        private void handleItem()
        {
          JETItem item = reader.popItem();
          result.add((JETRootItem)item);
        }
      }

      JETCoreElement[] coreElements = { new Directive(), new JETParser.QuoteEscape(), new JETParser.Comment(), new JETParser.Expression(), new JETParser.Scriptlet() };

      JETParser parser = new JETParser(reader, new Listener(), coreElements, new JETProblemListener());
      parser.parse(null, null);
      return result;
    }
    catch (Exception exception)
    {
      return null;
    }
  }
}
