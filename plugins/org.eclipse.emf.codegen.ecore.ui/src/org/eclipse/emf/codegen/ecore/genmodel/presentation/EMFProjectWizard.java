/**
 * <copyright> 
 *
 * Copyright (c) 2002-2004 IBM Corporation and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Common Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/cpl-v10.html
 * 
 * Contributors: 
 *   IBM - Initial API and implementation
 *
 * </copyright>
 *
 * $Id: EMFProjectWizard.java,v 1.8 2004/08/06 17:04:19 emerks Exp $
 */
package org.eclipse.emf.codegen.ecore.genmodel.presentation;


import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.core.runtime.MultiStatus;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Platform;
import org.eclipse.core.runtime.Status;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.core.JavaCore;
import org.eclipse.jdt.core.JavaModelException;
import org.eclipse.jface.dialogs.ErrorDialog;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.CheckStateChangedEvent;
import org.eclipse.jface.viewers.CheckboxTableViewer;
import org.eclipse.jface.viewers.CheckboxTreeViewer;
import org.eclipse.jface.viewers.ColumnWeightData;
import org.eclipse.jface.viewers.ICheckStateListener;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.TableLayout;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.wizard.IWizardPage;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.FocusAdapter;
import org.eclipse.swt.events.FocusEvent;
import org.eclipse.swt.events.KeyAdapter;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.DirectoryDialog;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.ui.INewWizard;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.actions.WorkspaceModifyOperation;
import org.eclipse.ui.dialogs.ResourceSelectionDialog;
import org.eclipse.ui.dialogs.WizardNewProjectCreationPage;
import org.eclipse.ui.part.FileEditorInput;
import org.eclipse.ui.part.ISetSelectionTarget;

import org.eclipse.emf.codegen.ecore.Generator;
import org.eclipse.emf.codegen.ecore.genmodel.GenModel;
import org.eclipse.emf.codegen.ecore.genmodel.GenModelFactory;
import org.eclipse.emf.codegen.ecore.genmodel.GenPackage;
import org.eclipse.emf.codegen.ecore.genmodel.GenResourceKind;
import org.eclipse.emf.codegen.ecore.genmodel.provider.GenBaseItemProvider;
import org.eclipse.emf.codegen.ecore.genmodel.provider.GenModelEditPlugin;
import org.eclipse.emf.codegen.ecore.genmodel.provider.GenModelItemProviderAdapterFactory;
import org.eclipse.emf.codegen.ecore.genmodel.provider.GenPackageItemProvider;
import org.eclipse.emf.codegen.ecore.java2ecore.JavaEcoreBuilder;
import org.eclipse.emf.codegen.ecore.rose2ecore.RoseUtil;
import org.eclipse.emf.codegen.ecore.rose2ecore.UnitTreeNode;
import org.eclipse.emf.common.notify.Adapter;
import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.common.notify.impl.AdapterFactoryImpl;
import org.eclipse.emf.common.ui.celleditor.ExtendedTableEditor;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.common.util.UniqueEList;
import org.eclipse.emf.ecore.EClassifier;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EcorePackage;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.edit.provider.ItemProvider;
import org.eclipse.emf.edit.ui.provider.AdapterFactoryContentProvider;
import org.eclipse.emf.edit.ui.provider.AdapterFactoryLabelProvider;
import org.eclipse.emf.edit.ui.provider.ExtendedImageRegistry;
import org.osgi.framework.Bundle;


/**
 * Creates a new EMF Project.
 */
public class EMFProjectWizard extends Wizard implements INewWizard
{
  protected static IWorkspaceRoot workspaceRoot = ResourcesPlugin.getWorkspace().getRoot();

  /**
   * The standard project creation page.
   */
  protected WizardNewProjectCreationPage newProjectCreationPage;

  protected static final int JAVA = 0;
  protected static final int XSD = 1;
  protected static final int ECORE = 2;
  protected static final int ROSE = 3;
  protected static final int NOTHING = 4;

  protected int whichModel;

  protected static final int NEW_PROJECT = 0;
  protected static final int NEW_FILE = 1;
  protected static final int RELOAD = 2;

  protected int whichStyle;

  /**
   * The page where the Rose model is specified.
   */
  protected RoseModelPage roseModelPage;

  /**
   * The page where the Rose model is specified.
   */
  protected XMLSchemaModelPage xmlSchemaModelPage;

  /**
   * The page where the Ecore models are specified.
   */
  protected EcoreModelPage ecoreModelPage;

  /**
   * The builder used for Java conversion.
   */
  protected JavaEcoreBuilder javaEcoreBuilder;

  /**
   * The page where the packages are specified.
   */
  protected PackagePage packagePage;

  /**
   * The page were the type of model is chosen.
   */
  protected DetailsPage detailsPage;

  /** 
   * The selection in effect during initialization, which is used for populating the default container.
   */
  protected IStructuredSelection selection;

  /**
   * The workbench in effect during initialization.
   */
  protected IWorkbench workbench;

  /**
   * The GenModel being reloaded.
   */
  protected IFile genModelFile;

  /**
   * The original GenModel being reloaded.
   */
  protected GenModel originalGenModel;

  /**
   * The map from EPackage to extra information.
   */
  protected Map ePackageToInformationMap;

  /**
   * Creates an instance to be used for creating a new project.
   */
  public EMFProjectWizard()
  {
    whichStyle = NEW_PROJECT;
    setWindowTitle(GenModelEditPlugin.INSTANCE.getString("_UI_NewProject_title")); 
  }

  /**
   * Creates an instance to be used for updating and existing GenModel.
   */
  public EMFProjectWizard(IFile genModelFile)
  {
    this.genModelFile = genModelFile;
    whichStyle = RELOAD;
    setWindowTitle(GenModelEditPlugin.INSTANCE.getString("_UI_Reload_title"));
  }

  public IFile getGenModelFile()
  {
    return genModelFile;
  }

  public String getGenModelName()
  {
    switch (whichStyle)
    {
      case NEW_PROJECT:
      {
        switch (whichModel)
        {
          case ROSE:
          {
            return roseModelPage.getGenModelName();
          }
          case XSD:
          {
            return xmlSchemaModelPage.getGenModelName();
          }
          case ECORE:
          {
            return ecoreModelPage.getGenModelName();
          }
        }
      }
      case NEW_FILE:
      {
        return getGenModelFile().getName();
      }
      case RELOAD:
      {
        return 
          new Path(originalGenModel.eResource().getURI().path()).lastSegment();
      }
      default:
      {
        return null;
      }
    }
  }

  public String getGenModelFileName()
  {
    switch (whichStyle)
    {
      case NEW_PROJECT:
      {
        return
          getProjectName() + "/src/model/" + getGenModelName();
      }
      case NEW_FILE:
      {
        return getGenModelFile().getFullPath().makeRelative().toString();
      }
      case RELOAD:
      {
        return 
          new Path(originalGenModel.eResource().getURI().path()).removeFirstSegments(1).toString();
      }
      default:
      {
        return null;
      }
    }
  }

  public String getProjectName()
  {
    switch (whichStyle)
    { 
      case NEW_PROJECT:
      {
        return newProjectCreationPage.getProjectName();
      }
      case NEW_FILE:
      {
        return getGenModelFile().getProject().getName();
      }
      case RELOAD:
      {
        return new Path(originalGenModel.eResource().getURI().path()).segment(1);
      }
      default:
      {
        return null;
      }
    }
  }

  /**
   * Initializes by recording the arguments.
   */
  public void init(IWorkbench workbench, IStructuredSelection selection) 
  {
    this.workbench = workbench;
    this.selection = selection;
    setNeedsProgressMonitor(true);
    setDefaultPageImageDescriptor
      (ExtendedImageRegistry.INSTANCE.getImageDescriptor(GenModelEditPlugin.INSTANCE.getImage("full/wizban/NewEMFProject")));
  }

  /**
   * Called by the framework to create the contents of the wizard.
   */
  public void addPages()
  {
    if (whichStyle == NEW_PROJECT)
    {
      newProjectCreationPage = new WizardNewProjectCreationPage("Whatever1");
      newProjectCreationPage.setTitle(GenModelEditPlugin.INSTANCE.getString("_UI_EMFProject_title"));
      newProjectCreationPage.setDescription(GenModelEditPlugin.INSTANCE.getString("_UI_EMFProject_description"));
      addPage(newProjectCreationPage);
    }

    detailsPage = new DetailsPage("Whatever2");
    detailsPage.setTitle(GenModelEditPlugin.INSTANCE.getString("_UI_InitialContents_title"));
    detailsPage.setDescription(GenModelEditPlugin.INSTANCE.getString("_UI_InitialContents_description"));
    addPage(detailsPage);

    roseModelPage = new RoseModelPage("Whatever3");
    roseModelPage.setTitle(GenModelEditPlugin.INSTANCE.getString("_UI_RoseModelImport_title"));
    roseModelPage.setDescription
      (GenModelEditPlugin.INSTANCE.getString
         (whichStyle != NEW_PROJECT ? 
           "_UI_RoseModelImportFile_description" : "_UI_RoseModelImportNewProject_description" ));
    addPage(roseModelPage);

    xmlSchemaModelPage = new XMLSchemaModelPage("Whatever4");
    xmlSchemaModelPage.setTitle(GenModelEditPlugin.INSTANCE.getString("_UI_XMLSchemaImport_title"));
    xmlSchemaModelPage.setDescription
      (GenModelEditPlugin.INSTANCE.getString
         (whichStyle != NEW_PROJECT ?  "_UI_XMLSchemaImportFile_description" : "_UI_XMLSchemaImportNewProject_description"));
    addPage(xmlSchemaModelPage);

    ecoreModelPage = new EcoreModelPage("Whatever5");
    ecoreModelPage.setTitle(GenModelEditPlugin.INSTANCE.getString("_UI_EcoreImport_title"));
    ecoreModelPage.setDescription
      (GenModelEditPlugin.INSTANCE.getString
         (whichStyle != NEW_PROJECT ?  "_UI_EcoreImportFile_description" : "_UI_EcoreImportNewProject_description"));
    addPage(ecoreModelPage);

    packagePage = new PackagePage("Whatever6");
    packagePage.setTitle(GenModelEditPlugin.INSTANCE.getString("_UI_PackageSelection_title"));
    packagePage.setDescription(GenModelEditPlugin.INSTANCE.getString("_UI_PackageSelection_description"));
    addPage(packagePage);
  }

  /** 
   * The GenModel file to be opened in an editor.
   */
  protected IFile modelFile = null;

