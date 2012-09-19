/**
 * Copyright (c) 2005-2008 IBM Corporation and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: 
 *   IBM - Initial API and implementation
 */
package org.eclipse.emf.exporter.ui.contribution.base;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.Platform;
import org.eclipse.core.runtime.preferences.IEclipsePreferences;
import org.eclipse.core.runtime.preferences.IPreferencesService;
import org.eclipse.core.runtime.preferences.InstanceScope;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.ui.IWorkbench;

import org.eclipse.emf.codegen.ecore.genmodel.GenModel;
import org.eclipse.emf.codegen.ecore.genmodel.provider.GenModelEditPlugin;
import org.eclipse.emf.common.ui.dialogs.DiagnosticDialog;
import org.eclipse.emf.common.util.Diagnostic;
import org.eclipse.emf.common.util.DiagnosticException;
import org.eclipse.emf.common.util.Monitor;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.converter.ui.contribution.base.ModelConverterWizard;
import org.eclipse.emf.exporter.ExporterPlugin;
import org.eclipse.emf.exporter.ModelExporter;
import org.osgi.service.prefs.BackingStoreException;


/**
 * @since 2.2.0
 */
public abstract class ModelExporterWizard extends ModelConverterWizard
{
  protected static final String PREFERENCE_SAVE_EXPORTER = "ModelExporterWizard.SaveExporter";
  protected static final String PREFERENCE_SAVE_PACKAGE_URI = "ModelExporterWizard.SavePackageURI";
  
  public ModelExporter getModelExporter()
  {
    return (ModelExporter)getModelConverter();
  }
  
  @Override
  public void init(IWorkbench workbench, IStructuredSelection selection)
  {
    super.init(workbench, selection);
    
    Object object = selection.getFirstElement();
    
    try
    {
      if (object instanceof IFile)
      {
        URI uri = URI.createPlatformResourceURI(((IFile)object).getFullPath().toString(), true);
        getModelExporter().loadGenModel(uri);
      }
      else if (object instanceof GenModel)
      {
        getModelExporter().setGenModel((GenModel)object);
      }
    }
    catch (DiagnosticException exception)
    {
      Diagnostic diagnostic = exception.getDiagnostic();
      DiagnosticDialog.openProblem
        (getShell(),
         GenModelEditPlugin.INSTANCE.getString("_UI_ModelProblems_title"),
         ExporterPlugin.INSTANCE.getString("_UI_InvalidModel_message"),
         diagnostic);
    }    

    readPreferencesSettings();

    if (getModelExporter().getDirectoryURI() == null)
    {
      GenModel genModel = getModelExporter().getGenModel();
      if (genModel != null && genModel.eResource() != null)
      {
        URI uri = genModel.eResource().getURI().trimSegments(1);
        getModelExporter().setDirectoryURI(uri.toString() + "/");
      }
    }
  }

  protected void readPreferencesSettings()
  {
    IPreferencesService preferencesService = Platform.getPreferencesService();
    
    ModelExporter modelExporter = getModelExporter();

    modelExporter.setSaveEPackageArtifactURI(preferencesService.getBoolean(ExporterPlugin.ID, PREFERENCE_SAVE_PACKAGE_URI, false, null));
    
    modelExporter.setSaveExporter(preferencesService.getBoolean(ExporterPlugin.ID, PREFERENCE_SAVE_EXPORTER, false, null));
  }

  @SuppressWarnings("deprecation")
  private static final InstanceScope INSTANCE_SCOPE = new InstanceScope();

  protected void writePreferencesSettings()
  {
    IEclipsePreferences node = INSTANCE_SCOPE.getNode(ExporterPlugin.ID);
    ModelExporter modelExporter = getModelExporter();

    node.putBoolean(PREFERENCE_SAVE_PACKAGE_URI, modelExporter.isSaveEPackageArtifactURI());
    node.putBoolean(PREFERENCE_SAVE_EXPORTER, modelExporter.isSaveExporter());
    try
    {
      node.flush();
    }
    catch (BackingStoreException exception)
    {
      ExporterPlugin.INSTANCE.log(exception);
    }
  }

  @Override
  protected Diagnostic doPerformFinish(Monitor monitor) throws Exception
  {
    Diagnostic diagnostic = getModelExporter().export(monitor);
    getModelExporter().save();
    writePreferencesSettings();
    return diagnostic;
  }  
}
