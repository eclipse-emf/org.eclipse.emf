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


import java.util.List;


/**
 * The JETCharDataGenerator generates strings for the character data present in the template file.
 */
public class JETCharDataGenerator implements JETGenerator, JETGenerator.BuilderSensitive, JETJavaGenerator
{
  /**
   * @since 2.19
   */
  protected static final String FUNCTION_CALL_BEGIN_APPEND = ".append(";

  protected static final String FUNCTION_CALL_BEGIN = "stringBuffer.append(";

  protected static final String FUNCTION_CALL_END = ");";

  protected static final String NEW_LINE_BEGIN = "NL + ";

  protected static final String NEW_LINE_MIDDLE = "\" + NL + \"";

  protected static final String NEW_LINE_END = " + NL";

  protected char[] characters;

  /**
   * @since 2.19
   */
  protected String builderName;

  /**
   * @since 2.19
   */
  protected JETLiteralItem literalItem;

  public JETCharDataGenerator(char[] characters)
  {
    this.characters = characters;
  }

  public String generate()
  {
    StringBuilder builder = new StringBuilder(characters.length + 16);
    if (builderName == null)
    {
      builder.append(FUNCTION_CALL_BEGIN);
    }
    else
    {
      builder.append(builderName);
      builder.append(FUNCTION_CALL_BEGIN_APPEND);
    }

    builder.append(generateValue());

    builder.append(FUNCTION_CALL_END);
    return builder.toString();
  }

  /**
   * @since 2.19
   */
  protected String generateValue()
  {
    return generateCharData();
  }

  /**
   * @since 2.19
   */
  public void setBuilderName(String builderName)
  {
    this.builderName = builderName;
  }

  /**
   * @since 2.19
   */
  public JETLiteralItem getLiteralItem()
  {
    return literalItem;
  }

  /**
   * @since 2.19
   */
  public void setLiteralItem(JETLiteralItem literalItem)
  {
    this.literalItem = literalItem;
  }

  /**
   * @since 2.19
   */
  public JETJavaItem getJavaItem()
  {
    return literalItem;
  }

  /**
   * @since 2.19
   */
  public int getRelativeJavaOffset()
  {
    return builderName == null ? FUNCTION_CALL_BEGIN.length() : builderName.length() + FUNCTION_CALL_BEGIN_APPEND.length();
  }

  /**
   * @since 2.19
   */
  private JETLiteralItem getLastItem()
  {
    if (literalItem == null)
    {
      return null;
    }
    else
    {
      List<JETLiteralItem> explode = literalItem.explode();
      return explode.get(explode.size() - 1);
    }
  }

  /**
   * @since 2.19
   */
  public JETMark getStart()
  {
    JETLiteralItem lastItem = getLastItem();
    return lastItem == null ? null : lastItem.getStart();
  }

  /**
   * @since 2.19
   */
  public JETMark getStop()
  {
    JETLiteralItem lastItem = getLastItem();
    return lastItem == null ? null : lastItem.getStop();
  }

  /**
   * @since 2.19
   */
  public int getJavaLength()
  {
    return generateValue().length();
  }

  /**
   * @since 2.19
   */
  protected boolean isConsumeWhitespace()
  {
    return false;
  }

  /**
   * @since 2.19
   */
  protected int generateNewLineFeedBegin(StringBuilder builder, int offset)
  {
    int i = 0;
    int lineEndLength = 0;
    int lineOffset = -1;
    for (; i <= offset; i++)
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
          if (lineOffset != -1)
          {
            builder.append(generateNewLine(lineOffset, lineEndLength));
            builder.append(" + ");
            lineEndLength = 0;
          }

          ++lineEndLength;
          lineOffset = i;
          break;
        }
        case ' ':
        case '\t':
        {
          if (lineOffset != -1 && isConsumeWhitespace())
          {
            ++lineEndLength;
            break;
          }
        }
        default:
        {
          if (lineEndLength > 0)
          {
            builder.append(generateNewLine(lineOffset, lineEndLength));
            builder.append(" + ");
          }

          return i;
        }
      }
    }

    return i;
  }

  /**
   * @since 2.19
   */
  protected int generateNewLineMiddle(StringBuilder builder, int offset, int limit)
  {
    int lineOffset = offset;
    int lineLength = 1;
    if (isConsumeWhitespace())
    {
      LOOP: for (int i = offset + 1; i < limit; ++i)
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
            builder.append(generateNewLine(lineOffset, lineLength));
            builder.append(" + ");
            lineOffset = i;
            lineLength = 1;
            break;
          }
          case ' ':
          case '\t':
          {
            if (isConsumeWhitespace())
            {
              ++lineLength;
              break;
            }
          }
          default:
          {
            break LOOP;
          }
        }
      }
    }

    builder.append(generateNewLine(lineOffset, lineLength));

    return lineOffset + lineLength - 1;
  }

  /**
   * @since 2.19
   */
  protected int generateNewLineEnd(StringBuilder builder)
  {
    int offset = characters.length - 1;
    int lineEndLength = 0;
    BUILDER_END: while (offset >= 0)
    {
      char ch = characters[offset];
      switch (ch)
      {
        case '\r':
        {
          break;
        }
        case '\n':
        {
          if (builder.length() != 0)
          {
            builder.insert(0, " + ");
          }
          builder.insert(0, generateNewLine(offset, ++lineEndLength));
          lineEndLength = 0;
          break;
        }
        case ' ':
        case '\t':
        {
          if (isConsumeWhitespace() && offset > 0)
          {
            ++lineEndLength;
            break;
          }
        }
        default:
        {
          offset += lineEndLength + 1;
          break BUILDER_END;
        }
      }

      offset--;
    }

    if (offset < 0)
    {
      if (builder.length() == 0)
      {
        builder.append("\"\"");
      }
    }
    else if (builder.length() != 0)
    {
      builder.insert(0, " + ");
    }

    return offset;
  }

  /**
   * @since 2.19
   */
  protected String generateNewLine(int offset, int length)
  {
    if (length != 1)
    {
      System.err.println("###");
    }

    return "NL";
  }

  /**
   * @since 2.19
   */
  protected String generateLiteral(StringBuilder literalBody)
  {
    return '"' + literalBody.toString() + '"';
  }

  protected String generateCharData()
  {
    StringBuilder builderTail = new StringBuilder();
    int limit = generateNewLineEnd(builderTail);
    if (limit < 0)
    {
      return builderTail.toString();
    }
    else
    {
      StringBuilder builder = new StringBuilder();

      StringBuilder builderHead = new StringBuilder();
      int i = generateNewLineFeedBegin(builderHead, limit);
      builder.append(builderHead);

      StringBuilder literalBuilder = new StringBuilder();

      for (; i < limit; i++)
      {
        char ch = characters[i];
        switch (ch)
        {
          case '"':
          {
            literalBuilder.append("\\\"");
            break;
          }
          case '\\':
          {
            literalBuilder.append("\\\\");
            break;
          }
          case '\r':
          {
            break;
          }
          case '\n':
          {
            String literal = generateLiteral(literalBuilder);

            builder.append(literal);
            builder.append(" + ");
            i = generateNewLineMiddle(builder, i, limit);
            builder.append(" + ");
            literalBuilder.setLength(0);
            break;
          }
          case '\t':
          {
            literalBuilder.append("\\t");
            break;
          }
          default:
          {
            literalBuilder.append(ch);
            break;
          }
        }
      }

      builder.append(generateLiteral(literalBuilder));
      builder.append(builderTail);
      return builder.toString();
    }
  }

  protected JETCharDataGenerator copy()
  {
    return new JETCharDataGenerator(characters);
  }
}
