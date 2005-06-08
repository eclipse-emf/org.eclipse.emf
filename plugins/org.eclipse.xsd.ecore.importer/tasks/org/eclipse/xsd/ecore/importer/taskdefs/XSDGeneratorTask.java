/**
 * <copyright> 
 *
 * Copyright (c) 2004-2005 IBM Corporation and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: 
 *   IBM - Initial API and implementation
 *
 * </copyright>
 *
 * $Id: XSDGeneratorTask.java,v 1.2 2005/06/08 06:21:26 nickb Exp $
 */
package org.eclipse.xsd.ecore.importer.taskdefs;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.tools.ant.BuildException;

import org.eclipse.emf.ant.taskdefs.codegen.ecore.GeneratorTask;

import org.eclipse.xsd.ecore.importer.XSDImporterApplication;


/**
 * <p>
 * Generates source code from models specifed in <b>XML Schema</b> files.  Exposes some 
 * functionalities available on the {@link org.eclipse.emf.codegen.ecore.XSD2GenModel} 
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
 * java -classpath <i>eclipseDir</i>/startup.jar org.eclipse.core.launcher.Main
 *      -data <i>worspaceDir</i>
 *      -application org.eclipse.ant.core.antRunner
 *      -buildfile <i>antScript</i>
 * </p>
 * <p>
 * Usage example:
 * </p>
 * <pre>
 * &lt;emf.XSD2Java model=&quot;c:/lib/model/lib.xsd&quot; 
 *                genModel=&quot;c:/lib/emf/lib.genmodel&quot; 
 *                modelProject=&quot;c:/lib&quot; 
 *                modelProjectFragmentPath=&quot;src&quot;
 *                modelPluginID=&quot;emf.lib.example&quot;&gt;
 *        &lt;arg line=&quot;-packages http://www.example.eclipse.org/Library&quot;/&gt;
 *  &lt;/emf.XSD2Java&gt;
 * </pre>
 * <pre>
 * &lt;emf.XSD2Java genModel=&quot;c:/lib/emf/lib.genmodel&quot; 
 *                modelProject=&quot;c:/lib&quot; 
 *                modelProjectFragmentPath=&quot;src&quot;&gt;
 *        &lt;arg line=&quot;-packages http://www.example.eclipse.org/Library&quot;/&gt;
 *        &lt;model uri=&quot;http://www.example.eclipse.org/library.xsd&quot;/&gt;
 *        &lt;model file=&quot;c:/common.xsd&quot;/&gt;
 *  &lt;/emf.XSD2Java&gt;
 * </pre>
 * 
 * @since 2.1.0
 */
public class XSDGeneratorTask extends GeneratorTask
{
  public static class Model
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

  private List models;

  public Model createModel()
  {
    Model model = new Model();
    if (models == null)
    {
      models = new ArrayList();
    }
    models.add(model);
    return model;
  }

  protected void checkAttributes() throws BuildException
  {
    if (models != null)
    {
      useModelAttribute = false;
      for (Iterator i = models.iterator(); i.hasNext();)
      {
        Model model = (Model)i.next();
        assertTrue("Either the 'file' or the 'uri' attributes of a 'model' element must be specified.", model.getFile() != null
          || model.getUri() != null);
      }
    }

    super.checkAttributes();
  }

  protected void addGenModelArguments()
  {
    super.addGenModelArguments();

    if (models != null)
    {
      for (Iterator i = models.iterator(); i.hasNext();)
      {
        Model model = (Model)i.next();
        String argument = model.getUri() != null ? model.getUri() : model.getFile().getAbsolutePath();
        getCommandline().createArgument(true).setValue(argument);
      }
    }
  }

  protected void createGenModel(String[] arguments) throws Exception
  {
    new XSDImporterApplication().run(getProgressMonitor(), arguments);
  }
}