  /**
   * Do the work after everything is specified.
   */
  public boolean performFinish()
  {
    try
    {
      // Do the work within an operation.
      //
      WorkspaceModifyOperation operation = 
        new WorkspaceModifyOperation()
        {
          protected void execute(IProgressMonitor progressMonitor) 
          {
            try
            {
              if (whichModel == NOTHING)
              {
                // Make sure the location path isn't just the workspace.
                //
                IPath projectLocationPath = newProjectCreationPage.getLocationPath();
                if (workspaceRoot.getLocation().equals(projectLocationPath))
                {
                  projectLocationPath = null;
                }

                // Create the model project.
                //
                IProject emfProject = 
                  Generator.createEMFProject
                    (new Path("/" + getProjectName() + "/src"), 
                     projectLocationPath,
                     Collections.EMPTY_LIST, 
                     progressMonitor, 
                     Generator.EMF_MODEL_PROJECT_STYLE);
              }
              else if (whichModel == JAVA)
              {
                modelFile = getGenModelFile();
                for (Iterator entries = packagePage.getEPackageToEcoreFileInformationMap().entrySet().iterator(); entries.hasNext(); )
                {
                  Map.Entry entry = (Map.Entry)entries.next();
                  EPackage ePackage = (EPackage)entry.getKey();
                  List value = (List)entry.getValue();
                  String ecoreFileName = value.get(PackagePage.ECORE_FILE_COLUMN).toString();
                  ePackage.eResource().setURI(ePackage.eResource().getURI().trimSegments(1).appendSegment(ecoreFileName));
                }

                for (Iterator i = packagePage.getEPackageToEcoreFileInformationMap().keySet().iterator(); i.hasNext(); )
                {
                  EPackage ePackage = (EPackage)i.next();
                  ePackage.eResource().save(Collections.EMPTY_MAP);
                }

                javaEcoreBuilder.getGenModel().eResource().save(Collections.EMPTY_MAP);
              }
              else
              {
                // The name of the model project we will create.
                //
                String projectName = getProjectName();

                // The name of the GenModel we will create.
                //
                String genModelFileName = getGenModelFileName();
                IPath genModelFilePath = new Path(genModelFileName);

                String baseLocation = genModelFilePath.removeLastSegments(1).toString() + "/";

                // Create resources for all the root EPackages.
                //
                ResourceSet resourceSet = new ResourceSetImpl();
                for (Iterator entries = packagePage.getEPackageToEcoreFileInformationMap().entrySet().iterator(); entries.hasNext(); )
                {
                  Map.Entry entry = (Map.Entry)entries.next();
                  EPackage ePackage = (EPackage)entry.getKey();
                  List value = (List)entry.getValue();
                  String ecoreFileName = baseLocation + value.get(PackagePage.ECORE_FILE_COLUMN).toString();
                  URI ecoreURI = URI.createPlatformResourceURI(ecoreFileName);
                  Resource resource = resourceSet.getResource(ecoreURI, false);
                  if (resource == null)
                  {
                    resource = resourceSet.createResource(ecoreURI);
                  }
                  resource.getContents().add(ePackage);
                }

                // Create resources for all the referenced EPackages,
                // while keeping track of all the referenced GenModels.
                //
                List referencedGenPackages = new ArrayList();
                Collection referencedGenModels = new HashSet();
                for (Iterator entries = packagePage.getEPackageToGenPackageMap().entrySet().iterator(); entries.hasNext(); )
                {
                  Map.Entry entry = (Map.Entry)entries.next();
                  EPackage ePackage = (EPackage)entry.getKey();
                  GenPackage referencedGenPackage = (GenPackage)entry.getValue();

                  EPackage realEPackage = referencedGenPackage.getEcorePackage();
                  GenModel referencedGenModel = referencedGenPackage.getGenModel();
                  referencedGenModels.add(referencedGenPackage.getGenModel());

                  // Use the resource name of the existing EPackage.
                  //
                  URI ecoreURI = realEPackage.eResource().getURI();
                  Resource resource = resourceSet.createResource(ecoreURI);
                  resource.getContents().add(ePackage);
                  referencedGenPackages.add(referencedGenPackage);
                }

                // Determine which projects will need to be referenced.
                //
                List referencedModelProjects = new ArrayList();
                List referencedEditProjects = new ArrayList();
                for (Iterator i = referencedGenModels.iterator(); i.hasNext(); )
                {
                  GenModel referencedGenModel = (GenModel)i.next();
                  referencedModelProjects.add(workspaceRoot.getProject(new Path(referencedGenModel.getModelDirectory()).segment(0)));
                  if (!referencedGenModel.getModelDirectory().equals(referencedGenModel.getEditDirectory()))
                  {
                    referencedEditProjects.add(workspaceRoot.getProject(new Path(referencedGenModel.getEditDirectory()).segment(0)));
                  }
                }

                if (whichStyle == NEW_PROJECT)
                {
                  // Make sure the location path isn't just the workspace.
                  //
                  IPath projectLocationPath = newProjectCreationPage.getLocationPath();
                  if (workspaceRoot.getLocation().equals(projectLocationPath))
                  {
                    projectLocationPath = null;
                  }

                  // Create the model project.
                  //
                  List referencedProjects = new ArrayList(referencedModelProjects);
                  IProject emfProject = 
                    Generator.createEMFProject
                      (new Path("/" + projectName + "/src"), 
                       projectLocationPath,
                       referencedProjects, 
                       progressMonitor, 
                       Generator.EMF_MODEL_PROJECT_STYLE | Generator.EMF_EMPTY_PROJECT_STYLE);
                }

                // Determine the packages that have been generated and save them.
                //
                List ePackages = new ArrayList(resourceSet.getResources().size());
                for (Iterator resources = resourceSet.getResources().iterator(); resources.hasNext(); )
                {
                  Resource resource = (Resource)resources.next();
                  if (!resource.getContents().isEmpty() && resource.getContents().get(0) instanceof EPackage)
                  {
                    EPackage ePackage = (EPackage)resource.getContents().get(0);
                    if (packagePage.getCheckedEPackageList().contains(ePackage))
                    {
                      resource.save(null);
                      ePackages.add(resource.getContents().get(0));
                    }
                  }
                }

                // Create a GenModel.
                //
                URI genModelURI = URI.createPlatformResourceURI(genModelFileName);
                Resource genModelResource = resourceSet.createResource(genModelURI);
                GenModelFactory genModelFactory = GenModelFactory.eINSTANCE;
                GenModel genModel = genModelFactory.createGenModel();
                genModelResource.getContents().add(genModel);

                // Initialize the GenModel with all the computed data.
                //
                genModel.initialize(ePackages);
                genModel.getUsedGenPackages().addAll(referencedGenPackages);

                String qualifier = projectName;
                if (!genModel.getGenPackages().isEmpty())
                {
                  traverseGenPackages(genModel.getGenPackages());
                  GenPackage genPackage = (GenPackage)genModel.getGenPackages().get(0);
                  while (genPackage.getGenClassifiers().isEmpty() && !genPackage.getNestedGenPackages().isEmpty())
                  {
                    genPackage = (GenPackage)genPackage.getNestedGenPackages().get(0);
                  }
                  qualifier = genPackage.getInterfacePackageName();
                }

                String modelName = getGenModelName();
                int index = modelName.lastIndexOf('.');
                if (index != -1) modelName = modelName.substring(0, index);
                modelName = Character.toUpperCase(modelName.charAt(0)) + modelName.substring(1);

                genModel.setModelName(modelName);
                genModel.setModelPluginID(projectName);
                genModel.setEditPluginClass(qualifier + ".provider." + Generator.validName(genModel.getModelName()) + "EditPlugin");
                genModel.setEditorPluginClass(qualifier + ".presentation." + Generator.validName(genModel.getModelName()) + "EditorPlugin");
                genModel.setModelDirectory("/" + projectName + "/src");
                genModel.setEditDirectory("/" + projectName + ".edit/src");
                genModel.setEditorDirectory("/" + projectName + ".editor/src");

                // IWorkspaceRoot workspaceRoot = ResourcesPlugin.getWorkspace().getRoot();

                switch (whichModel)
                {
                  case ROSE:
                  {
                    genModel.getForeignModel().add
                      (makeRelative
                         (URI.createFileURI(roseModelPage.getRoseModelLocationPath().toOSString()),
                          genModelURI).toFileString());
                    IPath projectLocation = 
                      workspaceRoot.getProject(projectName).getLocation().removeLastSegments(1);
                    for (Iterator i = roseModelPage.getPathMap().entrySet().iterator(); i.hasNext(); )
                    {
                      Map.Entry entry = (Map.Entry)i.next();
                      genModel.getForeignModel().add(entry.getKey());
                      String value = (String)entry.getValue();
                      if (new Path(value).equals(projectLocation))
                      {
                        value = "..";
                        for (int depth = genModelFilePath.segmentCount(); depth > 2; --depth)
                        {
                          value += "/..";
                        }
                        genModel.getForeignModel().add(value);
                      }
                      else
                      {
                        genModel.getForeignModel().add(value);
                      }
                    }
                    break;
                  }
                  case JAVA:
                  {
                    genModel.getForeignModel().add("@model");
                    break;
                  }
                  case XSD:
                  {
                    for (Iterator i = xmlSchemaModelPage.getXMLSchemaModelLocations().iterator(); i.hasNext(); )
                    {
                      genModel.getForeignModel().add(makeRelative((URI)i.next(), genModelURI).toString());
                    }

                    if (xmlSchemaModelPage.getMappingRoot() != null)
                    {
                      URI mappingModelURI = 
                        URI.createPlatformResourceURI(genModelFileName).trimFileExtension().appendFileExtension("xsd2ecore");
                      Resource mappingModelResource = resourceSet.createResource(mappingModelURI);
                      mappingModelResource.getContents().add(xmlSchemaModelPage.getMappingRoot());
                      mappingModelResource.save(Collections.EMPTY_MAP);
                    }

                    break;
                  }
                  case ECORE:
                  {
                    for (Iterator i = ecoreModelPage.getEcoreModelLocations().iterator(); i.hasNext(); )
                    {
                      genModel.getForeignModel().add(makeRelative((URI)i.next(), genModelURI).toString());
                    }
                    break;
                  }
                }

                // Restore all configured settings from the original.
                //
                genModel.reconcile(originalGenModel);

                // All done; save the file.
                //
                genModelResource.save(Collections.EMPTY_MAP);

                // Remember the model so that it can be opened.
                //
                modelFile = ResourcesPlugin.getWorkspace().getRoot().getFile(new Path(genModelFileName));
              }
            }
            catch (Exception exception)
            {
              GenModelEditPlugin.INSTANCE.log(exception);
            }
            finally
            {
              progressMonitor.done();
            }
          }
        };

      getContainer().run(false, false, operation);

      if (whichStyle != RELOAD && whichModel != NOTHING)
      {
        // Select the new file resource in the current view.
        //
        IWorkbenchWindow workbenchWindow = workbench.getActiveWorkbenchWindow();
        IWorkbenchPage page = workbenchWindow.getActivePage();
        final IWorkbenchPart activePart = page.getActivePart();
        if (activePart instanceof ISetSelectionTarget) 
        {
          final ISelection targetSelection = new StructuredSelection(modelFile);
          getShell().getDisplay().asyncExec
            (new Runnable() 
             {
               public void run() 
               {
                 ((ISetSelectionTarget)activePart).selectReveal(targetSelection);
               }
             });
        }
  
        // Open an editor on the new file.
        //
        try 
        {
          page.openEditor
            (new FileEditorInput(modelFile), 
             workbench.getEditorRegistry().getDefaultEditor(modelFile.getFullPath().toString()).getId());
        } 
        catch (PartInitException exception) 
        {
          MessageDialog.openError
            (workbenchWindow.getShell(), GenModelEditPlugin.INSTANCE.getString("_UI_OpenEditor_title"), exception.getMessage());
          return false;
        }
      }

      return true;
    }
    catch (Exception exception)
    {
      GenModelEditPlugin.INSTANCE.log(exception);
      return false;
    }
  }

  protected URI makeRelative(URI uri, URI relativeTo)
  {
    if ("file".equals(uri.scheme()))
    {
      IFile file = workspaceRoot.getFileForLocation(new Path(uri.toFileString()));
      if (file != null)
      {
        URI platformURI = URI.createPlatformResourceURI(file.getFullPath().toString());
        URI result = platformURI.deresolve(relativeTo, false, true, false);
        if (result.isRelative())
        {
          return result;
        }
      }
    }

    URI result = uri.deresolve(relativeTo, true, true, false);
    if (result.isRelative())
    {
      return result;
    }

    return uri;
  }

  protected URI makeAbsolute(URI uri, URI relativeTo)
  {
    if (uri.isRelative())
    {
      return uri.resolve(relativeTo);
    }
    return uri;
  }

  /**
   * A page that allows a Rose model to be imported.
   */
  public class RoseModelPage extends WizardPage
  {
    protected Text genModelNameText;
    protected Text roseModelLocationText;
    protected Map pathMap = new HashMap();
    protected TableViewer pathMapTableViewer;
    protected boolean isCellEditing;
    protected boolean isDirty = true;
    protected RoseUtil roseUtil;
    protected UnitTreeNode unitTree;

    public RoseModelPage(String pageId)
    {
      super(pageId);
    }

    public boolean isPageComplete()
    {
      return 
        whichModel != ROSE ||
          !isCellEditing && unitTree != null && getErrorMessage() == null;
    }

    public void setVisible(boolean visible)
    {
      super.setVisible(visible);
      if (visible && isDirty && whichStyle == RELOAD && genModelFile != null)
      {
        isDirty = false;
        getControl().getDisplay().asyncExec
          (new Runnable()
           {
             public void run()
             {
               try
               {
                 Iterator values = originalGenModel.getForeignModel().iterator();
                 if (values.hasNext())
                 {
                   String firstValue = (String)values.next();
                   if (firstValue.endsWith(".mdl"))
                   {
                     URI genModelFileURI = URI.createFileURI(getGenModelFile().getLocation().toOSString());
                     firstValue = makeAbsolute(URI.createFileURI(firstValue), genModelFileURI).toFileString();
                     roseModelLocationText.setText(firstValue);
                     while (values.hasNext())
                     {
                       String variable = (String)values.next();
                       String value = (String)values.next();
                       value = makeAbsolute(URI.createFileURI(value), genModelFileURI).toFileString();
                       pathMap.put(variable, value);
                     }

                     refreshPathMapTable();
                   }
                 }
               }
               catch (Exception exception)
               {
                 GenModelEditPlugin.INSTANCE.log(exception);
               }
             }
           });
      }
    }

