/**
 * Copyright (c) 2002-2007 IBM Corporation and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: 
 *   IBM - Initial API and implementation
 */
package org.eclipse.emf.importer.rose.parser;

import java.util.List;
import java.util.Stack;


/**
 * A parser for Rose files.
 * This parser uses the following contex-free grammar:
 * <pre>
 *   E  -> ( O )  |  ( L )  |  ( V )  |   S'  ;expression
 *   S' -> S  |  "|" S'                       ;sequence of strings
 *   S" -> EPS | S' S"                        ;sequence of strings inside list
 *   O  -> object S P'                        ;object
 *   V  -> value S S'                         ;value
 *   P' -> EPS  |  P P'                       ;sequence of pairs
 *   P  -> K E                                ;pair
 *   L  -> list S O' | list S S"              ;list
 *   O' -> EPS  |  ( O ) O'                   ;sequence of objects
 * </pre>
 * Here are the terminals:
 * <pre>
 *   K      is a key token (always one word)
 *   S      is a string token (can have multiple words)
 *   object is an object token
 *   list   is a list token
 *   "|"    is a vertical bar token
 *   EPS    is an empty string token
 * </pre>
 */
public class RoseParser
{
  protected RoseLexer lexer;
  protected RoseNode versionTree;
  protected RoseNode modelTree;
  protected String baseId = "";
  protected Stack<Integer> idStack = new Stack<Integer>();
  protected boolean isTreeOnly = false;
  protected boolean isAllowed = true;
  protected boolean isRoot = true;
  protected boolean isListMapping = true;
  protected boolean noLogicalPresentation = false;
  protected boolean mapProperties = true; // map properties even when isTreeOnly == true

  public RoseParser(RoseLexer lexer)
  {
    this(lexer, false, false);
  }

  public RoseParser(RoseLexer lexer, boolean isTreeOnly)
  {
    this(lexer, isTreeOnly, false);
  }

  public RoseParser(RoseLexer lexer, boolean isTreeOnly, boolean noPresentation)
  {
    this.lexer = lexer;
    this.isTreeOnly = isTreeOnly;
    this.noLogicalPresentation = noPresentation;
  }

  public RoseNode getVersionTree()
  {
    return versionTree;
  }

  public RoseNode getModelTree()
  {
    return modelTree;
  }

  public void parse()
  {
    baseId = null;
    idStack.push(0);
    versionTree = parseExpr("");
    idStack.push(0);
    baseId = null;
    modelTree = parseExpr("");
  }

  public void traverseTree(List<Integer> path)
  {
    RoseNode node = modelTree;
    if (node == null)
    {
      return;
    }

    for (int i = 0; i < path.size(); i++)
    {
      if (node.getRoseNodeType() == RoseNode.STRING)
      {
        return;
      }
      List<RoseNode> nodes = node.getNodes();
      Integer integ = path.get(i);
      int j = integ;
      if (j < 1 || j > nodes.size())
        break;
      node = nodes.get(j - 1);
    }

    String id = node.getId();
    String atId = node.getAtId();
    if (id == null)
    {
      id = "";
    }
    if (atId == null)
    {
      atId = "";
    }
    String t1 = "";
    if (node.getRoseNodeType() == RoseNode.STRING)
    {
      t1 = "(STRING)";
    }
    else if (node.getRoseNodeType() == RoseNode.LIST)
    {
      t1 = "(LIST)";
    }
    else if (node.getRoseNodeType() == RoseNode.OBJECT)
    {
      t1 = "(OBJECT)";
    }
    else if (node.getRoseNodeType() == RoseNode.STRING_SEQ)
    {
      t1 = "(STRING_SEQ)";
    }
    else if (node.getRoseNodeType() == RoseNode.VALUE)
    {
      t1 = "(VALUE)";
    }
    System.out.println(t1 + "\t" + node.getKey() + " --- " + node.getValue() + " - " + id + " - " + atId);
    List<RoseNode> nodes = node.getNodes();
    int count = 1;
    for (int i = 0; i < nodes.size(); i++)
    {
      RoseNode n = nodes.get(i);
      String t = "";
      if (n.getRoseNodeType() == RoseNode.STRING)
      {
        t = "(STRING)";
      }
      else if (n.getRoseNodeType() == RoseNode.LIST)
      {
        t = "(LIST)";
      }
      else if (n.getRoseNodeType() == RoseNode.OBJECT)
      {
        t = "(OBJECT)";
      }
      else if (n.getRoseNodeType() == RoseNode.STRING_SEQ)
      {
        t = "(STRING_SEQ)";
      }
      else if (n.getRoseNodeType() == RoseNode.VALUE)
      {
        t = "(VALUE)";
      }
      System.out.println(count + " " + t + "\t" + n.getKey() + " --- " + n.getValue());
      count++;
    }
  }

