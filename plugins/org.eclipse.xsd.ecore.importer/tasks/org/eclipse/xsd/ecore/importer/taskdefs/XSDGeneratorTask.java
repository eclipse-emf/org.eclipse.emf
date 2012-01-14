/**
 * Copyright (c) 2004-2006 IBM Corporation and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: 
 *   IBM - Initial API and implementation
 */
package org.eclipse.xsd.ecore.importer.taskdefs;

import org.eclipse.emf.ant.taskdefs.codegen.ecore.GeneratorTask;

import org.eclipse.xsd.ecore.importer.XSDImporterApplication;


/**
 * <p>
 * Generates source code from models specified in <b>XML Schema</b> files.  Exposes some 
 * functionalities available on the {@link XSDImporterApplication}
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
 * &lt;emf.XSD2Java model=&quot;c:/lib/model/lib.xsd&quot; 
 *                genModel=&quot;c:/lib/emf/lib.genmodel&quot; 
 *                modelProject=&quot;c:/lib&quot; 
 *                modelProjectFragmentPath=&quot;src&quot;
 *                modelPluginID=&quot;emf.lib.example&quot;&gt;
 *        &lt;arg line=&quot;-packages http://www.example.eclipse.org/Library&quot;/&gt;
 * &lt;/emf.XSD2Java&gt;
 * </pre>
 * <pre>
 * &lt;emf.XSD2Java genModel=&quot;c:/lib/emf/lib.genmodel&quot; 
 *                modelProject=&quot;c:/lib&quot; 
 *                modelProjectFragmentPath=&quot;src&quot;&gt;
 *        &lt;arg line=&quot;-packages http://www.example.eclipse.org/Library&quot;/&gt;
 *        &lt;model uri=&quot;http://www.example.eclipse.org/library.xsd&quot;/&gt;
 *        &lt;model file=&quot;c:/common.xsd&quot;/&gt;
 * &lt;/emf.XSD2Java&gt;
 * </pre>
 * 
 * @since 2.1.0
 */
public class XSDGeneratorTask extends GeneratorTask
{
  @Override
  protected void createGenModel(String[] arguments) throws Exception
  {
    new XSDImporterApplication().run(getProgressMonitor(), arguments);
  }
}