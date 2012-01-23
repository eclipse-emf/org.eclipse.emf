/**
 * Copyright (c) 2010 itemis AG (http://www.itemis.eu) and others.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package org.eclipse.emf.codegen.ecore.xtext.ui;


import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.eclipse.xtext.ui.LanguageSpecific;
import org.eclipse.xtext.ui.editor.IURIEditorOpener;
import org.eclipse.xtext.ui.editor.hover.IEObjectHoverProvider;
import org.eclipse.xtext.ui.resource.generic.EmfUiModule;


public class GenModelUIModule extends EmfUiModule
{

  public GenModelUIModule(AbstractUIPlugin plugin)
  {
    super(plugin);
  }

  @Override
  public void configureLanguageSpecificURIEditorOpener(com.google.inject.Binder binder)
  {
    binder.bind(IURIEditorOpener.class).annotatedWith(LanguageSpecific.class).to(GenModelEditorOpener.class);
  }

  public Class<? extends IEObjectHoverProvider> bindIEObjectHoverProvider()
  {
    return GenModelHoverProvider.class;
  }

}
