/**
 * Copyright (c) 2005-2007 IBM Corporation and others.
 * All rights reserved.   This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 * 
 * Contributors: 
 *   IBM - Initial API and implementation
 */

package org.eclipse.emf.edit.ui.util;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.eclipse.core.resources.IFile;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IStorage;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.Path;
import org.eclipse.core.runtime.Platform;
import org.eclipse.core.runtime.content.IContentType;
import org.eclipse.ui.IEditorDescriptor;
import org.eclipse.ui.IEditorInput;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IEditorRegistry;
import org.eclipse.ui.IPathEditorInput;
import org.eclipse.ui.IStorageEditorInput;
import org.eclipse.ui.IURIEditorInput;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.part.FileEditorInput;
import org.eclipse.emf.common.EMFPlugin;
import org.eclipse.emf.common.ui.URIEditorInput;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.common.util.UniqueEList;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.URIConverter;
import org.eclipse.emf.ecore.resource.URIHandler;
import org.eclipse.emf.ecore.resource.impl.URIHandlerImpl;
import org.eclipse.emf.edit.ui.EMFEditUIPlugin;
import org.osgi.framework.Bundle;

/**
 * @since 2.2.0
 */
public class EditUIUtil
{
  /**
   * Opens the default editor for the resource that contains the specified
   * EObject.  This method only works if the resource's URI is a 
   * platform resource URI.
   */
  public static boolean openEditor(EObject eObject) throws PartInitException
  {
    if (eObject != null)
    {
      Resource resource = eObject.eResource();
      if (resource != null)
      {
        URI uri = resource.getURI();
        if (uri != null)
        {
          IEditorInput editorInput = null;
          if (uri.isPlatformResource())
          {
            String path = uri.toPlatformString(true);
            IResource workspaceResource = ResourcesPlugin.getWorkspace().getRoot().findMember(new Path(path));
            if (workspaceResource instanceof IFile)
            {
              editorInput = EclipseUtil.createFileEditorInput((IFile)workspaceResource);
            }
          }
          else
          {
            editorInput = new URIEditorInput(uri);
          }
          if (editorInput != null)
          {
            IWorkbench workbench = PlatformUI.getWorkbench();
            IWorkbenchPage page = workbench.getActiveWorkbenchWindow().getActivePage();
            IEditorPart editorPart = page.openEditor(editorInput, workbench.getEditorRegistry().getDefaultEditor(uri.lastSegment()).getId());
            return editorPart != null;
          }
        }
      }
    }
    return false;
  }

  public static URI getURI(IEditorInput editorInput)
  {
    return getURI(editorInput, null);
  }

  /**
   * @since 2.11
   */
  public static URI getURI(IEditorInput editorInput, URIConverter uriConverter)
  {
    URI result = null;
    if (EMFPlugin.IS_ECLIPSE_RUNNING)
    {
      result = EclipseUtil.getURI(editorInput, uriConverter);
    }
    if (result == null)
    {
      if (editorInput instanceof URIEditorInput)
      {
        result = ((URIEditorInput)editorInput).getURI().trimFragment();
      }
      else
      {
        result = URI.createURI(editorInput.getName());
      }
    }
    return result;
  }

  /**
   * Returns the default editor for a given file name. This method is like {@link IEditorRegistry#getDefaultEditor(String)},
   * but it will not return <code>null</code> unless all applicable content types have no associated editor.
   * 
   * @param fileName the file name in the system
   * @return the descriptor of the default editor, or <code>null</code> if not found
   * @since 2.4
   */
  public static IEditorDescriptor getDefaultEditor(String fileName)
  {
    return fileName != null && fileName.length() != 0 ?
      getDefaultEditor(fileName, Platform.getContentTypeManager().findContentTypesFor(fileName)) :
      null;
  }

  /**
   * Returns the default editor for a given contents and file name.
   * <p>
   * If a file name is not provided, the entire content type registry will be queried. For performance reasons, it is
   * highly recommended to provide a file name if available.
   *
   * @param contents an input stream
   * @param fileName the file name associated to the contents, or <code>null</code> 
   * @return the descriptor of the default editor, or <code>null</code> if not found
   * @since 2.4
   */ 
  public static IEditorDescriptor getDefaultEditor(InputStream contents, String fileName)
  {
    if (contents != null)
    {
      try
      {
        return getDefaultEditor(fileName, Platform.getContentTypeManager().findContentTypesFor(contents, fileName));
      }
      catch (IOException e)
      {
        EMFEditUIPlugin.INSTANCE.log(e);
      }
    }
    return getDefaultEditor(fileName);
  }

