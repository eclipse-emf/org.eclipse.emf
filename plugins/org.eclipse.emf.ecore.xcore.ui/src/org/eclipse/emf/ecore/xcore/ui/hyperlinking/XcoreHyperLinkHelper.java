/**
 * Copyright (c) 2011-2012 Eclipse contributors and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package org.eclipse.emf.ecore.xcore.ui.hyperlinking;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.xcore.XNamedElement;
import org.eclipse.emf.ecore.xcore.mappings.XcoreMapper;
import org.eclipse.jface.text.Region;
import org.eclipse.xtext.common.types.xtext.ui.TypeAwareHyperlinkHelper;
import org.eclipse.xtext.resource.XtextResource;
import org.eclipse.xtext.ui.editor.hyperlinking.IHyperlinkAcceptor;

import com.google.inject.Inject;

public class XcoreHyperLinkHelper extends TypeAwareHyperlinkHelper
{
  @Inject
  private XcoreMapper mapper;

  @Override
  public void createHyperlinksTo(XtextResource from, Region region, EObject to, IHyperlinkAcceptor acceptor)
  {
	XNamedElement element = mapper.getToXcoreMapping(to).getXcoreElement();
    if (element != null)
    {
        super.createHyperlinksTo(from, region, element, acceptor);
    } else
    {
      super.createHyperlinksTo(from, region, to, acceptor);
    }
  }

}
