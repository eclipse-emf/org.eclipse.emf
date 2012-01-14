/**
 * Copyright (c) 2004-2009 IBM Corporation and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: 
 *   IBM - Initial API and implementation
 */
package org.eclipse.emf.ant.taskdefs.codegen.ecore;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.tools.ant.BuildException;
import org.apache.tools.ant.types.Commandline;
import org.apache.tools.ant.types.EnumeratedAttribute;

import org.eclipse.emf.ant.taskdefs.EMFTask;
import org.eclipse.emf.codegen.ecore.Generator;
import org.eclipse.emf.codegen.ecore.genmodel.GenJDKLevel;
import org.eclipse.emf.common.util.URI;


/**
 * <p>
 * Base class for all the &quot;generator tasks&quot; that provides the common attributes 
 * and behavior.  A &quot;generator task&quot; is an Ant task that encompasses the 
 * two operations performed when generating code with EMF: the creation of the EMF files
 * (genmodel and ecore) from a given model specification and the code generation.
 * </p>
 * <p>
 * These are the common attributes provided by this class:
 * </p>
 * <table order="1" cellpadding="2" cellspacing="0">
 * <tr><td><b>Attribute</b></td><td><b>Description</b></td></tr>
 * <tr>
 *    <td valign="top">model</td>
 *    <td>The file that defines the model.</td>
 * </tr>
 * <tr>
 *    <td valign="top">genModel</td>
 *    <td>The .genmodel file.</td>
 * </tr>
 * <tr>
 *    <td valign="top">reconcileGenModel</td>
 *    <td>Specifies how the genmodel file is to be supposed to be handled.  The possible values are:
 *    <ul>
 *        <li>overwrite - <i>(default)</i>Replaces the genmodel file if it exists;</li>
 *        <li>keep - Doesn't generate the genmodel if one is provided; and</li>
 *        <li>reload - Reloads the genmodel to reflect model changes.</li>
 *    </ul> 
 *    </td>
 * </tr>
 * <tr>
 *    <td valign="top">generateJavaCode</td>
 *    <td>Boolean value indicating whether the java code should be generated.  Should be set to 
 *    <tt>false</tt> when you want to generate only the .ecore and .genmodel files</td>
 * </tr>
 * <tr>
 *    <td valign="top">generateModelProject</td>
 *    <td>The model project will be only generated if this attribute is set to <tt>true</tt> and if the project's
 *    information is specified in the task.
 *    <br />The default value is <tt>true</tt>.</td>
 * </tr>
 * <tr>
 *    <td valign="top">generateEditProject</td>
 *    <td>The edit project will be only generated if this attribute is set to <tt>true</tt> and if the project's
 *    information is specified in the task.
 *    <br />The default value is <tt>true</tt>.</td>
 * </tr>
 * <tr>
 *    <td valign="top">generateEditorProject</td>
 *    <td>The editor project will be only generated if this attribute is set to <tt>true</tt> and if the project's
 *    information is specified in the task.
 *    <br />The default value is <tt>true</tt>.</td>
 * </tr>
 * <tr>
 *    <td valign="top">templatePath</td>
 *    <td>The directory where your customized JET templates are located.</td>
 * </tr>
 * <tr>
 *    <td valign="top">modelProject</td>
 *    <td>The directory where the files generated for the model will be placed into.</td>
 * </tr>
 * <tr>
 *    <td valign="top">modelProjectFragmentPath</td>
 *    <td>The model project relative path of the source folder.</td>
 * </tr>
 * <tr>
 *    <td valign="top">modelPluginID</td>
 *    <td>The ID of the generated model plugin.</td>
 * </tr>
 * <tr>
 *    <td valign="top">autoBuild</td>
 *    <td>Boolean value that sets the Eclipse's 'Build Automatically' flag.  If not specified, this task doesn't 
 *    change the current workspace's setting.</td>
 * </tr>
 * <tr>
 *    <td valign="top">sdo</td>
 *    <td>Boolean value indicating whether the SDO API should be generated.</td>
 * </tr>
 * <tr>
 *    <td valign="top">copyright</td>
 *    <td>The copyright text.</td>
 * </tr>
 * <tr>
 *    <td valign="top">jdkLevel</td>
 *    <td>The JDK level for the generated code. &quot;1.4&quot;, &quot;5.0&quot;, and
 *    &quot;6.0&quot; are valid values.</td>
 * </tr>
 * <tr>
 *    <td valign="top">validateModel</td>
 *    <td>Boolean value indicating whether the Ecore model should be validated before
 *    generating the code.</td>
 * </tr>
 * </table>
 * 
 * <p>If the Ant task knows how to handle multiple model specifications,  
 * &lt;model&gt; elements can be used (instead of the <tt>model</tt> attribute).  Here's
 * an example:</p>
 * 
 * <pre>
 *   &lt;model uri=&quot;http://www.example.eclipse.org/library.xsd&quot;/&gt;
 *   &lt;model file=&quot;c:/common.xsd&quot;/&gt;
 * </pre>
 * 
 * @since 2.1.0
 */
