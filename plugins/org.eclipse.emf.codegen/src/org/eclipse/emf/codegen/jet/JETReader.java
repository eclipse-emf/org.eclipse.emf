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


import java.io.CharArrayWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import org.eclipse.emf.common.util.Diagnostic;


/**
 * JETReader is an input buffer for the JSP parser. It should allow
 * unlimited lookahead and push-back. It also has a bunch of parsing
 * utility methods for understanding html-style things.
 */
public class JETReader
{
  protected char startTagInitialChar = '<';

  protected char endTagInitialChar = '%';

  protected char endTagFinalChar = '>';

  protected JETMark current;

  /**
   * @since 2.19
   */
  protected JETMark start;

  /**
   * @deprecated since 2.19 because this is not used and was never used.
   */
  @Deprecated
  protected String master;

  protected List<String> sourceFiles = new ArrayList<String>();

  protected List<String> baseURIs = new ArrayList<String>();

  protected int size;

  protected boolean trimExtraNewLine = true;

  /**
   * @since 2.19
   */
  protected List<String> resolvedURIs = new ArrayList<String>();

  /**
   * @since 2.19
   */
  protected final LinkedList<JETItem> jetItems = new LinkedList<JETItem>();

  /**
   * @since 2.19
   */
  protected JETProblemListener problemListener;

  /**
   * @since 2.19
   */
  public JETReader(String baseURI, String locationURI, InputStream inputStream, String encoding, JETProblemListener problemListener) throws JETException
  {
    stackStream(baseURI, locationURI, inputStream, encoding);
    this.problemListener = problemListener;
  }

  /**
   * @deprecated Use {@link #JETReader(String, String, InputStream, String, JETProblemListener)}.
   */
  @Deprecated
  public JETReader(String baseURI, String locationURI, InputStream inputStream, String encoding) throws JETException
  {
    this(baseURI, locationURI, inputStream, encoding, new JETProblemListener());
  }

  public JETReader(String locationURI, InputStream inputStream, String encoding) throws JETException
  {
    this(null, locationURI, inputStream, encoding);
  }

  public String getFile(int fileid)
  {
    return sourceFiles.get(fileid);
  }

  public String getBaseURI(int fileid)
  {
    return baseURIs.get(fileid);
  }

  /**
   * @since 2.19
   */
  public String getResolvedURI(int fileid)
  {
    return resolvedURIs.get(fileid);
  }

  public void stackStream(String locationURI, InputStream iStream, String encoding) throws JETException
  {
    stackStream(null, locationURI, iStream, encoding);
  }

  /**
   * Stack a stream for parsing
   * @param iStream Stream ready to parse
   * @param encoding Optional encoding to read the file.
   */
  public void stackStream(String baseURI, String locationURI, InputStream iStream, String encoding) throws JETException
  {
    // Until the encoding can be specified within the template
    // we need to assume an encoding capable of working with any character set.
    if (encoding == null)
    {
      encoding = "UTF8";
    }

    // Register the file, and read its content:
    //
    int fileid = registerSourceFile(locationURI);
    registerBaseURI(baseURI);
    registerResolvedURI(baseURI, locationURI);

    InputStreamReader reader = null;
    try
    {
      reader = new InputStreamReader(iStream, encoding);
      CharArrayWriter writer = new CharArrayWriter();
      char buf[] = new char [1024];
      for (int i = 0; (i = reader.read(buf)) != -1;)
      {
        // Remove zero width non-breaking space, which may be used as a byte order marker,
        // and may be ignored according to the Unicode FAQ: http://www.unicode.org/unicode/faq/utf_bom.html#38
        //
        if (buf[0] == '\uFEFF')
        {
          writer.write(buf, 1, i - 1);
        }
        else
        {
          writer.write(buf, 0, i);
        }
      }
      writer.close();
      if (current == null)
      {
        start = current = new JETMark(this, writer.toCharArray(), fileid, locationURI, encoding);
      }
      else
      {
        current.pushStream(writer.toCharArray(), fileid, locationURI, encoding);
      }
    }
    catch (UnsupportedEncodingException exception)
    {
      start = new JETMark(this, new char [0], fileid, locationURI, encoding);
      throw new JETException(exception);
    }
    catch (IOException exception)
    {
      start = new JETMark(this, new char [0], fileid, locationURI, encoding);
      throw new JETException(exception);
    }
    finally
    {
      if (reader != null)
      {
        try
        {
          reader.close();
        }
        catch (Exception exception)
        {
          throw new JETException(exception);
        }
      }
    }
  }

