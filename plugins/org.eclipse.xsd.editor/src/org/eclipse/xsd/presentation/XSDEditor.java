/**
 * <copyright>
 *
 * Copyright (c) 2002-2007 IBM Corporation and others.
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
 * $Id: XSDEditor.java,v 1.24 2007/05/28 18:28:23 emerks Exp $
 */
package org.eclipse.xsd.presentation;


import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.EventObject;
import java.util.LinkedList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.xml.sax.helpers.DefaultHandler;

import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IMarker;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IWorkspaceRunnable;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.SubProgressMonitor;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.ActionContributionItem;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.action.IContributionItem;
import org.eclipse.jface.action.IContributionManager;
import org.eclipse.jface.action.IMenuListener;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.IStatusLineManager;
import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.action.Separator;
import org.eclipse.jface.action.SubContributionItem;
import org.eclipse.jface.dialogs.ProgressMonitorDialog;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.text.BadLocationException;
import org.eclipse.jface.text.DocumentEvent;
import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.IDocumentListener;
import org.eclipse.jface.text.source.ISourceViewer;
import org.eclipse.jface.text.source.IVerticalRuler;
import org.eclipse.jface.viewers.CellEditor;
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
import org.eclipse.swt.dnd.DND;
import org.eclipse.swt.dnd.Transfer;
import org.eclipse.swt.events.KeyAdapter;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.MouseAdapter;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.widgets.Composite;
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
import org.eclipse.ui.editors.text.TextEditor;
import org.eclipse.ui.ide.IGotoMarker;
import org.eclipse.ui.part.FileEditorInput;
import org.eclipse.ui.part.MultiPageEditorPart;
import org.eclipse.ui.part.MultiPageSelectionProvider;
import org.eclipse.ui.views.contentoutline.ContentOutline;
import org.eclipse.ui.views.contentoutline.ContentOutlinePage;
import org.eclipse.ui.views.contentoutline.IContentOutlinePage;
import org.eclipse.ui.views.properties.IPropertyDescriptor;
import org.eclipse.ui.views.properties.IPropertySheetPage;
import org.eclipse.ui.views.properties.IPropertySource;
import org.eclipse.ui.views.properties.PropertySheet;
import org.eclipse.ui.views.properties.PropertySheetPage;

import org.eclipse.emf.common.command.BasicCommandStack;
import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.command.CommandStack;
import org.eclipse.emf.common.command.CommandStackListener;
import org.eclipse.emf.common.ui.ViewerPane;
import org.eclipse.emf.common.ui.celleditor.ExtendedComboBoxCellEditor;
import org.eclipse.emf.common.ui.viewer.IViewerProvider;
import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.xmi.XMLLoad;
import org.eclipse.emf.ecore.xmi.XMLResource;
import org.eclipse.emf.ecore.xmi.impl.SAXXMLHandler;
import org.eclipse.emf.ecore.xmi.impl.XMLLoadImpl;
import org.eclipse.emf.ecore.xmi.impl.XMLResourceFactoryImpl;
import org.eclipse.emf.ecore.xmi.impl.XMLResourceImpl;
import org.eclipse.emf.ecore.xml.type.internal.DataValue.EncodingMap;
import org.eclipse.emf.edit.domain.AdapterFactoryEditingDomain;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.emf.edit.domain.IEditingDomainProvider;
import org.eclipse.emf.edit.provider.AdapterFactoryItemDelegator;
import org.eclipse.emf.edit.provider.IDisposable;
import org.eclipse.emf.edit.provider.IItemPropertyDescriptor;
import org.eclipse.emf.edit.provider.IItemPropertySource;
import org.eclipse.emf.edit.provider.ItemProvider;
import org.eclipse.emf.edit.ui.action.CreateChildAction;
import org.eclipse.emf.edit.ui.action.CreateSiblingAction;
import org.eclipse.emf.edit.ui.action.EditingDomainActionBarContributor;
import org.eclipse.emf.edit.ui.celleditor.AdapterFactoryTreeEditor;
import org.eclipse.emf.edit.ui.dnd.EditingDomainViewerDropAdapter;
import org.eclipse.emf.edit.ui.dnd.LocalTransfer;
import org.eclipse.emf.edit.ui.dnd.ViewerDragAdapter;
import org.eclipse.emf.edit.ui.provider.AdapterFactoryContentProvider;
import org.eclipse.emf.edit.ui.provider.AdapterFactoryLabelProvider;
import org.eclipse.emf.edit.ui.provider.ExtendedImageRegistry;
import org.eclipse.emf.edit.ui.provider.PropertyDescriptor;
import org.eclipse.emf.edit.ui.provider.PropertySource;
import org.eclipse.emf.edit.ui.util.EditUIUtil;

import org.eclipse.xsd.XSDAttributeDeclaration;
import org.eclipse.xsd.XSDAttributeGroupDefinition;
import org.eclipse.xsd.XSDAttributeUse;
import org.eclipse.xsd.XSDComplexTypeDefinition;
import org.eclipse.xsd.XSDConcreteComponent;
import org.eclipse.xsd.XSDDiagnostic;
import org.eclipse.xsd.XSDDiagnosticSeverity;
import org.eclipse.xsd.XSDElementDeclaration;
import org.eclipse.xsd.XSDModelGroup;
import org.eclipse.xsd.XSDModelGroupDefinition;
import org.eclipse.xsd.XSDParticle;
import org.eclipse.xsd.XSDSchema;
import org.eclipse.xsd.XSDSimpleTypeDefinition;
import org.eclipse.xsd.XSDTypeDefinition;
import org.eclipse.xsd.ecore.XSDEcoreBuilder;
import org.eclipse.xsd.provider.XSDItemProviderAdapterFactory;
import org.eclipse.xsd.provider.XSDSemanticItemProviderAdapterFactory;
import org.eclipse.xsd.util.XSDParser;
import org.eclipse.xsd.util.XSDResourceFactoryImpl;
import org.eclipse.xsd.util.XSDResourceImpl;
import org.eclipse.xsd.util.XSDSwitch;



/**
 * This is a an example of a xsd model editor.
 */
