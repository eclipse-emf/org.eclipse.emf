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
 * $Id: GeneratorTask.java,v 1.1 2004/12/30 08:15:34 marcelop Exp $
 */
package org.eclipse.emf.ant.taskdefs.codegen.ecore;

import java.io.File;

import org.apache.tools.ant.BuildException;
import org.apache.tools.ant.types.Commandline;

import org.eclipse.emf.ant.taskdefs.EMFTask;
import org.eclipse.emf.codegen.ecore.Generator;


/**
 * <p>
 * Base class for all the &quot;generator tasks&quot; that provides the common attributes 
 * and behavior.  A &quot;generator task&quot; is an Ant task that encompasses the 
 * two operations performed when generating code with EMF: the creation of the EMF files
 * (genmodel and ecore) from a given model specification and the code generation.
 * </p>
 * 
 * @since 2.1.0
 */
public abstract class GeneratorTask extends EMFTask
{
  private File model;
  private File genModel;
  private File modelProject;
  private String modelProjectFragmentPath;
  private File templatePath;
  private String copyright;
  private boolean sdo = false;

  private Commandline cmdl;

  public void setModel(File model)
  {
    this.model = model;
  }

  public void setGenModel(File genModel)
  {
    this.genModel = genModel;
  }

  public void setModelProject(File modelProject)
  {
    this.modelProject = modelProject;
  }

  public void setModelProjectFragmentPath(String modelProjectFragmentPath)
  {
    this.modelProjectFragmentPath = modelProjectFragmentPath;
  }

  public Commandline.Argument createArg()
  {
    if (cmdl == null)
    {
      cmdl = new Commandline();
    }

    return cmdl.createArgument();
  }

  public void setTemplatePath(File templatePath)
  {
    this.templatePath = templatePath;
  }

  public void setCopyright(String copyright)
  {
    this.copyright = copyright;
  }

  public void setSDO(boolean sdo)
  {
    this.sdo = sdo;
  }

  protected void checkAttributes() throws BuildException
  {
    assertTrue("The 'model' attribute must be specified.", model != null);
    assertTrue("The 'genModel' attribute must be specified.", genModel != null);
    assertTrue("The 'modelProject' attribute must be specified.", modelProject != null);
    assertTrue("The specifed 'templatePath' attribute is not a valid directory.", templatePath == null || templatePath.isDirectory());
  }

  protected void doExecute() throws Exception
  {
    adjustCommandline();
    createGenModel();
    generator();
  }

  abstract protected void createGenModel() throws Exception;

  protected Commandline getCommandline()
  {
    return cmdl;
  }

  protected void adjustCommandline()
  {
    if (sdo)
    {
      getCommandline().createArgument(true).setValue("-sdo");
    }

    if (copyright != null)
    {
      getCommandline().createArgument(true).setValue(copyright);
      getCommandline().createArgument(true).setValue("-copyright");
    }

    if (templatePath != null)
    {
      getCommandline().createArgument(true).setValue(templatePath.getAbsolutePath());
      getCommandline().createArgument(true).setValue("-templatePath");
    }

    if (modelProjectFragmentPath != null)
    {
      getCommandline().createArgument(true).setValue(modelProjectFragmentPath);
    }

    getCommandline().createArgument(true).setValue(modelProject.getAbsolutePath());
    getCommandline().createArgument(true).setValue("-modelProject");
    getCommandline().createArgument(true).setValue(genModel.getAbsolutePath());
    getCommandline().createArgument(true).setValue(model.getAbsolutePath());
  }

  protected void generator()
  {
    new Generator().run(new String []{ "-forceOverwrite", genModel.getAbsolutePath() });
  }
}