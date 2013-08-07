/**
 * Copyright (c) 2002-2012 IBM Corporation and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *   IBM - Initial API and implementation
 */
package org.eclipse.emf.codegen.ecore.genmodel.presentation;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.action.IContributionItem;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.action.Separator;
import org.eclipse.jface.dialogs.ProgressMonitorDialog;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.ISelectionProvider;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkingSet;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.actions.BaseSelectionListenerAction;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.IAdaptable;
import org.eclipse.core.runtime.Path;
import org.eclipse.emf.codegen.ecore.CodeGenEcorePlugin;
import org.eclipse.emf.codegen.ecore.generator.Generator;
import org.eclipse.emf.codegen.ecore.genmodel.GenAnnotation;
import org.eclipse.emf.codegen.ecore.genmodel.GenBase;
import org.eclipse.emf.codegen.ecore.genmodel.GenModel;
import org.eclipse.emf.codegen.ecore.genmodel.GenModelPackage;
import org.eclipse.emf.codegen.ecore.genmodel.generator.GenBaseGeneratorAdapter;
import org.eclipse.emf.codegen.ecore.genmodel.provider.GenModelEditPlugin;
import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.command.CommandWrapper;
import org.eclipse.emf.common.command.UnexecutableCommand;
import org.eclipse.emf.common.ui.action.ViewerFilterAction;
import org.eclipse.emf.common.ui.viewer.IViewerProvider;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EcorePackage;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.edit.command.AddCommand;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.emf.edit.domain.IEditingDomainProvider;
import org.eclipse.emf.edit.ui.action.CommandActionHandler;
import org.eclipse.emf.edit.ui.action.EditingDomainActionBarContributor;
import org.eclipse.emf.edit.ui.util.EditUIUtil;


/**
 * This is the action bar contributor for the GenModel model editor.
 * <!-- begin-user-doc -->
 * <!-- end-user-doc -->
 * @generated NOT
 */
