/**
 * <copyright> 
 *
 * Copyright (c) 2002-2005 IBM Corporation and others.
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
 * $Id: GenModelActionBarContributor.java,v 1.10 2005/03/07 21:25:52 khussey Exp $
 */
package org.eclipse.emf.codegen.ecore.genmodel.presentation;


import java.util.Collection;
import java.util.Iterator;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.SubProgressMonitor;
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
import org.eclipse.jface.wizard.WizardDialog;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IFileEditorInput;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.actions.WorkspaceModifyOperation;

import org.eclipse.emf.codegen.ecore.genmodel.GenBase;
import org.eclipse.emf.codegen.ecore.genmodel.provider.GenModelEditPlugin;
import org.eclipse.emf.common.ui.viewer.IViewerProvider;
import org.eclipse.emf.edit.ui.action.EditingDomainActionBarContributor;

//import org.eclipse.emf.edit.ui.action.LoadResourceAction;
//import org.eclipse.emf.edit.ui.action.ValidateAction;
// import java.util.LinkedList;
// import org.eclipse.emf.codegen.ecore.genmodel.GenPropertyKind;
// import org.eclipse.jface.action.ActionContributionItem;
// import org.eclipse.jface.action.IAction;
// import org.eclipse.jface.action.IContributionManager;
// import org.eclipse.jface.action.SubContributionItem;


