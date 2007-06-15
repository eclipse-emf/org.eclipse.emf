/**
 * <copyright>
 *
 * Copyright (c) 2002-2007 IBM Corporation and others.
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
 * $Id: URIConverterImpl.java,v 1.14 2007/06/15 21:57:52 emerks Exp $
 */
package org.eclipse.emf.ecore.resource.impl;


import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;
import java.util.Map;

import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.Path;
import org.eclipse.emf.common.CommonPlugin;
import org.eclipse.emf.common.archive.ArchiveURLConnection;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.plugin.EcorePlugin;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.URIConverter;


/**
 * A highly functional and extensible URI converter implementation.
 * <p>
 * This implementation provides seamless transparent Eclipse integration
 * by supporting the <code>platform:/resource</code> mechanism both inside of Eclipse and outside of Eclipse.
 * Furthermore, although the implementation imports 
 * both {@link org.eclipse.core.runtime} and {@link org.eclipse.core.resources},
 * and hence requires the Eclipse libraries at development time,
 * the implementation does <b>not</b> require them at runtime. 
 * Clients of this implementation must be cautious if they wish to maintain this platform neutral behaviour.
 * </p>
 */
public class URIConverterImpl implements URIConverter
{
  // ECLIPSE-DEPEND-BEGIN 
  /** 
   * An output stream that transfers its contents to an {@link IFile} upon closing.
   */
  public static class PlatformResourceOutputStream extends ByteArrayOutputStream
  {
    protected IFile file;
    protected boolean force;
    protected boolean keepHistory;
    protected IProgressMonitor progressMonitor;
    protected boolean previouslyFlushed;

    public PlatformResourceOutputStream(IFile file,  boolean force, boolean keepHistory, IProgressMonitor progressMonitor)
    {
      this.file = file;
      this.force = force;
      this.keepHistory = keepHistory;
      this.progressMonitor = progressMonitor;
    }

    protected void createContainer(IContainer container) throws IOException
    {
      if (!container.exists())
      {
        if (container.getType() == IResource.FOLDER)
        {
          createContainer(container.getParent());
          try
          {
            ((IFolder)container).create(force, keepHistory, progressMonitor);
          }
          catch (CoreException exception)
          {
            throw new ResourceImpl.IOWrappedException(exception);
          }
        }
      }
    }

    @Override
    public void close() throws IOException 
    {
      flush();
      super.close();
    }

    @Override
    public void flush() throws IOException 
    {
      super.flush();

      if (previouslyFlushed)
      {
        if (count == 0)
        {
          return;
        }
      }
      else
      {
        createContainer(file.getParent());
      }

      byte[] contents = toByteArray();
      InputStream inputStream = new ByteArrayInputStream(contents, 0, contents.length);
  
      try 
      {
        if (previouslyFlushed)
        {
          file.appendContents(inputStream, force, false, progressMonitor); 
        }
        else if (!file.exists())
        {
          file.create(inputStream, false, null);
          previouslyFlushed = true;
        }
        else 
        {
          if (!file.isSynchronized(IResource.DEPTH_ONE))
          {
            file.refreshLocal(IResource.DEPTH_ONE, progressMonitor);
          }
          file.setContents(inputStream, force, keepHistory, progressMonitor); 
          previouslyFlushed = true;
        }
        reset();
      }
      catch (CoreException exception) 
      {
        throw new Resource.IOWrappedException(exception);
      }
    }
  }

  /**
   * Isolated Eclipse workbench utilities.
   */
  public static class WorkbenchHelper
  {
    /**
     * Creates an output stream for the given {@link IFile} path.
     * <p>
     * This implementation uses a {@link URIConverterImpl.PlatformResourceOutputStream}.
     * </p>
     * @return an open output stream.
     * @exception IOException if there is a problem obtaining an open output stream.
     * @see IWorkspaceRoot#getFile(org.eclipse.core.runtime.IPath)
     * @see URIConverterImpl.PlatformResourceOutputStream
     * @see IFile#setContents(InputStream, boolean, boolean, IProgressMonitor)
     */
    public static OutputStream createPlatformResourceOutputStream(String platformResourcePath) throws IOException
    {
      IFile file = workspaceRoot.getFile(new Path(platformResourcePath));
      return new PlatformResourceOutputStream(file, false, true, null);
    }

