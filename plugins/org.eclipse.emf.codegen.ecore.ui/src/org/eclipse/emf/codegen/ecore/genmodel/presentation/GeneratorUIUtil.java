/**
 * Copyright (c) 2008 IBM Corporation and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: 
 *   IBM - Initial API and implementation
 */
package org.eclipse.emf.codegen.ecore.genmodel.presentation;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.SubProgressMonitor;
import org.eclipse.jface.window.Window;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.actions.WorkspaceModifyOperation;

import org.eclipse.emf.codegen.ecore.generator.Generator;
import org.eclipse.emf.codegen.ecore.genmodel.GenModel;
import org.eclipse.emf.codegen.ecore.genmodel.provider.GenModelEditPlugin;
import org.eclipse.emf.common.ui.dialogs.DiagnosticDialog;
import org.eclipse.emf.common.util.BasicDiagnostic;
import org.eclipse.emf.common.util.BasicMonitor;
import org.eclipse.emf.common.util.Diagnostic;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;

/**
 * @since 2.5
 */
public class GeneratorUIUtil
{
  private GeneratorUIUtil()
  {
    // Empty block
  }
  
  public static List<GenModel> loadGenModels(IProgressMonitor progressMonitor, List<URI> uris, Shell shell)
  {
    if (!uris.isEmpty())
    {
      progressMonitor.beginTask(GenModelEditPlugin.INSTANCE.getString("_UI_LoadingGenModel_message"), uris.size());
      List<GenModel> genModels = new ArrayList<GenModel>(uris.size());
      Map<String, Diagnostic> fileNameToErrorDiagnosticMap = new LinkedHashMap<String, Diagnostic>();
      ResourceSet resourceSet = new ResourceSetImpl();
      for (URI uri : uris)
      {
        progressMonitor.subTask(uri.toPlatformString(true));
        Resource resource = resourceSet.getResource(uri, true);
        if (resource != null && resource.getContents().size() == 1)
        {
          EObject eObject = resource.getContents().get(0);
          if (eObject instanceof GenModel)
          {
            GenModel genModel = (GenModel)eObject;
            genModel.reconcile();
            genModel.setCanGenerate(true);
            genModel.setValidateModel(true);
            Diagnostic diagnostic = genModel.diagnose();
            if (diagnostic.getSeverity() != Diagnostic.ERROR)
            {
              genModels.add((GenModel)eObject);
            }
            else
            {
              fileNameToErrorDiagnosticMap.put(uri.lastSegment(), diagnostic);
           }
          }
        }
        progressMonitor.worked(1);
      }
      
      if (shell != null && !fileNameToErrorDiagnosticMap.isEmpty())
      {
        String reason = GenModelEditPlugin.INSTANCE.getString("_UI_InvalidGenModel_message");
        
        StringBuilder sb = new StringBuilder();
        BasicDiagnostic diagnosticChain = new BasicDiagnostic("", Diagnostic.ERROR, reason, null);
        for (Map.Entry<String, Diagnostic> entry : fileNameToErrorDiagnosticMap.entrySet())
        {
          sb.append(", \'").append(entry.getKey()).append("'");           
          diagnosticChain.add(entry.getValue());
        }
                  
        String title = GenModelEditPlugin.INSTANCE.getString("_UI_Error_title");
        String message = genModels.isEmpty() ?
          GenModelEditPlugin.INSTANCE.getString("_UI_SelectGenModelInvalid_message") :
          GenModelEditPlugin.INSTANCE.getString("_UI_SomeSelectGenModelInvalid_message", new Object[]{sb.delete(0, ", ".length()).toString()});
                      
        if (DiagnosticDialog.open(shell, title, message, diagnosticChain) != Window.OK)
        {
          genModels.clear();
        }
      }

      return genModels;
    }
    return Collections.emptyList();
  }
    
  public static class GeneratorOperation extends WorkspaceModifyOperation
  {
    protected Shell shell;
    protected List<Object[]> generatorAndArgumentsList;
    protected String rootDiagnosticMessage = GenModelEditPlugin.INSTANCE.getString("_UI_GeneratorOperationDiagnostic_message");
    
    public GeneratorOperation(Shell shell)
    {
      this.shell = shell;
    }

    public GeneratorOperation(Shell shell, List<Object[]> generatorAndArgumentsList)
    {
      this(shell);
      this.generatorAndArgumentsList = generatorAndArgumentsList;
    }
    
    public void setRootDiagnosticMessage(String rootDiagnosticMessage)
    {
      this.rootDiagnosticMessage = rootDiagnosticMessage;
    }
    
    public String getRootDiagnosticMessage()
    {
      return rootDiagnosticMessage;
    }
    
    public void addGeneratorAndArguments(Generator generator, Object object, Object projectType, String projectTypeName)
    {
      if (generatorAndArgumentsList == null)
      {
        generatorAndArgumentsList = new ArrayList<Object[]>();
      }
      generatorAndArgumentsList.add(new Object[]{generator, object, projectType, projectTypeName});
    }
    
    @Override
    protected void execute(IProgressMonitor progressMonitor) throws CoreException, InvocationTargetException, InterruptedException
    {
      BasicDiagnostic diagnostic = new BasicDiagnostic(GenModelEditPlugin.ID, 0, getRootDiagnosticMessage(), null);
      progressMonitor.beginTask("", generatorAndArgumentsList.size());
      try
      {
        for (Object[] generatorAndArguments : generatorAndArgumentsList)
        {
          Generator generator = (Generator)generatorAndArguments[0];

          diagnostic.add(
            generator.generate(
              generatorAndArguments[1], 
              generatorAndArguments[2], 
              (String)generatorAndArguments[3], 
              BasicMonitor.toMonitor(new SubProgressMonitor(progressMonitor, 1))));
              
          if (!canContinue(diagnostic))
          {
            break;
          }
        }
        
        if (shell != null && diagnostic.getSeverity() != Diagnostic.OK)
        {
          final Diagnostic finalDiagnostic = diagnostic;
          shell.getDisplay().asyncExec
            (new Runnable()
             {
               public void run()
               {
                 DiagnosticDialog.openProblem
                   (shell, 
                    GenModelEditPlugin.INSTANCE.getString("_UI_GenerationProblems_title"),
                    GenModelEditPlugin.INSTANCE.getString("_UI_GenerationProblems_message"),
                    finalDiagnostic);              
               }
             });
        }
      }
      catch (Throwable throwable)
      {
        GenModelEditPlugin.INSTANCE.log(throwable);
      }
      progressMonitor.done();      
    }
    
    protected boolean canContinue(Diagnostic diagnostic)
    {
      return diagnostic.getSeverity() != Diagnostic.CANCEL;
    }    
  }  
}