  private RoseNode parseObject(String key)
  {
    Integer topInt = idStack.pop();
    int top = topInt;
    idStack.push(++top);
    idStack.push(0);
    if (baseId == null)
    {
      baseId = "id";
    }
    else
    {
      baseId += ".";
      baseId += topInt.toString();
    }
    RoseToken tok = lexer.getNext();
    if (tok.getType() != RoseToken.OBJECT)
    {
      System.out.println("  Parsing error in parseObject - expecting object token");
      return null;
    }
    tok = lexer.getNext();
    if (tok.getType() != RoseToken.STRING)
    {
      System.out.println("  Parsing error in parseObject - expecting string");
      return null;
    }
    String label = tok.getValue();
    RoseNode root = null;
    if (isTreeOnly)
    {
      if (key.equals("root_usecase_package") || key.equals("root_subsystem") || !mapProperties && key.equals("properties")
        && label.equals("Properties"))
      {
        isListMapping = false;
      }
      else if (key.equals("root_category") || key.equals("process_structure") && label.equals("Processes"))
      {
        isListMapping = true;
      }
    }
    root = new RoseNode(key, label, RoseNode.OBJECT);
    root.setId(baseId);
    while (true)
    {
      tok = lexer.peekNext();
      if (tok.getType() == RoseToken.RIGHT_PAREN)
        break;
      else
        tok = lexer.getNext();
      if (tok.getType() != RoseToken.KEY)
      {
        System.out.println("  Parsing error in parseObject - expecting key " + tok.lineNum);
        return null;
      }
      String pairKey = tok.getValue();
      RoseNode node = parseExpr(pairKey);
      if (isTreeOnly)
      {
        if (node != null)
        {
          root.addNode(node);
        }
      }
      else
      {
        if (node == null)
        {
          return null;
        }
        root.addNode(node);
      }
    }
    idStack.pop();
    if (baseId.lastIndexOf('.') > 0)
    {
      baseId = baseId.substring(0, baseId.lastIndexOf('.'));
    }
    return root;
  }

  private RoseNode parseValue(String key)
  {
    Integer topInt = idStack.pop();
    int top = topInt;
    //    top++;
    idStack.push(++top);
    idStack.push(0);
    if (baseId == null)
    {
      baseId = "id";
    }
    else
    {
      baseId += ".";
      baseId += topInt.toString();
    }
    RoseToken tok = lexer.getNext();
    if (tok.getType() != RoseToken.VALUE)
    {
      System.out.println("  Parsing error in parseValue - expecting value token");
      return null;
    }
    tok = lexer.getNext();
    if (tok.getType() != RoseToken.STRING)
    {
      System.out.println("  Parsing error in parseValue - expecting string");
      return null;
    }
    String label = tok.getValue();
    RoseNode root = null;
    root = new RoseNode(key, label, RoseNode.VALUE);
    root.setId(baseId);
    RoseNode node = parseS_prime("");
    if (isTreeOnly)
    {
      if (node != null)
      {
        root.addNode(node);
      }
    }
    else
    {
      if (node == null)
      {
        return null;
      }
      root.addNode(node);
    }
    idStack.pop();
    if (baseId.lastIndexOf('.') > 0)
    {
      baseId = baseId.substring(0, baseId.lastIndexOf('.'));
    }
    return root;
  }

  private RoseNode parseExpr(String key)
  {
    RoseToken tok = lexer.peekNext();
    if (tok.getType() == RoseToken.LEFT_PAREN)
    {
      tok = lexer.getNext();
      RoseNode root;
      tok = lexer.peekNext();
      if (tok.getType() == RoseToken.OBJECT)
      {
        root = parseObject(key);
        if (!isTreeOnly)
        {
          if (root == null)
            return null;
        }
      }
      else if (tok.getType() == RoseToken.VALUE)
      {
        root = parseValue(key);
        if (!isTreeOnly)
        {
          if (root == null)
          {
            return null;
          }
        }
      }
      else if (tok.getType() == RoseToken.LIST)
      {
        root = parseList(key);
        if (!isTreeOnly)
        {
          if (root == null)
            return null;
        }
      }
      else
      {
        System.out.println("  Parsing error in parseExpr - expecting object or list");
        return null;
      }
      tok = lexer.getNext();
      if (tok.getType() != RoseToken.RIGHT_PAREN)
      {
        System.out.println("  Parsing error in parseExpr - expecting right parenthesis");
        return null;
      }
      return root;
    }
    else
    {
      return parseS_prime(key);
    }
  }

