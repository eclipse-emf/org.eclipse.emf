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
 * $Id: JETEmitterTask.java,v 1.1 2004/12/30 08:15:34 marcelop Exp $
 */
package org.eclipse.emf.ant.taskdefs.codegen;


import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.tools.ant.BuildException;

import org.eclipse.emf.ant.taskdefs.EMFTask;
import org.eclipse.emf.ant.util.Util;
import org.eclipse.emf.codegen.jet.JETEmitter;
import org.eclipse.emf.codegen.jet.JETException;


/**
 * <p>
 * Exposes some functionalities available on the 
 * {@link org.eclipse.emf.codegen.jet.JETEmitter JETEmitter} class. 
 * </p>
 * <p>
 * This task is supposed to be executed by a Eclipse driver with the 
 * <b>org.eclipse.emf.ant</b> plugin.  It is neither necessary to use Ant's task
 * <tt>TaskDef</tt> to declare this task in a script nor to change the Ant's runtime 
 * classpath.
 * </p>
 * <p>
 * The following command line will start a headless Eclipse instance and run the specified Ant script.
 * </p>
 * <p>
 * java -classpath <i>eclipseDir</i>/startup.jar org.eclipse.core.launcher.Main
 *      -data <i>worspaceDir</i>
 *      -application org.eclipse.ant.core.antRunner
 *      -buildfile <i>antScript</i>
 * </p>
 * <p>
 * Usage examples:
 * </p>
 * <pre>
 * &lt;emf.JETEmitter templateURI=&quot;http://www.example.com/jetTemplate.txtjet&quot;
 *                 newFile=&quot;c:\output.txt&quot;/&gt;
 * </pre>
 * <pre>
 * &lt;emf.JETEmitter templateFile=&quot;jetTemplate.txtjet&quot;
 *                 newFile=&quot;c:\output.txt&quot;/&gt;
 * </pre>
 * <pre>
 * &lt;emf.JETEmitter templateURI=&quot;d:\templates\jetTemplate.txtjet&quot;
 *                 newFile=&quot;c:\output.txt&quot;&gt;
 *     &lt;variable name=&quot;MY_VAR&quot; pluginID=&quot;com.myplugin;/&gt;
 *     &lt;variable name=&quot;JUNIT_HOME&quot; pluginID=&quot;org.eclipse.jdt.junit;/&gt;
 * &lt;/emf.JETEmitter&gt;
 * </pre>
 * 
 * @since 2.1.0
 */
public class JETEmitterTask extends EMFTask
{
  public static class Variable
  {
    private String name;
    private String pluginID;

    public String getName()
    {
      return name;
    }

    public void setName(String name)
    {
      this.name = name;
    }

    public String getPluginID()
    {
      return pluginID;
    }

    public void setPluginID(String pluginID)
    {
      this.pluginID = pluginID;
    }
  }

  private String templateURI;
  private File templateFile;
  private File newFile;
  private String project;
  private List variables;
  private Object argument;
  private Class argumentClass;

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

  public void setProject(String project)
  {
    this.project = project;
  }

  public Variable createVariable()
  {
    Variable variable = new Variable();
    if (variables == null)
    {
      variables = new ArrayList();
    }
    variables.add(variable);
    return variable;
  }
  
  public void setArgument(Object argument)
  {
    this.argument = argument;
  }

  public void setArgumentClass(Class argumentClass)
  {
    this.argumentClass = argumentClass;
  }
  
  protected void checkAttributes() throws BuildException
  {
    assertTrue("Either 'templateURI' or 'templateFile' must be specified.", templateURI != null || templateFile != null);
    assertTrue("The 'newFile' attribute must be specified.", newFile != null);
  }

  protected void doExecute() throws Exception
  {
    invokeEmitter(createJETEmitter());
  }

  protected JETEmitter createJETEmitter() throws JETException
  {
    JETEmitter emitter = templateURI != null ? new JETEmitter(templateURI) : new JETEmitter(templateFile.getAbsolutePath());

    if (project != null)
    {
      emitter.setProjectName(project);
    }

    if (variables != null)
    {
      for (Iterator i = variables.iterator(); i.hasNext();)
      {
        Variable variable = (Variable)i.next();
        emitter.addVariable(variable.getName(), variable.getPluginID());
      }
    }
          
    return emitter;
  }

  protected void invokeEmitter(JETEmitter emitter) throws JETException, IOException, InstantiationException, IllegalAccessException
  {
    Object[] arguments = null;
    if (argument != null)
    {
      arguments = argument instanceof Object[] ? (Object[])argument : new Object[]{argument};
    }
    else if (argumentClass != null)
    {
      arguments = new Object[]{argumentClass.newInstance()};
    }
    else
    {
      arguments = new Object[1];
    }
    
    String result = emitter.generate(getProgressMonitor(), arguments);
    Util.writeFile(newFile, result);
  }
}