  private static IEditorDescriptor getDefaultEditor(String fileName, IContentType[] contentTypes)
  {
    IEditorRegistry editorRegistry = PlatformUI.getWorkbench().getEditorRegistry();

    if (contentTypes.length == 0)
    {
      return editorRegistry.getDefaultEditor(fileName, null);
    }

    IEditorDescriptor result = null;
    for (int i = 0; result == null && i < contentTypes.length; i++)
    {
      result = editorRegistry.getDefaultEditor(fileName, contentTypes[i]);
    }
    return result;
  }

  /**
   * Returns the default editor for a given URI. This method actually attempts to open an input stream for the URI and
   * uses its contents, along with the filename (the URI's last segment), to obtain appropriate content types.
   * <p>
   * If a URI converter is specified, it is used to open the stream. Otherwise, the global default
   * {@link URIConverter#INSTANCE instance} is used.
   *
   * @param uri a URI
   * @param uriConverter URI converter from which to obtain an input stream, or <code>null</code>
   * @return the descriptor of the default editor, or <code>null</code> if not found
   * @since 2.4
   */ 
  public static IEditorDescriptor getDefaultEditor(URI uri, URIConverter uriConverter)
  {
    String fileName = URI.decode(uri.lastSegment());
    if (uriConverter == null)
    {
      uriConverter = URIConverter.INSTANCE;
    }
    InputStream stream = null;

    try
    {
      stream = uriConverter.createInputStream(uri);
      return getDefaultEditor(stream, fileName);
    }
    catch (IOException e)
    {
      EMFEditUIPlugin.INSTANCE.log(e);
      return getDefaultEditor(fileName);
    }
    finally
    {
      close(stream);
    }
  }

  private static void close(InputStream stream)
  {
    if (stream != null)
    {
      try
      {
        stream.close();
      }
      catch (IOException e) { EMFEditUIPlugin.INSTANCE.log(e); }
    }
  }

  /**
   * Returns the editors for a given file name. This method is like {@link IEditorRegistry#getEditors(String)},
   * but its result will include editors for all applicable content types.
   * 
   * @param fileName the file name in the system
   * @param defaultsOnly if <code>true</code>, only the default editor for each content type will be included in the result 
   * @return the descriptors of the editors
   * @since 2.4
   */
  public static IEditorDescriptor[] getEditors(String fileName, boolean defaultsOnly)
  {
    return fileName != null && fileName.length() != 0 ?
      getEditors(fileName, Platform.getContentTypeManager().findContentTypesFor(fileName), defaultsOnly) :
      new IEditorDescriptor[0];
  }

  /**
   * Returns the editors for a given contents and file name.
   * <p>
   * If a file name is not provided, the entire content type registry will be queried. For performance reasons, it is
   * highly recommended to provide a file name if available.
   * 
   * @param contents an input stream
   * @param fileName the file name associated to the contents, or <code>null</code> 
   * @param defaultsOnly if <code>true</code>, only the default editor for each content type will be included in the result 
   * @return the descriptors of the editors
   * @since 2.4
   */
  public static IEditorDescriptor[] getEditors(InputStream contents, String fileName, boolean defaultsOnly)
  {
    if (contents != null)
    {
      try
      {
        return getEditors(fileName, Platform.getContentTypeManager().findContentTypesFor(contents, fileName), defaultsOnly);
      }
      catch (IOException e)
      {
        EMFEditUIPlugin.INSTANCE.log(e);
      }
    }    
    return getEditors(fileName, defaultsOnly);
  }

