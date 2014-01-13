/**
 * Copyright (c) 2011-2012 Eclipse contributors and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package org.eclipse.emf.ecore.xcore.generator;


import org.eclipse.xtext.xbase.compiler.output.FakeTreeAppendable;
import org.eclipse.xtext.xbase.compiler.output.ITreeAppendable;


public class XcoreAppendable extends FakeTreeAppendable
{
  private boolean inSingleQuotedString;
  private boolean inDoubleQuotedString;
  
  public XcoreAppendable()
  {
    super(new XcoreImportManager(), "\t", "\n");
  }

  @Override
  public ITreeAppendable append(CharSequence value)
  {
    String string = value.toString();
    if (string.equals("'"))
    {
      if (!inDoubleQuotedString)
      {
        inSingleQuotedString = !inSingleQuotedString;
      }
      return super.append(value);
    }
    else if (string.equals("\""))
    {
      if (!inSingleQuotedString)
      {
        inDoubleQuotedString = !inDoubleQuotedString;
      }
      return super.append(value);
    }
    else if (inSingleQuotedString || inDoubleQuotedString)
    {
      return super.append(string.replace("<%", "<%<%><%%%>"));
    }
    else if (string.startsWith(" else") || string.startsWith(" catch") || string.startsWith(" finally") || string.startsWith("\n"))
    {
      return super.append("\n").append(string.substring(1));
    }
    else if (string.endsWith(" {"))
    {
      return super.append(string.substring(0, string.length() - 2)).append("\n{");
    }
    else if (string.endsWith(" { "))
    {
      return super.append(string.substring(0, string.length() - 3)).append("\n{");
    }
    else if (string.startsWith("} "))
    {
      return super.append("}\n").append(string.substring(2));
    }
    else if (string.endsWith("finally "))
    {
      return super.append(string.substring(0, string.length() - 1)).append("\n");
    }
    else
    {
      return super.append(value);
    }
  }

}
