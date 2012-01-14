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
package org.eclipse.emf.importer.java.ui;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.ui.actions.WorkspaceModifyOperation;

import org.eclipse.emf.common.util.BasicMonitor;
import org.eclipse.emf.common.util.Diagnostic;
import org.eclipse.emf.common.util.Monitor;
import org.eclipse.emf.importer.ModelImporter;
import org.eclipse.emf.importer.java.JavaImporter;
import org.eclipse.emf.importer.java.JavaImporterPlugin;
import org.eclipse.emf.importer.ui.contribution.base.ModelImporterPackagePage;


/**
 * @since 2.1.0
 */
public class JavaPackagePage extends ModelImporterPackagePage
{
  public JavaPackagePage(ModelImporter modelImporter, String pageName)
  {
    super(modelImporter, pageName);

    setDescription(JavaImporterPlugin.INSTANCE.getString("_UI_PackageSelection_description"));

  }

  public JavaImporter getJavaImporter()
  {
    return (JavaImporter)getModelImporter();
  }

  @Override
  protected void pageActivated(final boolean firstTime, int cause)
  {
    if (getJavaImporter().canImport())
    {
      setErrorMessage(null);
      getControl().getDisplay().asyncExec(new Runnable()
        {
          public void run()
          {
            computeEPackages();
            filterEPackagesTable(firstTime);
            getContainer().updateButtons();
          }
        });
    }
    else
    {
      setErrorMessage(JavaImporterPlugin.INSTANCE.getString("_UI_JavaProjectRequired_message"));
      setPageComplete(false);
    }
  }

  protected void computeEPackages()
  {
    WorkspaceModifyOperation initializeOperation = new WorkspaceModifyOperation()
      {
        @Override
        protected void execute(IProgressMonitor progressMonitor) throws CoreException
        {
          Monitor monitor = BasicMonitor.toMonitor(progressMonitor);
          try
          {
            computeEPackages(monitor);
          }
          catch (Exception e)
          {
            JavaImporterPlugin.INSTANCE.log(e);
          }
          finally
          {
            monitor.done();
          }
        }
      };

    try
    {
      getContainer().run(false, false, initializeOperation);
    }
    catch (Exception exception)
    {
      JavaImporterPlugin.INSTANCE.log(exception);
    }
  }

  protected void computeEPackages(Monitor monitor) throws Exception
  {
    Diagnostic diagnostic = getJavaImporter().computeEPackages(monitor);
    getJavaImporter().adjustEPackages(monitor);
    
    String message = JavaImporterPlugin.INSTANCE.getString("_UI_ProblemsEncounteredProcessingJava_message");
    handleDiagnostic(diagnostic, message, JavaImporterPlugin.INSTANCE.getString("_UI_LoadProblem_title"), message);
  }
}
