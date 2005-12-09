/**
 * <copyright>
 *
 * Copyright (c) 2002-2004 IBM Corporation and others.
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
 * $Id: GenModelEditor.java,v 1.19 2005/12/09 19:08:50 marcelop Exp $
 */
package org.eclipse.emf.codegen.ecore.genmodel.presentation;


import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.EventObject;
import java.util.Iterator;
import java.util.Map;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IMarker;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IResourceChangeEvent;
import org.eclipse.core.resources.IResourceChangeListener;
import org.eclipse.core.resources.IResourceDelta;
import org.eclipse.core.resources.IResourceDeltaVisitor;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.jdt.core.JavaCore;
import org.eclipse.jdt.core.formatter.DefaultCodeFormatterConstants;
import org.eclipse.jface.action.IMenuListener;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.IStatusLineManager;
import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.action.Separator;
import org.eclipse.jface.dialogs.ErrorDialog;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.dialogs.ProgressMonitorDialog;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.ISelectionProvider;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.StructuredViewer;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CTabFolder;
import org.eclipse.swt.dnd.DND;
import org.eclipse.swt.dnd.Transfer;
import org.eclipse.swt.events.ControlAdapter;
import org.eclipse.swt.events.ControlEvent;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.ui.IActionBars;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IEditorSite;
import org.eclipse.ui.IFileEditorInput;
import org.eclipse.ui.IPartListener;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.actions.WorkspaceModifyOperation;
import org.eclipse.ui.dialogs.SaveAsDialog;
import org.eclipse.ui.part.FileEditorInput;
import org.eclipse.ui.part.MultiPageEditorPart;
import org.eclipse.ui.views.properties.IPropertySheetPage;
import org.eclipse.ui.views.properties.PropertySheet;
import org.eclipse.ui.views.properties.PropertySheetPage;

import org.eclipse.emf.codegen.ecore.genmodel.GenModel;
import org.eclipse.emf.codegen.ecore.genmodel.provider.GenModelEditPlugin;
import org.eclipse.emf.common.command.BasicCommandStack;
import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.command.CommandStack;
import org.eclipse.emf.common.command.CommandStackListener;
import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.common.ui.viewer.IViewerProvider;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.plugin.EcorePlugin;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.edit.domain.AdapterFactoryEditingDomain;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.emf.edit.domain.IEditingDomainProvider;
import org.eclipse.emf.edit.provider.AdapterFactoryItemDelegator;
import org.eclipse.emf.edit.provider.ComposedAdapterFactory;
import org.eclipse.emf.edit.ui.action.EditingDomainActionBarContributor;
import org.eclipse.emf.edit.ui.celleditor.AdapterFactoryTreeEditor;
import org.eclipse.emf.edit.ui.dnd.EditingDomainViewerDropAdapter;
import org.eclipse.emf.edit.ui.dnd.LocalTransfer;
import org.eclipse.emf.edit.ui.dnd.ViewerDragAdapter;
import org.eclipse.emf.edit.ui.provider.AdapterFactoryContentProvider;
import org.eclipse.emf.edit.ui.provider.AdapterFactoryLabelProvider;


/**
 * This is an example of a GenModel model editor.
 * <!-- begin-user-doc -->
 * <!-- end-user-doc -->
 * @generated NOT
 */
