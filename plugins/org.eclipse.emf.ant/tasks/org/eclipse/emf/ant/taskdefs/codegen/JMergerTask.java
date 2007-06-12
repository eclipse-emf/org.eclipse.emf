/**
 * <copyright>
 *
 * Copyright (c) 2004-2006 IBM Corporation and others.
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
 * $Id: JMergerTask.java,v 1.7 2007/06/12 20:56:07 emerks Exp $
 */
package org.eclipse.emf.ant.taskdefs.codegen;

import java.io.File;
import java.io.IOException;

import org.apache.tools.ant.BuildException;

import org.eclipse.emf.ant.taskdefs.EMFTask;
import org.eclipse.emf.ant.util.Util;
import org.eclipse.emf.codegen.merge.java.JMerger;
import org.eclipse.emf.common.util.BasicMonitor;
import org.eclipse.emf.common.util.URI;


/**
 * <p>
 * Exposes some functionalities available on the 
 * {@link org.eclipse.emf.codegen.merge.java.JMerger JMerger} class. 
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
 * &lt;emf.JMerger mergeXMLURI=&quot;http://www.example.com/merge.xml&quot;
 *              sourceFile=&quot;c:/java/MyClass.java&quot;
 *              targetURI=&quot;http://www.example.com/target/MyNewClass.java&quot;/&gt;
 * </pre>
 * <pre>
 * &lt;emf.JMerger mergeXMLFile=&quot;merge.xml&quot;
 *              sourceURI=&quot;http://www.example.com/source/MyClass.java&quot;
 *              targetFile=&quot;MyNewClass.java&quot;;
 *              newFile=&quot;c:\MyClass.java&quot;/&gt;
 * </pre>
 * <pre>
 * &lt;emf.JMerger mergeXMLFile=&quot;c:\merge-files\merge.xml&quot;
 *              sourceFile=&quot;d:\old\MyClass.java&quot;
 *              targetFile=&quot;d:\new\MyNewClass.java&quot;;
 *              newFile=&quot;MyMergedClass.java&quot;/&gt;
 * </pre>
 * 
 * @since 2.1.0
 */
public class JMergerTask extends EMFTask
{
  private String mergeXMLURI;
  private File mergeXMLFile;
  private String sourceURI;
  private File sourceFile;
  private String targetURI;
  private File targetFile;
  private File newFile;
  private String facadeHelperClass = JMerger.DEFAULT_FACADE_HELPER_CLASS;

  public void setMergeXMLURI(String mergeXMLURI)
  {
    this.mergeXMLURI = mergeXMLURI;
  }

  public void setMergeXMLFile(File mergeXMLFile)
  {
    this.mergeXMLFile = mergeXMLFile;
  }

  public void setSourceURI(String sourceURI)
  {
    this.sourceURI = sourceURI;
  }

  public void setSourceFile(File sourceFile)
  {
    this.sourceFile = sourceFile;
  }

  public void setTargetURI(String targetURI)
  {
    this.targetURI = targetURI;
  }

  public void setTargetFile(File targetFile)
  {
    this.targetFile = targetFile;
  }

  public void setNewFile(File newFile)
  {
    this.newFile = newFile;
  }

  public void setFacadeHelperClass(String facadeHelperClass)
  {
    this.facadeHelperClass = facadeHelperClass;
  }

  @Override
  protected void checkAttributes() throws BuildException
  {
    assertTrue("Either 'mergeXMLURI' or 'mergeXMLFile' must be specified.", mergeXMLURI != null || mergeXMLFile != null);
    assertTrue("Either 'sourceURI' or 'sourceFile' must be specified.", sourceURI != null || sourceFile != null);
    assertTrue("Either 'targetURI' or 'targetFile' must be specified.", targetURI != null || targetFile != null);
  }

  @Override
  protected void doExecute() throws Exception
  {
    invokeMerger(createJMerger());
  }

  protected JMerger createJMerger()
  {
    return new JMerger();
  }

  protected void invokeMerger(JMerger merger) throws IOException, BuildException
  {
    String mergeXML = mergeXMLURI != null ? mergeXMLURI : mergeXMLFile.getAbsolutePath();
    String source = sourceURI != null ? sourceURI : sourceFile.getAbsolutePath();

    String target = null;
    if (targetURI != null)
    {
      target = targetURI;
      if (newFile == null)
      {
        String file = URI.createFileURI(target).toFileString();
        if (file != null)
        {
          newFile = new File(file);
        }
      }
    }
    else
    {
      target = targetFile.getAbsolutePath();
      if (newFile == null)
      {
        newFile = targetFile;
      }
    }

    assertTrue("Cannot write to target", newFile != null);

    String contents = merger.execute(BasicMonitor.toMonitor(getProgressMonitor()), new String []{ mergeXML, source, target, facadeHelperClass });
    Util.writeFile(newFile, contents);
  }
}
