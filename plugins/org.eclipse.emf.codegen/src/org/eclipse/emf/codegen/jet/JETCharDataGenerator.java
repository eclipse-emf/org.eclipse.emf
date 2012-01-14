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



/**
 * The JETCharDataGenerator generates strings for the character data present in the template file.
 */
public class JETCharDataGenerator
  implements JETGenerator
{
  protected static final String FUNCTION_CALL_BEGIN = "stringBuffer.append(";
  protected static final String FUNCTION_CALL_END = ");";
  protected static final String NEW_LINE_BEGIN = "NL + ";
  protected static final String NEW_LINE_MIDDLE = "\" + NL + \"";
  protected static final String NEW_LINE_END = " + NL";

  protected char[] characters;

  public JETCharDataGenerator(char[] characters) 
  {
    this.characters = characters;
  }

  public String generate() 
  {
    StringBuffer stringBuffer = new StringBuffer(characters.length + 16);
    stringBuffer.append(FUNCTION_CALL_BEGIN);
    stringBuffer.append(generateCharData());
    stringBuffer.append(FUNCTION_CALL_END);
    return stringBuffer.toString();
  }

  protected String generateCharData() 
  {
    StringBuffer stringBuffer = new StringBuffer();
    StringBuffer stringBufferTail = new StringBuffer();
    int limit = characters.length - 1;

     buildEnd:
     while (limit >= 0)
     {
       char ch = characters[limit];
       switch (ch)
       {
         case '\r':
         {
           break;
         }
         case '\n':
         {
           stringBufferTail.append(NEW_LINE_END);
           break;
         }
         default:
         {
           limit++;
           break buildEnd;
         }
       }
       limit--;
     }

     if (limit < 0)
     {
       if (stringBufferTail.length() == 0)
       {
         stringBuffer.append("\"\"");
       }
       else
       {
         stringBuffer.append(stringBufferTail.toString().substring(3));
       }
     }
     else
     {
       int i = 0;
       buildFront:
       for (; i <= limit; i ++ )
       {
         char ch = characters[i];
         switch (ch)
         {
           case '\r':
           {
             break;
           }
           case '\n':
           {
             stringBuffer.append(NEW_LINE_BEGIN);
             break;
           }
           default:
           {
             break buildFront;
           }
         }
       }
 
       stringBuffer.append('"');
       for (; i < limit; i++) 
       {
         char ch = characters[i];
         switch (ch) 
         {
           case '"':
           {
             stringBuffer.append("\\\"");
             break;
           }
           case '\\':
           {
             stringBuffer.append("\\\\");
             break;
           }
          case '\r':
          {
            continue;
          }
          case '\n':
          {
            stringBuffer.append(NEW_LINE_MIDDLE);
            break;
          }
          case '\t':
          {
            stringBuffer.append("\\t");
            break;
          }
          default:
          {
            stringBuffer.append(ch);
          }
        }
      }
      stringBuffer.append('"');
      stringBuffer.append(stringBufferTail.toString());
    }
    return stringBuffer.toString();
  }
}