  public boolean popFile()
  {
    // Is stack created ? (will happen if the JET file we're looking at is missing.
    //
    if (current == null)
    {
      return false;
    }

    // Restore parser state:
    //
    size--;

    return current.popStream();
  }

  /**
   * Register a new source file.
   * This method is used to implement file inclusion. Each included file
   * gets a unique identifier (which is the index in the array of source files).
   * @return The index of the now registered file.
   */
  protected int registerSourceFile(String file)
  {
    sourceFiles.add(file);
    ++this.size;
    return sourceFiles.size() - 1;
  }

  /**
   * Register a new baseURI.
   * This method is used to implement file inclusion. Each included file
   * gets a unique identifier (which is the index in the array of base URIs).
   */
  protected void registerBaseURI(String baseURI)
  {
    baseURIs.add(baseURI);
  }

  /**
   * Register a new resolvedURI.
   * This method is used to implement file inclusion. Each included file
   * gets a unique identifier (which is the index in the array of base URIs).
   *
   * @since 2.19
   */
  protected void registerResolvedURI(String baseURI, String file)
  {
    resolvedURIs.add(baseURI + "/" + file);
  }

  /**
   * Returns whether more input is available. If the end of the buffer for an included file is reached, it will return
   * to the context of the previous file, and return whether more input is available from there. In this case, if
   * trimExtraNewLine is true, then an unwanted extra newline character will be suppressed. We consider the first
   * newline in the buffer we are returning to be unwanted if the ending buffer already has at least one trailing
   * newline.
  */
  public boolean hasMoreInput()
  {
    if (current.cursor < current.stream.length)
    {
      return true;
    }

    boolean nl = hasTrailingNewLine();
    while (popFile())
    {
      if (current.cursor < current.stream.length)
      {
        if (trimExtraNewLine && nl)
        {
          skipNewLine();
        }
        return true;
      }
    }
    return false;
  }

  /**
   * Tests whether the current stream has at least one trailing newline, optionally followed by spaces.
   */
  protected boolean hasTrailingNewLine()
  {
    char[] stream = current.stream;

    for (int i = stream.length - 1; i >= 0; i--)
    {
      if (stream[i] == '\n' || stream[i] == '\r')
      {
        return true;
      }
      else if (stream[i] != ' ')
      {
        return false;
      }
    }
    return false;
  }

  /**
   * If the next character would be a line break, moves the cursor past it.
   */
  protected void skipNewLine()
  {
    char[] stream = current.stream;
    int c = current.cursor;

    if (stream.length > c + 1 && (stream[c] == '\n' && stream[c + 1] == '\r' || stream[c] == '\r' && stream[c + 1] == '\n'))
    {
      current.cursor += 2;
      current.line++;
      current.col = stream[0] == '\n' ? 1 : 0;
    }
    else if (stream.length > c && (stream[c] == '\n' || stream[c] == '\r'))
    {
      current.cursor++;
      current.line++;
      current.col = 0;
    }
  }

  public int nextChar()
  {
    if (!hasMoreInput())
    {
      return -1;
    }

    int ch = current.stream[current.cursor];

    ++current.cursor;

    if (ch == '\n')
    {
      ++current.line;
      current.col = 0;
    }
    else
    {
      ++current.col;
    }
    return ch;
  }

  /**
   * @since 2.19
   */
  public JETItem popItem()
  {
    return jetItems.isEmpty() ? null : jetItems.removeFirst();
  }

  /**
   * @since 2.19
   */
  public void pushItem(JETItem jetItem)
  {
    jetItems.addFirst(jetItem);
  }

  /**
   * Gets Content until the next potential JSP element.  Because all elements
   * begin with a '&lt;' we can just move until we see the next one.
   */
  public String nextContent()
  {
    int cur_cursor = current.cursor;
    int len = current.stream.length;
    if (cur_cursor == len)
      return "";
    char ch = current.stream[current.cursor];

    // pure obfuscated genius!
    // But not really because this skips the "\n" character, but only with Linux line endings, screwing up the line count of the cursor.
    // while (++current.cursor < len && (ch = current.stream[current.cursor]) != startTagInitialChar)
    // We must look at the first character and we must consume it even if it is the '<' because we only get here is none of the other result have consumed this character.
    do
    {
      if (ch == '\n')
      {
        ++current.line;
        current.col = 0;
      }
      else
      {
        ++current.col;
      }
      ++current.cursor;
    }
    while (current.cursor < len && (ch = current.stream[current.cursor]) != startTagInitialChar);

    return new String(current.stream, cur_cursor, current.cursor - cur_cursor);
  }

