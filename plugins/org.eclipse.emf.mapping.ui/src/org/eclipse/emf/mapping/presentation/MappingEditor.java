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
 * $Id: MappingEditor.java,v 1.3 2004/05/16 16:52:12 emerks Exp $
 */
package org.eclipse.emf.mapping.presentation;


import java.net.URL;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.EventObject;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IMarker;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Platform;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.action.IMenuListener;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.IStatusLineManager;
import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.action.Separator;
import org.eclipse.jface.dialogs.ProgressMonitorDialog;
import org.eclipse.jface.viewers.ColumnWeightData;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.ISelectionProvider;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.StructuredSelection;
import org.eclipse.jface.viewers.StructuredViewer;
import org.eclipse.jface.viewers.TableLayout;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.CTabFolder;
import org.eclipse.swt.custom.SashForm;
import org.eclipse.swt.custom.TableTree;
import org.eclipse.swt.custom.TableTreeItem;
import org.eclipse.swt.dnd.DND;
import org.eclipse.swt.dnd.DropTargetEvent;
import org.eclipse.swt.dnd.Transfer;
import org.eclipse.swt.events.ControlAdapter;
import org.eclipse.swt.events.ControlEvent;
import org.eclipse.swt.events.ControlListener;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.Tree;
import org.eclipse.swt.widgets.Widget;
import org.eclipse.ui.IActionBars;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IEditorSite;
import org.eclipse.ui.IFileEditorInput;
import org.eclipse.ui.IPartListener;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.actions.WorkspaceModifyOperation;
import org.eclipse.ui.dialogs.SaveAsDialog;
import org.eclipse.ui.part.FileEditorInput;
import org.eclipse.ui.part.MultiPageEditorPart;
import org.eclipse.ui.views.contentoutline.ContentOutline;
import org.eclipse.ui.views.contentoutline.ContentOutlinePage;
import org.eclipse.ui.views.contentoutline.IContentOutlinePage;
import org.eclipse.ui.views.properties.IPropertySheetPage;
import org.eclipse.ui.views.properties.PropertySheet;
import org.eclipse.ui.views.properties.PropertySheetPage;

import org.eclipse.emf.common.command.BasicCommandStack;
import org.eclipse.emf.common.command.Command;
import org.eclipse.emf.common.command.CommandStack;
import org.eclipse.emf.common.command.CommandStackListener;
import org.eclipse.emf.common.command.CommandWrapper;
import org.eclipse.emf.common.notify.AdapterFactory;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.ui.ViewerPane;
import org.eclipse.emf.common.ui.viewer.ExtendedTableTreeViewer;
import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EStructuralFeature;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.edit.command.RemoveCommand;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.emf.edit.domain.IEditingDomainProvider;
import org.eclipse.emf.edit.provider.AdapterFactoryTreeIterator;
import org.eclipse.emf.edit.provider.DecoratorAdapterFactory;
import org.eclipse.emf.edit.provider.IChangeNotifier;
import org.eclipse.emf.edit.provider.IDisposable;
import org.eclipse.emf.edit.provider.IEditingDomainItemProvider;
import org.eclipse.emf.edit.provider.IItemLabelProvider;
import org.eclipse.emf.edit.provider.IItemPropertySource;
import org.eclipse.emf.edit.provider.IItemProviderDecorator;
import org.eclipse.emf.edit.provider.INotifyChangedListener;
import org.eclipse.emf.edit.provider.IStructuredItemContentProvider;
import org.eclipse.emf.edit.provider.ITableItemLabelProvider;
import org.eclipse.emf.edit.provider.ITreeItemContentProvider;
import org.eclipse.emf.edit.provider.ItemProvider;
import org.eclipse.emf.edit.provider.ItemProviderDecorator;
import org.eclipse.emf.edit.ui.action.DelegatingCommandAction;
import org.eclipse.emf.edit.ui.action.EditingDomainActionBarContributor;
import org.eclipse.emf.edit.ui.celleditor.AdapterFactoryTableTreeEditor;
import org.eclipse.emf.edit.ui.dnd.EditingDomainViewerDropAdapter;
import org.eclipse.emf.edit.ui.dnd.LocalTransfer;
import org.eclipse.emf.edit.ui.dnd.ViewerDragAdapter;
import org.eclipse.emf.edit.ui.provider.AdapterFactoryContentProvider;
import org.eclipse.emf.edit.ui.provider.AdapterFactoryLabelProvider;
import org.eclipse.emf.edit.ui.provider.ExtendedImageRegistry;
import org.eclipse.emf.mapping.MappedObjectState;
import org.eclipse.emf.mapping.Mapping;
import org.eclipse.emf.mapping.MappingFactory;
import org.eclipse.emf.mapping.MappingPackage;
import org.eclipse.emf.mapping.MappingPlugin;
import org.eclipse.emf.mapping.MappingRoot;
import org.eclipse.emf.mapping.action.CreateMappingAction;
import org.eclipse.emf.mapping.action.CreateOneSidedMappingAction;
import org.eclipse.emf.mapping.action.NameMatchMappingAction;
import org.eclipse.emf.mapping.action.RemoveMappingAction;
import org.eclipse.emf.mapping.action.TypeMatchMappingAction;
import org.eclipse.emf.mapping.command.CreateMappingCommand;
import org.eclipse.emf.mapping.command.PersistentCommandStack;
import org.eclipse.emf.mapping.command.RemoveMappingCommand;
import org.eclipse.emf.mapping.command.RestoreInitialStateCommand;
import org.eclipse.emf.mapping.domain.AdapterFactoryMappingDomain;
import org.eclipse.emf.mapping.domain.MappingDomain;
import org.eclipse.emf.mapping.provider.MappedObjectItemProvider;
import org.eclipse.emf.mapping.provider.MappingItemProvider;


/**
 * This is an example of a model editor.
 */