public class GenModelEditor
  extends MultiPageEditorPart
  implements IEditingDomainProvider, ISelectionProvider, IMenuListener, IViewerProvider
{
  /**
   * This keeps track of the editing domain that is used to track all changes to the model.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected AdapterFactoryEditingDomain editingDomain;

  /**
   * This is the one adapter factory used for providing views of the model.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected ComposedAdapterFactory adapterFactory;

  /**
   * This is the property sheet page.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected PropertySheetPage propertySheetPage;

  /**
   * This keeps track of the active content viewer, which may be either one of the viewers in the pages or the content outline viewer.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected Viewer currentViewer;

  /**
   * This listens to which ever viewer is active.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected ISelectionChangedListener selectionChangedListener;

  /**
   * This keeps track of all the {@link org.eclipse.jface.viewers.ISelectionChangedListener}s that are listening to this editor.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected Collection selectionChangedListeners = new ArrayList();

  /**
   * This keeps track of the selection of the editor as a whole.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected ISelection editorSelection = StructuredSelection.EMPTY;

  /**
   * This listens for when the outline becomes active
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated NOT
   */
  protected IPartListener partListener =
    new IPartListener()
    {
      public void partActivated(IWorkbenchPart p)
      {
        if (p instanceof PropertySheet)
        {
          if (((PropertySheet)p).getCurrentPage() == propertySheetPage)
          {
            getActionBarContributor().setActiveEditor(GenModelEditor.this);
            handleActivate();
          }
        }
        else if (p == GenModelEditor.this)
        {
          handleActivate();
        }
      }
      public void partBroughtToTop(IWorkbenchPart p)
      {
      }
      public void partClosed(IWorkbenchPart p)
      {
      }
      public void partDeactivated(IWorkbenchPart p)
      {
      }
      public void partOpened(IWorkbenchPart p)
      {
      }
    };

  /**
   * Resources that have been removed since last activation.
   * @generated
   */
  Collection removedResources = new ArrayList();

  /**
   * Resources that have been changed since last activation.
   * @generated
   */
  Collection changedResources = new ArrayList();

  /**
   * Resources that have been saved.
   * @generated
   */
  Collection savedResources = new ArrayList();

  /**
   * This listens for workspace changes.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected IResourceChangeListener resourceChangeListener =
    new IResourceChangeListener()
    {
      public void resourceChanged(IResourceChangeEvent event)
      {
        // Only listening to these.
        // if (event.getType() == IResourceDelta.POST_CHANGE)
        {
          IResourceDelta delta = event.getDelta();
          try
          {
            class ResourceDeltaVisitor implements IResourceDeltaVisitor
            {
              protected ResourceSet resourceSet = editingDomain.getResourceSet();
              protected Collection changedResources = new ArrayList();
              protected Collection removedResources = new ArrayList();

              public boolean visit(IResourceDelta delta)
              {
                if (delta.getResource().getType() == IResource.FILE)
                {
                  if ((delta.getKind() & (IResourceDelta.CHANGED | IResourceDelta.REMOVED)) != 0)
                  {
                    Resource resource = resourceSet.getResource(URI.createURI(delta.getFullPath().toString()), false);
                    if (resource != null)
                    {
                      if ((delta.getKind() & IResourceDelta.REMOVED) != 0)
                      {
                        removedResources.add(resource);
                      }
                      else
                      {
                        changedResources.add(resource);
                      }
                    }
                  }
                }

                return true;
              }

              public Collection getChangedResources()
              {
                return changedResources;
              }

              public Collection getRemovedResources()
              {
                return removedResources;
              }
            }

            ResourceDeltaVisitor visitor = new ResourceDeltaVisitor();
            delta.accept(visitor);

            if (!visitor.getRemovedResources().isEmpty())
            {
              removedResources.addAll(visitor.getRemovedResources());
              if (!isDirty())
              {
                getSite().getShell().getDisplay().asyncExec
                  (new Runnable()
                   {
                     public void run()
                     {
                       getSite().getPage().closeEditor(GenModelEditor.this, false);
                       GenModelEditor.this.dispose();
                     }
                   });
              }
            }

            if (!visitor.getChangedResources().isEmpty())
            {
              changedResources.addAll(visitor.getChangedResources());
            }
          }
          catch (CoreException exception)
          {
            GenModelEditPlugin.INSTANCE.log(exception);
          }
        }
      }
    };


  /**
   * Handles activation of the editor or it's associated views.
   * @generated
   */
  public void handleActivate()
  {
    if (!removedResources.isEmpty())
    {
      if (handleDirtyConflict())
      {
        getSite().getPage().closeEditor(GenModelEditor.this, false);
        GenModelEditor.this.dispose();
      }
      else
      {
        removedResources.clear();
        changedResources.clear();
        savedResources.clear();
      }
    }
    else if (!changedResources.isEmpty())
    {
      changedResources.removeAll(savedResources);
      handleChangedResources();
      changedResources.clear();
      savedResources.clear();
    }
  }


  /**
   * Handles what to do with changed resources on activation.
   * @generated NOT
   */
  protected void handleChangedResources()
  {
    if (!changedResources.isEmpty() && (!isDirty() || handleDirtyConflict()))
    {
      editingDomain.getCommandStack().flush();
      Resource mainResource = (Resource)editingDomain.getResourceSet().getResources().get(0);
      for (Iterator i = changedResources.iterator(); i.hasNext(); )
      {
        Resource resource = (Resource)i.next();
        if (resource.isLoaded())
        {
          resource.unload();
          try
          {
            resource.load(Collections.EMPTY_MAP);
          }
          catch (IOException exception)
          {
            GenModelEditPlugin.INSTANCE.log(exception);
          }
        }
      }

      currentViewer.setInput(mainResource);
      GenModel genModel = (GenModel)mainResource.getContents().get(0);
      initialize(genModel);
    }
  }

  protected void initialize(GenModel genModel)
  {
    genModel.reconcile();
    genModel.setCanGenerate(true);
    validate();

    Map options = JavaCore.getOptions();
    String tabSize = (String)options.get(DefaultCodeFormatterConstants.FORMATTER_TAB_SIZE);
    String braceStyle = (String)options.get(DefaultCodeFormatterConstants.FORMATTER_BRACE_POSITION_FOR_TYPE_DECLARATION);
    String tabCharacter = (String)options.get(DefaultCodeFormatterConstants.FORMATTER_TAB_CHAR);
    if (JavaCore.TAB.equals(tabCharacter))
    {
       genModel.getJControlModel().setLeadingTabReplacement("\t");
    }
    else
    {
      String spaces = "";
      for (int i = Integer.parseInt(tabSize); i > 0; --i)
      {
        spaces += " ";
      }
      genModel.getJControlModel().setLeadingTabReplacement(spaces);
    }
    genModel.getJControlModel().setConvertToStandardBraceStyle(DefaultCodeFormatterConstants.END_OF_LINE.equals(braceStyle));
  }

  /**
   * Shows a dialog that asks if conflicting changes should be discarded.
   * @generated
   */
  protected boolean handleDirtyConflict()
  {
    return
      MessageDialog.openQuestion
        (getSite().getShell(),
         getString("_UI_FileConflict_label"),
         getString("_WARN_FileConflict"));
  }

  /**
   * This creates a model editor.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated NOT
   */
  public GenModelEditor()
  {
    super();

    adapterFactory = new ComposedAdapterFactory(ComposedAdapterFactory.Descriptor.Registry.INSTANCE);

    // Create the command stack that will notify this editor as commands are executed.
    //
    BasicCommandStack commandStack = new BasicCommandStack();

    // Add a listener to set the most recent command's affected objects to be the selection of the viewer with focus.
    //
    commandStack.addCommandStackListener
      (new CommandStackListener()
       {
         public void commandStackChanged(final EventObject event)
         {
           getContainer().getDisplay().asyncExec
             (new Runnable()
              {
                public void run()
                {
                  firePropertyChange(IEditorPart.PROP_DIRTY);

                  // Try to select the affected objects.
                  //
                  Command mostRecentCommand = ((CommandStack)event.getSource()).getMostRecentCommand();
                  if (mostRecentCommand != null)
                  {
                    setSelectionToViewer(mostRecentCommand.getAffectedObjects());
                  }

                  if (propertySheetPage != null  && !propertySheetPage.getControl().isDisposed())
                  {
                    propertySheetPage.refresh();
                  }
                }
              });
         }
       });

    // Create the editing domain with a special command stack.
    //
    editingDomain = new AdapterFactoryEditingDomain(adapterFactory, commandStack)
    {
      public boolean isReadOnly(Resource resource)
      {
        return super.isReadOnly(resource) || getResourceSet().getResources().indexOf(resource) != 0;  
      }
    };
  }

  /**
   * This is here for the listener to be able to call it.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected void firePropertyChange(int action)
  {
    super.firePropertyChange(action);
  }

  /**
   * This sets the selection into whichever viewer is active.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setSelectionToViewer(Collection collection)
  {
    final Collection theSelection = collection;
    // Make sure it's okay.
    //
    if (theSelection != null && !theSelection.isEmpty())
    {
      // I don't know if this should be run this deferred
      // because we might have to give the editor a chance to process the viewer update events
      // and hence to update the views first.
      //
      //
      Runnable runnable =
        new Runnable()
        {
          public void run()
          {
            // Try to select the items in the current content viewer of the editor.
            //
            if (currentViewer != null)
            {
              currentViewer.setSelection(new StructuredSelection(theSelection.toArray()), true);
            }
          }
        };
      runnable.run();
    }
  }

  /**
   * This returns the editing domain as required by the {@link IEditingDomainProvider} interface.
   * This is important for implementing the static methods of {@link AdapterFactoryEditingDomain}
   * and for supporting {@link org.eclipse.emf.edit.ui.action.CommandAction}.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EditingDomain getEditingDomain()
  {
    return editingDomain;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public class ReverseAdapterFactoryContentProvider extends AdapterFactoryContentProvider
  {
    public ReverseAdapterFactoryContentProvider(AdapterFactory adapterFactory)
    {
      super(adapterFactory);
    }

    public Object [] getElements(Object object)
    {
      Object parent = super.getParent(object);
      return (parent == null ? Collections.EMPTY_SET : Collections.singleton(parent)).toArray();
    }

    public Object [] getChildren(Object object)
    {
      Object parent = super.getParent(object);
      return (parent == null ? Collections.EMPTY_SET : Collections.singleton(parent)).toArray();
    }

    public boolean hasChildren(Object object)
    {
      Object parent = super.getParent(object);
      return parent != null;
    }

    public Object getParent(Object object)
    {
      return null;
    }
  }


  /**
   * This makes sure that one content viewer, either for the current page or the outline view, if it has focus,
   * is the current one.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setCurrentViewer(Viewer viewer)
  {
    // If it is changing...
    //
    if (currentViewer != viewer)
    {
      if (selectionChangedListener == null)
      {
        // Create the listener on demand.
        //
        selectionChangedListener =
          new ISelectionChangedListener()
          {
            // This just notifies those things that are affected by the section.
            //
            public void selectionChanged(SelectionChangedEvent selectionChangedEvent)
            {
              setSelection(selectionChangedEvent.getSelection());
            }
          };
      }

      // Stop listening to the old one.
      //
      if (currentViewer != null)
      {
        currentViewer.removeSelectionChangedListener(selectionChangedListener);
      }

      // Start listening to the new one.
      //
      if (viewer != null)
      {
        viewer.addSelectionChangedListener(selectionChangedListener);
      }

      // Remember it.
      //
      currentViewer = viewer;

      // Set the editors selection based on the current viewer's selection.
      //
      setSelection(currentViewer == null ? StructuredSelection.EMPTY : currentViewer.getSelection());
    }
  }

  /**
   * This returns the viewer as required by the {@link IViewerProvider} interface.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public Viewer getViewer()
  {
    return currentViewer;
  }

  /**
   * This creates a context menu for the viewer and adds a listener as well registering the menu for extension.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected void createContextMenuFor(StructuredViewer viewer)
  {
    MenuManager contextMenu = new MenuManager("#PopUp");
    contextMenu.add(new Separator("additions"));
    contextMenu.setRemoveAllWhenShown(true);
    contextMenu.addMenuListener(this);
    Menu menu= contextMenu.createContextMenu(viewer.getControl());
    viewer.getControl().setMenu(menu);
    getSite().registerContextMenu(contextMenu, viewer);

    int dndOperations = DND.DROP_COPY | DND.DROP_MOVE | DND.DROP_LINK;
    Transfer[] transfers = new Transfer[] { LocalTransfer.getInstance() };
    viewer.addDragSupport(dndOperations, transfers, new ViewerDragAdapter(viewer));
    viewer.addDropSupport(dndOperations, transfers, new EditingDomainViewerDropAdapter(editingDomain, viewer));
  }

  /**
   * This is the method used by the framework to install your own controls.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated NOT
   */
  public void createPages()
  {
    // I assume that the input is a file object.
    //
    IFileEditorInput modelFile = (IFileEditorInput)getEditorInput();

    editingDomain.getResourceSet().getURIConverter().getURIMap().putAll(EcorePlugin.computePlatformURIMap());
    
    try
    {
      // Load the resource through the editing domain.
      //
      Resource resource = 
        editingDomain.loadResource
          (URI.createPlatformResourceURI(modelFile.getFile().getFullPath().toString(), true).toString());

      // Initialize the formatting styles based on the Eclipse preferences.
      //
      GenModel genModel = (GenModel)resource.getContents().get(0);
      initialize(genModel);
    }
    catch (Exception exception)
    {
      GenModelEditPlugin.INSTANCE.log(exception);
    }

    // Create a page for the selection tree view.
    //
    {
      Tree tree = new Tree(getContainer(), SWT.MULTI);
      TreeViewer treeViewer = new TreeViewer(tree);
      setCurrentViewer(treeViewer);

      treeViewer.setContentProvider(new AdapterFactoryContentProvider(adapterFactory));
      treeViewer.setLabelProvider(new AdapterFactoryLabelProvider(adapterFactory));
      treeViewer.setInput(editingDomain.getResourceSet().getResources().get(0)); 

      new AdapterFactoryTreeEditor(treeViewer.getTree(), adapterFactory);

      createContextMenuFor(treeViewer);
      addPage(tree);
    }
    setActivePage(0);

    getContainer().addControlListener
      (new ControlAdapter()
       {
         boolean guard = false;
         public void controlResized(ControlEvent event)
         {
           if (!guard)
           {
             guard = true;
             hideTabs();
             guard = false;
           }
         }
       });
  }

  protected void hideTabs()
  {
    if (getPageCount() <= 1)
    {
      setPageText(0, "");
      if (getContainer() instanceof CTabFolder)
      {
        ((CTabFolder)getContainer()).setTabHeight(1);
        Point point = getContainer().getSize();
        getContainer().setSize(point.x, point.y + 6);
      }
    }
  }

  /**
   * This is used to track the active viewer.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected void pageChange(int pageIndex)
  {
    super.pageChange(pageIndex);

    // This is a temporary workaround... EATM
    //
    Control control = getControl(pageIndex);
    if (control != null)
    {
      control.setVisible(true);
      control.setFocus();
    }
  }

  /**
   * This is how the framework determines which interfaces we implement.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated NOT
   */
  public Object getAdapter(Class key)
  {
    if (key.equals(IPropertySheetPage.class)) return getPropertySheetPage();
    return super.getAdapter(key);
  }


  /**
   * This accesses a cached version of the property sheet.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public IPropertySheetPage getPropertySheetPage()
  {
    if (propertySheetPage == null)
    {
      propertySheetPage =
        new PropertySheetPage()
        {
          public void makeContributions(IMenuManager menuManager, IToolBarManager toolBarManager, IStatusLineManager statusLineManager)
          {
            super.makeContributions(menuManager, toolBarManager, statusLineManager);
          }

          public void setActionBars(IActionBars actionBars)
          {
            super.setActionBars(actionBars);
            getActionBarContributor().shareGlobalActions(this, actionBars);
          }
        };
      propertySheetPage.setPropertySourceProvider(new AdapterFactoryContentProvider(adapterFactory));
    }

    return propertySheetPage;
  }

  /**
   * This is for implementing {@link IEditorPart} and simply tests the command stack.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public boolean isDirty()
  {
    return ((BasicCommandStack)editingDomain.getCommandStack()).isSaveNeeded();
  }

  /**
   * This is for implementing {@link IEditorPart} and simply saves the model file.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void doSave(IProgressMonitor progressMonitor)
  {
    // Do the work within an operation because this is a long running activity that modifies the workbench.
    //
    WorkspaceModifyOperation operation =
      new WorkspaceModifyOperation()
      {
        // This is the method that gets invoked when the operation runs.
        //
        protected void execute(IProgressMonitor monitor) throws CoreException
        {
          try
          {
            // Save the resource to the file system.
            //
            Resource savedResource = (Resource)editingDomain.getResourceSet().getResources().get(0);
            savedResources.add(savedResource);
            savedResource.save(Collections.EMPTY_MAP);
          }
          catch (Exception exception)
          {
            GenModelEditPlugin.INSTANCE.log(exception);
          }
        }
      };

    try
    {
      // This runs the options, and shows progress.
      // (It appears to be a bad thing to fork this onto another thread.)
      //
      new ProgressMonitorDialog(getSite().getShell()).run(true, false, operation);

      // Refresh the necessary state.
      //
      ((BasicCommandStack)editingDomain.getCommandStack()).saveIsDone();
      firePropertyChange(IEditorPart.PROP_DIRTY);
    }
    catch (Exception exception)
    {
      // Something went wrong that shouldn't.
      //
      GenModelEditPlugin.INSTANCE.log(exception);
    }
  }

  /**
   * This always returns false because it is not current supported.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public boolean isSaveAsAllowed()
  {
    return true;
  }

  /**
   * This also changes the editor's input.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void doSaveAs()
  {
    SaveAsDialog saveAsDialog= new SaveAsDialog(getSite().getShell());
    saveAsDialog.open();
    IPath path= saveAsDialog.getResult();
    if (path != null)
    {
      IFile file = ResourcesPlugin.getWorkspace().getRoot().getFile(path);
      if (file != null)
      {
        ((Resource)editingDomain.getResourceSet().getResources().get(0)).setURI
          (URI.createPlatformResourceURI(file.getFullPath().toString(), true));
        IFileEditorInput modelFile = new FileEditorInput(file);
        setInput(modelFile);
        setPartName(file.getName());
        doSave(getActionBars().getStatusLineManager().getProgressMonitor());
      }
    }
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void gotoMarker(IMarker marker)
  {
  }

  /**
   * This is called during startup.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void init(IEditorSite site, IEditorInput editorInput) throws PartInitException, PartInitException, PartInitException, PartInitException, PartInitException, PartInitException, PartInitException, PartInitException, PartInitException, PartInitException
  {
    if (editorInput instanceof IFileEditorInput)
    {
      setSite(site);
      setInput(editorInput);
      setPartName(((IFileEditorInput)editorInput).getFile().getName());
      site.setSelectionProvider(this);
      site.getPage().addPartListener(partListener);
      ResourcesPlugin.getWorkspace().addResourceChangeListener(resourceChangeListener, IResourceChangeEvent.POST_CHANGE);
    }
    else
    {
      throw new PartInitException("Invalid Input: Must be IFileEditorInput.");
    }
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setFocus()
  {
    getControl(getActivePage()).setFocus();
  }

  /**
   * This implements {@link org.eclipse.jface.viewers.ISelectionProvider}.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void addSelectionChangedListener(ISelectionChangedListener listener)
  {
    selectionChangedListeners.add(listener);
  }

  /**
   * This implements {@link org.eclipse.jface.viewers.ISelectionProvider}.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void removeSelectionChangedListener(ISelectionChangedListener listener)
  {
    selectionChangedListeners.remove(listener);
  }

  /**
   * This implements {@link org.eclipse.jface.viewers.ISelectionProvider} to return this editor's overall selection.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public ISelection getSelection()
  {
    return editorSelection;
  }

  /**
   * This implements {@link org.eclipse.jface.viewers.ISelectionProvider} to set this editor's overall selection.
   * Calling this result will notify the listeners.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setSelection(ISelection selection)
  {
    editorSelection = selection;
    for (Iterator listeners = selectionChangedListeners.iterator(); listeners.hasNext(); )
    {
      ISelectionChangedListener listener = (ISelectionChangedListener)listeners.next();
      listener.selectionChanged(new SelectionChangedEvent(this, selection));
    }
    setStatusLineManager(selection);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated NOT
   */
  public void setStatusLineManager(ISelection selection)
  {
    IStatusLineManager statusLineManager = getActionBars().getStatusLineManager();
    if(statusLineManager != null)
    {
      if (selection instanceof IStructuredSelection)
      {
        Collection collection = ((IStructuredSelection)selection).toList();
        switch (collection.size())
        {
          case 0:
          {
            statusLineManager.setMessage(getString("_UI_NoObjectSelected"));
            break;
          }
          case 1:
          {
            String text = new AdapterFactoryItemDelegator(adapterFactory).getText(collection.iterator().next());
            statusLineManager.setMessage(getString("_UI_SingleObjectSelected", text));
            break;
          }
          default:
          {
            statusLineManager.setMessage(getString("_UI_MultiObjectSelected", Integer.toString(collection.size())));
            break;
          }
        }
      }
      else
      {
        statusLineManager.setMessage("");
      }
    }
  }

  /**
   * This looks up a string in the plugin's plugin.properties file.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private static String getString(String key)
  {
    return GenModelEditPlugin.INSTANCE.getString(key);
  }

  /**
   * This looks up a string in plugin.properties, making a substitution.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private static String getString(String key, Object s1)
  {
    return GenModelEditPlugin.INSTANCE.getString(key, new Object [] { s1 });
  }

  /**
   * This implements {@link org.eclipse.jface.action.IMenuListener} to help fill the context menus with contributions from the Edit menu.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void menuAboutToShow(IMenuManager menuManager)
  {
    ((IMenuListener)getEditorSite().getActionBarContributor()).menuAboutToShow(menuManager);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EditingDomainActionBarContributor getActionBarContributor()
  {
    return (EditingDomainActionBarContributor)getEditorSite().getActionBarContributor();
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public IActionBars getActionBars()
  {
    return getActionBarContributor().getActionBars();
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public AdapterFactory getAdapterFactory()
  {
    return adapterFactory;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void dispose()
  {
    ResourcesPlugin.getWorkspace().removeResourceChangeListener(resourceChangeListener);

    getSite().getPage().removePartListener(partListener);

    adapterFactory.dispose();

    getActionBarContributor().setActiveEditor(null);

    if (propertySheetPage != null)
    {
      propertySheetPage.dispose();
    }

    super.dispose();
  }

  public void validate()
  {
    Resource mainResource = (Resource)editingDomain.getResourceSet().getResources().get(0);
    GenModel genModel = (GenModel)mainResource.getContents().get(0);
    IStatus status = genModel.validate();
    if (!status.isOK())
    {
      ErrorDialog.openError
        (getSite().getShell(),
         GenModelEditPlugin.INSTANCE.getString("_UI_ModelProblems_title"),
         "",
         status);
    }
  }
}
