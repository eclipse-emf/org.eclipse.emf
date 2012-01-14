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

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.List;


/**
 * Creates a Rose file from Rose Tree.
 */
public class RoseWriter extends RoseComponent
{
  public static final String PROGRESS = "ROSE_WRITE_PROGRESS";

  protected BufferedWriter buffWriter;
  protected RoseNode versionTree;
  protected RoseNode modelTree;
  protected int justIndex;

  protected int lower = 0;
  protected int upper = 100;
  protected int oldValue;
  protected int progressIncrement = 10;

  public RoseWriter(String fileName, RoseNode versionTree, RoseNode modelTree) throws Exception
  {
    try
    {
      buffWriter = new BufferedWriter(new FileWriter(fileName));
    }
    catch (Exception e)
    {
      throw e;
    }
    this.versionTree = versionTree;
    this.modelTree = modelTree;
  }

  public void setProgressIncrement(int progressIncrement)
  {
    this.progressIncrement = progressIncrement;
  }

  public void setLower(int lower)
  {
    this.lower = lower;
    oldValue = lower;
  }

  public int getLower()
  {
    return lower;
  }

  public void setUpper(int upper)
  {
    this.upper = upper;
  }

  public int getUpper()
  {
    return upper;
  }

  public void write() throws Exception
  {
    if (buffWriter == null)
      return;
    try
    {
      oldValue = lower;
      justIndex = 0;
      buffWriter.newLine();
      writeObject(versionTree);
      buffWriter.newLine();
      buffWriter.newLine();
      if (modelTree.getRoseNodeType() == RoseNode.OBJECT)
      {
        writeObject(modelTree);
      }
      else
      {
        writeList(modelTree);
      }
      buffWriter.newLine();
      buffWriter.close();
    }
    catch (Exception e)
    {
      throw e;
    }
  }

  private void writeExpr(RoseNode tree) throws Exception
  {
    try
    {
      if (tree.getRoseNodeType() == RoseNode.STRING)
      {
        buffWriter.write('\t');
        buffWriter.write(tree.getValue());
      }
      else if (tree.getRoseNodeType() == RoseNode.STRING_SEQ)
      {
        List<RoseNode> nodes = tree.getNodes();
        for (int i = 0; i < nodes.size(); i++)
        {
          RoseNode node = nodes.get(i);
          buffWriter.newLine();
          buffWriter.write('|');
          buffWriter.write(node.getValue());
        }
        buffWriter.newLine();
      }
      else if (tree.getRoseNodeType() == RoseNode.LIST)
      {
        buffWriter.write(' ');
        writeList(tree);
      }
      else if (tree.getRoseNodeType() == RoseNode.OBJECT)
      {
        buffWriter.write(' ');
        writeObject(tree);
      }
      else if (tree.getRoseNodeType() == RoseNode.VALUE)
      {
        buffWriter.write('\t');
        writeValue(tree);
      }
    }
    catch (Exception e)
    {
      throw e;
    }
  }

  private void writeList(RoseNode tree) throws Exception
  {
    try
    {
      String key = tree.getKey();
      if (!key.equals("collection"))
      {
        justIndex++;
        buffWriter.write("(list ");
        buffWriter.write(tree.getValue());
      }
      List<RoseNode> nodes = tree.getNodes();

      for (int j = 0; j < nodes.size(); j++)
      {
        RoseNode node = nodes.get(j);
        buffWriter.newLine();
        for (int i = 0; i < justIndex; i++)
        {
          buffWriter.write('\t');
        }
        if (node.getRoseNodeType() == RoseNode.OBJECT)
        {
          writeObject(node);
        }
        else if (node.getRoseNodeType() == RoseNode.STRING)
        {
          buffWriter.write(node.getValue());
        }
        else
        {
          System.out.println("!!!!! error in writeLists !!!!! " + node.getRoseNodeType());
          System.exit(0);
        }
      }
      if (!key.equals("collection"))
      {
        buffWriter.write(')');
        justIndex--;
      }
    }
    catch (Exception e)
    {
      throw e;
    }
  }

  public void writeObject(RoseNode tree) throws Exception
  {
    try
    {
      justIndex++;

      buffWriter.write("(object ");
      buffWriter.write(tree.getValue());

      List<RoseNode> nodes = tree.getNodes();
      for (int i = 0; i < nodes.size(); i++)
      {
        RoseNode node = nodes.get(i);
        writePair(node);
      }
      buffWriter.write(')');
      justIndex--;
    }
    catch (Exception e)
    {
      //      System.out.println("RoseWriter - exception: "+ e.toString());
      throw e;
    }
  }

  public void writeValue(RoseNode tree) throws Exception
  {
    try
    {
      justIndex++;

      buffWriter.write("(value ");
      buffWriter.write(tree.getValue());

      List<RoseNode>nodes = tree.getNodes();
      for (int i = 0; i < nodes.size(); i++)
      {
        RoseNode node = nodes.get(i);
        writeExpr(node);
      }
      buffWriter.write(')');
      justIndex--;
    }
    catch (Exception e)
    {
      //      System.out.println("RoseWriter - exception: "+ e.toString());
      throw e;
    }
  }

  public void writePair(RoseNode node) throws Exception
  {
    try
    {
      buffWriter.newLine();
      for (int i = 0; i < justIndex; i++)
      {
        buffWriter.write('\t');
      }
      buffWriter.write(node.getKey());
      writeExpr(node);
    }
    catch (Exception e)
    {
      //      System.out.println("RoseWriter - exception: "+ e.toString());
      throw e;
    }
  }
}