    /**
     * Creates an input stream for the given {@link IFile} path.
     * <p>
     * This implementation uses {@link IFile#getContents() IFile.getContents}.
     * </p>
     * @return an open input stream.
     * @see IWorkspaceRoot#getFile(org.eclipse.core.runtime.IPath)
     * @see IFile#getContents()
     * @exception IOException if there is a problem obtaining an open input stream.
     */
    public static InputStream createPlatformResourceInputStream(String platformResourcePath) throws IOException
    {
      IFile file = workspaceRoot.getFile(new Path(platformResourcePath));
      try
      {
        if (!file.isSynchronized(IResource.DEPTH_ONE))
        {
          file.refreshLocal(IResource.DEPTH_ONE, null);
        }
        return file.getContents();
      }
      catch (CoreException exception)
      {
        throw new Resource.IOWrappedException(exception);
      }
    }
  }

  /**
   * The cached Eclipse workspace.
   */
  protected static IWorkspaceRoot workspaceRoot = EcorePlugin.getWorkspaceRoot();

  private static Map<String, Boolean> efsScheme;
  private static final Method EFS_GET_FILE_SYSTEM_METHOD;
  private static final Method EFS_GET_STORE_METHOD;
  private static final Method FILE_STORE_OPEN_INPUT_STREAM_METHOD;
  private static final Method FILE_STORE_OPEN_OUTPUT_STREAM_METHOD;
  static
  {
    Method efsGetStoreMethod = null;
    Method efsGetFileSystemMethod = null;
    Method fileStoreOpenInputStreamMethod = null;
    Method fileStoreOpenOutputStreamMethod = null;
    try
    {
      Class <?> efsClass  = CommonPlugin.loadClass("org.eclipse.core.filesystem", "org.eclipse.core.filesystem.EFS");
      efsGetStoreMethod = efsClass.getMethod("getStore", java.net.URI.class);
      efsGetFileSystemMethod = efsClass.getMethod("getFileSystem", String.class);
      Class <?> fileStoreClass = efsGetStoreMethod.getReturnType();
      fileStoreOpenInputStreamMethod = fileStoreClass.getMethod("openInputStream", Integer.TYPE, IProgressMonitor.class);
      fileStoreOpenOutputStreamMethod = fileStoreClass.getMethod("openOutputStream", Integer.TYPE, IProgressMonitor.class);
    }
    catch (Throwable exeption)
    {
      // Ignore any exceptions and assume the class isn't available.
    }
    EFS_GET_STORE_METHOD = efsGetStoreMethod;
    EFS_GET_FILE_SYSTEM_METHOD = efsGetFileSystemMethod;
    FILE_STORE_OPEN_INPUT_STREAM_METHOD = fileStoreOpenInputStreamMethod;
    FILE_STORE_OPEN_OUTPUT_STREAM_METHOD = fileStoreOpenOutputStreamMethod;
  }
  // ECLIPSE-DEPEND-END

  /**
   * A map that remaps URIs.
   */
  public interface URIMap extends Map<URI, URI>
  {
    /**
     * Returns the remapped URI, or the URI itself.
     * @param uri the URI to remap.
     * @return the remapped URI, or the URI itself.
     */
    URI getURI(URI uri);
  }

  /** 
   * The URI map.
   */
  protected URIMap uriMap;

  /**
   * Creates an instance.
   */
  public URIConverterImpl()
  {
    // This is a way to test stand-alone platform:/resource support in what's really an headless environment.
    //
    // org.eclipse.core.resources.IProject [] projects = workspaceRoot.getProjects();
    // for (int i = 0; i < projects.length; ++i)
    // {
    //   String rootContainerName = projects[i].getName();
    //   URI rootContainerLocation = URI.createFileURI(projects[i].getLocation().toString() + "/");
    //   platformResourceMap.put(rootContainerName, rootContainerLocation);
    // }
    //
    // workspaceRoot = null;
  }
  