public abstract class GeneratorTask extends EMFTask
{
  public static class ModelLocation
  {
    private File file;
    private String uri;

    public File getFile()
    {
      return file;
    }

    public void setFile(File file)
    {
      this.file = file;
    }

    public String getUri()
    {
      return uri;
    }

    public void setUri(String uri)
    {
      this.uri = uri;
    }
  }
  
  public static class ReconcileGenModelType extends EnumeratedAttribute
  {
    @Override
    public String[] getValues()
    {
      return new String []{ "overwrite", "keep", "reload" };
    }
  }

  protected static final int GENMODEL_OVERWRITE = 0;
  protected static final int GENMODEL_KEEP = 1;
  protected static final int GENMODEL_RELOAD = 2;

  protected File model;
  private List<ModelLocation> modelLocations;
  
  protected File genModel;
  protected File modelProject;
  protected String modelPluginID;
  protected String modelProjectFragmentPath;
  protected File templatePath;
  protected String copyright;
  protected boolean sdo = false;
  protected String jdkLevel;
  protected String validateModel;

  protected int reconcileGenModel = GENMODEL_OVERWRITE;
  protected boolean generateJavaCode = true;

  protected Commandline commandline;
  protected boolean generateModelProject = true;
  protected boolean generateEditProject = true;
  protected boolean generateEditorProject = true;
  protected boolean generateTestsProject = true;
  
  protected Boolean autoBuild; 

  protected boolean supportMultipleURIs()
  {
    return true;
  }  
  
  public void setModel(File model)
  {
    this.model = model;
  }
  