  private static IEditorDescriptor[] getEditors(String fileName, IContentType[] contentTypes, boolean defaultsOnly)
  {
    IEditorRegistry editorRegistry = PlatformUI.getWorkbench().getEditorRegistry();

    if (contentTypes.length == 0)
    {
      return editorRegistry.getEditors(fileName, null);
    }

    List<IEditorDescriptor> result = new UniqueEList<IEditorDescriptor>();
    for (IContentType contentType : contentTypes)
    {
      if (defaultsOnly)
      {
        IEditorDescriptor editor = editorRegistry.getDefaultEditor(fileName, contentType);
        if (editor != null)
        {
          result.add(editor);
        }
      }
      else
      {
        result.addAll(Arrays.asList(editorRegistry.getEditors(fileName, contentType)));        
      }
    }
    return result.toArray(new IEditorDescriptor[result.size()]);
  }

  /**
   * Returns the editors for a given URI. This method actually attempts to open an input stream for the URI and
   * uses its contents, along with the filename (the URI's last segment), to obtain appropriate content types.
   * <p>
   * If a URI converter is specified, it is used to open the stream. Otherwise, the global default
   * {@link URIConverter#INSTANCE instance} is used.
   *
   * @param uri a URI
   * @param uriConverter a URI converter from which to obtain an input stream, or <code>null</code>
   * @param defaultsOnly if <code>true</code>, only the default editor for each content type will be included in the result 
   * @return the descriptors of the editors
   * @since 2.4
   */ 
  public static IEditorDescriptor[] getEditors(URI uri, URIConverter uriConverter, boolean defaultsOnly)
  {
    String fileName = URI.decode(uri.lastSegment());
    if (uriConverter == null)
    {
      uriConverter = URIConverter.INSTANCE;
    }
    InputStream stream = null;

    try
    {
      stream = uriConverter.createInputStream(uri);
      return getEditors(stream, fileName, defaultsOnly);
    }
    catch (IOException e)
    {
      EMFEditUIPlugin.INSTANCE.log(e);
      return getEditors(fileName, defaultsOnly);
    }
    finally
    {
      close(stream);
    }
  }

  private static class StorageURIHandler extends URIHandlerImpl
  {
    final private URI uri;
    final private IStorage storage;

    private StorageURIHandler(URI uri, IStorage storage)
    {
      this.uri = uri;
      this.storage = storage;
    }

    @Override
    public boolean canHandle(URI uri)
    {
      return uri.equals(this.uri);
    }

    @Override
    public InputStream createInputStream(URI uri, Map<?, ?> options) throws IOException
    {
      try
      {
        return storage.getContents();
      }
      catch (CoreException exception)
      {
        throw new Resource.IOWrappedException(exception);
      }
    }
    
    @Override
    public boolean exists(URI uri, Map<?, ?> options)
    {
      return true;
    }

    @Override
    public Map<String, ?> getAttributes(URI uri, Map<?, ?> options)
    {
      Map<String, Object> result = new HashMap<String, Object>();
      Set<String> requestedAttributes = getRequestedAttributes(options);
      if (requestedAttributes == null || requestedAttributes.contains(URIConverter.ATTRIBUTE_READ_ONLY))
      {
          result.put(URIConverter.ATTRIBUTE_READ_ONLY, storage.isReadOnly());
      }

      return result;
    }
  }

  private static class EclipseUtil
  {
    static final Class<?> FILE_CLASS;
    static
    {
      Class<?> fileClass = null;
      try
      {
        fileClass = IFile.class;
      }
      catch (Throwable exception)
      {
        // Ignore any exceptions and assume the class isn't available.
      }
      FILE_CLASS = fileClass;
    }

    static final Class<?> FILE_REVISION_CLASS;
    static final Method FILE_REVISION_GET_URI_METHOD;
    static final Method FILE_REVISION_GET_STORAGE;
    static
    {
      FILE_REVISION_CLASS = loadClass("org.eclipse.team.core", "org.eclipse.team.core.history.IFileRevision");
      FILE_REVISION_GET_URI_METHOD = getMethod(FILE_REVISION_CLASS, "getURI");
      FILE_REVISION_GET_STORAGE = getMethod(FILE_REVISION_CLASS, "getStorage", IProgressMonitor.class);
    }

    static final Class<?> URI_EDITOR_INPUT_CLASS;
    static
    {
      Class<?> uriEditorInputClass = null;
      try
      {
        uriEditorInputClass = IURIEditorInput.class;
      }
      catch (Throwable exception)
      {
        // The class is not available.
      }
      URI_EDITOR_INPUT_CLASS = uriEditorInputClass;
    }

