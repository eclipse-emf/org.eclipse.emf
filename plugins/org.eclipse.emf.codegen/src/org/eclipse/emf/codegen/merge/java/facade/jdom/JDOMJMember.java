/**
 * <copyright>
 *
 * Copyright (c) 2006 IBM Corporation and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: 
 *   IBM - Initial API and implementation
 *
 * </copyright>
 *
 * $Id: JDOMJMember.java,v 1.1 2006/01/18 20:42:15 marcelop Exp $
 */
package org.eclipse.emf.codegen.merge.java.facade.jdom;

import org.eclipse.jdt.core.jdom.IDOMInitializer;
import org.eclipse.jdt.core.jdom.IDOMMember;

import org.eclipse.emf.codegen.merge.java.facade.JMember;


/**
 * @since 2.2.0
 */
public abstract class JDOMJMember extends JDOMJNode implements JMember
{
  /**
   * @param member
   */
  protected JDOMJMember(IDOMMember member)
  {
    super(member);
  }

  protected IDOMMember getIDOMMember()
  {
    return (IDOMMember)getIDOMNode();
  }

  public int getFlags()
  {
    return getIDOMMember().getFlags();
  }
  
  public void setFlags(int flags)
  {
    getIDOMMember().setFlags(flags);
  }

  public String getComment()
  {
    return getIDOMMember().getComment();
  }
  
  public void setComment(String comment)
  {
    getIDOMMember().setComment(comment);
  }
  
  public String getContents()
  {
    String contents = super.getContents();
    return getIDOMNode().getNextNode() instanceof IDOMInitializer ?
      splitLastComment(contents)[0] :
      contents;    
  }
  
  protected String[] splitLastComment(String contents)
  {
    String[] split = new String[]{contents, ""};
    char[] characters =  contents.toCharArray();
    LOOP:
    for (int i=characters.length-1; i>=0; i--)
    {
      switch (characters[i])
      {
        case ';':
        case '}':
        {
          for (; i < characters.length; i++)
          {
            switch (characters[i])
            {
              case '/':
              {
                while (characters[i-1] == ' ' || characters[i-1] == 9)
                {
                  i--;
                }
                split[0] = new String(characters, 0, i);
                split[1] = new String(characters, i, characters.length-i);
                break LOOP;
              }
            }
          }
          break LOOP;
        }
      }
    }
    return split;
  }
}
