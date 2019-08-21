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


/**
 * Generator to deal with JSP expressions: <%= ... %> stuff.
 */
public class JETExpressionGenerator extends JETScriptletGenerator implements JETGenerator.BuilderSensitive
{
  protected static final String FUNCTION_CALL_BEGIN_APPEND = ".append(";

  protected static final String FUNCTION_CALL_BEGIN = "stringBuffer.append(";

  protected static final String FUNCTION_CALL_END = ");";

  /**
   * @since 2.19
   */
  protected String builderName;

  /**
   * @deprecated Use {@link #JETExpressionGenerator(char[], JETMark, JETMark, JETJavaItem)} instead.
   */
  public JETExpressionGenerator(char[] chars)
  {
    super(chars, null, null, null);
  }

  /**
   * @since 2.19
   */
  public JETExpressionGenerator(char[] chars, JETMark start, JETMark stop, JETJavaItem jetJavaItem)
  {
    super(chars, start, stop, jetJavaItem);
  }

  @Override
  public String generate()
  {
    StringBuilder stringBuilder = new StringBuilder();
    if (builderName == null)
    {
      stringBuilder.append(FUNCTION_CALL_BEGIN);
    }
    else
    {
      stringBuilder.append(builderName);
      stringBuilder.append(FUNCTION_CALL_BEGIN_APPEND);
    }
    stringBuilder.append(super.generate());
    stringBuilder.append(FUNCTION_CALL_END);
    return stringBuilder.toString();
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
  @Override
  public int getRelativeJavaOffset()
  {
    return builderName == null ? FUNCTION_CALL_BEGIN.length() : builderName.length() + FUNCTION_CALL_BEGIN_APPEND.length();
  }

  /**
   * @since 2.19
   */
  @Override
  public int getJavaLength()
  {
    return super.generate().length();
  }
}
