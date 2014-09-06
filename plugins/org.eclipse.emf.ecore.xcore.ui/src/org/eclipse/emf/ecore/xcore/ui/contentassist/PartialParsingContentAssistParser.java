/**
 * Copyright (c) 2014 itemis AG (http://www.itemis.eu) and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package org.eclipse.emf.ecore.xcore.ui.contentassist;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.xcore.XClassifier;
import org.eclipse.emf.ecore.xcore.ui.contentassist.antlr.XcoreParser;
import org.eclipse.xtext.nodemodel.ICompositeNode;
import org.eclipse.xtext.xbase.XBlockExpression;

public class PartialParsingContentAssistParser extends XcoreParser
{
  @Override
  protected String getReplacement(ICompositeNode node)
  {
    if (node.hasDirectSemanticElement())
    {
      EObject semanticElement = node.getSemanticElement();
      if (semanticElement instanceof XBlockExpression)
      {
        return "{}";
      }
      if (semanticElement instanceof XClassifier)
      {
        return "\nclass A {}\n";
      }
    }
    return null;
  }
}
