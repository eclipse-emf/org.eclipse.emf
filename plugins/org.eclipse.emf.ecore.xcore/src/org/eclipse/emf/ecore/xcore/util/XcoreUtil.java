/**
 * Copyright (c) 2012 Eclipse contributors and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package org.eclipse.emf.ecore.xcore.util;

import java.util.Iterator;
import java.util.NoSuchElementException;

import org.eclipse.emf.codegen.ecore.genmodel.GenClassifier;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.xcore.XAnnotation;
import org.eclipse.emf.ecore.xcore.XGenericType;
import org.eclipse.xtext.CrossReference;
import org.eclipse.xtext.common.types.JvmAnnotationType;
import org.eclipse.xtext.common.types.JvmEnumerationType;
import org.eclipse.xtext.common.types.JvmGenericType;
import org.eclipse.xtext.common.types.JvmType;
import org.eclipse.xtext.common.types.JvmTypeReference;
import org.eclipse.xtext.nodemodel.INode;
import org.eclipse.xtext.nodemodel.util.NodeModelUtils;
import org.eclipse.xtext.xbase.XConstructorCall;
import org.eclipse.xtext.xbase.XFeatureCall;
import org.eclipse.xtext.xbase.XTypeLiteral;

final public class XcoreUtil
{
  public static Iterable<INode> importableCrossReferences(EObject eObject)
  {
    return new XcoreCrossReferencer(eObject);
  }

  private static class XcoreCrossReferencer implements Iterator<INode>, Iterable<INode>
  {
    private Iterator<INode> nodes;
    private INode node;

    public XcoreCrossReferencer(EObject eObject)
    {
      nodes = NodeModelUtils.findActualNodeFor(eObject).getAsTreeIterable().iterator();
      scan();
    }
   
    public Iterator<INode> iterator()
    {
      return this;
    }
 
    private void scan()
    {
      node = null;
      while (nodes.hasNext())
      {
        INode nextNode = nodes.next();
        EObject grammarElement = nextNode.getGrammarElement();
        if (grammarElement instanceof CrossReference)
        {
          EObject semanticElement = nextNode.getSemanticElement();
          if (semanticElement instanceof JvmTypeReference)
          {
            JvmType jvmType = ((JvmTypeReference)semanticElement).getType();
            if (jvmType instanceof JvmGenericType ||
                  jvmType instanceof JvmEnumerationType ||
                  jvmType instanceof JvmAnnotationType)
            {
              node = nextNode;
              break;
            }
          }
          else if (semanticElement instanceof XGenericType && ((XGenericType)semanticElement).getType() instanceof GenClassifier ||
                     semanticElement instanceof XAnnotation && ((XAnnotation)semanticElement).getSource() != null ||
                     semanticElement instanceof XConstructorCall ||
                     semanticElement instanceof XFeatureCall && ((XFeatureCall)semanticElement).isTypeLiteral() ||
                     semanticElement instanceof XTypeLiteral)
          {
            node = nextNode;
            break;
          }
        }
      }
    }

    public boolean hasNext()
    {
      return node != null;
    }
 
    public INode next()
    {
      if (node == null)
      {
        throw new NoSuchElementException();
      }
      INode result = node;
      scan();
      return result;
    }
 
    public void remove()
    {
      throw new UnsupportedOperationException();
    }
 }

  private XcoreUtil()
  {
  }

}
