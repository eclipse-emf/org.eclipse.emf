/**
 * Copyright (c) 2011-2012 Eclipse contributors and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package org.eclipse.emf.ecore.xcore.ui;


import org.eclipse.emf.ecore.xcore.ui.contentassist.ImportingTypesProposalProvider;
import org.eclipse.emf.ecore.xcore.ui.hover.XcoreHoverProvider;
import org.eclipse.emf.ecore.xcore.ui.hyperlinking.XcoreHyperLinkHelper;
import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.eclipse.xtext.common.types.xtext.ui.ITypesProposalProvider;
import org.eclipse.xtext.ui.editor.hover.IEObjectHoverProvider;
import org.eclipse.xtext.ui.editor.hyperlinking.IHyperlinkHelper;


/**
 * Use this class to register components to be used within the IDE.
 */
public class XcoreUiModule extends org.eclipse.emf.ecore.xcore.ui.AbstractXcoreUiModule
{
  public XcoreUiModule(AbstractUIPlugin plugin)
  {
    super(plugin);
  }

  @Override
  public Class<? extends IHyperlinkHelper> bindIHyperlinkHelper()
  {
    return XcoreHyperLinkHelper.class;
  }

  @Override
  public Class<? extends ITypesProposalProvider> bindITypesProposalProvider()
  {
    return ImportingTypesProposalProvider.class;
  }
  
  @Override
  public Class<? extends IEObjectHoverProvider> bindIEObjectHoverProvider()
  {
    return XcoreHoverProvider.class;
  }
}
