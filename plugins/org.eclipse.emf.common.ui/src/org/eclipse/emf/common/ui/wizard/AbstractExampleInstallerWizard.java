/**
 * <copyright>
 *
 * Copyright (c) 2006-2007 IBM Corporation and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: 
 *   IBM - Initial API and implementation
 *
 * </copyright>
 *
 * $Id: AbstractExampleInstallerWizard.java,v 1.6 2008/02/21 15:25:11 emerks Exp $
 */
package org.eclipse.emf.common.ui.wizard;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.jar.JarFile;
import java.util.zip.ZipException;
import java.util.zip.ZipFile;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.SubProgressMonitor;
import org.eclipse.jface.operation.IRunnableWithProgress;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.window.IShellProvider;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.BusyIndicator;
import org.eclipse.swt.custom.SashForm;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.ui.IEditorRegistry;
import org.eclipse.ui.INewWizard;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.actions.DeleteResourceAction;
import org.eclipse.ui.actions.RenameResourceAction;
import org.eclipse.ui.actions.WorkspaceModifyOperation;
import org.eclipse.ui.dialogs.IOverwriteQuery;
import org.eclipse.ui.part.FileEditorInput;
import org.eclipse.ui.wizards.datatransfer.FileSystemStructureProvider;
import org.eclipse.ui.wizards.datatransfer.ImportOperation;
import org.eclipse.ui.wizards.datatransfer.ZipFileStructureProvider;

import org.eclipse.emf.common.CommonPlugin;
import org.eclipse.emf.common.ui.CommonUIPlugin;
import org.eclipse.emf.common.ui.dialogs.DiagnosticDialog;
import org.eclipse.emf.common.util.BasicDiagnostic;
import org.eclipse.emf.common.util.URI;


/**
 * <p>This abstract example installer wizard simply copies or unzips a number of files and
 * directories into the workspace, creating the projects to hold them. This wizard can be 
 * added as a new wizard to the new wizards dialog through the 
 * <tt>org.eclipse.ui.newWizards</tt> extension point.</p>
 * 
 * <p>Clients should subclass this class and override the {@link #getProjectDescriptors()}
 * method to provide the location and name of the project content that should be added to the 
 * workspace. Note that any projects that are already in the workspace will <i>not</i> be 
 * overwritten because the user could have made changes to them that would be lost.</p>
 * 
 * <p>It is highly recommended when registering subclasses to the new wizards extension point 
 * that the wizard declaration should have canFinishEarly = true. Any label and icon can be 
 * freely given to the wizard to suit the needs of the client.</p>
 * 
 * @since 2.2.0
 */
public abstract class AbstractExampleInstallerWizard extends Wizard implements INewWizard, IShellProvider
{
  public static class ProjectDescriptor
  {
    protected String name;
    protected URI contentURI;
    protected String description;

    protected IProject project;

    public URI getContentURI()
    {
      return contentURI;
    }

    public void setContentURI(URI contentURI)
    {
      this.contentURI = contentURI;
    }

    public String getName()
    {
      return name;
    }

    public void setName(String name)
    {
      this.name = name;
    }

    public String getDescription()
    {
      return description;
    }

    public void setDescription(String description)
    {
      this.description = description;
    }

    public IProject getProject()
    {
      if (project == null)
      {
        project = ResourcesPlugin.getWorkspace().getRoot().getProject(getName());
      }
      return project;
    }
  }
  
  public static class FileToOpen
  {
    protected String location;
    protected String editorID;
    
    protected IFile workspaceFile;
    
    public String getEditorID()
    {
      return editorID;
    }
    
    public void setEditorID(String editorID)
    {
      this.editorID = editorID;
    }
    
    public String getLocation()
    {
      return location;
    }
    
    public void setLocation(String location)
    {
      this.location = location;
    }
   
    public IFile getWorkspaceFile()
    {
      if (workspaceFile == null)
      {
        workspaceFile = ResourcesPlugin.getWorkspace().getRoot().getFile(new Path(getLocation()));
      }
      return workspaceFile;
    }
  }

  public class ProjectPage extends WizardPage
  {
    protected org.eclipse.swt.widgets.List projectList;
    protected Text descriptionText;
    protected Button deleteButton;
    protected Button deleteAllButton;
    protected Button renameButton;

    public ProjectPage(String pageName, String title, ImageDescriptor titleImage)
    {
      super(pageName, title, titleImage);
    }