  private RoseNode parseS_prime(String key)
  {
    RoseToken tok = lexer.peekNext();
    if (tok.getType() == RoseToken.STRING)
    {
      tok = lexer.getNext();
      RoseNode root = null;
      root = new RoseNode(key, tok.getValue(), RoseNode.STRING);
      return root;
    }
    else if (tok.getType() == RoseToken.VERTICAL_BAR)
    {
      RoseNode root = null;
      root = new RoseNode(key, "", RoseNode.STRING_SEQ);
      while (true)
      {
        tok = lexer.peekNext();
        if (tok.getType() == RoseToken.VERTICAL_BAR)
        {
          tok = lexer.getNext();
        }
        else
        {
          break;
        }
        tok = lexer.getNext();
        if (tok.getType() != RoseToken.STRING)
        {
          System.out.println("  Parsing error in parseS_prime - expecting string");
          return null;
        }

        RoseNode node = null;
        node = new RoseNode("", tok.getValue(), RoseNode.STRING);
        if (root != null)
        {
          root.addNode(node);
        }
      }
      return root;
    }
    else
    {
      lexer.printNeighbors();
      System.out.println("  Parsing error in parseS_prime - expecting string or | " + tok.lineNum);
      return null;
    }
  }

  private RoseNode parseList(String key)
  {
    boolean wasAllowed = isAllowed;

    if (noLogicalPresentation)
    {
      if (key.equals("logical_presentations"))
      {
        isAllowed = false;
      }
      else
      {
        isAllowed = true;
      }
    }
    else
    {
      isAllowed = true;
    }

    if (!isListMapping)
    {
      isAllowed = false;
    }
    RoseNode root = null;
    RoseToken tok;
    tok = lexer.getNext();
    if (tok.getType() != RoseToken.LIST)
    {
      System.out.println("  Parsing error in parseList - expecting list token");
      return null;
    }
    tok = lexer.getNext();
    if (tok.getType() != RoseToken.STRING)
    {
      System.out.println("  Parsing error in parseList - expecting string");
      return null;
    }
    String label = tok.getValue();
    tok = lexer.peekNext();
    if (tok.getType() == RoseToken.LEFT_PAREN)
    {
      if (!isTreeOnly || isAllowed)
      {
        root = new RoseNode(key, label, RoseNode.LIST);
      }
      while (true)
      {
        tok = lexer.getNext();
        if (tok.getType() != RoseToken.LEFT_PAREN)
        {
          System.out.println("  Parsing error in parseList - expecting left parenthesis");
          return null;
        }
        RoseNode node = parseObject("");
        if (isTreeOnly)
        {
          if (root != null && node != null)
          {
            root.addNode(node);
          }
        }
        else if (root != null)
        {
          if (node == null)
          {
            return null;
          }
          root.addNode(node);
        }
        tok = lexer.getNext();
        if (tok.getType() != RoseToken.RIGHT_PAREN)
        {
          System.out.println("  Parsing error in parseList - expecting right parenthesis");
          return null;
        }
        tok = lexer.peekNext();
        if (tok.getType() == RoseToken.RIGHT_PAREN)
        {
          break;
        }
      }
    }
    else if (tok.getType() == RoseToken.RIGHT_PAREN)
    {
      if (!isTreeOnly || isAllowed)
      {
        root = new RoseNode(key, label, RoseNode.LIST);
      }
    }
    else if (tok.getType() == RoseToken.STRING || tok.getType() == RoseToken.VERTICAL_BAR)
    {
      if (!isTreeOnly || isAllowed)
      {
        root = new RoseNode(key, label, RoseNode.LIST);
      }
      while (true)
      {
        tok = lexer.peekNext();
        if (tok.getType() != RoseToken.STRING && tok.getType() != RoseToken.VERTICAL_BAR)
        {
          System.out.println("  Parsing error in parseList - expecting string or |");
          return null;
        }
        RoseNode node = null;
        node = parseS_prime("");
        if (root != null)
        {
          root.addNode(node);
        }
        tok = lexer.peekNext();
        if (tok.getType() == RoseToken.RIGHT_PAREN)
        {
          break;
        }
      }
    }
    else
    {
      lexer.getNext();
      System.out.println("  Parsing error in parseList - expecting left/right parenthesis or string or | " + tok.lineNum);
    }

    isAllowed = wasAllowed;
    return root;
  }
}
