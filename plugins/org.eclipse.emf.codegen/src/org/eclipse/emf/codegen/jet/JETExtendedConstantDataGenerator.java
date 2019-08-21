/**
 * Copyright (c) 2019 Eclipse contributors and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 */
package org.eclipse.emf.codegen.jet;


import java.util.Map;


/**
 * @since 2.19
 */
public class JETExtendedConstantDataGenerator extends JETConstantDataGenerator
{
  public interface Analzyer
  {
    void handleNewLine(String newLine);

    void handleLiteral(String literal);
  }

  protected final Map<String, String> substitutions;

  protected Analzyer analyzer;

  public JETExtendedConstantDataGenerator(char[] characters, String label, Map<String, String> substitutions)
  {
    super(characters, label);
    this.substitutions = substitutions;
  }

  public void setLabel(String label)
  {
    this.label = label;
  }

  public void analyze(Analzyer analyzer)
  {
    this.analyzer = analyzer;
    generateCharData();
    this.analyzer = null;
  }

  @Override
  protected boolean isConsumeWhitespace()
  {
    return true;
  }

  @Override
  protected String generateLiteral(StringBuilder literalBody)
  {
    if (analyzer == null)
    {
      String substitution = substitutions.get(literalBody.toString());
      return substitution == null ? super.generateLiteral(literalBody) : substitution;
    }
    else
    {
      analyzer.handleLiteral(literalBody.toString());
      return "";
    }
  }

  @Override
  protected String generateNewLine(int offset, int length)
  {
    if (analyzer == null)
    {
      String substitution = substitutions.get(new String(characters, offset, length));
      return substitution == null ? super.generateNewLine(offset, length) : substitution;
    }
    else
    {
      analyzer.handleNewLine(new String(characters, offset, length));
      return "";
    }
  }

  @Override
  protected JETExtendedConstantDataGenerator copy()
  {
    return new JETExtendedConstantDataGenerator(characters, label, substitutions);
  }
}