    public void createControl(Composite parent)
    {
      SashForm sashForm = new SashForm(parent, SWT.VERTICAL);
      sashForm.setLayoutData(new GridData(GridData.FILL_BOTH | GridData.GRAB_VERTICAL));

      Composite composite = new Composite(sashForm, SWT.NONE);
      {
        GridLayout layout = new GridLayout(2, false);
        int margin = -5;
        int spacing = 3;
        layout.marginTop = margin;
        layout.marginLeft = margin;
        layout.marginRight = margin;
        layout.marginBottom = margin;
        layout.horizontalSpacing = spacing;
        layout.verticalSpacing = spacing;
        composite.setLayout(layout);
      }

      projectList = new org.eclipse.swt.widgets.List(composite, SWT.SINGLE | SWT.BORDER);
      projectList.setLayoutData(new GridData(GridData.FILL_BOTH));
      projectList.addSelectionListener(new SelectionAdapter()
        {
          @Override
          public void widgetSelected(SelectionEvent e)
          {
            itemSelected();
          }
        });
      projectList.setFocus();

      Composite buttonComposite = new Composite(composite, SWT.NONE);
      buttonComposite.setLayoutData(new GridData(GridData.VERTICAL_ALIGN_BEGINNING | GridData.HORIZONTAL_ALIGN_END));
      buttonComposite.setLayout(new GridLayout());
      {
        GridLayout layout = new GridLayout();
        int margin = -5;
        int spacing = 3;
        layout.marginTop = margin;
        layout.marginLeft = margin;
        layout.marginRight = margin;
        layout.marginBottom = margin;
        layout.horizontalSpacing = spacing;
        layout.verticalSpacing = spacing;
        buttonComposite.setLayout(layout);
      }

      renameButton = new Button(buttonComposite, SWT.PUSH);
      renameButton.setLayoutData(new GridData(GridData.HORIZONTAL_ALIGN_BEGINNING | GridData.FILL_HORIZONTAL));
      renameButton.setText(CommonUIPlugin.INSTANCE.getString("_UI_Rename_label"));
      renameButton.addSelectionListener(new SelectionAdapter()
        {
          @Override
          public void widgetSelected(SelectionEvent e)
          {
            renameExistingProject();
          }
        });
      renameButton.setEnabled(false);

      deleteButton = new Button(buttonComposite, SWT.PUSH);
      deleteButton.setLayoutData(new GridData(GridData.HORIZONTAL_ALIGN_BEGINNING | GridData.FILL_HORIZONTAL));
      deleteButton.setText(CommonUIPlugin.INSTANCE.getString("_UI_Delete_label"));
      deleteButton.addSelectionListener(new SelectionAdapter()
        {
          @Override
          public void widgetSelected(SelectionEvent e)
          {
            deleteExistingProject();
          }
        });
      deleteButton.setEnabled(false);

      Label label = new Label(buttonComposite, SWT.NONE);
      {
        GridData gridData = new GridData(GridData.FILL_BOTH);
        gridData.heightHint = 3;
        label.setLayoutData(gridData);
      }

      deleteAllButton = new Button(buttonComposite, SWT.PUSH);
      deleteAllButton.setLayoutData(new GridData(GridData.HORIZONTAL_ALIGN_BEGINNING | GridData.FILL_HORIZONTAL));
      deleteAllButton.setText(CommonUIPlugin.INSTANCE.getString("_UI_DeleteAll_label"));
      deleteAllButton.addSelectionListener(new SelectionAdapter()
        {
          @Override
          public void widgetSelected(SelectionEvent e)
          {
            deleteAllExistingProjects();
          }
        });
      deleteAllButton.setEnabled(false);

      descriptionText = new Text(sashForm, SWT.READ_ONLY | SWT.MULTI | SWT.WRAP | SWT.V_SCROLL | SWT.BORDER);
      {
        GridData gridData = new GridData(GridData.FILL_BOTH);
        gridData.heightHint = convertHeightInCharsToPixels(2);
        gridData.grabExcessVerticalSpace = true;
        descriptionText.setLayoutData(gridData);
      }

      refresh();
      sashForm.setWeights(new int []{ 70, 30 });
      setControl(sashForm);
    }