    static final Class<?> STORAGE_CLASS;
    static
    {
      Class<?> storageClass = null;
      try
      {
        storageClass = IStorage.class;
      }
      catch (Throwable exception)
      {
        // Ignore any exceptions and assume the class isn't available.
      }
      STORAGE_CLASS = storageClass;
    }

    static final Class<?> STORAGE_EDITOR_INPUT_CLASS;
    static
    {
      Class<?> storageEditorInputClass = null;
      try
      {
        storageEditorInputClass = IStorageEditorInput.class;
      }
      catch (Throwable exception)
      {
        // The class is not available.
      }
      STORAGE_EDITOR_INPUT_CLASS = storageEditorInputClass;
    }

    static final Class<?> JAR_ENTRY_RESOURCE_CLASS;
    static final Method JAR_ENTRY_RESOURCE_GET_PACKAGE_FRAGMENT_ROOT;
    static
    {
      JAR_ENTRY_RESOURCE_CLASS = loadClass("org.eclipse.jdt.core", "org.eclipse.jdt.core.IJarEntryResource");
      JAR_ENTRY_RESOURCE_GET_PACKAGE_FRAGMENT_ROOT = getMethod(JAR_ENTRY_RESOURCE_CLASS, "getPackageFragmentRoot");
    }

    static final Class<?> NON_JAVA_RESOURCE_CLASS = loadClass("org.eclipse.jdt.core", "org.eclipse.jdt.internal.core.NonJavaResource");
    static final Field NON_JAVA_RESOURCE_FIELD;
    static
    {
      Field nonJavaResourceField = null;
      if (NON_JAVA_RESOURCE_CLASS != null)
      {
        try
        {
          nonJavaResourceField = NON_JAVA_RESOURCE_CLASS.getDeclaredField("resource");
          nonJavaResourceField.setAccessible(true);
        }
        catch (Throwable throwable)
        {
          // Ignore.
        }
      }
      NON_JAVA_RESOURCE_FIELD = nonJavaResourceField;
    }

    private static Class<?> loadClass(String bundleID, String className)
    {
      Bundle bundle = Platform.getBundle(bundleID);
      if (bundle != null && (bundle.getState() & (Bundle.ACTIVE | Bundle.STARTING | Bundle.RESOLVED)) != 0)
      {
        try
        {
          return bundle.loadClass(className);
        }
        catch (Throwable exeption)
        {
          // Ignore any exceptions and assume the class isn't available.
        }
      }
      return null;
    }

    private static Method getMethod(Class<?> theClass, String name, Class<?>... parameterTypes)
    {
      try
      {
        return theClass == null ? null : theClass.getMethod(name, parameterTypes);
      }
      catch (Throwable exception)
      {
        return null;
      }
    }

    private static Object invoke(Method method, Object object, Object... arguments)
    {
      try
      {
        return method == null ? null : method.invoke(object, arguments);
      }
      catch (Throwable throwable)
      {
        return null;
      }
    }

    private static Object invoke(String methodName, Object object)
    {
      if (object != null)
      {
        Method method = getMethod(object.getClass(), methodName);
        if (method != null)
        {
          return invoke(method, object);
        }
      }
      return null;
    }

