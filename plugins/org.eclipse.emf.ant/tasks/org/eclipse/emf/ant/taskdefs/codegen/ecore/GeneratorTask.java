/**
 * <copyright> 
 *
 * Copyright (c) 2004-2005 IBM Corporation and others.
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
 * $Id: GeneratorTask.java,v 1.4 2005/02/10 22:11:51 marcelop Exp $
 */
package org.eclipse.emf.ant.taskdefs.codegen.ecore;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.apache.tools.ant.BuildException;
import org.apache.tools.ant.types.Commandline;
import org.apache.tools.ant.types.EnumeratedAttribute;

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
  public static class ReconcileGenModelType extends EnumeratedAttribute
  {
    public String[] getValues()
    {
      return new String []{ "overwrite", "keep", "reload" };
    }
  }

  private static final int GENMODEL_OVERWRITE = 0;
  private static final int GENMODEL_KEEP = 1;
  private static final int GENMODEL_RELOAD = 2;

  private File model;
  private File genModel;
  private File modelProject;
  private String modelPluginID;
  private String modelProjectFragmentPath;
  private File templatePath;
  private String copyright;
  private boolean sdo = false;

  private int reconcileGenModel = GENMODEL_OVERWRITE;
  private boolean generateJavaCode = true;

  protected Commandline commandline;
  protected boolean useModelAttribute = true;
  protected boolean generateModelProject = true;
  protected boolean generateEditProject = true;
  protected boolean generateEditorProject = true;

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

  public void setModelPluginID(String modelPluginID)
  {
    this.modelPluginID = modelPluginID;
  }

  public Commandline.Argument createArg()
  {
    return getCommandline().createArgument();
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

  public void setReconcileGenModel(ReconcileGenModelType type)
  {
    String value = type.getValue();
    if ("overwrite".equals(value))
    {
      reconcileGenModel = GENMODEL_OVERWRITE;
    }
    else if ("keep".equals(value))
    {
      reconcileGenModel = GENMODEL_KEEP;
    }
    else if ("reload".equals(value))
    {
      reconcileGenModel = GENMODEL_RELOAD;
      throw new UnsupportedOperationException("'genModelAction=\"reload\"' is not supported yet.");
    }
  }

  public void setGenerateJavaCode(boolean generateJavaCode)
  {
    this.generateJavaCode = generateJavaCode;
  }

  protected Commandline getCommandline()
  {
    if (commandline == null)
    {
      commandline = new Commandline();
    }
    return commandline;
  }

  protected void checkAttributes() throws BuildException
  {
    if (useModelAttribute)
    {
      assertTrue("The 'model' attribute must be specified.", model != null);
    }

    assertTrue("The 'genModel' attribute must be specified.", genModel != null);
    assertTrue("The 'modelProject' attribute must be specified.", modelProject != null);
    assertTrue("The specifed 'templatePath' attribute is not a valid directory.", templatePath == null || templatePath.isDirectory());
  }

  protected void doExecute() throws Exception
  {
    switch (reconcileGenModel)
    {
      case GENMODEL_KEEP: {
        if (genModel.exists())
          break;
      }
      case GENMODEL_OVERWRITE: {
        addGenModelArguments();
        adjustEditAndEditorProjects();
        createGenModel(getCommandline().getArguments());
      }
    }

    if (generateJavaCode)
    {
      List arguments = getGeneratorArguments();
      generateCodeFromGenModel((String[])arguments.toArray(new String [arguments.size()]));
    }
  }

  abstract protected void createGenModel(String[] arguments) throws Exception;

  protected void addGenModelArguments()
  {
    getCommandline().createArgument(true).setValue(genModel.getAbsolutePath());
    if (useModelAttribute)
    {
      getCommandline().createArgument(true).setValue(model.getAbsolutePath());
    }

    getCommandline().createArgument().setValue("-modelProject");
    getCommandline().createArgument().setValue(modelProject.getAbsolutePath());
    if (modelProjectFragmentPath != null)
    {
      getCommandline().createArgument().setValue(modelProjectFragmentPath);
    }

    if (templatePath != null)
    {
      getCommandline().createArgument().setValue("-templatePath");
      getCommandline().createArgument().setValue(templatePath.getAbsolutePath());
    }

    if (modelPluginID != null)
    {
      getCommandline().createArgument().setValue("-modelPluginID");
      getCommandline().createArgument().setValue(modelPluginID);
    }

    if (copyright != null)
    {
      getCommandline().createArgument().setValue("-copyright");
      getCommandline().createArgument().setValue(copyright);
    }

    if (sdo)
    {
      getCommandline().createArgument().setValue("-sdo");
    }
  }

  protected void adjustEditAndEditorProjects()
  {
    String arguments = getCommandline().toString();
    if (arguments.indexOf("-editProject") < 0)
    {
      generateEditProject = false;
      getCommandline().createArgument().setValue("-editProject");
      getCommandline().createArgument().setValue(modelProject.getAbsolutePath() + ".edit");
      if (modelProjectFragmentPath != null)
      {
        getCommandline().createArgument().setValue(modelProjectFragmentPath);
      }
    }

    if (arguments.indexOf("-editorProject") < 0)
    {
      generateEditorProject = false;
      getCommandline().createArgument().setValue("-editorProject");
      getCommandline().createArgument().setValue(modelProject.getAbsolutePath() + ".editor");
      if (modelProjectFragmentPath != null)
      {
        getCommandline().createArgument().setValue(modelProjectFragmentPath);
      }
    }
  }

  protected List getGeneratorArguments()
  {
    List arguments = new ArrayList();

    if (generateModelProject)
      arguments.add("-model");
    if (generateEditProject)
      arguments.add("-edit");
    if (generateEditorProject)
      arguments.add("-editor");

    arguments.add(genModel.getAbsolutePath());
    return arguments;
  }

  protected void generateCodeFromGenModel(String[] arguments)
  {
    new Generator().run(arguments);
  }
}