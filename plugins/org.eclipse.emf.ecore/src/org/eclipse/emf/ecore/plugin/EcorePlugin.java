/**
 * <copyright>
 *
 * Copyright (c) 2002-2004 IBM Corporation and others.
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
 * $Id: EcorePlugin.java,v 1.1 2004/03/06 17:31:31 marcelop Exp $
 */
package org.eclipse.emf.ecore.plugin;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.eclipse.core.resources.IWorkspaceRoot;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IPluginDescriptor;

import org.eclipse.emf.common.EMFPlugin;
import org.eclipse.emf.common.util.ResourceLocator;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.common.util.WrappedException;
// ECLIPSE-DEPEND-END


/**
 * A collection of platform-neutral static utilities
 * as well as Eclipse support utilities.
 */
public class EcorePlugin 
  // ECLIPSE-DEPEND-BEGIN
  extends EMFPlugin
  // ECLIPSE-DEPEND-END

{
  /**
   * The singleton instance of the plugin.
   */
  public static final EcorePlugin INSTANCE = new EcorePlugin();

  /**
   * Creates the singleton instance.
   */
  private EcorePlugin()
  {
    // ECLIPSE-DEPEND-BEGIN
    super(new ResourceLocator[] {});
    // ECLIPSE-DEPEND-END
  }

  // ECLIPSE-DEPEND-BEGIN
  /*
   * Javadoc copied from base class.
   */
  public ResourceLocator getPluginResourceLocator()
  {
    return plugin;
  }
  // ECLIPSE-DEPEND-END

  /**
   * Returns the platform resource map.
   * <p>
   * This map is from {@link String} to {@link URI}.
   * It is the logical equivalent of the map implied by an {@link IWorkspaceRoot}:
   * I.e., each entry in the map corresponds to
   * an {@link org.eclipse.core.resources.IProject} 
   * that has a {@link org.eclipse.core.resources.IProject#getName name} 
   * and a location {@link org.eclipse.core.resources.IProject#getLocation location};
   * the name is the key 
   * and the location, interpretted as a {@link URI#createFileURI file URI}, is the value.
   * This map is used to {@link #resolvePlatformResourcePath resolve} a platform resource path,
   * and thereby supports relocatable projects in a manner that is transparently the same as an Eclipse workspace.
   * </p>
   * @return the platform resource map.
   * @see #resolvePlatformResourcePath
   */
  public static Map getPlatformResourceMap()
  {
    if (platformResourceMap == null)
    {
      platformResourceMap = new HashMap();
    }
    return platformResourceMap;
  }

  /**
   * Resolves a platform resource path of the form <code>"/project/path"</code> 
   * against the platform resource map.
   * <p>
   * The first segment of the path, i.e., the <em>project name</em>,
   * is used to get a URI from the {@link #getPlatformResourceMap() map}.
   * If a URI results, the remaining segments are {@link URI#resolve resolved} against it
   * and that is the result.
   * Otherwise, the result is <code>null</code>.
   * For example, given this mapping
   *<pre>
   *  EcoreUtil.getPlatformResourceMap().put
   *    ("project", URI.createURI("file:///C:/location/"));
   *</pre>
   * the following transformation would result:
   *<pre>
   *  /project/directory/file
   *    ->
   *  file:///C:/location/directory/file
   *</pre>
   * </p>
   * @return the resolved URI or <code>null</code>.
   */
  public static URI resolvePlatformResourcePath(String platformResourcePath)
  {
    int index = platformResourcePath.indexOf("/", 1);
    String rootContainerName = platformResourcePath.substring(1, index);
    String relativeName = platformResourcePath.substring(index + 1);
    URI rootContainerLocation = (URI)platformResourceMap.get(rootContainerName);
    return 
      rootContainerLocation != null ?
        URI.createURI(relativeName).resolve(rootContainerLocation) :
         null;
  }

  /**
   * Handles recognized platform resource arguments and returns the stripped result.
   * <p>
   * Recognized arguments are of this form:
   *<pre>
   *  -platformResource ( &lt;project-name> &lt;file-or-URI> )+
   *</pre>
   * E.g., This these arguments
   *<pre>
   *  -platformResource project file:///C:/location/
   *</pre>
   * will produce this effect:
   *<pre>
   *  EcoreUtil.getPlatformResourceMap().put
   *    ("project", URI.createURI("file:///C:/location/"));
   *</pre>
   * This mechanism supports relocatable projects outside of Eclipse.
   * </p>
   * @param arguments an array of "command line" options.
   * @return the arguments stripped of those recognized as platform resource options.
   */
  public static String [] handlePlatformResourceOptions(String [] arguments)
  {
    getPlatformResourceMap();

    for (int i = 0; i < arguments.length; ++i)
    {
      if (arguments[i].equalsIgnoreCase("-platformResource"))
      {
        int start = i;
        while (++i < arguments.length && !arguments[i].startsWith("-"))
        {
          String rootContainerName = arguments[i];
          if (++i < arguments.length)
          {
            String rootContainerLocation = arguments[i];

            // This let's us test whether the string exists as a file.
            // If not, we try as a URI.
            //
            URI uri;
            File file = new File(rootContainerLocation);
            if (file.isDirectory() || !file.exists() && file.getParent() != null && file.getParentFile().isDirectory())
            {
              try
              {
                file = file.getCanonicalFile();
              }
              catch (IOException exception)
              {
                throw new WrappedException(exception);
              }
              uri = URI.createFileURI(file.toString() + "/");
            }
            else
            {
              uri = URI.createURI(rootContainerLocation);
            }

            platformResourceMap.put(rootContainerName, uri);
          }
        }

        String [] remainingArguments = new String [arguments.length - (i - start)];
        System.arraycopy(arguments, 0, remainingArguments, 0, start);
        System.arraycopy(arguments, i, remainingArguments, start, arguments.length - i);
        return remainingArguments;
      }
    }

    return arguments;
  }

  /**
   * The platform resource map.
   * @see #getPlatformResourceMap
   */
  private static Map platformResourceMap;

  // ECLIPSE-DEPEND-BEGIN
  /** 
   * A plugin implementation that handles Ecore plugin registration.
   * @see #startup
   */
  static public class Implementation extends EclipsePlugin
  {
    /**
     * Creates the singleton instance.
     * @param descriptor the initialization data for the plugin.
     */
    public Implementation(IPluginDescriptor descriptor)
    {
      super(descriptor);
      plugin = this;
    }
  
    /**
     * Starts up this plugin by reading some extensions and populating the relevant registries.
     * <p>
     * The {@link org.eclipse.emf.ecore.EPackage.Registry#INSTANCE global} package registry
     * is populated by plugin registration of the form:
     *<pre>
     *  &lt;extension point="org.eclipse.emf.ecore.generated_package" >
     *      &lt;package uri="http://www.example.org/abc/Abc.ecore" class="org.example.abc.AbcPackage"/>
     *  &lt;extension>
     *</pre>
     * </p>
     * The URI is arbitrary but an absolute URI is recommended.
     * Provision for access to the serialized model via <code>"http:"</code> is encouraged.
     * <p>
     * The {@link org.eclipse.emf.ecore.resource.Resource.Factory.Registry#INSTANCE global} resource factory registry's 
     * {@link org.eclipse.emf.ecore.resource.Resource.Factory.Registry#getExtensionToFactoryMap() extension} map
     * is populated by plugin registration of the form:
     *<pre>
     *  &lt;extension point="org.eclipse.emf.ecore.extension_parser">
     *      &lt;parser type="abc" class="org.example.abc.util.AbcResourceFactoryImpl"/>
     *  &lt;extension>
     *</pre>
     * </p>
     * <p>
     * The {@link org.eclipse.emf.ecore.resource.Resource.Factory.Registry#INSTANCE global} resource factory registry's
     * {@link org.eclipse.emf.ecore.resource.Resource.Factory.Registry#getProtocolToFactoryMap() protocol} map
     * is populated by plugin registration of the form:
     *<pre>
     *  &lt;extension point="org.eclipse.emf.ecore.protocol_parser" >
     *      &lt;parser protocolName="abc" class="org.example.abc.util.AbcResourceFactoryImpl"/>
     *  &lt;extension>
     *</pre>
     * </p>
     * <p>
     * The {@link org.eclipse.emf.ecore.resource.URIConverter#URI_MAP global} URI map
     * is populated by plugin registration of the form:
     *<pre>
     *  &lt;extension point="org.eclipse.emf.ecore.uri_mapping" >
     *      &lt;mapping source="//special/" target="special/"/>
     *  &lt;extension>
     *</pre>
     * If the target is relative, it is resolved against the plugin's installed location,
     * resulting in a URI of the form:
     *<pre>
     *  platform:/plugin/plugin-name_1.2.3/...
     *</pre>
     * The above registration would map
     *<pre>
     *  //special/a/b.c
     *</pre>
     * to
     *<pre>
     *  platform:/plugin/plugin-name_1.2.3/special/a/b.c
     *</pre>
     * </p>
     * @throws CoreException if there is a show stopping problem.
     */
    public void startup() throws CoreException
    {
      super.startup();

      workspaceRoot = ResourcesPlugin.getWorkspace().getRoot();

      new GeneratedPackageRegistryReader().readRegistry();
      new ExtensionParserRegistryReader().readRegistry();
      new ProtocolParserRegistryReader().readRegistry();
      new URIMappingRegistryReader().readRegistry();
    }
  }

  /**
   * Returns the Eclipse plugin singleton.
   * @return the plugin singleton.
   */
  public static Implementation getPlugin()
  {
    return plugin;
  }

  /**
   * The plugin singleton
   */
  private static Implementation plugin;

  /**
   * The workspace root.
   * @see #getWorkspaceRoot
   */
  private static IWorkspaceRoot workspaceRoot;

  /**
   * Returns the workspace root, or <code>null</code>, if the runtime environment is stand-alone.
   * @return the workspace root, or <code>null</code>.
   */
  public static IWorkspaceRoot getWorkspaceRoot()
  {
    return workspaceRoot;
  }

  static final String GENERATED_PACKAGE_PPID = "generated_package";
  static final String EXTENSION_PARSER_PPID = "extension_parser";
  static final String PROTOCOL_PARSER_PPID = "protocol_parser";
  static final String SCHEME_PARSER_PPID = "scheme_parser";
  static final String URI_MAPPING_PPID = "uri_mapping";

  // ECLIPSE-DEPEND-END
}
