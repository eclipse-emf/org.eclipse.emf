/**
 * <copyright> 
 *
 * Copyright (c) 2004 IBM Corporation and others.
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
 * $Id: LoadResourceAction.java,v 1.5 2004/06/09 20:07:35 marcelop Exp $
 */
package org.eclipse.emf.edit.ui.action;


import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.StringTokenizer;

import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.Platform;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.edit.domain.EditingDomain;
import org.eclipse.emf.edit.domain.IEditingDomainProvider;
import org.eclipse.emf.edit.ui.EMFEditUIPlugin;
import org.eclipse.jface.action.Action;
import org.eclipse.jface.dialogs.Dialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.layout.RowLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.dialogs.ResourceSelectionDialog;


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

  public void setActiveEditor(IEditorPart editorPart)
  {
    setEditingDomain(editorPart instanceof IEditingDomainProvider ? ((IEditingDomainProvider)editorPart).getEditingDomain() : null);
  }

  public static class LoadResourceDialog extends Dialog
  {
    protected EditingDomain domain;
    protected Text resourceURIField;
    protected String resourceURIs;

    public LoadResourceDialog
    (Shell parent)
  {
    this(parent, null);
  }

    public LoadResourceDialog
      (Shell parent, EditingDomain domain)
    {
      super(parent);
      setShellStyle(getShellStyle() | SWT.MAX);
      this.domain = domain;
    }

    protected void configureShell(Shell shell) 
    {
      super.configureShell(shell);
      shell.setText(EMFEditUIPlugin.INSTANCE.getString("_UI_LoadResourceDialog_title"));
    }

    protected Control createDialogArea(Composite parent) 
    {
      Composite composite = (Composite)super.createDialogArea(parent);
      {
        GridLayout layout = new GridLayout();
        layout.numColumns = 2;
        layout.verticalSpacing = 12;
        layout.horizontalSpacing = 20;
        composite.setLayout(layout);

        GridData data = new GridData();
        data.verticalAlignment = GridData.FILL;
        data.grabExcessVerticalSpace = true;
        data.horizontalAlignment = GridData.FILL;
        composite.setLayoutData(data);
      }

      Label resourceURILabel = new Label(composite, SWT.LEFT);
      {
        resourceURILabel.setText(EMFEditUIPlugin.INSTANCE.getString("_UI_ResourceURI_label"));

        GridData data = new GridData();
        data.horizontalAlignment = GridData.FILL;
        resourceURILabel.setLayoutData(data);
      }

      Composite buttonComposite = new Composite(composite, SWT.NONE);
      {
        GridData data = new GridData();
        data.horizontalAlignment = GridData.END;
        buttonComposite.setLayoutData(data);
      
        RowLayout layout = new RowLayout();
        layout.justify = true;
        layout.pack = true;
        layout.spacing = 5;
        buttonComposite.setLayout(layout);
      }
      
      Button resourceURIBrowseFileSystemButton = new Button(buttonComposite, SWT.PUSH);
      resourceURIBrowseFileSystemButton.setText(EMFEditUIPlugin.INSTANCE.getString("_UI_BrowseFileSystem_label"));

      resourceURIBrowseFileSystemButton.addSelectionListener
        (new SelectionAdapter()
         {
           public void widgetSelected(SelectionEvent event)
           {
             FileDialog fileDialog = new FileDialog(getShell());
             fileDialog.open();
             if (fileDialog.getFileName() != null && fileDialog.getFileName().length() > 0)
             {
               String filePath = fileDialog.getFilterPath() + File.separator + fileDialog.getFileName();
               resourceURIField.setText((resourceURIField.getText() + "  " + URI.createFileURI(filePath).toString()).trim());
             }
           }
         });
      
      if(Platform.getBundle("org.eclipse.core.resources") != null)
      {
	      Button resourceURIBrowseWorkspaceButton = new Button(buttonComposite, SWT.PUSH);
	      resourceURIBrowseWorkspaceButton.setText(EMFEditUIPlugin.INSTANCE.getString("_UI_BrowseWorkspace_label"));
	      resourceURIBrowseWorkspaceButton.addSelectionListener
	        (new SelectionAdapter()
	         {
	           public void widgetSelected(SelectionEvent event)
	           {
	             Collection resources = new ArrayList();
	             ResourceSelectionDialog resourceSelectionDialog =
	               new ResourceSelectionDialog
	                 (getShell(),
	                  ResourcesPlugin.getWorkspace().getRoot(),
	                  EMFEditUIPlugin.INSTANCE.getString("_UI_SelectTheResource_label"));
	
	             resourceSelectionDialog.open();
	             Object [] result = resourceSelectionDialog.getResult();
	             if (result != null)
	             {
	               StringBuffer text = new StringBuffer();
	               for (int i = 0; i < result.length; ++i)
	               {
	                 IResource resource = (IResource)result[i];
	                 if (resource.getType() == IResource.FILE)
	                 {
	                   text.append(URI.createPlatformResourceURI(resource.getFullPath().toString()));
	                   text.append("  ");
	                 }
	               }
	               resourceURIField.setText((resourceURIField.getText() + "  " + text.toString()).trim());
	             }
	           }
	         });
      }

      resourceURIField = new Text(composite, SWT.BORDER);
      {
        GridData data = new GridData();
        data.horizontalAlignment = GridData.FILL;
        data.grabExcessHorizontalSpace = true;
        data.horizontalSpan = 2;
        resourceURIField.setLayoutData(data);
      }
      
      Label separatorLabel = new Label(composite, SWT.SEPARATOR | SWT.HORIZONTAL);
      {
        GridData data = new GridData();
        data.horizontalAlignment = GridData.FILL;
        data.grabExcessHorizontalSpace = true;
        data.horizontalSpan = 2;
        separatorLabel.setLayoutData(data);
      }      
      
      return composite;
    }

    protected void okPressed()
    {
      resourceURIs = getResourceURIs();
      if (domain != null)
      {
        for (Iterator i = getURIs().iterator(); i.hasNext();)
        {
	        try
	        {
	          domain.getResourceSet().getResource((URI)i.next(), true);
	        }
	        catch (RuntimeException exception)
	        {
	          EMFEditUIPlugin.INSTANCE.log(exception);
	        }
        }
      }
      super.okPressed();
    }

    public boolean close()
    {
      return super.close();
    }
    
    public String getResourceURIs()
    {
      return resourceURIField != null && !resourceURIField.isDisposed() ? resourceURIField.getText() : resourceURIs; 
    }
    
    public List getURIs()
    {
      List uris = new ArrayList();
      for (StringTokenizer stringTokenizer = new StringTokenizer(getResourceURIs()); stringTokenizer.hasMoreTokens(); )
      {
        String resourceURI = stringTokenizer.nextToken();
        uris.add(URI.createURI(resourceURI));
      }
      
      return uris;
    }
  }
}