  public ModelLocation createModel()
  {
    if (supportMultipleURIs())
    {
      ModelLocation modelLocation = new ModelLocation();
      if (modelLocations == null)
      {
        modelLocations = new ArrayList<ModelLocation>();
        modelLocations.add(modelLocation);
      }
      else
      {
        modelLocations.add(0, modelLocation);
      }
      
      return modelLocation;
    }
    else
    {
      throw new BuildException("This importer doesn't support multiple models");
    }
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
  
  public void setGenerateModelProject(boolean generate)
  {
    generateModelProject = generate;
  }

  public void setGenerateEditProject(boolean generate)
  {
    generateEditProject = generate;
  }

  public void setGenerateEditorProject(boolean generate)
  {
    generateEditorProject = generate;
  }

  public void setGenerateTestsProject(boolean generate)
  {
    generateTestsProject = generate;
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
    setReconcileGenModel(type.getValue());
  }

  public void setReconcileGenModel(String type)
  {
    if ("overwrite".equals(type))
    {
      reconcileGenModel = GENMODEL_OVERWRITE;
    }
    else if ("keep".equals(type))
    {
      reconcileGenModel = GENMODEL_KEEP;
    }
    else if ("reload".equals(type))
    {
      reconcileGenModel = GENMODEL_RELOAD;
    }
  }

  public void setGenerateJavaCode(boolean generateJavaCode)
  {
    this.generateJavaCode = generateJavaCode;
  }
  
  public void setAutoBuild(boolean autoBuild)
  {
    this.autoBuild = Boolean.valueOf(autoBuild);
  }
  
  public void setValidateModel(boolean validateModel)
  {
    this.validateModel = Boolean.toString(validateModel);
  }
  
  public void setJdkLevel(String jdkLevel)
  {
    this.jdkLevel = jdkLevel;
  }
  
  protected GenJDKLevel getJDKLevel()
  {
    return GenJDKLevel.get(jdkLevel);
  }

  protected Commandline getCommandline()
  {
    if (commandline == null)
    {
      commandline = new Commandline();
    }
    return commandline;
  }

  @Override
  protected void checkAttributes() throws BuildException
  {
    if (modelLocations == null)
    {
      assertTrue("The 'model' attribute must be specified.", model != null && modelLocations == null);
    }
    else
    {
      for (ModelLocation modelLocation : modelLocations)
      {
        assertTrue("Either the 'file' or the 'uri' attributes of a 'model' element must be specified.", 
          modelLocation.getFile() != null || modelLocation.getUri() != null);
      }      
    }
    
    assertTrue("The 'genModel' attribute must be specified.", genModel != null);
    assertTrue("The specifed 'templatePath' attribute is not a valid directory.", templatePath == null || templatePath.isDirectory());
  }

  @Override
  protected void doExecute() throws Exception
  {
    switch (reconcileGenModel)
    {
      case GENMODEL_KEEP:
      {
        if (genModel.exists()) break;
      }
      case GENMODEL_RELOAD:
      case GENMODEL_OVERWRITE:
      {
        addGenModelArguments();
        adjustEditAndEditorProjects();
        createGenModel(getCommandline().getArguments());
        break;
      }
    }

    if (generateJavaCode)
    {
      List<String> arguments = getGeneratorArguments();
      generateCodeFromGenModel(arguments.toArray(new String [arguments.size()]));
    }
  }

  abstract protected void createGenModel(String[] arguments) throws Exception;

  protected void addGenModelPathArgument()
  {
    if (genModel != null)
    {
      getCommandline().createArgument(true).setValue(genModel.getAbsolutePath());
      if (reconcileGenModel == GENMODEL_RELOAD)
      {
        getCommandline().createArgument().setValue("-reload");
      }
    }
  }
  
  protected void addModelPathArgument()
  {
    if (model != null)
    {
      getCommandline().createArgument(true).setValue(model.getAbsolutePath());
    }
    
    if (modelLocations != null)
    {
      for (ModelLocation modelLocation : modelLocations)
      {
        String argument = modelLocation.getUri();
        if (argument == null)
        {
          try
          {
            argument = URI.createFileURI(modelLocation.getFile().getCanonicalPath()).toString();
          }
          catch (IOException e)
          {
            argument = URI.createFileURI(modelLocation.getFile().getAbsolutePath()).toString();
          }
        }
        getCommandline().createArgument(true).setValue(argument);
      }
    }    
  }
  
  protected void addGenModelArguments()
  {    
    addGenModelPathArgument();
    addModelPathArgument();
    
    if (modelProject != null)
    {
      getCommandline().createArgument().setValue("-modelProject");
      getCommandline().createArgument().setValue(modelProject.getAbsolutePath());
    }
    
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
    
    if (validateModel != null)
    {
      getCommandline().createArgument().setValue("-validateModel");
      getCommandline().createArgument().setValue(validateModel);
    }
    
    if (jdkLevel != null)
    {
      GenJDKLevel genJDKLevel = getJDKLevel();
      if (genJDKLevel != null)
      {
        getCommandline().createArgument().setValue("-jdkLevel");
        getCommandline().createArgument().setValue(genJDKLevel.getLiteral());        
      }
    }

    if (sdo)
    {
      getCommandline().createArgument().setValue("-sdo");
    }
  }

  protected void adjustEditAndEditorProjects()
  {
    String arguments = getCommandline().toString();
    generateModelProject &= arguments.indexOf("-modelProject") >= 0;
    generateEditProject &= arguments.indexOf("-editProject") >= 0;
    generateEditorProject &= arguments.indexOf("-editorProject") >= 0;
    generateTestsProject &= arguments.indexOf("-testsProject") >= 0;
  }

  protected List<String> getGeneratorArguments()
  {
    List<String> arguments = new ArrayList<String>();

    if (generateModelProject)  arguments.add("-model");
    if (generateEditProject)   arguments.add("-edit");
    if (generateEditorProject) arguments.add("-editor");
    if (generateTestsProject) arguments.add("-tests");
    
    if (autoBuild != null)
    {
      arguments.add("-autoBuild");
      arguments.add(autoBuild.toString());
    }
    
    if (templatePath != null)
    {
      arguments.add("-dynamicTemplates");
    }

    arguments.add(genModel.getAbsolutePath());
    return arguments;
  }

  protected void generateCodeFromGenModel(String[] arguments)
  {
    new Generator().run(arguments);
  }
}