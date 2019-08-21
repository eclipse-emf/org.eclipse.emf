/**
 * Copyright (c) 2019 Eclipse contributors and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v2.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v20.html
 */
package org.eclipse.emf.codegen.presentation;


import org.eclipse.emf.codegen.presentation.JETEditor.JavaEditor;
import org.eclipse.jface.text.IRegion;
import org.eclipse.jface.text.ITextViewer;
import org.eclipse.jface.text.hyperlink.AbstractHyperlinkDetector;
import org.eclipse.jface.text.hyperlink.IHyperlink;


/**
 * @since 2.19
 */
public final class JETJavaHyperlinkDetector extends AbstractHyperlinkDetector
{
  public IHyperlink[] detectHyperlinks(ITextViewer textViewer, IRegion region, boolean canShowMultipleHyperlinks)
  {
    JavaEditor javaEditor = (JETEditor.JavaEditor)getAdapter(JETEditor.JavaEditor.class);
    return javaEditor == null ? null : javaEditor.detectHyperlinks(textViewer, region, canShowMultipleHyperlinks);
  }
}
