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
package org.eclipse.emf.importer.rose.parser;

/**
 * This is the RoseToken class. Lexer uses this class to create a vector of tokens.
 *
 * @version 1.0, 04/08/98
 * @author Alex Glebov, glebov@us.ibm.com
 */
//************************************************************************
//*    class RoseToken                                                   *
//************************************************************************
public class RoseToken
{
  public final static int OBJECT = 0;
  public final static int LIST = 1;
  public final static int LEFT_PAREN = 2;
  public final static int RIGHT_PAREN = 3;
  public final static int VERTICAL_BAR = 4;
  public final static int KEY = 5;
  public final static int STRING = 6;
  public final static int VALUE = 7;

  protected int tokenType;
  protected String tokenValue;
  protected int lineNum;

  public RoseToken(int tokenType, String tokenValue)
  {
    this.tokenType = tokenType;
    this.tokenValue = tokenValue;
  }

  public int getType()
  {
    return tokenType;
  }

  public String getValue()
  {
    return tokenValue;
  }

  public String getToken()
  {
    if (tokenType == OBJECT)
    {
      return "object";
    }
    else if (tokenType == LIST)
    {
      return "list";
    }
    else if (tokenType == VALUE)
    {
      return "value";
    }
    else if (tokenType == LEFT_PAREN)
    {
      return "(";
    }
    else if (tokenType == RIGHT_PAREN)
    {
      return ")";
    }
    else if (tokenType == VERTICAL_BAR)
    {
      return "|";
    }
    else if (tokenType == KEY)
    {
      return "key: " + getValue();
    }
    else
    {
      return "string: " + getValue();
    }
  }
}
