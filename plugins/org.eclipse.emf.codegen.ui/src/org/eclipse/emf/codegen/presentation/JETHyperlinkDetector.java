/**
 * Copyright (c) 2019 Eclipse contributors and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 */
package org.eclipse.emf.codegen.presentation;


import org.eclipse.jface.text.IRegion;
import org.eclipse.jface.text.ITextViewer;
import org.eclipse.jface.text.hyperlink.AbstractHyperlinkDetector;
import org.eclipse.jface.text.hyperlink.IHyperlink;


/**
 * @since 2.19
 */
public final class JETHyperlinkDetector extends AbstractHyperlinkDetector
{
  public IHyperlink[] detectHyperlinks(ITextViewer textViewer, IRegion region, boolean canShowMultipleHyperlinks)
  {
    JETEditor jetEditor = (JETEditor)getAdapter(JETEditor.class);
    return jetEditor == null ? null : jetEditor.detectHyperlinks(textViewer, region, canShowMultipleHyperlinks);
  }
}