    public void refresh()
    {
      if (getProjectDescriptors().isEmpty())
      {
        setErrorMessage(CommonUIPlugin.INSTANCE.getString("_UI_NoProjectError_message"));
        setPageComplete(false);
      }
      else
      {
        int selectionIndex = projectList.getSelectionIndex();
        if (selectionIndex < 0)
        {
          selectionIndex = 0;
        }

        boolean atLeastOneExists = false;
        projectList.removeAll();
        for (ProjectDescriptor projectDescriptor : getProjectDescriptors())
        {
          String name = projectDescriptor.getName();
          boolean exists = projectDescriptor.getProject().exists();
          atLeastOneExists |= exists;
          String item = exists
            ? CommonUIPlugin.INSTANCE.getString("_UI_ExistingProjectName_message", new String []{ name })
            : CommonUIPlugin.INSTANCE.getString("_UI_ProjectName_message", new String []{ name });
          projectList.add(item);

          projectList.setData(item, projectDescriptor);
        }

        setErrorMessage(null);
        projectList.setSelection(selectionIndex);
        itemSelected();

        deleteAllButton.setEnabled(atLeastOneExists);
        setPageComplete(!atLeastOneExists);
      }
    }

    protected ProjectDescriptor getSelectedProjectDescriptor()
    {
      return (ProjectDescriptor)projectList.getData(projectList.getSelection()[0]);
    }

    protected void itemSelected()
    {
      ProjectDescriptor projectDescriptor = getSelectedProjectDescriptor();

      boolean exists = projectDescriptor.getProject().exists();
      deleteButton.setEnabled(exists);
      renameButton.setEnabled(exists);

      descriptionText.setText(projectDescriptor.getDescription() != null ? projectDescriptor.getDescription() : "");
    }

    protected void deleteAllExistingProjects()
    {
      List<IProject> projects = new ArrayList<IProject>();
      for (ProjectDescriptor projectDescriptor : getProjectDescriptors())
      {
        if (projectDescriptor.getProject().exists())
        {
          projects.add(projectDescriptor.getProject());
        }
      }
      
      deleteExistingProjects(projects);
    }

    protected void deleteExistingProject()
    {
      ProjectDescriptor projectDescriptor = getSelectedProjectDescriptor();
      if (projectDescriptor.getProject().exists())
      {
        List<IProject> projects = new ArrayList<IProject>();
        projects.add(projectDescriptor.getProject());
        deleteExistingProjects(projects);
      }
    }
    
    protected void deleteExistingProjects(List<IProject> existingProjects)
    {
      if (!existingProjects.isEmpty())
      {
        DeleteResourceAction deleteResourceAction = new DeleteResourceAction(AbstractExampleInstallerWizard.this);
        deleteResourceAction.selectionChanged(new StructuredSelection(existingProjects));
        deleteResourceAction.run();

        waitForDeleteJob(existingProjects);
        refresh();
      }
    }

    protected void waitForDeleteJob(final List<IProject> existingProjects)
    {
      // If the no project is deleted in the first 3s, this code
      // assumes that the user has canceled the delete job.
      BusyIndicator.showWhile(getShell().getDisplay(), new Runnable()
        {
          public void run()
          {
            int counter = 0;
            int initialSize = existingProjects.size();
            int size = initialSize;
            while (size > 0 && counter < 30)
            {
              for (Iterator<IProject> i = existingProjects.iterator(); i.hasNext();)
              {
                IProject project = i.next();
                if (!project.exists())
                {
                  i.remove();
                }
              }
              size = existingProjects.size();
              if (size > 0)
              {
                if (initialSize == size)
                {
                  counter++;
                }
                
                try
                {
                  Thread.sleep(100);
                }
                catch (InterruptedException e)
                {
                  // Ignore
                }
              }
            }
          }
        });
    }

    protected void renameExistingProject()
    {
      ProjectDescriptor projectDescriptor = getSelectedProjectDescriptor();
      if (projectDescriptor.getProject().exists())
      {
        RenameResourceAction renameResourceAction = new RenameResourceAction(AbstractExampleInstallerWizard.this);
        renameResourceAction.selectionChanged(new StructuredSelection(projectDescriptor.getProject()));
        renameResourceAction.run();
        projectDescriptor.project = null;
        refresh();
      }
    }
  }

  protected static final IOverwriteQuery OVERWRITE_ALL_QUERY = new IOverwriteQuery()
    {
      public String queryOverwrite(String pathString)
      {
        return IOverwriteQuery.ALL;
      }
    };

