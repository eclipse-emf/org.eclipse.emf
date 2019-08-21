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
 * The JETConstantDataGenerator generates constant declaration of the strings.
 */
public class JETConstantDataGenerator extends JETCharDataGenerator
{
  protected static final String CONSTANT_STATIC_DECLARATION_BEGIN = "protected static final String ";

  protected static final String CONSTANT_DECLARATION_BEGIN = "protected final String ";

  protected static final String CONSTANT_DECLARATION_MIDDLE = " = ";

  protected static final String CONSTANT_DECLARATION_END = ";";

  protected String label;

  public JETConstantDataGenerator(char[] characters, String label)
  {
    super(characters);
    this.label = label;
  }

  /**
   * @since 2.19
   */
  @Override
  protected String generateValue()
  {
    return label;
  }

  public String generateConstant()
  {
    return generateConstant(false, label, generateCharData());
  }

  /**
   * @since 2.19
   */
  @Override
  protected JETConstantDataGenerator copy()
  {
    return new JETConstantDataGenerator(characters, label);
  }

  /**
   * @since 2.19
   */
  public static String generateConstant(boolean isStatic, String variableName, String variableValue)
  {
    StringBuilder builder = new StringBuilder(isStatic ? CONSTANT_STATIC_DECLARATION_BEGIN : CONSTANT_DECLARATION_BEGIN);
    builder.append(variableName);
    builder.append(CONSTANT_DECLARATION_MIDDLE);
    builder.append(variableValue);
    builder.append(CONSTANT_DECLARATION_END);
    return builder.toString();
  }
}
