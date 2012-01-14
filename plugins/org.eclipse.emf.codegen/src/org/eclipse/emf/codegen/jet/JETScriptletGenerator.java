/**
 * Copyright (c) 2002-2004 IBM Corporation and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
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
public class JETScriptletGenerator
    implements JETGenerator
{
  protected char[] chars;

  public JETScriptletGenerator(char[] chars) 
  {
    this.chars = chars;
  }

  public String generate() 
  {
    return new String(removeQuotes(chars));
  }

  public static char[] removeQuotes(char [] characters)
  {
    CharArrayWriter writer = new CharArrayWriter();
    for (int i = 0; i < characters.length; ++i)
    {
      if (characters[i] == '%' && characters[i + 1] == '\\' && characters[i + 2] == '\\' && characters[i + 3] == '>')
      {
        writer.write('%');
        writer.write('>');
        i += 3;
      }
      else
      {
        writer.write(characters[i]);
      }
    }

    return writer.toCharArray();
  }
}