    public void createControl(Composite parent)
    {
      Composite composite = new Composite(parent, SWT.NONE);
      {
        GridLayout layout = new GridLayout();
        layout.numColumns = 2;
        layout.verticalSpacing = 12;
        composite.setLayout(layout);

        GridData data = new GridData();
        data.verticalAlignment = GridData.FILL;
        data.grabExcessVerticalSpace = true;
        data.horizontalAlignment = GridData.FILL;
        composite.setLayoutData(data);
      }

      Label roseModelLocationLabel = new Label(composite, SWT.LEFT);
      {
        roseModelLocationLabel.setText(GenModelEditPlugin.INSTANCE.getString("_UI_RoseModelLocation_label"));

        GridData data = new GridData();
        data.horizontalAlignment = GridData.FILL;
        roseModelLocationLabel.setLayoutData(data);
      }

      Button roseModelLocationBrowseButton = new Button(composite, SWT.PUSH);
      roseModelLocationBrowseButton.setText(GenModelEditPlugin.INSTANCE.getString("_UI_Browse_label"));
      {
        GridData data = new GridData();
        data.horizontalAlignment = GridData.END;
        roseModelLocationBrowseButton.setLayoutData(data);
      }

      roseModelLocationText = new Text(composite, SWT.SINGLE | SWT.BORDER);
      {
        GridData data = new GridData();
        data.horizontalAlignment = GridData.FILL;
        data.grabExcessHorizontalSpace = true;
        data.horizontalSpan = 2;
        roseModelLocationText.setLayoutData(data);
      }

      roseModelLocationText.addModifyListener
        (new ModifyListener()
         {
           public void modifyText(ModifyEvent event)
           {
             unitTree = null;
             setPageComplete(false);
           }
         });

      roseModelLocationBrowseButton.addSelectionListener
        (new SelectionAdapter()
         {
           public void widgetSelected(SelectionEvent event)
           {
             FileDialog fileDialog = new FileDialog(RoseModelPage.this.getShell());
             fileDialog.setFilterExtensions(new String [] { "*.mdl" });
             fileDialog.open();
             if (fileDialog.getFileName() != null && fileDialog.getFileName().length() > 0)
             {
               String filePath = fileDialog.getFilterPath() + File.separator + fileDialog.getFileName();
               roseModelLocationText.setText(filePath);
               refreshPathMapTable();
             }
           }
         });

      Label pathMapLabel = new Label(composite, SWT.LEFT);
      {
        pathMapLabel.setText(GenModelEditPlugin.INSTANCE.getString("_UI_PathMap_label"));

        GridData data = new GridData();
        data.horizontalAlignment = GridData.FILL;
        pathMapLabel.setLayoutData(data);
      }

      Composite buttonComposite = new Composite(composite, SWT.NONE);
      {
        GridData data = new GridData();
        data.horizontalAlignment = GridData.END;
        buttonComposite.setLayoutData(data);

        RowLayout layout = new RowLayout();
        layout.justify = true;
        layout.pack = true;
        layout.spacing = 15;
        buttonComposite.setLayout(layout);
      }

      Button pathMapTableLoadButton = new Button(buttonComposite, SWT.PUSH);
      pathMapTableLoadButton.setText(GenModelEditPlugin.INSTANCE.getString("_UI_Load_label"));

      pathMapTableLoadButton.addSelectionListener
        (new SelectionAdapter()
         {
           public void widgetSelected(SelectionEvent event)
           {
             refreshPathMapTable();
           }
         });

      final Button pathMapTableBrowseButton = new Button(buttonComposite, SWT.PUSH);
      pathMapTableBrowseButton.setEnabled(false);
      pathMapTableBrowseButton.setText(GenModelEditPlugin.INSTANCE.getString("_UI_Browse_label"));

      final Table pathMapTable = new Table(composite, SWT.BORDER);

      pathMapTable.addSelectionListener
        (new SelectionAdapter()
         {
           public void widgetSelected(SelectionEvent event)
           {
             pathMapTableBrowseButton.setEnabled(pathMapTable.getSelectionIndex() != -1);
           }
         });

      pathMapTableBrowseButton.addSelectionListener
        (new SelectionAdapter()
         {
           public void widgetSelected(SelectionEvent event)
           {
             DirectoryDialog directoryDialog = new DirectoryDialog(RoseModelPage.this.getShell());
             String path = directoryDialog.open();
             if (path != null && path.length() > 0)
             {
               int index = pathMapTable.getSelectionIndex();
               pathMap.put(pathMapTable.getItem(index).getText(), path);
               unitTree = null;
               setPageComplete(false);
               pathMapTableViewer.refresh();
               if (++index < pathMapTable.getItemCount())
               {
                 pathMapTable.select(index);
               }
             }
           }
         });

      pathMapTableViewer = new TableViewer(pathMapTable);
      {
        GridData data = new GridData();
        data.verticalAlignment = GridData.FILL;
        data.grabExcessVerticalSpace = true;
        data.horizontalAlignment = GridData.FILL;
        data.horizontalSpan = 2;
        pathMapTable.setLayoutData(data);
      }
      pathMapTable.setHeaderVisible(true);
      pathMapTable.setLinesVisible(true);
      {
        TableLayout layout = new TableLayout();

        TableColumn variableNameColumn = new TableColumn(pathMapTable, SWT.NONE);
        layout.addColumnData(new ColumnWeightData(2, true));
        variableNameColumn.setText(GenModelEditPlugin.INSTANCE.getString("_UI_SymbolName_label"));
        variableNameColumn.setResizable(true);

        TableColumn locationColumn = new TableColumn(pathMapTable, SWT.NONE);
        layout.addColumnData(new ColumnWeightData(3, true));
        locationColumn.setText(GenModelEditPlugin.INSTANCE.getString("_UI_ActualLocation_label"));
        locationColumn.setResizable(true);

        pathMapTable.setLayout(layout);
      }

      ExtendedTableEditor extendedTableEditor =
        new ExtendedTableEditor(pathMapTable)
        {
          protected void editItem(final TableItem tableItem, final int column)
          {
            switch (column)
            {
              case 1:
              {
                final String string = tableItem.getText(column);
                horizontalAlignment = SWT.LEFT;
                minimumWidth = Math.max(50, tableItem.getBounds(column).width);

                final Text text = new Text(table, SWT.NONE);
                setEditor(text, tableItem, column);
                text.setFocus();
                text.setText(string);
                text.setSelection(0, string.length());

                text.addFocusListener
                 (new FocusAdapter()
                  {
                    public void focusLost(FocusEvent event)
                    {
                      modify(tableItem, column, text);
                    }
                  });

                text.addKeyListener
                 (new KeyAdapter()
                  {
                    public void keyPressed(KeyEvent event)
                    {
                      if (event.character == '\r' || event.character == '\n')
                      {
                        modify(tableItem, column, text);
                        setEditor(null);
                        text.dispose();
                      }
                      else if (event.character == '\033')
                      {
                        setEditor(null);
                        text.dispose();
                      }
                    }
                  });

                isCellEditing = true;
                setPageComplete(false);
                break;
              }
            }
          }

          protected void modify(TableItem tableItem, int column, Text text)
          {
            tableItem.setText(column, text.getText());
            String key = tableItem.getText();
            String value = tableItem.getText(column);
            text.setVisible(false);
            if ("".equals(value))
            {
              value = null;
            }
            pathMap.put(key, value);

            isCellEditing = false;
            setPageComplete(isPageComplete());
          }
        };

      AdapterFactory adapterFactory = new AdapterFactoryImpl();
      pathMapTableViewer.setColumnProperties(new String [] {"a","b"});
      pathMapTableViewer.setContentProvider(new AdapterFactoryContentProvider(adapterFactory));
      pathMapTableViewer.setLabelProvider
        (new AdapterFactoryLabelProvider(adapterFactory)
         {
           public Image getColumnImage(Object o, int columnIndex)
           {
             switch (columnIndex)
             {
               default:
               {
                 return null;
               }
             }
           }

           public String getColumnText(Object o, int columnIndex)
           {
             switch (columnIndex)
             {
               case 0:
               {
                 return (String)o;
               }
               default:
               case 1:
               {
                 String result = (String)pathMap.get(o);
                 return result == null ? "" : result;
               }
             }
           }
         });

      if (whichStyle == NEW_PROJECT)
      {
        Label genModelNameLabel = new Label(composite, SWT.LEFT);
        {
          genModelNameLabel.setText(GenModelEditPlugin.INSTANCE.getString("_UI_GeneratorModelName_label"));
          GridData data = new GridData();
          data.horizontalAlignment = GridData.FILL;
          data.horizontalSpan = 2;
          genModelNameLabel.setLayoutData(data);
        }
  
        genModelNameText = new Text(composite, SWT.SINGLE | SWT.BORDER);
        {
          GridData data = new GridData();
          data.horizontalAlignment = GridData.FILL;
          data.grabExcessHorizontalSpace = true;
          data.horizontalSpan = 2;
          genModelNameText.setLayoutData(data);
        }

        genModelNameText.addModifyListener(new ModifyListener()
        {
          public void modifyText(ModifyEvent event) 
          {
            String name = ((Text)event.getSource()).getText();
            String msg = null;
            if (name == null || name.equals("")) 
            {
              msg = GenModelEditPlugin.INSTANCE.getString("_UI_GeneratorModelFileNameCannotBeEmpty_message");
            }
            else if (!name.endsWith(".genmodel"))
            {
              msg = GenModelEditPlugin.INSTANCE.getString("_UI_GeneratorModelFileNameMustEndWithGenModel_message");
            }
            setErrorMessage(msg);
            setPageComplete(isPageComplete());
          }
        });
      }

      setControl(composite);
    }

    public void refreshPathMapTable()
    {
      WorkspaceModifyOperation initializeOperation =
        new WorkspaceModifyOperation()
        {
          protected void execute(IProgressMonitor progressMonitor) throws CoreException
          {
            try
            {
              IPath roseModelLocationPath = getRoseModelLocationPath();
              String roseModelLocationString = roseModelLocationPath.toOSString();

              progressMonitor.beginTask("", 2);
              progressMonitor.subTask
                (GenModelEditPlugin.INSTANCE.getString("_UI_Loading_message", new Object [] { roseModelLocationString }));

              for (;;)
              {
                unitTree = null;
                roseUtil = new RoseUtil();
                roseUtil.getVariableToDirectoryMap().putAll(pathMap);
      
                String message = null;
                String errorMessage = null;

                unitTree = roseUtil.createRoseUnitTreeAndTable(roseModelLocationString, null);
                if (unitTree == null)
                {
                  errorMessage = GenModelEditPlugin.INSTANCE.getString("_UI_SpecifyAValidRoseModel_message");
                }
                else
                {
                  pathMap.putAll(roseUtil.getVariableToDirectoryMap());
                  pathMap.remove(null);

                  if (pathMap.containsKey("VABASE_PLUGINS_PATH") && pathMap.get("VABASE_PLUGINS_PATH") == null)
                  {
                    int index = roseModelLocationString.indexOf(File.separator + "plugins" + File.separator);
                    if (index != -1)
                    {
                      pathMap.put("VABASE_PLUGINS_PATH", roseModelLocationString.substring(0, index + 8));
                      continue;
                    }
                  }

                  IPath genModelBasisPath =
                    unitTree.getNodes().size() != 1 ?
                      roseModelLocationPath :
                      new Path(((UnitTreeNode)unitTree.getNodes().get(0)).getRoseFileName());

                  if (whichStyle == NEW_PROJECT)
                  {
                    String genModelName = genModelBasisPath.removeFileExtension().lastSegment() + ".genmodel";
                    genModelNameText.setText(genModelName);
                  }

                  for (Iterator entries = roseUtil.getVariableToDirectoryMap().entrySet().iterator(); entries.hasNext(); )
                  {
                    Map.Entry entry = (Map.Entry)entries.next();
                    if (entry.getValue() == null)
                    {
                      errorMessage = GenModelEditPlugin.INSTANCE.getString("_UI_SpecifyTheSymbolLocations_message");
                      break;
                    }
                  }
                  if (errorMessage == null && !roseUtil.getStatus().isOK())
                  {
                    errorMessage = GenModelEditPlugin.INSTANCE.getString("_UI_SpecifyTheSymbolLocations_message");
                    ErrorDialog.openError
                      (getShell(), 
                       GenModelEditPlugin.INSTANCE.getString("_UI_LoadProblem_title"), 
                       GenModelEditPlugin.INSTANCE.getString("_UI_RoseLoadFailed_message"), 
                       roseUtil.getStatus());
                  }
                }

                setMessage(message);
                setErrorMessage(errorMessage);

                pathMapTableViewer.setInput(new ItemProvider(pathMap.keySet()));
    
                packagePage.setDirty(true);
                break;
              }
            }
            catch (Exception exception)
            {
              GenModelEditPlugin.INSTANCE.log(exception);
            }
            finally
            {
              progressMonitor.done();
            }
          }
        };

      try
      {
        RoseModelPage.this.getContainer().run(false, false, initializeOperation);
      }
      catch (Exception exception)
      {
        GenModelEditPlugin.INSTANCE.log(exception);
      }

      setPageComplete(isPageComplete());
    }

    public IPath getRoseModelLocationPath()
    {
      return roseModelLocationText == null ? null : new Path(roseModelLocationText.getText());
    }

    public String getGenModelName()
    {
      return genModelNameText == null ? null : genModelNameText.getText();
    }

    public Map getPathMap()
    {
      return pathMap;
    }

    public RoseUtil getRoseUtil()
    {
      return roseUtil;
    }

    public UnitTreeNode getUnitTree()
    {
      return unitTree;
    }
  }

  /**
   * A page that allows a XMLSchema model to be imported.
   */
  public class XMLSchemaModelPage extends WizardPage
  {
    protected Text genModelNameText;
    protected Text xmlSchemaModelLocationText;
    protected Button createMapButton;
    protected List ecoreModels = new ArrayList();
    protected EObject mappingRoot;
    protected boolean isDirty = true;

    public XMLSchemaModelPage(String pageId)
    {
      super(pageId);
    }

    public boolean isPageComplete()
    {
      return 
        whichModel != XSD ||
          !ecoreModels.isEmpty() && getErrorMessage() == null;
    }

    Collection getEPackages()
    {
      return ecoreModels;
    }

    EObject getMappingRoot()
    {
      return mappingRoot;
    }