  /**
   * Returns whether the scheme is one that this implementation should treat as an archive.
   * This implementation returns <code>true</code> when the scheme is <code>"archive"</code>.
   * @param scheme the scheme to consider.
   * @return whether the scheme is one that this implementation treats as an archive.
   */
  protected boolean isArchiveScheme(String scheme)
  {
    return "archive".equals(scheme);
  }

  /**
   * Returns whether the scheme is one that this implementation should treat as a supported Eclipse File System scheme.
   * This implementation uses Java reflection to check whether there is an Eclipse File System available and if so whether it supports this scheme.
   * @param scheme the scheme to consider.
   * @return whether the scheme is one that this implementation treats as an Eclipse File System scheme.
   */
  protected boolean isEFSScheme(String scheme)
  {
    if (EFS_GET_FILE_SYSTEM_METHOD == null)
    {
      return false;
    }
    else
    {
      Boolean result = efsScheme == null ? null : efsScheme.get(scheme);
      if (result == null)
      {
        try
        {
          result = EFS_GET_FILE_SYSTEM_METHOD.invoke(null, scheme) != null;
        }
        catch (Throwable exception)
        {
          result = Boolean.FALSE;
        }
        Map<String, Boolean> map = new HashMap<String, Boolean>();
        if (efsScheme != null)
        {
          map.putAll(efsScheme);
        }
        map.put(scheme, result);
        efsScheme = map;
      }
      return result == Boolean.TRUE;
    }
  }

  /**
   * Creates an output stream for the URI and returns it.
   * <p>
   * This implementation {@link #normalize normalizes} the URI and uses that as the basis for further processing.
   * A {@link URI#isFile() file-based} URI is {@link URI#toFileString converted} to a file path, e.g.,
   *<pre>
   *  file:///C:/directory/file
   *    ->
   *   C:/directory/file
   *</pre>
   * and is delegated to {@link #createFileOutputStream createFileOutputStream}.
   * An {@link #isArchiveScheme(String) archive-based} URI is delegated to  {@link #createArchiveOutputStream createArchiveOutputStream}.
   * A {@link URI#isPlatformResource() platform-based} URI is {@link URI#toPlatformString(boolean) converted} to a platform path 
   * by trimming the leading <code>platform:/resource</code>, e.g.,
   *<pre>
   *  platform:/resource/project/directory/file 
   *    ->
   *  /project/directory/file 
   *</pre>
   * and is delegated to {@link #createPlatformResourceOutputStream createPlatformResourceOutputStream}.
   * An {@link #isEFSScheme(String) EFS-based} URI is delegated to {@link #createEFSInputStream(URI) createEFSOutputStream}.
   * And all other cases are handled as standard URLs by {@link #createURLOutputStream createURLOutputStream}.
   * </p>
   * @return an open output stream.
   * @exception IOException if there is a problem obtaining an open output stream.
   */
  public OutputStream createOutputStream(URI uri) throws IOException
  {
    URI converted = normalize(uri);
    if (converted.isFile())
    {
      String filePath = converted.toFileString();
      return createFileOutputStream(filePath);
    }
    else 
    {
      String scheme = converted.scheme();
      if (isArchiveScheme(scheme))
      {
        return createArchiveOutputStream(converted);  
      }
      else if (converted.isPlatformResource())
      {
        return createPlatformResourceOutputStream(converted.toPlatformString(true));
      }
      else if (isEFSScheme(scheme))
      {
        return createEFSOutputStream(converted);
      }
      else
      {
        return createURLOutputStream(converted);
      }
    }
  }

