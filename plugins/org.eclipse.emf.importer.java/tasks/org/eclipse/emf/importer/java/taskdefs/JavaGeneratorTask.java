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
package org.eclipse.emf.importer.java.taskdefs;

import java.io.File;
import java.util.List;

import org.apache.tools.ant.BuildException;

import org.eclipse.emf.ant.taskdefs.codegen.ecore.GeneratorTask;
import org.eclipse.emf.common.CommonPlugin;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.importer.java.JavaImporter;
import org.eclipse.emf.importer.java.JavaImporterApplication;


/**
 * <p>
 * Generates source code from models specified using <b>Annotated Java</b>.  Exposes some 
 * functionalities available on the {@link org.eclipse.emf.importer.java.JavaImporterApplication} 
 * class - check its <tt>printUsage()</tt> method for detailed information on the arguments 
 * you can use with this task.
 * </p>
 * <p>
 * This task is supposed to be executed by a Eclipse driver with the 
 * <b>org.eclipse.emf.ant</b> plugin.  It is neither necessary to use Ant's task
 * <tt>TaskDef</tt> to declare this task in a script nor to change the Ant's runtime 
 * classpath.
 * </p>
 * <p>
 * The following command line will start a headless Eclipse instance and run the specified 
 * Ant script.
 * </p>
 * <p>
 * <i>eclipseDir</i>/eclipse
 *      -noSplash
 *      -data <i>worspaceDir</i>
 *      -application org.eclipse.ant.core.antRunner
 *      -buildfile <i>antScript</i>
 * </p>
 * <p>In Windows you should execute <i>eclipsec</i> instead of <i>eclipse</i> in order to have
 * access to the Ant build script console output.</p>
 * <p>You can also use the Eclipse UI to execute an Ant script containing this task by right-clicking the script and selecting 
 * <i>Run As &gt; Ant Build... &gt; JRE &gt; "Run in the same JRE as the workspace&quot;</i>.</p>
 * 
 * <p>
 * Usage example:
 * </p>
 * <pre>
 * &lt;emf.Java2Java genModel=&quot;/lib/emf/lib.genmodel&quot;&gt;
 * </pre>
 * <pre>
 * &lt;emf.Java2Java genModelPath=&quot;/lib/model/lib.genmodel&quot; 
 *                modelProject=&quot;c:/lib&quot; 
 *        &lt;arg line=&quot;-packages http://www.example.eclipse.org/Library&quot;/&gt;
 * &lt;/emf.Ecore2Java&gt;
 * </pre>
 * 
 * <p>Different than the other EMF Generator tasks, this one requires the model
 * specification files, Annotated Java, to be located in a workspace Java project. The 
 * <tt>genModelPath</tt> attribute should be used to indicated the workspace absolute path
 * of the GenModel.  Also, if the genModel's project doesn't exist in the workspace (indicated by 
 * <tt>workspaceDir</tt> in the command line above), the <tt>modelProject</tt> attribute should
 * be used to indicate the model's location.  This directory should contain all the Eclipse's necessary
 * files (such as &quot;.project&quot; and &quot;.classpath&quot;) to define this project as a valid Java project.
 * </p>
 * <p>Also, the following attributes cannot be used within this task:
 * <ul>
 *  <li>model</li>
 *  <li>genModel</li>
 *  <li>modelProjectFragmentPath</li>
 * </ul>
 * </p>
 *  
 * @since 2.3.0
 */
public class JavaGeneratorTask extends GeneratorTask
{
  protected String genModelPath;
  protected JavaImporter javaImporter;
  
  @Override
  public void setModel(File model)
  {
    throw new BuildException("The 'model' attribute cannot be used in this task.");
  }
  
  @Override
  public void setGenModel(File genModel)
  {
    throw new BuildException("The 'genModel' attribute cannot be used in this task.");
  }
  
  @Override
  public void setModelProjectFragmentPath(String modelProjectFragmentPath)
  {
    throw new BuildException("The 'modelProjectFragmentPath' attribute cannot be used in this task.");
  }
  
  public void setGenModelPath(String genModelPath)
  {
    this.genModelPath = genModelPath;
  }
  
  @Override
  protected void createGenModel(String[] arguments) throws Exception
  {
    JavaImporterApplication application = new JavaImporterApplication();
    javaImporter = application.getJavaImporter();
    application.run(getProgressMonitor(), arguments);
  }
  
  @Override
  protected void checkAttributes() throws BuildException
  {
    assertTrue("The 'genModelPath' attribute must be specified.", genModelPath != null);
    assertTrue("The specifed 'templatePath' attribute is not a valid directory.", templatePath == null || templatePath.isDirectory());
  }
  
  @Override
  protected void addGenModelPathArgument()
  {
    if (genModelPath != null)
    {
      getCommandline().createArgument(true).setValue(genModelPath);
      if (reconcileGenModel == GENMODEL_RELOAD)
      {
        getCommandline().createArgument().setValue("-reload");
      }
    }
  }
  
  @Override
  protected List<String> getGeneratorArguments()
  {
    URI genModelURI = CommonPlugin.asLocalURI(javaImporter.getGenModel().eResource().getURI());
    genModel = new File(genModelURI.toFileString());
    return super.getGeneratorArguments();
  }
}