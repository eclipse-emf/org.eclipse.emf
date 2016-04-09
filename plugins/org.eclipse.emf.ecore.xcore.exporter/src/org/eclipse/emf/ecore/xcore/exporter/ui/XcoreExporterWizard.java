/**
 * Copyright (c) 2005-2012 IBM Corporation and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *   IBM - Initial API and implementation
 */
package org.eclipse.emf.ecore.xcore.exporter.ui;

import org.eclipse.emf.common.util.Diagnostic;
import org.eclipse.emf.converter.ModelConverter;
import org.eclipse.emf.ecore.xcore.exporter.XcoreExporter;
import org.eclipse.emf.ecore.xcore.exporter.XcoreExporterPlugin;
import org.eclipse.emf.exporter.ui.contribution.base.ModelExporterDirectoryURIPage;
import org.eclipse.emf.exporter.ui.contribution.base.ModelExporterOptionsPage;
import org.eclipse.emf.exporter.ui.contribution.base.ModelExporterPackagePage;
import org.eclipse.emf.exporter.ui.contribution.base.ModelExporterWizard;
import org.eclipse.swt.custom.BusyIndicator;
import org.eclipse.swt.widgets.Shell;

import com.google.inject.Inject;
import com.google.inject.Provider;


public class XcoreExporterWizard extends ModelExporterWizard
{
  @Inject
  protected Provider<XcoreExporter> xcoreExporterProvider;

  protected boolean retry;
  
  public XcoreExporterWizard()
  {
    super();
  }

  @Override
  protected ModelConverter createModelConverter()
  {
    return xcoreExporterProvider.get();
  }

  @Override
  public void addPages()
  {
    ModelExporterDirectoryURIPage directoryURIPage = new ModelExporterDirectoryURIPage(getModelExporter(), "XcoreExporterBaseLocationPage");
    directoryURIPage.setTitle(XcoreExporterPlugin.INSTANCE.getString("_UI_XcoreImport_title"));
    addPage(directoryURIPage);

    ModelExporterPackagePage packagePage = new ModelExporterPackagePage(getModelExporter(), "XcoreExporterGenModelDetailPage");
    packagePage.setTitle(XcoreExporterPlugin.INSTANCE.getString("_UI_XcoreImport_title"));
    addPage(packagePage);

    ModelExporterOptionsPage optionsPage = new ModelExporterOptionsPage(getModelExporter(), "XcoreExporterOptionsPage");
    optionsPage.setTitle(XcoreExporterPlugin.INSTANCE.getString("_UI_XcoreImport_title"));
    addPage(optionsPage);
  }
  
  @Override
  public boolean performFinish()
  {
    boolean result = super.performFinish();
    if (retry)
    {
      result = super.performFinish();
    }

    Shell shell = getShell();
    shell.setEnabled(false);
    BusyIndicator.showWhile(shell.getDisplay(), new Runnable()
      {
        @Override
        public void run()
        {
          ((XcoreExporter)getModelConverter()).waitForSaveJob();
        }
      });

    return result;
  }
  
  @Override
  protected void handleConvertDiagnostic(Diagnostic diagnostic)
  {
    if (diagnostic == XcoreExporter.RETRY)
    {
      retry = true;
    }
    else
    {
      super.handleConvertDiagnostic(diagnostic);
    }
  }
}
