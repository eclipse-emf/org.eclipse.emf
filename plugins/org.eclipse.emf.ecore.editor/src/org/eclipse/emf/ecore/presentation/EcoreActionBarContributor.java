/**
 * <copyright>
 *
 * Copyright (c) 2002-2006 IBM Corporation and others.
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
 * $Id: EcoreActionBarContributor.java,v 1.9 2006/02/14 19:38:04 emerks Exp $
 */
package org.eclipse.emf.ecore.presentation;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.eclipse.jface.action.ActionContributionItem;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.action.IContributionItem;
import org.eclipse.jface.action.IContributionManager;
import org.eclipse.jface.action.IMenuListener;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.action.Separator;
import org.eclipse.jface.action.SubContributionItem;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.ISelectionProvider;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.jface.viewers.LabelProvider;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.Viewer;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.PlatformUI;

import org.eclipse.ui.PartInitException;
import org.eclipse.ui.dialogs.ElementListSelectionDialog;

import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.plugin.EcorePlugin;
import org.eclipse.emf.ecore.provider.EcoreEditPlugin;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.emf.edit.domain.IEditingDomainProvider;
import org.eclipse.emf.edit.ui.action.ControlAction;
import org.eclipse.emf.common.ui.viewer.IViewerProvider;
import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.common.util.URI;

import org.eclipse.jface.action.Action;
import org.eclipse.emf.edit.ui.action.CreateChildAction;
import org.eclipse.emf.edit.ui.action.CreateSiblingAction;
import org.eclipse.emf.edit.ui.action.EditingDomainActionBarContributor;
import org.eclipse.emf.edit.ui.action.LoadResourceAction;
import org.eclipse.emf.edit.ui.action.ValidateAction;
import org.eclipse.emf.edit.ui.provider.ExtendedImageRegistry;


/**
 * This is the action bar contributor for the Ecore model editor.
 * <!-- begin-user-doc -->
 * <!-- end-user-doc -->
 * @generated
 */
