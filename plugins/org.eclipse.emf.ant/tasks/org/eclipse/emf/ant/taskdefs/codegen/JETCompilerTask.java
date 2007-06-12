/**
 * <copyright>
 *
 * Copyright (c) 2007 IBM Corporation and others.
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
 * $Id: JETCompilerTask.java,v 1.3 2007/06/12 20:56:07 emerks Exp $
 */
package org.eclipse.emf.ant.taskdefs.codegen;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.StringWriter;
import java.io.Writer;

import org.apache.tools.ant.BuildException;

import org.eclipse.emf.codegen.jet.JETCompiler;
import org.eclipse.emf.codegen.jet.JETException;
import org.eclipse.emf.codegen.jet.JETSkeleton;


/**
 * <p>
 * Exposes some functionalities available on the 
 * {@link org.eclipse.emf.codegen.jet.JETCompiler JETCompiler} class. 
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
 * &lt;emf.JETCompiler templateURI=&quot;http://www.example.com/jetTemplate.javajet&quot;/&gt;
 * </pre>
 * <pre>
 * &lt;emf.JETCompiler templateFile=&quot;jetTemplate.javajet&quot;/&gt;
 * </pre>
 * <pre>
 * &lt;emf.JETCompiler templateURI=&quot;d:\templates\jetTemplate.txtjet&quot;
 *                 newFile=&quot;c:\output.txt&quot;/&gt;
 * </pre>
 * 
 * @since 2.3.0
 */
public class JETCompilerTask extends JETTask
{
  protected static final String DEFAULT_EXTENSION = ".java";
  
  protected String encoding;
  protected File sourceDirectory;
  
  public void setSourceDirectory(File sourceDirectory)
  {
    this.sourceDirectory = sourceDirectory;
  }
  
  public void setEncoding(String encoding)
  {
    this.encoding = encoding;
  }
  
  @Override
  protected void checkAttributes() throws BuildException
  {
    assertTrue("Either the 'newFile' or 'sourceDirectory' attributes must be specified.", newFile != null || sourceDirectory != null);
    if (sourceDirectory != null)
    {
      assertTrue("The sourceDirectory must be an existing directory", sourceDirectory.isDirectory());
    }
  }  
  
  @Override
  protected void doExecute() throws Exception
  {
    invokeCompiler(createJETCompiler());
  }

  protected JETCompiler createJETCompiler() throws JETException
  {
    JETCompiler compiler = new JETCompiler(getTemplateURIAsString(), encoding);
    return compiler;
  }

  protected void invokeCompiler(JETCompiler compiler) throws JETException, IOException, InstantiationException, IllegalAccessException
  {
    compiler.parse();
    
    Writer writer = newFile != null ? new FileWriter(newFile) : new StringWriter();      
    compiler.generate(writer);
    
    if (newFile == null)
    {
      JETSkeleton skeleton = compiler.getSkeleton();
      if (skeleton != null)
      {
        String fileName = skeleton.getClassName();
        if (fileName  != null)
        {
          if (!fileName.endsWith(DEFAULT_EXTENSION) && fileName.indexOf('.') < 0)
          {
            fileName = fileName + DEFAULT_EXTENSION;
          }
          
          String packageDirectory = skeleton.getPackageName();
          newFile = packageDirectory != null ?
            new File(sourceDirectory, packageDirectory.replace('.', '/') + "/" + fileName) :
            new File(sourceDirectory, fileName);
          
          newFile.getParentFile().mkdirs();
          
          String contents = ((StringWriter)writer).getBuffer().toString();
          OutputStream outputStream = new BufferedOutputStream(new FileOutputStream(newFile));
          try
          {
            outputStream.write(encoding == null ? contents.getBytes() : contents.getBytes(encoding));
            return;
          }
          finally
          {
            outputStream.close();
          }
        }
      }
      throw new BuildException("Unable to save the file.  Try the scrip again specifying the 'newFile' attribute.");
    }    
  }
}
