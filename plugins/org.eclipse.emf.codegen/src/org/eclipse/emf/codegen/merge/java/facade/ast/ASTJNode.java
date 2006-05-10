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
 * $Id: ASTJNode.java,v 1.1 2006/05/10 20:33:16 marcelop Exp $
 */
package org.eclipse.emf.codegen.merge.java.facade.ast;

import java.util.Collections;
import java.util.List;

import org.eclipse.jdt.core.dom.ASTNode;
import org.eclipse.jdt.core.dom.Javadoc;

import org.eclipse.emf.codegen.merge.java.facade.AbstractJNode;
import org.eclipse.emf.codegen.merge.java.facade.FacadeFlags;
import org.eclipse.emf.codegen.merge.java.facade.JNode;

/**
 * @since 2.2.0
 */
public abstract class ASTJNode extends AbstractJNode
{
  protected ASTJNode(ASTNode astNode)
  {
    super(astNode);
  }
  
  protected ASTNode getASTNode()
  {
    return (ASTNode)getWrappedObject();
  }
  
  public void setFlags(int flags)
  {
    getASTNode().setFlags(flags);
  }

  public JNode getParent()
  {
    return getFacadeHelper().convertToNode(getASTNode().getParent());
  }
  
  public List getChildren()
  {
    return Collections.EMPTY_LIST;
  }  
  
  public int getFlags()
  {
    return FacadeFlags.DEFAULT;
  }
    
  public String getContents()
  {
    return getContents(this.getASTNode());
  }
  
  protected String getContents(ASTNode astNode)
  {
    if (astNode != null)
    {
      ASTJCompilationUnit compilationUnit = (ASTJCompilationUnit)facadeHelper.getCompilationUnit(this);
      char[] originalContents = compilationUnit.getOriginalContents();
      if (originalContents != null)
      {
        int start = compilationUnit.getASTCompilationUnit().getExtendedStartPosition(astNode);
        int length = compilationUnit.getASTCompilationUnit().getExtendedLength(astNode);
        
        StringBuffer contents = new StringBuffer();
        contents.append(originalContents, start, length);      
        int max = originalContents.length;
        int index = length + start;
        while (index < max)
        {
          char c = originalContents[index++];
          if (Character.isWhitespace(c))
          {
            contents.append(c);
          }
          else
          {
            break;
          }
        }
        return contents.toString();
      }
    }
    return null;    
  }
  
  protected String toString(Javadoc javadoc)
  {
    if (javadoc != null)
    {
      String contents = getContents(javadoc);
      return contents != null ? contents : ASTFacadeHelper.toString(javadoc);
    }
    else
    {
      return null;
    }    
  }
}
