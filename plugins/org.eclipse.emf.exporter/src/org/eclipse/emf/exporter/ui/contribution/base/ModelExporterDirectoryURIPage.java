/**
 * Copyright (c) 2005-2006 IBM Corporation and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: 
 *   IBM - Initial API and implementation
 */

package org.eclipse.emf.exporter.ui.contribution.base;

import java.io.File;

import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.Path;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.DirectoryDialog;

import org.eclipse.emf.common.ui.CommonUIPlugin;
import org.eclipse.emf.common.ui.dialogs.WorkspaceResourceDialog;
import org.eclipse.emf.common.util.Diagnostic;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.converter.ui.contribution.base.ModelConverterURIPage;
import org.eclipse.emf.exporter.ExporterPlugin;
import org.eclipse.emf.exporter.ModelExporter;

/**
 * @since 2.2.0
 */
public class ModelExporterDirectoryURIPage extends ModelConverterURIPage implements IModelExporterPage
{
  public ModelExporterDirectoryURIPage(ModelExporter modelExporter, String pageName)
  {
    super(modelExporter, pageName);
    setDescription(ExporterPlugin.INSTANCE.getString("_UI_ArtifactDirectory_description"));
  }

  public ModelExporter getModelExporter()
  {
    return (ModelExporter)getModelConverter();
  }
  
  @Override
  public boolean isPageComplete()
  {
    return super.isPageComplete()
      && getModelExporter().getDirectoryURI()!= null;
  }
  
  @Override
  protected String getURITextLabel()
  {
    return ExporterPlugin.INSTANCE.getString("_UI_ArtifactDirectoryURI_label");
  }
  
  @Override
  protected String getURITextInitialValue()
  {
    URI uri = getModelExporter().getDirectoryURI();
    return uri == null ? super.getURITextInitialValue() : uri.toString();
  }
  
  @Override
  protected void uriTextModified(String text)
  {
    if (text.length() == 0)
    {
      setErrorMessage(ExporterPlugin.INSTANCE.getString("_UI_DirectoryURI_error"));
    }
    else
    {
      Diagnostic diagnostic = getModelExporter().validateDirectoryURI(text);
      if (diagnostic.getSeverity() == Diagnostic.OK)
      {
        getModelExporter().setDirectoryURI(text);
        if (getErrorMessage() != null)
        {
          handleDiagnostic(diagnostic);
        }
      }
      else
      {
        getModelExporter().setDirectoryURI((URI)null);      
        if (getErrorMessage() == null)
        {
          handleDiagnostic(diagnostic);
        }
      }
    }
  }
  
  @Override
  protected boolean browseFileSystem()
  {
    DirectoryDialog directoryDialog = new DirectoryDialog(getShell(), SWT.NONE);
    directoryDialog.setText(CommonUIPlugin.INSTANCE.getString("_UI_FolderSelection_title"));
    URI uri = getModelExporter().getDirectoryURI();
    if (uri != null)
    {
      directoryDialog.setFilterPath(uri.toFileString());
    }
    
    String directory = directoryDialog.open();
    if (directory != null)
    {
      setDirectory(directory, false);
      return true;
    }
    return false;    
  }
  
  @Override
  protected boolean browseWorkspace()
  {
    IResource initialFolderResource = null;
    URI uri = getModelExporter().getDirectoryURI();
    if (uri != null)
    {
      if (uri.isPlatformResource())
      {
        String initialFolder = uri.toPlatformString(true);
        initialFolderResource = ResourcesPlugin.getWorkspace().getRoot().findMember(new Path(initialFolder));
      }
    }
    
    IContainer[] containers = WorkspaceResourceDialog.openFolderSelection(
      getShell(), 
      null, null, 
      false, new Object[]{initialFolderResource}, null);
    if (containers.length == 1)
    {
      setDirectory(containers[0].getFullPath().toString(), true);
      return true;
    }
    return false;
  }
  
  protected void setDirectory(String directory, boolean platformURI)
  {
    if (!directory.endsWith(File.separator))
    {
      directory = directory + File.separator;
    }
    
    if (platformURI)
    {
      getModelExporter().setDirectoryURI(URI.createPlatformResourceURI(directory, true));
    }
    else
    {
      getModelExporter().setDirectoryURI(URI.createFileURI(directory));
    }
    
    setURIText(getModelExporter().getDirectoryURI().toString());    
  }
}
