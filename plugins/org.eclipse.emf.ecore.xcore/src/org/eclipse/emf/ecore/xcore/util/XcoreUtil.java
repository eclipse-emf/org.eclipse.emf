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

import org.eclipse.emf.codegen.ecore.genmodel.GenModelPackage;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.xcore.XcorePackage;
import org.eclipse.xtext.CrossReference;
import org.eclipse.xtext.common.types.TypesPackage;
import org.eclipse.xtext.nodemodel.INode;
import org.eclipse.xtext.nodemodel.util.NodeModelUtils;

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
        if (nextNode.getGrammarElement() instanceof CrossReference)
        {
          EClassifier classifier = ((CrossReference)nextNode.getGrammarElement()).getType().getClassifier();
          if (classifier instanceof EClass)
          {
            EClass eClass = (EClass)classifier;
 
            // We're interested in references to Jvm types or constructors, Xcore annotation directives, or GenModel base references that aren't references to features, i.e., opposites and keys.
            //
            boolean isJvmTypeReference = TypesPackage.Literals.JVM_TYPE.isSuperTypeOf(eClass) || TypesPackage.Literals.JVM_CONSTRUCTOR.isSuperTypeOf(eClass);
            if (isJvmTypeReference ||
                  (XcorePackage.Literals.XANNOTATION_DIRECTIVE.isSuperTypeOf(eClass)) ||
                  GenModelPackage.Literals.GEN_BASE.isSuperTypeOf(eClass) && !GenModelPackage.Literals.GEN_FEATURE.isSuperTypeOf(eClass))
            {
              node = nextNode;
              break;
            }
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
