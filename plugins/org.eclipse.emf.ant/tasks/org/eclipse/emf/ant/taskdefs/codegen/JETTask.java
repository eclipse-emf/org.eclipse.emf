/**
 * Copyright (c) 2007 IBM Corporation and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: 
 *   IBM - Initial API and implementation
 */
package org.eclipse.emf.ant.taskdefs.codegen;

import java.io.File;
import java.io.IOException;

import org.apache.tools.ant.BuildException;

import org.eclipse.emf.ant.taskdefs.EMFTask;
import org.eclipse.emf.common.util.URI;


/**
 * Super class of the Jet Ant tasks
 * @since 2.3.0
 */
public abstract class JETTask extends EMFTask
{
  protected String templateURI;
  protected File templateFile;
  protected File newFile;
  
  
  public void setTemplateFile(File templateFile)
  {
    this.templateFile = templateFile;
  }

  public void setTemplateURI(String templateURI)
  {
    this.templateURI = templateURI;
  }

  public void setNewFile(File newFile)
  {
    this.newFile = newFile;
  }

  protected String getTemplateURIAsString()
  {
    if (templateURI != null)
    {
      return templateURI.toString();
    }
    else if (templateFile != null)
    {
      try
      {
        templateFile = templateFile.getCanonicalFile();
      }
      catch (IOException e)
      {
        // Ignore
      }
      URI uri = templateFile.isFile() ? URI.createFileURI(templateFile.toString()) : URI.createURI(templateFile.toString());
      return uri.toString();
    }
    else
    {
      return null;
    }
  }

  @Override
  protected void checkAttributes() throws BuildException
  {
    assertTrue("Either 'templateURI' or 'templateFile' must be specified.", templateURI != null || templateFile != null);
  }
}
