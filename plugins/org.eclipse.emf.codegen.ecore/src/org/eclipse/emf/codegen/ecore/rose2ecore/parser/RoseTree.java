/**
 * <copyright> 
 *
 * Copyright (c) 2002-2004 IBM Corporation and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Common Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/cpl-v10.html
 * 
 * Contributors: 
 *   IBM - Initial API and implementation
 *
 * </copyright>
 *
 * $Id: RoseTree.java,v 1.1 2004/03/06 17:31:31 marcelop Exp $
 */
package org.eclipse.emf.codegen.ecore.rose2ecore.parser;


import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;


/**
 * A tree navigator.
 */
public class RoseTree implements PropertyChangeListener 
{
  protected RoseLoader loader;
  protected RoseLexer  lexer;
  protected RoseParser parser;
  protected String inFileName;
  protected String outFileName;

  public RoseTree(String args[]) throws Exception
  {
    if (args.length > 0)
    {
      inFileName = args[0];
    }
    String isTree = "false";
    String noPresentation = "false";
    if (args.length > 1)
    {
      isTree = args[1];
    }
    if (args.length > 2)
    {
      noPresentation = args[2];
    }
    if (args.length > 3)
    {
      outFileName = args[3];
    }
    loader = new RoseLoader(inFileName);      
    loader.addPropertyChangeListener(this);
    loader.setProgressIncrement(5);
    loader.setLower(25);
    loader.setUpper(50);
    lexer = new RoseLexer(loader);
    if (isTree.equals("true")) 
    {
      if (noPresentation.equals("true"))
      {
        parser = new RoseParser(lexer, true, true);
      }
      else
      {
        parser = new RoseParser(lexer, true, false);
      }
    }
    else 
    {
       if (noPresentation.equals("true"))
       {
         parser = new RoseParser(lexer, false, true);
       }
       else
       {
         parser = new RoseParser(lexer, false, false);
       }
    }
    
    parser.parse();
    System.out.println("parser is done");
  }

  public void writeRose() throws Exception
  { 
    RoseWriter writer = new RoseWriter(outFileName, parser.getVersionTree(), parser.getModelTree());
    writer.addPropertyChangeListener(this);
    writer.setProgressIncrement(20);
    writer.setLower(20);
    writer.setUpper(60);
    writer.write();
  }

  public void traverse() 
  { 
    BufferedReader buffReader = new BufferedReader(new InputStreamReader(System.in));
    String str = null;
    List v = new ArrayList();
    while (true) 
    {
      try 
      {
        parser.traverseTree(v);
        System.out.print("input path >");
        str = buffReader.readLine();      
        StringTokenizer st = new StringTokenizer(str);
        v.clear();
        while (st.hasMoreTokens()) 
        {
          String tok = st.nextToken();
          if (tok.equals("q"))
          {
            return;
          }
          Integer integ = new Integer(tok);
          v.add(integ);
        }
      } 
      catch ( Exception e ) 
      {
        System.out.println("RoseTree - Exception while readLine");
      }
    }
  }

  public void propertyChange(PropertyChangeEvent evt)
  {
    if (evt.getPropertyName().equals(RoseWriter.PROGRESS)) 
    {
      Integer oldValue = (Integer) evt.getOldValue();
      Integer newValue = (Integer) evt.getNewValue();
      System.out.println("saving ... "+oldValue+" "+newValue);
    } 
    else if (evt.getPropertyName().equals(RoseLoader.PROGRESS)) 
    {
      Integer oldValue = (Integer) evt.getOldValue();
      Integer newValue = (Integer) evt.getNewValue();
      System.out.println("loading ... "+oldValue+" "+newValue);
    }
  }
}
