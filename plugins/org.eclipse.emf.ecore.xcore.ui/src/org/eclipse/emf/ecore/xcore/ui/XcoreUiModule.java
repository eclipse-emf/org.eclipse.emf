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
import org.eclipse.emf.ecore.xcore.ui.container.XcoreJavaProjectsState;
import org.eclipse.emf.ecore.xcore.ui.contentassist.ImportingTypesProposalProvider;
import org.eclipse.emf.ecore.xcore.ui.contentassist.XcoreVariableCompletions;
import org.eclipse.emf.ecore.xcore.ui.hover.XcoreHoverProvider;
import org.eclipse.emf.ecore.xcore.ui.hover.XcoreHoverSignatureProvider;
import org.eclipse.emf.ecore.xcore.ui.hyperlinking.XcoreHyperLinkHelper;
import org.eclipse.emf.ecore.xcore.ui.refactoring.XcoreDependentElementsCalculator;
import org.eclipse.emf.ecore.xcore.ui.refactoring.XcoreJavaElementFinder;
import org.eclipse.emf.ecore.xcore.ui.refactoring.XcoreReferenceFinder;
import org.eclipse.emf.ecore.xcore.ui.refactoring.XcoreRenameElementProcessor;
import org.eclipse.emf.ecore.xcore.ui.refactoring.XcoreRenameRefactoringProcessorFactory;
import org.eclipse.emf.ecore.xcore.ui.refactoring.XcoreRenameStrategy;
import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.eclipse.xtext.builder.EclipseResourceFileSystemAccess2;
import org.eclipse.xtext.builder.IXtextBuilderParticipant;
import org.eclipse.xtext.common.types.access.jdt.IJavaProjectProvider;
import org.eclipse.xtext.common.types.ui.refactoring.JdtRenameRefactoringProcessorFactory;
import org.eclipse.xtext.common.types.util.jdt.IJavaElementFinder;
import org.eclipse.xtext.common.types.xtext.ui.ITypesProposalProvider;
import org.eclipse.xtext.common.types.xtext.ui.JdtVariableCompletions;
import org.eclipse.xtext.resource.containers.IAllContainersState;
import org.eclipse.xtext.ui.editor.findrefs.IReferenceFinder;
import org.eclipse.xtext.ui.editor.hover.IEObjectHover;
import org.eclipse.xtext.ui.editor.hover.IEObjectHoverProvider;
import org.eclipse.xtext.ui.editor.hyperlinking.IHyperlinkHelper;
import org.eclipse.xtext.ui.refactoring.IDependentElementsCalculator;
import org.eclipse.xtext.ui.refactoring.IRenameStrategy;
import org.eclipse.xtext.ui.refactoring.impl.RenameElementProcessor;
import org.eclipse.xtext.xbase.ui.hover.XbaseDeclarativeHoverSignatureProvider;
import org.eclipse.xtext.xbase.ui.hover.XbaseDispatchingEObjectTextHover;

import com.google.inject.Inject;
import com.google.inject.Provider;


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

  @Override
  public Class<? extends IEObjectHover> bindIEObjectHover()
  {
    return XbaseDispatchingEObjectTextHover.class;
  }

  public Class<? extends XbaseDeclarativeHoverSignatureProvider> bindXbaseDeclarativeHoverSignatureProvider()
  {
    return XcoreHoverSignatureProvider.class;
  }

  public Class<? extends JdtVariableCompletions> bindJdtVariableCompletions()
  {
    return XcoreVariableCompletions.class;
  }

  public Class<? extends IReferenceFinder> bindIReferenceFinder()
  {
    return XcoreReferenceFinder.class;
  }

  public Class<? extends IJavaElementFinder> bindIJavaElementFinder()
  {
    return XcoreJavaElementFinder.class;
  }

  public Class<? extends JdtRenameRefactoringProcessorFactory> bindJdtRenameRefactoringProcessorFactory()
  {
    return XcoreRenameRefactoringProcessorFactory.class;
  }

  @Override
  public  Provider<IAllContainersState> provideIAllContainersState()
  {
   return 
     new Provider<IAllContainersState>()
     {
       @Inject
       XcoreJavaProjectsState instance;
   
       public IAllContainersState get()
       {
         return instance;
       }
     };
  }
}