public class GenModelActionBarContributor
  extends EditingDomainActionBarContributor
  implements ISelectionChangedListener
{
  /**
   * This keeps track of the active editor.
   */
  protected IEditorPart activeEditorPart;

  /**
   * This gets the selection from the active editor.
   */
  protected ISelection getActiveEditorSelection()
  {
    return activeEditorPart == null ? null :
      ((GenModelEditor)activeEditorPart).getSelection();
  }

  /**
   * This keeps track of the current selection provider.
   */
  protected ISelectionProvider selectionProvider;

  /**
   * This action opens the Properties view.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected IAction showPropertiesViewAction =
    new Action(GenModelEditPlugin.INSTANCE.getString("_UI_ShowPropertiesView_menu_item"))
    {
      @Override
      public void run()
      {
        try
        {
          getPage().showView("org.eclipse.ui.views.PropertySheet");
        }
        catch (PartInitException exception)
        {
          GenModelEditPlugin.INSTANCE.log(exception);
        }
      }
    };

  /**
   * This action refreshes the viewer of the current editor if the editor
   * implements {@link org.eclipse.emf.common.ui.viewer.IViewerProvider}.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected IAction refreshViewerAction =
    new Action(GenModelEditPlugin.INSTANCE.getString("_UI_RefreshViewer_menu_item"))
    {
      @Override
      public boolean isEnabled()
      {
        return activeEditorPart instanceof IViewerProvider;
      }

      @Override
      public void run()
      {
        if (activeEditorPart instanceof IViewerProvider)
        {
          Viewer viewer = ((IViewerProvider)activeEditorPart).getViewer();
          if (viewer != null)
          {
            viewer.refresh();
          }
        }
      }
    };

  /**
   * This is the menu manager for the "Generate" menu.
   */
  protected IMenuManager generateMenuManager;

  protected IAction generateModelAction = new GenerateAction
    (GenBaseGeneratorAdapter.MODEL_PROJECT_TYPE,
     CodeGenEcorePlugin.INSTANCE.getString("_UI_ModelProject_name"),
     GenModelEditPlugin.INSTANCE.getString("_UI_GenerateModel_menu_item"));

  protected IAction generateEditAction = new GenerateAction
    (GenBaseGeneratorAdapter.EDIT_PROJECT_TYPE,
     CodeGenEcorePlugin.INSTANCE.getString("_UI_EditProject_name"),
     GenModelEditPlugin.INSTANCE.getString("_UI_GenerateEdit_menu_item"));

  protected IAction generateEditorAction = new GenerateAction
    (GenBaseGeneratorAdapter.EDITOR_PROJECT_TYPE,
     CodeGenEcorePlugin.INSTANCE.getString("_UI_EditorProject_name"),
     GenModelEditPlugin.INSTANCE.getString("_UI_GenerateEditor_menu_item"));

  protected IAction generateTestsAction = new GenerateAction
    (GenBaseGeneratorAdapter.TESTS_PROJECT_TYPE ,
     CodeGenEcorePlugin.INSTANCE.getString("_UI_TestsProject_name"),
     GenModelEditPlugin.INSTANCE.getString("_UI_GenerateTests_menu_item"));

  protected IAction generateAllAction = new GenerateAction
  (new ProjectType[]
   {
     new ProjectType(GenBaseGeneratorAdapter.MODEL_PROJECT_TYPE, CodeGenEcorePlugin.INSTANCE.getString("_UI_ModelProject_name")),
     new ProjectType(GenBaseGeneratorAdapter.EDIT_PROJECT_TYPE, CodeGenEcorePlugin.INSTANCE.getString("_UI_EditProject_name")),
     new ProjectType(GenBaseGeneratorAdapter.EDITOR_PROJECT_TYPE, CodeGenEcorePlugin.INSTANCE.getString("_UI_EditorProject_name")),
     new ProjectType(GenBaseGeneratorAdapter.TESTS_PROJECT_TYPE, CodeGenEcorePlugin.INSTANCE.getString("_UI_TestsProject_name"))
   },
   GenModelEditPlugin.INSTANCE.getString("_UI_GenerateAll_menu_item"));

  /**
   * This implements the "Generate..." actions.
   */
  protected class GenerateAction extends Action
  {
    ProjectType[] projectTypes;
    Generator generator;

    public GenerateAction(Object projectType, String projectTypeName, String text)
    {
      super(text);
      this.projectTypes = new ProjectType[] { new ProjectType(projectType, projectTypeName) };
    }

    public GenerateAction(ProjectType[] projectTypes, String text)
    {
      super(text);
      this.projectTypes = projectTypes;
    }

    @Override
    public boolean isEnabled()
    {
      if (activeEditorPart instanceof GenModelEditor)
      {
        generator = ((GenModelEditor)activeEditorPart).getGenerator();
      }

      if (generator == null)
      {
        return false;
      }

      ISelection s = getActiveEditorSelection();
      if (!(s instanceof IStructuredSelection))
      {
        return false;
      }

      IStructuredSelection ss = (IStructuredSelection)s;
      if (ss.size() == 0)
      {
        return false;
      }

      for (Object object : ss.toList())
      {
        boolean canGenerateObject = false;

        for (int i = 0; i < projectTypes.length; i++)
        {
          if (generator.canGenerate(object, projectTypes[i].getType()))
          {
            canGenerateObject = true;
          }
        }

        if (!canGenerateObject)
        {
          return false;
        }
      }
      return true;
    }

    @Override
    public void run()
    {
      GeneratorUIUtil.GeneratorOperation operation =
        new GeneratorUIUtil.GeneratorOperation(activeEditorPart.getSite().getShell());
      operation.setRootDiagnosticMessage(getText());

      GenModel genModel = null;
      Collection<?> selection = ((IStructuredSelection)getActiveEditorSelection()).toList();
      for (Object object : selection)
      {
        for (int i = 0; i < projectTypes.length; i++)
        {
          if (object instanceof GenBase)
          {
            genModel = ((GenBase)object).getGenModel();
          }
          operation.addGeneratorAndArguments(generator, object, projectTypes[i].getType(), projectTypes[i].getName());
        }
      }

      Set<IProject> projects = new HashSet<IProject>();
      Set<IWorkingSet> workingSets = new HashSet<IWorkingSet>();
      if (genModel != null)
      {
        IProject project = ResourcesPlugin.getWorkspace().getRoot().getFile(new Path(genModel.getModelDirectory())).getProject();
        if (project != null)
        {
          IWorkbench workbench = PlatformUI.getWorkbench();
          for (IWorkingSet workingSet : workbench.getWorkingSetManager().getAllWorkingSets())
          {
            IAdaptable[] elements = workingSet.getElements();
            for (IAdaptable element : elements)
            {
              if (project.equals(element.getAdapter(IProject.class)))
              {
                workingSets.add(workingSet);
                continue;
              }
            }
          }
          if (!workingSets.isEmpty())
          {
            for (int i = 0; i < projectTypes.length; i++)
            {
              Object projectType = projectTypes[i].getType();
              if (GenBaseGeneratorAdapter.EDIT_PROJECT_TYPE.equals(projectType) && genModel.hasEditSupport())
              {
                IProject editProject = ResourcesPlugin.getWorkspace().getRoot().getFile(new Path(genModel.getEditDirectory())).getProject();
                if (!editProject.exists())
                {
                  projects.add(editProject);
                }
              }
              if (GenBaseGeneratorAdapter.EDITOR_PROJECT_TYPE.equals(projectType) && genModel.hasEditorSupport())
              {
                IProject editorProject = ResourcesPlugin.getWorkspace().getRoot().getFile(new Path(genModel.getEditorDirectory())).getProject();
                if (!editorProject.exists())
                {
                  projects.add(editorProject);
                }
              }
              if (GenBaseGeneratorAdapter.TESTS_PROJECT_TYPE.equals(projectType) && genModel.hasTestSupport())
              {
                IProject testsProject = ResourcesPlugin.getWorkspace().getRoot().getFile(new Path(genModel.getTestsDirectory())).getProject();
                if (!testsProject.exists())
                {
                  projects.add(testsProject);
                }
              }
            }
          }
        }
      }

      // This runs the options, and shows progress.
      // (It appears to be a bad thing to fork this onto another thread.)
      //
      try
      {
        new ProgressMonitorDialog(activeEditorPart.getSite().getShell()).run(true, true, operation);

        if (!projects.isEmpty())
        {
          for (IWorkingSet workingSet : workingSets)
          {
            List<IAdaptable> elements = new ArrayList<IAdaptable>(Arrays.asList(workingSet.getElements()));
            elements.addAll(projects);
            workingSet.setElements(workingSet.adaptElements(elements.toArray(new IAdaptable[elements.size()])));
          }
        }
      }
      catch (Exception exception)
      {
        // Something went wrong that shouldn't.
        //
        GenModelEditPlugin.INSTANCE.log(exception);
      }
    }
  }

  protected static class ProjectType
  {
    protected Object type;
    protected String name;

    public ProjectType(Object type, String name)
    {
      this.type = type;
      this.name = name;
    }

    public Object getType()
    {
      return type;
    }

    public String getName()
    {
      return name;
    }
  }

  protected ViewerFilterAction showGenAnnotationsAction = new ViewerFilterAction(GenModelEditPlugin.INSTANCE.getString("_UI_ShowGenAnnotation_menu_item"), IAction.AS_CHECK_BOX)
  {
    @Override
    public boolean select(Viewer viewer, Object parentElement, Object element)
    {
      return !(element instanceof GenAnnotation) || isChecked();
    }
  };

  protected abstract class CreateAction extends CommandActionHandler
  {
    protected String label;

    public CreateAction(String text, String label)
    {
      super(null, text);
      this.label = label;
    }

    public void dispose()
    {
      setEditingDomain(null);
    }

    @Override
    public Command createCommand(Collection<?> selection)
    {
      if (activeEditorPart instanceof IEditingDomainProvider)
      {
        setEditingDomain(((IEditingDomainProvider)activeEditorPart).getEditingDomain());
      }

      if (getEditingDomain() != null && selection.size() == 1)
      {
        Object selectedObject = selection.iterator().next();
        if (selectedObject instanceof GenBase)
        {
          Command command = doCreateCommand((GenBase)selectedObject);
          if (command != null)
          {
            command = new CommandWrapper(label, null, command);
          }
          return command;
        }
      }
      return UnexecutableCommand.INSTANCE;
    }

    protected abstract Command doCreateCommand(GenBase selectedObject);
  }

  protected CreateAction annotateAction = new CreateAction(
    GenModelEditPlugin.INSTANCE.getString("_UI_Annotate_menu_item"),
    GenModelEditPlugin.INSTANCE.getString("_UI_Annotate_text"))
  {
    @Override
    protected Command doCreateCommand(GenBase selectedObject)
    {
      return AddCommand.create(getEditingDomain(), selectedObject, GenModelPackage.Literals.GEN_BASE__GEN_ANNOTATIONS, selectedObject.getGenModel().createGenAnnotation());
    }
  };

  protected CreateAction addDetailAction = new CreateAction(
    GenModelEditPlugin.INSTANCE.getString("_UI_AddDetail_menu_item"),
    GenModelEditPlugin.INSTANCE.getString("_UI_AddDetail_text"))
  {
    @Override
    protected Command doCreateCommand(GenBase selectedObject)
    {
      return AddCommand.create(getEditingDomain(), selectedObject, GenModelPackage.Literals.GEN_ANNOTATION__DETAILS, EcoreUtil.create(EcorePackage.Literals.ESTRING_TO_STRING_MAP_ENTRY));
    }
  };

  protected static abstract class OpenEObjectEditorAction extends BaseSelectionListenerAction
  {
    protected EObject eObject;

    public OpenEObjectEditorAction(String text)
    {
      super(text);
    }

    public void dispose()
    {
      eObject = null;
      clearCache();
    }

    @Override
    public void run()
    {
      if (eObject != null)
      {
        try
        {
          EditUIUtil.openEditor(eObject);
        }
        catch (PartInitException e)
        {
          e.printStackTrace();
        }
      }
    }

    @Override
    protected boolean updateSelection(IStructuredSelection selection)
    {
      if (selection.size() == 1)
      {
        Object element = selection.getFirstElement();
        eObject = getEObject(element);
        return eObject != null;
      }
      return false;
    }

    protected abstract EObject getEObject(Object element);
  }

  protected OpenEObjectEditorAction openEcoreAction = new OpenEObjectEditorAction(GenModelEditPlugin.INSTANCE.getString("_UI_OpenEcore_menu_item"))
  {
    @Override
    protected EObject getEObject(Object element)
    {
      return element instanceof GenBase ? ((GenBase)element).getEcoreModelElement() : null;
    }
  };

  protected OpenEObjectEditorAction openGenModelAction = new OpenEObjectEditorAction(GenModelEditPlugin.INSTANCE.getString("_UI_OpenGenModel_menu_item"))
  {
    @Override
    protected EObject getEObject(Object element)
    {
      if (activeEditorPart instanceof IEditingDomainProvider && element instanceof EObject)
      {
        EObject eObject = (EObject)element;
        EditingDomain editingDomain = ((IEditingDomainProvider)activeEditorPart).getEditingDomain();
        if (editingDomain.getResourceSet().getResources().indexOf(eObject.eResource()) != 0)
        {
          return eObject;
        }
      }
      return null;
    }
  };


  /**
   * This creates an instance of the contributor.
   */
  public GenModelActionBarContributor()
  {
    super(ADDITIONS_LAST_STYLE);
    showGenAnnotationsAction.setChecked
     (Boolean.parseBoolean(GenModelEditPlugin.getPlugin().getDialogSettings().get("showGenAnnotationsAction")));
  }

  @Override
  public void dispose()
  {
    GenModelEditPlugin.getPlugin().getDialogSettings().put(
      "showGenAnnotationsAction", Boolean.toString(showGenAnnotationsAction.isChecked()));

    showGenAnnotationsAction.dispose();
    annotateAction.dispose();
    addDetailAction.dispose();
    openEcoreAction.dispose();
    openGenModelAction.dispose();

    super.dispose();
  }

  /**
   * This adds menu contributions for the generate actions.
   */
  @Override
  public void contributeToMenu(IMenuManager menuManager)
  {
    super.contributeToMenu(menuManager);

    generateMenuManager =
      new MenuManager(GenModelEditPlugin.INSTANCE.getString("_UI_Generate_menu"), "org.eclipse.emf.codegen.ecore.genmodelMenuID");
    menuManager.insertAfter("additions", generateMenuManager);
    generateMenuManager.add(generateModelAction);
    generateMenuManager.add(generateEditAction);
    generateMenuManager.add(generateEditorAction);
    generateMenuManager.add(generateTestsAction);
    generateMenuManager.add(generateAllAction);

    generateMenuManager.add(new Separator("annotation-actions"));
    generateMenuManager.add(showGenAnnotationsAction);

    generateMenuManager.add(new Separator("global-actions"));
  }

  /**
   * This adds Separators for editor additions to the tool bar.
   */
  @Override
  public void contributeToToolBar(IToolBarManager toolBarManager)
  {
    toolBarManager.add(new Separator("genmodel-settings"));
    toolBarManager.add(new Separator("genmodel-additions"));
  }

  /**
   * When the active editor changes, this remembers the change,
   */
  @Override
  public void setActiveEditor(IEditorPart part)
  {
    super.setActiveEditor(part);

    if (part instanceof GenModelEditor)
    {
      showGenAnnotationsAction.addViewer(((GenModelEditor)part).getViewer());
      showGenAnnotationsAction.setEnabled(true);
    }
    else
    {
      showGenAnnotationsAction.setEnabled(false);
    }

    activeEditorPart = part;

    // Switch to the new selection provider.
    //
    if (selectionProvider != null)
    {
      selectionProvider.removeSelectionChangedListener(this);
    }
    if (part == null)
    {
      selectionProvider = null;
    }
    else
    {
      selectionProvider = part.getSite().getSelectionProvider();
      selectionProvider.addSelectionChangedListener(this);

      // Fake a selection changed event to update the menus.
      //
      if (selectionProvider.getSelection() != null)
      {
        selectionChanged(new SelectionChangedEvent(selectionProvider, selectionProvider.getSelection()));
      }
    }
  }

  /**
   * This implements {@link ISelectionChangedListener}, refreshing the
   * "Generate..." action contribution managers in the pull-down menu.
   */
  public void selectionChanged(SelectionChangedEvent event)
  {
    IContributionItem[] items = generateMenuManager.getItems();
    for (int i = 0, len = items.length; i < len; i++) items[i].update();

    annotateAction.selectionChanged(event);
    addDetailAction.selectionChanged(event);
    openEcoreAction.selectionChanged(event);
    openGenModelAction.selectionChanged(event);
  }

  /**
   * This populates the pop-up menu before it appears.
   */
  @Override
  public void menuAboutToShow(IMenuManager menuManager)
  {
    generateAllAction.setEnabled(generateAllAction.isEnabled());
    generateTestsAction.setEnabled(generateTestsAction.isEnabled());
    generateEditorAction.setEnabled(generateEditorAction.isEnabled());
    generateEditAction.setEnabled(generateEditAction.isEnabled());
    generateModelAction.setEnabled(generateModelAction.isEnabled());
    refreshViewerAction.setEnabled(refreshViewerAction.isEnabled());

    super.menuAboutToShow(menuManager);

    menuManager.insertBefore("edit", new Separator("generate-actions"));
    menuManager.insertAfter("generate-actions", generateAllAction);
    menuManager.insertAfter("generate-actions", generateTestsAction);
    menuManager.insertAfter("generate-actions", generateEditorAction);
    menuManager.insertAfter("generate-actions", generateEditAction);
    menuManager.insertAfter("generate-actions", generateModelAction);

    menuManager.insertBefore("edit", new Separator("open-actions"));
    menuManager.insertAfter("open-actions", openGenModelAction);
    menuManager.insertAfter("open-actions", openEcoreAction);

    if (showGenAnnotationsAction.isChecked())
    {
      menuManager.insertBefore("edit", new Separator("annotation-actions"));
      if (addDetailAction.isEnabled()) menuManager.insertAfter("annotation-actions", addDetailAction);
      if (annotateAction.isEnabled()) menuManager.insertAfter("annotation-actions", annotateAction);
    }
  }

  /**
   * This inserts global actions before the "additions-end" separator.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  protected void addGlobalActions(IMenuManager menuManager)
  {
    menuManager.insertAfter("additions-end", new Separator("ui-actions"));
    menuManager.insertAfter("ui-actions", showPropertiesViewAction);

    refreshViewerAction.setEnabled(refreshViewerAction.isEnabled());
    menuManager.insertAfter("ui-actions", refreshViewerAction);

    super.addGlobalActions(menuManager);
  }

}