public class EcoreActionBarContributor
  extends EditingDomainActionBarContributor
  implements ISelectionChangedListener
{
  public static class Reflective extends EcoreActionBarContributor
  {
    public Reflective()
    {
    }

    protected IMenuManager createSubmenuManager()
    {
      return new MenuManager(EcoreEditorPlugin.getPlugin().getString("_UI_ReflectiveEditor_menu"), "org.eclipse.emf.ecoreMenuID");
    }
  }
  
  public static class ExtendedLoadResourceAction extends LoadResourceAction
  {
    public void run()
    {
      ExtendedLoadResourceDialog loadResourceDialog =
        new ExtendedLoadResourceDialog
          (PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell(), domain);
    
      loadResourceDialog.open();
    }
    
    public static class ExtendedLoadResourceDialog extends LoadResourceDialog
    {
      public ExtendedLoadResourceDialog(Shell parent, EditingDomain domain)
      {
        super(parent, domain);
      }

      protected Control createDialogArea(Composite parent)
      {
        Composite composite = (Composite)super.createDialogArea(parent);
        Composite buttonComposite = (Composite)composite.getChildren()[0];
        Button browseRegisteredPackagesButton = new Button(buttonComposite, SWT.PUSH);
        browseRegisteredPackagesButton.setText(EcoreEditorPlugin.INSTANCE.getString("_UI_BrowseRegisteredPackages_label"));
        prepareBrowseRegisteredPackagesButton(browseRegisteredPackagesButton);
        {
          FormData data = new FormData();
          Control [] children = buttonComposite.getChildren();
          data.right = new FormAttachment(children[0], -CONTROL_OFFSET);
          browseRegisteredPackagesButton.setLayoutData(data);
        }
        return composite;
      }

      protected void prepareBrowseRegisteredPackagesButton(Button browseRegisteredPackagesButton)
      {
        browseRegisteredPackagesButton.addSelectionListener
          (new SelectionAdapter()
           {
             public void widgetSelected(SelectionEvent event)
             {
               RegisteredPackageDialog registeredPackageDialog = new RegisteredPackageDialog(getShell());
               registeredPackageDialog.open();
               Object [] result = registeredPackageDialog.getResult();
               if (result != null)
               {
                 List nsURIs = Arrays.asList(result);
                 ResourceSet resourceSet = new ResourceSetImpl();
                 resourceSet.getURIConverter().getURIMap().putAll(EcorePlugin.computePlatformURIMap());
                 StringBuffer uris = new StringBuffer();
                 Map ePackageNsURItoGenModelLocationMap = EcorePlugin.getEPackageNsURIToGenModelLocationMap();
                 for (int i = 0, length = result.length; i < length; i++)
                 {
                   URI location = (URI)ePackageNsURItoGenModelLocationMap.get(result[i]);
                   Resource resource = resourceSet.getResource(location, true);
                   EcoreUtil.resolveAll(resource);
                 }
                 for (Iterator i = resourceSet.getResources().iterator(); i.hasNext(); )
                 {
                   Resource resource = (Resource)i.next();
                   for (TreeIterator j = 
                          new EcoreUtil.ContentTreeIterator(resource.getContents())
                          {
                            protected Iterator getEObjectChildren(EObject eObject)
                            {
                              return 
                                eObject instanceof EPackage ? 
                                  ((EPackage)eObject).getESubpackages().iterator() : 
                                    Collections.EMPTY_LIST.iterator();
                            }
                          };
                        j.hasNext(); )
                   {
                     Object content = j.next();
                     if (content instanceof EPackage)
                     {
                       EPackage ePackage = (EPackage)content;
                       if (nsURIs.contains(ePackage.getNsURI()))
                       {
                         uris.append(resource.getURI());
                         uris.append("  ");
                        break;
                       }
                     }
                   }
                 }
                 uriField.setText((uriField.getText() + "  " + uris.toString()).trim());
               }
             }
           });      
      }
    }
    
    public static class RegisteredPackageDialog extends ElementListSelectionDialog
    {
      public RegisteredPackageDialog(Shell parent)
      {
        super
          (parent, 
           new LabelProvider()
           {
             public Image getImage(Object element)
             {
               return ExtendedImageRegistry.getInstance().getImage(EcoreEditPlugin.INSTANCE.getImage("full/obj16/EPackage"));
             }
           });
        
        setMultipleSelection(true);
        setMessage(EcoreEditorPlugin.INSTANCE.getString("_UI_SelectRegisteredPackageURI"));
        setFilter("*");
        Map ePackageNsURItoGenModelLocationMap = EcorePlugin.getEPackageNsURIToGenModelLocationMap();
        Object [] result = ePackageNsURItoGenModelLocationMap.keySet().toArray(new Object[ePackageNsURItoGenModelLocationMap.size()]);
        Arrays.sort(result);
        setElements(result);
        setTitle(EcoreEditorPlugin.INSTANCE.getString("_UI_PackageSelection_label"));
      }
    }
  }

  /**
   * This keeps track of the active editor.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected IEditorPart activeEditorPart;

  /**
   * This keeps track of the current selection provider.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected ISelectionProvider selectionProvider;

  /**
   * This action opens the Properties view.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected IAction showPropertiesViewAction =
    new Action(EcoreEditorPlugin.INSTANCE.getString("_UI_ShowPropertiesView_menu_item"))
    {
      public void run()
      {
        try
        {
          getPage().showView("org.eclipse.ui.views.PropertySheet");
        }
        catch (PartInitException exception)
        {
          EcoreEditorPlugin.INSTANCE.log(exception);
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
    new Action(EcoreEditorPlugin.INSTANCE.getString("_UI_RefreshViewer_menu_item"))
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
   * This will contain one {@link org.eclipse.emf.edit.ui.action.CreateChildAction} corresponding to each descriptor
   * generated for the current selection by the item provider.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected Collection createChildActions;

  /**
   * This is the menu manager into which menu contribution items should be added for CreateChild actions.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected IMenuManager createChildMenuManager;

  /**
   * This will contain one {@link org.eclipse.emf.edit.ui.action.CreateSiblingAction} corresponding to each descriptor
   * generated for the current selection by the item provider.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected Collection createSiblingActions;

  /**
   * This is the menu manager into which menu contribution items should be added for CreateSibling actions.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected IMenuManager createSiblingMenuManager;

  /**
   * This creates an instance of the contributor.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated NOT
   */
  public EcoreActionBarContributor()
  {
    loadResourceAction = new ExtendedLoadResourceAction();
    validateAction = new ValidateAction();
    controlAction = new ControlAction();
  }

  /**
   * This adds Separators for editor additions to the tool bar.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void contributeToToolBar(IToolBarManager toolBarManager)
  {
    toolBarManager.add(new Separator("ecore-settings"));
    toolBarManager.add(new Separator("ecore-additions"));
  }

  /**
   * This adds to the menu bar a menu and some separators for editor additions,
   * as well as the sub-menus for object creation items.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated NOT
   */
  public void contributeToMenu(IMenuManager menuManager)
  {
    super.contributeToMenu(menuManager);

    IMenuManager submenuManager = createSubmenuManager();

    menuManager.insertAfter("additions", submenuManager);
    submenuManager.add(new Separator("settings"));
    submenuManager.add(new Separator("actions"));
    submenuManager.add(new Separator("additions"));
    submenuManager.add(new Separator("additions-end"));

    // Prepare for CreateChild item addition or removal.
    //
    createChildMenuManager = new MenuManager(EcoreEditorPlugin.getPlugin().getString("_UI_CreateChild_menu_item"));
    submenuManager.insertBefore("additions", createChildMenuManager);

    // Prepare for CreateSibling item addition or removal.
    //
    createSiblingMenuManager = new MenuManager(EcoreEditorPlugin.getPlugin().getString("_UI_CreateSibling_menu_item"));
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

    addGlobalActions(submenuManager);
  }

  protected IMenuManager createSubmenuManager()
  {
    return new MenuManager(EcoreEditorPlugin.getPlugin().getString("_UI_EcoreEditor_menu"), "org.eclipse.emf.ecoreMenuID");
  }

  /**
   * When the active editor changes, this remembers the change and registers with it as a selection provider.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
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
   * This implements {@link org.eclipse.jface.viewers.ISelectionChangedListener},
   * handling {@link org.eclipse.jface.viewers.SelectionChangedEvent}s by querying for the children and siblings
   * that can be added to the selected object and updating the menus accordingly.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void selectionChanged(SelectionChangedEvent event)
  {
    // Remove any menu items for old selection.
    //
    if (createChildMenuManager != null)
    {
      depopulateManager(createChildMenuManager, createChildActions);
    }
    if (createSiblingMenuManager != null)
    {
      depopulateManager(createSiblingMenuManager, createSiblingActions);
    }

    // Query the new selection for appropriate new child/sibling descriptors
    //
    Collection newChildDescriptors = null;
    Collection newSiblingDescriptors = null;

    ISelection selection = event.getSelection();
    if (selection instanceof IStructuredSelection && ((IStructuredSelection)selection).size() == 1)
    {
      Object object = ((IStructuredSelection)selection).getFirstElement();

      EditingDomain domain = ((IEditingDomainProvider)activeEditorPart).getEditingDomain();

      newChildDescriptors = domain.getNewChildDescriptors(object, null);
      newSiblingDescriptors = domain.getNewChildDescriptors(null, object);
    }

    // Generate actions for selection; populate and redraw the menus.
    //
    createChildActions = generateCreateChildActions(newChildDescriptors, selection);
    createSiblingActions = generateCreateSiblingActions(newSiblingDescriptors, selection);

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
   * This generates a {@link org.eclipse.emf.edit.ui.action.CreateChildAction} for each object in <code>descriptors</code>,
   * and returns the collection of these actions.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected Collection generateCreateChildActions(Collection descriptors, ISelection selection)
  {
    Collection actions = new ArrayList();
    if (descriptors != null)
    {
      for (Iterator i = descriptors.iterator(); i.hasNext(); )
      {
        actions.add(new CreateChildAction(activeEditorPart, selection, i.next()));
      }
    }
    return actions;
  }

  /**
   * This generates a {@link org.eclipse.emf.edit.ui.action.CreateSiblingAction} for each object in <code>descriptors</code>,
   * and returns the collection of these actions.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected Collection generateCreateSiblingActions(Collection descriptors, ISelection selection)
  {
    Collection actions = new ArrayList();
    if (descriptors != null)
    {
      for (Iterator i = descriptors.iterator(); i.hasNext(); )
      {
        actions.add(new CreateSiblingAction(activeEditorPart, selection, i.next()));
      }
    }
    return actions;
  }

  /**
   * This populates the specified <code>manager</code> with {@link org.eclipse.jface.action.ActionContributionItem}s
   * based on the {@link org.eclipse.jface.action.IAction}s contained in the <code>actions</code> collection,
   * by inserting them before the specified contribution item <code>contributionID</code>.
   * If <code>ID</code> is <code>null</code>, they are simply added.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected void populateManager(IContributionManager manager, Collection actions, String contributionID)
  {
    if (actions != null)
    {
      for (Iterator i = actions.iterator(); i.hasNext(); )
      {
        IAction action = (IAction)i.next();
        if (contributionID != null)
        {
          manager.insertBefore(contributionID, action);
        }
        else
        {
          manager.add(action);
        }
      }
    }
  }
    
  /**
   * This removes from the specified <code>manager</code> all {@link org.eclipse.jface.action.ActionContributionItem}s
   * based on the {@link org.eclipse.jface.action.IAction}s contained in the <code>actions</code> collection.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected void depopulateManager(IContributionManager manager, Collection actions)
  {
    if (actions != null)
    {
      IContributionItem[] items = manager.getItems();
      for (int i = 0; i < items.length; i++)
      {
        // Look into SubContributionItems
        //
        IContributionItem contributionItem = items[i];
        while (contributionItem instanceof SubContributionItem)
        {
          contributionItem = ((SubContributionItem)contributionItem).getInnerItem();
        }

        // Delete the ActionContributionItems with matching action.
        //
        if (contributionItem instanceof ActionContributionItem)
        {
          IAction action = ((ActionContributionItem)contributionItem).getAction();
          if (actions.contains(action))
          {
            manager.remove(contributionItem);
          }
        }
      }
    }
  }

  /**
   * This populates the pop-up menu before it appears.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void menuAboutToShow(IMenuManager menuManager)
  {
    super.menuAboutToShow(menuManager);
    MenuManager submenuManager = null;

    submenuManager = new MenuManager(EcoreEditorPlugin.INSTANCE.getString("_UI_CreateChild_menu_item"));
    populateManager(submenuManager, createChildActions, null);
    menuManager.insertBefore("additions", submenuManager);

    submenuManager = new MenuManager(EcoreEditorPlugin.INSTANCE.getString("_UI_CreateSibling_menu_item"));
    populateManager(submenuManager, createSiblingActions, null);
    menuManager.insertBefore("additions", submenuManager);
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

  /**
   * This ensures that a delete action will clean up all references to deleted objects.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected boolean removeAllReferencesOnDelete()
  {
    return true;
  }

}