  /**
   * Creates an output stream for the file path and returns it.
   * <p>
   * This implementation allocates a {@link FileOutputStream} and creates subdirectories as necessary.
   * </p>
   * @return an open output stream.
   * @exception IOException if there is a problem obtaining an open output stream.
   */
  protected OutputStream createFileOutputStream(String filePath) throws IOException
  {
    File file = new File(filePath);
    String parent = file.getParent();
    if (parent != null)
    {
      new File(parent).mkdirs();
    }
    OutputStream outputStream = new FileOutputStream(file);
    return outputStream;
  }
  
  /**
   * Creates an output stream for the archive access.
   * </p>
   * @return an open output stream.
   * @exception IOException if there is a problem obtaining an open output stream.
   */
  protected OutputStream createArchiveOutputStream(URI archiveURI) throws IOException
  {
    return createArchive(archiveURI).getOutputStream();
  }

  /**
   * Creates an output stream for the platform resource path and returns it.
   * <p>
   * This implementation does one of two things, depending on the runtime environment.
   * If there is an Eclipse workspace, it delegates to 
   * {@link WorkbenchHelper#createPlatformResourceOutputStream WorkbenchHelper.createPlatformResourceOutputStream},
   * which gives the expected Eclipse behaviour.
   * Otherwise, the {@link EcorePlugin#resolvePlatformResourcePath resolved} URI 
   * is delegated to {@link #createOutputStream createOutputStream}
   * for recursive processing.
   * @return an open output stream.
   * @exception IOException if there is a problem obtaining an open output stream or a valid interpretation of the path.
   * @see EcorePlugin#resolvePlatformResourcePath(String)
   */
  protected OutputStream createPlatformResourceOutputStream(String platformResourcePath) throws IOException
  {
    // ECLIPSE-DEPEND-BEGIN
    if (workspaceRoot != null)
    {
      return WorkbenchHelper.createPlatformResourceOutputStream(platformResourcePath);
    }
    else
    // ECLIPSE-DEPEND-END
    {
      URI resolvedLocation = EcorePlugin.resolvePlatformResourcePath(platformResourcePath);
      if (resolvedLocation != null)
      {
        return createOutputStream(resolvedLocation);
      }

      throw new IOException("The path '" + platformResourcePath + "' is unmapped");
    }
  }

  /**
   * Creates an output stream for the URI, assuming it's a URI recognized by the Eclipse File System, and returns it.
   * @return an open output stream.
   * @exception IOException if there is a problem obtaining an open output stream.
   */
  protected OutputStream createEFSOutputStream(URI uri) throws IOException
  {
    if (EFS_GET_STORE_METHOD != null)
    {
      try
      {
        Object store = EFS_GET_STORE_METHOD.invoke(null, new java.net.URI(uri.toString()));
        if (store != null)
        {
          return (OutputStream)FILE_STORE_OPEN_OUTPUT_STREAM_METHOD.invoke(store, 0, null);
        }
      }
      catch (Exception exception)
      {
        throw new Resource.IOWrappedException(exception);
      }
    }
    throw new IOException("EFS unavailable");
  }

  /**
   * Creates an output stream for the URI, assuming it's a URL, and returns it.
   * @return an open output stream.
   * @exception IOException if there is a problem obtaining an open output stream.
   */
  protected OutputStream createURLOutputStream(URI uri) throws IOException
  {
    try
    {
      URL url = new URL(uri.toString());
      URLConnection urlConnection = url.openConnection();
      urlConnection.setDoOutput(true);
      return urlConnection.getOutputStream();
    }
    catch (RuntimeException exception)
    {
      throw new Resource.IOWrappedException(exception);
    }
  }

