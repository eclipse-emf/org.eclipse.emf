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
 * $Id: NewXMIHandler.java,v 1.2 2009/06/01 17:19:26 tschindl Exp $
 */
package org.eclipse.emf.example.databinding.project.ui.rcp.handlers;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.expressions.IEvaluationContext;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.ISources;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PartInitException;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.xmi.impl.XMIResourceFactoryImpl;
import org.eclipse.emf.example.databinding.project.ui.rcp.views.ProjectAdminViewPart;
import org.eclipse.emf.examples.databinding.project.core.model.project.Foundation;
import org.eclipse.emf.examples.databinding.project.core.model.project.ProjectFactory;
import org.eclipse.emf.examples.databinding.project.core.model.project.ProjectPackage;


/**
 * Handles the creation of a new XMI-Resource
 */
public class NewXMIHandler extends AbstractHandler
{

  public Object execute(ExecutionEvent event) throws ExecutionException
  {
    IEvaluationContext ctx = (IEvaluationContext)event.getApplicationContext();
    Shell shell = (Shell)ctx.getVariable(ISources.ACTIVE_WORKBENCH_WINDOW_SHELL_NAME);

    FileDialog dialog = new FileDialog(shell, SWT.SAVE);
    String name = dialog.open();

    if (name != null)
    {
      File f = new File(name);

      if (!f.exists())
      {
        ResourceSet resourceSet = new ResourceSetImpl();
        resourceSet.getResourceFactoryRegistry().getExtensionToFactoryMap().put(
          Resource.Factory.Registry.DEFAULT_EXTENSION,
          new XMIResourceFactoryImpl());
        resourceSet.getPackageRegistry().put(ProjectPackage.eNS_URI, ProjectPackage.eINSTANCE);

        Resource resource = resourceSet.createResource(URI.createURI("http:///My.project"));
        Foundation root = ProjectFactory.eINSTANCE.createFoundation();
        resource.getContents().add(root);
        try
        {
          FileOutputStream out = new FileOutputStream(f);
          resource.save(out, null);
          out.close();
        }
        catch (FileNotFoundException e)
        {
          e.printStackTrace();
        }
        catch (IOException e)
        {
          e.printStackTrace();
        }
      }

      try
      {
        IWorkbenchWindow w = (IWorkbenchWindow)ctx.getVariable(ISources.ACTIVE_WORKBENCH_WINDOW_NAME);
        String path = f.toURL().toString();
        path = path.replaceFirst(":", "_");

        w.getActivePage().showView(ProjectAdminViewPart.ID, path, IWorkbenchPage.VIEW_ACTIVATE);
      }
      catch (PartInitException e)
      {
        e.printStackTrace();
      }
      catch (MalformedURLException e)
      {
        e.printStackTrace();
      }
    }

    return null;
  }
}
