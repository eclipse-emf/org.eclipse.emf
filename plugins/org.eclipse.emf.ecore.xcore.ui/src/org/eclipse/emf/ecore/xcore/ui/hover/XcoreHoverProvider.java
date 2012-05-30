/**
 * Copyright (c) 2012 Eclipse contributors and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package org.eclipse.emf.ecore.xcore.ui.hover;


import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.Status;
import org.eclipse.emf.codegen.ecore.genmodel.GenFeature;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.xcore.ui.internal.XcoreActivator;
import org.eclipse.emf.ecore.xcore.ui.refactoring.XcoreJavaElementFinder;
import org.eclipse.jdt.core.IJavaElement;
import org.eclipse.jdt.ui.JavaUI;
import org.eclipse.jface.action.ToolBarManager;
import org.eclipse.jface.text.DefaultInformationControl;
import org.eclipse.jface.text.IInformationControl;
import org.eclipse.jface.text.IInformationControlCreator;
import org.eclipse.jface.text.IInputChangedListener;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.xtext.common.types.JvmIdentifiableElement;
import org.eclipse.xtext.ui.editor.hover.html.IXtextBrowserInformationControl;
import org.eclipse.xtext.ui.editor.hover.html.XtextBrowserInformationControlInput;
import org.eclipse.xtext.xbase.ui.hover.XbaseHoverProvider;
import org.eclipse.xtext.xbase.ui.hover.XbaseInformationControl;
import org.eclipse.xtext.xbase.ui.hover.XbaseInformationControlInput;

import com.google.inject.Inject;


public class XcoreHoverProvider extends XbaseHoverProvider
{
  @Inject
  protected XcoreJavaElementFinder javaElementFinder;

  @Override
  protected boolean hasHover(EObject eObject)
  {
    return eObject instanceof GenFeature || super.hasHover(eObject);
  }

  @Override
  public IInformationControlCreator getInformationPresenterControlCreator()
  {
    if (presenterControlCreator == null)
    {
      presenterControlCreator = new XCorePresenterControlCreator();
    }
    return presenterControlCreator;
  }

  public class XCorePresenterControlCreator extends PresenterControlCreator
  {
    @Override
    public IInformationControl doCreateInformationControl(Shell parent)
    {
      if (XbaseInformationControl.isAvailable(parent))
      {
        ToolBarManager toolBarManager = new ToolBarManager(SWT.FLAT);
        IXtextBrowserInformationControl control = new XbaseInformationControl(parent, "org.eclipse.jdt.ui.javadocfont", toolBarManager, xbaseHoverConfiguration);
        configureControl(control, toolBarManager, "org.eclipse.jdt.ui.javadocfont");
        return control;
      }
      else
      {
        return new DefaultInformationControl(parent, true);
      }
    }

    @Override
    protected void configureControl(final IXtextBrowserInformationControl control, ToolBarManager toolBarManager, String font)
    {
      final BackAction backAction = new BackAction(control);
      backAction.setEnabled(false);
      toolBarManager.add(backAction);
      final ForwardAction forwardAction = new ForwardAction(control);
      toolBarManager.add(forwardAction);
      forwardAction.setEnabled(false);
      final ShowInJavadocViewAction showInJavadocViewAction = new ShowInJavadocViewAction(control);
      toolBarManager.add(showInJavadocViewAction);
      showInJavadocViewAction.setEnabled(false);
      final OpenDeclarationAction openDeclarationAction = new XcoreOpenDeclarationAction(control);
      toolBarManager.add(openDeclarationAction);
      IInputChangedListener inputChangeListener =
        new IInputChangedListener()
        {
          public void inputChanged(Object newInput)
          {
            backAction.update();
            forwardAction.update();
            if (newInput != null && newInput instanceof XbaseInformationControlInput)
            {
              openDeclarationAction.setEnabled(true);
              if (((XtextBrowserInformationControlInput)newInput).getInputElement() != null)
              {
                showInJavadocViewAction.setEnabled(true);
              }
            }
          }
        };
      control.addInputChangeListener(inputChangeListener);
      toolBarManager.update(true);
      addLinkListener(control);
    }
  }

  protected class XcoreOpenDeclarationAction extends OpenDeclarationAction
  {
    protected IXtextBrowserInformationControl informationControl;

    public XcoreOpenDeclarationAction(IXtextBrowserInformationControl informationControl)
    {
      super(informationControl);
      this.informationControl = informationControl;
    }

    @Override
    public void run()
    {
      if (informationControl.getInput() instanceof XtextBrowserInformationControlInput)
      {
        XtextBrowserInformationControlInput infoInput = (XtextBrowserInformationControlInput)informationControl.getInput();
        informationControl.notifyDelayedInputChange(null);
        informationControl.dispose();
        EObject element = infoInput.getElement();
        Object inputElement = infoInput.getInputElement();
        if (inputElement == null && element instanceof JvmIdentifiableElement)
        {
          inputElement = javaElementFinder.findElementFor((JvmIdentifiableElement)element);
        }

        if (inputElement instanceof IJavaElement)
        {
          try
          {
            JavaUI.openInEditor((IJavaElement)inputElement);
          }
          catch (CoreException exception)
          {
            IStatus status = exception.getStatus();
            XcoreActivator.getInstance().getLog().log(new Status(status.getSeverity(), status.getPlugin(), status.getMessage(), exception));
          }
        }
        else
        {
          if (uriEditorOpener != null)
          {
            uriEditorOpener.open(createURI(element), true);
          }
        }
      }
    }
  }
}