public abstract class MappingEditor 
  extends MultiPageEditorPart 
  implements IEditingDomainProvider, ISelectionProvider, IMenuListener
{
  protected static final String DIVIDER = MappingPlugin.getPlugin().getString("_UI_Mapping_label_separator");
  protected static final String SEPARATOR = MappingPlugin.getPlugin().getString("_UI_Mapping_label_separator") + " ";

  protected String topLabel = MappingUIPlugin.getPlugin().getString("_UI_Top_label");

  public String getTopLabel()
  {
    return topLabel;
  }

  protected String bottomLabel = MappingUIPlugin.getPlugin().getString("_UI_Bottom_label");

  public String getBottomLabel()
  {
    return bottomLabel;
  }

  protected Image topImage = MappingUIPlugin.getPlugin().getImage("full/cview16/TopLogo");

  public Image getTopImage()
  {
    return topImage;
  }

  protected Image bottomImage = MappingUIPlugin.getPlugin().getImage("full/cview16/BottomLogo");

  public Image getBottomImage()
  {
    return bottomImage;
  }

  protected boolean isNotificationEnabled = true;

  public boolean isNotificationEnabled()
  {
    return isNotificationEnabled;
  }

  public void setNotificationEnabled(boolean isNotificationEnabled)
  {
    this.isNotificationEnabled = isNotificationEnabled;
    if (isNotificationEnabled)
    {
      if (leftSelectionViewer != null)
      {
        leftSelectionViewer.refresh();
        rightSelectionViewer.refresh();
        overviewViewer.refresh();
      }
      if (contentOutlineViewer != null)
      {
        contentOutlineViewer.refresh();
      }
      if (propertySheetPage != null)
      {
        propertySheetPage.refresh();
      }
    }
  }

  /**
   * This keeps track of the file that stores the model.
   */
  protected IFileEditorInput modelFile;

  /**
   * This keeps track of the root object of the model.
   */
  protected MappingRoot mappingRoot;

  /**
   * This keeps track of the editing domain that is used to track all changes to the model.
   */
  protected AdapterFactoryMappingDomain mappingDomain;

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

  protected Action contentOutlineFilterAction;

  /**
   * This is the property sheet page.
   */
  protected PropertySheetPage propertySheetPage;

  /**
   * This is the viewer that shadows the selection in the content outline.
   * The parent relation must be correctly defined for this to work.
   */
  protected SimpleMappedObjectViewer leftSelectionViewer;
  protected MyDecoratorAdapterFactory leftSelectionFactory;
  protected SimpleMappedObjectViewer rightSelectionViewer;
  protected MyDecoratorAdapterFactory rightSelectionFactory;
  protected ViewerPane overviewViewerPane;
  protected OverviewViewer overviewViewer;
  protected OverviewDecoratorAdapterFactory overviewFactory;

  /**
   * This keeps track of the current viewer pane of the page book.
   */
  protected ViewerPane currentViewerPane;

  /**
   * This keeps track of the active content viewer, which may be either one of the viewers in the pages or the content outline viewer.
   */
  protected Viewer currentViewer;

  /**
   * This keeps track of the content viewer, which had something dropped on it.
   */
  protected Viewer dropViewer;

  /**
   * This listens to which ever viewer is active.
   */
  protected ISelectionChangedListener selectionChangedListener;

  /**
   * This keeps track of all the {@link org.eclipse.jface.viewers.ISelectionChangedListener}s that are listening to this editor.
   */
  protected Collection selectionChangedListeners = new ArrayList();

  /**
   * This keeps track of the selection of the editor as a whole.
   */
  protected ISelection editorSelection;

  /**
   * This listens for when the outline becomes active
   */
  protected IPartListener partListener =
    new IPartListener()
    {
      public void partActivated(IWorkbenchPart p)
      {
        if (p instanceof ContentOutline)
        {
          if (((ContentOutline)p).getCurrentPage() == contentOutlinePage)
          {
            getActionBarContributor().setActiveEditor(MappingEditor.this);
            setCurrentViewer(contentOutlineViewer);
          }
        }
        else if (p instanceof PropertySheet)
        {
          if (((PropertySheet)p).getCurrentPage() == propertySheetPage)
          {
            getActionBarContributor().setActiveEditor(MappingEditor.this);
          }
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

  CommandStackListener commandStackListener =
    new CommandStackListener()
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
       
               leftSelectionViewer.updateActions();
               rightSelectionViewer.updateActions();
       
               if (propertySheetPage != null)
               {
                 propertySheetPage.refresh();
               }
             }
           });
      }
    };

  /**
   * This simply calls super; it is here only so that inner classes can call it.
   */
  protected void firePropertyChange(int type) 
  {
    // VAJ kludge
    //
    super.firePropertyChange(type);
  }

  /**
   * This creates a model editor.
   */
  public MappingEditor()
  {
    super();
  }

  protected abstract AdapterFactoryMappingDomain createMappingDomain();

  /**
   * This sets the selection into whichever viewer is active.
   */
  public void setSelectionToViewer(final Collection collection)
  {
    // Make sure it's okay.
    //
    if (collection != null && !collection.isEmpty())
    {
      // I don't know if we should be run this deferred 
      // because we might have to give the editor a chance to process the viewer update events 
      // and hence to update the views first.
      // 
      //
      Runnable runnable = 
       new Runnable()
        { 
          public void run()
          {
            Collection indirectMappedObjects = new ArrayList();
            Collection mappedInputs = new ArrayList();
            Collection mappedOutputs = new ArrayList();
            Collection mappings = new ArrayList();
            for (Iterator objects = collection.iterator(); objects.hasNext(); )
            {
              Object object = objects.next();
              if (object instanceof Mapping)
              {
                mappings.add(object);
              }
              else if (mappingDomain.getMappingRoot().isInputObject(object))
              {
                mappedInputs.add(object);
                indirectMappedObjects.add(object);
              }
              else if (mappingDomain.getMappingRoot().isOutputObject(object))
              {
                mappedOutputs.add(object);
                indirectMappedObjects.add(object);
              }
            }

            LOOP: for (Iterator i = mappings.iterator(); i.hasNext(); )
            {
              Mapping mapping = (Mapping)i.next(); 

              indirectMappedObjects.addAll(mapping.getMappedObjects());

              for (Mapping parent = mapping.getNestedIn(); parent != null; parent = parent.getNestedIn())
              {
                if (mappings.contains(parent))
                {
                  continue LOOP;
                }
              }
              
              mappedOutputs.addAll(mapping.getOutputs());
              mappedInputs.addAll(mapping.getInputs());
            }

            if (!indirectMappedObjects.isEmpty())
            {
              overviewViewer.setSelection(new StructuredSelection(indirectMappedObjects.toArray()), true);
            }

            if (!mappings.isEmpty() && contentOutlineViewer != null)
            {
              contentOutlineViewer.setSelection(new StructuredSelection(mappings.toArray()), true);
            }

            Viewer affectedViewer = dropViewer == null ? currentViewer : dropViewer;
            dropViewer = null;

            // Try to select the items in the current content viewer of the editor.
            //
            if (affectedViewer != null)
            {
              if (affectedViewer == leftSelectionViewer)
              {
                Collection topMappedObjects = mappingRoot.isTopToBottom() ? mappedInputs : mappedOutputs;
                if (!topMappedObjects.isEmpty())
                {
                  leftSelectionViewer.setSelection(new StructuredSelection(topMappedObjects.toArray()), true);
                }
              }
              else if (affectedViewer == rightSelectionViewer)
              {
                Collection bottomMappedObjects = !mappingRoot.isTopToBottom() ? mappedInputs : mappedOutputs;
                if (!bottomMappedObjects.isEmpty())
                {
                  rightSelectionViewer.setSelection(new StructuredSelection(bottomMappedObjects.toArray()), true);
                }
              }
              else if (!collection.isEmpty())
              {
                affectedViewer.setSelection(new StructuredSelection(collection.toArray()), true);
              }
            }
          }
        };
      runnable.run();
    }
    else
    {
      dropViewer = null;
    }
  }

  /**
   * This returns the editing domain as required by the {@link IEditingDomainProvider} interface.
   * This is important for implementing the static methods of 
   * {@link org.eclipse.emf.edit.domain.AdapterFactoryEditingDomain} and for supporting 
   * {@link org.eclipse.emf.edit.ui.action.CommandAction}.
   */
  public EditingDomain getEditingDomain()
  {
    return mappingDomain;
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
    setCurrentViewer(currentViewerPane.getViewer());
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
            // This just notifies those things that are affected by the selection.
            //
            public void selectionChanged(SelectionChangedEvent event)
            {
              setSelection(event.getSelection());
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

      // Set the editor's selection based on the current viewer's selection.
      //
      setSelection(currentViewer.getSelection());
    }
  }

  /**
   * This is the contributor for the mapping editor.
   */
  static public class ActionBarContributor extends EditingDomainActionBarContributor
  {
    protected DelegatingCommandAction removeMappingAction;
    protected DelegatingCommandAction createMappingAction;
    protected DelegatingCommandAction createOneSidedMappingAction;
    protected DelegatingCommandAction nameMatchMappingAction;
    protected DelegatingCommandAction typeMatchMappingAction;

    /**
     * This creates an instance the contributor.
     */
    public ActionBarContributor()
    {
      removeMappingAction = new DelegatingCommandAction(new RemoveMappingAction());
      createMappingAction = new DelegatingCommandAction(new CreateMappingAction());
      createOneSidedMappingAction = new DelegatingCommandAction(new CreateOneSidedMappingAction());
      nameMatchMappingAction = new DelegatingCommandAction(new NameMatchMappingAction());
      typeMatchMappingAction = new DelegatingCommandAction(new TypeMatchMappingAction());
    }

    public void setActiveEditor(IEditorPart part)
    {
      super.setActiveEditor(part);
      removeMappingAction.setActiveEditor(part);
      createMappingAction.setActiveEditor(part);
      createOneSidedMappingAction.setActiveEditor(part);
      nameMatchMappingAction.setActiveEditor(part);
      typeMatchMappingAction.setActiveEditor(part);
    }

/*
    public void contributeToMenu(IMenuManager menuManager)
    {
      super.contributeToMenu(menuManager);
    }
*/

    public void contributeToToolBar(IToolBarManager toolBarManager)
    {
      toolBarManager.add(new Separator("mapping-settings"));
      toolBarManager.add(new Separator("mapping-additions"));
      toolBarManager.add(new Separator("mapping-global-actions"));

      super.contributeToToolBar(toolBarManager);
    }
  }

  /**
   * This creates a context menu for the viewer and adds a listener as well registering the menu for extension.
   */
  protected void createContextMenuFor(final StructuredViewer structuredViewer)
  {
    MenuManager contextMenu = new MenuManager("#PopUp"); 
    contextMenu.add(new Separator("additions"));
    contextMenu.setRemoveAllWhenShown(true);
    contextMenu.addMenuListener(this);
    Menu menu= contextMenu.createContextMenu(structuredViewer.getControl());
    structuredViewer.getControl().setMenu(menu);
    getSite().registerContextMenu(contextMenu, structuredViewer);

    int dndOperations = DND.DROP_COPY | DND.DROP_MOVE | DND.DROP_LINK;
    Transfer[] transfers = new Transfer[] { LocalTransfer.getInstance() };
    structuredViewer.addDragSupport(dndOperations, transfers, new ViewerDragAdapter(structuredViewer));
    structuredViewer.addDropSupport
      (dndOperations, 
       transfers, 
       new EditingDomainViewerDropAdapter(mappingDomain, structuredViewer)
       {
         public void drop(DropTargetEvent event)
         {
           dropViewer = structuredViewer;
           super.drop(event);
         }
       });
  }

  protected class MyDecoratorAdapterFactory extends DecoratorAdapterFactory
  {
    protected Collection listeningItemProviderDecorators = new HashSet();
    protected Action action;
    protected Action multipleColumnsAction;
    protected boolean exclude;

    public MyDecoratorAdapterFactory(AdapterFactory adapterFactory, Action action, boolean exclude, Action multipleColumnsAction)
    {
      this(adapterFactory, action, exclude);
      this.multipleColumnsAction = multipleColumnsAction;
    }

    public MyDecoratorAdapterFactory(AdapterFactory adapterFactory, Action action, boolean exclude)
    {
      super(adapterFactory);
      this.action = action;
      this.exclude = exclude;
    }

    public boolean isFactoryForType(Object t)
    {
      return super.isFactoryForType(t) || t == ITableItemLabelProvider.class;

    }

    public Object adapt(Object o, Object t)
    {
      Object result = super.adapt(o, t == ITableItemLabelProvider.class ? IItemLabelProvider.class : t);

      if (result != null)
      {
        MappedObjectState mappedObjectState = mappingDomain.getMappingRoot().getMappedObjectState(o);
        if (mappedObjectState != null && listeningItemProviderDecorators.add(o))
        {
          ((MyItemProviderDecorator) result).setMappedObjectState(mappedObjectState);
        }
      }

      return result;
    }

    protected boolean doMappingRefresh(Object object)
    {
      return false;
    }

    protected void doMappedObjectRefresh(Object object)
    {
    }

    public List getPrimaryMappedObjects(Mapping mapping)
    {
      return Collections.EMPTY_LIST;
    }
  
    public List getSecondaryMappedObjects(Mapping mapping)
    {
      return Collections.EMPTY_LIST;
    }

    class MyItemProviderDecorator 
      extends
        ItemProviderDecorator
      implements
        IStructuredItemContentProvider,
        ITreeItemContentProvider,
        IItemPropertySource,
        IEditingDomainItemProvider,
        IItemLabelProvider,
        ITableItemLabelProvider
    {
      protected Collection mappedObjectStates = new ArrayList();

      public MyItemProviderDecorator(AdapterFactory adapterFactory)
      {
        super(adapterFactory);
      }

      public Collection getChildren(Object o)
      {
        if (action.isChecked())
        {
          Collection result = new ArrayList(super.getChildren(o));
          LOOP : for (Iterator results = result.iterator(); results.hasNext(); )
          {
            Object child = results.next();
            for (Iterator tree = new AdapterFactoryTreeIterator(getDecoratedAdapterFactory(), child, true); tree.hasNext(); )
            {
              Object descendant = tree.next();
              if (mappingDomain.getMappingRoot().getMappings(descendant).isEmpty() == exclude)
              {
                continue LOOP;
              }
            }
            results.remove();
          }
          return result;
        }
        else
        {
          return super.getChildren(o);
        }
      }

      public boolean hasChildren(Object o)
      {
        if (action.isChecked())
        {
          for (Iterator tree = new AdapterFactoryTreeIterator(getDecoratedAdapterFactory(), o, false); tree.hasNext(); )
          {
            Object descendant = tree.next();
            if (mappingDomain.getMappingRoot().getMappings(descendant).isEmpty() == exclude)
            {
              return true;
            }
          }
          return false;
        }
        else
        {
          return super.hasChildren(o);
        }
      }

      public Object getSummaryColumnImage(Object o)
      {
        return getOverviewSummaryColumnImage(o);
      }

      public Object getColumnImage(Object o, int columnIndex)
      {
        if (columnIndex == 0)
        {
          return super.getImage(o);
        }
        else if (multipleColumnsAction == null || !multipleColumnsAction.isEnabled() || !multipleColumnsAction.isChecked())
        {
          if (columnIndex == 2)
          {
            return getSummaryColumnImage(o);
          }
          else
          {
            Collection mappedObjects = new ArrayList();
            Collection mappings = mappingRoot.getMappings(o);
            for (Iterator i = mappings.iterator(); i.hasNext(); )
            {
              Mapping mapping = (Mapping)i.next();
              for (Iterator j = getSecondaryMappedObjects(mapping).iterator(); j.hasNext(); )
              {
                Object mappedObject = j.next();
                if (!mappedObjects.contains(mappedObject))
                {
                  mappedObjects.add(mappedObject);
                }
              }
            }
  
            Object result = null;
            for (Iterator i = mappedObjects.iterator(); i.hasNext(); )
            {
              Object mappedObject = i.next();
              IItemLabelProvider itemLabelProvider = 
                (IItemLabelProvider)/* EATM getDecoratedAdapterFactory().*/adapt(mappedObject, IItemLabelProvider.class);
              Object image = itemLabelProvider.getImage(mappedObject);
              if (result == null)
              {
                result = image;
              }
              else if (!result.equals(image))
              {
                result = MappingPlugin.getPlugin().getImage("full/obj16/MultipleImages");
                break;
              }
            }
  
            return result;
          }
        }
        else
        {
          Collection secondaryMappedObjectsCollection = getSecondaryMappedObjects(mappingRoot);
          if (secondaryMappedObjectsCollection.size() == 1)
          {
            secondaryMappedObjectsCollection = mappingDomain.getChildren(secondaryMappedObjectsCollection.iterator().next());
          }
          int count = 0;
          for (Iterator secondaryMappedObjects = secondaryMappedObjectsCollection.iterator(); secondaryMappedObjects.hasNext(); )
          {
            Object secondardMappedObject = secondaryMappedObjects.next();
            if (++count == columnIndex)
            {
              Collection mappedObjects = new HashSet();
              Collection mappings = mappingRoot.getMappings(o);
              for (Iterator i = mappings.iterator(); i.hasNext(); )
              {
                Mapping mapping = (Mapping)i.next();
                mappedObjects.addAll(getSecondaryMappedObjects(mapping));
              }

              Object result = null;
              for (Iterator i = mappedObjects.iterator(); i.hasNext(); )
              {
                Object mappedObject = i.next();
                  //FB
                for (Object parent = mappedObject; parent instanceof EObject; parent = mappingDomain.getParent(parent))
                {
                  if (parent == secondardMappedObject)
                  {
                    IItemLabelProvider itemLabelProvider = 
                      (IItemLabelProvider)/* EATM getDecoratedAdapterFactory().*/ adapt(mappedObject, IItemLabelProvider.class);
                    Object image = itemLabelProvider.getImage(mappedObject);
                    if (result == null)
                    {
                      result = image;
                    }
                    else if (!result.equals(image))
                    {
                      result = MappingPlugin.getPlugin().getImage("full/obj16/MultipleImages");
                    }
                    break;
                  }
                }
              }

              return result;
            }
          }

          return getSummaryColumnImage(o);
        }
      }

      public String getSummaryColumnText(Object o)
      {
        return getOverviewSummaryColumnText(o);
      }

      public String getColumnText(Object o, int columnIndex)
      {
        if (columnIndex == 0)
        {
          return super.getText(o);
        }
        else if (multipleColumnsAction == null || !multipleColumnsAction.isEnabled() || !multipleColumnsAction.isChecked())
        {
          if (columnIndex == 2)
          {
            return getSummaryColumnText(o);
          }
          else
          {
            Collection mappedObjects = new ArrayList();
            Collection mappings = mappingRoot.getMappings(o);
            for (Iterator i = mappings.iterator(); i.hasNext(); )
            {
              Mapping mapping = (Mapping)i.next();
              for (Iterator j = getSecondaryMappedObjects(mapping).iterator(); j.hasNext(); )
              {
                Object mappedObject = j.next();
                if (!mappedObjects.contains(mappedObject))
                {
                  mappedObjects.add(mappedObject);
                }
              }
            }

            StringBuffer label = new StringBuffer(); 
            for (Iterator i = mappedObjects.iterator(); i.hasNext(); )
            {
              Object mappedObject = i.next();
              if (label.length() > 0)
              {
                label.append(SEPARATOR);
              }
              IItemLabelProvider itemLabelProvider = 
                (IItemLabelProvider)/* EATM getDecoratedAdapterFactory().*/adapt(mappedObject, IItemLabelProvider.class);
              label.append(itemLabelProvider.getText(mappedObject));
            }
  
            return label.toString();
          }
        }
        else
        {
          Collection secondaryMappedObjectsCollection = getSecondaryMappedObjects(mappingRoot);
          if (secondaryMappedObjectsCollection.size() == 1)
          {
            secondaryMappedObjectsCollection = mappingDomain.getChildren(secondaryMappedObjectsCollection.iterator().next());
          }
          int count = 0;
          for (Iterator secondaryMappedObjects = secondaryMappedObjectsCollection.iterator(); secondaryMappedObjects.hasNext(); )
          {
            Object secondardMappedObject = secondaryMappedObjects.next();
            if (++count == columnIndex)
            {
              Collection mappedObjects = new HashSet();
              Collection mappings = mappingRoot.getMappings(o);
              for (Iterator i = mappings.iterator(); i.hasNext(); )
              {
                Mapping mapping = (Mapping)i.next();
                mappedObjects.addAll(getSecondaryMappedObjects(mapping));
              }

              StringBuffer label = new StringBuffer(); 
              for (Iterator i = mappedObjects.iterator(); i.hasNext(); )
              {
                Object mappedObject = i.next();
                  //FB
                for (Object parent = mappedObject; parent instanceof EObject; parent = mappingDomain.getParent(parent))
                {
                  if (parent == secondardMappedObject)
                  {
                    if (label.length() > 0)
                    {
                      label.append(SEPARATOR);
                    }
                    IItemLabelProvider itemLabelProvider = 
                      (IItemLabelProvider)/* EATM getDecoratedAdapterFactory().*/ adapt(mappedObject, IItemLabelProvider.class);
                    label.append(itemLabelProvider.getText(mappedObject));
                    break;
                  }
                }
              }

              return label.toString();
            }
          }

          return getSummaryColumnText(o);
        }
      }

      public void fireNotifyChanged(Notification note)
      {
        super.fireNotifyChanged(note);
      }

      public void notifyChanged(Notification note)
      {
        if (mappingDomain.getMappingRoot().isInputObject(note.getNotifier()) || 
              mappingDomain.getMappingRoot().isOutputObject(note.getNotifier()))
        {
          EStructuralFeature myFeature = (EStructuralFeature) note.getFeature(); 
          if (note.getFeatureID(null) == Notification.NO_FEATURE_ID - 1)
          {
            ENotificationImpl newNote = new ENotificationImpl(
               (InternalEObject)note.getNotifier(), 
               Notification.SET, 
               null, 
               null, 
               null, 
               Notification.NO_INDEX);
            super.notifyChanged(newNote);

            if (action.isChecked() &&
                 (note.getNewValue() instanceof Mapping 
                 || note.getOldValue() instanceof Mapping))
            {
              if (!doMappingRefresh(note.getNotifier()))
              {
                for (Object parent = getParent(note.getNotifier()); parent instanceof EObject; parent = mappingDomain.getParent(parent))
                {
                  if (mappingDomain.getMappingRoot().getMappings(parent).isEmpty() == exclude)
                  {
                    ENotificationImpl newNote2 = new ENotificationImpl(
                      (InternalEObject)parent, 
                      Notification.ADD, 
                      null, 
                      null, 
                      null, 
                      Notification.NO_INDEX);
                    super.notifyChanged(newNote2);
                    break;
                  }
                }

                if (note.getOldValue() instanceof Mapping)
                {
                  for (Iterator primaryMappedObjects = getPrimaryMappedObjects((Mapping)note.getOldValue()).iterator(); 
                       primaryMappedObjects.hasNext(); )
                  {
                    Object primaryMappedObject = primaryMappedObjects.next();
                    
                     ENotificationImpl newNote2 = new ENotificationImpl(
                      (InternalEObject)primaryMappedObject, 
                      Notification.SET, 
                      null, 
                      null, 
                      null, 
                      Notification.NO_INDEX);
                    super.notifyChanged(newNote2);
                  }
                }
                else if (note.getNewValue() instanceof Mapping)
                {
                  for (Iterator primaryMappedObjects = getPrimaryMappedObjects((Mapping)note.getNewValue()).iterator(); 
                       primaryMappedObjects.hasNext(); )
                  {
                    Object primaryMappedObject = primaryMappedObjects.next();
                    
                     ENotificationImpl newNote2 = new ENotificationImpl(
                      (InternalEObject)primaryMappedObject, 
                      Notification.SET, 
                      null, 
                      null, 
                      null, 
                      Notification.NO_INDEX);
                    super.notifyChanged(newNote2);
                  }
                }
              }
            }
          }
          else if (note.getFeatureID(null) != Notification.NO_FEATURE_ID - 2)
          {
            Collection additions = new ArrayList();
            switch (note.getEventType())
            {
              case Notification.ADD:
              {
                additions.add(note.getNewValue());
                break;
              }
              case Notification.ADD_MANY:
              {
                additions.addAll((Collection)note.getNewValue());
                break;
              }
              case Notification.SET:
              {
                if (note.getPosition() == Notification.NO_INDEX)
                {
                  doMappedObjectRefresh(note.getNotifier());
                }
                else
                {
                  additions.add(note.getNewValue());
                }
                break;
              }
            }

            for (Iterator i = additions.iterator(); i.hasNext(); )
            {
              Object addition = i.next();
              for (TreeIterator objects = new AdapterFactoryTreeIterator(mappingDomain.getAdapterFactory(), addition); 
                   objects.hasNext(); )
              {
                Object child = objects.next();
                adapterFactory.adapt(child, ITreeItemContentProvider.class);
              }
            }

          }
        }

        super.notifyChanged(note);
      }

      public void setMappedObjectState(MappedObjectState mappedObjectState)
      {
        mappedObjectState.addListener(this);
        mappedObjectStates.add(mappedObjectState);
      }

      public void dispose()
      {
        if (mappedObjectStates != null)
        {
          for (Iterator i = mappedObjectStates.iterator(); i.hasNext(); )
          {
            MappedObjectState mappedObjectState = (MappedObjectState)i.next();
            mappedObjectState.removeListener(this);
          }
        }
        mappedObjectStates.clear();

        super.dispose();
      }
    }

    protected IItemProviderDecorator createItemProviderDecorator(Object object, Object type)
    {
      MyItemProviderDecorator result = new MyItemProviderDecorator(this);
      return result;
    }

    public void fireNotifyChanged(Notification note)
    {
      if (isNotificationEnabled())
      {
        super.fireNotifyChanged(note);
      }
    }
  }

  protected class OverviewDecoratorAdapterFactory extends MyDecoratorAdapterFactory
  {
    INotifyChangedListener notifyChangedListener = 
      new INotifyChangedListener()
      {
        public void notifyChanged(Notification note)
        {
          if (note.getNotifier() instanceof Mapping)
          {
            Mapping mapping = (Mapping)note.getNotifier();

            // The helper part assumes that the summary is likely based on the helper and will change when the helper is set.
            //
            if (note.getFeature() == MappingPackage.eINSTANCE.getMapping_Inputs() || 
                  note.getFeature() == MappingPackage.eINSTANCE.getMapping_Outputs() ||
                  getOverviewSummaryColumnLabel() != null && note.getFeature() == MappingPackage.eINSTANCE.getMapping_Helper())
            {
              for (Iterator mappedObjects = mapping.getMappedObjects().iterator(); mappedObjects.hasNext(); )
              {
                ENotificationImpl newNote = new ENotificationImpl(
                (InternalEObject)mappedObjects.next(), 
                Notification.SET, 
                null, 
                null, 
                null, 
                Notification.NO_INDEX);
                fireNotifyChanged(newNote);
              }
            }
          }
        }
      };

    public OverviewDecoratorAdapterFactory(AdapterFactory adapterFactory, Action action, boolean exclude, Action multipleColumnsAction)
    {
      super(adapterFactory, action, exclude, multipleColumnsAction);
      ((IChangeNotifier)decoratedAdapterFactory).addListener(notifyChangedListener);
    }

    public void fireNotifyChanged(Notification note)
    {
      MappingRoot mappingRoot = mappingDomain.getMappingRoot();
      if (note.getNotifier() == mappingRoot &&
            (note.getFeature() == MappingPackage.eINSTANCE.getMapping_Outputs() 
            || note.getFeature() == MappingPackage.eINSTANCE.getMapping_Inputs()))
      {
        overviewViewer.getControl().setVisible(false);
        overviewViewer.preserveState();
        overviewViewer.init();
        overviewViewer.setInput(new ItemProvider(overviewViewer.getPrimaryMappedObjects(mappingDomain.getMappingRoot())));
        overviewViewer.restoreState();
        overviewViewer.getControl().setVisible(true);
      }
      else
      {
        if (isNotificationEnabled())
        {
          super.fireNotifyChanged(note);
        }
      }
      if (isNotificationEnabled())
      {
        overviewViewer.refreshCell();
      }
    }

    public List getPrimaryMappedObjects(Mapping mapping)
    {
      return overviewViewer.getPrimaryMappedObjects(mapping);
    }

    public List getSecondaryMappedObjects(Mapping mapping)
    {
      return overviewViewer.getSecondaryMappedObjects(mapping);
    }

    protected void doMappedObjectRefresh(Object object)
    {
      MappingRoot mappingRoot = mappingDomain.getMappingRoot();
      Collection mappings = mappingRoot.getMappings(object);
      Collection primaryMappedObjects = new HashSet();
      for (Iterator i = mappings.iterator(); i.hasNext(); )
      {
        Mapping mapping = (Mapping)i.next();
        primaryMappedObjects.addAll(getPrimaryMappedObjects(mapping));
      }
      if (!primaryMappedObjects.contains(object))
      {
        for (Iterator i = primaryMappedObjects.iterator(); i.hasNext(); )
        {
          Object primaryMappedObject = i.next();
          if (isNotificationEnabled())
          {
            ENotificationImpl newNote = new ENotificationImpl(
                (InternalEObject)primaryMappedObject, 
                Notification.SET, 
                null, 
                null, 
                null, 
                Notification.NO_INDEX);
            super.fireNotifyChanged(newNote);
          }
        }
      }
    }
  }

  class MyViewerPane extends ViewerPane
  {
    protected MappingDomain domain;
    protected boolean isTop;
    public MyViewerPane(IWorkbenchPage page, IWorkbenchPart part, MappingDomain domain, boolean isTop)
    {
      super(page, part);
      this.domain = domain;
      this.isTop = isTop;
    }
    public Viewer createViewer(Composite composite)
    {
      return new SimpleMappedObjectViewer(domain, new Tree(composite, SWT.MULTI), isTop);
    }
    public void requestActivation()
    {
      super.requestActivation();
      setCurrentViewerPane(this);
    }
  }

  /**
   * This is the method used by the framework to install your own controls.
   */
  public void createPages()
  {
    // I assume that the editorInput is a file object.
    //
    modelFile = (IFileEditorInput)getEditorInput();

    // Handle the creation of the model from the modelFile.
    //
    handleCreation();

    // Restore the persistent command stack, if need be.
    //
    mappingDomain.getCommandStack().execute(RestoreInitialStateCommand.create(mappingDomain));

    // Add a listener to set the most recent command's affected objects to be the selection of the viewer with focus.
    //
    mappingDomain.getCommandStack().addCommandStackListener(commandStackListener);

    // Create a page for the selection tree view.
    //
    {
      final SashForm compositePage = new SashForm(getContainer(), SWT.VERTICAL);
      final SashForm topSashForm = new SashForm(compositePage, SWT.HORIZONTAL);
      final MappingDomain theDomain = mappingDomain;


      ViewerPane leftSelectionViewerPane = new MyViewerPane(getSite().getPage(), MappingEditor.this, mappingDomain, true);
      leftSelectionViewerPane.createControl(topSashForm);
      leftSelectionViewer = (SimpleMappedObjectViewer)leftSelectionViewerPane.getViewer();

      ViewerPane rightSelectionViewerPane = new MyViewerPane(getSite().getPage(), MappingEditor.this, mappingDomain, false);
      rightSelectionViewerPane.createControl(topSashForm);
      rightSelectionViewer = (SimpleMappedObjectViewer)rightSelectionViewerPane.getViewer();

      addSelectionChangedListener
        (new ISelectionChangedListener()
         {
           // This ensures that we handle selections correctly.
           //
           public void selectionChanged(SelectionChangedEvent event)
           {
             if (currentViewer == contentOutlineViewer)
             {
               Collection mappedObjects = getMappedObjects(event.getSelection());
               IStructuredSelection selection = new StructuredSelection(mappedObjects.toArray());
               leftSelectionViewer.setSelection(selection, true);
               rightSelectionViewer.setSelection(selection, true);

               Collection indirectMappedObjects = new HashSet();
               Collection primaryMappedObjects = new HashSet();
               for (Iterator objects = mappedObjects.iterator(); objects.hasNext(); )
               {
                 Object object = objects.next();
                 if (overviewViewer.isSecondaryMappedObject(mappingRoot, object))
                 {
                   for (Iterator mappings = mappingRoot.getMappings(object).iterator(); mappings.hasNext(); )
                   {
                     Mapping mapping = (Mapping)mappings.next();
                     indirectMappedObjects.addAll(overviewViewer.getPrimaryMappedObjects(mapping));
                   }
                 }
                 else if (overviewViewer.isPrimaryMappedObject(mappingRoot, object))
                 {
                   primaryMappedObjects.add(object);
                 }
               }
               if (!primaryMappedObjects.isEmpty())
               {
                 overviewViewer.setSelection(new StructuredSelection(primaryMappedObjects.toArray()), true);
               }
               else if (!indirectMappedObjects.isEmpty())
               {
                 overviewViewer.setSelection(new StructuredSelection(indirectMappedObjects.toArray()), true);
               }
             }
             else if (currentViewer == leftSelectionViewer || currentViewer == rightSelectionViewer)
             {
               Collection mappedObjects = new HashSet();
               for (Iterator mappings = 
                      mappingRoot.getAllMappings(((IStructuredSelection)event.getSelection()).toList()).iterator();
                    mappings.hasNext(); )
               {
                 Mapping mapping = (Mapping)mappings.next();
                 mappedObjects.addAll(overviewViewer.getPrimaryMappedObjects(mapping));
               }

               if (!mappedObjects.isEmpty())
               {
                 overviewViewer.setSelection(new StructuredSelection(mappedObjects.toArray()), true);
               }
               else
               {
                 overviewViewer.setSelection(event.getSelection(), true);
               }
             }
             else if (currentViewer == overviewViewer)
             {
               IStructuredSelection selection = (IStructuredSelection)event.getSelection();
               if (!selection.isEmpty() && contentOutlineViewer != null)
               {
                 contentOutlineViewer.setSelection(selection, true);
               }
               Collection mappedObjects = getMappedObjects(selection);
               leftSelectionViewer.setSelection(new StructuredSelection(mappedObjects.toArray()));
               rightSelectionViewer.setSelection(new StructuredSelection(mappedObjects.toArray()));
             }

             if (currentViewer == leftSelectionViewer || currentViewer == rightSelectionViewer)
             {
               Collection mappings = 
                 mappingRoot.getAllMappings(((IComposedSelection)event.getSelection()).getCombinedSelection().toList());
               if (mappings.isEmpty())
               {
                 mappings = 
                   mappingRoot.getAllMappings(((IStructuredSelection)event.getSelection()).toList());
               }
               if (!mappings.isEmpty() && contentOutlineViewer != null)
               {
                 contentOutlineViewer.setSelection(new StructuredSelection(mappings.toArray()), true);
               }
             }
           }
         });

      leftSelectionViewer.setOtherViewer(rightSelectionViewer);
      rightSelectionViewer.setOtherViewer(leftSelectionViewer);

      leftSelectionViewer.makeContributions
        (leftSelectionViewerPane.getMenuManager(), leftSelectionViewerPane.getToolBarManager(), getActionBars().getStatusLineManager());
      leftSelectionFactory = 
        new MyDecoratorAdapterFactory(mappingDomain.getAdapterFactory(), leftSelectionViewer.getFilterMappedObjectsAction(), true)
        {
          protected boolean doMappingRefresh(Object object)
          {
            if (leftSelectionViewer.getFilterMappedObjectsAction().isChecked())
            {
              leftSelectionViewer.getFilterMappedObjectsAction().setChecked(false);
              leftSelectionViewer.getFilterMappedObjectsAction().run();
            }
            return true;
          }
        };
      leftSelectionFactory.adapt((Object)mappingRoot, ITreeItemContentProvider.class);
      leftSelectionViewer.setAdapterFactory(leftSelectionFactory);
      leftSelectionViewer.setContentProvider
        (new AdapterFactoryContentProvider(leftSelectionFactory)
         {
           public void notifyChanged(Notification note)
           {
             if (note.getNotifier() == mappingRoot &&
                   (note.getFeature() == MappingPackage.eINSTANCE.getMapping_Outputs() 
                   || note.getFeature() == MappingPackage.eINSTANCE.getMapping_Inputs()))
             {
               leftSelectionViewer.setInput(new ItemProvider(mappingRoot.getTops()));
             }
             else
             {
               super.notifyChanged(note);
             }
           }
         });
      leftSelectionViewer.setLabelProvider(new MappingDomainLabelProvider(mappingDomain));
      leftSelectionViewer.setAutoExpandLevel(2);
      leftSelectionViewer.setInput(new ItemProvider(mappingRoot.getTops()));
      leftSelectionViewer.setSelection(new StructuredSelection(mappingRoot.getTops().toArray()));
      leftSelectionViewerPane.setTitle(getTopLabel(), getTopImage());

      rightSelectionViewer.makeContributions
        (rightSelectionViewerPane.getMenuManager(), rightSelectionViewerPane.getToolBarManager(), getActionBars().getStatusLineManager());
      rightSelectionFactory = 
        new MyDecoratorAdapterFactory(mappingDomain.getAdapterFactory(), rightSelectionViewer.getFilterMappedObjectsAction(), true)
        {
          protected boolean doMappingRefresh(Object object)
          {
            if (rightSelectionViewer.getFilterMappedObjectsAction().isChecked())
            {
              rightSelectionViewer.getFilterMappedObjectsAction().setChecked(false);
              rightSelectionViewer.getFilterMappedObjectsAction().run();
            }
            return true;
          }
        };
      rightSelectionFactory.adapt((Object)mappingRoot, ITreeItemContentProvider.class);
      rightSelectionViewer.setAdapterFactory(rightSelectionFactory);
      rightSelectionViewer.setContentProvider
        (new AdapterFactoryContentProvider(rightSelectionFactory)
         {
           public void notifyChanged(Notification note)
           {
             if (note.getNotifier() == mappingRoot &&
                   (note.getFeature() == MappingPackage.eINSTANCE.getMapping_Outputs() 
                   || note.getFeature() == MappingPackage.eINSTANCE.getMapping_Inputs()))
             {
               rightSelectionViewer.setInput(new ItemProvider(mappingRoot.getBottoms()));
             }
             else
             {
               super.notifyChanged(note);
             }
           }
         });
      rightSelectionViewer.setLabelProvider(new MappingDomainLabelProvider(mappingDomain));
      rightSelectionViewer.setAutoExpandLevel(2);
      rightSelectionViewer.setInput(new ItemProvider(mappingRoot.getBottoms()));
      rightSelectionViewer.setSelection(new StructuredSelection(mappingRoot.getBottoms().toArray()));
      rightSelectionViewerPane.setTitle(getBottomLabel(), getBottomImage());

      overviewViewerPane =
        new ViewerPane(getSite().getPage(), MappingEditor.this)
        {
          public Viewer createViewer(Composite composite)
          {
            return createOverviewViewer(composite);
          }
          public void requestActivation()
          {
            super.requestActivation();
            setCurrentViewerPane(this);
          }
          public void showFocus(boolean inFocus)
          {
            super.showFocus(inFocus);
            if (!inFocus)
            {
              overviewViewer.dismissCellEditor();
            }
          }
        };
      overviewViewerPane.createControl(compositePage);
      overviewViewer = (OverviewViewer)overviewViewerPane.getViewer();
      overviewViewer.setAutoExpandLevel(2);
      overviewViewerPane.setTitle
        (MappingUIPlugin.getPlugin().getString("_UI_Overview_label"), 
           getDefaultCheckedShowTopFirst() ?
             MappingUIPlugin.getPlugin().getImage("full/cview16/OverviewLogo") :
             MappingUIPlugin.getPlugin().getImage("full/cview16/OverviewLogoFlipped"));

      overviewViewer.makeContributions
        (overviewViewerPane.getMenuManager(), overviewViewerPane.getToolBarManager(), getActionBars().getStatusLineManager());

      overviewFactory = createOverviewDecoratorAdapterFactory();

      overviewViewer.setAdapterFactory(overviewFactory);
      overviewFactory.adapt((Object)mappingRoot, ITreeItemContentProvider.class);

      overviewViewer.setContentProvider
        (new AdapterFactoryContentProvider(overviewFactory)
         {
           public void inputChanged(Viewer viewer, Object oldInput, Object newInput)
           {
             if (newInput != null)
             {
               for (TreeIterator children = new AdapterFactoryTreeIterator(mappingDomain.getAdapterFactory(), newInput); 
                    children.hasNext(); )
               {
                 Object child = children.next();
                 adapterFactory.adapt(child, ITreeItemContentProvider.class);
               }
             }
             super.inputChanged(viewer, oldInput, newInput);
           }
         });
      overviewViewer.setLabelProvider(new AdapterFactoryLabelProvider(overviewFactory));
      overviewViewer.setInput(new ItemProvider(overviewViewer.getPrimaryMappedObjects(mappingRoot)));

      createContextMenuFor(leftSelectionViewer);
      createContextMenuFor(rightSelectionViewer);
      createContextMenuFor(overviewViewer);

      int pageIndex = addPage(compositePage);
      setPageText(pageIndex, MappingUIPlugin.getPlugin().getString("_UI_Composition_label"));
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

  /**
   * If there is just one page in the multi-page editor part, this hides
   * the single tab at the bottom.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
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

  public String getURIFromFile(IFile file)
  {
    return URI.createPlatformResourceURI(file.getFullPath().toString()).toString();
  }

  /**
   * This creates the model based on the {@link #modelFile}.
   */
  protected void handleCreation()
  {
    // Get the OS compatible name of the model file.
    //
    // String fileName = modelFile.getFile().getLocation().toOSString();

    try
    {
      // Load the resource through the editing domain.
      // This will create a context and associate it with the resource set.
      //
      Resource resource = mappingDomain.loadResource(getURIFromFile(modelFile.getFile()));
   
      // The one object in the resource's extent should be the root object.
      //
      if (resource.getContents().isEmpty())
      {
        handleMissingModelFile();
      }
      else
      {
        Object rootObject = resource.getContents().get(0);
  
        if (rootObject instanceof MappingRoot)
        {
          setMappingRoot((MappingRoot)rootObject);
        }
        else
        {
          handleMissingModelFile();
        }
      }
    }
    catch (Exception exception)
    {
      handleCreationException(exception);
    }
  }

  protected abstract void handleMissingModelFile();

  protected void handleCreationException(Exception exception)
  {
    exception.printStackTrace();
    setMappingRoot(MappingFactory.eINSTANCE.createMappingRoot());
  }

  protected void setMappingRoot(MappingRoot mappingRoot)
  {
    this.mappingRoot = mappingRoot;
    this.mappingRoot.setDomain(mappingDomain);

    // This case happens when the IDE is brought up with the editor already open
    //
    if (contentOutlineViewer != null && contentOutlineViewer.getInput() == null)
    {
      // Select the root object in the view.
      //
      contentOutlineViewer.setInput(new ItemProvider(Collections.singleton(mappingRoot)));
      ArrayList selection = new ArrayList();
      selection.add(mappingRoot);
      contentOutlineViewer.setSelection(new StructuredSelection(selection), true);
    }
  }

  /**
   * This is how the framework determines which interfaces we implement.
   */
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
      return super.getAdapter(key);
    }
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
        public void createControl(Composite parent) 
        {
          contentOutlineFilterAction =
            new Action
              (MappingUIPlugin.getPlugin().getString("_UI_FilterMappedObjects_menu_item"), 
               MappingUIPlugin.getPlugin().getImageDescriptor("full/elcl16/OutlineFilter"))
            {
              public void run()
              {
                contentOutlineViewer.getControl().setVisible(false);
                Object [] expandedElements = contentOutlineViewer.getExpandedElements();
                Collection selectedObjects = new HashSet();
                for (Iterator i = ((IStructuredSelection)contentOutlineViewer.getSelection()).iterator(); i.hasNext(); )
                {
                  Object selectedObject = i.next();
                  selectedObjects.add(selectedObject);
                  selectedObjects.add
                    (selectedObject instanceof MappedObjectItemProvider ? 
                      ((MappedObjectItemProvider)selectedObject).getMapping() : 
                      selectedObject);
                }

                Object input = contentOutlineViewer.getInput();
                contentOutlineViewer.setInput(input);

                contentOutlineViewer.setExpandedElements(expandedElements);
                contentOutlineViewer.setSelection(new StructuredSelection(selectedObjects.toArray()), true);
                contentOutlineViewer.getControl().setVisible(true);
              }
              public void setChecked(boolean checked)
              {
                super.setChecked(checked);
                setToolTipText
                  (MappingUIPlugin.getPlugin().getString
                    (checked ? "_UI_OutlineFilter_checked_description" : "_UI_OutlineFilter_unchecked_description"));
              }
            };
          contentOutlineFilterAction.setChecked(true);
          contentOutlineFilterAction.setHoverImageDescriptor
            (MappingUIPlugin.getPlugin().getImageDescriptor("full/clcl16/OutlineFilter"));
          contentOutlineFilterAction.setDisabledImageDescriptor
            (MappingUIPlugin.getPlugin().getImageDescriptor("full/dlcl16/OutlineFilter"));

          super.createControl(parent);
          contentOutlineViewer = getTreeViewer();
          contentOutlineViewer.addSelectionChangedListener(this);
          contentOutlineViewer.setAutoExpandLevel(2);

          // Set up the tree viewer.
          //
          contentOutlineViewer.setContentProvider
            (new AdapterFactoryContentProvider(mappingDomain.getAdapterFactory())
             {
               public Object [] getChildren(Object object)
               {
                 return 
                   contentOutlineFilterAction.isChecked() ?
                     ((Mapping)object).getNested().toArray() :
                     super.getChildren(object);
               }

               public boolean hasChildren(Object object)
               {
                 return 
                   contentOutlineFilterAction.isChecked() ?
                     object instanceof Mapping && !((Mapping)object).getNested().isEmpty() :
                     object instanceof Mapping;
               }
             });
          contentOutlineViewer.setLabelProvider(new MappingDomainLabelProvider(mappingDomain));
          if (mappingRoot != null)
          {
            contentOutlineViewer.setInput(new ItemProvider(Collections.singleton(mappingRoot)));
          }

          // Make sure our popups work.
          //
          createContextMenuFor(contentOutlineViewer);

          if (mappingRoot != null)
          {
            // Select the root object in the view.
            //
            ArrayList selection = new ArrayList();
            selection.add(mappingRoot);
            contentOutlineViewer.setSelection(new StructuredSelection(selection), true);
          }
        }

        public void setActionBars(IActionBars actionBars)
        {
          super.setActionBars(actionBars);

          contentOutlineStatusLineManager = actionBars.getStatusLineManager();
          actionBars.getToolBarManager().add(contentOutlineFilterAction);
          actionBars.getMenuManager().add(contentOutlineFilterAction);
          getActionBarContributor().shareGlobalActions(this, actionBars);
        }
      }

      contentOutlinePage = new MyContentOutlinePage();
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
          public void setActionBars(IActionBars actionBars)
          {
            super.setActionBars(actionBars);
            getActionBarContributor().shareGlobalActions(this, actionBars);
          }
        };
      propertySheetPage.setPropertySourceProvider(new AdapterFactoryContentProvider(mappingDomain.getAdapterFactory()));
    }

    return propertySheetPage;
  }

  /**
   * This is for implementing {@link IEditorPart} and simply tests the command stack.
   */
  public boolean isDirty()
  {
    return ((BasicCommandStack)mappingDomain.getCommandStack()).isSaveNeeded();
  }

  /**
   * This is for implementing {@link IEditorPart} and simply saves the model file.
   */
  public void doSave(IProgressMonitor progressMonitor)
  {
    doSaveHelper(modelFile.getFile());
  }

  protected void doSaveHelper(final IFile file)
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
            if (mappingDomain.getCommandStack() instanceof PersistentCommandStack)
            {
              mappingRoot.setCommandStack(((PersistentCommandStack)mappingDomain.getCommandStack()).getEncoding());
            }

            // Save the resource to the file system.
            //
            Resource r = mappingRoot.eResource();
            r.save(Collections.EMPTY_MAP);
            

            // Update the workbench's knowledge of the file's contents.
            //
            file.refreshLocal(1, monitor);

            if (!mappingRoot.isOutputReadOnly())
            {
              HashSet mappedObjectResources = new HashSet();
              for (Iterator outputs = mappingRoot.getOutputs().iterator(); outputs.hasNext(); )
              {
                EObject output = (EObject)outputs.next();
                Resource mappedObjectResource = output.eResource();
                if (mappedObjectResource != null)
                {
                  if (mappedObjectResources.add(mappedObjectResource))
                  {
                    mappedObjectResource.save(Collections.EMPTY_MAP);
                    URL resolvedURL = Platform.resolve(new URL(mappedObjectResource.getURI().toString()));
                    IFile mappedObjectFile = 
                      ResourcesPlugin.getWorkspace().getRoot().getFileForLocation(new Path(resolvedURL.getFile()));
                    if (mappedObjectFile != null)
                    {
                      mappedObjectFile.refreshLocal(1, monitor);
                    }
                  }
                }
              }
            }
          }
          catch (Exception exception)
          {
            exception.printStackTrace();
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
      ((BasicCommandStack)mappingDomain.getCommandStack()).saveIsDone();
      firePropertyChange(IEditorPart.PROP_DIRTY);
    }
    catch (Exception exception)
    {
      // Something went wrong that shouldn't.
      //
      System.err.println(MappingPlugin.getPlugin().getString("_EXC_SaveFailed"));
      exception.printStackTrace();
    }
  }

  /**
   * This always returns false because it is not currently supported.
   */
  public boolean isSaveAsAllowed()
  {
    return true;
  }

  /**
   * This also changes the model.
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
        URI oldURI = mappingRoot.eResource().getURI();
        mappingRoot.eResource().setURI(URI.createURI(getURIFromFile(file)));
        modelFile = new FileEditorInput(file);
        setInput(modelFile);
        setPartName(file.getName());
        doSaveHelper(file);
      }
    }
  }

  public void gotoMarker(IMarker marker)
  {
  }

  public void init(IEditorSite site, IEditorInput editorInput) throws PartInitException
  {
    if (editorInput instanceof IFileEditorInput)
    {
      setSite(site);
      setInput(editorInput);
      site.setSelectionProvider(this);

      site.getPage().addPartListener(partListener);

      setPartName(((IFileEditorInput)editorInput).getFile().getName());

      // Create the mapping domain with a special command stack.
      // 
      mappingDomain = createMappingDomain(); 

      // Create an adapter factory that yields item providers.
      //
      AdapterFactory adapterFactory = mappingDomain.getAdapterFactory();

      editorSelection = new StructuredSelection();
    }
    else
    {
      throw new PartInitException(MappingUIPlugin.getPlugin().getString("_EXC_InvalidEditorInput"));
    }
  }

  public void setFocus()
  {
    // Doing this just makes focus go to the default control, not the control that last has focus.
    //
    // super.setFocus();

    if (currentViewerPane != null)
    {
      currentViewerPane.setFocus();
    }
    else
    {
      getControl(getActivePage()).setFocus();
    }
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
    for (Iterator listeners = selectionChangedListeners.iterator(); listeners.hasNext(); )
    {
      ISelectionChangedListener listener = (ISelectionChangedListener)listeners.next();
      listener.selectionChanged(new SelectionChangedEvent(this, selection));
    }
    setStatusLineManager(selection);
  }

  protected static final String SELECTED_MAPPING_PREFIX = 
    MappingUIPlugin.getPlugin().getString("_UI_SelectedMapping_statusline_prefix") + " ";

  protected static final String SELECTED_MAPPED_OBJECT_PREFIX = 
    MappingUIPlugin.getPlugin().getString("_UI_SelectedMappedObject_statusline_prefix") + " ";

  public void setStatusLineManager(ISelection selection)
  {
    IStatusLineManager statusLineManager = getActionBars().getStatusLineManager();
    if (currentViewer == contentOutlineViewer)
    {
      statusLineManager = contentOutlineStatusLineManager;
    }

    if (selection instanceof IComposedSelection)
    {
      Collection objects = ((IComposedSelection)selection).getCombinedSelection().toList();
      Image image = 
        ExtendedImageRegistry.getInstance().getImage(MappingItemProvider.getImage(mappingDomain.getMappingRoot(), "full/obj16/", objects));
      String text = MappingItemProvider.getText(mappingDomain.getMappingRoot(), mappingDomain.getAdapterFactory(), objects, "/");
      statusLineManager.setMessage(SELECTED_MAPPING_PREFIX + text);
    }
    else if (selection instanceof IStructuredSelection)
    {
      Collection collection = ((IStructuredSelection)selection).toList();
      switch (collection.size())
      {
        case 0:
        {
          statusLineManager.setMessage
            (MappingUIPlugin.getPlugin().getString("_UI_Selected_statusline_prefix", "0"));
          break;
        }
        case 1:
        {
          Object object = collection.iterator().next();
          if (object instanceof Mapping)
          {
            String text = 
              MappingItemProvider.getText
                (mappingDomain.getMappingRoot(), mappingDomain.getAdapterFactory(), ((Mapping)object).getMappedObjects(), "/");
            statusLineManager.setMessage(SELECTED_MAPPING_PREFIX + text);
          }
          else
          {
            if (object instanceof MappedObjectItemProvider)
            {
              object = ((MappedObjectItemProvider)object).getMappedObject();
            }

            String text = 
              MappingItemProvider.getText
                (mappingDomain.getMappingRoot(), mappingDomain.getAdapterFactory(), Collections.singleton(object), "/");
            statusLineManager.setMessage(SELECTED_MAPPED_OBJECT_PREFIX + text);
          }
          break;
        }
        default:
        {
          statusLineManager.setMessage
            (MappingUIPlugin.getPlugin().getString("_UI_Selected_statusline_prefix", String.valueOf(collection.size())));
          break;
        }
      }
    }
    else
    {
      statusLineManager.setMessage("");
    }
  }

  /**
   * This implements {@link org.eclipse.jface.action.IMenuListener} to help fill the context menu.
   */
  public void menuAboutToShow(IMenuManager menuManager)
  {
    ((IMenuListener)getEditorSite().getActionBarContributor()).menuAboutToShow(menuManager);
  }

  /**
   * This turns the selection into the set of RefObjects involved in the mapping.
   */
  public Collection getMappedObjects(ISelection selection)
  {
    Collection result = new ArrayList();
    if (selection instanceof IComposedSelection)
    {
      ISelection [] selections = ((IComposedSelection)selection).getSelections();
      for (int i = 0; i < selections.length; ++i)
      {
        result.addAll(getMappedObjects(selections[i]));
      }
    }
    else if (selection instanceof IStructuredSelection)
    {
      for (Iterator selectedElements = ((IStructuredSelection)selection).iterator(); selectedElements.hasNext(); )
      {
        Object selectedElement = selectedElements.next();

        if (selectedElement instanceof MappedObjectItemProvider)
        {
          selectedElement = ((MappedObjectItemProvider)selectedElement).getMappedObject();
        }

        // If the object is an input or output object, then use it.
        //
        if (mappingDomain.getMappingRoot().isInputObject(selectedElement) ||
              mappingDomain.getMappingRoot().isOutputObject(selectedElement))
        {
          result.add(selectedElement);
        }
        else
        {
          // Process the children recursively.
          //
          Object [] selectedElementChildren = mappingDomain.getChildren(selectedElement).toArray();
          for (int i = 0; i < selectedElementChildren.length; ++i)
          {
            Object selectedElementChild = selectedElementChildren[i];
            if (!(selectedElementChild instanceof Mapping))
            {
              result.addAll(getMappedObjects(new StructuredSelection(selectedElementChild)));
            }
          }
        }
      }
    }

    return result;
  }

  /**
   * This turns the selection into the set of mappings, but only if all the objects are mappings.
   */
  public Collection getMappings(ISelection selection)
  {
    Collection result = new ArrayList();
    if (selection instanceof IStructuredSelection)
    {
      for (Iterator selectedElements = ((IStructuredSelection)selection).iterator(); selectedElements.hasNext(); )
      {
        Object selectedElement = selectedElements.next();

        if (selectedElement instanceof Mapping)
        {
          result.add(selectedElement);
        }
        else if (selectedElement instanceof MappedObjectItemProvider)
        {
          result.add(((MappedObjectItemProvider)selectedElement).getMapping());
        }
        else
        {
          return Collections.EMPTY_LIST;
        }
      }
    }

    return result;
  }

  public AdapterFactoryMappingDomain getMappingDomain()
  {
    return mappingDomain;
  }

  public Viewer getCurrentViewer()
  {
    return currentViewer;
  }

  public void dispose()
  {
    getSite().getPage().removePartListener(partListener);
    if (mappingDomain != null)
    {
      AdapterFactory adapterFactory = mappingDomain.getAdapterFactory();
      if (adapterFactory instanceof IDisposable)
      {
        ((IDisposable)adapterFactory).dispose();
      }
      mappingDomain.getCommandStack().removeCommandStackListener(commandStackListener);
    }

    if (propertySheetPage != null)
    {
      propertySheetPage.dispose();
    }

    if (contentOutlinePage != null)
    {
      contentOutlinePage.dispose();
    }

    if (leftSelectionFactory != null)
    {
      leftSelectionFactory.dispose();
      rightSelectionFactory.dispose();
      overviewFactory.dispose();
    }

    getActionBarContributor().setActiveEditor(null);

    if (mappingRoot != null)
    {
      mappingRoot.dispose();
    }

    super.dispose();
  }

  public EditingDomainActionBarContributor getActionBarContributor()
  {
    return (EditingDomainActionBarContributor)getEditorSite().getActionBarContributor();
  }

  public IActionBars getActionBars()
  {
    return getActionBarContributor().getActionBars();
  }

  protected String overviewSummaryColumnLabel;
  public String getOverviewSummaryColumnLabel()
  {
    return overviewSummaryColumnLabel;
  }

  public Object getOverviewSummaryColumnImage(Object o)
  {
    return null;
  }

  public String getOverviewSummaryColumnText(Object o)
  {
    return "";
  }

  public boolean hasLaunchedOverviewSummaryColumnEditor(Object object)
  {
    return false;
  }

  public void createLaunchedOverviewSummaryColumnEditor(Composite parent, Object object)
  {
  }

  public boolean getDefaultCheckedShowTopFirst()
  {
    return !mappingDomain.getMappingRoot().isTopToBottom();
  }

  public boolean getDefaultShowMultipleColumns()
  {
    return false;
  }

  public boolean getDefaultFilterUnmappedObjects()
  {
    return true;
  }

  protected OverviewViewer createOverviewViewer(Composite composite)
  {
    return new OverviewViewer(this, composite);
  }

  protected OverviewDecoratorAdapterFactory createOverviewDecoratorAdapterFactory()
  {
    return
      new OverviewDecoratorAdapterFactory
        (mappingDomain.getAdapterFactory(),
         overviewViewer.getFilterUnmappedObjectsAction(),
         false,
         overviewViewer.getMultipleColumnsAction());
  }

  public static class OverviewViewer extends ExtendedTableTreeViewer
  {
    protected SimpleMappedObjectViewer otherViewer;
    protected MappingEditor mappingEditor;
    protected AdapterFactoryMappingDomain mappingDomain;
    protected AdapterFactory adapterFactory;
    protected Action filterUnmappedObjects;
    protected Action multipleColumns;
    protected Action showTopFirst;
    protected TableTree tableTree;
    protected Table table;
    protected AdapterFactoryTableTreeEditor tableTreeEditor;


    ControlListener controlListener = new DelayedColumnFitter();

    public OverviewViewer(MappingEditor editor, Composite composite) 
    {
      super(composite);
      this.mappingEditor = editor;
      this.mappingDomain = editor.getMappingDomain();

      tableTree = getTableTree();
      table = tableTree.getTable();
      table.getVerticalBar().setVisible(true);
      table.setHeaderVisible(true);
      table.setLinesVisible(true);

      // This is a big festering hack to make the images the right size from the start.
      //
      org.eclipse.swt.widgets.TableItem item = new org.eclipse.swt.widgets.TableItem(table, SWT.NULL);
      item.setImage(1, ExtendedImageRegistry.getInstance().getImage(MappingPlugin.getPlugin().getImage("full/obj16/MultipleImages")));
      TableTreeItem itemx = new TableTreeItem(tableTree, SWT.NULL, 0);
      itemx.setImage(1, ExtendedImageRegistry.getInstance().getImage(MappingPlugin.getPlugin().getImage("full/obj16/MultipleImages")));
      itemx.dispose();
      item.dispose();

      table.addControlListener(controlListener);

      tableTreeEditor = 
        new AdapterFactoryTableTreeEditor(tableTree, mappingDomain.getAdapterFactory())
        {
          TreeViewer dropDownTreeViewer;
          Collection dropDownRoots;
          Collection mappedObjects;
          Mapping mapping;
          HashMap filteredChildren;

          public boolean hasDropDownEditor(Object object, int column)
          {
            if (column == 0 || column == this.table.getColumnCount() - 1 && mappingEditor.getOverviewSummaryColumnLabel() != null)
            {
              return false;
            }

            MappingRoot mappingRoot = mappingDomain.getMappingRoot();

            dropDownTreeViewer = null;
            dropDownRoots = new ArrayList();
            mappedObjects = new ArrayList();
            mapping = null;
            filteredChildren = new HashMap();

            if (multipleColumns == null || multipleColumns.isEnabled() && !multipleColumns.isChecked())
            {
              Collection mappings = mappingRoot.getMappings(object);
              for (Iterator i = mappings.iterator(); i.hasNext(); )
              {
                if (mapping != null)
                {
                  return false;
                }
                mapping = (Mapping)i.next();
                for (Iterator j = getSecondaryMappedObjects(mapping).iterator(); j.hasNext(); )
                {
                  Object mappedObject = j.next();
                  if (!mappedObjects.contains(mappedObject))
                  {
                    mappedObjects.add(mappedObject);
                  }
                }
              }
              dropDownRoots.addAll(getSecondaryMappedObjects(mappingRoot));
            }
            else
            {
              Collection secondaryMappedObjectsCollection = getSecondaryMappedObjects(mappingRoot);
              if (secondaryMappedObjectsCollection.size() == 1)
              {
                secondaryMappedObjectsCollection = mappingDomain.getChildren(secondaryMappedObjectsCollection.iterator().next());
              }
              int count = 0;
              for (Iterator secondaryMappedObjects = secondaryMappedObjectsCollection.iterator(); secondaryMappedObjects.hasNext(); )
              {
                Object secondaryMappedObject = secondaryMappedObjects.next();
                if (++count == column)
                {
                  dropDownRoots.add(secondaryMappedObject);
                  mappedObjects = new HashSet();
                  Collection mappings = mappingRoot.getMappings(object);
                  for (Iterator i = mappings.iterator(); i.hasNext(); )
                  {
                    if (mapping != null)
                    {
                      return false;
                    }
                    mapping = (Mapping)i.next();
                    mappedObjects.addAll(getSecondaryMappedObjects(mapping));
                  }
                  break;
                }
              }
            }

            if (mapping == mappingRoot)
            {
              return false;
            }

            Collection dropDownTree = new ArrayList();
            Collection primaryMappedObjects = mapping != null ? (Collection)getPrimaryMappedObjects(mapping) : Collections.singleton(object);
            for (Iterator i = dropDownRoots.iterator(); i.hasNext(); )
            {
              Object o = i.next();
              if (filter(dropDownTree, mapping, primaryMappedObjects, o))
              {
                dropDownTree.add(o);
              }
            }

            if (dropDownTree.isEmpty())
            {
              return false;
            }

            dropDownRoots = dropDownTree;

            return true;
          }

          protected boolean filter(Collection dropDownTree, Mapping mapping, Collection primaryMappedObjects, Object candidate)
          {
            Collection children = new ArrayList();
            for (Iterator i = mappingDomain.getChildren(candidate).iterator(); i.hasNext(); )
            {
              Object child = i.next();
              if (filter(dropDownTree, mapping, primaryMappedObjects, child))
              {
                children.add(child);
              }
            }

            filteredChildren.put(candidate, children);
            return !children.isEmpty() || canCreateMapping(primaryMappedObjects, candidate);
          }

          protected boolean canCreateMapping(Collection primaryObjects, Object secondaryObject)
          {
            if (!showTopFirst.isChecked() != mappingDomain.getMappingRoot().isTopToBottom())
            {
              return mappingDomain.getMappingRoot().canCreateMapping(Collections.singleton(secondaryObject), primaryObjects, mapping);
            }
            else
            {
              return mappingDomain.getMappingRoot().canCreateMapping(primaryObjects, Collections.singleton(secondaryObject), mapping);
            }
          }

          public Control createDropDownEditor(Composite parent, Object object, int column)
          {
            dropDownTreeViewer = new TreeViewer(new Tree(parent, SWT.MULTI | SWT.FLAT));
            dropDownTreeViewer.setAutoExpandLevel(2);
            dropDownTreeViewer.setContentProvider
              (new AdapterFactoryContentProvider(this.adapterFactory)
               {
                 public boolean hasChildren(Object o)
                 {
                   Collection children = (Collection)filteredChildren.get(o);
                   return children != null && !children.isEmpty();
                 }
                 public Object [] getChildren(Object o)
                 {
                   Collection children = (Collection)filteredChildren.get(o);
                   return (children != null ? children : Collections.EMPTY_LIST).toArray();
                 }
               });
            dropDownTreeViewer.setLabelProvider(new AdapterFactoryLabelProvider(this.adapterFactory));
            dropDownTreeViewer.setInput(new ItemProvider(dropDownRoots));

            if (!mappedObjects.isEmpty())
            {
              dropDownTreeViewer.setSelection(new StructuredSelection(mappedObjects.toArray()), true);
            }

            return dropDownTreeViewer.getControl();
          }

          public boolean hasLaunchedEditor(Object object, int column)
          {    
            return 
              mappingEditor.getOverviewSummaryColumnLabel() != null && 
              column == this.table.getColumnCount() - 1 && 
              mappingEditor.hasLaunchedOverviewSummaryColumnEditor(object);
          }

          public void createLaunchedEditor(Composite parent, Object object, int column)
          {
            mappingEditor.createLaunchedOverviewSummaryColumnEditor(parent, object);
          }

          public void apply()
          {
            if (dropDownTreeViewer == null)
            {
              return;
            }

            MappingRoot mappingRoot = mappingDomain.getMappingRoot();

            final Collection selection = new ArrayList(((IStructuredSelection)dropDownTreeViewer.getSelection()).toList());
            if (mapping == null)
            {
              if (!selection.isEmpty())
              {
                selection.add(currentTableTreeItem.getData());
                Command command = CreateMappingCommand.create(mappingDomain, selection);
                mappingDomain.getCommandStack().execute(command);
              }
            }
            else if (!selection.isEmpty()) 
            {
              boolean flip = !showTopFirst.isChecked() != mappingDomain.getMappingRoot().isTopToBottom();
              final Collection inputs = flip ? selection : mapping.getInputs();
              final Collection outputs = flip ? mapping.getOutputs() : selection;
              if (!inputs.containsAll(mapping.getInputs()) || !mapping.getInputs().containsAll(inputs) ||
                    !outputs.containsAll(mapping.getOutputs()) || !mapping.getOutputs().containsAll(outputs))
              {
                Command command = 
                  new CommandWrapper
                    ((mappingDomain.getMappingEnablementFlags() & MappingDomain.ENABLE_UNMAPPED_PARENTS) == 0 ?
                      RemoveCommand.create(mappingDomain, Collections.singleton(mapping)) :
                      RemoveMappingCommand.create(mappingDomain, Collections.singleton(mapping)))
                  {
                    protected Command createCommand;

                    protected boolean prepare()
                    {
                      boolean result = super.prepare() && mappingDomain.getMappingRoot().canCreateMapping(inputs, outputs, mapping);
                      return result;
                    }
                    public void execute()
                    {
                      super.execute();
                      createCommand = CreateMappingCommand.create(mappingDomain, inputs, outputs);
                      createCommand.execute();
                    }

                    public void undo()
                    {
                      createCommand.undo();
                      super.undo();
                    }
                    public void redo()
                    {
                      super.redo();
                      createCommand.redo();
                    }

                    public Collection getResult()
                    {
                      return createCommand.getResult();
                    }

                    public Collection getAffectedObjcts()
                    {
                      return createCommand.getAffectedObjects();
                    }

                    public void dispose()
                    {
                      super.dispose();
                      if (createCommand != null)
                      {
                        createCommand.dispose();
                      }
                    }
                  };
                mappingDomain.getCommandStack().execute(command);
              }
            }

            super.apply();

            dropDownTreeViewer = null;
          }
        };
    }

    public void cancelEditing()
    {
      super.cancelEditing();
      tableTreeEditor.dismiss();
    }

    public boolean isPrimaryMappedObject(MappingRoot mappingRoot, Object object)
    {
      return
        !showTopFirst.isChecked() == mappingRoot.isTopToBottom() ?
          mappingRoot.isInputObject(object) :
          mappingRoot.isOutputObject(object);
    }

    public boolean isSecondaryMappedObject(MappingRoot mappingRoot, Object object)
    {
      return
        !showTopFirst.isChecked() != mappingRoot.isTopToBottom() ?
          mappingRoot.isInputObject(object) :
          mappingRoot.isOutputObject(object);
    }

    public List getPrimaryMappedObjects(Mapping mapping)
    {
      return
        !showTopFirst.isChecked() == mappingDomain.getMappingRoot().isTopToBottom() ?
          mapping.getInputs() : 
          mapping.getOutputs();
    }

    public List getSecondaryMappedObjects(Mapping mapping)
    {
      return
        !showTopFirst.isChecked() != mappingDomain.getMappingRoot().isTopToBottom() ?
          mapping.getInputs() : 
          mapping.getOutputs();
    }

    protected Collection expandedObjects = new HashSet();
    protected Collection selectedObjects = new HashSet();

    public void preserveState()
    {
      Collection oldExpandedObjects = expandedObjects;
      expandedObjects = new HashSet(Arrays.asList(getExpandedElements()));
      oldExpandedObjects.removeAll(expandedObjects);
      for (Iterator i = oldExpandedObjects.iterator(); i.hasNext(); )
      {
        Object oldExpandedObject = i.next();
        Widget item = findItem(oldExpandedObject);
        if (item == null)
        {
          expandedObjects.add(oldExpandedObject);
        }
      }

      Collection oldSelectedObjects = selectedObjects;
      selectedObjects = new HashSet();
      MappingRoot mappingRoot = mappingDomain.getMappingRoot();
      for (Iterator i = ((IStructuredSelection)super.getSelection()).iterator(); i.hasNext(); )
      {
        Object selectedObject = i.next();
        selectedObjects.add(selectedObject);
        for (Iterator mappings = mappingRoot.getMappings(selectedObject).iterator(); mappings.hasNext(); )
        {
          Mapping mapping = (Mapping)mappings.next();
          selectedObjects.addAll(mapping.getInputs().contains(selectedObject) ? mapping.getOutputs() : mapping.getInputs());
        }
      }
      if (selectedObjects.isEmpty())
      {
        selectedObjects = oldSelectedObjects;
      }
    }

    public void restoreState()
    {
      setExpandedElements(expandedObjects.toArray());
      this.setSelection(new StructuredSelection(selectedObjects.toArray()), true);
    }

    public void init()
    {
      tableTree.removeAll();

      TableColumn [] columns = table.getColumns();
      for (int i = 0; i < columns.length; ++i)
      {
        columns[i].dispose();
      }

      TableLayout layout = new TableLayout();
      table.setLayout(layout);
      // oldWidth = table.getClientArea().width;

      TableColumn objectColumn = new TableColumn(table, SWT.NONE);
      objectColumn.addControlListener(controlListener);
      layout.addColumnData(new ColumnWeightData(2, true));
      objectColumn.setText(!showTopFirst.isChecked() ? mappingEditor.getTopLabel() : mappingEditor.getBottomLabel());
      objectColumn.setResizable(true);

      if (multipleColumns != null)
      {
        Collection secondaryMappedObjects = getSecondaryMappedObjects(mappingDomain.getMappingRoot());
        multipleColumns.setEnabled
          (secondaryMappedObjects.size() > 1 || 
            secondaryMappedObjects.size() == 1 && mappingDomain.getChildren(secondaryMappedObjects.iterator().next()).size() > 1);
        if (!multipleColumns.isEnabled())
        {
          multipleColumns.setChecked(false);
        }
      }
    
      String summaryColumnLabel = mappingEditor.getOverviewSummaryColumnLabel();

      if (multipleColumns == null || !multipleColumns.isChecked())
      {
        TableColumn otherColumn = new TableColumn(table, SWT.NONE);
        otherColumn.addControlListener(controlListener);
        layout.addColumnData(new ColumnWeightData(2, true));
        otherColumn.setText(showTopFirst.isChecked() ? mappingEditor.getTopLabel() : mappingEditor.getBottomLabel());
        otherColumn.setResizable(true);
        if (summaryColumnLabel != null)
        {
          TableColumn summaryColumn = new TableColumn(table, SWT.NONE);
          summaryColumn.addControlListener(controlListener);
          layout.addColumnData(new ColumnWeightData(2, true));
          summaryColumn.setText(summaryColumnLabel);
          summaryColumn.setResizable(true);
          setColumnProperties(new String [] {"a", "b", "c"});
        }
        else
        {
          setColumnProperties(new String [] {"a", "b"});
        }
      }
      else
      {
        Collection properties = new ArrayList();
        Collection secondaryMappedObjectsCollection = getSecondaryMappedObjects(mappingDomain.getMappingRoot());
        if (secondaryMappedObjectsCollection.size() == 1)
        {
          secondaryMappedObjectsCollection = mappingDomain.getChildren(secondaryMappedObjectsCollection.iterator().next());
        }
        int count = 0;
        for (Iterator secondaryMappedObjects = secondaryMappedObjectsCollection.iterator(); secondaryMappedObjects.hasNext(); )
        {
          Object mappedObject = secondaryMappedObjects.next();

          TableColumn mappedObjectColumn = new TableColumn(table, SWT.NONE);
          mappedObjectColumn.addControlListener(controlListener);
          layout.addColumnData(new ColumnWeightData(2, true));

          String text = ((ILabelProvider)getLabelProvider()).getText(mappedObject);

          mappedObjectColumn.setText(text);
          mappedObjectColumn.setResizable(true);

          properties.add(text);
        }

        if (summaryColumnLabel != null)
        {
          properties.add("summaryColumnLabel");
          TableColumn summaryColumn = new TableColumn(table, SWT.NONE);
          summaryColumn.addControlListener(controlListener);
          layout.addColumnData(new ColumnWeightData(2, true));
          summaryColumn.setText(summaryColumnLabel);
          summaryColumn.setResizable(true);
        }
        setColumnProperties((String [])properties.toArray(new String [properties.size()]));
        setColumnProperties((String [])properties.toArray(new String [properties.size()]));
      }

      table.layout();
    }

    public void setAdapterFactory(AdapterFactory adapterFactory)
    {
      this.adapterFactory = adapterFactory;
    }

    void makeContributions(IMenuManager menuManager, IToolBarManager toolBarManager, IStatusLineManager statusLineManager)
    {
      showTopFirst = 
        new Action
          ("",
           MappingUIPlugin.getPlugin().getImageDescriptor("full/elcl16/ExchangeSourceAndTarget"))
        {
          public void run()
          {
            getControl().setVisible(false);
            dismissCellEditor();
            preserveState();
            init();
            setInput(new ItemProvider(getPrimaryMappedObjects(mappingDomain.getMappingRoot())));
            restoreState();
            getControl().setVisible(true);
          }

          public void setChecked(boolean checked)
          {
            super.setChecked(checked);
            setToolTipText
              (MessageFormat.format
                (MappingUIPlugin.getPlugin().getString("_UI_ShowTopFirst_description"),
                  new String [] { checked ? mappingEditor.getTopLabel() : mappingEditor.getBottomLabel() }));

            setText
              (MessageFormat.format
                 (MappingUIPlugin.getPlugin().getString("_UI_ShowTopFirst_menu_item"), 
                  new String [] { checked ? mappingEditor.getTopLabel() : mappingEditor.getBottomLabel() }));

            mappingEditor.overviewViewerPane.setTitle
              (MappingUIPlugin.getPlugin().getString("_UI_Overview_label"), 
                 !checked ?
                   MappingUIPlugin.getPlugin().getImage("full/cview16/OverviewLogo") :
                   MappingUIPlugin.getPlugin().getImage("full/cview16/OverviewLogoFlipped"));
          }
        };
      showTopFirst.setChecked(!mappingEditor.getDefaultCheckedShowTopFirst());
      showTopFirst.setHoverImageDescriptor(MappingUIPlugin.getPlugin().getImageDescriptor("full/clcl16/ExchangeSourceAndTarget"));
      showTopFirst.setDisabledImageDescriptor(MappingUIPlugin.getPlugin().getImageDescriptor("full/dlcl16/ExchangeSourceAndTarget"));
      toolBarManager.add(showTopFirst);
      menuManager.add(showTopFirst);

      multipleColumns = 
        new Action
          (MappingUIPlugin.getPlugin().getString("_UI_ShowMultipleColumns_menu_item"), 
           MappingUIPlugin.getPlugin().getImageDescriptor("full/elcl16/ShowMultipleTopsOrBottoms"))
        {
          public void run()
          {
            getControl().setVisible(false);
            preserveState();
            dismissCellEditor();
            init();
            setInput(getInput());
            restoreState();
            getControl().setVisible(true);
          }
          public void setChecked(boolean checked)
          {
            super.setChecked(checked);
            setToolTipText
              (MappingUIPlugin.getPlugin().getString
                (checked ? 
                   "_UI_ShowMultipleColumns_checked_description" :
                   "_UI_ShowMultipleColumns_unchecked_description"));
          }
        };
      multipleColumns.setChecked(mappingEditor.getDefaultShowMultipleColumns());
      Collection secondaryMappedObjects = getSecondaryMappedObjects(mappingDomain.getMappingRoot());
      multipleColumns.setEnabled
        (secondaryMappedObjects.size() > 1 || 
          secondaryMappedObjects.size() == 1 && mappingDomain.getChildren(secondaryMappedObjects.iterator().next()).size() > 1);
      multipleColumns.setHoverImageDescriptor(MappingUIPlugin.getPlugin().getImageDescriptor("full/clcl16/ShowMultipleTopsOrBottoms"));
      multipleColumns.setDisabledImageDescriptor(MappingUIPlugin.getPlugin().getImageDescriptor("full/dlcl16/ShowMultipleTopsOrBottoms"));
      toolBarManager.add(multipleColumns);
      menuManager.add(multipleColumns);

      filterUnmappedObjects = 
        new Action
          (MappingUIPlugin.getPlugin().getString("_UI_FilterUnmappedObjects_menu_item"), 
           MappingUIPlugin.getPlugin().getImageDescriptor("full/elcl16/ShowOnlyMappedObjects"))
        {
          public void run()
          {
            preserveState();
            dismissCellEditor();
            setInput(getInput());
            restoreState();
          }
          public void setChecked(boolean checked)
          {
            super.setChecked(checked);
            setToolTipText
              (MappingUIPlugin.getPlugin().getString
                (checked ? 
                   "_UI_FilterUnmappedObjects_checked_description" :
                   "_UI_FilterUnmappedObjects_unchecked_description"));
          }
        };
      filterUnmappedObjects.setChecked(mappingEditor.getDefaultFilterUnmappedObjects());
      filterUnmappedObjects.setHoverImageDescriptor(MappingUIPlugin.getPlugin().getImageDescriptor("full/clcl16/ShowOnlyMappedObjects"));
      filterUnmappedObjects.setDisabledImageDescriptor(MappingUIPlugin.getPlugin().getImageDescriptor("full/dlcl16/ShowOnlyMappedObjects"));
      toolBarManager.add(filterUnmappedObjects);
      menuManager.add(filterUnmappedObjects);

      toolBarManager.update(true);
      menuManager.update(true);

      init();
    }

    public Action getFilterUnmappedObjectsAction()
    {
      return filterUnmappedObjects;
    }

    public Action getMultipleColumnsAction()
    {
      return multipleColumns;
    }

    public Action getShowTopFirstAction()
    {
      return showTopFirst;
    }

    public ISelection getSelection()
    {
      IStructuredSelection theSelection = (IStructuredSelection)super.getSelection();
      List result = new ArrayList();
      for (Iterator objects = theSelection.iterator(); objects.hasNext(); )
      {
        Object object = objects.next();
        result.addAll(mappingDomain.getMappingRoot().getMappings(object));
      }

      return result.isEmpty() ? theSelection : new StructuredSelection(result.toArray());
    }

    public void dismissCellEditor()
    {
      tableTreeEditor.dismiss();
    }

    public void refreshCell()
    {
      if (tableTreeEditor.getEditor() != null)
      {
        tableTreeEditor.getEditor().redraw();
      }
    }

    /**
     * This override ensures the objects which aren't in the view don't cause a failure.
     */
    protected void setSelectionToWidget(List list, boolean reveal)
    {
      List filteredSelection = new ArrayList();
      if (list != null)
      {
        filteredSelection.addAll(list);
        for (Iterator i = filteredSelection.iterator(); i.hasNext(); )
        {
          Object selectedObject = i.next();
          if (findItem(selectedObject) == null && internalExpand(selectedObject, false) == null)
          {
            i.remove();
          }
        }
      }

      super.setSelectionToWidget(filteredSelection, reveal);
    }
  }
}

