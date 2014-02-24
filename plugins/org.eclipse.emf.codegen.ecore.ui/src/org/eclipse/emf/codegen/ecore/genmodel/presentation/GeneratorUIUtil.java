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

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.OperationCanceledException;
import org.eclipse.core.runtime.Status;
import org.eclipse.core.runtime.SubProgressMonitor;
import org.eclipse.core.runtime.jobs.Job;
import org.eclipse.jdt.core.ICompilationUnit;
import org.eclipse.jface.operation.IRunnableContext;
import org.eclipse.jface.window.Window;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.actions.WorkspaceModifyOperation;
import org.eclipse.ui.progress.UIJob;
import org.eclipse.emf.codegen.ecore.CodeGenEcorePlugin;
import org.eclipse.emf.codegen.ecore.generator.Generator;
import org.eclipse.emf.codegen.ecore.generator.Generator.CleanupScheduler;
import org.eclipse.emf.codegen.ecore.genmodel.GenModel;
import org.eclipse.emf.codegen.ecore.genmodel.provider.GenModelEditPlugin;
import org.eclipse.emf.common.CommonPlugin;
import org.eclipse.emf.common.ui.dialogs.DiagnosticDialog;
import org.eclipse.emf.common.util.BasicDiagnostic;
import org.eclipse.emf.common.util.BasicMonitor;
import org.eclipse.emf.common.util.Diagnostic;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.plugin.EcorePlugin;
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

  /**
   * @since 2.10
   */
  public static final CleanupScheduler CLEANUP_SCHEDULER;
  static
  {
    CleanupScheduler cleanupScheduler = null;
    try
    {
      cleanupScheduler = new CleanupSchedulerImpl();
    }
    catch (Exception e)
    {
      // Ignore if JDT UI isn't available.
    }
    CLEANUP_SCHEDULER = cleanupScheduler;
  }

  private static class CleanupSchedulerImpl implements CleanupScheduler
  {
    private final Set<ICompilationUnit> compilationUnits = new LinkedHashSet<ICompilationUnit>();

    private final Class<?> javaPluginClass = CommonPlugin.loadClass("org.eclipse.jdt.ui", "org.eclipse.jdt.internal.ui.JavaPlugin");
    private final Method getDefaultMethod = javaPluginClass.getMethod("getDefault");
    private final Object javaPlugin = getDefaultMethod.invoke(null);
    private final Method getCleanUpRegistryMethod = javaPluginClass.getMethod("getCleanUpRegistry");
    private final Method createCleanUpsMethod = getCleanUpRegistryMethod.getReturnType().getMethod("createCleanUps");
    private final Class<?> cleanUpRefactoringClass = CommonPlugin.loadClass("org.eclipse.jdt.ui", "org.eclipse.jdt.internal.corext.fix.CleanUpRefactoring");
    private final Constructor<?> cleanupRefactoringConstructor = cleanUpRefactoringClass.getConstructor(String.class);
    private final Method setUseOptionsFromProfileMethod = cleanUpRefactoringClass.getMethod("setUseOptionsFromProfile", boolean.class);
    private final Class<?> iCleanUpClass = CommonPlugin.loadClass("org.eclipse.jdt.ui", "org.eclipse.jdt.ui.cleanup.ICleanUp");
    private final Method addCleanUpMethod = cleanUpRefactoringClass.getMethod("addCleanUp", iCleanUpClass);
    private final Class<?> refactoringClass = CommonPlugin.loadClass("org.eclipse.jdt.ui", "org.eclipse.ltk.core.refactoring.Refactoring");
    private final Class<?> refactoringExecutionHelperClass = CommonPlugin.loadClass("org.eclipse.jdt.ui", "org.eclipse.jdt.internal.ui.refactoring.RefactoringExecutionHelper");
    private final Constructor<?> refactoringExecutionHelperConstructor = refactoringExecutionHelperClass.getConstructor(refactoringClass, int.class, int.class, Shell.class, IRunnableContext.class);
    private final Method performMethod = refactoringExecutionHelperClass.getMethod("perform", boolean.class, boolean.class, boolean.class);

    private Job job;

    public CleanupSchedulerImpl() throws Exception
    {
      // TODO Auto-generated constructor stub
    }
    
    public synchronized Job getJob()
    {
      if (job == null)
      {
        job = new UIJob(Display.getDefault(), "Cleanup Generated Java")
          {
            @Override
            public IStatus runInUIThread(IProgressMonitor monitor)
            {
              try
              {
                Set<ICompilationUnit> compilationUnits = getScheduledCompilationUnits();
                ICompilationUnit[] units = compilationUnits.toArray(new ICompilationUnit[compilationUnits.size()]);

                // ICleanUp[] cleanUps = JavaPlugin.getDefault().getCleanUpRegistry().createCleanUps();
                //
                Object cleanUpRegistry = getCleanUpRegistryMethod.invoke(javaPlugin);
                Object[] cleanups = (Object[])createCleanUpsMethod.invoke(cleanUpRegistry);

                // CleanUpRefactoring cleanupRefactoring= new CleanUpRefactoring("Cleanup Generated Java");
                //
                Object cleanUpRefactoring = cleanupRefactoringConstructor.newInstance("Clean Generated Java");
                
                Method addCompilationUnitMethod = cleanUpRefactoringClass.getMethod("addCompilationUnit", ICompilationUnit.class);
                for (ICompilationUnit unit : units)
                {
                  // cleanupRefactoring.addCompilationUnit(unit);
                  //
                  addCompilationUnitMethod.invoke(cleanUpRefactoring, unit);
                }

                // cleanupRefactoring.setUseOptionsFromProfile(true);
                //
                setUseOptionsFromProfileMethod.invoke(cleanUpRefactoring, Boolean.TRUE);
                
                for (Object cleanup : cleanups)
                {
                  // cleanupRefactoring.addCleanUp(cleanUps[i]);
                  //
                  addCleanUpMethod.invoke(cleanUpRefactoring, cleanup);
                }

                IWorkbenchWindow activeWorkbenchWindow = PlatformUI.getWorkbench().getActiveWorkbenchWindow();
                
                // RefactoringExecutionHelper helper= new RefactoringExecutionHelper(cleanupRefactoring, IStatus.INFO, RefactoringSaveHelper.SAVE_REFACTORING, activeWorkbenchWindow.getShell(), activeWorkbenchWindow);
                //
                Object refactoringExecutionHelper = refactoringExecutionHelperConstructor.newInstance(cleanUpRefactoring, IStatus.INFO, 4, activeWorkbenchWindow.getShell(), activeWorkbenchWindow);

                // refactoringExecutionHelper.perform(true, true, true);
                performMethod.invoke(refactoringExecutionHelper, Boolean.TRUE, Boolean.TRUE, Boolean.TRUE);
              }
              catch (Exception e)
              {
                if (e.getCause() instanceof OperationCanceledException)
                {
                  return Status.CANCEL_STATUS;
                }
                else
                {
                  return new Status(IStatus.ERROR, CodeGenEcorePlugin.ID, "Refactoring failure", e);
                }
              }

              return Status.OK_STATUS;
            }
          };

        job.setRule(EcorePlugin.getWorkspaceRoot());
        job.schedule();
      }

      return job;
    }

    public synchronized Set<ICompilationUnit> getScheduledCompilationUnits()
    {
      job = null;
      Set<ICompilationUnit> result = new LinkedHashSet<ICompilationUnit>(compilationUnits);
      compilationUnits.clear();
      return result;
    }

    public synchronized void schedule(Set<ICompilationUnit> compilationUnits)
    {
      if (!compilationUnits.isEmpty())
      {
        this.compilationUnits.addAll(compilationUnits);
        getJob();
      }
    }
  }
}