  public char[] getChars(JETMark start, JETMark stop)
  {
    JETMark oldstart = mark();
    reset(start);
    CharArrayWriter writer = new CharArrayWriter();
    while (!stop.equals(mark()))
    {
      writer.write(nextChar());
    }
    writer.close();
    reset(oldstart);
    return writer.toCharArray();
  }

  public int peekChar()
  {
    return current.cursor < current.stream.length ? current.stream[current.cursor] : -1;
  }

  public JETMark mark()
  {
    return new JETMark(current);
  }

  public void reset(JETMark mark)
  {
    current = new JETMark(mark);
  }

  public boolean matchesIgnoreCase(String string)
  {
    JETMark mark = mark();
    int ch = 0;
    int i = 0;
    do
    {
      ch = nextChar();
      if (Character.toLowerCase((char)ch) != string.charAt(i++))
      {
        reset(mark);
        return false;
      }
    }
    while (i < string.length());
    reset(mark);
    return true;
  }

  public boolean matches(String string)
  {
    return matches(string, false);
  }

  public boolean matches(String string, boolean word)
  {
    JETMark mark = mark();
    int ch = 0;
    int i = 0;
    do
    {
      ch = nextChar();
      if (((char)ch) != string.charAt(i++))
      {
        reset(mark);
        return false;
      }
    }
    while (i < string.length());
    boolean result = !word || !Character.isLetterOrDigit(peekChar());
    reset(mark);
    return result;
  }

  public void advance(int n)
  {
    while (--n >= 0)
      nextChar();
  }

  public int skipSpaces()
  {
    int i = 0;
    while (isSpace())
    {
      ++i;
      nextChar();
    }
    return i;
  }

  /**
   * Skip until the given string is matched in the stream.
   * When returned, the context is positioned past the end of the match.
   * @param limit The String to match.
   * @return A non-null <code>JETMark</code> instance if found,
   * <strong>null</strong> otherwise.
   */
  public JETMark skipUntil(String limit)
  {
    JETMark ret = null;
    int limlen = limit.length();
    int ch;

    skip: for (ret = mark(), ch = nextChar(); ch != -1; ret = mark(), ch = nextChar())
    {
      if (ch == limit.charAt(0))
      {
        for (int i = 1; i < limlen; i++)
        {
          if (nextChar() != limit.charAt(i))
          {
            reset(ret);
            nextChar();
            continue skip;
          }
        }
        return ret;
      }
    }
    return null;
  }

  protected boolean isSpace()
  {
    int peekChar = peekChar();
    return peekChar <= ' ' && peekChar != -1;
  }

