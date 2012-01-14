/**
 * Copyright (c) 2002-2006 IBM Corporation and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: 
 *   IBM - Initial API and implementation
 */
package org.eclipse.emf.importer.rose.parser;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;


/**
 * A lexical analyzer that creates vector of tokens out of Rose file.
 */
public class RoseLexer
{
  protected RoseLoader roseLoader;
  protected List<RoseToken> tokens = new ArrayList<RoseToken>();
  protected int currentElement = 0;
  protected boolean isList = false;
  protected int lineCounter = 0;

  public RoseLexer(RoseLoader roseLoader)
  {
    this.roseLoader = roseLoader;
  }

  protected void getNextBlock()
  {
    RoseToken roseToken = null;
    if (tokens.size() > currentElement)
    {
      roseToken = tokens.get(currentElement);
    }
    tokens.clear();
    if (roseToken != null)
    {
      tokens.add(roseToken);
    }
    currentElement = 0;
    System.gc();
    readLines();
  }

  public RoseToken getNext()
  {
    if (currentElement >= tokens.size())
    {
      getNextBlock();
    }
    if (currentElement < tokens.size())
    {
      RoseToken roseToken = tokens.get(currentElement);
      currentElement++;
      return roseToken;
    }
    else
    {
      return null;
    }
  }

  public RoseToken peekNext()
  {
    if (currentElement >= tokens.size())
    {
      getNextBlock();
    }
    if (currentElement < tokens.size())
    {
      return tokens.get(currentElement);
    }
    else
    {
      return null;
    }
  }

  public void printNeighbors()
  {
    int count = 100;
    if (currentElement < 100)
    {
      count = currentElement;
    }
    for (int i = currentElement - count; i <= currentElement; i++)
    {
      RoseToken tok = tokens.get(i);
      System.out.println(tok.getToken());
    }
  }

  public void readLines()
  {
    for (int k = 0; k < 1000; k++)
    {
      String currentLine = roseLoader.readLine();
      if (currentLine == null)
      {
        return;
      }
      lineCounter++;
      if (currentLine.length() == 0)
      {
        k--;
        continue;
      }
      if (currentLine.charAt(0) == '|')
      {
        RoseToken roseToken = new RoseToken(RoseToken.VERTICAL_BAR, "");
        roseToken.lineNum = lineCounter;

        tokens.add(roseToken);
        currentLine = currentLine.substring(1);
        roseToken = new RoseToken(RoseToken.STRING, currentLine);
        roseToken.lineNum = lineCounter;
        tokens.add(roseToken);
        continue;
      }
      StringTokenizer stringTokenizer = new StringTokenizer(currentLine);
      for (int i = 0; stringTokenizer.hasMoreTokens();)
      {
        String tok = stringTokenizer.nextToken();
        if (tok.equals("#"))
        {
          break;
        }
        currentLine = currentLine.substring(currentLine.indexOf(tok));
        if (tok.equals("(object"))
        {
          isList = false;
          RoseToken roseToken = new RoseToken(RoseToken.LEFT_PAREN, "");
          roseToken.lineNum = lineCounter;
          tokens.add(roseToken);
          roseToken = new RoseToken(RoseToken.OBJECT, "");
          roseToken.lineNum = lineCounter;
          tokens.add(roseToken);
          currentLine = currentLine.substring(7).trim(); // 7 is the size of (object
          processToTheEnd(currentLine, lineCounter);
          break;
        }
        else if (tok.equals("(list"))
        {
          isList = true;
          RoseToken roseToken = new RoseToken(RoseToken.LEFT_PAREN, "");
          roseToken.lineNum = lineCounter;
          tokens.add(roseToken);
          roseToken = new RoseToken(RoseToken.LIST, "");
          roseToken.lineNum = lineCounter;
          tokens.add(roseToken);
          currentLine = currentLine.substring(5).trim(); // 5 is the size of (list
          processToTheEnd(currentLine, lineCounter);
          break;
        }
        else if (tok.equals("(value"))
        {
          isList = false;
          RoseToken roseToken = new RoseToken(RoseToken.LEFT_PAREN, "");
          roseToken.lineNum = lineCounter;
          tokens.add(roseToken);
          roseToken = new RoseToken(RoseToken.VALUE, "");
          roseToken.lineNum = lineCounter;
          tokens.add(roseToken);
          currentLine = currentLine.substring(6).trim(); // 6 is the size of (value
          processValueToTheEnd(currentLine, lineCounter);
          break;
        }
        else if (i == 0 && tok.charAt(0) == ')')
        {
          int rightParenNum = 0;
          String temp = tok;
          while (temp.length() > 0 && temp.charAt(temp.length() - 1) == ')')
          {
            rightParenNum++;
            temp = temp.substring(0, temp.length() - 1);
          }
          for (int j = 0; j < rightParenNum; j++)
          {
            isList = false;
            RoseToken roseToken = new RoseToken(RoseToken.RIGHT_PAREN, "");
            roseToken.lineNum = lineCounter;
            tokens.add(roseToken);
          }
          break;
        }
        else
        {
          if (i == 0)
          {
            if (isList)
            {
              int rightParenNum = 0;
              String temp = currentLine.trim();
              while (temp.charAt(temp.length() - 1) == ')')
              {
                rightParenNum++;
                temp = temp.substring(0, temp.length() - 1);
              }
              if (currentLine.charAt(0) == '(')
              {
                rightParenNum--;
                temp += ")";
              }
              RoseToken roseToken = new RoseToken(RoseToken.STRING, temp);
              roseToken.lineNum = lineCounter;
              tokens.add(roseToken);
              for (int j = 0; j < rightParenNum; j++)
              {
                isList = false;
                roseToken = new RoseToken(RoseToken.RIGHT_PAREN, "");
                roseToken.lineNum = lineCounter;
                tokens.add(roseToken);
              }
              break;
            }
            else
            {
              isList = false;
              RoseToken roseToken = new RoseToken(RoseToken.KEY, tok);
              roseToken.lineNum = lineCounter;
              tokens.add(roseToken);
            }
          }
          else
          {
            isList = false;
            currentLine = currentLine.trim();
            processToTheEnd(currentLine, lineCounter);
            break;
          }
        }

        i++;
      }
    }
  }

