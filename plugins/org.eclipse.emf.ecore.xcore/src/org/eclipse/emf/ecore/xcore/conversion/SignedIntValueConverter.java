/**
 * Copyright (c) 2013 Eclipse contributors and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package org.eclipse.emf.ecore.xcore.conversion;


import org.eclipse.xtext.conversion.ValueConverterException;
import org.eclipse.xtext.conversion.impl.AbstractLexerBasedConverter;
import org.eclipse.xtext.nodemodel.INode;
import org.eclipse.xtext.util.Strings;


public class SignedIntValueConverter extends AbstractLexerBasedConverter<Integer>
{
  public SignedIntValueConverter()
  {
    super();
  }

  @Override
  public String toString(Integer value)
  {
    assertValidValue(value);
    return value.toString();
  }

  public Integer toValue(String string, INode node)
  {
    if (Strings.isEmpty(string))
      throw new ValueConverterException("Couldn't convert empty string to an int value.", node, null);
    try
    {
      int intValue = Integer.parseInt(string, 10);
      return Integer.valueOf(intValue);
    }
    catch (NumberFormatException e)
    {
      throw new ValueConverterException("Couldn't convert '" + string + "' to an int value.", node, e);
    }
  }

}