  /**
   * Parse a space delimited token.
   * If quoted the token will consume all characters up to a matching quote,
   * otherwise, it consumes up to the first delimiter character.
   * @param quoted If <strong>true</strong> accept quoted strings.
   */
  public String parseToken(boolean quoted) throws JETException
  {
    StringBuilder stringBuilder = new StringBuilder();
    skipSpaces();

    int ch = peekChar();
    char quote = 0;
    JETMark start = mark();
    JETMark quoteStart = null;
    JETMark quoteStop = null;
    List<JETValueElementItem> values = null;
    if (quoted)
    {
      if (ch == '"' || ch == '\'')
      {
        quote = (char)ch;

        // Consume the open quote:
        //
        ch = nextChar();
        quoteStart = quoteStop = mark();
        StringBuilder valueBuilder = new StringBuilder();
        JETMark valueStart = quoteStart;
        values = new ArrayList<JETValueElementItem>();
        for (ch = nextChar(); ch != -1 && ch != quote; ch = nextChar())
        {
          if (ch == '\\')
          {
            ch = nextChar();
            if (ch == -1)
            {
              break;
            }
          }

          if (ch > ' ')
          {
            valueBuilder.append((char)ch);
            if (peekChar() <= ' ' || peekChar() == quote)
            {
              if (valueBuilder.length() > 0)
              {
                JETValueElementItem valueElementItem = new JETValueElementItem(valueStart, mark(), valueBuilder.toString());
                values.add(valueElementItem);
                valueBuilder.setLength(0);
              }
            }
          }
          else
          {
            valueStart = mark();
          }

          stringBuilder.append((char)ch);
          quoteStop = mark();
        }

        // Check end of quote, skip closing quote:
        //
        if (ch == -1)
        {
          reset(quoteStart);
          stringBuilder.setLength(0);
          quoteStop = quoteStart;
          for (ch = nextChar(); ch != -1; ch = nextChar())
          {
            if (ch == endTagInitialChar && peekChar() == endTagFinalChar)
            {
              break;
            }
            stringBuilder.append(ch);
            quoteStop = mark();
          }

          problemListener.handleProblem(quoteStart, quoteStop, Diagnostic.ERROR, null, JETProblemListener.QUOTE_UNTERMINATED, quoteStart.format("jet.mark.file.line.column"));
          values.clear();
          JETValueElementItem valueElementItem = new JETValueElementItem(quoteStart, quoteStop, stringBuilder.toString());
          values.add(valueElementItem);
        }
      }
      else
      {
        quoteStart = quoteStop = mark();

        for (ch = nextChar(); ch != -1; ch = nextChar())
        {
          if (ch == endTagInitialChar && peekChar() == endTagFinalChar)
          {
            reset(quoteStop);
            break;
          }
          stringBuilder.append(ch);
          quoteStop = mark();
        }

        values = new ArrayList<JETValueElementItem>();
        problemListener.handleProblem(quoteStart, quoteStop, Diagnostic.ERROR, null, JETProblemListener.BAD_ATTRIBUTE_UNQUOTED, quoteStart.format("jet.mark.file.line.column"));
        JETValueElementItem valueElementItem = new JETValueElementItem(quoteStart, quoteStop, stringBuilder.toString());
        values.add(valueElementItem);
      }
    }
    else
    {
      if (!isDelimiter())
      {
        // Read value until delimiter is found:
        do
        {
          ch = nextChar();
          // Take care of the quoting here.
          if (ch == '\\')
          {
            int peekChar = peekChar();
            if (peekChar == '"' || peekChar == '\'' || peekChar == endTagFinalChar || peekChar == endTagInitialChar)
            {
              ch = nextChar();
            }
          }
          stringBuilder.append((char)ch);
        }
        while (!isDelimiter());
      }
    }

    String result = stringBuilder.toString();
    JETMark stop = mark();
    if (quoteStart != null)
    {
      pushItem(new JETTokenItem(start, stop, quote + result + quote, new JETValueItem(quoteStart, quoteStop, result, values)));
    }
    else
    {
      pushItem(new JETTokenItem(start, stop, result));
    }

    return stringBuilder.toString();
  }

  /**
   * Parse an attribute/value pair, and store it in provided hash table.
   * The attribute/value pair is defined by:
   * <pre>
   * av := spaces token spaces '=' spaces token spaces
   * </pre>
   * Where <em>token</em> is defined by <code>parseToken</code> and
   * <em>spaces</em> is defined by <code>skipSpaces</code>.
   * The name is always considered case insensitive, hence stored in its
   * lower case version.
   * @param into The HashMap instance to save the result to.
   */
  protected void parseAttributeValue(HashMap<String, String> into) throws JETException
  {
    // Get the attribute name:
    //
    skipSpaces();
    JETMark start = mark();
    String name = parseToken(false);
    JETTokenItem nameToken = (JETTokenItem)popItem();

    // Check for an equal sign:
    //
    skipSpaces();
    if (peekChar() != '=')
    {
      JETMark stop = mark();
      problemListener.handleProblem(start, stop, Diagnostic.ERROR, null, JETProblemListener.BAD_ATTRIBUTE_NO_VALUE, name, stop.format("jet.mark.file.line.column"));
    }
    else
    {
      nextChar();
    }

    // Get the attribute value:
    //
    skipSpaces();
    String value = parseToken(true);

    JETTokenItem valueToken = (JETTokenItem)popItem();
    JETMark stop = mark();
    pushItem(new JETAttributeItem(start, stop, nameToken, valueToken));

    // Add the binding to the provided hash table:
    //
    into.put(name, value);
  }

