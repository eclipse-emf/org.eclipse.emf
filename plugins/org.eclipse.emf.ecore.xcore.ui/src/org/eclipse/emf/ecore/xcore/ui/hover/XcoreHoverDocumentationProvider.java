/**
 * Copyright (c) 2012 Eclipse contributors and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package org.eclipse.emf.ecore.xcore.ui.hover;

import static org.eclipse.xtext.xbase.ui.hover.HoverLinkHelper.createLinkWithLabel;

import java.util.List;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.xtext.common.types.TypesPackage;
import org.eclipse.xtext.ui.editor.hover.html.XtextElementLinks;
import org.eclipse.xtext.xbase.ui.hover.XbaseDeclarativeHoverSignatureProvider;
import org.eclipse.xtext.xbase.ui.hover.XbaseHoverDocumentationProvider;

import com.google.inject.Inject;

public class XcoreHoverDocumentationProvider extends XbaseHoverDocumentationProvider
{
  @Inject
  private XbaseDeclarativeHoverSignatureProvider hoverSignatureProvider;

  @Override
  protected String getDerivedElementInformation(EObject eObject)
  {
    StringBuffer buf = new StringBuffer();
    List<EObject> jvmElements = getFilteredDerivedElements(eObject, TypesPackage.Literals.JVM_IDENTIFIABLE_ELEMENT);
    if (jvmElements.size() > 0)
    {
      buf.append("<dt>Generated Java Artifacts:</dt>");
      for (EObject jvmElement : jvmElements)
      {
        buf.append("<dd>");
        buf.append(computeLinkToElement(jvmElement));
        buf.append("</dd>");
      }
    }
    return buf.toString();
  }

  private String computeLinkToElement(EObject jvmElement)
  {
    String imageURL = hoverSignatureProvider.getImageTag(jvmElement);
    String signature = hoverSignatureProvider.getDerivedOrSourceSignature(jvmElement);
    return imageURL + createLinkWithLabel(XtextElementLinks.XTEXTDOC_SCHEME, EcoreUtil.getURI(jvmElement), signature);
  }
}
