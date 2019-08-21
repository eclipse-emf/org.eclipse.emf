/**
 * Copyright (c) 2002-2004 IBM Corporation and others.
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


/**
 * Generator for <% .... %> stuff.
 *
 */
public class JETScriptletGenerator implements JETGenerator, JETJavaGenerator
{
  protected char[] chars;

  /**
   * @since 2.19
   */
  protected JETMark start;

  /**
   * @since 2.19
   */
  protected JETMark stop;

  /**
   * @since 2.19
   */
  protected JETJavaItem jetJavaItem;

  /**
   * @deprecated Use {@link #JETScriptletGenerator(char[], JETMark, JETMark, JETJavaItem)} instead.
   */
  public JETScriptletGenerator(char[] chars)
  {
    this(chars, null, null, null);
  }

  /**
   * @since 2.19
   */
  public JETScriptletGenerator(char[] chars, JETMark start, JETMark stop, JETJavaItem jetJavaItem)
  {
    this.chars = chars;
    this.start = start;
    this.stop = stop;
    this.jetJavaItem = jetJavaItem;
  }

  /**
   * @since 2.19
   */
  public JETJavaItem getJavaItem()
  {
    return jetJavaItem;
  }

  public String generate()
  {
    return new String(removeQuotes(chars));
  }

  /**
   * @since 2.19
   */
  public JETMark getStart()
  {
    return start;
  }

  /**
   * @since 2.19
   */
  public JETMark getStop()
  {
    return stop;
  }

  /**
   * @since 2.19
   */
  public int getRelativeJavaOffset()
  {
    return 0;
  }

  /**
   * @since 2.19
   */
  public int getJavaLength()
  {
    return generate().length();
  }

  public static char[] removeQuotes(char[] characters)
  {
    CharArrayWriter writer = null;
    for (int i = 0; i < characters.length; ++i)
    {
      if (characters[i] == '%' && i + 3 < characters.length && characters[i + 1] == '\\' && characters[i + 2] == '\\' && characters[i + 3] == '>')
      {
        if (writer == null)
        {
          writer = new CharArrayWriter();
          writer.write(characters, 0, i);
        }

        writer.write('%');
        writer.write('>');
        i += 3;
      }
      else if (writer != null)
      {
        writer.write(characters[i]);
      }
    }

    return writer == null ? characters : writer.toCharArray();
  }
}