  /**
   * Creates an input stream for the URI and returns it.
   * <p>
   * This implementation {@link #normalize normalizes} the URI and uses that as the basis for further processing.
   * A {@link URI#isFile() file-based} URI is {@link URI#toFileString converted} to a file path, e.g.,
   *<pre>
   *  file:///C:/directory/file
   *    ->
   *   C:/directory/file
   *</pre>
   * and is delegated to {@link #createFileInputStream createFileInputStream}.
   * An {@link #isArchiveScheme(String) archive-based} URI is delegated to  {@link #createArchiveInputStream createArchiveInputStream}.
   * A {@link URI#isPlatformResource() platform-based} URI is {@link URI#toPlatformString(boolean) converted} to a platform path 
   * by trimming the leading <code>platform:/resource</code>, e.g.,
   *<pre>
   *  platform:/resource/project/directory/file 
   *    ->
   *  /project/directory/file 
   *</pre>
   * and is delegated to {@link #createPlatformResourceInputStream createPlatformResourceInputStream}.
   * An {@link #isEFSScheme(String) EFS-based} URI is delegated to {@link #createEFSInputStream(URI) createEFSInputStream}.
   * And all other cases are handled as standard URLs by {@link #createURLInputStream createURLInputStream}.
   * </p>
   * @return an open input stream.
   * @exception IOException if there is a problem obtaining an open input stream.
   */
  public InputStream createInputStream(URI uri) throws IOException
  {
    URI converted = normalize(uri);
    if (converted.isFile())
    {
      String filePath = converted.toFileString();
      return createFileInputStream(filePath);
    }
    else
    {
      String scheme = converted.scheme();
      if (isArchiveScheme(scheme))
      {
        return createArchiveInputStream(converted);
      }
      else if (converted.isPlatformResource())
      {
        return createPlatformResourceInputStream(converted.toPlatformString(true));
      }      
      else if (isEFSScheme(scheme))
      {
        return createEFSInputStream(converted);
      }
      else
      {
        return createURLInputStream(converted);
      }
    }
  }

  /**
   * Creates an input stream for the file path and returns it.
   * <p>
   * This implementation allocates a {@link FileInputStream}.
   * </p>
   * @return an open input stream.
   * @exception IOException if there is a problem obtaining an open input stream.
   */
  protected InputStream createFileInputStream(String filePath) throws IOException
  {
    File file = new File(filePath);
    InputStream inputStream = new FileInputStream(file);
    return inputStream;
  }
  
  /**
   * A specialized class for reading from an archive.
   */
  protected class Archive extends ArchiveURLConnection
  {
    public Archive(URI uri)
    {
      super(uri.toString());
    }
    
    @Override
    protected boolean emulateArchiveScheme()
    {
      return false;
    }
    
    @Override
    protected boolean useZipFile()
    {
      return true;
    }
    
    @Override
    protected InputStream createInputStream(String nestedURL) throws IOException
    {
      return URIConverterImpl.this.createInputStream(URI.createURI(nestedURL));
    }
    
    @Override
    protected OutputStream createOutputStream(String nestedURL) throws IOException
    {
      return URIConverterImpl.this.createOutputStream(URI.createURI(nestedURL));
    }
  }
  
  protected Archive createArchive(URI uri)
  {
    return new Archive(uri);
  }
  
  /**
   * Creates an input stream for the archive paths and returns it.
   * It uses {@link Archive} to implement read access.
   * </p>
   * @return an open input stream.
   * @exception IOException if there is a problem obtaining an open input stream.
   */
  protected InputStream createArchiveInputStream(URI archiveURI) throws IOException
  {
    return createArchive(archiveURI).getInputStream();
  }

  /**
   * Creates an input stream for the platform resource path and returns it.
   * <p>
   * This implementation does one of two things, depending on the runtime environment.
   * If there is an Eclipse workspace, it delegates to 
   * {@link WorkbenchHelper#createPlatformResourceInputStream WorkbenchHelper.createPlatformResourceInputStream},
   * which gives the expected Eclipse behaviour.
   * Otherwise, the {@link EcorePlugin#resolvePlatformResourcePath resolved} URI 
   * is delegated to {@link #createInputStream createInputStream}
   * for recursive processing.
   * @return an open input stream.
   * @exception IOException if there is a problem obtaining an open input stream or a valid interpretation of the path.
   * @see EcorePlugin#resolvePlatformResourcePath(String)
   */
  protected InputStream createPlatformResourceInputStream(String platformResourcePath) throws IOException
  {
    // ECLIPSE-DEPEND-BEGIN
    if (workspaceRoot != null)
    {
      return WorkbenchHelper.createPlatformResourceInputStream(platformResourcePath);
    }
    else
    // ECLIPSE-DEPEND-END
    {
      URI resolvedLocation = EcorePlugin.resolvePlatformResourcePath(platformResourcePath);
      if (resolvedLocation != null)
      {
        return createInputStream(resolvedLocation);
      }

      throw new IOException("The path '" + platformResourcePath + "' is unmapped");
    }
  }

