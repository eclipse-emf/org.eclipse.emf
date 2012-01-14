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

import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;
import java.util.List;

import org.eclipse.jdt.core.jdom.IDOMNode;

import org.eclipse.emf.codegen.merge.java.facade.AbstractJNode;
import org.eclipse.emf.codegen.merge.java.facade.FacadeFlags;
import org.eclipse.emf.codegen.merge.java.facade.FacadeHelper;
import org.eclipse.emf.codegen.merge.java.facade.JNode;

/**
 * @since 2.2.0
 */
@SuppressWarnings({"deprecation", "unchecked", "rawtypes"})
public abstract class JDOMJNode extends AbstractJNode
{
  private JDOMFacadeHelper facadeHelper;
  private IDOMNode wrappedObject;
  
  protected JDOMJNode(IDOMNode idomNode)
  {
    wrappedObject = idomNode;
  }
  
  @Override
  public void dispose()
  {
    facadeHelper = null;
    wrappedObject = null;
  }
  
  @Override
  public boolean isDisposed()
  {
    return wrappedObject == null;
  }
  
  @Override
  public JDOMFacadeHelper getFacadeHelper()
  {
    return facadeHelper;
  }
  
  @Override
  public void setFacadeHelper(FacadeHelper facadeHelper)
  {
    this.facadeHelper = (JDOMFacadeHelper)facadeHelper;
  }
  
  @Override
  protected IDOMNode getWrappedObject()
  {
    return wrappedObject;
  }

  public String getName()
  {
    return getWrappedObject().getName();
  }
  
  public void setName(String name)
  {
    getWrappedObject().setName(name);
  }
  
  @Override
  public int getFlags()
  {
    return FacadeFlags.DEFAULT;
  }
  
  public void setFlags(int flags)
  {
    // Ignore.
  }

  public String getContents()
  {
    return getWrappedObject().getContents();
  }

  public JNode getParent()
  {
    return getFacadeHelper().convertToNode(getWrappedObject().getParent());
  }

  @Override
  public List getChildren()
  {
    if (!isDisposed())
    {
      List children = new ArrayList();
      for (Enumeration e = getWrappedObject().getChildren(); e.hasMoreElements();)
      {
        IDOMNode node = (IDOMNode)e.nextElement();
        JNode jNode = getFacadeHelper().convertToNode(node);
        children.add(jNode);
      }
      return Collections.unmodifiableList(children);
    }
    return Collections.emptyList();
  }
}
