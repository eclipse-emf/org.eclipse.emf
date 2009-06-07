/**
 * <copyright>
 *
 * Copyright (c) 2009 Bestsolution.at and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Common Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/cpl-v10.html
 * 
 * Contributors: 
 *   Tom Schindl<tom.schindl@bestsolution.at> - Initial API and implementation
 *
 * </copyright>
 *
 * $Id: ProjectAdminViewPart.java,v 1.10 2009/06/07 13:58:56 tschindl Exp $
 */
package org.eclipse.emf.example.databinding.project.ui.rcp.views;

import org.eclipse.core.databinding.ObservablesManager;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.IStatus;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.SashForm;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.IMemento;
import org.eclipse.ui.IPartListener2;
import org.eclipse.ui.ISaveablePart2;
import org.eclipse.ui.IViewSite;
import org.eclipse.ui.IWorkbenchPartReference;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.actions.ActionFactory;
import org.eclipse.ui.forms.widgets.FormToolkit;
import org.eclipse.ui.part.ViewPart;
import org.eclipse.ui.services.ISourceProviderService;

import org.eclipse.emf.examples.databinding.project.core.IModelResource;
import org.eclipse.emf.examples.databinding.project.core.model.project.Project;
import org.eclipse.emf.example.databinding.project.ui.rcp.Activator;
import org.eclipse.emf.example.databinding.project.ui.rcp.ResourceProvider;
import org.eclipse.emf.example.databinding.project.ui.rcp.databinding.Util;


/**
 * The workbench part displayed to the user
 */
public class ProjectAdminViewPart extends ViewPart implements ISaveablePart2
{
  /**
   * Id of the view
   */
  public static final String ID = "org.eclipse.emf.example.databinding.project.ui.rcp.views.ProjectAdminViewPart";

  private SashForm sashForm;

  private FormToolkit toolkit;

  private float divider = 0.2f;
  private static final String DIVIDER_KEY = Activator.PLUGIN_ID + ".divider";

  private ObservablesManager mgr;
  private IModelResource resource;

  private ProjectExplorerPart projectExplorer;
  private ProjectFormAreaPart projectDataForm;
  private IPartListener2 listener;
  private UndoAction undoAction;
  private RedoAction redoAction;
  private IModelResource.Listener modelListener;

  @Override
  public void init(final IViewSite site, IMemento memento) throws PartInitException
  {
    super.init(site, memento);
    if (memento != null && memento.getFloat(DIVIDER_KEY) != null)
    {
      divider = memento.getFloat(DIVIDER_KEY);
    }

    listener = new PartListenerImpl(site);
    site.getPage().addPartListener(listener);
  }

  @Override
  public void saveState(IMemento memento)
  {
    super.saveState(memento);
    int total = sashForm.getWeights()[0] + sashForm.getWeights()[1];
    memento.putFloat(DIVIDER_KEY, sashForm.getWeights()[0] * 1.f / total);
  }

  @Override
  public void createPartControl(Composite parent)
  {
    String path = getViewSite().getSecondaryId().replaceAll("#_#", ":");

    resource = Activator.getDefault().loadResource(path); //FIXME This needs to be a real URI
    modelListener = new IModelResource.Listener()
      {

        public void dirtyStateChanged()
        {
          firePropertyChange(PROP_DIRTY);
        }

        public void commandStackChanged()
        {
        }

      };

    resource.addListener(modelListener);

    if (resource == null)
    {
      throw new RuntimeException("Could not load resource!");
    }

    toolkit = new FormToolkit(parent.getDisplay());
    sashForm = new SashForm(parent, SWT.HORIZONTAL);

    /* 
     * Track the creation of observables so that we don't leak listeners 
     * when the view part is closed
     */
    mgr = Util.getObservableManager();
    mgr.runAndCollect(new Runnable()
      {

        public void run()
        {
          projectExplorer = new ProjectExplorerPart(getViewSite(), sashForm, toolkit, resource.getFoundation());
          projectDataForm = new ProjectFormAreaPart(getViewSite(), sashForm, toolkit, resource, projectExplorer.getProjectObservable());
        }
      });

    int left = (int)(100 * divider);

    sashForm.setWeights(new int []{ left, 100 - left });

    makeActions();

  }

  private void makeActions()
  {
    undoAction = new UndoAction(resource);
    redoAction = new RedoAction(resource);

    getViewSite().getActionBars().setGlobalActionHandler(ActionFactory.UNDO.getId(), undoAction);
    getViewSite().getActionBars().setGlobalActionHandler(ActionFactory.REDO.getId(), redoAction);
  }

  @Override
  public void setFocus()
  {
    projectExplorer.setFocus();
  }

  @Override
  public void dispose()
  {
    if (undoAction != null)
      undoAction.dispose();

    if (redoAction != null)
      redoAction.dispose();

    if (modelListener != null && resource != null)
      resource.removeListener(modelListener);

    ISourceProviderService s = (ISourceProviderService)getSite().getService(ISourceProviderService.class);
    ResourceProvider p = (ResourceProvider)s.getSourceProvider(ResourceProvider.MODEL_RESOURCE_NAME);
    p.setModelResource(null);
    getSite().getPage().removePartListener(listener);

    projectDataForm.dispose();
    projectExplorer.dispose();

    if (toolkit != null)
    {
      toolkit.dispose();
    }

    if (mgr != null)
    {
      mgr.dispose();
    }

    super.dispose();
  }

  private class PartListenerImpl implements IPartListener2
  {
    private IViewSite site;

    public PartListenerImpl(IViewSite site)
    {
      this.site = site;
    }

    public void partVisible(IWorkbenchPartReference partRef)
    {
    }

    public void partOpened(IWorkbenchPartReference partRef)
    {
    }

    public void partInputChanged(IWorkbenchPartReference partRef)
    {
    }

    public void partHidden(IWorkbenchPartReference partRef)
    {
    }

    public void partDeactivated(IWorkbenchPartReference partRef)
    {
    }

    public void partClosed(IWorkbenchPartReference partRef)
    {
    }

    public void partBroughtToTop(IWorkbenchPartReference partRef)
    {
    }

    public void partActivated(IWorkbenchPartReference partRef)
    {
      ISourceProviderService s = (ISourceProviderService)site.getService(ISourceProviderService.class);
      ResourceProvider p = (ResourceProvider)s.getSourceProvider(ResourceProvider.MODEL_RESOURCE_NAME);
      p.setModelResource(resource);
      p.setCommitter(projectExplorer.getCommitter());
      p.setProject((Project)projectExplorer.getProjectObservable().getValue());
    }
  }

  public int promptToSaveOnClose()
  {
    return ISaveablePart2.DEFAULT;
  }

  public void doSave(IProgressMonitor monitor)
  {
    if (resource != null)
    {
      IStatus s = resource.save();
      if (!s.isOK())
      {
        Activator.getDefault().getLog().log(s);
        throw new RuntimeException();
      }
      else
      {
        firePropertyChange(PROP_DIRTY);
      }
    }
  }

  public void doSaveAs()
  {
  }

  public boolean isDirty()
  {
    return resource.isDirty();
  }

  public boolean isSaveAsAllowed()
  {
    return false;
  }

  public boolean isSaveOnCloseNeeded()
  {
    return true;
  }
}
