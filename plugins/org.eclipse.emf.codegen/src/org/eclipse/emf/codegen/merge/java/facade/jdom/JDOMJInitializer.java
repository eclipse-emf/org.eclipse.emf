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
 * $Id: JDOMJInitializer.java,v 1.2 2006/02/21 06:17:17 marcelop Exp $
 */
package org.eclipse.emf.codegen.merge.java.facade.jdom;

import org.eclipse.jdt.core.jdom.IDOMInitializer;
import org.eclipse.jdt.core.jdom.IDOMMember;
import org.eclipse.jdt.core.jdom.IDOMNode;

import org.eclipse.emf.codegen.merge.java.facade.JInitializer;


/**
 * @since 2.2.0
 */
public class JDOMJInitializer extends JDOMJMember implements JInitializer
{
  /**
   * @param initializer
   */
  public JDOMJInitializer(IDOMInitializer initializer)
  {
    super(initializer);
  }

  protected IDOMInitializer getIDOMInitializer()
  {
    return (IDOMInitializer)getIDOMNode();
  }

  public String getBody()
  {
    String contents = getContents();
    int index = contents.indexOf("{");
    return index >= 0 ? contents.substring(index) : getIDOMInitializer().getBody();
  }
  
  public void setBody(String body)
  {
    getIDOMInitializer().setBody(body);
  }

  public String getName()
  {
    return getName(this);
  }
    
  protected String computeQualifiedName()
  {
    return computeQualifiedName(this);
  }
  
  public String getComment()
  {
    String comment = super.getComment();
    IDOMNode previousNode = getIDOMNode().getPreviousNode();
    if (comment == null && previousNode instanceof IDOMMember)
    {
      String text = splitLastComment(previousNode.getContents())[1];
      if (text.indexOf("/**") >= 0)
      {
        int index = text.lastIndexOf("*/");
        if (index > 0)
        {
          comment = text.substring(0, index + "*/".length());
        }
      }
    }
    return comment;
  }
  
  public String getContents()
  {
    String contents = super.getContents();
    IDOMNode previousNode = getIDOMNode().getPreviousNode();
    if (previousNode instanceof IDOMMember)
    {
      contents = splitLastComment(previousNode.getContents())[1] + contents;
    }
    return contents;
  }
}