    public void setVisible(boolean visible)
    {
      super.setVisible(visible);
      if (visible && isDirty && whichStyle == RELOAD && genModelFile != null)
      {
        isDirty = false;
        getControl().getDisplay().asyncExec
          (new Runnable()
           {
             public void run()
             {
               try
               {
                 URI genModelURI = URI.createPlatformResourceURI(getGenModelFile().getFullPath().toString());
                 StringBuffer text = new StringBuffer();
                 for (Iterator i = originalGenModel.getForeignModel().iterator(); i.hasNext(); )
                 {
                   String value = (String)i.next();
                   if (value.endsWith(".xsd") || value.endsWith(".wsdl"))
                   {
                     text.append(makeAbsolute(URI.createURI(value), genModelURI).toString());
                     text.append("  ");
                   }
                 }
                 xmlSchemaModelLocationText.setText(text.toString().trim());
                 refreshXMLSchemaModel();
               }
               catch (Exception exception)
               {
                 GenModelEditPlugin.INSTANCE.log(exception);
               }
             }
           });
      }
    }

    public void createControl(Composite parent)
    {
      Composite composite = new Composite(parent, SWT.NONE);
      {
        GridLayout layout = new GridLayout();
        layout.numColumns = 2;
        layout.verticalSpacing = 12;
        composite.setLayout(layout);

        GridData data = new GridData();
        data.verticalAlignment = GridData.FILL;
        data.grabExcessVerticalSpace = true;
        data.horizontalAlignment = GridData.FILL;
        composite.setLayoutData(data);
      }

      Label xmlSchemaModelLocationLabel = new Label(composite, SWT.LEFT);
      {
        xmlSchemaModelLocationLabel.setText(GenModelEditPlugin.INSTANCE.getString("_UI_XMLSchemaURI_label"));

        GridData data = new GridData();
        data.horizontalAlignment = GridData.FILL;
        xmlSchemaModelLocationLabel.setLayoutData(data);
      }

      Composite buttonComposite = new Composite(composite, SWT.NONE);
      {
        GridData data = new GridData();
        data.horizontalAlignment = GridData.END;
        buttonComposite.setLayoutData(data);

        RowLayout layout = new RowLayout();
        layout.justify = true;
        layout.pack = true;
        layout.spacing = 15;
        buttonComposite.setLayout(layout);
      }
      Button xmlSchemaModelLocationBrowseFileSystemButton = new Button(buttonComposite, SWT.PUSH);
      xmlSchemaModelLocationBrowseFileSystemButton.setText(GenModelEditPlugin.INSTANCE.getString("_UI_BroseFileSystemFile_label"));

      Button xmlSchemaModelLocationBrowseWorkspaceButton = new Button(buttonComposite, SWT.PUSH);
      xmlSchemaModelLocationBrowseWorkspaceButton.setText(GenModelEditPlugin.INSTANCE.getString("_UI_BroseWorkspace_label"));

      xmlSchemaModelLocationText = new Text(composite, SWT.SINGLE | SWT.BORDER);
      {
        GridData data = new GridData();
        data.horizontalAlignment = GridData.FILL;
        data.grabExcessHorizontalSpace = true;
        data.horizontalSpan = 3;
        xmlSchemaModelLocationText.setLayoutData(data);
      }

      xmlSchemaModelLocationText.addModifyListener
        (new ModifyListener()
         {
           public void modifyText(ModifyEvent event)
           {
             ecoreModels.clear();
             setPageComplete(false);
           }
         });

      xmlSchemaModelLocationBrowseFileSystemButton.addSelectionListener
        (new SelectionAdapter()
         {
           public void widgetSelected(SelectionEvent event)
           {
             FileDialog fileDialog = new FileDialog(XMLSchemaModelPage.this.getShell());
             fileDialog.setFilterExtensions(new String [] { "*.xsd;*.wsdl", "*.xsd", "*.wsdl" });
             fileDialog.open();
             if (fileDialog.getFileName() != null && fileDialog.getFileName().length() > 0)
             {
               String filePath = fileDialog.getFilterPath() + File.separator + fileDialog.getFileName();
               xmlSchemaModelLocationText.setText
                 ((xmlSchemaModelLocationText.getText() + "  " + URI.createFileURI(filePath).toString()).trim());
               refreshXMLSchemaModel();
             }
           }
         });

      xmlSchemaModelLocationBrowseWorkspaceButton.addSelectionListener
        (new SelectionAdapter()
         {
           public void widgetSelected(SelectionEvent event)
           {
             Collection xmlSchemaResources = new ArrayList();
             ResourceSelectionDialog resourceSelectionDialog = 
               new ResourceSelectionDialog
                 (XMLSchemaModelPage.this.getShell(), 
                  ResourcesPlugin.getWorkspace().getRoot(),
                  GenModelEditPlugin.INSTANCE.getString("_UI_SelectTheXMLSchema_label"));

             resourceSelectionDialog.open();
             Object [] result = resourceSelectionDialog.getResult();
             if (result != null)
             {
               StringBuffer text = new StringBuffer();
               for (int i = 0; i < result.length; ++i)
               {
                 IResource resource = (IResource)result[i];
                 if (resource.getType() == IResource.FILE && !isInJavaOutput(resource) &&
                       ("xsd".equals(resource.getFullPath().getFileExtension()) || 
                        "wsdl".equals(resource.getFullPath().getFileExtension())))
                 {
                   text.append(URI.createPlatformResourceURI(resource.getFullPath().toString()));
                   text.append("  ");
                 }
               }
               xmlSchemaModelLocationText.setText((xmlSchemaModelLocationText.getText() + "  " + text.toString()).trim());
               refreshXMLSchemaModel();
             }
           }
         });

      Bundle xsd2ecorePlugin = Platform.getBundle("org.eclipse.emf.mapping.xsd2ecore");
      if (xsd2ecorePlugin != null)
      {
        createMapButton = new Button(composite, SWT.CHECK);
        createMapButton.setText(GenModelEditPlugin.INSTANCE.getString("_UI_Create_XML_Schema_to_Ecore_Map"));
        {
          GridData data = new GridData();
          data.horizontalSpan = 1;
          createMapButton.setLayoutData(data);
        }
  
        createMapButton.addSelectionListener
          (new SelectionAdapter()
           {
             public void widgetSelected(SelectionEvent event)
             {
               refreshXMLSchemaModel();
             }
           });
      }

      Button pathMapTableLoadButton = new Button(composite, SWT.PUSH);
      pathMapTableLoadButton.setText(GenModelEditPlugin.INSTANCE.getString("_UI_Load_label"));
      {
        GridData data = new GridData();
        data.horizontalSpan = xsd2ecorePlugin == null ? 2 : 1;
        data.horizontalAlignment = GridData.END;
        pathMapTableLoadButton.setLayoutData(data);
      }

      pathMapTableLoadButton.addSelectionListener
        (new SelectionAdapter()
         {
           public void widgetSelected(SelectionEvent event)
           {
             refreshXMLSchemaModel();
           }
         });

      if (whichStyle == NEW_PROJECT)
      {
        Label genModelNameLabel = new Label(composite, SWT.LEFT);
        {
          genModelNameLabel.setText(GenModelEditPlugin.INSTANCE.getString("_UI_GeneratorModelName_label"));
  
          GridData data = new GridData();
          data.horizontalAlignment = GridData.FILL;
          data.horizontalSpan = 2;
          genModelNameLabel.setLayoutData(data);
        }
  
        genModelNameText = new Text(composite, SWT.SINGLE | SWT.BORDER);
        {
          GridData data = new GridData();
          data.horizontalAlignment = GridData.FILL;
          data.grabExcessHorizontalSpace = true;
          data.horizontalSpan = 2;
          genModelNameText.setLayoutData(data);
        }

        genModelNameText.addModifyListener(new ModifyListener()
        {
          public void modifyText(ModifyEvent event) 
          {
            String name = ((Text)event.getSource()).getText();
            String msg = null;
            if (name == null || name.equals("")) 
            {
              msg = GenModelEditPlugin.INSTANCE.getString("_UI_GeneratorModelFileNameCannotBeEmpty_message");
            }
            else if (!name.endsWith(".genmodel"))
            {
              msg = GenModelEditPlugin.INSTANCE.getString("_UI_GeneratorModelFileNameMustEndWithGenModel_message");
            }
            setErrorMessage(msg);
            setPageComplete(isPageComplete());
          }
        });
      }

      setControl(composite);
    }

    public void refreshXMLSchemaModel()
    {
      WorkspaceModifyOperation initializeOperation =
        new WorkspaceModifyOperation()
        {
          protected void execute(IProgressMonitor progressMonitor) throws CoreException
          {
            try
            {
              List xmlSchemaModelLocations = getXMLSchemaModelLocations();

              progressMonitor.beginTask("", 2);
              progressMonitor.subTask
                (GenModelEditPlugin.INSTANCE.getString("_UI_Loading_message", new Object [] { xmlSchemaModelLocations }));

              Bundle xsdPlugin = Platform.getBundle("org.eclipse.xsd");
              Class theGeneratorClass = xsdPlugin.loadClass("org.eclipse.xsd.ecore.XSDEcoreBuilder");
              Object ecoreGenerator = theGeneratorClass.newInstance();

              // Set the mapper to build an XSD2EcoreMappingRoot, if available.
              //
              Bundle xsd2ecorePlugin = Platform.getBundle("org.eclipse.emf.mapping.xsd2ecore");
              if (xsd2ecorePlugin != null && createMapButton != null && createMapButton.getSelection())
              {
                try
                {
                  Class theMapperInterface =
                    xsdPlugin.loadClass("org.eclipse.xsd.ecore.MapBuilder$Mapper");

                  Class theMapperClass =
                    xsd2ecorePlugin.loadClass("org.eclipse.emf.mapping.xsd2ecore.XSD2EcoreMapper");

                  Object mapper = theMapperClass.newInstance();

                  theGeneratorClass.getMethod
                    ("setMapper",
                     new Class [] { theMapperInterface }).invoke(ecoreGenerator, new Object [] { mapper });
                }
                catch (InstantiationException exception)
                {
                  GenModelEditPlugin.INSTANCE.log(exception);
                }
                catch (NoSuchMethodException  exception)
                {
                  GenModelEditPlugin.INSTANCE.log(exception);
                }
                catch (IllegalAccessException  exception)
                {
                  GenModelEditPlugin.INSTANCE.log(exception);
                }
                catch (InvocationTargetException  exception)
                {
                  GenModelEditPlugin.INSTANCE.log(exception);
                }
                catch (ClassNotFoundException exception)
                {
                  // Ignore this soft dependency when it's not satisfied.
                }
              }

              ecoreModels.clear();
              ecoreModels.addAll
                ((Collection)theGeneratorClass.getDeclaredMethod
                  ("generate", new Class [] { Collection.class }).invoke(ecoreGenerator, new Object [] { xmlSchemaModelLocations }));

              if (!ecoreModels.isEmpty() && ecoreModels.get(ecoreModels.size() - 1) instanceof List)
              {
                List diagnostics = (List)ecoreModels.remove(ecoreModels.size() - 1);
                if (!diagnostics.isEmpty())
                {
                  MultiStatus status =
                    new MultiStatus
                      (GenModelEditPlugin.getPlugin().getBundle().getSymbolicName(),
                       0,
                       GenModelEditPlugin.INSTANCE.getString("_UI_ErrorsWereDetectedXMLSchema_message"),
                       null);
                  for (Iterator i = diagnostics.iterator(); i.hasNext(); )
                  {
                    List information = (List)i.next();
                    status.add
                     (new Status
                       ("error".equals(information.get(0)) ? 
                           IStatus.ERROR : 
                           "warning".equals(information.get(0)) ?
                             IStatus.WARNING :
                             IStatus.INFO,
                        GenModelEditPlugin.getPlugin().getBundle().getSymbolicName(),
                        0,
                        (String)information.get(1),
                        null));
                  }

                  if (!status.isOK())
                  {
                    ErrorDialog.openError
                      (getShell(),
                       GenModelEditPlugin.INSTANCE.getString("_UI_ConversionProblem_title"),
                       GenModelEditPlugin.INSTANCE.getString("_UI_ProblemsEncounteredProcessingXMLSchema_message"),
                       status);
                  }
                }
              }

              if (!ecoreModels.isEmpty() && !(ecoreModels.get(ecoreModels.size() - 1) instanceof EPackage))
              {
                mappingRoot = (EObject)ecoreModels.remove(ecoreModels.size() - 1);
              }

              if (whichStyle == NEW_PROJECT)
              {
                if (!xmlSchemaModelLocations.isEmpty())
                {
                  String genModelName = ((URI)xmlSchemaModelLocations.get(0)).trimFileExtension().lastSegment() + ".genmodel";
                  genModelNameText.setText(genModelName);
                }
              }
      
              packagePage.setDirty(true);
            }
            catch (Exception exception)
            {
              GenModelEditPlugin.INSTANCE.log(exception);
            }
            finally
            {
              setErrorMessage(ecoreModels.isEmpty() ? GenModelEditPlugin.INSTANCE.getString("_UI_SpecifyAValidXMLSchema_message") : null);
              progressMonitor.done();
            }
          }
        };

      try
      {
        XMLSchemaModelPage.this.getContainer().run(false, false, initializeOperation);
      }
      catch (Exception exception)
      {
        GenModelEditPlugin.INSTANCE.log(exception);
      }

      setPageComplete(isPageComplete());
    }

