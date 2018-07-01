/**
 * Copyright (c) 2011-2012 Eclipse contributors and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 */
package org.eclipse.emf.xcore.ui.folding;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.xcore.XImportDirective;
import org.eclipse.emf.ecore.xcore.XPackage;
import org.eclipse.xtext.resource.ILocationInFileProvider;
import org.eclipse.xtext.ui.editor.folding.DefaultFoldingRegionProvider;
import org.eclipse.xtext.ui.editor.folding.IFoldingRegionAcceptor;
import org.eclipse.xtext.ui.editor.folding.IFoldingRegionAcceptorExtension;
import org.eclipse.xtext.util.ITextRegion;

import com.google.inject.Inject;

public class XcoreFoldingRegionProvider extends DefaultFoldingRegionProvider
{
  @Inject
  private ILocationInFileProvider locationInFileProvider;

  @Override
  protected void computeObjectFolding(EObject eObject, IFoldingRegionAcceptor<ITextRegion> foldingRegionAcceptor, boolean initiallyFolded)
  {
    if (eObject instanceof XPackage)
    {
      XPackage xPackage = (XPackage)eObject;
      EList<XImportDirective> importDirectives = xPackage.getImportDirectives();
      if (importDirectives.size() > 1)
      {
        XImportDirective first = importDirectives.get(0);
        XImportDirective last = importDirectives.get(importDirectives.size() - 1);
        ITextRegion firstFullTextRegion = locationInFileProvider.getFullTextRegion(first);
        ITextRegion firstSignificantTextRegion = locationInFileProvider.getSignificantTextRegion(first);
        ITextRegion lastFullTextRegion = locationInFileProvider.getFullTextRegion(last);
        ITextRegion lastSignificantTextRegion = locationInFileProvider.getSignificantTextRegion(last);
        ITextRegion fullTextRegion = firstFullTextRegion.merge(lastFullTextRegion);
        ITextRegion significantTextRegion = firstSignificantTextRegion.merge(lastSignificantTextRegion);
        int offset = fullTextRegion.getOffset();
        ((IFoldingRegionAcceptorExtension<ITextRegion>)foldingRegionAcceptor).accept(offset, fullTextRegion.getLength(), true, significantTextRegion);
      }
    }
    else
    {
      super.computeObjectFolding(eObject, foldingRegionAcceptor, initiallyFolded);
    }
  }

  @Override
  protected boolean isHandled(EObject eObject)
  {
    return super.isHandled(eObject) || eObject instanceof XPackage;
  }
}