/**
 * This is the action bar contributor for the GenModel model editor.
 * <!-- begin-user-doc -->
 * <!-- end-user-doc -->
 * @generated
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
      public boolean isEnabled()
      {
        return activeEditorPart instanceof IViewerProvider;
      }

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

  protected class ReloadAction extends Action
  {
    public ReloadAction()
    {
      super(GenModelEditPlugin.INSTANCE.getString("_UI_GenModel_Reload"));
    }

    public void run()
    {
      IFile file = ((IFileEditorInput)activeEditorPart.getEditorInput()).getFile();
      EMFProjectWizard emfProjectWizard = new EMFProjectWizard(file);
      WizardDialog wizardDialog = new WizardDialog(activeEditorPart.getEditorSite().getShell(), emfProjectWizard);
      wizardDialog.create();
      wizardDialog.getShell().setSize(540, 580);
      int result = wizardDialog.open();
      ((GenModelEditor)activeEditorPart).handleActivate();
    }
  }

  protected IAction reloadAction = new ReloadAction();

  protected IAction generateAction = new GenerateAction(GenModelEditPlugin.INSTANCE.getString("_UI_GenerateModel_menu_item"))
  {
    protected boolean canGenerate(GenBase genObject)
    {
      return genObject.canGenerate();
    }

    protected void generate(GenBase genObject, IProgressMonitor progressMonitor)
    {
      genObject.generate(progressMonitor);
    }
  };

  protected IAction generateEditAction = new GenerateAction(GenModelEditPlugin.INSTANCE.getString("_UI_GenerateEdit_menu_item"))
  {
    protected boolean canGenerate(GenBase genObject)
    {
      return genObject.canGenerateEdit();
    }

    protected void generate(GenBase genObject, IProgressMonitor progressMonitor)
    {
      genObject.generateEdit(progressMonitor);
    }  
  };

  protected IAction generateEditorAction = new GenerateAction(GenModelEditPlugin.INSTANCE.getString("_UI_GenerateEditor_menu_item"))
  {
    protected boolean canGenerate(GenBase genObject)
    {
      return genObject.canGenerateEditor();
    }

    protected void generate(GenBase genObject, IProgressMonitor progressMonitor)
    {
      genObject.generateEditor(progressMonitor);
    }  
  };
  
  protected IAction generateTestsAction = new GenerateAction(GenModelEditPlugin.INSTANCE.getString("_UI_GenerateTests_menu_item"))
  {
    protected boolean canGenerate(GenBase genObject)
    {
      return genObject.canGenerateTests();
    }
    
    protected void generate(GenBase genObject, IProgressMonitor progressMonitor)
    {
      genObject.generateTests(progressMonitor);
    }
  };
  
  protected IAction generateSchemaAction = new GenerateAction(GenModelEditPlugin.INSTANCE.getString("_UI_GenerateSchema_menu_item"))
  {
    protected boolean canGenerate(GenBase genObject)
    {
      return genObject.canGenerateSchema();
    }

    protected void generate(GenBase genObject, IProgressMonitor progressMonitor)
    {
      progressMonitor.beginTask("", 1);
      genObject.generateSchema(new SubProgressMonitor(progressMonitor, 1));
    }
  };  

  protected IAction generateAllAction = new GenerateAction(GenModelEditPlugin.INSTANCE.getString("_UI_GenerateAll_menu_item"))
  {
    protected boolean canGenerate(GenBase genObject)
    {
      return genObject.canGenerate() || genObject.canGenerateEdit() ||
        genObject.canGenerateEditor();
    }

    protected void generate(GenBase genObject, IProgressMonitor progressMonitor)
    {
      progressMonitor.beginTask("", 4);
      genObject.generate(new SubProgressMonitor(progressMonitor, 1));
      genObject.generateEdit(new SubProgressMonitor(progressMonitor, 1));
      genObject.generateEditor(new SubProgressMonitor(progressMonitor, 1));
      genObject.generateTests(new SubProgressMonitor(progressMonitor, 1));
    }  
  };

  /**
   * This is a base class for the "Generate..." actions.
   */
  protected abstract class GenerateAction extends Action
  {
    public GenerateAction(String text)
    {
      super(text);
    }

    protected abstract boolean canGenerate(GenBase genObject);
    protected abstract void generate(GenBase genObject, IProgressMonitor progressMonitor);

    public boolean isEnabled()
    {
      ISelection s = getActiveEditorSelection();
      if (!(s instanceof IStructuredSelection))
      {
        return false;
      }

      IStructuredSelection ss = (IStructuredSelection) s;
      if (ss.size() == 0)
      {
        return false;
      }

      for (Iterator iter = ss.iterator(); iter.hasNext(); )
      {
        Object selected = iter.next();
        if (!(selected instanceof GenBase) || !canGenerate((GenBase)selected))
        {
          return false;
        }
      }
      return true;
    }
  
    public void run()
    {
      // Do the work within an operation because this is a long running activity that modifies the workbench.
      WorkspaceModifyOperation operation = new WorkspaceModifyOperation()
      {
        // This is the method that gets invoked when the operation runs.
        //
        protected void execute(IProgressMonitor progressMonitor) throws CoreException
        {
          Collection selection = ((IStructuredSelection)getActiveEditorSelection()).toList();
          progressMonitor.beginTask("", selection.size());
          try
          {
            for (Iterator iter = selection.iterator(); iter.hasNext(); )
            {
              generate((GenBase)iter.next(), new SubProgressMonitor(progressMonitor, 1));
            }          
          }
          catch (Exception exception)
          {
            GenModelEditPlugin.INSTANCE.log(exception);
          }
          progressMonitor.done();
        }
      };
    
      // This runs the options, and shows progress.
      // (It appears to be a bad thing to fork this onto another thread.)
      //
      try
      {
        new ProgressMonitorDialog(activeEditorPart.getSite().getShell()).run(true, false, operation);
      }
      catch (Exception exception)
      {
        // Something went wrong that shouldn't.
        //
        GenModelEditPlugin.INSTANCE.log(exception);
      }
    } 
  }

  /**
   * This creates an instance of the contributor.
   */
  public GenModelActionBarContributor()
  {
  }

  /**
   * This adds menu contributions for the generate actions.
   */
  public void contributeToMenu(IMenuManager menuManager)
  {
    super.contributeToMenu(menuManager);

    generateMenuManager = 
      new MenuManager(GenModelEditPlugin.INSTANCE.getString("_UI_Generate_menu"), "org.eclipse.emf.codegen.ecore.genmodelMenuID");
    menuManager.insertAfter("additions", generateMenuManager);
    generateMenuManager.add(generateAction);
    generateMenuManager.add(generateEditAction);
    generateMenuManager.add(generateEditorAction);
    generateMenuManager.add(generateTestsAction);
    generateMenuManager.add(generateAllAction);

    generateMenuManager.add(new Separator("schema-actions"));
    generateMenuManager.add(generateSchemaAction);    
    
    generateMenuManager.add(new Separator("global-actions"));
    generateMenuManager.add(reloadAction);
  }

  /**
   * This adds Separators for editor additions to the tool bar.
   */
  public void contributeToToolBar(IToolBarManager toolBarManager)
  {
    toolBarManager.add(new Separator("genmodel-settings"));
    toolBarManager.add(new Separator("genmodel-additions"));
  }

  /**
   * When the active editor changes, this remembers the change,
   */
  public void setActiveEditor(IEditorPart part)
  {
    super.setActiveEditor(part);
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
  }

  /**
   * This populates the pop-up menu before it appears.
   */
  public void menuAboutToShow(IMenuManager menuManager)
  {
    generateAllAction.setEnabled(generateAllAction.isEnabled());
    generateSchemaAction.setEnabled(generateSchemaAction.isEnabled());
    generateTestsAction.setEnabled(generateTestsAction.isEnabled());
    generateEditorAction.setEnabled(generateEditorAction.isEnabled());
    generateEditAction.setEnabled(generateEditAction.isEnabled());
    generateAction.setEnabled(generateAction.isEnabled());
    refreshViewerAction.setEnabled(refreshViewerAction.isEnabled());
    
    super.menuAboutToShow(menuManager);
    menuManager.insertBefore("additions", new Separator("generate-actions"));
    menuManager.insertAfter("generate-actions", generateAllAction);
    menuManager.insertAfter("generate-actions", generateTestsAction);
    menuManager.insertAfter("generate-actions", generateEditorAction);
    menuManager.insertAfter("generate-actions", generateEditAction);
    menuManager.insertAfter("generate-actions", generateAction);

    menuManager.insertBefore("additions", new Separator("schema-actions"));
    menuManager.insertAfter("schema-actions", generateSchemaAction);
  }

  /**
   * This inserts global actions before the "additions-end" separator.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected void addGlobalActions(IMenuManager menuManager)
  {
    menuManager.insertAfter("additions-end", new Separator("ui-actions"));
    menuManager.insertAfter("ui-actions", showPropertiesViewAction);

    refreshViewerAction.setEnabled(refreshViewerAction.isEnabled());		
    menuManager.insertAfter("ui-actions", refreshViewerAction);

    super.addGlobalActions(menuManager);
  }

}
