/**
 * Copyright (c) 2006 IBM Corporation and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: 
 *   IBM - Initial API and implementation
 */
package org.eclipse.emf.codegen.merge.java.facade.jdom;

import org.eclipse.jdt.core.jdom.IDOMInitializer;
import org.eclipse.jdt.core.jdom.IDOMMember;

import org.eclipse.emf.codegen.merge.java.facade.JMember;


/**
 * @since 2.2.0
 */
@SuppressWarnings({"deprecation", "unchecked"})
public abstract class JDOMJMember extends JDOMJNode implements JMember
{
  /**
   * @param member
   */
  protected JDOMJMember(IDOMMember member)
  {
    super(member);
  }

  @Override
  protected IDOMMember getWrappedObject()
  {
    return (IDOMMember)super.getWrappedObject();
  }

  @Override
  public int getFlags()
  {
    return getWrappedObject().getFlags();
  }
  
  @Override
  public void setFlags(int flags)
  {
    getWrappedObject().setFlags(flags);
  }

  public String getComment()
  {
    return getWrappedObject().getComment();
  }
  
  public void setComment(String comment)
  {
    getWrappedObject().setComment(comment);
  }
  
  @Override
  public String getContents()
  {
    String contents = super.getContents();
    return getWrappedObject().getNextNode() instanceof IDOMInitializer ?
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