  protected IWorkbench workbench;
  protected IStructuredSelection structuredSelection;

  public AbstractExampleInstallerWizard()
  {
    setNeedsProgressMonitor(true);
    setWindowTitle(CommonUIPlugin.INSTANCE.getString("_UI_ExampleInstallerWizard_title"));
  }

  public void init(IWorkbench workbench, IStructuredSelection selection)
  {
    this.workbench = workbench;
    this.structuredSelection = selection;
  }

  protected abstract List<ProjectDescriptor> getProjectDescriptors();
  protected abstract List<FileToOpen> getFilesToOpen();

  protected ProjectPage projectPage;

  @Override
  public void dispose()
  {
    projectPage = null;
    super.dispose();
  }

  @Override
  public void addPages()
  {
    projectPage = new ProjectPage("projectPage", CommonUIPlugin.INSTANCE.getString("_UI_ProjectPage_title"), null);
    projectPage.setDescription(CommonUIPlugin.INSTANCE.getString("_UI_ProjectPage_description"));
    addPage(projectPage);
  }

  @Override
  public boolean performFinish()
  {
    final Exception exceptionWrapper = new Exception();

    try
    {
      getContainer().run(false, true, new IRunnableWithProgress()
        {
          public void run(IProgressMonitor monitor) throws InvocationTargetException, InterruptedException
          {
            monitor.beginTask(CommonUIPlugin.INSTANCE.getString("_UI_InstallingExample_message"), 2);

            WorkspaceModifyOperation op = new WorkspaceModifyOperation()
              {
                @Override
                protected void execute(IProgressMonitor monitor) throws CoreException, InvocationTargetException, InterruptedException
                {
                  try
                  {
                    installExample(monitor);
                  }
                  catch (Exception e)
                  {
                    exceptionWrapper.initCause(e);
                    throw new InterruptedException();
                  }
                }
              };  
            op.run(new SubProgressMonitor(monitor, 1));
            
            openFiles(new SubProgressMonitor(monitor, 1));
            monitor.done();
          }
        });
      
      return true;
    }
    catch (InterruptedException e)
    {
      if (exceptionWrapper.getCause() != null)
      {
        openErrorDialog(CommonUIPlugin.INSTANCE.getString("_UI_InstallExampleError_message"), exceptionWrapper.getCause());
      }
    }
    catch (InvocationTargetException e)
    {
      CommonUIPlugin.INSTANCE.log(e);
    }

    if (projectPage != null && !projectPage.getControl().isDisposed())
    {
      projectPage.refresh();
    }
    return false;
  }
  
  protected void installExample(IProgressMonitor progressMonitor) throws Exception
  {
    List<ProjectDescriptor> projectDescriptors = getProjectDescriptors();
    progressMonitor.beginTask(CommonUIPlugin.INSTANCE.getString("_UI_CreatingProjects_message"), 2 * projectDescriptors.size());
    for (ProjectDescriptor projectDescriptor : projectDescriptors)
    {
      ImportOperation importOperation = createImportOperation(projectDescriptor);
      createProject(projectDescriptor, new SubProgressMonitor(progressMonitor, 1));
      importOperation.setContext(getShell());
      importOperation.run(new SubProgressMonitor(progressMonitor, 1));
    }
    progressMonitor.done();
  }
  
  protected void openFiles(IProgressMonitor progressMonitor)
  {
    List<FileToOpen> filesToOpen = getFilesToOpen();
    if (!filesToOpen.isEmpty())
    {
      progressMonitor.beginTask(CommonUIPlugin.INSTANCE.getString("_UI_OpeningFiles_message"), filesToOpen.size());
      for (FileToOpen fileToOpen : filesToOpen)
      {
        IFile workspaceFile = fileToOpen.getWorkspaceFile();
        if (workspaceFile != null && workspaceFile.exists())
        {
          try
          {
            openEditor(workspaceFile, fileToOpen.getEditorID());
            progressMonitor.worked(1);
          }
          catch (PartInitException e)
          {
            CommonUIPlugin.INSTANCE.log(e);
          }
        }
      }
      progressMonitor.done();
    }    
  }

