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
 * $Id: JETReader.java,v 1.1 2004/03/06 17:31:31 marcelop Exp $
 */
package org.eclipse.emf.codegen.jet;


import java.io.CharArrayWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.eclipse.emf.codegen.CodeGenPlugin;


/**
 * JETReader is an input buffer for the JSP parser. It should allow
 * unlimited lookahead and pushback. It also has a bunch of parsing
 * utility methods for understanding htmlesque thingies.
 */
public class JETReader 
{
  protected char startTagInitialChar = '<';
  protected char endTagInitialChar = '%';
  protected char endTagFinalChar = '>';
  protected JETMark current  = null;
  protected String master = null;
  protected List sourceFiles = new ArrayList();
  protected int size = 0;

  public JETReader(String locationURI, InputStream inputStream, String encoding) throws JETException
  {
    stackStream(locationURI, inputStream, encoding);
  }

  public String getFile(int fileid) 
  {
    return (String) sourceFiles.get(fileid);
  }

  /**
   * Stack a stream for parsing
   * @param inputStream Stream ready to parse
   * @param encoding Optional encoding to read the file.
   */
  public void stackStream(String locationURI, InputStream iStream, String encoding) throws JETException
  {
    InputStreamReader reader = null;
    try
    {
      // Until the encoding can be specified within the template 
      // we need to assume an encoding capable of working with any character set.
      if (encoding == null) 
      {
        encoding = "UTF8";
      }

      // Register the file, and read its content:
      //
      int fileid    = registerSourceFile(locationURI);
      reader = new InputStreamReader(iStream, encoding);
      CharArrayWriter writer   = new CharArrayWriter();
      char            buf[] = new char[1024];
      for (int i = 0; (i = reader.read(buf)) != -1; )
        writer.write(buf, 0, i);
      writer.close();
      if (current == null) 
      {
        current = new JETMark(this, writer.toCharArray(), fileid, locationURI, encoding);
      } 
      else 
      {
        current.pushStream(writer.toCharArray(), fileid, locationURI, encoding);
      }
    }
    catch (UnsupportedEncodingException exception)
    {
      throw new JETException(exception);
    }
    catch (IOException exception)
    {
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
   * gets a uniq identifier (which is the index in the array of source files).
   * @return The index of the now registered file.
   */
  protected int registerSourceFile(String file) 
  {
    sourceFiles.add(file);
    ++this.size;
    return sourceFiles.size() - 1;
  }


  public boolean hasMoreInput() 
  {
    if (current.cursor >= current.stream.length) 
    {
      while (popFile()) 
      {
        if (current.cursor < current.stream.length) return true;
      }
      return false;
    }

    return true;
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
   * Gets Content until the next potential JSP element.  Because all elements
   * begin with a '&lt;' we can just move until we see the next one.
   */
  public String nextContent() 
  {
    int cur_cursor = current.cursor;
    int len = current.stream.length;
    char ch;

    // pure obsfuscated genius!
    while (++current.cursor < len && (ch = current.stream[current.cursor]) != startTagInitialChar) 
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
    }

    return new String(current.stream, cur_cursor, current.cursor-cur_cursor);
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
    return current.stream[current.cursor];
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
      if (Character.toLowerCase((char) ch) != string.charAt(i++)) 
      {
        reset(mark);
        return false;
      }
    } while (i < string.length());
    reset(mark);
    return true;
  }

  public boolean matches(String string) 
  {
    JETMark mark = mark();
    int ch = 0;
    int i = 0;
    do 
    {
      ch = nextChar();
      if (((char) ch) != string.charAt(i++)) 
      {
        reset(mark);
        return false;
      }
    } while (i < string.length());
    reset(mark);
    return true;
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
   * @param s The String to match.
   * @return A non-null <code>JETMark</code> instance if found,
   * <strong>null</strong> otherwise.
   */
  public JETMark skipUntil(String limit)
  {
    JETMark ret = null;
    int limlen = limit.length();
    int ch;

    skip:
    for (ret = mark(), ch = nextChar(); ch != -1; ret = mark(), ch = nextChar()) 
    {
      if (ch == limit.charAt(0)) 
      {
        for (int i = 1; i < limlen; i++) 
        {
          if (Character.toLowerCase((char) nextChar()) != limit.charAt(i))
          {
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
    return peekChar() <= ' ';
  }

  /**
   * Parse a space delimited token.
   * If quoted the token will consume all characters up to a matching quote,
   * otherwise, it consumes up to the first delimiter character.
   * @param quoted If <strong>true</strong> accept quoted strings.
   */
  public String parseToken(boolean quoted) throws JETException
  {
    StringBuffer stringBuffer = new StringBuffer();
    skipSpaces();
    stringBuffer.setLength(0);

    int ch = peekChar();

    if (quoted) 
    {
      if (ch == '"' || ch == '\'') 
      {
        char endQuote = ch == '"' ? '"' : '\'';

        // Consume the open quote:
        //
        ch = nextChar();
        for (ch = nextChar(); ch != -1 && ch != endQuote; ch = nextChar()) 
        {
          if (ch == '\\')
          {
            ch = nextChar();
          }
          stringBuffer.append((char) ch);
        }

        // Check end of quote, skip closing quote:
        //
        if (ch == -1)
        {
          throw new JETException(CodeGenPlugin.getPlugin().getString("jet.error.quotes.unterminated", new Object [] { mark().toString()}));
        }
      }
      else 
      {
        throw new JETException(CodeGenPlugin.getPlugin().getString("jet.error.attr.quoted", new Object [] { mark().toString() }));
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
            if (peekChar() == '"' || peekChar() == '\'' || peekChar() == endTagFinalChar || peekChar() == endTagInitialChar)
            {
              ch = nextChar();
            }
          }
          stringBuffer.append((char) ch);
        } 
        while (!isDelimiter());
      }
    }
    return stringBuffer.toString();
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
  protected void parseAttributeValue(HashMap into) throws JETException
  {
    // Get the attribute name:
    //
    skipSpaces();
    String name = parseToken(false);

    // Check for an equal sign:
    //
    skipSpaces();
    if (peekChar() != '=')
    {
      throw new JETException(CodeGenPlugin.getPlugin().getString("jet.error.attr.novalue", new Object[] { name, mark().toString() }));
    }
    char ch = (char) nextChar();

    // Get the attribute value:
    //
    skipSpaces();
    String value = parseToken(true);
    skipSpaces();

    // Add the binding to the provided hashtable:
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
   */
  public HashMap parseTagAttributesBean() throws JETException
  {
    HashMap values = new HashMap(11);
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
    throw new JETException(CodeGenPlugin.getPlugin().getString("jet.error.tag.attr.unterminated", new Object [] { mark().toString() }));
  }

  /**
   * Parse some tag attributes.
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
   */
  public HashMap parseTagAttributes()
      throws JETException
  {
    HashMap values = new HashMap(11);
    while (true) 
    {
      skipSpaces();
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
    }
    // Reached EOF:
    throw new JETException(CodeGenPlugin.getPlugin().getString("jet.error.tag.attr.unterminated", new Object [] { mark().toString() }));
  }

  /**
   * Parse utils - Is current character a token delimiter ?
   * Delimiters are currently defined to be =, &gt;, &lt;, ", and ' or any
   * any space character as defined by <code>isSpace</code>.
   * @return A boolean.
   */
  protected boolean isDelimiter() 
  {
    if (! isSpace()) 
    {
      int ch = peekChar();

      // Look for a single-char work delimiter:
      //
      if (ch == '=' || ch == endTagFinalChar || ch == '"' || ch == '\'' || ch == '/')
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