public class XSDEditor
  extends MultiPageEditorPart
  implements IEditingDomainProvider, ISelectionProvider, IMenuListener, IViewerProvider
{
  /**
   * This keeps track of the root object of the model.
   */
  protected XSDSchema xsdSchema;

  /**
   * This keeps track of the editing domain that is used to track all changes to the model.
   */
  protected AdapterFactoryEditingDomain editingDomain;

  /**
   * This is the adapter factory used for providing the syntactive views of the model.
   */
  protected XSDItemProviderAdapterFactory syntacticAdapterFactory;

  /**
   * This is the adapter factory used for providing the semantic views of the model.
   */
  protected XSDItemProviderAdapterFactory semanticAdapterFactory;

  /**
   * This is the content outline page.
   */
  protected IContentOutlinePage contentOutlinePage;

  /**
   * This is a kludge...
   */
  protected IStatusLineManager contentOutlineStatusLineManager;

  /**
   * This is the content outline page's viewer.
   */
  protected TreeViewer contentOutlineViewer;

  /**
   * This is the property sheet page.
   */
  protected PropertySheetPage propertySheetPage;

  /**
   * This source part of the editor.
   */
  protected TextEditor textEditor;
  protected ISourceViewer sourceViewer;

  /**
   * This is the syntactic viewer that shadows the selection in the content outline.
   * The parent relation must be correctly defined for this to work.
   */
  protected TreeViewer syntacticSelectionViewer;

  /**
   * This is the semantic viewer that shadows the selection in the content outline.
   */
  protected TreeViewer semanticSelectionViewer;

  /**
   * This keeps track of the active viewer pane, in the book.
   */
  protected ViewerPane currentViewerPane;

  /**
   * This keeps track of the active content viewer, which may be either one of the viewers in the pages or the content outline viewer.
   */
  protected Viewer currentViewer;

  /**
   * This listens to which ever viewer is active.
   */
  protected ISelectionChangedListener selectionChangedListener;

  /**
   * This keeps track of all the {@link org.eclipse.jface.viewers.ISelectionChangedListener}s that are listening to this editor.
   */
  protected Collection<ISelectionChangedListener> selectionChangedListeners = new ArrayList<ISelectionChangedListener>();

  /**
   * This keeps track of the selection of the editor as a whole.
   */
  protected ISelection editorSelection = StructuredSelection.EMPTY;

  /**
   * This is the outline action to select the next unresolved component.
   */
  protected SelectDiagnosticAction selectNextDiagnosticsAction;

  /**
   * This is the outline action to select the previous unresolved component.
   */
  protected SelectDiagnosticAction selectPreviousDiagnosticsAction;

  /**
   * This is the outline action to select the next use of a component.
   */
  protected SelectUseAction selectNextUseAction;

  /**
   * This is the outline action to select the previous use of a component.
   */
  protected SelectUseAction selectPreviousUseAction;

  /**
   * This listens for when things becomes active.
   */
  protected IPartListener partListener =
    new IPartListener()
    {
      public void partActivated(IWorkbenchPart p)
      {
        handlePartActivated(p);
      }
      public void partBroughtToTop(IWorkbenchPart p)
      {
        // Ignore
      }
      public void partClosed(IWorkbenchPart p)
      {
        // Ignore
      }
      public void partDeactivated(IWorkbenchPart p)
      {
        // Ignore
      }
      public void partOpened(IWorkbenchPart p)
      {
        // Ignore
      }
    };

  /**
   * This creates a model editor.
   */
  public XSDEditor()
  {
    super();

    // Create an adapter factory that yields item providers.
    //
    syntacticAdapterFactory = new XSDItemProviderAdapterFactory();
    semanticAdapterFactory = new XSDSemanticItemProviderAdapterFactory();

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

                  handleStructuredModelChange();

                  updateActions();

                  if (propertySheetPage != null)
                  {
                    propertySheetPage.refresh();
                  }
                }
              });

         }
       });

    // Create the editing domain with a special command stack.
    //
    editingDomain = new AdapterFactoryEditingDomain(syntacticAdapterFactory, commandStack);

    // Register our xsd resource factory for this context.
    //
    editingDomain.getResourceSet().getResourceFactoryRegistry().getExtensionToFactoryMap().put("xsd", new XSDResourceFactoryImpl());
  }

  protected void updateActions()
  {
    if (selectNextDiagnosticsAction != null)
    {
      selectNextDiagnosticsAction.updateAction();
      selectPreviousDiagnosticsAction.updateAction();

      selectNextUseAction.updateAction();
      selectPreviousUseAction.updateAction();
    }
  }

  protected String determineEncoding()
  {
    String encoding = (String)((XSDResourceImpl)xsdSchema.eResource()).getDefaultSaveOptions().get(XSDResourceImpl.XSD_ENCODING);
    if (encoding != null && EncodingMap.getIANA2JavaMapping(encoding) != null)
    {
      encoding = EncodingMap.getIANA2JavaMapping(encoding);
    }
    return encoding;
  }

  protected boolean handledStructuredModelChange = false;
  protected void handleStructuredModelChange()
  {
    IDocument document = textEditor.getDocumentProvider().getDocument(textEditor.getEditorInput());
    if (xsdSchema.getElement() == null)
    {
      xsdSchema.updateElement();
    }

    ByteArrayOutputStream out = new ByteArrayOutputStream();
    try
    {
      xsdSchema.eResource().save(out, null);

      String encoding = determineEncoding();
      String newContent = encoding == null ? out.toString() : out.toString(encoding);
      String oldContent = document.get();

      int startIndex = 0;
      while (startIndex < newContent.length() &&
               startIndex < oldContent.length() &&
               newContent.charAt(startIndex) == oldContent.charAt(startIndex))
      {
        ++startIndex;
      }
      int newEndIndex = newContent.length() - 1;
      int oldEndIndex = oldContent.length() - 1;
      while (newEndIndex >= startIndex &&
               oldEndIndex >= startIndex &&
               newContent.charAt(newEndIndex) == oldContent.charAt(oldEndIndex))
      {
        --newEndIndex;
        --oldEndIndex;
      }

      String replacement = newContent.substring(startIndex, newEndIndex + 1);
      int length = oldEndIndex - startIndex + 1;
      handledStructuredModelChange = true;
      document.replace(startIndex, length, replacement);
    }
    catch (Exception exception)
    {
      XSDEditorPlugin.INSTANCE.log(exception);
    }
  }

  /**
   * This handles part activation.
   */
  protected void handlePartActivated(IWorkbenchPart workbenchPart)
  {
    if (workbenchPart == this)
    {
      if (getActivePage() == 0)
      {
        setCurrentViewer((Viewer)sourceViewer);
      }
    }
    else if (workbenchPart instanceof ContentOutline)
    {
      if (((ContentOutline)workbenchPart).getCurrentPage() == contentOutlinePage)
      {
        getEditorSite().getActionBarContributor().setActiveEditor(XSDEditor.this);

        setCurrentViewer(contentOutlineViewer);
      }
    }
    else if (workbenchPart instanceof PropertySheet)
    {
      if (((PropertySheet)workbenchPart).getCurrentPage() == propertySheetPage)
      {
        getActionBarContributor().setActiveEditor(XSDEditor.this);
      }
    }
  }

  /**
   * This is here for the listener to be able to call it.
   */
  @Override
  protected void firePropertyChange(int action)
  {
    super.firePropertyChange(action);
  }

  /**
   * This sets the selection into whichever viewer is active.
   */
  public void setSelectionToViewer(final Collection<?> collection)
  {
    // Make sure it's okay.
    //
    if (collection != null && !collection.isEmpty())
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
              currentViewer.setSelection(new StructuredSelection(collection.toArray()), true);
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
   */
  public EditingDomain getEditingDomain()
  {
    return editingDomain;
  }

  public void setCurrentViewerPane(ViewerPane viewerPane)
  {
    if (currentViewerPane != viewerPane)
    {
      if (currentViewerPane != null)
      {
        currentViewerPane.showFocus(false);
      }
      currentViewerPane = viewerPane;
    }

    if (currentViewerPane != null)
    {
      setCurrentViewer(currentViewerPane.getViewer());
    }
  }

  /**
   * This makes sure that one content viewer, either for the current page or the outline view, if it has focus,
   * is the current one.
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
   * This is the contributor for the XSD model editor.
   */
  static public class ActionBarContributor
    extends EditingDomainActionBarContributor
    implements ISelectionChangedListener
  {
    protected IEditorPart activeEditorPart;
    protected ISelectionProvider selectionProvider;

    /**
     * This action refreshes the viewer of the current editor if the editor
     * implements {@link org.eclipse.emf.common.ui.viewer.IViewerProvider}.
     */
    protected IAction refreshViewerAction =
      new Action(XSDEditorPlugin.INSTANCE.getString("_UI_RefreshViewer_menu_item"))
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
     * This action opens the Properties view.
     */
    protected IAction showPropertiesViewAction =
      new Action(XSDEditorPlugin.INSTANCE.getString("_UI_ShowPropertiesView_menu_item"))
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
            XSDEditorPlugin.INSTANCE.log(exception);
          }
        }
      };

    /**
     * This action enable or disable automatic validation.
     */
    protected IAction validateAutomaticallyAction =
      new Action(XSDEditorPlugin.INSTANCE.getString("_UI_ValidateAutomatically_menu_item"))
      {
        @Override
        public void run()
        {
          ((XSDEditor)activeEditorPart).setValidateAutomatically(isChecked());
        }
      };

    /**
     * This will contain one CreateChildAction corresponding to each
     * descriptor generated for the current selection.
     */
    protected Collection<IAction> createChildActions = Collections.emptyList();

    /**
     * This is the menu manager into which menu contribution items should be
     * added for the child creation actions.
     */
    protected IMenuManager createChildMenuManager;

    /**
     * This will contain one CreateSiblingAction corresponding to each
     * descriptor generated for the current selection's parent.
     */
    protected Collection<IAction> createSiblingActions = Collections.emptyList();

    /**
     * This is the menu manager into which menu contribution items should be
     * added for sibling creation actions.
     */
    protected IMenuManager createSiblingMenuManager;

    /**
     * This creates an instance of the contributor.
     */
    public ActionBarContributor()
    {
      super(ADDITIONS_LAST_STYLE);
    }

    /**
     * This adds to the menu bar a menu for editor actions, duplicating
     * the menu contribution made in the plugin.xml, so that the new menu is
     * accessible for modification in code.  Also, sub-menus are created for
     * the addition and removal of child and sibling creation items.
     */
    @Override
    public void contributeToMenu(IMenuManager menuManager)
    {
      super.contributeToMenu(menuManager);

      // duplicate the menu contribution in the plugin.xml
      IMenuManager submenuManager = new MenuManager(XSDEditorPlugin.INSTANCE.getString("_UI_XSDEditor_menu"), "org.eclipse.xsdMenuID");
      menuManager.insertAfter("additions", submenuManager);
      submenuManager.add(new Separator("settings"));
      submenuManager.add(new Separator("actions"));
      submenuManager.add(new Separator("additions"));
      submenuManager.add(new Separator("additions-end"));

      // prepare for child and sibling creation item addition/removal
      createChildMenuManager = new MenuManager(XSDEditorPlugin.INSTANCE.getString("_UI_CreateChild_menu_item"));
      createSiblingMenuManager = new MenuManager(XSDEditorPlugin.INSTANCE.getString("_UI_CreateSibling_menu_item"));
      submenuManager.insertBefore("additions", new Separator("actions"));
      submenuManager.insertBefore("additions", createChildMenuManager);
      submenuManager.insertBefore("additions", createSiblingMenuManager);

      // Force an update because Eclipse hides empty menus now.
      //
      submenuManager.addMenuListener
        (new IMenuListener()
         {
           public void menuAboutToShow(IMenuManager menuManager)
           {
             menuManager.updateAll(true);
           }
         });

      validateAutomaticallyAction.setChecked(true);
      submenuManager.insertAfter("additions-end", validateAutomaticallyAction);
      addGlobalActions(submenuManager);
    }

    /**
     * This adds Separators to the tool bar.
     */
    @Override
    public void contributeToToolBar(IToolBarManager toolBarManager)
    {
      toolBarManager.add(new Separator("xsd-settings"));
      toolBarManager.add(new Separator("xsd-additions"));
    }

    /**
     * When the active editor changes, this remembers the change, and
     * registers with it as a selection provider.
     */
    @Override
    public void setActiveEditor(IEditorPart part)
    {
      super.setActiveEditor(part);
      activeEditorPart = part;

      // switch to the new selection provider
      if (selectionProvider != null)
      {
        selectionProvider.removeSelectionChangedListener(this);
      }
      selectionProvider = part.getSite().getSelectionProvider();
      selectionProvider.addSelectionChangedListener(this);

      // fake a selection changed event to update the menus
      if (selectionProvider.getSelection() != null)
        selectionChanged(new SelectionChangedEvent(selectionProvider, selectionProvider.getSelection()));

      if (part instanceof XSDEditor)
      {
        validateAutomaticallyAction.setChecked(((XSDEditor)part).isValidateAutomatically());
      }
    }

    /**
     * This implements {@link ISelectionChangedListener}, handling
     * SelectionChangedEvents by querying for the children and siblings that
     * can be added to the selected object and updating the menus
     * accordingly.
     */
    public void selectionChanged(SelectionChangedEvent event)
    {
      // remove any menu items for old selection
      if (createChildMenuManager != null)
      {
        depopulateManager(createChildMenuManager, createChildActions);
      }
      if (createSiblingMenuManager != null)
      {
        depopulateManager(createSiblingMenuManager, createSiblingActions);
      }

      // query new selection for appropriate new child/sibling descriptors...
      Collection<?> newChildDescriptors = Collections.emptyList();
      Collection<?> newSiblingDescriptors = Collections.emptyList();
      ISelection sel = event.getSelection();

      if (sel instanceof IStructuredSelection
          && ((IStructuredSelection) sel).size() == 1)
      {
        Object object = ((IStructuredSelection) sel).getFirstElement();
        EditingDomain domain =
          ((IEditingDomainProvider) activeEditorPart).getEditingDomain();

        newChildDescriptors = domain.getNewChildDescriptors(object, null);
        newSiblingDescriptors = domain.getNewChildDescriptors(domain.getParent(object), object);
      }

      // generate actions for selection, populate and redraw menu
      createChildActions = generateCreateChildActions(newChildDescriptors, sel);
      createSiblingActions = generateCreateSiblingActions(newSiblingDescriptors, sel);

      if (createChildMenuManager != null)
      {
        populateManager(createChildMenuManager, createChildActions, null);
        createChildMenuManager.update(true);
      }
      if (createSiblingMenuManager != null)
      {
        populateManager(createSiblingMenuManager, createSiblingActions, null);
        createSiblingMenuManager.update(true);
      }
    }

    /**
     * This generates a {@link CreateChildAction} for each object in
     * <code>descriptors</code>, and returns the collection of these actions.
     */
    protected Collection<IAction> generateCreateChildActions(Collection<?> descriptors, ISelection selection)
    {
      Collection<IAction> actions = new LinkedList<IAction>();
      for (Object descriptor : descriptors)
      {
        actions.add(new CreateChildAction(activeEditorPart, selection, descriptor));
      }
      return actions;
    }

    /**
     * This generates a {@link CreateSiblingAction} for each object in
     * <code>descriptors</code>, and returns the collection of these actions.
     */
    protected Collection<IAction> generateCreateSiblingActions(Collection<?> descriptors, ISelection selection)
    {
      Collection<IAction> actions = new LinkedList<IAction>();
      for (Object descriptor : descriptors)
      {
        actions.add(new CreateSiblingAction(activeEditorPart, selection, descriptor));
      }
      return actions;
    }

    /**
     * This populates the specified IContributionManager with
     * ActionContributionItems based on the IActions contained in
     * the actions collection, by inserting them before the specified
     * contribution item ID.  If ID is null, they are simply added.
     */
    protected void populateManager(IContributionManager manager, Collection<IAction> actions,  String ID)
    {
      for (IAction action : actions)
      {
        if (ID != null)
        {
          manager.insertBefore(ID, action);
        }
        else
        {
          manager.add(action);
        }
      }
    }

    /**
     * This removes from the specified IContributionManager all
     * ActionContributionItems based on the IActions contained in the
     * actions collection.
     */
    protected void depopulateManager(IContributionManager manager, Collection<IAction> actions)
    {
      IContributionItem[] item = manager.getItems();
      for (int i = 0; i < item.length; i++)
      {
        // look into SubContributionItems
        IContributionItem curItem = item[i];
        while (curItem instanceof SubContributionItem)
        {
          curItem = ((SubContributionItem) curItem).getInnerItem();
        }

        // delete ActionContributionItems with matching action
        if (curItem instanceof ActionContributionItem)
        {
          IAction action = ((ActionContributionItem) curItem).getAction();
          if (actions.contains(action))
          {
            manager.remove(curItem);
          }
        }
      }
    }

    /**
     * This populates the pop-up menu before it appears.
     */
    @Override
    public void menuAboutToShow(IMenuManager menuManager)
    {
      super.menuAboutToShow(menuManager);

      MenuManager submenuManager = new MenuManager(XSDEditorPlugin.INSTANCE.getString("_UI_CreateChild_menu_item"));
      populateManager(submenuManager, createChildActions, null);
      menuManager.insertBefore("edit", submenuManager);
 
      submenuManager = new MenuManager(XSDEditorPlugin.INSTANCE.getString("_UI_CreateSibling_menu_item"));
      populateManager(submenuManager, createSiblingActions, null);
      menuManager.insertBefore("edit", submenuManager);
    }

    /**
     * This inserts global actions before the "additions-end" separator.
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

  /**
   * This returns the viewer as required by the {@link IViewerProvider} interface.
   */
  public Viewer getViewer()
  {
    return currentViewer;
  }

  /**
   * This creates a context menu for the viewer and adds a listener as well registering the menu for extension.
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
   */
  @Override
  public void createPages()
  {
    createSourcePage();
    createSemanticsPage();
    createSyntaxPage();

    setActivePage(0);
    setCurrentViewer((Viewer)sourceViewer);
  }

  protected void createResource(String uri)
  {
    extendedCreateResource(uri);
  }

  protected void standardCreateResource(String uri)
  {

    // Load the resource through the editing domain.
    // This will creat a context and associate it with the resource set.
    //
    XSDResourceImpl xsdResource = (XSDResourceImpl)editingDomain.loadResource(uri);
    xsdSchema = xsdResource.getSchema();
  }

  protected void extendedCreateResource(String uri)
  {
    editingDomain.getResourceSet().getLoadOptions().put(XSDResourceImpl.XSD_TRACK_LOCATION, Boolean.TRUE);
    try
    {
      XSDResourceImpl xsdResource = (XSDResourceImpl)editingDomain.getResourceSet().createResource(URI.createURI(uri));
      xsdResource.load(editingDomain.getResourceSet().getLoadOptions());
      xsdSchema = xsdResource.getSchema();
    }
    catch (Exception exception)
    {
      XSDEditorPlugin.INSTANCE.log(exception);
    }
  }

  protected void createModel()
  {
    // Do the work within an operation because this is a long running activity that modifies the workbench.
    //
    WorkspaceModifyOperation operation =
      new WorkspaceModifyOperation()
      {
        // This is the method that gets invoked when the operation runs.
        //
        @Override
        protected void execute(IProgressMonitor progressMonitor) throws CoreException
        {
          try
          {
            progressMonitor.beginTask("", 12);

            editingDomain.getResourceSet().getLoadOptions().put(XSDResourceImpl.XSD_PROGRESS_MONITOR, progressMonitor);
            createResource(EditUIUtil.getURI(getEditorInput()).toString());
            editingDomain.getResourceSet().getLoadOptions().remove(XSDResourceImpl.XSD_PROGRESS_MONITOR);

            progressMonitor.worked(1);
            progressMonitor.subTask(XSDEditorPlugin.INSTANCE.getString("_UI_Validating_message"));
            if (xsdSchema.getDiagnostics().isEmpty())
            {
              xsdSchema.validate();
            }

            if (determineEncoding() != null && getEditorInput() instanceof IFileEditorInput)
            {
              ((IFileEditorInput)getEditorInput()).getFile().setCharset(determineEncoding(), new SubProgressMonitor(progressMonitor, 2));
            }

            progressMonitor.worked(1);
            progressMonitor.subTask(XSDEditorPlugin.INSTANCE.getString("_UI_ReportingErrors_message"));
          }
          finally
          {
            progressMonitor.done();
          }
        }
      };

    try
    {
      // This runs the operation, and shows progress.
      // (It appears to be a bad thing to fork this onto another thread.)
      //
      new ProgressMonitorDialog(getSite().getShell()).run(false, false, operation);
    }
    catch (Exception exception)
    {
      XSDEditorPlugin.INSTANCE.log(exception);
    }
  }

  protected void handleSourceCaretPosition()
  {
    int offset = sourceViewer.getTextWidget().getCaretOffset();
    Element element = xsdSchema.getElement();
    if (element != null)
    {
      IDocument document = sourceViewer.getDocument();
      int line = 0;
      int lineOffset = 0;
      try
      {
        line = document.getLineOfOffset(offset);
        lineOffset = document.getLineOffset(line);
      }
      catch (BadLocationException exception)
      {
        // Ignore
      }
      int column = offset - lineOffset;
      // System.out.println("[" + line + "," + column + "]");

      Element bestElement = findBestElement(element, line + 1, column + 1);
      if (bestElement != null)
      {
        handleSelectedNodes(Collections.singleton(bestElement));
      }
    }
  }

  public Element findBestElement(Element element, int line, int column)
  {
    int startLine = XSDParser.getStartLine(element);
    int startColumn = XSDParser.getStartColumn(element);
    int endLine = XSDParser.getEndLine(element);
    int endColumn = XSDParser.getEndColumn(element);

    Element candidate = null;
    if ((line == startLine ? column >= startColumn : line > startLine)  &&
          (line == endLine ? column <= endColumn : line < endLine))
    {
      candidate = element;
      for (Node child = element.getFirstChild(); child != null; child = child.getNextSibling())
      {
        if (child.getNodeType() == Node.ELEMENT_NODE)
        {
          Element childElement = (Element)child;
          Element betterCandidate = findBestElement(childElement, line, column);
          if (betterCandidate != null)
          {
            candidate = betterCandidate;
            break;
          }
        }
      }
    }
    return candidate;
  }


  public void handleSelectedNodes(Collection<? extends Node> nodes)
  {
    Collection<Object> selection = new ArrayList<Object>();
    for (Node node : nodes)
    {
      XSDConcreteComponent bestXSDConcreteComponent = xsdSchema.getCorrespondingComponent(node);
      if (bestXSDConcreteComponent != null)
      {
        boolean add = true;
        for (XSDConcreteComponent parent = bestXSDConcreteComponent;
             parent != null;
             parent = parent.getContainer())
        {
          if (selection.contains(parent))
          {
            add = false;
            break;
          }
        }
        if (add)
        {
          XSDConcreteComponent container = bestXSDConcreteComponent.getContainer();
          if (container instanceof XSDParticle || container instanceof XSDAttributeUse)
          {
            bestXSDConcreteComponent = container;
          }
          selection.add(bestXSDConcreteComponent);
        }
      }
    }
    if (!selection.isEmpty())
    {
      ISelection newSelection = new StructuredSelection(selection.toArray());
      if (contentOutlineViewer != null)
      {
        contentOutlineViewer.setSelection(newSelection, true);
      }
      setSelection(newSelection);
      handleContentOutlineSelectionForTextEditor(newSelection, false);
    }
  }

  protected void handleDocumentChange()
  {
    try
    {
      XSDParser xsdParser = new XSDParser(null);
      String documentContent = sourceViewer.getDocument().get();
      xsdParser.parseString(documentContent);
      xsdSchema.clearDiagnostics();
      xsdParser.setSchema(xsdSchema);
      if (validateAutomatically)
      {
        xsdSchema.validate();
        handleDiagnostics(null);
      }
    }
    catch (Exception exception)
    {
      XSDEditorPlugin.INSTANCE.log(exception);
    }
  }

  protected void createSourcePage()
  {
    try
    {
      // Create the SED Editor.
      //
      textEditor =
        new TextEditor()
        {
          @Override
          public ISourceViewer createSourceViewer(Composite parent, IVerticalRuler ruler, int styles)
          {
            final ISourceViewer result = super.createSourceViewer(parent, ruler, styles);
            result.getTextWidget().addMouseListener
              (new MouseAdapter()
               {
                 @Override
                public void mouseDown(MouseEvent event)
                 {
                   handleSourceCaretPosition();
                 }
               });
            result.getTextWidget().addKeyListener
              (new KeyAdapter()
               {
                 @Override
                public void keyPressed(KeyEvent event)
                 {
                   switch (event.keyCode)
                   {
                     case SWT.ARROW_UP:
                     case SWT.ARROW_DOWN:
                     case SWT.ARROW_LEFT:
                     case SWT.ARROW_RIGHT:
                     case SWT.PAGE_UP:
                     case SWT.PAGE_DOWN:
                     {
                       handleSourceCaretPosition();
                       break;
                     }
                   }
                 }
               });
            sourceViewer = result;
            return result;
          }
        };

      createModel();

      int pageIndex = addPage(textEditor, getEditorInput());

      handleDiagnostics(null);

      setPageText(pageIndex, XSDEditorPlugin.INSTANCE.getString("_UI_Source_title"));

      IDocument document = textEditor.getDocumentProvider().getDocument(textEditor.getEditorInput());

      document.addDocumentListener
        (new IDocumentListener()
         {
           protected Timer timer = new Timer();
           protected TimerTask timerTask;

           public void documentAboutToBeChanged(DocumentEvent documentEvent)
           {
             // Ingore
           }

           public void documentChanged(final DocumentEvent documentEvent)
           {
             try
             {
               // This is need for the Properties view.
               //
               // setSelection(StructuredSelection.EMPTY);

               if (timerTask != null)
               {
                 timerTask.cancel();
               }

               if (handledStructuredModelChange)
               {
                 handledStructuredModelChange = false;
                 handleDocumentChange();
               }
               else
               {
                 timerTask =
                   new TimerTask()
                   {
                     @Override
                    public void run()
                     {
                       getSite().getShell().getDisplay().asyncExec
                         (new Runnable()
                          {
                            public void run()
                            {
                              handleDocumentChange();
                            }
                          });
                     }
                   };

                 timer.schedule(timerTask, 1000);
               }
             }
             catch (Exception exception)
             {
               XSDEditorPlugin.INSTANCE.log(exception);
             }
           }
         });
    }
    catch (Exception exception)
    {
      XSDEditorPlugin.INSTANCE.log(exception);
    }
  }

  protected void createSemanticsPage()
  {
    // Create a page for the selection tree view.
    //
    {
      ViewerPane viewerPane =
        new ViewerPane(getSite().getPage(), XSDEditor.this)
        {
          @Override
          public Viewer createViewer(Composite composite)
          {
            Tree tree = new Tree(composite, SWT.MULTI);
            TreeViewer newTreeViewer = new TreeViewer(tree);
            return newTreeViewer;
          }
          @Override
          public void requestActivation()
          {
            super.requestActivation();
            setCurrentViewerPane(this);
          }
        };
      viewerPane.createControl(getContainer());

      semanticSelectionViewer = (TreeViewer)viewerPane.getViewer();
      semanticSelectionViewer.setContentProvider(new AdapterFactoryContentProvider(semanticAdapterFactory));
      semanticSelectionViewer.setLabelProvider(new AdapterFactoryLabelProvider(semanticAdapterFactory));
      semanticSelectionViewer.setAutoExpandLevel(2);

      semanticSelectionViewer.addSelectionChangedListener
        (new ISelectionChangedListener()
         {
           // This just notifies those things that are affected by the section.
           //
           public void selectionChanged(SelectionChangedEvent selectionChangedEvent)
           {
             if (currentViewer == semanticSelectionViewer && contentOutlineViewer != null)
             {
               contentOutlineViewer.setSelection(selectionChangedEvent.getSelection(), true);
             }
           }
         });

      semanticSelectionViewer.setInput(new ItemProvider(Collections.singleton(xsdSchema)));
      viewerPane.setTitle(xsdSchema);

      new AdapterFactoryTreeEditor(semanticSelectionViewer.getTree(), semanticAdapterFactory);

      createContextMenuFor(semanticSelectionViewer);
      int pageIndex = addPage(viewerPane.getControl());
      setPageText(pageIndex, XSDEditorPlugin.INSTANCE.getString("_UI_Semantics_title"));
    }
  }

  protected void createSyntaxPage()
  {
    // Create a page for the selection tree view.
    //
    {
      ViewerPane viewerPane =
        new ViewerPane(getSite().getPage(), XSDEditor.this)
        {
          @Override
          public Viewer createViewer(Composite composite)
          {
            Tree tree = new Tree(composite, SWT.MULTI);
            TreeViewer newTreeViewer = new TreeViewer(tree);
            return newTreeViewer;
          }
          @Override
          public void requestActivation()
          {
            super.requestActivation();
            setCurrentViewerPane(this);
          }
        };
      viewerPane.createControl(getContainer());

      syntacticSelectionViewer = (TreeViewer)viewerPane.getViewer();
      syntacticSelectionViewer.setContentProvider(new AdapterFactoryContentProvider(syntacticAdapterFactory));
      syntacticSelectionViewer.setLabelProvider(new AdapterFactoryLabelProvider(syntacticAdapterFactory));
      syntacticSelectionViewer.setAutoExpandLevel(2);

      syntacticSelectionViewer.setInput(new ItemProvider(Collections.singleton(xsdSchema)));
      viewerPane.setTitle(xsdSchema);

      new AdapterFactoryTreeEditor(syntacticSelectionViewer.getTree(), syntacticAdapterFactory);

      createContextMenuFor(syntacticSelectionViewer);
      int pageIndex = addPage(viewerPane.getControl());
      setPageText(pageIndex, XSDEditorPlugin.INSTANCE.getString("_UI_Syntax_title"));
    }
  }

  protected void initializeMarkerPosition(IMarker marker, XSDDiagnostic xsdDiagnostic) throws CoreException
  {
    Node node = xsdDiagnostic.getNode();
    if (node != null && node.getNodeType() == Node.ATTRIBUTE_NODE)
    {
      node = ((Attr)node).getOwnerElement();
    }
    if (node != null && /* !xsdDiagnostic.isSetLine() && */ XSDParser.getUserData(node) != null)
    {
      int startLine = XSDParser.getStartLine(node) - 1;
      int startColumn = XSDParser.getStartColumn(node);
      int endLine = XSDParser.getEndLine(node) - 1;
      int endColumn = XSDParser.getEndColumn(node);

      marker.setAttribute(IMarker.LINE_NUMBER, startLine);

      try
      {
        IDocument document = textEditor.getDocumentProvider().getDocument(textEditor.getEditorInput());
        marker.setAttribute(IMarker.CHAR_START, document.getLineOffset(startLine) + startColumn - 1);
        marker.setAttribute(IMarker.CHAR_END, document.getLineOffset(endLine) + endColumn - 1);
      }
      catch (BadLocationException exception)
      {
        // Ignore
      }
    }
    else
    {
      marker.setAttribute(IMarker.LINE_NUMBER, xsdDiagnostic.getLine());
    }
  }

  protected void handleDiagnostics(IProgressMonitor progressMonitor)
  {
    if (progressMonitor == null)
    {
      // Do the work within an operation because this is a long running activity that modifies the workbench.
      //
      IWorkspaceRunnable operation =
        new IWorkspaceRunnable()
        {
          // This is the method that gets invoked when the operation runs.
          //
          public void run(IProgressMonitor localProgressMonitor) throws CoreException
          {
            handleDiagnostics(localProgressMonitor);
          }
        };

      try
      {
        ResourcesPlugin.getWorkspace().run(operation, new NullProgressMonitor());
        // getSite().getWorkbenchWindow().run(false, false, operation);
      }
      catch (Exception exception)
      {
        XSDEditorPlugin.INSTANCE.log(exception);
      }
    }
    else
    {
      XSDConcreteComponent newSelection = null;
      if (getEditorInput() instanceof IFileEditorInput)
      {
        try
        {
          // I assume that the input is a file object.
          //
          IFileEditorInput modelFile = (IFileEditorInput)getEditorInput();
          IFile file = modelFile.getFile();
  
          IMarker[] markers = file.findMarkers(IMarker.PROBLEM, true, IResource.DEPTH_ZERO);
          Collection<IMarker> deletableMarkers = new ArrayList<IMarker>(Arrays.asList(markers));
  
          if (validateAutomatically)
          {
            for (XSDDiagnostic xsdDiagnostic : xsdSchema.getAllDiagnostics())
            {
              String uriReferencePath = xsdSchema.eResource().getURIFragment(xsdDiagnostic);
  
              IMarker marker = null;
              for (int i = 0; i < markers.length; ++i)
              {
                if (markers[i].getAttribute(XSDDiagnostic.URI_FRAGMENT_ATTRIBUTE, "").equals(uriReferencePath))
                {
                  marker = markers[i];
                  deletableMarkers.remove(marker);
                  break;
                }
              }
  
              if (marker == null)
              {
                marker = file.createMarker(XSDDiagnostic.MARKER);
                marker.setAttribute(XSDDiagnostic.URI_FRAGMENT_ATTRIBUTE, uriReferencePath);
              }
  
              initializeMarkerPosition(marker, xsdDiagnostic);
  
              marker.setAttribute(IMarker.MESSAGE, xsdDiagnostic.getMessage());
  
              switch (xsdDiagnostic.getSeverity().getValue())
              {
                case XSDDiagnosticSeverity.FATAL:
                case XSDDiagnosticSeverity.ERROR:
                {
                  if (newSelection == null)
                  {
                    newSelection = xsdDiagnostic.getPrimaryComponent();
                  }
                  marker.setAttribute(IMarker.SEVERITY, IMarker.SEVERITY_ERROR);
                  break;
                }
                case XSDDiagnosticSeverity.WARNING:
                {
                  marker.setAttribute(IMarker.SEVERITY, IMarker.SEVERITY_WARNING);
                  break;
                }
                case XSDDiagnosticSeverity.INFORMATION:
                {
                  marker.setAttribute(IMarker.SEVERITY, IMarker.SEVERITY_INFO);
                  break;
                }
              }
            }
          }
  
          for (IMarker marker : deletableMarkers)
          {
            marker.delete();
          }
        }
        catch (Exception exception)
        {
          XSDEditorPlugin.INSTANCE.log(exception);
        }
      }

      // This will refresh the status.
      //
      if (editorSelection != null)
      {
        setSelection(editorSelection);
      }
      // This is the startup case.
      //
      else if (newSelection != null)
      {
        final IStructuredSelection errorSelection = new StructuredSelection(newSelection);
        getSite().getShell().getDisplay().asyncExec
          (new Runnable()
           {
             public void run()
             {
               if (contentOutlineViewer != null)
               {
                 contentOutlineViewer.setSelection(errorSelection, true);
               }
               if (sourceViewer != null)
               {
                 handleContentOutlineSelectionForTextEditor(errorSelection, true);
               }
               setSelection(errorSelection);
               handleSourceCaretPosition();
             }
           });
      }
    }
  }

  /**
   * This is used to track the active viewer.
   */
  @Override
  protected void pageChange(int pageIndex)
  {
    super.pageChange(pageIndex);

    if (pageIndex == 0)
    {
      setCurrentViewerPane(null);
      setCurrentViewer((Viewer)sourceViewer);
    }

    // This is a temporary workaround... EATM
    //
    Control control = getControl(pageIndex);
    if (control != null)
    {
      control.setVisible(true);
      control.setFocus();
    }

    if (contentOutlinePage != null)
    {
      handleContentOutlineSelection(contentOutlinePage.getSelection());
    }
  }

  /**
   * This is how the framework determines which interfaces we implement.
   */
  @SuppressWarnings("unchecked")
  @Override
  public Object getAdapter(Class key)
  {
    if (key.equals(IContentOutlinePage.class))
    {
      return getContentOutlinePage();
    }
    else if (key.equals(IPropertySheetPage.class))
    {
      return getPropertySheetPage();
    }
    else
    {
      return textEditor.getAdapter(key);
    }
  }

  /**
   * This is a utility function to resolve a component.
   */
  public static XSDConcreteComponent getResolvedObject(XSDConcreteComponent xsdConcreteComponent)
  {
    XSDConcreteComponent result =
      (XSDConcreteComponent)new XSDSwitch<Object>()
      {
        @Override
        public Object caseXSDAttributeUse(XSDAttributeUse xsdAttributeUse)
        {
          return xsdAttributeUse.getAttributeDeclaration().getResolvedAttributeDeclaration();
        }
        @Override
        public Object caseXSDAttributeDeclaration(XSDAttributeDeclaration xsdAttributeDeclaration)
        {
          return xsdAttributeDeclaration.getResolvedAttributeDeclaration();
        }
        @Override
        public Object caseXSDAttributeGroupDefinition(XSDAttributeGroupDefinition xsdAttributeGroupDefinition)
        {
          return xsdAttributeGroupDefinition.getResolvedAttributeGroupDefinition();
        }
        @Override
        public Object caseXSDElementDeclaration(XSDElementDeclaration xsdElementDeclaration)
        {
          return xsdElementDeclaration.getResolvedElementDeclaration();
        }
        @Override
        public Object caseXSDModelGroupDefinition(XSDModelGroupDefinition xsdModelGroupDefinition)
        {
          return xsdModelGroupDefinition.getResolvedModelGroupDefinition();
        }
        @Override
        public Object caseXSDParticle(XSDParticle xsdParticle)
        {
          Object resolvedObject = getResolvedObject(xsdParticle.getContent());
          if (resolvedObject instanceof XSDModelGroup)
          {
            return xsdParticle;
          }
          else
          {
            return resolvedObject;
          }
        }
      }.doSwitch(xsdConcreteComponent);

    return result == null ? xsdConcreteComponent : result;
  }

  /**
   * This accesses a cached version of the content outliner.
   */
  public IContentOutlinePage getContentOutlinePage()
  {
    if (contentOutlinePage == null)
    {
      // The content outline is just a tree.
      //
      class MyContentOutlinePage extends ContentOutlinePage
      {
        @Override
        public void createControl(Composite parent)
        {
          super.createControl(parent);
          contentOutlineViewer = getTreeViewer();
          contentOutlineViewer.addSelectionChangedListener(this);
          contentOutlineViewer.setAutoExpandLevel(2);

          selectNextDiagnosticsAction = new SelectDiagnosticAction(true, contentOutlineViewer);
          selectPreviousDiagnosticsAction = new SelectDiagnosticAction(false, contentOutlineViewer);

          selectNextUseAction = new SelectUseAction(true, contentOutlineViewer);
          selectPreviousUseAction = new SelectUseAction(false, contentOutlineViewer);

          contentOutlineViewer.getTree().addMouseListener
            (new MouseAdapter()
             {
               @Override
              public void mouseDoubleClick(MouseEvent event)
               {
                 // Do fancy navigation selections when double clicking.
                 //
                 IStructuredSelection selection = (IStructuredSelection)contentOutlineViewer.getSelection();
                 for (Object object :  selection.toList())
                 {
                   XSDConcreteComponent xsdConcreteComponent = (XSDConcreteComponent)object;
                   Object resolvedObject = getResolvedObject(xsdConcreteComponent);
                   if (xsdConcreteComponent != resolvedObject)
                   {
                     contentOutlineViewer.setSelection(new StructuredSelection(new Object [] {resolvedObject}), true);
                     break;
                   }
                   else if (xsdConcreteComponent instanceof XSDAttributeDeclaration)
                   {
                     XSDAttributeDeclaration xsdAttributeDeclaration = (XSDAttributeDeclaration)xsdConcreteComponent;
                     XSDSimpleTypeDefinition typeDefinition = xsdAttributeDeclaration.getTypeDefinition();
                     if (typeDefinition != null && typeDefinition.getSchema() == xsdAttributeDeclaration.getSchema())
                     {
                       contentOutlineViewer.setSelection(new StructuredSelection(new Object [] {typeDefinition}), true);
                       break;
                     }
                   }
                   else if (xsdConcreteComponent instanceof XSDElementDeclaration)
                   {
                     XSDElementDeclaration xsdElementDeclaration = (XSDElementDeclaration)xsdConcreteComponent;
                     XSDTypeDefinition typeDefinition = xsdElementDeclaration.getTypeDefinition();
                     if (typeDefinition != null && typeDefinition.getSchema() == xsdElementDeclaration.getSchema())
                     {
                       contentOutlineViewer.setSelection(new StructuredSelection(new Object [] {typeDefinition}), true);
                       break;
                     }
                   }
                   else if (xsdConcreteComponent instanceof XSDSimpleTypeDefinition)
                   {
                     XSDSimpleTypeDefinition xsdSimpleTypeDefinition = (XSDSimpleTypeDefinition)xsdConcreteComponent;
                     XSDSimpleTypeDefinition baseTypeDefinition = xsdSimpleTypeDefinition.getBaseTypeDefinition();
                     if (baseTypeDefinition != null && baseTypeDefinition.getSchema() == xsdSimpleTypeDefinition.getSchema())
                     {
                       contentOutlineViewer.setSelection(new StructuredSelection(new Object [] {baseTypeDefinition}), true);
                       break;
                     }
                     XSDSimpleTypeDefinition itemTypeDefinition = xsdSimpleTypeDefinition.getItemTypeDefinition();
                     if (itemTypeDefinition != null && itemTypeDefinition.getSchema() == xsdSimpleTypeDefinition.getSchema())
                     {
                       contentOutlineViewer.setSelection(new StructuredSelection(new Object [] {itemTypeDefinition}), true);
                       break;
                     }
                     List<?> memberTypeDefinitions = xsdSimpleTypeDefinition.getMemberTypeDefinitions();
                     if (!memberTypeDefinitions.isEmpty())
                     {
                       contentOutlineViewer.setSelection(new StructuredSelection(memberTypeDefinitions.toArray()), true);
                       break;
                     }
                   }
                   else if (xsdConcreteComponent instanceof XSDComplexTypeDefinition)
                   {
                     XSDComplexTypeDefinition xsdComplexTypeDefinition = (XSDComplexTypeDefinition)xsdConcreteComponent;
                     XSDTypeDefinition baseTypeDefinition = xsdComplexTypeDefinition.getBaseTypeDefinition();
                     if (baseTypeDefinition != null && baseTypeDefinition.getSchema() == xsdComplexTypeDefinition.getSchema())
                     {
                       contentOutlineViewer.setSelection(new StructuredSelection(new Object [] {baseTypeDefinition}), true);
                       break;
                     }
                   }
                 }
               }
             });

          // Set up the tree viewer.
          //
          contentOutlineViewer.setContentProvider(new AdapterFactoryContentProvider(syntacticAdapterFactory));
          contentOutlineViewer.setLabelProvider(new AdapterFactoryLabelProvider(syntacticAdapterFactory));
          contentOutlineViewer.setInput(new ItemProvider(Collections.singleton(xsdSchema)));

          // Make sure our popups work.
          //
          createContextMenuFor(contentOutlineViewer);

          // Select the root object in the view.
          //
          ArrayList<Object> selection = new ArrayList<Object>();
          selection.add(xsdSchema);
          contentOutlineViewer.setSelection(new StructuredSelection(selection), true);

          // Listen to selection so that we can handle it is a special way.
          //
          this.addSelectionChangedListener
            (new ISelectionChangedListener()
             {
               // This ensures that we handle selections correctly.
               //
               public void selectionChanged(SelectionChangedEvent event)
               {
                 ISelection s = event.getSelection();
                 if (contentOutlineViewer == currentViewer)
                 {
                   handleContentOutlineSelection(s);
                 }
                 selectNextDiagnosticsAction.setCurrentObjects(((IStructuredSelection)s).toList());
                 selectPreviousDiagnosticsAction.setCurrentObjects(((IStructuredSelection)s).toList());

                 selectNextUseAction.setCurrentObjects(((IStructuredSelection)s).toList());
                 selectPreviousUseAction.setCurrentObjects(((IStructuredSelection)s).toList());
               }
             });
        }

        @Override
        public void setActionBars(IActionBars actionBars)
        {
          super.setActionBars(actionBars);

          contentOutlineStatusLineManager = actionBars.getStatusLineManager();

          actionBars.getToolBarManager().add(selectNextUseAction);
          actionBars.getToolBarManager().add(selectPreviousUseAction);

          actionBars.getToolBarManager().add(selectNextDiagnosticsAction);
          actionBars.getToolBarManager().add(selectPreviousDiagnosticsAction);


          actionBars.getMenuManager().add(selectNextDiagnosticsAction);
          actionBars.getMenuManager().add(selectPreviousDiagnosticsAction);

          actionBars.getMenuManager().add(selectNextUseAction);
          actionBars.getMenuManager().add(selectPreviousUseAction);

          getActionBarContributor().shareGlobalActions(this, actionBars);
        }
      }

      contentOutlinePage = new MyContentOutlinePage();

      // Listen to selection so that we can handle it is a special way.
      //
      contentOutlinePage.addSelectionChangedListener
        (new ISelectionChangedListener()
         {
           // This ensures that we handle selections correctly.
           //
           public void selectionChanged(SelectionChangedEvent event)
           {
             if (contentOutlineViewer == currentViewer)
             {
               handleContentOutlineSelection(event.getSelection());
             }
           }
         });
    }

    return contentOutlinePage;
  }

  /**
   * This accesses a cached version of the property sheet.
   */
  public IPropertySheetPage getPropertySheetPage()
  {
    if (propertySheetPage == null)
    {
      propertySheetPage =
        new PropertySheetPage()
        {
          @Override
          public void makeContributions(IMenuManager menuManager, IToolBarManager toolBarManager, IStatusLineManager statusLineManager)
          {
            super.makeContributions(menuManager, toolBarManager, statusLineManager);
          }

          @Override
          public void setActionBars(IActionBars actionBars)
          {
            super.setActionBars(actionBars);
            getActionBarContributor().shareGlobalActions(this, actionBars);
          }
        };
      propertySheetPage.setPropertySourceProvider
        (new AdapterFactoryContentProvider(syntacticAdapterFactory)
         {
           @Override
          protected IPropertySource createPropertySource(Object object, IItemPropertySource itemPropertySource)
           {
             return
               new PropertySource(object, itemPropertySource)
               {
                 @Override
                protected IPropertyDescriptor createPropertyDescriptor(IItemPropertyDescriptor itemPropertyDescriptor)
                 {
                   return
                     new PropertyDescriptor(this.object, itemPropertyDescriptor)
                     {
                       @Override
                      public CellEditor createPropertyEditor(Composite composite)
                       {
                         if (!this.itemPropertyDescriptor.canSetProperty(this.object))
                         {
                           return null;
                         }

                         CellEditor result = null;

                         Object genericFeature = this.itemPropertyDescriptor.getFeature(this.object);
                         if (genericFeature instanceof EStructuralFeature)
                         {
                           EStructuralFeature feature = (EStructuralFeature)genericFeature;
                           EObject getEType = feature.getEType();
                           if (getEType == ecorePackage.getEBoolean())
                           {
                             Collection<?> choiceOfValues = this.itemPropertyDescriptor.getChoiceOfValues(this.object);
                             if (choiceOfValues != null)
                             {
                               result =
                                 new ExtendedComboBoxCellEditor
                                   (composite, new ArrayList<Object>(choiceOfValues), getLabelProvider(), true);
                             }
                           }
                         }
                         if (result == null)
                         {
                           result = super.createPropertyEditor(composite);
                         }
                         return result;
                       }
                     };
                 }
               };
           }
         });
    }

    return propertySheetPage;
  }

  /**
   * This deals with how we want selection in the outliner to affect the other views.
   */
  public void handleContentOutlineSelection(ISelection selection)
  {
    if ((currentViewerPane != null || getActivePage() == 0) && !selection.isEmpty() && selection instanceof IStructuredSelection)
    {
      if (getActivePage() == 0)
      {
        handleContentOutlineSelectionForTextEditor(selection, true);
      }
      else if (currentViewerPane.getViewer() == syntacticSelectionViewer)
      {
        // Set the selection to the widget.
        //
        syntacticSelectionViewer.setSelection(selection);
      }
      else if (currentViewerPane.getViewer() == semanticSelectionViewer)
      {
        ArrayList<Object> selectionList = new ArrayList<Object>();
        for (Object object : ((IStructuredSelection)selection).toList())
        {
          selectionList.add(getResolvedObject((XSDConcreteComponent)object));
        }

        // Set the selection to the widget.
        //
        semanticSelectionViewer.setSelection(new StructuredSelection(selectionList));
      }
    }
  }

  /**
   * This deals with how we want selection in the outliner to affect the text editor.
   */
  public void handleContentOutlineSelectionForTextEditor(ISelection selection, boolean reveal)
  {
    XSDConcreteComponent xsdConcreteComponent = (XSDConcreteComponent)((IStructuredSelection)selection).iterator().next();
    if (xsdConcreteComponent instanceof XSDParticle)
    {
      XSDParticle xsdParticle = (XSDParticle)xsdConcreteComponent;
      XSDConcreteComponent content = xsdParticle.getContent();
      if (content != null)
      {
        xsdConcreteComponent = content;
      }
    }

    Element element = xsdConcreteComponent.getElement();
    if (element != null)
    {
      try
      {
        IDocument document = textEditor.getDocumentProvider().getDocument(textEditor.getEditorInput());
        int startLine = XSDParser.getStartLine(element);
        int startColumn = XSDParser.getStartColumn(element);
        int endLine = XSDParser.getEndLine(element);
        int endColumn = XSDParser.getEndColumn(element);

        int startOffset = document.getLineOffset(startLine - 1);
        startOffset += startColumn - 1;
        int endOffset = document.getLineOffset(endLine - 1);
        endOffset += endColumn - 1;
        if (startLine == endLine)
        {
          textEditor.setHighlightRange(startOffset, endOffset - startOffset, false);
          if (reveal)
          {
            textEditor.selectAndReveal(startOffset, endOffset - startOffset);
          }
        }
        else
        {
          textEditor.setHighlightRange(startOffset, endOffset - startOffset, reveal);
        }
      }
      catch (Exception exception)
      {
        XSDEditorPlugin.INSTANCE.log(exception);
      }
    }
  }

  /**
   * This is for implementing {@link IEditorPart} and simply tests the command stack.
   */
  @Override
  public boolean isDirty()
  {
    return ((BasicCommandStack)editingDomain.getCommandStack()).isSaveNeeded() || textEditor != null && textEditor.isDirty();
  }

  /**
   * This is for implementing {@link IEditorPart} and simply saves the model file.
   */
  @Override
  public void doSave(IProgressMonitor progressMonitor)
  {
    // Do the work within an operation because this is a long running activity that modifies the workbench.
    //
    WorkspaceModifyOperation operation =
      new WorkspaceModifyOperation()
      {
        // This is the method that gets invoked when the operation runs.
        //
        @Override
        protected void execute(IProgressMonitor monitor) throws CoreException
        {
          try
          {
            // Save the resource to the file system.
            //
            xsdSchema.eResource().save(Collections.EMPTY_MAP);
          }
          catch (Exception exception)
          {
            XSDEditorPlugin.INSTANCE.log(exception);
          }
        }
      };

    try
    {
      // This runs the operation, and shows progress.
      // (It appears to be a bad thing to fork this onto another thread.)
      //
      new ProgressMonitorDialog(getSite().getShell()).run(false, false, operation);

      // Refresh the necessary state.
      //
      ((BasicCommandStack)editingDomain.getCommandStack()).saveIsDone();
      textEditor.doRevertToSaved();
      firePropertyChange(IEditorPart.PROP_DIRTY);
    }
    catch (Exception exception)
    {
      // Something went wrong that shouldn't.
      //
      XSDEditorPlugin.INSTANCE.log(exception);
    }
  }

  /**
   * This always returns false because it is not current supported.
   */
  @Override
  public boolean isSaveAsAllowed()
  {
    return true;
  }

  /**
   * This also changes the editor's input.
   */
  @Override
  public void doSaveAs()
  {
    SaveAsDialog saveAsDialog = new SaveAsDialog(getSite().getShell());
    saveAsDialog.open();
    IPath path = saveAsDialog.getResult();
    if (path != null)
    {
      IFile file = ResourcesPlugin.getWorkspace().getRoot().getFile(path);
      if (file != null)
      {
        xsdSchema.eResource().setURI(URI.createPlatformResourceURI(file.getFullPath().toString(), true));
        IFileEditorInput modelFile = new FileEditorInput(file);
        setInput(modelFile);
        setPartName(file.getName());
        doSave(getActionBars().getStatusLineManager().getProgressMonitor());
      }
    }
  }

  public void gotoMarker(IMarker marker)
  {
    try
    {
/*
      if (marker.getType().equals(XSDDiagnostic.MARKER) && xsdSchema != null)
      {
        XSDDiagnostic xsdDiagnostic =
          xsdSchema.getDiagnosticForURIReferencePath(marker.getAttribute(XSDDiagnostic.URI_FRAGMENT_ATTRIBUTE, "/0/"));
      }
*/
      setActivePage(0);
      ((IGotoMarker)textEditor.getAdapter(IGotoMarker.class)).gotoMarker(marker);
    }
    catch (Exception exception)
    {
      XSDEditorPlugin.INSTANCE.log(exception);
    }
  }

  /**
   * This is called during startup.
   */
  @Override
  public void init(IEditorSite site, IEditorInput editorInput) throws PartInitException
  {
    setSite(site);
    setInput(editorInput);
    setPartName(editorInput.getName());
    site.setSelectionProvider(new MultiPageSelectionProvider(this));
    site.getPage().addPartListener(partListener);
  }

  @Override
  public void setFocus()
  {
    getControl(getActivePage()).setFocus();
  }

  /**
   * This implements {@link org.eclipse.jface.viewers.ISelectionProvider}.
   */
  public void addSelectionChangedListener(ISelectionChangedListener listener)
  {
    selectionChangedListeners.add(listener);
  }

  /**
   * This implements {@link org.eclipse.jface.viewers.ISelectionProvider}.
   */
  public void removeSelectionChangedListener(ISelectionChangedListener listener)
  {
    selectionChangedListeners.remove(listener);
  }

  /**
   * This implements {@link org.eclipse.jface.viewers.ISelectionProvider} to return this editor's overall selection.
   */
  public ISelection getSelection()
  {
    return editorSelection;
  }

  /**
   * This implements {@link org.eclipse.jface.viewers.ISelectionProvider} to set this editor's overall selection.
   * Calling this result will notify the listeners.
   */
  public void setSelection(ISelection selection)
  {
    editorSelection = selection;

    SelectionChangedEvent selectionChangedEvent = new SelectionChangedEvent(this, selection);
    ((MultiPageSelectionProvider)getSite().getSelectionProvider()).fireSelectionChanged(selectionChangedEvent);
    for (ISelectionChangedListener listener : selectionChangedListeners)
    {
      listener.selectionChanged(selectionChangedEvent);
    }

    setStatusLineManager(selection);
  }

  /**
   *  This shows the selection on the status line.
   */
  public void setStatusLineManager(ISelection selection)
  {
    IStatusLineManager statusLineManager = currentViewer != null && currentViewer == contentOutlineViewer ?
      contentOutlineStatusLineManager : getActionBars().getStatusLineManager();

    if (statusLineManager != null)
    {
      if (selection instanceof IStructuredSelection)
      {
        Collection<?> collection = ((IStructuredSelection)selection).toList();
        switch (collection.size())
        {
          case 0:
          {
            statusLineManager.setMessage(XSDEditorPlugin.INSTANCE.getString("_UI_NoObjectSelected"));
            break;
          }
          case 1:
          {
            Object object = collection.iterator().next();
            String text = new AdapterFactoryItemDelegator(syntacticAdapterFactory).getText(object);
            text = XSDEditorPlugin.INSTANCE.getString("_UI_SingleObjectSelected", new Object [] { text });
            if (object instanceof XSDConcreteComponent)
            {
              XSDConcreteComponent xsdConcreteComponent = (XSDConcreteComponent)object;
              if (!xsdConcreteComponent.getDiagnostics().isEmpty())
              {
                text = (xsdConcreteComponent.getDiagnostics().get(0)).getMessage();
              }
            }

            statusLineManager.setMessage(text);
            break;
          }
          default:
          {
            statusLineManager.setMessage
              (XSDEditorPlugin.INSTANCE.getString("_UI_MultiObjectSelected", new Object [] { Integer.toString(collection.size()) }));
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
   * This implements {@link org.eclipse.jface.action.IMenuListener} to help fill the context menus with contributions from the Edit menu.
   */
  public void menuAboutToShow(IMenuManager menuManager)
  {
    ((IMenuListener)getEditorSite().getActionBarContributor()).menuAboutToShow(menuManager);
  }

  /**
   * This convenience method provides typed access to the contributor.
   */
  public EditingDomainActionBarContributor getActionBarContributor()
  {
    return (EditingDomainActionBarContributor)getEditorSite().getActionBarContributor();
  }

  /**
   * This convenience method provides access to the actionbars.
   */
  public IActionBars getActionBars()
  {
    return getActionBarContributor().getActionBars();
  }

  /**
   * This is called when the editor is disposed.
   */
  @Override
  public void dispose()
  {
    super.dispose();
    getSite().getPage().removePartListener(partListener);
    ((IDisposable)semanticAdapterFactory).dispose();
    ((IDisposable)syntacticAdapterFactory).dispose();
  }

  protected boolean validateAutomatically = true;
  public void setValidateAutomatically(boolean validateAutomatically)
  {
    this.validateAutomatically = validateAutomatically;
    if (validateAutomatically)
    {
      handleDocumentChange();
    }
    else
    {
      handleDiagnostics(null);
    }
  }

  public boolean isValidateAutomatically()
  {
    return validateAutomatically;
  }

  /**
   * This is the base action for the outline actions.
   */
  class SelectObjectAction extends Action
  {
    protected Collection<?> objectsToSelect;
    protected StructuredViewer structuredViewer;

    public SelectObjectAction(StructuredViewer structuredViewer, String text, ImageDescriptor imageDescriptor)
    {
      super(text, imageDescriptor);
      this.structuredViewer = structuredViewer;
      setEnabled(false);
    }

    public void setObjectToSelect(Object objectToSelect)
    {
      setObjectsToSelect
        (objectToSelect != null ?
           Collections.singleton(objectToSelect) :
           Collections.EMPTY_LIST);
    }

    public void setObjectsToSelect(Collection<?> objectsToSelect)
    {
      this.objectsToSelect = new ArrayList<Object>(objectsToSelect);
      setEnabled(!objectsToSelect.isEmpty());
    }

    @Override
    public void run()
    {
      structuredViewer.setSelection(new StructuredSelection(objectsToSelect.toArray()), true);
    }
  }

  /**
   * This is used to implement the select next/previous unresolved component action.
   */
  class SelectDiagnosticAction extends SelectObjectAction
  {
    boolean isForward;
    public SelectDiagnosticAction(boolean isForward, StructuredViewer structuredViewer)
    {
      super
        (structuredViewer,
         isForward ?  "Select &Next Diagnosed Object" : "Select &Previous Diagnosed Object",
         ExtendedImageRegistry.INSTANCE.getImageDescriptor
           (XSDEditorPlugin.INSTANCE.getImage
             (isForward ? "full/elcl16/SelectNextDiagnosticObject" : "full/elcl16/SelectPreviousDiagnosticObject")));
      this.isForward = isForward;

      setHoverImageDescriptor
        (ExtendedImageRegistry.INSTANCE.getImageDescriptor
          (XSDEditorPlugin.INSTANCE.getImage
             (isForward ? "full/clcl16/SelectNextDiagnosticObject" : "full/clcl16/SelectPreviousDiagnosticObject")));

      setDisabledImageDescriptor
        (ExtendedImageRegistry.INSTANCE.getImageDescriptor
          (XSDEditorPlugin.INSTANCE.getImage
             (isForward ? "full/dlcl16/SelectNextDiagnosticObject" : "full/dlcl16/SelectPreviousDiagnosticObject")));
    }

    public void updateAction()
    {
      setCurrentObjects(((IStructuredSelection)structuredViewer.getSelection()).toList());
    }

    public void setCurrentObjects(List<?> objects)
    {
      XSDConcreteComponent result = null;

      boolean isStarted = false;
      for (TreeIterator<?> tree = editingDomain.treeIterator(xsdSchema); tree.hasNext(); )
      {
        XSDConcreteComponent xsdConcreteComponent = (XSDConcreteComponent)tree.next();
        if (!isForward && objects.contains(xsdConcreteComponent))
        {
          break;
        }
        else if (isStarted || !isForward)
        {
          if (!xsdConcreteComponent.getDiagnostics().isEmpty() ||
                 xsdConcreteComponent instanceof XSDParticle &&
                   !((XSDParticle)xsdConcreteComponent).getContent().getDiagnostics().isEmpty() ||
                 xsdConcreteComponent instanceof XSDAttributeUse &&
                   !((XSDAttributeUse)xsdConcreteComponent).getContent().getDiagnostics().isEmpty())
          {
            if (isStarted)
            {
              result = xsdConcreteComponent;
              break;
            }
            else
            {
              result = xsdConcreteComponent;
            }
          }
        }
        else if (objects.contains(xsdConcreteComponent))
        {
          isStarted = true;
        }
      }

      setObjectToSelect(result);
    }
  }

  /**
   * This is used to implement the select next/previous component use action.
   */
  class SelectUseAction extends SelectObjectAction
  {
    boolean isForward;
    public SelectUseAction(boolean isForward, StructuredViewer structuredViewer)
    {
      super
        (structuredViewer,
         isForward ?  "Select &Next Use" : "Select &Previous Use",
         ExtendedImageRegistry.INSTANCE.getImageDescriptor
           (XSDEditorPlugin.INSTANCE.getImage
             (isForward ? "full/elcl16/SelectNextUseObject" : "full/elcl16/SelectPreviousUseObject")));
      this.isForward = isForward;

      setHoverImageDescriptor
        (ExtendedImageRegistry.INSTANCE.getImageDescriptor
          (XSDEditorPlugin.INSTANCE.getImage
             (isForward ? "full/clcl16/SelectNextUseObject" : "full/clcl16/SelectPreviousUseObject")));
      setDisabledImageDescriptor
        (ExtendedImageRegistry.INSTANCE.getImageDescriptor
          (XSDEditorPlugin.INSTANCE.getImage
             (isForward ? "full/dlcl16/SelectNextUseObject" : "full/dlcl16/SelectPreviousUseObject")));
    }

    public void updateAction()
    {
      setCurrentObjects(((IStructuredSelection)structuredViewer.getSelection()).toList());
    }

    public void setCurrentObjects(List<?> objects)
    {
      XSDConcreteComponent result = null;

      final List<Object> resolvedObjects = new ArrayList<Object>();
      for (Object object : objects)
      {
        XSDConcreteComponent xsdConcreteComponent = (XSDConcreteComponent)object;
        XSDConcreteComponent resolvedObject = getResolvedObject(xsdConcreteComponent);
        if (resolvedObject != this)
        {
          resolvedObjects.add(resolvedObject);
        }
      }

      boolean isStarted = false;
      for (TreeIterator<?> tree = editingDomain.treeIterator(xsdSchema); tree.hasNext(); )
      {
        XSDConcreteComponent xsdConcreteComponent = (XSDConcreteComponent)tree.next();
        if (!isForward && objects.contains(xsdConcreteComponent))
        {
          break;
        }
        else if (isStarted || !isForward)
        {
          XSDConcreteComponent resolvedObject = getResolvedObject(xsdConcreteComponent);
          if (resolvedObjects.contains(resolvedObject))
          {
            if (isStarted)
            {
              result = xsdConcreteComponent;
              break;
            }
            else
            {
              result = xsdConcreteComponent;
            }
          }
        }
        else if (objects.contains(xsdConcreteComponent))
        {
          isStarted = true;
        }
      }

      setObjectToSelect(result);
    }
  }

  public static class GenericXMLResourceFactoryImpl extends XMLResourceFactoryImpl
  {
    public GenericXMLResourceFactoryImpl()
    {
      super();
    }

    @Override
    public Resource createResource(URI uri)
    {
      XMLResource result = new GenericXMLResourceImpl(uri);
      
      result.getDefaultSaveOptions().put(XMLResource.OPTION_EXTENDED_META_DATA, Boolean.TRUE);
      result.getDefaultLoadOptions().put(XMLResource.OPTION_EXTENDED_META_DATA, Boolean.TRUE);

      result.getDefaultLoadOptions().put(XMLResource.OPTION_USE_LEXICAL_HANDLER, Boolean.TRUE);

      result.getDefaultSaveOptions().put(XMLResource.OPTION_LINE_WIDTH, new Integer(80));

      result.getDefaultSaveOptions().put(XMLResource.OPTION_SCHEMA_LOCATION, Boolean.TRUE);
      return result;
    }
  }

  public static class GenericXMLResourceImpl extends XMLResourceImpl
  {
    protected XSDEcoreBuilder xsdEcoreBuilder;

    public GenericXMLResourceImpl(URI uri)
    {
      super(uri);
    }

    @Override
    protected XMLLoad createXMLLoad()
    {
      return 
        new XMLLoadImpl(createXMLHelper())
        {
          @Override
          protected DefaultHandler makeDefaultHandler()
          {
            return 
              new SAXXMLHandler(resource, helper, options)
              {
                @Override
               protected void handleTopLocations(String prefix, String name)
                {
                  if (urisToLocations != null)
                  {
                    xsdEcoreBuilder = new XSDEcoreBuilder(extendedMetaData);
                    Collection<Resource> resources = xsdEcoreBuilder.generateResources(urisToLocations.values());
                    resource.getResourceSet().getResources().addAll(resources);
                  }

                  // Ensure that anything can be handled, even if it's not recognized.
                  //
                  String namespaceURI = helper.getURI(prefix);
                  if (extendedMetaData.getPackage(namespaceURI) == null)
                  {
                    extendedMetaData.demandFeature(namespaceURI, name, true);
                  }
                }
              };
          }
        };
    }

/*
    public void doLoad(InputStream inputStream, final Map op) throws IOException
    {
      XMLLoad xmlStart =
        new XMLLoadImpl(createXMLHelper())
        {
          protected DefaultHandler makeDefaultHandler()
          {
            SAXXMLHandler saxXMLHandler =
              new SAXXMLHandler(resource, helper, op)
              {
                protected MyStack elementDeclarations = new MyStack();
                protected MyStack dfaStates = new MyStack();

                protected void createTopObject(String prefix, String name)
                {
                  String namespaceURI = helper.getURI(prefix);
                  for (int i = 0, size = attribs.getLength(); i < size; ++i)
                  {
                    String attributeName = attribs.getQName(i);
                    int index = attributeName.indexOf(":");
                    String attributeNamespaceURI = null;
                    String attributeLocalName = attributeName;
                    if (index != -1)
                    {
                      attributeNamespaceURI = helper.getURI(attributeName.substring(0, index));
                      attributeLocalName = attributeName.substring(index + 1);
                    }

                    if (XSDConstants.SCHEMA_INSTANCE_URI_2001.equals(attributeNamespaceURI) &&
                         (namespaceURI == null ? "noNamespaceSchemaLocation" : "schemaLocation").equals(attributeLocalName))
                    {
                      String schemaLocationHint = null;

                      if (namespaceURI == null)
                      {
                        schemaLocationHint = attribs.getValue(i);
                      }
                      else
                      {
                        for (StringTokenizer stringTokenizer = new StringTokenizer(attribs.getValue(i));
                             stringTokenizer.hasMoreTokens(); )
                        {
                          String namespaceURIHint = stringTokenizer.nextToken();
                          if (stringTokenizer.hasMoreTokens())
                          {
                            if (namespaceURIHint.equals(namespaceURI))
                            {
                              schemaLocationHint = stringTokenizer.nextToken();
                              break;
                            }
                            else
                            {
                              stringTokenizer.nextToken();
                            }
                          }
                          else
                          {
                            break;
                          }
                        }
                      }

                      if (schemaLocationHint != null)
                      {
                        URI uri = URI.createURI(schemaLocationHint);
                        if (resolve && uri.isRelative() && uri.hasRelativePath())
                        {
                          uri = uri.resolve(resourceURI);
                        }

                        xsdEcoreBuilder = new XSDEcoreBuilder();
                        Collection resources = xsdEcoreBuilder.generateResources(uri);
                        resource.getResourceSet().getResources().addAll(resources);
                      }
                    }
                  }

                  if (xsdEcoreBuilder == null)
                  {
                    error(new XMIException("Cannot resolve schema location",  getLocation(), getLineNumber(), getColumnNumber()));
                  }
                  else
                  {
                    XSDElementDeclaration xsdElementDeclaration =
                      xsdEcoreBuilder.getSchema().resolveElementDeclaration(namespaceURI, name);
                    EClass eClass = (EClass)xsdEcoreBuilder.getXSDComponentToEModelElementMap().get(xsdElementDeclaration);
                    if (eClass != null)
                    {
                      processTopObject(eClass.getEPackage().getEFactoryInstance().create(eClass));
                      elementDeclarations.push(xsdElementDeclaration);
                      XSDParticle xsdParticle = xsdElementDeclaration.getTypeDefinition().getComplexType();
                      if (xsdParticle != null)
                      {
                        dfaStates.push(xsdParticle.getDFA().getInitialState());
                      }
                      else
                      {
                        dfaStates.push(null);
                      }
                    }
                    else
                    {
                      error(new XMIException("Cannot resolve EClass ",  getLocation(), getLineNumber(), getColumnNumber()));
                    }
                  }
                }

                protected void processElement(String name, String prefix, String localName)
                {
                  if (isError())
                  {
                    types.push(ERROR_TYPE);
                  }
                  else
                  {
                    if (objects.isEmpty())
                    {
                      createTopObject(prefix, localName);

                    }
                    else
                    {
                      EObject peekObject = (EObject)objects.peek();
                      XSDParticle.DFA.State state = (XSDParticle.DFA.State)dfaStates.peek();
                      if (state == null)
                      {
                        error(new XMIException("Cannot contain content ",  getLocation(), getLineNumber(), getColumnNumber()));
                      }
                      else
                      {
                        XSDParticle.DFA.Transition transition = state.accept(helper.getURI(prefix), localName);
                        if (transition == null)
                        {
                          error(new XMIException("Not expecting this element ",  getLocation(), getLineNumber(), getColumnNumber()));
                        }
                        else
                        {
                          dfaStates.set(dfaStates.size() - 1, transition.getState());

                          XSDParticle transitionXSDParticle = transition.getParticle();
                          XSDTerm xsdTerm = transitionXSDParticle.getTerm();
                          XSDElementDeclaration xsdElementDeclaration = null;
                          if (xsdTerm instanceof XSDElementDeclaration)
                          {
                            xsdElementDeclaration =  (XSDElementDeclaration)xsdTerm;
                          }
                          else
                          {
                            xsdElementDeclaration = xsdEcoreBuilder.getSchema().resolveElementDeclaration(helper.getURI(prefix), name);
                          }

                          EClass eClass = (EClass)xsdEcoreBuilder.getXSDComponentToEModelElementMap().get(xsdElementDeclaration);
                          if (eClass != null)
                          {
                            EObject eObject = eClass.getEPackage().getEFactoryInstance().create(eClass);
                            ((EList)peekObject.eGet(peekObject.eClass().getEStructuralFeature("contents"))).add(eObject);

                            processObject(eObject);
                            elementDeclarations.push(xsdElementDeclaration);
                            XSDParticle xsdParticle = xsdElementDeclaration.getTypeDefinition().getComplexType();
                            if (xsdParticle != null)
                            {
                              dfaStates.push(xsdParticle.getDFA().getInitialState());
                            }
                            else
                            {
                              dfaStates.push(null);
                              XSDSimpleTypeDefinition xsdSimpleTypeDefinition = xsdElementDeclaration.getTypeDefinition().getSimpleType();
                              if (xsdSimpleTypeDefinition != null)
                              {
                                EStructuralFeature valueFeature = eClass.getEStructuralFeature("value");
                                if (valueFeature != null)
                                {
                                  text = new StringBuffer();
                                  types.set(types.size() - 1, valueFeature);
                                }
                              }
                            }
                          }
                          else
                          {
                            error(new XMIException("Cannot resolve EClass ",  getLocation(), getLineNumber(), getColumnNumber()));
                          }
                        }
                      }
                    }
                  }
                }

                public void endElement(String uri, String localName, String name)
                {
                  EObject topObject = (EObject)objects.pop();
                  elements.pop();
                  Object type = types.pop();

                  if (text != null)
                  {
                    EAttribute eAttribute = (EAttribute)type;
                    EDataType eDataType = eAttribute.getEAttributeType();
                    Object value = eDataType.getEPackage().getEFactoryInstance().createFromString(eDataType, text.toString());
                    topObject.eSet(eAttribute, value);
                    text = null;
                  }

                  XSDParticle.DFA.State state = (XSDParticle.DFA.State)dfaStates.pop();
                  if (state != null && !state.isAccepting())
                  {
                    error(new XMIException("Need more content ",  getLocation(), getLineNumber(), getColumnNumber()));
                  }
                  elementDeclarations.pop();
                }
              };

            return new SAXWrapper(saxXMLHandler);
          }
        };
      xmlStart.load(this, inputStream, op);
    }
*/
  }

  public static class GenericXMLLoadAction extends org.eclipse.ui.actions.ActionDelegate implements org.eclipse.ui.IActionDelegate
  {
    protected IFile file;
    protected IContainer container;

    public GenericXMLLoadAction()
    {
      super();
    }

    @Override
    public void run(IAction action)
    {
      if (file != null)
      {
        execute(file);
      }
      else 
      {
        execute(container);
      }
    }

    public void execute(IContainer container) 
    {
      try
      {
        IResource [] contents = container.members();
        for (int i = 0; i < contents.length; ++i)
        {
          IResource resource = contents[i];
          if (resource.getType() == IResource.FILE)
          {
            if ("xml".equals(resource.getLocation().getFileExtension()) && 
                  resource.getName().indexOf(".xml") == resource.getName().lastIndexOf(".xml"))
            {
              execute((IFile)resource);
            }
          }
          else
          {
            execute((IContainer)resource);
          }
        }
      }
      catch (CoreException exception)
      {
        XSDEditorPlugin.INSTANCE.log(exception);
        exception.printStackTrace();
      }

    }

    public void execute(IFile file)
    {
      System.err.println("Processing: " + file.getFullPath());
      ResourceSet resourceSet = new ResourceSetImpl();
      resourceSet.getResourceFactoryRegistry().getExtensionToFactoryMap().put("xml", new GenericXMLResourceFactoryImpl());

      Resource resource = resourceSet.getResource(URI.createPlatformResourceURI(file.getFullPath().toString(), true), true);
      resource.setURI(URI.createPlatformResourceURI(file.getFullPath().toString() + ".save.xml", true));
      try
      {
        resource.save(Collections.EMPTY_MAP);
      }
      catch (IOException exception)
      {
        XSDEditorPlugin.INSTANCE.log(exception);
        exception.printStackTrace();
      }

      List<EPackage> ePackages = new ArrayList<EPackage>();
      for (Resource otherResource : resourceSet.getResources())
      {
        for (Object object : otherResource.getContents())
        {
          if (object instanceof EPackage)
          {
            ePackages.add((EPackage)object);
          }
        }
      }

      Resource ePackagesResource = resourceSet.createResource(URI.createPlatformResourceURI(file.getFullPath().toString() + ".ecore", true));
      ePackagesResource.getContents().addAll(EcoreUtil.copyAll(ePackages));
      try
      {
        ePackagesResource.save(Collections.EMPTY_MAP);
      }
      catch (IOException exception)
      {
        XSDEditorPlugin.INSTANCE.log(exception);
        exception.printStackTrace();
      }

      try
      {
        XSDParser xsdParser = new XSDParser(null);
        xsdParser.parse(URI.createPlatformResourceURI(file.getFullPath().toString(), true).toString());
        Document document = xsdParser.getDocument();
        OutputStream outputStream = 
          resourceSet.getURIConverter().createOutputStream
            (URI.createPlatformResourceURI(file.getFullPath().toString() + ".format.xml", true));
        XSDResourceImpl.serialize(outputStream, document);
        outputStream.close();
      }
      catch (IOException exception)
      {
        XSDEditorPlugin.INSTANCE.log(exception);
        exception.printStackTrace();
      }

      try
      {
        XSDParser xsdParser = new XSDParser(null);
        xsdParser.parse(URI.createPlatformResourceURI(file.getFullPath().toString() + ".save.xml", true).toString());
        Document document = xsdParser.getDocument();
        OutputStream outputStream = 
          resourceSet.getURIConverter().createOutputStream
            (URI.createPlatformResourceURI(file.getFullPath().toString() + ".save.format.xml", true));
        XSDResourceImpl.serialize(outputStream, document);
        outputStream.close();
      }
      catch (IOException exception)
      {
        XSDEditorPlugin.INSTANCE.log(exception);
        exception.printStackTrace();
      }
    }

    @Override
    public void selectionChanged(IAction action, ISelection selection)
    {
      if (selection instanceof IStructuredSelection)
      {
        Object object = ((IStructuredSelection)selection).getFirstElement();
        if (object instanceof IFile)
        {
          file = (IFile)object;
          action.setEnabled(true);
          return;
        }
        else if (object instanceof IContainer)
        {
          container = (IContainer)object;
          action.setEnabled(true);
          return;
        }
      }
      file = null;
      container = null;
      action.setEnabled(false);
    }
  }
}