  protected void openErrorDialog(String message, Throwable throwable)
  {
    DiagnosticDialog.open(getShell(), CommonUIPlugin.INSTANCE.getString("_UI_Error_label"), message, BasicDiagnostic.toDiagnostic(throwable));
  }

  protected void createProject(ProjectDescriptor projectDescriptor, IProgressMonitor monitor) throws CoreException
  {
    monitor.beginTask(CommonUIPlugin.INSTANCE.getString("_UI_CreateProject_message", new String []{ projectDescriptor.getName() }), 3);

    IProject project = projectDescriptor.getProject();
    project.create(new SubProgressMonitor(monitor, 1));
    project.open(new SubProgressMonitor(monitor, 1));

    monitor.done();
  }

  protected ImportOperation createImportOperation(ProjectDescriptor projectDescriptor) throws Exception
  {
    URI contentURI = projectDescriptor.getContentURI();
    if (contentURI.hasTrailingPathSeparator())
    {
      return createDirectoryImportOperation(projectDescriptor);
    }
    else
    {
      return createFileImportOperation(projectDescriptor);
    }
  }

  protected ImportOperation createDirectoryImportOperation(ProjectDescriptor projectDescriptor) throws Exception
  {
    URI contentURI = projectDescriptor.getContentURI();
    if (contentURI.isPlatform())
    {
      contentURI = CommonPlugin.asLocalURI(contentURI);
    }

    String location = contentURI.toFileString();
    if (location != null)
    {
      File directory = new File(location);
      if (directory.isDirectory() && directory.canRead())
      {
        List<File> filesToImport = new ArrayList<File>();
        filesToImport.addAll(Arrays.asList(directory.listFiles()));

        ImportOperation importOperation = new ImportOperation(
          projectDescriptor.getProject().getFullPath(),
          directory,
          FileSystemStructureProvider.INSTANCE,
          OVERWRITE_ALL_QUERY,
          filesToImport);
        importOperation.setCreateContainerStructure(false);
        return importOperation;
      }
    }

    throw new Exception(CommonUIPlugin.INSTANCE.getString("_UI_DirectoryError_message", new String []{ contentURI.toString() }));
  }

  protected ImportOperation createFileImportOperation(ProjectDescriptor projectDescriptor) throws Exception
  {
    URI contentURI = projectDescriptor.getContentURI();
    if (contentURI.isPlatform())
    {
      contentURI = CommonPlugin.asLocalURI(contentURI);
    }

    String location = contentURI.toFileString();
    if (location != null)
    {
      File file = new File(location);
      if (file.isFile() && file.canRead())
      {
        if (isZipFile(file))
        {
          return createZipImportOperation(projectDescriptor, file);
        }
      }
    }

    throw new Exception(CommonUIPlugin.INSTANCE.getString("_UI_FileError_message", new String []{ contentURI.toString() }));
  }

  protected boolean isZipFile(File file)
  {
    try
    {
      ZipFile zipFile = new ZipFile(file);
      zipFile.close();
      return true;
    }
    catch (ZipException e)
    {
      // Ignore
    }
    catch (IOException e)
    {
      // Ignore
    }
    return false;
  }

  protected ImportOperation createZipImportOperation(ProjectDescriptor projectDescriptor, File file) throws Exception
  {
    ZipFile zipFile = file.getName().endsWith(".jar") ? new JarFile(file) : new ZipFile(file);
    ZipFileStructureProvider zipFileStructureProvider = new ZipFileStructureProvider(zipFile);

    return new ImportOperation(
      projectDescriptor.getProject().getFullPath(),
      zipFileStructureProvider.getRoot(),
      zipFileStructureProvider,
      OVERWRITE_ALL_QUERY);
  }
  
  protected IWorkbench getWorkbench()
  {
    return workbench;
  }

  protected IStructuredSelection getSelection()
  {
    return structuredSelection;
  }  
  
  protected void openEditor(IFile file, String editorID) throws PartInitException
  {
    IEditorRegistry editorRegistry = getWorkbench().getEditorRegistry();
    if (editorID == null || editorRegistry.findEditor(editorID) == null)
    {
      editorID = getWorkbench().getEditorRegistry().getDefaultEditor(file.getFullPath().toString()).getId();
    }
    
    IWorkbenchPage page = getWorkbench().getActiveWorkbenchWindow().getActivePage();
    page.openEditor(new FileEditorInput(file), editorID, true, IWorkbenchPage.MATCH_ID);
  }  
}
