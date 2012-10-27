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
package org.eclipse.emf.ecore.presentation;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.eclipse.jface.action.Action;
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
import org.eclipse.jface.window.Window;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.dialogs.ElementListSelectionDialog;

import org.eclipse.emf.common.ui.action.ViewerFilterAction;
import org.eclipse.emf.common.ui.dialogs.DiagnosticDialog;
import org.eclipse.emf.common.ui.viewer.IViewerProvider;
import org.eclipse.emf.common.util.BasicDiagnostic;
import org.eclipse.emf.common.util.Diagnostic;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.TreeIterator;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EGenericType;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.ETypeParameter;
import org.eclipse.emf.ecore.EcorePackage;
import org.eclipse.emf.ecore.EPackage.Registry;
import org.eclipse.emf.ecore.plugin.EcorePlugin;
import org.eclipse.emf.ecore.provider.EcoreEditPlugin;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.edit.command.CommandParameter;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.emf.edit.domain.IEditingDomainProvider;
import org.eclipse.emf.edit.ui.action.ControlAction;
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
      super();
    }

    @Override
    protected IMenuManager createSubmenuManager()
    {
      return new MenuManager(EcoreEditorPlugin.getPlugin().getString("_UI_ReflectiveEditor_menu"), "org.eclipse.emf.ecoreMenuID");
    }
  }
  
  public static class ExtendedLoadResourceAction extends LoadResourceAction
  {
    @Override
    public void run()
    {
      Shell shell = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell();
      ExtendedLoadResourceDialog loadResourceDialog =
        new ExtendedLoadResourceDialog
          (shell, domain);
    
      if (loadResourceDialog.open() == Window.OK && !loadResourceDialog.getRegisteredPackages().isEmpty())
      {
        String source = EcoreEditorPlugin.INSTANCE.getSymbolicName();
        BasicDiagnostic diagnosic = 
          new BasicDiagnostic(Diagnostic.INFO, source, 0, EcoreEditorPlugin.INSTANCE.getString("_UI_RuntimePackageDetail_message"), null);
        for (EPackage ePackage : loadResourceDialog.getRegisteredPackages())
        {
          diagnosic.add(new BasicDiagnostic(Diagnostic.INFO, source, 0, ePackage.getNsURI(), null));
        }
        new DiagnosticDialog
         (shell, 
          EcoreEditorPlugin.INSTANCE.getString("_UI_Information_title"), 
          EcoreEditorPlugin.INSTANCE.getString("_UI_RuntimePackageHeader_message"),
          diagnosic,
          Diagnostic.INFO).open();
      }
    }

    public static class ExtendedLoadResourceDialog extends LoadResourceDialog
    {
      protected Set<EPackage> registeredPackages = new LinkedHashSet<EPackage>();

      public ExtendedLoadResourceDialog(Shell parent, EditingDomain domain)
      {
        super(parent, domain);
      }
      
      @Override
      protected boolean processResource(Resource resource)
      {
        // Put all static package in the package registry.
        //
        ResourceSet resourceSet = domain.getResourceSet();
        if (!resourceSet.getResources().contains(resource))
        {
          Registry packageRegistry = resourceSet.getPackageRegistry();
          for (EPackage ePackage : getAllPackages(resource))
          {
            packageRegistry.put(ePackage.getNsURI(), ePackage);
            registeredPackages.add(ePackage);
          }
        }
        return true;
      }

      public Set<EPackage> getRegisteredPackages()
      {
        return registeredPackages;
      }

      protected Collection<EPackage> getAllPackages(Resource resource)
      {
        List<EPackage> result = new ArrayList<EPackage>();
        for (TreeIterator<?> j = 
               new EcoreUtil.ContentTreeIterator<Object>(resource.getContents())
               {
                 private static final long serialVersionUID = 1L;
  
                 @Override
                 protected Iterator<? extends EObject> getEObjectChildren(EObject eObject)
                 {
                   return 
                     eObject instanceof EPackage ? 
                       ((EPackage)eObject).getESubpackages().iterator() : 
                         Collections.<EObject>emptyList().iterator();
                 }
               };
             j.hasNext(); )
        {
          Object content = j.next();
          if (content instanceof EPackage)
          {
            result.add((EPackage)content);
          }
        }
        return result;
      }

      @Override
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

        Button browseTargetPlatformPackagesButton = new Button(buttonComposite, SWT.PUSH);
        browseTargetPlatformPackagesButton.setText(EcoreEditorPlugin.INSTANCE.getString("_UI_BrowseTargetPlatformPackages_label"));
        prepareBrowseTargetPlatformPackagesButton(browseTargetPlatformPackagesButton);
        {
          FormData data = new FormData();
          data.right = new FormAttachment(browseRegisteredPackagesButton, -CONTROL_OFFSET);
          browseTargetPlatformPackagesButton.setLayoutData(data);
        }
        
        return composite;
      }

      /**
       * @since 2.9
       */
      protected void prepareBrowseTargetPlatformPackagesButton(Button browseTargetPlatformPackagesButton)
      {
        browseTargetPlatformPackagesButton.addSelectionListener
          (new SelectionAdapter()
           {
             @Override
             public void widgetSelected(SelectionEvent event)
             {
               TargetPlatformPackageDialog classpathPackageDialog = new TargetPlatformPackageDialog(getShell());
               classpathPackageDialog.open();
               Object [] result = classpathPackageDialog.getResult();
               if (result != null)
               {
                 List<?> nsURIs = Arrays.asList(result);
                 ResourceSet resourceSet = new ResourceSetImpl();
                 // resourceSet.getURIConverter().getURIMap().putAll(EcorePlugin.computePlatformURIMap(domain.getResourceSet().getResources().get(0).getURI()));
                 resourceSet.getURIConverter().getURIMap().putAll(EcorePlugin.computePlatformURIMap(true));

                 // To support Xcore resources, we need a resource with a URI that helps determine the containing project
                 //
                 Resource dummyResource = resourceSet.createResource(domain.getResourceSet().getResources().get(0).getURI());

                 StringBuffer uris = new StringBuffer();
                 Map<String, URI> ePackageNsURItoGenModelLocationMap = EcorePlugin.getEPackageNsURIToGenModelLocationMap(true);
                 for (int i = 0, length = result.length; i < length; i++)
                 {
                   URI location = ePackageNsURItoGenModelLocationMap.get(result[i]);
                   Resource resource = resourceSet.getResource(location, true);
                   EcoreUtil.resolveAll(resource);
                 }

                 EList<Resource> resources = resourceSet.getResources();
                 resources.remove(dummyResource);

                 for (Resource resource : resources)
                 {
                   for (EPackage ePackage : getAllPackages(resource))
                   {
                     if (nsURIs.contains(ePackage.getNsURI()))
                     {
                       uris.append(resource.getURI());
                       uris.append("  ");
                       break;
                     }
                   }
                 }
                 uriField.setText((uriField.getText() + "  " + uris.toString()).trim());
               }
             }
           });
      }

      protected void prepareBrowseRegisteredPackagesButton(Button browseRegisteredPackagesButton)
      {
        browseRegisteredPackagesButton.addSelectionListener
          (new SelectionAdapter()
           {
             @Override
             public void widgetSelected(SelectionEvent event)
             {
               RegisteredPackageDialog registeredPackageDialog = new RegisteredPackageDialog(getShell());
               registeredPackageDialog.open();
               Object [] result = registeredPackageDialog.getResult();
               if (result != null)
               {
                 List<?> nsURIs = Arrays.asList(result);
                 if (registeredPackageDialog.isDevelopmentTimeVersion())
                 {
                   ResourceSet resourceSet = new ResourceSetImpl();
                   resourceSet.getURIConverter().getURIMap().putAll(EcorePlugin.computePlatformURIMap(false));

                   // To support Xcore resources, we need a resource with a URI that helps determine the containing project
                   //
                   Resource dummyResource = resourceSet.createResource(domain.getResourceSet().getResources().get(0).getURI());

                   StringBuffer uris = new StringBuffer();
                   Map<String, URI> ePackageNsURItoGenModelLocationMap = EcorePlugin.getEPackageNsURIToGenModelLocationMap(false);
                   for (int i = 0, length = result.length; i < length; i++)
                   {
                     URI location = ePackageNsURItoGenModelLocationMap.get(result[i]);
                     Resource resource = resourceSet.getResource(location, true);
                     EcoreUtil.resolveAll(resource);
                   }

                   EList<Resource> resources = resourceSet.getResources();
                   resources.remove(dummyResource);

                   for (Resource resource : resources)
                   {
                     for (EPackage ePackage : getAllPackages(resource))
                     {
                       if (nsURIs.contains(ePackage.getNsURI()))
                       {
                         uris.append(resource.getURI());
                         uris.append("  ");
                         break;
                       }
                     }
                   }
                   uriField.setText((uriField.getText() + "  " + uris.toString()).trim());
                 }
                 else
                 {
                   StringBuffer uris = new StringBuffer();
                   for (int i = 0, length = result.length; i < length; i++)
                   {
                     uris.append(result[i]);
                     uris.append("  ");
                   }
                   uriField.setText((uriField.getText() + "  " + uris.toString()).trim());
                 }
               }
             }
           });      
      }
    }

    /**
     * @since 2.9
     */
    public static class TargetPlatformPackageDialog extends ElementListSelectionDialog
    {
      public TargetPlatformPackageDialog(Shell parent)
      {
        super
          (parent, 
           new LabelProvider()
           {
             @Override
            public Image getImage(Object element)
             {
               return ExtendedImageRegistry.getInstance().getImage(EcoreEditPlugin.INSTANCE.getImage("full/obj16/EPackage"));
             }
           });
        
        setMultipleSelection(true);
        setMessage(EcoreEditorPlugin.INSTANCE.getString("_UI_SelectRegisteredPackageURI"));
        setFilter("*");
        setTitle(EcoreEditorPlugin.INSTANCE.getString("_UI_PackageSelection_label"));
      }

      protected void updateElements()
      {
        Map<String, URI> ePackageNsURItoGenModelLocationMap = EcorePlugin.getEPackageNsURIToGenModelLocationMap(true);
        Object [] result = ePackageNsURItoGenModelLocationMap.keySet().toArray(new Object[ePackageNsURItoGenModelLocationMap.size()]);
        Arrays.sort(result);
        setListElements(result);
      }

      @Override
      protected Control createDialogArea(Composite parent)
      {
        Composite result = (Composite)super.createDialogArea(parent);
        updateElements();
        return result;
      }
    }

    public static class RegisteredPackageDialog extends ElementListSelectionDialog
    {
      protected boolean isDevelopmentTimeVersion = true;

      public RegisteredPackageDialog(Shell parent)
      {
        super
          (parent, 
           new LabelProvider()
           {
             @Override
            public Image getImage(Object element)
             {
               return ExtendedImageRegistry.getInstance().getImage(EcoreEditPlugin.INSTANCE.getImage("full/obj16/EPackage"));
             }
           });
        
        setMultipleSelection(true);
        setMessage(EcoreEditorPlugin.INSTANCE.getString("_UI_SelectRegisteredPackageURI"));
        setFilter("*");
        setTitle(EcoreEditorPlugin.INSTANCE.getString("_UI_PackageSelection_label"));
      }
      
      public boolean isDevelopmentTimeVersion()
      {
        return isDevelopmentTimeVersion;
      }
      
      protected void updateElements()
      {
        if (isDevelopmentTimeVersion)
        {
          Map<String, URI> ePackageNsURItoGenModelLocationMap = EcorePlugin.getEPackageNsURIToGenModelLocationMap(false);
          Object [] result = ePackageNsURItoGenModelLocationMap.keySet().toArray(new Object[ePackageNsURItoGenModelLocationMap.size()]);
          Arrays.sort(result);
          setListElements(result);
        }
        else
        {
          Object [] result = EPackage.Registry.INSTANCE.keySet().toArray(new Object[EPackage.Registry.INSTANCE.size()]);
          Arrays.sort(result);
          setListElements(result);
        }
      }

      @Override
      protected Control createDialogArea(Composite parent)
      {
        Composite result = (Composite)super.createDialogArea(parent);
        Composite buttonGroup = new Composite(result, SWT.NONE);
        GridLayout layout = new GridLayout();
        layout.numColumns = 2;
        buttonGroup.setLayout(layout);
        final Button developmentTimeVersionButton = new Button(buttonGroup, SWT.RADIO);
        developmentTimeVersionButton.addSelectionListener
          (new SelectionAdapter() 
           {
             @Override
             public void widgetSelected(SelectionEvent event)
             {
               isDevelopmentTimeVersion = developmentTimeVersionButton.getSelection();
               updateElements();
             }
           });
        developmentTimeVersionButton.setText(EcoreEditorPlugin.INSTANCE.getString("_UI_DevelopmentTimeVersion_label"));
        Button runtimeTimeVersionButton = new Button(buttonGroup, SWT.RADIO);
        runtimeTimeVersionButton.setText(EcoreEditorPlugin.INSTANCE.getString("_UI_RuntimeVersion_label"));
        developmentTimeVersionButton.setSelection(true);

        updateElements();

        return result;
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
      @Override
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
   * This will contain one {@link org.eclipse.emf.edit.ui.action.CreateChildAction} corresponding to each descriptor
   * generated for the current selection by the item provider.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected Collection<IAction> createChildActions;

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
  protected Collection<IAction> createSiblingActions;

  /**
   * This is the menu manager into which menu contribution items should be added for CreateSibling actions.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected IMenuManager createSiblingMenuManager;

  protected SelectionChangedEvent lastSelectionChangedEvent;
  
  protected ViewerFilterAction showGenericsAction = 
    new ViewerFilterAction(EcoreEditorPlugin.INSTANCE.getString("_UI_ShowGenerics_menu_item"), IAction.AS_CHECK_BOX)
    {
      @Override
      protected void refreshViewers()
      {
        super.refreshViewers();
        if (lastSelectionChangedEvent != null && activeEditorPart instanceof EcoreEditor)
        {
          selectionChanged(lastSelectionChangedEvent); 
        }
      }
      
      @Override
      public boolean select(Viewer viewer, Object parentElement, Object element)
      {
        return isChecked() ||
         !(element instanceof ETypeParameter || 
           element instanceof EGenericType);
      }    
    };
  
  /**
   * This creates an instance of the contributor.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated NOT
   */
  public EcoreActionBarContributor()
  {
    super(ADDITIONS_LAST_STYLE);
    loadResourceAction = new ExtendedLoadResourceAction();
    validateAction = new ValidateAction();
    controlAction = new ControlAction();
    
    showGenericsAction.setChecked
      (Boolean.parseBoolean(EcoreEditorPlugin.getPlugin().getDialogSettings().get("showGenericsAction")));    
  }
  
  public void showGenerics(boolean isChecked)
  {
    if (showGenericsAction != null)
    {
      showGenericsAction.setChecked(isChecked);
    }
  }

  @Override
  public void dispose()
  {
    EcoreEditorPlugin.getPlugin().getDialogSettings().put(
      "showGenericsAction", Boolean.toString(showGenericsAction.isChecked()));
    
    super.dispose();
  }

  /**
   * This adds Separators for editor additions to the tool bar.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
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
  @Override
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
    submenuManager.insertBefore("additions-end", showGenericsAction);
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
  public void setActiveEditorGen(IEditorPart part)
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
  
  @Override
  public void setActiveEditor(IEditorPart part)
  {
    setActiveEditorGen(part);
    
    if (part instanceof EcoreEditor)
    {
      showGenericsAction.addViewer(((EcoreEditor)part).getViewer());
      showGenericsAction.setEnabled(true);
    }
    else
    {
      showGenericsAction.setEnabled(false);
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
  public void selectionChangedGen(SelectionChangedEvent event)
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
    Collection<?> newChildDescriptors = null;
    Collection<?> newSiblingDescriptors = null;

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
  
  public void selectionChanged(SelectionChangedEvent event)
  {
    lastSelectionChangedEvent = event;
    selectionChangedGen(event);
  }

  /**
   * This generates a {@link org.eclipse.emf.edit.ui.action.CreateChildAction} for each object in <code>descriptors</code>,
   * and returns the collection of these actions.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated NOT
   */
  protected Collection<IAction> generateCreateChildActions(Collection<?> descriptors, ISelection selection)
  {
    Collection<IAction> actions = new ArrayList<IAction>();
    if (descriptors != null)
    {
      for (Object descriptor : descriptors)
      {
        if (!showGenericsAction.isChecked() && descriptor instanceof CommandParameter)
        {
          Object feature = ((CommandParameter)descriptor).getFeature();
          if (isGenericFeature(feature))
          {
            continue;
          }
        }
        actions.add(new CreateChildAction(activeEditorPart, selection, descriptor));
      }
    }
    return actions;
  }

  /**
   * This generates a {@link org.eclipse.emf.edit.ui.action.CreateSiblingAction} for each object in <code>descriptors</code>,
   * and returns the collection of these actions.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated NOT
   */
  protected Collection<IAction> generateCreateSiblingActions(Collection<?> descriptors, ISelection selection)
  {
    Collection<IAction> actions = new ArrayList<IAction>();
    if (descriptors != null)
    {
      for (Object descriptor : descriptors)
      {
        if (!showGenericsAction.isChecked() && descriptor instanceof CommandParameter)
        {
          Object feature = ((CommandParameter)descriptor).getFeature();
          if (isGenericFeature(feature))
          {
            continue;
          }
        }
        actions.add(new CreateSiblingAction(activeEditorPart, selection, descriptor));
      }
    }
    return actions;
  }

  protected boolean isGenericFeature(Object feature)
  {
    return feature == EcorePackage.Literals.ECLASS__EGENERIC_SUPER_TYPES ||
      feature == EcorePackage.Literals.ECLASSIFIER__ETYPE_PARAMETERS ||
      feature == EcorePackage.Literals.EOPERATION__EGENERIC_EXCEPTIONS || 
      feature == EcorePackage.Literals.EOPERATION__ETYPE_PARAMETERS ||
      feature == EcorePackage.Literals.ETYPED_ELEMENT__EGENERIC_TYPE;
  }

  /**
   * This populates the specified <code>manager</code> with {@link org.eclipse.jface.action.ActionContributionItem}s
   * based on the {@link org.eclipse.jface.action.IAction}s contained in the <code>actions</code> collection,
   * by inserting them before the specified contribution item <code>contributionID</code>.
   * If <code>contributionID</code> is <code>null</code>, they are simply added.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected void populateManager(IContributionManager manager, Collection<? extends IAction> actions, String contributionID)
  {
    if (actions != null)
    {
      for (IAction action : actions)
      {
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
  protected void depopulateManager(IContributionManager manager, Collection<? extends IAction> actions)
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
  @Override
  public void menuAboutToShow(IMenuManager menuManager)
  {
    super.menuAboutToShow(menuManager);
    MenuManager submenuManager = null;

    submenuManager = new MenuManager(EcoreEditorPlugin.INSTANCE.getString("_UI_CreateChild_menu_item"));
    populateManager(submenuManager, createChildActions, null);
    menuManager.insertBefore("edit", submenuManager);

    submenuManager = new MenuManager(EcoreEditorPlugin.INSTANCE.getString("_UI_CreateSibling_menu_item"));
    populateManager(submenuManager, createSiblingActions, null);
    menuManager.insertBefore("edit", submenuManager);
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

  /**
   * This ensures that a delete action will clean up all references to deleted objects.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  protected boolean removeAllReferencesOnDelete()
  {
    return true;
  }

}