class DelayedColumnFitter extends ControlAdapter
{
  protected Table table;
  protected DelayedLayout delayedLayout;
  protected int columnResizeTime;
  protected int oldWidth;
  protected boolean inLayout;

  public void controlResized(ControlEvent event)
  {
    if (event.getSource() instanceof Table)
    {
      table = (Table)event.getSource();
      if (delayedLayout == null)
      {
        delayedLayout = new DelayedLayout(event.time);
      }
    }
    else
    {
      if (!inLayout)
      {
        columnResizeTime = event.time;
      }
    }
  }

  protected class DelayedLayout implements Runnable
  {
    protected TableLayout layout;
    protected int newWidth;
    protected boolean ignore;

    public DelayedLayout(int time)
    {
      newWidth = table.getClientArea().width;
      if (oldWidth != newWidth && oldWidth != 0)
      {
        layout = new TableLayout();
        TableColumn [] tableColumns = table.getColumns();
        for (int i = 0; i < tableColumns.length; ++i)
        {
          layout.addColumnData(new ColumnWeightData(tableColumns[i].getWidth(), true));
        }

        if (columnResizeTime != 0 && time - columnResizeTime < 500)
        {
          ignore = true;
        }
      }
      else
      {
        ignore = true;
      }

      oldWidth = newWidth;
      columnResizeTime = 0;

      table.getDisplay().asyncExec(this);
    }

    public void run()
    {
      delayedLayout = null;
      if (!table.isDisposed() && !ignore)
      {
        columnResizeTime = 0;
        table.setLayout(layout);
        inLayout = true;
        table.layout();
        inLayout = false;
/*
        if (delayedLayout != null)
        {
          System.out.println("Layout causes a layout!!!!!");
        }
*/
      }
    }
  } 
}

