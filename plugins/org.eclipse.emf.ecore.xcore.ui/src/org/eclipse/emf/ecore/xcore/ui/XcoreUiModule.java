/**
 * Copyright (c) 2011-2012 Eclipse contributors and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package org.eclipse.emf.ecore.xcore.ui;


import org.eclipse.emf.ecore.xcore.interpreter.IClassLoaderProvider;
import org.eclipse.emf.ecore.xcore.ui.builder.XcoreBuildParticipant;
import org.eclipse.emf.ecore.xcore.ui.builder.XcoreFileSystemAccess;
import org.eclipse.emf.ecore.xcore.ui.contentassist.ImportingTypesProposalProvider;
import org.eclipse.emf.ecore.xcore.ui.hover.XcoreHoverProvider;
import org.eclipse.emf.ecore.xcore.ui.hyperlinking.XcoreHyperLinkHelper;
import org.eclipse.emf.ecore.xcore.ui.refactoring.XcoreDependentElementsCalculator;
import org.eclipse.emf.ecore.xcore.ui.refactoring.XcoreRenameElementProcessor;
import org.eclipse.emf.ecore.xcore.ui.refactoring.XcoreRenameStrategy;
import org.eclipse.jface.text.source.CompositeRuler;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.eclipse.xtext.builder.EclipseResourceFileSystemAccess2;
import org.eclipse.xtext.builder.IXtextBuilderParticipant;
import org.eclipse.xtext.common.types.access.jdt.IJavaProjectProvider;
import org.eclipse.xtext.common.types.xtext.ui.ITypesProposalProvider;
import org.eclipse.xtext.ui.editor.XtextSourceViewer;
import org.eclipse.xtext.ui.editor.XtextSourceViewerConfiguration;
import org.eclipse.xtext.ui.editor.hover.IEObjectHoverProvider;
import org.eclipse.xtext.ui.editor.hyperlinking.IHyperlinkHelper;
import org.eclipse.xtext.ui.editor.model.XtextDocument;
import org.eclipse.xtext.ui.refactoring.IDependentElementsCalculator;
import org.eclipse.xtext.ui.refactoring.IRenameStrategy;
import org.eclipse.xtext.ui.refactoring.impl.RenameElementProcessor;


/**
 * Use this class to register components to be used within the IDE.
 */
public class XcoreUiModule extends AbstractXcoreUiModule
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

  public Class<? extends RenameElementProcessor> bindRenameElementProcessor()
  {
    return XcoreRenameElementProcessor.class;
  }

  @Override
  public Class<? extends IRenameStrategy> bindIRenameStrategy()
  {
    return XcoreRenameStrategy.class;
  }

  @Override
  public Class<? extends IDependentElementsCalculator> bindIDependentElementsCalculator()
  {
    return XcoreDependentElementsCalculator.class;
  }

  @Override
  public Class<? extends IJavaProjectProvider> bindIJavaProjectProvider()
  {
    return XcoreJavaProjectProvider.class;
  }

  public Class<?extends EclipseResourceFileSystemAccess2> bindEclipseResourceFileSystemAccess2()
  {
    return XcoreFileSystemAccess.class;
  }

  @Override
  public Class<? extends IXtextBuilderParticipant> bindIXtextBuilderParticipant()
  {
    return XcoreBuildParticipant.class;
  }

  public Class<? extends IClassLoaderProvider> bindIClassLoaderProvider()
  {
    return XcoreJavaProjectProvider.class;
  }

  @SuppressWarnings("restriction")
  public Class<? extends org.eclipse.xtext.ui.editor.embedded.EmbeddedEditorFactory.Builder> bindBuilder()
  {
   return PatchedBuilder.class;
  }

  @SuppressWarnings("restriction")
  public static class PatchedBuilder extends org.eclipse.xtext.ui.editor.embedded.EmbeddedEditorFactory.Builder
  {
    @Override
    public org.eclipse.xtext.ui.editor.embedded.EmbeddedEditor withParent(Composite parent)
    {
      final XtextDocument document = documentProvider.get();
      XtextSourceViewerConfiguration viewerConfiguration = sourceViewerConfigurationProvider.get();
      final CompositeRuler annotationRuler = new CompositeRuler();
      final XtextSourceViewer viewer = sourceViewerFactory.createSourceViewer(parent, annotationRuler, null,  false,  SWT.BORDER | SWT.V_SCROLL | SWT.H_SCROLL);
      viewer.configure(viewerConfiguration);
      return
        new org.eclipse.xtext.ui.editor.embedded.EmbeddedEditor
           (document,
            viewer,
            viewerConfiguration,
            resourceProvider,
            new Runnable()
            {
              public void run()
              {
                // Do nothing.
              }
            })
        {
          @Override
          public org.eclipse.xtext.ui.editor.embedded.EmbeddedEditorModelAccess createPartialEditor(String prefix, String editablePart, String suffix, boolean insertLineBreaks)
          {
            return new org.eclipse.xtext.ui.editor.embedded.EmbeddedEditorModelAccess(viewer, resourceProvider, insertLineBreaks);
          }
       };
    }
  }
}