  protected void processToTheEnd(String temp, int lineCounter)
  {
    if (temp.length() == 0)
    {
      RoseToken roseToken = new RoseToken(RoseToken.STRING, temp);
      roseToken.lineNum = lineCounter;
      tokens.add(roseToken);
      return;
    }

    int indFirst = temp.indexOf("\"");
    int indLast = temp.lastIndexOf("\"");
    if (indFirst != -1 && indFirst == indLast)
    {
      // Only one open quote.
      //
      String currentLine = roseLoader.readLine();
      temp += currentLine;
      while (currentLine.indexOf("\"") == -1)
      {
        currentLine = roseLoader.readLine();
        temp += currentLine;
      }
    }

    int rightParenNum = 0;
    while (temp.charAt(temp.length() - 1) == ')')
    {
      rightParenNum++;
      temp = temp.substring(0, temp.length() - 1);
    }

    if (temp.charAt(0) == '(')
    {
      rightParenNum--;
      temp += ")";
    }

    RoseToken roseToken = new RoseToken(RoseToken.STRING, temp);
    roseToken.lineNum = lineCounter;
    tokens.add(roseToken);
    for (int i = 0; i < rightParenNum; i++)
    {
      isList = false;
      roseToken = new RoseToken(RoseToken.RIGHT_PAREN, "");
      roseToken.lineNum = lineCounter;
      tokens.add(roseToken);
    }
  }

  protected void processValueToTheEnd(String temp, int lineCounter)
  {
    if (temp.length() == 0)
    {
      RoseToken roseToken = new RoseToken(RoseToken.STRING, temp);
      roseToken.lineNum = lineCounter;
      tokens.add(roseToken);
      return;
    }
    int rightParenNum = 0;
    while (temp.charAt(temp.length() - 1) == ')')
    {
      rightParenNum++;
      temp = temp.substring(0, temp.length() - 1);
    }
    if (temp.charAt(0) == '(')
    {
      rightParenNum--;
      temp += ")";
    }
    String firstWord = temp;
    String restOfString = "";
    int indexOfSpace = temp.indexOf(" ");
    if (indexOfSpace == -1)
    {
      indexOfSpace = temp.indexOf("\t");
    }
    if (indexOfSpace != -1)
    {
      firstWord = temp.substring(0, indexOfSpace);
      restOfString = temp.substring(indexOfSpace).trim();
    }

    RoseToken roseToken = new RoseToken(RoseToken.STRING, firstWord);
    roseToken.lineNum = lineCounter;
    tokens.add(roseToken);
    if (restOfString.length() > 0)
    {
      roseToken = new RoseToken(RoseToken.STRING, restOfString);
      roseToken.lineNum = lineCounter;
      tokens.add(roseToken);
    }
    for (int i = 0; i < rightParenNum; i++)
    {
      isList = false;
      roseToken = new RoseToken(RoseToken.RIGHT_PAREN, "");
      roseToken.lineNum = lineCounter;
      tokens.add(roseToken);
    }
  }
}
