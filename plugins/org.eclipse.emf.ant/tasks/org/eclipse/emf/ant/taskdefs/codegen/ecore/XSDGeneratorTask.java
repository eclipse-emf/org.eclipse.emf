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
 * $Id: XSDGeneratorTask.java,v 1.1 2004/12/30 08:15:34 marcelop Exp $
 */
package org.eclipse.emf.ant.taskdefs.codegen.ecore;

import org.eclipse.emf.codegen.ecore.XSD2GenModel;


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
 *                modelProjectFragmentPath=&quot;src&quot;&gt;
 *        &lt;arg line=&quot;-packages http://www.example.eclipse.org/Library&quot;/&gt;
 *  &lt;/emf.XSD2Java&gt;
 * </pre>
 * 
 * @since 2.1.0
 */
public class XSDGeneratorTask extends GeneratorTask
{
  protected void createGenModel() throws Exception
  {
    new XSD2GenModel().execute(getProgressMonitor(), getCommandline().getArguments());
  }
}