  /**
   * Creates an input stream for the URI, assuming it's a URI recognized by the Eclipse File System, and returns it.
   * @return an open input stream.
   * @exception IOException if there is a problem obtaining an open input stream.
   */
  protected InputStream createEFSInputStream(URI uri) throws IOException
  {
    if (EFS_GET_STORE_METHOD != null)
    {
      try
      {
        Object store = EFS_GET_STORE_METHOD.invoke(null, new java.net.URI(uri.toString()));
        if (store != null)
        {
          return (InputStream)FILE_STORE_OPEN_INPUT_STREAM_METHOD.invoke(store, 0, null);
        }
      }
      catch (Exception exception)
      {
        throw new Resource.IOWrappedException(exception);
      }
    }
    throw new IOException("EFS unavailable");
  }

  /**
   * Creates an input stream for the URI, assuming it's a URL, and returns it.
   * @return an open input stream.
   * @exception IOException if there is a problem obtaining an open input stream.
   */
  protected InputStream createURLInputStream(URI uri) throws IOException
  {
    try
    {
      URL url = new URL(uri.toString());
      URLConnection urlConnection = url.openConnection();
      return urlConnection.getInputStream();
    }
    catch (RuntimeException exception)
    {
      throw new Resource.IOWrappedException(exception);
    }
  }

  /**
   * Returns the normalized form of the URI.
   * <p>
   * This implementation does precisely and only the {@link URIConverter#normalize typical} thing.
   * It calls itself recursively so that mapped chains are followed.
   * </p>
   * @param uri the URI to normalize.
   * @return the normalized form.
   * @see org.eclipse.emf.ecore.plugin.EcorePlugin#getPlatformResourceMap
   */
  public URI normalize(URI uri)
  {
    String fragment = uri.fragment();
    URI result = 
      fragment == null ? 
        getInternalURIMap().getURI(uri) :
        getInternalURIMap().getURI(uri.trimFragment()).appendFragment(fragment);
    String scheme = result.scheme();
    if (scheme == null)
    {
      // ECLIPSE-DEPEND-BEGIN
      if (workspaceRoot != null)
      {
        if (result.hasAbsolutePath())
        {
          result = URI.createPlatformResourceURI(result.trimFragment().toString(), false);
          if (fragment != null)
          {
            result = result.appendFragment(fragment);
          }
        }
      }
      else 
      // ECLIPSE-DEPEND-END
      {
        if (result.hasAbsolutePath())
        {
          result = URI.createURI("file:" + result);
        }
        else
        {
          result = URI.createFileURI(new File(result.trimFragment().toString()).getAbsolutePath());
          if (fragment != null)
          {
            result = result.appendFragment(fragment);
          }
        }
      }
    }

    if (result.equals(uri))
    {
      return uri;
    }
    else
    {
      return normalize(result);
    }
  }

  /*
   * Javadoc copied from interface.
   */
  public Map<URI, URI> getURIMap()
  {
    return getInternalURIMap();
  }

  /**
   * Returns the internal version of the URI map.
   * @return the internal version of the URI map.
   */
  protected URIMap getInternalURIMap()
  {
    if (uriMap == null)
    {
      URIMappingRegistryImpl mappingRegistryImpl = 
        new URIMappingRegistryImpl()
        {
          private static final long serialVersionUID = 1L;

          @Override
          protected URI delegatedGetURI(URI uri)
          {
            return URIMappingRegistryImpl.INSTANCE.getURI(uri);
          }
        };

      uriMap = (URIMap)mappingRegistryImpl.map();
    }

    return uriMap;
  }
}
