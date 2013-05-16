/**
 * Copyright (c) 2011-2012 Eclipse contributors and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package org.eclipse.emf.ecore.xcore.ui.outline;


import java.util.Iterator;
import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.xcore.XClassifier;
import org.eclipse.xtext.common.types.JvmTypeReference;
import org.eclipse.xtext.ui.editor.outline.IOutlineNode;
import org.eclipse.xtext.ui.editor.outline.impl.DefaultOutlineTreeProvider;


/**
 * Customization of the default outline structure.
 * 
 */
public class XcoreOutlineTreeProvider extends DefaultOutlineTreeProvider
{
  protected void _createNode(IOutlineNode parentNode, JvmTypeReference jvmTypeReference)
  {
    // Filter it from the outline.
  }

  protected boolean _isLeaf(XClassifier xClassifier)
  {
    // Efficiently test if there are contained objects other than the instance type.
    //
    JvmTypeReference instanceType = xClassifier.getInstanceType();
    List<EObject> children = xClassifier.eContents();
    if (instanceType == null)
    {
      return children.isEmpty();
    }
    else
    {
      for (Iterator<EObject> i = children.iterator(); i.hasNext(); )
      {
        if (i.next() != instanceType)
        {
          return false;
        }
      }
      return true;
    }
  }
}