  /**
   * Parse some tag attributes for Beans.
   * The stream is assumed to be positioned right after the tag name. The
   * syntax recognized is:
   * <pre>
   * tag-attrs := empty | attr-list ("&gt;" | "--&gt;" | %&gt;)
   * attr-list := empty | av spaces attr-list
   * empty     := spaces
   * </pre>
   * Where <em>av</em> is defined by <code>parseAttributeValue</code>.
   * @return A HashMap mapping String instances (variable names) into
   * String instances (variable values).
   * @deprecated this is not used at all and since 2.19, it also doesn't build JETItems nor use the problem listener.
   */
  @Deprecated
  public HashMap<String, String> parseTagAttributesBean() throws JETException
  {
    HashMap<String, String> values = new HashMap<String, String>(11);
    JETMark start = mark();
    while (true)
    {
      skipSpaces();
      int ch = peekChar();
      if (ch == endTagFinalChar)
      {
        // End of the useBean tag.
        //
        return values;
      }
      else if (ch == '/')
      {
        JETMark mark = mark();
        nextChar();

        // XMLesque Close tags
        //
        try
        {
          if (nextChar() == endTagFinalChar)
          {
            return values;
          }
        }
        finally
        {
          reset(mark);
        }
      }
      if (ch == -1)
      {
        break;
      }

      // Parse as an attribute=value:
      //
      parseAttributeValue(values);
    }

    // Reached EOF:
    //
    problemListener.handleProblem(start, mark(), Diagnostic.ERROR, null, JETProblemListener.BAD_ATTRIBUTE_UNTERMINATED, mark().format("jet.mark.file.line.column"));
    return new HashMap<String, String>();
  }

  /**
   * Parse some tag attributes.
   * The stream is assumed to be positioned right after the tag name. The
   * syntax recognized is:
   * <pre>
   * tag-attributes := empty | attribute-list ("&gt;" | "--&gt;" | %&gt;)
   * attribute-list := empty | attribute-value spaces attribute-list
   * empty     := spaces
   * </pre>
   * Where <em>attribute-value</em> is defined by <code>parseAttributeValue</code>.
   * @return A HashMap mapping String instances (variable names) into
   * String instances (variable values).
   */
  public HashMap<String, String> parseTagAttributes() throws JETException
  {
    skipSpaces();
    JETMark start = mark();
    JETMark stop = start;
    HashMap<String, String> values = new HashMap<String, String>(11);
    List<JETAttributeItem> attributes = new ArrayList<JETAttributeItem>();
    try
    {
      while (true)
      {
        int ch = peekChar();
        if (ch == endTagFinalChar)
        {
          return values;
        }

        if (ch == '-')
        {
          JETMark mark = mark();
          nextChar();
          // Close NCSA like attributes "->"
          try
          {
            if (nextChar() == '-' && nextChar() == endTagFinalChar)
            {
              return values;
            }
          }
          finally
          {
            reset(mark);
          }
        }
        else if (ch == endTagInitialChar)
        {
          JETMark mark = mark();
          nextChar();
          // Close variable like attributes "%>"
          try
          {
            if (nextChar() == endTagFinalChar)
            {
              return values;
            }
          }
          finally
          {
            reset(mark);
          }
        }
        else if (ch == '/')
        {
          JETMark mark = mark();
          nextChar();
          // XMLesque Close tags
          try
          {
            if (nextChar() == endTagFinalChar)
            {
              return values;
            }
          }
          finally
          {
            reset(mark);
          }
        }
        if (ch == -1)
        {
          break;
        }
        // Parse as an attribute=value:
        parseAttributeValue(values);
        attributes.add((JETAttributeItem)popItem());
        stop = mark();
        skipSpaces();
      }
    }
    finally
    {
      pushItem(new JETAttributeListItem(start, stop, attributes));
    }

    problemListener.handleProblem(start, mark(), Diagnostic.ERROR, null, JETProblemListener.BAD_ATTRIBUTE_UNTERMINATED, start.format("jet.mark.file.line.column"));
    return values;
  }

  /**
   * Parse utilities - Is current character a token delimiter ?
   * Delimiters are currently defined to be =, &gt;, &lt;, ", and ' or any
   * any space character as defined by <code>isSpace</code>.
   * @return A boolean.
   */
  protected boolean isDelimiter()
  {
    if (!isSpace())
    {
      int ch = peekChar();

      if (ch == -1)
      {
        return true;
      }

      // Look for a single-char work delimiter:
      //
      if (ch == '=' || ch == endTagFinalChar || ch == '"' || ch == '\'' || ch == '/' || ch == endTagInitialChar)
      {
        return true;
      }

      // Look for an end-of-comment or end-of-tag:
      //
      if (ch == '-')
      {
        JETMark mark = mark();
        if (((ch = nextChar()) == endTagFinalChar) || ((ch == '-') && (nextChar() == endTagFinalChar)))
        {
          reset(mark);
          return true;
        }
        else
        {
          reset(mark);
          return false;
        }
      }
      return false;
    }
    else
    {
      return true;
    }
  }

  public void setStartTag(String startTag)
  {
    startTagInitialChar = startTag.charAt(0);
  }

  public void setEndTag(String endTag)
  {
    endTagFinalChar = endTag.charAt(endTag.length() - 1);
    endTagInitialChar = endTag.charAt(0);
  }
}
