/**
 * Copyright (c) 2012 Eclipse contributors and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 */
package org.eclipse.emf.ecore.xcore.ui.quickfix;


import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.util.Collections;

import org.apache.log4j.Logger;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.FileLocator;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Platform;
import org.eclipse.core.runtime.SubMonitor;
import org.eclipse.emf.common.util.URI;
import org.eclipse.jdt.core.IClasspathEntry;
import org.eclipse.jdt.core.IJavaProject;
import org.eclipse.jdt.core.JavaCore;
import org.eclipse.jdt.core.JavaModelException;
import org.eclipse.xtext.util.MergeableManifest;
import org.osgi.framework.Bundle;


public class XcoreClasspathUpdater
{
  private static final Logger LOG = Logger.getLogger(XcoreClasspathUpdater.class);

  private static final String PLUGIN_NATURE = "org.eclipse.pde.PluginNature";

  public void addBundle(IJavaProject javaProject, String bundleID, IProgressMonitor monitor)
  {
    try
    {
      SubMonitor progress = SubMonitor.convert(monitor, 2);
      IProject project = javaProject.getProject();
      if (!project.hasNature(PLUGIN_NATURE) || !addBundleToManifest(project, bundleID, progress.newChild(1)))
      {
        Bundle bundle = Platform.getBundle(bundleID);
        if (bundle != null)
        {
          URL entry = bundle.getEntry("/");
          if (entry != null)
          {
            URI uri = URI.createURI(FileLocator.resolve(entry).toURI().toString());
            if (uri.isArchive())
            {
              String authority = uri.authority();
              uri  = URI.createURI(authority.substring(0, authority.length() - 1));
            }
            if (uri.isFile())
            {
              String location = uri.toFileString();
              File binFolder = new File(location, "bin");
              if (binFolder.isDirectory())
              {
                location = binFolder.toString();
              }
              addJarToClasspath(javaProject, new Path(location), progress.newChild(1));
            }
          }
        }
      }
    }
    catch (Exception exception)
    {
      LOG.error("Error adding '" + bundleID + "' to the classpath", exception);
    }
  }

  protected boolean addJarToClasspath(IJavaProject javaProject, IPath location, IProgressMonitor monitor) throws JavaModelException
  {
    IClasspathEntry newClasspthEntry = JavaCore.newLibraryEntry(location, null, null);
    IClasspathEntry[] rawClasspath = javaProject.getRawClasspath();
    IClasspathEntry[] newRawClasspath = new IClasspathEntry [rawClasspath.length + 1];
    for (int i = 0; i < rawClasspath.length; ++i)
    {
      IClasspathEntry entry = rawClasspath[i];
      if (entry.getEntryKind() == IClasspathEntry.CPE_LIBRARY && entry.getPath().equals(location))
      {
        return false;
      }
      newRawClasspath[i + 1] = entry;
    }
    newRawClasspath[0] = newClasspthEntry;
    javaProject.setRawClasspath(newRawClasspath, monitor);
    return true;
  }

  protected boolean addBundleToManifest(IProject project, String bundleID, IProgressMonitor monitor) throws IOException, CoreException
  {
    IResource manifestResource = project.findMember("META-INF/MANIFEST.MF");
    if (manifestResource != null && manifestResource.isAccessible() && !manifestResource.getResourceAttributes().isReadOnly() && manifestResource instanceof IFile)
    {
      OutputStream output = null;
      InputStream input = null;
      try
      {
        IFile manifestFile = (IFile)manifestResource;
        MergeableManifest manifest = new MergeableManifest(manifestFile.getContents());
        manifest.addRequiredBundles(Collections.singleton(bundleID));
        if (manifest.isModified())
        {
          ByteArrayOutputStream out = new ByteArrayOutputStream();
          output = new BufferedOutputStream(out);
          manifest.write(output);
          ByteArrayInputStream in = new ByteArrayInputStream(out.toByteArray());
          input = new BufferedInputStream(in);
          manifestFile.setContents(input, true, true, monitor);
        }
        return true;
      }
      finally
      {
        if (output != null)
        {
          output.close();
        }
        if (input != null)
        {
          input.close();
        }
      }
    }
    return false;
  }
}