    public static URI getURI(IEditorInput editorInput, URIConverter uriConverter)
    {
      if (FILE_CLASS != null)
      {
        IFile file = (IFile)editorInput.getAdapter(FILE_CLASS);
        if (file != null)
        {
          return URI.createPlatformResourceURI(file.getFullPath().toString(), true);
        }
      }

      if (FILE_REVISION_CLASS != null)
      {
        Object fileRevision = editorInput.getAdapter(FILE_REVISION_CLASS);
        if (fileRevision != null)
        {
          try
          {
            URI uri = URI.createURI(((java.net.URI)FILE_REVISION_GET_URI_METHOD.invoke(fileRevision)).toString());
            IStorage storage = (IStorage)invoke(FILE_REVISION_GET_STORAGE, fileRevision, new NullProgressMonitor());
            if (storage != null)
            {
              registerHandler(uri, storage, uriConverter);
            }
            return uri;
          }
          catch (Throwable exception)
          {
            EMFEditUIPlugin.INSTANCE.log(exception);
          }
        }
      }

      if (STORAGE_CLASS != null)
      {
        IStorage storage = (IStorage)editorInput.getAdapter(STORAGE_CLASS);
        URI uri = handleStorage(storage, uriConverter);
        if (uri != null)
        {
          return uri;
        }
      }

      if (editorInput instanceof IPathEditorInput)
      {
        IPathEditorInput pathEditorInput = (IPathEditorInput)editorInput;
        IPath path = pathEditorInput.getPath();
        if (path != null)
        {
          return URI.createFileURI(path.toString());
        }
      }

      if (STORAGE_EDITOR_INPUT_CLASS != null)
      {
        if (editorInput instanceof IStorageEditorInput)
        {
          IStorageEditorInput storageEditorInput = (IStorageEditorInput)editorInput;
          try
          {
            IStorage storage = storageEditorInput.getStorage();
            URI uri = handleStorage(storage, uriConverter);
            if (uri != null)
            {
              return uri;
            }
          }
          catch (CoreException e)
          {
            // Ignore
          }
        }
      }

      if (URI_EDITOR_INPUT_CLASS != null)
      {
        if (editorInput instanceof IURIEditorInput)
        {
          return URI.createURI(((IURIEditorInput)editorInput).getURI().toString()).trimFragment();
        }
      }

      @SuppressWarnings("cast")
      URI uri = (URI)editorInput.getAdapter(URI.class);
      if (uri == null)
      {
        @SuppressWarnings("cast")
        java.net.URI javaNetURI = (java.net.URI)editorInput.getAdapter(java.net.URI.class);
        if (javaNetURI != null)
        {
          uri = URI.createURI(javaNetURI.toString());
        }
      }

      return uri;
    }

    private static IFile getNonJavaResourceFieldValue(Object object)
    {
      // This is a very ugly workaround for the fact that org.eclipse.jdt.internal.core.NonJavaResource
      // does not return a meaningful entry name relative to its parent.
      // The entry name will just be the file name and the parent will be the package fragment root
      // which can be arbitrarily many folders up from the entry.
      // There are no methods on the implementation to determine the correct path.
      if (NON_JAVA_RESOURCE_CLASS != null && NON_JAVA_RESOURCE_CLASS.isInstance(object))
      {
        try
        {
          return (IFile)NON_JAVA_RESOURCE_FIELD.get(object);
        }
        catch (Throwable throwable)
        {
          return null;
        }
      }
      return null;
    }

    private static URI handleStorage(IStorage storage, URIConverter uriConverter)
    {
      if (storage != null)
      {
        IPath path = storage.getFullPath();
        if (path != null)
        {
          if (JAR_ENTRY_RESOURCE_CLASS != null && JAR_ENTRY_RESOURCE_CLASS.isInstance(storage))
          {
            IFile  file = getNonJavaResourceFieldValue(storage);
            if (file != null)
            {
              return URI.createPlatformResourceURI(file.getFullPath().toString(), true);
            }

            Object packageFragmentRoot = invoke(JAR_ENTRY_RESOURCE_GET_PACKAGE_FRAGMENT_ROOT, storage);
            if (packageFragmentRoot != null)
            {
              IPath archivePath = (IPath)invoke("getPath", packageFragmentRoot);
              if (archivePath != null)
              {
                return URI.createURI("archive:" + URI.createFileURI(archivePath.toString()) + "!" + path);
              }
            }
          }

          URI uri = URI.createURI(path.toString());
          registerHandler(uri, storage, uriConverter);
          return uri;
        }
      }

      return null;
    }

    private static void registerHandler(URI uri, IStorage storage, URIConverter uriConverter)
    {
      if (uriConverter != null)
      {
        EList<URIHandler> uriHandlers = uriConverter.getURIHandlers();
        for (URIHandler uriHandler : uriHandlers)
        {
          if (uriHandler instanceof StorageURIHandler)
          {
            return;
          }
        }

        URI logicalURI = URI.createURI("storage:/").appendSegments(uri.segments());
        uriConverter.getURIMap().put(uri, logicalURI);
        uriHandlers.add(0, new StorageURIHandler(logicalURI, storage));
      }
    }

    public static IEditorInput createFileEditorInput(IFile file)
    {
      return new FileEditorInput(file);
    }
  }
}