    public List getXMLSchemaModelLocations()
    {
      if (xmlSchemaModelLocationText == null)
      {
        return Collections.EMPTY_LIST;
      }
      else
      {
        List result = new ArrayList();
        for (StringTokenizer stringTokenizer = new StringTokenizer(xmlSchemaModelLocationText.getText()); 
             stringTokenizer.hasMoreTokens(); )
        {
          String uri = stringTokenizer.nextToken();
          result.add(URI.createURI(uri));
        }
        return result;
      }
    }

    public String getGenModelName()
    {
      return genModelNameText == null ? null : genModelNameText.getText();
    }
  }

  public void traverseGenPackages(List genPackages)
  {
    for (Iterator i = genPackages.iterator(); i.hasNext(); )
    {
      GenPackage genPackage = (GenPackage)i.next();
      EPackage ePackage = genPackage.getEcorePackage();
 
      List ePackageInformation = (List)ePackageToInformationMap.get(ePackage);
      if (ePackageInformation != null)
      {
        genPackage.setBasePackage((String)ePackageInformation.get(0));
        genPackage.setPrefix((String)ePackageInformation.get(1));
      }

      if (genPackage.getPrefix() == null || genPackage.getPrefix().length() == 0)
      {
        String name = ePackage.getName();
        genPackage.setPrefix(Character.toUpperCase(name.charAt(0)) + name.substring(1));
      }

      if (whichModel == XSD)
      {
        genPackage.setResource(GenResourceKind.XML_LITERAL);
      }

      traverseGenPackages(genPackage.getNestedGenPackages());
    }
  }

  /**
   * A page that allows a Ecore model to be imported.
   */
  public class EcoreModelPage extends WizardPage
  {
    protected Text genModelNameText;
    protected Text ecoreModelLocationText;
    protected Collection ecoreModels = new ArrayList();
    protected boolean isDirty = true;

    public EcoreModelPage(String pageId)
    {
      super(pageId);
    }

    public boolean isPageComplete()
    {
      return 
        whichModel != ECORE ||
          !ecoreModels.isEmpty() && getErrorMessage() == null;
    }

    Collection getEPackages()
    {
      return ecoreModels;
    }

    public void setVisible(boolean visible)
    {
      super.setVisible(visible);
      if (visible && isDirty && whichStyle == RELOAD && genModelFile != null)
      {
        isDirty = false;
        getControl().getDisplay().asyncExec
          (new Runnable()
           {
             public void run()
             {
               try
               {
                 URI genModelURI = URI.createPlatformResourceURI(getGenModelFile().getFullPath().toString());
                 StringBuffer text = new StringBuffer();
                 for (Iterator i = originalGenModel.getForeignModel().iterator(); i.hasNext(); )
                 {
                   String value = (String)i.next();
                   if (value.endsWith(".ecore") || value.endsWith(".emof"))
                   {
                     text.append(makeAbsolute(URI.createURI(value), genModelURI).toString());
                     text.append("  ");
                   }
                 }
                 ecoreModelLocationText.setText(text.toString().trim());
                 refreshEcoreModel();
               }
               catch (Exception exception)
               {
                 GenModelEditPlugin.INSTANCE.log(exception);
               }
             }
           });
      }
    }

    public void createControl(Composite parent)
    {
      Composite composite = new Composite(parent, SWT.NONE);
      {
        GridLayout layout = new GridLayout();
        layout.numColumns = 2;
        layout.verticalSpacing = 12;
        composite.setLayout(layout);

        GridData data = new GridData();
        data.verticalAlignment = GridData.FILL;
        data.grabExcessVerticalSpace = true;
        data.horizontalAlignment = GridData.FILL;
        composite.setLayoutData(data);
      }

      Label ecoreModelLocationLabel = new Label(composite, SWT.LEFT);
      {
        ecoreModelLocationLabel.setText(GenModelEditPlugin.INSTANCE.getString("_UI_EcoreModelURI_label"));

        GridData data = new GridData();
        data.horizontalAlignment = GridData.FILL;
        ecoreModelLocationLabel.setLayoutData(data);
      }

      Composite buttonComposite = new Composite(composite, SWT.NONE);
      {
        GridData data = new GridData();
        data.horizontalAlignment = GridData.END;
        buttonComposite.setLayoutData(data);

        RowLayout layout = new RowLayout();
        layout.justify = true;
        layout.pack = true;
        layout.spacing = 15;
        buttonComposite.setLayout(layout);
      }
      Button ecoreModelLocationBrowseFileSystemButton = new Button(buttonComposite, SWT.PUSH);
      ecoreModelLocationBrowseFileSystemButton.setText(GenModelEditPlugin.INSTANCE.getString("_UI_BroseFileSystemFile_label"));

      Button ecoreModelLocationBrowseWorkspaceButton = new Button(buttonComposite, SWT.PUSH);
      ecoreModelLocationBrowseWorkspaceButton.setText(GenModelEditPlugin.INSTANCE.getString("_UI_BroseWorkspace_label"));

      ecoreModelLocationText = new Text(composite, SWT.SINGLE | SWT.BORDER);
      {
        GridData data = new GridData();
        data.horizontalAlignment = GridData.FILL;
        data.grabExcessHorizontalSpace = true;
        data.horizontalSpan = 3;
        ecoreModelLocationText.setLayoutData(data);
      }

      ecoreModelLocationText.addModifyListener
        (new ModifyListener()
         {
           public void modifyText(ModifyEvent event)
           {
             ecoreModels.clear();
             setPageComplete(false);
           }
         });

      ecoreModelLocationBrowseFileSystemButton.addSelectionListener
        (new SelectionAdapter()
         {
           public void widgetSelected(SelectionEvent event)
           {
             FileDialog fileDialog = new FileDialog(EcoreModelPage.this.getShell());
             fileDialog.setFilterExtensions(new String [] { "*.ecore;*.emof", "*.ecore", "*.emof" });
             fileDialog.open();
             if (fileDialog.getFileName() != null && fileDialog.getFileName().length() > 0)
             {
               String filePath = fileDialog.getFilterPath() + File.separator + fileDialog.getFileName();
               ecoreModelLocationText.setText((ecoreModelLocationText.getText() + "  " + URI.createFileURI(filePath).toString()).trim());
               refreshEcoreModel();
             }
           }
         });

      ecoreModelLocationBrowseWorkspaceButton.addSelectionListener
        (new SelectionAdapter()
         {
           public void widgetSelected(SelectionEvent event)
           {
             Collection ecoreModelResources = new ArrayList();
             ResourceSelectionDialog resourceSelectionDialog = 
               new ResourceSelectionDialog
                 (EcoreModelPage.this.getShell(), 
                  ResourcesPlugin.getWorkspace().getRoot(),
                  GenModelEditPlugin.INSTANCE.getString("_UI_SelectTheEcoreModel_label"));

             resourceSelectionDialog.open();
             Object [] result = resourceSelectionDialog.getResult();
             if (result != null)
             {
               StringBuffer text = new StringBuffer();
               for (int i = 0; i < result.length; ++i)
               {
                 IResource resource = (IResource)result[i];
                 if (resource.getType() == IResource.FILE && !isInJavaOutput(resource) &&
                       ("ecore".equals(resource.getFullPath().getFileExtension()) ||
                          "emof".equals(resource.getFullPath().getFileExtension())))
                 {
                   text.append(URI.createPlatformResourceURI(resource.getFullPath().toString()));
                   text.append("  ");
                 }
               }
               ecoreModelLocationText.setText((ecoreModelLocationText.getText() + "  " + text.toString()).trim());
               refreshEcoreModel();
             }
           }
         });

      Button pathMapTableLoadButton = new Button(composite, SWT.PUSH);
      pathMapTableLoadButton.setText(GenModelEditPlugin.INSTANCE.getString("_UI_Load_label"));
      {
        GridData data = new GridData();
        data.horizontalSpan = 2;
        data.horizontalAlignment = GridData.END;
        pathMapTableLoadButton.setLayoutData(data);
      }

      pathMapTableLoadButton.addSelectionListener
        (new SelectionAdapter()
         {
           public void widgetSelected(SelectionEvent event)
           {
             refreshEcoreModel();
           }
         });

      if (whichStyle == NEW_PROJECT)
      {
        Label genModelNameLabel = new Label(composite, SWT.LEFT);
        {
          genModelNameLabel.setText(GenModelEditPlugin.INSTANCE.getString("_UI_GeneratorModelName_label"));
  
          GridData data = new GridData();
          data.horizontalAlignment = GridData.FILL;
          data.horizontalSpan = 2;
          genModelNameLabel.setLayoutData(data);
        }
  
        genModelNameText = new Text(composite, SWT.SINGLE | SWT.BORDER);
        {
          GridData data = new GridData();
          data.horizontalAlignment = GridData.FILL;
          data.grabExcessHorizontalSpace = true;
          data.horizontalSpan = 2;
          genModelNameText.setLayoutData(data);
        }

        genModelNameText.addModifyListener(new ModifyListener()
        {
          public void modifyText(ModifyEvent event) 
          {
            String name = ((Text)event.getSource()).getText();
            String msg = null;
            if (name == null || name.equals("")) 
            {
              msg = GenModelEditPlugin.INSTANCE.getString("_UI_GeneratorModelFileNameCannotBeEmpty_message");
            }
            else if (!name.endsWith(".genmodel"))
            {
              msg = GenModelEditPlugin.INSTANCE.getString("_UI_GeneratorModelFileNameMustEndWithGenModel_message");
            }
            setErrorMessage(msg);
            setPageComplete(isPageComplete());
          }
        });
      }

      setControl(composite);
    }

    public void refreshEcoreModel()
    {
      WorkspaceModifyOperation initializeOperation =
        new WorkspaceModifyOperation()
        {
          protected void execute(IProgressMonitor progressMonitor) throws CoreException
          {
            try
            {
              List ecoreModelLocations = getEcoreModelLocations();

              progressMonitor.beginTask("", 2);
              progressMonitor.subTask
                (GenModelEditPlugin.INSTANCE.getString("_UI_Loading_message", new Object [] { ecoreModelLocations }));

              ResourceSet ecoreResourceSet = new ResourceSetImpl();
              for (Iterator i = ecoreModelLocations.iterator(); i.hasNext(); )
              {
                URI ecoreModelLocation = (URI)i.next();
                Resource ecoreResource = ecoreResourceSet.getResource(ecoreModelLocation, true);
                for (Iterator j = ecoreResourceSet.getAllContents(); j.hasNext(); )
                {
                  Object content = j.next();
                  if (content instanceof EObject)
                  {
                    for (Iterator k = ((EObject)content).eCrossReferences().iterator(); k.hasNext(); )
                    {
                      Object referencedObject = k.next();
                    }
                  }
                }
              }

              ecoreModels.clear();
              for (Iterator i = ecoreResourceSet.getResources().iterator(); i.hasNext(); )
              {
                Resource resource = (Resource)i.next();
                ecoreModels.addAll(EcoreUtil.getObjectsByType(resource.getContents(), EcorePackage.eINSTANCE.getEPackage()));
              }

              if (whichStyle == NEW_PROJECT)
              {
                if (!ecoreModelLocations.isEmpty())
                {
                  String genModelName = ((URI)ecoreModelLocations.get(0)).trimFileExtension().lastSegment() + ".genmodel";
                  genModelNameText.setText(genModelName);
                }
              }

              packagePage.setDirty(true);
            }
            catch (Exception exception)
            {
              GenModelEditPlugin.INSTANCE.log(exception);
            }
            finally
            {
              if (ecoreModels.isEmpty())
              {
                setErrorMessage(GenModelEditPlugin.INSTANCE.getString("_UI_SpecifyAValidEcoreModel_message"));
              }
              progressMonitor.done();
            }
          }
        };

      try
      {
        EcoreModelPage.this.getContainer().run(false, false, initializeOperation);
      }
      catch (Exception exception)
      {
        GenModelEditPlugin.INSTANCE.log(exception);
      }

      setPageComplete(isPageComplete());
    }

    public List getEcoreModelLocations()
    {
      if (ecoreModelLocationText == null)
      {
        return Collections.EMPTY_LIST;
      }
      else
      {
        List result = new ArrayList();
        for (StringTokenizer stringTokenizer = new StringTokenizer(ecoreModelLocationText.getText());
             stringTokenizer.hasMoreTokens(); )
        {
          String uri = stringTokenizer.nextToken();
          result.add(URI.createURI(uri));
        }
        return result;
      }
    }

    public String getGenModelName()
    {
      return genModelNameText == null ? null : genModelNameText.getText();
    }
  }

  /**
   * This pages allows a Rose model to be imported.
   */
  public class PackagePage extends WizardPage
  {
    public static final int ECORE_FILE_COLUMN = 0;
    public static final int BASE_COLUMN = 1;

