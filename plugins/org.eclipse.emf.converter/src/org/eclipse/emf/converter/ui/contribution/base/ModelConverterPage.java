/**
 * Copyright (c) 2005-2006 IBM Corporation and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: 
 *   IBM - Initial API and implementation
 */
package org.eclipse.emf.converter.ui.contribution.base;

import org.eclipse.jface.wizard.IWizardPage;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.Listener;

import org.eclipse.emf.common.util.Diagnostic;
import org.eclipse.emf.converter.ModelConverter;
import org.eclipse.emf.converter.util.ConverterUIUtil;


/**
 * @since 2.2.0
 */
public abstract class ModelConverterPage extends WizardPage implements Listener
{
  protected static class PageDiagnosticHandler extends ConverterUIUtil.DiagnosticHandler
  {
    protected ModelConverterPage modelConverterPage;
    
    public PageDiagnosticHandler(ModelConverterPage modelConverterPage)
    {
      this.modelConverterPage = modelConverterPage;
    }
    
    @Override
    protected boolean doMessages()
    {
      return true;
    }
    
    @Override
    protected void setMessage(String message)
    {
      modelConverterPage.setMessage(message);
    }
    
    @Override
    protected void setMessage(String message, int messageType)
    {
      modelConverterPage.setMessage(message, messageType);
    }
    
    @Override
    protected void setErrorMessage(String message)
    {
      modelConverterPage.setErrorMessage(message);
    }
  }
  
  public static final int CAUSE_UNKNOWN = 0;
  public static final int CAUSE_BACK = 1;
  public static final int CAUSE_NEXT = 2;
  public static final int CAUSE_FINISH = 3;
  public static final int CAUSE_CANCEL = 4;
    
  protected ModelConverter modelConverter;
  protected boolean neverVisible = true;
  protected boolean forwardDirection = true;
  protected boolean handlingEvent = true;
  
  protected PageDiagnosticHandler pageDiagnosticHandler;

  protected ModelConverterPage(ModelConverter modelConverter, String pageName)
  {
    super(pageName);
    this.modelConverter = modelConverter;
    setPageComplete(false);
  }

  @Override
  public void dispose()
  {
    modelConverter = null;
    pageDiagnosticHandler = null;
    super.dispose();
  }

  protected ModelConverter getModelConverter()
  {
    return modelConverter;
  }

  protected void pageActivated(boolean firstTime, int cause)
  {
    // Subclasses may override
  }

  protected void pageDeactivated(int cause)
  {
    // Subclasses may override
  }
  
  @Override
  public IWizardPage getNextPage()
  {
    forwardDirection = true;
    return super.getNextPage();
  }
  
  @Override
  public IWizardPage getPreviousPage()
  {
    forwardDirection = false;
    return super.getPreviousPage();
  }

  @Override
  public boolean isPageComplete()
  {
    return getErrorMessage() == null;
  }
  
  public boolean isHandlingEvent()
  {
    return handlingEvent;
  }
  
  public void setHandlingEvent(boolean handlingEvent)
  {
    this.handlingEvent = handlingEvent;
  }

  public void handleEvent(Event event)
  {
    if (isHandlingEvent())
    {
      doHandleEvent(event);
    }
  }
  
  protected void doHandleEvent(Event event)
  {
    // Subclasses may override
  }

  protected void handleDiagnostic(Diagnostic diagnostic)
  {
    handleDiagnostic(diagnostic, null, null, null);
  }
  
  protected final void handleDiagnostic(Diagnostic diagnostic, String message, String dialogTitle, String dialogMessage)
  {
    if (pageDiagnosticHandler == null)
    {
      pageDiagnosticHandler = new PageDiagnosticHandler(this);
    }
    pageDiagnosticHandler.handleDiagnostic(diagnostic, message, dialogTitle, dialogMessage);
  }
}
