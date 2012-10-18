/**
 * Copyright (c) 2004-2012 IBM Corporation and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: 
 *   IBM - Initial API and implementation
 */
package org.eclipse.emf.edit.ui.action;


import java.util.List;

import org.eclipse.jface.action.Action;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.PlatformUI;

import org.eclipse.emf.common.ui.dialogs.ResourceDialog;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.emf.edit.domain.IEditingDomainProvider;
import org.eclipse.emf.edit.ui.EMFEditUIPlugin;


/**
 * An action to load a resource into an editing domain's resource set.
 */
public class LoadResourceAction extends Action
{
  protected EditingDomain domain;

  public LoadResourceAction(EditingDomain domain)
  {
    this();
    this.domain = domain;
    update();
  }

  public LoadResourceAction()
  {
    super(EMFEditUIPlugin.INSTANCE.getString("_UI_LoadResource_menu_item"));
    setDescription(EMFEditUIPlugin.INSTANCE.getString("_UI_LoadResource_menu_item_description"));
  }

  /**
   * This returns the action's domain.
   */
  public EditingDomain getEditingDomain()
  {
    return domain;
  }

  /**
   * This sets the action's domain.
   */
  public void setEditingDomain(EditingDomain domain)
  {
    this.domain = domain;
  }

  @Override
  public void run()
  {
    LoadResourceDialog loadResourceDialog =
      new LoadResourceDialog
          (PlatformUI.getWorkbench().getActiveWorkbenchWindow().getShell(), domain);
  
    loadResourceDialog.open();
  }

  public void update()
  {
    setEnabled(domain != null);
  }

  /**
   * @deprecated As of EMF 2.1.0, replaced by {@link #setActiveWorkbenchPart}.
   */
  @Deprecated
  public void setActiveEditor(IEditorPart editorPart)
  {
    setActiveWorkbenchPart(editorPart);
  }

  /**
   * @since 2.1.0
   */
  public void setActiveWorkbenchPart(IWorkbenchPart workbenchPart)
  {
    setEditingDomain(workbenchPart instanceof IEditingDomainProvider ? ((IEditingDomainProvider)workbenchPart).getEditingDomain() : null);
  }

  public static class LoadResourceDialog extends ResourceDialog
  {
    protected EditingDomain domain;

    public LoadResourceDialog(Shell parent)
    {
      this(parent, null);
    }

    public LoadResourceDialog(Shell parent, EditingDomain domain)
    {
      super(parent, EMFEditUIPlugin.INSTANCE.getString("_UI_LoadResourceDialog_title"), SWT.OPEN | SWT.MULTI, getContextURI(domain));
      this.domain = domain;
    }

    private static URI getContextURI(EditingDomain domain)
    {
      if (domain != null)
      {
        List<Resource> resources = domain.getResourceSet().getResources();
        if (!resources.isEmpty())
        {
          return resources.get(0).getURI();
        }
      }
      return null;
    }

    @Override
    protected boolean processResources()
    {
      if (domain != null)
      {
        for (URI uri : getURIs())
        {
          try
          {
            if (!processResource(domain.getResourceSet().getResource(uri, true)))
            {
              return false;
            }
          }
          catch (RuntimeException exception)
          {
            EMFEditUIPlugin.INSTANCE.log(exception);
          }
        }
      }
      return true;
    }

    protected boolean processResource(Resource resource)
    {
      return true;
    }
  }
}