    protected Map ePackageToGenPackageMap = new HashMap();
    protected Map ePackageToEcoreFileInformationMap = new HashMap();
    protected CheckboxTreeViewer referencedGenModelsCheckboxTreeViewer;
    protected CheckboxTableViewer packagesCheckboxTableViewer;
    protected Button referencedGenModelsTreeBrowseButton;
    protected Label referencedGenModelsLabel;
    protected boolean isCellEditing;
    protected RoseUtil roseUtil;
    protected UnitTreeNode unitTree;
    protected List ePackageList = new ArrayList();
    protected List checkedEPackageList = new ArrayList();
    protected List filteredEPackageList = new ArrayList();
    protected List targetPackageNames = new ArrayList();
    protected ResourceSet referencedGenModels;
    protected boolean isDirty = true;

    public PackagePage(String pageId)
    {
      super(pageId);
    }

    public void setDirty(boolean isDirty)
    {
      this.isDirty = isDirty;
    }

    public boolean isPageComplete()
    {
      return 
        whichModel == NOTHING ||
          getErrorMessage()  == null &&
            !isCellEditing && 
            packagesCheckboxTableViewer != null && 
            packagesCheckboxTableViewer.getCheckedElements().length > 0;
    }

    public void setVisible(boolean visible)
    {
      super.setVisible(visible);
      if (visible)
      {
        referencedGenModelsCheckboxTreeViewer.getControl().setVisible(whichModel != JAVA);
        referencedGenModelsTreeBrowseButton.setVisible(whichModel != JAVA);
        referencedGenModelsLabel.setVisible(whichModel != JAVA);
      }

      if (visible && isDirty)
      {
        refreshPackagesTable();
        isDirty = false;
      }
    }

    public void createControl(Composite parent)
    {
      Composite composite = new Composite(parent, SWT.NONE);
      {
        GridLayout layout = new GridLayout();
        layout.numColumns = 2;
        layout.verticalSpacing = 12;
        composite.setLayout(layout);

        GridData data = new GridData();
        data.verticalAlignment = GridData.FILL;
        data.grabExcessVerticalSpace = true;
        data.horizontalAlignment = GridData.FILL;
        composite.setLayoutData(data);
      }

      Label packagesLabel = new Label(composite, SWT.LEFT);
      packagesLabel.setText(GenModelEditPlugin.INSTANCE.getString("_UI_RootPackages_label"));
      {
        GridData data = new GridData();
        data.horizontalAlignment = GridData.FILL;
        packagesLabel.setLayoutData(data);
      }

      Composite selectionComposite = new Composite(composite, SWT.NONE);
      {
        GridData data = new GridData();
        data.horizontalAlignment = GridData.END;
        selectionComposite.setLayoutData(data);

        RowLayout layout = new RowLayout();
        layout.justify = true;
        layout.pack = true;
        layout.spacing = 15;
        selectionComposite.setLayout(layout);
      }

      Button selectAllButton = new Button(selectionComposite, SWT.PUSH);
      selectAllButton.setText(GenModelEditPlugin.INSTANCE.getString("_UI_SelectAll_label"));
      selectAllButton.addSelectionListener
        (new SelectionAdapter()
         {
           public void widgetSelected(SelectionEvent event)
           {
             packagesCheckboxTableViewer.setCheckedElements(ePackageList.toArray());
             filterPackagesTable();
             // validate();
             setPageComplete(isPageComplete());
           }
         });

      Button deselectAllButton = new Button(selectionComposite, SWT.PUSH);
      deselectAllButton.setText(GenModelEditPlugin.INSTANCE.getString("_UI_DeselectAll_label"));
      deselectAllButton.addSelectionListener
        (new SelectionAdapter()
         {
           public void widgetSelected(SelectionEvent event)
           {
             packagesCheckboxTableViewer.setCheckedElements(new Object [0]);
             filterPackagesTable();
             // validate();
             setPageComplete(isPageComplete());
           }
         });

      Table packagesTable = new Table(composite, SWT.CHECK | SWT.BORDER);
      packagesCheckboxTableViewer = new CheckboxTableViewer(packagesTable);
      {
        GridData data = new GridData();
        data.verticalAlignment = GridData.FILL;
        data.grabExcessHorizontalSpace = true;
        data.grabExcessVerticalSpace = true;
        data.horizontalAlignment = GridData.FILL;
        data.horizontalSpan = 2;
        data.heightHint = 90;
        packagesTable.setLayoutData(data);
      }

      packagesTable.setHeaderVisible(true);
      packagesTable.setLinesVisible(true);
      {
        TableLayout layout = new TableLayout();

        TableColumn rosePackageColumn = new TableColumn(packagesTable, SWT.NONE);
        layout.addColumnData(new ColumnWeightData(1, true));
        rosePackageColumn.setText(GenModelEditPlugin.INSTANCE.getString("_UI_Package_label"));
        rosePackageColumn.setResizable(true);

        TableColumn ecoreFileNameColumn = new TableColumn(packagesTable, SWT.NONE);
        layout.addColumnData(new ColumnWeightData(1, true));
        ecoreFileNameColumn.setText(GenModelEditPlugin.INSTANCE.getString("_UI_EcoreModelName_label"));
        ecoreFileNameColumn.setResizable(true);

        packagesTable.setLayout(layout);
      }

      ExtendedTableEditor extendedTableEditor =
        new ExtendedTableEditor(packagesTable)
        {
          protected void editItem(final TableItem tableItem, final int column)
          {
            switch (column)
            {
              case 1:
              case 2:
              {
                final String string = tableItem.getText(column);
                horizontalAlignment = SWT.LEFT;
                minimumWidth = Math.max(50, tableItem.getBounds(column).width);

                final Text text = new Text(table, SWT.NONE);
                setEditor(text, tableItem, column);
                text.setFocus();
                text.setText(string);
                text.setSelection(0, string.length());
                validateName(string, null);

                text.addFocusListener
                 (new FocusAdapter()
                  {
                    public void focusLost(FocusEvent event)
                    {
                      modify(tableItem, column, text);
                    }
                  });

                text.addKeyListener
                 (new KeyAdapter()
                  {
                    public void keyPressed(KeyEvent event)
                    {
                      if (event.character == '\r' || event.character == '\n')
                      {
                        modify(tableItem, column, text);
                        setEditor(null);
                        text.dispose();
                      }
                      else if (event.character == '\033')
                      {
                        setEditor(null);
                        text.dispose();
                      }
                    }
                  });

                text.addModifyListener(new ModifyListener()
                {
                  public void modifyText(ModifyEvent event) 
                  {
                    if (column == 1) validateName(text.getText(), null);
                  }
                });

                isCellEditing = true;
                setPageComplete(false);
                break;
              }
            }
          }

          protected void modify(TableItem tableItem, int column, Text text)
          {
            tableItem.setText(column, text.getText());
            String value = tableItem.getText(column);
            text.setVisible(false);
            List ecoreFileNameInformation = (List)getEPackageToEcoreFileInformationMap().get(tableItem.getData());
            if (ecoreFileNameInformation != null)
            {
              StringBuffer oldValue = (StringBuffer)ecoreFileNameInformation.get(column - 1);
              oldValue.replace(0, oldValue.length(), value);
            }
            isCellEditing = false;
            validate();
            setPageComplete(isPageComplete());
          }
        };

      AdapterFactory adapterFactory = new AdapterFactoryImpl();
      packagesCheckboxTableViewer.setColumnProperties(new String [] {"a","b"});
      packagesCheckboxTableViewer.setContentProvider(new AdapterFactoryContentProvider(adapterFactory));
      packagesCheckboxTableViewer.setLabelProvider
        (new AdapterFactoryLabelProvider(adapterFactory)
         {
           public Image getColumnImage(Object o, int columnIndex)
           {
             switch (columnIndex)
             {
               case 0:
               {
                 return 
                   ExtendedImageRegistry.INSTANCE.getImage
                     (new GenBaseItemProvider.UnderlayedImage(GenModelEditPlugin.INSTANCE.getImage("full/obj16/EPackage")));
               }
               default:
               {
                 return null;
               }
             }
           }

           public String getColumnText(Object o, int columnIndex)
           {
             switch (columnIndex)
             {
               case 0:
               {
                 return ((EPackage)o).getName();
               }
               case 1:
               case 2:
               {
                 return ((List)ePackageToEcoreFileInformationMap.get(o)).get(columnIndex - 1).toString();
               }
               default:
               {
                 return "";
               }
             }
           }
         });

       packagesCheckboxTableViewer.addCheckStateListener
         (new ICheckStateListener()
          {
            public void checkStateChanged(CheckStateChangedEvent event)
            {
              validate();
              setPageComplete(isPageComplete());
              packagesCheckboxTableViewer.refresh();
              targetPackageNames.clear();
              Object [] checkedElements = packagesCheckboxTableViewer.getCheckedElements();
              for (int i = 0; i < checkedElements.length; ++i)
              {
                EPackage ePackage = (EPackage)checkedElements[i];
                targetPackageNames.add(ePackage.getName());
              }
            }
          });

      referencedGenModelsLabel = new Label(composite, SWT.LEFT);
      {
        referencedGenModelsLabel.setText(GenModelEditPlugin.INSTANCE.getString("_UI_ReferencedGeneratorModels_label"));

        GridData data = new GridData();
        data.horizontalAlignment = GridData.FILL;
        referencedGenModelsLabel.setLayoutData(data);
      }

      referencedGenModelsTreeBrowseButton = new Button(composite, SWT.PUSH);
      referencedGenModelsTreeBrowseButton.setText(GenModelEditPlugin.INSTANCE.getString("_UI_Browse_label"));
      {
        GridData data = new GridData();
        data.horizontalAlignment = GridData.END;
        referencedGenModelsTreeBrowseButton.setLayoutData(data);
      }

      referencedGenModelsTreeBrowseButton.addSelectionListener
        (new SelectionAdapter()
         {
           public void widgetSelected(SelectionEvent event)
           {
             Collection genModelResources = new ArrayList();
             ResourceSelectionDialog resourceSelectionDialog = 
               new ResourceSelectionDialog
                 (PackagePage.this.getShell(), 
                  ResourcesPlugin.getWorkspace().getRoot(),
                  GenModelEditPlugin.INSTANCE.getString("_UI_SelectAllGeneratorModels_description"));

             resourceSelectionDialog.setInitialSelections(genModelResources.toArray());

             resourceSelectionDialog.open();
             StringBuffer text = new StringBuffer();
             Object [] result = resourceSelectionDialog.getResult();
             if (result != null)
             {
               referencedGenModels = new ResourceSetImpl();
               List genModels = new ArrayList();
               for (int i = 0; i < result.length; ++i)
               {
                 IResource resource = (IResource)result[i];
                 if (resource.getType() == IResource.FILE && !isInJavaOutput(resource) &&
                       "genmodel".equals(resource.getFullPath().getFileExtension()))
                 {
                   URI genModelURI = URI.createPlatformResourceURI(resource.getFullPath().toString());
                   Resource genModelResource = referencedGenModels.getResource(genModelURI, true);
                   genModels.add(genModelResource.getContents().get(0));
                 }
               }

               filterPackagesTable();

               referencedGenModelsCheckboxTreeViewer.setInput(new ItemProvider(genModels));

               Collection checkedGenPackages = new ArrayList();
               for (Iterator j = genModels.iterator(); j.hasNext(); )
               {
                 GenModel genModel = (GenModel)j.next();
                 for (Iterator k = genModel.getGenPackages().iterator(); k.hasNext(); )
                 {
                   GenPackage genPackage = (GenPackage)k.next();
                   for (Iterator l = ePackageList.iterator(); l.hasNext(); )
                   {
                     EPackage ePackage = (EPackage)l.next();
                     if (!checkedEPackageList.contains(ePackage))
                     {
                       if (genPackage.getEcorePackage().getNsURI().equalsIgnoreCase(ePackage.getNsURI()))
                       {
                         checkedGenPackages.add(genPackage);
                         break;
                       }
                     }
                   }
                 }
               }

               referencedGenModelsCheckboxTreeViewer.setSelection(new StructuredSelection(checkedGenPackages.toArray()), true);
             }
           }
         });

      final Tree referencedGenModelsTree = new Tree(composite, SWT.CHECK | SWT.BORDER | SWT.MULTI);
      referencedGenModelsCheckboxTreeViewer = new CheckboxTreeViewer(referencedGenModelsTree);
      {
        GridData data = new GridData();
        data.verticalAlignment = GridData.FILL;
        data.grabExcessHorizontalSpace = true;
        data.horizontalAlignment = GridData.FILL;
        data.horizontalSpan = 2;
        data.heightHint = 60;
        referencedGenModelsTree.setLayoutData(data);
      }
      GenModelItemProviderAdapterFactory genModelItemProviderAdapterFactory = 
         new GenModelItemProviderAdapterFactory()
         {
           public Adapter createGenPackageAdapter()
           {
             if (genPackageItemProvider == null)
             {
               genPackageItemProvider = 
                 new GenPackageItemProvider(this)
                 {
                   public Collection getChildrenFeatures(Object object)
                   {
                     return Collections.EMPTY_LIST;
                   }
                 };
             }
         
             return genPackageItemProvider;
           }
         };
      referencedGenModelsCheckboxTreeViewer.setContentProvider(new AdapterFactoryContentProvider(genModelItemProviderAdapterFactory));
      referencedGenModelsCheckboxTreeViewer.setLabelProvider(new AdapterFactoryLabelProvider(genModelItemProviderAdapterFactory));

      referencedGenModelsCheckboxTreeViewer.addCheckStateListener
        (new ICheckStateListener()
         {
           public void checkStateChanged(CheckStateChangedEvent event)
           {
             filterPackagesTable();
             validate();
             setPageComplete(isPageComplete());
           }
         });


      setControl(composite);
    }

    private boolean validateName(String fileName, String packageName)
    {
      String msg = null;
      if (fileName == null || fileName.equals(""))
      {
        msg = packageName == null ? 
          GenModelEditPlugin.INSTANCE.getString("_UI_EcoreModelFileNameCannotBeEmpty_message") :
          GenModelEditPlugin.INSTANCE.getString("_UI_EcoreModelFileNameForPackageCannotBeEmpty_message", new Object [] { packageName });
      }
      else if (!fileName.endsWith(".ecore"))
      {
        msg = packageName == null ?
          GenModelEditPlugin.INSTANCE.getString("_UI_EcoreModelFileNameMustEndWithEcore_message") :
          GenModelEditPlugin.INSTANCE.getString("_UI_EcoreModelFileNameForPackageMustEndWithEcore_message", new Object [] { packageName });
      }
      setErrorMessage(msg);
      return msg == null;
    }

    void validate()
    {
      List referencedEPackages = new UniqueEList();
      for (Iterator i = ePackageList.iterator(); i.hasNext(); )
      {
        EPackage ePackage = (EPackage)i.next();
        if (getCheckedEPackageList().contains(ePackage) || !filteredEPackageList.contains(ePackage))
        {
          for (Iterator j = ePackage.eAllContents(); j.hasNext();)
          {
            EObject eObject = (EObject)j.next();
            for (Iterator k = eObject.eCrossReferences().iterator(); k.hasNext(); )
            {
              Object o = k.next();
              if (o instanceof EClassifier)
              {
                EClassifier eClassifier = (EClassifier)o;
                referencedEPackages.add(eClassifier.getEPackage());
              }
            }
          }
        }
      }

      String errorMessage = null;
      for (Iterator i = referencedEPackages.iterator(); i.hasNext(); )
      {
        EPackage ePackage = (EPackage)i.next();
        if (!getCheckedEPackageList().contains(ePackage) && filteredEPackageList.contains(ePackage))
        {
          errorMessage = 
            GenModelEditPlugin.INSTANCE.getString("_UI_PackageIsUsedBySelectedPackage_message", new Object [] { ePackage.getName() });
        }
      }
      setErrorMessage(errorMessage);

      if (errorMessage == null)
      {
        List checked = getCheckedEPackageList();
        Iterator i = ePackageToEcoreFileInformationMap.entrySet().iterator();
        while (i.hasNext())
        {
          Map.Entry entry = (Map.Entry)i.next();
          EPackage ePackage = (EPackage)entry.getKey();
          if (checked.contains(ePackage))
          {
            String fileName = ((List)entry.getValue()).get(ECORE_FILE_COLUMN).toString();
            if (!validateName(fileName, ePackage.getName())) break;
          }
        }
      }
    }

    public void refreshPackagesTable()
    {
      WorkspaceModifyOperation initializeOperation =
        new WorkspaceModifyOperation()
        {
          protected void execute(IProgressMonitor progressMonitor) throws CoreException
          {
            try
            {
              switch (whichModel)
              {
                case ROSE:
                {
                  String roseModelLocationString = roseModelPage.getRoseModelLocationPath().toString();
    
                  progressMonitor.beginTask("", 2);
                  progressMonitor.subTask
                    (GenModelEditPlugin.INSTANCE.getString("_UI_CreatingPackagesFor_message", new Object [] { roseModelLocationString }));
    
                  roseUtil = new RoseUtil();
                  roseUtil.getVariableToDirectoryMap().putAll(roseModelPage.getPathMap());
            
                  ePackageToEcoreFileInformationMap.clear();
                  unitTree = roseUtil.createRoseUnitTreeAndTable(roseModelLocationString, null);
          
                  // roseUtil.showRoseUnitTree(unitTree);
          
                  // Determine the packages that have been generated.
                  //
                  roseUtil.createExtent4RoseUnitTree(unitTree);
                  roseUtil.processUnitTree(unitTree);
    
                  ePackageToInformationMap = new HashMap(roseUtil.getEPackageToInformationMap());
          
                  ePackageList.clear();
                  checkedEPackageList.clear();
                  traversePackages(unitTree);
    
                  filterPackagesTable();
    
                  if (!roseUtil.getStatus().isOK())
                  {
                    ErrorDialog.openError
                      (getShell(), 
                       GenModelEditPlugin.INSTANCE.getString("_UI_RoseToGeneratorModelConversionProblem_title"), 
                       GenModelEditPlugin.INSTANCE.getString("_UI_ConversionMayBeIncomplete_message"), 
                       roseUtil.getStatus());
                  }
                  break;
                }
                case XSD:
                {
                  List xmlSchemaModelLocations = xmlSchemaModelPage.getXMLSchemaModelLocations();
    
                  progressMonitor.beginTask("", 2);
                  progressMonitor.subTask
                    (GenModelEditPlugin.INSTANCE.getString("_UI_CreatingPackagesFor_message", new Object [] { xmlSchemaModelLocations }));
    
                  ePackageToEcoreFileInformationMap.clear();
          
                  ePackageList.clear();
                  checkedEPackageList.clear();
                  traversePackages(xmlSchemaModelPage.getEPackages());
    
                  filterPackagesTable();

                  break;
                }
                case ECORE:
                {
                  List ecoreModelLocations = ecoreModelPage.getEcoreModelLocations();
    
                  progressMonitor.beginTask("", 2);
                  progressMonitor.subTask
                    (GenModelEditPlugin.INSTANCE.getString("_UI_CreatingPackagesFor_message", new Object [] { ecoreModelLocations }));
    
                  ePackageToEcoreFileInformationMap.clear();
          
                  ePackageList.clear();
                  checkedEPackageList.clear();
                  traversePackages(ecoreModelPage.getEPackages());

                  // For the single-package case, use the same name as the file we're copying from.
                  //
                  if (originalGenModel == null && !ecoreModelLocations.isEmpty() && ePackageList.size() == 1)
                  {
                    URI ecoreModelLocation = (URI)ecoreModelLocations.get(0);
                    String ecoreModelName = ecoreModelLocation.lastSegment();
                    EPackage ePackage = (EPackage)ePackageList.get(0);
                    List fileInformation = (List)ePackageToEcoreFileInformationMap.get(ePackage);
                    if (ecoreModelName != null && ecoreModelName.length() > 0 &&
                        fileInformation != null && fileInformation.size() > 0)
                    {
                      int index = ecoreModelName.lastIndexOf('.');
                      StringBuffer buffer = new StringBuffer(ecoreModelName);
                      if (index != -1) buffer.setLength(index);
                      buffer.append(".ecore");
                      fileInformation.set(0, buffer);
                    }
                  }

                  filterPackagesTable();

                  break;
                }
                case JAVA:
                {
                  progressMonitor.beginTask("", 2);
                  progressMonitor.subTask(GenModelEditPlugin.INSTANCE.getString("_UI_CreatingPackages_message"));

                  javaEcoreBuilder = new JavaEcoreBuilder(getGenModelFile(), originalGenModel);
                  javaEcoreBuilder.run(progressMonitor, false);
                  IStatus status = javaEcoreBuilder.getStatus();
                  if (!status.isOK())
                  {
                    ErrorDialog.openError
                      (getShell(),
                       GenModelEditPlugin.INSTANCE.getString("_UI_ConversionProblem_title"),
                       GenModelEditPlugin.INSTANCE.getString("_UI_ProblemsEncounteredProcessingJava_message"),
                       status);
                  }
    
                  ePackageToEcoreFileInformationMap.clear();

                  List ePackages = new ArrayList();
                  for (Iterator i = javaEcoreBuilder.getGenModel().getGenPackages().iterator(); i.hasNext(); )
                  {
                    GenPackage genPackage = (GenPackage)i.next();
                    ePackages.add(genPackage.getEcorePackage());
                  }
                  ePackageList.clear();
                  checkedEPackageList.clear();
                  traversePackages(ePackages);

                  filterPackagesTable();

                  break;
                }
              }
            }
            catch (Exception exception)
            {
              GenModelEditPlugin.INSTANCE.log(exception);
            }
            finally
            {
              progressMonitor.done();
            }
          }
        };

      try
      {
        PackagePage.this.getContainer().run(false, false, initializeOperation);
      }
      catch (Exception exception)
      {
        GenModelEditPlugin.INSTANCE.log(exception);
      }

    }

    public void filterPackagesTable()
    {
      if (whichStyle == RELOAD && isDirty && originalGenModel != null)
      {
        Collection genModels = new HashSet();
        List checkedElements = new ArrayList();
        for (Iterator genPackages = originalGenModel.getUsedGenPackages().iterator(); genPackages.hasNext(); )
        {
          GenPackage genPackage = (GenPackage)genPackages.next();
          checkedElements.add(genPackage);
          if (genPackage.getGenModel() != null)
          {
            genModels.add(genPackage.getGenModel());
          }
        }
        referencedGenModelsCheckboxTreeViewer.setInput(new ItemProvider(genModels));
        referencedGenModelsCheckboxTreeViewer.setCheckedElements(checkedElements.toArray());
        referencedGenModelsCheckboxTreeViewer.expandAll();
      }
      else
      {
        checkedEPackageList = new ArrayList(Arrays.asList(packagesCheckboxTableViewer.getCheckedElements()));
      }

      filteredEPackageList = new ArrayList(ePackageList);
      Object [] checkedElements = referencedGenModelsCheckboxTreeViewer.getCheckedElements();

      for (Iterator ePackages = filteredEPackageList.iterator(); ePackages.hasNext(); )
      {
        EPackage ePackage = (EPackage)ePackages.next();
        int match = -1;
        for (int i = 0; i < checkedElements.length; ++i)
        {
          if (checkedElements[i] instanceof GenPackage)
          {
            if (((GenPackage)checkedElements[i]).getEcorePackage().getNsURI().equals(ePackage.getNsURI()))
            {
              match = i;
              break;
            }
          }
        }
        if (match != -1)
        {
          ePackageToGenPackageMap.put(ePackage, checkedElements[match]);
          ePackages.remove();
          checkedEPackageList.remove(ePackage);
        }
        else
        {
          ePackageToGenPackageMap.remove(ePackage);
        }
      }
      packagesCheckboxTableViewer.setInput(new ItemProvider(filteredEPackageList));
      packagesCheckboxTableViewer.setCheckedElements(checkedEPackageList.toArray());
    }

    protected void traversePackages(UnitTreeNode subNode)
    {
      ePackageList.addAll(subNode.getExtent());

      for (Iterator i = subNode.getExtent().iterator(); i.hasNext(); )
      {
        EPackage ePackage = (EPackage)i.next();
        List ecoreFileNameInformation = new ArrayList();
        ePackageToEcoreFileInformationMap.put(ePackage, ecoreFileNameInformation);
        String roseFileName = subNode.getRoseFileName();
        int indexOfSlash = roseFileName.lastIndexOf(File.separator);

        String ecoreFileName;
        if (roseFileName.endsWith(".cat") && indexOfSlash != -1)
        {
          String baseName = roseFileName.substring(indexOfSlash + 1, roseFileName.length() - 4);
          int indexOfDot = baseName.lastIndexOf(".");
          if (indexOfDot != -1)
          {
            baseName = baseName.substring(indexOfDot + 1);
          }
          ecoreFileName = baseName + ".ecore";
        }
        else if (subNode.getExtent().size() == 1)
        {
          ecoreFileName = subNode.getName() + ".ecore";
        }
        else
        {
          ecoreFileName = ePackage.getName() + ".ecore";
        }

        if (originalGenModel != null)
        {
          for (Iterator genPackages = originalGenModel.getGenPackages().iterator(); genPackages.hasNext(); )
          {
            GenPackage genPackage = (GenPackage)genPackages.next();
            if (genPackage.getEcorePackage() != null && genPackage.getEcorePackage().getNsURI().equals(ePackage.getNsURI()))
            {
              ecoreFileName = new Path(genPackage.getEcorePackage().eResource().getURI().path()).lastSegment();
              checkedEPackageList.add(ePackage);
              break;
            }
          }
        }

        ecoreFileNameInformation.add(new StringBuffer(ecoreFileName));
      }

      for (Iterator i = subNode.getNodes().iterator(); i.hasNext(); )
      {
        UnitTreeNode childTree = (UnitTreeNode)i.next();
        traversePackages(childTree);
      }
    }

    protected void traversePackages(Collection ePackages)
    {
      ePackageToInformationMap = new HashMap();
      ePackageList.addAll(ePackages);

      for (Iterator i = ePackages.iterator(); i.hasNext(); )
      {
        EPackage ePackage = (EPackage)i.next();

        List packageInformation = new ArrayList();
        ePackageToInformationMap.put(ePackage, packageInformation);

        List ecoreFileNameInformation = new ArrayList();
        ePackageToEcoreFileInformationMap.put(ePackage, ecoreFileNameInformation);

        String qualifiedName = ePackage.getName();
        String ecoreFileName = qualifiedName + ".ecore";
        int index = qualifiedName.lastIndexOf(".");
        if (index != -1)
        {
          packageInformation.add(qualifiedName.substring(0, index));
          ePackage.setName(qualifiedName.substring(index + 1));
        }
        else
        {
          packageInformation.add(null);
        }
        String prefix = ePackage.getName();
        packageInformation.add(Character.toUpperCase(prefix.charAt(0)) + prefix.substring(1));

        if (originalGenModel != null)
        {
          for (Iterator genPackages = originalGenModel.getGenPackages().iterator(); genPackages.hasNext(); )
          {
            GenPackage genPackage = (GenPackage)genPackages.next();
            if (genPackage.getEcorePackage() != null && genPackage.getEcorePackage().getNsURI().equals(ePackage.getNsURI()))
            {
              ecoreFileName = new Path(genPackage.getEcorePackage().eResource().getURI().path()).lastSegment();
              checkedEPackageList.add(ePackage);
  
              packageInformation.set(0, genPackage.getBasePackage());
              packageInformation.set(1, genPackage.getPrefix());

              break;
            }
          }
        }

        ecoreFileNameInformation.add(new StringBuffer(ecoreFileName));
      }
    }

    public RoseUtil getRoseUtil()
    {
      return roseUtil;
    }

    public UnitTreeNode getUnitTree()
    {
      return unitTree;
    }

    public List getEPackageList()
    {
      return ePackageList;
    }

    public List getFilteredEPackageList()
    {
      return filteredEPackageList;
    }

    public List getCheckedEPackageList()
    {
      return Arrays.asList(packagesCheckboxTableViewer.getCheckedElements());
    }

    public List getTargetPackageNames()
    {
      return targetPackageNames;
    }

    public Map getEPackageToEcoreFileInformationMap()
    {
      return ePackageToEcoreFileInformationMap;
    }

    public Map getEPackageToGenPackageMap()
    {
      return ePackageToGenPackageMap;
    }
  }

  public class DetailsPage extends WizardPage
  {
    protected Button loadFromRose;
    protected Button loadFromJavaAnnotations;
    protected Button loadFromXMLSchema;
    protected Button loadFromEcore;
    protected Button loadNothing;

    public DetailsPage(String pageId)
    {
      super(pageId);
    }

    public void chooseRose()
    {
      whichModel = ROSE;
      xmlSchemaModelPage.setPageComplete(true);
      ecoreModelPage.setPageComplete(true);
      roseModelPage.setPageComplete(roseModelPage.isPageComplete());
      packagePage.setPageComplete(packagePage.isPageComplete());
      setPageComplete(true);
    }

    public void chooseJava()
    {
      whichModel = JAVA;
      roseModelPage.setPageComplete(true);
      xmlSchemaModelPage.setPageComplete(true);
      ecoreModelPage.setPageComplete(true);
      packagePage.setPageComplete(packagePage.isPageComplete());
      setPageComplete(true);
    }

    public void chooseXSD()
    {
      whichModel = XSD;
      roseModelPage.setPageComplete(true);
      ecoreModelPage.setPageComplete(true);
      xmlSchemaModelPage.setPageComplete(xmlSchemaModelPage.isPageComplete());
      packagePage.setPageComplete(packagePage.isPageComplete());
      setPageComplete(true);
    }

    public void chooseEcore()
    {
      whichModel = ECORE;
      ecoreModelPage.setPageComplete(ecoreModelPage.isPageComplete());
      roseModelPage.setPageComplete(true);
      xmlSchemaModelPage.setPageComplete(true);
      packagePage.setPageComplete(packagePage.isPageComplete());
      setPageComplete(true);
    }

    public void chooseNothing()
    {
      whichModel = NOTHING;
      ecoreModelPage.setPageComplete(true);
      roseModelPage.setPageComplete(true);
      xmlSchemaModelPage.setPageComplete(true);
      packagePage.setPageComplete(true);
      setPageComplete(true);
    }

    public void setVisible(boolean visible)
    {
      super.setVisible(visible);
      if (visible && whichStyle == RELOAD && genModelFile != null)
      {
        getControl().getDisplay().asyncExec
          (new Runnable()
           {
             public void run()
             {
               ResourceSet resourceSet =  new ResourceSetImpl();
               try
               {
                 URI genModelURI = URI.createPlatformResourceURI(getGenModelFile().getFullPath().toString());
                 Resource resource = resourceSet.getResource(genModelURI, true);
                 originalGenModel = (GenModel)resource.getContents().get(0);
                 originalGenModel.reconcile();

                 Iterator values = originalGenModel.getForeignModel().iterator();
                 if (values.hasNext())
                 {
                   String firstValue = (String)values.next();
                   if (firstValue.endsWith(".mdl"))
                   {
                     chooseRose();
                     loadFromRose.setSelection(true);
                     loadFromXMLSchema.setSelection(false);
                     loadFromJavaAnnotations.setSelection(false);
                     loadFromEcore.setSelection(false);
                     if (loadNothing != null)
                     {
                       loadNothing.setSelection(false);
                     }
                   }
                   else if (firstValue.endsWith(".xsd") || firstValue.endsWith(".wsdl"))
                   {
                     firstValue = makeAbsolute(URI.createURI(firstValue), genModelURI).toString();
                     chooseXSD();
                     loadFromXMLSchema.setSelection(true);
                     loadFromRose.setSelection(false);
                     loadFromJavaAnnotations.setSelection(false);
                     loadFromEcore.setSelection(false);
                     if (loadNothing != null)
                     {
                       loadNothing.setSelection(false);
                     }
                   }
                   else if (firstValue.equals("@model"))
                   {
                     chooseJava();
                     loadFromJavaAnnotations.setSelection(true);
                     loadFromRose.setSelection(false);
                     loadFromXMLSchema.setSelection(false);
                     loadFromEcore.setSelection(false);
                     if (loadNothing != null)
                     {
                       loadNothing.setSelection(false);
                     }
                   }
                   else if (firstValue.endsWith(".ecore"))
                   {
                     firstValue = makeAbsolute(URI.createURI(firstValue), genModelURI).toString();
                     chooseEcore();
                     loadFromEcore.setSelection(true);
                     loadFromJavaAnnotations.setSelection(false);
                     loadFromRose.setSelection(false);
                     loadFromXMLSchema.setSelection(false);
                     if (loadNothing != null)
                     {
                       loadNothing.setSelection(false);
                     }
                   }
                 }
               }
               catch (Exception exception)
               {
                 GenModelEditPlugin.INSTANCE.log(exception);
               }
             }
           });
      }
    }

    public void createControl(Composite parent)
    {
      Composite composite = new Composite(parent, SWT.NONE);
      {
        GridLayout layout = new GridLayout();
        layout.numColumns = 1;
        layout.verticalSpacing = 12;
        composite.setLayout(layout);

        GridData data = new GridData();
        data.verticalAlignment = GridData.FILL;
        data.grabExcessVerticalSpace = true;
        data.horizontalAlignment = GridData.FILL;
        composite.setLayoutData(data);
      }

      if (whichStyle != NEW_PROJECT)
      {
        whichModel = JAVA;

        loadFromJavaAnnotations = new Button(composite, SWT.RADIO);
        loadFromJavaAnnotations.setSelection(true);
        loadFromJavaAnnotations.setText(GenModelEditPlugin.INSTANCE.getString("_UI_LoadFromJava_label"));
        {
          GridData data = new GridData();
          loadFromJavaAnnotations.setLayoutData(data);
        }

        loadFromJavaAnnotations.addSelectionListener
          (new SelectionAdapter()
           {
             public void widgetSelected(SelectionEvent event)
             {
               if (loadFromJavaAnnotations.getSelection())
               {
                 chooseJava();
               }
             }
           });
      }
      else
      {
        whichModel = XSD;
      }

      loadFromXMLSchema = new Button(composite, SWT.RADIO);
      loadFromXMLSchema.setText(GenModelEditPlugin.INSTANCE.getString("_UI_LoadFromXMLSchema_label"));
      {
        GridData data = new GridData();
        loadFromXMLSchema.setLayoutData(data);
      }

      loadFromXMLSchema.addSelectionListener
        (new SelectionAdapter()
         {
           public void widgetSelected(SelectionEvent event)
           {
             if (loadFromXMLSchema.getSelection())
             {
               chooseXSD();
             }
           }
         });

      if (Platform.getBundle("org.eclipse.xsd.editor") == null)
      {
        loadFromXMLSchema.setEnabled(false);
        if (whichModel == XSD)
        {
          whichModel = ECORE;
        }
      }
      else if (whichModel == XSD)
      {
        loadFromXMLSchema.setSelection(true);
      }

      loadFromEcore = new Button(composite, SWT.RADIO);
      if (whichModel == ECORE)
      {
        loadFromEcore.setSelection(true);
      }
      loadFromEcore.setText(GenModelEditPlugin.INSTANCE.getString("_UI_LoadFromEcore_label"));
      {
        GridData data = new GridData();
        loadFromEcore.setLayoutData(data);
      }

      loadFromEcore.addSelectionListener
        (new SelectionAdapter()
         {
           public void widgetSelected(SelectionEvent event)
           {
             if (loadFromEcore.getSelection())
             {
               chooseEcore();
             }
           }
         });

      loadFromRose = new Button(composite, SWT.RADIO);
      loadFromRose.setText(GenModelEditPlugin.INSTANCE.getString("_UI_loadFromRose_label"));
      {
        GridData data = new GridData();
        loadFromRose.setLayoutData(data);
      }

      xmlSchemaModelPage.setPageComplete(true);

      loadFromRose.addSelectionListener
        (new SelectionAdapter()
         {
           public void widgetSelected(SelectionEvent event)
           {
             if (loadFromRose.getSelection())
             {
               chooseRose();
             }
           }
         });

      if (whichStyle == NEW_PROJECT)
      {
        loadNothing = new Button(composite, SWT.RADIO);
        loadNothing.setText(GenModelEditPlugin.INSTANCE.getString("_UI_CreateEmptyProject_label"));
        {
          GridData data = new GridData();
          loadNothing.setLayoutData(data);
        }
  
        loadNothing.addSelectionListener
          (new SelectionAdapter()
           {
             public void widgetSelected(SelectionEvent event)
             {
               if (loadNothing.getSelection())
               {
                 chooseNothing();
               }
             }
           });
      }

      setControl(composite);
    }
  }

  public IWizardPage getNextPage(IWizardPage page)
  {
    if (page == detailsPage)
    {
      switch (whichModel)
      {
        case ROSE:
        {
          return roseModelPage;
        }
        case JAVA:
        {
          return packagePage;
        }
        case XSD:
        {
          return xmlSchemaModelPage;
        }
        case ECORE:
        {
          return ecoreModelPage;
        }
        case NOTHING:
        {
          return null;
        }
      }
    }
    else if (page == roseModelPage || page == xmlSchemaModelPage || page == ecoreModelPage)
    {
      return packagePage;
    }

    return super.getNextPage(page);
  }


  protected static boolean isInJavaOutput(IResource resource)
  {
    IProject project = resource.getProject();
    IJavaProject javaProject = JavaCore.create(project);
    try
    {
      if (javaProject.exists() &&
             project != project.getWorkspace().getRoot().findMember(javaProject.getOutputLocation()) &&
             javaProject.getOutputLocation().isPrefixOf(resource.getFullPath()))
      {
        return true;
      }
    }
    catch (JavaModelException exception)
    {
      GenModelEditPlugin.INSTANCE.log(exception);
    }

    return false;
  }

/*
  protected static FileSystemElement createFileSystemElement
    (FileSystemElement parent, IResource resource, Collection matchingResources, String extension)
  {
    FileSystemElement result = null;
    String label = resource.getName();

    switch (resource.getType())
    {
      case IResource.FILE:
      {
        if (resource.getName().endsWith("." + extension))
        {
          IProject project = resource.getProject();
          IJavaProject javaProject = JavaCore.create(project);
          try
          {
            if (!javaProject.exists() ||
                 project == project.getWorkspace().getRoot().findMember(javaProject.getOutputLocation()) ||
                 !javaProject.getOutputLocation().isPrefixOf(resource.getFullPath()))
            {
              result = new FileSystemElement(label, parent, false);
              result.setFileSystemObject(resource);
              matchingResources.add(result);
            }
          }
          catch (JavaModelException exception)
          {
            GenModelEditPlugin.INSTANCE.log(exception);
          }
        }
        break;
      }
      case IResource.ROOT:
      {
        label = GenModelEditPlugin.INSTANCE.getString("_UI_Workspace_label");
      }
      default:
      {
        result = new FileSystemElement(label, parent, true);
        result.setFileSystemObject(resource);
        try
        {
          IResource [] members = ((IContainer)resource).members();
          for (int i = 0; i < members.length; ++i)
          {
            FileSystemElement child = createFileSystemElement(result, members[i], matchingResources, extension);
          }
        }
        catch (CoreException exception)
        {
        }
        if (result.getFolders().size() == 0 && result.getFiles().size() == 0)
        {
          if (parent != null)
          {
            parent.removeFolder(result);
            result = null;
          }
        }
        break;
      }
    }

    return result;
  }
*/
}
